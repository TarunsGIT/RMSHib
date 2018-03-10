<%@page import="in.co.mss.rmshib.util.HTMLUtility"%>
<%@page import="org.w3c.dom.html.HTMLUListElement"%>
<%@page import="java.util.List"%>
<%@page import="in.co.mss.rmshib.controller.MarksheetCtl"%>
<%@page import="in.co.mss.rmshib.util.DataUtility"%>
<%@page import="in.co.mss.rmshib.util.ServletUtility"%>
<%@page import="in.co.mss.rmshib.controller.ORSView"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<jsp:include page="Home.jsp"></jsp:include>
<jsp:useBean id="dto" class="in.co.mss.rmshib.dto.MarksheetDTO"
	scope="request"></jsp:useBean>
<script type="text/javascript">
	function AllowSingleSpaceNotInFirstAndLast() {
		var obj = document.getElementById('inputEmail3');
		obj.value = obj.value.replace(/^\s+|\s+$/g, "");
		var CharArray = obj.value.split(" ");
		if (CharArray.length > 1) {
			alert("Roll No. cannot contain space");
			return false;
		}
		return true;
	}
</script>

<div class="container text-center" style="margin-left: 60;">
	<h1 class="text-primary">
		<b> <%
 	long id = DataUtility.getLong(request.getParameter("id"));
 	if (id > 0) {
 %> Edit Marksheet <%
 	} else {
 %> Add Marksheet <%
 	}
 %>
		</b>
	</h1>
	<br>

	<%
		List l = (List) request.getAttribute("studentList");
	%>
	<div class="alert alert-success" role="alert"
		<%=ServletUtility.getSuccessMessage(request).equals("") ? "hidden"
					: ""%>>
		<%=ServletUtility.getSuccessMessage(request)%></div>
	<div class="alert alert-danger" role="alert"
		<%=ServletUtility.getErrorMessage(request).equals("") ? "hidden"
					: ""%>><%=ServletUtility.getErrorMessage(request)%></div>


</div>


<div class="col-sm-offset-4">

	<form class="form-horizontal" action="<%=ORSView.MARKSHEET_CTL%>"
		method="post">

		<input type="hidden" name="createdBy" value="<%=dto.getCreatedBy()%>">
		<input type="hidden" name="id" value="<%=dto.getId()%>"> <input
			type="hidden" name="modifiedBy" value="<%=dto.getModifiedBy()%>">
		<input type="hidden" name="createdDatetime"
			value="<%=dto.getCreatedDatetime()%>"> <input type="hidden"
			name="modifiedDatetime" value="<%=dto.getModifiedDatetime()%>">

		<div class="form-group">
			<%
				long id2 = DataUtility.getLong(request.getParameter("id"));
			%>
			<label for="text" class="col-sm-3 control-label">
				<div class="text-left">Roll No:</div>
			</label>
			<div class="col-sm-4" style="margin-left: -150;">
				<input type="text" class="form-control" id="inputEmail3"
					placeholder="Roll No" name="rollNo"
					value="<%=DataUtility.getStringData(dto.getRollNo())%>"
					<%=(id2 > 0) ? "readonly" : ""%>>
			</div>
			<label class="control-label text-danger" for="inputError1"><%=ServletUtility.getErrorMessage("rollNo", request)%></label>
		</DIV>

		<div class="form-group">
			<label for="inputPassword3" class="col-sm-3 control-label">
				<div class="text-left">Name:</div>
			</label>
			<div class="col-sm-4" style="margin-left: -150;">
				<%=HTMLUtility.getList("studentId",
					String.valueOf(dto.getStudentId()), l)%>
			</div>
			<label class="control-label text-danger" for="inputError1"><%=ServletUtility.getErrorMessage("studentId", request)%></label>
		</div>

		<%-- 
          <div class="form-group">
			<label for="inputPassword3" class="col-sm-2 control-label">
			studentId:</label>
			<div class="col-sm-4">
				<input type="text" class="form-control" id="inputEmail3"
					name="subject"
					value="<%=DataUtility.getStringData(dto.getStudentId())%>"
					placeholder="Name">
			</div>
			<label class="control-label text-danger" for="inputError1"><%=ServletUtility.getErrorMessage("subject", request)%></label>
		</div>
		 --%>
		<div class="form-group">
			<label for="inputPassword3" class="col-sm-3 control-label">
				<div class="text-left">Physics:</div>
			</label>
			<div class="col-sm-4" style="margin-left: -150;">
				<input type="text" class="form-control" id="inputEmail3"
					name="physics"
					value="<%=DataUtility.getStringData(dto.getPhysics()).equals("0") ? ""
					: DataUtility.getStringData(dto.getPhysics())%>"
					placeholder="Physics">
			</div>
			<label class="control-label text-danger" for="inputError1"><%=ServletUtility.getErrorMessage("physics", request)%></label>
		</div>

		<div class="form-group">
			<label for="inputPassword3" class="col-sm-3 control-label">
				<div class="text-left">Chemistry:</div>
			</label>
			<div class="col-sm-4" style="margin-left: -150;">
				<input type="text" class="form-control" id="inputEmail3"
					name="chemistry"
					value="<%=DataUtility.getStringData(dto.getChemistry()).equals("0") ? ""
					: DataUtility.getStringData(dto.getChemistry())%>"
					placeholder="Chemistry">
			</div>
			<label class="control-label text-danger" for="inputError1"><%=ServletUtility.getErrorMessage("chemistry", request)%></label>
		</div>

		<div class="form-group">
			<label for="inputPassword3" class="col-sm-3 control-label">
				<div class="text-left">Maths:</div>
			</label>
			<div class="col-sm-4" style="margin-left: -150;">
				<input type="text" class="form-control" id="inputEmail3"
					name="maths"
					value="<%=DataUtility.getStringData(dto.getMaths()).equals("0") ? ""
					: DataUtility.getStringData(dto.getMaths())%>"
					placeholder="Maths">
			</div>
			<label class="control-label text-danger" for="inputError1"><%=ServletUtility.getErrorMessage("maths", request)%></label>
		</div>
		<div class="col-sm-offset-2 col-sm-10">
			<button type="submit" class="btn btn-primary" name="operation"
				onclick="return AllowSingleSpaceNotInFirstAndLast();"
				style="margin-left: -78;" value="<%=MarksheetCtl.OP_SAVE%>">
				<span class="glyphicon glyphicon-floppy-save"></span> Save
			</button>
			&emsp;&emsp;
			<button type="submit" class="btn btn-primary" name="operation"
				value="<%=MarksheetCtl.OP_CANCEL%>">
				<span class="glyphicon glyphicon-remove"></span> Cancel
			</button>
			<%-- 			<%
				if (dto.getId() > 0l) {
			%>
			<button type="submit" class="btn btn-primary" name="operation"
				value="<%=MarksheetCtl.OP_DELETE%>">Delete</button>
			<%
				}
			%>
 --%>
		</div>


	</form>
</div>
<%@ include file="Footer.jsp"%>