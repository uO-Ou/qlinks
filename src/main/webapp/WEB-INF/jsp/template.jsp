<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Qlinks</title>

<script src="<%=request.getContextPath()%>/js/jquery-3.2.1.min.js"></script>
<script	src="<%=request.getContextPath()%>/js/bootstrap/bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bootstrap/bootstrap.min.css"/>

<link rel="shortcut icon" href="<%=request.getContextPath()%>/img/bacground/favicon.ico" type="image/x-icon" />

<input type="hidden" id="ctx" value="<%=request.getContextPath()%>" />
</head>
<body>
    <!-- back-ground image -->
    <div style="position:absolute; width:100%; height:100%; z-index:-1">    
      <img src="<%=request.getContextPath()%>/img/background/dark.png" height="100%" width="100%"/>    
    </div> 
</body>
</html>