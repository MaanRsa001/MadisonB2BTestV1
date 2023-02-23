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
		<script type="text/javascript" src="js/common.js"></script>
		<script type="text/javascript" src="js/tcal.js"></script>
		<script type="text/javascript" src="pages/admin/ratingEngine/menu.js"> </script>
		<link href="css/styles.css" rel="stylesheet" type="text/css" />
		<link rel="stylesheet" type="text/css"href="<%=request.getContextPath()%>/cssbootstrap/bootstrap.min.css" />
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/dataTables/css/dataTables.responsive.css">
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/dataTables/css/dataTables.jqueryui.css">
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/dataTables/css/dataTables.tableTools.css">
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/dataTables/css/jquery-start-ui.css"/>
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
		<s:form id="QuickRenewal" name="QuickRenewal" action="" theme="simple">
			<div class="container-fluid">
				<div class="row">
					<div class="col-xs-12">
						<div class="panel panel-primary">
							<div class="panel-heading">
								<s:if test='"accept".equalsIgnoreCase(renewalStatus)'><s:text name="Accepted Status" /></s:if>
								<s:if test='"cancel".equalsIgnoreCase(renewalStatus)'><s:text name="Rejected Status" /></s:if>
<!--								<s:if test='"P".equalsIgnoreCase(cStatus)'><s:text name="label.claim.pending" /></s:if>-->
							</div>
							<div class="panel-body">
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
												<th><s:text name="Response Decision" /></th>
												<th><s:text name="Response Message" /></th>
												<th><s:text name="Bill Ref Num" /></th>
												<th><s:text name="Reason Code" /></th>
												<th><s:text name="Request Time" /></th>
												<th><s:text name="Merchant Ref No" /></th>
												<th><s:text name="Payment Type" /></th>
												<th><s:text name="Premium" /></th>
												<th><s:text name="Currency Type" /></th>
												<th><s:text name="Customer E-Mail ID" /></th>
												<th><s:text name="Mobile No" /></th>
												<th><s:text name="Branch Code" /></th>
											</tr>
										</thead>
<!--										QUOTE_NO,PRODUCT_ID,PAYMENT_TYPE,PREMIUM,REQUEST_TIME,MERCHANT_REFERENCE,-->
<!--										CUSTOMER_EMAIL,CUSTOMER_NAME,BRANCH_CODE,BILL_TO_FORENAME,BILL_TO_EMAIL,MOBILE_NO,CURRENCY_TYPE-->
										<tbody> 
											<s:iterator value="adminRenewalList" var="list" status="stat">
												<tr>
													<td><s:property value="#stat.count" /></td>
													<td><s:property value="#list.QUOTE_NO" /></td>
													<td><s:property value="#list.POLICY_NO" /></td>
													<td><s:property value="#list.CUSTOMER_NAME" /></td>
													<td><s:property value="#list.RISK_ID" /></td>
													<td><s:property value="#list.RES_DECISION" /></td>
													<td><s:property value="#list.RES_MESSAGE" /></td>
													<td><s:property value="#list.RES_BILL_TRANS_REF_NO" /></td>
													<td><s:property value="#list.RES_REASON_CODE" /></td>
													<td><s:property value="#list.REQUEST_TIME" /></td>
													<td><s:property value="#list.MERCHANT_REFERENCE" /></td>
													<td><s:property value="#list.PAYMENT_TYPE" /></td>
													<td><s:property value="#list.PREMIUM" /></td>
													<td><s:property value="#list.CURRENCY_TYPE" /></td>
													<td><s:property value="#list.CUSTOMER_EMAIL" /></td>
													<td><s:property value="#list.MOBILE_NO" /></td>
													<td><s:property value="#list.BRANCH_CODE" /></td>
												</tr>
											</s:iterator>
										</tbody>
									</table>
								</div>
							</div>
<!--							<div class="col-xs-12 col-sm-12 col-md-12" align="center">-->
<!--								<input type="button" class="btn btn-sm btn-danger" readOnly="true" value="Back" onclick="funBack();" /> &nbsp;&nbsp;&nbsp;-->
<!--							</div>-->
						</div>
					</div>
				</div>
			</div>
			<s:hidden name="renewalStatus"></s:hidden>
		</s:form>
		<script type="text/javascript" src="<%=request.getContextPath()%>/bootstrap/js/jquery-1.9.1.min.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/bootstrap/js/bootstrap.min.js"></script>
		<script type="text/javascript">
				function funSubmit(mode,claimref,policyno){
					action='editClaimAdminClm.action?claimRefNo='+claimref+'&policyNo='+policyno;
					document.QuickRenewal.action=action;
					document.QuickRenewal.submit();
					}
				//function funBack(){
			//		document.claimIntimation.action='listclaimClaimIntimation.action';
			//		document.claimIntimation.submit();
			//	}
		</script>
	</body>
</html>