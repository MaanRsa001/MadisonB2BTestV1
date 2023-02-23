<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ page language="java" import="com.maan.report.service.ReportService"%>
<!DOCTYPE HTML>
<html>
<head>
<s:if test='menuType=="CHART"'>
	<!-- Load jQuery -->
	<script src="${pageContext.request.contextPath}/bootstrap/js/jquery-1.9.1.min.js"></script>
	<!-- Chartinator  -->
	<script src="${pageContext.request.contextPath}/js/highcharts.js" ></script>    
</s:if>
<s:if test='menuType!="CHART"'>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/dataTables/css/dataTables.responsive.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/dataTables/css/dataTables.jqueryui.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/dataTables/css/dataTables.tableTools.css">
	<link href="${pageContext.request.contextPath}/dataTables/css/jquery-start-ui.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="${pageContext.request.contextPath}/dataTables/js/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/dataTables/js/jquery.dataTables.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/dataTables/js/dataTables.jqueryui.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/dataTables/js/dataTables.responsive.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/dataTables/js/dataTables.tableTools.js"></script>
	<script type="text/javascript">
  	jQuery(function ($) {
  		try {
  			var data = $('#gridTable').dataTable( {
				"lengthMenu": [[10, 25, 50, -1], [10, 25, 50, "All"]],
				"order": [[ 1, "asc" ]],
				"columnDefs": [ {
		          "targets": 'no-sort',
		          "orderable": false
			    } ],
				responsive: true
			});
			var data1 = $('#record').dataTable( {
				"lengthMenu": [[10, 25, 50, -1], [10, 25, 50, "All"]],
				"order": [[ 1, "asc" ]],
				"columnDefs": [ {
		          "targets": 'no-sort',
		          "orderable": false
			    } ],
				responsive: true
			});
		} catch(err){}
	} );
  	</script>
</s:if>
<style type="text/css">
.deatilsBox {
	padding: 10px;
	background: #fff;
	border: 1px solid #e7e7e7;
	-webkit-box-shadow: 0 1px 2px #3a1e52;
	-moz-box-shadow: 0 1px 2px #3a1e52;
	box-shadow: 0 1px 2px #3a1e52;
	z-index: 10000000;
}

  #loading {
  width: 100%;
  height: 100%;
  top: 0px;
  left: 0px;
  position: fixed;
  display: block;
  opacity: 0.7;
  background-color: black;
  z-index: 99;
  opacity:0.5;
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
<s:form name="report" theme="simple">
<div id="loading" style="width:100%">
   <img id="loading-image" src="${pageContext.request.contextPath}/images/madisonSymbolLogo.png"/>
</div>
<div class="row">
	<s:set var="format" value="%{'number.format.'+#session.BrokerDetails.CurrencyDecimal}"></s:set>
	<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
		<s:if test='menuType!="CHART"'>
			<div class="panel panel-primary">
				<div class="panel panel-heading">
					<span class="pullLeft">
					<s:if test='menuType!="CHART"'>
						<s:text name="report.%{menuType}" />
					</s:if>					
					</span>
					<span class="pullRight">
						<s:if test='menuType!="ET" && menuType!="E" && menuType!="CHART" && (#session.usertype==getText("BROKER") || #session.usertype==getText("ISSUER") || #session.usertype==getText("USER"))'>
							<s:if test='#session.product_id==getText("OPEN_COVER")'>
								<s:label key="endt.policyNo"/> : <span style="color:yellow;"><s:property value="%{originalPolicyNo}"/></span>
							</s:if> 
						</s:if>
						<%--
						<s:elseif test='menuType!="ET" || menuType!="E"'>
			  				<s:hidden name="loginId"></s:hidden>
			  			</s:elseif>
			  			--%>
					</span>
					<br class="clear" />
				</div>
				<div class="panel-body" style="padding-top: 0px;">
					<s:if test='menuType!="ET" && menuType!="E" && menuType!="CHART" && (#session.usertype==getText("BROKER") || #session.usertype==getText("ISSUER") || #session.usertype==getText("USER"))'>
						<s:if test='(menuType=="PD" && searchFrom != "BS"'>
							<div class="col-xs-12 col-sm-12 col-md-6 col-lg-4">
								<div class="text">
									<s:text name='%{#session.product_id!=getText("OPEN_COVER")?"report.searchByPolicyNo":"report.searchByCertNo"}' />
								</div>
								<div class="tbox">
									<s:textfield name="searchValue" id="searchValue" cssClass="inputBox" />
								</div>
							</div>	
							<div class="col-xs-12 col-sm-12 col-md-6 col-lg-4" align="center">
								<s:hidden name="searchBy" value="policyNo"/>
								<s:submit type="submit" name="go" key="Go" cssClass="btn btn-sm btn-success" onclick="return searchByPolicy()"/>
							</div>
						</s:if>
						<s:if test='(#session.usertype!=getText("USER"))'>
							<s:if test='searchFrom == "BS"'> 
		  						<s:hidden name="searchValue" id="searchValue" />&nbsp;<s:hidden name="searchBy" value="policyNo"/>
		  					</s:if>
		  					<s:if test='(#session.usertype==getText("BROKER") || (#session.product_id!=getText("OPEN_COVER") && #session.usertype==getText("ISSUER")))'>
		  						<s:if test='menuType=="R" || menuType=="PR" || "BQE".equalsIgnoreCase(menuType) || "BP".equalsIgnoreCase(menuType) || "BRU".equalsIgnoreCase(menuType) || "PVD".equalsIgnoreCase(menuType) || "BRP".equalsIgnoreCase(menuType)'>
		  							<s:hidden name="loginId"/>
		  						</s:if>
		  						<s:else>
		  							<div class="col-xs-12 col-sm-12 col-md-6 col-lg-4">
										<div class="text">
											<s:label key="report.selectuser" theme="simple"/>
										</div>
										<div class="tbox">
											<s:select list="userSelection" cssClass="inputBoxS" listKey="LOGIN_ID" listValue="USERNAME" name="loginId"  headerKey="" headerValue="Select"  onchange="userSelectReport(this)" disabled='issuer !=null && issuer.length() > 0 && #session.product_id==getText("OPEN_COVER")'/>
										</div>
									</div>
		  						</s:else>
		  					</s:if>
		  					<s:else>
		  						<s:hidden name="loginId"/>
		  					</s:else>
						</s:if>
						<s:else>
	  						<s:hidden name="loginId"/>
	  					</s:else>
					</s:if>
					<%--
					<s:elseif test='menuType!="ET" || menuType!="E"'>
		  				<s:hidden name="loginId"></s:hidden>
		  			</s:elseif>
		  			--%>
		  			<s:else>
		  				<s:hidden name="loginId"></s:hidden>
		  			</s:else>
		  			<br class="clear"/>
		  			<s:if test='menuType=="R"'>
						<div class="boxcontent">
							<div style="color: red;"><s:actionerror/></div>
							<br/>
							<table class="tableWidth">
								<tr>
									<td width="50%"><s:text name="report.startDate" /><font color="red">*</font></td>
									<td width="50%"><s:textfield id="rpolicyStartDateSearch" name="policyStartDate" cssClass="inputBox datepicker" readonly="true" disabled="#disable" /></td>
								</tr>
								<tr><td colspan="2" height="5"></td></tr>
								<tr>
									<td><s:text name="report.endDate" /><font color="red">*</font></td>
									<td><s:textfield id="rpolicyEndDateSearch" name="policyEndDate" cssClass="inputBox datepicker" readonly="true" disabled="#disable" /></td>
								</tr>
								<tr><td colspan="2" height="5"></td></tr>
								<s:if test='#session.usertype!=getText("USER")'>
									<tr>
										<td><s:text name="report.selectuser"/><font color="red">*</font></td>
										<td><s:select name="userLoginId" id="userLoginId" list="policyList1" headerKey="ALL" headerValue="-ALL-"  listKey="FIRST_NAME" listValue="USERNAME" cssClass="inputBoxS" /></td>
									</tr>									
								</s:if>
								<s:else>
									<s:hidden name="userLoginId" value="%{loginId}"></s:hidden>
								</s:else>
							</table>
							<br class="clear" />
							<div align="center">
								<s:hidden name="loginId"></s:hidden>					
								<s:submit type="submit" name="submit" key="Submit" onclick="forwardReport('PR')" cssClass="btn btn-sm btn-success"/>
							</div>
						</div>
					</s:if>
					<s:elseif test='menuType=="PR"'>
						<div class="row">
							<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
								<table class="display responsive no-wrap" id="record" width="100%" cellspacing="0">
									<thead>
									<tr>
										<th class="no-sort"></th>
										<s:if test='%{#session.product_id=="3" || #session.product_id=="11"}'>
											<th><s:text name="S.No." /></th>
											<th><s:text name="Policy Issue Date" /></th>
											<th><s:text name="Policy No" /></th>
											<th><s:text name="Inception Date" /></th>
											<th><s:text name="Debit Note No" /></th>
											<th><s:text name="Creditnote no" /></th>
											<th><s:text name="Insured Name" /></th>
											<th><s:text name="Carrier Name" /></th>
											<th><s:text name="Country Of Origin" /></th>
											<th><s:text name="Country Of Destination" /></th>
											<th><s:text name="Via" /></th>
											<th><s:text name="Form of Transport" /></th>
											<th><s:text name="Sum Insured(Local Currency)" /></th>
											<th><s:text name="Sum Insured(Foreign  Currency)" /></th>
											<th><s:text name="Currency Type" /></th>
											<th><s:text name="Basis of Valuation" /></th>
											<th><s:text name="Marine Premium" /></th>
											<th><s:text name="WSRCC Premium" /></th>
											<th><s:text name="Excess Premium" /></th>
											<th><s:text name="Issuance Fee" /></th>
											<th><s:text name="Inspection Fee" /></th>
											<th><s:text name="Total Premium" /></th>
											<th><s:text name="Commission" /></th>
											<th><s:text name="Goods Description" /></th>
											<th><s:text name="Excess Description" /></th>			
										</s:if>
										<s:else> <!-- #session.product_id=="30" -->
											<th><s:text name="S.No." /></th>							
											<th><s:text name="Insured Name" /></th>
											<th><s:text name="Policy No" /></th>
											<th><s:text name="Premium" /></th>
											<th><s:text name="Commission" /></th>
											<th><s:text name="Mode of Payment" /></th>
											<th><s:text name="Policy Issue Date" /></th>
											<th><s:text name="Policy Inception Date" /></th>
											<th><s:text name="Policy Expiry Date" /></th>
											<th><s:text name="Debit Note No" /></th>
											<th><s:text name="Receipt No" /></th>
										</s:else>
									</tr>
									</thead>
									<tbody>
									<s:iterator value="policyList" status="stat" var="record">
									<tr>
										<td></td>
										<s:if test='%{#session.product_id=="3" || #session.product_id=="11"}'>
											<td><s:property value="%{#stat.index+1}" /></td>
											<td><s:property value="POLICY_ISSUE_DATE" /></td>
											<td> <s:property value="POLICY_NO" /> </td>
											<td> <s:property value="INCEPTION_DATE" /> </td>
											<td> <s:property value="DEBIT_NOTE_NO" /> </td>
											<td> <s:property value="CREDIT_NOTE_NO" /> </td>
											<td> <s:property value="INSURED_NAME" /> </td>
											<td> <s:property value="CARRIER_NAME" /> </td>
											<td> <s:property value="ORIGIN_COUNTRY" /> </td>
											<td> <s:property value="DEST_COUNTRY" /> </td>
											<td> <s:property value="VIA" /> </td>
											<td> <s:property value="FORM_OF_TRANSPORT" /> </td>
											<td align="right"> <s:property value='getText(#format,{@java.lang.Double@valueOf(EQUIVALENT_DH)})'/> </td>
											<td align="right"> <s:property value='getText(#format,{@java.lang.Double@valueOf(EQUIVALENT_USD)})'/> </td>
											<td> <s:property value="CURRENCY_TYPE" /> </td>
											<td> 
												<s:property value="BASIS_OF_VALUATION" /> 
												<s:if test='%{!"NONE".equals(#stat.index.TOLERANCE_NAME)}'>
													+ <s:property value='%{#stat.index.TOLERANCE_NAME}'/>
												</s:if> 
											</td>
											<td align="right"> <s:property value='getText("number.format.2",{@java.lang.Double@valueOf(MARINE_PREMIUM)})' /> </td>
											<td align="right"> <s:property value='getText("number.format.2",{@java.lang.Double@valueOf(WSRCC_PREMIUM)})' /> </td>
											<td align="right"> <s:property value='getText("number.format.2",{@java.lang.Double@valueOf(EXCESS_PREMIUM)})' /> </td>
											<td align="right"> <s:property value='getText("number.format.2",{@java.lang.Double@valueOf(POLICY_FIXEDFEE)})' /> </td>
											<td align="right"> <s:property value='getText("number.format.2",{@java.lang.Double@valueOf(INSPECTION_FEE)})' /> </td>
											<td align="right"> <s:property value='getText(#format,{@java.lang.Double@valueOf(TOTAL_PREMIUM)})' /> </td>
											<td align="right"> <s:property value='getText("number.format.2",{@java.lang.Double@valueOf(COMMISSION)})' /> </td>
											<td align="right"> <s:property value='getText("number.format.2",{@java.lang.Double@valueOf(GOODS_DESCRIPTION)})' /> </td>
											<td align="right"> <s:property value='getText("number.format.2",{@java.lang.Double@valueOf(EXCESS_DESCRIPTION)})' /> </td>
											<td> <s:property value="user_name" /> </td>
										</s:if>
										<s:else> <!-- #session.product_id=="30" -->
											<td><s:property value="%{#stat.index+1}" /></td>
											<td> <s:property value="INSURED_NAME" /> </td>
											<td> <s:property value="POLICY_NO" /> </td>
											<td align="right"> <s:property value='getText(#format,{@java.lang.Double@valueOf(OVERALL_PREMIUM)})' /> </td>
											<td align="right"> <s:property value='getText(#format,{@java.lang.Double@valueOf(COMMISSION)})' /> </td>
											<td> <s:property value="PAYMENT_MODE" /> </td>
											<td> <s:property value="EFFECTIVE_DATE" /> </td>
											<td> <s:property value="INCEPTION_DATE" /> </td>
											<td> <s:property value="EXPIRY_DATE" /> </td>
											<td> <s:property value="DEBIT_NOTE_NO" /> </td>
											<td> <s:property value="RECEIPT_NO" /> </td>
										</s:else>
									</tr>
									</s:iterator>
									</tbody>
								</table>
							</div>
						</div>
						<br class="clear" />
						<div class="row">
							<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" align="center">
								<input type="button" onclick="exportdata('excel')" class="btn btn-sm btn-warning" value="Excel"/> &nbsp;&nbsp;&nbsp;
								<input type="button" onclick="exportdata('pdf')" class="btn btn-sm btn-info" value="Pdf"/> &nbsp;&nbsp;&nbsp;
								<input type="button" onclick="menuSelect('R')" class="btn btn-sm btn-danger" value="Back"/>
							</div>
						</div>
						<s:hidden name="policyStartDate"/>
						<s:hidden name="policyEndDate"/>
						<s:hidden name="userLoginId"/>
						<s:hidden name="downloadType" id="downloadType"/>
					</s:elseif>
					<s:elseif test='menuType=="ET"'>
						<div class="row">
							<div class="col-xs-12 col-sm-12 col-md-6 col-lg-4">
								<s:label key="endt.policyNo"/> : <span class="text-primary"><s:property value="%{policyNo}"/></span>
							</div>
							<div class="col-xs-12 col-sm-12 col-md-6 col-lg-4">
								<s:label key="endt.brokerName"/> : <span class="text-primary"><s:property value="%{brokerCompany}"/></span>
							</div>
							<div class="col-xs-12 col-sm-12 col-md-6 col-lg-4">
								<s:label key="endt.custName"/> : <span class="text-primary"><s:property value="%{custName}"/></span>
							</div>
							<br class="clear"/>
						</div>
						<div class="boxcontent">
							<s:actionerror cssStyle="color:red;"/>
						</div>
						<div class="panel panel-primary">
							<div class="panel-heading">
								<s:text name="Non-Financial Endorsement" />
							</div>
							<div class="panel-body">
								<s:set var="list" value="endType"></s:set>
						        <s:set var="#typelist" value="endTypeList"></s:set>
						        <div class="row">
									<s:set var="datacnt" value="0"/>
									<s:iterator value="#typelist" var="type" status="stat">
							        	<s:if test='%{"1"==#type.ENDT_TYPE_CATEGORY_ID}'>
							        		<div class="col-xs-12 col-sm-12 col-md-6 col-lg-4">
							        			<s:set var="datacnt" value="%{@java.lang.Integer@parseInt(#attr.datacnt)+1}"/>
												<s:checkbox name="endType" value="%{#type.ENDT_TYPE_ID in #list}" id="%{#type.ENDT_TYPE_ID}" fieldValue="%{#type.ENDT_TYPE_ID}" onclick="toggleConfirm();"/><s:property value="%{#type.ENDT_TYPE}" />
							        		</div>
							        	</s:if>
							        </s:iterator>
						        </div>
							</div>
						</div>
						<s:if test='(#session.usertype==getText("RSAIssuer") || #session.usertype==getText("BROKER"))'>
						<div class="panel panel-primary">
							<div class="panel-heading">
								<s:text name="Financial Endorsement" />
							</div>
							<div class="panel-body">			
						        <div class="row">
									<s:set var="datacnt" value="0"/>
									<s:iterator value="#typelist" var="type" status="stat">
							        	<s:if test='%{"2"==#type.ENDT_TYPE_CATEGORY_ID}'>
							        		<div class="col-xs-12 col-sm-12 col-md-6 col-lg-4">
							        			<s:set var="datacnt" value="%{@java.lang.Integer@parseInt(#attr.datacnt)+1}"/>
												<s:checkbox name="endType" value="%{#type.ENDT_TYPE_ID in #list}" id="%{#type.ENDT_TYPE_ID}" fieldValue="%{#type.ENDT_TYPE_ID}" onclick="toggleConfirm();"/><s:property value="%{#type.ENDT_TYPE}" />
							        		</div>
							        	</s:if>
							        </s:iterator>
						        </div>
							</div>
						</div>
						</s:if>
						<div class="row" id="toggle" style="display: none;">
							<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
								<div class="text">
									<s:text name=" Do you really want to cancel this policy?" />
								</div>
								<div class="tbox">
									<s:radio list="#{'Y':'Yes','N':'No'}" name="cancelYN"  />
								</div>
							</div>
							<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6" id="cancelRemarks">
								<div class="text">
									<s:text name="Cancellation Remarks" /> &nbsp; <font color="red">* </font>
								</div>
								<div class="tbox">
									<s:textarea  name="cancelRemarks" rows="2" cssClass="inputBoxA1" cols="50"  onkeyup="textLimit(this,500)" /> 
								</div>
							</div>
							<br class="clear" />
						</div>
						<br/>
						<div align="center">
							<s:submit type="button" name="close"  key="Back" cssClass="btn btn-sm btn-danger" onclick="forward('E')"/>&nbsp;&nbsp;&nbsp;
							<s:submit type="button" name="submit" key="Proceed" onclick="redirect('/endorsementReport.action?openCoverNo=%{openCoverNo}')" cssClass="btn btn-sm btn-success"/>
						</div>
					</s:elseif>
					<s:elseif test='menuType=="PVD"'>
			  			<s:if test='"Claim".equals(reqFrom) || "Endorse".equals(reqFrom)'>
			  				<div class="row">
				  				<div class="col-xs-12 col-md-4 col-lg-4 col-sm-4">
					  				<s:label key="endt.policyNo"/>
					  				<s:select list="policyNoList" cssClass="inputBoxS" listKey="POLICY_NO" listValue="POLICY_NO" name="policyNo"  headerKey="" headerValue="Select"  onchange="getMotorDetails(this.value,'%{reqFrom}')"/>
				  				</div>
				  			</div>
				  			<s:if test='policyNo!=null && policyNo!=""'>
				  				<div class="row">
									<div class="col-xs-12  "><font color="green"><s:property value="%{actionMsg}"/></font></div>
								</div>
					  			<div class="boxcontent">
									<table class="display responsive no-wrap" id="gridTable" width="100%" cellspacing="0">
											<thead>
												<tr>
													<th class="no-sort"></th>
													<th><s:text name="S.No." /></th>
													<s:if test='%{#session.product_id=="65"}'>
														<th><s:text name="Make" /></th>
														<th><s:text name="Model" /></th>
														<th><s:text name="Type of Body" /></th>
														<th><s:text name="Engine Number" /></th>
														<th><s:text name="Chasis Number" /></th>
														<th><s:text name="Registration Number" /></th>
														<th><s:text name="Sum Insured" /></th>
													</s:if>
													<s:else>
														<th><s:text name="Quote No"/></th>
														<th><s:text name="Policy Holder Name"/></th>
														<th><s:text name="Policy Date"/></th>
													</s:else>
													<th><s:text name="Premium" /></th>
													<s:if test='reqFrom=="Claim"'>
														<th><s:text name="Claim Request" /></th>										
													</s:if>
													<s:elseif test='reqFrom=="Endorse"'>
														<%--<th><s:text name="Non-Financial Endorsement" /></th>
														--%><th><s:text name="Endorsement Request" /></th>										
													</s:elseif>
												</tr>
											</thead>
											<tbody>
												<s:iterator value="endorseMentDetails" var="record1" status="status">
													<tr>
														<td></td>
														<td> ${status.count} </td>
														<s:if test='%{#session.product_id=="65"}'>
															<td> <s:property value="#record1.MAKE_NAME"/> </td>
															<td> <s:property value="#record1.MODEL_NAME"/> </td>
															<td> <s:property value="#record1.TYPE_OF_BODY_NAME"/> </td>
															<td> <s:property value="#record1.ENGINE_NUMBER"/> </td>
															<td> <s:property value="#record1.CHASSIS_NO"/> </td>
															<td> <s:property value="#record1.REGISTRATION_NO"/> </td>
															<td align="right"> <s:property value="#record1.SUMINSURED_VALUE_LOCAL"/> </td>
															<td align="right"> <s:property value="#record1.PREMIUM"/> </td>
														</s:if>
														<s:else>
															<td> <s:property value="#record1.QUOTE_NO"/> </td>
															<td> <s:property value="#record1.CUSTOMER_NAME"/> </td>
															<td> <s:property value="#record1.QUOTATION_DATE"/> </td>
															<td> <s:property value="#record1.OVERALL_PREMIUM"/> </td>
														</s:else>
														<s:if test='reqFrom=="Claim"'>
															<td align="center">
																<s:a href="#" cssClass="btn btn-sm btn-success" onclick="claim('%{#record1.VEHICLE_ID}','%{#session.product_id}','individual')" ><i class="glyphicon glyphicon-piggy-bank"></i></s:a>
															</td>										
														</s:if>
														<s:elseif test='reqFrom=="Endorse"'>
															<%--<td align="center">
																<s:a href="#" cssClass="btn btn-sm btn-warning" onclick="endorsement('E','%{#record1.QUOTE_NO}','%{policyNo}','%{#record1.CUSTOMER_NAME}','%{#record1.BROKER_NAME}','%{#record1.LOGIN_ID}','%{#record1.VEHICLE_ID}')"><i class="glyphicon glyphicon-book"></i></s:a>
															</td>
															--%><td align="center">
																<s:a href="#" cssClass="btn btn-sm btn-success" onclick="OtherEndorsement('%{#record1.VEHICLE_ID}','%{policyNo}','newReq')" ><i class="glyphicon glyphicon-piggy-bank"></i></s:a>
															</td>										
														</s:elseif>
													</tr>
												</s:iterator>
											</tbody>
										</table>
								</div>
				  			</s:if>
			  			</s:if>
			  			<s:else>
							<div class="row">
								<div class="col-xs-12  ">
									<s:label key="endt.policyNo"/> : <span class="text-primary"><s:property value="%{policyNo}"/></span>
									<br/><font color="green"><s:property value="%{actionMsg}"/></font>
								</div>
							</div>
				  			<div class="boxcontent">
								<table class="display responsive no-wrap" id="gridTable" width="100%" cellspacing="0">
									<thead>
										<tr>
											<th class="no-sort"></th>
											<th><s:text name="S.No." /></th>
											<th><s:text name="Make" /></th>
											<th><s:text name="Model" /></th>
											<th><s:text name="Type of Body" /></th>
											<th><s:text name="Sum Insured" /></th>
											<th><s:text name="Premium" /></th>
											<th><s:text name="Schedule" /></th>
											<%--<th><s:text name="Non-Financial Endorsement" /></th>
											<th><s:text name="Claim Request" /></th>
											<th><s:text name="Financial Endorsement Request" /></th>
										--%></tr>
									</thead>
									<tbody>
										<s:iterator value="multiVehicleDetails" var="record1" status="status">
											<tr>
												<td></td>
												<td> ${status.count} </td>
												<td> <s:property value="#record1.MAKE_NAME"/> </td>
												<td> <s:property value="#record1.MODEL_NAME"/> </td>
												<td> <s:property value="#record1.TYPE_OF_BODY_NAME"/> </td>
												<td align="right"> <s:property value="#record1.SUMINSURED_VALUE_LOCAL"/> </td>
												<td align="right"> <s:property value="#record1.PREMIUM"/> </td>
												<td align="center">
													<s:a href="#" cssClass="btn btn-sm btn-primary" onclick="return getFleetPdf('%{#record1.QUOTE_NO}','%{#record1.VEHICLE_ID}')"><i class="glyphicon glyphicon-book"></i></s:a>
												</td>
												<%--<td align="center">
													<s:a href="#" cssClass="btn btn-sm btn-warning" onclick="endorsement('E','%{#record1.QUOTE_NO}','%{policyNo}','%{#record1.CUSTOMER_NAME}','%{#record1.BROKER_NAME}','%{#record1.LOGIN_ID}','%{#record1.VEHICLE_ID}')"><i class="glyphicon glyphicon-book"></i></s:a>
												</td>
												<td align="center">
													<s:a href="#" cssClass="btn btn-sm btn-success" onclick="claim('%{#record1.VEHICLE_ID}','%{#session.product_id}','individual')" ><i class="glyphicon glyphicon-piggy-bank"></i></s:a>
												</td>
												<td align="center">
													<s:a href="#" cssClass="btn btn-sm btn-success" onclick="OtherEndorsement('%{#record1.VEHICLE_ID}','%{policyNo}','newReq')" ><i class="glyphicon glyphicon-piggy-bank"></i></s:a>
												</td>
											--%></tr>
										</s:iterator>
									</tbody>
								</table>
							</div>
							<br class="clear"/>
							 <div align="center">
								<s:submit type="button"  key="Back" cssClass="btn btn-sm btn-danger" onclick="forward('P')"/>
							</div>			  			
			  			</s:else>
			  		</s:elseif>
			  		<s:elseif test='menuType=="AP"'>
			  			<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
			  				<table class="display responsive no-wrap" id="gridTable" width="100%" cellspacing="0">
								<thead>
									<tr>
										<th class="no-sort"></th>
										<td><s:text name="S.No." /></td>
										<td><s:text name="Quote No." /></td>
										<td><s:text name="Policy Holder Name" /></td>
										<td><s:text name="Pending Status" /></td>
										<td><s:text name="Policy Start Date" /></td>
										<td><s:text name="Policy End Date" /></td>
										<td><s:text name="Premium" /></td>
										<td><s:text name="Draft" /></td>
									</tr>
								</thead>
								<tbody>
									<s:iterator value="homeApproverPendingList" var="record" status="status">
										<tr>
											<td></td>
											<td> <s:property value="#status.count"/> </td>
											<td> <s:property value="#record.QUOTE_NO"/> </td>
											<td> <s:property value="#record.CUSTOMER_NAME"/> </td>
											<td> <s:property value="#record.STATUS_DESC"/> </td>
											<td> <s:property value="#record.START_DATE"/> </td>
											<td> <s:property value="#record.END_DATE"/> </td>
											<td> <s:property value="#record.OVERALL_PREMIUM"/> </td>
											<td>
												<a href="#" type="button" class="btn btn-sm btn-primary" onClick='viewQuote(<s:property value="#record.QUOTE_NO" />)'><i class="glyphicon glyphicon-print"></i></a>
											</td>
										</tr>
									</s:iterator>
								</tbody>
							</table>
			  			</div>
			  		</s:elseif>
					<s:else>
						<s:if test='menuType=="E"'>
							<div class="row">
								<div class="col-xs-12 col-sm-12 col-md-6 col-lg-4">
									<s:label key="endt.policyNo"/> : <span style="color:blue;"><s:property value="%{policyNo}"/></span>
								</div>
								<div class="col-xs-12 col-sm-12 col-md-6 col-lg-4">
									<s:label key="endt.brokerName"/> : <span style="color:blue;"><s:property value="%{brokerCompany}"/></span>
								</div>
								<div class="col-xs-12 col-sm-12 col-md-6 col-lg-4">
									<s:label key="endt.custName"/> : <span style="color:blue;"><s:property value="%{custName}"/></span>
								</div>
								<br class="clear"/>
							</div>
						</s:if>
						<s:if test='menuType!="CHART"'>
							<div class="row">
								<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
									<table class="display responsive no-wrap" id="gridTable" width="100%" cellspacing="0">
										<thead>
										<tr>
											<th class="no-sort"></th>
											<th><s:text name="S.No." /></th>
											<s:if test='menuType!="T" && menuType!="PD" && menuType!="C"'>
												<th><s:text name="Quote No"/></th>
												<th><s:text name="Policy Holder Name"/></th>
												<s:if test='menuType!="L" && menuType!="RR" && menuType!="P" && menuType!="RP" && menuType!="BRP"'>
													<th><s:text name="Quote Date"/></th>
												</s:if>
												<s:if test='menuType=="RR"'>
													<th><s:text name="Rejected Date"/></th>
												</s:if>
												<s:elseif test='menuType=="P" || menuType=="BP"'>
													<th><s:text name="Policy Date"/></th>
												</s:elseif>
											</s:if>
											<s:if test='menuType=="P" || menuType=="PE"  || menuType=="PC" || menuType=="BP"'>
												<s:if test='%{#session.product_id=="3" || #session.product_id=="11"}'>
													<th><s:text name="Premium" /></th>
													<th><s:text name="Policy No" /></th>
													<th><s:text name="Schedule" /></th>
													<th><s:text name="Debit Note" /></th>
													<th><s:text name="Credit Note" /></th>
													<s:if test='#session.product_id=="3"'>
														<th><s:text name="Policy Wording" /></th>
													</s:if>
													<s:if test='menuType=="PC"'>
														<th><s:text name="Endorsement" /></th>
													</s:if>
													<s:else>
														<th><s:text name="Endorse" /></th>
														<th><s:text name="Documents" /></th>
													</s:else>
												</s:if>
												<s:else><!-- #session.product_id=="30" -->
													<th><s:text name="Premium" /></th>
													<th><s:text name="Policy No" /></th>
													<s:if test='%{("User".equalsIgnoreCase(#session.usertype))}'>
														<th><s:text name="Payment Detail" /></th>
													</s:if>
													<th><s:text name="Schedule" /></th>
													<th><s:text name="Debit Note" /></th>
													<th><s:text name="Receipt" /></th>
													<s:if test='%{"65".equalsIgnoreCase(#session.product_id) || "30".equalsIgnoreCase(#session.product_id)}'>
														<th><s:text name="Policy Wording" /></th>
													</s:if>
													<s:if test='%{"65".equalsIgnoreCase(#session.product_id)}'>
														<th style="background: #FF0000;"><s:text name="Certificate" /></th>
													</s:if>
													<s:elseif test='%{"33".equalsIgnoreCase(#session.product_id)}'>
														<th><s:text name="Correction" /></th>
														<th><s:text name="Cancel / ReIssue" /></th>
													</s:elseif>
													<s:else>
													    <%--<th><s:text name="Claim" /></th>
														<th><s:text name="Endorse" /></th>
														<th><s:text name="Endorsement" /></th>--%>
													</s:else>
												</s:else>	 
											</s:if>
											<s:elseif test='menuType=="QL"'>
												<th><s:text name="Validity Date" /></th>
												<th><s:text name="Active" /></th>
												<th><s:text name="Reject" /></th>
											</s:elseif>
											<s:elseif test='menuType=="QE" || menuType=="QS" || menuType=="BQE"'>
												<th><s:text name="Validity Date" /></th>
												<s:if test='menuType=="QE" || menuType=="BQE"'>
													<th><s:text name="Premium" /></th>
												</s:if>
												<s:if test='%{#session.product_id=="65"}'>
													<th><s:text name="Edit Quote" /></th>
													<th><s:text name="Quote to Policy" /></th>
												</s:if>
												<s:else>
													<th><s:text name="Edit" /></th>
												</s:else>
												<s:if test='menuType=="QE" || menuType=="BQE"'>
													<th><s:text name="Email" /></th>
													<th><s:text name="Print" /></th>
													<s:if test='%{#session.product_id=="65"}'>
														<th><s:text name="View Quote" /></th>
													</s:if>
												</s:if>
												<th><s:text name="Reject" /></th>					
											</s:elseif>
											<s:elseif test='menuType=="RP" || menuType=="BRP"'>
												<th><s:text name="Policy No" /></th>
												<th><s:text name="Policy Start Date" /></th>
												<th><s:text name="Policy Expiry Date" /></th>
												<th><s:text name="Premium" /></th>
												<th><s:text name="Renew" /></th>
												<th><s:text name="Schedule" /></th>
											</s:elseif>
											<s:elseif test='menuType=="RU" || menuType=="RA" || menuType=="BRU"'>
												<th><s:text name="Edit" /></th>							
												<th><s:text name="Print" /></th>
												<th><s:text name="Reject" /></th>
											</s:elseif>
											<s:elseif test='menuType=="RR"'>
												<th><s:text name="Remarks" /></th>
												<th><s:text name="Print" /></th>
											</s:elseif>
											<s:elseif test='menuType=="L"'>
												<th><s:text name="Quote Date" /></th>
												<th><s:text name="Rejected Date" /></th>
												<th><s:text name="Remarks" /></th>
												<th><s:text name="Print" /></th>
											</s:elseif>
											<s:elseif test='menuType=="T"'>
												<th><s:text name="Transaction Id" /></th>
												<th><s:text name="Valid Records" /></th>
												<th><s:text name="Invalid Records" /></th>
											</s:elseif>
											<s:elseif test='menuType=="PD"'>
												<th><s:text name="Policy No" /></th>
												<th><s:text name="OpenCover Customer Name" /></th>
												<th align="right"><s:text name="Premium" /></th>
												<th><s:text name="Total of Certificates" /></th>
												<th><s:text name="Schedule" /></th>
												<th><s:text name="Debit Note" /></th>
												<th><s:text name="Credit Note" /></th>
											</s:elseif>
											<s:elseif test='menuType=="C"'>
												<th><s:text name="Policy Holder Name" /></th>
												<th><s:text name="Address" /></th>
												<th><s:text name="Email Id" /></th>
												<th><s:text name="Contact No." /></th>
											</s:elseif>
											<s:elseif test='menuType=="E"'>
												<th><s:text name="Premium" /></th>
												<th><s:text name="Policy No" /></th>												
												<%---- <s:if test='#session.product_id=="3" || #session.product_id=="11"'> 
													<s:if test='"P".equals(gstatus)'>
														<th><s:text name="Schedule" /></th>
													</s:if>
												 	<s:if test='"P".equals(gstatus)'>
														<th><s:text name="Debit Note" /></th>
													</s:if>
													<s:if test='"P".equals(gstatus)'> 
														<th><s:text name="Credit Note" /></th>
													</s:if>
												</s:if>
												<s:else>
												  	<s:if test='"P".equals(gstatus)'>
														<th><s:text name="Schedule" /></th>
													</s:if>
												 	<s:if test='"P".equals(gstatus)'>
														<th><s:text name="Debit Note" /></th>
													</s:if>
													<s:if test='"P".equals(gstatus)'> 
														<th><s:text name="Credit Note" /></th>
													</s:if>
												</s:else>												
												<th><s:text name="Schedule" /></th>
												<th><s:text name="Debit Note" /></th>
												<th><s:text name="Credit Note" /></th>
												---%>
												<%--<s:if test='"P".equals(gstatus)'>
													<th><s:text name="Schedule" /></th>
												</s:if>--%>
												<th><s:text name="Status" /></th>
												<th><s:text name="Endorsement" /></th>
												<th><s:text name="Debit Note" /></th>
												<th><s:text name="Credit Note" /></th>
											</s:elseif>
										</tr>
										</thead>
										<tbody>
										<s:iterator value="gridReport" status="stat" var="report">
										<tr valign="middle">
											<td></td>
											<td><s:property value="%{#stat.index+1}"/></td>
											<s:if test='menuType!="T" && menuType!="PD" && menuType!="C"'>
												<td><s:property value="gquoteNo"/></td>
												<td><s:property value="gcustName"/></td>
												<s:if test='menuType!="L" && menuType!="RR" && menuType!="P"  && menuType!="RP" && menuType!="BRP"'>
													<td><s:property value="gquoteDate"/></td>
												</s:if>
												<s:if test='menuType=="RR"'>
													<td><s:property value="gquoteDate"/></td>
												</s:if>
												<s:elseif test='menuType=="P" || menuType=="BP"'>
													<td><s:property value="gquoteDate"/></td>
												</s:elseif>
											</s:if>
											<s:if test='menuType=="P" || menuType=="PE"  || menuType=="PC" || menuType=="BP"'>
												<s:if test='%{#session.product_id=="3" || #session.product_id=="11"}'>
													<td align="right"><s:property value='getText(#format,{@java.lang.Double@valueOf(gpremium)})' /></td>
													<td> <s:property value="gpolicyNo" /> </td>
													<td align="center">
														<a href="#" type="button" class="btn btn-sm btn-primary" onClick="getSchedule('<s:property value="gschedule" />')"><i class="glyphicon glyphicon-book"></i></a> 
													</td>
													<td align="center">
														<s:if test='!"".equals(gdebit)'>
															<a href="#" type="button" class="btn btn-sm btn-warning" onClick="getDebit('<s:property value="gdebit" />')"><i class="glyphicon glyphicon-book"></i></a>
														</s:if> 
													</td>
												</s:if>
												<s:else><!-- #session.product_id=="30" -->
													<td align="right"><s:property value='getText(#format,{@java.lang.Double@valueOf(goverAllPremium)})' /></td>
													<td> <s:property value="gpolicyNo" /> </td>
													<s:if test='%{("User".equalsIgnoreCase(#session.usertype))}'>
														<s:if test='"Y".equals(ginstallmentYN)'>
															<td align="center">
																<a href="#" type="button" class="btn btn-sm btn-default" onClick="fnView('<s:property value="gquoteNo" />')"><i class="glyphicon glyphicon-book"></i></a> 
															</td>
														</s:if>
														<s:else>
															<td align="center">
																<a href="#" type="button" class="btn btn-sm btn-default" onClick="fnView1('<s:property value="gquoteNo" />','<s:property value="gmerchent" />')"><i class="glyphicon glyphicon-book"></i></a> 
															</td>
														</s:else>
													</s:if>
													<td align="center">
														<a href="#" type="button" class="btn btn-sm btn-primary" onClick="getSchedule('<s:property value="gschedule" />')"><i class="glyphicon glyphicon-book"></i></a> 
													</td>
													<td align="center">
														<s:if test='!"".equals(gdebit)'>
															<a href="#" type="button" class="btn btn-sm btn-warning" onClick="getDebit('<s:property value="gdebit" />')"><i class="glyphicon glyphicon-book"></i></a>
														</s:if>
													</td>
													<td align="center">
														<s:if test='!"".equals(greceipt)'>
															<a href="#" type="button" class="btn btn-sm btn-info" onClick="getReceiptPdf('<s:property value="gquoteNo" />')"><i class="glyphicon glyphicon-book"></i></a>
														</s:if> 
													</td>
													<s:if test='%{#session.product_id=="65" ||#session.product_id=="30"}'>
														<td align="center">
															<a href="#" type="button" class="btn btn-sm btn-danger" onclick="getPolicyWordingPdf('${report.gquoteNo}')"><i class="glyphicon glyphicon-book"></i></a>
														</td>
													</s:if>
													<s:if test='%{#session.product_id=="65"}'>
														<td align="center">
															<a href="#" type="button" class="btn btn-sm btn-default" onClick="motorVehicleDetails('${report.gpolicyNo}')"><i class="glyphicon glyphicon glyphicon-list-alt"></i></a>
														</td>
														
													</s:if>
													<s:elseif test='%{#session.product_id=="33"}'>
													<td align="center">
														<a href="#" type="button" class="btn btn-sm btn-primary" onClick="viewCorrection('${report.gquoteNo}','${report.gapplicationNo}')"><i class="glyphicon glyphicon-pencil"></i></a>
													</td>
													<td align="center">
														<a href="#" type="button" class="btn btn-sm btn-default" onClick="viewPopUp('${pageContext.request.contextPath}/cancelReissueTravel.action?quoteNo=${report.gquoteNo}&applicationNo=${report.gapplicationNo}&policyNo=${report.gpolicyNo}&product_id=#session.product_id &branch_code=#session.branch_code')"><i class="glyphicon glyphicon-book"></i></a>
													</td>
													</s:elseif>
													<s:else>
													    <%--<td align="center">
												       		<s:a href="#"   cssClass="btn btn-sm btn-success" onclick="claim('%{gpolicyNo}','%{#session.product_id}','individual')" ><i class="glyphicon glyphicon-piggy-bank"></i></s:a>
											            </td>
														<td  align="center">															
														<s:a href="#"   cssClass="btn btn-sm btn-success" onclick="OtherEndorsement('','%{gpolicyNo}','newReq')"><i class="glyphicon glyphicon-piggy-bank"></i></s:a>
														</td>
														<td align="center">
															<s:if test='!"".equals(gendorse)'>
															<a href="#" type="button" class="btn btn-sm btn-warning" onClick="endorsement(<s:property value="gendorse" />)"><i class="glyphicon glyphicon-book"></i></a>		
															</s:if>
														</td >--%>
													</s:else>
												</s:else>
												
												
												
												
												<s:if test='%{#session.product_id=="3" || #session.product_id=="11"}'>
													<td align="center">
														<s:if test='!"".equals(gcredit)'>
															<a href="#" type="button" class="1btn btn-sm btn-warning" onClick="getCredit('<s:property value="loginId" />','<s:property value="gcredit" />')"><i class="glyphicon glyphicon-book"></i></a>
														</s:if>
													</td>
													<s:if test='#session.product_id=="3"'>
														<td>
															<a href='#' onClick="popUp('${pageContext.request.contextPath}/Copyofinformation.jsp?policyMode=clauses&policynumber=${gpolicyWording}',1000,500)"><i class="glyphicon glyphicon-book"></i></a>
														</td>
													</s:if>
													<s:if test='menuType!="PC"'>
														<td align="center">
															<s:if test='!"".equals(gendorse)'>
																<a href="#" type="button" class="btn btn-sm btn-warning" onClick="endorsement(<s:property value="gendorse" />)"><i class="glyphicon glyphicon-book"></i></a>
															</s:if>
														</td>
														<td align="center">
															<s:if test='!"".equals(glcupload)'>
																<a href="#" type="button" class="btn btn-sm btn-default" onClick="lcupload(<s:property value="glcupload" />)"><i class="glyphicon glyphicon-book"></i></a>
															</s:if>
														</td>
													</s:if>	
												</s:if>
												<s:if test='menuType=="PC"'>
													<td align="center">
														${gendtSchedule}
													</td>
												</s:if>												 
											</s:if>
											<s:elseif test='menuType=="QL"'>
												<td> <s:property value="gvalidityDate" /> </td>
												<td align="center">
													<a href="#" type="button" class="btn btn-sm btn-success" onClick="sentEMail(<s:property value="gactive" />)"><i class="glyphicon glyphicon-ok-sign"></i></a>
												</td>
												<td align="center">
													<a href="#" type="button" class="btn btn-sm btn-danger" onClick="sentEMail(<s:property value="gdeactive" />)"><i class="glyphicon glyphicon-remove-sign"></i></a>
												</td>
											</s:elseif>
											<s:elseif test='menuType=="QE" || menuType=="QS" || menuType=="BQE"'>
												<td> <s:property value="gvalidityDate" /> </td>
												<s:if test='menuType=="QE" || menuType=="BQE"'>
													<td align="right"> <s:property value='getText(#format,{@java.lang.Double@valueOf(gpremium)})' /> </td>
												</s:if>
												<s:if test='%{#session.product_id=="65"}'>
													<td align="center">
														<a href="#" type="button" class="btn btn-sm btn-warning" onClick="editQuoteNew(<s:property value="gedit" />)"><i class="glyphicon glyphicon-pencil"></i></a>
													</td>
													<td align="center">
														<a href="#" type="button" class="btn btn-sm btn-warning" onClick="editPolicy(<s:property value="gedit" />)"><i class="glyphicon glyphicon-pencil"></i></a>
													</td>
												</s:if>
												<s:else>
													<td align="center">
														<a href="#" type="button" class="btn btn-sm btn-warning" onClick="editQuote(<s:property value="gedit" />)"><i class="glyphicon glyphicon-pencil"></i></a>
													</td>
												</s:else>
												<s:if test='menuType=="QE" || menuType=="BQE"'>
													<td align="center">
														<a href="#" type="button" class="btn btn-sm btn-default" onClick="sentEMail(<s:property value="gedit" />)"><i class="glyphicon glyphicon-envelope"></i></a>
													</td>
													<td align="center">
														<a href="#" type="button" class="btn btn-sm btn-primary" onClick="viewQuote(<s:property value="gquoteNo" />)"><i class="glyphicon glyphicon-print"></i></a>
													</td>
													<s:if test='%{#session.product_id=="65"}'>
														<td align="center">
															<a href="#" type="button" class="btn btn-sm btn-info" onClick="viewQuoteDtl(<s:property value="gquoteNo" />,'${report.gapplicationNo}')"><i class="glyphicon glyphicon-eye-open"></i></a>
														</td>
													</s:if>
												</s:if>
												<td align="center">
													<a href="#" type="button" class="btn btn-sm btn-danger" onClick="sentEMail(<s:property value="greject" />)"><i class="glyphicon glyphicon-remove-sign"></i></a>
												</td>
											</s:elseif>
											<s:elseif test='menuType=="RP"  || menuType=="BRP"'>
												<td> <s:property value="gpolicyNo" /> </td>
												<td> <s:property value="policyStartDate" /> </td>
												<td> <s:property value="policyEndDate" /> </td>
												<td> <s:property value="goverAllPremium" /> </td>
												<td align="center">
													<a href="#" type="button" class="btn btn-sm btn-info" onClick="getRenew('<s:property value="gquoteNo" />')"><i class="glyphicon glyphicon-book"></i></a> 
												</td>
												<td align="center">
													<a href="#" type="button" class="btn btn-sm btn-primary" onClick="getSchedule('<s:property value="gschedule" />')"><i class="glyphicon glyphicon-book"></i></a> 
												</td>
											</s:elseif>
											<s:elseif test='menuType=="RU" || menuType=="RA" || menuType=="BRU"'>
												<td align="center"><a type="button" class="btn btn-sm btn-warning" href="#" onClick="editQuote(<s:property value="gedit" />)"><i class="glyphicon glyphicon-pencil" ></i></a></td>
												<td align="center">
													<a href="#" type="button" class="btn btn-sm btn-primary" onClick="viewQuote(<s:property value="gquoteNo" />)"><i class="glyphicon glyphicon-print"></i></a>
												</td>
												<td align="center">
													<a href="#" type="button" class="btn btn-sm btn-danger" onClick="sentEMail(<s:property value="greject1" />)"><i class="glyphicon glyphicon-remove-sign"></i></a>
												</td>
											</s:elseif>
											<s:elseif test='menuType=="RR"'>
												<td> <s:property value="glapsedRemark" /> </td>
												<td align="center">
													<a href="#" type="button" class="btn btn-sm btn-primary" onClick="viewQuote(<s:property value="gquoteNo" />)"><i class="glyphicon glyphicon-print"></i></a>
												</td>
											</s:elseif>
											<s:elseif test='menuType=="L"'>
												<td> <s:property value="gquoteDate" /> </td>
												<td> <s:property value="glapsedDate" /> </td>
												<td> <s:property value="glapsedRemark" /> </td>
												<td align="center">
													<a href="#" type="button" class="btn btn-sm btn-primary" onClick="viewQuote(<s:property value="gquoteNo" />)"><i class="glyphicon glyphicon-print"></i></a>
												</td>
											</s:elseif>
											<s:elseif test='menuType=="T"'>
												<td> <s:property value="gtransactionId" /> </td>
												<td> <s:property value="gtransactionId1" /> </td>
												<td> <s:property value="ginvalidRecords" /> </td>
											</s:elseif>
											<s:elseif test='menuType=="PD"'>
												<td> <s:property value="gpolicyNo" /> </td>
												<td> <s:property value="gocCustName" /> </td>
												<td> <s:property value="gpremium" /> </td>
												<td> <s:property value="gtotalCert" /> </td>
												<td align="center">
													<a href="#" type="button" class="btn btn-sm btn-primary" onClick="getMultiSchedule(<s:property value="gschedule" />)"><i class="glyphicon glyphicon-book"></i></a> 
												</td>
												<td align="center">
													<a href="#" type="button" class="btn btn-sm btn-warning" onClick="getDebit2(<s:property value="gdebit" />)"><i class="glyphicon glyphicon-book"></i></a> 
												</td>
												<td align="center">
													<a href="#" type="button" class="btn btn-sm btn-warning" onClick="getCredit(<s:property value="loginId" />,<s:property value="gcredit" />)"><i class="glyphicon glyphicon-book"></i></a>
												</td>
											</s:elseif>
											<s:elseif test='menuType=="C"'>
												<td> <s:property value="gfirstName" /> </td>
												<td> <s:property value="gaddress" /> </td>
												<td> <s:property value="gemail" /> </td>
												<td> <s:property value="gmobile" /> </td>
											</s:elseif>
											<s:elseif test='menuType=="E"'>
												 <td> <s:property value="gpremium" /> </td>
												 <s:if test='"P".equals(gstatus)'>
												 	<td> <s:property value="gpolicyNo" /> </td>
												 </s:if>
												 <s:else>
												 		<td>&nbsp;</td>
												 </s:else>
												<%--- <s:if test='#session.product_id=="3" || #session.product_id=="11"'>
												 	<td align="center">
												 		<s:if test='"P".equals(gstatus)'>
												 			<a href="#" type="button" class="btn btn-sm btn-primary" onClick="getSchedule(<s:property value="gschedule" />)"><i class="glyphicon glyphicon-book"></i></a>
												 		</s:if>
												 		<s:else> &nbsp; </s:else>
												 	</td>
												 	<td align="center">
												 		<s:if test='%{"P".equals(gstatus)}'>
															<a href="#" type="button" class="btn btn-sm btn-warning" onClick="getDebit2(<s:property value="gdebit" />)"><i class="glyphicon glyphicon-book"></i></a>
														</s:if>
														<s:else> &nbsp; </s:else> 
													</td>												 	
												 	<td align="center">
												 		<s:if test='%{"P".equals(gstatus)}'>
															<a href="#" type="button" class="btn btn-sm btn-warning" onClick="getCredit(<s:property value="loginId" />,<s:property value="gcredit" />)"><i class="glyphicon glyphicon-book"></i></a>
														</s:if>
													 	<s:else>
													 		&nbsp;
													 	</s:else>
													</td>
												 </s:if>
												 <s:else>												 	
												 	<td align="center">
												 		<s:if test='"P".equals(gstatus)'>
															<a href="#" type="button" class="btn btn-sm btn-primary" onClick="getSchedule1(<s:property value="gquoteNo" />)"><i class="glyphicon glyphicon-book"></i></a>
														</s:if>
													 	<s:else>
													 		&nbsp;
													 	</s:else> 
													</td>												 	
												 	<td align="center">
												 		<s:if test='%{"P".equals(gstatus)}'>
															<a href="#" type="button" class="btn btn-sm btn-warning" onClick="getDebit1(<s:property value="gdebit1" />)"><i class="glyphicon glyphicon-book"></i></a>
														</s:if>
													 	<s:else>
													 		&nbsp;
													 	</s:else> 
													</td>												 	
												 		<td align="center">
														<s:if test='%{"P".equals(gstatus)}'>
															<a href="#" type="button" class="btn btn-sm btn-warning" onClick="getCredit(<s:property value="loginId" />,<s:property value="gcredit" />)"><i class="glyphicon glyphicon-book"></i></a>
														</s:if>
													 	<s:else>
													 		&nbsp;
													 	</s:else>
													</td>
												 </s:else> --%>
												 <td> <s:property value="grefSatus" /> </td>												 
												  <td align="center"> ${gendtSchedule} </td>												  
												<td align="center">
													<s:if test='!"".equalsIgnoreCase(gdebit)'>
														<a href="#" type="button" class="btn btn-sm btn-warning" onClick="getDebit2(<s:property value="gdebit" />)"><i class="glyphicon glyphicon-book"></i></a>
													</s:if>
													<s:else>&nbsp;</s:else>													 
												</td>
												<td align="center">
													<s:if test='!"".equalsIgnoreCase(gcredit)'>
														<a href="#" type="button" class="btn btn-sm btn-warning" onClick="getCredit('<s:property value="loginId" />','<s:property value="gcredit" />')"><i class="glyphicon glyphicon-book"></i></a>
													</s:if>
													<s:else>&nbsp;</s:else>
												</td>													
											</s:elseif>					
										</tr>
										</s:iterator>
										</tbody>
									</table>
								</div>								
							</div>
						</s:if>
						<br class="clear" />
						<div class="row" align="center">
							<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
								<s:if test='menuType=="P" || menuType=="PE" || menuType=="PC" || menuType=="BP"'>
									<s:if test='#session.product_id!=getText("OPEN_COVER")'> </s:if>
									<s:else>
										<s:submit type="button" name="close"  key="Back" cssClass="btn btn-sm btn-danger" onclick="redirect('/ViewOpenCovers.jsp')"/>&nbsp;&nbsp;&nbsp;
									</s:else>
								</s:if>
								<s:if test='menuType=="E"'>
									<s:if test='%{#session.product_id=="65"}'>
										<s:submit type="button" name="close"  key="Back" cssClass="btn btn-sm btn-danger" onclick="motorVehicleDetails('%{policyNo}')"/>&nbsp;&nbsp;&nbsp;
									</s:if>
									<s:else>
										<s:submit type="button" name="close"  key="Back" cssClass="btn btn-sm btn-danger" onclick="forward('P')"/>&nbsp;&nbsp;&nbsp;
									</s:else>
									<s:if test='%{((issuer !=null && issuer.length() > 0) || #session.usertype==getText("BROKER") || "User".equalsIgnoreCase(#session.usertype)) && (endtStatus==null || endtStatus=="")}'>
										<s:submit type="button" name="new"  key="Create New" cssClass="btn btn-sm btn-primary" onclick="endorsementTypeNew('ET','N')"/>&nbsp;&nbsp;&nbsp;
									</s:if>
								 <s:elseif test='%{((issuer !=null && issuer.length() > 0) || #session.usertype==getText("BROKER")) && endtStatus=="N"}'>
										<a href="#" onclick="endorsementType('ET','P','','${quotationNo}','${referalStatus}','${appNo}')"><s:submit type="button" name="new"  key="Create New" cssClass="btn btn-sm btn-primary" /></a>
									</s:elseif>  
								</s:if>
								<s:if test='menuType=="D"'>
									<s:submit type="button" name="close"  key="Back" cssClass="btn btn-sm btn-danger" onclick="forward('T')"/>&nbsp;&nbsp;&nbsp;
									<s:submit type="submit" name="submit" key="Submit" onclick="forward()" cssClass="btn btn-sm btn-success"/>
								</s:if>
							</div>							
						</div>		
					</s:else>
				</div>
			</div>
		</s:if>
		<s:if test='%{menuType=="CHART" && #session.LoginType=="B2C"}'>
			<script language="JavaScript" type="text/javascript">
				newQuote('<s:property value="%{#session.product_id}"/>');
			</script>
		</s:if>
		<s:elseif test='menuType=="CHART"'>
		<div class="panel panel-primary">			
			<div class="panel-body">
				<div class="row">
					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
						<%  String product_id = (String)session.getAttribute("product_id");
			     			String login_id = (String)session.getAttribute("BROKER_LOGIN_ID");
			      			String issuer=(String)session.getAttribute("RSAISSUER")==null?"":(String)session.getAttribute("RSAISSUER");				
							String openCoverNo=(String)session.getAttribute("openCoverNo")==null?"":(String)session.getAttribute("openCoverNo");
							String schemeId = (String)session.getAttribute("scheme_id")==null?"":(String)session.getAttribute("scheme_id");
							String loginBranch=(String)session.getAttribute("selectedBranch")==null?"":(String)session.getAttribute("selectedBranch");
			      			if(login_id==null){
			      				login_id = (String)session.getAttribute("user");
			      			}
							final ReportService service=new ReportService();
							String originalOC=service.getOriginalPolicyNo(openCoverNo);
							int[] exist=service.getTRExisting(login_id, product_id,issuer,originalOC,schemeId,loginBranch);
							int[] portfo=service.getTRPortfolio(login_id, product_id,issuer,originalOC,schemeId,loginBranch);
							int[] laps=service.getTRLapsed(login_id, product_id,issuer,originalOC,schemeId,loginBranch);
							int[] unapprove=service.getTRUnapproved(login_id, product_id,issuer,originalOC,schemeId,loginBranch);
							int[] approve=service.getTRApproved(login_id, product_id,issuer,originalOC,schemeId,loginBranch);
							int[] rej=service.getTRReject(login_id, product_id,issuer,originalOC,schemeId,loginBranch);
						 %>						
						<script language="JavaScript" type="text/javascript">
							$(function () {
							        $('#container').highcharts({
							    
							            chart: {
							                type: 'column'
							            },
							    
							            title: {
							                text: 'Last One Month Reports'
							            },
							    
							            xAxis: {
							                categories: ['Existing + Lapsed', 'Portfolio', 'UnApproved + Approved + Rejected']
							            },
							    
							            yAxis: {
							                allowDecimals: false,
							                min: 0,
							                title: {
							                    text: 'Number of Quotations'
							                }
							            },
							    
							            tooltip: {
							                formatter: function() {
							                    return '<b>'+ this.x +'</b><br/>'+
							                        this.series.name +': '+ this.y +'<br/>'+
							                        'Total: '+ this.point.stackTotal;
							                }
							            },
							    
							            plotOptions: {
							                column: {
							                    stacking: 'normal'
							                }
							            },
							    
							            series: [{
							                name: 'Existing',
							                data: [<%= exist[0] %>, 0, 0],
							                stack: 'Existing + Lapsed'
							            }, {
							                name: 'Lapsed',
							                data: [ <%= laps[0] %>, 0, 0],
							                stack: 'Existing + Lapsed'
							            },  {
							                name: 'Portfolio',
							                data: [0, <%= portfo[0] %>,0],
							                stack: 'Portfolio'
							            }, {
							                name: 'UnApproved',
							                data: [0, 0, <%= unapprove[0] %>],
							                stack: 'UnApproved + Approved + Rejected'
							            }, 
							            {
							                name: 'Approved',
							                data: [0, 0, <%= approve[0] %>],
							                stack: 'UnApproved + Approved + Rejected'
							            }, {
							                name: 'Rejected',
							                data: [0,0, <%= rej[0] %>],
							                stack: 'UnApproved + Approved + Rejected'
							            }]
							        });
							    });
						    
						 $(document).ready(function(){
					        var colors = Highcharts.getOptions().colors,
					            categories = ['Existing', 'Lapsed', 'Portfolio','Approved'],
					            name='Total Revenue',
					            data = [{
					                    y: <%= exist[1] %>,
					                    color: colors[0]
					                }, {
					                    y: <%= laps[1] %>,
					                    color: colors[1]
					                }, {
					                    y: <%= portfo[1] %>,
					                    color: colors[2]
					                }, {
					                    y: <%=approve[1] %>,
					                    color: colors[4]
					                }];
							    var chart = $('#container2').highcharts({
						            chart: {
						                type: 'column'
						            },
						            title: {
						                text: 'Last One Month Reports'
						            },
						            xAxis: {
						                categories: categories
						            },
						            yAxis: {
						                title: {
						                    text: 'Total Revenue'
						                }
						            },
						            plotOptions: {
						                column: {
						                    cursor: 'pointer',
						                    dataLabels: {
						                        enabled: true,
						                        color: colors[0],
						                        style: {
						                            fontWeight: 'bold'
						                        }
						                    }
						                }
						            },
						            tooltip: {
						                formatter: function() {
						                    var point = this.point,
						                        s = this.x +':<b>'+ this.y+' <b><s:property value="#session.BrokerDetails.CurrencyAbb"/>';
						                    return s;
						                }
						            },
						            series: [{
						                name: name,
						                data: data,
						                color: 'white'
						            }],
						            exporting: {
						                enabled: false
						            }
						        })
						    });
						
						</script>
						<s:div id="container" cssStyle="width: 100%; height: 400px; margin: 0 auto" />
						<s:div id="container2" cssStyle="width: 100%; height: 400px; margin: 0 auto" />
					</div>
				</div>				
			</div>
		</div>
	</s:elseif>
	</div>
</div>
<s:hidden name="mode" ></s:hidden>
<s:if test='menuType!="P"'>
<s:hidden name="quoteNo" ></s:hidden>
</s:if>
<s:if test='menuType!="PVD"'>
	<s:hidden name="policyNo"></s:hidden>
</s:if>
<s:hidden name="quoteStatus" ></s:hidden>
<s:hidden name="tranId" ></s:hidden>
<s:hidden name="menuType"></s:hidden> 
<s:hidden name="applicationNo" ></s:hidden>
<s:hidden name="linkType" ></s:hidden>
<s:hidden name="endTypeId" ></s:hidden>
<s:hidden name="endStatus" ></s:hidden>
<s:hidden name="custName" ></s:hidden>
<s:hidden name="brokerCompany" ></s:hidden>
<s:hidden name="searchFrom" ></s:hidden>
<s:hidden name="customerId" ></s:hidden>
<s:hidden name="searchFrom" ></s:hidden>
<s:hidden name="display" ></s:hidden>
<s:hidden name="reqFrm" ></s:hidden>
<s:hidden name="reqFrom" ></s:hidden>
<s:hidden name="selRow" id="selRow"/>
<s:hidden name="endtLoginId"/>
<s:hidden name="contentTypeId"/>
<s:hidden name="vehicleId"/>
<s:hidden name="requireType"/>
<s:hidden name="copyQuoteValue"/>
</s:form>
</body>
<script type="text/javascript">
<s:if test='"ET".equals(menuType)' >
toggleConfirm();
</s:if>
function getSchedule(val) {
	var URL ='${pageContext.request.contextPath}/Copyofinformation.jsp?policyMode=schedule&policynumber='+val;
	var bwidth = window.innerWidth;
	var bwidth1 = document.body.clientWidth;
	if(bwidth <= 768) {
		return popUp(URL,bwidth1,'500');
	} else {
		return popUp(URL,'750','500');
	}
}
function getDebit(val) {
	var URL ='${pageContext.request.contextPath}/Copyofinformation.jsp?policyMode=debit&policynumber='+val;
	var bwidth = window.innerWidth;
	var bwidth1 = document.body.clientWidth;
	if(bwidth <= 768) {
		return popUp(URL,bwidth1,'500');
	} else {
		return popUp(URL,'750','500');
	}
}
function getReceiptPdf(val) {
	var URL ='${pageContext.request.contextPath}/receiptReport.action?quoteNo='+val;
	var bwidth = window.innerWidth;
	var bwidth1 = document.body.clientWidth;
	if(bwidth <= 768) {
		return popUp(URL,bwidth1,'500');
	} else {
		return popUp(URL,'750','500');
	}
}
function getFleetPdf(quoteNo, vehicleId) {
	var URL ='${pageContext.request.contextPath}/motorFleetScheduleReport.action?quoteNo='+quoteNo+'&vehicleId='+vehicleId;
	var bwidth = window.innerWidth;
	var bwidth1 = document.body.clientWidth;
	if(bwidth <= 768) {
		return popUp(URL,bwidth1,'500');
	} else {
		return popUp(URL,'750','500');
	}
}
function getSchedule1(val) {
	var URL ='${pageContext.request.contextPath}/PdfSummary_Schedule.Travel?quoteNo='+val;
	var bwidth = window.innerWidth;
	var bwidth1 = document.body.clientWidth;
	if(bwidth <= 768) {
		return popUp(URL,bwidth1,'500');
	} else {
		return popUp(URL,'750','500');
	}
}
function getDebit1(val) {
	var URL ='${pageContext.request.contextPath}/PdfSummary_Debit.Travel?quoteNo='+val;
	var bwidth = window.innerWidth;
	var bwidth1 = document.body.clientWidth;
	if(bwidth <= 768) {
		return popUp(URL,bwidth1,'500');
	} else {
		return popUp(URL,'750','500');
	}
}
function getRenew(quoteNo) {
	document.report.copyQuoteValue.value = quoteNo;
	document.report.action = '${pageContext.request.contextPath}/renewCopyQuote.action';
	document.report.submit();
}
function getDebit2(val) {
	var URL ='${pageContext.request.contextPath}/Copyofinformation.jsp?policyMode=debitMultiple&policynumber='+val;
	var bwidth = window.innerWidth;
	var bwidth1 = document.body.clientWidth;
	if(bwidth <= 768) {
		return popUp(URL,bwidth1,'500');
	} else {
		return popUp(URL,'750','500');
	}
}
function getReceipt(val) {
	var URL ='${pageContext.request.contextPath}/PdfSummary_Receipt.Travel?quoteNo='+val;
	var bwidth = window.innerWidth;
	var bwidth1 = document.body.clientWidth;
	if(bwidth <= 768) {
		return popUp(URL,bwidth1,'500');
	} else {
		return popUp(URL,'750','500');
	}
}
function getCredit(loginId,val) {
	var URL ='Copyofinformation.jsp?policyMode=credit&loginid='+loginId+'&policynumber='+val;
	var bwidth = window.innerWidth;
	var bwidth1 = document.body.clientWidth;
	if(bwidth <= 768) {
		return popUp(URL,bwidth1,'500');
	} else {
		return popUp(URL,'750','500');
	}
}
function getPrint1(val) {
	var URL ='${pageContext.request.contextPath}/PdfSummary_Draft.Travel?quoteNo='+val;
	var bwidth = window.innerWidth;
	var bwidth1 = document.body.clientWidth;
	if(bwidth <= 768) {
		return popUp(URL,bwidth1,'500');
	} else {
		return popUp(URL,'750','500');
	}
}
function getMultiSchedule(val) {
	var URL ='${pageContext.request.contextPath}/Copyofinformation.jsp?policyMode=scheduleMultiple&policynumber='+val;
	var bwidth = window.innerWidth;
	var bwidth1 = document.body.clientWidth;
	if(bwidth <= 768) {
		return popUp(URL,bwidth1,'500');
	} else {
		return popUp(URL,'750','500');
	}
}
function exportdata(val) {
	document.report.downloadType.value=val;	
	document.report.action='${pageContext.request.contextPath}/reportReport.action';	
	document.report.submit();
}
function editQuote(applicationNo,quoteNo, status,customerId, contentTypeId) { 
	document.report.quoteNo.value=quoteNo;
	document.report.quoteStatus.value=status;
	document.report.applicationNo.value=applicationNo;
	if(30=='<s:property value="#session.product_id"/>') {
		document.report.contentTypeId.value = contentTypeId;
		if(status!='RA') {
			document.report.display.value='getQuote';
		}
		//document.report.action = "${pageContext.request.contextPath}/initHome.action";
		document.report.action = "${pageContext.request.contextPath}/packageSelectionHome.action";
	} else if(65=='<s:property value="#session.product_id"/>') {
		document.report.action = "${pageContext.request.contextPath}/editQuoteMotor.action";
	} else if(3=='<s:property value="#session.product_id"/>' || 11=='<s:property value="#session.product_id"/>') {
		document.report.action = "${pageContext.request.contextPath}/editQuoteQuotation.action";
	}else if(33 == '<s:property value="#session.product_id"/>')
		document.report.action = "${pageContext.request.contextPath}/initTravel.action";
	document.report.submit();
}
function editQuoteNew(applicationNo,quoteNo, status,customerId, contentTypeId) { 
	document.report.quoteNo.value=quoteNo;
	document.report.quoteStatus.value=status;
	document.report.applicationNo.value=applicationNo;
	document.report.reqFrom.value='quoteView';
	if(30=='<s:property value="#session.product_id"/>') {
		document.report.contentTypeId.value = contentTypeId;
		if(status!='RA') {
			document.report.display.value='getQuote';
		}
		//document.report.action = "${pageContext.request.contextPath}/initHome.action";
		document.report.action = "${pageContext.request.contextPath}/packageSelectionHome.action";
	} else if(65=='<s:property value="#session.product_id"/>') {
		document.report.action = "${pageContext.request.contextPath}/editQuoteMotor.action";
	} else if(3=='<s:property value="#session.product_id"/>' || 11=='<s:property value="#session.product_id"/>') {
		document.report.action = "${pageContext.request.contextPath}/editQuoteQuotation.action";
	}else if(33 == '<s:property value="#session.product_id"/>')
		document.report.action = "${pageContext.request.contextPath}/initTravel.action";
	document.report.submit();
}
function editPolicy(applicationNo,quoteNo, status,customerId, contentTypeId) { 
	document.report.quoteNo.value=quoteNo;
	document.report.quoteStatus.value=status;
	document.report.applicationNo.value=applicationNo;
	document.report.reqFrom.value='';
	if(30=='<s:property value="#session.product_id"/>') {
		document.report.contentTypeId.value = contentTypeId;
		if(status!='RA') {
			document.report.display.value='getQuote';
		}
		//document.report.action = "${pageContext.request.contextPath}/initHome.action";
		document.report.action = "${pageContext.request.contextPath}/packageSelectionHome.action";
	} else if(65=='<s:property value="#session.product_id"/>') {
		document.report.action = "${pageContext.request.contextPath}/editQuoteMotor.action";
	} else if(3=='<s:property value="#session.product_id"/>' || 11=='<s:property value="#session.product_id"/>') {
		document.report.action = "${pageContext.request.contextPath}/editQuoteQuotation.action";
	}else if(33 == '<s:property value="#session.product_id"/>')
		document.report.action = "${pageContext.request.contextPath}/initTravel.action";
	document.report.submit();
}
function declaration(tranId)
{
	document.report.tranId.value=tranId;
	document.report.menuType.value='D';
	document.report.reqFrom.value='declare';
	document.report.action = "${pageContext.request.contextPath}/initDeclaration.action";
	document.report.submit();
}
function forward(menuType)
{
	document.report.menuType.value=menuType;
	document.report.action = "${pageContext.request.contextPath}/initReport.action";
	document.report.submit();
}
function userSelectReport(obj)
{ 	
	if(document.report.searchValue){
		document.report.searchValue.value='';
	}
	if(obj.value==''){
		document.report.loginId.value='<s:property value="#session.user"/>';	
	}else{
	document.report.loginId.value=obj.value;	
	}
			
	document.report.action='${pageContext.request.contextPath}/initReport.action';	
	document.report.submit();
}

function forwardReport(menuType)
{
    document.report.menuType.value=menuType;
	document.report.action='${pageContext.request.contextPath}/initReport.action';	
	document.report.submit();	
}
function viewQuote(val)
{
	var URL ='${pageContext.request.contextPath}/QuotePrint.pdfSchedule?quote_no='+val+'&reqFrom=QuotePrint';
	var windowName = "QuotatiionPrint";
	var width  = screen.availWidth;
	var height = screen.availHeight;
	var bwidth = window.innerWidth;
	var bwidth1 = document.body.clientWidth;
	if(bwidth <= 768) {
		var w = bwidth1;
		var h =	500;
	} else {
		var w = 750;
		var h =	500;
	}
	var features =
		'width='          + w +
		',height='		  + h +
		',left='		  + ((width - w - 10) * .5)  +
		',top='			  + ((height - h - 30) * .5) +
		',directories=no' +
		',location=no'	  +
		',menubar=no'	  +
		',scrollbars=yes' +
		',status=no'	  +
		',toolbar=no'	  +
		',resizable=false';
	var strOpen = window.open (URL, windowName, features);
	strOpen.focus();
	return false;
}
function sentMail(val)
{
	document.report.scrnFrom.value = "QuoteRegister";
	document.report.quote_no.value=val;
	document.report.action="mailController";
	document.report.submit();
}
function sentEMail(applicationNo,linkType,quoteNo)
{
	document.report.menuType.value='<s:property value="%{menuType}" />';
	document.report.applicationNo.value=applicationNo;
	document.report.linkType.value=linkType;
	document.report.quoteNo.value=quoteNo;		
	document.report.action='${pageContext.request.contextPath}/mailReport.action';
	document.report.submit();
}
function delQuotes(val)
{
	document.report.quote_no.value=val;
	document.report.action='${pageContext.request.contextPath}/LapsedQuote1.jsp';
	document.report.submit();
}
function lapsedQuotes(val)
{
	document.report.quote_no.value=val;
	document.report.action='${pageContext.request.contextPath}/lapsedReport.action';
	document.report.submit();
}
function view(appNo,quoteNo,productId)
{
	if(productId=='3' || productId=='11'){
		popUp('\quotationSchedule.jsp?quoteNo='+quoteNo,650,650);
	}else
	{
		popUp('${pageContext.request.contextPath}/viewTravel.action?quoteNo='+quoteNo+'&applicationNo='+appNo+'&selection=profile',650,650);
	}
}
function redirect(url)
{
	document.report.action='${pageContext.request.contextPath}'+url;
	//document.report.submit();
}
function endorsement(menuType, quoteNo, policyNo, custName, brokerCompany, loginId, vehicleId)
{
	document.report.brokerCompany.value=brokerCompany;
	document.report.custName.value=custName;
	document.report.quoteNo.value=quoteNo;
	document.report.policyNo.value=policyNo;
	document.report.menuType.value=menuType;
	document.report.endtLoginId.value=loginId;
	document.report.vehicleId.value=vehicleId;
	document.report.action = "${pageContext.request.contextPath}/initReport.action";
	document.report.submit();
}
function endorsementType(menuType, endStatus, typeId, quoteNo, status, applicationNo)
{
	document.report.applicationNo.value=applicationNo;
	document.report.quoteNo.value=quoteNo;
	document.report.menuType.value=menuType;
	document.report.endTypeId.value=typeId;
	document.report.endStatus.value=endStatus;
	document.report.quoteStatus.value=status;
	if(status=='ReferalApproved' || status=='RA'){ //if(status=='RA'){
		document.report.quoteStatus.value='RA';
		document.report.action = "${pageContext.request.contextPath}/initPremium.action";
	}else{
		document.report.action = "${pageContext.request.contextPath}/initReport.action";
	}
	document.report.submit();
}
function endorsementTypeNew(menuType, endStatus)
{
	document.report.menuType.value=menuType;
	document.report.endStatus.value=endStatus;
	document.report.endTypeId.value='';
	document.report.action = "${pageContext.request.contextPath}/initReport.action";
	document.report.submit();
}
function searchByPolicy()
{
	if(document.report.searchValue.value==''){
		alert('Please enter Policy No to search');
		return false;
	}
}
function searchByQuote()
{
	if(document.report.searchValue.value==''){
		alert('Please enter Quote No to search');
		return false;
	}
}
function toggleConfirm(){
	var status=document.getElementById('41').checked;
	if(status){
	   document.getElementById('toggle').style.display="";
	   document.getElementById('cancelRemarks').style.display="";
	}else{
	   document.getElementById('toggle').style.display="none";
	   document.getElementById('cancelRemarks').style.display="none";
	} 
}
function lcupload(policyNo){
	document.report.policyNo.value = policyNo; 
	document.report.action = "${pageContext.request.contextPath}/lcUploadReport.action";
	document.report.submit();
}
function claim(id,productId,mode){
	document.forms['report'].requireType.value ='claim';
    if(productId == '65'){
        document.forms['report'].mode.value = mode;
		document.forms['report'].vehicleId.value = id;
	}	
	if(productId == '30'){
	    document.forms['report'].mode.value = mode;
	}
	document.forms['report'].action = "${pageContext.request.contextPath}/intimateClaim.action";
	document.forms['report'].submit();
}
function OtherEndorsement(Vid,PolicyNO,mode){
	document.forms['report'].requireType.value ='endorsement';
	document.forms['report'].mode.value = mode;
	document.forms['report'].vehicleId.value = Vid;
	document.forms['report'].policyNo.value = PolicyNO;	
	document.forms['report'].action = "${pageContext.request.contextPath}/endorsementClaim.action";
	document.forms['report'].submit();
}
function viewCorrection(quoteNo,appNo){
	document.report.quoteNo.value=quoteNo;
	document.report.applicationNo.value=appNo;
	document.report.action = "${pageContext.request.contextPath}/viewTravel.action";
	document.report.submit();
}
function viewPopUp(URL) {
	var bwidth = window.innerWidth;
	var bwidth1 = document.body.clientWidth;
	if(bwidth <= 768) {
		return popUp(URL,bwidth1,'500');
	} else {
		return popUp(URL,'750','500');
	}
}
function getPolicyWordingPdf(val){
	var URL ='${pageContext.request.contextPath}/policyWordingReport.action?quoteNo='+val;
	var bwidth = window.innerWidth;
	var bwidth1 = document.body.clientWidth;
	if(bwidth <= 768) {
		return popUp(URL,bwidth1,'500');
	} else {
		return popUp(URL,'750','500');
	}
}
function getMotorDetails(policyNo,reqFrom){
	document.report.reqFrom.value =reqFrom;
	document.report.menuType.value = 'PVD';
	document.report.action = "${pageContext.request.contextPath}/initReport.action";
	document.report.submit();
}
function fnView(val){
	document.report.action='getInstallmentViewPaymentStatus.action?quoteNo='+val;
    document.report.submit();
}
function fnView1(val,val2){
	document.report.action='getMotorViewPaymentStatus.action?quoteNo='+val+'&reqTranNo='+val2;
    document.report.submit();
}
function viewQuoteDtl(quoteNo,appNo)
{
	var URL ='${pageContext.request.contextPath}/viewQuoteInfoMotor?quoteNo='+quoteNo+'&applicationNo='+appNo;
	var windowName = "QuotatiionPrint";
	var width  = screen.availWidth;
	var height = screen.availHeight;
	var bwidth = window.innerWidth;
	var bwidth1 = document.body.clientWidth;
	if(bwidth <= 768) {
		var w = bwidth1;
		var h =	500;
	} else {
		var w = 750;
		var h =	500;
	}
	var features =
		'width='          + w +
		',height='		  + h +
		',left='		  + ((width - w - 10) * .5)  +
		',top='			  + ((height - h - 30) * .5) +
		',directories=no' +
		',location=no'	  +
		',menubar=no'	  +
		',scrollbars=yes' +
		',status=no'	  +
		',toolbar=no'	  +
		',resizable=false';
	var strOpen = window.open (URL, windowName, features);
	strOpen.focus();
	return false;
}
</script>
</html>