package repositories.jdbc;

import exceptions.EntityNotFoundException;
import repositories.utils.JDBCUtil;

import java.sql.*;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * @Create 6/27/2021
 */

//  Абстрактный класс призван предоставлять базовый функционал для классов-наследников.
//  А производные классы уже реализуют этот функционал.

public abstract class AbstractJDBCRepository<T, K> {

    /**
     * ОСНОВНЫЕ ПОНЯТИЯ
     *
     * JDBC API {@link Statement} используется для выполнения SQL запросов к базе данных.
     * Объект Statement можно получить с помощью метода Connection.getStatement().
     * Вызывая методы execute(), executeQuery(), executeUpdate() и др., можно выполнять различные статичные SQL запросы.
     *
     *
     * Объект {@link PreparedStatement} используется для выполнения прекомпилированных SQL-запросов с или без входных (IN) параметров.
     * Мы можем использовать сеттеры для установки значений в запрос.
     * Т.к. {@link PreparedStatement} является предкомпилированным, то он может быть эффективно использован множество раз.
     * {@link PreparedStatement} считается лучшим выбором нежели {@link Statement}
     * Т.к. он автоматически обрабатывает специальные символы, а так же предотвращает, так называемые, SQL injection attack (когда в запрос можно подставить свой код).
     *
     *
     * JDBC {@link ResultSet} – интерфейс, объект которого создается в результате запроса к базе данных.
     * Его можно представить в виде таблицы данных, которая была сформирована в ответ на запрос.
     * Объект {@link ResultSet} поддерживает курсор, который указывает на текущую строку данных.
     * При инициализации курсор устанавливается до первой строки. Для движение по строкам используется метод next().
     * При наличии строк после текущей позиции, метод next() возвращает true, что можно использовать для итерации по таблице полученных результатов.
     * Объект {@link ResultSet} автоматически закрывается при закрытии объекта, который его сгенерировал.
     * Так же закрытие произойдет при повторном выполнении запроса или возврату результата из другого набора результатов.
     */

    protected Connection connection = JDBCUtil.getConnection();

    protected abstract String createQuery();
    protected abstract String getByIdQuery();
    protected abstract String getAllQuery();
    protected abstract String updateQuery();
    protected abstract String deleteQuery();
    protected abstract String existByIdQuery();

    protected abstract void preparedStatementExtract(PreparedStatement preparedStatement, T element) throws SQLException;
    protected abstract void preparedStatementExtractUpdate(PreparedStatement preparedStatement, T element) throws SQLException;
    protected abstract T resultSetExtract(ResultSet resultSet) throws SQLException;

    protected abstract String optionalMessage();

    public T create(T element) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(createQuery())) {
            preparedStatementExtract(preparedStatement, element);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return element;
    }

    public T getById(K id) {
        T t = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(getByIdQuery())) {
            preparedStatement.setLong(1, (Long) id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                t = resultSetExtract(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(t).orElseThrow(() -> new EntityNotFoundException(optionalMessage()));
    }

    public Set<T> getAll() {
        Set<T> tSet = new HashSet<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(getAllQuery());
            while (resultSet.next()) {
                tSet.add(resultSetExtract(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tSet;
    }

    public T update(T element) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery())) {
            preparedStatementExtractUpdate(preparedStatement, element);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return element;
    }

    public void delete(K id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery())) {
            preparedStatement.setLong(1, (Long) id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean existById(K id) {
        boolean exist = false;
        try (PreparedStatement preparedStatement = connection.prepareStatement(existByIdQuery())) {
            preparedStatement.setLong(1, (Long) id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) exist = rs.getInt(1) > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return exist;
    }
}