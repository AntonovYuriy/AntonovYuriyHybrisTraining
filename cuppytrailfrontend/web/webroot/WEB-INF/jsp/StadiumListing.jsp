<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
<title>Stadium Listing</title>
<body>
<h1>Stadium Listing</h1>
<ul>
    <c:set var="count" value="0" scope="page" />

    <c:forEach var="stadium" items="${stadiums}">
        <c:set var="count" value="${count + 1}" scope="page"/>
        <form action="${pageContext.request.contextPath}/deleteStadium/${stadium.name}" method="post">
            <li>
                <a href="./stadiums/${stadium.name}">${count} - ${stadium.name} have ${stadium.capacity} capacity</a>
                <input type="submit" value="Delete 1 stadium ${stadium.name}"/>
            </li>
        </form>
    </c:forEach>

    <a class="btn btn-default" href="./deleteAllStadiums" role="button">Delete all stadiums</a>
    <br/>
    <form action="${pageContext.request.contextPath}/deleteAllStadiums" method="post">
    <input  type="submit"  value="Delete All"/>
    </form>

</ul>
</body>
</html>