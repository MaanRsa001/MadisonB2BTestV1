<%@ page isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html <s:if test="locale.language == 'ar'">dir="rtl"</s:if> >
	<head>
	</head>
	<body>
		<s:form name="GeneralPremiumInfo" theme="simple">
			<div id="loading" class="ajaxLoader" style="width:100%">
			   <img id="loading-image" src="${pageContext.request.contextPath}/images/ajax-loader-bar.gif"/>
			</div>
			<s:property value="coverReferralDesc"/>
			<s:set var="coverReferralDesc" value="%{coverReferralRemarks}"/>
			<s:set name="premiumDisplay" value='("0".equalsIgnoreCase(#session.ContentType) && "".equalsIgnoreCase(#coverReferralDesc)) || "admin".equalsIgnoreCase(#session.usertype) || "RA".equalsIgnoreCase(menuType)'/>
			<div class="tablerow" style="padding:10px; background:#F8F8F8">
				<s:if test='!"".equalsIgnoreCase(#coverReferralDesc) && !"RA".equalsIgnoreCase(menuType)'>
					<div class="panel panel-danger">
						<div class="panel-heading">
							<s:label value="Referral Reasons"/>
						</div>
						<div class="panel-body">
							<div style="color: red;">
								<s:property value="#coverReferralDesc"/>
							</div>
						</div>
					</div>
				</s:if>
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
					<s:if test='%{!"1".equalsIgnoreCase(locationSize) || "".equalsIgnoreCase(locationSize)}'>
						<div class="row"><br>
							<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
								<div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
									<div class="text">
										<s:text name="Product Name"/><font color="red">*</font>
									</div>
									<div class="tbox">
										<s:select name="dropDownScheme" id="dropDownScheme" list="dropDownSchemeList" listKey="CODE" listValue="CODEDESC" cssClass="inputBoxS"  headerKey="" headerValue="--- Select ---"  disabled="#disable" theme="simple" onchange="getAjaxModel(this.form,this.value,'homeLocationList');getCovergaeAjax(this.form,'','homeCoverageDetails')"/>
									</div>
								</div>
								<div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
									<div class="text">
										<s:text name="Location Name"/><font color="red">*</font>
									</div>
									<div class="tbox">
										<div id="homeLocationList">
											<s:select name="dropDownLocation" id="dropDownLocation" list="dropDownLocationList" listKey="CODE" listValue="CODEDESC" cssClass="inputBoxS" headerKey="" headerValue="--- Select ---" disabled="#disable" theme="simple" onchange="getCovergaeAjax(this.form,this.value,'homeCoverageDetails')"/>
										</div>							
									</div>
								</div>
							</div>
						</div><br><br>
					</s:if>
					<div class="panel-body" id="premiumSummary">					
						<div class="boxcontent">
							<!--<table class="footable">
							--><s:if test='"1".equalsIgnoreCase(locationSize) || "".equalsIgnoreCase(locationSize)'>
							<table class="footable">
		   						<thead>
		   							<tr>
		   								<th width="40%"><s:text name="coverage.details"/></th>
										<th width="25%"><s:text name="coverage.sumInsured"/></th>
										<th width="20%" align="right"><s:text name="label.premium"/> in [<s:property value="#session.BrokerDetails.CurrencyAbb"/>]</th>
										<th width="15%"><s:text name="Click to Add Cover Details"/></th>
		   							</tr>
		   						</thead>
		   						<tbody>
			   						 
			   						<s:iterator var="list" value="schemeListNew" status="stat">
			    						<s:set var="subcount" value="0"/>
										<s:set var="maincount" value="0"/>
			    						<s:iterator var="prem" value="premiumListNew[#stat.count-1]">
			    							<s:if test='"11".equalsIgnoreCase(schemeId) && #maincount==0'>
												<tr class="tableHeading">
													<s:if test='%{#prem.coverages_id=="87" || #prem.coverages_id=="88"|| #prem.coverages_id=="89"|| #prem.coverages_id=="90"|| #prem.coverages_id=="91"|| #prem.coverages_id=="92"}'>
														<td colspan="4" align="center"><s:property value="%{#list.SCHEME_NAME}"/>&nbsp;&nbsp;-&nbsp;&nbsp;<s:text name="Terms Of Coverage"/></td>
														<s:set var="maincount" value="1"/>
													</s:if>
												</tr>
											</s:if>
											<s:elseif test='"11".equalsIgnoreCase(schemeId) && #subcount==0'>
												<tr class="tableHeading">
													<s:if test='%{#prem.coverages_id=="93" || #prem.coverages_id=="94" || #prem.coverages_id=="95"|| #prem.coverages_id=="96"|| #prem.coverages_id=="97"|| #prem.coverages_id=="98"|| #prem.coverages_id=="99"||#prem.coverages_id=="100" || #prem.coverages_id=="101"|| #prem.coverages_id=="102"|| #prem.coverages_id=="103"|| #prem.coverages_id=="104"|| #prem.coverages_id=="105"}'>
														<td colspan="4" align="center"><s:property value="%{#list.SCHEME_NAME}"/>&nbsp;&nbsp;-&nbsp;&nbsp;<s:text name="Table Of Benefits"/></td>
														<s:set var="subcount" value="1"/>
													</s:if>
												</tr>
											</s:elseif>
											<s:else>
				    							<s:if test='%{"B".equals(COVERAGES_TYPE) && #maincount==0}'>
													<tr class="tableHeading">
														<td colspan="4" align="center"><s:property value="%{#list.SCHEME_NAME}"/>&nbsp;&nbsp;-&nbsp;&nbsp;<s:text name="label.main.cover"/></td>
													</tr>							
													<s:set var="maincount" value="1"/>
												</s:if>
												<s:elseif test='%{"O".equals(COVERAGES_TYPE) && #subcount==0}'>	
													 <tr class="tableHeading">
														<td colspan="4" align="center"><s:property value="%{#list.SCHEME_NAME}"/>&nbsp;&nbsp;-&nbsp;&nbsp;<s:text name="label.additional.cover"/></td>
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
															<td colspan="4"><s:property value="%{#prem.coverages_display_name}"/></td>
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
																<s:if test='#premiumDisplay'>
																	<b><s:property value="getText('{0,number,#,##0.00}',{PREMIUM_AMOUNT})"/></b>
																</s:if>
																<s:elseif test='!"".equalsIgnoreCase(#coverReferralDesc)'>
																	Referral
																</s:elseif>
															</td>
															<td align="center">
																<s:if test='%{"Y".equals(#subprem.UPLOAD_OPTION)}'>
																	<button type="button" class="btn btn-sm btn-danger" data-toggle="modal" data-refresh="true" data-target="#premiumSubUploadModal" data-backdrop="static" data-keyboard="false" onclick="premiumSubUploadAjax('${subprem.coverages_id}','${subprem.coverages_sub_id}','${subprem.coverages_display_name}');"> Click to Add Cover Details </button>
							        							</s:if>
															</td>
														</tr>
													</s:if>
												</s:iterator>
											</s:if>
											<s:else>
												<s:if test='PREMIUM_AMOUNT!=null && !"".equalsIgnoreCase(PREMIUM_AMOUNT)'>
													<tr>
														<td>${coverages_display_name}</td>
														<td align="right">
															<b>
																<s:property value="getText('{0,number,#,##0.00}',{@java.lang.Double@valueOf(CALCULATED_SI)})"/>&nbsp;<s:property value="#session.BrokerDetails.CurrencyAbb"/>
															</b>
														</td>
														<td align="right">
															<s:if test='#premiumDisplay'>
																<b><s:property value="getText('{0,number,#,##0.00}',{PREMIUM_AMOUNT})"/></b>
															</s:if>
															<s:elseif test='!"".equalsIgnoreCase(#coverReferralDesc)'>
																Referral
															</s:elseif>
														</td>
														<td align="center">
															<s:if test='%{"Y".equals(#prem.UPLOAD_OPTION)}'>
																<button type="button" class="btn btn-sm btn-danger" data-toggle="modal" data-refresh="true" data-target="#premiumSubUploadModal" data-backdrop="static" data-keyboard="false" onclick="premiumSubUploadAjax('${prem.coverages_id}','','${prem.coverages_display_name}','<s:property value="%{#list.SCHEME_ID}"/>','${prem.LOCATION_ID}');"> Click to Add Cover Details </button>
						        							</s:if>
														</td>
													</tr>
												</s:if>
											</s:else>
										</s:iterator>
										</s:iterator>
										</tbody>
										</table>
										</s:if>
										<s:else>
											<div id="homeCoverageDetails">
												<table class="footable">
							   						<thead>
							   							<tr>
							   								<th width="40%"><s:text name="coverage.details"/></th>
															<th width="25%"><s:text name="coverage.sumInsured"/></th>
															<th width="20%" align="right"><s:text name="label.premium"/> in [<s:property value="#session.BrokerDetails.CurrencyAbb"/>]</th>
															<th width="15%"><s:text name="Click to Add Cover Details"/></th>
							   							</tr>
							   						</thead>
							   						<tbody>
							   							<tr>
															<td colspan="4" align="center"><font style="font-weight: bold" color="red"> Please Select Scheme and Location Name</font></td>
														</tr>
							   						</tbody>
						   						</table>
											</div>
										</s:else>
									<table class="footable">
									<thead>
		   							<tr>
		   								<th width="40%"></th>
										<th width="25%"></th>
										<th width="20%" align="right"></th>
										<th width="15%"></th>
		   							</tr>
		   							</thead>
									<tbody>
									<tr class="tableHeading">
										<td colspan="4" align="center"><s:text name="Attach Documents"/></td>
									</tr>
									<tr>
										<td colspan="4">
											<s:submit type="button" value="Attach Documents" onclick="return uploadDocuments();" cssClass="btn btn-sm btn-danger tooltipContent"/>
										</td>
									</tr>
									<%--<tr class="tableHeading">
										<td colspan="4" align="center"><s:text name="label.extraBenefits"/></td>
									</tr>
									<s:iterator value="extraBenefitsList" var="eblVar" status="eblStatus">
										<tr>
											<td colspan="4"> <s:property value="#eblVar.ITEM_VALUE"/> </td>
										</tr>
									</s:iterator>
									<tr class="tableHeading">
										<td colspan="4" align="center"><s:text name="label.excess"/></td>
									</tr>
									<s:iterator value="excessList" var="excessVar" status="excessStatus">
										<tr>
											<td colspan="4"> <s:property value="#excessVar.ITEM_VALUE"/> </td>
										</tr>
									</s:iterator> --%>
									<%--<tr class="tableHeading">
										<td colspan="4" align="center"><s:text name="Extra Covers"/></td>
									</tr>
									<tr>
										<td colspan="4" align="center">
										    <table class="footable">
										    	<thead>
													<tr>
														<th width="20%"><s:text name="Scheme Name"/></th>
														<th width="20%"><s:text name="Extended Coverage"/></th>
														<th width="20%"><s:text name="Excess"/></th>
														<th width="20%"><s:text name="Warranties"/></th>
														<th width="20%"><s:text name="Excluded Risk"/></th>
													</tr>
												</thead>
												<tbody>
											    	<s:iterator var="list" value="schemeListNew" status="stat">
														<tr>
															<td align="center">
														       <b><s:property value="%{#list.SCHEME_NAME}"/></b>
														    </td>
														    <td align="center">
																<button type="button" class="btn btn-sm btn-warning" data-toggle="modal" data-refresh="true" data-target="#extendedCoverageModal" data-backdrop="static" data-keyboard="false" onclick="funCoverageDetail('<s:property value="%{#list.SCHEME_ID}"/>','<s:property value="%{#list.SCHEME_NAME}"/>','extendCover');"> <i class="fa fa-eye"></i> </button>
															</td>
															<td align="center">	
																<button type="button" class="btn btn-sm btn-info" data-toggle="modal" data-refresh="true" data-target="#extendedCoverageModal" data-backdrop="static" data-keyboard="false" onclick="funCoverageDetail('<s:property value="%{#list.SCHEME_ID}"/>','<s:property value="%{#list.SCHEME_NAME}"/>','excess');"> <i class="fa fa-eye"></i> </button>
															</td>
															<td align="center">	
																<button type="button" class="btn btn-sm btn-info" data-toggle="modal" data-refresh="true" data-target="#extendedCoverageModal" data-backdrop="static" data-keyboard="false" onclick="funCoverageDetail('<s:property value="%{#list.SCHEME_ID}"/>','<s:property value="%{#list.SCHEME_NAME}"/>','warranties');"> <i class="fa fa-eye"></i> </button>
															</td>
															<td align="center">	
																<button type="button" class="btn btn-sm btn-info" data-toggle="modal" data-refresh="true" data-target="#extendedCoverageModal" data-backdrop="static" data-keyboard="false" onclick="funCoverageDetail('<s:property value="%{#list.SCHEME_ID}"/>','<s:property value="%{#list.SCHEME_NAME}"/>','excludeRisk');"> <i class="fa fa-eye"></i> </button>
															</td>	
														</tr>
													</s:iterator>
												</tbody>
										    </table>
										</td>
			   						</tr>--%>
									<s:if test='"Y".equals(minPremiumYN) && #premiumDisplay'>
										<tr><td colspan="4"><font color="red"><s:text name="label.home.minimum.premium.threshold">&nbsp</s:text><s:property value="getText('{0,number,#,##0.00}',{@java.lang.Double@valueOf(actualPremium)})"/>&nbsp<s:text name="label.home.minimum.premium.threshold.charged"></s:text></font></td></tr>
									</s:if>
									<tr class="tableHeading">
										<td colspan="4" align="center"><s:text name="label.totals"/></td>
									</tr>
									<s:if test='%{actualPremium!=null && !"".equalsIgnoreCase(actualPremium) && @java.lang.Double@valueOf(actualPremium)!=0}'>
										<tr>
											<td><s:label value="Base Premium"/></td>										
											<td></td>
											<td align="right">
												<s:if test='#premiumDisplay'>
													<b><s:property value="getText('{0,number,#,##0.00}',{@java.lang.Double@valueOf(actualPremium)})"/></b>
												</s:if>
												<s:elseif test='!"".equalsIgnoreCase(#coverReferralDesc)'>
													Referral
												</s:elseif>
											</td>
											<td></td>
										</tr>
									</s:if>
									<s:if test='%{discountPremium!=null && !"".equalsIgnoreCase(discountPremium) && @java.lang.Double@valueOf(discountPremium)!=0}'>
										<tr>
											<td><s:label value="Discount Premium"/><b>&nbsp;(<s:property value="discountPercent"/>%)</b></td>										
											<td></td>
											<td align="right">
												<s:if test='#premiumDisplay'>
													<b><s:property value="getText('{0,number,#,##0.00}',{@java.lang.Double@valueOf(discountPremium)})"/></b>
												</s:if>
												<s:elseif test='!"".equalsIgnoreCase(#coverReferralDesc)'>
													Referral
												</s:elseif>
											</td>
											<td></td>
										</tr>
									</s:if>
									<s:if test='%{actualOptionalPremium!=null && !"".equalsIgnoreCase(actualOptionalPremium) && @java.lang.Double@valueOf(actualOptionalPremium)!=0}'>
										<tr>
											<td><s:label value="Additional Cover Premium"/></td>										
											<td></td>
											<td align="right">
												<s:if test='#premiumDisplay'>
													<b><s:property value="getText('{0,number,#,##0.00}',{@java.lang.Double@valueOf(actualOptionalPremium)})"/></b>
												</s:if>
												<s:elseif test='!"".equalsIgnoreCase(#coverReferralDesc)'>
													Referral
												</s:elseif>
											</td>
											<td></td>
										</tr>
									</s:if>
									<tr>
										<td><s:label key="total.premium"/></td>										
										<td></td>
										<td align="right">
											<s:if test='#premiumDisplay'>
												<b><s:property value="getText('{0,number,#,##0.00}',{@java.lang.Double@valueOf(totalPremium)})"/></b>
											</s:if>
											<s:elseif test='!"".equalsIgnoreCase(#coverReferralDesc)'>
												Referral
											</s:elseif>
										</td>
										<td></td>
									</tr>
									<s:set var="total" value="%{totalPremium}" scope="page"/>
									<s:if test='%{"RA".equals(menuType) && personalInfo[0].EXCESS_PREMIUM!=null}'>
										<tr>
											<td><s:label key="home.loadingOrDiscountPremium"/></td>											
											<td></td>
											<td align="right"><b>${personalInfo[0].EXCESS_SIGN} &nbsp;<s:property value="getText('{0,number,#,##0.00}',{@java.lang.Double@valueOf(personalInfo[0].EXCESS_PREMIUM)})"/></b></td>
											<td></td>
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
										<td></td>
										<td align="right">
											<s:if test='#premiumDisplay'>
												<b><s:property value="getText('{0,number,#,##0.00}',{@java.lang.Double@valueOf(policyFee)})"/></b>
											</s:if>
											<s:elseif test='!"".equalsIgnoreCase(#coverReferralDesc)'>
												Referral
											</s:elseif>
										</td>
										<td></td>
										<s:set var="total" value="%{@java.lang.Double@valueOf(policyFee)+@java.lang.Double@valueOf(#attr.total)}" scope="page"/>
									</tr>
									<tr>
										<td><s:label key="label.total.premium.payable"/></td>										
										<td></td>
										<td align="right">
											<s:if test='#premiumDisplay'>
												<b><s:property value="getText('{0,number,#,##0.00}',{@java.lang.Double@valueOf(#attr.total)})"/></b>
											</s:if>
											<s:elseif test='!"".equalsIgnoreCase(#coverReferralDesc)'>
												Referral
											</s:elseif>
										</td>
										<td align="center">
										</td>
									</tr>
								</tbody>
							</table>
	     					<br class="clear"/>
		     			</div>
		     		</div>
		     	</div>
		     </div>
			<s:hidden name="quoteNo"/>
			<s:hidden name="applicationNo"/>
			<s:hidden name="menuType"/>
			<s:hidden name="schemeSelected"/>
			
			<s:hidden name="add" id="add"/> 
			<s:hidden name="locationSelected" id="locationSelected"/>
			<s:hidden name="locationIds" id="locationIds"/>
			<s:hidden name="locationSize" id="locationSize"/>

			<s:hidden name="ajScheme"/>
			
		</s:form>
		<div id="premiumSubUploadModal" class="modal fade" role="dialog">
			<div id="loading" class="ajaxLoader" style="width:100%">
			   <img id="loading-image" src="${pageContext.request.contextPath}/images/ajax-loader-bar.gif"/>
			</div>
			<div class="modal-dialog" style="width: 90%;">
				<div class="modal-content" >
					<div class="modal-header">
						<div align="center">
							<h3>
								<span id="coverNameSpan"></span>
							</h3>
						</div>
					</div>
					<div class="modal-body" id="premiumSubUploadAjax"></div>
				</div>
			</div>
		</div>
		<div id="extendedCoverageModal" class="modal fade" role="dialog">
			<div id="loading" class="ajaxLoader" style="width:100%">
			   <img id="loading-image" src="${pageContext.request.contextPath}/images/ajax-loader-bar.gif"/>
			</div>
			<div class="modal-dialog" style="width: 90%;">
				<div class="modal-content" >
					<!-- <div class="modal-header">
						<div align="center">
							<h3>
								<span>Extended Coverage</span>
							</h3>
						</div>
					</div> -->
					<div class="modal-body" id="extendedCoverageAjax"></div>
				</div>
			</div>
		</div>
		<SCRIPT type="text/javascript">
		$(document).ready(function(){
		try {
	  		document.getElementById("dropDownScheme").selectedIndex = 1;
	  		document.getElementById("dropDownLocation").selectedIndex = 1;
	  		var e = document.getElementById('dropDownLocation');
			var locId = e.value;
	  		getCovergaeAjax(this.form,locId,'homeCoverageDetails');
		 	}catch(err) {
				console.error(err);
			}
		});
				
		function fnsubmit1(action) {
			document.GeneralPremiumInfo.action = "${pageContext.request.contextPath}/" + action;
			document.GeneralPremiumInfo.submit();
		}
		function premiumSummary(val) {
		  	if(val) {
			  	document.getElementById('premiumSummary').style.display='';
			    document.getElementById('miuns').style.display='';
			    document.getElementById('plus').style.display='none';
		    } else {
		    	document.getElementById('premiumSummary').style.display='none';
			    document.getElementById('miuns').style.display='none';
			    document.getElementById('plus').style.display='';
		    }
		}
		function premiumSubUploadAjax(coverId, subCoverId, coverName, scheme, locationId) {
			$('#coverNameSpan').html(coverName);
			var id = 'premiumSubUploadAjax';
			var val = '?quoteNo=<s:property value="quoteNo"/>'
						+ '&applicationNo=<s:property value="applicationNo"/>'
						+ '&menuType=<s:property value="menuType"/>'
						+ '&coverId=' + coverId
						+ '&schemeId=' + scheme
						+ '&subCoverId=' + subCoverId
						+ '&dropDownLocation=' + locationId
			postRequest('${pageContext.request.contextPath}/'+id+'Home.action'+val, id);
		}
		
		function funCoverageDetail(schemeId,schemeName,reqFrom,locationId) {
			var id = 'extendedCoverageAjax';
			var val = '?quoteNo=<s:property value="quoteNo"/>'
						+ '&applicationNo=<s:property value="applicationNo"/>'
						+ '&schemeId=' + schemeId
						+ '&scheme=' + schemeName
						+ '&reqFrom=' + reqFrom
						+ '&dropDownLocation=' + locationId
			postRequest('${pageContext.request.contextPath}/'+id+'Home.action'+val, id);
		}
		
		function uploadDocuments() {
			var URL ='${pageContext.request.contextPath}/documentUploadDoUpload.action?applicationNo=<s:property value="applicationNo"/>&quoteNo=<s:property value="quoteNo"/>&deleteVehicleId=';
			return popUp(URL,'700','500');
		}
		
		function getAjaxModel(frm,value, id) {
			var val = '?dropDownScheme='+value+ '&quoteNo=<s:property value="quoteNo"/>'
			postRequest('${pageContext.request.contextPath}/'+id+'Home.action'+val, id);
		}
		function getCovergaeAjax(frm,value, id) {
			var e = document.getElementById('dropDownScheme');
			var schemeId = e.value;
			//alert(schemeId);
			var premiumDisplayVal='<s:property value="premiumDisplay"/>';
			//alert(premiumDisplayVal);
			var val = '?quoteNo=<s:property value="quoteNo"/>'
			+'&dropDownLocation='+value
			+'&productId=<s:property value="productId"/>'
			+'&contentTypeId=<s:property value="contentTypeId"/>'
			+'&dropDownScheme='+schemeId
			+'&premiumDisplayVal='+premiumDisplayVal
			postRequest('${pageContext.request.contextPath}/'+id+'Home.action'+val, id);
		}
		
		</SCRIPT>
	</body>
</html>