<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
<title>ALL Matches</title>
<body>
<h1>Matches Listing</h1>

<ul>
    <c:forEach var="match" items="${allMatches}">
        <li>${match.homeTeam} (${match.homeGoals}) - ${match.guestTeam} (${match.guestGoals}) = DATE = ${match.date}</li>
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