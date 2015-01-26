<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/jsp/inc/meta.inc" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/jsp/inc/head.inc" %>
    <script type="text/javascript">
    	var grid=null;
        var toolbar={ items: [
				{line:true},
                { text: "增加", click: f_add , icon:"add"},
                {line:true}
            ]};
        
    	$(function ()
        {	
            var g={
            		gridid:"maingrid",
            		conditon:"condition",
                    url:"${basePath}permission/role!data.action",
                    params:[gg("likename")],
                    toolbar:toolbar,
                    checkbox:false,
            		columns: [
                     { display: "角色名称", name: "name"},
                     { display: "级别", name: "gradeType",render:function(item){
                    	 if(item.gradeType==0){
                    		 return "系统管理员";
                    	 }else if(item.gradeType==1){
                    		 return "专技司管理员";
                    	 }else if(item.gradeType==2){
                    		 return "二级机构管理员";
                    	 }else if(item.gradeType==3){
                    		 return "三级业务管理员";
                    	 }
                     }},
                     { display: "项目", name: "projectType",render:function(item){
                    	 if(item.projectType == 1){
                    		 return "高研班项目";
                    	 }else if(item.projectType == 2){
                    		 return "基地项目";
                    	 }else if(item.projectType == 3){
                    		 return "急需人才项目";
                    	 }else if(item.projectType == 4){
                    		 return "岗位培训项目";	
                    	 }else if(item.projectType == 0){
                    		 return "系统管理";	
                    	 }else{
                    	 	return "所有";
                    	 }
                     }},
                     
                     
                     { display: "创建人", name: "createrId",render:function(item){
                    	 if(item.creater){
                    		 return item.creater.name;
                    	 }
                    	 return "";
                     }},
                     { display: "修改人", name: "modifyerId",render:function(item){
                    	 if(item.modifyer){
                    		 return item.modifyer.name;
                    	 }
                    	 return "";
                     }},
                     { display: "创建日期", name: "createTime"},
                     { display: "操作", name: "id",
                     	render: function(item){
                     		var html="<a href='javascript:f_view(\""+item.id+"\")' class='linkbutton'>查看</a>";
                            html = html+"<a href='javascript:f_edit(\""+item.id+"\")' class='linkbutton'>修改</a>";
                            html=html+"<a href='javascript:f_delete(\""+item.id+"\")' class='linkbutton'>删除</a>";
                            return html;
                        }
                     }
                    ]
            };
            grid=showGrid(g);
        });
        function f_search(){
        	grid.loadData();
        }
     function f_add(){
			var win = {
					title : "增加角色",
					name : "role-index-add-window",
					url : "${basePath}permission/role!toAdd.action",
					width:900,
					height:600,
					buttons : [ {
						text : "确定",
						onclick : function(item, dialog) {
							gg("role-index-add-window").contentWindow.f_submit(dialog);
						}
					}, {
						text : "取消",
						onclick : function(item, dialog) {
							dialog.close();
						}
					} ]
				};
		showWindow(win);
	}
     /**编辑*/
	function f_edit(id) {
		var win = {
			title : "编辑用户组",
			name : "role-index-edit-window",
			url : "${basePath}permission/role!toEdit.action?role.id=" + id,
			width:900,
			height:600,
			buttons : [ {
				text : "确定",onclick : function(item, dialog) {
					gg("role-index-edit-window").contentWindow.f_submit(dialog);
				}}, 
				{
				text : "取消",
				onclick : function(item, dialog) {
					dialog.close();
				}} 
			]
		};
		showWindow(win);
	}
	/**查看*/
	function f_view(id){
		var win = {
				title : "查看用户组",
				url : "${basePath}permission/role!detail.action?role.id=" + id,
				width:900,
				height:600,
				buttons : [
				    {text : "关闭",onclick : function(item, dialog) {
						dialog.close();
					}
				 }]
			};
		showWindow(win);
	}
	/**删除*/
	function f_delete(id){
		confirm("删除操作", "确认删除吗？", function() {
			$.getJSON("${basePath}permission/role!delete.action?role.id=" + id,function(json) {
					if (json.success) {
						grid.loadData();
					}else{
						alert(json.message,"error");
					}
			});
		});
	}
	function f_reset(){
		gg("form-search").reset();
	}
</script>
</head>
<body>
<div class="content">
<form action="" id="form-search">
   <ul>
   <li>
	   <label>用户组名称：</label>
	   <input type="text" name="condition.likename" id="likename" class="search-input-text"/>
   </li>
   <li><a class="l-button" onclick="f_search()">查询</a></li>
   <li><a class="l-button" onclick="f_reset()">重置</a></li>
   </ul>
   </form>
</div>
    <div id="maingrid" class="grid">
    </div>
</body>
</html>

