<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!doctype html>
<html>
<title>Stadium Listing</title>
<body>
<h1>Stadium Listing</h1>
<ul>
    <c:set var="count" value="0" scope="page"/>

    <div>
        <h1>Error = ${errorMessage}</h1>
    </div>

    ${errorMessage}

    <c:forEach var="stadium" items="${stadiums}">
        <c:set var="count" value="${count + 1}" scope="page"/>
        <form action="${pageContext.request.contextPath}/deleteStadium/${stadium.name}" method="post">
            <li>
                <a href="./stadiums/${stadium.name}">${count} - ${stadium.name} have ${stadium.capacity} capacity</a>
                <img style="margin-left:20px;vertical-align:top;margin-bottom:20px" src="${stadium.imageUrl}"/>
                <input type="submit" value="Delete 1 stadium ${stadium.name}"/>
            </li>
        </form>
    </c:forEach>
    <br/>
    Total ${count} stadiums found
    <br/>
    <br/>
    <c:choose>
        <c:when test="${fn:length(stadiums) gt 0}">
            <a class="btn btn-default" href="./deleteAllStadiums" role="button">Delete all stadiums</a>
            <br/>
            <form action="${pageContext.request.contextPath}/deleteAllStadiums" method="post">
                <input type="submit" value="Delete All"/>
            </form>
        </c:when>
        <c:otherwise>
            <br>
            Stadiums not found
            <br/>
        </c:otherwise>
    </c:choose>

    <br/>
    <a href="${pageContext.request.contextPath}/addNewRandomStadium">Add New Random Stadium</a>
    <br/>
    <a href="${pageContext.request.contextPath}/addNewStadium">Add New Stadium</a>
    <br/>
    <a href="${pageContext.request.contextPath}/stadiums">Back to Stadium Listing</a>

</ul>
</body>
</html>