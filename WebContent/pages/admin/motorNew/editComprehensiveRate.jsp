<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/struts-tags" prefix="s"%>
<html>
	<head>
		<SCRIPT language=Javascript>
			function isNumberKey(evt) {
				var charCode = (evt.which) ? evt.which : event.keyCode;
				if (charCode != 46 && charCode > 31 
				&& (charCode < 48 || charCode > 57))
				return false;
				return true;
			}
		</SCRIPT>
	</head>
	<body>
	<s:form id="rateConfig" name="rateConfig" action="" theme="simple">
<div class="container vehDtl">
	<div class="Card_Parent ">
		<div class="card card-5">
			<div class="p-3">
				<div class="col-xs-12">
					<div class="panel panel-primary">
						<div class="panel-heading">
							<h3><s:text name="label.master.comprehensiveRateConfig"/></h3>
							<hr>
						</div>
						<div class="panel-body">
							<div class="panel panel-primary">
								<div class="panel-heading">
									<s:iterator value="modalHeaderList" var="list"  >
										<div class="row">
											<div class="col-xs-12 col-sm-12 col-md-6 col-lg-4">
												<s:text name="label.master.policyType" /> &nbsp;:&nbsp; <b> <s:property value="%{POLICYTYPE_DESC_ENGLISH}"/></b>
											</div>
											<div class="col-xs-12 col-sm-12 col-md-6 col-lg-4">
												<s:text name="label.master.vehicleType" /> &nbsp;:&nbsp; <b>  <s:property value="%{VEHICLETYPE_DESC}"/> </b>
											</div>
											<div class="col-xs-12 col-sm-12 col-md-6 col-lg-4">
												<s:text name="label.master.bodyType" /> &nbsp;:&nbsp; <b> <s:property value="%{TYPE_OF_BODY_NAME}"/> </b>
											</div>
										</div>
									</s:iterator>									
								</div>
							</div>
							<s:if test="hasActionErrors()">
								<font color="red"><s:actionerror cssStyle="list-style: none;"/></font>
							</s:if>
							<div class="row">
								<div class="table-responsive" style="padding: 0px 12px 0px 12px; ">
	  								<table class="table table-bordered table-hover" id="gridTableMake" cellspacing="0" width="100%" >
											<thead class="bluecolortable">
	  										<tr>
		  										<th></th>
		  										<th align="center"><s:text name="label.start"></s:text> </th>
		  										<th align="center"><s:text name="label.end"></s:text></th>
		  										<th align="center"><s:text name="label.status"/></th>
	  										</tr>
	  									</thead>
	  									<tbody class="form-group rowevencolor">
	  										<tr>
	  											<td width="40%">
	  												<label><s:text name="label.master.seatingTon" /> <font color="red">*</font> </label>
	  											</td>
	  											<td width="20%">
  													<s:textfield name="seatingTonStart" id="seatingTonStart" cssClass="form-control numericOnly" maxlength="4"></s:textfield>
	  											</td>
	  											<td width="20%">
  													<s:textfield name="seatingTonEnd" id="seatingTonEnd" onchange="callAjax('checkSeatingTon');" cssClass="form-control numericOnly" maxlength="4" ></s:textfield>
	  											</td>
	  											<td width="20%" align="center">
	  												<div class="col-xs-6">
	  													<button class="btn btn-info" style="border-radius:0px;" type="button" onclick="$('#seatingTonInfo').modal('show');">
															<span class="glyphicon glyphicon-eye-open"> </span>
														</button>
	  												</div>
	  												<div class="col-xs-6" id="checkSeatingTon" >
	  												
	  												</div>
													<div id="seatingTonInfo" class="modal fade" role="dialog">
														<div class="modal-dialog modal-md">
															<div class="modal-content">
																<div class="modal-header">
																	<button type="button" class="close" data-dismiss="modal">&times;</button>
																	<h4 class="modal-title"><s:text name="label.master.seatingTon" /> </h4>
																</div>
																<div class="modal-body">
																	<s:iterator value="modalHeaderList" var="list"  >
																		<div class="row">
																			<div class="col-xs-12 col-sm-12 col-md-6 col-lg-4">
																				<s:text name="label.master.policyType" /> &nbsp;:&nbsp; <b> <s:property value="%{POLICYTYPE_DESC_ENGLISH}"/></b>
																			</div>
																			<div class="col-xs-12 col-sm-12 col-md-6 col-lg-4">
																				<s:text name="label.master.vehicleType" /> &nbsp;:&nbsp; <b>  <s:property value="%{VEHICLETYPE_DESC}"/> </b>
																			</div>
																			<div class="col-xs-12 col-sm-12 col-md-6 col-lg-4">
																				<s:text name="label.master.bodyType" /> &nbsp;:&nbsp; <b> <s:property value="%{TYPE_OF_BODY_NAME}"/> </b>
																			</div>
																		</div>
																	</s:iterator>
																	<div class="row">
																		<s:if test='comprehensiveRateList.size()>0'>
																			<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 table-responsive">
																				<table class="table table-bordered table-hover" id="gridTableMake" cellspacing="0" width="100%" >
																					<thead class="bluecolortable">
																					<tr>
																						<th align="center"><s:text name="label.master.seating.ton.start"></s:text> </th>
																						<th align="center"><s:text name="label.master.seating.ton.end"></s:text> </th>
																						<th align="center"><s:text name="label.master.cc.gvw.start.range"></s:text> </th>
																						<th align="center"><s:text name="label.master.cc.gvw.end.range"></s:text> </th>
																						<th align="center"><s:text name="label.master.uae.license.start"></s:text> </th>
																						<th align="center"><s:text name="label.master.uae.license.end"></s:text> </th>
																						<th align="center"><s:text name="label.master.driver.age.from"></s:text> </th>
																						<th align="center"><s:text name="label.master.driver.age.to"></s:text> </th>
																						<th align="center"><s:text name="label.master.suminsured.start"></s:text> </th>
																						<th align="center"><s:text name="label.master.suminsured.end"></s:text> </th>
																						<th align="center"><s:text name="label.master.policy.start.date"></s:text> </th>
																						<th align="center"><s:text name="label.master.policy.end.date"></s:text> </th>
																						<th align="center"><s:text name="label.master.status"></s:text> </th>
																					</tr>
																					</thead>
																					<tbody class="rowevencolor">
																						<s:iterator value="comprehensiveRateList" var="list">
																							<tr>
																								<td align="center"><s:property value="%{SEATING_TON_START}"/></td>
																								<td align="center"><s:property value="%{SEATING_TON_END}"/></td>
																								<td align="center"><s:property value="%{CC_GVW_START_RANGE}"/></td>
																								<td align="center"><s:property value="%{CC_GVW_END_RANGE}"/></td>
																								<td align="center"><s:property value="%{UAE_LISCENCE_START}"/></td>
																								<td align="center"><s:property value="%{UAE_LISCENCE_END}"/></td>
																								<td align="center"><s:property value="%{DRIVER_AGE_FROM}"/></td>
																								<td align="center"><s:property value="%{DRIVER_AGE_TO}"/></td>
																								<td align="center"><s:property value="%{SUMINSURED_START}"/></td>
																								<td align="center"><s:property value="%{SUMINSURED_END}"/></td>																							
																								<td align="center"><s:property value="%{POLICY_START_DATE}"/></td>
																								<td align="center"><s:property value="%{POLICY_END_DATE}"/></td>
																								<td align="center"><s:property value="%{STATUS}"/></td>
																							</tr>
																						</s:iterator>
																					</tbody>
																				</table>
																				</div>
																		</s:if>
																		<s:else>
																			<font color="green"><s:text name="label.no.records.available"></s:text></font>
																		</s:else>
																	</div>
																</div>
															</div>
														</div>
													</div>
												</td>
	  										</tr>
	  										<tr>
	  											<td width="40%">
	  												<label><s:text name="label.master.cc.gvw.range" /> <font color="red">*</font> </label>
	  											</td>
	  											<td width="20%">
  													<s:textfield name="CCGVWStartRange" id="CCGVWStartRange" cssClass="form-control numericOnly" maxlength="5"></s:textfield>
	  											</td>
	  											<td width="20%">
  													<s:textfield name="CCGVWEndRange" id="CCGVWEndRange" onchange="callAjax('checkCCGVWRange')" cssClass="form-control numericOnly"  maxlength="5"></s:textfield>
	  											</td>
	  											<td width="15%" align="center">
	  												<div class="col-xs-6">
	  													<button class="btn btn-info" style="border-radius:0px;" type="button" onclick="$('#CCGVWRange').modal('show');">
															<span class="glyphicon glyphicon-eye-open"> </span>
														</button>
	  												</div>
	  												<div class="col-xs-6" id="checkCCGVWRange" >
	  												
	  												</div>
													<div id="CCGVWRange" class="modal fade" role="dialog">
														<div class="modal-dialog modal-md">
															<div class="modal-content">
																<div class="modal-header">
																	<button type="button" class="close" data-dismiss="modal">&times;</button>
																	<h4 class="modal-title"><s:text name="label.master.cc.gvw.range" /> </h4>
																</div>
																<div class="modal-body">
																	<s:iterator value="modalHeaderList" var="list"  >
																		<div class="row">
																			<div class="col-xs-12 col-sm-12 col-md-6 col-lg-4">
																				<s:text name="label.master.policyType" /> &nbsp;:&nbsp; <b> <s:property value="%{POLICYTYPE_DESC_ENGLISH}"/></b>
																			</div>
																			<div class="col-xs-12 col-sm-12 col-md-6 col-lg-4">
																				<s:text name="label.master.vehicleType" /> &nbsp;:&nbsp; <b>  <s:property value="%{VEHICLETYPE_DESC}"/> </b>
																			</div>
																			<div class="col-xs-12 col-sm-12 col-md-6 col-lg-4">
																				<s:text name="label.master.bodyType" /> &nbsp;:&nbsp; <b> <s:property value="%{TYPE_OF_BODY_NAME}"/> </b>
																			</div>
																		</div>
																	</s:iterator>
																	<div class="row">
																		<s:if test='comprehensiveRateList.size()>0'>
																			<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 table-responsive">
																				<table class="table table-bordered table-hover" id="gridTableMake" cellspacing="0" width="100%" >
																					<thead class="bluecolortable">
																					<tr>
																						<th align="center"><s:text name="label.master.seating.ton.start"></s:text> </th>
																						<th align="center"><s:text name="label.master.seating.ton.end"></s:text> </th>
																						<th align="center"><s:text name="label.master.cc.gvw.start.range"></s:text> </th>
																						<th align="center"><s:text name="label.master.cc.gvw.end.range"></s:text> </th>
																						<th align="center"><s:text name="label.master.uae.license.start"></s:text> </th>
																						<th align="center"><s:text name="label.master.uae.license.end"></s:text> </th>
																						<th align="center"><s:text name="label.master.driver.age.from"></s:text> </th>
																						<th align="center"><s:text name="label.master.driver.age.to"></s:text> </th>
																						<th align="center"><s:text name="label.master.suminsured.start"></s:text> </th>
																						<th align="center"><s:text name="label.master.suminsured.end"></s:text> </th>
																						<th align="center"><s:text name="label.master.policy.start.date"></s:text> </th>
																						<th align="center"><s:text name="label.master.policy.end.date"></s:text> </th>
																						<th align="center"><s:text name="label.master.status"></s:text> </th>
																					</tr>
																					</thead>
																					<tbody class="rowevencolor">
																						<s:iterator value="comprehensiveRateList" var="list">
																							<tr>
																								<td align="center"><s:property value="%{SEATING_TON_START}"/></td>
																								<td align="center"><s:property value="%{SEATING_TON_END}"/></td>
																								<td align="center"><s:property value="%{CC_GVW_START_RANGE}"/></td>
																								<td align="center"><s:property value="%{CC_GVW_END_RANGE}"/></td>
																								<td align="center"><s:property value="%{UAE_LISCENCE_START}"/></td>
																								<td align="center"><s:property value="%{UAE_LISCENCE_END}"/></td>
																								<td align="center"><s:property value="%{DRIVER_AGE_FROM}"/></td>
																								<td align="center"><s:property value="%{DRIVER_AGE_TO}"/></td>
																								<td align="center"><s:property value="%{SUMINSURED_START}"/></td>
																								<td align="center"><s:property value="%{SUMINSURED_END}"/></td>																							
																								<td align="center"><s:property value="%{POLICY_START_DATE}"/></td>
																								<td align="center"><s:property value="%{POLICY_END_DATE}"/></td>
																								<td align="center"><s:property value="%{STATUS}"/></td>
																							</tr>
																						</s:iterator>
																					</tbody>
																				</table>
																			</div>
																		</s:if>
																		<s:else>
																			<font color="green"><s:text name="label.no.records.available"></s:text></font>
																		</s:else>
																	</div>
																</div>
															</div>
														</div>
<!-- 													</div> -->
												</td>
	  										</tr>
	  										<tr>
	  											<td width="40%">
	  												<label><s:text name="label.master.uae.license" /> <font color="red">*</font> </label>
	  											</td>
	  											<td width="20%">
  													<s:textfield name="UAELiscenceStart" id="UAELiscenceStart" cssClass="form-control numericOnly" maxlength="5"></s:textfield>
	  											</td>
	  											<td width="20%">
  													<s:textfield name="UAELiscenceEnd" id="UAELiscenceEnd" cssClass="form-control numericOnly" onchange="callAjax('checkUAELiscence')" maxlength="5"></s:textfield>
	  											</td>
	  											<td width="15%" align="center">
	  												<div class="col-xs-6">
	  													<button class="btn btn-info" style="border-radius:0px;" type="button" onclick="$('#UAELiscence').modal('show');">
															<span class="glyphicon glyphicon-eye-open"> </span>
														</button>
	  												</div>
	  												<div class="col-xs-6" id="checkUAELiscence" >
	  												
	  												</div>
													<div id="UAELiscence" class="modal fade" role="dialog">
														<div class="modal-dialog modal-md">
															<div class="modal-content">
																<div class="modal-header">
																	<button type="button" class="close" data-dismiss="modal">&times;</button>
																	<h4 class="modal-title"><s:text name="label.master.uae.license" /> </h4>
																</div>
																<div class="modal-body">
																	<s:iterator value="modalHeaderList" var="list"  >
																		<div class="row">
																			<div class="col-xs-12 col-sm-12 col-md-6 col-lg-4">
																				<s:text name="label.master.policyType" /> &nbsp;:&nbsp; <b> <s:property value="%{POLICYTYPE_DESC_ENGLISH}"/></b>
																			</div>
																			<div class="col-xs-12 col-sm-12 col-md-6 col-lg-4">
																				<s:text name="label.master.vehicleType" /> &nbsp;:&nbsp; <b>  <s:property value="%{VEHICLETYPE_DESC}"/> </b>
																			</div>
																			<div class="col-xs-12 col-sm-12 col-md-6 col-lg-4">
																				<s:text name="label.master.bodyType" /> &nbsp;:&nbsp; <b> <s:property value="%{TYPE_OF_BODY_NAME}"/> </b>
																			</div>
																		</div>
																	</s:iterator>
																	<div class="row">
																		<s:if test='comprehensiveRateList.size()>0'>
																			<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 table-responsive">
																				<table class="table table-bordered table-hover" id="gridTableMake" cellspacing="0" width="100%" >
																					<thead class="bluecolortable">
																					<tr>
																						<th align="center"><s:text name="label.master.seating.ton.start"></s:text> </th>
																						<th align="center"><s:text name="label.master.seating.ton.end"></s:text> </th>
																						<th align="center"><s:text name="label.master.cc.gvw.start.range"></s:text> </th>
																						<th align="center"><s:text name="label.master.cc.gvw.end.range"></s:text> </th>
																						<th align="center"><s:text name="label.master.uae.license.start"></s:text> </th>
																						<th align="center"><s:text name="label.master.uae.license.end"></s:text> </th>
																						<th align="center"><s:text name="label.master.driver.age.from"></s:text> </th>
																						<th align="center"><s:text name="label.master.driver.age.to"></s:text> </th>
																						<th align="center"><s:text name="label.master.suminsured.start"></s:text> </th>
																						<th align="center"><s:text name="label.master.suminsured.end"></s:text> </th>
																						<th align="center"><s:text name="label.master.policy.start.date"></s:text> </th>
																						<th align="center"><s:text name="label.master.policy.end.date"></s:text> </th>
																						<th align="center"><s:text name="label.master.status"></s:text> </th>
																					</tr>
																					</thead>
																					<tbody class="rowevencolor">
																						<s:iterator value="comprehensiveRateList" var="list">
																							<tr>
																								<td align="center"><s:property value="%{SEATING_TON_START}"/></td>
																								<td align="center"><s:property value="%{SEATING_TON_END}"/></td>
																								<td align="center"><s:property value="%{CC_GVW_START_RANGE}"/></td>
																								<td align="center"><s:property value="%{CC_GVW_END_RANGE}"/></td>
																								<td align="center"><s:property value="%{UAE_LISCENCE_START}"/></td>
																								<td align="center"><s:property value="%{UAE_LISCENCE_END}"/></td>
																								<td align="center"><s:property value="%{DRIVER_AGE_FROM}"/></td>
																								<td align="center"><s:property value="%{DRIVER_AGE_TO}"/></td>
																								<td align="center"><s:property value="%{SUMINSURED_START}"/></td>
																								<td align="center"><s:property value="%{SUMINSURED_END}"/></td>																							
																								<td align="center"><s:property value="%{POLICY_START_DATE}"/></td>
																								<td align="center"><s:property value="%{POLICY_END_DATE}"/></td>
																								<td align="center"><s:property value="%{STATUS}"/></td>
																							</tr>
																						</s:iterator>
																					</tbody>
																				</table>
																				</div>
																		</s:if>
																		<s:else>
																			<font color="green"><s:text name="label.no.records.available"></s:text></font>
																		</s:else>
																	</div>
																</div>
															</div>
														</div>
													</div>
												</td>
	  										</tr>
	  										<tr>
	  											<td width="40%">
	  												<label><s:text name="label.master.driver.age" /> <font color="red">*</font> </label>
	  											</td>
	  											<td width="20%">
  													<s:textfield name="driverAgeFrom" id="driverAgeFrom" cssClass="form-control numericOnly" maxlength="2"></s:textfield>
	  											</td>
	  											<td width="20%">
  													<s:textfield name="driverAgeTo" id="driverAgeTo" cssClass="form-control numericOnly" maxlength="2" onchange="callAjax('checkDriverAge')"></s:textfield>
	  											</td>
	  											<td width="15%" align="center">
	  												<div class="col-xs-6">
	  													<button class="btn btn-info" style="border-radius:0px;" type="button" onclick="$('#driverAge').modal('show');">
															<span class="glyphicon glyphicon-eye-open"> </span>
														</button>
	  												</div>
	  												<div class="col-xs-6" id="checkDriverAge" >
	  												
	  												</div>
													<div id="driverAge" class="modal fade" role="dialog">
														<div class="modal-dialog modal-md">
															<div class="modal-content">
																<div class="modal-header">
																	<button type="button" class="close" data-dismiss="modal">&times;</button>
																	<h4 class="modal-title"><s:text name="label.master.driver.age" /> </h4>
																</div>
																<div class="modal-body">
																	<s:iterator value="modalHeaderList" var="list"  >
																		<div class="row">
																			<div class="col-xs-12 col-sm-12 col-md-6 col-lg-4">
																				<s:text name="label.master.policyType" /> &nbsp;:&nbsp; <b> <s:property value="%{POLICYTYPE_DESC_ENGLISH}"/></b>
																			</div>
																			<div class="col-xs-12 col-sm-12 col-md-6 col-lg-4">
																				<s:text name="label.master.vehicleType" /> &nbsp;:&nbsp; <b>  <s:property value="%{VEHICLETYPE_DESC}"/> </b>
																			</div>
																			<div class="col-xs-12 col-sm-12 col-md-6 col-lg-4">
																				<s:text name="label.master.bodyType" /> &nbsp;:&nbsp; <b> <s:property value="%{TYPE_OF_BODY_NAME}"/> </b>
																			</div>
																		</div>
																	</s:iterator>
																	<div class="row">
																		<s:if test='comprehensiveRateList.size()>0'>
																			<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 table-responsive">
																				<table class="table table-bordered">
																					<thead>
																					<tr>
																						<th align="center"><s:text name="label.master.seating.ton.start"></s:text> </th>
																						<th align="center"><s:text name="label.master.seating.ton.end"></s:text> </th>
																						<th align="center"><s:text name="label.master.cc.gvw.start.range"></s:text> </th>
																						<th align="center"><s:text name="label.master.cc.gvw.end.range"></s:text> </th>
																						<th align="center"><s:text name="label.master.uae.license.start"></s:text> </th>
																						<th align="center"><s:text name="label.master.uae.license.end"></s:text> </th>
																						<th align="center"><s:text name="label.master.driver.age.from"></s:text> </th>
																						<th align="center"><s:text name="label.master.driver.age.to"></s:text> </th>
																						<th align="center"><s:text name="label.master.suminsured.start"></s:text> </th>
																						<th align="center"><s:text name="label.master.suminsured.end"></s:text> </th>
																						<th align="center"><s:text name="label.master.policy.start.date"></s:text> </th>
																						<th align="center"><s:text name="label.master.policy.end.date"></s:text> </th>
																						<th align="center"><s:text name="label.master.status"></s:text> </th>
																					</tr>
																					</thead>
																					<tbody>
																						<s:iterator value="comprehensiveRateList" var="list">
																							<tr>
																								<td align="center"><s:property value="%{SEATING_TON_START}"/></td>
																								<td align="center"><s:property value="%{SEATING_TON_END}"/></td>
																								<td align="center"><s:property value="%{CC_GVW_START_RANGE}"/></td>
																								<td align="center"><s:property value="%{CC_GVW_END_RANGE}"/></td>
																								<td align="center"><s:property value="%{UAE_LISCENCE_START}"/></td>
																								<td align="center"><s:property value="%{UAE_LISCENCE_END}"/></td>
																								<td align="center"><s:property value="%{DRIVER_AGE_FROM}"/></td>
																								<td align="center"><s:property value="%{DRIVER_AGE_TO}"/></td>
																								<td align="center"><s:property value="%{SUMINSURED_START}"/></td>
																								<td align="center"><s:property value="%{SUMINSURED_END}"/></td>																							
																								<td align="center"><s:property value="%{POLICY_START_DATE}"/></td>
																								<td align="center"><s:property value="%{POLICY_END_DATE}"/></td>
																								<td align="center"><s:property value="%{STATUS}"/></td>
																							</tr>
																						</s:iterator>
																					</tbody>
																				</table>
																			</div>
																		</s:if>
																		<s:else>
																			<font color="green"><s:text name="label.no.records.available"></s:text></font>
																		</s:else>
																	</div>
																</div>
															</div>
														</div>
													</div>
												</td>
	  										</tr>
	  										<tr>
	  											<td width="40%">
	  												<label><s:text name="label.master.sum.insured" /> <font color="red">*</font> </label>
	  											</td>
	  											<td width="20%">
  													<s:textfield name="suminsuredStart" id="suminsuredStart" cssClass="form-control numericOnly" maxlength="10"></s:textfield>
	  											</td>
	  											<td width="20%">
  													<s:textfield name="suminsuredEnd" id="suminsuredEnd" cssClass="form-control numericOnly" maxlength="10" onchange="callAjax('checkSumInsureSd')"></s:textfield>
	  											</td>
	  											<td width="15%" align="center">
	  												<div class="col-xs-6">
	  													<button class="btn btn-info" style="border-radius:0px;" type="button" onclick="$('#sumInsured').modal('show');">
															<span class="glyphicon glyphicon-eye-open"> </span>
														</button>
	  												</div>
	  												<div class="col-xs-6" id="checkSumInsureSd" >
	  												
	  												</div>
													<div id="sumInsured" class="modal fade" role="dialog">
														<div class="modal-dialog modal-md">
															<div class="modal-content">
																<div class="modal-header">
																	<button type="button" class="close" data-dismiss="modal">&times;</button>
																	<h4 class="modal-title"><s:text name="label.master.sum.insured" /> </h4>
																</div>
																<div class="modal-body">
																	<s:iterator value="modalHeaderList" var="list"  >
																		<div class="row">
																			<div class="col-xs-12 col-sm-12 col-md-6 col-lg-4">
																				<s:text name="label.master.policyType" /> &nbsp;:&nbsp; <b> <s:property value="%{POLICYTYPE_DESC_ENGLISH}"/></b>
																			</div>
																			<div class="col-xs-12 col-sm-12 col-md-6 col-lg-4">
																				<s:text name="label.master.vehicleType" /> &nbsp;:&nbsp; <b>  <s:property value="%{VEHICLETYPE_DESC}"/> </b>
																			</div>
																			<div class="col-xs-12 col-sm-12 col-md-6 col-lg-4">
																				<s:text name="label.master.bodyType" /> &nbsp;:&nbsp; <b> <s:property value="%{TYPE_OF_BODY_NAME}"/> </b>
																			</div>
																		</div>
																	</s:iterator>
																	<div class="row">
																		<s:if test='comprehensiveRateList.size()>0'>
																			<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 table-responsive">
																				<table class="table table-bordered table-hover" id="gridTableMake" cellspacing="0" width="100%" >
																					<thead class="bluecolortable">
																					<tr>
																						<th align="center"><s:text name="label.master.seating.ton.start"></s:text> </th>
																						<th align="center"><s:text name="label.master.seating.ton.end"></s:text> </th>
																						<th align="center"><s:text name="label.master.cc.gvw.start.range"></s:text> </th>
																						<th align="center"><s:text name="label.master.cc.gvw.end.range"></s:text> </th>
																						<th align="center"><s:text name="label.master.uae.license.start"></s:text> </th>
																						<th align="center"><s:text name="label.master.uae.license.end"></s:text> </th>
																						<th align="center"><s:text name="label.master.driver.age.from"></s:text> </th>
																						<th align="center"><s:text name="label.master.driver.age.to"></s:text> </th>
																						<th align="center"><s:text name="label.master.suminsured.start"></s:text> </th>
																						<th align="center"><s:text name="label.master.suminsured.end"></s:text> </th>
																						<th align="center"><s:text name="label.master.policy.start.date"></s:text> </th>
																						<th align="center"><s:text name="label.master.policy.end.date"></s:text> </th>
																						<th align="center"><s:text name="label.master.status"></s:text> </th>
																					</tr>
																					</thead>
																					<tbody class="rowevencolor">
																						<s:iterator value="comprehensiveRateList" var="list">
																							<tr>
																								<td align="center"><s:property value="%{SEATING_TON_START}"/></td>
																								<td align="center"><s:property value="%{SEATING_TON_END}"/></td>
																								<td align="center"><s:property value="%{CC_GVW_START_RANGE}"/></td>
																								<td align="center"><s:property value="%{CC_GVW_END_RANGE}"/></td>
																								<td align="center"><s:property value="%{UAE_LISCENCE_START}"/></td>
																								<td align="center"><s:property value="%{UAE_LISCENCE_END}"/></td>
																								<td align="center"><s:property value="%{DRIVER_AGE_FROM}"/></td>
																								<td align="center"><s:property value="%{DRIVER_AGE_TO}"/></td>
																								<td align="center"><s:property value="%{SUMINSURED_START}"/></td>
																								<td align="center"><s:property value="%{SUMINSURED_END}"/></td>																							
																								<td align="center"><s:property value="%{POLICY_START_DATE}"/></td>
																								<td align="center"><s:property value="%{POLICY_END_DATE}"/></td>
																								<td align="center"><s:property value="%{STATUS}"/></td>
																							</tr>
																						</s:iterator>
																					</tbody>
																				</table>
																			</div>
																		</s:if>
																		<s:else>
																			<font color="green"><s:text name="label.no.records.available"></s:text></font>
																		</s:else>
																	</div>
																</div>
															</div>
														</div>
													</div>
												</td>
	  										</tr>
	  										<tr>
	  											<td width="40%">
	  												<label><s:text name="Policy Date" /> <font color="red">*</font> </label>
	  											</td>
	  											<td width="20%">
  													<s:textfield name="policyStartDate" id="policyStartDate" cssClass="form-control datepicker "></s:textfield>
	  											</td>
	  											<td width="20%">
  													<s:textfield name="policyEndDate" id="policyEndDate" cssClass="form-control datepicker "></s:textfield>
	  											</td>
	  											<td width="15%" align="center">
	  												<div class="col-xs-6">
	  													<button class="btn btn-info" style="border-radius:0px;" type="button" onclick="$('#policyDate').modal('show');">
															<span class="glyphicon glyphicon-eye-open"> </span>
														</button>
	  												</div>
	  												<div class="col-xs-6" id="checkPolicyDate" >
	  												
	  												</div>
													<div id="policyDate" class="modal fade" role="dialog">
														<div class="modal-dialog modal-md">
															<div class="modal-content">
																<div class="modal-header">
																	<button type="button" class="close" data-dismiss="modal">&times;</button>
																	<h4 class="modal-title"><s:text name="label.master.sum.insured" /> </h4>
																</div>
																<div class="modal-body">
																	<s:iterator value="modalHeaderList" var="list"  >
																		<div class="row">
																			<div class="col-xs-12 col-sm-12 col-md-6 col-lg-4">
																				<s:text name="label.master.policyType" /> &nbsp;:&nbsp; <b> <s:property value="%{POLICYTYPE_DESC_ENGLISH}"/></b>
																			</div>
																			<div class="col-xs-12 col-sm-12 col-md-6 col-lg-4">
																				<s:text name="label.master.vehicleType" /> &nbsp;:&nbsp; <b>  <s:property value="%{VEHICLETYPE_DESC}"/> </b>
																			</div>
																			<div class="col-xs-12 col-sm-12 col-md-6 col-lg-4">
																				<s:text name="label.master.bodyType" /> &nbsp;:&nbsp; <b> <s:property value="%{TYPE_OF_BODY_NAME}"/> </b>
																			</div>
																		</div>
																	</s:iterator>
																	<div class="row">
																		<s:if test='comprehensiveRateList.size()>0'>
																			<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 table-responsive">
																			<table class="table table-bordered table-hover" id="gridTableMake" cellspacing="0" width="100%" >
																				<thead class="bluecolortable">
																					<tr>
																						<th align="center"><s:text name="label.master.seating.ton.start"></s:text> </th>
																						<th align="center"><s:text name="label.master.seating.ton.end"></s:text> </th>
																						<th align="center"><s:text name="label.master.cc.gvw.start.range"></s:text> </th>
																						<th align="center"><s:text name="label.master.cc.gvw.end.range"></s:text> </th>
																						<th align="center"><s:text name="label.master.uae.license.start"></s:text> </th>
																						<th align="center"><s:text name="label.master.uae.license.end"></s:text> </th>
																						<th align="center"><s:text name="label.master.driver.age.from"></s:text> </th>
																						<th align="center"><s:text name="label.master.driver.age.to"></s:text> </th>
																						<th align="center"><s:text name="label.master.suminsured.start"></s:text> </th>
																						<th align="center"><s:text name="label.master.suminsured.end"></s:text> </th>
																						<th align="center"><s:text name="label.master.policy.start.date"></s:text> </th>
																						<th align="center"><s:text name="label.master.policy.end.date"></s:text> </th>
																						<th align="center"><s:text name="label.master.status"></s:text> </th>
																					</tr>
																					</thead>
																					<tbody class="rowevencolor">
																						<s:iterator value="comprehensiveRateList" var="list">
																							<tr>
																								<td align="center"><s:property value="%{SEATING_TON_START}"/></td>
																								<td align="center"><s:property value="%{SEATING_TON_END}"/></td>
																								<td align="center"><s:property value="%{CC_GVW_START_RANGE}"/></td>
																								<td align="center"><s:property value="%{CC_GVW_END_RANGE}"/></td>
																								<td align="center"><s:property value="%{UAE_LISCENCE_START}"/></td>
																								<td align="center"><s:property value="%{UAE_LISCENCE_END}"/></td>
																								<td align="center"><s:property value="%{DRIVER_AGE_FROM}"/></td>
																								<td align="center"><s:property value="%{DRIVER_AGE_TO}"/></td>
																								<td align="center"><s:property value="%{SUMINSURED_START}"/></td>
																								<td align="center"><s:property value="%{SUMINSURED_END}"/></td>																							
																								<td align="center"><s:property value="%{POLICY_START_DATE}"/></td>
																								<td align="center"><s:property value="%{POLICY_END_DATE}"/></td>
																								<td align="center"><s:property value="%{STATUS}"/></td>
																							</tr>
																						</s:iterator>
																					</tbody>
																				</table>
																			</div>
																		</s:if>
																		<s:else>
																			<font color="green"><s:text name="label.no.records.available"></s:text></font>
																		</s:else>
																	</div>
																</div>
															</div>
														</div>
													</div>
												</td>
	  										</tr>
	  									</tbody>
	  								</table>
	  							</div>
	  							<br/>
	  							<div class="col-xs-12 col-sm-6 col-md-6">
	  							    <div class="form-group">
									<label><s:text name="label.userName" /><font color="red">*</font></label>
									<s:select name="userNameLogin" id="userNameLogin" list="userNameLoginList" headerKey="" headerValue="---Select---"  listKey="AGENCY_CODE" listValue="USERNAME" cssClass="form-control inputBoxS" disabled='%{(mode =="editCompreRate")?"true":"false"}'/>
								    </div>
								</div>
								<div class="col-xs-12 col-sm-4 col-md-4">
									<div class="form-group">
    									<label><s:text name="label.master.motor.rate" /> <font color="red">*</font> </label>
    									<s:textfield name="motorRate" id="motorRate" cssClass="form-control decimalOnly" maxlength="5"></s:textfield>
									</div>
								</div>
								<div class="col-xs-12 col-sm-4 col-md-4">
									<div class="form-group">
    									<label><s:text name="label.master.minimum.premium" /> <font color="red">*</font> </label>
    									<s:textfield name="minimumPremium" id="minimumPremium" cssClass="form-control decimalOnly" maxlength="10"></s:textfield>
									</div>
								</div>
								<div class="col-xs-12 col-sm-4 col-md-4">
									<div class="form-group">
    									<label><s:text name="label.master.online.percent" /> <font color="red">*</font> </label>
    									<s:textfield name="onlinePercent" id="onlinePercent" cssClass="form-control decimalOnly" maxlength="5"></s:textfield>
									</div>
								</div>
								<div class="col-xs-12 col-sm-4 col-md-4">
									<div class="form-group">
    									<label><s:text name="label.master.effective.date" /> <font color="red">*</font> </label>
    									<s:textfield name="effectiveDate" id="effectiveDate" cssClass="form-control datepicker"></s:textfield>
									</div>
								</div>
								<div class="col-xs-12 col-sm-4 col-md-4">
									<div class="form-group">
    									<label><s:text name="label.master.deduction.percent" /> <font color="red">*</font> </label>
    									<s:textfield name="deductionPercent" id="deductionPercent" cssClass="form-control decimalOnly" maxlength="5"></s:textfield>
									</div>
								</div>
								<div class="col-xs-12 col-sm-4 col-md-4">
									<div class="form-group">
    									<label><s:text name="label.master.deduction.amount" /> <font color="red">*</font> </label>
    									<s:textfield name="deductionAmount" id="deductionAmount" cssClass="form-control decimalOnly" maxlength="10"></s:textfield>
									</div>
								</div>
								<div class="col-xs-12 col-sm-4 col-md-4">
									<div class="form-group">
    									<label><s:text name="label.master.deduction.description" /></label>
    									<s:textarea name="deductionDesc" id="deductionDesc" rows="3" cssClass="form-control" maxlength="500"/>
									</div>
								</div>
								<div class="col-xs-12 col-sm-4 col-md-4">
									<div class="form-group">
										<label><s:text name="label.master.remarks" /></label><br/>
										<s:textfield name="remarks" id="remarks" cssClass="form-control" maxlength="100"></s:textfield>
									</div>
								</div>
								<div class="col-xs-12 col-sm-4 col-md-4">
									<div class="form-group">
										<label><s:text name="label.master.status" /><font color="red">*</font></label><br/>
										<s:radio list="#{'Y':'Activate','N':'Deactivate'}" name="status" id="status" value='%{status==null?"N":status}' />
									</div>
								</div>
								<div class="col-xs-12 col-sm-12 col-md-12" align="center">
									<s:submit cssClass="btn btn-sm btn-success" value="Submit" onclick="disableForm(this.form,false,'');funSubmit();"></s:submit>
									<s:submit cssClass="btn btn-sm btn-danger" value="Back" onclick="funBack();"></s:submit>
								</div>
							</div> <br/>
						</div>
					</div>
				</div>
			</div> <br/>			
		</div>
	</div>
</div>
			<s:hidden name="policyType"></s:hidden>
			<s:hidden name="vehicleType"></s:hidden>
			<s:hidden name="bodyType"></s:hidden>
			<s:hidden name="subRateId"></s:hidden>
			<s:hidden name="rateId"></s:hidden>
	</s:form>

<script type="text/javascript">
function funBack(){
	document.rateConfig.action='comprehensiveMotorAdminNew.action';
	document.rateConfig.submit();
}
function funSubmit(){
	document.rateConfig.action='insCompreRateMotorAdminNew.action?mode=<s:property value="mode"/>';
	document.rateConfig.submit();
}
function callAjax(id){
	if(id=='checkSeatingTon'){
		if($("#seatingTonStart").val().length != 0 && $("#seatingTonEnd").val().length != 0  && '<s:property value="mode"/>' == 'editCompreRate' ){
			ajax1(id);
		}
	}
	else if(id=='checkCCGVWRange'){
		if($("#CCGVWStartRange").val().length != 0 && $("#CCGVWEndRange").val().length != 0  && '<s:property value="mode"/>' == 'editCompreRate' ){
			ajax1(id);
		}
	}
	else if(id=='checkUAELiscence'){
		if($("#UAELiscenceStart").val().length != 0 && $("#UAELiscenceEnd").val().length != 0  && '<s:property value="mode"/>' == 'editCompreRate' ){
			ajax1(id);
		}
	}
	else if(id=='checkDriverAge'){
		if($("#driverAgeFrom").val().length != 0 && $("#driverAgeTo").val().length != 0  && '<s:property value="mode"/>' == 'editCompreRate' ){
			ajax1(id);
		}
	}
	else if(id=='checkSumInsureSd'){
		if($("#suminsuredStart").val().length != 0 && $("#suminsuredEnd").val().length != 0  && '<s:property value="mode"/>' == 'editCompreRate'){
			ajax1(id);
		}
	}
}
function ajax1(id){
	var rateConfig = document.getElementById("rateConfig");
		var fd = new FormData(rateConfig );
	$.ajax({
        url: '${pageContext.request.contextPath}/ajaxMotorAdminNew.action?reqFrom='+id+'&mode=<s:property value="mode"/>',
        data: fd,
        cache: false,
        processData: false,
        contentType: false,
        type: 'POST',
        success: function (dataofconfirm) {
        	document.getElementById(id).innerHTML=dataofconfirm;
        	$('#'+id+'Modal').modal('show');
        }
    });
}
function openModel(id){
	$(id).modal('show');
}
$(function() {
	try {
		$('#policyEndDate').datepicker({
			todayHighlight: true,
			format: "dd/mm/yyyy"
		}).on('changeDate', function(e){
		    $(this).datepicker('hide');
		});
	} catch(err) {console.error(err);}
});
</script>
</body>
</html>   
