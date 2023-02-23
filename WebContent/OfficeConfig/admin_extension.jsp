<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ page language="java" import="java.util.*,com.maan.Office.DAO.*" %>
<%@page import="com.maan.report.dao.ReportDAO"%>
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
	<title>Extension Master</title>
	<%
		String mode=(String)request.getAttribute("mode")==null?"":(String)request.getAttribute("mode");
		String scheme=(String)request.getAttribute("scheme")==null?"":(String)request.getAttribute("scheme");
		String cont_type=(String)request.getAttribute("contentType")==null?"":(String)request.getAttribute("contentType");
		String cont_value=(String)request.getParameter("cont_value")==null?"":(String)request.getParameter("cont_value");
		String remarks=(String)request.getParameter("remarks")==null?"":(String)request.getParameter("remarks");
		String rsacode=(String)request.getParameter("rsacode")==null?"":(String)request.getParameter("rsacode");
		String status=(String)request.getParameter("status")==null?"":(String)request.getParameter("status");
		GregorianCalendar gc = new GregorianCalendar();
		String effectiveDate = gc.get(Calendar.DATE) + "-"
					+ (gc.get(Calendar.MONTH) + 1) + "-"
					+ gc.get(Calendar.YEAR);  
     	String content_id="";
 		String values[][] = (String[][]) request.getAttribute("values") ==null?new String[0][0] : (String[][]) request.getAttribute("values"); 
	%>
</head>
<body>
<div id="page" class="content">
	<div class="header">
		<%@ include file="../admin/header.jsp"%>
	</div>
	<div class="body">
		<div class="row">
			<div class="col-xs-12">
				<form name="contentForm" method="post">
					<div class="panel panel-primary">
						<div class="panel-heading"> Extension Master </div>
						<div class="panel-body">
							<%
								String error = (String) request.getAttribute("error");
								error = error == null ? "" : error;
								if (error.length() > 0) {
							%>
							<div class="row">
								<div class="col-xs-12">
									<font color="red"><%=error%> </font>
								</div>
							</div>
							<br/>
							<%	} %>
							<div class="row">
								<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
									<div class="textV">Product</div>
									<div class="tboxV"><b>Office Shield</b></div>
								</div>
								<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
									<div class="text">Coverage Name</div>
									<div class="tbox">
										<select name="cover" class="inputBoxS">
											<option value="select"> Select Scheme </option>
											<%  for (int index = 0; index < values.length; ++index) { %>
												<option value="<%=values[index][0]%>"
												<%=scheme.equalsIgnoreCase(values[index][0]) ? "selected": ""%>> <%=values[index][1]%> </option>
											<% } %>
										</select>   
									</div>
								</div>
								<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
									<div class="text">Extension description</div>
									<div class="tbox">
										<div class="inputAppend">
											<input name="extn_desc" type="text" size="25" maxlength="50" value="<%=cont_value%>" class="inputBox1" style="border: none;background: transparent;">
											<a type="button" href="<%=path%>/OfficeConfig/admin_extension_edit.jsp?mode=extensionedit" class="btn btn-sm btn-warning inputButton" style="height: 28px;">Edit</a>
			                           </div>   
									</div>
								</div>
								<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
									<div class="text"> Effective Date</div>
									<div class="tbox">
										<input type="text" name="effectiveDate" value="<%=effectiveDate%>"	size="30" maxlength="20" class="inputBox datepicker" size="10"	id="effectiveDate" ReadOnly>
									</div>
								</div>
								<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
									<div class="text">Remarks</div>
									<div class="tbox">
										<textarea name="remarks" class="inputBoxA" style="width: 100%" rows="2"><%=remarks%></textarea>
									</div>
								</div>
								<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
									<div class="text">Core Code</div>
									<div class="tbox"><input name="rsacode" type="text" size="10" maxlength="10" value="<%=rsacode%>" class="inputBox"></div>
								</div>
								<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
									<div class="text">Status</div>
									<div class="tbox">
										<select name="status" class="inputBoxS">
											<option value="Y" <%=(status.equalsIgnoreCase("Y"))?"selected":""%>> Activate </option>
											<option value="N" <%=(status.equalsIgnoreCase("N"))?"selected":""%>> DeActivate </option>
										</select>
									</div>
								</div>
							</div>
							<br class="clear" />
							<div class="row">
								<div class="col-xs-12 col-sm-12 col-md-12 col-xs-12" align="center">
									<input type="button" value="Proceed" class="btn btn-sm btn-success"  onClick="return SubmitSaveAndContinue('<%=mode%>','<%=scheme%>','<%=cont_type%>')" tabindex="31">
								</div>
							</div>
						</div>
					</div>
					<input type="hidden" name="linkFrom" value="">
					<input type="hidden" name="scheme" value="">
					<input type="hidden" name="cont_type" value="">
				</form>
			</div>
		</div>
	</div>
</div>
</body>
<script type="text/javascript">
var cal1 = new calendar1(document.forms["contentForm"].elements["effectiveDate"]);
	cal1.year_scroll = true;
	cal1.time_comp = false;
function SubmitSaveAndContinue(val,scheme,cont_type) {
	document.contentForm.linkFrom.value=val;
	document.contentForm.scheme.value=scheme;
	document.contentForm.cont_type.value=cont_type;
	document.contentForm.action="<%=path%>/servlet/adminEntry";
	document.contentForm.submit();
	return false;
}			
</script>
</html>