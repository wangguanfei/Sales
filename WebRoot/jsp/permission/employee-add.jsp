<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/jsp/inc/meta.inc" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/jsp/inc/head.inc" %>
    <script type="text/javascript">
    	 function isMobile(){
			  var v =$("#phone").val();
			  if(/^1\d{10}$/g.test(v))  //1开头后面10位
			   {     
			     return false;
			   }
			   else
			   {
			    alert("请输入正确的手机号码");  
			    $("#phone").val("");
			    $("#phone").focus();
			    return true;
			   }
			  return false;
		}
		
		//电话、传真验证
		function isTel(vid)
		{
			//国家代码(2到3位)-区号(2到3位)-电话号码(7到8位)-分机号(3位)"
			var s = $("#"+vid).val();
			var pattern =/^(([0\+]\d{2,3}-)?(0\d{2,3})-)(\d{7,8})(-(\d{3,}))?$/;
			//var pattern =/(^[0-9]{3,4}\-[0-9]{7,8}$)|(^[0-9]{7,8}$)|(^\([0-9]{3,4}\)[0-9]{3,8}$)|(^0{0,1}13[0-9]{9}$)/; 
		     if(s!="")
		     {
		         if(!pattern.exec(s))
		         {
		          alert("请输入正确的号码");
		          $("#"+vid).val("");
		          $("#"+vid).focus();
		          return true;
		         }
		     }
		     return false;
		}
		 function initSelect(lev,sid){
         	var regionId=0;
         	var pid=0;
         	if(lev == 2){
         		regionId = $("#regionId").val();
         	}else if(lev == 3){
         		pid = $("#org2").val();
         		$("#orgId").val(pid);
         	}
         	$.ajax({
         		url : "${basePath}zsgx/organization!queryOrgInit.action",
         		type : "post",
         		data : "regionId="+regionId+"&pid="+pid,
         		dataType : "json",
         		success : function(json){
         		    var selHtml = "<option value='0'>请选择</option>";
         			$.each(json, function(i, n){
						  selHtml += "<option value='"+n.id+"'>"+n.orgName+"</option>";
					});
         			$("#"+sid).html(selHtml);
         		}
         	});
         }
         
         function initValue(org){
         	var v = $("#"+org).val();
         	$("#orgId").val(v);
         }
         function f_submit(dialog){
         	if(isMobile()){return;}//手机验证
         	if(isTel("tel")){return;}//电话
         	if(isTel("fax")){return;} //传真
         	if($("#orgId").val() == ""){alert("请选择机构");return;}
        	if($("form.validate").valid()){
	        	var param=$("form.validate").serialize();
	        	$.getJSON("${basePath}permission/employee!add.action", param,function(json){
	        		if(json.success){
	        			home_fresh();
					}else{
						alert(json.message,"error");
					}
				});
			}  
          }
    </script>
</head>

<body>
 <form class="l-form validate">
 <div style="display:none;"><!-- 附加  样式的问题 -->
 	<input type="text" />
 	<input type="password"/>
 </div>
    <div class="content">
    	<ul>
    		<li>
				<label>角色：</label>
    			<select name="employee.roleId" class="required input-select">
    				<option></option>
    				<c:forEach items="${roleList }" var="item">
    					<option value="${item.id }">${item.name }</option>
    				</c:forEach>
    			</select>
    		</li>
    	</ul>
    	<c:if test="${userRoleType != null && userRoleType != 0 }">
    		<ul>
	    		<li>
	    			<input type="hidden"  name="employee.orgId" id="orgId" value=""/>
	    			<c:choose>
	    				<c:when test="${userRoleType == 2 }">
	    					<label>机构：</label>
	    					<c:if test="${employeeSession.organization != null && employeeSession.organization.region != null}"><label>${employeeSession.organization.region.regionName} ></label> </c:if>
		    				<c:if test="${employeeSession.organization != null}"><label>${employeeSession.organization.orgName}></label></c:if>
			    			<select id="org3" class="input-select" onchange="initValue('org3');">
			    				<c:forEach items="${orgList}" var="organization">
			    					<option value="${organization.id}">${organization.orgName}</option>
			    				</c:forEach>
			    			</select>
	    				</c:when>
	    				<c:otherwise>
	    					<label>行政地域：</label>
			    			<select id="regionId" class="input-select" onchange="initSelect('2','org2')">
			    				<option value="0">请选择</option>
			    				<c:forEach items="${regionList}" var="region">
			    					<option value="${region.id}">${region.regionName}</option>
			    				</c:forEach>
			    			</select>
							<label>二级机构：</label>
			    			<select id="org2" class="input-select" onchange="initValue('org2');">
			    				<option value="0">请选择</option>
			    			</select>
	    				</c:otherwise>
	    			</c:choose>
	    			</li>
	    	</ul>
    	</c:if>
    	
    	<!-- admin 特殊处理 -->
    	<c:if test="${employeeSession.isadmin ==1}">
    		<ul>
	    		<li>
	    			<input type="hidden"  name="employee.orgId" id="orgId" value=""/>
   					<label>行政地域：</label>
	    			<select id="regionId" class="input-select" onchange="initSelect('2','org2')">
	    				<option value="0">请选择</option>
	    				<c:forEach items="${regionList}" var="region">
	    					<option value="${region.id}">${region.regionName}</option>
	    				</c:forEach>
	    			</select>
					<label>二级</label>
	    			<select id="org2" class="input-select" onchange="initSelect('3','org3');">
	    				<option value="0">请选择</option>
	    			</select>
	    			<label>三级</label>
	    			<select id="org3" class="input-select" onchange="initValue('org3');">
	    				<option value="0">请选择</option>
	    			</select>
    			</li>
	    	</ul>
    	</c:if>
    	
    	<ul>
    		<li>
    			<label>用户名：</label>
    			<input type="text"  name="employee.name" id="name" class="required search-input-text"/>
    		</li>
    	</ul>
    	<ul>
    		<li>
    			<label>联系人姓名：</label>
    			<input type="text"" name="employee.linkman" id="linkman" class="required search-input-text">
    		</li>
    	</ul>
    	<ul>
    		<li>
    			<label>办公电话：</label>
    			<input type="text" name="employee.tel" id="tel" class="required search-input-text">
    		</li>
    	</ul>
    	<ul>
    		<li>
    			<label>手机：</label>
    			<input type="text" name="employee.phone" id="phone" class="required search-input-text">
    		</li>
    	</ul>
    	<ul>
    		<li>
    			<label>邮箱：</label>
    			<input type="text" name="employee.email" id="email" class="required email search-input-text">
    		</li>
    	</ul>
    	<ul>
    		<li>
    			<label>传真：</label>
    			<input type="text" name="employee.fax" id="fax" class="required search-input-text">
    		</li>
    	</ul>
    </div>
    </form>
</body>
</html>