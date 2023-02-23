<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.*" %>
<jsp:useBean id="admin" class="com.maan.admin.ExportBean">
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
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/dataTables/css/dataTables.responsive.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/dataTables/css/dataTables.jqueryui.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/dataTables/css/dataTables.tableTools.css">
	<link href="${pageContext.request.contextPath}/dataTables/css/jquery-start-ui.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="${pageContext.request.contextPath}/dataTables/js/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/dataTables/js/jquery.dataTables.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/dataTables/js/dataTables.jqueryui.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/dataTables/js/dataTables.responsive.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/dataTables/js/dataTables.tableTools.js"></script>
	<script type="text/javascript">
	 	jQuery(function ($) {
	 		try {
			var data1 = $('.display').dataTable( {
				"lengthMenu": [[10, 25, 50, -1], [10, 25, 50, "All"]],
				"order": [[ 1, "asc" ]],
				"columnDefs": [ {
		          "targets": 'no-sort',
		          "orderable": false
			    } ],
				responsive: true
			});
		} catch(err){}
	} );	
 	</script>
 	<title>Closed TRN Details</title> 
</head>
<body>
<%	int no_of_records=10;
	int displaypages=5;
	int samplepages=displaypages;
	if(request.getParameter("displaypages")!=null&&!request.getParameter("displaypages").equalsIgnoreCase(""))
		displaypages = request.getParameter("displaypages")==null?3:Integer.parseInt(request.getParameter("displaypages"));
%>
<div id="page" class="content">
	<div>
		<%@ include file="header.jsp" %>
	</div>
	<br class="clear"/>
	<form name="form1" method="post" action="BrokerCreationController">
	<div class="body">
	<%
		String productCodes=request.getParameter("productCode")==null?"":(String)request.getParameter("productCode");
		String[][] app = new String[0][0];
		app = admin.selectTRNClosingDetails(actualBranch,productCodes);
		int length123=0;
		if(app.length==0)
			length123=1;
		else
			length123=app.length;
		int pages=length123/no_of_records;
		int rem=length123%no_of_records;
		if(rem!=0)
			pages=pages+1;
		int display=0;
		int spage=1;
		int  start=0;
		if(request.getParameter("spage")!=null&&!request.getParameter("spage").equalsIgnoreCase(""))
			spage=request.getParameter("spage")==null?1:Integer.parseInt(request.getParameter("spage"));
		if(request.getParameter("start")!=null&&!request.getParameter("start").equalsIgnoreCase(""))
			start=request.getParameter("start")==null?0:Integer.parseInt(request.getParameter("start"));
		display=no_of_records*spage;
		if(spage>=displaypages) {
			if(pages>displaypages) {
				start++;
				displaypages++;
			}
		}
		else if((displaypages-spage)==(samplepages-1)&&start!=0) {
			start--;
			displaypages--;
		}	
	%>
		<div class="row">
			<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
				<div class="panel panel-primary">
					<div class="panel-heading">
						<s:text name="TRN CLOSING REPORTS" />
					</div>			
					<div class="panel-body">
						<div class="row">
							<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
								<table class="display responsive no-wrap" id="record" width="100%" cellspacing="0">
									<thead>
									<tr>
										<th class="no-sort"></th>
										<th><s:text name="S.No." /></th>
										<th>MONTH</th>
										<th>START DATE</th>
										<th>END DATE</th>
										<th>FUTURE DATE</th>
										<th>REMARKS</th>
										<th>ENTRY DATE</th>
									</tr>
									</thead>
									<tbody>
									<%
										int k=0;
										int skip=0;
										int count=0;
										for(int i=0;i<app.length;i++) {
									%>
									<tr>
										<td></td>
										<td><%=(app[i][0]!=null?app[i][0]:"")%></td>
										<td><%=(app[i][1]!=null?app[i][1]:"")%></td>
										<td><%=(app[i][2]!=null?app[i][2]:"")%></td>
										<td><%=(app[i][3]!=null?app[i][3]:"")%></td>
										<td><%=(app[i][4]!=null?app[i][4]:"")%></td>
										<td><%=(app[i][6]!=null?app[i][6]:"")%></td>
										<td><%=(app[i][5]!=null?app[i][5]:"")%></td>											
									</tr>
									<% } %>
									</tbody>
								</table>
							</div>
						</div>
						<br class="clear" />
						<div class="row">
							<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" align="center">
								<input name="image" type="button" class="btn btn-sm btn-danger"  value="Back"  onclick="back()" />
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<input type="hidden" name="spage">
	<input type="hidden" name="displaypages">
	<input type="hidden" name="start">
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
function back() {
	document.form1.action="Close_Date.jsp";
	document.form1.submit();
}

function Existing(value123,displaypages,start) {
	document.form1.spage.value=value123;
	document.form1.start.value=start;
	document.form1.displaypages.value=displaypages;
	document.form1.action="Close_Date_Report.jsp"
	document.form1.submit();
}
</script>
</html>