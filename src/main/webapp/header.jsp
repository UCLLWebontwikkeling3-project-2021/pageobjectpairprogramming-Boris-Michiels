<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<header>
    <h1><span>Restaurant Contact Tracer</span></h1>

    <nav>
        <ul>
            <li ${param.title == 'Home' ? "id=actual" : ""}><a href="Controller?command=HomePage">Home</a></li>
            <c:if test="${not empty person}">
                <c:if test="${person.role == 'ADMIN'}">
                    <li ${param.title == 'User Overview' ? "id=actual" : ""}><a href="Controller?command=OverviewPage">Overview</a></li>
                </c:if>
                <li ${param.title == 'Contacts' ? "id=actual" : ""}><a href="Controller?command=ContactsPage">Contacts</a></li>
                <li ${param.title == 'Register Test Result' ? "id=actual" : ""}><a href="Controller?command=RegisterTestResultPage">Register Test Result</a></li>
                <li ${param.title == 'Search' ? "id=actual" : ""}><a href="Controller?command=SearchPage">Search</a></li>
            </c:if>
            <li ${param.title == 'Profile' ? "id=actual" : ""}><a href="Controller?command=ProfilePage">Profile</a></li>
        </ul>
    </nav>

    <h2>${param.title}</h2>
</header>