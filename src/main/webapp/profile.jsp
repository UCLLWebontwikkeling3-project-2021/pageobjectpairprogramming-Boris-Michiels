<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <title>Profile</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<div id="container">
    <jsp:include page="header.jsp">
        <jsp:param name="title" value="Profile"/>
    </jsp:include>

    <main>
        <c:choose>
            <c:when test="${empty person}">
                <c:if test="${not empty deleteMessage}">
                    <div class="alert-success">
                        <p>${deleteMessage}</p>
                    </div>
                </c:if>

                <h3>Log in</h3>
                <c:if test="${not empty logInMessage}">
                    <div class="alert-danger">
                        <p>${logInMessage}</p>
                    </div>
                </c:if>
                <form method="post" action="Controller?command=LogIn" novalidate>
                    <p>
                        <label for="useridLogIn">User id</label>
                        <input type="text" id="useridLogin" name="useridLogIn" value="${fn:escapeXml(useridLogInPreviousValue)}" required>
                    </p>
                    <p>
                        <label for="passwordLogIn">Password</label>
                        <input type="password" id="passwordLogIn" name="passwordLogIn" required>
                    </p>
                    <p>
                        <input type="submit" id="logIn" value="Log In">
                    </p>
                </form>

                <h3>Register</h3>
                <c:if test="${not empty errors}">
                    <div class="alert-danger">
                        <ul>
                            <c:forEach var="error" items="${errors}">
                                <li>${error}</li>
                            </c:forEach>
                        </ul>
                    </div>
                </c:if>
                <form method="post" action="Controller?command=Register" novalidate>
                    <p>
                        <label for="userid">User id</label>
                        <input class="form-group ${useridClass}" type="text" id="userid" name="userid" value="${fn:escapeXml(useridPreviousValue)}" required>
                    </p>
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
                        <label for="password">Password</label>
                        <input class="form-group ${passwordClass}" type="password" id="password" name="password" value="${fn:escapeXml(passwordPreviousValue)}" required>
                    </p>
                    <p>
                        <input type="submit" id="signUp" value="Sign Up">
                    </p>
                </form>
            </c:when>
            <c:otherwise>
                <h3>Welcome ${person.firstName} ${person.lastName}</h3>
                <c:if test="${not empty newPwMessage}">
                    <div class="${fn:contains(newPwMessage, "No") ? "alert-danger" : "alert-success"}">
                        <p>${newPwMessage}</p>
                    </div>
                </c:if>
                <p>Manage your account</p>

                <form method="post" action="Controller?command=LogOut" novalidate>
                    <p>
                        <input type="submit" id="logOut" value="Log Out">
                    </p>
                </form>

                <form method="post" action="Controller?command=ChangePassword" novalidate>
                    <p>
                        <label for="newPassword">New Password</label>
                        <input type="text" id="newPassword" name="newPassword" required>
                    </p>
                    <p>
                        <input type="submit" id="changePassword" value="Change Password">
                    </p>
                </form>

                <form method="post" action="Controller?command=DeleteConfirmationPage" novalidate>
                    <p>
                        <input type="submit" id="deleteConfirmation" value="Delete account">
                    </p>
                </form>
            </c:otherwise>
        </c:choose>
    </main>

    <jsp:include page="footer.jsp"/>
</div>
</body>
</html>