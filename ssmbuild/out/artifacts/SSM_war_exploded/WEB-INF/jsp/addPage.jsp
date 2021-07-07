<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>书籍添加页面</title>
    <!-- 新 Bootstrap 核心 CSS 文件 -->
    <link href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <div class="page-header">
                <h2>
                    <small>新增书籍</small>
                </h2>
            </div>
        </div>
    </div>

    <form action="${pageContext.request.contextPath}/book/addBook" method="post">
        <div class="form-group">
            <label for="bkID">书籍编号</label>
            <input type="text" name="bookID" class="form-control" id="bkID" placeholder="BookID" required>
        </div>
        <div class="form-group">
            <label for="bkName">书籍名称</label>
            <input type="text" name="bookName" class="form-control" id="bkName" placeholder="BookName" required>
        </div>
        <div class="form-group">
            <label for="bkCount">书籍数量</label>
            <input type="text" name="bookCounts" class="form-control" id="bkCount" placeholder="BookName" required>
        </div>
        <div class="form-group">
            <label for="bkDetail">书籍描述</label>
            <input type="text" name="detail" class="form-control" id="bkDetail" placeholder="BookName" required>
        </div>
        <button type="submit" class="btn btn-default">提交</button>
    </form>


</div>
</body>
</html>
