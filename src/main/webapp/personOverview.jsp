<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <title>Overview</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<div id="container">
    <jsp:include page="header.jsp">
        <jsp:param name="title" value="User Overview"/>
    </jsp:include>

    <main>
        <c:if test="${not empty persons}">
            <table>
                <tr>
                    <th>E-mail</th>
                    <th>First Name</th>
                    <th>Last Name</th>
                </tr>
                <c:forEach var="person" items="${persons}">
                    <tr>
                        <td><c:out value="${person.email}"/></td>
                        <td><c:out value="${person.firstName}"/></td>
                        <td><c:out value="${person.lastName}"/></td>
                    </tr>
                </c:forEach>
                <caption>Users Overview</caption>
            </table>
        </c:if>
    </main>

    <jsp:include page="footer.jsp"/>
</div>
</body>
</html>