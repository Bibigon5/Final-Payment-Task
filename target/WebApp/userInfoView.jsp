<%--
  Created by IntelliJ IDEA.
  User: Максимилиан
  Date: 29.08.2021
  Time: 19:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User Info</title>
</head>
<body>
<jsp:include page="_header.jsp"></jsp:include>
<jsp:include page="_menu.jsp"></jsp:include>

<h3>Hello, ${user.userName}</h3>

User Name: <b>${user.userName}</b>
<br />
Gender: ${user.gender }

<h3>You can add your credit card below, ${user.userName}</h3>

<form method="POST" action="${pageContext.request.contextPath}/userInfo">
    <table border="0">
        <tr>
            <td>Card name:</td>
            <td><input type="text" name="cardName" value= "${card.cardName}" /> </td>
        </tr>
        <tr>
            <td>Card number:</td>
            <td><input type="number" name="cardNumber" value= "${card.cardNumber}" /> </td>
        </tr>
        <tr>
            <td colspan ="2">
                <input type="submit" value= "Submit" />
                <a href="${pageContext.request.contextPath}/">Cancel</a>
            </td>
        </tr>
    </table>
</form>

<jsp:include page="_footer.jsp"></jsp:include>

</body>
</html>
