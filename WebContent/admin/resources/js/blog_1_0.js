var path = "http://localhost/ssmBlog/";
$(function(){
	$(document).on("click","#nav_blog1_0",function(){
		toblogPage2(1);
		init();
	});
	
})

function init(){
	$(".blog_add_btn").css({"display":"none"});
	$(document).off('click',".blog_add1_0_btn");
	$(document).on('click',".blog_add1_0_btn",function(){
		//弹出即全屏
		var index = layer.open({
		  type: 2,
		  title:" ",
		  content: path+'admin/addpage.html',
		  area: ['1000px', '500px'],
		  maxmin: true 
		});
		layer.full(index);
	});
}

function toblogPage2(pn){
	$.ajax({
		type:'get',
		url:path+'blog/list',
		data:{
			"pn":pn
		},
		dataType:'json',
		success:function(data){
			//console.log(data);
			curPn=pn;
			pages=data.pages;
			var blogTemplate = Handlebars.compile($("#blogTemplate").html());
			$(".container").html(blogTemplate(data));
			$(".blog_add1_0_btn").css({"display":"block"});
			$(document).on("click",".blog_add_btn",function(){
				$(".container").html("");
				var blogAddTemplate = Handlebars.compile($("#blogAddTemplate").html());
				$(".container").html(blogAddTemplate());
				ue=UE.getEditor('editor');
				if(ueRendered){
					ue.render('editor');
				}
				ueRendered=true;
//				UE.getEditor('editor').render('editor')
				showMarks(1);
				showCategory();
			});
			
			
			
			$(document).off("click",".pageNumBtn");
			$(document).on("click",".pageNumBtn",function(){
				toblogPage($(this).attr("title"));
			});
			$(document).off("click",".categorynav_STATUS_btn");
			$(document).on("click",".categorynav_STATUS_btn",function(){
				//博客修改状态
				var id=$(this).attr("id").split("-")[0];
				var status=$(this).attr("id").split("-")[1];
				$.ajax({
					type:'POST',
					url:path+"/blog/switch/"+id,
					data:{
						"status":status
					},
					dataType:'json',
					success:function(data){
						if(data==true){
							layer.msg("修改成功");
							toblogPage(curPn);
						}
						
					}
					
				});
			});
			//绑定删除按钮
			$(document).on('click',".blog_del_btn",function(){
				var titleName=$("#blogTitle").html();
				var id=$("#blogId").html();
				layer.confirm('您确定要删除'+titleName+'?', {
					  btn: ['坚决要删','再考虑考虑吧'] //按钮
					}, function(){
						//执行删除操作
						$.ajax({
							type:'GET',
							url:path+'blog/delete/'+id,
							dataType:'json',
							success:function(data){
								if(data==true){
									layer.msg("删除成功");
									toblogPage(curPn);
								}
							}
						});
					}, function(){
					  layer.msg('也可以这样', {
					    time: 20000, //20s后自动关闭
					  });
					});
			});
		}
	});
}

//获取地址栏参数查询
function GetQueryString(name){
    var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if(r!=null)return  unescape(r[2]); return null;
}