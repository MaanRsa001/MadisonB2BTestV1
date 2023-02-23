<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ page import="java.util.List,java.util.ArrayList,java.util.Map,java.util.HashMap,java.util.Collections"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html <s:if test="locale.language == 'ar'">dir="rtl"</s:if>>
	<head>
	</head>
	<body>
		<s:set var="disable1" value="%{((endTypeId!=null && endTypeId.length() > 0)||(menuType=='RA'))}"/>
		<s:set var="endtDisable" value="%{endTypeId!=null && endTypeId.length() > 0}"/>
		<div class="table0">
			<div class="tablerow">
				<div class="table1">
					<div class="tablerow">
						<span style="color:red;"><s:actionerror/></span>
					</div>
					<div class="tablerow">
						<s:include value="/pages/home/generalInfo.jsp" />
					</div>
					<div class="tablerow">
						<s:include value="/pages/home/coverPremiumInfo.jsp" />
					</div>
					<s:form id="quotation" name="quotation" method="post" theme="simple" action="getQuoteHome.action">
						<div class="tablerow">
							<div class="tablerow">
								<div class="tablerow">
									<div class="tablerow">
										<div class="panel panel-primary">
						        			<div class="panel-heading txtB">
						        				<s:text name="motor.vehicleDetails" />
						        			</div>
						        			<div class="panel-body">
						        				<div class="boxcontent">
						        					<div class="textfield33">
						        						<div class="text">
						        							<s:text name="motor.make" /><font color="red">*</font>												
														</div>
														<div class="tbox">
															<s:textfield name="make" id="make" cssClass="inputBox" disabled="#disable1"/>
															<%--
															<s:select name="make" id="make" list="makeList" listKey="CODE" listValue="CODEDESC" headerKey="" headerValue="-Select-" onchange="getAjaxModel(this.form,'?make='+this.value,'modelList')" cssClass="inputBoxS" disabled="#disable1"/>
															 --%>
														</div>
						        					</div>
						        					<div class="textfield33">
						        						<div class="text">
						        							<s:text name="motor.model" /><font color="red">*</font>												
														</div>
														<div class="tbox">
															<s:textfield name="model" id="model" cssClass="inputBox" disabled="#disable1"/>
														</div>
														<%--
														<div class="tbox" id="modelList">
															<s:select name="model" id="model" list="modelList" listKey="CODE" listValue="CODEDESC" headerKey="" headerValue="-Select-" cssClass="inputBoxS" disabled="#disable1"/>
														</div> 
														--%>
						        					</div>
						        					<div class="textfield33">
						        						<div class="text">
						        							<s:text name="Tractor Type" /><font color="red">*</font>												
														</div>
														<div class="tbox">
															<s:select name="typeBody" id="typeBody" list="typeBodyList" listKey="CODE" listValue="CODEDESC" headerKey="" headerValue="-Select-" cssClass="inputBoxS" onchange="getAjaxModel(this.form,'?typeBody='+this.value,'noOfCylinderList')" disabled="#disable1"/>
														</div>
						        					</div>
						        					<div class="textfield33">
						        						<div class="text">
						        							<s:text name="Manufacture Date" /><font color="red">*</font>												
														</div>
														<div class="tbox">
															<s:textfield id="manufactureDate" name="manufactureDate" cssClass="inputBox datepicker" readonly="true" disabled="#disable" />
															<%--
															<s:select name="mfgYr"  id="mfgYr" list="mfgYrMap" headerKey="" headerValue="-Select-" cssClass="inputBoxS" disabled="#disable1"/>
															 --%>
														</div>
						        					</div>
						        					<div class="textfield33">
						        						<div class="text">
						        							<s:text name="Tractor IDV" /><font color="red">*</font>												
														</div>
														<div class="tbox">
															<s:textfield name="sumInsured" id="sumInsured" cssStyle="width:85%; float: left;" cssClass="inputBox" maxLength="13" onkeyup="checkDecimals(this);" disabled="#disable1"/>&nbsp;<span style="float:left;"><s:property value="#session.BrokerDetails.CurrencyAbb"/></span>
														</div>
						        					</div>
						        					<div class="textfield33" style="display: none;">
						        						<div class="text">
						        							<s:text name="Cover for Motor Lamps" /><font color="red">*</font>												
														</div>
														<div class="tbox">
															<s:radio name="motorLampsYN" list="#{'Y':'Yes','N':'No'}" value='%{motorLampsYN==null||"".equals(motorLampsYN)?"N":motorLampsYN}' disabled="#disable1"/>
														</div>
						        					</div>
						        					<div class="textfield33" style="display: none;">
						        						<div class="text">
						        							<s:text name="motor.cubicCapacity" />											
														</div>
														<div class="tbox">
															<s:textfield name="cubicCapacity" id="cubicCapacity" cssClass="inputBox" maxLength="13" onkeyup="checkDecimals(this);" disabled="#disable1"/>
														</div>
						        					</div>
						        					<div class="textfield33" style="display: none;">
						        						<div class="text">
						        							<s:text name="motor.noOfCylinder" /><font color="red">*</font>												
														</div>
														<div class="tbox" id="noOfCylinderList">
															<s:select name="noOfCylinder" id="noOfCylinder" list="noOfCylinderList" listKey="CODE" listValue="CODEDESC" headerKey="" headerValue="-Select-" cssClass="inputBox" disabled="#disable1"/>
														</div>
						        					</div>
						        					<div class="textfield33">
						        						<div class="text">
						        							<s:text name="RTO" /><font color="red">*</font>												
														</div>
														<div class="tbox">
															<s:select name="areaCoverage" list="areaCoverageList" listKey="CODE" listValue="CODEDESC" headerKey="" headerValue="-Select-" cssClass="inputBoxS" disabled="#disable1" />
														</div>
						        					</div>
						        					<div class="textfield33">
						        						<div class="text">
						        							<s:text name="Policy Type" /><font color="red">*</font>												
														</div>
														<div class="tbox">
															<s:select name="motorPolicyType" list="MotorPolicyTypeList" listKey="CODE" listValue="CODEDESC" headerKey="" headerValue="-Select-" cssClass="inputBoxS" disabled="#disable1" />
														</div>
						        					</div>
						        					<div class="textfield33">
						        						<div class="text">
						        							<s:text name="Restricted TPPD" /><font color="red">*</font>												
														</div>
														<div class="tbox">
															<s:radio name="restrictedTPPDYN" list="#{'Y':'Yes','N':'No'}" value='%{restrictedTPPDYN==null||"".equals(restrictedTPPDYN)?"N":restrictedTPPDYN}' disabled="#disable1"/>
														</div>
						        					</div>
						        					<div class="textfield33">
						        						<div class="text">
						        							<s:text name="No Of Trailer" /><font color="red">*</font>												
														</div>
														<div class="tbox">
															<s:select name="noOfTrailer" id="noOfTrailer" list="#{'1':'1','2':'2'}" headerKey="" headerValue="-None-" cssClass="inputBoxS" disabled="#disable1" onchange="toggleTrailerDetails();"/>
														</div>
						        					</div>
						        					<div id="trailer1Div">
							        					<div class="textfield33">
							        						<div class="text">
							        							<s:text name="Trailer 1 IDV" /><font color="red">*</font>												
															</div>
															<div class="tbox">
																<s:textfield name="trailerIdv1" id="trailerIdv1" cssClass="inputBox" maxLength="13" onkeyup="checkDecimals(this);" disabled="#disable1"/>
															</div>
							        					</div>
							        					<div class="textfield33">
							        						<div class="text">
							        							<s:text name="Trailer 1 Date of Manufacture" /><font color="red">*</font>												
															</div>
															<div class="tbox">
																<s:textfield id="trailer1MfrDate" name="trailer1MfrDate" cssClass="inputBox datepicker" readonly="true" disabled="#disable" />
															</div>
							        					</div>
						        					</div>
						        					<div id="trailer2Div">
							        					<div class="textfield33">
							        						<div class="text">
							        							<s:text name="Trailer 2 IDV" /><font color="red">*</font>												
															</div>
															<div class="tbox">
																<s:textfield name="trailerIdv2" id="trailerIdv2" cssClass="inputBox" maxLength="13" onkeyup="checkDecimals(this);" disabled="#disable1"/>
															</div>
							        					</div>
							        					<div class="textfield33">
							        						<div class="text">
							        							<s:text name="Trailer 2 Date of Manufacture" /><font color="red">*</font>												
															</div>
															<div class="tbox">
																<s:textfield id="trailer2MfrDate" name="trailer2MfrDate" cssClass="inputBox datepicker" readonly="true" disabled="#disable" />
															</div>
							        					</div>
							        				</div>
						        					<div class="textfield33">
						        						<div class="text">
						        							<s:text name="motor.seatingCapacity" /><font color="red">*</font>												
														</div>
														<div class="tbox">
															<s:textfield name="seatingCapacity" id="seatingCapacity" size="7" cssClass="input" maxLength="5" onkeyup="checkNumbers(this);" disabled="#disable1"/>&nbsp;&nbsp;<s:text name="motor.includingDriver"/>
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
						        							<s:text name="motor.VoluntaryDeductible" /><font color="red">*</font>												
														</div>
														<div class="tbox">
															<s:select name="voluntaryDeductible"  id="voluntaryDeductible" list="voluntaryDeductibleList" listKey="CODE" listValue="CODEDESC" headerKey="" headerValue="-Select-" cssClass="inputBoxS" disabled="#disable1"/>
														</div>
						        					</div>
						        					<div class="textfield33">
						        						<div class="text">
						        							<s:text name="motor.electricalAcc" />
														</div>
														<div class="tbox">
															<s:textfield name="electricalAcc" id="electricalAcc" cssClass="inputBox" maxLength="13" onkeyup="checkDecimals(this);" disabled="#disable1"/>
														</div>
						        					</div>
						        					<div class="textfield33">
						        						<div class="text">
						        							<s:text name="motor.nonElectricalAcc" />
														</div>
														<div class="tbox">
															<s:textfield name="nonElectricalAcc" id="nonElectricalAcc" cssClass="inputBox" maxLength="13" onkeyup="checkDecimals(this);" disabled="#disable1"/>
														</div>
						        					</div>
						        					<div class="textfield33">
						        						<div class="text">
						        							<s:text name="motor.bifuelKit" />
														</div>
														<div class="tbox">
															<s:textfield name="bifuelKit" id="bifuelKit" cssClass="inputBox" maxLength="13" onkeyup="checkDecimals(this);" disabled="#disable1"/>
														</div>
						        					</div>
						        					<%--
						        					<div class="textfield33" style="display: none;">
						        						<div class="text">
						        							<s:text name="motor.agencyRepair" /><font color="red">*</font>												
														</div>
														<div class="tbox">
															<s:radio name="agencyRepairYNIdList[0]" id="agencyRepairYN" list="#{'Y':'Yes','N':'No'}" value="%{agencyRepairYN==null?'N':agencyRepairYN}" cssClass="input" disabled="#disable1"/>
														</div>
						        					</div>        					
						        					--%>
						        					<s:hidden name="agencyRepairYN" value="N"/>
						        					<br class="clear"/>
						        				</div>
						        			</div>
						        		</div>
									</div>
									<div class="tablerow">
										<div class="panel panel-primary">
						        			<div class="panel-heading txtB">
						        				<s:text name="motor.additionalVehicleDetails" />
						        			</div>
						        			<div class="panel-body">
						        				<div class="textfield33">
					        						<div class="text">
					        							<s:text name="motor.regNo" /><font color="red">*</font>												
													</div>
													<div class="tbox">
														<table width="90%">
															<tr>
																<td width="20%"><s:textfield name="regNo1" id="regNo1" maxlength="2" cssClass="inputBox" disabled="#disable1"/></td>
																<td width="5%">-</td>
																<td width="20%"><s:textfield name="regNo2" id="regNo2" maxlength="2" cssClass="inputBox" disabled="#disable1" onkeyup="checkNumbers(this);"/></td>
																<td width="5%">-</td>
																<td width="20%"><s:textfield name="regNo3" id="regNo3" maxlength="2" cssClass="inputBox" disabled="#disable1"/></td>
																<td width="5%">-</td>
																<td width="20%"><s:textfield name="regNo4" id="regNo4" maxlength="4" cssClass="inputBox" disabled="#disable1" onkeyup="checkNumbers(this);"/></td>
															</tr>
														</table>
													</div>
					        					</div>
					        					<div class="textfield33">
					        						<div class="text">
					        							<s:text name="motor.chassisNo" /><font color="red">*</font>												
													</div>
													<div class="tbox">
														<s:textfield name="chassisNo" id="chassisNo" cssClass="inputBox" maxLength="20" disabled="#disable1"/>
													</div>
					        					</div>
					        					<div class="textfield33">
					        						<div class="text">
					        							<s:text name="motor.engineNo" /><font color="red">*</font>											
													</div>
													<div class="tbox">
														<s:textfield name="engineNo" id="engineNo" cssClass="inputBox" maxLength="20" disabled="#disable1"/>
													</div>
					        					</div>
					        					<div class="textfield33" style="display: none;">
					        						<div class="text">
					        							<s:text name="motor.vehicleColour" />												
													</div>
													<div class="tbox">
														<s:select name="vehicleColour" id="vehicleColour" list="vehicleColourList" listKey="CODE" listValue="CODEDESC" headerKey="" headerValue="-Select-" cssClass="inputBoxS" disabled="#disable1"/>								
													</div>
					        					</div>
					        					<div class="textfield33" style="display: none;">
					        						<div class="text">
					        							<s:text name="motor.vehicleRegLoc" /><font color="red">*</font>												
													</div>
													<div class="tbox">
														<s:select name="vehicleRegLoc" id="vehicleRegLoc" list="cityList" listKey="CODE" listValue="CODEDESC" headerKey="" headerValue="-Select-" cssClass="inputBoxS" disabled="#disable1"/>								
													</div>
					        					</div>
					        					<div class="textfield33" style="display: none;">
					        						<div class="text">
					        							<s:text name="motor.plateCharacter" />												
													</div>
													<div class="tbox">
														<%--
														<s:select name="plateCharacterIdList[0]" id="plateCharacterIdList[0]" list="plateCharList" listKey="CODE" listValue="CODEDESC" headerKey="" headerValue="-Select-" cssClass="inputBoxS" disabled="#disable1"/>
														--%>
														<s:textfield name="plateCharacter" id="plateCharacter" cssClass="inputBox" disabled="#disable1" maxLength="5" onkeypress="return IsNumeric(event);"/>
													</div>
												</div>
					        					<div class="textfield33">
					        						<div class="text">
					        							<s:text name="motor.leasedYN" /><font color="red">*</font>												
													</div>
													<div class="tbox">
														<s:radio name="leasedYN" list="#{'Y':'Yes','N':'No'}" value='%{leasedYN==null||"".equals(leasedYN)?"N":leasedYN}' onclick="toggleLeasedYN(this.value);" disabled="#disable1"/>								
													</div>
					        					</div>
					        					<div class="textfield33">
					        						<div class="text">
					        							<s:text name="L&T Finance" /><font color="red">*</font>												
													</div>
													<div class="tbox">
														<s:select name="ltFinanceYN" id="ltFinanceYN" list="#{'N':'No','Y':'Yes'}" cssClass="inputBoxS" disabled='%{(#disable1 || leasedYN==null||"N".equals(leasedYN))?"true":"false"}' onchange="toggleltFinanceYN(this.value);"/>								
													</div>
					        					</div>
					        					<div class="textfield33">
					        						<div class="text">
					        							<s:text name="motor.bankOfFinance" /><font color="red">*</font>												
													</div>
													<div class="tbox">
														<%--
														<s:select name="bankOfFinance" id="bankOfFinance" list="bankOfFinanceList" listKey="CODE" listValue="CODEDESC" headerKey="" headerValue="-Select-" cssClass="inputBoxS" disabled='%{(#disable1 || ltFinanceYN==null||"".equals(ltFinanceYN)||"Y".equals(ltFinanceYN) || leasedYN==null||"".equals(leasedYN)||"N".equals(leasedYN))?"true":"false"}'/>
														--%>
														<s:textfield name="bankOfFinance" id="bankOfFinance" cssClass="inputBox" maxLength="50" disabled='%{(#disable1 || ltFinanceYN==null||"".equals(ltFinanceYN)||"Y".equals(ltFinanceYN) || leasedYN==null||"".equals(leasedYN)||"N".equals(leasedYN))?"true":"false"}'/>
													</div>
					        					</div>
					        					<div class="textfield33" id="trailer1RegDiv">
					        						<div class="text">
					        							<s:text name="motor.trailer1RegNo" /><font color="red">*</font>												
													</div>
													<div class="tbox">
														<table width="90%">
															<tr>
																<td width="20%"><s:textfield name="trailer1RegNo1" id="trailer1RegNo1" maxlength="2" cssClass="inputBox" disabled="#disable1"/></td>
																<td width="5%">-</td>
																<td width="20%"><s:textfield name="trailer1RegNo2" id="trailer1RegNo2" maxlength="2" cssClass="inputBox" disabled="#disable1" onkeyup="checkNumbers(this);"/></td>
																<td width="5%">-</td>
																<td width="20%"><s:textfield name="trailer1RegNo3" id="trailer1RegNo3" maxlength="2" cssClass="inputBox" disabled="#disable1"/></td>
																<td width="5%">-</td>
																<td width="20%"><s:textfield name="trailer1RegNo4" id="trailer1RegNo4" maxlength="4" cssClass="inputBox" disabled="#disable1" onkeyup="checkNumbers(this);"/></td>
															</tr>
														</table>
													</div>
					        					</div>
					        					<div class="textfield33" id="trailer2RegDiv">
					        						<div class="text">
					        							<s:text name="motor.trailer2RegNo" /><font color="red">*</font>												
													</div>
													<div class="tbox">
														<table width="90%">
															<tr>
																<td width="20%"><s:textfield name="trailer2RegNo1" id="trailer2RegNo1" maxlength="2" cssClass="inputBox" disabled="#disable1"/></td>
																<td width="5%">-</td>
																<td width="20%"><s:textfield name="trailer2RegNo2" id="trailer2RegNo2" maxlength="2" cssClass="inputBox" disabled="#disable1" onkeyup="checkNumbers(this);"/></td>
																<td width="5%">-</td>
																<td width="20%"><s:textfield name="trailer2RegNo3" id="trailer2RegNo3" maxlength="2" cssClass="inputBox" disabled="#disable1"/></td>
																<td width="5%">-</td>
																<td width="20%"><s:textfield name="trailer2RegNo4" id="trailer2RegNo4" maxlength="4" cssClass="inputBox" disabled="#disable1" onkeyup="checkNumbers(this);"/></td>
															</tr>
														</table>
													</div>
					        					</div>
					        					<%--<div class="textfield33">
					        						<div class="text">
					        							<s:text name="motor.insuredNameAra" /><font color="red">*</font>											
													</div>
													<div class="tbox">
														<s:textfield name="insNameArabic" id="insNameArabic" cssClass="inputBox"/>								
													</div>
					        					</div>
					        					<div class="textfield33" style="height: 75px;">
					        						<div class="text">
					        							<s:text name="motor.insuredAddressAra" /><font color="red">*</font>											
													</div>
													<div class="tbox">
														<s:textarea name="insAddressArabic" id="insAddressArabic" cssClass="inputBoxA" onkeyup="textLimit(this,'200')" cssStyle="width: 90%;" />								
													</div>
					        					</div>
					        					--%>
					        					<s:hidden name="insNameArabic"/>
				                				<s:hidden name="insAddressArabic"/>
					        					<br class="clear"/>
						        			</div>
						        		</div>
						        	</div>
									<div class="tablerow">
										<div class="panel panel-primary">
						        			<div class="panel-heading txtB">
						        				<s:text name="PA Cover" />												
						        			</div>
						        			<div class="panel-body">
						        				<div class="boxcontent">
						        					<div class="textfield33">
						        						<div class="text">
						        							<s:text name="Owner Driver" /><font color="red">*</font>												
														</div>
														<div class="tbox">
															<s:radio name="ownerDriver" list="#{'Y':'Yes','N':'No'}" value='%{ownerDriver==null||"".equals(ownerDriver)?"N":ownerDriver}' disabled="#disable1"/>								
														</div>
						        					</div>
						        					<div class="textfield33">
						        						<div class="text">
						        							<s:text name="Un-Named Passengers" />
														</div>
														<div class="tbox">
															<table width="90%">
																<tr>
																	<td>Nos</td>
																	<td>
																		<s:textfield name="unNamedPassengersNos" id="unNamedPassengersNos" maxlength="2" cssClass="inputBox" disabled="#disable1"/>
																	</td>
																	<td>
																		<s:select name="unNamedPassengersSi" id="unNamedPassengersSi" list="unNamedPassengerList" cssClass="inputBoxS" headerKey="" headerValue="---SI---" listKey="CODE" listValue="CODEDESC" disabled="#disable1"/>
																	</td>
																</tr>
															</table>
														</div>
						        					</div>	
						        					<div class="textfield33">
						        						<div class="text">
						        							<s:text name="Paid Drivers" />
														</div>
														<div class="tbox">
															<table width="90%">
																<tr>
																	<td>Nos</td>
																	<td>
																		<s:textfield name="paidDriversNos" id="paidDriversNos" maxlength="2" cssClass="inputBox" disabled="#disable1"/>
																	</td>
																	<td width="40%">
																		<s:select name="paidDriversSi" id="paidDriversSi" list="paidDriversList" cssClass="inputBoxS" headerKey="" headerValue="---SI---" listKey="CODE" listValue="CODEDESC" disabled="#disable1"/>
																	</td>
																</tr>
															</table>
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
						        				<s:text name="Legal Liability Cover" />
						        			</div>
						        			<div class="panel-body" id="legalLibCoverTable">
						        				<div class="boxcontent">
						        					<div class="textfield33">
						        						<div class="text">
						        							<s:text name="Driver / Conductor / Cleaner" />										
														</div>
														<div class="tbox">
															<s:textfield name="driverCondCleaner" id="driverCondCleaner" maxlength="2" cssClass="inputBox" disabled="#disable1"/>
														</div>
						        					</div>
						        					<div class="textfield33">
						        						<div class="text">
						        							<s:text name="Non-Fare Paying Passenger" />												
														</div>
														<div class="tbox">
															<s:textfield name="nonFarePassenger" id="nonFarePassenger" maxlength="2" cssClass="inputBox" disabled="#disable1"/>
														</div>
						        					</div>
						        					<div class="textfield33">
						        						<div class="text">
						        							Employees in Operation												
														</div>
														<div class="tbox">
															<s:textfield name="employeesInOperation" id="employeesInOperation" maxlength="2" cssClass="inputBox" disabled="#disable1"/>
														</div>
						        					</div>
						        				</div>
						        				<br class="clear"/>
						        			</div>
						        		</div>
						        	</div>
						        	<div class="tablerow">
										<div class="panel panel-primary">
						        			<div class="panel-heading txtB">
						        				<div class="textfield33" style="height: auto;">
						        					<s:text name="Coverages" />
												</div>
												<br class="clear" />
						        			</div>
						        			<div class="panel-body">
						        				<div class="boxcontent">
						        					<s:iterator value="farmCoverDetailsList" var="var" status="status">
							        					<div class="textfield33">
							        						<div class="text">
							        							<s:text name="%{#var.COVERAGES_NAME}" />
							        						</div>
							        						<div class="tbox">
							        							<s:hidden name="coverageNameList[%{#status.index}]" value="%{#var.COVERAGES_NAME}"/>
						        								<s:hidden name="coverageIdList[%{#status.index}]" value="%{#var.COVERAGES_SUB_ID}"/>
						        								<s:radio name="coverageValueList[%{#status.index}]" list="#{'Y':'Yes','N':'No'}" value='%{ccoverageValueList==null||coverageValueList[#status.index]==null||"".equals(coverageValueList[#status.index])?#var.STATUS:coverageValueList[#status.index]}' disabled="#disable1"/>
							        						</div>
							        					</div>
							        				</s:iterator>
						        					<div class="textfield33">
						        						<div class="text">
						        							<s:text name="Loading / Discount" />
						        						</div>
						        						<div class="tbox">
						        							<table width="100%">
					        									<tr>
					        										<td width="50%">
					        											OD %&nbsp;<s:textfield name="loadingOd" id="loadingOd" maxlength="3" size="15" cssClass="input" disabled="#disable1" onkeyup="checkDecimals(this);"/>
					        										</td>
					        										<td width="50%">
					        											TP&nbsp;<s:textfield name="loadingTp" id="loadingTp" maxlength="3" size="15" cssClass="input" disabled="#disable1" onkeyup="checkDecimals(this);"/>
					        										</td>
					        									</tr>
					        								</table>
						        						</div>
						        					</div>
						        					<div class="textfield33">
						        						<div class="text">
						        							<s:text name="No Claim Bonus" />
						        						</div>
						        						<div class="tbox">
						        							<s:select name="noClaimBonusVal" id="noClaimBonusVal" list="noClaimBonusList" cssClass="inputBoxS" headerKey="" headerValue="---Select---" listKey="CODE" listValue="CODEDESC" disabled="#disable1"/>
						        						</div>
						        					</div>
						        				</div>
						        				<br class="clear"/>
						        				<br class="clear"/>
						        			</div>
						        		</div>
						        	</div>
					        	</div>
					        </div>
						</div>
			        	<div class="tablerow" align="center">
			        		<s:if test='"Y".equals(paCoverStatus)'>
			        			<s:if test='"admin".equalsIgnoreCase(#session.usertype)'>
			        				<s:submit type="button" name="Back" value="Back" cssClass="btn btn-sm btn-danger" onclick="fnsubmit('paPremiuminfoHome');"/>
			        			</s:if>
			        			<s:else>
			        				&nbsp;&nbsp;&nbsp;
			        				<s:submit type="button" name="Back" value="Back" cssClass="btn btn-sm btn-danger" onclick="fnsubmit('paCoverHome');"/>
			        			</s:else>
			        		</s:if>
			        		<s:else>
			        			&nbsp;&nbsp;&nbsp;
			              		<s:submit type="button" name="Back" value="Back" cssClass="btn btn-sm btn-danger" onclick="fnsubmit('getBackHome');"/>
			              	</s:else>
			              	&nbsp;&nbsp;&nbsp;
			              	<s:submit name="Submit3" type="submit" cssClass="btn btn-sm btn-success" value="Proceed" onclick="disableField(this);this.form.actionType.value='getQuote';disableForm(this.form,false,'');"/>&nbsp;&nbsp;&nbsp;
			              	
			              	<s:hidden name="display" />
			                <s:hidden name="actionType" />
			                <s:hidden name="fleetNo" />
			                <s:hidden name="applicationNo" />
			                <s:hidden name="quoteNo" />
			                <s:hidden name="menuType"/>
			                <s:hidden name="referralMsg"/>
			                <s:hidden name="endTypeId"/>
			                <s:hidden name="policyNo"/>
			                <s:hidden name="brokerCompany"/>
			                <s:hidden name="custName"/>
			        	</div>
			        </s:form>
				</div>
				
			</div>
		</div>
		<script  type="text/javascript">
			function fnsubmit(action) {
				document.quotation.action = "${pageContext.request.contextPath}/" + action;
				document.quotation.submit();
			}
			appPath = "${pageContext.request.contextPath}/";
			function getAjaxModel(frm,val, id) {
					postRequest('${pageContext.request.contextPath}/'+id+'Home.action'+val, id);
			}
			function getList(val, id) {
				postRequest('${pageContext.request.contextPath}/'+id+'Home.action'+val, id);
			}
			function fnVehiclesubmit(type, vehicleId) {
				document.getElementById('deleteVehicleId').value = vehicleId;
				if("delete"==type) {
					document.quotation.action = "${pageContext.request.contextPath}/deleteAddVehicleMotor";
				}
				else if("edit"==type) {
					document.quotation.action = "${pageContext.request.contextPath}/editAddVehicleMotor";
				}
				document.quotation.submit();
			}
			function toggleLeasedYN(value) {
				if(value=="Y") {
					document.getElementById('ltFinanceYN').disabled=false;
					toggleltFinanceYN(document.getElementById('ltFinanceYN').value);
				}   
				else {
					document.getElementById('ltFinanceYN').value = "N";
					document.getElementById('ltFinanceYN').disabled=true;
					toggleltFinanceYN("Y");
				}
		    }
		    function toggleltFinanceYN(value) {
		    	if(value=="N") {
					document.getElementById('bankOfFinance').disabled=false;
				}   
				else {  
					document.getElementById('bankOfFinance').value='';
					document.getElementById('bankOfFinance').disabled=true;
				}
		    }
			function toggleTrailerDetails() {
				var noOfTrailer = document.getElementById('noOfTrailer').value;
				if("1"==noOfTrailer || "2"==noOfTrailer) {
					document.getElementById('trailer1Div').style.display = "";	
					document.getElementById('trailer1RegDiv').style.display = "";	
				}
				else {
					document.getElementById('trailer1Div').style.display = "none";
					document.getElementById('trailerIdv1').value = "";
					document.getElementById('trailer1MfrDate').value = "";
					
					document.getElementById('trailer1RegDiv').style.display = "none";
					document.getElementById('trailer1RegNo1').value = "";
					document.getElementById('trailer1RegNo2').value = "";
					document.getElementById('trailer1RegNo3').value = "";
					document.getElementById('trailer1RegNo4').value = "";
				}
				if("2"==noOfTrailer) {
					document.getElementById('trailer2Div').style.display = "";
					document.getElementById('trailer2RegDiv').style.display = "";
				}
				else {
					document.getElementById('trailer2Div').style.display = "none";
					document.getElementById('trailerIdv2').value = "";
					document.getElementById('trailer2MfrDate').value = "";
					
					document.getElementById('trailer2RegDiv').style.display = "none";
					document.getElementById('trailer2RegNo1').value = "";
					document.getElementById('trailer2RegNo2').value = "";
					document.getElementById('trailer2RegNo3').value = "";
					document.getElementById('trailer2RegNo4').value = "";
				}
			}
			<s:if test='endTypeId!=null && !"".equalsIgnoreCase(endTypeId)'>
				enableForm(document.motor,false,'<s:property value="%{fields}"/>');
			</s:if>
			toggleTrailerDetails();
		</script>
	</body>
</html>