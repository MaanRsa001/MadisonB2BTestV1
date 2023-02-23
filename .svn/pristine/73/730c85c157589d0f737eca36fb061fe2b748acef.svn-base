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
					var data = $('#gridTableCoverage').dataTable( {
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
			.tableColumnWidth {
				min-width: 150px;
				max-width: 150px;
				width: 150px; 
			}
		</style>	
	</head>
	<body>
	<s:form id="subConfigDtl" name="subConfigDtl" action="" theme="simple">
		<div class="container-fluid">
			<div class="row">
				<div class="col-xs-12">
					<div class="row">
						<s:if test="hasActionErrors()">
							<font color="red"><s:actionerror cssStyle="list-style: none;"/></font>
						</s:if>
						<font color="green"><s:actionmessage cssStyle="list-style:none;"/> </font>
					</div>
						<div class="panel panel-primary">
							<div class="panel-heading">
								<s:if test='mode == "edit" || mode == "add"'>
									<s:text name="Edit Sub Coverage Details"/>
								</s:if>
								<s:elseif test='mode == "config" || mode=="addMore" || configType == "config"'>
									<s:text name="Edit Configuration Details"/>
								</s:elseif>
							</div>
							<div class="panel-body">
								<div class="row">
									<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
										<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
											<label><s:text name="Scheme Name"/></label> 
											<div align="left"> <s:property value="schemeLabelName"/></div>
										</div>
										<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
    										<label><s:text name="Content Type"/></label>
											<div align="left"> <s:property value="contentLabelName"/></div>
										</div>
										<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
    										<label><s:text name="Coverage Name"/></label>
											<div align="left"> <s:property value="coverageLabelName"/></div>
										</div>
										<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
    										<label><s:text name="Sub Coverage Name"/></label>
											<div align="left"> <s:property value="subCoverageLabelName"/></div>
										</div>
									</div>
								</div>
								<br/><br/>
								<s:if test='mode == "config" || mode=="addMore" || configType == "config"'>
									<div class="row">
										<div class="col-xs-12" style="overflow:scroll;width:100%;overflow:auto">
											<table class="footable" width="100%">
												<thead>
													<tr>
														<th class="tableColumnWidth">Display Name</th>
														<th class="tableColumnWidth">Display Type</th>
														<th class="tableColumnWidth"> Display Order</th>
														<th class="tableColumnWidth"> Dropdown Table</th>
												   <%-- <th class="tableColumnWidth"> Dropdown Key</th>
														<th class="tableColumnWidth"> Dropdown Value</th> --%>
														<th class="tableColumnWidth"> Validation Type</th>
														<th class="tableColumnWidth"> Destination Table</th>
														<th class="tableColumnWidth"> Destination Column</th>
														<th class="tableColumnWidth"> Mandatory</th>
														<th class="tableColumnWidth"> Total SumInsured</th>
														<th class="tableColumnWidth"> Status</th>
														
													</tr>
												</thead>
												<tbody>
													<s:iterator begin="1" end="add" var="list" status="stat">
														<tr>
															<td>
																<s:textfield theme="simple" name="displayNamesConfig[%{#stat.count-1}]" id="displayNamesConfig[%{#stat.count-1}]" disabled="#disable1" maxlength="100" size="8" cssClass="inputBox tooltipContent" />
															</td>
															<td>
																<s:select name="sumControlTypeConfig[%{#stat.count-1}]" id="sumControlTypeConfig[%{#stat.count-1}]" cssClass="form-control inputBoxS" headerKey="" headerValue="None" list="#{'Display':'Display', 'textbox':'textbox', 'Radio':'Radio', 'dropdown':'dropdown'}"></s:select>
															</td>
															<td>
																<s:select name="displayOrderConfig[%{#stat.count-1}]" id="displayOrderConfig[%{#stat.count-1}]" cssClass="form-control inputBoxS" headerKey="" headerValue="None" list="displayOrderList" listKey="SUB_DISPLAY_ORDER" listValue="SUB_DISPLAY_ORDER" ></s:select>
															</td>
															<td>
																<s:textfield theme="simple" name="dropdownTableConfig[%{#stat.count-1}]" id="dropdownTableConfig[%{#stat.count-1}]" disabled="#disable1" maxlength="20" size="10" cssClass="inputBox tooltipContent" />
															</td>
															<td>
																<s:select name="validationTypeConfig[%{#stat.count-1}]" id="validationTypeConfig[%{#stat.count-1}]" cssClass="form-control inputBoxS" headerKey="" headerValue="None" list="#{'String':'String', 'Number':'Number', 'Date':'Date'}"></s:select>
															</td>
															<td>
																<s:textfield theme="simple" name="destTableConfig[%{#stat.count-1}]" id="destTableConfig[%{#stat.count-1}]" value="OFS_TRANSACTION_DETAILS" disabled="#disable1" maxlength="10" size="10" cssClass="inputBox tooltipContent" readonly="true"/>
															</td>
															<td>
																<s:select name="destColumnConfig[%{#stat.count-1}]" id="destColumnConfig[%{#stat.count-1}]" cssClass="form-control inputBoxS" headerKey="" headerValue="None" list="columnNames" listKey="COLUMN_NAME" listValue="COLUMN_NAME" ></s:select>
															</td>
															<td>
																<s:radio list="#{'Y':'Yes','N':'No'}" name="mandatoryConfig[%{#stat.count-1}]" id="mandatoryConfig[%{#stat.count-1}]" />
															</td>
															<td>
																<s:radio list="#{'Y':'Yes','N':'No'}" name="totalSumInsuredConfig[%{#stat.count-1}]" id="totalSumInsuredConfig[%{#stat.count-1}]" />
															</td>
															<td>
																<s:radio list="#{'Y':'Active','N':'Deactive'}" name="statusConfig[%{#stat.count-1}]" id="statusConfig[%{#stat.count-1}]" />
															</td>
														</tr>
													</s:iterator>
												</tbody>
											</table>
										</div>
									</div><br/>
									<div class="col-xs-12 col-sm-12 col-md-12" align="center">
										<button type="button" class="btn btn-sm btn-danger" value="Back" onclick="funBack();">Back</button>
										<button type="button" class="btn btn-sm btn-primary" value="Submit" onclick="addMore('addMore')">Add More</button>
										<button type="button" class="btn btn-sm btn-success" value="Submit" onclick="funSubmit('add');">Submit</button>
									</div>
								</s:if>
								<s:elseif test='mode == "edit" || mode == "add"'>
									<div class="panel panel-primary">
										<div class="panel-body">
											<div class="row">
												<div class="col-xs-12 col-sm-6 col-md-4 col-lg-4">
													<div class="form-group">
				    									<label><s:text name="Display Order" /><font color="red">*</font></label>
				    									<s:select name="displayOrder" id="displayOrder" cssClass="form-control inputBoxS" list="displayOrderList" listKey="SUB_DISPLAY_ORDER" listValue="SUB_DISPLAY_ORDER" ></s:select>
													</div>
												</div>
												
												<div class="col-xs-12 col-sm-6 col-md-4 col-lg-4">
													<div class="form-group">
				    									<label><s:text name="Control Type" /><font color="red">*</font></label>
				    									<s:select name="controlType" id="controlType" cssClass="form-control inputBoxS" headerKey="" headerValue="None" list="#{'Display':'Display', 'textbox':'textbox', 'Radio':'Radio', 'dropdown':'dropdown'}"></s:select>
													</div>
												</div>
												<div class="col-xs-12 col-sm-6 col-md-4 col-lg-4">
													<div class="form-group">
				    									<label><s:text name="Sub Coverage Limit" /></label>
				    									<s:textfield name="coverageLimit" cssClass="form-control"></s:textfield>
													</div>
												</div>
												<div class="col-xs-12 col-sm-6 col-md-4 col-lg-4">
													<div class="form-group">
				    									<label><s:text name="Sub Coverage Limit(Value)" /></label>
				    									<s:textfield name="subCoverageLimit" cssClass="form-control"></s:textfield>
													</div>
												</div>
												<div class="col-xs-12 col-sm-6 col-md-4 col-lg-4">
													<div class="form-group">
				    									<label><s:text name="Calculation Type" /><font color="red">*</font></label><br/>
														<s:radio list="#{'A':'Amount','M':'Mile ','P':'Percentage','G':'Grid'}" name="calculationType" id="calculationType" value='%{calculationType==null?"A":calculationType}' />
													</div>
												</div>
												<div class="col-xs-12 col-sm-6 col-md-4 col-lg-4">
													<div class="form-group">
				    									<label><s:text name="Sub Rate" /><font color="red">*</font></label>
				    									<s:textfield name="subRate" cssClass="form-control"></s:textfield>
													</div>
												</div>
												<div class="col-xs-12 col-sm-6 col-md-4 col-lg-4">
													<div class="form-group">
				    									<label><s:text name="Effective Date" /><font color="red">*</font></label>
				    									<s:textfield name="effectiveDate" id="effectiveDate" cssClass="form-control datepicker"></s:textfield>
													</div>
												</div>
												<div class="col-xs-12 col-sm-6 col-md-4 col-lg-4">
													<div class="form-group">
				    									<label><s:text name="label.status" /><font color="red">*</font></label><br/>
														<s:radio list="#{'Y':'Active','N':'Deactive'}" name="status" id="status" value='%{status==null?"N":status}' />
													</div>
												</div>
												<div class="col-xs-12 col-sm-6 col-md-4 col-lg-4">
													<div class="form-group">
				    									<label><s:text name="Core Code" /><font color="red">*</font></label>
				    									<s:textfield name="rsaCode" cssClass="form-control"></s:textfield>
													</div>
												</div>
											</div>
										</div>
									</div>
									<div class="col-xs-12 col-sm-12 col-md-12" align="center">
										<button type="button" class="btn btn-sm btn-danger" value="Back" onclick="funBack();">Back</button>
										<button type="button" class="btn btn-sm btn-success" value="Submit" onclick="funSubmitCoverage('add');">Submit</button>
									</div>
								</s:elseif>
							</div>
						</div>
					<s:hidden name="contentId"></s:hidden>
				</div>
			</div>		
		</div>
		<s:hidden name="mode"></s:hidden>
		<s:hidden name="schemeId"></s:hidden>
		<s:hidden name="coverageId"></s:hidden>
		<s:hidden name="schemeLabelName"></s:hidden>
		<s:hidden name="coverageLabelName"></s:hidden>
		<s:hidden name="contentLabelName"></s:hidden>
		<s:hidden name="subCoverageId"></s:hidden>
		<s:hidden name="subCoverageLabelName"></s:hidden>
		<s:hidden name="add"></s:hidden>
	</s:form>
<script type="text/javascript" src="<%=request.getContextPath()%>/bootstrap/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript">

function funBack(){
	document.subConfigDtl.mode.value="add";
	document.subConfigDtl.action="subCoverageDtlOfsMaster.action";
	document.subConfigDtl.submit();
} 
function funSubmitCoverage(mode){
	document.subConfigDtl.mode.value=mode;
	document.subConfigDtl.action="updSubCoverageDtlOfsMaster.action";
	document.subConfigDtl.submit();
}
function addMore(mode) {
	document.subConfigDtl.mode.value=mode;
	document.subConfigDtl.action="editSubConfigOfsMaster.action";
	document.subConfigDtl.submit();
}
function funSubmit(mode){
	document.subConfigDtl.mode.value=mode;
	document.subConfigDtl.action="updSubConfigOfsMaster.action";
	document.subConfigDtl.submit();
}

</script>
</body>
</html>   
