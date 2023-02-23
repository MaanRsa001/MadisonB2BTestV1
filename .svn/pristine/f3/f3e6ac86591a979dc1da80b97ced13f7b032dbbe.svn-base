<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<style type="text/css">
	#myDIV {
	    overflow-x:scroll;
	    margin: auto:
	}
           }
</style>
<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript" src="js/tcal.js"></script>
<link href="css/styles.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/cssbootstrap/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/bootstrap/css/footable-0.1.css"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/bootstrap/css/bootstrap-datetimepicker.css"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/dataTables/css/dataTables.responsive.css"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/dataTables/css/dataTables.jqueryui.css"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/dataTables/css/dataTables.tableTools.css"/>
<link href="<%=request.getContextPath()%>/dataTables/css/jquery-start-ui.css" rel="stylesheet" type="text/css" />
<script src="<%=request.getContextPath()%>/bootstrap/js/bootstrap-datetimepicker.js" type="text/javascript"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/dataTables/js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/dataTables/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/dataTables/js/dataTables.jqueryui.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/dataTables/js/dataTables.responsive.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/dataTables/js/dataTables.tableTools.js"></script>
<script type="text/javascript">
	jQuery(function ($) {
				try {
					var data = $('#gridTable').dataTable( {
				"lengthMenu": [[10, 25, 50, -1], [10, 25, 50, "All"]],
				responsive: true,
				"dom": 'T<"clear">lfrtip',
				"columnDefs": [ { "type": "numeric-comma", targets: 2 } ],
		        "tableTools": {
		            "sSwfPath": "<%=request.getContextPath()%>/dataTables/swf/copy_csv_xls_pdf.swf",
		            "aButtons": [ 
					"copy",
					"print", 
					{
						"sExtends": "collection",
						"sButtonText": "Export",
						//"mColumns": "visible",
						"aButtons": [{
							"sExtends": "csv",
							"sButtonText": "CSV",
							"sFileName": "report.csv",
							"mColumns": "visible"
							}, 
							{
							"sExtends": "xls",
							"sButtonText": "Excel",
							"sFileName": "report.xls",
							"mColumns": "visible"
							},
							{
							"sExtends": "pdf",
							"sButtonText": "PDF",
							"sPdfOrientation": "landscape",
							"sFileName": "report.pdf",
							"mColumns": "visible"
						}]
					}
					]
		        }
			});
				} catch(err){}
			} );
</script>	
</head>
<body>
<s:form name="form1" theme="simple">
<div class="row">
	<div class="col-xs-12">
		<div class="panel panel-primary">
				<div class="panel-heading">
					<s:text name="Reports"/>
				</div>				
				<div class="panel-body">
					<s:if test="hasActionErrors()">
						<div class="row">
							<div class="col-xs-12">
								<font color="red"><s:actionerror cssStyle="list-style: none;"/></font>
							</div>						
						</div>
						<br class="clear" />
					</s:if>
					<div class="row">
						<div class="col-xs-12 col-sm-12 col-md-4 col-lg-3">
							<div class="">
								<label><s:text name="Search By"/><font color="red">*</font></label>
								 <%--'P':'Payment Method','CT':'Cover Type','SLI':'Approver Id','UWI':'Approver Id','ET':'Endorsement Type','VU':'Vehicle Usage','CI':'City' --%>
								    <s:if test="reportType == 'INSTALMENTPAYMENT'">
									      <s:select name="searchBy" id="searchBy" list="#{'DP':'Payment Period','PM':'Payment Method','ST':'Status'}"  headerKey="" headerValue="---Select---" onchange="displayAjax(this.value,'searchListId');" cssClass="inputBoxS"/>
									</s:if>
									<s:if test="reportType == 'AGINGANALYSIS'">
									      <s:select name="searchBy" id="searchBy" list="#{'DP':'Policy Period','PM':'Payment Method'}"  headerKey="" headerValue="---Select---" onchange="displayAjax(this.value,'searchListId');" cssClass="inputBoxS"/>
									</s:if>
									<s:if test="reportType == 'CCAPRQ'">
									      <s:select name="searchBy" id="searchBy" list="#{'DP':'Policy Period','CT':'Cover Type','ST':'Quote Status','AID':'Approver Id'}"  headerKey="" headerValue="---Select---" onchange="displayAjax(this.value,'searchListId');" cssClass="inputBoxS"/>
									</s:if>
									<s:if test="reportType == 'SAPRQM'">
									      <s:select name="searchBy" id="searchBy" list="#{'DP':'Approval Period','AID':'Approver Id'}"  headerKey="" headerValue="---Select---" onchange="displayAjax(this.value,'searchListId');" cssClass="inputBoxS"/>
									</s:if>
							</div>
						</div>
						<div id="dateWise" style="display: none;" >
					    	<div class="col-xs-12 col-sm-12 col-md-4 col-lg-3">
								<div class="" >
									<label><s:text name="label.startDate"/><font color="red">*</font></label><s:textfield name="startDate" id="startDate1" cssClass="inputBox datepicker" readonly="true"></s:textfield>
								</div>
							</div>
							<div class="col-xs-12 col-sm-12 col-md-4 col-lg-3">
								<div class="">
									<label><s:text name="label.endDate" /><font color="red">*</font></label> <s:textfield name="endDate" id="endDate1" cssClass="inputBox datepicker" readonly="true" ></s:textfield>
								</div>
							</div>
						</div>
						<div class="col-xs-12 col-sm-12 col-md-4 col-lg-3">
							<div class="" style="display: none;" id="searchList">
							<label><s:text name="Choose Data for Search"/><font color="red">*</font></label>
								<div id="searchListId">
							</div>
							</div>
							<div style="display: none;" id="searchValueId">
								<label><s:text name="Enter Data For Search" /><font color="red">*</font></label>
								<s:textfield name="searchValue" id="searchValue" cssClass="inputBox" ></s:textfield>
							</div>
						</div>
						<div class="col-xs-12 col-sm-12 col-md-4 col-lg-3" align="center">
						<br class="clear"/>
							<input type="button" onclick="fnSubmit('search');" class="btn btn-sm btn-success" value="Submit" align="left" />
						</div>
					</div>
				</div>
		</div>
		<s:hidden name="reportType" id="reportType" />
		<s:if test="reqFrom == 'showList'">
		<s:if test="reportType == 'INSTALMENTPAYMENT'">
			<div class="panel panel-primary">
				<div class="panel-heading">
					<label><font><s:property value="reportType"/></font></label>&nbsp;REPORT
				</div> 
				<div class="panel-body">
					<table class="display responsive no-wrap" id="gridTable" width="100%" cellspacing="0">
						<thead>
						<tr>
							<th><s:text name="label.master.sno"/></th>
							<th><s:text name="label.quoteno"/></th>
							<th><s:text name="label.policyNo"/></th>					
							<th><s:text name="label.premium"/></th>
							<th><s:text name="label.installA"/></th>		
							<th><s:text name="label.dueDate"/></th>
							<th><s:text name="label.noOfEmi"/></th>
							<th><s:text name="label.currentIns"/></th>
							<th><s:text name="label.insStatus"/></th>
							<th><s:text name="label.baldue"/></th>
							<th><s:text name="label.paymentMode"/></th>
						</tr>
						</thead>
						<tbody>
							<s:iterator value="smartReportList" var="list" status="stat">
							<tr>
								<td><s:property value="#stat.count"/></td>
								<td><s:property value="#list.QUOTE_NO"/></td>
								<td><s:property value="#list.POLICY_NO" /></td>
								<td align="right"><s:property value="#list.OVERALL_PREMIUM"/></td>
								<td align="right"><s:property value="#list.INSTALLMENT_PREMIUM_AMOUNT" /></td>
								<td><s:property value="#list.INS_PREMIUM_DATE"/></td>
								<td align="center"><s:property value="#list.NO_OF_EMI"/></td>
								<td align="center"><s:property value="#list.INSTALLMENT_NO"/></td>
								<td align="center"><s:property value="#list.PAYMENT_STATUS"/></td>
								<td><s:property value="#list.DUE_AMT"/></td>
								<td align="center"><s:property value="#list.PAYMENT_METHOD"/></td>
							</tr>
							</s:iterator>
						</tbody>
					</table> 	
				</div>   
			</div>
		</s:if>
		<s:if test="reportType == 'AGINGANALYSIS'">
			<div class="panel panel-primary">
				<div class="panel-heading">
					<label><font><s:property value="reportType"/></font></label>&nbsp;REPORT
				</div> 
				<div class="panel-body">
					<table class="display responsive no-wrap" id="gridTable" width="100%" cellspacing="0">
						<thead>
						<tr>
							<th><s:text name="label.master.sno"/></th>
							<th><s:text name="label.quoteno"/></th>
							<th><s:text name="label.policyNo"/></th>
							<th><s:text name="label.policyPeriod"/></th>
							<th><s:text name="label.baldue"/></th>
							<th><s:text name="label.noOfEmi"/></th>
							<th><s:text name="label.currentIns"/></th>
							<th><s:text name="label.insStatus"/></th>
							<th><s:text name="label.paymentMode"/></th>
						</tr>
						</thead>
						<tbody>
							<s:iterator value="smartReportList" var="list" status="stat">
							<tr>
								<td><s:property value="#stat.count"/></td>
								<td><s:property value="#list.QUOTE_NO"/></td>
								<td><s:property value="#list.POLICY_NO" /></td>
								<td ><s:property value="#list.POLICY_START_DATE"/>&nbsp;to&nbsp; <s:property value="#list.POLICY_END_DATE"/></td>
								<td align="right"><s:property value="#list.DUE_AMT"/></td>
								<td><s:property value="#list.NO_OF_EMI"/></td>
								<td><s:property value="#list.INSTALLMENT_NO"/></td>
								<td align="center"><s:property value="#list.PAYMENT_STATUS"/></td>
								<td align="center"><s:property value="#list.PAYMENT_METHOD"/></td>
							</tr>
							</s:iterator>
						</tbody>
					</table> 	 	
				</div>   
			</div>
		</s:if>
		<s:if test="reportType == 'CCAPRQ'">
			<div class="panel panel-primary">
				<div class="panel-heading">
					<label><font><s:property value="reportType"/></font></label>&nbsp;REPORT
				</div> 
				<div class="panel-body">
					<table class="display responsive no-wrap" id="gridTable" width="100%" cellspacing="0">
						<thead>
						<tr>
							<th><s:text name="label.master.sno"/></th>
							<th><s:text name="label.quoteno"/></th>
							<th><s:text name="label.cust.name"/></th>
							<th><s:text name="label.mobile"/></th>
							<th><s:text name="label.policyType"/></th>		
							<th><s:text name="label.policy.start"/></th>
							<th><s:text name="label.policy.end"/></th>
							<th><s:text name="label.premium"/></th>
							<th><s:text name="label.suminsured.value.local"/></th>
							<th><s:text name="label.risk.id"/></th>
							<th><s:text name="label.risk.addr"/></th>
							<th><s:text name="claim.remarks"/></th>
							<th><s:text name="label.paymentMode"/></th>
							<th><s:text name="label.paymentamount"/></th>
							<th><s:text name="label.approve"/></th>
							<th><s:text name="label.approve.date"/></th>
						</tr>
						</thead>
						<tbody>
							<s:iterator value="smartReportList" var="list" status="stat">
							<tr>
								<td><s:property value="#stat.count"/></td>
								<td><s:property value="#list.QUOTE_NO"/></td>
								<td><s:property value="#list.CUSTOMER_NAME"/></td>
								<td><s:property value="#list.MOBILE" /></td>
								<td><s:property value="#list.POLICYTYPE_DESC"/></td>
								<td><s:property value="#list.POLICY_START_DATE" /></td>
								<td><s:property value="#list.POLICY_END_DATE"/></td>
								<td><s:property value="#list.PREMIUM" /></td>
								<td><s:property value="#list.SUMINSURED_VALUE_LOCAL" /></td>
								<td><s:property value="#list.VEHICLE_ID"/></td>
								<td><s:property value="#list.RISK_ADDRESS"/></td>
								<td><s:property value="#list.REMARKS" /></td>
								<td><s:property value="#list.PAYMENT_METHOD" /></td>
								<td><s:property value="#list.INITIAL_AMOUNT"/></td>
								<td><s:property value="#list.APPROVED_ID" /></td>
								<td><s:property value="#list.APPROVED_DATE" /></td>
							</tr>
							</s:iterator>
						</tbody>
					</table> 	
				</div>   
			</div>
		</s:if>
		<s:if test="reportType == 'SAPRQM' || reportType == 'UWAPRQM'">
			<div class="panel panel-primary">
				<div class="panel-heading">
					<label><font><s:property value="reportType"/></font></label>&nbsp;REPORT
				</div> 
				<div class="panel-body">
					<table class="display responsive no-wrap" id="gridTable" width="100%" cellspacing="0">
						<thead>
						<tr>
							<th><s:text name="label.master.sno"/></th>
							<th><s:text name="label.quoteno"/></th>
							<th><s:text name="label.cust.name"/></th>
							<th><s:text name="label.mobile"/></th>
							<th><s:text name="label.policyType"/></th>		
							<th><s:text name="label.policy.start"/></th>
							<th><s:text name="label.policy.end"/></th>
							<th><s:text name="label.premium"/></th>
							<th><s:text name="label.suminsured.value.local"/></th>
							<th><s:text name="label.risk.id"/></th>
							<th><s:text name="label.risk.addr"/></th>
							<th><s:text name="label.vehicle.reg"/></th>
							<th><s:text name="label.makename"/></th>
							<th><s:text name="label.modelname"/></th>
							<th><s:text name="claim.remarks"/></th>
						</tr>
						</thead>
						<tbody>
							<s:iterator value="smartReportList" var="list" status="stat">
							<tr>
								<td><s:property value="#stat.count"/></td>
								<td><s:property value="#list.QUOTE_NO"/></td>
								<td><s:property value="#list.CUSTOMER_NAME"/></td>
								<td><s:property value="#list.MOBILE" /></td>
								<td><s:property value="#list.POLICYTYPE_DESC"/></td>
								<td><s:property value="#list.POLICY_START_DATE" /></td>
								<td><s:property value="#list.POLICY_END_DATE"/></td>
								<td><s:property value="#list.PREMIUM" /></td>
								<td><s:property value="#list.SUMINSURED_VALUE_LOCAL" /></td>
								<td><s:property value="#list.VEHICLE_ID"/></td>
								<td><s:property value="#list.RISK_ADDRESS"/></td>
								<td><s:property value="#list.REGISTRATION_NO" /></td>
								<td><s:property value="#list.MAKE_NAME"/></td>
								<td><s:property value="#list.MODEL_NAME" /></td>
								<td><s:property value="#list.REMARKS" /></td>
							</tr>
							</s:iterator>
						</tbody>
					</table> 	
				</div>   
			</div>
		</s:if>
		<s:if test="reportType == 'POLICYDUERENEWAL' ">
			<div class="panel panel-primary">
				<div class="panel-heading">
					<label><font><s:property value="reportType"/></font></label>&nbsp;REPORT
				</div> 
				<div class="panel-body">
					<table class="display responsive no-wrap" id="gridTable" width="100%" cellspacing="0">
						<thead>
						<tr>
							<th><s:text name="label.master.sno"/></th>
							<th><s:text name="label.policyNo"/></th>
							<th><s:text name="label.policy.start"/></th>
							<th><s:text name="label.policy.end"/></th>
							<th><s:text name="label.suminsured.value.local"/></th>
							<th><s:text name="label.premium"/></th>
							<th><s:text name="label.policyType"/></th>		
							<th><s:text name="label.opcover"/></th>
							<th><s:text name="label.opcover.premium"/></th>
							<th><s:text name="label.discount.rate"/></th>
							<th><s:text name="label.discount"/></th>
						</tr>
						</thead>
						<tbody>
							<s:iterator value="smartReportList" var="list" status="stat">
							<tr>
								<td><s:property value="#stat.count"/></td>
								<td><s:property value="#list.POLICY_NO"/></td>
								<td><s:property value="#list.POLICY_START_DATE" /></td>
								<td><s:property value="#list.POLICY_END_DATE"/></td>
								<td><s:property value="#list.SUMINSURED_VALUE_LOCAL"/></td>
								<td><s:property value="#list.PREMIUM" /></td>
								<td><s:property value="#list.POLICYTYPE_DESC"/></td>
								<td><s:property value="#list.ADDITIONAL_COVER" /></td>
								<td><s:property value="#list.ADDITIONAL_PREMIUM" /></td>
								<td><s:property value="#list.DISCOUNT_RATE"/></td>
								<td><s:property value="#list.DISCOUNT_PREMIUM"/></td>
							</tr>
							</s:iterator>
						</tbody>
					</table> 	
				</div>   
			</div>
		</s:if>
		<s:if test="reportType == 'PREMIUMREGISTER' ">
			<div class="panel panel-primary">
				<div class="panel-heading">
					<label><font><s:property value="reportType"/></font></label>&nbsp;REPORT
				</div> 
				<div class="panel-body">
					<table class="display responsive no-wrap" id="gridTable" width="100%" cellspacing="0">
						<thead>
						<tr>
							<th><s:text name="label.master.sno"/></th>
							<th><s:text name="label.policyNo"/></th>
							<th><s:text name="label.policy.start"/></th>
							<th><s:text name="label.policy.end"/></th>
							<th><s:text name="label.suminsured.value.local"/></th>
							<th><s:text name="label.premium"/></th>
							<th><s:text name="label.policyType"/></th>		
							<th><s:text name="label.opcover"/></th>
							<th><s:text name="label.opcover.premium"/></th>
							<th><s:text name="label.discount.rate"/></th>
							<th><s:text name="label.discount"/></th>
						</tr>
						</thead>
						<tbody>
							<s:iterator value="smartReportList" var="list" status="stat">
							<tr>
								<td><s:property value="#stat.count"/></td>
								<td><s:property value="#list.POLICY_NO"/></td>
								<td><s:property value="#list.POLICY_START_DATE" /></td>
								<td><s:property value="#list.POLICY_END_DATE"/></td>
								<td><s:property value="#list.SUMINSURED_VALUE_LOCAL"/></td>
								<td><s:property value="#list.PREMIUM" /></td>
								<td><s:property value="#list.POLICYTYPE_DESC"/></td>
								<td><s:property value="#list.ADDITIONAL_COVER" /></td>
								<td><s:property value="#list.ADDITIONAL_PREMIUM" /></td>
								<td><s:property value="#list.DISCOUNT_RATE"/></td>
								<td><s:property value="#list.DISCOUNT_PREMIUM"/></td>
							</tr>
							</s:iterator>
						</tbody>
					</table> 	
				</div>   
			</div>
		</s:if>
		<s:if test="reportType == 'POLICYREGISTER' ">
			<div class="panel panel-primary">
				<div class="panel-heading">
					<label><font><s:property value="reportType"/></font></label>&nbsp;REPORT
				</div> 
				<div class="panel-body">
					<table class="display responsive no-wrap" id="gridTable" width="100%" cellspacing="0">
						<thead>
						<tr>
							<th><s:text name="label.master.sno"/></th>
							<th><s:text name="label.policyNo"/></th>
							<th><s:text name="label.policy.start"/></th>
							<th><s:text name="label.policy.end"/></th>
							<th><s:text name="label.suminsured.value.local"/></th>
							<th><s:text name="label.premium"/></th>
							<th><s:text name="label.policyType"/></th>		
							<th><s:text name="label.approve"/></th>
							<th><s:text name="label.cust.name"/></th>
							<th><s:text name="claim.address"/></th>
							<th><s:text name="claim.typeOfBody"/></th>
						</tr>
						</thead>
						<tbody>
							<s:iterator value="smartReportList" var="list" status="stat">
							<tr>
								<td><s:property value="#stat.count"/></td>
								<td><s:property value="#list.POLICY_NO"/></td>
								<td><s:property value="#list.POLICY_START_DATE" /></td>
								<td><s:property value="#list.POLICY_END_DATE"/></td>
								<td><s:property value="#list.SUMINSURED_VALUE_LOCAL"/></td>
								<td><s:property value="#list.PREMIUM" /></td>
								<td><s:property value="#list.POLICYTYPE_DESC"/></td>
								<td><s:property value="#list.APPROVED_ID" /></td>
								<td><s:property value="#list.CUSTOMER_NAME" /></td>
								<td><s:property value="#list.CUSTOMER_ADDRESS"/></td>
								<td><s:property value="#list.VEHICLE_USAGE"/></td>
							</tr>
							</s:iterator>
						</tbody>
					</table> 	
				</div>   
			</div>
		</s:if>
		<s:if test="reportType == 'ENDORSEMENTREGISTER' ">
			<div class="panel panel-primary">
				<div class="panel-heading">
					<label><font><s:property value="reportType"/></font></label>&nbsp;REPORT
				</div> 
				<div class="panel-body">
					<table class="display responsive no-wrap" id="gridTable" width="100%" cellspacing="0">
						<thead>
						<tr>
							<th><s:text name="label.master.sno"/></th>
							<th><s:text name="label.policyNo"/></th>
							<th><s:text name="label.endt.type"/></th>
							<th><s:text name="label.status"/></th>
							<th><s:text name="label.req"/></th>
							<th><s:text name="label.approve.date"/></th>
							<th><s:text name="label.approve"/></th>		
							<th><s:text name="label.premium"/></th>
							<th><s:text name="label.agent.remarks"/></th>
							<th><s:text name="label.admin.remarks"/></th>
						</tr>
						</thead>
						<tbody>
							<s:iterator value="smartReportList" var="list" status="stat">
							<tr>
								<td><s:property value="#stat.count"/></td>
								<td><s:property value="#list.POLICY_NO"/></td>
								<td><s:property value="#list.ENDORE_TYPE" /></td>
								<td><s:property value="#list.STATUS"/></td>
								<td><s:property value="#list.ENTRY_DATE"/></td>
								<td><s:property value="#list.APPROVED_DATE" /></td>
								<td><s:property value="#list.APPROVED_ID"/></td>
								<td><s:property value="#list.PREMIUM" /></td>
								<td><s:property value="#list.REMARKS"/></td>
								<td><s:property value="#list.ADMIN_REMARKS"/></td>
							</tr>
							</s:iterator>
						</tbody>
					</table> 	
				</div>   
			</div>
		</s:if>
		<s:if test="reportType == 'ROADASSIT' ">
			<div class="panel panel-primary">
				<div class="panel-heading">
					<label><font><s:property value="reportType"/></font></label>&nbsp;REPORT
				</div> 
				<div class="panel-body">
					<table class="display responsive no-wrap" id="gridTable" width="100%" cellspacing="0">
						<thead>
						<tr>
							<th><s:text name="label.master.sno"/></th>
							<th><s:text name="label.ref.no"/></th>
							<th><s:text name="label.policyNo"/></th>
							<th><s:text name="label.cust.name"/></th>
							<th><s:text name="label.customerNumber"/></th>
							<th><s:text name="claim.email"/></th>
							<th><s:text name="label.assist.type"/></th>		
							<th><s:text name="label.desc"/></th>
							<th><s:text name="label.road.assist.status"/></th>
							<th><s:text name="label.update.id"/></th>		
							<th><s:text name="label.req.create"/></th>
							<th><s:text name="label.req.close"/></th>
							<th><s:text name="label.remarks"/></th>
						</tr>
						</thead>
						<tbody>
							<s:iterator value="smartReportList" var="list" status="stat">
							<tr>
								<td><s:property value="#stat.count"/></td>
								<td><s:property value="#list.REF_NO"/></td>
								<td><s:property value="#list.POLICY_NO" /></td>
								<td><s:property value="#list.CUSTOMER_NAME"/></td>
								<td><s:property value="#list.MOBILE"/></td>
								<td><s:property value="#list.EMAIL" /></td>
								<td><s:property value="#list.ASSIT_TYPE" /></td>
								<td><s:property value="#list.DESCRIPTION"/></td>
								<td><s:property value="#list.STATUS" /></td>
								<td><s:property value="#list.APPROVED_ID"/></td>
								<td><s:property value="#list.ENTRY_DATE" /></td>
								<td><s:property value="#list.APPROVED_DATE"/></td>
								<td><s:property value="#list.REMARKS"/></td>
							</tr>
							</s:iterator>
						</tbody>
					</table> 	
				</div>   
			</div>
		</s:if>
		</s:if>
	</div>
</div>
</s:form>
<script type="text/Javascript" >

displayAjax('<s:property value="searchBy"/>','searchListId')

function fnSubmit(val){
	document.form1.action='getAllListAreport.action?mode='+val;
	document.form1.submit();
}


function displayAjax(val,id){
	if(val == 'SV'){
		document.getElementById("searchValueId").style.display = "block";
		document.getElementById("searchList").style.display = "none";	
		document.getElementById("dateWise").style.display = "none";
	}else if(val == '' || val == null){
		document.getElementById("searchList").style.display = "none";
		document.getElementById("searchValueId").style.display = "none";
		document.getElementById("dateWise").style.display = "none";
	}else if(val == 'DP'){
		document.getElementById("dateWise").style.display = "block";
		document.getElementById("searchList").style.display = "none";
		document.getElementById("searchValueId").style.display = "none";
	}
	else{
		var reportType = document.getElementById("reportType").value;
		var action ="${pageContext.request.contextPath}/searchListAjaxAreport.action?searchBy="+val+"&reportType="+reportType;
		postRequest(action, id);
		document.getElementById("searchList").style.display = "block";
		document.getElementById("searchValueId").style.display = "none";
		document.getElementById("dateWise").style.display = "none";
	}
}
</script>
</body>
</html>