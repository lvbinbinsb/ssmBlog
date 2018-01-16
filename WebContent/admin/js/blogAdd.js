var pages=1;

var markIds=[];
$(function(){
	markIds=[];
	$(document).on("click",".markPageBtn",function(){
		showMarks($(this).attr("title"));
	});
	
	$(document).on('change',".categoryNavSelect",function(){
		var cateGoryNavId=$(".categoryNavSelect :selected").val();
		//加载二级分类了
		showCategoryV(cateGoryNavId);
	});
	
	$(document).on('change',".markIdsCheckBox",function(){
		var curId=$(this).val();
		if($(this).prop("checked")==true){
			markIds.push(curId);
		}else{
			$.each(markIds,function(index,data){
				if(data==curId){
					markIds.splice(index,1);
				}
			});
		}
	})
	
	$(document).on("click",".blogAddSubmit",function() {
				//判断
				var curCheckedLen=$(".markIdsCheckBox:checked").length;
				var idArr=[],notExistArr=[];
				$.each($(".markIdsCheckBox:checked"),function(index,data){
					idArr.push($(data).val());
				});
//				console.log(idArr);
				if(curCheckedLen<=markIds.length){
//					console.log(markIds);
					$.each(markIds,function(index,result){
						for(var i=0;i<idArr.length;i++){
							if(idArr[i]==result){
								break;
							}
						}
						if(i>=idArr.length){
							//说明不存在
							notExistArr.push(result);
						}
					});
				}
				$.each(notExistArr,function(index,data){
					$("#blogFormAjax").append($('<input type="checkbox" style="opacity:0" checked=true name="blogMarkId" value="'+data+'"/>'));
				});
				var options = {
						type : 'POST',
						url : path + "addBlog",
						beforeSubmit:showRequest,
						success : function(result) {
							if (result == "true") {
								layer.msg("恭喜你，修改成功！");
								//然后跳转到博客查询列表
								toblogPage(1);
							}else{
								layer.msg("服务器繁忙 请稍后重试");
							}
						},
					};
				$("#blogFormAjax").submit(function() {
					$(this).ajaxSubmit(options); // 使用jQuery.form的方法实现ajax方式提交表单
					return false; // true 表单会正常提交出去，false可以阻止默认提交出去的行为
				});
			});
})

function showRequest(formData, jqForm, options){
   //formData: 数组对象，提交表单时，Form插件会以Ajax方式自动提交这些数据，格式如：[{name:user,value:val },{name:pwd,value:pwd}]
   //jqForm:   jQuery对象，封装了表单的元素   
   //options:  options对象
//   var formElement = jqForm[0];              //将jqForm转换为DOM对象
//   var address = formElement.address.value;  //访问jqForm的DOM元素
	var queryString = $.param(formData);   //name=1&address=2
	
   return true;  //只要不返回false，表单都会提交,在这里可以对表单元素进行验证
};


//获取mark列表
function showMarks(pn){
	$.ajax({
		type:'get',
		url:path+'showBlogMarkPage',
		data:{
			"pn":pn
		},
		dataType:'json',
		success:function(data){
//			console.log(data);
			pages=data.pages;
			var markPageTemplate = Handlebars.compile($("#markPageTemplate").html());
			$("#markPageArea").html("");
			$("#markPageArea").html(markPageTemplate(data));
			$.each($(".markIdsCheckBox"),function(index,result){
				$.each(markIds,function(index,data){
					if($(result).val()==data){
						$(result).prop("checked",true);
					}
				});
			});
		}
	});
}

function showCategory(){
	$.ajax({
		type:'get',
		url:path+'showBlogCategoryNavPage',
		dataType:'json',
		success:function(data){
//			console.log(data);
			var CategoryNavTemplate = Handlebars.compile($("#CategoryNavTemplate").html());
			$(".categoryArea").html("");
			$(".categoryArea").html(CategoryNavTemplate(data));
		}
	});
}

function showCategoryV(navId){
	$.ajax({
		type:'get',
		url:path+'showBlogCategoryVPage',
		data:{
			"navId":navId
		},
		dataType:'json',
		success:function(data){
//			console.log(data);
			//拼接第二个categoryV
			if(data.total==0){
				$(".categoryVSelect").empty().append($("<option></option>").html("暂无下级分类"));
			}else{
				$(".categoryVSelect").empty();
				$.each(data.list,function(index,data){
					$("<option></option>").val(data.categoryvId).html(data.categoryvName).appendTo($(".categoryVSelect"));
				});
			}
			
		}
	});
}
