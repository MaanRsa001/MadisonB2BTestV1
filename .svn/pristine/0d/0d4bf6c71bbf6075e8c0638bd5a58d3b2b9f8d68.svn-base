<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored ="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
       <meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta name="description" content="">
	<meta name="keywords" content="Maan Sarovar, Sea Trans, Marine Insurance">	
	<meta name="author" content="">
	<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/bootstrap/css/demo.css" />
	<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/bootstrap/css/font-awesome.min.css" />
	<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/bootstrap/css/bootstrap-multiselect.css" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/bootstrap/css/datepicker3.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/bootstrap/css/slimmenu.css">
	<link href="<%=request.getContextPath()%>/bootstrap/css/footable-0.1.css" rel="stylesheet" type="text/css">
	<link href="<%=request.getContextPath()%>/bootstrap/css/table-res.css" rel="stylesheet" type="text/css">
	<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/bootstrap/css/bootstrap.min.css" />
	<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/bootstrap/css/style.css" />
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/common.js"></script>
	<script src="<%=request.getContextPath()%>/bootstrap/js/modernizr.custom.63321.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/bootstrap/js/jquery-1.9.1.min.js"></script>		
</head>
<body>
<s:form name="cancelreissue" theme="simple">
<div class="row">
	<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
		<s:actionerror cssStyle="color: red;" />
		<s:actionmessage cssStyle="color: green;" />
	</div>
</div>
<div class="row">
	<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
		<s:if test="'cancel'.equalsIgnoreCase(display)">
			<div class="panel panel-info">
				<div class="panel-heading">
					<s:text name="travelInfo.result.info"/>
				</div>
				<div class="panel-body" align="center">
					<s:text name="travelInfo.result.cancel"/>
				</div>
			</div>
		</s:if>
		<s:if test="'nocancel'.equalsIgnoreCase(display)">
			<div class="panel panel-info">
				<div class="panel-heading">
					<s:text name="travelInfo.result.info"/>
				</div>
				<div class="panel-body" align="center">
					<s:text name="travelInfo.result.nocancel"/>
				</div>
			</div>
		</s:if>
		<s:if test="'reissue'.equalsIgnoreCase(display)">
			<div class="panel panel-info">
				<div class="panel-heading">
					<s:text name="travelInfo.result.info"/>
				</div>
				<div class="panel-body" align="center">
					<s:text name="travelInfo.result.reissue"/>
		     		<s:text name="travelInfo.result.reissuequote"/>
		     		<s:property value="map.QUOTE_NO"/>
				</div>
			</div>
		</s:if>
		<s:if test="'nothing'.equalsIgnoreCase(display)">
			<div class="panel panel-info">
				<div class="panel-heading">
					<s:text name="travelInfo.result.info"/>
				</div>
				<div class="panel-body" align="center">
					<s:text name="travelInfo.result.nothing"/>
				</div>
			</div>
		</s:if>
		<s:if test='display==null'>
			<div class="panel panel-info">
				<div class="panel-heading">
					<s:text name="travelInfo.title"/>
				</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
							<div class="text"><s:text name="travelInfo.cancel"/></div>
							<div class="tbox">
								<s:radio name="cancelPolicy" list="#{'YES':'Yes','NO':'No'}" value="%{cancelPolicy==null?'NO':cancelPolicy}"/>
							</div>
						</div>
						<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
							<div class="text"><s:text name="travelInfo.reissue"/></div>
							<div class="tbox">
								<s:radio name="reissuePolicy" list="#{'YES':'Yes','NO':'No'}" value="%{reissuePolicy==null?'NO':reissuePolicy}"/>
							</div>
						</div>
						<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
							<div class="text"><s:text name="travelInfo.reason"/></div>
							<div class="tbox">
								<s:textarea name="reason" onkeyup="textLimit(this,'450')" cssClass="inputBoxA" rows="2" cssStyle="width: 100%;" />
							</div>
						</div>
					</div>
				</div>
			</div>
		</s:if>
	</div>
</div>
<div class="row">
	<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" align="center">
		<s:submit type="button" name="Cancel"  key="travel.cover.close" cssClass="btn btn-sm btn-danger" onclick="window.close();return false;"/>
		<s:if test='display==null'>
			&nbsp;&nbsp;&nbsp;
			<s:submit type="button" name="Proceed"  key="travel.cover.proceed" cssClass="btn btn-sm btn-success" onclick="forward('getCancelReissueTravel.action')"/>
		</s:if>
	</div>
</div>
<s:hidden name="quoteNo" ></s:hidden>
<s:hidden name="policyNo" ></s:hidden>
<s:hidden name="applicationNo" ></s:hidden>
<s:hidden name="product_id" ></s:hidden>
<s:hidden name="branch_code" ></s:hidden>
</s:form>
</body>
<script type="text/javascript">
function forward(url) {
	var val = document.cancelreissue.reason.value;
		if(val == null || val == ''){
			alert('Please Enter Reissue/Canceled reason for this Policy');
			return false;
    	}
 	document.cancelreissue.action = "${pageContext.request.contextPath}/"+url;
	document.cancelreissue.submit();
}
</script>
</html>