<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head id="Head1">
<meta charset="UTF-8">
<title>毕业审核数据</title>
<link href="css/admin_in.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/json2.js"></script>
</head>

<body>
	<div class="Tm_border">
		<table cellspacing="2" cellpadding="5" width="100%" border="0">
			<tbody>
			<br />
				<div class="Tm_Msg">
					<div style="text-align: center;">
						<span id="Label1">请上传Excel文件</span>
					</div>
				</div>
				
				<tr>
					<td align="center" class="txt_title_02" colspan="2">上传Excel导出数据</td>
				</tr>
				<tr>
					<td class="td_left" style="width: 30%;">导入Excel：</td>
					<td class="td_right">
						<form action="<%=basePath %>UploadExcelServlet" target="_blank"
							method="post" enctype="multipart/form-data">
							<input type="file" name="importExcel" id="importExcel"> <input
								type="submit" value="导出数据">
						</form>
					</td>
				</tr>
		</table>
	</div>
	<form id="exportForm" target="_blank" name="exportFrom" method="post"
		action="<%=basePath %>ExportPageServlet" id="exportFrom">

		<div>
		<br />
			<div class="Tm_Msg">
				<div style="text-align: center;">
					<span id="Label1">请选择导出条件并导出数据</span>
				</div>
			</div>
			
			<div class="Tm_border">
				<table cellspacing="2" cellpadding="5" width="100%" border="0">
					<tbody>
						<tr>
							<td align="center" class="txt_title_02" colspan="2">毕 业 审 核
								数 据</td>
						</tr>

						<tr>
							<td class="td_left" style="width: 30%;">学&nbsp; 院：</td>
							<td class="td_right"><select name="college" id="college"
								onchange="teachingPointSelect();professionName();classesInfo();">
									<option selected="selected" value="">请选择</option>
							</select></td>
						</tr>
						<tr>
							<td class="td_left">教学点区域：</td>
							<td class="td_right"><select name="teachingPart"
								id="teachingPart"
								onchange="teachingPointSelect();professionName();classesInfo();">
									<option value="" selected="selected">请选择</option>

							</select></td>
						</tr>
						<tr>
							<td class="td_left">教学点：</td>
							<td class="td_right"><select name="teachingPoint"
								id="teachingPoint" onchange="professionName();classesInfo();">
									<option selected="selected" value="">请选择教学点</option>
							</select></td>
						</tr>
						<tr>
							<td class="td_left">年&nbsp; 级：</td>
							<td class="td_right"><select name="grade" id="grade"
								onchange="teachingPointSelect();professionName();classesInfo();">
									<option value="" selected="selected">请选择</option>
							</select></td>
						</tr>
						<tr>
							<td class="td_left">学习形式：</td>
							<td class="td_right"><select name="professionalType"
								id="professionalType" onchange="professionName();classesInfo();">
									<option selected="selected" value="">请选择</option>
							</select></td>
						</tr>
						<tr>
							<td class="td_left">层&nbsp; 次：</td>
							<td class="td_right"><select name="professionalLevel"
								id="professionalLevel"
								onchange="professionName();classesInfo();">
									<option value="" selected="selected">请选择</option>
							</select></td>
						</tr>
						<tr>
							<td class="td_left">专&nbsp; 业：</td>
							<td class="td_right"><select name="professionalName"
								id="professionalName" onchange="classesInfo();">
									<option selected="selected" value="">请选择专业</option>

							</select></td>
						</tr>
						<tr>
							<td class="td_left">班级搜索：</td>
							<td class="td_right"><input name="searchClasses" type="text"
								id="searchClasses" style="width:134px;" />&nbsp; <input
								type="button" name="searchClasses_Btn" value="搜索"
								onclick="classesInfo();" id="searchClasses_Btn" /></td>
						</tr>
						<tr>
							<td class="td_left">班&nbsp; 级：</td>
							<td class="td_right"><select name="classes" id="classes">
									<option value="" selected="true">请选择班级</option>
							</select></td>
						</tr>
						<tr>
							<td class="td_button" colspan="2">
								<!-- <input type="button" name="selectStudents" value="开始查询" id="selectStudents" onclick="selectUnPassStudent_Btn();"/> -->
								<input type="submit" name="exportData" value="导出数据"
								id="exportData" />&nbsp;&nbsp;
							</td>
						</tr>



					</tbody>
				</table>
			</div>
		</div>
	</form>
	
</body>
<script type="text/javascript">
$("#exportForm").submit(function(event) {
		var grade = $('#grade option:selected').val();
		if(grade=="" || grade==null){
			alert("必须选择年级");
			return false;
		}
	});
window.onload=function(){
	
	collegeSelect();
	teachingPartSelect();
	GradeSelect();
	professionalTypeSelect();
	professionalLevelSelect();
	professionName();
	classesInfo();
}
function collegeSelect(){
$.ajax({
			url : "<c:url value="/CollegeServlet" />",
			dataType : "json",
			type : "POST",
			success : function(result) {
				if(result!=null){
				var json=JSON.stringify(result); 
				var objects=JSON.parse(json); 
					for(var i = 0;i<objects.length;i++){
						var object = objects[i];
						//创建<option>元素
						var optionEle = document.createElement("option");
						//给<option>元素的value属性赋值
						optionEle.value = object.c_Id//给<option>的实际值赋值为pid,而不是name
						var textNode = document.createTextNode("["+object.c_cId+"]"+object.c_Name);
						optionEle.appendChild(textNode);
						
						$('#college').append(optionEle); 
					}
				}
			}
		});
}

function teachingPartSelect(){

$.ajax({
			url : "<c:url value="/TeachingPartServlet" />",
			dataType : "json",
			type : "POST",
			success : function(result) {
				if(result!=null){
				var json=JSON.stringify(result); 
				var objects=JSON.parse(json); 
					for(var i = 0;i<objects.length;i++){
						var object = objects[i];
						//创建<option>元素
						var optionEle = document.createElement("option");
						//给<option>元素的value属性赋值
						optionEle.value = object.t_Part//给<option>的实际值赋值为pid,而不是name
						var textNode = document.createTextNode("["+object.t_Part+"]"+object.partName);
						optionEle.appendChild(textNode);
						$('#teachingPart').append(optionEle); 
					}
				}
			}
		});
}

function GradeSelect(){
	$.ajax({
			url : "<c:url value="/GradeServlet" />",
			dataType : "json",
			type : "POST",
			success : function(result) {
				if(result!=null){
				var json=JSON.stringify(result); 
				var objects=JSON.parse(json); 
					for(var i = 0;i<objects.length;i++){
						var object = objects[i];
						//创建<option>元素
						var optionEle = document.createElement("option");
						//给<option>元素的value属性赋值
						optionEle.value = object.c_Grade;//给<option>的实际值赋值为pid,而不是name
						var textNode = document.createTextNode(object.c_Grade+"级");
						optionEle.appendChild(textNode);
						$('#grade').append(optionEle); 
					}
				}
			}
		});
}

function professionalTypeSelect(){
	$.ajax({
			url : "<c:url value="/ProfessionTypeServlet" />",
			dataType : "json",
			type : "POST",
			success : function(result) {
				if(result!=null){
				var json=JSON.stringify(result); 
				var objects=JSON.parse(json); 
					for(var i = 0;i<objects.length;i++){
						var object = objects[i];
						//创建<option>元素
						var optionEle = document.createElement("option");
						//给<option>元素的value属性赋值
						optionEle.value = object.p_Type//给<option>的实际值赋值为pid,而不是name
						var textNode = document.createTextNode("["+object.p_Type+"]"+object.typeName);
						optionEle.appendChild(textNode);
						$('#professionalType').append(optionEle); 
					}
				}
			}
		});
}
function professionalLevelSelect(){
	$.ajax({
			url : "<c:url value="/ProfessionLevelServlet" />",
			dataType : "json",
			type : "POST",
			success : function(result) {
				if(result!=null){
				var json=JSON.stringify(result); 
				var objects=JSON.parse(json); 
					for(var i = 0;i<objects.length;i++){
						var object = objects[i];
						//创建<option>元素
						var optionEle = document.createElement("option");
						//给<option>元素的value属性赋值
						optionEle.value = object.p_Level//给<option>的实际值赋值为pid,而不是name
						var textNode = document.createTextNode("["+object.p_Level+"]"+object.levelName);
						optionEle.appendChild(textNode);
						$('#professionalLevel').append(optionEle); 
					}
				}
			}
		});
}
function teachingPointSelect(){
	var gradeId = $('#grade option:selected').val();
	var collegeId = $('#college option:selected').val();
	var partId = $('#teachingPart option:selected').val();
	var data = {"gradeId":gradeId,"collegeId":collegeId,"partId":partId};
	if(gradeId!=""&&gradeId!=null){
	$.ajax({
			url : "<c:url value="/TeachingPointServlet" />",
			dataType : "json",
			type : "GET",
			contentType: "application/json;charset=utf-8",
            data : data,
			success : function(result) {
				/*
					0.先要清空原先的city
					1.得到服务器发送过来的所有市
					2.使用每个市生成<option>元素添加到<select id = "city">中
							
				 */
						/*清空<select id="city">中的选项  */
						
						var teachingPoint = document.getElementById("teachingPoint");
						//获取select中的所有子元素
						var teachingPointOptionArray = teachingPoint
								.getElementsByTagName("option");
						while (teachingPointOptionArray.length > 1) {//这里只控制循环的次数
							teachingPoint.removeChild(teachingPointOptionArray[1]);//只删除1下标，不会删除0
						}
				if(result!=null){
				var json=JSON.stringify(result); 
				var objects=JSON.parse(json); 
					for(var i = 0;i<objects.length;i++){
						var object = objects[i];
						//创建<option>元素
						var optionEle = document.createElement("option");
						//给<option>元素的value属性赋值
						optionEle.value = object.t_tId//给<option>的实际值赋值为pid,而不是name
						var textNode = document.createTextNode("["+object.t_tId+"]"+object.t_Name);
						optionEle.appendChild(textNode);
						$('#teachingPoint').append(optionEle); 
					}
				}
			}
		});
		}
}

function professionName(){
	var grade = $('#grade option:selected').val();
	var collegeId = $('#college option:selected').val();
	var t_Part = $('#teachingPart option:selected').val();
	var t_tId = $('#teachingPoint option:selected').val();
	var p_Type = $('#professionalType option:selected').val();
	var p_Level = $('#professionalLevel option:selected').val();
	
	var data = {"grade":grade,"collegeId":collegeId,"t_Part":t_Part,"t_tId":t_tId,"p_Type":p_Type,"p_Level":p_Level};
	
	$.ajax({
			url : "<c:url value="/ProfessionalServlet" />",
			dataType : "json",
			type : "GET",
			contentType: "application/json;charset=utf-8",
            data : data,
			success : function(result) {
				/*
					0.先要清空原先的city
					1.得到服务器发送过来的所有市
					2.使用每个市生成<option>元素添加到<select id = "city">中
							
				 */
						/*清空<select id="city">中的选项  */
						
						var professionalName = document.getElementById("professionalName");
						//获取select中的所有子元素
						var professionalNameOptionArray = professionalName
								.getElementsByTagName("option");
						while (professionalNameOptionArray.length > 1) {//这里只控制循环的次数
							professionalName.removeChild(professionalNameOptionArray[1]);//只删除1下标，不会删除0
						}
				if(result!=null){
				var json=JSON.stringify(result); 
				var objects=JSON.parse(json); 
					for(var i = 0;i<objects.length;i++){
						var object = objects[i];
						//创建<option>元素
						var optionEle = document.createElement("option");
						//给<option>元素的value属性赋值
						optionEle.value = object.p_Coding//给<option>的实际值赋值为pid,而不是name
						var textNode = document.createTextNode("["+object.p_Coding+"]"+object.p_Name);
						optionEle.appendChild(textNode);
						$('#professionalName').append(optionEle); 
					}
				}
			}
		});
		
}

function classesInfo(){
	var gradeId = $('#grade option:selected').val();
	var collegeId = $('#college option:selected').val();
	var partId = $('#teachingPart option:selected').val();
	var t_tId = $('#teachingPoint option:selected').val();
	var pType = $('#professionalType option:selected').val();
	var pLevel = $('#professionalLevel option:selected').val();
	var pcoding = $('#professionalName option:selected').val();
	var classResearch = $('#searchClasses').val();
	var data = {"gradeId":gradeId,"collegeId":collegeId,"partId":partId,"t_tId":t_tId,"pType":pType,"pLevel":pLevel,"pcoding":pcoding,"classResearch":classResearch};
	
	$.ajax({
			url : "<c:url value="/ClassesServlet" />",
			dataType : "json",
			type : "GET",
			contentType: "application/json;charset=utf-8",
            data : data,
			success : function(result) {
				/*
					0.先要清空原先的city
					1.得到服务器发送过来的所有市
					2.使用每个市生成<option>元素添加到<select id = "city">中
							
				 */
						/*清空<select id="city">中的选项  */
						
						var classes = document.getElementById("classes");
						//获取select中的所有子元素
						var classesOptionArray = classes
								.getElementsByTagName("option");
						while (classesOptionArray.length > 1) {//这里只控制循环的次数
							classes.removeChild(classesOptionArray[1]);//只删除1下标，不会删除0
						}
				if(result!=null){
				var json=JSON.stringify(result); 
				var objects=JSON.parse(json); 
					for(var i = 0;i<objects.length;i++){
						var object = objects[i];
						//创建<option>元素
						var optionEle = document.createElement("option");
						//给<option>元素的value属性赋值
						optionEle.value = object.c_Id//给<option>的实际值赋值为pid,而不是name
						var textNode = document.createTextNode("["+object.c_cId+"]"+object.c_Name);
						optionEle.appendChild(textNode);
						$('#classes').append(optionEle); 
					}
				}
			}
		});
		
}

function selectUnPassStudent_Btn(){
	var gradeId = $('#grade option:selected').val();
	var collegeId = $('#college option:selected').val();
	var partId = $('#teachingPart option:selected').val();
	var t_tId = $('#teachingPoint option:selected').val();
	var pType = $('#professionalType option:selected').val();
	var pLevel = $('#professionalLevel option:selected').val();
	var pcoding = $('#professionalName option:selected').val();
	var classResearch = $('#searchClasses').val();
	var classesId = $('#classes').val();
	
	var data = {"gradeId":gradeId,"collegeId":collegeId,"partId":partId,"t_tId":t_tId,"pType":pType,"pLevel":pLevel,"pcoding":pcoding,"classResearch":classResearch,"classesId":classesId};
	$.ajax({
			url : "<c:url value="/StudentServlet" />",
			dataType : "json",
			type : "GET",
			contentType: "application/json;charset=utf-8",
            data : data
			
		});
		
}
function exportStudent(){
	var grade = $('#grade option:selected').val();
	if(grade=="" || grade==null){
		alert("必须选择年级");
		return;
	}
	var college = $('#college option:selected').val();
	var part = $('#teachingPart option:selected').val();
	var tId = $('#teachingPoint option:selected').val();
	var type = $('#professionalType option:selected').val();
	var level = $('#professionalLevel option:selected').val();
	var coding = $('#professionalName option:selected').val();
	var claResearch = $('#searchClasses').val();
	var classes = $('#classes').val();
	
	var data = {"gradeId":grade,"collegeId":college,"partId":part,"t_tId":tId,"pType":type,"pLevel":level,"pcoding":coding,"classResearch":claResearch,"classesId":classes};
	var str = "gradeId="+grade+"&collegeId="+college+"&partId="+part+"&t_tId="+tId+"&pType="+type+"&pLevel="+level+"&pcoding="+coding+"&classResearch="+claResearch+"&classesId="+classes;
	
	window.open('<%= basePath%>/ExportStudentServlet?'+str)
	/*window.open("index.jsp");*/
		
}	

</script>

</html>