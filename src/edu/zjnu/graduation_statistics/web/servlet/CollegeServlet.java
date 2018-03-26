package edu.zjnu.graduation_statistics.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.zjnu.graduation_statistics.domain.College;
import edu.zjnu.graduation_statistics.service.CollegeService;
import net.sf.json.JSONArray;

public class CollegeServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		CollegeService collegeService = new CollegeService();
		List<College> collegeList = collegeService.selectAllCollege();
		String collegeListJson = JSONArray.fromObject(collegeList).toString();
		response.getWriter().write(collegeListJson);
	}

}
