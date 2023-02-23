<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/WEB-INF/displaytag.tld" prefix="display"%>
<%@ page isELIgnored="false"%>
<html>
	<head>
		<link rel="stylesheet" type="text/css"
			href="<%=request.getContextPath()%>/cssbootstrap/bootstrap.min.css" />
		<link
			href="<%=request.getContextPath()%>/cssbootstrap/footable-0.1.css"
			rel="stylesheet" type="text/css" />
		<style type="text/css">
			.datepicker table tr th{
				background-color: #FFF;
			}
		</style>
		
		<script type="text/javascript">
			function stopRKey(evt) { 
				var evt = (evt) ? evt : ((event) ? event : null); 
				var node = (evt.target) ? evt.target : ((evt.srcElement) ? evt.srcElement : null); 
				if ((evt.keyCode == 13) && (node.type=="text"))  {return false;} 
			} 
			document.onkeypress = stopRKey;
		</script>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css">
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/bootstrap/css/datepicker3.css">
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
	  			var data = $('.display').dataTable( {
					"lengthMenu": [[10, 25, 50, -1], [10, 25, 50, "All"]],
					"order": [[ 1, "desc" ]],
					"columnDefs": [ {
			          "targets": 'no-sort',
			          "orderable": false
				    } ],
					responsive: true
				});
				
			} catch(err){}
		} );
	  	</script>
	</head>
	<body>
		<table>
			<tr>
		    	<td>
				   	<s:if test="hasActionErrors()">
						<font color="red" style="list-style:none; "><s:actionerror cssStyle="list-style:none;"/></font>
					</s:if>
					<s:if test="hasActionMessages()">
						<s:actionmessage cssStyle="list-style:none; color:green;"/>
					</s:if>
				</td>
			</tr>
		</table>
					
		<div class="panel panel-primary">
			<div class="panel-heading clearfix" >
				<div class="pull-left">
					<s:text name="claim.claimIntimation" />
				</div>
				<div class="pull-right">
					<s:if test='(#session.user1 == "admin" && status == "claimDetails")'>
							<span style="width:20%; font:bold;">
								<s:text name="label.claim.claimNo"></s:text>&nbsp : &nbsp
							</span>
							<span style="width:20%;font:bold;">
								<s:property value="claimNo"/>
							</span>	
						</s:if>
				</div>
			</div>
			<div id="tabs-1">
				<div class="panel-body">
					<div id="pendingPolicies">
					<s:form name="claimReport" method="post" theme="simple"	action="">
						<s:if test='(status == "intimated" || status.equals("intimated"))'>
							<div style="color: red; margin: 50px 0 50px 0;" align="center">
								<s:text name="label.claim.intimated.successfully" > </s:text><br/>
								<s:text name="label.claim.no.is" > </s:text>
								<span style="color: #000;">
									<s:property value="claimNo"/>	
								</span>
								
							</div>
						</s:if>
						<s:elseif test='(#session.user1 == "admin" || #session.user1.equalsIgnoreCase("admin")) && (status != "claimDetails")'>
							<table class="display" width="100%" cellspacing="0">
								<thead>
									<tr>
										<th><s:text name="label.claim.sno" /></th>
										<th><s:text name="label.claim.claimNo" /></th>
										<th><s:text name="claim.policyNo" /></th>
										<th><s:text name="claim.intimationDate" /></th>
										<th><s:text name="claim.lossDate" /></th>
										<th><s:text name="claim.lossDescription" /></th>
										<th><s:text name="label.claim.view" /></th>
									</tr>
								</thead>
								<tbody>
									<s:iterator value="claimList" var="claim" status="stat">
										<tr>
											<td>${stat.count}</td>
											<td>${claim.CLAIM_NO}</td>
											<td>${claim.POLICY_NO}</td>
											<td>${claim.INTIMATION_DATE}</td>
											<td>${claim.LOSS_DATE}</td>
											<td>${claim.LOSS_DESCRIPTION}</td>
											<td valign="middle" align="center"><a href="#" type="button" class="btn btn-sm btn-info" onClick="getView('${claim.CLAIM_NO}','${claim.POLICY_NO}','${claim.PRODUCT_ID}');"><i class="glyphicon glyphicon-eye-open"></i></a></td>
										</tr>
									</s:iterator>
								</tbody>
							</table>			
					</s:elseif>
					<s:elseif test='(status == "claimDetails")'>
						<table width="100%">
							<tr>
								<td>
									<table width="100%" class="footable">
										<tr>
											<td colspan="4" width="100%" style="background: #f5f5f5;"
												align="center">
												<b><s:text name="claim.Policy.Details"></s:text> </b>
											</td>
										</tr>
										<tr>
											<td width="20%">
												<b><s:text name="claim.policyNo" /> </b>
											</td>
											<td width="30%">
												<s:property value="policyNo" />
											</td>
											<td width="20%">
												<b><s:text name="claim.startDate" /> </b>
											</td>
											<td width="30%">
												<s:property value="policyStartDate" />
											</td>
										</tr>
										<tr>
											<td width="20%">
												<b><s:text name="claim.quote.no" /> </b>
											</td>
											<td width="30%">
												<s:property value="quoteNo" />
											</td>
											<!--<td width="20%">
												<b><s:text name="claim.quote.date"/> </b>
											</td>
											<td width="30%">
												<s:property value="quoteDate" />
											</td>
											-->
											<td width="20%">
												<b><s:text name="claim.product" /> </b>
											</td>
											<td width="30%">
												<s:property value="product" />
											</td>
										</tr>
										<tr>
											<td width="20%">
												<b><s:text name="claim.premium" /> </b>
											</td>
											<td width="30%">
												<s:property value="premium" />
											</td>
											<td></td>
											<td></td>
										</tr>
										<tr>
											<td colspan="4" width="100%" style="background: #f5f5f5;"
												align="center">
												<b><s:text name="claim.customer.details"></s:text> </b>
											</td>
										</tr>
										<tr>
											<td width="20%">
												<b><s:text name="claim.customer.name" /> </b>
											</td>
											<td width="30%">
												<s:property value="customerName" />
											</td>
											<td width="20%">
												<b><s:text name="claim.core.application.code" /> </b>
											</td>
											<td width="30%">
												<s:property value="coreAppCode" />
											</td>
										</tr>
										<tr>
											<td width="20%">
												<b><s:text name="claim.address" /> </b>
											</td>
											<td colspan="3" width="70%">
												<s:property value="address1" />
											</td>
										</tr>
										<tr>
											<td width="20%">
												<b><s:text name="claim.city" /> </b>
											</td>
											<td width="30%">
												<s:property value="city" />
											</td>
											<td width="20%">
												<b><s:text name="claim.po.box" /> </b>
											</td>
											<td width="30%">
												<s:property value="poBox" />
											</td>
										</tr>
										<tr>
											<td width="20%">
												<b><s:text name="claim.mobile.number" /> </b>
											</td>
											<td width="30%">
												<s:property value="mobileNo" />
											</td>
											<td width="20%">
												<b><s:text name="claim.email" /> </b>
											</td>
											<td width="30%">
												<s:property value="email" />
											</td>
										</tr>
										<tr>
											<td colspan="4" width="100%" style="background: #f5f5f5;"
												align="center">
												<b><s:text name="claim.vehicle.details"></s:text> </b>
											</td>
										</tr>
										<tr>
											<td width="20%">
												<b><s:text name="claim.make" /> </b>
											</td>
											<td width="30%">
												<s:property value="makeName" />
											</td>
											<td width="20%">
												<b><s:text name="claim.model" /> </b>
											</td>
											<td width="30%">
												<s:property value="modelName" />
											</td>
										</tr>
										<tr>
											<td width="20%">
												<b><s:text name="claim.typeOfBody" /> </b>
											</td>
											<td width="30%">
												<s:property value="typeBodyName" />
											</td>
											<td width="20%">
												<b><s:text name="claim.manufacture.year" /> </b>
											</td>
											<td width="30%">
												<s:property value="mfgYr" />
											</td>
										</tr>
										<tr>
											<td width="20%">
												<b><s:text name="claim.sum.insured" /> </b>
											</td>
											<td width="30%">
												<s:property value="sumInsured" />
											</td>
											<td width="20%">
												<b><s:text name="claim.cubic.capacity/tonnage" /> </b>
											</td>
											<td width="30%">
												<s:property value="cubicCapacity" />
											</td>
										</tr>
										<tr>
											<td width="20%">
												<b><s:text name="claim.seating.capacity" /> </b>
											</td>
											<td width="30%">
												<s:property value="seatingCapacity" />
											</td>
											<td width="20%">
												<b><s:text name="claim.no.of.cylinder" /> </b>
											</td>
											<td width="30%">
												<s:property value="noOfCylinder" />
											</td>
										</tr>
										<tr>
											<td width="20%">
												<b><s:text name="claim.vehicle.usage" /> </b>
											</td>
											<td width="30%">
												<s:property value="vehicleUsageName" />
											</td>
											<td width="20%">
												<b><s:text name="claim.area.coverage" /> </b>
											</td>
											<td width="30%">
												<s:property value="areaCoverage" />
											</td>
										</tr>
										<tr>
											<td width="20%">
												<b><s:text name="claim.agency.repair" /> </b>
											</td>
											<td width="30%">
												<s:property value="agencyRepairName" />
											</td>
											<td width="20%">
												<b><s:text name="claim.registration.number" /> </b>
											</td>
											<td width="30%">
												<s:property value="regNo" />
											</td>
										</tr>
										<tr>
											<td width="20%">
												<b><s:text name="claim.chassis.number" /> </b>
											</td>
											<td width="30%">
												<s:property value="chassisNo" />
											</td>
											<td width="20%">
												<b><s:text name="claim.engine.number" /> </b>
											</td>
											<td width="30%">
												<s:property value="engineNo" />
											</td>
										</tr>
										<tr>
											<td width="20%">
												<b><s:text name="claim.vehicle.colour" /> </b>
											</td>
											<td width="30%">
												<s:property value="vehicleColour" />
											</td>
											<td width="20%">
												<b><s:text name="claim.vehicle.registered.location" />
												</b>
											</td>
											<td width="30%">
												<s:property value="vehicleRegLoc" />
											</td>
										</tr>
										<tr>
											<td width="20%">
												<b><s:text name="claim.plate.color" /> </b>
											</td>
											<td width="30%">
												<s:property value="plateCharacter" />
											</td>
											<td width="20%">
												<b><s:text name="claim.leased" /> </b>
											</td>
											<td width="30%">
												<s:property value="leasedYN" />
											</td>
										</tr>
									<!--<tr>
										<td width="20%">
											<b><s:text name="claim.bank.of.finance" /> </b>
										</td>
										<td width="70%" colspan="3">
											<s:property value="bankOfFinance" />
										</td>
									</tr>
									-->
										<tr>
											<td colspan="4" width="100%" style="background: #f5f5f5;"
												align="center">
												<b><s:text name="claim.driver.details"></s:text> </b>
											</td>
										</tr>
										<tr>
											<td width="20%">
												<b><s:text name="claim.driver.dob" /> </b>
											</td>
											<td width="30%">
												<s:property value="driverDOB" />
											</td>
											<td width="20%">
												<b><s:text name="claim.driver.nationality" /> </b>
											</td>
											<td width="30%">
												<s:property value="driverNationality" />
											</td>
										</tr>
										<tr>
											<td width="20%">
												<b><s:text name="claim.driver.licence.no" /> </b>
											</td>
											<td width="30%">
												<s:property value="uaeLicenceNo" />
											</td>
											<td width="20%">
												<b><s:text name="claim.driver.licence.expiry.date" />
												</b>
											</td>
											<td width="30%">
												<s:property value="uaeLicExpDT" />
											</td>
										</tr>
										<tr>
											<td width="20%">
												<b><s:text name="claim.driver.claim.amount" /> </b>
											</td>
											<td width="70%" colspan="3">
												<s:property value="claimAmount" />
											</td>
										</tr>
										<tr>
											<td colspan="4" width="100%" style="background: #f5f5f5;"
												align="center">
												<b><s:text name="claim.premium.info"></s:text> </b>
											</td>
										</tr>
									</table>
									<table width="100%" class="footable">
										<thead>
												<tr>
													<th colspan="4">
														<s:text name="Coverages"></s:text>
													</th>
												</tr>
											</thead>
										<s:iterator value="premiumInfo" var="prInfo" status="stat">
											<tbody>
												<tr>
												<!--<td align="center" width="10%">
														<s:property value="%{#prInfo.POLICYTYPE_COVERID}"/>
													</td>
												-->
													<td align="left" width="20%">
														<s:property value="%{#prInfo.OPCOVER_DESC_ENGLISH}" />
													</td>
													<td align="left" width="20%">
														<s:property value="%{#prInfo.SUM_INSURED}" />
													</td>
													<td align="left" width="20%">
														<s:property value="%{#prInfo.RATE}" />
													</td>
													<td align="right" width="30%">
														<s:property value="%{#prInfo.PREMIUM}" />
													</td>
												</tr>
											</tbody>
										</s:iterator>
										<tbody>
											<tr>
												<td></td>
												<td></td>
												<td>
													<font style="float:left;"><s:text name="claim.premium"/></font>
													<b style="float:right;">[<s:property value="#session.BrokerDetails.CurrencyAbb"/>]</b>
												</td>
												<td align="right">
													<s:textfield name="premium" id="premium"
														cssStyle="text-align:right;"
														onkeyup="checkDecimals(this);" readonly="true" />
												</td>
											</tr>
											<tr>
												<td></td>
												<td></td>
												<td>
													<font style="float:left;"><s:text name="claim.policyFee"/></font>
													<b style="float:right;">[<s:property value="#session.BrokerDetails.CurrencyAbb"/>]</b>
												</td>
												<td align="right">
													<s:textfield name="policyFee" id="policyFee"
														onkeyup="checkDecimals(this);"
														cssStyle="text-align:right;" readonly="true" />
												</td>
											</tr>
											<tr>
												<td></td>
												<td></td>
												<td>
													<font style="float:left;"><s:text name="claim.totalPremiumPayable"/></font>
													<b style="float:right;">[<s:property value="#session.BrokerDetails.CurrencyAbb"/>]</b>
												</td>
												<td align="right">
													<s:textfield name="totalPremium"
														cssStyle="text-align:right;"
														onkeyup="checkDecimals(this);" readonly="true" />
												</td>
											</tr>
										</tbody>
									</table>
									<br class="clear" />
									<hr />
								</td>
							</tr>
						</table>
						<div class="row">
							<div class="col-xs-12 col-sm-12 col-md-6 col-lg-4">
								<div class="text"><s:text name="Plate No." /> </div>
								<div class="tbox">
									<s:textfield cssClass="inputBox" />
								</div>
							</div>
							<div class="col-xs-12 col-sm-12 col-md-6 col-lg-4">
								<div class="text"><s:text name="ID No." /> </div>
								<div class="tbox">
									<s:textfield cssClass="inputBox" />
								</div>
							</div>
							<div class="col-xs-12 col-sm-12 col-md-6 col-lg-4">
								<div class="text"><s:text name="Mobile No." /> </div>
								<div class="tbox">
									<s:textfield cssClass="inputBox" />
								</div>
							</div>
							<div class="col-xs-12 col-sm-12 col-md-6 col-lg-4">
								<div class="text">E-mail </div>
								<div class="tbox">
									<s:textfield cssClass="inputBox" />
								</div>
							</div>
						</div>
					</s:elseif>
					<br class="clear" />
					<div class="row">
						<div class="col-xs-12" align="center">
							<s:if test='(#session.user1 != "admin")'>
								<s:if test='(status != "intimated")'>
									<input type="button" value="Submit" class="btn btn-sm btn-success" onclick="funSave('saveClaimMotorClaim')"/>
											&nbsp;&nbsp;&nbsp;
								</s:if>
								<input type="button" value="Back" class="btn btn-sm btn-danger" onclick="funBack('initReport')">
							</s:if>
							<s:else>
								<s:if test='(status == "claimDetails")'>
									<input type="button" value="Submit" class="btn btn-sm btn-success" onclick="funSave('closeClaimMotorClaim')"/>
											&nbsp;&nbsp;&nbsp;
									<input type="button" value="Back" class="btn btn-sm btn-danger" onclick="funBack('adminClaimMotorClaim')">
									<s:hidden name="status"></s:hidden>
									<s:hidden name="claimNo"></s:hidden>
								</s:if>
							</s:else>
						</div>
					</div>
					<s:hidden name ="applicationNo"/>
					<s:hidden name="policyNo"></s:hidden>
					<s:hidden name="quoteStatus"></s:hidden>
					<s:hidden name=" productId"></s:hidden>
					<s:hidden name="loginId"></s:hidden>
				</s:form>
			</div>
		</div>
	</div>
</div>
</body>
	<script type="text/javascript" src="<%=request.getContextPath()%>/bootstrap/js/jquery-1.9.1.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/bootstrap/js/app-js.js"></script>
	<script src="<%=request.getContextPath()%>/bootstrap/js/bootstrap-datepicker.js" type="text/javascript"></script>
	<script src="<%=request.getContextPath()%>/bootstrap/js/bootstrap-3.2.0.min.js" type="text/javascript"></script>
	<script type="text/javascript">
		function funSave(action){
			document.claimReport.action = "${pageContext.request.contextPath}/"+action+".action";
			document.claimReport.submit();
		}
		function funBack(action)
		{
			if(action == "adminClaimMotorClaim"){
				document.claimReport.action = "${pageContext.request.contextPath}/"+action+".action";
			}else{
				document.claimReport.action = "${pageContext.request.contextPath}/"+action+".action?menuType=P";
			}
			document.claimReport.submit();
		}
		function getView(claimNo,policyNo,productId){
			document.claimReport.policyNo.value=policyNo;
			document.claimReport.action = '${pageContext.request.contextPath}/closeClaimDetMotorClaim.action?claimNo='+claimNo+'&productId='+productId;
			document.claimReport.submit();
		}
	</script>
		
</html>