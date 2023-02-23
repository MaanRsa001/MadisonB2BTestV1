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
		<script type="text/javascript" src="js/tcal.js"></script>
		<link href="css/styles.css" rel="stylesheet" type="text/css" />
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/cssbootstrap/bootstrap.min.css" />
		<script type="text/javascript" src="pages/admin/ratingEngine/menu.js"> </script>
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/dataTables/css/dataTables.responsive.css">
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/dataTables/css/dataTables.jqueryui.css">
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/dataTables/css/dataTables.tableTools.css">
		<link href="<%=request.getContextPath()%>/dataTables/css/jquery-start-ui.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="<%=request.getContextPath()%>/dataTables/js/jquery-1.11.1.min.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/dataTables/js/jquery.dataTables.min.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/dataTables/js/dataTables.jqueryui.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/dataTables/js/dataTables.responsive.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/dataTables/js/dataTables.tableTools.js"></script>
		<script type="text/javascript">
		jQuery(function ($) {
			try {
				var data = $('#gridTableMake').dataTable( {
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
	</head>
	<body>
		<s:form id="updateCashRenewal" name="updateCashRenewal" action="" theme="simple">
			<div class="container-fluid">
				<div class="row">
					<div class="col-xs-12" align="center">
						<div align="left">
							<s:if test="hasActionErrors()">
								<font color="red"><s:actionerror/></font>
							</s:if>
							<font color="green"><s:actionmessage cssStyle="list-style: none;" /></font>
						</div>
						<div class="panel-body"> 
							<div class="panel panel-primary">
								<div class="panel-heading" align="left">
									<s:text name="Quick Renewal Cash/Cheque Pending Renewals" />
								</div>
								<div class="panel-body"> 
									<div class="panel panel-primary">
										<div class="panel-heading" align="left">
											<s:text name="Policy Information" />
										</div>
										<div class="panel-body">
											<div class="row" class="col-xs-12 col-sm-10 col-md-10 col-lg-12" align="left">
												<div >
													<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
														<div class="">
					    									<label><s:text name="Customer Name"/>&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;</label><s:property value="custInsuredName" />
														</div>
													</div>
							        				<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
														<div class="">
					    									<label><s:text name="Policy No"/>&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;</label><s:property value="policyNo" /><s:hidden name="policyNo"></s:hidden>
														</div>
													</div>
													<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
														<div class="">
					    									<label><s:text name="Quote No"/>&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;</label><s:property value="merchant_reference" /><s:hidden name="merchant_reference"></s:hidden>
														</div>
													</div>
													<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
														<div class="">
					    									<label><s:text name="label.claim.phone"/>&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;</label><s:property value="mobileNo" />
														</div>
													</div>
													<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
														<div class="">
					    									<label><s:text name="Email ID"/>&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;</label><s:property value="email" />
														</div>
													</div>
													<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
														<div class="">
					    									<label><s:text name="Total Premium Amount"/>&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;</label><s:property value="premium" />
														</div>
													</div>
<!--													<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">-->
<!--														<div class="">-->
<!--					    									<label><s:text name="Risk ID"/>&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;</label><s:property value="riskId" />-->
<!--														</div>-->
<!--													</div>-->
<!--													<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">-->
<!--														<div class="">-->
<!--					    									<label><s:text name="Vechicle Registration No"/>&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;</label><s:property value="rskId" />-->
<!--														</div>-->
<!--													</div>-->
												</div>
					        				</div>
										</div>
									</div>
									<div class="panel panel-primary">
										<div class="panel-heading" align="left">
											<s:text name="Vehicle Information" />
										</div>
										<div class="panel-body"> <!-- content body -->
											<s:iterator value="vehList" var="list">
												<div class="panel panel-primary">
													<div class="panel-body">
														<div align="left">
															<label><u><s:text name="Risk ID"></s:text>&nbsp;:&nbsp;<s:property value="#list.RISK_ID"/></u></label>
														</div>
														<div class="row" class="col-xs-12 col-sm-10 col-md-10 col-lg-12" align="left">
															<div class="col-xs-12 col-sm-12 col-md-3 col-lg-3">
																<div class="">
							    									<label><s:text name="vehicle Registration No"/>&nbsp;:&nbsp;</label><s:property value="#list.REG_NO" />
																</div>
															</div>
															<div class="col-xs-12 col-sm-12 col-md-3 col-lg-3">
																<div class="">
							    									<label><s:text name="Policy Start Date"/>&nbsp;:&nbsp;</label><s:property value="#list.CUR_FROM_DT" />
																</div>
															</div>
															<div class="col-xs-12 col-sm-12 col-md-3 col-lg-3">
																<div class="">
							    									<label><s:text name="Policy End Date"/>&nbsp;:&nbsp;</label><s:property value="#list.CUR_TO_DT" />
																</div>
															</div>
															<div class="col-xs-12 col-sm-12 col-md-3 col-lg-3">
																<div class="">
							    									<label><s:text name="Premium Amount"/>&nbsp;:&nbsp;</label><s:property value="#list.CUST_PREM" />
																</div>
															</div>
														</div>
													</div>
												</div>
											</s:iterator>
										</div>
									</div>
									
								<s:if test='modeOfPayment == "1"'>
									<div class="panel panel-primary" id="cheque" style="">
										<div class="panel-heading" align="left">
											<s:text name="Payment Information" />
											<div class="pullRight">
												<label>Mode Of Payment&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;</label>Cash
											</div>
										</div>
										<div class="panel-body">
											<div class="row">
												<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
													<div class="text">Deposit Date<font color="red">*</font></div>
													<div class="tbox">
														<s:textfield  name="chequeDate" disabled="true" class="inputBox tooltipContent" />
													</div>
												</div>
												<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
													<div class="text">Premium Amount<font color="red">*</font></div>
													<div class="tbox">
														<s:textfield  name="chequeAmount" disabled="true" class="inputBox tooltipContent"  />
													</div>
												</div>
												<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
													<div class="text">Deposit Bank<font color="red">*</font></div>
													<div class="tbox">
<!--														<input type="text" name="bank" maxlength="20" id="chequeAmount" class="inputBox tooltipContent" onkeyup="checkDecimals10_2(this);" data-content="Cheque Amount" disabled="disabled" data-original-title="" title="">-->
														<s:select name="cashDepositBank" id="cashDepositBank" list="bankNamelist" listKey="CODE" listValue="CODEDESC" value="5"  headerKey="" headerValue="---Select---"  cssClass="inputBoxS tooltipContent" data-content="Deposit Bank" disabled="true"  ></s:select>
													</div>
												</div>
												<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
													<div class="text">Reference Code<font color="red">*</font></div>
													<div class="tbox">
<!--														<input type="text" name="micrCode" maxlength="9"  id="micrCode" class="inputBox tooltipContent" onkeyup="checkNumbers(this);" data-content="9 digit number indicated at the bottom of the cheque. Position varies according to cheque issuing bank." disabled="disabled" data-original-title="" title="">-->
														<s:textfield  name="chequeNo" disabled="true" class="inputBox tooltipContent" />
													</div>
												</div>
											</div>
										</div>
									</div>
								</s:if>

								<s:if test='modeOfPayment == "2"'>
									<div class="panel panel-primary" id="cheque" style="">
										<div class="panel-heading" align="left">
											<s:text name="Payment Information" />
											<div class="pullRight">
												<label>Mode Of Payment&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;</label>Cheque
											</div>
										</div>
										<div class="panel-body">
											<div class="row">
												<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
													<div class="text">Cheque Number<font color="red">*</font></div>
													<div class="tbox">
														<s:textfield  name="chequeNo" disabled="true" class="inputBox tooltipContent" />
													</div>
												</div>
												<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
													<div class="text">Cheque Date<font color="red">*</font></div>
													<div class="tbox">
														<s:textfield  name="chequeDate" disabled="true" class="inputBox tooltipContent" />
													</div>
												</div>
												<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
													<div class="text">Cheque Amount<font color="red">*</font></div>
													<div class="tbox">
														<s:textfield  name="chequeAmount" disabled="true" class="inputBox tooltipContent" />
													</div>
												</div>
												<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
													<div class="text">Cheque Bank<font color="red">*</font></div>
													<div class="tbox">
														<s:select name="bankName" id="bankName" list="bankNamelist" listKey="CODE" listValue="CODEDESC"  headerKey="" headerValue="---Select---"  cssClass="inputBoxS tooltipContent" data-content="Deposit Bank" disabled="true"  ></s:select>
													</div>
												</div>
												<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
													<div class="text">MICR Code<font color="red">*</font></div>
													<div class="tbox">
														<s:textfield  name="micrCode" disabled="true" class="inputBox tooltipContent" />
													</div>
												</div>
											</div>
										</div>
									</div>
								</s:if>
								
									<br class="clear" />
									<div class="panel panel-primary">
										<div class="panel-heading" align="left">
											<s:text name="Confirmation" />
										</div>
										<div class="panel-body">
											<div class="row">
												<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
													<label>Remarks</label>
													<s:textarea name="remarks" id="remarks"  cssClass="form-control" maxlength="150"/>
												</div>
												<!--<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
													<label><s:text name="Surveyour" /></label>
														<s:select name="applicapleLoginId" list="approverLoginList" listKey="LOGIN_ID" listValue="USERNAME" id="applicapleLoginId"  cssClass="inputBoxS" />
												</div>-->
												<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4"><br/>
													<label>Status<font color="red">*&nbsp;:</font></label>
														<s:radio list="#{'Y':'Accepted','R':'Rejected'}" name="status" id="status" />
												</div>
											</div>
										</div>
									</div>
								</div>
							<br class="clear" />
							<div class="row">
								<div class="col-xs-12 col-sm-12 col-md-12" align="center">
									<input type="button" class="btn btn-sm btn-danger" value="Back" onclick="funBack();" />
									<input type="button" class="btn btn-sm btn-success" value="Submit" onclick="funSubmit();"> &nbsp;&nbsp;&nbsp;
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		</s:form>
		<script type="text/javascript" src="<%=request.getContextPath()%>/bootstrap/js/jquery-1.9.1.min.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/bootstrap/js/bootstrap.min.js"></script>	
		<script type="text/javascript">
	
			function funSubmit(){
				document.updateCashRenewal.action='updateCashAdminRenewal.action';
				document.updateCashRenewal.submit();
				}
					
			function funBack(){
				document.updateCashRenewal.action='cashListAdminRenewal.action?renewalStatus=CCP';
				document.updateCashRenewal.submit();
			}
		</script>
	</body>
</html>