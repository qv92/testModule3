
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container" align="center">
<form method="post">
    <a href="/users?action=create">Add new User</a>
    <input type="hidden" name="action" value="search">
    <input type="text" name="name">
    <button>Search</button>
    <table border="1px solid black">
        <tr>
            <td colspan="7">User list</td>
        </tr>
        <tr>
            <td>#</td>
            <td>User Name</td>
            <td>DOB</td>
            <td>address</td>
            <td>phone</td>
            <td>Department</td>
            <td>Action</td>
        </tr>
        <c:forEach items="${users}" var="user">
            <tr>
                <td>${user.id}</td>
                <td>${user.name}</td>
                <td>${user.dob}</td>
                <td>${user.address}</td>
                <td>${user.phone}</td>
                <td><c:forEach items="${departments}" var="department">
                    <c:if test="${user.departmentId == department.id}">
                        ${department.name}
                    </c:if>
                </c:forEach> </td>
                <td><a href="/users?action=edit&id=${user.id}">Edit</a>|<a href="/users?action=delete&id=${user.id}">Delete</a></td>
            </tr>
        </c:forEach>
    </table>
</form>
    <a href="/products">Product</a>
    <a href="/users">Users</a>
</div>
</body>
</html>