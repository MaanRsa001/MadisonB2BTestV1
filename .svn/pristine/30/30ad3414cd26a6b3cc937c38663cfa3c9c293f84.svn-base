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
<s:form id="bankFinance" name="bankFinance" action="" theme="simple">
<div class="container vehDtl">
<div class="Card_Parent ">
<div class="card card-5">
<div class="p-3">
			<div class="col-md-12">
				<div class="panel panel-primary">
					<div class="panel-heading">
						<h3><s:text name="label.bankFinanceMaster"/></h3>
						<hr>
						<s:if test='mode !="add" && mode !="edit"'>
							<div align="right">
								<input type="button" class="btn btn-sm btn-info" value="Add New" onclick="funSubmit('add','');">
							</div>
							<br class="clear" />
						</s:if>
					</div>
					<s:if test='mode !="add" && mode !="edit"'>
						<div class="panel-body">
						<font color="green"><s:actionmessage cssStyle="list-style: none;"/></font>
							<table class="table table-bordered table-hover" id="gridTableMake" cellspacing="0" width="100%" >
								<thead class="bluecolortable">
								<tr>
									<th class="no-sort"><s:text name="label.master.sno"/></th>
									<th><s:text name="label.coreAppCode"/></th>
									<th><s:text name="label.bankName"/></th>
									<!--<th><s:text name="label.bankNameAra"/></th>
									--><th><s:text name="lable.master.effectiveDate" /></th>
									<th><s:text name="label.master.status"/></th>
									<th><s:text name="label.master.edit"/></th>
								</tr>
								</thead>
								<tbody class="rowevencolor">
									<s:iterator value="bankFinanceList" var="list" status="stat">
										<tr>
											<td><s:property value="#stat.count" /></td>
											<td><s:property value="#list.BANK_CODE" /></td>
											<td><s:property value="#list.BANK_NAME_ENGLISH" /></td>
											<!--<td><s:property value="#list.BANK_NAME_ARABIC" /></td>
											--><td><s:property value="#list.EFFECTIVE_DATE" /></td>
											<td><s:property value="#list.STATUS" /></td>
											<td style="text-align: center;">
												<input type="button" class="btn btn-warning btn-sm" onclick="funSubmit('edit','<s:property value="#list.BANK_ID" />');" value="Modify" />
											</td>
										</tr>
									</s:iterator>
								</tbody>
							</table>								
						</div>
					</s:if>
					<s:else>
						<div class="panel-body">
							<s:if test="hasActionErrors()">
								<font color="red"><s:actionerror cssStyle="list-style: none;"/></font>
							</s:if>
							<div class="row">									
								<div class="col-xs-12 col-sm-12 col-md-6 col-lg-4">
									<div class="form-group">
    									<label><s:text name="label.coreAppCode"/><font color="red">*</font></label>
    									<s:textfield name="bankCode" id="bankId" cssClass="form-control" maxlength="10"></s:textfield>
									</div>
								</div>
								<div class="col-xs-12 col-sm-12 col-md-6 col-lg-4">
									<div class="form-group">
    									<label><s:text name="label.bankName"/><font color="red">*</font></label>
    									<s:textfield name="bankNameEnglish" id="bankNameEnglish" cssClass="form-control" maxlength="100"></s:textfield>
									</div>
								</div>
								<div class="col-xs-12 col-sm-12 col-md-6 col-lg-4">
									<div class="form-group">
    									<label><s:text name="label.address1"/><font color="red">*</font></label>
    									<s:textfield name="addressA" id="addressA" cssClass="form-control" maxlength="500"></s:textfield>
									</div>
								</div>
								<div class="col-xs-12 col-sm-12 col-md-6 col-lg-4">
									<div class="form-group">
    									<label><s:text name="label.address2"/><font color="red">*</font></label>
    									<s:textfield name="addressB" id="addressB" cssClass="form-control" maxlength="500"></s:textfield>
									</div>
								</div>
								<div class="col-xs-12 col-sm-12 col-md-6 col-lg-4">
									<div class="form-group">
    									<label><s:text name="label.telephoneNo"/><font color="red">*</font></label>
    									<s:textfield name="telephoneNo" id="telephoneNo" cssClass="form-control numericOnly" maxlength="100"></s:textfield>
									</div>
								</div>
								<!--
								<div class="col-xs-12 col-sm-12 col-md-6 col-lg-4">
									<div class="form-group">
    									<label><s:text name="label.bankNameAra"/></label>
    									<s:textfield name="bankNameArabic" id="bankNameArabic" cssClass="form-control" maxlength="400"></s:textfield>
									</div>
								</div>								
								--><div class="col-xs-12 col-sm-12 col-md-6 col-lg-4">
									<div class="form-group">
    									<label><s:text name="lable.master.effectiveDate" /><font color="red">*</font></label>
    									<s:textfield name="effectiveDate" cssClass="form-control" readonly="true" id="effectiveDate"></s:textfield>
									</div>
								</div>
								<div class="col-xs-12 col-sm-12 col-md-6 col-lg-4">
									<div class="form-group">
    									<label><s:text name="label.master.remarks"/></label>
    									<s:textfield name="remarks" cssClass="form-control" maxlength="100"></s:textfield>
									</div>
								</div>
								<div class="col-xs-12 col-sm-12 col-md-6 col-lg-4">
									<div class="form-group">
    									<label><s:text name="label.master.status" /><font color="red">*</font></label><br/>
										<s:radio list="#{'Y':'Activate','N':'Deactivate'}" name="status" id="status" value='%{status==null?"N":status}' />
									</div>
								</div>
								<s:hidden name="bankId"></s:hidden>
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
		action='getBankFinanceEditMotorAdminNew.action?mode='+mode;
	}else if(mode=='edit'){
		action='getBankFinanceEditMotorAdminNew.action?mode='+mode+'&bankId='+val;
	}else if(mode=='insert'){
		action='bankFinanceInsertMotorAdminNew.action?mode=<s:property value="mode"/>';
	}
	else{
		alert("Action is empty");
	}
	document.bankFinance.action=action;
	document.bankFinance.submit();
	}
	function funBack(){
		document.bankFinance.action='bankFinanceMotorAdminNew.action';
		document.bankFinance.submit();
	}
</script>
</body>
</html>   
