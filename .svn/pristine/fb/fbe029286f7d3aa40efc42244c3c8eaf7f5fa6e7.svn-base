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
				
			
	$(function() {
		try {
			$('#effectiveDate').datepicker({
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
<s:form id="bodyType" name="bodyType" action="" theme="simple">
<div class="container vehDtl">
<div class="Card_Parent ">
<div class="card card-5">
<div class="p-3">
			<div class="col-md-12">
				<div class="panel panel-primary">
					<div class="panel-heading">
						<h3><s:text name="label.bodyTypeMaster"/></h3>
						<hr>
						<s:if test='mode == "list"'>
							<div align="right">
								<input type="button" class="btn btn-sm btn-info" value="Add New" onclick="funSubmit('add','');">
							</div>
							<br class="clear" />
						</s:if>
						<s:if test='mode == "vehicle"'>
							<div align="right">
								<input type="button" class="btn btn-sm btn-info" value="Add New" onclick="funVehicleAdd('vehicleedit','addBodyVehicleLink','<s:property value="bodyTypeId"/>');">
							</div>
						</s:if>
					</div>
					<s:if test='mode == "add" || mode =="edit" || mode =="vehicleedit"'>
						<div class="panel-body">
						<s:if test="hasActionErrors()">
							<font color="red"><s:actionerror cssStyle="list-style: none;"/></font>
						</s:if>
							<div class="row">									
								<s:if test='mode =="vehicleedit"'>
								<div class="col-xs-12 col-sm-12 col-md-6 col-lg-4">
									<div class="form-group">
    									<label><s:text name="label.bodyName"/><font color="red">*</font></label>
    									<s:textfield name="bodyTypeName" id="bodyTypeName" cssClass="form-control" maxlength="50" readonly='true'></s:textfield>
									</div>
								</div>
								<div class="col-xs-12 col-sm-12 col-md-6 col-lg-4">
									<div class="form-group">
    									<label><s:text name="label.master.status" /><font color="red">*</font></label><br/>
										<s:radio list="#{'Y':'Activate','N':'Deactivate'}" name="status" id="status" value='%{status==null?"N":status}' />
									</div>
								</div>
								<div class="col-xs-12 col-sm-12 col-md-6 col-lg-4">
									<div class="form-group">
    									<label><s:text name="label.master.vehicle" /><font color="red">*</font></label><br/>
    									<s:select name="vehicleIdforBodyType" id="vehicleIdforBodyType" list="vehicleListforBodyTypeMaster" listKey="VTYPE_ID" listValue="VEHICLETYPE_DESC" headerKey="" headerValue="---Select---" cssClass="form-control" /> 
									</div>
								</div>
								<s:if test='reqFrom != "addBodyVehicleLink"'><s:hidden name="bodyTypeId"></s:hidden></s:if>
								</s:if>
								<s:else>
								<div class="col-xs-12 col-sm-12 col-md-6 col-lg-4">
									<div class="form-group">
    									<label><s:text name="label.bodyName"/><font color="red">*</font></label>
    									<s:textfield name="bodyTypeName" id="bodyTypeName" cssClass="form-control" maxlength="50" ></s:textfield>
									</div>
								</div>
								<div class="col-xs-12 col-sm-12 col-md-6 col-lg-4">
									<div class="form-group">
    									<label><s:text name="label.coreAppCode"/><font color="red">*</font></label>
    									<s:textfield name="coreAppCode" id="coreAppCode" cssClass="form-control" maxlength="10"></s:textfield>
									</div>
								</div>
								<div class="col-xs-12 col-sm-12 col-md-6 col-lg-4">
									<div class="form-group">
    									<label><s:text name="lable.master.effectiveDate" /><font color="red">*</font></label>
    									<s:textfield name="effectiveDate" readonly="true" cssClass="form-control" id="effectiveDate"></s:textfield>
									</div>
								</div>
								<div class="col-xs-12 col-sm-12 col-md-6 col-lg-4">
									<div class="form-group">
    									<label><s:text name="label.master.remarks"/></label>
    									<s:textfield name="remarks" cssClass="form-control" maxlength="150"></s:textfield>
									</div>
								</div>
								<div class="col-xs-12 col-sm-12 col-md-6 col-lg-4">
									<div class="form-group">
    									<label><s:text name="label.master.status" /><font color="red">*</font></label><br/>
										<s:radio list="#{'Y':'Activate','N':'Deactivate'}" name="status" id="status" value='%{status==null?"N":status}' />
									</div>
								</div>
								<div class="col-xs-12 col-sm-12 col-md-6 col-lg-4">
									<div class="form-group">
    									<label><s:text name="label.master.referalstatus" /><font color="red">*</font></label><br/>
										<s:radio list="#{'Y':'Referral','N':'Non-Referral'}" name="referralStatus" id="referralStatus" value='%{referralStatus==null?"N":referralStatus}' />
									</div>
								</div>
								</s:else>
							</div>
						</div>
						<br class="clear" />
						<div class="row">
							<div class="col-xs-12 col-sm-12 col-md-12" align="center">
							<s:if test='mode=="edit"'>
								<input type="button" class="btn btn-sm btn-success" value="Submit" onclick="funSubmit('update','<s:property value="bodyTypeId"/>');">&nbsp;&nbsp;&nbsp;
								<input type="button" class="btn btn-sm btn-danger" value="Back" onclick="funBack();" />
							</s:if>
							<s:if test='mode=="add"'>
								<input type="button" class="btn btn-sm btn-success" value="Submit" onclick="funSubmit('addNew','');">&nbsp;&nbsp;&nbsp;
								<input type="button" class="btn btn-sm btn-danger" value="Back" onclick="funBack();" />
							</s:if>
							<s:if test='mode=="vehicleedit"'>
							<s:if test='reqFrom == "addBodyVehicleLink"'>
								<input type="button" class="btn btn-sm btn-success" value="Submit" onclick="funSubmit('vehicleadd','<s:property value="bodyTypeId"/>');">&nbsp;&nbsp;&nbsp;
								<input type="button" class="btn btn-sm btn-danger" value="Back" onclick="funVehicle('vehicle','<s:property value="bodyTypeId" />');" />
							</s:if>
							<s:else>
								<input type="button" class="btn btn-sm btn-success" value="Submit" onclick="funvehicleSubmit('vehicleadd','<s:property value="vehiclebodyTypeId"/>');">&nbsp;&nbsp;&nbsp;
								<input type="button" class="btn btn-sm btn-danger" value="Back" onclick="funVehicle1('vehicle');" />
							</s:else>
							</s:if>
							</div>
						</div>
					</s:if>
					<s:else>
					<s:if test='mode != "vehicle" '>
						<div class="panel-body">
						<font color="green"><s:actionmessage cssStyle="list-style:none;"/> </font>
							<div class="row">
							<table class="table table-bordered table-hover" id="gridTableMake" cellspacing="0" width="100%" >
								<thead class="bluecolortable">
									<tr>
										<th class="no-sort"><s:text name="label.master.sno"/></th>
										<td><s:text name="label.bodyId"/></td>
										<td><s:text name="label.bodyName"/></td>		
										<td><s:text name="label.coreAppCode"/></td>
										<td><s:text name="label.status"/></td>
										<td><s:text name="label.master.referalstatus"/></td>
										<td><s:text name="label.master.edit"/></td>
										<td><s:text name="label.master.vehiclelink"/></td>
									</tr>
									</thead>
									<tbody class="rowevencolor">
										<s:iterator value="bodyTypeMasterList" var="list" status="stat">
											<tr>
												<td><s:property value="#stat.count" /></td>
												<td><s:property value="#list.BODY_ID" /></td>
												<td><s:property value="#list.BODY_NAME" /></td>
												<td><s:property value="#list.COREAPP_CODE" /></td>
												<td><s:property value="#list.STATUS" /></td>
												<td><s:property value="#list.REFERRAL_STATUS" /></td>
												<td style="text-align: center;">
													<input type="button" class="btn btn-warning btn-sm" onclick="funSubmit('edit','<s:property value="#list.BODY_ID" />');" value="Modify" />
												</td>
												<td>
												<input type="button" class="btn btn-warning btn-sm" onclick="funVehicle('vehicle','<s:property value="#list.BODY_ID" />');" value="Vehicle Link" />
												</td>
											</tr>
										</s:iterator>
									</tbody>
								</table>								
							</div>
						</div>
						</s:if>
					</s:else>
					<s:if test='mode == "vehicle"'>
						<div class="panel-body">
						<font color="green"><s:actionmessage cssStyle="list-style:none;"/> </font>
<!-- 							<div class="row"> -->
								<table class="table table-bordered table-hover" id="gridTableMake" cellspacing="0" width="100%" >
									<thead class="bluecolortable">
									<tr>
										<th class="no-sort"><s:text name="label.master.sno"/></th>
										<td><s:text name="label.verticleId"/></td>
										<td><s:text name="label.verticleName"/></td>
										<td><s:text name="label.bodyName"/></td>
										<td><s:text name="label.status"/></td>
										<td><s:text name="label.master.edit"/></td>
									</tr>
									</thead>
									<tbody class="rowevencolor">
										<s:iterator value="vehicleLinkforBodyTypeMaster" var="list" status="stat">
											<tr>
												<td><s:property value="#stat.count" /></td>
												<td><s:property value="#list.VTYPE_ID" /></td>
												<td><s:property value="#list.VEHICLETYPE_DESC" /></td>
												<td><s:property value="#list.TYPE_OF_BODY_NAME" /></td>
												<td><s:property value="#list.STATUS" /></td>
												<td style="text-align: center;">
													<input type="button" class="btn btn-warning btn-sm" onclick="funvehicleSubmit('vehicleedit','<s:property value="#list.TYPE_OF_BODY_ID" />');" value="Modify" />
												</td>
											</tr>
										</s:iterator>
									</tbody>
								</table>								
<!-- 							</div> -->
						</div>
						<div class="row">
							<div class="col-xs-12 col-sm-12 col-md-12" align="center">
								<input type="button" class="btn btn-sm btn-danger" value="Back" onclick="funBack();" />
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
<script type="text/javascript">
	function funSubmit(mode,val){
		document.bodyType.action='bodyTypeMotorAdminNew.action?mode='+mode+'&bodyTypeId='+val;
		document.bodyType.submit();
	}
	function funBack(){
		document.bodyType.action='bodyTypeMotorAdminNew.action?mode=list';
		document.bodyType.submit();
	}
	function funVehicle(mode,val){
		document.bodyType.action='vehicleLinkBodyTypeMotorAdminNew.action?mode='+mode+'&bodyTypeId='+val;
		document.bodyType.submit();
	}
	function funVehicleAdd(mode,val,val1){
	document.bodyType.action='addVehicleLinkBodyTypeMotorAdminNew.action?mode='+mode+'&reqFrom='+val+'&bodyTypeId='+val1;
	document.bodyType.submit();
	}
	function funvehicleSubmit(mode,val){
		document.bodyType.action='modifyMotorAdminNew.action?mode='+mode+'&vehiclebodyTypeId='+val;
		document.bodyType.submit();
	}
	function funVehicle1(mode){
		document.bodyType.action='vehicleLinkBodyTypeMotorAdminNew.action?mode='+mode;
		document.bodyType.submit();
	}
</script>
</body>
</html>   
