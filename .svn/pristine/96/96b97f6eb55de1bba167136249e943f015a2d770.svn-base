<%@ page language="java"
	isELIgnored="false"%>
<%
	String path = request.getContextPath();
%>
<html>
<head>
<title>** XYZ Insurance - Client Login **</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<style type="text/css">
<!--
	.style1 {color: #FF0000}
-->
</style>
</head>

<body>
<%@ include file="../admin/header.jsp"%>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
<%@ include file="../admin/left.jsp"%></tr></table>
</td>
<td width="75%" valign="top" valign="top">
<table width="100%" border="0" cellpadding="0" cellspacing="0">
<tr calss="heading"><td width="100%" class="heading"><strong>CUSTOMER CREATION</strong></td></tr>
</table>

<form name="Customer" method="post">
<br><br><br>

<table width="95%"  border="0" cellspacing="1" cellpadding="0" align='center'>
<tr> <td align="center" class="text"> <strong>Customer details updated sucessfully </strong>	</td>	</tr>
</table>
<br> <br>
<table width="50%" border="0" align="center" cellpadding="0" cellspacing="0">
<tr align="center">
	<td>
		<!-- <a href= "#" class="buttonsMenu" >
		<img src="<%=path%>/images/Cancel.gif" border="0">		</a>	-->
	</td>
	<td>
		<a href="#" onClick= "return customerProceed()" class="buttonsMenu" >
		<img src="<%=path%>/images/Submit.gif" border="0">		</a>	
	</td>
</tr>
</table>
<input type="hidden" name ="requestfrom" />
</form>
</td>
</tr>
</body>
</html>

<script>

function customerProceed()
{
	document.Customer.requestfrom.value="AdminCistomerConfirm";
	document.Customer.action = "addCustomer.jsp";
	document.Customer.submit();
}

</script>


