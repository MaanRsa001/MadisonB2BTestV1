<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ page import="java.util.List,java.util.ArrayList,java.util.Map,java.util.HashMap,java.util.Collections"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page import="nl.captcha.Captcha"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<style type="text/css">
	.nav-pills>li.active>a,.nav-pills>li.active>a:focus,.nav-pills>li.active>a:hover {
		color: #ffffff;
		background-color: #ff6f00;
	}
	.nav-justified>li>a {    
	    background-color: #ffb300;
	    color: #000000; 
	}
	.textSize {
		font-size: 12px;
	}
	.row {
		margin-bottom: 10px;
	}
	.text {
		font-size: 14px;
		font-weight: bold;
	}
	</style>
</head>
<body>
<s:set var="disable1" value="%{((endTypeId!=null && endTypeId.length() > 0)||(quoteStatus=='RA'))}"/>
<s:set var="endtDisable" value="%{endTypeId!=null && endTypeId.length() > 0}"/>
<div id="loading" style="width:100%">
   <img id="loading-image" src="${pageContext.request.contextPath}/images/ajax-loader-bar.gif"/>
</div>
<div class="row">
	<div class='${("User"==userType||"B2C"==session.LoginType)?"col-xs-12 col-sm-12 col-md-9 col-lg-9":"col-xs-12"}'>
	<s:if test="'newQuote'.equalsIgnoreCase(display)">
		<s:form id="motor" name="motor" method="post" theme="simple" action="getQuoteMotor.action">
		<div class="row">
			<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
				<div class="panel panel-primary">
					<div class="panel-body"  style="padding: 0px;">
						<img alt="Car Banner" src="<%=request.getContextPath()%>/images/car-quote-header.jpg" style="width: 100%; height: auto;">
					</div>				
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
				<span style="color:red;"><s:actionerror/></span>
			</div>
		</div>		
		<div class="row">
			<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
				<div class="panel panel-default">
					<div class="panel-heading">
						<div class="row">
							<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
								<div class="btn-group" role="group" aria-label="...">
								  <button type="button" title="Customer Information" class="btnGroupMargin btn btn-lg btn-success active"><i class="fa fa-user"></i></button>
								  <button type="button" title="Premium Comparison" class="btnGroupMargin btn btn-lg btn-primary" disabled="disabled"><i class="fa fa-eye"></i></button>
								  <button type="button" title="Policy Information" class="btnGroupMargin btn btn-lg btn-primary" disabled="disabled"><i class="fa fa-users"></i></button>
								  <button type="button" title="Policy Information" class="btnGroupMargin btn btn-lg btn-primary" disabled="disabled"><i class="fa fa-file-text-o"></i></button>
								</div>
							</div>
							<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6 text-success">
								<span class="textHeading"><s:text name="label.quoteInfo" /> </span> 
							</div>
						</div>						
					</div>		
				</div>
			</div>
		</div>
		<!-- <div class="row">
			<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
				<s:include value="/pages/customer/customerDetailsMotor.jsp"/>
			</div>			
		</div> -->
		<div class="row">
			<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
				<div class="panel panel-primary">
        			<div class="panel-heading txtB">
        				<s:text name="motor.policyDetails" />
        			</div>
        			<div class="panel-body">
        				<div class="row">
        					<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
        						<div class="text"><s:text name="motor.policyStartDt"/><s:text name="(DD/MM/YYYY)" /><font color="red">*</font></div>
        						<div class="tbox"><s:textfield name="policyStartDate" id="motorPolicyStartDate" onchange="getAjaxModel(this.form,'?policyStartDate='+this.value,'policyEndList')" cssClass="inputBox datePicker tooltipContentR" data-content="When you want your policy to be effective. Can only be a future date." disabled="#disable1"/></div>       							
								<%--
								<div class="inputAppend">
									<sj:datepicker name="policyStartDate" id="policyStartDate" cssClass="inputBox1" cssStyle="border: none;background: transparent; " displayFormat="dd/mm/yy" minDate="-%{backDt}D" maxDate="+12M" readonly="true"  disabled="#disable1" changeMonth="true" changeYear="true" showAnim="slideDown" duration="fast"/>
								</div>
								 --%>
        					</div>
        					<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
        						<div class="text"><s:text name="motor.policyEndDt"/><font color="red">*</font></div>
        						<div class="tbox"><div id="policyEndList"><s:select name="policyEndDate" id="policyEndDate" list="policyEndList" listKey="CODE" listValue="CODEDESC" cssClass="inputBoxS tooltipContent" data-content="When you want your policy to end" disabled="#disable1"/></div></div>
        					</div>
        					<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
        						<div class="text"><s:text name="motor.currencyType"/><font color="red">*</font></div>
        						<div class="tbox">
        							<s:select name="currencyType" id="currencyType" list="currencyTypeList" cssClass="inputBoxS tooltipContent" data-content="Your preferred currency e.g dollar or kwacha" disabled="#disable1" cssStyle="color: #000000;"/>
        						</div>
        					</div>
        				</div>
        			</div>
        		</div>
			</div>			
		</div>
		<div class="row" style="display:none;">
			<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
				<div class="btn-group btnGroupTypeDiv" role="group" aria-label="...">
					<s:hidden name="premiumType" id="premiumType"/>
					<%--
					<button type="button" id="tplBtn" title="Third Party Liability" class='${"2"==premiumType?"btnGroupType btn btn-lg btn-success active":"btnGroupType btn btn-lg btn-warning"}' style="min-height: 200px;" onclick="changeBtn('2');"> <i class="fa fa-ambulance"></i> <br/> Third Party Liability </button>
					<button type="button" id="comprehensiveBtn" title="Comprehensive" class='${"1"==premiumType?"btnGroupType btn btn-lg btn-success active":"btnGroupType btn btn-lg btn-warning"}' style="min-height: 200px;" onclick="changeBtn('1');"> <i class="fa fa-money"></i> <br/> Comprehensive </button>
					<button type="button" id="bothBtn" title="Both" class='${"0"==premiumType?"btnGroupType btn btn-lg btn-success active":"btnGroupType btn btn-lg btn-warning"}' style="min-height: 200px;" onclick="changeBtn('0');"> <i class="fa fa-files-o"></i> <br/> Both </button>
					--%>
					<s:submit type="button" id="tplBtn" title="Third Party Liability" cssClass='%{"2"==premiumType?"btnGroupType btn btn-lg btn-success active":"btnGroupType btn btn-lg btn-warning"}' cssStyle="min-height: 200px;" onclick="return changeBtn('2');" disabled="#disable1"> <i class="fa fa-ambulance" style="font-size: 100px;"></i> <br/> Third Party Liability </s:submit>
					<s:submit type="button" id="comprehensiveBtn" title="Comprehensive" cssClass='%{"1"==premiumType?"btnGroupType btn btn-lg btn-success active":"btnGroupType btn btn-lg btn-warning"}' cssStyle="min-height: 200px;" onclick="return changeBtn('1');" disabled="#disable1"> <i class="fa fa-money" style="font-size: 100px;"></i> <br/> Comprehensive </s:submit>
					<s:submit type="button" id="bothBtn" title="Both" cssClass='%{"0"==premiumType?"btnGroupType btn btn-lg btn-success active":"btnGroupType btn btn-lg btn-warning"}' cssStyle="min-height: 200px;" onclick="return changeBtn('0');" disabled="#disable1"> <i class="fa fa-files-o" style="font-size: 100px;"></i> <br/> Both </s:submit>
				</div>
			</div>
		</div>
		<br />
		<div>
			<div class="row">
				<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">				
					<div class="panel panel-primary">
	        			<div class="panel-heading txtB">
	        				<s:text name="motor.vehicleDetails" />
	        				<button type="button" class="btn btn-sm btn-primary" data-toggle="modal" data-refresh="true" href="${pageContext.request.contextPath}/getHelpInfoMotor.action?helpType=VEHICLE_DETAILS" data-target="#modalvehicleDetails"> <img src="${pageContext.request.contextPath}/images/Help.png" width="20" height="20"/> </button>
							<div class="modal fade" data-refresh="true" id="modalvehicleDetails" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
								<div class="modal-dialog modal-lg">
									<div class="modal-content">
										<div class="modal-header">
											<h4 class="modal-title"> <s:label key="motor.vehicleDetails"/> </h4>
										</div>
										<div class="modal-body">
											<div class="te"></div>
										</div>
									</div>
								</div>
							</div>
	        			</div>
	        			<div class="panel-body">
	        				<s:set name="multiVehicleDtls" value="%{multiVehicleDetails}"/>
							<s:if test='applicationNo !=null && applicationNo.length() > 0 && #multiVehicleDtls.size()>0'>
							<div class="row">
								<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
									<table cellpadding="1" class="footable" id="vehicleDetailsTable" cellspacing="1" border="1">
										<thead>
									        <tr>
												<th style="width:5%;"><s:label value="S.No." /></th>
												<th style="width:15%;"><s:text name="motor.vehicleUsage"/></th>
												<th style="width:20%;"><s:text name="motor.make" /></th>
												<th style="width:20%;"><s:text name="motor.model" /></th>
												<th style="width:15%;"><s:text name="motor.typeOfBody" /></th>
												<th style="width:15%;"><s:text name="motor.sumInsured" /></th>
												<th style="width:10%;">Action</th>
											</tr>
										</thead>
										<tbody>
											<s:iterator value="#multiVehicleDtls" var="view" status="status">
												<tr>
													<td align="center"><s:property value="#status.count" /></td>
													<td align="center"><s:property value="#view.VEHICLETYPE_DESC" /></td>
													<td align="center"><s:property value="#view.MAKE_NAME" /></td>					
													<td align="center"><s:property value="#view.MODEL_NAME" default=" " /> </td>
													<td align="center"><s:property value="#view.TYPE_OF_BODY_NAME" /></td>
													<td align="right"> <s:property value="getText('{0,number,#,##0.00}',{#view.SUMINSURED_VALUE_LOCAL})"/> </td>
													<td align="center">
														<s:if test='"admin".equalsIgnoreCase(#session.usertype)'>
															<s:submit type="button" name="rateEditButton" id="rateEditButton" onclick="return fnVehiclesubmit(this.form,'rate','%{VEHICLE_ID}');" cssClass="btn btn-sm btn-warning" value="Modify Rate"/>
														</s:if>
														<s:else>
															<s:submit type="button" name="vehicleEditButton" id="vehicleEditButton" onclick="return fnVehiclesubmit(this.form,'edit','%{VEHICLE_ID}');" cssClass="btn btn-sm btn-warning" disabled="#disable1"> <i class="glyphicon glyphicon-pencil"></i> </s:submit>
															<s:submit type="button" name="vehicleDeleteButton" id="vehicleDeleteButton" cssClass="btn btn-sm btn-danger" disabled="#disable1" data-toggle="modal" data-target="#vehicleDeleteModal%{#view.VEHICLE_ID}" onclick="return false;"><i class="glyphicon glyphicon-remove-sign"></i></s:submit>
															<div id="vehicleDeleteModal${view.VEHICLE_ID}" class="modal fade" role="dialog">
																<div class="modal-dialog">
																	<div class="modal-content" >
																		<div class="modal-header">
																			<h4>Confirmation</h4>
																		</div>
																		<div class="modal-body">
																			<h5> You are about to delete the vehicle added </h5>
																		</div>
																		<div class="modal-footer">
																			<button type="button" class="btn btn-sm btn-danger" data-dismiss="modal">Cancel</button>
																			<button type="button" class="btn btn-sm btn-warning" data-dismiss="modal" onclick="return fnVehiclesubmit(this.form,'delete','${view.VEHICLE_ID}');">Delete</button>
																		</div>
																	</div>
																</div>
															</div>
														</s:else>
													</td>
												</tr>
											</s:iterator>
										</tbody>
									</table>
								</div>
							</div>
							<s:hidden name="deleteVehicleId" id="deleteVehicleId"/>
							<s:hidden name="rateVehicleId" id="rateVehicleId"/>
							</s:if>
							<div>
								<div class="row addVehicle" style="display: none;">
									<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
										<div class="row">
											<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
												<div class="text"><s:text name="motor.make" /><font color="red">*</font></div>
												<div class="tbox">
													<div id="makeListAjax">
														<s:select name="makeIdList[0]" id="make" list="makeList" listKey="CODE" listValue="CODEDESC" headerKey="" headerValue="---Select---" onchange="getAjaxModel(this.form,'?make='+this.value,'modelList');removeVehicleTypeDetails();" cssClass="inputBoxS tooltipContent" data-content="Select your vehicle Make e.g. Toyota" disabled="#disable1" theme="simple"/>
													</div>
												</div>
											</div>				
											<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
												<div class="text"><s:text name="motor.model" /><font color="red">*</font></div>
												<div class="tbox">
													<div id="modelList">
														<s:select name="modelIdList[0]" id="model" list="modelList" listKey="CODE" listValue="CODEDESC" headerKey="" headerValue="---Select---" onchange="removeVehicleTypeDetails();" cssClass="inputBoxS tooltipContent" data-content="Select your vehicle Model e.g. Corolla" disabled="#disable1" theme="simple"/>
													</div>
												</div>
											</div>
											<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
												<div class="text"><s:text name="motor.vehicleDetails" /><font color="red">*</font></div>
												<div class="tbox">
													<s:submit type="button" name="vehicleSearchButton" id="vehicleSearchButton" cssClass="btn btn-info btn-danger tooltipContent" data-content="Click to select vehicle body type, Usage and Seating Capacity" cssStyle="width: 100%;" onclick="return vehicleTypeDetailsAjax(this.form);" disabled="#disable1" data-toggle="modal" data-target="#vehicleTypeDetails"><s:text name="motor.clickToAddVehicleDetails" /></s:submit>
													<div id="vehicleTypeDetails" class="modal fade" role="dialog">
														<div class="modal-dialog">
															<div class="modal-content">
																<div class="modal-header">
																	<button type="button" class="close" data-dismiss="modal">&times;</button>
																	<h4 class="modal-title" align="center">Vehicle Details</h4>
																	<div class="row" align="center">
																		<SPAN id="modalErrorSpan" style="color: red;">
																		</SPAN>
																	</div>
																</div>
																<div class="modal-body" id="vehicleTypeDetailsAjax">
																	
																</div>
																<div class="modal-footer">
																	<button type="button" class="btn btn-sm btn-danger" data-dismiss="modal">Cancel</button>
																	<button type="button" class="btn btn-sm btn-success" onclick="setVehicleTypeDetails(this.form);">Submit</button>
																</div>
															</div>
														</div>
													</div>
												</div>
											</div>
											<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
												<div class="text"><s:text name="motor.typeOfBody" /><font color="red">*</font></div>
												<div class="tbox">
													<%--
													<div id="typeBodyAjax"><s:select name="typeBodyIdList[0]" id="typeBody" list="typeBodyList" listKey="CODE" listValue="CODEDESC" cssClass="inputBoxS" disabled="true"/></div>
													--%>
													<div id="typeBodyDiv">
														<s:property value="typeBodyNameList[0]"/>
													</div>
													<s:hidden name="typeBodyNameList[0]" id="typeBodyName"/>
													<s:hidden name="typeBodyIdList[0]" id="typeBody"/>
												</div>
											</div>
											<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
												<div class="text"><s:text name="motor.vehicleUsage" /><font color="red">*</font></div>
												<div class="tbox">
													<%--
													<div id="vehicleUsageAjax"><s:select name="vehicleUsageIdList[0]"  id="vehicleUsage" list="vehicleUsageList" listKey="CODE" listValue="CODEDESC" headerKey="" headerValue="---Select---" cssClass="inputBoxS" onchange="getDeductibleAjax(this.form);" disabled="#disable1"/></div>
													--%>
													<div id="vehicleUsageDiv">
														<s:property value="vehicleUsageNameList[0]"/>
													</div>
													<s:hidden name="vehicleUsageNameList[0]" id="vehicleUsageName"/>
													<s:hidden name="vehicleUsageIdList[0]" id="vehicleUsage"/>
												</div>
											</div>
											<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4" id="seatingCapacityBlock">
												<div class="text"><s:text name="motor.seatingCapacity" /><font color="red">*</font>&nbsp;</div>
												<div class="tbox">
													<%--
													<s:textfield name="seatingCapacityList[0]" id="seatingCapacity" size="2" cssClass="inputBox" maxLength="2" onkeyup="checkNumbers(this);" onblur="getDeductibleAjax(this.form);" disabled="#disable1" cssStyle="width: 50px;" /> &nbsp;<s:text name="motor.includingDriver"/>
													--%>
													<div id="seatingCapacityDiv">
														<s:property value="seatingCapacityList[0]"/>
													</div>
													<s:hidden name="seatingCapacityList[0]" id="seatingCapacity"/>
												</div>
											</div>
											<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
												<div class="text"><s:text name="motor.sumInsured" /><font color="red">*</font></div>
												<div class="tbox">
													<s:textfield name="sumInsuredList[0]" id="sumInsured" cssClass="inputBox tooltipContent" data-content="The market value of your vehicle upon which premium calculation will be based." maxlength="8" onkeyup="checkNumbers(this);" disabled="#disable1"/>
												</div>
											</div>
											<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
												<div class="text"><s:text name="motor.electricalAcc" /></div>
												<div class="tbox">
													<s:textfield name="electricalAccList[0]" id="electricalAcc" cssClass="inputBox tooltipContent" data-content="The total sum amount for any extra electrical equipment additions to the vehicle that you want to state separately e.g. A custom sound system" maxlength="8" onkeyup="checkNumbers(this);" disabled="#disable1"/>
												</div>
											</div>
											<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
												<div class="text"><s:text name="motor.nonElectricalAcc" /></div>
												<div class="tbox">
													<s:textfield name="nonElectricalAccList[0]" id="nonElectricalAcc" cssClass="inputBox tooltipContent" data-content="The total sum amount for any extra body enhancements you have made to the vehicle that you want to state separately e.g. Low Profile Tyres" maxlength="8" onkeyup="checkNumbers(this);" disabled="#disable1"/>
												</div>
											</div>
											<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
												<div class="text"><s:text name="motor.mfgYr" /><font color="red">*</font></div>
												<div class="tbox">
													<s:select name="mfgYrIdList[0]"  id="mfgYr" list="mfgYrMap" headerKey="" headerValue="---Select---" cssClass="inputBoxS tooltipContent" data-content="The year your vehicle was manufactured" disabled="#disable1" />
												</div>
											</div>
											<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
												<div class="text"><s:text name="motor.deductible" /><font color="red">*</font></div>
												<div class="tbox">
													<div id="deductibleAjax">
													       <s:select name="deductibleIdList[0]" id="deductible" list="deductibleList" listKey="CODE" listValue="CODEDESC" cssClass="inputBoxS tooltipContent" data-content="Excess is the first amount you will bear in case of a claim. If you choose a higher excess your rate will be lower." disabled="#disable1" />
													</div>
												</div>
											</div>
											<s:hidden name="agencyRepairYNIdList[0]" value="N"/>
				        					<s:hidden name="vehicleIdList[0]" id="rvehicleId"/>
										</div>
									</div>
								</div>
								<div class="row addVehicle" style="display: none;">
									<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
										<div class="row">
											<div class="col-xs-12">
												<span class="text-primary"><s:text name="motor.driverDetails" /></span>
												<hr style="border: 1px solid #c2c2c2; margin: 0px 0px 0px 5px" />
											</div>
										</div>
										<div class="row">
											<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
					       						<div class="text"><s:text name="motor.driverId" /></div>
												<div class="tbox">
													<s:textfield name="driverIdList[0]" id="driverIdList[0]" cssClass="inputBox tooltipContent" data-content="Valid driver licence number for driver of the vehicle entered above" maxlength="20" disabled="#disable1"/>
												</div>
					       					</div>
					       					<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
				       							<div class="text"><s:text name="motor.driverDOB" /><font color="red">*</font></div>
												<div class="tbox">
													<s:textfield name="driverDOBList[0]" id="driverDOB" cssClass="inputBox datePicker tooltipContentL" data-content="The driver’s date of birth. Should be more than 18 years." disabled="#disable1"/>
												</div>   						
											</div>
											<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
												<div class="text">
				        							<s:text name="motor.vehicleOwnerDriver" /><font color="red">*</font>												
												</div>
												<div class="tbox">
													<s:radio name="ownerDriverYNList[0]" id="ownerDriverYN" list="#{'Y':'Yes','N':'No'}" value='%{(ownerDriverYNList==null || ownerDriverYNList[0]==null || "".equals(ownerDriverYNList[0]))?"N":ownerDriverYNList[0]}' disabled="#disable1"/>
												</div>
											</div>
										</div>
									</div>
								</div>
								<!--<div class="row addVehicle" style="display: none;">
									<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
										<div class="row">
											<div class="col-xs-12">
												<span class="text-primary"><s:text name="motor.driverClaimDetails" /></span>
												<hr style="border: 1px solid #c2c2c2; margin: 0px 0px 0px 5px" />
											</div>
										</div>
										<div class="row">
											<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
				        						<div class="text">
				        							<s:text name="motor.noClaimBonus" /><font color="red">*</font>												
												</div>
												<div class="tbox">
													<div id="ncbAjax"> <s:select name="noClaimBonusIdList[0]" id="noClaimBonus" list="noClaimBonusList" headerKey="" headerValue="---Select---" cssClass="inputBoxS tooltipContentR" data-content="The number of years you have insured your vehicle without making a claim" disabled="#disable1" listKey="CODE" listValue="CODE" theme="simple"/> </div>
												</div>
											</div>
											<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
												<div class="text">
				        							<s:text name="motor.driverClaimMadeYN" /><font color="red">*</font>												
												</div>
												<div class="tbox">
													<s:radio name="claimYNIdList[0]" id="claimYN" list="#{'Y':'Yes','N':'No'}" value="%{claimYN==null?'N':claimYN}" onclick="toggleClaimDetails(this.value);" cssClass="tooltipContent" data-content="Indicate whether you have made any claims" disabled="#disable1"/>
												</div>
											</div>
											<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
												<div class="text">
				        							<s:text name="motor.claimAmount" /><font color="red">*</font>												
												</div>
												<div class="tbox">
													<s:textfield name="claimAmountList[0]" id="claimAmount" cssClass="inputBox tooltipContent" data-content="If you have made any claim, enter the claim amount" maxLength="8" onkeyup="checkDecimals(this);" disabled="#disable1"/>
												</div>
											</div>
											<br class="clear">
											<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
												&nbsp;&nbsp;&nbsp;<font color="red">"Note that the NCB years given is the basis for the contract and a wrong declaration would make the cover invalid from inception."</font>
											</div>
										</div>
									</div>
								</div>-->
								
							</div>			
	        			</div>
	        		</div>
	        		<s:if test="premiumInfo.size()>0">
						<div class="row">
							<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
								<div class="panel panel-primary">
				        			<div class="panel-heading txtB">
										<s:text name="motor.premiumInfo"/>
				        			</div>
				        			<div class="panel-body">
				        				<div class="boxcontent">
				        					<table class="footable" width="100%">
				        						<tbody>
					        						<s:set name="groupId" value=""/>
								                    <s:set var="preAmt" value="0.0" scope="page"/>
								                    <s:iterator value="premiumInfo" var="prInfo" status="stat">
					        						
								                    <s:if test="%{#groupId != #prInfo.GROUP_ID}">
								                    <s:set name="groupId" value="%{#prInfo.GROUP_ID}"/>
								                    <tr class="tableHeading">
								                    	<td colspan="5"><s:property value="%{#prInfo.GROUP_DESC_ENGLISH}"/></td>
								                    </tr>
								                    </s:if>
					        						<tr>
					        							<td><s:hidden name="coverId[%{#stat.count-1}]" value="%{#prInfo.POLICYTYPE_COVERID}"/></td>
					        							<td><s:property  value="%{#prInfo.OPCOVER_DESC_ENGLISH}"/></td>
					        							<td>
					        								<s:property value="getText('{0,number,#,##0.00}',{#prInfo.SUM_INSURED})"/>
						                             		<s:hidden name="sI[%{#stat.count-1}]"  cssClass="inputBox" value="%{#prInfo.SUM_INSURED}" cssStyle="text-align:right;"/>
					        							</td>
					        							<td>
					        								<s:textfield name="baseRate[%{#stat.count-1}]" cssClass="inputBox" value="%{#prInfo.RATE}" cssStyle="text-align:right;" maxLength="16" onkeyup="checkDecimals6_4(this);" disabled='"R".equals(#prInfo.CALC_TYPE)?"false":true'/>
					        							</td>
					        							<td>
					        								<s:property value="getText('{0,number,#,##0.00}',{#prInfo.PREMIUM})"/>
						                            		<s:set var="preAmt" value="%{@java.lang.Double@parseDouble(#attr.preAmt)+@java.lang.Double@parseDouble(#prInfo.PREMIUM)}" scope="page"/>
					        							</td>
					        						</tr>        						
					        						</s:iterator>
				        						</tbody>
				        					</table>
				        					<br class="clear"/>
				        				</div>
				        			</div>
				        		</div>
							</div>
						</div>
					</s:if>
				</div>
			</div>
			<s:if test='!"admin".equalsIgnoreCase(#session.usertype)'>
				<div class="row">
					<div class="row" id="toggleAddVehicleDiv">
						<div class="col-xs-12 col-sm-12 col-md-8 col-lg-8">&nbsp;</div>
						<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4" align="right">
							<input type="button" name="toggleAddVehicleButton" id="toggleAddVehicleButton" value="Click to Add another vehicle" class="btn btn-sm btn-danger" onclick="toggleAddVehicle('show');"/>
							&nbsp;&nbsp;&nbsp;
						</div>
					</div>
					<div class="row addVehicle" style="display: none;">
						<div class="col-xs-12 col-sm-12 col-md-8 col-lg-8">&nbsp;</div>
						<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4" align="right">									
		            		<s:submit name="Submit3" type="submit" cssClass="btn btn-sm btn-danger tooltipContent" data-content="Used to add more vehicle details in case of fleet policy" value='%{(vehicleIdList!=null && vehicleIdList[0]!=null && !"".equals(vehicleIdList[0]))?"Click to Update Vehicle":"Save"}' onclick="this.form.actionType.value='getQuote';disableForm(this.form,false,'');fnsubmit('addVehicleMotor')"/>&nbsp;&nbsp;&nbsp;
						</div>
					</div>
				</div>
			</s:if>
			<s:if test='"B2C".equalsIgnoreCase(#session.LoginType)'>
				<div class="row">
					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
						<div class="panel panel-primary">
							<div class="panel-body">
								<div class="row">
									<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
										<div class="text"><s:text name="motor.pleaseenterthetextthatyouseeintheimage" /></div>
										<div class="tbox">
											<s:textfield name="captchavalue" id="captchavalue" value="" cssClass="inputBox tooltipContent" data-content="Please enter the text that you see in the image" />
										</div>
									</div>
									<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
										<%	Captcha captcha = new Captcha.Builder(200, 50).addText().addBackground().addNoise().gimp().addBorder().build(); %>
							            <div class="captchaBg" style="width: 100px; float: left;">
							            	<img id="captcha" src="<c:url value="simpleCaptcha.jpg"  />" width="100">
							            </div>
							            <a href="#" onclick="reloadCaptcha();" style="float: left;"><img src="${pageContext.request.contextPath}/images/reload.png" alt="reload" width="40" height="40"/> </a>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				</s:if>
				<div class="row">
					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" align="center">
						<s:if test='#session.LoginType != "B2C"'> 
		        			<s:submit type="button1" name="close"  key="Back" cssClass="btn btn-sm btn-danger" onclick="getBack('home')"/>&nbsp;&nbsp;&nbsp;
		        		</s:if>
		        		<s:if test='"admin".equalsIgnoreCase(#session.usertype)'>
		        			<s:if test='rateVehicleId!=null && !"".equals(rateVehicleId)'>
		        				<input type="button" name="Submit2" class="btn btn-sm btn-info" value="Calculate" onclick="this.form.actionType.value='getCalculate';disableForm(this.form,false,'');fnsubmit('updateCovRateVehicleMotor');"/>&nbsp;&nbsp;&nbsp;
		        			</s:if>
							<input type="button" name="Submit3" class="btn btn-sm btn-success" value="Proceed" onclick="this.form.actionType.value='getQuote';disableForm(this.form,false,'');fnsubmit('updateCovRateVehicleMotor');"/>
		        		</s:if>
		        		<s:else>
			              	<span id="proceedTPSpan" style='${"2"==premiumType?"":"display:none;"}'> <s:submit type="submit" cssClass="btn btn-sm btn-success" value="Submit" onclick="this.form.actionType.value='getQuote';disableForm(this.form,false,'');fnsubmit('addVehicleMotor')"/> </span>
			              	<span id="proceedSpan" style='${"2"!=premiumType?"":"display:none;"}'> <s:submit name="Submit3" type="submit" cssClass="btn btn-sm btn-success tooltipContent" data-content="Used to get quote for the supplied vehicles details" value="Get Quote" onclick="this.form.actionType.value='getQuote';disableForm(this.form,false,'');"/> </span>
						</s:else>
					</div>
				</div>
				<br class="clear" />
		        
				<div id="premiumCard" class="panel panel-danger" <s:if test='!("2".equals(premiumType) && !hasActionErrors() && #multiVehicleDtls!=null && #multiVehicleDtls.size()>0)'> style="display:none;" </s:if> >
					<div class="panel-heading">
						<s:set var="tpPremiumInfo" value="%{thirdPartyPremiumInfo}"/>
						<s:property value='#tpPremiumInfo.get("X_DATA_NAME")'/> Premium : <s:property value='#tpPremiumInfo.get("DATA_VALUE")'/> 
						<span class="pullRight">
							<s:submit type="button" cssClass="btn btn-sm btn-warning" value="Buy Now" onclick="this.form.actionType.value='getQuote';disableForm(this.form,false,'');"/>
						</span> 
						<br class="clear" />
					</div>
				</div>
			</div>
			<s:hidden name="display" />
			<s:hidden name="actionType" />
			<s:hidden name="fleetNo" />
			<s:hidden name="applicationNo" />
			<s:hidden name="quoteNo" />
			<s:hidden name="quoteStatus"/>
			<%--<s:hidden name="referralMsg"/>--%>
			<s:hidden name="endTypeId"/>
			<s:hidden name="policyNo"/>
			<s:hidden name="brokerCompany"/>
			<s:hidden name="custName"/>
			<s:hidden name="vehicleId"/>
			<s:hidden name="noClaimBonusIdList[0]" id="noClaimBonus" value=""/>
			<s:hidden name="claimYNIdList[0]" id="claimYN" value=""/>
			<s:hidden name="claimAmountList[0]" id="claimAmount" value=""/>
			<s:hidden name="customerId"/>
			
		</s:form>
	</s:if>
	<s:elseif test="'showPrSummary'.equalsIgnoreCase(display)">
        <s:form id="motor" name="motor" method="post" theme="simple" action="insertOptionCoverMotor.action">
        <div class="row">
			<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
				<span style="color:red;"><s:actionerror/></span>
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
								  <button type="button" title="Personal Information" class="btnGroupMargin btn btn-lg btn-primary" disabled="disabled"><i class="fa fa-users"></i></button>
								  <button type="button" title="Policy Information" class="btnGroupMargin btn btn-lg btn-primary" disabled="disabled"><i class="fa fa-file-text-o"></i></button>
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
				<div class="panel panel-primary">
        			<div class="panel-heading txtB">
        				<s:text name="motor.policyInfo" />
        			</div>
        			<div class="panel-body">
        				<div class="row">
        					<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
        						<span class="text">	<s:text name="motor.quoteNo" /> </span> &nbsp; : &nbsp; <s:property value="quoteNo"/> 
							</div>
							<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
        						<span class="text"> <s:text name="motor.quoteDate" /> </span> &nbsp; : &nbsp; <s:property value="quoteDate"/>
							</div>
							<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
        						<span class="text"> <s:text name="motor.product" /> </span> &nbsp; : &nbsp; <s:property value="product"/> 
							</div>
							<!-- <div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
        						<span class="text"> <s:text name="motor.customerName"  /> </span> &nbsp; : &nbsp; <s:property value="title"/>.<s:property value="customerName"/>&nbsp;<s:property value="custLastName"/>
							</div>
							<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
								<span class="text"> <s:text name="motor.email" /> </span> &nbsp; : &nbsp; <s:property value="email"/>
							</div> -->
							<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
								<span class="text"> <s:text name="motor.policyStartDt" /> </span> &nbsp; : &nbsp; <s:property value="policyStartDate"/>
							</div>
							<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
								<!--<span class="text"> <s:text name="motor.policyEndDt" /> </span> &nbsp; : &nbsp; <s:property value="policyEndDatePeriod"/>-->
								<span class="text"> <s:text name="motor.policyEndDt" /> </span> &nbsp; : &nbsp; <s:property value="policyEndDate"/>
							</div>
						</div>
						<div class="row">
						 	<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
				        		<font color="red" size="5px">Currency&nbsp;:&nbsp;<s:property value="currencyType"/></font>
				        	</div>
				        </div>
						<div class="row">
        					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
        						<s:set name="multiVehicleDtls" value="%{multiVehicleDetails}"/>
								<table cellpadding="1" class="footable" cellspacing="1" border="1">
									<thead>
								        <tr>
											<th style="width:5%;"><s:label value="S.No." /></th>
											<th style="width:15%;"><s:text name="motor.vehicleUsage"/></th>
											<th style="width:20%;"><s:text name="motor.make" /></th>
											<th style="width:20%;"><s:text name="motor.model" /></th>
											<th style="width:15%;"><s:text name="motor.typeOfBody" /></th>
											<th style="width:15%;"><s:text name="motor.sumInsured" /></th>
											
										</tr>
									</thead>
									<tbody>
										<s:iterator value="#multiVehicleDtls" var="view" status="status">
											<tr>
												<td align="center"><s:property value="#status.count" /></td>
												<td align="center"><s:property value="#view.VEHICLETYPE_DESC" /></td>
												<td align="center"><s:property value="#view.MAKE_NAME" /></td>					
												<td align="center"><s:property value="#view.MODEL_NAME" default=" " /> </td>
												<td align="center"><s:property value="#view.TYPE_OF_BODY_NAME" /></td>
												<td align="right"><s:property value="getText('{0,number,#,##0.00}',{#view.SUMINSURED_VALUE_LOCAL})"/></td>
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
				<div class="panel panel-primary">
        			<div class="panel-heading">
        				<div class="row">
							<div class="col-xs-12 col-sm-12 col-md-6 col-lg-4">
								<s:text name="motor.premiumInfo" />
							</div>
							<div class="col-xs-12 col-sm-12 col-md-8 col-lg-8"><span style="color: yellow;"> <s:text name="Choose one cover to buy" /> </span></div>
						</div>
        			</div>
        			<div class="panel-body">
        				<div class="row" align="center">
        					<s:if test='referralMsgs!=null && referralMsgs.length()>0'>
        						<font color="red" size="3">	
					 				<s:text name="motor.referralMessage"/> 
					 				<%--<s:property value="referralMsg"/>--%> 
					 				<s:text name="referralMsgs"/>
				 				</font>
        					</s:if>
        				</div>
        				<div id="errorMessageDiv" align="center" style="color: red;">
        				</div>
        				<div>
       						<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
       						<span style="font-size: 12px;font-weight: normal;">
	        					 <a data-toggle="modal" data-refresh="true" href="#" data-target="#modalStdLimits" style="color: red;" >Click to view Standard Limits</a> 
	        				</span>
       						<div id="modalStdLimits" class="modal fade" role="dialog">
									<div class="modal-dialog">
										<div class="modal-content">
											<div class="modal-header">
												<button type="button" class="close" data-dismiss="modal">&times;</button>
												<h4 class="modal-title" align="center">Standard Limits</h4>
											</div>
											<div class="modal-body">
												<ul>
													<s:iterator value="conditionClausesList" var="ccVar" status="ccStatus">
														<li> <s:property value="#ccVar.CONDITION_DESC"/> </li>
													</s:iterator>
												</ul>
											</div>
										</div>
									</div>
								</div>								
        						<div class="text">
        							<s:text name="motor.doyouwanttoincreaseCombinedThirdPartyLiability"/><font color="red">*</font>
        							&nbsp;&nbsp;&nbsp;<s:radio name="tpLiablityYN" id="tpLiablityYN" list="#{'Y':'Yes','N':'No'}" onchange="toggleTpLiablity(this.value);" disabled="#disable1" value='%{"Y".equals(tpLiablityYN)?"Y":"N"}'/>        										        				
        						</div>
        						<!--  <div id="modalStdLimits" class="modal fade" role="dialog">
									<div class="modal-dialog">
										<div class="modal-content">
											<div class="modal-header">
												<button type="button" class="close" data-dismiss="modal">&times;</button>
												<h4 class="modal-title" align="center">Standard Limits</h4>
											</div>
											<div class="modal-body">
												<ul>
													<s:iterator value="conditionClausesList" var="ccVar" status="ccStatus">
														<li> <s:property value="#ccVar.CONDITIONS"/> </li>
													</s:iterator>
												</ul>
											</div>
										</div>
									</div>
								</div>  -->
        					</div>
        					<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
        						<div class="text"><s:text name="Combined Third Party Liability Amount"/><font color="red">*</font></div>
        						<div class="tbox">
        							<s:textfield name="tpLiablityAmount" id="tpLiablityAmount" cssClass="inputBox tooltipContent" cssStyle="width:75%" disabled='%{#disable1||!"Y".equals(tpLiablityYN)?"true":"false"}' onkeyup="checkDecimals10_2(this);" value='%{(tpLiablityAmount==null || "".equals(tpLiablityAmount))?"":tpLiablityAmount}' data-content='%{"USD".equals(currencyType)?"A combination of Bodily injury/death and property damage Liability claims resulting from the accident. Should be above USD 7,915.57 and charged at a rate of 0.5% limited to USD 43,975.37":"A combination of Bodily injury/death and property damage Liability claims resulting from the accident. Should be above ZMW 90, 000 & charged at a rate of 0.5% limited to ZMW 500,000."}' />
        							<b>&nbsp;<s:property value="currencyType"/></b>
        						</div>
        					</div>
        					<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
        						<div class="text">&nbsp;</div>
								<div class="tbox">
									<button type="button" class="btn btn-danger" style="width: 100%;" onclick="getComparisionDetails(this.form);">Calculate</button>
								</div>
        					</div>
       					</div>
       					<br class="clear"/>
       					<div style="color: red;">
        					<s:label>Please click the “add” button</s:label>&nbsp;<img src="${pageContext.request.contextPath}/images/add_cover.png" height="24" width="24" >&nbsp;<s:label>to add Additional Covers in the quotations below</s:label>
        				</div>
       					<br class="clear"/>
						<div id="comparisionDetailsAjax">
							<s:include value="/pages/motor/premiumComparision.jsp"/>
       					</div>
        				<div class="row" align="center">
        					<s:submit type="button" name="close"  key="Back" cssClass="btn btn-sm btn-danger" onclick="getBack('newQuote')"/>
        					<s:hidden name="brokerCode"/>
        					<s:hidden name="applicationNo"/>
							<s:hidden name="quoteNo"/>
							<s:hidden name="custdob"/>
							<s:hidden name="quoteStatus"/>
							<s:hidden name="endTypeId"/>
							<s:hidden name="policyNo"/>
							<s:hidden name="brokerCompany"/>
							<s:hidden name="custName"/>
							<s:hidden name="custmerId"/>
							<s:hidden name="vehicleId"/>
        				</div>        				
        			</div>
        		</div>
        	</div>
        </div>
        </s:form>
    </s:elseif>
    <s:elseif test='"editCovRate".equalsIgnoreCase(display)'>
        <s:form id="motor" name="motor" method="post" theme="simple" action="updateCovRateMotor.action">
        <div class="row">
			<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
				<span style="color:red;"><s:actionerror/></span>
			</div>
		</div>
		<div class="row">
			<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
				<div class="panel panel-default">
					<div class="panel-heading">
						<div class="row">
							<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
								<div class="btn-group" role="group" aria-label="...">							
								  <button type="button" title="Customer Information" title="Customer Information" class="btnGroupMargin btn btn-lg btn-warning"><i class="fa fa-user"></i></button>
								  <button type="button" title="Premium Comparison" class="btnGroupMargin btn btn-lg btn-warning""><i class="fa fa-eye"></i></button>
								  <button type="button" title="Policy Information" class="btnGroupMargin btn btn-lg btn-success active"><i class="fa fa-users"></i></button>
								</div>
							</div>
							<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6 text-success">
								<span class="textHeading"><s:text name="label.premiumInfo" /> </span> 
							</div>
						</div>
												
					</div>		
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
				<div class="panel panel-primary">
        			<div class="panel-heading txtB">
        				<s:text name="motor.policyInfo" />
        			</div>
        			<div class="panel-body">
        				<div class="row">
        					<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
        						<span class="text"> <s:text name="motor.quoteNo"  /> </span> &nbsp; : &nbsp; <s:property value="quoteNo"/> 
							</div>
							<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
        						<span class="text"> <s:text name="motor.quoteDate" /> </span> &nbsp; : &nbsp; <s:property value="quoteDate"/>
							</div>
							<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
        						<span class="text"> <s:text name="motor.product" /> </span> &nbsp; : &nbsp; <s:property value="product"/> 
							</div>
							<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
        						<span class="text"> <s:text name="motor.customerName"  /> </span> &nbsp; : &nbsp; <s:property value="title"/>.<s:property value="customerName"/>&nbsp;<s:property value="custLastName"/>
							</div>
							<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
								<span class="text"> <s:text name="motor.email" /> </span> &nbsp; : &nbsp; <s:property value="email"/>
							</div>
							<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
								<span class="text"> <s:text name="motor.policyStartDt" /> </span> &nbsp; : &nbsp; <s:property value="policyStartDate"/>
							</div>
							<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
								<span class="text"> <s:text name="motor.policyEndDt" /> </span> &nbsp; : &nbsp; <s:property value="policyEndDatePeriod"/>
							</div>
						</div>
						<div class="row">
						 	<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
				        		<font color="red" size="5px">Currency&nbsp;:&nbsp;<s:property value="currencyType"/></font>
				        	</div>
				        </div>
						<br/>
						<div class="row">
							<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
								<s:set name="multiVehicleDtls" value="%{multiVehicleDetails}"/>
								<table cellpadding="1" class="footable" cellspacing="1" border="1">
									<thead>
								        <tr>
											<th style="width:5%;"><s:label value="S.No." /></th>
											<th style="width:15%;"><s:text name="motor.vehicleUsage"/></th>
											<th style="width:20%;"><s:text name="motor.make" /></th>
											<th style="width:20%;"><s:text name="motor.model" /></th>
											<th style="width:15%;"><s:text name="motor.typeOfBody" /></th>
											<th style="width:15%;"><s:text name="motor.sumInsured" /></th>
											<th style="width:10%;"> <s:text name="Modify Rate" /> </th>
										</tr>
									</thead>
									<tbody>
										<s:iterator value="#multiVehicleDtls" var="view" status="status">
											<tr>
												<td align="center"><s:property value="#status.count" /></td>
												<td align="center"><s:property value="#view.VEHICLETYPE_DESC" /></td>
												<td align="center"><s:property value="#view.MAKE_NAME" /></td>					
												<td align="center"><s:property value="#view.MODEL_NAME" default=" " /> </td>
												<td align="center"><s:property value="#view.TYPE_OF_BODY_NAME" /></td>
												<td align="right"> <s:property value="getText('{0,number,#,##0.00}',{#view.SUMINSURED_VALUE_LOCAL})"/> </td>
												<td align="center">
													<button type="button" name="vehicleEditButton" id="vehicleEditButton" onclick="editCoverRate(this.form,'${view.VEHICLE_ID}');" class="btn btn-sm btn-danger"> <i class="glyphicon glyphicon-pencil"></i> </button>
												</td>
											</tr>
										</s:iterator>
									</tbody>
								</table>
							</div>
						</div>
						<s:if test="premiumInfo.size()>0">
						<br/>
						<div class="row">
							<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
								<div class="panel panel-primary">
				        			<div class="panel-heading txtB">
										<s:text name="motor.premiumInfo"/>
				        			</div>
				        			<div class="panel-body">
				        				<div class="boxcontent">
				        					<table class="footable" width="100%">
				        						<tbody>
					        						<s:set name="groupId" value=""/>
								                    <s:set var="preAmt" value="0.0" scope="page"/>
								                    <s:iterator value="premiumInfo" var="prInfo" status="stat">
					        						
								                    <s:if test="%{#groupId != #prInfo.GROUP_ID}">
								                    <s:set name="groupId" value="%{#prInfo.GROUP_ID}"/>
								                    <tr class="tableHeading">
								                    	<td colspan="5"><s:property value="%{#prInfo.GROUP_DESC_ENGLISH}"/></td>
								                    </tr>
								                    </s:if>
					        						<tr>
					        							<td><s:hidden name="coverId[%{#stat.count-1}]" value="%{#prInfo.POLICYTYPE_COVERID}"/></td>
					        							<td><s:property  value="%{#prInfo.OPCOVER_DESC_ENGLISH}"/></td>
					        							<td>
					        								<s:property value="getText('{0,number,#,##0.00}',{#prInfo.SUM_INSURED})"/>
						                             		<s:hidden name="sI[%{#stat.count-1}]"  cssClass="inputBox" value="%{#prInfo.SUM_INSURED}" cssStyle="text-align:right;"/>
					        							</td>
					        							<td>
					        								<s:textfield name="baseRate[%{#stat.count-1}]" cssClass="inputBox" value="%{#prInfo.RATE}" cssStyle="text-align:right;" maxLength="16" onkeyup="checkDecimals(this);" />
					        							</td>
					        							<td>
					        								<s:property value="getText('{0,number,#,##0.00}',{#prInfo.PREMIUM})"/>
						                            		<s:set var="preAmt" value="%{@java.lang.Double@parseDouble(#attr.preAmt)+@java.lang.Double@parseDouble(#prInfo.PREMIUM)}" scope="page"/>
					        							</td>
					        						</tr>        						
					        						</s:iterator>
				        						</tbody>
				        					</table>
				        					<br class="clear"/>
				        				</div>
				        			</div>
				        		</div>
							</div>
						</div>
						</s:if>
        			</div>
        		</div>
        	</div>
        </div>
        <div class="row">
			<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" align="center">
				<input type="button" name="Submit1" class="btn btn-sm btn-danger" value="Back" onclick="getBack('newQuote')"/> &nbsp;&nbsp;&nbsp;
	            <input type="button" name="Submit2" class="btn btn-sm btn-info" value="Calculate" onclick="this.form.actionType.value='getCalculate';this.form.submit();"/>&nbsp;&nbsp;&nbsp;
				<input type="button" name="Submit3" class="btn btn-sm btn-success" value="Proceed" onclick="this.form.actionType.value='getQuote';this.form.submit();"/>
				<s:hidden name="applicationNo"/>
		 		<s:hidden name="quoteNo"/>
		 		<s:hidden name="quoteStatus"/>
		 		<s:hidden name="actionType" />
		 		<s:hidden name="endTypeId"/>
				<s:hidden name="policyNo"/>
                <s:hidden name="brokerCompany"/>
                <s:hidden name="custName"/>
                <s:hidden name="vehicleId"/>
                <s:hidden name="currencyType"/>
			</div>
		</div>
        </s:form>
    </s:elseif>
    <s:elseif test="'policyInfo'.equalsIgnoreCase(display)">
        <s:set var="multiVehicleDtls" value="%{multiVehicleDetails}"/>
        <s:set var="totPremium" value="0"/>
        <s:form id="motor" name="motor" method="post" theme="simple" action="getGeratePolicyMotor.action">
        <div class="row">
			<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
				<span style="color:red;"><s:actionerror/></span>
			</div>
		</div>
		<div class="row">
			<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
				<div class="panel panel-default">
					<div class="panel-heading">
						<div class="row">
							<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
								<div class="btn-group" role="group" aria-label="...">
									<button type="button" title="Quote Information" class="btnGroupMargin btn btn-lg btn-warning"><i class="fa fa-user"></i></button>
								  	<button type="button" title="Premium Comparison" class="btnGroupMargin btn btn-lg btn-warning"><i class="fa fa-eye"></i></button>
								  	<button type="button" title="Personal Information" class="btnGroupMargin btn btn-lg btn-warning"><i class="fa fa-users"></i></button>
								  	<button type="button" title="Policy Information" class="btnGroupMargin btn btn-lg btn-primary active" disabled="disabled"><i class="fa fa-file-text-o"></i></button>
								</div>
							</div>
							<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6 text-success">
								<span class="textHeading"><s:text name="label.policyInfo" /> </span> 
							</div>
						</div>	
					</div>		
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
				<div class="panel panel-primary">
        			<div class="panel-heading txtB">
        				<s:text name="motor.policyInfo" />
        			</div>
        			<div class="panel-body">
        				<div class="row">
        					<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
        						<span class="text"> <s:text name="motor.quoteNo"  /> </span> &nbsp; : &nbsp; <s:property value="quoteNo"/> 
							</div>
							<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
        						<span class="text"> <s:text name="motor.quoteDate" /> </span> &nbsp; : &nbsp; <s:property value="quoteDate"/>
							</div>
							<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
        						<span class="text"> <s:text name="motor.product" /> </span> &nbsp; : &nbsp; <s:property value="product"/> 
							</div>
							<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
        						<span class="text"> <s:text name="motor.customerName"  /> </span> &nbsp; : &nbsp; <s:property value="title"/>.<s:property value="customerName"/>&nbsp;<s:property value="custLastName"/>
							</div>
							<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
								<span class="text"> <s:text name="motor.email" /> </span> &nbsp; : &nbsp; <s:property value="email"/>
							</div>
							<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
								<span class="text"> <s:text name="motor.policyStartDt" /> </span> &nbsp; : &nbsp; <s:property value="policyStartDate"/>
							</div>
							<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
								<span class="text"> <s:text name="motor.policyEndDt" /> </span> &nbsp; : &nbsp; <s:property value="policyEndDatePeriod"/>
							</div>
						</div>
						<div class="row">
						 	<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
				        		<font color="red" size="5px">Currency&nbsp;:&nbsp;<s:property value="currencyType"/></font>
				        	</div>
				        </div>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
				<s:include value="/pages/customer/customerDetailsMotor.jsp"></s:include>
			</div>
		</div>
		<div class="row">
			<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
				<s:if test='#session.user1 != "admin"'>
					<div class="panel panel-primary">
	        			<div class="panel-heading txtB">
	        				<s:text name="motor.additionalVehicleDetails" />
	        				<button type="button" class="btn btn-sm btn-primary" data-toggle="modal" data-refresh="true" href="${pageContext.request.contextPath}/getHelpInfoMotor.action?helpType=ADDITIONAL_VEHICLE_DETAILS" data-target="#modaladdVehicleDetails"> <img src="${pageContext.request.contextPath}/images/Help.png" width="20" height="20"/> </button>
							<div class="modal fade" data-refresh="true" id="modaladdVehicleDetails" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
								<div class="modal-dialog modal-lg">
									<div class="modal-content">
										<div class="modal-header">
											<h4 class="modal-title"> <s:label key="motor.additionalVehicleDetails"/> </h4>
										</div>
										<div class="modal-body">
											<div class="te"></div>
										</div>
									</div>
								</div>
							</div>
	        			</div>
	        			<div class="panel-body">
	        				<ul class="nav nav-tabs responsive">
		        				<s:iterator value="#multiVehicleDtls" var="var" status="status">
			        				<li id="tab${status.count}" class='${status.index=="0"?"active":""}'>
								  		<a href="#tabBody${status.count}" data-toggle="pill">
								  			Vehicle&nbsp;${status.count}
										</a>
									</li>
								</s:iterator>
							</ul>
							<br class="clear"/>
							<div class="tab-content responsive">
								<s:iterator value="#multiVehicleDtls" var="var" status="status">
									<div id="tabBody${status.count}" class='tab-pane fade ${status.index=="0"?"in active":""}'>
										<div class="row">
       										<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
       											<table cellpadding="1" class="footable" cellspacing="1" border="1">
													<thead>
														<tr>
															<th style="width:15%;"><s:text name="motor.vehicleUsage"/></th>
															<th style="width:20%;"><s:text name="motor.make" /></th>
															<th style="width:20%;"><s:text name="motor.model" /></th>
															<th style="width:10%;"><s:text name="motor.typeOfBody" /></th>
															<th style="width:13%;"><s:text name="motor.sumInsured" /></th>
														</tr>
													</thead>
													<tbody>
														<tr>
															<td align="center"><s:property value="#var.VEHICLETYPE_DESC" /></td>
															<td align="center"><s:property value="#var.MAKE_NAME" /></td>					
															<td align="center"><s:property value="#var.MODEL_NAME" default=" " /> </td>
															<td align="center"><s:property value="#var.TYPE_OF_BODY_NAME" /></td>
															<td align="right"> <s:property value="getText('{0,number,#,##0.00}',{#var.SUMINSURED_VALUE_LOCAL})"/> </td>
														</tr>
													</tbody>
												</table>
       										</div>
       									</div>																		
										<br class="clear"/>
										<div class="row">
       										<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
       											<div class="text"><s:text name="motor.regNo" /><font color="red">*</font></div>
       											<div class="tbox">
       												<s:textfield name="regNoList[%{#status.index}]" id="regNoList[%{#status.index}]" cssClass="inputBox tooltipContent" data-content="Your vehicle registration number as shown on white book without spaces e.g. ALC3245" maxLength="20" disabled="#endtDisable"/>
       											</div>
       										</div>
       										<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
       											<div class="text"><s:text name="motor.chassisNo" /><font color="red">*</font></div>
       											<div class="tbox">
       												<s:textfield name="chassisNoList[%{#status.index}]" id="chassisNoList[%{#status.index}]" cssClass="inputBox tooltipContent" data-content="Your vehicle Chassis number as indicated on white book without spaces. eg. AE110-0011442" maxLength="20" disabled="#endtDisable"/>
       											</div>
       										</div>
       										<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
       											<div class="text"><s:text name="motor.engineNo" /><font color="red">*</font></div>
       											<div class="tbox">
       												<s:textfield name="engineNoList[%{#status.index}]" id="engineNoList[%{#status.index}]" cssClass="inputBox tooltipContent" data-content="Your vehicle Engine number as indicated on white book without spaces. eg. 4S-1394811" maxLength="20" disabled="#endtDisable"/>
       											</div>
       										</div>
       									</div>
       									<div class="row">
       										<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
       											<div class="text"><s:text name="motor.vehicleColour" /></div>
       											<div class="tbox">
       												<s:select name="vehicleColourIdList[%{#status.index}]" id="vehicleColourIdList[%{#status.index}]" list="vehicleColourList" listKey="CODE" listValue="CODEDESC" headerKey="" headerValue="---Select---" cssClass="inputBoxS tooltipContent" data-content="Your vehicle color as indicated on white book" disabled='#endtDisable'/>
       											</div>
       										</div>
       										<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
       											<div class="text"><s:text name="motor.leasedYN" /><font color="red">*</font></div>
       											<div class="tbox">
       												<s:radio name="leasedYNIdList[%{#status.index}]" id="leasedYNIdList[%{#status.index}]" list="#{'Y':'Yes','N':'No'}" value='%{(leasedYNIdList==null||leasedYNIdList[#status.index]==null||"".equals(leasedYNIdList[#status.index]))?"N":leasedYNIdList[#status.index]}' onclick="toggleLeasedYN(this.value,'%{#status.index}');" disabled="#endtDisable" cssClass="tooltipContent" data-content="Indicate whether vehicle has been leased or not" />
       											</div>        											
       										</div>
       										<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
       											<div class="text"><s:text name="motor.bankOfFinance" /><font color="red">*</font></div>
       											<div class="tbox">
       												<s:select name="bankOfFinanceIdList[%{#status.index}]" id="bankOfFinanceIdList[%{#status.index}]" list="bankOfFinanceList" listKey="CODE" listValue="CODEDESC" headerKey="" headerValue="---Select---" cssClass="inputBoxS tooltipContent" data-content="If Leased, indicate the bank that financed the lease" disabled='%{(#endtDisable || leasedYNIdList[#status.index]==null||"".equals(leasedYNIdList[#status.index])||"N".equals(leasedYNIdList[#status.index]))?"true":"false"}'/>
       											</div>
       										</div>
       									</div>
       									<div class="row">
       										<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4" <s:if test='%{"0".equals(noClaimBonusIdList[#status.index]) || "".equals(noClaimBonusIdList[#status.index])}'>style="display:none;"</s:if> >
       											<div class="text"><s:text name="motor.prevPolicyNo" /></div> 
       											<div class="tbox">
       												<s:textfield name="prevPolicyNoList[%{#status.index}]" id="prevPolicyNoList[%{#status.index}]" cssClass="inputBox tooltipContent" maxLength="30" disabled='(#endtDisable || "0".equals(noClaimBonusIdList[#status.index]))?"true":"false"' value='%{"0".equals(noClaimBonusIdList[#status.index])?"":prevPolicyNoList[#status.index]}' data-content="Policy number you previously held with Madison or another Insurer" />
       											</div>
       										</div>
       										<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4" <s:if test='%{"0".equals(noClaimBonusIdList[#status.index]) || "".equals(noClaimBonusIdList[#status.index])}'>style="display:none;"</s:if> >
       											<div class="text"><s:text name="motor.prevInsExpiryDate" /><s:text name="(DD/MM/YYYY)" /></div>
       											<div class="tbox">
       												<s:textfield name="prevExpiryDateList[%{#status.index}]" id="prevExpiryDateList[%{#status.index}]" cssClass="inputBox datePicker prevExpiryDateList tooltipContentL" maxLength="10" disabled='(#endtDisable || "0".equals(noClaimBonusIdList[#status.index]))?"true":"false"' value='%{"0".equals(noClaimBonusIdList[#status.index])?"":prevExpiryDateList[#status.index]}' data-content="Date your previous policy expired" />
       											</div>
       										</div>
       										<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4" <s:if test='%{"0".equals(noClaimBonusIdList[#status.index]) || "".equals(noClaimBonusIdList[#status.index])}'>style="display:none;"</s:if> >
       											<div class="text"><s:text name="motor.prevInsCompany" /><font color="red">*</font></div>
       											<div class="tbox">
       												<s:select name="prevInsCompanyIdList[%{#status.index}]" id="prevInsCompanyIdList[%{#status.index}]" list="insCompanyList" listKey="CODE" listValue="CODEDESC" headerKey="" headerValue="---Select---" cssClass="inputBoxS tooltipContent" data-content="Insurance Company that you took out your previous policy with" disabled='(#endtDisable || "0".equals(noClaimBonusIdList[#status.index]))?"true":"false"' value='%{"0".equals(noClaimBonusIdList[#status.index])?"":prevInsCompanyIdList[#status.index]}'/>
       											</div>
       										</div>
       									</div>
       									<div class="row">
       										<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
												<div class="text"><s:text name="motor.cubicCapacity" /></div>
												<div class="tbox">
													<s:textfield name="cubicCapacityList[%{#status.index}]" id="cubicCapacity[%{#status.index}]" cssClass="inputBox tooltipContent" data-content="The size of your vehicle’s engine in litres e.g. 2000" maxLength="5" onkeyup="checkNumbers(this);" disabled="#disable1"/>
												</div>
											</div>
       										<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
					       						<div class="text"><s:text name="motor.driverId" /><font color="red">*</font></div>
												<div class="tbox">
													<s:textfield name="driverIdList[%{#status.index}]" id="driverIdList[%{#status.index}]" cssClass="inputBox tooltipContent" data-content="Valid driver licence number for driver of the vehicle entered above" maxlength="20" disabled="#disable1"/>
												</div>
					       					</div>
					       					<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
       											<div class="text"><s:text name="Documents" /><font color="red">*</font></div>
       											<div class="tbox">
       												<s:submit type="button" value="Attach Documents" onclick="return uploadDocuments('%{vehicleIdList[#status.index]}');" cssClass="btn btn-sm btn-danger tooltipContent" data-content="Attach supporting documentse.g white book, drivers licence, letter of no claim record,  pictures of the vehicle" />
       											</div>
       										</div>
       									</div>
       										<%--
       										<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
       											<div class="text"><s:text name="Electrical Details" /><font color="red">*</font></div>
       											<div class="tbox">
       												<s:submit type="button" value="Add Details" onclick="return electricalPopup();" cssClass="btn btn-sm btn-primary"/>
       											</div>
       										</div>
       										<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
       											<div class="text"><s:text name="Non Electrical Details" /><font color="red">*</font></div>
       											<div class="tbox">
       												<s:submit type="button" value="Add Details" onclick="return nelectricalPopup();" cssClass="btn btn-sm btn-primary"/>
       											</div>
       										</div>
       										<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
      											<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
       											<div class="text"><s:text name="motor.plateCharacter" /><font color="red">*</font></div>
       											<div class="tbox">
       												<s:select name="plateNo1[%{#status.index}]" id="plateNo1[%{#status.index}]" list="plateCharacterList" disabled="#endtDisable" cssClass="inputBoxS" cssStyle="width:20%; padding: 0px;"/>
													<s:select name="plateNo2[%{#status.index}]" id="plateNo2[%{#status.index}]" list="plateCharacterList" disabled="#endtDisable" cssClass="inputBoxS" cssStyle="width:20%; padding: 0px;"/>
													<s:select name="plateNo3[%{#status.index}]" id="plateNo3[%{#status.index}]" list="plateCharacterList" disabled="#endtDisable" cssClass="inputBoxS" cssStyle="width:20%; padding: 0px;"/>
													<s:textfield name="plateNo4[%{#status.index}]" id="plateNo4[%{#status.index}]" cssClass="inputBox" disabled="#endtDisable" maxLength="4" onkeypress="return IsNumeric(event);" cssStyle="width:30%; "/>
													<s:hidden name="plateCharacterIdList[%{#status.index}]" id="plateCharacterIdList[%{#status.index}]"/>
       											</div>
       										</div>
      											<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
       											<div class="text"><s:text name="motor.vehicleRegLoc" /><font color="red">*</font></div>
       											<div class="tbox">
       												<s:select name="vehicleRegLocIdList[%{#status.index}]" id="vehicleRegLocIdList[%{#status.index}]" list="cityList" listKey="CODE" listValue="CODEDESC" headerKey="" headerValue="---Select---" cssClass="inputBoxS" disabled="#endtDisable"/>
       											</div>
       										</div>
       										<div class="textfield33">
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
       										</div>
       										<s:hidden name="insNameArabicList[%{#status.index}]"/>
											<s:hidden name="insAddressArabicList[%{#status.index}]"/>
       										--%>
       									<s:hidden name="noClaimBonusIdList[%{#status.index}]"/>
       									<s:hidden name="vehicleIdList[%{#status.index}]"/>
									</div>
								</s:iterator>
							</div>
			        	</div>
					</div>
				</s:if>
	       	</div>
		</div>
		<s:if test="premiumInfo.size()>0">
			<div class="row">
				<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
					<div class="panel panel-primary">
	        			<div class="panel-heading txtB">
							<s:text name="label.quoteDetail" />
	        			</div>
	        			<div class="panel-body">
	        				<div class="row">
	        					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
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
												<s:hidden name="sI[%{#stat.count-1}]"  cssClass="inputBox" value="%{#prInfo.SUM_INSURED}" cssStyle="text-align:right;"/>
											</td>
											<td align="left" width="25%">
												<s:textfield name="baseRate[%{#stat.count-1}]" cssClass="inputBox tooltipContent" data-content="Base Rate" value="%{#prInfo.RATE}" size="11" cssStyle="text-align:right; width:100%;" maxLength="16" onkeyup="checkDecimals(this);" readonly="true"/>
											</td>
											<td align="right" width="20%"> 
												<s:property value="getText('{0,number,#,##0.00}',{#prInfo.PREMIUM})"/>
												<s:set var="preAmt" value="%{@java.lang.Double@parseDouble(#attr.preAmt)+@java.lang.Double@parseDouble(#prInfo.PREMIUM)}" scope="page"/>
											</td>
										</tr>								
										</tbody>
										</s:iterator>
										<%--<s:if test="@java.lang.Double@parseDouble(miniPre) >  @java.lang.Double@parseDouble(#attr.preAmt)" >
										<s:set var="preAmt" value="%{@java.lang.Double@parseDouble(miniPre)}" scope="page"/>
										</s:if>
										--%><tbody>
										<tr>
											<td></td>
											<td></td>									
											<td></td>
											<td>
												<font style="float:left;"><s:text name="motor.Premium"/></font>
												<b style="float:right;">[<s:property value="currencyType"/>]</b>
											</td>
											<td width="100%" align="right">
												<%-- &nbsp;&nbsp;&nbsp;
												<s:textfield name="premium" id="premium" cssClass="inputBox" value="%{#attr.preAmt}" onkeyup="checkDecimals(this);" maxlength="11" cssStyle="text-align:right;" readonly="true"/>
												--%>
												<s:textfield name="premium" id="premium" cssClass="inputBox tooltipContent" data-content="Premium" value="%{getText('{0,number,0.00}',{#attr.preAmt})}" onkeyup="checkDecimals(this);" maxlength="11" cssStyle="text-align:right;" readonly="true"/>
											</td>
										</tr>
										<s:if test='#session.user1 == "admin" || quoteStatus == "RA"'>
										<tr>
											<td></td>
											<td></td>									
											<td></td>
											<td>
											<font style="float:left;"><s:text name="motor.loadingOrDiscountPremium"/></font>
											<b style="float:right;">[<s:property value="currencyType"/>]</b>
											</td>
											<td align="right">
												<s:select list="#{'+':'+','-':'-'}" name="sign" onchange="getTotalPremium(this.form)" disabled="%{quoteStatus=='RA' && #session.user1 != 'admin'?'true':'false'}" cssClass="inputBox" cssStyle="width:30%;"/>&nbsp;&nbsp;&nbsp;
					                            <s:textfield name="loadOrDiscPremium" id="loadOrDiscPremium" cssClass="inputBox tooltipContent" data-content="Loading or Discount Premium" disabled="%{quoteStatus=='RA' && #session.user1 != 'admin'?'true':'false'}" onkeyup="checkDecimals4_2(this);getTotalPremium(this.form);" maxlength="11" cssStyle="text-align:right;width:60%;"/>
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
											<td>
												<font style="float:left;"><s:text name="motor.policyFee"/></font>
												<b style="float:right;">[<s:property value="currencyType"/>]</b>
											</td>
											<td width="100%" align="right">
												<s:textfield name="policyFee" id="policyFee" cssClass="inputBox tooltipContent" data-content="Policy Fee" onkeyup="checkDecimals(this);" maxlength="11" cssStyle="text-align:right;" readonly="true"/>
											</td>
										</tr>
										<tr>
											<td></td>
											<td></td>									
											<td></td>
											<td>
												<font style="float:left;"><s:text name="motor.totalPremiumPayable" /></font>
												<b style="float:right;">[<s:property value="currencyType"/>]</b>
											</td>
												
											<td width="100%" align="right">
												<%-- 
												<s:textfield name="totalPremium" id="totalPremium" cssClass="inputBox" value="%{@java.lang.Double@parseDouble(#attr.preAmt)+@java.lang.Double@parseDouble(policyFee)}" onkeyup="checkDecimals(this);" maxlength="11" cssStyle="text-align:right;" readonly="true"/>
												--%>
												<s:set var="totPremium" value="%{getText('{0,number,0.00}',{@java.lang.Double@parseDouble(#attr.preAmt)+@java.lang.Double@parseDouble(policyFee)})}"/>
												<s:textfield name="totalPremium" id="totalPremium" cssClass="inputBox tooltipContent" data-content="Total Premium" value="%{#totPremium}" onkeyup="checkDecimals(this);" maxlength="11" cssStyle="text-align:right;" readonly="true"/>
											</td>
										</tr>
										<s:if test='#session.user1 == "admin" || (!"".equals(adminRemarks)&&(adminRemarks!=null)&& #session.user1 != "admin")'>
										<tr>
											<td></td>
											<td></td>									
											<td></td>
											<td align="right"><s:text name="motor.specialCondition"/></td>
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
											<td align="right"><s:text name="motor.referralStatus"/></td>
											<td><s:radio list="#{'Y':'Accept','N':'Reject','A':'Pending'}" name="adminRefStatus" value="%{adminRefStatus==null?'A':adminRefStatus}" /></td>
										</tr>
										<br /></s:if>
										</tbody>	
									</table>
		       						<br class="clear"/>
		       					</div>
		       				</div>
		       			</div>
		       		</div>
				</div>
			</div>
			<s:if test='showReferralYN == "Y" && #session.user1 != "admin"'>
        		<div class="row">
	        		<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
	        			<div class="panel panel-primary">
		        			<div class="panel-heading txtB">
								<s:text name="motor.referalInfo" />
		        			</div>
		        			<div class="panel-body">
		        				<div class="row">
		        					<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
		        						<span class="text"> <s:text name="motor.policyStartDt"/> </span> &nbsp; : &nbsp; <s:property value="policyStartDate"/>
		        					</div>
		        					<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">								        						
		        						<span class="text"> <s:text name="motor.policyEndDt"/> </span> &nbsp; : &nbsp; <s:property value="policyEndDate"/>
		        					</div>
		        				</div>
		        				<div class="row">
		        					<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
		        						<div class="text"><s:text name="motor.referralYN"/></div>
     											<div class="tbox">
     												<s:radio list="#{'Y':'Yes','N':'No'}" name="referralYN" id="referralYN"  onclick="disablePolicyOption(this.value);"/>
     											</div>					        						
		        					</div>
		        					<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
		        						<div class="text"><s:text name="motor.comments"/></div>
     											<div class="tbox">
     												<s:textarea name="referralComments" id="referralComments" cssClass="inputBoxA tooltipContent" data-content="Referral Comments" cssStyle="width: 100%;" onkeyup="textLimit(this,'200')"/>
     											</div>
		        					</div>
		        				</div>
		        			</div>
		        		</div>
	        		</div>
	        	</div>
	        	<s:hidden name="showReferralYN"/>
        	</s:if>
        	<s:if test='"Y".equals(paymentYN) && #session.user1 != "admin"'>
				<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
					<s:include value="/pages/payment/paymentInfo.jsp"/>
				</div>
	        </s:if>
			<div class="row">
				<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" align="center">
					<s:if test='#session.user1 != "admin"'>
						<s:if test='quoteStatus=="RA" || (endTypeId!=null && !"".equalsIgnoreCase(endTypeId)) || "2".equals(premiumType)'>
							<input type="button" name="Submit1" class="btn btn-sm btn-danger" value="Back" onclick="getBack('newQuote');" />
							&nbsp;&nbsp;&nbsp;
						</s:if>
						<s:else>
							<input type="button" name="Submit1" class="btn btn-sm btn-danger" value="Back" onclick="getBack('showSummarry');" />
							&nbsp;&nbsp;&nbsp;
						</s:else>
						<%--<input type="button" name="Submit2" class="btn" value="Save" onclick="this.form.actionType.value='getSave';this.form.submit();" />
						                  &nbsp;--%>
					</s:if>
					<s:else>
						<%--
						<s:submit type="button" name="close"  key="Back" cssClass="btn btn-sm btn-danger" onclick="getBack('editCovRate')"/>
						--%>
						<input type="button" name="Submit1" class="btn btn-sm btn-danger" value="Back" onclick="getBack('newQuote');" />
						&nbsp;&nbsp;&nbsp;
					</s:else>
						<input type="button" name="Submit3" class="btn btn-sm btn-success" value="Proceed" onclick="this.form.actionType.value='getQuote';disableForm(this.form,false,'');this.form.submit();"/>
					<s:hidden name="applicationNo"/>
					<s:hidden name="quoteNo"/>
					<s:hidden name="quoteStatus"/>
					<%--<s:hidden name="referralMsg"/>--%>
					<s:hidden name="actionType" />
					<s:hidden name="display" />
					<s:hidden name="endTypeId"/>
					<s:hidden name="policyNo"/>
	                <s:hidden name="brokerCompany"/>
	                <s:hidden name="custName"/>
	                <s:hidden name="vehicleId"/>
	                <s:hidden name="currencyType"/>
				</div>
			</div>
	       	</s:if>	
        </s:form>
    </s:elseif>
    <s:elseif test="'showQuoteInfo'.equalsIgnoreCase(display)">
		<s:form id="motor" name="motor" method="post"  action="getGeratePolicyMotor.action" theme="simple">
		<div class="table1">
			<div class="tablerow">
				<div class="panel panel-primary">
        			<div class="panel-heading txtB">
						<s:text name="motor.quoteInfo" />
        			</div>
        			<div class="panel-body">
        				<div class="boxcontent" align="center">
        					<div style="color:red;font-size: 15px;"><b><s:text name="motor.refInfo"/> Saved. Your Quote No is : <s:property value="quoteNo"/></b></div>
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
						<s:text name="quotation.quoteInfo" />
        			</div>
        			<div class="panel-body">
        				<div class="boxcontent" align="center">
        					<div style="color:red;font-size: 15px;"><b><s:text name="motor.refInfo"/> <s:property value="referralMsg"/></b></div>
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
		<s:elseif test="'newQuoteOtpLogin'.equalsIgnoreCase(display)">
			<s:form id="motor" name="motor" method="post"  theme="simple">
				<div class="panel panel-primary">
					<div class="panel-body">
						<div class="row">
							<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
								<s:if test="hasActionErrors()">
									<font color="red" style="list-style:none; "><s:actionerror cssStyle="list-style:none;"/></font>
								</s:if>
								<s:if test="hasActionMessages()">
									<s:actionmessage cssStyle="list-style:none; color:green;"/>
								</s:if>
							</div>
						</div>
						<div class="row">
							<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
								<div class="panel panel-primary">
									<div class="panel-heading">
										Registered User
									</div>
									<div class="panel-body">
										<div class="row">
											<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
						  						<div class="text"><s:text name="Registered Mobile No"/><font color="red">*</font></div>
						  						<div class="tbox">
						  							<s:textfield name="mobileNum" id="mobileNum" cssClass="inputBox tooltipContent empyCustDetails" data-content="Your primary mobile number without spaces e.g. 0977777777" maxLength="10" onkeyup="checkNumbers(this);" disabled="#disable1"/>
						  						</div>
						  					</div>
						  					<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4" align="center" style="margin-top: 17px;">
							            		<button type="button" class="btn btn-sm btn-success" onclick="funSubmitOtp('motor','registered')">Submit</button>
											</div>
										</div>
									</div>
								</div>
								<div class="panel panel-primary">
									<div class="panel-heading">
										New User
									</div>
									<div class="panel-body">
										<s:include value="/pages/customer/customerDetailsMotor.jsp"/>
										<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" align="center">
											<button type="button" class="btn btn-sm btn-success" onclick="funSubmitOtp('motor','new')">Submit</button>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" align="center">
			            		<s:submit name="Submit" type="submit" cssClass="btn btn-sm btn-danger" value='Back' onclick="funBackQuote()"/>
							</div>
						</div>
					</div>
				</div>
				<s:hidden name="applicationNo"/>
				<s:hidden name="quoteNo"/>
				<s:hidden name="product" id="product"/>
				<s:hidden name="policyStartDate" id="policyStartDate"/>
				<s:hidden name="policyEndDate" id="policyEndDate"/>
				<s:hidden name="quoteDate" id="quoteDate"/>
				<s:hidden name="currencyType" id="currencyType"/>
				<s:hidden name="branchCode" id="branchCode"/>
				<!--<s:hidden name="customerId" id="customerId"/>-->
				<s:hidden name="policyType" id="policyType"/>
				<s:hidden name="optionalCovers1"/>
				<s:hidden name="optionalCovers2"/>
				<s:hidden name="optionalCovers3"/>
			</s:form>
		</s:elseif>
	</div>
	<s:if test='"User".equalsIgnoreCase(userType)||"B2C".equalsIgnoreCase(#session.LoginType)'>
		<div class="col-xs-12 col-sm-12 col-md-3 col-lg-3">
			<div class="row">
				<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
					<s:include value="/pages/motor/sideBar.jsp"/>
				</div>
			</div>		
		</div>
	</s:if>
</div>
<script  type="text/javascript">

<s:if test='"Y".equals(paymentYN) && #session.user1 != "admin"'>
	<s:if test='"Y".equalsIgnoreCase(generatePolicyYN) || "viewPayment".equalsIgnoreCase(mode)'>
		getPaymentModeDetails('<s:property value="modeOfPayment"/>');
	</s:if>
</s:if>

try {
	function fnsubmit(action) {
		document.motor.action = "${pageContext.request.contextPath}/" + action;
		document.motor.submit();
	}
	appPath = "${pageContext.request.contextPath}/";
	function getAjaxModel(frm,val, id)
	{
			postRequest('${pageContext.request.contextPath}/'+id+'Motor.action'+val, id);
	}
	function getDeductibleAjax(frm) {
		var val = "?seatingCapacity=" + $("#seatingCapacity").val()
					+ "&vehicleUsage=" + $("#vehicleUsage").val()
					+ "&typeBody=" + $("#typeBody").val();
		getAjaxModel(frm,val,'deductibleAjax');
	}
	function getNCBAjax(frm) {
		var val = "?vehicleUsage=" + $("#vehicleUsage").val();
		getAjaxModel(frm,val,'ncbAjax');
	}
	function vehicleTypeDetailsAjax(frm) {
		$("#modalErrorSpan").html("");
		var val = "?make=" + $("#make").val()
					+ "&model=" + $("#model").val()
					+ "&typeBody=" + $("#typeBody").val()
					+ "&vehicleUsage=" + $("#vehicleUsage").val()
					+ "&seatingCapacity=" + $("#seatingCapacity").val()
					+ "&deleteVehicleId=" + $("#rvehicleId").val()
					+ "&applicationNo=<s:property value='applicationNo'/>";
		getAjaxModel(frm,val,'vehicleTypeDetailsAjax');
		return false;
	}
	function setModalVehicleDetails(modalSelectId,modalTypeBody,modalVehicleUsage,modalTypeBodyName,modalVehicleUsageName) {
		$("#modalSelectId").val(modalSelectId);
		$("#modalTypeBody").val(modalTypeBody);
		$("#modalVehicleUsage").val(modalVehicleUsage);
		$("#modalTypeBodyName").val(modalTypeBodyName);
		$("#modalVehicleUsageName").val(modalVehicleUsageName);
	}
	function setVehicleTypeDetails(frm) {
		var error = "";
		var seatingCapacity = $("#tSeatingCapacity_" + $("#modalSelectId").val()).val();
		if($("#modalSelectId").val()=="") {
			error = "Please select Vehicle Type";
		} else if(seatingCapacity=="") {
			error = "Please Enter Seating Capacity";
		} else if(parseInt(seatingCapacity) == NaN) {
			error = "Please Enter valid Seating Capacity";
		}
		$("#modalErrorSpan").html(error);
		if(error=="") {
			/*var bodyId =$("#modalTypeBody").val();
			if(bodyId==9 || bodyId==27){
				$( '#seatingCapacityBlock' ).attr('style','display:none;');
			}else{
				$( '#seatingCapacityBlock' ).attr('style','display:block;');
			}*/
			$("#typeBody").val($("#modalTypeBody").val());
			$("#vehicleUsage").val($("#modalVehicleUsage").val());
			$("#seatingCapacity").val($("#tSeatingCapacity_" + $("#modalSelectId").val()).val());
			$("#typeBodyDiv").html($("#modalTypeBodyName").val());
			$("#vehicleUsageDiv").html($("#modalVehicleUsageName").val());
			$("#seatingCapacityDiv").html($("#seatingCapacity").val());
			$("#typeBodyName").val($("#modalTypeBodyName").val());
			$("#vehicleUsageName").val($("#modalVehicleUsageName").val());
			$('#vehicleTypeDetails').modal('toggle');
			getDeductibleAjax(frm);
			getNCBAjax(frm);
		}
	}
	function removeVehicleTypeDetails() {
		$("#typeBody").val("");
		$("#vehicleUsage").val("");
		$("#seatingCapacity").val("");
		$("#typeBodyDiv").html("");
		$("#vehicleUsageDiv").html("");
		$("#seatingCapacityDiv").html("");
		$("#typeBodyName").val("");
		$("#vehicleUsageName").val("");
		$("#deductible").find('option').remove();
		$("#noClaimBonus").find('option').remove().end().append('<option value="">---Select---</option>').val("");
	}

	function toggleAddVehicle(value) {
		if(value=="show") {
			$( '.addVehicle' ).attr('style','');
			$( '#toggleAddVehicleDiv' ).attr('style','display:none;');
			
		} else {
			$( '.addVehicle' ).attr('style','display:none;');
		}
	}

	function toggleTpLiablity(value) {
		if("Y"==value) {
			document.getElementById('tpLiablityAmount').disabled = false;
		} else {
			document.getElementById('tpLiablityAmount').value = "";
			document.getElementById('tpLiablityAmount').disabled = true;
		}
	}

	function getComparisionDetails(frm) {
		if( validateTpLiablity()==""  ) {
			$('#errorMessageDiv').html('');
			var val = '?applicationNo=<s:property value="applicationNo"/>'
				+ '&quoteNo=<s:property value="quoteNo"/>'
				+ '&currencyType=<s:property value="currencyType"/>'
				+ '&tpLiablityYN=' + ($('#tpLiablityYNN').is(':checked')==true?"N":"Y")
				+ '&tpLiablityAmount=' + $('#tpLiablityAmount').val();
			getAjaxModel(frm,val,'comparisionDetailsAjax');
		}
	}

	function validateTpLiablity() {
		var errorMessage = "";
		if( $('#tpLiablityYNY').is(':checked')==true) {
			var val = parseFloat($('#tpLiablityAmount').val());
			if(isNaN(val)) {
				errorMessage = 'Please enter valid Combined Third Party Liability Amount';
			} else {
				var minLimit = 90000;
				var maxLimit = 500000;
				if('<s:property value="currencyType"/>'=='USD') {
					minLimit = minLimit/11.37;
					maxLimit = maxLimit/11.37;
				}
				if(val<minLimit) {
					errorMessage = 'Please enter Combined Third Party Liability Amount above ' + numeral(Number(minLimit)).format('0,0.00');
				} else if(val>maxLimit) {
					errorMessage = 'Please enter Combined Third Party Liability Amount below ' + numeral(Number(maxLimit)).format('0,0.00');
				}
			}
		}
		$('#errorMessageDiv').html(errorMessage);
		$('html, body').animate(
			{'scrollTop' : $("#errorMessageDiv").offset().top},
			'fast'
		);
		return errorMessage;
	}

	function disablePolicyOption(value) {
		if(value=="Y") {
			if('Y'=='<s:property value="showReferralYN"/>') {
				document.getElementById('referralComments').readOnly=false;
			}
			document.getElementById('policyGeneration').style.display='none';
		} else {   
		 	document.getElementById('policyGeneration').style.display='block';
		 	if('Y'=='<s:property value="showReferralYN"/>') {
			 	document.getElementById('referralComments').value='';
			 	document.getElementById('referralComments').readOnly=true;
		 	}
		}
    }
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
    function toggleLeasedYN(value, id) {
    	if(value=="Y") {
			document.getElementById('bankOfFinanceIdList['+id+"]").disabled=false;
		}   
		else {  
			document.getElementById('bankOfFinanceIdList['+id+"]").value='';
			document.getElementById('bankOfFinanceIdList['+id+"]").disabled=true;
		}
    }
	function uploadDocuments(vehicleId) {
		var URL ='${pageContext.request.contextPath}/documentUploadDoUpload.action?applicationNo=<s:property value="applicationNo"/>&quoteNo=<s:property value="quoteNo"/>&deleteVehicleId='+vehicleId;
		return popUp(URL,'700','500');
	}
	function electricalPopup(vehicleId) {
		var URL ='${pageContext.request.contextPath}/electricalPopupMotor.action?applicationNo=<s:property value="applicationNo"/>';
		return popUp(URL,'700','500');
	}
	 
	function nelectricalPopup(vehicleId) {
		var URL ='${pageContext.request.contextPath}/nelectricalPopupMotor.action?applicationNo=<s:property value="applicationNo"/>';
		return popUp(URL,'700','500');
	}
		
    function getBack(page) {
		if(page=='showSummarry') {
			document.motor.action ='${pageContext.request.contextPath}/showSummarryMotor.action';
		} else if(page=='newQuote') {
				document.motor.action ='${pageContext.request.contextPath}/editQuoteMotor.action';
		}else if(page=='editCovRate'){
			document.motor.action ='${pageContext.request.contextPath}/editCovRateMotor.action';
		}else if(page=='home'){
			if('admin'=='<s:property value="#session.user1"/>'){
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
				document.motor.action ='${pageContext.request.contextPath}/getOCAjaxReferal.action';
		}
		document.motor.submit();
	}
	
	function fnVehiclesubmit(frm, type, vehicleId) {
		if("delete"==type) {
			document.getElementById('deleteVehicleId').value = vehicleId;
			document.motor.action = "${pageContext.request.contextPath}/deleteAddVehicleMotor";
			disableForm(frm,false,'');
			document.motor.submit();
		}
		else if("edit"==type) {
			document.getElementById('deleteVehicleId').value = vehicleId;
			document.motor.action = "${pageContext.request.contextPath}/editAddVehicleMotor";
			disableForm(frm,false,'');
			document.motor.submit();
		} else if("rate"==type) {
			document.getElementById('rateVehicleId').value = vehicleId;
			document.motor.action = "${pageContext.request.contextPath}/editCovRateVehicleMotor";
			disableForm(frm,false,'');
			document.motor.submit();
		}
		return false;
	}
	
	
	function changeBtn(val) {
		if('true'!='<s:property value="#disable1"/>') {
			if (val=='2') {
				document.getElementById("tplBtn").className = 'btnGroupType btn btn-lg btn-success active';
				document.getElementById("comprehensiveBtn").className = 'btnGroupType btn btn-lg btn-warning';
				document.getElementById("bothBtn").className = 'btnGroupType btn btn-lg btn-warning';
	
				//document.getElementById("tp").style.display = 'block';
				//document.getElementById("tpOthers").style.display = 'none'; 
				document.getElementById("proceedTPSpan").style.display = '';
				document.getElementById("proceedSpan").style.display = 'none';
				
			} else if (val=='1') {
				document.getElementById("tplBtn").className = 'btnGroupType btn btn-lg btn-warning';
				document.getElementById("comprehensiveBtn").className = 'btnGroupType btn btn-lg btn-success active';
				document.getElementById("bothBtn").className = 'btnGroupType btn btn-lg btn-warning';
	
				document.getElementById("premiumCard").style.display = 'none';
				document.getElementById("proceedTPSpan").style.display = 'none';
				document.getElementById("proceedSpan").style.display = '';
			} else if (val=='0') {
				document.getElementById("tplBtn").className = 'btnGroupType btn btn-lg btn-warning';
				document.getElementById("comprehensiveBtn").className = 'btnGroupType btn btn-lg btn-warning';
				document.getElementById("bothBtn").className = 'btnGroupType btn btn-lg btn-success active';
	
				//document.getElementById("tpOthers").style.display = 'block';
				//document.getElementById("tp").style.display = 'none';
	
				document.getElementById("premiumCard").style.display = 'none';
				document.getElementById("proceedTPSpan").style.display = 'none';
				document.getElementById("proceedSpan").style.display = '';
			}
			document.getElementById("premiumType").value = val;
		}
		return false;
	}
	
	/*function showPremium() {
		document.getElementById("premiumCard").style.display = 'block';
		document.getElementById("submitBtn").style.display = 'none';
	}*/
	
	function showBuyNow() {
		if (document.getElementById("buyNow").checked == true) {
			document.getElementById("tpBuyInner").style.display = 'block';
		} else {
			document.getElementById("tpBuyInner").style.display = 'none';
		}
	}
	
	} catch(err) {console.error(err);}
	/*$(function() {
		try {
			$('.datePicker').datepicker({
				todayHighlight: true,
				format: "dd/mm/yyyy"
			}).on('changeDate', function(e){
			    $(this).datepicker('hide');			    
			});
		} catch(err) {console.error(err);}
	});*/
	function reloadCaptcha(){
	    $("#captcha").attr("src", "${pageContext.request.contextPath}/simpleCaptcha.jpg");
	}
	function editCoverRate(frm, vehicleId) {
		document.motor.vehicleId.value = vehicleId;
		document.motor.action = "${pageContext.request.contextPath}/editCovRateMotor";
		disableForm(frm,false,'');
		document.motor.submit();
	}	
function funSubmitOtp(page,loginType) {
	if(page=='motor' && loginType=='registered') {
		document.motor.action = '${pageContext.request.contextPath}/loginRegDtlMotor.action';
	}else if(page=='motor' && loginType=='new'){
		document.motor.action = '${pageContext.request.contextPath}/loginNewDtlMotor.action';
	}
	document.motor.submit();
}
function funBackQuote() {
	document.motor.action = '${pageContext.request.contextPath}/showSummarryMotor.action';
	document.motor.submit();
}
</script>
<SCRIPT type="text/javascript">
<s:if test="'newQuote'.equalsIgnoreCase(display)">
toggleClaimDetails('<s:property value="claimYN"/>');
try {
	if('<s:property value="make"/>'!='' || '<s:property value="applicationNo"/>'=='') {
		toggleAddVehicle("show");
	} else {
		toggleAddVehicle("hide");
	}
} catch(err) {
	console.error(err);
}
</s:if>
<s:elseif test='%{"policyInfo".equalsIgnoreCase(display) && #session.user1 != "admin"}'>
disablePolicyOption('<s:property value="referralYN"/>');
</s:elseif>
<s:if test='endTypeId!=null && !"".equalsIgnoreCase(endTypeId)'>
enableForm(document.motor,false,'<s:property value="%{fields}"/>');
</s:if>
$(function() {
		try {
			$('.prevExpiryDateList').datepicker({
				todayHighlight: true,
				format: "dd/mm/yyyy"
			}).on('changeDate', function(e){
			    $(this).datepicker('hide');			    
			});
		} catch(err) {console.error(err);}
	});
</SCRIPT>
</body>
</html>