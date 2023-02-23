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
				  $('#gridTable').DataTable({
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
				<div class="col-xs-12">
					<div class="panel panel-primary">
						<div class="panel-heading">
							<h3><s:text name="label.master.comprehensiveRateConfig"/></h3>
							<hr>
						</div>
						<div class="panel-body">
							<font color="red"><s:actionerror cssStyle="list-style:none;"/> </font>
							<div class="row">
								<div class="col-xs-12 col-sm-6 col-md-3">
									<s:text name="label.master.policyType" /> <br/>
									<s:select name="policyType" id="policyType" list="policyTypeList" headerKey="" headerValue="---Select---"  listKey="POLICY_SUBTYPE_ID" listValue="POLICYTYPE_DESC_ENGLISH" cssClass="form-control" onchange="hideSupPolicy();"/>
								</div>
								<div class="col-xs-12 col-sm-6 col-md-3">
									<s:text name="label.master.vehicleType" /> <br/>
									<s:select name="vehicleType" id="vehicleType" list="vehicleTypeList" headerKey="" headerValue="---Select---"  listKey="CODE" listValue="CODEDESC" cssClass="form-control" onchange="ajax(this.value,'bodyType');hideSupPolicy();"/>
								</div>
								<div class="col-xs-12 col-sm-6 col-md-3" id="bodyType">
									<s:text name="label.master.bodyType" /> <br/>
									<s:select list="#{}"  name="bodyType" cssClass="form-control" headerKey="" headerValue="---Select---" />
								</div>
								<div class="col-xs-12 col-sm-6 col-md-3" align="left">
									<s:text name="" /> <br/>
									<s:submit cssClass="btn btn-sm btn-success" value="Submit" onclick="funSubmit('subPolicyList','');"></s:submit>
								</div>
							</div> <br/>
							<s:if test='subPolicyList!=null'>
								<div class="panel panel-primary" id="subPolicyListGrid">
									<div class="panel-heading">
										<div class="clearfix">
											<h4><div align="left"><s:text name="lable.master.subPolicytype.master"/></div></h4>
											<div align="right"><s:submit cssClass="btn btn-sm btn-info" value="Add New" onclick="funSubmit('addSubPolicy','');"></s:submit></div>
										</div>
									</div>
									<div class="panel-body">
										<font color="green"><s:actionmessage cssStyle="list-style:none;"/> </font>
										<div class="row" id="gridTableRow">
											<table class="table table-bordered table-hover" id="gridTable" cellspacing="0" width="100%" >
												<thead class="bluecolortable">
												<tr>
													<th class="no-sort"><s:text name="label.master.sno"/></th>
													<td><s:text name="label.master.vehicle.start.age"/></td>		
													<td><s:text name="label.master.vehicle.end.age"/></td>
													<td><s:text name="label.vehicle.count"/></td>
													<td><s:text name="label.master.status"/></td>
													<td style="text-align: center;"></td>
													<td><s:text name="label.master.rate"/></td>
													<td><s:text name="lable.master.effectiveDate"/></td>
													<td><s:text name="label.master.description"/></td>
												</tr>
												</thead>
												<tbody class="rowevencolor">
													<s:iterator value="subPolicyList" var="list" status="stat">
														<tr>
															<td><s:property value="#stat.count" /></td>
															<td><s:property value="#list.AGE_OF_VEHICLE_START" /></td>
															<td><s:property value="#list.AGE_OF_VEHICLE_END" /></td>
															<td><s:property value="#list.VEHICLE_COUNT" /></td>
															<td><s:property value="#list.STATUS" /></td>
															<td style="text-align: center;">
																<button type="button" class="btn btn-info btn-sm" onclick="openSubPolicyListModal('<s:property value="#list.SUB_RATE_ID" />');">View</button> 
																<button type="button" class="btn btn-warning btn-sm" onclick="funSubmit('editSubPolicy','<s:property value="#list.SUB_RATE_ID" />');">Modify</button>
															</td>
															<td style="text-align: center;"><button type="button" class="btn btn-primary btn-sm" onclick="funSubmit('compreRateList','<s:property value="#list.SUB_RATE_ID"/>');" >Rate</button></td>
															<td><s:property value="#list.EFFECTIVE_DATE" /></td>
															<td><s:property value="#list.DESCRIPTION" /></td>
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
	</s:form>
	<s:iterator value="subPolicyList" var="list" status="stat">
		<div id='viewSubPolicyListModal<s:property value="#list.SUB_RATE_ID" />' class="modal fade" role="dialog">
			<div class="modal-dialog">
			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title"> <s:text name="Sub Policy List" /> </h4>
				</div>
				<div class="modal-body">
					<div class="table-responsive">
	  					<table class="table table-bordered">
							<tbody>
							<tr>
								<td> <s:text name="label.master.vehicle.start.age" /> &nbsp;:&nbsp; <s:property value="#list.AGE_OF_VEHICLE_START" /> </td>
								<td> <s:text name="label.master.vehicle.end.age" /> &nbsp;:&nbsp; <s:property value="#list.AGE_OF_VEHICLE_END" /> </td>
							</tr>
							<tr>
								<td> <s:text name="lable.master.effectiveDate" /> &nbsp;:&nbsp;<s:property value="#list.EFFECTIVE_DATE" /></td>
								<td> <s:text name="label.master.status" /> &nbsp;:&nbsp;<s:property value="#list.STATUS" /> </td>
							</tr>
							<tr>
								<td colspan="2">
									<s:text name="label.master.description" /> &nbsp;:&nbsp; <s:property value="#list.DESCRIPTION" />
								</td>
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
	if(mode =='subPolicyList'){
		action='subPolicyMotorAdminNew.action?mode='+mode;
	}else if(mode=='addSubPolicy'){
		action='editSubPolicyMotorAdminNew.action?mode='+mode;
	}else if(mode=='editSubPolicy'){
		action='editSubPolicyMotorAdminNew.action?mode='+mode+'&subRateId='+val;
	}else if(mode=='compreRateList'){
		action='comprehensiveMotorAdminNew.action?mode='+mode+'&subRateId='+val;
	}else{
		alert("Action is empty");
	}
	document.rateConfig.action=action;
	document.rateConfig.submit();
}
ajax('<s:property value="vehicleType"/>','bodyType');
function ajax(val,id){
	var action='';
	if(id=='bodyType'){
		action=	'?vehicleType='+val+'&bodyType=<s:property value="bodyType"/>';
	}
	postRequest('${pageContext.request.contextPath}/ajaxMotorAdminNew.action'+action+'&reqFrom='+id, id);
}
function hideSupPolicy(){
	$("#subPolicyListGrid").hide();
}
</script>
</body>
</html>   
