
<%@page import="com.sun.tools.javac.tree.Tree.ForeachLoop"%><%@ include file="login/sessionsCheckNormal.jsp"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<jsp:useBean id="pdf" class="rsa.pdf.PDFDisplay">
	<jsp:setProperty name="pdf" property="*" />
</jsp:useBean>
<jsp:useBean id="pol" class="com.maan.services.policyInfo">
	<jsp:setProperty name="pol" property="*" />
</jsp:useBean>
<jsp:useBean id="bean" class="rsa.opencoverpdf.finalprint">
	<jsp:setProperty name="bean" property="*" />
</jsp:useBean>
<%
	String path3 = request.getContextPath();
	String usrModeSCPOLICYTT = (String) session.getAttribute("userLoginMode") == null ? "": (String) session.getAttribute("userLoginMode");
	com.maan.DBCon.DBConnectionStatus.statusStatic = usrModeSCPOLICYTT;

	boolean senFlag = false;
	// Marine Download File
	String downloadInfo[][] = new String[0][0];
	String downloadRemarks = "";
	String custFileName = "";
	String fileType = "";
	// Marine Download File

	String policynumber = "";
	String openCoverNoSettingCert = "";
	String loginid = "";
	String policyMode = "";
	String disPlayText = "";
	String displayMode = "";
	String pdfStatus = "DATAS";
	String productTypeIds = "0";
	String[][] preBankOptions = new String[0][0];
	String[][] noteNo = new String[0][0];
	String preOpted = "";
	String bankOpted = "";
	String bankAssuredOpted = "";
	String currencyOpted = "", creditNote="", debitNote="";
	String verNo = "";

	policynumber = request.getParameter("policynumber") == null ? policynumber: request.getParameter("policynumber");
	productTypeIds = request.getParameter("productTypeIds") == null ? productTypeIds: request.getParameter("productTypeIds");
	noteNo=pol.getNoteNo(policynumber,productTypeIds);
	if(noteNo!=null && noteNo.length>0) {
		debitNote=noteNo[0][0];
		creditNote=noteNo[0][1];
	}
	verNo = request.getParameter("verNo") == null ? "0" : request.getParameter("verNo");
	openCoverNoSettingCert = request.getParameter("openCoverNoSettingCert") == null ? openCoverNoSettingCert: request.getParameter("openCoverNoSettingCert");

	pdfStatus = request.getParameter("pdfStatus") == null ? pdfStatus: request.getParameter("pdfStatus");

	disPlayText = request.getParameter("disPlayText") == null ? disPlayText: request.getParameter("disPlayText");

	loginid = request.getParameter("loginid") == null ? loginid: request.getParameter("loginid");

	if ("Y".equalsIgnoreCase(request.getParameter("authorized"))) 
		pdf.getUpdatePDFStatus(policynumber, loginid, "0",productTypeIds); 
		policyMode = request.getParameter("policyMode") == null ? policyMode: request.getParameter("policyMode");

	if ("scheduleMultiple".equalsIgnoreCase(policyMode)	|| "debitMultiple".equalsIgnoreCase(policyMode)) {
		displayMode = "NormalMultiple";
		//Checked Okay...
		preBankOptions = pdf.getPreBankOptions(policynumber, loginid, "policy");

	} else if ("suppleMentMultiple".equalsIgnoreCase(policyMode)|| "suppleMentDebitMultiple".equalsIgnoreCase(policyMode)) {
		displayMode = "NormalSupplement";
		preBankOptions = pdf.getPreBankOptions(policynumber, loginid,"MultiQuotes");
	} else if ("schedule".equalsIgnoreCase(policyMode)) {
		displayMode = "Normal";
		preBankOptions = pdf.getPreBankOptions(policynumber, loginid,"policy");
	} else if ("draft".equalsIgnoreCase(policyMode)) {
		displayMode = "draftMode";
		preBankOptions = pdf.getPreBankOptions(policynumber, loginid,"draft");
	} else if ("certificate".equalsIgnoreCase(policyMode)) {
		displayMode = "certificateMode";
		preBankOptions = pdf.getPreBankOptions(policynumber, loginid,"certificate");
	} else if ("draftModeMultiple".equalsIgnoreCase(policyMode)) {
		displayMode = "draftModeMultiple";
		preBankOptions = pdf.getPreBankOptions(policynumber, loginid,"MultiQuotesDisplay");
	}
	if(preBankOptions.length > 0) {
		preOpted = preBankOptions[0][0] == null ? "": preBankOptions[0][0];
		bankOpted = preBankOptions[0][1] == null ? "": preBankOptions[0][1];
		bankAssuredOpted = preBankOptions[0][6] == null ? "": preBankOptions[0][6];
		currencyOpted = preBankOptions[0][7] == null ? "": preBankOptions[0][7];
	} else { }	
%>
<html>
<head>
	<meta http-equiv="X-UA-Compatible" content="IE=edge">		
	<script type="text/javascript" src="js/common.js"></script>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/dataTables/css/dataTables.responsive.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/dataTables/css/dataTables.jqueryui.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/dataTables/css/dataTables.tableTools.css">
	<link href="<%=request.getContextPath()%>/dataTables/css/jquery-start-ui.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="<%=request.getContextPath()%>/dataTables/js/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/dataTables/js/jquery.dataTables.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/dataTables/js/dataTables.jqueryui.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/dataTables/js/dataTables.responsive.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/dataTables/js/dataTables.tableTools.js"></script>
	<script type="text/javascript">
  	jQuery(function ($) {
  		try {
			var data1 = $('#record').dataTable( {
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
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/bootstrap/css/bootstrap.min.css" >
	<link href="<%=request.getContextPath()%>/bootstrap/css/footable-0.1.css" rel="stylesheet" type="text/css" >
	<link href="<%=request.getContextPath()%>/bootstrap/css/style.css" rel="stylesheet" type="text/css" >
	<style type="text/css">
	label {
		margin-left: 10px;
	}
	</style>	
	<script type="text/javascript">		
	function stopRKey(evt) { 
	  var evt = (evt) ? evt : ((event) ? event : null); 
	  var node = (evt.target) ? evt.target : ((evt.srcElement) ? evt.srcElement : null); 
	  if ((evt.keyCode == 13) && (node.type=="text"))  {return false;} 
	} 
	document.onkeypress = stopRKey;
  		</script>
</head>
<body>
<form name="form1" method="post">
<div class="row">
	<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
		<% if ("Y".equalsIgnoreCase(request.getParameter("authorized"))) { %>
		<div class="panel panel-primary">			
			<div class="panel-body" align="center">
				<div class="label label-warning">Now Broker can take One more Original and Original copy</div>
			</div>
		</div>
		<% } else { %>
		<div class="panel panel-primary">
			<div class="panel-heading">
				<s:text name="PRINTING OPTIONS" />
			</div>			
			<div class="panel-body">
				<div class="row">
					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" align="center">
						<% 	if (("draftMode".equalsIgnoreCase(displayMode) || "draftModeMultiple".equalsIgnoreCase(displayMode)) && "DATAS".equalsIgnoreCase(pdfStatus)) { %>
						<%		if ("Test".equalsIgnoreCase(usrModeSCPOLICYTT)) { %>
									<a href="#" type="button" class="btn btn-sm btn-info" onClick="return viewPdfCopy('<%=policynumber%>','<%=loginid%>','INVALID DRAFT','<%=preOpted%>','<%=bankOpted%>','<%=displayMode%>','<%=bankAssuredOpted%>','<%=currencyOpted%>','<%=productTypeIds%>','<%=openCoverNoSettingCert%>','<%=verNo%>')"> VIEW DRAFT </a>
						<%		} else if ("Live".equalsIgnoreCase(usrModeSCPOLICYTT)) { %>
									<a href="#" type="button" class="btn btn-sm btn-info" onClick="return viewPdfCopy('<%=policynumber%>','<%=loginid%>','DRAFT','<%=preOpted%>','<%=bankOpted%>','<%=displayMode%>','<%=bankAssuredOpted%>','<%=currencyOpted%>','<%=productTypeIds%>','<%=openCoverNoSettingCert%>','<%=verNo%>')">	VIEW DRAFT </a>
						<% 		}
							} else if("certificateMode".equalsIgnoreCase(displayMode)) {
								if ("Test".equalsIgnoreCase(usrModeSCPOLICYTT)) { %>
									<a href="#" type="button" class="btn btn-sm btn-info" 	onClick="return viewPdfCopy('<%=policynumber%>','<%=loginid%>','INVALID CERTIFICATE','<%=preOpted%>','<%=bankOpted%>','<%=displayMode%>','<%=bankAssuredOpted%>','<%=currencyOpted%>','<%=productTypeIds%>','<%=openCoverNoSettingCert%>','<%=verNo%>')"> VIEW&nbsp;CERTIFICATE </a>
						<%		} else if ("Live".equalsIgnoreCase(usrModeSCPOLICYTT)) { %>
									<a href="#" type="button" class="btn btn-sm btn-info"	onClick="return viewPdfCopy('<%=policynumber%>','<%=loginid%>','CERTIFICATE','<%=preOpted%>','<%=bankOpted%>','<%=displayMode%>','<%=bankAssuredOpted%>','<%=currencyOpted%>','<%=productTypeIds%>','<%=openCoverNoSettingCert%>','<%=verNo%>')"> VIEW CERTIFICATE </a>
						<%		}
							} else if (("Normal".equalsIgnoreCase(displayMode) || "NormalMultiple".equalsIgnoreCase(displayMode) || "NormalSupplement".equalsIgnoreCase(displayMode))	&& "DATAS".equalsIgnoreCase(pdfStatus)) {
								if ("Test".equalsIgnoreCase(usrModeSCPOLICYTT)) { 
									if(debitNote!=null && debitNote.length()>0) { %>
										<a href="#" type="button" class="btn btn-sm btn-info" onClick="return viewPdfCopy('<%=policynumber%>','<%=loginid%>','INVALID DEBIT','<%=preOpted%>','<%=bankOpted%>','<%=displayMode%>','<%=bankAssuredOpted%>','<%=currencyOpted%>','<%=productTypeIds%>','<%=openCoverNoSettingCert%>','<%=verNo%>')">DEBIT NOTE </a>
						<%			}	if(creditNote!=null && creditNote.length()>0) { %>
											<a href="#" type="button" class="btn btn-sm btn-info" onClick="return viewPdfCopy('<%=policynumber%>','<%=loginid%>','INVALID CREDIT','<%=preOpted%>','<%=bankOpted%>','<%=displayMode%>','<%=bankAssuredOpted%>','<%=currencyOpted%>','<%=productTypeIds%>','<%=openCoverNoSettingCert%>','<%=verNo%>')">CREDIT NOTE </a>
						<%				}
								} else if ("Live".equalsIgnoreCase(usrModeSCPOLICYTT)) {
									if(debitNote!=null && debitNote.length()>0) { %>
										<a href="#" type="button" class="btn btn-sm btn-info" onClick="return viewPdfCopy('<%=policynumber%>','<%=loginid%>','DEBIT','<%=preOpted%>','<%=bankOpted%>','<%=displayMode%>','<%=bankAssuredOpted%>','<%=currencyOpted%>','<%=productTypeIds%>','<%=openCoverNoSettingCert%>','<%=verNo%>')"> DEBIT NOTE </a>
						<%			} 	if(creditNote!=null && creditNote.length()>0) { %>
										<a href="#" type="button" class="btn btn-sm btn-info" onClick="return viewPdfCopy('<%=policynumber%>','<%=loginid%>','CREDIT','<%=preOpted%>','<%=bankOpted%>','<%=displayMode%>','<%=bankAssuredOpted%>','<%=currencyOpted%>','<%=productTypeIds%>','<%=openCoverNoSettingCert%>','<%=verNo%>')"> CREDIT NOTE </a>
						<%				}
									}	if ("Test".equalsIgnoreCase(usrModeSCPOLICYTT)) { %>
											<a href="#" type="button" class="btn btn-sm btn-info" onClick="return viewPdfCopy('<%=policynumber%>','<%=loginid%>','INVALID POLICY','<%=preOpted%>','<%=bankOpted%>','<%=displayMode%>','<%=bankAssuredOpted%>','<%=currencyOpted%>','<%=productTypeIds%>','<%=openCoverNoSettingCert%>','<%=verNo%>')"> ORIGINAL </a>
						<%				} 	else if ("Live".equalsIgnoreCase(usrModeSCPOLICYTT)) { %>
											<a href="#" type="button" class="btn btn-sm btn-info" onClick="return viewPdfCopy('<%=policynumber%>','<%=loginid%>','','<%=preOpted%>','<%=bankOpted%>','<%=displayMode%>','<%=bankAssuredOpted%>','<%=currencyOpted%>','<%=productTypeIds%>','<%=openCoverNoSettingCert%>','<%=verNo%>')"> ORIGINAL </a>
						<% 				}	if ("Test".equalsIgnoreCase(usrModeSCPOLICYTT)) { %>
											<a href="#" type="button" class="btn btn-sm btn-info" onClick="return viewPdfCopy('<%=policynumber%>','<%=loginid%>','INVALID POLICY','<%=preOpted%>','<%=bankOpted%>','<%=displayMode%>','<%=bankAssuredOpted%>','<%=currencyOpted%>','<%=productTypeIds%>','<%=openCoverNoSettingCert%>','<%=verNo%>')">ORIGINAL COPY</a>
						<%					} else if ("Live".equalsIgnoreCase(usrModeSCPOLICYTT)) { %>
												<a href="#" type="button" class="btn btn-sm btn-info" onClick="return viewPdfCopy('<%=policynumber%>','<%=loginid%>','ORIGINAL COPY','<%=preOpted%>','<%=bankOpted%>','<%=displayMode%>','<%=bankAssuredOpted%>','<%=currencyOpted%>','<%=productTypeIds%>','<%=openCoverNoSettingCert%>','<%=verNo%>')">ORIGINAL COPY</a>
						<%					} if ("Test".equalsIgnoreCase(usrModeSCPOLICYTT)) { %>
												<a href="#" type="button" class="btn btn-sm btn-info" onClick="return viewPdfCopy('<%=policynumber%>','<%=loginid%>','INVALID POLICY','<%=preOpted%>','<%=bankOpted%>','<%=displayMode%>','<%=bankAssuredOpted%>','<%=currencyOpted%>','<%=productTypeIds%>','<%=openCoverNoSettingCert%>','<%=verNo%>')"> COPY</a>
						<%					} else if ("Live".equalsIgnoreCase(usrModeSCPOLICYTT)) { %>
												<a href="#" type="button" class="btn btn-sm btn-info" onClick="return viewPdfCopy('<%=policynumber%>','<%=loginid%>','COPY','<%=preOpted%>','<%=bankOpted%>','<%=displayMode%>','<%=bankAssuredOpted%>','<%=currencyOpted%>','<%=productTypeIds%>','<%=openCoverNoSettingCert%>','<%=verNo%>')"> COPY </a>
						<%--					}
											String[][] res = bean.getCustomerDebitDetails(policynumber,"");
			    							if(res != null && res.length > 0 && senFlag) { 
			    								<a type="button" class="btn btn-sm btn-info" href ="<%=request.getContextPath()%>/PDFCreator.customerDebit?requestFrom=FromAdmin&policyNumber=<%=policynumber%>" >Customer Debit</a>
			    		--%>
			    		<%  				} if ("Test".equalsIgnoreCase(usrModeSCPOLICYTT)) {	%>
			    								<a href="#" type="button" class="btn btn-sm btn-danger" onClick="return viewPdfCopy('<%=policynumber%>','<%=loginid%>','DELETE','<%=preOpted%>','<%=bankOpted%>','<%=displayMode%>','<%=bankAssuredOpted%>','<%=currencyOpted%>','<%=productTypeIds%>','<%=openCoverNoSettingCert%>','<%=verNo%>')"> DELETE</a>
			    		<%					} else if ("Live".equalsIgnoreCase(usrModeSCPOLICYTT)) { %>
			    								<a href="#" type="button" class="btn btn-sm btn-danger" onClick="return viewPdfCopy('<%=policynumber%>','<%=loginid%>','DELETE','<%=preOpted%>','<%=bankOpted%>','<%=displayMode%>','<%=bankAssuredOpted%>','<%=currencyOpted%>','<%=productTypeIds%>','<%=openCoverNoSettingCert%>','<%=verNo%>')">DELETE</a>
			    		<% 					} %>
											&nbsp;&nbsp;&nbsp;<a href="#" type="button" class="btn btn-sm btn-primary" onClick="return viewPolicyss()"><b>Authorization</b></a>
											&nbsp;&nbsp;&nbsp;<a href="#" type="button" class="btn btn-sm btn-default" onClick="return viewDocuments('<%=policynumber%>')"><b>DOCUMENTS</b></a>
						<%				} else if ("NODATAS".equalsIgnoreCase(pdfStatus)) { %>
											<span class="label label-danger">SORRY ! SOME INFORMATIONS	ARE MISSING</span>
						<%				}
									} 
						%>		
					</div>
					
					<%
						if("65".equalsIgnoreCase(productTypeIds)){
							String branch = "";
							branch = (String) session.getAttribute("LoginBranchCode"); 
							String[][] multiVehicleDetails = bean.getMultiVehicleDetails(policynumber,productTypeIds,branch);
							if(multiVehicleDetails!=null && multiVehicleDetails.length>0){
					%>
							<div class="row" style="margin-top:50px;">
								<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" align="center">
									<table class="display responsive no-wrap footable" id="gridTable" width="100%" cellspacing="0">
										<thead>
											<tr>
												<th><s:text name="Make" /></th>
												<th><s:text name="Model" /></th>
												<th><s:text name="Type of Body" /></th>
												<th><s:text name="Engine Number" /></th>
												<th><s:text name="Chasis Number" /></th>
												<th><s:text name="Registration Number" /></th>
												<th><s:text name="Certificate" /></th>
											</tr>
										</thead>
										<tbody>
											<%if(multiVehicleDetails.length > 0) {
												for(int i=0;i<multiVehicleDetails.length;i++){
													
												
											%>
												<tr>
													<td><%= multiVehicleDetails[i][0]%></td>
													<td><%= multiVehicleDetails[i][1]%></td>
													<td><%= multiVehicleDetails[i][2]%></td>
													<td><%= multiVehicleDetails[i][3]%></td>
													<td><%= multiVehicleDetails[i][4]%></td>
													<td><%= multiVehicleDetails[i][5]%></td>
													<td align="center">
															<a href="#" class="btn btn-sm btn-primary" onclick="return getFleetPdf('<%= multiVehicleDetails[i][9]%>','<%= multiVehicleDetails[i][8]%>')"><i class="glyphicon glyphicon-book"></i></a>
													</td>
												</tr>
											<%	}
											} %>
											</tbody>
									</table>
								</div>
							</div>
					<%} 
					}%>
				</div>
			</div>
		</div>
	</div>
</div>
<%
	String branch = "";
	branch = (String) session.getAttribute("LoginBranchCode"); 
	branch = branch == null ? "" : branch;
	//downloadInfo = pol.getInfoForFileDownload(policynumber,productTypeIds,branch);
	if (downloadInfo.length > 0) {
%>
<br class="clear" />
<div class="row">
	<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
		<div class="panel panel-primary">
			<div class="panel-heading"> <s:text name="Download Information" /> </div>
			<div class="panel-body">
				<table class="display responsive no-wrap" id="record" width="100%" cellspacing="0">
					<thead>
					<tr>
						<th class="no-sort"></th>
						<th><s:text name="S.No." /></th>
						<th><s:text name="File Type" /></th>
						<th><s:text name="Customer File Name" /></th>
						<th><s:text name="Description" /></th>
						<th><s:text name="Download" /></th>
					</tr>
					</thead>
					<tbody>
					<%	for (int i = 0; i < downloadInfo.length; i++) {
							custFileName = downloadInfo[i][1] == null ? "": downloadInfo[i][1];
							downloadRemarks = downloadInfo[i][3] == null ? "": downloadInfo[i][3];
							fileType = downloadInfo[i][5]==null ? "" :downloadInfo[i][5]; %>
						<tr>
							<td></td>
							<td align="center"> <%=(i + 1)%> </td>
							<td> <%=fileType%> </td>
							<td> <%=custFileName%> </td>
							<td> <textarea name="downloadRemarks" rows="2" cols="40" readonly class="inputBoxA" ><%=downloadRemarks%></textarea> </td>
							<td align="center">
								<a href="#" type="button" class="btn btn-sm btn-default" onclick="return download('<%=downloadInfo[i][1]%>','<%=downloadInfo[i][2]%>')"> <i class="fa fa-download"></i> </a>
							</td>
						</tr>
						<% } // For Loop %>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>
<% } // If DownLoad Information %>
<br class="clear" />
<div class="row">
	<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" align="center">
		<a href="javaScript:onclick=window.close()" class="btn btn-sm btn-danger"> Cancel </a>
	</div>
</div>
<input type="hidden" name="policynumber" value="<%=policynumber%>">
<input type="hidden" name="loginid" value="<%=loginid%>">
<input type="hidden" name="displayText"> 
<input type="hidden" name="displayPrintOption"> 
<input type="hidden" name="displayMode"> 
<input type="hidden" name="bankerOption">
<input type="hidden" name="bankerAssuredOption"> 
<input type="hidden" name="currencyOption"> 
<input type="hidden" name="printoption"> 
<input type="hidden" name="authorized">
<input type="hidden" name="custFileName">
<input type="hidden" name="systemFileName">
<input type="hidden" name="policyMode" value="<%=policyMode%>">
<input type="hidden" name="policyNo" id="policyNo">
</form>
</body>
<script type="text/javascript">
function viewPdfCopy(policynumber,loginid1,displayTextVal,preOptedVal,bankOptedVal,displayModeVal,bankAssuredOptedVal,currencyOptedVal,productCheckId,openNo,verNo)
{
			document.form1.policynumber.value=policynumber;
			document.form1.loginid.value=loginid1;
			document.form1.displayText.value=displayTextVal;
			document.form1.displayMode.value=displayModeVal;
			document.form1.bankerOption.value=bankOptedVal;
			document.form1.printoption.value=preOptedVal;
			document.form1.bankerAssuredOption.value=bankAssuredOptedVal;
			var policyModee = displayTextVal;
			//alert(displayModeVal);
			if(displayModeVal=='NormalSupplement'&&policyModee=='DEBIT')
				policyModee = "suppleMentDebitMultiple";
			else if(displayModeVal=='NormalMultiple'&&policyModee=='DEBIT')
				policyModee = "debitMultiple";

			document.form1.currencyOption.value=currencyOptedVal;

			if(displayTextVal == 'DRAFT'||displayTextVal == 'INVALID DRAFT')
			{
				if(productCheckId=='11')
				{
					document.form1.action="print4ScheduleOpen.OpenCertificate?policynumber="+policynumber+"&loginid="+loginid1+"&displayText="+displayTextVal+"&displayMode="+displayModeVal+"&printoption="+preOptedVal+"&bankerOption="+bankOptedVal+"&bankerAssuredOption="+bankAssuredOptedVal+"&productTypeIdCert="+productCheckId+"&openCoverNoSettingCert="+openNo+"&currencyOption="+currencyOptedVal+"";
				}
				else if(productCheckId=='3')
				{
					document.form1.action="print4Schedule.pdfSchedule?policynumber="+policynumber+"&loginid="+loginid1+"&displayText="+displayTextVal+"&displayMode="+displayModeVal+"&printoption="+preOptedVal+"&bankerOption="+bankOptedVal+"&bankerAssuredOption="+bankAssuredOptedVal+"&productTypeIdCert="+productCheckId+"&openCoverNoSettingCert="+openNo+"&currencyOption="+currencyOptedVal+"";
				}
				else{
						document.form1.action="print4Schedule.pdfSchedule?policynumber="+policynumber+"&loginid="+loginid1+"&displayText="+displayTextVal+"&displayMode="+displayModeVal+"&printoption="+preOptedVal+"&bankerOption="+bankOptedVal+"&bankerAssuredOption="+bankAssuredOptedVal+"&productTypeIdCert="+productCheckId+"&openCoverNoSettingCert="+openNo+"&currencyOption="+currencyOptedVal+"";
					}
			}
			else if(displayTextVal == 'CERTIFICATE'||displayTextVal == 'INVALID CERTIFICATE')
			{
				if(productCheckId=='11')
				{
					document.form1.action="print4ScheduleOpen.OpenCertificate?policynumber="+policynumber+"&loginid="+loginid1+"&displayText="+displayTextVal+"&displayMode="+displayModeVal+"&printoption="+preOptedVal+"&bankerOption="+bankOptedVal+"&bankerAssuredOption="+bankAssuredOptedVal+"&productTypeIdCert="+productCheckId+"&openCoverNoSettingCert="+openNo+"&currencyOption="+currencyOptedVal+"";
				}
				else if(productCheckId=='3')
				{
					document.form1.action="print4Schedule.pdfSchedule?policynumber="+policynumber+"&loginid="+loginid1+"&displayText="+displayTextVal+"&displayMode="+displayModeVal+"&printoption="+preOptedVal+"&bankerOption="+bankOptedVal+"&bankerAssuredOption="+bankAssuredOptedVal+"&productTypeIdCert="+productCheckId+"&openCoverNoSettingCert="+openNo+"&currencyOption="+currencyOptedVal+"";
				}
				else 	{
					document.form1.action="print4Schedule.pdfSchedule?policynumber="+policynumber+"&loginid="+loginid1+"&displayText="+displayTextVal+"&displayMode="+displayModeVal+"&printoption="+preOptedVal+"&bankerOption="+bankOptedVal+"&bankerAssuredOption="+bankAssuredOptedVal+"&productTypeIdCert="+productCheckId+"&openCoverNoSettingCert="+openNo+"&currencyOption="+currencyOptedVal+"";
				}
				
			}
			else if(displayTextVal=='DEBIT' || displayTextVal=='INVALID DEBIT')
			{
				if(displayTextVal=='INVALID DEBIT')
				{
					displayTextVal="INVALID DEBIT";
					if(displayModeVal=='NormalSupplement')
						policyModee = "suppleMentDebitMultiple";
					else if(displayModeVal=='NormalMultiple')
						policyModee = "debitMultiple";
					else
						policyModee = "debit";
				}
				else if(displayTextVal=='DEBIT')
				{
					displayTextVal="";
				}

				if(productCheckId=='11')
				{
					document.form1.action="Copyofinformation.jsp?policynumber="+policynumber+"&loginid="+loginid1+"&displayText="+displayTextVal+"&displayMode="+displayModeVal+"&printoption="+preOptedVal+"&bankerOption="+bankOptedVal+"&bankerAssuredOption="+bankAssuredOptedVal+"&policyMode="+policyModee+"&productTypeIdCert="+productCheckId+"&openCoverNoSettingCert="+openNo+"&verNo="+verNo+"&currencyOption="+currencyOptedVal+"";
				}
				else if(productCheckId=='3')
				{
					document.form1.action="Copyofinformation.jsp?policynumber="+policynumber+"&loginid="+loginid1+"&displayText="+displayTextVal+"&displayMode="+displayModeVal+"&printoption="+preOptedVal+"&bankerOption="+bankOptedVal+"&bankerAssuredOption="+bankAssuredOptedVal+"&policyMode="+policyModee+"&productTypeIdCert="+productCheckId+"&openCoverNoSettingCert="+openNo+"&currencyOption="+currencyOptedVal+"";
				}
				else {
					document.form1.action="Copyofinformation.jsp?policynumber="+policynumber+"&loginid="+loginid1+"&displayText="+displayTextVal+"&displayMode="+displayModeVal+"&printoption="+preOptedVal+"&bankerOption="+bankOptedVal+"&bankerAssuredOption="+bankAssuredOptedVal+"&policyMode="+policyModee+"&productTypeIdCert="+productCheckId+"&openCoverNoSettingCert="+openNo+"&currencyOption="+currencyOptedVal+"";
				}
			}
			else if(displayTextVal == 'CREDIT'||displayTextVal == 'INVALID CREDIT')
			{
				if(displayTextVal=='INVALID CREDIT')
				{
					displayTextVal="INVALID CREDIT";
					if(displayModeVal=='NormalSupplement')
						policyModee = "suppleMentCreditMultiple";
					else if(displayModeVal=='NormalMultiple')
						policyModee = "creditMultiple";
					else
						policyModee = "credit";
				}
				else if(displayTextVal=='CREDIT')
				{
					displayTextVal="";
				}

				if(productCheckId=='11')
				{
					document.form1.action="Copyofinformation.jsp?policynumber="+policynumber+"&loginid="+loginid1+"&displayText="+displayTextVal+"&displayMode="+displayModeVal+"&printoption="+preOptedVal+"&bankerOption="+bankOptedVal+"&bankerAssuredOption="+bankAssuredOptedVal+"&policyMode="+policyModee+"&productTypeIdCert="+productCheckId+"&openCoverNoSettingCert="+openNo+"&verNo="+verNo+"&currencyOption="+currencyOptedVal+"";
				}
				else if(productCheckId=='3')
				{
					document.form1.action="Copyofinformation.jsp?policynumber="+policynumber+"&loginid="+loginid1+"&displayText="+displayTextVal+"&displayMode="+displayModeVal+"&printoption="+preOptedVal+"&bankerOption="+bankOptedVal+"&bankerAssuredOption="+bankAssuredOptedVal+"&policyMode="+policyModee+"&productTypeIdCert="+productCheckId+"&openCoverNoSettingCert="+openNo+"&currencyOption="+currencyOptedVal+"";
				}
				else
				{
					document.form1.action="Copyofinformation.jsp?policynumber="+policynumber+"&loginid="+loginid1+"&displayText="+displayTextVal+"&displayMode="+displayModeVal+"&printoption="+preOptedVal+"&bankerOption="+bankOptedVal+"&bankerAssuredOption="+bankAssuredOptedVal+"&policyMode="+policyModee+"&productTypeIdCert="+productCheckId+"&openCoverNoSettingCert="+openNo+"&currencyOption="+currencyOptedVal+"";
				}
			}
			else
			{
				if(productCheckId=='11')
				{
					document.form1.action="Copyofinformation.jsp?policynumber="+policynumber+"&loginid="+loginid1+"&displayText="+displayTextVal+"&displayMode="+displayModeVal+"&printoption="+preOptedVal+"&bankerOption="+bankOptedVal+"&bankerAssuredOption="+bankAssuredOptedVal+"&productTypeIdCert="+productCheckId+"&openCoverNoSettingCert="+openNo+"&verNo="+verNo+"&currencyOption="+currencyOptedVal+"";
				}
				else if(productCheckId=='3')
				{
					document.form1.action="Copyofinformation.jsp?policynumber="+policynumber+"&loginid="+loginid1+"&displayText="+displayTextVal+"&displayMode="+displayModeVal+"&printoption="+preOptedVal+"&bankerOption="+bankOptedVal+"&bankerAssuredOption="+bankAssuredOptedVal+"&productTypeIdCert="+productCheckId+"&openCoverNoSettingCert="+openNo+"&currencyOption="+currencyOptedVal+"";
				}
				else 
				{
					document.form1.action="Copyofinformation.jsp?policynumber="+policynumber+"&loginid="+loginid1+"&displayText="+displayTextVal+"&displayMode="+displayModeVal+"&printoption="+preOptedVal+"&bankerOption="+bankOptedVal+"&bankerAssuredOption="+bankAssuredOptedVal+"&productTypeIdCert="+productCheckId+"&openCoverNoSettingCert="+openNo+"&currencyOption="+currencyOptedVal+"";
				}
			}
			if(displayTextVal=='DELETE')
			{
				displayTextVal="DELETE";
				document.form1.action="Delete4Debit.DeleteDebit?policynumber="+policynumber+"&loginid="+loginid1+"&displayText="+displayTextVal+"&displayMode="+displayModeVal+"&printoption="+preOptedVal+"&bankerOption="+bankOptedVal+"&bankerAssuredOption="+bankAssuredOptedVal+"&currencyOption="+currencyOptedVal+"&productID="+productCheckId+"";
			}
			document.form1.submit();
        return false;
}

function viewPolicyss(authorized)
{
	document.form1.authorized.value="Y";
	document.form1.action="Copy of information Admin.jsp";
	document.form1.submit();
	return false;
}
function download(cFileName,sFileName)
{
	document.form1.custFileName.value=cFileName;
	document.form1.systemFileName.value=sFileName;
	document.form1.action="MarineDownloadFile.jsp";
	document.form1.submit();
	return false;
}
function viewDocuments(policyNo){
	document.form1.policyNo.value = policyNo;
	document.form1.action="viewDocumentReport.action";
	document.form1.submit();
	return false;
}
function getFleetPdf(quoteNo, vehicleId) {
	var URL ='${pageContext.request.contextPath}/motorFleetScheduleReport.action?quoteNo='+quoteNo+'&vehicleId='+vehicleId;
	var bwidth = window.innerWidth;
	var bwidth1 = document.body.clientWidth;
	if(bwidth <= 768) {
		return popUp(URL,bwidth1,'500');
	} else {
		return popUp(URL,'750','500');
	}
}
</script>
</html>