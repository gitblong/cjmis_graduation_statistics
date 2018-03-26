package edu.zjnu.graduation_statistics.dao;

import java.sql.SQLException;
import java.util.List;

import javax.enterprise.inject.New;

import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.junit.Test;

import cn.itcast.jdbc.TxQueryRunner;
import edu.zjnu.graduation_statistics.domain.Classes;

public class ClassesDao {
	TxQueryRunner qr = new TxQueryRunner();

	/**
	 * --得到学生的年级
		SELECT Classes.C_Grade FROM Classes,Students WHERE
		Classes.C_Id = Students.S_StudyClassesId AND Students.S_Id ='EA23FB37-DF9C-49B1-93A9-920DE2E012E1'
	 */
	public String selectGradeBySId(String sId){
		String sql = "SELECT Classes.C_Grade FROM Classes,Students WHERE "
				+ "Classes.C_Id = Students.S_StudyClassesId AND Students.S_Id = ?";
		try {
			return (String) qr.query(sql, new ScalarHandler(),sId);
		} catch (SQLException e) {
			System.out.println("我错了"+e.getMessage());
		}
		return null;
	}
	
	/**
	 * 
	 * @param C_Grade
	 * @param T_tId
	 * @param T_Part
	 * @param P_Coding
	 * @param P_Level
	 * @param P_Type
	 * @param C_cId
	 * @param classId
	 * @param C_Name
	 * @return
	 */
//	 SELECT  Classes.* From
//	 TeachingPoint ,Classes,Professional,College WHERE Classes.C_tId=TeachingPoint.T_Id 
//	 And Classes.C_pId = Professional.P_Id AND Professional.P_cId =  College.C_Id 
//	 AND Classes.C_Grade='2002' 
//	 AND TeachingPoint.T_tId='44'  
//	 AND TeachingPoint.T_Part='03' 
//	 AND Professional.P_Coding='' 
//	 AND Professional.P_Level='1' 
//	 AND Professional.P_Type='1' 
//	 AND College.C_cId=''
//	 AND Classes.C_cId Like '%04%'
//	 AND Classes.C_Name Like '%a%'
//	 ORDER BY Classes.C_cId

	public List<Classes> selectAllClasses(String C_Grade,String T_tId,String T_Part,
			String P_Coding,String P_Level,String P_Type,String C_cId,
			String classId,String C_Name) {
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT  Classes.* From TeachingPoint ,Classes,Professional,College WHERE "
				+ "Classes.C_tId=TeachingPoint.T_Id And Classes.C_pId = Professional.P_Id "
				+ "AND Professional.P_cId =  College.C_Id ");
		if(!C_Grade.trim().isEmpty()&&C_Grade!=null){
			sb.append("AND Classes.C_Grade='"+C_Grade+"' "); 
		}
		if(!T_tId.trim().isEmpty()&&T_tId!=null){
			sb.append(" AND TeachingPoint.T_tId='"+T_tId+"' "); 
		}
		if(!T_Part.trim().isEmpty()&&T_Part!=null){
			sb.append("AND TeachingPoint.T_Part='"+T_Part+"' "); 
		}
		if(!P_Coding.trim().isEmpty()&&P_Coding!=null){
			sb.append("AND Professional.P_Coding='"+P_Coding+"' "); 
		}
		if(!P_Level.trim().isEmpty()&&P_Level!=null){
			sb.append("AND Professional.P_Level='"+P_Level+"' "); 
		}
		if(!P_Type.trim().isEmpty()&&P_Type!=null){
			sb.append("AND Professional.P_Type='"+P_Type+"' "); 
		}
		if(!C_cId.trim().isEmpty()&&C_cId!=null){
			sb.append("AND College.C_Id='"+C_cId+"' "); 
		}
		if(!classId.trim().isEmpty()&&classId!=null){
			sb.append("AND Classes.C_cId Like '%"+classId+"%' "); 
		}
		if(!C_Name.trim().isEmpty()&&C_Name!=null){
			sb.append("AND Classes.C_Name Like '%"+C_Name+"%' "); 
		}
		
		sb.append("ORDER BY Classes.C_cId");
		try {
			return qr.query(sb.toString(), new BeanListHandler<Classes>(Classes.class));
		} catch (SQLException e) {
			System.out.println("我错了"+e.getMessage());
		}
		return null;
		
	}
	
	public List<Classes> getAllGrade(){
		String sql = "SELECT DISTINCT C_Grade FROM dbo.Classes ORDER BY C_Grade DESC";
		try {
			List<Classes> gradeLists =  qr.query(sql, new BeanListHandler<Classes>(Classes.class));
			return gradeLists;
		} catch (SQLException e) {
			System.out.println("我错了"+e.getMessage());
			return null;
		}
	}
//	j41117010041
	public Classes findClassesById(String id) {
		String sql = "SELECT * FROM Classes where C_Id=?";
		try {
			Classes classes = (Classes) qr.query(
					sql, new BeanHandler<Classes>(Classes.class),id);
			return classes;
		} catch (SQLException e) {
			System.out.println("我错了"+e.getMessage());
			return null;
		}
	}
	
	
	public List<Classes> getClassesByGradeId(String gradeId) {
		String sql = "SELECT * FROM [cjmis].[dbo].[Classes] where C_Grade = ?";
		try {
			List<Classes> listClasses = (List<Classes>) qr.query(
					sql, new BeanListHandler<Classes>(Classes.class),gradeId);
			return listClasses;
		} catch (SQLException e) {
			System.out.println("我错了"+e.getMessage());
			return null;
		}
	}

	
}
