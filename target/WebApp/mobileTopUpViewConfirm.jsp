<%--
  Created by IntelliJ IDEA.
  User: Максимилиан
  Date: 03.09.2021
  Time: 0:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Confirmation</title>
</head>
<body>
<h3>Confirmation</h3>
<form method="POST" action="${pageContext.request.contextPath}/topUp">
    <table border="0">
        <tr>
            <td>Phone Number: </td>
            <td><input type="text" name="userName" value= "${user.userName}" /> </td>
        </tr>
        <tr>
            <td>From card: </td>
            <td><input type="text" name="password" value= "${user.password}" /> </td>
        </tr>
        <tr>
            <td>Commission: </td>
            <td><input type="text" name="password" value= "${user.password}" /> </td>
        </tr>
        <tr>
            <td>Amount to be credited: </td>
            <td><input type="text" name="password" value= "${user.password}" /> </td>
        </tr>
        <tr>
            <td>Total amount: </td>
            <td><input type="text" name="password" value= "${user.password}" /> </td>
        </tr>
        <tr>
            <td colspan ="2">
                <input type="submit" value= "Pay" />
                <a href="${pageContext.request.contextPath}/">Cancel</a>
            </td>
        </tr>
    </table>
</form>

</body>
</html>
