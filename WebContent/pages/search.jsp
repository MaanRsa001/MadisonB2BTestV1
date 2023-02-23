<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib uri="http://displaytag.sf.net" prefix="display"%>
<!DOCTYPE HTML>
<html>
<head>
	<script type="text/javascript" src="js/common.js"></script>		
	<script language="JavaScript">
		function stopRKey(evt) {
		  var evt = (evt) ? evt : ((event) ? event : null); 
		  var node = (evt.target) ? evt.target : ((evt.srcElement) ? evt.srcElement : null); 
		  if ((evt.keyCode == 13) && (node.type=="text"))  {return false;} 
		}
		document.onkeypress = stopRKey; 
	</script>			
</head>
<body> 
<s:form name="search" theme="simple" action="searchReport.action">
<div class=" wrapper vehDtl">
	<div class="Card_Parent ">
	    <div class="card card-5">
			<div class="container">
				<div class="panel panel-primary">
					<div class="panel-heading">
						<div class="VehicleTableheading">
							<div class="row">
								<div class="col-md-12">
									<h3><s:label key="search.search" /></h3><hr>
								</div>
							</div>
						</div>
					</div>
					<div class="panel-body">
						<div style="color: red;"><s:actionerror/></div>
						<br class="clear" />
						<div class="row">
							<div class="col-xs-12 col-sm-12 col-md-6 col-lg-4">
								<div class="text">
									<b><s:label key="search.searchBy" /></b>
								</div>
								<div class="tbox">
									<s:if test='#session.usertype==getText("ISSUER")'>
										<s:select name="searchBy" id="searchBy" list="#{'policyNo':'Policy No','quoteNo':'Quote No','custName':'Customer Name','nrc':'NRC','comRegNo':'Company Reg.No','otherUsers':'Other Users'}"  headerKey="" headerValue="Select" cssClass="form-control" onchange="setDefault(this)"/>
									</s:if>
									<s:else>
										<s:select name="searchBy" id="searchBy" list="#{'policyNo':'Policy No','quoteNo':'Quote No','custName':'Customer Name','nrc':'NRC','comRegNo':'Company Reg.No'}"  headerKey="" headerValue="Select"  onchange="setDefault(this)" cssClass="form-control"/>
									</s:else>
								</div>
							</div>
							<s:if test='#session.usertype==getText("BROKER") || #session.usertype==getText("ISSUER")'> 
							<div class="col-xs-12 col-sm-12 col-md-6 col-lg-4">
								<div class="text">
									<b><s:label key="report.selectuser" theme="simple"/></b>
								</div>
								<div class="tbox">
									<s:select list="userSelection" listKey="LOGIN_ID" listValue="USERNAME" name="loginId"  headerKey="" headerValue="Select" value='%{#session.product_id==getText("OPEN_COVER")?#session.userName:loginId}'  cssClass="form-control"/>
								</div>
							</div>
							</s:if>
							<div class="col-xs-12 col-sm-12 col-md-6 col-lg-4">
								<div class="text">
									<b><s:label key="search.enterDataForSearch"/></b>
								</div>
								<div class="tbox">
									<%-- <s:textfield name="searchValue" id="searchValue" onkeyup="getSearchResult('searchResult',this.value)" cssClass="form-control"/> --%>	
									<s:textfield name="searchValue" id="searchValue" cssClass="form-control"/>
								</div>
							</div>
							<br class="clear" />
						</div>
						<div class="row mt-5" align="center">
							<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
								<button type="button" class="btn btn-sm btn-success" onclick="getSearchResult('searchResult','')"> Search</button>
							</div>
						</div>
						<br class="clear" />
						<div class="boxcontent">
							<div id="searchResult"></div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<s:hidden name="openCoverNo" value="%{#session.openCoverNo}"/>
<s:hidden name="quoteNo"/>
<s:hidden name="quoteStatus"/>
<s:hidden name="tranId"/>
<s:hidden name="policyNo"/>
<s:hidden name="menuType"/> 
<s:hidden name="applicationNo"/>
<s:hidden name="linkType" />
<s:hidden name="scrnFrom" />
<s:hidden name="quote_no" />
</s:form>
<script type="text/javascript">

function setDefault(obj) { 
 	var value=obj.value;  
 	var productCode='<s:text name="OPEN_COVER"/>';
 	var session_productCode='<s:property value="#session.product_id"/>'
 	var loginId='';
	loginId='<s:property value="#session.user"/>'; 
 	if(value=='policyNo' && session_productCode == productCode ) {  
 		document.getElementById('searchValue').value='<s:property value="#session.openCoverNo"/>';
 		getSearchResult('searchResult',document.search.searchValue.value);
 	} else {
 		 document.getElementById('searchValue').value='';   
 	}
 	if(session_productCode != productCode) {
 		postRequest('${pageContext.request.contextPath}/brokerListReport.action?searchBy='+value+'&loginId='+loginId,'brokerList');
 	}
}

if(document.search.searchValue.value!='') {
	getSearchResult('searchResult',document.search.searchValue.value);
}

function getSearchResult(id, searchValue) {
	var error='';
	var loginId='';
	var searchVal=searchValue;
	var searchBy=document.search.searchBy.value;
	if(searchVal==null || searchVal==''){
		searchVal=document.search.searchValue.value;
	}
	
	if('User'=='<s:property value="#session.usertype"/>'){
		loginId='<s:property value="#session.user"/>'; 
	} else {
		loginId=document.search.loginId.value;
	}
	if(searchBy=='') {
		error+='- <s:text name="error.searchBy.empty" />\n';
	} 
	if(loginId=='') {
		error+='- <s:text name="error.loginId.empty" />\n';
	}
	if(error=='' && searchVal!='') {
		postRequest('${pageContext.request.contextPath}/searchReport.action?searchBy='+searchBy+'&loginId='+loginId+'&searchValue='+searchVal+'&openCoverNo=<s:property value="#session.openCoverNo"/>'+'&searchFrom=S',id);
	} else if(error!='') {
		alert(error);
	}
}

function view(appNo,quoteNo,productId){
	popUp('\quotationSchedule.jsp?quoteNo='+quoteNo,650,650);
}

/*function viewPending(appNo,quoteNo,productId){
	popUp('${pageContext.request.contextPath}/viewpdfReport?req=report&quoteNo='+quoteNo,650,650);
}*/

function editQuote(applicationNo,quoteNo, status,customerId) {
	var pId='<s:property value="#session.product_id"/>'
	document.search.quoteNo.value=quoteNo;
	document.search.quoteStatus.value=status;
	document.search.applicationNo.value=applicationNo;
	//document.search.action = "${pageContext.request.contextPath}/editQuoteQuotation.action";
	if(pId=='65'){
		if(status=='RA'){
			document.search.action = "${pageContext.request.contextPath}/quotationMotor.action";
		}
		else{
			document.search.action = "${pageContext.request.contextPath}/editQuoteNewMotor.action";
		}
		
	}
	else if(pId=='30'){
		document.search.action = "${pageContext.request.contextPath}/packageSelectionHome.action";
	}		
	document.search.submit();
}
/*function sentMail(val){
	document.search.scrnFrom.value = "QuoteRegister";
	document.search.quote_no.value=val;
	document.search.action="mailController";
	document.search.submit();
}*/
function sentMail(applicationNo,linkType,quoteNo) {
	document.search.menuType.value='QL';
	document.search.applicationNo.value=applicationNo;
	document.search.linkType.value=linkType;
	document.search.quoteNo.value=quoteNo;		
	document.search.action='${pageContext.request.contextPath}/mailReport.action';
	document.search.submit();
}
function viewPending(val)
{
	var URL ='${pageContext.request.contextPath}/QuotePrint.pdfSchedule?quote_no='+val+'&reqFrom=QuotePrint';
	var windowName = "QuotatiionPrint";
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

function motorVehicleDetails(policyNo) {
	document.search.menuType.value = 'PVD';
	document.search.policyNo.value = policyNo;
	document.search.action = "${pageContext.request.contextPath}/initReport.action";
	document.search.submit();
}
</script>
</body>
</html>