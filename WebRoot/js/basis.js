/**
 * public
 */
/*根绝ID获得页面元素*/
function gg(id) {
	return document.getElementById(id);
};
/*刷新父页面-子窗口调用*/
function home_fresh() {
	//window.top.frames["home"].location.reload();
	window.parent.location.reload();
}
/*关闭子窗口-子窗口调用*/
function closeWindow(){
	$(".l-dialog,.l-window-mask",parent.document).remove();
}
/*隐藏子窗口-子窗口调用*/
function hideWindow(){
	$(".l-dialog,.l-window-mask",parent.document).hide();
}
/** 
 * 关闭所有弹出框
 */
function closeAllBox(){
	$(".l-messagebox").remove();
	$(".l-window-mask").remove();
}
/*表单验证*/
$(function () {
	// <form class="validate"></form>
	$('form.validate').validate({
		errorPlacement: function(error, element) {
			error.appendTo(element.parents("li:first"));
		}
	});
});
/*显示表格列表*/
function showGrid(grid){
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
	var _headerRowHeight = grid.headerRowHeight||23;//默认表头高度
	if(grid.delayLoad){
		_delayLoad=true;
	}
	//if(isNull(_headerRowHeight)){
	//	headerRowHeight = 23;
	//}
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
	        height:"100%",
	        rownumbers:_rownumbers,//是否显示行序号
	        delayLoad:_delayLoad,//初始化是是否不加载
	        headerRowHeight:_headerRowHeight,
	        fixedCellHeight:false,
	        switchPageSizeApplyComboBox: true,     //切换每页记录数是否应用ligerComboBox
	        root:'data',//返回json格式数组的key
	        record:'recordSize',//总记录数
	        pageParmName:_conditionname+'.pageNo', //页索引参数名，(提交给服务器)
	        pagesizeParmName:_conditionname+'.pageSize',//页记录数参数名，(提交给服务器)
	        sortnameParmName: _conditionname+'.sortname',//页排序列名(提交给服务器)
	        sortorderParmName: _conditionname+'.sortorder',//页排序方向(提交给服务器)
	        pageSize: 20, //每页默认的结果数
	        page:1, //默认当前页 
	        pageSizeOptions: [10,20, 50], //可选择设定的每页结果数
	        selectRowButtonOnly: true //复选框模式时，是否只允许点击复选框才能选择行
    	});
	return _grid;
}
/*弹出窗口*/
function showWindow(win){
	if(!win)return null;
	var _url=win.url||"";
	var _title=win.title||"";
	var _name=win.name||"";
	var _height=win.height||500;
	var _width=win.width||850;
	var _buttons=win.buttons||null;
	
    $.ligerDialog.open({ 
    	url:_url,//地址
    	title:_title ,//标题
    	name:_name, //窗口名称
    	height:_height,//窗口高度 默认是500
    	width: _width, //窗口宽度 默认是700
    	buttons:_buttons,//按钮数组
    	
    	showMax: true,//是否显示最大化按钮 
        showToggle: true,//显示收缩窗口按钮
    	isResize:true,// 允许调整大小
    	timeParmName:'r_m', //是否给URL后面加上值为new Date().getTime()的参数，如果需要指定一个参数名即可
    	isHidden:false, //关闭对话框时是否只是隐藏，还是销毁对话框
    	slide:true//是否启动动画效果
    });
}
/**
 * 获得grid编辑行的编辑数据
 * @param grid 必填
 * @param rowid 必填
 * @returns {Array}
 */
function getGridEditData(grid,rowid){
	var row = grid.getRow(rowid);
 	var editdata=[];
	var editors=grid.editors[row["__id"]];
	 
	 for(var columnid in editors){
		 var o = editors[columnid];
		 var val=o.editor.getValue(o.input, o.editParm);
		 var column = o.editParm.column;
		 editdata[column.name]=val;
	 }
	 return editdata;
}
/*弹出确认框*/
function confirm(title,message,callback){
	$.ligerMessageBox.confirm(title,message, function (yes){
        if(yes&&callback&&typeof(callback)=="function"){
			callback();
		}
    });
}
/*弹出提示对话框*/
function alert(content,type,callback){
	var _callback=null;
	var _type=null;
	if(typeof(type)=="string"){
		_type=type;
		_callback=callback;
	}else if(typeof(type)=="function"){
		_callback=type;
		_type=null;
	}
	if(_callback)$.ajaxSetup({ async: false});
	if(!_type){
		return $.ligerMessageBox.alert("提示",content,"",_callback);
	}
	switch (_type)
    {
        case "success":
        	$.ligerMessageBox.success("成功",content,_callback);
            break;
        case "warn":
        	$.ligerMessageBox.warn("警告",content,_callback);
            break;
        case "question":
        	$.ligerMessageBox.question("问题",content,_callback);
            break;
        case "error":
        	$.ligerMessageBox.error("错误",content,_callback);
            break;
    }
	if(_callback)$.ajaxSetup({ async: true});
}





