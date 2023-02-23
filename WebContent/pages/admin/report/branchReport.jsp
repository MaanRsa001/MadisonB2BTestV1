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
<s:if test ="reqFrom==null || reqFrom == ''">
<s:form id="branch" name="branch" method="post" action="branchAreport.action" theme="simple">
<div class="row">
	<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
		<div class="panel panel-primary">
			<div class="panel-heading">
				<s:text name="Branch Report" />
			</div>			
			<div class="panel-body">
				<div class="row">
					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
						<s:actionerror cssStyle="color: red;" /> <s:actionmessage cssStyle="color: green;" />
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
						<div class="text"> <s:text name="policy.report.product"/> <font color="red">*</font> </div>
						<div class="tbox">
							<s:select name="productID" id="productID" list="productList" headerKey="" headerValue="---Select---" listKey="PRODUCT_ID"  listValue="PRODUCT_NAME" cssClass="inputBoxS"/>
						</div>
					</div>
					<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
						<div class="text"> <s:text name="policy.report.Status"/> <font color="red">*</font> </div>
						<div class="tbox">
							<s:radio list="#{'P':'Policy Status','Y':'Quote Status','D':'Cancelled Policy Status'}" name="reportStatus" id="reportStatus" value="%{reportStatus==null?'P':reportStatus}" />
						</div>
					</div>
					<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
						<div class="text"> <s:text name="policy.report.Branch"/> </div>
						<div class="tbox">
							<s:select name="branch" id="branch" list="branchName" headerKey="" headerValue="---Select---" listKey="BRANCH_CODE"  listValue="BRANCH_NAME" cssClass="inputBoxS"/>
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
<s:else>
<s:form id="branch1" name="branch1" method="post" action="branchAreport.action" theme="simple">
<div class="row">
	<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
		<div class="panel panel-primary">
			<div class="panel-heading">
				<s:if test='"reportBR".equals(mode1)'> <s:text name="Broker Report" /></s:if>
				<s:elseif test='"reportUW".equals(mode1)'> <s:text name="Underwriter Report" /></s:elseif>
				<s:else> <s:text name="Branch Report" /></s:else>
			</div>			
			<div class="panel-body">
				<div class="row">
					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
						<s:actionerror cssStyle="color: red;" /> <s:actionmessage cssStyle="color: green;" />
					</div>
				</div>
				<div class="row">
					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">																		
						<table class="display responsive no-wrap" id="record" width="100%" cellspacing="0">
							<thead>
							<tr>
								<th class="no-sort"></th>
								<th><s:text name="S.No" /></th>
								<th><s:text name="Entry Date" /></th>
								<th><s:text name="Branch Name" /></th>
								<th><s:text name="Login Id" /></th>
								<th><s:text name="Application Id" /></th>
								<th><s:text name="Quote No" /></th>
								<th><s:text name="Application No" /></th>
								<th><s:text name="Policy No" /></th>
								<th><s:text name="Inception Date" /></th>
								<th><s:text name="Effective Date" /></th>
								<th><s:text name="Product Id" /></th>
								<th><s:text name="Customer Name" /></th>
								<th><s:text name="Transport Desc" /></th>
								<th><s:text name="Cover Name" /></th>
								<th><s:text name="Goods Description" /></th>
								<th><s:text name="Saleterm For Valuation" /></th>
								<th><s:text name="Saleterm Charges" /></th>
								<th><s:text name="Tolerance Charges(SAR)" /></th>
								<th><s:text name="Extracover Name" /></th>
								<th><s:text name="SumInsured Local" /></th>
								<th><s:text name="Currency Name" /></th>
								<th><s:text name="Exchange Rate" /></th>
								<th><s:text name="SumInsured Foreign" /></th>
								<th><s:text name="Marine Premium" /></th>
								<th><s:text name="War Premium" /></th>
								<th><s:text name="Excess Premium" /></th>
								<th><s:text name="Policy Fee" /></th>
								<th><s:text name="Inspection Fee" /></th>
								<th><s:text name="Total Premium(SAR)" /></th>
								<th><s:text name="Foreign Currency Status" /></th>
								<th><s:text name="Total Premium(Foreign)" /></th>
								<th><s:text name="Commission" /></th>
								<th><s:text name="Transit from City" /></th>
								<th><s:text name="Transit from Country" /></th>
								<th><s:text name="Finaldest City" /></th>
								<th><s:text name="Finaldest Country" /></th>
								<th><s:text name="Via" /></th>
								<th><s:text name="Debitnote No" /></th>
								<th><s:text name="Creditnote No" /></th>
								<th><s:text name="Referral" /></th>
								<th><s:text name="Referral Status" /></th>
								<th><s:text name="Status" /></th>
								<th><s:text name="Integration Status" /></th>
								<th><s:text name="Integration Error" /></th>
								<th><s:text name="Excess Description" /></th>
							</tr>
							</thead>
							<tbody>
							<s:iterator value="branchList" status="stat" var="record">
							<tr>
								<td></td>
								<td><s:property value="%{#stat.index+1}" /></td>
								<td><s:property value="ENTRY_DATE" /> </td>
								<td><s:property value="BRANCH_NAME" /> </td>
								<td><s:property value="LOGIN_ID" /> </td>
								<td><s:property value="APPLICATIONID" /> </td>
								<td><s:property value="QUOTE_NO" /> </td>
								<td><s:property value="APPLICATION_NO" /> </td>
								<td><s:property value="POLICY_NO" /> </td>
								<td><s:property value="INCEPTION_DATE" /> </td>
								<td><s:property value="EFFECTIVE_DATE" /> </td>
								<td><s:property value="PRODUCT_ID" /> </td>
								<td><s:property value="CUSTOMER_NAME" /> </td>
								<td><s:property value="TRANSPORT_DESCRIPTION" /> </td>
								<td><s:property value="COVER_NAME" /> </td>
								<td><s:property value="GOODS_DESCRIPTION" /> </td>
								<td><s:property value="SALE_TERM_NAME" /> </td>
								<td><s:property value="TOTAL_SLAE_TERM_CHARGES" /> </td>
								<td><s:property value="TOTAL_TOLERANCE_CHARGES" /> </td>
								<td><s:property value="EXTRA_COVER_NAME" /> </td>
								<td><s:property value="SUM_INSURED_LOCAL" /> </td>
								<td><s:property value="CURRENCY_NAME" /> </td>
								<td><s:property value="EXCHANGE_RATE" /> </td>
								<td><s:property value="SUM_INSURED_FOREIGN" /> </td>
								<td><s:property value="BASIC_PREMIUM" /> </td>
								<td><s:property value="TOTAL_WAR_CHARGES" /> </td>
								<td><s:property value="EXCESS_PREMIUM" /> </td>
								<td><s:property value="POLICY_FEE" /> </td>
								<td><s:property value="INSPECTION_FEE" /> </td>
								<td><s:property value="TOTAL_PREMIUM_LOCAL" /> </td>
								<td><s:property value="FOREIGN_CURRENCY" /> </td>
								<td><s:property value="TOTAL_PREMIUM_FOREIGN" /> </td>
								<td><s:property value="COMMISSION" /> </td>
								<td><s:property value="TRANSIT_FROM_CITY" /> </td>
								<td><s:property value="TRANSIT_FROM_COUNTRY" /> </td>
								<td><s:property value="FINAL_DEST_CITY" /> </td>
								<td><s:property value="FINAL_DESTINATION_COUNTRY" /> </td>
								<td><s:property value="via" /> </td>
								<td><s:property value="DEBIT_NOTE_NO" /> </td>
								<td><s:property value="CREDIT_NOTE_NO" /> </td>
								<td><s:property value="REFERRAL" /> </td>
								<td><s:property value="REFERAL_STATUS" /> </td>
								<td><s:property value="STATUS" /> </td>
								<td><s:property value="INTEGRATION_STATUS" /> </td>
								<td><s:property value="INTEGRATION_ERROR" /> </td>
								<td><s:property value="excess_description" /> </td>
							</tr>
							</s:iterator>
							</tbody>
						</table>
						<br class="clear" />
						<div align="right">
							<input type="button" onclick="exportdata('excel')" class="btn btn-sm btn-info" value="Excel"/> &nbsp;&nbsp;&nbsp;
	  						<input type="button" onclick="exportdata('pdf')" class="btn btn-sm btn-info" value="Pdf"/>
						</div>
					</div>
				</div>
				<br class="clear" />
				<div class="row">
					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" align="center">
						<input type="button" name="sub" value="Back" onclick="fnsubmit(this.form,'Back','')" class="btn btn-sm btn-danger" />
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<s:hidden name="startDate"/>
<s:hidden name="mode1" id="lmode1"/>
<s:hidden name="endDate"/>
<s:hidden name="productID"/>
<s:hidden name="reportStatus"/>
<s:hidden name="branch"/>
<s:hidden name="downloadType"/>
<s:hidden name="loginId"/>
</s:form>
</s:else>
<script type="text/javascript">
function exportdata(val) {
	document.branch1.downloadType.value=val;	
	document.branch1.action='${pageContext.request.contextPath}/branchReportJasperAreport.action';	
	document.branch1.submit();
}
function fnsubmit(frm,from,val) {
	if(from == 'result'){
		document.branch.action = "${pageContext.request.contextPath}/branchResultAreport.action?reqFrom="+from;
		document.branch.submit();
	}else{
		<s:if test='"reportBR".equals(mode1)'>document.branch1.action = "${pageContext.request.contextPath}/policyAreport.action?index=1";</s:if>
		<s:elseif test='"reportUW".equals(mode1)'>document.branch1.action = "${pageContext.request.contextPath}/policyAreport.action";</s:elseif>
		<s:else>document.branch1.action = "${pageContext.request.contextPath}/branchAreport.action";</s:else>
		
		document.branch1.submit();
	}
}
</script>
</body>
</html>