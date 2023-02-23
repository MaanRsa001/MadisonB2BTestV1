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
		
	 </script>	
</head>
<body>
<s:form id="conditions" name="conditions" action="" theme="simple">
<div class="container vehDtl">
<div class="Card_Parent ">
<div class="card card-5">
<div class="p-3">
			<div class="col-md-12">
				<div class="panel panel-primary">
					<div class="panel-heading">
						<h3>Motor Conditions Master</h3>
						<hr>
							<div align="right">
								<input type="button" class="btn btn-sm btn-default" value="Add New" onclick="funSubmit('add','');">
							</div>
							<br class="clear" />
					</div>
						<div class="panel-body">
<div class="row">
<s:actionmessage style="color: green;"/>
</div>
								<table class="table table-bordered table-hover" id="gridTableMake" cellspacing="0" width="100%" >
									<thead class="bluecolortable">
									<tr>
										<th class="no-sort"><s:text name="label.master.sno"/></th>
										<th>Policy Type</th>
										<th>Condition Type</th>
										<th>Conditions</th>
										<th>Core App Code</th>
										<th>Status</th>
										<th><s:text name="label.master.edit"/></th>
									</tr>
									</thead>
									<tbody class="rowevencolor">
										<s:iterator value="conditionsList" var="list" status="stat">
											<tr>
												<td><s:property value="#stat.count" /></td>
												<td><s:property value="#list.POLICY_TYPE" /></td>
												<td><s:property value="#list.CONDITION_TYPE" /></td>
												<td><s:property value="#list.CONDITION_DESC" /></td>
												<td><s:property value="#list.COREAPPCODE" /></td>
												<td><s:property value="#list.STATUS" /></td>
												<td style="text-align: center;">
													<input type="button" class="btn btn-warning btn-sm" onclick="funSubmit('edit','<s:property value="#list.SNO" />');" value="Modify" />
												</td>
											</tr>
										</s:iterator>
									</tbody>
								</table>
						</div>	
				</div>
			</div>
		</div>		
	</div>
</div>
</div>
<s:hidden name="mode" id="mode"/>
<s:hidden name="sno" id="sno"/>
</s:form>
<script type="text/javascript">
	function funSubmit(mode, sno){
		try{
			document.conditions.sno.value = sno;
			document.conditions.mode.value = mode;
			document.conditions.action='editCondMotorAdminNew.action';
			document.conditions.submit();
		}catch(err){
			console.log(err);
		}
	}
</script>
</body>
</html>   
