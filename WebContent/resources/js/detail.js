var path = "http://localhost/ssmBlog/";

var curBlogId = $.cookie("blogId");
var blogIds = $.cookie("blogIds");
var beforeBlogId, nextBlogId;
var curCategoryNavId;
var curCategoryVId;

var hasBefore = false, hasNext = false;
$(function() {
	getParams();
	// ////console.log(blogIds+":"+blogIds.split(",").length);
	init_global();
	init_categoryNav();
	init_categoryV(curCategoryNavId);

	init_top3hot();
	
	callBackCategory();
	$(document).on('click', '.categoryVBtn', function() {
		$(this).siblings().removeClass("active");
		$(this).toggleClass('active');
	});
	
	init_blog(1, 0);
	$(document).on('click', '#goIndexBtn', function() {
		window.location = path + "index.html";
	})

	getTopAndUnder();

	// 点赞功能实现
	$(document)
			.on(
					'click',
					'#praise',
					function() {
						var praise_img = $("#praise-img");
						var text_box = $("#add-num");
						var praise_txt = $("#praise-txt");
						var num = parseInt(praise_txt.text());
						if (praise_img.attr("src") == ("praise/Images/yizan.png")) {
							$(this)
									.html(
											"<img src='praise/Images/zan.png' id='praise-img' class='animation' />");
							praise_txt.removeClass("hover");
							text_box.show().html(
									"<em class='add-animation'>-1</em>");
							$(".add-animation").removeClass("hover");
							num -= 1;
							praise_txt.text(num)
							update_blog(0, -1);
						} else {
							$(this)
									.html(
											"<img src='praise/Images/yizan.png' id='praise-img' class='animation' />");
							praise_txt.addClass("hover");
							text_box.show().html(
									"<em class='add-animation'>+1</em>");
							$(".add-animation").addClass("hover");
							num += 1;
							praise_txt.text(num);
							// 更新点赞数目
							update_blog(0, 1);
						}
					});
	init_comment();
	getCurUser();
});

function getCurUser(){
	$.ajax({
		type:'post',
		url:path+'getCurUserName',
		dataType:'json',
		success:function(data){
//			console.log(data);
			if(data.code==200){
				//$(".userInfoArea").css({"display":"none"});
				$(".userInfoArea").html("");
				$(".userInfoArea").append($("<span style='font-size:16px;color:blue;'></span>").html(data.result.curUserName));
			}
		},
		error:function(){
//			layer.msg("服务器繁忙  请稍后重试");
		}
	});
}


function init_blog(isBlogRead, isBlogPraise) {
	var params = "";
	if (isBlogRead) {
		params += "blogRead=" + isBlogRead + "&";
	}
	if (isBlogPraise) {
		params += "blogPraise=" + isBlogPraise + "&";
	}
	$.ajax({
		url : path + 'blog/' + curBlogId,
		type : 'POST',
		data : params,
		dataType : 'json',
		success : function(data) {
			// //////console.log(data);
			$(".blogTitleArea").html(data.blogTitle);

			var blogTitleTemplate = Handlebars.compile($("#blogTitleTemplate")
					.html());
			$(".blogDetailArea").html(blogTitleTemplate(data));

			var blogContentTemplate = Handlebars.compile($("#blogContentTemplate").html());
			//////console.log(data.blogContent);
			$(".lg_blog_detail_content").html(blogContentTemplate(data));
		}
	});
}

function update_blog(isBlogRead, isBlogPraise) {
	var params = "";
	if (isBlogRead) {
		params += "blogRead=" + isBlogRead + "&";
	}
	if (isBlogPraise) {
		params += "blogPraise=" + isBlogPraise + "&";
	}
	$.ajax({
		url : path + 'blog/praise/' + curBlogId,
		type : 'POST',
		data : params,
		success : function(data) {
		var praise=parseInt($("#praiseSpan").text());
			$("#praiseSpan").text(eval(praise+isBlogPraise));	
		}
	});
}

// 获取博客跳转过来参数
function getParams() {
	var params = location.search;
	var paraArr = params.split("?")[1].split("&");
	curCategoryNavId = paraArr[0].substr(paraArr[0].indexOf("=") + 1);
	curCategoryVId = paraArr[1].substr(paraArr[1].indexOf("=") + 1);
	//回显分类列表
	
}

function init_categoryV(navId) {
	$.ajax({
		url : path + 'categoryV/' + navId,
		type : 'get',
		dataType : 'json',
		success : function(data) {
			////console.log(data);
			var categoryVHTML = Handlebars.compile($('#categoryV-template')
					.html());
			$('.lg_list-group').html(categoryVHTML(data));
			$.each($(".categoryVBtn"),function(index,data){
//				////console.log($(data).attr("cateGoryVid")+":"+curCategoryVId);
				if($(data).attr("cateGoryVid")==curCategoryVId){
					$(data).toggleClass("active").siblings('a').removeClass("active");
				}
			});
			
		},
		error : function() {
		}
	});
}

function init_categoryNav() {
	$.ajax({
		url : path + 'categoryNav/list',
		type : 'get',
		dataType : 'json',
		success : function(data) {
			// ////////console.log(data);
			var categoryNavHTML = Handlebars
					.compile($('#category_nav-template').html());
			$('.nav-pills').html(categoryNavHTML(data));
			$.each($(".categorynavNameClass"),function(index,data){
				if($(data).attr("categorynavName_id")==curCategoryNavId){
					$(data).parents("li").addClass("active").siblings('li').removeClass("active");
				}
			})		
		},
		error : function() {
		}
	});
}

function getCookie(cname) {
	var name = cname + "=";
	var ca = document.cookie.split(";");
	for (var i = 0; i < ca.length; i++) {
		var c = ca[i];
		while (c.charAt(0) == ' ') {
			c = c.substring(1);
		}
		if (c.indexOf(cname) == 0) {
			return c.substring(name.length, c.length);
		}
	}
	return "";
}

function init_global() {
	$.ajax({
		url : path + 'global/1',
		type : 'get',
		dataType : 'json',
		success : function(data) {
			$(".globalTitle").html(data.globalHtitle);
			$(".globalTitle2").append(data.globalHtitle).append(
					"<small>欢迎您访问！</small>");
			$(".globalFooterTitle").html(data.globalFootertext);
			$(".globalAttentiontitleArea").html(data.globalAttentiontitle);
			$(".globalRecommendtitleArea").html(data.globalRecommendtitle);
			$(".globalCategorytitleArea").html(data.globalCategorytitle);
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
	datetimeType = date.getFullYear() + "/" + getMonth(date) + "/"
			+ getDay(date) + " " + getHours(date) + ":" + getMinutes(date)
			+ ":" + getSeconds(date);// yyyy-MM-dd 00:00:00格式日期
	return datetimeType;
}
var handleHelper = Handlebars.registerHelper("fmtDate", function(data) {
	return datetimeFormat(data);
});

// 加载上下页标题
function getTopAndUnder() {
	var turn = false;
	// ////console.log(blogIds);
	var Idarr = blogIds.split(",");
	var curIndex = 0;
	for (var i = 0; i < Idarr.length; i++) {
		if (Idarr[i] == curBlogId) {
			curIndex = i;
			break;
		}
	}
	var nextTitle = "";
	var nextId, beforeId;
	// ////console.log(curBlogId);
	if (Idarr.length == 3) {
		$.each(Idarr, function(index, data) {
			if (data != curBlogId) {
				// 获取上下篇文章的标题
				$.ajax({
					url : path + 'blog/' + data,
					type : 'POST',
					dataType : 'json',
					success : function(data) {
						if (curIndex == 0 && !turn) {
							$("#beforeBlog").html(":暂无上一篇");
							nextTitle = data.blogTitle;
							nextId = data.blogId;
							// ////console.log(nextTitle);
							turn = true;
						} else if (curIndex == 0 && turn) {
							$("#nextBlog").html(nextTitle).attr("nextId",
									nextId);
						} else if (curIndex == 1 && !turn) {
							$("#beforeBlog").html(data.blogTitle).attr(
									"beforeId", data.blogId);
							turn = true;
						} else if (curIndex == 1 && turn) {
							$("#nextBlog").html(data.blogTitle).attr("nextId",
									data.blogId);
							;
						} else if (curIndex == 2 && !turn) {
							$("#nextBlog").html(":暂无下一篇");
							turn = true;
						} else if (curIndex == 2 && turn) {
							$("#beforeBlog").html(data.blogTitle).attr(
									"beforeId", data.blogId);
						}

					}
				});
			}
		});
	} else if (Idarr.length == 1) {
		$("#beforeBlog").html(":暂无上一篇");
		$("#nextBlog").html(":暂无下一篇");
	} else if (Idarr.length == 2) {
		if (curIndex == 0) {
			$("#beforeBlog").html(":暂无上一篇");
			$.ajax({
				url : path + 'blog/' + Idarr[1],
				type : 'POST',
				dataType : 'json',
				success : function(data) {
					$("#nextBlog").html(data.blogTitle).attr("nextId",
							data.blogId);
				}
			});

		} else if (curIndex == 1) {
			$("#nextBlog").html(":暂无下一篇");
			$.ajax({
				url : path + 'blog/' + Idarr[0],
				type : 'POST',
				dataType : 'json',
				success : function(data) {
					$("#beforeBlog").html(data.blogTitle).attr("beforeId",
							data.blogId);
				}
			});
		}
	}

	// 绑定事件
	$(document).on(
			'click',
			'.linkBlog',
			function() {
				if ($(this).html().indexOf(":暂无") < 0) {
					// 页面跳转
					var BlogId = $(this).find(".hasBlogIdSpan")
							.attr("beforeId")
							|| $(this).find(".hasBlogIdSpan").attr("nextId");
					curBlogId = BlogId;
					init_blog(1, 0);
				}

			});
}

// 热门标签三个
function init_top3hot() {
	$.ajax({
		url : path + 'blog/top3hot',
		type : 'get',
		dataType : 'json',
		success : function(data) {
			// ////console.log(data);
			var top3HotTemplate = Handlebars.compile($("#top3HotTemplate")
					.html());
			$(".tophot3Area").html(top3HotTemplate(data));
		}
	});

}

/**
 * 异步加载评论功能
 * 
 * @returns
 */
function init_comment() {
	$.ajax({
		url : path + 'blogComment/' + curBlogId,
		type : 'get',
		dataType : 'json',
		success : function(data) {
			//////console.log(data);
			var commentTemplate = Handlebars.compile($("#commentTemplate").html());
			$(".historyComment").html(commentTemplate(data));
		}
	});
}

function callBackCategory(){
	$.each($(".categoryVBtn"),function(index,data){
//		////console.log($(data).attr("cateGoryVid")+":"+curCategoryVId);
		if($(data).attr("cateGoryVid")==curCategoryVId){
			$(data).toggleClass("active");
		}
	})
}
