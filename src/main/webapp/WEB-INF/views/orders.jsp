<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Stella
  Date: 18.09.2019
  Time: 22:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>View Orders Page</title>
</head>
<body>
Orders by user ID: ${userId}
<table border="1">
    <tr>
        <th>Order ID</th>
        <th>Order contents</th>
        <th>Delete order</th>
    </tr>
    <c:forEach var="order" items="${orders}">
        <tr>
            <td>
                <c:out value="${order.id}" />
            </td>
            <td>
                <c:out value="${order}" />
            </td>
            <td>
                <a href="/internetshop_war_exploded/servlet/deleteOrder?order_id=${order.id}&user_id=${userId}">DELETE</a>
            </td>
        </tr>
    </c:forEach>
</table>
<form action="/internetshop_war_exploded/servlet/orderAccess" method="post">
    <p>To view orders, please enter userID</p>
    <div class="container">
        <label for="user_id"><b></b></label>
        <input type="text" placeholder="Enter User ID" name="user_id" required>
        <button type="submit" class="registerbtn">View Orders</button>
    </div>
</form>
</body>
</html>
