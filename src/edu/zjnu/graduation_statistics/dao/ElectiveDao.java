package edu.zjnu.graduation_statistics.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.itcast.jdbc.TxQueryRunner;
import edu.zjnu.graduation_statistics.domain.Elective;

public class ElectiveDao {
	TxQueryRunner qr = new TxQueryRunner();
//	0912F27B-49FC-4083-8C64-44DE615A6BC2

	public Elective findElectiveByCsId(String id) {
		String sql = "SELECT * FROM [cjmis].[dbo].[Elective_2008] where E_csId=?";
		try {
			Elective elective = (Elective) qr.query(
					sql, new BeanHandler<Elective>(Elective.class),id);
			return elective;
		} catch (SQLException e) {
			System.out.println("我错了"+e.getMessage());
			return null;
		}
	}
	
	public Elective findElectiveById(String id) {
		String sql = "SELECT * FROM [cjmis].[dbo].[Elective_2008] where E_Id=?";
		try {
			Elective elective = (Elective) qr.query(
					sql, new BeanHandler<Elective>(Elective.class),id);
			return elective;
		} catch (SQLException e) {
			System.out.println("我错了"+e.getMessage());
			return null;
		}
	}
	/**
	 * SELECT * FROM Elective_2015 where  
	 * E_csId != '' AND E_sId='EA23FB37-DF9C-49B1-93A9-920DE2E012E1' 
	 * @param id
	 * @return
	 */
	public List<Elective> selectElectiveBySId(String grade,String id) {
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT * FROM Elective_");
		sb.append(grade);
		sb.append(" where E_csId != '' AND E_sId='"+id+"'");
		try {
			return qr.query(sb.toString(), new BeanListHandler<Elective>(Elective.class));
			
		} catch (SQLException e) {
			System.out.println("我错了"+e.getMessage());
			return null;
		}
	}
	public List<Elective> allElective() {
		String sql = "SELECT * FROM Elective_2012";
		try {
			List<Elective> listElective = (List<Elective>) qr.query(
					sql, new BeanListHandler<Elective>(Elective.class));
			return listElective;
		} catch (SQLException e) {
			System.out.println("我错了"+e.getMessage());
			return null;
		}
	}
	public List<Elective> getElectiveByGradeId(String gradeId) {
		String sql = "SELECT * FROM [cjmis].[dbo].[Elective_+"+gradeId+"]";
		try {
			List<Elective> listElective = (List<Elective>) qr.query(
					sql, new BeanListHandler<Elective>(Elective.class));
			return listElective;
		} catch (SQLException e) {
			System.out.println("我错了"+e.getMessage());
			return null;
		}
	}
}
