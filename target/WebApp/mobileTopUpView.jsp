<%--
  Created by IntelliJ IDEA.
  User: Максимилиан
  Date: 03.09.2021
  Time: 0:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Top Up</title>
</head>
<body>
<jsp:include page="_header.jsp"></jsp:include>
<jsp:include page="_menu.jsp"></jsp:include>

<h3>Product List</h3>

<form method="POST" action="${pageContext.request.contextPath}/topUp">
    <h3>From card: </h3>

    <ul>
        <c:forEach items="${cardList}" var="account">
            <li><c:out value="${account.cardName},
            ${account.cardNumber}, ${account.cardBalance}" /></li>
        </c:forEach>
    </ul>
    <table border="0">
<%--<table border="0">
        <tr>
            <td>From card: </td>
        </tr>
        <c:forEach items="${cardList}" var="account" >
            <tr>
                <input type="radio" id="html" name="cards" value="HTML">
                <label for="html">${account.cardName},
                        ${account.cardNumber},
                        ${account.cardBalance}</label>
                <%--<td>${account.cardName}</td>
                <td>${account.cardNumber}</td>
                <td>${account.cardBalance}</td>
                <td>
                    <a href="editProduct?code=${product.code}">Edit</a>
                </td>
                <td>
                    <a href="deleteProduct?code=${product.code}">Delete</a>
                </td>
            </tr>
        </c:forEach>--%>
        <tr>
            <td>Enter telephone number: </td>
            <td><input type="text" name="password" value= "${user.password}" /> </td>
        </tr>
        <tr>
            <td>Amount: </td>
            <td><input type="text" name="password" value= "${user.password}" /> </td>
        </tr>
        <tr>
            <td colspan ="2">
                <input type="submit" value= "Continue" />
                <a href="${pageContext.request.contextPath}/">Cancel</a>
            </td>
        </tr>
    </table>
</form>


</body>
</html>
