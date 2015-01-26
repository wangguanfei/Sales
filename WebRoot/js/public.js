// JavaScript Document



function hide(cover,id){
	var objCover=document.getElementById(cover);
	var objId=document.getElementById(id);
	objCover.style.display="none";
	objId.style.display="none";
}

function show(cover,id){
	var index = $('div').find("id*='div_'").length;	
	alert(index);

	var showBoxCenter = function() {
		var objCover=document.getElementById(cover);
		var objId=document.getElementById(id);
		objCover.style.display="block";
		objId.style.display="block";
		var scrollW=document.documentElement.scrollWidth;
		var scrollH=document.documentElement.scrollHeight;
		var T=(document.documentElement.clientHeight-objId.clientHeight)/2;
		var L=(document.documentElement.clientWidth-objId.clientWidth)/2+document.documentElement.scrollLeft;
		objCover.style.width=scrollW+"px";
		objCover.style.height=scrollH+"px";
		objId.style.top=T+"px";
		objId.style.left=L+"px";
	};
	showBoxCenter();
	window.onresize = function() {
		showBoxCenter();
	};
}

//滑动门
function setTab(name,cursel,n){
 for(i=1;i<=n;i++){
  var menu=document.getElementById(name+i);
  var con=document.getElementById("con_"+name+"_"+i);
  menu.className=i==cursel?"hover":"";
  con.style.display=i==cursel?"block":"none";
 }
}
//显示隐藏
function nav(id,sun){
	var show=document.getElementById(id);
	var showsun=document.getElementById(sun);
		showsun.style.display="block";
}
function navout(id,sun){
	var show=document.getElementById(id);
	var showsun=document.getElementById(sun);
		showsun.style.display="none";
}


//学科页面 鼠标滑过老师名字 教师列表功能显示、隐藏效果
$(function(){
	$('#t-pushdown').mouseover(function(){
		$(this).removeClass('t-arrow-down').addClass('t-arrow-up');
		$('#t-pushdown-box').show();
   });
	$('#t-pushdown').mouseout(function(){
		$(this).removeClass('t-arrow-up').addClass('t-arrow-down');							   
		$('#t-pushdown-box').hide();							 
   });

})

//日历js显示隐藏  加课和 申请
$(function(){
	$('.top_open').hover(function(){
		$(this).children("a").addClass('top_on').removeClass('csbg');
		$(this).children("ul").stop(true,true).show()
	},function(){
		$(this).children("a").removeClass('top_on').addClass('csbg');
		$(this).children("ul").hide()
	});
})








