<%--
  Created by IntelliJ IDEA.
  User: Максимилиан
  Date: 07.09.2021
  Time: 16:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
    <title><fmt:message key="cardTopUp.title" /></title>
</head>
<body>
<jsp:include page="../common/_header.jsp"></jsp:include>
<jsp:include page="../common/_menu.jsp"></jsp:include>

<h3><fmt:message key="cardTopUp.header" /></h3>

<p style="color: red;">${errorString}</p>

<c:if test="${not empty card}">
    <form method="POST" action="${pageContext.request.contextPath}/cardTopUp">
        <input type="hidden" name="cardNumber" value="${card.cardNumber}" />
        <table border="0">
            <tr>
                <td><fmt:message key="cardTopUp.cardNumber" />: </td>
                <td style="color:green;">${card.cardNumber}</td>
            </tr>
            <tr>
                <td><fmt:message key="cardTopUp.enterAmount" />: </td>
                <td><input type="number" name="cardBalanceTopUp" value="${card.cardBalanceTopUp}" /></td>
            </tr>
            <tr>
                <td colspan = "2">
                    <input type="submit" value="<fmt:message key="cardTopUp.submit" />" />
                    <a href="${pageContext.request.contextPath}/userInfo"><fmt:message key="cardTopUp.reset" /></a><%--add reset button--%>
                </td>
            </tr>
        </table>
    </form>
</c:if>

<jsp:include page="../common/_footer.jsp"></jsp:include>

</body>
</html>
