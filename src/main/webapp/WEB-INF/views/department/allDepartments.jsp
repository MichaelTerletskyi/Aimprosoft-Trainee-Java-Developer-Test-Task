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
        <%@include file="css/allDepartments.css" %>
    </style>

</head>

<body>

<nav class="navbar navbar-expand-lg navbar-light bg-light text-center">
    <div class="collapse navbar-collapse" id="navbarNavDropdown">
        <ul class="navbar-nav">
            <li class="nav-item active">
                <a class="nav-link" href="${pageContext.request.contextPath}/departments/add" style="color:black; font-size: 30px">
                    Add new department
                </a>
            </li>
        </ul>
    </div>
</nav>

<div class="container text-center">
    <div class="card border-0 shadow my-5">
        <div class="card-body p-5">

            <h6>Departments</h6>

            <br>

            <table class="table center">
                <tr>
                    <th>Title</th>
                    <th>Description</th>
                    <th>Actions</th>
                    <th></th>
                    <th></th>
                    <th></th>
                </tr>
                <c:forEach var="department" items="${departments}">
                    <tr>
                        <td><c:out value="${department.getTitle()}"/></td>
                        <td><c:out value="${department.getDescription()}"/></td>
                        <td>
                            <a href="${pageContext.request.contextPath}/departments/employees?id=<c:out value='${department.getId()}'/>">
                                <button type="button" class="btn btn-outline-success">Employees</button>
                            </a>
                        </td>
                        <td>
                            <a href="${pageContext.request.contextPath}/departments/details?id=<c:out value='${department.getId()}'/>">
                                <button type="button" class="btn btn-outline-warning">Details</button>
                            </a>
                        </td>
                        <td>
                            <a href="${pageContext.request.contextPath}/departments/update?id=<c:out value='${department.getId()}'/>">
                                <button type="button" class="btn btn-outline-info">Edit</button>
                            </a>
                        </td>
                        <td>
                            <a href="${pageContext.request.contextPath}/departments/delete?id=<c:out value='${department.getId()}'/>">
                                <button type="button" class="btn btn-outline-danger">Delete</button>
                            </a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>
</div>
</body>
</html>
