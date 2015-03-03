var customer_grid = null;
var customer_toolbar = {
	items : [ {
		line : true
	}, {
		text : "增加",
		click : customer_add,
		icon : "add"
	}, {
		line : true
	} ]
};

$(function() {
	var g = {
		gridid : "zsgx_customer_grid",
		condition : "customerCondition",
		url : basePath+"zsgx/customer!data.action",
		formid : "zsgx_customer_form",
		toolbar : customer_toolbar,
		checkbox : false,
		columns : [
				{
					display : "操作",
					name : "id",
					render : function(item) {
						var html = "<a href='javascript:;' onclick='customer_delete(\""
								+ item.id
								+ "\")' class='linkbutton'>删除</a>";
						html += "<a href='javascript:;' onclick='customer_edit(\""
								+ item.id
								+ "\")' class='linkbutton'>修改</a>";
						html += "<a href='javascript:;' onclick='customer_buy(\""
							+ item.id
							+ "\")' class='linkbutton'>购买记录</a>";
						return html;
					}
				},
				// columns start
				{
					display : "客户姓名",
					name : "name"
				},
				{
					display : "客户性别",
					name : "sex",
					render : function(item) {
						if(item.sex == "0"){
							return "男";
						}else{
							return "女";
						}
					}
				},
				{
					display : "客户电话",
					name : "phone"
				},
				{
					display : "客户地址",
					name : "address"
				},
				{
					display : "客户QQ",
					name : "qq"
				},
				{
					display : "消费金额(元)",
					name : "totalSpend"
				},
				{
					display : "备注",
					name : "remain"
				}
		]
	};
	customer_grid = showGrid(g);
});

function customer_search() {
	customer_grid.loadData();
}

function customer_delete(id) {
	confirm("删除操作", "确认删除吗？", function() {
		$.getJSON(basePath+"zsgx/customer!delete.action?customer.id=" + id,function(json) {
			if (json.success) {
				customer_grid.loadData();
			}else{
				alert(json.message,"error");
			}
		});
	});
}

function customer_add() {
	showDialog({
		title : "新建",
		name : "zsgx_customer_add_window",
		url : basePath+"zsgx/customer!toAdd.action",
		onok : function(item, dialog){
			gg("zsgx_customer_add_window").contentWindow.customer_add_submit(dialog);
		}
	});
}

function customer_edit(id) {
	showDialog({
		title : "编辑",
		name : "zsgx_customer_edit_window",
		url : basePath+"zsgx/customer!toEdit.action?customer.id="+id,
		onok : function(item, dialog){
			gg("zsgx_customer_edit_window").contentWindow.customer_edit_submit(dialog);
		}
	});

}

function customer_buy(id){
	window.location.href = basePath+ "zsgx/salesRecord!index.action?salesRecordCondition.customerId="+id;
}
