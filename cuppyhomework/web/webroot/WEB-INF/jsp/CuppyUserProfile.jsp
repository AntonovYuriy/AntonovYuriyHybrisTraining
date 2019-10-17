<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>PROFILE PAGE</title>
</head>
<body>
<h1>This is the Cuppy User Details Page</h1>


<h1>Hello ${registeredUserName}
    List of Special matches:</h1>
<%--CHANGE SPECIAL FIELD--%>
<ul>
    <c:forEach var="match" items="${matchesSelected}">
        <li>${match.homeTeam} (${match.homeGoals}) - ${match.guestTeam} (${match.guestGoals}) DATA  ${match.date} SPECIAL = ${match.id}</li>
    </c:forEach>
</ul>

</br>
</br>
</br>
</br>
<br/>
<a href="./logOff">LogOff</a>

</body>
</html>
