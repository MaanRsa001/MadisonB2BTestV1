<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/struts-tags" prefix="s"%>
<html>
<head>
<style type="text/css">
	#myDIV {
	    overflow-x:scroll;
	    margin: auto:
	}
           }
</style>
	<SCRIPT language=Javascript>
		function isNumberKey(evt) {
			var charCode = (evt.which) ? evt.which : event.keyCode;
			if (charCode != 46 && charCode > 31 
			&& (charCode < 48 || charCode > 57))
			return false;
			return true;
		}
	</SCRIPT>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/dataTables/css/dataTables.responsive.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/dataTables/css/dataTables.jqueryui.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/dataTables/css/dataTables.tableTools.css">
	<script type="text/javascript" src="${pageContext.request.contextPath}/dataTables/js/dataTables.responsive.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/dataTables/js/dataTables.tableTools.js"></script>
	
<%-- 	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/dataTables/css/dataTables.responsive.css"> --%>
<%-- 	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/dataTables/css/dataTables.jqueryui.css"> --%>
<%-- 	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/dataTables/css/dataTables.tableTools.css"> --%>
<%-- 	<link href="<%=request.getContextPath()%>/dataTables/css/jquery-start-ui.css" rel="stylesheet" type="text/css" /> --%>
<%-- 	<script type="text/javascript" src="<%=request.getContextPath()%>/dataTables/js/jquery-1.11.1.min.js"></script> --%>
<%-- 	<script type="text/javascript" src="<%=request.getContextPath()%>/dataTables/js/jquery.dataTables.min.js"></script> --%>
<%-- 	<script type="text/javascript" src="<%=request.getContextPath()%>/dataTables/js/dataTables.jqueryui.js"></script> --%>
<%-- 	<script type="text/javascript" src="<%=request.getContextPath()%>/dataTables/js/dataTables.responsive.js"></script> --%>
<%-- 	<script type="text/javascript" src="<%=request.getContextPath()%>/dataTables/js/dataTables.tableTools.js"></script> --%>
	<script type="text/javascript">
	$(document).ready(function () {
		  $('#tadaTable').DataTable({
	    	  "responsive": true,
	        "columnDefs": [
	          { "orderable": false, "targets": 6 },
	          { "responsivePriority": 1, targets: 0 },
	            { "responsivePriority": 10001, targets: 4 },
	            { "responsivePriority": 2, targets: -2 }
	        ],
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
	 <style type="text/css">
	 .tableColWidth {
	 	min-width: 100px;
	 	max-width: 500px;
	 	width: 100px;
	 	white-space: nowrap;
	 }
	 
		#loading {
		  width: 100%;
		  height: 100%;
		  top: 0px;
		  left: 0px;
		  position: fixed;
		  display: block;
		  opacity: 0.7;
		  background-color: black;
		  z-index: 99;
		  opacity:0.5;
		  text-align: center;
		}
		
		#loading-image {
		  position: absolute;
		  top: 30%;
		  left: 45%;
		  z-index: 100;
		  width: 100px;
		  height: 100px;
		}
	 </style>	
</head>
<body>
<s:form name="form1" theme="simple">

<div id="loading" style="width:100%">
   <img id="loading-image" src="${pageContext.request.contextPath}/images/madisonSymbolLogo.png"/>
</div>
<div class="container vehDtl">
<div class="Card_Parent ">
<div class="card card-5">
<div class="p-3">
	<div class="row">
		<div class="col-md-12">
			<div class="panel panel-primary">
				<div class="panel-heading">
					<h3><s:text name="Convert To Policy"/></h3><hr>
				</div>			
				<div class="panel-body">
					<div class="row">
						<font color="green"><s:actionmessage cssStyle="list-style: none;"/></font>
						<s:if test="hasActionErrors()">
							<font color="red"><s:actionerror cssStyle="list-style: none;"/></font>
						</s:if>
					</div>
					<br/>
					<br/>
					<div class="panel-body">
						<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
							<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
								<div class="row">
   									<label class="mt-2"><b><s:text name="Quote No"/> :</b></label>&nbsp;&nbsp;&nbsp;
   									<s:textfield name="quoteNo" id="quoteNo" cssClass="form-control numericOnlyNew" maxlength="10" style="width:20%"></s:textfield>
   									<button type="button" class="btn btn-sm btn-success offset-1" value="Submit" onclick="funSubmit();">Search</button>
  									</div>
							</div>
						</div>
					</div>
					<br/>
					<br/>
					<s:if test='mode == "QuoteDtl"'>
						<div class="panel-body">
							<div class="row">
								<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
									<table class="table table-bordered table-hover" cellspacing="0" width="100%" id="tadaTable">
										<thead class="bluecolortable" >
										<tr>
											<th><s:text name="Quote No"/></th>
											<th><s:text name="Premium"/></th>
											<th><s:text name="Customer Name"/></th>
											<th><s:text name="Merchant Ref Num" /></th>
											<th><s:text name="Product"/></th>
											<th><s:text name="Payment Type"/></th>
											<!--<th><s:text name="Response Decision"/></th>-->
											<th><s:text name="Bill Transfer Ref Num"/></th>
											<th><s:text name="Convert"/></th>
										</tr>
										</thead>
										<tbody class="rowevencolor">
											<s:iterator value="quoteDetails" var="list" status="stat">
												<tr>
													<td><s:property value="#list.QUOTE_NO" /></td>
													<td><s:property value="#list.PREMIUM" /></td>
													<td><s:property value="#list.CUSTOMER_NAME" /></td>
													<td><s:property value="#list.MERCHANT_REFERENCE" /></td>
													<td><s:property value="#list.PRODUCT" /></td>
													<td><s:property value="#list.PAYMENT" /></td>
													<!--<td><s:property value="#list.RES_DECISION" /></td>-->
													<td>
													<s:textfield name="billRefNum" id="billRefNum" cssClass="form-control numericOnlyNew" placeholder="Enter Bill Transfer Ref Num"></s:textfield>
													</td>
													<td style="text-align: center;">
														<input type="button" class="btn btn-warning btn-sm" onclick="funProceed('<s:property value="#list.QUOTE_NO" />');" value="Convert" />
													</td>
												</tr>
											</s:iterator>
										</tbody>
									</table>
								</div>								
							</div>
						</div>
					</s:if>
					
				</div>
			</div>
		</div>
	</div>
	</div>
	</div>
	</div>
</div>
<s:hidden name="reqForm"/>
</s:form>
</body>
<script type="text/Javascript" >

function funProceed(quoteNo){
	//document.form1.action='convertPaymentProcess.action?quoteNo='+quoteNo;
	var billRefNum=document.getElementById("billRefNum").value;
	if(billRefNum==null || billRefNum==""){
		if (confirm("Do You Want To Convert This Quote Without Bill Transfer Ref Num ?")) {
		    document.form1.action='convertPaymentProcess.action';
		    document.form1.submit();
		  } 
    }
    else{
  		document.form1.action='convertPaymentProcess.action';
	    document.form1.submit()
    }
}
function funSubmit(){
	document.form1.action='quoteDetailPaymentProcess.action';
	document.form1.submit();
}

$(".numericOnlyNew").on("keypress keyup blur",function (event) {  
	this.value = this.value.replace(/[^\d]/g,'');
    if (event.which > 57) {
       event.preventDefault();
    }
});
</script>
</html>