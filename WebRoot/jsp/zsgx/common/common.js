/*******************************************************************************
 * 
 * @author wqtan create 2013-7-20 14:19
 */

// 空值判断
function isNull(obj) {
	var result = true;
	var type = typeof (obj);
	/* undefined or null return false */
	if (type == "undefined" || obj == null) {
		result = true;
	}
	/* type is string */
	else if (type == "string") {
		obj = $.trim(obj);
		if (obj == "" || obj == "undefined") {
			result = true;
		} else {
			result = false;
		}
	}
	/* other object */
	else {
		result = false;
	}
	return result;
}

function startWith(source,str) {
	if(isNull(str) || isNull(source)){
		return false;
	}
	if(source.substr(0,str.length)==str){
		return true;
	} else {
		return false;
	}
}

function endWith(source,str) {
	if(isNull(str) || isNull(source)){
		return false;
	}
	if(source.substr(source.length-str.length)==str){
		return true;
	} else {
		return false;
	}
}

/**
 * 执行一个js方法，可以是一个方法名，也可以是一个js方法对象
 * 
 * @param fun :
 *            js方法
 * @return 执行后的返回值
 */
function evalFunction(fun, obj, params) {
	var funType = typeof (fun);
	if (funType == "function") {
		fun.apply(obj, params);
	} else if (funType == "string") {
		eval(fun, params);
	}
}

/**
 * 弹出一个层
 * 
 * @param opts
 * url : 弹出的页面地址 
 * width : 宽度 
 * height : 高度 
 * confirm : 是否带有确认取消按钮 
 * title : 标题 
 * name : 弹出页包含的iframe名称 
 * onok : 执行确认的回调 
 * oncancel : 执行取消的回调
 */
function showDialog(opts) {
	if (isNull(opts.width)) {
		opts.width = 800;
	}
	if (isNull(opts.height)) {
		opts.height = 600;
	}
	if (isNull(opts.confirm)) {
		opts.confirm = true;
	}
	var win = {
		title : opts.title,
		name : opts.name,
		url : opts.url,
		width : opts.width,
		height : opts.height,
		buttons : [ {
			text : "确定",
			onclick : function(item, dialog) {
				if (!isNull(opts.onok)) {
					evalFunction(opts.onok,this,[item,dialog]);
				} else {
					$("#"+opts.name).contentWindow.f_submit(dialog);
				}
			}
		}, {
			text : "取消",
			onclick : function(item, dialog) {
				if (!isNull(opts.oncancel)) {
					evalFunction(opts.oncancel,this,[item,dialog]);
				} else {
					dialog.close();
				}
			}
		} ]
	};
	if(opts.confirm==false){
		win.buttons=null;
	}
	showWindow(win);
}



/**
 * 弹出一个层
 * 
 * @param opts
 * url : 弹出的页面地址 
 * width : 宽度 
 * height : 高度 
 * confirm : 是否带有确认取消按钮 
 * title : 标题 
 * name : 弹出页包含的iframe名称 
 * onok : 执行确认的回调 
 * oncancel : 执行取消的回调
 */
function showDialoglook(opts) {
	if (isNull(opts.width)) {
		opts.width = 800;
	}
	if (isNull(opts.height)) {
		opts.height = 600;
	}
	if (isNull(opts.confirm)) {
		opts.confirm = true;
	}
	var win = {
		title : opts.title,
		name : opts.name,
		url : opts.url,
		width : opts.width,
		height : opts.height,
		buttons : [{
			text : "取消",
			onclick : function(item, dialog) {
				if (!isNull(opts.oncancel)) {
					evalFunction(opts.oncancel,this,[item,dialog]);
				} else {
					dialog.close();
				}
			}
		} ]
	};
	showWindow(win);
}



/**
 * 弹出一个层
 * 
 * @param opts
 * content : 弹出的页面地址 
 * width : 宽度 
 * height : 高度 
 * confirm : 是否带有确认取消按钮 
 * title : 标题 
 * name : 弹出页包含的iframe名称 
 * onok : 执行确认的回调 
 * oncancel : 执行取消的回调
 */
function showDialogByContent(opts){
	if (isNull(opts.width)) {
		opts.width = 800;
	}
	if (isNull(opts.height)) {
		opts.height = 600;
	}
	if (isNull(opts.confirm)) {
		opts.confirm = true;
	}
	
    $.ligerDialog.open({ 
    	content:opts.content,//内容
    	title:opts.title ,//标题
    	name:opts.name, //窗口名称
    	height:opts.height,//窗口高度 默认是500
    	width: opts.width, //窗口宽度 默认是700
		buttons : [ {
			text : "确定",
			onclick : function(item, dialog) {
				if (!isNull(opts.onok)) {
					evalFunction(opts.onok,this,[item,dialog]);
				} else {
					$("#"+opts.name).contentWindow.f_submit(dialog);
				}
			}
		}, {
			text : "取消",
			onclick : function(item, dialog) {
				if (!isNull(opts.oncancel)) {
					evalFunction(opts.oncancel,this,[item,dialog]);
				} else {
					dialog.close();
				}
			}
		} ],
    	showMax: true,//是否显示最大化按钮 
        showToggle: true,//显示收缩窗口按钮
    	isResize:true,// 允许调整大小
    	timeParmName:'r_m', //是否给URL后面加上值为new Date().getTime()的参数，如果需要指定一个参数名即可
    	isHidden:false, //关闭对话框时是否只是隐藏，还是销毁对话框
    	slide:true//是否启动动画效果
    });
}

/**
 * 表单清空
 */
function clearForm(frm) {
	$(':input', frm).not(':button, :submit, :reset, :hidden').val('')
			.removeAttr('checked').removeAttr('selected');
}

/**
 * 表单提交
 * @param frm 表单
 * @param _url 提交地址
 * @param onok 提交成功的回调
 * @param onerror 提交失败的回调
 */
function commonFrmSubmit(frm, _url,onok,onerror) {
	if ($(frm).valid()) {
		alert("<span style=\"width: 100%; display:inline-block;\">服务器处理中...<img src='/image/loading.gif' width='30' height='30' style=\" display:inline-block; position:relative; top:10px;\"/></span>");
		$.ajax({
			url : _url,
			type : "post",
			data : $(frm).serialize(),
			dataType : "json",
			success : function(json) {
				if (!isNull(onok)) {
					evalFunction(onok,this,[json]);
				} else {
					if (json.success) {
						home_fresh();
					} else {
						alert(json.message, "error");
					}
				}
			},
			error : function(){
				if (!isNull(onerror)) {
					evalFunction(onerror,this,[json]);
				} else {
					alert("网络错误，提交失败。");
				}
			}
		});
	}
}

function closeDialog(){
	$(".l-dialog").remove();
	$(".l-window-mask").remove();
	$(".l-messagebox").remove();
}


function showCommonGrid(grid){
	if(!grid)return null;
	var _gridid=grid.gridid; //必填
	var _columns=grid.columns; //必填
	var _conditionname=grid.condition||"condition";//查询条件condition的名称
	var _url=grid.url;//必填
	var _params=grid.params;//查询条件参数（可以是数组、function返回数组）
	var _formid=grid.formid;//查询条件--form表单id
	var _toolbar=grid.toolbar;//工具条
	var _checkbox=grid.checkbox||false;//是否有复选框
	var _columnWidth=grid.columnWidth;// 默认列宽度
	var _rowHeight=grid.rowHeight||22; //行默认的高度
	var _rownumbers=grid.rownumbers||false; //是否显示行序号
	var _delayLoad=false;//初始化是是否不加载    默认是加载
	var _usePager=grid.usePager;//
	var _headerRowHeight = grid.headerRowHeight?grid.headerRowHeight:23;
	var _onAfterShowData = grid.onAfterShowData;
	var _headerRowHeight = grid.headerRowHeight;
	
	if(isNull(_onAfterShowData)){
		_onAfterShowData = null;
	}
	if(isNull(_headerRowHeight)){
		headerRowHeight = 23;
	}
	if(grid.delayLoad){
		_delayLoad=true;
	}
	
	if(!_params&&_formid){
		_params=function(){return $("#"+_formid).serializeArray();};
	}
	var _grid=$("#"+_gridid).ligerGrid({
	        columns: _columns,//表头
	        url: _url,//服务器地址 返回json格式
	        parms:_params,//查询参数  可以是 function(返回数组)、数组  -------数组格式必须是[{name:'xxx',value,'yyy'},{name:'nnn',value,'mmm'}...]
	        toolbar:_toolbar,//工具条--在表头的上面
	        checkbox: _checkbox,                         //是否显示复选框 默认不显示
	        columnWidth:_columnWidth,// 默认列宽度
	        rowHeight:_rowHeight, //行默认的高度
	        rownumbers:_rownumbers,//是否显示行序号
	        delayLoad:_delayLoad,//初始化是是否不加载
	        headerRowHeight:_headerRowHeight,
	        fixedCellHeight:false,
	        usePager:_usePager,
	        switchPageSizeApplyComboBox: true,     //切换每页记录数是否应用ligerComboBox
	        root:'data',//返回json格式数组的key
	        record:'recordSize',//总记录数
	        pageParmName:_conditionname+'.pageNo', //页索引参数名，(提交给服务器)
	        pagesizeParmName:_conditionname+'.pageSize',//页记录数参数名，(提交给服务器)
	        sortnameParmName: _conditionname+'.sortname',//页排序列名(提交给服务器)
	        sortorderParmName: _conditionname+'.sortorder',//页排序方向(提交给服务器)
	        pageSize: 20, //每页默认的结果数
	        page:1, //默认当前页 
	        pageSizeOptions: [10,20, 50 ,100], //可选择设定的每页结果数
	        selectRowButtonOnly: true, //复选框模式时，是否只允许点击复选框才能选择行
	        onAfterShowData : _onAfterShowData,
	        headerRowHeight : _headerRowHeight,
	        enabledSort : false
    	});
	return _grid;
}

/**
 * 绑定上传事件
 * param : inputId 上传标记id，可以是input也可以是其他标签
 * param : onsuccess 上传成功以后的回调 event, file, data
 * param : opts
 */
function commonUpload(inputId,onsuccess,opts){
	var _html = "<div id='"+inputId+"-swfupload-control'>";
	_html +=  "		<div>";
	_html +=  "			<div style='float: left; line-height: 20px; margin-left: 10px;'>";
	_html += "				<input type='button' id='"+inputId+"-swfupload-button' />";
	if($("#"+inputId).attr("tagName")=="input"
		&& $("#"+inputId).attr("type")=="button"){
		_html += "				<input readonly='readonly' type='text' style='"+$("#"+inputId).attr("style")+"' id='"+inputId+"' name='"+$("#"+inputId).attr("name")+"' value='"+$("#"+inputId).val()+"'" 
					+	"class='"+$("#"+inputId).attr("class")+"' />";		
	}		
	_html += "				<span id='"+inputId+"Preview'></span>";
	_html += "				<div id='"+inputId+"-swfupload-progress' style='display: none; width: 100px; height: 0.75em; padding: 0pt; margin: 0pt 10px; border: 1px solid black; float:right;margin-top:4px'>";
	_html += "				<span style='background-color: green; font-size: 1pt; height: 100%; float: left;'></span>";
	_html += "				</div>";
	_html += "			</div>";
	_html += "			<span class='swfupload-error' id='"+inputId+"swfupload-error'></span>";
	_html += "		</div>";
	_html += "</div>";
	$("#"+inputId).hide();
	$("#"+inputId).after(_html);
	$("#"+inputId).remove();
	
	if(null==opts){
		opts = {};
	}
	if(null==opts.button_text
		&& null==opts.button_image_url){
		opts.button_image_url = basePath+"swfupload/swfupload/XPButtonUploadText_61x22.png";
	} else if(null!=opts.button_text){
		opts.button_image_url = basePath+"swfupload/swfupload/TestImageNoText_65x29.png";
	}
	if(null==opts.button_width){
		opts.button_width = 61;
	}
	if(null==opts.button_height){
		opts.button_height = 22;
	}
	if(null==opts.file_types){
		//opts.file_types = "*.jpg;*.jpeg;*.gif;*.png;*.bmp;";
		opts.file_types = "*.pdf";
	}
	if(null==opts.file_size_limit){
		opts.file_size_limit = "10240";
	}
	if(null==opts.uploadFolder){ //上传目录
        opts.uploadFolder = "upload/";
    }
	$("#"+inputId+"-swfupload-control").swfupload({
		upload_url: basePath+"upload.action",
		use_query_string : true,
		post_params:{"dir":opts.uploadFolder},
		file_size_limit : opts.file_size_limit,
		file_types : opts.file_types,
		file_types_description : "Files",
		file_upload_limit : "0",
		flash_url : basePath+"swfupload/swfupload.swf",
		button_image_url : opts.button_image_url,
		button_text : opts.button_text,
		button_text_style : opts.button_text_style,
		button_width:opts.button_width,
	    button_height:opts.button_height,
		button_text_left_padding: 12,
		button_text_top_padding: 3,
		button_placeholder_id : inputId+"-swfupload-button"
	})
	.bind("fileQueued", function(event, file){ 
		$(this).swfupload("startUpload"); 
	})
	.bind("fileQueueError", function(event, file, errorCode, message){
		alert("333333");
		//$(this).find("#"+inputId+"-swfupload-error").html(message + ',' + errorCode);
	})
	.bind("uploadStart", function(event, file){	
		$(this).find("#"+inputId+"-swfupload-progress").show().children("span").width(0); 
	})
	.bind("uploadProgress", function(event, file, bytesLoaded){	
		$(this).find("#"+inputId+"-swfupload-progress>span").animate({width:(100 * bytesLoaded / file.size) + "%" }, {duration: 500, queue: false}); 
	})
	.bind("uploadSuccess", function(event, file, _result){
		var result = _result;
		if(typeof(result)=="string"){
			eval("result = " + _result);
		}
		if(!isNull(onsuccess)){
			evalFunction(onsuccess,this,[event,file,result]);
		}else{
			if(result&&result.success){
				$("#"+inputId).val(result.data.file);
				$("#"+inputId+"Preview").html("<a href='"+basePath+result.data.file+"' target='_blank'>查看</a>");
			}else{
				$(this).find("#"+inputId+"-swfupload-error").html(result.message);
			}
		}
	})
	.bind("uploadComplete", function(event, file){ 
		$(this).find("#"+inputId+"-swfupload-progress").hide().children("span").width(0); $(this).swfupload("startUpload"); 
	})
	.bind("uploadError", function(event, file, errorCode, message){ 
		$(this).find("#"+inputId+"-swfupload-error").html(message + ',' + errorCode); 
	});
}

// 验证扩展
jQuery.validator.addMethod("largeThan", function(value, element,param) {
	return this.optional(element) || $(param).val()<value;
}, "结束时间要大于开始时间");
jQuery.validator.addMethod("largeEqThan", function(value, element,param) {
	return this.optional(element) || $(param).val()<=value;
}, "结束时间要大于开始时间");

Date.prototype.format = function(format){
	 var o = {
	             "M+" : this.getMonth()+1, //month
	             "d+" : this.getDate(), //day
	             "h+" : this.getHours(), //hour
	             "m+" : this.getMinutes(), //minute
	             "s+" : this.getSeconds(), //second
	             "q+" : Math.floor((this.getMonth()+3)/3), //quarter
	             "S" : this.getMilliseconds()//millisecond
	         };
   if(/(y+)/.test(format))
   format=format.replace(RegExp.$1,(this.getFullYear()+"").substr(4 - RegExp.$1.length));
   for(var k in o)
   if(new RegExp("("+ k +")").test(format))
   format = format.replace(RegExp.$1,RegExp.$1.length==1 ? o[k] : ("00"+ o[k]).substr((""+ o[k]).length));
   return format;
};
//使用方式
//var date = new Date();
//var dateFormat = date.format("yyyyMMddhhmmssS"); yyyy-MM-dd