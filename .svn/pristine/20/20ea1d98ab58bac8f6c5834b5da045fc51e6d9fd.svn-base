<%@ page isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html <s:if test="locale.language == 'ar'">dir="rtl"</s:if> >
<head>
<script type="text/javascript" >
	function stopRKey(evt) { 
		var evt = (evt) ? evt : ((event) ? event : null); 
		var node = (evt.target) ? evt.target : ((evt.srcElement) ? evt.srcElement : null); 
		if ((evt.keyCode == 13) && (node.type=="text"))  {return false;} 
	} 
	document.onkeypress = stopRKey;
</script>
</head>
<body>
	<div class='${("User"==userType||"B2C"==session.LoginType)?"col-xs-12 col-sm-12 col-md-9 col-lg-9":"col-xs-12"}'>
		<div class="table0">
			<div class="tablerow">
				<span style="color:red;"> <s:actionerror/> </span>
			</div>
			<div class="tablerow">		
				<s:if test="'showQuoteInfo'.equalsIgnoreCase(display)">
				<s:form id="travel" name="travel" method="post"  action="geratePolicyHome.action" theme="simple">
				<div class="table1">			
					<div class="tablerow">				
						<div class="panel panel-primary">
							<div class="panel-heading">
								<s:label key="quotation.quoteInfo" /> 	
							</div>
							<div class="panel-body" align="center">
								<div style="color:red;font-size: 15px;"><b><s:label key="home.refInfo"/> Saved. Your Quote No is : ${quoteNo}</b></div>
								<br/><br/>
								<input type="button" name="Submit" class="btn btn-sm btn-success" value="Proceed" onclick="getBack('home');"/>
							</div>
						</div>
					</div>
				</div>					
				</s:form>
				</s:if>
				<s:elseif test="'showRefInfo'.equalsIgnoreCase(display)">
				<s:form id="travel" name="travel" method="post"  action="geratePolicyHome.action" theme="simple">
				<div class="table1">			
					<div class="tablerow">
						<div class="panel panel-primary">
							<div class="panel-heading">
								<s:label key="quotation.quoteInfo" /> 	
							</div>
							<div class="panel-body" align="center">
								<div style="color:red;font-size: 15px;"><b><s:label key="home.refInfo"/> ${referralMsg}</b></div>
								<br/><br/>
								<input type="button" name="Submit" class="btn btn-sm btn-success" value="Proceed" onclick="getBack('adminHome');"/>
							</div>
						</div>
					</div>
				</div>
				</s:form>
				</s:elseif>
				<s:else>
					<div class="table1">			
						<div class="tablerow">
							<s:include value="/pages/home/generalInfo.jsp"/>
							<s:form name="policy" action="generatePolicyHome" theme="simple">
								<s:if test='"admin".equals(#session.usertype)'>
									<div class="panel panel-primary">
										<div class="panel-heading">
											<s:text name="label.premium.info" />
										</div>
										<div class="panel-body">
											<table width="100%" class="footable">
					                   			<tr>
					                     			<td width="10%">&nbsp;</td>
					                     			<td width="25%"><s:text name="label.premium"/></td>
					                     			<td width="25%"><b><s:property value="#session.BrokerDetails.CurrencyAbb"/> &nbsp;</b><s:textfield name="finalPremium" id="finalPremium" value="%{getText('{0,number,##0.00}',{@java.lang.Double@valueOf(totalPremium)})}" cssClass="input" readonly="true" cssStyle="text-align:right;"/></td>
					                     			<td width="25%">&nbsp;</td>
					                     			<td width="2%">&nbsp;</td>
					          					</tr>
												<s:if test='#session.usertype == "admin" || quoteStatus == "RA"'>
												<%--<tr>
													<td width="2%">&nbsp;</td>
													<td width="25%"><s:text name="home.discount"/></td>
													<td width="25%"><b><s:property value="#session.BrokerDetails.CurrencyAbb"/> &nbsp;</b>
														<s:textfield name="discountAmt" id="discountAmt" cssClass="input" readonly="%{quoteStatus=='RA' && #session.usertype != 'admin'?'true':'false'}" onkeyup="checkDecimals(this);" maxlength="15" onchange="getTotalPremium(this.form)"/></td>
													<td width="25%">&nbsp;</td>
													<td width="2%">&nbsp;</td>
												</tr>--%>
												<tr>
													<td width="10%">&nbsp;</td>
													<td width="25%"><s:text name="home.loadingOrDiscountPremium"/>&nbsp;&nbsp;&nbsp;<s:select list="#{'+':'+','-':'-'}" name="sign" onchange="getTotalPremium(this.form)" disabled="%{quoteStatus=='RA' && #session.usertype != 'admin'?'true':'false'}"/></td>
													<td width="25%"><b><s:property value="#session.BrokerDetails.CurrencyAbb"/> &nbsp;</b>
														<s:textfield name="loadOrDiscPremium" id="loadOrDiscPremium" cssClass="input" readonly="%{quoteStatus=='RA' && #session.usertype != 'admin'?'true':'false'}" onkeyup="checkDecimals(this);" maxlength="15" cssStyle="text-align:right;" onchange="getTotalPremium(this.form)" maxLength="10" onblur="validamt(this);"/></td>
													<td width="25%">&nbsp;</td>
													<td width="2%">&nbsp;</td>
												</tr>
												</s:if>
					              				<tr>
						                            <td width="10%">&nbsp;</td>
						                            <td width="25%"><s:text name="home.policyFee"/></td>
						                            <td width="25%"><b><s:property value="#session.BrokerDetails.CurrencyAbb"/> &nbsp;</b>
						                            	<s:textfield name="policyFee" id="policyFee" cssClass="input" readonly="true" cssStyle="text-align:right;" value="%{getText('{0,number,##0.00}',{@java.lang.Double@valueOf(policyFee)})}"/></td>
						                            <td width="25%">&nbsp;</td>
						                            <td width="2%">&nbsp;</td>
						                 		</tr>
						                        <tr>
						                            <td width="10%">&nbsp;</td>
						                            <td width="25%">
							                            <s:if test='"0".equals(amendId)'>
							                            	<s:text name="label.total.premium.payable"/>
							                            </s:if>
							                            <s:else>
							                            	<s:text name="label.total.premium"/>
							                            </s:else>
						                            </td>
						                            <td width="25%"><b><s:property value="#session.BrokerDetails.CurrencyAbb"/> &nbsp;</b>
						                            	<s:textfield name="totalPremium" id="totalPremium" cssClass="input" readonly="true" cssStyle="text-align:right;" value="%{getText('{0,number,##0.00}',{@java.lang.Double@valueOf(policyFee)+@java.lang.Double@valueOf(totalPremium)})}"/></td>
						                            <td width="25%">&nbsp;</td>
						                            <td width="2%">&nbsp;</td>
						                 		</tr>
					              						<%--<s:if test='!"0".equals(amendId)'>
							                 		<tr>
								                    	<td width="2%">&nbsp;</td>
								                        <td width="25%"><s:text name="home.premiumPaid"/></td>
								                        <td width="25%"><b><s:property value="#session.BrokerDetails.CurrencyAbb"/> &nbsp;</b>
								                        	<s:textfield name="premiumPaid" id="premiumPaid" cssClass="input" readonly="true" maxlength="15" onchange="getTotalPremium(this.form)"/></td>
								                        <td width="25%">&nbsp;</td>
								                        <td width="2%">&nbsp;</td>
								                 	</tr>
								                 	<tr>
								                        <td width="2%">&nbsp;</td>
								                        <td width="25%"><s:text name="home.endtPremium"/></td>
								                        <td width="25%"><b><s:property value="#session.BrokerDetails.CurrencyAbb"/> &nbsp;</b>
								                        	<s:textfield name="endtPremium" id="endtPremium" cssClass="input" readonly="true" maxlength="15" onchange="getTotalPremium(this.form)"/></td>
								                        <td width="25%">&nbsp;</td>
								                        <td width="2%">&nbsp;</td>
								                 	</tr>
							                	</s:if>--%>
						                 		<s:if test='#session.usertype == "admin" || (!"".equals(adminRemarks)&&(!null.equals(adminRemarks))&& #session.usertype != "admin")'>
							                        <tr>
							                            <td width="10%">&nbsp;</td>
							                            <td width="25%"><s:text name="home.specialCondition"/></td>
							                            <td colspan="2" width="50%">
							                            	<s:textarea name="adminRemarks" onkeyup="textLimit(this,'470')" cssClass="input" rows="3" readonly="%{quoteStatus=='RA' && #session.usertype != 'admin'?'true':'false'}"/></td>
							                            <td width="2%">&nbsp;</td>
							                 		</tr>
						                 		</s:if>
						                 		<s:if test='#session.usertype == "admin"'>
							                         <tr>
							                            <td width="10%">&nbsp;</td>
							                            <td width="25%"><s:text name="home.referralStatus"/></td>
							                            <td width="25%"> <s:radio list="#{'Y':'Accept','N':'Reject','A':'Pending'}" name="adminRefStatus" value="%{adminRefStatus==null?'A':adminRefStatus}" /></td>
							                            <td width="25%">&nbsp;</td>
							                            <td width="2%">&nbsp;</td>
							                 		</tr>
						                 		</s:if>
					                 		</table>
										</div>
									</div>
								</s:if>
								<s:else>
									<s:include value="/pages/customer/customerDetailsMotor.jsp"/>
									<s:include value="/pages/home/homeSubPremium.jsp"/>
									<s:hidden name="policyFee"/>
				              		<s:set var="total" value="%{totalPremium}" scope="page"/>
									<s:if test='"+".equals(personalInfo[0].EXCESS_SIGN)'>
										<s:set var="total" value="%{@java.lang.Double@valueOf(personalInfo[0].EXCESS_PREMIUM)+@java.lang.Double@valueOf(totalPremium)}" scope="page"/>
									</s:if>
									<s:else>
										<s:set var="total" value="%{@java.lang.Double@valueOf(totalPremium)-@java.lang.Double@valueOf(personalInfo[0].EXCESS_PREMIUM)}" scope="page"/>
									</s:else>
									<s:set var="total" value="%{@java.lang.Double@valueOf(policyFee)+@java.lang.Double@valueOf(#attr.total)}" scope="page"/>
				              		<s:hidden name="totalPremium" value="%{#attr.total}"/>
				              		<%--
				              		<s:if test='"Y".equals(showReferralYN)'>
				               			<div class="panel panel-primary">
				               				<div class="panel-heading">
				               					<s:text name="home.referralInfo"/>
				               				</div>
				               				<div class="panel-body">
				               					<div class="boxcontent">
				               						<div class="textfieldA">
				               							<div class="text">
				               								<s:text name="home.referralYN"/>
				               							</div>
				               							<div class="tbox" align="center">
				               								<s:radio list="#{'Y':'Yes','N':'No'}" name="referralYN" id="referralYN" onclick="disablePolicyOption(this.value);" value='%{personalInfo[0].status=="Y"?"N":"Y"}'/>
				               							</div>
				               						</div>
				               						<div class="textfieldA">
				               							<div class="text">
				               								<s:text name="home.comments"/>
				               							</div>
				               							<div class="tbox" align="center">
				               								<s:textarea name="referralComments" id="referralComments" cssClass="inputBoxA" readonly="" cssStyle="width: 90%;" />
				               							</div>
				               						</div>
				               						<br class="clear"/>
				               					</div>
				               				</div>
				               			</div>
				               		</s:if>
				               		--%>
				               		<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" id="policyGeneration">
										<s:include value="/pages/payment/paymentInfo.jsp"/>
									</div>
				               	</s:else>
			               	
				               	<div style="margin-top: 10px;" align="center">
				               		<s:submit name="bck" cssClass="btn btn-sm btn-danger" value="Back" onclick="fnsubmit('uwMenuHome');"/>&nbsp;&nbsp;&nbsp;
				               		<s:if test='"admin".equals(#session.usertype)'>
										<s:submit name="Submit" cssClass="btn btn-sm btn-success" value="Proceed" onclick="this.form.actionType.value='getAdmProceed';disableForm(this.form,false,'');"/>
									</s:if>
									<s:else>
										<s:submit name="Submit" cssClass="btn btn-sm btn-success" value="Proceed" onclick="this.form.actionType.value='getProceed';disableForm(this.form,false,'');"/>
									</s:else>
									<s:hidden name="quoteNo"/>
					      			<s:hidden name="showReferralYN"/>
					      			<s:hidden name="actionType" />
					      			<s:hidden name="menuType" />
					      			<s:hidden name="applicationNo" />
				               	</div>
				               	<br class="clear"/>
			               </s:form>
						</div>			
					</div>		
				</s:else>
			</div>
		</div>
	</div>
	<s:if test='"User".equalsIgnoreCase(userType)||"B2C".equalsIgnoreCase(#session.LoginType)'>
		<div class="col-xs-12 col-sm-12 col-md-3 col-lg-3">
			<div class="row">
				<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
					<s:include value="/pages/motor/sideBar.jsp"></s:include>
				</div>
			</div>		
		</div>
	</s:if>
		<script>
		<s:if test='#session.user1 != "admin"'>
			<s:if test='"Y".equalsIgnoreCase(generatePolicyYN) || "viewPayment".equalsIgnoreCase(mode)'>
				getPaymentModeDetails('<s:property value="modeOfPayment"/>');
			</s:if>
		</s:if>
			function fnsubmit(action) {
				document.policy.action = "${pageContext.request.contextPath}/" + action;
				document.policy.submit();
			}
			function getBack(page){
				if(page=='home'){
					if('b2c'=='<s:property value="#session.b2c"/>')
						document.travel.action ='${pageContext.request.contextPath}/login/ProductSelection.jsp';
					else{
						if('RA'=='<s:property value="quoteStatus"/>')
							document.travel.action ='${pageContext.request.contextPath}/initReport.action?menuType=RA&loginId=<s:property value="loginId"/>';
						else if('RU'=='<s:property value="quoteStatus"/>')
							document.travel.action ='${pageContext.request.contextPath}/initReport.action?menuType=RU&loginId=<s:property value="loginId"/>';
						else if(('getSave'=='<s:property value="actionType"/>' || 'QS'=='<s:property value="quoteStatus"/>') && 0=='<s:property value="totalPremium"/>')
							document.travel.action ='${pageContext.request.contextPath}/initReport.action?menuType=QS&loginId=<s:property value="loginId"/>';
						else
							document.travel.action ='${pageContext.request.contextPath}/initReport.action?menuType=QE&loginId=<s:property value="loginId"/>';
					}
				}else if(page=='customer'){
					if('admin'=='<s:property value="#session.user1"/>'){
						/*if('RR'=='<s:property value="quoteStatus"/>')
							document.travel.action ='${pageContext.request.contextPath}/admin/HomeRejectedPolicyByDate.jsp';
						else
							document.travel.action ='${pageContext.request.contextPath}/admin/HomePendingPolicyByDate.jsp';*/
							document.travel.action ='${pageContext.request.contextPath}/getOCAjaxReferal.action';
					}else{
						if('ET'=='<s:property value="quoteStatus"/>')
							document.travel.action ='${pageContext.request.contextPath}/initReport.action?menuType=ET&custName=<s:property value="fullName"/>';
						else
							document.travel.action ='${pageContext.request.contextPath}/editCustomer.action';
					}
				}else if(page=='quote'){
					if('admin'=='<s:property value="#session.user1"/>'){
		     			if('RA'=='<s:property value="quoteStatus"/>'){
							//document.travel.action ='${pageContext.request.contextPath}/admin/HomeApprovedPolicyByDate.jsp';
							document.travel.action ='${pageContext.request.contextPath}/getOCAjaxReferal.action';
						}else
							document.travel.action ='${pageContext.request.contextPath}/initTravel.action';
					}else{
		    			if('RA'=='<s:property value="quoteStatus"/>')
							document.travel.action ='${pageContext.request.contextPath}/initReport.action?menuType=RA&loginId=<s:property value="loginId"/>';
						else
							document.travel.action ='${pageContext.request.contextPath}/initTravel.action';
					}
				}else if(page=='adminHome'){
					/*if('Y'=='<s:property value="adminRefStatus"/>')
						document.travel.action ='${pageContext.request.contextPath}/admin/HomeApprovedPolicy.jsp';
					else if('A'=='<s:property value="adminRefStatus"/>')
						document.travel.action ='${pageContext.request.contextPath}/admin/HomePendingPolicy.jsp';
					else if('N'=='<s:property value="adminRefStatus"/>')
						document.travel.action ='${pageContext.request.contextPath}/admin/HomeRejectedPolicy.jsp';*/
						document.travel.action ='${pageContext.request.contextPath}/getOCAjaxReferal.action';
				}
				document.travel.submit();
			}
			
			<%--
			<s:if test='#session.usertype != "admin" && "Y".equals(showReferralYN)'>
				disablePolicyOption('<s:property value="referralYN"/>');
			</s:if>
		    <s:if test='#session.usertype != "admin"'>
		    toggleEmailYN('<s:property value="generatePolicyYN"/>');
			function disablePolicyOption(value)  {
		 		if(value=="Y"){
					document.getElementById('policyGeneration').style.display='none';
					document.getElementById('referralComments').readOnly=false;
				} else {
				 	document.getElementById('policyGeneration').style.display='block';
				 	document.getElementById('referralComments').value='';
				 	document.getElementById('referralComments').readOnly=true;
				} 
	    	}
		    function toggleEmailYN(value) {
		 		if(value=="N") {
					document.getElementsByName("emailYN")[0].disabled=false;
					document.getElementsByName("emailYN")[1].disabled=false;
				 } else if(value=="Y") { 
				 	document.getElementsByName('emailYN')[0].checked=false;
				 	document.getElementsByName("emailYN")[0].disabled=true;
				 	document.getElementsByName('emailYN')[1].checked=true; 
				 	document.getElementsByName("emailYN")[1].disabled=true;				 	
				 }
		 		toggleModeOfPayment(value);
	    	}
	    	function toggleModeOfPayment(value) {
	    		if(value=="Y") {
	    			document.getElementById('generatePolicyYNY').checked=true;
	    			document.getElementById('modeOfPaymentDiv').style.display = 'block';
	    		} else {
	    			document.getElementById('modeOfPaymentDiv').style.display = 'none';
	    		}
	    	}
		    </s:if>
		    --%>
		    
		    function getTotalPremium(frm){
		   		if(frm.loadOrDiscPremium.value!=''){
					var coverPremium=0.0;
					var policyFee=0.0;
					var discountAmt=0;
					//var discountAmt=parseFloat(document.getElementById("discountAmt").value);
					var finalPremium=parseFloat(frm.finalPremium.value);
					var loadOrDiscPremium=parseFloat(frm.loadOrDiscPremium.value);
					frm.finalPremium.value=finalPremium.toFixed(1);
					//var discount=parseFloat(frm.discountAmt.value);
					var discount=0;
					var sign=frm.sign.value;
					var policyFee=parseFloat(document.getElementById("policyFee").value);
					var val=0;
					if(sign=='+'){
						val=finalPremium+loadOrDiscPremium+policyFee;
						frm.totalPremium.value=val.toFixed(1);
					}else {
						val=finalPremium-loadOrDiscPremium+policyFee;
						frm.totalPremium.value=val.toFixed(1);
					}
				}
			}
			
			function validamt(val){
				var value=val.value;
				if(value!=null){
					val.value = value.replace(/[^0-9.]/g,'');
				}
			}
		</script>
	</body>
</html>