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
			$('#effectiveDate').datepicker({
				todayHighlight: true,
				format: "dd/mm/yyyy"
			}).on('changeDate', function(e){
			    $(this).datepicker('hide');
			});
			$('#expiryDate').datepicker({
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
	<s:form id="makeConfig" name="makeConfig" action="" theme="simple">
		<div id="loading" style="width:100%">
		   <img id="loading-image" src="${pageContext.request.contextPath}/images/madisonSymbolLogo.png"/>
		</div>
		<div class="container">
			<div class="row">
				<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
					<div class="container vehDtl">
				  		<div class="Card_Parent ">
			            	<div class="card card-5">
								<div class="panel-heading">
									<h3><s:text name="label.master.make.master.configuration"/></h3>
									<hr>
								</div>
								<s:if test='mode == "addMake" || mode =="editMake"'>
									<div class="panel-body">
									<s:if test="hasActionErrors()">
										<font color="red"><s:actionerror cssStyle="list-style: none;"/></font>
									</s:if>
										<div class="row">
											<div class="col-xs-12 col-sm-6 col-md-6">
												<div class="form-group">
			    									<label><s:text name="label.master.make.name"/><font color="red">*</font></label>
			    									<s:textfield name="makeName" cssClass="form-control" maxlength="50"></s:textfield>
												</div>
											</div>
											<%--<div class="col-xs-12 col-sm-6 col-md-6">
												<div class="form-group">
			    									<label><s:text name="label.master.make.name.arabic"/></label>
			    									<s:textfield name="makeNameArabic" cssClass="form-control" maxlength="150"></s:textfield>
												</div>
											</div>--%>
											<div class="col-xs-12 col-sm-6 col-md-6">
												<div class="form-group">
			    									<label><s:text name="label.master.vehicle.country" /><font color="red">*</font></label>
			    									<s:select name="applicableCountry" id="applicableCountry" list="countryList" headerKey="" headerValue="---Select---"  listKey="CODE" listValue="CODEDESC" cssClass="form-control inputBoxS"/>
												</div>
											</div>
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
												<s:submit cssClass="btn btn-sm btn-success" value="Submit" onclick="funSubmit('insertMake','');"></s:submit> &nbsp;&nbsp;&nbsp;
												<s:submit cssClass="btn btn-sm btn-danger" value="Back" onclick="funBack();"></s:submit>
											</div>
										</div> <br/>
									</div>
									<s:hidden name="makeId"></s:hidden>
								</s:if>
								<s:else>
									<div class="panel-body">
											<div class="panel panel-primary">
												<div class="panel-heading">
													<div class="clearfix">
													<div class="panel-heading">
														<h5><s:text name="label.master.make.details"/></h5>
													</div>
														<%-- <div align="left"><s:text name="label.master.make.details"/></div> --%>
														<div align="right"><s:submit cssClass="btn btn-sm btn-info" value="Add New" onclick="funSubmit('addMake','');"></s:submit></div>
													</div>
												</div>
												<div class="panel-body">
													<font color="green"><s:actionmessage cssStyle="list-style:none;"/> </font>
													<div class="row">
														<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
															<table class="table table-bordered table-hover" cellspacing="0" width="100%" id="tadaTable">
																<thead class="bluecolortable" >
																	<tr>
																		<th class="no-sort"><s:text name="label.master.sno"/></th>
																		<th><s:text name="label.master.make.name"/></th>
																		<th><s:text name="label.master.status"/></th>
																		<th><s:text name="label.master.referral.status"/></th>
																		<th><s:text name="lable.master.effectiveDate"/></th>
																		<th><s:text name="label.master.expiryDate"/></th>
																		<th></th>
																		<th></th>
																	</tr>
																</thead>
																<tbody class="rowevencolor">
																	<s:iterator value="makeList" var="list" status="stat">
																		<tr>
																			<td><s:property value="#stat.count" /></td>
																			<td><s:property value="#list.MAKE_NAME" /></td>
																			<td><s:property value="#list.STATUS" /></td>
																			<td><s:property value="#list.REFERRAL_STATUS" /></td>
																			<td><s:property value="#list.EFFECTIVE_DATE" /></td>
																			<td><s:property value="#list.EXPIRY_DATE" /></td>
																			<td style="text-align: center;"><button type="button" class="btn btn-warning btn-sm" onclick="funSubmit('editMake','<s:property value="#list.MAKE_ID" />');">Modify</button></td>
																			<td style="text-align: center;"><button type="button" class="btn btn-primary btn-sm" onclick="funSubmit('modelList','<s:property value="#list.MAKE_ID"/>');" >Model</button></td>
																		</tr>
																	</s:iterator>
																</tbody>
															</table>
														</div>
													</div>
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
function funSubmit(mode,val){
	var action="";
	if(mode =='makeList'){
		action='makeMotorAdminNew.action?mode='+mode;
	}else if(mode=='addMake'){
		action='editMakeMotorAdminNew.action?mode='+mode;
	}else if(mode=='editMake'){
		action='editMakeMotorAdminNew.action?mode='+mode+'&makeId='+val;
	}else if(mode=='modelList'){
		action='modelMotorAdminNew.action?makeId='+val;
	}else if(mode=='insertMake'){
		action='insMakeMasterMotorAdminNew.action?mode=<s:property value="mode"/>';
	}
	else{
		alert("Action is empty");
	}
	document.makeConfig.action=action;
	document.makeConfig.submit();
}
function funBack(){
	document.makeConfig.action='makeMotorAdminNew.action';
	document.makeConfig.submit();
}
</script>
</body>
</html>   
