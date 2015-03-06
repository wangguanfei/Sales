<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
			<link href="/css/home.css" rel="stylesheet" type="text/css">
</head>
<!--  <body style="background:url(image/indexbgimg.jpg) repeat-x top left #fff; margin:0px;"> -->
<body style=" repeat-x top left #fff; margin:0px;">
	<div class="main">
		<table width="100%" cellspacing="0" cellpadding="0" border="0">
			<tbody>
				<tr>
					<td valign="top">
						<div style="display: inline-block;"
							class="whiteBox noPadding wf100">
							<div class="borderB fl wf100">
								<div style="padding-top: 8px;" class="fr mr10">
									<a class="font666 lh30" onclick="goSaleReport()"
										href="javascript:;">更多&gt;</a>
								</div>

								<div class="lineTab">
									<ul>
										<li type="2" onclick="getTotal(2);getClientReceive(2)"
											id="tab2" class="tabN tabS"><a href="javascript:;">今日</a>
										</li>
										<li type="3" onclick="getTotal(3);getClientReceive(3)"
											id="tab3" class="tabN"><a href="javascript:;">昨日</a>
										</li>
										<li type="4" onclick="getTotal(4);getClientReceive(4)"
											id="tab4" class="tabN"><a href="javascript:;">本月</a>
										</li>
										<li type="5" onclick="getTotal(5);getClientReceive(5)"
											id="tab5" class="tabN"><a href="javascript:;">上个月</a>
										</li>
									</ul>
								</div>
							</div>
							<table width="100%" cellspacing="0" cellpadding="8" border="0"
								style="background: #F8F8F8;">
								<tbody>
									<tr>
										<td valign="center" align="center" class="borderR"><span
											class="font14 fontDeepBlue">销售额</span><br> <span
												class="fontOrange"><span id="totalSalesAmount"
													class="font24 fontOrange">3,717.93元</span>
											</span>
										</td>
										<td valign="center" align="center" class="borderR"><span
											class="font14 fontDeepBlue">销售毛利
												<div onmouseout="$(this).children().hide()"
													onmouseover="$(this).children().show()" class="helpinfo">
													<div style="display: none;" class="tips">
														<h2></h2>
														<h1>销售毛利 = 销售额 - 当期成本价×销售量。</h1>
													</div>
												</div> </span><br> <span class="fontGreen"><span
													id="totalSalesGrossProfit" class="font24 fontGreen">633.93元</span>
											</span>
										</td>
										<td valign="center" align="center" class="borderR"><span
											class="font14 fontDeepBlue">销售笔数</span><br> <span
												id="totalSalesItems" class="font24">3笔</span>
										</td>
										<td valign="center" align="center" class="borderR"><span
											class="font14 fontDeepBlue">增加应收欠款</span><br> <span
												class="fontGreen"><span id="receivables"
													class="font24 fontGreen">0.00元</span>
											</span>
										</td>
										<td valign="center" align="center"><span
											class="font14 fontDeepBlue">销售额目标达成</span><br> <span
												id="spanSaleTarget" class="font24">1.86%</span><br> <a
													onclick="setSaleTarget()" href="javascript:;">设置目标值</a>
										</td>
									</tr>
								</tbody>
							</table>
						</div> <!-- whiteBox -->
						<div class="whiteBox noPadding wf100 fl">
							<div class="indexTitle">
								<a style="display: none" class="refresh fr mr10" href="desktop"></a>
								<span class="titleText fontDeepBlue">店铺实时动态</span>

							</div>
							<div id="dynamicArea" class="dynamicArea">
								<div class="dynamicContent">
									<div class="fl">
										<div class="oddLine">
											<div class="box">
												<div class="content">
													<span class="font14">新增销售</span><br> <spanclass="font999">
														<a
															onclick="openBusiness('销售','1D1818C9-0C50-4771-A022-C0DF3549BCCC','')">ZB20150305005</a>
														<br> <spanclass="font999">朗科<br> <spanclass
																	="font999 countnum"="">100元<br> <spanclass="font999">管理员<br>
																	</spanclass="font999">
																</spanclass="font999>
															</spanclass="font999">
														</spanclass="font999">
												</div>
												<div class="angle"></div>
												<div class="time fontDeepBlue">2015-03-05 15:42</div>
											</div>
										</div>
										<div class="evenLine">
											<div class="box">
												<div class="time fontDeepBlue">2015-03-05 15:39</div>
												<div class="angle"></div>
												<div class="content">
													<span class="font14">新增销售</span><br> <spanclass="font999">
														<a
															onclick="openBusiness('销售','3ECB46EB-4B00-4F8E-9F8D-05E9DC0F9FD0','')">ZB20150305003</a>
														<br> <spanclass="font999">朗科<br> <spanclass
																	="font999 countnum"="">0元<br> <spanclass="font999">管理员<br>
																	</spanclass="font999">
																</spanclass="font999>
															</spanclass="font999">
														</spanclass="font999">
												</div>
											</div>
										</div>
									</div>
									<div class="fl">
										<div class="oddLine">
											<div class="box">
												<div class="content">
													<span class="font14">新增销售</span><br> <spanclass="font999">
														<a
															onclick="openBusiness('销售','2167D8CC-CF19-47BA-BFB6-076DE8954C94','')">ZB20150305002</a>
														<br> <spanclass="font999">朗科<br> <spanclass
																	="font999 countnum"="">4.33元<br> <spanclass="font999">管理员<br>
																	</spanclass="font999">
																</spanclass="font999>
															</spanclass="font999">
														</spanclass="font999">
												</div>
												<div class="angle"></div>
												<div class="time fontDeepBlue">2015-03-05 15:20</div>
											</div>
										</div>
										<div class="evenLine">
											<div class="box">
												<div class="time fontDeepBlue">2015-03-05 11:35</div>
												<div class="angle"></div>
												<div class="content">
													<span class="font14">新增销售</span><br> <spanclass="font999">
														<a
															onclick="openBusiness('销售','F7C9C151-F4E4-422A-89FF-8E71BD181D95','')">ZB20150305001</a>
														<br> <spanclass="font999">朗科<br> <spanclass
																	="font999 countnum"="">4020元<br> <spanclass="font999">管理员<br>
																	</spanclass="font999">
																</spanclass="font999>
															</spanclass="font999">
														</spanclass="font999">
												</div>
											</div>
										</div>
									</div>
									<div class="fl">
										<div class="oddLine">
											<div class="box">
												<div class="content">
													<span class="font14">新增销售退货</span><br> <spanclass="font999">
														<a
															onclick="openBusiness('销售退货','8FB2D6DB-6C7B-45D2-BD07-03597904E526','')">ZB-TH0305000</a>
														<br> <spanclass="font999">朗科<br> <spanclass
																	="font999 countnum"="">406.4元<br> <spanclass="font999">管理员<br>
																	</spanclass="font999">
																</spanclass="font999>
															</spanclass="font999">
														</spanclass="font999">
												</div>
												<div class="angle"></div>
												<div class="time fontDeepBlue">2015-03-05 10:17</div>
											</div>
										</div>
										<div class="evenLine">
											<div class="box">
												<div class="time fontDeepBlue">2015-03-05 09:12</div>
												<div class="angle"></div>
												<div class="content">
													<span class="font14">新增盘点</span><br> <spanclass="font999">
														<a
															onclick="openBusiness('盘点','16CB4985-67E2-40FD-BF8A-0DB0541D095C','')">PD20150305000</a>
														<br> <spanclass="font999">管理员<br>
															</spanclass="font999">
														</spanclass="font999">
												</div>
											</div>
										</div>
									</div>
								</div>
								<!-- dynamicContent -->
							</div>
							<!-- dynamicArea -->
						</div> <!-- whiteBox --></td>
					<td width="315" valign="top">
						<div class="whiteBox warningIndex noPadding">
							<div class="indexTitle">
								<span class="titleText fontDeepBlue">预警信息</span>

							</div>
							<div class="WIlist">
								<ul>
									<li><a onclick="warningInfo1()" href="javascript:;"> <span
											class="icon inventoryLow"></span>
											<div id="div_inventory" class="text font666">
												<span class="font666">您有<span
													id="lowerinventorynumber" class="fontRed ">9</span>个商品库存低于预警线,<br>您有<span
														id="highinventorynumber" class="fontRed ">0</span>个商品超过预警线。
												</span>
											</div> </a>
									</li>
									<li><a onclick="toClientRecive()" href="javascript:;">
											<span class="icon receivable"></span>
											<div id="div_clientreceivenumber" style="padding-top:8px;"
												class="text font666">
												<span class="font666">您有<span
													id="clientreceivenumber" class="fontRed ">37</span>笔客户应收欠款。</span>
											</div> </a>
									</li>
									<li><a onclick="toSuplierPay()" href="javascript:;"> <span
											class="icon payable"></span>
											<div id="div_supplierpaynumber" style="padding-top:8px;"
												class="text font666">
												<span class="font666">您有<span
													id="clientreceivenumber" class="fontRed ">29</span>笔供应商应付欠款。</span>
											</div> </a>
									</li>
									<li><a onclick="warningInfo2()" href="javascript:;"> <span
											class="icon lowprice"></span>
											<div id="div_lowsalesnumber" style="padding-top:8px;"
												class="text font666">
												<span class="font666">最近7天低价销售了<span
													id="lowsalesnumber" class="fontRed ">1</span>个商品。</span>
											</div> </a>
									</li>

									<li><a onclick="toInStore()" href="javascript:;"> <span
											class="icon inWarehouseW"></span>
											<div id="div_incount" style="padding-top:8px;"
												class="text font666">
												<span class="font666">您有<span id="incount"
													class="fontRed ">135</span>笔待入库单需要处理。</span>
											</div> </a>
									</li>
									<li><a onclick="toOutStore()" href="javascript:;"> <span
											class="icon outWarehouseW"></span>
											<div id="div_outcount" style="padding-top:8px;"
												class="text font666">
												<span class="font666">您有<span id="outcount"
													class="fontRed ">272</span>笔待出库单需要处理。</span>
											</div> </a>
									</li>


									<li id="warn_serial" style="display: list-item;"><a
										onclick="warningInfo3()" href="javascript:;"> <span
											class="icon SNdeviant"></span>
											<div id="div_errorserialnumber" style="padding-top:8px;"
												class="text font666">
												<span class="font666">您有<span id="errorserialnumber"
													class="fontRed">0</span>个异常序列号需要处理。 </span>
											</div> </a>
									</li>

								</ul>
							</div>
						</div>


						<div class=" clear height10"></div>
						<div style="height: 138px; overflow: hidden;"
							class="whiteBox warningIndex noPadding">
							<div class="indexTitle">
								<a class="editIndex fr" onclick="setNote()" href="javascript:;"></a>
								<span class="titleText fontDeepBlue fl">老板寄语</span>
								<div onmouseout="$(this).children().hide()"
									onmouseover="$(this).children().show()"
									style="margin-top: 5px;" class="helpinfo fl">
									<div style="display: none" class="tips">
										<h2></h2>
										<h1>老板寄语会显示在员工登录首页。</h1>
									</div>
								</div>
							</div>
							<div style="padding: 10px;" class="content">
								<span class="font14">好好干，房子车子都不会有的～～～</span>
							</div>
						</div></td>
				</tr>
			</tbody>
		</table>
		<div class="height10"></div>
	</div>
</body>
</html>
