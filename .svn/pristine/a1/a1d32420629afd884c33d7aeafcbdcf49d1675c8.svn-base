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
<s:form id="vehicleType" name="vehicleType" action="" theme="simple">
<div class="container vehDtl">
<div class="Card_Parent ">
	<div class="card card-5">
		<div class="p-3">
			<div class="col-md-12">
				<div class="panel panel-primary">
					<div class="panel-heading">
						<h3><s:text name="label.vehicleTypeMaster"/></h3>
						<hr>
						<s:if test='mode != "add" && mode !="edit"'>
							<div align="right">
								<input type="button" class="btn btn-sm btn-info" value="Add New" onclick="funSubmit('add','');">
							</div>
							<br class="clear" />
						</s:if>
					</div>
					<s:if test='mode == "add" || mode =="edit"'>
						<div class="panel-body">
							<s:if test="hasActionErrors()">
								<font color="red"><s:actionerror cssStyle="list-style: none;"/></font>
							</s:if>
							<div class="row">									

								<div class="col-xs-12 col-sm-12 col-md-6 col-lg-4">
									<div class="form-group">
    									<label><s:text name="label.vehicleTypeDescription"/><font color="red">*</font></label>
    									<s:textfield name="vehicletypeDesc" id="vehicletypeDesc" cssClass="form-control" maxlength="200"></s:textfield>
									</div>
								</div>
								<div class="col-xs-12 col-sm-12 col-md-6 col-lg-4">
									<div class="form-group">
    									<label><s:text name="label.coreAppCode"/><font color="red">*</font></label>
    									<s:textfield name="coreappCode" id="coreappCode" cssClass="form-control" maxlength="50"></s:textfield>
									</div>
								</div>
								<div class="col-xs-12 col-sm-12 col-md-6 col-lg-4">
									<div class="form-group">
    									<label><s:text name="lable.master.effectiveDate" /><font color="red">*</font></label>
    									<s:textfield name="effectiveDate" id="effectiveDate" readonly="true" cssClass="form-control"></s:textfield>
									</div>
								</div>
								<div class="col-xs-12 col-sm-12 col-md-6 col-lg-4">
									<div class="form-group">
    									<label><s:text name="label.master.remarks"/></label>
    									<s:textfield name="remarks" id="remarks" cssClass="form-control" maxlength="100"></s:textfield>
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
    									<label><s:text name="label.master.referral.status" /><font color="red">*</font></label><br/>
										<s:radio list="#{'Y':'Referral','N':'Non-Referral'}" name="referralStatus" id="referralStatus" value='%{referralStatus==null?"N":referralStatus}' />
									</div>
								</div>
							<s:hidden name="vtypeId"></s:hidden>
							</div>
						</div>
						<br class="clear" />
						<div class="row">
							<div class="col-xs-12 col-sm-12 col-md-12" align="center">
								<input type="button" class="btn btn-sm btn-success" value="Submit" onclick="funSubmit('insert','');">&nbsp;&nbsp;&nbsp;
								<input type="button" class="btn btn-sm btn-danger" value="Back" onclick="funBack();" />
							</div>
						</div>
					</s:if>
					<s:else>
						<div class="panel-body">
							<font color="green"><s:actionmessage cssStyle="list-style: none;"/></font>
							<div class="row">
							<table class="table table-bordered table-hover" id="gridTableMake" cellspacing="0" width="100%" >
								<thead class="bluecolortable">
									<tr>
										<th class="no-sort"><s:text name="label.master.sno"/></th>
										<th><s:text name="label.vehicleTypeId"/></th>
										<th><s:text name="label.vehicleTypeDescription"/></th>		
										<th><s:text name="label.coreAppCode"/></th>
										<th><s:text name="lable.master.effectiveDate"/></th>
										<th><s:text name="label.master.referral.status"/></th>
										<th><s:text name="label.master.status"/></th>
										<th><s:text name="label.master.edit"/></th>
									</tr>
									</thead>
									<tbody class="rowevencolor">
										<s:iterator value="vehicleTypeList" var="list" status="stat">
											<tr>
												<td><s:property value="#stat.count"/></td>
												<td><s:property value="#list.VTYPE_ID"/></td>
												<td><s:property value="#list.VEHICLETYPE_DESC" /></td>
												<td><s:property value="#list.CORE_APP_CODE" /></td>
												<td><s:property value="#list.EFFECTIVE_DATE" /></td>
												<td><s:property value="#list.REFERRAL_STATUS" /></td>
												<td><s:property value="#list.STATUS" /></td>
												<td style="text-align: center;">
													<input type="button" class="btn btn-warning btn-sm" onclick="funSubmit('edit','<s:property value="#list.VTYPE_ID"/>');" value="Modify" />
												</td>
											</tr>
										</s:iterator>
									</tbody>
								</table>
							</div>
						</div>
					</s:else>
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
	 if(mode=='add'){
		action='getVehicleTypeEditMotorAdminNew.action?mode='+mode;
	}else if(mode=='edit'){
		action='getVehicleTypeEditMotorAdminNew.action?mode='+mode+'&VtypeId='+val;
	}else if(mode=='insert'){
		action='vehicleTypeInsertMotorAdminNew.action?mode=<s:property value="mode"/>';
	}
	else{
		alert("Action is empty");
	}
	document.vehicleType.action=action;
	document.vehicleType.submit();
	}
	
	function funBack(){
		document.vehicleType.action='vehicleTypeMotorAdminNew.action';
		document.vehicleType.submit();
	}
</script>
</body>
</html>   
