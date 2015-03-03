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

import com.basis.core.domain.SalesRecord;

import com.basis.core.common.Page;
import com.basis.core.common.Result;
import com.basis.core.dao.IBaseDao;
import com.basis.core.exception.NameIsExistException;
import com.basis.core.exception.ParamIsNullException;
import com.basis.core.service.ISalesRecordService;
import com.basis.core.condition.SalesRecordCondition;
import com.basis.core.util.DateUtil;
import com.basis.core.util.ExcelWritePoiUtil;
import com.basis.core.util.StringUtil;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFSheet;

/**
 * InnoDB free: 11264 kB业务处理
 * 
 * @author wgf
 * @version 1.0
 * @since 1.0
 */

public class SalesRecordServiceImpl implements ISalesRecordService{
	private final static Logger logger=Logger.getLogger(SalesRecordServiceImpl.class);
	private IBaseDao dao;

	
	/**
	 * 保存 SalesRecord
	 */
	public Result<String> addSalesRecord(SalesRecord salesRecord) {
		Result<String> result=new Result<String>(true); 
			String id=dao.addObject(salesRecord);
			result.setData(id);
		return result;
	}
	
	/**
	 * 删除 SalesRecord
	 */
	public Result<String> removeSalesRecord(SalesRecord salesRecord) {
		Result<String> result=new Result<String>(true); 
		dao.deleteObject(salesRecord);
		return new Result(true);
	}
	
	/**
	 * 更新 SalesRecord
	 */
	public  Result<String>  modifySalesRecord(SalesRecord salesRecord) {
		Result<String> result=new Result<String>(true); 
		this.dao.updateObject(salesRecord);
		return new Result(true);
	}
	
	/**
	 * 查询列表
	 */

	public List querySalesRecordList(SalesRecordCondition condition) {
		/**
		 * 添加自己的判断条件
		 */
		return dao.queryObjectList(condition);
	}
	
	/**
	 * 分页查询
	 */
	public Page querySalesRecordPage(SalesRecordCondition condition){
		return dao.queryPage(condition);
	}
	
	/**
	 * 根据ID查询
	 */
	public SalesRecord querySalesRecordById(Long id){
		return dao.getObject(SalesRecord.class, id);
	}	
	
	public String exportDatas(String rootpath,SalesRecordCondition condition){
		ExcelWritePoiUtil writePoiUtil = new ExcelWritePoiUtil();
		HSSFSheet sheet = writePoiUtil.createSheet();
		String[] headers = {
				"商品",
				"客户名称",
				"销售数量",
				"销售价格",
				"商品进价",
				"销售时间",
				"销售总额",
				"利润",
				"备注"
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
		
		Page<SalesRecord> page = this.querySalesRecordPage(condition);
		
		int index = 0;
		while(index < page.getRecordSize()){
			if(null==page.getData() || page.getData().isEmpty()){
				break;
			}
			for(int i=0;i<page.getData().size();i++){
				SalesRecord e = page.getData().get(i);
				String[] datas = new String[]{
						null==e.getGoods()?"":e.getGoods().getName()+"",
						null==e.getCustomer()?"":e.getCustomer().getName()+"",		
						null==e.getSalesNumber()?"":e.getSalesNumber()+"",
						null==e.getSalesPrice()?"":e.getSalesPrice()+"",
						null==e.getPurchasePrice()?"":e.getPurchasePrice()+"",
						null==e.getSalesDateStr()?"":e.getSalesDateStr()+"",
						null==e.getIncome()?"":e.getIncome()+"",
						null==e.getProfit()?"":e.getProfit()+"",
						null==e.getRemain()?"":e.getRemain()
					};
				try{
					writePoiUtil.addRow(sheet,(index+1), datas, (HSSFCellStyle)null);
				}catch(Exception e1){
					continue;
				}
				index++;
			}
			condition.setPageNo(condition.getPageNo()+1);
			page = this.querySalesRecordPage(condition);
		}
		
		String result = StringUtil.parseToPath(rootpath+"/exportTemp/"+UUID.randomUUID().toString());
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
}	
