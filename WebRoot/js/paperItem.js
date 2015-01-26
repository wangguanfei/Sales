 /**显示试题编号 **/
       function displayItemNum(items){
			var singleOptionNums =$("#itemnums").html();
			var mutipleOptionNums = "";
			var hasSingleOptionSymbol = false;
			var hasMultipleOptionSymbol = false;
			for(i=0;i<items.length;i++){
				var itemId=items[i].id;
				var itemType = items[i].itemType;
				if(itemType == 1){//单选
					if(!hasSingleOptionSymbol){
						singleOptionNums +="单选题<br><div id='singleOptionDiv'>";
						hasSingleOptionSymbol = true;
					}
					var singleOptionAttrStr = "";
					if(i== 0){
						singleOptionAttrStr = " class='selectitembutton' ";
					}else{
						singleOptionAttrStr = " class='itembutton'  ";
					}
					singleOptionNums+="<input type='button' name='item"+i+"' id='"+itemId+"' " +  singleOptionAttrStr +" onclick=selectItem(this) value='"+items[i].num+"'/>";
				}else if(itemType == 2){//不定项选择
					if(!hasMultipleOptionSymbol){
						
						if(hasSingleOptionSymbol){
							mutipleOptionNums = "..............................................................";
						}
						mutipleOptionNums += "<br>不定项选择题<br><div id='mutipleOptionDiv'>"
						hasMultipleOptionSymbol = true;
					}
					var mutipleOptionAttrStr = "";
					if(i== 0){
						mutipleOptionAttrStr = " class='selectitembutton' ";
					}else{
						mutipleOptionAttrStr = " class='itembutton' ";
					}
					mutipleOptionNums +="<input type='button' name='item"+i+"' id='"+itemId+"' " + mutipleOptionAttrStr + " onclick=selectItem(this) value='"+items[i].num+"'/>";
				}
				
			}
			singleOptionNums +="</div>";
			mutipleOptionNums +="</div>";
			$("#itemnums").html(singleOptionNums + mutipleOptionNums);
		}
       
       /**选择试题**/
       function selectItem(ele){
    	    $("#itemMoveUpButton").attr("disabled","");
    	    $("#itemMoveDownButton").attr("disabled","");
    	    //上移
    	    var _currEle = $(ele);
    	    var _prevEle=_currEle.prev("input");
      		var _parentDivId = _currEle.parent().attr("id");
      		if(_parentDivId == "mutipleOptionDiv" && _prevEle.length==0){
      			var _hasSingleDiv = $("#singleOptionDiv");
      			if(_hasSingleDiv.length){//存在单项选择
      				_prevEle = $("#singleOptionDiv input.itembutton").last();
      			}
      		}
      		
     		if(_parentDivId == 'singleOptionDiv'){
     			
     			if(_prevEle.length==0){
     				//disabled
      				$("#itemMoveUpButton").attr("disabled","disabled");
     			}else{
      				//abled
     				$("#itemMoveUpButton").attr("disabled","");
      			}
    		}else {
      			
     			if(_parentDivId =='mutipleOptionDiv' ){
     				var _singleDiv = $("#singleOptionDiv");
      				if(_singleDiv.length==0 && _prevEle.length ==0){
     					//disabled
      					$("#itemMoveUpButton").attr("disabled","disabled");
    				}else{
      					//abled
     					$("#itemMoveUpButton").attr("disabled","");
      				}
      			}
    		}
    	    
     		var nextElement=_currEle.next("input");
     		var _curParentDivId = _currEle.parent().attr("id");//当前
     		if(_curParentDivId == 'singleOptionDiv'){//当前是单项选择
     			
     			var _curHasMutipleDivId = $("#mutipleOptionDiv");
     			if(_curHasMutipleDivId.length == 0 && nextElement.length == 0 ){//不存在不定项选择
     				$("#itemMoveDownButton").attr("disabled","disabled");
     			}else{
     				$("#itemMoveDownButton").attr("disabled","");
     			}
     		}else{
     			//var _curHasSingleDivId = $("#singleOptionDiv");
     			if( nextElement.length == 0 ){
     				$("#itemMoveDownButton").attr("disabled","disabled");
     			}else{
     				$("#itemMoveDownButton").attr("disabled","");
     			}
     		}
     		
     		
//      		var _parentDivId = nextElement.parent().attr("id");
//      		if(_parentDivId == "singleOptionDiv" && nextElement.length == 0 ){
//      			var _hasMutipleDiv = $("#mutipleOptionDiv");
//      			if(_hasMutipleDiv.length){//存在不定项选择题
//      				nextElement = $("#mutipleOptionDiv input.itembutton").first();
//      			}
//      		}
//      		
//      		if(_parentDivId == 'mutipleOptionDiv'){
//      			
//      			if(nextElement.length==0){
//      				//disabled
//      				$("#itemMoveDownButton").attr("disabled","disabled");
//      			}else{
//      				//abled
//      				$("#itemMoveDownButton").attr("disabled","");
//      			}
//      		}else {
//      			
//      			if(_parentDivId =='singleOptionDiv' ){
//      				var _mutilDiv = $("#mutipleOptionDiv");
//      				if(_mutilDiv.length==0 && nextElement.length ==0){
//      					//disabled
//      					$("#itemMoveDownButton").attr("disabled","disabled");
//      				}else{
//      					//abled
//      					$("#itemMoveDownButton").attr("disabled","");
//      				}
//      			}
//      		}
    	    
     		
     		
       		$("input.selectitembutton").attr("class","itembutton");
       		$(ele).attr("class","selectitembutton");
       		var url=basePath+"/exampaper/zk/exampaperitem!item.action";
			var param="exampaperItem.id="+$(ele).attr("id")+"&random="+Math.random();
			$("#itemframe").attr("src",url+"?"+param);
       }
       
       /**显示试题**/
       function displayItem(json){
			$("#itemframe").contents().find("#itemcontent").html(json.data.content);
			$("#itemframe").contents().find("#itemcontent").css("display","block");
			$("#itemframe").contents().find("#itemedit").css("display","block");
			$("#itemframe").contents().find("#itemedit").html("<a href='javascript:void(0);' onclick=window.parent.f_editItem('"+json.data.id+"')>编辑</a>");
			//$("#itemframe").contents().find("head").html(changeStyle(json.data.exampaperId));

       }
       
       /**查询选项**/
       function initOptionList(itemId){
       		var url=basePath+"/exampaper/zk/exampaperoption!list.action";
			var param="condition.exampaperItemId="+itemId+"&random="+Math.random();
       		$.getJSON(url, param,function(json){
				if(json){
					displayOption(json);
				}
			});
       }
       
       /**显示选项*/
       function displayOption(json){
       		var options="";
       		var paperType=$("#paperType").val();
       		for(var i=0;i<json.length;i++){
       			options+="<div class='optionOperate' style='width:650px;position:absolute;left:20px;top:"+eval((145*i)+5)+"px;z-index:10;font-size:12px;text-align:right;'>";
       			options+="<input type='hidden' name='optionId' value='"+json[i].id+"'/>";
       			options+="<a href='javascript:void(0);' onclick='window.parent.optionMoveUp(this)' class='optionMoveUp'>上移</a>&nbsp;&nbsp;";
       			options+="<a href='javascript:void(0);' onclick='window.parent.optionMoveDown(this)' class='optionMoveDown'>下移</a>&nbsp;&nbsp;";
       			options+="<a href='javascript:void(0);' onclick='window.parent.f_deleteOption(this)'>删除</a>&nbsp;&nbsp;";
       			options+="<a href='javascript:void(0);' onclick=window.parent.f_editOption('"+json[i].id+"');>编辑选项</a>&nbsp;&nbsp;";
       			if((paperType=='1'&&json[i].isAnswer=='1')||paperType=='2'){
       				options+="<a href='javascript:void(0);' onclick=window.parent.f_editPoint('"+json[i].id+"');>编辑知识点</a>";
       			}
       			options+="</div>";
       			options+="<div class='option' style='position:absolute;left:0px;top:"+eval(145*i)+"px;width:650px;height:100px;border:solid 1px black;padding:15px;'>";
       			options+="<span class='optioncode'>"+json[i].code+"</span>.<span class='optioncontent' id='"+json[i].id+"'>"+json[i].content+"</span>";
       			options+="</div>";
       		}
       		$("#itemframe").contents().find("#itemoption").html(options);
       }
       
       
       /**更改试卷样式**/
       function changeStyle(paperId){
       		var url=basePath+"/exampaper/zk/paperstyle!item.action";
			var param="condition.exampaperpaperId="+paperId+"&random="+Math.random();
       		$.getJSON(url, param,function(json){
				if(json.data){
					return json.data.content;
				}
			});
       }

       
        $(document).ready(function(){
        	var url=basePath+"/exampaper/zk/exampaperitem!list.action";
        	var paperId=$("#paperId").val();
			var param="condition.exampaperId="+paperId+"&random="+Math.random();
			$.getJSON(url, param,function(json){
				if(json){
					displayItemNum(json);
				}
			});
        });
	

		/**删除*/
		function f_deleteItem() {
			confirm("删除操作", "确认删除吗？", function() {
				var currEle=$("input.selectitembutton");
				$.getJSON(basePath+"/exampaper/zk/exampaperitem!delete.action?exampaperItem.id=" + currEle.attr("id")+"&random="+Math.random(),
						function(json) {
							if (json.success) {
								currEle.nextAll("input.itembutton").each(function(){
									$(this).val(parseInt($(this).val())-1);
								});
								currEle.remove();
								clearItem();
							}else{
								alert(json.message,"error");
							}
						});
			});
		}

		/**清空试题及选项内容*/
        function clearItem(){
        	$("#itemframe").contents().find("#itemedit").html("");
      		$("#itemframe").contents().find("#itemcontent").html("");
      		$("#itemframe").contents().find("#itemoption").html("");
        }
       
       /**试题上移*/
       function itemMoveUp(){
    	  
    	   //$("#itemMoveDownButton").attr("disabled","");
       		var currEle=$("input.selectitembutton");
       		disabledMoveUpButtonChooseItem(currEle);
       		var prevEle=currEle.prev("input.itembutton");
       		var parentDivId = currEle.parent().attr("id");
       		if(parentDivId == "mutipleOptionDiv" && prevEle.length==0){
       			var hasSingleDiv = $("#singleOptionDiv");
       			if(hasSingleDiv.length){//存在单项选择
       				prevEle = $("#singleOptionDiv input.itembutton").last();
       			}
       		}
//       		var prevPrevEle = prevEle.prev("input.itembutton");
//       		if(parentDivId == 'singleOptionDiv'){
//       			
//       			if(prevPrevEle.length==0){
//       				//disabled
//       				$("#itemMoveUpButton").attr("disabled","disabled");
//       			}else{
//       				//abled
//       				$("#itemMoveUpButton").attr("disabled","");
//       			}
//       		}else {
//       			
//       			if(parentDivId =='mutipleOptionDiv' ){
//       				var singleDiv = $("#singleOptionDiv");
//       				if(singleDiv.length==0 && prevPrevEle.length ==0){
//       					//disabled
//       					$("#itemMoveUpButton").attr("disabled","disabled");
//       				}else{
//       					//abled
//       					$("#itemMoveUpButton").attr("disabled","");
//       				}
//       			}
//       		}
       		
       		if(prevEle.length){
       			$.getJSON(basePath+"/exampaper/zk/exampaperitem!changeNum.action?exampaperItem.id=" + currEle.attr("id")+"&exampaperItem2.id="+prevEle.attr("id")+"&random="+Math.random(),
				function(json) {
					if (json.success) {
						var prevEleId=prevEle.attr("id");
						prevEle.attr("class","selectitembutton");
						prevEle.attr("id",currEle.attr("id"));
						currEle.attr("class","itembutton");
						currEle.attr("id",prevEleId);
					}else{
						alert(json.message,"error");
					}
				});
       		}else{
       			alert("已经是第一题了，不能向上移了!");
       		}
       		
       }
       
       /**选择试题时,设置上移按钮的状态:可用,不可用*/
       function disabledMoveUpButtonChooseItem(_currentEle,_type){
    	   $("#itemMoveDownButton").attr("disabled","");
      		//var _currEle=$("input.selectitembutton");
    	   
    	    var _currEle = "";
    	    if(_type){
    	    	 _currEle = $(_currentEle);
     	   }else{
     		     _currEle = _currentEle;
     	   }
      		var _prevEle=_currEle.prev("input.itembutton");
      		var _parentDivId = _currEle.parent().attr("id");
      		if(_parentDivId == "mutipleOptionDiv" && _prevEle.length==0){
      			var _hasSingleDiv = $("#singleOptionDiv");
      			if(_hasSingleDiv.length){//存在单项选择
      				_prevEle = $("#singleOptionDiv input.itembutton").last();
      			}
      		}
      		var _prevPrevEle = _prevEle.prev("input.itembutton");
     		if(_parentDivId == 'singleOptionDiv'){
     			
     			if(_prevPrevEle.length==0){
     				//disabled
      				$("#itemMoveUpButton").attr("disabled","disabled");
     			}else{
      				//abled
     				$("#itemMoveUpButton").attr("disabled","");
      			}
    		}else {
      			
     			if(_parentDivId =='mutipleOptionDiv' ){
     				var _singleDiv = $("#singleOptionDiv");
      				if(_singleDiv.length==0 && _prevPrevEle.length ==0){
     					//disabled
      					$("#itemMoveUpButton").attr("disabled","disabled");
    				}else{
      					//abled
    					//如果只有一个,则为disabled
      					if(_prevPrevEle.length != 0 ){//
      						$("#itemMoveUpButton").attr("disabled","");
      					}else{
      						var _ItemButtonLens = _singleDiv.children("input.itembutton");
          					if(_ItemButtonLens.length <2 && _currEle.prev("input.itembutton").length == 0 ){
          						$("#itemMoveUpButton").attr("disabled","disabled");
          					}else{
          						$("#itemMoveUpButton").attr("disabled","");
          					}
      					}
     					
      				}
      			}
    		}
       }
       
       /**选择试题时,设置下移按钮的状态:可用,不可用*/
       function disabledMoveDownButtonChooseItem(_downEle,_type){
    	   
    	    $("#itemMoveUpButton").attr("disabled","");
      		//var currElement=$("input.selectitembutton");
    	    var currElement = "";
    	    if(_type){
    	    	 currElement = $(_downEle);
    	    }else{
    	    	currElement = _downEle;
    	    }
      		var nextElement=currElement.next("input.itembutton");
      		var _parentDivId = currElement.parent().attr("id");
      		if(_parentDivId == "singleOptionDiv" && nextElement.length == 0 ){
      			var _hasMutipleDiv = $("#mutipleOptionDiv");
      			if(_hasMutipleDiv.length){//存在不定项选择题
      				nextElement = $("#mutipleOptionDiv input.itembutton").first();
      			}
      		}
      		var nextNextElement = nextElement.next("input.itembutton");
      		if(_parentDivId == 'mutipleOptionDiv'){
      			
      			if(nextNextElement.length==0){
      				//disabled
      				$("#itemMoveDownButton").attr("disabled","disabled");
      			}else{
      				//abled
      				$("#itemMoveDownButton").attr("disabled","");
      			}
      		}else {
      			
      			if(_parentDivId =='singleOptionDiv' ){
      				var _mutilDiv = $("#mutipleOptionDiv");
      				
      				
      				if(_mutilDiv.length==0 && nextNextElement.length ==0){
      					//disabled
      					$("#itemMoveDownButton").attr("disabled","disabled");
      				}else{
      					
      					//abled
      					//如果只有一个,则为disabled
      					if(nextNextElement.length != 0 ){//存在下一个元素
      						$("#itemMoveDownButton").attr("disabled","");
      					}else{
      						var _ItemButtonLen = _mutilDiv.children("input.itembutton");
          					if(_ItemButtonLen.length <2 && currElement.next("input.itembutton").length == 0){
          						$("#itemMoveDownButton").attr("disabled","disabled");
          					}else{
          						$("#itemMoveDownButton").attr("disabled","");
          					}
      					}
      					
      					
      				}
      			}
      		}
    	   
       }
       /**试题下移*/
       function itemMoveDown(){
    	    // $("#itemMoveUpButton").attr("disabled","");
       		var currEle=$("input.selectitembutton");
       		disabledMoveDownButtonChooseItem(currEle);
       		var nextEle=currEle.next("input.itembutton");
       		var parentDivId = currEle.parent().attr("id");
       		if(parentDivId == "singleOptionDiv" && nextEle.length == 0 ){
       			var hasMutipleDiv = $("#mutipleOptionDiv");
       			if(hasMutipleDiv.length){//存在不定项选择题
       				nextEle = $("#mutipleOptionDiv input.itembutton").first();
       			}
       		}
//       		var nextNextEle = nextEle.next("input.itembutton");
//       		if(parentDivId == 'mutipleOptionDiv'){
//       			
//       			if(nextNextEle.length==0){
//       				//disabled
//       				$("#itemMoveDownButton").attr("disabled","disabled");
//       			}else{
//       				//abled
//       				$("#itemMoveDownButton").attr("disabled","");
//       			}
//       		}else {
//       			
//       			if(parentDivId =='singleOptionDiv' ){
//       				var mutilDiv = $("#mutipleOptionDiv");
//       				if(mutilDiv.length==0 && nextNextEle.length ==0){
//       					//disabled
//       					$("#itemMoveDownButton").attr("disabled","disabled");
//       				}else{
//       					//abled
//       					$("#itemMoveDownButton").attr("disabled","");
//       				}
//       			}
//       		}
       		if(nextEle.length){
       			$.getJSON(basePath+"/exampaper/zk/exampaperitem!changeNum.action?exampaperItem.id=" + currEle.attr("id")+"&exampaperItem2.id="+nextEle.attr("id")+"&random="+Math.random(),
				function(json) {
					if (json.success) {
						var nextEleId=nextEle.attr("id");
						nextEle.attr("class","selectitembutton");
						nextEle.attr("id",currEle.attr("id"));
						currEle.attr("class","itembutton");
						currEle.attr("id",nextEleId);
					}else{
						alert(json.message,"error");
					}
				});
       		}else{
       			alert("已经是最后一题了，不能向下移了!");
       		}
       		
       }
       
       /**删除选项*/
       function f_deleteOption(currEle){
       		confirm("删除操作", "确认删除吗？", function() {
				$.getJSON(basePath+"/exampaper/zk/exampaperoption!delete.action?exampaperOption.id=" + $(currEle).parent().children("input[name=optionId]").val()+"&random="+Math.random(),
						function(json) {
							if (json.success) {
								$(currEle).parent().nextAll("div.option").each(function(){
									$(this).children(".optioncode").html(String.fromCharCode($(this).children(".optioncode").html().charCodeAt(0)-1));
								//	$(this).css("top",($(this).position().top-145)+"px");
								//	$(this).prev("div").css("top",($(this).prev("div").position().top-145)+"px");

								});
								$(currEle).parent().next(".option").remove();
								$(currEle).parent().remove();
							}else{
								alert(json.message,"error");
							}
						});
			});
       }
       
       
       /**选项上移*/
       function optionMoveUp(currEle){
       		var prevOperateDiv=$(currEle).parent().prev().prev("div.optionOperate");
       		var currOperateDiv=$(currEle).parent();
       		if(prevOperateDiv.length){
       			$.getJSON(basePath+"/exampaper/zk/exampaperoption!changeCode.action?exampaperOption.id=" + $(currEle).parent().children("input[name=optionId]").val()+"&exampaperOption2.id="+$(prevOperateDiv).children("input[name=optionId]").val()+"&random="+Math.random(),
				function(json) {
					if (json.success) {
						var optionOperateDivHtml=currOperateDiv.html();
						var optionDivHtml=currOperateDiv.next("div.option").children(".optioncontent").html();
						currOperateDiv.html(prevOperateDiv.html());
						$(currOperateDiv).next("div.option").children(".optioncontent").html(prevOperateDiv.next("div.option").children(".optioncontent").html());
						$(prevOperateDiv).html(optionOperateDivHtml);
						$(prevOperateDiv).next("div.option").children(".optioncontent").html(optionDivHtml);

					}else{
						alert(json.message,"error");
					}
				});
       		}else{
       			alert("已经是第一个选项了，不能向上移了!");
       		}
       		
       }
       
       /**选项下移*/
       function optionMoveDown(currEle){
       		var nextOperateDiv=$(currEle).parent().next().next("div.optionOperate");
       		var currOperateDiv=$(currEle).parent();
       		if(nextOperateDiv.length){
       			$.getJSON(basePath+"/exampaper/zk/exampaperoption!changeCode.action?exampaperOption.id=" + $(currEle).parent().children("input[name=optionId]").val()+"&exampaperOption2.id="+$(nextOperateDiv).children("input[name=optionId]").val()+"&random="+Math.random(),
				function(json) {
					if (json.success) {
						var optionOperateDivHtml=currOperateDiv.html();
						var optionDivHtml=currOperateDiv.next("div.option").children(".optioncontent").html();
						currOperateDiv.html(nextOperateDiv.html());
						$(currOperateDiv).next("div.option").children(".optioncontent").html(nextOperateDiv.next("div.option").children(".optioncontent").html());
						$(nextOperateDiv).html(optionOperateDivHtml);
						$(nextOperateDiv).next("div.option").children(".optioncontent").html(optionDivHtml);

					}else{
						alert(json.message,"error");
					}
				});
       		}else{
       			alert("已经是最后一个选项了，不能向下移了!");
       		}
       		
       }
       
       /**分析试卷*/
   	function parserPaper(json){
   		var url=basePath+"/exampaper/zk/zkexampaper!parsePaper.action";
   		var paperId=$("#paperId").val();
   		var oldWordName=$("#oldWordName").val();
   		var wordPath=$("#wordPath").val();
   		var newWordName=$("#newWordName").val();
   		var param="paperStyle.paperId="+paperId+"&paperStyle.oldFilename="+oldWordName+"&paperStyle.htmlPath="+json.htmlPath+"&paperStyle.wordPath="+wordPath+"&paperStyle.htmlName="+json.htmlName+"&paperStyle.wordName="+newWordName+"&random="+Math.random();
   		$.getJSON(url, param,function(json){
   			if(json){
   				if(json.success){
   					displayItemNum(json.data);
   				}else{
   					alert(json.message);
   				}
   			}
   		});
   	}
	
	/**转换word格式到html*/
	function transferHtml(result){
		var url=basePath+"/exampaper/zk/zkexampaper!convertPaper.action";
   		var param="wordPaper.newWordFilename="+result.data.newfilename+"&wordPaper.filepath="+result.data.filepath+"&random="+Math.random();
   		$.getJSON(url, param,function(json){
   			if(json){
   				if(json.success){
   					parserPaper(json.data);
   				}else{
   					alert(json.message);
   				}
   			}
   		});
	}
	
	
	
	/**编辑试题*/
	function f_editItem(itemId) {
		var win = {
			title : "编辑试题",
			name : "paper-item-edit-window",
			url : basePath+"/exampaper/zk/exampaperitem!toEdit.action?exampaperItem.id=" + itemId,
			width:750,
			height:400,
			buttons : [ {
				text : "确定",onclick : function(item, dialog) {
					gg("paper-item-edit-window").contentWindow.f_submit(dialog);
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
	
	/**编辑选项*/
	function f_editOption(optionId) {
		var win = {
			title : "编辑选项",
			name : "paper-option-edit-window",
			url : basePath+"/exampaper/zk/exampaperoption!toEdit.action?exampaperOption.id=" + optionId,
			width:750,
			height:400,
			buttons : [ {
				text : "确定",onclick : function(item, dialog) {
					gg("paper-option-edit-window").contentWindow.f_submit(dialog);
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
	
	/**编辑知识点*/
	function f_editPoint(optionId) {
		var win = {
			title : "编辑知识点",
			name : "option-point-edit-window",
			url : basePath+"exampaper/zk/optionpoint!toEdit.action?condition.exampaperOptionId=" + optionId,
			width:750,
			height:400,
			buttons : [ {
				text : "确定",onclick : function(item, dialog) {
					gg("option-point-edit-window").contentWindow.f_submit(dialog);
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