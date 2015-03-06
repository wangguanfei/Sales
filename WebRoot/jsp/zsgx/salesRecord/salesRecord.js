var salesRecord_grid = null;
var salesRecord_toolbar = {
	items : [ ]
};

$(function() {
	var g = {
		gridid : "zsgx_salesRecord_grid",
		condition : "salesRecordCondition",
		url : basePath+"zsgx/salesRecord!data.action",
		formid : "zsgx_salesRecord_form",
		toolbar : salesRecord_toolbar,
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
					display : "客户姓名",
					name : "customerId",
					render : function(item) {
						if(item.customer != null){
							return item.customer.name;
						}else{
							return "";
						}
						
					}
				},
				{
					display : "销售数量",
					name : "salesNumber"
				},
				{
					display : "销售价格(元)",
					name : "salesPrice"
				},
				{
					display : "商品进价(元)",
					name : "purchasePrice"
				},
				{
					display : "销售时间",
					name : "salesDateStr"
				},
				{
					display : "销售总额(元)",
					name : "income"
				},
				{
					display : "利润(元)",
					name : "profit"
				},
				{
					display : "备注",
					name : "remain"
				}
		]
	};
	salesRecord_grid = showGrid(g);
});

function salesRecord_delete(id) {
	confirm("删除操作", "确认删除吗？", function() {
		$.getJSON(basePath+"zsgx/salesRecord!delete.action?salesRecord.id=" + id,function(json) {
			if (json.success) {
				salesRecord_grid.loadData();
			}else{
				alert(json.message,"error");
			}
		});
	});
}

function salesRecord_add() {
	showDialog({
		title : "新建",
		name : "zsgx_salesRecord_add_window",
		url : basePath+"zsgx/salesRecord!toAdd.action",
		onok : function(item, dialog){
			gg("zsgx_salesRecord_add_window").contentWindow.salesRecord_add_submit(dialog);
		}
	});
}

function salesRecord_edit(id) {
	showDialog({
		title : "编辑",
		name : "zsgx_salesRecord_edit_window",
		url : basePath+"zsgx/salesRecord!toEdit.action?salesRecord.id="+id,
		onok : function(item, dialog){
			gg("zsgx_salesRecord_edit_window").contentWindow.salesRecord_edit_submit(dialog);
		}
	});
}
