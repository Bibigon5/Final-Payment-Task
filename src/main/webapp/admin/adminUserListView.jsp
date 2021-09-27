<%--
  Created by IntelliJ IDEA.
  User: Максимилиан
  Date: 10.09.2021
  Time: 0:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
    <title>List of Users<fmt:message key="adminUserList.title" /></title>
</head>
<body>
<jsp:include page="../common/_header.jsp"></jsp:include>
<jsp:include page="../common/_menu.jsp"></jsp:include>

<h3><fmt:message key="adminUserList.greeting" /></h3>

<p style="color: red;">${errorString}</p>

<div class="container">
    <div class="row">
        <div class="col">

            <div class="card">
                <h4 class="card-heading text-center mt-4 mb-4"><fmt:message key="adminUserList.userListHeader" /></h4>

                <table class="table">
                    <thead>
                    <tr>
                        <th data-type="text"><fmt:message key="adminUserList.userName" /></th>
                        <th data-type="text"><fmt:message key="adminUserList.gender" /></th>
                    </tr>
                    </thead>

                    <tbody>
                    <c:forEach items="${userList}" var="user" >
                        <tr>
                            <td>${user.userName}</td>
                            <td>${user.gender}</td>
                            <td>
                                <a href="getUsersCards?userName=${user.userName}"><fmt:message key="adminUserList.usersCards" /></a>
                            </td>
                            <%-- бахнуть иф юзер норм -> блок, наоборот -> разблок --%>
                            <c:choose>
                                <c:when test="${user.userStatus.equals('BLOCKED')}">
                                    <td>
                                        <a href="blockUser?userName=${user.userName}"><fmt:message key="adminUserList.unblockUser"/></a>
                                    </td>
                                </c:when>
                                <c:otherwise>
                                    <td>
                                        <a href="blockUser?userName=${user.userName}"><fmt:message key="adminUserList.blockUser"/></a>
                                    </td>
                                </c:otherwise>
                            </c:choose>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>

        </div>
    </div>
</div>

<c:if test="${empty requestList}">
    <h3><fmt:message key="adminUserList.noRequestsWarning" /></h3>
</c:if>

<c:if test="${not empty requestList}">
<div class="container">
    <div class="row">
        <div class="col">

            <div class="card">
                <h4 class="card-heading text-center mt-4 mb-4"><fmt:message key="adminUserList.unlockListHeader" /></h4>

                <table class="table">
                    <thead>
                    <tr>
                        <th data-type="text"><fmt:message key="adminUserList.cardName" /></th>
                        <th data-type="integer"><fmt:message key="adminUserList.cardNumber" /></th>
                        <th data-type="double"><fmt:message key="adminUserList.cardBalance" /></th>
                    </tr>
                    </thead>

                    <tbody>
                    <c:forEach items="${requestList}" var="account" >
                        <tr>
                            <td>${account.cardName}</td>
                            <td>${account.cardNumber}</td>
                            <td>${account.cardBalance}</td>
                            <td>
                                <a href="unblockCard?cardNumber=${account.cardNumber}"><fmt:message key="adminUserList.unblockCard" /></a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>

        </div>
    </div>
</div>
</c:if>

<jsp:include page="../common/_footer.jsp"></jsp:include>
<script src="${pageContext.request.contextPath}/examples/main.js"></script>

</body>
</html>
