<%@ page isELIgnored="false" %>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/WEB-INF/displaytag.tld" prefix="display"%>
<!DOCTYPE HTML>
<html>
<head>
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
  	jQuery(function ($) {
  		try {
			var data1 = $('.display').dataTable( {
				"lengthMenu": [[10, 25, 50, -1], [10, 25, 50, "All"]],
				"order": [[ 1, "asc" ]],
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
<s:if test ='%{reqFrom==null || "".equals(reqFrom)}'>
<s:form id="opencover" name="opencover" method="post" action="openCoverAreport.action" theme="simple">
<div class="row">
	<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
		<div class="panel panel-primary">
			<div class="panel-heading">
				<s:text name="OpenCover Report" />
			</div>			
			<div class="panel-body">
				<div class="row">
					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
						<s:actionerror cssStyle="color: red;" />
					</div>
				</div>
				<div class="row">
					<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
						<div class="text"> <s:text name="policy.report.startdate"/> <font color="red">*</font> </div>
						<div class="tbox">
							<s:textfield cssClass="inputBox datepicker" name="startDate" id="startDate" />
						</div>
					</div>
					<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
						<div class="text"> <s:text name="policy.report.enddate"/> <font color="red">*</font> </div>
						<div class="tbox">
							<s:textfield cssClass="inputBox datepicker" name="endDate" id="endDate" />
						</div>
					</div>
					<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
						<div class="text"><s:text name="Select Broker"/> <font color="red">*</font></div>
						<div class="tbox">
							<s:select name="broker" list="brokerList" headerKey="ALL" headerValue="ALL" listKey="login_id" listValue="COMPANY_NAME" cssClass="inputBoxS"/>
						</div>
					</div>
				</div>
				<br class="clear" />
				<div class="row">
					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" align="center">
						<input type="button" name="sub" value="Submit" onclick="fnsubmit(this.form,'result','')" class="btn btn-sm btn-success" />
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
</s:form>
</s:if>
<s:elseif test='!"certificate".equals(reqFrom)' >
<s:form id="branch1" name="branch1" method="post" action="branchAreport.action" theme="simple">
<div class="row">
	<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
		<div class="panel panel-primary">
			<div class="OpenCover Report">
				<s:text name="Referral Cases" />
			</div>			
			<div class="panel-body">
				<div class="row">
					<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6" align="center">
						<s:text name="Start Date"/></b> &nbsp;:&nbsp;<s:property value="startDate"/>
					</div>
					<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6" align="center">
						<s:text name="End Date"/></b> &nbsp;:&nbsp;<s:property value="endDate"/>
					</div>
				</div>
				<br class="clear" />
				<div class="row">
					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
						<table class="display responsive no-wrap" id="record" width="100%" cellspacing="0">
							<thead>
							<tr>
								<th class="no-sort"></th>
								<th><s:text name="S.No" /></th>
								<th><s:text name="Broker Name" /></th>
								<th><s:text name="Customer Name" /></th>
								<th><s:text name="Open Cover No" /></th>
								<th><s:text name="No Of Policies" /></th>
								<th><s:text name="Total Premium" /></th>
								<th><s:text name="Total Commission" /></th>
							</tr>
							</thead>
							<tbody>
							<s:iterator value="coverList" status="stat" var="record">
							<tr>
								<td></td>
								<td><s:property value="%{#stat.index+1}" /> </td>
								<td><s:property value="BROKER_NAME" /> </td>
								<td><s:property value="customer_name" /> </td>
								<td><s:property value="open_cover_no" /> </td>
								<td align="center"> <a type="button" class="btn btn-sm btn-info" href="#" onclick="getCertificates('${record.open_cover_no}','${record.BROKER_NAME}')"  > ${record.POLICY_COUNT}</a> </td>
								<td style="text-align: right;"><s:property value='getText("number.format.2",{@java.lang.Double@valueOf(premium)})' /> </td>
								<td style="text-align: right;"><s:property value='getText("number.format.2",{@java.lang.Double@valueOf(commission)})' /> </td>
							</tr>
							</s:iterator>
							</tbody>
						</table>
					</div>
				</div>
				<br class="clear" />
				<div class="row">
					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" align="center">
						<input type="button" onclick="exportdata('excel')" class="btn btn-sm btn-info" value="Excel"/> &nbsp;&nbsp;&nbsp;
						<input type="button" onclick="exportdata('pdf')" class="btn btn-sm btn-info" value="Pdf"/> &nbsp;&nbsp;&nbsp;
						<input type="button" name="sub" value="Back" onclick="fnsubmit(this.form,'Back','')" class="btn btn-sm btn-danger" />
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<s:hidden name="startDate"/>
<s:hidden name="endDate"/>
<s:hidden name="broker"/>
<s:hidden name="productID"/>
<s:hidden name="reportStatus"/>
<s:hidden name="branch"/>
<s:hidden name="openCoverNo"/>
<s:hidden name="downloadType" id="downloadType"/>
</s:form>
</s:elseif>
<s:else>
<s:form id="certificate" name="certificate" method="post" action="" theme="simple">
<div class="row">
	<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
		<div class="panel panel-primary">
			<div class="OpenCover Report">
				<s:text name="OpenCover Report" />
			</div>			
			<div class="panel-body">
				<div class="row">
					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
						<table class="display responsive no-wrap" id="record" width="100%" cellspacing="0">
							<thead>
							<tr>
								<th class="no-sort"></th>
								<th><s:text name="S.No" /></th>
								<th><s:text name="Policy Issue Date" /></th>
								<th><s:text name="Policy No" /></th>
								<th><s:text name="Insured Name" /></th>
								<th><s:text name="Mode of Transport" /></th>
								<th><s:text name="Cover" /></th>
								<th><s:text name="Originating Country" /></th>
								<th><s:text name="Destination Country" /></th>
								<th><s:text name="Sum Insured(Foreign Currency)" /></th>
								<th><s:text name="Currency Type" /></th>
								<th><s:text name="Exchange Rate" /></th>
								<th><s:text name="Basis of Valuation" /></th>
								<th><s:text name="Equivalent INR" /></th>
								<th><s:text name="Marine Premium" /></th>
								<th><s:text name="WSRCC Premium" /></th>
								<th><s:text name="Excess" /></th>
								<th><s:text name="Total Premium" /></th>
								<th><s:text name="Commission Percent" /></th>
								<th><s:text name="Commission" /></th>
								<th><s:text name="Policy Type" /></th>
								<th><s:text name="User Name" /></th>
								<th><s:text name="Debitnote No" /></th>
								<th><s:text name="Creditnote No" /></th>
							</tr>
							</thead>
							<tbody>
							<s:iterator value="certificateList" status="stat" var="record">
							<tr>
								<td></td>
								<td><s:property value="%{#stat.index+1}" /> </td>
								<td><s:property value="POLICY_ISSUE_DATE" /> </td>
								<td><s:property value="POLICY_NO" /> </td>
								<td><s:property value="INSURED_NAME" /> </td>
								<td><s:property value="FORM_OF_TRANSPORT" /> </td>
								<td><s:property value="COVER_NAME" /> </td>
								<td><s:property value="ORIGIN_COUNTRY" /> </td>
								<td><s:property value="DEST_COUNTRY" /> </td>
								<td style="text-align: right;"><s:property value='getText("number.format.2",{@java.lang.Double@valueOf(SUM_INSURED_FOREIGN)})' /> </td>
								<td><s:property value="CURRENCY_TYPE" /> </td>
								<td><s:property value="exchange_rate" /> </td>
								<td><s:property value="BASIS_OF_VALUATION" /> </td>
								<td style="text-align: right;"><s:property value='getText("number.format.2",{@java.lang.Double@valueOf(EQUIVALENT_DH)})' /> </td>
								<td style="text-align: right;"><s:property value='getText("number.format.2",{@java.lang.Double@valueOf(MARINE_PREMIUM)})' /> </td>
								<td style="text-align: right;"><s:property value='getText("number.format.2",{@java.lang.Double@valueOf(WSRCC_PREMIUM)})' /> </td>
								<td style="text-align: right;"><s:property value='getText("number.format.2",{@java.lang.Double@valueOf(EXCESS_PREMIUM)})' /> </td>
								<td style="text-align: right;"><s:property value='getText("number.format.2",{@java.lang.Double@valueOf(TOTAL_PREMIUM)})' /> </td>
								<td><s:property value="COMMISSION_PERCENT" /> </td>
								<td style="text-align: right;"><s:property value='getText("number.format.2",{@java.lang.Double@valueOf(COMMISSION)})' /> </td>
								<td><s:property value="POLICYTYPE" /> </td>
								<td><s:property value="USER_NAME" /> </td>
								<td><s:property value="DEBIT_NOTE_NO" /> </td>
								<td><s:property value="CREDIT_NOTE_NO" /> </td>
							</tr>
							</s:iterator>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<br class="clear" />
<div class="row">
	<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" align="center">
		<input type="button" onclick="exportdetaildata('excel')" class="btn btn-sm btn-info" value="Excel"/> &nbsp;&nbsp;&nbsp;
 		<input type="button" onclick="exportdetaildata('pdf')" class="btn btn-sm btn-info" value="Pdf"/> &nbsp;&nbsp;&nbsp;
 		<input type="button" name="sub" value="Back" onclick="fnsubmit(this.form,'Back','certificate')" class="btn btn-sm btn-info" />
	</div>
</div>
<s:hidden name="startDate"/>
<s:hidden name="endDate"/>
<s:hidden name="broker" />
<s:hidden name="downloadType" id="downloadType"/>
<s:hidden name="openCoverBroker" />
<s:hidden name="openCoverNo" />
<s:hidden name="productID" />
</s:form>
</s:else>
<script type="text/javascript">
function fnsubmit(frm,from,val) {
	if(from == 'result'){
		document.opencover.action = "${pageContext.request.contextPath}/openCoverAreport.action?reqFrom="+from;
		document.opencover.submit();
	}else if(val=='certificate'){
		document.certificate.action = "${pageContext.request.contextPath}/openCoverAreport.action?reqFrom=result";
		document.certificate.submit();
	}else{
		document.branch1.action = "${pageContext.request.contextPath}/openCoverAreport.action?reqFrom=";
		document.branch1.submit();
	}
}
function exportdata(val) {
	document.branch1.downloadType.value=val;	
	document.branch1.action='${pageContext.request.contextPath}/openCoverJasperAreport.action';	
	document.branch1.submit();
}
function exportdetaildata(val) {
	document.certificate.downloadType.value=val;	
	document.certificate.action='${pageContext.request.contextPath}/openCoverdetailJasperAreport.action';	
	document.certificate.submit();
}
function getCertificates(openCoverNo,bname){
   document.branch1.openCoverNo.value=openCoverNo;
   //document.branch1.broker.value=bname;
   document.branch1.action = "${pageContext.request.contextPath}/openCoverCertificateAreport.action?reqFrom=certificate&openCoverBroker="+bname;
   document.branch1.submit();
}
</script>
</body>
</html>