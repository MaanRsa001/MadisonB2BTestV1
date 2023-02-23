<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<div class="tablerow" style="padding:10px; background:#F8F8F8">
	<s:set name="coverPremium" value='"0".equals(#session.ContentType) || "admin".equalsIgnoreCase(#session.usertype) || "RA".equalsIgnoreCase(menuType)'/>
	<div class="panel panel-primary">
		<div class="panel-heading txtB">
			<s:text name="label.premium.info"/>
			<s:if test='%{!"0".equals(#session.ContentType)}'>
				<span class="pullRight">
					<s:text name="home.packagePlan"/>&nbsp;:&nbsp;
					<s:property value="%{#session.ContentTypeName}"/>
				</span>
			</s:if>
		</div>
		<div class="panel-body">					
			<div class="boxcontent">
   				<table class="footable">
   					<tbody>     						
						<s:if test='"Y".equals(minPremiumYN)'>
							<tr><td colspan="2"><font color="red"><s:text name="label.home.minimum.premium.threshold">&nbsp</s:text><s:property value="getText('{0,number,#,##0.00}',{@java.lang.Double@valueOf(actualPremium)})"/>&nbsp<s:text name="label.home.minimum.premium.threshold.charged"></s:text></font></td></tr>
						</s:if>
						<s:if test='!(!"".equalsIgnoreCase(coverReferralRemarks) && !"admin".equalsIgnoreCase(userType) && !"RA".equalsIgnoreCase(menuType))'>
							<tr class="tableHeading">
								<td colspan="2" align="center"><s:text name="label.totals"/></td>
							</tr>
						</s:if>
						<s:if test='"Y".equals(paCoverStatus) && !"0".equals(paPremium)'>
							<tr>
								<td>Personal Accident</td>
								<td align="right">
									<s:if test='!"".equalsIgnoreCase(coverReferralRemarks) && !"admin".equalsIgnoreCase(userType) && !"RA".equalsIgnoreCase(menuType)'>
										Referral
									</s:if>
									<s:else>
										<b><s:property value="getText('{0,number,#,##0.00}',{@java.lang.Double@valueOf(paPremium)})"/></b>
									</s:else>
								</td>
								<td align="center">
									<s:if test='!(!"".equalsIgnoreCase(coverReferralRemarks) && !"admin".equalsIgnoreCase(userType) && !"RA".equalsIgnoreCase(menuType))'>
										<button type="button" class="btn btn-sm btn-primary" data-toggle="modal" data-refresh="true" href="${pageContext.request.contextPath}/coverPremiumInfoHome.action?reqFromq=personalAccident&menuType=${menuType}&quoteNo=${quoteNo}&applicationNo=${applicationNo}" data-target="#modalPA">View</button>
										<div class="modal fade" data-refresh="true" id="modalPA" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
											<div class="modal-dialog modal-lg">
												<div class="modal-content">
													<div class="modal-header">
														<h4 class="modal-title"> <s:text name="label.premium.info"/> </h4>
													</div>
													<div class="modal-body">
														<div class="te"></div>
													</div>
												</div>
											</div>
										</div>
									</s:if>
									<s:submit type="button" value="Edit" cssClass="btn btn-sm btn-warning" onclick="fnsubmit1('paCoverHome')"/>
								</td>
							</tr>
						</s:if>
						<s:if test='"Y".equals(tractorCoverStatus) && !"".equals(motorPremium)'>
							<tr>
								<td>Tractor</td>
								<td align="right">
									<s:if test='!"".equalsIgnoreCase(coverReferralRemarks) && !"admin".equalsIgnoreCase(userType) && !"RA".equalsIgnoreCase(menuType)'>
										Referral
									</s:if>
									<s:else>
										<b><s:property value="getText('{0,number,#,##0.00}',{@java.lang.Double@valueOf(motorPremium)})"/></b>
									</s:else>
								</td>
								<td align="center">
									<s:if test='!(!"".equalsIgnoreCase(coverReferralRemarks) && !"admin".equalsIgnoreCase(userType) && !"RA".equalsIgnoreCase(menuType))'>
										<button type="button" class="btn btn-sm btn-primary" data-toggle="modal" data-refresh="true" href="${pageContext.request.contextPath}/coverPremiumInfoHome.action?reqFromq=tractor&menuType=${menuType}&quoteNo=${quoteNo}&applicationNo=${applicationNo}" data-target="#modalTractor">View</button>
										<div class="modal fade" data-refresh="true" id="modalTractor" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
											<div class="modal-dialog modal-lg">
												<div class="modal-content">
													<div class="modal-header">
														<h4 class="modal-title"> <s:text name="label.premium.info"/> </h4>
													</div>
													<div class="modal-body">
														<div class="te"></div>
													</div>
												</div>
											</div>
										</div>
									</s:if>
									<s:submit type="button" value="Edit" cssClass="btn btn-sm btn-warning" onclick="fnsubmit1('edittractorHome')"/>
								</td>
							</tr>
						</s:if>
						<s:if test='!(!"".equalsIgnoreCase(coverReferralRemarks) && !"admin".equalsIgnoreCase(userType) && !"RA".equalsIgnoreCase(menuType))'>
							<s:if test='%{actualPremium!=null && !"".equalsIgnoreCase(actualPremium) && @java.lang.Double@valueOf(actualPremium)!=0}'>
								<tr>
									<td><s:label value="Base Premium"/></td>
									<td align="right"><b><s:property value="getText('{0,number,#,##0.00}',{@java.lang.Double@valueOf(actualPremium)})"/></b></td>
								</tr>
							</s:if>
							<s:if test='%{discountPremium!=null && !"".equalsIgnoreCase(discountPremium) && @java.lang.Double@valueOf(discountPremium)!=0}'>
								<tr>
									<td><s:label value="Discount Premium"/><b>&nbsp;(<s:property value="discountPercent"/>%)</b> </td>
									<td align="right"><b><s:property value="getText('{0,number,#,##0.00}',{@java.lang.Double@valueOf(discountPremium)})"/></b></td>
								</tr>
							</s:if>
							<s:if test='%{actualOptionalPremium!=null && !"".equalsIgnoreCase(actualOptionalPremium) && @java.lang.Double@valueOf(actualOptionalPremium)!=0}'>
								<tr>
									<td><s:label value="Additional Cover Premium"/></td>
									<td align="right"><b><s:property value="getText('{0,number,#,##0.00}',{@java.lang.Double@valueOf(actualOptionalPremium)})"/></b></td>
								</tr>
							</s:if>
							<tr>
								<td><s:label key="total.premium"/></td>
								<td align="right"><b><s:property value="getText('{0,number,#,##0.00}',{@java.lang.Double@valueOf(totalPremium)})"/></b></td>
							</tr>
							<s:set var="total" value="%{totalPremium}" scope="page"/>
							<s:if test='%{"RA".equals(menuType) && personalInfo[0].EXCESS_PREMIUM!=null}'>
								<tr>
									<td><s:label key="home.loadingOrDiscountPremium"/></td>
									<td align="right"><b>${personalInfo[0].EXCESS_SIGN} &nbsp;<s:property value="getText('{0,number,#,##0.00}',{@java.lang.Double@valueOf(personalInfo[0].EXCESS_PREMIUM)})"/></b></td>
								</tr>
								<s:if test='"+".equals(personalInfo[0].EXCESS_SIGN)'>
									<s:set var="total" value="%{@java.lang.Double@valueOf(personalInfo[0].EXCESS_PREMIUM)+@java.lang.Double@valueOf(totalPremium)}" scope="page"/>
								</s:if>
								<s:else>
									<s:set var="total" value="%{@java.lang.Double@valueOf(totalPremium)-@java.lang.Double@valueOf(personalInfo[0].EXCESS_PREMIUM)}" scope="page"/>
								</s:else>
							</s:if>
							<tr>
								<td><s:label key="home.policyFee"/><b>&nbsp;(3%)</b></td>
								<td align="right"><b><s:property value="getText('{0,number,#,##0.00}',{@java.lang.Double@valueOf(policyFee)})"/></b></td>
								<s:set var="total" value="%{@java.lang.Double@valueOf(policyFee)+@java.lang.Double@valueOf(#attr.total)}" scope="page"/>
							</tr>
							<tr>
								<td><s:label key="label.total.premium.payable"/></td>
								<td align="right"><b><s:property value="getText('{0,number,#,##0.00}',{@java.lang.Double@valueOf(#attr.total)})"/></b></td>
							</tr>
						</s:if>
     				</tbody>
     			</table>
			</div>
		</div>
	</div>
</div>
