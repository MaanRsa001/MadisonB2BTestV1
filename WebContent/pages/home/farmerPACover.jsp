<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ page import="java.util.List,java.util.ArrayList,java.util.Map"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html >
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<SCRIPT type="text/javascript">
		function commas(obj) {
			alert(obj);
			if(document.quotation.obj!=null && document.quotation.obj.value!="") {
				document.quotation.obj.value = comma(document.quotation.obj.value);
			}
		}
		</SCRIPT>
		<style type="text/css">
		
		</style>
	</head>
	<body>
		<s:if test='%{!"admin".equals(#session.usertype)}'>
			<s:set var="disable" value="%{menuType=='RA'}"/>
		</s:if>
		<s:set var="format" value="%{'number.format'}"/>
		<div class="table0">
			<div class="tablerow">
			
			<div class="table1">
				<div class="tablerow">
					<span style="color:red;"> <s:actionerror/> </span>
				</div>
				<div class="tablerow">
					<s:include value="/pages/home/generalInfo.jsp" />
				</div>
				<div class="tablerow">
					<s:include value="/pages/home/coverPremiumInfo.jsp"/>
				</div>
				<br class="clear"/>
				<s:form name="quotation" theme="simple">
					<div class="tablerow">
						<div class="panel panel-primary">
							<div class="panel-heading">
								<s:text name="Personal Accident"/>
							</div>
							<div class="panel-body">					
								<div class="tablerow">
									<div class="textfield33">
										<div class="text">
											<s:text name="Policy Type"/><font color="red">*</font>
										</div>
										<div class="tbox">
											<s:select name="paPolicyType" id="paPolicyType" list="paPolicyTypeList" listKey="CODE" listValue="CODEDESC" headerKey="" headerValue="---Select---" cssClass="inputBoxS" onchange="togglePolicyType();" disabled="disable"/>
										</div>
									</div>
									<br class="clear"/>
								</div>
							</div>
						</div>
						<div class="panel panel-primary">
							<div class="panel-heading">
								<s:text name="Insured People"/>
							</div>
							<div class="panel-body">
								<s:set name="paInsuredDtlsList" value="%{paInsuredDetailsList}"/>
								<s:if test='#paInsuredDtlsList.size()>0'>
									<div class="tablerow">
										<table width="100%" class="footable">
											<thead>
											<tr>
												<th> S.No. </th>
												<th> Name </th>
												<th> Relationship </th>
												<th> Occupation </th>
												<th> Sum Insured </th>
												<th> Annual Income </th>
												<th> Edit </th>
												<th> Remove </th>
											</tr>
											</thead>
											<tbody>
												<s:iterator value="#paInsuredDtlsList" var="var" status="status">
													<tr>
														<td> <s:property value="#status.count"/> </td>
														<td> <s:property value="#var.INSURED_NAME"/> </td>
														<td> <s:property value="#var.RELATIONNAME"/> </td>
														<td> <s:property value="#var.OCCUPATIONNAME"/> </td>
														<td align="right"> <s:property value="getText('number.format.amount2',{#var.SUMINSURED})"/> </td>
														<td align="right"> <s:property value="getText('number.format.amount2',{#var.ANNUALINCOME})"/> </td>
														<td align="center"> 
															<s:submit type="button" cssClass="btn btn-sm btn-warning" onclick="modifyInsuredDetails('%{#var.INSURED_ID}','editpaCoverHome')" disabled="#disable"> <i class="glyphicon glyphicon-edit"></i>  </s:submit>
														</td>
														<td align="center">
															<s:submit type="button" cssClass="btn btn-sm btn-danger" onclick="modifyInsuredDetails('%{#var.INSURED_ID}','deletepaCoverHome')" disabled="#disable"> <i class="glyphicon glyphicon-remove"></i> </s:submit>
														</td>
													</tr>
												</s:iterator>
											</tbody>
										</table>
									</div>
									<br class="clear" />
								</s:if>		
								<div class="tablerow">
									<div class="textfield33">
										<div class="text">
											<s:text name="Name"/><font color="red">*</font>
										</div>
										<div class="tbox">
											<s:textfield name="paName" id="paName" cssClass="inputBox" disabled="disable"/>
										</div>
									</div>
									<div class="textfield33">
										<div class="text">
											<s:text name="DOB"/><font color="red">*</font>
										</div>
										<div class="tbox">
											<s:textfield id="paDob" name="paDob" cssClass="inputBox datepicker" readonly="true" disabled="#disable" />
										</div>
									</div>
									<div class="textfield33" style="display: none;">
										<div class="text">
											<s:text name="Insurer"/><font color="red">*</font>
										</div>
										<div class="tbox">
											<s:select name="paIndurer" id="paIndurer" list="paInsurerList" listKey="CODE" listValue="CODEDESC" headerKey="" headerValue="---Select---" cssClass="inputBoxS" disabled="disable"/>
										</div>
									</div>
									<div class="textfield33">
										<div class="text">
											<s:text name="Relationship"/><font color="red">*</font>
										</div>
										<div class="tbox">
											<s:select name="paRelationship" id="paRelationship" list="paReliationShipList" listKey="CODE" listValue="CODEDESC" headerKey="" headerValue="---Select---" cssClass="inputBoxS" onchange="getRelationShip();" disabled="disable"/>
										</div>
									</div>
									<div class="textfield33" id="self1" style="display: none;">
										<div class="text">
											<s:text name="Father's First Name"/><font color="red">*</font>
										</div>
										<div class="tbox">
											<s:textfield name="paFatherFirstName" id="paFatherFirstName" cssClass="inputBox" disabled="disable"/>
										</div>
									</div>
									<div class="textfield33" id="self2" style="display: none;">
										<div class="text">
											<s:text name="Father's Middle Name"/><font color="red">*</font>
										</div>
										<div class="tbox">
											<s:textfield name="paFatherMiddleName" id="paFatherMiddleName" cssClass="inputBox" disabled="disable"/>
										</div>
									</div>
									<div class="textfield33" id="self3" style="display: none;">
										<div class="text">
											<s:text name="Father's Last Name"/><font color="red">*</font>
										</div>
										<div class="tbox">
											<s:textfield name="paFatherLastName" id="paFatherLastName" cssClass="inputBox" disabled="disable"/>
										</div>
									</div>
									<div class="textfield33">
										<div class="text"> <s:text name="Pre-Existing Disability" /> </div>
										<div class="tbox">
											<s:radio name="paExitingDisability" id="paExitingDisability" list="#{'Y':'Yes','N':'No'}" value='%{paExitingDisability==null||"".equals(paExitingDisability)?"N":paExitingDisability}' disabled="#disable" onchange="getPreExitDisable(this.value);" />
										</div>
									</div>
									<div class="textfield33" id="diablilityDescDiv" style="display: none;">
										<div class="text"> <s:text name="Disability Description"></s:text> </div>
										<div class="tbox">
											<s:textfield name="paDisabilityDesc" id="paDisabilityDesc" cssClass="inputBox" onkeyup="textLimit(this,'200')" disabled="#disable"/>
										</div>
									</div>
									<div class="textfield33" id="paOccupationDiv">
										<div class="text">
											<s:text name="Occupation"/><font color="red">*</font>
										</div>
										<div class="tbox">
											<s:select name="paOccupation" id="paOccupation" list="paOccupationList" listKey="CODE" listValue="CODEDESC" headerKey="" headerValue="---Select---" cssClass="inputBoxS" disabled="disable"/>
										</div>
									</div>
									<div class="textfield33" id="paTableOfBenifitsDiv">
										<div class="text">
											<s:text name="Table of Benefits"/><font color="red">*</font>
										</div>
										<div class="tbox">
											<s:select name="paTableOfBenifits" id="paTableOfBenifits" list="paTableBenifitsList" listKey="CODE" listValue="CODEDESC" headerKey="" headerValue="---Select---" cssClass="inputBoxS" disabled="disable"/>
										</div>
									</div>
									<div class="textfield33" id="paSumInsuredDiv">
										<div class="text">
											<s:text name="Sum Insured"/><font color="red">*</font>
										</div>
										<div class="tbox">
											<s:textfield name="paSumInsured" id="paSumInsured" cssClass="inputBox" disabled="disable"/>
										</div>
									</div>
									<div class="textfield33" id="paAnnualIncomeDiv">
										<div class="text">
											<s:text name="Annual Income"/><font color="red">*</font>
										</div>
										<div class="tbox">
											<s:textfield name="paAnnualIncome" id="paAnnualIncome" cssClass="inputBox" disabled="disable"/>
										</div>
									</div>
									<div class="textfield33" id="paMedicalExtnDiv">
										<div class="text">
											<s:text name="Medical Extension" /><font color="red">*</font>
										</div>
										<div class="tbox">
											<s:select name="paMedicalExtn" id="paMedicalExtn" list="paMedicalExtnList" listKey="CODE" listValue="CODEDESC" cssClass="inputBoxS" disabled="#disable"/>
										</div>
									</div>
									<div class="textfield33" id="paCostOfTravelDiv">
										<div class="text">
											<s:text name="Cost of Travel" /><font color="red">*</font>
										</div>
										<div class="tbox">
											<s:select name="paCostOfTravel" id="paCostOfTravel" list="paCostOfTravelList" listKey="CODE" listValue="CODEDESC" cssClass="inputBoxS" disabled="#disable"/>
										</div>
									</div>
									<div class="textfield33" id="paSupportingItemDiv">
										<div class="text">
											<s:text name="Supporting items Extension" /><font color="red">*</font>
										</div>
										<div class="tbox">
											<s:select name="paSupportingItem" id="paSupportingItem" list="paCostOfTravelList" listKey="CODE" listValue="CODEDESC" cssClass="inputBoxS" disabled="#disable"/>
										</div>
									</div>
									<div class="textfield33" id="paOptionTypeDiv">
										<div class="text">
											<s:text name="Option Type"/><font color="red">*</font>
										</div>
										<div class="tbox">
											<s:select name="paOptionType" id="paOptionType" list="paOptionTypeList" listKey="CODE" listValue="CODEDESC" headerKey="" headerValue="---Select---" cssClass="inputBoxS" disabled="disable"/>
										</div>
									</div>
									<s:hidden name="paInsuredId" id="paInsuredId"/>					
								</div>
								<br class="clear" />
								<div class="tablerow" align="right">
									<s:submit type="button" value='%{(paInsuredId!=null && !"".equals(paInsuredId))?"Update":"Add More"}' cssClass="btn btn-sm btn-primary" onclick="fnsubmit('addpaCoverHome');"/>
								</div>
							</div>
						</div>
						<div class="panel panel-primary">
							<div class="panel-heading">
								<s:text name="Previous Insurance Company Details"/>
							</div>
							<div class="panel-body">
								<div class="tablerow">
									<div class="textfield33">
										<div class="text">
											<s:text name="Is Rollover Policy"/><font color="red">*</font>
										</div>
										<div class="tbox">
											<s:radio name="paIsRollPolicyYN" id="paIsRollPolicyYN" list="#{'Y':'Yes','N':'No'}" value='%{paIsRollPolicyYN==null||"".equals(paIsRollPolicyYN)?"N":paIsRollPolicyYN}' disabled="#disable"  />
										</div>
									</div>
									<div class="textfield33">
										<div class="text">
											<s:text name="Previous Policy No."/><font color="red">*</font>
										</div>
										<div class="tbox">
											<s:textfield name="paPrevPolicyNo" id="paPrevPolicyNo" cssClass="inputBox" disabled="#disable"/>
										</div>
									</div>
									<div class="textfield33">
										<div class="text">
											<s:text name="Previous Policy Expiry Date"/><font color="red">*</font>
										</div>
										<div class="tbox">
											<s:textfield id="paPrevExpiryDate" name="paPrevExpiryDate" cssClass="inputBox datepicker" readonly="true" disabled="#disable"/>
										</div>
									</div>
									<div class="textfield33">
										<div class="text">
											<s:text name="Previous Insurance Company"/><font color="red">*</font>
										</div>
										<div class="tbox">
											<s:select name="paPrevInsCompany" id="paPrevInsCompany" list="paPrevInsuranceList" listKey="CODE" listValue="CODEDESC" headerKey="" headerValue="---Select---" cssClass="inputBoxS" disabled="#disable"/>
										</div>
									</div>
									<div class="textfield33">
										<div class="text">
											<s:text name="Previous Claim Amount Made"/><font color="red">*</font>
										</div>
										<div class="tbox">
											<s:textfield name="paPrevClaimAmount" id="paPrevClaimAmount" cssClass="inputBox" disabled="#disable"/>
										</div>
									</div>
									<br class="clear" />
								</div>
							</div>
						</div>			
					</div>
					<div class="tablerow">
						<div align="center">
							<s:submit type="button" value="Back" cssClass="btn btn-sm btn-danger" onclick="fnsubmit('getBackHome');"/>
							<s:submit type="button" value="Proceed" cssClass="btn btn-sm btn-success" onclick="disableForm(this.form,false,'');fnsubmit('updatepaCoverHome');"/>
				       	</div>
						<s:hidden name="quoteNo" />
						<s:hidden name="applicationNo" />
						<s:hidden name="menuType"/>
					</div>
				</s:form>
			</div>
			</div>
		</div>		
		<script type="text/javascript">
			function modifyInsuredDetails(insuredId, action) {
				document.quotation.paInsuredId.value = insuredId;
				fnsubmit(action);
			}
			function fnsubmit(action) {
				document.quotation.action = "${pageContext.request.contextPath}/" + action;
				document.quotation.submit();
			}
			function getRelationShip() {
				var val = document.getElementById('paRelationship').value;
				if (val == '1') {
					document.getElementById('self1').style.display = "";
					document.getElementById('self2').style.display = "";
					document.getElementById('self3').style.display = "";
				} else {
					document.getElementById('self1').style.display = "none";
					document.getElementById('self2').style.display = "none";
					document.getElementById('self3').style.display = "none";
				}
			}
			function togglePolicyType() {
				var val = document.getElementById('paPolicyType').value;
				if(val == "2") {
					document.getElementById('paOccupationDiv').style.display = "none";
					document.getElementById('paTableOfBenifitsDiv').style.display = "none";
					document.getElementById('paSumInsuredDiv').style.display = "none";
					document.getElementById('paAnnualIncomeDiv').style.display = "none";
					document.getElementById('paMedicalExtnDiv').style.display = "none";
					document.getElementById('paCostOfTravelDiv').style.display = "none";
					document.getElementById('paSupportingItemDiv').style.display = "none";
					
					document.getElementById('paOptionTypeDiv').style.display = "";
				} else {
					document.getElementById('paOccupationDiv').style.display = "";
					document.getElementById('paTableOfBenifitsDiv').style.display = "";
					document.getElementById('paSumInsuredDiv').style.display = "";
					document.getElementById('paAnnualIncomeDiv').style.display = "";
					document.getElementById('paMedicalExtnDiv').style.display = "";
					document.getElementById('paCostOfTravelDiv').style.display = "";
					document.getElementById('paSupportingItemDiv').style.display = "";
					
					document.getElementById('paOptionTypeDiv').style.display = "none";
				}
			}
			function getPreExitDisable(val) {
				if ( val == 'Y') {
					document.getElementById('diablilityDescDiv').style.display = "";
				} else {
					document.getElementById('diablilityDescDiv').style.display = "none";
					document.getElementById('paDisabilityDesc').value = "";
				}
			}
			try {
				togglePolicyType();
				getPreExitDisable('<s:property value="paExitingDisability"/>');
				getRelationShip();
			} catch(e){ console.debug(e); }
		</script>
	</body>
</html>
