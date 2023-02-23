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
	<s:form id="scheme" name="scheme" action="" theme="simple">
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
								<s:text name="Product Detail"/>
							</div>
							<div class="panel-body">
								<div class="row">
									<div class="col-xs-12 col-sm-6 col-md-4 col-lg-4">
										<div class="form-group">
	    									<label><s:text name="Product Name"/><font color="red">*</font></label>
	    									<s:textfield name="schemeName" cssClass="form-control"></s:textfield>
										</div>
									</div>
									<div class="col-xs-12 col-sm-6 col-md-4 col-lg-4">
										<div class="form-group">
	    									<label><s:text name="Core Code" /><font color="red">*</font></label>
	    									<s:textfield name="schemeCoreCode" cssClass="form-control"></s:textfield>
										</div>
									</div>
									<div class="col-xs-12 col-sm-6 col-md-4 col-lg-4">
										<div class="form-group">
	    									<label><s:text name="label.remarks"/></label>
	    									<s:textarea name="schemeRemarks" cssClass="form-control"></s:textarea>
										</div>
									</div>
									<div class="col-xs-12 col-sm-6 col-md-4 col-lg-4">
										<div class="form-group">
											<label><s:text name="label.status" /><font color="red">*</font></label><br/>
											<s:radio list="#{'Y':'Yes','N':'No'}" name="schemeStatus" id="schemeStatus" value='%{schemeStatus==null?"N":schemeStatus}' />
										</div>
									</div>
									<div class="col-xs-12 col-sm-12 col-md-12" align="center">
										<s:submit cssClass="btn btn-sm btn-danger" value="Back" onclick="funBack();"></s:submit>
										<s:submit cssClass="btn btn-sm btn-success" value="Submit" onclick="funSubmit();"></s:submit> &nbsp;&nbsp;&nbsp;
									</div>
								</div> <br/>
							</div>
						</div>
					</s:if>
					<s:else>
						<div class="panel-body">
							<div class="panel panel-primary">
								<div class="panel-heading">
									<div class="clearfix">
										<div class="pull-left"><s:text name="Product List"/></div>
										<div class="pull-right"><s:submit cssClass="btn btn-sm btn-default" value="Add New" onclick="funEdit('add','');"></s:submit></div>
									</div>
								</div>
								<div class="panel-body">
									<div class="row">
										<table class="display responsive no-wrap" id="gridTable" width="100%" cellspacing="0">
											<thead>
											<tr>
												<th class="no-sort"><s:text name="Sno"/></th>
												<td><s:text name="Product Name"/></td>
												<td><s:text name="Core Code"/></td>
												<td><s:text name="label.status"/></td>
												<td><s:text name="label.remarks"/></td>
												<td><s:text name="Edit"/></td>
												<td><s:text name="Content"/></td>
												<!--<td><s:text name="COVERAGE"/></td>
												<td><s:text name="SUB COVERAGE"/></td>-->
												<td><s:text name="Coverages"/></td>
												<td><s:text name="Link Broker"/></td>
											</tr>
											</thead>
											<tbody>
												<s:iterator value="schemeList" var="list" status="stat">
													<tr>
														<td><s:property value="#stat.count" /></td>
														<td><s:property value="#list.SCHEME_NAME" /></td>
														<td><s:property value="#list.RSACODE" /></td>
														<td><s:property value="#list.STATUS" /></td>
														<td><s:property value="#list.REMARKS" /></td>
														<td style="text-align: center;">
															<button type="button" class="btn btn-warning btn-sm" onclick="funEdit('edit','<s:property value="#list.SCHEME_ID" />');"><i class="fa fa-pencil-square-o"></i></button>
														</td>
														<td style="text-align: center;">
															<button type="button" class="btn btn-info btn-sm" onclick="funContent('<s:property value="#list.SCHEME_ID" />');"><i class="fa fa-eye"></i></button>
														</td>
														<!--<td style="text-align: center;">
															<button type="button" class="btn btn-primary btn-sm" onclick="funCoverage('<s:property value="#list.SCHEME_ID" />','<s:property value="#list.SCHEME_NAME" />');"><i class="fa fa-eye"></i></button>
														</td>
														<td style="text-align: center;">
															<button type="button" class="btn btn-primary btn-sm" onclick="funSubCoverage('<s:property value="#list.SCHEME_ID" />','<s:property value="#list.SCHEME_NAME" />');"><i class="fa fa-eye"></i></button>
														</td>-->
														<td style="text-align: center;">
															<button type="button" class="btn btn-primary btn-sm" onclick="funFields('<s:property value="#list.SCHEME_ID" />','<s:property value="#list.SCHEME_NAME" />');"><i class="fa fa-eye"></i></button>
														</td>
														<td style="text-align: center;">
															<button type="button" class="btn btn-primary btn-sm" onclick="funLinkBroker('<s:property value="#list.SCHEME_ID" />','<s:property value="#list.SCHEME_NAME" />');"><i class="fa fa-eye"></i></button>
														</td>
													</tr>
												</s:iterator>
											</tbody>
										</table>
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
		<s:hidden name="contentId"></s:hidden>
	</s:form>
<script type="text/javascript" src="<%=request.getContextPath()%>/bootstrap/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript">

function funEdit(mode,id){
	document.scheme.mode.value=mode;
	document.scheme.schemeId.value=id;
	document.scheme.action="editSchemeOfsMaster.action";
	document.scheme.submit();
}
function funSubmit(){
	document.scheme.action="updateSchemeOfsMaster.action";
	document.scheme.submit();
}
function funBack(){
	document.scheme.action="schemeListOfsMaster.action";
	document.scheme.submit();
}
function funContent(id){
	document.scheme.schemeId.value=id;
	document.scheme.action="contentListOfsMaster.action";
	document.scheme.submit();
}
function funCoverage(id,name){
	document.scheme.mode.value="list";
	document.scheme.contentId.value="0";
	document.scheme.schemeId.value=id; 
	document.scheme.schemeLabelName.value=name;
	document.scheme.action="coverageDtlListOfsMaster.action";
	document.scheme.submit();
}
function funSubCoverage(id,name){
	document.scheme.mode.value="list";
	document.scheme.contentId.value="0";
	document.scheme.schemeId.value=id; 
	document.scheme.schemeLabelName.value=name;
	document.scheme.action="subCoverageOfsMaster.action";
	document.scheme.submit();
}
function funFields(id,name){
	document.scheme.mode.value="list";
	document.scheme.contentId.value="0";
	document.scheme.schemeId.value=id; 
	document.scheme.schemeLabelName.value=name;
	document.scheme.action="fieldDetailOfsMaster.action";
	document.scheme.submit();
}
function funLinkBroker(id,name){
	document.scheme.mode.value="list";
	document.scheme.contentId.value="0";
	document.scheme.schemeId.value=id; 
	document.scheme.schemeLabelName.value=name;
	document.scheme.action="linkedBrokerOfsMaster.action";
	document.scheme.submit();
}
</script>
</body>
</html>   
