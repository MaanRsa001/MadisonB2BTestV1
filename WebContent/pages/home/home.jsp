<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ page import="java.util.List,java.util.ArrayList,java.util.Map"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page import="nl.captcha.Captcha"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html <s:if test="locale.language == 'ar'">dir="rtl"</s:if> >
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<style>
		#loading {
		  width: 100%;
		  height: 100%;
		  top: 0px;
		  left: 0px;
		  position: fixed;
		  display: block;
		  opacity: 0.7;
		  background-color: #fff;
		  z-index: 99;
		  text-align: center;
		}
		
		#loading-image {
		  position: absolute;
		  top: 30%;
		  left: 45%;
		  z-index: 100;
		  width: 100px;
		  height: 100px;
		}
	</style>
</head>
<body>
<s:if test='%{!"admin".equals(#session.usertype) && !"RSAIssuer".equals(#session.usertype)}'>
	<s:set var="disable" value="%{menuType=='RA'}"/>
</s:if>
<s:set var="format" value="%{'number.format'}"/>
<div class="row">
	<div class='${("User"==userType||"B2C"==session.LoginType)?"col-xs-12 col-sm-12 col-md-9 col-lg-9":"col-xs-12"}'>
		<s:form name="quotation" theme="simple">
			<div class="row">
				<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
					<div class="panel panel-primary">
						<div class="panel-body"  style="padding: 0px;">
							<img alt="Home Banner" src="<%=request.getContextPath()%>/images/home-quote-header.jpg" style="width: 100%; height: auto;">
						</div>				
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
					<span style="color:red;"> <s:actionerror/> </span>
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
					<s:include value="/pages/customer/customerDetailsMotor.jsp"/>
				</div>
			</div>
			<div class="row">
				<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
					<div class="panel panel-primary">
						<div class="panel-heading">
						<s:if test='"3".equalsIgnoreCase(schemeId)'>Home Insurance - </s:if>
						<s:elseif test='"6".equalsIgnoreCase(schemeId)'>Money - </s:elseif>
						<s:elseif test='"7".equalsIgnoreCase(schemeId)'>Fire and Material Damage Policy - </s:elseif>
						<s:elseif test='"8".equalsIgnoreCase(schemeId)'>Burglary - </s:elseif>
						<s:elseif test='"9".equalsIgnoreCase(schemeId)'>Plant all risks - </s:elseif>
						<s:elseif test='"10".equalsIgnoreCase(schemeId)'>Yact Insurance - </s:elseif>
						<s:elseif test='"11".equalsIgnoreCase(schemeId)'>Personal Accident - </s:elseif>
							<s:text name="coverage.details"/>
							<s:if test='%{!"0".equals(#session.ContentType)}'>
								<span class="pullRight">
									<s:text name="home.packagePlan"/>&nbsp;:&nbsp;
									<s:property value="%{#session.ContentTypeName}"/>
								</span>
							</s:if>
						</div>
						<div class="panel-body">
							<table width="100%" align="center" class="footable">
								<thead>
									<s:if test='%{"admin".equals(#session.usertype) || "RSAIssuer".equals(#session.usertype)}'>
									<tr>
										<th><s:text name="label.coverage"/></th>
										<th><s:text name="label.help"/></th>
										<th><s:text name="coverage.sumInsured"/> <%-- in [<s:property value="#session.BrokerDetails.CurrencyAbb"/>]  --%> </th>
										<th><s:text name="label.baseRate"/></th>
										<th><s:text name="label.premium"/> in [<s:property value="#session.BrokerDetails.CurrencyAbb"/>] </th>
									</tr>
									</s:if>
									<s:else>
									<tr>
										<th><s:text name="label.coverage"/></th>
										<th><s:text name="label.help"/></th>
										<th><s:text name="coverage.sumInsured"/> <%-- in [<s:property value="#session.BrokerDetails.CurrencyAbb"/>]  --%> </th>
									</tr>
									</s:else>					
								</thead>							
								<s:set var="subcount" value="0"/>
								<s:set var="maincount" value="0"/>
								<s:iterator value="Home" var="Home" status ="itemIndex">
									<s:if test='"11".equalsIgnoreCase(schemeId)'>
										<tr class="tableHeading">
											<s:if test='%{#Home.COVERAGES_ID=="87"}'>
												<s:if test='%{"admin".equals(#session.usertype) || "RSAIssuer".equals(#session.usertype)}'>
													<s:else><td colspan="5" ><s:text name="Terms Of Coverage"/></td></s:else>
												</s:if>
												<s:else><td colspan="3" ><s:text name="Terms Of Coverage"/></td></s:else>
											</s:if>
											<s:elseif test='%{#Home.COVERAGES_ID=="93"}'>
												<s:if test='%{"admin".equals(#session.usertype) || "RSAIssuer".equals(#session.usertype)}'>
													<s:else><td colspan="5" ><s:text name="Table Of Benefits"/></td></s:else>
												</s:if>
												<s:else><td colspan="3" ><s:text name="Table Of Benefits"/></td></s:else>
											</s:elseif>
										</tr>
									</s:if>	
									<s:else>
										<s:if test='%{"B".equals(COVERAGES_TYPE) && #maincount==0}'>
											<tr class="tableHeading">
												<s:if test='%{"admin".equals(#session.usertype) || "RSAIssuer".equals(#session.usertype)}'>
													<td colspan="5" ><s:text name="label.main.cover"/></td>
												</s:if>
												<s:else><td colspan="3" ><s:text name="label.main.cover"/></td></s:else>
											</tr>							
											<s:set var="maincount" value="1"/>
										</s:if>
										<s:elseif test='%{"O".equals(COVERAGES_TYPE) && #subcount==0}'>	
											 <tr class="tableHeading">
												<s:if test='%{"admin".equals(#session.usertype) || "RSAIssuer".equals(#session.usertype)}'>
													<td colspan="5"><s:text name="label.additional.cover"/></td>
												</s:if>
												<s:else><td colspan="3"><s:text name="label.additional.cover"/></td></s:else>
					           				 </tr>						
											<s:set var="subcount" value="1"/>
										</s:elseif>
									</s:else>					
									<tbody>
									<s:if test='"Y"==SUB_COVERAGES'>						
										<s:if test='%{"admin".equals(#session.usertype) || "RSAIssuer".equals(#session.usertype)}'>
										<tr class="tableHeading">
											<td colspan="5">${COVERAGES_NAME}</td>
										</tr>
										</s:if>
										<s:else>
										<tr class="tableHeading">
											<td colspan="3">${COVERAGES_NAME}</td>
										</tr>
										</s:else>
										<s:iterator value="Subhome" var="sublist" status ="item">
											<s:if test='%{#Home.COVERAGES_ID==#sublist.COVERAGES_ID}'>
											<tr>
												<td> <div style="margin-left: 20px;"> ${COVERAGES_NAME} </div> </td>
												<td style="text-align: center;">
													<a data-toggle="modal" data-refresh="true" href="${pageContext.request.contextPath}/getHelpInfoHome.action?helpInfo=${HELP_CONTENTS_ID}&coverId=${COVERAGES_ID}" data-target="#helpInfo${COVERAGES_ID}" style="text-decoration: none;"><img src="${pageContext.request.contextPath}/images/Help.png" width="20" height="20"/></a>
													<div class="modal fade" data-refresh="true" id="helpInfo${COVERAGES_ID}" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
														<div class="modal-dialog modal-lg">
															<div class="modal-content">
																<div class="modal-header">
																	<h4 class="modal-title"> Help Info </h4>
																</div>
																<div class="modal-body">
																	<div class="te"></div>
																</div>
															</div>
														</div>
													</div>
												</td>
												<td >
													<s:if test='%{"textbox"==SUB_CONTROL_TYPE}'>
														<s:textfield id="subtext[%{#sublist.COVERAGES_SUB_ID}]" cssStyle="text-align: right;" name="COVERAGES_COVERED_EMPLOYEES[%{#item.count}]" value='%{hasActionErrors()?subSumInsuredList[#item.count-1]:((COVERAGES_COVERED_EMPLOYEES==null || "".equals(COVERAGES_COVERED_EMPLOYEES))?"":getText(#format,{@java.lang.Double@valueOf(COVERAGES_COVERED_EMPLOYEES)}))}' cssClass="inputBox tooltipContent" data-content="%{REMARKS}" onkeyup="checkDecimals13(this);javascript:this.value=Comma(this.value);updateCoverValue('%{#sublist.COVERAGES_SUB_ID}');" disabled="#disable" maxLength="13"/>
													</s:if>
													<s:elseif test='%{"dropdown"==SUB_CONTROL_TYPE}'>
														<s:select id="subselect[%{#sublist.COVERAGES_SUB_ID}]" name="COVERAGES_COVERED_EMPLOYEES[%{#item.count}]"  list="DropDown" listKey="LISTKEY" listValue="LISTVALUE" headerKey="" headerValue="---None---" value='%{hasActionErrors()?subSumInsuredList[#item.index]:#sublist.COVERAGES_COVERED_EMPLOYEES}' cssClass="inputBoxS tooltipContent" data-content="%{REMARKS}"  disabled="#disable"/>
													</s:elseif>
													<s:elseif test='%{"Display"==SUB_CONTROL_TYPE}'>
														<s:set var="tempCoverAmount" value='%{hasActionErrors()?subSumInsuredList[#item.index]:((#sublist.COVERAGES_COVERED_EMPLOYEES!=null && !"".equals(#sublist.COVERAGES_COVERED_EMPLOYEES))?getText(#format,{@java.lang.Double@valueOf(#sublist.COVERAGES_COVERED_EMPLOYEES)}):( (SUM_INSURED_LIMIT==null || "".equals(SUM_INSURED_LIMIT))?"":getText(#format,{@java.lang.Double@valueOf(SUM_INSURED_LIMIT)}) )) }'/>
														<s:textfield id="subtext[%{#sublist.COVERAGES_SUB_ID}]" cssStyle="text-align: right;" name="COVERAGES_COVERED_EMPLOYEES[%{#item.count}]" value='%{#tempCoverAmount}' cssClass="inputBox tooltipContent" data-content="%{REMARKS}" onkeyup="checkDecimals13(this);javascript:this.value=Comma(this.value);" disabled='%{("admin".equalsIgnoreCase(#session.usertype) || "RSAIssuer".equals(#session.usertype))?"false":"true"}' maxLength="13"/>
													</s:elseif>
													<s:elseif test='%{"Radio"==SUB_CONTROL_TYPE}'>
														<s:radio id="subtext[%{#sublist.COVERAGES_SUB_ID}]" name="COVERAGES_COVERED_EMPLOYEES[%{#item.count}]" list="DropDown" listKey="LISTKEY" listValue="LISTVALUE" value='%{hasActionErrors()?COVERAGES_COVERED_EMPLOYEES[#item.count]:(COVERAGES_COVERED_EMPLOYEES==null?#sublist.SUM_INSURED_LIMIT:#sublist.COVERAGES_COVERED_EMPLOYEES)}' disabled="#disable"/>
													</s:elseif>
													<s:elseif test='%{"textarea"==SUB_CONTROL_TYPE}'>
														<s:textarea id="subtext[%{#sublist.COVERAGES_SUB_ID}]" name="COVERAGES_COVERED_EMPLOYEES[%{#item.count}]" value="%{hasActionErrors()?subSumInsuredList[#item.count-1]?SUB_COVERAGES_LIMIT}" cssClass="inputBox" onkeyup="checkDecimals13(this);" onblur="validamt(this);" disabled="#disable" maxLength="10"/>
													</s:elseif>
												</td>
												<s:if test='%{"admin".equals(#session.usertype) || "RSAIssuer".equals(#session.usertype)}'>
													<td>
													<span style="font-weight: bold;float:right;">
														<s:if test='%{("A".equals(#sublist.GRID_CALC_TYPE) || #sublist.GRID_CALC_TYPE==null) && "A".equals(#sublist.CALC_TYPE)}'><s:property value="#session.BrokerDetails.CurrencyAbb"/></s:if>
														<s:elseif test='%{"A".equals(#sublist.GRID_CALC_TYPE) && "G".equals(#sublist.CALC_TYPE)}'><s:property value="#session.BrokerDetails.CurrencyAbb"/>&nbsp; G</s:elseif>
														<s:elseif test='%{"A".equals(#sublist.GRID_CALC_TYPE) && "P".equals(#sublist.CALC_TYPE)}'><s:property value="#session.BrokerDetails.CurrencyAbb"/>&nbsp; %</s:elseif>
														<s:elseif test='%{#sublist.GRID_CALC_TYPE!=null && "P".equals(#sublist.GRID_CALC_TYPE)}'>G%</s:elseif>
														<s:elseif test='%{#sublist.GRID_CALC_TYPE!=null && "M".equals(#sublist.GRID_CALC_TYPE)}'>GM</s:elseif>
														<s:elseif test='%{#sublist.GRID_CALC_TYPE==null && "P".equals(#sublist.CALC_TYPE)}'>%</s:elseif>
														<s:elseif test='%{#sublist.GRID_CALC_TYPE==null && "M".equals(#sublist.CALC_TYPE)}'>M</s:elseif>
													</span>
														<s:textfield name="COVERAGES_SUB_BASE_RATE[%{COVERAGES_SUB_ID}]" value="%{#sublist.COVERAGES_SUB_BASE_RATE}" cssClass="inputBox" cssStyle="width: 80%; text-align:right;" onkeyup="checkDecimals13(this);" onblur="validamt(this);" />
													<%--								
													<s:hidden name="COVERAGES_SUB_BASE_RATE[%{#item.count}]" value="%{#sublist.COVERAGES_SUB_BASE_RATE}"/>
													--%>								
													</td>
													<td width="15%" align="right"><b><s:property value="getText('{0,number,#,##0.00}',{#sublist.PREMIUM_AMOUNT})"/></b></td>
												</s:if>
											</tr>
											<%--
											<s:hidden name="SUB_SUM_INSURED_LIMIT[%{#item.count}]" value="%{#sublist.SUB_COVERAGES_LIMIT}"/>
											<s:hidden name="SUB_COVERAGES_NAME[%{#item.count}]" value="%{#sublist.COVERAGES_NAME}"/>
											--%>
											</s:if>
										</s:iterator>
									</s:if>
									<s:else>
										<s:if test='%{!"P".equals(COVERAGES_TYPE)}'>
											<tr>
												<td> ${COVERAGES_NAME} </td>
												<td style="text-align: center;"> 
													<a data-toggle="modal" data-refresh="true" href="${pageContext.request.contextPath}/getHelpInfoHome.action?helpInfo=${HELP_CONTENTS_ID}&coverId=${COVERAGES_ID}" data-target="#helpInfo${COVERAGES_ID}" style="text-decoration: none;"><img src="${pageContext.request.contextPath}/images/Help.png" width="20" height="20"/></a>
													<div class="modal fade" data-refresh="true" id="helpInfo${COVERAGES_ID}" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
														<div class="modal-dialog modal-lg">
															<div class="modal-content">
																<div class="modal-header">
																	<h4 class="modal-title"> Help Info </h4>
																</div>
																<div class="modal-body">
																	<div class="te"></div>
																</div>
															</div>
														</div>
													</div>
												</td>
												<s:if test='"textbox"==SUM_INSURED_CONTROL_TYPE'>
													<td >
														<s:textfield name="SUM_INSURED[%{#itemIndex.count}]" id="text[%{#Home.COVERAGES_ID}]" value='%{hasActionErrors()?sumInsuredList[#itemIndex.count-1]:( (SUM_INSURED==null || "".equals(SUM_INSURED))?"":getText(#format,{@java.lang.Double@valueOf(SUM_INSURED)}) ) }' cssClass="inputBox tooltipContent" data-content="%{REMARKS}" onkeyup="checkDecimals13(this);javascript:this.value=Comma(this.value);updateCoverValue('%{#Home.COVERAGES_ID}');" disabled="#disable" maxLength="13" cssStyle="text-align: right;" />
													</td>
												</s:if>
												<s:if test='"dropdown"==SUM_INSURED_CONTROL_TYPE'>
													<s:if test='%{#Home.COVERAGES_ID=="94"}'>
														<td id="lossLimbs">
															<s:select name="SUM_INSURED[%{#itemIndex.count}]" id="text[%{#Home.COVERAGES_ID}]" list="DropDown" listKey="LISTKEY" listValue="LISTVALUE" headerKey="" headerValue="---None---" value="%{hasActionErrors()?sumInsuredList[#itemIndex.count-1]:SUM_INSURED}" cssClass="inputBoxS tooltipContent" data-content="%{REMARKS}" onchange="setLossLimbs(this.value);" disabled="#disable"/>
														</td>
													</s:if>
													<s:elseif test='%{#Home.COVERAGES_ID=="105"}'>
														<td>
															<div id="homeLossOfLimbs">
																<s:select name="SUM_INSURED[%{#itemIndex.count}]" id="text[%{#Home.COVERAGES_ID}]" list="DropDown" listKey="LISTKEY" listValue="LISTVALUE" headerKey="" headerValue="---None---" value="%{hasActionErrors()?sumInsuredList[#itemIndex.count-1]:SUM_INSURED}" cssClass="inputBoxS tooltipContent" data-content="%{REMARKS}" disabled="true"/>
															</div>
														</td>
													</s:elseif>
													<s:else>
														<td >
															<s:select name="SUM_INSURED[%{#itemIndex.count}]" id="text[%{#Home.COVERAGES_ID}]" list="DropDown" listKey="LISTKEY" listValue="LISTVALUE" headerKey="" headerValue="---None---" value="%{hasActionErrors()?sumInsuredList[#itemIndex.count-1]:SUM_INSURED}" cssClass="inputBoxS tooltipContent" data-content="%{REMARKS}" disabled="#disable"/>
														</td>
													</s:else>
												</s:if>
												<s:if test='"Display"==SUM_INSURED_CONTROL_TYPE'>
													<s:if test='%{#Home.COVERAGES_ID=="105"}'>
														<td>
															<div id="homeLossOfLimbs">
																<s:textfield name="SUM_INSURED[%{#itemIndex.count}]" id="text[%{#Home.COVERAGES_ID}]" cssStyle="text-align: right;" value='%{hasActionErrors()?sumInsuredList[#itemIndex.count-1]:( (SUM_INSURED_LIMIT==null || "".equals(SUM_INSURED_LIMIT))?"":getText(#format,{@java.lang.Double@valueOf(SUM_INSURED_LIMIT)}) ) }' cssClass="inputBox tooltipContent" data-content="%{REMARKS}" onkeyup="checkDecimals13(this);javascript:this.value=Comma(this.value);" disabled='%{("admin".equalsIgnoreCase(#session.usertype) || "RSAIssuer".equals(#session.usertype))?"false":"true"}' maxLength="13"/>
															</div>
														</td>
													</s:if>
													<s:else>
														<td >
															<s:textfield name="SUM_INSURED[%{#itemIndex.count}]" id="text[%{#Home.COVERAGES_ID}]" cssStyle="text-align: right;" value='%{hasActionErrors()?sumInsuredList[#itemIndex.count-1]:( (SUM_INSURED_LIMIT==null || "".equals(SUM_INSURED_LIMIT))?"":getText(#format,{@java.lang.Double@valueOf(SUM_INSURED_LIMIT)}) ) }' cssClass="inputBox tooltipContent" data-content="%{REMARKS}" onkeyup="checkDecimals13(this);javascript:this.value=Comma(this.value);" disabled='%{("admin".equalsIgnoreCase(#session.usertype) || "RSAIssuer".equals(#session.usertype))?"false":"true"}' maxLength="13"/>
														</td>
													</s:else>
												</s:if>
												<s:if test='"Radio"==SUM_INSURED_CONTROL_TYPE'>
													<td>
														<s:radio name="SUM_INSURED[%{#itemIndex.count}]" id="text[%{#Home.COVERAGES_ID}]"  list="DropDown" listKey="LISTKEY" listValue="LISTVALUE" disabled="#disable" cssClass="tooltipContent" data-content="%{REMARKS}"
														value='%{hasActionErrors()?sumInsuredList[#itemIndex.count-1]:(#Home.SUM_INSURED==null?#Home.SUM_INSURED_LIMIT:#Home.SUM_INSURED)}'/>
													</td>
												</s:if>
												<s:if test='%{"admin".equals(#session.usertype) || "RSAIssuer".equals(#session.usertype)}'>
													<td align="left">
													<span style="font-weight: bold; float: right;">
													<s:if test='%{("A".equals(#Home.GRID_CALC_TYPE) || #Home.GRID_CALC_TYPE==null) && "A".equals(#Home.CALC_TYPE)}'><s:property value="#session.BrokerDetails.CurrencyAbb"/></s:if>
													<s:elseif test='%{"A".equals(#Home.GRID_CALC_TYPE) && "G".equals(#Home.CALC_TYPE)}'><s:property value="#session.BrokerDetails.CurrencyAbb"/>&nbsp; G</s:elseif>
													<s:elseif test='%{"A".equals(#Home.GRID_CALC_TYPE) && "P".equals(#Home.CALC_TYPE)}'><s:property value="#session.BrokerDetails.CurrencyAbb"/>&nbsp; %</s:elseif>
													<s:elseif test='%{#Home.GRID_CALC_TYPE!=null && "P".equals(#Home.GRID_CALC_TYPE)}'>G%</s:elseif>
													<s:elseif test='%{#Home.GRID_CALC_TYPE!=null && "M".equals(#Home.GRID_CALC_TYPE)}'>GM</s:elseif>
													<s:elseif test='%{#Home.GRID_CALC_TYPE==null && "P".equals(#Home.CALC_TYPE)}'>%</s:elseif>
													<s:elseif test='%{#Home.GRID_CALC_TYPE==null && "M".equals(#Home.CALC_TYPE)}'>M</s:elseif>
													</span>
													<%--
													<s:textfield name="COVERAGES_BASE_RATE[%{COVERAGES_ID}]" value="%{COVERAGES_BASE_RATE}" cssClass="inputBox tooltipContent" data-content="Coverage Base Rate" cssStyle="width: 80%; text-align:right;"  onkeyup="checkDecimals13(this);" onblur="validamt(this);" />
													--%>
													<s:textfield name="COVERAGES_BASE_RATE[%{#itemIndex.count}]" value="%{COVERAGES_BASE_RATE}" cssClass="inputBox tooltipContent" data-content="Coverage Base Rate" cssStyle="width: 80%; text-align:right;"  onkeyup="checkDecimals13(this);" onblur="validamt(this);" />
													</td>
													<td width="15%" align="right"><b><s:property value="getText('{0,number,#,##0.00}',{PREMIUM_AMOUNT})"/></b></td>
												</s:if>
											</tr>
										</s:if>
									</s:else>
									</tbody>
									<s:hidden name="COVER_ID[%{#itemIndex.count}]" value="%{COVERAGES_ID}"/>
									<%--
									<s:hidden name="COVERAGES_SUB_ID[%{#itemIndex.count}]" value="%{COVERAGES_SUB_ID}"/>
									
									<s:hidden name="CALC_TYPE[%{#itemIndex.count}]" value="%{CALC_TYPE}"/>
									<s:hidden name="COVERAGES_Y_N_OPTION[%{#itemIndex.count}]" value="%{COVERAGES_Y_N_OPTION}"/>
									<s:if test='%{!"admin".equals(#session.usertype)}'>
										<s:hidden name="COVERAGES_BASE_RATE[%{#itemIndex.count}]" value="%{COVERAGES_BASE_RATE}"/>
									</s:if>
									<s:hidden name="COVERAGES_NAME[%{#itemIndex.count}]" value="%{COVERAGES_NAME}"/>
									<s:hidden name="SUB_COVERAGES[%{#itemIndex.count}]" value="%{SUB_COVERAGES}"/>
									<s:hidden name="SUM_INSURED_LIMIT[%{#itemIndex.count}]" value="%{SUM_INSURED_LIMIT}"/>
									<s:hidden name="MIN_SUM_INSURED[%{#itemIndex.count}]" value="%{MIN_SUM_INSURED}"/>
									--%>
								</s:iterator>												
							</table>
						</div>
					</div>
					<div class="row">
						<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
							<s:if test="%{schemeList.size>1}">
								<s:if test='prevScheme==null || "".equals(prevScheme)'>
								</s:if>
								<s:else>
									<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" align="left">
										<button type="button" class="btn btn-sm btn-warning" 
										onclick="nextFun('editSchemeHome.action')">Edit Previous</button>
									</div>
								</s:else>
								<s:if test='nextScheme==null || "".equals(nextScheme)'>
								</s:if>
								<s:else>
									<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" align="right">
										<button type="button" class="btn btn-sm btn-success" 
										onclick="nextFun('nextSchemeHome.action')">Save & Proceed Next</button>
									</div>
								</s:else>
							</s:if>
						</div>
					</div>
					<br>
					<div class="panel panel-primary">
						<div class="panel-heading">
							Policy Details
						</div>
						<div class="panel-body">
							<s:if test='"Y".equals(paTractorOption)'>
								<div class="textfield33">
									<div class="text">
										Personal Accident Cover
									</div>
									<div class="tbox">
										<s:radio name="paCoverYN" id="paCoverYN" list="#{'Y':'Yes','N':'No'}" value='%{paCoverYN==null||"".equals(paCoverYN)?"N":paCoverYN}' disabled="#disable"/>
									</div>
								</div>
								<div class="textfield33">
									<div class="text">
										Tractor Cover
									</div>
									<div class="tbox">
										<s:radio name="tractorYN" id="tractorYN" list="#{'Y':'Yes','N':'No'}" value='%{tractorYN==null||"".equals(tractorYN)?"N":tractorYN}' disabled="#disable"/>
									</div>
								</div>
								<br class="clear"/>
							</s:if>
							<%--
							<s:else>
								<s:hidden name="paCoverYN" value="N"/>
								<s:hidden name="tractorYN" value="N"/>
							</s:else>
							--%>
							<div class="textfield33">
								<div class="text">
									<s:text name="home.coverPeriod"/><font color="red">*</font>
								</div>
								<div class="tbox">
									<s:textfield id="homepolicyStartDate" name="inceptionDt" cssClass="inputBox datePicker tooltipContentR" data-content="When you want your policy to be effective. Can only be a future date." readonly="true" disabled="#disable" onchange="getAjaxModel(this.form,'?inceptionDt='+this.value,'homepolicyEndList')"/>
								</div>
							</div>
							<div class="textfield33">
								<div class="text">
									<s:text name="home.expirePeriod"/><font color="red">*</font>
								</div>
								<div class="tbox">
									<div id="homepolicyEndList">
										<s:select name="expiryDt" id="homeexpiryDt" list="policyEndList" listKey="CODE" listValue="CODEDESC" cssClass="inputBoxS tooltipContent" data-content="When you want your policy to end" disabled="#disable" theme="simple"/>
									</div>							
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<s:if test='"B2C".equalsIgnoreCase(#session.LoginType)'>
			<div class="row">
				<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
					<div class="panel panel-primary">
						<div class="panel-body">
							<div class="row">
								<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
									<div class="text"><s:text name="Please enter the text that you see in the image" /></div>
									<div class="tbox">
										<s:textfield name="captchavalue" id="captchavalue" value="" cssClass="inputBox tooltipContent" data-content="Please enter the text that you see in the image" />
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
			<div class="row">
				<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" align="center">
			       	 <s:if test='%{"admin".equalsIgnoreCase(#session.usertype) || "RSAIssuer".equals(#session.usertype)}'>
			       	 	<%--
		           		<s:submit type="button" name="Cancel" value="Cancel" cssClass="btn btn-sm btn-danger" onclick="fnsubmit('initHome');"/>&nbsp;&nbsp;&nbsp;
		           		--%>
		           		<s:submit type="button" name="calculate" value="Calculate" cssClass="btn btn-sm btn-success" onclick="disableForm(this.form,false,'');forward('getRenderHome.action','calculate')"/>&nbsp;&nbsp;&nbsp;
		           	</s:if>
		           	<s:if test='%{"".equalsIgnoreCase(#session.user) || "madisondirect".equalsIgnoreCase(#session.user)|| "guestmotor".equalsIgnoreCase(#session.user)}'>
						<input type="button" value="Get Quote" class="btn btn-sm btn-primary" onclick="disableForm(this.form,false,'');forward('otpLoginHome.action','proceed')"/>
					</s:if>
					<s:else>
						<button type="button" value="Back" class="btn btn-sm btn-danger" onclick="disableForm(this.form,false,'');forward('packageSelectionHome.action','')">Back</button>
						<s:if test='nextScheme==null || "".equalsIgnoreCase(nextScheme) || "Y".equalsIgnoreCase(btnYN)'>
							<input type="button" value="Get Quote" class="btn btn-sm btn-primary" onclick="disableForm(this.form,false,'');forward('getRenderHome.action','proceed')"/>
						</s:if>
					</s:else>
					<s:hidden name="quoteNo" />
					<s:hidden name="applicationNo" />
					<s:hidden name="menuType"/>
					<s:hidden name="nextScheme" />
				</div>
			</div>
			<s:hidden name="schemeId" />
			<s:hidden name="contentTypeId" />
			<s:hidden name="schemeSelected"/>
			<!--<s:hidden name="schemeList"/>-->
			<s:hidden name="prevScheme" />
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
	function nextFun(action,tpid,nextId){
		//document.quotation.nextScheme.value=nextId;
		document.quotation.action = "${pageContext.request.contextPath}/" + action;
		document.quotation.submit();
	}
	function fnsubmit(action) {
		document.quotation.action = "${pageContext.request.contextPath}/" + action;
		document.quotation.submit();
	}
	function forward(url, from){
		if(from=="proceed"){
			disableForm(document.quotation,false,'')
			document.quotation.action = "${pageContext.request.contextPath}/"+url+"?reqFrom="+from;
			document.quotation.submit();
		}else{
	 		document.quotation.action = "${pageContext.request.contextPath}/"+url+"?reqFrom="+from;
			document.quotation.submit();
		}
	}
	function updateCoverValue(coverId) {
		<s:if test='"1".equals(#session.scheme_id)'>
		if(coverId=="14" || coverId=="20" || coverId=="50") {
			if(coverId=="14") {
				document.getElementById('subtext[18]').value = document.getElementById('subtext[14]').value;
			} else if(coverId=="20") {
				document.getElementById('subtext[19]').value = document.getElementById('subtext[20]').value;
			} else {
				document.getElementById('subtext[18]').value = document.getElementById('subtext[14]').value;
				document.getElementById('subtext[19]').value = document.getElementById('subtext[20]').value;
			}
		}
		</s:if>
		<s:elseif test='"3".equals(#session.scheme_id) && !#disable'>
		if(coverId = "51") {
			if(document.getElementById('text[51]').value!="" && document.getElementById('text[54]').disabled == true) {
				document.getElementById('text[54]').disabled = false;
				document.getElementById('text[57]').disabled = false;
				document.getElementById('text[58]').disabled = false;
				document.getElementById('text[55]1').disabled = false;
				document.getElementById('text[55]2').disabled = false;
				document.getElementById('text[56]1').disabled = false;
				document.getElementById('text[56]2').disabled = false;
				
			} else if(document.getElementById('text[51]').value=="" && document.getElementById('text[54]').disabled == false) {
				document.getElementById('text[54]').disabled = true;
				document.getElementById('text[57]').disabled = true;
				document.getElementById('text[58]').disabled = true;
				document.getElementById('text[55]1').disabled = true;
				document.getElementById('text[55]2').disabled = true;
				document.getElementById('text[56]1').disabled = true;
				document.getElementById('text[56]2').disabled = true;

				document.getElementById('text[54]').value="";
				document.getElementById('text[57]').value="";
				document.getElementById('text[58]').value="";
				document.getElementById('text[55]1').checked = false;
				document.getElementById('text[55]2').checked = true;
				document.getElementById('text[56]1').checked = false;
				document.getElementById('text[56]2').checked = true;
			}
		}
		</s:elseif>
	}
	try {
		updateCoverValue('51');
	} catch(err){
		console.error(err);
	}
	
	function getList(val, id) {
		postRequest('${pageContext.request.contextPath}/'+id+'Home.action'+val, id);
	}
	function reloadCaptcha(){
	    $("#captcha").attr("src", "${pageContext.request.contextPath}/simpleCaptcha.jpg");
	}
	$(function() {
		try {
			$('#homepolicyStartDate').datepicker({
				todayHighlight: true,
				format: "dd/mm/yyyy",
				startDate: '-0d'
			}).on('changeDate', function(e){
			    $(this).datepicker('hide');
			});
		} catch(err) {
			console.error(err);
		}
	});
	function getAjaxModel(frm,val, id) {
			postRequest('${pageContext.request.contextPath}/'+id+'Home.action'+val, id);
	}
	function setLossLimbs(val){
		postRequest('${pageContext.request.contextPath}/homeLossOfLimbsHome.action?lossOfLimbs='+val, 'homeLossOfLimbs');
	}
</script>
</body>
</html>