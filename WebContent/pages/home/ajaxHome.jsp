<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:if test="#request.ELEMENT_NAME=='premiumDetailsDivId'">
					<div class="card card-1">
		                <h4>Premium Details</h4>
		                <hr>
		                <s:if test='"Y".equals(minPremiumYN) && #premiumDisplay'>
			                <div class="row rowFlex OnlyPremiumTable p-3">
			                	<div class="col-md-12 col-12">
			                		<font color="red"><s:text name="label.home.minimum.premium.threshold">&nbsp;</s:text><s:property value="getText('{0,number,#,##0.00}',{@java.lang.Double@valueOf(actualPremium)})"/>&nbsp;<s:text name="label.home.minimum.premium.threshold.charged"></s:text></font>
			                	</div>
			                </div>
		                </s:if>
		                <s:if test='premiumCalcValidation != null && !"".equals(premiumCalcValidation)'>
			                <div class="row rowFlex OnlyPremiumTable p-3">
			                	<div class="col-md-12 col-12">
			                		<font color="red"><s:property value="premiumCalcValidation"/></font>
			                	</div>
			                </div>
		                </s:if>
		                <div class="row rowFlex OnlyPremiumTable p-3">
			                <div class="col-md-2 col-2 colsame" style="padding: 0px 0px 0px 10px !important;" >
			                </div>
		                	<div class="col-md-8 col-8 colsame" style="padding: 0px 0px 0px 10px !important;">
		                		<table class="table">
		                			<thead>
		                				<tr>
		                					<th style="background-color:#261e6a;color:white;text-align:center;"></th>
		                					<th style="background-color:#261e6a;color:white;text-align:center;">Description</th>
		                					<th style="background-color:#261e6a;color:white;text-align:center;">Premium&nbsp;(ZMW)</th>
		                					<th style="background-color:#261e6a;color:white;text-align:center;"></th>
		                				</tr>
		                			</thead>
		                			<tbody>
		                			 	<s:if test='%{actualPremium!=null && !"".equalsIgnoreCase(actualPremium) && @java.lang.Double@valueOf(actualPremium)!=0}'>
			                				<tr>
				                				<td></td>
				                				<td align="left">
				                					<b>Base Premium</b>
				                				</td>
				                				<td align="right">
				                					<b><s:property value="getText('{0,number,#,##0.00}',{@java.lang.Double@valueOf(actualPremium)})"/></b>
				                				</td>
				                				<td></td>
			                				</tr>
		                				</s:if>
		                				<s:if test='%{actualOptionalPremium!=null && !"".equalsIgnoreCase(actualOptionalPremium) && @java.lang.Double@valueOf(actualOptionalPremium)!=0}'>
			                				<tr>
				                				<td></td>
				                				<td align="left">
				                					<b>Additional Cover Premium</b>
				                				</td>
				                				<td align="right">
				                					<b><s:property value="getText('{0,number,#,##0.00}',{@java.lang.Double@valueOf(actualOptionalPremium)})"/></b>
				                				</td>
				                				<td></td>
			                				</tr>
				                		</s:if>
			                			<s:if test='%{volDiscountAmount!=null && !"".equalsIgnoreCase(volDiscountAmount) && @java.lang.Double@valueOf(volDiscountAmount)!=0}'>
			                				<tr>
				                				<td></td>
				                				<td align="left">
				                					<b>Larger Volume Discount</b>
				                				</td>
				                				<td align="right">
				                					<b>-&nbsp;<s:property value="getText('{0,number,#,##0.00}',{@java.lang.Double@valueOf(volDiscountAmount)})"/></b>
				                				</td>
				                				<td></td>
			                				</tr>
		                				</s:if>
		                				<s:if test='%{corpDiscountAmount!=null && !"".equalsIgnoreCase(corpDiscountAmount) && @java.lang.Double@valueOf(corpDiscountAmount)!=0}'>
			                				<tr>
				                				<td></td>
				                				<td align="left">
				                					<b>Corporate Discount</b>
				                				</td>
				                				<td align="right">
				                					<b>-&nbsp;<s:property value="getText('{0,number,#,##0.00}',{@java.lang.Double@valueOf(corpDiscountAmount)})"/></b>
				                				</td>
				                				<td></td>
			                				</tr>
		                				</s:if>
		                				<s:if test='%{splDiscountAmount!=null && !"".equalsIgnoreCase(splDiscountAmount) && @java.lang.Double@valueOf(splDiscountAmount)!=0}'>
			                				<tr>
				                				<td></td>
				                				<td align="left">
				                					<b>Special Discount</b>
				                				</td>
				                				<td align="right">
				                					<b>-&nbsp;<s:property value="getText('{0,number,#,##0.00}',{@java.lang.Double@valueOf(splDiscountAmount)})"/></b>
				                				</td>
				                				<td></td>
			                				</tr>
		                				</s:if>
		                				<s:if test='%{totalPremium!=null && !"".equalsIgnoreCase(totalPremium) && @java.lang.Double@valueOf(totalPremium)!=0}'>
			                				<tr>
				                				<td></td>
				                				<td align="left">
				                					<b>Total Premium</b>
				                				</td>
				                				<td align="right">
				                					<b><s:property value="getText('{0,number,#,##0.00}',{@java.lang.Double@valueOf(totalPremium)})"/></b>
				                				</td>
				                				<td></td>
			                				</tr>
				                		</s:if>
			                			<s:if test='"RSAIssuer".equalsIgnoreCase(#session.usertype) || "Admin".equalsIgnoreCase(#session.usertype)'>
			                				<tr>
				                				<td></td>
				                				<td align="left">
				                					<b>Loading / Discount Premium</b>
				                				</td>
				                				<td align="right">
			                						<s:select theme="simple" name="excessSign" id="excessSign" cssclass="form-control" list="#{'+':'+','-':'-'}" />&nbsp;
			                						<s:textfield theme="simple" name="excessPremium" id="excessPremium" cssclass="form-control" maxlength="10" style="text-align: right;" onkeypress="return isNumberKey(event);"/>
				                				</td>
				                				<td></td>
			                				</tr>
			                			</s:if>
			                			<s:else>
			                				<s:if test='%{excessPremium!=null && !"".equalsIgnoreCase(excessPremium) && @java.lang.Double@valueOf(excessPremium)!=0}'>
				                				<tr>
					                				<td></td>
					                				<td align="left">
					                					<b>Loading / Discount Premium</b>
					                				</td>
					                				<td align="right">
					                					<b><s:property value="excessSign"/>&nbsp;<s:property value="getText('{0,number,#,##0.00}',{@java.lang.Double@valueOf(excessPremium)})"/></b>
					                				</td>
					                				<td></td>
				                				</tr>
			                				</s:if>
			                			</s:else>
			                			<s:if test='%{policyFee!=null && !"".equalsIgnoreCase(policyFee) && @java.lang.Double@valueOf(policyFee)!=0}'>
			                				<tr>
				                				<td></td>
				                				<td align="left">
				                					<b>Premium Tax&nbsp;(<s:property value="policyFeePercent"/>&nbsp;%)</b>
				                				</td>
				                				<td align="right">
				                					<b><s:property value="getText('{0,number,#,##0.00}',{@java.lang.Double@valueOf(policyFee)})"/></b>
				                				</td>
				                				<td></td>
			                				</tr>
		                				</s:if>
			                			<s:if test='%{finalPremium!=null && !"".equalsIgnoreCase(finalPremium) && @java.lang.Double@valueOf(finalPremium)!=0}'>
			                				<tr>
				                				<td></td>
				                				<td align="left">
				                					<b>Total Premium Payable</b>
				                				</td>
				                				<td align="right">
				                					<b><s:property value="getText('{0,number,#,##0.00}',{@java.lang.Double@valueOf(finalPremium)})"/></b>
				                				</td>
				                				<td></td>
			                				</tr>
			                			</s:if>
		                			</tbody>
		                		</table>
		                	</div>
			                <div class="col-md-2 col-2 colsame" style="padding: 0px 0px 0px 10px !important;" >
			                </div>
		                </div>
		                <s:if test='"RSAIssuer".equalsIgnoreCase(#session.usertype) || "Admin".equalsIgnoreCase(#session.usertype)'>
	       					<div class="row">
		        			   <div class="col-md-8 col-2 col-sm-2">
		        			   </div>
		        			   <div class="col-md-2 col-2 col-sm-2" id="premCalcDivId">
				               		<a class="btn btn-primary btn-sm" onclick="calcPrem();">Calculate</a>
				               </div>
				            </div>
			            </s:if>
	                </div>
</s:if>
<s:elseif test="#request.ELEMENT_NAME=='paymentBankDetails'">
	<s:set var="bdVar" value="bankDetails"/>
	<div class="row">
		<div class="col-md-4 col-4">
			<b><label class="LabelHeading"><s:text name="label.accountName" /></label></b>
		</div>
		<div class="col-md-8 col-8">
			<label class="labelValues"><s:property value="%{#bdVar.ACCOUNT_HOLDER}"/></label>
		</div>
	</div>
	<div class="row">
		<div class="col-md-4 col-4">
			<b><label class="LabelHeading"><s:text name="label.bank" /></label></b>
		</div>
		<div class="col-md-8 col-8">
			<label class="labelValues"><s:property value="%{#bdVar.BANK_NAME}"/></label>
		</div>
	</div>
	<div class="row">
		<div class="col-md-4 col-4">
			<b><label class="LabelHeading"><s:text name="label.accountNumber" /></label></b>
		</div>
		<div class="col-md-8 col-8">
			<label class="labelValues"><s:property value="%{#bdVar.ACCOUNT_NUMBER}"/></label>
		</div>
	</div>
	<div class="row">
		<div class="col-md-4 col-4">
			<b><label class="LabelHeading"><s:text name="label.branch" /></label></b>
		</div>
		<div class="col-md-8 col-8">
			<label class="labelValues"><s:property value="%{#bdVar.BRANCH_NAME}"/></label>
		</div>
	</div>
	 <div class="row">
		<div class="col-md-4 col-4">
			<b><label class="LabelHeading"><s:text name="label.currency" /></label></b>
		</div>
		<div class="col-md-8 col-8">
			<label class="labelValues"><s:property value="%{#bdVar.CURRENCY_TYPE}"/></label>
		</div>
	</div>
	 <div class="row">
		<div class="col-md-4 col-4">
			<b><label class="LabelHeading"><s:text name="Branch Code" /></label></b>
		</div>
		<div class="col-md-8 col-8">
			<label class="labelValues"><s:property value="%{#bdVar.BRANCH_CODE}"/></label>
		</div>
	</div>
	<div class="row">
		<div class="col-md-4 col-4">
			<b><label class="LabelHeading"><s:text name="label.swiftCode" /></label></b>
		</div>
		<div class="col-md-8 col-8">
			<label class="labelValues"><s:property value="%{#bdVar.SWIFT_CODE}"/></label>
		</div>
	</div>
	<br>
	<div class="row" align="center">
		<h5><font color="red">Important Note: Please provide the quotation number: <s:property value="quoteNo" /> to the bank teller when making the deposit.</font></h5>	
	</div>
   <div class="row mt-4">
       <div class="col-md-2 col-2 col-sm-2 offset-md-4">
           <a style="cursor:pointer" data-dismiss="modal" class="btn btn-danger btn-block">Close</a>
       </div>
   </div>
</s:elseif>