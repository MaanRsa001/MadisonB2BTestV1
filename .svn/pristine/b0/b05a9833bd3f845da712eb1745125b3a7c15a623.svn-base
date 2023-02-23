<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<style type="text/css">
	#myDIV {
	    overflow-x:scroll;
	    margin: auto:
	}
           }
</style>
<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript" src="js/tcal.js"></script>
<link href="css/styles.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/cssbootstrap/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/bootstrap/css/footable-0.1.css"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/bootstrap/css/bootstrap-datetimepicker.css"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/dataTables/css/dataTables.responsive.css"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/dataTables/css/dataTables.jqueryui.css"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/dataTables/css/dataTables.tableTools.css"/>
<link href="<%=request.getContextPath()%>/dataTables/css/jquery-start-ui.css" rel="stylesheet" type="text/css" />
<script src="<%=request.getContextPath()%>/bootstrap/js/bootstrap-datetimepicker.js" type="text/javascript"></script>
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
				responsive: true,
				"dom": 'T<"clear">lfrtip',
				"columnDefs": [ { "type": "numeric-comma", targets: 2 } ],
		        "tableTools": {
		            "sSwfPath": "<%=request.getContextPath()%>/dataTables/swf/copy_csv_xls_pdf.swf",
		            "aButtons": [ 
					"copy",
					"print", 
					{
						"sExtends": "collection",
						"sButtonText": "Export",
						//"mColumns": "visible",
						"aButtons": [{
							"sExtends": "csv",
							"sButtonText": "CSV",
							"sFileName": "report.csv",
							"mColumns": "visible"
							}, 
							{
							"sExtends": "xls",
							"sButtonText": "Excel",
							"sFileName": "report.xls",
							"mColumns": "visible"
							},
							{
							"sExtends": "pdf",
							"sButtonText": "PDF",
							"sPdfOrientation": "landscape",
							"sFileName": "report.pdf",
							"mColumns": "visible"
						}]
					}
					]
		        }
			});
				} catch(err){}
			} );
</script>	
</head>
<body>
<s:form name="form1" theme="simple">
<div class="row">
	<div class="col-xs-12">
		<div class="panel panel-primary">
			<div class="panel-heading">
				<s:text name="label.due.report"/>
			</div>				
			<div class="panel-body">
				<s:if test="hasActionErrors()">
					<div class="row">
						<div class="col-xs-12">
							<font color="red"><s:actionerror cssStyle="list-style: none;"/></font>
						</div>						
					</div>
					<br class="clear" />
				</s:if>
				<div class="row">
			    	<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
						<div class="">
							<label><s:text name="label.startDate"/><font color="red">*</font></label><s:textfield name="startDate" id="startDate1" cssClass="inputBox datepicker" readonly="true"></s:textfield>
						</div>
					</div>
					<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
						<div class="">
							<label><s:text name="label.endDate" /><font color="red">*</font></label> <s:textfield name="endDate" id="endDate1" cssClass="inputBox datepicker" readonly="true" ></s:textfield>
						</div>
					</div>
					<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4" align="center">
					<br class="clear"/>
						<input type="button" onclick="fnSubmit();" class="btn btn-sm btn-success" value="Submit" align="left" />
					</div>
				</div>															
			</div>
		</div>
		<s:if test="mode == 'list'">					
			<div class="panel panel-primary">
				<div class="panel-heading">
					<s:text name="label.insduedetail"/>
				</div> 
			    <div class="panel-body">
					<table class="display responsive no-wrap" id="gridTable" width="100%" cellspacing="0">
						<thead align="center">
							<tr>
								<th align="center"><s:text name="label.master.sno"/></th>
								<th align="center"><s:text name="label.duecount"/></th>
								<th align="right"><s:text name="label.dueAmount"/></th>
							</tr>
						</thead>
						<tbody>
							<s:iterator value="dueCountList" var="list" status="stat">
							<tr>
								<td align="center"><s:property value="#stat.count"/></td>
								<td align="center"><input type="button" value="<s:property value="#list.COUNT"/>" class="btn btn-sm btn-success" onclick="fnView();"/></td>
								<td align="right"><s:property value="#list.DUE_AMOUNT" /></td>
							</tr>
							</s:iterator>
						</tbody>
					</table> 
				</div>   
			</div>
		</s:if>
		<s:if test="mode == 'showlist'">
			<div class="panel panel-primary">
				<div class="panel-heading">
					<s:text name="label.insduedetail"/>
				</div> 
				<div class="panel-body">
					<table class="display responsive no-wrap" id="gridTable" width="100%" cellspacing="0">
						<thead>
						<tr>
							<th><s:text name="label.master.sno"/></th>
							<th><s:text name="label.quoteno"/></th>
							<th><s:text name="label.policyNo"/></th>					
							<th><s:text name="label.customerName"/></th>		
							<th><s:text name="label.premium"/></th>
							<th><s:text name="label.dueAmount"/></th>		
							<th><s:text name="label.dueDate"/></th>
							<th><s:text name="label.currentIns"/></th>
						</tr>
						</thead>
						<tbody>
							<s:iterator value="paymentDueList" var="list" status="stat">
							<tr>
								<td><s:property value="#stat.count"/></td>
								<td><s:property value="#list.QUOTE_NO"/></td>
								<td><s:property value="#list.POLICY_No" /></td>
								<td><s:property value="#list.CUSTOMER_NAME"/></td>
								<td align="right"><s:property value="#list.OVERALL_PREMIUM"/></td>
								<td align="right"><s:property value="#list.PREMIUM_AMOUNT" /></td>
								<td><s:property value="#list.PREMIUM_DATE"/></td>
								<td align="center"><s:property value="#list.INSTALLMENT_NO"/></td>
							</tr>
							</s:iterator>
						</tbody>
					</table> 	
				</div>   
			</div>
		</s:if>
	</div>
</div>
</s:form>
<script type="text/Javascript" >
function fnSubmit(){
	    document.form1.action='getDueCountPaymentStatus.action?mode=list';
	    document.form1.submit();
}
function fnView(){
		document.form1.action='getPaymentDuePaymentStatus.action';
	    document.form1.submit();
}
function fnBack(){
		document.form1.action='getDueCountPaymentStatus.action?mode=list';
	    document.form1.submit();
}
</script>
</body>
</html>