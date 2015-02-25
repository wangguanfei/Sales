/*
 * Powered By [up72-framework]
 * Web Site: http://www.up72.com
 * Since 2006 - 2015
 */

package com.basis.core.service.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFSheet;

import com.basis.core.common.Page;
import com.basis.core.common.Result;
import com.basis.core.condition.CustomerCondition;
import com.basis.core.dao.IBaseDao;
import com.basis.core.domain.Customer;
import com.basis.core.service.ICustomerService;
import com.basis.core.util.DateUtil;
import com.basis.core.util.ExcelWritePoiUtil;
import com.basis.core.util.StringUtil;

/**
 * 客户信息业务处理
 * 
 * @author wgf
 * @version 1.0
 * @since 1.0
 */

public class CustomerServiceImpl implements ICustomerService{
	private final static Logger logger=Logger.getLogger(CustomerServiceImpl.class);
	private IBaseDao dao;

	
	/**
	 * 保存 Customer
	 */
	public Result<String> addCustomer(Customer customer) {
		Result<String> result=new Result<String>(true); 
			String id=dao.addObject(customer);
			result.setData(id);
		return result;
	}
	
	/**
	 * 删除 Customer
	 */
	public Result<String> removeCustomer(Customer customer) {
		Result<String> result=new Result<String>(true); 
		dao.deleteObject(customer);
		return new Result(true);
	}
	
	/**
	 * 更新 Customer
	 */
	public  Result<String>  modifyCustomer(Customer customer) {
		Result<String> result=new Result<String>(true); 
		this.dao.updateObject(customer);
		return new Result(true);
	}
	
	/**
	 * 查询列表
	 */

	public List queryCustomerList(CustomerCondition condition) {
		/**
		 * 添加自己的判断条件
		 */
		return dao.queryObjectList(condition);
	}
	
	/**
	 * 分页查询
	 */
	public Page queryCustomerPage(CustomerCondition condition){
		return dao.queryPage(condition);
	}
	
	/**
	 * 根据ID查询
	 */
	public Customer queryCustomerById(Long id){
		return dao.getObject(Customer.class, id);
	}	
	
	public String exportDatas(String rootpath,CustomerCondition condition){
		ExcelWritePoiUtil writePoiUtil = new ExcelWritePoiUtil();
		HSSFSheet sheet = writePoiUtil.createSheet();
		String[] headers = {
				"id",
				"客户姓名",
				"客户性别",
				"客户电话",
				"客户地址",
				"客户QQ",
				"消费金额",
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
		
		Page<Customer> page = this.queryCustomerPage(condition);
		
		int index = 0;
		while(index < page.getRecordSize()){
			if(null==page.getData() || page.getData().isEmpty()){
				break;
			}
			for(int i=0;i<page.getData().size();i++){
				Customer e = page.getData().get(i);
				String[] datas = new String[]{
						null==e.getId()?"":e.getId()+"",
						null==e.getName()?"":e.getName()+"",
						null==e.getSex()?"":e.getSex()+"",
						null==e.getPhone()?"":e.getPhone()+"",
						null==e.getAddress()?"":e.getAddress()+"",
						null==e.getQq()?"":e.getQq()+"",
						null==e.getTotalSpend()?"":e.getTotalSpend()+"",
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
			page = this.queryCustomerPage(condition);
		}
		
		String result = StringUtil.parseToPath(rootpath+"/exam/"+DateUtil.format(new Date(), "yyyy-MM-dd")+"/"+UUID.randomUUID().toString());
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
