/*
 * Powered By [up72-framework]
 * Web Site: http://www.up72.com
 * Since 2006 - 2014
 */

package com.basis.core.service.impl;

import java.util.List;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.log4j.Logger;

import com.basis.core.domain.PurchaseRecord;
import com.basis.core.dto.PurchaseDto;

import com.basis.core.common.Page;
import com.basis.core.common.Result;
import com.basis.core.dao.IBaseDao;
import com.basis.core.exception.NameIsExistException;
import com.basis.core.exception.ParamIsNullException;
import com.basis.core.service.IPurchaseRecordService;
import com.basis.core.condition.PurchaseRecordCondition;
import com.basis.core.util.DateUtil;
import com.basis.core.util.ExcelWritePoiUtil;
import com.basis.core.util.StringUtil;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.hibernate.transform.Transformers;

/**
 * 进货表业务处理
 * 
 * @author wgf
 * @version 1.0
 * @since 1.0
 */

public class PurchaseRecordServiceImpl implements IPurchaseRecordService{
	private final static Logger logger=Logger.getLogger(PurchaseRecordServiceImpl.class);
	private IBaseDao dao;

	
	/**
	 * 保存 PurchaseRecord
	 */
	public Result<String> addPurchaseRecord(PurchaseRecord purchaseRecord) {
		Result<String> result=new Result<String>(true); 
			String id=dao.addObject(purchaseRecord);
			result.setData(id);
		return result;
	}
	
	/**
	 * 删除 PurchaseRecord
	 */
	public Result<String> removePurchaseRecord(PurchaseRecord purchaseRecord) {
		Result<String> result=new Result<String>(true); 
		dao.deleteObject(purchaseRecord);
		return new Result(true);
	}
	
	/**
	 * 更新 PurchaseRecord
	 */
	public  Result<String>  modifyPurchaseRecord(PurchaseRecord purchaseRecord) {
		Result<String> result=new Result<String>(true); 
		this.dao.updateObject(purchaseRecord);
		return new Result(true);
	}
	
	/**
	 * 查询列表
	 */

	public List queryPurchaseRecordList(PurchaseRecordCondition condition) {
		/**
		 * 添加自己的判断条件
		 */
		return dao.queryObjectList(condition);
	}
	
	/**
	 * 分页查询
	 */
	public Page queryPurchaseRecordPage(PurchaseRecordCondition condition){
		return dao.queryPage(condition);
	}
	
	/**
	 * 根据ID查询
	 */
	public PurchaseRecord queryPurchaseRecordById(Long id){
		return dao.getObject(PurchaseRecord.class, id);
	}	
	
	public String exportDatas(String rootpath,PurchaseRecordCondition condition){
		ExcelWritePoiUtil writePoiUtil = new ExcelWritePoiUtil();
		HSSFSheet sheet = writePoiUtil.createSheet();
		String[] headers = {
				"商品",
				"进货数量",
				"进货价(元)",
				"总支出(元)",
				"进货时间",
		};
		for(int i=0;i<headers.length;i++){
			sheet.setColumnWidth(i,3000);
		}
		// 设置头样式
		HSSFCellStyle headerStyle = writePoiUtil.createStyle();
		headerStyle.setWrapText(true);//自动换行
		headerStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中
		// 输出头
		writePoiUtil.addRow(
				sheet, sheet.getFirstRowNum(), headers, headerStyle);
		condition.setPageSize(100);
		
		Page<PurchaseRecord> page = this.queryPurchaseRecordPage(condition);
		
		int index = 0;
		if (page.getRecordSize() == 0) {
			return "";
		}
		while(index < page.getRecordSize()){
			if(null==page.getData() || page.getData().isEmpty()){
				return "";
			}
			for(int i=0;i<page.getData().size();i++){
				PurchaseRecord e = page.getData().get(i);
				String[] datas = new String[]{
						null==e.getGoods()?"":e.getGoods().getName()+"",
						null==e.getGoodsNum()?"":e.getGoodsNum()+"",
						null==e.getPurchasePrice()?"":e.getPurchasePrice()+"",
						null==e.getTotalMoney()?"":e.getTotalMoney()+"",
						null==e.getPurchaseDateStr()?"":e.getPurchaseDateStr()
					};
				try{
					writePoiUtil.addRow(sheet,(index+1), datas, (HSSFCellStyle)null);
				}catch(Exception e1){
					continue;
				}
				index++;
			}
			condition.setPageNo(condition.getPageNo()+1);
			page = this.queryPurchaseRecordPage(condition);
		}
		
		String result = StringUtil.parseToPath(rootpath+"/exportTemp/"+UUID.randomUUID().toString()+".xls");
		try {
			writePoiUtil.saveExcel(result);
		} catch (Exception e) {
			//throw new ServiceException(e);
		}
		return result;
	}
	
	/**
	 * get set  方法
	 */
	public IBaseDao getDao() {
		return dao;
	}

	public void setDao(IBaseDao dao) {
		this.dao = dao;
	}

	/**
	 * @Description: 查询商品历史进价
	 * @author wgf
	 * @date 2014-10-13下午4:50:11
	 * @param @param goodsId
	 * @param @return   
	 * @return List<String>  
	 * @throws
	 */

	@SuppressWarnings("unchecked")
	@Override
	public List<String> queryPurchasePrice(Long goodsId) {
		if (null!=goodsId) {
			String sql = "SELECT DISTINCT purchase_price FROM `zsgx_purchase_record` WHERE goods_id="+goodsId;
			return this.getDao().getSession().createSQLQuery(sql).list();
		}
		return null;
	}

	/**
	* @Description: 按月统计
	* @author wgf
	* @date 2015-3-2 上午11:19:54
	* @param year
	* @return    
	* @throws 
	*/ 
	@Override
	public List<PurchaseDto> queryPurchaseRecordByMonth(String year) {
		if (null!=year && !"".equals(year)) {
			String sql = "SELECT SUM(total_money) AS total_money ,purchase_month FROM `zsgx_purchase_record` WHERE purchase_year = '"+year+"' GROUP BY purchase_month ORDER BY purchase_month";
			return this.getDao().getSession().createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(PurchaseDto.class)).list();
		}
		return null;
	}

	/**
	* @Description: 年份总计
	* @author wgf
	* @date 2015-3-2 下午3:29:16  
	* @return String
	* @throws
	*/ 
	@Override
	public String SumPurchaseRecordByYear(String year) {
		if (null!=year && !"".equals(year)) {
			String sql = "SELECT SUM(total_money) AS total FROM `zsgx_purchase_record` WHERE purchase_year = '"+year+"'";
			Object obj = this.getDao().getSession().createSQLQuery(sql).uniqueResult();
			return null != obj?obj.toString():"0";
		}
		return "0";
	}
}	
