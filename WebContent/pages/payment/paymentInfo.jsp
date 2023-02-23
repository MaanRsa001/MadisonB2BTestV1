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
		</script>
	</head>
	<body>
		<s:set name="sPayAmount" value=""/>
		<div class="row">
			<s:if test='!"admin".equalsIgnoreCase(userType) && !"creditcontroller".equalsIgnoreCase(userType) && !"surveyor".equalsIgnoreCase(userType) && !"underwriter".equalsIgnoreCase(userType)'>
				<div class="row" id="policyGeneration">
	        		<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
	        			<div class="panel panel-primary" id="generatePolicyDiv">
		        			<div class="panel-heading txtB">
								<s:text name="motor.generatepolicy" />
		        			</div>
		        			<div class="panel-body">
		        				<div id="policyGeneration">
		        					<div class="row">
		        						<div style="width: 5%; float: left;" align="center">
			        						<s:checkbox name="quoteEmailYN" fieldValue="Y" id="quoteEmailYN" />
			        					</div>
			        					<div style="width: 95%; float: left;">
			        						<s:text name="label.email.quote"></s:text>
			        					</div>
			        					<br class="clear" />
			        				</div>
			        				<div class="row">
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
	       							<div class="row" id="installmentDiv">
	       								<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
	       									<div class="col-xs-6">
	        									<div class="text"><s:text name="label.installment"/></div>
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
	        </s:if>
	        <div class="row" id="modeOfPay">
				<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
					<div class="panel panel-primary" <s:if test='"admin".equalsIgnoreCase(userType) || "creditcontroller".equalsIgnoreCase(userType) || "surveyor".equalsIgnoreCase(userType) || "underwriter".equalsIgnoreCase(userType)'> style=" border-style:none;"  </s:if>>
						<s:if test='"admin".equalsIgnoreCase(userType) || "creditcontroller".equalsIgnoreCase(userType) || "surveyor".equalsIgnoreCase(userType) || "underwriter".equalsIgnoreCase(userType)'>	
							<s:hidden name="modeOfPayment"></s:hidden>
						</s:if>
						<s:else>
							<div class="panel-heading">
								<div class="row">
									<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
										<s:text name="payment.modeOfPayment" />
									</div>
									<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4" style="color:black">
										<s:select name="modeOfPayment" id="modeOfPayment" headerKey="" headerValue="--Select--" list="modeOfPaymentList" listKey="CODE" listValue="CODEDESC"  cssClass="inputBoxS "   disabled="false" onchange="getPaymentModeDetails(this.value);" />
									</div>
									<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
										<s:if test='#session.product_id=="33"'>
											<s:text name="payment.total.premium" />&nbsp[<s:property value="#session.BrokerDetails.CurrencyAbb"/>]&nbsp;:&nbsp;<s:property value="totalPremium"/>
											<s:set name="sPayAmount" value="%{totalPremium}"/>
										</s:if>
										<s:elseif test='#session.product_id=="30"'>
											<s:text name="payment.total.premium" />&nbsp[<s:property value="#session.BrokerDetails.CurrencyAbb"/>]&nbsp;:&nbsp;<s:property value="finalPremium"/>
											<s:set name="sPayAmount" value="%{finalPremium}"/>
										</s:elseif>
										<s:elseif test='#session.product_id=="65"'>
											<s:text name="payment.total.premium" />&nbsp[<s:property value="currencyType"/>]&nbsp;:&nbsp;<s:property value="#totPremium"/>
											<s:set name="sPayAmount" value="%{#totPremium}"/>									
										</s:elseif>
									</div>
								</div>
						    </div>
						</s:else>
				    <div class="panel-body">
				    	<s:if test='"admin".equalsIgnoreCase(userType) || "creditcontroller".equalsIgnoreCase(userType) || "surveyor".equalsIgnoreCase(userType) || "underwriter".equalsIgnoreCase(userType)'>	
					    </s:if>
					    <s:else>
					    	<div id="bankDetailsDiv" class="panel panel-primary" style="display:none;">
					    		<div class="panel-heading">
									<s:text name="bank.info.payment" />							 
								</div>
								<div class="panel-body" id="bankInfoAjx">
								 	<div class="row">	 
										<s:set var="info" value="bankInformAjax" ></s:set>	 
										<div class="textfield33V">
											<div class="textV"><s:text name="label.accountName" /></div>
											<div class="tboxV">
												<s:property value="%{#info.ACCOUNT_HOLDER}"/>
											</div>
										</div>
										<div class="textfield33V">
											<div class="textV"><s:text name="label.bank" /></div>
											<div class="tboxV">
												<s:property value="%{#info.BANK_NAME}"/>
											</div>
										</div>
										<div class="textfield33V">
											<div class="textV"><s:text name="label.accountNumber" /></div>
											<div class="tboxV">
												<s:property value="%{#info.ACCOUNT_NUMBER}"/>
											</div>
										</div>
										<div class="textfield33V">
											<div class="textV"><s:text name="label.branch" /></div>
											<div class="tboxV">
												<s:property value="%{#info.BRANCH_NAME}"/>
											</div>
										</div>
										<div class="textfield33V">
											<div class="textV"><s:text name="label.currency" /></div>
											<div class="tboxV">
												<s:property value="%{#info.CURRENCY_TYPE}"/>
											</div>
										</div>
										<div class="textfield33V">
											<div class="textV"><s:text name="Branch Code" /></div>
											<div class="tboxV">
												<s:property value="%{#info.BRANCH_CODE}"/>
											</div>
										</div>		
										<div class="textfield33V">
											<div class="textV"><s:text name="label.swiftCode" /></div>
											<div class="tboxV">
												<s:property value="%{#info.SWIFT_CODE}"/>
											</div>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-xs-12"  style="text-align:center;">
										<font color="red">IMPORTANT: Please provide the quotation number <s:property value="quoteNo" /> to the bank teller when making the deposit.</font>	
									</div>
								</div>
					    	</div>
					    </s:else>
						<div class="panel panel-primary" id="cheque" style="display:none;">
							<div class="panel-heading">
								<s:if test='"admin".equalsIgnoreCase(userType) || "creditcontroller".equalsIgnoreCase(userType) || "surveyor".equalsIgnoreCase(userType) || "underwriter".equalsIgnoreCase(userType)'>	
									<s:text name="label.payment"/>
									<s:if test='"ccPending".equalsIgnoreCase(reqForm) || "ccYStatus".equalsIgnoreCase(reqForm) || "ccNStatus".equalsIgnoreCase(reqForm)'>
										<div class="pullRight">
											<label><s:text name="payment.modeOfPayment" />&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;</label><s:property value="paymentType" />
										</div>
									</s:if>
								</s:if>
								<s:else>
									<s:text name="payment.cheque.chequeDetails" />
								</s:else>							 
							</div>
							<div class="panel-body">
								<div class="row">
									<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
										<div class="text"><s:text name="payment.cheque.No" /><font color="red">*</font></div>
										<div class="tbox">
											<s:textfield name="chequeNo" id="chequeNo"  cssClass="inputBox "   onkeypress="checkNumbers(this);" disabled="#disable2" maxlength="10" />
										</div>
									</div>
									<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
										<div class="text"><s:text name="payment.cheque.Date" /><font color="red">*</font></div>
										<div class="tbox">
											<s:textfield name="chequeDate" id="chequeDate" cssClass="inputBox datePicker "   />
										</div>
									</div>
									<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
										<div class="text"><s:text name="payment.cheque.Amount" /><font color="red">*</font></div>
										<div class="tbox">
											<s:textfield name="chequeAmount" id="chequeAmount"  cssClass="inputBox "  disabled="#disable2" maxlength="20" onkeyup="checkDecimals10_2(this);" value='%{(userType!="admin" && userType!="creditcontroller" && userType!="surveyor" && userType!="underwriter" && (chequeAmount==null || "".equals(chequeAmount)))?#sPayAmount:chequeAmount}'/>
										</div>
									</div>
									<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
										<div class="text"><s:text name="payment.cheque.bankName" /><font color="red">*</font></div>
										<div class="tbox">
											<s:select name="bankName" id="bankName" list="bankNamelist" listKey="CODE" listValue="CODEDESC" headerKey="" headerValue="---Select---" cssClass="inputBoxS "  disabled="#disable1"/>
										</div>
									</div>
									<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
										<div class="text"><s:text name="MICR Code" /><font color="red">*</font></div>
										<div class="tbox">
											<s:textfield name="micrCode" id="micrCode"  cssClass="inputBox "  onkeyup="checkNumbers(this);" disabled="#disable2" maxlength="9" />
										</div>
									</div>
								</div>
								<br class="clear" />
									<s:if test='"ccPending".equalsIgnoreCase(reqForm)'>
										<div align="center" >
											<input id="change" type="button" onclick="enable(mode);" class="btn btn-sm btn-success" value="Edit"/>
										</div>
									</s:if>
							</div>
						</div>
						
						<div class="panel panel-primary" id="cash" style="display:none;">
							<div class="panel-heading">
								<s:if test='"admin".equalsIgnoreCase(userType) || "creditcontroller".equalsIgnoreCase(userType) || "surveyor".equalsIgnoreCase(userType) || "underwriter".equalsIgnoreCase(userType)'>	
									<s:text name="label.payment"/>
									<s:if test='"ccPending".equalsIgnoreCase(reqForm) || "ccYStatus".equalsIgnoreCase(reqForm) || "ccNStatus".equalsIgnoreCase(reqForm)'>
										<div class="pullRight">
											<label><s:text name="payment.modeOfPayment" />&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;</label><s:property value="paymentType" />
										</div>
									</s:if>
								</s:if>
								<s:else>
									<s:text name="payment.cash.Details" />
								</s:else>	
							</div>
							<div class="panel-body">
								<div class="row">
									<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
										<div class="text"><s:text name="payment.cash.depositBank" /> <font color="red">*</font></div>
										<div class="tbox">
											<s:select name="cashDepositBank" id="cashDepositBank" list="bankNamelist" listKey="CODE" listValue="CODEDESC" value="5"  headerKey="" headerValue="---Select---"  cssClass="inputBoxS "  disabled="true"  ></s:select>
										</div>
									</div>
									<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
										<div class="text"><s:label key="payment.cash.premiumAmount" /><font color="red">*</font></div>
										<div class="tbox">
											<s:textfield name="cashAmount" id="cashAmount"  maxlength="14" onkeyup="checkDecimals10_2(this);" cssClass="inputBox "  disabled="#disable2" value='%{(userType!="admin" && userType!="creditcontroller" && userType!="surveyor" && userType!="underwriter" && (cashAmount==null || "".equals(cashAmount)))?#sPayAmount:cashAmount}'/>
										</div>
									</div>
									<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
										<div class="text"><s:text name="payment.cash.challanNumber" /></div>
										<div class="tbox">
											<s:textfield name="cashChellanNo" id="cashChellanNo"  maxlength="20" cssClass="inputBox "   disabled="#disable2"/>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
										<div class="text"><s:text name="payment.cash.instrumentDate" /> <font color="red">*</font></div>
										<div class="tbox">
											<s:textfield id="cashInstrumentDate" name="cashInstrumentDate" cssClass="inputBox datepicker" readonly="true" disabled="#disable2" />
										</div>
									</div>
								</div>
								<br class="clear" />
								<s:if test='"ccPending".equalsIgnoreCase(reqForm)'>
									<div align="center" >
										<input id="change" type="button" onclick="enable(mode);" class="btn btn-sm btn-success" value="Edit"/>
									</div>
								</s:if>
						</div>
					</div>
					<div class="panel panel-primary" id="net" style="display:none;">
							<div class="panel-heading">
								<s:if test='"admin".equalsIgnoreCase(userType) || "creditcontroller".equalsIgnoreCase(userType) || "surveyor".equalsIgnoreCase(userType) || "underwriter".equalsIgnoreCase(userType)'>
									<s:text name="label.payment"/>
									<s:if test='"ccPending".equalsIgnoreCase(reqForm) || "ccYStatus".equalsIgnoreCase(reqForm) || "ccNStatus".equalsIgnoreCase(reqForm)'>
										<div class="pullRight">
											<label><s:text name="payment.modeOfPayment" />&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;</label><s:property value="paymentType" />
										</div>
									</s:if>
								</s:if>
								<s:else>
									<div class="pullRight">
										<s:if test='#session.product_id=="33"'>
											<s:text name="payment.total.premium" />&nbsp[<s:property value="#session.BrokerDetails.CurrencyAbb"/>]&nbsp;:&nbsp;<s:property value="totalPremium"/>
										</s:if>
										<s:elseif test='#session.product_id=="30"'>
											<s:text name="payment.total.premium" />&nbsp[<s:property value="#session.BrokerDetails.CurrencyAbb"/>]&nbsp;:&nbsp;<s:property value="finalPremium"/>
											<s:hidden name="finalPremium"></s:hidden>
										</s:elseif>
										<s:elseif test='#session.product_id=="65"'>
											<s:text name="payment.total.premium" />&nbsp[<s:property value="currencyType"/>]&nbsp;:&nbsp;<s:property value="#totPremium"/>									
										</s:elseif>
									</div>
								</s:else>
							</div>
							<s:if test='"ccPending".equalsIgnoreCase(reqForm)'>
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
			  									<label><s:text name="label.res.status"/>&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;</label><s:property value="resStatus" />
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
									<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
										<div class="">
			  									<label><s:text name="label.masked.card"/>&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;</label><s:property value="maskedCard" />
										</div>
									</div>
			        			</div>
			        		</s:if>
						</div>
						<div class="panel panel-primary" id="credit">
							<div class="panel-body">
								<font color="red">“Please do not give out your card details to anyone. Madison General Insurance Company Limited will not be liable for any misuse of your card.”</font>
							</div>
						</div>
						<div class="panel panel-primary" id="mtnMobPay">
							<div class="panel-body">
								<div class="row">
									<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
										<div class="text"><s:text name="payment.mtnMobileNo" /><font color="red">*</font></div>
										<div class="tbox">
											<s:textfield name="mtnMobileNo" id="mtnMobileNo"  cssClass="inputBox tooltipContent" data-content="Please Provide 10 digit Mobile Number using for MTN Payment."  onkeypress="checkNumbers(this);" disabled="#disable2" maxlength="10" />
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="panel panel-primary" id="airtelMobPay">
							<div class="panel-body">
								<div class="row">
									<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
										<div class="text"><s:text name="payment.airtelMobileNo" /><font color="red">*</font></div>
										<div class="tbox">
											<s:textfield name="airtelMoneyNumber" id="airtelMoneyNumber"  cssClass="inputBox tooltipContent" data-content="Please Provide 9 digit Airtel Money Number using for Airtel Payment"  onkeypress="checkNumbers(this);" disabled="#disable2" maxlength="9" />
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
	</body>
<script type="text/javascript">
	function enable(){
			document.getElementById("change").value= "Edit";
		 	$('#modeOfPay input').removeAttr('disabled');
		 	$('#modeOfPay select').removeAttr('disabled');
		 	$('#mode').attr("value", 'editPayment');
			}
	<s:if test='"Y".equalsIgnoreCase(generatePolicyYN) || "viewPayment".equalsIgnoreCase(mode)'>
		getPaymentModeDetails('<s:property value="modeOfPayment"/>');
	</s:if>
		function getPaymentModeDetails(val) {
			try{
				hidePaymentDetails();
				if(val=='1') {
					callBankInfo(val);
					$("#bankDetailsDiv").show();
					$("#cash").show();
					if('<s:property value="userType"/>'!="admin" && '<s:property value="userType"/>'!="creditcontroller" && '<s:property value="userType"/>'!="surveyor" && '<s:property value="userType"/>'!="underwriter") {
						if(document.getElementById("installmentYNY").checked){
							$('#cashAmount').val('<s:property value="#insIntialAmount"/>');
						}else{
							$('#cashAmount').val('<s:property value="#sPayAmount"/>');
						}
						}
					//callBankInfo(val);
					//$("#bankDetailsDiv").show();
				} else if(val=='2') {
					callBankInfo(val); 
					$("#bankDetailsDiv").show();
					$("#cheque").show();
					if('<s:property value="userType"/>'!="admin" && '<s:property value="userType"/>'!="creditcontroller" && '<s:property value="userType"/>'!="surveyor" && '<s:property value="userType"/>'!="underwriter") {
						if(document.getElementById("installmentYNY").checked){
							$('#chequeAmount').val('<s:property value="#insIntialAmount"/>');
						}else{
							$('#chequeAmount').val('<s:property value="#sPayAmount"/>');
						}
					}
					//callBankInfo(val); 
					//$("#bankDetailsDiv").show();
				}else if(val=='6'){
					$("#credit").show();
				}else if(val=='101'){
					document.getElementById('mtnMobPay').style.display = 'block';
				}else if(val=='102'){
					document.getElementById('airtelMobPay').style.display = 'block';
				}
			}catch(err){
				console.log(err);
			}
			   
		}

		function hidePaymentDetails() {
			try{
			$("#cheque").hide();
			$("#cash").hide();
			$("#net").hide();
			$("#bankDetailsDiv").hide();
			$("#credit").hide();
			document.getElementById('mtnMobPay').style.display = 'none';
			$("#mtnMobPay").hide();
			document.getElementById('airtelMobPay').style.display = 'none';
			$("#airtelMobPay").hide();
			if('<s:property value="userType"/>'!="admin" && '<s:property value="userType"/>'!="creditcontroller" && '<s:property value="userType"/>'!="surveyor" && '<s:property value="userType"/>'!="underwriter") {
				if($('#modeOfPayment').val() !='2') {
					$('#cheque input').val('');
				} if($('#modeOfPayment').val() !='1') {
					$('#cash input').val('');
				}if($('#modeOfPayment').val() !='101') {
					document.getElementById('mtnMobileNo').value = '';
				}if($('#modeOfPayment').val() !='102') {
					document.getElementById('airtelMoneyNumber').value = '';
				}
			}
			}catch(err){
				console.log(err);
			}
				
		}

		function callBankInfo(val){
			var currencyId='';
			var quoteNo = '<s:property value="quoteNo"/>'
				
			if('<s:property value="currencyType"/>'=='USD'){
				currencyId='<s:property value="currencyType"/>';
			}else{
				currencyId='<s:property value="#session.BrokerDetails.CurrencyAbb"/>';
			}
			postRequest('${pageContext.request.contextPath}/bankInfoAjaxMotor.action?modeOfPay='+val+'&currencyType='+currencyId+'&quoteNo='+quoteNo, 'bankInfoAjx');	
		}

		/*function callInsAmount(val){
			var currencyId='';
			if('<s:property value="currencyType"/>'=='' && '<s:property value="currencyType"/>'==null){
				currencyId='<s:property value="currencyType"/>';
			}else{
				currencyId='<s:property value="#session.BrokerDetails.CurrencyAbb"/>';
			}
			postRequest('${pageContext.request.contextPath}/bankInfoAjaxMotor.action?modeOfPay='+val+'&currencyType='+currencyId, 'bankInfoAjx');	
		}*/
		
		 function onlyNos(e, t) {
	            try {
	                if (window.event) {
	                    var charCode = window.event.keyCode;
	                }
	                else if (e) {
	                    var charCode = e.which;
	                }
	                else { return true; }
	                if (charCode > 31 && (charCode < 48 || charCode > 57)) {
	                    return false;
	                }
	                return true;
	            }
	            catch (err) {
	                alert(err.Description);
	            }
	        }
		 function togglePayment(type) {
			if(type=="GENERATE_POLICY" || type=="INSTALLMENT_CALC") {
				if (document.getElementById("generatePolicyYN").checked) {
					document.getElementById('quoteEmailYN').checked=false;
					document.getElementById("quoteEmailYN").disabled=true;
					document.getElementById("installmentDiv").style.display = "";
					if (document.getElementById("installmentYNY").checked) {
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
		function installmenDetailsAjax() {
			var id = 'installmentCalcAjax';
			var val = '?quoteNo=<s:property value="quoteNo"/>'
					+ '&applicationNo=<s:property value="applicationNo"/>';
			postRequest('${pageContext.request.contextPath}/'+id+'Payment.action'+val, id);
		}

		function installmenDelete() {
			var id = 'N';
			var quoteNo = '<s:property value="quoteNo"/>'
			postRequest('${pageContext.request.contextPath}/deleteinstallmentMotor.action?quoteNo='+quoteNo+'&installmentYN='+id);
		}
		
		function viewQuote(val)
		{
			var URL ='${pageContext.request.contextPath}/QuotePrint.pdfSchedule?quote_no='+val+'&reqFrom=QuotePrint';
			var windowName = "QuotatiionPrint";
			var width  = screen.availWidth;
			var height = screen.availHeight;
			var bwidth = window.innerWidth;
			var bwidth1 = document.body.clientWidth;
			if(bwidth <= 768) {
				var w = bwidth1;
				var h =	500;
			} else {
				var w = 750;
				var h =	500;
			}
			var features =
				'width='          + w +
				',height='		  + h +
				',left='		  + ((width - w - 10) * .5)  +
				',top='			  + ((height - h - 30) * .5) +
				',directories=no' +
				',location=no'	  +
				',menubar=no'	  +
				',scrollbars=yes' +
				',status=no'	  +
				',toolbar=no'	  +
				',resizable=false';
			var strOpen = window.open (URL, windowName, features);
			strOpen.focus();
			return false;
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