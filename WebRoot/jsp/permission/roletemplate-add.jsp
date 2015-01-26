<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/jsp/inc/meta.inc" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/jsp/inc/head.inc" %>
    <script type="text/javascript">
    var treeManager=null;
         function f_submit(dialog){
        	//alert(JSON2.stringify(treeManager.getChecked()));
        	if($("form.validate").valid()){
        		var datas=treeManager.getChecked();
        		var menus1={};//一级菜单
    			var menus2={};//二级菜单
        		if(datas){
        			for(var i in datas){
        				var dt=datas[i];
        				if(dt.data.degree==3){
        					menus1[dt.data.ppid]=dt.data.ppid;
        					if(menus2[dt.data.pid]){
        					}else{
        						menus2[dt.data.pid]=0;
        					}
        					menus2[dt.data.pid]=menus2[dt.data.pid]+dt.data.bit;
        				}
        			}
        		}
	        	var param=$("form.validate").serialize();
	        	var index=0;
	        	for(var i in menus1){
	        		param=param+"&roletemplateMenuList["+ index +"].menuId="+i;
	        		index++;
	        	}
	        	for(var i in menus2){
	        		param=param+"&roletemplateMenuList["+ index +"].menuId="+i+"&roletemplateMenuList["+ index +"].roleBit="+menus2[i];
	        		index++;
	        	}
	        	//alert(param);
	        	$.getJSON("${basePath}permission/roletemplate!add.action", param,function(json){
	        		if(json.success){
	        			home_fresh();
					}else{
						alert(json.message,"error");
					}
				});
			} 
          }
         $(function (){
        		 var menusJson=${menusJson};//菜单列表 [{menu1},{menu2}...]
        		 var crud=[];
				 for(var i in menusJson){
					 var data=menusJson[i];
					 if(data.degree&&data.degree==2){
						 //json[i]["isexpand"]="false";
						 var pid=data.id;
						 var ppid=data.pid;
		            	 crud.push({id:pid+"-create" ,pid:pid, ppid:ppid, name: "新增",degree:3, bit:2});
		            	 crud.push({id:pid+"-read" , pid:pid, ppid:ppid, name: "查询",degree:3, bit:16});
		            	 crud.push({id:pid+"-update" , pid:pid, ppid:ppid, name: "修改",degree:3, bit:8});
		            	 crud.push({id:pid+"-delete" , pid:pid, ppid:ppid, name: "删除",degree:3, bit:4});
					 }
				 }
				 menusJson=menusJson.concat(crud);
				// alert(JSON2.stringify(datas));
        		 treeManager=$("#tree1").ligerTree({ 
	        		   data:menusJson,
	        		   textFieldName:"name",
	                   idFieldName :"id",
	                   parentIDFieldName :"pid",
	                   topParentIDValue:"-1",
	                   nodeWidth:600
	        	 });
         });
         function f_toggle(flag){
        	 if(flag==0){
        		 treeManager.collapseAll();
        	 }else{
        		 treeManager.expandAll();
        	 }
         }
    </script>
</head>

<body>
 <form class="l-form validate">
    <div class="content">
    	<ul>
    		<li>
    			<label>用户权限模板名称：</label>
    			<input type="text"  name="roletemplate.name" id="name" class="required search-input-text" />
    		</li>
    	</ul>
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