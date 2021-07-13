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
    <title>Departments</title>

    <style>
        <%@include file="css/genericEmployee.css" %>
        <%@include file="css/detailsEmployee.css" %>
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

<div class="container text-center">
    <div class="card border-0 shadow my-5">
        <div class="card-body p-5">
            <div class="about-section">
                <h1>Details of ${firstName} ${lastName}</h1>
                <h2 style="color: white">${email}</h2>
            </div>

            <div class="row">
                <div class="column">
                    <div class="card">
                        <div class="container">

                            <br>

                            <h2>ID</h2>
                            <h1 style="color: black">${id}</h1>
                        </div>
                    </div>
                </div>

                <div class="column">
                    <div class="card">
                        <div class="container">

                            <br>

                            <h2>First Name</h2>
                            <h1 style="color: black">${firstName}</h1>
                        </div>
                    </div>
                </div>

                <div class="column">
                    <div class="card">
                        <div class="container">

                            <br>

                            <h2>Last Name</h2>
                            <h1 style="color: black">${lastName}</h1>
                        </div>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="column">
                    <div class="card">
                        <div class="container">

                            <br>

                            <h2>Is Head</h2>
                            <h1 style="color: black">${head}</h1>
                        </div>
                    </div>
                </div>

                <div class="column">
                    <div class="card">
                        <div class="container">

                            <br>

                            <h2>Salary Per Hour</h2>
                            <h1 style="color: black">${salaryPerHour} $</h1>
                        </div>
                    </div>
                </div>

                <div class="column">
                    <div class="card">
                        <div class="container">

                            <br>

                            <h2>Salary Per Year</h2>
                            <h1 style="color: black">${salaryPerYear} $</h1>
                        </div>
                    </div>
                </div>
            </div>


            <div class="row">
                <div class="column">
                    <div class="card">
                        <div class="container">

                            <br>

                            <h2>Date Of Birth</h2>
                            <h1 style="color: black">${dateOfBirth}</h1>
                        </div>
                    </div>
                </div>

                <div class="column">
                    <div class="card">
                        <div class="container">

                            <br>

                            <h2>Age</h2>
                            <h1 style="color: black">${age} years</h1>
                        </div>
                    </div>
                </div>
            </div>

        </div>
    </div>
</div>
</body>