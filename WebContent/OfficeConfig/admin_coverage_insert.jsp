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
	<title>Edit Coverage Details</title>
	<%
		String productID = "30";

		GregorianCalendar gc = new GregorianCalendar();
		String effectiveDate = gc.get(Calendar.DATE) + "-"
				+ (gc.get(Calendar.MONTH) + 1) + "-"
				+ gc.get(Calendar.YEAR);

				String error = (String) request.getAttribute("error");
				error = error == null ? "" : error;

		String schemeID = request.getParameter("scheme");
		schemeID = schemeID == null ? "" : schemeID;

		String contentTypeID = request.getParameter("contentType");
		contentTypeID = contentTypeID == null ? "" : contentTypeID;

		String coverageID = request.getParameter("coverage");
		coverageID = coverageID == null ? "" : coverageID;

		String coverageType = request.getParameter("coverageType");
		coverageType = coverageType == null ? "" : coverageType;

		String uploadOption = request.getParameter("uploadOption");
		uploadOption = uploadOption == null ? "" : uploadOption;

		String displayOrder = request.getParameter("displayOrder");
		displayOrder = displayOrder == null ? "0" : displayOrder;

		String controlType = request.getParameter("controlType");
		controlType = controlType == null ? "" : controlType;

		String coverageLimit = request.getParameter("coverageLimit");
		coverageLimit = coverageLimit == null ? "" : coverageLimit;

		String sumControlType = request.getParameter("sumControlType");
		sumControlType = sumControlType == null ? "" : sumControlType;

		String sumCoverageLimit = request.getParameter("sumCoverageLimit");
		sumCoverageLimit = sumCoverageLimit == null ? "" : sumCoverageLimit;
		
		String minSumLimit = request.getParameter("minSumLimit");
		minSumLimit = minSumLimit == null ? "" : minSumLimit;
		
		String excess = request.getParameter("excess");
		excess = excess == null ? "" : excess;

		String baseRate = request.getParameter("baseRate");
		baseRate = baseRate == null ? "" : baseRate;

		String calculationStatus = request
				.getParameter("calculationStatus");
		calculationStatus = calculationStatus == null ? ""
				: calculationStatus;

		String calculationType = request.getParameter("calculationType");
		calculationType = calculationType == null ? "" : calculationType;

		String status = request.getParameter("status");
		status = status == null ? "" : status;

		String helpContents = request.getParameter("helpContent");
		helpContents = helpContents == null ? "" : helpContents;
		
		String rsaCode = request.getParameter("rsaCode");
		rsaCode = rsaCode == null ? "" : rsaCode;

		String schemeValue = coverageBean.getIDRelatedValue("SCHEME_NAME",
				"OFS_SCHEME_MASTER", "SCHEME_ID", schemeID);

		String contentTypeValue = coverageBean.getIDRelatedValue(
				"CONTENT_DESCRIPTION", "OFS_CONTENT_MASTER",
				"CONTENT_TYPE_ID", contentTypeID);

		String coverageValue = coverageBean.getIDRelatedValue(
				"COVERAGES_NAME", "OFS_MASTER", "COVERAGES_ID", coverageID);
		String[][] values = new String[0][0];
		values = coverageBean.selectValue(productID, coverageID, schemeID,
				contentTypeID);

		String displayOrderList = "";
		displayOrderList = coverageBean.getCoverageDisplayOrderList(
				productID, contentTypeID, schemeID);

	if (values.length > 0 && error.length()<=0) {
			coverageType = values[0][0] == null ? "" : values[0][0];
			uploadOption = values[0][1] == null ? "" : values[0][1];
			displayOrder = values[0][2] == null ? "0" : values[0][2];
			controlType = values[0][3] == null ? "" : values[0][3];
			coverageLimit = values[0][4] == null ? "" : values[0][4];
			excess = values[0][8] == null ? "" : values[0][8];
			baseRate = values[0][5] == null ? "" : values[0][5];
			calculationStatus = values[0][9] == null ? "" : values[0][9];
			calculationType = values[0][10] == null ? "" : values[0][10];
			status = values[0][7] == null ? "" : values[0][7];
			effectiveDate = values[0][11] == null ? "" : values[0][11];
			rsaCode = values[0][12] == null ? "" : values[0][12];
			sumCoverageLimit = values[0][13] == null ? "" : values[0][13];
			minSumLimit = values[0][15] == null ? "" : values[0][15];
			sumControlType = values[0][14] == null ? "" : values[0][14];
			helpContents = values[0][6] == null ? "" : values[0][6];
			helpContents = coverageBean.getIDRelatedValue(
			"HELP_DESCRIPTION", "OFS_HELP_MASTER",
			"HELP_CONTENTS_ID", helpContents);
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
								<div class="panel-body">
									<%	if (error.length() > 0) {%>
									<div class="row">
										<div class="col-xs-12">
											<font color="red"><%=error%> </font>
										</div>
									</div>
									<br/>
									<%	} %>
									<div class="row">
										<table class="footable" width="100%">
											<tbody>
											<tr>
												<td width="40%">Coverage Type</td>
												<td width="60%">
													<input type="radio" class="textbox" name="coverageType" value="B" <%=coverageType.equals("B") ? "checked" : ""%>> &nbsp;Base <br/>
													<input type="radio" class="textbox" name="coverageType" value="O" <%=coverageType.equals("O") ? "checked" : ""%>> &nbsp;Optional
												</td>
											</tr>
											<tr>
												<td>Upload Option</td>
												<td>
													<input type="radio" class="textbox" name="uploadOption" value="Y" <%=uploadOption.equals("Y") ? "checked" : ""%>> &nbsp;Yes <br/>
													<input type="radio" class="textbox" name="uploadOption" value="N" <%=uploadOption.equals("N") ? "checked" : ""%>> &nbsp;No
												</td>
											</tr>
											<tr>
												<td>Display Order</td>
												<td>
													<!--  Newly Added For Default 1 -->
													<%! String dispOrder[]=new 	String[0];
														String dispFlag="No"; %>
													<select name="displayOrder" class="inputBoxS">
														<option value=""> Select </option>
														<%	int plusone=0;
															for (int i = 1; i < 100; ++i) {
																if ((displayOrderList.indexOf(i+",") != -1)|| displayOrder.equalsIgnoreCase(i + "") || i<Integer.parseInt(displayOrder)) {
														%>
														<option value="<%=i%>"
															<%=displayOrder.equalsIgnoreCase(i + "") ? "selected" : ""%>> <%=i%>
														</option>
														<%			plusone = i;
																}
															}
														%>
														<option value="<%=plusone+1%>">
															<%=plusone+1%>
														</option>							
													</select>
												</td>
											</tr>
											<tr>
												<td>Coverage Display Type</td>
												<td>
													<%	String[] CONTROL_TYPE = { "Display", "textbox", "Radio", "dropdown" }; %>
													<select name="sumControlType" class="inputBoxS">
														<option value="">None</option>
														<% 	for (int i = 0; i < CONTROL_TYPE.length; ++i) { %>
																<option value="<%=CONTROL_TYPE[i]%>" <%=CONTROL_TYPE[i].equalsIgnoreCase(sumControlType) ? "selected": ""%>>
																	<%=CONTROL_TYPE[i]%>
																</option>
														<%	} %>
													</select>
												</td>
											</tr>
											<tr>
												<td>Sum Insured&nbsp; Coverage Limit(Calculation)</td>
												<td>
													<input type="text" name="sumCoverageLimit" class="inputBox" value="<%=sumCoverageLimit%>" maxlength="10">
												</td>
											</tr>
											<tr>
												<td>Minimum Sum Insured(Calculation)</td>
												<td>
													<input type="text" name="minSumLimit" class="inputBox" value="<%=minSumLimit%>" maxlength="10">
												</td>
											</tr>
											<tr>
												<td>Option Display Type</td>
												<td>
													<select name="controlType" class="inputBoxS">
													<%	for (int i = 0; i < CONTROL_TYPE.length; ++i) {	%>
															<option value="<%=CONTROL_TYPE[i]%>"<%=CONTROL_TYPE[i].equalsIgnoreCase(controlType) ? "selected" : ""%>>
																<%=CONTROL_TYPE[i]%>
															</option>
													<%	} %>
													</select>
												</td>
											</tr>
											<tr>
												<td>Coverage Limit(Display)</td>
												<td>
													<input type="text" name="coverageLimit" class="inputBox" value="<%=coverageLimit%>" maxlength="200">
												</td>
											</tr>
											<tr>
												<td>Excess</td>
												<td><input class="inputBox" type="text" name="excess" value="<%=excess%>" maxlength="100"></td>
											</tr>
											<tr>
												<td>Base Rate</td>
												<td><input class="inputBox" type="text" name="baseRate" value="<%=baseRate%>"  maxlength="10"></td>
											</tr>
											<tr>
												<td>Calculation Status</td>
												<td>
													<input type="radio" class="textbox" name="calculationStatus" value="Y" <%=calculationStatus.equals("Y") ? "checked" : ""%>> &nbsp;Yes <br/>
													<input type="radio" class="textbox" name="calculationStatus" value="N" <%=calculationStatus.equals("N") ? "checked" : ""%>> &nbsp;No
												</td>
											</tr>
											<tr>
												<td>Calculation Type</td>
												<td>
													<input type="radio" class="textbox" name="calculationType" value="A" <%=calculationType.equals("A") ? "checked" : ""%>> &nbsp;Amount <br/>
													<input type="radio" class="textbox" name="calculationType" value="M" <%=calculationType.equals("M") ? "checked" : ""%>> &nbsp;Mile <br/>
													<input type="radio" class="textbox" name="calculationType" value="P" <%=calculationType.equals("P") ? "checked" : ""%>> &nbsp;Percentage <br/>
													<input type="radio" class="textbox" name="calculationType" value="G" <%=calculationType.equals("G") ? "checked" : ""%>> &nbsp;Grid
												</td>
											</tr>
											<tr>
												<td>Status</td>
												<td>
													<input type="radio" class="textbox" name="status" value="Y" <%=status.equals("Y") ? "checked" : ""%>> &nbsp;Active <br/>
													<input type="radio" class="textbox" name="status" value="N" <%=status.equals("N") ? "checked" : ""%>> &nbsp;Deactive
												</td>
											</tr>
											<tr>
												<td>
													<%
															try {
																String str = effectiveDate.substring(6, effectiveDate.length());
																Integer.parseInt(str);
															} catch (Exception e) {
																if (effectiveDate.length() > 0) {
																	StringTokenizer token = new StringTokenizer(effectiveDate,
																	" ");
																	effectiveDate = token.nextToken();
																	token = new StringTokenizer(effectiveDate, "-");
																	effectiveDate = token.nextToken();
																	effectiveDate = token.nextToken() + "-" + effectiveDate;
																	effectiveDate = token.nextToken() + "-" + effectiveDate;
																}
															}
													%>Effective Date
												</td>
												<td><input type="text" name="effectiveDate" value="<%=effectiveDate%>" size="30" maxlength="20" class="inputBox datepicker" size="10" id="effectiveDate" /></td>
											</tr>
											<tr>
												<td>Core Code</td>
												<td><input class="inputBox" maxlength="10" type="text" name="rsaCode" value="<%=rsaCode%>"></td>
											</tr>
											<tr>
												<td>Help Content</td>
												<td>
													<textarea rows="8" class="inputBoxA" style="width: 100%;" name="helpContent" maxlength="1000" onkeyup="return ismaxlength(this)"><%=helpContents%></textarea>
												</td>
											</tr>
											</tbody>
										</table>										
									</div>
									<br class="clear" />
								</div>
								<br class="clear" />
							</div>
							<br class="clear" />
						</div>
					</div>
					<br class="clear" />
					<div class="row">
						<div class="col-xs-12" align="center">
							<input type="button"  onclick="javascript:window.close()" class="btn btn-sm btn-danger" value="Back">
							&nbsp;&nbsp;&nbsp;
							<input type="submit" class="btn btn-sm btn-success" value="Submit">
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<input type="hidden" name="requestForm" value="coverageInsert">
	<input type="hidden" name="scheme" value="<%=schemeID%>">
	<input type="hidden" name="contentType" value="<%=contentTypeID%>">
	<input type="hidden" name="coverage" value="<%=coverageID%>">
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
var cal1 = new calendar1(document.forms["form1"].elements["effectiveDate"]);
	cal1.year_scroll = true;
	cal1.time_comp = false;

function ismaxlength(obj){	 
	var mlength=obj.getAttribute? parseInt(obj.getAttribute("maxlength")) : ""
	//alert(mlength);
	if (obj.getAttribute && obj.value.length>mlength)
	obj.value=obj.value.substring(0,mlength)
}
</script>
</html>