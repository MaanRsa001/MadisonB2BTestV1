<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<!DOCTYPE HTML>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1" />
		<title><tiles:insertAttribute name="title" ignore="true"/></title>
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
		<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/bootstrap/css/style.css" />
		<script type="text/javascript" src="<%=request.getContextPath()%>/js/common.js"></script>
		<script src="<%=request.getContextPath()%>/bootstrap/js/modernizr.custom.63321.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/bootstrap/js/jquery-1.9.1.min.js"></script>
		
		<STYLE type="text/css">
		#hoe-header {
		    background: #fff none repeat scroll 0 0;
		    margin: 0;
		    padding: 0;
		    width: 100%;
		    height: 50px;
		    z-index: 10;
			/*position:relative;
			position:fixed;*/
			position: absolute;
			top: 0;
			box-shadow: 0 1px 4px 0 rgba(0, 0, 0, 0.2);
		}
		.hoe-left-header {
		    background: #3498db;
		    font-size: 16px;
		    font-weight: 600;
		    line-height: 50px;
		    position: absolute;
		    float: left;
		    text-align: center;
		    width: 230px;
		    z-index: 999;
		}
		.hoe-header {
		    background: #1C105A;
		    font-size: 16px;
		    font-weight: 600;
		    line-height: 50px;
		    text-align: center;
		    z-index: 999;
		}
		.hoe-header a {
		    color: #FFF;
		}
		.contenthead{
		    font-size: 85%;
		    font-style: normal;
		    color : #1C0F5F;
		    font-weight: bold;
		}
		.content{
		font-size: 85%;
		    font-style: normal;
		}
		</STYLE>
	</head>
	<body>
		<s:set name="paymentDtl" value="%{paymentDetails}"/>
		<div id="hoe-header">
			<div class="hoe-header">
				<div class="row">
					<div class="col-xs-10">
						<img alt="Madison Logo" src="${pageContext.request.contextPath}/images/mgen-logo-sm.png" width="auto" height="50px" style="max-width: 275px;">
					</div>
					<div class="col-xs-1"></div>
					<div class="col-xs-1"></div>
				</div>
			</div>
		</div>
		<br class="clear"/>
		<div class="panel panel-default" style="margin: auto; margin-top: 50px; width: 95%;">
			<div class="panel-heading">
				<s:text name="Payment Details" />
			</div>
			<div class="panel-body">
				<div class="row">
					<div class="col-xs-5 contenthead">Reference No</div>
					<div class="col-xs-7 content"> <s:property value="#paymentDtl.MERCHANT_REFERENCE"/> </div>
				</div>
				<div class="row">
					<div class="col-xs-5 contenthead">Response Tran Id</div>
					<div class="col-xs-7 content"> <s:property value="#paymentDtl.RES_TRANSACTION_ID"/> </div>
				</div>
				<div class="row">
					<div class="col-xs-5 contenthead">Response Status</div>
					<div class="col-xs-7 content"> <s:property value="#paymentDtl.RES_DECISION"/> </div>
				</div>
				<div class="row">
					<div class="col-xs-5 contenthead">Response Message</div>
					<div class="col-xs-7 content"> <s:property value="#paymentDtl.RES_MESSAGE"/> </div>
				</div>
			</div>
		</div>
		<br class="clear"/>
		<div align="center">
			<a href="#?status=${paymentDtl.RES_DECISION}" class="btn btn-sm btn-success">Proceed</a>
		</div>
	</body>
</html>
