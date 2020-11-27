<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <title>Contacts</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<div id="container">
    <jsp:include page="header.jsp">
        <jsp:param name="title" value="Contacts"/>
    </jsp:include>

    <main>
        <c:if test="${not empty contactRemovedMessage}">
            <div class="alert-success">
                <p>${contactRemovedMessage}</p>
            </div>
        </c:if>
        <h3>Contact overview</h3>
        <c:choose>
            <c:when test="${empty contacts}">
                <p>You haven't added any contacts yet.</p>
                <br>
            </c:when>
            <c:otherwise>
                <table>
                    <tr>
                        <th>Date</th>
                        <th>Hour</th>
                        <th>Name</th>
                        <th>Remove</th>
                    </tr>
                    <c:forEach var="contact" items="${contacts}">
                        <tr>
                            <td><c:out value="${contact.dateString}"/></td>
                            <td><c:out value="${contact.timeString}"/></td>
                            <td><c:out value="${contact.firstName} ${contact.lastName}"/></td>
                            <td><a href="Controller?command=RemoveContactConfirmationPage&contactid=${contact.contactid}">Remove</a></td>
                        </tr>
                    </c:forEach>
                    <caption>Contacts Overview</caption>
                </table>
                <br>
            </c:otherwise>
        </c:choose>

        <h3>Add a contact</h3>
        <c:if test="${not empty errors}">
            <div class="alert-danger">
                <ul>
                    <c:forEach var="error" items="${errors}">
                        <li>${error}</li>
                    </c:forEach>
                </ul>
            </div>
        </c:if>

        <form method="post" action="Controller?command=AddContact" novalidate>
            <p>
                <label for="firstName">First Name</label>
                <input class="form-group ${firstNameClass}" type="text" id="firstName" name="firstName" value="${fn:escapeXml(firstNamePreviousValue)}" required>
            </p>
            <p>
                <label for="lastName">Last Name</label>
                <input class="form-group ${lastNameClass}" type="text" id="lastName" name="lastName" value="${fn:escapeXml(lastNamePreviousValue)}" required>
            </p>
            <p>
                <label for="email">Email</label>
                <input class="form-group ${emailClass}" type="email" id="email" name="email" value="${fn:escapeXml(emailPreviousValue)}" required>
            </p>
            <p>
                <label for="phoneNumber">Phone number</label>
                <input class="form-group ${phoneNumberClass}" type="tel" id="phoneNumber" name="phoneNumber" value="${fn:escapeXml(phoneNumberPreviousValue)}" required>
            </p>
            <p>
                <label for="dateTime">Date and Time</label>
                <input class="form-group ${dateTimeClass}" type="datetime-local" id="dateTime" name="dateTime" value="${fn:escapeXml(dateTimePreviousValue)}" required>
            </p>
            <p>
                <input type="submit" id="add" value="Add">
            </p>
        </form>
    </main>

    <jsp:include page="footer.jsp"/>
</div>
</body>
</html>