package edu.zjnu.graduation_statistics.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.zjnu.graduation_statistics.service.ClassesService;
import net.sf.json.JSONArray;

public class GradeServlet extends HttpServlet {
	ClassesService classesService = new ClassesService();
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		
		JSONArray gradeObejct = JSONArray.fromObject(classesService.getAllGrade());
		response.getWriter().write(gradeObejct.toString());
	}

}
