<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
	<head>
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<meta name="description" content="">
		<meta name="keywords" content="Maan Sarovar, Sea Trans, Marine Insurance">	
		<meta name="author" content="">
		<title> ::: Madison General - Customer Selection ::: </title>
		<link rel="shortcut icon" href="<%=request.getContextPath()%>/images/favicon.ico" />	
		<script type="text/javascript" src="js/common.js"></script>
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/bootstrap/css//bootstrap.min.css" />
		<link href="<%=request.getContextPath()%>/bootstrap/css//style.css" rel="stylesheet" type="text/css" />
		
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/dataTables/css/dataTables.responsive.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/dataTables/css/dataTables.jqueryui.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/dataTables/css/dataTables.tableTools.css">
	<link href="${pageContext.request.contextPath}/dataTables/css/jquery-start-ui.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="${pageContext.request.contextPath}/dataTables/js/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/dataTables/js/jquery.dataTables.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/dataTables/js/dataTables.jqueryui.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/dataTables/js/dataTables.responsive.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/dataTables/js/dataTables.tableTools.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/dataTables/js/jquery.dataTables.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/dataTables/js/numeric-comma.js"></script>
		
		<script type="text/javascript">
		jQuery(function ($) {
				try {
					var data = $('#record').dataTable( {
						"lengthMenu": [[10, 25, 50, -1], [10, 25, 50, "All"]],
						"order": [[ 0, "asc" ]],
						"columnDefs": [ {
				          "targets": 'no-sort',
				          "orderable": false
					    } ],
						responsive: true
					});
				} catch(err){}
			} );
   		</script>
	</head>
<body>
<s:form name="customerSelection1" id="customerSelection" theme="simple" action="customerPopulateQuotation.action">
<div class="table0">
	<div class="tablerow" id="customerSearchList">
		<div class="panel panel-primary" style="overflow-x: visible;">
			<div class="panel-heading">
				<s:text name="Customer Selection" />
			</div>
			<div class="panel-body">
				<table class="display responsive no-wrap" id="record" width="100%" cellspacing="0" style="font-size: 12px;">
					<thead>
					<tr>
						<th width="5%" class="no-sort">S.No</th>
						<th width="5%"></th>
					<%--<s:if test='"CUSTOMER".equals(searchType) || "OC_CUSTOMER".equals(searchType)'>
							<th width="19%"><b>Customer Name</b></th>
						</s:if>
						<s:elseif test='"BROKER".equalsIgnoreCase(userType)'>
							<th width="19%"><b>Customer Name</b></th>
						</s:elseif>--%>
						<th width="19%"><b>Customer Name</b></th>
						<th width="19%"><b>P.O.Box</b></th>
						<th width="19%"><b>City/Town</b></th>
						<th width="19%"><b>Email</b></th>
						<th width="19%"><b>Core Application Code</b></th>
					</tr>
					</thead>
					<tbody>
						<s:iterator value="customerSelection" var="customerdetail" status="stat">
							<tr>
								<td><s:property value="#stat.count" /></td>   
							 	<td width="5%"><input type="radio" name="selects" onclick="customerDetail('<s:property value="%{#customerdetail.TITLE}"/>','<s:property value="%{#customerdetail.ADDRESS1}"/>','<s:property value="%{#customerdetail.ADDRESS2}"/>','<s:property value="%{#customerdetail.MOBILE}"/>','<s:property value="%{#customerdetail.EMIRATE}"/>','<s:property value="%{#customerdetail.POBOX}"/>','<s:property value="%{#customerdetail.FIRST_NAME}"/>','<s:property value="%{#customerdetail.MISSIPPI_CUSTOMER_CODE}"/>','<s:property value="%{#customerdetail.CUSTOMER_ID}"/>','<s:property value="%{#customerdetail.EMAIL}"/>','<s:property value="%{#customerdetail.CUST_AR_NO}"/>','<s:property value="%{#customerdetail.LAST_NAME}"/>','<s:property value="%{#customerdetail.DOB}"/>','<s:property value="%{#customerdetail.GENDER}"/>','<s:property value="%{#customerdetail.TELEPHONE}"/>','<s:property value="%{#customerdetail.OCCUPATION}"/>','<s:property value="%{#customerdetail.COUNTRY}"/>','<s:property value="%{#customerdetail.PASSPORT_NUMBER}"/>','<s:property value="%{#customerdetail.NRC1}"/>','<s:property value="%{#customerdetail.NRC2}"/>','<s:property value="%{#customerdetail.NRC3}"/>','<s:property value="%{#customerdetail.ALTERNATE_MOBILE}"/>','<s:property value="%{#customerdetail.CUSTOMER_TYPE}"/>','<s:property value="%{#customerdetail.COMPANY_REG_NO}"/>')"/></td>   				  
								<td width="19%"><s:property value="FIRST_NAME" /></td>
								<td width="19%"><s:property value="POBOX"  /></td>
								<td width="19%"><s:property value="CITY_NAME" /></td>
								<td width="19%"><s:property value="EMAIL" /></td>
								<td width="19%"><s:property value="MISSIPPI_CUSTOMER_CODE" /></td>
							</tr>
						</s:iterator>
					</tbody>
				</table>
				<s:hidden name="title" />
				<s:hidden name="custLastName" id="custLastName"/>
				<s:hidden name="custdob" id="custdob"/>
				<s:hidden name="gender" id="gender"/>
				<s:hidden name="occupation" id="occupation"/>
				<s:hidden name="custAlterMobileNo" id="custAlterMobileNo"/>
				<s:hidden name="custnrc1" id="custnrc1"/>
				<s:hidden name="custnrc2" id="custnrc2"></s:hidden>
				<s:hidden name="custnrc3" id="custnrc3"></s:hidden>
				<s:hidden name="custPassportNo" id="custPassportNo"></s:hidden>
				<s:hidden name="companyRegNo" id="companyRegNo"></s:hidden>
				<s:hidden name="customerType" id="customerType"/>
         		<s:hidden name="address1" />
         		<s:hidden name="address2" />
         		<s:hidden name="mobileNo" />
         		<s:hidden name="city" />
         		<s:hidden name="poBox" />
         		<s:hidden name="customerName" />
         		<s:hidden name="coreAppCode" />
         		<s:hidden name="email"/>
         		<s:hidden name="custArNo" />
         		<s:hidden name="customerId" />
         		<s:hidden name="searchType" />
			</div>
		</div>
	</div>
	<br/>
	<div class="tablerow" align="center">
		<s:submit type="button" name="close"  key="Close" cssClass="btn btn-sm btn-danger"/>&nbsp;&nbsp;&nbsp;
		<input type="button" name="submit" value="Submit" class="btn btn-sm btn-success" onclick="fnSubmit()"/>
	</div>
</div>
</s:form>
<script type="text/javascript">
function stopRKey(evt) { 
	var evt = (evt) ? evt : ((event) ? event : null); 
	var node = (evt.target) ? evt.target : ((evt.srcElement) ? evt.srcElement : null); 
	if ((evt.keyCode == 13) && (node.type=="text")) { return false; } 
} 

document.onkeypress = stopRKey;

function searchList() {
	ajaxLoading(); 
	var search=document.getElementById('searchValue').value; 
	postRequest('${pageContext.request.contextPath}/customerSelectionQuotation.action?searchValue='+search+'&brokerCode='+'<s:property value="brokerCode"/>','customerSearchList');
}

function coreSearchList() { 
	ajaxLoading();
	var search=document.getElementById('coreSearchValue').value; 
	postRequest('${pageContext.request.contextPath}/customerSelectionQuotation.action?coreSearchValue='+search+'&brokerCode=<s:property value="brokerCode"/>&channelType=<s:property value="channelType"/>&searchType=<s:property value="searchType"/>','customerSearchList');
}

function ajaxLoading() {
	document.getElementById("customerSearchList").innerHTML = '<img alt="Loading" src="${pageContext.request.contextPath}/images/ajax-loader.gif" class="ajaxLoader">';
}

function customerDetail(title,address1,address2,mobileNo,city,poBox,customerName,coreAppCode,customerId,email,custArNo,custLastName,custdob,gender,telephone,occupation,country,custPassportNo,nrc1,nrc2,nrc3,custAlterMobileNo,customerType,companyRegNo) {
	//alert(title+"  "+address1+" "+address2+" "+mobileNo+" "+city+" "+poBox+" "+customerName+" "+coreAppCode+" "+customerId+" "+email+" "+custArNo+" "+custLastName+" "+custdob+" "+gender+" "+telephone+" "+occupation+" "+country+" "+custPassportNo+" "+nrc1+" "+nrc2+" "+nrc3+" "+custAlterMobileNo+" "+customerType+" "+companyRegNo);
	document.customerSelection1.title.value=title;
	document.customerSelection1.address1.value=address1;
	document.customerSelection1.address2.value=address2;
	document.customerSelection1.mobileNo.value=mobileNo;
	document.customerSelection1.city.value=city;
	document.customerSelection1.poBox.value=poBox;
	document.customerSelection1.customerName.value=customerName;
	document.customerSelection1.coreAppCode.value=coreAppCode;
	document.customerSelection1.customerId.value=customerId;
	document.customerSelection1.email.value=email;
	document.customerSelection1.custArNo.value=custArNo;
	document.customerSelection1.custLastName.value=custLastName;
	document.customerSelection1.custdob.value=custdob;
	document.customerSelection1.gender.value=gender;
	document.customerSelection1.occupation.value=occupation;
	document.customerSelection1.custPassportNo.value=custPassportNo;
	document.customerSelection1.custnrc1.value=nrc1;
	document.customerSelection1.custnrc2.value=nrc2;
	document.customerSelection1.custnrc3.value=nrc3;
	document.customerSelection1.custAlterMobileNo.value=custAlterMobileNo;
	document.customerSelection1.customerType.value=customerType;
	document.customerSelection1.companyRegNo.value=companyRegNo;
}

function fnSubmit() { 
	if(!document.customerSelection1.customerId.value) {
		alert("Please Select Customer");
	} else {
		var searchType = document.customerSelection1.searchType.value;
		if("CUSTOMER" == searchType) {
			if("0"==document.customerSelection1.customerId.value) {
				document.customerSelection1.customerId.value = "";
			}
			window.opener.document.getElementById("title").value=document.customerSelection1.title.value;
			window.opener.document.getElementById("address1").value=document.customerSelection1.address1.value;
			window.opener.document.getElementById("address2").value=document.customerSelection1.address2.value;
			window.opener.document.getElementById("mobileNo").value=document.customerSelection1.mobileNo.value;
			window.opener.document.getElementById("city").value=document.customerSelection1.city.value;
			window.opener.document.getElementById("poBox").value=document.customerSelection1.poBox.value;
			window.opener.document.getElementById("customerName").value=document.customerSelection1.customerName.value;
			window.opener.document.getElementById("coreAppCode").value=document.customerSelection1.coreAppCode.value;
			window.opener.document.getElementById("customerId").value=document.customerSelection1.customerId.value;
			window.opener.document.getElementById("email").value=document.customerSelection1.email.value;
			window.opener.document.getElementById("custLastName").value=document.customerSelection1.custLastName.value;
			window.opener.document.getElementById("custdob").value=document.customerSelection1.custdob.value;
			window.opener.document.getElementsByName("gender").value=document.customerSelection1.gender.value;
			window.opener.document.getElementById("occupation").value=document.customerSelection1.occupation.value;
			window.opener.document.getElementById("custPassportNo").value=document.customerSelection1.custPassportNo.value;
			window.opener.document.getElementById("custnrc1").value=document.customerSelection1.custnrc1.value;
			window.opener.document.getElementById("custnrc2").value=document.customerSelection1.custnrc2.value;
			window.opener.document.getElementById("custnrc3").value=document.customerSelection1.custnrc3.value;
			window.opener.document.getElementById("custAlterMobileNo").value=document.customerSelection1.custAlterMobileNo.value;
			window.opener.document.getElementsByName("customerType").value=document.customerSelection1.customerType.value;
			window.opener.document.getElementById("companyRegNo").value=document.customerSelection1.companyRegNo.value;
			/*<s:if test='%{productId==openCover && !((quoteStatus=="RA")||(endTypeId!=null && endTypeId.length() > 0))}'>
				var sescustomerId = '<s:property value="#session.customer_id"/>';
				var customerId = document.customerSelection.customerId.value;
				if(customerId == sescustomerId) {
					window.opener.document.getElementById("editCustomerNO").checked = true;
				}
				else {
					window.opener.document.getElementById("editCustomerYES").checked = true;
				}
				window.opener.editCustomerInfo();
			</s:if>*/
			window.close();
		} else if("BROKER" == searchType) {
			window.opener.document.getElementById("address1").value=document.customerSelection1.address1.value;
			window.opener.document.getElementById("address2").value=document.customerSelection1.address2.value;
			window.opener.document.getElementById("telephone").value=document.customerSelection1.mobileNo.value;
			window.opener.document.getElementById("emirate").value=document.customerSelection1.city.value;
			window.opener.document.getElementById("pobox").value=document.customerSelection1.poBox.value;
			window.opener.document.getElementById("borganization").value=document.customerSelection1.customerName.value;
			window.opener.document.getElementById("bcode").value=document.customerSelection1.coreAppCode.value;
			window.opener.document.getElementById("custLastName").value=document.customerSelection1.custLastName.value;
			window.opener.document.getElementById("custdob").value=document.customerSelection1.custdob.value;
			window.opener.document.getElementsByName("gender").value=document.customerSelection1.gender.value;
			window.opener.document.getElementById("occupation").value=document.customerSelection1.occupation.value;
			window.opener.document.getElementById("custPassportNo").value=document.customerSelection1.custPassportNo.value;
			window.opener.document.getElementById("custnrc1").value=document.customerSelection1.custnrc1.value;
			window.opener.document.getElementById("custnrc2").value=document.customerSelection1.custnrc2.value;
			window.opener.document.getElementById("custnrc3").value=document.customerSelection1.custnrc3.value;
			window.opener.document.getElementById("custAlterMobileNo").value=document.customerSelection1.custAlterMobileNo.value;
			window.opener.document.getElementsByName("customerType").value=document.customerSelection1.customerType.value;
			window.opener.document.getElementById("companyRegNo").value=document.customerSelection1.companyRegNo.value;
			window.close();
		} else if("OC_CUSTOMER" == searchType) {
			var coreCustomerId = document.customerSelection1.coreAppCode.value;
			document.location = '${pageContext.request.contextPath}/premiumOpenCover/newCustomer.jsp?corecustomers='+coreCustomerId+'&coreMode=edit&mode=new&brokerId=<s:property value="brokerId"/>';
		}else{
			window.opener.document.getElementById("title").value=document.customerSelection1.title.value;
			window.opener.document.getElementById("address1").value=document.customerSelection1.address1.value;
			window.opener.document.getElementById("address2").value=document.customerSelection1.address2.value;
			window.opener.document.getElementById("mobileNo").value=document.customerSelection1.mobileNo.value;
			window.opener.document.getElementById("city").value=document.customerSelection1.city.value;
			window.opener.document.getElementById("poBox").value=document.customerSelection1.poBox.value;
			window.opener.document.getElementById("customerName").value=document.customerSelection1.customerName.value;
			window.opener.document.getElementById("coreAppCode").value=document.customerSelection1.coreAppCode.value;
			window.opener.document.getElementById("customerId").value=document.customerSelection1.customerId.value;
			window.opener.document.getElementById("email").value=document.customerSelection1.email.value;
			window.opener.document.getElementById("custLastName").value=document.customerSelection1.custLastName.value;
			window.opener.document.getElementById("custdob").value=document.customerSelection1.custdob.value;
			window.opener.document.getElementsByName("gender").value=document.customerSelection1.gender.value;
			window.opener.document.getElementById("occupation").value=document.customerSelection1.occupation.value;
			window.opener.document.getElementById("custPassportNo").value=document.customerSelection1.custPassportNo.value;
			window.opener.document.getElementById("custnrc1").value=document.customerSelection1.custnrc1.value;
			window.opener.document.getElementById("custnrc2").value=document.customerSelection1.custnrc2.value;
			window.opener.document.getElementById("custnrc3").value=document.customerSelection1.custnrc3.value;
			window.opener.document.getElementById("custAlterMobileNo").value=document.customerSelection1.custAlterMobileNo.value;
			window.opener.document.getElementsByName("customerType").value=document.customerSelection1.customerType.value;
			window.opener.document.getElementById("companyRegNo").value=document.customerSelection1.companyRegNo.value;
			window.close();
		}
			
	}
}

function trim(text) {
	return text.replace(/^\s+|\s+$/g, "");
}
</script>
</body>
</html>