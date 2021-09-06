<%--
  Created by IntelliJ IDEA.
  User: Максимилиан
  Date: 02.09.2021
  Time: 23:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Payments</title>
</head>
<body>
<jsp:include page="_header.jsp"></jsp:include>
<jsp:include page="_menu.jsp"></jsp:include>

<h3>Payment Options</h3>

<a href="${pageContext.request.contextPath}/topUp">Mobile Top-Up</a>
<br />
<a href="${pageContext.request.contextPath}/cardTransfer">Transfer to a card</a><br />


</body>
</html>
