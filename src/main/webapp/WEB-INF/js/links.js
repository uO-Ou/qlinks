var ctx_global;
var tagIdMap_global = {};  // <tagname,tagId>
var linkInfo_global = {}; // <linkid,linkInfo>
var tagSize_global = {};  // <tagname,linkcnt>
var curLinkId_global;
var CELL_IMG_CNT = 50;

//parse date
function parseDate(date){
    var now = new Date(date);
    var year = now.getFullYear();     
    var month = now.getMonth()+1; if(month<10) month="0"+month;
    var date = now.getDate();     if(date<10) date="0"+date;
    var hour = now.getHours();     
    var minute = now.getMinutes();     
    var second = now.getSeconds();     
    return year+"-"+month+"-"+date+"   "+hour+":"+minute+":"+second;     
};

//add a link to type(tag)
function addLinkToTag(tag){
	var url = $("#url_edit").val();
	var hint = $("#hint_edit").val();
	
	if(url.length<1) return ;
	if(hint.length<1) hint = url;
	
	$.ajax({
		url : ctx_global+"/addLink",
		dataType: 'json',
		async : false,
		type:'post',
		data:{
			url : url,
			hint : hint,
			tag : tag
		},
		success:function(data){
			if(data!=null){
				
				if(tagSize_global[tag]==0)
					$("#tag_list").prepend('<li role="presentation"><a role="menuitem" tabindex="-1" href="JavaScript:addLinkToTag(\''+tag+'\')">'+tag+'</a></li>');
				
				linkInfo_global[data.id]=data;
				$("#"+tagIdMap_global[tag]).append(packlink(data));
				tagSize_global[tag]++;
			} 
		},
		error:function(){
			alert("add url failed");
		}
	});
};

//when user click a link
function hits(url,id){
	window.open(url);
	
	$.ajax({
		url : ctx_global+"/incHit",
		dataType: 'text',
		async : false,
		type:'post',
		data:{
			id:id
		},
		success:function(data){
			if(data=="success"){
				linkInfo_global[id].hitCnt++;
				$("#hitCnt_"+id).html(linkInfo_global[id].hitCnt);
			}
		},
		error:function(){
			alert("error");
		}
	});
};

//edit link info
function edit(id){
	
	curLinkId_global = ''+id;
	
	$("#info_header").html("create time:["+parseDate(linkInfo_global[curLinkId_global].birth)+"] hitted ["+linkInfo_global[curLinkId_global].hitCnt+"] times");

	$("#info_url").val(linkInfo_global[curLinkId_global].url);
	
	$("#info_hint").val(linkInfo_global[curLinkId_global].hint);
	
	if(linkInfo_global[curLinkId_global].helpInfo.length>0){
		$("#info_helpinfo").val(linkInfo_global[curLinkId_global].helpInfo);
	}
	else{
		$("#info_helpinfo").val("");
	}
	
	$('#linkModal').modal({
		keyboard : true
	});
};

function remove(id){
	$.ajax({
		url : ctx_global+"/deleteLink",
		dataType: 'text',
		async : false,
		type:'post',
		data:{
			id : id
		},
		success:function(data){
			if(data=="success"){
				var tagname = linkInfo_global[id].tag;
				tagSize_global[tagname]--;
				if(tagSize_global[tagname]<=0){
					$("#tfather_"+tagIdMap_global[tagname]).remove();
				}
				else $("#li_"+id).remove();
			} 
		},
		error:function(){
			alert("delete url failed");
		}
	});
};

function packtag(tag){
	return ('<div class="col-md-3 col-sm-12 white" id="tfather_'+tagIdMap_global[tag]+'">' +
	          '<br/>' +
	          '<div class="panel panel-default inner_tag" id="cell_'+tagIdMap_global[tag]+'">' +
	               '<ul class="list-group transparent" id="' + tagIdMap_global[tag] + '">' +
		               '<li role="presentation" class="li-head-font">'+ tag+ '</li>' +
	               '</ul>' +
	          '</div>' +
	        '</div>');
};

function packlink(link){
	return ('<li id="li_'+link.id+'" class="list-group-item transparent">' +
	            '<a class="tooltip-test" id="a_'+link.id+'" href="javascript:hits(\''+link.url+'\','+link.id+')" title="'+link.hint+"\n"+link.url+"\n"+link.helpInfo+'">' +
	                   link.hint.substring(0,8) + '[<label id="hitCnt_'+link.id+'">' + link.hitCnt + '</label>]' +
	            '</a>' +
	            '<div class="pull-right">' +
	                 '<a href="javascript:edit('+link.id+')"><span class="glyphicon glyphicon-edit"/></a>' +
	                 '<span>    </span>' +
	                 '<a href="javascript:remove('+link.id+')"><span class="glyphicon glyphicon-trash"/></a>' +
	            '</div>' +
	        '</li>');
};

//initialize
$(document).ready(function(){
	ctx_global = $("#ctx").val();
	var label_hidden = $("#label_hidden").val();
	$.get({
		url : ctx_global+"/getTags",
		async : false,
		dataType : 'json',
		success : function(data){
			if(label_hidden=="resetpassword"){
				$("#resetPassModal").modal({
					keyboard:true
				});
			}
			if(data.length<1) return ;
			
			//initialize tags-drop-down-list
			var tags = "";
			data.forEach(function(value,index,array){
			     tags += '<li role="presentation"><a role="menuitem" tabindex="-1" href="JavaScript:addLinkToTag(\''+value+'\')">'+value+'</a></li>';	
			     tagIdMap_global[value] = "tag_"+index;
			     tagSize_global[value] = 0;
			});
			$("#tag_list").prepend(tags);
			
			//initialize tags-panel
		    for (var tagname in tagIdMap_global){
		    	$("#tags").append(packtag(tagname));
		    	var pid = Math.floor(Math.random()*CELL_IMG_CNT);
		    	if(pid<10) pid="/img/cell/00"+pid+".png) center 0";
		    	else pid = "/img/cell/0"+pid+".png) center 0";
		    	$("#cell_"+tagIdMap_global[tagname]).css('background','url('+ctx_global+pid);
		    }
			
		    //add links
			$.get({
				url : ctx_global+"/getLinks",
				async : false,
				cache : false,
				dataType : 'json',
				success : function(data){
					data.forEach(function(value,index,array){
						linkInfo_global[value.id]=value;
						var tagId = "#"+tagIdMap_global[value.tag];
						$(tagId).append(packlink(value));
						tagSize_global[value.tag]++;
					});
				},
				error:function(){
					alert("get urls failed");
				}
			});
			
		},
		error:function(){
			alert("get tags failed");
		}
	});
	
	//show new tag modal
	$("#addTag").click(function(){
		$('#tagModal').modal({
			keyboard:true
		});
	});
	
	//call addLinkToTag
	$("#newUrlBtn").click(function(){
		$('#tagModal').modal('hide');
		var tagname = $("#tag_edit").val();
		tagSize_global[tagname]=0;
		tagIdMap_global[tagname]="tag_"+new Date().getTime();
		
		//gen cell pic
		var pid = Math.floor(Math.random()*CELL_IMG_CNT);
    	if(pid<10) pid="/img/cell/00"+pid+".png) center 0";
    	else pid = "/img/cell/0"+pid+".png) center 0";
		
		$("#tags").append(packtag(tagname));
		$("#cell_"+tagIdMap_global[tagname]).css('background','url('+ctx_global+pid);
		addLinkToTag(tagname);
	});
	
	//show linkinfo modal
    $('#linkModal').on('show.bs.modal', function(e) {  
        $(this).css('display', 'block');  
        var modalHeight=$(window).height() * 0.3 - $('#tagModal .modal-dialog').height() / 2;  
        $(this).find('.modal-dialog').css({  
            'margin-top': modalHeight  
        });  
    });  
    
    //保存对url的修改
    $("#saveUrlBtn").click(function(){
    	
    	var url = $("#info_url").val();
    	var hint = $("#info_hint").val();
    	var helpInfo = $("#info_helpinfo").val();
    	
    	$('#linkModal').modal('hide');
    	
    	if(url!=linkInfo_global[curLinkId_global].url||hint!=linkInfo_global[curLinkId_global].hint||helpInfo!=linkInfo_global[curLinkId_global].helpInfo){
    		if(url.length<1) return ;
    		if(hint.length<1) hint = url;
    		
    		$.ajax({
    			url : ctx_global + "/modifyLink",
    			dataType: 'text',
    			async : false,
    			type:'post',
    			data:{
    				id : curLinkId_global,
    				url : url,
    				hint : hint,
    				helpInfo : helpInfo
    			},
    			success:function(data){
    				if(data=="success"){
    					if(url!=linkInfo_global[curLinkId_global].url) {
    						$("#a_"+curLinkId_global).attr("href",'javascript:hits(\''+url+'\','+curLinkId_global+')');
    						linkInfo_global[curLinkId_global].url = url;
    					}
    					if(hint!=linkInfo_global[curLinkId_global].hint) {
    						$("#a_"+curLinkId_global).html(
    							hint.substring(0,8) + '[<label id="hitCnt_'+curLinkId_global+'">' + linkInfo_global[curLinkId_global].hitCnt + '</label>]'		
    						);
    						linkInfo_global[curLinkId_global].hint = hint;
    					}
    					else linkInfo_global[curLinkId_global].helpInfo = helpInfo;
    					
    					$("#a_"+curLinkId_global).attr("title",hint+"\n"+url+"\n"+helpInfo);
    				}
    			},
    			error:function(){
    				alert("error");
    			}
    		});
    	}
    });
    
    //重置密码按钮
    $("#resetPassButton").click(function(){
    	var pass1 = $("#reset-pass-1").val();
    	var pass2 = $("#reset-pass-2").val();
    	if(pass1!=pass2){
    		_showMessageBox("两次输入密码不一致"); return ;
    	}
    	else if(pass1.length<6){
    		_showMessageBox("密码长度少于6位"); return false;
    	}
    	else if(validStr(pass1)==false){
    		_showMessageBox("密码只能包含6位以上(字母、数字和_)"); return false;
    	}
    	$('#resetPassModal').modal('hide');
    	$.ajax({
			url : ctx_global + "/updatepass",
			dataType: 'text',
			async : false,
			type : 'post',
			data:{
				newPass : wrappass(pass1)
			},
			success:function(data){
			},
			error:function(){
			}
	    });
    });
    
	$("#album_img,#people_img,#info_img,#home_img").hover(
	     function(){
		      $(this).stop().animate({ height: 122, width: 122, left: "-15px", top: "-15px" },200);
		      
		 }, 
		 function(){
		      $(this).stop().animate({ height: 92, width: 92, left: "0px", top: "0px" },200);
		 }
    );    
});