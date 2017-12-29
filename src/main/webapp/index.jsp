<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Qlinks</title>

<script src="<%=request.getContextPath()%>/js/jquery-3.2.1.min.js"></script>
<script	src="<%=request.getContextPath()%>/js/bootstrap/bootstrap.min.js"></script>
<script src="http://cdn.bootcss.com/blueimp-md5/1.1.0/js/md5.min.js"></script>
<script src="<%=request.getContextPath()%>/js/util.js"></script>
<script src="<%=request.getContextPath()%>/js/index.js"></script>

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bootstrap/bootstrap.min.css"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/flatui/flat-ui.css"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/flatui/demo.css"/>

<link rel="shortcut icon" href="<%=request.getContextPath()%>/img/bacground/favicon.ico" type="image/x-icon" />
<style>
body{
   background : url(<%=request.getContextPath()%>/img/index_back.jpg) no-repeat center center fixed;
}
</style>
<input type="hidden" id="ctx" value="<%=request.getContextPath()%>" />
<input type="hidden" id="try_name" value="<%=request.getParameter("name")%>" />
<input type="hidden" id="authcode_hidden" value="<%=request.getParameter("authcode")%>" />
</head>
<body>
    <div class="container login-div">
	      <br/> <br/> <br/> <br/> <br/> <br/> <br/> <br/> 
	      
	      <div id="login-div" class="login-form col-md-4 col-md-offset-4">
            <div class="form-group">
              <input type="text" class="form-control login-field" value="" placeholder="Enter your name" id="login-name" />
              <label class="login-field-icon fui-user" for="login-name"></label>
            </div>
            
            <div class="form-group">
              <input type="password" class="form-control login-field" value="" placeholder="Password" id="login-pass" />
              <label class="login-field-icon fui-lock" for="login-pass"></label>
            </div>

            <a id="login_btn"class="btn btn-primary btn-lg btn-block">Log in</a>
            <a id="forget_btn" class="login-link">Lost your password?</a>
            <a id="toggle2regist_btn" class="login-link" >Have no accounts? Register!</a>
          </div>
          
          
          <div id="regist-div" class="login-form col-md-4 col-md-offset-4" style="display:none;">
            <div class="form-group">
              <input id="regist-name" type="text" class="form-control login-field" value="" placeholder="Username"/>
              <label class="login-field-icon fui-user" for="login-name"></label>
            </div>
            
            <div class="form-group">
              <input id="regist-pass" type="password" class="form-control login-field" value="" placeholder="Password" />
              <label class="login-field-icon fui-lock" for="login-pass"></label>
            </div>

            <div class="form-group">
              <input id="regist-mail" type="text" class="form-control login-field" value="" placeholder="Mail" />
              <label class="login-field-icon fui-mail" for="login-name"></label>
            </div>

            <a id="regist_btn"class="btn btn-primary btn-lg btn-block">Register</a>
            <a id="toggle2login_btn" class="login-link" >Already have one account</a>
          </div>
          
    </div> <!-- /container -->
</body>
</html>