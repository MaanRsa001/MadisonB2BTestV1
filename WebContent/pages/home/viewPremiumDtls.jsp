<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ page import="java.util.List,java.util.ArrayList,java.util.Map"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
			<div class="Card_Parent  mt-4">
	            <div class="card card-1">
	            	<div class="row">
	            		<div class="col-md-3">
	                		<h4>Premium Details</h4>
	                	</div>
	            		<div class="col-md-3">
	            			<b>Quote No :</b> <s:property value="quoteNo"/>
	            		</div>
	            		<div class="col-md-4">
	            			<b>Total Payable :</b> <s:property value="getText('{0,number,#,##0.00}',{@java.lang.Double@valueOf(finalPremium)})"/>&nbsp;ZMW
	            		</div>
	            		<div class="col-md-2">
	            			<a class="btn btn-primary btn-sm" style="cursor: pointer" id="premShBtnId" onclick="togglePremDtls();">Show Details</a>
	            			<s:hidden name="shPremDtls" id="shPremDtls" value="show"/>
	            		</div>
	                </div>
	                <hr>
	                <div class="row rowFlex OnlyPremiumTable p-3" id="premDtlsDivId" style="display: none;">
		                <div class="col-md-2">
		                </div>
	                	<div class="col-md-8">
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
		                <div class="col-md-2" >
		                </div>
	                </div>
	            </div>
	        </div>
		    <SCRIPT type="text/javascript">
				
				function togglePremDtls(){
					try{
						var sh = document.getElementById('shPremDtls').value;
						if(sh == 'show'){
							document.getElementById('shPremDtls').value = 'hide';
							document.getElementById('premDtlsDivId').style.display = 'block';
							document.getElementById('premShBtnId').innerHTML = 'Hide Details'
						}else{
							document.getElementById('shPremDtls').value = 'show';
							document.getElementById('premDtlsDivId').style.display = 'none';
							document.getElementById('premShBtnId').innerHTML = 'Show Details'
						}
					}catch(err){
						console.error(err);
					}
				}
				
			</SCRIPT>