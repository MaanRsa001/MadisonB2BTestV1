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
				$('#startDate').datepicker({
					todayHighlight: true,
					format: "dd/mm/yyyy"
				}).on('changeDate', function(e){
				    $(this).datepicker('hide');
				});
				$('#endDate').datepicker({
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
<s:form id="opCover" name="opCover" action="" theme="simple">
<div class="container vehDtl">
<div class="Card_Parent ">
<div class="card card-5">
<div class="p-3">
			<div class="col-md-12">
				<div class="panel panel-primary">
					<div class="panel-heading">
						<h3><s:text name="label.opCoverDetail"/></h3>
						<hr>
						<s:if test='mode == "list"'>
							<div align="right">
								<input type="button" class="btn btn-sm btn-info" value="Add New" onclick="funEdit('add','<s:property value="opCoverId" />','');">
							</div>
							<br class="clear" />
						</s:if>
					</div>
					<s:if test='mode == "add" || mode =="edit"'>
						<div class="panel-body" >
						<s:if test="hasActionErrors()">
							<font color="red"><s:actionerror cssStyle="list-style: none;"/></font>
						</s:if>
							<div class="row">									
								<div class="col-xs-12 col-sm-12 col-md-6 col-lg-4">
									<div class="form-group">
    									<label><s:text name="label.group"/><font color="red">*</font></label>
    									<s:select name="groupId" id="groupId" list="groupOpCoverList" cssClass="form-control" headerKey="" headerValue="---Select---" listKey="GROUP_ID" listValue="GROUP_DESC_ENGLISH" />
									</div>
								</div>
								<div class="col-xs-12 col-sm-12 col-md-6 col-lg-4">
									<div class="form-group">
    									<label><s:text name="label.policySubType"/><font color="red">*</font></label>
    									<s:select name="policySubTypeId" id="policySubTypeId" list="policyTypeList" cssClass="form-control" headerKey="" headerValue="---Select---" listKey="POLICY_SUBTYPE_ID" listValue="POLICYTYPE_DESC_ENGLISH" disabled='%{(mode =="edit")?"true":"false"}'/>
									</div>
								</div>
								<div class="col-xs-12 col-sm-12 col-md-6 col-lg-4">
									<div class="form-group">
    									<label><s:text name="label.master.opDesc"/></label>
    									<s:textfield name="opDesc" id="opDesc" cssClass="form-control" maxlength="150"></s:textfield>
									</div>
								</div>
								<div class="col-xs-12 col-sm-12 col-md-6 col-lg-4">
									<div class="form-group">
    									<label><s:text name="label.opCoverIsCalcType" /><font color="red">*</font></label><br/>
										<s:radio list="#{'Y':'Yes','N':'No'}" name="isCalcType" id="isCalcType" value='%{isCalcType==null?"N":isCalcType}' onchange="getCalciType(this.value);" />
									</div>
								</div>
								<div class="col-xs-12 col-sm-12 col-md-6 col-lg-4" id="opCoverRate">
									<div class="form-group">
    									<label><s:text name="label.opCoverRate"/><font color="red">*</font></label>
    									<s:textfield name="opCoverRate" id="opCoverRate" cssClass="form-control decimalOnly" maxlength="50"></s:textfield>
									</div>
								</div>	
								<div class="col-xs-12 col-sm-12 col-md-6 col-lg-4" id="opCoverAmount" >
									<div class="form-group">
    									<label><s:text name="label.opCoverAmount"/><font color="red">*</font></label>
    									<s:textfield name="opCoverAmount" id="opCoverAmount" cssClass="form-control numericOnly" maxlength="50"></s:textfield>
									</div>
								</div>
								<div class="col-xs-12 col-sm-12 col-md-6 col-lg-4">
									<div class="form-group">
    									<label><s:text name="label.startDatee" /><font color="red">*</font></label>
    									<s:textfield name="startDate" readonly="true" cssClass="form-control" id="startDate"></s:textfield>
									</div>
								</div>
								<div class="col-xs-12 col-sm-12 col-md-6 col-lg-4">
									<div class="form-group">
    									<label><s:text name="label.endDatee" /><font color="red">*</font></label>
    									<s:textfield name="endDate" readonly="true" cssClass="form-control" id="endDate"></s:textfield>
									</div>
								</div>
								<div class="col-xs-12 col-sm-12 col-md-6 col-lg-4">
									<div class="form-group">
    									<label><s:text name="label.coreAppCode"/><font color="red">*</font></label>
    									<s:textfield name="coreAppCode" id="coreAppCode" cssClass="form-control" maxlength="50"></s:textfield>
									</div>
								</div>
								<div class="col-xs-12 col-sm-12 col-md-6 col-lg-4">
									<div class="form-group">
    									<label><s:text name="label.opCoverIsSelected" /><font color="red">*</font></label><br/>
										<s:radio list="#{'Y':'Yes','N':'No'}" name="isSelected" id="isSelected" value='%{isSelected==null?"N":isSelected}' />
									</div>
								</div>	
								<div class="col-xs-12 col-sm-12 col-md-6 col-lg-4">
									<div class="form-group">
    									<label><s:text name="label.opCoverIsAddon" /><font color="red">*</font></label><br/>
										<s:radio list="#{'Y':'Yes','N':'No'}" name="isAddon" id="isAddon" value='%{isAddon==null?"N":isAddon}' />
									</div>
								</div>	
								<div class="col-xs-12 col-sm-12 col-md-6 col-lg-4">
									<div class="form-group">
    									<label><s:text name="label.opCoverIsDeletable" /><font color="red">*</font></label><br/>
										<s:radio list="#{'Y':'Yes','N':'No'}" name="isDeletable" id="isDeletable" value='%{isDeletable==null?"N":isDeletable}' />
									</div>
								</div>	
								<div class="col-xs-12 col-sm-12 col-md-6 col-lg-4">
									<div class="form-group">
    									<label><s:text name="label.master.status" /><font color="red">*</font></label>
										<s:radio list="#{'Y':'Activate','N':'Deactivate'}" name="status" id="status" value='%{status==null?"N":status}' />
									</div>
								</div>
							</div>
						</div>
						<br class="clear" />
						<div class="row">
							<div class="col-xs-12 col-sm-12 col-md-12" align="center">
								<input type="button" class="btn btn-sm btn-success" value="Submit" onclick="disableForm(this.form,false,'');funSubmit('<s:property value="mode" />','<s:property value="opCoverSubId" />');">&nbsp;&nbsp;&nbsp;
								<input type="button" class="btn btn-sm btn-danger" value="Back" onclick="funBackDet();" />
							</div>
						</div>
					</s:if>
					<s:else>
						<div class="panel-body">
							<div class="row">
							<font color="green"><s:actionmessage cssStyle="list-style: none;"/></font>
								<table class="table table-bordered table-hover" id="gridTableMake" cellspacing="0" width="100%" >
									<thead class="bluecolortable">
									<tr>
										<th class="no-sort"><s:text name="label.master.sno"/></th>
										<th><s:text name="label.group"/></th>
										<th><s:text name="label.opCoverDescription"/></th>
										<th><s:text name="Policy SubType"/></th>		
										<th><s:text name="Rate"/></th>
										<th><s:text name="label.Amount"/></th>			
										<th><s:text name="label.master.status"/></th>
										<th><s:text name="label.master.edit"/></th>
									</tr>
									</thead>
									<tbody class="rowevencolor">
										<s:iterator value="opCoverDetailList" var="list" status="stat">
											<tr>
												<td><s:property value="#stat.count" /></td>
												<td><s:property value="#list.GROUP_DESC_ENGLISH" /></td>
												<td><s:property value="#list.OPCOVER_DESC" /></td>
												<td><s:property value="#list.POLICYTYPE_DESC_ENGLISH" /></td>
												<td><s:property value="#list.RATE" /></td>
												<td><s:property value="#list.AMOUNT" /></td>
												<td><s:property value="#list.STATUS" /></td>
												<td style="text-align: center;">
													<input type="button" class="btn btn-warning btn-sm" onclick="funEdit('edit','<s:property value="#list.OPCOVER_ID" />','<s:property value="#list.OPCOVERSUB_ID" />');" value="Modify" />
												</td>
											</tr>
										</s:iterator> 
										
									</tbody>
								</table>	
							</div>
						</div>
						<br class="clear" />
						<div class="row">
							<div class="col-xs-12 col-sm-12 col-md-12" align="center">
								<input type="button" class="btn btn-sm btn-danger" value="Back" onclick="funBack();" />
							</div>
						</div>
					</s:else>
					<br class="clear" />
					<s:hidden id="opCoverId" name="opCoverId" />
				</div>		
			</div>
		</div>
	</div>
</div>
</div>
</s:form>
<script type="text/javascript">
	
	function funSubmit(mode,subtyp){
		document.opCover.action='insOpCoverDetailMotorAdminNew.action?mode='+mode+'&opCoverSubId='+subtyp;
		document.opCover.submit();
	}
	function funBack(){
		document.opCover.action='opCoverMotorAdminNew.action?mode=list';
		document.opCover.submit();
	}
	function funBackDet(){
		document.opCover.action='opCoverDetailMotorAdminNew.action?mode=list';
		document.opCover.submit();
	}
	function funEdit(mode,val,subtyp){
		document.opCover.action='editOpCoverDetailMotorAdminNew.action?mode='+mode+'&opCoverSubId='+subtyp;
		document.opCover.submit();
	}
	function getCalciType(val) {
		if(val == 'Y') {
			document.getElementById('opCoverAmount').style.display = 'none';
			document.getElementById('opCoverRate').style.display = 'block';
		} else {
			document.getElementById('opCoverAmount').style.display = 'block';
			document.getElementById('opCoverRate').style.display = 'none';
		}
	}
	<s:if test='isCalcType!=null && !"".equals(isCalcType)'> 
		getCalciType('<s:property value="isCalcType"/>');
	</s:if>
	<s:else>
	    getCalciType('N');
	</s:else>
	 
</script>
</body>
</html>   
