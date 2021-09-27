<%--
  Created by IntelliJ IDEA.
  User: Максимилиан
  Date: 01.09.2021
  Time: 0:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div align = "center">
    <h1>Insert Register Form</h1>
    <form action="<%= request.getContextPath()%>/registration" method = "post">
        <table style="with: 80%">
            <tr>
                <td>Title</td>
                <td><input type="text" name="title"/></td>
            </tr>
            <tr>
                <td>Author</td>
                <td><input type="text" name="author"/></td>
            </tr>
            <tr>
                <td>Quantity</td>
                <td><input type="text" name="quantity"/></td>
            </tr>
        </table>
        <input type="submit" value="Submit" />
    </form>
</div>
</body>
</html>
