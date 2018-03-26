package edu.zjnu.graduation_statistics.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.zjnu.graduation_statistics.domain.TeachingPoint;
import edu.zjnu.graduation_statistics.service.TeachingPointService;
import net.sf.json.JSONArray;

public class TeachingPartServlet extends HttpServlet {
	
	TeachingPointService teachingPointService = new TeachingPointService();
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("UTF-8");
		List<TeachingPoint> teachingPartLists = teachingPointService.selectAllTeachingPart();
		JSONArray teachingPartJson = JSONArray.fromObject(teachingPartLists);
		response.getWriter().write(teachingPartJson.toString());
	}

}
