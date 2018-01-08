
drop table if exists tbl_global ;

create table tbl_global(
	global_id int primary key auto_increment comment '全局参数ID' ,
	global_htitle varchar(100) not null default '【某某某】IT技术博客系统' comment '头部标题信息' ,
	global_footertext varchar(100) not null default '(c) Copyright 2018 【某某某】的IT技术博客系统. All Rights Reserved. ' comment '底部文字信息' ,
	global_attentiontitle varchar(100) not null default '关注【某某某】' comment '关注栏目标题' ,
	global_recommendtitle varchar(100) not null default '热度推荐' comment '推荐栏目标题' ,
	global_categorytitle varchar(100) not null default '分类列表' comment '分类栏目标题' ,
	global_commenttitle varchar(100) not null default '最新评论' comment '评论栏目标题' ,
	global_ctime timestamp not null default now() comment '记录存入时间' 
);

drop table if exists tbl_category_nav ;
create table tbl_category_nav (
	categorynav_id  int primary key auto_increment comment '一级分类ID' ,
	categorynav_no int comment '一级分类排序标识' ,
	categorynav_name varchar(50) not null comment '一级分类名称' ,
	categorynav_status char not null default '0' comment '一级分类状态 0:未启用，1:已启用' ,
	categorynav_ctime timestamp not null default now() comment '记录存入时间' 
) ;

drop table if exists tbl_category_v ;
create table tbl_category_v (
	categoryv_id  int primary key auto_increment comment '二级分类ID' ,
	categoryv_no int comment '二级分类排序标识' ,
	categoryv_name varchar(50) not null comment '二级分类名称' ,
	categoryv_status char not null default '0' comment '二级分类状态 0:未启用，1:已启用' ,
	categorynavid int not null comment '该二级分类所属一级分类的ID' ,
	categoryv_ctime timestamp not null default now() comment '记录存入时间' 
) ;

drop table if exists tbl_category_point ;
create table tbl_category_point (
	categorypoint_id  int primary key auto_increment comment '三级分类ID' ,
	categorypoint_no int comment '三级分类排序标识' ,
	categorypoint_name varchar(50) not null comment '三级分类名称' ,
	categorypoint_status char not null default '0' comment '三级分类状态 0:未启用，1:已启用' ,
	categoryvid int not null comment '该三级分类所属二级分类的ID' ,
	categorypoint_ctime timestamp not null default now() comment '记录存入时间' 
) ;


drop table if exists tbl_mark ;
create table tbl_mark (
	mark_id  int primary key auto_increment comment '标签ID' ,
	mark_name varchar(50) not null comment '标签名称' ,
	mark_status char not null default '0' comment '标签状态 0:未启用，1:已启用' ,
	mark_ctime timestamp not null default now() comment '记录存入时间' 
) ;

drop table if exists tbl_blog ;
create table tbl_blog (
	blog_id  int primary key auto_increment comment '博客ID' ,
	blog_no int comment '博客排序标识' ,
	blog_title varchar(50) not null comment '博客标题' ,
	blog_content varchar(65536) not null comment '博客内容' ,
	blog_praise int not null  default 0 comment '博客点赞数量' ,
	blog_read int not null  default 0 comment '博客阅读数量' ,
	blog_comment int not null  default 0 comment '博客评论数量' ,
	blog_status char not null default '0' comment '博客状态 0:未启用，1:已启用，2:已删除' ,
	categorynavid int comment '博客一分类ID',
	categoryvid int comment '博客二分类ID',
	categorypointid int comment '博客三分类ID',
	blog_ctime timestamp not null default now() comment '记录存入时间' 
) ;

drop table if exists tbl_read ;
create table tbl_read (
	read_id  bigint primary key auto_increment comment '阅读ID' ,
	userid int comment '阅读者的ID',
	blogid int comment '博客ID',
	read_ctime timestamp not null default now() comment '记录存入时间' 
) ;

drop table if exists tbl_user ;
create table tbl_user (
	user_id  int primary key auto_increment comment '博客ID' ,
	user_name char(11) not null default '11111111111' comment '用户的账号' ,
	user_ctime timestamp not null default now() comment '记录存入时间' 
) ;


drop table if exists tbl_praise;
create table tbl_praise(
	praise_id bigint primary key auto_increment comment '点赞ID' ,
	blogid int comment '博客ID',
	userid int comment '点赞者ID'
	praise_ctime timestamp not null default now() comment '点赞保存时间'
);

drop table tbl_commentinfo;
create table tbl_commentinfo(
	commentinfo_id bigint primary key auto_increment comment '评论ID' ,
	userid int comment '说话者ID',
	answerid int comment '回答者ID',
	commentinfo_content varchar(65535) default "" comment '评论内容',
	commentinfo_ctime timestamp not null default now() comment '评论回复时间'
)

drop table tbl_admin;
create table tbl_admin (
	admin_id  int primary key auto_increment comment '管理员ID' ,
	admin_name char(11) not null default '11111111111' comment '管理员的账号' ,
	admin_password varchar(50) not null comment '管理员密码', 
	admin_ctime timestamp not null default now() comment '记录存入时间' 
) ;








