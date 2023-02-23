<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/WEB-INF/displaytag.tld" prefix="display"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ page isELIgnored="false"%>
<html>
<head>
	<title> ::: Madison General - Claim Details ::: </title>
	<link rel="shortcut icon" href="<%=request.getContextPath()%>/images/favicon.ico" />
</head>
<body>
<div class="row">
	<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
		<s:if test="hasActionErrors() || hasActionMessages()">
		<div class="row">
			<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
				<s:actionerror cssStyle="color: red;" /> <s:actionmessage cssStyle="color:green;"/>
			</div>
		</div>
		<br/>
		</s:if>
		<div class="row">
			<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
				<div class="panel panel-primary">
					<div class="panel-heading">
						<s:if test='(reqFrom == "View" )'>
							<s:text name="claim.claimIntimation" />
						</s:if>
						<s:else>
							<s:text name="claim.claimDetails" />
						</s:else>
					</div>
					<div class="panel-body">
						<div id="pendingPolicies">
						<s:form name="claimReport" method="post" theme="simple" action="claimsave">
							<div class="row">
								<div class="col-xs-12 col-sm-12 col-md-6 col-lg-4">
									<span class="textV"><s:text name="claim.policyNo" /></span> &nbsp;:&nbsp;
									<span class="tboxV"><s:property value="policyNo" /></span>
								</div>
								<div class="col-xs-12 col-sm-12 col-md-6 col-lg-4">
									<span class="textV"><s:text name="claim.startDate" /></span> &nbsp;:&nbsp;
									<span class="tboxV"> <s:property value="startDate" /> <s:hidden name="startDate" /> </span>
								</div>
								<div class="col-xs-12 col-sm-12 col-md-6 col-lg-4">
									<span class="textV"><s:text name="claim.purchasedDate" /></span> &nbsp;:&nbsp;
									<span class="tboxV"><s:property value="inceptionDate" /><s:hidden name="inceptionDate" /></span>
								</div>
								<div class="col-xs-12 col-sm-12 col-md-6 col-lg-4">
									<span class="textV"><s:text name="claim.assuredName" /></span>
									<div class="tboxV">
										<table width="100%">
											<tr>
												<td>
													<s:if test='%{assuredName!="" }'>
														<s:property value="assuredName" />
													</s:if>
												</td>
												<s:hidden name="assuredName"></s:hidden>
											</tr>
											<tr>
												<td>
													<s:if test='%{assuredName!="" }'>
														<s:property value="address" />
													</s:if>
												</td>
											</tr>
										</table>
									</div>
								</div>
								<div class="col-xs-12 col-sm-12 col-md-6 col-lg-4">
									<span class="textV"><s:text name="claim.voyage" /></span> &nbsp;:&nbsp;
									<div class="tboxV">
										<s:if test='%{voyageFrom!="" && voyageTo!=""}'>
											<table width="100%">
												<tr>
														<td width="5%" style="padding: 5px;">
															<b><s:text name="claim.from" /> </b>
														</td>
														<td width="45%" style="padding: 5px;">
															: &nbsp; <s:property value="voyageFrom" />
														</td>
														<td width="5%" style="padding: 5px;">
															<b><s:text name="claim.to" /></b>
														</td>
														<td width="45%"  style="padding: 5px;">
															: &nbsp; <s:property value="voyageTo" />
														</td>
													</tr>
											</table>
										</s:if>
									</div>
								</div>
								<s:if test='%{modeOfTransport!="" }'>
								<div class="col-xs-12 col-sm-12 col-md-6 col-lg-4">																				
									<span class="textV"><s:text name="claim.modeOfTransport" /></span> &nbsp;:&nbsp;
									<span class="tboxV"><s:property value="modeOfTransport" /></span>									
								</div>
								</s:if>
								<s:if test='%{exchangeRate!="" || saleTerm!="" }'>
								<div class="col-xs-12 col-sm-12 col-md-6 col-lg-4">
									<b><s:text name="claim.basisValuation" /></b>
									<s:if test='%{saleTerm!="" }'>																				
										<span class="textV"> <s:text name="claim.saleTerm" />  </span>&nbsp;:&nbsp;
										<span class="tboxV"><s:property value="saleTerm" /> </span>
									</s:if>	
									<s:if test='%{exchangeRate!="" }'>																		
										<span class="textV"> <s:text name="claim.exchangeRate" />  </span>&nbsp;:&nbsp;
										<span class="tboxV"><s:property value="exchangeRate" /> </span>
									</s:if>									
								</div>
								</s:if>								
							</div>
							<div class="row">
								<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
									<div class="panel panel-primary">
										<div class="panel-heading">
											<s:text name="Commodity" />
										</div>
										<div class="panel-body">
											<table class="footable" width="100%">
												<thead>
												<tr>
													<th> <s:text name="Commodity Discription" /> </th>
													<th> <s:text name="Supplier Name" /> </th>
													<th> <s:text name="Invoice No." /> </th>
													<th> <s:text name="Invoice Date" /> </th>
													<th> <s:text name="Sum Insured" /> </th>
												</tr>
												</thead>
												<tbody>
												<tr>
													<td></td>
													<td><s:property value="supplierName" /></td>
													<td><s:property value="invoiceNo" /></td>
													<td><s:property value="lcDate" /></td>
													<td><s:property value="sumInsured" /></td>
												</tr>
												</tbody>
											</table>
										</div>
									</div>
								</div>
							</div>
							<s:if test='%{clauseId.size()>0 }'>
								<div class="row">
									<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
										<div class="panel panel-primary">
											<div class="panel-heading">
												<s:text name="claim.condition" />
											</div>
											<div class="panel-body">
												<ol>
													<s:iterator value="clauseId">
														<li>
															<s:property />
														</li>
													</s:iterator>
												</ol>
											</div>
										</div>
									</div>
								</div>
							</s:if>
							<s:if test='%{warrentyId.size()>0 }'>
								<div class="row">
									<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
										<div class="panel panel-primary">
											<div class="panel-heading">
												<s:text name="claim.warenty" />
											</div>
											<div class="panel-body">
												<ol>
													<s:iterator value="warrentyId">
														<li>
															<s:property />
														</li>
													</s:iterator>
												</ol>
											</div>
										</div>
									</div>
								</div>
							</s:if>
							<s:if test='%{exclusionId.size()>0}'>
								<div class="row">
									<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
										<div class="panel panel-primary">
											<div class="panel-heading">
												<s:text name="claim.exclusion" />
											</div>
											<div class="panel-body">
												<ol>
													<s:iterator value="exclusionId">
														<li>
															<s:property />
														</li>
													</s:iterator>
												</ol>
											</div>
										</div>
									</div>
								</div>
							</s:if>
							<s:if test='%{commodityExcess!="0 for each and every loss or occurrence." }'>
								<div class="row">
									<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
										<div class="panel panel-primary">
											<div class="panel-heading">
												<s:text name="claim.excess" />
											</div>
											<div class="panel-body">
												<s:property value="commodityExcess" />
											</div>
										</div>
									</div>
								</div>
							</s:if>
							<div class="row">
								<div class="col-xs-12 col-sm-12 col-md-6 col-lg-4">
									<div class="text"><s:text name="claim.lossDate" /></div>
									<div class="tbox">
										<s:if test='(reqFrom=="View" || reqFrom.equals("View"))'>
											<%--
											<div class="inputAppend" id="datePick" >
											<sj:datepicker name="lossDate" id="lossDate" changeYear="true"
												displayFormat="dd-mm-yy " cssClass="inputBox1" cssStyle="border-color: transparent;"
												disabled="true" />
											</div>
											 --%>
											 <s:textfield cssClass="inputBox datepicker" name="lossDate" id="lossDate" />
										</s:if>
										<s:else>
											<%--
											<div class="inputAppend">
											<sj:datepicker name="lossDate" id="lossDate" changeYear="true"
												displayFormat="dd-mm-yy " cssClass="inputBox1" cssStyle="border-color: transparent;"
												disabled="rights" />
											</div>
											--%>
											<s:textfield cssClass="inputBox datepicker" name="lossDate" id="lossDate" />
										</s:else>
									</div>
								</div>
								<s:if test='(reqFrom=="View" || reqFrom.equals("View"))'>
								<div class="col-xs-12 col-sm-12 col-md-6 col-lg-4">
									<div class="text"> <s:text name="claim.intimationDate" /> </div>
									<div class="tbox">
										<%--
										<div class="inputAppend" id="datePick" style="background-color: rgb(235, 235, 228);">
										<sj:datepicker name="intimationDate" id="intimationDate" 
											changeYear="true" displayFormat="dd-mm-yy" disabled="true" cssClass="inputBox1" cssStyle="border-color: transparent;"
											readonly="true" />
										</div>
										 --%>
										<s:textfield cssClass="inputBox datepicker" name="intimationDate" id="intimationDate" />
									</div>
								</div>
								</s:if>
								<s:else>
								<div class="col-xs-12 col-sm-12 col-md-6 col-lg-4">
									<div class="text"> <s:text name="claim.intimationDate" /> </div>
									<div class="tbox">
										<%--
										<div class="inputAppend" id="datePick" style="background-color: rgb(235, 235, 228);">
										<sj:datepicker name="intimationDate" id="intimationDate" 
											changeYear="true" displayFormat="dd-mm-yy" disabled="true" cssClass="inputBox1" cssStyle="border-color: transparent;"
											readonly="true" value="today" />
										</div>
										 --%>
										<s:textfield cssClass="inputBox datepicker" name="intimationDate" id="intimationDate" />
									</div>
								</div>
								</s:else>
								<s:if test='(reqFrom=="View" || reqFrom.equals("View"))'>
								<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
									<div class="text"><s:text name="claim.lossDescription" /></div>
									<div class="tbox">
										<s:textarea name="lossDescription" rows="3" cssStyle="width: 100%" cssClass="inputBoxA" disabled="true"/>
									</div>
								</div>
								<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
									<div class="text"><s:text name="claim.remarks"/></div>
									<div class="tbox">
										<s:textarea name="remarks" rows="3" />
									</div>
								</div>
								</s:if>
								<s:else>
								<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
									<div class="text"><s:text name="claim.lossDescription" /></div>
									<div class="tbox">
										<s:textarea name="lossDescription" rows="3" cssStyle="width: 100%" cssClass="inputBoxA" />
									</div>
								</div>
								</s:else>
							</div>
							<br/>
							<div class="row">
								<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" align="center">
									<s:if test='(reqFrom=="View" || reqFrom.equals("View"))'>
											<input type="button" value="Submit" class="btn btn-sm btn-success" onclick='fnSave("claimIntimation.action?reqFrom=UpdateRemarks");'/>
											&nbsp;&nbsp;&nbsp;
											<input type="button" value="Back" class="btn btn-sm btn-danger"onclick="funBack();">
									</s:if>
									<s:else>
										<input type="button" value="Submit" class="btn btn-sm btn-success" onclick='fnSave("claimsave");'/>
											&nbsp;&nbsp;&nbsp;
										<input type="button" value="Back" class="btn btn-sm btn-danger" onclick="fnBack();">
									</s:else>
								</div>
							</div>
							<s:hidden name="policyNo"></s:hidden>	
						</s:form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
function fnBack() {
	document.claimReport.action = "${pageContext.request.contextPath}/claimDetails.action";
	document.claimReport.submit();
}

function funBack() {
	document.claimReport.action = "${pageContext.request.contextPath}/claimIntimation.action";
	document.claimReport.submit();
}

function fnSave(action) {
	document.claimReport.action = "${pageContext.request.contextPath}/"+action;
	document.claimReport.submit();
}
</script>
</body>
</html>	