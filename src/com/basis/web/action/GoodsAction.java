/*
 * Powered By [up72-framework]
 * Web Site: http://www.up72.com
 * Since 2006 - 2014
 */


package com.basis.web.action;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.basis.core.common.Result;
import com.basis.core.condition.GoodsCondition;
import com.basis.core.constants.EMessageCode;
import com.basis.core.constants.EOperator;
import com.basis.core.domain.Goods;
import com.basis.core.domain.PurchaseRecord;
import com.basis.core.service.IGoodsService;
import com.basis.core.service.IPurchaseRecordService;
import com.basis.core.util.DateUtil;
import com.basis.web.common.Authority;
import com.basis.web.common.BaseAction;

public class GoodsAction extends BaseAction{
	
	public static Logger logger = Logger.getLogger(GoodsAction.class);
	
	private IGoodsService goodsService;
	
	private IPurchaseRecordService purchaseRecordService;
	
	private GoodsCondition goodsCondition = new GoodsCondition();

	private Goods goods = new Goods();
	
	private PurchaseRecord purchaseRecord = new PurchaseRecord();
	
	/**
	 * 列表
	 */
	public String index() {
		return "goods-index";
	}
	
	/**
	 * 调查项目（校区）列表
	 * 
	 * @return
	 */
	@Authority(operator = EOperator.SELECT)
	public String data() {
		try {
			if(null==goodsCondition){
				goodsCondition = new GoodsCondition();
			}
			goodsCondition.setSortname("stock");
			goodsCondition.setSortorder("asc");
			//分页查询
			page = goodsService.queryGoodsPage(goodsCondition);
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
			result = goodsService.removeGoods(goods);
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
		return "goods-add";
	}
	/**
	 * 添加处理
	 */
	@Authority(operator=EOperator.ADD)
	public String doAdd(){
		try{
			goods.setStock(0);
			result = goodsService.addGoods(goods);
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
			goods = goodsService.queryGoodsById(goods.getId());
		}catch(Exception e){
			logger.error(e);
		}
		return "goods-edit";
	}
	
	
	/**
	 * 编辑
	 * @return
	 */
	@Authority(operator=EOperator.MODIFY)
	public String doEdit(){
		try{
			result = goodsService.modifyGoods(goods);
		}catch(Exception e){
			logger.error(e);
			result = new Result<Object>(false, EMessageCode.EXCEPTION.getCode());
		}
		return "json-result";
	}
	
	/**
	 * @Description: 进货
	 * @author wgf
	 * @date 2014-10-11下午4:53:47
	 * @param @return   
	 * @return String  
	 * @throws
	 */
	@Authority(operator=EOperator.SELECT)
	public String toGoodsPurchase(){
		try{
			goods = goodsService.queryGoodsById(goods.getId());
		}catch(Exception e){
			logger.error(e);
		}
		return "goods-purchase";
	}
	/**
	 * @Description: 进货
	 * @author wgf
	 * @date 2014-10-11下午4:52:17
	 * @param @return   
	 * @return String  
	 * @throws
	 */
	@Authority(operator=EOperator.MODIFY)
	public String goodsPurchase(){
		try{
			goods = goodsService.queryGoodsById(goods.getId());
			goods.setStock(goods.getStock()+purchaseRecord.getGoodsNum());
			result = goodsService.modifyGoods(goods);
			
			//添加进货记录
			purchaseRecord.setPurchaseDate(new Date().getTime());
			purchaseRecord.setPurchaseYear(DateUtil.format(new Date(), "yyyy"));
			purchaseRecord.setPurchaseMonth(DateUtil.format(new Date(), "MM"));
			purchaseRecord.setPurchaseDay(DateUtil.format(new Date(), "dd"));
			purchaseRecord.setPurchaseDateStr(DateUtil.format(new Date()));
			purchaseRecord.setTotalMoney(new BigDecimal(purchaseRecord.getGoodsNum()).multiply(purchaseRecord.getPurchasePrice()).setScale(2,BigDecimal.ROUND_HALF_UP));
			purchaseRecordService.addPurchaseRecord(purchaseRecord);
		}catch(Exception e){
			logger.error(e);
			result = new Result<Object>(false, EMessageCode.EXCEPTION.getCode());
		}
		return "json-result";
	}
	
	/**
	* @Description: 库存预警
	* @author wgf
	* @date 2015-3-6 下午2:36:18  
	* @return String
	* @throws
	*/ 
	@Authority(operator=EOperator.SELECT)
	public String yuJing(){
		List<Goods> list = goodsService.getYujing();
		request.setAttribute("list", list);
		return "goods-yuJing";
	}
	/** 
	 * get set 
	 **/
	public void setGoodsService(IGoodsService service) {
		this.goodsService = service;
	}
	
	public IGoodsService getGoodsService() {
		return this.goodsService;
	}
	
	public void setGoods(Goods goods){
		this.goods=goods;
	}
	
	public Goods getGoods(){
		return goods;
	}
	
	public void setGoodsCondition(GoodsCondition goodsCondition){
		this.goodsCondition=goodsCondition;
	}
	
	public GoodsCondition getGoodsCondition(){
		return goodsCondition;
	}

	public PurchaseRecord getPurchaseRecord() {
		return purchaseRecord;
	}

	public void setPurchaseRecord(PurchaseRecord purchaseRecord) {
		this.purchaseRecord = purchaseRecord;
	}

	public IPurchaseRecordService getPurchaseRecordService() {
		return purchaseRecordService;
	}

	public void setPurchaseRecordService(
			IPurchaseRecordService purchaseRecordService) {
		this.purchaseRecordService = purchaseRecordService;
	}
	
	
	
}

