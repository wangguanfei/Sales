var purchaseRecord_grid = null;
var purchaseRecord_toolbar = {
	items : []
};

$(function() {
	var g = {
		gridid : "zsgx_purchaseRecord_grid",
		condition : "purchaseRecordCondition",
		url : basePath+"zsgx/purchaseRecord!data.action",
		formid : "zsgx_purchaseRecord_form",
		toolbar : purchaseRecord_toolbar,
		checkbox : false,
		columns : [
				// columns start
				{
					display : "商品",
					name : "goodsId",
					render : function(item) {
						return item.goods.name;
					}
				},
				{
					display : "进货数量",
					name : "goodsNum"
				},
				{
					display : "进货价(元)",
					name : "purchasePrice"
				},
				{
					display : "总支出(元)",
					name : "totalMoney"
				},
				{
					display : "进货时间",
					name : "purchaseDateStr"
				}
		]
	};
	purchaseRecord_grid = showGrid(g);
});

function purchaseRecord_search() {
	purchaseRecord_grid.loadData();
}

function purchaseRecord_delete(id) {
	confirm("删除操作", "确认删除吗？", function() {
		$.getJSON(basePath+"zsgx/purchaseRecord!delete.action?purchaseRecord.id=" + id,function(json) {
			if (json.success) {
				purchaseRecord_grid.loadData();
			}else{
				alert(json.message,"error");
			}
		});
	});
}

function purchaseRecord_add() {
	showDialog({
		title : "新建",
		name : "zsgx_purchaseRecord_add_window",
		url : basePath+"zsgx/purchaseRecord!toAdd.action",
		onok : function(item, dialog){
			gg("zsgx_purchaseRecord_add_window").contentWindow.purchaseRecord_add_submit(dialog);
		}
	});
}

function purchaseRecord_edit(id) {
	showDialog({
		title : "编辑",
		name : "zsgx_purchaseRecord_edit_window",
		url : basePath+"zsgx/purchaseRecord!toEdit.action?purchaseRecord.id="+id,
		onok : function(item, dialog){
			gg("zsgx_purchaseRecord_edit_window").contentWindow.purchaseRecord_edit_submit(dialog);
		}
	});
}
