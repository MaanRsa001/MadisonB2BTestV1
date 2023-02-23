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
	<STYLE type="text/css">
		b{
			color:#043966;
		}
	</STYLE>
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
			</font> <br/>
			<i class="fa fa-phone"></i>: 4848 | 378700-5 | <i class="fa fa-envelope"></i>: <a href="mailto:online@madison.co.zm?Subject=Insure" target="_top" style="color: #DBA832;">online@madison.co.zm</a> <br/>
			<span style="font-size: 12px;">
				<a type="button" class="btn btn-sm btn-primary" href="${pageContext.request.contextPath}" style="width: 100px; height: 30px;">Home</a>
			</span>
		</div>
		<br class="clear" />
	</div>
	<br class="clear" />	
	<div class="row">
		<div class="col-xs-12">
			<div class="panel panel-primary">
				<div class="panel-heading">
					<s:text name="Downloads"/>
				</div>
				<div class="panel-body">
					<br/>
					<div class="row">
						<div class="col-xs-12">
							<s:submit name="Submit" type="submit" cssClass="btn btn-sm btn-info" value="Motor-Shield Car Insurance" onclick="getPolicyWordingPdf('65','private');" align="center"/>
						</div>
					</div>
					<br/>
					<div class="row">
						<div class="col-xs-12">
							<s:submit name="Submit" type="submit" cssClass="btn btn-sm btn-info" value="Motor-Commercial Insurance" onclick="getPolicyWordingPdf('65','public');" align="center"/>
						</div>
					</div>
					<br/>
					<div class="row">						
						<div class="col-xs-12">
							<s:submit name="Submit" type="submit" cssClass="btn btn-sm btn-info" value="Home Insurance" onclick="getPolicyWordingPdf('30','Home');" align="center"/>
						</div>					
					</div>
					<br/>
				</div>
			</div>
		</div>
	</div>
</div>
<div id="footer">
	<s:include value="../templates/footer.jsp" />
</div>
</body>
<script type="text/javascript">
function getPolicyWordingPdf(val,reqfrm){
	var URL ='${pageContext.request.contextPath}/policyWordingReport.action?productId='+val+'&downloadType='+reqfrm;
	var bwidth = window.innerWidth;
	var bwidth1 = document.body.clientWidth;
	if(bwidth <= 768) {
		return popUp(URL,bwidth1,'500');
	} else {
		return popUp(URL,'750','500');
	}
}
</script>
</html>