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
	<title> ::: Madison General - Forgot Password ::: </title>
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
		function callSubmit(val){
	       	if(val=='back'){
	       		document.changepwd.action="Loginpage.action";
	       		document.getElementById("status").value="";
	       	}else if(val=='submit'){
	       		document.changepwd.action="${pageContext.request.contextPath}/LoginpwdForgot.action";
	       	}
	          document.changepwd.submit();
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
	<div class="ielogo" style="text-align: center;">
		<img id="Madison General InsuranceLogo" src="images/EmiratesLogo.jpg" alt="logo" align="left">
	</div>	
	<div class="logonBg">
		<div class="row">
			<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
				<div class="panel panel-primary">
					<div class="panel-body" style="padding: 0; min-height: 305px;">
						<h3>
							<span><s:text name="forgot.password.reset" /></span>
						</h3>
						<br/>
						<div style="color: red;">
							<s:actionerror/>
						</div>
						<s:form id="changepwd" name="changepwd" method="post" action="" theme="simple">							
							<div class="form-group">
								<div class="tableWidth">
									<s:if test='"success"==status'>
										<div class="row">
											<div class="col-xs-12" align="center">
												<h3 class="label label-success"> <s:text name="forgot.pass.success"/> </h3>
											</div>
										</div>
									</s:if>
									<s:else>
										<s:if test='"user".equals(utype)'>
											<div class="row">
												<div class="col-xs-12">
													<div class="text"> <s:text name="Login Mail Id" /> <font color="red">*</font> </div>
													<div class="tbox">
														<s:textfield name="loginId" id="userId" cssClass="inputBox" placeholder="Login Name" cssStyle="border: 1px solid #c2c2c2;"/>
													</div>
												</div>
											</div>
										</s:if>
										<s:elseif test='"admin".equals(utype)||utype==null'>
											<div class="row">
												<div class="col-xs-12">
													<div class="text"> <s:text name="Login Id / Username" /> <font color="red">*</font> </div>
													<div class="tbox">
														<s:textfield name="loginId" id="userId" cssClass="inputBox" placeholder="Login Name" cssStyle="border: 1px solid #c2c2c2;"/>
													</div>
												</div>
											</div>
										</s:elseif>
										<div class="row">
											<div class="col-xs-12">
												<div class="text"><s:text name="forgot.email" /> <font color="red">*</font></div>
												<div class="tbox">
													<s:textfield name="mailId" id="mailId" cssClass="inputBox" cssStyle="border: 1px solid #c2c2c2;" />
												</div>
											</div>
										</div>
										<s:hidden name="utype" id="utype"/>
									</s:else>
									<s:hidden name="status" id="status"/>
									<br class="clear" />
									<div class="row">
										<div class="col-xs-12" align="center">
											<s:if test='"success"==status'>
												<s:hidden name="pwdMsg"/>
	                            				<s:hidden name="home"/>
	                              		<input type="button"  name="Submit1" style="cursor:pointer;" value=" Back " onclick="callSubmit('back')" class="btn btn-sm btn-danger" style="cursor:pointer;" />
											</s:if>
											<s:else>
												<s:hidden name="home"/>
			                            		<s:submit name="Submit1" cssStyle="cursor:pointer;" cssClass="btn btn-sm btn-danger" value=" Cancel " onclick="callSubmit('back')"/>&nbsp;&nbsp;&nbsp;
			                              		<s:reset name="Submit2" cssStyle="cursor:pointer;" value=" Reset " cssClass="btn btn-sm btn-warning"/>&nbsp;&nbsp;&nbsp;
			                              		<s:submit name="Submit1" cssStyle="cursor:pointer;" cssClass="btn btn-sm btn-success" value=" Submit " onclick="callSubmit('submit')"/>
											</s:else>
										</div>
									</div>
								</div>
								<br class="clear">
							</div>
							<s:hidden name="loginType" id="loginType"/>
						</s:form>				
					</div>
				</div>
			</div>			
		</div>
		<br class="clear" />
	</div>	
</div>
<div id="footer">
	<s:include value="../../templates/footer.jsp" />
</div>
<script src="<%=request.getContextPath()%>/bootstrap/js/jquery.placeholder.js"></script>
<script type="text/javascript">	
	var width = (window.innerWidth > 0) ? window.innerWidth : screen.width;
	document.getElementById("swidth").value=width;	
</script>
</body>
</html>