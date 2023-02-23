<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ page import="java.util.List,java.util.ArrayList,java.util.Map,java.util.HashMap,java.util.Collections"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page import="nl.captcha.Captcha"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
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
	        "columnDefs": [
	          { "orderable": false, "targets": 6 },
	          { "responsivePriority": 1, targets: 0 },
	            { "responsivePriority": 10001, targets: 4 },
	            { "responsivePriority": 2, targets: -2 }
	        ],
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
	<s:form id="brokerBranch" name="brokerBranch" action=""  method="post" theme="simple">
		<div id="loading" style="width:100%">
		   <img id="loading-image" src="${pageContext.request.contextPath}/images/madisonSymbolLogo.png"/>
		</div>
		<div class="container">
			<div class="row">
				<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
					<div class="container vehDtl">
				  		<div class="Card_Parent ">
			            	<div class="card card-5">
								<div class="panel panel-heading">
									<h3><s:text name="Broker Branch Master"/></h3><hr>
								</div>
								<font color="red"><s:actionerror cssStyle="list-style:none;"/> </font>
								<font color="green"><s:actionmessage cssStyle="list-style:none;"/> </font>
								<s:if test='"list".equalsIgnoreCase(mode)' >
									<div class="panel-body">
										
										<div class="panel panel-primary" >
											<div class="panel-heading">
												<div class="clearfix">
													<%-- <div class="pull-left"><h3><s:text name="Broker Branch List"/></h3></div><hr> --%>
													<div class="pull-right offset-11"><s:submit cssClass="btn btn-sm btn-info" value="Add New" onclick="funEdit('add','');"></s:submit></div>
												</div>
											</div>
											<div class="panel-body">
												<div class="row">
												<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
													<table class="table table-bordered table-hover" cellspacing="0" width="100%" id="tadaTable">
														<thead class="bluecolortable" >
															<tr>
																<th class="no-sort"><s:text name="label.master.sno"/></th>
																<th><s:text name="Broker Branch Code"/></th>
																<th><s:text name="Broker Branch Name"/></th>		
																<th><s:text name="MGEN Branch name"/></th>
																<th><s:text name="Broker Name"/></th>
																<th><s:text name="Login ID"/></th>
																<th><s:text name="Status."/></th>
																<th><s:text name="Edit"/></th>
																<th><s:text name="Remarks."/></th>
															</tr>
														</thead>
														<tbody class="rowevencolor">
															<s:iterator value="brokerBranchList" var="list" status="stat">
																<tr>
																	<td><s:property value="#stat.count" /></td>
																	<td><s:property value="#list.BRANCH_CODE" /></td>
																	<td><s:property value="#list.BROKER_BRANCH_NAME" /></td>
																	<td><s:property value="#list.MGEN_BRANCH_NAME" /></td>
																	<td><s:property value="#list.BROKER_NAME" /></td>
																	<td><s:property value="#list.LOGIN_ID" /></td>
																	<td><s:property value="#list.STATUS" /></td>
																	<td style="text-align: center;"><button type="button" class="btn btn-sm btn-warning" onclick="funEdit('edit','<s:property value="#list.BRANCH_ID"/>');" >Edit</button></td>
																	<td><s:property value="#list.REMARKS" /></td>
																</tr>
															</s:iterator>
														</tbody>
													</table>
													</div>
												</div>
											</div>
										</div>		
									</div>
									
								</s:if>
								<s:elseif test='"add".equalsIgnoreCase(mode) || "edit".equalsIgnoreCase(mode)'>
							  	 <div class="panel panel-primary" >
									<%-- <div class="panel-heading">
										<div class="clearfix">
											<div class="pull-left"><h3><s:text name="Add Broker Branch"/></h3></div><hr>
										</div>
									</div> --%>
									<div class="panel-body">
										<div class="row">
											<div class="col-xs-12 col-sm-6 col-md-4">
												<b><s:text name="Broker Selection" /></b><font color="red">*</font> <br/>
												<s:select name="brokerId" id="brokerId" list="userSelection" headerKey="" headerValue="---Select---"  listKey="AGENCY_CODE" listValue="USERNAME" cssClass="form-control" disabled='%{mode=="edit"?"true":false}'/>
											</div>
											<div class="col-xs-12 col-sm-6 col-md-4">
												<b><s:text name="MGEN Branch" /></b><font color="red">*</font> <br/>
												<s:select name="branchCode" id="branchCode" list="branchList" headerKey="" headerValue="---Select---"  listKey="BRANCH_CODE" listValue="BRANCH_NAME" cssClass="form-control" />
											</div>
											<div class="col-xs-12 col-sm-6 col-md-4">
												<b><s:text name="Broker Branch Code" /></b><font color="red">*</font> <br/>
												<s:textfield name="brokerBranchCode"  cssClass="form-control textfiledNew" id="brokerBranchCode" maxlength="5"></s:textfield>
											</div>
										</div>
										<div class="row mt-3">
											
											<div class="col-xs-12 col-sm-6 col-md-4">
												<b><s:text name="Broker Branch Name" /></b><font color="red">*</font> <br/>
												<s:textfield name="brokerBranchName"  cssClass="form-control textfiledNew" id="brokerBranchName" ></s:textfield>
											</div>
											<div class="col-xs-12 col-sm-6 col-md-4">
												<b><s:text name="Branch Status" /></b><font color="red">*</font> <br/>
												<s:radio list="#{'Y':'Activate','N':'Deactivate'}" name="status" id="status" value='%{status==null?"Y":status}' />
											</div>
											<div class="col-xs-12 col-sm-6 col-md-4">
												<b><s:text name="Branch Remarks" /></b> <br/>
												<s:textarea name="remarks"  cssClass="form-control textfiledNew" id="remarks" ></s:textarea>
											</div>
										</div>
									</div>
								<br/>
								<div class="row">
								  	 <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" align="center">
								  	 	<button type="button" class="btn btn-sm btn-danger" value="Back" onclick="funSubmit('list','');">Back</button>
								  	 	<button type="button" class="btn btn-sm btn-success" value="Back" onclick="funSubmit('<s:property value="mode"/>','');">Submit</button>
								  	 </div>
							  	 </div>
							  	 </div>
							  	
								</s:elseif>
							</div>
						</div>
					</div>
				</div>
			</div>		
		</div>
		<s:hidden name="mode" id="mode"/>
		<s:hidden name="brokerBranchId" id="brokerBranchId"/>
	</s:form>
<script type="text/javascript">

function funSubmit(mode,val){
	var action="";
	if(mode =='list'){
		action='branchListMotorAdminNew.action';
	}
	else if(mode=='add' || mode=='edit'){
		document.getElementById('brokerId').disabled=false;
		action='addBrokerBranchMotorAdminNew.action';
	}
	document.brokerBranch.mode.value=mode;
	document.brokerBranch.action=action;
	document.brokerBranch.submit();
}
function funEdit(mode,val){
	var action="";
	if(mode=='add'){
		action='editBrokerBranchMotorAdminNew.action';
	}else if(mode=='edit'){
		document.brokerBranch.brokerBranchId.value=val;
		action='editBrokerBranchMotorAdminNew.action';
	}
	document.brokerBranch.mode.value=mode;
	document.brokerBranch.action=action;
	document.brokerBranch.submit();
}


</script>
</body>
</html>   
