<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
</head>
<body>
<s:iterator value="quotationInfo" status="stat" var="quotation">
<s:form id="premiumInfo" name="premiumInfo" method="post" action="" theme="simple">
<div class="row">
	<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
		<s:set var="disable" value="%{endTypeId!=null && endTypeId.length() > 0}"/>
		<s:set var="endtDisable" value='%{!"admin".equals(#session.user1) && (financialEndt || "RA".equals(quoteStatus))}'/>
		<s:if test='%{"admin".equalsIgnoreCase(#session.user1)}'>
			<s:set var="format" value="%{'number.format.2'}"/>
		</s:if>
		<s:else>
			<s:set var="format" value="%{'number.format.'+#session.BrokerDetails.CurrencyDecimal}"/>
		</s:else>
		<s:actionerror cssStyle="color: red;" />
	</div>	
</div>
<div class="row">
	<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
		<s:if test="%{endt}">
			<div class="panel panel-primary">
				<div class="panel-body" style="padding: 0;">
					<s:set value="policyEndtInfo" var="policyEndtInfo"></s:set>
					<div class="row">
						<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
					 		<div class="textV">
					 			<b><s:text name="endt.policyNo"/></b>
					 		</div>
					 		<div class="tboxV">
					 			<span style="color:blue;"><s:property value="%{#policyEndtInfo.POLICY_NO}"/></span>								 		
					 		</div>
					 	</div>
					 	<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
					 		<div class="textV">
					 			<b><s:text name="endt.brokerName"/></b>
					 		</div>
					 		<div class="tboxV">
					 			<span style="color:blue;"><s:property value="%{#policyEndtInfo.BROKER_NAME}"/></span>						 		
					 		</div>
					 	</div>
					 	<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
					 		<div class="textV">
					 			<b><s:text name="endt.custName"/></b>
					 		</div>
					 		<div class="tboxV">
					 			<span style="color:blue;"><s:property value="%{#policyEndtInfo.CUSTOMER_NAME}"/></span>							 		
					 		</div>
					 	</div>
					</div>
					<div class="row">
						<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
					 		<div class="textV">
					 			<b><s:text name="quotation.endtTypeDesc" /></b>
					 		</div>
					 		<div class="tboxV">
					 			<span style="color:blue;"><s:property value="endtType" /></span>						 		
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
					 			<b><s:text name="endt.openPolicyNo"/></b>
					 		</div>
					 		<div class="tboxV">
					 			<span style="color:blue;"><s:property value="%{#openCoverInfo.OPEN_COVER_NO}"/></span>						 		
					 		</div>
					 	</div>
					 	<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
					 		<div class="textV">
					 			<b><s:text name="endt.brokerName"/></b>
					 		</div>
					 		<div class="tboxV">
					 			<span style="color:blue;"><s:property value="%{#openCoverInfo.BROKER_NAME}"/></span>						 		
					 		</div>
					 	</div>
					 	<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
					 		<div class="textV">
					 			<b><s:text name="endt.custName"/></b>
					 		</div>
					 		<div class="tboxV">
					 			<span style="color:blue;"><s:property value="%{#openCoverInfo.CUSTOMER_NAME}"/></span>								 		
					 		</div>
					 	</div>
				 	</div>
				 	<br class="clear"/>
				 </div>
			</div>
		</s:elseif>
	</div>
</div>
<div class="row">
	<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
		<div class="panel panel-primary">
			<div class="panel-heading" id="plus" onclick="quotationDetail();" style="display: none; cursor: pointer;">
				<div class="row">
					<div class="col-xs-12 col-sm-12 col-md-6 col-lg-3">
					+ <s:text name="premiumInfo.quotation"/>
					</div>
					<div class="col-xs-12 col-sm-12 col-md-6 col-lg-3">
				 		<s:text name="premiumInfo.quoteNo"/>&nbsp;:&nbsp;<s:property value="QUOTE_NUMBER" />
				 	</div>
				 	<div class="col-xs-12 col-sm-12 col-md-6 col-lg-3">
				 		<s:text name="premiumInfo.customerName"/>&nbsp;:&nbsp;<s:property value="CUSTOMER_NAME"/>
				 	</div>
				 	<div class="col-xs-12 col-sm-12 col-md-6 col-lg-3">
				 		<s:text name="premiumInfo.coverStartDate"/>&nbsp;:&nbsp;<s:property value="POLICY_START_DATE" />
				 	</div>
				</div>
			</div>
			<div class="panel-heading" id="miuns" onclick="normalForm();" style="display: block; cursor: pointer;">
				<div class="row">
					<div class="col-xs-12 col-sm-12 col-md-6 col-lg-3">
					- <s:text name="premiumInfo.quotation"/>
					</div>
					<div class="col-xs-12 col-sm-12 col-md-6 col-lg-3">
				 		<s:text name="premiumInfo.quoteNo"/>&nbsp;:&nbsp;<s:property value="QUOTE_NUMBER" />
				 	</div>
				 	<div class="col-xs-12 col-sm-12 col-md-6 col-lg-3">
				 		<s:text name="premiumInfo.customerName"/>&nbsp;:&nbsp;<s:property value="CUSTOMER_NAME"/>
				 	</div>
				 	<div class="col-xs-12 col-sm-12 col-md-6 col-lg-3">
				 		<s:text name="premiumInfo.coverStartDate"/>&nbsp;:&nbsp;<s:property value="POLICY_START_DATE" />
				 	</div>
				</div>				
			</div>
			<div class="panel-body" id="quoteInfo" style="padding: 0px;">
				<div class="row">
					<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
				 		<div class="textV">
				 			<s:text name="premiumInfo.formTrans"  />
				 		</div>
				 		<div class="tboxV">
				 			<s:property value="TRANSPORT_DESCRIPTION"/>									 		
				 		</div>
				 	</div>
				 	<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
				 		<div class="textV">
				 			<s:text name="premiumInfo.coverage" />
				 		</div>
				 		<div class="tboxV">
				 			<s:property value="COVER_NAME"/>							 		
				 		</div>
				 	</div>
				 	<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
				 		<div class="textV">
				 			<s:text name="premiumInfo.conveyance" />
				 		</div>
				 		<div class="tboxV">
				 			<s:property value="CONVDESC" />							 		
				 		</div>
				 	</div>
				</div>
				<div class="row">
					<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
				 		<div class="textV">
				 			<s:if test='%{"3".equals(MODE_OF_TRANSPORT)}'>
								<s:text name="quotation.Srcc" />
							</s:if>
							<s:else>
								<s:text name="premiumInfo.warSrcc" />
							</s:else>
				 		</div>
				 		<div class="tboxV">
				 			<s:property value="WAR_SRCC" />						 		
				 		</div>
				 	</div>
				 	<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
				 		<div class="textV">
				 			<s:text name="premiumInfo.originatingCountry" />
				 		</div>
				 		<div class="tboxV">
				 			<s:property value="ORIGIN_COUNTRY" default="nil" />									 		
				 		</div>
				 	</div>
				 	<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
				 		<div class="textV">
				 			<s:text name="premiumInfo.designationCountry" />
				 		</div>
				 		<div class="tboxV">
				 			<s:property value="DEST_COUNTRY" default="nil" />									 		
				 		</div>
				 	</div>
				</div>
				<div class="row">
					<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
				 		<div class="textV">
				 			<s:text name="premiumInfo.currency" />
				 		</div>
				 		<div class="tboxV">
				 			<s:property value="CURRENCY_NAME"/>(<s:property value="EXCHANGE_RATE"/>)									 		
				 		</div>
				 	</div>
				 	<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
				 		<div class="textV">
				 			<s:text name="premiumInfo.saleTermForValuation" />
				 		</div>
				 		<div class="tboxV">
				 			<s:property value="SALES_TERM" default="nil" />									 		
				 		</div>
				 	</div>
				 	<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
				 		<div class="textV">
				 			<s:text name="premiumInfo.totalInsuredValue" />
				 		</div>
				 		<div class="tboxV">
				 			<s:property value="getText(#format,{TOTAL_INSURED})" default="" />									 		
				 		</div>
				 	</div>
				</div>
				<div class="row">
					<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
				 		<div class="textV">
				 			<s:text name="premiumInfo.equivalent"  />(<s:property value="#session.BrokerDetails.CurrencyAbb"/>)
				 		</div>
				 		<div class="tboxV">
				 			<s:property value="getText(#format,{EQUIVALENT})" default="nil" />									 		
				 		</div>
				 	</div>
				</div>
			</div>
		</div>
	</div>
</div>
<div class="row">
	<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
		<div class="panel panel-primary">
			<div class="panel-heading">
				<s:text name="premiumInfo.insuredGoods" />
			</div>
			<div class="panel-body" style="padding: 0px;">
				<div class="row">
					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
						<table class="footable" width="100%" id="insGoodsTable">
							<thead>
							<tr>
								<th><s:text name="S.No" /></th>
								<th><s:text name="premiumInfo.categoryOfGoods" /></th>
								<th><s:text name="premiumInfo.policyExcess" /></th>
								<th><s:text name="premiumInfo.excessdesc" /></th>
								<th><s:text name="premiumInfo.description" /></th>
								<th><s:text name="premiumInfo.insuredValue" /></th>
								<s:if test='Status=="N" || #session.user1 == "admin" || "RSAIssuer".equalsIgnoreCase(#session.usertype)'>
									<s:if test='"admin".equalsIgnoreCase(#session.usertype) || "RSAIssuer".equalsIgnoreCase(#session.usertype)'>
										<th><s:text name="premiumInfo.ratePercentage" /></th>
										<th>
											<s:if test='%{"3".equals(MODE_OF_TRANSPORT)}'>
					                       		<s:text name="premiumInfo.srccRetePercentage" />
					                       	</s:if>
					                       	<s:else>
					                       		<s:text name="premiumInfo.warRetePercentage" />
					                       	</s:else>
										</th>
									</s:if>
									<s:else>
										<th><s:text name="premiumInfo.ratePercentage" /></th>
									</s:else>
								</s:if>
								<th><s:text name="premiumInfo.fragile" /></th>
							</tr>
							</thead>
							<tbody>
							<s:iterator value="insuredGoodsInfo" var="commodityList" status="stat">
							<tr>
								<td><s:property value="#stat.index+1" default=" "/></td>
								<td><s:property value="COMMODITY_NAME"  default=" "/></td>
								<s:if test='#session.user1 == "admin" || "RSAIssuer".equals(#session.usertype)'>
								<td>
									<s:if test="hasActionErrors()">
		                       	    	<s:textfield value="%{policyExcessPercent[#stat.index]}" name="policyExcessPercent" cssClass="inputBox"  cssStyle="width:40px" onkeyup="checkDecimal2(this)"   disabled="#endtDisable" tabindex="1" />%
		                    			<s:textfield value="%{policyExcess[#stat.index]}" name="policyExcess" cssClass="inputBox" cssStyle="width:60px" id="policyExcess%{#commodityList.COMMODITY_ID}" onkeyup="checkNumbers(this)" maxlength="6" disabled="#endtDisable" tabindex="2" />				                      				
		                    			<s:hidden name="originalPolicyExcess" value="%{#commodityList.POLICY_EXCESS}"/>
		                    		</s:if>
			                       <s:else>
			                            <s:textfield value="%{POLICY_EXCESS_PERCENT}" name="policyExcessPercent" cssClass="inputBox" cssStyle="width:40px" onkeyup="checkDecimal2(this)"  disabled="#endtDisable" tabindex="1" />%
			                       		<s:textfield value="%{POLICY_EXCESS}" name="policyExcess" id="policyExcess%{COMMODITY_ID}" cssClass="inputBox" cssStyle="width:60px" onkeyup="checkNumbers(this)" maxlength="6" disabled="#endtDisable" tabindex="2" />						                       		
			                       		<s:hidden name="originalPolicyExcess[%{#stat.count-1}]" />    
			                       </s:else>
								</td>
								</s:if>
								<s:else >
								<td> <s:property value="POLICY_EXCESS_PERCENT"/>%<br/><s:property value="POLICY_EXCESS"/> </td>
								</s:else>
								<s:if test='#session.user1 == "admin" || "RSAIssuer".equals(#session.usertype)'>
								<td>
									<s:if test="hasActionErrors()">
										<s:textarea value="%{excessDesc[#stat.index]}" name="excessDesc" cssClass="inputBoxA" cssStyle="width: 224px; height: 55px" id="excessDesc%{#commodityList.COMMODITY_ID}" maxlength="1000" disabled="#endtDisable" tabindex="2" rows="2" />				                      				
									</s:if>
									<s:else>
										<s:textarea value="%{EXCESS_DESCRIPTION}" name="excessDesc" id="excessDesc%{COMMODITY_ID}" cssClass="inputBoxA" cssStyle="width: 224px; height: 55px" maxlength="1000" disabled="#endtDisable" tabindex="2" rows="2" />
										&nbsp;						                       		    
									</s:else>
								</td>
								</s:if>
								<s:else>
								<td>
									<s:property value="EXCESS_DESCRIPTION"/> <s:hidden name="excessDesc" value="%{EXCESS_DESCRIPTION}"></s:hidden>
								</td>
								</s:else>
								<td><s:property value="DESCRIPTION" default=" " /></td>
								<td align="right"><s:property value="getText(#format,{SUM_INSURED})" default=" " /></td>
								<s:if test='("RSAIssuer".equals(#session.usertype)) || ("admin".equalsIgnoreCase(#session.usertype))'>
									<s:if test="hasActionErrors()">
										<td>
											<s:textfield cssClass="inputBox" name="commodityRate" value="%{commodityRate[#stat.index]}" disabled="#endtDisable" cssStyle="width:60px" onkeyup="checkNumbers(this)"/><s:hidden name="commodityId" value="%{#commodityList.COMMODITY_ID}"/>
										</td>
										<td>
											<s:textfield cssClass="inputBox" name="commodityWarRate" value="%{commodityWarRate[#stat.index]}" cssStyle="width:60px" onkeyup="checkNumbers(this)" disabled="#endtDisable"/>
										</td>
									</s:if>
									<s:else>
										<td>
											<s:textfield cssClass="inputBox" name="commodityRate" value="%{getText('{0,number,#,##0.0000}',{RATE})}" cssStyle="width:60px" onkeyup="checkNumbers(this)" disabled="#endtDisable"/><s:hidden name="commodityId" value="%{#commodityList.COMMODITY_ID}"/>
										</td>
										<td>
											<s:textfield cssClass="inputBox" name="commodityWarRate" value="%{getText('{0,number,#,##0.0000}',{WARRATE})}" cssStyle="width:60px" onkeyup="checkNumbers(this)" disabled="#endtDisable"/>
										</td>
									</s:else>
								</s:if>
								<s:elseif test='Status=="N"'>
									<s:if test='"admin".equalsIgnoreCase(#session.usertype) || "RSAIssuer".equalsIgnoreCase(#session.usertype)'>
										<td><s:textfield cssClass="inputBox" name="commodityRate" value="%{getText('{0,number,#,##0.0000}',{RATE})}" cssStyle="width:60px" onkeyup="checkNumbers(this)" disabled="#endtDisable"/><s:hidden name="commodityId" value="%{#commodityList.COMMODITY_ID}"/></td>
										<td><s:textfield cssClass="inputBox" name="commodityWarRate" value="%{getText('{0,number,#,##0.0000}',{WARRATE})}" cssStyle="width:60px" onkeyup="checkNumbers(this)" disabled="#endtDisable"/></td>
									</s:if>
									<s:else>
										<td><s:property value="getText('{0,number,#,##0.000000}',{MARINE_WAR_RATE})" default=" " /></td>
									</s:else>
								</s:elseif>
								<td><s:property value="FRAGILE"/></td>
							</tr>
							</s:iterator>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<div class="row">
	<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
		<s:if test='Status=="N" || #session.user1 == "admin"'>		
			<div class="panel panel-primary">
				<div class="panel-heading">
					<s:text name="premiumInfo.premiumInfo" />
				</div>
				<div class="panel-body" style="padding: 0px;">
					<div class="row">
						<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
							<table width="100%" class="footable">
								<tbody>
									<tr>
										<td width="25%">&nbsp;</td>
										<td width="25%" align="left">
											<b><s:text name="premiumInfo.marinePremium" /></b>
										</td>
										<td width="25%" align="right">
											<s:property value="getText(#format,{MARINE_PREMIUM})" default=" "/> 
										</td>
										<td width="25%" align="right">&nbsp;</td>
									</tr>
									<tr>
										<td align="right"><b><s:text name="premiumInfo.add" /></b></td>
										<td>
											<b>
				                   	    		<s:if test='%{"3".equals(MODE_OF_TRANSPORT)}'>
				                   	    			<s:text name="premiumInfo.srccPremium" />
				                   	    		</s:if>
				                   	    		<s:else>
				                   	    			<s:text name="premiumInfo.warPremium" />
				                   	    		</s:else>
				                   	    	</b>
										</td>
										<td align="right"> <s:property value="getText(#format,{WAR_PREMIUM})" /> </td>
										<td align="right"> <s:property value="getText(#format,{TOTAL_PREMIUM})"/> </td>
									</tr>
									<tr>
										<td></td>
										<td></td>
										<td>
											<b><s:text name="premiumInfo.additionalPremium" /></b>
											<s:if test='"RA".equals(quoteStatus) && !"admin".equalsIgnoreCase(#session.user1)'>
												<s:select list="#{'+':'+','-':'-'}" name="premiumOption" value="%{#quotation.EXCESS_SIGN}" headerKey="" disabled="true" cssClass="inputBoxS" cssStyle="width: 50px;" />
											</s:if>
											<s:else>
												<s:select list="#{'+':'+','-':'-'}" name="premiumOption" value="%{#quotation.EXCESS_SIGN}" headerKey="" disabled="#disable" cssStyle="width: 50px;" />
											</s:else>  
										</td>
										<td>
											<s:if test="hasActionErrors()">
						                        <s:textfield name="additionalPremium" onkeyup="validamt(this)" readonly="%{quoteStatus=='RA' && #session.user1 != 'admin'?'true':'false'}" disabled="#disable"  cssStyle="text-align:right;" cssClass="inputBox"/>                        
						                     </s:if>
						                     <s:else>
					                       		<s:textfield name="additionalPremium"  value="%{#quotation.ADDITIONAL_PREMIUM}" onkeyup="validamt(this)" readonly='%{ (#session.user1 != "admin" && "11".equals(#session.product_id)) || (quoteStatus=="RA" && #session.user1 != "admin")?"true":"false"}' disabled="#disable"  cssStyle="text-align:right;" cssClass="inputBox" />                          
						                     </s:else>
										</td>
									</tr>
									<s:if test="GOVT_TAX">
									<tr>
										<td></td>
										<td></td>
										<td><b><s:text name="premiumInfo.governmentTax" /></b></td>
										<td>
											<s:property value="getText(#format,{@java.lang.Double@parseDouble(GOVT_TAX)})"/>
											<s:hidden name="govtTax" value="%{getText(#format,{@java.lang.Double@parseDouble(GOVT_TAX)})}"/>
										</td>
									</tr>
									</s:if>
									<tr>
										<td></td>
										<td><b><s:text name="premiumInfo.add" /></b></td>
										<td><b><s:text name="premiumInfo.inspectionFee" />&nbsp;<s:property value="#session.BrokerDetails.CurrencyAbb"/></b></td>
										<td>
											<s:textfield name="inspectionFee" id="inspectionFee" value="%{#quotation.INSPECTION_FEE}" onkeyup="validamt(this)"  cssStyle="text-align:right;" readonly='#endtDisable' cssClass="inputBox" />
										</td>
									</tr>
									<s:if test='#session.user1 == "admin"'>
									<tr>
										<td align="right"><b><s:text name="premiumInfo.add" /></b></td>
										<td><b><s:text name="premiumInfo.policyIssuanceFee" />&nbsp;<s:property value="#session.BrokerDetails.CurrencyAbb"/></b></td>
										<td><b><s:text name="premiumInfo.editPolicyIssuanceFee" /><s:radio list="#{'Y':'Yes','N':'No'}" name="editPolicyFee" value="%{editPolicyFee==null?'N':editPolicyFee}" onclick="enable(this.value,'policyFee')"/></b></td>
										<td>
											<s:textfield name="policyFee" id="policyFee" cssClass="inputBox" value="%{#quotation.POLICY_ISSUNCE_FEE}" readonly='%{editPolicyFee=="Y"?"false":"true"}'  cssStyle="text-align:right;"/>
										</td>
									</tr>
									</s:if>
									<s:else>
									<tr>
										<td align="right"><b><s:text name="premiumInfo.add" /></b></td>
										<td><s:text name="premiumInfo.policyIssuanceFee" />&nbsp;<s:property value="#session.BrokerDetails.CurrencyAbb"/></b></td>
										<td></td>
										<td>
											<s:textfield name="policyFee" id="policyFee" cssClass="inputBox"  value="%{#quotation.POLICY_ISSUNCE_FEE}" readonly='%{editPolicyFee=="Y"?"false":"true"}'  cssStyle="text-align:right;"/>
										</td>
									</tr>
									</s:else>
									<tr>
										<td></td>
										<td></td>
										<td><b><s:text name="premiumInfo.totalPremium" />	</b></td>
										<td align="right">
											<s:if test="%{endt && endTypeId!='15'}">
					                     		<s:include value="/pages/premiumInfoEndt.jsp"></s:include>
					                     	</s:if>
				                       		<s:textfield name="netPremium"  value="%{#quotation.NET_PREMIUM}" readonly="true" cssClass="inputBox"  cssStyle="text-align:right;"/>
										</td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
		
			<s:if test='"admin".equalsIgnoreCase(#session.usertype) || "RSAIssuer".equalsIgnoreCase(#session.usertype) || "N".equalsIgnoreCase(viewClausesOption)'>
				<div class="panel panel-primary">
					<div class="panel-heading">
						<s:text name="premiumInfo.clausesInfo" />
					</div>
					<div class="panel-body">
						<div class="row">
							<s:if test='(#session.user1 == "admin" || "RSAIssuer".equals(#session.usertype))'>
								<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" align="center">
									<a type="button" class="btn btn-sm btn-info" href="#" onclick="viewPopUp('${pageContext.request.contextPath}/clausesPremium.action?applicationNo=<s:property value="%{applicationNo}"/>&editStatus=yes');"><s:text name="premiumInfo.editClauses"/></a> &nbsp;&nbsp;&nbsp;
									<a type="button" class="btn btn-sm btn-info" href="#" onclick="viewPopUp('${pageContext.request.contextPath}/addClausesPremium.action?applicationNo=<s:property value="%{applicationNo}"/>&coverId=<s:property value="COVER_ID"/>&conditionType=1');"><s:text name="premiumInfo.addClauses"/></a> &nbsp;&nbsp;&nbsp;
									<a type="button" class="btn btn-sm btn-info" href="#" onclick="viewPopUp('${pageContext.request.contextPath}/addClausesPremium.action?applicationNo=<s:property value="%{applicationNo}"/>&coverId=<s:property value="COVER_ID"/>&conditionType=2');"><s:text name="premiumInfo.addWarClauses"/></a> &nbsp;&nbsp;&nbsp;
									<a type="button" class="btn btn-sm btn-info" href="#" onclick="viewPopUp('${pageContext.request.contextPath}/addClausesPremium.action?applicationNo=<s:property value="%{applicationNo}"/>&coverId=<s:property value="COVER_ID"/>&conditionType=3');"><s:text name="premiumInfo.addExclusions"/></a> &nbsp;&nbsp;&nbsp;
									<a type="button" class="btn btn-sm btn-info" href="#" onclick="viewPopUp('${pageContext.request.contextPath}/addClausesPremium.action?applicationNo=<s:property value="%{applicationNo}"/>&coverId=<s:property value="COVER_ID"/>&conditionType=4');"><s:text name="premiumInfo.addWarranties"/></a>
								</div>
								<br/><br/>
							</s:if>
							<s:else>
								<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" align="center" style="padding-top: 5px;" style="height: 30px;">
									<s:text name="premiumInfo.viewClausesDesc"/> &nbsp;&nbsp;&nbsp;
									<a type="button" class="btn btn-sm btn-info" href="#" onclick="viewPopUp('${pageContext.request.contextPath}/clausesPremium.action?applicationNo=<s:property value="%{applicationNo}"/>');"><s:text name="premiumInfo.viewClauses"/></a>
								</div>
								<br/><br/>
							</s:else>
							<s:if test='"RSAIssuer".equals(#session.usertype)'>
								<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" id="viewClauses">
									<div class="text">
										<s:text name="premiumInfo.viewClausesDesc"/>
									</div>
									<div class="tbox">
										<a type="button" class="btn btn-sm btn-info" href="#" onclick="viewPopUp('${pageContext.request.contextPath}/clausesPremium.action?applicationNo=<s:property value="%{applicationNo}"/>');"><s:text name="premiumInfo.viewClauses"/></a>
									</div>
								</div>
								<br/><br/>
								<s:if test="!endt">
									<s:if test='%{productId!=openCover || "admin".equals(#session.user1)}'>
										<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" id="viewClauses">
											<div class="text">
												<s:text name="Do you want to edit the clauses?"/>
											</div>
											<div class="tbox">
												<s:radio name="editClausesYN" list="#{'Y':'Yes','N':'No'}" onclick="getEditClauses(this.value,'normal');" tabindex="30" />
											</div>
										</div>
									</s:if>
								</s:if>
							</s:if>
						</div>
					</div>
				</div>
			</s:if>
			
			<s:if test='Status=="N" && #session.user1 != "admin"'>
   				<s:if test="!endt || (endt && financial)">
   					<div class="panel panel-primary">
						<div class="panel-heading">
							<s:text name="premiumInfo.referralInfo" />
						</div>
						<div class="panel-body">
							<div class="row">
								<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
									<div class="text">
										Would you like to get in touch with under writer for getting better quote?
									</div>
									<div class="tbox">
										<s:radio list="#{'Y':'Yes','N':'No'}" name="referralUpdate"  id="referralUpdate"  onclick="disablePolicyOption(this.value);enable(this.value, 'referralComment')" value="%{referralUpdate==null?(ADMIN_REFERRAL_STATUS==null?'N':ADMIN_REFERRAL_STATUS):referralUpdate}"/>
									</div>
								</div>
								<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
									<div class="text">
										<s:text name="premiumInfo.comments" />
									</div>
									<div class="tbox">
										<s:textarea name="referralComment" cssClass="inputBoxA" cssStyle="width: 100%;" id="referralComment" cols="25" rows="2" readonly='%{referralUpdate==null || referralUpdate=="N"?(ADMIN_REFERRAL_STATUS!="Y"):"false"}' value='%{referralComment==null?(ADMIN_REFERRAL_STATUS=="Y"?REFERRAL_DESC:""):referralComment}'/>
									</div>
								</div>
								<s:if test='"RSAIssuer".equals(#session.usertype) && !"RA".equals(quoteStatus)'>
									<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
										<div class="text">
											Are You finalizing this Quote?
										</div>
										<div class="tbox">
											<s:radio name="finalizeYN" tabindex="16" list="#{'Y':'Yes','N':'No'}"  />
										</div>
									</div>
								</s:if>
								<s:elseif test='"RSAIssuer".equals(#session.usertype) && "RA".equals(quoteStatus)'>
									<s:hidden name="finalizeYN" value="Y"/>
								</s:elseif>
								<s:else>
									<s:hidden name="finalizeYN"/>
								</s:else>
							</div>
						</div>
					</div>
   				</s:if>
   				<div id="policyGeneration" >
   					<div class="panel panel-primary">
						<div class="panel-heading">
							<s:text name="premiumInfo.policyGeneration" />
						</div>
						<div class="panel-body">
							<div class="row">
								<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
									<div class="text">
							 			<s:text name="premiumInfo.generatePolicy" />
							 		</div>
							 		<div class="tbox">
							 			<s:radio list="#{'Y':'Schedule','N':'Draft'}"  name="generatePolicy" id="generatePolicy" value="%{generatePolicy==null?'N':generatePolicy}" onclick="disablePolicyDetail(this);"/>
							 		</div>	
								</div>
							</div>
						</div>
					</div>
   				</div>
   				<div id="getPolicyDetail" >
   					<div class="panel panel-primary">
						<div class="panel-heading">
							<s:text name="premiumInfo.certificateDisplayInfo" />
						</div>
						<div class="panel-body">
							<div class="row">
								<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
									<s:iterator var="docInfo" value="policyInformation">
										<s:if test="productId != openCover">
											<s:if test='"RSAIssuer".equalsIgnoreCase(#session.usertype)'>
												<s:checkbox name="premiumYes"  id="premiumYes"  fieldValue="YES" value='%{premiumYN==null?PDF_PRE_SHOW_STATUS=="YES":premiumYN=="YES"}' disabled='%{premiumYN==null?PDF_PRE_SHOW_STATUS=="PAID":premiumYN=="PAID"}' onclick="setPremiumYN();"/><s:label key="premiumInfo.showPremium"  name="certificateInfo"/>&nbsp;&nbsp;&nbsp;										
												<s:checkbox name="premiumPaid"  id="premiumPaid"  fieldValue="PAID" value='%{premiumYN==null?PDF_PRE_SHOW_STATUS=="PAID":premiumYN=="PAID"}' disabled='%{premiumYN==null?PDF_PRE_SHOW_STATUS=="YES":premiumYN=="YES"}' onclick="setPremiumYN();"/><s:label key="premiumInfo.paidPremium"/>&nbsp;&nbsp;&nbsp;
											</s:if>
											<s:else>
												<s:checkbox name="premiumYN"  id="check_premium"  fieldValue="YES" value='%{premiumYN==null?PDF_PRE_SHOW_STATUS=="YES":premiumYN=="YES"}' /><s:label key="premiumInfo.showPremium"  name="certificateInfo"/>&nbsp;&nbsp;&nbsp;
											</s:else>
											<s:checkbox name="banker" id="check_banker"  fieldValue="YES"  value='%{banker==null?PDF_BANKER_STATUS=="YES":banker=="YES"}' onclick="if (this.checked) document.getElementById('check_both').disabled=true; else document.getElementById('check_both').disabled = false;" disabled='%{BANK_NAME==null || both=="YES"}'/><s:label key="premiumInfo.shouldBankerOnly"  name="certificateInfo"/> &nbsp;&nbsp;&nbsp;
											<s:checkbox name="both" id="check_both"  fieldValue="YES"  value='%{both==null?PDF_BANKER_ASSURED_STATUS=="YES":both=="YES"}' onclick="if (this.checked) document.getElementById('check_banker').disabled=true; else document.getElementById('check_banker').disabled = false;" disabled='%{BANK_NAME==null || banker=="YES"}'/><s:label key="premiumInfo.shouldBankerAndAssured"  name="certificateInfo"/> &nbsp;&nbsp;&nbsp;
											<s:checkbox name="foreign" id="check_foreign" fieldValue="YES" value='%{foreign==null?PDF_CURRENCY_STATUS=="YES":foreign=="YES"}' /><s:label key="premiumInfo.showForeign"  name="certificateInfo"/> &nbsp;&nbsp;&nbsp;
											<s:checkbox name="rubberStamp" id="check_rubber_stamp" fieldValue="Y" value='%{rubberStamp==null?PDF_STAMP_STATUS=="Y":rubberStamp=="Y"}' /><s:label key="premiumInfo.printRubberStamp"  name="certificateInfo"/> &nbsp;&nbsp;&nbsp;
											<s:hidden name="certClausesYN" value="Y"/>
											<s:if test='"RSAIssuer".equalsIgnoreCase(#session.usertype)'>
												<s:checkbox name="excess"  id="check_excess"  fieldValue="YES" value='%{excess==null?PDF_EXCESS_STATUS=="YES":excess=="YES"}' /><s:label key="premiumInfo.showExcess"  name="certificateInfo"/> &nbsp;&nbsp;&nbsp;
											</s:if>
											<s:else>
												<s:hidden name="excess"  id="check_excess" value=""/>
											</s:else> 
										</s:if>
										<s:else>
											<s:if test='"RSAIssuer".equalsIgnoreCase(#session.usertype)'>
												<s:checkbox name="premiumYes"  id="premiumYes"  fieldValue="YES" value='%{premiumYN==null?PDF_PRE_SHOW_STATUS=="YES":premiumYN=="YES"}' disabled='%{premiumYN==null?PDF_PRE_SHOW_STATUS=="PAID":premiumYN=="PAID"}' onclick="setPremiumYN();"/><s:label key="premiumInfo.showPremium"  name="certificateInfo"/> &nbsp;&nbsp;&nbsp;
												<s:checkbox name="premiumPaid"  id="premiumPaid"  fieldValue="PAID" value='%{premiumYN==null?PDF_PRE_SHOW_STATUS=="PAID":premiumYN=="PAID"}' disabled='%{premiumYN==null?PDF_PRE_SHOW_STATUS=="YES":premiumYN=="YES"}' onclick="setPremiumYN();"/><s:text name="premiumInfo.paidPremium"/> &nbsp;&nbsp;&nbsp;
												<s:hidden name="premiumYN" id="premiumYN"/>
											</s:if>
											<s:else>
												<s:checkbox name="premiumYN"  id="check_premium"  fieldValue="YES" value='%{premiumYN==null?PDF_PRE_SHOW_STATUS=="YES":premiumYN=="YES"}' /><s:label key="premiumInfo.showPremium"  name="certificateInfo"/> &nbsp;&nbsp;&nbsp;
											</s:else>
											<s:checkbox name="banker" id="check_banker"  fieldValue="YES"  value='%{banker==null?PDF_BANKER_STATUS=="YES":banker=="YES"}' onclick="if (this.checked) document.getElementById('check_both').disabled=true; else document.getElementById('check_both').disabled = false;" disabled='%{BANK_NAME==null || both=="YES"}'/><s:label key="premiumInfo.shouldBankerOnly"  name="certificateInfo"/> &nbsp;&nbsp;&nbsp;
											<s:checkbox name="both" id="check_both"  fieldValue="YES"  value='%{both==null?PDF_BANKER_ASSURED_STATUS=="YES":both=="YES"}' onclick="if (this.checked) document.getElementById('check_banker').disabled=true; else document.getElementById('check_banker').disabled = false;" disabled='%{BANK_NAME==null || banker=="YES"}'/><s:label key="premiumInfo.shouldBankerAndAssured"  name="certificateInfo"/> &nbsp;&nbsp;&nbsp;
											<s:checkbox name="foreign" id="check_foreign" fieldValue="YES" value='%{foreign==null?PDF_CURRENCY_STATUS=="YES":foreign=="YES"}' /><s:label key="premiumInfo.showForeign"  name="certificateInfo"/> &nbsp;&nbsp;&nbsp;
											<s:checkbox name="rubberStamp" id="check_rubber_stamp" fieldValue="Y" value='%{rubberStamp==null?PDF_STAMP_STATUS=="Y":rubberStamp=="Y"}' /><s:label key="premiumInfo.printRubberStamp"  name="certificateInfo"/> &nbsp;&nbsp;&nbsp;
											<s:if test='"RSAIssuer".equalsIgnoreCase(#session.usertype)'>
												<s:checkbox name="excess"  id="check_excess"  fieldValue="YES" value='%{excess==null?PDF_EXCESS_STATUS=="YES":excess=="YES"}' /><s:label key="premiumInfo.showExcess"  name="certificateInfo"/>
											</s:if>
											<s:else>
												<s:hidden name="excess" id="check_excess"  value=""/>
											</s:else>
										</s:else>
									</s:iterator>
								</div>
							</div>
						</div>
					</div>
					<s:if test="%{endt}">
						<div class="panel panel-primary">
							<div class="panel-heading">
								<s:text name="premiumInfo.certificateDisplayInfoEndt" />
							</div>
							<div class="panel-body">
								<div class="row">
									<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
										<s:iterator var="docInfo" value="policyInformation">
											<s:checkbox name="showpremiumYN"  id="check_showpremium"  fieldValue="Y" value='%{showpremiumYN==null?ENDT_PREM_YN=="Y":showpremiumYN=="Y"}' /><s:label key="premiumInfo.showPremiumEndt"  name="certificateInfo"/> &nbsp;&nbsp;&nbsp;
											<s:checkbox name="printClausesYN" id="printClausesYN" fieldValue="Y" value='%{printClausesYN==null?ENDT_CLAUSES_YN=="Y":printClausesYN=="Y"}' /><s:label key="premiumInfo.showClausesEndt"  name="certificateInfo"/>
										</s:iterator>
									</div>
								</div>
							</div>
						</div>
					</s:if>
					<s:if test='%{"RSAIssuer".equalsIgnoreCase(#session.usertype)}'>
						<div class="panel panel-primary">
							<div class="panel-heading">
								<s:text name="premiumInfo.documentBasis" />
							</div>
						</div>
					</s:if>	
							<div class="panel-body">
								<div class="row">
									<s:if test="%{endt}">
										<s:hidden name="noteType" value="G"/>
										<s:if test='%{#quotation.ISSUER!=null && !"".equals(#quotation.ISSUER) && "cash".equalsIgnoreCase(#quotation.CHANNEL_TYPE)}'>
											<s:hidden name="paymentMode" value="CA"/>
										</s:if>
										<s:else>
											<s:hidden name="paymentMode" value="CR"/>
										</s:else>
										<s:if test='%{"RSAIssuer".equalsIgnoreCase(#session.usertype)}'>
											<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
										 		<div class="text">
										 			<s:text name="premiumInfo.basisOfValidation" />
										 		</div>
										 		<div class="tbox">
										 			<s:select cssClass="inputBoxS" list="basisValList" name="basisValDesc" listKey="REMARKS" listValue="REMARKS"></s:select>
										 		</div>	
										 	</div>
										</s:if>
										<s:else>
											<s:hidden name="basisValDesc" value="ACTUAL AMOUNT"/>
										</s:else>
									</s:if>
									<s:else>
										<s:hidden name="noteType" value="G"/>
										<s:if test='%{#quotation.ISSUER!=null && !"".equals(#quotation.ISSUER) && "cash".equalsIgnoreCase(#quotation.CHANNEL_TYPE)}'>
											<s:hidden name="paymentMode" value="CA"/>
										</s:if>
										<s:else>
											<s:hidden name="paymentMode" value="CR"/>
										</s:else>
										<s:if test='%{"RSAIssuer".equalsIgnoreCase(#session.usertype)}'>
											<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
										 		<div class="text">
										 			<s:text name="premiumInfo.basisOfValidation" />
										 		</div>
										 		<div class="tbox">
										 			<s:select list="basisValList" name="basisValDesc" listKey="REMARKS" listValue="REMARKS" cssClass="inputBox"></s:select>
										 		</div>	
										 	</div>
										</s:if>
										<s:else>
											<s:hidden name="basisValDesc" value="ACTUAL AMOUNT"/>
										</s:else>
									</s:else>
								</div>
							</div>
						</div>
					</s:if>   				 
   			</s:if>
			<s:elseif test='Status=="Y"'>			 
					<div class="panel panel-primary">
						<div class="panel-heading">
							<div >
									<div style="font-size: 15px;">
										<b>List of Refferal in Your Quotation</b><br/><s:property value="referralMsg"/>
									</div>
							</div>
						</div>					 	
						<div class="row">
							<br/>
							<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">								 
								<div style="color:red;font-size: 12px; padding-left:12px ;" >
									<ol class="">
										<s:iterator value='REFERRAL_DESC.split("~")' status="stat"> 
											<li><b><s:property/></b></li>
										</s:iterator>
									</ol>
								</div>
							</div>
							<br/>
						</div>						
					</div>				 
				<div class="panel panel-primary">
					<div class="panel-body">
						<div class="row">
							<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
								<div class="panel-heading">
									<div >
											<div style="font-size: 15px;">
												<center><b><s:property value="premiumInfoReferralMsg"/></b></center> 
											</div>
									</div>
								</div>								
								<s:if test="adminuwList.size()>0">
									<table class="footable" width="100%">
										<thead>
										<tr>
											<th><span style="color: #000000;">SNO.</span></th>
											<th><span style="color: #000000;">Name</span></th>
											<th><span style="color: #000000;">Email</span></th>
										</tr>
										</thead>
										<tbody>
										<s:iterator value="adminuwList" status="stat" var="adminIds">  
										<tr>
											<td>
												<s:property value="%{#stat.index+1}"/>
											</td>
											<td>
									    		<s:property value="%{#adminIds.USERNAME}"/>
											</td>
											<td>
									    		<s:property value="%{#adminIds.USER_MAIL}"/>
											</td>
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
			<s:if test='#session.user1 == "admin"'>
				<div class="panel panel-primary">
					<div class="panel-body">
						<div class="row">
							<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
						 		<div class="text">
						 			<s:text name="premiumInfo.commissionYN"/>
						 		</div>
						 		<div class="tbox">
						 			<s:radio name="commissionYN" list="#{'YES':'Yes','NO':'No'}" value="%{commissionYN==null?'NO':commissionYN}" onclick="enable(this.value,'commission')"/>
						 		</div>	
						 	</div>
						 	<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
						 		<div class="text">
						 			<s:text name="premiumInfo.commission"/>
						 		</div>
						 		<div class="tbox">
						 			<s:textfield name="commission" id="commission" readonly="%{commissionYN==null || commissionYN=='NO'}" value="%{commission==null?#quotation.DISCOUNT_PREMIUM:commission}" maxlength="5" onkeyup="checkNumbers(this)" cssClass="inputBox" />
						 		</div>	
						 	</div>
						 	<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
						 		<div class="text">
						 			<s:text name="premiumInfo.referralStatus"/>
						 		</div>
						 		<div class="tbox">
						 			<s:radio name="referralStatus" list="#{'A':'Approve','R':'Reject','N':'None'}" value='%{(referralStatus==null || "".equals(referralStatus))?quoteStatus=="RA"?"A":quoteStatus=="RR"?"R":"N":referralStatus}'/>
						 		</div>	
						 	</div>
						</div>
						<div class="row">
							<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
						 		<div class="text">
						 			<s:text name="premiumInfo.remarks"/>
						 		</div>
						 		<div class="tbox">
						 			<s:textarea name="adminRemarks" value="%{adminRemarks==null?#quotation.ADMIN_REMARKS:adminRemarks}" onkeyup="textLimit(this,'450')" cssClass="inputBoxA" cssStyle="width: 100%;"  />
						 		</div>	
						 	</div>
						</div>
					</div>
				</div>
			</s:if>		 
		</div>
	</div>
	<div class="row">
		<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" align="center">
			<s:if test="%{endTypeId!=null && endTypeId.length() > 0 && quoteStatus=='RA'}">
				<s:submit name="Submit2" type="submit" cssClass="btn btn-sm btn-danger" value="Back"  onclick="update('initReport.action?menuType=E')"/>&nbsp;&nbsp;&nbsp;
			</s:if>
			<s:else>
				<s:submit name="Submit2" type="submit" cssClass="btn btn-sm btn-danger" value="Back"  onclick="update('editQuoteQuotation.action')"/>&nbsp;&nbsp;&nbsp;
			</s:else>
			<s:if test='Status=="N" || #session.user1 == "admin"'>                              
				<s:if test="!(quoteStatus=='RA' && #session.user1 != 'admin')">
					<s:submit name="Submit3" type="button" cssClass="btn btn-sm btn-primary" value="Calculate" onclick="disableForm(this.form,false,'');update('calculatePremium.action')"/> &nbsp;&nbsp;&nbsp;
				</s:if>
			</s:if>
			<s:submit name="SubmitProceed" id="SubmitProceed" type="button" cssClass="btn btn-sm btn-success" value="Proceed"  onclick="disableForm(this.form,false,'');update('updatePremium.action')"/>
		</div>
	</div>
	<s:hidden name="quoteNo" value="%{#quotation.QUOTE_NUMBER}"/>    
	<s:hidden name="totalPremium" value="%{#quotation.TOTAL_PREMIUM}"/>    
	<s:hidden name="applicationNo"/>  
	<s:hidden name="refNo" />  
	<s:hidden name="openCoverNo"/>  
	<s:hidden name="loginId" value="%{#quotation.LOGIN_ID}"/>
	<s:hidden name="issuer" value="%{#quotation.ISSUER}"/>
	<s:hidden name="totalWarPremium" value="%{#quotation.WAR_PREMIUM}"/>
	<s:hidden name="status" value="%{#quotation.STATUS}"/>
	<s:hidden name="quoteStatus" />
	<s:hidden name="settlingAgent" value="%{#quotation.SETTLING_AGENT_NAME}"/>
	<s:hidden name="packDesc" value="%{#quotation.PACKAGE_DESCRIPTION}"/>
	<s:hidden name="addClauses"/>
	<s:hidden name="addWarClauses"/>
	<s:hidden name="addExclusions"/>
	<s:hidden name="addWarranties"/>
	<s:hidden name="endTypeId" />
	<s:hidden name="adminRefStatus" value="%{#quotation.ADMIN_REFERRAL_STATUS}"/>
	<s:hidden name="policyNo"/> 
	<s:hidden name="custName"/>
	<s:hidden name="brokerCompany"/>
	<s:hidden name="updateClauses" value="Y"/>
	<s:hidden name="searchFrom" />
	<s:hidden name="searchBy" />
	<s:hidden name="searchValue" />
	<s:hidden name="customerId" value="%{#quotation.CUSTOMER_ID}"/>
	<s:hidden name="channelType" value="%{#quotation.CHANNEL_TYPE}"/>
	</s:form>
</s:iterator>
</body>
<script type="text/javascript">
function viewPopUp(URL) {
	var bwidth = window.innerWidth;
	var bwidth1 = document.body.clientWidth;
	if(bwidth <= 768) {
		return popUp(URL,bwidth1,'500');
	} else {
		return popUp(URL,'750','500');
	}
}
if(document.premiumInfo.endTypeId.value!='') {
	enableForm(document.premiumInfo,false,'<s:property value="%{fields}"/>');
}  
function quotationDetail() {
	document.getElementById('quoteInfo').style.display='block';
    document.getElementById('miuns').style.display='block';
    document.getElementById('plus').style.display='none';
}
<s:if test='"RSAIssuer".equalsIgnoreCase(#session.usertype)'>
	try {
		setPremiumYN();
	} catch(e){}
</s:if>
function setPremiumYN() {
	if(document.getElementById('premiumYes').checked == true) {
		document.getElementById('premiumPaid').disabled = true;
		document.getElementById('premiumYN').value = document.getElementById('premiumYes').value;
	}
	else if(document.getElementById('premiumPaid').checked == true) {
		document.getElementById('premiumYes').disabled = true;
		document.getElementById('premiumYN').value = document.getElementById('premiumPaid').value;
	}
	else {
		document.getElementById('premiumYes').disabled = false;
		document.getElementById('premiumPaid').disabled = false;
		document.getElementById('premiumYN').value = "";
	}
}

function normalForm() {
	document.getElementById('quoteInfo').style.display='none';
	document.getElementById('miuns').style.display='none';
	document.getElementById('plus').style.display='block';
} 
       
function disablePolicyDetail(obj) {
     var displayStatus=obj.value;   
	if(displayStatus=="N") {
		document.getElementById('getPolicyDetail').style.display='block';
	} else {
		document.getElementById('getPolicyDetail').style.display='block';    
	}
}
if(document.getElementById('policyGeneration') && document.getElementById('getPolicyDetail') && document.premiumInfo.referralUpdate[0].checked){
	disablePolicyOption('Y');
}
function disablePolicyOption(value) {
	if(value=="Y") {
		document.getElementById('policyGeneration').style.display='none';
		document.getElementById('getPolicyDetail').style.display='none';
		document.premiumInfo.generatePolicy[0].checked=false;
		document.premiumInfo.generatePolicy[1].checked=true;
	} else {   
		document.getElementById('policyGeneration').style.display='block';
		document.getElementById('referralComment').value='';
		//if(document.premiumInfo.generatePolicy[0].checked){
			document.getElementById('getPolicyDetail').style.display='block';
		//}
	} 
}
   
function update(actionPath) { 
	document.getElementById('SubmitProceed').disabled=true;
	document.premiumInfo.action = '${pageContext.request.contextPath}/' + actionPath;
	document.premiumInfo.submit();
	return false;
}

function enable(value, id) { 
	if('Y'==value || 'YES'==value){
		document.getElementById(id).readOnly=false;
	} else {
		document.getElementById(id).readOnly=true;
		document.getElementById('commission').value='';
	} if(id=='referralComment' && 'N'==value){
		document.getElementById(id).value='';
	}
 	return false;
}
   /* if(document.premiumInfo.paymentMode){
    <s:if test="%{!endt}">
    	toggleCredit('<s:property value="%{noteType==null?'N':noteType}"/>');
    </s:if>
    }*/
    function toggleCredit(value)
	{
		var accountStatus='<s:property value="accountStatus"/>';
		var commission='<s:property value="commissionStatus"/>';	
		var opencover='<s:text name="OPEN_COVER"/>';
 	    var session_productCode='<s:property value="#session.product_id"/>';
		if(session_productCode == opencover)
			{				
			   var arInsuredStatus=accountStatus;
			   var arNo='<s:property value="arInsuredStatus"/>';
			}
			else{
				  var arNo=accountStatus;
			}		
 	    
		if(value=='N'){		
			if(!arNo || commission)
			{		
				document.premiumInfo.paymentMode[1].disabled=true;
				document.premiumInfo.paymentMode[1].checked=false;
				document.premiumInfo.paymentMode[0].checked=true;
			}			
			if(session_productCode == opencover && (!arInsuredStatus || commission))
			{					
				document.premiumInfo.paymentMode[2].disabled=true;
				document.premiumInfo.paymentMode[2].checked=false;
				document.premiumInfo.paymentMode[0].checked=true;		
			}
		
		}
		if(value=='G'){
			document.premiumInfo.paymentMode[0].disabled=false;
			if(arNo){
				document.premiumInfo.paymentMode[1].disabled=false;
			}else{
				document.premiumInfo.paymentMode[1].disabled=true;
			}
			if(arInsuredStatus){
				document.premiumInfo.paymentMode[2].disabled=false;
			}else{
				document.premiumInfo.paymentMode[2].disabled=true;
			}
		}
	}
	function directMsg(value)
	{
	  var status='<s:property value="directStatus"/>';
	  if(value=='CA'){		
		   if(status=='Y'){
		   alert('You are trying to create debit note under Cash customer');
		   }
	   }
	}
 	function addConditions(type)
	{
		 var exclude='';
		 var obj='';
		 var ids='';
		 if(type=='1'){
		 	obj=document.form1.clausesIds;
		 }else if(type=='2'){
		 	obj=document.form1.exClausesIds;
		 }else if(type=='3'){
		 	obj=document.form1.exIds;
		 }else if(type=='4'){
		 	obj=document.form1.warIds;
		 }
		 if(obj.length)
		 {
		 	for ( var int = 0; int < obj.length; int++) {
				exclude+=obj[int].value+',';
			}
		 }else{
		 	exclude=obj.value;
		 }
		 if(exclude.lastIndexOf(',')!=-1){
		 	ids=exclude.substring(0, exclude.lastIndexOf(',')-1);
	 	 }
		popUp('${pageContext.request.contextPath}/admin/addConditions.jsp?type='+type+'&exclude='+ids+'&coverId='+document.form1.exisCoverId.value,'1000','500');
	}	
	function validamt(val){
		
		try{
			var value=val.value;
			if(value!=null && value){
				val.value = value.replace(/[^0-9.]/g,'');
			}
			if(val.value=='')
				val.value = '0';
		}catch(err){ val.value = '0';}
	}
	function validamt1(val){
		try{
			var value=val.value;
			if(value!=null){
				val.value = value.replace(/[^0-9]/g,'');
			}
			if(val.value=='')
				val.value = '0';
		}catch(err){val.value = '0';}
	}
	<s:if  test='"RSAIssuer".equals(#session.usertype)' >
      getEditClauses('<s:property value="editClausesYN" />','onload');
    </s:if>
    function getEditClauses(clausesYN,status){
       var userType='<s:property value="%{#session.usertype}" />';
       if("RSAIssuer"==userType){
	        if("Y"==clausesYN){
	          document.getElementById("editClauses").style.display="";
	        //  document.getElementById("viewClauses").style.display="none";	          
	          document.premiumInfo.referralUpdate[0].checked=true;
	          document.premiumInfo.referralUpdate[1].checked=false;
	          disablePolicyOption("Y");
	          enable("Y", 'referralComment');
	         }else{
	          document.getElementById("editClauses").style.display="none";
	         // document.getElementById("viewClauses").style.display="";
	          /*if("normal"==status){
		          document.premiumInfo.referralUpdate[0].checked=false;
		          document.premiumInfo.referralUpdate[1].checked=true;
		          disablePolicyOption("N");
		          enable("N", 'referralComment');
	          }*/
	        }
         }
     }
</script>
</html>