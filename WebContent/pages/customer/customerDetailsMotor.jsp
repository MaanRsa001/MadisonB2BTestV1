<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<%--
	<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/calendars/jquery.calendars.picker.css">
	--%>
	
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
<s:if test='issuer != null'>
<div class="row">
	<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
		<div class="panel panel-primary">
  			<div class="panel-heading txtB">
  				<s:text name="customer.brokerInfo" />
  			</div>
  			<div class="panel-body">
  				<div class="row">
  					<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
  						<div class="text"><s:text name="customer.broker.name"  /><font color="red">*</font></div>
  						<div class="tbox">
  							<s:select name="brokerCode" id="brokerCode" list="brokerList" headerKey="" headerValue="---Select---" listKey="CODE" listValue="CODEDESC" cssClass="inputBoxS "  onchange="getExecutive('?brokerCode='+this.value,'executiveList');" disabled="%{brokerCode!=null && brokerCode!=''}"/>
                 			<s:hidden name="brokerName" />
  						</div>
  					</div>
  					<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
  						<div class="text"><s:text name="customer.executive.bdm"  /><font color="red">*</font></div>
  						<div class="tbox">
  							<div id="executiveList"><s:select name="executive" list="executiveList" headerKey="" headerValue="---Select---" listKey="CODE" listValue="CODEDESC" cssClass="inputBoxS "  disabled="#disable" value='executive==null?getText("customer.executiveDefault"):executive'/></div>
  						</div>
  					</div>
  					<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
  					</div>
  				</div>  				
  			</div>
  		</div>  		
	</div>
</div>
</s:if>
<s:else>
	<s:hidden name="brokerCode" />
	<s:hidden name="executive" />
</s:else>
<div class="row">
	<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" align="right">
		<font color="red">* &nbsp;Mandatory fields</font>
	</div>
</div>
<div class="row">
	<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
		<div class="panel panel-primary">
  			<div class="panel-heading txtB">
  				<s:if test="'new'.equals(display) || 'update'.equals(mode)">
  					<s:text name="customer.contact.info" />
  				</s:if>
  				<s:else>
					<s:text name="customer.customerInfo" />					  				
  				</s:else>
				<s:if test='%{"65".equals(#session.product_id)}'>
					<%--<button type="button" class="btn btn-sm btn-primary" data-toggle="modal" data-refresh="true" href="${pageContext.request.contextPath}/getHelpInfoMotor.action?helpType=CUSTOMER_INFORMATION" data-target="#modalcustomerDetails"> <img src="${pageContext.request.contextPath}/images/Help.png" width="20" height="20"/> </button> --%>
					<div class="modal fade" data-refresh="true" id="modalcustomerDetails" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
						<div class="modal-dialog modal-lg">
							<div class="modal-content">
								<div class="modal-header">
									<h4 class="modal-title"> <s:label key="customer.customerInfo"/> </h4>
								</div>
								<div class="modal-body">
									<div class="te"></div>
								</div>
							</div>
						</div>
					</div>
				</s:if>
				<div class="pullRight">
					<button type="button" class="btn btn-sm btn-danger" onclick="empyCustDetails();">Clear</button>
				</div>
				<br class="clear" />
  			</div>
  			<div class="panel-body">
  				<s:if test='"newQuote".equalsIgnoreCase(display) || "getQuote".equalsIgnoreCase(display) || "getCustomer".equalsIgnoreCase(display) ||"newQuoteOtpLogin".equalsIgnoreCase(display)'>
	  				<div class="row">
	  					<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
	  						<div class="text"><s:text name="customer.title"  /><font color="red">*</font></div>
	  						<div class="tbox">
	  							<s:select name="title" id="title" list="titleList" listKey="CODEDESC" listValue="CODEDESC" cssClass="inputBoxS "  disabled="#disable1"/>
	  						</div>
	  					</div>
	  					<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
	  						<div class="text"><s:text name="customer.firstName"  /><font color="red">*</font></div>
	  						<div class="tbox">
	  							<s:if test='%{#session.LoginType!="B2C" && !"User".equalsIgnoreCase(#session.usertype)}'>
									<s:textfield name="customerName" id="customerName" cssClass="inputBox  empyCustDetails"  cssStyle="width:85%;" maxLength="100" disabled="#disable1" /><s:hidden name="customerId"  id="customerId"/>
									<s:submit type="button" value="..." cssClass="inputButton" onclick="return customerSelectionAction(this.form)" disabled="#disable1"/>
								</s:if>
								<s:else>
									<s:textfield name="customerName" id="customerName" cssClass="inputBox  empyCustDetails"  maxLength="100" disabled="#disable1" /><s:hidden name="customerId"  id="customerId"/>
								</s:else>
	  						</div>
	  					</div>
	  					<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
	  						<div class="text"><s:text name="customer.lastName"/></div>
	  						<div class="tbox">
	  							<s:textfield name="custLastName" id="custLastName" cssClass="inputBox  empyCustDetails"   maxLength="20" disabled="#endtDisable"  />
	  						</div>
	  					</div>
	  					<%--
	  					<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
	  						<div class="text">Customer Name(Arabic)<font color="red">*</font></div>
	  						<div class="tbox">
	  							<s:textfield name="custNameArabic" id="custNameArabic" cssClass="inputBox"  maxLength="20" disabled="#disable1"/>
	  						</div>
	  					</div>
	  					--%>
	  				</div>
	  				<div class="row">
	  					<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
	  						<div class="text"><s:text name="customer.email"  /><font color="red">*</font></div>
	  						<div class="tbox">
	  							<s:textfield name="email" id="email" cssClass="inputBox  empyCustDetails"  maxLength="100" disabled="#disable1"/>
	  						</div>
	  					</div>
	  					<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
	  						<div class="text"><s:text name="customer.mobileNo1"  /><font color="red">*</font></div>
	  						<div class="tbox">
	  							<s:textfield name="mobileNo" id="mobileNo" cssClass="inputBox  empyCustDetails" maxLength="10" onkeyup="checkNumbers(this);" disabled="#disable1"/>
	  						</div>
	  					</div>
	  					<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
	  						<div class="text"> <s:text name="customer.customerType"  /><font color="red">*</font>  </div>
	  						<div class="tbox">
	  							<s:radio name="customerType" id="customerType" list="#{'INDIVIDUAL':'Individual','CORPORATE':'Corporate'}" value='%{(customerType==null||"".equals(customerType))?"INDIVIDUAL":customerType}' cssClass="input "  disabled='%{(productId==null)?true:false}' onclick="toggleCompanyRegistration(this.value);"/>
	  						</div>
	  					</div>
	  					<%-- <div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
	  						<div class="text"><s:text name="customer.companyRegNo"  /><font color="red">*</font></div>
	  						<div class="tbox">
	  							<s:textfield name="companyRegNo" id="companyRegNo" cssClass="inputBox  empyCustDetails" data-content="For customer type corporate, enter your PACRA company number" maxLength="10" disabled='%{(customerType==null||"".equals(customerType)||#disable1||"INDIVIDUAL".equals(customerType))?"true":"false"}'/>
	  						</div>
	  					</div>--%>
	  				</div>
	  				<s:hidden name="coreAppCode" id="coreAppCode"/>
					<s:hidden name="custArNo" id="custArNo"/>
					<s:hidden name="custCoreCode" id="custCoreCode"/>
					<s:hidden name="clientCustomerId" id="clientCustomerId"/>
					<s:hidden name="address1" id="address1"/>
					<s:hidden name="address2" id="address2"/>
					<s:hidden name="city" id="city"/>
					<s:hidden name="poBox" id="poBox"/>
					<s:hidden name="gender" id="gender"/>
					<s:hidden name="occupation" id="occupation"/>
					<s:hidden name="custdob" id="custdob"/>
					<s:hidden name="custAlterMobileNo" id="custAlterMobileNo"/>
					<s:hidden name="custnrc1" id="custnrc1"/>
					<s:hidden name="custnrc2" id="custnrc2"></s:hidden>
					<s:hidden name="custnrc3" id="custnrc3"></s:hidden>
					<s:hidden name="custPassportNo" id="custPassportNo"></s:hidden>
					<%--<s:hidden name="companyRegNo" id="companyRegNo"></s:hidden> --%>
				</s:if>
				<s:else>
  				<div class="row">
  					<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
  						<div class="text"><s:text name="customer.title"  /><font color="red">*</font></div>
  						<div class="tbox">
  							<s:select name="title" id="title" list="titleList" listKey="CODEDESC" listValue="CODEDESC" cssClass="inputBoxS "  disabled="#disable1"/>
  						</div>
  					</div>
  					<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
  						<div class="text"><s:text name="customer.firstName"/><font color="red">*</font></div>
  						<div class="tbox">
  							<s:if test='%{#session.LoginType!="B2C" && !"User".equalsIgnoreCase(#session.usertype)}'>
	  							<s:textfield name="customerName" id="customerName" cssClass="inputBox  empyCustDetails"  cssStyle="width:85%;" maxLength="100" disabled="#disable1" />
								<s:submit type="button" value="..." cssClass="inputButton" onclick="return customerSelectionAction(this.form)" />
							</s:if>
							<s:else>
								<s:textfield name="customerName" id="customerName" cssClass="inputBox  empyCustDetails"  maxLength="100" disabled="#disable1" />
								
							</s:else>
  						</div>
  					</div>
  					<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
  						<div class="text"><s:text name="customer.lastName"/><font color="red">*</font></div>
  						<div class="tbox">
  							<s:textfield name="custLastName" id="custLastName" cssClass="inputBox  empyCustDetails"   maxLength="20" disabled="#endtDisable"  />
  						</div>
  					</div>
  				</div>
  				<div class="row">
  					<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
						<div class="text"><s:text name="customer.gender"/><font color="red">*</font></div>
						<div class="tbox"><s:radio name="gender" list="#{'M':'Male','F':'Female'}" /></div>
					</div>
					<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
						<div class="text"><s:text name="customer.occupation"/></div>
						<div class="tbox"><s:textfield name="occupation" maxlength="30" cssClass="inputBox L empyCustDetails"  disabled="#endtDisable" /></div>
					</div>
					<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
  						<div class="text"><s:text name="customer.dob" /><s:text name="(DD/MM/YYYY)" /><font color="red">*</font></div>
  						<div class="tbox">
  							<s:textfield name="custdob" id="custdob" cssClass="inputBox datePicker L empyCustDetails"  disabled="#endtDisable"/>
  						</div>
  					</div>
  				</div>
  				<div class="row">
  					<%--
  					<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
  						<div class="text">Customer Name(Arabic)<font color="red">*</font></div>
  						<div class="tbox">
  							<s:textfield name="custNameArabic" id="custNameArabic" cssClass="inputBox"  maxLength="20" disabled="#endtDisable"/>
  						</div>
  					</div>
  					--%>
  					<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
  						<div class="text"><s:text name="customer.address1"/><font color="red">*</font></div>
  						<div class="tbox">
  							<s:textfield name="address1" id="address1" cssClass="inputBox  empyCustDetails"   maxLength="200" disabled="#endtDisable"/>
  						</div>
  					</div>
  					<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
  						<div class="text"><s:text name="customer.address2"/></div>
  						<div class="tbox">
  							<s:textfield name="address2" id="address2" cssClass="inputBox  empyCustDetails"  maxlength="200" disabled="#endtDisable"/>
  						</div>
  					</div>
  					<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
  						<div class="text"><s:text name="customer.city"  /><font color="red">*</font></div>
  						<div class="tbox">
  							<s:select name="city" id="city" list="cityList" headerKey="" headerValue="---Select---" listKey="CODEDESC" listValue="CODEDESC" cssClass="inputBoxS  empyCustDetails"  disabled="#endtDisable"/>
  						</div>
  					</div>
  				</div>
  				<div class="row">
  					<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
  						<div class="text"><s:text name="customer.poBox"  /></div>
  						<div class="tbox">
  							<s:textfield name="poBox" id="poBox" cssClass="inputBox  empyCustDetails"  maxLength="6" disabled="#endtDisable"/>
  						</div>
  					</div>
  					
  					<%--
  					<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
  						<div class="text"><s:text name="customer.dob" /><font color="red">*</font> (Islamic) </div>
  						<div class="tbox">
  							<s:textfield name="custdobar" id="custdobar" cssClass="inputBox" disabled="true"/>
  						</div>
  					</div>
  					--%>
  					<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
  						<div class="text"> <s:text name="customer.mobileNo1"  /><font color="red">*</font> </div>
  						<div class="tbox">
  							<s:textfield name="mobileNo" id="mobileNo" cssClass="inputBox  empyCustDetails"  maxLength="10" onkeyup="checkNumbers(this);" disabled="#disable1"/>
  						</div>
  					</div>
  					<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
  						<div class="text"><s:text name="customer.alterMobile"  /></div>
  						<div class="tbox">
  							<s:textfield name="custAlterMobileNo" id="custAlterMobileNo" cssClass="inputBox  empyCustDetails"  maxLength="10" onkeyup="checkNumbers(this);" disabled="#endtDisable"/>
  						</div>
  					</div>
  				</div>
  				<div class="row">
  					<s:if test='!"new".equals(display)'>
						<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
	  						<div class="text"> <s:text name="customer.email"  /><font color="red">*</font> </div>
	  						<div class="tbox">
	  							<s:textfield name="email" id="email" cssClass="inputBox  empyCustDetails"  maxLength="100" disabled="#endtDisable"/>
	  						</div>
	  					</div>	  					
	  				</s:if>
  					<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
  						<div class="text"><s:text name="customer.nrc"/><font color="red">*</font></div>
  						<div class="tbox">
  							<s:textfield name="custnrc1" id="custnrc1" cssClass="inputBox  empyCustDetails"  cssStyle="width:35%" maxLength="6" size="6" onkeyup="checkDecimals6_0(this);autotab(this, this.form.custnrc2);" disabled="#endtDisable"/>&nbsp;/&nbsp;
  							<s:textfield name="custnrc2" id="custnrc2" cssClass="inputBox  empyCustDetails"  cssStyle="width:25%" maxLength="2" size="2" onkeyup="checkDecimals6_0(this);autotab(this, this.form.custnrc3)" disabled="#endtDisable"/>&nbsp;/&nbsp;
  							<s:textfield name="custnrc3" id="custnrc3" cssClass="inputBox  empyCustDetails"  cssStyle="width:15%" maxLength="1" size="1" onkeyup="checkDecimals6_0(this);" disabled="#endtDisable"/>
  						</div>
  					</div>
  					<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
  						<div class="text"><s:text name="customer.passportNo"/></div>
  						<div class="tbox">
  							<s:textfield name="custPassportNo" id="custPassportNo" cssClass="inputBox  empyCustDetails"   maxLength="10" disabled="#endtDisable"/>
  						</div>
  					</div>
  					<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
  						<div class="text"> <s:text name="customer.customerType"  /><font color="red">*</font>  </div>
  						<div class="tbox">
  							<s:radio name="customerType" id="customerType" list="#{'INDIVIDUAL':'Individual','CORPORATE':'Corporate'}" value='%{(customerType==null||"".equals(customerType))?"INDIVIDUAL":customerType}' cssClass="input "  onclick="toggleCompanyRegistration(this.value);"/>
  						</div>
  					</div>
  					<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
  						<div class="text"><s:text name="customer.companyRegNo"  /><font color="red">*</font></div>
  						<div class="tbox">
  							<s:textfield name="companyRegNo" id="companyRegNo" cssClass="inputBox  empyCustDetails"  maxLength="10" disabled='%{(customerType==null||"".equals(customerType)||#disable1||"INDIVIDUAL".equals(customerType))?"true":"false"}'/>
  						</div>
  					</div>
  					<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4" style="display: none;">
  						<div class="text"><s:text name="customer.coreAppCode"  /></div>
  						<div class="tbox">
  							<s:if test='%{#session.LoginType!="B2C" && !"User".equalsIgnoreCase(#session.usertype)}'>
								<s:textfield name="coreAppCode" id="coreAppCode" cssClass="inputBox "  cssStyle="width:85%;" readonly="true" disabled="#disable1"/>
								<s:submit type="button" cssClass="inputButton" value="..." onclick="return coreCustomerSearch()" />
							</s:if>
							<s:else>
								<s:textfield name="coreAppCode" id="coreAppCode" cssClass="inputBox "  readonly="true" disabled="#disable1"/>
							</s:else>
  						</div>
						<s:hidden name="custArNo" id="custArNo"/>
						<s:hidden name="custCoreCode" id="custCoreCode"/>
						<s:hidden name="clientCustomerId" id="clientCustomerId"/>
  					</div>
  				</div>
  				<div class="row">
  					<%--
					<div class="textfield33">
						<div class="text">
							<s:text name="customer.landLineNo"  /><font color="red">*</font><br />
						</div>
						<div class="tbox">
							<s:textfield name="custLandLineNo" id="custLandLineNo" cssClass="inputBox" maxLength="10" onkeyup="checkNumbers(this);" disabled="#disable1"/>
						</div>
					</div>
					--%>
  				</div>
				</s:else>
  			</div>
  		</div>
  	</div>
</div>
<script  type="text/javascript">
function getExecutive(val, id) {
	postRequest('${pageContext.request.contextPath}/'+id+'Customer.action'+val, id);
}
	
function emptyCustInfo() {
	var s="poBox&mobileNo&email&title&customerName&coreAppCode&address2&address1&city"; 
	var stringToArray = new Array;
	stringToArray = s.split("&");
	for ( var int = 0; int < stringToArray.length; int++) {		
		var obj=stringToArray[int]
		document.getElementById(""+obj).value="";
	}
}
function userCustomerSelection(frm){
	var agencyCode='';	
	if(frm.agencyCode){			
		agencyCode=frm.agencyCode.value;
		alert(agencyCode);		
	}
	alert(frm.agencyCode);	
	var URL ='${pageContext.request.contextPath}/customerSelectionCustomer.action?agencyCode='+agencyCode;
	alert(URL);
	//return popUp(URL,'779','537');
	return "";
}

function customerSelectionAction(frm) {	
	var brokerCode='';	
	if(frm.brokerCode){			
			brokerCode=frm.brokerCode.value;		
	}
	var URL ='${pageContext.request.contextPath}/customerSelectionCustomer.action?brokerCode='+brokerCode;
	return popUp(URL,'779','537');
}
	
function coreCustomerSearch() {
	var URL ='${pageContext.request.contextPath}/coreCustSearchCustomer.action';
	return popUp(URL,'700','500');
}

function toggleCompanyRegistration(val) {
	if(val=="CORPORATE") {
		document.getElementById('companyRegNo').disabled = false;
	}
	else {
		document.getElementById('companyRegNo').value = "";
		document.getElementById('companyRegNo').disabled = true;
	}
}
function  empyCustDetails() {
	$('.empyCustDetails').val('');
}
</script>	
</body>
</html>