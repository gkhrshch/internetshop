<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Stella
  Date: 18.09.2019
  Time: 17:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Bucket Access</title>
</head>
<body>
Bucket Access Page
<table border="1">
    <tr>
        <th>Bucket ID: ${bucketId}</th>
        <th>Item ID</th>
        <th>Name</th>
        <th>Price</th>
        <th>Remove Item From Bucket</th>
    </tr>
    <c:forEach var="item" items="${item}">
        <tr>
            <td></td>
            <td>
                <c:out value="${item.id}" />
            </td>
            <td>
                <c:out value="${item.name}" />
            </td>
            <td>
                <c:out value="${item.price}" />
            </td>
            <td>
                <a href="/internetshop_war_exploded/servlet/bucketRemoveItem?item_id=${item.id}&bucket_id=${bucketId}">REMOVE</a>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
<form action="/internetshop_war_exploded/servlet/bucketAccess" method="post">
    <div class="container">
        <label for="id"><b></b></label>
        <input type="text" placeholder="Enter Bucket ID" name="id" required>
        <button type="submit" class="registerbtn">Show Bucket Contents</button>
    </div>
</form>
<form action="/internetshop_war_exploded/servlet/completeOrder?bucket_id=${bucketId}" method="post">
    <p>To create order from this bucket, please enter userID</p>
    <div class="container">
        <label for="user_id"><b></b></label>
        <input type="text" placeholder="Enter User ID" name="user_id" required>
        <button type="submit" class="registerbtn">Complete Order</button>
    </div>
</form>
</html>
