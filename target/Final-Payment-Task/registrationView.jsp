<%--
  Created by IntelliJ IDEA.
  User: Максимилиан
  Date: 29.08.2021
  Time: 18:55
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
        th {
            cursor: pointer;
        }
        th:last-child, td:last-child {
            text-align: right;
        }
    </style>
    <title><fmt:message key="registration.title" /></title>
</head>
<body>
<jsp:include page="common/_header.jsp"></jsp:include>
<jsp:include page="common/_menu.jsp"></jsp:include>

<h3><fmt:message key="registration.header" /></h3>
<p style="color: red;">${errorString}</p>


<form method="POST" action="${pageContext.request.contextPath}/register">
    <table border="0">
        <tr>
            <td><fmt:message key="registration.userName" /></td>
            <td><input type="text" name="userName" value= "${user.userName}" /> </td>
        </tr>
        <tr>
            <td><fmt:message key="registration.gender" /></td>
            <td><input type="text" name="gender" value= "${user.gender}" /> </td>
        </tr>
        <tr>
            <td><fmt:message key="registration.password" /></td>
            <td><input type="password" name="password" value= "${user.password}" /> </td>
        </tr>
        <tr>
            <td colspan ="2">
                <input type="submit" value= "<fmt:message key="registration.submit" />" />
                <a href="${pageContext.request.contextPath}/"><fmt:message key="registration.cancel" /></a><%--add reset button--%>
            </td>
        </tr>
    </table>
</form>

<p style="color:blue;">User Name example: tom,Tony322<fmt:message key="registration.exampleName" /></p>
<p style="color:blue;">Password example: tom001 or jerry001!_*/=<fmt:message key="registration.examplePass" /></p>
<jsp:include page="common/_footer.jsp"></jsp:include>

</body>
</html>
