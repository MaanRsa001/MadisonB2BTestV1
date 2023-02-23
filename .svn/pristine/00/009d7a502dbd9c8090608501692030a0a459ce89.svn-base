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
	<div class="container PolicyPage">
		<div class="Card_Parent ModeOfPayment" id="modeOfPay">
            	<div class="card card-4 ">
	               <!--  <div class="row" > -->
	                	<div class="panel panel-primary" <s:if test='"admin".equalsIgnoreCase(userType) || "creditcontroller".equalsIgnoreCase(userType) || "surveyor".equalsIgnoreCase(userType) || "underwriter".equalsIgnoreCase(userType)'> style=" border-style:none;"  </s:if>>
	                		<s:if test='"admin".equalsIgnoreCase(userType) || "creditcontroller".equalsIgnoreCase(userType) || "surveyor".equalsIgnoreCase(userType) || "underwriter".equalsIgnoreCase(userType)'>	
							<s:hidden name="modeOfPayment"></s:hidden>
						</s:if>
						<s:else>
						<div class="row">
							<div class="col-md-6">
						 		<h3><s:text name="payment.modeOfPayment" /></h3>
						 	</div>
						 	<div class="col-md-6">
							 	<s:if test='#session.product_id=="33"'>
									<s:text name="payment.total.premium" />&nbsp[<s:property value="#session.BrokerDetails.CurrencyAbb"/>]&nbsp;:&nbsp;<s:property value="totalPremium"/>
									<s:set name="sPayAmount" value="%{totalPremium}"/>
								</s:if>
								<s:elseif test='#session.product_id=="30"'>
									<s:text name="payment.total.premium" />&nbsp[<s:property value="#session.BrokerDetails.CurrencyAbb"/>]&nbsp;:&nbsp;<s:property value="finalPremium"/>
									<s:set name="sPayAmount" value="%{finalPremium}"/>
								</s:elseif>
								<s:elseif test='#session.product_id=="65"'>
									<s:if test='insIntialAmount != null && !"".equals(insIntialAmount)'>
										<s:text name="Initial Payment" />&nbsp[<s:property value="currencyType"/>]&nbsp;:&nbsp;<b><s:property value="insIntialAmount"/></b>
									</s:if>
									<s:if test='isRenewal == "Y" && "Y".equals(isRenewal)'>
										<s:text name="payment.total.premium" />&nbsp[<s:property value="currency"/>]&nbsp;:&nbsp;<b><s:property value="#totPremium"/></b>
									</s:if>
									<s:else>
										<s:text name="payment.total.premium" />&nbsp[<s:property value="currencyType"/>]&nbsp;:&nbsp;<b><s:property value="#totPremium"/></b>
									</s:else>
									<s:set name="sPayAmount" value="%{#totPremium}"/>									
								</s:elseif>
							 </div>
							 
						 </div><hr><br>
							 <%-- <div class="col-md-6">
	                                <div class="input-group mb-3">
	                                    <div class="input-group-prepend">
	                                        <span class="input-group-text border border-right-0">
	                                            <i class="fas fa-address-card"></i>
	                                        </span>
	                                    </div>
	                                    <s:select name="modeOfPayment" id="modeOfPayment" headerKey="" headerValue="--Select--" list="modeOfPaymentList" listKey="CODE" listValue="CODEDESC"  class="form-control border border-left-0"   disabled="false" onchange="getPaymentModeDetails(this.value);" />
	                                </div>
	                            </div> --%>
	                            
                            <div class="row mytap">
                               <div class="col-md-3 p-0">
	                                <ul class="nav nav-tabs flex-column" role="tablist">
										 <s:iterator value="#modeOfPaymentList" var="var" status="status">
											 <s:if test='!"1".equalsIgnoreCase(#var.CODE)'>
											 	<s:if test='"Y".equalsIgnoreCase(isRenewal)'>
											 		<s:if test=' !"9".equalsIgnoreCase(#var.CODE)'>
													 	<li class="nav-item mofPay" id="modeOfPayments">
														    <%-- <a class='nav-link   ${status.index=="0"?" active":""}' data-toggle="tab" href="#pay${var.CODE}" onclick="getPaymentModeDetails('${var.CODE}')">${var.CODEDESC}</a> --%>
														    <a class='nav-link' data-toggle="tab" href="#pay${var.CODE}" onclick="getPaymentModeDetails('${var.CODE}')">${var.CODEDESC}</a>
														 </li>
													 </s:if>
												</s:if>
												<s:else>
												 	<li class="nav-item mofPay" id="modeOfPayments">
													    <%-- <a class='nav-link   ${status.index=="0"?" active":""}' data-toggle="tab" href="#pay${var.CODE}" onclick="getPaymentModeDetails('${var.CODE}')">${var.CODEDESC}</a> --%>
													    <a class='nav-link' data-toggle="tab" href="#pay${var.CODE}" onclick="getPaymentModeDetails('${var.CODE}')">${var.CODEDESC}</a>
													 </li>
												</s:else>
											 </s:if>
										 </s:iterator>
									</ul>
									<s:hidden name="modeOfPayment" id="modeOfPayment"/>
                               </div>
                               <div class="col-md-9 p-0">
									<div class="tab-content p-3">
									  	<%-- <s:iterator value="#modeOfPaymentList" var="var" status="status"> --%>
									  		<%-- <div id="pay${var.CODE}" class='tab-pane container fade ${status.index=="0"?" active show":""}' >
										  		<div class="row">
										  			${var.CODEDESC}
										  		</div>
									  		</div> --%>
									  		<div id="paymentChoose">
									  			<b>Please Choose Any Payment Option to Proceed...</b>
									  		</div>
									  		<div id="bankDetailsDiv">
												<h3><s:text name="bank.info.payment" /></h3><hr>
												<s:set var="info" value="bankInformAjax" ></s:set>								 
												<div id="bankInfoAjxNew">
													 <div class="row">
														<div class="col-md-4 col-4">
															<label class="LabelHeading"><s:text name="label.accountName" /></label>
								
														</div>
														<div class="col-md-8 col-8">
															<label class="labelValues"><s:property value="%{#info.ACCOUNT_HOLDER}"/></label>
														</div>
													</div>
													<div class="row">
														<div class="col-md-4 col-4">
															<label class="LabelHeading"><s:text name="label.bank" /></label>
								
														</div>
														<div class="col-md-8 col-8">
															<label class="labelValues"><s:property value="%{#info.BANK_NAME}"/></label>
														</div>
													</div>
													<div class="row">
														<div class="col-md-4 col-4">
															<label class="LabelHeading"><s:text name="label.accountNumber" /></label>
								
														</div>
														<div class="col-md-8 col-8">
															<label class="labelValues"><s:property value="%{#info.ACCOUNT_NUMBER}"/></label>
														</div>
													</div>
													<div class="row">
														<div class="col-md-4 col-4">
															<label class="LabelHeading"><s:text name="label.branch" /></label>
								
														</div>
														<div class="col-md-8 col-8">
															<label class="labelValues"><s:property value="%{#info.BRANCH_NAME}"/></label>
														</div>
													</div>
													 <div class="row">
														<div class="col-md-4 col-4">
															<label class="LabelHeading"><s:text name="label.currency" /></label>
								
														</div>
														<div class="col-md-8 col-8">
															<label class="labelValues"><s:property value="%{#info.CURRENCY_TYPE}"/></label>
														</div>
													</div>
													 <div class="row">
														<div class="col-md-4 col-4">
															<label class="LabelHeading"><s:text name="Branch Code" /></label>
								
														</div>
														<div class="col-md-8 col-8">
															<label class="labelValues"><s:property value="%{#info.BRANCH_CODE}"/></label>
														</div>
													</div>
													<div class="row">
														<div class="col-md-4 col-4">
															<label class="LabelHeading"><s:text name="label.swiftCode" /></label>
								
														</div>
														<div class="col-md-8 col-8">
															<label class="labelValues"><s:property value="%{#info.SWIFT_CODE}"/></label>
														</div>
													</div>
													
												</div><br>
												<div class="row">
													<div  style="text-align:center;">
														<font color="red">IMPORTANT: Please provide the quotation number <s:property value="quoteNo" /> to the bank teller when making the deposit.</font>	
													</div>
												</div>
											</div>
									  		
									  		<!-- <div id="pay1" class='tab-pane container fade active show' > -->
									  		<div id="pay1" class='tab-pane container fade' >
										  		<div class="row">
													<div class="col-md-12">
														<s:if test='"admin".equalsIgnoreCase(userType) || "creditcontroller".equalsIgnoreCase(userType) || "surveyor".equalsIgnoreCase(userType) || "underwriter".equalsIgnoreCase(userType)'>	
															<s:text name="label.payment"/>
															<s:if test='"ccPending".equalsIgnoreCase(reqForm) || "ccYStatus".equalsIgnoreCase(reqForm) || "ccNStatus".equalsIgnoreCase(reqForm)'>
																<div class="pullRight">
																	<label><s:text name="payment.modeOfPayment" />&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;</label><s:property value="paymentType" />
																</div>
															</s:if>
														</s:if>
														<s:else>
															<h3><s:text name="payment.cash.Details" /></h3>
														</s:else><hr>
														<%-- <div class="row mt-2">
															<div class="col-md-12">
																<label class="labelHeader"><s:text name="payment.cash.depositBank" /> <font color="red">*</font></label>
																<div class="input-group mb-2">
																	<div class="input-group-prepend">
																		<span class="input-group-text border border-right-0"><i class="fas fa-university"></i></span>
																	</div>
																	<!-- <input type="text" class="form-control border border-left-0" placeholder="Deposite Bank"> -->
																	 <s:select name="cashDepositBank" id="cashDepositBank" list="bankNamelist" listKey="CODE" listValue="CODEDESC" value="5"  headerKey="" headerValue="---Select---"  class="form-control border border-left-0" placeholder="Deposite Bank" disabled="true"  ></s:select>
																</div>
															</div>
														</div> --%>
														<div class="row">
															<div class="col-md-12">
																<label class="labelHeader"><s:label key="Cash Collected" /><font color="red">*</font></label>
																<div class="input-group mb-2">
																	<div class="input-group-prepend">
																		<span class="input-group-text border border-right-0"><i class="fas fa-dollar-sign"></i></span>
																	</div>
																	<!-- <input type="text" class="form-control border border-left-0" placeholder="Premium Amount"> -->
																	<s:textfield name="cashAmount" id="cashAmount"  maxlength="14" onkeyup="checkDecimals10_2(this);" class="form-control border border-left-0" placeholder="Premium Amount"  disabled="#disable2" value='%{(userType!="admin" && userType!="creditcontroller" && userType!="surveyor" && userType!="underwriter" && (cashAmount==null || "".equals(cashAmount)))?#sPayAmount:cashAmount}'/>
																</div>
															</div>
														</div>
														<div class="row">
															<div class="col-md-12">
																<label class="labelHeader"><s:text name="payment.cash.challanNumber" /></label>
																<div class="input-group mb-2">
																	<div class="input-group-prepend">
																		<span class="input-group-text border border-right-0"><i class="fas fa-list-ol"></i></span>
																	</div>
																	<!-- <input type="text" class="form-control border border-left-0" placeholder="Reference Number"> -->
																	<s:textfield name="cashChellanNo" id="cashChellanNo"  maxlength="20" class="form-control border border-left-0" placeholder="Reference Number"   disabled="#disable2"/>
																</div>
															</div>
														</div>
														<div class="row">
															<div class="col-md-12">
																<label class="labelHeader"><s:text name="Collected Date" /> <font color="red">*</font></label>
																<div class="input-group mb-2">
																	<div class="input-group-prepend">
																		<span class="input-group-text border border-right-0"><i class="fas fa-calendar-alt"></i></span>
																	</div>
																	<!-- <input type="text" class="form-control border border-left-0" placeholder="Deposite Date"> -->
																	<s:textfield id="cashInstrumentDate" name="cashInstrumentDate" class="form-control border border-left-0 datePicker" placeholder="Deposite Date" readonly="true" disabled="#disable2" />
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
										  		<div class="row">
										  			<div class="col-lg-4 col-md-4 offset-md-5 offset-lg-5 mb-3" >
										  				<input type="button" name="Submit3" class="btn btn-success btn-block" value="Convert To Policy" onclick="this.form.actionType.value='getQuote';disableForm(this.form,false,'');makePayment('1')"/>
													</div>
										  		</div>
									  		</div>
									  		<div id="pay2" class='tab-pane container fade' >
										  		<div class="row">
										  			<div class="col-md-12">
														<s:if test='"admin".equalsIgnoreCase(userType) || "creditcontroller".equalsIgnoreCase(userType) || "surveyor".equalsIgnoreCase(userType) || "underwriter".equalsIgnoreCase(userType)'>	
															<s:text name="label.payment"/>
															<s:if test='"ccPending".equalsIgnoreCase(reqForm) || "ccYStatus".equalsIgnoreCase(reqForm) || "ccNStatus".equalsIgnoreCase(reqForm)'>
																<div class="pullRight">
																	<label><s:text name="payment.modeOfPayment" />&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;</label><s:property value="paymentType" />
																</div>
															</s:if>
														</s:if>
														<s:else>
															<h3><s:text name="payment.cheque.chequeDetails" /></h3>
														</s:else><hr>
														<div class="row mt-2">
															<div class="col-md-12">
																<label class="labelHeader"><s:text name="payment.cheque.No" /><font color="red">*</font></label>
																<div class="input-group mb-2">
																	<div class="input-group-prepend">
																		<span class="input-group-text border border-right-0"><i class="fas fa-list-ol"></i></span>
																	</div>
																	 <s:textfield name="chequeNo" id="chequeNo" class="form-control border border-left-0" placeholder="Cheque Number" onkeypress="checkNumbers(this);" disabled="#disable2" maxlength="10" />
																</div>
															</div>
														</div>
														<div class="row mt-2">
															<div class="col-md-12">
																<label class="labelHeader"><s:text name="payment.cheque.Date" /><font color="red">*</font></label>
																<div class="input-group mb-2">
																	<div class="input-group-prepend">
																		<span class="input-group-text border border-right-0"><i class="fas fa-calendar-alt"></i></span>
																	</div>
																	<s:textfield name="chequeDate" id="chequeDate" class="form-control border border-left-0 datePicker" placeholder="Cheque Date" readonly="true"/>
																</div>
															</div>
														</div>
														<div class="row mt-2">
															<div class="col-md-12">
																<label class="labelHeader"><s:text name="payment.cheque.Amount" /><font color="red">*</font></label>
																<div class="input-group mb-2">
																	<div class="input-group-prepend">
																		<span class="input-group-text border border-right-0"><i class="fas fa-dollar-sign"></i></span>
																	</div>
																	<s:textfield name="chequeAmount" id="chequeAmount"  class="form-control border border-left-0" placeholder="Cheque Amount" disabled="#disable2" maxlength="20" onkeyup="checkDecimals10_2(this);" value='%{(userType!="admin" && userType!="creditcontroller" && userType!="surveyor" && userType!="underwriter" && (chequeAmount==null || "".equals(chequeAmount)))?#sPayAmount:chequeAmount}'/>
																</div>
															</div>
														</div>
														<div class="row mt-2">
															<div class="col-md-12">
																<label class="labelHeader"><s:text name="payment.cheque.bankName" /><font color="red">*</font></label>
																<div class="input-group mb-2">
																	<div class="input-group-prepend">
																		<span class="input-group-text border border-right-0"><i class="fas fa-university"></i></span>
																	</div>
																		<s:select name="bankName" id="bankName" list="bankNamelist" listKey="CODE" listValue="CODEDESC" headerKey="" headerValue="---Cheque Bank---" class="form-control border border-left-0" placeholder="Cheque Bank"  disabled="#disable1"/>
																</div>
															</div>
														</div>
														<div class="row mt-2">
															<div class="col-md-12">
																<label class="labelHeader"><s:text name="MICR Code" /><font color="red">*</font></label>
																<div class="input-group mb-2">
																	<div class="input-group-prepend">
																		<span class="input-group-text border border-right-0"><i class="fas fa-list-ol"></i></span>
																	</div>
																	<s:textfield name="micrCode" id="micrCode"  class="form-control border border-left-0" placeholder="MICR Code" onkeyup="checkNumbers(this);" disabled="#disable2" maxlength="9" />
																</div>
															</div>
														</div>
													</div>
										  		</div>
										  		<div class="row">
										  			<div class="col-lg-4 col-md-4 offset-md-5 offset-lg-5 mb-3">
										  				<input type="button" name="Submit3" class="btn btn-success btn-block" value="Convert To Policy" onclick="this.form.actionType.value='getQuote';disableForm(this.form,false,'');makePayment('2')"/>
													</div>
										  		</div>
									  		</div>
									  		<div id="pay6" class='tab-pane container fade' >
										  		<div class="row">
										  			<div class="col-md-12">
													<h3><s:text name="Credit / Debit Card" /></h3><hr>
														<br><font color="red">“Please do not give out your card details to anyone. Madison General Insurance Company Zambia LTD will not be liable for any misuse of your card.”</font>
													</div>
										  		</div>
										  		<br>
										  		<div class="row">
										  			<div class="col-lg-2 col-md-2 offset-md-5 offset-lg-5 mb-3">
										  				<input type="button" name="Submit3" class="btn btn-success btn-block" value="Proceed" onclick="this.form.actionType.value='getQuote';disableForm(this.form,false,'');makePayment('6')"/>
													</div>
										  		</div>
										  		
									  		</div>
									  		<div id="pay9" class='tab-pane container fade' >
										  		<div class="row">
										  			<div class="col-md-12">
													<h3><s:text name="Credit Note" /></h3><hr>
														<br><font color="red">“Amount will be debited from you credit limit.”</font>
													</div>
										  		</div>
										  		<br>
										  		<div class="row">
										  			<div class="col-lg-4 col-md-4 offset-md-5 offset-lg-5 mb-3">
										  				<input type="button" name="Submit3" class="btn btn-success" value="Convert To Policy" onclick="this.form.actionType.value='getQuote';disableForm(this.form,false,'');makePayment('9')" />
													</div>
										  		</div>
										  		
									  		</div>
									  		<div id="pay101" class='tab-pane container fade' >
									  			<div class="row">
										  			<div class="col-md-12">
														<h3><s:text name="MTN Mobile Money" /></h3><hr>
													</div>
										  		</div>
										  		<br>
										  		<div class="row mt-3">
													<div class="col-md-12">
														<label class="labelHeader"><s:text name="payment.mtnMobileNo" /><font color="red">*</font></label>
														<div class="input-group mb-3">
															<div class="input-group-prepend">
																<span class="input-group-text border border-right-0"><i class="fas fa-mobile"></i></span>
															</div>
															<s:textfield name="mtnMobileNo" id="mtnMobileNo"  cssClass="form-control border border-left-0" placeholder="MTN Mobile No." onkeypress="checkNumbers(this);" disabled="#disable2" maxlength="10" />
														</div>
													</div>
												</div><br>
												<div class="row">
										  			<div class="col-lg-2 col-md-2 offset-md-5 offset-lg-5 mb-3">
										  				<input type="button" name="Submit3" class="btn btn-success btn-block" value="Proceed" onclick="this.form.actionType.value='getQuote';disableForm(this.form,false,'');makePayment('101')"/>
													</div>
										  		</div>
											</div>
											<div id="pay102" class='tab-pane container fade' >
									  			<div class="row">
										  			<div class="col-md-12">
														<h3><s:text name="Airtel Money Payment" /></h3><hr>
													</div>
										  		</div>
										  		<br>
										  		<div class="row mt-3">
													<div class="col-md-12">
														<label class="labelHeader"><s:text name="payment.airtelMobileNo" /><font color="red">*</font></label>
														<div class="input-group mb-3">
															<div class="input-group-prepend">
																<span class="input-group-text border border-right-0"><i class="fas fa-mobile"></i></span>
															</div>
															<s:textfield name="airtelMoneyNumber" id="airtelMoneyNumber"  cssClass="form-control border border-left-0" placeholder="Airtel Money No." onkeypress="checkNumbers(this);" disabled="#disable2" maxlength="9" />
														</div>
													</div>
												</div><br>
												<div class="row">
										  			<div class="col-lg-2 col-md-2 offset-md-5 offset-lg-5 mb-3">
										  				<input type="button" name="Submit3" class="btn btn-success btn-block" value="Proceed" onclick="this.form.actionType.value='getQuote';disableForm(this.form,false,'');makePayment('102')"/>
													</div>
										  		</div>
											</div>
											
									  	<%-- </s:iterator> --%>
									  	
								    	<br>
									</div>
                               </div>
                            </div>
						</s:else>
	                	</div>
	              <!--   </div> -->
                </div>
            </div>
            </div><br>
</body>
<script type="text/javascript">
$(function() {
	try {
		$('.datePicker').datepicker({
			todayHighlight: true,
			format: "dd/mm/yyyy"
		}).on('changeDate', function(e){
		    $(this).datepicker('hide');			    
		});
	} catch(err) {console.error(err);}
});

	function enable(){
			document.getElementById("change").value= "Edit";
		 	$('#modeOfPay input').removeAttr('disabled');
		 	$('#modeOfPay select').removeAttr('disabled');
		 	$('#mode').attr("value", 'editPayment');
			}
	
	<s:if test='"Y".equalsIgnoreCase(generatePolicyYN) || "viewPayment".equalsIgnoreCase(mode) || "Y".equalsIgnoreCase(isRenewal)'>
		getPaymentModeDetails('<s:property value="modeOfPayment"/>');
		enablePay('<s:property value="modeOfPayment"/>');
	</s:if>
	
	function enablePay(val){
		 $('[href="#pay'+val+'"]').tab('show');
	}
		function getPaymentModeDetails(val) {
			//alert(val);
			//document.getElementById('modeOfPayment').value=val;
			hidePaymentDetails();
			if(val=='1') {
				callBankInfo(val);
				//$("#bankDetailsDiv").show();
				$("#cash").show();
				$("#paymentChoose").hide();
				
				//$("#pay1").show();
				
				/*if('<s:property value="userType"/>'!="admin" && '<s:property value="userType"/>'!="creditcontroller" && '<s:property value="userType"/>'!="surveyor" && '<s:property value="userType"/>'!="underwriter") {
					if(document.getElementById("installmentYNY").checked){
						$('#cashAmount').val('<s:property value="#insIntialAmount"/>');
					}else{
						$('#cashAmount').val('<s:property value="#sPayAmount"/>');
					}
					}*/
				
			} else if(val=='2') {
				callBankInfo(val); 
				$("#bankDetailsDiv").show();
				$("#cheque").show();
				$("#paymentChoose").hide();
				/*if('<s:property value="userType"/>'!="admin" && '<s:property value="userType"/>'!="creditcontroller" && '<s:property value="userType"/>'!="surveyor" && '<s:property value="userType"/>'!="underwriter") {
					if(document.getElementById("installmentYNY").checked){
						$('#chequeAmount').val('<s:property value="#insIntialAmount"/>');
					}else{
						$('#chequeAmount').val('<s:property value="#sPayAmount"/>');
					}
				}*/
				//callBankInfo(val); 
				//$("#bankDetailsDiv").show();
			}else if(val=='6'){
				$("#credit").show();
				$("#paymentChoose").hide();
			}
			else if(val=='9'){
				$("#paymentChoose").hide();
			}
			else if(val=='101'){
				$("#paymentChoose").hide();
			}
			else if(val=='102'){
				$("#paymentChoose").hide();
			}
			   
		}

		function hidePaymentDetails() {
			$("#cheque").hide();
			$("#cash").hide();
			$("#net").hide();
			$("#bankDetailsDiv").hide();
			$("#credit").hide();
			if('<s:property value="userType"/>'!="admin" && '<s:property value="userType"/>'!="creditcontroller" && '<s:property value="userType"/>'!="surveyor" && '<s:property value="userType"/>'!="underwriter") {
				if($('#modeOfPayment').val() !='2') {
					$('#cheque input').val('');
				} if($('#modeOfPayment').val() !='1') {
					$('#cash input').val('');
				}
			}
				
		}

		function callBankInfo(val){
			var currencyId='';
			var quoteNo = '<s:property value="quoteNo"/>'
				
			if('<s:property value="currencyType"/>'=='USD'){
				currencyId='<s:property value="currencyType"/>';
				//alert("USD");
			}else{
				currencyId='<s:property value="#session.BrokerDetails.CurrencyAbb"/>';
				//alert(currencyId);
			}
			//postRequest('${pageContext.request.contextPath}/bankInfoAjaxMotor.action?modeOfPay='+val+'&currencyType='+currencyId+'&quoteNo='+quoteNo, 'bankInfoAjx');
			postRequest('${pageContext.request.contextPath}/bankInfoAjaxNewMotor.action?modeOfPay='+val+'&currencyType='+currencyId+'&quoteNo='+quoteNo, 'bankInfoAjxNew');
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
		/*  function togglePayment(type) {
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
		} */
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
		/*if('<s:property value="quoteEmailYN"/>' == "Y") {
			document.getElementById("quoteEmailYN").checked = true;
		} else {
			document.getElementById("quoteEmailYN").checked = false;
		}
		if('<s:property value="generatePolicyYN"/>' == "Y") {
			document.getElementById("generatePolicyYN").checked = true;
		} else {
			document.getElementById("generatePolicyYN").checked = false;
		}
		togglePayment('GENERATE_POLICY');*/
		</SCRIPT>
	</s:if>
</html>	