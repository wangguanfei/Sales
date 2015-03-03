var statistics_grid = null;
var statistics_toolbar = {
	items : [ {
		line : true
	}, {
		text : "增加",
		click : statistics_add,
		icon : "add"
	}, {
		line : true
	} ]
};

$(function() {
	var g = {
		gridid : "zsgx_statistics_grid",
		condition : "statisticsCondition",
		url : basePath+"zsgx/statistics!data.action",
		formid : "zsgx_statistics_form",
		toolbar : statistics_toolbar,
		checkbox : false,
		columns : [
				{
					display : "操作",
					name : "id",
					render : function(item) {
						var html = "<a href='javascript:;' onclick='statistics_delete(\""
								+ item.id
								+ "\")' class='linkbutton'>删除</a>";
						html += "<a href='javascript:;' onclick='statistics_edit(\""
								+ item.id
								+ "\")' class='linkbutton'>修改</a>";
						return html;
					}
				},
				// columns start
				{
					display : "年份",
					name : "syear"
				},
				{
					display : "月份",
					name : "smonth"
				},
				{
					display : "日",
					name : "sday"
				},
				{
					display : "总收入",
					name : "sin"
				},
				{
					display : "总利润",
					name : "sprofit"
				}
		]
	};
	statistics_grid = showGrid(g);
});

function statistics_search() {
	statistics_grid.loadData();
}

function statistics_delete(id) {
	confirm("删除操作", "确认删除吗？", function() {
		$.getJSON(basePath+"zsgx/statistics!delete.action?statistics.id=" + id,function(json) {
			if (json.success) {
				statistics_grid.loadData();
			}else{
				alert(json.message,"error");
			}
		});
	});
}

function statistics_add() {
	showDialog({
		title : "新建",
		name : "zsgx_statistics_add_window",
		url : basePath+"zsgx/statistics!toAdd.action",
		onok : function(item, dialog){
			gg("zsgx_statistics_add_window").contentWindow.statistics_add_submit(dialog);
		}
	});
}

function statistics_edit(id) {
	showDialog({
		title : "编辑",
		name : "zsgx_statistics_edit_window",
		url : basePath+"zsgx/statistics!toEdit.action?statistics.id="+id,
		onok : function(item, dialog){
			gg("zsgx_statistics_edit_window").contentWindow.statistics_edit_submit(dialog);
		}
	});
}
