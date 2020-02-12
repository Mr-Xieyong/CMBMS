<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <%--<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>--%>
    <script src="https://cdn.bootcss.com/jquery/3.3.0/core.js"></script>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>登录页面</title>
</head>
<body>

<form action="/userController/login" method="post" id="loginUser">
    <div class="form-group">
        姓名：<input id="name" name="username" type="text"/>
    </div>
    <div class="form-group">
        密码：<input  type="password" name="password"/>
    </div>
    <th>验证码</th>
    <td>
        <input type="text" id="captcha" name="captcha" class="text" maxlength="10" />
        <img id="captchaImage" src="userController/captcha"/>
    </td>

    <input type="submit" value="登录"/>
</form>



<script type="text/javascript" language="javascript">
    // 更换验证码
    $('#captchaImage').click(function()
    {
        $('#captchaImage').attr("src", "captcha.form?timestamp=" + (new Date()).valueOf());
    });
</script>

</body>
</html>




<%--<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>--%>
<%--<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">--%>
<%--<html lang="en">--%>
<%--<head>--%>
    <%--<meta charset="UTF-8">--%>
    <%--<title>login</title>--%>

    <%--<script src="https://cdn.bootcss.com/jquery/2.2.4/jquery.min.js"></script>--%>
    <%--<link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">--%>
    <%--<link href="https://cdn.bootcss.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">--%>
    <%--<script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>--%>
    <%--<link rel="stylesheet" href="css/style.css">--%>


<%--</head>--%>
<%--<body>--%>
<%--<h3>/trans/file</h3>--%>
<%--<form action="/trans/file" method="post" enctype="multipart/form-data">--%>
    <%--请选择文件：<input type="file" name="file"/>--%>
    <%--<input type="submit" value="提交"/>--%>
<%--</form>--%>

<%--<h3>/filetransController/upload</h3>--%>
<%--<form action="/filetransController/upload" method="post" enctype="multipart/form-data">--%>
    <%--请选择文件：<input type="file" name="file"/>--%>
    <%--<input type="submit" value="提交"/>--%>
<%--</form>--%>



<%--<h3>/filetransController/springUpload</h3>--%>
<%--<form action="/filetransController/springUpload" method="post" enctype="multipart/form-data">--%>
    <%--请选择文件：<input type="file" name="file"/>--%>
    <%--<input type="submit" value="提交"/>--%>
<%--</form>--%>


<%--<h3>/filetransController/toFile</h3>--%>
<%--<form action="/filetransController/toFile" method="post" enctype="multipart/form-data">--%>
    <%--请选择文件：<input type="file" name="file"/>--%>
    <%--<input type="submit" value="提交"/>--%>
<%--</form>--%>




<%--<h1>文件下载</h1>--%>
<%--<a href="/filetransController/download01?filename=2016211026-谢勇-毕业实习报告.doc">下载1</a>--%>
<%--<a href="/filetransController/download01?filename=2016211026-谢勇-毕业实习报告.doc">下载2</a>--%>

<%--&lt;%&ndash;<form action="uploadUrl" method="post" enctype="multipart/form-data">&ndash;%&gt;--%>
<%--&lt;%&ndash;<input type="file" name="images" multiple="multiple" />&ndash;%&gt;--%>
<%--&lt;%&ndash;<input type="submit" value="文件上传" />&ndash;%&gt;--%>
<%--&lt;%&ndash;</form>&ndash;%&gt;--%>


<%--&lt;%&ndash;<h3>采用流的方式上传文件</h3>&ndash;%&gt;--%>
<%--&lt;%&ndash;<form action="/filetransController/springUpload" method="post" enctype="multipart/form-data">&ndash;%&gt;--%>
<%--&lt;%&ndash;<input type="file"  value="选择文件">&ndash;%&gt;--%>
<%--&lt;%&ndash;<input type="submit" value="提交">&ndash;%&gt;--%>
<%--&lt;%&ndash;</form>&ndash;%&gt;--%>

<%--</body>--%>
<%--</html>--%>