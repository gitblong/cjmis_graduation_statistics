package edu.zjnu.graduation_statistics.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.commons.CommonUtils;
import edu.zjnu.graduation_statistics.domain.Students;
import edu.zjnu.graduation_statistics.domain.dto.FromName;

public class ExportPageServlet extends HttpServlet {
	private List<Students> unPassStudentList;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		FromName form = CommonUtils.toBean(request.getParameterMap(), FromName.class);
		request.setAttribute("form", form);
		request.getRequestDispatcher("/WEB-INF/export.jsp").forward(request, response);
	}
	

}
