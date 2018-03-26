package edu.zjnu.graduation_statistics.dao;

import java.sql.SQLException;
import java.util.List;

import javax.enterprise.inject.New;

import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.junit.Test;

import cn.itcast.jdbc.TxQueryRunner;
import edu.zjnu.graduation_statistics.domain.Professional;

public class ProfessionalDao {
	TxQueryRunner qr = new TxQueryRunner();
	
	/**
	 * 
		--得到专业应修学分
		--P_LeastCredits
		SELECT Professional.* FROM Professional ,Classes, Students 
		WHERE Professional.P_Id = Classes.C_pId AND 
		Classes.C_Id = Students.S_StudyClassesId AND 
		Students.S_Id = 'EA23FB37-DF9C-49B1-93A9-920DE2E012E1'

	 */
	public Professional selectProfessionalBySId(String s_Id){
		String sql= "SELECT Professional.* FROM Professional ,Classes, Students "
				+ "WHERE Professional.P_Id = Classes.C_pId AND "
				+ "Classes.C_Id = Students.S_StudyClassesId AND "
				+ "Students.S_Id =?";
		try {
			return qr.query(sql, new BeanHandler<Professional>(Professional.class),s_Id);
		} catch (SQLException e) {
			System.out.println("我错了");
		}
		return null;
	}
/*	SELECT  * From TeachingPoint ,Classes,Professional,College where
 *  SELECT DISTINCT Professional.P_Coding,Professional.P_Name FROM Professional WHERE Professional.P_Coding In (
SELECT DISTINCT Professional.P_Coding From TeachingPoint ,Classes,Professional,College where Classes.C_tId=TeachingPoint.T_Id  
And Classes.C_pId = Professional.P_Id AND Professional.P_cId =  College.C_Id
)ORDER BY Professional.P_Coding

*/
	
/**
 * 
 * @param grade
 * @param collegeId
 * @param t_PartId
 * @param t_tId
 * @param p_Type
 * @param p_Level
 * @return
 */
	public List<Professional> selectAllProfessional(String grade,String collegeId,String t_PartId,String t_tId,String p_Type,String p_Level){
		StringBuffer sb = new StringBuffer("SELECT DISTINCT Professional.P_Coding,Professional.P_Name FROM Professional WHERE Professional.P_Coding In ( "
				+ "SELECT DISTINCT Professional.P_Coding From TeachingPoint ,Classes,Professional,College "
				+ "WHERE Classes.C_tId=TeachingPoint.T_Id And Classes.C_pId = Professional.P_Id AND Professional.P_cId =  College.C_Id ");
		if (!grade.trim().isEmpty()&&grade!=null) {
			sb.append(" AND Classes.C_Grade = '"+grade+"'");
		}
		if (!collegeId.trim().isEmpty()&&collegeId!=null) {
			sb.append(" AND College.C_Id = '"+collegeId+"'");
		}
		if (!t_PartId.trim().isEmpty()&&t_PartId!=null) {
			sb.append(" AND TeachingPoint.T_Part = '"+t_PartId+"'");
		}
		if (!t_tId.trim().isEmpty()&&t_tId!=null) {
			sb.append(" AND TeachingPoint.T_tId = '"+t_tId+"'");
		}
		if (!p_Type.trim().isEmpty()&&p_Type!=null) {
			sb.append(" AND Professional.P_Type = '"+p_Type+"'");
		}
		if (!p_Level.trim().isEmpty()&&p_Level!=null) {
			sb.append(" AND Professional.P_Level = '"+p_Level+"'");
		}
		sb.append(") ORDER BY Professional.P_Coding");
		try {
			return qr.query(sb.toString(), new BeanListHandler<Professional>(Professional.class));
		} catch (SQLException e) {
			System.out.println("我错了"+e.getMessage());
		}
		
		return null;
	}
	

	/**
	 * SELECT  DISTINCT Professional.P_Id,P_cId,P_Name,P_Type,P_Level,P_Structure,P_ProfessionalType,P_Branches,P_Coding,P_LeastCredits From Students,TeachingPoint ,Classes,Professional,College WHERE 
Classes.C_tId=TeachingPoint.T_Id And Classes.C_pId = Professional.P_Id AND Professional.P_cId =  College.C_Id 
AND Students.S_StudyClassesId = Classes.C_Id And Students.S_StudyClassesId = 'E1AD80CC-DC39-41F0-BB62-9B5AE0E943C1'
ORDER BY Professional.P_Id

	 * @param classesId
	 * @return
	 */
	public Professional selectProfessionalByClassesId(String classesId) {
		String sql = "SELECT  DISTINCT P_Id,P_cId,P_Name,"
				+ "P_Type,P_Level,P_Structure,P_ProfessionalType,P_Branches,"
				+ "P_Coding,P_LeastCredits From Professional,Classes WHERE "
				+ "P_Id = C_pId AND C_Id = ? ORDER BY P_Id ";
		try {
			Professional professional = (Professional) qr.query(
					sql, new BeanHandler<Professional>(Professional.class),classesId);
			return professional;
		} catch (SQLException e) {
			System.out.println("我错了"+e.getMessage());
			return null;
		}
	}
	
}
