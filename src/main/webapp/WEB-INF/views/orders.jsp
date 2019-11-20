<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>View Orders Page</title>
</head>
<body>
Orders of user: ${name}
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
                <a href="${pageContext.request.contextPath}/servlet/deleteOrder?order_id=${order.id}">DELETE</a>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
