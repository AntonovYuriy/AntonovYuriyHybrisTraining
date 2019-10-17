<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
<title>ALL USERS</title>
<body>
<h1>Cuppy Users Details Listing</h1>

<ul>
    <c:forEach var="match" items="${allMatches}">
        <li>${match.homeTeam} (${match.homeGoals}) - ${match.guestTeam} (${match.guestGoals}) DATA  ${match.date} SPECIAL = ${match.id}</li>
    </c:forEach>
</ul>

<br/>
<br/>
<br/>

<a href="./">Home</a>
<br/>
<a href="./cuppyUser">User Login</a>
</body>
</html>