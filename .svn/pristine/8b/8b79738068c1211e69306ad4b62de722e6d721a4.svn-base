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
					var data = $('#gridTable').dataTable( {
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
	<s:form id="broker" name="broker" action="" theme="simple">
		<div class="container-fluid">
			<div class="row">
				<div class="col-xs-12">
					<div class="row">
						<s:if test="hasActionErrors()">
							<font color="red"><s:actionerror cssStyle="list-style: none;"/></font>
						</s:if>
						<font color="green"><s:actionmessage cssStyle="list-style:none;"/> </font>
					</div>
					<s:if test='mode == "add" || mode =="edit"'>
						<div class="panel panel-primary">
							<div class="panel-heading">
								<s:text name="Field Details"/>
							</div>
							<div class="panel-body">
								<div class="row">
									<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
										<div class="col-xs-12 col-sm-6 col-md-4 col-lg-4">
											<label><s:text name="Product Name"/></label> 
											<div align="left"> <s:property value="schemeLabelName"/></div>
										</div>
										<div class="col-xs-12 col-sm-6 col-md-4 col-lg-4">
											<label><s:text name="Broker Name"/></label> 
											<div align="left"> <s:property value="brokerName"/></div>
										</div>
										<div class="col-xs-12 col-sm-6 col-md-4 col-lg-4">
											<label><s:text name="Field Name"/></label> 
											<div align="left"> <s:property value="coverageLabelName"/></div>
										</div>
										
									</div>
								</div>
								<br/><br/>
								<div class="panel panel-primary">
									<div class="panel-body">
										<!--<div class="row">
											<div class="col-xs-12 col-sm-6 col-md-4 col-lg-4">
												<div class="form-group">
			    									<label><s:text name="Broker Name" /><font color="red">*</font></label><br/>
													<s:select name="brokerLinked" id="brokerLinked" cssClass="form-control inputBoxS" list="brokerLinkedList"  listKey="AGENCY_CODE" listValue="COMPANY_NAME" headerKey="" headerValue="---select---"></s:select>
												</div>
											</div>
											<div class="col-xs-12 col-sm-6 col-md-4 col-lg-4">
												<div class="form-group">
			    									<label><s:text name="Field Name" /><font color="red">*</font></label><br/>
													<s:select name="fieldName" id="fieldName" cssClass="form-control inputBoxS" list="coverageIncludedList" listKey="COVERAGES_ID" listValue="COVERAGES_NAME" headerKey="" headerValue="---select---"></s:select>
												</div>
											</div>
										</div><br/>-->
										
										<div class="row">
											<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
												<div class="col-xs-12 col-sm-6 col-md-3 col-lg-3">
													<label><s:text name="Display Order"/></label> 
													<div align="left"> <s:property value="displayOrder"/></div>
												</div>
												<div class="col-xs-12 col-sm-6 col-md-3 col-lg-3">
													<label><s:text name="Coverage Display Type"/></label> 
													<div align="left"> <s:property value="sumControlType"/></div>
												</div>
												<div class="col-xs-12 col-sm-6 col-md-3 col-lg-3">
													<label><s:text name="Option Display Type"/></label> 
													<div align="left"> <s:property value="controlType"/></div>
												</div>
												<div class="col-xs-12 col-sm-6 col-md-3 col-lg-3">
													<label><s:text name="Upload Option"/></label> 
													<div align="left"> <s:property value="uploadOptionLabel"/></div>
												</div>
												<!--<div class="col-xs-12 col-sm-6 col-md-4 col-lg-4">
													<label><s:text name="Help Content"/></label> 
													<div align="left"> <s:property value="helpContent"/></div>
												</div>-->
											</div>
										</div><br/><br/>
										
										<div class="row">
											<div class="col-xs-12 col-sm-6 col-md-4 col-lg-4">
												<div class="form-group">
			    									<label><s:text name="Coverage Type" /><font color="red">*</font></label><br/>
													<s:radio list="#{'B':'Base','O':'Optional'}" name="coverageType" id="coverageType" value='%{coverageType==null?"B":coverageType}' />
												</div>
											</div>
											<!-- <div class="col-xs-12 col-sm-6 col-md-4 col-lg-4">
												<div class="form-group">
			    									<label><s:text name="Upload Option" /><font color="red">*</font></label><br/>
													<s:radio list="#{'Y':'Yes','N':'No'}" name="uploadOption" id="uploadOption" value='%{uploadOption==null?"N":uploadOption}' />
												</div>
											</div> -->
											
											<div class="col-xs-12 col-sm-6 col-md-4 col-lg-4">
												<div class="form-group">
			    									<label><s:text name="Sum Insured&nbsp; Coverage Limit(Calculation)" /></label>
			    									<s:textfield name="sumCoverageLimit" cssClass="form-control"></s:textfield>
												</div>
											</div>
											<div class="col-xs-12 col-sm-6 col-md-4 col-lg-4">
												<div class="form-group">
			    									<label><s:text name="Minimum Sum Insured(Calculation)" /></label>
			    									<s:textfield name="minSumLimit" cssClass="form-control"></s:textfield>
												</div>
											</div>
											
											<div class="col-xs-12 col-sm-6 col-md-4 col-lg-4">
												<div class="form-group">
			    									<label><s:text name="Coverage Limit(Display)" /></label>
			    									<s:textfield name="coverageLimit" cssClass="form-control"></s:textfield>
												</div>
											</div>
											<div class="col-xs-12 col-sm-6 col-md-4 col-lg-4">
												<div class="form-group">
			    									<label><s:text name="labe.excess" /></label>
			    									<s:textfield name="excess" cssClass="form-control"></s:textfield>
												</div>
											</div>
											<div class="col-xs-12 col-sm-6 col-md-4 col-lg-4">
												<div class="form-group">
			    									<label><s:text name="Base Rate" /><font color="red">*</font></label>
			    									<s:textfield name="baseRate" cssClass="form-control"></s:textfield>
												</div>
											</div>
											<div class="col-xs-12 col-sm-6 col-md-4 col-lg-4">
												<div class="form-group">
			    									<label><s:text name="Calculation Status" /><font color="red">*</font></label><br/>
													<s:radio list="#{'Y':'Yes','N':'No'}" name="calculationStatus" id="calculationStatus" value='%{calculationStatus==null?"N":calculationStatus}' />
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
											<!--<div class="col-xs-12 col-sm-6 col-md-4 col-lg-4">
												<div class="form-group">
			    									<label><s:text name="Core Code" /><font color="red">*</font></label>
			    									<s:textfield name="rsaCode" cssClass="form-control"></s:textfield>
												</div>
											</div>-->
											<div class="col-xs-12 col-sm-12 col-md-12" align="center">
												<s:submit cssClass="btn btn-sm btn-danger" value="Back" onclick="funBack();"></s:submit>
												<s:submit cssClass="btn btn-sm btn-success" value="Submit" onclick="funSubmit();"></s:submit> &nbsp;&nbsp;&nbsp;
											</div>
										</div> <br/>
									</div>
								</div>
							</div>
						</div>
						<s:hidden name="brokerLinked"/>
					</s:if>
					<s:else>
						<div class="panel-body">
							<div class="panel panel-primary">
								<div class="panel-heading">
									<div class="clearfix">
										<div class="pull-left"><s:text name="Field List"/></div>
										<!--<div class="pull-right"><s:submit cssClass="btn btn-sm btn-default" value="Add New" onclick="funEdit('add','','');"></s:submit></div>-->
									</div>
								</div>
								<div class="panel-body">
									<div class="row">
										<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
											<div class="col-xs-12 col-sm-6 col-md-4 col-lg-4">
												<label><s:text name="Product Name"/></label> 
												<div align="left"> <s:property value="schemeLabelName"/></div>
											</div>
											<div class="col-xs-12 col-sm-6 col-md-4 col-lg-4">
												<div class="form-group">
			    									<label><s:text name="Broker Name" /><font color="red">*</font></label><br/>
													<s:select name="brokerLinked" id="brokerLinked" cssClass="form-control inputBoxS" list="brokerLinkedList"  listKey="AGENCY_CODE" listValue="COMPANY_NAME" headerKey="" headerValue="---select---"></s:select>
												</div>
											</div>
											<div class="col-xs-12 col-sm-6 col-md-4 col-lg-4" align="center">
												<s:submit cssClass="btn btn-sm btn-success" value="Submit" onclick="funSearch();"></s:submit>
											</div>
										</div>
									</div>
									<br/>
									<div class="panel-body">
										<div class="row">
											<table class="display responsive no-wrap" id="gridTable" width="100%" cellspacing="0">
												<thead>
													<tr>
														<th class="no-sort"><s:text name="Sno"/></th>
														<td><s:text name="Field Name"/></td>
														<td><s:text name="Dispaly Order"/></td>
														<td><s:text name="Coverage Display Type"/></td>
														<td><s:text name="Option Display Type"/></td>
														<td><s:text name="Add / Edit"/></td>
													</tr>
												</thead>
												<tbody>
													<s:iterator value="brokerLinkedField" var="list" status="stat">
														<tr>
															<td><s:property value="#stat.count" /></td>
															<td><s:property value="#list.COVERAGES_NAME" /></td>
															<td><s:property value="#list.DISPLAY_ORDER" /></td>
															<td><s:property value="#list.SUM_INSURED_CONTROL_TYPE" /></td>
															<td><s:property value="#list.CONTROL_TYPE" /></td>
															<td style="text-align: center;">
																<s:if test='"N".equalsIgnoreCase(#list.STATUS)'>
																	<button type="button" class="btn btn-danger btn-sm" onclick="funEdit('add','<s:property value="#list.COVERAGES_ID"/>','<s:property value="#list.COVERAGES_NAME"/>','<s:property value="#list.DISPLAY_ORDER"/>','<s:property value="#list.SUM_INSURED_CONTROL_TYPE"/>','<s:property value="#list.CONTROL_TYPE"/>','<s:property value="#list.HELP_CONTENTS_ID"/>','<s:property value="#list.UPLOAD_OPTION_YN"/>','<s:property value="#list.UPLOAD_OPTION"/>','<s:property value="#list.RSACODE"/>');"><i class="fa fa-plus-circle"></i></button>
																</s:if>
																<s:else>
																	<button type="button" class="btn btn-success btn-sm" onclick="funEdit('edit','<s:property value="#list.COVERAGES_ID"/>','<s:property value="#list.COVERAGES_NAME"/>','<s:property value="#list.DISPLAY_ORDER"/>','<s:property value="#list.SUM_INSURED_CONTROL_TYPE"/>','<s:property value="#list.CONTROL_TYPE"/>','<s:property value="#list.HELP_CONTENTS_ID"/>','<s:property value="#list.UPLOAD_OPTION_YN"/>','<s:property value="#list.UPLOAD_OPTION"/>','<s:property value="#list.RSACODE"/>');"><i class="fa fa-pencil-square-o"></i></button>
																</s:else>
															</td>
														</tr>
													</s:iterator>
												</tbody>
											</table>
											<!--<table class="display responsive no-wrap" id="gridTable" width="100%" cellspacing="0">
												<thead>
												<tr>
													<th class="no-sort"><s:text name="Sno"/></th>
													<td><s:text name="Broker Name"/></td>
													<td><s:text name="Coverage Type"/></td>
													<td><s:text name="Upload Option"/></td>
													<td><s:text name="Minimum Sum Insured"/></td>
													<td><s:text name="Sum Insured  Coverage Limit"/></td>
													<td><s:text name="Excess"/></td>
													<td><s:text name="Edit"/></td>
												</tr>
												</thead>
												<tbody>
													<s:iterator value="coverageIncludedList" var="list" status="stat">
														<tr>
															<td><s:property value="#stat.count" /></td>
															<td><s:property value="#list.COVERAGES_NAME" /></td>
															<td><s:property value="#list.DISPLAY_ORDER" /></td>
															
															<td><s:property value="#list.COVERAGES_NAME" /></td>
															<td><s:property value="#list.DISPLAY_ORDER" /></td>
															
															
															<td><s:property value="#list.SUM_INSURED_CONTROL_TYPE" /></td>
															<td><s:property value="#list.CONTROL_TYPE" /></td>
															<td style="text-align: center;">
																<button type="button" class="btn btn-warning btn-sm" onclick="funEdit('edit','<s:property value="#list.COVERAGES_ID"/>','<s:property value="#list.COVERAGES_NAME"/>');"><i class="fa fa-pencil-square-o"></i></button>
															</td>
															
														</tr>
													</s:iterator>
												</tbody>
											</table>-->
										</div>
									</div>
									<div class="row">
										<div class="col-xs-12 col-sm-12 col-md-12" align="center">
											<s:submit cssClass="btn btn-sm btn-danger" value="Back" onclick="funBackProd();"></s:submit>
										</div>
									</div>
								</div>
							</div>		
						</div>
					</s:else>
				</div>
			</div>		
		</div>
		<s:hidden name="mode"></s:hidden>
		<s:hidden name="schemeId"></s:hidden>
		<s:hidden name="schemeLabelName"></s:hidden>
		<s:hidden name="coverageId"></s:hidden>
		<s:hidden name="coverageLabelName"></s:hidden>
		<s:hidden name="contentLabelName"></s:hidden>
		<s:hidden name="contentId"></s:hidden>
		<s:hidden name="brokerName"></s:hidden>
		<s:hidden name="displayOrder"></s:hidden>
		<s:hidden name="sumControlType"></s:hidden>
		<s:hidden name="controlType"></s:hidden>
		<s:hidden name="helpContent"></s:hidden>
		<s:hidden name="uploadOption"></s:hidden>
		<s:hidden name="uploadOptionLabel"></s:hidden>
		<s:hidden name="rsaCode"></s:hidden>
	</s:form>
<script type="text/javascript" src="<%=request.getContextPath()%>/bootstrap/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript">

function funEdit(mode,id,name,order,sict,ct,hc,uopyn,uop,ccode){
	document.broker.mode.value=mode;
	document.broker.coverageId.value=id;
	document.broker.coverageLabelName.value=name;
	document.broker.displayOrder.value=order;
	document.broker.sumControlType.value=sict;
	document.broker.controlType.value=ct;
	document.broker.helpContent.value=hc;
	document.broker.uploadOptionLabel.value=uopyn;
	document.broker.uploadOption.value=uop;
	document.broker.rsaCode.value=ccode;
	document.broker.action="editBrokerFieldOfsMaster.action";
	document.broker.submit();
}
function funBackProd(){
	document.broker.mode.value="list";
	document.broker.action="schemeListOfsMaster.action";
	document.broker.submit();
}
function funBack(){
	document.broker.mode.value="list";
	document.broker.action="linkedBrokerOfsMaster.action";
	document.broker.submit();
}
function funSearch(){
	document.broker.mode.value="list";
	var e = document.getElementById("brokerLinked");
	var desc = e.options[e.selectedIndex].text;
	document.broker.brokerName.value=desc;
	document.broker.action="linkedBrokerOfsMaster.action";
	document.broker.submit();
}
function funSubmit(){
	document.broker.action="updateFieldDtlOfsMaster.action";
	document.broker.submit();
}

</script>
</body>
</html>   
