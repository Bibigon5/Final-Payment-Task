<%--
  Created by IntelliJ IDEA.
  User: Максимилиан
  Date: 29.08.2021
  Time: 23:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Delete Product</title>
</head>
<body>
<jsp:include page="common/_header.jsp"></jsp:include>
<jsp:include page="common/_menu.jsp"></jsp:include>

<h3>Delete Product</h3>

<p style="color: red;">${errorString}</p>
<a href="productList">Product List</a>

<jsp:include page="common/_footer.jsp"></jsp:include>

</body>
</html>
