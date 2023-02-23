<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<%-- <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/dataTables/css/dataTables.responsive.css"> --%>
<%-- 	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/dataTables/css/dataTables.jqueryui.css"> --%>
<%-- 	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/dataTables/css/dataTables.tableTools.css"> --%>
<%-- 	<link href="<%=request.getContextPath()%>/dataTables/css/jquery-start-ui.css" rel="stylesheet" type="text/css" /> --%>
<%-- 	<script type="text/javascript" src="<%=request.getContextPath()%>/dataTables/js/jquery-1.11.1.min.js"></script> --%>
<%-- 	<script type="text/javascript" src="<%=request.getContextPath()%>/dataTables/js/jquery.dataTables.min.js"></script> --%>
<%-- 	<script type="text/javascript" src="<%=request.getContextPath()%>/dataTables/js/dataTables.jqueryui.js"></script> --%>
<%-- 	<script type="text/javascript" src="<%=request.getContextPath()%>/dataTables/js/dataTables.responsive.js"></script> --%>
<%-- 	<script type="text/javascript" src="<%=request.getContextPath()%>/dataTables/js/dataTables.tableTools.js"></script> --%>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/dataTables/css/dataTables.responsive.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/dataTables/css/dataTables.jqueryui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/dataTables/css/dataTables.tableTools.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/dataTables/js/dataTables.responsive.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/dataTables/js/dataTables.tableTools.js"></script>

	<script type="text/javascript">
		
		$(document).ready(function () {
	 		  $('#gridTable').DataTable({
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
	 <style type="text/css">
	 .tableColWidth {
	 	min-width: 100px;
	 	max-width: 200px;
	 	width: 100px;
	 }
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
<div id="loading" style="width:100%">
   <img id="loading-image" src="${pageContext.request.contextPath}/images/madisonSymbolLogo.png"/>
</div>
<s:form id="report" name="report" method="post" action="" theme="simple">
<s:set var="selectpolicyType" value=""/>
<s:set var="disable" value='"N".equalsIgnoreCase(updateStatus)'/>
<div class="row">
	<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
		<s:actionerror cssStyle="color: red;" />
	</div>
</div>
<div class="container vehDtl">
<div class="Card_Parent ">
<div class="card card-5">
<div class="p-3">
<div class="row">
	<s:if test='"admin"==loginType || "admin".equalsIgnoreCase(loginType)'>
		<div class="container-fluid">
			<div class="row">
				<div class="col-md-12">
					<div class="panel panel-primary">
						<div class="panel-heading">
							<h3>
								<s:if test='"claim"==requireType'>
									<s:text name="label.claimIntimation" />
								</s:if>
								 <s:if test='"endorsement"==requireType'>
									<s:text name="label.endorement" />
								</s:if>
							</h3>
							<hr/>
							<br/>
						</div>				
						<div class="panel-body">
						<font color="green"><s:actionmessage cssStyle="list-style: none;"/></font>
							<div class="row">
								<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
									<div class="text"><s:text name="label.fromDate"/><font color="red">*</font></div>
									<div class="tbox">
										<s:textfield name="fromDate" id="fromDate" cssClass="form-control datepicker" readonly="true" />
									</div>
								</div>
								<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
									<div class="text"><s:text name="label.toDate" /><font color="red">*</font></div>
									<div class="tbox">
										<s:textfield name="toDate" id="toDate" cssClass="form-control datepicker" readonly="true" />
									</div>
								</div>
								<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4" align="center">
									<s:if test='"claim"==requireType'>
										<input type="button" onclick="funSubmit('calimReportList','','','','');" class="btn btn-sm btn-success" value="Submit" />
									</s:if>
									<s:elseif test='"endorsement"==requireType'>
										<input type="button" onclick="funSubmit('endorsementList','','','','');" class="btn btn-sm btn-success" value="Submit" />
									</s:elseif>
								</div>
							</div>	
							<br/>														
						</div>
						<s:if test="mode == 'calimReportList'">
							<s:if test="intimateList!= null && intimateList.size() > 0">
								<s:if test="productId == '65'">
									<div class="panel panel-primary">
										<div class="panel-heading">
											<s:text name="label.claim.comprehensive.and.fire.and.theft"/>
										</div> 
								        <div class="panel-body">
<!-- 											<div class="row"> -->
<!-- 												<table class="display responsive no-wrap gridTable" id="gridTable" width="100%" cellspacing="0"> -->
												<table class="table table-bordered table-hover" id="gridTable" >
													<thead class="bluecolortable">
													<tr>
														<th class="no-sort"><s:text name="label.master.sno"/></th>
														<td><s:text name="label.policyNumber"/></td>
														<td><s:text name="label.claimNumber"/></td>		
														<td><s:text name="label.make"/></td>
														<td><s:text name="label.model"/></td>
														<td><s:text name="label.status"/></td>
														<td><s:text name="label.view"/></td>
													</tr>
													</thead>
													<tbody>
														<s:iterator value="intimateList" id="intimateList" var="list" status="stat">
															<tr>
																<td><s:property value="#stat.count"/></td>
																<td><s:property value="#list.POLICY_NO"/></td>
																<td><s:property value="#list.CLAIM_NO"/></td>
																<td><s:property value="#list.MAKE_NAME" /></td>
																<td><s:property value="#list.MODEL_NAME"/></td>
																<td><s:property value="#list.APPROVER_STATUS"/></td>
																<td style="text-align: center;">
																	<input type="button" class="btn btn-warning btn-sm" onclick="funSubmit('viewClaim','<s:property value="#list.POLICY_NO"/>','<s:property value="#list.VEHICLE_ID"/>','<s:property value="#list.CLAIM_NO"/>','<s:property value="productId"/>');" value="View" />
																</td>
															</tr>
														</s:iterator>
													</tbody>
												</table> 								
<!-- 											</div> -->
										</div>   
									</div>
									<s:if test="intimateThirdPartyList!= null && intimateThirdPartyList.size() > 0">
										<div class="panel panel-primary">
											<div class="panel-heading">
												<h4><s:text name="label.claim.third.party"/></h4>
											</div>     
									        <div class="panel-body">
													<table class="table table-bordered table-hover" id="gridTable">
														<thead class="bluecolortable">
														<tr>
															<th class="no-sort"><s:text name="label.master.sno"/></th>
															<td><s:text name="label.policyNumber"/></td>
															<td><s:text name="label.claimNumber"/></td>		
															<td><s:text name="label.make"/></td>
															<td><s:text name="label.model"/></td>
															<td><s:text name="label.status"/></td>
															<td><s:text name="label.view"/></td>
														</tr>
														</thead>
														<tbody class="rowevencolor">
															<s:iterator value="intimateThirdPartyList" id="intimateThirdPartyList" var="list" status="stat">
																<tr>
																		<td><s:property value="#stat.count"/></td>
																		<td><s:property value="#list.POLICY_NO"/></td>
																		<td><s:property value="#list.CLAIM_NO"/></td>
																		<td><s:property value="#list.MAKE_NAME" /></td>
																		<td><s:property value="#list.MODEL_NAME"/></td>
																		<td><s:property value="#list.APPROVER_STATUS"/></td>
																		<td style="text-align: center;">
																			<input type="button" class="btn btn-warning btn-sm" onclick="funSubmit('viewClaim','<s:property value="#list.POLICY_NO"/>','<s:property value="#list.VEHICLE_ID"/>','<s:property value="#list.CLAIM_NO"/>','<s:property value="productId"/>');" value="View" />
																	    </td>
																</tr>
															</s:iterator>
														</tbody>
													</table>								
											</div>
									    </div>
								    </s:if>
							    </s:if>
								<s:if test="productId == '30'">
									<div class="panel-body">
										<div class="row">
											<table class="table table-bordered table-hover" id="gridTable" cellspacing="0" width="100%" >
												<thead class="bluecolortable">
												<tr>
													<th class="no-sort"><s:text name="label.master.sno"/></th>
													<td><s:text name="label.policyNumber"/></td>
													<td><s:text name="label.claimNumber"/></td>		
													<td><s:text name="label.customerName"/></td>
													<td><s:text name="label.status"/></td>
													<td><s:text name="label.view"/></td>
												</tr>
												</thead>
												<tbody class="rowevencolor">
													<s:iterator value="intimateList" id="intimateList" var="list" status="stat">
														<tr>
																<td><s:property value="#stat.count"/></td>
																<td><s:property value="#list.CLAIM_NO"/></td>
																<td><s:property value="#list.POLICY_NO"/></td>
																<td><s:property value="#list.CUSTOMER_NAME" /></td>
																<td><s:property value="#list.APPROVER_STATUS"/></td>
																<td style="text-align: center;">
																<input type="button" class="btn btn-warning btn-sm" onclick="funSubmit('viewClaim','<s:property value="#list.POLICY_NO"/>','','<s:property value="#list.CLAIM_NO"/>','<s:property value="productId"/>');" value="view" />
															    </td>
														</tr>
													</s:iterator>
												</tbody>
											</table> 								
										</div>
									</div>
								</s:if>
							</s:if>
					    </s:if>
					    <s:if test="mode == 'endorsementList'">
							<s:if test="productId == '65'">
								<div class="row">
									<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
<!-- 										<table class="display responsive no-wrap gridTable" width="100%" cellspacing="0"> -->
										<table class="table table-bordered table-hover" id="gridTable" cellspacing="0" width="100%" >
											<thead class="bluecolortable">
												<tr>
													<th><s:text name="label.master.sno"/></th>
													<th><s:text name="label.policyNo"/></th>
													<th><s:text name="label.vehicletypedesc"/></th>
													<th><s:text name="label.endt.type"/></th>					
													<th><s:text name="label.entry.date"/></th>		
													<th><s:text name="label.agent.remarks"/></th>
													<th><s:text name=""/></th>
													<th><s:text name="label.ref.no"/></th>
													<th><s:text name="label.view"/></th>
												</tr>
												</thead>
												<tbody class="rowevencolor">
													<s:iterator value="endorsementList" var="list" status="stat">
														<tr>
															<td ><s:property value="#stat.count"/></td>
															<td><s:property value="#list.POLICY_NO"/></td>
															<td><s:property value="#list.VEHICLETYPE_DESC" /></td>
															<td><s:property value="#list.ENDT_TYPE" /></td>
															<td><s:property value="#list.ENTRY_DATE"/></td>
															<td><s:property value="#list.AGENT_REMARKS" /></td>
															<td><s:property value="#list.STATUS" /></td>
															<td><s:property value="#list.REFERENCE_NO"/></td>
															<td>
															<input type="button" class="btn btn-warning btn-sm" onclick="funSubmit('viewEndorsement','<s:property value="#list.POLICY_NO"/>','<s:property value="#list.VEHICLE_ID" />','','<s:property value="#list.REFERENCE_NO" />');" value="View" />
															</td>
														</tr>
													</s:iterator>
												</tbody>
										</table>
									</div>
								</div>
							</s:if>	
							<s:if test="productId == '30'">
									<div class="panel-body">
										<div class="row">
											<table class="table table-bordered table-hover" id="gridTable" cellspacing="0" width="100%" >
												<thead class="bluecolortable">
												<tr>
													<th><s:text name="label.master.sno"/></th>
													<th><s:text name="label.policyNo"/></th>
													<td><s:text name="label.customerName"/></td>
													<th><s:text name="label.endt.type"/></th>					
													<th><s:text name="label.entry.date"/></th>		
													<th><s:text name="label.agent.remarks"/></th>
													<th><s:text name="label.admin.status"/></th>
													<th><s:text name="label.ref.no"/></th>
													<th><s:text name="label.view"/></th>
												</tr>
												</thead>
												<tbody class="rowevencolor">
													<s:iterator value="endorsementList" id="intimateList" var="list" status="stat">
														<tr>
															<td ><s:property value="#stat.count"/></td>
															<td><s:property value="#list.POLICY_NO"/></td>
															<td><s:property value="#list.CUSTOMER_NAME" /></td>
															<td><s:property value="#list.ENDT_TYPE" /></td>
															<td><s:property value="#list.ENTRY_DATE"/></td>
															<td><s:property value="#list.AGENT_REMARKS" /></td>
															<td><s:property value="#list.STATUS" /></td>
															<td><s:property value="#list.REFERENCE_NO"/></td>
																<td style="text-align: center;">
																<input type="button" class="btn btn-warning btn-sm" onclick="funSubmit('viewEndorsement','<s:property value="#list.POLICY_NO"/>','','','<s:property value="#list.REFERENCE_NO" />');" value="view" />
															    </td>
														</tr>
													</s:iterator>
													</tbody>
													</table> 										
													</div>
													</div>
												</s:if>
										    </s:if>
									   </div>
									</div>
								</div>
							</div> 
						</s:if>
<s:else>
							<s:if test="productId == '65' && mode == 'individual'">
								<div class="panel panel-primary">
									<div class="panel-heading">
										<s:if test='"claim"==requireType'>
											<s:text name="label.claimIntimation" />
										</s:if>
										<div class="pullRight">
											<input type="button" class="btn btn-sm btn-default" value="Add New" onclick="addNew('Add','<s:property value="policyNo"/>','<s:property value="vehicleId"/>');">
										</div>
									</div>
									<div class="panel-body">
									<font color="green"><s:actionmessage cssStyle="list-style: none;"/></font>
										<div class="row">
											<div class="col-xs-12 col-sm-12 col-md-8 col-lg-8">
												<span class="textV" style="width:25%;">	<s:text name="label.policyNo" /> </span> <span class="tboxV" style="width:75%;">&nbsp; : &nbsp; <s:property value="policyNo"/> </span> 
											</div>
										</div>
										<s:iterator value="claimDistinctVehicle" id="claimDistinctVehicle" var="list" status="stat">
										<s:set name="selectpolicyType" var="selectpolicyType" value="%{#list.ISSELECTCOVER}"/>
										<div class="row">
											<div class="col-xs-12 col-sm-12 col-md-8 col-lg-8">
												<span class="textV" style="width:25%;">	<s:text name="label.customerName" /> </span> <span class="tboxV" style="width:75%;">&nbsp; : &nbsp; <s:property value="#list.CUSTOMER_NAME"/> </span> 
											</div>
										</div>
										<div class="row">
											<div class="col-xs-12 col-sm-12 col-md-8 col-lg-8">
												<span class="textV" style="width:25%;">	<s:text name="label.make" /> </span> <span class="tboxV" style="width:75%;">&nbsp; : &nbsp; <s:property value="#list.MAKE_NAME"/> </span> 
											</div>
										</div>
										<div class="row">
											<div class="col-xs-12 col-sm-12 col-md-8 col-lg-8">
												<span class="textV" style="width:25%;">	<s:text name="claim.model" /> </span> <span class="tboxV" style="width:75%;">&nbsp; : &nbsp; <s:property value="#list.MODEL_NAME"/> </span> 
											</div>
										</div>
										</s:iterator>
										<div class="row"   <s:if test='%{(getText("MOTOR_TP_ID")).equalsIgnoreCase(#selectpolicyType)}'>style="display: none;"</s:if> >
											<table class="table table-bordered table-hover" id="gridTable" cellspacing="0" width="100%" >
												<thead class="bluecolortable">
												<tr>
													<th class="no-sort"><s:text name="label.master.sno"/></th>		
													<td><s:text name="label.refrenceNumber"/></td>
													<td><s:text name="label.entryDate"/></td>
													<td><s:text name="label.view"/></td>
												</tr>
												</thead>
												<tbody class="rowevencolor">
													<s:iterator value="claimSameVehicleList" id="claimSameVehicleList" var="list" status="stat">
														<tr>
																<td><s:property value="#stat.count"/></td>
																<td><s:property value="#list.CLAIM_NO" /></td>
																<td><s:property value="#list.ENTRY_DATE"/></td>
																<td style="text-align: center;">
																<input type="button" class="btn btn-warning btn-sm" onclick="claimlList('','<s:property value="%{POLICY_NO}"/>','<s:property value="%{VEHICLE_ID}"/>','<s:property value="%{CLAIM_NO}"/>');" value="view" />
															    </td>
														</tr>
													</s:iterator>
												</tbody>
											</table> 								
										</div>
										<div class="row"  <s:if test='%{!(getText("MOTOR_TP_ID")).equalsIgnoreCase(#selectpolicyType)}'>style="display: none;"</s:if> >
											<table class="table table-bordered table-hover" id="gridTable" cellspacing="0" width="100%" >
												<thead class="bluecolortable">
												<tr>
													<th class="no-sort"><s:text name="label.master.sno"/></th>		
													<td><s:text name="label.refrenceNumber"/></td>
													<td><s:text name="label.entryDate"/></td>
													<td><s:text name="label.view"/></td>
												</tr>
												</thead>
												<tbody class="rowevencolor">
													<s:iterator value="claimSameTpaVehicleList" id="claimSameTpaVehicleList" var="list" status="stat">
														<tr>
																<td><s:property value="#stat.count"/></td>
																<td><s:property value="#list.CLAIM_NO" /></td>
																<td><s:property value="#list.ENTRY_DATE"/></td>
																<td style="text-align: center;">
																<input type="button" class="btn btn-warning btn-sm" onclick="claimlList('','<s:property value="%{POLICY_NO}"/>','<s:property value="%{VEHICLE_ID}"/>','<s:property value="%{CLAIM_NO}"/>');" value="view" />
															    </td>
														</tr>
													</s:iterator>
												</tbody>
											</table> 								
										</div>
									</div>
								</div>
							</s:if>
							<s:if test="productId == '30' && mode == 'individual'">
							<br/>
								<div class="panel panel-primary">
									<div class="panel-heading">
										<s:if test='"claim"==requireType'>
											<h4><s:text name="label.claimIntimation" /></h4>
											<hr/>
										</s:if>
										<div class="pullRight">
											<input type="button" class="btn btn-sm btn-default" value="Add New" onclick="addNew('Add','<s:property value="policyNo"/>','<s:property value="vehicleId"/>');">
										</div>
									</div>
									<div class="panel-body">
									<font color="green"><s:actionmessage cssStyle="list-style: none;"/></font>
										<div class="row">
											<div class="col-xs-12 col-sm-12 col-md-8 col-lg-8">
												<span class="textV" style="width:25%;">	<s:text name="label.policyNo" /> </span> <span class="tboxV" style="width:75%;">&nbsp; : &nbsp; <s:property value="policyNo"/> </span> 
											</div>
										</div>
										<s:iterator value="claimDistinctVehicle" id="claimDistinctVehicle" var="list" status="stat">
										<div class="row">
											<div class="col-xs-12 col-sm-12 col-md-8 col-lg-8">
												<span class="textV" style="width:25%;">	<s:text name="label.customerName" /> </span> <span class="tboxV" style="width:75%;">&nbsp; : &nbsp; <s:property value="#list.CUSTOMER_NAME"/> </span> 
											</div>
										</div>
										</s:iterator>
										<div class="row">
											<table class="table table-bordered table-hover" id="gridTable" cellspacing="0" width="100%" >
												<thead class="bluecolortable">
												<tr>
													<th class="no-sort"><s:text name="label.master.sno"/></th>		
													<td><s:text name="label.refrenceNumber"/></td>
													<td><s:text name="label.entryDate"/></td>
													<td><s:text name="label.view"/></td>
												</tr>
												</thead>
												<tbody class="rowevencolor">
													<s:iterator value="claimSameVehicleList" id="claimSameVehicleList" var="list" status="stat">
														<tr>
																<td><s:property value="#stat.count"/></td>
																<td><s:property value="#list.CLAIM_NO" /></td>
																<td><s:property value="#list.ENTRY_DATE"/></td>
																<td style="text-align: center;">
																<input type="button" class="btn btn-warning btn-sm" onclick="claimlList('','<s:property value="%{POLICY_NO}"/>','','<s:property value="%{CLAIM_NO}"/>');" value="view" />
															    </td>
														</tr>
													</s:iterator>
												</tbody>
											</table> 								
										</div>
									</div>
								</div>
							</s:if>
							<s:if test="mode == 'newReq'">
							<s:if test="productId == '65' || productId == '30'">
								<div class="panel panel-primary">
									<div class="panel-heading">
										<s:if test='"endorsement"==requireType'>
											<h4><s:text name="Financial Endorsement Request" /></h4>
											<hr>
										</s:if>
										<div class="pullRight">
											<input type="button" class="btn btn-sm btn-default" value="Add New" onclick="addNew('Add','<s:property value="policyNo"/>','<s:property value="vehicleId"/>');">
										</div>
									</div>
									<div class="panel-body">
									<font color="green"><s:actionmessage cssStyle="list-style: none;"/></font>
										<div class="row">
											<div class="col-xs-12 col-sm-12 col-md-8 col-lg-8">
												<span class="textV" style="width:25%;">	<s:text name="label.policyNo" /> </span> <span class="tboxV" style="width:75%;">&nbsp; : &nbsp; <s:property value="policyNo"/> </span> 
											</div>
										</div>
										<div class="row">
											<table class="table table-bordered table-hover" id="gridTable" cellspacing="0" width="100%" >
												<thead class="bluecolortable">
												<tr>
													<th class="no-sort"><s:text name="label.master.sno"/></th>		
													<th><s:text name="label.refrenceNumber"/></th>
													<th><s:text name="label.endt.type"/></th>					
													<th><s:text name="label.entryDate"/></th>
													<th><s:text name="label.view"/></th>
												</tr>
												</thead>
												<tbody class="rowevencolor">
													<s:iterator value="endtReqList" id="endtReqList" var="list" status="stat">
														<tr>
																<td><s:property value="#stat.count"/></td>
																<td><s:property value="#list.REFERENCE_NO"/></td>
																<td><s:property value="#list.ENDT_TYPE_DESC" /></td>																	
																<td><s:property value="#list.ENTRY_DATE"/></td>
																<td style="text-align: center;">
																	<input type="button" class="btn btn-warning btn-sm" onclick="updateEndt('update','<s:property value="policyNo"/>','<s:property value="#list.REFERENCE_NO"/>','<s:property value="#list.ENDORSEMENT_ID"/>','<s:property value="#list.AGENT_REMARKS"/>');" value="View" />
																</td>
														</tr>
													</s:iterator>
												</tbody>
											</table> 								
										</div>
									</div>
								</div>
							</s:if>
						</s:if>
						<s:if test='"Add".equalsIgnoreCase(mode) || "Update".equalsIgnoreCase(mode)|| "Insert".equalsIgnoreCase(mode)'>					
						<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
							<div class="panel panel-primary">
								<div class="panel-heading">
								<h4>
									<s:if test='"claim"==requireType'>
										<s:text name="label.claimIntimation" />
									</s:if>
									 <s:if test='"endorsement"==requireType'>
										<s:text name="label.endorement" />
									</s:if>
								</h4>
								<hr>
								</div>
								<div class="panel-body">
									<div class="row">
										<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
											<div class="panel panel-primary">
												<div class="panel-heading">
													<s:text name="label.policyHolderDetails" />
												 </div>
											<s:iterator value="intimatePolicyList" status="stat" var="list">
									<s:set name="selectpolicyType" var="selectpolicyType" value="%{#list.ISSELECTCOVER}"/>
									<div class="panel-body">
										<div class="row">
											<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
												<span class="textV">	<s:text name="label.policyNo" /> </span> <span class="tboxV">&nbsp; : &nbsp; <s:property value="#list.POLICY_NO"/> </span> 
											</div>
											<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
												<div class="textV">
													<s:text name="label.periodOfInsurance" />
												</div>
												<div class="tboxV">
													&nbsp; : &nbsp; <s:property value="#list.POLICY_START_DATE"/> to <s:property value="#list.POLICY_END_DATE"/> 
												</div>
											</div>
										</div>
										<div class="row">
											<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
												<span class="textV">	<s:text name="label.nameOfTheInsured" /> </span> <span class="tboxV">&nbsp; : &nbsp; <s:property value="#list.INSUREDNAME"/> </span> 
											</div>
											<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
												<span class="textV">	<s:text name="label.mobileNo" /> </span> <span class="tboxV">&nbsp; : &nbsp; <s:property value="#list.MOBILE"/> </span>
											</div>
											<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
												<span class="textV">	<s:text name="label.email" /> </span> <span class="tboxV">&nbsp; : &nbsp; <s:property value="#list.EMAIL"/> </span>
											</div>
										</div>
										<div class="row">
											<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
												<span class="textV" style="width:25%;">	<s:text name="label.physicalAddress" /> </span> <span class="tboxV" style="width:75%;">&nbsp; : &nbsp; <s:property value="#list.ADDRESS"/> </span> 
											</div>
										</div>
									</div>
								</s:iterator>
							</div>
							<s:if test="#session.product_id == '30'">
								<div class="panel panel-primary">
									<div class="panel-heading">
										<h4><s:text name="label.homePremiumInformation" /></h4>
										<hr>
									 </div>
									 <table class="table table-bordered table-hover" id="gridTable" cellspacing="0" width="100%" >
										<thead class="bluecolortable">
										<tr>
											<th width="40%"><s:text name="coverage.details"/></th>
											<th width="30%"><s:text name="coverage.sumInsured"/></th>
											<th width="30%" align="right"><s:text name="label.premium"/> in [<s:property value="#session.BrokerDetails.CurrencyAbb"/>]</th>
										</tr>
										</thead>
										<tbody class="rowevencolor">
											<s:iterator var="list" value="homeList">
												<s:if test='PREMIUM_AMOUNT!=null'>
													<tr>
														<td>${coverages_display_name}</td>
														<td align="right"><b>
															<s:property value="COVERAGES_SUMINSURED" />&nbsp;<s:property value="#session.BrokerDetails.CurrencyAbb"/></b>
														</td>
														<td align="right">
															<b><s:property value="getText('{0,number,#,##0.00}',{PREMIUM_AMOUNT})"/></b>
															<s:if test='#coverPremium'>
																<b><s:property value="getText('{0,number,#,##0.00}',{PREMIUM_AMOUNT})"/></b>
															</s:if>
														</td>
													</tr>
												</s:if>
											</s:iterator>
									    </tbody>
								     </table>
								</div>
							</s:if>
							<s:if test="#session.product_id == '65'">
								<div class="panel panel-primary">
									<div class="panel-heading">
										<h4><s:text name="label.vehicleDetails" /></h4>
										<hr>
									</div>
									<s:iterator value="intimateVehicleList" status="stat" var="list">
										<div class="panel-body">
											<div class="row">
												<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
													<span class="textV">	<s:text name="label.make" /> </span> <span class="tboxV">&nbsp; : &nbsp; <s:property value="#list.MAKE"/> </span>
												</div>
												<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
													<span class="textV">	<s:text name="claim.model" /> </span> <span class="tboxV">&nbsp; : &nbsp; <s:property value="#list.MODEL_NAME"/> </span>
												</div>
												<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
													<span class="textV">	<s:text name="label.year" /> </span> <span class="tboxV">&nbsp; : &nbsp; <s:property value="#list.YEAR"/> </span> 
												</div>
												<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
													<span class="textV">	<s:text name="label.regNo" /> </span> <span class="tboxV">&nbsp; : &nbsp; <s:property value="#list.REGISTRATION_NO"/></span>
												</div>
												<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
													<span class="textV">	<s:text name="label.engineNo" /> </span> <span class="tboxV">&nbsp; : &nbsp; <s:property value="#list.ENGINE_NUMBER"/> </span>
												</div>
												<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
													<span class="textV">	<s:text name="label.chassisNo" /> </span> <span class="tboxV">&nbsp; : &nbsp; <s:property value="#list.CHASSIS_NO"/> </span>
												</div>
												<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
													<span class="textV">	<s:text name="label.colour" /> </span> <span class="tboxV">&nbsp; : &nbsp; <s:property value="#list.COLOR"/> </span>
												</div>
												<s:if test='"claim"==requireType'>
													<s:if test='%{!(getText("MOTOR_TP_ID")).equalsIgnoreCase(#selectpolicyType)}'>
														<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
															<div class="text">
													 			<s:text name="label.Are.you.the.sole.owner.of.the.vehicle"  /><font color="red">*</font>
													 		</div>
													 		<div class="tbox">									 			
													 			 <s:radio list="#{'Y':'Yes','N':'No'}" name="soleOwnerYN" id="soleOwnerYN" value='%{soleOwnerYN==null?"N":soleOwnerYN}' onchange="enable(this.value,'financiers')" />							 		
													 		</div>
												 		</div>
												 		<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
															<div class="text" id="soleOwnerYN" >
													 			<s:text name="label.If.not.name.of.other.interested.parties.Financiers"   /><font color="red">*</font>
													 		</div>
													 		<div class="tbox">									 			
													 			 <s:textfield name="financiers" id="financiers" readonly='%{soleOwnerYN=="N"?"false":"true"}' cssClass="form-control" />							 		
													 		</div>
												 		</div>
											 		</s:if>
											 	</s:if>
											</div>
										</div>
								    </s:iterator>
								</div>
								<s:if test='"claim"==requireType'>
									<div class="panel panel-primary" <s:if test='%{(getText("MOTOR_TP_ID")).equalsIgnoreCase(#selectpolicyType)}'>style="display: none;"</s:if> >
										<div class="panel-heading">
											<h4><s:text name="label.driverDetails" /></h4>
											<hr/>
										</div>
										<div class="panel-body">
											<div class="row">
												<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
													<div class="text">
											 			<b><s:text name="label.nameOfDriver"  /></b><font color="red">*</font>
											 		</div>
											 		<div class="tbox">									 			
											 			 <s:textfield name="nameOfDriver" id="nameOfDriver" cssClass="form-control" />							 		
											 		</div>
										 		</div>
										 		<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
													<div class="text">
											 			<b><s:text name="label.dob"  /></b><font color="red">*</font>
											 		</div>
											 		<div class="tbox">									 			
											 			<b> <s:textfield name="driverDob" id="driverDob" cssClass="form-control datePicker" />							 		
											 		</div>
										 		</div>
										 		<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
													<div class="text">
											 			<b><s:text name="label.licenseNo"  /></b><font color="red">*</font>
											 		</div>
											 		<div class="tbox">									 			
											 			<s:textfield name="licenseNumber" id="licenseNumber" cssClass="form-control" />							 		
											 		</div>
										 		</div>
										 		<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
													<div class="text">
											 			<b><s:text name="label.dateObtained"  /></b><font color="red">*</font>
											 		</div>
											 		<div class="tbox">									 			
											 			 <s:textfield name="dateObtained" id="dateObtained" cssClass="form-control datePicker" />							 		
											 		</div>
										 		</div>
											</div>
										</div>
									</div>
									<br/>
									<div class="panel panel-primary" <s:if test='%{(getText("MOTOR_TP_ID")).equalsIgnoreCase(#selectpolicyType)}'> style="display: none;"</s:if>>
										<div class="panel-heading">
											<h4><s:text name="label.lossDamageTheftDetails" /></h4>
											<hr/>
										</div>
										<div class="panel-body">
											<div class="row">
												<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
													<div class="text">
											 			<s:text name="label.date"  /><font color="red">*</font>
											 		</div>
											 		<div class="tbox">									 			
											 			 <s:textfield name="lossDate" id="lossDate" cssClass="form-control datePicker" />							 		
											 		</div>
										 		</div>
										 		<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
													<div class="text">
											 			<s:text name="label.time"  /><font color="red">*</font>
											 		</div>
											 		<div class="tbox">									 			
											 			 <s:textfield name="lossTime" id="lossTime" cssClass="form-control" />							 		
											 		</div>
										 		</div>
										 		<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
													<div class="text">
											 			<s:text name="label.speed"  /><font color="red">*</font>
											 		</div>
											 		<div class="tbox">									 			
											 			 <s:textfield name="lossSpeed" id="lossSpeed" cssClass="form-control" />							 		
											 		</div>
										 		</div>
										 	</div>
										 	<div class="row">
										 		<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
													<div class="text">
											 			<s:text name="label.place"  /><font color="red">*</font>
											 		</div>
											 		<div class="tbox">									 			
											 			 <s:textarea rows="2" name="lossPlace" id="lossPlace" cssClass="form-control" cssStyle="width: 100%;" />							 		
											 		</div>
										 		</div>
										 		<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
													<div class="text">
											 			<s:text name="label.purposeforwhichthevehiclewasbeingusedatthetimeofaccident"  /><font color="red">*</font>
											 		</div>
											 		<div class="tbox">									 			
											 			 <s:textarea rows="2" name="lossPurposeDescription" id="lossPurposeDescription" cssClass="form-control" cssStyle="width: 100%;" />							 		
											 		</div>
										 		</div>
										 		<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
													<div class="text">
											 			<s:text name="label.missingPartsDetails"  />
											 		</div>
											 		<div class="tbox">									 			
											 			 <s:textarea rows="2" name="lossDetailDescription" id="lossDetailDescription" cssClass="form-control" cssStyle="width: 100%;" />							 		
											 		</div>
										 		</div>
											</div>
											<br class="clear" />
											<br class="clear" />
											<div class="row">
										 		<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
													<div class="text">
											 			<s:text name="label.reportedToPolice"  /><font color="red">*</font>
											 		</div>
											 		<div class="tbox">									 			
											 			 <s:radio list="#{'Y':'Yes','N':'No'}" name="lossReportYN" id="lossReportYN" value='%{lossReportYN==null?"N":lossReportYN}' onchange="enable1(this.value,'lossDateReported','lossTimeReported')"  />							 		
											 		</div>
										 		</div>
										 		<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
													<div class="text" >
											 			<s:text name="label.dateReported"  /><font color="red">*</font>
											 		</div>
											 		<div class="tbox">									 			
											 			 <s:textfield name="lossDateReported" id="lossDateReported" readonly='%{lossReportYN=="Y"?"false":"true"}' cssClass="form-control datePicker" />							 		
											 		</div>
										 		</div>
										 		<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
													<div class="text">
											 			<s:text name="label.timeReported"  /><font color="red">*</font>
											 		</div>
											 		<div class="tbox">									 			
											 			 <s:textfield name="lossTimeReported" id="lossTimeReported" readonly='%{lossReportYN=="Y"?"false":"true"}' cssClass="form-control datetimepicker" />
											 		</div>
										 		</div>
										 		<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
													<div class="text">
											 			<s:text name="label.policeVisited"  /><font color="red">*</font>
											 		</div>
											 		<div class="tbox">									 			
											 			 <s:radio list="#{'Y':'Yes','N':'No'}" name="lossPoliceVisitYN" id="lossPoliceVisitYN"  value='%{lossPoliceVisitYN==null?"N":lossPoliceVisitYN}' onchange="enable2(this.value,'PoliceOfficerName','identityNumber','policeStationName')" />							 		
											 		</div>
										 		</div>
										 		<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
													<div class="text">
											 			<s:text name="label.nameofpoliceofficer"  /><font color="red">*</font>
											 		</div>
											 		<div class="tbox">									 			
											 			 <s:textfield name="PoliceOfficerName" id="PoliceOfficerName" readonly='%{lossPoliceVisitYN=="Y"?"false":"true"}' cssClass="form-control" />						 		
											 		</div>
										 		</div>
										 		<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
													<div class="text">
											 			<s:text name="label.identityNo"  />
											 		</div>
											 		<div class="tbox">									 			
											 			 <s:textfield name="identityNumber" id="identityNumber" readonly='%{lossPoliceVisitYN=="Y"?"false":"true"}' cssClass="form-control" />						 		
											 		</div>
										 		</div>							 		
											</div>
											<br class="clear" />
											<div class="row">
										 		<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
													<div class="text">
											 			<s:text name="label.nameofpolicestation"  /><font color="red">*</font>
											 		</div>
											 		<div class="tbox">									 			
											 			 <s:textarea rows="2" name="policeStationName" id="policeStationName" cssClass="form-control" readonly='%{lossPoliceVisitYN=="Y"?"false":"true"}' cssStyle="width: 100%;" />							 		
											 		</div>
										 		</div>
										 		<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
													<div class="text">
											 			<s:text name="label.locationofDamagedVehicleWMobile"  /><font color="red">*</font>
											 		</div>
											 		<div class="tbox">									 			
											 			 <s:textarea rows="2" name="lossLocation" id="lossLocation" cssClass="form-control" cssStyle="width: 100%;" />							 		
											 		</div>
										 		</div>
											</div>
											<br class="clear" />
										</div>
									</div>
									<div class="panel panel-primary" <s:if test='%{(getText("MOTOR_TP_ID")).equalsIgnoreCase(#selectpolicyType)}'>style="display: none;"</s:if>>
										<div class="panel-heading">
											<h4><s:text name="label.accidentHappendStatement" /></h4>
										</div>
										<div class="panel-body">
											<div class="row">
												<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
													<div class="tbox">									 			
											 			 <s:textarea rows="2" name="driverDescription" id="driverDescription" cssClass="form-control" cssStyle="width: 100%;" />							 		
											 		</div>
											 		<br class="clear"/>
												</div>
											</div>
										</div>
									</div>
									<br/>
									<div class="panel panel-primary" <s:if test='%{!(getText("MOTOR_TP_ID")).equalsIgnoreCase(#selectpolicyType)}'>style="display: none;"</s:if>>
										<div class="panel-heading">
											<h4><s:text name="label.driverPassengerThirdPartyInjuries" /></h4>
											<hr/>
										</div>
										<div class="panel-body">
											<div class="row">
												<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
													<table width="100%" class="footable">
														<thead class="bluecolortable">
														<tr>
															<th width="5%"></th>
															<th width="23.75%"> <s:text name="label.name" /> </th>
															<th width="23.75%"> <s:text name="label.phoneNo" /> </th>
															<th width="23.75%"> <s:text name="label.address" /> </th>
															<th width="23.75%"> <s:text name="label.natureOfInjury" /> </th>
														</tr>
														</thead>
														<tbody class="rowevencolor">
														<tr>
															<td align="center">A</td>
															<td><s:textfield name="passengerNameA" id="passengerNameA" cssClass="form-control" /></td>
															<td><s:textfield name="passengerPhoneNoA" id="passengerPhoneNoA" cssClass="form-control numericOnly" /></td>
															<td><s:textarea rows="3" name="passengerAddressA" id="passengerAddressA" cssClass="form-control" cssStyle="width: 100%;" /></td>					
															<td><s:textarea rows="3" name="passengerInjuryA" id="passengerInjuryA" cssClass="form-control" cssStyle="width: 100%;" /></td>
														</tr>
														<tr>
															<td align="center">B</td>
															<td><s:textfield name="passengerNameB" id="passengerNameB" cssClass="form-control" /></td>
															<td><s:textfield name="passengerPhoneNoB" id="passengerPhoneNoB" cssClass="form-control numericOnly" /></td>
															<td><s:textarea rows="3" name="passengerAddressB" id="passengerAddressB" cssClass="form-control" cssStyle="width: 100%;" /></td>
															<td><s:textarea rows="3" name="passengerInjuryB" id="passengerInjuryB" cssClass="form-control" cssStyle="width: 100%;" /></td>
														</tr>
														</tbody>
													</table>
												</div>
											</div>
										</div>
									</div>
									<div class="panel panel-primary" <s:if test='%{!(getText("MOTOR_TP_ID")).equalsIgnoreCase(#selectpolicyType)}'>style="display: none;"</s:if>>
										<div class="panel-heading">
											<h4><s:text name="label.hospitalPersonsAttened" /></h4>
											<hr>
										</div>
										<div class="panel-body">
											<div class="row">
											    <div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
													<div class="text">
											 			<s:text name="label.nameofhospital"  /><font color="red">*</font>
											 		</div>
											 		<div class="tbox">									 			
											 			 <s:textfield name="hospitalName" id="hospitalName" cssClass="form-control" />							 		
											 		</div>
											 	</div>
												<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
													<div class="text">
											 			<s:text name="label.nameofdoctor"  /><font color="red">*</font>
											 		</div>
											 		<div class="tbox">									 			
											 			 <s:textfield name="nameOfDoctor" id="nameOfDoctor" cssClass="form-control" />							 		
											 		</div>
										 		</div>
										 		<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
													<div class="text">
											 			<s:text name="label.telephoneNo"  /><font color="red">*</font>
											 		</div>
											 		<div class="tbox">									 			
											 			 <s:textfield name="doctorTelephoneNumber" id="doctorTelephoneNumber" cssClass="form-control numericOnly" />							 		
											 		</div>
										 		</div>
											 </div>
										</div>
									</div>
									<div class="panel panel-primary" <s:if test='%{!(getText("MOTOR_TP_ID")).equalsIgnoreCase(#selectpolicyType)}'>style="display: none;"</s:if>>
										<div class="panel-heading">
											<h4><s:text name="label.thirdPartyPropertyDamage" /></h4>
											<hr>
										</div>
										<div class="panel-body">
											<div class="row">
												<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
													<div class="text">
											 			<s:text name="label.name"  /><font color="red">*</font>
											 		</div>
											 		<div class="tbox">									 			
											 			 <s:textfield name="thirdPartyName" id="thirdPartyName" cssClass="form-control" />							 		
											 		</div>
										 		</div>
										 		<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
													<div class="text">
											 			<s:text name="label.propertyMotorVehicleDetails"  /><font color="red">*</font>
											 		</div>
											 		<div class="tbox">									 			
											 			 <s:textarea rows="2" name="thirdPartyPropertyDetail" id="thirdPartyPropertyDetail" cssClass="form-control" cssStyle="width: 100%;" />
											 		</div>
											 		<br class="clear"/>
										 		</div>
										 	</div>
										 </div>
									</div>
									<div class="panel panel-primary" <s:if test='%{!(getText("MOTOR_TP_ID")).equalsIgnoreCase(#selectpolicyType)}'>style="display: none;"</s:if>>
										<div class="panel-heading">
											<h4><s:text name="label.involvesThirdParty" /></h4>
										</div>
										<div class="panel-body">
											<div class="row">
												<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
													<div class="text">
											 			<s:text name="label.nameofOwner"  /><font color="red">*</font>
											 		</div>
											 		<div class="tbox">									 			
											 			 <s:textfield name="thirdPartyOwnerName" id="thirdPartyOwnerName" cssClass="form-control" />							 		
											 		</div>
										 		</div>
										 		<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
													<div class="text">
											 			<s:text name="label.ownertelephoneNo"  /><font color="red">*</font>
											 		</div>
											 		<div class="tbox">									 			
											 			 <s:textfield name="thirdPartyOwnerTelephoneNumber" id="thirdPartyOwnerTelephoneNumber" cssClass="form-control numericOnly" />							 		
											 		</div>
										 		</div>
										 		<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
													<div class="text">
											 			<s:text name="label.nameOfDriver"  /><font color="red">*</font>
											 		</div>
											 		<div class="tbox">									 			
											 			 <s:textfield name="driverName" id="driverName" cssClass="form-control" />							 		
											 		</div>
										 		</div>
										 		<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
													<div class="text">
											 			<s:text name="label.physicalAddress"  /><font color="red">*</font>
											 		</div>
											 		<div class="tbox">									 			
											 			 <s:textarea rows="2" name="physicalAddress" id="physicalAddress" cssClass="form-control" cssStyle="width: 100%;" />						 		
											 		</div>
											 		<br class="clear"/>
										 		</div>
										 		<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
													<div class="text">
											 			<s:text name="label.regNo"  /><font color="red">*</font>
											 		</div>
											 		<div class="tbox">									 			
											 			 <s:textfield name="registerNumber" id="registerNumber" cssClass="form-control" />						 		
											 		</div>
										 		</div>
										 		<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
													<div class="text">
											 			<s:text name="label.make"  /><font color="red">*</font>
											 		</div>
											 		<div class="tbox">									 			
											 			 <s:textfield name="make" id="make" cssClass="form-control" />						 		
											 		</div>
										 		</div>
										 		<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
													<div class="text">
											 			<s:text name="label.nameofInsurer"  /><font color="red">*</font>
											 		</div>
											 		<div class="tbox">									 			
											 			 <s:textfield name="nameOfInsurer" id="nameOfInsurer" cssClass="form-control" />						 		
											 		</div>
										 		</div>
										 	</div>
										 	<br class="clear" />
										 </div>
									</div>
								</s:if>
							</s:if>
							<s:if test='"claim"==requireType'>
									<div class="panel panel-primary">
										<div class="panel-body">
											<div class="row">
												<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
													<input type="checkbox" name="declaration" id="declaration" /> &nbsp;
													<h4><s:text name="label.declartion" /></h4>
													<hr>
												</div>
											</div>
										</div>
									</div>
						            <s:if test="#session.product_id == '30'">
							            <div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
											<div class="text">
									 			<s:text name="label.lossStatus"  /><font color="red">*</font>
									 		</div>
									 		<div class="tbox">									 			
									 			 <s:radio list="#{'Y':'Yes','N':'No'}" name="lossStatus" id="lossStatus" value='%{lossStatus==null?"N":lossStatus}'  />							 		
									 		</div>
								 		</div>
							            <div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
											<div class="text">
									 			<s:text name="label.lossDescriptionn"  /><font color="red">*</font>
									 		</div>
									 		<div class="tbox">									 			
									 			 <s:textarea rows="2" name="lossDescription" id="lossDescription" cssClass="form-control" cssStyle="width: 100%;" />
									 		</div>
									 		<br class="clear"/>
								 		</div>
								 	</s:if>	
								</s:if>
								<s:elseif test='"endorsement"==requireType'>
									<div class="panel panel-primary">
										<div class="panel-heading">
											<h4><s:text name="label.endorement" /></h4>
										</div>
										<div class="panel-body">
											<div class="row">
												<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
													<div class="text">
														<s:text name="lable.dropdown.endorsement" /><font color="red">*</font>
											 		</div>
											 		<div class="dropdown">									 			
														<s:select name="endTypeId" id="endTypeId" list="endorsementDropdown" headerKey="" headerValue="---Select---" listKey="ENDT_TYPE_ID"  listValue="ENDT_TYPE" cssClass="form-control" disabled="#disable" />								 		
													</div>
										 		</div>
										 		<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
													<div class="text">
											 			<s:text name="label.agent.remarks"  /><font color="red">*</font>
											 		</div>
											 		<div class="tbox">									 			
											 			 <s:textarea rows="2" name="agentRemarks" id="agentRemarks" cssClass="form-control" cssStyle="width: 100%;" disabled="#disable"/>	
											 		</div>
											 		<br class="clear"/>
										 		</div>
										 		</div>
										 </div>
									</div>	
								</s:elseif>
						</div>
					</div>
				</div>
			</div>
		</div>
	</s:if>
	<div class="row">
		<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" align="center">
		    <s:if test='!"Edit".equalsIgnoreCase(mode) && !"Update".equalsIgnoreCase(mode) && "Add".equalsIgnoreCase(mode)|| "Insert".equalsIgnoreCase(mode)'>
				<input type="button" class="btn btn-sm btn-success" value="Submit" onclick="claimSubmit('Insert','<s:property value="requireType"/>');" /> &nbsp;&nbsp;&nbsp;
			</s:if>
			<s:if test='"Edit".equalsIgnoreCase(mode) || "Update".equalsIgnoreCase(mode)'>
				<s:if test='"Y".equalsIgnoreCase(updateStatus)'>
					<input type="button" class="btn btn-sm btn-warning" value="Update" onclick="claimSubmit('Update','<s:property value="requireType"/>');" /> &nbsp;&nbsp;&nbsp;			
				</s:if>
			</s:if>
		<s:if test="requireType !='endorsement'">
			<s:if test="#session.product_id == '65' && mode != 'individual' && mode != 'Edit' && mode != 'Update' ">
				<input type="button" class="btn btn-sm btn-danger" value="Back" onclick="addNew('individual','<s:property value="policyNo"/>','<s:property value="vehicleId"/>')"/>
			</s:if>
			<s:if test="#session.product_id == '65' && mode != 'individual' && mode != 'Edit' && mode == 'Update' ">
				<input type="button" class="btn btn-sm btn-danger" value="Back" onclick="claimlList('','<s:property value="policyNo"/>','<s:property value="vehicleId"/>','<s:property value="claimNo"/>');" />
			</s:if>
			<s:if test="#session.product_id == '65' && mode == 'individual' ">
				<s:submit type="button" cssClass="btn btn-sm btn-danger" value="Back" onclick="motorVehicleDetails('%{policyNo}')"/>
			</s:if>
			<s:if test="#session.product_id == '30' && mode != 'individual' && mode != 'Edit' && mode != 'Update' ">
				<input type="button" class="btn btn-sm btn-danger" value="Back" onclick="addNew('individual','<s:property value="policyNo"/>','')"/>
			</s:if>
			<s:if test="#session.product_id == '30' && mode != 'individual' && mode != 'Edit' && mode == 'Update' ">
				<input type="button" class="btn btn-sm btn-danger" value="Back" onclick="claimlList('','<s:property value="policyNo"/>','','<s:property value="claimNo"/>');" />
			</s:if>
			<s:if test="#session.product_id == '30' && mode == 'individual' ">
				<s:submit type="button" cssClass="btn btn-sm btn-danger" value="Back" onclick="menuBack('%{policyNo}','homePage')"/>
			</s:if>
		</s:if>
		<s:else>
			<s:if test="mode != 'newReq'">
				<s:submit type="button" cssClass="btn btn-sm btn-danger" value="Back" onclick="endtBack('%{policyNo}','newReq')"/>
			</s:if>
			<s:else>
				<s:submit type="button" cssClass="btn btn-sm btn-danger" value="Back" onclick="menuBack('%{policyNo}','homePage')"/>
			</s:else>
		</s:else>
		</div>
	</div>
</s:else>
</div>
</div>
</div>
</div>
</div>
<s:if test="#session.product_id == '65'">
<s:hidden name="policyType" value="%{selectpolicyType}"/>
</s:if>
<s:hidden name="claimNo"/>
<s:hidden name="reqFrom"/>
<s:hidden name="vehicleId"/>
<s:hidden name="menuType"/>
<s:hidden name="policyNo"/>
<s:hidden name="applicationNo"/>
<s:hidden name="quoteNo"/>
<s:hidden name="loginId"/>
<s:hidden name="mode" id="mode"/>
<s:hidden name="requireType" id="requireType"/>
<s:hidden name="productId" id="productId"/>
<s:hidden name="refNo" id="refNo"></s:hidden>
<s:if test="mode == 'newReq'">
<s:hidden name="endTypeId" id="endTypeId"/>
<s:hidden name="agentRemarks" id="agentRemarks"/>
</s:if>
</s:form>
<script type="text/javascript">
	function claimSubmit(mode,requireType) {
		if(requireType=='claim'){
			document.forms['report'].mode.value=mode;
			document.report.action = 'intimateInsertClaim.action';
		}else if(requireType =='endorsement'){
			document.report.action = 'endorsementInsertClaim.action';
		}
		document.report.submit();
	}
	$(function() {
		try {
			$('#driverDob').datepicker({
				todayHighlight: true,
				format: "dd/mm/yyyy"
			}).on('changeDate', function(e){
				$(this).datepicker('hide');
			});
			$('#dateObtained').datepicker({
				todayHighlight: true,
				format: "dd/mm/yyyy"
			}).on('changeDate', function(e){
				$(this).datepicker('hide');
			});
			$('#lossDate').datepicker({
				todayHighlight: true,
				format: "dd/mm/yyyy"
			}).on('changeDate', function(e){
				$(this).datepicker('hide');
			});
			$('#lossDateReported').datepicker({
				todayHighlight: true,
				format: "dd/mm/yyyy"
			}).on('changeDate', function(e){
				$(this).datepicker('hide');
			});
			/*$('#lossTimeReported').datetimepicker();*/
		} catch(err){
			console.error(err);
		}
	});
	function enable(value, id) { 
	if('No'==value || 'N'==value){
		document.getElementById(id).readOnly=false;
	} else {
		document.getElementById(id).readOnly=true;
	}
 	return false;
}
function enable1(value, id1,id2) { 
	if('yes'==value || 'Y'==value){
		document.getElementById(id1).readOnly=false;
		document.getElementById(id2).readOnly=false;
	} else {
		document.getElementById(id1).readOnly=true;
		document.getElementById(id2).readOnly=true;
	}
 	return false;
}
function enable2(value, id1,id2,id3) { 
	if('yes'==value || 'Y'==value){
		document.getElementById(id1).readOnly=false;
		document.getElementById(id2).readOnly=false;
		document.getElementById(id3).readOnly=false;
	} else {
		document.getElementById(id1).readOnly=true;
		document.getElementById(id3).readOnly=true;
		document.getElementById(id3).readOnly=true;
	}
 	return false;
}
function claimHomeBack(policyNo) {
	document.forms['report'].menuType.value = 'P';
	document.forms['report'].policyNo.value = policyNo;
	document.forms['report'].action = "${pageContext.request.contextPath}/initReport.action";
	document.forms['report'].submit();
}
function funSubmit(mode,policyNo,vehicleId,claimNo,id){
	document.forms['report'].mode.value = mode;
	document.forms['report'].policyNo.value = policyNo;
	document.forms['report'].vehicleId.value = vehicleId;
	document.forms['report'].claimNo.value = claimNo;
	if(mode=='calimReportList'){
		document.forms['report'].action = 'intimateClaim.action';
	}else if(mode=='viewClaim'){
		document.forms['report'].action = 'intimateClaim.action';
	}else if(mode=='endorsementList'){
		document.forms['report'].action = 'endorsementClaim.action';
	}else if(mode=='viewEndorsement'){
		document.forms['report'].refNo.value = id;
		document.forms['report'].action = '${pageContext.request.contextPath}/endorsementClaim.action';
	}
	document.forms['report'].submit();
}

function addNew(mode,policyNo,vehicleId){
    document.forms['report'].mode.value = mode;
	document.forms['report'].policyNo.value = policyNo;
	document.forms['report'].vehicleId.value = vehicleId;
	document.forms['report'].refNo.value = '';
	<s:if test="requireType =='endorsement'">
		document.forms['report'].endTypeId.value = '';
		document.forms['report'].agentRemarks.value = '';
	</s:if>
	document.forms['report'].action = "${pageContext.request.contextPath}/intimateClaim.action";
	document.forms['report'].submit();
}

function claimlList(mode,policyNo,vehicleId,claimNo){
    document.forms['report'].mode.value = mode;
	document.forms['report'].policyNo.value = policyNo;
	document.forms['report'].vehicleId.value = vehicleId;
	document.forms['report'].claimNo.value = claimNo;
	document.forms['report'].action = "${pageContext.request.contextPath}/intimateClaim.action";
	document.forms['report'].submit();
}
function endtBack(policyNo,mode) {
	document.forms['report'].policyNo.value = policyNo;
	 document.forms['report'].mode.value = mode;
	document.forms['report'].action = "${pageContext.request.contextPath}/endorsementClaim.action";
	document.forms['report'].submit();
}
function menuBack(policyNo,mode) {
	document.forms['report'].policyNo.value = policyNo;
	 document.forms['report'].mode.value = mode;
	//document.forms['report'].action = "${pageContext.request.contextPath}/endorsementInsertClaim.action";
	document.forms['report'].action = "${pageContext.request.contextPath}/initReport.action";
	document.forms['report'].submit();
}

function updateEndt(mode,policyNo,refNo,endtId,remarks){
    document.forms['report'].mode.value = mode;
	document.forms['report'].policyNo.value = policyNo;
	document.forms['report'].refNo.value = refNo;
	document.forms['report'].endTypeId.value = endtId;
	document.forms['report'].agentRemarks.value = remarks;
	document.forms['report'].action = "${pageContext.request.contextPath}/intimateClaim.action";
	document.forms['report'].submit();
}

</script>
</body>
</html>