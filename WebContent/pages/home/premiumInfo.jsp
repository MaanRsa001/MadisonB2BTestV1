<%@ page isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<!DOCTYPE html>
<html <s:if test="locale.language == 'ar'">dir="rtl"</s:if> >
	<head>
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/bootstrap/css/bootstrap.min.css" />
		<link href="<%=request.getContextPath()%>/bootstrap/css/table-res.css" rel="stylesheet" type="text/css" />
		<link href="<%=request.getContextPath()%>/bootstrap/css/style.css" rel="stylesheet" type="text/css" />
		<style type="text/css">
		.tableHeading {
			background: #0078ae url(images/ui-bg_glass_45_0078ae_1x400.png) 50% 50% repeat-x;
			color: #ffffff; font-weight: bold; font-size: 14px;
		}
		.footable > thead > tr > th, .footable > thead > tr > td, .footable > tfoot > tr > th, .footable > tfoot > tr > td {
			font-size: 14px;
		}
		</style>
	</head>
	<body>
		
		<div class="panel panel-primary">
			<div class="panel-heading">
				<s:text name="label.premium.info"/>
			</div>
			<div class="panel-body">
				<div style="overflow-x:scroll">
					<s:if test='"home".equalsIgnoreCase(reqFromq)'>
						<s:set name="coverPremium" value='"0".equals(#session.ContentType) || "admin".equalsIgnoreCase(#session.usertype) || "RA".equalsIgnoreCase(menuType)'/>
						<table class="footable" style="width: 100%; margin: 0 auto;">
							<thead>
							<tr>
								<th width="40%"><s:text name="coverage.details"/></th>
								<th width="30%"><s:text name="coverage.sumInsured"/></th>
								<th width="30%" align="right"><s:text name="label.premium"/> in [<s:property value="#session.BrokerDetails.CurrencyAbb"/>]</th>
							</tr>
							</thead>
							<tbody>
								<s:iterator var="prem" value="premiumList">
									<s:if test='%{"Y".equals(#prem.SUB_COVERAGES)}'>
										<s:set value="0" var="raj"/>
										<s:iterator var="subprem" value="subPremium">
											<s:if test='%{#prem.COVERAGES_ID==#subprem.COVERAGES_ID}'>
												<s:if test='%{#raj==0}'>
												<tr class="tableHeading">
													<td colspan="3"><s:property value="%{#prem.coverages_display_name}"/></td>
												</tr>
												<s:set value="1" var="raj"/>
												</s:if>
												<tr>
													<td>${coverages_display_name}</td>
													<td align="right">
														<b>
															<s:property value="COVERAGES_COVERED_EMPLOYEES1"/>
															<s:if test='%{!"dropdown".equalsIgnoreCase(#subprem.SUB_CONTROL_TYPE) && !"Radio".equalsIgnoreCase(#subprem.SUB_CONTROL_TYPE)}'>
																<s:property value="#session.BrokerDetails.CurrencyAbb"/>
															</s:if>
														</b>
													</td>
													<td align="right">
														<s:if test='#coverPremium'>
															<b><s:property value="getText('{0,number,#,##0.00}',{PREMIUM_AMOUNT})"/></b>
														</s:if>
													</td>
												</tr>
											</s:if>
										</s:iterator>
									</s:if>
									<s:else>
										<s:if test='PREMIUM_AMOUNT!=null'>
											<tr>
												<td>${coverages_display_name}</td>
												<td align="right"><b>
													<s:property value="COVERAGES_SUMINSURED" />
													<s:if test='%{!"dropdown".equalsIgnoreCase(#prem.SUM_INSURED_CONTROL_TYPE) && !"Radio".equalsIgnoreCase(#prem.SUM_INSURED_CONTROL_TYPE)}'>
														&nbsp;<s:property value="#session.BrokerDetails.CurrencyAbb"/>
													</s:if>
													</b></td>
												<td align="right">
													<s:if test='#coverPremium'>
														<b><s:property value="getText('{0,number,#,##0.00}',{PREMIUM_AMOUNT})"/></b>
													</s:if>
												</td>
											</tr>
										</s:if>
									</s:else>
								</s:iterator>
							</tbody>
						</table>
					</s:if>
					<s:elseif test='"personalAccident".equalsIgnoreCase(reqFromq)'>
						<table width="100%" class="footable">
							<thead>
								<tr>
									<th> S.No. </th>
									<th> Name </th>
									<th> Relationship </th>
									<th> Occupation </th>
									<th> Sum Insured </th>
									<th> Annual Income </th>
									<th> Rate </th>
									<th> Premium </th>
									<th> Medical Extension Rate </th>
									<th> Medical Extension Premium </th>
									<th> Cost of Travel Rate </th>
									<th> Cost of Travel Premium </th>
									<th> Supporting items Extension Rate </th>
									<th> Supporting items Extension Premium </th>
								</tr>
							</thead>
							<tbody>
								<s:iterator value="paInsuredDetailsList" var="var" status="status">
									<tr>
										<td> <s:property value="#status.count"/> </td>
										<td> <s:property value="#var.INSURED_NAME"/> </td>
										<td> <s:property value="#var.RELATIONNAME"/> </td>
										<td> <s:property value="#var.OCCUPATIONNAME"/> </td>
										<td align="right"> <s:property value="getText('number.format.amount2',{#var.SUMINSURED})"/> </td>
										<td align="right"> <s:property value="getText('number.format.amount2',{#var.ANNUALINCOME})"/> </td>
										<td align="right">
											<s:if test='%{"admin".equalsIgnoreCase(#session.usertype) && "Y".equals(premiumEditStatus)}'>
												<s:textfield name="paInsuredRateList[%{#status.index}]" value="%{hasActionErrors()?paInsuredRateList[#status.index]:#var.RATE}" cssClass="inputBox" cssStyle="width: 80%; text-align:right;" onkeyup="checkDecimals2_6(this);" />
												<s:hidden name="paInsuredIdList[%{#status.index}]" value="%{#var.INSURED_ID}"/>
											</s:if>
											<s:else> 
												<s:property value="#var.RATE" />
											</s:else>
										</td>
										<td align="right"> <s:property value="getText('number.format.premium2',{#var.PREMIUM})" /> </td>
										<td align="right">
											<s:if test='%{"admin".equalsIgnoreCase(#session.usertype) && "Y".equals(premiumEditStatus)}'>
												<s:textfield name="paMedExtnRateList[%{#status.index}]" value="%{hasActionErrors()?paMedExtnRateList[#status.index]:#var.MEDICAL_EXTENSION_RATE}" cssClass="inputBox" cssStyle="width: 80%; text-align:right;" onkeyup="checkDecimals4_6(this);" />
											</s:if>
											<s:else> 
												<s:property value="#var.MEDICAL_EXTENSION_RATE" />
											</s:else>
										</td>
										<td align="right"> <s:property value="getText('number.format.premium2',{#var.MEDICAL_EXTENSION_PREMIUM})" /> </td>
										<td align="right">
											<s:if test='%{"admin".equalsIgnoreCase(#session.usertype) && "Y".equals(premiumEditStatus)}'>
												<s:textfield name="paTravelCostRateList[%{#status.index}]" value="%{hasActionErrors()?paTravelCostRateList[#status.index]:#var.TRAVEL_COST_RATE}" cssClass="inputBox" cssStyle="width: 80%; text-align:right;" onkeyup="checkDecimals4_6(this);" />
											</s:if>
											<s:else> 
												<s:property value="#var.TRAVEL_COST_RATE" />
											</s:else>
										</td>
										<td align="right"> <s:property value="getText('number.format.premium2',{#var.TRAVEL_COST_PREMIUM})" /> </td>
										<td align="right">
											<s:if test='%{"admin".equalsIgnoreCase(#session.usertype) && "Y".equals(premiumEditStatus)}'>
												<s:textfield name="paSupportRateList[%{#status.index}]" value="%{hasActionErrors()?paSupportRateList[#status.index]:#var.SUPPORTITEM_EXTENSION_RATE}" cssClass="inputBox" cssStyle="width: 80%; text-align:right;" onkeyup="checkDecimals4_6(this);" />
											</s:if>
											<s:else> 
												<s:property value="#var.SUPPORTITEM_EXTENSION_RATE" />
											</s:else>
										</td>
										<td align="right"> <s:property value="getText('number.format.premium2',{#var.SUPPORTITEM_EXTENSION_PREMIUM})" /> </td>
									</tr>
								</s:iterator>
							</tbody>
						</table>
					</s:elseif>
					<s:elseif test='"tractor".equalsIgnoreCase(reqFromq)'>
						<table class="footable" style="width: 100%; margin: 0 auto;">
							<thead>
							<tr>
								<th width="50%"><s:text name="coverage.details"/></th>
								<th width="25%"><s:text name="coverage.sumInsured"/></th>
								<th width="10%">Rate</th>
								<th width="15%" align="right"><s:text name="label.premium"/> in [<s:property value="#session.BrokerDetails.CurrencyAbb"/>]</th>
							</tr>
							</thead>
							<tbody>
								<s:set name="rownum" value="0"/>
								<s:iterator var="var" value="tractorCoverList" status="status">
									<s:if test='%{"admin".equalsIgnoreCase(#session.usertype) || (#var.Premium!="0")}'>
										<tr>
											<td> <s:property value="#var.Coverages_Name"/> </td>
											<td align="right">
												<s:if test='#var.ORGSUMINSURED!=null'>
													<s:text name="number.format"><s:param value="#var.ORGSUMINSURED"/></s:text>
												</s:if>
											</td>
											<td align="right">
												<s:if test='%{"admin".equalsIgnoreCase(#session.usertype) && "Y".equals(premiumEditStatus)}'>
													<s:if test='"Y".equals(#var.DISPLAY_STATUS)'>
														<s:textfield name="tractorRateList[%{#rownum}]" value="%{hasActionErrors()?tractorRateList[#rownum]:#var.ORGRATE}" cssClass="inputBox" cssStyle="width: 80%; text-align:right;" onkeyup="checkDecimals2_6(this);"/>
														<s:hidden name="tractorSubCoverList[%{#rownum}]" value="%{#var.COVERAGES_SUB_ID}"/>
														<s:set name="rownum" value="%{#rownum+1}"/>
													</s:if>
												</s:if>
												<s:else>
													<s:property value="#var.ORGRATE"/>
												</s:else>
											</td>
											<td align="right"> <s:property value="getText('number.format',{#var.Premium})" /> </td>
										</tr>
									</s:if>
								</s:iterator>
							</tbody>
						</table>
					</s:elseif>
				</div>
			</div>
		</div>
	</body>
</html>