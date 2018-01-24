var path = "http://localhost/ssmBlog/";

var curPn = 1, pages = 1;
var ueRendered=false;

$(function() {
	Handlebars.registerHelper("format_comment_content_ctime_helper",
			function(v) {
				var date = new Date(v);
				var m = date.getMonth() + 1;
				if (m < 10) {
					m = "0" + m;
				}
				var d = date.getDay();
				if (d < 10) {
					d = "0" + d;
				}
				var h = date.getHours();
				if (h < 10) {
					h = "0" + h;
				}
				var mm = date.getMinutes();
				if (mm < 10) {
					mm = "0" + mm;
				}
				var s = date.getSeconds();
				if (s < 10) {
					s = "0" + s;
				}
				return date.getFullYear() + "/" + m + "/" + d + " " + h + ":"
						+ mm + ":" + s;
			});

	Handlebars.registerHelper("myCount", function(index) {
		return index + 1;
	});

	Handlebars.registerHelper("nextPageNum", function(index) {
		////console.log();
		if (index == pages) {
			return pages;
		} else {
			return index + 1;
		}
	});

	Handlebars.registerHelper("beforePageNum", function(index) {
		if (index == 1) {
			return 1;
		} else {
			return index - 1;
		}
	});

	Handlebars.registerHelper("fmtStatus", function(data) {
		if (data == '0') {
			return "已禁用";
		} else if (data == '1') {
			return "已启用";
		} else {
			return "数据无效";
		}
	});

	Handlebars.registerHelper("SwitchStatus", function(data) {
		if (data == '0') {
			return "启用";
		} else if (data == '1') {
			return "禁用";
		} else {
			return "启用";
		}
	});

	$("#nav_site").on("click", function() {
		$.ajax({
			type : "get",
			url : path + "global/1",
			data : {
				"global_status" : 1
			},
			success : function(str) {
				// alert(str);
				var obj = eval("(" + str + ")");
				// 编译模板
				var global_ht = Handlebars.compile($("#global_ht").html());
				// 模板渲染数据
				var _html = global_ht(obj);
				// dom操作
				$(".container").empty();
				$(".container").append(_html);
			},
			error : function() {
				layer.msg("服务器有异常，请稍后再试！");
			}
		});
	});

	$(document)
			.on(
					"click",
					"#global_submit",
					function() {
						var options = {
							type : 'POST',
							url : path + "global/update",
							success : function(result) {
								if (result == "true") {
									layer.msg("恭喜你，修改成功！");
									setTimeout(
											"window.location.href=window.location.href;",
											1000)
								}else{
									window.location.href=path+"errorPage/401/401.html";
								}
							},
						};
						$("#global_form").submit(function() {
							$(this).ajaxSubmit(options); // 使用jQuery.form的方法实现ajax方式提交表单
							return false; // true 表单会正常提交出去，false可以阻止默认提交出去的行为
						});
					});
	$(document).on(
			"click",
			"#global_reset",
			function() {
				$(':input', '#global_form').not(
						':button, :submit, :reset, :hidden').val('')
			});

	/**
	 * 一级分类管理的思路： 点击管理-->查询所有的一级分类数据并展示（table）在页面 可以删除（关联二级分类） 可以修改
	 * 可以继续添加一级分类（layer弹窗） 确认提交后，重新查询数据并展示
	 */

	$("#nav_categorynav").on("click", function() {
		to_page(1);
	});

	// 一级分类添加按钮
	$(document).on('click', ".categorynav_add_btn", function() {
		layer.prompt(function(value, index, elem) {
			$.ajax({
				type : 'post',
				url : path + 'categoryNav/add',
				data : {
					"categorynavName" : value
				},
				success : function(str) {
					if (str == "true") {
						layer.close(index);
						// //重新查询所有的数据并展示在当前页面中
						$("#nav_categorynav").trigger("click");
					}
				}
			});
		});
	});

	$(document).on('click', ".categorynav_STATUS_btn", function() {
		var curId = $(this).attr("id").split("-")[0];
		var status = $(this).attr("id").split("-")[1];
		$.ajax({
			type : 'post',
			url : path + 'categoryNav/switchstatus',
			data : {
				"categorynavId" : curId,
				"categorynavStatus" : status
			},
			success : function(str) {
				if (str == "true") {
					// //重新查询所有的数据并展示在当前页面中
					$("#nav_categorynav").trigger("click");
				}
			}
		});
	});

	$(document).on('click', ".categorynav_del_btn", function() {
		$.ajax({
			type : 'GET',
			url : path + 'categoryNav/del/' + $(this).attr("id"),
			success : function(str) {
				if (str == "true") {
					layer.msg("删除成功");
					setTimeout('$("#nav_categorynav").trigger("click")', 1000);
				}
			}
		});
	});

	$(document)
			.on(
					'click',
					".categorynav_edit_btn",
					function() {
						var categoryName = $(this).parents("tr").find(
								"td:eq(3)").html();
						var categoryId = $(this).attr("id");
						// 修改数据
						layer
								.prompt(
										{
											"value" : categoryName
										},
										function(value, index, elem) {

											$
													.ajax({
														type : 'post',
														url : path
																+ 'categoryNav/editCategory',
														data : {
															"categorynavName" : value,
															"categorynavId" : categoryId
														},
														success : function(str) {
															if (str == "true") {
																layer.close(index);
																layer.msg("修改成功");
																// //重新查询所有的数据并展示在当前页面中
																setTimeout(
																		'$("#nav_categorynav").trigger("click")',
																		1000);
															}
														}
													});
										});
					});

	$(document).on('click', ".pageNumBtn", function() {

		var pageNum = parseInt($(this).attr("title"));
		if (curPn == 1 && pageNum == 1) {
			layer.msg("当前已经是第一页");
		} else if (curPn == pages && pageNum == pages) {
			layer.msg("当前已经是最后一页");
		} else {
			to_page(pageNum);
		}
	});

	$(document).on('click', "#nav_mark", function() {
		// 触发加载mark事件
		to_markPage(1);
	});

	
	$(document).on('click','.blogmark_edit_btn',function(){
		var markId=$(this).attr("id");
		var name=$(this).parents("tr").children("td:eq(2)").text();
		
		layer.prompt({"value":name},function(value, index, elem) {
			$.ajax({
				type : 'post',
				url : path + 'mark/edit',
				data : {
					"markName" : value,
					"markId":markId
				},
				success : function(str) {
					if (str == "true") {
						layer.close(index);
						// //重新查询所有的数据并展示在当前页面中
						$("#nav_mark").trigger("click");
					}
				}
			});
		});
	});
	
	// 标签添加栏
	$(document).on('click', ".blogmark_add_btn", function() {
		layer.prompt(function(value, index, elem) {
			$.ajax({
				type : 'post',
				url : path + 'mark/add',
				data : {
					"markName" : value
				},
				success : function(str) {
					if (str == "true") {
						layer.close(index);
						// //重新查询所有的数据并展示在当前页面中
						$("#nav_mark").trigger("click");
					}
				}
			});
		});
	});

	// 删除标签功能
	$(document).on('click', ".blogmark_del_btn", function() {
		var curMarkId = $(this).attr("id");
		$.ajax({
			type : 'post',
			url : path + 'mark/delete',
			data : {
				"markId" : curMarkId
			},
			success : function(str) {
				if (str == "true") {
					layer.msg("删除成功");
					setTimeout('$("#nav_mark").trigger("click")', 1000);
					// //重新查询所有的数据并展示在当前页面中
				}
			}
		});
	});

	// 修改博客标签状态栏
	$(document).on('click', ".blogmark_STATUS_btn", function() {
		var curId = $(this).attr("id").split("-")[0];
		var status = $(this).attr("id").split("-")[1];
		$.ajax({
			type : 'post',
			url : path + 'mark/switchstatus',
			data : {
				"markId" : curId,
				"markStatus" : status
			},
			success : function(str) {
				if (str == "true") {
					// //重新查询所有的数据并展示在当前页面中
					$("#nav_mark").trigger("click");
				}
			}
		});
	});

	$(document).on('click', ".markpageNumBtn", function() {
		to_markPage($(this).attr("title"));
	});

	/**
	 * 二级分类管理 参考一级分类的基础上做管理 先要知道选择为哪一个一级分类添加二级分类 ex：省 市 区 街道
	 */
	$(document)
			.on(
					"click",
					'#nav_categoryv',
					function() {
						var _html = '<div class="categoryv_list" style="width:90%"><h2>二级分类管理</h2><p style="text-indent:4em"><select class="change_nav"><option>请选择一级分类名称：</option></select></p><table width="100%" border="1"><thead><tr><th>No.</th><th>ID</th><th>ORDER</th><th>NAME</th><th>STATUS</th><th>CITME</th><th>OPERATION</th></tr></thead><tbody><tr><td colspan="7"><p style="text-align:center">暂无数据，请先选择一个一级分类进行管理！^_^</p></td></tr></tbody></table></div>';
						$(".container").html(_html);
						getCategoryV(null);
					});

	$(document).on("change",".change_nav",function() {
						var categorynav_id = $(this).val();
						$
								.ajax({
									type : 'post',
									url : path + 'categoryV/listbypage',
									data : {
										"categorynavId" : categorynav_id
									},
									dataType : 'json',
									success : function(data) {
										// //console.log(data);
										if (data.list.length != 0) {
											var categoryVTemplate = Handlebars.compile($("#categoryVTemplate").html());
											// 模板渲染数据
											$(".container").empty();
											$(".container").html(
													categoryVTemplate(data));
										} else {
											$(".container").empty();
											var _html = '<div class="categoryv_list" style="width:90%"><h2>二级分类管理</h2><p style="text-indent:4em"><select class="change_nav"><option>请选择一级分类名称：</option></select></p><table width="100%" border="1"><thead><tr><th>No.</th><th>ID</th><th>ORDER</th><th>NAME</th><th>STATUS</th><th>CITME</th><th>OPERATION</th></tr></thead><tbody><tr><td colspan="7"><p style="text-align:center">暂无数据，请先选择一个一级分类进行管理！^_^</p></td></tr></tbody></table></div>';
											$(".container").html(_html);
										}
										getCategoryV(categorynav_id);

									},
									error : function() {
										alert("查询操作失败！");
									}
								});
					});

	//动态触发博客展示页面
	$(document).on("click","#nav_blog",function(){
		toblogPage(1);
	});
	
	$(document).on('click',".categoryNavpageNumBtn",function(){
		to_page($(this).attr("title"));
	});	
});

var markList=new Array();

function toblogPage(pn){
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
			$(".blog_add_btn").css({"display":"block"});
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




function to_page(pn) {
	$.ajax({
		type : "post",
		url : path + "categoryNav/list",
		data : {
			"pn" : pn
		},
		dataType : 'json',
		success : function(data) {
			// //console.log(data);
			curPn = data.pageNum;
			pages = data.pages;
			// 定义一个helper 实现在index基础上从1开始
			// 编译模板
			var categorynav_ht = Handlebars
					.compile($("#categorynav_ht").html());
			// 模板渲染数据
			var _html = categorynav_ht(data);
			// dom操作
			$(".container").empty();
			$(".container").append(_html);
			
			
		},
		error : function() {
			layer.msg("服务器有异常，请稍后再试！");
		}
	});
}

function to_markPage(pn) {
	$.ajax({
		type : 'post',
		url : path + "mark/list",
		data : {
			"pn" : pn
		},
		dataType : 'json',
		success : function(data) {
			// //console.log(data);
			curPn = data.page;
			pages = data.pages;
			var markTemplate = Handlebars
					.compile($("#blogmarktemplate").html());
			$(".container").empty();
			$(".container").append(markTemplate(data));
		},
		error : function() {
			layer.msg("服务器繁忙稍后重试");
		}
	});
}

function getCategoryV(categoryVid) {
	$
			.ajax({
				type : 'post',
				url : path + 'categoryNav/listByMap',
				data : {
					"categorynavStatus" : "1"
				},
				success : function(str) {
					var navs = eval("(" + str + ")");
					var _options = '';
					for (var i = 0; i < navs.length; i++) {
						if (categoryVid != null
								&& navs[i].categorynavId == categoryVid) {
							_options += '<option selected="selected" value="'
									+ navs[i].categorynavId + '">'
									+ navs[i].categorynavName + '</option>';
						} else {
							_options += '<option value="'
									+ navs[i].categorynavId + '">'
									+ navs[i].categorynavName + '</option>';
						}
					}
					$(".change_nav").append(_options);
				},
				error : function() {
					alert("添加操作失败！");
				}
			});
}
