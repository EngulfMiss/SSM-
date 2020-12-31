<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 2020/12/28
  Time: 10:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h3> 查询了所有账户信息 </h3>


    <c:forEach items="${AccountList}" var="account">
        ${account.name}
        ${account.money}<br>
    </c:forEach>



</body>
</html>
