<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ page import="java.util.List,java.util.ArrayList,java.util.Map,java.util.HashMap,java.util.Collections"%>
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
	</style>
</head>
<body>
<s:set var="disable1" value="%{((endTypeId!=null && endTypeId.length() > 0)||(quoteStatus=='RA'))}"/>
<s:set var="endtDisable" value="%{endTypeId!=null && endTypeId.length() > 0}"/>
<div id="loading" style="width:100%">
   <img id="loading-image" src="${pageContext.request.contextPath}/images/ajax-loader-bar.gif"/>
</div>
<div class="row">
	<div class='${("User"==userType||"B2C"==session.LoginType)?"col-xs-12 col-sm-12 col-md-12 col-lg-12":"col-xs-12"}'>
        <s:set var="multiVehicleDtls" value="%{multiVehicleDetails}"/>
        <s:set var="totPremium" value="0"/>
        <s:form id="motor" name="motor" method="post" theme="simple" >
	        <div class="row">
				<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
					<span style="color:red;"><s:actionerror/></span>
				</div>
			</div>
			<div class="row">
				<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
					<div class="panel panel-primary">
	        			<div class="panel-heading txtB">
	        				<s:text name="motor.vehicleDetails" />
	        			</div>
	        			<div class="panel-body">
							<div class="row">
	        					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
	        						<s:set name="multiVehicleDtls" value="%{multiVehicleDetails}"/>
									<table cellpadding="1" class="footable" cellspacing="1" border="1">
										<thead>
									        <tr>
												<th style="width:5%;"><s:label value="S.No." /></th>
												<th style="width:15%;"><s:text name="motor.vehicleUsage"/></th>
												<th style="width:20%;"><s:text name="motor.make" /></th>
												<th style="width:20%;"><s:text name="motor.model" /></th>
												<th style="width:15%;"><s:text name="motor.typeOfBody" /></th>
												<th style="width:15%;"><s:text name="motor.sumInsured" /></th>
												
											</tr>
										</thead>
										<tbody>
											<s:iterator value="#multiVehicleDtls" var="view" status="status">
												<tr>
													<td align="center"><s:property value="#status.count" /></td>
													<td align="center"><s:property value="#view.VEHICLETYPE_DESC" /></td>
													<td align="center"><s:property value="#view.MAKE_NAME" /></td>					
													<td align="center"><s:property value="#view.MODEL_NAME" default=" " /> </td>
													<td align="center"><s:property value="#view.TYPE_OF_BODY_NAME" /></td>
													<td align="right"><s:property value="getText('{0,number,#,##0.00}',{#view.SUMINSURED_VALUE_LOCAL})"/></td>
												</tr>
											</s:iterator>
										</tbody>
									</table>
								</div>							
							</div>						
	        			</div>
	        		</div>        		
				</div>
			</div>
			<div class="row">
				<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
					<div class="panel panel-primary">
	        			<div class="panel-heading txtB">
							<s:text name="label.quoteDetail" />
	        			</div>
	        			<div class="panel-body">
	        				<div class="row">
	        					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
									<table width="100%" class="footable">
										<s:set name="groupId" value=""/>
										<s:set var="preAmt" value="0.0" scope="page"/>
										<s:iterator value="premiumInfo" var="prInfo" status="stat">
										<s:if test="%{#groupId != #prInfo.GROUP_ID}">
											<s:set name="groupId" value="%{#prInfo.GROUP_ID}"/>
											<thead>
											<tr>	     
										        <th colspan="5" ><s:property  value="%{#prInfo.GROUP_DESC_ENGLISH}"/></th>
											</tr>
											</thead>
										</s:if>
										<tbody>
										<tr>       
											<td align="center" width="10%">
												<s:hidden name="coverId[%{#stat.count-1}]" value="%{#prInfo.POLICYTYPE_COVERID}"/>
											</td>
											<td align="left" width="20%">
												<s:property  value="%{#prInfo.OPCOVER_DESC_ENGLISH}"/>
											</td>
											<td align="left" width="25%">
												<s:property value="getText('{0,number,#,##0.00}',{#prInfo.SUM_INSURED})"/>
												<s:hidden name="sI[%{#stat.count-1}]"  cssClass="inputBox" value="%{#prInfo.SUM_INSURED}" cssStyle="text-align:right;"/>
											</td>
											<td align="left" width="25%">
												<s:textfield name="baseRate[%{#stat.count-1}]" cssClass="inputBox tooltipContent" data-content="Base Rate" value="%{#prInfo.RATE}" size="11" cssStyle="text-align:right; width:100%;" maxLength="16" onkeyup="checkDecimals(this);" readonly="true"/>
											</td>
											<td align="right" width="20%"> 
												<s:property value="getText('{0,number,#,##0.00}',{#prInfo.PREMIUM})"/>
												<s:set var="preAmt" value="%{@java.lang.Double@parseDouble(#attr.preAmt)+@java.lang.Double@parseDouble(#prInfo.PREMIUM)}" scope="page"/>
											</td>
										</tr>								
										</tbody>
										</s:iterator>
										<%--<s:if test="@java.lang.Double@parseDouble(miniPre) >  @java.lang.Double@parseDouble(#attr.preAmt)" >
										<s:set var="preAmt" value="%{@java.lang.Double@parseDouble(miniPre)}" scope="page"/>
										</s:if>
										--%><tbody>
										<tr>
											<td></td>
											<td></td>									
											<td></td>
											<td>
												<font style="float:left;"><s:text name="motor.Premium"/></font>
												<b style="float:right;">[<s:property value="currencyType"/>]</b>
											</td>
											<td width="100%" align="right">
												<%-- &nbsp;&nbsp;&nbsp;
												<s:textfield name="premium" id="premium" cssClass="inputBox" value="%{#attr.preAmt}" onkeyup="checkDecimals(this);" maxlength="11" cssStyle="text-align:right;" readonly="true"/>
												--%>
												<s:textfield name="premium" id="premium" cssClass="inputBox tooltipContent" data-content="Premium" value="%{getText('{0,number,0.00}',{#attr.preAmt})}" onkeyup="checkDecimals(this);" maxlength="11" cssStyle="text-align:right;" readonly="true"/>
											</td>
										</tr>
										<s:if test='#session.user1 == "admin" || quoteStatus == "RA"'>
										<tr>
											<td></td>
											<td></td>									
											<td></td>
											<td>
											<font style="float:left;"><s:text name="motor.loadingOrDiscountPremium"/></font>
											<b style="float:right;">[<s:property value="currencyType"/>]</b>
											</td>
											<td align="right">
												<s:select list="#{'+':'+','-':'-'}" name="sign" onchange="getTotalPremium(this.form)" disabled="%{quoteStatus=='RA' && #session.user1 != 'admin'?'true':'false'}" cssClass="inputBox" cssStyle="width:30%;"/>&nbsp;&nbsp;&nbsp;
					                            <s:textfield name="loadOrDiscPremium" id="loadOrDiscPremium" cssClass="inputBox tooltipContent" data-content="Loading or Discount Premium" disabled="%{quoteStatus=='RA' && #session.user1 != 'admin'?'true':'false'}" onkeyup="checkDecimals4_2(this);getTotalPremium(this.form);" maxlength="11" cssStyle="text-align:right;width:60%;"/>
					                            <s:if test='"+".equalsIgnoreCase(sign)'>
					                            	<s:set var="preAmt" value="%{@java.lang.Double@parseDouble(#attr.preAmt)+@java.lang.Double@parseDouble(loadOrDiscPremium)}" scope="page"/>
					                            </s:if>
					                            <s:else>
					                            	<s:set var="preAmt" value="%{@java.lang.Double@parseDouble(#attr.preAmt)-@java.lang.Double@parseDouble(loadOrDiscPremium)}" scope="page"/>
					                            </s:else>
											</td>
										</tr>
										</s:if>
										<tr>
											<td></td>
											<td></td>									
											<td></td>
											<td>
												<font style="float:left;"><s:text name="motor.policyFee"/></font>
												<b style="float:right;">[<s:property value="currencyType"/>]</b>
											</td>
											<td width="100%" align="right">
												<s:textfield name="policyFee" id="policyFee" cssClass="inputBox tooltipContent" data-content="Policy Fee" onkeyup="checkDecimals(this);" maxlength="11" cssStyle="text-align:right;" readonly="true"/>
											</td>
										</tr>
										<tr>
											<td></td>
											<td></td>									
											<td></td>
											<td>
												<font style="float:left;"><s:text name="motor.totalPremiumPayable" /></font>
												<b style="float:right;">[<s:property value="currencyType"/>]</b>
											</td>
												
											<td width="100%" align="right">
												<%-- 
												<s:textfield name="totalPremium" id="totalPremium" cssClass="inputBox" value="%{@java.lang.Double@parseDouble(#attr.preAmt)+@java.lang.Double@parseDouble(policyFee)}" onkeyup="checkDecimals(this);" maxlength="11" cssStyle="text-align:right;" readonly="true"/>
												--%>
												<s:set var="totPremium" value="%{getText('{0,number,0.00}',{@java.lang.Double@parseDouble(#attr.preAmt)+@java.lang.Double@parseDouble(policyFee)})}"/>
												<s:textfield name="totalPremium" id="totalPremium" cssClass="inputBox tooltipContent" data-content="Total Premium" value="%{#totPremium}" onkeyup="checkDecimals(this);" maxlength="11" cssStyle="text-align:right;" readonly="true"/>
											</td>
										</tr>
										<s:if test='#session.user1 == "admin" || (!"".equals(adminRemarks)&&(adminRemarks!=null)&& #session.user1 != "admin")'>
										<tr>
											<td></td>
											<td></td>									
											<td></td>
											<td align="right"><s:text name="motor.specialCondition"/></td>
											<td>
												<s:textarea name="adminRemarks" onkeyup="textLimit(this,'470')"  cols="50" rows="3" readonly="%{quoteStatus=='RA' && #session.user1 != 'admin'?'true':'false'}"/>
											</td>
										</tr>
										</s:if>
										<s:if test='#session.user1 == "admin"'>
										<tr>
											<td></td>
											<td></td>									
											<td></td>
											<td align="right"><s:text name="motor.referralStatus"/></td>
											<td><s:radio list="#{'Y':'Accept','N':'Reject','A':'Pending'}" name="adminRefStatus" value="%{adminRefStatus==null?'A':adminRefStatus}" /></td>
										</tr>
										<br /></s:if>
										</tbody>	
									</table>
		       						<br class="clear"/>
		       					</div>
		       				</div>
		       			</div>
		       		</div>
				</div>
			</div>
        </s:form>
	</div>
</div>
<script  type="text/javascript">

</script>
</body>
</html>