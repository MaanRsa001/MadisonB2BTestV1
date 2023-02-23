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
<s:form id="ncb" name="ncb" action="" theme="simple">
<div class="container vehDtl">
<div class="Card_Parent ">
<div class="card card-5">
	<div class="p-3">
		<div class="col-md-12">
				<div class="panel panel-primary">
					<div class="panel-heading">
						<h3><s:text name="label.ncbMaster"/></h3>
						<hr>
						<s:if test='mode != "add" && mode != "edit"'>
							<div align="right">
								<input type="button" class="btn btn-sm btn-info" value="Add New" onclick="funSubmit('add','');">
							</div>
							<br class="clear" />
						</s:if>
					</div>
					<s:if test='mode != "add" && mode != "edit"'>
						<div class="panel-body">
						<font color="green"><s:actionmessage cssStyle="list-style: none;"/></font>
<!-- 							<div class="row"> -->
								<table class="table table-bordered table-hover" id="gridTableMake" cellspacing="0" width="100%" >
									<thead class="bluecolortable">
									<tr>
										<th class="no-sort"><s:text name="label.master.sno"/></th>
										<th><s:text name="label.ncb.year"/></th>
										<th><s:text name="label.policy.type.name"/></th>
										<th><s:text name="label.master.vehicleType"/></th>
										<th><s:text name="label.insuredValueStart"/></th>
										<th><s:text name="label.insuredValueEnd"/></th>
										<th><s:text name="label.rate"/></th>
										<th><s:text name="lable.master.effectiveDate" /></th>
										<th><s:text name="label.master.status" /></th>
										<th><s:text name="label.master.edit"/></th>
									</tr>
									</thead>
									<tbody class="rowevencolor">
										<s:iterator value="transList" var="list" status="stat">
											<tr>
												<td><s:property value="#stat.count" /></td>
												<td><s:property value="#list.NCBYEAR" /></td>
												<td><s:property value="#list.POLICYTYPE" /></td>
												<td><s:property value="#list.VEHICLE_USAGE" /></td>
												<td><s:property value="#list.INSUREDVALUE_START" /></td>
												<td><s:property value="#list.INSUREDVALUE_END" /></td>
												<td><s:property value="#list.RATE" /></td>
												<td><s:property value="#list.EFFECTIVE_DATE" /></td>
												<td><s:property value="#list.STATUS" /></td>
												
												<td style="text-align: center;">
													<input type="button" class="btn btn-warning btn-sm" onclick="funSubmit('edit','<s:property value="#list.S_NO" />');" value="Modify" />
												</td>
											</tr>
										</s:iterator>
									</tbody>
								</table>								
<!-- 							</div> -->
						</div>
					</s:if>
					<s:else>
						<div class="panel-body">
							<s:if test="hasActionErrors()">
								<font color="red"><s:actionerror cssStyle="list-style: none;"/></font>
							</s:if>
							<div class="row">									
								<font color="green"><s:actionmessage cssStyle="list-style: none;"/></font>
								<div class="col-xs-12 col-sm-12 col-md-6 col-lg-4">
									<div class="form-group">
    									<label><s:text name="label.ncbYear"/><font color="red">*</font></label>
    									<s:textfield name="ncbYear" cssClass="form-control numericOnly" maxlength="2"></s:textfield>
									</div>
								</div>
								<div class="col-xs-12 col-sm-6 col-md-4">
									<label><s:text name="label.master.policyType" /><font color="red">*</font></label>
									<s:select name="policyType" id="policyType" list="policyTypeList" headerKey="" headerValue="---Select---"  listKey="POLICY_SUBTYPE_ID" listValue="POLICYTYPE_DESC_ENGLISH" cssClass="form-control" onchange="hideSupPolicy();"/>
								</div>
								<div class="col-xs-12 col-sm-6 col-md-4">
									<label><s:text name="label.master.vehicleType" /><font color="red">*</font></label>
									<s:select name="vehicleType" id="vehicleType" list="vehicleTypeList" headerKey="" headerValue="---Select---"  listKey="CODE" listValue="CODEDESC" cssClass="form-control"/>
								</div>
								<div class="col-xs-12 col-sm-12 col-md-6 col-lg-4">
									<div class="form-group">
    									<label><s:text name="label.insuredValueStart"/><font color="red">*</font></label>
    									<s:textfield name="suminsuredStart" cssClass="form-control decimalOnly" maxlength="12"></s:textfield>
									</div>
								</div>
								<div class="col-xs-12 col-sm-12 col-md-6 col-lg-4">
									<div class="form-group">
    									<label><s:text name="label.insuredValueEnd"/><font color="red">*</font></label>
    									<s:textfield name="suminsuredEnd" cssClass="form-control decimalOnly" maxlength="12"></s:textfield>
									</div>
								</div>
								<div class="col-xs-12 col-sm-12 col-md-6 col-lg-4">
									<div class="form-group">
    									<label><s:text name="label.rate"/><font color="red">*</font></label>
    									<s:textfield name="ncbRate" cssClass="form-control decimalOnly" maxlength="12"></s:textfield>
									</div>
								</div>
								<div class="col-xs-12 col-sm-12 col-md-6 col-lg-4">
									<div class="form-group">
    									<label><s:text name="label.coreAppCode"/><font color="red">*</font></label>
    									<s:textfield name="coreAppCode" cssClass="form-control" maxlength="10"></s:textfield>
									</div>
								</div>
								<div class="col-xs-12 col-sm-12 col-md-6 col-lg-4">
									<div class="form-group">
    									<label><s:text name="lable.master.effectiveDate" /><font color="red">*</font></label>
    									<s:textfield name="effectiveDate" cssClass="form-control" id="effectiveDate" readonly="true"></s:textfield>
									</div>
								</div>
								<div class="col-xs-12 col-sm-12 col-md-6 col-lg-4">
									<div class="form-group">
    									<label><s:text name="label.master.status" /><font color="red">*</font></label><br/>
										<s:radio list="#{'Y':'Activate','N':'Deactivate'}" name="status" id="status" value='%{status==null?"N":status}' />
									</div>
								</div>
							</div>
						</div>
						<br class="clear" />
						<div class="row">
							<div class="col-xs-12 col-sm-12 col-md-12" align="center">
								<input type="button" class="btn btn-sm btn-success" value="Submit" onclick="funSubmit('insert','');">&nbsp;&nbsp;&nbsp;
								<input type="button" class="btn btn-sm btn-danger" value="Back" onclick="funBack();" />
							</div>
						</div>	
						<s:hidden name="ncbId"></s:hidden>					
					</s:else>					
				</div>
			</div>
		</div>		
	</div>
	</div>
	</div>
	<s:token />
</s:form>
<script type="text/javascript">
	function funSubmit(mode,val){
		if(mode=='edit' || mode=='add'){
			document.ncb.action='editNCBMotorAdminNew.action?mode='+mode+'&ncbId='+val;
		}else if(mode=='insert'){
			document.ncb.action='insNCBMasterMotorAdminNew.action?mode=<s:property value="mode"/>';
		}
		document.ncb.submit();
	}
	function funBack(){
		document.ncb.action='ncbMotorAdminNew.action?mode=list';
		document.ncb.submit();
	}

</script>
</body>
</html>   
