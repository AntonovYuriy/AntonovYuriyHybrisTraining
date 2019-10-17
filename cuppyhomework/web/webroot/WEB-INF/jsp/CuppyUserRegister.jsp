<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>REGISTRATION</title>
</head>
<body>
<h1>This is the Cuppy User registration page</h1>

<form action="${pageContext.request.contextPath}/cuppyUser/register" method="post">
    <table style="with: 50%">
        <tr>
            <td>Name</td>
            <td><input type="text" name="name"/></td>
        </tr>
        <tr>
            <td>UserName</td>
            <td><input type="text" name="username"/></td>
        </tr>
        <tr>
            <td>Password</td>
            <td><input type="password" name="password"/></td>
        </tr>
        <tr>
            <td>Contact Phone No</td>
            <td><input type="text" name="phone"/></td>
        </tr>
    </table>
    <input type="submit" value="Submit"/></form>
</form>


<br/>
<a href="../cuppyUser">Home</a>
</br>
</br>
</br>
</br>
<br/>
<a href="./logOff">LogOff</a>

</body>
</html>
