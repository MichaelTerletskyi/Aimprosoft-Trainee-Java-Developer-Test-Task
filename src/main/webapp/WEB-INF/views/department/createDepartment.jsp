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

    <title>Add New Department</title>

</head>
<body class="bg-light text-dark"><br>

<div class="py-md-1" style="text-align: center;">
    <h1>Add New Department</h1>
</div>


<form class="needs-validation" method="post" novalidate>
    <div class="mx-auto" style="width: 400px;">
        <div class="form-group px-md-5">


            <label for="title">Title</label>
            <input type="text" class="form-control" id="title" name="title" placeholder="Enter Title" required>
            <span class="error" style="color: red">${errors.DepartmentTitleCannotBeNull}</span>
            <span class="error" style="color: red">${errors.DepartmentTitleCannotBeEmpty}</span>
            <span class="error" style="color: red">${errors.DepartmentWithThisTitleIsAlreadyExist}</span>
            <span class="error" style="color: red">${errors.DepartmentTitleCanContainsFrom1To64Characters}</span>
            <span class="error" style="color: red">${errors.DepartmentTitleCanContainsOnlyLatinsOrCyrillicLetter}</span>

            <h1></h1>

            <label for="description">Description</label>
            <input type="text" class="form-control" id="description" name="description" placeholder="Enter Description"
                   required>
            <span class="error" style="color: red">${errors.DepartmentDescriptionCanContainsTo255Characters}</span>

            <h1></h1>

            <div class="invalid-feedback">This field is mandatory.</div>
        </div>
    </div>

    <div class="container" style="text-align: center">
        <button type="submit" class="btn btn-success">ADD</button>
        <a href="${pageContext.request.contextPath}/departments">
            <button type="button" class="btn btn-primary">Return</button>
        </a>
    </div>

</form>
</body>
</html>
