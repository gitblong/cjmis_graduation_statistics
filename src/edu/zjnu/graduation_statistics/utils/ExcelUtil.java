package edu.zjnu.graduation_statistics.utils;

import java.io.InputStream;
import java.sql.ResultSet;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import edu.zjnu.graduation_statistics.domain.Classes;
import edu.zjnu.graduation_statistics.domain.CourseScheduling;
import edu.zjnu.graduation_statistics.domain.Professional;
import edu.zjnu.graduation_statistics.domain.Students;

public class ExcelUtil {

	public static void fillExcelData(ResultSet rs,Workbook wb,String[] headers)throws Exception{
		int rowIndex=0;
		Sheet sheet=wb.createSheet();
		Row row=sheet.createRow(rowIndex++);
		for(int i=0;i<headers.length;i++){
			row.createCell(i).setCellValue(headers[i]);
		}
		while(rs.next()){
			row=sheet.createRow(rowIndex++);
			for(int i=0;i<headers.length;i++){
				row.createCell(i).setCellValue(rs.getObject(i+1).toString());
			}
		}
	}
	
	public static Workbook fillExcelDataWithTemplate(List<Students> studentsList,String templateFileName)throws Exception{
		InputStream inp=ExcelUtil.class.getResourceAsStream("/edu/zjnu/graduation_statistics/template/"+templateFileName);
		POIFSFileSystem fs=new POIFSFileSystem(inp);
		Workbook workbook = new HSSFWorkbook(fs);
		Sheet sheet=workbook.getSheetAt(0);
		// 获取列数
		int colNums=sheet.getRow(0).getPhysicalNumberOfCells();
		int rowIndex=2;
		for (int j = 0; j < studentsList.size(); j++) {
			int k = 0;
			Row row=sheet.createRow(rowIndex++);
			row.createCell(k++).setCellValue(studentsList.get(j).getTeachingName());//教学点
			row.createCell(k++).setCellValue(studentsList.get(j).getS_ResearchNum());//准考证号
			row.createCell(k++).setCellValue(studentsList.get(j).getS_JClassNum());//进修班号
			row.createCell(k++).setCellValue(studentsList.get(j).getS_JClassName());//进修班级名称
			row.createCell(k++).setCellValue(studentsList.get(j).getS_JstuNum());//进修学号
			Classes classes = studentsList.get(j).getClasses();//
			row.createCell(k++).setCellValue(classes.getC_Name());//班级名称
			row.createCell(k++).setCellValue(studentsList.get(j).getS_StuNum());//学号
			row.createCell(k++).setCellValue(xmlUtil.parseAtrById("Changes.xml", "changes", studentsList.get(j).getS_Changes()));//异动
			row.createCell(k++).setCellValue(studentsList.get(j).getS_ExamineeNum());//考生号
			row.createCell(k++).setCellValue(studentsList.get(j).getS_Name());//姓名
			row.createCell(k++).setCellValue(studentsList.get(j).getS_Sex());//性别
			row.createCell(k++).setCellValue(studentsList.get(j).getCollegeName());//学院名称
			Professional professional = studentsList.get(j).getProfessional();//
			row.createCell(k++).setCellValue(professional.getP_Coding());//专业编号
			row.createCell(k++).setCellValue(professional.getP_Name());//专业名称
			row.createCell(k++).setCellValue(classes.getC_cId());//班级编号
			row.createCell(k++).setCellValue(classes.getC_Structure());//学制
			row.createCell(k++).setCellValue(xmlUtil.parseAtrById("Professional.xml", "branches", professional.getP_Branches()));//学位类别
			row.createCell(k++).setCellValue(xmlUtil.parseAtrById("Professional.xml", "type", professional.getP_Type()));//学习形式
			row.createCell(k++).setCellValue(xmlUtil.parseAtrById("Professional.xml", "level", professional.getP_Level()));//层次
			row.createCell(k++).setCellValue(classes.getC_AdmissionTime());//入学日期
			row.createCell(k++).setCellValue(studentsList.get(j).getS_Address());//通讯地址
			row.createCell(k++).setCellValue(xmlUtil.parseAtrById("Students.xml", "race", studentsList.get(j).getS_Race()));//名族studentsList.get(j).getS_Race()
			row.createCell(k++).setCellValue(studentsList.get(j).getS_IDCard());//身份证号
			row.createCell(k++).setCellValue(xmlUtil.parseAtrById("Students.xml", "politicalFeature", studentsList.get(j).getS_PoliticalFeature()));//政治面貌
			row.createCell(k++).setCellValue(studentsList.get(j).getS_TotalScore());//总分
			row.createCell(k++).setCellValue(classes.getC_Grade());//年级
			row.createCell(k++).setCellValue(studentsList.get(j).getS_Zip());//邮政编码
			row.createCell(k++).setCellValue(studentsList.get(j).getS_Phone());//联系电话
			row.createCell(k++).setCellValue(professional.getP_LeastCredits());//应修学分
			row.createCell(k++).setCellValue(studentsList.get(j).getS_TotalCredits());//已修学分
			List<CourseScheduling> listCourseScheduling = studentsList.get(j).getListCourseScheduling();//
			String courses= "";
			for (CourseScheduling courseScheduling : listCourseScheduling) {
				String c_cName = courseScheduling.getC_cName();
				courses+=c_cName+",";
				
			}
			if (courses.trim().isEmpty()||courses==null) {
				courses += "<该生未达到应修学分>,";
			}
			String course = courses.substring(0, courses.length()-1);//不合格课程
			row.createCell(k++).setCellValue(course);
			
		}
		return workbook;
	}
	
	public static String formatCell(HSSFCell hssfCell){
		if(hssfCell==null){
			return "";
		}else{
			if(hssfCell.getCellType()==HSSFCell.CELL_TYPE_BOOLEAN){
				return String.valueOf(hssfCell.getBooleanCellValue());
			}else if(hssfCell.getCellType()==HSSFCell.CELL_TYPE_NUMERIC){
				return String.valueOf(hssfCell.getNumericCellValue());
			}else{
				return String.valueOf(hssfCell.getStringCellValue());
			}
		}
	}
}
