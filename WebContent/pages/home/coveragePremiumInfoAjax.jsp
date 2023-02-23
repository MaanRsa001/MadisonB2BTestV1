<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:set name="premiumDisplay" value="%{premiumDisplayVal}"/>
<div class="row">
	<div class="col-md-12">
		<table class="table" style="width: 100%">
			<thead>
				<tr align="center">
	                <th style="background-color:#261e6a;color:white;width: 30%;"><s:text name="coverage.details"/></th>
	                <th style="background-color:#261e6a;color:white;width: 20%;"><s:text name="coverage.sumInsured"/> (ZMW)</th>
	                <th style="background-color:#261e6a;color:white;width: 15%;">Base Rate (%)</th>
	                <th style="background-color:#261e6a;color:white;width: 15%;"><s:text name="label.premium"/> (ZMW)</th>
	                <th style="background-color:#261e6a;color:white;width: 20%;">Add&nbsp;Covers</th>
                </tr>
            </thead>
            <tbody>
            	<s:iterator var="list" value="schemeListNew" status="stat">
					<s:set var="subcount" value="0"/>
					<s:set var="maincount" value="0"/>
					<s:if test='premiumList.size()>0'>
						<s:iterator var="prem" value="premiumList">
							<s:if test='"11".equalsIgnoreCase(schemeId) && #maincount==0'>
								<tr class="tableHeading">
									<s:if test='%{#prem.coverages_id=="87" || #prem.coverages_id=="88"|| #prem.coverages_id=="89"|| #prem.coverages_id=="90"|| #prem.coverages_id=="91"|| #prem.coverages_id=="92"}'>
										<td colspan="5" align="center" style="background-color:#261e6a;color:white;"><b><s:property value="%{#list.SCHEME_NAME}"/>&nbsp;&nbsp;-&nbsp;&nbsp;<s:text name="Terms Of Coverage"/></b></td>
										<s:set var="maincount" value="1"/>
									</s:if>
								</tr>
							</s:if>
							<s:elseif test='"11".equalsIgnoreCase(schemeId) && #subcount==0'>
								<tr class="tableHeading">
									<s:if test='%{#prem.coverages_id=="93" || #prem.coverages_id=="94" || #prem.coverages_id=="95"|| #prem.coverages_id=="96"|| #prem.coverages_id=="97"|| #prem.coverages_id=="98"|| #prem.coverages_id=="99"||#prem.coverages_id=="100" || #prem.coverages_id=="101"|| #prem.coverages_id=="102"|| #prem.coverages_id=="103"|| #prem.coverages_id=="104"|| #prem.coverages_id=="105"}'>
										<td colspan="5" align="center" style="background-color:#261e6a;color:white;"><b><s:property value="%{#list.SCHEME_NAME}"/>&nbsp;&nbsp;-&nbsp;&nbsp;<s:text name="Table Of Benefits"/></b></td>
										<s:set var="subcount" value="1"/>
									</s:if>
								</tr>
							</s:elseif>
							<s:else>
								<s:if test='%{"B".equals(COVERAGES_TYPE) && #maincount==0}'>
									<tr class="tableHeading">
										<td colspan="5" align="center" style="background-color:#261e6a;color:white;"><b><s:property value="%{#list.SCHEME_NAME}"/>&nbsp;&nbsp;-&nbsp;&nbsp;<s:text name="label.main.cover"/></b></td>
									</tr>							
									<s:set var="maincount" value="1"/>
								</s:if>
								<s:elseif test='%{"O".equals(COVERAGES_TYPE) && #subcount==0}'>	
									 <tr class="tableHeading">
										<td colspan="5" align="center" style="background-color:#261e6a;color:white;"><b><s:property value="%{#list.SCHEME_NAME}"/>&nbsp;&nbsp;-&nbsp;&nbsp;<s:text name="label.additional.cover"/></b></td>
					         		 </tr>						
									<s:set var="subcount" value="1"/>
								</s:elseif>
							</s:else>
							<s:if test='%{"Y".equals(#prem.SUB_COVERAGES)}'>
								<s:set value="0" var="raj"/>
								<s:iterator var="subprem" value="subPremium">
									<s:if test='%{#prem.COVERAGES_ID==#subprem.COVERAGES_ID}'>
										<s:if test='%{#raj==0}'>
										<tr class="tableHeading">
											<td colspan="5" style="background-color:#261e6a;color:white;"><b><s:property value="%{#prem.coverages_display_name}"/></b></td>
										</tr>
										<s:set value="1" var="raj"/>
										</s:if>
										<tr>
											<td style="text-align: left;"><b>${coverages_display_name}</b></td>
											<td align="right">
												<s:property value="COVERAGES_COVERED_EMPLOYEES1"/>
												<%--<s:if test='%{!"dropdown".equalsIgnoreCase(#subprem.SUB_CONTROL_TYPE) && !"Radio".equalsIgnoreCase(#subprem.SUB_CONTROL_TYPE)}'>
													ZMW
												</s:if> --%>
											</td>
											<td align="right">
												<s:property value="%{COVERAGES_BASE_RATE}"/>
											</td>
											<td align="right">
												<s:if test='#premiumDisplay'>
													<s:property value="getText('{0,number,#,##0.00}',{PREMIUM_AMOUNT})"/>
												</s:if>
												<s:elseif test='!"".equalsIgnoreCase(#coverReferralDesc)'>
													Referral
												</s:elseif>
											</td>
											<td align="center">
												<s:if test='%{"Y".equals(#subprem.UPLOAD_OPTION)}'>
													<button type="button" class="btn btn-sm btn-info" data-toggle="modal" data-refresh="true" data-target="#premiumSubUploadModal" data-backdrop="static" data-keyboard="false" onclick="premiumSubUploadAjax('${subprem.coverages_id}','${subprem.coverages_sub_id}','${subprem.coverages_display_name}');">Add Cover</button>
				     							</s:if>
											</td>
										</tr>
									</s:if>
								</s:iterator>
							</s:if>
							<s:else>
								<s:if test='PREMIUM_AMOUNT!=null && !"".equalsIgnoreCase(PREMIUM_AMOUNT)'>
									<tr>
										<td style="text-align: left;"><b>${coverages_display_name}</b></td>
										<td align="right">
												<s:property value="getText('{0,number,#,##0.00}',{@java.lang.Double@valueOf(CALCULATED_SI)})"/><%--&nbsp;ZMW --%>
										</td>
										<td align="right">
											<s:property value="%{COVERAGES_BASE_RATE}"/>
										</td>
										<td align="right">
											<s:if test='#premiumDisplay'>
												<s:property value="getText('{0,number,#,##0.00}',{PREMIUM_AMOUNT})"/>
											</s:if>
											<s:elseif test='!"".equalsIgnoreCase(#coverReferralDesc)'>
												Referral
											</s:elseif>
										</td>
										<td align="center">
											<s:if test='%{"Y".equals(#prem.UPLOAD_OPTION)}'>
												<button type="button" class="btn btn-sm btn-info" data-toggle="modal" data-refresh="true" data-target="#premiumSubUploadModal" data-backdrop="static" data-keyboard="false" onclick="premiumSubUploadAjax('${prem.coverages_id}','','${prem.coverages_display_name}','<s:property value="%{#list.SCHEME_ID}"/>','${prem.LOCATION_ID}');">Add Cover</button>
			     							</s:if>
										</td>
									</tr>
								</s:if>
							</s:else>
						</s:iterator>
						<tr class="tableHeading">
							<td colspan="5" align="center" style="background-color:#261e6a;color:white;"><b><s:property value="%{#list.SCHEME_NAME}"/>&nbsp;&nbsp;-&nbsp;&nbsp;<s:text name="Total Premium Details"/></b></td>
						</tr>
						<tr>
							<td></td>
							<td style="text-align: left;" colspan="2"><b>Base Premium (ZMW)</b></td>
							<td></td>
							<td align="center">
								<s:if test='#premiumDisplay'>
									<s:property value="getText('{0,number,#,##0.00}',{@java.lang.Double@valueOf(ajActualPremium)})"/>
								</s:if>
								<s:elseif test='!"".equalsIgnoreCase(#coverReferralDesc)'>
									Referral
								</s:elseif>
							</td>
						</tr>
						<tr>
							<td></td>
							<td style="text-align: left;" colspan="2"><b>Additional Cover Premium (ZMW)</b></td>
							<td></td>
							<td align="center">
								<s:if test='#premiumDisplay'>
									<s:property value="getText('{0,number,#,##0.00}',{@java.lang.Double@valueOf(ajActualOptionalPremium)})"/>
								</s:if>
								<s:elseif test='!"".equalsIgnoreCase(#coverReferralDesc)'>
									Referral
								</s:elseif>
							</td>
						</tr>
						<s:if test='#premiumDisplay'>
							<s:if test='premCalcErrorSchLoc!=null && !"".equalsIgnoreCase(premCalcErrorSchLoc)'>
								<tr>
									<td colspan="5">
										<s:iterator value='premCalcErrorSchLoc.split("~")' status="stat"> 
											<li><b><font style="color:red;"><s:property/></font></b></li>
										</s:iterator>
								    </td>
								</tr>
							</s:if>
							<s:if test='!"Y".equalsIgnoreCase(ajMinimumPremiumYn)'>
								<tr>
									<td></td>
									<td style="text-align: left;" colspan="2"><b>Larger Volume Discount (ZMW)</b></td>
									<td><s:textfield theme="simple" name="ajVolDiscountPercent" id="ajVolDiscountPercent" cssclass="form-control" maxlength="6" style="text-align: right;width: 65%;" onkeypress="return isNumberKey(event);"/>&nbsp;%</td>
									<td align="center">
										-&nbsp;<s:property value="getText('{0,number,#,##0.00}',{@java.lang.Double@valueOf(ajVolDiscountAmount)})"/>
									</td>
								</tr>
								<tr>
									<td></td>
									<td style="text-align: left;" colspan="2"><b>Corporate Discount (ZMW)</b></td>
									<td><s:textfield theme="simple" name="ajCorpDiscountPercent" id="ajCorpDiscountPercent" cssclass="form-control" maxlength="6" style="text-align: right;width: 65%;" onkeypress="return isNumberKey(event);"/>&nbsp;%</td>
									<td align="center">
										-&nbsp;<s:property value="getText('{0,number,#,##0.00}',{@java.lang.Double@valueOf(ajCorpDiscountAmount)})"/>
									</td>
								</tr>
								<tr>
									<td></td>
									<td style="text-align: left;" colspan="2"><b>Special Discount (ZMW)</b></td>
									<td><s:textfield theme="simple" name="ajSplDiscountPercent" id="ajSplDiscountPercent" cssclass="form-control" maxlength="6" style="text-align: right;width: 65%;" onkeypress="return isNumberKey(event);"/>&nbsp;%</td>
									<td align="center">
										-&nbsp;<s:property value="getText('{0,number,#,##0.00}',{@java.lang.Double@valueOf(ajSplDiscountAmount)})"/>
									</td>
								</tr>
							</s:if>
						</s:if>
						<s:if test='#premiumDisplay'>
							<s:if test='"Y".equalsIgnoreCase(ajMinimumPremiumYn)'>
								<tr>
									<td colspan="4" align="center" style="color: red;"><s:property value="ajRemarks"/></td>
								</tr>
							</s:if>
						</s:if>
						<tr>
							<td></td>
							<td style="text-align: left;" colspan="2"><b>Total Premium (ZMW)</b></td>
							<td></td>
							<td align="center">
								<s:if test='#premiumDisplay'>
									<s:property value="getText('{0,number,#,##0.00}',{@java.lang.Double@valueOf(ajTtotalPremium)})"/>
								</s:if>
								<s:elseif test='!"".equalsIgnoreCase(#coverReferralDesc)'>
									Referral
								</s:elseif>
							</td>
						</tr>
						<s:if test='#premiumDisplay'>
							<s:if test='!"Y".equalsIgnoreCase(ajMinimumPremiumYn)'>
								<tr>
									<td></td>
									<td></td>
									<td></td>
									<td align="center">
										<a class="btn btn-primary btn-sm" id="schLocDiscCalcBtnId" onclick="coverageDtls('','','','schemeLoc');">Calculate</a>
									</td>
									<td></td>
								</tr>
							</s:if>
						</s:if>
						<tr>
							<td colspan="4"></td>
						</tr>
						<%--<tr class="tableHeading">
							<td colspan="5" align="center" style="background-color:#261e6a;color:white;"><b><s:property value="%{#list.SCHEME_NAME}"/>&nbsp;&nbsp;-&nbsp;&nbsp;<s:text name="Extra Covers"/></b></td>
						</tr>
						<tr>
							<td align="center">
							    <s:if test='"Y".equalsIgnoreCase(#list.EXTENDED_YN)'>
									<button type="button" class="btn btn-sm btn-info" data-toggle="modal" data-refresh="true" data-target="#extendedCoverageModal" data-backdrop="static" data-keyboard="false" onclick="funCoverageDetail('<s:property value="%{#list.SCHEME_ID}"/>','<s:property value="%{#list.SCHEME_NAME}"/>','extendCover','<s:property value="dropDownLocation"/>');"> <s:text name="Extended Coverage"/> </button>
								</s:if>
							</td>
							<td align="center">
								<button type="button" class="btn btn-sm btn-info" data-toggle="modal" data-refresh="true" data-target="#extendedCoverageModal" data-backdrop="static" data-keyboard="false" onclick="funCoverageDetail('<s:property value="%{#list.SCHEME_ID}"/>','<s:property value="%{#list.SCHEME_NAME}"/>','excess','<s:property value="dropDownLocation"/>');"> <s:text name="Excess"/> </button>
							</td>
							<td align="center">
								<button type="button" class="btn btn-sm btn-info" data-toggle="modal" data-refresh="true" data-target="#extendedCoverageModal" data-backdrop="static" data-keyboard="false" onclick="funCoverageDetail('<s:property value="%{#list.SCHEME_ID}"/>','<s:property value="%{#list.SCHEME_NAME}"/>','warranties','<s:property value="dropDownLocation"/>');"> <s:text name="Warranties"/> </button>
							</td>
							<td align="center" colspan="2">
								<button type="button" class="btn btn-sm btn-info" data-toggle="modal" data-refresh="true" data-target="#extendedCoverageModal" data-backdrop="static" data-keyboard="false" onclick="funCoverageDetail('<s:property value="%{#list.SCHEME_ID}"/>','<s:property value="%{#list.SCHEME_NAME}"/>','excludeRisk','<s:property value="dropDownLocation"/>');"> <s:text name="Excluded Risk"/> </button>
							</td>
						</tr> --%>
					</s:if>
					<s:else>
						<tr>
							<td colspan="5" align="center"><font style="font-weight: bold" color="red"> No Data Available for Selected Details</font></td>
						</tr>
					</s:else>
				</s:iterator>
            </tbody>
		</table>
	</div>
</div>