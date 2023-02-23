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
	<script type="text/javascript" src="js/tcal.js"></script>
	<link href="css/styles.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/cssbootstrap/bootstrap.min.css" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/bootstrap/css/footable-0.1.css"/>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/bootstrap/css/bootstrap-datetimepicker.css"/>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/dataTables/css/dataTables.responsive.css"/>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/dataTables/css/dataTables.jqueryui.css"/>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/dataTables/css/dataTables.tableTools.css"/>
	<link href="<%=request.getContextPath()%>/dataTables/css/jquery-start-ui.css" rel="stylesheet" type="text/css" />
	<script src="<%=request.getContextPath()%>/bootstrap/js/bootstrap-datetimepicker.js" type="text/javascript"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/dataTables/js/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/dataTables/js/jquery.dataTables.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/dataTables/js/dataTables.jqueryui.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/dataTables/js/dataTables.responsive.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/dataTables/js/dataTables.tableTools.js"></script>
	<script type="text/javascript">
			jQuery(function ($) {
				try {
					var data = $('#gridTable').dataTable( {
						"lengthMenu": [[10, 25, 50, -1], [10, 25, 50, "All"]],
						"order": [[ 0, "asc" ]],
						"columnDefs": [ {
				          "targets": 'no-sort',
				          "orderable": false
					    } ],
						responsive: true
					});
				} catch(err){}
			} );
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
								            <td bgcolor="#FFFFFF" class="heading" width="100%" >Quotation Status</td>
								       		 </tr>
									        	<tr>
									        		<td>&nbsp;</td>
									        	</tr>
									        	<tr>
									        		<td>
									        		<div class="row">
									        				<div class="col-xs-12 col-sm-12 col-md-3 col-lg-3">
																<div class="">
							    									<label><s:text name="label.startDate"/><font color="red">*</font></label><s:textfield name="startDate" id="startDate1" cssClass="inputBox datepicker" readonly="true"></s:textfield>
																</div>
															</div>
															<div class="col-xs-12 col-sm-12 col-md-3 col-lg-3">
																<div class="">
							    									<label><s:text name="label.endDate" /><font color="red">*</font></label> <s:textfield name="endDate" id="endDate1" cssClass="inputBox datepicker" readonly="true" ></s:textfield>
																</div>
															</div>
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
							      	<br class="clear"/>
							      	<br class="clear"/>
							      	<br class="clear"/>
							      	<s:if test="mode == 'list'">
							      	<font color="green"><s:actionmessage cssStyle="list-style: none;"/></font>
							      	<tr>
								        <td bgcolor="#FFFFFF" class="heading" width="100%" >Motor Payment Detail</td>
								    </tr>
									<table class="footable" id="gridTable">
									<thead style="background: #F8F8F8;">
									<tr>
										<th><s:text name="label.master.sno"/></th>
										<th><s:text name="label.quoteno"/></th>
										<th><s:text name="label.policyNo"/></th>					
										<th><s:text name="label.customerName"/></th>		
										<th><s:text name="label.paymentType"/></th>
										<th><s:text name="label.status"/></th>
										<th></th>
									</tr>
									</thead>
									<tbody>
										<s:iterator value="motorPaymentList" var="list" status="stat">
											<tr>
												<td><s:property value="#stat.count"/></td>
												<td><s:property value="#list.QUOTE_NO"/></td>
												<td><s:property value="#list.POLICY_No" /></td>
												<td><s:property value="#list.CUSTOMER_NAME"/></td>
												<td><s:property value="#list.PAYMENT_TYPE" /></td>
												<td><s:property value="#list.RES_DECISION"/></td>
												<td align="center" ><input type="button" onclick="fnView('<s:property value="#list.QUOTE_NO"/>','<s:property value="#list.MERCHANT_REFERENCE"/>');" class="btn btn-sm btn-success" value="View" align="middle" /></td>
											</tr>
										</s:iterator>
									</tbody>
								</table>
							</s:if>
							</td>
							</tr>
						</table>
						</s:if>
						<s:if test="mode == 'viewlist' ">
						<div class="panel panel-primary" >
						<div class="panel-heading">
							<s:text name="label.payment.status"/>
						</div><br class="clear"/>
							<div class="container-fluid">
							<s:if test="hasActionErrors()">
								<font color="red"><s:actionerror cssStyle="list-style: none;"/></font>
							</s:if>
									<div class="row">
									     <div class="col-xs-12">
											<div class="panel panel-primary">
												<div class="panel-heading">
													<s:text name="label.customer"/>
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
							    									<label><s:text name="label.mail"/>&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;</label><s:property value="eMailId" />
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
									        				</div>
									        			</div>
									        		</div>
									        		<div class="panel panel-primary">
									        			<div class="panel-heading">
															<s:text name="label.payment.detail"/>
													</div>
														<div class="panel-body">
									        			<div class="row">
									        					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" style="overflow-x: scroll; width:1250px;">
									        					<table class="footable" >
																		<thead>
																		<tr>
																			<th class="tableColWidth"><s:text name="label.master.sno"/></th>
																			<th class="tableColNoWrap"><s:text name="label.reqtime"/></th>
																			<th class="tableColNoWrap"><s:text name="label.req.tran"/></th>
																			<th class="tableColNoWrap"><s:text name="label.restime"/></th>					
																			<th class="tableColWidth"><s:text name="label.res.tran"/></th>		
																			<th class="tableColWidth"><s:text name="label.res.status"/></th>
																			<%--<th><s:text name="label.res.msge"/></th>--%>
																			<th class="tableColWidth"><s:text name="label.masked.card"/></th>
																			<th class="tableColWidth"><s:text name="label.res.code"/></th>
																			<th class="tableColWidth"><s:text name="label.res.msge"/></th>
																			<th class="tableColWidth"><s:text name="label.res.deci"/></th>
																		</tr>
																		</thead>
																		<tbody>
																			<s:iterator value="creditPaymentList" var="list" status="stat">
																				<tr>
																					<td><s:property value="#stat.count"/></td>
																					<td class="tableColNoWrap"><s:property value="#list.REQUEST_TIME"/></td>
																					<td ><s:property value="#list.MERCHANT_REFERENCE"/></td>
																					<td class="tableColNoWrap"><s:property value="#list.RESPONSE_TIME" /></td>
																					<td><s:property value="#list.RES_TRANSACTION_ID"/></td>
																					<td><s:property value="#list.RESPONSE_STATUS" /></td>
																					<%--<td><s:property value="#list.RESPONSE_MESSAGE"/></td>--%>
																					<td><s:property value="#list.CARD_NUMBER_MASKED"/></td>
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
									        		<div class="panel panel-primary">
									        		<div class="panel-heading">
													<s:text name="label.payment"/>
															<div class="pullRight">
																<label><s:text name="label.paymentType" />&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;</label><s:property value="paymentMode" />
															</div>
													<br class="clear" />
													</div>
													<div class="panel-body">
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
																	<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
																		<div class="form-group">
									    									<label><s:text name="label.masked.card"/></label><font color="red">*</font>
																			<s:textfield name="maskedCard" id="maskedCard"  cssClass="form-control" maxlength="20" />
																		</div>
																	</div>
																	<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
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
									    									<label><s:text name="label.res.bill.tran"/></label><font color="red">*</font>
																			<s:textfield name="resBillTranNo" id="resBillTranNo"  cssClass="form-control" maxlength="20" />
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
																	<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
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
											        			</div>
									        				</div>
									        			<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" align="center">
															<div class="" >
								    							<input type="button" onclick="fnUpdate();" class="btn btn-sm btn-success" value="Submit" align="middle" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								    							<input type="button" onclick="fnBack();" class="btn btn-sm btn-danger" value="Back" align="middle" />
															</div>
									        		    </div>
													</div>
									        	</div>
									        </div>
									     </div>
									</div>
										<s:hidden name="paymentType" />       
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