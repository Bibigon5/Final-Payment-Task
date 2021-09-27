<%--
  Created by IntelliJ IDEA.
  User: Максимилиан
  Date: 22.09.2021
  Time: 0:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page session="true" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>

<html lang="${sessionScope.lang}">
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <style>
        th {
            cursor: pointer;
        }
        th:last-child, td:last-child {
            text-align: right;
        }
    </style>
    <title>Title</title>
</head>
<body>
<jsp:include page="../common/_header.jsp"></jsp:include>
<jsp:include page="../common/_menu.jsp"></jsp:include>


<h3><fmt:message key="userInfo.greeting" />, ${user.userName}</h3>

<p style="color: red;">${errorString}</p>

<c:if test="${empty cardList}">
    <h3><fmt:message key="userInfo.suggestion" /></h3>
</c:if>

<c:if test="${not empty cardList}">
    <div class="container">
        <div class="row">
            <div class="col">

                <div class="card">
                    <h4 class="card-heading text-center mt-4 mb-4"><fmt:message key="userInfo.cardListHeader" /></h4>

                    <table class="table">
                        <thead>
                        <tr>
                            <th data-type="text"><fmt:message key="userInfo.cardName" /></th>
                            <th data-type="integer"><fmt:message key="userInfo.cardNumber" /></th>
                            <th data-type="double"><fmt:message key="userInfo.cardBalance" /></th>
                        </tr>
                        </thead>

                        <tbody>
                        <c:forEach items="${cardList}" var="account" >
                            <tr>
                                <td>${account.cardName}</td>
                                <td>${account.cardNumber}</td>
                                <td>${account.cardBalance}</td>
                                <c:choose>
                                    <c:when test="${account.cardStatus.equals('BLOCKED')}">
                                        <td>
                                            <a href="adminCardBlock?cardNumber=${account.cardNumber}"><fmt:message key="userInfo.cardUnblock" /></a>
                                        </td>
                                    </c:when>
                                    <c:otherwise>
                                        <td>
                                            <a href="adminCardBlock?cardNumber=${account.cardNumber}"><fmt:message key="userInfo.cardBlock" /></a>
                                        </td>
                                    </c:otherwise>
                                </c:choose>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>

            </div>
        </div>
    </div>
</c:if>
<script src="${pageContext.request.contextPath}/examples/main.js"></script>

</body>
</html>
