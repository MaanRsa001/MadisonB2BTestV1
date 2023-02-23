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
<s:form id="opCover" name="opCover" action="" theme="simple">
<div class="container vehDtl">
<div class="Card_Parent ">
	<div class="card card-5">
		<div class="p-3">
			<div class="col-md-12">
				<div class="panel panel-primary">
					<div class="panel-heading">
						<h3><s:text name="label.opCoverMaster"/></h3>
						<hr>
						<s:if test='mode == "list"'>
							<div align="right">
								<input type="button" class="btn btn-sm btn-info" value="Add New" onclick="funEdit('add','');">
							</div>
							<br class="clear" />
						</s:if>
					</div>
					<font color="green"><s:actionmessage cssStyle="list-style: none;"/></font>
					<s:if test='mode == "add" || mode =="edit"'>
						<div class="panel-body">
							<s:if test="hasActionErrors()">
								<font color="red"><s:actionerror cssStyle="list-style: none;"/></font>
							</s:if>
							<div class="row">									
								<div class="col-xs-12 col-sm-12 col-md-6 col-lg-4">
									<div class="form-group">
    									<label><s:text name="label.opCoverDescription"/><font color="red">*</font></label>
    									<s:textfield name="opCoverDesc" id="opCoverDesc" cssClass="form-control" maxlength="250"></s:textfield>
									</div>
								</div>
								<div class="col-xs-12 col-sm-12 col-md-6 col-lg-4">
									<div class="form-group">
    									<label><s:text name="label.coreAppCode"/><font color="red">*</font></label>
    									<s:textfield name="coreAppCode" id="coreAppCode" cssClass="form-control" maxlength="50"></s:textfield>
									</div>
								</div>
					<%--			<div class="col-xs-12 col-sm-12 col-md-6 col-lg-4">
									<div class="form-group">
    									<label><s:text name="label.policySubType"/><font color="red">*</font></label>
    									<s:select name="policySubTypeId" id="policySubTypeId" list="policyTypeList" cssClass="form-control" headerKey="" headerValue="---Select---" listKey="POLICY_SUBTYPE_ID" listValue="POLICYTYPE_DESC_ENGLISH" />
									</div>
								</div>
								<div class="col-xs-12 col-sm-12 col-md-6 col-lg-4">
									<div class="form-group">
    									<label><s:text name="label.opCoverRate"/><font color="red">*</font></label>
    									<s:textfield name="opCoverRate" id="opCoverRate" cssClass="form-control numericOnly" maxlength="50"></s:textfield>
									</div>
								</div>
								<div class="col-xs-12 col-sm-12 col-md-6 col-lg-4">
									<div class="form-group">
    									<label><s:text name="label.master.opDesc"/></label>
    									<s:textfield name="opDesc" id="opDesc" cssClass="form-control" maxlength="150"></s:textfield>
									</div>
								</div>  --%>
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
				<%--				<div class="col-xs-12 col-sm-12 col-md-6 col-lg-4">
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
    									<label><s:text name="label.opCoverIsCalcType" /><font color="red">*</font></label><br/>
										<s:radio list="#{'Y':'Yes','N':'No'}" name="isCalcType" id="isCalcType" value='%{isCalcType==null?"N":isCalcType}' />
									</div>
								</div>	 --%>
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
								<input type="button" class="btn btn-sm btn-success" value="Submit" onclick="funSubmit('<s:property value="mode" />');">&nbsp;&nbsp;&nbsp;
								<input type="button" class="btn btn-sm btn-danger" value="Back" onclick="funBack();" />
							</div>
						</div>
						<s:hidden id="opCoverId" name="opCoverId" />
					</s:if>
					<s:else>
						<div class="panel-body">
							<div class="row">
							<table class="table table-bordered table-hover" id="gridTableMake" cellspacing="0" width="100%" >
								<thead class="bluecolortable">
									<tr>
										<th class="no-sort"><s:text name="label.master.sno"/></th>
										<td><s:text name="label.opCoverDescription"/></td>		
										<td><s:text name="label.coreAppCode"/></td>
										<td><s:text name="lable.master.effectiveDate"/></td>
										<td><s:text name="label.master.status"/></td>
										<td><s:text name="label.master.edit"/></td>
										<td><s:text name="label.master.opDetails"/></td> 
									</tr>
									</thead>
									<tbody class="rowevencolor">
										<s:iterator value="opCoverList" var="list" status="stat">
											<tr>
												<td><s:property value="#stat.count" /></td>
												<td><s:property value="#list.OPCOVER_DESC" /></td>
												<td><s:property value="#list.COREAPP_CODE" /></td>
												<td><s:property value="#list.EFFECTIVE_DATE" /></td>
												<td><s:property value="#list.STATUS" /></td>
												<td style="text-align: center;">
													<input type="button" class="btn btn-warning btn-sm" onclick="funEdit('edit','<s:property value="#list.OPCOVER_ID" />','<s:property value="#list.POLICY_SUBTYPE_ID" />');" value="Modify" />
												</td>
												<td style="text-align: center;">
													<input type="button" class="btn btn-warning btn-sm" onclick="funOpDetails('<s:property value="#list.OPCOVER_ID" />');" value="OP Details" />
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
	function funSubmit(mode){
		document.opCover.action='insOpCoverMotorAdminNew.action?mode='+mode;
		document.opCover.submit();
	}
	function funEdit(mode,val,subtyp){
		document.opCover.action='editOpCoverMotorAdminNew.action?mode='+mode+'&opCoverId='+val+'&policySubTypeId'+subtyp;
		document.opCover.submit();
	}
	function funOpDetails(opId){
		document.opCover.action='opCoverDetailMotorAdminNew.action?opCoverId='+opId;
		document.opCover.submit();
	}
	function funBack(){
		document.opCover.action='opCoverMotorAdminNew.action?mode=list';
		document.opCover.submit();
	}
</script>
</body>
</html>   
