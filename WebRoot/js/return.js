// JavaScript Document

//返回顶部
window.onload=function(){
var av_height = $(window).height();
var av_width = $(window).width();
var go_top= $("#go_top");
var Gotop_w = go_top.width()+2;
var Gotop_h = go_top.height()+2;
var doc_width =1000;
var Gotop_x = (av_width>doc_width?0.5*av_width+0.5*doc_width:av_width-Gotop_w);
var Gotop_y = av_height-Gotop_h;
var ie6Hack = "<style>.go_top{position:absolute; top:expression(documentElement.scrollTop+documentElement.clientHeight - this.offsetHeight-40);</style>}";
if ($.browser.msie && ($.browser.version == "6.0")){
$("body").append(ie6Hack);
}
function setGotop(){
av_height = $(window).height();
av_width = $(window).width();
Gotop_y = av_height-Gotop_h-40;
Gotop_x = (av_width>doc_width?0.5*av_width+0.5*doc_width:av_width-Gotop_w);
if($(window).scrollTop()>0){
go_top.fadeIn(200);
}else{
go_top.fadeOut(200);
}
if ($.browser.msie && ($.browser.version == "6.0")){
go_top.animate({"left":Gotop_x},0);
return false;
}
go_top.animate({"left":Gotop_x,"top":Gotop_y},0);
}
setGotop();
$(window).resize(function(){
setGotop();
})
$(window).scroll(function(){
setGotop();
})
go_top.click(function(){
$("html , body").animate({scrollTop:"0"},100);
})



}
