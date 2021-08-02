<%--
  Created by IntelliJ IDEA.
  User: david
  Date: 8/2/2021
  Time: 9:05 AM
  To change this template use File | Settings | File Templates.
--%>
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
<form method="post">
    <h3>Update user</h3>
    <label>Name</label>
    <br>
    <input type="text" name="name" value="${user.name}">
    <br>
    <label>dob</label>
    <br>
    <input type="text" name="dob" value="${user.dob}">
    <br>
    <label>address</label>
    <br>
    <input type="text" name="address" value="${user.address}">
    <br>
    <label>Phone</label>
    <br>
    <input type="text" name="phone" value="${user.phone}">
    <br>
    <label>Email</label>
    <br>
    <input type="text" name="email" value="${user.email}">
    <br>
    <label>Department</label>
    <br>
    <select name="department">
        <br>
        <c:forEach items="${departments}" var="department">
            <option value="${department.name}">${department.name}</option>
        </c:forEach>
    </select>
    <br>
    <button>Update</button>
    |<a href="/users">Back</a>
</form>
</body>
</html>