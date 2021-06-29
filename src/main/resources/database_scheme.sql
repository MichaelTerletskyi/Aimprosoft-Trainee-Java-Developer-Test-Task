CREATE DATABASE test_database;

# @Create 6/27/2021

USE test_database;
CREATE TABLE IF NOT EXISTS DEPARTMENTS
(
    department_id BIGINT      NOT NULL AUTO_INCREMENT PRIMARY KEY,
    title         VARCHAR(64) NOT NULL UNIQUE
);

USE test_database;
CREATE TABLE IF NOT EXISTS EMPLOYEES
(
    employee_id     BIGINT       NOT NULL AUTO_INCREMENT PRIMARY KEY,
    first_name      VARCHAR(32)  NOT NULL,
    last_name       VARCHAR(32)  NOT NULL,
    email           VARCHAR(255) NOT NULL UNIQUE,
    salary_per_hour DECIMAL(7, 1),
    date_of_birth   DATE,
    head            TINYINT(1),
    department_id   BIGINT       NULL REFERENCES DEPARTMENTS (department_id)
);

INSERT INTO DEPARTMENTS(title)
VALUES ('Groove Street Department'),
       ('Los Santos Police Department');

INSERT INTO EMPLOYEES(first_name, last_name, email, salary_per_hour, date_of_birth, head, department_id)
VALUES ('Carl', 'Johnson', 'cj@gmail.com', 250000, '1968-05-03', 1, 1),
       ('Sean', 'Johnson', 'sweet@gmail.com', 30, '1966-03-11', 0, 1),
       ('Melvin', 'Harris', 'bigsmoke@yahoo.com', 100, '1967-10-29', 0, 1),
       ('Lance', 'Wilson', 'ryder@yahoo.com', 50, '1963-06-24', 0, 1),

       ('Frank', 'Tenpenny', 'officertenpenny@gmail.com', 80.0, '1958-11-12', 1, 2),
       ('Edward', 'Pulaski', 'officerpulaski@gmail.com', 80.0, '1956-02-27', 0, 2);