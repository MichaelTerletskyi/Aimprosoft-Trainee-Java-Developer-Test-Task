package repositories.jdbc.impl;

import enums.EmployeeFetchType;
import exceptions.EntityNotFoundException;
import models.Department;
import repositories.jdbc.AbstractDepartmentJDBCRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

/**
 * @Create 6/27/2021
 * @Extends of {@link AbstractDepartmentJDBCRepository} class.
 */

public class DepartmentJDBCRepository extends AbstractDepartmentJDBCRepository {
    private final EmployeeJDBCRepository employeeJDBCRepository = new EmployeeJDBCRepository();

    @Override
    protected String createQuery() {
        return "INSERT INTO test_database.departments(title, description) VALUES (?, ?)";
    }

    @Override
    protected String getByIdQuery() {
        return "SELECT department_id, title, description FROM test_database.departments WHERE department_id = ?";
    }

    @Override
    protected String getAllQuery() {
        return "SELECT * FROM test_database.departments";
    }

    @Override
    protected String updateQuery() {
        return "UPDATE test_database.departments SET title = ?, description = ? WHERE department_id = ?";
    }

    @Override
    protected String deleteQuery() {
        return "DELETE FROM test_database.departments WHERE department_id = ?";
    }

    @Override
    protected String existByIdQuery() {
        return "SELECT COUNT(*) FROM test_database.departments WHERE department_id = ?";
    }

    @Override
    protected void preparedStatementExtract(PreparedStatement preparedStatement, Department element) throws SQLException {
        preparedStatement.setString(1, element.getTitle());
        preparedStatement.setString(2, element.getDescription());
    }

    @Override
    protected void preparedStatementExtractUpdate(PreparedStatement preparedStatement, Department element) throws SQLException {
        preparedStatementExtract(preparedStatement, element);
        preparedStatement.setLong(3, element.getId());
    }

    @Override
    protected Department resultSetExtract(ResultSet resultSet) throws SQLException {
        Department department = new Department();
        department.setId(resultSet.getLong("department_id"));
        department.setTitle(resultSet.getString("title"));
        department.setDescription(resultSet.getString("description"));
        return department;
    }

    @Override
    protected String optionalMessage() {
        return "Department not found";
    }

    @Override
    public Department getById(Long id, EmployeeFetchType employeeFetchType) {
        Department department = super.getById(id);
        if (employeeFetchType.equals(EmployeeFetchType.LAZY)) return department;
        if (employeeFetchType.equals(EmployeeFetchType.EAGER)) {
            department.setEmployees(employeeJDBCRepository.getAllByDepartmentId(id));
        }
        return department;
    }

    @Override
    public Long getIdByTitle(String title) {
        Long departmentId = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT department_id FROM test_database.departments WHERE title = ?")) {
            preparedStatement.setString(1, title);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) departmentId = rs.getLong("department_id");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(departmentId).orElseThrow(()
                -> new EntityNotFoundException(String.format("%s %s %s", "Department with title '", title, "' has not been found")));
    }

    @Override
    public Set<Department> getAll(EmployeeFetchType employeeFetchType) {
        Set<Department> all = super.getAll();
        if (employeeFetchType.equals(EmployeeFetchType.LAZY)) return all;
        if (employeeFetchType.equals(EmployeeFetchType.EAGER)) {
            all.forEach(department -> department.setEmployees(employeeJDBCRepository.getAllByDepartmentId(department.getId())));
        }
        return all;
    }

    @Override
    public Department getByTitle(String title) {
        Department department = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT department_id, title, description FROM test_database.departments WHERE title = ?")) {
            preparedStatement.setString(1, title);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                department = resultSetExtract(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(department).orElseThrow(() -> new EntityNotFoundException("Department with this title has not been found"));
    }

    @Override
    public Department getByTitle(String title, EmployeeFetchType employeeFetchType) {
        Department department = getByTitle(title);
        if (!Objects.isNull(department)) {
            if (employeeFetchType.equals(EmployeeFetchType.EAGER)) {
                Long id = getIdByTitle(department.getTitle());
                department.setEmployees(employeeJDBCRepository.getAllByDepartmentId(id));
            }
        }
        return Optional.ofNullable(department).orElseThrow(() -> new EntityNotFoundException("Department with this title has not been found"));
    }

    @Override
    public boolean existByTitle(String title) {
        boolean isTitleExist = false;
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT COUNT(*) FROM test_database.departments WHERE title = ?")) {
            preparedStatement.setString(1, title);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                isTitleExist = rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isTitleExist;
    }

    @Override
    public void addEmployee(long departmentId, long employeeId) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("UPDATE test_database.employees SET department_id = ? WHERE employee_id = ?")) {
            preparedStatement.setLong(1, departmentId);
            preparedStatement.setLong(2, employeeId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}