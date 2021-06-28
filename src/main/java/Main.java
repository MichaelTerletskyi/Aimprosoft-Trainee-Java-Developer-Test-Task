import models.Department;
import models.Employee;
import repositories.jdbc.impl.DepartmentJDBCRepository;
import repositories.jdbc.impl.EmployeeJDBCRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        DepartmentJDBCRepository departmentJDBCRepository = new DepartmentJDBCRepository();
        departmentJDBCRepository.create(new Department("Department"));
        Department department = departmentJDBCRepository.getByTitle("Department");


        EmployeeJDBCRepository employeeJDBCRepository = new EmployeeJDBCRepository();

        employeeJDBCRepository.create(new Employee("nameA", "nameA", "nameA@gmail.com",
                BigDecimal.valueOf(200L), LocalDate.of(1999, 11, 11), false));
        employeeJDBCRepository.create(new Employee("nameB", "nameB", "nameB@gmail.com",
                BigDecimal.valueOf(200L), LocalDate.of(1999, 11, 11), false));

        Employee employeeA = employeeJDBCRepository.getByEmail("nameA@gmail.com");
        Employee employeeB = employeeJDBCRepository.getByEmail("nameB@gmail.com");

        departmentJDBCRepository.addEmployee(department, Set.of(employeeA, employeeB));
    }
}
