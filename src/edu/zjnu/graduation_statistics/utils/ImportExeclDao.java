package edu.zjnu.graduation_statistics.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ImportExeclDao {
	/** 总行数 */
	private int totalRows = 0;
	/** 总列数 */
	private int totalCells = 0;
	/** 错误信息 */
	private String errorInfo;
	private Workbook mWorkBook;

	public Workbook getmWorkBook() {
		return mWorkBook;
	}

	public void setmWorkBook(Workbook mWorkBook) {
		this.mWorkBook = mWorkBook;
	}

	public void setErrorInfo(String errorInfo) {
		this.errorInfo = errorInfo;
	}

	/** 构造方法 */
	public ImportExeclDao() {
	}

	/**
	 * @描述：得到总行数
	 * @参数：@return
	 * @返回值：int
	 */
	public int getTotalRows() {
		return totalRows;
	}

	/**
	 * @描述：得到总列数
	 * @参数：@return
	 * @返回值：int
	 */

	public int getTotalCells() {
		return totalCells;
	}

	/**
	 * @描述：得到错误信息
	 * @参数：@return
	 * @返回值：String
	 */

	public String getErrorInfo() {
		return errorInfo;
	}

	/**
	 * @描述：验证excel文件
	 * @参数：@param filePath 文件完整路径
	 * @参数：@return
	 * @返回值：boolean
	 */

	public boolean validateExcel(String filePath) {
		/** 检查文件名是否为空或者是否是Excel格式的文件 */
		if (filePath == null || !(WDWUtil.isExcel2003(filePath) || WDWUtil.isExcel2007(filePath))) {
			errorInfo = "文件名不是excel格式";
			return false;
		}
		/** 检查文件是否存在 */
		File file = new File(filePath);
		if (file == null || !file.exists()) {
			errorInfo = "文件不存在";
			return false;
		}
		return true;
	}

	/**
	 * @throws IOException
	 * @throws InvalidFormatException
	 * @描述：读取数据
	 * @参数：@param Workbook
	 * @参数：@return
	 * @返回值：List<List<String>>
	 */

	public String read(String filePath) throws IOException, InvalidFormatException {
		File file = new File(filePath);
		// 创建输入流
		FileInputStream inputStream = new FileInputStream(file);
		// 创建工作簿读取
		Workbook workbook = WorkbookFactory.create(inputStream);
		// XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
		// Workbook workbook = WDWUtil.getWorkbook(filePath);
		// /** 得到第一个shell */
		Sheet sheet = workbook.getSheetAt(0);
		/** 得到Excel的行数 */
		this.totalRows = sheet.getPhysicalNumberOfRows();
		/** 循环Excel的行 */
		// List<String> rowList = new ArrayList<String>();
		String numStringAll = "";
		for (int r = 0; r < this.totalRows; r++) {
			Row row = sheet.getRow(r);
			if (row == null) {
				continue;
			}
			Cell cell = row.getCell(0);
			String cellValue = "";
			if (null != cell) {
				// 以下是判断数据的类型
				switch (cell.getCellType()) {
				case HSSFCell.CELL_TYPE_NUMERIC: // 数字
					cellValue = cell.getNumericCellValue() + "";
					break;
				case HSSFCell.CELL_TYPE_STRING: // 字符串
					cellValue = cell.getStringCellValue();
					break;
				case HSSFCell.CELL_TYPE_BOOLEAN: // Boolean
					cellValue = cell.getBooleanCellValue() + "";
					break;
				case HSSFCell.CELL_TYPE_FORMULA: // 公式
					cellValue = cell.getCellFormula() + "";
					break;
				case HSSFCell.CELL_TYPE_BLANK: // 空值
					cellValue = "";
					break;
				case HSSFCell.CELL_TYPE_ERROR: // 故障
					cellValue = "非法字符";
					break;
				default:
					cellValue = "未知类型";
					break;
				}
			}
			if (cellValue != null && cellValue != "" && !cellValue.isEmpty()) {

				String numString = "'" + cellValue + "',";
				numStringAll += numString;
			}
		}

		return numStringAll.substring(0, numStringAll.length() - 1);
	}

}
