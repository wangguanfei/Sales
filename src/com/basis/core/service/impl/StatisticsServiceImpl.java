/*
 * Powered By [up72-framework]
 * Web Site: http://www.up72.com
 * Since 2006 - 2015
 */

package com.basis.core.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.hibernate.transform.Transformers;

import com.basis.core.common.Page;
import com.basis.core.common.Result;
import com.basis.core.condition.StatisticsCondition;
import com.basis.core.dao.IBaseDao;
import com.basis.core.domain.Statistics;
import com.basis.core.dto.PurchaseDto;
import com.basis.core.dto.StatisticsDto;
import com.basis.core.service.IStatisticsService;
import com.basis.core.util.DateUtil;
import com.basis.core.util.ExcelWritePoiUtil;
import com.basis.core.util.StringUtil;

/**
 * 经营统计业务处理
 * 
 * @author wgf
 * @version 1.0
 * @since 1.0
 */

public class StatisticsServiceImpl implements IStatisticsService {
	private final static Logger logger = Logger
			.getLogger(StatisticsServiceImpl.class);
	private IBaseDao dao;

	/**
	 * 保存 Statistics
	 */
	public Result<String> addStatistics(Statistics statistics) {
		Result<String> result = new Result<String>(true);
		String id = dao.addObject(statistics);
		result.setData(id);
		return result;
	}

	/**
	 * 删除 Statistics
	 */
	public Result<String> removeStatistics(Statistics statistics) {
		Result<String> result = new Result<String>(true);
		dao.deleteObject(statistics);
		return new Result(true);
	}

	/**
	 * 更新 Statistics
	 */
	public Result<String> modifyStatistics(Statistics statistics) {
		Result<String> result = new Result<String>(true);
		this.dao.updateObject(statistics);
		return new Result(true);
	}

	/**
	 * 查询列表
	 */

	public List queryStatisticsList(StatisticsCondition condition) {
		/**
		 * 添加自己的判断条件
		 */
		return dao.queryObjectList(condition);
	}

	/**
	 * 分页查询
	 */
	public Page queryStatisticsPage(StatisticsCondition condition) {
		return dao.queryPage(condition);
	}

	/**
	 * 根据ID查询
	 */
	public Statistics queryStatisticsById(Long id) {
		return dao.getObject(Statistics.class, id);
	}

	public String exportDatas(String rootpath, StatisticsCondition condition) {
		ExcelWritePoiUtil writePoiUtil = new ExcelWritePoiUtil();
		HSSFSheet sheet = writePoiUtil.createSheet();
		String[] headers = { "id", "年份", "月份", "日", "总收入", "总利润" };
		for (int i = 0; i < headers.length; i++) {
			sheet.setColumnWidth(i, 3000);
		}
		// 设置头样式
		HSSFCellStyle headerStyle = writePoiUtil.createStyle();
		headerStyle.setWrapText(true);// 自动换行
		headerStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 垂直居中
		// 输出头
		writePoiUtil
				.addRow(sheet, sheet.getFirstRowNum(), headers, headerStyle);
		condition.setPageSize(100);

		Page<Statistics> page = this.queryStatisticsPage(condition);

		int index = 0;
		while (index < page.getRecordSize()) {
			if (null == page.getData() || page.getData().isEmpty()) {
				break;
			}
			for (int i = 0; i < page.getData().size(); i++) {
				Statistics e = page.getData().get(i);
				String[] datas = new String[] {
						null == e.getId() ? "" : e.getId() + "",
						null == e.getSyear() ? "" : e.getSyear() + "",
						null == e.getSmonth() ? "" : e.getSmonth() + "",
						null == e.getSday() ? "" : e.getSday() + "",
						null == e.getSin() ? "" : e.getSin() + "",
						(String) (null == e.getSprofit() ? "" : e.getSprofit()) };
				try {
					writePoiUtil.addRow(sheet, (index + 1), datas,
							(HSSFCellStyle) null);
				} catch (Exception e1) {
					continue;
				}
				index++;
			}
			condition.setPageNo(condition.getPageNo() + 1);
			page = this.queryStatisticsPage(condition);
		}

		String result = StringUtil.parseToPath(rootpath + "/exam/"
				+ DateUtil.format(new Date(), "yyyy-MM-dd") + "/"
				+ UUID.randomUUID().toString());
		try {
			writePoiUtil.saveExcel(result);
		} catch (Exception e) {
			// throw new ServiceException(e);
		}
		return result;
	}

	/**
	 * get set 方法
	 */
	public IBaseDao getDao() {
		return dao;
	}

	public void setDao(IBaseDao dao) {
		this.dao = dao;
	}

	/**
	 * @Description: 查询是否有当天统计记录
	 * @author wgf
	 * @date 2015-3-3 上午9:55:55
	 * @throws
	 */
	@Override
	public Statistics countRecord(String year, String month, String day) {
		StatisticsCondition condition = new StatisticsCondition();
		condition.setSyear(year);
		condition.setSmonth(month);
		condition.setSday(day);
		List<Statistics> list = dao.queryObjectList(condition);
		if (null != list && list.size() != 0) {
			return list.get(0);
		}
		return null;
	}

	/**
	 * @Description: 按月统计
	 * @author wgf
	 * @date 2015-3-3 下午2:05:26
	 * @return Map<String,Object>
	 * @throws
	 */
	@Override
	public Map<String, Object> getMonthMap(String nowMonth,
			StatisticsCondition statisticsCondition) {
		String[] arr = nowMonth.split("-");
		String year = arr[0];
		String month = arr[1];
		statisticsCondition.setSyear(year);
		statisticsCondition.setSmonth(month);
		statisticsCondition.setSortname("sday");
		statisticsCondition.setSortorder("asc");
		List<Statistics> list = dao.queryObjectList(statisticsCondition);
		String[] dayList = new String[list.size()];
		String[] salesList = new String[list.size()];
		String[] profitList = new String[list.size()];
		BigDecimal total_in = new BigDecimal(0);
		BigDecimal total_profit = new BigDecimal(0);
		Map<String, Object> map = new HashMap<String, Object>();
		if (null != list && list.size() != 0) {
			for (int j = 0; j < list.size(); j++) {
				Statistics statistics = list.get(j);
				dayList[j] = statistics.getSday() + "日";
				salesList[j] = statistics.getSin().toString();
				profitList[j] = statistics.getSprofit().toString();
				total_in = total_in.add(statistics.getSin());
				total_profit = total_profit.add(statistics.getSprofit());
			}
			map.put("dayList", dayList);
			map.put("salesList", salesList);
			map.put("profitList", profitList);
			map.put("total_in", total_in);
			map.put("total_profit", total_profit);
		} else {
			map.put("noData", "yes");
		}
		return map;
	}

	/**
	 * @Description: 按年统计
	 * @author wgf
	 * @date 2015-3-3 下午2:17:26
	 * @return Map<String,Object>
	 * @throws
	 */
	@Override
	public Map<String, Object> getYearMap(String nowYear) {
		String sql = "SELECT  s_month statisticMonth, SUM(s_in) income,SUM(s_profit) profit FROM `zsgx_statistics` WHERE s_year='"
				+ nowYear + "' GROUP BY s_month ORDER BY s_month";
		List<StatisticsDto> list = dao
				.getSession()
				.createSQLQuery(sql)
				.setResultTransformer(
						Transformers.aliasToBean(StatisticsDto.class)).list();
		String[] monthList = new String[list.size()];
		String[] salesList = new String[list.size()];
		String[] profitList = new String[list.size()];
		BigDecimal total_in = new BigDecimal(0);
		BigDecimal total_profit = new BigDecimal(0);
		Map<String, Object> map = new HashMap<String, Object>();
		if (null != list && list.size() != 0) {
			for (int i = 0; i < list.size(); i++) {
				StatisticsDto dto = list.get(i);
				monthList[i] = dto.getStatisticMonth();
				salesList[i] = dto.getIncome().toString();
				profitList[i] = dto.getProfit().toString();
				total_in = total_in.add(dto.getIncome());
				total_profit = total_profit.add(dto.getProfit());
			}
			map.put("dayList", monthList);
			map.put("salesList", salesList);
			map.put("profitList", profitList);
			map.put("total_in", total_in);
			map.put("total_profit", total_profit);
		} else {
			map.put("noData", "yes");
		}
		return map;
	}

	/**
	 * @Description: 首页概况统计
	 * @author wgf
	 * @date 2015-3-6 上午11:14:43
	 * @return Map<String,Object>
	 * @throws
	 */
	@Override
	public Map<String, Object> statisticAll(String type) {
		Map<String, Object>  map = new HashMap<String, Object>();
		String salesSql = initSql(type)[0];
		String purchaseSql = initSql(type)[1];
		StatisticsDto salesObj = (StatisticsDto) this
				.getDao()
				.getSession()
				.createSQLQuery(salesSql.toString())
				.setResultTransformer(
						Transformers.aliasToBean(StatisticsDto.class))
				.uniqueResult();
		StatisticsDto purchaseObj = (StatisticsDto) this
				.getDao()
				.getSession()
				.createSQLQuery(purchaseSql.toString())
				.setResultTransformer(
						Transformers.aliasToBean(StatisticsDto.class))
				.uniqueResult();
		if (null != salesObj) {
			map.put("income", salesObj.getIncome());
			map.put("profit", salesObj.getProfit());
			map.put("salesNum", salesObj.getNum());
		}else{
			map.put("income", 0);
			map.put("profit", 0);
			map.put("salesNum", 0);
		}
		if (null != purchaseObj) {
			map.put("totalMoney", purchaseObj.getTotal_money());
			map.put("purchaseNum", purchaseObj.getNum());
		}else {
			map.put("totalMoney", 0);
			map.put("purchaseNum", 0);
		}
		return map;
	}

	private String[] initSql(String type) {
		String salesSql = "SELECT SUM(income) income,SUM(profit) profit,COUNT(id) num FROM `zsgx_sales_record` where 1=1 ";
		String purchaseSql = "SELECT SUM(total_money) total_money ,COUNT(id) num FROM `zsgx_purchase_record` where 1=1 ";

		if ("2".equals(type)) {// 今天
			String today = DateUtil.format(new Date(), "yyyy-MM-dd");
			String[] arr = today.split("-");
			String year = arr[0];
			String month = arr[1];
			String day = arr[2];
			salesSql += " and sales_year='" + year + "' and sales_month='"
					+ month + "' and sales_day='" + day + "'";
			purchaseSql += " and purchase_date_str = '" + today + "'";
		}
		if ("3".equals(type)) {// 昨天
			String today = DateUtil.format(DateUtil.addDays(new Date(), -1),
					"yyyy-MM-dd");
			String[] arr = today.split("-");
			String year = arr[0];
			String month = arr[1];
			String day = arr[2];
			salesSql += " and sales_year='" + year + "' and sales_month='"
					+ month + "' and sales_day='" + day + "'";
			purchaseSql += " and purchase_date_str = '" + today + "'";
		}
		if ("4".equals(type)) {// 本月
			long time = DateUtil.parseDate(DateUtil.getFirstDayOfMonth())
					.getTime();
			salesSql += " and sales_date >= " + time;
			purchaseSql += " and purchase_date >= " + time;
		}
		if ("5".equals(type)) {// 上月
			long beginTime = DateUtil.parseDate(
					DateUtil.getFirstDayOfLastMonth()).getTime();
			long endTime = DateUtil.parseDate(DateUtil.getFirstDayOfMonth())
					.getTime();
			salesSql += " and sales_date >= " + beginTime
					+ " and sales_date <= " + endTime;
			purchaseSql += " and purchase_date >= " + beginTime
					+ " and purchase_date <= " + endTime;
		}
		return new String[] { salesSql, purchaseSql };
	}

}
