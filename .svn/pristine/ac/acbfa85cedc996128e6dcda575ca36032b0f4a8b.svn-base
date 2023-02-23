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
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/dataTables/css/dataTables.responsive.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/dataTables/css/dataTables.jqueryui.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/dataTables/css/dataTables.tableTools.css">
	<script type="text/javascript" src="${pageContext.request.contextPath}/dataTables/js/dataTables.responsive.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/dataTables/js/dataTables.tableTools.js"></script>
<script type="text/javascript">

$(document).ready(function () {
	  $('#tadaTable').DataTable({
  	  "responsive": true,
      "columnDefs": {
        "orderable": false
      },
      language: {
        //customize pagination prev and next buttons: use arrows instead of words
        'paginate': {
          'previous': '<span><i class="fas fa-chevron-circle-left"></i></span>',
          'next': '<span><i class="fas fa-chevron-circle-right"></i></span>'
        },
        //customize number of elements to be displayed
        "lengthMenu": '<select class="form-control input-sm">' +
          '<option value="10">10</option>' +
          '<option value="20">20</option>' +
          '<option value="30">30</option>' +
          '<option value="40">40</option>' +
          '<option value="50">50</option>' +
          '<option value="-1">All</option>' +
          '</select>'
      }
    })
  });
	

	
	$(function() {
		try {
			$('#startDateId').datepicker({
				todayHighlight: true,
				format: "dd/mm/yyyy"
			}).on('changeDate', function(e){
			    $(this).datepicker('hide');
			});
			$('#endDateId').datepicker({
				todayHighlight: true,
				format: "dd/mm/yyyy"
			}).on('changeDate', function(e){
			    $(this).datepicker('hide');
			});
		} catch(err) {console.error(err);}
		
	});
	

<%-- jQuery(function ($) {
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
			} );--%>
</script>	
</head>
<body>
<s:form name="form1" theme="simple">

<div class="container wrapper vehDtl">
<div class="Card_Parent ">
<div class="card card-5">
<div class="row p-3">
	<div class="col-md-12">
		<div class="panel panel-primary">
				<div class="panel-heading">
					<h3>
					<s:text name="Reports"/>
					<span class="pullRight">
						<s:if test='#session.product_id=="33" || #session.product_id=="34"'>
							<i class="fa fa-plane"></i>&nbsp;<s:text name="Travel Insurance"/>
						</s:if>
						<s:elseif test='#session.product_id=="41"'>
							<i class="fa fa-heartbeat"></i>&nbsp;<s:text name="Health Insurance"/>
						</s:elseif>
						<s:elseif test='#session.product_id=="65"'>
							<i class="fa fa-car"></i>&nbsp;<s:text name="Motor Insurance"/>
						</s:elseif>
						<s:elseif test='#session.product_id=="30"'>
							<i class="fa fa-home"></i>&nbsp;<s:text name="Home Insurance"/>
						</s:elseif>
						<s:elseif test='#session.product_id=="3" || #session.product_id=="11"'>
							<i class="fa fa-ship"></i>&nbsp;<s:text name="Marine Insurance"/>
						</s:elseif>	
					</span>
					</h3>
					<hr/>
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
			    		<div class="col-xs-12 col-sm-12 col-md-3 col-lg-3">
							<div class="" >
								<b><label><s:text name="label.startDate"/>:<font color="red">*</font></label><br/></b>
								<s:textfield name="startDate" id="startDateId" cssClass="form-control datepicker" readonly="true"></s:textfield>
							</div>
						</div>
						<div class="col-xs-12 col-sm-12 col-md-3 col-lg-3">
							<div class="">
								<b><label><s:text name="label.endDate" />:<font color="red">*</font></label><br/></b>
								<s:textfield name="endDate" id="endDateId" cssClass="form-control datepicker" readonly="true" ></s:textfield>
							</div>
						</div>
						<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6" >
							<div class="">
								<b><label><s:text name="Type of Report" />:<font color="red">*</font></label><br/></b>
							<%--<s:radio list="#{'P':'Policy Register','C':'Cancelled Policy','UR':'Un Renewed Policy'}" name="utype" id="utype" onchange="userIdAjax(this.value,'approverId','')"/> --%>
								<s:radio list="#{'P':'Policy Register','C':'Cancelled Policy','UR':'Un Renewed Policy'}" name="status" id="status" theme="simple"/>
							</div>
						</div>
					</div>
					<br class="clear" />
					<div class="row">
						<div class="col-xs-12 col-sm-12 col-md-3 col-lg-3">
							<div class="">
							<b><label><s:text name="Policy Type" /></label><br/></b>
								<s:select name="coverTypeVal" id="coverTypeVal" list="coverTypeList"  listKey="POLICY_SUBTYPE_ID" listValue="POLICYTYPE_DESC_ENGLISH" headerKey="ALL" headerValue="All" label="" cssClass="inputBoxS form-control" />
							</div>
						</div>
						<div class="col-xs-12 col-sm-12 col-md-3 col-lg-3">
							<div class="">
								<b><label><s:text name="Vehicle Usage" /></label><br/></b>
								<s:select name="vehicleUsageVal" id="vehicleUsageVal" list="vehicleUsageList"  listKey="CODE" listValue="CODEDESC" headerKey="ALL" headerValue="All" label="" cssClass="inputBoxS form-control" />
							</div>
						</div>
						<div class="col-xs-12 col-sm-12 col-md-3 col-lg-3">
							<div class="">
								<b><label><s:text name="Choose City" /></label><br/></b>
								<s:select name="cityVal" id="cityVal" list="cityList"  listKey="city_name" listValue="city_name" headerKey="ALL" headerValue="All" label="" cssClass="inputBoxS form-control" />
							</div>
						</div>
						<div id="approverId">
						</div>
					</div>
					<br class="clear" />
					<div class="row">
						<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" align="center">
							<br class="clear"/>
							<input type="button" onclick="fnSubmit('search');" class="btn btn-sm btn-success" value="Submit" align="left" />
						</div>
					</div>
				</div>
		</div>
		<s:hidden name="reportType" id="reportType" />
		<s:if test="reqFrom == 'showList'">
			<s:if test=" smartReportList.size()>0 ">
				<s:if test="reportType == 'POLICYREGISTER' ">
			<div class="panel panel-primary">
				<div class="panel-heading">
					<h3><label><font><s:property value="reportType"/></font></label>&nbsp;REPORT</h3><hr>
				</div> 
				<div class="panel-body">
					<table class="table table-bordered table-hover" id="tadaTable" cellspacing="0" width="100%" >
						<thead class="bluecolortable">
						<tr>
							<th><s:text name="label.master.sno"/></th>
							<th><s:text name="label.policyNo"/></th>
							<th><s:text name="label.policy.start"/></th>
							<th><s:text name="label.policy.end"/></th>
							<th><s:text name="label.suminsured.value.local"/></th>
							<th><s:text name="label.premium"/></th>
							<th><s:text name="label.policyType"/></th>		
							<th><s:text name="label.approve"/></th>
							<th><s:text name="label.cust.name"/></th>
							<th><s:text name="claim.address"/></th>
							<th><s:text name="claim.typeOfBody"/></th>
						</tr>
						</thead>
						<tbody class="rowevencolor">
							<s:iterator value="smartReportList" var="list" status="stat">
							<tr>
								<td><s:property value="#stat.count"/></td>
								<td><s:property value="#list.POLICY_NO"/></td>
								<td><s:property value="#list.POLICY_START_DATE" /></td>
								<td><s:property value="#list.POLICY_END_DATE"/></td>
								<td><s:property value="#list.SUMINSURED_VALUE_LOCAL"/></td>
								<td><s:property value="#list.PREMIUM" /></td>
								<td><s:property value="#list.POLICYTYPE_DESC"/></td>
								<td><s:property value="#list.APPROVED_ID" /></td>
								<td><s:property value="#list.CUSTOMER_NAME" /></td>
								<td><s:property value="#list.CUSTOMER_ADDRESS"/></td>
								<td><s:property value="#list.VEHICLE_USAGE"/></td>
							</tr>
							</s:iterator>
						</tbody>
					</table> 	
				</div>   
			</div>
		</s:if>
		<div class="row">
			<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6" align="center">
				<input type="button" onclick="fnReport('excel');" class="btn btn-warning btn-sm" value="Excel" />
			</div>
			<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6" align="center">
				<input type="button" onclick="fnReport('pdf');" class="btn btn-danger btn-sm" value="PDF" />
			</div>
		</div>
			</s:if>
			<s:else>
				<div class="panel panel-primary">
					<div class="panel-heading">
						<h3><label><font><s:property value="reportType"/></font></label>&nbsp;REPORT</h3><hr>
					</div> 
					<div class="panel-body">
						<font color="red">No Data Available</font>
					</div>   
				</div>
			</s:else>
		</s:if>
	</div>
</div>
</div>
</div>
</div>
</s:form>
<script type="text/Javascript" >

userIdAjax('<s:property value="utype"/>','approverId','<s:property value="vehicleUsageVal"/>')

function fnSubmit(val){
	document.form1.action='getAllListAreport.action?mode='+val;
	document.form1.submit();
}
function userIdAjax(val,id,vuVal){
	var action ="${pageContext.request.contextPath}/searchListAjaxAreport.action?utype="+val+"&vehicleUsageVal="+vuVal;
	postRequest(action, id);
	//document.getElementById("approverList").style.display = "block";
}

function fnReport(val){
	
	document.form1.action='allRepJasperAreport.action?downloadType='+val;
    document.form1.submit();
}
</script>
</body>
</html>