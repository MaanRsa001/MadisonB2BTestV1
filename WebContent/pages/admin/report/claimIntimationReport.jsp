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
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/dataTables/css/dataTables.responsive.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/dataTables/css/dataTables.jqueryui.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/dataTables/css/dataTables.tableTools.css">
	<link href="<%=request.getContextPath()%>/dataTables/css/jquery-start-ui.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="<%=request.getContextPath()%>/dataTables/js/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/dataTables/js/jquery.dataTables.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/dataTables/js/dataTables.jqueryui.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/dataTables/js/dataTables.responsive.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/dataTables/js/dataTables.tableTools.js"></script>
	<script type="text/javascript">
		jQuery(function ($) {
			try {
				var data = $('.display').dataTable( {
					"lengthMenu": [[10, 25, 50, -1], [10, 25, 50, "All"]],
					"order": [[ 0, "asc" ]],
					"columnDefs": [ {
			          "targets": 'no-sort',
			          "orderable": false
				    } ],
					responsive: true
				});
			} catch(err){}
		} );
	 </script>
	 <style type="text/css">
	 .tableColWidth {
	 	min-width: 100px;
	 	max-width: 200px;
	 	width: 100px;
	 }
	 </style>	
</head>
<body>
<s:form name="claimIntimation" theme="simple">
<div class="container-fluid">
	<div class="row">
		<div class="col-xs-12">
			<div class="panel panel-primary">
				<div class="panel-heading">
					<s:text name="label.claim.report"/>
				</div>				
				<div class="panel-body">
					<s:if test="hasActionErrors()">
					<div class="row">
						<font color="red"><s:actionerror cssStyle="list-style: none;"/></font>
					</div>
					<br class="clear" />
					</s:if>
					  <div class="row">
					  <font color="green"><s:actionmessage cssStyle="list-style: none;"/></font>
							<div class="col-xs-12 col-sm-12 col-md-6 col-lg-4">
								<div class="text"><s:text name="label.fromDate"/><font color="red">*</font></div>
								<div class="tbox">
									<s:textfield name="fromDate" id="fromDate" cssClass="inputBox datepicker" readonly="true" />
								</div>
							</div>
							<div class="col-xs-12 col-sm-12 col-md-6 col-lg-4">
								<div class="text"><s:text name="label.toDate" /><font color="red">*</font></div>
								<div class="tbox">
									<s:textfield name="toDate" id="toDate" cssClass="inputBox datepicker" readonly="true" />
								</div>
							</div>
							<div class="col-xs-12 col-sm-12 col-md-12col-lg-12" align="center">
								<input type="button" onclick="getClaimReport('list');" class="btn btn-sm btn-success" value="Submit" />
							</div>
					  </div>															
				</div>
				<s:if test="mode == 'list'">
				<s:if test="claimIntimateReportList!= null && claimIntimateReportList.size() > 0">
				<s:if test="productId == '65'">
				<div class="panel panel-primary">
				<div class="panel-heading">
					<s:text name="label.claim.comprehensive.and.fire.and.theft"/>
				</div> 
				        <div class="panel-body">
							<div class="row">
								<table class="display responsive no-wrap" id="gridTableMake" width="100%" cellspacing="0">
									<thead>
									<tr>
										<th class="no-sort"><s:text name="label.master.sno"/></th>
										<td><s:text name="label.policyNumber"/></td>		
										<td><s:text name="label.make"/></td>
										<td><s:text name="label.model"/></td>
										<td><s:text name="label.status"/></td>
										<td><s:text name="label.view"/></td>
									</tr>
									</thead>
									<tbody>
										<s:iterator value="claimIntimateReportList" id="claimIntimateReportList" var="list" status="stat">
											<tr>
													<td><s:property value="#stat.count"/></td>
													<td><s:property value="#list.POLICY_NO"/></td>
													<td><s:property value="#list.MAKE_NAME" /></td>
													<td><s:property value="#list.MODEL_NAME"/></td>
													<td><s:property value="#list.APPROVER_STATUS"/></td>
													<td style="text-align: center;">
													<input type="button" class="btn btn-warning btn-sm" onclick="claimSubmitComp('<s:property value="#list.POLICY_NO"/>','<s:property value="#list.VEHICLE_ID"/>','<s:property value="productId"/>');" value="view" />
												    </td>
											</tr>
										</s:iterator>
									</tbody>
								</table> 								
							</div>
						</div>   
				</div>
				</s:if>
				<s:if test="productId == '30'">
				<div class="panel-body">
							<div class="row">
								<table class="display responsive no-wrap" id="gridTableMake" width="100%" cellspacing="0">
									<thead>
									<tr>
										<th class="no-sort"><s:text name="label.master.sno"/></th>
										<td><s:text name="label.policyNumber"/></td>		
										<td><s:text name="label.customerNumber"/></td>
										<td><s:text name="label.status"/></td>
										<td><s:text name="label.view"/></td>
									</tr>
									</thead>
									<tbody>
										<s:iterator value="claimIntimateReportList" id="claimIntimateReportList" var="list" status="stat">
											<tr>
													<td><s:property value="#stat.count"/></td>
													<td><s:property value="#list.POLICY_NO"/></td>
													<td><s:property value="#list.CUSTOMER_NAME" /></td>
													<td><s:property value="#list.APPROVER_STATUS"/></td>
													<td style="text-align: center;">
													<input type="button" class="btn btn-warning btn-sm" onclick="claimSubmitComp('<s:property value="#list.POLICY_NO"/>','','<s:property value="productId"/>');" value="view" />
												    </td>
											</tr>
										</s:iterator>
									</tbody>
								</table> 								
							</div>
						</div>
				</s:if>
				</s:if>
				<s:if test="#session.product_id == '65'">
				<s:if test="claimIntimateReportTpaList!= null && claimIntimateReportTpaList.size() > 0">
				<div class="panel panel-primary">
				<div class="panel-heading">
					<s:text name="label.claim.third.party"/>
				</div>     
				        <div class="panel-body">
							<div class="row">
								<table class="display responsive no-wrap" id="gridTableMake" width="100%" cellspacing="0">
									<thead>
									<tr>
										<th class="no-sort"><s:text name="label.master.sno"/></th>
										<td><s:text name="label.policyNumber"/></td>		
										<td><s:text name="label.make"/></td>
										<td><s:text name="label.model"/></td>
										<td><s:text name="label.status"/></td>
										<td><s:text name="label.view"/></td>
									</tr>
									</thead>
									<tbody>
										<s:iterator value="claimIntimateReportTpaList" id="claimIntimateReportTpaList" var="list" status="stat">
											<tr>
													<td><s:property value="#stat.count"/></td>
													<td><s:property value="#list.POLICY_NO"/></td>
													<td><s:property value="#list.MAKE_NAME" /></td>
													<td><s:property value="#list.MODEL_NAME"/></td>
													<td><s:property value="#list.APPROVER_STATUS"/></td>
													<td style="text-align: center;">
													<input type="button" class="btn btn-warning btn-sm" onclick="claimSubmitTpa('<s:property value="#list.POLICY_NO"/>','<s:property value="#list.VEHICLE_ID"/>');" value="view" />
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
			    </s:if>
		   </div>
		</div>
	</div>
</div> 
</s:form>
</body>
<script type="text/Javascript" >

function getClaimReport(mode)  {
document.forms['claimIntimation'].action = "getIntimationMotorReportAM.action?mode="+mode;
document.forms['claimIntimation'].submit();
}

function claimSubmitComp(policy,vehicle,productId){
if(productId == '65')
{
document.forms['claimIntimation'].action = "getIntimationViewMotorReportAM.action?policyNo="+policy+"&vehicleId="+vehicle;
}
if(productId == '30')
{
document.forms['claimIntimation'].action = "getIntimationViewMotorReportAM.action?policyNo="+policy;
}
document.forms['claimIntimation'].submit();
}

function claimSubmitTpa(policy,vehicle){
document.forms['claimIntimation'].action = "getIntimationViewMotorReportAM.action?policyNo="+policy+"&vehicleId="+vehicle;
document.forms['claimIntimation'].submit();
}

</script>
</html>