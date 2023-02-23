<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ page language="java" import="com.maan.report.service.ReportService"%>
<!DOCTYPE HTML>
<html>
<head>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/dataTables/css/dataTables.responsive.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/dataTables/css/dataTables.jqueryui.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/dataTables/css/dataTables.tableTools.css">
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
  	
  	
  	$(function() {
  		try {
  			$('#fromdate').datepicker({
  				todayHighlight: true,
  				format: "dd/mm/yyyy"
  			}).on('changeDate', function(e){
  			    $(this).datepicker('hide');
  			});
  			$('#todate').datepicker({
  				todayHighlight: true,
  				format: "dd/mm/yyyy"
  			}).on('changeDate', function(e){
  			    $(this).datepicker('hide');
  			});
  		} catch(err) {console.error(err);}
  	});
  	
  	</script>
</head>
<body>
<s:form name="form1" theme="simple">
<div class="container vehDtl">
<div class="Card_Parent ">
<div class="card card-5">
<div class="row">
	<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
		<div class="panel panel-primary">
			<div class="panel-heading">
			 	<h3><s:text name="Portfolio" /></h3><hr> 
			 </div>
			<div class="panel-body">
				<div class="row">
					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
						<b id="val1" style="color:red"></b><br/>
						<b id="val" style="color:red"></b>
					</div>
				</div>
				<div class="row">
					<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
						<div class="labelHeader"><s:text name="from.date"/></div>
						<div class="tbox">
							<s:textfield name="fromdate" id="fromdate" cssClass="form-control" readonly="true" />
						</div>
					</div>
					<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
						<div class="labelHeader"><s:text name="to.date"/></div>
						<div class="tbox">
							<s:textfield name="todate" id="todate" cssClass="form-control" readonly="true" />
						</div>
					</div>
					<%-- <div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
						<div class="text"><s:text name="product.select"/></div>
						<div class="tbox">
							<s:select name="productID" id="productID" list="products" listKey="PRODUCT_ID" listValue="PRODUCT_NAME" headerKey="1" headerValue="ALL" cssClass="form-control"/>
						</div>
					</div>--%>
					<s:hidden name="productID" id="productID" />
					<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
						<div class="labelHeader"><s:text name="admin.user.branch"/></div>
						<div class="tbox">
							<s:select name="branchId" cssClass="form-control" id="branchId" list="branchLists" headerKey="" headerValue="---Select---" listKey="BRANCH_CODE" listValue="BRANCH_NAME" />
						</div>
					</div>
				</div>
				<div class="row mt-3">
					<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
						<div class="labelHeader"><s:text name="Regions"/></div>
						<div class="tbox">
							<s:select cssClass="form-control" name="region" id="region1" list="regionList" listKey="BRANCH_CODE" listValue="BRANCH_NAME" onchange="getBranches(this.value,'pendingBranch');"/>
						</div>
					</div>
					<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
						<div class="labelHeader"><s:text name="Type "/></div>
						<div class="tbox">
							<s:select name="rep" id="rep" list="#{'c':'Pending Quotations','p':'Policy Generated','r':'Pending Reports','d':'Policy Canceled'}" cssClass="form-control" />
						</div>
					</div>
				</div>
				<br class="clear" />
				<div class="row">
					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" align="center">
						<input type="button" onclick="getPortfolio('portfolioLists')" class="btn btn-sm btn-success" value="Search"/>
					</div>
				</div>
				<br class="clear" />
				<s:if test='%{#session.product_id=="3" || #session.product_id=="11"}'>
					<div class="row">
						<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
							<div class="text"><s:text name="portfolio.search.by"/></div>
							<div class="tbox">
								<s:select cssClass="form-control" name="searchBy" id="searchBy" list="#{'policyNO':'Policy No','customerName':'Customer Name'}"  headerKey="" headerValue="---Select---"/>
							</div>
						</div>
						<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
							<div class="text"><s:text name="portfolio.search.value"/></div>
							<div class="tbox">
								<s:textfield name="searchValue" cssClass="form-control"  />
							</div>
						</div>
						<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4" align="center">
							<br/>
							<input type="button" onclick="getportfolioResult('viewLists','portfolioLists')" class="btn btn-sm btn-success" value="Search" />
						</div>
					</div>
					<br class="clear" />
				</s:if>
				<div class="row">
					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
						<div id="portfolioLists">
							<s:if test='"portfolioLists".equals(optionMode)'>
								<table class="table table-bordered table-hover" id="record" cellspacing="0" width="100%" >
									<thead class="bluecolortable">
										<tr>
											<th> <s:text name="S.No." /> </th>
											<th> <s:text name="DATE" /> </th>
											<th> <s:text name="NUMBER OF QUOTES" /> </th>
										</tr>
									</thead>
									<tbody class="rowevencolor">
										<s:iterator value="portfolioList" status="stat" var="record">
										<tr>
											<td align="center"><s:property value="%{#stat.index+1}" /></td>
											<td align="center">
												<s:if test='"A".equals(rep)'>
													<s:property value="ENTRY_DATE" />
												</s:if>
												<s:else>
													<a href="#" type="buttton" class="btn btn-sm btn-info" onclick="getViewLists('${record.ENTRY_DATE}','portfolioLists','<s:property value="rep"/>','<s:property value="productID"/>')" > <s:property value="ENTRY_DATE" /> </a>
												</s:else>
											</td>
											<td align="center"> <s:property value="COU" /> </td>
										</tr>
										</s:iterator>
									</tbody>
								</table>
								<s:if test='"A".equals(rep)'>
									<br class="clear" />
									<div align="center">
										<input type="button" class="btn btn-sm btn-success" value="Save" onclick="pendingExcel();"/>
									</div>
								</s:if>
							</s:if>
							<div id="viewLists">
							<s:if test='"viewLists".equals(optionMode)'>
								<b>Selected Date :</b><s:property value="viewdate"/>
                                <s:hidden name="viewdate"  /> <br class="clear" />
                                <table class="table table-bordered table-hover" id="record" cellspacing="0" width="100%" >
									<thead class="bluecolortable">
									<tr>
										<th><s:text name="S.No." /> </th>
										<s:if test='"c".equals(rep)'>
											<th> <s:text name="BROKER ORAGANIZATION" /> </th>
											<th> <s:text name="QUOTE CREATED BY" /> </th>
											<th> <s:text name="QUOTE NO" /> </th>
											<th> <s:text name="CUSTOMER NAME" /> </th>
											<th> <s:text name="PREMIUM" /> </th>
											<th> <s:text name="VIEW DRAFT" /> </th>
										</s:if>
										<s:elseif test='"p".equals(rep)'>
											<th> <s:text name="BROKER ORAGANIZATION" /> </th>
											<th> <s:text name="QUOTE CREATED BY" /> </th>
											<th> <s:text name="CUSTOMER NAME" /> </th>
											<th> <s:text name="POLICY NO" /> </th>
											<th> <s:text name="POLICY START DATE" /> </th>
											<th> <s:text name="POLICY END DATE" /> </th>
											<th> <s:text name="POLICY TYPE" /> </th>
											<th> <s:text name="PAYMENT TYPE" /> </th>
											<th> <s:text name="PREMIUM" /> </th>
											<th> <s:text name="VIEW PDF" /> </th>
										</s:elseif>
										<s:elseif test='"pc".equals(rep)'>
											<th> <s:text name="BROKER ORAGANIZATION" /> </th>
											<th> <s:text name="QUOTE CREATED BY" /> </th>
											<th> <s:text name="POLICY NO" /> </th>
											<th> <s:text name="CUSTOMER NAME" /> </th>
											<th> <s:text name="PREMIUM" /> </th>
											<th> <s:text name="VIEW PDF" /> </th>
											<th> <s:text name="Status" /> </th>
										</s:elseif>
										<s:elseif test='"r".equals(rep)'>
											<th> <s:text name="BROKER ORAGANIZATION" /> </th>
											<th> <s:text name="QUOTE CREATED BY" /> </th>
											<th> <s:text name="QUOTE NO" /> </th>
											<th> <s:text name="CUSTOMER NAME" /> </th>
											<th> <s:text name="PREMIUM" /> </th>
										</s:elseif>
										<s:elseif test='"sr".equals(rep)'>
											<th> <s:text name="BROKER ORAGANIZATION" /> </th>
											<th> <s:text name="QUOTE CREATED BY" /> </th>
											<th> <s:text name="QUOTE NO" /> </th>
											<th> <s:text name="POLICY NO" /> </th>
											<th> <s:text name="CUSTOMER NAME" /> </th>
											<th> <s:text name="PREMIUM" /> </th>
											<th> <s:text name="VIEW PDF" /> </th>
										</s:elseif>
										<s:elseif test='"sr".equals(rep)'>
											<th> <s:text name="BROKER ORAGANIZATION" /> </th>
											<th> <s:text name="QUOTE CREATED BY" /> </th>
											<th> <s:text name="QUOTE NO" /> </th>
											<th> <s:text name="POLICY NO" /> </th>
											<th> <s:text name="CUSTOMER NAME" /> </th>
											<th> <s:text name="PREMIUM" /> </th>
											<th> <s:text name="VIEW PDF" /> </th>
										</s:elseif>
									</tr>
									</thead>
									<tbody class="rowevencolor">
									<s:iterator value="viewList" status="stat" var="record">
									<tr>
										<td><s:property value="%{#stat.index+1}" /></td>
										<s:if test='"c".equals(rep)'>
											<td> <s:property value="BROKER_ORGANIZATION" /> </td>
											<td> <s:property value="LOGIN_ID" /> </td>
											<td> <s:property value="QUOTE_NO" /> </td>
											<td> <s:property value="FIRST_NAME" /> </td>
											<td> <s:property value="PREMIUM" /> </td>
											<td align="center">
												<a href="#" type="button" class="btn btn-sm btn-info" onclick="viewPolicys('${record.QUOTE_NO}','${record.LOGIN_ID}','draft','${record.PRODUCT_ID}','${record.OPEN_COVER_NO}')"> <i class="fa fa-book"></i> </a>
											</td>
										</s:if>
										<s:elseif test='"p".equals(rep)'>
											<td> <s:property value="BROKER_ORGANIZATION" /> </td>
											<td> <s:property value="FIRST_NAME" /> </td>
											<td> <s:property value="LOGIN_ID" /> </td>
											<td> <s:property value="POLICY_NO" /> </td>
											<td> <s:property value="POLICY_START_DATE" /> </td>
											<td> <s:property value="POLICY_END_DATE" /> </td>
											<td> <s:property value="POLICY_TYPE" /> </td>
											<td> <s:property value="PAYMENT_TYPE" /> </td>
											<td> <s:property value="PREMIUM" /> </td>
											<td>
												<a href="#" type="button" class="btn btn-sm btn-info" onclick="viewPolicys('${record.POLICY_NO}','${record.LOGIN_ID}','schedule','${record.PRODUCT_ID}','${record.OPEN_COVER_NO}')"> <i class="fa fa-file-pdf-o"></i> </a>
											</td>
										</s:elseif>
										<s:elseif test='"pc".equals(rep)'>
											<td> <s:property value="BROKER_ORGANIZATION" /> </td>
											<td> <s:property value="LOGIN_ID" /> </td>
											<td> <s:property value="POLICY_NO" /> </td>
											<td> <s:property value="FIRST_NAME" /> </td>
											<td> <s:property value="PREMIUM" /> </td>
											<td>
												<a href="#" type="button" class="btn btn-sm btn-info" onclick="viewPolicys('${record.POLICY_NO}','${record.LOGIN_ID}','schedule','${record.PRODUCT_ID}','${record.OPEN_COVER_NO}')"> <i class="fa fa-eye"></i> </a>
											</td>
											<td>
												<s:hidden name="policyNo[%{#attr.record_rowNum-1}]" value="%{#myrow.POLICY_NO}" />
												<s:radio list="#{'A':'Active','D':'Deactive'}" name="status[%{#attr.record_rowNum-1}]" value="%{#myrow.POLICY_CANCEL_STATUS}"></s:radio>
											</td>
										</s:elseif>
										<s:elseif test='"r".equals(rep)'>
											<td> <s:property value="BROKER_ORGANIZATION" /> </td>
											<td> <s:property value="LOGIN_ID" /> </td>
											<td> <s:property value="QUOTE_NO" /> </td>
											<td> <s:property value="FIRST_NAME" /> </td>
											<td> <s:property value="PREMIUM" /> </td>
										</s:elseif>
										<s:elseif test='"sr".equals(rep)'>
											<td> <s:property value="BROKER_ORGANIZATION" /> </td>
											<td> <s:property value="LOGIN_ID" /> </td>
											<td> <s:property value="QUOTE_NO" /> </td>
											<td> <s:property value="POLICY_NO" /> </td>
											<td> <s:property value="FIRST_NAME" /> </td>
											<td> <s:property value="PREMIUM" /> </td>
											<td>
												<a href="#" type="button" class="btn btn-sm btn-info" onclick="viewPolicys('${record.POLICY_NO}','${record.LOGIN_ID}','schedule','${record.PRODUCT_ID}','${record.OPEN_COVER_NO}')"><i class="fa fa-eye"></i></a>
											</td>
										</s:elseif>
										<s:elseif test='"sr".equals(rep)'>
											<td> <s:property value="BROKER_ORGANIZATION" /> </td>
											<td> <s:property value="LOGIN_ID" /> </td>
											<td> <s:property value="QUOTE_NO" /> </td>
											<td> <s:property value="POLICY_NO" /> </td>
											<td> <s:property value="FIRST_NAME" /> </td>
											<td> <s:property value="PREMIUM" /> </td>
											<td>
												<a href="#" type="button" class="btn btn-sm btn-info" onclick="viewPolicys('${record.POLICY_NO}','${record.LOGIN_ID}','schedule','${record.PRODUCT_ID}','${record.OPEN_COVER_NO}')"><i class="fa fa-eye"></i></a>
											</td>
										</s:elseif>
									</tr>
									</s:iterator>
									</tbody>
								</table>
								<s:if test='"c".equals(rep)'><br/><s:submit align="center" value="Back" cssClass="btn btn-sm btn-danger" onclick="getBack();" /></s:if>
								<s:if test='"p".equals(rep)'><br/><s:submit align="center" value="Back" cssClass="btn btn-sm btn-danger" /></s:if>
								<s:if test='"pc".equals(rep)'><br/><s:submit align="center" value="Back" cssClass="btn btn-sm btn-danger" onclick="getBack();"  /><input type="button" onclick="getSubmit();" value="Submit" /></s:if>
								<s:if test='"r".equals(rep)'><br/><s:submit align="center" value="Back" cssClass="btn btn-sm btn-danger" />
								<a href="#" type="button" onclick="print('${record.ENTRY_DATE}')" class="btn btn-sm btn-info" > <i class="fa fa-print"></i> </a>
								<br/></s:if>
							</s:if>
						</div>
						</div>
					</div>
				</div>				
			</div>
		</div>
	</div>
</div>
</div>
</div>
</div>
<s:hidden name="policynumber" />
<s:hidden name="policyno" />
<s:hidden name="loginid" />
<s:hidden name="policyMode" />
<s:hidden name="polNo" />
<s:hidden name="reqFrom1" />
</s:form>
<script type="text/javascript">
function getPortfolio(id){
	var fromdate=document.form1.fromdate.value;
	var todate=document.form1.todate.value;
	var branchId=document.form1.branchId.value;
	var region=document.form1.region.value;
	var productID=document.getElementById("productID").value;
	var rep=document.getElementById("rep").value;
	document.getElementById('val1').innerHTML="";
	document.getElementById('val').innerHTML="";
	
	if(fromdate=="") { 
		document.getElementById('val1').innerHTML="Please Select the From Date";
	}if(todate=="") {
		document.getElementById('val').innerHTML="Please Select the To Date";
	}
	else {
		postRequest('${pageContext.request.contextPath}/getListAjaxPortfolio.action?reqFrom='+id+'&fromdate='+fromdate+'&todate='+todate+'&productID='+productID+'&rep='+rep+'&branchId='+branchId+'&region='+region, id);
	}
}

function getViewLists(date,id,status,productID){
	var branchId=document.form1.branchId.value;
	postRequest('${pageContext.request.contextPath}/viewPortfolio.action?mode=edit&reqFrom=viewLists&viewdate='+date+'&productID='+productID+'&rep='+status+'&branchId='+branchId, id);
}

function getSubmit(){
	document.form1.action='updatePolicyPortfolio.action';
	document.form1.submit();
}

function getBack(){
	document.form1.action='getListPortfolio.action';
	document.form1.submit();
}

function pendingExcel(){
	var fromdate=document.form1.fromdate.value;
	var todate=document.form1.todate.value;
	var status='p';
	var productID=document.getElementById("productID").value;
	var freightstatus='';
	var edate='';
	var brokercode='';
	
	//var URL="${pageContext.request.contextPath}/admin/PendingQuoteShowxcelusingPoi.jspfromdate="+fromdate+'&todate='+todate+'&rep='+status+'&productID='+productID";
	document.form1.action='${pageContext.request.contextPath}/admin/PendingQuoteShowxcelusingPoi.jsp?eDate='+edate+'&data1='+fromdate+'&data2='+todate+'&rep='+status+'&productId='+productID+'&broker_codes='+brokercode+'&freightStatus='+freightstatus;
	document.form1.submit();
}

function getportfolioResult(reqFrom,id) {
 	var searchBy=document.form1.searchBy.value;
  	var searchValue=document.form1.searchValue.value;
 	var rep=document.getElementById("rep").value;
 	var rep = "sr";
 	if(searchBy==null || searchBy=="") {
  		document.getElementById('val1').innerHTML="Please Select the Search By";
  	}
  	else if(searchValue==null || searchValue=="") {
  		document.getElementById('val1').innerHTML="Please Enter the Search Value";
  	}
  	else {
	 	//postRequest('${pageContext.request.contextPath}/getListAjaxPortfolio.action?reqFrom='+id+'&rep='+rep+'searchBy='+searchBy+'&searchValue='+searchValue, id);
	 	postRequest('${pageContext.request.contextPath}/viewPortfolio.action?reqFrom='+reqFrom+'&rep='+rep+'&searchBy='+searchBy+'&searchValue='+searchValue, id);
	 }
}

function print(ed)
{
    var pid=document.getElementById("productID").value;
	var rep=document.getElementById("rep").value;
    var fs='';
	var Print="print";
	var URL = "${pageContext.request.contextPath}/admin/PendingQuotesPrint.jsp?eDate="+ed+"&rep="+rep+"&productId="+pid+"&freightStatus="+fs;

	var windowName = "PremiumSummarySheet_BI";
	var width  = screen.availWidth;
	var height = screen.availHeight;
	var bwidth = window.innerWidth;
	var bwidth1 = document.body.clientWidth;
	if(bwidth <= 768) {
		var w = bwidth;
		var h = 400;
	} else {
		var w = 700;
		var h = 400;
	}
	var features =
	'width='          + w +
	',height='		  + h +
	',left='		  + ((width - w - 10) * .5)  +
	',top='			  + ((height - h - 30) * .5) +
	',directories=no' +
	',location=no'	  +
	',menubar=no'	  +
	',scrollbars=yes' +
	',status=yes'	  +
	',toolbar=no'	  +
	',resizable=false';
	var strOpen = window.open (URL, windowName, features);
	return false;
}
function viewPolicys(policynumber,loginid,policyModee,productId,openNo) {
	document.form1.policynumber.value=policynumber;
	document.form1.loginid.value=loginid;
	document.form1.policyMode.value=policyModee;
	
	if(policyModee=='treaty'){
		document.form1.polNo.value=policynumber;
		document.form1.reqFrom1.value='view';
		document.form1.action='treatyPortfolio.action';
		document.form1.submit();
	
	}else{	
		var verno='';
		var pdfstatus='';
		var displaytext='';
      	var URL ='${pageContext.request.contextPath}/Copy of information Admin.jsp?policyMode='+policyModee+'&policynumber='+policynumber+'&loginid='+loginid+'&productTypeIds='+productId+'&openCoverNoSettingCert='+openNo+'verNo='+verno+'pdfStatus='+pdfstatus+'disPlayText='+displaytext;

		var windowName = "PolicyView";
		var width  = screen.availWidth;
		var height = screen.availHeight;
		var bwidth = window.innerWidth;
		var bwidth1 = document.body.clientWidth;
		if(bwidth <= 768) {
			var w = bwidth;
			var h = 400;
		} else {
			var w = 700;
			var h = 400;
		}
		var features =
			'width='          + w +
			',height='		  + h +
			',left='		  + ((width - w - 10) * .5)  +
			',top='			  + ((height - h - 30) * .5) +
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
}
		
function fnSubmit() {
	document.form1.action="openPortfolio.action";
	document.form1.submit();
}

</script>
</body>
</html>