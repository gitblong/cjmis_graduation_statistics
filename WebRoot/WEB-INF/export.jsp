
<%@page import="edu.zjnu.graduation_statistics.domain.dto.FromName"%>
<%@page import="edu.zjnu.graduation_statistics.domain.dto.RequestInfo"%>
<%@page import="cn.itcast.commons.CommonUtils"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">

<title>毕业审核导出</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<!-- <script type="text/javascript" src="js/base-loading.js"></script> -->
<script type="text/javascript" src="js/jquery.js"></script>
</head>

<body>
	<form action="<%=basePath%>ExportStudentServlet" id="exportForm"
		method="post">
		<input type="hidden" id="fileName" name="fileName"> <input
			type="hidden" id="submitId" name="submitId">
	</form>

</body>
<script type="text/javascript">
	var _PageHeight = document.body.clientHeight || document.documentElement.clientHeight,
		_PageWidth = document.body.clientWidth || document.documentElement.clientWidth;
	var _LoadingTop = _PageHeight > 61 ? (_PageHeight - 61) / 2 : 0,
		_LoadingLeft = _PageWidth > 215 ? (_PageWidth - 215) / 2 : 0;
	var headContent = '<div id="loadingDiv" style="position:absolute;left:0;width:100%;height:' + _PageHeight + 'px;top:0;background:#f3f8ff;opacity:1;filter:alpha(opacity=80);z-index:10000;">';
	var searchLoadingContent = '<div id="searchloading" style="position: absolute; cursor1: wait; left: ' + _LoadingLeft + 'px; top:' + _LoadingTop + 'px; width: auto; height: 57px; line-height: 57px; padding-left: 50px; padding-right: 5px; background: #fff url(img/loading.gif) no-repeat scroll 5px 10px; border: 2px solid #95B8E7; color: #696969; font-family:\'Microsoft YaHei\';">正在检查不合格学生，请耐心等待...</div>';
	var downLoadingContent = '<div id="downloading" style="position: absolute; cursor1: wait; left: ' + _LoadingLeft + 'px; top:' + _LoadingTop + 'px; width: auto; height: 57px; line-height: 57px; padding-left: 50px; padding-right: 5px; background: #fff url(img/loading.gif) no-repeat scroll 5px 10px; border: 2px solid #95B8E7; color: #696969; font-family:\'Microsoft YaHei\';">正在生成Excel，请耐心等待...</div>';
	var endContent = '</div>';

	var mention = '正在检查不合格学生，请耐心等待...';
	/* var _LoadingHtml = '<div id="loadingDiv" style="position:absolute;left:0;width:100%;height:' + _PageHeight + 'px;top:0;background:#f3f8ff;opacity:1;filter:alpha(opacity=80);z-index:10000;"><div id="searchloading" style="position: absolute; cursor1: wait; left: ' + _LoadingLeft + 'px; top:' + _LoadingTop + 'px; width: auto; height: 57px; line-height: 57px; padding-left: 50px; padding-right: 5px; background: #fff url(img/loading.gif) no-repeat scroll 5px 10px; border: 2px solid #95B8E7; color: #696969; font-family:\'Microsoft YaHei\';">正在检查不合格学生，请耐心等待...</div><div id="downloading" style="position: absolute; cursor1: wait; left: ' + _LoadingLeft + 'px; top:' + _LoadingTop + 'px; width: auto; height: 57px; line-height: 57px; padding-left: 50px; padding-right: 5px; background: #fff ; border: 2px solid #95B8E7; color: #696969; font-family:\'Microsoft YaHei\';">已生成Excel表格！！！</div></div>';
	 */
	var _LoadingHtml = headContent + searchLoadingContent + downLoadingContent + endContent;
	document.write(_LoadingHtml);

	window.onload = function() {
		document.getElementById("searchloading").style.display = "block";
		document.getElementById("downloading").style.display = "none";
		var college = "${empty (form.college)?null:(form.college)}";
		var teachingPart = "${empty (form.teachingPart)?null:(form.teachingPart)}";
		var teachingPoint = "${empty (form.teachingPoint)?null:(form.teachingPoint)}";
		var grade = "${empty (form.grade)?null:(form.grade)}";
		var professionalType = "${empty (form.professionalType)?null:(form.professionalType)}";
		var professionalLevel = "${empty (form.professionalLevel)?null:(form.professionalLevel)}";
		var professionalName = "${empty (form.professionalName)?null:(form.professionalName)}";
		var searchClasses = "${empty (form.searchClasses)?null:(form.searchClasses)}";
		var classes = "${empty (form.grade)?null:(form.classes)}";
		var data = "college=" + college + "&teachingPart=" + teachingPart + "&teachingPoint=" + teachingPoint + "&grade=" + grade + "&professionalType=" + professionalType + "&professionalLevel=" + professionalLevel + "&professionalName=" + professionalName + "&searchClasses=" + searchClasses + "&classes=" + classes;

		$.ajax({
			url : "<%=basePath%>DownloadExcelServlet",
			type : "POST",
			async : true,
			data : data,
			success : function(result) {
				$("#fileName").val(result);
				$("form").submit(function(event) {
					document.getElementById("searchloading").style.display = "none";
					document.getElementById("downloading").style.display = "block";
				});
				$("#exportForm").submit();
			}
		});

		$(document).ajaxStop(function() {
			setTimeout(function() {
				document.getElementById("downloading").style.display = "none";

			}, 4500);
		});
	}
</script>

</html>
