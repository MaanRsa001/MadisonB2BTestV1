<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<style type="text/css">
	#myDIV {
	    overflow-x:scroll;
	    margin: auto:
	}
           }
</style>
<script type="text/javascript" src="js/common.js"></script>

	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/dataTables/css/dataTables.responsive.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/dataTables/css/dataTables.jqueryui.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/dataTables/css/dataTables.tableTools.css">
	<script type="text/javascript" src="${pageContext.request.contextPath}/dataTables/js/dataTables.responsive.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/dataTables/js/dataTables.tableTools.js"></script>
<script type="text/javascript">
		
	 	$(document).ready(function () {
	 		  $('#gridTable').DataTable({
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
		
		$(function() {
			try {
				$('#startDate1').datepicker({
					todayHighlight: true,
					format: "dd/mm/yyyy"
				}).on('changeDate', function(e){
				    $(this).datepicker('hide');
				});
				$('#endDate1').datepicker({
					todayHighlight: true,
					format: "dd/mm/yyyy"
				}).on('changeDate', function(e){
				    $(this).datepicker('hide');
				});
			} catch(err) {console.error(err);}
			
		});
		
		
	 </script>
</head>
<body>
<s:form name="form1" theme="simple">
<div class="container vehDtl">
<div class="Card_Parent ">
       <div class="card card-5">
       <div class="p-3">
<div class="row">
	<div class="col-lg-12">
	<s:if test="mode != 'viewlist' ">
		<div class="panel panel-primary">
			<div class="panel-heading">
				<h3><s:text name="Installment Payment"/></h3>
			</div>				
			<div class="panel-body">
				<s:if test="hasActionErrors()">
					<div class="row">
						<div class="col-xs-12">
							<font color="red"><s:actionerror cssStyle="list-style: none;"/></font>
						</div>						
					</div>
					<br class="clear" />
				</s:if>
				<div class="row">
			    	<div class="col-xs-12 col-sm-12 col-md-4 col-lg-3">
						<div class="">
							<label><s:text name="label.startDate"/><font color="red">*</font></label><s:textfield name="startDate" id="startDate1" cssClass="form-control" readonly="true"></s:textfield>
						</div>
					</div>
					<div class="col-xs-12 col-sm-12 col-md-4 col-lg-3">
						<div class="">
							<label><s:text name="label.endDate" /><font color="red">*</font></label> <s:textfield name="endDate" id="endDate1" cssClass="form-control" readonly="true" ></s:textfield>
						</div>
					</div>
					<div class="col-xs-12 col-sm-12 col-md-4 col-lg-3" align="center">
					<br class="clear"/>
						<input type="button" onclick="fnSubmit('');" class="btn btn-sm btn-success" value="Submit" align="left" />
					</div>
				</div>
				<br class="clear"/>
							<div class="" align="center">
								<font color="red" >OR</font>
							</div>
				<div class="row">
					<div class="col-xs-12 col-sm-12 col-md-4 col-lg-3">
							<div class="">
								<label><s:text name="Search By"/><font color="red">*</font></label><s:select name="searchType" id="searchType" list="#{'P':'Policy No','Q':'Quote No','C':'Customer Name'}"  headerKey="" headerValue="---Select---" cssClass="form-control"/>
							</div>
						</div>
						<div class="col-xs-12 col-sm-12 col-md-4 col-lg-3">
							<div class="">
								<label><s:text name="Enter Data For Search" /><font color="red">*</font></label><s:textfield name="searchValue" id="searchValue" cssClass="form-control" ></s:textfield>
							</div>
						</div>
						<div class="col-xs-12 col-sm-12 col-md-4 col-lg-3" align="center">
						<br class="clear"/>
							<input type="button" onclick="fnSubmit('search');" class="btn btn-sm btn-success" value="Search" align="left" />
					</div>
				</div>
			</div>
		</div>
		</s:if>	
		<s:if test="mode == 'showlist'">
			<div class="panel panel-primary">
				<div class="panel-heading">
					<s:text name="Installment Detail"/>
				</div> 
				<div class="panel-body">
<!-- 					<table class="display responsive no-wrap" id="gridTable" width="100%" cellspacing="0"> -->
					<table class="table table-bordered table-hover" id="gridTable" cellspacing="0" width="100%" >
						<thead class="bluecolortable">
						<tr>
							<th><s:text name="label.master.sno"/></th>
							<th><s:text name="label.quoteno"/></th>
							<th><s:text name="label.policyNo"/></th>					
							<th><s:text name="label.customerName"/></th>		
							<th><s:text name="label.premium"/></th>
							<th><s:text name="label.dueAmount"/></th>		
							<th><s:text name="label.dueDate"/></th>
							<th><s:text name="label.currentIns"/></th>
							<th><s:text name="payment.modeOfPayment"/></th>
							<th><s:text name="label.status"/></th>
							<th></th>
						</tr>
						</thead>
						<tbody class="rowevencolor">
							<s:iterator value="paymentDueList" var="list" status="stat">
							<tr>
								<td><s:property value="#stat.count"/></td>
								<td><s:property value="#list.QUOTE_NO"/></td>
								<td><s:property value="#list.POLICY_No" /></td>
								<td><s:property value="#list.CUSTOMER_NAME"/></td>
								<td align="right"><s:property value="#list.OVERALL_PREMIUM"/></td>
								<td align="right"><s:property value="#list.PREMIUM_AMOUNT" /></td>
								<td><s:property value="#list.PREMIUM_DATE"/></td>
								<td align="center"><s:property value="#list.DESCRIPTION"/></td>
								<td align="center"><s:property value="#list.PAYMENT_MODE"/></td>
								<td align="center"><s:property value="#list.PAYMENT_STATUS"/></td>
								<td align="center" ><input type="button" onclick="fnView('<s:property value="#list.QUOTE_NO"/>','<s:property value="reqFrom" />');" class="btn btn-sm btn-success" value="View" align="middle" /></td>
							</tr>
							</s:iterator>
						</tbody>
					</table> 	
				</div>   
			</div>
		</s:if>
		<s:if test="mode == 'viewlist' ">
						<div class="panel panel-primary" >
						<div class="panel-heading">
							<s:text name="label.installment.status"/>
						</div><br class="clear"/>
							<div class="container-fluid">
							<s:if test="hasActionErrors()">
								<font color="red"><s:actionerror cssStyle="list-style: none;"/></font>
							</s:if>
							<font color="green"><s:actionmessage cssStyle="list-style: none;"/></font>
									<div class="row">
									     <div class="col-xs-12">
											<div class="panel panel-primary">
												<div class="panel-heading">
													<s:text name="label.customer"/>
													<div class="pullRight">
																<label><s:text name="label.quoteno" />&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;</label><s:property value="quoteNo" /><s:hidden name="quoteNo"></s:hidden><s:hidden name="paymentMode"></s:hidden>
													</div>
												</div>
												<div class="panel-body">
									        		<div class="row">
									        				<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
																<div class="">
							    									<label><s:text name="label.customerName"/>&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;</label><s:property value="firstName" />&nbsp;&nbsp;<s:property value="lastName" />
																</div>
															</div>
															<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
																<div class="">
							    									<label><s:text name="label.add1"/>&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;</label><s:property value="add1" />
																</div>
															</div>
															<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
																<div class="">
							    									<label><s:text name="label.add2"/>&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;</label><s:property value="add2" />
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
							    									<label><s:text name="label.mail"/>&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;</label><s:property value="mailId" />
																</div>
															</div>
									        				</div>
									        		</div>
									        		</div>
									        		<div class="panel panel-primary">
									        		<div class="panel-heading">
														<s:text name="label.premium.detail"/>
													</div>
													<div class="panel-body">
									        		<div class="row">
									        				<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
																<div class="">
							    									<label><s:text name="label.policyNo"/>&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;</label><s:property value="policyNo" />
																</div>
															</div>
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
							    									<label><s:text name="label.total.premium"/>&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;</label><s:property value="totalPremium" />
																</div>
															</div>
															<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
																<div class="">
							    									<label><s:text name="label.currencytype"/>&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;</label><s:property value="currency" />
																</div>
															</div>
															<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
																<div class="">
							    									<label><s:text name="label.noOfEmi"/>&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;</label><s:property value="noOfEmi" />
																</div>
															</div>
									        				</div>
									        			</div>
									        		</div>
									        		<div class="panel panel-primary">
									        			<div class="panel-heading">
															<s:text name="label.installment.detail"/>
														</div>
														<div class="panel-body">
									        			<div class="row">
									        					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" style="overflow-x: scroll; width:1250px;">
									        					<table class="footable" >
																		<thead>
																		<tr>
																			<th align="center"><s:text name="label.master.sno"/></th>
																			<th align="center"><s:text name="label.ins"/></th>
																			<th align="center"><s:text name="label.desc"/></th>
																			<th align="right"><s:text name="label.dueAmount"/></th>					
																			<th align="center"><s:text name="label.dueDate"/></th>		
																			<th align="center"><s:text name="label.status"/></th>
																			<th align="center"><s:text name="Payment Status"/></th>
																			
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
																					<td align="center"><s:property value="#list.STATUS" /></td>
																					<s:if test='"Pending".equalsIgnoreCase(#list.STATUS)'>
																						<s:if test='(#session.usertype==getText("USER"))'>
																							<td align="center">Due for Payment</td>
																						</s:if>
																						<s:else>
																							<td align="center" ><input type="button" onclick="fnViewPayment('<s:property value="#list.INSTALLMENT_NO"/>','<s:property value="#list.DESCRIPTION"/>','<s:property value="#list.PREMIUM_AMOUNT" />','updatePayment');" class="btn btn-sm btn-warning" value="Make Payment" align="middle" /></td>
																						</s:else>
																					</s:if>
																					<s:else>
																							<td align="center" ><input type="button" onclick="fnViewPayment('<s:property value="#list.INSTALLMENT_NO"/>','<s:property value="#list.DESCRIPTION"/>','<s:property value="#list.PREMIUM_AMOUNT" />','viewPayment');" class="btn btn-sm btn-success" value="View Payment" align="middle" /></td>
																					</s:else>
																				</tr>
																			</s:iterator>
																		</tbody>
																	</table>
									        					</div>
									        				</div>
									        			</div>
									        			<s:if test="reqFrom1 != 'updatePayment'">
									        					<s:hidden name="insAmount" id="insAmount"/>
									        			</s:if>
									        		</div>
									    <s:if test="reqFrom1 == 'viewPayment' || reqFrom1 == 'updatePayment'" >
									    <div class="panel panel-primary">
						        			<div class="panel-heading">
													<s:text name="Payment Details" />
												<div class="pullRight">
													<label><s:text name="payment.modeOfPayment" />&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;</label><s:property value="paymentType" />
												</div>
											</div>
											<br class="clear"/>
											<div class="panel-body">
											<div class="row">
												<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
													<div class="">
				    									<label><s:text name="Installment No"/>&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;</label><s:property value="noInstallment" />
													</div>
												</div>
												<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
													<div class="">
				    									<label><s:text name="Pay Amount"/>&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;</label><s:property value="insAmount" />
													</div>
												</div>
												<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
													<div class="">
				    									<label><s:text name="Installment Description"/>&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;</label><s:property value="desci" />
													</div>
												</div>
												<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
													<div class="">
				    									<label><s:text name="label.currencytype"/>&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;</label><s:property value="currency" />
													</div>
												</div>
											</div>
											<br class="clear"/>
										 	<s:if test="reqFrom1 == 'viewPayment'">
					        					<s:if test='"2".equalsIgnoreCase(paymentMode)'>		
													<div class="row">
													<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
															<div class="">
						    									<label><s:text name="payment.cheque.No"/>&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;</label><s:property value="chequeNo" />
															</div>
															</div>
															<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
																<div class="">
							    									<label><s:text name="payment.cheque.Date"/>&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;</label><s:property value="chequeDate" />
																</div>
															</div>
															<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
																<div class="">
							    									<label><s:text name="payment.cheque.Amount"/>&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;</label><s:property value="chequeAmount" />
																</div>
															</div>
															<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
																<div class="">
							    									<label><s:text name="payment.cheque.bankName"/>&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;</label><s:property value="bankName" />
																</div>
															</div>
															<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
																<div class="">
							    									<label><s:text name="label.bank.micr"/>&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;</label><s:property value="micr" />
																</div>
															</div>
													</div>
												<br class="clear" />
												</s:if>
												<s:elseif test='"1".equalsIgnoreCase(paymentMode)'>
																<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
																		<div class="">
									    									<label><s:text name="payment.cash.depositBank"/>&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;</label><s:property value="cashDepositBank" />
																		</div>
																</div>
																<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
																		<div class="">
									    									<label><s:text name="payment.cash.premiumAmount"/>&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;</label><s:property value="cashAmount" />
																		</div>
																	</div>
																<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
																		<div class="">
									    									<label><s:text name="payment.cash.challanNumber"/>&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;</label><s:property value="cashChellanNo" />
																		</div>
																	</div>
															<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
																	<div class="">
								    									<label><s:text name="payment.cash.instrumentDate"/>&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;</label><s:property value="cashInstrumentDate" />
																	</div>
															</div>
												</s:elseif>
												<s:elseif test='"6".equalsIgnoreCase(paymentMode)'>
													<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
														<div class="">
					    									<label><s:text name="label.reqtime"/></label>&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;
															<s:property value="reqTime" />
														</div>
													</div>
													<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
														<div class="">
					    									<label><s:text name="label.req.tran"/></label>&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;
															<s:property value="reqTranNo" />
														</div>
													</div>
													<div class="form-group">
															<label><s:text name="label.restime"/></label>&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;
															<s:property value="resTime" />
													</div>
													<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
														<div class="">
					    									<label><s:text name="label.res.tran"/></label>&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;
															<s:property value="resTranNo" />
														</div>
													</div>
													<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
														<div class="">
					    									<label><s:text name="label.res.payAmount"/></label>&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;
															<s:property value="payAmount" />
														</div>
													</div>
													<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
														<div class="">
					    									<label><s:text name="label.card.date"/></label>&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;
															<s:property value="cardDate" />
														</div>
													</div>
													<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
														<div class="">
					    									<label><s:text name="label.res.deci"/></label>&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;
															<s:property value="resDeci" />
														</div>
													</div>
													<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
														<div class="">
					    									<label><s:text name="label.res.code"/></label>&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;
					    										<s:property value="resCode" />
														</div>
													</div>
													<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
														<div class="">
					    									<label><s:text name="label.res.msge"/></label>&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;
					    										<s:property value="resMsge" />
														</div>
													</div>
					        			</s:elseif>
					        			</s:if>
										<s:if test="reqFrom1 == 'updatePayment'">
					        			<s:if test='"2".equalsIgnoreCase(paymentMode)'>		
													<div class="row">
														<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
															<div class="form-group">
																<label><s:text name="payment.cheque.No" /><font color="red">*</font></label>
																<s:textfield name="chequeNo" id="chequeNo"  cssClass="form-control tooltipContent" onkeyup="checkNumbers(this);" maxlength="10" />
															</div>
														</div>
														<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
															<div class="form-group">
																	<label><s:text name="payment.cheque.Date" /><font color="red">*</font></label>
																	<s:textfield name="chequeDate" id="chequeDate" cssClass="form-control datePicker" />
															</div>
														</div>
														<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
															<div class="form-group">
																<label><s:text name="payment.cheque.Amount" /><font color="red">*</font></label>
																<s:textfield name="insAmount" id="insAmount"  cssClass="form-control" maxlength="20" onkeyup="checkDecimals10_2(this);" readonly="true"/>
															</div>
														</div>
														<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
															<div class="form-group">
																<label><s:text name="label.late.fee" /></label>
																<s:textfield name="chequeAmount" id="chequeAmount"  cssClass="form-control" maxlength="20" onkeyup="checkDecimals10_2(this);" />
															</div>
														</div>
														<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
															<div class="form-group">
																<label><s:text name="payment.cheque.bankName" /><font color="red">*</font></label>
																<s:select name="bankName" id="bankName" list="bankNamelist" listKey="CODE" listValue="CODEDESC" headerKey="" headerValue="---Select---" cssClass="form-control"/>
															</div>
														</div>
														<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
															<div class="form-group">
																<label><s:text name="label.bank.micr" /><font color="red">*</font></label>
																<s:textfield name="micr" id="micr"  cssClass="form-control " onkeyup="checkNumbers(this);" maxlength="9" />
															</div>
														</div>
													</div>
										</s:if>
										<s:elseif test='"1".equalsIgnoreCase(paymentMode)'>
													<div class="row">
														<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
														<div class="form-group">
																<label><s:text name="payment.cash.depositBank" /> <font color="red">*</font></label>
																<s:select name="cashDepositBank" id="cashDepositBank" list="bankNamelist" listKey="CODE" listValue="CODEDESC" value="5"  headerKey="" headerValue="---Select---"  cssClass="form-control tooltipContent"></s:select>
														</div>
														</div>
														<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
														<div class="form-group">
																<label><s:text name="payment.cash.premiumAmount" /><font color="red">*</font></label>
																<s:textfield name="insAmount" id="insAmount"  maxlength="14" onkeyup="checkDecimals10_2(this);" cssClass="form-control" readonly="true"/>
														</div>
														</div>
														<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
														<div class="form-group">
																<label><s:text name="label.late.fee" /></label>
																<s:textfield name="cashAmount" id="cashAmount"  maxlength="14" onkeyup="checkDecimals10_2(this);" cssClass="form-control" />
														</div>
														</div>
														<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
														<div class="form-group">
																<label><s:text name="payment.cash.challanNumber" /></label>
																<s:textfield name="cashChellanNo" id="cashChellanNo"  maxlength="20" cssClass="form-control" />
														</div>
														</div>
													</div>
													<div class="row">
														<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
														<div class="form-group">
														<label><s:text name="payment.cash.instrumentDate" /> <font color="red">*</font></label>
														<s:textfield id="cashInstrumentDate" name="cashInstrumentDate" cssClass="form-control datepicker" readonly="true"/>
														</div>
														</div>
													</div>
									</s:elseif>
									<s:elseif test='"6".equalsIgnoreCase(paymentMode)'>
							        			<div class="row">
													<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
														<div class="form-group">
					    									<label><s:text name="label.reqtime"/></label>
															<s:textfield name="reqTime" id="reqTime"  cssClass="form-control" maxlength="20" readonly="true" />
														</div>
													</div>
													<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
														<div class="form-group">
					    									<label><s:text name="label.req.tran"/></label>
															<s:textfield name="reqTranNo" id="reqTranNo"  cssClass="form-control" maxlength="20" readonly="true" />
														</div>
													</div>
													<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
														<div class="form-group">
																<label><s:text name="label.restime"/></label><font color="red">*</font>
																<s:textfield name="resTime" id="resTime"  cssClass="form-control form_datetime"  readonly="true" maxlength="20" />
														</div>
													</div>
													<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
														<div class="form-group">
					    									<label><s:text name="label.res.tran"/></label><font color="red">*</font>
															<s:textfield name="resTranNo" id="resTranNo"  cssClass="form-control" maxlength="20" />
														</div>
													</div>
													<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
														<div class="form-group">
					    									<label><s:text name="label.res.payAmount"/></label><font color="red">*</font>
															<s:textfield name="insAmount" id="insAmount"  cssClass="form-control" maxlength="20" readonly="true" />
														</div>
													</div>
													<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
														<div class="form-group">
					    									<label><s:text name="label.late.fee"/></label>
															<s:textfield name="payAmount" id="payAmount"  cssClass="form-control" maxlength="20" />
														</div>
													</div>
													<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
														<div class="form-group">
					    									<label><s:text name="label.card.date"/></label><font color="red">*</font>
															<s:textfield name="cardDate" id="carddate"  cssClass="form-control datePicker" readonly="true" maxlength="20" />
														</div>
													</div>
													<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
														<div class="form-group">
					    									<label><s:text name="label.res.deci"/></label><font color="red">*</font>
															<s:select cssStyle="width:100%;" name="resDeci" id="resDeci" cssClass="form-control" headerKey="" headerValue="--Select--" list="resDecisionList" listKey="ITEM_VALUE" listValue="ITEM_VALUE" onchange="getAjaxResCode(this.value,'resCodeAjaxList');" theme="simple"/>
														</div>
													</div>
													<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
														<div class="form-group">
					    									<label><s:text name="label.res.code"/></label><font color="red">*</font>
					    										<s:select list="#{}" id="resCodeAjaxList" cssStyle="width:100%;" name="resCode" cssClass="form-control"  headerKey="" headerValue="---Select---" />
														</div>
													</div>
							        			</div>
					        			</s:elseif>
					        			<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" >
											<div class="" align="center">
		    									<input type="button" onclick="fnInsPayment('<s:property value="noInstallment" />','<s:property value="insAmount" />','<s:property value="desci" />','<s:property value="reqFrom1" />');" class="btn btn-sm btn-success" value="Payment Update" align="middle" />
											</div>
										<br class="clear"/>
										</div>
					        		</s:if>
					        		</div>
					        		</div>
					        		</s:if>
					        		<s:if test='(#session.usertype==getText("USER"))'>
										<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" >
											<div class="" align="center">
		    									<input type="button" onclick="fnSubmit1();" class="btn btn-sm btn-danger" value="Back" align="middle" />
											</div>
										<br class="clear"/>
									</div>
									</s:if>
									<s:else>
						        		<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" >
											<div class="" align="center">
		    									<input type="button" onclick="fnSubmit('<s:property value="reqFrom" />');" class="btn btn-sm btn-danger" value="Back" align="middle" />
											</div>
										<br class="clear"/>
									</div>
									</s:else>
						        </div>
						     </div>
						</div>
						<s:hidden name="startDate"  />
						<s:hidden name="endDate"  />
						<s:hidden name="searchType"  />
						<s:hidden name="searchValue"  />
				</div>
			</s:if>	
		</div>
	</div>
	</div>
</div>
</div>
</div>
</s:form>
<script type="text/Javascript" >
function fnSubmit(val){
	    document.form1.action='getInstallmentPaymentStatus.action?mode=showlist&reqFrom='+val;
	    document.form1.submit();
}
function fnSubmit1(){
	var menu='P';
	document.form1.action='initReport.action?menuType='+menu;	
	document.form1.submit();
}

function fnView(val,val1){
		document.form1.action='getInstallmentViewPaymentStatus.action?quoteNo='+val+'&reqFrom='+val1;
	    document.form1.submit();
}
function fnViewPayment(val,desci,insamount,mode){
	document.form1.insAmount.value = insamount;
	document.form1.action='getInsPaymentViewPaymentStatus.action?noInstallment='+val+'&desci='+desci+'&reqFrom1='+mode;
    document.form1.submit();
}
function fnInsPayment(val,insamount,desci,mode){
	document.form1.insAmount.value = insamount;
	document.form1.action='getInsPaymentInsertPaymentStatus.action?noInstallment='+val+'&desci='+desci+'&reqFrom1='+mode;
    document.form1.submit();
}
getAjaxResCode('<s:property value="resDeci"/>','resCodeAjaxList');
function getAjaxResCode(val,id){
	var action='';
	if(id=='resCodeAjaxList'){
		action=	'?resDeci='+val+'&resCode=<s:property value="resCode"/>';
	}
	postRequest('${pageContext.request.contextPath}/ajaxGetResCodePaymentStatus.action'+action+'&reqFrom1='+id, id);
}
</script>
</body>
</html>