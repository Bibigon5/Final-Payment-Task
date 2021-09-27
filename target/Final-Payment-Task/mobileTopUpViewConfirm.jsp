<%--
  Created by IntelliJ IDEA.
  User: Максимилиан
  Date: 03.09.2021
  Time: 0:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page session="true" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>

<%--<%@ taglib prefix="myLib" tagdir="WEB-INF/tags" %>--%>
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
    <title><fmt:message key="mobileTopUpConfirm.title" /></title>
</head>
<body>
<jsp:include page="common/_header.jsp"></jsp:include>
<jsp:include page="common/_menu.jsp"></jsp:include>

<h3><fmt:message key="mobileTopUpConfirm.header" /></h3>
<p style="color: red;">${errorString}</p>

<form method="POST" action="${pageContext.request.contextPath}/topUpConfirm">
    <table border="0">
        <tr>
            <td><fmt:message key="mobileTopUpConfirm.phoneNumber" />: </td>
            <td>${payment.paymentTelephone}</td>
        </tr>
        <tr>
            <td><fmt:message key="mobileTopUpConfirm.fromCard" />: </td>
            <td>${payment.cardNumber}</td>
        </tr>
        <%--<tr>
            <td>Commission<fmt:message key="mobileTopUpConfirm.commission" />: </td>
            <td>${user.password}</td><%--
        </tr>--%>
        <tr>
            <td><fmt:message key="mobileTopUpConfirm.creditedAmount" />: </td>
            <td>${payment.paymentAmount}</td>
        </tr>
        <tr>
            <td><fmt:message key="mobileTopUpConfirm.totalAmount" />: </td>
            <td>${payment.paymentAmount}</td><%-- amount + comission--%>
        </tr>
        <tr>
            <td colspan ="2">
                <input type="submit" value= "<fmt:message key="mobileTopUpConfirm.pay" />" />
                <a href="${pageContext.request.contextPath}/"><fmt:message key="mobileTopUpConfirm.editPayment" /></a>
            </td>
        </tr>
    </table>
</form>
<jsp:include page="common/_footer.jsp"></jsp:include>

</body>
</html>
