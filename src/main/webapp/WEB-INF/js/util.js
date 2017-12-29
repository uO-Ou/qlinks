//校验密码：只能输入4-20个字母、数字、下划线  
function validStr(s){  
	var patrn=/^(\w){4,20}$/;  
	if (!patrn.exec(s)) return false;
	return true;
}  
//校验email
function validEmail(email){
	var patrn = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
	if(!patrn.exec(email)) return false;
	return true;
}
//显示警告消息
function _showMessageBox(msg){
	alert(msg);
}

function wrappass(pass){
	return md5(md5(md5(pass)));
}
