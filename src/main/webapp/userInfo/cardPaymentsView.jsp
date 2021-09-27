<%--
  Created by IntelliJ IDEA.
  User: Максимилиан
  Date: 16.09.2021
  Time: 0:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page session="true" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>

<html lang="${sessionScope.lang}">
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <style>
        .text {
            text-align: center;
        }
        th {
            cursor: pointer;
        }
        th:last-child, td:last-child {
            text-align: right;
        }
    </style>
    <title><fmt:message key="cardPayments.title" /></title>
</head>
<body>
<jsp:include page="../common/_header.jsp"></jsp:include>
<jsp:include page="../common/_menu.jsp"></jsp:include>

<h3><fmt:message key="cardPayments.header" /></h3>

<div class="text">
    <p>
        <h3><fmt:message key="cardPayments.cardBalance" />: ${cardBalance}</h3>
    </p>
</div>

<p style="color: red;">${errorString}</p>

<c:if test="${empty paymentsList}">
    <h3><fmt:message key="cardPayments.suggestion" /></h3>
</c:if>

<c:if test="${not empty paymentsList}">
<div class="container">
    <div class="row">
        <div class="col">

            <div class="card">
                <h4 class="card-heading text-center mt-4 mb-4"><fmt:message key="cardPayments.paymentListHeader" /></h4>

                <table class="table">
                    <thead>
                    <tr>
                        <th data-type="integer"><fmt:message key="cardPayments.paymentNumber" /></th>
                        <th data-type="integer"><fmt:message key="cardPayments.fromCardNumber" /></th>
                        <th data-type="text"><fmt:message key="cardPayments.purpose" /></th>
                        <th data-type="integer"><fmt:message key="cardPayments.telephone" /></th>
                        <th data-type="double"><fmt:message key="cardPayments.amount" /></th>
                        <th data-type="date"><fmt:message key="cardPayments.dateAndTime" /></th>
                        <th data-type="text"><fmt:message key="cardPayments.status" /></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${paymentsList}" var="payment" >
                        <tr>
                            <td>${payment.paymentNumber}</td>
                            <td>${payment.cardNumber}</td>
                            <td>${payment.paymentPurpose}</td>
                            <td>${payment.paymentTelephone}</td>
                            <td>${payment.paymentAmount}</td>
                            <c:choose>
                                <c:when test="${payment.paymentDateAndTime.equals('Waiting for dispatch')}">
                                    <td>
                                        <fmt:message key="cardPayments.dateAndTimeWaiting" />
                                    </td>
                                </c:when>
                                <c:otherwise>
                                    <td>${payment.paymentDateAndTime}</td>
                                </c:otherwise>
                            </c:choose>
                            <c:choose>
                                <c:when test="${payment.paymentStatus.equals('Prepared')}">
                                    <td>
                                        <a href="paymentCommit?paymentNumber=${payment.paymentNumber}"><fmt:message key="cardPayments.statusPrepared" /></a>
                                    </td>
                                </c:when>
                                <c:otherwise>
                                    <td><fmt:message key="cardPayments.statusDispatched" /></td>
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
<jsp:include page="../common/_footer.jsp"></jsp:include>
<script src="${pageContext.request.contextPath}/examples/main.js"></script>

</body>
</html>
