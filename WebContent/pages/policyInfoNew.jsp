<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<style type="text/css">
	#loading {
  width: 100%;
  height: 100%;
  top: 0px;
  left: 0px;
  position: fixed;
  display: block;
  opacity: 0.7;
  background-color: black;
  z-index: 99;
  opacity:0.5;
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
<s:form name="policyInfo" id="policyInfo" theme="simple" action="initReport.action">
<div id="loading" style="width:100%">
   <img id="loading-image" src="${pageContext.request.contextPath}/images/madisonSymbolLogo.png"/>
</div>
<s:iterator value="policyInformation">
<div class="row">
	<s:set name="paymentDtl" value="%{paymentDetails}"/>
	<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
		<s:if test='#paymentDtl.PAYMENT_TYPE!=null && !"".equals(#paymentDtl.PAYMENT_TYPE) && !"1".equals(#paymentDtl.PAYMENT_TYPE) && !"2".equals(#paymentDtl.PAYMENT_TYPE)'>
			<s:if test='"ERROR".equals(#paymentDtl.RES_DECISION)'>
				<font color="red"><b style="padding: 20px 0 20px 0;">Paymet Failed..</b></font>
			</s:if>
		</s:if>
	</div>
	<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
		<div class="panel panel-primary">			
			<div class="container PolicyPage mt-5">
				<s:if test="POLICY_NO != null">
				<div class="Card_Parent PolicyInformation mt-4">
		            <div class="card card-5">
						<div class="container">
							<div class="VehicleTableheading">
								<div class="row">
								  <div class="col-md-12">
									<h3><s:text name="policyInfo.policyInfo" /></h3><hr>
								  </div>
							   </div>
							</div><br>
							<div class="panel-body" align="center">
								<font size="5px" style="font-weight: bold;">Your Policy Number is <font color="blue"><s:property value="POLICY_NO"/></font></font>
							</div>
						</div>
					</div>
				</div><br>
				</s:if>
				<%-- <div class="panel panel-primary">
					<div class="panel-heading">
						<s:label key="quotation.quoteInfo" />
					</div>
					<div class="panel-body">					
						<div class="row">
							<div class="col-xs-12 col-sm-12 col-md-3 col-lg-3">
								<table width="100%">
									<tr>
										<td width="40%"><b><s:label key="premiumInfo.quoteNo" /></b></td>
										<td width="60%">&nbsp;&#58;&nbsp;<s:property value="QUOTE_NO"/></td>
									</tr>
								</table>
						 	</div>
						 	<div class="col-xs-12 col-sm-12 col-md-3 col-lg-3">
						 		<table width="100%">
									<tr>
										<td width="40%"><b><s:label key="policyInfo.quotationDate" /></b></td>
										<td width="60%">
											&nbsp;&#58;&nbsp;
									 		<s:if test="POLICY_NO != null">
						                   		<s:property value="POL_DATE"/>
						                   	</s:if>
						                   	<s:else>
						                   		<s:property value="QUOTE_DATE"/>
						                   	</s:else>
										</td>
									</tr>
								</table>						 		
						 	</div>
						 	<div class="col-xs-12 col-sm-12 col-md-3 col-lg-3">
						 		<table width="100%">
									<tr>
										<td width="40%" valign="top"><b><s:label key="policyInfo.proposerName" /></b></td>
										<td width="60%">&nbsp;&#58;&nbsp;<s:property value="PROPOSER_NAME"/></td>
									</tr>
								</table>
						 	</div>
						 	<div class="col-xs-12 col-sm-12 col-md-3 col-lg-3">
						 		<table width="100%">
									<tr>
										<td width="40%"><b><s:label key="policyInfo.coverStartDate" /></b></td>
										<td width="60%">&nbsp;&#58;&nbsp;<s:property value="COVER_START_DATE"/></td>
									</tr>
								</table>
						 	</div>
						 	<div class="col-xs-12 col-sm-12 col-md-3 col-lg-3">
						 		<table width="100%">
									<tr>
										<td width="40%"><b><s:label key="policyInfo.coverEndDate" /></b></td>
										<td width="60%">&nbsp;&#58;&nbsp;<s:property value="COVER_END_PERIODS"/></td>
									</tr>
								</table>
						 	</div>
						 	<br class="clear" />
						</div>
					</div>
				</div> --%>
				<%-- <div class="container wrapper">
					
				    <div class="VehicleTableheading">
				       <div class="row">
				          <div class="col-md-4">
				            <h5>Quote Information</h5>
				          </div>
				       </div>
				    </div><br>
			     	<div class="row" style="padding-left: 15px;">
		     			<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
		     				<span style="font-family:Lato;" class="text"> <b><s:text name="motor.quoteNo"  /></b></span> &nbsp; : &nbsp; <s:property value="quoteNo"/> 
						</div>
						<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
		     				<span style="font-family:Lato;" class="text"> <b><s:text name="motor.quoteDate" /></b></span> &nbsp; : &nbsp; <s:property value="quoteDate"/> 
						</div>
						<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
		     				<span style="font-family:Lato;" class="text"> <b><s:text name="motor.product" /></b></span> &nbsp; : &nbsp; <s:property value="product"/>  
						</div>
						<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
		     				<span style="font-family:Lato;" class="text"> <b><s:text name="motor.customerName"  /></b></span> &nbsp; : &nbsp; <s:property value="title"/>.<s:property value="customerName"/>&nbsp;<s:property value="custLastName"/> 
						</div>
						<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
							<span style="font-family:Lato;" class="text"> <b><s:text name="motor.email" /></b></span> &nbsp; : &nbsp; <s:property value="email"/> 
						</div>
						<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
							<span style="font-family:Lato;" class="text"> <b><s:text name="motor.policyStartDt" /></b></span> &nbsp; : &nbsp; <s:property value="policyStartDate"/> 
						</div>
						<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
							<span style="font-family:Lato;" class="text"> <b><s:text name="motor.policyEndDt" /></b></span> &nbsp; : &nbsp; <s:property value="policyEndDatePeriod"/> 
						</div>
						<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
							<span style="color:red;font-family:Lato;" class="text"> <b><s:text name="Currency" /></b></span> &nbsp; : &nbsp; <s:property value="currencyType"/>
						</div>
					</div>
			    </div> --%>
			    <s:if test="POLICY_NO == null">
			    	<s:if test='"30".equalsIgnoreCase(#session.product_id)'>
			    		<div class="Card_Parent Coverdetails">
				        	<s:set value="editQuoteDetails"/>
				            <div class="card card-1">
				                <h4>Policy Information</h4>
				                <hr>
				                <div class="row">
				                    <div class="col-md-6 col-6">
				                        <label class="LabelHeading"><s:text name="motor.quoteNo"/></label>
				                    </div>
				                    <div class="col-md-6 col-6">
				                        <label class="labelValues"><s:property value="quoteNo"/></label>
				                    </div>
				                    <div class="col-md-6 col-6">
				                        <label class="LabelHeading"><s:text name="motor.customerName"/></label>
				                    </div>
				                    <div class="col-md-6 col-6">
				                        <label class="labelValues"><s:property value="customerFullName"/></label>
				                    </div>
				                    <div class="col-md-6 col-6">
				                        <label class="LabelHeading"><s:text name="motor.email" /></label>
				                    </div>
				                    <div class="col-md-6 col-6">
				                        <label class="labelValues"><s:property value="email"/></label>
				                    </div>
				                    <div class="col-md-6 col-6">
				                        <label class="LabelHeading"><s:text name="Mobile No." /></label>
				                    </div>
				                    <div class="col-md-6 col-6">
				                        <label class="labelValues"><s:property value="mobileNo"/></label>
				                    </div>
				                    <div class="col-md-6 col-6">
				                        <label class="LabelHeading"><s:text name="motor.policyStartDt" /></label>
				                    </div>
				                    <div class="col-md-6 col-6">
				                        <label class="labelValues"><s:property value="inceptionDt"/></label>
				                    </div>
				                    <div class="col-md-6 col-6">
				                        <label class="LabelHeading"><s:text name="motor.policyEndDt" /></label>
				                    </div>
				                    <div class="col-md-6 col-6">
				                        <label class="labelValues"><s:property value="expiryDt"/></label>
				                    </div>
				                </div>
				            </div>
				        </div>
			    	</s:if>
			    	<s:else>
					    <div class="Card_Parent PolicyInformation">
				            <div class="card card-5">
				                
				                <div class="row">
				                	<div class="col-md-12">
				                	
				                		<h3>Policy Information</h3><hr>
				                		
				                		<div class="row">
						                    <div class="col-md-5 col-6">
						                        <label class="LabelHeading"><s:text name="motor.quoteNo"  /></label>
						                    </div>
						                    <div class="col-md-5 col-6">
						                        <label class="labelValues"><s:property value="quoteNo"/></label>
						                    </div>
						                </div>
						                <div class="row">
						                    <div class="col-md-5 col-6">
						                        <label class="LabelHeading"><s:text name="motor.customerName"  /> </label>
						                    </div>
						                    <div class="col-md-5 col-6">
						                        <label class="labelValues"><s:property value="title"/>.<s:property value="customerName"/>&nbsp;<s:property value="custLastName"/></label>
						                    </div>
						                </div>
				                		<div class="row">
						                    <div class="col-md-5 col-6">
						                        <label class="LabelHeading"><s:text name="Policy Type"  /> </label>
						                    </div>
						                    <div class="col-md-5 col-6">
						                        <label class="labelValues"><s:property value="policyTypeDesc"/></label>
						                    </div>
						                </div>
				                	</div>
				                </div>
				                
				                
				                <div id="demo" class="collapse">
					                <%-- <div class="row">
					                    <div class="col-md-5 col-6">
					                        <label class="LabelHeading"><s:text name="motor.quoteNo"  /></label>
					                    </div>
					                    <div class="col-md-5 col-6">
					                        <label class="labelValues"><s:property value="quoteNo"/></label>
					                    </div>
					                </div> --%>
					                <div class="row">
					                    <div class="col-md-5 col-6">
					                        <label class="LabelHeading"><s:text name="motor.quoteDate" /></label>
					                    </div>
					                    <div class="col-md-5 col-6">
					                        <label class="labelValues"> <s:property value="quoteDate"/></label>
					                    </div>
					                </div>
					                <div class="row">
					                    <div class="col-md-5 col-6">
					                        <label class="LabelHeading"><s:text name="motor.product" /></label>
					                    </div>
					                    <div class="col-md-5 col-6">
					                        <label class="labelValues"><s:property value="product"/> </label>
					                    </div>
					                </div>
					                <%-- <div class="row">
					                    <div class="col-md-5 col-6">
					                        <label class="LabelHeading"><s:text name="motor.customerName"  /> </label>
					                    </div>
					                    <div class="col-md-5 col-6">
					                        <label class="labelValues"><s:property value="title"/>.<s:property value="customerName"/>&nbsp;<s:property value="custLastName"/></label>
					                    </div>
					                </div> --%>
					                <div class="row">
					                    <div class="col-md-5 col-6">
					                        <label class="LabelHeading"><s:text name="motor.email" /></label>
					                    </div>
					                    <div class="col-md-5 col-6">
					                        <label class="labelValues"><s:property value="email"/></label>
					                    </div>
					                </div>
					                 <div class="row">
					                    <div class="col-md-5 col-6">
					                        <label class="LabelHeading"><s:text name="motor.policyStartDt" /></label>
					                    </div>
					                    <div class="col-md-5 col-6">
					                        <label class="labelValues"><s:property value="policyStartDate"/></label>
					                    </div>
					                </div>
					                 <div class="row">
					                    <div class="col-md-5 col-6">
					                        <label class="LabelHeading"><s:text name="motor.policyEndDt" /></label>
					                    </div>
					                    <div class="col-md-5 col-6">
					                        <label class="labelValues"><s:property value="policyEndDatePeriod"/></label>
					                    </div>
					                </div>
					                 <div class="row">
					                    <div class="col-md-5 col-6">
					                        <font color="red"><label class="LabelHeading">Currency</label></font>
					                    </div>
					                    <div class="col-md-5 col-6">
					                        <label class="labelValues"><s:property value="currencyType"/></label>
					                    </div>
					                </div>
				                </div>
				                <div class="row">
				                	<div class="col-md-12 text-right">
				                		<a style="text-decoration:none" id="more" data-toggle="collapse" data-target="#demo" onclick="funCollapse('less')">View More <i class="fas fa-chevron-circle-down"></i></a>
				                		<a style="text-decoration:none;display:none" id="less" data-toggle="collapse" data-target="#demo" onclick="funCollapse('more')">View Less <i class="fas fa-chevron-circle-up"></i></a>
				                	</div> 
				                </div>
				            </div>
				        </div>
				    </s:else>
					    <br>
			    </s:if>
				<s:if test='#paymentDtl.PAYMENT_TYPE!=null && !"".equals(#paymentDtl.PAYMENT_TYPE) && !"1".equals(#paymentDtl.PAYMENT_TYPE) && !"2".equals(#paymentDtl.PAYMENT_TYPE) && !"9".equals(#paymentDtl.PAYMENT_TYPE)'>
					<div class="container">
						<div class="Card_Parent PolicyInformation mt-4">
				            <div class="card card-5">
								<div class="VehicleTableheading">
									<div class="row">
									  <div class="col-md-12">
										<h3><s:text name="Payment Information"/></h3><hr>
									  </div>
								   </div>
								</div>
								
								<div class="panel-body">					
									<div class="row" >
										<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
			        						<span class="text">	<s:label value="Reference No" /> </span> &nbsp; : &nbsp; <s:property value="#paymentDtl.MERCHANT_REFERENCE"/> 
										</div>
										<s:if test='"101".equals(#paymentDtl.PAYMENT_TYPE)'>
											<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
				        						<span class="text">	<s:label value="Response Tran Id" /> </span> &nbsp; : &nbsp; <s:property value="#paymentDtl.RESPONSE_TRAN_NO"/> 
											</div>
											<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
				        						<span class="text">	<s:label value="Response Status" /> </span> &nbsp; : &nbsp; <s:property value="#paymentDtl.RESPONSE_STATUS"/> 
											</div>
											<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
				        						<span class="text">	<s:label value="Response Message" /> </span> &nbsp; : &nbsp; <s:property value="#paymentDtl.RESPONSE_MESSAGE"/> 
											</div>
										</s:if>
										<s:else>
											<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
				        						<span class="text">	<s:label value="Response Tran Id" /> </span> &nbsp; : &nbsp; <s:property value="#paymentDtl.RES_TRANSACTION_ID"/> 
											</div>
											<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
				        						<span class="text">	<s:label value="Response Status" /> </span> &nbsp; : &nbsp; <s:property value="#paymentDtl.RES_DECISION"/> 
											</div>
											<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
				        						<span class="text">	<s:label value="Response Message" /> </span> &nbsp; : &nbsp; <s:property value="#paymentDtl.RES_MESSAGE"/> 
											</div>
										</s:else>
									</div>
								</div>
							</div>
						</div>
					</div>
				</s:if>
				<s:if test='"CCP".equals(QUOTE_STATUS) || "SP".equals(QUOTE_STATUS)'>
					<div class="Card_Parent PolicyInformation mt-4">
				            <div class="card card-5">
							<div class="container">				
								<div class="panel-body" align="center">
								  <s:if test='"CCP".equals(QUOTE_STATUS)'>
									<div style="color:red;font-size: 15px;">
										<b>
						   					<s:label key="policyInfo.approvalPending"/>
						   				</b>
					   				</div>
					   				</s:if>
					   				<s:if test='"SP".equals(QUOTE_STATUS)'>
										<div style="color:red;font-size: 15px;">
										    <b>
										    <s:label key="policyInfo.approvalPendingOnline1"/>
										    </b>
										    <br class="clear"/>
											<b>
							   					<s:label key="policyInfo.approvalPendingOnline"/>
							   				</b>
						   				</div>
					   				</s:if>
					   				<div class="row">
						   				<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4" align="center">
											<s:label key="Print Quote" />
										</div>
										<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4" align="center">
											<s:property value="QUOTE_NO"/>
										</div>
										<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4" align="center">
											<s:if test="%{#session.product_id == openCover}">
												<s:submit name="Submit2" type="button" cssClass="btn btn-sm btn-info" value="Print Quote" onclick="return getPopup('print4ScheduleOpen.OpenCertificate?policynumber=%{QUOTE_NO}&loginid=%{LOGIN_ID}&printoption=%{PDF_PRE_SHOW_STATUS}&bankerOption=%{PDF_BANKER_STATUS}&displayText=DRAFT&displayMode=draftMode&bankerAssuredOption=%{PDF_BANKER_ASSURED_STATUS}')"/>
											</s:if>
											<s:elseif test="%{#session.product_id == 31 || #session.product_id == 32 || #session.product_id == 33 || #session.product_id == 34 || #session.product_id == 41 || #session.product_id == 42}">
												<input type="button" name="Submit2" type="button" class="btn btn-sm btn-warning" value="Schedule" onclick="return popUp('PdfSummary_Schedule.Travel?quoteNo='+<s:property value="QUOTE_NO"/>,'1000','500');"/>
											</s:elseif>
											<s:else>
												<s:submit name="Submit2" type="button" cssClass="btn btn-sm btn-info" value="Print Quote" onclick="return getPopup('print4Schedule.pdfSchedule?policynumber=%{QUOTE_NO}&loginid=%{LOGIN_ID}&printoption=%{PDF_PRE_SHOW_STATUS}&bankerOption=%{PDF_BANKER_STATUS}&displayText=DRAFT&displayMode=draftMode&bankerAssuredOption=%{PDF_BANKER_ASSURED_STATUS}')"/>
											</s:else>  
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</s:if>
				<s:elseif test='%{#session.product_id == "65" && STATUS == "N" && "N".equals(paymentYN)}'>
					<div class="Card_Parent PolicyInformation mt-4">
			            <div class="card card-5">
							<div class="container">				
								<div class="panel-body" align="center">
									<div style="color:red;font-size: 15px;">
										<b>
						   					<s:text name="policyInfo.contactInsurance"/>
						   				</b>
					   				</div>
								</div>
							</div>
						</div>
					</div><br>
				</s:elseif>
				<s:elseif test='%{STATUS == "Y"}'>
					<div class="Card_Parent PolicyInformation mt-4">
				            <div class="card card-5">
							<div class="card container">
					     		<div class="card-body">
									<div style="color:red;font-size: 15px;" align="center">
									<b>
										<s:if test='%{#session.product_id == 30 && validCoverage!=null && !"".equals(validCoverage)}'>
											<s:property value="getText('label.error.validcoverage.referralmsg')"/> :<br/>${validCoverage}
										</s:if>
										<s:else>
											<s:property value="policyInfoReferralMsg"/><br/>
											<s:property value="referralMsg"/><br/>
										</s:else>						
										<br/>
										<%--<s:label key="policyInfo.referralMsg1"></s:label> <br/> --%>
										<s:label key="policyInfo.referralMsg"/><s:property value="REFERRAL_DESC"/>
									</b>
									</div>
									<br class="clear" />
									<div style="width: 100%; margin: 0 auto;">	
										<center><b><s:property value="premiumInfoReferralMsg"/></b></center>
									</div>
									<br class="clear" />
									<s:if test="adminuwList.size()>0">
										<table class="footable" width="100%">
											<thead>
											<tr>
												<th width="5%"><s:text name="S.No." /></th>
												<th width="31.66%"><s:text name="Name" /></th>
												<th width="31.66%"><s:text name="Email" /></th>
											</tr>
											</thead>
											<tbody>
											<s:iterator value="adminList" status="stat" var="adminIds">
											<tr>
												<td><s:property value="%{#stat.index+1}"/></td>
												<td><s:property value="%{#adminIds.USERNAME}"/></td>
												<td><s:property value="%{#adminIds.USER_MAIL}"/></td>
											</tr>
											</s:iterator>
											</tbody>
										</table>
									</s:if>
								</div>
							</div>
						</div>
					</div>
					
				</s:elseif>
				<s:elseif test='STATUS == "N"'>
				<%-- <div class="panel panel-primary">
					<div class="panel-heading">
						<s:label key="premiumInfo.premiumInfo" />
					</div>
					<div class="panel-body">
						<div class="row">
							<s:if test="%{#session.product_id == 31 || #session.product_id == 32 || #session.product_id == 33 || #session.product_id == 34 || #session.product_id == 41 || #session.product_id == 65 || #session.product_id == 30}">
								<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" align="center">
									<!-- <b><s:label key="travel.Premium" />&nbsp;<s:property value="#session.BrokerDetails.CurrencyAbb"/></b> &nbsp;&#58;&nbsp; <s:property value="getText('{0,number,#,##0.00}',{PREMIUM})"/> -->
									<b><s:label key="travel.Premium" />&nbsp;<s:property value="currencyType"/></b> &nbsp;&#58;&nbsp; <s:property value="getText('{0,number,#,##0.00}',{PREMIUM})"/>
								</div>
							</s:if>
							<s:else>
								<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" align="center">
									<b><s:label key="policyInfo.totalInsuredValueUSD" />&nbsp;<s:property value="#session.BrokerDetails.CurrencyAbb"/></b> &nbsp;&#58;&nbsp; <s:property value="getText('{0,number,#,##0.00}',{EQUIVALENT})"/> <br/>
									<b><s:label key="premiumInfo.showPremium" />&nbsp;<s:property value="#session.BrokerDetails.CurrencyAbb"/></b> &nbsp;&#58;&nbsp; <s:property value="getText('{0,number,#,##0.00}',{PREMIUM})"/>
								</div>
							</s:else>
							<br class="clear" />
						</div>
					</div>
				</div> --%>
				<div class="Card_Parent PolicyInformation mt-4">
				    <div class="card card-5">
						<div class="container">
							<div class="VehicleTableheading">
						       <div class="row">
						          <div class="col-md-12">
						            <h3><s:text name="Premium Information" /></h3><hr>
						          </div>
						       </div>
						    </div><br>
						    <div class="row">
								<s:if test="%{#session.product_id == 31 || #session.product_id == 32 || #session.product_id == 33 || #session.product_id == 34 || #session.product_id == 41 || #session.product_id == 65 || #session.product_id == 30}">
									<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" align="center">
										<!-- <b><s:label key="travel.Premium" />&nbsp;<s:property value="#session.BrokerDetails.CurrencyAbb"/></b> &nbsp;&#58;&nbsp; <s:property value="getText('{0,number,#,##0.00}',{PREMIUM})"/> -->
										<b><s:label key="travel.Premium" />&nbsp;<s:property value="currencyType"/></b> &nbsp;&#58;&nbsp; <s:property value="getText('{0,number,#,##0.00}',{Premium})"/>
									</div>
								</s:if>
								<s:else>
									<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" align="center">
										<b><s:label key="policyInfo.totalInsuredValueUSD" />&nbsp;<s:property value="#session.BrokerDetails.CurrencyAbb"/></b> &nbsp;&#58;&nbsp; <s:property value="getText('{0,number,#,##0.00}',{EQUIVALENT})"/> <br/>
										<b><s:label key="premiumInfo.showPremium" />&nbsp;<s:property value="#session.BrokerDetails.CurrencyAbb"/></b> &nbsp;&#58;&nbsp; <s:property value="getText('{0,number,#,##0.00}',{Premium})"/>
									</div>
								</s:else>
								<br class="clear" />
							</div>
						</div>
					</div>
				</div>		
				<div class="Card_Parent PolicyInformation mt-4">
				    <div class="card card-5">
						<div class="container">
							<div class="VehicleTableheading">
								<div class="row">
							        <div class="col-md-12">
										<h3><s:text name="policyInfo.documentInfo" /></h3><hr>
									</div>
								</div>
							</div><br>
							<div class="">
								<div class="row">
									<s:if test="POLICY_NO == null">
										<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4" align="center">
											<b><s:label key="Print Quote" /></b>
										</div>
										<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4" align="center">
											<s:property value="QUOTE_NO"/>
										</div>
										<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4" align="center">
											<s:if test="%{#session.product_id == openCover}">
												<s:submit name="Submit2" type="button" cssClass="btn btn-sm btn-info" value="Print Quote" onclick="return getPopup('print4ScheduleOpen.OpenCertificate?policynumber=%{QUOTE_NO}&loginid=%{LOGIN_ID}&printoption=%{PDF_PRE_SHOW_STATUS}&bankerOption=%{PDF_BANKER_STATUS}&displayText=DRAFT&displayMode=draftMode&bankerAssuredOption=%{PDF_BANKER_ASSURED_STATUS}')"/>
											</s:if>
											<s:elseif test="%{#session.product_id == 31 || #session.product_id == 32 || #session.product_id == 33 || #session.product_id == 34 || #session.product_id == 41 || #session.product_id == 42}">
												<input type="button" name="Submit2" type="button" class="btn btn-sm btn-warning" value="Schedule" onclick="return popUp('PdfSummary_Schedule.Travel?quoteNo='+<s:property value="QUOTE_NO"/>,'1000','500');"/>
											</s:elseif>
											<s:else>
												<s:submit name="Submit2" type="button" cssClass="btn btn-sm btn-info" value="Print Quote" onclick="return getPopup('print4Schedule.pdfSchedule?policynumber=%{QUOTE_NO}&loginid=%{LOGIN_ID}&printoption=%{PDF_PRE_SHOW_STATUS}&bankerOption=%{PDF_BANKER_STATUS}&displayText=DRAFT&displayMode=draftMode&bankerAssuredOption=%{PDF_BANKER_ASSURED_STATUS}')"/>
											</s:else>  
										</div>
									</s:if>
									<br class="clear"/>
									<s:if test="POLICY_NO != null">
										<s:if test='%{#session.product_id == "65"}'>
											<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" align="center">
					       						<s:set name="multiVehicleDtls" value="%{documentinfo}"/>
												<table class="table table-bordered table-hover" cellspacing="0" width="100%" id="tadaTable">
													<thead  class="bluecolortable">
												        <tr>
															<th><s:label value="S.No." /></th>
															<th><s:text name="motor.vehicleUsage"/></th>
															<th><s:label key="motor.make" /></th>
															<th><s:label key="motor.model" /></th>
															<th><s:text name="motor.typeOfBody" /></th>
															<th><s:label key="motor.sumInsured" /></th>
															<th></th>
														</tr>
													</thead>
													<tbody>
														<s:iterator value="#multiVehicleDtls" var="view" status="status">
															<tr>
																<td><s:property value="#status.count" /></td>
																<td><s:property value="#view.VehicleUsage" /></td>
																<td><s:property value="#view.Make" /></td>					
																<td><s:property value="#view.Model" default=" " /> </td>
																<td><s:property value="#view.BodyType" /></td>
																<td> <s:property value="#view.VehicleValue"/> </td>
																<td align="center">
																	<%-- <s:submit type="button" name="Submit2" cssClass="btn btn-sm btn-warning" value="Certificate" onclick="return getFleetPdf('%{#view.QUOTE_NO}','%{#view.VEHICLE_ID}')"/> --%>
																	<s:submit type="button" name="Submit2" cssClass="btn btn-sm btn-warning" value="Certificate" onclick="return getFleetPdf('%{quoteNo}','%{vehicleId}')"/>
																</td>
															</tr>
														</s:iterator>
													</tbody>
												</table>
												<br class="clear"/>
											</div>
			        						<br class="clear"/>
			        					</s:if>
										<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4" align="center">
											<b><s:label key="policyInfo.schedule" /></b>
										</div>
										<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4" align="center">
											<s:property value="POLICY_NO"/>
										</div>
										<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4" align="center">
											<s:if test="%{#session.product_id == 31 ||#session.product_id == 32|| #session.product_id == 33 || #session.product_id == 34 || #session.product_id == 41}">
						                 	 	<input type="button" name="Submit2" type="button" class="btn btn-sm btn-info" value="Schedule" onclick="return getPopup('PdfSummary_Schedule.Travel?quoteNo='+<s:property value="QUOTE_NO"/>);"/>
						                  	</s:if>
						                  	<s:else>
						                  		<s:submit name="Submit2" type="button" cssClass="btn btn-sm btn-info" value="Schedule" onclick="return getPopup('Copyofinformation.jsp?policyMode=schedule&policynumber=%{POLICY_NO}&loginid=%{LOGIN_ID}')"/>
						                  	</s:else>
										</div>
										<br class="clear" />
										<s:if test="%{endTypeId!=null && endTypeId.length() > 0 && (#session.product_id == 3|| #session.product_id == 11)}">
											<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4" align="center">
												<b><s:label key="policyInfo.endtSchedule" /></b>
											</div>
											<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4" align="center">
												<s:property value="POLICY_NO"/>
											</div>
											<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4" align="center">
												<s:submit name="Submit2" type="button" cssClass="btn btn-sm btn-info" value="Endorsement" onclick="return getPopup('documentReport.action?policyNo=%{POLICY_NO}&applicationNo=%{applicationNo}&endTypeId=%{endTypeId}')"/>
											</div>
										</s:if>
									</s:if>
									<br class="clear"/>
									<s:if test="%{DEBIT_NOTE_NO != null}">
			               		  		<s:if test="%{#session.product_id == 31 || #session.product_id == 32 || #session.product_id == 33 || #session.product_id == 34 || #session.product_id == 41}">
			               		  			<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4" align="center">
												<b><s:label key="policyInfo.debit" /></b>
											</div>
											<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4" align="center">
												<s:property value="DEBIT_NOTE_NO"/>
											</div>
											<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4" align="center">
												<input type="button" name="Submit3" type="button" class="btn btn-sm btn-info" value="Debit Note" onclick="return getPopup('PdfSummary_Debit.Travel?quoteNo='+<s:property value="QUOTE_NO"/>);"/>
											</div>
											<br class="clear" />
										</s:if>
			               		  		<s:else>
			               		  			<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4" align="center">
												<b><s:label key="policyInfo.debit" /></b>
											</div>
											<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4" align="center">
												<s:property value="DEBIT_NOTE_NO"/>
											</div>
											<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4" align="center">
												<s:submit name="Submit2" type="submit" cssClass="btn btn-sm btn-info" key="policyInfo.debit" onclick="return getPopup('Copyofinformation.jsp?policyMode=debit&policynumber=%{POLICY_NO}&loginid=%{LOGIN_ID}')"/>
											</div>
											<br class="clear" />
			               		  		</s:else>
			               		  	</s:if><br class="clear"/>
			               		  	<s:if test="%{RECEIPT_NO != null}">
			               		  		<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4" align="center">
											<b><s:label key="policyInfo.receipt" /></b>
										</div>
										<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4" align="center">
											<s:property value="RECEIPT_NO"/>
										</div>
										<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4" align="center">
											<input type="button" name="Submit3" type="button" class="btn btn-sm btn-info" value="Receipt" onClick="getReceiptPdf('<s:property value="QUOTE_NO"/>')"/>
										</div>
										<br class="clear"/>
			               		  	</s:if>
			               		  	<br class="clear"/>
			               		  	<s:if test="%{#session.product_id == 41}">
			               		  		<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4" align="center">
											Bill No
										</div>
										<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4" align="center">
											1111
										</div>
										<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4" align="center">
											<input type="button" name="Submit2" class="btn btn-sm btn-info" type="submit" value="Bill" />
										</div>
										<br class="clear" />
										<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4" align="center">
											CCHI
										</div>
										<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4" align="center">
											2222
										</div>
										<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4" align="center">
											<input type="button" name="Submit2" class="btn btn-sm btn-info" type="submit" value="CCHI" />
										</div>
										<br class="clear" />
			               		  	</s:if>
			               		  	<s:if test="%{CREDIT_NOTE_NO != null}">
										<s:if test='"Y".equalsIgnoreCase(creditNoteStatus)'>
											<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4" align="center">
												<s:label key="policyInfo.credit" />
											</div>
											<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4" align="center">
												<s:property value="CREDIT_NOTE_NO"/>
											</div>
											<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4" align="center">
												<s:submit name="Submit2" type="submit" cssClass="btn btn-sm btn-info" key="policyInfo.credit" onclick="return getPopup('Copyofinformation.jsp?policyMode=credit&policynumber=%{POLICY_NO}&loginid=%{LOGIN_ID}')"/>
											</div>
											<br class="clear" />
										</s:if>
									</s:if>
									<s:if test="%{#session.product_id == 3 && POLICY_NO != null}">
										<br class="clear" />
										<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4" align="center">
											<s:label key="policyInfo.policyWording" />
										</div>
										<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4" align="center">
											<s:property value="POLICY_NO"/>
										</div>
										<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4" align="center">
											<s:submit name="Submit3" type="submit" cssClass="btn btn-sm btn-info" key="policyInfo.policyWording" onclick="return getPopup('Copyofinformation.jsp?policyMode=clauses&policynumber=%{POLICY_NO}&loginid=%{LOGIN_ID}')"/>
										</div>
										<br class="clear" />
									</s:if>
									<s:if test='%{#session.product_id == 31 || #session.product_id == 32 || #session.product_id == 33 || #session.product_id == 34}'>
										<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4" align="center">
											<s:label key="policyInfo.policyTermsAndCondition" />
										</div>
										<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4" align="center">
											<s:property value="POLICY_NO"/>
										</div>
										<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4" align="center">
											<input type="button" name="Submit3" type="button" class="btn btn-sm btn-info" value="Download" onclick="return getPopup('PdfSummary_PolicyWording.Travel?quoteNo==%{QUOTE_NO}');"/>
										</div>
										<br class="clear" />
									</s:if>
									<%--<s:if test='%{#session.product_id=="65" ||#session.product_id=="30"}'>
										<br class="clear" />
										<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4" align="center">
											<s:label key="policyInfo.policyWording" />
										</div>
										<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4" align="center">
											<s:property value="POLICY_NO"/>
										</div>
										<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4" align="center">
											<s:submit name="Submit3" type="submit" cssClass="btn btn-sm btn-info" key="policyInfo.policyWording" onclick="getPolicyWordingPdf(%{QUOTE_NO})"/>
										</div>
										<br class="clear" />
									</s:if>--%>
								</div>
							</div>
						</div>
					</div>
				</div>
				</s:elseif>
			</div>
		</div>
	</div>
</div><br>
<div class="row">
	<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" align="center">
		<s:if test="%{endTypeId!=null && endTypeId.length() > 0}">
			<s:hidden name="menuType" value="P"/>
		</s:if>
		<s:elseif test='STATUS=="Y"'>
			<s:hidden name="menuType" value="RU"/>
		</s:elseif>
		<s:else>
			<s:if test="%{#session.product_id == 3 || #session.product_id == 11}">
				<s:hidden name="menuType" value="%{POLICY_NO==null?'QE':'P'}"/>
			</s:if>
			<s:else>
				<s:if test="%{quoteStatus==null || quoteStatus==''}">
					<s:hidden name="menuType" value="%{POLICY_NO==null?menuType=='RA'?'RA':'QE':'P'}"/>
				</s:if>
				<s:else>
					<s:hidden name="menuType" value="%{POLICY_NO!=null?'P':quoteStatus}"/>
				</s:else>
			</s:else>
		</s:else>    
		<div class="container">
			<div class="row">
				<div class="col-lg-2 col-md-3 offset-md-3 offset-lg-4 mb-3">
					<s:if test='%{POLICY_NO==null && !"CCP".equalsIgnoreCase(QUOTE_STATUS) && !"SP".equalsIgnoreCase(QUOTE_STATUS)}'>  
						<s:if test="%{#session.product_id == 31 || #session.product_id == 32 || #session.product_id == 33 || #session.product_id == 34}">
							<s:if test='referralMsg==null || "".equalsIgnoreCase(referralMsg)'>
								<s:submit name="Submit" type="submit" cssClass="btn btn-sm btn-danger btn-block" value="Back"  onclick="update('showQuoteTravel.action')"/> &nbsp;&nbsp;&nbsp;
							</s:if>
							<s:else>
								<s:submit name="Submit" type="submit" cssClass="btn btn-sm btn-danger btn-block" value="Back"  onclick="update('initTravel.action')"/> &nbsp;&nbsp;&nbsp;																
							</s:else>
						</s:if>
						<s:elseif test="%{#session.product_id == 41}">																
							<s:submit name="Submit" type="submit" cssClass="btn btn-sm btn-danger btn-block" value="Back"  onclick="update('showQuoteHealth.action')"/> &nbsp;&nbsp;&nbsp;
						</s:elseif>
						<s:elseif test="%{#session.product_id == 65}">
							<s:if test='referralMsg==null || "".equalsIgnoreCase(referralMsg) || "RA".equals(quoteStatus)'>
								<s:submit name="Submit" type="submit" cssClass="btn btn-sm btn-danger btn-block" value="Back"  onclick="update('showPolicyInfoNewMotor.action')"/>
							</s:if>
							<s:else>
								<%--<s:submit name="Submit" type="submit" cssClass="btn btn-sm btn-danger" value="Back"  onclick="update('editQuoteMotor.action')"/> --%>
								<s:submit name="Submit" type="submit" cssClass="btn btn-sm btn-danger btn-block" value="Back"  onclick="update('getBackVehicleMotor.action')"/>
							</s:else>
						</s:elseif>
						<s:elseif test='%{#session.product_id=="30"}'>
							<s:if test='%{validCoverage!=null && !"".equals(validCoverage)}'>
								<s:submit name="Submit" type="submit" cssClass="btn btn-sm btn-danger btn-block" value="Back"  onclick="update('uwMenuHome.action')"/>
							</s:if>
							<s:else>
								<s:submit name="Submit" type="submit" cssClass="btn btn-sm btn-danger btn-block" value="Back"  onclick="update('showQuoteHome.action')"/>
							</s:else>
						</s:elseif>
						<s:else>
							<s:submit name="Submit" type="submit" cssClass="btn btn-sm btn-danger btn-block" value="Back"  onclick="update('initPremium.action')"/> &nbsp;&nbsp;&nbsp;
						</s:else>  
					</s:if>
				</div>
				<div class="col-lg-2 col-md-3 mb-3">
					<s:if test='#session.b2c == "b2c" || #session.LoginType=="B2C"'>
						<s:submit name="Submit" type="submit" cssClass="btn btn-sm btn-success btn-block" value="Proceed"  onclick="getHome()"/>
					</s:if>
					<s:else>
						<s:submit name="Submit" type="submit" cssClass="btn btn-sm btn-success btn-block" value="Proceed"  onclick=""/>
					</s:else>
				</div>
			</div>
		</div>
	</div>
</div>
<s:hidden name="loginId" value="%{LOGIN_ID}"/> 
</s:iterator>
<s:hidden name="applicationNo"/> 
<s:hidden name="quoteNo"/> 
<s:hidden name="policyNo"/> 
<s:hidden name="custName"/> 
<s:hidden name="refNo"/> 
<s:hidden name="openCoverNo"/>
<s:hidden name="quoteStatus" />
<s:hidden name="endTypeId" />
<s:hidden name="brokerCompany"/>
<s:hidden name="searchFrom" />
<s:hidden name="searchBy" />
<s:hidden name="searchValue" />
<s:hidden name="referralMsg"/>
<s:hidden name="menuFrom" value="%{quoteStatus}"/>
<s:hidden name="vehicleId"/>
<s:hidden name="policyTypeDesc"  id="policyTypeDesc"/>
<s:hidden name="policyType"/>
<s:hidden name="policyStartDate"/>
<s:hidden name="policyEndDate"/>
<s:hidden name="gquoteNo" id="gquoteNo"/>
<s:hidden name="gvehicleId" id="gvehicleId"/>



</s:form>
</body>
<script type="text/javascript">
function funCollapse(type){
	if(type=='more'){
		$( '#less' ).attr('style','display:none;');
		$( '#more' ).attr('style','display:block;');
	}
	else if (type=='less'){
		$( '#more' ).attr('style','display:none;');
		$( '#less' ).attr('style','display:block;');
	}
}
function update(actionPath) { 
	document.policyInfo.action = actionPath;
	document.policyInfo.submit();
	return false;
}
function getHome() {
	document.policyInfo.action ='${pageContext.request.contextPath}/login/ProductSelection.jsp'
	document.policyInfo.submit();
	return false;
}
function getPopup(URL) {
	var bwidth = window.innerWidth;
	var bwidth1 = document.body.clientWidth;
	if(bwidth <= 768) {
		return popUp(URL,bwidth1,'500');
	} else {
		return popUp(URL,'750','500');
	}
}	
function getReceiptPdf(val) {
	var URL ='${pageContext.request.contextPath}/receiptReport.action?quoteNo='+val;
	var bwidth = window.innerWidth;
	var bwidth1 = document.body.clientWidth;
	if(bwidth <= 768) {
		return popUp(URL,bwidth1,'500');
	} else {
		return popUp(URL,'750','500');
	}
}
function getFleetPdf(quoteNo, vehicleId) {
	var URL ='${pageContext.request.contextPath}/getCertificateReport.action?quoteNo='+quoteNo+'&vehicleId='+vehicleId;
	var bwidth = window.innerWidth;
	var bwidth1 = document.body.clientWidth;
	if(bwidth <= 768) {
		return popUp(URL,bwidth1,'500');
	} else {
		return popUp(URL,'750','500');
	}
}
function getPolicyWordingPdf(val){
	var URL ='${pageContext.request.contextPath}/policyWordingReport.action?quoteNo='+val;
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