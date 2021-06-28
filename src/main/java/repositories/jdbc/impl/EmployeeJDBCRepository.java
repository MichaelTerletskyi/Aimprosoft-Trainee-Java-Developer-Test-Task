package repositories.jdbc.impl;

import exceptions.EntityNotFoundException;
import models.Employee;
import repositories.IEmployee;
import repositories.jdbc.AbstractJDBCRepository;

import java.sql.*;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * @Create 6/27/2021
 */

public class EmployeeJDBCRepository extends AbstractJDBCRepository<Employee, Long> implements IEmployee {

    /**
     * {@link AbstractJDBCRepository} block
     */

    @Override
    protected String createQuery() {
        return "INSERT INTO test_database.employees (first_name, last_name, email, salary_per_hour, date_of_birth, head) VALUES (?, ?, ?, ?, ?, ?)";
    }

    @Override
    protected String getByIdQuery() {
        return "SELECT employee_id, first_name, last_name, email, salary_per_hour, date_of_birth, head FROM test_database.employees WHERE employee_id = ?";
    }

    @Override
    protected String getAllQuery() {
        return "SELECT * FROM test_database.employees";
    }

    @Override
    protected String updateQuery() {
        return "UPDATE test_database.employees SET first_name = ?, last_name = ?, email = ?, salary_per_hour = ?, date_of_birth = ?, head = ? WHERE employee_id = ?";
    }

    @Override
    protected String deleteQuery() {
        return "DELETE FROM test_database.employees WHERE employee_id = ?";
    }

    @Override
    protected String existByIdQuery() {
        return "SELECT COUNT(*) FROM test_database.employees WHERE employee_id = ?";
    }

    @Override
    protected void preparedStatementExtract(PreparedStatement preparedStatement, Employee element) throws SQLException {
        preparedStatement.setString(1, element.getFirstName());
        preparedStatement.setString(2, element.getLastName());
        preparedStatement.setString(3, element.getEmail());
        preparedStatement.setBigDecimal(4, element.getSalaryPerHour());
        preparedStatement.setDate(5, Date.valueOf(element.getDateOfBirth()));
        preparedStatement.setBoolean(6, element.isHead());
    }

    @Override
    protected void preparedStatementExtractUpdate(PreparedStatement preparedStatement, Employee element) throws SQLException {
        preparedStatementExtract(preparedStatement, element);
        preparedStatement.setLong(7, element.getId());
    }

    @Override
    protected Employee resultSetExtract(ResultSet resultSet) throws SQLException {
        Employee employee = new Employee();
        employee.setId(resultSet.getLong("employee_id"));
        employee.setFirstName(resultSet.getString("first_name"));
        employee.setLastName(resultSet.getString("last_name"));
        employee.setEmail(resultSet.getString("email"));
        employee.setSalaryPerHour(resultSet.getBigDecimal("salary_per_hour"));
        employee.setDateOfBirth(resultSet.getDate("date_of_birth").toLocalDate());
        employee.setHead(resultSet.getBoolean("head"));
        return employee;
    }

    @Override
    protected String optionalMessage() {
        return "Employee not found";
    }


    /**
     * {@link IEmployee} block
     */

    @Override
    public Set<Employee> getAllByDepartmentId(Long id) {
        Set<Employee> employees = new HashSet<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM test_database.employees WHERE department_id = ?")) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                employees.add(resultSetExtract(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }

    @Override
    public Employee getByEmail(String email) {
        Employee employee = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT employee_id, first_name, last_name, email, salary_per_hour, date_of_birth, head FROM test_database.employees WHERE email = ?")) {
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                employee = resultSetExtract(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(employee).orElseThrow(() -> new EntityNotFoundException("Employee with this email has not been found"));
    }

    @Override
    public boolean existByEmail(String email) {
        boolean isEmailExist = false;
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT COUNT(*) FROM test_database.employees WHERE email = ?")) {
            preparedStatement.setString(1, email);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                isEmailExist = rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isEmailExist;
    }

    @Override
    public Long getIdByEmail(String email) {
        Long employeeId = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT employee_id FROM test_database.employees WHERE email = ?")) {
            preparedStatement.setString(1, email);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) employeeId = rs.getLong("employee_id");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(employeeId).orElseThrow(()
                -> new EntityNotFoundException(String.format("%s %s %s", "Employee with email '", email, "' has not been found ")));
    }
}