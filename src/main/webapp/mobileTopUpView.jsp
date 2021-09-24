<%--
  Created by IntelliJ IDEA.
  User: Максимилиан
  Date: 03.09.2021
  Time: 0:10
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
    <title><fmt:message key="mobileTopUp.title" /></title>
</head>
<body>
<jsp:include page="common/_header.jsp"></jsp:include>
<jsp:include page="common/_menu.jsp"></jsp:include>

<h3><fmt:message key="mobileTopUp.header" /></h3>

<p style="color: red;">${errorString}</p>

<c:if test="${not empty cardList}">
<form method="POST" action="${pageContext.request.contextPath}/topUp">
    <h2><fmt:message key="mobileTopUp.fromCard" />: </h2>


    <table border="1" cellpadding="5" cellspacing="1" >
        <c:forEach items="${cardList}" var="account" >
            <tr>
                <input type="radio" id="html" name="card" value="${account.cardNumber}">
                <label for="html"><fmt:message key="mobileTopUp.cardName" />: ${account.cardName}<br>
                        <fmt:message key="mobileTopUp.cardNumber" />: ${account.cardNumber}<br>
                        <fmt:message key="mobileTopUp.cardBalance" />: ${account.cardBalance}</label><br>
            </tr>
        </c:forEach>
    </table>

    <%-- <ul>
         <c:forEach items="${cardList}" var="account">
             <li><c:out value="${account.cardName},
             ${account.cardNumber}, ${account.cardBalance}" /></li>
         </c:forEach>
     </ul>
     <table border="0">
 <table border="0">
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

    <label for="telephone"><fmt:message key="mobileTopUp.telephoneNumber" />: </label>
    <input type="tel" id="telephone" name="telephone" value= "${payment.paymentTelephone}"
           placeholder="38-123-562-8787" pattern="[0-9]{2}[0-9]{3}[0-9]{3}[0-9]{4}"/><br>

    <label for="amount"><fmt:message key="mobileTopUp.amount" />: </label>
    <input type="number" id="amount" name="amount" value= "${payment.paymentAmount}" /><br>

    <label for="purpose"><fmt:message key="mobileTopUp.purpose" />: </label>
    <textarea id="purpose" name="purpose" value= "${payment.paymentPurpose}"
              rows="4" cols="50"></textarea><br>

        <%--<table border="1" cellpadding="5" cellspacing="1">
            <tr>
                <td>Enter telephone number: </td>
                <td><input type="tel" name="telephone" value= "${payment.paymentTelephone}"
                           placeholder="38-123-562-8787" pattern="[0-9]{2}[0-9]{3}[0-9]{3}[0-9]{4}"/> </td>
            </tr>
            <tr>
                <label for="amount">Amount: </label>
                <%--<td>Amount: </td>
            <td><input type="number" id="amount" name="amount" value= "${payment.password}" /> </td>
        </tr>
        <tr>
            <label for="purpose">Payment Purpose: </label>
            <td><textarea id="purpose" name="purpose" rows="4" cols="50"></textarea> </td>
            <%--<td><input type="text" name="purpose" value= "${user.password}" /> </td>
        </tr>--%>
    <input type="submit" value= "<fmt:message key="mobileTopUp.continue" />" />
    <input type="reset" value="<fmt:message key="mobileTopUp.reset" />">
    <a href="${pageContext.request.contextPath}/"><fmt:message key="mobileTopUp.cancel" /></a>

</form>
</c:if>
<jsp:include page="common/_footer.jsp"></jsp:include>

</body>
</html>
