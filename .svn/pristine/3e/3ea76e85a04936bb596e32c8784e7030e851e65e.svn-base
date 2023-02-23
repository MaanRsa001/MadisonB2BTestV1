<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ page language="java" import="java.util.*,com.maan.Office.DAO.*" %>
<%
	String path = request.getContextPath();
String schemeName = request.getScheme();
String serverName = request.getServerName();
String serverPort =  String.valueOf(request.getServerPort());
if(serverName.startsWith("online")){
	schemeName = "https";
	serverPort = "";
}
String basePath = schemeName + "://"
		+ serverName + (!"".equalsIgnoreCase(serverPort)?(":"+serverPort):"")
		+ path + "/";
	CoverageBean coverageBean = new CoverageBean();
%>

<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
	<base href="<%=basePath%>">
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta name="description" content="">
	<meta name="keywords" content="Maan Sarovar, Sea Trans, Marine Insurance">	
	<meta name="author" content="">
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/css/demo.css" />
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/css/font-awesome.min.css" />
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/css/bootstrap-multiselect.css" />
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/bootstrap/css/datepicker3.css">	 
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/bootstrap/css/slimmenu.css">
	<link href="${pageContext.request.contextPath}/bootstrap/css/footable-0.1.css" rel="stylesheet" type="text/css">
	<link href="${pageContext.request.contextPath}/bootstrap/css/table-res.css" rel="stylesheet" type="text/css">
	<link href="${pageContext.request.contextPath}/bootstrap/css/easy-responsive-tabs.css" rel="stylesheet" type="text/css">
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css" />
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/css/style.css" />
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js"></script>
	<script src="${pageContext.request.contextPath}/bootstrap/js/modernizr.custom.63321.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/bootstrap/js/jquery-1.9.1.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/bootstrap/js/jquery-1.9.1.min.js"></script>
	<style type="text/css">
	.panel-heading {
		padding: 5px;
	}
	.header {
		text-align: left;
	}
	</style>
	<title>Edit Formuala Details</title>
	<%
		String error = (String) request.getAttribute("error");
		error = error == null ? "" : error;

		String proId = (String) request.getParameter("product_id");
		proId = proId == null ? "0" : proId;
		
		String schemeId = (String) request.getParameter("scheme");
		schemeId = schemeId == null ? "0" : schemeId;

		String contentTypeID = request.getParameter("contentType");
		contentTypeID = contentTypeID == null ? "0" : contentTypeID;

		String coverageID = request.getParameter("coverage");
		coverageID = coverageID == null ? "0" : coverageID;
		
		String branchCode = (String)session.getAttribute("LoginBranchCode");
		branchCode = branchCode == null ? "0" : branchCode;
		
		String coverid = request.getParameter("coverage") == null ? "": request.getParameter("coverage");
		String subcoverid = request.getParameter("subcoverage") == null ? "0": request.getParameter("subcoverage");
		String requestfrom = request.getParameter("requestfrom") == null ? "": request.getParameter("requestfrom");
		String msg = request.getParameter("msg") == null ? "": request.getParameter("msg");
		String schemeValue = coverageBean.getIDRelatedValue("SCHEME_NAME","OFS_SCHEME_MASTER", "SCHEME_ID", schemeId);
		String contentTypeValue = coverageBean.getIDRelatedValue("CONTENT_DESCRIPTION", "OFS_CONTENT_MASTER","CONTENT_TYPE_ID", contentTypeID);
		contentTypeValue=contentTypeValue==null?"None":"None";
		String coverageValue = coverageBean.getIDRelatedValue("COVERAGES_NAME", "OFS_MASTER", "COVERAGES_ID", coverageID);
		
		String[][] formulaDetails=request.getAttribute("formulaDetails")==null?new String[0][0]:(String[][])request.getAttribute("formulaDetails");
		if(formulaDetails.length<=0)
		{
			formulaDetails=coverageBean.getFormulaDetails(proId, schemeId, coverid, branchCode);
		}
		
		String formula="";
		String remarks="";
		String status="";
		if(formulaDetails!=null && formulaDetails.length>0)
		{
			formula=formulaDetails[0][0];
			remarks=formulaDetails[0][1];
			status=formulaDetails[0][2];
		}
	%>
</head>
<body>
<div id="page" class="content">	
	<div class="body">
	<form action="CoverageController" method="post" name="form1">
	<div class="row">
		<div class="col-xs-12">
			<div class="panel panel-primary">
				<div class="panel-heading"> Sub Coverages Master </div>
				<div class="panel-body">
					<div class="row">
						<div class="col-xs-12">
							<div class="textV">Product</div>
							<div class="tboxV">&nbsp;:&nbsp;Office Shield</div>
						</div>
						<div class="col-xs-12">
							<div class="textV">Scheme</div>
							<div class="tboxV">&nbsp;:&nbsp;<%=schemeValue%></div>
						</div>
						<div class="col-xs-12">
							<div class="textV">Content Type</div>
							<div class="tboxV">&nbsp;:&nbsp;<%=contentTypeValue%></div>
						</div>
						<div class="col-xs-12">
							<div class="textV">Coverage Name</div>
							<div class="tboxV">&nbsp;:&nbsp;<%=coverageValue%></div>
						</div>
					</div>
					<br class="clear" />
					<div class="row">
						<div class="col-xs-12">
							<div class="panel panel-primary">
								<div class="panel-body" style="overflow-x: scroll; overflow-y: none;">
									<%	if (error.length() > 0) {%>
									<div class="row">
										<div class="col-xs-12">
											<font color="red"><%=error%> </font>
										</div>
									</div>
									<br/>
									<%	} else if(msg.length()>0) { %>
									<div class="row">
										<div class="col-xs-12">
											<font color="red"><%=msg%> </font>
										</div>
									</div>
									<br/>
									<%	} %>
									<div class="row">
										<table class="footable">
											<thead>
											<tr>
												<th>Formula (Premium Calcuation)</th>
												<th>Remarks</th>
												<th style="width: 150px; min-width: 150px; max-width: 150px;">Status</th>
											</tr>
											</thead>
											<tbody>
											<tr>
												<td>
													<textarea cols="40" rows="8" class="inputBoxA" name="formula" maxlength="1000" onkeyup="return ismaxlength(this)"><%=formula==null?"":formula%></textarea>
												</td>
												<td>
													<textarea cols="40" rows="8" class="inputBoxA" name="remarks" maxlength="1000" onkeyup="return ismaxlength(this)"><%=remarks==null?"":remarks%></textarea>
												</td>
												<td>
													<input type="radio" class="textbox" name="status" value="Y" <%=status!=null && status.equals("Y")? "checked" : ""%>> &nbsp;Active</br>
													<input type="radio" class="textbox" name="status" value="N" <%=status!=null && status.equals("N") ? "checked" : ""%>>&nbsp;Deactive
													<input type="hidden" name="requestForm" id="requestForm">
													<input type="hidden" name="coverage" value="<%=coverageID%>">
													<input type="hidden" name="scheme" value="<%=schemeId%>">
													<input type="hidden" name="product_id" value="<%=proId%>">
													<input type="hidden" name="coverage" value="<%=coverid%>">
													<input type="hidden" name="subcoverage" value="<%=subcoverid%>">
													<input type="hidden" name="branchCode" value="<%=branchCode%>">
												</td>
											</tr>
											</tbody>
										</table>
									</div>
								</div>
							</div>
						</div>
					</div>
					<br class="clear" />
					<div class="row">
						<div class="col-xs-12" align="center">
							<input type="button" onclick="return SubmitSaveAndExit()" class="btn btn-sm btn-success" value="Submit">
							&nbsp;&nbsp;&nbsp;
							<input type="button"  onclick="fnSubmit()" class="btn btn-sm btn-danger" value="Back">
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	</form>
	</div>
</div>
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/bootstrap/js/jquery.slimmenu.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/bootstrap/js/jquery.easing.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/bootstrap/js/easy-responsive-tabs.js"></script>	
<script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap-datepicker.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap-multiselect.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap-3.2.0.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/bootstrap/js/app-js.js" type="text/javascript"></script>
<script type="text/javascript">
function ismaxlength(obj) {
	var mlength=obj.getAttribute? parseInt(obj.getAttribute("maxlength")) : ""
	//alert(mlength);
	if (obj.getAttribute && obj.value.length>mlength)
		obj.value=obj.value.substring(0,mlength)
}

function SubmitSaveAndExit() {
	document.getElementById('requestForm').value = 'formulaInsert';
	document.form1.submit();
	return false;
}

function fnSubmit() {
	window.close();
}
</script>
</html>