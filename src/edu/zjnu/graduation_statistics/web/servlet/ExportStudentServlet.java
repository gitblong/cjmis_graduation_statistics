package edu.zjnu.graduation_statistics.web.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Workbook;

import edu.zjnu.graduation_statistics.domain.CourseScheduling;
import edu.zjnu.graduation_statistics.domain.Students;
import edu.zjnu.graduation_statistics.utils.ExcelUtil;
import edu.zjnu.graduation_statistics.utils.FilePathUtils;
import edu.zjnu.graduation_statistics.utils.ResponseUtil;
import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

public class ExportStudentServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	@SuppressWarnings("deprecation")
	public void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException{
		/*
		 * 2.给上下文对象一个Runable对象，让它执行这个任务
		 */
			try {
				request.setCharacterEncoding("UTF-8");
				String fileName = request.getParameter("fileName");
				List<Students> unPassStudentList = getUnPassStudentInfo(fileName);

				if (!unPassStudentList.isEmpty()) {
					Workbook wb = ExcelUtil.fillExcelDataWithTemplate(unPassStudentList, "graduation_statistic.xls");
					unPassStudentList.clear();
					FileUtils.deleteQuietly(FilePathUtils.getFile(fileName));
					ResponseUtil.export(response, wb, "毕业审核.xls");
					
				} else {
					StringBuffer sb = new StringBuffer();
					sb.append("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
					sb.append("<HTML>");
					sb.append("  <HEAD><TITLE>毕业审核导出Excel</TITLE></HEAD>");
					sb.append("  <BODY>");
					sb.append("	<script type=" + "text/javascript" + ">");
					sb.append("	alert(\"未检测到不合格数据\");");
					sb.append("	</script>");
					sb.append("  </BODY>");
					sb.append("</HTML>");
					ResponseUtil.write(response, sb);
				}
			} catch (Exception e) {
				System.out.println("我錯了"+e.getMessage());
			}



	}

	public List<Students> getUnPassStudentInfo(String fileName) throws IOException {
		List<String> readLines = FileUtils.readLines(FilePathUtils.getFile(fileName), "utf-8");

		String fileString = readLines.toString();

		String json = fileString.substring(1, fileString.length() - 1);

		if (json.length() > 0 && json != null && !json.trim().isEmpty()) {
			JSONArray jsonArray = JSONArray.fromObject(json);
			JsonConfig jsonConfig = new JsonConfig();
			jsonConfig.setRootClass(Students.class);
			Map<String, Class> classMap = new HashMap<String, Class>();
			classMap.put("listCourseScheduling", CourseScheduling.class); // 指定JsonRpcRequest的request字段的内部类型
			jsonConfig.setClassMap(classMap);
			List<Students> unPassStudentList = (List<Students>) JSONArray.toCollection(jsonArray, jsonConfig);
			return unPassStudentList;
		}

		return null;

	}

}
