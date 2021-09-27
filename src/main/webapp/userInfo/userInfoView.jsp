<%--
  Created by IntelliJ IDEA.
  User: Максимилиан
  Date: 29.08.2021
  Time: 19:00
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
    <title><fmt:message key="userInfo.title" /></title>
</head>
<body>
<jsp:include page="../common/_header.jsp"></jsp:include>
<jsp:include page="../common/_menu.jsp"></jsp:include>

<h3><fmt:message key="userInfo.greeting" />, ${user.userName}</h3>

<fmt:message key="userInfo.userName" />: <b>${user.userName}</b>
<br />
<c:choose>
<c:when test="${user.gender.equals('Male')}">
    <fmt:message key="userInfo.gender" />: <fmt:message key="registration.genderMale" />
</c:when>
<c:otherwise>
    <fmt:message key="userInfo.gender" />: <fmt:message key="registration.genderFemale" />
</c:otherwise>
</c:choose>

<br />

<c:if test="${empty cardList}">
    <h3><fmt:message key="userInfo.suggestion" /></h3>
</c:if>

<p style="color: red;">${errorString}</p>

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
                            <td>
                                <a href="cardPayments?cardNumber=${account.cardNumber}">${account.cardNumber}</a>
                            </td>
                            <td>${account.cardBalance}</td>
                            <td>
                                <a href="cardTopUp?cardNumber=${account.cardNumber}"><fmt:message key="userInfo.cardTopUp" /></a>
                            </td>
                            <c:choose>
                                <c:when test="${account.cardStatus.equals('BLOCKED')}">
                                    <td>
                                        <a href="cardBlock?cardNumber=${account.cardNumber}"><fmt:message key="userInfo.cardUnblock" /></a>
                                    </td>
                                </c:when>
                                <c:otherwise>
                                    <td>
                                        <a href="cardBlock?cardNumber=${account.cardNumber}"><fmt:message key="userInfo.cardBlock" /></a>
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

<h3><fmt:message key="userInfo.youCanAddCard" />, ${user.userName}</h3>

<p style="color: red;">${errorString1}</p>
<p style="color: red;">${notification}</p>


<form method="POST" action="${pageContext.request.contextPath}/userInfo">
    <table border="0">
        <tr>
            <td><fmt:message key="userInfo.cardName" />:</td>
            <td><input type="text" name="cardName" maxlength="20" value= "${card.cardName}" /> </td>
        </tr>
        <tr>
            <td><fmt:message key="userInfo.cardNumber" />:</td>
            <td><input type="number" name="cardNumber" min="1000000000000000" maxlength="16"
                       oninput="javascript: if (this.value.length > this.maxLength) this.value = this.value.slice(0, this.maxLength);"
                       placeholder="5686-1379-8556-1375"
                       value= "${card.cardNumber}" /> </td>
        </tr>
        <tr>
            <td colspan ="2">
                <input type="submit" value= "<fmt:message key="userInfo.submit" />" />
                <a href="${pageContext.request.contextPath}/"><fmt:message key="userInfo.cancel" /></a><%--вставить кнопку ресет--%>
            </td>
        </tr>
    </table>
</form>

<jsp:include page="../common/_footer.jsp"></jsp:include>
<script src="${pageContext.request.contextPath}/examples/main.js"></script>

</body>
</html>
