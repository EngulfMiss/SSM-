<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>数据展示</title>
    <!-- 新 Bootstrap 核心 CSS 文件 -->
    <link href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <h1>数据展示</h1>
    <div class="container">
        <div class="row clearfix">
            <div class="col-md-12 column">
                <div class="page-header">
                    <h2>
                        <small>书籍列表 ------- 显示所有书籍</small>
                    </h2>
                </div>
            </div>

            <div class="row">
                <div class="col-md-3 column">
                    <%-- toAddBook --%>
                    <a class="btn btn-primary" href="${pageContext.request.contextPath}/book/toAddBook">添加书籍</a>
                </div>
                <div class="col-md-4 column">
                    <%-- 查询书籍 --%>
                    <form action="${pageContext.request.contextPath}/book/queryBook" method="post" class="form-inline" style="float: right">
                        <input type="text" name="queryBookName" class="form-control" placeholder="请输入要查询的书籍名称">
                        <input type="submit" value="查询" class="btn btn-warning">
                    </form>
                </div>
            </div>
        </div>


        <div class="row clearfix">
            <div class="col-md-12 column">
                <table class="table table-hover table-striped">
                    <thead>
                        <tr>
                            <th>书籍编号</th>
                            <th>书籍名称</th>
                            <th>书籍数量</th>
                            <th>书籍描述</th>
                            <th>操作</th>
                        </tr>
                    </thead>

                    <%-- 书籍从数据库中查询出来,从booklist中遍历出来：foreach --%>
                    <tbody>
                        <c:forEach var="book" items="${booklist}">
                            <tr>
                                <td>${book.bookID}</td>
                                <td>${book.bookName}</td>
                                <td>${book.bookCounts}</td>
                                <td>${book.detail}</td>
                                <td>
                                    <a class="btn btn-info" href="${pageContext.request.contextPath}/book/toUpdateBook/${book.bookID}">修改</a>
                                    &nbsp; | &nbsp;
                                    <a class="btn btn-danger" href="${pageContext.request.contextPath}/book/delBook?bookId=${book.bookID}">删除</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>


    </div>
</body>
</html>
