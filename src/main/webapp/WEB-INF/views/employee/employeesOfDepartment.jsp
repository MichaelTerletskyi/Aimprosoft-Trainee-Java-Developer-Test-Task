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
    <title>Employees of ${title}</title>

    <style>
        <%@include file="css/genericEmployee.css" %>
        <%@include file="css/employeesOfDepartment.css" %>
    </style>

</head>

<body>

<nav class="navbar navbar-expand-lg navbar-light bg-light text-center">
    <div class="collapse navbar-collapse" id="navbarNavDropdown">
        <ul class="navbar-nav">
            <li class="nav-item active">
                <a class="nav-link" href="${pageContext.request.contextPath}/departments" style="color:black; font-size: 30px">
                    RETURN TO DEPARTMENTS
                </a>
            </li>
        </ul>
    </div>
</nav>

<div class="container text-center">
    <div class="card border-0 shadow my-5">
        <div class="card-body p-5">

            <h6>Employees of ${title}</h6>

            <br>

            <table class="table center">
                <tr>
                    <th>ID</th>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Head</th>
                    <th>Actions</th>
                    <th></th>
                    <th></th>
                    <th></th>
                </tr>
                <c:forEach var="employee" items="${employees}">
                    <tr>
                        <td><c:out value="${employee.getId()}"/></td>
                        <td><c:out value="${employee.getFirstName()}"/></td>
                        <td><c:out value="${employee.getLastName()}"/></td>
                        <td><c:out value="${employee.isHead()}"/></td>
                        <td>
                            <a href="${pageContext.request.contextPath}/employee?id=<c:out value='${employee.getId()}'/>">
                                <button type="button" class="btn btn-outline-success">Head</button>
                            </a>
                        </td>
                        <td>
                            <a href="${pageContext.request.contextPath}/employee/update?id=<c:out value='${employee.getId()}'/>">
                                <button type="button" class="btn btn-outline-warning">Details</button>
                            </a>
                        </td>                        <td>
                            <a href="${pageContext.request.contextPath}/employee/update?id=<c:out value='${employee.getId()}'/>">
                                <button type="button" class="btn btn-outline-info">Update</button>
                            </a>
                        </td>
                        <td>
                            <a href="${pageContext.request.contextPath}/employee/delete?id=<c:out value='${employee.getId()}'/>">
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


<td>
    <a href="${pageContext.request.contextPath}/departments/details?id=<c:out value='${department.getId()}'/>">
        <button type="button" class="btn btn-outline-warning">Details</button>
    </a>
</td>