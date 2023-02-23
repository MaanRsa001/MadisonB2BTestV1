<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML>
<html>
<head></head>
<body>
<s:if test='%{!"admin".equalsIgnoreCase(#session.user1)}'>
<form action="initReport.action" method="post">
	<div class="row" style="margin-bottom: 0px;">
		<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4"></div>
		<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4"></div>
		<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
			<div class="panel panel-primary">
				<div class="panel-heading">
					<h3 style="margin-bottom: 0px; margin-top: 0px;">
						<s:if test='#session.product_id=="33" || #session.product_id=="34"'>
							<i class="fa fa-plane"></i>&nbsp;<s:text name="Travel Insurance"/>
						</s:if>
						<s:elseif test='#session.product_id=="41"'>
							<i class="fa fa-heartbeat"></i>&nbsp;<s:text name="Health Insurance"/>
						</s:elseif>
						<s:elseif test='#session.product_id=="65"'>
							<i class="fa fa-car"></i>&nbsp;<s:text name="Motor Insurance"/>
						</s:elseif>
						<s:elseif test='#session.product_id=="30"'>
							<i class="fa fa-home"></i>&nbsp;<s:text name="Home Insurance"/>
						</s:elseif>
						<s:elseif test='#session.product_id=="3" || #session.product_id=="11"'>
							<i class="fa fa-ship"></i>&nbsp;<s:text name="Marine Insurance"/>
						</s:elseif>						
					</h3>
				</div>
			</div>
		</div>
	</div>
	<div style="width: 100%; margin: 0 auto;">
		<s:if test='#session.b2c=="b2c"'>	
			<ul class="slimmenu">
				<li><a href="${pageContext.request.contextPath}/login/ProductSelection.jsp">PRODUCTS</a></li>
				<li><a href="${pageContext.request.contextPath}/CopyQuote/BtoCSearchQuote.jsp">RETRIEVE QUOTE</a></li>
				<li><a href="${pageContext.request.contextPath}/CopyQuote/BtoCSearchQuote.jsp">RETRIEVE POLICY</a></li>
				<s:if test='#session.typeOfProduct=="M"'>
					<li><a href="${pageContext.request.contextPath}/CopyQuote/BtoCSearchPolicy.jsp?reqFrom=renew">RENEW POLICY</a></li>
				</s:if>
			</ul>
		</s:if>
		<s:elseif test='#session.LoginType=="B2C"'>			
		</s:elseif>
		<s:else>
			<ul class="slimmenu">
				<li><a href="#" onclick="newQuote('<s:property value="%{#session.product_id}"/>')">New Quote</a></li>
				<li>
					<a href="#">Quote Register</a>
					<ul style="padding: 0px;">
						<li><a href="#" onclick="menuSelect('QE')">Existing Quotes</a></li>
						<%--<li><a href="#" onclick="menuSelect('QS')">Saved Quotes</a></li> --%>
						<li><a href="#" onclick="menuSelect('QL')">Lapsed Quotes</a></li>
						<li><a href="#" onclick="menuSelect('L')">Rejected Quotes</a></li>
						<s:if test='%{#session.usertype==getText("ISSUER")}'>
							<li><a href="#" onclick="menuSelect('BQE')">B2C Existing Quote</a></li>
						</s:if>
					</ul>
				</li>							
				<s:if test='#session.product_id==getText("OPEN_COVER")'>	
				<li>
					<a href="#">Portfolio</a>
					<ul style="padding: 0px;">
						<li><a href="#" onclick="menuSelect('P')">Certificate</a></li>
						 <li><a href="#" onclick="menuSelect('PC')">Canceled Certificate</a></li> 				
					</ul>
				</li>
				</s:if>
				<s:else>
				<li>
					<a href="#">Portfolio</a>
					<ul style="padding: 0px;">
			 			<li><a href="#" onclick="menuSelect('AP')">Pending Policies</a></li>
			 			<li><a href="#" onclick="menuSelect('P')">Policies</a></li>
						<li><a href="#" onclick="menuSelect('PC')">Canceled Policies</a></li>
						<s:if test='%{#session.usertype==getText("ISSUER")}'>
							<li><a href="#" onclick="menuSelect('BP')">B2C Portfolio</a></li>
						</s:if>
						<li><a href="#" onclick="menuSelect('RP')">Renewal Policies</a></li>
						<s:if test='%{#session.usertype==getText("ISSUER")}'>
							<li><a href="#" onclick="menuSelect('BRP')">B2C Renewal Policies</a></li>
						</s:if>
						<li><a href="#" onclick="menuSelectQuickRenewal()">Quick Renewal</a></li>
					</ul>
				</li>
				</s:else>
				<li>
					<a href="#">Referral Quote</a>
					<ul style="padding: 0px;">
						<li><a href="#" onclick="menuSelect('RA')" >Referral Approved</a></li>
						<li><a href="#" onclick="menuSelect('RU')">Referral UnApproved</a></li>
						<li><a href="#" onclick="menuSelect('RR')">Referral Rejected</a></li>
						<s:if test='%{#session.usertype==getText("ISSUER")}'>
							<li><a href="#" onclick="menuSelect('BRU')">B2C Referral UnApproved</a></li>
						</s:if>
					</ul>
				</li>
				<s:if test='%{!"User".equalsIgnoreCase(#session.usertype)}'>
				<li><a href="#" onclick="menuSelect('C')">Customer</a></li>
				</s:if>
				<li>
					<a href="#">Reports</a>
					<ul style="padding: 0px;">
						<li><a href="#" onclick="menuSelect('R')">Reports</a></li>
						<s:if test='"3".equals(#session.product_id) || "11".equals(#session.product_id)'>
						 	<li> <a href="#" onclick="movePolicyPrint('PP')">Policy Print </a></li>
						 </s:if>
						 <s:if test='%{#session.usertype==getText("ISSUER")}'>
							<li>
								<a href="#" onclick="menuSelectOthers('mUploadImgMotorAdminNew.action')">Mobile Upload Image Report</a>
								</li>
							<li>
								<a href="#" onclick="menuSelectOthers('roadAssistReportAM.action')">Road Assistant Report</a>
							</li>
							<li>
								<a href="#" onclick="menuSelectOthers('getEmiPaymentStatus.action')">Installment Report</a>
							</li>
						</s:if>			
					</ul>
				</li>
				<%--
				<s:if test='#session.product_id==getText("OPEN_COVER")'>	
				<li>
					<a href="#">Open Cover</a>
					<ul style="padding: 0px;">
						<li><a href="${pageContext.request.contextPath}/ViewOpenCovers.jsp" onclick="menuSelect('C')" >Certificate</a></li>
						<li><a href="#" onclick="declarationMenu('D')">Declaration</a></li>
						<li><a href="#" onclick="declarationMenu('DE')">Declaration Menu</a></li>
						<li><a href="${pageContext.request.contextPath}/redirectOpenUpload.action" onclick="menuSelect('RU')">Declaration Upload</a></li>
						<li><a href="#" onclick="menuSelect('T')">Upload Transaction</a></li>
					</ul>
				</li>	
				</s:if>
				--%>
				<li>
					<a href="#" onclick="#">Endorsement</a>
					<ul style="padding: 0px;">
						<li><a href="#" onclick="motorRequest('Endorse')">Endorsement Request</a></li>
						<li><a href="#" onclick="menuSelectOthers('endorsementClaim.action?mode=showlist')">Endorsement Status</a></li>
					</ul>
					
				</li>
				<li>
					<a href="#" onclick="#">Claim</a>
					<ul style="padding: 0px;">
						<li><a href="#" onclick="motorRequest('Claim')">Claim Request</a></li>
						<li><a href="#" onclick="menuSelectOthers('endorsementClaim.action?mode=showlistClaim')">Claim Status</a></li>
					</ul>
				</li>
				<s:if test='%{"User".equalsIgnoreCase(#session.usertype)}'>
				<li>
					<a href="#" onclick="#">Road Side Assistant</a>
					<ul style="padding: 0px;">
						<li><a href="#" onclick="menuSelectOthers('getRoadAssistantMotor.action')">Road Side Request</a></li>
						<li><a href="#" onclick="menuSelectOthers('getRAListMotor.action')">Road Side Status</a></li>
					</ul>
				</li>
				</s:if>
				<%--<s:if test='issuer !=null'>
					<li><a href="#" onclick="menuSelect('PE')">Endorsement</a></li>
				</s:if>--%>				
				<li><a href="initCopyQuote.action" >Copy Quote</a></li>						
				<li><a href="initSearchReport.action" >Search</a></li>		
				<s:if test='%{"User".equalsIgnoreCase(#session.usertype)}'>
					<li><a href="modifyUserReg.action" >Profile</a></li>
				</s:if>
				<s:if test='%{#session.usertype==getText("ISSUER")}'>
					<li>
						<a href="#" onclick="#">Details Report</a>
						<ul style="padding: 0px;">
							<li><a href="#" onclick="detailReport('POLICYREGISTER')">Policy Reports</a></li>
							<li><a href="#" onclick="detailReport('')">Installment/Aging Anlysis Reports</a></li>
							<li><a href="#" onclick="detailReport('ROADASSIT')">Road Assistance Report</a></li>
							<li><a href="#" onclick="detailReport('ENDORSEMENTREGISTER')">Endorsement Report</a></li>
							<li><a href="#" onclick="detailReport('PREMIUMREGISTER')">Premium Reports</a></li>
							<li><a href="#" onclick="detailReport('CLAIMS')">Claim Reports</a></li>
							<li><a href="#" onclick="detailReport('APRQUOTES')">Quotation Reports</a></li>
						</ul>
					</li>
					<li>
						<a href="#" onclick="#">Underwriter</a>
						<ul style="padding: 0px;">
							<li><a href="#" onclick="underwriterSubmit('creditControlPaymentProcess.action?reqForm=uwPending')">Pending</a></li>
							<li><a href="#" onclick="underwriterSubmit('creditControlPaymentProcess.action?reqForm=uwNStatus')">Rejected</a></li>
							<li><a href="#" onclick="underwriterSubmit('creditControlPaymentProcess.action?reqForm=uwYStatus')">Accepted</a></li>
						</ul>
					</li>
				</s:if>	
			</ul>
		</s:else>	
<s:hidden name="menuType"/>
<s:hidden name="reportType"/>			
<s:hidden name="productId"/>
<s:hidden name="quoteStatus"/>
<s:hidden name="loginId"/>	
	</div>
</form>
</s:if>
<script type="text/javascript">
function menuSelect(obj)
{	
	document.forms[0].menuType.value=obj;
	document.forms[0].loginId.value='<s:property value="#session.user"/>';
	document.forms[0].action='${pageContext.request.contextPath}/initReport.action';	
	document.forms[0].submit();
}
function menuSelectOthers(action) {
	document.forms[0].action='${pageContext.request.contextPath}/' + action;
	document.forms[0].submit();
}
function newQuote(productId)
{
	if(null=='<s:property value="#session.RSAISSUER"/>' || ''=='<s:property value="#session.RSAISSUER"/>')
	{
		document.forms[0].productId.value=productId;
		document.forms[0].quoteStatus.value='QE';	
		//if(productId=='31'||productId=='32'||productId=='33'||productId=='34'||productId=='41')//31-Travel & 32-Travel Standard &33-Travel -Walaa &41-Helath Insurance Walaa & 30-Home Insurance-Walaa
		if(productId=='31'||productId=='32'||productId=='34'||productId=='41')//33-Travel & 32-Travel Standard &33-Travel -Walaa &41-Helath Insurance Walaa & 30-Home Insurance-Walaa
		{
			document.forms[0].action="${pageContext.request.contextPath}/getDetailCustomer.action";
		}else if(productId=='65')
			document.forms[0].action="${pageContext.request.contextPath}/editQuoteMotor.action";
		else if(productId=='30')
			document.forms[0].action="${pageContext.request.contextPath}/packageSelectionHome.action";
		else if(productId=='33')
			document.forms[0].action="${pageContext.request.contextPath}/initTravel.action";	
		else
			document.forms[0].action="${pageContext.request.contextPath}/newQuoteQuotation.action";
	}else{
		document.forms[0].quoteStatus.value='QE';
		if(productId=='65')
			document.forms[0].action="${pageContext.request.contextPath}/editQuoteMotor.action";
		else if(productId=='30')
			document.forms[0].action="${pageContext.request.contextPath}/packageSelectionHome.action";
		else if(productId=='11' || productId=='3')
			document.forms[0].action="${pageContext.request.contextPath}/newQuoteQuotation.action";
		else if(productId=='33')
			document.forms[0].action="${pageContext.request.contextPath}/initTravel.action";	
		else{
			document.forms[0].loginId.value='<s:property value="#session.user"/>';
			document.forms[0].action='${pageContext.request.contextPath}/login/RSAPolicyIssue.jsp';
		}
	}
	document.forms[0].submit();
	return false;
}
function declarationMenu(obj)
{
	document.forms[0].menuType.value=obj;
	document.forms[0].action='${pageContext.request.contextPath}/initDeclaration.action';	
	document.forms[0].submit();
}
function movePolicyPrint(obj)
{
	document.forms[0].menuType.value=obj;
	document.forms[0].action='${pageContext.request.contextPath}/pInitDeclaration.action';	
	document.forms[0].submit();
}

function detailReport(reportType)
{
	document.forms[0].reportType.value=reportType;
	document.forms[0].action='${pageContext.request.contextPath}/getAllListAreport.action';	
	document.forms[0].submit();
}

function motorVehicleDetails(policyNo) {
	document.forms['report'].menuType.value = 'PVD';
	document.forms['report'].policyNo.value = policyNo;
	document.forms['report'].action = "${pageContext.request.contextPath}/initReport.action";
	document.forms['report'].submit();
}
function motorRequest(reqFrom) {
	document.forms[0].menuType.value = 'PVD';
	document.forms[0].action = "${pageContext.request.contextPath}/initReport.action?reqFrom="+reqFrom;
	document.forms[0].submit();
}
function underwriterSubmit(action){
	document.forms[0].action = "${pageContext.request.contextPath}/"+action;
	document.forms[0].submit();
}

function menuSelectQuickRenewal()
{	
	document.forms[0].loginId.value='<s:property value="#session.user"/>';
	document.forms[0].action='${pageContext.request.contextPath}/portfolioQuickRenewal.action';	
	document.forms[0].submit();
}

</script>
</body>
</html>