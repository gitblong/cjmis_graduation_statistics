package edu.zjnu.graduation_statistics.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.commons.CommonUtils;
import edu.zjnu.graduation_statistics.domain.Professional;
import edu.zjnu.graduation_statistics.domain.dto.ProfessionalName;
import edu.zjnu.graduation_statistics.service.ProfessionalService;
import net.sf.json.JSONArray;

public class ProfessionalServlet extends HttpServlet {
	ProfessionalService professionalService = new ProfessionalService();
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("UTF-8");
		ProfessionalName request_ProfessionalName = CommonUtils.toBean(request.getParameterMap(), ProfessionalName.class);
		List<Professional> professionalTypeLists = professionalService.selectAllProfessional(request_ProfessionalName);
		JSONArray professionJson = JSONArray.fromObject(professionalTypeLists);
		response.getWriter().write(professionJson.toString());
	}

}
