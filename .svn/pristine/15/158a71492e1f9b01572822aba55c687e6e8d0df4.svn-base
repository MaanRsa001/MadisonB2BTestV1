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
	
	$(function() {
		try {
			$('#startDate1').datepicker({
				todayHighlight: true,
				format: "dd/mm/yyyy"
			}).on('changeDate', function(e){
			    $(this).datepicker('hide');
			});
			$('#endDate1').datepicker({
				todayHighlight: true,
				format: "dd/mm/yyyy"
			}).on('changeDate', function(e){
			    $(this).datepicker('hide');
			});
		} catch(err) {console.error(err);}
		
	});
</script>	
</head>
<body>
<s:form name="form1" theme="simple">
<div class="container vehDtl">
<div class="Card_Parent ">
<div class="card card-5">
<div class="p-3">
<div class="row">
	<div class="col-md-12">
	<s:if test="mode != 'viewlist' ">
		<div class="panel panel-primary">
			<div class="panel-heading">
				<h3><s:text name="label.due.report"/></h3>
			</div>				
			<div class="panel-body">
				<s:if test="hasActionErrors()">
					<div class="row">
						<div class="col-md-12">
							<font color="red"><s:actionerror cssStyle="list-style: none;"/></font>
						</div>						
					</div>
					<br class="clear" />
				</s:if>
				<div class="row">
			    	<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
						<div class="">
							<label><s:text name="label.startDate"/><font color="red">*</font></label><s:textfield name="startDate" id="startDate1" cssClass="form-control" readonly="true"></s:textfield>
						</div>
					</div>
					<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
						<div class="">
							<label><s:text name="label.endDate" /><font color="red">*</font></label> <s:textfield name="endDate" id="endDate1" cssClass="form-control" readonly="true" ></s:textfield>
						</div>
					</div>
					<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4" align="center">
					<br class="clear"/>
						<input type="button" onclick="fnSubmit();" class="btn btn-sm btn-success" value="Submit" align="left" />
					</div>
				</div>
			</div>
		</div>
			</s:if>	
		<s:if test="mode == 'list'">					
			<div class="panel panel-primary">
				<div class="panel-heading">
					<h4><s:text name="label.insduedetail"/></h4>
				</div> 
			    <div class="panel-body">
					<table class="table table-bordered table-hover" id="gridTable" cellspacing="0" width="100%" >
						<thead class="bluecolortable" align="center">
							<tr>
								<th align="center"><s:text name="label.master.sno"/></th>
								<th align="center"><s:text name="label.duecount"/></th>
								<th align="right"><s:text name="label.dueAmount"/></th>
							</tr>
						</thead>
						<tbody class="rowevencolor">
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
					<h4><s:text name="label.insduedetail"/></h4>
				</div> 
				<div class="panel-body">
<!-- 					<table class="display responsive no-wrap" id="gridTable" width="100%" cellspacing="0"> -->
					<table class="table table-bordered table-hover" id="gridTable" cellspacing="0" width="100%" >
						<thead class="bluecolortable">
						<tr>
							<th><s:text name="label.master.sno"/></th>
							<th><s:text name="label.quoteno"/></th>
							<th><s:text name="label.policyNo"/></th>					
							<th><s:text name="label.customerName"/></th>		
							<th><s:text name="label.premium"/></th>
							<th><s:text name="label.dueAmount"/></th>		
							<th><s:text name="label.dueDate"/></th>
							<th><s:text name="label.currentIns"/></th>
							<th><s:text name="label.status"/></th>
							<th></th>
						</tr>
						</thead>
						<tbody class="rowevencolor">
							<s:iterator value="paymentDueList" var="list" status="stat">
							<tr>
								<td><s:property value="#stat.count"/></td>
								<td><s:property value="#list.QUOTE_NO"/></td>
								<td><s:property value="#list.POLICY_No" /></td>
								<td><s:property value="#list.CUSTOMER_NAME"/></td>
								<td align="right"><s:property value="#list.OVERALL_PREMIUM"/></td>
								<td align="right"><s:property value="#list.PREMIUM_AMOUNT" /></td>
								<td><s:property value="#list.PREMIUM_DATE"/></td>
								<td align="center"><s:property value="#list.DESCRIPTION"/></td>
								<td align="center"><s:property value="#list.PAYMENT_STATUS"/></td>
								<td align="center" ><input type="button" onclick="fnView1('<s:property value="#list.QUOTE_NO"/>');" class="btn btn-sm btn-success" value="View" align="middle" /></td>
							</tr>
							</s:iterator>
						</tbody>
					</table> 	
				</div>   
			</div>
		</s:if>
		<s:if test="mode == 'viewlist' ">
						<div class="panel panel-primary" >
						<div class="panel-heading">
							<h4><s:text name="label.installment.status"/></h4>
						</div><br class="clear"/>
							<div class="container-fluid">
							<s:if test="hasActionErrors()">
								<font color="red"><s:actionerror cssStyle="list-style: none;"/></font>
							</s:if>
									<div class="row">
									     <div class="col-xs-12">
											<div class="panel panel-primary">
												<div class="panel-heading">
													<s:text name="label.customer"/>
													<div class="pullRight">
																<label><s:text name="label.quoteno" />&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;</label><s:property value="quoteNo" /><s:hidden name="quoteNo"></s:hidden>
													</div>
												</div>
												<div class="panel-body">
									        		<div class="row">
									        				<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
																<div class="">
							    									<label><s:text name="label.customerName"/>&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;</label><s:property value="firstName" />&nbsp;&nbsp;<s:property value="lastName" />
																</div>
															</div>
															<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
																<div class="">
							    									<label><s:text name="label.add1"/>&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;</label><s:property value="add1" />
																</div>
															</div>
															<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
																<div class="">
							    									<label><s:text name="label.add2"/>&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;</label><s:property value="add2" />
																</div>
															</div>
															<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
																<div class="">
							    									<label><s:text name="label.pobox"/>&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;</label><s:property value="poBox" />
																</div>
															</div>
															<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
																<div class="">
							    									<label><s:text name="label.mobile"/>&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;</label><s:property value="mobileNo" />
																</div>
															</div>
															<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
																<div class="">
							    									<label><s:text name="label.mail"/>&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;</label><s:property value="eMailId" />
																</div>
															</div>
									        				</div>
									        		</div>
									        		</div>
									        		<div class="panel panel-primary">
									        		<div class="panel-heading">
														<s:text name="label.premium.detail"/>
													</div>
													<div class="panel-body">
									        		<div class="row">
									        				<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
																<div class="">
							    									<label><s:text name="label.policyNo"/>&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;</label><s:property value="policyNo" />
																</div>
															</div>
									        				<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
																<div class="">
							    									<label><s:text name="label.startDate"/>&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;</label><s:property value="fromDate" />
																</div>
															</div>
															<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
																<div class="">
							    									<label><s:text name="label.endDate"/>&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;</label><s:property value="toDate" />
																</div>
															</div>
															<!--<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
																<div class="">
							    									<label><s:text name="label.quoteno"/>&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;</label><s:property value="quoteNo" />
																</div>
															</div>
															-->
															<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
																<div class="">
							    									<label><s:text name="label.premium"/>&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;</label><s:property value="premium" />
																</div>
															</div>
															<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
																<div class="">
							    									<label><s:text name="label.vat"/>&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;</label><s:property value="vat" />
																</div>
															</div>
															<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
																<div class="">
							    									<label><s:text name="label.total.premium"/>&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;</label><s:property value="totalPremium" />
																</div>
															</div>
															<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
																<div class="">
							    									<label><s:text name="label.noOfEmi"/>&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;</label><s:property value="noOfEmi" />
																</div>
															</div>
									        				</div>
									        			</div>
									        		</div>
									        		<div class="panel panel-primary">
									        			<div class="panel-heading">
															<h3><s:text name="label.installment.detail"/></h3>
														</div>
														<div class="panel-body">
									        			<div class="row">
									        					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" style="overflow-x: scroll; width:1250px;">
									        					<table class="footable" >
																		<thead>
																		<tr>
																			<th align="center"><s:text name="label.master.sno"/></th>
																			<th align="center"><s:text name="label.ins"/></th>
																			<th align="center"><s:text name="label.desc"/></th>
																			<th align="right"><s:text name="label.dueAmount"/></th>					
																			<th align="center"><s:text name="label.dueDate"/></th>		
																			<th align="center"><s:text name="label.status"/></th>
																		</tr>
																		</thead>
																		<tbody>
																			<s:iterator value="noInstallmentList" var="list" status="stat">
																				<tr>
																					<td align="center"><s:property value="#stat.count"/></td>
																					<td align="center"><s:property value="#list.INSTALLMENT_NO"/></td>
																					<td align="center"><s:property value="#list.DESCRIPTION"/></td>
																					<td align="right"><s:property value="#list.PREMIUM_AMOUNT" /></td>
																					<td align="center"><s:property value="#list.PREMIUM_DATE"/></td>
																					<td align="center"><s:property value="#list.STATUS" /></td>
																				</tr>
																			</s:iterator>
																		</tbody>
																	</table>
									        					</div>
									        				</div>
									        			</div>
									        		</div>
									        		<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" >
														<div class="" align="center">
					    									<input type="button" onclick="fnView();" class="btn btn-sm btn-danger" value="Back" align="middle" />
														</div>
														<br class="clear"/>
													</div>
									        </div>
									     </div>
										</div>
										<s:hidden name="startDate"  />
										<s:hidden name="endDate"  />
									</div>
								</s:if>	
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
</s:form>
<script type="text/Javascript" >
function fnSubmit(){
	    document.form1.action='getEmiCountPaymentStatus.action?mode=list';
	    document.form1.submit();
}
function fnView(){
		document.form1.action='getEmiDetailPaymentStatus.action';
	    document.form1.submit();
}
function fnBack(){
		document.form1.action='getEmiCountPaymentStatus.action?mode=list';
	    document.form1.submit();
}

function fnView1(val){
	document.form1.action='getEmiViewPaymentStatus.action?quoteNo='+val;
    document.form1.submit();
}

</script>
</body>
</html>