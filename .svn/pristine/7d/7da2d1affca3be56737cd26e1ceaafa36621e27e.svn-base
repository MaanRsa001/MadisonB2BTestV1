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
//	try{
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
	<style type="text/css">
	.panel-heading {
		padding: 5px;
	}
	.header {
		text-align: left;
	}
	.tableColumnWidth {
		min-width: 150px;
		max-width: 150px;
		width: 150px; 
	}
	</style>
	<title>Edit Configuration Details</title>
	<%
		GregorianCalendar gc = new GregorianCalendar();
		String effectiveDate = gc.get(Calendar.DATE) + "-"
				+ (gc.get(Calendar.MONTH) + 1) + "-"
				+ gc.get(Calendar.YEAR);

				String error = (String) request.getAttribute("error");
				error = error == null ? "" : error;

		String proId = (String) request.getParameter("product_id");
		proId = proId == null ? "0" : proId;
		
		String schemeId = (String) request.getParameter("scheme");
		schemeId = schemeId == null ? "0" : schemeId;

		String contentTypeID = request.getParameter("contentType");
		contentTypeID = contentTypeID == null ? "0" : contentTypeID;

		String coverid = request.getParameter("coverage") == null ? "": request.getParameter("coverage");
		String subcoverid = request.getParameter("subcoverage") == null ? "0": request.getParameter("subcoverage");
		String requestfrom = request.getParameter("requestfrom") == null ? "": request.getParameter("requestfrom");
		String schemeValue = coverageBean.getIDRelatedValue("SCHEME_NAME","OFS_SCHEME_MASTER", "SCHEME_ID", schemeId);
		String contentTypeValue = coverageBean.getIDRelatedValue("CONTENT_DESCRIPTION", "OFS_CONTENT_MASTER","CONTENT_TYPE_ID", contentTypeID);
		contentTypeValue=contentTypeValue==null?"None":"None";
		String coverageValue = coverageBean.getIDRelatedValue("COVERAGES_NAME", "OFS_MASTER", "COVERAGES_ID", coverid);
		String add = request.getParameter("addMore") == null ? "3": request.getParameter("addMore");
		int addMore=Integer.parseInt(add);
		
		String[][] configDetails=request.getAttribute("configDetails")==null?new String[0][0]:(String[][])request.getAttribute("configDetails");
		if(configDetails.length<=0)
		{
			configDetails=coverageBean.getConfigDetails(proId, schemeId, contentTypeID, coverid, subcoverid);
		}
		int arrayLength = 0;
		if(configDetails != null && configDetails.length>0)
		{
			addMore=(configDetails.length>addMore)?configDetails.length:addMore;
			arrayLength = configDetails.length;
		}
		String displayOrderList = "0";
		String[][] columnNames= coverageBean.getColumnNames("OFS_TRANSACTION_DETAILS");
		String[][] TableNames=coverageBean.getTableNames();
		String[][] dropdownColumnNames=request.getAttribute("dropdownColumnNames")==null?new String[0][0]:(String[][])request.getAttribute("dropdownColumnNames");
		String row = request.getParameter("row") == null ? "": request.getParameter("row");
		String flag = request.getParameter("flag") == null ? "true": request.getParameter("flag");
		System.out.println("row: "+row);
		System.out.println("flag===>: "+flag);
	%>
</head>
<body onload="return Call()">
<div id="page" class="content">	
	<div class="body">
	<form action="CoverageController" method="post" name="form1">
	<div class="row">
		<div class="col-xs-12">
			<div class="panel panel-primary">
				<div class="panel-heading"> Edit Configuration Details </div>
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
								<div class="panel-body" style="overflow-x: scroll; overflow-y: none;">
									<%	if (error.length() > 0) {%>
									<div class="row">
										<div class="col-xs-12">
											<font color="red"><%=error%> </font>
										</div>
									</div>
									<br/>
									<%	} %>
									<div class="row">
										<div class="col-xs-12" >
											<table class="footable">
												<thead>
												<tr>
													<th class="tableColumnWidth">Display Name</th>
													<th class="tableColumnWidth">Display Type</th>
													<th class="tableColumnWidth"> Display Order</th>
													<th class="tableColumnWidth"> Dropdown Table</th>
											   <%-- <th class="tableColumnWidth"> Dropdown Key</th>
													<th class="tableColumnWidth"> Dropdown Value</th> --%>
													<th class="tableColumnWidth"> Validation Type</th>
													<th class="tableColumnWidth"> Destination Table</th>
													<th class="tableColumnWidth"> Destination Column</th>
													<th class="tableColumnWidth"> Mandatory</th>
													<th class="tableColumnWidth"> Total SumInsured</th>
													<th class="tableColumnWidth"> Status</th>
													
												</tr>
												</thead>
												<tbody>
												<%for(int k=0; k<addMore; k++){   %>
												<tr>
													<td>
														<input class="inputBox" type="text" name="displayName<%=k%>" value="<%=(arrayLength>k && configDetails[k][0]!=null)?configDetails[k][0]:"" %>"  maxlength="100" size="8">
													</td>
													<td>
														<%	String[] CONTROL_TYPE = { "Display", "textbox", "Radio", "dropdown" }; %>
														<select name="controlType<%=k%>" id="controlType<%=k%>" class="inputBoxS">
															<option value="">None</option>
															<% for (int i = 0; i < CONTROL_TYPE.length; ++i) { %>
																<option value="<%=CONTROL_TYPE[i]%>" <%=CONTROL_TYPE[i].equalsIgnoreCase((arrayLength>k && configDetails[k][1]!=null)?configDetails[k][1]:"") ? "selected": ""%>>
																	<%=CONTROL_TYPE[i]%>
																</option>
															<% } %>
														</select>
													</td>
													<td>
														<%!String dispOrder[]=new 	String[0]; String dispFlag="No"; %>
														<select name="displayOrder<%=k%>" class="inputBoxS">
															<option value=""> Select </option>
								
															
															<%	int plusone=0;
																for (int i = 1; i < addMore; i++) {
																	//if ((displayOrderList.indexOf(i+",") != -1)|| configDetails[k][2].equalsIgnoreCase(i + "") || i<Integer.parseInt(configDetails[k][2])) {
															%>
																	<option value="<%=i%>" <%=((arrayLength>k && configDetails[k][2]!=null)?configDetails[k][2]:"").equalsIgnoreCase(i + "") ? "selected":""%>> <%=i%></option>
															<%		plusone = i;
																//}
																}
															%>
															<option value="<%=plusone+1%>" <%=((arrayLength>k && configDetails[k][2]!=null)?configDetails[k][2]:"").equalsIgnoreCase(plusone+1 + "") ? "selected":""%>>
																<%=plusone+1%>
															</option>							
														</select>
													</td>
													<td>
														<input class="inputBox" type="text" name="dropdownTable<%=k%>" id="dropdownTable<%=k%>" value='<%=(arrayLength>k && configDetails[k][5]!=null)?configDetails[k][5]:""%>' maxlength="20" size="10">
													</td>
											   <%-- <td>
														<select name="dropdownKey<%=k%>" class="inputBoxS">
															<option value="">None</option>
															<% 	dropdownColumnNames=request.getAttribute("dropdownTable"+k)==null?new String[0][0]:(String[][])request.getAttribute("dropdownTable"+k);
																if( dropdownColumnNames !=null && dropdownColumnNames.length>0 ){
																	for (int i = 0; i < dropdownColumnNames.length; ++i) {
															%>
																		<option value="<%=dropdownColumnNames[i][0]%>"
																			<%=dropdownColumnNames[i][0].equalsIgnoreCase((arrayLength>k && configDetails[k][3]!=null)?configDetails[k][3]:"") ? "selected": ""%>>
																			<%=dropdownColumnNames[i][0]%>
																		</option>
															<%		}
																}
															%>
														</select>
													</td>
													<td>
														<select name="dropdownValue<%=k%>" class="inputBoxS">
															<option value="">None</option>
															<%	dropdownColumnNames=request.getAttribute("dropdownTable"+k)==null?new String[0][0]:(String[][])request.getAttribute("dropdownTable"+k);
																if(dropdownColumnNames.length>0 ){	
																	for (int i = 0; i < dropdownColumnNames.length; ++i) {
															%>
																		<option value="<%=dropdownColumnNames[i][0]%>"
																			<%=dropdownColumnNames[i][0].equalsIgnoreCase((arrayLength>k && configDetails[k][4]!=null)?configDetails[k][4]:"") ? "selected": ""%>>
																			<%=dropdownColumnNames[i][0]%>
																		</option>
															<%		}
																}
															%>
														</select>
													</td> --%>
													<td>
														<%	String[] VALIDATION_TYPE = { "String", "Number", "Date"}; %>
														<select name="validationType<%=k%>" class="inputBoxS">
															<option value="">None</option>
															<% 	for (int i = 0; i < VALIDATION_TYPE.length; ++i) {	%>
																<option value="<%=VALIDATION_TYPE[i]%>"<%=VALIDATION_TYPE[i].equalsIgnoreCase((arrayLength>k && configDetails[k][6]!=null)?configDetails[k][6]:"") ? "selected": ""%>>
																	<%=VALIDATION_TYPE[i]%>
																</option>
															<%	} %>
														</select>
													</td>
													<td>
														<input class="inputBox" type="text" name="destTable<%=k%>" value="OFS_TRANSACTION_DETAILS<%--=(arrayLength>k && configDetails[k][7]!=null)?configDetails[k][7]:"OFS_TRANSACTION_DETAILS" --%>"  maxlength="10" size="10" readonly>
													</td>
													<td>
														<select name="destColumn<%=k%>" class="inputBoxS">
															<option value="">Select</option>
															<%	if(columnNames.length>0){
																	for (int i = 0; i < columnNames.length; ++i) { %>
																	<option value="<%=columnNames[i][0]%>"
																		<%=columnNames[i][0].equalsIgnoreCase((arrayLength>k && configDetails[k][8]!=null)?configDetails[k][8]:"") ? "selected": ""%>>
																		<%=columnNames[i][0]%>
																	</option>
															<%		}
																} %>
														</select>
													</td>
													<td>
														<input type="radio" class="textbox" name="mandatory<%=k%>" value="Y" <%=(arrayLength>k && configDetails[k][10]!=null && configDetails[k][10].equals("Y")) ? "checked" : ""%>> &nbsp;Yes<br/>
														<input type="radio" class="textbox" name="mandatory<%=k%>" value="N" <%=(arrayLength>k && configDetails[k][10]!=null&& configDetails[k][10].equals("N")) ? "checked" : ""%>> &nbsp;No
													</td>
													<td>
														<input type="radio" class="textbox" name="totalSumInsured<%=k%>" value="Y" <%=(arrayLength>k && configDetails[k][11]!=null && configDetails[k][11].equals("Y")) ? "checked" : ""%>> &nbsp;Yes<br/>
														<input type="radio" class="textbox" name="totalSumInsured<%=k%>" value="N" <%=(arrayLength>k && configDetails[k][11]!=null&& configDetails[k][11].equals("N")) ? "checked" : ""%>> &nbsp;No
													</td>
													<td>
														<input type="radio" class="textbox" name="status<%=k%>" value="Y" <%=(arrayLength>k && configDetails[k][9]!=null && configDetails[k][9].equals("Y")) ? "checked" : ""%>> &nbsp;Active<br/>
														<input type="radio" class="textbox" name="status<%=k%>" value="N" <%=(arrayLength>k && configDetails[k][9]!=null&& configDetails[k][9].equals("N")) ? "checked" : ""%>> &nbsp;Deactive
														<input type="hidden" name="requestForm" id="requestForm">
														<input type="hidden" name="scheme" value="<%=schemeId%>">
														<input type="hidden" name="contentType" value="<%=contentTypeID%>">
														<input type="hidden" name="addMore" value="<%=addMore%>">
														<input type="hidden" name=scheme_id value="<%=schemeId%>">
														<input type="hidden" name="product_id" value="<%=proId%>">
														<input type="hidden" name="coverage" value="<%=coverid%>">
														<input type="hidden" name="subcoverage" value="<%=subcoverid%>">
														<input type="hidden" name="tableName" id="tableName">
														<input type="hidden" name="controlType" id="controlType">
														<input type="hidden" name="row" id="row">
														<input type="hidden" name="flag" id="flag" value="<%=flag%>">
														<input type="hidden" name="error" id="error" value="<%=error%>">
													</td>
												</tr>
												<%} %>
												</tbody>
											</table>
										</div>
									</div>
									
								</div>
							</div>
						</div>
					</div>
					<br class="clear" />
					<div class="row">
						<div class="col-xs-12" align="center">
							<input type="button" onclick="return SubmitSaveAndExit()" class="btn btn-sm btn-success" value="Submit">
							&nbsp;&nbsp;&nbsp;
							<input type="button" onclick="return AddMore()" class="btn btn-sm btn-primary" value="Add More">
							&nbsp;&nbsp;&nbsp;
							<input type="button"  onclick="fnSubmit()" class="btn btn-sm btn-danger" value="Back">
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
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
function getDate(fieldName) {
	var cal1 = new calendar1(document.forms["form1"].elements[fieldName]);
	cal1.year_scroll = true;
	cal1.time_comp = false;
	cal1.popup();
}

function ismaxlength(obj) {
	var mlength=obj.getAttribute? parseInt(obj.getAttribute("maxlength")) : ""
	//alert(mlength);
	if (obj.getAttribute && obj.value.length>mlength)
		obj.value=obj.value.substring(0,mlength)
}

function SubmitSaveAndExit() {
	document.getElementById('requestForm').value = 'configInsert';
	document.form1.submit();
	return false;
}

function AddMore() {
	document.getElementById('requestForm').value='configAddMore';
	document.getElementById('row').value=rowNo;
	document.form1.submit();
	return false;
}
var rowNo='';
rowNo+='<%=row%>';

function ColumnNames(type,table) {
	if(document.getElementById(type).value=='dropdown') {
		rowNo+=table+',';
		document.getElementById('row').value=rowNo;
		document.getElementById('tableName').value=table;
		document.getElementById('requestForm').value='dropdownTable';
		document.form1.submit();
	}
	return false;
}

var flag=document.getElementById('flag').value;
function Call() {
	if(flag=='true') {	
		var values='';
		<%for(int k=0; k<addMore; k++){%>
			var val=document.getElementById('dropdownTable<%=k%>').value;
			var control=document.getElementById('controlType<%=k%>').value;
			if(document.getElementById('controlType<%=k%>').value=='dropdown') {
				rowNo+=val+':<%=k%>'+',';
				<%if(arrayLength>k && configDetails[k][3]!=null){%>
				values+='dropdownKey<%=k%>=<%=configDetails[k][3]%>&';
				<%}%>
				<%if(arrayLength>k && configDetails[k][4]!=null){%>
				values+='dropdownValue<%=k%>=<%=configDetails[k][4]%>&';
				<%}%>
			}
		<%}%>
		document.getElementById('row').value=rowNo;
		document.getElementById('requestForm').value='dropdownTable';
		document.getElementById('flag').value='false';
		document.form1.action="CoverageController?"+values.substring(0, values.length-1);
		document.form1.submit();
	}
return false;
}

function fnSubmit() {
	window.close();
}
</script>
</html>