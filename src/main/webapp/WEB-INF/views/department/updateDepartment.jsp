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

    <title>Edit Department</title>
    <style>
        <%@include file="css/genericDepartment.css" %>
        <%@include file="css/updateDepartment.css" %>
    </style>
</head>

<body>

<nav class="navbar navbar-expand-lg navbar-light bg-light text-center">
    <div class="collapse navbar-collapse" id="navbarNavDropdown">
        <ul class="navbar-nav">
            <li class="nav-item active">
                <a class="nav-link" href="${pageContext.request.contextPath}/departments" style="color:black; font-size: 30px">
                    Return to departments
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
                    <img src="https://www.allianceproject.com/wp-content/uploads/2019/07/buildings.png"
                         alt="image"/>
                    <h2>Edit</h2>
                    <h4>Department</h4>
                </div>
            </div>

            <div class="col-md-9">
                <div class="contact-form">

                    <div class="form-group">
                        <br/>
                        <br/>
                        <br/>
                        <br/>
                        <br/>
                        <br/>

                        <label for="title">Title</label>
                        <input type="text" class="form-control" id="title" name="title" value="${title}" required>
                        <span class="error">${errors.DepartmentTitleCannotBeNull}</span>
                        <span class="error">${errors.DepartmentTitleCannotBeEmpty}</span>
                        <span class="error">${errors.DepartmentWithThisTitleIsAlreadyExist}</span>
                        <span class="error">${errors.DepartmentTitleCanContainsFrom1To64Characters}</span>
                        <span class="error">${errors.DepartmentTitleCanContainsOnlyLatinsOrCyrillicLetter}</span>

                        <br/>

                        <label for="description">Description</label>
                        <input type="text" class="form-control" id="description" name="description" value="${description}" required>
                        <span class="error">${errors.DepartmentDescriptionCanContainsTo255Characters}</span>

                        <br/>

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