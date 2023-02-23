<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
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
<style>
#loading {
  width: 100%;
  height: 100%;
  top: 0px;
  left: 0px;
  position: fixed;
  display: block;
  opacity: 0.7;
  background-color: #fff;
  z-index: 99;
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
	.textV {
		border: none;
		width: 33%;
	}
	.tboxV {
		border: none;
		width: 67%;
	}
</style>

</head>
<body>
<div id="loading" style="width:100%">
   <img id="loading-image" src="${pageContext.request.contextPath}/images/ajax-loader-bar.gif" id="loader"/>
</div>
<s:form id="report" name="report" method="post" action="" theme="simple">
<div class="row">
	<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
		<s:actionerror cssStyle="color: red;" />
		<s:actionmessage cssStyle="color: green;"/>
	</div>
</div>
<div class="row">
	<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
		<div class="panel panel-primary">
			<div class="panel-heading">
			<s:if test='mode == "showlist"'>
				<s:text name="label.endorement" />
			</s:if>
			<s:elseif test='mode == "showlistClaim"'>
				<s:text name="Claim Status" />
			</s:elseif>
				 
			</div>
			</div>
					<s:if test='mode == "showlist"'>
					<div class="row">
					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
						<div class="panel panel-primary">
								<div class="row">
									<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" style="overflow-x: scroll;">
										<table class="display responsive no-wrap" id="gridTableMake" width="100%" cellspacing="0">
										<thead>
										<tr>
											<th><s:text name="label.sno"/></th>
											<th><s:text name="label.policyNo"/></th>
											<th><s:text name="label.name"/></th>
											<th><s:text name="label.vehicletypedesc"/></th>
											<th><s:text name="label.engine.no"/></th>
											<th><s:text name="label.endt.type"/></th>					
											<th><s:text name="label.entry.date"/></th>		
											<th><s:text name="label.admin.remarks"/></th>
											<th><s:text name="label.endor.status"/></th>
											<th><s:text name="label.ref.no"/></th>
										</tr>
										</thead>
										<tbody >
											<s:iterator value="endtReqList" var="list" status="stat">
												<tr>
													<td ><s:property value="#stat.count"/></td>
													<td><s:property value="#list.POLICY_NO"/></td>
													<td><s:property value="#list.NAME"/></td>													
													<td><s:property value="#list.VEHICLETYPE_DESC" /></td>
													<td><s:property value="#list.ENGINE_NUMBER"/></td>
													<td><s:property value="#list.ENDT_TYPE" /></td>
													<td><s:property value="#list.ENTRY_DATE"/></td>
													<td><s:property value="#list.ADMIN_REMARKS" /></td>
													<td><s:property value="#list.STATUS" /></td>
													<td><s:property value="#list.REFERENCE_NO"/></td>
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
		<s:elseif test='mode == "showlistClaim"'>
			<div class="row">
					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
						<div class="panel panel-primary">
								<div class="row">
									<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" style="overflow-x: scroll;">
										<table class="display responsive no-wrap" id="gridTableMake" width="100%" cellspacing="0">
										<thead>
										<tr>
											<th><s:text name="label.sno"/></th>
											<th><s:text name="label.policyNo"/></th>
											<th><s:text name="label.name"/></th>
											<th><s:text name="Make Name"></s:text>
											<th><s:text name="Model Name"></s:text>
											<th><s:text name="label.vehicletypedesc"/></th>
											<th><s:text name="label.engine.no"/></th>
											<th><s:text name="label.endor.status"/></th>
										</tr>
										</thead>
										<tbody >
											<s:iterator value="endtReqList" var="list" status="stat">
												<tr>
													<td ><s:property value="#stat.count"/></td>
													<td><s:property value="#list.POLICY_NO"/></td>
													<td><s:property value="#list.NAME"/></td>
													<td><s:property value="#list.MAKE_NAME"/></td>
													<td><s:property value="#list.MODEL_NAME"/></td>
													<td><s:property value="#list.VEHICLETYPE_DESC" /></td>
													<td><s:property value="#list.ENGINE_NUMBER"/></td>
													<td><s:property value="#list.APPROVER_STATUS" /></td>
												</tr>
											</s:iterator>
										</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
			</div>		
		</s:elseif>
		
		</div>
	</div>
</div>
</s:form>
<script type="text/javascript">
function submitInsert() {
	document.report.action='${pageContext.request.contextPath}/endorsementInsertClaim.action';
	document.report.submit();
}
</script>	
</body>
</html>