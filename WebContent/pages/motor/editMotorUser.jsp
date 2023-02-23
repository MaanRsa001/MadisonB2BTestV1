<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ page import="java.util.List,java.util.ArrayList,java.util.Map,java.util.HashMap,java.util.Collections"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
</head>
<body>
<s:form id="info" name="info" method="post" action="" theme="simple">
<div class="panel panel-primary">
	<div class="panel-body">
		<s:if test="'successNew'.equals(display)">
		<div class="row">
			<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
				<div align="center">
					<s:label key="user.new.success"/> <br/>
					<input type="button" onclick="fnsubmit('back','viewOptionCoverMotor', this.form)" name="Proceed" class="btn btn-sm btn-danger" value="Proceed" />
				</div>
			</div>
		</div>
		</s:if>
		<s:elseif test="'successUpdate'.equals(display)">
		<div class="row">
			<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
				<div align="center">
					<s:label key="user.update.success"/> <br/>
					<input type="button" onclick="fnsubmit('back','viewOptionCoverMotor', this.form)" name="Proceed" class="btn btn-sm btn-danger" value="Proceed" />
				</div>
			</div>
		</div>		
		</s:elseif>
		<s:else>		
		<div class="row">
			<s:if test="'new'.equals(display)">
				<div class="col-xs-12 col-sm-12 col-md-9 col-lg-9">
					<div class="row">
						<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
							<div class="panel panel-primary">
								<div class="panel-body"  style="padding: 0px;">
									<img alt="Car Header" src="<%=request.getContextPath()%>/images/sign_up.jpg" style="width: 100%; height: auto;">
								</div>				
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
							<div class="panel panel-default">
								<div class="panel-heading">
									<div class="row">
										<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
											<div class="btn-group" role="group" aria-label="...">							
											  <button type="button" title="Customer Information" title="Customer Information" class="btnGroupMargin btn btn-lg btn-warning"><i class="fa fa-user"></i></button>
											  <button type="button" title="Premium Comparison" class="btnGroupMargin btn btn-lg btn-warning""><i class="fa fa-eye"></i></button>
											  <button type="button" title="Personal Information" class="btnGroupMargin btn btn-lg btn-success active"><i class="fa fa-eye"></i></button>
											  <button type="button" title="Policy Information" class="btnGroupMargin btn btn-lg btn-primary" disabled="disabled"><i class="fa fa-file-text-o"></i></button>
											</div>
										</div>
										<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6 text-success">
											<span class="textHeading"><s:text name="label.personalInfo" /> </span> 
										</div>
									</div>				
								</div>		
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
							<s:if test="hasActionErrors()">
								<font color="red" style="list-style:none; "><s:actionerror cssStyle="list-style:none;"/></font>
							</s:if>
							<s:if test="hasActionMessages()">
								<s:actionmessage cssStyle="list-style:none; color:green;"/>
							</s:if>
						</div>
					</div>
					<div class="row">
						<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">					
							<s:include value="/pages/customer/customerDetailsMotor.jsp"/>
						</div>
					</div>
					<div class="row">
						<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
							<div class="panel panel-primary">
								<div class="panel-heading">
									<s:label key="user.login.creation" />
								</div>
								<div class="panel-body">
									<div class="row">
										<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
											<s:label key="user.email" /> <font color="red">*</font>
											<s:textfield name="email" cssClass="inputBox" maxlength="30"/>
										</div>
										<%--<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
											<s:label key="user.new" /> <font color="red">*</font>
											<s:textfield name="userId" cssClass="inputBox" maxlength="15" />										
										</div>
										--%><div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
											<s:label key="user.password" /> <font color="red">*</font>
											<s:password name="password" cssClass="inputBox tooltipContent" data-content="Password Must Contains Minimum 6 Digits" maxlength="20" />
										</div>
										<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
											<s:label key="user.rpassword" /> <font color="red">*</font>
											<s:password name="repassword" cssClass="inputBox tooltipContent" data-content="Password Must Contains Minimum 6 Digits" maxlength="20" />
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" align="center">
							<input type="button" onclick="fnReset();" name="Reset" class="btn btn-sm btn-danger" value="Reset" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<s:if test='"65".equals(#session.product_id)'>
								<input type="button" onclick="fnsubmit('back','editQuoteMotor', this.form)" name="Back" class="btn btn-sm btn-danger" value="Back" /> &nbsp;&nbsp;&nbsp;
							</s:if>
							<s:elseif test='"30".equals(#session.product_id)'>
								<input type="button" onclick="fnsubmit('back','uwMenuHome', this.form)" name="Back" class="btn btn-sm btn-danger" value="Back" /> &nbsp;&nbsp;&nbsp;
							</s:elseif>
							<input type="button" onclick="fnsubmit('back','newUserUserReg', this.form)" name="Submit" class="btn btn-sm btn-success" value="Submit" />
						</div>
					</div>
				</div>
				<div class="col-xs-12 col-sm-12 col-md-3 col-lg-3">
				<s:if test="productId != null ">
				<div class="row">
					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
						<div class="panel panel-primary">
							<div class="panel-body">
								<div class="panel panel-primary">
									<div class="panel-heading">
										Login
									</div>
									<div class="panel-body">
										<div class="row">
											<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
												<s:if test='"login".equals(mode)'>
													<span style="color:red;"><s:actionerror/></span>
												</s:if> 
											</div>
										</div>
										<div class="row">
											<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
												<s:text name="label.loginId"></s:text>
												<s:textfield cssClass="inputBox" name="userLoginId" maxlength="50" /> 
											</div>											
										</div>
										<div class="row">
											<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
												<s:text name="label.Password"></s:text>
												<s:password cssClass="inputBox" name="userPwd"/>
											</div>
										</div>
										<br/>
										<div class="row">
											<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" align="center">
												<a href="#" class="btn btn-sm btn-primary" onclick="callSubmit();" > <i class="glyphicon glyphicon-user"></i> &nbsp; Login  </a>
											</div>
										</div>
										<br/>
										<div class="row">
											<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" id="forgetPwd">
												<a href="${pageContext.request.contextPath}/Loginoption.action?status=forgotPwd" style="font-size: 13px; color:blue" >Forgot Password?</a>
												<%--
												<a data-toggle="modal" href="#" data-target="#myModal" style="text-align: center;">Forget Password?</a>
												 --%>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</s:if>
				<div class="row">
					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
						<div class="panel panel-primary">
							<div class="panel-body">
								<div class="panel panel-primary">
									<div class="panel-heading">
										Benefits
									</div>
									<div class="panel-body">
										<ol type="1">
											<li>View Quote</li>
											<li>View Policy</li>
											<li>Intimate Claim</li>
											<li>Intimate Endrosement</li>
											<li>Track your claim</li>
										</ol>
										
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			</s:if>
			<s:elseif test='"otpVerify".equalsIgnoreCase(display)'>
			<s:iterator value="policyInformation">
			<div class="clearfix"></div>
				<div class="row">
					<div class="col-xs-12">
						<div id="contentcontainer" class="layoutHeight">
						<div class="row">
							<div class="col-md-6 col-sm-6 col-xs-12">
							<div class="panel panel-vehicle">
								<div class="panel-heading">
										<s:text name="quotation.quoteInfo" />
									<br class="clear" />
							    </div>
									<div class="col-sm-12">
										<div class="col-xs-12">
											<ul class="list-unstyled">															
												<li><s:label key="premiumInfo.quoteNo" />&nbsp;:&nbsp;<s:property value="QUOTE_NO"/></li>				                                
												<li>
													<s:label key="policyInfo.quotationDate" />&nbsp;:&nbsp;
								                   	<s:property value="QUOTE_DATE"/>
												</li>
												<li><s:label key="policyInfo.coverStartDate" />&nbsp;:&nbsp;<s:property value="COVER_START_DATE"/></li>
												<li><s:label key="policyInfo.coverEndDate" />&nbsp;:&nbsp;<s:property value="COVER_END_PERIODS"/></li>
											</ul>
											
										</div>
									</div>
									<br class="clear" />
									<br class="clear" />
									<br class="clear" />   
									<br class="clear" />
								</div>
							</div>
							<div class="col-md-6 col-sm-6 col-xs-12">
								<div class="row">
									<s:include value="/pages/otpDetail.jsp" />
								</div>
							</div>
							<br class="clear" />
							</div>
							<div class="row">
								<div class="col-xs-6" align="center">
									<s:if test='"65".equals(#session.product_id)'>
										<input type="submit" id=close class="btn btn-sm btn-danger" value="<s:text name="label.back"/>" onclick="getBack('newQuote')" />
									</s:if>
									<s:elseif test='"30".equals(#session.product_id)'>
										<input type="submit" id=close class="btn btn-sm btn-danger" value="<s:text name="label.back"/>" onclick="getBack('home')" />
									</s:elseif>
									<s:elseif test='"33".equals(#session.product_id)'>
										<input type="submit" id=close class="btn btn-sm btn-danger" value="<s:text name="label.back"/>" onclick="getBack('travel')" />
									</s:elseif>
								</div>
								<div class="col-xs-6" align="center">
									<s:if test='"65".equals(#session.product_id)'>	
										<input type="submit" id="submitVehicle3" class="btn btn-sm bg-green" value="<s:text name="motor.proceed"/>" onclick="funsubmit('motor');" />
									</s:if>
									<s:elseif test='"30".equals(#session.product_id)'>
										<input type="submit" id="submitVehicle3" class="btn btn-sm bg-green" value="<s:text name="motor.proceed"/>" onclick="funsubmit('home');" />
									</s:elseif>
									<s:elseif test='"33".equals(#session.product_id)'>
										<input type="submit" id="submitVehicle3" class="btn btn-sm bg-green" value="<s:text name="motor.proceed"/>" onclick="funsubmit('travel');" />
									</s:elseif>
								</div>
							</div>
						</div>							
					</div>
				</div>
				<s:hidden name="loginId" value="%{LOGIN_ID}"/> 
				<s:hidden name="isFirstQuote" value="N"/>
			</s:iterator>
		</s:elseif> 
			<s:else>
				<div class="panel panel-primary">
					<div class="panel-heading"> <s:property value="email"/> &nbsp <s:text name="user.b2c.already.registered"></s:text>
					
					  </div>
				</div>
				<div class="col-xs-12 col-sm-12 col-md-7 col-lg-7">
				</div>
				<%-- <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
					<div class="row">
					<table class="tableWidth footable">
						<tr>
							<td width="30%">
								<s:text name="Name"></s:text>
							</td>
							<td width="70%">
								<b><s:property value="utitle"/> &nbsp; <s:property value="ufirstname"/> &nbsp; <s:property value="ulastname"/></b>
							</td>
						</tr>
						<tr>
							<td>
								<s:text name="Gender"></s:text>	
							</td>
							<td>
								<b> <s:property value="ugender"/> </b>	
							</td>
						</tr>
						<tr>
							<td>
								<s:text name="DOB"></s:text>	
							</td>
							<td>
								<b> <s:property value="udob"/> </b>	
							</td>
						</tr>
						<tr>
							<td>
								<s:text name="Address 1"></s:text>		
							</td>
							<td>
								<b> <s:property value="uaddress1"/> </b> 
							</td>
						</tr>
						<tr>
							<td>
								<s:text name="Address 2"></s:text>		
							</td>
							<td>
								<b> <s:property value="uaddress2"/> </b> 
							</td>
						</tr>
						<tr>
							<td>
								<s:text name="City"></s:text>	
							</td>
							<td>
								<b> <s:property value="ucity"/> </b>	
							</td>
						</tr>
						<tr>
							<td>
								<s:text name="Country"></s:text>	
							</td>
							<td>
								<b> <s:property value="ucountry"/> </b>	
							</td>
						</tr>
						<tr>
							<td>
								<s:text name="P.O.Box"></s:text>	
							</td>
							<td>
								<b> <s:property value="upobox"/> </b>	
							</td>
						</tr>
						<tr>
							<td>
								<s:text name="Telephone"></s:text>	
							</td>
							<td>
								<b> <s:property value="utelephone"/> </b>	
							</td>
						</tr>
						<tr>
							<td>
								<s:text name="Mobile"></s:text>	
							</td>
							<td>
								<b> <s:property value="umobile"/> </b>	
							</td>
						</tr>
						<tr>
							<td>
								<s:text name="user.email"></s:text>	
							</td>
							<td>
								<b> <s:property value="uemail"/> </b>	
							</td>
						</tr>
					</table>
					</div>
				</div>--%>
				<div class="col-xs-12 col-sm-12 col-md-5 col-lg-5">
					<div class="row">
						<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
							<div class="panel panel-primary">
								<div class="panel-body">
									<div class="panel panel-primary">
										<div class="panel-heading">
											Login
										</div>
										<div class="panel-body">
											<div class="row">
												<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
													<s:if test='"login".equals(mode)'>
														<span style="color:red;"><s:actionerror/></span>
													</s:if> 
												</div>
											</div>
											<div class="row">
												<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
													<s:text name="label.loginId"></s:text>
													<s:textfield cssClass="inputBox" name="userLoginId" maxlength="50" /> 
												</div>											
											</div>
											<div class="row">
												<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
													<s:text name="label.Password"></s:text>
													<s:password cssClass="inputBox" name="userPwd"/>
												</div>
											</div>
											<br/>
											<div class="row">
												<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" align="center">
													<a href="#" class="btn btn-sm btn-primary" onclick="callSubmit();" > <i class="glyphicon glyphicon-user"></i> &nbsp; Login  </a>
												</div>
											</div>
											<br/>
											<div class="row">
												<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" id="forgetPwd">
													<a href="${pageContext.request.contextPath}/Loginoption.action?status=forgotPwd" style="font-size: 13px; color:blue" >Forgot Password?</a>
													<%-- 
													<a data-toggle="modal" href="#" data-target="#myModal" style="text-align: center;">Forget Password?</a>
													--%>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</s:else>
			
			
			
		</div>
		</s:else>
	</div>
</div>
<s:hidden name="agencyCode"/>
<s:hidden name="mode"/>
<s:hidden name="display"/>
<s:hidden name="mode1"/>
<s:hidden name="uagencyCode"/>
<s:hidden name="borganization"/>
<s:hidden name="login_Id" value="%{#ulogin_Id}"/>
<s:hidden name="broker" value='%{getText("B2C_AGENCY_CODE")}'/>
<s:hidden name="utype" value="User"/>
<!-- Motor Details | End -->
<s:hidden name="applicationNo"/>
<s:hidden name="quoteNo"/>
<s:hidden name="referralMsg"/>
<s:hidden name="quoteStatus" />
<!-- Motor Details | Start -->
<s:if test='"otpVerify".equalsIgnoreCase(display) && "65".equals(#session.product_id)'>
	<s:hidden name="product" id="product"/>
	<s:hidden name="policyStartDate" id="policyStartDate"/>
	<s:hidden name="policyEndDate" id="policyEndDate"/>
	<s:hidden name="quoteDate" id="quoteDate"/>
	<s:hidden name="currencyType" id="currencyType"/>
	<s:hidden name="branchCode" id="branchCode"/>
	<s:hidden name="customerId" id="customerId"/>  
	<s:hidden name="policyType" id="policyType"/>
</s:if>
<s:if test='"otpVerify".equalsIgnoreCase(display) && "30".equals(#session.product_id)'>
	<s:hidden name="product" id="product"/>
	<s:hidden name="policyStartDate" id="policyStartDate"/>
	<s:hidden name="policyEndDate" id="policyEndDate"/>
	<s:hidden name="quoteDate" id="quoteDate"/>
	<s:hidden name="currencyType" id="currencyType"/>
	<s:hidden name="branchCode" id="branchCode"/>
	<s:hidden name="title" id="title"/>    
	<s:hidden name="customerId" id="customerId"/>  
	<s:hidden name="customerName" id="customerName"/>
	<s:hidden name="custLastName" id="custLastName"/>
	<s:hidden name="email" id="email"/>
	<s:hidden name="customerType" id="customerType"/>
	<s:hidden name="captchavalue" id="captchavalue"/>
	<s:hidden name="inceptionDt" id="inceptionDt"/>
	<s:hidden name="expiryDt" id="expiryDt"/>
	
</s:if>
<s:if test='"otpVerify".equalsIgnoreCase(display) && "33".equals(#session.product_id)'>
	<s:hidden name="actionType" id="actionType"/>
</s:if>
</s:form>
<s:form name="login_user" method="post" theme="simple">
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true" id="close"> &times; </button>
				<h4 class="modal-title">
					Forget Password
				</h4>
			</div>
			<div class="modal-body">
				<div class="te">
					<div id="error">
						<s:if test='!hasActionMessages()'>
							<div class="form-group">
								<s:text name="Username"></s:text>
								<s:textfield cssClass="form-control" name="userLoginId1" id="loginId" maxlength="20" />
							</div>
							<div class="form-group">
								<s:text name="Email"></s:text>
								<s:textfield cssClass="form-control" name="userEmail" id="email" maxlength="20" />
							</div>
							<a href="#" class="btn btn-primary" onclick="fnForgetPwd()">Submit</a>
						</s:if>
						<s:else>
							<font color="green"><s:text name="forgot.pass.success"></s:text> </font>
						</s:else>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal" id="close"> Close </button>
			</div>
		</div>
	</div>
</div>
</s:form>
<script type="text/javascript">
$(function() {
	try {
		
		$('#udob').datepicker({
			todayHighlight: true,
			format: "dd/mm/yyyy",
			viewMode: "years",
			endDate: "-18y"
		}).on('changeDate', function(e){
		    $(this).datepicker('hide');
		});
	} catch(err){}	
});

function forward1(agcode, id, value, mode1){
	postRequest('${pageContext.request.contextPath}/getUserAjaxUserMgm.action?reqFrom='+id+'&agencyCode='+agcode+'&productID='+value+'&mode1='+mode1, id);
}
   	
function fnsubmit(from, action, frm){
	frm.action=action+".action";
	frm.submit();
}

function funsubmit(page) {
	if(page=='motor') {
		document.info.action = '${pageContext.request.contextPath}/getCoverMotor.action';
	}else if(page=='home'){
		document.info.action = '${pageContext.request.contextPath}/getPolicyHome.action';
	}else if(page=='travel'){
		document.info.action = '${pageContext.request.contextPath}/quoteDtlTravel.action';
	}
	
	document.info.submit();
}
function getBack(page) {
	if(page=='newQuote') {
		document.info.action ='${pageContext.request.contextPath}/otpLoginMotor.action';
	}else if(page=='home'){
		document.info.action ='${pageContext.request.contextPath}/getBackHome';
	}else if(page=='travel'){
		document.info.action ='${pageContext.request.contextPath}/initTravel.action';
	}
	document.info.submit();
}

function callSubmit(){
	document.info.action='${pageContext.request.contextPath}/userLoginUserReg.action';
    document.info.submit();
}
function fnForgetPwd(){
	var loginId=document.getElementById("loginId").value;
	var email =document.getElementById("email").value;
	postRequest('${pageContext.request.contextPath}/forgotPwdUserReg.action?userLoginId1='+loginId+'&userEmail='+email,'error');
}

function fnReset() {
    document.getElementById("info").reset();
}
</script>
</body>
</html>