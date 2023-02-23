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
<body>
<div id="page" class="content">
	<div>
		<%@ include file="header.jsp" %>
	</div>
	<br class="clear"/>
	<%
		String error=request.getParameter("error")==null?"":request.getParameter("error");
		String user=(String)session.getAttribute("user");
		/////////////////Closed Date
		String	closedDay="";
		String	closedMonth="";
		String	closedYear="";
		/////////////////Open Date
		String	openDay="";
		String	openMonth="";
		String	openYear="";
		/////////////////Entry Date
		String	entryDay = "";
		String	entryMonth= "";
		String	entryYear= "";
	%>
	<%	
	String error_msg = "";
	if(request.getAttribute("Trn_Error")!=null)
		error_msg = (String)request.getAttribute("Trn_Error");
	String result_TRN[][] = new String[0][0];
	if(error_msg.length()>0) {
		try {		    
			String productCode="";			
			productCode=(String)request.getAttribute("productCode");		   
			result_TRN = exportBean.selectCloseTRN(actualBranch,productCode);		
			
			if(result_TRN.length>0) {
				StringTokenizer stClose = new StringTokenizer((result_TRN[0][0]==null?"":result_TRN[0][0]),"-");
				while(stClose.hasMoreTokens()) {
					closedDay = stClose.nextToken();
					closedMonth = stClose.nextToken();
					closedYear = stClose.nextToken();
				}
				StringTokenizer stentry = new StringTokenizer((result_TRN[0][2]==null?"":result_TRN[0][2]),"-");
				while(stentry.hasMoreTokens()) {
					entryDay = stentry.nextToken();
					entryMonth = stentry.nextToken();
					entryYear = stentry.nextToken();
				}
				StringTokenizer stopen = new StringTokenizer((result_TRN[0][3]==null?"":result_TRN[0][3]),"-");
				while(stopen.hasMoreTokens()) {
					openDay = stopen.nextToken();
					openMonth = stopen.nextToken();
					openYear = stopen.nextToken();
				}
			}
			
		} catch(Exception e) {
			System.out.println("Exception in selecting data from T_TRN_CLOSING"+e.toString());
		}
	}
	int cm =0;
	int cm1 =0;
	int om =0;
	int em =0;
	String cmm = "";
	String cmm1 = "";
	String omm = "";
	String emm = "";
	String closedYear1 = "";
	try {
		String[] MMM={"JAN","FEB","MAR","APR","MAY","JUN","JUL","AUG","SEP","OCT","NOV","DEC"};
		for(int i=0;i<MMM.length;i++) {
			if(MMM[i].equalsIgnoreCase(closedMonth))
				cm = i+1;
			if(MMM[i].equalsIgnoreCase(openMonth))
				om = i+1;
			if(MMM[i].equalsIgnoreCase(entryMonth))
				em = i+1;
		}
		if(cm<=9)
			cmm = "0"+cm;
		else
			cmm = ""+cm;
		if(om<=9)
			omm = "0"+om;
		else
			omm = ""+om;
		if(em<=9)
			emm = "0"+em;
		else
			emm = ""+em;
		if(cm==12) {
			cm1=0;
			closedYear1 = ""+(Integer.parseInt(closedYear)+1);
		} else {
			cm1=cm;
			closedYear1 = ""+(Integer.parseInt(closedYear));
		}
		if(cm1<=9)
			cmm1 = "0"+(cm1+1);
		else
			cmm1 = ""+(cm1+1);
	} catch(Exception e) {
		System.out.println("Exception in T_TRN_CLOSING"+e.toString());
	}
	%>
	<form name="form1" method="post">
		<div class="body">
			<div class="row">
				<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
					<div class="panel panel-primary">
						<div class="panel-heading">
							<s:text name="TRN CLOSING DETAILS CONFIRMATION" />
							<span class="pullRight label label-warning">
								<%=entryDay%>&nbsp;<%=entryMonth%>&nbsp;<%=entryYear%>
							</span>
							<br class="clear" />
						</div>			
						<div class="panel-body">
							<% if(error_msg.length()>0) { %>
								<font color="red"><%=error_msg%></font>
							<% } %>
							<div class="panel panel-success">
								<div class="panel-heading">
									For the Month of <%=entryMonth%> <%=entryYear%>
								</div>
								<div class="panel-body">
									<div class="row">
										<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6" align="center">
											Policy Open Date	: <span class="label label-danger"><%=openDay%>:<%=omm%>:<%=openYear%></span>
										</div>
										<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6" align="center">
											Policy Close Date	: <span class="label label-danger"><%=closedDay%>:<%=cmm%>:<%=closedYear%></span>
										</div>
									</div>
								</div>
							</div>
							<div class="panel panel-warning">
								<div class="panel-heading">
									After <%=closedDay%>:<%=cmm%>:<%=closedYear%>
								</div>
								<div class="panel-body">
									<div class="row">
										<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" align="center">
											All policies generated from <span class="label label-primary"><%=(Integer.parseInt(closedDay)+1)%>:<%=cmm%>:<%=closedYear%></span> to <span class="label label-primary"><%=openDay%>:<%=cmm1%>:<%=closedYear1%></span> are inserted under <span class="label label-primary"><%=openDay%>:<%=cmm1%>:<%=closedYear1%></span>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<br class="clear" />
			<div class="row">
				<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" align="center">
					<input class="btn btn-sm btn-danger" value="Back" onclick="back()" />
				</div>
			</div>
		</div>		
	</form>
</div>
</body>
<script type="text/javascript">
function back() {
	document.form1.action="Close_Date.jsp"
	document.form1.submit();
} 
</script>
</html>