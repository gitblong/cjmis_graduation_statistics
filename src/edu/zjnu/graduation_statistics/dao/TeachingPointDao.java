package edu.zjnu.graduation_statistics.dao;

import java.sql.SQLException;
import java.util.List;

import javax.enterprise.inject.New;

import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import cn.itcast.jdbc.TxQueryRunner;
import edu.zjnu.graduation_statistics.domain.TeachingPoint;

public class TeachingPointDao {
	TxQueryRunner qr = new TxQueryRunner();
	
	public List<TeachingPoint> selecTeachingPoint(String C_GradeId ,String C_CollegeId,String T_Part){
		StringBuffer sb = new StringBuffer();
//		SELECT Distinct TeachingPoint.* From TeachingPoint ,Classes,Professional where TeachingPoint.T_Id = Classes.C_tId AND Classes.C_Grade = '2010'
//				And Classes.C_pId = Professional.P_Id AND Professional.P_cId =  '0912F27B-49FC-4083-8C64-44DE615A6BC2' And TeachingPoint.T_Part = '01' ORDER BY TeachingPoint.T_tId
		sb.append("SELECT Distinct TeachingPoint.* From TeachingPoint ,Classes ");
		if (!C_CollegeId.trim().isEmpty()&&C_CollegeId!=null) {
			sb.append(",Professional ");
		}
		sb.append("where TeachingPoint.T_Id = Classes.C_tId AND Classes.C_Grade = '"+C_GradeId+"' ");
		if (!C_CollegeId.trim().isEmpty()&&C_CollegeId!=null) {
			sb.append("And Classes.C_pId = Professional.P_Id AND Professional.P_cId =  '"+C_CollegeId+"' ");
		}
		if (!T_Part.trim().isEmpty()&&T_Part!=null) {
			sb.append("And TeachingPoint.T_Part = '"+T_Part+"'");
		}
		sb.append("ORDER BY TeachingPoint.T_tId");
		try {
			return qr.query(sb.toString(), new BeanListHandler<TeachingPoint>(TeachingPoint.class));
		} catch (SQLException e) {
			
			System.out.println("我错了"+e.getMessage());
			return null;
		}
	}
	/**
	 * 


--得到教学点
SELECT  DISTINCT TeachingPoint.T_Name From Students,TeachingPoint ,Classes,Professional,College WHERE 
Classes.C_tId=TeachingPoint.T_Id And Classes.C_pId = Professional.P_Id AND Professional.P_cId =  College.C_Id 
AND Students.S_StudyClassesId = Classes.C_Id And Students.S_StudyClassesId = 'E1AD80CC-DC39-41F0-BB62-9B5AE0E943C1'
ORDER BY TeachingPoint.T_Name

	 * @param classesId
	 * @return
	 */
	public String selectTeaPointNameByClassesId(String classesId){
		String sql = "SELECT  DISTINCT T_Name From TeachingPoint,Classes WHERE T_Id=C_tID AND C_Id = ? ORDER BY T_Name";
		try {
			Object query = qr.query(sql, new ScalarHandler(),classesId);
			String teachingName = String.valueOf(query);
			return teachingName;
		} catch (SQLException e) {
			System.out.println("我错了\n"+e.getMessage());
		}
		return null;
	}
	
	public TeachingPoint findTeachingPointById(String id) {
		String sql = "SELECT * FROM [cjmis].[dbo].[TeachingPoint] where T_Id=?";
		try {
			TeachingPoint teachingPoint = (TeachingPoint) qr.query(
					sql, new BeanHandler<TeachingPoint>(TeachingPoint.class),id);
			return teachingPoint;
		} catch (SQLException e) {
			System.out.println("我错了"+e.getMessage());
			return null;
		}
	}
	
	public List<TeachingPoint> selectAllTeachingPart() {
		String sql = "SELECT DISTINCT T_Part FROM [cjmis].[dbo].[TeachingPoint]";
		try {
			List<TeachingPoint> listTeachingPoints = (List<TeachingPoint>) qr.query(
					sql, new BeanListHandler<TeachingPoint>(TeachingPoint.class));
			return listTeachingPoints;
		} catch (SQLException e) {
			System.out.println("我错了"+e.getMessage());
			return null;
		}
	}
}
