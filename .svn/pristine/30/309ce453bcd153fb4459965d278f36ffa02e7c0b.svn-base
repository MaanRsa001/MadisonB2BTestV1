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

<!DOCTYPE html>
<html>
<head>
	<base href="<%=basePath%>">
	<title>Coverages Information</title>
</head>
<%
	String mode=(String)request.getAttribute("mode")==null?"coverageinsert":(String)request.getAttribute("mode");
	String Cname=(String)request.getParameter("Cname")==null?"":(String)request.getParameter("Cname");
	String Cdisname=(String)request.getParameter("Cdisname")==null?"":(String)request.getParameter("Cdisname");
	String sce_details=(String)request.getParameter("sce_details")==null?"":(String)request.getParameter("sce_details");
	String remarks=(String)request.getParameter("remarks")==null?"":(String)request.getParameter("remarks");
	String rsacode=(String)request.getParameter("rsacode")==null?"":(String)request.getParameter("rsacode");
	String status=(String)request.getParameter("status")==null?"":(String)request.getParameter("status");
	String coverage_id="";
	if(mode.equalsIgnoreCase("updatecoverage")) {
		String values[][] = (String[][]) request.getAttribute("values") ==null?new String[0][0] : (String[][]) request.getAttribute("values");
		if(values.length>0) {  
			coverage_id=values[0][0]==null?"":values[0][0];
			Cname=values[0][1]==null?"":values[0][1];
			Cdisname=values[0][2]==null?"":values[0][2];
			remarks=values[0][3]==null?"":values[0][3];
			rsacode=values[0][4]==null?"":values[0][4];
			status=values[0][5]==null?"":values[0][5];
			sce_details=values[0][6]==null?"":values[0][6];

		}
	}
%>
<body>
<div id="page" class="content">
	<div class="header">
		<%@ include file="../admin/header.jsp"%>
	</div>
	<div class="body">
		<div class="row">
			<div class="col-xs-12">
				<form name="coverForm"  method="post">
					<div class="panel panel-primary">
						<div class="panel-heading"> Coverages Information </div>
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
									<div class="text">Coverage Name</div>
									<div class="tbox">
										<div class="inputAppend">
											<input name="Cname" type="text" size="25" maxlength="200" class="inputBox1" style="border: none;background: transparent;" value="<%=Cname%>">
											<a type="button" href="<%=path%>/OfficeConfig/admin_ofsmaster_edit.jsp" class="btn btn-sm btn-warning inputButton" style="height: 28px;">Edit</a>
			                           </div>
									</div>
								</div>
								<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
									<div class="text">Coverage Display Name</div>
									<div class="tbox">
										<input name="Cdisname" type="text" size="25" maxlength="200" value="<%=Cdisname%>" class="inputBox">
									</div>
								</div>
								<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
									<div class="text">Section Details</div>
									<div class="tbox">
										<input name="sce_details" type="text" size="25" maxlength="50"value="<%=sce_details%>" class="inputBox">
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
									<input type="button" value="Proceed" class="btn btn-sm btn-success"  onClick="return SubmitSaveAndContinue('<%=mode%>')"  tabindex="31">
								</div>
							</div>
						</div>
					</div>
					<input type="hidden" name="linkFrom" >
					<% if(mode.equalsIgnoreCase("updatecoverage")){%>
						<input type="hidden" name="coverage_id" value="<%=coverage_id%>" >
					<%}%>
				</form>
			</div>
		</div>
	</div>
	<br/>
	<div class="footer">
		<%@include file="/templates/footer.jsp"%>
	</div>
</div>
</body>
<script type="text/javascript">
function SubmitSaveAndContinue(val) {
	document.coverForm.linkFrom.value=val;
	document.coverForm.action="<%=path%>/servlet/adminEntry";
	document.coverForm.submit();
	return false;
}
</script>
</html>