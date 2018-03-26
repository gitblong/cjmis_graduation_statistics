package edu.zjnu.graduation_statistics.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import cn.itcast.jdbc.TxQueryRunner;
import edu.zjnu.graduation_statistics.domain.College;

public class CollegeDao {
	TxQueryRunner qr = new TxQueryRunner();
	/**
	 * SELECT  DISTINCT College.C_Name From Students,TeachingPoint ,Classes,Professional,College WHERE 
Classes.C_tId=TeachingPoint.T_Id And Classes.C_pId = Professional.P_Id AND Professional.P_cId =  College.C_Id 
AND Students.S_StudyClassesId = Classes.C_Id And Students.S_StudyClassesId = 'E1AD80CC-DC39-41F0-BB62-9B5AE0E943C1'
ORDER BY College.C_Name

	 * @param classesId
	 * @return
	 */
	public String selectCollegeNameByProfessionalId(String pId) {
		String sql = "SELECT  DISTINCT C_Name From College WHERE C_Id = ? ORDER BY C_Name ";
		try {
			Object query = qr.query(
					sql, new ScalarHandler(),pId);
			String collegeName = String.valueOf(query);
			return collegeName;
		} catch (SQLException e) {
			System.out.println("我错了"+e.getMessage());
			return null;
		}
	}
	
	public List<College> selectAllCollege() {
		String sql = "SELECT * FROM [cjmis].[dbo].[College] ORDER BY C_cId asc";
		try {
			List<College> listCollege = (List<College>) qr.query(sql, new BeanListHandler<College>(College.class));
			return listCollege;
		} catch (Exception e) {
			System.out.println("我错了"+e.getMessage());
			return null;
		}
	}
}
