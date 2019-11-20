<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Bucket Access</title>
</head>
<body>
<p>Bucket Access Page</p>
<p>User: ${user}</p>
<table border="1">
    <tr>
        <th>Item ID</th>
        <th>Name</th>
        <th>Price</th>
        <th>Remove Item From Bucket</th>
    </tr>
    <c:forEach var="item" items="${item}">
        <tr>
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
                <a href="${pageContext.request.contextPath}/servlet/bucketRemoveItem?item_id=${item.id}">REMOVE</a>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
<form action="${pageContext.request.contextPath}/servlet/completeOrder" method="get">
        <button type="submit" class="registerbtn">Complete Order</button>
</form>
</html>
