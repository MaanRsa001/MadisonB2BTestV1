<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<script type="text/javascript" src="<%=request.getContextPath()%>/bootstrap/js/jquery-1.9.1.min.js"></script>
<title>Madison Payment Gateway</title>
<style>
/* Add animation to "page content" */
.animate-bottom {
  position: relative;
  -webkit-animation-name: animatebottom;
  -webkit-animation-duration: 1s;
  animation-name: animatebottom;
  animation-duration: 1s
}

@-webkit-keyframes animatebottom {
  from { bottom:-100px; opacity:0 } 
  to { bottom:0px; opacity:1 }
}

@keyframes animatebottom { 
  from{ bottom:-100px; opacity:0 } 
  to{ bottom:0; opacity:1 }
}

#infoDiv {
  text-align: center;
}
</style>
</head>
<body onload="wcactivity();">
<div id="infoDiv" class="animate-bottom">
  <h2>Please don't close or refresh the page.</h2>
  <p>Accept your Airtel Money Payment</p>
</div>
<br/>
<div id="loading" style="width:100%;text-align: center;">
   <img id="loading-image" src="${pageContext.request.contextPath}/images/ajax-loader-bar.gif"/>
</div>
<s:form name="payment" id="payment" action="airtelResPayment">
<s:hidden name="merchant_reference" id="merchant_reference"/>
</s:form>
<script type="text/javascript">	
function wcactivity(){
	try{
		var a = setTimeout(cpactivity, 10000);  
	}catch(err){
		console.log(err);
	}
}
function cpactivity(){
	try{
		var merchantNo = document.getElementById('merchant_reference').value
		$.ajax({
	        url: "${pageContext.request.contextPath}/airtelMoneyStatusPayment.action",
	        type: "GET",
	        data: {
	        	merchant_reference: merchantNo
	        },
	        dataType: "json",
	        contentType: "application/json",
	        success: function(data) {
		        try{
		          var jo = JSON.stringify(data);
		          var jd = $.parseJSON(jo);
		          var ps = jd.payStatus;
		          if(ps == 'pending' || ps == 'TIP'){
		        	  wcactivity();
		          }else{
		        	  document.payment.submit();
		          }
				}catch(e){
					console.error(e);
				}
	        },
	        error: function() {
	        }
	      });
	}catch(err){
		console.log(err);
	}
}
</script>
</body>
</html>