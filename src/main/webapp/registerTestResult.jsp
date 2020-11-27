<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <title>Register Test Result</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<div id="container">
    <jsp:include page="header.jsp">
        <jsp:param name="title" value="Register Test Result"/>
    </jsp:include>

    <main>
        <c:if test="${not empty errors}">
            <div class="alert-danger">
                <ul>
                    <c:forEach var="error" items="${errors}">
                        <li>${error}</li>
                    </c:forEach>
                </ul>
            </div>
        </c:if>

        <form method="post" action="Controller?command=RegisterTestResult" novalidate>
            <p>
                <label for="date">Date</label>
                <input class="form-group ${dateClass}" type="date" id="date" name="date" value="${fn:escapeXml(datePreviousValue)}" required>
            </p>
            <p>
                <input type="submit" id="add" value="Register Positive Test Result">
            </p>
        </form>
    </main>

    <jsp:include page="footer.jsp"/>
</div>
</body>
</html>