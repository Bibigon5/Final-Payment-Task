<%--
  Created by IntelliJ IDEA.
  User: Максимилиан
  Date: 18.09.2021
  Time: 19:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>

<fmt:setLocale value="${param.lang}"/>
<fmt:setBundle basename="messages"/>

<html lang="${param.lang}">
<head>
    <title>PhraseApp - i18n</title>
</head>
<body>
<h2>
    <fmt:message key="label.chooseSessionLocale" />
</h2>
<ul>
    <li><a href="?sessionLocale=en"><fmt:message key="label.lang.en" /></a></li>
    <li><a href="?sessionLocale=ru"><fmt:message key="label.lang.ru" /></a></li>

</ul>
<c:if test="${not empty param.sessionLocale}">
    <fmt:message key="label.cookieChangeSuccess" />
    <button><a href="testSessionLocale.jsp"><fmt:message key="label.viewPage" /></a></button>
</c:if>
</body>
</html>
