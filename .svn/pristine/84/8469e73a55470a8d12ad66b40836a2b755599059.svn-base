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
		 	
			$(function() {
				try {
					$('#startDate1').datepicker({
						todayHighlight: true,
						format: "dd/mm/yyyy"
					}).on('changeDate', function(e){
					    $(this).datepicker('hide');
					});
					$('#resTime').datepicker({
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
		 <style type="text/css">
			 .tableColWidth {
			 	min-width: 100px;
			 	max-width: 750px;
			 	width: 100px;
			 	white-space: normal;
			 }
	 </style>
	 <style type="text/css">
			 .tableColWidthLarge {
			 	min-width: 500px;
			 	max-width: 750px;
			 	width: 100px;
			 	white-space: normal;
			 }
	 </style>
	 <style type="text/css">
			 .tableColNoWrap {
			 	min-width: 100px;
			 	max-width: 750px;
			 	width: 100px;
			 	white-space: nowrap;
			 }
	 </style>
	</head>
	<body>
	
	
	<div class="container vehDtl">
<div class="Card_Parent ">
<div class="card card-5">
<div class="p-3">

		<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0"> 
			<s:form name="form1" theme="simple">
		  	<tr>
		    	<td>
		    	<s:if test="mode != 'viewlist' ">
		    	<table width="100%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#E5E5E5" >
		      		<s:if test="hasActionErrors()">
						<font color="red"><s:actionerror cssStyle="list-style: none;"/></font>
					</s:if>
		      		<tr>
		        		<td bgcolor="#FFFFFF" style="padding:10px; background:#F8F8F8">
							 		<table width="100%" align="center">
								 		<tr>
								        	<td>
								        	<table width="100%">
								        	<tr>
								        	<s:if test="reqFrom != 'ccpStatus'">
								            <td><h3>Quotation Status</h3><hr></hr></td>
								            </s:if>
								            <s:else>
								            <td><h3>Online Payment Update</h3><hr></hr></td>
								            </s:else>
								       		 </tr>
									        	<tr>
									        		<td>&nbsp;</td>
									        	</tr>
									        	<tr>
									        		<td>
									        		<div class="row">
									        				<div class="col-xs-12 col-sm-12 col-md-3 col-lg-3">
																<div class="">
							    									<label><s:text name="label.startDate"/><font color="red">*</font></label><s:textfield name="startDate" id="startDate1" cssClass="form-control" readonly="true"></s:textfield>
																</div>
															</div>
															<div class="col-xs-12 col-sm-12 col-md-3 col-lg-3">
																<div class="">
							    									<label><s:text name="label.endDate" /><font color="red">*</font></label> <s:textfield name="endDate" id="endDate1" cssClass="form-control" readonly="true" ></s:textfield>
																</div>
															</div>
															<%-- <div class="col-xs-12 col-sm-12 col-md-3 col-lg-3">
																<div class="">
							    									<label><s:text name="label.product" /><font color="red">*</font></label> 
							    										<s:select headerKey="" headerValue="<-- Select Product " list="motorProductList" name="productId" value="productId" listKey="PRODUCT_ID" listValue="PRODUCT_NAME" ></s:select>
																</div>
															</div>--%>
															<s:if test="reqFrom != 'ccpStatus'">
															<div class="col-xs-12 col-sm-12 col-md-2 col-lg-2">
																<div class="">
							    									<label><s:text name="label.paymentType" /><font color="red">*</font></label> 
							    									<s:select cssClass="form-control" headerKey="all" headerValue="All Payment" list="motorPaymentTypeList" name="paymentType" value="paymentType" listKey="item_code" listValue="item_value" ></s:select>
																</div>
															</div>
															</s:if>
															<div class="col-xs-12 col-sm-12 col-md-2 col-lg-2">
																<div class="">
																	<br/>
							    									<input type="button" onclick="fnSubmit();" class="btn btn-sm btn-success" value="Submit" align="left" />
																</div>
															</div>
															
									        			</div>
									        			</td>
										        </tr>
								        	</table>
								        	</td>
							        	</tr>
							      	</table>
							      	<br/>
							      	<br/>
							      	
							</td>
							</tr>
						</table>
						
							<s:if test="mode == 'list'">
						      	<font color="green"><s:actionmessage cssStyle="list-style: none;"/></font>
						      	<tr>
							        <td><h3>Payment Detail</h3><hr></hr></td>
							    </tr>
							    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
									<table class="table table-bordered table-hover" id="tadaTable" cellspacing="0" width="100%" >
									<thead class="bluecolortable">
									<tr>
										<th><s:text name="label.master.sno"/></th>
										<th><s:text name="label.quoteno"/></th>
										<th><s:text name="label.policyNo"/></th>					
										<th><s:text name="label.customerName"/></th>		
										<th><s:text name="label.paymentType"/></th>
										<th><s:text name="label.status"/></th>
										<th></th>
										<th><s:text name="Merchant Ref"/></th>
										<th><s:text name="Emai"/></th>
										<th><s:text name="Application No"/></th>
										<th><s:text name="Currency Type"/></th>
										<th><s:text name="Mobile No"/></th>
										<th><s:text name="Payment No"/></th>
									</tr>
									</thead>
									<tbody class="rowevencolor">
										<s:iterator value="motorPaymentList" var="list" status="stat">
											<tr>
												<td><s:property value="#stat.count"/></td>
												<td><s:property value="#list.QUOTE_NO"/></td>
												<td><s:property value="#list.POLICY_No" /></td>
												<td><s:property value="#list.CUSTOMER_NAME"/></td>
												<td><s:property value="#list.PAYMENT_TYPE" /></td>
												<td><s:property value="#list.RES_DECISION"/></td>
												<td align="center" ><input type="button" onclick="fnView('<s:property value="#list.QUOTE_NO"/>','<s:property value="#list.MERCHANT_REFERENCE"/>');" class="btn btn-sm btn-success" value="View" align="middle" /></td>
												<td><s:property value="#list.MERCHANT_REFERENCE"/></td>
												<td><s:property value="#list.CUSTOMER_EMAIL"/></td>
												<td><s:property value="#list.APPLICATION_NO"/></td>
												<td><s:property value="#list.CURRENCY_TYPE"/></td>
												<td><s:property value="#list.MOBILE_NO"/></td>
												<td><s:property value="#list.PAYMENT_MOBILE_NO"/></td>
											</tr>
										</s:iterator>
									</tbody>
								</table>
								
								
								<div class="row">
									<div class="offset-11 mt-5">
										<button type="button" class="btn btn-success" onclick="downloadReport();" >Excel <i class="fa fa-download  fa-1x"></i></button>
									</div>
								</div>
							</div>
						</s:if>
						
						</s:if>
						<s:if test="mode == 'viewlist' ">
						<div class="panel panel-primary" >
						<div class="panel-heading">
							<h3><s:text name="label.payment.status"/></h3>
						</div><br class="clear"/>
							<div class="container-fluid">
							<s:if test="hasActionErrors()">
								<font color="red"><s:actionerror cssStyle="list-style: none;"/></font>
							</s:if>
									<div class="row">
									     <div class="col-xs-12">
											<div class="panel panel-primary">
												<div class="panel-heading">
													<h4><s:text name="label.customer"/></h4>
													<hr/>
													<div class="pullRight">
																<label><s:text name="label.quoteno" />&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;</label><s:property value="quoteNo" /><s:hidden name="quoteNo"></s:hidden>
													</div>
												</div>
												<div class="panel-body">
									        		<div class="row">
									        				<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
																<div class="">
							    									<label><s:text name="label.first.name"/>&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;</label><s:property value="firstName" />
																</div>
															</div>
															<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
																<div class="">
							    									<label><s:text name="label.last.name"/>&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;</label><s:property value="lastName" />
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
														<h4><s:text name="label.premium.detail"/></h4>
														<hr/>
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
															<!--<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
																<div class="">
							    									<label><s:text name="label.quoteno"/>&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;</label><s:property value="quoteNo" />
																</div>
															</div>
															-->
															<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
																<div class="">
							    									<label><s:text name="label.currencytype"/>&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;</label><s:property value="currency" />
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
							    									<label><s:text name="label.total.premium"/>&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;</label><s:property value="totalPremium" /><s:hidden name="totalPremium"></s:hidden>
																</div>
															</div>
									        				</div>
									        			</div>
									        		</div>
									        		<s:if test="reqFrom == 'ccpStatus'">
									        		<div class="panel panel-primary">
									        			<div class="panel-heading">
															<h4><s:text name="label.payment.detail"/></h4>
															<hr/>
													</div>
														<div class="panel-body">
									        			<div class="row">
								        					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" style="overflow-x: scroll; width:1250px;" >
									        					<table class="table table-bordered table-hover" id="tadaTable" cellspacing="0" width="100%" >
																	<thead class="bluecolortable">
																		<tr>
																			<th class="tableColWidth"><s:text name="label.master.sno"/></th>
																			<th class="tableColNoWrap"><s:text name="label.reqtime"/></th>
																			<th class="tableColNoWrap"><s:text name="label.req.tran"/></th>
																			<th class="tableColNoWrap"><s:text name="label.restime"/></th>					
																			<th class="tableColWidth"><s:text name="label.res.tran"/></th>		
																			<th class="tableColWidth"><s:text name="label.res.code"/></th>
																			<th class="tableColWidth"><s:text name="label.res.msge"/></th>
																			<th class="tableColWidth"><s:text name="label.res.deci"/></th>
																		</tr>
																	</thead>
																	<tbody class="rowevencolor">
																		<s:iterator value="creditPaymentList" var="list" status="stat">
																			<tr>
																				<td><s:property value="#stat.count"/></td>
																				<td class="tableColNoWrap"><s:property value="#list.REQUEST_TIME"/></td>
																				<td ><s:property value="#list.MERCHANT_REFERENCE"/></td>
																				<td class="tableColNoWrap"><s:property value="#list.RESPONSE_TIME" /></td>
																				<td><s:property value="#list.RES_TRANSACTION_ID"/></td>
																				<td class="tableColWidthLarge"><s:property value="#list.RES_DESCRIPTION" /></td>
																				<td class="tableColNoWrap"><s:property value="#list.RES_MESSAGE"/></td>
																				<td><s:property value="#list.RES_DECISION" /></td>
																			</tr>
																		</s:iterator>
																	</tbody>
																</table>
									        					</div>
									        				</div>
									        			</div>
									        		</div>
									        		</s:if>
									        		<br/>
									        		<br/>
									        		<div class="panel panel-primary">
									        		<div class="panel-heading">
													<h4><s:text name="label.payment"/></h4>
													<hr/>
													<div class="pullRight">
														<label><b><s:text name="label.paymentType" /></b>&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;</label><s:property value="paymentMode" />
													</div>
													<br class="clear" />
													</div>
													<div class="panel-body">
													<s:if test="reqFrom != 'ccpStatus'">
													<s:if test="paymentMode == 'Cheque'">
									        		<div class="row">
									        				<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
																<div class="">
							    									<label><s:text name="label.cheque.no"/>&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;</label><s:property value="chequeNo" />
																</div>
															</div>
															<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
																<div class="">
							    									<label><s:text name="label.cheque.date"/>&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;</label><s:property value="chequeDate" />
																</div>
															</div>
															<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
																<div class="">
							    									<label><s:text name="label.cheque.amount"/>&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;</label><s:property value="chequeAmount" />
																</div>
															</div>
															<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
																<div class="">
							    									<label><s:text name="label.bank.name"/>&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;</label><s:property value="bankName" />
																</div>
															</div>
															<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
																<div class="">
							    									<label><s:text name="label.bank.micr"/>&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;</label><s:property value="micr" />
																</div>
															</div>
														</div>
														</s:if>
														<s:if test="paymentMode == 'Cash'">
									        		<div class="row">
									        				<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
																<div class="">
							    									<label><s:text name="label.challan.no"/>&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;</label><s:property value="chequeNo" />
																</div>
															</div>
															<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
																<div class="">
							    									<label><s:text name="label.deposit.date"/>&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;</label><s:property value="chequeDate" />
																</div>
															</div>
															<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
																<div class="">
							    									<label><s:text name="label.amount"/>&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;</label><s:property value="chequeAmount" />
																</div>
															</div>
															<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
																<div class="">
							    									<label><s:text name="label.bank.name"/>&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;</label><s:property value="bankName" />
																</div>
															</div>
															<!--<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
																<div class="">
							    									<label><s:text name="label.bank.micr"/>&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;</label><s:property value="micr" />
																</div>
															</div>
														--></div>
														</s:if>
														<s:if test="paymentMode !='Cheque' && paymentMode !='Cash'">
														<div class="row">
															<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
																<div class="">
							    									<label><s:text name="label.reqtime"/>&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;</label><s:property value="reqTime" />
																</div>
															</div>
															<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
																<div class="">
							    									<label><s:text name="label.restime"/>&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;</label><s:property value="resTime" />
																</div>
															</div>
															<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
																<div class="">
							    									<label><s:text name="label.res.tran"/>&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;</label><s:property value="resTranNo" />
																</div>
															</div>
															<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
																<div class="">
							    									<label><s:text name="label.req.tran"/>&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;</label><s:property value="reqTranNo" />
																</div>
															</div>
															<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
																<div class="">
							    									<label><s:text name="label.res.deci"/>&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;</label><s:property value="resDeci" />
																</div>
															</div>
															<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
																<div class="">
							    									<label><s:text name="label.res.msge"/>&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;</label><s:property value="resMsge" />
																</div>
															</div>
															<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
																<div class="">
							    									<label><s:text name="label.res.code"/>&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;</label><s:property value="resCode" />
																</div>
															</div>
															<%--<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
																<div class="">
							    									<label><s:text name="label.masked.card"/>&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;</label><s:property value="maskedCard" />
																</div>
															</div>
									        				--%></div>
									        			</s:if>
									        			</s:if>
									        			<s:if test="reqFrom == 'ccpStatus'">
									        				<div class="panel-body">
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
																	<%--<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
																		<div class="form-group">
									    									<label><s:text name="label.masked.card"/></label><font color="red">*</font>
																			<s:textfield name="maskedCard" id="maskedCard"  cssClass="form-control" maxlength="20" />
																		</div>
																	</div>
																	--%><div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
																	<div class="form-group">
																			<label><s:text name="label.restime"/></label><font color="red">*</font>
																			<s:textfield name="resTime" id="resTime"  cssClass="form-control form_datetime"  readonly="true" maxlength="20" />
																		    <!--<input size="16" type="text" value="" class="form_datetime" id="form_datetime"></input>
																		--></div>
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
																	<%--<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
																		<div class="form-group">
									    									<label><s:text name="label.res.status"/></label><font color="red">*</font>
																			<s:textfield name="resStatus" id="resStatus"  cssClass="form-control"  maxlength="20" />
																		</div>
																	</div>
																	<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
																		<div class="form-group">
									    									<label><s:text name="label.res.msge"/></label><font color="red">*</font>
																			<s:textfield name="resMsge" id="resMsge"  cssClass="form-control" maxlength="20" />
																		</div>
																	</div>
																	--%>
<!-- 																	<div  > -->
																	<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4" id="genPolicy" style="display:none;">
<!-- 																		<div class="form-group"> -->
									    									<label><s:text name="label.genpolicy.yn"/></label><font color="red">*</font><br class="clear"/>
									    									<s:radio list="#{'Y':'Yes','N':'No'}" name="genPolicyYN" id="genPolicyYN" />
<!-- 																		</div> -->
<!-- 																	</div> -->
																	</div>
											        			</div>
									        				</div>
									        				<s:hidden name="insYN" />
															<s:hidden name="insFrq" />
															<s:hidden name="insAmount" />
															<s:hidden name="insStartdate" />
															<s:hidden name="noInstallment" />
															<s:hidden name="currency" />
									        			</s:if>
									        			<s:if test="reqFrom != 'ccpStatus' ">
											        			<s:if test='(#session.usertype==getText("USER"))'>
																	<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" >
																		<div class="" align="center">
									    									<input type="button" onclick="fnSubmit1();" class="btn btn-sm btn-danger" value="Back" align="middle" />
																		</div>
																	<br class="clear"/>
																</div>
																</s:if>
																<s:else>
													        		<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" align="center">
																		<div class="" >
																			<br/>
											    							<input type="button" onclick="fnSubmit();" class="btn btn-sm btn-danger" value="Back" align="middle" />
																		</div>
												        		    </div>
																</s:else>
									        			</s:if>
									        			<s:if test="reqFrom == 'ccpStatus' ">
									        			<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" align="center">
															<div class="" >
								    							<input type="button" onclick="fnUpdate();" class="btn btn-sm btn-success" value="Submit" align="middle" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								    							<input type="button" onclick="fnBack();" class="btn btn-sm btn-danger" value="Back" align="middle" />
															</div>
									        		    </div>
									        		    </s:if>
													</div>
									        	</div>
									        </div>
									     </div>
									</div>
										<s:hidden name="paymentType" />  
										<s:hidden name="appNo" />     
										<%--<s:hidden name="productId" /> --%>
										<s:hidden name="startDate"  />
										<s:hidden name="endDate"  />
									</div>
								</s:if>		
								</td>
							</tr>
							<s:hidden name="reqFrom"/>
						</s:form>
					</table>
				</div>
			</div>
		</div>
	</div>
<script type="text/Javascript" >
function fnSubmit(){
	    document.form1.action='getMotorPaymentStatus.action?mode=list';
	    document.form1.submit();
}
function fnClick(val){
		document.form1.action='getMotorPaymentStatus.action?policyType='+val+'&mode=showlist';
	    document.form1.submit();
}
function fnView(val,val2){
		document.form1.action='getMotorViewPaymentStatus.action?quoteNo='+val+'&reqTranNo='+val2;
	    document.form1.submit();
}
function fnBack(){
		document.form1.action='getMotorPaymentStatus.action?mode=list';
	    document.form1.submit();
}
function fnUpdate(){
	    document.form1.action='ccpUpdatePaymentStatus.action';
	    document.form1.submit();
}

function fnSubmit1(){
	var menu='P';
	document.form1.action='initReport.action?menuType='+menu;	
	document.form1.submit();
}

getAjaxResCode('<s:property value="resDeci"/>','resCodeAjaxList');
	function getAjaxResCode(val,id){
		var action='';
		if(val=='ACCEPT'){
			$("#genPolicy").show();	
			}
		else{
			$("#genPolicy").hide();	
			}
		if(id=='resCodeAjaxList'){
			action=	'?resDeci='+val+'&resCode=<s:property value="resCode"/>';
		}
		postRequest('${pageContext.request.contextPath}/ajaxGetResCodePaymentStatus.action'+action+'&reqFrom1='+id, id);
	}

	function hideGenPolicy() {
		$("#genPolicy").hide();	
	}
	
	function downloadReport(){
		document.form1.action='reportDownloadPaymentStatus.action';
	    document.form1.submit();
	}
</script>
</body>
</html>