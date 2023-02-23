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
%>
<!DOCTYPE html>
<html>
<head>
	<base href="<%=basePath%>">
	<title>Scheme Master</title>
	<%
		String mode=(String)request.getAttribute("mode")==null?"schemeinsert":(String)request.getAttribute("mode");
		String scheme=(String)request.getParameter("scheme")==null?"":(String)request.getParameter("scheme");
		String remarks=(String)request.getParameter("remarks")==null?"":(String)request.getParameter("remarks");
		String rsacode=(String)request.getParameter("rsacode")==null?"":(String)request.getParameter("rsacode");
		String status=(String)request.getParameter("status")==null?"":(String)request.getParameter("status");
		String scheme_id="";

		if(mode.equalsIgnoreCase("updatescheme")) {
 			String values[][] = (String[][]) request.getAttribute("values") ==null?new String[0][0] : (String[][]) request.getAttribute("values");
 			if(values.length>0) {
				scheme_id=values[0][0]==null?"":values[0][0];
				scheme=values[0][1]==null?"":values[0][1];
				remarks=values[0][2]==null?"":values[0][2];
				rsacode=values[0][3]==null?"":values[0][3];
				status=values[0][4]==null?"":values[0][4];
 			}
 		}
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
				<form name="schemeForm"  method="post">
					<div class="panel panel-primary">
						<div class="panel-heading"> Scheme Master </div>
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
									<div class="text">Scheme</div>
									<div class="tbox">
										<div class="inputAppend">
											<input name="scheme" type="text" size="25" maxlength="50" value="<%=scheme%>" class="inputBox1" style="border: none;background: transparent;">
											<a type="button" href="<%=path%>/OfficeConfig/admin_scheme_edit.jsp" class="btn btn-sm btn-warning inputButton" style="height: 28px;">Edit</a>
			                           </div>   
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
									<input type="button" class="btn btn-sm btn-success" value="Proceed" onClick="return SubmitSaveAndContinue('<%=mode%>')"  tabindex="31">
								</div>
							</div>
						</div>
					</div>
					<input type="hidden" name="linkFrom" >
					<% if(mode.equalsIgnoreCase("updatescheme")){%>
						<input type="hidden" name="scheme_id" value="<%=scheme_id%>" >
		            <%}%>
				</form>
			</div>
		</div>
	</div>
</div>
</body>
<script type="text/javascript">
function SubmitSaveAndContinue(val) {
	document.schemeForm.linkFrom.value=val;
	document.schemeForm.action="<%=path%>/servlet/adminEntry";
	document.schemeForm.submit();
	return false;
}			
</script>
</html>