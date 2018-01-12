var path = "http://localhost/ssmBlog/";

var curNavId = 2;
var curVid = 1;
var curPn = 1;
var navVarr = [ 1 ];
var activesize = 0;
var curIndex = 0;
var keyWords="";
var blogIds=[];
$(function() {
	init_global();
	init_categoryNav();
	init_categoryV(2);
	init_blog(2, 1, navVarr[0]);
	init_top3hot();
	
	// 一级菜单切换
	$(document).on('click', ".categorynavNameClass", function() {
		curIndex = $(this).attr("indexNav");
		$(this).parent("li").addClass("active");
		if ($("ul.nav-pills").find("li[class=active]").length > 1) {
			$("ul.nav-pills").find("li[class=active]").removeClass("active");
			$(this).parent("li").addClass("active");
		}
		var navId = $(this).attr("categorynavName_id");
		if (navId != 1) {
			curNavId = navId;
			init_categoryV(navId);
			// console.log(index);
			init_blog(curNavId, navVarr[curIndex], 1);
			$(".searchKeyWords").attr("placeholder","Search for...").val("");
		} else {
			init_HomePage();
		}
	});

	init_pageBar();

	$(document).on('click', '.lg_list-group .categoryVBtn', function() {
		$(this).siblings().removeClass("active");
		$(this).toggleClass("active");
		var vid = $(this).attr('categoryvId');
		curVid = vid;
		init_blog(curNavId, vid, 1);
	});

	getcategoryVid();

	// 博客详情展示页面
	$(document).on(
			'click',
			".gotoDetailPage",
			function() {
				// 获取blogId
				var blogId = $(this).parents("div.blogDiv").attr("blogId");
//				setCookie("blogId", blogId, 3);
				$.cookie("blogId",blogId);
//				setCookie("blogIds",serialize(blogIds),3);
				$.cookie("blogIds",blogIds);
//				console.log(blogIds);
				$(this).attr("href",path + "detail.html?categoryNavId="+curNavId+"&categoryVId="+curVid);
					
			});

	$(document).on(
			'click',
			".blogDiv",
			function() {
				var blogId = $(this).attr("blogId");
//				setCookie("blogId", blogId, 3);
				$.cookie("blogId",blogId);
				window.location.href = path + "detail.html?categoryNavId="
						+ curNavId + "&categoryVId=" + curVid;
			});
	
	//搜索框查询
	$("#searchBtn").on('click',function(){
		keyWords=$(".searchKeyWords").val();
		//发送ajax查询
		init_result(keyWords,1);
		
	});
});

function init_result(keyWords,curPn){
	$.ajax({
		type:'POST',
		url:path+"/search/"+keyWords,
		data:{
			"pn":curPn
		},
		dataType:'json',
		success:function(data){
			console.log(data);
			$(".lg_blog_items").html("");
			var blogsearchTemplate=Handlebars.compile($("#blogsearch-template").html());
			$(".lg_blog_items").html(blogsearchTemplate(data));
			
			//分页栏渲染
			$(".blogPageNav").html("");
			var pageSearchNavtemplate=Handlebars.compile($("#pageSearchNavtemplate").html());
			$(".blogPageNav").html(pageSearchNavtemplate(data));
		},
		error:function(){
			layer.msg("服务器繁忙");
		}
	});
}

function init_top3hot() {
	$.ajax({
		url : path + 'blog/top3hot',
		type : 'get',
		dataType : 'json',
		success : function(data) {
			//console.log(data);
			var top3HotTemplate = Handlebars.compile($("#top3HotTemplate")
					.html());
			$(".tophot3Area").html(top3HotTemplate(data));
		}
	});

}

function init_HomePage(){
	$.ajax({
		type:'GET',
		url:path+'blog/getHomePage',
		dataType:'json',
		success:function(data){
			 // console.log(data);
			if (data.size == 0) {
				$(".lg_blog_items").html(' ');
				$(".lg_blog_items").append($("<div></div>").append("<h4>主人很懒,暂未留下任何痕迹</h4>"));
				return ;
			}
			$.each(data.list,function(index, data) {
				if (data.blogTitle.length > 30) {
					data.blogTitle = data.blogTitle.substr(0, 30);
				}
				if (data.blogContent.length > 100) {
					data.blogContent = data.blogContent.substr(0, 180);
				}
			});
			var blogtemplate = Handlebars.compile($('#blog-template').html());
			$(".lg_blog_items").html(blogtemplate(data));

			var pageNavtemplate = Handlebars.compile($('#pageNav-template')
					.html());
			$('.blogPageNav').html(pageNavtemplate(data));

			// 在次渲染完成 再次调用请求加载 标签
			$.each($(".blogDiv"), function(index, data) {
				var divObj = $(this).find(".markArea");
				var blogId = $(data).attr('blogId');
				$.ajax({
					url : path + '/mark/blog/' + blogId,
					type : 'GET',
					dataType : 'json',
					success : function(data) {
						// console.log(data);
						divObj.html("");
						$.each(data,
								function(index, data) {
									// <span class="label label-primary
									// markArea"></span>
									divObj.append($("<span></span>").addClass(
											"label label-primary").html(
											data.markName));
									divObj.append("&nbsp;");
								});
					}
				});

			});
		}
	});
}




function init_global() {
	$.ajax({
		url : path + 'global/1',
		type : 'get',
		dataType : 'json',
		success : function(data) {
			// //console.log(data);
			var htitle = Handlebars
					.compile($('#global_htitle-template').html());
			$('#global_htitle-area').html(htitle(data));
			var htitle2 = Handlebars.compile($('#global_htitle2-template')
					.html());
			$('.page-header').html(htitle2(data));
			var attentiontitle = Handlebars.compile($(
					'#global_attentiontitle-template').html());
			$('#attentiontitle_area').html(attentiontitle(data));
			var recommendtitle = Handlebars.compile($(
					'#global_recommendtitle-template').html());
			$('#global_recommendtitle_area').html(recommendtitle(data));
			var categorytitle = Handlebars.compile($(
					'#global_categorytitle-template').html());
			$('#global_categorytitle_area').html(categorytitle(data));
			var commenttitle = Handlebars.compile($(
					'#global_commenttitle-template').html());
			$('#global_commenttitle_area').html(commenttitle(data));

			var footertext = Handlebars
					.compile($('#global_footertext-template').html());
			var footerhtml = footertext(data);
			footerhtml = footerhtml.replace("&amp;", "&");
			$('.panel-body').html(footerhtml);
		},
		error : function() {
		}
	});
}

// 注册一个比较大小的Helper,判断v1是否大于v2
Handlebars.registerHelper("testEqual", function(v1, v2, options) {
	if (v1 == v2) {
		// 满足添加继续执行
		return options.fn(this);
	} else {
		// 不满足条件执行{{else}}部分
		return options.inverse(this);
	}
});

function init_categoryNav() {
	$.ajax({
		url : path + 'categoryNav/list',
		type : 'get',
		dataType : 'json',
		success : function(data) {
			// //console.log(data);
			var categoryNavHTML = Handlebars
					.compile($('#category_nav-template').html());
			$('.nav-pills').html(categoryNavHTML(data));
		},
		error : function() {
		}
	});
}

function init_categoryV(navId) {
	$.ajax({
		url : path + 'categoryV/' + navId,
		type : 'get',
		dataType : 'json',
		success : function(data) {
			// //console.log(data);
			var categoryVHTML = Handlebars.compile($('#categoryV-template')
					.html());
			$('.lg_list-group').html(categoryVHTML(data));

		},
		error : function() {
		}
	});
}

// 加载博客
function init_blog(navId, Vid, pn) {
	$.ajax({
		url : path + 'blog/list',
		type : 'post',
		data : {
			"categorynavId" : navId,
			"categoryvid" : Vid,
			"pn" : pn
		},
		dataType : 'json',
		success : function(data) {
			if (data.size == 0) {
				$(".lg_blog_items").html(' ');
				$(".lg_blog_items").append(
						$("<div></div>").append("<h4>主人很懒,暂未留下任何痕迹</h4>"));
				return;
			}
				
			// console.log(data);
			blogIds=[];
			$.each(data.list, function(index, data) {
				blogIds.push(data.blogId);
				if (data.blogTitle.length > 30) {
					data.blogTitle = data.blogTitle.substr(0, 30);
				}
				if (data.blogContent.length > 100) {
					data.blogContent = data.blogContent.substr(0, 180);
				}
			});
//			console.log(blogIds);
			var blogtemplate = Handlebars.compile($('#blog-template').html());
			$(".lg_blog_items").html(blogtemplate(data));

			var pageNavtemplate = Handlebars.compile($('#pageNav-template')
					.html());
			$('.blogPageNav').html(pageNavtemplate(data));

			// 在次渲染完成 再次调用请求加载 标签
			$.each($(".blogDiv"), function(index, data) {
				var divObj = $(this).find(".markArea");
				var blogId = $(data).attr('blogId');
				$.ajax({
					url : path + '/mark/blog/' + blogId,
					type : 'GET',
					dataType : 'json',
					success : function(data) {
						// console.log(data);
						divObj.html("");
						$.each(data,
								function(index, data) {
									// <span class="label label-primary
									// markArea"></span>
									divObj.append($("<span></span>").addClass(
											"label label-primary").html(
											data.markName));
									divObj.append("&nbsp;");
								});
					}
				});

			});

		},
		error : function() {

		}
	});
}
// 时间格式化

// 返回 01-12 的月份值
function getMonth(date) {
	var month = "";
	month = date.getMonth() + 1; // getMonth()得到的月份是0-11
	if (month < 10) {
		month = "0" + month;
	}
	return month;
}
// 返回01-30的日期
function getDay(date) {
	var day = "";
	day = date.getDate();
	if (day < 10) {
		day = "0" + day;
	}
	return day;
}
// 返回小时
function getHours(date) {
	var hours = "";
	hours = date.getHours();
	if (hours < 10) {
		hours = "0" + hours;
	}
	return hours;
}
// 返回分
function getMinutes(date) {
	var minute = "";
	minute = date.getMinutes();
	if (minute < 10) {
		minute = "0" + minute;
	}
	return minute;
}
// 返回秒
function getSeconds(date) {
	var second = "";
	second = date.getSeconds();
	if (second < 10) {
		second = "0" + second;
	}
	return second;
}
function datetimeFormat(longTypeDate) {
	var datetimeType = "";
	var date = new Date();
	date.setTime(longTypeDate);
	datetimeType = date.getFullYear() + "-" + getMonth(date) + "-"
			+ getDay(date) + " " + getHours(date) + ":" + getMinutes(date)
			+ ":" + getSeconds(date);// yyyy-MM-dd 00:00:00格式日期
	return datetimeType;
}
var handleHelper = Handlebars.registerHelper("fmtDate", function(data) {
	return datetimeFormat(data);
});

// 初始化标签
function init_pageBar() {
	// 页面跳转
	$(document).on('click', '.blogPageNav a.hasPageNum', function() {
		var pn = $(this).text();
		curPn = pn;
		if(!$(this).hasClass("searchResult")){
			init_blog(curNavId, curVid, pn);
		}else{
			init_result(keyWords, curPn)
		}
	});

	// 下一页功能实现
	$(document).on('click', '.blogPageNav a.toBeforPageA', function() {
		init_blog(curNavId, curVid, curPn - 1);
		curPn = curPn - 1;
	});

	// 前往下一页
	$(document).on('click', '.blogPageNav a.toNextPageA', function() {
		init_blog(curNavId, curVid, curPn + 1);
		curPn = curPn + 1;
	});
}

function getcategoryVid() {
	$.ajax({
		url : path + '/categoryV/load',
		type : 'GET',
		dataType : 'json',
		success : function(data) {
			$.each(data, function(index, data) {
				navVarr.push(data);
			});
			// console.log(navVarr);
		}

	});
}