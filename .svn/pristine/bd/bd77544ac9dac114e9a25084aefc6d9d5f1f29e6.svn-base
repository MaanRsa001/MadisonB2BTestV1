<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ page import="nl.captcha.Captcha"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
				var data = $('#gridTableMake').dataTable( {
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
	</head>
	<body>
	
		<s:form id="cashRenewal" name="cashRenewal" action="" theme="simple">
			<div class="container-fluid">
				<div class="row">
					<div class="col-xs-12">
						<div class="panel panel-primary">
							<div class="panel-heading">
								<s:text name="Credit Controller Pending Renewals" />
							</div>
								<div class="panel-body">
									<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
										<div class="row">
											<font color="green"><s:actionmessage cssStyle="list-style: none;" /> </font>
											<table class="display responsive no-wrap" id="gridTableMake" width="100%" cellspacing="0">
											<thead>
												<tr>
													<th><s:text name="label.sno" /></th>
													<th><s:text name="Quote No" /></th>
													<th><s:text name="Policy No" /></th>
													<th><s:text name="Customer Name" /></th>
													<th><s:text name="Risk ID" /></th>
													<th><s:text name="Mobile No" /></th>
													<th><s:text name="Branch Code" /></th>
													<th><s:text name="Request Time" /></th>
													<th><s:text name="Merchant Ref No" /></th>
													<th><s:text name="Payment Type" /></th>
													<th><s:text name="Currency Type" /></th>
													<th><s:text name="Premium Amount" /></th>
													<th><s:text name="View" /></th>
													<th><s:text name="Customer E-Mail ID" /></th>
												</tr>
											</thead>
											<tbody> 
												<s:iterator value="cashPaymentList" var="list" status="stat">
													<tr>
														<td><s:property value="#stat.count" /></td>
														<td><s:property value="#list.QUOTE_NO" /></td>
														<td><s:property value="#list.POLICY_NO" /></td>
														<td><s:property value="#list.ASSR_NAME" /></td>
														<td><s:property value="#list.RISK_ID" /></td>
														<td><s:property value="#list.MOBILE_NO" /></td>
														<td><s:property value="#list.BRANCH_CODE" /></td>
														<td><s:property value="#list.REQUEST_TIME" /></td>
														<td><s:property value="#list.MERCHANT_REFERENCE" /></td>
														<td><s:property value="#list.PAYMENT_TYPE" /></td>
														<td><s:property value="#list.CURRENCY_TYPE" /></td>
														<td><s:property value="#list.PREMIUM" /></td>
														<td>
															<input type="button" class="btn btn-warning btn-sm" onclick="funSubmit('<s:property value="#list.QUOTE_NO" />','<s:property value="#list.POLICY_NO" />','<s:property value="#list.RISK_ID" />');" value="View" />
														</td>
														<td><s:property value="#list.EMAIL_ID" /></td>
													</tr>
												</s:iterator>
											</tbody>
										</table>
									</div>
								 </div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<s:hidden name="cStatus"></s:hidden>
		</s:form>
		<script type="text/javascript" src="<%=request.getContextPath()%>/bootstrap/js/jquery-1.9.1.min.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/bootstrap/js/bootstrap.min.js"></script>
		<script type="text/javascript">
		
				function funSubmit(merchentRefNo,policyNo,riskId){
					action='getListAdminRenewal.action?merchant_reference='+merchentRefNo+'&policyNo='+policyNo+'&riskId='+riskId;
					document.cashRenewal.action=action;
					document.cashRenewal.submit();
				}
				
		</script>
	</body>
</html>