package repositories.jdbc.impl;

import models.Employee;
import repositories.jdbc.AbstractJDBCRepository;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @Create 6/27/2021
 */

public class EmployeeJDBCRepository extends AbstractJDBCRepository<Employee, Long> {

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
}