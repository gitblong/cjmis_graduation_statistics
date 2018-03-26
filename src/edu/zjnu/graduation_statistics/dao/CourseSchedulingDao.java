package edu.zjnu.graduation_statistics.dao;

import java.sql.SQLException;

import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import cn.itcast.jdbc.TxQueryRunner;
import edu.zjnu.graduation_statistics.domain.CourseScheduling;

public class CourseSchedulingDao {

	TxQueryRunner qr = new TxQueryRunner();

	/**
	 * --得到学生已修学分 未加别名
		SELECT sum(C_Credits) FROM CourseScheduling WHERE C_Id IN(
		SELECT E_csId FROM Elective_2015 where  E_csId != '' AND E_sId='EA23FB37-DF9C-49B1-93A9-920DE2E012E1'  
		)
	 * @return 
	 */
	/**
	 * sb.append("SELECT sum(CourseScheduling.C_Credits) FROM CourseScheduling,Elective_");
		sb.append(grade);
		sb.append(" WHERE CourseScheduling.C_Id IN("
				+ "SELECT Elective_");
		sb.append(grade);
		sb.append(".E_csId FROM Elective_");
		sb.append(grade);
		sb.append(" where ");
		sb.append("Elective_");
		sb.append(grade);
		sb.append(".E_csId = ? AND ");
		sb.append("Elective_");
		sb.append(grade);
		sb.append(".E_sId=?)");
	 * SELECT SUM(CourseScheduling.C_Credits) 
	 * From CourseScheduling,Elective_2015 WHERE
	 *  CourseScheduling.C_Id = Elective_2015.E_csId 
	 *  AND Elective_2015.E_csId != '' 
	 *  AND Elective_2015.E_sId='235935A1-C446-4419-A1DC-BF42B5B0902A'
	 *  
	 * @param grade
	 * @param s_Id
	 * @return 
	 * @return
	 */

	public String getTotalCreditsForStudents(String grade,String s_Id){
		int gradeInt = Integer.parseInt(grade);
		String elective = "Elective_"+grade+"";
		
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT SUM(CourseScheduling.C_Credits) TotalCredits FROM CourseScheduling ,");
		sb.append(elective);
		sb.append(" WHERE CourseScheduling.C_Id = ");
		sb.append(elective);
		sb.append(".E_csId AND ");
		sb.append(elective);
		sb.append(".E_sId=?");
		try {
			Object query = qr.query(sb.toString(), new ScalarHandler(),s_Id);
			return String.valueOf(query);
		} catch (SQLException e) {
			System.out.println("我错了"+e.getMessage());
		}
		return null;
	}
	/**
	 * --得到不合格的课程 SELECT CourseScheduling.* FROM CourseScheduling ,Elective_2015
	 * WHERE CourseScheduling.C_Id = Elective_2015.E_csId AND Elective_2015.E_Id
	 * = '0000C1FD-4F6B-4AB8-8BE5-15E8BE81962D'
	 * 
	 * @return
	 */
	public CourseScheduling selectCourseSchedulingByEId(String grade, String e_Id) {
		StringBuffer sb = new StringBuffer();
		String elective = "Elective_"+grade;
		sb.append("SELECT CourseScheduling.* FROM CourseScheduling ,");
		sb.append(elective);
		sb.append(" WHERE CourseScheduling.C_Id = ");
		sb.append(elective);
		sb.append(".E_csId AND ");
		sb.append(elective);
		sb.append(".E_Id=?");
		try {
			String string = sb.toString();
			CourseScheduling courseScheduling = (CourseScheduling) qr.query(sb.toString(),
					new BeanHandler<CourseScheduling>(CourseScheduling.class), e_Id);
			return courseScheduling;
		} catch (SQLException e) {
			System.out.println("我错了" + e.getMessage());
			return null;
		}
	}
	
	public String getCreditsByCourseId(String C_Id){
		
		String sql = "SELECT C_Credits FROM CourseScheduling WHERE C_Id = ?";
		
		try {
			Object query = qr.query(sql, new ScalarHandler(),C_Id);
			String credits = String.valueOf(query);
			return credits;
		} catch (SQLException e) {
			System.out.println("我错了"+e.getMessage());
			return null;
		}
		
	}
}
