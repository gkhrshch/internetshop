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
        </tr>
    </c:forEach>
</table>
</body>
<form action="/internetshop_war_exploded/servlet/bucketAddItem" method="post">
    <div class="container">
        <p>Please fill in these forms to add certain item to certain bucket.</p>
        <label for="item_id"><b></b></label>
        <input type="text" placeholder="Enter Item ID" name="item_id" required>
        <label for="bucket_id"><b></b></label>
        <input type="text" placeholder="Enter Bucket ID" name="bucket_id" required>
        <button type="submit" class="registerbtn">Add To Bucket</button>
    </div>
</form>
</html>
