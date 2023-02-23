<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ page import="java.util.List,java.util.ArrayList,java.util.Map"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title></title>
</head>

<body  onload="getFinalPremium();">
<s:set var="disable1" value="%{endTypeId!=null && endTypeId.length() > 0}"/>
<div class="row">
	<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
		<s:form id="travel" name="travel" method="post" action="getGeratePolicyTravel.action" theme="simple">
		<div class="row">
			<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
				<span style="color:red;"> <s:actionerror/> </span>
			</div>
		</div>
		<div class="row">
			<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
				<div class="panel panel-default">
					<div class="panel-heading">
						<div class="row">
							<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
								<div class="btn-group" role="group" aria-label="...">							
								  <button type="button" title="Customer Information" class="btnGroupMargin btn btn-lg btn-warning"><i class="fa fa-user"></i></button>
								  <button type="button" title="Premium Comparison" class="btnGroupMargin btn btn-lg btn-success active"><i class="fa fa-eye"></i></button>
								</div>
							</div>
							<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6 text-success">
								<span class="textHeading"><s:text name="label.quoteDetail" /> </span> 
							</div>
						</div>
					</div>		
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
				<s:if test='#session.user1 == "admin"'>
					<div class="panel panel-primary">
		       			<div class="panel-heading txtB">
		       				<s:text name="PERSONAL INFO" />
		       			</div>
		       			<div class="panel-body">
		       				<div class="row">
		       					<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
		       						<s:text name="travel.customerName"/>&nbsp;:&nbsp;<b><s:property value="customerName"/></b>
		       					</div>
		       					<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
		       						<s:text name="travel.mobileNo" />&nbsp;:&nbsp;<b><s:property value="mobileNo"/></b>
		       					</div>
		       					<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
		       						<s:text name="travel.emailId" />&nbsp;:&nbsp;<b><s:property value="emailId"/></b>
		       					</div>
		       				</div>
		       			</div>
		       		</div>
	       		</s:if>
				<div class="panel panel-primary">
	       			<div class="panel-heading txtB">
	       				<s:text name="travel.policyDetails" />
	       				<span class="pullRight">
	       					<s:text name="travel.quoteNo"/>&nbsp;:&nbsp;<b><s:property value="quoteNo"/></b>
	       				</span>
	       			</div>
	       			<div class="panel-body">
	       				<div class="row">
	       					<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
	       						<div class="textV">
	       							<s:text name="travel.schemeCovers"/>			        							
	       						</div>
	       						<div class="tboxV">
	       							<s:property value="scheme_Covers"/>
	       						</div>
	       					</div>
	       					<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
	       						<div class="textV">
	       							<s:text name="travel.travelCovers"/>			        							
	       						</div>
	       						<div class="tboxV">
	       							<s:property value="travel_Covers"/>
	       						</div>
	       					</div>
	       					<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
	       						<div class="textV">
	       							<s:text name="travel.coverPeriod"/>			        							
	       						</div>
	       						<div class="tboxV">
	       							<s:property value="coverPeriod"/> &nbsp;<s:label  key="travel.days"/>
	       						</div>
	       					</div>
	       					<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
	       						<div class="textV">
	       							<s:text name="travel.effectiveDt"/>			        							
	       						</div>
	       						<div class="tboxV">
	       							<s:property value="inceptionDt"/>
	       						</div>
	       					</div>
	       					<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
	       						<div class="textV">
	       							<s:text name="travel.expiryDt"/>			        							
	       						</div>
	       						<div class="tboxV">
	       							<s:property value="expiryDt"/>
	       						</div>
	       					</div>
	       					<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
	       						<div class="textV">
	       							<s:text name="travel.Premium"/>			        							
	       						</div>
	       						<div class="tboxV" style="line-height: 12px;">
	       							<s:property value="#session.BrokerDetails.CurrencyAbb"/>&nbsp;<s:property value="getText('{0,number,#,##0.00}',{totalPremium})"/>&nbsp;<s:text name="travel.includingPremiumTax"/>
	       						</div>
	       						<br class="clear"/>
	       					</div>			        					
	       					<br class="clear"/>			        					
	       				</div>
	       				<br class="clear" />
	       				<div class="row">
	       					<table class="polDtls footable" width="100%" id="TravelTable">
	       						<thead>
	       						<tr>
	       							<th width="5%"> <s:text name="travel.sNo"/> </th>
									<th width="12.85%"> <s:text name="travel.first.name" /></th>
									<th width="12.85%"> <s:text name="travel.last.name" /></th>
									<th width="12.85%"> <s:text name="travel.dateOfBirth" /></th>
									<th width="12.85%"> <s:text name="travel.gender" /></th>
									<th width="12.85%"> <s:text name="travel.relations" /></th>
									<th width="12.85%"> <s:text name="travel.nationality" /></th>
									<th width="12.85%"><s:text name="travel.passport.number"/></th>
									<th width="12.85%"><s:text name="travel.passport.expiry.date" /></th>
	       							<s:if test='#session.user1 == "admin"'> 
		                           		<th width="12.85%"><s:text name="travel.Premium" /></th>
		                            </s:if>
	       						</tr>
	       						</thead>
	       						<tbody>
	       						<s:if test="travelList.size()>0">
	        						<s:iterator value="travelList" var="trList" status="stat">
		        						<tr>
		        							<td align="center">
		        								<s:property />
												<s:hidden name="travelList[%{#stat.count-1}]" id="travelList%{#stat.count-1}"/>
												<s:hidden name="serialNos[%{#stat.count-1}]" id="serialNos%{#stat.count-1}" />
		        							</td>
		        							<td align="right">
						             				<s:textfield name="travelNames[%{#stat.count-1}]" readonly="true" cssClass="inputBox"/>
					             			</td>
					             			<td align="right">
						             				<s:textfield name="travelLastNames[%{#stat.count-1}]" readonly="true" cssClass="inputBox"/>
					             			</td>
							      			<td align="right">
							      				<s:textfield name="dobs[%{#stat.count-1}]" id="dobs%{#stat.count-1}" readonly="true" cssClass="inputBox datePicker" />
					             			</td>
							      			<td align="right">
						             			<s:select list="#{'M':'Male','F':'Female'}" name="genders[%{#stat.count-1}]" id="genders[%{#stat.count-1}]"
						             				 headerKey="-1" headerValue="-Select-" disabled="true" cssClass="inputBoxS"/>
					             			</td>
						             		<td align="right">
						             			<s:select list="relationList" name="relations[%{#stat.count-1}]" id="relations[%{#stat.count-1}]"
						             				 headerKey="-1" headerValue="-Select-" listKey="CODE" listValue="CODEDESC"  disabled="true" cssClass="inputBoxS"/>
					             			</td>
					             			<td align="right">
						             			<s:select list="nationalityList" name="nationalitys[%{#stat.count-1}]" id="nationalitys[%{#stat.count-1}]" 
						             				headerKey="-1" headerValue="-Select-" listKey="CODEDESC" listValue="CODEDESC" disabled="true" cssClass="inputBoxS"/>
					             			</td>
					             			<td align="right"> <s:textfield name="passportNo[%{#stat.count-1}]" cssClass="inputBox"  readonly="true"/> </td>
											<td align="right"> <s:textfield name="passportExpDate[%{#stat.count-1}]" id="passportExpDate[%{#stat.count-1}]" cssClass="inputBox datePicker" readonly="true"/> </td>
											<s:if test='#session.user1 == "admin"'>
												<td>
													<s:textfield name="travelPremium[%{#stat.count-1}]" id="travelPremium%{#stat.count-1}" onkeyup="checkDecimals(this);" maxlength="15" cssClass="input"/>
							             		</td>
											</s:if>        											             		
		        						</tr>  
	       							</s:iterator>
	       						</s:if>
	       						</tbody>			        						
	       					</table>
	       				</div>
	       			</div>        		
	       		</div>
	       		<div class="row">
					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">					
						<s:include value="/pages/customer/customerDetailsMotor.jsp"/>
					</div>
				</div>
	       		<div class="panel panel-primary">
	       			<div class="panel-heading txtB">
	       				<s:text name="travel.coverages" />
	       			</div>
	       			<div class="panel-body">
	       				<div class="row">
							<table width="100%">
								<tbody>
								<s:if test="coveragesList.size()>0">
								<s:iterator value="coveragesList" var="cov" status="stat">
								<tr>
									<td width="40%">
										<s:property value="CODEDESC"/>
									</td>
									<s:if test='#session.user1 == "admin"'>
									<td width="25%">
										<s:radio list="#{'Y':'Included','N':'Excluded'}" name="coverages[%{#stat.count-1}]" id="coverages" />
									</td>									
									<td  width="10%">
										<s:textfield name="coveragePremium[%{#stat.count-1}]" id="coveragePremium%{#stat.count-1}" onkeyup="checkDecimals(this);" maxlength="15"/>
									</td>
									</s:if>	
									<s:else>
									<td width="25%">
										<s:radio list="#{'Y':'Included','N':'Excluded'}" name="coverages[%{#stat.index}]" id="coverages" disabled="true" />
									</td>
									</s:else>
								</tr>
								</s:iterator>
								</s:if>
								</tbody>
							</table>
	       				</div>
	       			</div>
	       		</div>
	       		<div class="panel panel-primary">
	       			<div class="panel-heading txtB">
	       				<s:text name="travel.premiumDetails" />
	       			</div>
	       			<div class="panel-body">
	       				<s:if test='#session.user1 != "admin" || quoteStatus != "RA"'>
	        				<div class="row">        					
	        					<div class="col-xs-12 col-sm-12 col-md-4 col-sm-4">
	        						<div class="row">
	        							<div class="col-xs-5"> <s:text name="travel.Premium"/> </div>
	        							<div class="col-xs-1"> <s:label key="#session.BrokerDetails.CurrencyAbb"/> </div>
	        							<div class="col-xs-5">
	        								<s:textfield name="finalPremium" id="finalPremium" cssClass="inputBox" readonly="%{#session.user1 != 'admin'?'true':'false'}" onkeyup="checkDecimals(this);" maxlength="15" theme="simple" cssStyle="text-align: right;" />
	        							</div>
	        						</div>
	        					</div>
	        					<div class="col-xs-12 col-sm-12 col-md-4 col-sm-4">
	        						<div class="row">
	        							<div class="col-xs-5"> <s:text name="travel.premium.tax"/> </div>
	        							<div class="col-xs-1"> <s:label key="#session.BrokerDetails.CurrencyAbb"/> </div>
	        							<div class="col-xs-5">
	        								<s:textfield name="policyFee" id="policyFee" cssClass="inputBox" readonly="true" theme="simple" cssStyle="text-align: right;" />
	        							</div>
	        						</div>
	        					</div>
	        					<div class="col-xs-12 col-sm-12 col-md-4 col-sm-4">
	        						<div class="row">
	        							<div class="col-xs-5">
	        								<s:if test='"0".equals(amendId)'>
												<s:text name="travel.totalPremiumPayable"/>
											</s:if>
											<s:else>
												<s:text name="travel.totalPremium"/>	
											</s:else>
	        							</div>
	        							<div class="col-xs-1"> <s:label key="#session.BrokerDetails.CurrencyAbb"/> </div>
	        							<div class="col-xs-5">
	        								<s:textfield name="totalPremium" id="totalPremium" cssClass="inputBox" readonly="true" theme="simple" cssStyle="text-align: right;" />
	        							</div>
	        						</div>
	        					</div>
	        					<s:if test='!"0".equals(amendId)'>
		        					<div class="col-xs-12 col-sm-12 col-md-4 col-sm-4">
		        						<div class="row">
		        							<div class="col-xs-5"> <s:text name="travel.premiumPaid"/> </div>
		        							<div class="col-xs-1"> <s:label key="#session.BrokerDetails.CurrencyAbb"/> </div>
		        							<div class="col-xs-5">
		        								<s:textfield name="premiumPaid" id="premiumPaid" cssClass="inputBox" readonly="true" maxlength="15" onchange="getTotalPremium(this.form)" cssStyle="text-align: right;"/>
		        							</div>
		        						</div>
		        					</div>
		        					<div class="col-xs-12 col-sm-12 col-md-4 col-sm-4">
		        						<div class="row">
		        							<div class="col-xs-5"> <s:text name="travel.endtPremium"/> </div>
		        							<div class="col-xs-1"> <s:label key="#session.BrokerDetails.CurrencyAbb"/> </div>
		        							<div class="col-xs-5">
		        								<s:textfield name="endtPremium" id="endtPremium" cssClass="inputBox" readonly="true" maxlength="15" onchange="getTotalPremium(this.form)" cssStyle="text-align: right;" />
		        							</div>
		        						</div>
		        					</div>
	        					</s:if>
	        				</div>
	       				</s:if>
	       				<s:else>
	       					<table class="footable" width="100%">
								<tr>
									<td class="text" style="width: 45%">
										<s:label key="travel.Premium"/>			        							
									</td>
									<td class="txtB" style="float: left; width: 10%;">
										<s:property value="#session.BrokerDetails.CurrencyAbb"/>
									</td>
									<td class="tbox" style="width: 45%">
										<s:textfield name="finalPremium" id="finalPremium" cssClass="inputBox" readonly="%{#session.user1 != 'admin'?'true':'false'}" onkeyup="checkDecimals(this);" maxlength="15" theme="simple" cssStyle="text-align: right;" />
									</td>
								</tr>
								<s:if test='#session.user1 == "admin" || quoteStatus == "RA"'>
									<tr>
										<td class="text" style="width: 45%">
											<s:label key="travel.discount"/>			        							
										</td>
										<td class="txtB" style="float: left; width: 10%;">
											<s:property value="#session.BrokerDetails.CurrencyAbb"/>
										</td>
										<td class="tbox" style="width: 45%">
											<s:textfield name="discountAmt" id="discountAmt" cssClass="inputBox" readonly="%{quoteStatus=='RA' && #session.user1 != 'admin'?'true':'false'}" onkeyup="checkDecimals(this);" maxlength="15" theme="simple" onchange="getTotalPremium(this.form);" cssStyle="text-align: right;" />
										</td>
									</tr>
									<tr>
										<td class="text" style="width: 45%">
											<s:label key="travel.loadingOrDiscountPremium"/>&nbsp;&nbsp;&nbsp;<s:select list="#{'+':'+','-':'-'}" id="sign" name="sign" disabled="%{quoteStatus=='RA' && #session.user1 != 'admin'?'true':'false'}" onchange="getTotalPremium(this.form);" cssStyle="text-align: right;" />		        							
										</td>
										<td class="txtB" style="float: left; width: 10%;">
											<s:property value="#session.BrokerDetails.CurrencyAbb"/>
										</td>
										<td class="tbox" style="width: 45%">
											<s:textfield name="loadOrDiscPremium" id="loadOrDiscPremium" cssClass="inputBox" readonly="%{quoteStatus=='RA' && #session.user1 != 'admin'?'true':'false'}" onkeyup="checkDecimals(this);" maxlength="15" theme="simple" onchange="getTotalPremium(this.form);" cssStyle="text-align: right;" />
										</td>
									</tr>
								</s:if>
								<tr>
									<td class="text" style="width: 45%">
										<s:label key="travel.premium.tax"/>		        							
									</td>
									<td class="txtB" style="float: left; width: 10%;">
										<s:property value="#session.BrokerDetails.CurrencyAbb"/>
									</td>
									<td class="tbox" style="width: 45%">
										<s:textfield name="policyFee" id="policyFee" cssClass="inputBoxs" readonly="true" theme="simple" cssStyle="text-align: right;" />
									</td>
								</tr>
								<tr>
									<td class="text" style="width: 45%">
										<s:if test='"0".equals(amendId)'>
											<s:label key="travel.totalPremiumPayable"/>
										</s:if>
										<s:else>
											<s:label key="travel.totalPremium"/>	
										</s:else>		        							
									</td>
									<td class="txtB" style="float: left; width: 10%;">
										<s:property value="#session.BrokerDetails.CurrencyAbb"/>
									</td>
									<td class="tbox" style="width: 45%">
										<s:textfield name="totalPremium" id="totalPremium" cssClass="inputBox" readonly="true" theme="simple" cssStyle="text-align: right;" />
									</td>
								</tr>
								<s:if test='!"0".equals(amendId)'>
									<tr>
										<td class="text" style="width: 45%">
											<s:label key="travel.premiumPaid"/>	        							
										</td>
										<td class="txtB" style="float: left; width: 10%;">
											<s:property value="#session.BrokerDetails.CurrencyAbb"/>
										</td>
										<td class="tbox" style="width: 45%">
											<s:textfield name="premiumPaid" id="premiumPaid" cssClass="inputBox" readonly="true" maxlength="15" onchange="getTotalPremium(this.form)" cssStyle="text-align: right;" />
										</td>
									</tr>
									<tr>
										<td class="text" style="width: 45%">
											<s:label key="travel.endtPremium"/>       							
										</td>
										<td class="txtB" style="float: left; width: 10%;">
											<s:property value="#session.BrokerDetails.CurrencyAbb"/>
										</td>
										<td class="tbox" style="width: 45%">
											<s:textfield name="endtPremium" id="endtPremium" cssClass="inputBox" readonly="true" maxlength="15" onchange="getTotalPremium(this.form)" cssStyle="text-align: right;" />
										</td>
									</tr>
								</s:if>
								<s:if test='#session.user1 == "admin" || (!"".equals(adminRemarks)&&(!null.equals(adminRemarks))&& #session.user1 != "admin")'>
									<tr>
										<td class="text" style="width: 45%">
											<s:label key="travel.specialCondition"/>    							
										</td>			        						
										<td class="tbox" style="width: 55%" colspan="2">
											<s:textarea name="adminRemarks" onkeyup="textLimit(this,'470')"  cols="50" rows="3" cssStyle="width: 100%" readonly="%{quoteStatus=='RA' && #session.user1 != 'admin'?'true':'false'}" cssClass="inputBoxA" />
										</td>
									</tr>
								</s:if>
								<s:if test='#session.user1 == "admin"'>
									<tr>
										<td class="text" style="width: 45%">
											<s:label key="travel.referralStatus"/>							
										</td>			        						
										<td class="tbox" style="width: 55%" colspan="2">
											<s:radio list="#{'Y':'Accept','N':'Reject','A':'Pending'}" name="adminRefStatus" value="%{adminRefStatus==null?'A':adminRefStatus}" />
										</td>
									</tr>
								</s:if>
							</table>
	       				</s:else>
      					<br class="clear"/>
      				</div>
      			</div>
      			<s:if test='#session.user1 != "admin"'>
	        		<s:if test='showReferralYN == "Y"'>
		        		<div class="panel panel-primary">
		        			<div class="panel-heading txtB">
		        				<s:text name="travel.referralInfo"/>
		        			</div>
		        			<div class="panel-body">
		        				<div class="row">
		        					<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
		        						<div class="text"><s:text name="travel.referralYN"/></div>
		        						<div class="tbox">
		        							<s:radio list="#{'Y':'Yes','N':'No'}" name="referralYN" id="referralYN"  onclick="disablePolicyOption(this.value);"/>
		        						</div>
		        					</div>
		        					<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
		        						<div class="text"><s:text name="travel.comments"/></div>
		        						<div class="tbox">
		        							<s:textarea name="referralComments" id="referralComments" cssClass="inputBoxA" cssStyle="width: 100%; resize: none;" onkeyup="textLimit(this,'200')"/>
		        						</div>
		        					</div>	        					        					
		        					<br class="clear"/>
		        				</div>
		        			</div>
		        		</div>
		        		<s:hidden name="brokerCode"></s:hidden>
	        		</s:if>
	        		
	        		<div class="panel panel-primary">        			
	        			<div class="panel-heading txtB" id="summaryPlus" onclick="getSummaryView(true);" style="cursor: pointer;">        				
	        				<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
	        					<i class="glyphicon glyphicon-chevron-down"></i> &nbsp;&nbsp;&nbsp; <s:text name="travelInfo.summary" />
	        				</div>
	        				<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4"> 
	          					<s:text name="travel.schemeCovers"/> : <s:property value="scheme_Covers"/>
	          				</div>
	          				<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4"> 
	          					<s:text name="travel.travelCovers"/> : <s:property value="travel_Covers"/>
	          				</div>          				
	          				<br class="clear"/>
	        			</div>
	        			<div class="panel-heading txtB" id="summaryMinus" onclick="getSummaryView(false);" style="display: none; cursor: pointer;">        				
	        				<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
	        					<i class="glyphicon glyphicon-chevron-up"></i> &nbsp;&nbsp;&nbsp; <s:text name="travelInfo.summary" />
	        				</div>
	        				<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4"> 
	          					<s:text name="travel.schemeCovers"/> : <s:property value="scheme_Covers"/>
	          				</div>
	          				<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4"> 
	          					<s:text name="travel.travelCovers"/> : <s:property value="travel_Covers"/>
	          				</div>          				
	          				<br class="clear"/>
	        			</div>        			
	        			<div class="panel-body" id="summaryData" style="display: none;">
	        				<div class="row">
	        					<s:if test="cover.size()>0">
	        					<table class="footable">
	        						<thead>
	        						<tr>
	        							<th width="50%">
	        								<s:text name="travelInfo.cover" />
	        							</th>
	        							<th width="25%">
	        								<s:text name="travelInfo.schme" />(<s:property value="#session.BrokerDetails.CurrencyAbb"/>)
	        							</th>
	        							<th width="25%">
	        								<s:text name="travelInfo.schme" />(<s:text name="travelInfo.destnCurrency" />)
	        							</th>
	        						</tr>
	        						</thead>
	        						<tbody>
		        						<s:iterator value="cover" id="cover">
		        						<tr>
		        							<td>
		        								<s:property value="CLAUSES_DESCRIPTION"/>
		        							</td>
		        							<td align="right">
		        								<s:property value="VALUE"/>
		        							</td>
		        							<td align="right">
		        								<s:property value="DISTINATION_VALUE"/>
		        							</td>
		        						</tr>
		        						</s:iterator>
	        						</tbody>
	        					</table>
	        					</s:if>
	        					<s:else>
									<table width="100%" border="0" align="center" cellpadding="1" cellspacing="0">
			                            <tr>
			                            	<td> <span class="label label-danger"> <s:text name="travel.noCoverAvailable"/> </span> </td>
										</tr>
								    </table>
								 </s:else>
	        				</div>
	        			</div>
	        		</div>
	        		<div class="row">
						<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
							<s:include value="/pages/payment/paymentInfo.jsp"/>
						</div>
					</div>
	        		<%--<div id="policyGeneration">
	        		    <div class="panel panel-primary" id="declarationDiv">
				       		<div class="panel-heading">
				       			<s:text name="label.declaration"/>
				       		</div>
				       		<div class="panel-body">
				       			<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
	        						<s:checkbox name="quoteEmailYN" fieldValue="Y" id="quoteEmailYN"/> Email Quote
	        					</div><br/>
				       			<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
					       			<s:checkbox name="generatePolicyYN" fieldValue="Y" id="generatePolicyYN" onclick="getMop(this.value);toggleEmailYN(this.value);" />
					       			<s:text name="label.declarationStatement"/><br/>
					       			<s:text name="label.declarationStatement1"/>
					       		</div>
				       		</div>
			        	</div>	
			        	<div class="row" id="modeOfPay" style="display: none;">
							<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
								<s:include value="/pages/payment/paymentInfo.jsp"/>
							</div>
						</div>
	        		</div>
	       		    --%><%--<div class="panel panel-primary" id="generatePolicyDiv"">
			       		<div class="panel-heading">
			       			<s:text name="travel.generatepolicy"/>
			       		</div>
	        			<div class="panel-body">
	        				<div class="row">
	        					<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
	        						<s:checkbox name="generatePolicyYN" fieldValue="Y" id="generatePolicyYN" onclick="getMop(this.value);toggleEmailYN(this.value);" /> Generate Policy
	        					</div>
	        					<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
	        						<s:checkbox name="quoteEmailYN" fieldValue="Y" id="quoteEmailYN"/> Email Quote
	        					</div>
	        					<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
	        						<s:checkbox name="policyEmailYN" fieldValue="Y" id="policyEmailYN"/> Email Policy
	        					</div>    
	        					<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4" id="modeOfPay" style="display: none;">			        						
	       							<s:text name="travel.modeOfPayment"/><br/>
	       							<s:radio name="modeOfPay" list="#{'Credit Card':'Credit Card','Cash':'Cash','Cheque':'Cheque'}" value="%{modeOfPay==null?'Cash':modeOfPay}"/>
	        					</div>
	        				</div>
	        			</div>
	        		</div>		        		 
	        	--%>
	        	</s:if>
			</div>			
      	</div>
      	<div class="row">
			<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" align="center">
				<br class="clear"/>
        		<input type="button" name="Submit1" class="btn btn-sm btn-danger" value="Back" onclick="getBack('quote');" />
       			&nbsp;&nbsp;&nbsp;
       			<s:if test='#session.user1 == "admin"'>
       			<input type="button" name="Submit2" class="btn btn-sm btn-primary" value="Calculate" onclick="getBack('admin');" />
       			&nbsp;&nbsp;&nbsp;
       			</s:if>
       			<%--<input type="button" name="Submit3" class="btn btn-sm btn-success" value="Proceed" onclick="disableForm(this.form,false,'');this.form.actionType.value='getProceed';this.form.submit();"/> --%>
       			<input type="button" name="Submit3" class="btn btn-sm btn-success" value="Proceed" onclick="disableForm(this.form,false,'');this.form.disMode.value='otpFlow';this.form.actionType.value='getProceed';this.form.submit();"/>
                <s:hidden name="display" />
                <s:hidden name="actionType" />
                <s:hidden name="applicationNo" />
                <s:hidden name="quoteNo"/>
                <s:hidden name="quoteStatus"/>
                <s:hidden name="endTypeId" />
                <s:hidden name="brokerCompany" />
                <s:hidden name="policyNo" />
                <s:hidden name="entryDate"/>
                <s:hidden name="cover"/>
                <s:hidden name="schemeCover"/>
                <s:hidden name="travelCover"/>
                <s:hidden name="amendId"/>
                <s:hidden name="scheme_Covers"/>
                <s:hidden name="travel_Covers"/>
                <s:hidden name="coverPeriod"/>
                <s:hidden name="inceptionDt"/>
                <s:hidden name="expiryDt"/>
                <s:hidden name="disMode"/>
			</div>
		</div>		 
		</s:form>
	</div>
</div>
<script type="text/javascript">
	appPath = "${pageContext.request.contextPath}/";
	if(document.travel.endTypeId!=null && document.travel.endTypeId.value!=''){
		enableForm(document.travel,false,'<s:property value="%{fields}"/>');
    }
    
    function getFinalPremium() {
    	<s:if test='#session.user1 == "admin"'>
    	var travelPremium=0.0;
		var coverPremium=0.0;
		var policyFee=0.0;
		for(var i=0;;i++)
		{
			if(document.getElementById("travelPremium"+i)!=null)
				travelPremium+=parseFloat(document.getElementById("travelPremium"+i).value);
			else
				break;
		}
		
		for(var i=0;;i++)
		{
			if(document.getElementById("coveragePremium"+i)!=null)
				coverPremium+=parseFloat(document.getElementById("coveragePremium"+i).value);
			else
				break;
		}
		var discountAmt=parseFloat(document.getElementById("discountAmt").value);
		var finalPremium=parseFloat(travelPremium)+parseFloat(coverPremium)-parseFloat(discountAmt);
		document.getElementById('finalPremium').value=finalPremium.toFixed(1);
    	var loadOrDiscPremium=parseFloat(document.getElementById('loadOrDiscPremium').value);
		var discount=parseFloat(document.getElementById('discountAmt').value);
		var sign=document.getElementById('sign').value;
		var policyFee=parseFloat(document.getElementById("policyFee").value);
		var val=0;
		if(sign=='+'){
			val=finalPremium+loadOrDiscPremium+policyFee;
			document.getElementById('totalPremium').value=val.toFixed(1);
		}else 
		{
			val=finalPremium-loadOrDiscPremium+policyFee;
			document.getElementById('totalPremium').value=val.toFixed(1);
		}
		if(0!='<s:property value="amendId"/>'){
		    var premiumPaid=parseFloat(frm.premiumPaid.value);
		    var endtPremium=val-premiumPaid;
		    document.getElementById('endtPremium').value=endtPremium.toFixed(1);
		}
		</s:if>
    }
    
	function getTotalPremium(frm)
	{
	var travelPremium=0.0;
	var coverPremium=0.0;
	var policyFee=0.0;
	for(var i=0;;i++)
	{
		if(document.getElementById("travelPremium"+i)!=null)
			travelPremium+=parseFloat(document.getElementById("travelPremium"+i).value);
		else
			break;
	}
	
	for(var i=0;;i++)
	{
		if(document.getElementById("coveragePremium"+i)!=null)
			coverPremium+=parseFloat(document.getElementById("coveragePremium"+i).value);
		else
			break;
	}
	var discountAmt=parseFloat(document.getElementById("discountAmt").value);
	var finalPremium=parseFloat(travelPremium)+parseFloat(coverPremium)-parseFloat(discountAmt);
	frm.finalPremium.value=finalPremium.toFixed(1);
	var loadOrDiscPremium=parseFloat(frm.loadOrDiscPremium.value);
	var discount=parseFloat(frm.discountAmt.value);
	var sign=frm.sign.value;
	var policyFee=parseFloat(document.getElementById("policyFee").value);
	var val=0;
	if(sign=='+'){
		val=finalPremium+loadOrDiscPremium+policyFee;
		frm.totalPremium.value=val.toFixed(1);
	}else 
	{
		val=finalPremium-loadOrDiscPremium+policyFee;
		frm.totalPremium.value=val.toFixed(1);
	}
	if(0!='<s:property value="amendId"/>'){
	    var premiumPaid=parseFloat(frm.premiumPaid.value);
	    var endtPremium=val-premiumPaid;
	    frm.endtPremium.value=endtPremium.toFixed(1);
	}
	}
	function getBack(page)
	{
		if(page=='home'){
			if('b2c'=='<s:property value="#session.b2c"/>')
				document.travel.action ='${pageContext.request.contextPath}/login/ProductSelection.jsp';
			else{
				if('RA'=='<s:property value="quoteStatus"/>')
					document.travel.action ='${pageContext.request.contextPath}/initReport.action?menuType=RA&loginId=<s:property value="loginId"/>';
				else if('RU'=='<s:property value="quoteStatus"/>')
					document.travel.action ='${pageContext.request.contextPath}/initReport.action?menuType=RU&loginId=<s:property value="loginId"/>';
				else if(('getSave'=='<s:property value="actionType"/>' || 'QS'=='<s:property value="quoteStatus"/>') && 0=='<s:property value="totalPremium"/>')
					document.travel.action ='${pageContext.request.contextPath}/initReport.action?menuType=QS&loginId=<s:property value="loginId"/>';
				else
					document.travel.action ='${pageContext.request.contextPath}/initReport.action?menuType=QE&loginId=<s:property value="loginId"/>';
			}
		}else if(page=='customer'){
			if('admin'=='<s:property value="#session.user1"/>'){
				/*if('RR'=='<s:property value="quoteStatus"/>')
					document.travel.action ='${pageContext.request.contextPath}/admin/HomeRejectedPolicyByDate.jsp';
				else
					document.travel.action ='${pageContext.request.contextPath}/admin/HomePendingPolicyByDate.jsp';*/
					document.travel.action ='${pageContext.request.contextPath}/getOCAjaxReferal.action';
			}else{
				if('ET'=='<s:property value="quoteStatus"/>')
					document.travel.action ='${pageContext.request.contextPath}/initReport.action?menuType=ET&custName=<s:property value="fullName"/>';
				else
					document.travel.action ='${pageContext.request.contextPath}/editCustomer.action';
			}
		}else if(page=='quote'){
			if('admin'=='<s:property value="#session.user1"/>'){
     			if('RA'=='<s:property value="quoteStatus"/>'){
					//document.travel.action ='${pageContext.request.contextPath}/admin/HomeApprovedPolicyByDate.jsp';
					document.travel.action ='${pageContext.request.contextPath}/getOCAjaxReferal.action';
				}else
					document.travel.action ='${pageContext.request.contextPath}/getOCAjaxReferal.action';
			}else{
    			if('RA'=='<s:property value="quoteStatus"/>')
					document.travel.action ='${pageContext.request.contextPath}/initReport.action?menuType=RA&loginId=<s:property value="loginId"/>';
				else
					document.travel.action ='${pageContext.request.contextPath}/initTravel.action';
			}
		}else if(page=='adminHome'){
			/*if('Y'=='<s:property value="adminRefStatus"/>')
				document.travel.action ='${pageContext.request.contextPath}/admin/HomeApprovedPolicy.jsp';
			else if('A'=='<s:property value="adminRefStatus"/>')
				document.travel.action ='${pageContext.request.contextPath}/admin/HomePendingPolicy.jsp';
			else if('N'=='<s:property value="adminRefStatus"/>')
				document.travel.action ='${pageContext.request.contextPath}/admin/HomeRejectedPolicy.jsp';*/
			document.travel.action ='${pageContext.request.contextPath}/getOCAjaxReferal.action';
		}
		else if(page=='admin'){			
			document.travel.action ='${pageContext.request.contextPath}/getCalculateTravel.action';
		}
		document.travel.submit();
	}	
addTotal();
function addTotal(){
	if(document.travel.schemeType){
	if(document.travel.schemeType.length){
		var policylen = document.travel.schemeType.length;
		for(var i=0;i<policylen;i++){
			var typeId = document.travel.schemeType[i].value;
			var obj =  document.getElementsByName("default"+typeId);
		<%--	if('<s:property value="referralMsg"/>'==null || '<s:property value="referralMsg"/>'==''){--%>
				var tot = 0;
				if(obj.length){
					var len = obj.length;
					for(var j=0;j<len;j++){
						tot = parseFloat(tot) + parseFloat(obj[j].value==""?0:obj[j].value);
					}
				}else{
					tot = parseFloat(tot) + parseFloat(obj.value==""?0:obj.value);
				}
				document.getElementById("total"+typeId).value = roundNumber(tot,0);
		<%--	}else{ 
				document.getElementById("total"+typeId).value = 'Referral';
			}--%>
			document.getElementById("totaldisplay"+typeId).innerHTML = document.getElementById("total"+typeId).value;
		}		
	}else{
		var typeId = document.travel.schemeType.value;
		var obj =  document.getElementsByName("default"+typeId);
	<%--	if('<s:property value="referralMsg"/>'==null || '<s:property value="referralMsg"/>'==''){--%>
			var tot = 0;
			if(obj.length){
				var len = obj.length;
				for(var j=0;j<len;j++){
					tot = parseFloat(tot) + parseFloat(obj[j].value==""?0:obj[j].value);
				}
			}else{
				tot = parseFloat(tot) + parseFloat(obj.value==""?0:obj.value);
			}
			document.getElementById("total"+typeId).value = roundNumber(tot,0);
     <%--   }else{ 
			document.getElementById("total"+typeId).value = 'Referral';
		}--%>
		document.getElementById("totaldisplay"+typeId).innerHTML = document.getElementById("total"+typeId).value;
	}
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

function getSummaryView(val) {
	if (val) {
		document.getElementById("summaryMinus").style.display = "block";
		document.getElementById("summaryData").style.display = "block";
		document.getElementById("summaryPlus").style.display = "none";
	} else {
		document.getElementById("summaryMinus").style.display = "none";
		document.getElementById("summaryData").style.display = "none";
		document.getElementById("summaryPlus").style.display = "block";
	}
}

/*function getMop(val) {
	if (document.getElementById("generatePolicyYN").checked) {
		document.getElementById("modeOfPay").style.display = "block";
	} else {
		document.getElementById("modeOfPay").style.display = "none";
	}
} 
if('<s:property value="generatePolicyYN"/>' =="Y"){
	document.getElementById("generatePolicyYN").checked = true;
	getMop('');
}
if('<s:property value="declarationYN"/>' == "Y") {
	document.getElementById("declarationYN").checked = true;
	toggleGeneratePolicy();
}*/
	/*function toggleGeneratePolicy() {
	   	if(document.getElementById('generatePolicyYN').checked) {
	    	document.getElementById('generatePolicyDiv').style.display = "";
	    } else {
	    	document.getElementById('generatePolicyDiv').style.display = "none";
	    	toggleEmailYN('');
	    	getMop('');
	    }
	}*/
	if("admin"!='<s:property value="user"/>' && '<s:property value="showReferralYN"/> == "Y"'){
		disablePolicyOption('<s:property value="referralYN"/>');
		toggleEmailYN('<s:property value="generatePolicyYN"/>');
	}
	function disablePolicyOption(value){
	 if(value=="Y"){
		document.getElementById('policyGeneration').style.display='none';
		document.getElementById('referralComments').readOnly=false;
		document.getElementById("generatePolicyYN").checked=false;
	 }   
	 else{   
	 	document.getElementById('policyGeneration').style.display='block';
	 	document.getElementById('referralComments').value='';
	 	document.getElementById('referralComments').readOnly=true;
	 } 
	}
	function toggleEmailYN(value){
	 if(document.getElementById('generatePolicyYN').checked){
		document.getElementById('quoteEmailYN').checked=false; 
	 	document.getElementById("quoteEmailYN").disabled=true;
		//document.getElementById("policyEmailYN").disabled=false;			
	 }   
	 else{  
	    document.getElementById("quoteEmailYN").disabled=false;
		//document.getElementById('policyEmailYN').checked=false;
		//document.getElementById("policyEmailYN").disabled=true; 			 
	 } 
	}
</script>
</body>
</html>
