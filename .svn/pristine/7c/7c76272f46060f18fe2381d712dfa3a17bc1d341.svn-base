<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
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
	.textV {
		border: none;
	}
	.tboxV {
		border: none;
	}
</style>
</head>
<body>
<div id="loading" style="width:100%">
   <img id="loading-image" src="${pageContext.request.contextPath}/images/ajax-loader-bar.gif" id="loader"/>
</div>
<s:form id="claimIntimation" name="claimIntimation" method="post" action="" theme="simple">
<s:set var="selectpolicyType" value=""/>
<div class="row">
	<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
		<s:actionerror cssStyle="color: red;" />
	</div>
</div>
<div class="row">
	<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
		<div class="panel panel-primary">
			<div class="panel-heading">
				<s:text name="label.claimIntimation" /> 
			</div>
			<div class="panel-body">
				<div class="row">
					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
						<s:if test="#session.product_id == '65' || #session.product_id == '30' ">
						<div class="panel panel-primary">
							<div class="panel-heading">
								<s:text name="label.policyHolderDetails" />
							 </div>
							<s:iterator value="intimatePolicyList" status="stat" var="list">
							<s:if test="#session.product_id == '65'">
							<s:set name="selectpolicyType" var="selectpolicyType" value="%{#list.ISSELECTCOVER}"/>
							</s:if>
							<div class="panel-body">
								<div class="row">
									<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
										<span class="textV">	<s:text name="label.policyNo" /> </span> <span class="tboxV">&nbsp; : &nbsp; <s:property value="#list.POLICY_NO"/> </span> 
									</div>
									<div class="col-xs-12 col-sm-12 col-md-8 col-lg-8">
										<div class="textV" style="width: 100%">
											<s:text name="label.periodOfInsurance" /> 
											<s:text name="label.from" /> &nbsp; <b> : &nbsp; <s:property value="#list.POLICY_START_DATE"/> </b>
											<s:text name="label.to" /> &nbsp; <b> : &nbsp; <s:property value="#list.POLICY_END_DATE"/> </b>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
										<span class="textV">	<s:text name="label.nameOfTheInsured" /> </span> <span class="tboxV">&nbsp; : &nbsp; <s:property value="#list.INSUREDNAME"/> </span> 
									</div>
									<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
										<span class="textV">	<s:text name="label.mobileNo" /> </span> <span class="tboxV">&nbsp; : &nbsp; <s:property value="#list.MOBILE"/> </span>
									</div>
									<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
										<span class="textV">	<s:text name="label.email" /> </span> <span class="tboxV">&nbsp; : &nbsp; <s:property value="#list.EMAIL"/> </span>
									</div>
								</div>
								<div class="row">
									<div class="col-xs-12 col-sm-12 col-md-8 col-lg-8">
										<span class="textV" style="width:25%;">	<s:text name="label.physicalAddress" /> </span> <span class="tboxV" style="width:75%;">&nbsp; : &nbsp; <s:property value="#list.ADDRESS"/> </span> 
									</div>
								</div>
							</div>
							</s:iterator>
						</div>
						</s:if>
						<s:if test="#session.product_id == '30'">
						<div class="panel panel-primary">
							<div class="panel-heading">
								<s:text name="label.homePremiumInformation" />
							 </div>
							 <table class="footable" style="width: 100%; margin: 0 auto;">
							<thead>
							<tr>
								<th width="40%"><s:text name="coverage.details"/></th>
								<th width="30%"><s:text name="coverage.sumInsured"/></th>
								<th width="30%" align="right"><s:text name="label.premium"/> in [<s:property value="#session.BrokerDetails.CurrencyAbb"/>]</th>
							</tr>
							</thead>
							<tbody>
						<s:iterator var="list" value="homeList">
							<s:if test='PREMIUM_AMOUNT!=null'>
								<tr>
									<td>${coverages_display_name}</td>
									<td align="right"><b>
									<s:property value="COVERAGES_SUMINSURED" />&nbsp;<s:property value="#session.BrokerDetails.CurrencyAbb"/></b></td>
									<td align="right">
										<s:if test='#coverPremium'>
											<b><s:property value="getText('{0,number,#,##0.00}',{PREMIUM_AMOUNT})"/></b>
										</s:if>
									</td>
								</tr>
							</s:if>
						</s:iterator>
						    </tbody>
						     </table>
						</div>
						</s:if>
						<s:if test="#session.product_id == '65'">
						<div class="panel panel-primary">
							<div class="panel-heading">
								<s:text name="label.vehicleDetails" />
							</div>
							<div class="panel-body">
								<div class="row">
								    <s:iterator value="intimateVehicleList" status="stat" var="list">
									<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
										<span class="textV">	<s:text name="label.make" /> </span> <span class="tboxV">&nbsp; : &nbsp; <s:property value="#list.MAKE"/> </span>
									</div>
									<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
										<span class="textV">	<s:text name="label.year" /> </span> <span class="tboxV">&nbsp; : &nbsp; <s:property value="#list.YEAR"/> </span> 
									</div>
									<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
										<span class="textV">	<s:text name="label.regNo" /> </span> <span class="tboxV">&nbsp; : &nbsp; <s:property value="#list.REGISTRATION_NO"/></span>
									</div>
									<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
										<span class="textV">	<s:text name="label.engineNo" /> </span> <span class="tboxV">&nbsp; : &nbsp; <s:property value="#list.ENGINE_NUMBER"/> </span>
									</div>
									<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
										<span class="textV">	<s:text name="label.chassisNo" /> </span> <span class="tboxV">&nbsp; : &nbsp; <s:property value="#list.CHASSIS_NO"/> </span>
									</div>
									<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
										<span class="textV">	<s:text name="label.colour" /> </span> <span class="tboxV">&nbsp; : &nbsp; <s:property value="#list.COLOR"/> </span>
									</div>
									</s:iterator>
									<s:if test='%{!(getText("MOTOR_TP_ID")).equalsIgnoreCase(#selectpolicyType)}'>
						            <s:iterator value="intimateList" status="stat" var="list">
									<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
										<span class="textV">	<s:text name="label.Are.you.the.sole.owner.of.the.vehicle" /> </span> <span class="tboxV">&nbsp; : &nbsp; <s:property value="#list.SOLE_OWNER_YN"/> </span>
									</div>
									<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
										<span class="textV">	<s:text name="label.If.not.name.of.other.interested.parties.Financiers" /> </span> <span class="tboxV">&nbsp; : &nbsp; <s:property value="#list.FINANCIERS"/> </span>
									</div>
							        </s:iterator>
							        </s:if>
								</div>
							</div>
						</div>
						</s:if>
						<s:if test="#session.product_id == '65'">
						<div class="panel panel-primary" <s:if test='%{(getText("MOTOR_TP_ID")).equalsIgnoreCase(#selectpolicyType)}'>style="display: none;"</s:if>>
							<div class="panel-heading">
								<s:text name="label.driverDetails" />
							</div>
							<s:iterator value="intimateList" status="stat" var="list">
							<div class="panel-body">
								<div class="row">
									<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
										<span class="textV">	<s:text name="label.nameOfDriver" /> </span> <span class="tboxV">&nbsp; : &nbsp; <s:property value="#list.NAME_OF_DRIVER"/> </span>
									</div>
									<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
										<span class="textV">	<s:text name="label.dob" /> </span> <span class="tboxV">&nbsp; : &nbsp; <s:property value="#list.DRIVER_DOB"/> </span> 
									</div>
									<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
										<span class="textV">	<s:text name="label.licenseNo" /> </span> <span class="tboxV">&nbsp; : &nbsp; <s:property value="#list.LICENSE_NO"/></span>
									</div>
									<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
										<span class="textV">	<s:text name="label.dateObtained" /> </span> <span class="tboxV">&nbsp; : &nbsp; <s:property value="#list.DATE_OBTAINED"/> </span>
									</div>
								</div>
							</div>
							</s:iterator>
						</div>
						</s:if>
						<s:if test="#session.product_id == '65'">
						<div class="panel panel-primary" <s:if test='%{(getText("MOTOR_TP_ID")).equalsIgnoreCase(#selectpolicyType)}'>style="display: none;"</s:if>>
							<div class="panel-heading">
								<h4><s:text name="label.lossDamageTheftDetails" /></h4>
								<hr>
							</div>
							<s:iterator value="intimateList" status="stat" var="list">
							<div class="panel-body">
								<div class="row">
									<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
										<span class="textV">	<b><s:text name="label.date" /></b> </span> <span class="tboxV">&nbsp; : &nbsp; <s:property value="#list.LOSS_DATE"/> </span>
									</div>
									<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
										<span class="textV">	<b><s:text name="label.time" /></b> </span> <span class="tboxV">&nbsp; : &nbsp; <s:property value="#list.LOSS_TIME"/> </span> 
									</div>
									<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
										<span class="textV">	<b><s:text name="label.speed" /></b> </span> <span class="tboxV">&nbsp; : &nbsp; <s:property value="#list.LOSS_SPEED"/></span>
									</div>
									<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
										<span class="textV">	<b><s:text name="label.place" /></b> </span> <span class="tboxV">&nbsp; : &nbsp; <s:property value="#list.LOSS_PLACE"/> </span>
									</div>
									<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
										<span class="textV">	<b><s:text name="label.purposeforwhichthevehiclewasbeingusedatthetimeofaccident" /></b> </span> <span class="tboxV">&nbsp; : &nbsp; <s:property value="#list.LOSS_PURPOSE_DESC"/> </span>
									</div>
									<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
										<span class="textV">	<b><s:text name="label.missingPartsDetails" /></b> </span> <span class="tboxV">&nbsp; : &nbsp; <s:property value="#list.LOSS_DETAILS_DESC"/> </span>
									</div>
									<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
										<span class="textV">	<b><s:text name="label.reportedToPolice" /></b> </span> <span class="tboxV">&nbsp; : &nbsp; <s:property value="#list.LOSS_REPORT_YN"/> </span>
									</div>
									<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
										<span class="textV">	<b><s:text name="label.dateReported" /></b> </span> <span class="tboxV">&nbsp; : &nbsp; <s:property value="#list.LOSS_DATE_REPORTED"/> </span>
									</div>
									<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
										<span class="textV">	<b><s:text name="label.timeReported" /></b> </span> <span class="tboxV">&nbsp; : &nbsp; <s:property value="#list.LOSS_TIME_REPORTED"/> </span>
									</div>
									<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
										<span class="textV">	<b><s:text name="label.policeVisited" /></b> </span> <span class="tboxV">&nbsp; : &nbsp; <s:property value="#list.LOSS_POLICE_VISIT_YN"/> </span>
									</div>
									<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
										<span class="textV">	<b><s:text name="label.nameofpoliceofficer" /></b> </span> <span class="tboxV">&nbsp; : &nbsp; <s:property value="#list.POLICE_OFFICER_NAME"/> </span>
									</div>
									<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
										<span class="textV">	<b><s:text name="label.identityNo" /></b> </span> <span class="tboxV">&nbsp; : &nbsp; <s:property value="#list.IDENTITY_NO"/> </span>
									</div>
									<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
										<span class="textV">	<b><s:text name="label.nameofpolicestation" /></b> </span> <span class="tboxV">&nbsp; : &nbsp; <s:property value="#list.POLICE_STATION_NAME"/> </span>
									</div>
									<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
										<span class="textV">	<b><s:text name="label.locationofDamagedVehicleWMobile" /></b> </span> <span class="tboxV">&nbsp; : &nbsp; <s:property value="#list.LOSS_LOCATION"/> </span>
									</div>
								</div>
							</div>
							</s:iterator>
						</div>
						</s:if>
						<s:if test="#session.product_id == '65'">
						<div class="panel panel-primary" <s:if test='%{(getText("MOTOR_TP_ID")).equalsIgnoreCase(#selectpolicyType)}'>style="display: none;"</s:if>>
							<div class="panel-heading">
								<s:text name="label.accidentHappendStatement" />
							</div>
							<s:iterator value="intimateList" status="stat" var="list">
							<div class="panel-body">
								<div class="row">
									<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
										<span class="textV"><s:property value="DRIVER_DESC"/> </span>
									</div>
								</div>
							</div>
							</s:iterator>
						</div>
						</s:if>
						<s:if test="#session.product_id == '65'">
						<div class="panel panel-primary" <s:if test='%{!(getText("MOTOR_TP_ID")).equalsIgnoreCase(#selectpolicyType)}'>style="display: none;"</s:if>>
							<div class="panel-heading">
								<s:text name="label.driverPassengerThirdPartyInjuries" />
							</div>
							<s:iterator value="intimateThirdPartyList" status="stat" var="list">
							<div class="panel-body">
								<div class="row">
									<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
										<table width="100%" class="footable">
											<thead>
											<tr>
												<th width="5%"></th>
												<th width="23.75%"> <s:text name="label.name" /> </th>
												<th width="23.75%"> <s:text name="label.phoneNo" /> </th>
												<th width="23.75%"> <s:text name="label.address" /> </th>
												<th width="23.75%"> <s:text name="label.natureOfInjury" /> </th>
											</tr>
											</thead>
											<tbody>
											<tr>
												<td align="center">A</td>
												<td><span class="textV"><s:property value="#list.PASSENGER_NAME_A"/> </span></td>
												<td><span class="textV"><s:property value="#list.PASSENGER_PHONENO_A"/> </span></td>
												<td><span class="textV"><s:property value="#list.PASSENGER_ADDRESS_A"/> </span></td>
												<td><span class="textV"><s:property value="#list.PASSENGER_INJURY_A"/> </span></td>
											</tr>
											<tr>
												<td align="center">B</td>
												<td><span class="textV"><s:property value="#list.PASSENGER_NAME_B"/> </span></td>
												<td><span class="textV"><s:property value="#list.PASSENGER_PHONENO_B"/> </span></td>
												<td><span class="textV"><s:property value="#list.PASSENGER_ADDRESS_B"/> </span></td>
												<td><span class="textV"><s:property value="#list.PASSENGER_INJURY_B"/> </span></td>
											</tr>
											</tbody>
										</table>
									</div>
								</div>
							</div>
							</s:iterator>
						</div>
						</s:if>
						<s:if test="#session.product_id == '65'">
						<div class="panel panel-primary" <s:if test='%{!(getText("MOTOR_TP_ID")).equalsIgnoreCase(#selectpolicyType)}'>style="display: none;"</s:if>>
							<div class="panel-heading">
								<s:text name="label.hospitalPersonsAttened" />
							</div>
							<s:iterator value="intimateThirdPartyList" status="stat" var="list">
							<div class="panel-body">
								<div class="row">
									<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
										<span class="textV">	<s:text name="label.nameofhospital" /> </span> <span class="tboxV">&nbsp; : &nbsp; <s:property value="#list.HOSPITAL_NAME"/> </span>
									</div>
									<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
										<span class="textV">	<s:text name="label.nameofdoctor" /> </span> <span class="tboxV">&nbsp; : &nbsp; <s:property value="#list.NAME_OF_DOCTOR"/> </span> 
									</div>
									<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
										<span class="textV">	<s:text name="label.telephoneNo" /> </span> <span class="tboxV">&nbsp; : &nbsp; <s:property value="#list.DOC_TELEPHONE_NO"/> </span>
									</div>
								</div>
							</div>
							</s:iterator>
						</div>
						</s:if>
						<s:if test="#session.product_id == '65'">
						<div class="panel panel-primary" <s:if test='%{!(getText("MOTOR_TP_ID")).equalsIgnoreCase(#selectpolicyType)}'>style="display: none;"</s:if>>
							<div class="panel-heading">
								<s:text name="label.thirdPartyPropertyDamage" />
							</div>
							<s:iterator value="intimateThirdPartyList" status="stat" var="list">
							<div class="panel-body">
								<div class="row">
									<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
										<span class="textV">	<s:text name="label.name" /> </span> <span class="tboxV">&nbsp; : &nbsp; <s:property value="#list.THIRD_PARTY_NAME"/> </span>
									</div>
									<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
										<span class="textV">	<s:text name="label.propertyMotorVehicleDetails" /> </span> <span class="tboxV">&nbsp; : &nbsp; <s:property value="#list.THIRD_PARTY_PROPERTY_DETAIL"/> </span> 
									</div>
								</div>
							</div>
							</s:iterator>
						</div>
						</s:if>
						<s:if test="#session.product_id == '65'">
						<div class="panel panel-primary" <s:if test='%{!(getText("MOTOR_TP_ID")).equalsIgnoreCase(#selectpolicyType)}'>style="display: none;"</s:if>>
							<div class="panel-heading">
								<s:text name="label.involvesThirdParty" />
							</div>
							<s:iterator value="intimateThirdPartyList" status="stat" var="list">
							<div class="panel-body">
								<div class="row">
									<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
										<span class="textV">	<s:text name="label.nameofOwner" /> </span> <span class="tboxV">&nbsp; : &nbsp; <s:property value="#list.THIRD_PARTY_OWNER_NAME"/> </span>
									</div>
									<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
										<span class="textV">	<s:text name="label.ownertelephoneNo" /> </span> <span class="tboxV">&nbsp; : &nbsp; <s:property value="#list.THIRD_PARTY_OWNER_TELEPHONE_NO"/> </span> 
									</div>
									<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
										<span class="textV">	<s:text name="label.nameOfDriver" /> </span> <span class="tboxV">&nbsp; : &nbsp; <s:property value="#list.DRIVER_NAME"/> </span> 
									</div>
									<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
										<span class="textV">	<s:text name="label.physicalAddress" /> </span> <span class="tboxV">&nbsp; : &nbsp; <s:property value="#list.PHYSICAL_ADDRESS"/> </span> 
									</div>
									<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
										<span class="textV">	<s:text name="label.regNo" /> </span> <span class="tboxV">&nbsp; : &nbsp; <s:property value="#list.REGISTER_NO"/> </span> 
									</div>
									<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
										<span class="textV">	<s:text name="label.make" /> </span> <span class="tboxV">&nbsp; : &nbsp; <s:property value="#list.MAKE"/> </span> 
									</div>
									<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
										<span class="textV">	<s:text name="label.nameofInsurer" /> </span> <span class="tboxV">&nbsp; : &nbsp; <s:property value="#list.NAME_OF_INSURER"/> </span> 
									</div>
								</div>
							</div>
							</s:iterator>
						</div>
						</s:if>
						<s:if test="#session.product_id == '30'">
						<s:iterator value="intimateList" status="stat" var="list">
									<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
										<span class="textV">	<s:text name="label.lossStatus" /> </span> <span class="tboxV">&nbsp; : &nbsp; <s:property value="#list.LOSS_STATUS"/> </span> 
									</div>
									<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
										<span class="textV">	<s:text name="label.lossDescriptionn" /> </span> <span class="tboxV">&nbsp; : &nbsp; <s:property value="#list.LOSS_DESCRIPTION"/> </span> 
									</div>
						</s:iterator>
						</s:if>
						<s:if test="#session.product_id == '65' || #session.product_id == '30'">
						<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
							<div class="text">
								<s:text name="label.status"  /><font color="red">*</font>
							</div>
						    <div class="tbox">									 			
								<s:radio list="#{'Y':'Approved','N':'Rejected'}" name="approverStatus" id="approverStatus" value='%{approverStatus==null?"":approverStatus}'  />							 		
							</div>
						</div>
						<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
							<div class="text">
								<s:text name="label.remarks"  /><font color="red">*</font>
							</div>
							<div class="tbox">									 			
								<s:textarea rows="2" name="remarks" id="remarks" cssClass="inputBoxA" cssStyle="width: 100%;" />							 		
							</div>
						</div>
						</s:if>								
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<div class="row">
	<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" align="center">
	<s:if test="#session.product_id == '65'">
		<s:submit type="button" cssClass="btn btn-warning btn-sm" value="submit" onclick="adminSubmit('%{policyNo}','%{vehicleId}','list','%{#session.product_id}')"/>
    </s:if>
    <s:if test="#session.product_id == '30'">
		<s:submit type="button" cssClass="btn btn-warning btn-sm" value="submit" onclick="adminSubmit('%{policyNo}','','list','%{#session.product_id}')"/>
    </s:if>
		<s:submit type="button" cssClass="btn btn-sm btn-danger" value="back" onclick="adminBack('list')"/>
	</div>
</div>
<s:if test="#session.product_id == '65'">
<s:hidden name="policyType" value="%{selectpolicyType}"/>
</s:if>
<s:hidden name="fromDate" id="fromDate"/>
<s:hidden name="toDate" id="toDate"/>
</s:form>
<script type="text/javascript">

function adminSubmit(policy,vehicle,mode,productId){
if(productId == '65')
{
document.forms['claimIntimation'].action = "getIntimationInsertMotorReportAM.action?policyNo="+policy+"&vehicleId="+vehicle+"&mode="+mode;
}
if(productId == '30')
{
document.forms['claimIntimation'].action = "getIntimationInsertMotorReportAM.action?policyNo="+policy+"&mode="+mode;
}
document.forms['claimIntimation'].submit();
}	
function adminBack(mode){
document.forms['claimIntimation'].action = "getIntimationBackMotorReportAM.action?&mode="+mode;
document.forms['claimIntimation'].submit();
}
</script>
</body>
</html>