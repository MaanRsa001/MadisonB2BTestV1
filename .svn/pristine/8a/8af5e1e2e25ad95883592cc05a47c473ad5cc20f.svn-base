<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ page import="java.util.List,java.util.ArrayList,java.util.Map,java.util.HashMap,java.util.Collections"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html <s:if test="locale.language == 'ar'">dir="rtl"</s:if>>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Form</title>
<link href="css/styles.css" rel="stylesheet" type="text/css" />
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
</head>

<body>
<table width="900" border="0" align="center" cellpadding="0" cellspacing="0"> 
  <tr>
    <td height="5"></td>
  </tr>
  <tr>
    <td><table width="100%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#E5E5E5">
      <tr>
        <td bgcolor="#FFFFFF" style="padding:10px; background:#F8F8F8">
        <s:if test="'newQuote'.equalsIgnoreCase(display)">
        <s:set var="disable1" value="%{((endTypeId!=null && endTypeId.length() > 0)||(quoteStatus=='RA'))}"/>
        <s:form id="motor" name="motor" method="post" theme="simple" action="getQuoteMotor.action">
          <table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tr>
	          <td  style="color:red;"><s:actionerror/></td>
	        </tr>
              <tr>
                <td bgcolor="#FFFFFF">
	                <s:include value="/pages/customer/custDetails.jsp"></s:include>
                </td>
              </tr>
              <tr>
                <td height="2" bgcolor="#FFFFFF"></td>
              </tr>
              <tr>
                <td bgcolor="#FFFFFF"><table width="100%" border="0" cellspacing="0" cellpadding="0">
                    <tr>
                      <td class="heading"><s:text name="motor.vehicleDetails" /></td>
                    </tr>
                    <tr>
                      <td class="bg"><table width="100%" border="0" align="center" cellpadding="4" cellspacing="0">
                      	<tr>
                      		<td width="2%">&nbsp;</td>
	                        <td width="25%"> <s:text name="motor.make" /><font color="red">*</font><br/>
								<s:select name="make" id="make" list="makeList" listKey="CODE" listValue="CODEDESC" headerKey="" headerValue="-Select-" onchange="getAjaxModel(this.form,'?make='+this.value,'modelList')" cssClass="input" disabled="#disable1"/> </td>						
							<td width="25%"> <s:text name="motor.model" /><font color="red">*</font><br/>
								<div id="modelList">
										<s:select name="model" id="model" list="modelList" listKey="CODE" listValue="CODEDESC" headerKey="" headerValue="-Select-" cssClass="input" disabled="#disable1"/>
								</div> 
							</td>
							<td width="25%"> 
								<s:text name="motor.typeOfBody" /><font color="red">*</font><br/>
								<s:select name="typeBody" id="typeBody" list="typeBodyList" listKey="CODE" listValue="CODEDESC" headerKey="" headerValue="-Select-" cssClass="input" onchange="getAjaxModel(this.form,'?typeBody='+this.value,'noOfCylinderList')" disabled="#disable1" cssStyle="width:200px;"/>
							</td>
							<td width="2%">&nbsp;</td>
						</tr>
						<tr>
							<td>&nbsp;</td>
							<td><s:text name="motor.mfgYr" /><font color="red">*</font><br/>
								<s:select name="mfgYr"  id="mfgYr" list="mfgYrMap" headerKey="" headerValue="-Select-" cssClass="input" disabled="#disable1"/></td>
                            <td><s:text name="motor.sumInsured" /><font color="red">*</font><br />
                                <s:textfield name="sumInsured" id="sumInsured" cssClass="input" maxLength="13" onkeyup="checkDecimals(this);" disabled="#disable1"/>&nbsp;&nbsp;<s:property value="#session.BrokerDetails.CurrencyAbb"/>
                            </td>
                            <td>
                           		<s:text name="motor.cubicCapacity" /><font color="red">*</font><br/>
								<s:textfield name="cubicCapacity" id="cubicCapacity" cssClass="input" maxLength="10" onkeyup="checkNumbers(this);" disabled="#disable1"/>
                            </td>
							<td>&nbsp;</td>
						</tr>
                      	<tr>
							<td>&nbsp;</td>
							<td><s:text name="motor.seatingCapacity" /><font color="red">*</font><br/>
									<s:textfield name="seatingCapacity" id="seatingCapacity" size="7" cssClass="input" maxLength="5" onkeyup="checkNumbers(this);" disabled="#disable1"/>&nbsp;&nbsp;<s:text name="motor.includingDriver"/>
								</td>
							<td><s:text name="motor.noOfCylinder" /><font color="red">*</font><br/>
							<div id="noOfCylinderList">
								<s:select name="noOfCylinder" id="noOfCylinder" list="noOfCylinderList" listKey="CODE" listValue="CODEDESC" headerKey="" headerValue="-Select-" cssClass="input" disabled="#disable1"/>
							</div>
							</td>
							<td>
								<s:text name="motor.vehicleUsage" /><font color="red">*</font><br/>
								<s:select name="vehicleUsage"  id="vehicleUsage" list="vehicleUsageList" listKey="CODE" listValue="CODEDESC" headerKey="" headerValue="-Select-" cssClass="input" disabled="#disable1"/>
							</td>
							<td>&nbsp;</td>
                      	</tr>
                      	<tr>
							<td>&nbsp;</td>
							<td>
								<s:text name="motor.areaCoverage" /><font color="red">*</font><br/>
								<s:select name="areaCoverage" list="areaCoverageList" listKey="CODE" listValue="CODEDESC" headerKey="" headerValue="-Select-" cssClass="input" disabled="#disable1" cssStyle="width:200px;"/></td>
							<td>
								<s:text name="motor.agencyRepair" /><font color="red">*</font><br/>
								<s:radio name="agencyRepairYN" id="agencyRepairYN" list="#{'Y':'Yes','N':'No'}" value="%{agencyRepairYN==null?'N':agencyRepairYN}" cssClass="input" disabled="#disable1"/>
							</td>
							<td></td>
							<td>&nbsp;</td>
                      	</tr>
                      </table></td>
                    </tr>
                    <tr>
                      <td class="heading"><s:text name="motor.driverDetails" /></td>
                    </tr>
                    <tr>
                      <td class="bg"><table width="100%" border="0" align="center" cellpadding="4" cellspacing="0">
                      <tr>
							<td width="2%">&nbsp;</td>
                      		<td width="25%"><s:text name="motor.driverDOB" /><font color="red">*</font><br/>
                      			<sj:datepicker name="driverDOB" id="driverDOB" displayFormat="dd/mm/yy" minDate="-100Y" maxDate="-1D" readonly="true" disabled="#disable1" cssClass="input" changeMonth="true" changeYear="true" showAnim="slideDown" duration="fast" yearRange="c-100:c+100"  disabled="#disable1"/>
								</td>
							<td width="25%"><s:text name="motor.driverNationality" /><font color="red">*</font><br/>
								<s:select name="driverNationality" id="driverNationality" list="driverNationalityList" listKey="CODEVALUE" listValue="CODEDESC" headerKey="" headerValue="-Select-" cssClass="input" disabled="#disable1"/>
							</td>
							<td width="25%">
								<s:text name="motor.uaeLicenceNo" /><br/>
								<s:textfield name="uaeLicenceNo" id="uaeLicenceNo" cssClass="input" maxLength="5" onkeyup="checkNumbers(this);" disabled="#disable1"/>
							</td>
							<td width="2%">&nbsp;</td>
                      	</tr>
                      	<tr>
							<td width="2%">&nbsp;</td>
                      		<td width="25%"><s:text name="motor.uaeLicenceExpDT" /><br/>
                      		    <sj:datepicker name="uaeLicExpDT" id="uaeLicExpDT" displayFormat="dd/mm/yy" minDate="0D" maxDate="30Y" readonly="true" disabled="#disable1" cssClass="input" changeMonth="true" changeYear="true" showAnim="slideDown" duration="fast" yearRange="c-100:c+100"  disabled="#disable1"/>
                      		    </td>
							<td width="25%">
							</td>
							<td width="25%">
							</td>
							<td width="2%">&nbsp;</td>
                      	</tr>
                      </table></td>
                    </tr>
                     <tr>
                      <td class="heading"><s:text name="motor.driverClaimDetails" /></td>
                    </tr>
                     <tr>
                      <td class="bg"><table width="100%" border="0" align="center" cellpadding="4" cellspacing="0">
                      <tr>
							<td width="2%">&nbsp;</td>
							<td width="25%"> 
								<s:text name="motor.noClaimBonus" /><font color="red">*</font><br/>
									<s:select name="noClaimBonus" id="noClaimBonus" list="noClaimBonusList" headerKey="" headerValue="-Select-" cssClass="input" disabled="#disable1"/>
								
							</td>
                      		<td width="25%"><s:text name="motor.driverClaimMadeYN" /><font color="red">*</font><br/>
                      			<s:radio name="claimYN" id="claimYN" list="#{'Y':'Yes','N':'No'}" value="%{claimYN==null?'N':claimYN}" cssClass="input" onclick="toggleClaimDetails(this.value);" disabled="#disable1"/>
							</td>
							<td width="25%">
								<s:text name="motor.claimAmount" /><font color="red">*</font><br/>
									<s:textfield name="claimAmount" id="claimAmount" cssClass="input" maxLength="8" onkeyup="checkDecimals(this);" disabled="#disable1"/>
								</td>
							<td width="2%">&nbsp;</td>
                      	</tr>
                      </table></td>
                    </tr>
                    <tr>
                      <td class="heading"><s:text name="motor.additionalVehicleDetails" /></td>
                    </tr>
                    <tr>
                      <td class="bg"><table width="100%" border="0" align="center" cellpadding="4" cellspacing="0">
                      <tr>
							<td width="2%">&nbsp;</td>
                      		<td width="25%"><s:text name="motor.regNo" /><br/>
								<s:textfield name="regNo" id="regNo" cssClass="input"/></td>
							<td width="25%"><s:text name="motor.chassisNo" /><br/>
								<s:textfield name="chassisNo" id="chassisNo" cssClass="input"/></td>
							<td width="25%"><s:text name="motor.engineNo" /><br/>
								<s:textfield name="engineNo" id="engineNo" cssClass="input"/></td>
							<td width="2%">&nbsp;</td>
                      	</tr>
                      	 <tr>
							<td>&nbsp;</td>
                      		<td><s:text name="motor.vehicleColour" /><br/>
								<s:select name="vehicleColour" id="vehicleColour" list="vehicleColourList" listKey="CODE" listValue="CODEDESC" headerKey="" headerValue="-Select-" cssClass="input"/>
							</td>
							<td>
								<s:text name="motor.vehicleRegLoc" /><br/>
								<s:select name="vehicleRegLoc" id="vehicleRegLoc" list="cityList" listKey="CODE" listValue="CODEDESC" headerKey="" headerValue="-Select-" cssClass="input"/>
							</td>
							<td><s:text name="motor.plateCharacter" /><br/>
								<s:select name="plateCharacter" id="plateCharacter" list="plateCharList" listKey="CODE" listValue="CODEDESC" headerKey="" headerValue="-Select-" cssClass="input"/>
							</td>
							<td>&nbsp;</td>
                      	</tr>
              			<tr>
							<td>&nbsp;</td>
							<td><s:text name="motor.leasedYN" /><font color="red">*</font><br/>
								<s:radio name="leasedYN" list="#{'Y':'Yes','N':'No'}" value="%{leasedYN==null?'N':leasedYN}" cssClass="input" onclick="toggleLeasedYN(this.value);"/>
							</td>
							<td><s:text name="motor.bankOfFinance" /><font color="red">*</font><br/>
								<s:select name="bankOfFinance" id="bankOfFinance" list="bankOfFinanceList" listKey="CODE" listValue="CODEDESC" headerKey="" headerValue="-Select-" cssClass="input"/>
							</td>
                      		<td><s:text name="motor.insuredNameAra" /><br/>
								<s:textfield name="insNameArabic" id="insNameArabic" cssClass="input"/></td>
							<td>&nbsp;</td>
              			 </tr>
              			 <tr>
							<td>&nbsp;</td>
							<td><s:text name="motor.insuredAddressAra" /><br/>
								<s:textarea name="insAddressArabic" id="insAddressArabic" cssClass="input" onkeyup="textLimit(this,'200')"/>
							</td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
						  </tr>
                      </table>
                      </td>
                    </tr>
             		 <tr>
                      <td class="heading"><s:text name="motor.coverStartDt" /></td>
              		</tr>
              		<tr>
                      <td class="bg">
                      	<table width="100%" border="0" align="center" cellpadding="4" cellspacing="0">
              			<tr>
							<td width="2%">&nbsp;</td>
                      		<td width="25%"><s:text name="motor.policyStartDt"/><font color="red">*</font><br/>
								<%--onchange="getPolicyEndDate(this.value)"--%>
								<sj:datepicker name="policyStartDate" id="policyStartDate" cssClass="input" displayFormat="dd/mm/yy" minDate="-%{backDt}D" maxDate="+12M" readonly="true"  disabled="#disable1" changeMonth="true" changeYear="true" showAnim="slideDown" duration="fast"/>
								</td>
							<td width="25%">
								<%--<s:text name="motor.policyEndDt" /><font color="red">*</font><br/>
								<s:textfield name="policyEndDate" id="policyEndDate" cssClass="input" readonly="true"/>--%>
							</td>
							<td width="25%">&nbsp;</td>
							<td width="2%">&nbsp;</td>
              			 </tr>
                      	</table>
                      </td>
               		</tr>
                </table>
                </td>
              </tr>
              <tr>
                <td height="2" bgcolor="#FFFFFF"></td>
              </tr>
               <tr>
               	<td>
               		<table align="center">
               			<tr>
                        <td>&nbsp;</td>
                        <td colspan="3" align="center">
                        	<s:submit type="button1" name="close"  key="Back" cssClass="btn" onclick="getBack('home')"/>
                        	<%--<s:if test='#session.user1 != "admin"'> 
                        		<s:submit name="Submit2" type="submit" cssClass="btn" value="Save" onclick="this.form.actionType.value='getSave';" />
                        	</s:if>--%>
                        	<s:submit name="Submit3" type="submit" cssClass="btn" value="Proceed" onclick="this.form.actionType.value='getQuote';disableForm(this.form,false,'');"/>
                        	<s:hidden name="display" />
			                <s:hidden name="actionType" />
			                <s:hidden name="fleetNo" />
			                <s:hidden name="applicationNo" />
			                <s:hidden name="quoteNo" />
			                <s:hidden name="quoteStatus"/>
			                <s:hidden name="referralMsg"/>
                        	</td>
                        <td>&nbsp;</td>
                   	 	</tr>
               		</table>	
               	</td>
               </tr>
           </table>
        </s:form>
        </s:if>
        <s:elseif test="'showPrSummary'.equalsIgnoreCase(display)">
        <s:form id="motor" name="motor" method="post" theme="simple" action="insertOptionCoverMotor.action">
         <table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tr>
	          <td  style="color:red;"><s:actionerror/></td>
	        </tr>
              <tr>
                <td bgcolor="#FFFFFF">
                  <table width="100%" border="0" cellspacing="0" cellpadding="0">
                    <tr>
                      <td class="heading"><s:text name="motor.policyInfo" /></td>
                    </tr>
                     <tr>
                      <td class="bg">
	                      <table width="100%" border="0" align="center" cellpadding="4" cellspacing="0">
	                          <tr>
	                            <td width="2%">&nbsp;</td>
	                            <td width="12%">
	                            	<b><s:text name="motor.quoteNo"  /></b>
	                            </td>
	                            <td width="19%">
	                               <s:property value="quoteNo"/>
	                            </td>
	                            <td width="12%">
	                            	<b><s:text name="motor.quoteDate" /></b>
	                            </td>
	                            <td width="19%">
	                               	<s:property value="quoteDate"/>
	                            </td>
	                            <td width="12%">
	                            	<b><s:text name="motor.product" /></b>
								</td>
	                            <td width="19%">
	                              <s:property value="product"/>
	                            </td>
	                            <td width="2%">&nbsp;</td>
	                          </tr>
	                          <tr>
	                            <td width="2%">&nbsp;</td>
	                            <td>
	                            	<b><s:text name="motor.make"  /></b>
								</td>
	                            <td>
	                               <s:property value="makeName"/>
	                            </td>
	                            <td>
	                            	<b><s:text name="motor.model" /></b>
								</td>
	                            <td>
	                               	<s:property value="modelName"/>
	                            </td>
	                            <td>
	                            	<b><s:text name="motor.typeOfBody" /></b>
								</td>
	                            <td>
	                              <s:property value="typeBodyName"/>
	                            </td>
	                            <td width="2%">&nbsp;</td>
	                          </tr>
	                          <tr>
	                            <td width="2%">&nbsp;</td>
	                            <td>
	                            	<b><s:text name="motor.sumInsured"  /></b>
								</td>
	                            <td>
	                               <s:property value="sumInsured"/>
	                            </td>
	                            <td>
	                            	<b><s:text name="motor.vehicleUsage" /></b>
								</td>
	                            <td>
	                               	<s:property value="vehicleUsageName"/>
	                            </td>
	                            <td>
	                            	<b><s:text name="motor.agencyRepair" /></b>
								</td>
	                            <td>
	                              	<s:property value="agencyRepairName"/>
	                            </td>
	                            <td width="2%">&nbsp;</td>
	                          </tr>
	                          <tr>
	                            <td width="2%">&nbsp;</td>
	                            <td>
	                            	<b><s:text name="motor.customerName"  /></b>
								</td>
	                            <td>
	                               <s:property value="customerName"/>
	                            </td>
	                            <td>
	                            	<b><s:text name="motor.email" /></b>
								</td>
	                            <td>
	                               	<s:property value="email"/>
	                            </td>
	                            <td>
	                            	<b><s:text name="motor.policyStartDt" /></b>
								</td>
	                            <td>
	                              	<s:property value="policyStartDate"/>
	                            </td>
	                            <td width="2%">&nbsp;</td>
	                          </tr>
	                        </table>
                         </td>
                        </tr>
                     </table>
                  </td>
                </tr>
                 <tr>
	                <td bgcolor="#FFFFFF">
		                <table width="100%" border="0" cellspacing="0" cellpadding="0">
		                    <tr>
		                      <td class="heading"><s:text name="motor.premiumInfo" /></td>
		                    </tr>
		                    <tr>
		                    <td class="bg">
		                    <s:if test="%{#request.comparisionDetails.get('policyType') !=null && #request.comparisionDetails.get('policyType').size()>0}">
			                    <s:iterator value="%{#request.comparisionDetails.get('policyType')}" var="polType" status="type">
			                    	<table width="100%" align="center" cellpadding="0" cellspacing="0" width="100%" border="1" class="tableCovers">
					                          	<tr>
					                          	        <td align="center" width="6%" class="tdScheme<s:property value='#type.index % 3'/>" ><img style="cursor: pointer;" alt="${X_ID}" src="${pageContext.request.contextPath}/images/plus.png" 
									       						onclick="showHideDetails(this, 'OptCoverDiplay${X_ID}')" id="plusImg${X_ID}" /></td>
					                          			<td align="left" width="45%" class="tdScheme<s:property value='#type.index % 3' />"><s:property value="X_DATA_NAME"/></td>
													    <td align="right" width="30%" class="tdScheme<s:property value='#type.index % 3' />">
													        <div id="totaldisplay${X_ID}" style="vertical-align: middle;"></div>
													    </td>
													    <td align="center" width="15%" class="tdScheme<s:property value='#type.index % 3' />">
													     	<div>
													     		<input style="visibility: ;" type="radio" <s:property  value="%{(#request.comparisionDetails.get('selectedPolicyType') == #polType.X_ID)?'checked':''}"/> name="policyType" id="policyType${X_ID}" value="${X_ID}" />
													     	</div>
													    </td>
												</tr>
									</table>
									 <s:if test="%{#request.comparisionDetails.get('policyGroup') !=null && #request.comparisionDetails.get('policyGroup').size()>0}">
									<div style="display: none;" id="OptCoverDiplay${X_ID}">
									<table width="100%" align="center" cellpadding="0" cellspacing="0" width="100%" border="1" class="tableCovers">
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
												       		 		<s:if test="%{ #request.comparisionDetails.get('selectedPolicyType').equalsIgnoreCase(#polType.X_ID) && #request.policyInfo.contains(#coverDetails[0].Y_ID)}">
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
												       		 		</s:else>
											       				</s:if>
												       		 </s:else>
										       				<s:if test='#covStat.index == "0"'>
													       		<tr>
													       		 <td align="center" width="6%" class="tdCovers<s:property value='#type.index % 3'/>"></td>
													       		 <td align="left" width="45%" class="tdCovers<s:property value='#type.index % 3'/>"><b><s:property value="GROUP_DESC_ENGLISH"/></b>
													       		</td>
													       		<td align="right" width="30%" class="tdCovers<s:property value='#type.index % 3'/>">
													       		<s:if test='#polGroup.GROUP_ID == "0"'>
													       			<s:property value="#coverDetails[0].DATA_VALUE"/>
													       			<input type="text" name="total${X_ID}" id="total${X_ID}" value="#coverDetails[0].DATA_VALUE" readonly/>
													       			<input type="text" name="default<s:property value="#polType.X_ID"/>" value="<s:property value='#coverDetails[0].DATA_VALUE'/>" />
													       			<input   style="position: absolute;visibility:"  type="checkbox" checked="checked" name="optionalCovers<s:property value="#polType.X_ID"/>" id="optionalCovers<s:property value="#polType.X_ID"/>" value='<s:property value="#optCovId"/>' />
													       		</s:if>
										             			</td>
										             			<td align="center" width="15%" class="tdCovers<s:property value='#type.index % 3'/>">
										             			</td>	
													       		</tr>
												       		</s:if>
												       		 <s:if test="%{#coverDetails[0].DESCRIPTION !=null && #coverDetails[0].DESCRIPTION.length() >0 && (#coverDetails[0].DESCRIPTION.indexOf('~') !=-1)}">
												       			<s:iterator value="%{#coverDetails[0].DESCRIPTION.split('~')}" var="covDesc" status="desc">
													       		<tr>
														       		<td align="center" width="6%" class="tdCovers<s:property value='#type.index % 3'/>"></td>
														       		<td align="left" width="45%" class="tdCovers<s:property value='#type.index % 3'/>">
														       		<img name='firstImg<s:property value="#polGroup.GROUP_ID"/><s:property value="#polType.X_ID"/><s:property value="#coverDetails[0].Y_ID"/>' id='firstImg<s:property value="#polGroup.GROUP_ID"/><s:property value="#polType.X_ID"/><s:property value="#coverDetails[0].Y_ID"/>' src='<s:url value="%{#img}"/>' style="cursor: <s:property value='#cursor'/>;" alt="%{#alt}" height="24" width="24"/>
														       			<s:property/>
														       		<img name='secondImg<s:property value="#polGroup.GROUP_ID"/><s:property value="#polType.X_ID"/><s:property value="#coverDetails[0].Y_ID"/>' id='secondImg<s:property value="#polGroup.GROUP_ID"/><s:property value="#polType.X_ID"/><s:property value="#coverDetails[0].Y_ID"/>' src="<s:url value="/images/delete_cover.png"/>"  style="cursor: pointer; display:<s:property value='%{#disp}'/>;" alt="Remove Cover" height="24" width="24" onclick="return deleteCover(this,'<s:property value="#polType.X_ID"/>','<s:property value="#coverDetails[0].Y_ID"/>','<s:property value="%{#covStat.index}" />','<s:property value="#coverDetails[0].DATA_VALUE"/>','<s:property value="#polGroup.GROUP_ID"/>'+'<s:property value="#polType.X_ID"/>'+'<s:property value="#coverDetails[0].Y_ID"/>');" />
														       		</td>
														       		<td align="right" width="30%" class="tdCovers<s:property value='#type.index % 3'/>">
											             			</td>
											             			<td align="center" width="15%" class="tdCovers<s:property value='#type.index % 3'/>">
											             			</td>	
													       		</tr>
												       			</s:iterator>
												       		</s:if>
												       		<s:elseif test="%{#coverDetails[0].DESCRIPTION!=null && #coverDetails[0].DESCRIPTION!=''}">
												       		<tr>
													       		 <td align="center" width="6%" class="tdCovers<s:property value='#type.index % 3'/>"></td>
													       		 <td align="left" width="45%" class="tdCovers<s:property value='#type.index % 3'/>">
													       		 <img name='firstImg<s:property value="#polGroup.GROUP_ID"/><s:property value="#polType.X_ID"/><s:property value="#coverDetails[0].Y_ID"/>' id='firstImg<s:property value="#polGroup.GROUP_ID"/><s:property value="#polType.X_ID"/><s:property value="#coverDetails[0].Y_ID"/>' src='<s:url value="%{#img}"/>' style="cursor: <s:property value='#cursor'/>;" alt="%{#alt}" height="24" width="24" onclick="return addCover(this,'<s:property value="#polType.X_ID"/>','<s:property value="#coverDetails[0].Y_ID"/>','<s:property value="%{#covStat.index}" />','<s:property value="#coverDetails[0].DATA_VALUE"/>','<s:property value="#polGroup.GROUP_ID"/>'+'<s:property value="#polType.X_ID"/>'+'<s:property value="#coverDetails[0].Y_ID"/>');"/>
													       		 <s:property value="#coverDetails[0].DESCRIPTION"/>
													       		 <img name='secondImg<s:property value="#polGroup.GROUP_ID"/><s:property value="#polType.X_ID"/><s:property value="#coverDetails[0].Y_ID"/>' id='secondImg<s:property value="#polGroup.GROUP_ID"/><s:property value="#polType.X_ID"/><s:property value="#coverDetails[0].Y_ID"/>' src="<s:url value="/images/delete_cover.png"/>"  style="cursor: pointer; display:<s:property value='%{#disp}'/>;" alt="Remove Cover" height="24" width="24" onclick="return deleteCover(this,'<s:property value="#polType.X_ID"/>','<s:property value="#coverDetails[0].Y_ID"/>','<s:property value="%{#covStat.index}" />','<s:property value="#coverDetails[0].DATA_VALUE"/>','<s:property value="#polGroup.GROUP_ID"/>'+'<s:property value="#polType.X_ID"/>'+'<s:property value="#coverDetails[0].Y_ID"/>');" />
													       		</td>
													       		<td align="right" width="30%" class="tdCovers<s:property value='#type.index % 3'/>">
													       		<s:if test='#polGroup.GROUP_ID != "0"'>
													       		<input type="text" name="default<s:property value="#polType.X_ID"/>" value="<s:property value='#preVal'/>" />
													       		<input   style="position: absolute;visibility:"  type="checkbox" checked="checked" name="optionalCovers<s:property value="#polType.X_ID"/>" id="optionalCovers<s:property value="#polType.X_ID"/>" value='<s:property value="#optCovId"/>' />
													       		<s:property value="#coverDetails[0].DATA_VALUE"/>
													       		</s:if>
										             			</td>
										             			<td align="center" width="15%" class="tdCovers<s:property value='#type.index % 3'/>">
										             			</td>	
													       		</tr>
												       		</s:elseif>
												      </s:iterator>
				                     	        </s:iterator>
			                     	  			<tr>
										             		<td align="center" width="6%" class="tdCovers<s:property value='#type.index % 3'/>"></td>
										             		<td align="left" width="45%" class="tdCovers<s:property value='#type.index % 3'/>"></td>
										             		<td align="center" width="30%" class="tdCovers<s:property value='#type.index % 3'/>">
										             		</td>
									             			<td align="center" width="15%" class="tdCovers<s:property value='#type.index % 3'/>">
									             				<input type="button"  class="btn"  name="tdCovers" value='<s:property value='%{#session.user1 != "admin" ? "Buy Policy" : "Approve Quote"}'/>' onclick="SubmitAction('<s:property value="%{quoteNo}"/>','<s:property value="%{applicationNo}"/>','<s:property value="%{typeBody}"/>','false','<s:property value="#polType.X_ID"/>');" />
									             			</td>	
										             	</tr>
									           </table>
			                     	</div>
			                	    </s:if>
			                    </s:iterator>
		                    </s:if>
		                    </td>
		                    </tr>
		                    <tr>
	                          <td class="bg">
	                          <table cellpadding="3" cellspacing="1" align="center" width="100%" border="1px bevel">
	                          <%try{
	                          String pat = request.getContextPath();
	                          List onloadCal = new ArrayList();
	                          String referral=(String) request.getAttribute("referral") == null ? "" : (String) request.getAttribute("referral");
	                          List policyInfo = (ArrayList)request.getAttribute("policyInfo");
	                          HashMap comparisionDetails = (request.getAttribute("comparisionDetails")==null?new HashMap():(HashMap)request.getAttribute("comparisionDetails"));
	                          String isModifyRate=(String)(comparisionDetails.get("rateModification")==null?"":comparisionDetails.get("rateModification"));
	                          List policyType = (List)comparisionDetails.get("policyType");
							  List policyGroup = (List)comparisionDetails.get("policyGroup");
							  String selectType = (String)(comparisionDetails.get("selectedPolicyType")==null?"":comparisionDetails.get("selectedPolicyType"));
					          if(policyType!=null && policyType.size()>0){
	                           %>
	                           <tr class="formtxtf">
										<% for(int i=0; i<policyType.size(); i++){ 
											String bgcolor="";
											switch(i%3){
											case 0:
												bgcolor = "style='background-color: rgb(84,71,65);border: bevel;'";
												break;
											case 1:
												bgcolor = "style='background-color: rgb(84,71,65);border: bevel;'";
												break;
											case 2:
												bgcolor = "style='background-color: rgb(84,71,65);border: bevel;'";
												break;
											default:
												bgcolor = "style='background-color: rgb(84,71,65);border: bevel;'";
												break;
											}
										%>
										<td <%=bgcolor%>>
											<b>
												<font size="3" color="white"><b><%=((Map)policyType.get(i)).get("X_DATA_NAME").toString()%></b></font>
												<div><%--<input type="radio" <%=selectType.equalsIgnoreCase(((Map)policyType.get(i)).get("X_ID").toString())?"checked='checked'":"" %> name="policyType" onclick="makeReadOnly()" style="visibility: " id="policyType<%=((Map)policyType.get(i)).get("X_ID").toString()%>" value="<%=((Map)policyType.get(i)).get("X_ID").toString()%>" /> --%></div> 												
											</b>
										</td>
										<% } %>
									</tr>
									<%	if(policyGroup!=null && policyGroup.size()>0){
											int coverCount[] = new int[policyType.size()];
											for(int group = 0; group < policyGroup.size(); group++){
											List covers = (List)comparisionDetails.get("policyCovers"+((Map)policyGroup.get(group)).get("GROUP_ID").toString());
											if(covers!=null && covers.size()>0){												
									%>
									<tr class="formtxtf">
										<% 	
											for(int types = 0; types < policyType.size(); types++){  
											coverCount[types] = 0; 
											String bgcolor="";
											switch(types%3){
											case 0:
												bgcolor = "style='background-color: rgb(116,194,198);'";
												break;
											case 1:
												bgcolor = "style='background-color: rgb(180,198,112);'";
												break;
											case 2:
												bgcolor = "style='background-color: rgb(179,140,128);'";
												break;
											default:
												bgcolor = "style='background-color: rgb(116,194,198);'";
												break;
											}
										%>
										<td class="whtFont" <%=bgcolor %> valign="top">
											<b><font size="2">
												<%
												List coverDetails = (List)comparisionDetails.get("policyCoverDetails"+((Map)policyGroup.get(group)).get("GROUP_ID").toString()+((Map)policyType.get(types)).get("X_ID").toString()+((Map)covers.get(0)).get("Y_DATA_NAME").toString());
												if("0".equalsIgnoreCase(((Map)policyGroup.get(group)).get("GROUP_ID").toString())){ 
												List rateRange = new ArrayList();
												String startRate = "0";
												String endRate = "0";
				 								String modifyRate = "";
				 								List rates = (List)comparisionDetails.get("brokerRates"+((Map)policyGroup.get(group)).get("GROUP_ID").toString()+((Map)policyType.get(types)).get("X_ID").toString()+((Map)covers.get(0)).get("Y_DATA_NAME").toString());
				 								if(rates!=null && rates.size()>0){
				 									//for(int i=0;i<4;i++){
				 									Map rateMap=(Map)rates.get(0);
				 										if(rateMap.get("RATE_ONE")!=null && Float.parseFloat(rateMap.get("RATE_ONE").toString())>0.0){
				 											rateRange.add(rateMap.get("RATE_ONE"));
				 										}
				 										if(rateMap.get("RATE_TWO")!=null && Float.parseFloat(rateMap.get("RATE_TWO").toString())>0.0){
				 											rateRange.add(rateMap.get("RATE_TWO"));
				 										}
				 										if(rateMap.get("RATE_THREE")!=null && Float.parseFloat(rateMap.get("RATE_THREE").toString())>0.0){
				 											rateRange.add(rateMap.get("RATE_THREE"));
				 										}
				 										if(rateMap.get("RATE_FOUR")!=null && Float.parseFloat(rateMap.get("RATE_FOUR").toString())>0.0){
				 											rateRange.add(rateMap.get("RATE_FOUR"));
				 										}
				 									//}
				 									modifyRate = rateMap.get("PROVISION_FOR_RATE")==null?"N":rateMap.get("PROVISION_FOR_RATE").toString();
				 								}
				 								if(rateRange.size()>0){ 
													Collections.sort(rateRange);
												}
				 								startRate = (rateRange.size()>0?rateRange.get(0).toString():"0");
												endRate = (rateRange.size()>0?rateRange.get((rateRange.size()-1)).toString():"0");
												String tempstartRate = "0";
												String tempendRate = "0";
												if(coverDetails!=null && coverDetails.size()>0){
													tempstartRate = (Float.parseFloat(((Map)policyType.get(types)).get("BASE_RATE").toString())>Float.parseFloat(startRate)?((Map)policyType.get(types)).get("BASE_RATE").toString():startRate);
													tempendRate = (Float.parseFloat(tempstartRate)>Float.parseFloat(endRate)?tempstartRate:endRate);
													String rateval = (Float.parseFloat(((Map)policyType.get(types)).get("RATE").toString())>Float.parseFloat(startRate)?((Map)policyType.get(types)).get("RATE").toString():startRate);
		 											if(Float.parseFloat(((Map)policyType.get(types)).get("RATE").toString())<Float.parseFloat(startRate)){
														onloadCal.add(((Map)policyType.get(types)).get("X_ID").toString());
													}	   
												%>
													yy<%=((Map)policyGroup.get(group)).get("GROUP_DESC_ENGLISH").toString()%>: 
													<%if(referral.length()>0){ %>
													<div align="right">
														<font color="red">Referral</font>
														<input type="hidden" name="coverRate<%=((Map)policyType.get(types)).get("X_ID").toString() %>" id="coverRate<%=((Map)policyType.get(types)).get("X_ID").toString() %>" value="<%=((Map)policyType.get(types)).get("RATE").toString() %>" />
													</div>
													<%}else{ %>
													<div align="left">
													<table width="100%">
														<tr>
														<td align="left" width="50%">
														<input type="hidden" name="startRate<%=((Map)policyType.get(types)).get("X_ID").toString()%>" id="startRate<%=((Map)policyType.get(types)).get("X_ID").toString() %>" value="<%=tempstartRate %>" />
														<input type="hidden" name="endRate<%=((Map)policyType.get(types)).get("X_ID").toString() %>" id="endRate<%=((Map)policyType.get(types)).get("X_ID").toString() %>" value="<%=tempendRate %>" />
														<input type="hidden" name="minpremium<%=((Map)policyType.get(types)).get("X_ID").toString() %>" id="minpremium<%=((Map)policyType.get(types)).get("X_ID").toString() %>" value="<%=((Map)policyType.get(types)).get("MIN_PREMIUM").toString() %>" />
														<input type="hidden" name="coverBaseRate<%=((Map)policyType.get(types)).get("X_ID").toString()%>" id="coverBaseRate<%=((Map)policyType.get(types)).get("X_ID").toString() %>" value="<%=((Map)policyType.get(types)).get("RATE").toString() %>" />
														<%if("Y".equalsIgnoreCase(isModifyRate) && "Y".equalsIgnoreCase(modifyRate)){ %>
															<select	name="coverRate<%=((Map)policyType.get(types)).get("X_ID").toString() %>" id="coverRate<%=((Map)policyType.get(types)).get("X_ID").toString() %>" onchange="calculatePremium('<%=((Map)policyType.get(types)).get("X_ID").toString() %>')">
																<% if(rateRange.size()>0){
																	int valcount = 0;
																	boolean ratechange = true;
																	for(int i=0;i<rateRange.size();i++){
																		if(Float.parseFloat(rateRange.get(i).toString())>=Float.parseFloat(tempstartRate)){
																		valcount++;
																		if(Float.parseFloat(((Map)policyType.get(types)).get("RATE").toString())==Float.parseFloat(rateRange.get(i).toString())){
																			ratechange = false;
																		}
																		if(Float.parseFloat(((Map)policyType.get(types)).get("RATE").toString())<Float.parseFloat(rateRange.get(i).toString())){
																			if(ratechange){
																				ratechange = false;
																%>
																<option value="<%=((Map)policyType.get(types)).get("RATE").toString() %>" selected="selected">
																	<%=((Map)policyType.get(types)).get("RATE").toString() %>
																</option>
																<%
																			}
																		}
																%>
																<option value="<%=rateRange.get(i).toString() %>" <%=(Float.parseFloat(((Map)policyType.get(types)).get("RATE").toString())==Float.parseFloat(rateRange.get(i).toString())?"selected":"") %>>
																	<%=rateRange.get(i).toString() %>
																</option>
																<% 		}} %>
																<%	if(valcount<=0){ %>
																<option value="<%=tempstartRate %>"><%=tempstartRate %></option>
																<%
																	}}else{ %>
																<option value=""></option>
																<% } %>
															</select>
														<% }else{ %>
															<input type="text" name="coverRate<%=((Map)policyType.get(types)).get("X_ID").toString()  %>" id="coverRate<%=((Map)policyType.get(types)).get("X_ID").toString()  %>" value="<%=rateval %>" />
															<% } %>
														&nbsp;
														<input type="text" name="coverDataValue<%=((Map)policyType.get(types)).get("X_ID").toString()  %>" id="coverDataValue<%=((Map)policyType.get(types)).get("X_ID").toString()  %>" value="<%=((Map)coverDetails.get(0)).get("DATA_VALUE").toString() %>" />
														</td>
														<td align="right" width="40%" id="coverValue<%=((Map)policyType.get(types)).get("X_ID").toString()  %>">
															<font color="white">XX<%="0".equalsIgnoreCase(((Map)coverDetails.get(0)).get("DATA_VALUE").toString())?"":((Map)coverDetails.get(0)).get("DATA_VALUE").toString() %></font>
														</td>
														<td align="right" width="10%" >&nbsp;</td>
														</tr>
													</table>
													</div>
													<%} %>
													<input type="hidden" name="basic<%=((Map)policyType.get(types)).get("X_ID").toString()%>" value="<%=((Map)coverDetails.get(0)).get("DATA_VALUE").toString() %>" />
												<%}}else{ %>
												asd	<%=((Map)policyGroup.get(group)).get("GROUP_DESC_ENGLISH").toString()%>: 
												<%} %>
											</font></b>
										<%	if("0".equalsIgnoreCase(((Map)policyGroup.get(group)).get("GROUP_ID").toString())){
											for(int cover = 0; cover < covers.size(); cover++){												
												List coverDetails1 = (List)comparisionDetails.get("policyCoverDetails"+((Map)policyGroup.get(group)).get("GROUP_ID").toString()+((Map)policyType.get(types)).get("X_ID").toString()+((Map)covers.get(0)).get("Y_DATA_NAME").toString());
										%>
											<div id="cover<%=((Map)policyGroup.get(group)).get("GROUP_ID").toString()+((Map)policyType.get(types)).get("X_ID").toString() +((Map)covers.get(0)).get("Y_DATA_NAME").toString() %>">
											<table width="100%">
												<% if(coverDetails!=null && coverDetails.size()>0){
														coverCount[types]++;
														String description = ((Map)coverDetails.get(0)).get("DESCRIPTION").toString();
														String[] temp = description.split("~");
														if(temp==null || temp.length<=0){
															temp = new String[]{description};
														}
														if("Y".equalsIgnoreCase(((Map)coverDetails.get(0)).get("IS_SELECTED").toString())){
														String premiumVal = ((Map)coverDetails.get(0)).get("DATA_VALUE").toString();
														String coverVal = ((Map)coverDetails.get(0)).get("Y_ID").toString();
														if(selectType.equalsIgnoreCase(((Map)policyType.get(types)).get("X_ID").toString()) && "Y".equalsIgnoreCase(((Map)coverDetails.get(0)).get("DELETABLE").toString()) && !policyInfo.contains(((Map)coverDetails.get(0)).get("Y_ID").toString())){
															premiumVal = "";
															coverVal="";
														}
												%>
														<%--<div><input type="text" name="default<%=((Map)policyType.get(types)).get("X_ID").toString() %>" value="<%=premiumVal %>" /> --%>
														<input  style="visibility: ;position: absolute;"  type="checkbox" checked="checked" name="optionalCovers<%=((Map)policyType.get(types)).get("X_ID").toString() %>" id="optionalCovers<%=((Map)policyType.get(types)).get("X_ID").toString() %>" value="<%=coverVal %>" />	</div>														
												<% 	}else if("Y".equalsIgnoreCase(((Map)coverDetails.get(0)).get("IS_ADDON").toString())){ 
														String covered = "";
														if(selectType.equalsIgnoreCase(((Map)policyType.get(types)).get("X_ID").toString()) && policyInfo.contains(((Map)coverDetails.get(0)).get("Y_ID").toString())){
															covered = ((Map)coverDetails.get(0)).get("Y_ID").toString();
														}
												%>
														<div><input  style="visibility: ;position: absolute;"   type="checkbox" checked="checked" name="optionalCovers<%=((Map)policyType.get(types)).get("X_ID").toString() %>" id="optionalCovers<%=((Map)policyType.get(types)).get("X_ID").toString()%>" value="<%=covered %>" /></div>
												<% 	} 
													for(int i=0;i<temp.length;i++){
												%>
												<tr>
													<td align="left" width="10%">
													<% if("Y".equalsIgnoreCase(((Map)coverDetails.get(0)).get("IS_SELECTED").toString())){  
															String img = "";
															String alt = "";
															String cursor = "";
															if(selectType.equalsIgnoreCase(((Map)policyType.get(types)).get("X_ID").toString()) && !policyInfo.contains(((Map)coverDetails.get(0)).get("Y_ID").toString()) && "Y".equalsIgnoreCase(((Map)coverDetails.get(0)).get("DELETABLE").toString())){
																img = "add_cover.png";
																alt = "Add Cover";
																cursor = "pointer";
															}else{
																img = "selected_cover.png";
																alt = "Default Cover";
																cursor = "default";
															}
													%>
														<img name="firstImg<%=((Map)policyGroup.get(group)).get("GROUP_ID").toString()+((Map)policyType.get(types)).get("X_ID").toString()+((Map)coverDetails.get(0)).get("Y_ID").toString() %>" id="firstImg<%=((Map)policyGroup.get(group)).get("GROUP_ID").toString()+((Map)policyType.get(types)).get("X_ID").toString()+((Map)coverDetails.get(0)).get("Y_ID").toString() %>" style="cursor: <%=cursor %>;" onclick="return addCover(this,'<%=((Map)policyType.get(types)).get("X_ID").toString() %>','<%=((Map)coverDetails.get(0)).get("Y_ID").toString() %>','<%=coverCount[types] %>','<%=((Map)coverDetails.get(0)).get("DATA_VALUE").toString() %>','<%=((Map)policyGroup.get(group)).get("GROUP_ID").toString()+((Map)policyType.get(types)).get("X_ID").toString()+((Map)coverDetails.get(0)).get("Y_ID").toString() %>');"  alt="<%=alt %>" src="<%=pat %>/images/<%=img %>" height="24" width="24">
													<% }else if("Y".equalsIgnoreCase(((Map)coverDetails.get(0)).get("IS_ADDON").toString())){ 															
															String img = policyInfo.contains(((Map)coverDetails.get(0)).get("Y_ID").toString())?"selected_cover.png":"add_cover.png";
															String alt = policyInfo.contains(((Map)coverDetails.get(0)).get("Y_ID").toString())?"Added":"Add Cover";
															String cursor = policyInfo.contains(((Map)coverDetails.get(0)).get("Y_ID").toString())?"default":"pointer";
															if(policyInfo.contains(((Map)coverDetails.get(0)).get("Y_ID").toString())){
													%>
														<%--<input type="text" name="default<%=((Map)policyType.get(types)).get("X_ID").toString() %>" value="<%=((Map)coverDetails.get(0)).get("DATA_VALUE").toString() %>" /> --%>															
														<% } %>
														<img name="firstImg<%=((Map)policyGroup.get(group)).get("GROUP_ID").toString()+((Map)policyType.get(types)).get("X_ID").toString()+((Map)coverDetails.get(0)).get("Y_ID").toString() %>" id="firstImg<%=((Map)policyGroup.get(group)).get("GROUP_ID").toString()+((Map)policyType.get(types)).get("X_ID").toString()+((Map)coverDetails.get(0)).get("Y_ID").toString() %>" style="cursor: <%=cursor %>;" onclick="return addCover(this,'<%=((Map)policyType.get(types)).get("X_ID").toString() %>','<%=((Map)coverDetails.get(0)).get("Y_ID").toString() %>','<%=coverCount[types] %>','<%=((Map)coverDetails.get(0)).get("DATA_VALUE").toString() %>','<%=((Map)policyGroup.get(group)).get("GROUP_ID").toString()+((Map)policyType.get(types)).get("X_ID").toString()+((Map)coverDetails.get(0)).get("Y_ID").toString() %>');" alt="<%=alt %>" src="<%=pat %>/images/<%=img %>" height="24" width="24">
													<% } %>
													</td>
													<td align="left" width="60%"><font color="white"><%=temp[i] %></font></td>
													<td align="right" width="20%">
														<%if(referral.length()>0){ %>
														<font color="red">Referral</font>
														<%}else{ %>
														<%if(!"0".equalsIgnoreCase(((Map)policyGroup.get(group)).get("GROUP_ID").toString())){ %>
														<font color="white"><%="0".equalsIgnoreCase(((Map)coverDetails.get(0)).get("DATA_VALUE").toString())?"":((Map)coverDetails.get(0)).get("DATA_VALUE").toString().replace("-","") %></font>
														<%}} %>
													</td>
													<td align="center" width="10%" valign="top">
														<%if("Y".equalsIgnoreCase(((Map)coverDetails.get(0)).get("DELETABLE").toString())){ 
															String diplayType = "block";
															if(selectType.equalsIgnoreCase(((Map)policyType.get(types)).get("X_ID").toString()) && !policyInfo.contains(((Map)coverDetails.get(0)).get("Y_ID").toString())){
																diplayType = "none";
															}
														%>
														<img name="secondImg<%=((Map)policyGroup.get(group)).get("GROUP_ID").toString()+((Map)policyType.get(types)).get("X_ID").toString()+((Map)coverDetails.get(0)).get("Y_ID").toString() %>" id="secondImg<%=((Map)policyGroup.get(group)).get("GROUP_ID").toString()+((Map)policyType.get(types)).get("X_ID").toString()+((Map)coverDetails.get(0)).get("Y_ID").toString() %>" style="cursor: pointer; display: <%=diplayType %>;" onclick="return deleteCover(this,'<%=((Map)policyType.get(types)).get("X_ID").toString() %>','<%=((Map)coverDetails.get(0)).get("Y_ID").toString() %>','<%=coverCount[types] %>','<%=((Map)coverDetails.get(0)).get("DATA_VALUE").toString() %>','<%=((Map)policyGroup.get(group)).get("GROUP_ID").toString()+((Map)policyType.get(types)).get("X_ID").toString()+((Map)coverDetails.get(0)).get("Y_ID").toString() %>')" alt="Remove Cover" src="<%=pat %>/images/delete_cover.png" height="24" width="24">
														<%}else{ 
															String diplayType = (selectType.equalsIgnoreCase(((Map)policyType.get(types)).get("X_ID").toString()) && policyInfo.contains(((Map)coverDetails.get(0)).get("Y_ID").toString()) && !"Y".equalsIgnoreCase(((Map)coverDetails.get(0)).get("IS_SELECTED").toString()))?"block":"none";
														%>
														<img name="secondImg<%=((Map)policyGroup.get(group)).get("GROUP_ID").toString()+((Map)policyType.get(types)).get("X_ID").toString()+((Map)coverDetails.get(0)).get("Y_ID").toString() %>" id="secondImg<%=((Map)policyGroup.get(group)).get("GROUP_ID").toString()+((Map)policyType.get(types)).get("X_ID").toString()+((Map)coverDetails.get(0)).get("Y_ID").toString() %>" style="cursor: pointer; display: <%=diplayType %>;" onclick="return deleteCover(this,'<%=((Map)policyType.get(types)).get("X_ID").toString() %>','<%=((Map)coverDetails.get(0)).get("Y_ID").toString() %>','<%=coverCount[types] %>','<%=((Map)coverDetails.get(0)).get("DATA_VALUE").toString() %>','<%=((Map)policyGroup.get(group)).get("GROUP_ID").toString()+((Map)policyType.get(types)).get("X_ID").toString()+((Map)coverDetails.get(0)).get("Y_ID").toString() %>')" alt="Remove Cover" src="<%=pat %>/images/delete_cover.png" height="24" width="24">
														<%} %>
													</td>
												</tr>
												<% }}else{ %>
												<tr>
													<td colspan="4" align="center">--</td>
												</tr>
												<% } %>												
											</table>
											</div>
										<%	} } }%>
										</td>
									</tr>
									<%
										if(!"0".equalsIgnoreCase(((Map)policyGroup.get(group)).get("GROUP_ID").toString())){
										for(int cover = 0; cover < covers.size(); cover++){
									%>
									<tr class="formtxtf">
										<%	for(int type = 0; type < policyType.size(); type++){
												List coverDetails = (List)comparisionDetails.get("policyCoverDetails"+((Map)policyGroup.get(group)).get("GROUP_ID").toString()+((Map)policyType.get(type)).get("X_ID").toString()+((Map)covers.get(cover)).get("Y_DATA_NAME").toString()); 
											String bgcolor="";
											switch(type%3){
											case 0:
												bgcolor = "style='background-color: rgb(116,194,198);'";
												break;
											case 1:
												bgcolor = "style='background-color: rgb(180,198,112);'";
												break;
											case 2:
												bgcolor = "style='background-color: rgb(179,140,128);'";
												break;
											default:
												bgcolor = "style='background-color: rgb(116,194,198);'";
												break;
											}
										%>
										<td class="whtFont" <%=bgcolor %>>
											<div id="cover<%=((Map)policyGroup.get(group)).get("GROUP_ID").toString()+((Map)policyType.get(type)).get("X_ID").toString()+((Map)covers.get(cover)).get("Y_DATA_NAME").toString() %>">
											<table width="100%">
												<% if(coverDetails!=null && coverDetails.size()>0){
														coverCount[type]++;
														String description = ((Map)coverDetails.get(0)).get("DESCRIPTION").toString();
														String[] temp = description.split("~");
														if(temp==null || temp.length<=0){
															temp = new String[]{description};
														}
														if("Y".equalsIgnoreCase(((Map)coverDetails.get(0)).get("IS_SELECTED").toString())){
														String premiumVal = ((Map)coverDetails.get(0)).get("DATA_VALUE").toString();
														String coverVal = ((Map)coverDetails.get(0)).get("Y_ID").toString();
														if(selectType.equalsIgnoreCase(((Map)policyType.get(type)).get("X_ID").toString()) && "Y".equalsIgnoreCase(((Map)coverDetails.get(0)).get("DELETABLE").toString()) && !policyInfo.contains(((Map)coverDetails.get(0)).get("Y_ID").toString())){
															premiumVal = "";
															coverVal="";
														}
												%>
														 <%--<div><input type="text" name="default<%=((Map)policyType.get(type)).get("X_ID").toString() %>" value="<%=premiumVal %>" />--%>
														<input  style="visibility: ;position: absolute;"  type="checkbox" checked="checked" name="optionalCovers<%=((Map)policyType.get(type)).get("X_ID").toString() %>" id="optionalCovers<%=((Map)policyType.get(type)).get("X_ID").toString() %>" value="<%=coverVal %>" /></div>												
												<% 	}else if("Y".equalsIgnoreCase(((Map)coverDetails.get(0)).get("IS_ADDON").toString())){ 
														String covered = "";
														if(selectType.equalsIgnoreCase(((Map)policyType.get(type)).get("X_ID").toString()) && policyInfo.contains(((Map)coverDetails.get(0)).get("Y_ID").toString())){
															covered = ((Map)coverDetails.get(0)).get("Y_ID").toString();
														}
												%>
														<div><input   style="visibility: ;position: absolute;"  type="checkbox" checked="checked" name="optionalCovers<%=((Map)policyType.get(type)).get("X_ID").toString() %>" id="optionalCovers<%=((Map)policyType.get(type)).get("X_ID").toString() %>" value="<%=covered%>" /></div>
												<% 	} 
													for(int i=0;i<temp.length;i++){
												%>
												<tr>
													<td align="left" width="10%">
													<% if("Y".equalsIgnoreCase(((Map)coverDetails.get(0)).get("IS_SELECTED").toString())){  
															String img = "";
															String alt = "";
															String cursor = "";
															if(selectType.equalsIgnoreCase(((Map)policyType.get(type)).get("X_ID").toString()) && !policyInfo.contains(((Map)coverDetails.get(0)).get("Y_ID").toString()) && "Y".equalsIgnoreCase(((Map)coverDetails.get(0)).get("DELETABLE").toString())){
																img = "add_cover.png";
																alt = "Add Cover";
																cursor = "pointer";
															}else{
																img = "selected_cover.png";
																alt = "Default Cover";
																cursor = "default";
															}
													%>
														<img name="firstImg<%=((Map)policyGroup.get(group)).get("GROUP_ID").toString()+((Map)policyType.get(type)).get("X_ID").toString()+((Map)coverDetails.get(0)).get("Y_ID").toString() %>" id="firstImg<%=((Map)policyGroup.get(group)).get("GROUP_ID").toString()+((Map)policyType.get(type)).get("X_ID").toString()+((Map)coverDetails.get(0)).get("Y_ID").toString() %>" style="cursor: <%=cursor %>;" onclick="return addCover(this,'<%=((Map)policyType.get(type)).get("X_ID").toString() %>','<%=((Map)coverDetails.get(0)).get("Y_ID").toString() %>','<%=coverCount[type] %>','<%=((Map)coverDetails.get(0)).get("DATA_VALUE").toString() %>','<%=((Map)policyGroup.get(group)).get("GROUP_ID").toString()+((Map)policyType.get(type)).get("X_ID").toString()+((Map)coverDetails.get(0)).get("Y_ID").toString() %>');"  alt="<%=alt %>" src="<%=pat %>/images/<%=img %>" height="24" width="24">
													<% }else if("Y".equalsIgnoreCase(((Map)coverDetails.get(0)).get("IS_ADDON").toString())){ 															
															String img = policyInfo.contains(((Map)coverDetails.get(0)).get("Y_ID").toString())?"selected_cover.png":"add_cover.png";
															String alt = policyInfo.contains(((Map)coverDetails.get(0)).get("Y_ID").toString())?"Added":"Add Cover";
															String cursor = policyInfo.contains(((Map)coverDetails.get(0)).get("Y_ID").toString())?"default":"pointer";
															if(policyInfo.contains(((Map)coverDetails.get(0)).get("Y_ID").toString())){
													%>
														<%--<input type="text" name="default<%=((Map)policyType.get(type)).get("X_ID").toString() %>" value="<%=((Map)coverDetails.get(0)).get("DATA_VALUE").toString() %>" /> --%>															
														<% } %>
														<img name="firstImg<%=((Map)policyGroup.get(group)).get("GROUP_ID").toString()+((Map)policyType.get(type)).get("X_ID").toString()+((Map)coverDetails.get(0)).get("Y_ID").toString() %>" id="firstImg<%=((Map)policyGroup.get(group)).get("GROUP_ID").toString()+((Map)policyType.get(type)).get("X_ID").toString()+((Map)coverDetails.get(0)).get("Y_ID").toString() %>" style="cursor: <%=cursor %>;" onclick="return addCover(this,'<%=((Map)policyType.get(type)).get("X_ID").toString() %>','<%=((Map)coverDetails.get(0)).get("Y_ID").toString() %>','<%=coverCount[type] %>','<%=((Map)coverDetails.get(0)).get("DATA_VALUE").toString() %>','<%=((Map)policyGroup.get(group)).get("GROUP_ID").toString()+((Map)policyType.get(type)).get("X_ID").toString()+((Map)coverDetails.get(0)).get("Y_ID").toString() %>');" alt="<%=alt %>" src="<%=pat %>/images/<%=img %>" height="24" width="24">
													<% } %>
													</td>
													<td align="left" width="60%"><font color="white"><%=temp[i] %></font></td>
													<td align="right" width="20%">
														<%if(referral.length()>0){ %>
														<font color="red">Referral</font>
														<%}else{ %>
														<%if(!"0".equalsIgnoreCase(((Map)policyGroup.get(group)).get("GROUP_ID").toString())){ %>
														<font color="white"><%="0".equalsIgnoreCase(((Map)coverDetails.get(0)).get("DATA_VALUE").toString())?"":((Map)coverDetails.get(0)).get("DATA_VALUE").toString().replace("-","") %></font>
														<%}} %>
													</td>
													<td align="center" width="10%" valign="top">
														<%if("Y".equalsIgnoreCase(((Map)coverDetails.get(0)).get("DELETABLE").toString())){ 
															String diplayType = "block";
															if(selectType.equalsIgnoreCase(((Map)policyType.get(type)).get("X_ID").toString()) && !policyInfo.contains(((Map)coverDetails.get(0)).get("Y_ID").toString())){
																diplayType = "none";
															}
														%>
														<img name="secondImg<%=((Map)policyGroup.get(group)).get("GROUP_ID").toString()+((Map)policyType.get(type)).get("X_ID").toString()+((Map)coverDetails.get(0)).get("Y_ID").toString() %>" id="secondImg<%=((Map)policyGroup.get(group)).get("GROUP_ID").toString()+((Map)policyType.get(type)).get("X_ID").toString()+((Map)coverDetails.get(0)).get("Y_ID").toString() %>" style="cursor: pointer; display: <%=diplayType %>;" onclick="return deleteCover(this,'<%=((Map)policyType.get(type)).get("X_ID").toString() %>','<%=((Map)coverDetails.get(0)).get("Y_ID").toString() %>','<%=coverCount[type] %>','<%=((Map)coverDetails.get(0)).get("DATA_VALUE").toString() %>','<%=((Map)policyGroup.get(group)).get("GROUP_ID").toString()+((Map)policyType.get(type)).get("X_ID").toString()+((Map)coverDetails.get(0)).get("Y_ID").toString() %>')" alt="Remove Cover" src="<%=pat %>/images/delete_cover.png" height="24" width="24">
														<%}else{ 
															String diplayType = (selectType.equalsIgnoreCase(((Map)policyType.get(type)).get("X_ID").toString()) && policyInfo.contains(((Map)coverDetails.get(0)).get("Y_ID").toString()) && !"Y".equalsIgnoreCase(((Map)coverDetails.get(0)).get("IS_SELECTED").toString()))?"block":"none";
														%>
														<img name="secondImg<%=((Map)policyGroup.get(group)).get("GROUP_ID").toString()+((Map)policyType.get(type)).get("X_ID").toString()+((Map)coverDetails.get(0)).get("Y_ID").toString() %>" id="secondImg<%=((Map)policyGroup.get(group)).get("GROUP_ID").toString()+((Map)policyType.get(type)).get("X_ID").toString()+((Map)coverDetails.get(0)).get("Y_ID").toString() %>" style="cursor: pointer; display: <%=diplayType %>;" onclick="return deleteCover(this,'<%=((Map)policyType.get(type)).get("X_ID").toString() %>','<%=((Map)coverDetails.get(0)).get("Y_ID").toString() %>','<%=coverCount[type] %>','<%=((Map)coverDetails.get(0)).get("DATA_VALUE").toString() %>','<%=((Map)policyGroup.get(group)).get("GROUP_ID").toString()+((Map)policyType.get(type)).get("X_ID").toString()+((Map)coverDetails.get(0)).get("Y_ID").toString() %>')" alt="Remove Cover" src="<%=pat %>/images/delete_cover.png" height="24" width="24">
														<%} %>
													</td>
												</tr>
												<% }}else{ %>
												<tr>
													<td colspan="4" align="center">--</td>
												</tr>
												<% } %>												
											</table>
											</div>
										</td>
										<% 	} %>
									</tr>										
									<%	}	}
										}
									}}
									%>
									<tr class="formtxtf">
										<% for(int i=0; i<policyType.size(); i++){  
											String bgcolor="";
											switch(i%3){
											case 0:
												bgcolor = "style='background-color: rgb(116,194,198);'";
												break;
											case 1:
												bgcolor = "style='background-color: rgb(180,198,112);'";
												break;
											case 2:
												bgcolor = "style='background-color: rgb(179,140,128);'";
												break;
											default:
												bgcolor = "style='background-color: rgb(116,194,198);'";
												break;
											}
										%>
										<td class="whtFont" <%=bgcolor %>>
											<table width="100%">
												<tr>
													<td align="left" width="60%" >
														<b><font size="3" color="white">TOTAL PREMIUM:&nbsp;&nbsp;</font></b>
													</td>
													<td align="right" width="40%">
														<div id="totaldisplay<%=((Map)policyType.get(i)).get("X_ID").toString() %>" style="text-align: right;font-weight: bold;font-size: medium;color: white;"></div>
														<%--<input type="text" name="total<%=((Map)policyType.get(i)).get("X_ID").toString()%>" id="total<%=((Map)policyType.get(i)).get("X_ID").toString()%>" value="" readonly/> --%>	
													</td>											
												</tr>
												<tr>
													<td align="center" valign="middle" colspan="2">
														<a
															onclick="return SubmitAction('<s:property value="%{quoteNo}"/>','<s:property value="%{applicationNo}"/>','<s:property value="%{typeBody}"/>','false','<%=((Map)policyType.get(i)).get("X_ID").toString()%>');" href="#"><input style="background-color: black;color: rgb(230,249,250);font-weight: bold" type="button" value="Proceed to Buy Policy" />
														</a>
													</td>
												</tr>
											</table>
										</td>
										<% } %>
									</tr>																	
								</table>
								<input type="hidden" name="Remarks" value="D">
								<%
								 	}else{  
								%>
									<table align="center" cellpadding="0" cellspacing="0">
										<tr>
											<td height="32" align="center" valign="middle">
								 				<font color="red" size="3">	
								 					Quote Cannot be provided. Please Contact XYZ Representative for Further Information.
								 				</font>
											</td>
										</tr>
									</table>
								<%	}
								if(referral.length()>0) {%>
									<table align="center" cellpadding="0" cellspacing="0">
										<tr>
											<td height="32" align="center" valign="middle">
								 				<font color="red" size="3">	
								 					This Quote is referral<br><%=referral %>
								 				</font>
											</td>
										</tr>
									</table> 
								<%}%>
								<input type="hidden" name="referral" value="<%=referral%>"/>
<script type="text/javascript">

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
function SubmitBack(qno,ed,req,appNo,bodyid){
	document.motor.QuoteNo.value = qno;
	document.motor.entryDate.value = ed;
	document.motor.applicationNo.value=appNo;
	document.motor.bodyid.value=bodyid;
	document.motor.linkFrom.value='edit';
	document.motor.action="../servlet/motorQuote";	
	document.motor.submit();
}

function sendMail(appNo,typeId){
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
		alert("Please Select Policy Coverage");
		return false;
	}
	document.motor.applicationNo.value=appNo;
    document.motor.action="../servlet/mailmotorquote";
	document.motor.submit();
	return false;
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
			<%if(!(referral.length()>0)){ %>
				var val = document.getElementById("total"+typeId).value;
				val= val==""?0:val;
				var tot = parseFloat(val) - parseFloat(premium);
				document.getElementById("total"+typeId).value = roundNumber(tot,2);				
			<%}else{%> 
				document.getElementById("total"+typeId).value = 'Referral';
			<%}%>
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
			<%if(!(referral.length()>0)){ %>
				var val = document.getElementById("total"+typeId).value;
				val= val==""?0:val;
				var tot = parseFloat(val) + parseFloat(premium);
				document.getElementById("total"+typeId).value = roundNumber(tot,2);
			<%}else{%> 
				document.getElementById("total"+typeId).value = 'Referral';
			<%}%>
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
			<%if(!(referral.length()>0)){ %>
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
			<%}else{%> 
				document.getElementById("total"+typeId).value = 'Referral';
			<%}%>
			document.getElementById("totaldisplay"+typeId).innerHTML = document.getElementById("total"+typeId).value;
		}		
	}else{
		var typeId = document.motor.policyType.value;
		var obj =  document.getElementsByName("default"+typeId);
		<%if(!(referral.length()>0)){ %>
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
		<%}else{%> 
			document.getElementById("total"+typeId).value = 'Referral';
		<%}%>
		document.getElementById("totaldisplay"+typeId).innerHTML = document.getElementById("total"+typeId).value;
	}
	}
}

/*function calculatePremium(val){
	var startRate = document.getElementById("startRate"+val).value;
	var endRate = document.getElementById("endRate"+val).value;
	var rate = document.getElementById("coverRate"+val).value;
	var baseRate = document.getElementById("coverBaseRate"+val).value;
	var sumIns = document.getElementById("sumInsured").value;
	var finalTotal = document.getElementById("total"+val).value;
	var minpremium = document.getElementById("minpremium"+val).value;
	var previousPrem = document.getElementById("coverDataValue"+val).value;
	startRate = startRate==""?0:startRate;
	endRate = endRate==""?0:endRate;
	rate = rate==""?0:rate;
	baseRate = baseRate==""?0:baseRate;
	sumIns = sumIns==""?0:sumIns;
	finalTotal = finalTotal==""?0:finalTotal;
	previousPrem = previousPrem==""?0:previousPrem;
	if(parseFloat(rate)>=parseFloat(startRate) && parseFloat(rate)<=parseFloat(endRate)){
		var tot = 0.0;
		if(val==2){
			tot = parseFloat(rate);
		}else{
			tot = parseFloat(sumIns) * parseFloat(rate);
		}
		if(parseFloat(tot)<parseFloat(minpremium))
			tot = parseFloat(minpremium);
		document.getElementById("coverValue"+val).innerHTML = "<font color='white'>"+roundNumber(tot,2)+"</font>";
		var result = (parseFloat(finalTotal) - parseFloat(previousPrem))+parseFloat(tot);
		document.getElementById("total"+val).value = roundNumber(result,2);
		document.getElementById("coverDataValue"+val).value = roundNumber(tot,2);
		document.getElementById("totaldisplay"+val).innerHTML = document.getElementById("total"+val).value;
		return true;		
	}else{
		alert("Rate must be in between "+startRate+" and "+endRate);
		var tot = parseFloat(sumIns) * parseFloat(baseRate);
		if(parseFloat(tot)<parseFloat(minpremium))
			tot = parseFloat(minpremium);
		document.getElementById("coverValue"+val).innerHTML = "<font color='white'>"+roundNumber(tot,2)+"</font>";
		document.getElementById("coverRate"+val).value = baseRate;
		var result = (parseFloat(finalTotal) - parseFloat(previousPrem))+parseFloat(tot);
		document.getElementById("total"+val).value = roundNumber(result,2);
		document.getElementById("coverDataValue"+val).value = roundNumber(tot,2);
		document.getElementById("totaldisplay"+val).innerHTML = document.getElementById("total"+val).value;
		return false;
	}
}

function calnewPremium(val){
	var startRate = document.getElementById("startRate"+val).value;
	var endRate = document.getElementById("endRate"+val).value;
	var rate = document.getElementById("coverRate"+val).value;
	var baseRate = document.getElementById("coverBaseRate"+val).value;
	var sumIns = document.getElementById("sumInsured").value;
	var finalTotal = document.getElementById("total"+val).value;
	var minpremium = document.getElementById("minpremium"+val).value;
	var previousPrem = document.getElementById("coverDataValue"+val).value;
	startRate = startRate==""?0:startRate;
	endRate = endRate==""?0:endRate;
	rate = rate==""?0:rate;
	baseRate = baseRate==""?0:baseRate;
	sumIns = sumIns==""?0:sumIns;
	finalTotal = finalTotal==""?0:finalTotal;
	previousPrem = previousPrem==""?0:previousPrem;
	if(parseFloat(rate)>=parseFloat(startRate) && parseFloat(rate)<=parseFloat(endRate)){
		var tot = 0.0;
		if(val==2){
			tot = parseFloat(rate);
		}else{
			tot = parseFloat(sumIns) * parseFloat(rate);
		}
		if(parseFloat(tot)<parseFloat(minpremium))
			tot = parseFloat(minpremium);
		document.getElementById("coverValue"+val).innerHTML = "<font color='white'>"+roundNumber(tot,2)+"</font>";
		var result = (parseFloat(finalTotal) - parseFloat(previousPrem))+parseFloat(tot);
		document.getElementById("total"+val).value = roundNumber(result,2);
		document.getElementById("coverDataValue"+val).value = roundNumber(tot,2);
		document.getElementById("totaldisplay"+val).innerHTML = document.getElementById("total"+val).value;
		return true;		
	}else{
		alert("Rate must be in between "+startRate+" and "+endRate);
		var tot = parseFloat(sumIns) * parseFloat(baseRate);
		if(parseFloat(tot)<parseFloat(minpremium))
			tot = parseFloat(minpremium);
		document.getElementById("coverValue"+val).innerHTML = "<font color='white'>"+roundNumber(tot,2)+"</font>";
		document.getElementById("coverRate"+val).value = baseRate;
		var result = (parseFloat(finalTotal) - parseFloat(previousPrem))+parseFloat(tot);
		document.getElementById("total"+val).value = roundNumber(result,2);
		document.getElementById("coverDataValue"+val).value = roundNumber(tot,2);
		document.getElementById("totaldisplay"+val).innerHTML = document.getElementById("total"+val).value;
		return false;
	}
}*/
</script>

<% 	
	if(onloadCal!=null && onloadCal.size()>0){
		for(int loop=0;loop<onloadCal.size();loop++){
			String id = (String)onloadCal.get(loop);
			if(id!=null && id.trim().length()>0){
%>
<script>
	calnewPremium('<%=id %>');
</script>
<%				
			}
		}
	}
%>							
								
								<%
								}catch(Exception e){
								e.printStackTrace();
								} %>
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
		                      </td>
		                    </tr>
		                    <tr>
		                    <td align="center">
		                    	<s:submit type="button" name="close"  key="Back" cssClass="btn" onclick="getBack('newQuote')"/>
		                    </td>
		                    </tr>
	                    </table>
	                </td>
                </tr>
           </table></s:form>
        </s:elseif>
        <s:elseif test='"editCovRate".equalsIgnoreCase(display)'>
        <s:form id="motor" name="motor" method="post" theme="simple" action="updateCovRateMotor.action">
         <table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tr>
	          <td  style="color:red;"><s:actionerror/></td>
	        </tr>
              <tr>
                <td bgcolor="#FFFFFF">
                  <table width="100%" border="0" cellspacing="0" cellpadding="0">
                    <tr>
                      <td class="heading"><s:text name="motor.policyInfo" /></td>
                    </tr>
                     <tr>
                      <td class="bg">
	                      <table width="100%" border="0" align="center" cellpadding="4" cellspacing="0">
	                          <tr>
	                            <td width="2%">&nbsp;</td>
	                            <td width="12%">
	                            	<b><s:text name="motor.quoteNo"  /></b>
	                            </td>
	                            <td width="19%">
	                               <s:property value="quoteNo"/>
	                            </td>
	                            <td width="12%">
	                            	<b><s:text name="motor.quoteDate" /></b>
	                            </td>
	                            <td width="19%">
	                               	<s:property value="quoteDate"/>
	                            </td>
	                            <td width="12%">
	                            	<b><s:text name="motor.product" /></b>
								</td>
	                            <td width="19%">
	                              <s:property value="product"/>
	                            </td>
	                            <td width="2%">&nbsp;</td>
	                          </tr>
	                          <tr>
	                            <td width="2%">&nbsp;</td>
	                            <td>
	                            	<b><s:text name="motor.make"  /></b>
								</td>
	                            <td>
	                               <s:property value="makeName"/>
	                            </td>
	                            <td>
	                            	<b><s:text name="motor.model" /></b>
								</td>
	                            <td>
	                               	<s:property value="modelName"/>
	                            </td>
	                            <td>
	                            	<b><s:text name="motor.typeOfBody" /></b>
								</td>
	                            <td>
	                              <s:property value="typeBodyName"/>
	                            </td>
	                            <td width="2%">&nbsp;</td>
	                          </tr>
	                          <tr>
	                            <td width="2%">&nbsp;</td>
	                            <td>
	                            	<b><s:text name="motor.sumInsured"  /></b>
								</td>
	                            <td>
	                               <s:property value="sumInsured"/>
	                            </td>
	                            <td>
	                            	<b><s:text name="motor.vehicleUsage" /></b>
								</td>
	                            <td>
	                               	<s:property value="vehicleUsageName"/>
	                            </td>
	                            <td>
	                            	<b><s:text name="motor.agencyRepair" /></b>
								</td>
	                            <td>
	                              	<s:property value="agencyRepairName"/>
	                            </td>
	                            <td width="2%">&nbsp;</td>
	                          </tr>
	                          <tr>
	                            <td width="2%">&nbsp;</td>
	                            <td>
	                            	<b><s:text name="motor.customerName"  /></b>
								</td>
	                            <td>
	                               <s:property value="customerName"/>
	                            </td>
	                            <td>
	                            	<b><s:text name="motor.email" /></b>
								</td>
	                            <td>
	                               	<s:property value="email"/>
	                            </td>
	                            <td>
	                            	<b><s:text name="motor.policyStartDt" /></b>
								</td>
	                            <td>
	                              	<s:property value="policyStartDate"/>
	                            </td>
	                            <td width="2%">&nbsp;</td>
	                          </tr>
	                        </table>
                         </td>
                        </tr>
                     </table>
                     </td>
                     </tr>
                     <s:if test="premiumInfo.size()>0">
                      <tr>
                		<td class="heading"><s:text name="motor.premiumInfo"/></td>
                	  </tr>
                      <tr>
                      <td>
	                    <table width="100%" border="0" align="center" cellpadding="4" cellspacing="0">
	                    <s:set name="groupId" value=""/>
	                    <s:set var="preAmt" value="0.0" scope="page"/>
	                    <s:iterator value="premiumInfo" var="prInfo" status="stat">
	                    <s:if test="%{#groupId != #prInfo.GROUP_ID}">
	                    <s:set name="groupId" value="%{#prInfo.GROUP_ID}"/>
	                    <tr class="bg">
	                            <td width="2%">&nbsp;</td>
	                            <td colspan="5" style="font-size:13px"><b><u><s:property  value="%{#prInfo.GROUP_DESC_ENGLISH}"/></u></b></td>
	                            <td width="2%">&nbsp;</td>
	                     </tr>
	                     </s:if>
	                     <tr>
	                            <td width="2%">&nbsp;</td>
	                            <td align="center" width="10%">
	                            <s:hidden name="coverId[%{#stat.count-1}]" value="%{#prInfo.POLICYTYPE_COVERID}"/>
	                            </td>
	                            <td align="left" width="20%">
	                            	<s:property  value="%{#prInfo.OPCOVER_DESC_ENGLISH}"/>
	                            </td>
	                            <td align="left" width="15%">
	                            	<s:property value="getText('{0,number,#,##0.00}',{#prInfo.SUM_INSURED})"/>
	                             <s:hidden name="sI[%{#stat.count-1}]"  cssClass="input" value="%{#prInfo.SUM_INSURED}" cssStyle="text-align:right;"/>
	                            </td>
	                            <td align="left" width="15%">
	                                <s:textfield name="baseRate[%{#stat.count-1}]" cssClass="input" value="%{#prInfo.RATE}" size="11" cssStyle="text-align:right;" maxLength="16" onkeyup="checkDecimals(this);" />
	                            </td>
	                            <td align="right" width="15%"> 
	                            	<s:property value="getText('{0,number,#,##0.00}',{#prInfo.PREMIUM})"/>
	                            	<s:set var="preAmt" value="%{@java.lang.Double@parseDouble(#attr.preAmt)+@java.lang.Double@parseDouble(#prInfo.PREMIUM)}" scope="page"/>
	                            </td>
	                            <td width="2%">&nbsp;</td>
	                     </tr>
	                     </s:iterator>
	                      </table>
                		</td>
                	</tr>
                </s:if>
                <tr>
		                      <td><table width="100%" border="0" align="center" cellpadding="4" cellspacing="0">
		                          <tr>
		                            <td height="5" colspan=""></td>
		                          </tr>
		                          <tr>
			                            <td>&nbsp;</td>
			                            <td colspan="3" align="center">
			                            <input type="button" name="Submit1" class="btn" value="Back" onclick="getBack('newQuote')"/>
			                            <input type="button" name="Submit2" class="btn" value="Calculate" onclick="this.form.actionType.value='getCalculate';this.form.submit();"/>
										<input type="button" name="Submit3" class="btn" value="Proceed" onclick="this.form.actionType.value='getQuote';this.form.submit();"/>
										<s:hidden name="applicationNo"/>
								 		<s:hidden name="quoteNo"/>
								 		<s:hidden name="quoteStatus"/>
								 		<s:hidden name="referralMsg"/>
								 		<s:hidden name="actionType" />
			                            </td>
			                            <td>&nbsp;</td>
		                          </tr>
		                      </table>
		                     </td>
		               </tr>
		               </table>
		     </s:form>
        </s:elseif>
        <s:elseif test="'policyInfo'.equalsIgnoreCase(display)">
        <s:form id="motor" name="motor" method="post" theme="simple" action="getGeratePolicyMotor.action">
         <table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tr>
	          <td  style="color:red;"><s:actionerror/></td>
	        </tr>
              <tr>
                <td bgcolor="#FFFFFF">
                  <table width="100%" border="0" cellspacing="0" cellpadding="0">
                    <tr>
                      <td class="heading"><s:text name="motor.policyInfo" /></td>
                    </tr>
                     <tr>
                      <td class="bg">
	                      <table width="100%" border="0" align="center" cellpadding="4" cellspacing="0">
	                          <tr>
	                            <td width="2%">&nbsp;</td>
	                            <td width="12%">
	                            	<b><s:text name="motor.quoteNo"  /></b>
	                            </td>
	                            <td width="19%">
	                               <s:property value="quoteNo"/>
	                            </td>
	                            <td width="12%">
	                            	<b><s:text name="motor.quoteDate" /></b>
	                            </td>
	                            <td width="19%">
	                               	<s:property value="quoteDate"/>
	                            </td>
	                            <td width="12%">
	                            	<b><s:text name="motor.product" /></b>
								</td>
	                            <td width="19%">
	                              <s:property value="product"/>
	                            </td>
	                            <td width="2%">&nbsp;</td>
	                          </tr>
	                          <tr>
	                            <td width="2%">&nbsp;</td>
	                            <td>
	                            	<b><s:text name="motor.make"  /></b>
								</td>
	                            <td>
	                               <s:property value="makeName"/>
	                            </td>
	                            <td>
	                            	<b><s:text name="motor.model" /></b>
								</td>
	                            <td>
	                               	<s:property value="modelName"/>
	                            </td>
	                            <td>
	                            	<b><s:text name="motor.typeOfBody" /></b>
								</td>
	                            <td>
	                              <s:property value="typeBodyName"/>
	                            </td>
	                            <td width="2%">&nbsp;</td>
	                          </tr>
	                          <tr>
	                            <td width="2%">&nbsp;</td>
	                            <td>
	                            	<b><s:text name="motor.sumInsured"  /></b>
								</td>
	                            <td>
	                               <s:property value="sumInsured"/>
	                            </td>
	                            <td>
	                            	<b><s:text name="motor.vehicleUsage" /></b>
								</td>
	                            <td>
	                               	<s:property value="vehicleUsageName"/>
	                            </td>
	                            <td>
	                            	<b><s:text name="motor.agencyRepair" /></b>
								</td>
	                            <td>
	                              	<s:property value="agencyRepairName"/>
	                            </td>
	                            <td width="2%">&nbsp;</td>
	                          </tr>
	                          <tr>
	                            <td width="2%">&nbsp;</td>
	                            <td>
	                            	<b><s:text name="motor.customerName"  /></b>
								</td>
	                            <td>
	                               <s:property value="customerName"/>
	                            </td>
	                            <td>
	                            	<b><s:text name="motor.email" /></b>
								</td>
	                            <td>
	                               	<s:property value="email"/>
	                            </td>
	                            <td>
	                            	<%--<b><s:text name="motor.policyStartDt" /><s:text name="motor.policyEndDt" /></b> --%>
								</td>
	                            <td>
	                              	<%--<s:property value="policyStartDate"/><s:textfield name="policyEndDate" id="policyEndDate" cssClass="input" readonly="true"/> --%>
	                            </td>
	                            <td width="2%">&nbsp;</td>
	                          </tr>
	                        </table>
                         </td>
                        </tr>
                     </table>
                     </td>
                     </tr>
                     <s:if test="premiumInfo.size()>0">
                      <tr>
                		<td class="heading"><s:text name="motor.premiumInfo"/></td>
                	  </tr>
                      <tr>
                      <td>
	                    <table width="100%" border="0" align="center" cellpadding="4" cellspacing="0">
	                    <s:set name="groupId" value=""/>
	                    <s:set var="preAmt" value="0.0" scope="page"/>
	                    <s:iterator value="premiumInfo" var="prInfo" status="stat">
	                    <s:if test="%{#groupId != #prInfo.GROUP_ID}">
	                    <s:set name="groupId" value="%{#prInfo.GROUP_ID}"/>
	                    <tr class="bg">
	                            <td width="2%">&nbsp;</td>
	                            <td colspan="3" style="font-size:13px"><b><u><s:property  value="%{#prInfo.GROUP_DESC_ENGLISH}"/></u></b></td>
	                            <td width="2%">&nbsp;</td>
	                     </tr>
	                     </s:if>
	                     <tr>
	                            <td width="2%">&nbsp;</td>
	                            <td align="center" width="25%"></td>
	                            <td align="left" width="25%"><s:property  value="%{#prInfo.OPCOVER_DESC_ENGLISH}"/>
	                            </td>
	                            <td align="right" width="25%"> 
	                            	<s:property value="getText('{0,number,#,##0.00}',{#prInfo.PREMIUM})"/>
	                            	<s:set var="preAmt" value="%{@java.lang.Double@parseDouble(#attr.preAmt)+@java.lang.Double@parseDouble(#prInfo.PREMIUM)}" scope="page"/>
	                            </td>
	                            <td width="2%">&nbsp;</td>
	                     </tr>
	                     </s:iterator>
	                      </table>
                		</td>
                	</tr>
                </s:if>
                <tr>
                <td class="bg">
	                  <table width="100%" border="0" align="center" cellpadding="4" cellspacing="0">
	                      <tr>
	                            <td width="2%">&nbsp;</td>
	                            <td width="25%"></td>
	                            <td width="25%"><s:text name="motor.Premium"/></td>
	                            <td width="25%" align="right"><b>[<s:property value="#session.BrokerDetails.CurrencyAbb"/>] </b>&nbsp;&nbsp;&nbsp;<s:textfield name="premium" id="premium" cssClass="input" value="%{#attr.preAmt}" onkeyup="checkDecimals(this);" maxlength="11" cssStyle="text-align:right;" readonly="true"/></td>
	                            <td width="2%">&nbsp;</td>
	                       </tr>
	                       <s:if test='#session.user1 == "admin" || quoteStatus == "RA"'>
	                       <tr>
		                            <td width="2%">&nbsp;</td>
		                            <td width="25%">&nbsp;</td>
		                            <td width="25%"><s:label key="motor.loadingOrDiscountPremium"/>&nbsp;&nbsp;&nbsp;<s:select list="#{'+':'+','-':'-'}" name="sign" onchange="getTotalPremium(this.form)" disabled="%{quoteStatus=='RA' && #session.user1 != 'admin'?'true':'false'}"/>
		                            </td>
		                            <td width="25%" align="right"><b>[<s:property value="#session.BrokerDetails.CurrencyAbb"/>]&nbsp;&nbsp;&nbsp;</b>
		                            <s:textfield name="loadOrDiscPremium" id="loadOrDiscPremium" cssClass="input" readonly="%{quoteStatus=='RA' && #session.user1 != 'admin'?'true':'false'}" onkeyup="checkDecimals(this);" maxlength="11" cssStyle="text-align:right;" onchange="getTotalPremium(this.form)"/>
		                            <s:if test='"+".equalsIgnoreCase(sign)'>
		                            	<s:set var="preAmt" value="%{@java.lang.Double@parseDouble(#attr.preAmt)+@java.lang.Double@parseDouble(loadOrDiscPremium)}" scope="page"/>
		                            </s:if>
		                            <s:else>
		                            	<s:set var="preAmt" value="%{@java.lang.Double@parseDouble(#attr.preAmt)-@java.lang.Double@parseDouble(loadOrDiscPremium)}" scope="page"/>
		                            </s:else>
		                            </td>
		                            <td width="2%">&nbsp;</td>
		                 		</tr>
	                       </s:if>
	                       <tr>
	                            <td width="2%">&nbsp;</td>
	                            <td width="25%"></td>
	                            <td width="25%"><s:text name="motor.policyFee"/></td>
	                            <td width="25%" align="right"><b>[<s:property value="#session.BrokerDetails.CurrencyAbb"/>] &nbsp;&nbsp;&nbsp;</b><s:textfield name="policyFee" id="policyFee" cssClass="input" onkeyup="checkDecimals(this);" maxlength="11" cssStyle="text-align:right;" readonly="true"/></td>
	                            <td width="2%">&nbsp;</td>
	                       </tr>
	                       <tr>
	                            <td width="2%">&nbsp;</td>
	                            <td width="25%"></td>
	                            <td width="25%"><s:text name="motor.totalPremiumPayable"/></td>
	                            <td width="25%" align="right"><b>[<s:property value="#session.BrokerDetails.CurrencyAbb"/>] &nbsp;&nbsp;&nbsp;</b><s:textfield name="totalPremium" id="totalPremium" cssClass="input" value="%{@java.lang.Double@parseDouble(#attr.preAmt)+@java.lang.Double@parseDouble(policyFee)}" onkeyup="checkDecimals(this);" maxlength="11" cssStyle="text-align:right;" readonly="true"/></td>
	                            <td width="2%">&nbsp;</td>
	                      </tr>
	                      <s:if test='#session.user1 == "admin" || (!"".equals(adminRemarks)&&(adminRemarks!=null)&& #session.user1 != "admin")'>
		                        <tr>
		                            <td width="2%">&nbsp;</td>
		                            <td width="25%">
		                            	<s:label key="motor.specialCondition"/>
		                            </td>
		                            <td colspan="2" width="50%">
		                            <s:textarea name="adminRemarks" onkeyup="textLimit(this,'470')"  cols="50" rows="3" readonly="%{quoteStatus=='RA' && #session.user1 != 'admin'?'true':'false'}"/>
		                            </td>
		                            <td width="2%">&nbsp;</td>
		                 		</tr>
		                 		</s:if>
		                 		<s:if test='#session.user1 == "admin"'>
		                         <tr>
		                            <td width="2%">&nbsp;</td>
		                            <td width="25%"><s:label key="motor.referralStatus"/>
		                            </td>
		                            <td width="25%">
		                           		<s:radio list="#{'Y':'Accept','N':'Reject','A':'Pending'}" name="adminRefStatus" value="%{adminRefStatus==null?'A':adminRefStatus}" />
		                            </td>
		                             <td width="25%">&nbsp;</td>
		                            <td width="2%">&nbsp;</td>
		                 		</tr>
		                 		</s:if>
	                 </table>
                </td>
                </tr>
                  <s:if test='#session.user1 != "admin"'>
		                 <s:if test='showReferralYN == "Y"'>
		                 <s:hidden name="showReferralYN"/>
		                 <tr>
		                      <td class="heading"><s:label key="motor.referalInfo"/></td>
		                 </tr>
		                 <tr>
		                      <td class="bg">
		                      	<table width="100%" border="0" align="center" cellpadding="4" cellspacing="0">
  	                               <tr>
		                            	<td width="35%"><s:label key="motor.policyStartDt"/>
		                            	</td>
		                            	<td width="20%">
		                            		<s:property value="policyStartDate"/>
		                            	</td>
		                            	<td width="10%"><s:label key="motor.policyEndDt"/>
		                            	</td>
		                            	<td width="25%"> 
		                            		<s:property value="policyEndDate"/>
		                            	</td>
		                 			</tr>
		                          	<tr>
		                            	<td width="35%"><s:label key="motor.referralYN"/>
		                            	</td>
		                            	<td width="20%">
		                            	<s:radio list="#{'Y':'Yes','N':'No'}" name="referralYN" id="referralYN"  onclick="disablePolicyOption(this.value);"/>
		                            	</td>
		                            	<td width="10%"><s:label key="motor.comments"/>
		                            	</td>
		                            	<td width="25%"> 
		                            	<s:textarea name="referralComments" id="referralComments" cssClass="input" onkeyup="textLimit(this,'200')"/>
		                            	</td>
		                 			</tr>
		                 		</table>
		                 	</td>
		                 </tr>
		                 </s:if>
		                 
		                 <tr>
		                 <td>
		                 <div id="policyGeneration" >
		                 <table width="100%">
		                  <tr>
		              			<td class="heading"><s:label key="motor.paymentDetails" /></td>
		         		 </tr>
		                  <tr>
		                      <td class="bg">
			                      <table width="100%" border="0" align="center" cellpadding="4" cellspacing="0">
			                          <tr>
			                          	<td width="2%">&nbsp;</td>
			                            <td width="25%"><s:label key="motor.modeOfPayment"/></td>
			                            <td width="25%">
			                            	<s:select name="modeOfPay" list="#{'Credit Card':'Credit Card','Cash':'Cash','Cheque':'Cheque'}" headerKey="" headerValue="-Select-" cssClass="input" value="%{modeOfPay==null?'':modeOfPay}" onchange="toggleChqInvNo(this.value);"/>
			                            </td>
			                            <td width="25%">
			                           		 <div id="ChqInvNo">
				                            	<s:label key="motor.chqInvNo"/><br/>
					                            <s:textfield name="chqInvNo" id="chqInvNo" cssClass="input" maxlength="10" />
				                            </div>
			                            </td>
			                            <td width="2%">&nbsp;</td>
			                 		</tr>
			                 		</table>
		                 	</td>
		                 </tr>
		                  <tr>
				                  <td class="heading"><s:label key="motor.generatepolicy" /></td>
				          </tr>
				          <tr>
		                      <td>
		                      <table width="100%" border="0" align="center" cellpadding="4" cellspacing="0">
		                          <tr>
		                            <td width="2%">&nbsp;</td>
		                            <td width="25%"><s:label key="motor.generatePolicyYN"/>
		                            </td>
		                            <td width="25%">
										<s:radio name="generatePolicyYN" list="#{'Y':'Yes','N':'No'}" value="%{generatePolicyYN==null?'N':generatePolicyYN}" onclick="toggleEmailYN(this.value)"/>
									</td>
		                            <td width="25%">
		                            </td>
		                            <td width="2%">&nbsp;</td>
		                 		</tr>
		                 		 <tr>
		                            <td width="2%">&nbsp;</td>
		                            <td width="25%"><s:label key="motor.draftEmailYN" />
		                            </td>
		                            <td width="25%">
		                            <s:radio name="quoteEmailYN" list="#{'Y':'Yes','N':'No'}" value="%{quoteEmailYN==null?'N':quoteEmailYN}" id="quoteEmailYN"/>
		                            </td>
		                            <td width="25%">
		                            </td>
		                            <td width="2%">&nbsp;</td>
		                 		</tr>
		                 		</table>
		                 	</td>
		                 </tr>
		                 </table>
		                 </div>
		                 </td>
		                 </tr>
		                 </s:if>
		                 <tr>
		                      <td><table width="100%" border="0" align="center" cellpadding="4" cellspacing="0">
		                          <tr>
		                            <td height="5" colspan=""></td>
		                          </tr>
		                          <tr>
			                            <td>&nbsp;</td>
			                            <td colspan="3" align="center">
		                                <s:if test='#session.user1 != "admin"'>
		                                	<s:if test='quoteStatus=="RA"'>
		                                		<input type="button" name="Submit1" class="btn" value="Back" onclick="getBack('newQuote');" />
		                                	</s:if>
		                                	<s:else>
		                                		<input type="button" name="Submit1" class="btn" value="Back" onclick="getBack('showSummarry');" />
		                                	</s:else>
		                                	&nbsp; 
     										<%--<input type="button" name="Submit2" class="btn" value="Save" onclick="this.form.actionType.value='getSave';this.form.submit();" />
				                             &nbsp;--%>
			                            </s:if>
			                            <s:else>
			                            	<s:submit type="button" name="close"  key="Back" cssClass="btn" onclick="getBack('editCovRate')"/>
			                            </s:else>
										<input type="button" name="Submit3" class="btn" value="Proceed" onclick="this.form.actionType.value='getQuote';this.form.submit();"/>
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
			                            </td>
			                            <td>&nbsp;</td>
		                          </tr>
		                      </table>
		                     </td>
		               </tr>
		               </table>
            </s:form>
        </s:elseif>
        <s:elseif test="'showQuoteInfo'.equalsIgnoreCase(display)">
			 <s:form id="motor" name="motor" method="post"  action="getGeratePolicyMotor.action" theme="simple">
			 <table width="100%" border="0" cellspacing="0" cellpadding="0">
			 <tr>
					<td class="heading" width="100%">	
		     			<s:label key="motor.quoteInfo" /> 			
					</td>
			</tr>
			<tr>
			<td align="center"><div style="color:red;font-size: 15px;"><b><s:label key="motor.refInfo"/> Saved. Your Quote No is : <s:property value="quoteNo"/></b></div></td>
			</tr>
			<tr>
			<td align="center">
     			<input type="button" name="Submit" class="btn" value="Proceed" onclick="getBack('home');"/>
			</td>
			</tr>
			</table>
			</s:form>
		</s:elseif>
		<s:elseif test="'showRefInfo'.equalsIgnoreCase(display)">
			 <s:form id="motor" name="motor" method="post"  action="getGeratePolicyMotor.action"  theme="simple">
			 <table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
					<td class="heading" width="100%">	
		     			<s:label key="quotation.quoteInfo" />			
					</td>
			</tr>
			<tr>
			<td align="center"><div style="color:red;font-size: 15px;"><b><s:label key="motor.refInfo"/> <s:property value="referralMsg"/></b></div></td>
			</tr>
			<tr>
			<td align="center">
				<input type="button" name="Submit" class="btn" value="Proceed" onclick="getBack('adminHome');"/>
			</td>
			</tr>
			</table>
			</s:form>
		</s:elseif>
        </td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td>&nbsp;</td>
  </tr>
</table>
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
			 	document.getElementById('ChqInvNo').style.display='block';
			 	document.getElementById('chqInvNo').value='';
			 }   
			 else
			 {   
				document.getElementById('chqInvNo').value='';
				document.getElementById('ChqInvNo').style.display='none';			 
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
			 if(value=="Y")
			 {
				document.getElementsByName('quoteEmailYN')[0].checked=false; 
			 	document.getElementsByName("quoteEmailYN")[0].disabled=true;
			 	document.getElementsByName('quoteEmailYN')[1].checked=true; 
			 	document.getElementsByName("quoteEmailYN")[1].disabled=true;
			 }   
			 else
			 {  
			    document.getElementsByName("quoteEmailYN")[0].disabled=false;
				document.getElementsByName("quoteEmailYN")[1].disabled=false;
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
				if('RR'=='<s:property value="quoteStatus"/>')
					document.motor.action ='${pageContext.request.contextPath}/admin/HomeRejectedPolicyByDate.jsp';
				else
					document.motor.action ='${pageContext.request.contextPath}/admin/HomePendingPolicyByDate.jsp';
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
				if('RR'=='<s:property value="quoteStatus"/>')
					document.motor.action ='${pageContext.request.contextPath}/admin/HomeRejectedPolicyByDate.jsp';
				else
					document.motor.action ='${pageContext.request.contextPath}/admin/HomePendingPolicyByDate.jsp';
			}else{
				if('ET'=='<s:property value="quoteStatus"/>')
					document.motor.action ='${pageContext.request.contextPath}/initReport.action?menuType=ET&custName=<s:property value="fullName"/>';
				else
					document.motor.action ='${pageContext.request.contextPath}/editCustomer.action';
			}
		}else if(page=='quote'){
			if('admin'=='<s:property value="#session.user1"/>'){
     			if('RA'=='<s:property value="quoteStatus"/>')
					document.motor.action ='${pageContext.request.contextPath}/admin/HomeApprovedPolicyByDate.jsp';
				else
					document.motor.action ='${pageContext.request.contextPath}/initTravel.action';
			}else{
    			if('RA'=='<s:property value="quoteStatus"/>')
					document.motor.action ='${pageContext.request.contextPath}/initReport.action?menuType=RA&loginId=<s:property value="loginId"/>';
				else
					document.motor.action ='${pageContext.request.contextPath}/initTravel.action';
			}
		}else if(page=='adminHome'){
			if('Y'=='<s:property value="adminRefStatus"/>')
				document.motor.action ='${pageContext.request.contextPath}/admin/HomeApprovedPolicy.jsp';
			else if('A'=='<s:property value="adminRefStatus"/>')
				document.motor.action ='${pageContext.request.contextPath}/admin/HomePendingPolicy.jsp';
			else if('N'=='<s:property value="adminRefStatus"/>')
				document.motor.action ='${pageContext.request.contextPath}/admin/HomeRejectedPolicy.jsp';
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
</script>
</body>
</html>
