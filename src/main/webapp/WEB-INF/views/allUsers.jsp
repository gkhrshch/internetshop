<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="greeting" scope="request" type="java.lang.String"/>
<jsp:useBean id="user" scope="request" type="java.util.List<mate.academy.internetshop.model.User>"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>All Users</title>
</head>
<body>
Hello, ${greeting}, welcome to the All Users Page!
Users:
<table border="1">
    <tr>
        <th>ID</th>
        <th>Login</th>
        <th>Name</th>
        <th>Surname</th>
        <th>Delete</th>
    </tr>
    <c:forEach var="user" items="${user}">
    <tr>
        <td>
            <c:out value="${user.id}" />
        </td>
        <td>
            <c:out value="${user.login}" />
        </td>
        <td>
            <c:out value="${user.name}" />
        </td>
        <td>
            <c:out value="${user.surname}" />
        </td>
        <td>
            <a href="/internetshop_war_exploded/servlet/deleteUser?user_id=${user.id}">DELETE</a>
        </td>
    </tr>
    </c:forEach>
</body>
</html>
