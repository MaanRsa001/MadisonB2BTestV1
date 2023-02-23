<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/struts-tags" prefix="s"%>
<html>
<head>
<style type="text/css">
	#myDIV {
	    overflow-x:scroll;
	    margin: auto:
	}
           }
</style>
	<SCRIPT language=Javascript>
		function isNumberKey(evt) {
			var charCode = (evt.which) ? evt.which : event.keyCode;
			if (charCode != 46 && charCode > 31 
			&& (charCode < 48 || charCode > 57))
			return false;
			return true;
		}
	</SCRIPT>
	
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/dataTables/css/dataTables.responsive.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/dataTables/css/dataTables.jqueryui.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/dataTables/css/dataTables.tableTools.css">
	<script type="text/javascript" src="${pageContext.request.contextPath}/dataTables/js/dataTables.responsive.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/dataTables/js/dataTables.tableTools.js"></script>
	
	
<%-- 	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/dataTables/css/dataTables.responsive.css"> --%>
<%-- 	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/dataTables/css/dataTables.jqueryui.css"> --%>
<%-- 	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/dataTables/css/dataTables.tableTools.css"> --%>
<%-- 	<link href="<%=request.getContextPath()%>/dataTables/css/jquery-start-ui.css" rel="stylesheet" type="text/css" /> --%>
<%-- 	<script type="text/javascript" src="<%=request.getContextPath()%>/dataTables/js/jquery-1.11.1.min.js"></script> --%>
<%-- 	<script type="text/javascript" src="<%=request.getContextPath()%>/dataTables/js/jquery.dataTables.min.js"></script> --%>
<%-- 	<script type="text/javascript" src="<%=request.getContextPath()%>/dataTables/js/dataTables.jqueryui.js"></script> --%>
<%-- 	<script type="text/javascript" src="<%=request.getContextPath()%>/dataTables/js/dataTables.responsive.js"></script> --%>
<%-- 	<script type="text/javascript" src="<%=request.getContextPath()%>/dataTables/js/dataTables.tableTools.js"></script> --%>
<script type="text/javascript" src="js/common.js"></script>		
	<script type="text/javascript">
	

	$(document).ready(function () {
		  $('#tadaTable').DataTable({
	    	  "responsive": true,
	        "columnDefs": {
	          "orderable": false
	        },
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
	      })
	    });
		
	 </script>	
	 <style type="text/css">
	 .tableColWidth {
	 	min-width: 100px;
	 	max-width: 500px;
	 	width: 100px;
	 	white-space: nowrap;
	 }
	 </style>	
</head>
<body>
<s:form name="form1" theme="simple">
<div class="container wrapper vehDtl">
<div class="Card_Parent ">
<div class="card card-5">
	<div class="row">
		<div class="col-md-12">
			<div class="panel panel-primary">
				<div class="VehicleTableheading">
					<div class="panel-heading">
					<h3>
						<s:if test="reqForm == 'ccPending' ">
							<s:text name="label.creditCotrol"/>
						</s:if>
						<s:elseif test="reqForm == 'ccYStatus' ">
							<s:text name="label.creditCotrol.approved.status"/>
						</s:elseif>
						<s:elseif test="reqForm == 'ccNStatus' ">
							<s:text name="label.creditCotrol.rejected.status"/>
						</s:elseif>
						<s:elseif test="reqForm == 'ssPending'">
							<s:text name="label.surveyor"/>
						</s:elseif>
						<s:elseif test="reqForm == 'ssYStatus'">
							<s:text name="label.surveyor.approved.status"/>
						</s:elseif>
						<s:elseif test="reqForm == 'ssNStatus'">
							<s:text name="label.surveyor.rejected.status"/>
						</s:elseif>
						<s:elseif test="reqForm == 'uwPending'">
							<s:text name="label.underwriter"/>
						</s:elseif>	
						<s:elseif test="reqForm == 'uwYStatus'">
							<s:text name="label.underwriter.approved.status"/>
						</s:elseif>
						<s:elseif test="reqForm == 'uwNStatus'">
							<s:text name="label.underwriter.rejected.status"/>
						</s:elseif>	
						</h3>
					</div>		
				</div>	
				<div class="panel-body">
					<div class="row">
						<font color="green"><s:actionmessage cssStyle="list-style: none;"/></font>
					</div>
					<div class="panel-body p-5">
						<div class="row">
							<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 ">
								<s:if test="mode != 'viewPayment'">
									<table class="table table-bordered table-hover" cellspacing="0" width="100%" id="tadaTable">
										<thead class="bluecolortable">
											<tr>
												<th class="no-sort"><s:text name="label.master.sno"/></th>
												<th><s:text name="label.quoteno"/></th>
												<th><s:text name="label.customername"/></th>
												<th><s:text name="label.mobile"/></th>
												<!--<th><s:text name="label.product.name"/></th>
												-->
												<s:if test='productId=="65"'>
													<th><s:text name="label.policytype"/></th>
												</s:if>
												<th><s:text name="label.start.date"/></th>
												<th><s:text name="label.end.date"/></th>
												<th><s:text name="label.premium"/></th>
												<s:if test='reqForm != "ccPending" && reqForm != "ssPending" && reqForm != "uwPending"'>
													<th><s:text name="label.remarks"/></th>
												</s:if>
												<s:if test="reqForm == 'ccYStatus'">
													<th><s:text name="Surveyor" /></th>
												</s:if>
												<s:elseif test='reqForm == "ssYStatus" || reqForm == "ssNStatus"'>
													<th><s:text name="Under Writer" /></th>
												</s:elseif>
												<th><s:text name=""/></th>
											</tr>
										</thead>
										<tbody class="rowevencolor">
											<s:iterator value="transList" var="list" status="stat">
												<tr>
													<td><s:property value="#stat.count"/></td>
													<td><s:property value="#list.QUOTE_NO"/></td>
													<td><s:property value="#list.CUSTOMER_NAME" /></td>
													<td><s:property value="#list.MOBILE" /></td>
													<s:if test='productId=="65"'>
														<td><s:property value="#list.POLICYTYPE" /></td>
													</s:if>
													<td><s:property value="#list.START_DATE" /></td>
													<td><s:property value="#list.END_DATE" /></td>
													<td align="right"><s:property value="#list.OVERALL_PREMIUM" /></td>
												 	<s:if test='reqForm != "ccPending" && reqForm != "ssPending" && reqForm != "uwPending"'>
														<td><s:property value="#list.REMARKS" /></td>
													</s:if>
													<s:if test='reqForm == "ccYStatus" || reqForm == "ssYStatus" || reqForm == "ssNStatus"'>
														<td><s:property value="#list.APPLICAPLE_USER" /></td>
													</s:if>
													<td style="text-align: center;">
														<input type="button" class="btn btn-warning btn-sm" onclick="fnView('<s:property value="#list.QUOTE_NO"/>','<s:property value="#list.POLICY_NO"/>');" value="View" />
													</td>
											 </tr>
											</s:iterator>
										</tbody>
									</table>
								</s:if>
							<s:elseif test="mode == 'viewPayment'">
								<s:if test="hasActionErrors()">
									<div class="row">
										<font color="red"><s:actionerror cssStyle="list-style: none;"/></font>
									</div>
									<br class="clear" />
								</s:if>
								<div class="panel panel-primary">
									<div class="panel-heading">
										<h4><s:text name="label.customer"/></h4>
										<div class="pullRight">
											<label><b><s:text name="label.quoteno" /></b>&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;</label><s:property value="quoteNo" />
										</div>
									</div>
									<div class="panel-body">
										<s:iterator value="customerInfo" var="cust">
											<div class="row">
												<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
													<b><s:label key="label.customername" /></b>&nbsp;&nbsp;:&nbsp;&nbsp;<s:property value="#cust.TITLE"/>.<s:property value="#cust.FIRST_NAME"/>&nbsp;<s:property value="#cust.LAST_NAME"/>										
												</div>
												<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
													<b><s:label key="user.mobile" /></b>&nbsp;&nbsp;:&nbsp;&nbsp;<s:property value="#cust.MOBILE"/>										
												</div>
												<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
													<b><s:label key="user.email" /></b>&nbsp;&nbsp;:&nbsp;&nbsp;<s:property value="#cust.EMAIL"/>
												</div>
												<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
													<b><s:label key="user.address1" /></b>&nbsp;&nbsp;:&nbsp;&nbsp;<s:property value="#cust.ADDRESS1"/>										
												</div>
												<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
													<b><s:label key="user.address2" /></b>&nbsp;&nbsp;:&nbsp;&nbsp;<s:property value="#cust.ADDRESS2"/>
												</div>
												
												<%--<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
													<s:label key="user.gender" />&nbsp;&nbsp;:&nbsp;&nbsp;<s:property value="#cust.GENDER"/>
												</div>
												<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
													<s:label key="label.dob" />&nbsp;&nbsp;:&nbsp;&nbsp;<s:property value="#cust.DOB"/>										
												</div>--%>
												
												<%-- <div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
													<s:label key="user.telephone" />&nbsp;&nbsp;:&nbsp;&nbsp;<s:property value="#cust.TELEPHONE"/>
												</div>--%>
												
												
												<%-- 
												<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
													<s:label key="user.pobox" />&nbsp;&nbsp;:&nbsp;&nbsp;<s:property value="#cust.POBOX"/>					
												</div>
												--%>
												<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
													<s:label key="user.city" />&nbsp;&nbsp;:&nbsp;&nbsp;<s:property value="#cust.EMIRATE"/>
												</div>
												<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
							  						<s:label key="customer.passportNo" />&nbsp;&nbsp;:&nbsp;&nbsp;<s:property value="#cust.PASSPORT_NUMBER"/>
							  					</div>
												<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
							  						<s:label key="customer.nrc" />&nbsp;&nbsp;:&nbsp;&nbsp;<s:property value="#cust.NRC"/>
							  					</div>
												<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
													<s:label key="user.occupation" />&nbsp;&nbsp;:&nbsp;&nbsp;<s:property value="#cust.OCCUPATION"/>
												</div>
											</div>
										</s:iterator>
									</div>
								</div>	
									<div class="row">
									     <div class="col-xs-12">
											<%--<div class="panel panel-primary">
												<div class="panel-heading">
													<s:text name="label.customer"/>
													<div class="pullRight">
																<label><s:text name="label.quoteno" />&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;</label><s:property value="quoteNo" />
													</div>
												</div>
												<div class="panel-body">
									        		<div class="row">
									        				<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
																<div class="">
							    									<label><s:text name="label.customername"/>&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;</label><s:property value="customerName" />
																</div>
															</div>
															<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
																<div class="">
							    									<label><s:text name="label.add1"/>&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;</label><s:property value="add1" />
																</div>
															</div>
															<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
																<div class="">
							    									<label><s:text name="label.pobox"/>&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;</label><s:property value="poBox" />
																</div>
															</div>
															<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
																<div class="">
							    									<label><s:text name="label.mobile"/>&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;</label><s:property value="mobileNo" />
																</div>
															</div>
															<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
																<div class="">
							    									<label><s:text name="label.mail"/>&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;</label><s:property value="email" />
																</div>
															</div>
									        			</div>
									        		</div>
									        	</div>
									        	<div class="panel panel-primary">
									        		</div>--%>
									        		<div class="panel panel-primary">
									        		<div class="panel-heading">
														<h4><s:text name="label.premium.detail"/></h4>
													</div>
													<div class="panel-body">
									        		<div class="row">
									        				<s:if test='policyNo!=null && policyNo!=""'>
										        				<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
																	<div class="">
								    									<label><s:text name="label.policyno"/>&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;</label><s:property value="policyNo" />
																	</div>
																</div>
															</s:if>
									        				<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
																<div class="">
							    									<label><s:text name="label.startDate"/>&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;</label><s:property value="fromDate" />
																</div>
															</div>
															<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
																<div class="">
							    									<label><s:text name="label.endDate"/>&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;</label><s:property value="toDate" />
																</div>
															</div>
															<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
																<div class="">
							    									<label><s:text name="label.premium"/>&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;</label><s:property value="premium" />
																</div>
															</div>
															<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
																<div class="">
							    									<label><s:text name="label.vat"/>&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;</label><s:property value="vat" />
																</div>
															</div>
															<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
																<div class="">
							    									<label><s:text name="label.total.premium"/>&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;</label><s:property value="totPremium" />
																</div>
															</div>
															<s:if test='currencyType!=null && currencyType!=""'>
										        				<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
																	<div class="">
								    									<label><s:text name="label.currency.type"/>&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;</label><s:property value="currencyType" />
																	</div>
																</div>
															</s:if>
									        				</div>
									        			</div>
									        		</div>
									        		<br>
									        		<s:if test='noInstallmentList!=null && noInstallmentList.size()>0'>
										       		<div class="panel panel-primary">
									        			<div class="panel-heading">
															<h4><s:text name="label.installment.detail"/></h4>
														</div>
														<div class="panel-body">
									        			<div class="row">
									        					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" style="overflow-x: scroll; width:1180px;">
									        					<table class="footable" >
																		<thead>
																		<tr>
																			<th align="center"><s:text name="label.master.sno"/></th>
																			<th align="center"><s:text name="label.ins"/></th>
																			<th align="center"><s:text name="label.desc"/></th>
																			<th align="right"><s:text name="label.dueAmount"/></th>					
																			<th align="center"><s:text name="label.dueDate"/></th>		
																		</tr>
																		</thead>
																		<tbody>
																			<s:iterator value="noInstallmentList" var="list" status="stat">
																				<tr>
																					<td align="center"><s:property value="#stat.count"/></td>
																					<td align="center"><s:property value="#list.INSTALLMENT_NO"/></td>
																					<td align="center"><s:property value="#list.DESCRIPTION"/></td>
																					<td align="right"><s:property value="#list.PREMIUM_AMOUNT" /></td>
																					<td align="center"><s:property value="#list.PREMIUM_DATE"/></td>
																				</tr>
																			</s:iterator>
																		</tbody>
																	</table>
									        					</div>
									        				</div>
									        			</div>
									        		</div>
										       		</s:if>
										       		<br>
									        		<s:if test="reqForm != 'ccPending'">
									        		<div class="panel panel-primary">
										        		<div class="panel-heading">
										        			<h4>
																<s:if test='productId=="65"'>
																	<s:text name="label.vehicle.detail"/>
																</s:if>
																<s:elseif test='productId=="33"'>
																	<s:text name="traveller.header"/>
																</s:elseif>
																<s:elseif test='productId=="30"'>
																	<s:text name="label.home.info"/>
																</s:elseif>
															</h4>
														</div>
														<div class="panel-body">
											        		<div class="row">
																<s:if test='productId=="65"'>
																	<table class="footable" style="overflow-x: scroll;">
											        					<thead>
																			<tr>
																				<th class="no-sort"><s:text name="label.master.sno"/></th>
																				<th><s:text name="label.document"/></th>
																				<th><s:text name="label.make.name"/></th>
																				<th><s:text name="label.model.name"/></th>
																				<th><s:text name="label.body.type"/></th>
																				<th><s:text name="label.vehi.usage"/></th>
																				<th><s:text name="label.mfg.year"/></th>
																				<th><s:text name="label.seating.cap"/></th>
																				<th><s:text name="label.engine.no"/></th>
																				<th><s:text name="label.reg.no"/></th>
																				<th><s:text name="label.chasis.no"/></th>
																				<th><s:text name="label.ncb"/></th>
																				<th><s:text name="label.premium"/></th>
																			</tr>
																		</thead>
																		<tbody >
																			<s:iterator value="vehicleDetailList" var="list" status="stat">
																				<tr>
																					<td><s:property value="#stat.count"/></td>
																					<td align="center">
																						<s:if test="reqForm == 'ssPending' || reqForm == 'ssYStatus'  ">
																							<s:submit type="button" value="Attach Documents" onclick='return uploadDocuments("%{#list.VEHICLE_ID}");' cssClass="btn btn-sm btn-danger"/>
														       								<s:submit type="button" value="View Documents" onclick='return viewDocuments("%{#list.VEHICLE_ID}");' cssClass="btn btn-sm btn-success"/>
														       							</s:if>
														       							<s:else>
														       								<s:submit type="button" value="View Documents" onclick='return viewDocuments("%{#list.VEHICLE_ID}");' cssClass="btn btn-sm btn-success"/>
														       							</s:else>
																					</td>
																					<td><s:property value="#list.MAKE_NAME"/></td>
																					<td><s:property value="#list.MODEL_NAME"/></td>
																					<td><s:property value="#list.TYPE_OF_BODY_NAME"/></td>
																					<td><s:property value="#list.VEHICLETYPE_DESC"/></td>
																					<td><s:property value="#list.MANUFACTURE_YEAR"/></td>
																					<td><s:property value="#list.SEATING_CAPACITY"/></td>
																					<td><s:property value="#list.ENGINE_NUMBER"/></td>
																					<td><s:property value="#list.REGISTRATION_NO"/></td>
																					<td><s:property value="#list.CHASSIS_NO"/></td>
																					<td><s:property value="#list.NO_CLAIM_BONUS"/></td>
																					<td><s:property value="#list.PREMIUM" /></td>
																				</tr>
																			</s:iterator>
																		</tbody>
																	</table>
											        			</s:if>
											        				<s:elseif test='productId=="33"'>
											        					<table class="footable">
											        						<thead>
																				<tr>
																					<th width="3%"> <s:text name="travel.sNo"/> </th>
																					<th width="12.12%"> <s:text name="travel.first.name" /><font color="red">*</font> </th>
																					<th width="12.12%"> <s:text name="travel.last.name" /><font color="red">*</font> </th>
																					<th width="12.12%"> <s:text name="travel.dateOfBirth" /><font color="red">*</font> </th>
																					<th width="12.12%"> <s:text name="travel.gender" /><font color="red">*</font> </th>
																					<th width="12.12%"> <s:text name="travel.relations" /><font color="red">*</font> </th>
																					<th width="12.12%"> <s:text name="travel.nationality" /><font color="red">*</font> </th>
																					<th width="12.12%"><s:text name="travel.passport.number" /></th>
																					<th width="12.12%"><s:text name="travel.passport.expiry.date" /></th>
																				</tr>
																			</thead>
																			<tbody >
																				<s:iterator value="travellerDetailsList" var="list" status="stat">
																					<tr>
																						<td><s:property value="#stat.count"/></td>
																						<td><s:property value="#list.PASSENGER_NAME"/></td>
																						<td><s:property value="#list.PASSPANGER_LAST_NAME"/></td>
																						<td><s:property value="#list.DOB" /></td>
																						<td><s:property value="#list.RELATION"/></td>
																						<td><s:property value="#list.GENDER"/></td>
																						<td><s:property value="#list.NATIONALITY"/></td>
																						<td><s:property value="#list.PASSPORT_NO"/></td>
																						<td><s:property value="#list.PASSPORT_EXP_DATE"/></td>
																					</tr>
																				</s:iterator>
																			</tbody>
																		</table>
											        				</s:elseif>
											        				<s:elseif test='productId=="30"'>
																		<div style="float: right">
																			<s:if test="reqForm == 'ssPending'">
																				<s:submit type="button" value="Attach Documents" onclick='return uploadDocuments("%{#list.VEHICLE_ID}");' cssClass="btn btn-sm btn-danger"/>
											       								<s:submit type="button" value="View Documents" onclick='return viewDocuments("%{#list.VEHICLE_ID}");' cssClass="btn btn-sm btn-success"/>
											       							</s:if>
											       							<s:else>
											       								<s:submit type="button" value="View Documents" onclick='return viewDocuments("%{#list.VEHICLE_ID}");' cssClass="btn btn-sm btn-success"/>
											       							</s:else>
										       							</div>
																		<table class="footable">
																			<thead>
																				<tr>
																					<th>S.No</th>
																					<th><s:text name="label.coverage"/></th>
																					<th><s:text name="coverage.sumInsured"/>
																				</tr>
																			</thead>
																			<tbody>
																				<s:iterator value="homeInfo" var="Home" status ="itemIndex">
																					<s:if test='PREMIUM_AMOUNT!=null'>
																						<tr>
																							<td><s:property value="#itemIndex.Index"/> </td>
																							<td><s:property value="COVERAGES_DISPLAY_NAME" /></td>
																							<td align="right"><b><s:property value="COVERAGES_SUMINSURED" />&nbsp;<s:property value="#session.BrokerDetails.CurrencyAbb"/></b></td>
																						</tr>
																					</s:if>
																				</s:iterator>
																			</tbody>
																		</table>
											        				</s:elseif>
											        			</div>
										        			</div>
									        			</div>
									        		</s:if>
									        		<br>
									        		<s:if test='productId=="65"'>
									        		<s:if test="premiumInfo.size()>0">
									        		<div class="row">
															<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
																<div class="panel panel-primary">
												        			<div class="panel-heading txtB">
																		<h4><s:text name="label.quoteDetail" /></h4>
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
																							<s:hidden name="sI[%{#stat.count-1}]"  cssClass="form-control" value="%{#prInfo.SUM_INSURED}" cssStyle="text-align:right;"/>
																						</td>
																						<td align="left" width="25%">
																							<s:textfield name="baseRate[%{#stat.count-1}]" cssClass="form-control tooltipContent" data-content="Base Rate" value="%{#prInfo.RATE}" size="11" cssStyle="text-align:right; width:100%;" maxLength="16" onkeyup="checkDecimals(this);" readonly="true"/>
																						</td>
																						<td align="right" width="20%"> 
																							<s:property value="getText('{0,number,#,##0.00}',{#prInfo.PREMIUM})"/>
																							<s:set var="preAmt" value="%{@java.lang.Double@parseDouble(#attr.preAmt)+@java.lang.Double@parseDouble(#prInfo.PREMIUM)}" scope="page"/>
																						</td>
																					</tr>								
																					</tbody>
																					</s:iterator>
																					<tbody>
																					<tr>
																						<td></td>
																						<td></td>									
																						<td></td>
																						<td>
																							<font style="float:left;"><s:text name="label.premium"/></font>
																							<b style="float:right;">[<s:property value="currencyType"/>]</b>
																						</td>
																						<td width="100%" align="right">
																							<s:textfield name="premium" id="premium" cssClass="form-control tooltipContent" data-content="Premium" value="%{getText('{0,number,0.00}',{#attr.preAmt})}" onkeyup="checkDecimals(this);" maxlength="11" cssStyle="text-align:right;" readonly="true"/>
																						</td>
																					</tr>
																					<tr>
																						<td></td>
																						<td></td>									
																						<td></td>
																						<td>
																							<font style="float:left;"><s:text name="label.vat"/></font>
																							<b style="float:right;">[<s:property value="currencyType"/>]</b>
																						</td>
																						<td width="100%" align="right">
																							<s:textfield name="policyFee" id="policyFee" cssClass="form-control tooltipContent" data-content="Policy Fee" onkeyup="checkDecimals(this);" maxlength="11" cssStyle="text-align:right;" readonly="true"/>
																						</td>
																					</tr>
																					<tr>
																						<td></td>
																						<td></td>									
																						<td></td>
																						<td>
																							<font style="float:left;"><s:text name="label.total.premium" /></font>
																							<b style="float:right;">[<s:property value="currencyType"/>]</b>
																						</td>
																							
																						<td width="100%" align="right">
																							<%-- 
																							<s:textfield name="totalPremium" id="totalPremium" cssClass="form-control" value="%{@java.lang.Double@parseDouble(#attr.preAmt)+@java.lang.Double@parseDouble(policyFee)}" onkeyup="checkDecimals(this);" maxlength="11" cssStyle="text-align:right;" readonly="true"/>
																							--%>
																							<s:set var="totPremium" value="%{getText('{0,number,0.00}',{@java.lang.Double@parseDouble(#attr.preAmt)+@java.lang.Double@parseDouble(policyFee)})}"/>
																							<s:textfield name="totalPremium" id="totalPremium" cssClass="form-control tooltipContent" data-content="Total Premium" value="%{#totPremium}" onkeyup="checkDecimals(this);" maxlength="11" cssStyle="text-align:right;" readonly="true"/>
																						</td>
																					</tr>
																					</tbody>	
																				</table>
													       						<br class="clear"/>
													       					</div>
													       				</div>
													       			</div>
													       		</div>
															</div>
														</div>
														</s:if>
													</s:if>
													<br>
									        		<s:if test='reqForm == "ccPending"  || reqForm == "ccYStatus"  || reqForm == "ccNStatus"'>
									        		<s:if test='modeOfPayment == "1" || modeOfPayment == "2"'>
									        		<div class="row" id="modeOfPay">
														<s:include value="/pages/payment/paymentInfo.jsp"/>
													</div>
													</s:if>
													</s:if>
													<s:if test='modeOfPayment == "7"'>
													<div class="panel panel-primary">
														<div class="panel-heading">
														<h4><s:text name="label.payment"/></h4>
															<div class="pullRight">
																	<label><s:text name="payment.modeOfPayment" />&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;</label><s:property value="paymentType" />
																</div>
															</div>
														<div class="panel-body">
															<div class="row">
																<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
										  							<label><s:text name="label.reqtime"/>&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;</label><s:property value="reqTime" />
																</div>
																<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
										  							<label><s:text name="label.restime"/>&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;</label><s:property value="resTime" />
																</div>
																<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
										  							<label><s:text name="label.res.tran"/>&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;</label><s:property value="resTranNo" />
																</div>
																<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
										  							<label><s:text name="label.req.tran"/>&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;</label><s:property value="reqTranNo" />
																</div>
																<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
										  							<label><s:text name="label.res.deci"/>&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;</label><s:property value="resDecision" />
																</div>
																<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
										  							<label><s:text name="label.res.status"/>&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;</label><s:property value="resStatus" />
																</div>
																<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
										  							<label><s:text name="label.res.msge"/>&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;</label><s:property value="resMsge" />
																</div>
																<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
										  							<label><s:text name="label.res.code"/>&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;</label><s:property value="resCode" />
																</div>
																<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
										  							<label><s:text name="label.masked.card"/>&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;</label><s:property value="maskedCard" />
																</div>
									        				</div>
									        			</div>
									        		</div>
									        		</s:if>
													<s:if test='tracList!=null && tracList.size()>0'>
										       			<div class="panel panel-primary">
											        		<div class="panel-heading">
																<h4><s:text name="Payment Tracking Details"/></h4>
															</div>
															<div class="panel-body">
																<div class="row">
																	<table class="footable">
										        						<thead>
																			<tr>
																				<th width="3%"> <s:text name="payment.sNo"/> </th>
																				<th width="10%"> <s:text name="payment.user.name" /></th>
																				<th width="10%"> <s:text name="payment.user.type" /></th>
																				<th width="10%"> <s:text name="payment.user.status" /></th>
																				<th width="10%"> <s:text name="payment.user.approve.date" /></th>
																				<th width="10%"> <s:text name="payment.user.remarks" /></th>
																				<th width="10%"> <s:text name="payment.allocated.user" /></th>
																			</tr>
																		</thead>
																		<tbody >
																			<s:iterator value="tracList" var="list" status="stat">
																				<tr>
																					<td><s:property value="#stat.count"/></td>
																					<td><s:property value="#list.USERNAME"/></td>
																					<td><s:property value="#list.ITEM_VALUE"/></td>
																					<td><s:property value="#list.STATUS" /></td>
																					<td><s:property value="#list.RESPONSE_TIME"/></td>
																					<td><s:property value="#list.REMARKS"/></td>
																					<td><s:property value="#list.ALLOCATED_PERSON"/></td>
																				</tr>
																			</s:iterator>
																		</tbody>
																	</table>
																</div>
															</div>
										       			</div>
										       		</s:if>
										       		<br>
										       		<s:if test='check !=  "notshow"'>
													<s:if test="reqForm == 'ccPending' || reqForm == 'ssPending' || reqForm == 'uwPending' ">
													<div class="panel panel-primary">
										        		<div class="panel-heading">
															<h4><s:text name="label.Confirmation.detail"/></h4>
														</div>
														<div class="panel-body">
															<div class="row">
																<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
																	<label><s:text name="label.admin.remarks" /></label>
																	<s:textarea name="remarks" id="remarks" size="3" cssClass="form-control" maxlength="150"/>
																</div>
																<s:if test="reqForm == 'ssPending' || reqForm == 'ccPending'">
																	<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
																		<s:if test="reqForm == 'ccPending'">
																			<label><s:text name="Surveyour" /></label>
																		</s:if>
																		<s:elseif test="reqForm == 'ssPending'">
																			<label><s:text name="Under Writer" /></label>
																		</s:elseif>
																		<s:select name="applicapleLoginId" list="approverLoginList" listKey="LOGIN_ID" listValue="USERNAME" id="applicapleLoginId"  cssClass="form-controlS" />
																	</div>
																</s:if>
																<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4"><br/>
																	<label><s:text name="label.admin.status" /><font color="red">*&nbsp;:</font></label>
																	<s:if test="reqForm == 'ssPending' || reqForm == 'uwPending'">
																		<s:radio list="#{'Y':'Accepted','H':'Hold','N':'Rejected'}" name="status" id="status" />																		
																	</s:if>
																	<s:else>
																		<s:radio list="#{'Y':'Accepted','N':'Rejected'}" name="status" id="status" />
																	</s:else>
																</div>
															</div>
					       									<div class="row">
																<br class="clear" />
																<br class="clear" />
																<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" align="center">
																	<input type="button" onclick="fnBack();" class="btn btn-sm btn-danger" value="Back" />
																	<input type="button" onclick="fnSubmit();" class="btn btn-sm btn-success" value="Submit" /> &nbsp;&nbsp;&nbsp;
																</div>
															</div>
														</div>
									       			</div>
									       			</s:if>
									       			</s:if>
									       			<s:else>
									       				<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" align="center">
															<input type="button" onclick="fnBack();" class="btn btn-sm btn-danger" value="Back" />
														</div>
									       			</s:else>
												</div>
											</div>
										<s:hidden name="totPremium"/>
										<s:hidden name="policyNo"/>
										<s:hidden name="quoteNo"/>
										<s:hidden name="customerName"/>
										<s:hidden name="customerId"/>
										<s:hidden name="paymentType" />
										<s:hidden id="mode" name="mode"/>
										<s:hidden id="mode" name="mode"/>
										<s:hidden name="merchant_reference" />
				      		 	</s:elseif>
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
<s:hidden name="reqForm"/>
</s:form>
</body>
<script type="text/Javascript" >
$('#modeOfPay input:text').attr('disabled', 'disabled');
$('#modeOfPay select').attr('disabled', 'disabled');
function fnView(quoteNo,policyNo){
	document.form1.action='viewPaymentProcess.action?quoteNo='+quoteNo+'&policyNo='+policyNo;
	document.form1.submit();
}
function fnSubmit(val){
	document.form1.action='insertCCPaymentProcess.action';
	document.form1.submit();
}
function fnBack(){
	document.form1.action='creditControlPaymentProcess.action';
	document.form1.submit();
}

function uploadDocuments(vehicleId) {
	var URL ='${pageContext.request.contextPath}/documentUploadDoUpload.action?applicationNo=<s:property value="applicationNo"/>&reqFrom=<s:property value="reqForm"/>&quoteNo=<s:property value="quoteNo"/>&deleteVehicleId='+vehicleId;
	return popUp(URL,'700','500');
}
function viewDocuments(vehicleId) {
	var URL ='${pageContext.request.contextPath}/documentUploadDoUpload.action?applicationNo=<s:property value="applicationNo"/>&reqFrom=view&quoteNo=<s:property value="quoteNo"/>&deleteVehicleId='+vehicleId;
	return popUp(URL,'700','500');
}

function downloadDoc(filePath,sFileName) {
	document.form1.action = "${pageContext.request.contextPath}/downloaddocumentDoUpload?fileName="+filePath+"&filePath="+filePath;
	document.form1.submit();
}

</script>
</html>