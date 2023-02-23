<%@ page language="java"
	isELIgnored="false"%>
<%@ include file="/OfficeConfig/css/cssInclude.jsp"%>
<html>
<%
	String path = request.getContextPath();
%>
<head>
<title> Choose Customer</title>
</head>

<jsp:useBean id= "OSB" class = "com.maan.Office.DAO.OfficeShieldBean">
</jsp:useBean>
<%
	String customerDetails[][] = new String[0][0];
	String brokerLogin = "";
	brokerLogin = request.getParameter("brokerId");
	brokerLogin = brokerLogin == null ? "" : brokerLogin;
	customerDetails = OSB.getExistingCustomers(brokerLogin);
%>
<body onunload="window.opener.top.CustomerSelection();">
<form name="CustomerSelection" method="post">

<table width="95%"  border="0" cellspacing="1" cellpadding="0" align='center'>
<tr class="heading"> 
	<td class="heading" width="20%"> Customer Id </td>
	<td class="heading" width="60%"> Customer Name </td> 
	<td class="heading" width="15%"> Contact No </td>
</tr>
</table>
<%
	if(customerDetails.length > 0 )
	{
%>
<div STYLE="overflow: auto; width:100%; height:270; border-left: 0px gray solid;padding:1px; padding-left:5px; margin: 1px">
<table width="95%"  border="0" cellspacing="1" cellpadding="0" align='center'>
<%
	for(int i=0;i<customerDetails.length;i++)
	{
%>
	<tr> 
		<td width="20%"> 
		<input type='hidden' name='customerIds<%=i%>' value='<%=customerDetails[i][4]%>' />
		<input type='hidden' name='customerNames<%=i%>' value='<%=customerDetails[i][4]%>' />
		<input type="radio" name="customerName" <%=customerDetails[i][4].equalsIgnoreCase(request.getParameter("customerId"))?"checked":""%> onClick="setCustomer('<%=i%>')">
		<%=customerDetails[i][4]%> 
		</td> 
		<td width="60%"> <%=customerDetails[i][0]%> </td> 
		<td width="15%"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<%=customerDetails[i][3]==null ? "" : customerDetails[i][3]%> 
		</td>
	</tr>
<%
	}
%>
<tr>
</tr>
</table>
</div>
<%
	}
	else
	{
%>
	<table width="95%"  border="0" cellspacing="1" cellpadding="0" align='center'>
	<tr class="heading"> 
		<td class="heading" align="center" width="20%"> <font color="red"> There is no customer for this broker </font></td>
	</tr>
	</table>
<%
	}
%>
<br>
<table width="50%" border="0" align="center" cellpadding="0" cellspacing="0">
<tr align="center">
	<td>
	<a href= "#" onClick='window.close()' class="buttonsMenu" >
	<img src="<%=path%>/images/Cancel.gif" border="0">		</a>	</td>
	<td>
	<% 
		if(customerDetails.length > 0)
		{
	%>
		<a href="#" onClick= "return settingValues()" class="buttonsMenu" >
		<img src="<%=path%>/images/Submit.gif" border="0">		</a>	
	<%
		}
	%>
	</td>
</tr>
</table>

<input type="hidden" name="customerName">
<input type="hidden" name="customerId">
<input type="hidden" name="brokerId" value="<%=request.getParameter("brokerId")%>">
</form>
<script>

function settingValues()
{	
	var custId = document.CustomerSelection.customerId.value;
	window.opener.document.Customer.customerId.value = document.CustomerSelection.customerId.value;
}

function setCustomer(ids)
{
	var customername = eval("document.CustomerSelection.customerNames"+ids+".value");
	var customerid = eval("document.CustomerSelection.customerIds"+ids+".value");
	document.CustomerSelection.customerName.value = customername;
	document.CustomerSelection.customerId.value = customerid;
}

</script>
</body>
</html>                     
