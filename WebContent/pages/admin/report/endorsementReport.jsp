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
	 <style type="text/css">
	 .tableColWidth {
	 	min-width: 100px;
	 	max-width: 200px;
	 	width: 100px;
	 }
	 </style>	
</head>
<body>
<s:form name="form1" theme="simple">
<div class="container-fluid">
	<div class="row">
		<div class="col-xs-12">
			<div class="panel panel-primary">
				<div class="panel-heading">
					<s:text name="label.endt.Reports"/>
				</div>				
				<div class="panel-body">
					<s:if test="hasActionErrors()">
					<div class="row">
						<font color="red"><s:actionerror cssStyle="list-style: none;"/></font>
					</div>
					<br class="clear" />
					</s:if>
			
					<s:if test="mode != 'view'">
						<div class="row">
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
							<div class="col-xs-12 col-sm-12 col-md-6 col-lg-4">								<br/>
								<input type="button" onclick="getEndorsmentReport();" class="btn btn-sm btn-success" value="Submit" />
							</div>
						</div>
					</s:if>
					<s:if test="mode == 'list'">
						<div class="row">
							<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
								<table class="display responsive no-wrap" id="gridTableMake" width="100%" cellspacing="0">
									<thead style="background: #F8F8F8;">
										<tr>
											<th class="no-sort"><s:text name="label.master.sno"/></th>
											<!--<th><s:text name="label.policyType"/></th>
											--><th><s:text name="label.policyName"/></th>					
											<th><s:text name="label.noofpolicy"/></th>		
											<th><s:text name="label.premium"/></th>
										</tr>
									</thead>
									<tbody>
										<s:iterator value="endorsementList" var="list" status="stat">
										<tr>
											<td align="center"><s:property value="#stat.count"/></td>
											<!--<td><s:property value="#list.POLICYTYPE"/></td>
											--><td><s:property value="#list.POLICY_NAME" /></td>
											<td align="center"><a type="button" class="btn btn-sm btn-primary" href="#" onclick="fnClick('<s:property value="#list.POLICYTYPE"/>');" ><s:property value="#list.NO_OF_POLICY"/> </a> </td>
											<td align="right"><s:property value="#list.PREMIUM" /></td>
										</tr>
										</s:iterator>
									</tbody>
								</table>
							</div>
						</div>
					</s:if>
					<div class="panel-body">
					<s:if test="mode == 'showlist'">
						<div class="row">
							<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" style="overflow-x: scroll;">
								<table class="display responsive no-wrap" id="gridTableMake" width="100%" cellspacing="0">
									<thead>
										<tr>
											<th><s:text name="label.master.sno"/></th>
											<th><s:text name="label.policy.no"/></th>
											<th><s:text name="label.vehicletypedesc"/></th>
											<th><s:text name="label.endt.type"/></th>					
											<th><s:text name="label.entry.date"/></th>		
											<th><s:text name="label.agent.remarks"/></th>
											<th><s:text name="label.admin.status"/></th>
											<th><s:text name="label.ref.no"/></th>
											<th><s:text name="label.view"/></th>
										</tr>
										</thead>
										<tbody >
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
													<input type="button" class="btn btn-warning btn-sm" onclick="funView('<s:property value="#list.REFERENCE_NO" />');" value="View" />
													</td>
												</tr>
											</s:iterator>
										</tbody>
								</table>
							</div>
						</div>
						<br class="clear" />
						<div class="row">
							<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" align="center">
								<center><input type="button" onclick="fnBack();" class="btn btn-sm btn-danger" value="Back" /></center>
							</div>
						</div>
					</s:if>
							<s:elseif test="mode == 'view'">
						<div class="panel panel-primary">
							<div class="panel-heading">
								<s:text name="label.endorsement.details" />
 							</div>
							<s:iterator value="endorsementListView" status="stat" var="list">
								<div class="panel-body">
									<div class="row">
										<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
											<span class="textV">	<s:text name="label.policy.no" /> </span> <span class="tboxV">&nbsp; : &nbsp; <s:property value="#list.POLICY_NO"/> </span>
										</div>
										<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
											<span class="textV">	<s:text name="label.vehicletypedesc" /> </span> <span class="tboxV">&nbsp; : &nbsp; <s:property value="#list.VEHICLETYPE_DESC"/> </span> 
										</div>
										<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
											<span class="textV">	<s:text name="label.endt.type" /> </span> <span class="tboxV">&nbsp; : &nbsp; <s:property value="#list.ENDT_TYPE"/></span>
										</div>
										<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
											<span class="textV">	<s:text name="label.entry.date" /> </span> <span class="tboxV">&nbsp; : &nbsp; <s:property value="#list.ENTRY_DATE"/> </span>
										</div>
										<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
											<span class="textV">	<s:text name="label.agent.remarks" /> </span> <span class="tboxV">&nbsp; : &nbsp; <s:property value="#list.AGENT_REMARKS"/> </span>
										</div>
										<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
											<span class="textV">	<s:text name="label.ref.no" /> </span> <span class="tboxV">&nbsp; : &nbsp; <s:property value="#list.REFERENCE_NO"/> </span>
										</div>
										<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
												<label><s:text name="label.admin.remarks" /></label><font color="red">*</font></label>
												<s:textfield name="adminRemarks" id="adminRemarks" cssClass="form-control" maxlength="150"></s:textfield>
										</div>
										<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
											<label><s:text name="label.admin.status" /><font color="red">*</font></label><br/>
											<s:radio list="#{'Y':'Accepted','N':'Rejected'}" name="status" id="status" value="#list.STATUS1"/>										</div>
									</div>
									<br class="clear"/>
									<div class="row">
										<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" align="center">
											<input type="button" onclick="getEndorsmentUpdate('<s:property value="#list.REFERENCE_NO" />');" class="btn btn-sm btn-success" value="Submit" /> &nbsp;&nbsp;&nbsp;
											<input type="button" onclick="getEndorsmentReport();" class="btn btn-sm btn-danger" value="Back" />
										</div>
									</div>
								</div>
								<s:hidden name="fromDate"  id="fromDate"/>
								<s:hidden name="toDate"  id="toDate"/>		
						    </s:iterator>
						</div>
					</s:elseif>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
</s:form>
</body>
<script type="text/Javascript" >
function getEndorsmentReport(){
	    document.form1.action='endorsementDetailsReportAM.action?mode=showlist';
	    document.form1.submit();
}
function fnBack(){
		
		document.form1.action='endorsementDetailsReportAM.action';
	    document.form1.submit();
}
function funView(val){
		document.form1.action='endorsementDetailsReportAM.action?refNo='+val+'&mode=view';
		document.form1.submit();
}
function getEndorsmentUpdate(val){
	document.form1.action='endorsementDetailsReportAM.action?refNo='+val+'&mode=update';
	    document.form1.submit();
}

</script>
</html>