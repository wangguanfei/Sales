package com.basis.core.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReadPoiUtil {
	
//	public final static short ENCODING = HSSFWorkbook.ENCODING_UTF_16;
	private XSSFWorkbook excel;
	
	/**
	 * 受保护的构造方法，不允许外部初始化
	 */
	protected ExcelReadPoiUtil(){
		
	}
	
	/**
	 * 根据给定的文件初始化excel
	 * @param fileName
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public ExcelReadPoiUtil(String fileName) throws FileNotFoundException, IOException {
		this.excel = this.getExcel(fileName);
	}
	
	/**
	 * 根据给定的输入流初始化excel
	 * @param inputStream
	 * @throws IOException
	 */
	public ExcelReadPoiUtil(InputStream inputStream) throws IOException {
		this.excel = this.getExcel(inputStream);
	}
	
	/**
	 * 读取一个excel文件，获得操作对象
	 * 
	 * @param fileName
	 *            excle文件路径
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	protected XSSFWorkbook getExcel(String fileName)
			throws FileNotFoundException, IOException {
		XSSFWorkbook result = null;
		// POIFSFileSystem poiFileSystem = new POIFSFileSystem(new FileInputStream(fileName)); 
		result = new XSSFWorkbook(new FileInputStream(fileName));

		return result;
	}
	
	/**
	 * 读取一个excel文件，获得操作对象
	 * 
	 * @param fileName
	 *            excle文件路径
	 * @throws IOException 
	 */
	protected XSSFWorkbook getExcel(InputStream inputStream) throws IOException {
		XSSFWorkbook result = null;
		if(null != inputStream){
			result = new XSSFWorkbook(inputStream);
		}
		return result;
	}
	
	/**
	 * 获得当前excel中所有的工作区
	 * @return XSSFSheet[]
	 */
	public XSSFSheet[] getSheets(){
		XSSFSheet[] result = new XSSFSheet[this.excel.getNumberOfSheets()];
		for(int i=0;i<this.excel.getNumberOfSheets();i++){
			result[i] = this.excel.getSheetAt(i);
		}
		return result;
	}
	
	/**
	 * 获得指定工作区的所有行
	 * @param sheet
	 * @return XSSFRow[]
	 */
	public XSSFRow[] getRows(XSSFSheet sheet){
		XSSFRow[] result = new XSSFRow[sheet.getLastRowNum() - sheet.getFirstRowNum() + 1];
		
		for(int i=sheet.getFirstRowNum();i<= sheet.getLastRowNum();i++){
			result[i-sheet.getFirstRowNum()] = sheet.getRow(i);
		}
		
		return result;
	}
	
	/**
	 * 获得指定行的所有单元格
	 * @return
	 */
	public XSSFCell[] getCells(XSSFRow row){
		XSSFCell[] result = new XSSFCell[row.getLastCellNum() - row.getFirstCellNum() + 1];
		
		for(short i=row.getFirstCellNum();i< row.getLastCellNum();i++){
			result[i - row.getFirstCellNum()] = row.getCell(i);
		}
		
		return result;
	}
	
	/**
	 * 获得指定小标的工作区，如果下标越界则返回null
	 * @return XSSFSheet
	 */
	public XSSFSheet getSheet(int index){
		XSSFSheet result = null;
		if(index >= 0 
				&& index < this.getSheetNum()){
			result = this.excel.getSheetAt(index);
		}
		return result;
	}
	
	/**
	 * 根据名称，获得指定的工作区
	 * @return
	 */
	public XSSFSheet getSheet(String name){
		XSSFSheet result = null;
		if(null != name 
				&& !name.trim().equals("")){
			result = this.excel.getSheet(name);
		}
		return result;
	}
	
	/**
	 * 获得指定小标sheet的名称
	 * @param index
	 * @return
	 */
	public String getSheetName(int index){
		String result = null;
		if(index >= 0 
				&& index < this.excel.getNumberOfSheets()){
			result = this.excel.getSheetName(index);
		}
		return result;
	}
	
	/**
	 * 获得工作区数量
	 */
	public int getSheetNum(){
		int result = 0;
		result = this.excel.getNumberOfSheets();
		return result;
	}
	
	/**
	 * 获得指定工作区的指定行
	 * @return
	 */
	public XSSFRow getRow(XSSFSheet sheet,int index){
		XSSFRow result = null;
		if(null != sheet 
				&& index >= sheet.getFirstRowNum() 
				&& index <= sheet.getLastRowNum()){
			result = sheet.getRow(index);
		}
		return result;
	}
	
	/**
	 * 获得单元格的字符串值
	 * @return String
	 */
	public String getStringValue(XSSFCell cell){
		String result = null;
		
		if(null != cell){
			// 设置编码
//			cell.setEncoding(ENCODING);
			// 判断类型
			int type = cell.getCellType();
			
			switch (type) {
			// 类型为空
			case XSSFCell.CELL_TYPE_BLANK:
				break;
			// 类型为布尔
			case XSSFCell.CELL_TYPE_BOOLEAN:
				result = Boolean.toString(cell.getBooleanCellValue());
				break;
			// 公式活函数类型
			case XSSFCell.CELL_TYPE_FORMULA:
				result = cell.getCellFormula();
				break;
			// 数字类型
			case XSSFCell.CELL_TYPE_NUMERIC:
				double d = cell.getNumericCellValue();
				NumberFormat nf = NumberFormat.getNumberInstance();
				nf.setGroupingUsed(false);
				nf.setMaximumIntegerDigits(309);
				result = nf.format(d);
				break;
			// string类型
			case XSSFCell.CELL_TYPE_STRING:
				result = cell.getStringCellValue();
				break;
			// 错误类型
			case XSSFCell.CELL_TYPE_ERROR:
				result = "error:"+cell.getErrorCellValue();
				break;
			}
		}
		return result;
	}
	
	/**
	 * 获得单元格的字符串值
	 * @return Date
	 */
	public Date getDateValue(XSSFCell cell,String formateString){
		Date result = null;
		// 设置编码
//		cell.setEncoding(ENCODING);
		
		if(null != cell){
			// 判断类型
			int type = cell.getCellType();
			
			switch (type) {
			// 类型为空
			case XSSFCell.CELL_TYPE_BLANK:
				break;
			// 类型为布尔
			case XSSFCell.CELL_TYPE_BOOLEAN:
				break;
			// 公式活函数类型
			case XSSFCell.CELL_TYPE_FORMULA:
				result = parseToDate(cell.getCellFormula(), formateString);
				break;
			// 数字类型
			//case XSSFCell.CELL_TYPE_NUMERIC:
			//	result = HSSFDateUtil.getJavaDate(cell.getNumericCellValue());
			//	break;
			// string类型
			case XSSFCell.CELL_TYPE_STRING:
				result = parseToDate(cell.getStringCellValue(), formateString);
				break;
			// 错误类型
			case XSSFCell.CELL_TYPE_ERROR:
				break;
			}
		}
		return result;
	}
	
	/**
	 * 获得单元格的字符串值
	 * @return Date
	 */
	public Date getDateValue(XSSFCell cell,DateFormat formate){
		Date result = null;
		// 设置编码
//		cell.setEncoding(ENCODING);
		
		if(null != cell){
			// 判断类型
			int type = cell.getCellType();
			
			switch (type) {
			// 类型为空
			case XSSFCell.CELL_TYPE_BLANK:
				break;
			// 类型为布尔
			case XSSFCell.CELL_TYPE_BOOLEAN:
				break;
			// 公式活函数类型
			case XSSFCell.CELL_TYPE_FORMULA:
				break;
			// 数字类型
			//case XSSFCell.CELL_TYPE_NUMERIC:
			//	result = XSSFDateUtil.getJavaDate(cell.getNumericCellValue());
			//	break;
			// string类型
			case XSSFCell.CELL_TYPE_STRING:
				result = parseToDate(cell.getStringCellValue(), formate);
				break;
			// 错误类型
			case XSSFCell.CELL_TYPE_ERROR:
				break;
			}
		}
		return result;
	}
	
	/**
	 * 获得单元格的Double型值
	 * @return Double
	 */
	public Double getDoubleValue(XSSFCell cell){
		Double result = null;
		String value = this.getStringValue(cell);
		if(null != value
				&& !value.trim().equals("")){
			try{
				result = Double.parseDouble(value);
			}catch (NumberFormatException e) {
				result = null;
			}
		}
		return result;
	}
	
	/**
	 * 获得单元格的整型值，如果单元格返回为double或float类型则四舍五入
	 * @return Integer
	 */
	public Integer getIntValue(XSSFCell cell){
		Integer result = null;
		Double numeric = this.getDoubleValue(cell);
		if(null != numeric){
			// Double的intValue为下取整，加0.5做四舍五入处理
			numeric += 0.5;
			result = numeric.intValue();
		}
		return result;
	}
	
	/**
	 * 获得单元格的长整型值，如果单元格返回为double或float类型则四舍五入
	 * @return Long
	 */
	public Long getLongValue(XSSFCell cell){
		Long result = null;
		Double numeric = this.getDoubleValue(cell);
		if(null != numeric){
			// Double的longValue为下取整，加0.5做四舍五入处理
			numeric += 0.5;
			result = numeric.longValue();
		}
		return result;
	}
	
	/**
	 * 获得单元格的浮点值
	 * @return Float
	 */
	public Float getFloatValue(XSSFCell cell){
		Float result = null;
		Double numeric = this.getDoubleValue(cell);
		if(null != numeric){
			result = numeric.floatValue();
		}
		return result;
	}
	
	/**
	 * 获得单元格的Short值，如果单元格返回为double或float类型则四舍五入
	 * @return Short
	 */
	public Short getShortValue(XSSFCell cell){
		Short result = null;
		Double numeric = this.getDoubleValue(cell);
		if(null != numeric){
			result = numeric.shortValue();
		}
		return result;
	}
	
	/**
	 * 获得单元格的Byet值，如果单元格返回为double或float类型则四舍五入
	 * @return Byte
	 */
	public Byte getByteValue(XSSFCell cell){
		Byte result = null;
		Double numeric = this.getDoubleValue(cell);
		if(null != numeric){
			result = numeric.byteValue();
		}
		return result;
	}
	
	/**
	 * 获得单元格的布尔值
	 * @return Boolean
	 */
	public Boolean getBooleanValue(XSSFCell cell){
		Boolean result = null;
		String value = this.getStringValue(cell);
		if(null != value
				&& !value.trim().equals("")){
			try{
				result = Boolean.parseBoolean(value);
			}catch(NumberFormatException e){
				result = null;
			}
		}
		return result;
	}
	
	/**
	 * 获得单元格的Char值，如果单元格的string长度超过1则取第一个字符
	 * @return Character
	 */
	public Character getCharValue(XSSFCell cell){
		Character result = null;
		String value = this.getStringValue(cell);
		if(null != value
				&& !value.trim().equals("")){
			try{
				result = value.charAt(0);
			}catch(NumberFormatException e){
				result = null;
			}
		}
		return result;
	}
	
	/*
	 * 装换字符串为时间类型
	 */
	protected Date parseToDate(String dateString,String formate){
		Date result = null;
		try {
			result = new SimpleDateFormat(formate).parse(dateString);
		} catch (ParseException e) {
			result = null;
		}
		return result;
	}
	
	/*
	 * 装换字符串为时间类型
	 */
	protected Date parseToDate(String dateString,DateFormat formate){
		Date result = null;
		if(null != dateString 
				&& !dateString.trim().equals("")
				&& null != formate){
			try {
				result = formate.parse(dateString);
			} catch (ParseException e) {
				result = null;
			}
		}
		return result;
	}
	
	/**
	 * 获得当前对象的excel对象
	 * @return XSSFWorkbook
	 */
	public XSSFWorkbook getExcel(){
		return this.excel;
	}
	
	public static void main(String[] args) {
//		double d = cell.getNumericCellValue();
//		NumberFormat nf = NumberFormat.getNumberInstance();
//		nf.setGroupingUsed(false);
//		nf.setMaximumIntegerDigits(309);
//		result = nf.format(d);
//		System.out.println("double value : "+result);
	}
}
