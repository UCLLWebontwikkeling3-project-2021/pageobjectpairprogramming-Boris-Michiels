<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <title>Search</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<div id="container">
    <jsp:include page="header.jsp">
        <jsp:param name="title" value="Search"/>
    </jsp:include>

    <main>
        <c:choose>
            <c:when test="${empty contacts}">
                <p>${searchMessage}</p>
                <br>
            </c:when>
            <c:otherwise>
                <h3>Contact the following people</h3>
                <table>
                    <tr>
                        <th>Name</th>
                        <th>GSM</th>
                        <th>Email</th>
                        <th></th>
                    </tr>
                    <c:forEach var="contact" items="${contacts}">
                        <tr>
                            <td><c:out value="${contact.firstName} ${contact.lastName}"/></td>
                            <td><c:out value="${contact.phoneNumber}"/></td>
                            <td><c:out value="${contact.email}"/></td>
                        </tr>
                    </c:forEach>
                    <caption>Contacts Overview</caption>
                </table>
                <br>
            </c:otherwise>
        </c:choose>
    </main>

    <jsp:include page="footer.jsp"/>
</div>
</body>
</html>