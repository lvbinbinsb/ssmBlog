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

$("#logoutBtn").click(function(){
	window.location.href=path+"logout";
});

$(function(){
	getCurUserName();
	
	
});