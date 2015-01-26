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
                    url:"${basePath}permission/employee!data.action",
                    params:function(){return $("#form-search").serializeArray();},
                    toolbar:toolbar,
                    checkbox:false,
            		columns: [
                     { display: "用户名", name: "name"},
                     { display: "所属角色", name: "roleId",
                    	 render:function(item){
                    		 if(item.role)return item.role.name;
                    	 }
                     },
                     { display: "行政区域", name: "organization",
                    	 render:function(item){
                    		 if(item.organization != null && item.organization.region != null){
                    		 	return item.organization.region.regionName;
                    		 }else{
                    		 	return "全国";
                    		 }
                    	 }
                     },
                     { display: "所属机构", name: "orgId",
                    	 render:function(item){
                    		 if(item.orgId == null || item.orgId == 0){
                    		 	return "全国";
                    		 }else{
                    		 	return item.orgName;
                    		 }
                    	 }
                     },
                     { display: "创建日期" , name: "createTime"},
                     { display: "创建人", name: "createId",
                    	 render:function(item){
                    		 if(item.creater)return item.creater.name;
                    	 }
                     },
                     { display: "状态", name: "state",
                    	 render:function(item){
                    		 if(item.state==0)return "删除";
                    		 else if(item.state==1)return "已启用";
                    		 else if(item.state==2)return "冻结";
                    	 }
                     },
                     { display: "操作", name: "id",
                     	render: function(item){
                     		var html="<a href='javascript:f_edit(\""+item.id+"\")' class='linkbutton'>修改</a>";
                     		if(item.state==1){
                     			html=html+"<a href='javascript:f_togle_lock(\""+item.id+"\",\"lock\")' class='linkbutton'>冻结</a>";
                     		}else if(item.state==2){
                     			html=html+"<a href='javascript:f_togle_lock(\""+item.id+"\",\"unlock\")' class='linkbutton'>开启</a>";
                     		}
                            html=html+"<a href='javascript:f_reset_password(\""+item.id+"\")' class='linkbutton'>重置密码</a>";
                            html=html+"<a href='javascript:f_delete(\""+item.id+"\")' class='linkbutton'>删除</a>";
                            html=html+"<a href='javascript:f_detail(\""+item.id+"\")' class='linkbutton'>查看详情</a>";
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
     /**重置密码*/
     function f_reset_password(id){
    	confirm("重置密码操作", "初始化密码将变为${initPassword}<br/>您确定要初始化吗？", function() {
 			$.getJSON("${basePath}permission/employee!initPassword.action?employee.id=" + id,function(json) {
 					var info=json.success?"success":"error";
 					alert(json.message,info,function(){
 						if (json.success) {
 							grid.loadData();
 						}
 					});
 			});
 		});
     }
     /**开启、冻结*/
     function f_togle_lock(id,action){
    	 $.getJSON("${basePath}permission/employee!"+action+".action?employee.id=" + id,function(json) {
				if (json.success) {
					grid.loadData();
				}
		 });
     }
     function f_add(){
			var win = {
					title : "增加用户",
					name : "employee-index-add-window",
					url : "${basePath}permission/employee!toAdd.action",
					buttons : [ {
						text : "确定",
						onclick : function(item, dialog) {
							gg("employee-index-add-window").contentWindow.f_submit(dialog);
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
	function f_edit(id) {
		var win = {
			title : "编辑用户",
			name : "employee-index-edit.window",
			url : "${basePath}permission/employee!toEdit.action?employee.id=" + id,
			buttons : [ {
				text : "确定",onclick : function(item, dialog) {
					gg("employee-index-edit.window").contentWindow.f_submit(dialog);
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
	function f_detail(id) {
		var win = {
			title : "用户详情",
			name : "employee-detail",
			url : "${basePath}permission/employee!toDetail.action?employee.id=" + id,
			buttons : [
				{
				text : "关闭",
				onclick : function(item, dialog) {
					dialog.close();
				}} 
			]
		};
		showWindow(win);
	}

	function f_delete(id) {
		confirm("删除操作", "确认删除吗？", function() {
			$.getJSON("${basePath}permission/employee!delete.action?employee.id=" + id,
					function(json) {
						if (json.success) {
							grid.loadData();
						}
			});
		});
	}
	function f_reset(){
		gg("form-search").reset();
	}
</script>
    <style type="text/css">
    	.linkbutton{margin:1px 1px; padding:1px 2px;}
    </style>
</head>
<body>
<form action="" id="form-search">
<div class="content">
   <ul>
   <li>
   		<label>权限：</label>
   		<select name="condition.roleId" id="roleId" class="search-input-text">
   			<option></option>
    		<c:forEach items="${roleList }" var="item">
    			<option value="${item.id }">${item.name }</option>
    		</c:forEach>
    	</select>
   </li>
   <li>
	   <label>用户名称：</label>
	   <input type="text" name="condition.likename" id="likename" class="search-input-text"/>
   </li>
   <li>
   		<label>状态：</label>
   		<select name="condition.state" class="input-select" style="width:80px;_width:80px;">
   			<option></option>
    		<option value="NORMAL">已启用</option>
    		<option value="LOCK">冻结</option>
    	</select>
   </li>
   <li><a class="l-button" onclick="f_search()">查询</a></li>
   <li><a class="l-button" onclick="f_reset()">重置</a></li>
   
   </ul>
</div>
</form>
    <div id="maingrid" class="grid">
    </div>
</body>
</html>

