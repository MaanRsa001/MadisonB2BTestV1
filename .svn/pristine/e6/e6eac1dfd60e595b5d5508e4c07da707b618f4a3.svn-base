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
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/dataTables/css/dataTables.responsive.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/dataTables/css/dataTables.jqueryui.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/dataTables/css/dataTables.tableTools.css">
	<script type="text/javascript" src="${pageContext.request.contextPath}/dataTables/js/dataTables.responsive.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/dataTables/js/dataTables.tableTools.js"></script>
	<script type="text/javascript">
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
		
		
		$(function() {
			try {
				$('#expiryDate').datepicker({
					todayHighlight: true,
					format: "dd/mm/yyyy"
				}).on('changeDate', function(e){
				    $(this).datepicker('hide');
				});
				$('#intiExpiryDate').datepicker({
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
<s:form id="paymentMaster" name="paymentMaster" action="" theme="simple">
	
	
<div class="container vehDtl">
<div class="Card_Parent ">
<div class="card card-5">
<div class="p-3">

			<div class="col-md-12">
				<div class="panel panel-primary">
					<div class="panel-heading">
						<h3><s:text name="label.paymentMaster"/></h3>
						<hr>
						<s:if test='mode != "add" && mode != "edit"'> 
							<div class="pullRight">
								<input type="button" class="btn btn-sm btn-default" value="Add New" onclick="funSubmit('add','');">
							</div>
							<br class="clear" />
						</s:if>
					</div>
					<s:if test='mode != "edit" && mode != "add"'>
						<div class="panel-body">
						<font color="green"><s:actionmessage cssStyle="list-style: none;"/></font>
<!-- 							<div class="row"> -->
								<table class="table table-bordered table-hover" id="gridTableMake" cellspacing="0" width="100%" >
									<thead class="bluecolortable">
									<tr>
										<th class="no-sort"><s:text name="label.master.sno"/></th>
										<th><s:text name="label.profileId"/></th>
										<th><s:text name="label.accessKey" /></th>
										<th><s:text name="label.secretKey"/></th>
										<th><s:text name="label.expiryDate"/></th>
										<th><s:text name="label.currencyType"/></th>
										<th><s:text name="label.intiExpiryDate"/></th>
										<th><s:text name="label.intiMobileNo"/></th>
										<th><s:text name="label.intiEMailId"/></th>
										<th><s:text name="label.status"/></th>
										<th><s:text name="label.remarks"/></th>
										<th><s:text name="label.master.edit"/></th>
									</tr>
									</thead>
									<tbody class="rowevencolor">
										<s:iterator value="paymentMasterList" var="list" status="stat">
											<tr>
												<td><s:property value="#stat.count" /></td>
												<td><s:property value="PROFILE_ID" /></td>
												<td><s:property value="ACCESS_KEY"/></td>
												<td><s:property value="SECRET_KEY"/></td>
												<td><s:property value="EXPIRY_DATE" /></td>
												<td><s:property value="CURRENCY_TYPE" /></td>
												<td><s:property value="INTIMATION_EXPIRY_DATE" /></td>
												<td><s:property value="INTIMATION_MOBILE_NO"/></td>
												<td><s:property value="INTIMATION_MAIL_ID"/></td>
												<td><s:property value="STATUS" /></td>
												<td><s:property value="REMARKS"/></td>
												<td style="text-align: center;">
													<input type="button" class="btn btn-warning btn-sm" onclick="funSubmit('edit','<s:property value="PAYMENT_ID" />');" value="Modify" />
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
								<div class="col-xs-12 col-sm-12 col-md-6 col-lg-4">
									<div class="form-group">
    									<label><s:text name="label.profileId"/><font color="red">*</font></label>
    									<s:textarea name="profileId" id="profileId" cssClass="form-control" maxlength="1000"></s:textarea>
									</div>
								</div>		
								<div class="col-xs-12 col-sm-12 col-md-6 col-lg-4">
									<div class="form-group">
    									<label><s:text name="label.accessKey"/><font color="red">*</font></label>
    									<s:textarea name="accessKey" id="accessKey" cssClass="form-control" maxlength="1000"></s:textarea>
									</div>
								</div>			
								<div class="col-xs-12 col-sm-12 col-md-6 col-lg-4">
									<div class="form-group">
    									<label><s:text name="label.secretKey" /><font color="red">*</font></label>
    									<s:textarea name="secretKey" id="secretKey" cssClass="form-control" maxlength="1000"></s:textarea>
									</div>
								</div>
								<div class="col-xs-12 col-sm-12 col-md-6 col-lg-4">
<!-- 									<div class="form-group"> -->
    									<label><s:text name="label.expiryDate"/>(DD/MM/YYYY)<font color="red">*</font></label>
    									<s:textfield name="expiryDate" id="expiryDate" cssClass="form-control" readonly="true" />
<!-- 									</div> -->
								</div>
								<s:if test='mode == "add"'>
								<div class="col-xs-12 col-sm-12 col-md-6 col-lg-4">
									<div class="form-group">
    									<label><s:text name="label.currencyType"/><font color="red">*</font></label>
    									<s:select name="currencyType" id="currencyType" cssClass="form-control" headerKey="" headerValue="--Select Currency--" list="#{'USD':'USD', 'ZMW':'ZMW'}"/>
									</div>
								</div>
								</s:if>
								<s:if test='mode == "edit"'>
								<div class="col-xs-12 col-sm-12 col-md-6 col-lg-4">
									<div class="form-group">
    									<label><s:text name="label.currencyType"/><font color="red">*</font></label>
    									<s:textfield name="currencyType" id="currencyType" cssClass="form-control" disabled="true"></s:textfield>
									</div>
								</div>
								<s:hidden name="currencyType"></s:hidden>
								</s:if>
								<div class="col-xs-12 col-sm-12 col-md-6 col-lg-4">
									<div class="form-group">
    									<label><s:text name="label.intiExpiryDate"/>(DD/MM/YYYY)<font color="red">*</font></label>
    									<s:textfield name="intiExpiryDate" id="intiExpiryDate" readonly="true" cssClass="form-control"></s:textfield>
									</div>
								</div>			
								<div class="col-xs-12 col-sm-12 col-md-6 col-lg-4">
									<div class="form-group">
    									<label><s:text name="label.intiMobileNo"/><font color="red">*</font></label>
    									<s:textfield name="intiMobileNo" id="intiMobileNo" cssClass="form-control" onkeypress="return isNumberKey(event)" maxlength="10"></s:textfield>
									</div>
								</div>
								<div class="col-xs-12 col-sm-12 col-md-6 col-lg-4">
									<div class="form-group">
    									<label><s:text name="label.intiEMailId"/><font color="red">*</font></label>
    									<s:textfield name="intiEMailId" id="intiEMailId" cssClass="form-control" maxlength="50"></s:textfield>
									</div>
								</div>
								<div class="col-xs-12 col-sm-12 col-md-6 col-lg-4">
									<div class="form-group">
    									<label><s:text name="label.remarks"/></label>
    									<s:textarea name="remarks" id="remarks" cssClass="form-control" maxlength="150"></s:textarea>
									</div>
								</div>
								<div class="col-xs-12 col-sm-12 col-md-6 col-lg-4">
									<div class="form-group">
    									<label><s:text name="label.status" /><font color="red">*</font></label><br/>
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
					<s:hidden name="paymentId" id="paymentId"></s:hidden>						
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
	var action="";
	if(mode=='edit'){
		action='editPaymentMasAdmin.action?mode='+mode+'&PaymentId='+val;
	}else if(mode=='add'){
		action='editPaymentMasAdmin.action?mode='+mode;
		}
	else if(mode=='insert'){
		action='updatePaymentMasAdmin.action?mode=<s:property value="mode"/>';
	}
	else{
		alert("Action is empty");
	}
	document.paymentMaster.action=action;
	document.paymentMaster.submit();
	}
	function funBack(){
		document.paymentMaster.action='paymentMasAdmin.action';
		document.paymentMaster.submit();
	}
	function isNumberKey(evt){
    var charCode = (evt.which) ? evt.which : event.keyCode
    if (charCode > 31 && (charCode < 48 || charCode > 57))
        return false;
    return true;
}
</script>
</body>
</html>   
