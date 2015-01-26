var goods_grid = null;
var goods_toolbar = {
	items : [ {
		line : true
	}, {
		text : "增加",
		click : goods_add,
		icon : "add"
	}, {
		line : true
	} ]
};

$(function() {
	var g = {
		gridid : "zsgx_goods_grid",
		condition : "goodsCondition",
		url : basePath+"zsgx/goods!data.action",
		formid : "zsgx_goods_form",
		toolbar : goods_toolbar,
		checkbox : false,
		columns : [
				{
					display : "操作",
					name : "id",
					render : function(item) {
						var html = "<a href='javascript:;' onclick='goods_delete(\""
								+ item.id
								+ "\")' class='linkbutton'>删除</a>";
						html += "<a href='javascript:;' onclick='goods_edit(\""
								+ item.id
								+ "\")' class='linkbutton'>修改</a>";
						return html;
					}
				},
				{
					display : "进货",
					name : "id",
					render : function(item) {
						
						var html = "<a href='javascript:;' onclick='goods_purchase(\""
							+ item.id
							+ "\")' class='linkbutton'>进货</a>";
						html += "<a href='javascript:;' onclick='goods_purchase_record(\""
							+ item.id
							+ "\")' class='linkbutton'>进货记录</a>";
						return html;
					}
				},
				{
					display : "销售",
					name : "id",
					render : function(item) {
						
						var html = "<a href='javascript:;' onclick='goods_sales(\""
							+ item.id
							+ "\")' class='linkbutton'>销售</a>";
						html += "<a href='javascript:;' onclick='goods_sales_record(\""
							+ item.id
							+ "\")' class='linkbutton'>销售记录</a>";
						return html;
					}
				},
				// columns start
				{
					display : "商品名称",
					name : "name"
				},
				{
					display : "商品图片",
					name : "img",
					render : function(item) {
						var html = "<a href="+basePath+item.img+" data-lightbox='image-"+item.id+"/'>查看</a>";
						return html;
					}
				},
				{
					display : "库存",
					name : "stock",
					render : function(item) {
						var html = item.stock;
						if(item.stock < 1){
							html = "<font color='red'>0</font>";
						}
						return html;
					}
				},
				{
					display : "简介",
					name : "introduction"
				}
		]
	};
	goods_grid = showGrid(g);
});

function goods_search() {
	goods_grid.loadData();
}

function goods_delete(id) {
	confirm("删除操作", "确认删除吗？", function() {
		$.getJSON(basePath+"zsgx/goods!delete.action?goods.id=" + id,function(json) {
			if (json.success) {
				goods_grid.loadData();
			}else{
				alert(json.message,"error");
			}
		});
	});
}
function goods_purchase(id) {
	showDialog({
		title : "进货",
		name : "zsgx_goods_edit_window",
		url : basePath+"zsgx/goods!toGoodsPurchase.action?goods.id="+id,
		width : 400,
		height : 200,
		onok : function(item, dialog){
			gg("zsgx_goods_edit_window").contentWindow.goods_edit_submit(dialog);
		}
	});
}
function goods_sales(id) {
	showDialog({
		title : "销售",
		name : "zsgx_goods_edit_window",
		url : basePath+"zsgx/salesRecord!toGoodsSales.action?salesRecord.goodsId="+id,
		width : 500,
		height : 300,
		onok : function(item, dialog){
			gg("zsgx_goods_edit_window").contentWindow.goods_edit_submit(dialog);
		}
	});
}
function goods_purchase_record(id) {
	window.location.href = basePath+"zsgx/purchaseRecord!index.action?purchaseRecordCondition.goodsId="+id;
}
function goods_sales_record(id) {
	window.location.href = basePath+"zsgx/salesRecord!index.action?salesRecordCondition.goodsId="+id;
}

function goods_add() {
	showDialog({
		title : "新建",
		name : "zsgx_goods_add_window",
		url : basePath+"zsgx/goods!toAdd.action",
		onok : function(item, dialog){
			gg("zsgx_goods_add_window").contentWindow.goods_add_submit(dialog);
		}
	});
}

function goods_edit(id) {
	showDialog({
		title : "编辑",
		name : "zsgx_goods_edit_window",
		url : basePath+"zsgx/goods!toEdit.action?goods.id="+id,
		onok : function(item, dialog){
			gg("zsgx_goods_edit_window").contentWindow.goods_edit_submit(dialog);
		}
	});
}
