<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>LOGIN PAGE</title>
</head>
<body>
<h1>This is the Cuppy User Login page</h1>

<h1>Please log in</h1>

<form action="${pageContext.request.contextPath}/cuppyUser/login" method="post">
    <table style="with: 50%">
        <tr>
            <td>Log in</td>
            <td><input type="text" name="login" /></td>
        </tr>
        <tr>
            <td>Password</td>
            <td><input type="text" name="password" /></td>
        </tr>
        </table>
    <input type="submit" value="Submit" />
</form>

<br/>
<a href="${pageContext.request.contextPath}/cuppyUser/register">Register</a>

</body>
</html>
