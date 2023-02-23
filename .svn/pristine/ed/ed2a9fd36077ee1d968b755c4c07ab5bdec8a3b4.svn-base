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
	<s:form id="deductible" name="deductible" action="" theme="simple">
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
									<h3><s:text name="label.deductibleMaster"/></h3><hr>
								</div>
								<font color="red"><s:actionerror cssStyle="list-style:none;"/> </font>
								<font color="green"><s:actionmessage cssStyle="list-style:none;"/> </font>
								<s:if test='mode == "Add" || mode =="Edit"'>
									<div class="panel-body">
										<div class="row">									
											<s:hidden name="deductibleId" id="deductibleId"></s:hidden>
											<div class="col-xs-12 col-sm-12 col-md-6 col-lg-4">
												<s:text name="label.master.vehicleType" /> <br/>
												<s:select name="vehicleType" id="vehicleType" list="vehicleTypeList" headerKey="" headerValue="---Select---"  listKey="CODE" listValue="CODEDESC" cssClass="form-control" onchange="ajax(this.value,'bodyType');hideSupPolicy();"/>
											</div>
											<div class="col-xs-12 col-sm-12 col-md-6 col-lg-4" id="bodyType">
												<s:text name="label.master.bodyType" /> <br/>
												<s:select list="#{}"  name="bodyType" cssClass="form-control" headerKey="" headerValue="---Select---" />
											</div>
											<div class="col-xs-12 col-sm-12 col-md-6 col-lg-4">
												<div class="form-group">
			    									<label><s:text name="label.seatingStart"/><font color="red">*</font></label>
			    									<s:textfield name="seatingStart" id="seatingStart" cssClass="form-control numericOnly" maxlength="10"></s:textfield>
												</div>
											</div>
											<div class="col-xs-12 col-sm-12 col-md-6 col-lg-4">
												<div class="form-group">
			    									<label><s:text name="label.seatingEnd"/><font color="red">*</font></label>
			    									<s:textfield name="seatingEnd" id="seatingEnd" cssClass="form-control numericOnly" maxlength="10"></s:textfield>
												</div>
											</div>
											<div class="col-xs-12 col-sm-12 col-md-6 col-lg-4">
												<div class="form-group">
			    									<label><s:text name="label.deductStart"/><font color="red">*</font></label>
			    									<s:textfield name="deductibleStart" id="deductibleStart" cssClass="form-control numericOnly" maxlength="10"></s:textfield>
												</div>
											</div>
											<div class="col-xs-12 col-sm-12 col-md-6 col-lg-4">
												<div class="form-group">
			    									<label><s:text name="label.deductEnd"/><font color="red">*</font></label>
			    									<s:textfield name="deductibleEnd" id="deductibleEnd" cssClass="form-control numericOnly" maxlength="10"></s:textfield>
												</div>
											</div>
											<div class="col-xs-12 col-sm-12 col-md-6 col-lg-4">
												<div class="form-group">
			    									<label><s:text name="label.deductRate"/><font color="red">*</font></label>
			    									<s:textfield name="deductibleRate" id="deductibleRate" cssClass="form-control decimalOnly" maxlength="15"></s:textfield>
												</div>
											</div>
											<div class="col-md-2">
						                    	<label><s:text name="Deductible Amount"/><font color="red">*</font></label>
						                    	<div class="input-group p-0">
						                            <div class="input-group-prepend">
						                                <span class="input-group-text border border-right-0">ZMW</span>
						                            </div>
						                            <s:textfield name="discountAmount" id="discountAmount" cssClass="form-control numericOnly" maxlength="10"></s:textfield>
						                        </div>
					                    	</div>
					                    	<div class="col-md-2">
					                    		<label></label>
						                    	<div class="input-group p-2">
						                            <div class="input-group-prepend">
						                                <span class="input-group-text border border-right-0">USD</span>
						                            </div>
						                            <s:textfield name="deductibleAmountUSD" id="deductibleAmountUSD" cssClass="form-control numericOnly" maxlength="10"></s:textfield>
						                        </div>
					                    	</div>
											<div class="col-md-4">
						                    	<label><s:text name="Theft Excess"/> %<font color="red">*</font></label>
						                    	<div class="input-group p-0">
						                            <%-- <div class="input-group-prepend">
						                                <span class="input-group-text border border-right-0">ZMW</span>
						                            </div> --%>
						                            <s:textfield name="theftExcessZMW" id="theftExcessZMW" cssClass="form-control decimalOnly" maxlength="10"></s:textfield>
						                        </div>
					                    	</div>
					                    	<%-- <div class="col-md-2">
					                    		<label></label>
						                    	<div class="input-group p-2">
						                            <div class="input-group-prepend">
						                                <span class="input-group-text border border-right-0">USD</span>
						                            </div>
						                            <s:textfield name="theftExcessUSD" id="theftExcessUSD" cssClass="form-control decimalOnly" maxlength="10"></s:textfield>
						                        </div>
					                    	</div> --%>
											<div class="col-md-4">
						                    	<label><s:text name="Driver Excess"/> %<font color="red">*</font></label>
						                    	<div class="input-group p-0">
						                            <%-- <div class="input-group-prepend">
						                                <span class="input-group-text border border-right-0">ZMW</span>
						                            </div> --%>
						                            <s:textfield name="driverExcessZMW" id="driverExcessZMW" cssClass="form-control decimalOnly" maxlength="10"></s:textfield>
						                        </div>
					                    	</div>
					                    	<%-- <div class="col-md-2">
					                    		<label></label>
						                    	<div class="input-group p-2">
						                            <div class="input-group-prepend">
						                                <span class="input-group-text border border-right-0">USD</span>
						                            </div>
						                            <s:textfield name="driverExcessUSD" id="driverExcessUSD" cssClass="form-control decimalOnly" maxlength="10"></s:textfield>
						                        </div>
					                    	</div> --%>
											<div class="col-md-4">
						                    	<label><s:text name="Claim Excess"/> %<font color="red">*</font></label>
						                    	<div class="input-group p-0">
						                            <%-- <div class="input-group-prepend">
						                                <span class="input-group-text border border-right-0">ZMW</span>
						                            </div> --%>
						                            <s:textfield name="claimExcessZMW" id="claimExcessZMW" cssClass="form-control decimalOnly" maxlength="10"></s:textfield>
						                        </div>
					                    	</div>
					                    	<%-- <div class="col-md-2">
					                    		<label></label>
						                    	<div class="input-group p-2">
						                            <div class="input-group-prepend">
						                                <span class="input-group-text border border-right-0">USD</span>
						                            </div>
						                            <s:textfield name="claimExcessUSD" id="claimExcessUSD" cssClass="form-control decimalOnly" maxlength="10"></s:textfield>
						                        </div>
					                    	</div> --%>
											<div class="col-xs-12 col-sm-12 col-md-6 col-lg-4">
												<div class="form-group">
			    									<label><s:text name="lable.master.effectiveDate" /><font color="red">*</font></label>
			    									<s:textfield name="effectiveDate" id="effectiveDate" readonly="true" cssClass="form-control"></s:textfield>
												</div>
											</div>
											<div class="col-xs-12 col-sm-12 col-md-6 col-lg-4">
												<div class="form-group">
			    									<label><s:text name="label.master.remarks"/></label>
			    									<s:textfield name="remarks" id="remarks" cssClass="form-control" maxlength="500"></s:textfield>
												</div>
											</div>
											<div class="col-xs-12 col-sm-12 col-md-6 col-lg-4">
												<div class="form-group">
			    									<label><s:text name="label.master.dataModified" /><font color="red">*</font></label><br/>
													<s:radio list="#{'Y':'Activate','N':'Deactivate'}" name="dataModified" id="dataModified" value='%{status==null?"N":status}' />
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
								</s:if>
								<s:else>
									<div class="panel-body">
									<div class="panel panel-primary" >
										<div class="panel-heading">
											<div class="clearfix">
												<div class="pull-right offset-11"><input type="button" class="btn btn-sm btn-info" value="Add New" onclick="funSubmit('Add','');"></div>
											</div>
										</div>
										<div class="panel-body">
										<div class="row">
										<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
											<table class="table table-bordered table-hover" cellspacing="0" width="100%" id="tadaTable">
												<thead class="bluecolortable" >
													<tr>
														<th class="no-sort"><s:text name="label.master.sno"/></th>
														<td><s:text name="label.deductStart"/></td>		
														<td><s:text name="label.deductEnd"/></td>
														<td><s:text name="label.deductRate"/></td>
														<td><s:text name="label.discountAmount"/></td>
														<td><s:text name="label.vehicleName"/></td>
														<td><s:text name="label.bodyName"/></td>
														<td><s:text name="label.seatingStart"/></td>
														<td><s:text name="label.seatingEnd"/></td>
														<td><s:text name="label.status"/></td>
														<td><s:text name="label.master.edit"/></td>
													</tr>
													</thead>
													<tbody class="rowevencolor">
														<s:iterator value="deductibleList" var="list" status="stat">
															<tr>
																<td><s:property value="#stat.count" /></td>
																<td><s:property value="#list.DEDUCT_START" /></td>
																<td><s:property value="#list.DEDUCT_END" /></td>
																<td><s:property value="#list.RATE" /></td>
																<td><s:property value="#list.DISC_AMT" /></td>
																<td><s:property value="#list.VEHICLE_NAME" /></td>
																<td><s:property value="#list.BODY_NAME" /></td>
																<td><s:property value="#list.SEATING_START" /></td>
																<td><s:property value="#list.SEATING_END" /></td>
																<td><s:property value="#list.STATUS" /></td>
																<td style="text-align: center;">
																	<input type="button" class="btn btn-warning btn-sm" onclick="funSubmit('Edit','<s:property value="#list.DEDUCT_ID" />');" value="Modify" />
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
	  if(mode == "Add"){
		document.deductible.action='getDeductibleEditMotorAdminNew.action?mode='+mode;
		}
	  else if(mode == "Edit"){
		 document.deductible.action='getDeductibleEditMotorAdminNew.action?mode='+mode+'&deductibleId='+val;
		 }
	  else if(mode == "Insert"){
	     document.deductible.action='getDeductibleInsertMotorAdminNew.action?mode=<s:property value="mode"/>';
	  }
	  else{
	    alert("No Action for Submit");
	    }
		document.deductible.submit();
	}
	function funBack(){
		document.deductible.action='deductibleMotorAdminNew.action';
		document.deductible.submit();
	}
	ajax('<s:property value="vehicleType"/>','bodyType');
function ajax(val,id){
	var action='';
	if(id=='bodyType'){
		action=	'?vehicleType='+val+'&bodyType=<s:property value="bodyType"/>';
	}
	postRequest('${pageContext.request.contextPath}/ajaxMotorAdminNew.action'+action+'&reqFrom='+id, id);
}
</script>
</body>
</html>   
