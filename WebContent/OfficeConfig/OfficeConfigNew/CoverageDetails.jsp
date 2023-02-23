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
	</head>
	<body>
	<s:form id="coverageDtl" name="coverageDtl" action="" theme="simple">
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
								<s:text name="Coverage Details"/>
							</div>
							<div class="panel-body">
								<div class="row">
									<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
										<div class="col-xs-12 col-sm-6 col-md-4 col-lg-4">
											<label><s:text name="Scheme Name"/></label> 
											<div align="left"> <s:property value="schemeLabelName"/></div>
										</div>
										<div class="col-xs-12 col-sm-6 col-md-4 col-lg-4">
    										<label><s:text name="Content Type"/></label>
											<div align="left"> <s:property value="contentLabelName"/></div>
										</div>
										<div class="col-xs-12 col-sm-6 col-md-4 col-lg-4">
    										<label><s:text name="Coverage Name"/></label>
											<div align="left"> <s:property value="coverageLabelName"/></div>
										</div>
									</div>
								</div>
								<br/><br/>
								<div class="panel panel-primary">
									<div class="panel-body">
										<div class="row">
											<div class="col-xs-12 col-sm-6 col-md-4 col-lg-4">
												<div class="form-group">
			    									<label><s:text name="Coverage Type" /><font color="red">*</font></label><br/>
													<s:radio list="#{'B':'Base','O':'Optional'}" name="coverageType" id="coverageType" value='%{coverageType==null?"B":coverageType}' />
												</div>
											</div>
											<div class="col-xs-12 col-sm-6 col-md-4 col-lg-4">
												<div class="form-group">
			    									<label><s:text name="Upload Option" /><font color="red">*</font></label><br/>
													<s:radio list="#{'Y':'Yes','N':'No'}" name="uploadOption" id="uploadOption" value='%{uploadOption==null?"N":uploadOption}' />
												</div>
											</div>
											<div class="col-xs-12 col-sm-6 col-md-4 col-lg-4">
												<div class="form-group">
			    									<label><s:text name="Display Order" /><font color="red">*</font></label>
			    									<s:select name="displayOrder" id="displayOrder" cssClass="form-control inputBoxS" list="displayOrderList" listKey="DISPLAY_ORDER" listValue="DISPLAY_ORDER" ></s:select>
												</div>
											</div>
											<div class="col-xs-12 col-sm-6 col-md-4 col-lg-4">
												<div class="form-group">
			    									<label><s:text name="Coverage Display Type" /><font color="red">*</font></label>
			    									<s:select name="sumControlType" id="sumControlType" cssClass="form-control inputBoxS" headerKey="" headerValue="None" list="#{'Display':'Display', 'textbox':'textbox', 'Radio':'Radio', 'dropdown':'dropdown'}"></s:select>
												</div>
											</div>
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
			    									<label><s:text name="Option Display Type" /><font color="red">*</font></label>
			    									<s:select name="controlType" id="controlType" cssClass="form-control inputBoxS" headerKey="" headerValue="None" list="#{'Display':'Display', 'textbox':'textbox', 'Radio':'Radio', 'dropdown':'dropdown'}"></s:select>
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
			    									<label><s:text name="Base Rate" /><font color="red">*</font></label>
			    									<s:textfield name="baseRate" cssClass="form-control"></s:textfield>
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
											<div class="col-xs-12 col-sm-6 col-md-4 col-lg-4">
												<div class="form-group">
			    									<label><s:text name="Help Content"/></label>
			    									<s:textarea name="helpContent" cssClass="form-control" maxlength="1000" ></s:textarea>
												</div>
											</div>
										</div> <br/>
									</div>
								</div>
								<div class="row">
									<div class="col-xs-12 col-sm-12 col-md-12" align="center">
										<button type="button" class="btn btn-sm btn-danger" value="Back" onclick="funBack();">Back</button>
										<button type="button" class="btn btn-sm btn-success" value="Submit" onclick="funSubmitUpd('add');">Submit</button>
									</div>
								</div>
							</div>
						</div>
					<s:hidden name="contentId"></s:hidden>
					</s:if>
					<s:else>
						<div class="panel-body">
							<div class="panel panel-primary">
								<div class="panel-heading">
									<div class="clearfix">
										<div class="pull-left"><s:text name="Coverage Details"/></div>
									</div>
								</div>
								<div class="panel-body">
									<div class="row">
										<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
											<div class="col-xs-12 col-sm-6 col-md-4 col-lg-4">
												<label><s:text name="Scheme Name"/></label> 
												<div align="left"> <s:property value="schemeLabelName"/></div>
											</div>
											<div class="col-xs-12 col-sm-6 col-md-4 col-lg-4">
												<div class="form-group">
		    										<label><s:text name="Content Name"/></label>
													<s:select name="contentId" id="contentId" cssClass="form-control inputBoxS" headerKey="" headerValue="--- Select Content ---" list="contentDropDownList" listKey="CONTENT_TYPE_ID" listValue="CONTENT_DESCRIPTION" ></s:select>
												</div>
											</div>
											<div class="col-xs-12 col-sm-6 col-md-4 col-lg-4">
												<div class="form-group">
		    										<button type="button" class="btn btn-sm btn-success" value="Submit" onclick="funSubmit('list');">Submit</button>
												</div>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="panel-body">
											<div class="panel panel-primary">
												<div class="panel-heading">
													<div class="clearfix">
														<div class="pull-left"><s:text name="Coverage Included"/></div>
													</div>
												</div>
												<div class="panel-body">
													<s:iterator value="coverageIncludedList" var="list" status="stat">
														<s:if test='coverageIncludedList.size()>0'>
															<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4" style="margin-bottom: 10px;">
																<s:property value="#list.COVERAGES_NAME"/>&nbsp;<i style="font-size: 18px; cursor: pointer;" title="Edit Coverage Details" class="fa fa-edit" onclick="return funEditCoverage('edit','<s:property value="#list.COVERAGES_ID"/>','<s:property value="#list.COVERAGES_NAME"/>')"></i>
																&nbsp;
																<i style="font-size: 18px; cursor: pointer;" class="fa fa-edit" title="Edit Configuration Details" onclick="return funConfig('edit','<s:property value="#list.COVERAGES_ID"/>','<s:property value="#list.COVERAGES_NAME"/>')"></i>
																&nbsp;
																<i style="font-size: 18px; cursor: pointer;" class="fa fa-edit" title="Edit Formula Details" onclick="return funFormula('edit','<s:property value="#list.COVERAGES_ID"/>','<s:property value="#list.COVERAGES_NAME"/>')"></i>
																	
																
															</div>
														</s:if>
														<s:else>
															<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" align="center">
																<div class="label label-danger" align="center">No Coverages Included</div>
															</div>
														</s:else>
													</s:iterator>
												</div>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="panel-body">
											<div class="panel panel-primary">
												<div class="panel-heading">
													<div class="clearfix">
														<div class="pull-left"><s:text name="Coverage Excluded"/></div>
													</div>
												</div>
												<div class="panel-body">
													<s:iterator value="coverageExcludedList" var="list" status="stat">
														<s:if test='coverageExcludedList.size>0'>
															<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4" style="margin-bottom: 10px;">
																<i style="font-size: 18px; cursor: pointer;" class="fa fa-file-text" onclick="return funEditCoverage('add','<s:property value="#list.COVERAGES_ID"/>','<s:property value="#list.COVERAGES_NAME"/>')"></i>&nbsp;&nbsp;&nbsp;<s:property value="#list.COVERAGES_NAME"/>
															</div>
														</s:if>
														<s:else>
															<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" align="center">
																<div class="label label-danger" align="center">No Coverages Excluded</div>
															</div>
														</s:else>
													</s:iterator>
												</div>
											</div>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-xs-12 col-sm-12 col-md-12" align="center">
										<s:submit cssClass="btn btn-sm btn-danger" value="Back" onclick="funBackScheme();"></s:submit>
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
		<s:hidden name="coverageId"></s:hidden>
		<s:hidden name="schemeLabelName"></s:hidden>
		<s:hidden name="coverageLabelName"></s:hidden>
		<s:hidden name="contentLabelName"></s:hidden>
	</s:form>
<script type="text/javascript" src="<%=request.getContextPath()%>/bootstrap/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript">

function funEditCoverage(mode,id,name){
	document.coverageDtl.mode.value=mode;
	document.coverageDtl.coverageId.value=id;
	document.coverageDtl.coverageLabelName.value=name;
	var e = document.getElementById("contentId");
	var desc = e.options[e.selectedIndex].text;
	document.coverageDtl.contentLabelName.value=desc;
	document.coverageDtl.action="editCoverageDtlOfsMaster.action";
	document.coverageDtl.submit();
}
function funSubmit(mode){
	document.coverageDtl.mode.value=mode;
	document.coverageDtl.action="coverageDtlListOfsMaster.action";
	document.coverageDtl.submit();
}
function funBackScheme(){
	document.coverageDtl.action="schemeListOfsMaster.action";
	document.coverageDtl.submit();
}
function funBack(){
	document.coverageDtl.mode.value="list";
	document.coverageDtl.action="coverageDtlListOfsMaster.action";
	document.coverageDtl.submit();

} 
function funSubmitUpd(mode){
	document.coverageDtl.mode.value=mode;
	document.coverageDtl.action="updateCoverageDtlOfsMaster.action";
	document.coverageDtl.submit();
}
function funConfig(mode,id,name){
	document.coverageDtl.mode.value=mode;
	document.coverageDtl.coverageId.value=id;
	document.coverageDtl.coverageLabelName.value=name;
	var e = document.getElementById("contentId");
	var desc = e.options[e.selectedIndex].text;
	document.coverageDtl.contentLabelName.value=desc;
	document.coverageDtl.action="editConfigOfsMaster.action";
	document.coverageDtl.submit();
}
function funFormula(mode,id,name){
	document.coverageDtl.mode.value=mode;
	document.coverageDtl.coverageId.value=id;
	document.coverageDtl.coverageLabelName.value=name;
	var e = document.getElementById("contentId");
	var desc = e.options[e.selectedIndex].text;
	document.coverageDtl.contentLabelName.value=desc;
	document.coverageDtl.action="editFormulaOfsMaster.action";
	document.coverageDtl.submit();
}
</script>
</body>
</html>   
