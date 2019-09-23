<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="item" scope="request" type="java.util.List<mate.academy.internetshop.model.Item>"/>
<%--
  Created by IntelliJ IDEA.
  User: Stella
  Date: 18.09.2019
  Time: 8:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>All Items</title>
</head>
<body>
Items:
<p></p>
<table border="1">
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Price</th>
        <th>Add Item To Bucket</th>
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
                <a href="/internetshop_war_exploded/servlet/bucketAddItem?item_id=${item.id}">ADD</a>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
