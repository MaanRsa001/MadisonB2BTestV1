<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<script language="JavaScript">javascript:window.history.forward(1);</script>
<script language="JavaScript">
function stopRKey(evt) { 
  var evt = (evt) ? evt : ((event) ? event : null); 
  var node = (evt.target) ? evt.target : ((evt.srcElement) ? evt.srcElement : null); 
  if ((evt.keyCode == 13) && (node.type=="text"))  {return false;} 
} 
document.onkeypress = stopRKey;
</script>
<style>
	#loading {
	  width: 100%;
	  height: 100%;
	  top: 0px;
	  left: 0px;
	  position: fixed;
	  display: block;
	  opacity: 0.7;
	  background-color: #fff;
	  z-index: 99;
	  text-align: center;
	}
	
	#loading-image {
	  position: absolute;
	  top: 30%;
	  left: 45%;
	  z-index: 100;
	  width: 100px;
	  height: 100px;
	}
</style>
</head>
<body>
<s:set var="disable" value='%{(quoteStatus=="RA")||(endTypeId!=null && endTypeId.length() > 0) || "Y".equals(finalizeYN)}'/>
<s:set var="disable2" value="%{endTypeId!=null && endTypeId.length() > 0}"/>
<div id="loading" style="width:100%">
   <img id="loading-image" src="${pageContext.request.contextPath}/images/ajax-loader-bar.gif"/>
</div>
<s:form id="quotation" name="quotation" method="post" action="getQuoteQuotation.action" theme="simple">
<div class="row">
	<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
		<s:actionerror cssStyle="color: red;" />
	</div>
</div>
<div class="row">
	<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
		<s:if test='%{endtType!=null && endtType.length() > 0}'>			
			<div class="panel panel-primary">
				<div class="panel-body" style="padding: 0;">
					<s:set value="policyEndtInfo" var="policyEndtInfo"></s:set>
					<div class="row">
						<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
							<div class="textV">
					 			<s:text name="endt.policyNo"/>
					 		</div>
					 		<div class="tboxV">
					 			<span style="color:blue;"><s:property value="%{#policyEndtInfo.POLICY_NO}"/></span>									 		
					 		</div>
						</div>
						<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
							<div class="textV">
					 			<s:text name="endt.brokerName"/>
					 		</div>
					 		<div class="tboxV">
					 			<span style="color:blue;"><s:property value="%{#policyEndtInfo.BROKER_NAME}"/></span>									 		
					 		</div>
						</div>
						<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
							<div class="textV">
					 			<s:text name="endt.custName"/>
					 		</div>
					 		<div class="tboxV">
					 			<span style="color:blue;"><s:property value="%{#policyEndtInfo.CUSTOMER_NAME}"/></span>									 		
					 		</div>
						</div>
					</div>
					<div class="row">
						<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
							<div class="textV">
					 			<s:text name="quotation.endtTypeDesc" />
					 		</div>
					 		<div class="tboxV">
					 			:&nbsp;<span style="color:blue;"><s:property value="endtType" /></span>									 		
					 		</div>
						</div>
					</div>
				 	<br class="clear"/>
				</div>
			</div>
		</s:if>
		<s:elseif test="%{productId==openCover}" >
			<div class="panel panel-primary">
				<div class="panel-body" style="padding: 0;">
				 	<s:set value="openCoverInfo" var="openCoverInfo"></s:set>
				 	<div class="row">
				 		<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
				 			<div class="textV">
					 			<s:text name="endt.openPolicyNo"/>
					 		</div>
					 		<div class="tboxV">
					 			<span style="color:blue;"><s:property value="%{#openCoverInfo.OPEN_COVER_NO}"/></span>									 		
					 		</div>
				 		</div>
				 		<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
				 			<div class="textV">
					 			<s:text name="endt.brokerName"/>
					 		</div>
					 		<div class="tboxV">
					 			<span style="color:blue;"><s:property value="%{#openCoverInfo.BROKER_NAME}"/></span>									 		
					 		</div>
				 		</div>
				 		<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
				 			<div class="textV">
					 			<s:text name="endt.custName"/>
					 		</div>
					 		<div class="tboxV">
					 			<span style="color:blue;"><s:property value="%{#openCoverInfo.CUSTOMER_NAME}"/></span>									 		
					 		</div>
				 		</div>
				 	</div>
				 	<br class="clear" />
				 </div>
			</div>
		</s:elseif>
	</div>
</div>
<div class="row">
	<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
		<s:if test="issuer != null">
			<div class="panel panel-primary">
				<div class="panel-heading">
					<s:text name="quotation.brokerInfo" />
				</div>
				<div class="panel-body" style="padding: 0;">
					<div class="row">
						<s:if test="%{productId!=openCover}">
							<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
								<div class="text">
						 			<s:text name="quotation.channelType"  /><font color="red">*</font>
						 		</div>
						 		<div class="tbox">									 			
						 			 <s:select name="channelType" id="channelType" list="#{'broker':'Broker','customer':'Customer','cash':'Cash'}" headerKey="" headerValue="---Select----" cssClass="inputBoxS tooltipContent" data-content="Channel Type" onchange="getList('?channelType='+this.value,'brokersList');getList('?brokerCode=','executiveList');getList('?brokerCode=','promotionalList');emptyCustInfo();" disabled="%{brokerCode!=null && brokerCode!=''}"/>							 		
						 		</div>
					 		</div>
				 		</s:if>
				 		<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
				 			<div class="text">
					 			<s:text name="quotation.broker"/><font color="red">*</font>
					 		</div>
					 		<div class="tbox">
					 			<s:select name="brokerCode" id="brokerCode" list="brokerList" headerKey="" headerValue="---Select----" cssClass="inputBoxS tooltipContent" data-content="Broker"  listKey="CODE" listValue="CODEDESC" onchange="getList('?brokerCode='+this.value,'executiveList');getList('?brokerCode='+this.value,'promotionalList');emptyCustInfo();" disabled="%{brokerCode!=null && brokerCode!=''}"/>
					 		</div>
				 		</div>
				 		<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
				 			<div class="text">
					 			<s:text name="quotation.executive" /><font color="red">*</font>
					 		</div>
					 		<div class="tbox">
					 			<div id="executiveList"><s:select name="executive" list="executiveList" headerKey="" headerValue="---Select----" cssClass="inputBoxS tooltipContent" data-content="Executive" listKey="CODE" listValue="CODEDESC" disabled="#disable" value='executive==null?getText("quotation.executiveDefault"):executive'/></div>
					 		</div>	
				 		</div>
					</div>			
				</div>
			</div>
		</s:if>
		<s:else>
			<s:hidden name="channelType"/>
		</s:else>
		<div class="panel panel-primary">
			<div class="panel-heading">
				<s:text name="quotation.customerInfo" />
				<div class="pullRight">
					<input type="button" value="Clear" class="btn btn-sm btn-default" style="cursor:pointer;" onclick="clearCustomerInfo();"/>
				</div>
				<br class="clear"/>
			</div>
			<div class="panel-body">
				<div class="row">
					<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
						<div class="text">
				 			<s:text name="quotation.title"  /><font color="red">*</font>
				 		</div>
				 		<div class="tbox">
				 			<s:select name="title" id="title" list="titleList" headerKey="" headerValue="---Select---"  listKey="CODEDESC" listValue="CODEDESC" cssClass="inputBoxS tooltipContent" data-content="Title" disabled="#disable"/>
				 		</div>
			 		</div>
			 		<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
			 			<div class="text">
				 			<s:text name="quotation.customerName" /><font color="red">*</font>
				 		</div>
				 		<div class="tbox">
				 			<div class="inputAppend">
                               <s:textfield name="customerName" id="customerName" cssClass="inputBox1 inputBox tooltipContent" data-content="Custome Name" cssStyle="border: none;background: transparent;"  maxLength="200" disabled="#disable" onchange="setCustomerId();"/>
                               <s:hidden name="customerId" id="customerId" />
                               <s:submit type="button" value="..." cssClass="inputButton" onclick="return customerSelectionAction()" disabled="#disable"/>
                           </div>
				 		</div>	
			 		</div>
			 		<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
			 			<div class="text">
				 			<s:text name="quotation.coreAppCode" />
				 		</div>
				 		<div class="tbox">
				 			<s:textfield name="coreAppCode" id="coreAppCode" cssClass="inputBox tooltipContent" data-content="Core App Code" maxlength="20" readonly="true" disabled="#disable"/>
                     		<s:hidden name="nameAndCode" id="nameAndCode"/>
                     		<s:hidden name="custArNo" id="custArNo"/>
                     		<s:hidden name="coreCustomerName" id="coreCustomerName"/>
				 		</div>	
			 		</div>
				</div>
				<div class="row">
					<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
						<div class="text">
				 			<s:text name="quotation.address1" />
				 		</div>
				 		<div class="tbox">
				 			<s:textfield name="address1" id="address1" cssClass="inputBox tooltipContent" data-content=""  maxLength="200" disabled="#disable"/>
				 		</div>	
			 		</div>
			 		<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
			 			<div class="text">
				 			<s:text name="quotation.address2" />
				 		</div>
				 		<div class="tbox">
				 			<s:textfield name="address2" id="address2" cssClass="inputBox tooltipContent" data-content="" maxlength="200" disabled="#disable"/>
				 		</div>
			 		</div>
			 		<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
			 			<div class="text">
				 			<s:text name="quotation.city" /><font color="red">*</font>
				 		</div>
				 		<div class="tbox">
				 			<s:select name="city" id="city" list="cityList" headerKey="" headerValue="---Select---" listKey="CODEDESC" listValue="CODEDESC" cssClass="inputBoxS tooltipContent" data-content="City" disabled="#disable"/>
				 		</div>
			 		</div>
				</div>
				<div class="row">
					<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
						<div class="text">
				 			<s:text name="quotation.poBox" /><font color="red">*</font>
				 		</div>
				 		<div class="tbox">
				 			<s:textfield name="poBox" id="poBox" cssClass="inputBox tooltipContent" data-content="P.O. Box" disabled="#disable" maxLength="6" onchange="checkNumbers(this);" />
				 		</div>
			 		</div>
			 		<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
			 			<div class="text">
				 			<s:text name="quotation.mobileNo" />
				 		</div>
				 		<div class="tbox">
				 			<s:textfield name="mobileNo" id="mobileNo" cssClass="inputBox tooltipContent" data-content="Mobile No." disabled="#disable"  maxLength="10" onchange="checkNumbers(this);" />
				 		</div>	
			 		</div>
			 		<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
			 			<div class="text">
				 			<s:text name="quotation.email" />
				 		</div>
				 		<div class="tbox">
				 			<s:textfield name="email" id="email" cssClass="inputBox tooltipContent" data-content="E-Mail" disabled="#disable" maxlength="100"/>
				 		</div>	
			 		</div>
				</div>
				<div class="row" id="editCustomerDIV">
					<s:if test='%{productId==openCover && customerId.equals(#session.customer_id) && !(#disable)}'>
					 	<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
					 		<div class="text">
					 			<s:text name="quotation.editCustomer" />
					 		</div>
					 		<div class="tbox">
					 			<s:radio list="#{'YES':'Yes','NO':'No'}" name="editCustomer" id="editCustomer"  value='%{(editCustomer==null || "".equals(editCustomer))?"NO":editCustomer}' disabled="#disable" onclick="editCustomerInfo();"/>
					 		</div>	
					 	</div>
				 	</s:if>
				</div>
			</div>
		</div>
		<div class="panel panel-primary">
			<div class="panel-heading">
				<s:text name="quotation.quoteInfo" />					
			</div>
			<div class="panel-body">
				<div class="row">
					<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
				 		<div class="text">
				 			<s:text name="quotation.modeOfTransport" /><font color="red">*</font>
				 		</div>
				 		<div class="tbox">
				 			<s:select name="modeOfTransport" id="modeOfTransport" list="modeList" headerKey="" headerValue="---Select---" listKey="CODE" listValue="CODEDESC" cssClass="inputBoxS tooltipContent" data-content="Mode of Transport" disabled="#disable"  onchange="getList('?modeOfTransport='+this.value,'coverList');getList('?modeOfTransport='+this.value,'conveyanceList');getList('?modeOfTransport='+this.value,'packageList');disableWarehouse(this);toggleWarSrcc(this);setVesselName();toggleModeOfTransport(this);" />
				 		</div>	
				 	</div>
				 	<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
				 		<div class="text">
				 			<s:text name="quotation.cover" /><font color="red">*</font>
				 		</div>
				 		<div class="tbox">
				 			<s:if test="%{productId==openCover}" >
	                           	<div id="coverList"><s:select name="cover" id="cover" list="coverList" headerKey="" headerValue="---Select---" listKey="CODE" listValue="CODEDESC" cssClass="inputBoxS tooltipContent" data-content="Cover" disabled="#disable" /></div>
	                           </s:if>	
	                           <s:else>
	                           	<div id="coverList"><s:select name="cover" id="cover" list="coverList" headerKey="" headerValue="---Select---" listKey="CODE" listValue="CODEDESC" cssClass="inputBoxS tooltipContent" data-content="Cover" disabled="#disable"  /></div>                                
	                          	</s:else>
				 		</div>	
				 	</div>
				 	<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
				 		<div class="text">
				 			<s:text name="quotation.conveyance" /><font color="red">*</font>
				 		</div>
				 		<div class="tbox">
				 			<div id="conveyanceList"><s:select name="conveyance" list="conveyanceList" listKey="CODE" listValue="CODEDESC" headerKey="" headerValue="---Select---" cssClass="inputBoxS tooltipContent" data-content="Conveyance" disabled="#disable" /></div>
				 		</div>	
				 	</div>
				</div>
				<div class="row">
					<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
				 		<div class="text">
				 			<s:text name="quotation.originCountry" /><font color="red">*</font>
				 		</div>
				 		<div class="tbox">
				 			<s:select name="originCountry" id="originCountry" list="originList" headerKey="" headerValue="---Select---" listKey="CODE" listValue="CODEDESC" cssClass="inputBoxS tooltipContent" data-content="Origin City" disabled="#disable" value="%{originCountry==null?'1':originCountry}" onchange="emptyCity('orgin');"/>
				 		</div>	
				 	</div>
				 	<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
				 		<div class="text">
				 			<s:text name="quotation.originCity" />
				 		</div>
				 		<div class="tbox">
				 			<div class="inputAppend"><s:textfield name="originCityName" id="originCityName" cssClass="inputBox1" cssStyle="border: none;background: transparent;" disabled="#disable" readonly="true"/><s:hidden name="originCity" id="originCity"/><s:submit type="button" value="..." onclick="return originCitySelection()" disabled="#disable" cssClass="inputButton" name="originCityBtn"/></div>
				 		</div>	
				 	</div>
				 	<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
				 		<div class="text">
				 			<s:text name="quotation.originWarehouse" />
				 		</div>
				 		<div class="tbox">
				 			<s:radio list="#{'YES':'Yes','NO':'No'}" name="originWarehouse" id="originWarehouse"  value='%{originWarehouse==null?"NO":originWarehouse}' disabled="#disable"/>
				 		</div>	
				 	</div>
				</div>
				<div class="row">
					<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
				 		<div class="text">
				 			<s:text name="quotation.destCountry" /><font color="red">*</font>
				 		</div>
				 		<div class="tbox">
				 			<s:select name="destCountry" id="destCountry" list="destList" headerKey="" headerValue="---Select---" listKey="CODE" listValue="CODEDESC" cssClass="inputBoxS tooltipContent" data-content="Destination City" disabled="#disable"  onchange="getList('?quoteStatus=%{quoteStatus}&destCountry='+this.value,'agentList');emptyCity('dest');" value="%{destCountry==null?'1':destCountry}"/>
				 		</div>	
				 	</div>
				 	<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
				 		<div class="text">
				 			<s:text name="quotation.destCity" />
				 		</div>
				 		<div class="tbox">
				 			<div class="inputAppend"><s:textfield name="destCityName" id="destCityName" cssClass="inputBox1 inputBox tooltipContent" data-content="Destination City" cssStyle="border: none;background: transparent;" disabled="#disable" readonly="true"/><s:hidden name="destCity" id="destCity"/><s:submit type="button" value="..." onclick="return destCitySelection()" cssClass="inputButton" disabled="#disable" name="destCityBtn"/></div>
				 		</div>	
				 	</div>
				 	<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
				 		<div class="text">
				 			<s:text name="quotation.destWarehouse" />
				 		</div>
				 		<div class="tbox">
				 			<s:radio list="#{'YES':'Yes','NO':'No'}" name="destWarehouse"  value='%{destWarehouse==null?"NO":destWarehouse}' disabled="#disable"/>
				 		</div>	
				 	</div>
				</div>
				<div class="row">
					<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
				 		<div class="text">
				 			<s:text name="quotation.policyStartDate" /><font color="red">*</font>
				 		</div>
				 		<div class="tbox">
				 			<s:textfield id="policyStartDate" name="policyStartDate" cssClass="inputBox datePicker tooltipContentR" data-content="Policy Start Date" readonly="true" disabled="#disable" />
				 		</div>	
				 	</div>
				 	<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
				 		<div class="text">
				 			<s:text name="quotation.currency" /><font color="red">*</font>
				 		</div>
				 		<div class="tbox">
				 			<s:select name="currency" id="currency" list="currencyList" headerKey="" headerValue="---Select---" listKey="CODE" listValue="CODEDESC" cssClass="inputBoxS tooltipContent" data-content="Currency List" cssStyle="width:80%;float:left;" disabled="#disable"  onchange="exchageRate(this,'currency')"/>
	                           <s:textfield id="exchageValue" name="exchageValue" cssStyle="width:20%;float:left;" cssClass="inputBox tooltipContent" data-content="Exchange Value" size="5"  disabled="#disable"/>
							<s:iterator value="currencyList" var="currencyDetail">
								<s:hidden name="%{#currencyDetail.CODE}" id="%{#currencyDetail.CODE}" value="%{#currencyDetail.CODEVALUE}"/>
							</s:iterator>
				 		</div>	
				 	</div>
				 	<s:if test='warOption=="Y"'>
				 	<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
				 		<div class="text">
				 			<div id="warSrccId" class="tCol"><s:text name="quotation.warSrcc" /> </div>
				 		</div>
				 		<div class="tbox">
				 			<s:radio list="#{'YES':'Yes','NO':'No'}" name="warSrcc"  value='%{warSrcc==null?"YES":warSrcc}' disabled="#disable"/>
				 		</div>	
				 	</div>
				 	</s:if>
				</div>
			 	<div class="row">
			 		<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
				 		<div class="text">
				 			<s:text name="quotation.commodity" /><font color="red">*</font>
				 		</div>
				 		<div class="tbox" style="height: 50px;">
				 			<div class="inputAppend" style="height: 50px;"> 
								<s:textarea name="commodity" cssClass="inputBox1 tooltipContent" data-content="Commodity Name" cssStyle="border: none;background: transparent; height : 50px;float:left;" disabled="#disable" readonly="true"/>
								<input type="button" value="..." style="width:10%;float:left;"  onclick="return commoditySelection()" tabindex="35" />
							</div>
				 		</div>	
				 	</div>
				 	<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
				 		<div class="text">
				 			<s:text name="quotation.totalCommodity" />
				 		</div>
				 		<div class="tbox">
				 			<s:textfield name="totalCommodity"  id="totalCommodity" cssClass="inputBox tooltipContent" data-content="" disabled="#disable" readonly="true"/>
				 		</div>	
				 	</div>
				 	<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
				 		<div class="text">
				 			<s:text name="quotation.totalSI" />
				 		</div>
				 		<div class="tbox">
				 			<s:textfield name="totalSI" id="totalSI" cssClass="inputBox tooltipContent" data-content="" disabled="#disable" readonly="true" maxlength="20"/>
				 		</div>	
				 	</div>
				</div>
			 	<div class="row">
			 		<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
				 		<div class="text">
				 			<s:text name="quotation.saleTerms" /><font color="red">*</font>
				 		</div>
				 		<div class="tbox">
				 			<s:select name="saleTerm" list="saleTermList" headerKey="" headerValue="---Select---" listKey="CODE" listValue="CODEDESC" cssClass="inputBoxS tooltipContent" data-content="Sale Term" disabled="#disable" onchange="getList('?saleTerm='+this.value,'percentList');"/>
				 		</div>	
				 	</div>
				 	<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
				 		<div class="text">
				 			<s:text name="quotation.percentage"/><font color="red">*</font>
				 		</div>
				 		<div class="tbox">
				 			<s:if test="%{productId==openCover}" >
								<div id="percentList"><s:select name="saleTermPercent" list="percentList" headerKey="" headerValue="---Select---" listKey="CODE" listValue="CODEDESC" cssClass="inputBoxS tooltipContent" data-content="Percentage" disabled="#disable"  onchange="getList('?saleTermPercent='+this.value,'toleranceList');"/></div>
							</s:if>	
							<s:else>                                
								<div id="percentList"><s:select name="saleTermPercent" id="percent" list="percentList" headerKey="" headerValue="---Select---" listKey="CODE" listValue="CODEDESC" cssClass="inputBoxS tooltipContent" data-content="Percentage" disabled="%{saleTerm=='205' || #disable}"  onchange="getList('?saleTermPercent='+this.value,'toleranceList');"/></div>                                
							</s:else><s:hidden name="saleTermDecVal"/>
				 		</div>	
				 	</div>
				 	<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
				 		<div class="text">
				 			<s:text name="quotation.tolerance" />
				 		</div>
				 		<div class="tbox">
				 			<div id="toleranceList"><s:select name="tolerance" list="toleranceList" listKey="CODE" listValue="CODEDESC" cssClass="inputBoxS tooltipContent" data-content="Tolerance" disabled="#disable" value='tolerance==null?getText("quotation.toleranceDecVal"):tolerance'/></div>
				 		</div>	
				 	</div>
				</div>
				<s:if test="%{productId==openCover}" >
				 	<div class="row">
					 	<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
					 		<div class="text">
					 			<s:text name="quotation.partialShipment" />
					 		</div>
					 		<div class="tbox">
					 			<s:select list="#{'N':'None','Y':'Partial','M':'Multiple'}" cssClass="inputBoxS tooltipContent" data-content="" name="partialShipment"  value='%{partialShipment==null?"N":partialShipment}' disabled="#disable2" onchange="if(this.value=='N'){this.form.shipmentExposure.value='';this.form.exposureCurrency.value='';this.form.exposureValue.value='';this.form.shipmentExposure.disabled=true;this.form.exposureCurrency.disabled=true;this.form.exposureValue.disabled=true;}else{this.form.shipmentExposure.disabled=false;this.form.exposureCurrency.disabled=false;this.form.exposureValue.disabled=false;}"/>
					 		</div>	
					 	</div>
					 	<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
					 		<div class="text">
					 			<s:text name="quotation.exposureOfShipment" /><font color="red">*</font>
					 		</div>
					 		<div class="tbox">
					 			<s:textfield name="shipmentExposure" id="shipmentExposure" cssClass="inputBox tooltipContent" data-content="Shipment Exposure" disabled='%{#disable2==false?(partialShipment==null || partialShipment=="N"):#disable2}' maxlength="20"/>
					 		</div>	
					 	</div>
					 	<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
					 		<div class="text">
					 			<s:text name="quotation.currencyOfExposure" /><font color="red">*</font>
					 		</div>
					 		<div class="tbox">
					 			<s:select name="exposureCurrency" id="exposureCurrency" list="currencyList" headerKey="" headerValue="---Select---" listKey="CODE" listValue="CODEDESC" cssClass="inputBoxS tooltipContent" data-content="Exposure Currency" cssStyle="width:80%;float:left;" onchange="exchageRate(this,'exposureCurrency')" disabled='%{#disable2==false?(partialShipment==null || partialShipment=="N"):#disable2}'/><s:textfield id="exposureValue" name="exposureValue" cssStyle="width:20%;float:left;" cssClass="inputBox tooltipContent" data-content="" size="5" readonly="true" disabled='%{#disable2==false?(partialShipment==null || partialShipment=="N"):#disable2}' maxlength="20"/>
		                           <s:iterator value="currencyList" var="currencyDetail">
		                           <s:hidden name="%{#currencyDetail.CODE}" id="%{#currencyDetail.CODE}" value="%{#currencyDetail.CODEVALUE}"/>
		                           </s:iterator>
					 		</div>	
					 	</div>
					</div>
				</s:if>
			 	<div class="row">
			 		<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
				 		<div class="text">
				 			<s:text name="quotation.vesselName" />
				 		</div>
				 		<div class="tbox">
				 			<div class="inputAppend">
	                         		<s:textfield name="vesselName" id="vesselName" cssClass="inputBox1" cssStyle="border: none;background: transparent;" disabled="#disable2" maxlength="100" readonly="true"/>
	                         		<s:hidden name="vesselId" id="vesselId"/>
	                         		<s:submit type="button" value="..." onclick="return callvessel()" cssClass="inputButton" name="vesselBtn" disabled="#disable2"/>
	                         	</div>
				 		</div>	
				 	</div>
				 	<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
				 		<div class="text">
				 			<s:text name="quotation.settlingAgent" />
				 		</div>
				 		<div class="tbox">
				 			<s:if test='settlingAgent!=null && "0".equals(settlingAgent) && !("admin".equalsIgnoreCase(#session.usertype) || "RSAIssuer".equalsIgnoreCase(#session.usertype)) && "RA".equalsIgnoreCase(quoteStatus)'>
	                     			<s:select name="settlingAgent" id="settlingAgent" list="agentList"  listKey="CODE" listValue="CODEDESC" cssClass="inputBoxS tooltipContent" data-content=""  onchange="disableOthers(this);" disabled="true" />
	                     		</s:if>
	                     		<s:else>
	                     			<s:select name="settlingAgent" id="settlingAgent" list="agentList"  listKey="CODE" listValue="CODEDESC" cssClass="inputBoxS tooltipContent" data-content=""  onchange="disableOthers(this);" disabled="#disable2" />
	                     		</s:else>
				 		</div>	
				 	</div>
				 	<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
				 		<div class="text">
				 			<s:text name="quotation.agentOthers" />
				 		</div>
				 		<div class="tbox">
				 			<div class="inputAppend" style="height: 50px;"> 
				 			<s:if test='settlingAgent!=null && "0".equals(settlingAgent) && !("admin".equalsIgnoreCase(#session.usertype) || "RSAIssuer".equalsIgnoreCase(#session.usertype)) && "RA".equalsIgnoreCase(quoteStatus)'>
	                          		<s:textarea name="agentOthers" id="agentOthers" cssClass="inputBox1" cssStyle="border: none;background: transparent; height : 50px;" disabled="true" maxlength="1000"/>
	                          	</s:if>
	                          	<s:else>
	                          		<s:textarea name="agentOthers" id="agentOthers" cssClass="inputBox1" cssStyle="border: none;background: transparent; height : 50px;" disabled="#disable2" maxlength="1000"/>
	                          	</s:else>
	                          	</div>
				 		</div>	
				 	</div>
				</div>
				<s:hidden name="consigneeDetail" value=""/>
                <s:hidden name="addCustomerName" value=""/>	 
			 	<div class="row">
			 		<s:if test='(specialTerm!=null && !"".equals(specialTerm)) || ("admin".equalsIgnoreCase(#session.usertype)||"RSAIssuer".equalsIgnoreCase(#session.usertype))'>       
					 	<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
					 		<div class="text">
					 			<s:text name="Special Term & Condition" />
					 		</div>
					 		<div class="tbox">
					 			<s:textarea name="specialTerm" cssClass="inputBox1" onkeyup="textLimit(this,1000)" disabled='%{(("admin".equalsIgnoreCase(#session.usertype)||"RSAIssuer".equalsIgnoreCase(#session.usertype)) && #disable2!=true)?"false":"true"}'/>
					 		</div>	
					 	</div>	
					 	</s:if>			 	
					 	<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
					 		<div class="text">
					 			<s:text name="quotation.packageDesc" /><font color="red">*</font>
					 		</div>
					 		<div class="tbox">
					 			<div id="packageList"><s:select name="packageCode" list="packageList" headerKey="" headerValue="---Select---" listKey="CODE" listValue="CODEDESC" cssClass="inputBoxS tooltipContent" data-content="Package Discription"   disabled="#disable2" /></div>
					 		</div>	
					 	</div>
					 	<s:if test="%{productId!=openCover}" >
					 	<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
					 		<div class="text">
					 			<s:text name="quotation.partialShipment" />
					 		</div>
					 		<div class="tbox">
					 			<s:radio list="#{'Y':'Yes','N':'No'}" name="partialShipment"  value='%{partialShipment==null?"N":partialShipment}' disabled="#disable2"/>
					 		</div>	
					 	</div>
				 	</s:if>
				</div>
			 	<div class="row">
			 		<s:if test='!((specialTerm!=null && !"".equals(specialTerm)) || ("admin".equalsIgnoreCase(#session.usertype)||"RSAIssuer".equalsIgnoreCase(#session.usertype)))'> 
					 	<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
					 		<div class="text">
					 			<s:text name="quotation.via"/>
					 		</div>
					 		<div class="tbox">
					 			<s:textfield name="via" id="via" cssClass="inputBox tooltipContent" data-content="" disabled="#disable" maxlength="100"/>
					 		</div>	
					 	</div>
				 	</s:if>
				 	<s:else>
					 	<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
					 		<div class="text">
					 			<s:text name="quotation.via" />
					 		</div>
					 		<div class="tbox">
					 			<s:textfield name="via" id="via" cssClass="inputBox tooltipContent" data-content="" disabled="#disable" maxlength="100"/>
					 		</div>	
					 	</div>
				 	</s:else>
				</div>
			 	<br class="clear" />
			 </div>
		</div>
		<div class="panel panel-primary">
			<div class="panel-heading">
				 <div id="plus" onclick="quotationDetail(true);" style="display: none; cursor: pointer;">+&nbsp;&nbsp;<s:text name="LC Bank Details" /></div> 
                 <div id="miuns" onclick="quotationDetail(false);" style="display: block; cursor: pointer;">-&nbsp;&nbsp;<s:text name="LC Bank Details" /></div>				
			</div>
			<div class="panel-body" id="quoteInfo" style="display:;">
				<div class="row">
					<s:if test="%{productId==openCover}" >
						<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
					 		<div class="text">
					 			<s:text name="quotation.lcBank" />
					 		</div>
					 		<div class="tbox">
					 			<s:if test='lcBankList!=null && lcBankList.size==1'>
		                      			<s:select name="lcBank" list="lcBankList" listKey="CODE" listValue="CODEDESC" cssClass="inputBoxS tooltipContent" data-content="LC Bank"   disabled="%{#disable2==false?(quoteStatus=='RA'):#disable2}"/>
		                      		</s:if>
		                      		<s:else>
		                      			<s:select name="lcBank" list="lcBankList" headerKey=""  headerValue="---Select---" listKey="CODE" listValue="CODEDESC" cssClass="inputBoxS tooltipContent" data-content="LC Bank"   disabled="%{#disable2==false?(quoteStatus=='RA'):#disable2}"/>
		                      		</s:else>
					 		</div>	
					 	</div>
				 	</s:if>
				 	<s:else>
					 	<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
					 		<div class="text">
					 			<s:text name="quotation.lcBank" />
					 		</div>
					 		<div class="tbox">
					 			<s:select name="lcBank" id="lcBank" list="lcBankList" headerKey="" headerValue="---Select---" listKey="CODE" listValue="CODEDESC" cssClass="inputBoxS tooltipContent" data-content="Lc Bank"  disabled="%{#disable2==false?(quoteStatus=='RA'):#disable2}"/>
					 		</div>	
					 	</div>
				 	</s:else>
				 	<s:if test="%{productId==openCover}" > 
					 	<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
					 		<div class="text">
					 			<s:text name="quotation.lcNo" />
					 		</div>
					 		<div class="tbox">
					 			<s:textfield name="lcNo" cssClass="inputBox tooltipContent" data-content="Lc No" maxlength="30" disabled="#disable2"/>
					 		</div>	
					 	</div>
				 	</s:if>
				 	<s:else>
					 	<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
					 		<div class="text">
					 			<s:text name="quotation.lcNo" />
					 		</div>
					 		<div class="tbox">
					 			<s:textfield name="lcNo" cssClass="inputBox tooltipContent" data-content="LC No." maxlength="30" disabled="#disable2"/>
					 		</div>	
					 	</div>
				 	</s:else>
				 	<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
				 		<div class="text">
				 			<s:text name="quotation.lcDate" />
				 		</div>
				 		<div class="tbox">
				 			<s:textfield id="lcDate" name="lcDate" cssClass="inputBox datepicker inputBox tooltipContent" data-content="LC Date" readonly="true" disabled="#disable" />
				 		</div>	
				 	</div>
				</div>
				<div class="row">
					<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
				 		<div class="text">
				 			<s:text name="quotation.blNo" />
				 		</div>
				 		<div class="tbox">
				 			<s:textfield name="blNo" cssClass="inputBox tooltipContent" data-content=""  maxLength="25" disabled="#disable2"/>
				 		</div>	
				 	</div>
				 	<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
				 		<div class="text">
				 			<s:text name="quotation.blDate" />
				 		</div>
				 		<div class="tbox">
				 			<s:textfield id="blDate" name="blDate" cssClass="inputBox datepicker" readonly="true" disabled="#disable" />
				 		</div>	
				 	</div>
				 	<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
				 		<div class="text">
				 			<s:text name="quotation.sailingDate" />
				 		</div>
				 		<div class="tbox">
				 			<s:textfield id="sailingDate" name="sailingDate" cssClass="inputBox datepicker" readonly="true" disabled="#disable" />
				 		</div>	
				 	</div>
				</div>
				<div class="row" id="promotionalList">
					<s:if test='%{(promotionalCode!=null && !"".equals(promotionalCode)) || dubaiTradeStatus}'>
					 	<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4" >
					 		<div class="text">
					 			<s:text name="Promotional Code" />
					 		</div>
					 		<div class="tbox">
					 			<s:textfield name="promotionalCode" cssClass="inputBox tooltipContent" data-content=""  maxLength="25" disabled="#disable2"/>
					 		</div>	
					 	</div>
				 	</s:if>
				 	<s:else><s:hidden name="promotionalCode" value=""/></s:else>
				</div>
			 	<div class="row">
					<s:if test="brokerType == brokerOne">
					 	<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
					 		<div class="text">
					 			<s:text name="quotation.custContractNo" /><s:if test="%{productId==openCover}" ><font color="red">*</font></s:if>
					 		</div>
					 		<div class="tbox">
					 			<s:textfield name="custContractNo" cssClass="inputBox tooltipContent" data-content="Custome Contact No."  disabled="#disable2"/>
					 		</div>	
					 	</div>
					 	<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
					 		<div class="text">
					 			<s:text name="quotation.custFmsCaseNo" /><s:if test="%{productId==openCover}" ><font color="red">*</font></s:if>
					 		</div>
					 		<div class="tbox">
					 			<s:textfield name="custFmsCaseNo" cssClass="inputBox tooltipContent" data-content="Customer FMS Case No"  disabled="#disable2"/>
					 		</div>	
					 	</div>
					 	<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
					 		<div class="text">
					 			<s:text name="quotation.custRefNo" /><s:if test="%{productId==openCover}" ><font color="red">*</font></s:if>
					 		</div>
					 		<div class="tbox">
					 			<s:textfield name="custRefNo" cssClass="inputBox tooltipContent" data-content="Customer Reference no."  disabled="#disable2"/>
					 		</div>	
					 	</div>
				 	</s:if>
				 	<s:hidden name="brokerType"></s:hidden>
				</div>
			</div>
		</div>
	</div>
</div>
<div class="row">
	<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" align="center">
		<s:if test="%{endTypeId!=null && endTypeId.length() > 0}">
			<s:submit type="button" name="close"  key="Back" cssClass="btn btn-sm btn-danger" onclick="forward('initReport.action?menuType=ET')"/>&nbsp;&nbsp;&nbsp;
		</s:if>
			<s:submit name="SubmitGetQuote" id="SubmitGetQuote" type="submit" cssClass="btn btn-sm btn-success" value="Get Quote" onclick="disableSubmitGetQuote();disableForm(this.form,false,'')"/>
		&nbsp;&nbsp;&nbsp;
		<s:hidden name="applicationNo" id="applicationNo"/>
		<s:hidden name="refNo" />
		<s:hidden name="quoteNo" />
		<s:hidden name="loginId" />	                            
		<s:hidden name="quoteStatus" />
		<s:hidden name="endTypeId" />
		<s:hidden name="policyNo"/> 
		<s:hidden name="custName"/>
		<s:hidden name="brokerCompany"/>
		<s:hidden name="searchFrom" />
		<s:hidden name="searchBy" />
		<s:hidden name="searchValue" />
		<s:hidden name="finalizeYN" />
		<s:hidden name="editClausesYN" />
		<s:hidden name="rateChange" value="N"/>
			<s:submit name="Submit2" type="submit" cssClass="btn btn-sm btn-danger" value="Cancel" onclick="callAction('%{quoteStatus}','%{#session.user1}');" />
		<s:if test='%{"admin".equalsIgnoreCase(#session.user1) || "Admin".equals(#session.user1)}'>
		<s:hidden name="executive"/>
		</s:if>
	</div>
</div>
</s:form>
<script type="text/javascript">
function validNumber(val){
	var value=val.value;
	if(value!=null){
		val.value = value.replace(/[^,0-9]/g,'');
	}
}

function disableSubmitGetQuote(){
	document.getElementById('SubmitGetQuote').disabled=true;
}


if(document.quotation.endTypeId.value!=''){
	enableForm(document.quotation,false,'<s:property value="%{fields}"/>');
}
setBasisVal(document.quotation.saleTerm, 'percentList');

function setBasisVal(obj, id) {
	 var flag=true;
	 var value=obj.options[obj.selectedIndex].innerText;
	 if(value=='<s:text name="quotation.declaredValue"/>'){
	 	document.quotation.tolerance.disabled=true;
	 	document.quotation.saleTermPercent.disabled=true;
	 	document.quotation.saleTermDecVal.value=document.quotation.saleTerm.value;
	 	document.getElementById(id).disabled=true;
	 	document.quotation.saleTermPercent.value='';
	 	document.quotation.tolerance.value='<s:text name="quotation.toleranceDecVal"/>';
	 	flag=false;
	 }else{
	 	if('RA'!='<s:property value="%{quoteStatus}"/>' && document.quotation.endTypeId.value==''){
		 	document.quotation.tolerance.disabled=false;
		 	document.getElementById(id).disabled=false;
	 	}
	 	document.quotation.saleTermDecVal.value='';
	 }
	 return flag;
}

function getList(val, id) {
 	if(id=='percentList'){
 		if(setBasisVal(document.quotation.saleTerm, id)){
 			postRequest('${pageContext.request.contextPath}/'+id+'Quotation.action'+val, id);
 		}
 	}else{
 		postRequest('${pageContext.request.contextPath}/'+id+'Quotation.action'+val, id);
 	}
}

function commoditySelection(){
     var URL ='${pageContext.request.contextPath}/commodityListQuotation.action?applicationNo='+document.quotation.applicationNo.value+'&quoteStatus='+document.quotation.quoteStatus.value+'&refNo='+document.quotation.refNo.value+'&endTypeId='+document.quotation.endTypeId.value;
     return viewPopUp(URL);
}

function optionsSelection(option) {
    var URL ='${pageContext.request.contextPath}/optionsListQuotation.action?option='+option;
	return viewPopUp(URL);
}
function coreCustomerSearch() {
     var URL ='${pageContext.request.contextPath}/coreCustomerSearchQuotation.action';
	 return viewPopUp(URL);
}

function customerSelectionAction() {	
	var brokerCode='';
	var channelType = "";	
	if(document.quotation.brokerCode){			
		brokerCode=document.quotation.brokerCode.value;		
	}
	if(document.quotation.channelType) {
		channelType=document.quotation.channelType.value;
	}
     var URL ="${pageContext.request.contextPath}/customerSelectionQuotation.action?brokerCode="+brokerCode+"&channelType="+channelType+"&searchType=CUSTOMER";
     return viewPopUp(URL);
}
function lcSelectionAction() {
     var URL ='${pageContext.request.contextPath}/lcSelectionQuotation.action';
     return popUp(URL,'700','500');
}
if(document.getElementById("modeOfTransport").value=='<s:text name="LAND_TRANSIT"/>') {
	document.getElementsByName("originWarehouse")[0].checked=true;   
	document.getElementsByName("destWarehouse")[0].checked=true;  
	document.getElementsByName("originWarehouse")[1].disabled=true;   
	document.getElementsByName("destWarehouse")[1].disabled=true;
}
//exchageRate(document.getElementById("currency"),"currency");
<s:if test='!("admin".equalsIgnoreCase(#session.usertype) || "RSAIssuer".equalsIgnoreCase(#session.usertype))'>
 document.getElementById("exchageValue").disabled = true;
</s:if>
if(document.getElementById("lccurrency")){
	exchageRate(document.getElementById("lccurrency"),"lccurrency");
}
function exchageRate(obj,colnType){
	if(obj.value!=''){
	    var selected=document.getElementById(obj.value);
		with(selected){
			if(colnType=="currency"){
				document.getElementById("exchageValue").value=value;
				<s:if test='("admin".equalsIgnoreCase(#session.usertype) || "RSAIssuer".equalsIgnoreCase(#session.usertype))'>
					if("1"==obj.value)
				 	document.getElementById("exchageValue").disabled = true;
				 else
				 	document.getElementById("exchageValue").disabled = false;
				</s:if>	
				<s:else>
				 document.getElementById("exchageValue").disabled = true;
				</s:else>
				 							 						
			}else if(colnType=="exposureCurrency"){
				document.getElementById("exposureValue").value=value;
			}else{
				document.getElementById("lcexchageValue").value=value;
			}
		}
	}else{
		if(colnType=="currency"){
			document.getElementById("exchageValue").value='';
		}else if(colnType=="exposureCurrency"){
			document.getElementById("exposureValue").value=value;
		}else{
			document.getElementById("lcexchageValue").value='';
	 	}		
	}
}
function disableWarehouse(obj){
	var modeOfTrans=obj.value
	if(modeOfTrans=='<s:text name="LAND_TRANSIT"/>'){		 
		document.getElementsByName("originWarehouse")[0].checked=true;   
		document.getElementsByName("destWarehouse")[0].checked=true;  
		document.getElementsByName("originWarehouse")[1].disabled=true;   
		document.getElementsByName("destWarehouse")[1].disabled=true;	  
    } else {                    
   	 	document.getElementsByName("originWarehouse")[1].checked=true;   
		document.getElementsByName("destWarehouse")[1].checked=true;  
		document.getElementsByName("originWarehouse")[1].disabled=false;   
		document.getElementsByName("destWarehouse")[1].disabled=false;    
    }   
}
toggleWarSrcc(document.getElementById("modeOfTransport"))
function toggleWarSrcc(obj) {
	<s:if test='warOption=="Y"'>
	var modeOfTrans=obj.value;
	if(modeOfTrans=='3') {		 
	    document.getElementById("warSrccId").innerHTML='<s:text name="quotation.Srcc"/>'
    } else {                    
		document.getElementById("warSrccId").innerHTML='<s:text name="quotation.warSrcc"/>'    
    }
    </s:if>
}
disableOthers(document.getElementById("settlingAgent"));

function disableOthers(obj) {
	var value=obj.value;	
	if(value=='<s:text name="quotation.others.value"/>') {		
		document.getElementById("agentOthers").readOnly=false;
	} else {	
		document.getElementById("agentOthers").value="";
		document.getElementById("agentOthers").readOnly=true;		
	}		
}

function emptyCity(obj) {		
	if(obj=='<s:text name="quotation.dest"/>') {
		document.getElementById("destCity").value="";	
		document.getElementById("destCityName").value="";	
	} else {
		document.getElementById("originCity").value="";
		document.getElementById("originCityName").value="";
	} 
}

function originCitySelection() {	
	var countryId=document.getElementById("originCountry").value;	
	var URL ='${pageContext.request.contextPath}/countryCityListQuotation.action?originCountry='+countryId+'&countrySelect=O';
	return viewPopUp(URL);	
}

function destCitySelection() {	
	var countryId=document.getElementById("destCountry").value;
	var URL ='${pageContext.request.contextPath}/countryCityListQuotation.action?destCountry='+countryId+'&countrySelect=D';
	return viewPopUp(URL);	
}

function callvessel() {
	var URL ='${pageContext.request.contextPath}/vesselQuotation.action';
	return viewPopUp(URL);	
}	
function emptyCustInfo(){
	var s="poBox&mobileNo&email&title&customerName&coreAppCode&address2&address1&city"; 
	var stringToArray = new Array;
	stringToArray = s.split("&");
	for ( var int = 0; int < stringToArray.length; int++) {		
			var obj=stringToArray[int]
			document.getElementById(""+obj).value="";
	}
}
function custInfoDisable(channelType) {
	var issuer = '${issuer}';
	var s="poBox&mobileNo&email&title&coreAppCode&address2&address1&city";
	var stringToArray = new Array;
	stringToArray = s.split("&"); 
	if(issuer!=null && issuer!="" && channelType=="customer") {
		for ( var int = 0; int < stringToArray.length; int++) {
			var obj=stringToArray[int]
			document.getElementById(""+obj).disabled=true;
		}
	}
	else {
		for ( var int = 0; int < stringToArray.length; int++) {
			var obj=stringToArray[int]
			document.getElementById(""+obj).disabled=false;
		}
	}
}
function setVesselName() {
	document.getElementById('vesselName').value = "";
	document.getElementById('vesselId').value = "";
}
function callAction(status, userType) {
	if(userType=='admin' || userType=='Admin'){
		if(status=='RU'){
			document.quotation.action='${pageContext.request.contextPath}/getOCAjaxReferal.action?index=0';
		}else if(status=='RA'){
			document.quotation.action='${pageContext.request.contextPath}/getOCAjaxReferal.action?index=1';
		}else if(status=='RR'){
			document.quotation.action='${pageContext.request.contextPath}/getOCAjaxReferal.action?index=2';
		}
	}else{
		document.quotation.action='${pageContext.request.contextPath}/initReport.action?menuType=QE';
	}
	document.quotation.submit();
}
function forward(url) {
	document.quotation.action = "${pageContext.request.contextPath}/"+url;
	document.quotation.submit();
}

<s:if test='lcBankList!=null && lcBankList.size==1'>
         	getList('?lcBank=<s:property value="lcBankList[0].CODE"/>','lcList');
         </s:if>
         
function quotationDetail(val) {
  	if(val){
	  	document.getElementById('quoteInfo').style.display='';
	    document.getElementById('miuns').style.display='';
	    document.getElementById('plus').style.display='none';
    }else{
    	document.getElementById('quoteInfo').style.display='none';
	    document.getElementById('miuns').style.display='none';
	    document.getElementById('plus').style.display='';
    }
}

function textLimit(field, maxlen){
	if (field.value.length > maxlen + 1)
		alert('Maximum Length is '+maxlen);
	if (field.value.length > maxlen)
		field.value = field.value.substring(0, maxlen);
		checkSpecialChars(field);
}
function clearCustomerInfo() {
	var disableStatus = '<s:property value="#disable"/>';
	if(disableStatus!="disabled" && disableStatus!="true") {
		document.getElementById("title").value="";
		document.getElementById("address1").value="";
		document.getElementById("address2").value="";
		document.getElementById("mobileNo").value="";
		document.getElementById("city").value="";
		document.getElementById("poBox").value="";
		document.getElementById("customerName").value="";
		document.getElementById("coreAppCode").value="";
		document.getElementById("customerId").value="";
		document.getElementById("email").value="";
		<s:if test='%{productId==openCover && !((quoteStatus=="RA")||(endTypeId!=null && endTypeId.length() > 0))}'>
			document.getElementById("editCustomerYES").checked = true;
			editCustomerInfo();
		</s:if>
	}
}
<s:if test='%{productId==openCover && customerId.equals(#session.customer_id) && !(#disable)}'>
	editCustomerInfo();
</s:if>
function editCustomerInfo() {
	var chk = document.getElementById("editCustomerNO").checked;
	if( chk == true) {
		document.getElementById("title").disabled=true;
		document.getElementById("address1").disabled=true;
		document.getElementById("address2").disabled=true;
		document.getElementById("mobileNo").disabled=true;
		document.getElementById("city").disabled=true;
		document.getElementById("poBox").disabled=true;
		document.getElementById("customerName").disabled=true;
		document.getElementById("email").disabled=true;
		
		document.getElementById("editCustomerDIV").style.display = "";
	} else {
		document.getElementById("title").disabled=false;
		document.getElementById("address1").disabled=false;
		document.getElementById("address2").disabled=false;
		document.getElementById("mobileNo").disabled=false;
		document.getElementById("city").disabled=false;
		document.getElementById("poBox").disabled=false;
		document.getElementById("customerName").disabled=false;
		document.getElementById("email").disabled=false;
		
		document.getElementById("customerId").value="";
		document.getElementById("editCustomerDIV").style.display = "none";
	}
}
function toggleModeOfTransport(obj){
	var modeOfTrans=obj.value;
	if(modeOfTrans=='3'  ){
    	document.quotation.warSrcc.value='NO';
	} else {
	    	document.quotation.warSrcc.value='YES';
	} 
}
function viewPopUp(URL) {
	var bwidth = window.innerWidth;
	var bwidth1 = document.body.clientWidth;
	if(bwidth <= 768) {
		return popUp(URL,bwidth1,'500');
	} else {
		return popUp(URL,'750','500');
	}
}
</script>	
</body>
</html>