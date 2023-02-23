<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ page import="java.util.List,java.util.ArrayList,java.util.Map"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page import="nl.captcha.Captcha"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<style type="text/css">
	.nav-pills>li.active>a,.nav-pills>li.active>a:focus,.nav-pills>li.active>a:hover {
		color: #ffffff;
		background-color: #ff6f00;
	}
	.nav-justified>li>a {    
	    background-color: #ffb300;
	    color: #000000; 
	}
	.textSize {
		font-size: 12px;
	}
	.row {
		margin-bottom: 10px;
	}
	.text {
		font-size: 14px;
		font-weight: bold;
	}
	.inputBox {
		font-size: 12px;
	}
	.inputBoxS {
		font-size: 12px;
	}
	.tableHeading {
		background: #0078ae url(images/ui-bg_glass_45_0078ae_1x400.png) 50% 50% repeat-x;
		color: #ffffff; font-weight: bold; font-size: 14px;
	}
	</style>
	
</head>
<body>
<s:set var="disable1" value="%{endTypeId!=null && endTypeId.length() > 0}"/>
<div class="row">
	<div class='${("User"==userType||"B2C"==session.LoginType)?"col-xs-12 col-sm-12 col-md-9 col-lg-9":"col-xs-12"}'>
			<s:form id="travel" name="travel" method="post" action="getQuoteTravel.action" theme="simple">
				<s:if test="'getQuote'.equalsIgnoreCase(display)">
					<div class="row">
						<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
							<div class="panel panel-primary">
								<div class="panel-body"  style="padding: 0px;">
									<img alt="Travel Banner" src="<%=request.getContextPath()%>/images/travel-quote-header.jpg" style="width: 100%; height: auto;">
								</div>				
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
							<span style="color:red;"><s:actionerror/></span>
						</div>
					</div>
					<div class="row">
						<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
							<div class="panel panel-default">
								<div class="panel-heading">
									<div class="row">
										<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
											<div class="btn-group" role="group" aria-label="...">
											  <button type="button" title="Customer Information" class="btnGroupMargin btn btn-lg btn-success active"><i class="fa fa-user"></i></button>
											  <button type="button" title="Premium Comparison" class="btnGroupMargin btn btn-lg btn-primary" disabled="disabled"><i class="fa fa-eye"></i></button>
											</div>
										</div>
										<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6 text-success">
											<span class="textHeading"><s:text name="label.quoteInfo" /> </span> 
										</div>
									</div>						
								</div>		
							</div>
						</div>
					</div>		
					<div class="row">
						<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
							<%--<s:if test="issuer != null">
								<div class="panel panel-primary">			        			
									<div class="panel-heading">
										<s:text name="Broker Info" />
									</div>
									<div class="panel-body">
										<div class="row">
											<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
												<div class="text">
													<s:text name="Broker"  /><font color="red">*</font>
												</div>
												<div class="tbox">												   
													<div id="brokerList">
														<s:select name="brokerCode" id="brokerCode" list="brokerList" headerKey="" headerValue="-----Select-----"  listKey="CODE" listValue="CODEDESC" cssClass="inputBoxS tooltipContent" data-content="Broker Code" onchange="getList('?brokerCode='+this.value,'executiveList');" disabled="#disable"/>
													</div>
													<s:hidden name="brokerName" />
												</div>
											</div>
											<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
												<div class="text"> <s:text name="Executive BDM" /><font color="red">*</font> </div>
												<div class="tbox">
													<div id="executiveList"><s:select name="executive" list="executiveList" headerKey="" headerValue="-----Select-----" listKey="CODE" listValue="CODEDESC" cssClass="inputBoxS tooltipContent" data-content="Executive BDM" disabled="#disable" value='executive==null?getText("quotation.executiveDefault"):executive'/></div>
												</div>
											</div>
											<br class="clear"/>
										</div>
									</div>
								</div>
							</s:if>
							--%>
							<div class="row">
								<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">					
									<s:include value="/pages/customer/customerDetailsMotor.jsp"/>
								</div>
							</div>
							<div class="panel panel-primary">
								<div class="panel-heading">
									<s:text name="travel.personsToInsure" />
								</div>
								<div class="panel-body">
									<div class="row">
										<table width="100%" class="footable perInsu" id="TravelTable">
											<thead>
											<tr>
												<th width="3%"> <s:text name="travel.sNo"/> </th>
												<th width="12.12%"> <s:text name="travel.first.name" /><font color="red">*</font> </th>
												<th width="12.12%"> <s:text name="travel.last.name" /><font color="red">*</font> </th>
												<th width="12.12%"> <s:text name="travel.dateOfBirth" /><font color="red">*</font> </th>
												<th width="12.12%"> <s:text name="travel.gender" /><font color="red">*</font> </th>
												<th width="12.12%"> <s:text name="travel.relations" /><font color="red">*</font> </th>
												<th width="12.12%"> <s:text name="travel.nationality" /><font color="red">*</font> </th>
												<th width="12.12%"><s:text name="travel.passport.number" /><font color="red">*</font> </th>
												<th width="12.12%"><s:text name="travel.passport.expiry.date" /><font color="red">*</font> </th>
												<th width="3%"> <s:text name="travel.delete" /> </th>
											</tr>
											</thead>
											<tbody>
											<s:if test="travelList.size()>0">
												<s:iterator value="travelList" var="trList" status="stat">
													<tr>
														<td align="center">
															<s:property />
															<s:hidden name="travelList[%{#stat.count-1}]" id="travelList%{#stat.count-1}"/>
															<s:hidden name="serialNos[%{#stat.count-1}]" id="serialNos%{#stat.count-1}" />
														</td>
														<td align="right">
															<s:textfield name="travelNames[%{#stat.count-1}]" id="travelNames[%{#stat.count-1}]" disabled="#disable1" maxlength="120" cssClass="inputBox tooltipContent" data-content="First Name" />
														</td>
														<td align="right">
															<s:textfield name="travelLastNames[%{#stat.count-1}]" id="travelLastNames[%{#stat.count-1}]" disabled="#disable1" maxlength="120" cssClass="inputBox tooltipContent" data-content="Last Name" />
														</td>
														<td align="right"> 
															<s:textfield name="dobs[%{#stat.count-1}]" id="dobs%{#stat.count-1}"  cssClass="inputBox datePicker dobDatePikcer tooltipContent" data-content="Date of Birth" maxlength="10" placeholder="DD/MM/YYYY" />
														</td>
														<td align="right">
															<s:select list="#{'M':'Male','F':'Female'}" name="genders[%{#stat.count-1}]" id="genders[%{#stat.count-1}]"	headerKey="" headerValue="-----Select-----" disabled="#disable1" cssClass="inputBoxS tooltipContent" data-content="Gender" onchange=""/>
														</td>
														<td align="right">
															<s:select list="relationList" name="relations[%{#stat.count-1}]" id="relations[%{#stat.count-1}]" headerKey="" headerValue="-----Select-----" listKey="CODE" listValue="CODEDESC" disabled="#disable1" cssClass="inputBoxS tooltipContent" data-content="Relations" onchange=""/>
														</td>
														<td align="right">
															<s:select list="nationalityList" name="nationalitys[%{#stat.count-1}]" id="nationalitys[%{#stat.count-1}]" headerKey="" headerValue="---Select---" listKey="CODEDESC" listValue="CODEDESC" disabled="#disable1" cssClass="inputBoxS tooltipContent" data-content="Nationality"/>
														</td>
														<td align="right"> <s:textfield name="passportNo[%{#stat.count-1}]" cssClass="inputBox tooltipContent" data-content="Passport No." maxlength="10"/> </td>
														<td align="right"> <s:textfield name="passportExpDate[%{#stat.count-1}]" id="passportExpDate%{#stat.count-1}" cssClass="inputBox datePicker passExpDate tooltipContent" data-content="Passport Expiry Date" placeholder="DD/MM/YYYY" /> </td>
														<td align="center" >
															<s:if test="!#stat.first">
																<s:checkbox name="deleteRow[%{#stat.count-1}]" id="chkBox%{#stat.count-1}" onclick="deleteRow(%{#stat.count-1},this);" disabled="#disable1"/>
															</s:if>
															<s:else> &nbsp; </s:else>
														</td>
													</tr>
												</s:iterator>
											</s:if>
											</tbody>
										</table>
										<br class="clear"/>
										<div class="pullRight">
											<input type="button" id="addmore" class="btn btn-sm btn-primary" name="addmore" value="Add More" onclick="addNewRow();setMode();" />
										</div>
										<br class="clear"/>
									</div>
								</div>
							</div>
							 		
							<div class="panel panel-primary">
								<div class="panel-heading clearfix">
									<div class="pull-left">
										<s:text name="travel.schemeCoversHeading" />								
									</div>
									<div class="pull-right">
										<a data-toggle="modal" data-refresh="true" class="btn btn-sm btn-default" href="gettravelcoverinfoTravel.action" data-target="#travelCovers" style="text-decoration: none;">?</a>
										<div class="modal fade bs-example-modal-lg" data-refresh="true" id="travelCovers" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel"  aria-hidden="true">
											<div class="modal-dialog modal-lg">
												<div class="modal-content">
													<div class="modal-header">
														<h4 class="modal-title"><s:text name="travel.cover.details"></s:text> </h4>
													</div>
													<div class="modal-body">
														<div class="te"></div>
													</div>
												</div>
											</div>
										</div>									
									</div>
								</div>
								<div class="panel-body">
									<div class="row">
										<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
											<s:radio name="travelCover" list="travelCoverList" listKey="CODE" listValue="CODEDESC" theme="simple" onchange="setMode();"/>
										</div>
									</div>
								</div>
							</div>
							
							<div class="panel panel-primary">
								<div class="panel-heading">
									<s:text name="Travel Period" />
								</div>
								<div class="panel-body">
									<div class="row">
										<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
											<div class="text"> <s:text name="travel.coverPeriod"/><font color="red">*</font> </div>
											<div class="tbox">
												<s:select list="coverPeriodList" name="coverPeriod" id="coverPeriod" headerKey="-1" headerValue="---Select---" listKey="CODE" listValue="CODEDESC" disabled="#disable1" cssClass="inputBoxS tooltipContent" data-content="Cover Period" onchange="getExpiryDate(this.form);setMode();" />
											</div>
										</div>
										<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
											<div class="text"> <s:text name="travel.effectiveDt" /><font color="red">*</font> </div>
											<div class="tbox">
												<s:textfield name="inceptionDt" id="inceptionDt" onchange="getExpiryDate(this.form);setMode();" cssClass="inputBox datePicker tooltipContentR" data-content="Inception Date" placeholder="DD/MM/YYYY"/>
											</div>
										</div>
										<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
											<div class="text"> <s:text name="travel.expiryDt" /><font color="red">*</font> </div>
											<div class="tbox">
												<s:textfield name="expiryDt" id="inceptionEndDate" cssClass="inputBox tooltipContent" data-content="" readonly="true" disabled="#disable1"/>
											</div>
										</div>
									</div>
								</div>
							</div>
							<s:if test='optCoverList.size()>0 &&  !hasActionErrors()' >
							<div class="panel panel-primary" id="optCover">
								<div class="panel-heading">
									<s:text name="travel.premiumInfo" />
								</div>
								<div class="panel-body">
									<div class="row">
										<s:iterator value="optCoverList" var="schCov" status="scheme">
										<s:if test='%{(#session.user1 == "admin" && #schCov.schemeId.equalsIgnoreCase(schemeCover)) || #session.user1 != "admin"}'>
											<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
												<div class="panel panel-primary<s:property value="#scheme.count" />">	
													<div class="panel-heading" id="preDetails1">
														<div class="row">
															<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
																<span class="icon-large icon-plane"></span> &nbsp; <s:property value="schemeName"/>
															</div>
															<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4" align="center">
																<a data-toggle="modal" data-refresh="true" class="btn btn-sm btn-success" href="getCoverInfoTravel.action?schemeCover=${schemeId}&travelCover=${optionId}" data-target="#scheme${schemeId}" style="text-decoration: none;">View Covers</a>
																<div class="modal fade bs-example-modal-lg" data-refresh="true" id="scheme${schemeId}" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel"  aria-hidden="true">
																	<div class="modal-dialog modal-lg">
																		<div class="modal-content">
																			<div class="modal-header">
																				<h4 class="modal-title">Cover Details</h4>
																			</div>
																			<div class="modal-body">
																				<div class="te"></div>
																			</div>
																		</div>
																	</div>
																</div>
															</div>
															<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4" align="right">
																<s:property value="#session.BrokerDetails.CurrencyAbb"/>
																<input style="visibility: hidden;" type="radio" checked='checked' name="schemeType" id="schemeType${schemeId}" value="${schemeId}" />
																<input type="hidden" name="total${schemeId}" id="total${schemeId}" value="${premium}" readonly/>
																<span id="totaldisplay${schemeId}" ></span>
															</div>
														</div>
													</div>	        						
													<div class="panel-body" id="preDetailsData1" style="padding: 0px">			        									        							
														<div class="row">
															<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
																<table width="100%" class="premiumTable<s:property value="#scheme.count" />" cellspacing="5">
																	<tbody>
																	<s:iterator value="coveragesList" var="stat" status="covStat">
																		<s:set var='img' value='""'/>
																		<s:set var='alt' value='""'/>
																		<s:set var='cursor' value='""'/>
																		<s:set var='disp' value='""'/>
																		<s:set var='optCovId' value='""'/>
																		<s:set var='preVal' value='"0.0"'/>
																		<s:if test='%{ "Y" == #stat.isSelected }'>
																			<s:if test='%{ "Y" == #stat.isDeletable }'>
																				<s:set var='img' value='"/images/add_cover.png"'/>
																				<s:set var='alt' value='"Add Cover"'/>
																				<s:set var='cursor' value='"pointer"'/>
																				<s:set var='disp' value='"inline"'/>
																			</s:if>
																			<s:else>
																				<s:set var='img' value='"/images/selected_cover.png"'/>
																				<s:set var='alt' value='"Default Cover"'/>
																				<s:set var='cursor' value='"default"'/>
																				<s:set var='disp' value='"none"'/>
																				<s:set var='optCovId' value='coverId'/>
																				<s:set var='preVal' value='#stat.premium'/>
																			</s:else>
																		</s:if>
																		<s:else>
																		<s:if test='%{ "Y" == #stat.isAddon}'>
																			<s:if test="%{ #schCov.schemeId.equalsIgnoreCase(schemeCover) && optCovers.contains(#stat.coverId)}">
																				<s:set var='img' value='"/images/selected_cover.png"'/>
																				<s:set var='alt' value='"Default Cover"'/>
																				<s:set var='cursor' value='"pointer"'/>
																				<s:set var='disp' value='"inline"'/>
																				<s:set var='optCovId' value='coverId'/>
																				<s:set var='preVal' value='#stat.premium'/>
																			</s:if>
																			<s:else>
																				<s:set var='img' value='"/images/add_cover.png"'/>
																				<s:set var='alt' value='"Add Cover"'/>
																				<s:set var='cursor' value='"pointer"'/>
																				<s:set var='disp' value='"none"'/>
																			</s:else>
																		</s:if>
																		</s:else>
																		<tr>
																			<td width="5%">
																				<img name="firstImg${groupId}<s:property value="#schCov.schemeId"/>${coverId}" id="firstImg${groupId}<s:property value="#schCov.schemeId"/><s:property value="%{#covStat.index}" />" src='<s:url value="%{#img}"/>' style="cursor: <s:property value='#cursor'/>;" alt="%{#alt}" height="24" width="24" onclick="return addCover(this,'<s:property value="#schCov.schemeId"/>','${coverId}','<s:property value="%{#covStat.index}" />','<s:property value="#stat.premium" />','${groupId}'+'<s:property value="#schCov.schemeId"/>'+'<s:property value="%{#covStat.index}" />');"/>
																				<img name="secondImg${groupId}<s:property value="#schCov.schemeId"/>${coverId}" id="secondImg${groupId}<s:property value="#schCov.schemeId"/><s:property value="%{#covStat.index}" />" src="<s:url value="/images/delete_cover.png"/>"  style="cursor: pointer; display:<s:property value='#disp'/>;" alt="Remove Cover" height="24" width="24" onclick="return deleteCover(this,'<s:property value="#schCov.schemeId"/>','${coverId}','<s:property value="%{#covStat.index}" />','<s:property value="#stat.premium" />','${groupId}'+'<s:property value="#schCov.schemeId"/>'+'<s:property value="%{#covStat.index}" />');" />
																				<input type="hidden" name="default<s:property value='#schCov.schemeId'/>" value="<s:property value='#preVal'/>" />
																				<input   style="position: absolute;visibility: hidden"  type="checkbox" checked="checked" name="optionalCovers<s:property value="#schCov.schemeId"/>" id="optionalCovers<s:property value="#schCov.schemeId"/>" value='<s:property value="#optCovId"/>' />
																			</td>
																			<td width="65%">
																				<span class="preText"><s:property value="#stat.coverName" /></span>
																			</td>
																			<td width="30%" style="font-weight: bolder;">
																				<s:if test='%{ "R" == #stat.referalYN }'> Referral </s:if>
																				<s:else>
																					<span class="pullRight">
																						<s:property value="getText('{0,number,#,##0}',{#stat.premium})" />
																					</span>
																					<br class="clear"/>
																				</s:else>
																				<s:if test='%{groupId == "1"}'>
																					<s:radio list="#{'Y':'','N':''}" cssStyle="visibility:hidden ;" name="coverages" id="coverages%{#schCov.schemeId}" disabled="#disable1" value="%{(#optCovId != null && #optCovId != '')?'Y':'N'}"/>
																				</s:if>										       				
																			</td>
																		</tr>			        									
																	</s:iterator>    									
																	</tbody>
																</table>
															</div>	
															<br/>
																<div align="center">
																	<input type="button"  class="btn btn-sm btn-warning"  name="tdCovers" value='<s:property value='%{#session.user1 != "admin" ? "Buy Policy" : "Approve Quote"}'/>' onclick="getBuyPolicy(this.form,'<s:property value="#schCov.schemeId"/>');" />
																</div>
															</div>
														</div>
												</div>
											</div>
										</s:if>				        					
										</s:iterator>			        					
									</div>
								</div>
							</div>
							</s:if>
							<s:else>
								<s:if test='"B2C".equalsIgnoreCase(#session.LoginType)'>
								<div class="row">
									<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
										<div class="panel panel-primary">
											<div class="panel-body">
												<div class="row">
													<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
														<div class="text"><s:text name="Please enter the text that you see in the image" /></div>
														<div class="tbox">
															<s:textfield name="captchavalue" id="captchavalue" cssClass="inputBox tooltipContent" data-content="Please enter the text that you see in the image" />
														</div>
													</div>
													<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
														<%	Captcha captcha = new Captcha.Builder(200, 50).addText().addBackground().addNoise().gimp().addBorder().build(); %>
											            <div class="captchaBg" style="width: 100px; float: left;">
											            	<img id="captcha" src="<c:url value="simpleCaptcha.jpg"  />" width="100">
											            </div>
											            <a href="#" onclick="reloadCaptcha();" style="float: left;"><img src="${pageContext.request.contextPath}/images/reload.png" alt="reload" width="40" height="40"/> </a>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
								</s:if>
								<br class="clear"/>
							</s:else>
							<div align="center">
								<s:if test='#session.LoginType != "B2C"'>
									<input type="button" name="Submit1" class="btn btn-sm btn-danger" value="Back" onclick="getBack('customer');disableForm(this.form,false,'');" />
									&nbsp;&nbsp;&nbsp;
								</s:if>
								<s:if test='"".equalsIgnoreCase(#session.user) || "madisondirect".equalsIgnoreCase(#session.user)|| "guestmotor".equalsIgnoreCase(#session.user)'>
									<input type="button" name="Submit4" class="btn btn-sm btn-success" id="getQuoteButton"  value="Calculate" onclick="disableForm(this.form,false,'');otpPage(this.form)"/>
								</s:if>
								<s:elseif test='#session.user1 != "admin" '>
									<input type="button" name="Submit4" class="btn btn-sm btn-success" id="getQuoteButton"  value="Calculate" onclick="this.form.schemeCover.value='';this.form.mode.value='';this.form.actionType.value='getQuote';disableForm(this.form,false,'');this.form.submit();"/>
								</s:elseif>
							</div>
							<s:hidden name="display" />
							<s:hidden name="actionType" />
							<%--<s:hidden name="customerId" id="customerId"/>--%>
							<s:hidden name="applicationNo" />
							<s:hidden name="quoteNo" />	           
							<s:hidden name="serialNo" />
							<s:hidden name="quoteStatus"/>
							<s:hidden name="endTypeId" />
							<s:hidden name="brokerCompany" />
							<s:hidden name="policyNo" />
							<s:hidden name="entryDate"/>
							<s:hidden name="amendId"/>
							<s:hidden name="schemeCover"/>
							<s:hidden name="mode" id="mode"/>
							<s:hidden name="referralMsg"/>
						</div>
					</div>
					</s:if>
					<s:elseif test="'showQuoteInfo'.equalsIgnoreCase(display)">
						<div class="panel panel-primary">
				 			<div class="panel-heading">
				 				<s:text name="quotation.quoteInfo" /> 	
							</div>
							<div class="panel-body">
								<div class="boxcontent" align="center">
									<div style="color:red;font-size: 15px;"><b><s:text name="travel.refInfo"/> Saved. Your Quote No is : <s:property value="quoteNo"/></b></div>
									<br class="clear"/>
									<input type="button" name="Submit" class="btn btn-sm btn-success" value="Proceed" onclick="getBack('home');"/>
								</div>
							</div>
						</div>
						<s:hidden name="endTypeId" />
					</s:elseif>
					<s:elseif test="'showRefInfo'.equalsIgnoreCase(display)">
						<div class="panel panel-primary">
						 	<div class="panel-heading">
						 		<s:text name="quotation.quoteInfo" />		
							</div>
							<div class="panel-body">
								<div class="boxcontent" align="center">
									<div style="color:red;font-size: 15px;"><b><s:text name="travel.refInfo"/> <s:property value="referralMsg"/></b></div>
									<br class="clear"/>
									<input type="button" name="Submit" class="btn btn-sm btn-success" value="Proceed" onclick="getBack('adminHome');"/>
								</div>
							</div>
						</div>
						<s:hidden name="endTypeId" />
					</s:elseif>	
			</s:form>
	</div>
	<s:if test='"User".equalsIgnoreCase(userType)||"B2C".equalsIgnoreCase(#session.LoginType)'>
		<div class="col-xs-12 col-sm-12 col-md-3 col-lg-3">
			<div class="row">
				<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
					<s:include value="/pages/motor/sideBar.jsp"></s:include>
				</div>
			</div>		
		</div>
	</s:if>	
</div>
<script type="text/javascript">	

function otpPage(frm){
document.travel.action ='${pageContext.request.contextPath}/otpLoginTravel.action';
document.travel.schemeCover.value='';
document.travel.mode.value='';
document.travel.actionType.value='getQuote';
//disableForm(frm,false,'');
document.travel.submit();
}


	appPath = "${pageContext.request.contextPath}/";
	try{
	if(document.policyInfo.endTypeId !=null && document.policyInfo.endTypeId.value != ''){
		enableForm(document.policyInfo,false,'<s:property value="%{fields}"/>');
    }
	}catch (e) {
		// TODO: handle exception
	}
	function getTotalPremium(frm)
	{
	var travelPremium=0.0;
	var coverPremium=0.0;
	var policyFee=0.0;
	for(var i=0;;i++)
	{
		if(document.getElementById("travelPremium"+i)!=null)
			travelPremium+=parseFloat(document.getElementById("travelPremium"+i).value);
		else
			break;
	}
	for(var i=0;;i++)
	{
		if(document.getElementById("coveragePremium"+i)!=null)
			coverPremium+=parseFloat(document.getElementById("coveragePremium"+i).value);
		else
			break;
	}
	var discountAmt=parseFloat(document.getElementById("discountAmt").value);
	var finalPremium=parseFloat(travelPremium)+parseFloat(coverPremium)-parseFloat(discountAmt);
	frm.finalPremium.value=finalPremium.toFixed(1);
	var loadOrDiscPremium=parseFloat(frm.loadOrDiscPremium.value);
	var discount=parseFloat(frm.discountAmt.value);
	var sign=frm.sign.value;
	var policyFee=parseFloat(document.getElementById("policyFee").value);
	var val=0;
	if(sign=='+'){
		val=finalPremium+loadOrDiscPremium+policyFee;
		frm.totalPremium.value=val.toFixed(1);
	}else 
	{
		val=finalPremium-loadOrDiscPremium+policyFee;
		frm.totalPremium.value=val.toFixed(1);
	}
	if(0!='<s:property value="amendId"/>'){
	    var premiumPaid=parseFloat(frm.premiumPaid.value);
	    var endtPremium=val-premiumPaid;
	    frm.endtPremium.value=endtPremium.toFixed(1);
	}
	}
	function getBack(page)
	{
		if(page=='home'){
			if('b2c'=='<s:property value="#session.b2c"/>')
				document.travel.action ='${pageContext.request.contextPath}/login/ProductSelection.jsp';
			else{
				if('RA'=='<s:property value="quoteStatus"/>')
					document.travel.action ='${pageContext.request.contextPath}/initReport.action?menuType=RA&loginId=<s:property value="loginId"/>';
				else if('RU'=='<s:property value="quoteStatus"/>')
					document.travel.action ='${pageContext.request.contextPath}/initReport.action?menuType=RU&loginId=<s:property value="loginId"/>';
				else if(('getSave'=='<s:property value="actionType"/>' || 'QS'=='<s:property value="quoteStatus"/>') && 0=='<s:property value="totalPremium"/>')
					document.travel.action ='${pageContext.request.contextPath}/initReport.action?menuType=QS&loginId=<s:property value="loginId"/>';
				else
					document.travel.action ='${pageContext.request.contextPath}/initReport.action?menuType=QE&loginId=<s:property value="loginId"/>';
			}
		}else if(page=='customer'){
			if('admin'=='<s:property value="#session.user1"/>'){
				/*if('RR'=='<s:property value="quoteStatus"/>')
					document.travel.action ='${pageContext.request.contextPath}/admin/HomeRejectedPolicyByDate.jsp';
				else
					document.travel.action ='${pageContext.request.contextPath}/admin/HomePendingPolicyByDate.jsp';*/
					document.travel.action ='${pageContext.request.contextPath}/getOCAjaxReferal.action';
			}else{
				if('ET'=='<s:property value="quoteStatus"/>')
					document.travel.action ='${pageContext.request.contextPath}/initReport.action?menuType=ET&custName=<s:property value="customerName"/>';
				else
					document.travel.action ='${pageContext.request.contextPath}/initReport.action?menuType=QE&loginId=<s:property value="loginId"/>';
			}
		}else if(page=='quote'){
			if('admin'=='<s:property value="#session.user1"/>'){
	 			if('RA'=='<s:property value="quoteStatus"/>'){
					//document.travel.action ='${pageContext.request.contextPath}/admin/HomeApprovedPolicyByDate.jsp';
					document.travel.action ='${pageContext.request.contextPath}/getOCAjaxReferal.action';
				}else
					document.travel.action ='${pageContext.request.contextPath}/initTravel.action';
			}else{
				if('RA'=='<s:property value="quoteStatus"/>')
					document.travel.action ='${pageContext.request.contextPath}/initReport.action?menuType=RA&loginId=<s:property value="loginId"/>';
				else
					document.travel.action ='${pageContext.request.contextPath}/initTravel.action';
			}
		}else if(page=='adminHome'){
			/*if('Y'=='<s:property value="adminRefStatus"/>')
				document.travel.action ='${pageContext.request.contextPath}/admin/HomeApprovedPolicy.jsp';
			else if('A'=='<s:property value="adminRefStatus"/>')
				document.travel.action ='${pageContext.request.contextPath}/admin/HomePendingPolicy.jsp';
			else if('N'=='<s:property value="adminRefStatus"/>')
				document.travel.action ='${pageContext.request.contextPath}/admin/HomeRejectedPolicy.jsp';*/
			document.travel.action ='${pageContext.request.contextPath}/getOCAjaxReferal.action';
		}
		document.travel.submit();
	}
	function getAjaxTravelCover(frm,val, id)
	{
		postRequest('${pageContext.request.contextPath}/'+id+'Travel.action'+val, id);
	}
	function getAjaxCoverage(frm,val, id)
	{
		postRequest('${pageContext.request.contextPath}/'+id+'Travel.action'+val, id);
	}
	function getExpiryDate(frm)
	{	
		if(frm.coverPeriod.value!='' && frm.inceptionDt.value!='')
		{
			if(isInteger(frm.coverPeriod.value)) {
				var date=new Date(reformatDate(frm.inceptionDt.value));
				date.setDate(date.getDate()+(parseInt(frm.coverPeriod.value)-1));
	    		var d;
				var m
				if(parseInt(date.getDate())<10)
				{
					d="0"+date.getDate();
				}else
				{
					d=date.getDate();
				}
				if(parseInt(date.getMonth()+1)==0)
				{
					m="12"
				}else
					if((parseInt(date.getMonth())+1)<10)
					{
						m="0"+(parseInt(date.getMonth())+1);
					}else
					{
						m=(parseInt(date.getMonth())+1);
					}
				var y=date.getFullYear();
				frm.inceptionEndDate.value=d+"/"+m+"/"+y;
			}
			else {
				frm.inceptionEndDate.value="";
				frm.coverPeriod.value="";
			}
		}
		else {
			frm.inceptionEndDate.value="";
		}
	}
	function isInteger(s){   
		var i;
	    for (i = 0; i < s.length; i++){   
	        var c = s.charAt(i);
	        if ((c < "0") || (c > "9")) 
	        {
	        	alert("Please Enter Valid Days");
	        	return false;
	        }
	    }
	    return true;
	}
	function reformatDate(dateStr) { 
		  dArr = dateStr.split("/");
	  return dArr[2]+ "/" +dArr[1]+ "/" +dArr[0];
	} 
	<s:if test="'getQuote'.equalsIgnoreCase(display)">
		var prevVal;
	    var editArray = new Array();
	    var delArray = new Array();
	    var slNo=1001;

		function setInitialValue(value){
	    	prevVal = value;
	    }

	    function checkStateChange(obj, value){
	    	if(prevVal == value){
	        	// No Change
	        }else{
				var edRowId = obj.parentNode.parentNode;
	            var existStatus = checkObjExists(edRowId.id);
	            if(!existStatus){
	            	var edRowId = obj.parentNode.parentNode;
	                editArray.unshift(edRowId.id);
	            }
			}
		}          

		function checkObjExists(id){
			for(arIdx=0;arIdx<editArray.length;arIdx++){
				if(editArray[arIdx] == id){
					return true;
				}
			}
			return false;
		}

		function deleteRow(sNo,obj){
	    	var decision = confirm("Row will be deleted. Do You Want to continue? ","");
			if (decision==true)
				{
	        	document.travel.serialNo.value=document.getElementById("serialNos"+sNo).value;
	        	document.travel.action ='${pageContext.request.contextPath}/getDeleteTravel.action';
			    document.travel.submit();
			    return false;
		    }else
		    {
		    	document.getElementById("chkBox"+sNo).checked=false;
		    }
		}

	    function removeEditRow(id){
	    	if(id.indexOf("ext")!=-1){
	        	for(edIdx=0;edIdx<editArray.length;edIdx++){
	            	if(editArray[edIdx] == id){
	                	editArray.splice(edIdx, 1);
	                }
	            }
	            delArray.unshift(id);
			}
		} 
	    function addNewRow(){
			var table = document.getElementById('TravelTable');
	        var rowCount = table.rows.length;
	        var row = table.insertRow(rowCount);
	        row.id = "new_"+rowCount;
	        row.className = "subHeadingDash";
	        
	        //var cell0 = row.insertCell(0);            
	 		//cell0.setAttribute("style", "text-align: center;");
	        
	        var cell = row.insertCell(0);            
	 		cell.innerHTML = parseInt(rowCount);
	 		cell.setAttribute("style", "text-align: center;");
	 		var element = document.createElement("input");
	        element.type = "hidden";
	        element.name = "travelList["+(rowCount-1)+"]";
	        element.id = "travelList["+(rowCount-1)+"]";
	        element.value = parseInt(rowCount);
	        cell.appendChild(element);
	 		element = document.createElement("input");
	        element.type = "hidden";
	        element.name = "serialNos["+(rowCount-1)+"]";
	        element.id = "serialNos"+(rowCount-1)+"";
	        element.value = slNo++;
	        cell.appendChild(element);
	 			
	        cell = row.insertCell(1);
	        cell.setAttribute("style", "text-align: center;");
	 		var element = document.createElement("input");
	        element.type = "text";
	        element.name = "travelNames["+(rowCount-1)+"]";
	        element.id = "travelNames["+(rowCount-1)+"]";  
	        element.setAttribute("maxlength",'120');    
	        element.className = "inputBox";      
	        cell.appendChild(element);
	        cell = row.insertCell(2);

	        cell.setAttribute("style", "text-align: center;");
	 		var element = document.createElement("input");
	        element.type = "text";
	        element.name = "travelLastNames["+(rowCount-1)+"]";
	        element.id = "travelLastNames["+(rowCount-1)+"]";  
	        element.setAttribute("maxlength",'120');    
	        element.className = "inputBox";      
	        cell.appendChild(element);
	        cell = row.insertCell(3);
	        
	        
	        cell.setAttribute("style", "text-align: center;");
	        //createDobCell(cell, rowCount);//dob
	 		var element = document.createElement("input");
	        element.type = "text";
	        element.setAttribute("placeholder",'DD/MM/YYYY'); 
	        element.setAttribute("maxlength",'10'); 
	        element.className = "inputBox";
	        element.name = "dobs["+(rowCount-1)+"]";
	        element.id = "dobs"+(rowCount-1)+"";            
	        cell.appendChild(element);
	        $(function() {
	        	var dt = new Date();
				dt.setFullYear(new Date().getFullYear()-18);
				$('#dobs'+(rowCount-1)+'').datepicker({
					todayHighlight: true,
		        	format: "dd/mm/yyyy"
				  	//viewMode: "years",
				  	//endDate: dt
				}).on('changeDate', function(e){
		            $(this).datepicker('hide');
		        });
			});
	 		cell = row.insertCell(4);
	        cell.setAttribute("style", "text-align: center;");
	 		createGenderCell(cell, rowCount);//gender
	        
	        cell = row.insertCell(5);
	        cell.setAttribute("style", "text-align: center;");
	 		createRelationCell(cell, rowCount);//relation
	        
	        cell = row.insertCell(6);
	        cell.setAttribute("style", "text-align: center;");
	 		createNationalityCell(cell, rowCount);//nationality
	        
	        cell = row.insertCell(7);
	        cell.setAttribute("style", "text-align: center;");
	 		var element = document.createElement("input");
	        element.type = "";
	        element.name = "passportNo["+(rowCount-1)+"]";
	        element.id = "passportNo["+(rowCount-1)+"]";  
	        element.setAttribute("maxlength",'10');
	        element.className = "inputBox";          
	        cell.appendChild(element); //Passport Number
	        cell = row.insertCell(8);
	        cell.setAttribute('style','text-transform: uppercase;');
	        //createDobCell(cell, rowCount);//dob
	 		var element = document.createElement("input");
	        element.type = "text";
	        element.className = "inputBox";
	        element.name = "passportExpDate["+(rowCount-1)+"]";
	        element.id = "passportExpDate"+(rowCount-1)+"";      
	        element.setAttribute("placeholder",'DD/MM/YYYY'); 
	        element.setAttribute("maxlength",'10');       
	        cell.appendChild(element);
	        $(function() {
	        	$('#passportExpDate'+(rowCount-1)+'').datepicker({
					todayHighlight: true,
					format: "dd/mm/yyyy"
				}).on('changeDate', function(e){
				    $(this).datepicker('hide');
				});
			}); //Passport Expiry Date
	        
	        cell = row.insertCell(9);
	        cell.setAttribute("style", "text-align: center;");
	 		element = document.createElement("input");
	        element.type = "checkbox";
	        element.name = "";
	        element.id = "chkBox"+(rowCount-1)+""; 
	        //element.style.width = "50px";
	        element.onclick =  function () {deleteRow(rowCount-1,this)};
	        cell.appendChild(element);
		}
	    function createGenderCell(cell, rowCount){
			element = document.createElement("select");
	        element.className = "inputBoxS";
	        element.name = "genders["+(rowCount-1)+"]";
	        element.id = "genders["+(rowCount-1)+"]";            
	        populateGenderTag(element);
	        cell.appendChild(element);
		}
		
		function createRelationCell(cell, rowCount){
			element = document.createElement("select");
	        element.className = "inputBoxS";
	        element.name = "relations["+(rowCount-1)+"]";
	        element.id = "relations["+(rowCount-1)+"]";            
	        populateRelationTag(element);
	        cell.appendChild(element);
		}
		
		function createNationalityCell(cell, rowCount){
			element = document.createElement("select");
	        element.className = "inputBoxS";
	        element.name = "nationalitys["+(rowCount-1)+"]";
	        element.id = "nationalitys["+(rowCount-1)+"]";            
	        populateNationalityTag(element);
	        cell.appendChild(element);
		}
		function populateGenderTag(objSelect){
			var objOption = document.createElement("option");
	        objOption.text = '---Select---';
	        objOption.value = '';
	        if(document.all && !window.opera){
	        	objSelect.add(objOption);
	        }else{
	        	objSelect.add(objOption, null);
	        }
	        objOption = document.createElement("option");
	        objOption.text = 'Male';
	        objOption.value = 'M';
	        if(document.all && !window.opera){
	        	objSelect.add(objOption);
	        }else{
	        	objSelect.add(objOption, null);
	        }
	        objOption = document.createElement("option");
	        objOption.text = 'Female';
	        objOption.value = 'F';
	        if(document.all && !window.opera){
	        	objSelect.add(objOption);
	        }else{
	        	objSelect.add(objOption, null);
	        }
		}
		
		function populateRelationTag(objSelect){
			var objOption = document.createElement("option");
	        objOption.text = '---Select---';
	        objOption.value = '';
	        objSelect.add(objOption);
	        <%--if(document.all && !window.opera){
	        	objSelect.add(objOption);
	        }else{
	        	objSelect.add(objOption, null);
	        }--%>
	        <s:iterator value="relationList" var="list" status="stat">
		        var objOption = document.createElement("option");
		    	objOption.text = '<s:property value="#list.CODEDESC" />';
		    	objOption.value = '<s:property value="#list.CODE"/>';
		    	objSelect.add(objOption);
		  	</s:iterator>
		  	
	       	<%--for (int i=0;i<relationList.size();i++) {
	       	Map entry = (Map)relationList.get(i);
	       	%>
	        	var objOption = document.createElement("option");
	        	objOption.text = '<%=entry.get("CODEDESC")%>';
	        	objOption.value = '<%=entry.get("CODE")%>';
	        	if(document.all && !window.opera){
	        		objSelect.add(objOption);
	        	}else{
	        		objSelect.add(objOption, null);
	        	}
	       	<% }--%>
		}
		
		function populateNationalityTag(objSelect){
			var objOption = document.createElement("option");
	        objOption.text = '---Select---';
	        objOption.value = '';
	        objSelect.add(objOption);
	        <s:iterator value="nationalityList" var="item" status="stat">
		        var objOption = document.createElement("option");
		    	objOption.text = '<s:property value="#item.CODEDESC"/>';
		    	objOption.value = '<s:property value="#item.CODEDESC"/>';
		    	objSelect.add(objOption);
	      	</s:iterator>
	        <%--
	        if(document.all && !window.opera){
	        	objSelect.add(objOption);
	        }else{
	        	objSelect.add(objOption, null);
	        }--%>
	        <%--
	        
	        for (int i=0;i<nationalityList.size();i++) {
	       	Map entry = (Map)nationalityList.get(i);
	       	%>
	        	var objOption = document.createElement("option");
	        	objOption.text = '<%=entry.get("CODEDESC")%>';
	        	objOption.value = '<%=entry.get("CODEDESC")%>';
	        	if(document.all && !window.opera){
	        		objSelect.add(objOption);
	        	}else{
	        		objSelect.add(objOption, null);
	        	}
	       	<% }--%>
		}	
		</s:if>
	<s:if test="'showQuote'.equalsIgnoreCase(display) && #session.user1 != 'admin'">
		disablePolicyOption('<s:property value="referralYN"/>');
		toggleEmailYN('<s:property value="generatePolicyYN"/>');
		 function disablePolicyOption(value)
			 {
			 if(value=="Y")
			 {
				document.getElementById('policyGeneration').style.display='none';
				document.getElementById('referralComments').readOnly=false;
			 }   
			 else
			 {   
			 	document.getElementById('policyGeneration').style.display='block';
			 	document.getElementById('referralComments').value='';
			 	document.getElementById('referralComments').readOnly=true;
			 } 
		}
		 function toggleEmailYN(value)
			 {
			 if(value=="Y")
			 {
				document.getElementsByName('quoteEmailYN')[0].checked=false; 
			 	document.getElementsByName("quoteEmailYN")[0].disabled=true;
			 	document.getElementsByName('quoteEmailYN')[1].checked=true; 
			 	document.getElementsByName("quoteEmailYN")[1].disabled=true;
				document.getElementsByName("policyEmailYN")[0].disabled=false;
				document.getElementsByName("policyEmailYN")[1].disabled=false;
			 }   
			 else
			 {  
			    document.getElementsByName("quoteEmailYN")[0].disabled=false;
				document.getElementsByName("quoteEmailYN")[1].disabled=false;
			 	document.getElementsByName('policyEmailYN')[0].checked=false; 
			 	document.getElementsByName("policyEmailYN")[0].disabled=true;
			 	document.getElementsByName('policyEmailYN')[1].checked=true; 
			 	document.getElementsByName("policyEmailYN")[1].disabled=true;
			 } 
		}
	</s:if>
	function addCover(val,typeId,coverId,coverCount,premium,imgId){
	/*alert('typeId'+typeId);
	alert('coverId'+coverId);
	alert('coverCount'+coverCount);
	alert(premium);
	alert(imgId);*/
		if((val.src).indexOf("add_cover.png")!=-1){
			val.src = "${pageContext.request.contextPath}/images/selected_cover.png";
			val.alt = "Added";
			val.style.cursor = "default";
			val.style.display = "none";
			document.getElementById("coverages"+typeId+"Y").checked=true;
			var object = document.getElementsByName("optionalCovers"+typeId);
			object[coverCount].value = coverId;
		<%--	if('<s:property value="referralMsg"/>'==null || '<s:property value="referralMsg"/>'==''){--%>
				var val = document.getElementById("total"+typeId).value;
				val= val==""?0:val;
				var tot = parseFloat(val) + parseFloat(premium);
				document.getElementById("total"+typeId).value = roundNumber(tot,0);
			<%--}else{ 
			    document.getElementById("total"+typeId).value = 'Referral';
			}--%>
			document.getElementById("totaldisplay"+typeId).innerHTML = document.getElementById("total"+typeId).value;
			var object1 =  document.getElementById("secondImg"+imgId);
			object1.style.display = "inline";		
		}
	return false;
	}
	function deleteCover(val,typeId,coverId,coverCount,premium,imgId){
		if((val.src).indexOf("delete_cover.png")!=-1){
			val.style.display = "none";
			document.getElementById("coverages"+typeId+"N").checked=true;
			var object = document.getElementsByName("optionalCovers"+typeId);
			object[coverCount].value = '';
		<%--	if('<s:property value="referralMsg"/>'==null || '<s:property value="referralMsg"/>'==''){--%>
				var val = document.getElementById("total"+typeId).value;
				val= val==""?0:val;
				var tot = parseFloat(val) - parseFloat(premium);
				document.getElementById("total"+typeId).value = roundNumber(tot,0);				
		<%--	}else{ 
				document.getElementById("total"+typeId).value = 'Referral';
			}--%>
			document.getElementById("totaldisplay"+typeId).innerHTML = document.getElementById("total"+typeId).value;
			var object1 =  document.getElementById("firstImg"+imgId);
			object1.src = "${pageContext.request.contextPath}/images/add_cover.png";
			object1.alt = "Add Cover";
			object1.style.cursor = "pointer";
			object1.style.display = "inline";
		}
	return false;
	}
	addTotal();
	function addTotal(){
	if(document.travel.schemeType){
	if(document.travel.schemeType.length){
		var policylen = document.travel.schemeType.length;		
		for(var i=0;i<policylen;i++){
			var typeId = document.travel.schemeType[i].value;			
			var obj =  document.getElementsByName("default"+typeId);
		<%--	if('<s:property value="referralMsg"/>'==null || '<s:property value="referralMsg"/>'==''){--%>
				var tot = 0;
				if(obj.length){
					var len = obj.length;
					for(var j=0;j<len;j++){
						tot = parseFloat(tot) + parseFloat(obj[j].value==""?0:obj[j].value);
					}
				}else{
					tot = parseFloat(tot) + parseFloat(obj.value==""?0:obj.value);
				}				
				document.getElementById("total"+typeId).value = roundNumber(tot,0);
		<%--	}else{ 
				document.getElementById("total"+typeId).value = 'Referral';
			}--%>			
			document.getElementById("totaldisplay"+typeId).innerHTML = document.getElementById("total"+typeId).value;
		}		
	}else{
		var typeId = document.travel.schemeType.value;
		var obj =  document.getElementsByName("default"+typeId);
	<%--	if('<s:property value="referralMsg"/>'==null || '<s:property value="referralMsg"/>'==''){--%>
			var tot = 0;
			if(obj.length){
				var len = obj.length;
				for(var j=0;j<len;j++){
					tot = parseFloat(tot) + parseFloat(obj[j].value==""?0:obj[j].value);
				}
			}else{
				tot = parseFloat(tot) + parseFloat(obj.value==""?0:obj.value);
			}
			document.getElementById("total"+typeId).value = roundNumber(tot,0);
	 <%--   }else{ 
			document.getElementById("total"+typeId).value = 'Referral';
		}--%>
		document.getElementById("totaldisplay"+typeId).innerHTML = document.getElementById("total"+typeId).value;
	}
	}
	}
	function roundNumber(number,decimal_points) {
		if(!decimal_points) return Math.round(number);
		if(number == 0) {
			return number;
		}
		var exponent = Math.pow(10,decimal_points);
		var num = Math.round((number * exponent)).toString();
		var result = num.slice(0,-1*decimal_points);
		if(num.slice(-1*decimal_points)!='00')
			result = result + "." + num.slice(-1*decimal_points);
		return result;
	}
	function setMode()
	{
		$('#optCover').hide();
		$('#getQuoteButton').show();
		//document.getElementById("mode").value='changed';
	}
	function getBuyPolicy(frm,schemeId)
	{
		if(document.getElementById("mode").value!='changed'){
		 	frm.schemeCover.value=schemeId;
		 	frm.actionType.value='getScheme';
		 	disableForm(frm,false,'');
		 	frm.submit();
			}else
			{
			  alert('Please Calculate to Buy the Policy');
		}
	}

	function fnAddWinSport(val, id) {
		if (val) {
			document.getElementById('winSportMinusText'+id).style.display='block';
			document.getElementById('winSportMinus'+id).style.display='block';
			document.getElementById('winSportMinusVal'+id).style.display='block';
			document.getElementById('premiumMinus'+id).style.display='block';		
			document.getElementById('winSportPlus'+id).style.display='none';
			document.getElementById('winSportPlusText'+id).style.display='none';
			document.getElementById('winSportPlusVal'+id).style.display='none';
			document.getElementById('premiumPlus'+id).style.display='none';		
		} else {
			document.getElementById('winSportMinus'+id).style.display='none';
			document.getElementById('winSportMinusText'+id).style.display='none';
			document.getElementById('winSportMinusVal'+id).style.display='none';
			document.getElementById('premiumMinus'+id).style.display='none';
			document.getElementById('winSportPlus'+id).style.display='block';
			document.getElementById('winSportPlusText'+id).style.display='block';
			document.getElementById('winSportPlusVal'+id).style.display='block';
			document.getElementById('premiumPlus'+id).style.display='block';
		}
	}
	function fnAddWinSport1(val) {
		if (val) {
			document.getElementById('winSportMinus').style.display='block';
			document.getElementById('winSportMinusT').style.display='block';
			document.getElementById('winSportMinusP').style.display='block';		
			document.getElementById('premiumMinusT').style.display='block';		
			document.getElementById('premiumMinusP').style.display='block';
			
			document.getElementById('winSportPlus').style.display='none';
			document.getElementById('winSportPlusT').style.display='none';
			document.getElementById('winSportPlusP').style.display='none';
			document.getElementById('premiumPlusT').style.display='none';		
			document.getElementById('premiumPlusP').style.display='none';
		} else {
			document.getElementById('winSportMinus').style.display='none';
			document.getElementById('winSportMinusT').style.display='none';
			document.getElementById('winSportMinusP').style.display='none';
			document.getElementById('premiumMinusT').style.display='none';		
			document.getElementById('premiumMinusP').style.display='none';
			
			document.getElementById('winSportPlus').style.display='block';
			document.getElementById('winSportPlusT').style.display='block';
			document.getElementById('winSportPlusP').style.display='block';
			document.getElementById('premiumPlusT').style.display='block';		
			document.getElementById('premiumPlusP').style.display='block';
		}
	}
	function funRemoveVal(val) {	
	if (!document.getElementById('chkWinSport'+val).checked) {
	
		document.getElementById('winSportPlus'+val).style.display='block';		
		document.getElementById('premiumPlus'+val).style.display='block';
		
		//document.getElementById('winSportMinus'+val).style.display='none';
		document.getElementById('premiumMinus'+val).style.display='none';
		
	} else {
		//document.getElementById('winSportMinus'+val).style.display='block';
		document.getElementById('premiumMinus'+val).style.display='block';
		document.getElementById('premiumPlus'+val).style.display='none';
	}
	}
	function getList(val, id) {
		postRequest('${pageContext.request.contextPath}/'+id+'Travel.action'+val, id);
	
	}
	function customerSelectionAction()
	{	
		var brokerCode='';	
		if(document.travel.brokerCode){			
		var brokerCode=document.travel.brokerCode.value;		
		}		
		var URL ='${pageContext.request.contextPath}/customerSelectionQuotation.action?brokerCode='+brokerCode;
		return viewPopUp(URL);
	}
	$(function() {
		try {			
			var dt = new Date();
			dt.setFullYear(new Date().getFullYear()-18);
			$(".dobDatePikcer").datepicker({
				todayHighlight: true,
	        	format: "dd/mm/yyyy"
			  	//viewMode: "years",
			  	//endDate: dt
			}).on('changeDate', function(e){
	            $(this).datepicker('hide');
	        });
			$('.passExpDate').datepicker({
				todayHighlight: true,
				format: "dd/mm/yyyy",
			}).on('changeDate', function(e){
			    $(this).datepicker('hide');
			});
			$('#inceptionDt').datepicker({
				todayHighlight: true,
				format: "dd/mm/yyyy",
			}).on('changeDate', function(e){
			    $(this).datepicker('hide');
			});			
		} catch(err){
			console.error(err);
		}
	});
	function viewPopUp() {
		var bwidth = window.innerWidth;
		var bwidth1 = document.body.clientWidth;
		if(bwidth <= 768) {
			return popUp(URL,bwidth1,'500');
		} else {
			return popUp(URL,'750','500');
		}
	}
	function reloadCaptcha(){
	    $("#captcha").attr("src", "${pageContext.request.contextPath}/simpleCaptcha.jpg");
	}
</script>
</body>
</html>