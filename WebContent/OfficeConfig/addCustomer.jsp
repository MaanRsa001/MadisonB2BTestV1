<%@ page language="java"
	pageEncoding="ISO-8859-1"
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

<jsp:useBean id= "adminBean" class = "com.maan.admin.DAO.AdminBean">
<jsp:setProperty name= "adminBean" property = "*"/>
</jsp:useBean>

<jsp:useBean id= "OSB" class = "com.maan.Office.DAO.OfficeShieldBean">
</jsp:useBean>

<body>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
<tr>
<td>
<%@ include file="../admin/header.jsp"%></table></tr></td></tr></table>
</td>
</tr>
<tr>
<td>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
<tr>
<td width="25%" valign="top">
<%@ include file="../admin/left.jsp"%></tr></table>
</td>
<td width="75%" valign="top" valign="top">
<table width="100%" border="0" cellpadding="0" cellspacing="0">
<tr calss="heading"><td width="100%" class="heading"><strong>CUSTOMER CREATION</strong></td></tr>
</table>
<%
	
	String[][] BrokerNames = new String[0][0];
	String branch = "";
	String adminCid = "";
	String emirates[][] = new String[0][0];
	String countrys[][] = new String[0][0];
	String custName = "";
	String custAddress = "";
	String custPhone = "";
	String custMailId = "";
	String custCity = "";
	String custCountry = "";
	String custPOBox = "";
	String selectBrokers = "";
	String officeProductId ="30"; // Hard Code
	
	adminCid = (String) session.getAttribute("AdminCountryId");
	branch = (String) session.getAttribute("LoginBranchCode");
	BrokerNames = adminBean.getAllBrokers(branch);
	
	emirates = OSB.emirateCollection(adminCid);
	countrys = OSB.getCountryNameWithID(adminCid);
	
	custName = request.getParameter("custName");
	custAddress = request.getParameter("custAddress");
	custPhone = request.getParameter("custPhone");
	custMailId = request.getParameter("custMailId");
	custCity = request.getParameter("custCity");
	custCountry = request.getParameter("custCountry");
	custPOBox = request.getParameter("custPOBox");
	selectBrokers = request.getParameter("selectBrokers");
	
	custName = custName == null ? "" : custName;
	custAddress = custAddress == null ? "" : custAddress;
	custPhone = custPhone == null ? "" : custPhone;
	custMailId = custMailId == null ? "" : custMailId;
	custCity = custCity == null ? "" : custCity;
	custCountry = custCountry == null ? "" : custCountry;
	custPOBox = custPOBox == null ? "" : custPOBox;
	selectBrokers = selectBrokers == null ? "" : selectBrokers;
%>

<%@include file="../HomeInsurance/ErrorPage.jsp"%>

<form name="Customer" method="post">
<table>
<tr>
<td class="text"> <strong> Select Broker </strong> </td>
<td class="">
	<select name="selectBrokers" onChange="return setBrokerAgency(this.value)">
	<option value="select" > Select Broker</option>
	<%
		for(int i=0;i<BrokerNames.length;i++)
		{
	%>
		<option value="<%=BrokerNames[i][1]%>" <%= selectBrokers == null?"":(selectBrokers.trim().equalsIgnoreCase(BrokerNames[i][1].trim())?"selected":"")%> >
		<%=BrokerNames[i][0]%>(<%=BrokerNames[i][1]%>)</option>
	<%
		}
	%>
	</select>
</td>
<td> <input name="chooseCustomer" value="....." onClick="existingCustomerPopup()" title="View Existing Customer" type="button"> </td>
</tr>
</table>

<br>

<table width="100%"  border="0" cellspacing="1" cellpadding="0" align='center'>
<tr class="heading"> 
	<td class="heading" width="100%"> Customer Information </td> 
</tr>
</table>
<br>
<div id="customerInformation" name="customerInformation">
<table width="100%"  border="0" cellspacing="1" cellpadding="0" align='center'>
<tr> 
	<td width="15%" > <strong>&nbsp;Customer Name </strong></td> 
	<td width="40%"> 
	<input type="text" name="custName" size="25" maxlength="50" value="<%=custName %>" /> 
	</td> 
	<td width="15%" > <strong>Registered address in the UAE </strong> </td>
	<td width="40%"> 
	<input type="text" name="custAddress" size="25" maxlength="50" value="<%=custAddress %>" /> 
	</td> 
</tr>

<tr> 
	<td width="15%" > <strong>&nbsp;Emirates </strong></td> 
	<td width="40%"> 
	<select name="custCity" >
	<option value="select">Select Emirate</option>
	<%
		for(int i=0;i<emirates.length;i++)
		{ 
	%>
		<option value="<%=emirates[i][0]%>" <%= custCity == null?"":(custCity.trim().equalsIgnoreCase(emirates[i][0].trim())?"selected":"")%>>
		<%=emirates[i][0]%></option>
	<%
		}
	%>
	</select>
	</td> 
	<td width="15%" > <strong>Country </strong> </td>
	<td width="40%"> 
	<SELECT name="custCountry">
	<%	
		for(int i=0; i<countrys.length; i++)
		{
	%>
		<option value="<%=countrys[i][0]%>>" selected ><%=countrys[i][0]%></option>
	<%
		}
	%>
	</select>
	</td> 
</tr>

<tr> 
	<td width="15%" > <strong> &nbsp;P.O Box</strong></td> 
	<td width="40%"> 
	<input type="text" name="custPOBox" size="25" maxlength="10" value="<%=custPOBox %>" /> 
	</td> 
	<td width="15%" > <strong> Phone No</strong> </td>
	<td width="40%"> 
	<input type="text" name="custPhone" size="25" maxlength="15" value="<%=custPhone %>" /> 
	</td> 
</tr>

<tr> 
	<td width="15%" > <strong> &nbsp;E-Mail Id</strong></td> 
	<td width="40%"> 
	<input type="text" name="custMailId" size="25" maxlength="40" value="<%=custMailId %>" /> 
	</td> 
	<td width="15%" >  </td>
	<td width="40%">   </td> 
</tr>
</table>
</div>

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


<input type="hidden" name ="brokerAgencyCode" />
<input type="hidden" name ="brokerId" />
<input type="hidden" name ="customerId" />
<input type="hidden" name ="requestfrom" />
<input type="hidden" name ="officeProductId" value="<%=officeProductId%>" />
</form>
</td>
</tr>
</body>
</html>

<script>

function customerProceed()
{
	document.Customer.requestfrom.value="AdminCustomerCreation";
	document.Customer.action = "newOfficeCustomer.Home";
	document.Customer.submit();
}

function setBrokerAgency(brokerId)
{
	document.Customer.brokerId.value = brokerId;
}

function existingCustomerPopup()
{
	var broker = document.Customer.brokerId.value;
	
	if(broker.length > 0 && broker != 'select')
	{
		var URL = '';
		var windowName = "";
		URL='chooseCustomer.jsp?brokerId='+document.Customer.brokerId.value+'&customerId='+document.Customer.customerId.value;
		var width  = screen.availWidth;
		var height = screen.availHeight;
		var h=350;
		var w=650;
		var features =
		'width='          + w +
		',height='		  + h +
		',left='		  + ((width - w - 0) * .4)  +
		',top='			  + ((height - h - 0) * .4) +
		',directories=no' +
		',location=no'	  +
		',menubar=no'	  +
		',scrollbars=yes' +
		',status=yes'	  +
		',toolbar=no'	  +
		',resizable=false';
		var strOpen = window.open (URL, windowName, features);
		strOpen.focus();
		return false;
	}
	else
	{
		alert("Please Select Broker");
	}
}

/** Customer Information From Database **/
function CustomerSelection()
{	
	var http;
    var browser = navigator.appName;  
    if(window.XMLHttpRequest)
	{
         http = new XMLHttpRequest();
    }
    else
	{
        if (window.ActiveXObject)
		{
	       	http = new ActiveXObject("Microsoft.XMLHTTP");
        }
    }

	var customerId = document.getElementById("customerId").value;	
	var urlvalue = '<%=path%>/servlet/AdminCustomerCreationOS?customerId='+customerId; 
	http.open('post',urlvalue ,true);	
    http.onreadystatechange = function() 
	{	
        if(http.readyState == 4 && http.status == 200) 
		{
	        var response = http.responseText; 
            if(response)
			{	
               document.getElementById("CustomerInformation").innerHTML = response;
            }
        }	
		Addressinsure1(); 
	}	
	http.send(null);
}

/** Customer Information From Database **/


</script>


