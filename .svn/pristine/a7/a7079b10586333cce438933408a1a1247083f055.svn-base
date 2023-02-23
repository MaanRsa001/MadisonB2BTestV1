<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://displaytag.sf.net" prefix="display"%>
<!DOCTYPE HTML>
<html>
<head>
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
<style type="text/css">
	#myDIV {
	    overflow-x:scroll;
	    margin: auto:
	}
           }
</style>
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
						    		<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
										<div class="">
											<label><s:text name="label.startDate"/>:<font color="red">*</font></label><br/>
											<s:textfield name="startDate" id="startDateId" cssClass="form-control" readonly="true"></s:textfield>
										</div>
									</div>
									<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
										<div class="">
											<label><s:text name="label.endDate" />:<font color="red">*</font></label><br/>
											<s:textfield name="endDate" id="endDateId" cssClass="form-control" readonly="true" ></s:textfield>
										</div>
									</div>
									<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4" >
										<div class="">
											<label><s:text name="Type of Report" />:<font color="red">*</font></label><br/>
											<s:radio list="#{'IR':'Installment Report','AG':'Aging Anlysis'}" name="utype" id="utype" onchange="userIdAjax(this.value,'approverId','','')"/>
										</div>
									</div>
								</div>
								<br class="clear" />
								<div class="row">
									<div id="approverId" style="width: 500px;">
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
					</div><br>
					<s:if test="reqFrom == 'showList'">
						<s:if test=" smartReportList.size()>0 ">
							<s:if test="reportType == 'INSTALMENTPAYMENT'">
								<div class="panel panel-primary">
									<div class="panel-heading">
										<h3><label><font><s:property value="reportType"/></font></label>&nbsp;REPORT</h3><hr></hr>
									</div> 
									<div class="panel-body">
										<table class="table table-bordered table-hover" id="tadaTable" cellspacing="0" width="100%" >
											<thead class="bluecolortable">
											<tr>
												<th><s:text name="label.master.sno"/></th>
												<th><s:text name="label.quoteno"/></th>
												<th><s:text name="label.policyNo"/></th>					
												<th><s:text name="label.premium"/></th>
												<th><s:text name="label.installA"/></th>		
												<th><s:text name="label.dueDate"/></th>
												<th><s:text name="label.noOfEmi"/></th>
												<th><s:text name="label.currentIns"/></th>
												<th><s:text name="label.insStatus"/></th>
												<th><s:text name="label.baldue"/></th>
												<th><s:text name="label.paymentMode"/></th>
												<th><s:text name="label.currencytype"/></th>
											</tr>
											</thead>
											<tbody class="rowevencolor">
												<s:iterator value="smartReportList" var="list" status="stat">
												<tr>
													<td><s:property value="#stat.count"/></td>
													<td><s:property value="#list.QUOTE_NO"/></td>
													<td><s:property value="#list.POLICY_NO" /></td>
													<td align="right"><s:property value="#list.OVERALL_PREMIUM"/></td>
													<td align="right"><s:property value="#list.INSTALLMENT_PREMIUM_AMOUNT" /></td>
													<td><s:property value="#list.INS_PREMIUM_DATE"/></td>
													<td align="center"><s:property value="#list.NO_OF_EMI"/></td>
													<td align="center"><s:property value="#list.INSTALLMENT_NO"/></td>
													<td align="center"><s:property value="#list.PAYMENT_STATUS"/></td>
													<td><s:property value="#list.DUE_AMT"/></td>
													<td align="center"><s:property value="#list.PAYMENT_METHOD"/></td>
													<td align="center"><s:property value="#list.CURRENCY"/></td>
												</tr>
												</s:iterator>
											</tbody>
										</table> 	
									</div>   
								</div>
							</s:if>
							<s:if test="reportType == 'AGINGANALYSIS'">
								<div class="panel panel-primary">
									<div class="panel-heading">
										<h3><label><font><s:property value="reportType"/></font></label>&nbsp;REPORT</h3><hr></hr>
									</div> 
									<div class="panel-body">
										<table class="display responsive no-wrap" id="tadaTable" width="100%" cellspacing="0">
											<thead class="bluecolortable">
											<tr>
												<th><s:text name="label.master.sno"/></th>
												<th><s:text name="label.quoteno"/></th>
												<th><s:text name="label.policyNo"/></th>
												<th><s:text name="label.policyPeriod"/></th>
												<th><s:text name="label.baldue"/></th>
												<th><s:text name="label.noOfEmi"/></th>
												<th><s:text name="label.currentIns"/></th>
												<th><s:text name="label.insStatus"/></th>
												<th><s:text name="label.paymentMode"/></th>
												<th><s:text name="label.currencytype"/></th>
											</tr>
											</thead>
											<tbody class="rowevencolor">
												<s:iterator value="smartReportList" var="list" status="stat">
												<tr>
													<td><s:property value="#stat.count"/></td>
													<td><s:property value="#list.QUOTE_NO"/></td>
													<td><s:property value="#list.POLICY_NO" /></td>
													<td ><s:property value="#list.POLICY_START_DATE"/>&nbsp;to&nbsp; <s:property value="#list.POLICY_END_DATE"/></td>
													<td align="right"><s:property value="#list.DUE_AMT"/></td>
													<td><s:property value="#list.NO_OF_EMI"/></td>
													<td><s:property value="#list.INSTALLMENT_NO"/></td>
													<td align="center"><s:property value="#list.PAYMENT_STATUS"/></td>
													<td align="center"><s:property value="#list.PAYMENT_METHOD"/></td>
													<td align="center"><s:property value="#list.CURRENCY"/></td>
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
									<h3><label><font><s:property value="reportType"/></font></label>&nbsp;REPORT</h3><hr></hr>
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

userIdAjax('<s:property value="utype"/>','approverId','<s:property value="paymentVal"/>','<s:property value="status"/>')

function fnSubmit(val){
	var reportType;
	var uType = $('input[name="utype"]:checked').val();
	if(uType == 'IR'){
		reportType="INSTALMENTPAYMENT";
	}else if(uType == 'AG'){
		reportType="AGINGANALYSIS";
	}else{
		reportType="";
		}
	document.form1.action='getAllListAreport.action?mode='+val+'&reportType='+reportType;
	document.form1.submit();
}

function userIdAjax(val,id,pVal,stat){
	var action ="${pageContext.request.contextPath}/searchListAjaxAreport.action?utype="+val+"&paymentVal="+pVal+"&status="+stat;
	postRequest(action, id);
	//document.getElementById("approverList").style.display = "block";
}
function fnReport(val){
	
	document.form1.action='allRepJasperAreport.action?downloadType='+val+'&reportType=<s:property value="reportType"/>';
    document.form1.submit();
}
/*function displayAjax(val,id){
	if(val == 'SV'){
		document.getElementById("searchValueId").style.display = "block";
		document.getElementById("searchList").style.display = "none";	
		document.getElementById("dateWise").style.display = "none";
	}else if(val == '' || val == null){
		document.getElementById("searchList").style.display = "none";
		document.getElementById("searchValueId").style.display = "none";
		document.getElementById("dateWise").style.display = "none";
	}else if(val == 'DP'){
		document.getElementById("dateWise").style.display = "block";
		document.getElementById("searchList").style.display = "none";
		document.getElementById("searchValueId").style.display = "none";
	}
	else{
		var reportType = document.getElementById("reportType").value;
		var action ="${pageContext.request.contextPath}/searchListAjaxAreport.action?searchBy="+val+"&reportType="+reportType;
		postRequest(action, id);
		document.getElementById("searchList").style.display = "block";
		document.getElementById("searchValueId").style.display = "none";
		document.getElementById("dateWise").style.display = "none";
	}
}*/
</script>
</body>
</html>