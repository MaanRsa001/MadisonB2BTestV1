<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>
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
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/assets/CssLibrary/bootstrap.min.css" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/assets/CssLibrary/glyphicon.min.css" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/StyleV1.css" />
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/all.css" /> 
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/bootstrap/css/datepicker3.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/bootstrap/css/bootstrap-datetimepicker.css">	 
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/assets/CssLibrary/dataTables.bootstrap.min.css">  
   <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/dataTables/css/dataTables.responsive.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/dataTables/css/dataTables.jqueryui.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/dataTables/css/dataTables.tableTools.css">
	<link href="${pageContext.request.contextPath}/dataTables/css/jquery-start-ui.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="${pageContext.request.contextPath}/dataTables/js/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/dataTables/js/jquery.dataTables.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/dataTables/js/dataTables.jqueryui.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/dataTables/js/dataTables.responsive.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/dataTables/js/dataTables.tableTools.js"></script>
    
	<script type="text/javascript">
		$(document).ready(function () {
 		  $('#tadaTable').DataTable({
	    	  "responsive": true,
	        "columnDefs": {
	          "orderable": false
	        },
	        language: {
	          //customize pagination prev and next buttons: use arrows instead of words
	          'paginate': {
	            'previous': '<span><i class="fas fa-chevron-circle-left"></i></span>',
	            'next': '<span><i class="fas fa-chevron-circle-right"></i></span>'
	          },
	          //customize number of elements to be displayed
	          "lengthMenu": '<select class="form-control input-sm">' +
	            '<option value="10">10</option>' +
	            '<option value="20">20</option>' +
	            '<option value="30">30</option>' +
	            '<option value="40">40</option>' +
	            '<option value="50">50</option>' +
	            '<option value="-1">All</option>' +
	            '</select>'
	        }
	      })
	    });
	    
    </script>
	</head>
<body>
<s:form name="frm"  theme="simple">
<div class="table0">
	<div class="tablerow" id="customerSearchList">
		<div class="panel panel-primary" style="overflow-x: visible;">
			<div class="panel-heading">
				<s:text name="Customer Selection" />
			</div>
			<div class="panel-body">
				<table width="100%">
					<tr>
						<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
							<span style="color:red;"><s:actionerror/></span>
						</div>
							<div class="row" >
								<div class="col-xs-12 col-sm-12 col-md-8 col-lg-8">
								</div>
								<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
									<div class="col-xs-1">
										<div class="text">
									 		<s:text name="customer.searchBy" />
									 	</div>
								 	</div>
								 	<div class="col-xs-3">
									 	<div class="tbox">
									 		<s:select list="#{'nrcmobile':'NRC Or Mobile No','mobileemail':'Passport Or Mobile' }" name="searchBy" id="searchBy" headerKey="" headerValue="--Select--" cssClass="inputBoxS tooltipContent" onchange="getSearch(this.value);"></s:select>
									 	</div>
								 	</div>
								 	<div class="col-xs-1">
									 	<div class="text" id="nrcid">
									 		<s:text name="NRC" />
									 	</div>
									 	<div class="text" id="emailid" style="display:none">
									 		<s:text name="Passport" />
									 	</div>
								 	</div>
								 	<div class="col-xs-3">
									 	<div class="tbox">
									 		<s:textfield name="searchnrc" id="searchnrc" cssClass="inputBox tooltipContent"/>
									 	</div>
									</div>
									<div class="col-xs-1">
									 	<div class="text">
									 		<s:text name="Mobile No" />
									 	</div>
								 	</div>
								 	<div class="col-xs-3">
									 	<div class="tbox">
									 		<s:textfield name="searchmobileNo" id="searchmobileNo" cssClass="inputBox tooltipContent"/>
									 	</div>
									</div>
								</div>
							</div>
							<div class="tablerow" align="center">
								
								 		<button type="button" onclick="searchList();" class="btn btn-sm btn-info">Search</button>
							</div>
					</tr>
				</table>
				<br class="clear"/>
					<table class="table table-bordered table-hover" cellspacing="0" width="100%" id="tadaTable">
					<thead class="bluecolortable" >
						<tr>
							<th><s:text name="S No." /></th>
							<th><s:text name="Select" /></th>
							<th><s:text name="Policy Holder Name" /></th>
							<th><s:text name="Email Id" /></th>
							<th><s:text name="Contact No." /></th>
							<th><s:text name="NRC." /></th>
							<th><s:text name="Passport No" /></th>
						
						</tr>
					</thead>
			      <tbody class="rowevencolor">
			    	<s:iterator value="searchCustList" var="view" status="status">
				        <tr>
				        	<td align="center"><s:property value="#status.count" /></td>
				        	<td align="center">
					            <div class="action_icons">
					            <input type="radio" name="selects" onclick="customerDetail('<s:property value="#view.CUSTOMER_ID"/>','<s:property value="#view.TITLE"/>','<s:property value="#view.FIRST_NAME"/>','<s:property value="#view.LAST_NAME"/>','<s:property value="#view.EMAIL"/>','<s:property value="#view.MOBILE"/>','<s:property value="#view.CUSTOMER_TYPE"/>')"/>
					            </div>
							</td>
							<td align="center"><s:property value="#view.FIRST_NAME" /></td>
							<td align="center"><s:property value="#view.EMAIL" /> </td>
							<td align="center"><s:property value="#view.MOBILE" /></td>
							<td align="center"><s:property value="#view.NRC" /></td>
							<td align="center"><s:property value="#view.PASSPORT_NUMBER" /></td>
						
				        </tr>
			        </s:iterator>
				</tbody>
			   </table>
			</div>
		</div>
	</div>
	<br/>
	<div class="tablerow" align="center">
		<input type="button" onclick="window.close()"  class="btn btn-sm btn-danger" value="Close" >&nbsp;&nbsp;&nbsp;
		<button type="button" onclick="fnSubmit();" class="btn btn-sm btn-info">Submit</button>
		<!-- <input type="button" name="submit" value="Submit" class="btn btn-sm btn-success" onclick="fnSubmit()"/> -->
	</div>
	
</div>

<s:hidden name="customerId" id="customerId"></s:hidden>
<s:hidden name="title" id="title"></s:hidden>
<s:hidden name="customerName" id="customerName"></s:hidden>
<s:hidden name="custLastName" id="custLastName"></s:hidden>
<s:hidden name="email" id="email"></s:hidden>
<s:hidden name="mobileNo" id="mobileNo"></s:hidden>
<s:hidden name="customerType" id="customerType"></s:hidden>
</s:form>

<script type="text/javascript">
function stopRKey(evt) { 
	var evt = (evt) ? evt : ((event) ? event : null); 
	var node = (evt.target) ? evt.target : ((evt.srcElement) ? evt.srcElement : null); 
	if ((evt.keyCode == 13) && (node.type=="text")) { return false; } 
} 

document.onkeypress = stopRKey;

function searchList() {
	document.frm.action = '${pageContext.request.contextPath}/searchCustomer.action';
	document.frm.submit();
	
}

function coreSearchList() {	
	var search=""; 
	try{
		search=document.getElementById('coreSearchValue').value;
	}catch (e) {
		search=document.customerSelection.searchValue.value;
	}
	postRequest('<%=request.getContextPath()%>/customerSelectionQuotation.action?coreSearchValue='+search+'&brokerCode=<s:property value="brokerCode"/>&channelType=<s:property value="channelType"/>&searchType=<s:property value="searchType"/>','customerSearchList');
	ajaxLoading();
}

function ajaxLoading() {
	document.getElementById("customerSearchList").innerHTML = '<img alt="Loading" src="<%=request.getContextPath()%>/images/ajax-loader.gif" class="ajaxLoader">';
}
function brokerDetail(bcode,borg,groupId,missipiId,commission,commissionPer,title,contactPerson,contactPersonNo,contactOccupation,address1,address2,city,country,phone,fax,mobileNo,email){
	document.frm.customerId.value=bcode;
	document.frm.bcode.value=bcode;
	document.frm.broOrganisation.value=borg;
	document.frm.groupId.value=groupId;
	document.frm.missippiId.value=missipiId;
	document.frm.commission.value=commission;
	document.frm.commissionPer.value=commissionPer;

	document.frm.title.value=title;
	document.frm.customerName.value=contactPerson;
	document.frm.occupation.value=contactOccupation;
	document.frm.address1.value=address1;
	document.frm.address2.value=address2;
	document.frm.city.value=city;
	document.frm.country.value=country;
	document.frm.phone.value=phone;
	document.frm.fax.value=fax;
	document.frm.mobileNo.value=mobileNo;
	document.frm.email.value=email;
	
}
function customerDetail(customerId,title,customerName,custLastName,email,mobileNo,customerType) { 
	document.frm.customerId.value =customerId;
	document.frm.title.value=title;
	document.frm.customerName.value=customerName;
	document.frm.custLastName.value=custLastName;
	document.frm.email.value=email;
	document.frm.mobileNo.value=mobileNo;
	document.frm.customerType.value=customerType;

	
}

function fnSubmit() { 

		if("0"==document.frm.customerId.value) {
			document.frm.customerId.value = "";
		}
		window.opener.document.getElementById("customerId").value=document.frm.customerId.value;
		window.opener.document.getElementById("title").value=document.frm.title.value;
		window.opener.document.getElementById("customerName").value=document.frm.customerName.value;
		window.opener.document.getElementById("custLastName").value=document.frm.custLastName.value;
		window.opener.document.getElementById("email").value=document.frm.email.value;
		window.opener.document.getElementById("mobileNo").value=document.frm.mobileNo.value;
		window.opener.document.getElementById("customerType").value=document.frm.customerType.value;
	
		
		window.close();
		
}

function trim(text) {
	return text.replace(/^\s+|\s+$/g, "");
}
getSearch('<s:property value="searchBy"/>');
function getSearch(val){
	if(val=="nrcmobile"){
		document.getElementById("nrcid").style.display = "inline";
		document.getElementById("emailid").style.display = "none";
	}else{
		document.getElementById("nrcid").style.display = "none";
		document.getElementById("emailid").style.display = "inline";
	}
}
</script>
</body>
</html>