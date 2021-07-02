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
        <%@include file="css/genericDepartment.css" %>
        <%@include file="css/detailsDepartment.css" %>
    </style>

</head>

<body>

<nav class="navbar navbar-expand-lg navbar-light bg-light text-center">
    <div class="collapse navbar-collapse" id="navbarNavDropdown">
        <ul class="navbar-nav">
            <li class="nav-item active">
                <a class="nav-link" href="${pageContext.request.contextPath}/departments"
                   style="color:black; font-size: 30px">
                    RETURN TO DEPARTMENTS
                </a>
            </li>
        </ul>
    </div>
</nav>

<div class="container text-center">
    <div class="card border-0 shadow my-5">
        <div class="card-body p-5">
            <h1>${title} Details</h1>

            <br>
            <br>

            <h2>ID : ${id} </h2>
            <h2>Title : ${title} </h2>
            <h2>Description : ${description} </h2>
            <h2>Amount of employees : ${amountOfEmployees} </h2>
            <h2>Head : ${fullNameOfHead} </h2>
            <h2>Average salary per hour : ${averageSalaryPerHour} $ </h2>
            <h2>Total salary : ${totalSalary} $</h2>
            <h2>Average age : ${averageAge} years </h2>
        </div>
    </div>
</div>
</body>