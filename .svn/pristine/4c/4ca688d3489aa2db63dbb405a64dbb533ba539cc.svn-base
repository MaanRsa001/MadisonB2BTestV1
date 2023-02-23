<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
	
<%-- 	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/dataTables/css/dataTables.responsive.css"> --%>
<%-- 	<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/bootstrap/css/gallery.css" /> --%>
<%-- 	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/dataTables/css/dataTables.jqueryui.css"> --%>
<%-- 	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/dataTables/css/dataTables.tableTools.css"> --%>
<%-- 	<link  rel="stylesheet" type="text/css"  href="<%=request.getContextPath()%>/dataTables/css/jquery-start-ui.css"/> --%>
<%-- 	<script type="text/javascript" src="<%=request.getContextPath()%>/dataTables/js/jquery-1.11.1.min.js"></script> --%>
<%-- 	<script type="text/javascript" src="<%=request.getContextPath()%>/dataTables/js/jquery.dataTables.min.js"></script> --%>
<%-- 	<script type="text/javascript" src="<%=request.getContextPath()%>/dataTables/js/dataTables.jqueryui.js"></script> --%>
<%-- 	<script type="text/javascript" src="<%=request.getContextPath()%>/dataTables/js/dataTables.responsive.js"></script> --%>
<%-- 	<script type="text/javascript" src="<%=request.getContextPath()%>/dataTables/js/dataTables.tableTools.js"></script> --%>
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
	 <style type="text/css">
	 .tableColWidth {
	 	min-width: 100px;
	 	max-width: 750px;
	 	width: 100px;
	 	white-space: normal;
	 }
	 
	  .tableColWidth1 {
	 	min-width: 500px;
	 	max-width: 750px;
	 	width: 450px;
	 	white-space: normal;
	 }
	 </style>
	 <style type="text/css">
	 .tableColNoWrap {
	 	min-width: 100px;
	 	max-width: 750px;
	 	width: 100px;
	 	white-space: nowrap;
	 }
	 </style>	
</head>
<body>
<s:form name="form1" theme="simple">

<div class="container vehDtl">
<div class="Card_Parent ">
<div class="card card-5">
<div class="p-3">
	<div class="row">
		<div class="col-md-12">
			<div class="panel panel-primary">
				<div class="panel-heading">
					<h3><s:text name="label.road.assist"/></h3>
					<hr/>
				</div>				
				<div class="panel-body">
					<s:if test="hasActionErrors()">
					<div class="row">
						<font color="red"><s:actionerror cssStyle="list-style: none;"/></font>
					</div>
					<br class="clear" />
					</s:if>
					<s:if test="mode != 'list' && mode != 'view'">
						<div class="row">
							<div class="col-xs-12 col-sm-12 col-md-6 col-lg-4">
								<div class="text"><s:text name="label.fromDate"/><font color="red">*</font></div>
								<div class="tbox">
									<s:textfield name="fromDate" id="startDate" cssClass="form-control" readonly="true" />
								</div>
							</div>
							<div class="col-xs-12 col-sm-12 col-md-6 col-lg-4">
								<div class="text"><s:text name="label.toDate" /><font color="red">*</font></div>
								<div class="tbox">
									<s:textfield name="toDate" id="endDate" cssClass="form-control" readonly="true" />
								</div>
							</div>
							<div class="col-xs-12 col-sm-12 col-md-6 col-lg-4" align="center">
								<br/>
								<input type="button" onclick="getRoadAssist();" class="btn btn-sm btn-success" value="Submit" />
							</div>
						</div>
						</s:if>
					</div>
					<s:if test="mode == 'list'">
					<div class="panel-body">
					<div class="row">
        				<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
							<div class="">
  									<label><b><s:text name="label.fromDate"/></b>&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;</label><s:property value="fromDate"/><s:hidden name="fromDate"/>
							</div>
						</div>
						<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
							<div class="">
  									<label><b><s:text name="label.toDate"/></b>&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;</label><s:property value="toDate" /><s:hidden name="toDate"/>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
							<table class="table table-bordered table-hover" id="gridTableMake" cellspacing="0" width="100%" >
								<thead class="bluecolortable">
									<tr>
										<th class="no-sort"><s:text name="label.master.sno"/></th>
										<th><s:text name="label.ref.no"/></th>
										<th><s:text name="label.cust.name"/></th>
										<th><s:text name="label.mobile"/></th>
										<th><s:text name="label.mail"/></th>
										<th><s:text name="label.policy.no"/></th>
										<th><s:text name="label.assist.type"/></th>
										<th><s:text name="label.desc"/></th>
										<th><s:text name="label.device"/></th>
										<th><s:text name="label.req"/></th>
										<th><s:text name="label.status"/></th>
										<th><s:text name="label.remarks"/></th>
										<s:if test='%{#session.usertype==getText("ISSUER")}'>
											<th><s:text name=""/></th>
										</s:if>
									</tr>
								</thead>
								<tbody class="rowevencolor">
									<s:iterator value="roadAssistList" var="list" status="stat">
									<tr>
										<td align="center"><s:property value="#stat.count"/></td>
										<td><s:property value="#list.REF_NO" /></td>
										<td><s:property value="#list.CUSTOMER_NAME" /></td>
										<td><s:property value="#list.MOBILE_NO" /></td>
										<td><s:property value="#list.MAIL_ID" /></td>
										<td><s:property value="#list.POLICY_NO" /></td>
										<td><s:property value="#list.ASSISTANT_TYPE" /></td>
										<td><s:property value="#list.DESCRIPTION" /></td>
										<td><s:property value="#list.DEVICE_ID" /></td>
										<td><s:property value="#list.ENTRY_DATE" /></td>
										<td><s:property value="#list.STATUS" /></td>
										<td><s:property value="#list.REMARKS" /></td>
										<s:if test='%{#session.usertype==getText("ISSUER")}'>
										<td style="text-align: center;">
											<input type="button" class="btn btn-warning btn-sm" onclick="fnView('<s:property value="#list.REF_NO"/>','<s:property value="#list.DEVICE_ID" />');" value="View" />
										</td>
										</s:if>
									</tr>
									</s:iterator>
								</tbody>
							</table>
						</div>
					</div>
						<br class="clear" />
						<div class="row">
						<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" align="center">
							<input type="button" onclick="getBack();" class="btn btn-sm btn-danger" value="Back" />
						</div>
						</div>
						</div>
					</s:if>
					<s:if test="mode == 'view'">
					<s:iterator value="roadAssistList1" var="list" status="stat">
					<div class="panel-body">
							<div class="container-fluid">
									<div class="row">
									     <div class="col-md-12">
											<div class="panel panel-primary">
												<div class="panel-heading">
													<h3><s:text name="label.road.assist.detail"/></h3>
													<hr/>
													<div class="pullRight">
														<label><s:text name="label.ref.no" />&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;</label><s:property value="#list.REF_NO" /><s:hidden name="refNo"></s:hidden>
													</div>
												</div>
												<div class="panel-body">
									        		<div class="row">
									        				<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
																<div class="">
							    									<label><s:text name="label.cust.name"/>&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;</label><s:property value="#list.CUSTOMER_NAME" />
																</div>
															</div>
															<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
																<div class="">
							    									<label><s:text name="label.mobile"/>&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;</label><s:property value="#list.MOBILE_NO" />
																</div>
															</div>
															<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
																<div class="">
							    									<label><s:text name="label.mail"/>&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;</label><s:property value="#list.MAIL_ID" />
																</div>
															</div>
															<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
																<div class="">
							    									<label><s:text name="label.policy.no"/>&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;</label><s:property value="#list.POLICY_NO" />
																</div>
															</div>
															<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
																<div class="">
							    									<label><s:text name="label.assist.type"/>&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;</label><s:property value="#list.ASSISTANT_TYPE" />
																</div>
															</div>
															<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
																<div class="">
							    									<label><s:text name="label.device"/>&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;</label><s:property value="#list.DEVICE_ID" />
																</div>
															</div>
															<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
																<div class="">
							    									<label><s:text name="label.desc"/>&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;</label><s:property value="#list.DESCRIPTION" />
																</div>
															</div>
															<s:if test="'webapp'.equalsIgnoreCase(device)">
															<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
																<div class="">
							    									<label><s:text name="label.location"/>&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;</label><s:property value="#list.LOCATION" />
																</div>
															</div>
															</s:if>
															<s:else>
															<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
																<div class="">
							    									<label><s:text name="label.long"/>&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;</label><s:property value="#list.LONGITUDE" />
																</div>
															</div>
															<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
																<div class="">
							    									<label><s:text name="label.lat"/>&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;</label><s:property value="#list.LATITUDE" />
																</div>
															</div>
															</s:else>
															<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
																<div class="">
							    									<label><s:text name="label.req"/>&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;</label><s:property value="#list.ENTRY_DATE" />
																</div>
															</div>
									        			</div>
									        		</div>
									        		</div>
									        		<div class="panel panel-primary">
									        		<div class="panel-heading">
													<s:text name="label.road.assist.status"/>
													<br class="clear" />
													</div>
													<div class="panel-body">
													<div class="row">
													<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
														<div >
															<label><s:text name="label.status" /></label><font color="red">*</font>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
																<s:select list="#{'P':'PROCESSED','C':'CLOSED'}" name="status" id="status" headerKey="" headerValue="-- Select --" cssClass="form-control" cssStyle="width: 100%;"/>
														</div>
													</div>
													<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
														<div >
															<label><s:text name="label.remarks" /></label><font color="red">*</font>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
															<s:textarea name="adminRemarks" id="adminRemarks" cssClass="form-control" rows="2" />	
														</div>
													</div>
									        			<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" align="center">
															<div class="" >
								    							<input type="button" onclick="fnUpdate();" class="btn btn-sm btn-success" value="Submit" align="middle" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								    							<input type="button" onclick="getRoadAssist();" class="btn btn-sm btn-danger" value="Back" align="middle" />
															</div>
									        		    </div>
													</div>
									        	</div>
									        </div>
									     </div>
										</div>
										<s:hidden name="fromDate"/>
										<s:hidden name="toDate"/>
									</div>
								</div>
							</s:iterator>
						</s:if>
					</div>
				</div>
			</div>
		</div>
		</div>
		</div>
		</div>
</s:form>
</body>	
<script type="text/Javascript" >
function getBack(){
    document.form1.action='roadAssistReportAM.action';
    document.form1.submit();
}
function getRoadAssist(){
    document.form1.action='getRoadAssistReportAM.action?mode=list';
    document.form1.submit();
}
function fnView(refno,device){
	document.form1.action='getRoadAssistViewReportAM.action?refNo='+refno+'&device='+device+'&mode=view';
    document.form1.submit();
}
function fnUpdate(){
	document.form1.action='updRoadAssistReportAM.action';
    document.form1.submit();
}
</script>
</html>