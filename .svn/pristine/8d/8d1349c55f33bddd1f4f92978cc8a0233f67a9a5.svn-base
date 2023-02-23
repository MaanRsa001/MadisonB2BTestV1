<%@ page language="java"
	pageEncoding="ISO-8859-1"
	isELIgnored="false"%>
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

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title><%@ include file="include/title.jsp"%>
		</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<%@ include file="include/includeFile.jsp"%>
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
	                        /* if(res){
	                               document.getElementById("contentTypeShow").innerHTML = res;
	                         }*/
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
		<%@ include file="../admin/header.jsp"%>
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<%--@ include file="../admin/left.jsp"--%></tr></table>
							</td>
								<td width="75%" valign="top" valign="top">
								<form name="mainForm" action="CoverageController" method="post">
									<table width="100%" border="0" cellpadding="0" cellspacing="0">
										<tr>
											<td align="center">
												<%@ include file="include/top_menu.jsp"%>
											</td>
										</tr>
									</table>
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td>
						<input type="hidden" name="requestForm" value="coverageMain">
						<%@ include file="/templates/footer.jsp"%>
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>
