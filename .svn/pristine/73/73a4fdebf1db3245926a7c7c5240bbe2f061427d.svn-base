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
		<script type="text/javascript" src="js/common.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/dataTables/css/dataTables.responsive.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/dataTables/css/dataTables.jqueryui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/dataTables/css/dataTables.tableTools.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/dataTables/js/dataTables.responsive.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/dataTables/js/dataTables.tableTools.js"></script>
		<script type="text/javascript">
			
			
			$(document).ready(function () {
				  $('#gridTable2').DataTable({
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
<s:form id="rateConfig" name="rateConfig" action="" theme="simple">
<div class="container vehDtl">
	<div class="Card_Parent ">
		<div class="card card-5">
			<div class="p-3">
				<div class="col-md-12">
					<div class="panel panel-primary">
						<div class="panel-heading">
							<div class="clearfix">
								<h3><div align="left"><s:text name="label.master.comprehensive.rate"/></div></h3>
								<hr>
								<div align="right"><s:submit cssClass="btn btn-sm btn-info" value="Add New" onclick="funSubmit('addCompreRate','');"></s:submit></div>
							</div>
						</div>
<!-- 						<div class="panel-body"> -->
							<font color="green"><s:actionmessage cssStyle="list-style:none;"/> </font>
							<table class="table table-bordered table-hover" id="gridTable2" cellspacing="0" width="100%" >
								<thead class="bluecolortable">
								<tr>
									<th><s:text name="label.master.sno"></s:text> </th>
									<th><s:text name="label.userName"/></th>
									<th><s:text name="label.master.seating.ton.start" /></th>
									<th><s:text name="label.master.seating.ton.end" /></th>
									<th><s:text name="label.master.cc.gvw.start.range" /></th>
									<th><s:text name="label.master.cc.gvw.end.range" /></th>
									<th><s:text name="label.master.uae.liscence.start" /></th>
									<th><s:text name="label.master.uae.liscence.end" /></th>
									<th><s:text name="label.master.driver.age.from" /></th>
									<th><s:text name="label.master.driver.age.to" /></th>
									<th><s:text name="label.master.suminsured.start" /></th>
									<th><s:text name="label.master.suminsured.end" /></th>
									<th></th>
									<th></th>
									<th><s:text name="label.master.policy.start.date" /></th>
									<th><s:text name="label.master.policy.end.date" /></th>
									<th><s:text name="label.master.motor.rate" /></th>
									<th><s:text name="label.master.minimum.premium" /></th>
									<th><s:text name="label.master.online.percent" /></th>
									<th><s:text name="label.master.deduction.percent" /></th>
									<th><s:text name="label.master.deduction.amount" /></th>
									<th><s:text name="label.master.deduction.description" /></th>
									<th><s:text name="label.status" /></th>
									<th><s:text name="label.master.deduction.entry.date" /></th>
									<th><s:text name="label.master.effective.date" /></th>
									<th><s:text name="label.master.remarks" /></th>
								</tr>
								</thead>
								<tbody class="rowevencolor">
									<s:iterator value="comprehensiveRateList" var="list" status="stat">
										<tr>
											<td><s:property value="#stat.count" /></td>
											<td><s:property value="#list.USERNAME" /></td>
											<td><s:property value="#list.SEATING_TON_START" /></td>
											<td><s:property value="#list.SEATING_TON_END" /></td>
											<td><s:property value="#list.CC_GVW_START_RANGE" /></td>
											<td><s:property value="#list.CC_GVW_END_RANGE" /></td>
											<td><s:property value="#list.UAE_LISCENCE_START" /></td>
											<td><s:property value="#list.UAE_LISCENCE_END" /></td>
											<td><s:property value="#list.DRIVER_AGE_FROM" /></td>
											<td><s:property value="#list.DRIVER_AGE_TO" /></td>
											<td><s:property value="#list.SUMINSURED_START" /></td>
											<td><s:property value="#list.SUMINSURED_END" /></td>
											<td style="text-align: center;">
												<button type="button" class="btn btn-info btn-sm" onclick="openCompreRate('<s:property value="#list.RATE_ID"/>');">View</button>
											</td>
											<td>
												<button type="button" class="btn btn-warning btn-sm" onclick="funSubmit('editCompreRate','<s:property value="#list.RATE_ID"/>');">Modify</button>
											</td>
											<td><s:property value="#list.POLICY_START_DATE" /></td>
											<td><s:property value="#list.POLICY_END_DATE" /></td>
											<td><s:property value="#list.MOTOR_RATE" /></td>
											<td><s:property value="#list.MINIMUM_PREMIUM" /></td>
											<td><s:property value="#list.ONLINE_PERCENT" /></td>
											<td><s:property value="#list.DEDUCTION_PERCENT" /></td>
											<td><s:property value="#list.DEDUCTION_AMOUNT" /></td>
											<td><s:property value="#list.DEDUCTION_DESC" /></td>
											<td><s:property value="#list.STATUS" /></td>
											<td><s:property value="#list.ENTRY_DATE" /></td>
											<td><s:property value="#list.EFFECTIVE_DATE" /></td>
											<td><s:property value="#list.REMARKS" /></td>
										</tr>
									</s:iterator>
								</tbody>
							</table>
<!-- 						</div> -->
						<div align="center">
							<button type="button" class="btn btn-danger btn-sm" onclick="funBack();">Back</button>
						</div>
					</div>
				</div>
			</div>		
		</div>
	</div>
</div>
		<s:hidden name="policyType"></s:hidden>
		<s:hidden name="vehicleType"></s:hidden>
		<s:hidden name="bodyType"></s:hidden>
		<s:hidden name="subRateId"></s:hidden>
	</s:form>
	
<s:iterator value="comprehensiveRateList" var="list" status="stat">
	<div id="viewRateModal<s:property value="#list.RATE_ID"/>" class="modal fade" role="dialog">
		<div class="modal-dialog">
		<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title"> <s:text name="label.master.comprehensive.rate" /> </h4>
				</div>
				<div class="modal-body">
					<div class="table-responsive">
	 					<table class="table table-bordered">
							<tbody>
								<tr>
									<td><s:text name="label.master.seating.ton.start" />	&nbsp;:&nbsp; <s:property value="#list.SEATING_TON_START" /></td>
									<td><s:text name="label.master.seating.ton.end" />	&nbsp;:&nbsp; <s:property value="#list.SEATING_TON_END" /></td>
								</tr>
								<tr>								
									<td><s:text name="label.master.cc.gvw.start.range" />	&nbsp;:&nbsp; <s:property value="#list.CC_GVW_START_RANGE" /></td>
									<td><s:text name="label.master.cc.gvw.end.range" />	&nbsp;:&nbsp; <s:property value="#list.CC_GVW_END_RANGE" /></td>
								</tr>
								<tr>
									<td><s:text name="label.master.uae.liscence.start" />	&nbsp;:&nbsp; <s:property value="#list.UAE_LISCENCE_START" /></td>
									<td><s:text name="label.master.uae.liscence.end" />	&nbsp;:&nbsp; <s:property value="#list.UAE_LISCENCE_END" /></td>
								</tr>
								<tr>
									<td><s:text name="label.master.driver.age.from" />	&nbsp;:&nbsp; <s:property value="#list.DRIVER_AGE_FROM" /></td>
									<td><s:text name="label.master.driver.age.to" />	&nbsp;:&nbsp; <s:property value="#list.DRIVER_AGE_TO" /></td>
								</tr>
								<tr>
									<td><s:text name="label.master.suminsured.start" />	&nbsp;:&nbsp; <s:property value="#list.SUMINSURED_START" /></td>
									<td><s:text name="label.master.suminsured.end" />	&nbsp;:&nbsp; <s:property value="#list.SUMINSURED_END" /></td>
								</tr>
								<tr>									
									<td><s:text name="label.master.policy.start.date" />	&nbsp;:&nbsp; <s:property value="#list.POLICY_START_DATE" /></td>
									<td><s:text name="label.master.policy.end.date" />	&nbsp;:&nbsp; <s:property value="#list.POLICY_END_DATE" /></td>
								</tr>
								<tr>
									<td><s:text name="label.master.motor.rate" />	&nbsp;:&nbsp; <s:property value="#list.MOTOR_RATE" /></td>
									<td><s:text name="label.master.minimum.premium" />	&nbsp;:&nbsp; <s:property value="#list.MINIMUM_PREMIUM" /></td>
								</tr>
								<tr>
									<td><s:text name="label.master.online.percent" />	&nbsp;:&nbsp; <s:property value="#list.ONLINE_PERCENT" /></td>
									<td><s:text name="label.master.deduction.percent" />	&nbsp;:&nbsp; <s:property value="#list.DEDUCTION_PERCENT" /></td>
								</tr>
								<tr>
									<td><s:text name="label.master.deduction.amount" />	&nbsp;:&nbsp; <s:property value="#list.DEDUCTION_AMOUNT" /></td>
									<td><s:text name="label.master.deduction.description" />	&nbsp;:&nbsp; <s:property value="#list.DEDUCTION_DESC" /></td>
								</tr>
								<tr>
									<td><s:text name="label.master.deduction.entry.date" />	&nbsp;:&nbsp; <s:property value="#list.ENTRY_DATE" /></td>
									<td><s:text name="label.master.effective.date" />	&nbsp;:&nbsp; <s:property value="#list.EFFECTIVE_DATE" /></td>
								</tr>
								<tr>
									<td><s:text name="label.status" />	&nbsp;:&nbsp; <s:property value="#list.STATUS" /></td>
									<td><s:text name="label.master.remarks" />	&nbsp;:&nbsp; <s:property value="#list.REMARKS" /></td>
								</tr>
								
							</tbody>
						</table>
					</div>
				</div>				      
			</div>
		</div>
	</div>
</s:iterator>
<script type="text/javascript">
function openSubPolicyListModal(id){
	$('#viewSubPolicyListModal'+id).modal('show');
}
function openCompreRate(id){
	$('#viewRateModal'+id).modal('show');
}
function funSubmit(mode,val){
	var action="";
	if(mode =='addCompreRate'){
		action='editComprehensiveRateMotorAdminNew.action?mode='+mode;
	}else if(mode =='editCompreRate'){
		action='editComprehensiveRateMotorAdminNew.action?mode='+mode+'&rateId='+val;
	}
	else{
		alert("Action is empty");
	}
	document.rateConfig.action=action;
	document.rateConfig.submit();
}
function funBack(){
	document.rateConfig.action='subPolicyMotorAdminNew.action';
	document.rateConfig.submit();
}
</script>
</body>
</html>   
