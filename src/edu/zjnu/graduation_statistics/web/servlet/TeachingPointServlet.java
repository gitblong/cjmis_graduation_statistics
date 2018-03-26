package edu.zjnu.graduation_statistics.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.commons.CommonUtils;
import edu.zjnu.graduation_statistics.domain.TeachingPoint;
import edu.zjnu.graduation_statistics.domain.dto.RequestInfo;
import edu.zjnu.graduation_statistics.service.TeachingPointService;
import net.sf.json.JSONArray;

public class TeachingPointServlet extends HttpServlet {
	TeachingPointService teachingPointService = new TeachingPointService();

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("UTF-8");
		RequestInfo teachingPointInfo = CommonUtils.toBean(request.getParameterMap(), RequestInfo.class);
		String C_GradeId = teachingPointInfo.getGradeId();
		String C_CollegeId = teachingPointInfo.getCollegeId();
		String T_Part = teachingPointInfo.getPartId();
		List<TeachingPoint> teachingPointLists = teachingPointService.selectTeachingPoints(C_GradeId, C_CollegeId,
				T_Part);
		JSONArray teachingPointJson = JSONArray.fromObject(teachingPointLists);
		response.getWriter().write(teachingPointJson.toString());
	}

}
