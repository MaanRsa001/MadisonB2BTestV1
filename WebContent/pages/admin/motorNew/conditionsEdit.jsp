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
</head>
<body>
<s:form id="conditions" name="conditions" action="" theme="simple">
	
	
<div class="container vehDtl">
<div class="Card_Parent ">
<div class="card card-5">
<div class="p-3">

			<div class="col-md-12">
				<div class="panel panel-primary">
					<div class="panel-heading">
						<h3>Motor Condition Master</h3>
						<hr>
					</div>
						<div class="panel-body">
							<s:if test="hasActionErrors()">
								<font color="red"><s:actionerror cssStyle="list-style: none;"/></font>
							</s:if>
							<div class="row">
								<div class="col-xs-12 col-sm-12 col-md-6 col-lg-4">
									<div class="form-group">
    									<label>Policy Type<font color="red">*</font></label>
    									<s:select name="policyType" id="policyType" cssClass="form-control" headerKey="" headerValue="---Select---" list="motorPolicyTypeList" listKey="POLICY_SUBTYPE_ID" listValue="POLICYTYPE_DESC_ENGLISH" />
									</div>
								</div>
								<div class="col-xs-12 col-sm-12 col-md-6 col-lg-4">
									<div class="form-group">
    									<label>Condition Type<font color="red">*</font></label>
    									<s:select name="conditionType" id="conditionType" cssClass="form-control" headerKey="" headerValue="---Select---" list="#{'CONDITION':'Condition','FIRSTAMOUNTPAYABLE':'Deductible'}" />
									</div>
								</div>
								<div class="col-xs-12 col-sm-12 col-md-6 col-lg-4">
									<div class="form-group">
    									<label>Condition Description<font color="red">*</font></label>
    									<s:textarea name="conditionDesc" id="conditionDesc" cssClass="form-control" maxlength="2000"></s:textarea>
									</div>
								</div>
								<div class="col-xs-12 col-sm-12 col-md-6 col-lg-4">
									<div class="form-group">
    									<label>Core App Code<font color="red">*</font></label>
    									<s:textfield name="coreappCode" id="coreappCode" cssClass="form-control" maxlength="50"></s:textfield>
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
								<input type="button" class="btn btn-sm btn-success" value="Submit" onclick="funSubmit();">&nbsp;&nbsp;&nbsp;
								<input type="button" class="btn btn-sm btn-danger" value="Back" onclick="funBack('');" />
							</div>
						</div>
					<s:hidden name="sno" id="sno"></s:hidden>	
					<s:hidden name="mode" id="mode"></s:hidden>	
				</div>
			</div>
		</div>		
	</div>
</div>
</div>
</s:form>
<script type="text/javascript">
function funSubmit(){
	document.conditions.action='insCondMotorAdminNew.action';
	document.conditions.submit();
	}
	function funBack(mode){
		document.conditions.mode.value = mode;
		document.conditions.action='condListMotorAdminNew.action';
		document.conditions.submit();
	}
</script>
</body>
</html>   
