<%--
  Created by IntelliJ IDEA.
  User: Максимилиан
  Date: 29.08.2021
  Time: 22:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Product List</title>
</head>
<body>
<jsp:include page="common/_header.jsp"></jsp:include>
<jsp:include page="common/_menu.jsp"></jsp:include>

<h3>Product List</h3>

<p style="color: red;">${errorString}</p>

<table border="1" cellpadding="5" cellspacing="1" >
    <tr>
        <th>Code</th>
        <th>Name</th>
        <th>Price</th>
        <th>Edit</th>
        <th>Delete</th>
    </tr>
    <c:forEach items="${productList}" var="product" >
        <tr>
            <td>${product.code}</td>
            <td>${product.name}</td>
            <td>${product.price}</td>
            <td>
                <a href="editProduct?code=${product.code}">Edit</a>
            </td>
            <td>
                <a href="deleteProduct?code=${product.code}">Delete</a>
            </td>
        </tr>
    </c:forEach>
</table>

<a href="createProduct" >Create Product</a>

<jsp:include page="common/_footer.jsp"></jsp:include>

</body>
</html>
