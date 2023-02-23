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
        * {
            font-family: 'Nunito', sans-serif !important;
        }

        .fas {
            font-family: "Font Awesome 5 Free" !important;
        }

        .QuotationInformationPage .card {
            padding: 20px 20px 20px 120px;
            border: 0px !important
        }

        .QuotationInformationPage .card-2 {
            padding: 20px 30px 20px 30px;

        }

        .QuotationInformationPage .Card_Parent {
            border-radius: 4px;
            background: #fff;
            box-shadow: 0 6px 10px rgba(0, 0, 0, .08), 0 0 6px rgba(0, 0, 0, .05);
            transition: .3s transform cubic-bezier(.155, 1.105, .295, 1.12), .3s box-shadow, .3s -webkit-transform cubic-bezier(.155, 1.105, .295, 1.12);
        }

        .QuotationInformationPage .plan_Card {
            box-shadow: 0 1px 1px rgba(0, 0, 0, 0.25),
                0 2px 2px rgba(0, 0, 0, 0.20),
                0 4px 4px rgba(0, 0, 0, 0.15),
                0 8px 8px rgba(0, 0, 0, 0.10),
                0 16px 16px rgba(0, 0, 0, 0.05);
            padding: 10px;
            border-radius: 5px;
            background-color: aliceblue;
        }

        .PremiumCoverDetails {
            box-shadow: 0 1px 1px rgba(0, 0, 0, 0.25),
                0 2px 2px rgba(0, 0, 0, 0.20),
                0 4px 4px rgba(0, 0, 0, 0.15),
                0 8px 8px rgba(0, 0, 0, 0.10),
                0 16px 16px rgba(0, 0, 0, 0.05);
            border-radius: 5px;
            background-color: white;
        }

        .QuotationInformationPage .card h4 {
            font-weight: 700;
            color: #261e6a;
        }

        .QuotationInformationPage .card-1 {
            background-image: url("assets/Images/home-insurances.png");
            background-repeat: no-repeat;
            background-position: left;
            background-size: 200px;
        }

        .QuotationInformationPage .LabelHeading {
            font-weight: bolder;
        }

        .PremiumCoverDetails .list-group .list-group-item {
            border-right: white;
            border-left: white;

        }

        .PremiumCoverDetails .list-group .list-group-item:first-child {
            border-top: white;
        }

        .PremiumCoverDetails .list-group .list-group-item:last-child {
            border-bottom: white;
        }

        .rowFlex {
            display: flex;
        }

        .rowFlex ul li {
            border-bottom: 1px solid #ccc;
        }

        .rowFlex ul li a {
            border-radius: 0px !important;
        }

        .colsame {
            flex: 1;
            /* border: 1px solid #ccc; */
        }

        .tabsidebar {
            box-shadow: 0 1px 1px rgba(0, 0, 0, 0.25),
                0 2px 2px rgba(0, 0, 0, 0.20),
                0 4px 4px rgba(0, 0, 0, 0.15),
                0 8px 8px rgba(0, 0, 0, 0.10),
                0 16px 16px rgba(0, 0, 0, 0.05);
        }

        .submenus li {
            box-shadow: 0 1px 2px rgba(0, 0, 0, 0.07),
                0 2px 4px rgba(0, 0, 0, 0.07),
                0 4px 8px rgba(0, 0, 0, 0.07),
                0 8px 16px rgba(0, 0, 0, 0.07),
                0 16px 32px rgba(0, 0, 0, 0.07),
                0 32px 64px rgba(0, 0, 0, 0.07);
            border: 0 !important;
        }

        .coveragepadding {
            padding: 60px 10px 10px 10px;
        }

        .OnlyPremiumTable .table {
            width: 100%;
            border-collapse: collapse;
            border-radius: 20px;
            box-shadow: 0 1px 2px rgba(0, 0, 0, 0.07),
                0 2px 4px rgba(0, 0, 0, 0.07),
                0 4px 8px rgba(0, 0, 0, 0.07),
                0 8px 16px rgba(0, 0, 0, 0.07),
                0 16px 32px rgba(0, 0, 0, 0.07),
                0 32px 64px rgba(0, 0, 0, 0.07);
        }

        .OnlyPremiumTable .table td,
        .table th {

            border-top: 0;
        }

        .OnlyPremiumTable thead th:first-child {
            border-radius: 20px 0px 0px 0px !important;
        }

        .OnlyPremiumTable thead th:last-child {
            border-radius: 0px 20px 0px 0px !important;

        }

        .OnlyPremiumTable tbody tr:last-child td:first-child {
            border-radius: 0px 0px 0px 20px !important;

        }

        .OnlyPremiumTable tbody tr:last-child td:last-child {
            border-radius: 0px 0px 20px 0px !important;

        }

        .OnlyPremiumTable .table td,
        .OnlyPremiumTable .table th {
            vertical-align: middle;
        }



        .OnlyPremiumTable .table td,
        .OnlyPremiumTable .table th {
            padding: 10px;
            /* border: 1px solid #ddd; */
            font-size: 14px;
        }

        .OnlyPremiumTable .table th {
            color: black;
        }

        /* .OnlyPremiumTable .table tbody tr:nth-child(even) {
            background-color: #f5f5f5;
        } */

        /*responsive*/

        @media(max-width: 500px) {
            .OnlyPremiumTable .table thead {
                display: none;
            }

            .OnlyPremiumTable .table,
            .OnlyPremiumTable .table tbody,
            .OnlyPremiumTable .table tr,
            .OnlyPremiumTable .table td {
                display: block;
                width: 100%;
            }

            .OnlyPremiumTable .table tr {
                margin-bottom: 15px;
            }

            .OnlyPremiumTable .table td {
                text-align: right;
                padding-left: 50%;
                text-align: right;
                position: relative;
            }

            .OnlyPremiumTable .table td::before {
                content: attr(data-label);
                position: absolute;
                left: 0;
                width: 50%;
                padding-left: 15px;
                font-size: 13px;
                font-weight: bold;
                text-align: left;
            }
        }
        
        .nav-pills .nav-link.active, .nav-pills .show>.nav-link {
		    color: #fff; 
		    background-color: #261e6a;
		}
		.flex-column a{
			color: #261e6a;
			font-weight: bold;
		}
		
		.submenus a{
			color: #261e6a;
			font-weight: bold;
		}
    </style>
</head>
<body>
	<div id="loading" style="width:100%">
	   <img id="loading-image" src="${pageContext.request.contextPath}/images/madisonSymbolLogo.png"/>
	</div>
	<s:if test="hasActionErrors()">
		<div class="container QuotationInformationPage mt-5">
	    	<div class="Card_Parent  mt-4">
	           	<div class="card card-1">
	                <h4><s:label value="Validation Errors"/></h4>
	                <hr>
	                <div class="row">
	                    <div class="col-md-12 col-12" style="color: red;">
							<s:actionerror style="color:red;"/>
	                    </div>
	                </div>
	           	</div>
	        </div>
        </div>
    </s:if>
    <div class="container QuotationInformationPage mt-5">
        <div class="Card_Parent Coverdetails">
        	<s:set value="editQuoteDetails"/>
            <div class="card card-1">
                <h4>Policy Information</h4>
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
		<s:set var="coverReferralDesc" value="%{coverReferralRemarks}"/>
		<s:set name="premiumDisplay" value='("0".equalsIgnoreCase(#session.ContentType) && "".equalsIgnoreCase(#coverReferralDesc)) || "admin".equalsIgnoreCase(#session.usertype) || "RA".equalsIgnoreCase(menuType)'/>
        <s:if test='!"".equalsIgnoreCase(#coverReferralDesc) && !"RA".equalsIgnoreCase(menuType)'>
        	<div class="Card_Parent  mt-4">
            	<div class="card card-1">
	                <h4><s:label value="Referral Reasons"/></h4>
	                <hr>
	                <div class="row">
	                    <div class="col-md-12 col-12" style="color: red;">
							<s:iterator value='#coverReferralDesc.split("~")' status="stat"> 
								<li><b><s:property/></b></li>
							</s:iterator>
	                    </div>
	                </div>
            	</div>
            </div>
		</s:if>
        <s:form name="GeneralPremiumInfo" theme="simple">
	        <div class="Card_Parent  mt-4">
	            <div class="card card-2">
	            	<div class="row rowFlex">
		            	<div class="col-md-8 col-12">
		                	<h4>Quotation Details</h4>
							<s:if test='%{!"0".equals(#session.ContentType)}'>
			                	<span class="pullRight">
									<s:text name="home.packagePlan"/>&nbsp;:&nbsp;
									<s:property value="%{#session.ContentTypeName}"/>
								</span>
							</s:if>
		                </div>
		                <div class="col-md-2 col-12">
		                	<s:submit type="button" value="Attach Documents" onclick="return uploadDocuments();" cssClass="btn btn-sm btn-info"/>
		                </div>
	                </div>
	                <hr>
	                <div class="row rowFlex">
	                    <div class="col-md-2 col-12 colsame p-0 tabsidebar">
	                        <div>
	                            <ul class="nav nav-pills nav-justified flex-column">
	                            	<s:set var="dropDownSchemeListVar" value="dropDownSchemeList"/>
	                            	<s:iterator var="ddslv" value="#dropDownSchemeListVar" status="stat">
	                            		<li class="nav-item">
	                            			<a class='nav-link <s:if test="(dropDownScheme).equalsIgnoreCase(#ddslv.CODE)">active</s:if>' data-toggle="pill" style="cursor:pointer;" onclick="locationMenu('<s:property value="#ddslv.CODE"/>','<s:property value="#ddslv.CODEDESC"/>');coverageDtls('<s:property value="#ddslv.CODE"/>','','<s:property value="#ddslv.CODEDESC"/>','');">
		                                       <s:property value="#ddslv.CODEDESC"/>
		                                    </a>
	                            		</li>
	                            	</s:iterator>
	                            	<s:hidden name="dropDownScheme" id="dropDownScheme"/>
	                            	<s:hidden name="dropDownSchemeName" id="dropDownSchemeName"/>
	                            </ul>
	                        </div>
	                    </div>
	                    <div class="col-md-10 col-12 colsame" style="padding: 0px 0px 0px 10px !important;">
	            			<div class="row rowFlex">
	            				<div class="col-md-8 col-12">
	            					<h4 id="schemeNameDivId"><s:property value="dropDownSchemeName"/></h4>
	            				</div>
	            				<div class="col-md-4 col-12">
	            					<a style="cursor: pointer;" class="btn btn-sm btn-warning" onclick="editParScheme();">Edit SumInsured<s:if test='"RSAIssuer".equalsIgnoreCase(#session.usertype) || "Admin".equalsIgnoreCase(#session.usertype)'> & Rate</s:if></a>
	            				</div>
	            			</div>
	                		<hr>
	                        <div class="tabsidebar">
	                            <div class="tab-content">
                            		<div class="row">
                            			<div class="col-md-3">
                            				<button type="button" class="btn btn-block btn-info" data-toggle="modal" data-refresh="true" data-target="#extendedCoverageModal" data-backdrop="static" data-keyboard="false" onclick="funCoverageDetail('extendCover');">Extended Coverage</button>
                            			</div>
                            			<div class="col-md-3">
                            				<button type="button" class="btn btn-block btn-info" data-toggle="modal" data-refresh="true" data-target="#extendedCoverageModal" data-backdrop="static" data-keyboard="false" onclick="funCoverageDetail('excess');">Excess</button>
                            			</div>
                            			<div class="col-md-3">
                            				<button type="button" class="btn btn-block btn-info" data-toggle="modal" data-refresh="true" data-target="#extendedCoverageModal" data-backdrop="static" data-keyboard="false" onclick="funCoverageDetail('warranties');">Warranties</button>
                            			</div>
                            			<div class="col-md-3">
                            				<button type="button" class="btn btn-block btn-info" data-toggle="modal" data-refresh="true" data-target="#extendedCoverageModal" data-backdrop="static" data-keyboard="false" onclick="funCoverageDetail('excludeRisk');">Excluded Risk</button>
                            			</div>
                            		</div>
                            		<br/>
                                    <ul class="nav nav-pills nav-justified submenus" id="homeLocationList">
                                    </ul>
                                    <div class="tab-content OnlyPremiumTable p-3">
                                    	<div class="tab-pane active"  id="homeCoverageDetails">
                                    		<h5 class="text-center">Loading...</h5>
                                    	</div>
                                    </div>
	                            </div>
	                        </div>
	                    </div>
	                </div>
	            </div>
	        </div>
	        <s:if test='#coverReferralDesc != null && !"".equalsIgnoreCase(#coverReferralDesc) && !("RSAIssuer".equalsIgnoreCase(#session.usertype) || "Admin".equalsIgnoreCase(#session.usertype)))'>
	        </s:if>
	        <s:else>
		        <div class="Card_Parent  mt-4" id="premiumDetailsDivId">
		            <div class="card card-1">
		                <h4>Premium Details</h4>
		                <hr>
		                <div class="row rowFlex OnlyPremiumTable p-3">
			                <div class="col-md-2 col-2 colsame" style="padding: 0px 0px 0px 10px !important;" >
			                </div>
		                	<div class="col-md-8 col-8 colsame" style="padding: 0px 0px 0px 10px !important;">
		                		<table class="table">
		                			<thead>
		                				<tr>
		                					<th style="background-color:#261e6a;color:white;text-align:center;"></th>
		                					<th style="background-color:#261e6a;color:white;text-align:center;">Description</th>
		                					<th style="background-color:#261e6a;color:white;text-align:center;">Premium&nbsp;(ZMW)</th>
		                					<th style="background-color:#261e6a;color:white;text-align:center;"></th>
		                				</tr>
		                			</thead>
		                			<tbody>
		                			 	<s:if test='%{actualPremium!=null && !"".equalsIgnoreCase(actualPremium) && @java.lang.Double@valueOf(actualPremium)!=0}'>
			                				<tr>
				                				<td></td>
				                				<td align="left">
				                					<b>Base Premium</b>
				                				</td>
				                				<td align="right">
				                					<b><s:property value="getText('{0,number,#,##0.00}',{@java.lang.Double@valueOf(actualPremium)})"/></b>
				                				</td>
				                				<td></td>
			                				</tr>
		                				</s:if>
		                				<s:if test='%{actualOptionalPremium!=null && !"".equalsIgnoreCase(actualOptionalPremium) && @java.lang.Double@valueOf(actualOptionalPremium)!=0}'>
			                				<tr>
				                				<td></td>
				                				<td align="left">
				                					<b>Additional Cover Premium</b>
				                				</td>
				                				<td align="right">
				                					<b><s:property value="getText('{0,number,#,##0.00}',{@java.lang.Double@valueOf(actualOptionalPremium)})"/></b>
				                				</td>
				                				<td></td>
			                				</tr>
				                		</s:if>
			                			<s:if test='%{volDiscountAmount!=null && !"".equalsIgnoreCase(volDiscountAmount) && @java.lang.Double@valueOf(volDiscountAmount)!=0}'>
			                				<tr>
				                				<td></td>
				                				<td align="left">
				                					<b>Larger Volume Discount</b>
				                				</td>
				                				<td align="right">
				                					<b>-&nbsp;<s:property value="getText('{0,number,#,##0.00}',{@java.lang.Double@valueOf(volDiscountAmount)})"/></b>
				                				</td>
				                				<td></td>
			                				</tr>
		                				</s:if>
		                				<s:if test='%{corpDiscountAmount!=null && !"".equalsIgnoreCase(corpDiscountAmount) && @java.lang.Double@valueOf(corpDiscountAmount)!=0}'>
			                				<tr>
				                				<td></td>
				                				<td align="left">
				                					<b>Corporate Discount</b>
				                				</td>
				                				<td align="right">
				                					<b>-&nbsp;<s:property value="getText('{0,number,#,##0.00}',{@java.lang.Double@valueOf(corpDiscountAmount)})"/></b>
				                				</td>
				                				<td></td>
			                				</tr>
		                				</s:if>
		                				<s:if test='%{splDiscountAmount!=null && !"".equalsIgnoreCase(splDiscountAmount) && @java.lang.Double@valueOf(splDiscountAmount)!=0}'>
			                				<tr>
				                				<td></td>
				                				<td align="left">
				                					<b>Special Discount</b>
				                				</td>
				                				<td align="right">
				                					<b>-&nbsp;<s:property value="getText('{0,number,#,##0.00}',{@java.lang.Double@valueOf(splDiscountAmount)})"/></b>
				                				</td>
				                				<td></td>
			                				</tr>
		                				</s:if>
		                				<s:if test='%{totalPremium!=null && !"".equalsIgnoreCase(totalPremium) && @java.lang.Double@valueOf(totalPremium)!=0}'>
			                				<tr>
				                				<td></td>
				                				<td align="left">
				                					<b>Total Premium</b>
				                				</td>
				                				<td align="right">
				                					<b><s:property value="getText('{0,number,#,##0.00}',{@java.lang.Double@valueOf(totalPremium)})"/></b>
				                				</td>
				                				<td></td>
			                				</tr>
				                		</s:if>
			                			<s:if test='"RSAIssuer".equalsIgnoreCase(#session.usertype) || "Admin".equalsIgnoreCase(#session.usertype)'>
			                				<tr>
				                				<td></td>
				                				<td align="left">
				                					<b>Loading / Discount Premium</b>
				                				</td>
				                				<td align="right">
			                						<s:select theme="simple" name="excessSign" id="excessSign" cssclass="form-control" list="#{'+':'+','-':'-'}" />&nbsp;
			                						<s:textfield theme="simple" name="excessPremium" id="excessPremium" cssclass="form-control" maxlength="10" style="text-align: right;" onkeypress="return isNumberKey(event);"/>
				                				</td>
				                				<td></td>
			                				</tr>
			                			</s:if>
			                			<s:else>
			                				<s:if test='%{excessPremium!=null && !"".equalsIgnoreCase(excessPremium) && @java.lang.Double@valueOf(excessPremium)!=0}'>
				                				<tr>
					                				<td></td>
					                				<td align="left">
					                					<b>Loading / Discount Premium</b>
					                				</td>
					                				<td align="right">
					                					<b><s:property value="excessSign"/>&nbsp;<s:property value="getText('{0,number,#,##0.00}',{@java.lang.Double@valueOf(excessPremium)})"/></b>
					                				</td>
					                				<td></td>
				                				</tr>
			                				</s:if>
			                			</s:else>
			                			<s:if test='%{policyFee!=null && !"".equalsIgnoreCase(policyFee) && @java.lang.Double@valueOf(policyFee)!=0}'>
			                				<tr>
				                				<td></td>
				                				<td align="left">
				                					<b>Premium Tax&nbsp;(<s:property value="policyFeePercent"/>&nbsp;%)</b>
				                				</td>
				                				<td align="right">
				                					<b><s:property value="getText('{0,number,#,##0.00}',{@java.lang.Double@valueOf(policyFee)})"/></b>
				                				</td>
				                				<td></td>
			                				</tr>
		                				</s:if>
			                			<s:if test='%{finalPremium!=null && !"".equalsIgnoreCase(finalPremium) && @java.lang.Double@valueOf(finalPremium)!=0}'>
			                				<tr>
				                				<td></td>
				                				<td align="left">
				                					<b>Total Premium Payable</b>
				                				</td>
				                				<td align="right">
				                					<b><s:property value="getText('{0,number,#,##0.00}',{@java.lang.Double@valueOf(finalPremium)})"/></b>
				                				</td>
				                				<td></td>
			                				</tr>
			                			</s:if>
		                			</tbody>
		                		</table>
		                	</div>
			                <div class="col-md-2 col-2 colsame" style="padding: 0px 0px 0px 10px !important;" >
			                </div>
		                </div>
		                <s:if test='"RSAIssuer".equalsIgnoreCase(#session.usertype) || "Admin".equalsIgnoreCase(#session.usertype)'>
	       					<div class="row">
		        			   <div class="col-md-8 col-2 col-sm-2">
		        			   </div>
		        			   <div class="col-md-2 col-2 col-sm-2" id="premCalcDivId">
				               		<a class="btn btn-primary btn-sm" onclick="calcPrem('');">Calculate</a>
				               </div>
				            </div>
			            </s:if>
	                </div>
	            </div>
            </s:else>
            <s:if test='("RA".equalsIgnoreCase(quoteStatus) || "RR".equalsIgnoreCase(quoteStatus) || "RU".equalsIgnoreCase(quoteStatus)) && ("RSAIssuer".equalsIgnoreCase(#session.usertype) || "Admin".equalsIgnoreCase(#session.usertype))'>
	            <div class="Card_Parent  mt-4">
		            	<div class="card card-1">
			                <h4>Referral Status</h4>
			                <hr>
			                <div class="row">
			                    <div class="col-xs-12 col-sm-12 col-md-7 col-lg-7">
									<div class="text">Status</div>
									<div class="tbox">
										<s:radio list="#{'Y':'Approve','N':'Reject','A':'Pending'}" value='%{adminRefStatus==null?"A":adminRefStatus}' name="adminRefStatus" id="adminRefStatus"/>
									</div>
								</div>
								<div class="col-xs-12 col-sm-12 col-md-5 col-lg-5">
									<div class="text">Comments</div>
									<div class="tbox">
										<s:textarea name="adminRemarks" id="adminRemarks" cssClass="form-control"  cssStyle="width: 100%;" onkeyup="textLimit(this,'2000')"/>
									</div>
								</div>
			                </div>
		            	</div>
		            </div>
            </s:if>
            <s:elseif test='!"RSAIssuer".equalsIgnoreCase(#session.usertype) && !"Admin".equalsIgnoreCase(#session.usertype)'>
		        <div class="Card_Parent  mt-4">
	            	<div class="card card-1">
		                <h4><s:text name="motor.referralYN"/></h4>
		                <hr>
		                <div class="row">
		                    <div class="col-xs-12 col-sm-12 col-md-7 col-lg-7">
								<div class="text"><s:text name="motor.referralYN"/></div>
								<div class="tbox">
									<s:radio list="#{'Y':'Yes','N':'No'}" name="referralYN" id="referralYN"  onclick="disableComments(this.value);"/>
								</div>					        						
							</div>
							<div class="col-xs-12 col-sm-12 col-md-5 col-lg-5" id="referralCommentsDivId">
								<div class="text"><s:text name="motor.comments"/></div>
								<div class="tbox">
									<s:textarea name="referralComments" id="referralComments" cssClass="form-control "  cssStyle="width: 100%;" onkeyup="textLimit(this,'2000')"/>
								</div>
							</div>
		                </div>
	            	</div>
	            </div>
	        </s:elseif>
			<s:hidden name="quoteNo"/>
			<s:hidden name="applicationNo"/>
			<s:hidden name="menuType"/>
			<s:hidden name="schemeSelected"/>
			<s:hidden name="quoteStatus" id="quoteStatus"/>
			<s:hidden name="add" id="add"/> 
			<s:hidden name="locationSelected" id="locationSelected"/>
			<s:hidden name="locationIds" id="locationIds"/>
			<s:hidden name="locationSize" id="locationSize"/>
			<s:hidden name="editFrom" id="editFrom"/>
	    </s:form>
		<div id="premiumSubUploadModal" class="modal fade" role="dialog">
			<div class="modal-dialog modal-xl container QuotationInformationPage mt-5" style="width: 90%;">
				<div class="modal-content Card_Parent  mt-4" >
	                <h4 id="coverNameSpan"></h4>
	                <hr>
	                <div class="row rowFlex OnlyPremiumTable p-3"  id="premiumSubUploadAjax" align="center">
	                </div>
				</div>
			</div>
		</div>
		<div id="extendedCoverageModal" class="modal fade" role="dialog">
			<div class="modal-dialog modal-xl container QuotationInformationPage mt-5" style="width: 90%;">
				<div class="modal-content Card_Parent  mt-4"  id="extendedCoverageAjax">
				</div>
			</div>
		</div>
        <div class="row mt-4">
            <div class="col-md-2 col-2 col-sm-2 offset-md-4">
				<a class="btn btn-danger btn-block" id="bpBackBtnId" style="cursor: pointer;" onclick="fnsubmit('getBackHome');hideBtn('bpBackBtnId');">Back</a>
            </div>
	        <div class="col-md-2 col-2 col-sm-2">
	        	<s:if test='("RA".equalsIgnoreCase(quoteStatus) || "RR".equalsIgnoreCase(quoteStatus) || "RU".equalsIgnoreCase(quoteStatus)) && ("RSAIssuer".equalsIgnoreCase(#session.usertype) || "Admin".equalsIgnoreCase(#session.usertype))'>
	        		<a class="btn btn-primary btn-block" style="cursor: pointer" onclick="fnRPopup();" data-toggle="modal" data-target="#referralSbModal" data-backdrop="static">Submit</a>
	        	</s:if>
	        	<s:else>
	        		<a class="btn btn-primary btn-block" style="cursor: pointer" id="bpSumbitBtnId" onclick="fnsubmit('saveUWDetailsHome');hideBtn('bpSumbitBtnId');">Proceed</a>
	        	</s:else>
	        </div>
        </div>
     </div>
     <div id="referralSbModal" class="modal fade" role="dialog">
    	<div class="modal-dialog modal-lg">
    		<div class="modal-content">
		      	<div class="modal-header">
		        	<div class="modal-title">
						<div class="row">
		       				<div class="col-md-12 col-xs-12">
					         	<h3><s:text name="Confirmation" /></h3>
					         </div>
		    			</div>
					</div>
		      	</div>
			    <div class="modal-body" >
			       <div class="row" align="center">
		       			<div class="col-md-12 col-xs-12">
			        		<h4>Do you want to Update Referral Status as <b><span id="referralStatusDivId"></span></b> for Quote No. <b><span id="quoteNoDivId"></span></b> ?</h4>
			        	</div>
			        </div>
				    <div class="row mt-4">
				        <div class="col-md-2 col-2 col-sm-2 offset-md-4">
				            <a style="cursor:pointer" data-dismiss="modal" class="btn btn-danger btn-block">No</a>
				        </div>
				        <div class="col-md-2 col-2 col-sm-2">
				            <a style="cursor:pointer" id="bpRSumbitBtnId" onclick="fnsubmit('saveUWDetailsHome');hideBtn('bpRSumbitBtnId');" class="btn btn-primary btn-block">Yes</a>
				        </div>
			        </div>
			    </div>
	        </div>
	    </div>
    </div>
    <SCRIPT type="text/javascript">
    	
		function fnsubmit(action) {
			document.GeneralPremiumInfo.action = "${pageContext.request.contextPath}/" + action;
			document.GeneralPremiumInfo.submit();
		}
				
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
		
		function funCoverageDetail(reqFrom) {
			try{
				var id = 'extendedCoverageAjax';
				var schemeId = document.GeneralPremiumInfo.dropDownScheme.value;
			    var locationId = document.GeneralPremiumInfo.dropDownLocation.value;
			    var quoteNo = document.GeneralPremiumInfo.quoteNo.value;
			    var appNo = document.GeneralPremiumInfo.applicationNo.value;
				var val = '?quoteNo='+quoteNo
							+ '&applicationNo='+appNo
							+ '&schemeId=' + schemeId
							//+ '&scheme=' + schemeName
							+ '&reqFrom=' + reqFrom
							//+ '&dropDownLocation=' + locationId
				postRequest('${pageContext.request.contextPath}/'+id+'Home.action'+val, id);
			}catch(err){
				console.log(err);
			}
		}
		
		function editParScheme(){
			try{
				document.GeneralPremiumInfo.action = "${pageContext.request.contextPath}/editSchemeHome.action"
				document.GeneralPremiumInfo.submit();
			}catch(err){
				console.log(err);
			}
		}
		
		function uploadDocuments() {
			var URL ='${pageContext.request.contextPath}/documentUploadDoUpload.action?applicationNo=<s:property value="applicationNo"/>&quoteNo=<s:property value="quoteNo"/>&deleteVehicleId=';
			return popUp(URL,'700','500');
		}
		
		
		<s:if test='dropDownScheme!=null && !"".equalsIgnoreCase(dropDownScheme)'>
			locationMenu('','');
		</s:if>
		
		function locationMenu(val,val1){
			try{
				if(val!=null && val!=''){
					document.GeneralPremiumInfo.dropDownScheme.value = val;
				}
				if(val1!=null && val1!=''){
					document.GeneralPremiumInfo.dropDownSchemeName.value = val1;
				}
			    var schemeId = document.GeneralPremiumInfo.dropDownScheme.value;
			    var schemeName = document.GeneralPremiumInfo.dropDownSchemeName.value;
			    document.getElementById('schemeNameDivId').innerHTML = schemeName;
			    var quoteNo = document.GeneralPremiumInfo.quoteNo.value;
				var id = 'homeLocationList';
				var strUrl = '${pageContext.request.contextPath}/'+id+'Home.do?dropDownScheme='+schemeId+'&quoteNo='+quoteNo;
				$.ajax({
					   url: strUrl,
					   error: function() {
					      $('#'+id).html('<p>An error has occurred in jquery Ajax</p>');
					   },
					   success: function(data) {
					      $('#'+id).html(data);
					      coverageDtls('','','','');
					   },
					   beforeSend : function(){
						   $('#loading').show();
			           },
			           complete : function(){
			        	   $('#loading').hide();
			           },
					   type: 'POST'
				});
			}catch(err){
				console.log(err);
			}
		}
	    
		function coverageDtls(schId,locId,schName,calcMode){
			try{
				if(schId!=null && schId!=''){
					document.GeneralPremiumInfo.dropDownScheme.value = schId;
				}
				if(locId!=null && locId!=''){
					document.GeneralPremiumInfo.dropDownLocation.value = locId;
				}
				if(schName!=null && schName!=''){
					document.GeneralPremiumInfo.dropDownSchemeName.value = schName;
				}
			    var schemeId = document.GeneralPremiumInfo.dropDownScheme.value;
			    var schemeName = document.GeneralPremiumInfo.dropDownSchemeName.value;
			    document.getElementById('schemeNameDivId').innerHTML = schemeName;
			    var locationId = document.GeneralPremiumInfo.dropDownLocation.value;
			    var quoteNo = document.GeneralPremiumInfo.quoteNo.value;
			    var applicationNo = document.GeneralPremiumInfo.applicationNo.value;
			    var valE = '';
			    if(calcMode == 'schemeLoc'){
			    	var volDiscPer = document.GeneralPremiumInfo.ajVolDiscountPercent.value;
			    	var corpDiscPer = document.GeneralPremiumInfo.ajCorpDiscountPercent.value;
			    	var splDiscPer = document.GeneralPremiumInfo.ajSplDiscountPercent.value;
			    	valE = '&ajVolDiscountPercent='+volDiscPer+'&ajCorpDiscountPercent='+corpDiscPer+'&ajSplDiscountPercent='+splDiscPer;
			    }
				var id = 'homeCoverageDetails';
				var val = '?quoteNo='+quoteNo
					+'&applicationNo='+applicationNo
					+'&dropDownLocation='+locationId
					+'&productId=<s:property value="productId"/>'
					+'&contentTypeId=<s:property value="contentTypeId"/>'
					+'&dropDownScheme='+schemeId
					+'&premiumDisplayVal=<s:property value="premiumDisplay"/>&calcFrom='+calcMode;
				var strUrl = '${pageContext.request.contextPath}/'+id+'Home.action'+val+valE;
				$.ajax({
					   url: strUrl,
					   error: function() {
					      $('#'+id).html('<p>An error has occurred in jquery Ajax</p>');
					   },
					   success: function(data) {
					      $('#'+id).html(data);
					   },
					   beforeSend : function(){
							if(calcMode == 'schemeLoc'){
						   		document.getElementById('schLocDiscCalcBtnId').style.display = 'none';
							}
						   $('#loading').show();
			           },
			           complete : function(){
			        	   if(calcMode == 'schemeLoc'){
						   		document.getElementById('schLocDiscCalcBtnId').style.display = 'none';
						   		calcPrem(calcMode);
			        	   }
			        	   $('#loading').hide();
			           },
					   type: 'POST'
				});
			}catch(err){
				console.log(err);
			}
		}
		disableComments()
		function disableComments(){
			try{
				var val = document.getElementById('referralYNY').checked;
				val = val+'';
				if(val == 'true'){
					document.getElementById('referralCommentsDivId').style.display = 'block';
				}else{
					document.getElementById('referralComments').value = '';
					document.getElementById('referralCommentsDivId').style.display = 'none';
				}
			}catch(err){
				console.log(err);
			}
		}
		
		function isNumberKey(evt){
			try{
	          var charCode = (evt.which) ? evt.which : evt.keyCode;
	          if (charCode != 46 && charCode > 31 
	            && (charCode < 48 || charCode > 57))
	             return false;

	          return true;
			}catch(err){
				console.log(err);
			}
	    }
		
		function calcPrem(calcMode){
			try{
				if(calcMode == 'schemeLoc'){
					var es = '';
				    var ep = '';
				}else{
				    var es = document.GeneralPremiumInfo.excessSign.value;
				    var ep = document.GeneralPremiumInfo.excessPremium.value;
				}
			    var quoteNo = document.GeneralPremiumInfo.quoteNo.value;
				var id = 'premiumDetailsDivId';
				var strUrl = '${pageContext.request.contextPath}/premiumCalcHome.do';
				$.ajax({
					   url: strUrl,
					   data:{
						   excessSign : es,
						   excessPremium : ep,
						   quoteNo : quoteNo,
						   calcFrom : calcMode
					   },
					   error: function() {
					      $('#'+id).html('<p>An error has occurred in Premium Calculation. Please Contact Technical Team.</p>');
					   },
					   success: function(data) {
					      $('#'+id).html(data);
					   },
					   beforeSend : function(){
						   if(calcMode == 'schemeLoc'){
						   		document.getElementById('schLocDiscCalcBtnId').style.display = 'none';
						   }else{
						   		document.getElementById('premCalcDivId').style.display = 'none';
						   }
						   $('#loading').show();
			           },
			           complete : function(){
						   if(calcMode == 'schemeLoc'){
						   		document.getElementById('schLocDiscCalcBtnId').style.display = 'block';
						   }else{
						   		document.getElementById('premCalcDivId').style.display = 'block';
						   }
			        	   $('#loading').hide();
			           },
					   type: 'POST'
				});
			}catch(err){
				console.log(err);
			}
		}
		
		function hideBtn(id){
			try{
				document.getElementById(''+id).style.display = 'none';
			}catch(err){
				console.log(err);
			}
		}
		
		function fnRPopup(){
			try{
				var rs = document.GeneralPremiumInfo.adminRefStatus.value;
				var quoteNo = document.GeneralPremiumInfo.quoteNo.value;
				var refstat = '';
				if(rs == 'A'){
					refstat = 'Pending';
				}else if(rs == 'Y'){
					refstat = 'Approved';
				}else if(rs == 'N'){
					refstat = 'Rejected';
				}
				document.getElementById('referralStatusDivId').innerHTML = refstat;
				document.getElementById('quoteNoDivId').innerHTML = quoteNo;
			}catch(err){
				console.log(err);
			}
		}
		
		function disableExcessAr(id){
			try{
				var isChecked = document.getElementById('excessDeleteAr_'+id).checked;
				if(isChecked == true){
					document.getElementById('excessPercentAr_'+id).readOnly = true;
					document.getElementById('excessAmountAr_'+id).readOnly = true;
					document.getElementById('excessDescAr_'+id).readOnly = true;
				}else{
					document.getElementById('excessPercentAr_'+id).readOnly = false;
					document.getElementById('excessAmountAr_'+id).readOnly = false;
					document.getElementById('excessDescAr_'+id).readOnly = false;
				}
			}catch(err){
				console.log(err);
			}
		}
		
		function disableWarrantiesAr(id){
			try{
				var isChecked = document.getElementById('warrantiesDeleteAr_'+id).checked;
				if(isChecked == true){
					document.getElementById('warrantiesDescAr_'+id).readOnly = true;
				}else{
					document.getElementById('warrantiesDescAr_'+id).readOnly = false;
				}
			}catch(err){
				console.log(err);
			}
		}
		
		function disableExclusionsAr(id){
			try{
				var isChecked = document.getElementById('exclusionsDeleteAr_'+id).checked;
				if(isChecked == true){
					document.getElementById('exclusionsDescAr_'+id).readOnly = true;
				}else{
					document.getElementById('exclusionsDescAr_'+id).readOnly = false;
				}
			}catch(err){
				console.log(err);
			}
		}
		
		function addNewExcessAr(){
			try{
				document.getElementById('excessArneaDivId').style.display='none';
				document.getElementById('excessAryeaDivId').style.display='block';
				
				var table = document.getElementById('excessArTableId');
				var rowCount = table.rows.length;
				var row = table.insertRow(rowCount);
				row.align = "center";
				
				cell = row.insertCell(0);
				var element = document.createElement("input");
				element.className = "form-control";
				element.type = "text";
				element.name = "excessPercentAr["+(rowCount-1)+"]";
				element.setAttribute("maxlength", "6");
				element.id = "excessPercentAr_"+(rowCount-1);
				element.setAttribute("onkeypress", "return isNumberKey(event);");
				cell.appendChild(element);

				var element = document.createElement("input");
				element.type = "hidden";
				element.name = "excessIdAr["+(rowCount-1)+"]";
				element.id = "excessIdAr_"+(rowCount-1);
				element.value = "99999";
				cell.appendChild(element);
				
				cell = row.insertCell(1);
				var element = document.createElement("input");
				element.className = "form-control";
				element.type = "text";
				element.name = "excessAmountAr["+(rowCount-1)+"]";
				element.setAttribute("maxlength", "10");
				element.id = "excessAmountAr_"+(rowCount-1);
				element.setAttribute("onkeypress", "return isNumberKey(event);");
				cell.appendChild(element);
				
				cell = row.insertCell(2);
				var element = document.createElement("textarea");
				element.className = "form-control";
				element.type = "text";
				element.name = "excessDescAr["+(rowCount-1)+"]";
				element.id = "excessDescAr_"+(rowCount-1);
				cell.appendChild(element);
				
				cell = row.insertCell(3);
				var element = document.createElement("input");
				element.type = "checkbox";
				element.name = "excessDeleteAr["+(rowCount-1)+"]";
				element.id = "excessDeleteAr_"+(rowCount-1);
				element.value = "true";
				element.onclick = "disableExcessAr('"+(rowCount-1)+"');"
				cell.appendChild(element);
				
				var element = document.createElement("input");
				element.type = "hidden";
				element.name = "__checkbox_excessDeleteAr["+(rowCount-1)+"]";
				element.id = "__checkbox_excessDeleteAr_"+(rowCount-1);
				element.value = "true";
				cell.appendChild(element);
			}catch(err){
				console.log(err);
			}
		}
		
		function addNewWarrantiesAr(){
			try{
				document.getElementById('warrantiesArneaDivId').style.display='none';
				document.getElementById('warrantiesAryeaDivId').style.display='block';
				
				var table = document.getElementById('warrantiesArTableId');
				var rowCount = table.rows.length;
				var row = table.insertRow(rowCount);
				row.align = "center";
				
				cell = row.insertCell(0);     
		    	cell.align = "center"; 
		     	cell.innerHTML = rowCount;

				var element = document.createElement("input");
				element.type = "hidden";
				element.name = "warrantiesIdAr["+(rowCount-1)+"]";
				element.id = "warrantiesIdAr_"+(rowCount-1);
				element.value = "99999";
				cell.appendChild(element);
				
				cell = row.insertCell(1);
				var element = document.createElement("textarea");
				element.className = "form-control";
				element.type = "text";
				element.name = "warrantiesDescAr["+(rowCount-1)+"]";
				element.id = "warrantiesDescAr_"+(rowCount-1);
				cell.appendChild(element);
				
				cell = row.insertCell(2);
				var element = document.createElement("input");
				element.type = "checkbox";
				element.name = "warrantiesDeleteAr["+(rowCount-1)+"]";
				element.id = "warrantiesDeleteAr_"+(rowCount-1);
				element.value = "true";
				element.onclick = "disableWarrantiesAr('"+(rowCount-1)+"');"
				cell.appendChild(element);
				
				var element = document.createElement("input");
				element.type = "hidden";
				element.name = "__checkbox_warrantiesDeleteAr["+(rowCount-1)+"]";
				element.id = "__checkbox_warrantiesDeleteAr_"+(rowCount-1);
				element.value = "true";
				cell.appendChild(element);
			}catch(err){
				console.log(err);
			}
		}
		
		function addNewExclusionsAr(){
			try{
				document.getElementById('exclusionsArneaDivId').style.display='none';
				document.getElementById('exclusionsAryeaDivId').style.display='block';
				
				var table = document.getElementById('exclusionsArTableId');
				var rowCount = table.rows.length;
				var row = table.insertRow(rowCount);
				row.align = "center";
				
				cell = row.insertCell(0);     
		    	cell.align = "center"; 
		     	cell.innerHTML = rowCount;

				var element = document.createElement("input");
				element.type = "hidden";
				element.name = "exclusionsIdAr["+(rowCount-1)+"]";
				element.id = "exclusionsIdAr_"+(rowCount-1);
				element.value = "99999";
				cell.appendChild(element);
				
				cell = row.insertCell(1);
				var element = document.createElement("textarea");
				element.className = "form-control";
				element.type = "text";
				element.name = "exclusionsDescAr["+(rowCount-1)+"]";
				element.id = "exclusionsDescAr_"+(rowCount-1);
				cell.appendChild(element);
				
				cell = row.insertCell(2);
				var element = document.createElement("input");
				element.type = "checkbox";
				element.name = "exclusionsDeleteAr["+(rowCount-1)+"]";
				element.id = "exclusionsDeleteAr_"+(rowCount-1);
				element.value = "true";
				element.onclick = "disableExclusionsAr('"+(rowCount-1)+"');"
				cell.appendChild(element);
				
				var element = document.createElement("input");
				element.type = "hidden";
				element.name = "__checkbox_exclusionsDeleteAr["+(rowCount-1)+"]";
				element.id = "__checkbox_exclusionsDeleteAr_"+(rowCount-1);
				element.value = "true";
				cell.appendChild(element);
			}catch(err){
				console.log(err);
			}
		}
		
		function postFormRequestNewie(strUrl, id, formId) {
			try{
				$.ajax({
					url : strUrl,
					type : "POST",
					data : $("#" + formId).serialize(),
					error: function() {
						$('#'+id).html('<p>An error has occurred in jquery Ajax</p>');
					},		   
					success: function(data) {
						$('#'+id).html(data);
					},
					beforeSend : function(){
						document.getElementById('coverageDtlSaveBtnId').style.display = 'none';
						$('#loading').show();
					},
					complete : function(){
						document.getElementById('coverageDtlSaveBtnId').style.display = 'block';
						$('#loading').hide();
					}
				});
			}catch(err){
				console.log(err);
			}
		}
		
		function saveCoveragesAr() {
			try{
				var reqFrom = document.coverageDtl.reqFrom.value;
				if(reqFrom == 'extendCover'){
				 	var array = []; 
			         $("input:checkbox[name=extendedCover]:checked").each(function() { 
			             array.push($(this).val()); 
			         }); 
			         document.coverageDtl.coverSelected.value=array;
				}
				postFormRequestNewie('${pageContext.request.contextPath}/saveCoveragesArHome.action', 'extendedCoverageAjax', 'coverageDtl');
				return false;
			}catch(err){
				console.log(err);
			}
		}
		
	</SCRIPT>
</body>
</html>