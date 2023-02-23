<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style>
	#loading {
	  width: 200%;
	  height: 200%;
	  top: 0px;
	  left: 0px;
	  position: fixed;
	  display: block;
	  opacity: 0.7;
	  background-color: #fff;
	  z-index: 100;
	  text-align: center;
	}
	
	#loading-image {
	  position: absolute;
	  top: 20%;
	  left: 45%;
	  z-index: 100;
	}
</style>
</head>
<body>
	<form method="post" action="https://testsecureacceptance.cybersource.com/pay" id="form1" name="form1">
		<div id="loading" style="width:100%;"><img id="loading-image" src="${pageContext.request.contextPath}/images/ajax-loader-bar.gif" id="loader"/>PLEASE DON'T REFRESH THE PAGE</div>
		
		<s:hidden name="amount" />
		<s:hidden name="reference_number" />
		<s:hidden name="signed_field_names" />
		<s:hidden name="profile_id" />
		<s:hidden name="signed_date_time" />
		<s:hidden name="transaction_type" />
		<s:hidden name="locale" />
		<s:hidden name="transaction_uuid" />
		<s:hidden name="access_key" />
		<s:hidden name="unsigned_field_names" />
		<s:hidden name="currency" />
		<s:hidden name="signature" />
		
		<s:hidden name="recurring_amount" />
		<s:hidden name="recurring_frequency" />
		<s:hidden name="recurring_start_date" />
		<s:hidden name="recurring_number_of_installments" />
		
		<s:hidden name="bill_to_forename" />
		<s:hidden name="bill_to_surname" />
		<s:hidden name="bill_to_address_line1" />
		<s:hidden name="bill_to_address_city" />
		<s:hidden name="bill_to_address_country" />
		<s:hidden name="bill_to_address_postal_code" />
		<s:hidden name="bill_to_email" />
		<s:hidden name="recurring_automatic_renew"></s:hidden>
	</form>
	<br/>
</body>
<script type="text/javascript">
	document.form1.submit();
</script>

</html>