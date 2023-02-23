<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ page import="java.util.List,java.util.ArrayList,java.util.Map,java.util.HashMap,java.util.Collections"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta name="description" content="">
	<meta name="keywords" content="Maan Sarovar, Sea Trans, Marine Insurance">	
	<meta name="author" content="">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="stylesheet" type="text/css" href="cssbootstrap/reset.css">
	<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/bootstrap/css/font-awesome.css" />
	<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/bootstrap/css/demo.css" />
	<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/bootstrap/css/bootstrap-multiselect.css" />
	<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/bootstrap/css/easy-responsive-tabs.css" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/bootstrap/css/datepicker3.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/bootstrap/css/slimmenu.css">
	<link href="<%=request.getContextPath()%>/bootstrap/css/footable-0.1.css" rel="stylesheet" type="text/css">
	<link href="<%=request.getContextPath()%>/bootstrap/css/table-res.css" rel="stylesheet" type="text/css">
	<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/bootstrap/css/bootstrap.min.css" />
	<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/bootstrap/css/style.css" />
	<style type="text/css">
	.inputBoxS {
		padding: 6px 10px;
	}
	.inputBox {
		padding: 6px 10px;
	}
	.cols .row {
		margin-top: 5px;
	}
	</style>
</head>
<body style="background-color: green; overflow: hidden;">
<s:form theme="simple" name="getQuoteForm">
<div class="row" style="color: #FFFFFF; padding: 15px;">
	<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 cols">
		<div class="row">
			<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" align="center">
				<h4><s:text name="Get Quote" /></h4>
			</div>
		</div>
		<div class="row">
			<div class="col-xs-4 col-sm-4 col-md-4 col-lg-4">
				&nbsp;
			</div>
			<div class="col-xs-8 col-sm-8 col-md-8 col-lg-8">
				<s:radio name="companyType" id="companyType" list="#{'1':'Retail','2':'Corporate'}" />
			</div>
		</div>
		<div class="row">
			<div class="col-xs-4 col-sm-4 col-md-4 col-lg-4">
				&nbsp;
			</div>
			<div class="col-xs-8 col-sm-8 col-md-8 col-lg-8">
				<s:select list="#{'1':'Home','2':'Medical','3':'Medical Malpractice','4':'Motor','5':'Travel'}" headerKey="" headerValue="CHOOSE COVER OPTION" cssClass="inputBoxS" />
			</div>
		</div>
		<div class="row">
			<div class="col-xs-4 col-sm-4 col-md-4 col-lg-4">
				<s:text name="Name" />
			</div>
			<div class="col-xs-8 col-sm-8 col-md-8 col-lg-8">
				<s:textfield name="name" id="name" cssClass="inputBox"  />
			</div>
		</div>
		<div class="row">
			<div class="col-xs-4 col-sm-4 col-md-4 col-lg-4">
				<s:text name="Gender" />
			</div>
			<div class="col-xs-8 col-sm-8 col-md-8 col-lg-8">
				<s:radio name="gender" id="gender" list="#{'1':'Male','2':'Female'}" />
			</div>
		</div>
		<div class="row">
			<div class="col-xs-4 col-sm-4 col-md-4 col-lg-4">
				<s:text name="Civil ID" />
			</div>
			<div class="col-xs-8 col-sm-8 col-md-8 col-lg-8">
				<s:textfield name="civilId" id="civilId" cssClass="inputBox"  />
			</div>
		</div>
		<div class="row">
			<div class="col-xs-4 col-sm-4 col-md-4 col-lg-4">
				<s:text name="DOB" />
			</div>
			<div class="col-xs-8 col-sm-8 col-md-8 col-lg-8">
				<s:textfield name="dob" id="dob" cssClass="inputBox datePicker"  />
			</div>
		</div>
		<div class="row">
			<div class="col-xs-4 col-sm-4 col-md-4 col-lg-4">
				<s:text name="Mobile" />
			</div>
			<div class="col-xs-8 col-sm-8 col-md-8 col-lg-8">
				<s:textfield name="mobile" id="mobile" cssClass="inputBox"  />
			</div>
		</div>
		<div class="row">
			<div class="col-xs-4 col-sm-4 col-md-4 col-lg-4">
				<s:text name="Email ID" />
			</div>
			<div class="col-xs-8 col-sm-8 col-md-8 col-lg-8">
				<s:textfield name="emailId" id="emailId" cssClass="inputBox"  />
			</div>
		</div>
		<div class="row">
			<div class="col-xs-4 col-sm-4 col-md-4 col-lg-4">
				<s:text name="City" />
			</div>
			<div class="col-xs-8 col-sm-8 col-md-8 col-lg-8">
				<s:select list="#{'1':'Al Khobar','2':'Jeddah','3':'Riyadh'}" headerKey="" headerValue="Select City" cssClass="inputBoxS" />
			</div>
		</div>
		<div class="row">
			<div class="col-xs-4 col-sm-4 col-md-4 col-lg-4">
				&nbsp;
			</div>
			<div class="col-xs-8 col-sm-8 col-md-8 col-lg-8">
				<button type="button" class="btn btn-sm btn-info">GET QUOTE</button>
			</div>
		</div>
	</div>
</div>
</s:form>
</body>
<script type="text/javascript" src="<%=request.getContextPath()%>/bootstrap/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/bootstrap/js/jquery.slimmenu.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/bootstrap/js/jquery.easing.min.js"></script>	
<script src="<%=request.getContextPath()%>/bootstrap/js/bootstrap-datepicker.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/bootstrap/js/bootstrap-3.2.0.min.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/bootstrap/js/easyResponsiveTabs.js" type="text/javascript"></script>
<script type="text/javascript">
$(function() {
	try {
		$('#dob').datepicker({
			todayHighlight: true,
			format: "dd/mm/yyyy"
		}).on('changeDate', function(e){
		    $(this).datepicker('hide');
		});
	} catch(err){}	
});
</script>
</html>