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
	officeadminBean officeadminBean = new officeadminBean();
%>

<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
	<base href="<%=basePath%>">
	<title>Scheme Master</title>
	<%
		String scheme=(String)request.getParameter("scheme")==null?"":(String)request.getParameter("scheme");
		String cont_desc=(String)request.getParameter("cont_desc")==null?"":(String)request.getParameter("cont_desc");
		String cont_value=(String)request.getParameter("cont_value")==null?"":(String)request.getParameter("cont_value");
		String min_prem=(String)request.getParameter("min_prem")==null?"":(String)request.getParameter("min_prem");
		String remarks=(String)request.getParameter("remarks")==null?"":(String)request.getParameter("remarks");
		String rsacode=(String)request.getParameter("rsacode")==null?"":(String)request.getParameter("rsacode");
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
				<form name="contentForm"  method="post">
					<div class="panel panel-primary">
						<div class="panel-heading"> Scheme Master [Edit] </div>
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
								<div class="col-xs-12 col-sm-12 col-md-12 col-xs-12">
									<table class="footable tableWidth">
										<tbody>
										<tr>
											<td width="40%">Product</td>
											<td width="60%"> <b>Office Shield</b> </td>
										</tr>
										<tr>
											<td>Select the Scheme</td>
											<td>
												<select name="scheme" onchange="fnGetContentType(this.value)" class="inputBoxS">
													<option value="select">
														Select Scheme
													</option>
													<%
														String[][] scheme1 = new String[0][0];
														scheme1 = officeadminBean.getScheme("30");
														for (int index = 0; index < scheme1.length; ++index) {
													%>
														<option value="<%=scheme1[index][0]%>"<%=scheme.equalsIgnoreCase(scheme1[index][0]) ? "selected": ""%>> <%=scheme1[index][1]%> </option>
													<%	} %>
												</select>
											</td>
										</tr>
										</tbody>
									</table>									
								</div>
							</div>
							<br/>
							<div class="row">					
								<div class="col-xs-12 col-sm-12 col-md-12 col-xs-12" align="center">
									<input type="button" class="btn btn-sm btn-success" value="Proceed" onClick="return SubmitSaveAndContinue()"  tabindex="31">
								</div>
							</div>
						</div>
					</div>
					<input type="hidden" name="linkFrom" value="">
				</form>
			</div>
		</div>
	</div>
</div>
</body>
<script type="text/javascript">
function SubmitSaveAndContinue() {
	document.contentForm.linkFrom.value="schemeedit";
	document.contentForm.action="<%=path%>/servlet/adminEntry";
	document.contentForm.submit();
	return false;
}			
</script>
</html>