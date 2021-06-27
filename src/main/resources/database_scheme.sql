CREATE DATABASE test_database;

# @Create 6/27/2021

USE test_database;
CREATE TABLE DEPARTMENTS
(
    department_id BIGINT      NOT NULL AUTO_INCREMENT PRIMARY KEY,
    title         VARCHAR(64) NOT NULL UNIQUE
);

USE test_database;
CREATE TABLE EMPLOYEES
(
    employee_id     BIGINT       NOT NULL AUTO_INCREMENT PRIMARY KEY,
    first_name      VARCHAR(32)  NOT NULL,
    last_name       VARCHAR(32)  NOT NULL,
    email           VARCHAR(255) NOT NULL UNIQUE,
    salary_per_hour DECIMAL(7, 1),
    date_of_birth   DATE,
    department_id   BIGINT       NULL,
    CONSTRAINT departmentEmployeesIndex
        FOREIGN KEY (department_id) REFERENCES DEPARTMENTS (department_id)
);

CREATE INDEX departmentEmployeesIndex
    ON EMPLOYEES (department_id);



INSERT INTO DEPARTMENTS(title)
VALUES ('Groove Street Department'),
       ('Los Santos Police Department');

INSERT INTO EMPLOYEES(first_name, last_name, email, salary_per_hour, date_of_birth, department_id)
VALUES ('Carl', 'Johnson', 'cj@gmail.com', 250000, '1968-05-03', 1),
       ('Sean', 'Johnson', 'sweet@gmail.com', 30, '1966-03-11', 1),
       ('Melvin', 'Harris', 'bigsmoke@yahoo.com', 100, '1967-10-29', 1),
       ('Lance', 'Wilson', 'ryder@yahoo.com', 50, '1963-06-24', 1),

       ('Frank', 'Tenpenny', 'officertenpenny@gmail.com', 80.0, '1958-11-12', 2),
       ('Edward', 'Pulaski', 'officerpulaski@gmail.com', 80.0, '1956-02-27', 2)
