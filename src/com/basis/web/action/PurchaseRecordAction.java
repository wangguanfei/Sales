/*
 * Powered By [up72-framework]
 * Web Site: http://www.up72.com
 * Since 2006 - 2014
 */


package com.basis.web.action;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.basis.core.common.Result;
import com.basis.core.condition.FactoryInfoCondition;
import com.basis.core.condition.PurchaseRecordCondition;
import com.basis.core.constants.Constant;
import com.basis.core.constants.EMessageCode;
import com.basis.core.constants.EOperator;
import com.basis.core.domain.PurchaseRecord;
import com.basis.core.dto.PurchaseDto;
import com.basis.core.service.IFactoryInfoService;
import com.basis.core.service.IPurchaseRecordService;
import com.basis.core.util.DateUtil;
import com.basis.web.common.Authority;
import com.basis.web.common.BaseAction;

public class PurchaseRecordAction extends BaseAction{
	
	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = 5974251269173302708L;

	public static Logger logger = Logger.getLogger(PurchaseRecordAction.class);
	
	private IPurchaseRecordService purchaseRecordService;
	
	private PurchaseRecordCondition purchaseRecordCondition = new PurchaseRecordCondition();

	private IFactoryInfoService factoryInfoService;
	
	private PurchaseRecord purchaseRecord = new PurchaseRecord();
	
	
	/**
	 * 列表
	 */
	public String index() {
		request.setAttribute("factoryList", factoryInfoService.queryFactoryInfoList(new FactoryInfoCondition()));
		return "purchaseRecord-index";
	}
	
	@Authority(operator = EOperator.SELECT)
	public String data() {
		try {
			if(null==purchaseRecordCondition){
				purchaseRecordCondition = new PurchaseRecordCondition();
			}
			if (null != purchaseRecordCondition.getPurchaseDateBeaginStr() && !"".equals(purchaseRecordCondition.getPurchaseDateBeaginStr())) {
				purchaseRecordCondition.setPurchaseDateBeagin(DateUtil.parse(purchaseRecordCondition.getPurchaseDateBeaginStr(), new SimpleDateFormat("yyyy-MM-dd")).getTime());
			}
			if (null != purchaseRecordCondition.getPurchaseDateEndStr() && !"".equals(purchaseRecordCondition.getPurchaseDateEndStr())) {
				purchaseRecordCondition.setPurchaseDateEnd(DateUtil.addDays(DateUtil.parse(purchaseRecordCondition.getPurchaseDateEndStr(), new SimpleDateFormat("yyyy-MM-dd")), 1).getTime());
			}
			purchaseRecordCondition.setSortname("purchaseDate");
			purchaseRecordCondition.setSortorder("desc");
			//分页查询
			page = purchaseRecordService.queryPurchaseRecordPage(purchaseRecordCondition);
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
			result = purchaseRecordService.removePurchaseRecord(purchaseRecord);
		}catch(Exception e){
			logger.error(e);
			result = new Result<Object>(false, EMessageCode.EXCEPTION.getCode());
		}
		return "json-result";
	}

	/**
	* @Description: 结账
	* @author wgf
	* @date 2015-3-12 下午2:53:50  
	* @return String
	* @throws
	*/ 
	@Authority(operator=EOperator.DELETE)
	public String invoicing(){
		try{
			purchaseRecord = purchaseRecordService.queryPurchaseRecordById(purchaseRecord.getId());
			purchaseRecord.setPurchaseStatus(1);
			result = purchaseRecordService.modifyPurchaseRecord(purchaseRecord);
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
		return "purchaseRecord-add";
	}
	/**
	 * 添加处理
	 */
	@Authority(operator=EOperator.ADD)
	public String doAdd(){
		try{
			result = purchaseRecordService.addPurchaseRecord(purchaseRecord);
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
			purchaseRecord = purchaseRecordService.queryPurchaseRecordById(purchaseRecord.getId());
		}catch(Exception e){
			logger.error(e);
		}
		return "purchaseRecord-edit";
	}
	
	
	/**
	 * 编辑
	 * @return
	 */
	@Authority(operator=EOperator.MODIFY)
	public String doEdit(){
		try{
			int goodsNum = purchaseRecord.getGoodsNum();
			java.math.BigDecimal purchasePrice = purchaseRecord.getPurchasePrice();
			purchaseRecord = purchaseRecordService.queryPurchaseRecordById(purchaseRecord.getId());
			purchaseRecord.setGoodsNum(goodsNum);
			purchaseRecord.setPurchasePrice(purchasePrice);
			purchaseRecord.setTotalMoney(new BigDecimal(goodsNum).multiply(purchasePrice).setScale(2,BigDecimal.ROUND_HALF_UP));
			result = purchaseRecordService.modifyPurchaseRecord(purchaseRecord);
		}catch(Exception e){
			logger.error(e);
			result = new Result<Object>(false, EMessageCode.EXCEPTION.getCode());
		}
		return "json-result";
	}
	
	
	/**
	* @Description: 导出
	* @author wgf
	* @date 2015-2-27 下午3:25:12  
	* @return String
	* @throws
	*/ 
	@Authority(operator = EOperator.SELECT)
	public String exportData() {
		try {
			if(null==purchaseRecordCondition){
				purchaseRecordCondition = new PurchaseRecordCondition();
			}
			if (null != purchaseRecordCondition.getPurchaseDateBeaginStr() && !"".equals(purchaseRecordCondition.getPurchaseDateBeaginStr())) {
				purchaseRecordCondition.setPurchaseDateBeagin(DateUtil.parse(purchaseRecordCondition.getPurchaseDateBeaginStr(), new SimpleDateFormat("yyyy-MM-dd")).getTime());
			}
			if (null != purchaseRecordCondition.getPurchaseDateEndStr() && !"".equals(purchaseRecordCondition.getPurchaseDateEndStr())) {
				purchaseRecordCondition.setPurchaseDateEnd(DateUtil.addDays(DateUtil.parse(purchaseRecordCondition.getPurchaseDateEndStr(), new SimpleDateFormat("yyyy-MM-dd")), 1).getTime());
			}
			String path  = this.purchaseRecordService.exportDatas(Constant.ROOTPATH , purchaseRecordCondition);
			if ("".equals(path)) {
				result = new Result<Object>(false, "无数据");
			}else{
				result.setData(path);
			}
			
		} catch (Exception e) {
			logger.error(e);	
			result = new Result<Object>(false, EMessageCode.EXCEPTION.getCode());
		}
		return "json-result";
	}
	
	/**
	* @Description: to 进货统计
	* @author wgf
	* @date 2015-2-28 上午11:23:54  
	* @return String
	* @throws
	*/ 
	@Authority(operator = EOperator.SELECT)
	public String toStatistics() {
		request.setAttribute("year", DateUtil.format(new Date(), "yyyy"));
		return "purchaseRecord-statistics";
	}
	/**
	* @Description: 加载图表数据
	* @author wgf
	* @date 2015-3-2 上午11:10:22  
	* @return String
	* @throws
	*/ 
	@Authority(operator = EOperator.SELECT)
	public String loadData() {
		String year = request.getParameter("year");
		List<PurchaseDto> list = purchaseRecordService.queryPurchaseRecordByMonth(year);
		Map<String,Object> map = new HashMap<String, Object>();
		if (null != list && list.size() != 0) {
			String[] monthList = new String[list.size()];
			String[] moneyList = new String[list.size()];
			BigDecimal total = new BigDecimal(0);
			for (int i = 0; i < list.size(); i++) {
				PurchaseDto dto = list.get(i);
				monthList[i] = dto.getPurchase_month()+"月";
				moneyList[i] = dto.getTotal_money().toString();
				total = total.add(dto.getTotal_money());
			}
			map.put("monthList", monthList);
			map.put("moneyList", moneyList);
			map.put("total", total);
		}else{
			map.put("noData", "yes");
		}
		result.setData(map);
		return "json-result";
	}
	/**
	* @Description: 根据时间统计
	* @author wgf
	* @date 2015-3-5 下午2:33:06  
	* @return String
	* @throws
	*/ 
	@Authority(operator = EOperator.SELECT)
	public String countByDate() {
		result.setData(purchaseRecordService.countByDate(purchaseRecordCondition));
		return "json-result";
	}
	/** 
	 * get set 
	 **/
	public void setPurchaseRecordService(IPurchaseRecordService service) {
		this.purchaseRecordService = service;
	}
	
	public IPurchaseRecordService getPurchaseRecordService() {
		return this.purchaseRecordService;
	}
	
	public void setPurchaseRecord(PurchaseRecord purchaseRecord){
		this.purchaseRecord=purchaseRecord;
	}
	
	public PurchaseRecord getPurchaseRecord(){
		return purchaseRecord;
	}
	
	public void setPurchaseRecordCondition(PurchaseRecordCondition purchaseRecordCondition){
		this.purchaseRecordCondition=purchaseRecordCondition;
	}
	
	public PurchaseRecordCondition getPurchaseRecordCondition(){
		return purchaseRecordCondition;
	}

	public IFactoryInfoService getFactoryInfoService() {
		return factoryInfoService;
	}

	public void setFactoryInfoService(IFactoryInfoService factoryInfoService) {
		this.factoryInfoService = factoryInfoService;
	}
	
	
}

