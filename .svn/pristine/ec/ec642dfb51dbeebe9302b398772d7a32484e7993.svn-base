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
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/dataTables/css/dataTables.responsive.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/dataTables/css/dataTables.jqueryui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/dataTables/css/dataTables.tableTools.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/dataTables/js/dataTables.responsive.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/dataTables/js/dataTables.tableTools.js"></script>
	<script type="text/javascript">
		

		$(document).ready(function () {
		  $('#gridTableMake').DataTable({
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
		
		$(document).ready(function () {
			  $('#gridTableMotorReport').DataTable({
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
				$('#fromDate').datepicker({
					todayHighlight: true,
					format: "dd/mm/yyyy"
				}).on('changeDate', function(e){
				    $(this).datepicker('hide');
				});
				$('#toDate').datepicker({
					todayHighlight: true,
					format: "dd/mm/yyyy"
				}).on('changeDate', function(e){
				    $(this).datepicker('hide');
				});
			} catch(err) {console.error(err);}
			
		});
	 </script>
	 <style type="text/css">
	 .tableColWidth {
	 	min-width: 100px;
	 	max-width: 750px;
	 	width: 100px;
	 	white-space: normal;
	 }
	 
	  .tableColWidth1 {
	 	min-width: 500px;
	 	max-width: 750px;
	 	width: 450px;
	 	white-space: normal;
	 }
	 </style>
	 <style type="text/css">
	 .tableColNoWrap {
	 	min-width: 100px;
	 	max-width: 750px;
	 	width: 100px;
	 	white-space: nowrap;
	 }
	 </style>	
</head>
<body>
<s:form name="form1" theme="simple">
<div class="container vehDtl">
<div class="Card_Parent ">
<div class="card card-5">
<div class="p-3">
		<div class="col-md-12">
			<div class="panel panel-primary">
				<div class="panel-heading">
					<h3><s:text name="label.motorReports"/></h3>
					<hr>
				</div>				
				<div class="panel-body">
					<s:if test="hasActionErrors()">
					<div class="row">
						<font color="red"><s:actionerror cssStyle="list-style: none;"/></font>
					</div>
					<br class="clear" />
					</s:if>
					<s:if test="mode != 'showlist'">
						<div class="row">
							<div class="col-xs-12 col-sm-12 col-md-6 col-lg-4">
								<div class="text"><s:text name="label.fromDate"/><font color="red">*</font></div>
								<div class="tbox">
									<s:textfield name="fromDate" id="fromDate" cssClass="form-control" readonly="true" />
								</div>
							</div>
							<div class="col-xs-12 col-sm-12 col-md-6 col-lg-4">
								<div class="text"><s:text name="label.toDate" /><font color="red">*</font></div>
								<div class="tbox">
									<s:textfield name="toDate" id="toDate" cssClass="form-control" readonly="true" />
								</div>
							</div>
							<div class="col-xs-12 col-sm-12 col-md-6 col-lg-4" align="center">
								<br/>
								<input type="button" onclick="getMotorReport('fromDate','toDate','id');" class="btn btn-sm btn-success" value="Submit" />
							</div>
						</div>
					</s:if>
					<s:if test="mode == 'list'">
					<br/>
						<div class="row">
							<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
								<table class="table table-bordered table-hover" id="gridTableMake" cellspacing="0" width="100%" >
									<thead class="bluecolortable">
										<tr>
											<th class="no-sort"><s:text name="label.master.sno"/></th>
											<!--<th><s:text name="label.policyType"/></th>
											--><th><s:text name="label.policyName"/></th>					
											<th><s:text name="label.noofpolicy"/></th>		
											<th><s:text name="label.premium"/></th>
										</tr>
									</thead>
									<tbody class="rowevencolor">
										<s:iterator value="motorReportList" var="list" status="stat">
										<tr>
											<td align="center"><s:property value="#stat.count"/></td>
											<!--<td><s:property value="#list.POLICYTYPE"/></td>
											--><td><s:property value="#list.POLICY_NAME" /></td>
											<td align="center"><a type="button" class="btn btn-sm btn-primary" href="#" onclick="fnClick('<s:property value="#list.POLICYTYPE"/>');" ><s:property value="#list.NO_OF_POLICY"/> </a> </td>
											<td align="right"><s:property value="#list.PREMIUM" /></td>
										</tr>
										</s:iterator>
									</tbody>
								</table>
							</div>
						</div>
					</s:if>
					<div class="panel-body">
					<s:if test="mode == 'showlist'">
					<br/>
						<div class="row">
							<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
								<table class="table table-bordered table-hover" id="gridTableMotorReport" cellspacing="0" width="100%" >
									<thead class="bluecolortable">
										<tr>
											<th class="no-sort"><s:text name="label.master.sno"/></th>
											<th class="tableColWidth"><s:text name="label.policyNumber"/></th>
											<th class="tableColWidth"><s:text name="label.policyName"/></th>
											<th class="tableColWidth"><s:text name="label.policy.start"/></th>					
											<th class="tableColWidth"><s:text name="label.policy.end"/></th>		
											<th class="tableColWidth"><s:text name="label.policy.period"/></th>
											<th class="tableColWidth"><s:text name="label.makename"/></th>					
											<th class="tableColWidth"><s:text name="label.modelname"/></th>		
											<th class="tableColWidth"><s:text name="label.typeofbody"/></th>
											<th class="tableColWidth"><s:text name="label.vehicletypedesc"/></th>
											<th class="tableColWidth"><s:text name="label.manfau.year"/></th>					
											<th class="tableColWidth"><s:text name="label.seating.capacity"/></th>		
											<th class="tableColWidth"><s:text name="label.cubic.capacity"/></th>
											<th class="tableColWidth"><s:text name="label.areaCoverage"/></th>
											<th class="tableColWidth"><s:text name="label.currencytype"/></th>
											<th class="tableColWidth"><s:text name="label.suminsured.value.local"/></th>					
											<th class="tableColWidth"><s:text name="label.electical.si"/></th>
											<th class="tableColWidth"><s:text name="label.non.electrical.si"/></th>					
											<th class="tableColWidth"><s:text name="label.optional.cover"/></th>
											<th class="tableColWidth"><s:text name="label.vehiclerate"/></th>	
											<th class="tableColWidth"><s:text name="label.base.premium"/></th>		
											<th class="tableColWidth"><s:text name="label.policy.fee"/></th>
											<th class="tableColWidth"><s:text name="label.total.premium"/></th>
											<th class="tableColWidth"><s:text name="label.online.discount"/></th>
											<th class="tableColWidth"><s:text name="label.no.claim.bonus"/></th>
											<th class="tableColWidth"><s:text name="label.ncd.value"/></th>
											<th class="tableColWidth"><s:text name="label.claim.yn"/></th>
											<th class="tableColWidth"><s:text name="label.claim.amount"/></th>					
											<th class="tableColWidth"><s:text name="label.driver.id"/></th>		
											<th class="tableColWidth"><s:text name="label.driver.dob"/></th>
											<th class="tableColWidth"><s:text name="label.vehicle.color"/></th>
											<th class="tableColWidth"><s:text name="label.policymode"/></th>
											<th class="tableColWidth"><s:text name="label.paymentMode"/></th>
											<th class="tableColWidth"><s:text name="label.bank.name"/></th>					
											<th class="tableColWidth"><s:text name="label.paymentamount"/></th>		
											<th class="tableColWidth"><s:text name="label.paymentdate"/></th>
											<th class="tableColWidth"><s:text name="label.cheque.no"/></th>
											<th class="tableColWidth"><s:text name="label.noOfEmi"/></th>				
											<th class="tableColWidth"><s:text name="label.intialPA"/></th>		
											<th class="tableColWidth"><s:text name="label.installA"/></th>
											<th class="tableColWidth"><s:text name="label.res.tran"/></th>	
										</tr>
										</thead>
										<tbody class="rowevencolor">
											<s:iterator value="motorReportList" var="list" status="stat">
												<tr>
													<td><s:property value="#stat.count"/></td>
													<td class="tableColNoWrap"><s:property value="#list.POLICY_NO"/></td>
													<td align="center"><s:property value="#list.POLICYTYPENAME"/></td>
													<td align="center"><s:property value="#list.INCEPTION_DATE" /></td>
													<td align="center"><s:property value="#list.EXPIRY_DATE" /></td>
													<td align="center"><s:property value="#list.POLICY_PERIOD" /></td>
													<td align="center"><s:property value="#list.MAKE_NAME" /></td>
													<td align="center"><s:property value="#list.MODEL_NAME"/></td>
													<td align="center"><s:property value="#list.TYPE_OF_BODY_NAME" /></td>
													<td align="center"><s:property value="#list.VEHICLETYPE_DESC"/></td>
													<td align="center"><s:property value="#list.MANUFACTURE_YEAR" /></td>
													<td align="center"><s:property value="#list.SEATING_CAPACITY"/></td>
													<td align="center"><s:property value="#list.CUBIC_CAPACITY" /></td>
													<td align="center"><s:property value="#list.AREA_COVERAGE"/></td>
													<td align="center"><s:property value="#list.CURRENCY_TYPE"/></td>
													<td align="right"><s:property value="getText('{0,number,#,##0.00}',{#list.SUMINSURED_VALUE_LOCAL})" /></td>
													<td align="right"><s:property value="getText('{0,number,#,##0.00}',{#list.ELECTRICAL_SI})"/></td>
													<td align="right"><s:property value="getText('{0,number,#,##0.00}',{#list.NONELECTRICAL_SI})" /></td>
													<td class="tableColWidth1"><s:property value="#list.OPTIONAL_COVER"/></td>
													<td align="right"><s:property value="#list.BASE_RATE"/></td>
													<td align="right"><s:property value="getText('{0,number,#,##0.00}',{#list.PREMIUM})" /></td>
													<td align="right"><s:property value="getText('{0,number,#,##0.00}',{#list.POLICY_FEE})" /></td>
													<td align="right"><s:property value="getText('{0,number,#,##0.00}',{#list.OVER_ALL_PREMIUM})" /></td>
													<td align="right"><s:property value="getText('{0,number,#,##0.00}',{#list.ONLINE_DISCOUNT})" /></td>
													<td align="right"><s:property value="#list.NO_CLAIM_BONUS" /></td>
													<td align="right"><s:property value="getText('{0,number,#,##0.00}',{#list.NCB_DISCOUNT})" /></td>
													<td align="center"><s:property value="#list.CLAIMYN"/></td>
													<td align="right"><s:property value="getText('{0,number,#,##0.00}',{#list.CLAIM_AMOUNT})" /></td>
													<td align="center"><s:property value="#list.DRIVER_ID"/></td>
													<td align="center"><s:property value="#list.DRIVER_DOB" /></td>
													<td align="center"><s:property value="#list.VEHICLE_COLOR"/></td>
													<td align="center"><s:property value="#list.POLICY_MODE"/></td>
													<td align="center"><s:property value="#list.PAYMENT_MODE" /></td>
													<td align="center" class="tableColWidth1"><s:property value="#list.BANK_NAME"/></td>
													<td align="right"><s:property value="#list.PAYMENT_AMOUNT"/></td>
													<td align="center"><s:property value="#list.PAYMENT_DATE"/></td>
													<td align="center"><s:property value="#list.CHEQUE_NO" /></td>
													<td align="center"><s:property value="#list.NO_OF_INSTALLMENT" /></td>
													<td align="right"><s:property value="#list.INITIAL_PAYMENT_AMOUNT"/></td>
													<td align="right"><s:property value="#list.INSTALLMENT_AMOUNT" /></td>
													<td align="center"><s:property value="#list.RES_TRANSACTION_ID"/></td>
												</tr>
											</s:iterator>
										</tbody>
								</table>
							</div>
						</div>
						<br class="clear" />
						<div class="row">
							<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6" align="center">
								<input type="button" onclick="fnReport('policyType','fromDate','toDate');" class="btn btn-warning btn-sm" value="Excel" />
							</div>
							<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6" align="center">
								<input type="button" onclick="getMotorReport();" class="btn btn-sm btn-danger" value="Back" />
							</div>
						</div>
						<s:hidden name="policyType" id="policyType"/>
						<s:hidden name="fromDate"  id="fromDate"/>
						<s:hidden name="toDate"  id="toDate"/>
					</s:if>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
</div>
</div>
</s:form>
</body>
<script type="text/Javascript" >
function getMotorReport(){
	    document.form1.action='getMotorReportAM.action?mode=list';
	    document.form1.submit();
}
function fnClick(val){
		document.form1.action='getMotorReportAM.action?policyType='+val+'&mode=showlist';
	    document.form1.submit();
}
function fnBack(){
		document.form1.action='getMotorReportAM.action?mode=list';
	    document.form1.submit();
}
function fnReport(val){
		document.form1.action='reportReportAM.action';
	    document.form1.submit();
}
</script>
</html>