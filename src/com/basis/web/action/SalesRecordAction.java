/*
 * Powered By [up72-framework]
 * Web Site: http://www.up72.com
 * Since 2006 - 2014
 */


package com.basis.web.action;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

import com.basis.core.common.Result;
import com.basis.core.condition.CustomerCondition;
import com.basis.core.condition.SalesRecordCondition;
import com.basis.core.constants.Constant;
import com.basis.core.constants.EMessageCode;
import com.basis.core.constants.EOperator;
import com.basis.core.domain.Customer;
import com.basis.core.domain.Goods;
import com.basis.core.domain.SalesRecord;
import com.basis.core.domain.Statistics;
import com.basis.core.service.ICustomerService;
import com.basis.core.service.IGoodsService;
import com.basis.core.service.IPurchaseRecordService;
import com.basis.core.service.ISalesRecordService;
import com.basis.core.service.IStatisticsService;
import com.basis.core.util.DateUtil;
import com.basis.web.common.Authority;
import com.basis.web.common.BaseAction;

public class SalesRecordAction extends BaseAction{
	
	public static Logger logger = Logger.getLogger(SalesRecordAction.class);
	
	private ISalesRecordService salesRecordService;
	
	private SalesRecordCondition salesRecordCondition = new SalesRecordCondition();

	private SalesRecord salesRecord = new SalesRecord();
	
	private Goods goods = new Goods();
	
	private IGoodsService goodsService;
	
	private IPurchaseRecordService purchaseRecordService;
	
	private ICustomerService customerService;
	
	private IStatisticsService statisticsService;
	
	
	/**
	 * 列表
	 */
	public String index() {
		return "salesRecord-index";
	}
	
	/**
	 * 调查项目（校区）列表
	 * 
	 * @return
	 */
	@Authority(operator = EOperator.SELECT)
	public String data() {
		try {
			if(null==salesRecordCondition){
				salesRecordCondition = new SalesRecordCondition();
			}
			if (null != salesRecordCondition.getSalesDateBeaginStr() && !"".equals(salesRecordCondition.getSalesDateBeaginStr())) {
				salesRecordCondition.setSalesDateBeagin(DateUtil.parse(salesRecordCondition.getSalesDateBeaginStr(), new SimpleDateFormat("yyyy-MM-dd")).getTime());
			}
			if (null != salesRecordCondition.getSalesDateEndStr() && !"".equals(salesRecordCondition.getSalesDateEndStr())) {
				salesRecordCondition.setSalesDateEnd(DateUtil.addDays(DateUtil.parse(salesRecordCondition.getSalesDateEndStr(), new SimpleDateFormat("yyyy-MM-dd")), 1).getTime());
			}
			salesRecordCondition.setSortname("salesDate");
			//分页查询
			page = salesRecordService.querySalesRecordPage(salesRecordCondition);
		} catch (Exception e) {
			logger.error(e);	
			result = new Result<Object>(false, EMessageCode.EXCEPTION.getCode());
		}
		return "json-page";
	}
	
	/**
	 * 删除
	 */
	@Authority(operator=EOperator.DELETE)
	public String doDelete(){
		try{
			result = salesRecordService.removeSalesRecord(salesRecord);
		}catch(Exception e){
			logger.error(e);
			result = new Result<Object>(false, EMessageCode.EXCEPTION.getCode());
		}
		return "json-result";
	}
	
	/**
	 * @Description: 销售
	 * @author wgf
	 * @date 2014-10-13下午4:40:39
	 * @param @return   
	 * @return String  
	 * @throws
	 */
	@Authority(operator=EOperator.SELECT)
	public String toGoodsSales(){
		goods = goodsService.queryGoodsById(salesRecord.getGoodsId());
		request.setAttribute("purchasePriceList", purchaseRecordService.queryPurchasePrice(salesRecord.getGoodsId()));
		request.setAttribute("customerList", customerService.queryCustomerList(new CustomerCondition()));
		return "goods-sales";
	}
	/**
	 * @Description: 销售
	 * @author wgf
	 * @date 2014-10-14上午10:27:21
	 * @param @return   
	 * @return String  
	 * @throws
	 */
	@Authority(operator=EOperator.ADD)
	public String goodsSales(){
		try{
			goods = goodsService.queryGoodsById(salesRecord.getGoodsId());
			salesRecord.setSalesDate(System.currentTimeMillis());
			String year = DateUtil.format(new Date(), "yyyy");
			String month = DateUtil.format(new Date(), "MM");
			String day = DateUtil.format(new Date(), "dd");
			salesRecord.setSalesYear(year);
			salesRecord.setSalesMonth(month);
			salesRecord.setSalesDay(day);
			BigDecimal income = new BigDecimal(salesRecord.getSalesNumber()).multiply(salesRecord.getSalesPrice()).setScale(2,BigDecimal.ROUND_HALF_UP);
			BigDecimal chengBen = new BigDecimal(salesRecord.getSalesNumber()).multiply(salesRecord.getPurchasePrice()).setScale(2,BigDecimal.ROUND_HALF_UP);
			salesRecord.setIncome(income);
			salesRecord.setProfit(income.subtract(chengBen));
			salesRecordService.addSalesRecord(salesRecord);
			//减少库存
			goods.setStock(goods.getStock()-salesRecord.getSalesNumber());
			goodsService.modifyGoods(goods);
			
			//客户消费总额
			if(null != salesRecord.getCustomerId()){
				Customer customer = customerService.queryCustomerById(salesRecord.getCustomerId());
				customer.setTotalSpend(customer.getTotalSpend().add(income));
				customerService.modifyCustomer(customer);
			}
			
			//当天收支统计
			Statistics statistic = statisticsService.countRecord(year, month, day);
			if (null != statistic) {
				statistic.setSin(statistic.getSin().add(income));
				statistic.setSprofit(statistic.getSprofit().add(income.subtract(chengBen)));
				statisticsService.modifyStatistics(statistic);
			}else {
				statistic = new Statistics();
				statistic.setSyear(year);
				statistic.setSmonth(month);
				statistic.setSday(day);
				statistic.setSin(income);
				statistic.setSprofit(income.subtract(chengBen));
				statisticsService.addStatistics(statistic);
			}
		}catch(Exception e){
			logger.error(e);
			result = new Result<Object>(false, EMessageCode.EXCEPTION.getCode());
		}
		return "json-result";
	}
	
	/**
	 * 添加
	 */
	@Authority(operator=EOperator.SELECT)
	public String toAdd(){
		return "salesRecord-add";
	}
	/**
	 * 添加处理
	 */
	@Authority(operator=EOperator.ADD)
	public String doAdd(){
		try{
			result = salesRecordService.addSalesRecord(salesRecord);
		}catch(Exception e){
			logger.error(e);
			result = new Result<Object>(false, EMessageCode.EXCEPTION.getCode());
		}
		return "json-result";
	}
	
	/**
	 * 编辑
	 * @return
	 */
	@Authority(operator=EOperator.SELECT)
	public String toEdit(){
		try{
			salesRecord = salesRecordService.querySalesRecordById(salesRecord.getId());
		}catch(Exception e){
			logger.error(e);
		}
		return "salesRecord-edit";
	}
	
	
	/**
	 * 编辑
	 * @return
	 */
	@Authority(operator=EOperator.MODIFY)
	public String doEdit(){
		try{
			result = salesRecordService.modifySalesRecord(salesRecord);
		}catch(Exception e){
			logger.error(e);
			result = new Result<Object>(false, EMessageCode.EXCEPTION.getCode());
		}
		return "json-result";
	}
	
	
	/**
	* @Description:导出
	* @author wgf
	* @date 2015-2-27 下午3:30:45  
	* @return String
	* @throws
	*/ 
	@Authority(operator = EOperator.SELECT)
	public String exportData() {
		if(null==salesRecordCondition){
			salesRecordCondition = new SalesRecordCondition();
		}
		if (null != salesRecordCondition.getSalesDateBeaginStr() && !"".equals(salesRecordCondition.getSalesDateBeaginStr())) {
			salesRecordCondition.setSalesDateBeagin(DateUtil.parse(salesRecordCondition.getSalesDateBeaginStr(), new SimpleDateFormat("yyyy-MM-dd")).getTime());
		}
		if (null != salesRecordCondition.getSalesDateEndStr() && !"".equals(salesRecordCondition.getSalesDateEndStr())) {
			salesRecordCondition.setSalesDateEnd(DateUtil.addDays(DateUtil.parse(salesRecordCondition.getSalesDateEndStr(), new SimpleDateFormat("yyyy-MM-dd")), 1).getTime());
		}
		String path  = this.salesRecordService.exportDatas(Constant.ROOTPATH , salesRecordCondition);
		if ("".equals(path)) {
			result = new Result<Object>(false, "无数据");
		}else{
			result.setData(path);
		}
		return "json-result";
	}
	/** 
	 * get set 
	 **/
	public void setSalesRecordService(ISalesRecordService service) {
		this.salesRecordService = service;
	}
	
	public ISalesRecordService getSalesRecordService() {
		return this.salesRecordService;
	}
	
	public void setSalesRecord(SalesRecord salesRecord){
		this.salesRecord=salesRecord;
	}
	
	public SalesRecord getSalesRecord(){
		return salesRecord;
	}
	
	public void setSalesRecordCondition(SalesRecordCondition salesRecordCondition){
		this.salesRecordCondition=salesRecordCondition;
	}
	
	public SalesRecordCondition getSalesRecordCondition(){
		return salesRecordCondition;
	}

	public Goods getGoods() {
		return goods;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}

	public IGoodsService getGoodsService() {
		return goodsService;
	}

	public void setGoodsService(IGoodsService goodsService) {
		this.goodsService = goodsService;
	}

	public IPurchaseRecordService getPurchaseRecordService() {
		return purchaseRecordService;
	}

	public void setPurchaseRecordService(
			IPurchaseRecordService purchaseRecordService) {
		this.purchaseRecordService = purchaseRecordService;
	}

	public ICustomerService getCustomerService() {
		return customerService;
	}

	public void setCustomerService(ICustomerService customerService) {
		this.customerService = customerService;
	}

	public IStatisticsService getStatisticsService() {
		return statisticsService;
	}

	public void setStatisticsService(IStatisticsService statisticsService) {
		this.statisticsService = statisticsService;
	}
	
	
	
}

