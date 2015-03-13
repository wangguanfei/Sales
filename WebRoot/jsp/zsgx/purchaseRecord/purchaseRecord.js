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
				{
					display : "操作",
					name : "id",
					render : function(item) {
						/*var html = "<a href='javascript:;' onclick='purchaseRecord_delete(\""
								+ item.id
								+ "\")' class='linkbutton'>删除</a>";*/
						var	html = "<a href='javascript:;' onclick='purchaseRecord_edit(\""
								+ item.id
								+ "\")' class='linkbutton'>修改</a>";
						if(item.purchaseStatus == 0){
							html += "<a href='javascript:;' onclick='purchaseRecord_invoicing(\""
								+ item.id
								+ "\")' class='linkbutton'>结账</a>";
						}
						
						return html;
					}
				},
				// columns start
				{
					display : "商品名称",
					name : "goodsId",
					render : function(item) {
						if(item.goods != null){
							return item.goods.name;
						}else{
							return "已下架";
						}
						
					}
				},
				{
					display : "厂家名称",
					name : "factoryId",
					render : function(item) {
						if(item.factoryInfo != null){
							return item.factoryInfo.name;
						}else{
							return "已删除";
						}
						
					}
				},
				{
					display : "是否结账",
					name : "purchaseStatus",
					render : function(item) {
						if(item.purchaseStatus == 0){
							return "<font color='red'>未结账</font>";
						}else{
							return "已结账";
						}
						
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
				},
				{
					display : "备注",
					name : "remain"
				}
		]
	};
	purchaseRecord_grid = showGrid(g);
});



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
		width : 400,
		height : 200,
		name : "zsgx_purchaseRecord_edit_window",
		url : basePath+"zsgx/purchaseRecord!toEdit.action?purchaseRecord.id="+id,
		onok : function(item, dialog){
			gg("zsgx_purchaseRecord_edit_window").contentWindow.purchaseRecord_edit_submit(dialog);
		}
	});
}
