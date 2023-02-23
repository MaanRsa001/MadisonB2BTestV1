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
	<script type="text/javascript" src="js/common.js"></script>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/dataTables/css/dataTables.responsive.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/dataTables/css/dataTables.jqueryui.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/dataTables/css/dataTables.tableTools.css">
	<script type="text/javascript" src="${pageContext.request.contextPath}/dataTables/js/dataTables.responsive.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/dataTables/js/dataTables.tableTools.js"></script>
	<script type="text/javascript">
	
  	$(function() {
  		try {
  			$('#effDate').datepicker({
  				todayHighlight: true,
  				format: "dd/mm/yyyy"
  			}).on('changeDate', function(e){
  			    $(this).datepicker('hide');
  			});
  		} catch(err) {console.error(err);}
  	});
	
	
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
</head>
<body>
<s:form id="paymentBank" name="paymentBank" action="" theme="simple">



<div class="container vehDtl">
<div class="Card_Parent ">
<div class="card card-5">
<div class="p-3">
			<div class="col-md-12">
				<div class="panel panel-primary">
					<div class="panel-heading">
						<h3><s:text name="label.paymentbank"/></h3>
						<hr>
						<s:if test='mode != "add" && mode != "edit"'>
							<div align="right">
								<input type="button" class="btn btn-sm btn-default" value="Add New" onclick="funSubmit('add','');">
							</div>
							<br class="clear" />
						</s:if>
					</div>
					<s:if test='mode != "add" && mode != "edit"'>
						<div class="panel-body">
<!-- 							<div class="row"> -->
								<table class="table table-bordered table-hover" id="gridTableMake" cellspacing="0" width="100%" >
									<thead class="bluecolortable">
									<tr>
										<th class="no-sort"><s:text name="label.master.sno"/></th>
										<th><s:text name="label.paymentType"/></th>
										<th><s:text name="label.master.accHolder" /></th>
										<th><s:text name="label.master.bankEngName"/></th>
										<th><s:text name="label.master.accNumber"/></th>
										<th><s:text name="label.master.bankBranch"/></th>
										<th><s:text name="label.master.bankCode" /></th>
										<th><s:text name="label.currencyType"/></th>
										<th><s:text name="label.master.swiftCode"/></th>
										<th><s:text name="label.master.remarks" /></th>
										<th><s:text name="lable.master.effectiveDate" /></th>
										<th><s:text name="label.master.status"/></th>
										<th><s:text name="label.master.edit"/></th>
										
									</tr>
									</thead>
									<tbody class="rowevencolor">
										<s:iterator value="paymentBankList" var="list" status="stat">
											<tr>
												<td><s:property value="#stat.count" /></td>
												<td><s:property value="#list.PAYMENT_TYPE" /></td>
												<td><s:property value="#list.ACCOUNT_HOLDER" /></td>
												<td><s:property value="#list.BANK_NAME" /></td>
												<td><s:property value="#list.ACCOUNT_NUMBER" /></td>
												<td><s:property value="#list.BRANCH_NAME" /></td>
												<td><s:property value="#list.BRANCH_CODE" /></td>
												<td><s:property value="#list.CURRENCY_TYPE" /></td>
												<td><s:property value="#list.SWIFT_CODE" /></td>
												<td><s:property value="#list.REMARKS" /></td>
												<td><s:property value="#list.EFFECTIVE_DATE" /></td>
												<td><s:property value="#list.STATUS" /></td>
												<td style="text-align: center;">
													<input type="button" class="btn btn-warning btn-sm" onclick="funSubmit('edit','<s:property value="#list.PAYMENTBANK_ID" />');" value="Modify" />
												</td>
											</tr>
										</s:iterator>
									</tbody>
								</table>								
<!-- 							</div> -->
						</div>
					</s:if>
					<s:else>
						<div class="panel-body">
							<s:if test="hasActionErrors()">
								<font color="red"><s:actionerror cssStyle="list-style: none;"/></font>
							</s:if>
							<div class="row">									
								<div class="col-xs-12 col-sm-6 col-md-4">
									<div class="text"><s:text name="label.currencyType" /><font color="red">*</font></div>
									<div class="tbox">
										<s:select name="currencyType" id="currencyType" headerKey="" list="#{'USD':'USD','ZMW':'ZMW'}" headerValue="---Select---"  cssClass="form-control" />
									</div>
								</div>
								<div class="col-xs-12 col-sm-6 col-md-4">
									<div class="text"><s:text name="label.paymentType" /><font color="red">*</font></div>
									<div class="tbox">
										<s:select name="paymentType" id="paymentType" list="paymentTypeList" headerKey="" headerValue="---Select---" listKey="ITEM_CODE" listValue="ITEM_VALUE" cssClass="form-control" />
									</div>
								</div>
								<div class="col-xs-12 col-sm-12 col-md-6 col-lg-4">
									<div class="form-group">
    									<label><s:text name="label.master.accHolder"/></label><font color="red">*</font>
    									<s:textfield name="accHolder" id="accHolder" cssClass="form-control" maxlength="150"></s:textfield>
									</div>
								</div>
								<div class="col-xs-12 col-sm-12 col-md-6 col-lg-4">
									<div class="form-group">
    									<label><s:text name="label.master.bankEngName"/></label><font color="red">*</font>
    									<s:textfield name="bankEngName" id="bankEngName" cssClass="form-control" maxlength="150"></s:textfield>
									</div>
								</div>
								<div class="col-xs-12 col-sm-12 col-md-6 col-lg-4">
									<div class="form-group">
    									<label><s:text name="label.master.accNumber"/></label><font color="red">*</font>
    									<s:textfield name="accNumber" id="accNumber" cssClass="form-control" maxlength="150"></s:textfield>
									</div>
								</div>
								<div class="col-xs-12 col-sm-12 col-md-6 col-lg-4">
									<div class="form-group">
    									<label><s:text name="label.master.bankBranch"/></label><font color="red">*</font>
    									<s:textfield name="bankBranch" id="bankBranch" cssClass="form-control" maxlength="150"></s:textfield>
									</div>
								</div>
								<div class="col-xs-12 col-sm-12 col-md-6 col-lg-4">
									<div class="form-group">
    									<label><s:text name="label.master.bankCode"/></label><font color="red">*</font>
    									<s:textfield name="bankCode" id="bankCode" cssClass="form-control" maxlength="150"></s:textfield>
									</div>
								</div>
								<div class="col-xs-12 col-sm-12 col-md-6 col-lg-4">
									<div class="form-group">
    									<label><s:text name="label.master.swiftCode"/></label><font color="red">*</font>
    									<s:textfield name="swiftCode" id="swiftCode" cssClass="form-control" maxlength="150"></s:textfield>
									</div>
								</div>
								<div class="col-xs-12 col-sm-12 col-md-6 col-lg-4">
									<div class="form-group">
    									<label><s:text name="lable.master.effectiveDate" /><font color="red">*</font></label>
    									<s:textfield name="effectiveDate" id="effDate" cssClass="form-control" readonly="true" />
									</div>
								</div>
								<div class="col-xs-12 col-sm-12 col-md-6 col-lg-4">
									<div class="form-group">
    									<label><s:text name="label.master.remarks"/></label>
    									<s:textfield name="remarks" cssClass="form-control" maxlength="150"></s:textfield>
									</div>
								</div>
								<div class="col-xs-12 col-sm-12 col-md-6 col-lg-4">
									<div class="form-group">
    									<label><s:text name="label.master.status" /><font color="red">*</font></label><br/>
										<s:radio list="#{'Y':'Activate','N':'Deactivate'}" name="status" id="status" value='%{status==null?"N":status}' />
									</div>
								</div>
							</div>
						</div>
						<br class="clear" />
						<div class="row">
							<div class="col-xs-12 col-sm-12 col-md-12" align="center">
								<input type="button" class="btn btn-sm btn-success" value="Submit" onclick="funSubmit('insert','');">&nbsp;&nbsp;&nbsp;
								<input type="button" class="btn btn-sm btn-danger" value="Back" onclick="funBack();" />
							</div>
						</div>
						<s:hidden name="paymentBankId"></s:hidden>
					</s:else>					
				</div>
			</div>
		</div>		
	</div>
</div>
</div>
</s:form>
<script type="text/javascript">
	function funSubmit(mode,val){
		if(mode=='edit' || mode=='add'){
			document.paymentBank.action='paymentBankEditMotorAdminNew.action?mode='+mode+'&paymentBankId='+val;
		}else if(mode=='insert'){
			document.paymentBank.action='insertPaymentBankMotorAdminNew.action?mode=<s:property value="mode"/>';
		}
		document.paymentBank.submit();
	}
	function funBack(){
		document.paymentBank.action='getPaymentBankMotorAdminNew.action';
		document.paymentBank.submit();
	}
</script>
</body>
</html>   
