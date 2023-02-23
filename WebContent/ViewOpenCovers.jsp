<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<jsp:directive.page import="java.util.*"/>
<%@taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@page import="com.maan.product.ProductSelection" isELIgnored="false"%>
<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@page import="com.maan.report.dao.ReportDAO"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<% 
String productSelection = request.getParameter("productSelection")==null?"":request.getParameter("productSelection");
//if((request.getQueryString())!=null&&!productSelection.equalsIgnoreCase("productSelection"))
if((request.getQueryString())!=null&&!productSelection.equalsIgnoreCase("productSelection") && !"rsa".equals((String)session.getAttribute("mode"))){
	//session.removeAttribute("ses");
}
%>
<%@ include file="login/home1.jsp" %>
<%@ include file="login/sessionsCheckNormal.jsp"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta name="description" content="">
	<meta name="keywords" content="Maan Sarovar, Sea Trans, Marine Insurance">	
	<meta name="author" content="">
	<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/bootstrap/css/demo.css" />
	<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/bootstrap/css/bootstrap-multiselect.css" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/bootstrap/css/datepicker3.css">
	
	<link href="<%=request.getContextPath()%>/bootstrap/css/footable-0.1.css" rel="stylesheet" type="text/css">
	<link href="<%=request.getContextPath()%>/bootstrap/css/table-res.css" rel="stylesheet" type="text/css">
	<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/bootstrap/css/bootstrap.min.css" />
	
	<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/bootstrap/css/style.css" />
	<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.min.js"></script>
	
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/common.js"></script>
	<script src="<%=request.getContextPath()%>/bootstrap/js/modernizr.custom.63321.js"></script>
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
				"order": [[ 0, "asc" ]],
				"columnDefs": [ {
		          "targets": 'no-sort',
		          "orderable": false
			    } ],
				responsive: true
			});
		} catch(err){}
	} );	
 	</script> 
	<style type="text/css">
	.panel-heading {
		padding: 5px;
	}
	</style>
</head>
<%
	String productTypeId="";
	String QuoteOpt="";
	QuoteOpt=request.getParameter("QuoteOpt")==null?"":request.getParameter("QuoteOpt");
	productTypeId=request.getParameter("selectProd")==null?(String) session.getAttribute("product_id")==null?"0":(String) session.getAttribute("product_id"):request.getParameter("selectProd");
	
	if("0".equalsIgnoreCase(productTypeId))
		response.sendRedirect("../login/error_messg.jsp");
	else
		session.setAttribute("product_id",productTypeId);
		
	String userTypes = (String)session.getAttribute("usertype");
	String loginId = (String)session.getAttribute("user");
%>
<%
 	String dtdiff=request.getParameter("dtdiff")==null?"":request.getParameter("dtdiff");
	String searchby=null;
	searchby=request.getParameter("searchby")==null?"":request.getParameter("searchby");
	String  customer_names=null;
	customer_names=request.getParameter("customer_names")==null?"":request.getParameter("customer_names");
	String  search_option=null;
	search_option=request.getParameter("search_option")==null?"":request.getParameter("search_option");
	
	int no_of_records=10;
	int lapsed_no_of_records=10;
	
	int displaypages=5;
	int samplepages=displaypages;
	int lapsed_displaypages=5;
	int lapsed_samplepages=lapsed_displaypages;
	
	if(request.getParameter("displaypages")!=null&&!request.getParameter("displaypages").equalsIgnoreCase(""))
		displaypages=request.getParameter("displaypages")==null?3:Integer.parseInt(request.getParameter("displaypages"));
	session.setAttribute("company_id","1");
	String cusName=null;
	cusName=loginId;
	
	if(request.getParameter("selectUser")!=null&&!"select".equalsIgnoreCase(request.getParameter("selectUser"))) {
		cusName=request.getParameter("selectUser");
	} else {
		cusName=loginId;
		session.removeAttribute("userName");
	}
	
	com.maan.services.util.dataCollection collect=null;
	collect = new com.maan.services.util.dataCollection();
	com.maan.premium.DAO.PremiumInputsBean prem = null;
	prem = new com.maan.premium.DAO.PremiumInputsBean();

	String[][] viewQuote123=new String[0][0];
	String[][] viewQuote124=new String[0][0];
	
	String DateValid=null;
	String rsaIssuer = (String)session.getAttribute("RSAISSUER");
	
	String[][] loginCustomers = new String[0][0];
	if(rsaIssuer==null)
		loginCustomers=collect.getNewLogIds(loginId);
	viewQuote124 = collect.getLapsedOpenCoversSearch(cusName,searchby,customer_names);
%>
<body onLoad="datealert('<%=dtdiff%>')">
<div id="page" class="content">
	<div class="header">
		<%@include file="openMenu.jsp"%>
	</div>
	<div class="body">
		<div class="table0">
		<%	int length124=0;
			if(viewQuote124.length==0)
				length124=1;
			else
				length124=viewQuote124.length;
			
			int lapsed_pages=length124/lapsed_no_of_records;
			int lapsed_rem=length124%lapsed_no_of_records;
			
			if(lapsed_rem!=0)
				lapsed_pages=lapsed_pages+1;
			
			int lapsed_display=0;
			int lapsed_spage=1;
			int lapsed_start=0;
			
			if(request.getParameter("lapsed_spage")!=null&&!request.getParameter("lapsed_spage").equalsIgnoreCase(""))
				lapsed_spage=request.getParameter("lapsed_spage")==null?1:Integer.parseInt(request.getParameter("lapsed_spage"));
			if(request.getParameter("lapsed_start")!=null&&!request.getParameter("lapsed_start").equalsIgnoreCase(""))
				lapsed_start=request.getParameter("lapsed_start")==null?0:Integer.parseInt(request.getParameter("lapsed_start"));
			lapsed_display=lapsed_no_of_records*lapsed_spage;
			if(lapsed_spage >= lapsed_displaypages) {
				if(lapsed_pages>lapsed_displaypages) {
					lapsed_start++;
					lapsed_displaypages++;
				}
			} else if((lapsed_displaypages-lapsed_spage)==(lapsed_samplepages-1)&& lapsed_start!=0) {
				lapsed_start--;
				lapsed_displaypages--;
			}
			if(viewQuote124.length>0) {
			%>
			<div class="tablerow">
				<div class="panel panel-primary">
					<div class="panel-heading">
						<s:text name="LAPSED OPEN COVERS" />
					</div>
					<div class="panel-body">
						<table class="display responsive no-wrap" id="record" width="100%" cellspacing="0">
							<thead>
							<tr>
								<th>S.No</th>
								<th>Core Application Policy No. </th>
								<th>OpenCover No. </th>
								<th>Customer Name </th>
								<th>OpenCover Date </th>
								<th>Validity Period </th>
								<th>Remarks</th>
								<th>Certificate</th>
							</tr>
							</thead>
							<tbody>
							<%
							int lapsed_k=0;
							int lapsed_skip=0;
							int lapsed_count=0;
							for(int i=0;i<viewQuote124.length;i++) {
								lapsed_k++;
								String DateEntry	=	viewQuote124[i][2]==null?"":viewQuote124[i][2];
								String MonthEntry	=	viewQuote124[i][3]==null?"":viewQuote124[i][3];
								String YearEntry	=	viewQuote124[i][4]==null?"":viewQuote124[i][4];
								String DateExp		=	viewQuote124[i][5]==null?"":viewQuote124[i][5];
								String MonthExp	=	viewQuote124[i][6]==null?"":viewQuote124[i][6];
								String YearExp		=	viewQuote124[i][7]==null?"":viewQuote124[i][7];
								
								String adminState = viewQuote124[i][15]==null?"":viewQuote124[i][15];
								String lapsRemarks="";
								if(adminState.equalsIgnoreCase("N"))
									lapsRemarks = "Admin Cancelled";
								else
									lapsRemarks = "Date Expired";							
							%>
							<tr>
								<td> <%=lapsed_k%> </td>
								<td><%=viewQuote124[i][14]==null?"":viewQuote124[i][14]%></td>
								<td><%=viewQuote124[i][0]==null?"":viewQuote124[i][0]%></td>
								<td><%=viewQuote124[i][8]==null?"":viewQuote124[i][8]%></td>
								<td><%=DateEntry+"/"+MonthEntry+"/"+YearEntry%></td>
								<td><%=DateExp+"/"+MonthExp+"/"+YearExp%></td>
								<td><%=lapsRemarks%></td>
								<td>
									<a type="button" href="#" class="btn btn-sm btn-warning" title="New Quote" onClick="return newQuote('<%=viewQuote124[i][0]%>','<%=productTypeId%>','<%=viewQuote124[i][1]%>','<%=QuoteOpt%>')"><b> Certificate</b> </a>
								</td>								
							</tr>
							<%} %>							
							</tbody>
						</table>
					</div>
				</div>
			</div>
			<% } // Lapsed Open Cover If Length Ends here %>
			<%/****************pagenaaaaaaaaaa************/
			if(!"select".equalsIgnoreCase(request.getParameter("selectUser")) && request.getParameter("selectUser")!=null)
				session.setAttribute("userName",request.getParameter("selectUser"));
		
			if(session.getAttribute("userName")==null)
				session.setAttribute("userName",cusName);
				cusName = (String)session.getAttribute("userName");
				try {
			        if(session.getAttribute("applicationNo") !=null)
						session.removeAttribute("applicationNo");	        
			        if(session.getAttribute("countryDetails") !=null)
						session.removeAttribute("countryDetails");	        
			        if(session.getAttribute("quote_no") !=null)
			        	session.removeAttribute("quote_no");	
					if(session.getAttribute("openCoverNo") !=null)
						session.removeAttribute("openCoverNo");	
					if(session.getAttribute("customer_id") !=null)
						session.removeAttribute("customer_id");
			        if(session.getAttribute("fullCommodity") !=null)
						session.removeAttribute("fullCommodity");
			        if(session.getAttribute("fullDetails") !=null)
						session.removeAttribute("fullDetails");
					if(session.getAttribute("fullDetailss") !=null)
						session.removeAttribute("fullDetailss");
			        if(session.getAttribute("sessionCountry") !=null)
						session.removeAttribute("sessionCountry");
			        if(session.getAttribute("fromCountryId") !=null)
						session.removeAttribute("fromCountryId");
			         if(session.getAttribute("toCountryId") !=null)
						session.removeAttribute("toCountryId");
				} catch(Exception ex){}
			%>
			<form name="ViewOpenCover" method="post">
			<input type="hidden" name="brokerName" value='<%=cusName%>'/> 
			<input type="hidden" name="custName" id="custName"/>
			<input type="hidden" name="quoteIds">
			<input type="hidden" name="mode">
			<input type="hidden" name="quote_no">
			<input type="hidden" name="openCoverNo">
			<input type="hidden" name="originalOpenCoverNo">
			<input type="hidden" name="openCustomerId">
			<input type="hidden" name="freshMode">
			<input type="hidden" name="openCover">
			<div class="tablerow" align="center">
				<font color="red"><%=DateValid==null?"":DateValid%></font>
			</div>
			<div class="tablerow">
				<div class="boxcontent">
					<div class="panel panel-primary">
						<div class="panel-body">
							<div class="textfield33">
								<div class="text">
						 			<s:text name="Search By"/>
						 		</div>
						 		<div class="tbox">
						 			<select name="searchby" class="inputBoxS" onChange="setData()">
										<option value="Select">All </option>
										<option value="FIRST_NAME" <%=("FIRST_NAME").equalsIgnoreCase(searchby)?"Selected":""%>>Customer Name</option>
										<option value="opencover_nos" <%=("opencover_nos").equalsIgnoreCase(searchby)?"Selected":""%>>Open Cover Number</option>
										<option value="DateWise" <%=("DateWise").equalsIgnoreCase(searchby)?"Selected":""%>>Date wise</option>
									</select>							 		
						 		</div>
							</div>
							<div class="textfield33">
								<div class="text">
						 			<s:text name="Enter Data For Search "/>
						 		</div>
						 		<div class="tbox">
						 			<input type="text" class="inputBox" name="customer_names" value="<%=customer_names%>" onsubmit='return focus1()'>
						 		</div>
							</div>
							<div class="textfield33" align="center">
								<input type="button" class="btn btn-sm btn-success" value="Go" onclick="return search_details()"  id='go' >
							</div>			
							<br class="clear" />
						</div>
					</div>
				</div>
				<%	if(rsaIssuer!=null){
						String[][] Broker = new ProductSelection().getBrokersList((String)session.getAttribute("LoginBranchCode"), rsaIssuer);
				%>
				<div class="boxcontent">
					<div class="panel panel-primary">
						<div class="panel-body">
							<div class="textfield33">
								<div class="text">
						 			<s:text name="Select Broker"/>
						 		</div>
						 		<div class="tbox">
						 			<select name="selectUser" onChange="samePage()" class="inputBoxS">
										<option value="0">
											Select
										</option>
										<% for (int i = 0; i < Broker.length; i++) {%>
											<option value="<%=Broker[i][0]%>" <%=cusName.equalsIgnoreCase(Broker[i][0])?"selected":""%>><%=Broker[i][1]%>(<%=Broker[i][0]%>) </option>
										<%}%>
									</select>
									<% for (int i = 0; i < Broker.length; i++) {%>
										<input type="hidden" value="<%=Broker[i][2]%>" id="<%=Broker[i][0]%>" title="<%=Broker[i][3]%>"/>
									<%}%>
						 		</div>
						 	</div>
						 	<br class="clear"/>
						</div>
					</div> 	
				</div>
				<%	}%>
				<div class="boxcontent">
					<% 
						if("Broker".equalsIgnoreCase((String)session.getAttribute("usertype"))) {
			 				if(loginCustomers.length>0) { %>
							<div class="textfield33">
					<%} } else {%>
						<div class="textfield33V">
					<%} %>
						User Name &nbsp;:&nbsp; <%=cusName%>
					</div>
					<% 
						if("Broker".equalsIgnoreCase((String)session.getAttribute("usertype"))) {
			 				if(loginCustomers.length>0) { %>
					<div class="textfield33">
						<div class="text">
				 			<s:text name="Select User"/>
				 		</div>
				 		<div class="tbox">
				 			<select class="inputBoxS" name="selectUser" onChange="samePage()">
								<option value="select">Select</option>
								<%for(int i=0;i<loginCustomers.length;i++) { %>
									<option value="<%=loginCustomers[i][0]%>" <%=cusName.equalsIgnoreCase(loginCustomers[i][0])?"selected":""%>><%=loginCustomers[i][0]%></option>
								<%}%>
							</select>
				 		</div>
					</div>
					<%} }%>
					<br class="clear"/>
				</div>
			</div>
			<%	for(int ii=0; ii<3 ; ii++) { %>
			<div class="tablerow">
				<div class="boxcontent">
					<div class="panel panel-primary">
						<div class="panel-heading">
							<% 
								String policyType = "";
								if(ii==0) { 
										policyType = "11";
								%>
										EXISTING Marine Open cover (MOC)
								<%} else if(ii==1) { 
									policyType = "12";
								%>
									EXISTING Marine Open cover (MOP)
								<%} else if(ii==2) { 
										policyType = "13";
								%>
									EXISTING Marine Haulier Liability
								<%}
								if("DateWise".equalsIgnoreCase(searchby) && "Invalid".equalsIgnoreCase(collect.checkDate(customer_names)) ) {
									DateValid="* Search Date Invalid ";
									viewQuote123=collect.getCreatedOpenCoversSearch(cusName,"select",customer_names,userTypes,(String)session.getAttribute("LoginBranchCode"), rsaIssuer, policyType);
								} else {
									viewQuote123=collect.getCreatedOpenCoversSearch(cusName,searchby,customer_names,userTypes,(String)session.getAttribute("LoginBranchCode"), rsaIssuer, policyType);
								}
								Map yetStartMOC = new HashMap();
								String ymocList = "";
								
								if(viewQuote123.length > 0) {
									ymocList = prem.removeLastChar(prem.getQuotedStringFromArray(viewQuote123,0),',');//String array,index value
									if(ymocList.length() > 0)
										yetStartMOC = prem.getYetToStratMOC(ymocList);
								}
								
								int length123=0;
								if(viewQuote123.length==0) {
									length123=1;
								} else {
									length123=viewQuote123.length;
								}
								int pages=length123/no_of_records;
								
								int rem=length123%no_of_records;
								if(rem!=0) {
									pages=pages+1;
								}
								
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
								} else if((displaypages-spage)==(samplepages-1)&&start!=0) {
									start--;
									displaypages--;
								}
							%>
						</div>
						<div class="panel-body">
							<table class="display responsive no-wrap" id="record" width="100%" cellspacing="0">
								<thead>
								<tr>
									<th>S.No</th>
									<th>Core Application Policy No. </th>
									<th>OpenCover No. </th> 
									<th>Customer Name </th>
									<th>OpenCover Date </th>
									<th>Validity Period </th>
									<th>Balance Sum Insured</th>
									<% if("admin".equalsIgnoreCase(userTypes)||"RSAIssuer".equalsIgnoreCase(userTypes)) { %>
										<th>Schedule</th>
										<th>Policy Wordings</th>
									<% } %>
										<th>Certificate</th>
									<%	if(!QuoteOpt.equalsIgnoreCase("None")) { %>
										<th>LC Details</th>
									<% } %>
								</tr>
								</thead>
								<tbody>
								<%
									int k=0;
									int skip=0;
									int count=0;
									for(int i=0;i<viewQuote123.length;i++) {
										k++;
										String str="";
										if(viewQuote123[i][11]==null||viewQuote123[i][11].equals("")||viewQuote123[i][11].equalsIgnoreCase("null")) {
											str=(viewQuote123[i][8]==null?"":viewQuote123[i][8])+" "+(viewQuote123[i][9]==null?"":viewQuote123[i][9]);
										} else {
											str=viewQuote123[i][11];
										} 
								%>
									<tr>
										<td><%=k%></td>
										<td><%=viewQuote123[i][15]==null?"":viewQuote123[i][15]%></td>
										<td><%=viewQuote123[i][15]==null?"":viewQuote123[i][15]%></td>
										<td class="formtxtf"><%=viewQuote123[i][8]==null?"":viewQuote123[i][8]%></td>
										<td><%=viewQuote123[i][2]%>/<%=viewQuote123[i][3]%>/<%=viewQuote123[i][4]%></td>
										<td><%=viewQuote123[i][5]%>/<%=viewQuote123[i][6]%>/<%=viewQuote123[i][7]%></td>
										<td><%=viewQuote123[i][16]%></td>
										<% if("admin".equalsIgnoreCase(userTypes)||"RSAIssuer".equalsIgnoreCase(userTypes)) { %>
											<td>
												<a href="#" type="button" title="View Schedule" class="btn btn-sm btn-default" onclick="return viewDoc('<%=viewQuote123[i][15]%>','<%=(String)session.getAttribute("user")%>','schedule','<%=viewQuote123[i][15]%>','','<%=viewQuote123[i][12]%>','false');" > <b> Schedule </b></a>
											</td>
											<td>
												<a href="#" type="button" title="Policy Wordings" class="btn btn-sm btn-default" onclick="return viewDoc('<%=viewQuote123[i][15]%>','<%=(String)session.getAttribute("user")%>','clauses','<%=viewQuote123[i][15]%>','','<%=viewQuote123[i][12]%>','false');" > <b> View </b></a>
											</td>
										<% } %>
										<td>
										<%		
											String newmode = (String)request.getAttribute("mode");
											String val = (String)session.getAttribute("identify_Id");
											if("1".equalsIgnoreCase(newmode)) {
										%>
											<a href="#" type="button" class="btn btn-sm btn-default" title="New Quote" onClick="return newQuote('<%=viewQuote123[i][0]%>','<%=productTypeId%>','<%=cusName%>')"><b> Certificate</b> </a>
										<%
											} else {
												if(!yetStartMOC.isEmpty() && yetStartMOC.get("moc"+viewQuote123[i][0]) != null && yetStartMOC.get("moc"+viewQuote123[i][0]).equals(viewQuote123[i][0]) ) {
										%>
													<a href="#" type="button" class="btn btn-sm btn-warning" title="Certificate not Started"> <b> Yet to Start </b></a>
										<%		} else { %>
													<a href="#" type="button" class="btn btn-sm btn-warning" title="New Quote" onClick="return newQuote('<%=viewQuote123[i][0]%>','<%=productTypeId%>','<%=cusName%>','<%=viewQuote123[i][1]==null?newmode:viewQuote123[i][1]%>')"><b> Certificate</b> </a>
										<%		}
											 }
										%>
										</td>
										<%	if(!QuoteOpt.equalsIgnoreCase("None")) { %>
											<td> <a href="#" type="button" class="btn btn-sm btn-info" title="LC Details" onClick="return openLCDetailsTest('<%=cusName%>','<%=viewQuote123[i][0]%>','<%=viewQuote123[i][8]%>');"> <b>LC Details </b> </a> </td>
										<% 	} %>
									</tr>
								<% 	}%>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
			<%	} %>
			<%if((String) session.getAttribute("RSAISSUER")!=null){%>
			<div class="tablerow">
				<div class="panel panel-primary">
					<div class="panel-heading">
						ENDORSEMENT SEARCH
					</div>
					<div class="panel-body">
						<div class="textfield33">
					 		<div class="text">
					 			Search By Policy No
					 		</div>
					 		<div class="tbox">
					 			<input type="text" size="25" class="inputBox" maxlength='50' id="policyNo"  name="searchPolicyNo" value="<%=StringUtils.defaultIfEmpty(request.getParameter("searchPolicyNo"),"") %>"/>
					 		</div>
					 	</div>
					 	<div class="textfield33" align="center">
					 		<input type="button" class="btn btn-sm btn-success" value="Submit" onclick="return searchByPolicy()" />
					 	</div>
					 	<br class="clear"/>
					 	<br/>
					 	<%
							String policyNo=StringUtils.defaultIfEmpty(request.getParameter("searchPolicyNo"),"");
							if(StringUtils.isNotEmpty(policyNo)){
								request.setAttribute("policyList", new ReportDAO().getSearchResult(policyNo,(String) session.getAttribute("LoginBranchCode"), productTypeId));
							}
						%>
						<table class="display responsive no-wrap" id="record" width="100%" cellspacing="0">
							<thead>
							<tr>
								<th></th>
								<th><s:text name="Select" /></th>
								<th><s:text name="Policy No" /></th>
								<th><s:text name="Broker Name" /></th>
								<th><s:text name="Customer Name" /></th>
								<th><s:text name="Policy Date" /></th>
							</tr>
							</thead>
							<tbody>
							<s:iterator value="policyList" status="stat" var="record">
							<tr>
								<td></td>
								<td align="center"> <input type="radio" name="selectPolicy" value="${record.POLICY_NO}" onclick="setPolicyInfo('${record.POLICY_NO}','${record.OPEN_COVER_NO}','${record.LOGIN_ID}','${record.OC_CUST_ID}')"/> </td>
								<td><s:property value="POLICY_NO" /> </td>
								<td><s:property value="BROKER_NAME" /> </td>
								<td><s:property value="CUSTOMER_NAME" /> </td>
								<td><s:property value="POLICY_START_DATE" /> </td>
							</tr>
							</s:iterator>
							</tbody>
						</table>
					</div>
				</div>
			</div>
			<input type="hidden" name="selectProd" value="<%=productTypeId %>">
			<input type="hidden" name="searchFrom" value="BS">
			<input type="hidden" name="searchValue">
			<input type="hidden" name="policyNo">
			<%} %>
			<br/>
			<div class="tablerow" align="center">
				<% if(productSelection.equalsIgnoreCase("productSelection")) { %>
					<a href="<%=path%>" class="btn btn-sm btn-danger"> Back </a>
				<%	} else { %>
					<a href="<%=path%>/HomeUser.action" class="btn btn-sm btn-danger"> Back </a> 
					<%	if((String) session.getAttribute("RSAISSUER")!=null) {%>
						<input name="image" type="button" class="btn btn-sm btn-success" value="Proceed" onclick="return redirect()"> 
					<%
						}
					}
				%>
			</div>
			<input type="hidden" name="spage">
			<input type="hidden" name="displaypages">
			<input type="hidden" name="start">
			<input type="hidden" name="identifyCus">
			<input type="hidden" name="search_option">
			<input type="hidden" name="lapsed_spage">
			<input type="hidden" name="lapsed_displaypages">
			<input type="hidden" name="lapsed_start">
			
			<input type="hidden" name="opencover">
			<input type="hidden" name="openNo">
			<input type="hidden" name="lcNos">
			<input type="hidden" name="mode">
			<input type="hidden" name="lcBroker">
			<input type="hidden" name="fromBroker">
			<input type="hidden" name="actionPath">
			<input type="hidden" name="OpenSesCheck">
			<input type="hidden" name="productId">
			<input type="hidden" name="loginId">
			<input type="hidden" name="brokerCode">
			<input type="hidden" name="executive">
			<input type="hidden" name="OpenSesCheck">
			<input type="hidden" name="renewal">
			</form>
		</div>
	</div>	
</div>
<br/>
<div class="footer">
	<%@include file="/templates/footer.jsp"%>
</div>
<script type="text/javascript">
function stopRKey(evt) { 
  var evt = (evt) ? evt : ((event) ? event : null); 
  var node = (evt.target) ? evt.target : ((evt.srcElement) ? evt.srcElement : null); 
  if ((evt.keyCode == 13) && (node.type=="text"))  {return false;} 
} 
document.onkeypress = stopRKey;
function samePage() {
	//document.ViewOpenCover.action="ViewQuote_B2B.jsp";
	document.ViewOpenCover.action="<%=path%>/ViewOpenCovers.jsp";
	document.ViewOpenCover.submit();
}

function newQuotes(val,productTypeId,cusId,qopt) {
	document.ViewOpenCover.openCoverNo.value=val;
	document.ViewOpenCover.openCustomerId.value=cusId;
	document.ViewOpenCover.freshMode.value='fresh';
	document.ViewOpenCover.OpenSesCheck.value="NewQuote";
	if(qopt=='None')
		document.ViewOpenCover.action="<%=path%>/final.jsp";
	else {
		document.ViewOpenCover.actionPath.value="premium/QuotationOpen.jsp";
		document.ViewOpenCover.action="newquote.dos";
	}
	document.ViewOpenCover.submit();
	return false;
}

function delQuotes(val) {
	document.ViewOpenCover.action="<%=path%>/LapsedQuote1.jsp?Quote_No="+val;
	document.ViewOpenCover.submit();
}

function redirectt(ids) {
	document.ViewOpenCover.quoteIds.value=ids;
	document.ViewOpenCover.action="<%=path%>/missing.jsp";
	document.ViewOpenCover.submit();
	return false;
}

function ExistingCustomers_B2B(value123,displaypages,start) {
	document.ViewOpenCover.spage.value=value123;
	document.ViewOpenCover.start.value=start;
	document.ViewOpenCover.displaypages.value=displaypages;
	document.ViewOpenCover.action="<%=path%>/ViewOpenCovers.jsp"
	document.ViewOpenCover.submit();
}

function focus1() {
	  var elem = document.getElementById("go");
	  elem.focus();
}

function search_details() {
	document.ViewOpenCover.search_option.value="YES"
	document.ViewOpenCover.action="<%=path%>/ViewOpenCovers.jsp"
	document.ViewOpenCover.submit();
   return false;
}

function setData() {
	var search=document.ViewOpenCover.searchby.value;
	if(search=='Select')
			document.ViewOpenCover.customer_names.value="";
	else if(search=='DateWise')
			document.ViewOpenCover.customer_names.value="DD/MM/YYYY";
	//alert(search);
}

function sentMail(val) {//,val1)
	document.ViewOpenCover.quote_no.value=val;
	document.ViewOpenCover.action="mailController";
	document.ViewOpenCover.submit();
}

function datealert(dtdiff) {
		if(dtdiff>=-10 & dtdiff<=-1)
			 alert("You Must Change Your Password After  "+(Math.abs(dtdiff)-1)+" Days");;
		
}

/*function openLCDetails(opencover,lcNos)
{
	document.ViewOpenCover.opencover.value = opencover;
	document.ViewOpenCover.lcNos.value = lcNos;
	document.ViewOpenCover.mode.value = "add";
	document.ViewOpenCover.action="<%=path%>/premium/OpenCoverView.jsp";
	document.ViewOpenCover.submit();
}*/

function openLCDetails(log,open) {
	document.ViewOpenCover.openNo.value = open;
	document.ViewOpenCover.lcBroker.value = log;
	document.ViewOpenCover.fromBroker.value = "Yes";
	document.ViewOpenCover.action="<%=path%>/LCCreation/LCOpenCoverWise.jsp";
	document.ViewOpenCover.submit();
}

function openLCDetailsTest(log,open,cust) {
	document.ViewOpenCover.openNo.value = open;
	document.ViewOpenCover.lcBroker.value = log;
	document.getElementById("custName").value=cust;
	document.ViewOpenCover.openCover.value=open;
	document.ViewOpenCover.fromBroker.value = "Yes";
	document.ViewOpenCover.action="lcDetailsLC.action";
	document.ViewOpenCover.submit();
}


function lapsed_ExistingCustomers_B2B(value124,lapsed_displaypages,lapsed_start) {
	document.ViewOpenCover.lapsed_spage.value=value124;
	document.ViewOpenCover.lapsed_start.value=lapsed_start;
	document.ViewOpenCover.lapsed_displaypages.value=lapsed_displaypages;
	document.ViewOpenCover.action="<%=path%>/ViewOpenCovers.jsp"
	document.ViewOpenCover.submit();
}

function newQuote(openCoverNo, productId, loginId, customerId) {
	var selected=document.getElementById(loginId);
	if(selected) {
		document.ViewOpenCover.brokerCode.value=selected.value;
		document.ViewOpenCover.executive.value=selected.title;
	}	
	document.ViewOpenCover.loginId.value=loginId;
	document.ViewOpenCover.productId.value=productId;
	document.ViewOpenCover.openCoverNo.value=openCoverNo;
	document.ViewOpenCover.openCustomerId.value=customerId;
	document.ViewOpenCover.action="<%=path%>/initReport.action?menuType=CHART";
	document.ViewOpenCover.submit();
	return false;
}

function searchByPolicy() {
	if(document.ViewOpenCover.searchPolicyNo.value==''){
		alert('Please enter Policy No to search');
		return false;
	} else {
		document.ViewOpenCover.action='<%=path%>/ViewOpenCovers.jsp';
		document.ViewOpenCover.submit();
		return true;
	}
}

function redirect() {
	if(document.ViewOpenCover.searchValue.value==''){
		alert('Please select Policy No');
		return false;
	} else {
		document.ViewOpenCover.productId.value='<%=productTypeId%>';
		document.ViewOpenCover.action='<%=path%>/initReport.action?menuType=P&searchBy=policyNo';
		document.ViewOpenCover.submit();
		return true;
	}
}

function setPolicyInfo(policyNo, openCoverNo, loginId, openCustomerId)
{
	document.ViewOpenCover.searchValue.value=policyNo;
	document.ViewOpenCover.openCoverNo.value=openCoverNo;
	document.ViewOpenCover.loginId.value=loginId;
	document.ViewOpenCover.openCustomerId.value=openCustomerId;
}

function viewDoc(policynumber,loginId,docType,docNo,amendId,proposalNo,endtstatus) {
     var URL ="<%=request.getContextPath()%>/GenerateDocument.jspa?docType="+docType+"&policynumber="+policynumber+"&loginId="+loginId+"&docNo="+docNo+"&amendId="+amendId+"&proposalNo="+proposalNo+"&endtstatus="+endtstatus;
	 var windowName = "PolicyView";
	 var width  = screen.availWidth;
	 var height = screen.availHeight;
	 var bwidth = window.innerWidth;
	var bwidth1 = document.body.clientWidth;
	if(bwidth <= 768) {
		var w = bwidth1;
	 	var h =	500;
	} else {
		var w = 750;
	 	var h =	500;
	}
	 
	 var features =
		'width='          + w +
		',height='		  + h +
		',left='		  + ((width - w - 10) * .5)  +
		',top='			  + ((height - h - 30) * .5) +
		',directories=no' +
		',location=no'	  +
		',menubar=no'	  +
		',scrollbars=yes' +
		',status=no'	  +
		',toolbar=no'	  +
		',resizable=false';
	var strOpen = window.open (URL, windowName, features);
	strOpen.focus();
	return false;
}
</script>
</body>
</html>