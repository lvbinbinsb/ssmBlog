var path="http://localhost/ssmBlog/";


function getCurUserName(){
	$.ajax({
		type:'post',
		url:path+"/getCurUserName",
		dataType:'json',
		success:function(data){
			console.log(data);
			if(data.code==200){
				$("#userSpan").text(data.result.curUserName);
			}else if(data.code==100){
				layer.msg("服务器繁忙 请稍后再试");
			}
		}
	});
}

var options;
$("#resetPwd").click(function(){
	$('#changePwdModal').modal(options);
});
var flag=false;
$("#userOldPwdInput").blur(function(){
	checkOldPwd($(this).val());
});

$(document).on('click',"#submitPwdBtn",function(){
	//
	if(flag&&easyValidate()){
		//校验成功
		changePwd($("#userNewPwdInput").val());
	}
});

$("#logoutBtn").click(function(){
	window.location.href=path+"logout";
});

function easyValidate(){
	if($("#userNewPwdInput").val()==""){
		layer.msg("密码不能为空");
	}else if($("#userNewPwdInput").val()==$("#userOldPwdInput").val()){
		layer.msg("两次密码不能一样");
	}else{
		return true;
	}
}

function changePwd(newPwd){
	var userName=$("#userSpan").text();
	$.ajax({
		type:'POST',
		url:path+'changePwd',
		data:{
			"userPassword":newPwd,
			"userName":userName
		},
		dataType:'json',
		success:function(data){
			if(data.code==200){
				layer.msg("修改成功");
				$('#myModal').modal('hide');
			}else if(data.code==100){
				layer.msg("sorry 修改失败  稍后重试");
				$('#myModal').modal('hide');
			}
		}
	});
}

function checkOldPwd(oldPwd){
	var userName=$("#userSpan").text();
	//校验原始密码是否正确
	$.ajax({
		type:'POST',
		url:path+'checkOldPwd',
		dataType:'json',
		data:{
			"userPassword":oldPwd,
			"userName":userName
		},
		success:function(data){
			console.log(data);
			if(data.code==200){
				flag=true;
			}else if(data.code==100){
				flag=false;
			}
		}
	});
}

$(document).on('click',"#cancelPwdBtn",function(){
	$('#changePwdModal').modal('hide');
});

$("#logoutBtn").click(function(){
	window.location.href=path+"logout";
});

$(function(){
	getCurUserName();
	
	
});