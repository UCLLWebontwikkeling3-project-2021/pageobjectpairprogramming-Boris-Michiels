<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <title>Something wrong</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<div id="container">
    <jsp:include page="header.jsp">
        <jsp:param name="title" value="Something is Wrong"/>
    </jsp:include>

    <main>
        <p>Sorry, we couldn't find the requested page (404 error).</p>
    </main>

    <jsp:include page="footer.jsp"/>
</div>
</body>
</html>