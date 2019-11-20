<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<div>${errorMsg}</div>
<form action="${pageContext.request.contextPath}/login" method="post">
    <div class="container">
        <h1>Login</h1>
        <p>Please fill in this form to sign in into account.</p>
        <hr>

        <label for="login"><b>Login</b></label>
        <input type="text" placeholder="Enter Login" name="login" required>

        <label for="psw"><b>Password</b></label>
        <input type="password" placeholder="Enter Password" name="psw" required>

        <p>By creating an account you agree to our <a href="#">Terms & Privacy</a>.</p>
        <button type="submit" class="registerbtn">Sign in</button>
    </div>

    <div class="container signin">
        <p>Don't have an account?<a href='${pageContext.request.contextPath}/registration'>Sign up</a></p>
    </div>
</form>
</body>
</html>
