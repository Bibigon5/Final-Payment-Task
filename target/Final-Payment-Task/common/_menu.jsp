<%--
  Created by IntelliJ IDEA.
  User: Максимилиан
  Date: 29.08.2021
  Time: 17:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page session="true" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>
<html>
<head>
    <style>

    </style>
</head>
<body>
    <a href="${pageContext.request.contextPath}/"><fmt:message key="menu.homePage" /></a>
    |
    <a href="${pageContext.request.contextPath}/payments"><fmt:message key="menu.paymentsPage" /></a>
    |
    <a href="${pageContext.request.contextPath}/userInfo"><fmt:message key="menu.userInfoPage" /></a>
    |
    <a href="${pageContext.request.contextPath}/login"><fmt:message key="menu.loginPage" /></a>
    |
    <a href="${pageContext.request.contextPath}/register"><fmt:message key="menu.registerPage" /></a>
    <c:if test="${loginedUserName.equals('admin')}">
        |
        <a href="${pageContext.request.contextPath}/userList"><fmt:message key="menu.userListPage" /></a>
    </c:if>
</body>
</html>