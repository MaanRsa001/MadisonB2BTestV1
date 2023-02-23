<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head></head>
<body>
	<s:if test='"profile"==selection'>
		<s:form id="profile" name="profile" method="post" theme="simple">
			<div class="row">
				<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
					<s:actionerror cssStyle="color: red;" />
					<s:actionmessage cssStyle="color: green;" />
				</div>
			</div>
			<div class="row">
				<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
					<div class="panel panel-primary">
						<div class="panel-heading">
							<s:text name="travel.customerInfo" />
						</div>
						<div class="panel-body" style="padding: 0px 15px 0px 15px;">
							<div class="boxcontent">
								<div class="textfield33V">
									<div class="textV"><s:text name="travel.customerName" /></div>
									<div class="tboxV"><s:property value="customerName" /></div>
								</div>
								<div class="textfield33V">
									<div class="textV"><s:text name="customer.address1" /></div>
									<div class="tboxV"><s:property value="address1" /></div>
								</div>
								<div class="textfield33V">
									<div class="textV"><s:text name="customer.address2" /></div>
									<div class="tboxV"><s:property value="address2" /></div>
								</div>
								<div class="textfield33V">
									<div class="textV"><s:text name="customer.city" /></div>
									<div class="tboxV"><s:property value="city" /></div>
								</div>
								<div class="textfield33V">
									<div class="textV"><s:text name="travel.pobox" /></div>
									<div class="tboxV"><s:property value="poBox" /></div>
								</div>
								<div class="textfield33V">
									<div class="textV"><s:text name="travel.emailId" /></div>
									<div class="tboxV"><s:property value="emailId" /></div>
								</div>
								<div class="textfield33V">
									<div class="textV"><s:text name="travel.mobileNo" /></div>
									<div class="tboxV"><s:property value="mobileNo" /></div>
								</div>
								<br class="clear" />
							</div>				
						</div>
					</div>
					<div class="panel panel-primary">
						<div class="panel-heading">
							<s:text name="travel.policyInfo" />
						</div>
						<div class="panel-body" style="padding: 0px 15px 0px 15px;">
							<s:iterator value="trPolicyList" var="trPolicyList">
							<div class="row">
								<s:if test='%{null!=#trPolicyList.POLICY_NO}'>
									<div class="textfield33V">
										<div class="textV"> <s:text name="travel.policyNo" /> </div>
										<div class="tboxV"> <s:property value="#trPolicyList.POLICY_NO" /> </div>
									</div>
								</s:if>
								<s:else>
									<div class="textfield33V">
										<div class="textV"> <s:text name="travel.quoteNo" /> </div>
										<div class="tboxV"> <s:property value="quoteNo" /> </div>
									</div>
								</s:else>
								<div class="textfield33V">
									<div class="textV"> <s:text name="travel.premium" /> </div>
									<div class="tboxV"> <s:property value="#trPolicyList.PREMIUM" /> </div>
								</div>
								<div class="textfield33V">
									<div class="textV"> <s:text name="travel.inceptiondate" /> </div>
									<div class="tboxV"> <s:property value="#trPolicyList.EFFECTIVE_DATE" /> </div>
								</div>
								<div class="textfield33V">
									<div class="textV"> <s:text name="travel.expiryDt" /> </div>
									<div class="tboxV"> <s:property value="#trPolicyList.EXPIRY_DATE" /> </div>
								</div>
								<s:if test='%{null!=#trPolicyList.LAPSED_REMARKS}'>
									<div class="textfield33V">
										<div class="textV"> <s:text name="travel.lapsremarks" /> </div>
										<div class="tboxV"> <s:property value="#trPolicyList.LAPSED_REMARKS" /> </div>
									</div>
									<div class="textfield33V">
										<div class="textV"> <s:text name="travel.lapseddate" /> </div>
										<div class="tboxV"> <s:property value="#trPolicyList.LAPSED_DATE" /> </div>
									</div>
								</s:if>
								<s:if test='%{null!=#trPolicyList.POLICY_TERM}'>
									<div class="textfield33V">
										<div class="textV"> <s:text name="travel.coverPeriod" /> </div>
										<div class="tboxV"> <s:property value="#trPolicyList.POLICY_TERM" /> Days </div>
									</div>
								</s:if>
								<br class="clear" />
							</div>
							</s:iterator>							
						</div>
					</div>
					<div class="panel panel-primary">
						<div class="panel-heading">
							<s:text name="travel.personsToInsure" />
						</div>
						<div class="panel-body" style="padding: 0px 15px 0px 15px;">
							<table class="footable" width="100%" id="TravelTable">
									<thead>
										<tr>
											<th width="3%"> <s:text name="travel.sNo"/> </th>
											<th width="12.12%"> <s:text name="travel.first.name" /><font color="red">*</font> </th>
											<th width="12.12%"> <s:text name="travel.last.name" /><font color="red">*</font> </th>
											<th width="12.12%"> <s:text name="travel.dateOfBirth" /><font color="red">*</font> </th>
											<th width="12.12%"> <s:text name="travel.gender" /><font color="red">*</font> </th>
											<th width="12.12%"> <s:text name="travel.relations" /><font color="red">*</font> </th>
											<th width="12.12%"> <s:text name="travel.nationality" /><font color="red">*</font> </th>
											<th width="12.12%"><s:text name="travel.passport.number" /></th>
											<th width="12.12%"><s:text name="travel.passport.expiry.date" /></th>
										</tr>
									</thead>
									<tbody>
										<s:iterator value="travelList" var="trList" status="stat">
											<tr>
												<td align="center">
													<s:property />
													<s:hidden name="travelList[%{#stat.count-1}]" id="travelList%{#stat.count-1}" />
													<s:hidden name="serialNos[%{#stat.count-1}]" id="serialNos%{#stat.count-1}" />
												</td>
												<td> <s:textfield name="travelNames[%{#stat.count-1}]" id="travelNames[%{#stat.count-1}]" cssClass="inputBox" /> </td>
												<td> <s:textfield name="travelLastNames[%{#stat.count-1}]" id="travelLastNames[%{#stat.count-1}]" cssClass="inputBox" /> </td>
												<td> <s:textfield name="dobs[%{#stat.count-1}]" id="dobs%{#stat.count-1}" onfocus="getDatePicker(%{#stat.count-1})" readonly="true" cssClass="inputBox" /> </td>
												<td> <s:select list="#{'M':'Male','F':'Female'}" name="genders[%{#stat.count-1}]" id="genders[%{#stat.count-1}]"   headerKey="" headerValue="---Select---" cssClass="inputBoxS" /> </td>
												<td> <s:hidden name="relations[%{#stat.count-1}]" /> <s:select list="relationList" name="relations[%{#stat.count-1}]" id="relations[%{#stat.count-1}]"  headerKey="" headerValue="---Select---" listKey="CODE" listValue="CODEDESC" disabled="true" cssClass="inputBoxS" /> </td>
												<td> <s:select list="nationalityList" name="nationalitys[%{#stat.count-1}]" id="nationalitys[%{#stat.count-1}]"  headerKey="" headerValue="---Select---" listKey="CODEDESC" listValue="CODEDESC" cssClass="inputBoxS" /> </td>
												<td align="right"> <s:textfield name="passportNo[%{#stat.count-1}]" cssClass="inputBox" maxlength="10"/> </td>
												<td align="right"> <s:textfield name="passportExpDate[%{#stat.count-1}]" id="passportExpDate%{#stat.count-1}" cssClass="inputBox datePicker passExpDate" placeholder="DD/MM/YYYY" /> </td>
											</tr>
										</s:iterator>
										<s:hidden name="selection" value="viewSave" />
										<s:hidden name="applicationNo" />
									</tbody>
								</table>
						</div>
					</div>
				</div>
			</div>
			<br />
			<div class="row">
				<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" align="center">
					<input name="back1" type="button" class="btn btn-sm btn-danger" value="Back" onclick="menuSelect('P')" />
				</div>
			</div>
		</s:form>
	</s:if>
	<s:elseif test='"updateSuccess"==selection'>
		<s:form id="travel1" name="travel1" method="post" theme="simple">
			<div class="row">
				<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
					<div class="panel panel-success">
						<div class="panel-heading" align="center">
							<s:text name="travel.correction.update" />
						</div>
					</div>
				</div>
			</div>
			<br class="clear" />
			<div class="row">
				<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" align="center">
					<input name="back1" type="button" class="btn btn-sm btn-danger"
						value="Back" onclick="menuSelect('P')" />
				</div>
			</div>
		</s:form>
	</s:elseif>
	<s:else>
		<s:if test="travelList.size()>0">
			<s:form id="travel" name="travel" method="post"
				action="viewTravel.action" theme="simple">
				<div class="row">
					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
						<s:actionerror cssStyle="color: red;" />
						<s:actionmessage cssStyle="color: green;" />
					</div>
				</div>
				<div class="row">
					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
						<div class="panel panel-primary">
							<div class="panel-heading">
								<s:text name="travel.customerInfo" />
							</div>
							<div class="panel-body" style="padding: 0px 15px 0px 15px;">
								<div class="row">
								<div class="textfield33V">
									<div class="textV"><s:text name="travel.customerName" /></div>
									<div class="tboxV"><s:property value="customerName" /></div>
								</div>
								<div class="textfield33V">
									<div class="textV"><s:text name="customer.address1" /></div>
									<div class="tboxV"><s:property value="address1" /></div>
								</div>
								<div class="textfield33V">
									<div class="textV"><s:text name="customer.address2" /></div>
									<div class="tboxV"><s:property value="address2" /></div>
								</div>
								<div class="textfield33V">
									<div class="textV"><s:text name="customer.city" /></div>
									<div class="tboxV"><s:property value="city" /></div>
								</div>
								<div class="textfield33V">
									<div class="textV"><s:text name="travel.pobox" /></div>
									<div class="tboxV"><s:property value="poBox" /></div>
								</div>
								<div class="textfield33V">
									<div class="textV"><s:text name="travel.emailId" /></div>
									<div class="tboxV"><s:property value="emailId" /></div>
								</div>
								<div class="textfield33V">
									<div class="textV"><s:text name="travel.mobileNo" /></div>
									<div class="tboxV"><s:property value="mobileNo" /></div>
								</div>
								<br class="clear" />
							</div>	
						</div>
					</div>
					<div class="panel panel-primary">
						<div class="panel-heading">
							<s:text name="travel.policyInfo" />
						</div>
						<div class="panel-body" style="padding: 0px 15px 0px 15px;">
							<s:iterator value="trPolicyList" var="trPolicyList">
							<div class="row">
								<s:if test='%{null!=#trPolicyList.POLICY_NO}'>
									<div class="textfield33V">
										<div class="textV"> <s:text name="travel.policyNo" /> </div>
										<div class="tboxV"> <s:property value="#trPolicyList.POLICY_NO" /> </div>
									</div>
								</s:if>
								<s:else>
									<div class="textfield33V">
										<div class="textV"> <s:text name="travel.quoteNo" /> </div>
										<div class="tboxV"> <s:property value="quoteNo" /> </div>
									</div>
								</s:else>
								<div class="textfield33V">
									<div class="textV"> <s:text name="travel.premium" /> </div>
									<div class="tboxV"> <s:property value="#trPolicyList.PREMIUM" /> </div>
								</div>
								<div class="textfield33V">
									<div class="textV"> <s:text name="travel.inceptiondate" /> </div>
									<div class="tboxV"> <s:property value="#trPolicyList.EFFECTIVE_DATE" /> </div>
								</div>
								<div class="textfield33V">
									<div class="textV"> <s:text name="travel.expiryDt" /> </div>
									<div class="tboxV"> <s:property value="#trPolicyList.EXPIRY_DATE" /> </div>
								</div>
								<s:if test='%{null!=#trPolicyList.LAPSED_REMARKS}'>
									<div class="textfield33V">
										<div class="textV"> <s:text name="travel.lapsremarks" /> </div>
										<div class="tboxV"> <s:property value="#trPolicyList.LAPSED_REMARKS" /> </div>
									</div>
									<div class="textfield33V">
										<div class="textV"> <s:text name="travel.lapseddate" /> </div>
										<div class="tboxV"> <s:property value="#trPolicyList.LAPSED_DATE" /> </div>
									</div>
								</s:if>
								<s:if test='%{null!=#trPolicyList.POLICY_TERM}'>
									<div class="textfield33V">
										<div class="textV"> <s:text name="travel.coverPeriod" /> </div>
										<div class="tboxV"> <s:property value="#trPolicyList.POLICY_TERM" /> Days </div>
									</div>
								</s:if>
								<br class="clear" />
							</div>
							</s:iterator>
							</div>
						</div>
						<div class="panel panel-primary">
							<div class="panel-heading">
								<s:text name="travel.policyInfo" />
							</div>
							<div class="panel-body">
								<table class="footable" width="100%" id="TravelTable">
									<thead>
										<tr>
											<th width="3%"> <s:text name="travel.sNo"/> </th>
											<th width="12.12%"> <s:text name="travel.first.name" /><font color="red">*</font> </th>
											<th width="12.12%"> <s:text name="travel.last.name" /><font color="red">*</font> </th>
											<th width="12.12%"> <s:text name="travel.dateOfBirth" /><font color="red">*</font> </th>
											<th width="12.12%"> <s:text name="travel.gender" /><font color="red">*</font> </th>
											<th width="12.12%"> <s:text name="travel.relations" /><font color="red">*</font> </th>
											<th width="12.12%"> <s:text name="travel.nationality" /><font color="red">*</font> </th>
											<th width="12.12%"><s:text name="travel.passport.number" /></th>
											<th width="12.12%"><s:text name="travel.passport.expiry.date" /></th>
										</tr>
									</thead>
									<tbody>
										<s:iterator value="travelList" var="trList" status="stat">
											<tr>
												<td align="center">
													<s:property />
													<s:hidden name="travelList[%{#stat.count-1}]" id="travelList%{#stat.count-1}" />
													<s:hidden name="serialNos[%{#stat.count-1}]" id="serialNos%{#stat.count-1}" />
												</td>
												<td> <s:textfield name="travelNames[%{#stat.count-1}]" id="travelNames[%{#stat.count-1}]" cssClass="inputBox" /> </td>
												<td> <s:textfield name="travelLastNames[%{#stat.count-1}]" id="travelLastNames[%{#stat.count-1}]" cssClass="inputBox" /> </td>
												<td> <s:textfield name="dobs[%{#stat.count-1}]" id="dobs%{#stat.count-1}" onfocus="getDatePicker(%{#stat.count-1})" readonly="true" cssClass="inputBox" /> </td>
												<td> <s:select list="#{'M':'Male','F':'Female'}" name="genders[%{#stat.count-1}]" id="genders[%{#stat.count-1}]"   headerKey="" headerValue="---Select---" cssClass="inputBoxS" /> </td>
												<td> <s:hidden name="relations[%{#stat.count-1}]" /> <s:select list="relationList" name="relations[%{#stat.count-1}]" id="relations[%{#stat.count-1}]"  headerKey="" headerValue="---Select---" listKey="CODE" listValue="CODEDESC" disabled="true" cssClass="inputBoxS" /> </td>
												<td> <s:select list="nationalityList" name="nationalitys[%{#stat.count-1}]" id="nationalitys[%{#stat.count-1}]"  headerKey="" headerValue="---Select---" listKey="CODEDESC" listValue="CODEDESC" cssClass="inputBoxS" /> </td>
												<td align="right"> <s:textfield name="passportNo[%{#stat.count-1}]" cssClass="inputBox" maxlength="10"/> </td>
												<td align="right"> <s:textfield name="passportExpDate[%{#stat.count-1}]" id="passportExpDate%{#stat.count-1}" cssClass="inputBox datePicker passExpDate" placeholder="DD/MM/YYYY" /> </td>
											</tr>
										</s:iterator>
										<s:hidden name="selection" value="viewSave" />
										<s:hidden name="applicationNo" />
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
				<br class="clear" />
				<div class="row">
					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12"
						align="center">
						<input name="back1" type="button" class="btn btn-sm btn-danger"
							value="Back" onclick="menuSelect('P')" />
						&nbsp;&nbsp;&nbsp;
						<s:submit name="Submit1" type="submit"
							cssClass="btn btn-sm btn-success" value="Save"
							onclick="fnsubmit()" />
					</div>
				</div>
			</s:form>
		</s:if>
		<s:else>
			<s:form id="travel1" name="travel1" method="post" theme="simple">
				<div class="row">
					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
						<div class="panel panel-success">
							<div class="panel-heading" align="center">
								<s:text name="travel.correction.reject" />
							</div>
						</div>
					</div>
				</div>
				<br class="clear" />
				<div class="row">
					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12"
						align="center">
						<input name="back1" type="button" class="btn btn-sm btn-danger"
							value="Back" onclick="menuSelect('P')" />
					</div>
				</div>
			</s:form>
		</s:else>
	</s:else>
</body>
<script type="text/javascript">
function menuSelect(obj) {	
	document.forms[0].menuType.value=obj;
	document.forms[0].loginId.value='<s:property value="#session.user"/>';
	document.forms[0].action='${pageContext.request.contextPath}/initReport.action';	
	document.forms[0].submit();
}
function fnsubmit() {
	document.travel.action = "${pageContext.request.contextPath}/viewTravel.action";
	document.travel.submit();
}
$(function() {
	try {
		 $('.passExpDate').datepicker({
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
</html>