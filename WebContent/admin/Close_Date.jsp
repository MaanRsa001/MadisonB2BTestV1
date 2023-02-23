<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.*" %>
<jsp:useBean id="exportBean" class="com.maan.admin.ExportBean">
</jsp:useBean>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML>
<html>
<head>
<%
	String proCodes = "";
	proCodes=request.getParameter("productCode")==null?"":(String)request.getParameter("productCode");
%> 
<title>Closed TRN Details</title>
</head>
<body onLoad="dislayAll('<%=proCodes%>')">
<div id="page" class="content">
	<div>
		<%@ include file="header.jsp" %>
	</div>
	<br class="clear"/>
	<form name="form1" method="post">
	<div class="body">
		<%
			String error=request.getParameter("error")==null?"":request.getParameter("error");
			String user=(String)session.getAttribute("user");
			String closedDay=request.getParameter("closedDay")==null?"Select":request.getParameter("closedDay");
			String closedMonth=request.getParameter("closedMonth")==null?"Select":request.getParameter("closedMonth");
			String closedYear=request.getParameter("closedYear")==null?"Select":request.getParameter("closedYear");
			String openDay=request.getParameter("openDay")==null?"Select":request.getParameter("openDay");
			String openMonth=request.getParameter("openMonth")==null?"Select":request.getParameter("openMonth");
			String openYear=request.getParameter("openYear")==null?"Select":request.getParameter("openYear");
			String entryDay = "";
			String entryMonth= "";
			String entryYear= "";
			String Closed_Remarks = request.getParameter("Closed_Remarks")==null?"":request.getParameter("Closed_Remarks");
			
			String productDetail[][]=exportBean.getProductDetail(actualBranch);
		
			String error_msg = "";
			if(request.getAttribute("Trn_Error")!=null)
				error_msg = (String)request.getAttribute("Trn_Error");
			String result_TRN[][] = new String[0][0];
		%>
		<%
			String[] DD={"01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"};
			String[] MMM={"JAN","FEB","MAR","APR","MAY","JUN","JUL","AUG","SEP","OCT","NOV","DEC"};
			String[] YYYY={"2009","2010","2011","2012","2013"};
		
			Date date = new Date();
			String rptDate;
			SimpleDateFormat YearOnly = new SimpleDateFormat("yyyy");
			SimpleDateFormat MonthOnly = new SimpleDateFormat("MMM");
			SimpleDateFormat DayOnly = new SimpleDateFormat("dd");
		
			entryYear = YearOnly.format(date);
			entryMonth = MonthOnly.format(date);
			entryDay = DayOnly.format(date);
		
		%>
		<div class="row">
			<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
				<div class="panel panel-primary">
					<div class="panel-heading">
						<s:text name="TRN CLOSING DETAILS" />
						<span class="pullRight label label-warning">
							<%=entryDay%>&nbsp;<%=entryMonth%>&nbsp;<%=entryYear%>
						</span>
						<br class="clear" />
					</div>			
					<div class="panel-body">
						<% if(error_msg.length()>0) { %>
						<div class="row">
							<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
								<div id="errors1" style="color: red; ">&nbsp;</div>
							</div>
						</div>						
						<% } %>
						<div class="row">
							<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
								<div class="text"> <s:text name="Please select the product :"/> </div>
								<div class="tbox">
									<select name="productCode" id="procode" onChange="hideRows()" class="inputBoxS">
										<option value="0" id="0">---Select---</option>
										<%	for(int pro=0;pro<productDetail.length;pro++) {
												if(productDetail[pro][0].equals(proCodes)) { %>
													<option value="<%=productDetail[pro][0]%>" id="<%=productDetail[pro][0]%>" selected> <%=productDetail[pro][1]%></option>
											<% 	} else { %>
												<option value="<%=productDetail[pro][0]%>" id="<%=productDetail[pro][0]%>"> <%=productDetail[pro][1]%></option>
											<%	}
											} %>
									</select>
								</div>
							</div>
						</div>
						<div class="row">
							<% 	if(error_msg.length()<=0) {
									try {
										//result_TRN = exportBean.selectCloseTRN(actualBranch);
										result_TRN = exportBean.selectCloseTRN(actualBranch,proCodes);
										if(result_TRN.length>0) {
											StringTokenizer stClose = new StringTokenizer((result_TRN[0][0]==null?"":result_TRN[0][0]),"-");
											while(stClose.hasMoreTokens()) {
												closedDay = stClose.nextToken();
												closedMonth = stClose.nextToken();
												closedYear = stClose.nextToken();
											}
											StringTokenizer stopen = new StringTokenizer((result_TRN[0][3]==null?"":result_TRN[0][3]),"-");
											while(stopen.hasMoreTokens()) {
												openDay = stopen.nextToken();
												openMonth = stopen.nextToken();
												openYear = stopen.nextToken();
											}
											Closed_Remarks = result_TRN[0][1]==null?"":result_TRN[0][1];
										}
									} catch(Exception e) {
										System.out.println("Exception in selecting data from T_TRN_CLOSING"+e.toString());
									}
								}
							%>
							<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4" id="policyOpenDateRow">
								<div class="text"> <s:text name="Policy Open Date :"/> </div>
								<div class="tbox">
									<div class="row">
										<div class="col-xs-4">
											<select name='openDay' class="inputBoxS">  
												<option value='Select' >DD</option>
												<% for(int Sele=0;Sele<DD.length;Sele++) { %>
													<option value=<%=DD[Sele]%> <%=openDay.equalsIgnoreCase(DD[Sele])?"SELECTED":""%>><%=DD[Sele]%></option>	
												<% } %>
											</select>
										</div>
										<div class="col-xs-4">
											<select name='openMonth' class="inputBoxS">  
												<option value='Select' >MMM</option>	
											  	<% for(int Sele=0;Sele<MMM.length;Sele++) { %>
													<option value=<%=MMM[Sele]%> <%=openMonth.equalsIgnoreCase(MMM[Sele])?"SELECTED":""%>> <%=MMM[Sele]%></option>	
												<% } %>
											</select>
										</div>
										<div class="col-xs-4">
											<select name='openYear' class="inputBoxS">  
												<option value='Select' >YYYY</option>	
												<% 	java.util.Calendar cal = java.util.Calendar.getInstance();
													for(int Sele=cal.get(java.util.Calendar.YEAR)-2;Sele<cal.get(java.util.Calendar.YEAR)+2;Sele++) { %>
													<option value=<%=Sele%> <%=openYear.equalsIgnoreCase(String.valueOf(Sele))?"SELECTED":""%>><%=Sele%></option>	
												<% 	} %>
											</select>
										</div>
									</div>
								</div>
							</div>
							<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4" id="policyCloseDateRow">
								<div class="text"> <s:text name="Policy Close Date :"/> </div>
								<div class="tbox">
									<div class="row">
										<div class="col-xs-4">
											<select name='closedDay' class="inputBoxS">  
												<option value='Select' >DD</option>
												<% for(int Sele=0;Sele<DD.length;Sele++) { %>
													<option value=<%=DD[Sele]%> <%=closedDay.equalsIgnoreCase(DD[Sele])?"SELECTED":""%>><%=DD[Sele]%></option>	
												<% } %>
											</select>
										</div>
										<div class="col-xs-4">
											<select name='closedMonth' class="inputBoxS">  
												<option value='Select' >MMM</option>	
											  	<% for(int Sele=0;Sele<MMM.length;Sele++) { %>
													<option value=<%=MMM[Sele]%> <%=closedMonth.equalsIgnoreCase(MMM[Sele])?"SELECTED":""%>> <%=MMM[Sele]%></option>	
												<% } %>
											</select>
										</div>
										<div class="col-xs-4">
											<select name='closedYear' class="inputBoxS">  
												<option value='Select' >YYYY</option>	
												<% 	for(int Sele=cal.get(java.util.Calendar.YEAR)-2;Sele<cal.get(java.util.Calendar.YEAR)+2;Sele++) { %>
													<option value=<%=Sele%> <%=closedYear.equalsIgnoreCase(String.valueOf(Sele))?"SELECTED":""%>><%=Sele%></option>	
												<% 	} %>
											</select>
										</div>
									</div>
								</div>
							</div>
							<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4" id="remarksRow">
								<div class="text"> <s:text name="Remarks :"/> </div>
								<div class="tbox">
									<textarea name="Closed_Remarks" class="inputBoxA" style="width: 100%;" rows="2" onkeyup="textLimit(this,110)"><%=Closed_Remarks%></textarea>
								</div>
							</div>
						</div>
						<br class="clear" />
						<div class="row">
							<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" align="center">
								<span id="viewReportRow">
									<a type="button" class="btn btn-sm btn-info" href="javascript:viewReport();">VIEW REPORT</a> &nbsp;&nbsp;&nbsp;
								</span>
								<span id="submitButtonRow">
									<a type="button" class="btn btn-sm btn-success" onclick="Actionsubmit()"> Submit </a>
								</span>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<input type="hidden" name="loginId" value='<%=user%>'/>
	<input type="hidden" name="requestfrom" value="Close_Date"/>
	<input type="hidden" name="path" id="pathid" value="<%=path%>/admin/Close_Date.jsp"/>
	<input type="hidden" name="entryYear" value='<%=entryYear%>'/>
	<input type="hidden" name="entryMonth" value='<%=entryMonth%>'/>
	<input type="hidden" name="entryDay" value='<%=entryDay%>'/>
	</form>
	<br class="clear"/>
	<div class="footer">
		<div class="footer_body">
		<table width="100%" align="center" cellpadding="0" cellspacing="0" >
			<tr>
				<td valign="bottom" width="100%" valign="bottom" bgColor="#4f6180">
					<table width="100%">
						<tr>
							<td style="PADDING-RIGHT: 10px" bgColor=#4f6180 align="center">
								<font color="white" style="font-weight: bold">&copy; &nbsp; All Rights are Reserved for Madison General Insurance</font>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		</div>
	</div>
</div>
</body>
<script type="text/javascript">
function viewReport()
{

	var urlargv=window.document.getElementById("procode").value
	var urlargn=window.document.getElementById("procode").options[window.document.getElementById("procode").selectedIndex].text;
	document.form1.action="../admin/Close_Date_Report.jsp?productCode="+urlargv+"&productName="+urlargn;
	document.form1.submit();
}

function hideRows()
{
	var procode=window.document.getElementById("procode").value;
	switch(procode)
	{
		case "0":
			window.document.getElementById("policyOpenDateRow").style.display='none';
			window.document.getElementById("policyCloseDateRow").style.display='none';
			window.document.getElementById("remarksRow").style.display='none';
			window.document.getElementById("submitButtonRow").style.display='none';
			window.document.getElementById("viewReportRow").style.display='none';
			break;
		default:
			window.document.getElementById("policyOpenDateRow").style.display='';
			window.document.getElementById("policyCloseDateRow").style.display='';
			window.document.getElementById("remarksRow").style.display='';
			window.document.getElementById("submitButtonRow").style.display='';
			window.document.getElementById("viewReportRow").style.display='';
		var url="";
		url=window.document.getElementById("pathid").value+"?productCode="+procode;
		window.location.href=url;
	}
}

function Actionsubmit()
{
	document.form1.action="ExportTableController"
	document.form1.submit();
} 

function textLimit(field, maxlen) 
{
	if (field.value.length > maxlen + 1)
		alert('Max Length is 120 Charectors only!');
	if (field.value.length > maxlen)
		field.value = field.value.substring(0, maxlen);
} 

function dislayAll(proval)
{
	if(proval=="")
	{
		window.document.getElementById("policyOpenDateRow").style.display='none';
		window.document.getElementById("policyCloseDateRow").style.display='none';
		window.document.getElementById("remarksRow").style.display='none';
		window.document.getElementById("submitButtonRow").style.display='none';
		window.document.getElementById("viewReportRow").style.display='none';
	}
}
</script>
</html>