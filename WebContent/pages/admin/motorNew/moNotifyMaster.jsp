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
				$('#motorPolicyStartDate').datepicker({
					todayHighlight: true,
					format: "dd/mm/yyyy"
				}).on('changeDate', function(e){
				    $(this).datepicker('hide');
				});
				$('#motorPolicyEndDate').datepicker({
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
<s:form id="moNotifyMaster" name="moNotifyMaster" action="" theme="simple">
<div class="container vehDtl">
<div class="Card_Parent ">
<div class="card card-5">
	<div class="p-3">
			<div class="col-md-12">
				<div class="panel panel-primary">
					<div class="panel-heading">
						<h3><s:text name="label.notifyMaster"/></h3>
						<hr>
						<s:if test='mode !="add" && mode !="edit"'>
							<div class="pullRight">
								<input type="button" class="btn btn-sm btn-default" value="Add New" onclick="funSubmit('add','');">
							</div>
							<br class="clear" />
						</s:if>
					</div>
					<s:if test='mode !="add" && mode !="edit"'>
						<div class="panel-body">
							<div class="row">
							<font color="green"><s:actionmessage cssStyle="list-style: none;"/></font>
								<table class="table table-bordered table-hover" id="gridTableMake" cellspacing="0" width="100%" >
									<thead class="bluecolortable">	
									<tr>
										<th class="no-sort"><s:text name="label.master.sno"/></th>
										<th><s:text name="label.msgeDesc"/></th>
										<th><s:text name="label.startDate" /></th>
										<th><s:text name="label.endDate" /></th>
										<th><s:text name="label.master.remarks"/></th>
										<th><s:text name="label.master.status"/></th>
										<th><s:text name="label.master.edit"/></th>
									</tr>
									</thead>
									<tbody class="rowevencolor">
										<s:iterator value="notifyList" var="list" status="stat">
											<tr>
												<td><s:property value="#stat.count" /></td>
												<td><s:property value="#list.MSG_DESCRIPTION" /></td>
												<td><s:property value="#list.START_DATE" /></td>
												<td><s:property value="#list.END_DATE" /></td>
												<td><s:property value="#list.REMARKS" /></td>
												<td><s:property value="#list.STATUS" /></td>
												<td style="text-align: center;">
													<input type="button" class="btn btn-warning btn-sm" onclick="funSubmit('edit','<s:property value="#list.MSG_ID" />');" value="Modify" />
												</td>
											</tr>
										</s:iterator>
									</tbody>
								</table>								
							</div>
						</div>
					</s:if>
					<s:else>
						<div class="panel-body">
							<div class="row">									
								<s:if test="hasActionErrors()">
									<font color="red"><s:actionerror cssStyle="list-style: none;"/></font>
								</s:if>
								<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
									<div class="form-group">
	   									<label><s:text name="label.msgeDesc"/><font color="red">*</font></label>
	   									<s:textarea name="notifyDesc" id="notifyDesc" cssClass="form-control" rows="3"></s:textarea>
									</div>
								</div>
								<div class="col-xs-12 col-sm-12 col-md-6 col-lg-4">
									<div class="form-group">
	    									<label><s:text name="label.startDate" /><font color="red">*</font></label>
	    									<s:textfield name="startDate" cssClass="form-control" id="motorPolicyStartDate" readonly="true"></s:textfield>
									</div>
								</div>
								<div class="col-xs-12 col-sm-12 col-md-6 col-lg-4">
									<div class="form-group">
	    									<label><s:text name="label.endDate" /><font color="red">*</font></label>
	    									<s:textfield name="endDate" cssClass="form-control" id="motorPolicyEndDate" readonly="true"></s:textfield>
									</div>
								</div>
								<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
									<div class="form-group">
	   									<label><s:text name="label.master.remarks"/></label>
	   									<s:textfield name="remarks" cssClass="form-control" maxlength="100"></s:textfield>
									</div>
								</div>
								<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
									<div class="form-group">
	   									<label><s:text name="label.master.status" /><font color="red">*</font></label><br/>
										<s:radio list="#{'Y':'Activate','N':'Deactivate'}" name="status" id="status" value='%{status==null?"N":status}' />
									</div>
								</div>
								<s:hidden name="notifyId"></s:hidden>
							</div>
						</div>
						<br class="clear" />
						<div class="row">
							<div class="col-xs-12 col-sm-12 col-md-12" align="center">
								<input type="button" class="btn btn-sm btn-success" value="Submit" onclick="funSubmit('insert','');">&nbsp;&nbsp;&nbsp;
								<input type="button" class="btn btn-sm btn-danger" value="Back" onclick="funBack();" />
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
		action='notifyEditMotorAdminNew.action?mode='+mode;
	}else if(mode=='edit'){
		action='notifyEditMotorAdminNew.action?mode='+mode+'&notifyId='+val;
	}else if(mode=='insert'){
		action='insertNotifyMotorAdminNew.action?mode=<s:property value="mode"/>';
	}
	else{
		alert("Action is empty");
	}
	document.moNotifyMaster.action=action;
	document.moNotifyMaster.submit();
	}
	function funBack(){
		document.moNotifyMaster.action='getNotifyMotorAdminNew.action';
		document.moNotifyMaster.submit();
	}
</script>
</body>
</html>   
