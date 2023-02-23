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
		 </script>	
	</head>
	<body>
	<s:form id="constant" name="constant" action="" theme="simple">
		

<div class="container vehDtl">
<div class="Card_Parent ">
	<div class="card card-5">
		<div class="p-3">
				<div class="col-md-12">
					<div class="panel panel-primary">
						<div class="panel-heading">
							<h3><s:text name="label.master.constant.detail.configuration"/></h3>
							<hr>
							<s:if test='mode != "add" && mode !="edit"'>
							<div class="pull-right"><s:submit cssClass="btn btn-sm btn-info" value="Add New" onclick="funSubmit('add','');" ></s:submit>
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
									<s:if test="mode == 'add'">
									<div class="col-xs-12 col-sm-6 col-md-6">
										<div class="text"><s:text name="label.item.type" /><font color="red">*</font></div>
										<div class="tbox">
											<div class="row">
												<div class="col-xs-6">
													<s:select name="itemType" id="itemType" list="ItemTypeList" headerKey="" headerValue="---Select---" listKey="ITEM_TYPE" listValue="ITEM_TYPE" cssClass="form-control" onchange='ItemTypeChange(this.value);' />
												</div>
												<div class="col-xs-6">
													<input type="text" name="item" id="item" class="form-control" style="display:none"/>
												</div>
											</div>
										</div>
									</div>
									</s:if>
									<s:if test="mode == 'edit'">
									<div class="col-xs-12 col-sm-6 col-md-6">
										<div class="text"><s:text name="label.item.type" /></div>
										<div class="tbox">
											<s:textfield name="itemType" cssClass="form-control" maxlength="50" readonly="true"></s:textfield>
										</div>
									</div>
									</s:if>
									<div class="col-xs-12 col-sm-6 col-md-6">
										<div class="text"><s:text name="label.item.value"/><font color="red">*</font></div>
										<div class="tbox"><s:textfield name="itemValue" cssClass="form-control" maxlength="50"></s:textfield></div>
									</div>
									<div class="col-xs-12 col-sm-6 col-md-6">
										<div class="text"><s:text name="label.item.desc"/><font color="red">*</font></div>
										<div class="tbox"><s:textfield name="itemDesc" cssClass="form-control" maxlength="50"></s:textfield></div>
									</div>
									<div class="col-xs-12 col-sm-6 col-md-6">
										<div class="text"><s:text name="label.param1"/></div>
										<div class="tbox"><s:textfield name="param1" cssClass="form-control" maxlength="50"></s:textfield></div>
									</div>
									<div class="col-xs-12 col-sm-6 col-md-6">
										<div class="text"><s:text name="label.param2"/></div>
										<div class="tbox"><s:textfield name="param2" cssClass="form-control" maxlength="50"></s:textfield></div>
									</div>
									<div class="col-xs-12 col-sm-6 col-md-6">
										<div class="text"><s:text name="label.master.status" /><font color="red">*</font></div>
										<div class="tbox"><s:radio list="#{'Y':'Activate','N':'Deactivate'}" name="status" id="status" value='%{status==null?"N":status}' /></div>
									</div>
									 <br/>
									 <div class="col-xs-12 col-sm-6 col-md-6" align="center">
									 <br/>
									</div>
									<div class="col-xs-12 col-sm-6 col-md-6" align="center">
									<br/>
									</div>
									<div class="col-xs-12 col-sm-12 col-md-12" align="center">
										<s:submit cssClass="btn btn-sm btn-success" value="Submit" onclick="funSubmit('insert','');"></s:submit> &nbsp;&nbsp;&nbsp;
										<s:submit cssClass="btn btn-sm btn-danger" value="Back" onclick="funBack();"></s:submit>
									</div>
								</div>
								 <br/>
							</div>
							<s:hidden name="itemCode"></s:hidden>
							<s:hidden name="itemId"></s:hidden>
						</s:if>
						<s:else>
							<div class="panel-body">
									<div class="panel panel-primary">
											<font color="green"><s:actionmessage cssStyle="list-style:none;"/> </font>
											<div class="row">
												<table class="table table-bordered table-hover" id="gridTableMake" cellspacing="0" width="100%" >
													<thead class="bluecolortable">
													<tr>
														<th><s:text name="label.master.sno"/></th>
														<th><s:text name="label.item.type"/></th>
														<th><s:text name="label.item.value"/></th>		
														<th><s:text name="label.item.desc"/></th>
														<th><s:text name="label.param1"/></th>
														<th><s:text name="label.param2"/></th>
														<th><s:text name="label.master.status"/></th>
														<th><s:text name="label.master.edit"/></th>
													</tr>
													</thead>
													<tbody class="rowevencolor">
														<s:iterator value="constantList" var="list" status="stat">
															<tr>
																<td><s:property value="#stat.count" /></td>
																<td><s:property value="#list.ITEM_TYPE" /></td>
																<td><s:property value="#list.ITEM_VALUE" /></td>
																<td><s:property value="#list.ITEM_DESC" /></td>
																<td><s:property value="#list.PARAM1" /></td>
																<td><s:property value="#list.PARAM2" /></td>
																<td><s:property value="#list.STATUS" /></td>
																<td style="text-align: center;">
																	<input type="button" class="btn btn-warning btn-sm" onclick="funSubmit('edit','<s:property value="#list.ITEM_ID"/>');" value="Modify" />
																</td>
															</tr>
														</s:iterator>
													</tbody>
												</table>
											</div>
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

$(document).ready(function() {
    $('#itemType').change(function() {
        if ( $("#itemType").val ()  ==  "***ADD NEW***") 
        {                              
            $('#item').show();
        }
        else
            $("#item").hide();
    }); 
});



function funSubmit(mode,val){
	var action="";
	if(mode=='add'){
		action='constantEditMotorAdminNew.action?mode='+mode;
	}else if(mode=='edit'){
		action='constantEditMotorAdminNew.action?mode='+mode+'&itemId='+val;
	}else  if(mode=='insert'){
		action='insertConstantMotorAdminNew.action?mode=<s:property value="mode"/>';
	}
	else{
		alert("Action is empty");
	}
	document.constant.action=action;
	document.constant.submit();
}
function funBack(){
	document.constant.action='constantMotorAdminNew.action';
	document.constant.submit();
}

</script>
</body>
</html>   

<!-- function ItemTypeChange(val){
 if(val=='***ADD NEW***') {
   document.getElementById('item').disabled=false;
 } else {  
   document.getElementById('item').disabled=true;
  }
} -->