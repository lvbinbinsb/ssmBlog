var path = "http://localhost/ssmBlog/";

var booleanShowRename = true, booleanShowDelete = true;

var zTreeObj;
var setting = {
	async : {
		enable : true,
		url : getUrl
	},
	check : {
		enable : false
	},
	data : {
		simpleData : {
			enable : true
		}
	},
	view : {
		addHoverDom: addHoverDom,
		removeHoverDom:removeHoverDom,
		expandSpeed : ""
	},
	edit : {
		enable : true,
		showRemoveBtn : booleanShowDelete,
		showRenameBtn : booleanShowRename
	},
	callback : {
		onRemove : onRemove,
		onRename : onRename,
		beforeDrag: beforeDrag,
		beforeDrop: beforeDrop,
		onDrop:onDrop
	}
};

//拖动节点
function onDrop(event, treeId, treeNodes, targetNode, moveType) {
    //获取父节点Id并更新
   var cateGoryNavId=targetNode.id;
   var curId=treeNodes[0].id;
   $.ajax({
	   type:'post',
	   url:path+"categoryV/changeCategoryNav",
	   data:{
		   "id":curId,
		   "pid":cateGoryNavId
	   },
	   dataType:'json',
	   success:function(data){
		   //console.log(data);
		   if(data==true){
			   layer.msg("操作成功");
		   }
	   }
   });
};

//控制拖动节点
function beforeDrag(treeId, treeNodes) {
	for (var i=0,l=treeNodes.length; i<l; i++) {
		if (treeNodes[i].drag === false) {
			return false;
		}
	}
	return true;
}

function beforeDrop(treeId, treeNodes, targetNode, moveType) {
	return targetNode.pid==0?true:false;
}

function addHoverDom(treeId, treeNode) {
	var sObj = $("#" + treeNode.tId + "_span");
	if (treeNode.pid!=0|| $("#addBtn_"+treeNode.tId).length>0) return;
	var addStr = "<span class='button add' id='addBtn_" + treeNode.tId
		+ "' title='添加' onfocus='this.blur();'></span>";
	sObj.after(addStr);
	var btn = $("#addBtn_"+treeNode.tId);
	if (btn) btn.bind("click", function(){
		var zTree = $.fn.zTree.getZTreeObj("ztree");
		zTree.addNodes(treeNode, {pid:treeNode.id, name:""});
		var nodeArr=treeNode.children;
		var theLastInsertNode=nodeArr[nodeArr.length-1];
		zTree.editName(theLastInsertNode);
		return false;
	});
};

function removeHoverDom(treeId, treeNode) {
	$("#addBtn_"+treeNode.tId).unbind().remove();
};

function onRename() {
	var node = zTreeObj.getSelectedNodes();
//	//console.log(node[0].name);
	if(node[0].id!=undefined){
	$.ajax({
		url : path + 'categoryV/updateNode',
		type : 'post',
		data : {
			"id" : node[0].id,
			"name" : node[0].name
		},
		dataType:'json',
		success : function(data) {
			if(data==true){
				layer.msg("修改成功");
			}
		}
	});
	}else{
		//属于新增节点
		$.ajax({
			url : path + 'categoryV/addNode',
			type : 'post',
			data : {
				"name" : node[0].name,
				"pid":node[0].pid
			},
			dataType:'json',
			success : function(data) {
				//console.log(data);
				//返回之后更新节点id以及pid
				if(data.code==200){
					//console.log(data);
				obj=data.result.newNode;
//				//console.log(data);
				node[0].id=obj.id;
				node[0].name=obj.name;
				node[0].isParent=obj.isParent;
				node[0].open=obj.open;
				$.fn.zTree.getZTreeObj("ztree").updateNode(node[0]);
				}else if(data.code==100){
					//console.log(data);
//					$.each(data.map.errors,function(index,result){
//						layer.msg();
//					});
					layer.msg(data.result.errors["ztreeVo"]);
					$.fn.zTree.getZTreeObj("ztree").removeNode(node[0]);
				}
			}
		});
	}
}

function onRemove(event, treeId, treeNode){
	var nodeId=treeNode.id;
	$.ajax({
		url : path + 'categoryV/deleteNode',
		data:{"id":nodeId},
		type:'GET',
		dataType:'json',
		success:function(data){
			if(data==true){
				layer.msg("删除成功");
			}
		}
	});
	
}

function getUrl(treeId, treeNode) {
	var param = "id=" + treeNode.id;
	return path + "categoryV/getNodes?" + param;
}

function setEdit() {
	var zTree = $.fn.zTree.getZTreeObj("ztree"), removeTitle = "删除"
	renameTitle = "重命名";
	zTree.setting.edit.removeTitle = removeTitle;
	zTree.setting.edit.renameTitle = renameTitle;
}

$(function() {
	$(document)
			.on(
					'click',
					"#nav_categoryvZtree",
					function() {
						var _html = '<ul class="ztree" id="ztree" style="width:350px;height:350px;margin:0 auto"></ul>';
						$(".container").empty();
						$(".container").append(_html);
						$.ajax({
							type : 'post',
							url : path + 'categoryNav/getZtree',
							data : {
								"categorynavStatus" : "1"
							},
							dataType : 'json',
							success : function(data) {
//								//console.log(data);
								var zNodes = data;
								for(var i=0;i<zNodes.length;i++){
									if(zNodes[i].pid==0){
										zNodes[i].drag=false;
									}
								}
								//console.log(zNodes);
								$.fn.zTree.init($("#ztree"), setting, zNodes);
								zTreeObj = $.fn.zTree.getZTreeObj("ztree");
								setEdit();
							},
							error : function() {
								alert("添加操作失败！");
							}
						});

					});
});