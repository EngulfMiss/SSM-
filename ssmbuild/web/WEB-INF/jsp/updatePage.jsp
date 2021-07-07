<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <!-- 新 Bootstrap 核心 CSS 文件 -->
    <link href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <div class="page-header">
                <h2>
                    <small>修改书籍</small>
                </h2>
            </div>
        </div>
    </div>

    <form action="${pageContext.request.contextPath}/book/updateBook" method="post">
        <div class="form-group hidden">
            <label for="bkID">书籍编号</label>
            <input type="text" name="bookID" class="form-control" id="bkID" value="${SBook.bookID}" required>
        </div>
        <div class="form-group">
            <label for="bkName">书籍名称</label>
            <input type="text" name="bookName" class="form-control" id="bkName" value="${SBook.bookName}" required>
        </div>
        <div class="form-group">
            <label for="bkCount">书籍数量</label>
            <input type="text" name="bookCounts" class="form-control" id="bkCount" value="${SBook.bookCounts}" required>
        </div>
        <div class="form-group">
            <label for="bkDetail">书籍描述</label>
            <input type="text" name="detail" class="form-control" id="bkDetail" value="${SBook.detail}" required>
        </div>
        <button type="submit" class="btn btn-default">提交</button>
    </form>


</div>
</body>
</html>
