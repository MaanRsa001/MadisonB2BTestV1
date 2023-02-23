<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>
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
<s:form name="form1" theme="simple">
<div class="container-fluid">
	<div class="row">
		<div class="col-xs-12">
			<div class="panel panel-primary">
				<div class="panel-heading">
					<s:text name="Road Assistant Status"/>
				</div>				
				<div class="panel-body">
				<div class="row">
					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
						<div class="panel panel-primary">
								<div class="row">
									<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" style="overflow-x: scroll;">
										<table class="display responsive no-wrap" id="gridTableMake" width="100%" cellspacing="0">
										<thead>
										<tr>
											<th><s:text name="S.No"/></th>
											<th><s:text name="label.refno"/></th>
											<th><s:text name="label.policyNo"/></th>
											<th><s:text name="motor.customerName"/></th>
											<th><s:text name="label.mobile"></s:text></th>
											<th><s:text name="motor.email"></s:text></th>
											<th><s:text name="label.assist"/></th>
											<th><s:text name="label.desc"/></th>
											<th><s:text name="label.status"></s:text></th>
											<th><s:text name="label.op.remarks"></s:text></th>
										</tr>
										</thead>
										<tbody >
											<s:iterator value="refNoList" var="list" status="stat">
												<tr>
													<td ><s:property value="#stat.count"/></td>
													<td><s:property value="#list.REF_NO"/></td>
													<td><s:property value="#list.POLICY_NO"/></td>
													<td><s:property value="#list.CUSTOMER_NAME"/></td>
													<td><s:property value="#list.MOBILE_NO"/></td>
													<td><s:property value="#list.MAIL_ID"/></td>
													<td><s:property value="#list.ASSISTANT_TYPE" /></td>
													<td><s:property value="#list.DESCRIPTION"/></td>
													<td><s:property value="#list.STATUS" /></td>
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
				</div>
			</div>
		</div>
	</div>
</div>
</s:form>
</body>
<script type="text/Javascript" >
function fnBack(){
		document.form1.action='getRoadAssistantMotor.action';
	    document.form1.submit();
}
function funSubmit(){
		document.form1.action='insRoadAssistantMotor.action';
		document.form1.submit();
}
</script>
</html>