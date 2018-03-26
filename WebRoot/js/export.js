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
