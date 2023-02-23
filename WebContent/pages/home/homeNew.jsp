<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ page import="java.util.List,java.util.ArrayList,java.util.Map"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page import="nl.captcha.Captcha"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html <s:if test="locale.language == 'ar'">dir="rtl"</s:if> >
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/dataTables/css/dataTables.responsive.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/dataTables/css/dataTables.jqueryui.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/dataTables/css/dataTables.tableTools.css">
	<script type="text/javascript" src="${pageContext.request.contextPath}/dataTables/js/dataTables.responsive.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/dataTables/js/dataTables.tableTools.js"></script>
    <style>
        .CoverdetailsPage .card {
            padding: 20px 20px 20px 120px;
            border: 0px !important
        }

        .CoverdetailsPage .Card_Parent {
            border-radius: 4px;
            background: #fff;
            box-shadow: 0 6px 10px rgba(0, 0, 0, .08), 0 0 6px rgba(0, 0, 0, .05);
            transition: .3s transform cubic-bezier(.155, 1.105, .295, 1.12), .3s box-shadow, .3s -webkit-transform cubic-bezier(.155, 1.105, .295, 1.12);
        }

        .CoverdetailsPage .plan_Card {
            box-shadow: 0 1px 1px rgba(0, 0, 0, 0.25),
                0 2px 2px rgba(0, 0, 0, 0.20),
                0 4px 4px rgba(0, 0, 0, 0.15),
                0 8px 8px rgba(0, 0, 0, 0.10),
                0 16px 16px rgba(0, 0, 0, 0.05);
            padding: 10px;
            border-radius: 5px;
            background-color: aliceblue;
        }

        .CoverdetailsPage .card h3 {
            font-weight: 700;
            color: #261e6a;
        }

        .CoverdetailsPage .card-1 {
            background-image: url("assets/Images/home-insurances.png");
            background-repeat: no-repeat;
            background-position: left;
            background-size: 200px;
        }

        .CoverdetailsPage .LabelHeading {
            font-weight: bolder;
        }

        .covertable th{
          font-size: 14px;
        }

        .CoverdetailsPage .card-2 {
            padding: 20px 30px 20px 30px;

        }
    </style>
</head>
<body>
	<div id="loading" style="width:100%">
	   <img id="loading-image" src="${pageContext.request.contextPath}/images/madisonSymbolLogo.png"/>
	</div>
	<s:if test='%{!"admin".equals(#session.usertype) && !"RSAIssuer".equals(#session.usertype)}'>
		<s:set var="disable" value="%{menuType=='RA'}"/>
	</s:if>
	<s:set var="format" value="%{'number.format'}"/>
	<s:form name="quotation" theme="simple" method="POST">
	    <div class="container CoverdetailsPage mt-5">
	        <div class="Card_Parent Coverdetails">
	        	<s:set value="editQuoteDetails"/>
	            <div class="card card-1">
	                <h3>Policy Information</h3>
	                <hr>
	                <div class="row">
	                    <div class="col-md-6 col-6">
	                        <label class="LabelHeading"><s:text name="motor.quoteNo"/></label>
	                    </div>
	                    <div class="col-md-6 col-6">
	                        <label class="labelValues"><s:property value="quoteNo"/></label>
	                    </div>
	                    <div class="col-md-6 col-6">
	                        <label class="LabelHeading"><s:text name="motor.customerName"/></label>
	                    </div>
	                    <div class="col-md-6 col-6">
	                        <label class="labelValues"><s:property value="customerFullName"/></label>
	                    </div>
	                    <div class="col-md-6 col-6">
	                        <label class="LabelHeading"><s:text name="motor.email" /></label>
	                    </div>
	                    <div class="col-md-6 col-6">
	                        <label class="labelValues"><s:property value="email"/></label>
	                    </div>
	                    <div class="col-md-6 col-6">
	                        <label class="LabelHeading"><s:text name="Mobile No." /></label>
	                    </div>
	                    <div class="col-md-6 col-6">
	                        <label class="labelValues"><s:property value="mobileNo"/></label>
	                    </div>
	                    <div class="col-md-6 col-6">
	                        <label class="LabelHeading"><s:text name="motor.policyStartDt" /></label>
	                    </div>
	                    <div class="col-md-6 col-6">
	                        <label class="labelValues"><s:property value="inceptionDt"/></label>
	                    </div>
	                    <div class="col-md-6 col-6">
	                        <label class="LabelHeading"><s:text name="motor.policyEndDt" /></label>
	                    </div>
	                    <div class="col-md-6 col-6">
	                        <label class="labelValues"><s:property value="expiryDt"/></label>
	                    </div>
	                </div>
	            </div>
	        </div>
	        <div class="Card_Parent mt-4">
	            <div class="card card-2">
	            	<div class="row rowFlex">
		            	<div class="col-md-8 col-12">
			                <h3>
			                	<s:if test='"3".equalsIgnoreCase(schemeId)'>Home Insurance - </s:if>
								<s:elseif test='"6".equalsIgnoreCase(schemeId)'>Money - </s:elseif>
								<s:elseif test='"7".equalsIgnoreCase(schemeId)'>Fire and Material Damage Policy - </s:elseif>
								<s:elseif test='"8".equalsIgnoreCase(schemeId)'>Burglary - </s:elseif>
								<s:elseif test='"9".equalsIgnoreCase(schemeId)'>Plant all risks - </s:elseif>
								<s:elseif test='"10".equalsIgnoreCase(schemeId)'>Yact Insurance - </s:elseif>
								<s:elseif test='"11".equalsIgnoreCase(schemeId)'>Personal Accident - </s:elseif>
								<s:elseif test='"12".equalsIgnoreCase(schemeId)'>Domestic Package - </s:elseif>
								<s:elseif test='"13".equalsIgnoreCase(schemeId)'>Office Comprehensive - </s:elseif>
								<s:elseif test='"14".equalsIgnoreCase(schemeId)'>Electronic Equipment - </s:elseif>
								<s:elseif test='"15".equalsIgnoreCase(schemeId)'>Business All Risk - </s:elseif>
								<s:elseif test='"16".equalsIgnoreCase(schemeId)'>Employers Liability - </s:elseif>
								<s:elseif test='"17".equalsIgnoreCase(schemeId)'>Public Liability - </s:elseif>
								<s:elseif test='"18".equalsIgnoreCase(schemeId)'>Special Type - </s:elseif>
								<s:elseif test='"19".equalsIgnoreCase(schemeId)'>Motor Policy - </s:elseif>
								<s:text name="coverage.details"/>
								<s:if test='%{!"0".equals(#session.ContentType)}'>
									<span class="pullRight">
										<s:text name="home.packagePlan"/>&nbsp;:&nbsp;
										<s:property value="%{#session.ContentTypeName}"/>
									</span>
								</s:if>
			                </h3>
			            </div>
		            	<div class="col-md-4 col-12">
		            		<s:if test='"1".equalsIgnoreCase(locationSize)'>
		            			<h5><b>Location - <s:property value="singleLocName"/></b></h5>
		            		</s:if>
		            	</div>
			        </div>
	                <hr>
	                <div class="row">
	                    <div class="col-md-6">
	                        <label class="LabelHeading">Do You Want To Modify the Details</label> &nbsp;&nbsp;
	                        <s:radio list="#{'Y':'Yes','N':'No'}" name="modifyYN" id="modifyYN" value='%{modifyYN==null?"N":modifyYN}' onchange="getModifyDetails(this.value)"/>
	                    </div>
	                </div>
	                <s:actionerror style="color: red;"/>
	                <s:if test='"1".equalsIgnoreCase(locationSize) || "".equalsIgnoreCase(locationSize)'>
						<div class="customTable mt-5 container"  class="customTable mt-5 container" style="overflow:scroll;width:100%;overflow:auto;">
							<table class="table table-bordered" cellspacing="0" width="100%" >
								<thead align="center" style="background-color:#261e6a;color:white;">
									<s:if test='%{"admin".equals(#session.usertype) || "RSAIssuer".equals(#session.usertype)}'>
										<tr>
											<th><s:text name="label.coverage"/></th>
											<th><s:text name="label.help"/></th>
											<th><s:text name="coverage.sumInsured"/>
											<th><s:text name="label.baseRate"/></th>
											<%--<th><s:text name="label.premium"/> in [ZMW] </th> --%>
										</tr>
									</s:if>
									<s:else>
										<tr>
											<th><s:text name="label.coverage"/></th>
											<th><s:text name="label.help"/></th>
											<th><s:text name="coverage.sumInsured"/> <%-- in [ZMW]  --%> </th>
										</tr>
									</s:else>					
								</thead>							
								<s:set var="subcount" value="0"/>
								<s:set var="maincount" value="0"/>
								<s:iterator value="Home" var="Home" status ="itemIndex">
									<s:if test='"11".equalsIgnoreCase(schemeId)'>
										<tr class="tableHeading"  align="center"  style="background-color:#261e6a;color:white;border-radius:20px;" >
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
											<tr class="tableHeading"  align="center" style="background-color:#261e6a;color:white;border-radius:0px 0px 20px 20px;">
												<s:if test='%{"admin".equals(#session.usertype) || "RSAIssuer".equals(#session.usertype)}'>
													<td colspan="5" ><s:text name="label.main.cover"/></td>
												</s:if>
												<s:else><td colspan="3" ><s:text name="label.main.cover"/></td></s:else>
											</tr>							
											<s:set var="maincount" value="1"/>
										</s:if>
										<s:elseif test='%{"O".equals(COVERAGES_TYPE) && #subcount==0}'>	
											 <tr class="tableHeading"  align="center" style="background-color:#261e6a;color:white;">
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
															<s:textfield id="subtext[%{#sublist.COVERAGES_SUB_ID}]" cssStyle="text-align: right;" name="COVERAGES_COVERED_EMPLOYEES[%{#item.count}]" value='%{hasActionErrors()?subSumInsuredList[#item.count-1]:((COVERAGES_COVERED_EMPLOYEES==null || "".equals(COVERAGES_COVERED_EMPLOYEES))?"":getText(#format,{@java.lang.Double@valueOf(COVERAGES_COVERED_EMPLOYEES)}))}' cssClass="form-control"  onkeyup="checkDecimals13(this);javascript:this.value=Comma(this.value);updateCoverValue('%{#sublist.COVERAGES_SUB_ID}');" disabled="#disable" maxLength="13"/>
														</s:if>
														<s:elseif test='%{"dropdown"==SUB_CONTROL_TYPE}'>
															<s:select id="subselect[%{#sublist.COVERAGES_SUB_ID}]" name="COVERAGES_COVERED_EMPLOYEES[%{#item.count}]"  list="DropDown" listKey="LISTKEY" listValue="LISTVALUE" headerKey="" headerValue="---None---" value='%{hasActionErrors()?subSumInsuredList[#item.index]:#sublist.COVERAGES_COVERED_EMPLOYEES}' cssClass="form-control"   disabled="#disable"/>
														</s:elseif>
														<s:elseif test='%{"Display"==SUB_CONTROL_TYPE}'>
															<s:set var="tempCoverAmount" value='%{hasActionErrors()?subSumInsuredList[#item.index]:((#sublist.COVERAGES_COVERED_EMPLOYEES!=null && !"".equals(#sublist.COVERAGES_COVERED_EMPLOYEES))?getText(#format,{@java.lang.Double@valueOf(#sublist.COVERAGES_COVERED_EMPLOYEES)}):( (SUM_INSURED_LIMIT==null || "".equals(SUM_INSURED_LIMIT))?"":getText(#format,{@java.lang.Double@valueOf(SUM_INSURED_LIMIT)}) )) }'/>
															<s:textfield id="subtext[%{#sublist.COVERAGES_SUB_ID}]" cssStyle="text-align: right;" name="COVERAGES_COVERED_EMPLOYEES[%{#item.count}]" value='%{#tempCoverAmount}' cssClass="form-control"  onkeyup="checkDecimals13(this);javascript:this.value=Comma(this.value);" disabled='%{("admin".equalsIgnoreCase(#session.usertype) || "RSAIssuer".equals(#session.usertype))?"false":"true"}' maxLength="13"/>
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
															<s:if test='%{("A".equals(#sublist.GRID_CALC_TYPE) || #sublist.GRID_CALC_TYPE==null) && "A".equals(#sublist.CALC_TYPE)}'>ZMW</s:if>
															<s:elseif test='%{"A".equals(#sublist.GRID_CALC_TYPE) && "G".equals(#sublist.CALC_TYPE)}'>ZMW&nbsp; G</s:elseif>
															<s:elseif test='%{"A".equals(#sublist.GRID_CALC_TYPE) && "P".equals(#sublist.CALC_TYPE)}'>ZMW&nbsp; %</s:elseif>
															<s:elseif test='%{#sublist.GRID_CALC_TYPE!=null && "P".equals(#sublist.GRID_CALC_TYPE)}'>G%</s:elseif>
															<s:elseif test='%{#sublist.GRID_CALC_TYPE!=null && "M".equals(#sublist.GRID_CALC_TYPE)}'>GM</s:elseif>
															<s:elseif test='%{#sublist.GRID_CALC_TYPE==null && "P".equals(#sublist.CALC_TYPE)}'>%</s:elseif>
															<s:elseif test='%{#sublist.GRID_CALC_TYPE==null && "M".equals(#sublist.CALC_TYPE)}'>M</s:elseif>
														</span>
															<s:textfield name="COVERAGES_SUB_BASE_RATE[%{COVERAGES_SUB_ID}]" value="%{#sublist.COVERAGES_SUB_BASE_RATE}" cssClass="inputBox" cssStyle="width: 80%; text-align:right;" onkeyup="checkDecimals13(this);" onblur="validamt(this);" />							
														</td>
														<%--<td width="15%" align="right"><b><s:property value="getText('{0,number,#,##0.00}',{#sublist.PREMIUM_AMOUNT})"/></b></td>--%>
													</s:if>
												</tr>
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
													<s:if test='"N".equals(modifyYN)'>
														<s:if test='"Radio"==SUM_INSURED_CONTROL_TYPE'>
															<td align="center">
																<s:radio name="SUM_INSURED[%{#itemIndex.count}]" id="text[%{#Home.COVERAGES_ID}]"  list="DropDown" listKey="LISTKEY" listValue="LISTVALUE" disabled="true" cssClass="tooltipContent" 
																value='%{hasActionErrors()?sumInsuredList[#itemIndex.count-1]:(#Home.SUM_INSURED==null?"2":#Home.SUM_INSURED)}'/>
															</td>
														</s:if>
														<s:elseif test='"dropdown"==SUM_INSURED_CONTROL_TYPE'>
															<s:if test='%{#Home.COVERAGES_ID=="94"}'>
																<td id="lossLimbs" align="center">
																	<s:select name="SUM_INSURED[%{#itemIndex.count}]" id="text[%{#Home.COVERAGES_ID}]" list="DropDown" listKey="LISTKEY" listValue="LISTVALUE" headerKey="" headerValue="---None---" value="%{hasActionErrors()?sumInsuredList[#itemIndex.count-1]:SUM_INSURED}" cssClass="form-control"  onchange="setLossLimbs(this.value);" disabled="true"/>
																</td>
															</s:if>
															<s:elseif test='%{#Home.COVERAGES_ID=="105"}'>
																<td align="center">
																	<div id="homeLossOfLimbs">
																		<s:select name="SUM_INSURED[%{#itemIndex.count}]" id="text[%{#Home.COVERAGES_ID}]" list="DropDown" listKey="LISTKEY" listValue="LISTVALUE" headerKey="" headerValue="---None---" value="%{hasActionErrors()?sumInsuredList[#itemIndex.count-1]:SUM_INSURED}" cssClass="form-control"  disabled="true"/>
																	</div>
																</td>
															</s:elseif>
															<s:else>
																<td align="center">
																	<s:select name="SUM_INSURED[%{#itemIndex.count}]" id="text[%{#Home.COVERAGES_ID}]" list="DropDown" listKey="LISTKEY" listValue="LISTVALUE" headerKey="" headerValue="---None---" value="%{hasActionErrors()?sumInsuredList[#itemIndex.count-1]:SUM_INSURED}" cssClass="form-control"  disabled="true"/>
																</td>
															</s:else>
														</s:elseif>
														<s:elseif test='"textbox"==SUM_INSURED_CONTROL_TYPE || "Display"==SUM_INSURED_CONTROL_TYPE'>
															<td align="center">
																<s:property value='%{hasActionErrors()?sumInsuredList[#itemIndex.count-1]:( (SUM_INSURED==null || "".equals(SUM_INSURED))?"":getText(#format,{@java.lang.Double@valueOf(SUM_INSURED)}) ) }' />
															</td>
														</s:elseif>
														<s:if test='%{"admin".equals(#session.usertype) || "RSAIssuer".equals(#session.usertype)}'>
															<td align="left">
																<span style="font-weight: bold; float: right;">
																	<s:if test='%{("A".equals(#Home.GRID_CALC_TYPE) || #Home.GRID_CALC_TYPE==null) && "A".equals(#Home.CALC_TYPE)}'>ZMW</s:if>
																	<s:elseif test='%{"A".equals(#Home.GRID_CALC_TYPE) && "G".equals(#Home.CALC_TYPE)}'>ZMW&nbsp; G</s:elseif>
																	<s:elseif test='%{"A".equals(#Home.GRID_CALC_TYPE) && "P".equals(#Home.CALC_TYPE)}'>ZMW&nbsp; %</s:elseif>
																	<s:elseif test='%{#Home.GRID_CALC_TYPE!=null && "P".equals(#Home.GRID_CALC_TYPE)}'>G%</s:elseif>
																	<s:elseif test='%{#Home.GRID_CALC_TYPE!=null && "M".equals(#Home.GRID_CALC_TYPE)}'>GM</s:elseif>
																	<s:elseif test='%{#Home.GRID_CALC_TYPE==null && "P".equals(#Home.CALC_TYPE)}'>%</s:elseif>
																	<s:elseif test='%{#Home.GRID_CALC_TYPE==null && "M".equals(#Home.CALC_TYPE)}'>M</s:elseif>
																</span>
																<s:property value="%{COVERAGES_BASE_RATE}"/>
															</td>
															<%--<td width="15%" align="right">
																<b><s:property value="getText('{0,number,#,##0.00}',{PREMIUM_AMOUNT})"/></b>
															</td>--%>
														</s:if>
													</s:if>
													<s:else>
														<s:if test='"textbox"==SUM_INSURED_CONTROL_TYPE'>
															<td >
																<s:textfield name="SUM_INSURED[%{#itemIndex.count}]" id="text[%{#Home.COVERAGES_ID}]" value='%{hasActionErrors()?sumInsuredList[#itemIndex.count-1]:( (SUM_INSURED==null || "".equals(SUM_INSURED))?"":getText(#format,{@java.lang.Double@valueOf(SUM_INSURED)}) ) }' cssClass="form-control"  onkeyup="checkDecimals13(this);javascript:this.value=Comma(this.value);updateCoverValue('%{#Home.COVERAGES_ID}');" disabled="#disable" maxLength="13" cssStyle="text-align: right;" />
															</td>
														</s:if>
														<s:elseif test='"dropdown"==SUM_INSURED_CONTROL_TYPE'>
															<s:if test='%{#Home.COVERAGES_ID=="94"}'>
																<td id="lossLimbs">
																	<s:select name="SUM_INSURED[%{#itemIndex.count}]" id="text[%{#Home.COVERAGES_ID}]" list="DropDown" listKey="LISTKEY" listValue="LISTVALUE" headerKey="" headerValue="---None---" value="%{hasActionErrors()?sumInsuredList[#itemIndex.count-1]:SUM_INSURED}" cssClass="form-control"  onchange="setLossLimbs(this.value);" disabled="#disable"/>
																</td>
															</s:if>
															<s:elseif test='%{#Home.COVERAGES_ID=="105"}'>
																<td>
																	<div id="homeLossOfLimbs">
																		<s:select name="SUM_INSURED[%{#itemIndex.count}]" id="text[%{#Home.COVERAGES_ID}]" list="DropDown" listKey="LISTKEY" listValue="LISTVALUE" headerKey="" headerValue="---None---" value="%{hasActionErrors()?sumInsuredList[#itemIndex.count-1]:SUM_INSURED}" cssClass="form-control"  disabled="true"/>
																	</div>
																</td>
															</s:elseif>
															<s:else>
																<td >
																	<s:select name="SUM_INSURED[%{#itemIndex.count}]" id="text[%{#Home.COVERAGES_ID}]" list="DropDown" listKey="LISTKEY" listValue="LISTVALUE" headerKey="" headerValue="---None---" value="%{hasActionErrors()?sumInsuredList[#itemIndex.count-1]:SUM_INSURED}" cssClass="form-control"  disabled="#disable"/>
																</td>
															</s:else>
														</s:elseif>
														<s:elseif test='"Display"==SUM_INSURED_CONTROL_TYPE'>
															<s:if test='%{#Home.COVERAGES_ID=="105"}'>
																<td>
																	<div id="homeLossOfLimbs">
																		<s:textfield name="SUM_INSURED[%{#itemIndex.count}]" id="text[%{#Home.COVERAGES_ID}]" cssStyle="text-align: right;" value='%{hasActionErrors()?sumInsuredList[#itemIndex.count-1]:( (SUM_INSURED_LIMIT==null || "".equals(SUM_INSURED_LIMIT))?"":getText(#format,{@java.lang.Double@valueOf(SUM_INSURED_LIMIT)}) ) }' cssClass="form-control"  onkeyup="checkDecimals13(this);javascript:this.value=Comma(this.value);" disabled='%{("admin".equalsIgnoreCase(#session.usertype) || "RSAIssuer".equals(#session.usertype))?"false":"true"}' maxLength="13"/>
																	</div>
																</td>
															</s:if>
															<s:else>
																<td >
																	<s:textfield name="SUM_INSURED[%{#itemIndex.count}]" id="text[%{#Home.COVERAGES_ID}]" cssStyle="text-align: right;" value='%{hasActionErrors()?sumInsuredList[#itemIndex.count-1]:( (SUM_INSURED_LIMIT==null || "".equals(SUM_INSURED_LIMIT))?"":getText(#format,{@java.lang.Double@valueOf(SUM_INSURED_LIMIT)}) ) }' cssClass="form-control"  onkeyup="checkDecimals13(this);javascript:this.value=Comma(this.value);" disabled='%{("admin".equalsIgnoreCase(#session.usertype) || "RSAIssuer".equals(#session.usertype))?"false":"true"}' maxLength="13"/>
																</td>
															</s:else>
														</s:elseif>
														<s:elseif test='"Radio"==SUM_INSURED_CONTROL_TYPE'>
															<td>
																<s:radio name="SUM_INSURED[%{#itemIndex.count}]" id="text[%{#Home.COVERAGES_ID}]"  list="DropDown" listKey="LISTKEY" listValue="LISTVALUE" disabled="#disable" cssClass="tooltipContent" 
																value='%{hasActionErrors()?sumInsuredList[#itemIndex.count-1]:(#Home.SUM_INSURED==null?"2":#Home.SUM_INSURED)}'/>
															</td>
														</s:elseif>
														<s:if test='%{"admin".equals(#session.usertype) || "RSAIssuer".equals(#session.usertype)}'>
															<td align="left">
																<span style="font-weight: bold; float: right;">
																	<s:if test='%{("A".equals(#Home.GRID_CALC_TYPE) || #Home.GRID_CALC_TYPE==null) && "A".equals(#Home.CALC_TYPE)}'>ZMW</s:if>
																	<s:elseif test='%{"A".equals(#Home.GRID_CALC_TYPE) && "G".equals(#Home.CALC_TYPE)}'>ZMW&nbsp; G</s:elseif>
																	<s:elseif test='%{"A".equals(#Home.GRID_CALC_TYPE) && "P".equals(#Home.CALC_TYPE)}'>ZMW&nbsp; %</s:elseif>
																	<s:elseif test='%{#Home.GRID_CALC_TYPE!=null && "P".equals(#Home.GRID_CALC_TYPE)}'>G%</s:elseif>
																	<s:elseif test='%{#Home.GRID_CALC_TYPE!=null && "M".equals(#Home.GRID_CALC_TYPE)}'>GM</s:elseif>
																	<s:elseif test='%{#Home.GRID_CALC_TYPE==null && "P".equals(#Home.CALC_TYPE)}'>%</s:elseif>
																	<s:elseif test='%{#Home.GRID_CALC_TYPE==null && "M".equals(#Home.CALC_TYPE)}'>M</s:elseif>
																</span>
																<s:textfield name="COVERAGES_BASE_RATE[%{#itemIndex.count}]" value="%{COVERAGES_BASE_RATE}" cssClass="form-control"  cssStyle="width: 80%; text-align:right;"  onkeyup="checkDecimals13(this);" onblur="validamt(this);" />
															</td>
															<%--<td width="15%" align="right">
																<b><s:property value="getText('{0,number,#,##0.00}',{PREMIUM_AMOUNT})"/></b>
															</td>--%>
														</s:if>
													</s:else>
												</tr>
											</s:if>
										</s:else>
									</tbody>
									<s:hidden name="COVER_ID[%{#itemIndex.count}]" value="%{COVERAGES_ID}"/>
								</s:iterator>												
							</table>
						</div>
					</s:if>
					<s:else>
						<div class="customTable mt-5 container"  class="customTable mt-5 container" style="overflow:scroll;width:100%;overflow:auto;">
							<table class="table table-bordered nowrap" cellspacing="0" width="100%">
								<thead align="center" style="background-color:#261e6a;color:white;white-space: nowrap;">
									<tr>
										<th class="tableColumnWidth">S.No</th>
										<th class="tableColumnWidth">Location</th>
										<s:iterator value="HomeDesc" var="Home" status ="itemIndex">
											<s:if test='%{!"P".equals(COVERAGES_TYPE)}'>
												
												<th>
													<s:if test='%{"B".equals(COVERAGES_TYPE)}'>
														<div style="font:bold; color:#428bca">(<s:text name="label.main.cover"/>)</div>
													</s:if>
													<s:elseif test='%{"O".equals(COVERAGES_TYPE)}'>	
														<div style="font:bold; color:#FD7904">(<s:text name="label.additional.cover"/>)</div>
													</s:elseif>
													<!--<div style="margin-left: 5px;width: 300px"> ${COVERAGES_NAME} </div>-->
													<div style="margin-left: 5px"> ${COVERAGES_NAME} </div>
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
												</th>
											</s:if>
										</s:iterator>
									</tr>
								</thead>
								<s:if test='%{"N".equals(modifyYN)}'>
									<tbody>
										<s:iterator begin="1" end="locationSize" var="Home" status ="itemStat">
											<s:if test='%{"admin".equals(#session.usertype) || "RSAIssuer".equals(#session.usertype)}'>
												<s:if test='"0".equalsIgnoreCase(#itemStat.index)'>
													<tr>
														<td align="center" colspan="2" style="background-color:#261e6a;color:white;font-weight:bold;vertical-align:middle;">Base&nbsp;Rate</td>
														<s:iterator value="HomeDesc" var="Home" status ="rateStat">
															<s:if test='%{!"P".equals(COVERAGES_TYPE)}'>
																<td align="right">
																	<span style="font-weight: bold; float: right;">
																		<s:if test='%{("A".equals(#Home.GRID_CALC_TYPE) || #Home.GRID_CALC_TYPE==null) && "A".equals(#Home.CALC_TYPE)}'>ZMW</s:if>
																		<s:elseif test='%{"A".equals(#Home.GRID_CALC_TYPE) && "G".equals(#Home.CALC_TYPE)}'>ZMW&nbsp; G</s:elseif>
																		<s:elseif test='%{"A".equals(#Home.GRID_CALC_TYPE) && "P".equals(#Home.CALC_TYPE)}'>ZMW&nbsp; %</s:elseif>
																		<s:elseif test='%{#Home.GRID_CALC_TYPE!=null && "P".equals(#Home.GRID_CALC_TYPE)}'>G%</s:elseif>
																		<s:elseif test='%{#Home.GRID_CALC_TYPE!=null && "M".equals(#Home.GRID_CALC_TYPE)}'>GM</s:elseif>
																		<s:elseif test='%{#Home.GRID_CALC_TYPE==null && "P".equals(#Home.CALC_TYPE)}'>%</s:elseif>
																		<s:elseif test='%{#Home.GRID_CALC_TYPE==null && "M".equals(#Home.CALC_TYPE)}'>M</s:elseif>
																	</span>
																	<b><s:property value="%{COVERAGES_BASE_RATE}"/></b>
																</td>
															</s:if>
														</s:iterator>
													</tr>
												</s:if>
											</s:if>
											<tr>
												<td align="center" style="vertical-align:middle;" rowspan="<%--<s:if test='"admin".equals(#session.usertype) || "RSAIssuer".equals(#session.usertype)'>2</s:if><s:else>1</s:else>--%>1"><s:property value="#itemStat.count"/></td>
												<td align="center"><b><s:property value="locationName[#itemStat.index]"/></b></td>
												<s:iterator value="HomeDesc" var="Home" status ="itemIndex">
													<s:if test='%{!"P".equals(COVERAGES_TYPE)}'>
														<s:if test='"Radio"==SUM_INSURED_CONTROL_TYPE'>
															<td>
																<s:radio name="sumInsuredNM[%{#itemStat.count-1}][%{#itemIndex.index}]" id="itext[%{#Home.COVERAGES_ID}]"  list="DropDown" listKey="LISTKEY" listValue="LISTVALUE" disabled="true" cssClass="tooltipContent" 
																 value='%{(sumInsuredNM[#itemStat.count-1][#itemIndex.index]==null || sumInsuredNM[#itemStat.count-1][#itemIndex.index]=="0")?"2":sumInsuredNM[#itemStat.count-1][#itemIndex.index]}'/>
															</td>
														</s:if>
														<s:if test='"dropdown"==SUM_INSURED_CONTROL_TYPE'>
															<s:if test='%{#Home.COVERAGES_ID=="94"}'>
																<td id="lossLimbs">
																	<s:select name="sumInsuredNM[%{#itemStat.count-1}][%{#itemIndex.index}]" id="itext[%{#Home.COVERAGES_ID}]" list="DropDown" listKey="LISTKEY" listValue="LISTVALUE" headerKey="" headerValue="---None---" value="%{hasActionErrors()?sumInsuredList[#itemIndex.count-1]:SUM_INSURED}" cssClass="form-control"  onchange="setLossLimbs(this.value);" disabled="true"/>
																</td>
															</s:if>
															<s:elseif test='%{#Home.COVERAGES_ID=="105"}'>
																<td>
																	<div id="homeLossOfLimbs">
																		<s:select name="sumInsuredNM[%{#itemStat.count-1}][%{#itemIndex.index}]" id="itext[%{#Home.COVERAGES_ID}]" list="DropDown" listKey="LISTKEY" listValue="LISTVALUE" headerKey="" headerValue="---None---" value="%{hasActionErrors()?sumInsuredList[#itemIndex.count-1]:SUM_INSURED}" cssClass="form-control"  disabled="true"/>
																	</div>
																</td>
															</s:elseif>
															<s:else>
																<td >
																	<s:select name="sumInsuredNM[%{#itemStat.count-1}][%{#itemIndex.index}]" id="itext[%{#Home.COVERAGES_ID}]" list="DropDown" listKey="LISTKEY" listValue="LISTVALUE" headerKey="" headerValue="---None---" value="%{hasActionErrors()?sumInsuredList[#itemIndex.count-1]:SUM_INSURED}" cssClass="form-control"  disabled="true"/>
																</td>
															</s:else>
														</s:if>
														<s:if test='"textbox"==SUM_INSURED_CONTROL_TYPE || "Display"==SUM_INSURED_CONTROL_TYPE'>
															<td align="right">
																<s:property value="sumInsuredNM[#itemStat.index][#itemIndex.index]"/>
															</td>
														</s:if>
													</s:if>
													<s:if test='"0".equalsIgnoreCase(#itemStat.index)'>
														<s:hidden name="COVER_ID[%{#itemIndex.count}]" value="%{COVERAGES_ID}"/>
													</s:if>
												</s:iterator>
											</tr>
											<%--<s:if test='%{"admin".equals(#session.usertype) || "RSAIssuer".equals(#session.usertype)}'>
												<tr>
													<td align="center" style="background-color:#261e6a;color:white;font-weight:bold;"><s:text name="label.premium"/>&nbsp;in&nbsp;[ZMW]</td>
													<s:iterator value="HomeDesc" var="Home" status ="premiumStat">
														<s:if test='%{!"P".equals(COVERAGES_TYPE)}'>
															<td align="right"><b><s:property value="getText('{0,number,#,##0.00}',premiumNM[#itemStat.index][#premiumStat.index])"/></b></td>
														</s:if>
													</s:iterator>
												</tr>
											</s:if>--%>
										</s:iterator>
									</tbody>
								</s:if>
								<s:else>
									<tbody>
										<s:iterator begin="1" end="locationSize" var="Home" status ="itemStat">
											<s:if test='%{"admin".equals(#session.usertype) || "RSAIssuer".equals(#session.usertype)}'>
												<s:if test='"0".equalsIgnoreCase(#itemStat.index)'>
													<tr>
														<td align="center" colspan="2" style="background-color:#261e6a;color:white;font-weight:bold;vertical-align:middle;">Base&nbsp;Rate</td>
														<s:iterator value="HomeDesc" var="Home" status ="rateStat">
															<s:if test='%{!"P".equals(COVERAGES_TYPE)}'>
																<td align="left">
																	<span style="font-weight: bold; float: right;">
																		<s:if test='%{("A".equals(#Home.GRID_CALC_TYPE) || #Home.GRID_CALC_TYPE==null) && "A".equals(#Home.CALC_TYPE)}'>ZMW</s:if>
																		<s:elseif test='%{"A".equals(#Home.GRID_CALC_TYPE) && "G".equals(#Home.CALC_TYPE)}'>ZMW&nbsp; G</s:elseif>
																		<s:elseif test='%{"A".equals(#Home.GRID_CALC_TYPE) && "P".equals(#Home.CALC_TYPE)}'>ZMW&nbsp; %</s:elseif>
																		<s:elseif test='%{#Home.GRID_CALC_TYPE!=null && "P".equals(#Home.GRID_CALC_TYPE)}'>G%</s:elseif>
																		<s:elseif test='%{#Home.GRID_CALC_TYPE!=null && "M".equals(#Home.GRID_CALC_TYPE)}'>GM</s:elseif>
																		<s:elseif test='%{#Home.GRID_CALC_TYPE==null && "P".equals(#Home.CALC_TYPE)}'>%</s:elseif>
																		<s:elseif test='%{#Home.GRID_CALC_TYPE==null && "M".equals(#Home.CALC_TYPE)}'>M</s:elseif>
																	</span>
																	<s:textfield name="COVERAGES_BASE_RATE[%{#rateStat.count}]" value="%{COVERAGES_BASE_RATE}" cssClass="form-control"  cssStyle="width: 80%; text-align:right;"  onkeyup="checkDecimals13(this);" onblur="validamt(this);" />
																</td>
															</s:if>
														</s:iterator>
													</tr>
												</s:if>
											</s:if>
											<tr>
												<td align="center" style="vertical-align:middle;" rowspan="<%--<s:if test='"admin".equals(#session.usertype) || "RSAIssuer".equals(#session.usertype)'>2</s:if><s:else>1</s:else>--%>1"><s:property value="#itemStat.count"/></td>
												<td align="center"><b><s:property value="locationName[#itemStat.index]"/></b></td>
												<s:iterator value="HomeDesc" var="Home" status ="itemIndex">
												
													<s:if test='%{!"P".equals(COVERAGES_TYPE)}'>
														<s:if test='"textbox"==SUM_INSURED_CONTROL_TYPE'>
															<td >
																<s:textfield name="sumInsuredNM[%{#itemStat.count-1}][%{#itemIndex.index}]" id="itext[%{#Home.COVERAGES_ID}]"  cssClass="form-control"  onkeyup="checkDecimals13(this);javascript:this.value=Comma(this.value);updateCoverValue('%{#Home.COVERAGES_ID}');" disabled="#disable" maxLength="13" cssStyle="text-align: right;" />
															</td>
														</s:if>
														<s:if test='"dropdown"==SUM_INSURED_CONTROL_TYPE'>
															<s:if test='%{#Home.COVERAGES_ID=="94"}'>
																<td id="lossLimbs">
																	<s:select name="sumInsuredNM[%{#itemStat.count-1}][%{#itemIndex.index}]" id="itext[%{#Home.COVERAGES_ID}]" list="DropDown" listKey="LISTKEY" listValue="LISTVALUE" headerKey="" headerValue="---None---" value="%{hasActionErrors()?sumInsuredList[#itemIndex.count-1]:SUM_INSURED}" cssClass="form-control"  onchange="setLossLimbs(this.value);" disabled="#disable"/>

																</td>
															</s:if>
															<s:elseif test='%{#Home.COVERAGES_ID=="105"}'>
																<td>
																	<div id="homeLossOfLimbs">
																		<s:select name="sumInsuredNM[%{#itemStat.count-1}][%{#itemIndex.index}]" id="itext[%{#Home.COVERAGES_ID}]" list="DropDown" listKey="LISTKEY" listValue="LISTVALUE" headerKey="" headerValue="---None---" value="%{hasActionErrors()?sumInsuredList[#itemIndex.count-1]:SUM_INSURED}" cssClass="form-control"  disabled="true"/>
																	</div>
																</td>
															</s:elseif>
															<s:else>
																<td >
																	<s:select name="sumInsuredNM[%{#itemStat.count-1}][%{#itemIndex.index}]" id="itext[%{#Home.COVERAGES_ID}]" list="DropDown" listKey="LISTKEY" listValue="LISTVALUE" headerKey="" headerValue="---None---" value="%{hasActionErrors()?sumInsuredList[#itemIndex.count-1]:SUM_INSURED}" cssClass="form-control"  disabled="#disable"/>
																</td>
															</s:else>
														</s:if>
														<s:if test='"Display"==SUM_INSURED_CONTROL_TYPE'>
															<s:if test='%{#Home.COVERAGES_ID=="105"}'>
																<td>
																	<div id="homeLossOfLimbs">
																		<s:textfield name="sumInsuredNM[%{#itemStat.count-1}][%{#itemIndex.index}]" id="itext[%{#Home.COVERAGES_ID}]" cssStyle="text-align: right;"  cssClass="form-control"  onkeyup="checkDecimals13(this);javascript:this.value=Comma(this.value);" disabled='%{("admin".equalsIgnoreCase(#session.usertype) || "RSAIssuer".equals(#session.usertype))?"false":"true"}' maxLength="13"/>
																	</div>
																</td>
															</s:if>
															<s:else>
																<td>
																	<s:textfield name="sumInsuredNM[%{#itemStat.count-1}][%{#itemIndex.index}]" id="itext[%{#Home.COVERAGES_ID}]" cssStyle="text-align: right;"  cssClass="form-control"  onkeyup="checkDecimals13(this);javascript:this.value=Comma(this.value);" disabled='%{("admin".equalsIgnoreCase(#session.usertype) || "RSAIssuer".equals(#session.usertype))?"false":"true"}' maxLength="13"/>
																</td>
															</s:else>
														</s:if>
														<s:if test='"Radio"==SUM_INSURED_CONTROL_TYPE'>
															<td>
																<s:radio name="sumInsuredNM[%{#itemStat.count-1}][%{#itemIndex.index}]" id="itext[%{#Home.COVERAGES_ID}]"  list="DropDown" listKey="LISTKEY" listValue="LISTVALUE" disabled="#disable" cssClass="tooltipContent" 
																value='%{(sumInsuredNM[#itemStat.count-1][#itemIndex.index]==null || sumInsuredNM[#itemStat.count-1][#itemIndex.index]=="0")?"2":sumInsuredNM[#itemStat.count-1][#itemIndex.index]}'/>
															</td>
														</s:if>
													</s:if>
													<s:if test='"0".equalsIgnoreCase(#itemStat.index)'>
														<s:hidden name="COVER_ID[%{#itemIndex.count}]" value="%{COVERAGES_ID}"/>
													</s:if>
												</s:iterator>
											</tr>
											<%--<s:if test='%{"admin".equals(#session.usertype) || "RSAIssuer".equals(#session.usertype)}'>
												<tr>
													<td align="center" style="background-color:#261e6a;color:white;font-weight:bold;"><s:text name="label.premium"/>&nbsp;in&nbsp;[ZMW]</td>
													<s:iterator value="HomeDesc" var="Home" status ="premiumStat">
														<s:if test='%{!"P".equals(COVERAGES_TYPE)}'>
															<td align="right"><b><s:property value="getText('{0,number,#,##0.00}',premiumNM[#itemStat.index][#premiumStat.index])"/></b></td>
														</s:if>
													</s:iterator>
												</tr>
											</s:if> --%>
										</s:iterator>
									</tbody>
								</s:else>	
							</table>
						</div>
					</s:else>
	            </div>
	            <br/>
	            <s:if test='!"sirEdit".equalsIgnoreCase(editFrom)'>
					<div class="row">
						<s:if test="%{schemeList.size>1}">
							<s:if test='prevScheme==null || "".equals(prevScheme)'>
								<div class="col-md-5 col-5 col-sm-5" align="left">
								</div>
							</s:if>
							<s:else>
								<div class="col-md-1 col-1 col-sm-1" align="left">
								</div>
								<div class="col-md-3 col-3 col-sm-3" align="left">
									<button type="button" class="btn btn-info btn-block" onclick="nextFun('editSchemeNewHome.action')"><i class="fa fa-hand-point-left"></i>&nbsp;&nbsp;Previous Scheme</button>
								</div>
								<div class="col-md-1 col-1 col-sm-1" align="left">
								</div>
							</s:else>
							<div class="col-md-2 col-2 col-sm-2">
								 <%--<s:if test='%{"admin".equalsIgnoreCase(#session.usertype) || "RSAIssuer".equals(#session.usertype)}'>
								    <button type="button" class="btn btn-warning btn-block" onclick="disableForm(this.form,false,'');forward('getRenderV1Home.action','calculate')"><i class="fa fa-calculator"></i>&nbsp;&nbsp;Calculate</button>
								 </s:if>--%>
							</div>
							<s:if test='nextScheme==null || "".equals(nextScheme)'>
								<div class="col-md-5 col-5 col-sm-5" align="right">
								</div>
							</s:if>
							<s:else>
								<div class="col-md-1 col-1 col-sm-1" align="right">
								</div>
								<div class="col-md-3 col-3 col-sm-3" align="right">
									<button type="button" class="btn btn-info btn-block" onclick="nextFun('nextSchemeNewHome.action')">Next Scheme&nbsp;&nbsp;<i class="fa fa-hand-point-right"></i></button>
								</div>
								<div class="col-md-1 col-1 col-sm-1" align="right">
								</div>
							</s:else>
						</s:if>
					</div>
		            <br/>
	            </s:if>
	        </div>
	        <div class="row mt-4">
	        	<s:if test='"sirEdit".equalsIgnoreCase(editFrom)'>
	        		<div class="col-md-4 col-4 col-sm-4">
					</div>
					<div class="col-md-2 col-2 col-sm-2">
						<button type="button" value="Back" class="btn btn-danger btn-block" onclick="disableForm(this.form,false,'');forwardd('getRenderV1Home.action','proceed','N');">Cancel</button>
					</div>
					<div class="col-md-2 col-2 col-sm-2">
						<input type="button" value="Submit" class="btn btn-primary btn-block" onclick="disableForm(this.form,false,'');forwardd('getRenderV1Home.action','proceed','Y');"/>
					</div>
					<div class="col-md-4 col-4 col-sm-4">
					</div>
	        	</s:if>
	            <s:else>
		           	<s:if test='%{"".equalsIgnoreCase(#session.user) || "madisondirect".equalsIgnoreCase(#session.user)|| "guestmotor".equalsIgnoreCase(#session.user)}'>
		           		<div class="col-md-2 col-2 col-sm-2">
							<input type="button" value="Get Quote" class="btn btn-primary btn-block" onclick="disableForm(this.form,false,'');forward('otpLoginHome.action','proceed')"/>
						</div>
					</s:if>
					<s:else>
						<div class="col-md-4 col-4 col-sm-4">
						</div>
						<s:if test='nextScheme==null || "".equalsIgnoreCase(nextScheme) || "Y".equalsIgnoreCase(btnYN)'>
						</s:if>
						<s:else>
							<div class="col-md-1 col-1 col-sm-1">
							</div>
						</s:else>
						<div class="col-md-2 col-2 col-sm-2">
							<button type="button" value="Back" class="btn btn-danger btn-block" onclick="disableForm(this.form,false,'');forward('schemeSelectionHome.action','');">Back</button>
						</div>
						<s:if test='nextScheme==null || "".equalsIgnoreCase(nextScheme) || "Y".equalsIgnoreCase(btnYN)'>
							<div class="col-md-2 col-2 col-sm-2">
								<input type="button" value="Get Quote" class="btn btn-primary btn-block" onclick="disableForm(this.form,false,'');forward('getRenderV1Home.action','proceed');"/>
							</div>
						</s:if>
						<s:else>
							<div class="col-md-1 col-1 col-sm-1">
							</div>
						</s:else>
						<div class="col-md-4 col-4 col-sm-4">
						</div>
					</s:else>
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
			<s:hidden name="locationSelected"/>
			<s:hidden name="locationIds"/>
			<s:hidden name="prevScheme" />
			<s:hidden name="add" id="add"/>
			<s:hidden name="locationSize" id="locationSize"/>
			<s:hidden name="customerId" id="customerId"/>
			<s:hidden name="locationId"/>
			<s:hidden name="quoteStatus" id="quoteStatus"/>
			<s:hidden name="editFrom" id="editFrom"/>
	</s:form>
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

		function forwardd(url, from, modify){
			try{
				disableForm(document.quotation,false,'')
				if(modify == 'Y'){
					document.getElementById('modifyYNY').checked = true;
				}else{
					document.getElementById('modifyYNN').checked = true;
				}
				document.quotation.action = "${pageContext.request.contextPath}/"+url+"?reqFrom="+from;
				document.quotation.submit();
			}catch(err){
				console.log(err);
			}
		}
		function updateCoverValue(coverId) {
			/*<s:if test='"1".equals(#session.scheme_id)'>
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
				if(document.getElementById('itext[51]').value!="" && document.getElementById('itext[54]').disabled == true) {
					document.getElementById('itext[54]').disabled = false;
					document.getElementById('itext[57]').disabled = false;
					document.getElementById('itext[58]').disabled = false;
					document.getElementById('itext[55]1').disabled = false;
					document.getElementById('itext[55]2').disabled = false;
					document.getElementById('itext[56]1').disabled = false;
					document.getElementById('itext[56]2').disabled = false;
					
				} else if(document.getElementById('itext[51]').value=="" && document.getElementById('itext[54]').disabled == false) {
					document.getElementById('itext[54]').disabled = true;
					document.getElementById('itext[57]').disabled = true;
					document.getElementById('itext[58]').disabled = true;
					document.getElementById('itext[55]1').disabled = true;
					document.getElementById('itext[55]2').disabled = true;
					document.getElementById('itext[56]1').disabled = true;
					document.getElementById('itext[56]2').disabled = true;
	
					document.getElementById('itext[54]').value="";
					document.getElementById('itext[57]').value="";
					document.getElementById('itext[58]').value="";
					document.getElementById('itext[55]1').checked = false;
					document.getElementById('itext[55]2').checked = true;
					document.getElementById('itext[56]1').checked = false;
					document.getElementById('itext[56]2').checked = true;
				}
			}
			</s:elseif>*/
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
		
		function getModifyDetails(val){
			//alert(val);
			document.quotation.action = "${pageContext.request.contextPath}/modifySumInsHome.action";
			document.quotation.submit();
		}
	</script>
</body>
</html>