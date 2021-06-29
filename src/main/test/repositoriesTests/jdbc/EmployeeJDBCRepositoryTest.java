package repositoriesTests.jdbc;

import models.Department;
import models.Employee;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import repositories.jdbc.impl.DepartmentJDBCRepository;
import repositories.jdbc.impl.EmployeeJDBCRepository;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @Create 6/28/2021
 */

class EmployeeJDBCRepositoryTest {
    private EmployeeJDBCRepository employeeJDBCRepository = new EmployeeJDBCRepository();
    private DepartmentJDBCRepository departmentJDBCRepository = new DepartmentJDBCRepository();

    @Test
    void employeeAddAndExistByEmail() {
        Assertions.assertFalse(employeeJDBCRepository.existByEmail("cesarvialpando@gmail.com"));

        employeeJDBCRepository.create(new Employee("Cesar", "Vialpando", "cesarvialpando@gmail.com",
                BigDecimal.valueOf(200L), LocalDate.of(1964, 6, 9), false));

        Assertions.assertTrue(employeeJDBCRepository.existByEmail("cesarvialpando@gmail.com"));
    }

    @Test
    void departmentUpdate() {
        Assertions.assertFalse(employeeJDBCRepository.existByEmail("jeffreycross1973@gmail.com"));
        Assertions.assertFalse(employeeJDBCRepository.existByEmail("updatedJeffreycross1973@gmail.com"));

        employeeJDBCRepository.create(new Employee("Jeffrey", "Cross", "jeffreycross1973@gmail.com",
                BigDecimal.valueOf(200L), LocalDate.of(1998, 6, 12), false));

        Assertions.assertFalse(employeeJDBCRepository.existByEmail("updatedJeffreycross1973@gmail.com"));
        Assertions.assertTrue(employeeJDBCRepository.existByEmail("jeffreycross1973@gmail.com"));

        Employee updatedEmployee = employeeJDBCRepository.getByEmail("jeffreycross1973@gmail.com");
        updatedEmployee.setEmail("updatedJeffreycross1973@gmail.com");
        employeeJDBCRepository.update(updatedEmployee);

        Assertions.assertFalse(employeeJDBCRepository.existByEmail("jeffreycross1973@gmail.com"));
        Assertions.assertTrue(employeeJDBCRepository.existByEmail("updatedJeffreycross1973@gmail.com"));
    }

    @Test
    void departmentDelete() {
        Assertions.assertFalse(employeeJDBCRepository.existByEmail("franklinclinton1988@yahoo.com"));

        employeeJDBCRepository.create(new Employee("Franklin", "Clinton", "franklinclinton1988@yahoo.com",
                BigDecimal.valueOf(200L), LocalDate.of(1998, 6, 12), false));

        Assertions.assertTrue(employeeJDBCRepository.existByEmail("franklinclinton1988@yahoo.com"));

        employeeJDBCRepository.delete(employeeJDBCRepository.getIdByEmail("franklinclinton1988@yahoo.com"));
        Assertions.assertFalse(employeeJDBCRepository.existByEmail("franklinclinton1988@yahoo.com"));
    }

    @Test
    void departmentFetchEmployee() {
        Assertions.assertFalse(departmentJDBCRepository.existByTitle("Fetched Department"));
        departmentJDBCRepository.create(new Department("Fetched Department"));
        Assertions.assertTrue(departmentJDBCRepository.existByTitle("Fetched Department"));

        Assertions.assertFalse(employeeJDBCRepository.existByEmail("michaeldesanta@gmail.com"));
        Assertions.assertFalse(employeeJDBCRepository.existByEmail("trevor@gmail.com"));


        employeeJDBCRepository.create(new Employee("Michael", "De Santa", "michaeldesanta@gmail.com",
                BigDecimal.valueOf(200L), LocalDate.of(1965, 6, 6), true));
        employeeJDBCRepository.create(new Employee("Trevor", "Philips", "trevor@gmail.com",
                BigDecimal.valueOf(200L), LocalDate.of(1968, 7, 4), true));

        Assertions.assertTrue(employeeJDBCRepository.existByEmail("michaeldesanta@gmail.com"));
        Assertions.assertTrue(employeeJDBCRepository.existByEmail("trevor@gmail.com"));

        Department departmentBeforeAdding = departmentJDBCRepository.getByTitle("Fetched Department");
        Assertions.assertNull(departmentBeforeAdding.getEmployees());

        Employee michaelDeSanta = employeeJDBCRepository.getByEmail("michaeldesanta@gmail.com");
        Employee trevorPhilips = employeeJDBCRepository.getByEmail("trevor@gmail.com");


        departmentJDBCRepository.addEmployee(departmentBeforeAdding.getId(), michaelDeSanta.getId());
        departmentJDBCRepository.addEmployee(departmentBeforeAdding.getId(), trevorPhilips.getId());

        Department departmentWithoutIncludeEmployees = departmentJDBCRepository.getByTitle("Fetched Department");
        Assertions.assertNull(departmentWithoutIncludeEmployees.getEmployees());

        Department departmentWithFalseIncludeEmployees = departmentJDBCRepository.getByTitle("Fetched Department", false);
        Assertions.assertNull(departmentWithFalseIncludeEmployees.getEmployees());

        Department departmentWithTrueIncludeEmployees = departmentJDBCRepository.getByTitle("Fetched Department", true);
        Assertions.assertNotNull(departmentWithTrueIncludeEmployees.getEmployees());
        Assertions.assertEquals(2, departmentWithTrueIncludeEmployees.getEmployees().size());
    }
}