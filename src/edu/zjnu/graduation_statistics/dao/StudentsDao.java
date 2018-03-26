package edu.zjnu.graduation_statistics.dao;

import java.sql.SQLException;
import java.util.List;

import javax.enterprise.inject.New;

import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.google.common.base.Joiner;

import cn.itcast.jdbc.TxQueryRunner;
import edu.zjnu.graduation_statistics.domain.Students;

public class StudentsDao {
	TxQueryRunner qr = new TxQueryRunner();

	/**
	 * 通过学号查询所有的 
	 * @param studentNumbers
	 * @return
	 */
	public List<Students> selectStudentByStuNum(String studentNumbers){
		String numString = "("+studentNumbers+")";
		System.out.println(numString);
		StringBuffer stringBuffer = new StringBuffer("SELECT * FROM [cjmis].[dbo].[Students] where S_StuNum IN ");
		stringBuffer.append(numString);
		try {
			return qr.query(stringBuffer.toString(), new BeanListHandler<Students>(Students.class));
		} catch (SQLException e) {
			System.out.println("我错了"+e.getMessage());
			return null;
		}
	}
	/**
	 * 通过各种下拉列表的条件查找所有的学生
	 * 
	 * 	SELECT * FROM Students WHERE S_StudyClassesId IN (
		SELECT  Classes.C_Id From TeachingPoint ,Classes,Professional,College WHERE 
		Classes.C_tId=TeachingPoint.T_Id And Classes.C_pId = Professional.P_Id AND Professional.P_cId =  College.C_Id 
		AND Classes.C_Grade='2002' 
		AND TeachingPoint.T_tId='44'  
		AND TeachingPoint.T_Part='03' 
		AND Professional.P_Coding='' 
		AND Professional.P_Level='1' 
		AND Professional.P_Type='1' 
		AND College.C_cId=''
		AND Classes.C_cId Like '%04%'
		AND Classes.C_Name Like '%a%'
		//	C_Grade, T_tId, T_Part, P_Coding, P_Level, P_Type, C_cId, classId, C_Name
		)AND S_StuNum not like 'j%' And S_Changes IN (00,04,06,08,10,14,18)
		ORDER BY Students.S_StuNum
	 * @param id
	 * @return 
	 * @return
	 */
	public List<Students> selectStudentByClassesId(String C_Grade,String T_tId,String T_Part,
			String P_Coding,String P_Level,String P_Type,String C_cId,
			String classId,String C_Name,String C_Id) {
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT * FROM Students WHERE S_ClassesId IN ( "
				+ "SELECT  Classes.C_Id From TeachingPoint ,Classes,Professional,College WHERE "
				+ "Classes.C_tId=TeachingPoint.T_Id And Classes.C_pId = Professional.P_Id "
				+ "AND Professional.P_cId =  College.C_Id ");
		if(!C_Grade.trim().isEmpty()&&C_Grade.trim()!=null&&C_Grade.trim()!="null"){
			sb.append("AND Classes.C_Grade='"+C_Grade+"' "); 
		}
		if(!T_tId.trim().isEmpty()&&T_tId.trim()!=null){
			sb.append(" AND TeachingPoint.T_tId='"+T_tId+"' "); 
		}
		if(!T_Part.trim().isEmpty()&&T_Part.trim()!=null){
			sb.append("AND TeachingPoint.T_Part='"+T_Part+"' "); 
		}
		if(!P_Coding.trim().isEmpty()&&P_Coding.trim()!=null){
			sb.append("AND Professional.P_Coding='"+P_Coding+"' "); 
		}
		if(!P_Level.trim().isEmpty()&&P_Level.trim()!=null){
			sb.append("AND Professional.P_Level='"+P_Level+"' "); 
		}
		if(!P_Type.trim().isEmpty()&&P_Type.trim()!=null){
			sb.append("AND Professional.P_Type='"+P_Type+"' "); 
		}
		if(!C_cId.trim().isEmpty()&&C_cId.trim()!=null){
			sb.append("AND College.C_Id='"+C_cId+"' "); 
		}
		if(!classId.trim().isEmpty()&&classId.trim()!=null){
			sb.append("AND Classes.C_cId Like '%"+classId+"%' "); 
		}
		if(!C_Name.trim().isEmpty()&&C_Name.trim()!=null){
			sb.append("AND Classes.C_Name Like '%"+C_Name+"%' "); 
		}
		sb.append(") ");
		if(!C_Id.trim().isEmpty()&&C_Id.trim()!=null){
			sb.append("AND Students.S_ClassesId = '"+C_Id+"' "); 
		}
//		And S_Changes IN (00,01,03,04,05,06,08,10,14,16,18,22)
		sb.append("AND S_StuNum not like 'j%' AND S_Diploma='' "
				+ "ORDER BY Students.S_StuNum");
		try {
			return qr.query(sb.toString(), new BeanListHandler<Students>(Students.class));
		} catch (SQLException e) {
			System.out.println("我错了"+e.getMessage());
			return null;
		}
	}
	
	// 552603BE-5938-407E-9BB0-CC4EE93B4F48
	public String findStuById(String id) {
		String sql = "SELECT S_StuNum FROM [cjmis].[dbo].[Stu] where S_Id=?";
		try {
			String jStuNum = (String) qr.query(sql, new ScalarHandler(), id);
			return jStuNum;
		} catch (SQLException e) {
			System.out.println("我错了" + e.getMessage());
			return null;
		}
	}

	public List<Students> findStuByStudyClassesId(String classesId) {
		String sql = "SELECT top 100* FROM [cjmis].[dbo].[Students] where  S_StuNum not like 'j%' "
				+ "And S_Changes IN (00,04,06,08,10,14,18)"
				+ " And S_StudyClassesId = ?";
		try {
			List<Students> listStudents = (List<Students>) qr.query(sql, new BeanListHandler<Students>(Students.class),
					classesId);
			return listStudents;
		} catch (SQLException e) {
			System.out.println("我错了" + e.getMessage());
			return null;
		}
	}

	public Students selectStusById(String id) {
		String sql = "SELECT * FROM Stu where S_Id=?";
		try {
			Students student = (Students) qr.query(sql, new BeanHandler<Students>(Students.class), id);
			return student;
		} catch (SQLException e) {
			System.out.println("我错了" + e.getMessage());
			return null;
		}
	}

	public List<Students> allStudnets() {
		String sql = "SELECT top 100* FROM [cjmis].[dbo].[Students] where  S_StuNum not like 'j%' And"
				+ " S_Changes IN (00,04,06,08,10,14,18)";
		try {
			List<Students> listStudents = (List<Students>) qr.query(sql, new BeanListHandler<Students>(Students.class));
			return listStudents;
		} catch (SQLException e) {
			System.out.println("我错了" + e.getMessage());
			return null;
		}
	}

}
