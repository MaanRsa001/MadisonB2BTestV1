<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ page import="nl.captcha.Captcha"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
		<script type="text/javascript" src="js/common.js"></script>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/dataTables/css/dataTables.responsive.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/dataTables/css/dataTables.jqueryui.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/dataTables/css/dataTables.tableTools.css">
	<script type="text/javascript" src="${pageContext.request.contextPath}/dataTables/js/dataTables.responsive.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/dataTables/js/dataTables.tableTools.js"></script>
	
<%-- 		<script type="text/javascript" src="js/tcal.js"></script> --%>
<!-- 		<link href="css/styles.css" rel="stylesheet" type="text/css" /> -->
<%-- 		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/cssbootstrap/bootstrap.min.css" /> --%>
<%-- 		<script type="text/javascript" src="pages/admin/ratingEngine/menu.js"> </script> --%>
<%-- 		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/dataTables/css/dataTables.responsive.css"> --%>
<%-- 		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/dataTables/css/dataTables.jqueryui.css"> --%>
<%-- 		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/dataTables/css/dataTables.tableTools.css"> --%>
<%-- 		<link href="<%=request.getContextPath()%>/dataTables/css/jquery-start-ui.css" rel="stylesheet" type="text/css" /> --%>
<%-- 		<script type="text/javascript" src="<%=request.getContextPath()%>/dataTables/js/jquery-1.11.1.min.js"></script> --%>
<%-- 		<script type="text/javascript" src="<%=request.getContextPath()%>/dataTables/js/jquery.dataTables.min.js"></script> --%>
<%-- 		<script type="text/javascript" src="<%=request.getContextPath()%>/dataTables/js/dataTables.jqueryui.js"></script> --%>
<%-- 		<script type="text/javascript" src="<%=request.getContextPath()%>/dataTables/js/dataTables.responsive.js"></script> --%>
<%-- 		<script type="text/javascript" src="<%=request.getContextPath()%>/dataTables/js/dataTables.tableTools.js"></script> --%>
		<script type="text/javascript">
			jQuery(function ($) {
				try {
					var data = $('#paymentDetailsList').dataTable( {
						"lengthMenu": [[10, 25, 50, -1], [10, 25, 50, "All"]],
						"order": [[ 0, "asc" ]],
						"columnDefs": [ {
				          "targets": 'no-sort',
				          "orderable": false
					    } ],
						responsive: true,
						language: {
					          //customize pagination prev and next buttons: use arrows instead of words
					          'paginate': {
					            'previous': '<span><i class="fas fa-chevron-circle-left"></i></span>',
					            'next': '<span><i class="fas fa-chevron-circle-right"></i></span>'
					          },
					          //customize number of elements to be displayed
					          "lengthMenu": '<select class="form-control input-sm">' +
					            '<option value="10">10</option>' +
					            '<option value="20">20</option>' +
					            '<option value="30">30</option>' +
					            '<option value="40">40</option>' +
					            '<option value="50">50</option>' +
					            '<option value="-1">All</option>' +
					            '</select>'
					        }
					});
				} catch(err){}
			} );
			
			jQuery(function ($) {
				try {
					var data = $('#paidVehicleDetailsTable').dataTable( {
						"lengthMenu": [[10, 25, 50, -1], [10, 25, 50, "All"]],
						"order": [[ 0, "asc" ]],
						"columnDefs": [ {
				          "targets": 'no-sort',
				          "orderable": false
					    } ],
						responsive: true,
						language: {
					          //customize pagination prev and next buttons: use arrows instead of words
					          'paginate': {
					            'previous': '<span><i class="fas fa-chevron-circle-left"></i></span>',
					            'next': '<span><i class="fas fa-chevron-circle-right"></i></span>'
					          },
					          //customize number of elements to be displayed
					          "lengthMenu": '<select class="form-control input-sm">' +
					            '<option value="10">10</option>' +
					            '<option value="20">20</option>' +
					            '<option value="30">30</option>' +
					            '<option value="40">40</option>' +
					            '<option value="50">50</option>' +
					            '<option value="-1">All</option>' +
					            '</select>'
					        }
					});
				} catch(err){}
			} );
			
			jQuery(function ($) {
				try {
					var data = $('#vehicleDetailsTable').dataTable( {
						"lengthMenu": [[10, 25, 50, -1], [10, 25, 50, "All"]],
						"order": [[ 0, "asc" ]],
						"columnDefs": [ {
				          "targets": 'no-sort',
				          "orderable": false
					    } ],
						responsive: true,
						language: {
					          //customize pagination prev and next buttons: use arrows instead of words
					          'paginate': {
					            'previous': '<span><i class="fas fa-chevron-circle-left"></i></span>',
					            'next': '<span><i class="fas fa-chevron-circle-right"></i></span>'
					          },
					          //customize number of elements to be displayed
					          "lengthMenu": '<select class="form-control input-sm">' +
					            '<option value="10">10</option>' +
					            '<option value="20">20</option>' +
					            '<option value="30">30</option>' +
					            '<option value="40">40</option>' +
					            '<option value="50">50</option>' +
					            '<option value="-1">All</option>' +
					            '</select>'
					        }
					});
				} catch(err){}
			} );
			
			jQuery(function ($) {
				try {
					var data = $('#renewalDetailsTable').dataTable( {
						"lengthMenu": [[10, 25, 50, -1], [10, 25, 50, "All"]],
						"order": [[ 0, "asc" ]],
						"columnDefs": [ {
				          "targets": 'no-sort',
				          "orderable": false
					    } ],
						responsive: true,
						language: {
					          //customize pagination prev and next buttons: use arrows instead of words
					          'paginate': {
					            'previous': '<span><i class="fas fa-chevron-circle-left"></i></span>',
					            'next': '<span><i class="fas fa-chevron-circle-right"></i></span>'
					          },
					          //customize number of elements to be displayed
					          "lengthMenu": '<select class="form-control input-sm">' +
					            '<option value="10">10</option>' +
					            '<option value="20">20</option>' +
					            '<option value="30">30</option>' +
					            '<option value="40">40</option>' +
					            '<option value="50">50</option>' +
					            '<option value="-1">All</option>' +
					            '</select>'
					        }
					});
				} catch(err){}
			} );
	 	</script>
	 	<style type="text/css">
	 	.dataTables_info {
		    font-size: 14px;
		}
	 	</style>
	</head>
	<body>
		<s:form id="quickRenewal" name="quickRenewal" method="post" action="" theme="simple">
		<div class="row">
			<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
				<div class="container vehDtl">
			  		<div class="Card_Parent ">
		            	<div class="card card-5">
							<div class="panel panel-heading">
								<h3><s:text name="QUICK RENEWAL"/></h3><hr>
							</div>
							<div class="panel-body">
								<s:if test='!"Y".equalsIgnoreCase(pMode)'>
									<div class="container-fluid">
											<div class="row">
												<s:if test="hasActionErrors()"> <font color="red"><s:actionerror/></font> </s:if>
												<font color="green"><s:actionmessage cssStyle="list-style: none;" /></font>
											</div>
											<br>
											<div class="row" style="padding: 0 15px 0 15px;">
												<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
													<h4><u><s:text name="Customer Details" /></u></h4>
													<br/>
													<div class="row">
								                        <div class="col-md-6">
								                            <div class="row">
								                                <div class="col-md-6 col-12">
								                                    <label class="LabelHeading"><b><s:text name="Insured Name" /></b></label>
								                                </div>
								                                <div class="col-md-6 col-12">
								                                    <label class="labelValues"><s:property value="custInsuredName"/></label>
								                                </div>
								                            </div>
								                        </div>
								                        <div class="col-md-6">
								                        	<div class="row">
								                        		<div class="col-md-6 col-12">
								                                    <label class="LabelHeading"><b><s:text name="Branch"/></b></label>
								                                </div>
								                                <div class="col-md-6 col-12">
								                                    <label class="labelValues"><s:property value="custBranch"/></label>
								                                </div>
							                                </div>
								                        </div>
								                        <div class="col-md-6">
								                        	<div class="row">
								                        		<div class="col-md-6 col-12">
								                                    <label class="LabelHeading"><b><s:text name="Product"/></b></label>
								                                </div>
								                                <div class="col-md-6 col-12">
								                                    <label class="labelValues"><s:property value="custProduct"/></label>
								                                </div>
							                                </div>
								                        </div>
								                        <div class="col-md-6">
								                        	<div class="row">
								                        		<div class="col-md-6 col-12">
								                                    <label class="LabelHeading"><b><s:text name="label.policynum"/></b></label>
								                                </div>
								                                <div class="col-md-6 col-12">
								                                    <label class="labelValues"><s:property value="policyNo"/></label>
								                                </div>
							                                </div>
								                        </div>
								                        <div class="col-md-6">
								                        	<div class="row">
								                        		<div class="col-md-6 col-12">
								                                    <label class="LabelHeading"><b><s:text name="Previous Inception Date"/></b></label>
								                                </div>
								                                <div class="col-md-6 col-12">
								                                    <label class="labelValues"><s:property value="prevInceptionDate"/></label>
								                                </div>
							                                </div>
								                        </div>
								                        <div class="col-md-6">
								                        	<div class="row">
								                        		<div class="col-md-6 col-12">
								                                    <label class="LabelHeading"><b><s:text name="Previous Expiry Date"/></b></label>
								                                </div>
								                                <div class="col-md-6 col-12">
								                                    <label class="labelValues"><s:property value="prevExpiryDate"/></label>
								                                </div>
							                                </div>
								                        </div>
								                        <div class="col-md-6">
								                        	<div class="row">
								                        		<div class="col-md-6 col-12">
								                                    <label class="LabelHeading"><b><s:text name="Period of Insurance"/></b></label>
								                                </div>
								                                <div class="col-md-6 col-12">
								                                    <label class="labelValues"><s:property value="periodOfInsurance"/></label>
								                                </div>
							                                </div>
								                        </div>
								                        <div class="col-md-6">
								                        	<div class="row">
								                        		<div class="col-md-6 col-12">
								                                    <label class="LabelHeading"><b><s:text name="Renewal Inception Date"/></b></label>
								                                </div>
								                                <div class="col-md-6 col-12">
								                                    <label class="labelValues"><s:property value="renewIncepDate"/></label>
								                                </div>
							                                </div>
								                        </div>
								                        <div class="col-md-6">
								                        	<div class="row">
								                        		<div class="col-md-6 col-12">
								                                    <label class="LabelHeading"><b><s:text name="Renewal Expiry Date"/></b></label>
								                                </div>
								                                <div class="col-md-6 col-12">
								                                    <label class="labelValues"><s:property value="renewExpiryDate"/></label>
								                                </div>
							                                </div>
								                        </div>
								                        <div class="col-md-6">
								                        	<div class="row">
								                        		<div class="col-md-6 col-12">
								                                    <label class="LabelHeading"><b><s:text name="Period of Renewal Days"/></b></label>
								                                </div>
								                                <div class="col-md-6 col-12">
								                                    <label class="labelValues"><s:property value="periodOfInsurance"/></label>
								                                </div>
							                                </div>
								                        </div>
								                        <div class="col-md-6">
								                        	<div class="row">
								                        		<div class="col-md-6 col-12">
								                                    <label class="LabelHeading"><b><s:text name="Currency Type"/></b></label>
								                                </div>
								                                <div class="col-md-6 col-12">
								                                    <label class="labelValues"><s:property value="currency"/></label>
								                                </div>
							                                </div>
								                        </div>
								                    </div>
												<br>
												</div>
											</div>
										<br/>
										<div class="row">
											<div class="col-xs-12 col-sm-6 col-md-4 col-lg-4">
												<div class="text"><b><s:text name="E-Mail ID" /></b>&nbsp;<font color="red">*</font></div>
												<div class="tbox">
													<s:textfield name="tempEmailId" id="tempEmailId" cssClass="form-control" cssStyle="border color:black: 1px;" placeholder="E-Mail ID" maxlength="50" />
												</div>
											</div>
											<div class="col-xs-12 col-sm-6 col-md-4 col-lg-4">
												<div class="text"><b><s:text name="Mobile Number" /></b>&nbsp;<font color="red">*</font></div>
												<div class="tbox">
													<s:textfield name="tempMobileNo" id="tempMobileNo" cssClass="form-control" cssStyle="border color:black: 1px;" placeholder="Mobile Number" maxlength="10" />
												</div>
											</div>
											<div class="col-xs-12 col-sm-6 col-md-4 col-lg-4">
												<div class="text"><b><s:text name="NRC/Passport Number" /></b>&nbsp;<font color="red">*</font></div>
												<div class="tbox">
													<s:textfield name="tempPassportNo" id="tempPassportNo" cssClass="form-control" cssStyle="border color:black: 1px;" placeholder="NRC/Passport Number" maxlength="20" />
												</div>
											</div>
										</div>
										<br/>
										<div class="row">
											<div class="panel panel-primary" style="width: 100%;">
												<div class="panel-heading">
													<h3><s:text name="Payment Detail" /></h3><hr>
												</div>
												<div class="panel-body">
		<!-- 											<table class="display responsive no-wrap" id="paymentDetailsList" width="100%" cellspacing="0"> -->
													<table class="table table-bordered table-hover" cellspacing="0" width="100%" id="paymentDetailsList" style="border-radius: 20px;">
														<thead class="bluecolortable">
													        <tr style="padding: 10px">
																<th style="width:10%;">RISK ID</th>
																<th style="width:11%;">Transaction ID</th>
																<th style="width:11%;">Paid Amount</th>
																<th style="width:11%;">Transaction Type</th>
																<th style="width:10%;">Currency Type</th>
																<th style="width:15%;">Reuqest Bill to Forename</th>
																<th style="width:15%;">Request Time</th>
																<th style="width:15%;">Response Time</th>
																<th style="width:15%;">Message</th>
															</tr>
														</thead>
														<tbody class="rowevencolor">
															<s:iterator value="paymentList" var="list" status="stat">											
																<tr  align="center">
															<td><s:property value="#list.RISK_ID" /></td>
																	<td><s:property value="#list.RES_TRANSACTION_ID" /></td>
																	<td><s:property value="#list.RES_AUTH_AMOUNT" /></td>
																	<td><s:property value="#list.RES_REQ_TRANSACTION_TYPE" /></td>
																	<td><s:property value="#list.RES_REQ_CURRENCY" /></td>
																	<td><s:property value="#list.RES_REQ_BILL_TO_FORENAME" /></td>
																	<td><s:property value="#list.REQUEST_TIME" /></td>
																	<td><s:property value="#list.RESPONSE_TIME" /></td>
																	<td><s:property value="#list.RES_MESSAGE" /></td>
																</tr>
															</s:iterator>
														</tbody>
													</table>
													<br/>
												</div>
											</div>
										</div>
										<s:if test="paidList.size()>0">
											<div class="row">
												<div class="panel panel-primary" style="width: 100%;">
													<div class="panel-heading">
														<h3><s:text name="Vehicle Paid Details" /></h3><hr>
													</div>
													<div class="panel-body">
		<!-- 										    	<table class="display responsive no-wrap" id="paidVehicleDetailsTable" width="100%" cellspacing="0"> -->
												    	<table class="table table-bordered table-hover" cellspacing="0" width="100%" id="paidVehicleDetailsTable" style="border-radius: 20px;">
															<thead class="bluecolortable">
														        <tr style="padding: 10px">
																	<th style="width:5%;"><label>RISK ID</label></th>
																	<th style="width:20%;">Policy No</th>
																	<th style="width:11%;">Chassis No</th>
																	<th style="width:11%;">Registration No</th>
																	<th style="width:11%;">Engine No</th>
																	<th style="width:10%;">Sum Insured</th>
																	<th style="width:15%;">Premium Renewal</th>
																	<th style="width:15%;">Date of Payement</th>
																	<th style="width:15%;">Transaction ID</th>
																</tr>
															</thead>
															<tbody class="rowevencolor">
																<s:iterator value="paidList" var="list" status="stat">											
																	<tr  align="center">
																		<td><s:property value="#list.RISK_ID" /></td>
																		<td><s:property value="#list.POLICY_NO" /></td>
																		<td><s:property value="#list.CHASSIS_NO" /></td>
																		<td><s:property value="#list.REG_NO" /></td>
																		<td><s:property value="#list.ENGINE_NO" /></td>
																		<td><s:property value="#list.CUST_SI" /></td>
																		<td><s:property value="#list.CUST_PREM" /></td>
																		<td><s:property value="#list.REQUEST_TIME" /></td>
																		<td><s:property value="#list.RES_TRANSACTION_ID" /></td>
																	</tr>
																</s:iterator>
															</tbody>
														</table>
														<br/>
													</div>
												</div>
											</div>
										</s:if>
										<div class="row">
											<div class="panel panel-primary" style="width: 100%;">
												<div class="panel-heading">
													<h3><s:text name="Vehicle Renewal Details" /></h3><hr>
												</div>
												<div class="panel-body">
													<table class="table table-bordered table-hover" id="vehicleDetailsTable" width="100%" cellspacing="0" style="border-radius: 20px;">
		<!-- 											<table class="table table-bordered table-hover" cellspacing="0" width="100%" id="vehicleDetailsTable"> -->
														<thead class="bluecolortable">
													        <tr style="padding: 10px">
													        <th style="width:10%;">Select Vehicle</th>
																<th style="width:8%;"><label>RISK ID</label></th>
																
																<th style="width:15%;">Policy No</th>
																<th style="width:10%;">Sum Insured</th>
																<th style="width:15%;">Premium Renewal</th>
																<th style="width:15%;">Chassis No</th>
																<th style="width:11%;">Registration No</th>
																<th style="width:11%;">Engine No</th>
																
																
															</tr>
														</thead>
														<tbody class="rowevencolor">
															<s:if test="renewalList.size()>0">			
																<s:iterator value="renewalList" var="list" status="stat">	
																		<tr  align="center">
																		<td style="text-align: center;">
																	 			<s:checkbox label="checkbox test" name="vehicleSelect[%{#stat.count-1}]" id="totalPrem_%{#stat.count}" fieldValue="true"  onclick="getTotalPremium('%{#stat.count}', this.id)" ></s:checkbox>
																				<input type="hidden" name="totalPrem_<s:property value="#stat.count"/>_prem" id="totalPrem_<s:property value="#stat.count"/>_prem" value='<s:property value="#list.CUST_PREM" />'/>
																				<input type="hidden" name="totalPrem_<s:property value="#stat.count"/>_tax" id="totalPrem_<s:property value="#stat.count"/>_tax" value='<s:property value="#list.PREM_TAX" />'/>
																			</td>
																			<td><s:property value="#list.RISK_ID" /></td>
																			
																			<td><s:property value="#list.POL_NO" /></td>
																			<td><s:property value="#list.CUST_SI" /></td>
																			<td><s:property value="#list.CUST_PREM" /></td>
																			<td><s:property value="#list.CHASSIS_NO" /></td>
																			<td><s:property value="#list.REG_NO" /></td>
																			<td><s:property value="#list.ENGINE_NO" /></td>
																		</tr>
																</s:iterator>
															</s:if>
															<s:else>
																<tr><td align="center" colspan="8"> No Vehicle Available to Renew</td></tr>
															</s:else>
														</tbody>
													</table>
												</div>
												<s:if test="renewalList.size()>0">	
													<!--<div align="right">
														<b>Total Premium Amount : </b>&nbsp;&nbsp;<b id="displayTot"><s:property value="myTotal"/></b>&nbsp;&nbsp;
													</div>-->
													<div class="row">
														<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" align="center">
														     <div class="col-xs-12 col-sm-12 col-md-4 col-lg-4" align="center">
														     	<b>Premium : </b>&nbsp;&nbsp;<b id="displayTot"><s:property value="myTotal"/></b>&nbsp;&nbsp;
														     </div>
														     <div class="col-xs-12 col-sm-12 col-md-4 col-lg-4" align="center">
														     	<b>Premium Tax : </b>&nbsp;&nbsp;<b id="displayTax"><s:property value="premTax"/></b>&nbsp;&nbsp;
														     </div>
														     <div class="col-xs-12 col-sm-12 col-md-4 col-lg-4" align="center">
														     	<b>Total Premium : </b>&nbsp;&nbsp;<b id="displayPremPay"><s:property value="premPay"/></b>&nbsp;&nbsp;
														     </div>
														</div>
													</div>
												</s:if>
												<br/>
												<div class="row">
							
													<s:if test='"N".equalsIgnoreCase(pMode)'>
														<div class="col-xs-12 col-sm-12 col-md-12" align="center">
															<input type="button" class="btn btn-sm btn-danger" readOnly="true" value="Back" onclick="funBack('portfolioQuickRenewal.action');" /> &nbsp;&nbsp;&nbsp;
															<input type="button" class="btn btn-sm btn-success" readOnly="true" value="Submit" onclick="SubmitNew();"> &nbsp;&nbsp;&nbsp;
														</div>
													</s:if>
													<s:else>
														<div class="col-xs-12 col-sm-12 col-md-12" align="center">
															<input type="button" class="btn btn-sm btn-danger" readOnly="true" value="Back" onclick="funBack('pageQuickRenewal.action');" /> &nbsp;&nbsp;&nbsp;
															<input type="button" class="btn btn-sm btn-success" readOnly="true" value="Submit" onclick="SubmitNew();"> &nbsp;&nbsp;&nbsp;
														</div>
														<br/>
														<h1></h1>
													</s:else>
												</div>
												<s:hidden name="totPrem" id="totPrem"/>
											</div>
										</div>
									</div>
									</s:if>
									<s:else> 
										<div class="container-fluid">
											<div class="row">
												<div class="panel panel-primary">
													<div class="panel-heading">
														<h3><s:text name="Renewal Policy Details" /></h3><hr>
													</div>
													<div class="panel-body">
		<!-- 												<table class="display responsive no-wrap" id="renewalDetailsTable" width="100%" cellspacing="0"> -->
														<table class="table table-bordered table-hover" cellspacing="0" width="100%" id="renewalDetailsTable" style="border-radius: 20px;">
															<thead class="bluecolortable">
														        <tr style="padding: 10px">
																	<th style="width:20%;">Policy No</th>
																	<th style="width:11%;">Insured Name</th>
																	<th style="width:11%;">Policy Start Date</th>
																	<th style="width:11%;">Policy Expiry Date</th>
																	<th style="width:20%;">Renewal</th>
																</tr>
															</thead>
															<tbody class="rowevencolor">
																<s:if test="policyList.size()>0">
																	<s:iterator value="policyList" var="list" status="stat">											
																		<tr  align="center">
																			<td><s:property value="#list.POLICY_NO" /></td>
																			<td><s:property value="#list.ASSR_NAME" /></td>
																			<td><s:property value="#list.PREV_FROM_DT" /></td>
																			<td><s:property value="#list.PREV_TO_DT" /></td>
																			<td style="text-align: center;">
																	 		<button type="button" class="btn btn-success btn-md" name="policyButton" value="Renewal"  id="policyButton"   onclick="viewVehicle('<s:property value="#list.POLICY_NO"/>','back');" >Renewal</button>
																			</td>
																		</tr>
																	</s:iterator>
																</s:if>
																<s:else>
																	<tr><td align="center" colspan="5"> No data available in table </td></tr>
																</s:else>
															</tbody>
														</table>
														<br/>
													</div>
												</div>
											</div>
										</div>
									</s:else>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<s:hidden name="cStatus"></s:hidden>
			<s:hidden name="myTotal" id="myTotal"/>
			<s:hidden name="policyNo" id="policyNo"/>
			<s:hidden name="vehicleList" id="vehicleList"/>			
			<s:hidden name="mobileNo" id="mobileNo"/>
			<s:hidden name="mode" id="mode"/>
			<s:hidden name="pMode" id="pMode"/>
			<s:hidden name="renewRefNo" id="renewRefNo"/>
			<s:hidden name="qrMode" id="qrMode"/>
			<s:hidden name="premTax" id="premTax"/>
			<s:hidden name="premPay" id="premPay"/>
		</s:form>
		<script type="text/javascript" src="<%=request.getContextPath()%>/bootstrap/js/jquery-1.9.1.min.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/bootstrap/js/bootstrap.min.js"></script>
		<script type="text/javascript">
					
				function funBack(action){
					//action='pageQuickRenewal.action';
					document.quickRenewal.action=action;
					document.quickRenewal.submit();
				}
					
				function getTotalPremium(typeId,id){
					var element1 = document.createElement("input");
					element1.type = "hidden";
					element1.name = "total"+typeId;
					element1.id = "total"+typeId;
					document.getElementById("quickRenewal").appendChild(element1);
					
					var element2 = document.createElement("input");
					element2.type = "hidden";
					element2.name = "tax"+typeId;
					element2.id = "tax"+typeId;
					document.getElementById("quickRenewal").appendChild(element2);
				
					var sp=document.getElementById(id+"_prem").value;
					var tax=document.getElementById(id+"_tax").value;
					
					var val = document.getElementById("total"+typeId).value;
					var taxval = document.getElementById("tax"+typeId).value;
					
					if($("#"+id).prop("checked")){
						var tot = ((val==""?0:parseFloat(val)) + parseFloat(sp)).toFixed(2);
						document.getElementById("total"+typeId).value = roundNumber(tot,2);
						document.getElementById("myTotal").value=(parseFloat(document.getElementById("myTotal").value==""?0:document.getElementById("myTotal").value)+parseFloat(document.getElementById("total"+typeId).value)).toFixed(2)
						document.getElementById("displayTot").innerHTML=parseFloat(document.getElementById("myTotal").value).toFixed(2);
						
						var taxtot = ((taxval==""?0:parseFloat(taxval)) + parseFloat(tax)).toFixed(2);
						document.getElementById("tax"+typeId).value = roundNumber(taxtot,2);
						document.getElementById("premTax").value=(parseFloat(document.getElementById("premTax").value==""?0:document.getElementById("premTax").value)+parseFloat(document.getElementById("tax"+typeId).value)).toFixed(2)
						document.getElementById("displayTax").innerHTML=parseFloat(document.getElementById("premTax").value).toFixed(2);
					}
					else if(!$("#"+id).prop("checked")){
						var tot = parseFloat(document.getElementById("myTotal").value - parseFloat(sp));	
						document.getElementById("total"+typeId).value = 0;
						document.getElementById("myTotal").value=parseFloat(roundNumber(tot,2)).toFixed(2);
						document.getElementById("displayTot").innerHTML=document.getElementById("myTotal").value;
						
						var taxtot = parseFloat(document.getElementById("premTax").value - parseFloat(tax));	
						document.getElementById("tax"+typeId).value = 0;
						document.getElementById("premTax").value=parseFloat(roundNumber(taxtot,2)).toFixed(2);
						document.getElementById("displayTax").innerHTML=document.getElementById("premTax").value;
					}
					var premAmt=document.getElementById("myTotal").value;
					var taxAmt=document.getElementById("premTax").value;
					var totPay=((premAmt==""?0:parseFloat(premAmt)) +(taxAmt==""?0:parseFloat(taxAmt))).toFixed(2);
					document.getElementById("premPay").value=totPay;
					document.getElementById("displayPremPay").innerHTML=totPay;
					
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

				function SubmitNew() {
			        var grid = document.getElementById("vehicleDetailsTable");
			        var checkBoxes = grid.getElementsByTagName("INPUT");
			        var vehregno = "";
			     //   var policyNo= "";
			        for (var i = 0; i < checkBoxes.length; i++) {
			            if (checkBoxes[i].checked) {
			                var row = checkBoxes[i].parentNode.parentNode;
			                vehregno +=row.cells[1].innerHTML +",";
			               // policyNo +=row.cells[1].innerHTML +"~";
			            }
			        }
			        var action='paymentQuickRenewal.action';
			        document.quickRenewal.vehicleList.value=vehregno;
			       // document.quickRenewal.myTotal.value=myTotal;
					document.quickRenewal.action=action;
					document.quickRenewal.submit();
			 	}
			 	
			 	function viewVehicle(polNum,mode){
			 		document.quickRenewal.policyNo.value=polNum;
			 		document.quickRenewal.mode.value=mode;
			 		document.quickRenewal.pMode.value='N';
			 		document.quickRenewal.action = 'vehDetailQuickRenewal.action';
					document.quickRenewal.submit();
			 	}
			 
			 	
		</script>
	</body>
</html>