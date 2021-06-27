package repositories.jdbc.impl;

import models.Department;
import repositories.jdbc.AbstractJDBCRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @Create 6/27/2021
 */

public class DepartmentJDBCRepository extends AbstractJDBCRepository<Department, Long> {

    @Override
    protected String createQuery() {
        return "INSERT INTO test_database.departments(title) VALUES (?)";
    }

    @Override
    protected String getByIdQuery() {
        return "SELECT department_id, title FROM test_database.departments WHERE department_id = ?";
    }

    @Override
    protected String getAllQuery() {
        return "SELECT * FROM test_database.departments";
    }

    @Override
    protected String updateQuery() {
        return "UPDATE test_database.departments SET title = ? WHERE department_id = ?";
    }

    @Override
    protected String deleteQuery() {
        return "DELETE FROM test_database.departments WHERE department_id = ?";
    }

    @Override
    protected void preparedStatementExtract(PreparedStatement preparedStatement, Department element) throws SQLException {
        preparedStatement.setString(1, element.getTitle());
    }

    @Override
    protected void preparedStatementExtractUpdate(PreparedStatement preparedStatement, Department element) throws SQLException {
        preparedStatementExtract(preparedStatement, element);
        preparedStatement.setLong(2, element.getId());
    }

    @Override
    protected Department resultSetExtract(ResultSet resultSet) throws SQLException {
        Department department = new Department();
        department.setId(resultSet.getLong("department_id"));
        department.setTitle(resultSet.getString("title"));
        return department;
    }

    @Override
    protected String optionalMessage() {
        return "Department not found";
    }
}
