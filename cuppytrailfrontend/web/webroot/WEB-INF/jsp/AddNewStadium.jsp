<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
<title>New Stadium</title>
<body>
<h1>Adding new Stadium</h1>

Add New stadium<br><br>

<form action="${pageContext.request.contextPath}/stadiumAddingProcedure" method="post">
    <p> Name <input type="text" name="name"></p>
    <p> Capacity <input type="text" name="capacity"></p>
    <p><input type="submit" value="Add new unknown stadium"></p>
</form>

</ul>
<a href="${pageContext.request.contextPath}/stadiums">Back to Stadium Listing</a>
</body>
</html>