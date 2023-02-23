<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE HTML>
<%@ taglib uri="/struts-tags" prefix="s"%>
<html>
	<head>
	
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/dataTables/css/dataTables.responsive.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/dataTables/css/dataTables.jqueryui.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/dataTables/css/dataTables.tableTools.css">
	<script type="text/javascript" src="${pageContext.request.contextPath}/dataTables/js/dataTables.responsive.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/dataTables/js/dataTables.tableTools.js"></script>
	<script type="text/javascript">
		
			$(document).ready(function () {
				  $('#gridTable').DataTable({
					  "responsive": false,
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
							<h3>
								<div align="left"><s:text name="label.master.third.party.configuration"/></div>
								</h3>
								<hr>
								<s:if test='thirdPartyList != null'>
								<div align="right"><s:submit cssClass="btn btn-sm btn-info" value="Add New" onclick="funSubmit('add','');"></s:submit></div>
								</s:if>
							</div>
						</div>
						<div class="panel-body">
							<font color="red"><s:actionerror cssStyle="list-style:none;"/> </font>
							<div class="row">
								<div class="col-xs-12 col-sm-6 col-md-3">
									<s:text name="label.master.vehicleType" /> <br/>
									<s:select name="vehicleType" id="vehicleType" list="vehicleTypeList" headerKey="" headerValue="---Select---"  listKey="CODE" listValue="CODEDESC" cssClass="form-control" onchange="ajax(this.value,'bodyType');hideSupPolicy();"/>
								</div>
								<div class="col-xs-12 col-sm-6 col-md-3">
									<s:text name="label.master.bodyType" /> <br/>
									<s:select list="#{}" id="bodyType" name="bodyType" cssClass="form-control" headerKey="" headerValue="---Select---" />
								</div>
								<div class="col-xs-12 col-sm-6 col-md-3" align="left">
									<s:text name="" /> <br/>
									<s:submit cssClass="btn btn-sm btn-success" value="Submit" onclick="funSubmit('thirdPartyList','');"></s:submit>
								</div>
							</div> <br/>
							<s:if test='thirdPartyList != null'>
								<font color="green"><s:actionmessage cssStyle="list-style:none;"/> </font>
									<table class="table table-bordered table-hover" id="gridTable" >
										<thead class="bluecolortable">
											<tr>
												<th class="no-sort"></th>
												<th><s:text name="label.master.sno"/></th>
												<th><s:text name="label.seating.cylinder.start"/></th>
												<th><s:text name="label.seating.cylinder.end"/></th>
												<th><s:text name="label.third.party.rate"/></th>
												<th><s:text name="label.userName"/></th>
												<th><s:text name="label.master.core.app.code"/></th>
												<th><s:text name="lable.master.effectiveDate"/></th>
												<th><s:text name="label.master.status"/></th>
												<th style="text-align: center;"></th>
											</tr>
										</thead>
										<tbody class="rowevencolor">
											<s:iterator value="thirdPartyList" var="list" status="stat">
												<tr>
													<th></th>
													<td><s:property value="#stat.count" /></td>
													<td><s:property value="#list.SEATING_CYLINDER_START" /></td>
													<td><s:property value="#list.SEATING_CYLINDER_END" /></td>
													<td><s:property value="#list.THIRD_PARTY_RATE" /></td>
													<td><s:property value="#list.USERNAME" /></td>
													<td><s:property value="#list.COREAPP_CODE" /></td>
													<td><s:property value="#list.EFFECTIVE_DATE" /></td>
													<td><s:property value="#list.STATUS" /></td>
													<td style="text-align: center;">
														<button type="button" class="btn btn-warning btn-sm" onclick="funSubmit('edit','<s:property value="#list.THIRD_PARTY_ID" />');">Modify</button>
													</td>
												</tr>
											</s:iterator>
										</tbody>
									</table>
							</s:if>
						</div>
					</div>
				</div>
			</div>		
		</div>
		</div>
		</div>
	</s:form>
<script type="text/javascript">
	function funSubmit(mode,val){
		var action="";
		if(mode =='thirdPartyList'){
			action='thirdPartyMotorAdminNew.action?mode='+mode;
		}else if(mode=='add'){
			action='editThirdPartyMotorAdminNew.action?mode='+mode;
		}else if(mode=='edit'){
			action='editThirdPartyMotorAdminNew.action?mode='+mode+'&thirdPartyId='+val;
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
		$("#gridTableRow").hide();
	}
</script>
</body>
</html>   
