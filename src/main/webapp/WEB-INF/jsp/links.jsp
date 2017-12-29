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
<script src="<%=request.getContextPath()%>/js/links.js"></script>

<link rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap/bootstrap.css"/>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/links.css"/>

<link rel="shortcut icon" href="<%=request.getContextPath()%>/img/background/favicon.ico" type="image/x-icon" />

<input type="hidden" id="ctx" value="<%=request.getContextPath()%>" />
<input type="hidden" id="label_hidden" value="<%= request.getAttribute("label")%>"/>
</head>
<body>
    <!-- head -->
	<br/> <br/>
	<div class="col-lg-6 col-lg-offset-3">
	     <div class="row">
				<div class="form-inline row">
					<input type="text" class="form-control" id="url_edit" placeholder="http://www.redips.xin/qlinks" style="width:60%;margin-right:2px" /> 
					<input type="text" class="form-control" id="hint_edit" placeholder="Qlinks" style="width:20%;margin-right:2px" /> 
					<div class="form-group input-group-btn" style="width:18%;">
				          <button type="button" class="btn btn-success dropdown-toggle" data-toggle="dropdown">添加链接至</button>
				          <ul class="dropdown-menu" id="tag_list">
					           <li role="presentation" class="divider"></li>
					           <li role="presentation">
					                <a style="cursor:pointer;" id="addTag" role="menuitem" tabindex="-1">新建分类</a>
					           </li>
				          </ul>
			        </div>
				</div>
		 </div><!-- /.row -->
	</div>
	<br/> <br/> <br/><br/> <br/> <br/>


	<div class="container-fluid row col-md-12">
		<!-- panels -->
		<div class="col-md-offset-1" id="tags"></div>

		<!-- left control -->
		<div class="col-md-offset-11 right-container">
			<div class="content">
				<img id="people_img" src="<%=request.getContextPath()%>/img/right/people.png" />
			</div>
			<div class="content">
				<img id="album_img" src="<%=request.getContextPath()%>/img/right/album.png" />
			</div>
			<div class="content">
				<img id="home_img" src="<%=request.getContextPath()%>/img/right/home.png" />
			</div>
		</div> 
	</div>

	<!-- new tag modal -->
	<div class="modal fade" id="tagModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel1" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel1">新建标签</h4>
				</div>
				<div class="modal-body">
				<input type="text" class="form-control" id="tag_edit" placeholder="tagname" name="url" />
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button id = "newUrlBtn" type="button" class="btn btn-primary">添加</button>
				</div>
			</div>
		</div>
	</div>
	
	<!-- link info modal -->
	<div class="modal fade" id="linkModal" tabindex="-1" role="dialog" aria-labelledby="info_header" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					<h5 class="modal-title" id="info_header" style="color:green;"></h5>
				</div>
				<div class="modal-body"> 
					<div class="form-group">
						<input type="text" class="form-control" id="info_url" /> <br /> 
						<input type="text" class="form-control" id="info_hint" /> <br /> 
						<input type="text" class="form-control" id="info_helpinfo" placeholder="添加此网站的账号、密码提示信息" />
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button id="saveUrlBtn" type="button" class="btn btn-success">保存</button>
				</div>
			</div>
		</div>
	</div>
	
	<!-- reset password modal -->
	<div class="modal fade" id="resetPassModal" tabindex="-1" role="dialog" aria-labelledby="info_header" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					<h5 class="modal-title" id="info_header" style="color:green;">重置密码</h5>
				</div>
				<div class="modal-body"> 
					<div class="form-group">
						<input type="password" class="form-control login-field" value="" placeholder="Password" id="reset-pass-1" />
                         <label class="login-field-icon fui-lock" for="login-pass"></label>
                         
                         <input type="password" class="form-control login-field" value="" placeholder="Password" id="reset-pass-2" />
                         <label class="login-field-icon fui-lock" for="login-pass"></label>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button id="resetPassButton" type="button" class="btn btn-success">更新密码</button>
				</div>
			</div>
		</div>
	</div>
</body>
</html>