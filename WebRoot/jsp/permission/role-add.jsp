﻿<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/jsp/inc/meta.inc" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/jsp/inc/head.inc" %>
    <script type="text/javascript">
    treeManager=null;
         function f_submit(dialog){
        	//alert(JSON2.stringify(treeManager.getChecked()));
        	if($("form.validate").valid()){
        		var datas=treeManager.getChecked();
        		var menus1={};//一级菜单
    			var menus2={};//二级菜单
        		if(datas){
        			for(var i in datas){
        				var dt=datas[i];
        				if(dt.data.degree==2){
        					menus2[dt.data.id]=dt.data.id;
        					menus1[dt.data.pid]=dt.data.pid;
        				}
        			}
        		}
	        	var param=$("form.validate").serialize();
	        	//封装菜单选项
	        	var index=0;
	        	for(var i in menus1){
	        		param=param+"&roleMenuList["+ index +"].menuId="+i;
	        		index++;
	        	}
	        	for(var i in menus2){
	        		param=param+"&roleMenuList["+ index +"].menuId="+i+"&roleMenuList["+ index +"].roleBit=30";
	        		index++;
	        	}
	        	//alert(param);
	        	$.getJSON("${basePath}permission/role!add.action", param,function(json){
	        		if(json.success){
	        			home_fresh();
					}else{
						alert(json.message,"error");
					}
				});
			} 
          }
         /**在客户端封装基础项参数*/
         function f_return_basicitem_param(name){
        	 var param="";
        	 var bit=0;
        	 var names=[];
        	 $("form :checkbox:checked[name='"+name+"']").each(function(){
        		 var val=$(this).val();
        		 var alt=$(this).attr("alt");
        		 if(val!=0){
					 bit=bit+(1<<val);
					 names.push(alt);
        		 }
        	 });
        	 param="&role."+name+"Bit="+bit+"&role."+name+"Name="+names.join(",");
        	 return param;
         }
   	   /**初始化*/
   	   $(function(){
	   		treeManager=$("#tree1").ligerTree({ 
	 		   data:[],
	 		   textFieldName:"name",
	            idFieldName :"id",
	            parentIDFieldName :"pid",
	            topParentIDValue:"-1",
	            nodeWidth:600
		 	});
		    f_change_template(0);
   	   });
   	   /**获取tree初始值*/
       function getData(){
        	 var treedata=[];
        	 var menusJson=${menusJson};//菜单列表JSON [{menu1},{menu2}...]
			 treedata=treedata.concat(menusJson);
        	 return treedata;
         }
         /**展开折叠*/
         function f_toggle(flag){
        	 if(treeManager){
        		 if(flag==0){
            		 treeManager.collapseAll();
            	 }else{
            		 treeManager.expandAll();
            	 }
        	 }
         }
         /**选择模版*/
         function f_change_template(obj){
        	 if(obj.value!=""){
        		 var datas=getData();
        		 treeManager.clear();
        		 treeManager.setData(datas);
        	 }
         }
    </script>
</head>

<body>
 <form class="l-form validate">
    <div class="content">
    	<ul>
    		<li>
    			<label>用户组名称：</label>
    			<input type="text"  name="role.name" id="name" class="required search-input-text" />
    		</li>
    	</ul>
    	<ul>
    		<li>
    			<label>简介：</label>
    			<textarea name="role.remark" rows="8" cols="50" maxlength="800"></textarea>
    		</li>
    	</ul>
    	<input type="hidden"  name="role.templateId" id="templateId" value="0" />
    	<ul>
    		<li>
    			<label>级别:</label>
    			<input type="radio"  name="role.gradeType" value="0" />系统管理员
    			<input type="radio"  name="role.gradeType" value="1" />专技司管理员
    			<input type="radio"  name="role.gradeType" value="2" />二级机构管理员
    			<input type="radio"  name="role.gradeType" value="3" checked="checked" />三级业务管理员
    		</li>
    	</ul>
    	
    	<!-- 
    	<ul>
    		<li>
    			<label>项目:</label>
    			<input type="radio"  name="role.projectType" value="0"/>系统管理
    			<input type="radio"  name="role.projectType" value="1" checked="checked" />高研班项目
    			<input type="radio"  name="role.projectType" value="2" />基地项目
    			<input type="radio"  name="role.projectType" value="3" />急需人才项目
    			<input type="radio"  name="role.projectType" value="4" />岗位培训项目
    		</li>
    	</ul>
    	 -->
    	 
    </div>
    </form>
    <a class="l-button" onclick="f_toggle(0)">折叠</a>
    <a class="l-button" onclick="f_toggle(1)">展开</a>
    <div id="treeDiv" style="width:80%; height:80%; margin:1px 10px 10px; padding:10px 50px 10px; float:left; border:1px solid #ccc; overflow:auto;  ">
    	<ul id="tree1"></ul>
    </div> 
    <div style="display:none">
    </div>
</body>
</html>