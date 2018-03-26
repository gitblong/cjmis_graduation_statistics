package edu.zjnu.graduation_statistics.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;

import cn.itcast.commons.CommonUtils;
import edu.zjnu.graduation_statistics.domain.Students;
import edu.zjnu.graduation_statistics.domain.dto.FromName;
import edu.zjnu.graduation_statistics.service.StudentsService;
import edu.zjnu.graduation_statistics.utils.FilePathUtils;
import net.sf.json.JSON;
import net.sf.json.JSONSerializer;

public class DownloadExcelServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		StudentsService studentsService = new StudentsService();
		FromName form = CommonUtils.toBean(request.getParameterMap(), FromName.class);
		List<Students> unPassStudentList = studentsService.selectUnPassStudentsInfo(form);
		JSON unPassStudentsJson = JSONSerializer.toJSON(unPassStudentList);
		String fileName = String.valueOf(System.currentTimeMillis())+".json";
		FileUtils.writeStringToFile(FilePathUtils.getFile(fileName), unPassStudentsJson.toString(), "utf-8");
		
		response.getWriter().write(fileName);
	}

}
