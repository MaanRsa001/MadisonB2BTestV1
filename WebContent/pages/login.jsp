<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta name="description" content="">
	<meta name="keywords" content="Maan Sarovar, Sea Trans, Marine Insurance">	
	<meta name="author" content="">
	<title> ::: Madison General - Login ::: </title>
	<link rel="shortcut icon" href="<%=request.getContextPath()%>/images/favicon.ico" />
	<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/bootstrap/css/demo.css" />
	<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/bootstrap/css/font-awesome.min.css" />
	<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/bootstrap/css/bootstrap-multiselect.css" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/bootstrap/css/datepicker3.css">	 
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/bootstrap/css/slimmenu.css">
	<link href="<%=request.getContextPath()%>/bootstrap/css/footable-0.1.css" rel="stylesheet" type="text/css">
	<link href="<%=request.getContextPath()%>/bootstrap/css/table-res.css" rel="stylesheet" type="text/css">
	<link href="<%=request.getContextPath()%>/bootstrap/css/easy-responsive-tabs.css" rel="stylesheet" type="text/css">
	<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/bootstrap/css/bootstrap.min.css" />
	<link href="${pageContext.request.contextPath}/bootstrap/css/loginStyle.css" rel="stylesheet" type="text/css">
	<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/bootstrap/css/style.css" />
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/common.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/shortcut.js"></script>
	<script src="<%=request.getContextPath()%>/bootstrap/js/modernizr.custom.63321.js"></script>
	<script src="<%=request.getContextPath()%>/bootstrap/js/jquery-1.9.1.min.js"></script>
	<script type="text/javascript">
		window.history.forward();
		function noBack(){ 
		    window.history.forward(); 
		}
		if(document.attachEvent)
		    document.attachEvent("onkeydown", my_onkeydown_handler);
		function my_onkeydown_handler() {
		    switch (event.keyCode) {
		        case 116 : // 'F5'
		            event.returnValue = false;
		            event.keyCode = 0;
		            window.status = "Refresh Functionality Disabled";
		            break;
		    }
		}
		shortcut.add("f5",function() {window.status = "Refresh Functionality Disabled";});
		shortcut.add("Ctrl+f5",function() {window.status = "Refresh Functionality Disabled";});
		shortcut.add("Ctrl+r",function() {window.status = "Refresh Functionality Disabled";});
		shortcut.add("Ctrl+Shift+r",function() {window.status = "Refresh Functionality Disabled";});
		shortcut.add("Ctrl+n",function() {window.status = "New Window Functionality Disabled";});
		
		function callSubmit(val){
			if(val=='login'){
		    	document.form1.action="${pageContext.request.contextPath}/LoginotpLogin.action";
		    }
		    document.form1.submit();
		}
	</script>
</head>
<!--[if lt IE 7 ]>   <body class="ie6">          <![endif]-->
<!--[if IE 7 ]>      <body class="ie7">          <![endif]-->
<!--[if IE 8 ]>      <body class="ie8">          <![endif]-->
<!--[if IE 9 ]>      <body class="ie9">          <![endif]-->
<!--[if (gt IE 9) ]> <body class="modern">       <![endif]-->
<!--[!(IE)]><!-->    <body class="notIE modern"> <!--<![endif]-->
<div id="page" class="content">
	<div class="ielogo">
		<div class="pullLeft">
			<a href="${pageContext.request.contextPath}">
				<img src="${pageContext.request.contextPath}/images/mgen-logo.png" border="0" >
			</a> 	
		</div>
		<div class="pullRight" align="center">		
			<font color="#dca531" style="font: bold;font-size: 30px;">
				<s:if test='"Live".equalsIgnoreCase(#session.userLoginMode)'>
					MGen-Online 	
				</s:if>
				<s:if test='"Test".equalsIgnoreCase(#session.userLoginMode)'>
					MGen-Test  
				</s:if>
			</font><br/>
			<i class="fa fa-phone"></i>: 4848 | 378700-5 | <i class="fa fa-envelope"></i>: <a href="mailto:online@madison.co.zm?Subject=Insure" target="_top" style="color: #DBA832;">online@madison.co.zm</a> <br/>
			<span style="font-size: 12px;">
				<a type="button" class="btn btn-sm btn-primary" href="${pageContext.request.contextPath}" style="width: 100px; height: 30px;">Home</a>
			</span>
		</div>
		<br class="clear" />
	</div>
	<br class="clear" />	
	<div class="logonBg">
		<div class="row">
			<div class="col-xs-12">
				<div class="panel panel-primary">
					<div class="panel-body" style="padding: 0px;">
						<img id="banner1" src="images/customer-portal-header.jpg" alt="Customer Banner" style="width: 100%; size: auto;">
						<%--<img id="banner2" src="images/operational-admin-header.jpg" alt="Admin Banner" style="width: 100%; size: auto;"> --%>
					</div>
				</div>
			</div>
		</div>		
		<div class="row">
		<s:form name="form1" method="post" id="form1" theme="simple">
			<!-- <div class="col-xs-12 col-sm-12 col-md-5 col-lg-5"> -->
			<div style="color: red;">
				<s:actionerror/>
			</div>
			<s:if test='"otpVerify".equalsIgnoreCase(showValue) || "renewalOTP".equalsIgnoreCase(showValue) || "botOTP".equalsIgnoreCase(showValue)'>
				<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
		             <div class="panel panel-primary">
						<div class="panel-body" style="padding: 0; min-height: 305px;">
							<h3>
								<span>OTP Verification</span>
							</h3>
							<br/>
							<!--  <div style="color: red;">
							<s:actionerror/>
						</div>-->
							<div class="tabsBg rEdge">
								<input type="button" class="tabBgA rEdge" name="Broker" id='Broker' value="Registered Customers" style="font-size: 14px; font-weight: bold;" />
								<%--<input type="button" class="tabBgA rEdge" name="admin" id='admin' value="Operational / Admin User" style="font-size: 14px; font-weight: bold;" /> --%>
							</div>
							<div class="form-group">
								<div class="" style="padding: 0 15px 0 15px;" id="tone">
									<div class="row">
										<div class="col-xs-12">
											<div class="text"><b><s:text name="customer.mobileNo" /></b></div>
											<div class="tbox">
												<label><s:text name="ologinId" /></label><s:hidden name="ologinId" id="ologinId" />
											</div>
											<%--<s:text name="OTP Num" /> : <s:property value="myOtp"/>--%>
										</div>
									</div>
									<div class="row">
										<div class="col-xs-12">
											<div class="text"><b><s:text name="label.otp.number" /><font color="red">*</font></b></div>
											<div class="tbox">
												<s:textfield name="otp" id="otp" placeholder="SMS OTP Number" maxlength="10" type="text" cssClass="inputBox" cssStyle="border: 1px;"/>
											</div>
										</div>
									</div>
									<br class="clear" />
									<div align="right">
										<span style="color: red;" ><s:text name="label.otp.timing" /></span>
									</div>
									<br class="clear" />
									<div class="row">
										<div class="col-xs-12" align="center">
											<button type="button" id="reotp" style="display: none;" class="btn btn-sm btn-warning" onclick="getOtpRegen('otpRegen');"><s:text name="label.regen.otp" /></button>
										</div>
									</div>
								</div>
								<s:hidden name="otpId" id="otpId" />
								<s:hidden name="otpStatus" id="otpStatus"/>
								<br class="clear" />
								<div class="row">
									<div class="col-xs-12" align="center">
										<button type="button" name="LoginButton" id="LoginButton" class="btn btn-sm btn-warning" onclick="otpLogin('user');" > <s:text name="label.user.login"/> </button>
									</div>
								</div>
							</div>
								<s:hidden name="appId" value="16"/>	
						</div>
					</div>
				</div>
		      <!--<div class="col-xs-12 col-sm-12 col-md-3 col-lg-3">
		      	 <div class="panel panel-primary">
			      	 <div class="panel-body" style="padding: 0; min-height: 305px;">
			      	 	<h3>
							<span>Login</span>
						</h3>
						<br/>
						  <div style="color: red;">
							<s:actionerror/>
						</div>
				      	<div class="tabsBg rEdge">
								<input type="button" class="tabBgA rEdge" name="Broker" id='Broker' value="Registered Customers" style="font-size: 14px; font-weight: bold;" />
								<%--<input type="button" class="tabBgA rEdge" name="admin" id='admin' value="Operational / Admin User" style="font-size: 14px; font-weight: bold;" /> --%>
							</div>
							<div class="form-group">
								<div class="" style="padding: 0 15px 0 15px;" id="tone">
									<div class="row">
										<div class="col-xs-12">
											<div class="text">Login Id</div>
											<div class="tbox">
												<s:textfield name="loginId" id="loginId" cssClass="inputBox" cssStyle="border: 1px;" placeholder="Login Id" maxlength="50" />
											</div>
										</div>
										<div class="col-xs-12">
											<div class="text">Password</div>
											<div class="tbox">
												<s:password name="pwd" id="pwd" cssClass="inputBox" placeholder="Password" />
											</div>
										</div>
									</div>
									<br class="clear" />
									<div class="row">
										<div class="col-xs-12" align="center">
											<s:submit type="button" id="userLogin" class="btn btn-sm btn-warning" onclick="funSubmit();" theme="simple">Login</s:submit>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				--><div class="col-xs-12 col-sm-12 col-md-8 col-lg-8">
					<div class="panel panel-primary">
						<div class="panel-body" style="padding:0; min-height: 305px;">
							<h3>
								<span>Welcome to MGen</span>
							</h3>
							<br/>
							<div class="" style="padding: 0 15px 0 15px;">
								<p>Madison General Insurance has been the insurer of preferred choice since 1992. We have a rich portfolio of satisfied clients and the list keeps growing. We boast a presence in 6 out of 10 provinces in Zambia and plans are underway to increase our customer touch-points through more branches and mobile offices.</p>
								<p>Our claims payment period is comparable to none in the industry coupled with unprecedented quality customer service. You do not just buy insurance with us, but you earn the right to a peace of mind. What are you waiting for? Get on the winning team today! It's worth it!</p>
							</div>
						</div>
					</div>
				</div>
	      </s:if>
	      <s:elseif test='"otpVerifyNew".equalsIgnoreCase(showValue)'>
				<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
		             <div class="panel panel-primary">
						<div class="panel-body" style="padding: 0; min-height: 305px;">
							<h3>
								<span>OTP Verification</span>
							</h3>
							<br/>
							<!--  <div style="color: red;">
							<s:actionerror/>
						</div>-->
							<div class="tabsBg rEdge">
								<input type="button" class="tabBgA rEdge" name="Broker" id='Broker' value="Registered Customers" style="font-size: 14px; font-weight: bold;" />
								<%--<input type="button" class="tabBgA rEdge" name="admin" id='admin' value="Operational / Admin User" style="font-size: 14px; font-weight: bold;" /> --%>
							</div>
							<div class="form-group">
								<div class="" style="padding: 0 15px 0 15px;" id="tone">
									<div class="row">
										<div class="col-xs-12">
											<div class="text"><b><s:text name="customer.mobileNo" /></b></div>
											<div class="tbox">
												<label><s:text name="ologinId" /></label><s:hidden name="ologinId" id="ologinId" /><s:hidden name="loginId" id="loginId" />
											</div>
											<%--<s:text name="OTP Num" /> : <s:property value="myOtp"/>--%>
										</div>
									</div>
									<div class="row">
										<div class="col-xs-12">
											<div class="text"><b><s:text name="label.otp.number" /><font color="red">*</font></b></div>
											<div class="tbox">
												<s:textfield name="otp" id="otp" placeholder="SMS OTP Number" maxlength="10" type="text" cssClass="inputBox" cssStyle="border: 1px;"/>
											</div>
										</div>
									</div>
									<br class="clear" />
									<div align="right">
										<span style="color: red;" ><s:text name="label.otp.timing" /></span>
									</div>
									<br class="clear" />
									<div class="row">
										<div class="col-xs-12" align="center">
											<button type="button" id="reotp" style="display: none;" class="btn btn-sm btn-warning" onclick="getOtpRegen('otpRegen');"><s:text name="label.regen.otp" /></button>
										</div>
									</div>
								</div>
								<s:hidden name="otpId" id="otpId" />
								<s:hidden name="otpStatus" id="otpStatus"/>
								<br class="clear" />
								<div class="row">
									<div class="col-xs-12" align="center">
										<button type="button" name="LoginButton" id="LoginButton" class="btn btn-sm btn-warning" onclick="otpLoginNew('user');" > <s:text name="label.user.login"/> </button>
									</div>
								</div>
							</div>
								<s:hidden name="appId" value="16"/>	
						</div>
					</div>
				</div>
		     <div class="col-xs-12 col-sm-12 col-md-8 col-lg-8">
					<div class="panel panel-primary">
						<div class="panel-body" style="padding:0; min-height: 305px;">
							<h3>
								<span>Welcome to MGen</span>
							</h3>
							<br/>
							<div class="" style="padding: 0 15px 0 15px;">
								<p>Madison General Insurance has been the insurer of preferred choice since 1992. We have a rich portfolio of satisfied clients and the list keeps growing. We boast a presence in 6 out of 10 provinces in Zambia and plans are underway to increase our customer touch-points through more branches and mobile offices.</p>
								<p>Our claims payment period is comparable to none in the industry coupled with unprecedented quality customer service. You do not just buy insurance with us, but you earn the right to a peace of mind. What are you waiting for? Get on the winning team today! It's worth it!</p>
							</div>
						</div>
					</div>
				</div>
	      </s:elseif>
	      <s:elseif test='"userLogin".equalsIgnoreCase(showValue)'>
		      <div class="col-xs-12 col-sm-12 col-md-5 col-lg-5">
			      	 <div class="panel panel-primary">
				      	 <div class="panel-body" style="padding: 0; min-height: 305px;">
				      	 	<h3>
								<span>Login</span>
							</h3>
							<br/>
					      	<div class="tabsBg rEdge">
									<input type="button" class="tabBgA rEdge" name="Broker" id='Broker' value="Registered Customers" style="font-size: 14px; font-weight: bold;" />
									<%--<input type="button" class="tabBgA rEdge" name="admin" id='admin' value="Operational / Admin User" style="font-size: 14px; font-weight: bold;" /> --%>
								</div>
								<div class="form-group">
									<div class="" style="padding: 0 15px 0 15px;" id="tone">
										<div class="row">
											<div class="col-xs-12">
												<div class="text">Login Id</div>
												<div class="tbox">
													<s:textfield name="loginId" id="loginId" cssClass="inputBox" cssStyle="border: 1px;" placeholder="Login Id" maxlength="50" />
												</div>
											</div>
											<div class="col-xs-12">
												<div class="text">Password</div>
												<div class="tbox">
													<s:password name="pwd" id="pwd" cssClass="inputBox" placeholder="Password" />
												</div>
											</div>
										</div>
										<br class="clear" />
										<div class="row">
											<div class="col-xs-12" align="center">
												<s:submit type="button" id="userLogin" class="btn btn-sm btn-warning" onclick="funSubmit();" theme="simple">Login</s:submit>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="col-xs-12 col-sm-12 col-md-7 col-lg-7">
						<div class="panel panel-primary">
							<div class="panel-body" style="padding:0; min-height: 305px;">
								<h3>
									<span>Welcome to MGen</span>
								</h3>
								<br/>
								<div class="" style="padding: 0 15px 0 15px;">
									<p>Madison General Insurance has been the insurer of preferred choice since 1992. We have a rich portfolio of satisfied clients and the list keeps growing. We boast a presence in 6 out of 10 provinces in Zambia and plans are underway to increase our customer touch-points through more branches and mobile offices.</p>
									<p>Our claims payment period is comparable to none in the industry coupled with unprecedented quality customer service. You do not just buy insurance with us, but you earn the right to a peace of mind. What are you waiting for? Get on the winning team today! It's worth it!</p>
								</div>
							</div>
						</div>
					</div>
	      </s:elseif>
          <s:else>
          	<!--<div class="col-xs-12 col-sm-12 col-md-3 col-lg-3">
				<div class="panel panel-primary">
					<div class="panel-body" style="padding: 0; min-height: 305px;">
						<h3>
							<span>OTP Login</span>
						</h3>
						<br/>
						<div style="color: red;">
							<s:actionerror/>
						</div>
						<div class="tabsBg rEdge">
							<input type="button" class="tabBgA rEdge" name="Broker" id='Broker' value="Registered Customers" style="font-size: 14px; font-weight: bold;" />
							<%--<input type="button" class="tabBgA rEdge" name="admin" id='admin' value="Operational / Admin User" style="font-size: 14px; font-weight: bold;" /> --%>
						</div>
						<div class="form-group">
							<div class="" style="padding: 0 15px 0 15px;" id="tone">
							<br class="clear" />
							<br class="clear" />
								<div class="row">
									<div class="col-xs-12">
										<div class="text"><b><s:text name="label.login.user" /></b></div>
											<div class="tbox">
												<s:textfield name="bloginId" id="bloginId" cssClass="inputBox" cssStyle="border: 1px;" placeholder="Email Address" maxlength="50" />
												<s:textfield name="ologinId" id="ologinId" placeholder="Mobile No" maxlength="10" type="text" cssClass="inputBox ; numericOnly" cssStyle="border: 1px;"/>
											</div>
									</div>
								<div class="col-xs-12">
									<div class="text">Password</div>
									<div class="tbox">
										<s:password name="bpwd" id="bpwd" cssClass="inputBox" placeholder="Password" />
									</div>
								</div>
								</div>
								<br class="clear" />
								<div class="row">
									<div class="col-xs-12" align="center">
										<button type="button" name="LoginButton" id="LoginButton" class="btn btn-sm btn-warning" onclick="callSubmit('login');"> Login </button>
									</div>
								</div>
								<br class="clear" />
							<div class="row">
								<%-- <div class="col-xs-6" align="center">
									<a href="#" onclick="getTab('admin')" style="font-size: 13px; font-weight: bold;">Operation / Admin User</a>
								</div>--%>
								<div class="col-xs-12" align="center">
									<a href="${pageContext.request.contextPath}/UserNewRegistration.action" style="font-size: 13px; font-weight: bold;">New User Registration</a>
								</div>
							</div>
							<br class="clear" />
							<div class="row">
								<div class="col-xs-6" align="center">
									<a href="${pageContext.request.contextPath}/Loginoption.action?status=changePwd" onclick="changePass()" style="font-size: 13px; font-weight: bold;">Change Password?</a><s:hidden name="appId" value="16"/>
								</div>									
								<div class="col-xs-6" align="center">
									<a href="${pageContext.request.contextPath}/Loginoption.action?status=forgotPwd&utype=user" onclick="forgotPass()" style="font-size: 13px; font-weight: bold;" >Forgot Password?</a>
								</div>
							</div>
						</div>
						<%-- <div class="" style="padding: 0 15px 0 15px;" id="ttwo">
							<div class="row">
								<div class="col-xs-12">
									<div class="text">Username</div>
									<div class="tbox">
										<s:textfield name="aloginId" id="aloginId" cssClass="inputBox" cssStyle="border: 1px;" placeholder="Username" maxlength="50" />
									</div>
								</div>										
								<div class="col-xs-12">
									<div class="text">Password</div>
									<div class="tbox">
										<s:password name="apwd" id="apwd" cssClass="inputBox" placeholder="Password" />
									</div>
								</div>
								<div class="col-xs-12">
									<div class="text">Branch</div>
									<div class="tbox">
										<div class="row">
											<div class="col-xs-12 col-sm-12 col-md-6 col-lg-12">
												<s:select name="branch" id="branch" cssClass="inputBoxS" list="branchList"  listKey="BRANCH_CODE" listValue="BRANCH_NAME" />
											</div>
											<%--
											<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
												<s:select name="product" id="product" list="productList" listKey="PRODUCT_ID" listValue="PRODUCT_NAME" cssClass="inputBoxS"></s:select>
											</div>
											
										</div>												
									</div>
								</div>
							</div>
							<br class="clear" />
							<div class="row">
								<div class="col-xs-12" align="center">
									<button type="submit" name="LoginButton" id="LoginButton" class="btn btn-sm btn-warning" onclick="callSubmit('login');"> Login </button>
								</div>
							</div>
							<br class="clear" />
							<div class="row">
								<div class="col-xs-12" align="center">
									<a href="#" onclick="getTab('Broker')" style="font-size: 13px; font-weight: bold;">Registered Customers</a>
								</div>
							</div>
							<br class="clear" />
							<div class="row">
								<div class="col-xs-6" align="center">
									<a href="${pageContext.request.contextPath}/Loginoption.action?status=changePwd" onclick="changePass()" style="font-size: 13px; font-weight: bold;">Change Password?</a><s:hidden name="appId" value="16"/>
								</div>									
								<div class="col-xs-6" align="center">
									<a href="${pageContext.request.contextPath}/Loginoption.action?status=forgotPwd" onclick="forgotPass()" style="font-size: 13px; font-weight: bold;" >Forgot Password?</a>
								</div>
							</div>
						</div>--%>
						   		<s:hidden name="appId" value="16"/>
						  	 	<br class="clear">
					  	  	</div>
				    	</div>
			    	</div>
		    	</div>
		    	--><!--<div class="col-xs-12 col-sm-12 col-md-1 col-lg-1" align="center">
		    		<div class="panel-body" >
			    		<div class="form-group">
			    			<div class ="" style="margin-top: 144px;height: 10px;font-size:18px"><b>OR</b></div>
			    		</div>
		    		</div>
		    	</div>
		    	--><div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
		      	 <div class="panel panel-primary">
			      	 <div class="panel-body" style="padding: 0; min-height: 305px;">
			      	 	<h3>
							<span>Broker Login</span>
						</h3>
						<br/>
						<!--<div style="color: red;">
							<s:actionerror/>
						</div>-->
				      	<div class="tabsBg rEdge">
								<input type="button" class="tabBgA rEdge" name="Broker" id='Broker' value="Registered Brokers" style="font-size: 14px; font-weight: bold;" />
								<%--<input type="button" class="tabBgA rEdge" name="admin" id='admin' value="Operational / Admin User" style="font-size: 14px; font-weight: bold;" /> --%>
							</div>
							<div class="form-group">
								<div class="" style="padding: 0 15px 0 15px;" id="tone">
								<br class="clear" />
								<br class="clear" />
									<div class="row">
										<div class="col-xs-12">
											<div class="text"><b>Login Id</b></div>
											<div class="tbox">
												<s:textfield name="loginId" id="loginId" cssClass="inputBox" cssStyle="border: 1px;" placeholder="Login Id" maxlength="50" />
											</div>
										</div>
										<div class="col-xs-12">
											<div class="text">Password</div>
											<div class="tbox">
												<s:password name="pwd" id="pwd" cssClass="inputBox" placeholder="Password" />
											</div>
										</div>
										<div class="col-xs-12">
											<div class="text">Branch</div>
											<div class="tbox">
												<s:select name="branch" id="branch" cssClass="inputBoxS" list="branchList"  listKey="BRANCH_CODE" listValue="BRANCH_NAME" />
											</div>
										</div>
									</div>
									<br class="clear" />
									<div class="row">
										<div class="col-xs-12" align="center">
											<s:submit type="button" id="userLogin" class="btn btn-sm btn-warning" onclick="funSubmitUsr('user');" theme="simple">Login</s:submit>
											<!--<s:submit type="button" id="userLogin" class="btn btn-sm btn-warning" onclick="funSubmitUsrNew('user');" theme="simple">Login</s:submit>-->
										</div>
									</div>
									<br class="clear" />
									<div class="row">
										<div class="col-xs-6" align="center">
											<a href="${pageContext.request.contextPath}/Loginoption.action?status=changePwd" onclick="changePass()" style="font-size: 13px; font-weight: bold;">Change Password?</a><s:hidden name="appId" value="16"/>
										</div>									
										<div class="col-xs-6" align="center">
											<a href="${pageContext.request.contextPath}/Loginoption.action?status=forgotPwd" onclick="forgotPass()" style="font-size: 13px; font-weight: bold;" >Forgot Password?</a>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="col-xs-12 col-sm-12 col-md-8 col-lg-8">
				<div class="panel panel-primary">
					<div class="panel-body" style="padding:0; min-height: 305px;">
						<h3>
							<span>Welcome to MGen</span>
						</h3>
						<br/>
						<div class="" style="padding: 0 15px 0 15px;">
							<p>Madison General Insurance has been the insurer of preferred choice since 1992. We have a rich portfolio of satisfied clients and the list keeps growing. We boast a presence in 6 out of 10 provinces in Zambia and plans are underway to increase our customer touch-points through more branches and mobile offices.</p>
							<p>Our claims payment period is comparable to none in the industry coupled with unprecedented quality customer service. You do not just buy insurance with us, but you earn the right to a peace of mind. What are you waiting for? Get on the winning team today! It's worth it!</p>
						</div>
					</div>
				</div>
			</div>
		   	</s:else>
			<!-- </div> -->
			<!--  <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
				<div class="panel panel-primary">
					<div class="panel-body" style="padding:0; min-height: 305px;">
						<h3>
							<span>Welcome to MGen</span>
						</h3>
						<br/>
						<div class="" style="padding: 0 15px 0 15px;">
							<p>Madison General Insurance has been the insurer of preferred choice since 1992. We have a rich portfolio of satisfied clients and the list keeps growing. We boast a presence in 6 out of 10 provinces in Zambia and plans are underway to increase our customer touch-points through more branches and mobile offices.</p>
							<p>Our claims payment period is comparable to none in the industry coupled with unprecedented quality customer service. You do not just buy insurance with us, but you earn the right to a peace of mind. What are you waiting for? Get on the winning team today! It's worth it!</p>
						</div>
					</div>
				</div>
			</div>-->
			<s:hidden name="swidth" id="swidth" value=""/>
			<s:hidden name="loginType" id="loginType"/>
			<s:hidden name="isArabic" id="isArabic"/>
			<s:hidden name="mode" value="b2c"/>
			<s:hidden name="showValue" />
			<s:hidden name="branchName"/>
			
			</s:form>
		</div>
		<br class="clear" />
	</div>	
</div>
<div id="footer">
	<s:include value="../templates/footer.jsp" />
</div>
<script src="<%=request.getContextPath()%>/bootstrap/js/jquery.placeholder.js"></script>
<script type="text/javascript">	
try{
	var width = (window.innerWidth > 0) ? window.innerWidth : screen.width;
	document.getElementById("swidth").value=width;
	}catch(err){
		
	}
	jQuery(document).ready(function($) {
		$('#loginId').placeholder();
		$('#pwd').placeholder();
	  });
	  
	  $(".numericOnly").on("keypress keyup blur",function (event) {  
			this.value = this.value.replace(/[^\d]/g,'');
	            if (event.which > 57) {
	                event.preventDefault();
	            }
	      });

   function getOtpRegen(id){
	otpId=document.getElementById("otpId").value;
	mobile=document.getElementById("ologinId").value;
	postRequest('${pageContext.request.contextPath}/regenAjaxOtpLogin.action?otpId='+otpId+'&mobile='+mobile+'&reqFrom='+id, id);
	document.getElementById("reotp").style.display = "none";
	setTimeout(function(){
		document.getElementById("reotp").style.display = "block";
	 }, 60000);
	}
setTimeout(function(){
	document.getElementById("reotp").style.display = "block";
 }, 60000);
 
function otpLogin(loginType){
	document.form1.loginType.value = loginType;
    document.form1.action="${pageContext.request.contextPath}/LoginvalidOTP.action";
    document.form1.submit();
}   
function funSubmit(){
   	document.form1.action="${pageContext.request.contextPath}/Loginsubmit.action";
    document.form1.submit();
}
function funSubmitUsr(loginType){
	var e = document.getElementById('branch'); 
   	var branchName = e.options[e.selectedIndex].text; 
   	document.form1.branchName.value = branchName; 
	document.form1.loginType.value = loginType;
   	document.form1.action="${pageContext.request.contextPath}/Loginsubmit.action";
    document.form1.submit();
}
function funSubmitUsrNew(loginType){
	document.form1.loginType.value = loginType;
   	document.form1.action="${pageContext.request.contextPath}/LoginotpLoginUsr.action";
    document.form1.submit();
}
function otpLoginNew(loginType){
	document.form1.loginType.value = loginType;
    document.form1.action="${pageContext.request.contextPath}/LoginvalidOTPNew.action";
    document.form1.submit();
}
	/*try{
		getTab('Broker');
	}catch(e){}
	function getTab(loginType){
		if(loginType=="Broker") {
			document.getElementById("Broker").style.display="block";
			document.getElementById("admin").style.display = "none";
			document.getElementById("tone").style.display = "block";
			document.getElementById("banner1").style.display = "block";
			document.getElementById("ttwo").style.display = "none";
			document.getElementById("banner2").style.display = "none";
			document.getElementById("loginType").value = loginType;
		}
		else if(loginType=="admin") {
			document.getElementById("admin").style.display="block";
			document.getElementById("Broker").style.display = "none";
			document.getElementById("ttwo").style.display = "block";
			document.getElementById("banner2").style.display = "block";
			document.getElementById("tone").style.display = "none";
			document.getElementById("banner1").style.display = "none";
			document.getElementById("loginType").value = loginType;
		}
	}
	
	function callNewRegistration(val, loginId) {
	}*/
			
</script>
</body>
</html>