<%--
  Created by IntelliJ IDEA.
  User: Максимилиан
  Date: 29.08.2021
  Time: 18:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page session="true" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>


<html lang="${sessionScope.lang}">
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <style>
        body {
            background: beige;
        }
        th {
            cursor: pointer;
        }
        th:last-child, td:last-child {
            text-align: right;
        }
    </style>
    <title><fmt:message key="home.homePage"/></title>
</head>
<body>
<jsp:include page="common/_header.jsp"></jsp:include>
<jsp:include page="common/_menu.jsp"></jsp:include>

<h3><fmt:message key="home.homePage"/></h3>
<br>
<h2><fmt:message key="home.description"/></h2>
<br>
<br>
<h4>
    <p>
        <fmt:message key="home.poem"/>
        <br>
        <fmt:message key="home.poemAuthor"/>
    </p>
</h4>
<br>

<jsp:include page="common/_footer.jsp"></jsp:include>

</body>
</html>
