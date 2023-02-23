<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<script language="JavaScript">
				function stopRKey(evt) { 
				  var evt = (evt) ? evt : ((event) ? event : null); 
				  var node = (evt.target) ? evt.target : ((evt.srcElement) ? evt.srcElement : null); 
				  if ((evt.keyCode == 13) && (node.type=="text"))  {return false;} 
				}
				document.onkeypress = stopRKey; 
				
				
				$(function() {
					try {
						$('#cashInstrumentDate').datepicker({
							todayHighlight: true,
							format: "dd/mm/yyyy"
						}).on('changeDate', function(e){
						    $(this).datepicker('hide');
						});
						$('#chequeDate').datepicker({
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
	<s:set name="sPayAmount" value=""/>
	<s:form id="paymentRenewal" name="paymentRenewal" action="" theme="simple">
		<s:set var="modeOfPaymentList" value="%{modeOfPaymentList}"/>
		<s:set var="totPremium" value="%{premPay}"/>
			<s:if test='!"admin".equalsIgnoreCase(userType) && !"creditcontroller".equalsIgnoreCase(userType) && !"surveyor".equalsIgnoreCase(userType) && !"underwriter".equalsIgnoreCase(userType)'>
			<div class="container vehDtl">
			<div class="row">
				<div align="left">
					<s:if test="hasActionErrors()">
						<font color="red"><s:actionerror/></font>
					</s:if>
					<font color="green"><s:actionmessage cssStyle="list-style: none;" /></font>
				</div>
			</div>
		  		<div class="Card_Parent ">
		            <div class="card card-5">
						<div class="row" id="policyGeneration">
			        		<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
			        			<div class="panel " id="generatePolicyDiv">
				        			<div class="panel-heading txtB">
										<h3><s:text name="motor.generate.renewalpolicy" /></h3><hr>
				        			</div>
				        			<div class="panel-body">
				        				<div id="policyGeneration">
				        					<div class="row" style="display:none" >
				        						<div style="width: 5%; float: left;" align="center">
					        						<s:checkbox name="quoteEmailYN" fieldValue="Y" id="quoteEmailYN" />
					        					</div>
					        					<div style="width: 95%; float: left;">
					        						<s:text name="label.email.quote"></s:text>
					        					</div>
					        					<br class="clear" />
					        				</div>
					        				<div class="row">
					        					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
						        					<div style="width: 5%; float: left;" align="center">
						        						<s:checkbox name="generatePolicyYN" fieldValue="Y" id="generatePolicyYN" onclick="togglePayment('GENERATE_POLICY');"/>
						        					</div>
					        						<div style="width: 95%; float: left;">
					        							<s:if test="#session.product_id == '65'">		        							
					        								<s:text name="label.declarationStatementMotor"/>
					        							</s:if>
				        								<s:elseif test="#session.product_id == '30'">
				        									<s:text name="label.declarationStatementHome"/>
				        								</s:elseif>
				        								<s:else>
				        									<s:text name="label.declarationStatement"/>
				        								</s:else>
							       						<br/><s:text name="label.declarationStatement1"/>
						        						&nbsp;&nbsp;&nbsp;<br/>
						        					</div>
						        					<br class="clear" />
					        					</div>
					        				</div>
			       							<div class="row" id="installmentDiv" style="display:none">
			       								<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
			       									<div class="col-xs-6">
		
			        								</div>
			        								<div class="col-xs-6">
			        									<div class="tbox">
															<s:radio list="#{'Y':'Yes','N':'No'}" name="installmentYN" id="installmentYN" value='%{"Y".equals(installmentYN)?"Y":"N"}' onchange="togglePayment('INSTALLMENT_CALC');" />
														</div>
			        								</div>
			        							</div>
			       								<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4" id="installmentCalcDiv">
			       									<button type="button" class="btn btn-sm btn-primary" data-toggle="modal" data-refresh="true" data-target="#installmentDetailsModal" data-backdrop="static" data-keyboard="false" onclick="installmenDetailsAjax();"> Calculate </button>
			        								<div id="installmentDetailsModal" class="modal fade" role="dialog">
			        									<div class="modal-dialog" id="installmentCalcAjax"></div>
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
	        	</div>
	        </s:if>
	        <br>
	        <br>
	        <div class="row" id="modeOfPay">
	        	<s:include value="/pages/payment/paymentDetails.jsp"></s:include>
	        </div>
		<div class="row">
			<div class="col-xs-12 col-sm-12 col-md-12" align="center">
				<input type="button" class="btn  btn-danger" value="Back" onclick="funBack();" /> &nbsp;&nbsp;&nbsp;
				<!-- <input type="button" class="btn  btn-success" value="Proceed" onclick="SubmitNew();"> -->
			</div>
<!--		<div class="col-xs-6" align="center">-->
<!--				<input types="button" class="btn btn-sm btn-danger" value="Back" onclick="back();"> &nbsp;&nbsp;&nbsp;-->
<!--		</div>-->
<!--		<div class="col-xs-6" align="center">-->
<!--				<input types="button" class="btn btn-sm btn-success" value="Proceed" onclick="SubmitNew();"> &nbsp;&nbsp;&nbsp;-->
<!--		</div>-->
		</div>
	
	<s:hidden name="policyNo"/>
	<s:hidden name="mobileNo"/>
	<s:hidden name="currency"/>
	<s:hidden name="custInsuredName"/>
	<s:hidden name="renewRefNo"/>
	<s:hidden name="myTotal"/>
	<s:hidden name="tempMobileNo"/>
	<s:hidden name="tempEmailId"/>
	<s:hidden name="tempPassportNo"/>
	<s:hidden name="vehicles"/>
	<s:hidden name="checkboxValue"/>
	<s:hidden name="riskId"/>
	<s:hidden name="qrMode" id="qrMode"></s:hidden>
	<s:hidden name="premPay"/>
	<s:hidden name="premTax"/>
	<s:hidden name="actionType" />
	</s:form>
	</body>
<script type="text/javascript">
	
	function SubmitNew(){
		document.paymentRenewal.action='genPaymentQuickRenewal.action';
		document.paymentRenewal.submit();
	}
	
	function makePayment(modeOfPay){
		document.getElementById('modeOfPayment').value=modeOfPay;
		document.paymentRenewal.action='genPaymentQuickRenewal.action';
		document.paymentRenewal.submit();
	}

	function funBack(){
		document.paymentRenewal.action='verifyOtpQuickRenewal.action?mode=back';
		document.paymentRenewal.submit();
	}
	function togglePayment(type) {
		if(type=="GENERATE_POLICY" || type=="INSTALLMENT_CALC") {
			if (document.getElementById("generatePolicyYN").checked) {
					document.getElementById('quoteEmailYN').checked=false;
					document.getElementById("quoteEmailYN").disabled=true;
			if (false) {
					document.getElementById("installmentCalcDiv").style.display = "";
					document.getElementById("modeOfPay").style.display = "none";
					document.getElementById('modeOfPayment').disabled=false;
					$('#modeOfPayment').val('');
					hidePaymentDetails();
				} else {
					document.getElementById('modeOfPayment').disabled=false;
					document.getElementById("installmentCalcDiv").style.display = "none";
					document.getElementById("modeOfPay").style.display = "block";
					installmenDelete();
					if('<s:property value="modeOfPayment"/>'==null ||'<s:property value="modeOfPayment"/>'=="" )
					hidePaymentDetails();
				}
			} else {
				document.getElementById('modeOfPayment').disabled=false;
				document.getElementById("quoteEmailYN").disabled=false;
				document.getElementById('installmentYNN').checked=true;
				document.getElementById('installmentYNY').checked=false;
				document.getElementById("installmentDiv").style.display = "none";
				document.getElementById("installmentCalcDiv").style.display = "none";
				document.getElementById("modeOfPay").style.display = "none";
				$('#modeOfPayment').val('');
				hidePaymentDetails();
			}
		} else if(type=="INSTALLMENT_SUBMIT") {
			if(document.getElementById('installmentAgreeYN').checked==true) {
				document.getElementById("modeOfPay").style.display = "block";
				//$('#modeOfPayment').val('6');
				//document.getElementById('modeOfPayment').disabled=true;
				$('#installmentDetailsModal').modal('toggle');
				hidePaymentDetails();
				//$("#credit").show();
			} else {
				$('#modalErrorSpan').html('Please Agree the Installement');
				hidePaymentDetails();
			}
		} else if(type=="INSTALLMENT_CANCEL") {
			document.getElementById("installmentCalcDiv").style.display = "none";
			document.getElementById('installmentYNN').checked=true;
			document.getElementById('installmentYNY').checked=false;
			document.getElementById("modeOfPay").style.display = "block";
			document.getElementById('modeOfPayment').disabled=false;
			installmenDelete();
			hidePaymentDetails();
			$('#installmentDetailsModal').modal('toggle');
		}
	}
	</script>
	<s:if test='!"admin".equalsIgnoreCase(userType) && !"creditcontroller".equalsIgnoreCase(userType) && !"surveyor".equalsIgnoreCase(userType) && !"underwriter".equalsIgnoreCase(userType)'>
		<SCRIPT type="text/javascript">
		if('<s:property value="quoteEmailYN"/>' == "Y") {
			document.getElementById("quoteEmailYN").checked = true;
		} else {
			document.getElementById("quoteEmailYN").checked = false;
		}
		if('<s:property value="generatePolicyYN"/>' == "Y") {
			document.getElementById("generatePolicyYN").checked = true;
		} else {
			document.getElementById("generatePolicyYN").checked = false;
		}
		togglePayment('GENERATE_POLICY');
		</SCRIPT>
	</s:if>
</html>	