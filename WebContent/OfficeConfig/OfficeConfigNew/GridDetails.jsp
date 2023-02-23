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
	<s:form id="configDtl" name="configDtl" action="" theme="simple">
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
								<s:text name="Grid Details"/>
							</div>
							<div class="panel-body">
								<div class="row">
									<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
										<div class="col-xs-12 col-sm-6 col-md-4 col-lg-4">
											<label><s:text name="Scheme Name"/></label> 
											<div align="left"> <s:property value="schemeLabelName"/></div>
										</div>
										<div class="col-xs-12 col-sm-6 col-md-4 col-lg-4">
											<label><s:text name="Broker Name"/></label> 
											<div align="left"> <s:property value="brokerName"/></div>
										</div>
										<div class="col-xs-12 col-sm-6 col-md-4 col-lg-4">
    										<label><s:text name="Coverage Name"/></label>
											<div align="left"> <s:property value="coverageLabelName"/></div>
										</div>
									</div>
								</div>
								<br/><br/>
								<%--<s:if test='configType == "gridDtl"'>--%>
									<div class="row">
										<div class="col-xs-12" style="overflow:scroll;width:100%;overflow:auto">
											<table class="footable" width="100%">
												<thead>
													<tr>
														<th class="tableColumnWidth">Start Sum Insured</th>
														<th class="tableColumnWidth">End Sum Insured</th>
														<th class="tableColumnWidth"> Base Rate</th>
														<th class="tableColumnWidth"> Calculation Type</th>
														<th class="tableColumnWidth"> Status</th>
														<th class="tableColumnWidth"> Effective Date</th>
														
													</tr>
												</thead>
												<tbody>
													<s:iterator begin="1" end="add" var="list" status="stat">
														<tr>
															<td>
																<s:textfield theme="simple" name="startSum[%{#stat.count-1}]" id="startSum[%{#stat.count-1}]" disabled="#disable1" maxlength="100" size="8" cssClass="inputBox tooltipContent" />
															</td>
															<td>
																<s:textfield theme="simple" name="endSum[%{#stat.count-1}]" id="endSum[%{#stat.count-1}]" disabled="#disable1" maxlength="100" size="8" cssClass="inputBox tooltipContent" />
															</td>
															<td>
																<s:textfield theme="simple" name="baseRateGrid[%{#stat.count-1}]" id="baseRateGrid[%{#stat.count-1}]" disabled="#disable1" maxlength="100" size="8" cssClass="inputBox tooltipContent" />
															</td>
															<td>
																<s:radio list="#{'A':'Amount','M':'Mile','P':'Percentage'}" name="calculationTypeGrid[%{#stat.count-1}]" id="calculationTypeGrid[%{#stat.count-1}]" />
															</td>
															<td>
																<s:radio list="#{'Y':'Active','N':'Deactive'}" name="statusGrid[%{#stat.count-1}]" id="statusGrid[%{#stat.count-1}]" />
															</td>
															<td>
																<s:textfield theme="simple" name="effectiveDateGrid[%{#stat.count-1}]" id="effectiveDateGrid[%{#stat.count-1}]" disabled="#disable1" maxlength="100" size="8" cssClass="inputBox effDatePikcer datepicker" />
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
								<%--</s:if>--%>
							</div>
						</div>
				</div>
			</div>		
		</div>
		<s:hidden name="brokerLinked"/>
		<s:hidden name="mode"></s:hidden>
		<s:hidden name="schemeId"></s:hidden>
		<s:hidden name="coverageId"></s:hidden>
		<s:hidden name="schemeLabelName"></s:hidden>
		<s:hidden name="coverageLabelName"></s:hidden>
		<s:hidden name="contentLabelName"></s:hidden>
		<s:hidden name="add"></s:hidden>
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

function addMore(mode) {
	document.configDtl.mode.value=mode;
	document.configDtl.action="gridEditOfsMaster.action";
	document.configDtl.submit();
}

function funSubmit(mode){
	document.configDtl.mode.value=mode;
	document.configDtl.action="gridUpdateOfsMaster.action";
	document.configDtl.submit();
}

function funBack(){
	document.configDtl.mode.value="edit";
	document.configDtl.action="editBrokerFieldOfsMaster.action";
	document.configDtl.submit();

} 


function ismaxlength(obj) {
	var mlength=obj.getAttribute? parseInt(obj.getAttribute("maxlength")) : ""
	//alert(mlength);
	if (obj.getAttribute && obj.value.length>mlength)
		obj.value=obj.value.substring(0,mlength)
}
$(function() {
	try {			
		$(".effDatePikcer").datepicker({
			todayHighlight: true,
        	format: "dd/mm/yyyy",
		}).on('changeDate', function(e){
            $(this).datepicker('hide');
        });
	} catch(err){
		console.error(err);
	}
});	
	

</script>
</body>
</html>   
