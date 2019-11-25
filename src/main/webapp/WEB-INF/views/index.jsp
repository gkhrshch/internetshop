<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Index</title>
</head>
<body>
Hello World!
<p><a href='${pageContext.request.contextPath}/servlet/getAllItems'>Available Items</a></p>
<p><a href='${pageContext.request.contextPath}/servlet/bucketAccess'>Your Bucket</a></p>
<p><a href='${pageContext.request.contextPath}/servlet/orderAccess'>Your Orders</a></p>
</body>
</html>
