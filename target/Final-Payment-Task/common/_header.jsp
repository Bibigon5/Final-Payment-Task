<%--
  Created by IntelliJ IDEA.
  User: Максимилиан
  Date: 29.08.2021
  Time: 17:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${param.lang}"/>
<fmt:setBundle basename="messages"/>

<div style="background: #E0E0E0; height: 55px; padding: 5px;">
    <div style="float: left">
        <h1>XTreme Payment System</h1>
    </div>

    <div style="float: right; padding: 10px; text-align: right;">

        <a href="?sessionLocale=en"><fmt:message key="label.lang.en" /></a>
        |
        <a href="?sessionLocale=ru"><fmt:message key="label.lang.ru" /></a>
        <!-- User store in session with attribute: loginedUser -->
        <c:if test="${not empty loginedUserName}">
                <fmt:message key="header.greeting" />, <b>${loginedUser.userName}!</b>
        <br/>
        </c:if>

    </div>

</div>