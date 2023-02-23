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
<s:form id="areaCoverage" name="areaCoverage" action="" theme="simple">
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-12">
				<div class="panel panel-primary">
					<div class="panel-heading">
						<h3><s:text name="label.areaCoverageMaster"/></h3>
						<hr>
						<s:if test='mode !="Add" && mode !="Edit"'>
							<div align="right">
								<input type="button" class="btn btn-sm btn-info" value="Add New" onclick="funSubmit('Add','');">
							</div>
							<br class="clear" />
						</s:if>
					</div>
					<s:if test='mode != "Add" && mode !="Edit"'>
						<div class="panel-body">
							<font color="green"><s:actionmessage cssStyle="list-style: none;"/></font>
								<table class="table table-bordered table-hover" id="gridTableMake" cellspacing="0" width="100%" >
									<thead class="bluecolortable">
									<tr>
										<th class="no-sort"><s:text name="label.master.sno"/></th>
										<th><s:text name="label.coreAppCode"/></th>
										<th><s:text name="label.areaCoverDescriptionEng"/></th>
										<th><s:text name="label.status" /></th>
										<th><s:text name="lable.master.effectiveDate" /></th>
										<th><s:text name="label.master.edit"/></th>
									</tr>
									</thead>
									<tbody class="rowevencolor">
										<s:iterator value="areaCoverageList" var="list" status="stat">
											<tr>
												<td><s:property value="#stat.count" /></td>
												<td><s:property value="#list.AREACOVER_CODE" /></td>
												<td><s:property value="#list.AREACOVER_DESC_ENGLISH" /></td>
												<td><s:property value="#list.STATUS" /></td>
												<td><s:property value="#list.EFFECTIVE_DATE" /></td>
												<td style="text-align: center;">
													<input type="button" class="btn btn-warning btn-sm" onclick="funSubmit('Edit','<s:property value="#list.AREACOVER_ID" />');" value="Modify" />
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
								<s:hidden name="areaCoverageId" id="areaCoverageId"></s:hidden>
								<div class="col-xs-12 col-sm-12 col-md-6 col-lg-4">
									<div class="form-group">
    									<label><s:text name="label.areaCoverDescriptionEng"/><font color="red">*</font></label>
    									<s:textfield name="areaCoverageDescriptionEnglish" id="areaCoverageDescriptionEnglish" cssClass="form-control" maxlength="100"></s:textfield>
									</div>
								</div><%--
								<!--<div class="col-xs-12 col-sm-12 col-md-6 col-lg-4">
									<div class="form-group">
    									<label><s:text name="label.areaCoverDescriptionAra"/></label>
    									<s:textfield name="areaCoverageDescriptionArabic" id="areaCoverageDescriptionArabic" cssClass="form-control" maxlength="200"></s:textfield>
									</div>
								</div>-->
								--%>
								<div class="col-xs-12 col-sm-12 col-md-6 col-lg-4">
									<div class="form-group">
    									<label><s:text name="label.coreAppCode"/><font color="red">*</font></label>
    									<s:textfield name="areaCoverageCode" id="areaCoverageCode" cssClass="form-control" maxlength="10"></s:textfield>
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
    									<s:textfield name="remarks" cssClass="form-control" maxlength="100"></s:textfield>
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
								<input type="button" class="btn btn-sm btn-success" value="Submit" onclick="funSubmit('Insert','');">&nbsp;&nbsp;&nbsp;
								<input type="button" class="btn btn-sm btn-danger" value="Back" onclick="funBack();" />
							</div>
						</div>						
					</s:else>					
				</div>
			</div>
		</div>		
	</div>
</s:form>
<script type="text/javascript">
	function funSubmit(mode,val){
		if(mode == "Add"){
		document.areaCoverage.action='getAreaCoverageEditMotorAdminNew.action?mode='+mode;
		}
	  else if(mode == "Edit"){
		 document.areaCoverage.action='getAreaCoverageEditMotorAdminNew.action?mode='+mode+'&areaCoverageId='+val;
		 }
	  else if(mode == "Insert"){
	     document.areaCoverage.action='getAreaCoverageInsertMotorAdminNew.action?mode=<s:property value="mode"/>';
	  }
	  else{
	    alert("No Action for Submit");
	    }
		document.areaCoverage.submit();
	}
	function funBack(){
		document.areaCoverage.action='areaCoverageMotorAdminNew.action?mode=list';
		document.areaCoverage.submit();
	}
</script>
</body>
</html>   
