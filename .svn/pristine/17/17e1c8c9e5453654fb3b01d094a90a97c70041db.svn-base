<%String cpath1 = request.getContextPath(); %>
<%@ page isELIgnored="false"%>
<!DOCTYPE HTML>
<html>
<head>
	<title>Madison General Insurance</title>
	<jsp:useBean id = "OCE" class = "com.maan.opencover.bean.opencoverEntry">
	<jsp:setProperty name = "OCE" property = "*"/>
	</jsp:useBean>

	<jsp:useBean id="newCover" class="com.maan.opencover.bean.newCoverBean">
		<jsp:setProperty name="newCover" property="*"/>
	</jsp:useBean>
	
	<link href="<%=cpath1%>/css/style.css" rel="stylesheet" type="text/css">
	<link href="<%=cpath1%>/css/footable-0.1.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js"></script>
	<script language="JavaScript">
		function stopRKey(evt) { 
		 	 var evt = (evt) ? evt : ((event) ? event : null); 
		  	 var node = (evt.target) ? evt.target : ((evt.srcElement) ? evt.srcElement : null); 
		 	 if ((evt.keyCode == 13) && ((node.type=="text") || (node.type=="password"))) {return false;}
		} 
		document.onkeypress = stopRKey; 
	</script>
	<style type="text/css">
	.inputBox {
		height: 20px;
		width: 95%;
	}
	</style>
</head>
<body>
<table width="90%"  border="0" cellspacing="0" cellpadding="0"  align="center">
<tr>
	<td colspan="2">
		<%@include file="menus.jsp"%>
	</td>
</tr>
<tr>
	<td>
<%
	boolean status = false;
	String brokerId=request.getParameter("brokerId")==null?"":request.getParameter("brokerId");
	String policyno=request.getParameter("policyno")==null?"":request.getParameter("policyno");
	String ActiveDeactive=request.getParameter("ActiveDeactive")==null?"":request.getParameter("ActiveDeactive");
	String proposalNo = request.getParameter("proposalNo")==null?"":request.getParameter("proposalNo");
	if(ActiveDeactive.length()>0) {
		status = OCE.openCoverActiveDeactive(proposalNo,ActiveDeactive);
		if(!status)
			out.println("<br align='center'><font color=red>* Error while updating the information </font>");
	}

	String deactive=request.getParameter("deactive")==null?"":request.getParameter("deactive");
	
	if(deactive.equalsIgnoreCase("Deactivate")) {
		String openNo = request.getParameter("openNo")==null?"":request.getParameter("openNo");
		OCE.openCoverDeactivate(openNo);
	}
	if(deactive.equalsIgnoreCase("Activate")) {
		String openNo = request.getParameter("openNo")==null?"":request.getParameter("openNo");
		OCE.openCoverActivate(openNo);
	}

	String loginId = (String)session.getAttribute("user");
	String branchCode=OCE.getBranchCode(loginId);
	String branchPrefix=OCE.getBranchPrefix(branchCode);
	String[][] information = OCE.getportfolioDetails(brokerId,policyno);
	String[][] lapsedInformation = new String[0][0];
	String[][] BrokerNames=newCover.getBrokersHasCover(actualBranch);
	lapsedInformation = OCE.getAdminLapsedPortfolioDetails(brokerId);

	int no_of_records=10;
	int lapsed_no_of_records=5;
	//**************total display pages
	int displaypages=3;
	int samplepages=displaypages;
	if(request.getParameter("displaypages")!=null&&!request.getParameter("displaypages").equalsIgnoreCase(""))
		displaypages=request.getParameter("displaypages")==null?3:Integer.parseInt(request.getParameter("displaypages"));
	int lapsed_displaypages=3;
	int lapsed_samplepages=displaypages;
	if(request.getParameter("lapsed_displaypages")!=null&&!request.getParameter("lapsed_displaypages").equalsIgnoreCase(""))
	lapsed_displaypages=request.getParameter("lapsed_displaypages")==null?3:Integer.parseInt(request.getParameter("lapsed_displaypages"));
	/********************pages************************/
%>
<form name="form1" method="post" action="newOpenCover.jsp">
<table width="100%">
	<% 
		int length124=0;
		if(lapsedInformation.length==0)
			length124=1;
		else
			length124=lapsedInformation.length;

		int lapsed_pages = length124/lapsed_no_of_records;
		int lapsed_rem = length124%lapsed_no_of_records;

		if(lapsed_rem != 0)
			lapsed_pages = lapsed_pages+1;

		int lapsed_display = 0;
		int lapsed_spage = 1;
		int  lapsed_start = 0;

		if(request.getParameter("lapsed_spage")!=null&&!request.getParameter("lapsed_spage").equalsIgnoreCase(""))
			lapsed_spage = request.getParameter("lapsed_spage")==null?1:Integer.parseInt(request.getParameter("lapsed_spage"));
		if(request.getParameter("lapsed_start")!=null&&!request.getParameter("lapsed_start").equalsIgnoreCase(""))
			lapsed_start = request.getParameter("lapsed_start")==null?0:Integer.parseInt(request.getParameter("lapsed_start"));
		lapsed_display = lapsed_no_of_records*lapsed_spage;
		if(lapsed_spage >= lapsed_displaypages) {
			if(lapsed_pages > lapsed_displaypages) {
				lapsed_start++;
				lapsed_displaypages++;
			}
		} else if((lapsed_displaypages-lapsed_spage)==(lapsed_samplepages-1)&& lapsed_start!=0) {
			lapsed_start--;
			lapsed_displaypages--;
		}
		if(lapsedInformation.length>0) {
	%>
	<tr>
		<td colspan="3"><span class="heading">Renewal Due OpenCovers</span></td>
	</tr>
	<tr>
		<td colspan="3">
			<table width="100%" class="footable">
				<thead>
				<tr>
					<th width="5%">S.No</th>
					<th width="13.57%">Core Application Policy No</th>
					<th width="10%">Proposal No</th>
					<th width="20%">Customer Name</th>
					<th width="10%">Policy Start Date</th>
					<th width="10%">Policy End Date</th>
					<th width="10%">Renew</th>
					<th width="10%">Delete</th>
				</tr>
				</thead>
				<tbody>
				<% 
				int lapsed_k = 0;
				int lapsed_skip = 0;
				int lapsed_count = 0;

				for(int i=0;i<lapsedInformation.length;i++) {
					lapsed_k++;
					if(lapsed_spage > 1) {
						 lapsed_skip = lapsed_spage-1;
			             if(lapsed_k <= (lapsed_skip*lapsed_no_of_records))
							continue;
					}
				%>
				<tr>
					<td><%=(i+1)%></td>
					<td><%=lapsedInformation[i][7]%></td>
					<td><%=lapsedInformation[i][0]%></td>
					<td><%=lapsedInformation[i][4]%></td>
					<td><%=lapsedInformation[i][2]%></td>
					<td><%=lapsedInformation[i][3]%></td>
					<%-- <td class="formtxtc"> <a href="#" class="two" title="Active" onclick = "ActiveDeActive('<%=lapsedInformation[i][0]%>','A','<%=(lapsedInformation[i][8]==null?"":lapsedInformation[i][8])%>')"> <b>Active</b> </a> </td>--%>
					<td> <a href="#" class="two" title="Renew" onclick = "Renew('<%=lapsedInformation[i][0]%>','<%=lapsedInformation[i][1]%>')"> <b>Renew</b> </a> </td>
					<td> <a href="#" class="two" title="Delete" onclick = "ActiveDeActive('<%=lapsedInformation[i][0]%>','D','')" ><b>Delete</b></a> </td>
				</tr>
				<%
				if(lapsed_k == lapsed_display)
					  break;
				}
				if(lapsedInformation.length==0) {
				%>
				<tr>
					<td  align="center"  colspan="8"><b style="color: red;">THERE IS NO LAPSED OPEN COVERS FOR THIS USER</b> </td>
				</tr>
				<%
				}
				if(length124>lapsed_no_of_records) {
				%>
				<tr bgcolor="#4f6180">
					<td align="right" colspan="8">
					<% 	if(lapsed_start>0)	{ %>
						<a href ="javaScript:lapsed_ExistingCustomers_B2B('<%=(lapsed_start+1)%>','<%=lapsed_displaypages%>','<%=lapsed_start%>')"><font color="white"><<</font></a>
					<% 	}
						boolean lapsed_flag=false;
						int lapsed_iValue=0;
						for(int i=lapsed_start;i<lapsed_pages;i++) {
							if(i<lapsed_displaypages) {
					%>
						<a href ="javaScript:lapsed_ExistingCustomers_B2B('<%=i+1%>','<%=lapsed_displaypages%>','<%=lapsed_start%>')"> <font color="white"><%=i+1%></font></a>
					<%		} else {
								lapsed_flag=true;
								lapsed_iValue=i;
								break;
							}
						}	 
						if(lapsed_flag) {
					%>					
						<a href ="javaScript:lapsed_ExistingCustomers_B2B('<%=lapsed_iValue%>','<%=lapsed_displaypages%>','<%=lapsed_start%>')"> <font color="white">>></font></a>
					<%	}
					} %>
				</td>
				</tr>
				<%
				} // Lapsed Open Cover If Length Ends here
				%>	
				</tbody>
			</table>
		</td>
	</tr>
	<%
/**pagenation**/ 
int length123=0;
if(information.length==0)
{
	length123=1;
}
else
{
	length123=information.length;
}
int pages=length123/no_of_records;
int rem=length123%no_of_records;
if(rem!=0)
{
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
if(spage>=displaypages)
{
	if(pages>displaypages)
	{
		start++;
		displaypages++;
	}
}
else if((displaypages-spage)==(samplepages-1)&&start!=0)
{
	start--;
	displaypages--;
}
/****************pagenaaaaaaaaaa************/
%>
	<tr>
		<td width="50%">
			<table width="100%">
				<tr>
					<td width="33%">Enter PolicyNo For Search</td>
					<td width="33%"><input type="text" class="inputBox" name="policyno" id="policyno" value="<%=policyno%>"/></td>
					<td width="33%"><input type='image' src="<%=path%>/images/Go.jpg" onclick="policysearch()"></td>
				</tr>
			</table>
		</td>
		<td width="50%">
			<table width="100%">
				<tr>
					<td width="50%" align="right">Select Broker:</td>
					<td width="50%">
						<select name="brokerId" id="brokerId" onchange="bidselection(this.value)" class="inputSelect">
							<option value="Select">Select</option>
							<%for(int i=0;i<BrokerNames.length;i++) { %>
							<option value="<%=BrokerNames[i][1]%>" <%=BrokerNames[i][1].equalsIgnoreCase(brokerId)?"selected":""%>><%=BrokerNames[i][0]%></option>
							<%	}	%>
						</select>
					</td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td colspan="2"><span class="heading">Existing OpenCovers</span></td>
	</tr>
	<tr>
		<td colspan="2">
			<table width="100%" class="footable">
				<thead>
				<tr>
					<th width="1%">S.No</th>
					<th width="15%">Core Application Policy No</th>
					<th width="10%">Proposal No</th>
					<th width="15%">Customer Name</th>
					<th width="10%">Balance Sum Insured</th>
					<th width="10%">Policy Start Date</th>
					<th width="10%">Policy End Date</th>
					<th width="10%">Endorse</th>
					<th width="5%">View</th>
					<th width="10%">Schedule</th>
					<th width="10%">Policy Wordings</th>
					<th width="10%">Documents</th>
					<th width="10%">Endt Schedule</th>
					<th width="5%">DeActivate</th>
				</tr>
				</thead>
				<tbody>
				<%
					int k=0;
					int skip=0;
					int count=0;
					for(int i=0;i<information.length;i++) {
						k++;
						if(spage>1) {
							skip=spage-1;
							if(k<=(skip*no_of_records))
								continue;
						}
						String bgColor="Y".equalsIgnoreCase(information[i][8])?"style='background-color: silver;'":"";
				%>
				<tr>
					<td><%=(i+1)%></td>
					<!--   <td align = "center" height="23"><%=information[i][1]%></td>-->
					<td><%=information[i][7]%></td>
					<td><%=information[i][0]%></td>
					<td><%=information[i][4]%></td>
					<td><%=information[i][13]%></td>
					<td><%=information[i][2]%></td>
					<td><%=information[i][3]%></td>
					<td>
						<%if("Y".equalsIgnoreCase(information[i][8])){%>
							<a href="#"  title="Edit Policy" class="two" onClick="return viewPolicys('<%=information[i][0]%>','<%=information[i][1]%>')"> <b> Edit </b></a>
						<%}else if(!"N".equalsIgnoreCase(information[i][9])){ %>
							<a href="#"  title="Endorse Policy" class="two" onclick = "Endorsement('<%=information[i][0]%>','<%=information[i][1]%>')"> <b> Endorse </b></a>
						<%} %>
					</td>
					<td><a href="#" title="Print Policy" class="two" onclick = "printQuote('<%=information[i][0]%>')" > <b> View </b></a> </td>
					<td>
						<%if(!"Y".equalsIgnoreCase(information[i][8])){%>
							<a href="#" title="View Schedule" class="two" onclick="return viewDoc('<%=information[i][1]%>','<%=(String)session.getAttribute("user")%>','schedule','<%=information[i][1]%>','','<%=information[i][0]%>','false');" > <b> Schedule </b></a>
						<%}else{%>
							N/A
						<%} %>
					</td>
					<td align="center">
						<%if(!"Y".equalsIgnoreCase(information[i][8])){%>
							<a href="#" title="Policy Wordings" class="two" onclick="return viewDoc('<%=information[i][1]%>','<%=(String)session.getAttribute("user")%>','clauses','<%=information[i][1]%>','','<%=information[i][0]%>','false');" > <b > View </b></a>
						<%}else{%>
							N/A
						<%} %>
					</td>
					<td align="center">
		                <%if(information[i][11]!=null && !"".equals(information[i][11])){%>
							<a href="#" title="<%=information[i][11]%>" class="two" onclick="return viewDoc('<%=information[i][1]%>','<%=(String)session.getAttribute("user")%>','debit','<%=information[i][11]%>','');" > <b>Debit</b></a>&nbsp;/&nbsp;
						<%}if(information[i][12]!=null && !"".equals(information[i][12])){%>
							<a href="#" title="<%=information[i][12]%>" class="two" onclick="return viewDoc('<%=information[i][1]%>','<%=(String)session.getAttribute("user")%>','credit','<%=information[i][12]%>','');" > <b>Credit</b></a>&nbsp;/&nbsp;
						<%} if(!"Y".equalsIgnoreCase(information[i][8])) { %>
							<a href="#" title="Policy Document" class="two" onclick="return addDoc('<%=org.apache.commons.lang3.StringUtils.isBlank(information[i][10])?information[i][1]:information[i][10]%>')"><b>Documents</b></a>
						<%} else { %>
							N/A
						<%} %>
	                </td>
					<td>
						<%if("N".equalsIgnoreCase(information[i][9])){ %>
						<a href="#" title="View Schedule" class="two" onclick="return viewDoc('<%=information[i][1]%>','<%=(String)session.getAttribute("user")%>','schedule','<%=information[i][1]%>','','<%=information[i][0]%>','true');" > <b> Schedule </b></a>
						<%}else{%>
						N/A
						<%} %>
					</td>            
					<td>
						<% if(!"N".equalsIgnoreCase(information[i][9])){ %>
							<a href="#" title="DeActive" class="two" onclick = "deActive('<%=information[i][0]%>','N')" > <b> Delete </b></a>
						<%}%> 
					</td>
				</tr>
				<%	if(k==display)
						break;
				}%>
				<tr bgcolor="#4f6180" >
					<td height="12" align="right" colspan="14">
						<%
							if(length123>no_of_records) {
								if(start>0) {
						%>
							<a href ="javaScript:ExistingCustomers_B2B('<%=(start+1)%>','<%=displaypages%>','<%=start%>')" style="color: #ffffff;" ><font ><<</font>&nbsp;&nbsp;</a>
						<%		}
					 			boolean flag=false;
								int iValue=0;
								for(int i=start;i<pages;i++) {
									if(i<displaypages) {
						%>
							<a href ="javaScript:ExistingCustomers_B2B('<%=i+1%>','<%=displaypages%>','<%=start%>')" style="color: #ffffff;"> <font ><%=i+1%></font></a>
							<%		} else {
										flag=true;
										iValue=i;
										break;
									}
								}	 
								if(flag) { %>
							<a href ="javaScript:ExistingCustomers_B2B('<%=iValue%>','<%=displaypages%>','<%=start%>')" style="color: #ffffff;">&nbsp;&nbsp;<font >>></font></a>
						<% 		}
							} %>
				</td>
				</tr>
				</tbody>
			</table>	
		</td>
	</tr>
</table>
<input type = "hidden" name = "proposalno"/>
<input type = "hidden" name = "coverNo"/>
<input type="hidden" name="spage">
<input type="hidden" name="displaypages">
<input type="hidden" name="start">
<input type="hidden" name="lapsed_spage">
<input type="hidden" name="lapsed_displaypages">
<input type="hidden" name="lapsed_start">
<input type="hidden" name="ActiveDeactive">
<input type="hidden" name="proposalNo">
<input type="hidden" name="requestfrom" value="showApprovedCover">
<!--Royal New on 18/05 -->
<input type="hidden" name="deactive">
<input type="hidden" name="openNo">
<input type="hidden" name="endtYN" value="Y">
<!--Royal New on 18/05 -->
</form>
	</td>
</tr>
</table>
</body>
<script type="text/javascript">

function viewPolicys(s,s1)
{
	  document.form1.proposalno.value = s;
	  //document.form1.coverNo.value = s1;
	  document.form1.action = "newOpenCover.jsp";
	  document.form1.submit();
}

function viewPolicy()
{
}

function bidselection(val)
{
    document.getElementById("policyno").value='';
	document.form1.brokerId.value=val;
	document.form1.action = "showApprovedCover.jsp";
	document.form1.submit();
}

function policysearch()
{
    var policyNo=document.getElementById("policyno").value;
    var number=policyNo.substring(0,1);
    if(number!=null && number!='')
    {
    document.form1.policyno.value=document.getElementById("policyno").value;
    document.form1.brokerId.value=document.getElementById("brokerId").value;
	document.form1.action = "showApprovedCover.jsp";
	document.form1.submit();
	}
	else
	{
	alert('Enter Valid PolicyNo');
	document.getElementById("policyno").value='';
	//document.getElementById("brokerId").value='';
	document.form1.action = "showApprovedCover.jsp";
	document.form1.submit();
	}
}

function ExistingCustomers_B2B(value123,displaypages,start)
{
	document.form1.spage.value=value123;
	document.form1.start.value=start;
	//document.form1.identifyCus.value=identifyCus;
	document.form1.displaypages.value=displaypages;
	document.form1.action="showApprovedCover.jsp"
	document.form1.submit();
}

function openLCDetails(opencover,lcNos)
{
	//document.form1.action="lcDetailsEntryAdmin.jsp?opencover="+opencover+"&lcNos="+lcNos+"&mode=add";
	document.form1.action="<%=path%>/premiumOpenCover/OpenCoverView.jsp?opencover="+opencover;
	document.form1.submit();
}

function openLCDetails123(opencover,lcNos)
{
	var URL = "lcDetailsEntryAdmin.jsp?opencover="+opencover+"&lcNos="+lcNos+"&mode=add";

	var windowName = "DetailsView";
	var width  = screen.availWidth;
	var height = screen.availHeight;
	var w = 620;
	var h = 300;
	var features =
		'width='          + w +
		',height='		  + h +
		',left='		  + ((0) * .5)  +
		',top='			  + ((0) * .5) +
		',directories=no' +
		',location=no'	  +
		',menubar=no'	  +
		',scrollbars=yes' +
		',status=no'	  +
		',toolbar=no'	  +
		',resizable=no';
	var strOpen = window.open (URL, windowName, features);
	strOpen.focus();
}

function lapsed_ExistingCustomers_B2B(value123,displaypages,start)
{
	document.form1.lapsed_spage.value=value123;
	document.form1.lapsed_start.value=start;
	document.form1.lapsed_displaypages.value=displaypages;
	document.form1.action="showApprovedCover.jsp"
	document.form1.submit();
}

function ActiveDeActive(proposalNo,ad,adminStatus)
{
	var flag = true;
	document.form1.proposalNo.value = proposalNo;
	if(adminStatus!='N')
	{
		if(ad == 'A')
			document.form1.ActiveDeactive.value='Active';
		else
		{
			flag = confirm("Are you sure want to delete permentely?","Yes","No");
			if(flag)
			{
				document.form1.ActiveDeactive.value='DeActive';
			}
		}
	}
	else if(adminStatus=='N')
	{
		document.form1.deactive.value='Activate';
		document.form1.openNo.value = proposalNo;
	}
	if(flag)
	{
		document.form1.action="showApprovedCover.jsp";
		document.form1.submit();
	}
}

function deActive(openNos)
{
    var flag=confirm("Are you sure want to delete ?","Yes","No");
    if(flag){
		document.form1.openNo.value = openNos;
		document.form1.deactive.value='Deactivate';
		document.form1.action="showApprovedCover.jsp";
		document.form1.submit();
	}else{
	   return false;
	}
}

// Print certificate Summary screen
function printQuote(proposalNo)
{
	var URL = '';
	URL='PrintOpencoverSummary.jsp?portfolio=portfolio&proposalNo='+proposalNo;
	var windowName = "PrintOpencoverSummary";
	var width  =	 screen.availWidth;
	var height = screen.availHeight;
	var w = 900;
	var h =	400;
	var features =
	'width='          + w +
	',height='		  + h +
	',left='		  + ((width - w) * .5)  +
	',top='			  + ((height - h ) * .5) +
	',directories=no' +
	',location=no'	  +
	',menubar=no'	  +
	',scrollbars=yes' +
	',status=no'	  +
	',toolbar=no'	  +
	',resizable=false';
	//alert(URL);
	var strOpen = window.open (URL, windowName, features);
	strOpen.focus();
	strOpen.focus();
	return false;
}
function viewDoc(policynumber,loginId,docType,docNo,amendId,proposalNo,endtstatus)
{
	     var URL ="<%=request.getContextPath()%>/GenerateDocument.jspa?docType="+docType+"&policynumber="+policynumber+"&loginId="+loginId+"&docNo="+docNo+"&amendId="+amendId+"&proposalNo="+proposalNo+"&endtstatus="+endtstatus;
		 var windowName = "PolicyView";
		 var width  = screen.availWidth;
		 var height = screen.availHeight;
		 var w = 900;
		 var h =	500;
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
function Renew(proposalNo, openNos)
{
	document.form1.openNo.value = openNos;
	document.form1.proposalno.value = proposalNo;
	document.form1.action="Renewal.jspa";
	document.form1.submit();
}
function Endorsement(proposalNo, openNos)
{
	document.form1.openNo.value = openNos;
	document.form1.proposalno.value = proposalNo;
	document.form1.action="Endorsement.jspa";
	document.form1.submit();
}
function addDoc(openCoverNo) {
	var URL ='${pageContext.request.contextPath}/uploadDocument?policyNo=' + openCoverNo;
	return popUp(URL,'1000','650');
}
</script>
</html>