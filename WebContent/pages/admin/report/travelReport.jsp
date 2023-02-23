<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/struts-tags" prefix="s"%>
<html>
<head>
	<SCRIPT language=Javascript>
		function isNumberKey(evt) {
			var charCode = (evt.which) ? evt.which : event.keyCode;
			if (charCode != 46 && charCode > 31 
			&& (charCode < 48 || charCode > 57))
			return false;
			return true;
		}
	</SCRIPT>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/dataTables/css/dataTables.responsive.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/dataTables/css/dataTables.jqueryui.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/dataTables/css/dataTables.tableTools.css">
	<link href="<%=request.getContextPath()%>/dataTables/css/jquery-start-ui.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="<%=request.getContextPath()%>/dataTables/js/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/dataTables/js/jquery.dataTables.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/dataTables/js/dataTables.jqueryui.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/dataTables/js/dataTables.responsive.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/dataTables/js/dataTables.tableTools.js"></script>
	<script type="text/javascript">
		jQuery(function ($) {
			try {
				var data = $('#gridTableMake').dataTable( {
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
	 <style type="text/css">
	 .tableColWidth {
	 	min-width: 100px;
	 	max-width: 500px;
	 	width: 100px;
	 	white-space: nowrap;
	 }
	 </style>	
</head>
<body>
<s:form name="form1" theme="simple">
<div class="container-fluid">
	<div class="row">
		<div class="col-xs-12">
			<div class="panel panel-primary">
				<div class="panel-heading">
					<s:text name="label.travelReports"/>
				</div>				
				<div class="panel-body">
					<s:if test="hasActionErrors()">
						<div class="row">
							<font color="red"><s:actionerror cssStyle="list-style: none;"/></font>
						</div>
						<br class="clear" />
					</s:if>
					<div class="panel-body">
						<div class="row">
							<div class="col-xs-12 col-sm-12 col-md-6 col-lg-4">
								<div class="text"><s:text name="label.fromDate"/><font color="red">*</font></div>
								<div class="tbox">
									<s:textfield name="fromDate" id="fromDate" cssClass="inputBox datepicker" readonly="true" />
								</div>
							</div>
							<div class="col-xs-12 col-sm-12 col-md-6 col-lg-4">
								<div class="text"><s:text name="label.toDate" /><font color="red">*</font></div>
								<div class="tbox">
									<s:textfield name="toDate" id="toDate" cssClass="inputBox datepicker" readonly="true" />
								</div>
							</div>
							<div class="col-xs-12 col-sm-12 col-md-6 col-lg-4" align="center">
								<br/>
								<input type="button" onclick="getTravelReport();" class="btn btn-sm btn-success" value="Submit" />
							</div>
						</div>
					</div>
					<div class="panel-body">
					<s:if test="reqFrom == 'showlist'">
						<div class="row">
							<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" style="overflow-x: scroll;">
								<table class="footable">
									<thead>
										<tr>
											<th class="no-sort"><s:text name="label.master.sno"/></th>
											<th class="tableColWidth"><s:text name="label.policy.no"/></th>
											<th class="tableColWidth"><s:text name="label.cust.name"/></th>
											<th class="tableColWidth"><s:text name="label.add1"/></th>					
											<th class="tableColWidth"><s:text name="label.add2"/></th>		
											<th class="tableColWidth"><s:text name="label.emirate"/></th>
											<th class="tableColWidth"><s:text name="label.pobox"/></th>
											<th class="tableColWidth"><s:text name="label.mobile"/></th>					
											<th class="tableColWidth"><s:text name="label.mail"/></th>		
											<th class="tableColWidth"><s:text name="label.no.of.days"/></th>
											<th class="tableColWidth"><s:text name="label.entry.date"/></th>
											<th class="tableColWidth"><s:text name="label.eff.date"/></th>					
											<th class="tableColWidth"><s:text name="label.ins.start.date"/></th>		
											<th class="tableColWidth"><s:text name="label.ins.end.date"/></th>
											<th class="tableColWidth"><s:text name="label.schema.name"/></th>
											<th class="tableColWidth"><s:text name="label.opt.id"/></th>					
											<th class="tableColWidth"><s:text name="label.no.of.days1"/></th>		
											<th class="tableColWidth"><s:text name="label.premium"/></th>
											<th class="tableColWidth"><s:text name="label.serial.no"/></th>
											<th class="tableColWidth"><s:text name="label.pass.name"/></th>					
											<th class="tableColWidth"><s:text name="label.dob"/></th>		
											<th class="tableColWidth"><s:text name="label.gender"/></th>
											<th class="tableColWidth"><s:text name="label.relation"/></th>
											<th class="tableColWidth"><s:text name="label.nation"/></th>
											<th class="tableColWidth"><s:text name="label.passport.no"/></th>
											<th class="tableColWidth"><s:text name="label.passport.exp.date"/></th>
										</tr>
										</thead>
										<tbody >
											<s:iterator value="travelReportList" var="list" status="stat">
												<tr>
													<td ><s:property value="#stat.count"/></td>
													<td class="tableColWidth" align="center"><s:property value="#list.POLICY_NO"/></td>
													<td class="tableColWidth" ><s:property value="#list.CUSTOMER_NAME"/></td>
													<td ><s:property value="#list.ADDRESS1" /></td>
													<td ><s:property value="#list.ADDRESS2"/></td>
													<td class="tableColWidth"><s:property value="#list.EMIRATE" /></td>
													<td class="tableColWidth"><s:property value="#list.POBOX"/></td>
													<td align="center"><s:property value="#list.MOBILE" /></td>
													<td class="tableColWidth"><s:property value="#list.EMAIL"/></td>
													<td align="center"><s:property value="#list.NO_OF_DAYS" /></td>
													<td align="center" ><s:property value="#list.ENTRY_DATE"/></td>
													<td align="center"><s:property value="#list.EFFECTIVE_DATE" /></td>
													<td align="center"><s:property value="#list.INSURANCE_START_DATE"/></td>
													<td align="center"><s:property value="#list.INSURANCE_END_DATE" /></td>
													<td class="tableColWidth"><s:property value="#list.SCHEME_NAME"/></td>
													<td class="tableColWidth"><s:property value="#list.OPTION_ID" /></td>
													<td class="tableColWidth" align="center"><s:property value="#list.NO_OF_DAYS_1"/></td>
													<td class="tableColWidth" align="right"><s:property value="#list.PREMIUM" /></td>
													<td class="tableColWidth" align="center"><s:property value="#list.SERIAL_NO"/></td>
													<td class="tableColWidth"><s:property value="#list.PASSENGER_NAME" /></td>
													<td class="tableColWidth" align="center"><s:property value="#list.DOB"/></td>
													<td class="tableColWidth"><s:property value="#list.GENDER" /></td>
													<td class="tableColWidth"><s:property value="#list.RELATION"/></td>
													<td class="tableColWidth"><s:property value="#list.NATIONALITY" /></td>
													<td class="tableColWidth"><s:property value="#list.PASSPORT_NO"/></td>
													<td class="tableColWidth" align="center"><s:property value="#list.PASSPORT_EXP_DATE" /></td>
												</tr>
											</s:iterator>
										</tbody>
								</table>
							</div>
						</div>
						<br class="clear" />
						<div class="row">
							<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6" align="center">
								<input type="button" onclick="fnReport('fromDate','toDate');" class="btn btn-warning btn-sm" value="Excel" />
							</div>
							<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6" align="center">
								<input type="button" onclick="fnBack();" class="btn btn-sm btn-danger" value="Back" />
							</div>
						</div>
					</s:if>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
</s:form>
</body>
<script type="text/Javascript" >
function getTravelReport(){
	    document.form1.action='travelReportAT.action?reqFrom=showlist';
	    document.form1.submit();
}
function fnBack(){
		document.form1.action='homeReportAT.action';
	    document.form1.submit();
}
function fnReport(val){
		document.form1.action='reportReportAT.action';
	    document.form1.submit();
}
</script>
</html>