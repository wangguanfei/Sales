//全局变量，高亮的信息-1表示没有选中  
var highlightindex = -1;  
//延时处理  
var timeout; 

function queryPoint(url,wordText,paperId){
	//和服务器端交互
	var param;
	if(paperId){
		param={"condition.keyword":wordText,"condition.exampaperId":paperId,"random":Math.random()};
	}else{
		param={"condition.keyword":wordText,"random":Math.random()};
	}
    $.post(url,param,function(data){  
    	showResult(data);
	},"json");
}

function showResult(data){
	var div = $("#auto"); 
	div.html("");
	if(data!=null){
		for(var i=0;i<data.length;i++){
	        var newDiv = $("<div>").attr("id",i).attr("name",data[i].id);  
	        newDiv.html(data[i].name).appendTo(div);  
	        newDiv.mouseover(function(){  
	            if(highlightindex!=-1){  
	                $("#auto").children("div").eq(highlightindex).css("background-color","white");  
	            }  
	            highlightindex = $(this).attr("id");  
	            $(this).css("background-color","blue");  
	        }); 
	        newDiv.mouseout(function(){  
	            $(this).css("background-color","white");  
	        });  
	        newDiv.click(function(){  
	            $("#auto").hide();  
	            var textClick = $("#auto").children("div").eq(highlightindex).text();
	            var idClick=$("#auto").children("div").eq(highlightindex).attr("name");
	            highlightindex = -1;  
	            $("#word").val(textClick);  
	            $("#pointId").val(idClick);
	        });  
	    }  
	    if(data.length > 0){  
	        div.show();  
	    }else{                        
	        div.hide();  
	        highlightindex = -1;
	    }  
	}
}

function initSuggest(url,paperId){  
    var wordInput = $("#word");  
    //获得JQUERY封装的一个对象  
    var wordInputOffset = wordInput.position(); 
    //设置DIV为隐藏，并设置边框属性  
   $("#auto").css("border","1px black solid")  
                    .css("position","relative")  
                    //input离上面的长度+input的高（宽）  
                    .css("top",eval(wordInputOffset.top)+"px")  
                    //input高左面的的长度  
                    .css("left",eval(wordInputOffset.left)+"px")  
                    //input的宽度  
                    .width(eval(wordInput.width()+6)+"px");  
   $("#auto").hide();
   //添加事件  
   $("#word").keyup(function(event){  
       var myEvent = event || window.event;  
       var myKeyCode = myEvent.keyCode;  
       //给页面的DIV做一个缓存  
       var div = $("#auto");         
       //获得文件框里面的值       
       if(myKeyCode>64 && myKeyCode<91 || myKeyCode==8 || myKeyCode==46){      
           var wordText = $("#word").val(); 
           if(wordText!=""){  
        	   queryPoint(url,wordText,paperId);
           }else{  
               //如果文本信息为空就隐藏  
               div.hide();  
               highlightindex = -1;  
           }  
             
           //键盘向上向下键  
       }else if(myKeyCode == 38 || myKeyCode ==40){  
           //向上  
           if(myKeyCode == 38){  
               //获得ID为auto里面的DIV对象  
               var autoNodes = $("#auto").children("div");  
               //如果没有被选中  
               if(highlightindex != -1){  
                   //设置CSS  
                   autoNodes.eq(highlightindex).css("background-color","white");  
                   highlightindex--;  
               }else{                    
                   highlightindex = autoNodes.length - 1;  
               }  
               autoNodes.eq(highlightindex).css("background-color","blue");  
           }     
           //向下  
           if(myKeyCode == 40){  
               var autoNodes = $("#auto").children("div");  
               if(highlightindex != -1){  
                   autoNodes.eq(highlightindex).css("background-color","white");  
                   highlightindex++;  
                }else{  
                    highlightindex++;  
                }  
                if(highlightindex == autoNodes.length){  
                    highlightindex = 0;  
                }  
                autoNodes.eq(highlightindex).css("background-color","blue");  
            }  
              
            //回车键  
        }else if(myKeyCode ==13){  
            if(highlightindex!=-1){  
                //获得选中的那个的文本值  
                var textInputText = $("#auto").children("div").eq(highlightindex).text();  
                highlightindex = -1;  
                $("#word").val(textInputText);    
                $("#auto").hide();        
            }else{                
                $("#auto").hide();  
                //失去焦点  
                $("#word").get(0).blur();  
            }  
        }  
    });  
}
