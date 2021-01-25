# SSM-
整合SSM

# 前端控制器完成跳转后的页面无法加载css文件的解决方案
```
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>透明登录页面</title>
    <%
        String path = request.getContextPath();
    %>    <link rel="stylesheet" href=<%=path %>/css/style.css type="text/css">

</head>
```
