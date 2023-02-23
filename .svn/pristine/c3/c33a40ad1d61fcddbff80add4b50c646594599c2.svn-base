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
<s:form id="report" name="report" method="post" action="" theme="simple">
<s:set var="selectpolicyType" value=""/>
<div class="row">
	<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
		<s:actionerror cssStyle="color: red;" />
	</div>
</div>

<div class="container vehDtl">
<div class="Card_Parent ">
<div class="card card-5">
<div class="p-3">
<div class="row">
	<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
		<div class="panel panel-primary">
			<div class="panel-heading">
				<h3>
				<s:if test='"claim"==requireType'>
					<s:text name="label.claimIntimation" />
				</s:if>
				 <s:if test='"endorsement"==requireType'>
					<s:text name="label.endorement" />
				</s:if>
				</h3>
			</div>
			<div class="panel-body">
				<div class="row">
					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
					<s:if test="#session.product_id == '65' || #session.product_id == '30' ">
					
						<div class="panel panel-primary">
							<div class="panel-heading">
								<h4><s:text name="label.policyHolderDetails" /></h4>
								<hr/>
							 </div>
							<s:iterator value="intimatePolicyList" status="stat" var="list">
							<s:set name="selectpolicyType" var="selectpolicyType" value="%{#list.ISSELECTCOVER}"/>
							<div class="panel-body">
								<div class="row">
									<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
										<span class="textV"><b><s:text name="label.policyNo" /></b></span> <span class="tboxV">&nbsp; : &nbsp; <s:property value="#list.POLICY_NO"/> </span> 
									</div>
									<div class="col-xs-12 col-sm-12 col-md-8 col-lg-8">
										<div class="textV" style="width: 100%">
											<b><s:text name="label.periodOfInsurance" /> 
											<s:text name="label.from" /> &nbsp;  : </b> &nbsp; <s:property value="#list.POLICY_START_DATE"/> 
											<b><s:text name="label.to" /> &nbsp; </b> : &nbsp; <s:property value="#list.POLICY_END_DATE"/> 
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
										<span class="textV">	<b><s:text name="label.nameOfTheInsured" /></b> </span> <span class="tboxV">&nbsp; : &nbsp; <s:property value="#list.INSUREDNAME"/> </span> 
									</div>
									<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
										<span class="textV">	<b><s:text name="label.mobileNo" /></b> </span> <span class="tboxV">&nbsp; : &nbsp; <s:property value="#list.MOBILE"/> </span>
									</div>
									<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
										<span class="textV">	<b><s:text name="label.email" /></b> </span> <span class="tboxV">&nbsp; : &nbsp; <s:property value="#list.EMAIL"/> </span>
									</div>
								</div>
								<div class="row">
									<div class="col-xs-12 col-sm-12 col-md-8 col-lg-8">
										<span class="textV" style="width:25%;">	<b><s:text name="label.physicalAddress" /></b> </span> <span class="tboxV" style="width:75%;">&nbsp; : &nbsp; <s:property value="#list.ADDRESS"/> </span> 
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
						
						<br/>
						<div class="panel panel-primary">
							<div class="panel-heading">
								<h4><s:text name="label.vehicleDetails" /></h4>
								<hr/>
							</div>
							<div class="panel-body">
								<div class="row">
								    <s:iterator value="intimateVehicleList" status="stat" var="list">
									<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
										<span class="textV">	<b><s:text name="label.make" /></b> </span> <span class="tboxV">&nbsp; : &nbsp; <s:property value="#list.MAKE"/> </span>
									</div>
									<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
										<span class="textV">	<b><s:text name="claim.model" /></b> </span> <span class="tboxV">&nbsp; : &nbsp; <s:property value="#list.MODEL_NAME"/> </span>
									</div>
									<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
										<span class="textV">	<b><s:text name="label.year" /></b> </span> <span class="tboxV">&nbsp; : &nbsp; <s:property value="#list.YEAR"/> </span> 
									</div>
									<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
										<span class="textV">	<b><s:text name="label.regNo" /></b> </span> <span class="tboxV">&nbsp; : &nbsp; <s:property value="#list.REGISTRATION_NO"/></span>
									</div>
									<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
										<span class="textV">	<b><s:text name="label.engineNo" /></b> </span> <span class="tboxV">&nbsp; : &nbsp; <s:property value="#list.ENGINE_NUMBER"/> </span>
									</div>
									<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
										<span class="textV">	<b><s:text name="label.chassisNo" /></b> </span> <span class="tboxV">&nbsp; : &nbsp; <s:property value="#list.CHASSIS_NO"/> </span>
									</div>
									<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
										<span class="textV">	<b><s:text name="label.colour" /></b> </span> <span class="tboxV">&nbsp; : &nbsp; <s:property value="#list.COLOR"/> </span>
									</div>
									</s:iterator>
									<s:if test='"claim"==requireType'>
										<s:if test='%{!(getText("MOTOR_TP_ID")).equalsIgnoreCase(#selectpolicyType)}'>
								            <s:iterator value="intimateList" status="stat" var="list">
												<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
													<span class="textV">	<b><s:text name="label.Are.you.the.sole.owner.of.the.vehicle" /></b> </span> <span class="tboxV">&nbsp; : &nbsp; <s:property value="#list.SOLE_OWNER_YN"/> </span>
												</div>
												<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
													<span class="textV">	<b><s:text name="label.If.not.name.of.other.interested.parties.Financiers" /></b> </span> <span class="tboxV">&nbsp; : &nbsp; <s:property value="#list.FINANCIERS"/> </span>
												</div>
									        </s:iterator>
								        </s:if>
								     </s:if>
								</div>
							</div>
						</div>
						</s:if>
						<s:if test='"claim"==requireType'>
							<s:if test="#session.product_id == '65'">
							<div class="panel panel-primary" <s:if test='%{(getText("MOTOR_TP_ID")).equalsIgnoreCase(#selectpolicyType)}'>style="display: none;"</s:if>>
								<div class="panel-heading">
									<h4><s:text name="label.driverDetails" /></h4>
									<hr>
								</div>
								<s:iterator value="intimateList" status="stat" var="list">
								<div class="panel-body">
									<div class="row">
										<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
											<span class="textV">	<b><s:text name="label.nameOfDriver" /></b> </span> <span class="tboxV">&nbsp; : &nbsp; <s:property value="#list.NAME_OF_DRIVER"/> </span>
										</div>
										<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
											<span class="textV">	<b><s:text name="label.dob" /></b> </span> <span class="tboxV">&nbsp; : &nbsp; <s:property value="#list.DRIVER_DOB"/> </span> 
										</div>
										<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
											<span class="textV">	<b><s:text name="label.licenseNo" /></b> </span> <span class="tboxV">&nbsp; : &nbsp; <s:property value="#list.LICENSE_NO"/></span>
										</div>
										<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
											<span class="textV">	<b><s:text name="label.dateObtained" /></b> </span> <span class="tboxV">&nbsp; : &nbsp; <s:property value="#list.DATE_OBTAINED"/> </span>
										</div>
									</div>
								</div>
								</s:iterator>
							</div>
							</s:if>
							<s:if test="#session.product_id == '65'">
							<br/>
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
							<br/>
							<div class="panel panel-primary" <s:if test='%{(getText("MOTOR_TP_ID")).equalsIgnoreCase(#selectpolicyType)}'>style="display: none;"</s:if>>
								<div class="panel-heading">
									<h4><s:text name="label.accidentHappendStatement" /></h4>
									<hr>
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
									<h4><s:text name="label.driverPassengerThirdPartyInjuries" /></h4>
									<hr>
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
							<br/>
							<div class="panel panel-primary" <s:if test='%{!(getText("MOTOR_TP_ID")).equalsIgnoreCase(#selectpolicyType)}'>style="display: none;"</s:if>>
								<div class="panel-heading">
									<h4><s:text name="label.hospitalPersonsAttened" /></h4>
									<hr>
								</div>
								<s:iterator value="intimateThirdPartyList" status="stat" var="list">
								<div class="panel-body">
									<div class="row">
										<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
											<span class="textV">	<b><s:text name="label.nameofhospital" /></b> </span> <span class="tboxV">&nbsp; : &nbsp; <s:property value="#list.HOSPITAL_NAME"/> </span>
										</div>
										<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
											<span class="textV">	<b><s:text name="label.nameofdoctor" /></b> </span> <span class="tboxV">&nbsp; : &nbsp; <s:property value="#list.NAME_OF_DOCTOR"/> </span> 
										</div>
										<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
											<span class="textV">	<b><s:text name="label.telephoneNo" /></b> </span> <span class="tboxV">&nbsp; : &nbsp; <s:property value="#list.DOC_TELEPHONE_NO"/> </span>
										</div>
									</div>
								</div>
								</s:iterator>
							</div>
							</s:if>
							<s:if test="#session.product_id == '65'">
							<br/>
							<div class="panel panel-primary" <s:if test='%{!(getText("MOTOR_TP_ID")).equalsIgnoreCase(#selectpolicyType)}'>style="display: none;"</s:if>>
								<div class="panel-heading">
									<h4><s:text name="label.thirdPartyPropertyDamage" /></h4>
									<hr>
								</div>
								<s:iterator value="intimateThirdPartyList" status="stat" var="list">
								<div class="panel-body">
									<div class="row">
										<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
											<span class="textV">	<b><s:text name="label.name" /></b> </span> <span class="tboxV">&nbsp; : &nbsp; <s:property value="#list.THIRD_PARTY_NAME"/> </span>
										</div>
										<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
											<span class="textV">	<b><s:text name="label.propertyMotorVehicleDetails" /></b> </span> <span class="tboxV">&nbsp; : &nbsp; <s:property value="#list.THIRD_PARTY_PROPERTY_DETAIL"/> </span> 
										</div>
									</div>
								</div>
								</s:iterator>
							</div>
							</s:if>
							<s:if test="#session.product_id == '65'">
							<br/>
							<div class="panel panel-primary" <s:if test='%{!(getText("MOTOR_TP_ID")).equalsIgnoreCase(#selectpolicyType)}'>style="display: none;"</s:if>>
								<div class="panel-heading">
									<h4><s:text name="label.involvesThirdParty" /></h4>
									<hr>
								</div>
								<s:iterator value="intimateThirdPartyList" status="stat" var="list">
								<div class="panel-body">
									<div class="row">
										<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
											<span class="textV">	<b><s:text name="label.nameofOwner" /></b> </span> <span class="tboxV">&nbsp; : &nbsp; <s:property value="#list.THIRD_PARTY_OWNER_NAME"/> </span>
										</div>
										<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
											<span class="textV">	<b><s:text name="label.ownertelephoneNo" /></b> </span> <span class="tboxV">&nbsp; : &nbsp; <s:property value="#list.THIRD_PARTY_OWNER_TELEPHONE_NO"/> </span> 
										</div>
										<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
											<span class="textV">	<b><s:text name="label.nameOfDriver" /></b> </span> <span class="tboxV">&nbsp; : &nbsp; <s:property value="#list.DRIVER_NAME"/> </span> 
										</div>
										<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
											<span class="textV">	<b><s:text name="label.physicalAddress" /></b> </span> <span class="tboxV">&nbsp; : &nbsp; <s:property value="#list.PHYSICAL_ADDRESS"/> </span> 
										</div>
										<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
											<span class="textV">	<b><s:text name="label.regNo" /></b> </span> <span class="tboxV">&nbsp; : &nbsp; <s:property value="#list.REGISTER_NO"/> </span> 
										</div>
										<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
											<span class="textV">	<b><s:text name="label.make" /></b> </span> <span class="tboxV">&nbsp; : &nbsp; <s:property value="#list.MAKE"/> </span> 
										</div>
										<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
											<span class="textV">	<b><s:text name="label.nameofInsurer" /></b> </span> <span class="tboxV">&nbsp; : &nbsp; <s:property value="#list.NAME_OF_INSURER"/> </span> 
										</div>
									</div>
								</div>
								</s:iterator>
							</div>
							</s:if>
							<%--<s:if test="#session.product_id == '65'">
							<div class="panel panel-primary">
								<div class="panel-body">
									<div class="row">
										<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
											<s:text name="label.declartion" />
										</div>
									</div>
								</div>
							</div>
							</s:if>	--%>
							<s:if test="#session.product_id == '30'">
								<s:iterator value="intimateList" status="stat" var="list">
									<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
										<span class="textV">	<s:text name="label.lossStatus" /> </span> <span class="tboxV">&nbsp; : &nbsp; <s:property value="#list.LOSS_STATUS"/> </span> 
									</div>
									<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
										<span class="textV">	<s:text name="label.lossDescriptionn" /> </span> <span class="tboxV">&nbsp; : &nbsp; <s:property value="#list.LOSS_DESCRIPTION"/> </span> 
									</div>
								</s:iterator>
							</s:if>
						</s:if>
						<s:elseif test='"endorsement"==requireType'>
						<br/>
							<div class="panel panel-primary">
								<div class="panel-heading">
									<h4><s:text name="label.endorsement.details" /></h4>
									<hr/>
	 							</div>
								<s:iterator value="endorsementListView" status="stat" var="list">
									<div class="panel-body">
										<div class="row">
											<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
												<span class="textV">	<b><s:text name="label.policyNo" /></b> </span> <span class="tboxV">&nbsp; : &nbsp; <s:property value="#list.POLICY_NO"/> </span>
											</div>
											<s:if test="#session.product_id == '65'">
												<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
													<span class="textV">	<b><s:text name="label.vehicletypedesc" /></b> </span> <span class="tboxV">&nbsp; : &nbsp; <s:property value="#list.VEHICLETYPE_DESC"/> </span> 
												</div>
											</s:if>
											<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
												<span class="textV">	<b><s:text name="label.endt.type" /></b> </span> <span class="tboxV">&nbsp; : &nbsp; <s:property value="#list.ENDT_TYPE"/></span>
											</div>
											<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
												<span class="textV">	<b><s:text name="label.entry.date" /></b> </span> <span class="tboxV">&nbsp; : &nbsp; <s:property value="#list.ENTRY_DATE"/> </span>
											</div>
											<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
												<span class="textV">	<b><s:text name="label.agent.remarks" /></b> </span> <span class="tboxV">&nbsp; : &nbsp; <s:property value="#list.AGENT_REMARKS"/> </span>
											</div>
											<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
												<span class="textV">	<b><s:text name="label.ref.no" /></b> </span> <span class="tboxV">&nbsp; : &nbsp; <s:property value="#list.REFERENCE_NO"/> </span>
											</div>
										</div>
									</div>
							    </s:iterator>
							</div>
						</s:elseif>
						<br class="clear"/>
						<s:if test='"admin"==loginType || "admin".equalsIgnoreCase(loginType)'>
						<s:if test='"claim"==requireType'>
						<s:if test='intimateStatusList.size()>0'>
						<div class="panel panel-primary">
			        		<div class="panel-heading">
								<h4><s:text name="Claim Status History"/></h4>
								<hr>
							</div>
							<div class="panel-body">
								<div class="row">
<!-- 									<table class="footable"> -->
									<table class="table table-bordered table-hover" id="gridTable" cellspacing="0" width="100%" >
		        						<thead class="bluecolortable">
											<tr>
												<th width="3%"> <s:text name="label.sno"/> </th>
												<th width="10%"> <s:text name="label.status" /></th>
												<th width="10%"> <s:text name="label.remarks" /></th>
												<th width="10%"> <s:text name="User Name" /></th>
												<th width="10%"> <s:text name="Approved Date" /></th>
											</tr>
										</thead>
										<tbody class="rowevencolor">
											<s:iterator value="intimateStatusList" var="list" status="stat">
												<tr>
													<td><s:property value="#stat.count"/></td>
													<td><s:property value="#list.STATUS"/></td>
													<td><s:property value="#list.REMARKS"/></td>
													<td><s:property value="#list.LOGIN_ID"/></td>
													<td><s:property value="#list.APPROVED_DATE"/></td>
													
												</tr>
											</s:iterator>
										</tbody>
									</table>
								</div>
							</div>
		       			</div>
		       			</s:if>
		       			</s:if>
		       			<s:if test=' ("claim".equals(requireType) && !("S".equals(approverStatus) || "R".equals(approverStatus ))) || ("endorsement".equals(requireType) && !("Y".equals(approverStatus) || "N".equals(approverStatus )))'>
						<div class="panel panel-primary">
							<div class="panel-heading">
									<h4><s:text name="Confirmation Detail" /></h4>
									<hr/>
	 						</div>
							<div class="row">
								<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
									<div >
										<label><b><s:text name="label.status" /></b></label><font color="red">*</font>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<s:if test='"claim"==requireType'>
											<s:select list="#{'P':'PROCESSING','D':'DISCHARGED','S':'SETTLED','R':'REJECTED'}" name="approverStatus" id="approverStatus" headerKey="" headerValue="-- Select --" cssClass="form-control" cssStyle="width: 100%;" onchange="displayAmount(this.value); " />
										</s:if>
										<s:elseif test='"endorsement"==requireType'>
											<s:radio list="#{'Y':'Approved','N':'Rejected'}" name="approverStatus" id="approverStatus" value='%{approverStatus==null?"":approverStatus}'  />											
										</s:elseif>
																	 		
									</div>
								</div>
								<s:if test='"claim"==requireType'>
								<div id="settleAmount" style="display: none;" class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
									<label><s:text name="Settlement Amount" /></label><font color="red">*</font>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<s:textfield name="settleAmt" id="settleAmt" cssClass="form-control" cssStyle="width: 100%;" onkeyup="checkDecimals10_2(this);"/>
								</div>
								<div id="estimateAmount" style="display: none;" class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
									<label><s:text name="Estimate Amount" /></label><font color="red">*</font>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<s:textfield name="estimateAmt" id="estimateAmt" cssClass="form-control" cssStyle="width: 100%;" onkeyup="checkDecimals10_2(this);"/>
								</div>
								</s:if>
								<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
									<div class="text">
										<label><b><s:text name="label.remarks"  /></b></label><font color="red">*</font>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<s:textarea rows="2" name="remarks" id="remarks" cssClass="form-control" cssStyle="width: 100%;" />							 		
									</div>
								</div>
							</div>
							<br class="clear"/>
							<br class="clear"/>
						</div>
						</s:if>
						</s:if>						
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
</div>
</div>
</div>
</div>
<div class="row">
	<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" align="center">
		<s:if test='"admin"!=loginType || !"admin".equalsIgnoreCase(loginType)'>
			<s:if test='"Y".equalsIgnoreCase(updateStatus)'>
				<input type="button" class="btn btn-warning btn-sm" value="Edit" onClick="claimSubmit();" />
			</s:if>
			<s:if test="#session.product_id == '65'">
				<s:submit type="button" cssClass="btn btn-sm btn-danger" value="Back" onclick="claimHomeBack('individual','%{policyNo}','%{vehicleId}')"/>
			</s:if>
			<s:if test="#session.product_id == '30'">
				<s:submit type="button" cssClass="btn btn-sm btn-danger" value="Back" onclick="claimHomeBack('individual','%{policyNo}','')"/>
			</s:if>
		</s:if>
		<s:else>
			<s:if test=' ("claim".equals(requireType) && !("S".equals(approverStatus) || "R".equals(approverStatus ))) || ("endorsement".equals(requireType) && !("Y".equals(approverStatus) || "N".equals(approverStatus )))'>
				<input type="button" class="btn btn-success btn-sm" value="Submit" onClick="funSubmit('<s:property value="loginType"/>','<s:property value="requireType"/>');" />
			</s:if>
			<input type="button" class="btn btn-sm btn-danger" value="Back" onClick="funBack('<s:property value="loginType"/>','<s:property value="requireType"/>');" />
		</s:else>
	</div>
</div>
	<s:hidden name="vehicleId"/>
	<s:hidden name="reqFrom"/>
	<s:hidden name="menuType"/>
	<s:hidden name="policyNo"/>
	<s:hidden name="claimNo"></s:hidden>
	<s:hidden name="applicationNo"/>
	<s:hidden name="quoteNo"/>
	<s:hidden name="loginId"/>
	<s:hidden name="mode" id="mode"/>
	<s:hidden name="refNo"/>
	<s:hidden name="requireType" id="requireType"/>
	<s:hidden name="productId" id="productId"/>
	<s:hidden name="fromDate" id="fromDate"/>
	<s:hidden name="toDate" id="toDate"/>
</s:form>
<script type="text/javascript">
	function claimSubmit() {
		$('#mode').val('Edit');
		document.report.action = 'intimateClaim.action';
		document.report.submit();
	}
	function claimHomeBack(mode,policyNo,vehicleId) {
	document.forms['report'].mode.value = mode;
	document.forms['report'].policyNo.value = policyNo;
	document.forms['report'].vehicleId.value = vehicleId;
	document.forms['report'].action = "${pageContext.request.contextPath}/intimateClaim.action";
	document.forms['report'].submit();
    }
	function funBack(userType,requireType){
		document.forms['report'].policyNo.value='';
		document.forms['report'].vehicleId.value='';
		if(userType=='admin' && requireType=='claim'){
			document.forms['report'].mode.value='calimReportList';
			document.forms['report'].action = "${pageContext.request.contextPath}/intimateClaim.action";
		}else if(userType=='admin' && requireType=='endorsement'){
			document.forms['report'].mode.value='endorsementList';
			document.forms['report'].action = "${pageContext.request.contextPath}/endorsementClaim.action";
		}
		document.forms['report'].submit();
	}
	function funSubmit(userType,requireType){
		if(userType=='admin' && requireType=='claim'){
			document.forms['report'].mode.value='calimReportList';
			document.forms['report'].action = "${pageContext.request.contextPath}/intimateInsertClaim.action";
		}else if(userType=='admin' && requireType=='endorsement'){
			document.forms['report'].mode.value='endorsementList';
			document.forms['report'].action = "${pageContext.request.contextPath}/endorsementInsertClaim.action";
		}
		
		document.forms['report'].submit();
	}

	function displayAmount(val){
		if(val == 'S'){
			document.getElementById("settleAmount").style.display = "block";
			document.getElementById("estimateAmount").style.display = "none";
		}else if(val == 'D' || val == 'P'){
			document.getElementById("estimateAmount").style.display = "block";
			document.getElementById("settleAmount").style.display = "none";
		}else{
			document.getElementById("settleAmount").style.display = "none";
			document.getElementById("estimateAmount").style.display = "none";
		}
	}
</script>
</body>
</html>