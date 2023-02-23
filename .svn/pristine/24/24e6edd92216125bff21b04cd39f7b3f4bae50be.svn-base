<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/struts-tags" prefix="s"%>
<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/dataTables/css/dataTables.responsive.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/dataTables/css/dataTables.jqueryui.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/dataTables/css/dataTables.tableTools.css">
	<script type="text/javascript" src="${pageContext.request.contextPath}/dataTables/js/dataTables.responsive.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/dataTables/js/dataTables.tableTools.js"></script>
	
	<script type="text/javascript">

	$(document).ready(function () {
		  $('#tadaTable').DataTable({
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
			$('#expiryDate').datepicker({
				todayHighlight: true,
				format: "dd/mm/yyyy"
			}).on('changeDate', function(e){
				$(this).datepicker('hide');
			});
			$('#effectiveDate').datepicker({
				todayHighlight: true,
				format: "dd/mm/yyyy"
			}).on('changeDate', function(e){
				$(this).datepicker('hide');
			});
		} catch(err) {console.error(err);}
	});
 	
 	</script>
 	
	<style type="text/css">
		#loading {
		  width: 100%;
		  height: 100%;
		  top: 0px;
		  left: 0px;
		  position: fixed;
		  display: block;
		  opacity: 0.7;
		  background-color: black;
		  z-index: 99;
		  opacity:0.5;
		  text-align: center;
		}
		
		#loading-image {
		  position: absolute;
		  top: 30%;
		  left: 45%;
		  z-index: 100;
		  width: 100px;
		  height: 100px;
		}
	</style>
</head>
	<body>
	<s:form id="modelConfig" name="modelConfig" action="" theme="simple">
		<div id="loading" style="width:100%">
		   <img id="loading-image" src="${pageContext.request.contextPath}/images/madisonSymbolLogo.png"/>
		</div>
<div class="container vehDtl">
	<div class="Card_Parent ">
		<div class="card card-5">
			<div class="p-3">
				<div class="col-xs-12">
					<s:if test='mode == "addModel" || mode =="editModel"'>
						<div class="panel panel-primary">
							<div class="panel-heading">
								<h3><s:text name="label.master.model.master.configuration"/></h3>
								<hr>
							</div>
							<div class="panel-body">
							<div class="panel panel-primary">
								<div class="panel-heading">
									<s:iterator value="modalHeaderList" var="list"  >
										<div class="row">
											<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
												<s:text name="label.master.make.name" /> &nbsp;:&nbsp; <b> <s:property value="%{MAKE_NAME}"/> </b>
											</div>
										</div>
									</s:iterator>									
								</div>
							</div>
							<br/>
								<s:if test="hasActionErrors()">
									<div class="row mt-3">
										<font color="red"><s:actionerror cssStyle="list-style: none;"/></font>
									</div>
								</s:if>
								<div class="row mt-2">
									<div class="col-xs-12 col-sm-6 col-md-6">
										<div class="form-group">
	    									<label><s:text name="label.master.model.name"/><font color="red">*</font></label>
	    									<s:textfield name="modelName" cssClass="form-control" maxlength="50"></s:textfield>
										</div>
									</div>
									<%--<div class="col-xs-12 col-sm-6 col-md-6">
										<div class="form-group">
	    									<label><s:text name="label.master.model.name.arabic"/></label>
	    									<s:textfield name="modelNameArabic" cssClass="form-control" maxlength="150"></s:textfield>
										</div>
									</div>
									--%>
									<%-- <div class="col-xs-12 col-sm-6 col-md-6">
										<div class="form-group">
											<label><s:text name="label.master.bodyType" /><font color="red">*</font></label>
											<s:select name="bodyType" id="bodyType" list="bodyTypeList" headerKey="" headerValue="---Select---"  listKey="TYPE_OF_BODY_ID" listValue="TYPE_OF_BODY_NAME" cssClass="form-control inputBoxS"/>
										</div>
									</div>--%>
									<div class="col-xs-12 col-sm-6 col-md-6">
										<div class="form-group">
	    									<label><s:text name="label.coreAppCode"/><font color="red">*</font></label>
	    									<s:textfield name="coreAppCode" cssClass="form-control" maxlength="10"></s:textfield>
										</div>
									</div>
									<div class="col-xs-12 col-sm-6 col-md-6">
										<div class="form-group">
	    									<label><s:text name="lable.master.effectiveDate" /><font color="red">*</font></label>
	    									<s:textfield name="effectiveDate" readonly="true" cssClass="form-control" id="effectiveDate"></s:textfield>
										</div>
									</div>
									<div class="col-xs-12 col-sm-6 col-md-6">
										<div class="form-group">
	    									<label><s:text name="label.master.expiryDate" /><font color="red">*</font></label>
	    									<s:textfield name="expiryDate" readonly="true" cssClass="form-control" id="expiryDate"></s:textfield>
										</div>
									</div>
									<div class="col-xs-12 col-sm-6 col-md-6">
										<div class="form-group">
	    									<label><s:text name="label.master.remarks"/></label>
	    									<s:textfield name="remarks" cssClass="form-control" maxlength="150"></s:textfield>
										</div>
									</div>
									<div class="col-xs-12 col-sm-6 col-md-6">
										<div class="form-group">
											<label><s:text name="label.master.referral.status" /><font color="red">*</font></label><br/>
											<s:radio list="#{'Y':'Yes','N':'No'}" name="referralStatus" id="referralStatus" value='%{referralStatus==null?"N":referralStatus}' />
										</div>
									</div>
									<div class="col-xs-12 col-sm-6 col-md-6">
										<div class="form-group">
											<label><s:text name="label.master.status" /><font color="red">*</font></label><br/>
											<s:radio list="#{'Y':'Activate','N':'Deactivate'}" name="status" id="status" value='%{status==null?"N":status}' />
										</div>
									</div>
									<div class="col-xs-12 col-sm-12 col-md-12" align="center">
										<s:submit cssClass="btn btn-sm btn-success" value="Submit" onclick="funSubmit('insModel','');"></s:submit> &nbsp;&nbsp;&nbsp;
										<s:submit cssClass="btn btn-sm btn-danger" value="Back" onclick="funBack('model');"></s:submit>
									</div>
								</div> <br/>
							</div>
						</div>
						<s:hidden name="modelId"></s:hidden>
					</s:if>
					<s:else>
						<div class="panel panel-primary">
							<div class="panel-heading">
								<h3><s:text name="label.model.master.details"/></h3>
								<hr>
								<div class="clearfix">
									<div align="right"><s:submit cssClass="btn btn-sm btn-info" value="Add New" onclick="funSubmit('addModel','');"></s:submit></div>
								</div>
							</div>
							<br class=""/>
							<div class="panel panel-primary">
								<div class="panel-heading">
									<s:iterator value="modalHeaderList" var="list"  >
										<div class="row">
											<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
												<s:text name="label.master.make.name" /> &nbsp;:&nbsp; <b> <s:property value="%{MAKE_NAME}"/> </b>
											</div>
										</div>
									</s:iterator>									
								</div>
							</div>
							<div class="panel-body">
								<font color="green"><s:actionmessage cssStyle="list-style:none;"/> </font>
<!-- 								<div class="row"> -->
									<table class="table table-bordered table-hover" cellspacing="0" width="100%" id="tadaTable">
										<thead class="bluecolortable" >
										<tr>
											<th class="no-sort"><s:text name="label.master.sno"/></th>
											<th><s:text name="label.master.model.name"/></th>
											<!--<td><s:text name="label.master.bodyType"/></td>-->
											<th><s:text name="label.master.status"/></th>
											<th><s:text name="label.master.referral.status"/></th>
											<th><s:text name="lable.master.effectiveDate"/></th>
											<th><s:text name="label.master.expiryDate"/></th>
											<th></th>
											<th></th>	
										</tr>
										</thead>
										<tbody class="rowevencolor">
											<s:iterator value="modelList" var="list" status="stat">
												<tr>
													<td><s:property value="#stat.count" /></td>
													<td><s:property value="#list.MODEL_NAME" /></td>
													<!--<td><s:property value="#list.BODY_NAME" /></td>-->
													<td><s:property value="#list.STATUS" /></td>
													<td><s:property value="#list.REFERRAL_STATUS" /></td>
													<td><s:property value="#list.EFFECTIVE_DATE" /></td>
													<td><s:property value="#list.EXPIRY_DATE" /></td>
													<td style="text-align: center;">
														<button type="button" class="btn btn-warning btn-sm" onclick="funSubmit('editModel','<s:property value="#list.MODEL_ID" />');">Modify</button>
													</td>
													<td style="text-align: center;">
														<button type="button" class="btn btn-primary btn-sm" onclick="funSubmit1('detailModel','<s:property value="#list.MAKE_ID"/>','<s:property value="#list.MODEL_NAME"/>','<s:property value="#list.MODEL_ID"/>');">Detail</button>
													</td>
												</tr>
											</s:iterator>
										</tbody>
									</table>
<!-- 								</div> -->
							</div>
						</div>
						<div style="text-align: center;">
							<button type="button" class="btn btn-danger btn-sm" onclick="funBack('make')">Back</button>
						</div>
					</s:else>
							
				</div>
			</div>
		</div>
		</div>
		</div>
		<s:hidden name="makeId"></s:hidden>
	</s:form>
<script type="text/javascript">
function funSubmit(mode,val){
	var action="";
	if(mode =='addModel'){
		action='editModelMotorAdminNew.action?mode='+mode;
	}else if(mode =='editModel'){
		action='editModelMotorAdminNew.action?mode='+mode+'&modelId='+val;
	}else if(mode =='insModel'){
		action='insModelMasterMotorAdminNew.action?mode=<s:property value="mode"/>';
	}
	else{
		alert("Action is empty");
	}
	document.modelConfig.action=action;
	document.modelConfig.submit();
}

function funSubmit1(mode,val,val1,val2){

	document.modelConfig.action='modelDetailMotorAdminNew.action?modelName='+val1+'&modelId='+val2;
	document.modelConfig.submit();
}
function funBack(type){
	var action="";
	if(type =="model"){
		action='modelMotorAdminNew.action';
	}else if(type =="make"){
		action='makeMotorAdminNew.action';
	}	
	document.modelConfig.action=action;
	document.modelConfig.submit();
}
</script>
</body>
</html>   
