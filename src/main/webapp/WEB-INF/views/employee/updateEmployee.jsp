<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link rel="icon" href="https://www.flaticon.com/svg/static/icons/svg/1126/1126202.svg">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">

    <title>Edit ${firstName} ${lastName}</title>
    <style>
        <%@include file="css/genericEmployee.css" %>
        <%@include file="css/updateEmployee.css" %>
    </style>
</head>

<body>

<nav class="navbar navbar-expand-lg navbar-light bg-light text-center">
    <div class="collapse navbar-collapse" id="navbarNavDropdown">
        <ul class="navbar-nav">
            <li class="nav-item active">
                <a class="nav-link" href="${pageContext.request.contextPath}/departments/employees?id=${departmentId}"
                   style="color:black; font-size: 30px">
                    Return to ${departmentTitle}
                </a>
            </li>
        </ul>
    </div>
</nav>

<form class="needs-validation" method="post" novalidate>
    <div class="container contact">
        <div class="row">

            <div class="col-md-3">
                <div class="contact-info">
                    <img src="https://image.flaticon.com/icons/png/512/4557/4557765.png"
                         alt="image" width="300"/>
                    <h2>Edit</h2>
                    <h4>Employee</h4>
                </div>
            </div>

            <div class="col-md-9">
                <div class="contact-form">

                    <div class="form-group">
                        <br/>

                        <input type="hidden" class="form-control" id="employeeId" name="employeeId" value="${employeeId}" >

                        <label for="firstName">First Name</label>
                        <input type="text" class="form-control" id="firstName" name="firstName" value="${firstName}" required>
                        <span class="error">${errors.EmployeeFirstNameCannotBeNull}</span>
                        <span class="error">${errors.EmployeeFirstNameCannotBeEmpty}</span>
                        <span class="error">${errors.EmployeeFirstNameCanContainsFrom1To32Letters}</span>
                        <span class="error">${errors.EmployeeFirstNameCanContainsOnlyLatinsOrCyrillicLetter}</span>

                        <br/>

                        <label for="lastName">Last Name</label>
                        <input type="text" class="form-control" id="lastName" name="lastName" value="${lastName}" required>
                        <span class="error">${errors.EmployeeLastNameCannotBeNull}</span>
                        <span class="error">${errors.EmployeeLastNameCannotBeEmpty}</span>
                        <span class="error">${errors.EmployeeLastNameCanContainsFrom1To32Letters}</span>
                        <span class="error">${errors.EmployeeLastNameCanContainsOnlyLatinsOrCyrillicLetter}</span>

                        <br/>

                        <label for="email">Email</label>
                        <input type="email" class="form-control" id="email" name="email" value="${email}" required>
                        <span class="error">${errors.EmployeeWithThisEmailIsAlreadyExist}</span>
                        <span class="error">${errors.EmployeeEmailCannotBeNull}</span>
                        <span class="error">${errors.EmployeeEmailCannotBeEmpty}</span>
                        <span class="error">${errors.IncorrectEmail}</span>

                        <br/>

                        <label for="salaryPerHour">Salary Per Hour ($)</label>
                        <input type="number" class="form-control" id="salaryPerHour" name="salaryPerHour" value="${salaryPerHour}" required>
                        <span class="error">${errors.EmployeeSalaryPerHourCannotBeNull}</span>
                        <span class="error">${errors.EmployeeSalaryPerHourCannotBeLessThen1}</span>
                        <span class="error">${errors.EmployeeSalaryPerHourCannotBeMoreThen99999}</span>

                        <br/>

                        <label for="dateOfBirth">Date Of Birth</label>
                        <input type="date" class="form-control" id="dateOfBirth" name="dateOfBirth" value="${dateOfBirth}" required>
                        <span class="error">${errors.EmployeeDateOfBirthCannotBeNull}</span>
                        <span class="error">${errors.EmployeeDateOfBirthCanBeOnlyInPast}</span>

                        <br/>

                        <input type="hidden" class="form-control" id="head" name="head" value="${head}">

                        <div class="invalid-feedback">This field is mandatory.</div>
                    </div>

                    <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-10">
                            <button type="submit" class="btn btn-default">Edit</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</form>
</body>
</html>