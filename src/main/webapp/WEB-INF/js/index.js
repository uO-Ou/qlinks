var ctx_global;

function validation(_name,_pass,_mail){
	if(_name.length<1){
		_showMessageBox("请输入用户名");return false;
    }
	else if(validStr(_name)==false){
		_showMessageBox("用户名只能包含4位以上(字母、数字和_)"); return false;
	}
	else if(_pass.length<6){
		_showMessageBox("密码长度少于6位"); return false;
    }
	else if(validStr(_pass)==false){
		_showMessageBox("密码只能包含6位以上(字母、数字和_)"); return false;
	}
	else if(_mail.length<1){
		_showMessageBox("请输入邮箱,用于密码找回");return false;
    }
	else if(validEmail(_mail)==false){
		_showMessageBox("请输入一个合理的邮箱地址");return false;
	}
	return true;
}

$(document).ready(function(){
	ctx_global = $("#ctx").val();
	if($("#try_name").val()!='null'){
		$("#login-name").val($("#try_name").val());
	}
	authcode_hidden = $("#authcode_hidden").val();
	if(authcode_hidden!='null'){
		if(authcode_hidden==1) _showMessageBox("重置密码链接已失效");
		else if(authcode_hidden==2) _showMessageBox("重置密码授权码不存在");
	}
	$("#login_btn").click(function(){
  		var name = $("#login-name").val();
		var pass = $("#login-pass").val();
		if(name.length<1||pass.length<1){
	    	_showMessageBox("请输入用户名和密码");return;
	    }
		else{
        	$.ajax({
        		url : ctx_global+"/login/"+name,
        		dataType : 'text',
        		async : false,
        		type:'post',
        		data:{
        			pass:wrappass(pass)
        		},
        		success:function(data){
        			if(data=="success"){
        				window.location="user/"+name;
        			}
        			else{
        				_showMessageBox("用户名或者密码错误");
        			}
        		}
        	});
        }
	});
	//注册
	$("#regist_btn").click(function(){
  		var name = $("#regist-name").val();
		var pass = $("#regist-pass").val();
		var mail = $("#regist-mail").val();
		if(validation(name,pass,mail)==true){
        	$.ajax({
        		url : ctx_global+"/regist",
        		dataType : 'text',
        		async : false,
        		type:'post',
        		data:{
        			name:name,
        			pass : wrappass(pass),
        			mail : mail
        		},
        		success:function(data){
        			if(data=="success"){
        				window.location="user/"+name;
        			}
        			else if(data=="mail_exists"){
        				_showMessageBox("邮箱已被占用");
        			}
        			else if(data=="user_exists"){
        				_showMessageBox("用户名已被占用");
        			}
        		}
        	});
        }
	});
	//忘记密码按钮
	$("#forget_btn").click(function(){
		var name = $("#login-name").val();
		if(name.length<1){
			_showMessageBox("请先输入您的用户名");return false;
	    }
		_showMessageBox("系统在几分钟内会向您的注册邮箱发送一个重置密码的链接，请于3日内点击链接重置密码"); 
		$.ajax({
    		url : ctx_global+"/forgetPassword/"+name,
    		dataType : 'text',
    		async : true,
    		type:'post',
    		success:function(data){
    			  if(data=="ok"){
    				  
    			  }
    			  else if(data=="null"){
    				  _showMessageBox("对不起,系统不存在名为"+name+"的用户"); return ;
    			  }
    		},
			error : function(){
				_showMessageBox("对不起,请联系管理员www.redips.xin@foxmail.com,遇到了一只bug"); return;
			}
    	});
	});
	
	$("#toggle2regist_btn").click(function(){
  		$("#login-div").hide(500);
  		$("#regist-div").show(500);
	});
	
	$("#toggle2login_btn").click(function(){
		$("#regist-div").hide(500);
		$("#login-div").show(500);
	});
});
