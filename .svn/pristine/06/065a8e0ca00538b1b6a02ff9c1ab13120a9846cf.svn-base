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
	<title>Content Master</title>
	<%
		String mode=(String)request.getAttribute("mode")==null?"contentinsert":(String)request.getAttribute("mode");
		String scheme=(String)request.getParameter("scheme")==null?"":(String)request.getParameter("scheme");
		String cont_desc=(String)request.getParameter("cont_desc")==null?"":(String)request.getParameter("cont_desc");
		String cont_value=(String)request.getParameter("cont_value")==null?"":(String)request.getParameter("cont_value");
		String min_prem=(String)request.getParameter("min_prem")==null?"":(String)request.getParameter("min_prem");
		String remarks=(String)request.getParameter("remarks")==null?"":(String)request.getParameter("remarks");
		String rsacode=(String)request.getParameter("rsacode")==null?"":(String)request.getParameter("rsacode");
		String status=(String)request.getParameter("status")==null?"":(String)request.getParameter("status");
		String content_id="";
		if(mode.equalsIgnoreCase("updatecontent")) {
			String values[][] = (String[][]) request.getAttribute("values") ==null?new String[0][0] : (String[][]) request.getAttribute("values");
			if(values.length>0) {
				content_id=values[0][0]==null?"":values[0][0];
				scheme=values[0][2]==null?"":values[0][2];
				cont_desc=values[0][1]==null?"":values[0][1];
				remarks=values[0][3]==null?"":values[0][3];
				rsacode=values[0][4]==null?"":values[0][4];
				status=values[0][5]==null?"":values[0][5];
				cont_value=values[0][6]==null?"":values[0][6];
				min_prem=values[0][7]==null?"":values[0][7];
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
				<form name="contentForm"  method="post">
					<div class="panel panel-primary">
						<div class="panel-heading"> Content Master </div>
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
										<select name="scheme" class="inputBoxS">
											<option value="select"> Select Scheme </option>
											<%
												String[][] scheme1 = new String[0][0];
												String branch = (String) session.getAttribute("LoginBranchCode");
												scheme1 = coverageBean.getScheme("30",branch);
												for (int index = 0; index < scheme1.length; ++index) {
											%>
												<option value="<%=scheme1[index][0]%>"<%=scheme.equalsIgnoreCase(scheme1[index][0]) ? "selected": ""%>> <%=scheme1[index][1]%> </option>
											<% } %>
										</select>   
									</div>
								</div>
								<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
									<div class="text">Content description</div>
									<div class="tbox">
										<div class="inputAppend">
											<input name="cont_desc" type="text" size="25" maxlength="50" value="<%=cont_desc%>" class="inputBox1" style="border: none;background: transparent;">
											<a type="button" href="<%=path%>/OfficeConfig/admin_content_edit.jsp" class="btn btn-sm btn-warning inputButton" style="height: 28px;">Edit</a>
			                           </div>   
									</div>
								</div>
								<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
									<div class="text"> Content Value</div>
									<div class="tbox">
										<input name="cont_value" type="text" size="10" maxlength="10" value="<%=cont_value%>" class="inputBox">
									</div>
								</div>
								<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
									<div class="text"> Minimum Premium</div>
									<div class="tbox">
										<input name="min_prem" type="text" size="10" maxlength="10" value="<%=min_prem%>" class="inputBox">
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
					<input type="hidden" name="linkFrom" value="">
					<% if(mode.equalsIgnoreCase("updatecontent")){%>
						<input type="hidden" name="content_id" value="<%=content_id%>" >
		            <%}%>
				</form>
			</div>
		</div>
	</div>
</div>
</body>
<script type="text/javascript">
function SubmitSaveAndContinue(val) {
	document.contentForm.linkFrom.value=val;
	document.contentForm.action="<%=path%>/servlet/adminEntry";
	document.contentForm.submit();
	return false;
}			
</script>
</html>