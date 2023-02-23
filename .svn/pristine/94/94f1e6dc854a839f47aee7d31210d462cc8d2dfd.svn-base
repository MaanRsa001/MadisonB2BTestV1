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
		    	document.form1.action="${pageContext.request.contextPath}/Loginsubmit.action";
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
			<%--<font color="#dca531" style="font: bold;font-size: 30px;">MGen-Online</font> --%>
			<br/>
			<font color="#dca531" style="font: bold;font-size: 30px;">OPERATIONAL / ADMIN USER</font> <br/>
			<i class="fa fa-phone"></i>: 4848 | 378700-5 | <i class="fa fa-envelope"></i>: <a href="mailto:online@madison.co.zm?Subject=Insure" target="_top" style="color: #DBA832;">online@madison.co.zm</a> <br/>
			<span style="font-size: 12px; display:none;">
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
						<img src="images/operational-admin-header.jpg" alt="Admin Banner" style="width: 100%; size: auto;">
					</div>
				</div>
			</div>
		</div>		
		<div class="row">
			<div class="col-xs-12 col-sm-12 col-md-5 col-lg-5">
				<div class="panel panel-primary">
					<div class="panel-body" style="padding: 0; min-height: 305px;">
						<h3>
							<span>Login</span>
						</h3>
						<br/>
						<div style="color: red;">
							<s:actionerror/>
						</div>
						<s:form name="form1" method="post" action="" theme="simple">
							<div class="tabsBg rEdge">
								<input type="button" class="tabBgA rEdge" name="admin" id='admin' value="Operational / Admin User" style="font-size: 14px; font-weight: bold;" />
							</div>
							<div class="form-group">
								<div class="" style="padding: 0 15px 0 15px;" id="ttwo">
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
													--%>
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
									<%-- <div class="row">
										<div class="col-xs-12" align="center">
											<a href="#" onclick="getTab('Broker')" style="font-size: 13px; font-weight: bold;">Registered Customers</a>
										</div>
									</div>
									<br class="clear" />--%>
									<div class="row">
										<div class="col-xs-6" align="center">
											<a href="${pageContext.request.contextPath}/Loginoption.action?status=changePwd" onclick="changePass()" style="font-size: 13px; font-weight: bold;">Change Password?</a><s:hidden name="appId" value="16"/>
										</div>									
										<div class="col-xs-6" align="center">
											<a href="${pageContext.request.contextPath}/Loginoption.action?status=forgotPwd&utype=admin" onclick="forgotPass()" style="font-size: 13px; font-weight: bold;" >Forgot Password?</a>
										</div>
									</div>
								</div>
								<s:hidden name="swidth" id="swidth" value=""/>
								<s:hidden name="loginType" value="admin"/>
								<br class="clear">
							</div>
						</s:form>				
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
		</div>
		<br class="clear" />
	</div>	
</div>
<div id="footer">
	<s:include value="../templates/footer.jsp" />
</div>
<script src="<%=request.getContextPath()%>/bootstrap/js/jquery.placeholder.js"></script>
<script type="text/javascript">	
	var width = (window.innerWidth > 0) ? window.innerWidth : screen.width;
	document.getElementById("swidth").value=width;
	
	jQuery(document).ready(function($) {
		$('#loginId').placeholder();
		$('#pwd').placeholder();
	  });
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