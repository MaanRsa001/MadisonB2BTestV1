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
	<title>Sub Coverages Master</title>
	<script type="text/javascript">
		function fnGetContentType(scheme){
             var http;  
             var urlvalue = '';                  
             var browser = navigator.appName;                	
             if (window.XMLHttpRequest){        				
          	   http = new XMLHttpRequest();
			  }
			  else{
				 if (window.ActiveXObject){
          			http = new ActiveXObject("Microsoft.XMLHTTP");
          		  }
			  } 
			  urlvalue = "<%=path%>/OfficeConfig/AjaxContentType.jsp?schemeID="+scheme;
		      http.open('Post',urlvalue ,true);
		      http.onreadystatechange = function(){	
                     if(http.readyState == 4 && http.status == 200) {                    
 	                 	 var res = http.responseText;
                         if(res){
                               document.getElementById("contentTypeShow").innerHTML = res;
                         }
                     }
			  }
              http.send(null);  
    	}
	</script>
	<%
		String schemeID = request.getParameter("scheme");
		schemeID = schemeID == null ? "" : schemeID;

		String contentTypeID = request.getParameter("contentType");
		contentTypeID = contentTypeID == null ? "" : contentTypeID;
		String load = "";
		if (!schemeID.equalsIgnoreCase("select")) {
			load = "onload = \"fnGetContentType('" + schemeID + "')\"";
		}
	%>
</head>
<body <%=load%>>
<div id="page" class="content">
	<div class="header">
		<%@ include file="../admin/header.jsp"%>
	</div>
	<div class="body">
		<div class="row">
			<div class="col-xs-12">
			<form name="mainForm" action="SubCoverageController" method="post">
				<div class="panel panel-primary">
					<div class="panel-heading"> Sub Coverages Master </div>
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
										<td>Scheme</td>
										<td>
											<select name="scheme" onchange="fnGetContentType(this.value)" class="inputBox">
												<option value="select"> Select Scheme </option>
												<%
													String[][] scheme = new String[0][0];
													String branch = (String) session.getAttribute("LoginBranchCode");
													scheme = coverageBean.getScheme("30",branch);
													for (int index = 0; index < scheme.length; ++index) {
												%>
													<option value="<%=scheme[index][0]%>"<%=schemeID.equalsIgnoreCase(scheme[index][0]) ? "selected": ""%>>
														<%=scheme[index][1]%>
													</option>
												<%	} %>
											</select>
										</td>
									</tr>
									<tr id="contentTypeShow"></tr>
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
				<br/>
				<div class="row">					
					<div class="col-xs-12 col-sm-12 col-md-12 col-xs-12" align="center">
						<input type="submit" class="btn btn-sm btn-success" value="Process">
					</div>
				</div>
				<input type="hidden" name="requestForm" value="subCoverage">	
			</form>
			</div>
		</div>
	</div>
	<div class="footer">
		<%@ include file="/templates/footer.jsp"%>
	</div>
</div>
</body>
</html>