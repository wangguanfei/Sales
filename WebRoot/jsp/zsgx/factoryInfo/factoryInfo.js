var factoryInfo_grid = null;
var factoryInfo_toolbar = {
	items : [ {
		line : true
	}, {
		text : "增加",
		click : factoryInfo_add,
		icon : "add"
	}, {
		line : true
	} ]
};

$(function() {
	var g = {
		gridid : "zsgx_factoryInfo_grid",
		condition : "factoryInfoCondition",
		url : basePath+"zsgx/factoryInfo!data.action",
		formid : "zsgx_factoryInfo_form",
		toolbar : factoryInfo_toolbar,
		checkbox : false,
		columns : [
				{
					display : "操作",
					name : "id",
					render : function(item) {
						var html = "<a href='javascript:;' onclick='factoryInfo_delete(\""
								+ item.id
								+ "\")' class='linkbutton'>删除</a>";
						html += "<a href='javascript:;' onclick='factoryInfo_edit(\""
								+ item.id
								+ "\")' class='linkbutton'>修改</a>";
						return html;
					}
				},
				// columns start
				{
					display : "名称",
					name : "name"
				},
				{
					display : "电话",
					name : "phone"
				},
				{
					display : "地址",
					name : "address"
				},
				{
					display : "备注",
					name : "remain"
				}
		]
	};
	factoryInfo_grid = showGrid(g);
});

function factoryInfo_search() {
	factoryInfo_grid.loadData();
}

function factoryInfo_delete(id) {
	confirm("删除操作", "确认删除吗？", function() {
		$.getJSON(basePath+"zsgx/factoryInfo!delete.action?factoryInfo.id=" + id,function(json) {
			if (json.success) {
				factoryInfo_grid.loadData();
			}else{
				alert(json.message,"error");
			}
		});
	});
}

function factoryInfo_add() {
	showDialog({
		title : "新建",
		name : "zsgx_factoryInfo_add_window",
		url : basePath+"zsgx/factoryInfo!toAdd.action",
		onok : function(item, dialog){
			gg("zsgx_factoryInfo_add_window").contentWindow.factoryInfo_add_submit(dialog);
		}
	});
}

function factoryInfo_edit(id) {
	showDialog({
		title : "编辑",
		name : "zsgx_factoryInfo_edit_window",
		url : basePath+"zsgx/factoryInfo!toEdit.action?factoryInfo.id="+id,
		onok : function(item, dialog){
			gg("zsgx_factoryInfo_edit_window").contentWindow.factoryInfo_edit_submit(dialog);
		}
	});
}
