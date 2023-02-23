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
	<%
		String schemeID = request.getParameter("scheme");
		schemeID = schemeID == null ? "" : schemeID;

		String productID = "30";

		String contentTypeID = request.getParameter("contentType");
		contentTypeID = contentTypeID == null ? "" : contentTypeID;

		String schemeValue = coverageBean.getIDRelatedValue("SCHEME_NAME",
				"OFS_SCHEME_MASTER", "SCHEME_ID", schemeID);
		String contentTypeValue = coverageBean.getIDRelatedValue(
				"CONTENT_DESCRIPTION", "OFS_CONTENT_MASTER",
				"CONTENT_TYPE_ID", contentTypeID);
		String[][] coverageData = new String[0][0];
		coverageData = coverageBean.getAllCoverage();
		String list = "";
		list = coverageBean.getCoverageList(productID, contentTypeID,
				schemeID);
	%>
	<script type="text/javascript">
		function fnSubmit(value){
			document.getElementById("coverage").value = value;
			document.mainForm.submit();
		}
	</script>
</head>
<body>
<div id="page" class="content">
	<div class="header">
		<%@ include file="../admin/header.jsp"%>
	</div>
	<div class="body">
		<form action="SubCoverageController" method="get" name="mainForm">				
		<div class="row">
			<div class="col-xs-12">
				<div class="panel panel-primary">
					<div class="panel-heading">
						Sub Coverages Master
					</div>
					<div class="panel-body">
						<div class="row">
							<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4" align="center">
								Product <b>&nbsp;:&nbsp;Office Shield</b>
							</div>
							<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4" align="center">
								Scheme <b>&nbsp;:&nbsp;<%=schemeValue%></b>
							</div>
							<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4" align="center">
								Content Type <<b>&nbsp;:&nbsp;<%=contentTypeValue%></b>
							</div>
						</div>
						<br class="clear" />
						<div class="row">
							<%	int index = 0;
								for (; index < coverageData.length; ++index) {
							%>
							<%		if (list.indexOf(coverageData[index][0] + ",")>=0) { %>
										<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4" style="margin-bottom: 10px;">
											<i style="font-size: 18px; cursor: pointer;" class="fa fa-file-text" onclick="fnSubmit(<%=coverageData[index][0]%>)"></i>&nbsp;<%=coverageData[index][1]%>
										</div>
							<%		}
								} %>
						</div>
					</div>
				</div>
			</div>
		</div>
		<br class="clear" />
		<div class="row">
			<div class="col-xs-12" align="center">
				<a type="button" class="btn btn-sm btn-danger" href="<%=path%>/OfficeConfig/admin_coverage.jsp" tabindex="16"> Back </a>
			</div> 
		</div>
		<input type="hidden" name="scheme" value="<%=schemeID%>">
		<input type="hidden" name="contentType" value="<%=contentTypeID%>">
		<input type="hidden" name="product" value="<%=productID%>">
		<input type="hidden" name="coverage" id="coverage">
		<input type="hidden" name="requestForm" value="subCoverageMain">
		</form>
	</div>
	<div class="footer">
		<%@ include file="/templates/footer.jsp"%>
	</div>
</div>
</body>
</html>