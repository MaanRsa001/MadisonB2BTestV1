<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ page import="java.util.List,java.util.ArrayList,java.util.Map,java.util.HashMap,java.util.Collections"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html <s:if test="locale.language == 'ar'">dir="rtl"</s:if>>
<head>
<sj:head jqueryui="true" jquerytheme="start" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Form</title>
<link href="css/styles.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/cssbootstrap/reset.css" rel="stylesheet" type="text/css" />
<link href="css/styles.css" rel="stylesheet" type="text/css" />			
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/cssbootstrap/bootstrap.min.css" />
<link href="<%=request.getContextPath()%>/cssbootstrap/bootstrap.icon-large.min.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/cssbootstrap/footable-0.1.css" rel="stylesheet" type="text/css" />	
<link href="<%=request.getContextPath()%>/cssbootstrap/style.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/cssbootstrap/table-res.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/common.js"></script>
<script language="JavaScript">javascript:window.history.forward(1);</script>
<script language="JavaScript">
		function stopRKey(evt) { 
		  var evt = (evt) ? evt : ((event) ? event : null); 
		  var node = (evt.target) ? evt.target : ((evt.srcElement) ? evt.srcElement : null); 
		  if ((evt.keyCode == 13) && (node.type=="text"))  {return false;} 
		} 
		document.onkeypress = stopRKey; 
</script>
<style type="text/css">
.v50l {
	width: 50%;
	height:auto;
	float: left;
	text-align: center;
	vertical-align: middle;
}
.v50r {
	width: 50%;
	height:auto;
	float: left;
	text-align: right;
	vertical-align: middle;
}
.tableHeading {
    background: #0078ae url(images/ui-bg_glass_45_0078ae_1x400.png) 50% 50% repeat-x;
    color: #ffffff;
    font-weight: bold;
    font-size: 14px;
}
</style>
</head>

<body>
<div class="table0">
	<div class="tablerow">
		<s:if test="'newQuote'.equalsIgnoreCase(display)">
        <s:set var="disable1" value="%{((endTypeId!=null && endTypeId.length() > 0)||(quoteStatus=='RA'))}"/>
        <s:form id="motor" name="motor" method="post" theme="simple" action="getQuoteMotor.action">
		<div class="table1">
			<div class="tablerow">
				<span style="color:red;"><s:actionerror/></span>
			</div>
			<div class="tablerow">
				<s:include value="/pages/customer/custDetails.jsp"></s:include>
			</div>
			<div class="tablerow">
				<div class="panel panel-primary">
        			<div class="panel-heading txtB">
        				<s:text name="motor.vehicleDetails" />
        				<sj:dialog id="helpInfo_vehicleDetails" title='%{getText("motor.vehicleDetails")}' autoOpen="false" modal="true" showEffect="explode" hideEffect="explode"/>
						<sj:a openDialog="helpInfo_vehicleDetails" href="getHelpInfoMotor.action?helpType=VEHICLE_DETAILS"><img src="${pageContext.request.contextPath}/images/Help.png" width="20" height="20"/></sj:a>
        			</div>
        			<div class="panel-body">
        				<div class="boxcontent">
        					<div class="textfield33">
        						<div class="text">
        							<s:text name="motor.make" /><font color="red">*</font>												
								</div>
								<div class="tbox">
									<s:select name="make" id="make" list="makeList" listKey="CODE" listValue="CODEDESC" headerKey="" headerValue="-Select-" onchange="getAjaxModel(this.form,'?make='+this.value,'modelList')" cssClass="inputBoxS" disabled="#disable1"/>
								</div>
        					</div>
        					<div class="textfield33">
        						<div class="text">
        							<s:text name="motor.model" /><font color="red">*</font>												
								</div>
								<div class="tbox" id="modelList">
									<s:select name="model" id="model" list="modelList" listKey="CODE" listValue="CODEDESC" headerKey="" headerValue="-Select-" cssClass="inputBoxS" disabled="#disable1"/>
								</div> 
        					</div>
        					<div class="textfield33">
        						<div class="text">
        							<s:text name="motor.typeOfBody" /><font color="red">*</font>												
								</div>
								<div class="tbox">
									<s:select name="typeBody" id="typeBody" list="typeBodyList" listKey="CODE" listValue="CODEDESC" headerKey="" headerValue="-Select-" cssClass="inputBoxS" onchange="getAjaxModel(this.form,'?typeBody='+this.value,'noOfCylinderList')" disabled="#disable1" />
								</div>
        					</div>
        					<div class="textfield33">
        						<div class="text">
        							<s:text name="motor.mfgYr" /><font color="red">*</font>												
								</div>
								<div class="tbox">
									<s:select name="mfgYr"  id="mfgYr" list="mfgYrMap" headerKey="" headerValue="-Select-" cssClass="inputBoxS" disabled="#disable1"/>
								</div>
        					</div>
        					<div class="textfield33">
        						<div class="text">
        							<s:text name="motor.sumInsured" /><font color="red">*</font>												
								</div>
								<div class="tbox">
									<s:textfield name="sumInsured" id="sumInsured" cssStyle="width:85%; float: left;" cssClass="inputBox" maxLength="13" onkeyup="checkDecimals(this);" disabled="#disable1"/>&nbsp;<span style="float:left;"><s:property value="#session.BrokerDetails.CurrencyAbb"/></span>
								</div>
        					</div>
        					<div class="textfield33">
        						<div class="text">
        							<s:text name="motor.cubicCapacity" /><font color="red">*</font>												
								</div>
								<div class="tbox">
									<s:textfield name="cubicCapacity" id="cubicCapacity" cssClass="inputBox" maxLength="13" onkeyup="checkDecimals(this);" disabled="#disable1"/>
								</div>
        					</div>
        					<div class="textfield33">
        						<div class="text">
        							<s:text name="motor.seatingCapacity" /><font color="red">*</font>												
								</div>
								<div class="tbox">
									<s:textfield name="seatingCapacity" id="seatingCapacity" cssClass="inputBox" size="7" cssClass="input" maxLength="5" onkeyup="checkNumbers(this);" disabled="#disable1"/>&nbsp;&nbsp;<s:text name="motor.includingDriver"/>
								</div>
        					</div>
        					<div class="textfield33">
        						<div class="text">
        							<s:text name="motor.noOfCylinder" /><font color="red">*</font>												
								</div>
								<div class="tbox" id="noOfCylinderList">
									<s:select name="noOfCylinder" id="noOfCylinder" list="noOfCylinderList" listKey="CODE" listValue="CODEDESC" headerKey="" headerValue="-Select-" cssClass="inputBox" disabled="#disable1"/>
								</div>
        					</div>
        					<div class="textfield33">
        						<div class="text">
        							<s:text name="motor.vehicleUsage" /><font color="red">*</font>												
								</div>
								<div class="tbox">
									<s:select name="vehicleUsage"  id="vehicleUsage" list="vehicleUsageList" listKey="CODE" listValue="CODEDESC" headerKey="" headerValue="-Select-" cssClass="inputBoxS" disabled="#disable1"/>
								</div>
        					</div>
        					<div class="textfield33">
        						<div class="text">
        							<s:text name="motor.areaCoverage" /><font color="red">*</font>												
								</div>
								<div class="tbox">
									<s:select name="areaCoverage" list="areaCoverageList" listKey="CODE" listValue="CODEDESC" headerKey="" headerValue="-Select-" cssClass="inputBoxS" disabled="#disable1" />
								</div>
        					</div>
        					<div class="textfield33">
        						<div class="text">
        							<s:text name="motor.agencyRepair" /><font color="red">*</font>												
								</div>
								<div class="tbox">
									<s:radio name="agencyRepairYN" id="agencyRepairYN" list="#{'Y':'Yes','N':'No'}" value="%{agencyRepairYN==null?'N':agencyRepairYN}" cssClass="input" disabled="#disable1"/>
								</div>
        					</div>        					
        					<br class="clear"/>
        				</div>
        			</div>
        		</div>
			</div>
			<div class="tablerow">
				<div class="panel panel-primary">
        			<div class="panel-heading txtB">
        				<s:text name="motor.driverDetails" />
        				<sj:dialog id="helpInfo_driverDetails" title='%{getText("motor.driverDetails")}' autoOpen="false" modal="true" showEffect="explode" hideEffect="explode"/>
						<sj:a openDialog="helpInfo_driverDetails" href="getHelpInfoMotor.action?helpType=DRIVER_DETAILS"><img src="${pageContext.request.contextPath}/images/Help.png" width="20" height="20"/></sj:a>
        			</div>
        			<div class="panel-body">
        				<div class="boxcontent">
        					<div class="textfield33">
        						<div class="text">
        							<s:text name="motor.driverDOB" /><font color="red">*</font>												
								</div>
								<div class="tbox">
									<div class="inputAppend">
										<sj:datepicker name="driverDOB" id="driverDOB" displayFormat="dd/mm/yy" minDate="-100Y" maxDate="-1D" readonly="true" disabled="#disable1" cssClass="inputBox1" cssStyle="border: none;background: transparent; " changeMonth="true" changeYear="true" showAnim="slideDown" duration="fast" yearRange="c-100:c+100"  disabled="#disable1"/>
									</div>
								</div>
        					</div>
        					<div class="textfield33">
        						<div class="text">
        							<s:text name="motor.driverNationality" /><font color="red">*</font>												
								</div>
								<div class="tbox">
									<s:select name="driverNationality" id="driverNationality" list="driverNationalityList" listKey="CODEVALUE" listValue="CODEDESC" headerKey="" headerValue="-Select-" cssClass="inputBoxS" disabled="#disable1"/>
								</div>
        					</div>
        					<div class="textfield33">
        						<div class="text">
        							<s:text name="motor.uaeLicenceNo" />												
								</div>
								<div class="tbox">
									<s:textfield name="uaeLicenceNo" id="uaeLicenceNo" cssClass="inputBox" maxLength="5" onkeyup="checkNumbers(this);" disabled="#disable1"/>
								</div>
        					</div>
        					<div class="textfield33">
        						<div class="text">
        							<s:text name="motor.uaeLicenceExpDT" />												
								</div>
								<div class="tbox">
									<div class="inputAppend">
										<sj:datepicker name="uaeLicExpDT" id="uaeLicExpDT" cssClass="inputBox1" cssStyle="border: none;background: transparent; " displayFormat="dd/mm/yy" minDate="0D" maxDate="30Y" readonly="true" disabled="#disable1" changeMonth="true" changeYear="true" showAnim="slideDown" duration="fast" yearRange="c-100:c+100"  disabled="#disable1"/>
									</div>
								</div>
        					</div>        					
        					<br class="clear"/>
        				</div>
        			</div>
        		</div>
        	</div>
        	<div class="tablerow">
				<div class="panel panel-primary">
        			<div class="panel-heading txtB">
        				<s:text name="motor.driverClaimDetails" />
        				<sj:dialog id="helpInfo_driverClaimDetails" title='%{getText("motor.driverClaimDetails")}' autoOpen="false" modal="true" showEffect="explode" hideEffect="explode"/>
						<sj:a openDialog="helpInfo_driverClaimDetails" href="getHelpInfoMotor.action?helpType=DRIVER_CLAIM_DETAILS"><img src="${pageContext.request.contextPath}/images/Help.png" width="20" height="20"/></sj:a>
        			</div>
        			<div class="panel-body">
        				<div class="boxcontent">
        					<div class="textfield33">
        						<div class="text">
        							<s:text name="motor.noClaimBonus" /><font color="red">*</font>												
								</div>
								<div class="tbox">
									<s:select name="noClaimBonus" id="noClaimBonus" list="noClaimBonusList" headerKey="" headerValue="-Select-" cssClass="inputBoxS" disabled="#disable1"/>
								</div>
        					</div>
        					<div class="textfield33">
        						<div class="text">
        							<s:text name="motor.driverClaimMadeYN" /><font color="red">*</font>												
								</div>
								<div class="tbox">
									<s:radio name="claimYN" id="claimYN" list="#{'Y':'Yes','N':'No'}" value="%{claimYN==null?'N':claimYN}" cssClass="input" onclick="toggleClaimDetails(this.value);" disabled="#disable1"/>
								</div>
        					</div>
        					<div class="textfield33">
        						<div class="text">
        							<s:text name="motor.claimAmount" /><font color="red">*</font>												
								</div>
								<div class="tbox">
									<s:textfield name="claimAmount" id="claimAmount" cssClass="inputBox" maxLength="8" onkeyup="checkDecimals(this);" disabled="#disable1"/>
								</div>
        					</div>        					
        					<br class="clear"/>
        					<br class="clear"/>
        				</div>
        			</div>
        		</div>
        	</div>
        	<div class="tablerow">
				<div class="panel panel-primary">
        			<div class="panel-heading txtB">
        				<s:text name="motor.additionalVehicleDetails" />
        				<sj:dialog id="helpInfo_addVehicleDetails" title='%{getText("motor.additionalVehicleDetails")}' autoOpen="false" modal="true" showEffect="explode" hideEffect="explode"/>
						<sj:a openDialog="helpInfo_addVehicleDetails" href="getHelpInfoMotor.action?helpType=ADDITIONAL_VEHICLE_DETAILS"><img src="${pageContext.request.contextPath}/images/Help.png" width="20" height="20"/></sj:a>
        			</div>
        			<div class="panel-body">
        				<div class="boxcontent">
        					<div class="textfield33">
        						<div class="text">
        							<s:text name="motor.regNo" />												
								</div>
								<div class="tbox">
									<s:textfield name="regNo" id="regNo" cssClass="inputBox" maxLength="20"/>
								</div>
        					</div>
        					<div class="textfield33">
        						<div class="text">
        							<s:text name="motor.chassisNo" />												
								</div>
								<div class="tbox">
									<s:textfield name="chassisNo" id="chassisNo" cssClass="inputBox" maxLength="20"/>
								</div>
        					</div>
        					<div class="textfield33">
        						<div class="text">
        							<s:text name="motor.engineNo" />												
								</div>
								<div class="tbox">
									<s:textfield name="engineNo" id="engineNo" cssClass="inputBox" maxLength="20"/>
								</div>
        					</div>
        					<div class="textfield33">
        						<div class="text">
        							<s:text name="motor.vehicleColour" />												
								</div>
								<div class="tbox">
									<s:select name="vehicleColour" id="vehicleColour" list="vehicleColourList" listKey="CODE" listValue="CODEDESC" headerKey="" headerValue="-Select-" cssClass="inputBoxS"/>								
								</div>
        					</div>
        					<div class="textfield33">
        						<div class="text">
        							<s:text name="motor.vehicleRegLoc" />												
								</div>
								<div class="tbox">
									<s:select name="vehicleRegLoc" id="vehicleRegLoc" list="cityList" listKey="CODE" listValue="CODEDESC" headerKey="" headerValue="-Select-" cssClass="inputBoxS"/>								
								</div>
        					</div>
        					<div class="textfield33">
        						<div class="text">
        							<s:text name="motor.plateCharacter" />												
								</div>
								<div class="tbox">
									<s:select name="plateCharacter" id="plateCharacter" list="plateCharList" listKey="CODE" listValue="CODEDESC" headerKey="" headerValue="-Select-" cssClass="inputBoxS"/>
								</div>
							</div>
        					<div class="textfield33">
        						<div class="text">
        							<s:text name="motor.leasedYN" /><font color="red">*</font>												
								</div>
								<div class="tbox">
									<s:radio name="leasedYN" list="#{'Y':'Yes','N':'No'}" value="%{leasedYN==null?'N':leasedYN}" cssClass="input" onclick="toggleLeasedYN(this.value);"/>								
								</div>
        					</div>
        					<div class="textfield33">
        						<div class="text">
        							<s:text name="motor.bankOfFinance" /><font color="red">*</font>												
								</div>
								<div class="tbox">
									<s:select name="bankOfFinance" id="bankOfFinance" list="bankOfFinanceList" listKey="CODE" listValue="CODEDESC" headerKey="" headerValue="-Select-" cssClass="inputBoxS"/>
								</div>
        					</div>
        					<div class="textfield33">
        						<div class="text">
        							<s:text name="motor.insuredNameAra" />												
								</div>
								<div class="tbox">
									<s:textfield name="insNameArabic" id="insNameArabic" cssClass="inputBox"/>								
								</div>
        					</div>
        					<div class="textfield33" style="height: 75px;">
        						<div class="text">
        							<s:text name="motor.insuredAddressAra" />												
								</div>
								<div class="tbox">
									<s:textarea name="insAddressArabic" id="insAddressArabic" cssClass="inputBoxA" onkeyup="textLimit(this,'200')" cssStyle="width: 90%;" />								
								</div>
        					</div>
        					<br class="clear"/>
        				</div>
        			</div>
        		</div>
        	</div>
        	<div class="tablerow">
				<div class="panel panel-primary">
        			<div class="panel-heading txtB">
        				<s:text name="motor.coverStartDt" />
        			</div>
        			<div class="panel-body">
        				<div class="boxcontent">
        					<div class="textfield33">
        						<div class="text">
        							<s:text name="motor.policyStartDt"/><font color="red">*</font>											
								</div>
								<div class="tbox">
									<div class="inputAppend">
										<sj:datepicker name="policyStartDate" id="policyStartDate" cssClass="inputBox1" cssStyle="border: none;background: transparent; " displayFormat="dd/mm/yy" minDate="-%{backDt}D" maxDate="+12M" readonly="true"  disabled="#disable1" changeMonth="true" changeYear="true" showAnim="slideDown" duration="fast"/>
									</div>								
								</div>
        					</div>
        					<%--onchange="getPolicyEndDate(this.value)"--%>
        					<%--<s:text name="motor.policyEndDt" /><font color="red">*</font><br/>
							<s:textfield name="policyEndDate" id="policyEndDate" cssClass="input" readonly="true"/>--%>
        					<br class="clear"/>
        				</div>
        			</div>
        		</div>
        	</div>
        	<div class="tablerow" align="center">
        		<s:if test='#session.LoginType != "B2C"'> 
        			<s:submit type="button1" name="close"  key="Back" cssClass="btn btn-sm btn-danger" onclick="getBack('home')"/>
        		</s:if>
        		&nbsp;&nbsp;&nbsp;
              	<%--<s:if test='#session.user1 != "admin"'> 
              		<s:submit name="Submit2" type="submit" cssClass="btn" value="Save" onclick="this.form.actionType.value='getSave';" />
              	</s:if>--%>
              	<s:submit name="Submit3" type="submit" cssClass="btn btn-sm btn-success" value="Proceed" onclick="this.form.actionType.value='getQuote';disableForm(this.form,false,'');"/>
              	<s:hidden name="display" />
                <s:hidden name="actionType" />
                <s:hidden name="fleetNo" />
                <s:hidden name="applicationNo" />
                <s:hidden name="quoteNo" />
                <s:hidden name="quoteStatus"/>
                <s:hidden name="referralMsg"/>
        	</div>
		</div>
		</s:form>
		</s:if>
		<s:if test="'newQuote'.equalsIgnoreCase(display)">
        	<s:set var="disable1" value="%{((endTypeId!=null && endTypeId.length() > 0)||(quoteStatus=='RA'))}"/>
        </s:if>
        <s:elseif test="'showPrSummary'.equalsIgnoreCase(display)">
        <s:form id="motor" name="motor" method="post" theme="simple" action="insertOptionCoverMotor.action">
        <div class="table1">
			<div class="tablerow">
				<span style="color:red;"><s:actionerror/></span>
			</div>			
			<div class="tablerow">
				<div class="panel panel-primary">
        			<div class="panel-heading txtB">
						<s:text name="motor.policyInfo" />
        			</div>
        			<div class="panel-body">
        				<div class="boxcontent">
        					<div class="textfield33v">
	       						<div class="textv">
	       							<s:text name="motor.quoteNo"  />
								</div>
								<div class="tboxv">												   
									 <s:property value="quoteNo"/>
								</div>
	       					</div>
	       					<div class="textfield33v">
	       						<div class="textv">
	       							<s:text name="motor.quoteDate" />
								</div>
								<div class="tboxv">												   
									 <s:property value="quoteDate"/>
								</div>
	       					</div>
	       					<div class="textfield33v">
	       						<div class="textv">
	       							<s:text name="motor.product" />
								</div>
								<div class="tboxv">												   
									 <s:property value="product"/>
								</div>
	       					</div>
	       					<div class="textfield33v">
	       						<div class="textv">
	       							<s:text name="motor.make"  />
								</div>
								<div class="tboxv">												   
									<s:property value="makeName"/>
								</div>
	       					</div>
	       					<div class="textfield33v">
	       						<div class="textv">
	       							<s:text name="motor.model" />
								</div>
								<div class="tboxv">												   
									<s:property value="modelName"/>
								</div>
	       					</div>
	       					<div class="textfield33v">
	       						<div class="textv">
	       							<s:text name="motor.typeOfBody" />
								</div>
								<div class="tboxv">												   
									<s:property value="typeBodyName"/>
								</div>
	       					</div>
	       					<div class="textfield33v">
	       						<div class="textv">
	       							<s:text name="motor.sumInsured"  />
								</div>
								<div class="tboxv">												   
									<s:property value="sumInsured"/> 
								</div>
	       					</div>
	       					<div class="textfield33v">
	       						<div class="textv">
	       							<s:text name="motor.vehicleUsage" />
								</div>
								<div class="tboxv">												   
									<s:property value="vehicleUsageName"/>
								</div>
	       					</div>
	       					<div class="textfield33v">
	       						<div class="textv">
	       							<s:text name="motor.agencyRepair" /> 
								</div>
								<div class="tboxv">												   
									<s:property value="agencyRepairName"/>
								</div>
	       					</div>
	       					<div class="textfield33v">
	       						<div class="textv">
	       							<s:text name="motor.customerName"  />
								</div>
								<div class="tboxv">												   
									<s:property value="customerName"/>
								</div>
	       					</div>
	       					<div class="textfield33v">
	       						<div class="textv">
	       							<s:text name="motor.email" />
								</div>
								<div class="tboxv">												   
									<s:property value="email"/>
								</div>
	       					</div>
	       					<div class="textfield33v">
	       						<div class="textv">
	       							<s:text name="motor.policyStartDt" />
								</div>
								<div class="tboxv">												   
									<s:property value="policyStartDate"/>
								</div>
	       					</div>
        					<br class="clear"/>
        				</div>
        			</div>
        		</div>
        	</div>
        	<div class="tablerow">
				<div class="panel panel-primary">
        			<div class="panel-heading txtB">
						<s:text name="motor.premiumInfo" />
        			</div>
        			<div class="panel-body">
        				<s:if test='referralMsg!=null && referralMsg.length()>0'>
        				<div class="tablerow" align="center">						
	                    		<font color="red" size="3">	
				 				<s:text name="motor.referralMessage"/> 
				 				<%--<s:property value="referralMsg"/>--%> 
				 				<s:text name="referralMsg"/>
				 				</font>
						</div>
						<br/>
						</s:if>
						
        				<div class="boxcontent" align="center">
        					<s:if test="%{#request.comparisionDetails.get('policyType') !=null && #request.comparisionDetails.get('policyType').size()>0}">
			                <s:iterator value="%{#request.comparisionDetails.get('policyType')}" var="polType" status="type">
			                <div class="panel panel-primary preBlock">			        						
								<div class="panel-heading" id="preDetails1">
									<span class="pullLeft">									
										<span class="icon-large icon-car"></span> &nbsp; <s:property value="X_DATA_NAME"/>
									</span>									
									<span class="pullRight" style="color: #ffff66; font-size: 15px;">
										<s:property value="#session.BrokerDetails.CurrencyAbb"/>
										<input style="visibility: hidden;" type="radio" <s:property  value="%{(#request.comparisionDetails.get('selectedPolicyType') == #polType.X_ID)?'checked':''}"/> name="policyType" id="policyType${X_ID}" value="${X_ID}" />
										 <span id="totaldisplay${X_ID}" style="vertical-align: middle;"></span>
									</span>
									<div class="clear"></div>			        							
								</div>
								<div class="panel-body" id="preDetailsData1" style="padding: 0px">									
									<div class="tableRow">
									<table width="100%" class="premiumTable" cellspacing="5">
										<tbody>
										<s:if test="%{#request.comparisionDetails.get('policyGroup') !=null && #request.comparisionDetails.get('policyGroup').size()>0}">
										</s:if>
										<s:set var="count" value="0"/>
										<s:iterator value="%{#request.comparisionDetails.get('policyGroup')}" var="polGroup" status="group">
											<s:iterator value="%{#request.comparisionDetails.get('policyCovers'+#polGroup.GROUP_ID)}" var="covers" status="covStat">
												<s:set var="coverDetails" value="%{#request.comparisionDetails.get('policyCoverDetails'+#polGroup.GROUP_ID+#polType.X_ID+#covers.Y_DATA_NAME)}"/>
												<s:set var='img' value='""'/>
												<s:set var='alt' value='""'/>
												<s:set var='cursor' value='""'/>
												<s:set var='disp' value='""'/>
												<s:set var='optCovId' value='""'/>
												<s:if test='"Y" == #coverDetails[0].IS_SELECTED'>
													<s:set var='img' value='"/images/selected_cover.png"'/>
													<s:set var='alt' value='"Default Cover"'/>
													<s:set var='cursor' value='"default"'/>
													<s:set var='optCovId' value='%{#coverDetails[0].Y_ID}'/>
													<s:set var='preVal' value='#coverDetails[0].DATA_VALUE'/>
													<s:if test='"Y" == #coverDetails[0].DELETABLE'>
														<s:set var='disp' value='"inline"'/>
													</s:if>
													<s:else>
														<s:set var='disp' value='"none"'/>
													</s:else>
												 </s:if>
												 <s:else>
													<s:if test='%{ "Y" == #coverDetails[0].IS_ADDON}'>
														<s:if test="%{ #request.comparisionDetails.get('selectedPolicyType').equalsIgnoreCase(#polType.X_ID) && optCovers.contains((#coverDetails[0].Y_ID +'~'))}">
															<s:set var='img' value='"/images/selected_cover.png"'/>
															<s:set var='alt' value='"Default Cover"'/>
															<s:set var='cursor' value='"pointer"'/>
															<s:set var='disp' value='"inline"'/>
															<s:set var='optCovId' value='%{#coverDetails[0].Y_ID}'/>
															<s:set var='preVal' value='#coverDetails[0].DATA_VALUE'/>
														</s:if>
														<s:else>
															<s:set var='img' value='"/images/add_cover.png"'/>
															<s:set var='alt' value='"Add Cover"'/>
															<s:set var='cursor' value='"pointer"'/>
															<s:set var='disp' value='"none"'/>
															<s:set var='preVal' value='0'/>
															<s:set var='optCovId' value=''/>
														</s:else>
													</s:if>
												 </s:else>
												 <s:if test='#covStat.index == "0"'>
												 <tr>
												 	<td width="10%"></td>
													<td width="60%">
														<span class="preText"><b><s:property value="GROUP_DESC_ENGLISH"/></b></span>														
													</td>
													<td width="30%" align="right">
														<s:if test='#polGroup.GROUP_ID == "0"'>
															<s:if test='referralMsg==null || referralMsg==""'>
																<s:property value="#coverDetails[0].DATA_VALUE"/>
															</s:if>
															<s:else>
																Referral
															</s:else>
															<input type="hidden" name="coverRate${X_ID}" id="coverRate${X_ID}" value="${RATE}" />
															<input type="hidden" name="total${X_ID}" id="total${X_ID}" value="#coverDetails[0].DATA_VALUE" readonly="readonly"/>
															<input type="hidden" name="default<s:property value="#polType.X_ID"/>" value="<s:property value='#coverDetails[0].DATA_VALUE'/>" />
															<input   style="position: absolute;visibility:hidden"  type="checkbox" checked="checked" name="optionalCovers<s:property value="#polType.X_ID"/>" id="optionalCovers<s:property value="#polType.X_ID"/>" value='<s:property value="#optCovId"/>'/>
															<s:set var="count" value="%{@java.lang.Integer@parseInt(#count)+1}"/>
														</s:if>
													</td>
												 </tr>
												 </s:if>
												 <s:if test="%{#coverDetails[0].DESCRIPTION !=null && #coverDetails[0].DESCRIPTION.length() >0 && (#coverDetails[0].DESCRIPTION.indexOf('~') !=-1)}">
												 <s:iterator value="%{#coverDetails[0].DESCRIPTION.split('~')}" var="covDesc" status="desc">
												 <tr>
												 	<td width="10%"></td>
												 	<td width="60%">
												 		<img name='firstImg<s:property value="#polGroup.GROUP_ID"/><s:property value="#polType.X_ID"/><s:property value="#coverDetails[0].Y_ID"/>' id='firstImg<s:property value="#polGroup.GROUP_ID"/><s:property value="#polType.X_ID"/><s:property value="#coverDetails[0].Y_ID"/>' src='<s:url value="%{#img}"/>' style="cursor: <s:property value='#cursor'/>;" alt="%{#alt}" height="24" width="24" onclick="return addCover(this,'<s:property value="#polType.X_ID"/>','<s:property value="#coverDetails[0].Y_ID"/>','<s:property value="%{#count}" />','<s:property value="#coverDetails[0].DATA_VALUE"/>','<s:property value="#polGroup.GROUP_ID"/><s:property value="#polType.X_ID"/><s:property value="#coverDetails[0].Y_ID"/>');"/>													<s:property/>
														<img name='secondImg<s:property value="#polGroup.GROUP_ID"/><s:property value="#polType.X_ID"/><s:property value="#coverDetails[0].Y_ID"/>' id='secondImg<s:property value="#polGroup.GROUP_ID"/><s:property value="#polType.X_ID"/><s:property value="#coverDetails[0].Y_ID"/>' src="<s:url value="/images/delete_cover.png"/>"  style="cursor: pointer; display:<s:property value='%{#disp}'/>;" alt="Remove Cover" height="24" width="24" onclick="return deleteCover(this,'<s:property value="#polType.X_ID"/>','<s:property value="#coverDetails[0].Y_ID"/>','<s:property value="%{#count}" />','<s:property value="#coverDetails[0].DATA_VALUE"/>','<s:property value="#polGroup.GROUP_ID"/>'+'<s:property value="#polType.X_ID"/>'+'<s:property value="#coverDetails[0].Y_ID"/>');" />
												 	</td>
												 	<td width="30%">												 		
												 	</td>
												 </tr>
												 </s:iterator>
												 </s:if>
												 <s:elseif test="%{#coverDetails[0].DESCRIPTION!=null && #coverDetails[0].DESCRIPTION!=''}">
												 <tr>
												 	<td width="10%"></td>
												 	<td width="60%">
												 		<img name='firstImg<s:property value="#polGroup.GROUP_ID"/><s:property value="#polType.X_ID"/><s:property value="#coverDetails[0].Y_ID"/>' id='firstImg<s:property value="#polGroup.GROUP_ID"/><s:property value="#polType.X_ID"/><s:property value="#coverDetails[0].Y_ID"/>' src='<s:url value="%{#img}"/>' style="cursor: <s:property value='#cursor'/>;" alt="%{#alt}" height="24" width="24" onclick="return addCover(this,'<s:property value="#polType.X_ID"/>','<s:property value="#coverDetails[0].Y_ID"/>','<s:property value="%{#count}" />','<s:property value="#coverDetails[0].DATA_VALUE"/>','<s:property value="#polGroup.GROUP_ID"/><s:property value="#polType.X_ID"/><s:property value="#coverDetails[0].Y_ID"/>');"/>
														 <s:property value="#coverDetails[0].DESCRIPTION"/>
														 <img name='secondImg<s:property value="#polGroup.GROUP_ID"/><s:property value="#polType.X_ID"/><s:property value="#coverDetails[0].Y_ID"/>' id='secondImg<s:property value="#polGroup.GROUP_ID"/><s:property value="#polType.X_ID"/><s:property value="#coverDetails[0].Y_ID"/>' src="<s:url value="/images/delete_cover.png"/>"  style="cursor: pointer; display:<s:property value='%{#disp}'/>;" alt="Remove Cover" height="24" width="24" onclick="return deleteCover(this,'<s:property value="#polType.X_ID"/>','<s:property value="#coverDetails[0].Y_ID"/>','<s:property value="%{#count}" />','<s:property value="#coverDetails[0].DATA_VALUE"/>','<s:property value="#polGroup.GROUP_ID"/><s:property value="#polType.X_ID"/><s:property value="#coverDetails[0].Y_ID"/>');" />
												 	</td>
												 	<td width="30%">
												 		<s:if test='#polGroup.GROUP_ID != "0"'>
															<input type="hidden" name="default<s:property value="#polType.X_ID"/>" value="<s:property value='#preVal'/>" />
															<input   style="position: absolute;visibility:hidden"  type="checkbox" checked="checked" name="optionalCovers<s:property value="#polType.X_ID"/>" id="optionalCovers<s:property value="#polType.X_ID"/>" value='<s:property value="#optCovId"/>' />
															<s:set var="count" value="%{@java.lang.Integer@parseInt(#count)+1}"/>
															<s:if test='referralMsg==null || referralMsg==""'>
																<s:property value="#coverDetails[0].DATA_VALUE"/>
															</s:if>
															<s:else>
																Referral
															</s:else>
														</s:if>
												 	</td>
												 </tr>
												 </s:elseif>
											</s:iterator>
										</s:iterator> 
										</tbody>
									</table>
									<br/>
									<div align="center">
										<input type="button"  class="btn btn-sm btn-warning"  name="tdCovers" value='<s:property value='%{#session.user1 != "admin" ? "Buy Policy" : "Approve Quote"}'/>' onclick="SubmitAction('<s:property value="%{quoteNo}"/>','<s:property value="%{applicationNo}"/>','<s:property value="%{typeBody}"/>','false','<s:property value="#polType.X_ID"/>');" />
									</div>	
									</div>
								</div>
							</div>
			                </s:iterator>
		                    </s:if>
		                    <s:else>
		                    		<table align="center" cellpadding="0" cellspacing="0">
										<tr>
											<td height="32" align="center" valign="middle">
								 				<font color="red" size="3">	
								 					<s:text name="motor.quotationInfo"/>
								 				</font>
											</td>
										</tr>
									</table>
		                    </s:else>
		                    
							<s:hidden name="sumInsured"/>
								 <s:hidden name="seatingCapacity"/>
								 <s:hidden name="driverDOB"/>
								 <s:hidden name="uaeLicenceNo"/>
								 <s:hidden name="noOfCylinder"/>
								 <s:hidden name="agencyRepairYN"/>
								 <s:hidden name="mfgYr"/>
								 <s:hidden name="vehicleUsage"/>
								 <s:hidden name="model"/>
								 <s:hidden name="applicationNo"/>
								 <s:hidden name="quoteNo"/>
								 <s:hidden name="typeBody"/>
								 <s:hidden name="quoteStatus"/>
								 <s:hidden name="referralMsg"/>
							<br class="clear"/>
							<div align="center">
		                    	<s:submit type="button" name="close"  key="Back" cssClass="btn btn-sm btn-danger" onclick="getBack('newQuote')"/>
		                    </div>							
        				</div>        				
        			</div>
        		</div>
        	</div>
        </div>
        </s:form>
        </s:elseif>
        <s:elseif test='"editCovRate".equalsIgnoreCase(display)'>
        <s:form id="motor" name="motor" method="post" theme="simple" action="updateCovRateMotor.action">
        <div class="table1">
			<div class="tablerow">
				<span style="color:red;"><s:actionerror/></span>
			</div>			
			<div class="tablerow">
				<div class="panel panel-primary">
        			<div class="panel-heading txtB">
						<s:text name="motor.policyInfo" />
        			</div>
        			<div class="panel-body">
        				<div class="boxcontent">
        					<div class="textfield33v">
	       						<div class="textv">
	       							<s:text name="motor.quoteNo"  />
								</div>
								<div class="tboxv">												   
									 <s:property value="quoteNo"/>
								</div>
	       					</div>
	       					<div class="textfield33v">
	       						<div class="textv">
	       							<s:text name="motor.quoteDate" />
								</div>
								<div class="tboxv">												   
									 <s:property value="quoteDate"/>
								</div>
	       					</div>
	       					<div class="textfield33v">
	       						<div class="textv">
	       							<s:text name="motor.product" />
								</div>
								<div class="tboxv">												   
									 <s:property value="product"/>
								</div>
	       					</div>
	       					<div class="textfield33v">
	       						<div class="textv">
	       							<s:text name="motor.make"  />
								</div>
								<div class="tboxv">												   
									<s:property value="makeName"/>
								</div>
	       					</div>
	       					<div class="textfield33v">
	       						<div class="textv">
	       							<s:text name="motor.model" />
								</div>
								<div class="tboxv">												   
									<s:property value="modelName"/>
								</div>
	       					</div>
	       					<div class="textfield33v">
	       						<div class="textv">
	       							<s:text name="motor.typeOfBody" />
								</div>
								<div class="tboxv">												   
									<s:property value="typeBodyName"/>
								</div>
	       					</div>
	       					<div class="textfield33v">
	       						<div class="textv">
	       							<s:text name="motor.sumInsured"  />
								</div>
								<div class="tboxv">												   
									<s:property value="sumInsured"/> 
								</div>
	       					</div>
	       					<div class="textfield33v">
	       						<div class="textv">
	       							<s:text name="motor.vehicleUsage" />
								</div>
								<div class="tboxv">												   
									<s:property value="vehicleUsageName"/>
								</div>
	       					</div>
	       					<div class="textfield33v">
	       						<div class="textv">
	       							<s:text name="motor.agencyRepair" /> 
								</div>
								<div class="tboxv">												   
									<s:property value="agencyRepairName"/>
								</div>
	       					</div>
	       					<div class="textfield33v">
	       						<div class="textv">
	       							<s:text name="motor.customerName"  />
								</div>
								<div class="tboxv">												   
									<s:property value="customerName"/>
								</div>
	       					</div>
	       					<div class="textfield33v">
	       						<div class="textv">
	       							<s:text name="motor.email" />
								</div>
								<div class="tboxv">												   
									<s:property value="email"/>
								</div>
	       					</div>
	       					<div class="textfield33v">
	       						<div class="textv">
	       							<s:text name="motor.policyStartDt" />
								</div>
								<div class="tboxv">												   
									<s:property value="policyStartDate"/>
								</div>
	       					</div>
        					<br class="clear"/>
        				</div>
	       			</div>
	       		</div>
	      	</div>
	      	<s:if test="premiumInfo.size()>0">
	      	<div class="tablerow">
	      		<div class="panel panel-primary">
        			<div class="panel-heading txtB">
						<s:text name="motor.premiumInfo"/>
        			</div>
        			<div class="panel-body">
        				<div class="boxcontent">
        					<table class="footable" width="100%">
        						<s:set name="groupId" value=""/>
			                    <s:set var="preAmt" value="0.0" scope="page"/>
			                    <s:iterator value="premiumInfo" var="prInfo" status="stat">
        						<thead>        						
			                    <s:if test="%{#groupId != #prInfo.GROUP_ID}">
			                    <s:set name="groupId" value="%{#prInfo.GROUP_ID}"/>
			                    <tr>
			                    	<th colspan="5"><s:property  value="%{#prInfo.GROUP_DESC_ENGLISH}"/></th>
			                    </tr>
			                    </s:if>
        						</thead>
        						<tbody>
        						<tr>
        							<td><s:hidden name="coverId[%{#stat.count-1}]" value="%{#prInfo.POLICYTYPE_COVERID}"/></td>
        							<td><s:property  value="%{#prInfo.OPCOVER_DESC_ENGLISH}"/></td>
        							<td>
        								<s:property value="getText('{0,number,#,##0.00}',{#prInfo.SUM_INSURED})"/>
	                             		<s:hidden name="sI[%{#stat.count-1}]"  cssClass="input" value="%{#prInfo.SUM_INSURED}" cssStyle="text-align:right;"/>
        							</td>
        							<td>
        								<s:textfield name="baseRate[%{#stat.count-1}]" cssClass="inputBox1" value="%{#prInfo.RATE}" size="11" cssStyle="text-align:right;" maxLength="16" onkeyup="checkDecimals(this);" />
        							</td>
        							<td>
        								<s:property value="getText('{0,number,#,##0.00}',{#prInfo.PREMIUM})"/>
	                            		<s:set var="preAmt" value="%{@java.lang.Double@parseDouble(#attr.preAmt)+@java.lang.Double@parseDouble(#prInfo.PREMIUM)}" scope="page"/>
        							</td>
        						</tr>        						
        						</tbody>
        						</s:iterator>        						
        					</table>
        					<br class="clear"/>
        				</div>
        			</div>
        		</div>
	      	</div>
	      	</s:if>
	      	<div class="tablerow" style="margin-top: 10px;">
	      		<input type="button" name="Submit1" class="btn btn-sm btn-danger" value="Back" onclick="getBack('newQuote')"/> &nbsp;&nbsp;&nbsp;
	            <input type="button" name="Submit2" class="btn btn-sm btn-info" value="Calculate" onclick="this.form.actionType.value='getCalculate';this.form.submit();"/>&nbsp;&nbsp;&nbsp;
				<input type="button" name="Submit3" class="btn btn-sm btn-success" value="Proceed" onclick="this.form.actionType.value='getQuote';this.form.submit();"/>
				<s:hidden name="applicationNo"/>
		 		<s:hidden name="quoteNo"/>
		 		<s:hidden name="quoteStatus"/>
		 		<s:hidden name="referralMsg"/>
		 		<s:hidden name="actionType" />	      		
	      	</div>
		</div>
        </s:form>
        </s:elseif>
        <s:elseif test="'policyInfo'.equalsIgnoreCase(display)">
        <s:form id="motor" name="motor" method="post" theme="simple" action="getGeratePolicyMotor.action">
        <div class="table1">
			<div class="tablerow">
				<span style="color:red;"><s:actionerror/></span>
			</div>			
			<div class="tablerow">
				<div class="panel panel-primary">
        			<div class="panel-heading txtB">
						<s:text name="motor.policyInfo" />
        			</div>
        			<div class="panel-body">
        				<div class="boxcontent">
        					<div class="textfield33v">
	       						<div class="textv">
	       							<s:text name="motor.quoteNo"  />
								</div>
								<div class="tboxv">												   
									 <s:property value="quoteNo"/>
								</div>
	       					</div>
	       					<div class="textfield33v">
	       						<div class="textv">
	       							<s:text name="motor.quoteDate" />
								</div>
								<div class="tboxv">												   
									 <s:property value="quoteDate"/>
								</div>
	       					</div>
	       					<div class="textfield33v">
	       						<div class="textv">
	       							<s:text name="motor.product" />
								</div>
								<div class="tboxv">												   
									 <s:property value="product"/>
								</div>
	       					</div>
	       					<div class="textfield33v">
	       						<div class="textv">
	       							<s:text name="motor.make"  />
								</div>
								<div class="tboxv">												   
									<s:property value="makeName"/>
								</div>
	       					</div>
	       					<div class="textfield33v">
	       						<div class="textv">
	       							<s:text name="motor.model" />
								</div>
								<div class="tboxv">												   
									<s:property value="modelName"/>
								</div>
	       					</div>
	       					<div class="textfield33v">
	       						<div class="textv">
	       							<s:text name="motor.typeOfBody" />
								</div>
								<div class="tboxv">												   
									<s:property value="typeBodyName"/>
								</div>
	       					</div>
	       					<div class="textfield33v">
	       						<div class="textv">
	       							<s:text name="motor.sumInsured"  />
								</div>
								<div class="tboxv">												   
									<s:property value="sumInsured"/> 
								</div>
	       					</div>
	       					<div class="textfield33v">
	       						<div class="textv">
	       							<s:text name="motor.vehicleUsage" />
								</div>
								<div class="tboxv">												   
									<s:property value="vehicleUsageName"/>
								</div>
	       					</div>
	       					<div class="textfield33v">
	       						<div class="textv">
	       							<s:text name="motor.agencyRepair" /> 
								</div>
								<div class="tboxv">												   
									<s:property value="agencyRepairName"/>
								</div>
	       					</div>
	       					<div class="textfield33v">
	       						<div class="textv">
	       							<s:text name="motor.customerName"  />
								</div>
								<div class="tboxv">												   
									<s:property value="customerName"/>
								</div>
	       					</div>
	       					<div class="textfield33v">
	       						<div class="textv">
	       							<s:text name="motor.email" />
								</div>
								<div class="tboxv">												   
									<s:property value="email"/>
								</div>
	       					</div>
	       					<br class="clear"/>
	       				</div>
	       			</div>
	       		</div>
	       	</div>
	       	<s:if test="premiumInfo.size()>0">
	       	<div class="tablerow">
	       		<div class="panel panel-primary">
        			<div class="panel-heading txtB">
						<s:text name="motor.premiumInfo" />
        			</div>
        			<div class="panel-body">
        				<div class="boxcontent">
							<table width="100%" class="footable">
								<s:set name="groupId" value=""/>
								<s:set var="preAmt" value="0.0" scope="page"/>
								<s:iterator value="premiumInfo" var="prInfo" status="stat">
								<s:if test="%{#groupId != #prInfo.GROUP_ID}">
									<s:set name="groupId" value="%{#prInfo.GROUP_ID}"/>
									<thead>
									<tr>	     
								        <th colspan="5" ><s:property  value="%{#prInfo.GROUP_DESC_ENGLISH}"/></th>
									</tr>
									</thead>
								</s:if>
								<tbody>
								<tr>       
									<td align="center" width="10%">
										<s:hidden name="coverId[%{#stat.count-1}]" value="%{#prInfo.POLICYTYPE_COVERID}"/>
									</td>
									<td align="left" width="20%">
										<s:property  value="%{#prInfo.OPCOVER_DESC_ENGLISH}"/>
									</td>
									<td align="left" width="25%">
										<s:property value="getText('{0,number,#,##0.00}',{#prInfo.SUM_INSURED})"/>
										<s:hidden name="sI[%{#stat.count-1}]"  cssClass="input" value="%{#prInfo.SUM_INSURED}" cssStyle="text-align:right;"/>
									</td>
									<td align="left" width="25%">
										<s:textfield name="baseRate[%{#stat.count-1}]" cssClass="inputBox" value="%{#prInfo.RATE}" size="11" cssStyle="text-align:right; width:100%;" maxLength="16" onkeyup="checkDecimals(this);" readonly="true"/>
									</td>
									<td align="right" width="20%"> 
										<s:property value="getText('{0,number,#,##0.00}',{#prInfo.PREMIUM})"/>
										<s:set var="preAmt" value="%{@java.lang.Double@parseDouble(#attr.preAmt)+@java.lang.Double@parseDouble(#prInfo.PREMIUM)}" scope="page"/>
									</td>
								</tr>								
								</tbody>
							</s:iterator>
								<tbody>
								<tr>
									<td></td>
									<td></td>									
									<td></td>
									<td align="right"><s:text name="motor.Premium"/></td>
									<td><b>[<s:property value="#session.BrokerDetails.CurrencyAbb"/>] </b>&nbsp;&nbsp;&nbsp;
										<%-- 
										<s:textfield name="premium" id="premium" cssClass="input" value="%{#attr.preAmt}" onkeyup="checkDecimals(this);" maxlength="11" cssStyle="text-align:right;" readonly="true"/>
										--%>
										<s:textfield name="premium" id="premium" cssClass="input" value="%{getText('{0,number,0.00}',{#attr.preAmt})}" onkeyup="checkDecimals(this);" maxlength="11" cssStyle="text-align:right;" readonly="true"/>
									</td>
								</tr>
								<s:if test='#session.user1 == "admin" || quoteStatus == "RA"'>
								<tr>
									<td></td>
									<td></td>									
									<td></td>
									<td align="right"><s:label key="motor.loadingOrDiscountPremium"/>&nbsp;&nbsp;&nbsp;<s:select list="#{'+':'+','-':'-'}" name="sign" onchange="getTotalPremium(this.form)" disabled="%{quoteStatus=='RA' && #session.user1 != 'admin'?'true':'false'}"/></td>
									<td>
										<b>[<s:property value="#session.BrokerDetails.CurrencyAbb"/>]&nbsp;&nbsp;&nbsp;</b>
			                            <s:textfield name="loadOrDiscPremium" id="loadOrDiscPremium" cssClass="input" readonly="%{quoteStatus=='RA' && #session.user1 != 'admin'?'true':'false'}" onkeyup="checkDecimals(this);" maxlength="11" cssStyle="text-align:right;" onchange="getTotalPremium(this.form)"/>
			                            <s:if test='"+".equalsIgnoreCase(sign)'>
			                            	<s:set var="preAmt" value="%{@java.lang.Double@parseDouble(#attr.preAmt)+@java.lang.Double@parseDouble(loadOrDiscPremium)}" scope="page"/>
			                            </s:if>
			                            <s:else>
			                            	<s:set var="preAmt" value="%{@java.lang.Double@parseDouble(#attr.preAmt)-@java.lang.Double@parseDouble(loadOrDiscPremium)}" scope="page"/>
			                            </s:else>
									</td>
								</tr>
								</s:if>
								<tr>
									<td></td>
									<td></td>									
									<td></td>
									<td align="right"><s:text name="motor.policyFee"/></td>
									<td>
										<b>[<s:property value="#session.BrokerDetails.CurrencyAbb"/>] &nbsp;&nbsp;&nbsp;</b><s:textfield name="policyFee" id="policyFee" cssClass="input" onkeyup="checkDecimals(this);" maxlength="11" cssStyle="text-align:right;" readonly="true"/>
									</td>
								</tr>
								<tr>
									<td></td>
									<td></td>									
									<td></td>
									<td align="right"><s:text name="motor.totalPremiumPayable"/></td>
									<td><b>[<s:property value="#session.BrokerDetails.CurrencyAbb"/>] &nbsp;&nbsp;&nbsp;</b>
										<%-- 
										<s:textfield name="totalPremium" id="totalPremium" cssClass="input" value="%{@java.lang.Double@parseDouble(#attr.preAmt)+@java.lang.Double@parseDouble(policyFee)}" onkeyup="checkDecimals(this);" maxlength="11" cssStyle="text-align:right;" readonly="true"/>
										--%>
										<s:textfield name="totalPremium" id="totalPremium" cssClass="input" value="%{getText('{0,number,0.00}',{@java.lang.Double@parseDouble(#attr.preAmt)+@java.lang.Double@parseDouble(policyFee)})}" onkeyup="checkDecimals(this);" maxlength="11" cssStyle="text-align:right;" readonly="true"/>
									</td>
								</tr>
								<s:if test='#session.user1 == "admin" || (!"".equals(adminRemarks)&&(adminRemarks!=null)&& #session.user1 != "admin")'>
								<tr>
									<td></td>
									<td></td>									
									<td></td>
									<td align="right"><s:label key="motor.specialCondition"/></td>
									<td>
										<s:textarea name="adminRemarks" onkeyup="textLimit(this,'470')"  cols="50" rows="3" readonly="%{quoteStatus=='RA' && #session.user1 != 'admin'?'true':'false'}"/>
									</td>
								</tr>
								</s:if>
								<s:if test='#session.user1 == "admin"'>
								<tr>
									<td></td>
									<td></td>									
									<td></td>
									<td align="right"><s:label key="motor.referralStatus"/></td>
									<td><s:radio list="#{'Y':'Accept','N':'Reject','A':'Pending'}" name="adminRefStatus" value="%{adminRefStatus==null?'A':adminRefStatus}" /></td>
								</tr>
								</s:if>
								</tbody>	
							</table>
	       					<br class="clear"/>
	       				</div>
	       			</div>
	       		</div>
	       	</div>
	       	</s:if>	       	
	       	<s:if test='#session.user1 != "admin"'>
				<s:if test='showReferralYN == "Y"'>
				<s:hidden name="showReferralYN"/>
			<div class="tablerow">
	       		<div class="panel panel-primary">
        			<div class="panel-heading txtB">
						<s:text name="motor.referalInfo" />
        			</div>
        			<div class="panel-body">
        				<div class="boxcontent">
        					<div class="textfield50">
	       						<div class="textv">
	       							<s:label key="motor.policyStartDt"/>
								</div>
								<div class="tboxv">												   
									<s:property value="policyStartDate"/>
								</div>
	       					</div>
	       					<div class="textfield50">
	       						<div class="textv">
	       							<s:label key="motor.policyEndDt"/>
								</div>
								<div class="tboxv">												   
									<s:property value="policyEndDate"/>
								</div>
	       					</div>
	       					<br class="clear"/>
        				</div>
        				<div class="boxcontent">
        					<div class="textfield50">
	       						<div class="text">
	       							<s:label key="motor.referralYN"/>
								</div>
								<div class="tbox">												   
									<s:radio list="#{'Y':'Yes','N':'No'}" name="referralYN" id="referralYN"  onclick="disablePolicyOption(this.value);"/>
								</div>
	       					</div>
	       					<div class="textfield50">
	       						<div class="text">
	       							<s:label key="motor.comments"/>
								</div>
								<div class="tbox">												   
									<s:textarea name="referralComments" id="referralComments" cssClass="inputBoxA" cssStyle="width: 100%;" onkeyup="textLimit(this,'200')"/>
								</div>
	       					</div>
	       					<br class="clear"/>
	       					<br class="clear"/>
        				</div>
        			</div>
        		</div>
        	</div>
				</s:if>
			<div class="tablerow" id="policyGeneration">
	       		<div class="panel panel-primary">
        			<div class="panel-heading txtB">
						<s:label key="motor.generatepolicy" />
        			</div>
        			<div class="panel-body">
        				<div class="boxcontent">
        					<div class="textfield33">
        						<s:checkbox name="generatePolicyYN" fieldValue="Y" id="generatePolicyYN" onclick="getMop(this.value);toggleEmailYN(this.value);" /> Generate Policy
        						&nbsp;&nbsp;&nbsp;
        						<s:checkbox name="quoteEmailYN" fieldValue="Y" id="quoteEmailYN" /> Email Quote	       						
	       					</div>	
        					<div class="textfield33" id="modeOfPay" style="display: none;">
	       						<div class="text">
	       							<s:label key="motor.modeOfPayment"/>
								</div>
								<div class="tbox">	
									<s:radio id="modeOfPayRadio" name="modeOfPay" list="#{'Credit Card':'Credit Card','Cash':'Cash','Cheque':'Cheque'}" value="%{modeOfPay==null?'':modeOfPay}" onchange="toggleChqInvNo(this.value);"></s:radio>
								</div>
	       					</div>
	       					<div class="textfield33" id="ChqInvNoData">
	       						<div class="text">
	       							<s:label key="motor.chqInvNo"/>
								</div>
								<div class="tbox">												   
									<s:textfield name="chqInvNo" id="chqInvNo" cssClass="inputBox" maxlength="10" />
								</div>
	       					</div>	       					       					
	       					<br class="clear"/>
	       				</div>
	       			</div>
	       		</div>
	       	</div>	       	
			</s:if>
			<div class="tablerow">
				<div class="boxcontent" align="center">
					<s:if test='#session.user1 != "admin"'>
						<s:if test='quoteStatus=="RA"'>
							<input type="button" name="Submit1" class="btn btn-sm btn-danger" value="Back" onclick="getBack('newQuote');" />
						</s:if>
						<s:else>
							<input type="button" name="Submit1" class="btn btn-sm btn-danger" value="Back" onclick="getBack('showSummarry');" />
						</s:else>
						&nbsp; 
						<%--<input type="button" name="Submit2" class="btn" value="Save" onclick="this.form.actionType.value='getSave';this.form.submit();" />
						                  &nbsp;--%>
					</s:if>
					<s:else>
						<s:submit type="button" name="close"  key="Back" cssClass="btn btn-sm btn-danger" onclick="getBack('editCovRate')"/>
					</s:else>
						<input type="button" name="Submit3" class="btn btn-sm btn-success" value="Proceed" onclick="this.form.actionType.value='getQuote';this.form.submit();"/>
					<s:hidden name="applicationNo"/>
					<s:hidden name="quoteNo"/>
					<s:hidden name="quoteStatus"/>
					<s:hidden name="referralMsg"/>
					<s:hidden name="actionType" />
					<s:hidden name="display" />
					<s:hidden name="regNo" />
					<s:hidden name="chassisNo" />
					<s:hidden name="engineNo" />
					<s:hidden name="vehicleRegLoc" />
					<s:hidden name="insNameArabic" />
					<s:hidden name="insAddressArabic" />
				</div>
			</div>
		</div>
        </s:form>
        </s:elseif>
        <s:elseif test="'showQuoteInfo'.equalsIgnoreCase(display)">
		<s:form id="motor" name="motor" method="post"  action="getGeratePolicyMotor.action" theme="simple">
		<div class="table1">
			<div class="tablerow">
				<div class="panel panel-primary">
        			<div class="panel-heading txtB">
						<s:label key="motor.quoteInfo" />
        			</div>
        			<div class="panel-body">
        				<div class="boxcontent" align="center">
        					<div style="color:red;font-size: 15px;"><b><s:label key="motor.refInfo"/> Saved. Your Quote No is : <s:property value="quoteNo"/></b></div>
        				</div>
        			</div>
        		</div>
			</div>
			<div class="tablerow">
				<div class="boxcontent" align="center">
					<input type="button" name="Submit" class="btn btn-sm btn-success" value="Proceed" onclick="getBack('home');"/>
				</div>
			</div>
		</div>
		</s:form>
		</s:elseif>
		<s:elseif test="'showRefInfo'.equalsIgnoreCase(display)">
		<s:form id="motor" name="motor" method="post"  action="getGeratePolicyMotor.action"  theme="simple">
		<div class="table1">
			<div class="tablerow">
				<div class="panel panel-primary">
        			<div class="panel-heading txtB">
						<s:label key="quotation.quoteInfo" />
        			</div>
        			<div class="panel-body">
        				<div class="boxcontent" align="center">
        					<div style="color:red;font-size: 15px;"><b><s:label key="motor.refInfo"/> <s:property value="referralMsg"/></b></div>
        				</div>
        			</div>
        		</div>
			</div>
			<div class="tablerow">
				<div class="boxcontent" align="center">
					<input type="button" name="Submit" class="btn btn-sm btn-success" value="Proceed" onclick="getBack('adminHome');"/>
				</div>
			</div>
		</div>
		</s:form>
		</s:elseif>
	</div>
</div>
<script  type="text/javascript">
	appPath = "${pageContext.request.contextPath}/";
	function getAjaxModel(frm,val, id)
	{
			postRequest('${pageContext.request.contextPath}/'+id+'Motor.action'+val, id);
	}
	function disablePolicyOption(value)
   	{
			 if(value=="Y")
			 {
				document.getElementById('policyGeneration').style.display='none';
				document.getElementById('referralComments').readOnly=false;
			 }   
			 else
			 {   
			 	document.getElementById('policyGeneration').style.display='block';
			 	document.getElementById('referralComments').value='';
			 	document.getElementById('referralComments').readOnly=true;
			 } 
    }
    function toggleChqInvNo(value)
   	{
			 if(value=="Cash" || value=="Cheque")
			 {
			 	document.getElementById('ChqInvNoData').style.display='block';
			 	document.getElementById('ChqInvNoData').value='';
			 }   
			 else
			 {   
				document.getElementById('ChqInvNoData').value='';
				document.getElementById('ChqInvNoData').style.display='none';			 
			 } 
    }	
    <s:if test="'newQuote'.equalsIgnoreCase(display)">
    		toggleClaimDetails('<s:property value="claimYN"/>');
    		toggleLeasedYN('<s:property value="leasedYN"/>');
    		
   	</s:if>
   	<s:elseif test="'policyInfo'.equalsIgnoreCase(display)">
   			toggleChqInvNo('<s:property value="modeOfPay"/>')
   			disablePolicyOption('<s:property value="referralYN"/>');
   	</s:elseif>
    function toggleClaimDetails(value)
    {
     		 if(value=="Y")
			 {
			 	document.getElementById('claimAmount').readOnly=false;
			 }   
			 else
			 {  
			 	document.getElementById('claimAmount').value='';
			 	document.getElementById('claimAmount').readOnly=true;
			 } 
    }
    function toggleLeasedYN(value)
    {
  			 if(value=="Y")
			 {
			 	document.getElementById('bankOfFinance').disabled=false;
			 }   
			 else
			 {  
			 	document.getElementById('bankOfFinance').value='';
			 	document.getElementById('bankOfFinance').disabled=true;
			 } 
    }
	function toggleEmailYN(value)
   		 {
			 if(document.getElementById('generatePolicyYN').checked)
			 {
				document.getElementById('quoteEmailYN').checked=false; 
			 	document.getElementById("quoteEmailYN").disabled=true;			 	
			 }   
			 else
			 {  
			    document.getElementById("quoteEmailYN").disabled=false;				
			 } 
    	}
    function getBack(page)
	{
		if(page=='showSummarry')
		{
			document.motor.action ='${pageContext.request.contextPath}/showSummarryMotor.action';
		}else if(page=='newQuote')
		{
			/*if('RA'=='<s:property value="quoteStatus"/>')
				document.motor.action ='${pageContext.request.contextPath}/admin/HomeApprovedPolicy.jsp';
			else*/
				document.motor.action ='${pageContext.request.contextPath}/editQuoteMotor.action';
		}else if(page=='editCovRate'){
			document.motor.action ='${pageContext.request.contextPath}/editCovRateMotor.action';
		}else if(page=='home'){
			if('admin'=='<s:property value="#session.user1"/>'){
				/*if('RR'=='<s:property value="quoteStatus"/>')
					document.motor.action ='${pageContext.request.contextPath}/admin/HomeRejectedPolicyByDate.jsp';
				else
					document.motor.action ='${pageContext.request.contextPath}/admin/HomePendingPolicyByDate.jsp';*/
					document.motor.action ='${pageContext.request.contextPath}/getOCAjaxReferal.action';
			}else if('b2c'=='<s:property value="#session.b2c"/>')
				document.motor.action ='${pageContext.request.contextPath}/login/ProductSelection.jsp';
			else{
				if('RA'=='<s:property value="quoteStatus"/>')
					document.motor.action ='${pageContext.request.contextPath}/initReport.action?menuType=RA&loginId=<s:property value="loginId"/>';
				else if('RU'=='<s:property value="quoteStatus"/>')
					document.motor.action ='${pageContext.request.contextPath}/initReport.action?menuType=RU&loginId=<s:property value="loginId"/>';
				else if(('getSave'=='<s:property value="actionType"/>' || 'QS'=='<s:property value="quoteStatus"/>'))
					document.motor.action ='${pageContext.request.contextPath}/initReport.action?menuType=QS&loginId=<s:property value="loginId"/>';
				else
					document.motor.action ='${pageContext.request.contextPath}/initReport.action?menuType=<s:property value="quoteStatus"/>&loginId=<s:property value="loginId"/>';
			}
		}else if(page=='customer'){
			if('admin'=='<s:property value="#session.user1"/>'){
				/*if('RR'=='<s:property value="quoteStatus"/>')
					document.motor.action ='${pageContext.request.contextPath}/admin/HomeRejectedPolicyByDate.jsp';
				else
					document.motor.action ='${pageContext.request.contextPath}/admin/HomePendingPolicyByDate.jsp';*/
					document.motor.action ='${pageContext.request.contextPath}/getOCAjaxReferal.action';
			}else{
				if('ET'=='<s:property value="quoteStatus"/>')
					document.motor.action ='${pageContext.request.contextPath}/initReport.action?menuType=ET&custName=<s:property value="fullName"/>';
				else
					document.motor.action ='${pageContext.request.contextPath}/editCustomer.action';
			}
		}else if(page=='quote'){
			if('admin'=='<s:property value="#session.user1"/>'){
     			if('RA'=='<s:property value="quoteStatus"/>'){
					//document.motor.action ='${pageContext.request.contextPath}/admin/HomeApprovedPolicyByDate.jsp';
					document.motor.action ='${pageContext.request.contextPath}/getOCAjaxReferal.action';
				}else
					document.motor.action ='${pageContext.request.contextPath}/initTravel.action';
			}else{
    			if('RA'=='<s:property value="quoteStatus"/>')
					document.motor.action ='${pageContext.request.contextPath}/initReport.action?menuType=RA&loginId=<s:property value="loginId"/>';
				else
					document.motor.action ='${pageContext.request.contextPath}/initTravel.action';
			}
		}else if(page=='adminHome'){
			/*if('Y'=='<s:property value="adminRefStatus"/>')
				document.motor.action ='${pageContext.request.contextPath}/admin/HomeApprovedPolicy.jsp';
			else if('A'=='<s:property value="adminRefStatus"/>')
				document.motor.action ='${pageContext.request.contextPath}/admin/HomePendingPolicy.jsp';
			else if('N'=='<s:property value="adminRefStatus"/>')
				document.motor.action ='${pageContext.request.contextPath}/admin/HomeRejectedPolicy.jsp';*/
				document.motor.action ='${pageContext.request.contextPath}/getOCAjaxReferal.action';
		}
		document.motor.submit();
	}
/*	function getPolicyEndDate(policyStartDt)
	{
		if(policyStartDt!="")
		{
			var date=new Date(reformatDate(policyStartDt));
			//date.setFullYear(date.getFullYear()+1);
			date.setMonth(date.getMonth()+13);
			date.setDate(date.getDate()-1);
			var d;
			var m
			if(parseInt(date.getDate())<10)
			{
				d="0"+date.getDate();
			}else
			{
				d=date.getDate();
			}
			if(parseInt(date.getMonth()+1)==0)
			{
				m="12";
			}else
			if((parseInt(date.getMonth())+1)<10)
			{
				m="0"+(parseInt(date.getMonth())+1);
			}else
			{
				m=(parseInt(date.getMonth())+1);
			}
			var y=date.getFullYear();
			document.getElementById("policyEndDate").value=d+"/"+m+"/"+y;
		}
	}
function reformatDate(dateStr) { 
  	dArr = dateStr.split("/");
    // ex input "2010-01-18" 
    return dArr[2]+ "/" +dArr[1]+ "/" +dArr[0]; //ex out: "18/01/2010"
}*/
function getTotalPremium(frm)
	{
	var premium=parseFloat(frm.premium.value);
	var loadOrDiscPremium=parseFloat(frm.loadOrDiscPremium.value);
	var sign=frm.sign.value;
	var policyFee=parseFloat(frm.policyFee.value);
	var val=0;
	if(sign=='+'){
		val=premium+loadOrDiscPremium+policyFee;
		frm.totalPremium.value=val.toFixed(1);
	}else 
	{
		val=premium-loadOrDiscPremium+policyFee;
		frm.totalPremium.value=val.toFixed(1);
	}
	}
function roundNumber(number,decimal_points) {
	if(!decimal_points) return Math.round(number);
	if(number == 0) {
		return number;
	}
	var exponent = Math.pow(10,decimal_points);
	var num = Math.round((number * exponent)).toString();
	var result = num.slice(0,-1*decimal_points);
	if(num.slice(-1*decimal_points)!='00')
		result = result + "." + num.slice(-1*decimal_points);
	return result;
}
function SubmitAction(qno,appNo,bodyid,isMail,typeId){
	document.getElementById("policyType"+typeId).checked=true;
	var ischecked = false;
	if(document.motor.policyType){
		if(document.motor.policyType.length!=null){
			var len = document.motor.policyType.length;
			for(var i=0;i<len;i++){
				if(document.motor.policyType[i].checked==true){
					var val = document.motor.policyType[i].value;
					if(document.getElementById("coverRateButton"+val)){
						if(!calculatePremium(val))
							return false;
					}
					ischecked = true;
				}
			}
		}else{
			if(document.motor.policyType.checked==true){
				var val = document.motor.policyType.value;
				if(document.getElementById("coverRateButton"+val)){
					if(!calculatePremium(val))
						return false;
				}
				ischecked = true;
			}
		}
	}else{
		alert("You Cannot Proceed");
		return false;
	}
	if(ischecked==false){
		alert("Please Choose Policy Coverage");
		return false;
	}
	document.motor.applicationNo.value=appNo;
	document.motor.quoteNo.value = qno;
	document.motor.typeBody.value=bodyid;
	/*document.motor.referral.value=status;
	if(isMail=='true'){
		document.motor.DisplayValue.value = "mail";
	}
	document.motor.linkFrom.value="usersummary";*/
	document.motor.action='${pageContext.request.contextPath}/insertOptionCoverMotor.action';
	document.motor.submit();
	return false;
}

function deleteCover(val,typeId,coverId,coverCount,premium,imgId){
	//if(document.getElementById("policyType"+typeId).checked==true){
		if((val.src).indexOf("delete_cover.png")!=-1){
			val.style.display = "none";
			var object = document.getElementsByName("optionalCovers"+typeId);
			object[coverCount].value = '';
			if('<s:property value="referralMsg"/>'==null || '<s:property value="referralMsg"/>'==''){
				var val = document.getElementById("total"+typeId).value;
				val= val==""?0:val;
				var tot = parseFloat(val) - parseFloat(premium);
				document.getElementById("total"+typeId).value = roundNumber(tot,2);				
			}else{ 
				document.getElementById("total"+typeId).value = 'Referral';
			}			
			document.getElementById("totaldisplay"+typeId).innerHTML = document.getElementById("total"+typeId).value;
			var object1 =  document.getElementById("firstImg"+imgId);
			object1.src = "${pageContext.request.contextPath}/images/add_cover.png";
			object1.alt = "Add Cover";
			object1.style.cursor = "pointer";
		}
	/*}else{
	    alert("Please Choose Policy Coverage");
	}*/
	return false;
}

function addCover(val,typeId,coverId,coverCount,premium,imgId){
	//if(document.getElementById("policyType"+typeId).checked==true){
		if((val.src).indexOf("add_cover.png")!=-1){
			val.src = "${pageContext.request.contextPath}/images/selected_cover.png";
			val.alt = "Added";
			val.style.cursor = "default";
			var object = document.getElementsByName("optionalCovers"+typeId);
			object[coverCount].value = coverId;
			if('<s:property value="referralMsg"/>'==null || '<s:property value="referralMsg"/>'==''){
				var val = document.getElementById("total"+typeId).value;
				val= val==""?0:val;
				var tot = parseFloat(val) + parseFloat(premium);
				document.getElementById("total"+typeId).value = roundNumber(tot,2);
			}else{ 
				document.getElementById("total"+typeId).value = 'Referral';
			}
			document.getElementById("totaldisplay"+typeId).innerHTML = document.getElementById("total"+typeId).value;
			var object1 =  document.getElementById("secondImg"+imgId);
			object1.style.display = "inline";		
		}
	/*}else{
	    alert("Please Choose Policy Type");
	}*/
	return false;
}
addTotal();
function addTotal(){
	if(document.motor.policyType){
	if(document.motor.policyType.length){
		var policylen = document.motor.policyType.length;		
		for(var i=0;i<policylen;i++){			
			var typeId = document.motor.policyType[i].value;
			
			var obj =  document.getElementsByName("default"+typeId);
			if('<s:property value="referralMsg"/>'==null || '<s:property value="referralMsg"/>'==''){
				var tot = 0;
				if(obj.length){
					var len = obj.length;
					for(var j=0;j<len;j++){
						tot = parseFloat(tot) + parseFloat(obj[j].value==""?0:obj[j].value);
					}
				}else{
					tot = parseFloat(tot) + parseFloat(obj.value==""?0:obj.value);
				}
				document.getElementById("total"+typeId).value = roundNumber(tot,2);
			}else{ 
				document.getElementById("total"+typeId).value = 'Referral';
			}
			
			document.getElementById("totaldisplay"+typeId).innerHTML = document.getElementById("total"+typeId).value;
			/* if(document.motor.policyType[i].checked==true)
			{
			alert(document.getElementById("plusImg"+document.motor.policyType[i].value));
			showHideDetails(document.getElementById("plusImg"+document.motor.policyType[i].value), 'OptCoverDiplay'+document.motor.policyType[i].value);
			}*/
		}		
	}else{
		var typeId = document.motor.policyType.value;
		var obj =  document.getElementsByName("default"+typeId);
		
		if('<s:property value="referralMsg"/>'==null || '<s:property value="referralMsg"/>'==''){
			var tot = 0;
			if(obj.length){
				var len = obj.length;
				for(var j=0;j<len;j++){
					tot = parseFloat(tot) + parseFloat(obj[j].value==""?0:obj[j].value);
				}
			}else{
				tot = parseFloat(tot) + parseFloat(obj.value==""?0:obj.value);
			}
			document.getElementById("total"+typeId).value = roundNumber(tot,2);
		}else{ 
			document.getElementById("total"+typeId).value = 'Referral';
		}
		document.getElementById("totaldisplay"+typeId).innerHTML = document.getElementById("total"+typeId).value;
	}
	}
}

function getMop(val) {
	if (document.getElementById("generatePolicyYN").checked) {
		document.getElementById("modeOfPay").style.display = "block";
		
		var value = document.motor.modeOfPay.value;
		if(value=="Cash" || value=="Cheque")
		 {
		 	document.getElementById('ChqInvNoData').style.display='block';
		 	document.getElementById('ChqInvNoData').value='';
		 }   
		 else
		 {   
			document.getElementById('ChqInvNoData').value='';
			document.getElementById('ChqInvNoData').style.display='none';			 
		 }
	} else {
		document.getElementById("modeOfPay").style.display = "none";
		document.getElementById('ChqInvNoData').style.display='none';
		document.getElementById('ChqInvNoData').value='';
	}
} 
</script>
</body>
</html>
