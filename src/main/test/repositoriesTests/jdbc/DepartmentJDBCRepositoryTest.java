package repositoriesTests.jdbc;

import models.Department;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import repositories.jdbc.impl.DepartmentJDBCRepository;

/**
 * @Create 6/28/2021
 */

class DepartmentJDBCRepositoryTest {
    private DepartmentJDBCRepository departmentJDBCRepository = new DepartmentJDBCRepository();

    @Test
    void departmentAddAndExistByTitle() {
        Assertions.assertFalse(departmentJDBCRepository.existByTitle("Test department"));

        departmentJDBCRepository.create(new Department("Test department"));

        Assertions.assertTrue(departmentJDBCRepository.existByTitle("Test department"));
    }

    @Test
    void departmentUpdate() {
        Assertions.assertFalse(departmentJDBCRepository.existByTitle("Department"));
        Assertions.assertFalse(departmentJDBCRepository.existByTitle("Updated Department"));

        departmentJDBCRepository.create(new Department("Department"));

        Assertions.assertFalse(departmentJDBCRepository.existByTitle("Updated Department"));
        Assertions.assertTrue(departmentJDBCRepository.existByTitle("Department"));

        Department updatedDepartment = departmentJDBCRepository.getByTitle("Department");
        updatedDepartment.setTitle("Updated Department");
        departmentJDBCRepository.update(updatedDepartment);

        Assertions.assertFalse(departmentJDBCRepository.existByTitle("Department"));
        Assertions.assertTrue(departmentJDBCRepository.existByTitle("Updated Department"));
    }


    @Test
    void departmentDelete() {
        Assertions.assertFalse(departmentJDBCRepository.existByTitle("Some Department"));

        departmentJDBCRepository.create(new Department("Some Department"));

        Assertions.assertTrue(departmentJDBCRepository.existByTitle("Some Department"));

        departmentJDBCRepository.delete(departmentJDBCRepository.getIdByTitle("Some Department"));
        Assertions.assertFalse(departmentJDBCRepository.existByTitle("Some Department"));
    }
}