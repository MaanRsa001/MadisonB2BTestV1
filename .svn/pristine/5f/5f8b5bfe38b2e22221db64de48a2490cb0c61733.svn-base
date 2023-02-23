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
		.tablecol{
			font-weight: bold;
		    color: #4c4b4b;
		}
		.tablecol1{
			font-weight: bold;
		    color: #f9f2f2;
		}
		.tableval{
			font-weight: lighter;
		    color: #4c4b4b;
		}
	</style>
</head>
	<body>
	<s:form id="rateConfig" name="rateConfig" action="" enctype="multipart/form-data" method="post" theme="simple">
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
									<h3><s:text name="Motor Rate Configuration"/></h3><hr>
								</div>
								<font color="red"><s:actionerror cssStyle="list-style:none;"/> </font>
								<s:if test='"factList".equalsIgnoreCase(mode)' >
									<div class="panel-body">
										<div class="row">
											<div class="col-xs-12 col-sm-6 col-md-3">
												<b><s:text name="Broker Selection" /></b> <br/>
												<s:select name="brokerId" id="brokerId" list="userSelection" headerKey="ALL" headerValue="ALL"  listKey="AGENCY_CODE" listValue="USERNAME" cssClass="form-control" onchange="hideSupPolicy();"/>
											</div>
											<div class="col-xs-12 col-sm-6 col-md-3">
												<b><s:text name="Branch Name" /></b> <br/>
												<s:select name="branchCode" id="branchCode" list="branchList" headerKey="ALL" headerValue="ALL"  listKey="BRANCH_CODE" listValue="BRANCH_NAME" cssClass="form-control" onchange="hideSupPolicy();"/>
											</div>
											<div class="col-xs-12 col-sm-6 col-md-3">
												<b><s:text name="label.master.policyType" /></b> <br/>
												<s:select name="policyType" id="policyType" list="policyTypeList" headerKey="" headerValue="---Select---"  listKey="POLICY_SUBTYPE_ID" listValue="POLICYTYPE_DESC_ENGLISH" cssClass="form-control" onchange="hideSupPolicy();"/>
											</div>
											<div class="col-xs-12 col-sm-6 col-md-3">
												<b><s:text name="label.master.vehicleType" /></b> <br/>
												<s:select name="vehicleType" id="vehicleType" list="vehicleTypeList" headerKey="ALL" headerValue="ALL"  listKey="CODE" listValue="CODEDESC" cssClass="form-control" onchange="hideSupPolicy();"/>
											</div>
											<%-- <div class="col-xs-12 col-sm-6 col-md-3">
												<s:text name="label.master.vehicleType" /> <br/>
												<s:select name="vehicleType" id="vehicleType" list="vehicleTypeList" headerKey="" headerValue="---Select---"  listKey="CODE" listValue="CODEDESC" cssClass="form-control" onchange="ajax(this.value,'bodyType');hideSupPolicy();"/>
											</div>
											<div class="col-xs-12 col-sm-6 col-md-3" id="bodyType">
												<s:text name="label.master.bodyType" /> <br/>
												<s:select list="#{}"  name="bodyType" cssClass="form-control" headerKey="" headerValue="---Select---" />
											</div> --%>
											
										</div> <br/>
										<div class="row">
											<div class="col-xs-12 col-sm-12 col-md-12" align="center">
												<s:submit cssClass="btn btn-sm btn-success" value="Submit" onclick="funSubmit('factList','');"></s:submit>
											</div>
										</div><br/>
										<s:if test='motorFactorList!=null'>
											<div class="panel panel-primary"  id="rateListGrid">
												<div class="panel-heading">
													<div class="clearfix">
														<div class="pull-left"><h3><s:text name="Factor Name List"/></h3></div><hr>
														<%-- <div class="pull-right"><s:submit cssClass="btn btn-sm btn-info" value="Add New" onclick="funSubmit('addRate','');"></s:submit></div> --%>
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
																	<th><s:text name="Factor Name"/></th>		
																	<th><s:text name="Rate Config"/></th>
																	<th><s:text name="Effective Date"/></th>
																	<th><s:text name="Status."/></th>
																	<th><s:text name="Report"/></th>
																	<th><s:text name="Template"/></th>
																	<th><s:text name="Info"/></th>
																</tr>
															</thead>
															<tbody class="rowevencolor">
																<s:iterator value="motorFactorList" var="list" status="stat">
																	<tr>
																		<td><s:property value="#stat.count" /></td>
																		<td><s:property value="#list.REMARKS" /></td>
																		<td style="text-align: center;"><button type="button" class="btn btn-primary btn-sm" onclick="funSubmitRate('<s:property value="#list.POLICYTYPE_ID"/>','<s:property value="#list.FACTOR_ID"/>','<s:property value="#list.EFFECTIVE_DATE"/>','<s:property value="#list.REMARKS"/>');">Rate</button></td>
																		<td><s:property value="#list.EFFECTIVE_DATE" /></td>
																		<td><s:property value="#list.STATUS" /></td>
																		<td style="text-align: center;"><button type="button" class="btn btn-sm btn-success" onclick="funcDown('<s:property value="#list.FACTOR_ID"/>','<s:property value="#list.EFFECTIVE_DATE" />','report');" ><i class="fa fa-download  fa-1x"></i></button></td>
																		<td style="text-align: center;"><button type="button" class="btn btn-sm btn-warning" onclick="funcDown('<s:property value="#list.FACTOR_ID"/>','<s:property value="#list.EFFECTIVE_DATE" />','template');" ><i class="fa fa-download  fa-1x"></i></button></td>
																		<td style="text-align: center;"><button type="button" class="btn btn-info btn-sm" data-toggle="modal" data-refresh="true" data-target="#factorInfoModal" data-backdrop="static" data-keyboard="false" onclick="openPopup('<s:property value="#list.FACTOR_ID"/>');"><i class="fa fa-info-circle fa-1x"></i></button></td>
																	</tr>
																</s:iterator>
															</tbody>
														</table>
														</div>
													</div>
												</div>
											</div>		
										</s:if>
									</div>
									<s:hidden name="policyTypeDescEng" id="policyTypeDescEng"/>
									<s:hidden name="brokerNameDesc" id="brokerNameDesc"/>
									<s:hidden name="vehUsageDesc" id="vehUsageDesc"/>
								</s:if>
								<s:elseif test='"rateList".equalsIgnoreCase(mode)'>
								<div class="card">
									<div class="panel-body">
										<div class="row">
											<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
												<br/>
												
												<b>For New Rating Upload Click here..</b>&nbsp;&nbsp;&nbsp;&nbsp;<button type="button" class="btn btn-sm btn-warning" onclick="funFactorUpload();" ><i class="fa fa-upload"></i>Upload</button>&nbsp;
											</div>
									  	 </div>
									</div>
								  	<br class="clear" />
							  	 </div>
							  	 <div class="panel panel-primary"  id="rateListGrid">
									<div class="panel-heading">
										<div class="clearfix">
											<div class="pull-left"><h3><s:text name="Factor Rate List"/></h3></div><hr>
										</div>
									</div>
									<div class="panel-body">
										<div class="row">
											<div class="col-md-3" align="center">
												<b><s:text name="Vehicle Usage" /></b>: <div class="label" ><s:property value="vehUsageDesc" /></div>
											</div>
											<div class="col-md-3" align="center">
												<b><s:text name="label.master.policyType" /></b>: <div class="label" ><s:property value="policyTypeDescEng" /></div>
											</div>
											<div class="col-md-3" align="center">
												<b><s:text name="Factor Name" /></b>: <div class="label" ><s:property value="factorName" /></div>
											</div>
											<div class="col-md-3" align="center">
												<b><s:text name="Broker Name" /></b>: <div class="label" ><s:property value="brokerNameDesc" /></div>
											</div>
										</div>
										<div class="row">
											<div class="col-md-3" align="center">
												<b><s:text name="Branch Name" /></b>: <div class="label" ><s:property value="branchName" /></div>
											</div>
										</div>
										<br>
										<div class="row">
										<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
				   							<table class="table table-bordered table-hover" cellspacing="0" width="100%" id="tadaTable">
				   								<s:if test='motorRateList.size()>0'>
												<thead class="bluecolortable" >
													<tr>
														<th class="no-sort"><s:text name="label.master.sno"/></th>
														<%-- <td ><s:property value="factorName"/>&nbsp;<s:text name="label.primaryStart"/></td>		
														<td ><s:property value="factorName"/>&nbsp;<s:text name="label.primaryEnd"/></td>
														<td ><s:property value="factorName"/>&nbsp;<s:text name="label.constant1"/></td>
														<td ><s:property value="factorName"/>&nbsp;<s:text name="label.secomdaryStart"/></td>		
														<td ><s:property value="factorName"/>&nbsp;<s:text name="label.secondaryEnd"/></td>
														<td ><s:property value="factorName"/>&nbsp;<s:text name="label.constant2"/></td>
														<!-- <td><s:property value="factorName"/>&nbsp;<s:text name="label.constant3"/></td> -->
														<td ><s:text name="label.tertiary"/></td>
														<td ><s:text name="label.quater"/></td> --%>
														<th><s:text name="Customer Type"/></th>
														<th><s:text name="Rate."/></th>
														<th ></th>
														<%-- <th ><s:text name="VehUsage ID"/></th> --%>
														<th ><s:text name="Rating type"/></th>
														<th ><s:text name="Status."/></th>
														<th ><s:text name="Body ID"/></th>
														<th ><s:text name="Body Desc"/></th>
														<s:if test='!"PARAM1".equalsIgnoreCase(paramHead1)'>
															<th ><s:property value="paramHead1"/></th>	
														</s:if>
														<s:if test='!"PARAM1".equalsIgnoreCase(paramHead1)'>											
															<th ><s:property value="paramHead2"/></th>
														</s:if>
														<s:if test='!"PARAM3".equalsIgnoreCase(paramHead3)'>
															<th ><s:property value="paramHead3"/></th>
														</s:if>
														<s:if test='!"PARAM4".equalsIgnoreCase(paramHead4)'>
															<th ><s:property value="paramHead4"/></th>
														</s:if>
														<s:if test='!"PARAM5".equalsIgnoreCase(paramHead5)'>
															<th ><s:property value="paramHead5"/></th>
														</s:if>
														<s:if test='!"PARAM6".equalsIgnoreCase(paramHead6)'>		
															<th ><s:property value="paramHead6"/></th>
														</s:if>
														<s:if test='!"PARAM7".equalsIgnoreCase(paramHead7)'>
															<th ><s:property value="paramHead7"/></th>
														</s:if>
														<s:if test='!"PARAM8".equalsIgnoreCase(paramHead8)'>
															<th ><s:property value="paramHead8"/></th>
														</s:if>
														<s:if test='!"PARAM9".equalsIgnoreCase(paramHead9)'>
															<th ><s:property value="paramHead9"/></th>
														</s:if>
														<s:if test='!"PARAM10".equalsIgnoreCase(paramHead10)'>
															<th ><s:property value="paramHead10"/></th>
														</s:if>
														<%--<s:if test='!"PARAM11".equalsIgnoreCase(paramHead11)'>
															<th ><s:property value="paramHead11"/></th>
														</s:if>
														<s:if test='!"PARAM12".equalsIgnoreCase(paramHead12)'>
															<th ><s:property value="paramHead12"/></th>
														</s:if> --%>
														<th ><s:text name="Branch Code"/></th>
														<th ><s:text name="Broker Name"/></th>
														<th ><s:text name="Effective Date"/></th>
														<th ><s:text name="End Date"/></th>
														<th ><s:text name="Remarks"/></th>
													</tr>
												</thead>
												<tbody class="rowevencolor">
													<s:if test='motorRateList.size()>0'>
														<s:iterator value="motorRateList" var="list" status="stat">
															<tr>
																<td class="tableval"><s:property value="#stat.count" /></td>
																<td class="tableval">
																	<s:property value="#list.CUSTOMER_TYPE" />
																</td>
																<td class="tableval">
																	<s:property value="#list.RATE" />
																</td>
																<td class="tableval">
																	<button type="button" class="btn btn-sm btn-warning" data-refresh="true" data-backdrop="static" data-keyboard="false" data-toggle="modal" data-target="#myModal" onclick="funcEdit('<s:property value="#list.FACTOR_RATE_ID"/>','editRate');"><i class="fa fa-edit"></i></button>
																</td>
																<%-- <td class="tableval"><s:property value="#list.VEHUSAGEID" /></td> --%>
																<td class="tableval"><s:property value="#list.RATING_TYPE" /></td>
																<td class="tableval"><s:property value="#list.STATUS" /></td>
																<td class="tableval"><s:property value="#list.BODYID" /></td>
																<td class="tableval"><s:property value="#list.BODYTYPE_DESC" /></td>
																<s:if test='!"PARAM1".equalsIgnoreCase(paramHead1)'>
																	<td class="tableval"><s:property value="#list.PARAM1" /></td>
																</s:if>
																<s:if test='!"PARAM2".equalsIgnoreCase(paramHead2)'>
																	<td class="tableval"><s:property value="#list.PARAM2" /></td>
																</s:if>
																<s:if test='!"PARAM3".equalsIgnoreCase(paramHead3)'>
																	<td class="tableval"><s:property value="#list.PARAM3" /></td>
																</s:if>
																<s:if test='!"PARAM4".equalsIgnoreCase(paramHead4)'>
																	<td class="tableval"><s:property value="#list.PARAM4" /></td>
																</s:if>
																<s:if test='!"PARAM5".equalsIgnoreCase(paramHead5)'>
																	<td class="tableval"><s:property value="#list.PARAM5" /></td>
																</s:if>
																<s:if test='!"PARAM6".equalsIgnoreCase(paramHead6)'>
																	<td class="tableval"><s:property value="#list.PARAM6" /></td>
																</s:if>
																<s:if test='!"PARAM7".equalsIgnoreCase(paramHead7)'>
																	<td class="tableval"><s:property value="#list.PARAM7" /></td>
																</s:if>
																<s:if test='!"PARAM8".equalsIgnoreCase(paramHead8)'>
																	<td class="tableval"><s:property value="#list.PARAM8" /></td>
																</s:if>
																<s:if test='!"PARAM9".equalsIgnoreCase(paramHead9)'>
																	<td class="tableval"><s:property value="#list.PARAM9" /></td>
																</s:if>
																<s:if test='!"PARAM10".equalsIgnoreCase(paramHead10)'>
																	<td class="tableval"><s:property value="#list.PARAM10" /></td>
																</s:if>
																<%--<s:if test='!"PARAM11".equalsIgnoreCase(paramHead11)'>
																	<td class="tableval"><s:property value="#list.PARAM11" /></td>
																</s:if>
																<s:if test='!"PARAM12".equalsIgnoreCase(paramHead12)'>
																	<td class="tableval"><s:property value="#list.PARAM12" /></td>
																</s:if> --%>
																<td class="tableval"><s:property value="#list.DIVISIONCODE" /></td>
																<td class="tableval"><s:property value="#list.BROKERNAME" /></td> 
																<td class="tableval"><s:property value="#list.EFFECTIVE_DATE" /></td>
																<td class="tableval"><s:property value="#list.end_date" /></td>
																<td class="tableval"><s:property value="#list.REMARKS" /></td>
															</tr>
														</s:iterator>
													</s:if>
													<s:elseif test= "motorRateList.size()==0 " >
															<tr><td colspan="16">
															<div align="center">No Record Available</div></td></tr>
													</s:elseif>
												</tbody>
												</s:if>
												<s:elseif test= "motorRateList.size()==0 " >
														<tr><td colspan="5">
														<div align="center">No Record Available</div></td></tr>
												</s:elseif>
											</table>
											</div>
										</div>
								</div>
								<br/>
								<div class="row">
								  	 <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" align="center">
								  	 	<button type="button" class="btn btn-sm btn-danger" value="Back" onclick="funSubmit('factList','');">Back</button>
								  	 </div>
							  	 </div>
							  	 
							  	 <div class="container modal_VehicleDetails">
								    <div class="modal fade" id="myModal">
								      <div class="modal-dialog  modal-lg">
								        <div class="modal-content">
								          	<div id="editRate"></div>
								        </div>
								      </div>
								    </div>
								</div>
							  	 
							  	 </div>
							  	 <s:hidden name="policyType" id="policyType"/>
							  	 <s:hidden name="branchCode" id="branchCode"/>
							  	 <s:hidden name="brokerId" id="brokerId"/>
							  	 <s:hidden name="vehicleType" id="vehicleType"/>
							  	 <s:hidden name="policyTypeDescEng" id="policyTypeDescEng"/>
								 <s:hidden name="brokerNameDesc" id="brokerNameDesc"/>
								 <s:hidden name="vehUsageDesc" id="vehUsageDesc"/>
								</s:elseif>
								<s:elseif test='"rateUpload".equalsIgnoreCase(mode)'>
								  <div class="card">
								  	 <div class="row">
									  	 <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
									  	 <table width="80%" border="0" cellspacing="2" cellpadding="2" align="center" >
									    		<tr align="center">
													<td height="50" colspan="4" >
														<div id="loaderImage" style="display:none" class="buttonload">
															<i class="fa fa-spinner fa-spin" style="font-size:48px"></i>
															<br>
															<br>
														</div>
													</td>
												</tr>
											</table>
											<div class="contact_form">
												<div class="panel-body table-body">
											    	<table width="55%" border="0" cellspacing="2" cellpadding="2" align="center" style="line-height: 3;" class="data-table-border" >
											    		<tr class="runtext" >
											    			<td width="30%"><b><s:label key="File to Upload"/><font color="red">*</font></b></td>
											    			<td align="left" width="70%">
											    				<s:file name="upload" cssClass="form-control" cssStyle="width:250px"/>
											    			</td>
											    		</tr>
											    		<tr class="runtext">
											    			<td width="30%"><b><s:label key="Effective Date"/><font color="red">*</font></b></td>
											    			<td align="left" width="70%">
											    				<div class=""><s:textfield name="effectiveDate"  cssClass="form-control textfiledNew datePicker" id="effectiveDate" readonly="true" style="width:63%"></s:textfield></div>
											    			</td>
											    		</tr>
											    		<tr>
											   				<td  align="right">
											   					<button type="button" class="btn btn-sm btn-danger" value="Back" onclick="funupload();" >Back</button>
											   				</td>	
											   				<td  align="center">
											   					<button type="button" class="btn btn-warning btn-sm" onclick="funRatingupload();" ><i class="fa fa-cloud"></i> Upload</button>
											   					
											   				</td>
											    		</tr>
											    	</table>
										    	</div>
									    	</div>
									    	<s:hidden name="policyTypeDescEng" id="policyTypeDescEng"/>
			   								<s:hidden name="policyType" id="policyType" />
			   								<s:hidden name="productId" id="productId" />
			   								<s:hidden name="brokerNameDesc" id="brokerNameDesc"/>
					    					<s:hidden name="vehUsageDesc" id="vehUsageDesc"/>
					    					<s:hidden name="branchCode" id="branchCode"/>
		    								<s:hidden name="vehicleType" id="vehicleType"/>
		    								<s:hidden name="brokerId" id="brokerId"/>
									  	 </div>
								  	 </div>
							  	 </div>
							 </s:elseif>
							 <s:elseif test='"tran".equalsIgnoreCase(mode)'>
								<div class="panel-body">
							    	<table width="55%" border="0" align="center" class="data-table-border" >
							    		<tr class="runtext">
							    			<td class="tableval" width="30%"><b><s:label key="Transaction ID"/></b></td>
							    			<td class="tableval" align="left" width="70%">
							    				<s:property value="tranID"/>
							    			</td>
							    		</tr>
							    		<s:if test='"P".equalsIgnoreCase(progressStatus) || "E".equalsIgnoreCase(progressStatus)'>
								    		<tr class="runtext">
								    			<td class="tableval" width="30%"><b><s:label key="Status"/></b></td>
								    			<td class="tableval" align="left" width="70%">
								    				<s:if test='"P".equalsIgnoreCase(progressStatus)'>Processing...</s:if><s:else>Error in Upload</s:else>
								    			</td>
								    		</tr>
								    		<tr class="runtext">
								    			<td class="tableval" width="30%"><b><s:label key="Description"/></b></td>
								    			<td class="tableval" align="left" width="70%">
								    				<s:property value = "progressMessage"/>
								    			</td>
								    		</tr>
										</s:if>
										<s:else>
								    		<tr class="runtext">
								    			<td width="30%"><b><s:label key="No.of Records"/></b></td>
								    			<td align="left" width="70%">
								    				<s:property value="noofRecords"/>
								    			</td>
								    		</tr>
								    		<tr class="runtext">
								    			<td width="30%"><b><s:label key="Valid Records"/></b></td>
								    			<td align="left" width="70%">
								    				<s:property value="validRecords"/>
								    			</td>
								    		</tr>
								    		<tr class="runtext">
								    			<td width="30%"><b><s:label key="Error Records"/></b></td>
								    			<td align="left" width="70%">
								    				<s:property value="errorRecords"/>
								    			</td>
								    		</tr>
								    		<tr>
								   				<td  align="left">
								   					<input type="button" class="btn btn-sm btn-danger" value="Back" onclick="funupload();" />
								   				</td>	
								   				<td  align="right">
								   					<input type="button" class="btn btn-sm btn-primary" value="View" onclick="funView('view','');" />
								   				</td>
								   			</tr>
							   			</s:else>
							    	</table>
					    		   </div>
						    	<s:hidden name="policyTypeDescEng" id="policyTypeDescEng"/>
								<s:hidden name="policyType" id="policyType" />
								<s:hidden name="productId" id="productId" />
								<s:hidden name="brokerNameDesc" id="brokerNameDesc"/>
		    					<s:hidden name="vehUsageDesc" id="vehUsageDesc"/>
		    					<s:hidden name="branchCode" id="branchCode"/>
		    					<s:hidden name="vehicleType" id="vehicleType"/>
		    					<s:hidden name="brokerId" id="brokerId"/>
							</s:elseif>
							<s:elseif test='"view".equalsIgnoreCase(mode)'>
								<div class="panel-body">
									<div style="padding-top:30px;" >
										<div class="section-title title-head" >
											<h3 class="info-details"><s:text name="Factor Rate Transaction Detail"/></h3>
										</div>
									</div>
									<div class="row" >
									<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
										<table class="table table-bordered table-hover" cellspacing="0" width="100%" id="tadaTable">
											<thead class="bluecolortable" >
											<tr>
												<th class="no-sort"><s:text name="label.master.sno"/></th>
												<th><s:text name="Tran ID"/></th>
												<th><s:text name="Factor Name"/></th>
												<th><s:text name="Transaction Date" /></th>
												<th><s:text name="Total Records"/></th>
												<th><s:text name="Valid Records"/></th>
												<th><s:text name="Error Records"/></th>
												
											</tr>
											</thead>
											<tbody class="rowevencolor">
												<s:if test='factorRateList.size()>0'>
													<s:iterator value="factorRateList" var="list" status="stat">
														<tr>
															<td><s:property value="#stat.count" /></td>
															<td><s:property value="#list.TRAN_ID" /></td>
															<td><s:property value="#list.FACTOR_ID" /></td>
															<td><s:property value="#list.TRANSACTION_DATE" /></td>
															<td><s:property value="#list.NO_OF_RECORDS" /></td>
															<td><s:property value="#list.VALID_RECORDS" /></td>
															<td><s:property value="#list.ERROR_RECORDS" /></td>
														</tr>
													</s:iterator>
												</s:if>
												<s:elseif test= "factorRateList.size()==0 " >
														<tr><td colspan="7">
														<div align="center">No Record Available</div></td></tr>
												</s:elseif>
											</tbody>
										</table>
										</div>								
										<br/>
										<br/>
									</div>
									<br class="clear" />
									<div class="row">
										<div class="col-md-12" align="center">
											<input type="button" class="btn btn-sm btn-danger" value="Back" onclick="funupload();" />
										</div>
									</div>
								</div>
								<s:hidden name="policyTypeDescEng" id="policyTypeDescEng"/>
								<s:hidden name="policyType" id="policyType" />
								<s:hidden name="productId" id="productId" />
								<s:hidden name="brokerNameDesc" id="brokerNameDesc"/>
		    					<s:hidden name="vehUsageDesc" id="vehUsageDesc"/>
		    					<s:hidden name="branchCode" id="branchCode"/>
		    					<s:hidden name="vehicleType" id="vehicleType"/>
		    					<s:hidden name="brokerId" id="brokerId"/>
							</s:elseif>
							<div id="factorInfoModal" class="modal fade" role="dialog">
								<div class="modal-dialog" align="center">
							        <div class="modal-content modal-lg" style="width: 500px;">
							            <div class="modal-header">
									      	<h5 class="modal-title">MOTOR FACTOR DETAILS</h5>
									      	<i class="far fa-times-circle mt-2" data-dismiss="modal"></i>
									    </div>
							            <!-- <div class="modal-body"></div> -->
							            <div class="panel-body" >
								            <div id="factorInfoAjax"></div>
							            </div>
							            <div class="modal-footer">
								            <div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
								            </div>
								            <br class="clearFix"/>
								            <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
								            	<div class="boxcontent" align="center">
								            	<s:submit type="button" cssClass="btn btn-sm btn-danger" value="Close" id="CloseButton" data-dismiss="modal" onclick="return false;"/>
													<%--<button type="button" class="btn btn-sm btn-danger" onclick="closePopup();">Close</button> --%>
										 		</div>
								            </div>
								       </div>
							         </div>
							    </div>
							</div>
			                <br>
							</div>
						</div>
						
							
					</div>
				</div>
			</div>		
		</div>
		<s:hidden name="mode" id="mode"/>
		<s:hidden name="factorRateId" id="factorRateId"/>
		<%-- <s:hidden name="policyType" id="policyType"/> --%>
		<s:hidden name="factorId" id="factorId"/>
		<s:hidden name="rateEndDate" id="rateEndDate"/>
		<s:hidden name="factorName" id="factorName"/>
		<s:hidden name="downloadType" id="downloadType" />
	</s:form>
<script type="text/javascript">
$(function() {
	try {
		var dt = new Date();
		$('.datePicker').datepicker({
			todayHighlight: true,
			format: "dd/mm/yyyy",
			startDate: dt
		}).on('changeDate', function(e){
		    $(this).datepicker('hide');			    
		});
	} catch(err) {console.error(err);}
});

function funSubmit(mode,val){
	var action="";
	if(mode =='factList'){
		action='factorListMotorAdminNew.action';
	}
	/*else if(mode=='addRate'){
		action='editRateMotorAdminNew.action';
	}else if(mode=='editRate'){
		document.rateConfig.factorRateId.value=val;
		action='editRateMotorAdminNew.action';
	}*/
	document.rateConfig.mode.value=mode;
	document.rateConfig.action=action;
	document.rateConfig.submit();
}

function funcEdit(factorRateId,id){
	document.rateConfig.factorRateId.value=factorRateId;
	postFormRequest('${pageContext.request.contextPath}/'+id+'MotorAdminNew.action', id,'rateConfig');
}
function updateRate(factorRateId) {
	document.rateConfig.factorRateId.value=factorRateId;
	postFormRequest('${pageContext.request.contextPath}/updateRateMotorAdminNew.action', 'editRate','rateConfig');
	return false;
}
function funSubmitRate(polType,factId,effDate,desc){
	//document.rateConfig.policyType.value=polType;
	/*var e = document.getElementById("policyType");
	var polTypeDesc = e.options[e.selectedIndex].text;
	var b = document.getElementById("brokerId");
	var brokerName = b.options[b.selectedIndex].text;
	var c = document.getElementById("vehicleType");
	var vehUsageDes=c.options[c.selectedIndex].text;
	document.getElementById("policyTypeDescEng").value=polTypeDesc;
	document.getElementById("brokerNameDesc").value=brokerName;
	document.getElementById("vehUsageDesc").value=vehUsageDes;*/
	document.rateConfig.factorId.value=factId;
	document.rateConfig.rateEndDate.value=effDate;
	document.rateConfig.factorName.value=desc;
	document.rateConfig.mode.value='rateList';
	document.rateConfig.action='rateListMotorAdminNew.action';
	document.rateConfig.submit();
}

function funFactorUpload(){
	document.rateConfig.action='factorUploadMotorAdminNew.action';
    document.rateConfig.submit();
}
function funupload(){
	document.rateConfig.mode.value='rateList';
	document.rateConfig.action='rateListMotorAdminNew.action';
    document.rateConfig.submit();
}

function funRatingupload()
{
loaderImage();
document.rateConfig.action='upldFileMotorAdminNew.action';
document.rateConfig.submit();
}

function loaderImage()
{
	document.getElementById('loaderImage').style.display="block";
}

<s:if test='"P".equalsIgnoreCase(progressStatus)'>
$(document).ready(function(){
	setInterval(function(){cache_clear()},5000);
});
	function cache_clear(){
		window.location.reload(true);
	}
</s:if>

function funView(mode,flag){
	document.rateConfig.action='factorRateTranDetailsMotorAdminNew.action?';
	document.rateConfig.submit();
}

function funcDown(factorId,effdate,type){
	document.rateConfig.factorId.value=factorId;
	document.getElementById("rateEndDate").value=effdate;
	document.getElementById("downloadType").value=type;
	document.rateConfig.action='factorDownloadMotorAdminNew.action';
    document.rateConfig.submit();
}

/*ajax('<s:property value="vehicleType"/>','bodyType');
function ajax(val,id){
	var action='';
	if(id=='bodyType'){
		action=	'?vehicleType='+val+'&bodyType=<s:property value="bodyType"/>';
	}
	postRequest('${pageContext.request.contextPath}/ajaxMotorAdminNew.action'+action+'&reqFrom='+id, id);
}*/
function hideSupPolicy(){
	$("#rateListGrid").hide();
}
function openPopup(factor){
	var polType = document.getElementById("policyType").value;
	var action=	'?factorId='+factor+'&policyType='+polType;
	postRequest('${pageContext.request.contextPath}/modalAjaxMotorAdminNew.action'+action+'&reqFrom=modalPopup', 'factorInfoAjax');
}
</script>
</body>
</html>   
