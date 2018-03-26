package edu.zjnu.graduation_statistics.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.commons.CommonUtils;
import edu.zjnu.graduation_statistics.domain.Classes;
import edu.zjnu.graduation_statistics.domain.dto.RequestInfo;
import edu.zjnu.graduation_statistics.service.ClassesService;
import net.sf.json.JSONArray;

public class ClassesServlet extends HttpServlet {
	ClassesService classesService = new ClassesService();
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("UTF-8");
		RequestInfo requestInfo = CommonUtils.toBean(request.getParameterMap(), RequestInfo.class);
		List<Classes> classesLists = classesService.selectAllClasses(requestInfo);
		JSONArray classesJson = JSONArray.fromObject(classesLists);
		response.getWriter().write(classesJson.toString());
	}

}
