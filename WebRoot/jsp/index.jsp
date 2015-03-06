<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/jsp/inc/meta.inc" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta name="title" content="艺奇模特管理后台系统"/>
    <meta name="application-name" content="艺奇模特管理后台系统" />
	<link rel="Shortcut Icon" href="${basePath}image/logo.ico"  type=”image/x-icon”/>
    <%@ include file="/jsp/inc/head.inc" %>
       <script type="text/javascript">
            var tabmanager = null;
            var accordion = null;
            var treeManager = null;
            $(function (){

                //布局
                $("#layout1").ligerLayout({ leftWidth: 200, height: '100%',heightDiff:-34,space:4, onHeightChanged: f_heightChanged });

                var height = $(".l-layout-center").height();

                //Tab
               tabmanager=$("#framecenter").ligerTab({ height: height });
				
                $(".l-link").hover(function (){
                    	$(this).addClass("l-link-over");
	                }, function (){
	                    $(this).removeClass("l-link-over");
	             });
                
			//加载菜单树
               treeManager=$("#leftMenu").ligerTree({ 
     	 		   data:[],
     	 		   textFieldName:"name",
     	           idFieldName :"id",
     	           parentIDFieldName :"pid",
     	           topParentIDValue:"-1",
     	           checkbox: false,
                   slide: false,
                   nodeWidth: 120,
	               onSelect: function (node){
	                    if (!node.data.url) return;
	                  	tabmanager.overrideSelectedTabItem({text:node.data.name,url:node.data.url,showClose:false});
	                   	return ;
	               }
     		   });
               //去后台Session里取当前的菜单列表
              $.ajaxSetup({ async: false});//同步开启
              $.getJSON("${basePath}permission/menu!currentAuthorityMenuList.action",function(menus){
  					if(menus&&menus.length){
  						treeManager.setData(menus);
  					}else{
  						alert("您尚未登录或登录超时，请先登录","warn",function(){
  							window.location.href="${basePath}login!index.action";
  						});
  					}
  			  });
              $.ajaxSetup({ async: true});//异步
              
              $("#pageloading").hide();
              
              $("#leftMenu").css("width","auto");

          });
            /**高度矫正*/
            function f_heightChanged(options){
            	tabmanager.addHeight(options.diff);
            }
            /**点击menu事件*/
            function f_addTab(tabid,text, url){ 
            	tabmanager.addTabItem({ tabid : tabid,text: text, url: url });
            } 
             
            
     </script> 
<style type="text/css"> 
    body,html{height:100%;width:100%;}
    body{ padding:0px; margin:0;   overflow:hidden;}  
    .l-link{ display:block; height:26px; line-height:26px; padding-left:10px; text-decoration:underline; color:#333;}
    .l-link2{text-decoration:underline; color:white; margin-left:2px;margin-right:2px;}
    .l-layout-top{background:#102A49; color:White;}
    .l-layout-bottom{ background:#E5EDEF; text-align:center;}
    #pageloading{position:absolute; left:0px; top:0px; background:white url('${basePath}ligerui/images/loading.gif') no-repeat center; width:100%; height:100%;z-index:99999;}
    .l-link{ display:block; line-height:22px; height:22px; padding-left:16px;border:1px solid white; margin:4px;}
    .l-link-over{ background:#FFEEAC; border:1px solid #DB9F00;} 
    .l-winbar{ background:#2B5A76; height:30px; position:absolute; left:0px; bottom:0px; width:100%; z-index:99999;}
    .space{ color:#E7E7E7;}
    /* 顶部 */ 
    .l-topmenu{ margin:0; padding:0; height:70px; line-height:31px; background:url(${basePath}image/topbg2.jpg) no-repeat left bottom #3699c2;  position:relative;}
   .l-topmenu img{ margin:6px 0px 0px 15px; }
    .l-topmenu-logo{ color:#E7E7E7; padding-left:0; line-height:26px;}
    .l-topmenu-welcome{  position:absolute; width:184px; height:24px; padding-left:50px; line-height:20px;  right:0px; top:0px; color:#070A0C; background:url(${basePath}image/admin.gif) no-repeat right top;}
    .l-topmenu-welcome a{ color:#E7E7E7; text-decoration:underline} 

 </style>
</head>

<body style="padding:0px;background:#EAEEF5;" >  
<div id="pageloading"></div>
<%@ include file="/jsp/inc/top.inc" %>
<div id="layout1" style="width:99.3%; margin:0 auto; margin-top:4px; "> 
		<div position="left"  title="艺奇模特" id="leftMenu" style="width:100%;">
			
		</div> 
        <div position="center" id="framecenter"> 
            <div tabid="home" title="<img src='${basePath}image/home.gif'/>&nbsp;主页" style="height:300px" >
                <iframe frameborder="0" name="home" id="home" src="${basePath}home.html"></iframe>
            </div>
        </div> 
    </div>
    
    <%@ include file="/jsp/inc/foot.inc" %>
        <div style="display:none">
    <!-- 流量统计代码 --> 
    </div>
</body>
</html>
