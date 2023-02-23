<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/WEB-INF/displaytag.tld" prefix="display"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ page isELIgnored="false"%>
<html>
<head>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/dataTables/css/dataTables.responsive.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/dataTables/css/dataTables.jqueryui.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/dataTables/css/dataTables.tableTools.css">
	<link href="${pageContext.request.contextPath}/dataTables/css/jquery-start-ui.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="${pageContext.request.contextPath}/dataTables/js/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/dataTables/js/jquery.dataTables.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/dataTables/js/dataTables.jqueryui.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/dataTables/js/dataTables.responsive.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/dataTables/js/dataTables.tableTools.js"></script>
	<script type="text/javascript">
  	jQuery(function ($) {
  		try {
			var data1 = $('#record').dataTable( {
				"lengthMenu": [[10, 25, 50, -1], [10, 25, 50, "All"]],
				"order": [[ 1, "asc" ]],
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
<div class="row">
	<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
		<div class="panel panel-primary">
			<div class="panel-heading">
				<s:text name="claim.claimIntimation" />
			</div>
			<div class="panel-body">
				<div id="pendingPolicies">				
					<s:form id="pending" name="pending" theme="simple">
						<table class="display responsive no-wrap" id="record" width="100%" cellspacing="0">
							<thead>
							<tr>
								<th class="no-sort"></th>
								<th> <s:text name="S.No." /> </th>
								<th> <s:text name="Date" /> </th>
								<th> <s:text name="Number of Policies" /> </th>
							</tr>
							</thead>
							<tbody>
							<s:iterator value="claimIntimation" status="stat" var="record">
							<tr>
								<td></td>
								<td> <s:property value="%{#stat.index+1}" /> </td>
								<td style="text-align: center;">
									<a type="button" class="btn btn-sm btn-primary" href="#" onclick="fnTab('${record1.INTIMATION_DATES}','claimPending')">${record1.INTIMATION_DATES}</a>
								</td>
								<td style="text-align: center;"> <s:property value="POLICY_COUNT" /> </td>
							</tr>
							</s:iterator>
							</tbody>
						</table>
					</s:form>
				</div>
			</div>
		</div>
	</div>
</div>
</body>
<script type="text/javascript">
function fnTab(date,reqFrom) {
	if(reqFrom=="claimPending") {
		postRequest('${pageContext.request.contextPath}/claimIntimation.action?reqFrom='+reqFrom+'&rdate='+date,'pendingPolicies');
  	}
  	if(reqFrom=="View") {
		document.pending.action='${pageContext.request.contextPath}/claimIntimation.action?reqFrom='+reqFrom+'&policyNo='+date,'claimPendingPolicies';
		document.pending.submit();
	}
}
</script>
</html>