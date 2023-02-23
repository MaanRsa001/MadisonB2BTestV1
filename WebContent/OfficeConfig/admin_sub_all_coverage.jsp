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
	SubCoverageBean subCoverageBean = new SubCoverageBean();
%>

<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
	<base href="<%=basePath%>">
	<title>Sub Coverages Master</title>
	<%
		String productID = "30";
		
		String schemeID = request.getParameter("scheme");
		schemeID = schemeID == null ? "" : schemeID;
		
		String contentTypeID = request.getParameter("contentType");
		contentTypeID = contentTypeID == null ? "" : contentTypeID;
		
		String coverageID = request.getParameter("coverage");
		coverageID = coverageID == null ? "" : coverageID;
		System.out.println("Coverage in jsp: "+request.getParameter("coverage"));
		
		String schemeValue = coverageBean.getIDRelatedValue("SCHEME_NAME",
				"OFS_SCHEME_MASTER", "SCHEME_ID", schemeID);
		
		String contentTypeValue = coverageBean.getIDRelatedValue(
				"CONTENT_DESCRIPTION", "OFS_CONTENT_MASTER",
				"CONTENT_TYPE_ID", contentTypeID);
		
		String coverageValue = coverageBean.getIDRelatedValue(
				"COVERAGES_NAME", "OFS_MASTER", "COVERAGES_ID", coverageID);
		
		String list = "";
		list = coverageBean.getCoverageList(productID, contentTypeID, schemeID);
		
		String[][] coverageData = new String[0][0];
		coverageData = coverageBean.getAllCoverage();
		
		String subCoverageList = "";
		subCoverageList = subCoverageBean.getSubCoverageList(productID, contentTypeID, schemeID, coverageID);
	%>
	<script type="text/javascript" src="${pageContext.request.contextPath}/OfficeConfig/js/office.js"></script>
</head>
<body>
<div id="page" class="content">
	<div class="header">
		<%@ include file="../admin/header.jsp"%>
	</div>
	<div class="body">
		<div class="row">
			<div class="col-xs-12">
				<form name="mainForm" action="SubCoverageController"  method="post">
					<div class="panel panel-primary">
						<div class="panel-heading"> Sub Coverages Master </div>
						<div class="panel-body">
							<div class="row">
								<div class="col-xs-12 col-sm-12 col-md-3 col-lg-3">
									Product <b>&nbsp;:&nbsp;Office Shield</b>
								</div>
								<div class="col-xs-12 col-sm-12 col-md-3 col-lg-3">
									Scheme <b>&nbsp;:&nbsp;<%=schemeValue%></b>
								</div>
								<div class="col-xs-12 col-sm-12 col-md-3 col-lg-3">
									Content Type <b>&nbsp;:&nbsp;<%=contentTypeValue%></b>
								</div>
								<div class="col-xs-12 col-sm-12 col-md-3 col-lg-3">
									Coverage Name <b>&nbsp;:&nbsp;<%=coverageValue%></b>
								</div>
							</div>
							<br class="clear" />
							<div class="row">
								<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
									<div class="panel panel-primary">
										<div class="panel-heading"> Sub Coverages Included </div>
										<div class="panel-body">
											<div class="row">												
												<%	int index = 0, i = -1;
													for (; index < coverageData.length; ++index) {
														if ((subCoverageList.indexOf(","+coverageData[index][0] + ",")>=0) && (list.indexOf(","+coverageData[index][0] + ",")<0)) {
															++i; %>
															<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4" style="margin-bottom: 10px;">
																<i style="font-size: 18px; cursor: pointer;" class="fa fa-file-text" onclick="return popUpWindowSubCoverage('<%=path%>/OfficeConfig/admin_sub_coverage_insert.jsp', '<%=coverageID%>', '<%=contentTypeID%>', '<%=schemeID%>', '<%=coverageData[index][0]%>')"></i>&nbsp;<%=coverageData[index][1]%>
																&nbsp;
																<font color="red"> Edit &nbsp; <i style="font-size: 18px; cursor: pointer;" class="fa fa-file-text" onclick="return popUpWindowConfig('<%=path%>/OfficeConfig/admin_config_details.jsp', '<%=coverageID%>', '<%=coverageData[index][0]%>', '<%=contentTypeID%>' , '<%=schemeID%>', '<%=productID%>')"></i> </font>
															</div>
												<%		}
													} 	if(i==-1) { %>
														<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" align="center">
															<div class="label label-danger" align="center">No Sub Coverages Included</div>
														</div>
												<%		} %>
											</div>
										</div>
									</div>
									<div class="panel panel-primary">
										<div class="panel-heading"> Sub Coverages Excluded </div>
										<div class="panel-body">
											<div class="row">
												<%	index = 0; i = -1;
													for (; index < coverageData.length; ++index) {
														if (!(subCoverageList.indexOf(coverageData[index][0] + ",")>=0) && (list.indexOf(coverageData[index][0] + ",")<0)) {
															++i; %>
															<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4" style="margin-bottom: 10px;">
																<i style="font-size: 18px; cursor: pointer;" class="fa fa-file-text" onclick="return popUpWindowSubCoverage('<%=path%>/OfficeConfig/admin_sub_coverage_insert.jsp', '<%=coverageID%>', '<%=contentTypeID%>', '<%=schemeID%>', '<%=coverageData[index][0]%>')"></i>&nbsp;&nbsp;&nbsp;<%=coverageData[index][1]%>
															</div>
												<%		}
													} 	if(i==-1) { %>
														<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" align="center">
															<div class="label label-danger" align="center">No Sub Coverages Excluded</div>
														</div>
												<%		} %>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</form>
			</div>
		</div>
		<div class="row">
			<div class="col-xs-12" align="center">
				<a type="button" class="btn btn-sm btn-danger" href="<%=path%>/OfficeConfig/admin_sub_coverage.jsp" tabindex="16"> Back </a>
			</div>
		</div>
	</div>
	<br class="clear" />
	<div class="footer">
		<%@ include file="/templates/footer.jsp"%>
	</div>
</div>
</body>
</html>