<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@page import="com.maan.premium.DAO.PremiumInputsBean"%>
<!DOCTYPE HTML>
<html>
<head>
	<title>Madison General Insurance</title>
	<jsp:useBean id="beans" class="com.maan.opencover.bean.rateModification">
		<jsp:setProperty name="beans" property="*"/>
	</jsp:useBean>
	
	<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css">
	<link href="${pageContext.request.contextPath}/css/footable-0.1.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js"></script>
	<script language="JavaScript">
		function stopRKey(evt) { 
		 	 var evt = (evt) ? evt : ((event) ? event : null); 
		  	 var node = (evt.target) ? evt.target : ((evt.srcElement) ? evt.srcElement : null); 
		 	 if ((evt.keyCode == 13) && ((node.type=="text") || (node.type=="password"))) {return false;}
		} 
		document.onkeypress = stopRKey; 
	</script>
	<style type="text/css">
	.inputBox {
		height: 20px;
		width: 95%;
	}
	</style>
</head>
	<body>
		<!-- This is for Declaration   -->
		<%!
		String action=null;
		String datas[]=new String[12];
		String proposalNo = null;
		String openCoverType = "";
		%>
		<!-- This is for Declaration   -->
		<!-- This All are get from the Table -->
		<%
		action="showOpencoverSummary.jsp";
		proposalNo = (String)session.getAttribute("proposalNo");
		datas = beans.getMopPremiumDetails(proposalNo);
		openCoverType = beans.getOpenCoverType(proposalNo);
		
		if(request.getAttribute("errorDetail")!=null) {
			
		}
		datas[9] = "".equals(datas[9])?"N":datas[9];
		datas[7] = "".equals(datas[7])?"0":datas[7];
		%>
		<!-- This All are get from the Table -->
		<table width="90%"  border="0" cellspacing="0" cellpadding="0"  align="center">
			<tr>
				<td>
					<%@include file="menus.jsp"%>
				</td>
			</tr>
			<tr>
				<td>
					<form name="form1" method="post">
						<table width="100%" align="center" border="0" > 
							<tr>
								<td align="left" width="35%">
									<input type="hidden" name="actionPath" value="<%=action%>">
								</td>
								<td align="left">
									<font color="red" size="2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<%=request.getAttribute("errorDetail")!=null?(String)request.getAttribute("errorDetail"):"&nbsp;"%></font>
								</td>
							</tr>
						</table>
						<table align="center" border="0" cellpadding="2" cellspacing="2" width="100%">
							<tr>
								<td colspan="4">
									<span class="heading">Proposal Number:&nbsp;&nbsp;<%=proposalNo%></span>
									<span class ="heading" style="float: right"><%=(session.getAttribute("missippiCode")!=null && !"".equals((String)session.getAttribute("missippiCode")))?("Core Application Policy No: "+(String)session.getAttribute("missippiCode")):""%></span>
								</td>
							</tr>
						</table>
						<br/>
						<table align="center" border="0" cellpadding="2" cellspacing="2" width="100%">
							<tr>
								<td>
									<span class ="heading">Premium Computation</span>			
								</td>
							</tr>
						</table>
						<table class="footable" style="margin: 0 auto;">
							<thead>
								<tr>
									<th>Total Sum Insured</th>
									<th>Marine Rate</th>
									<th>War Rate</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td><%=datas[0]%></td>
									<td><%=datas[1]%></td>
									<td><%=datas[3]%></td>
								</tr>
							</tbody>
						</table>
						<br/>
						<table class="footable" style="width: 50%; margin: 0 auto;">
							<tbody>
								<% if("12".equals(openCoverType)) { %>
									<tr>
										<td width="50%">Marine Premium</td>
										<td width="50%"><input type="text" name="marinePremium" value="<%=datas[2]%>" class="inputBox" style="text-align: right;" maxlength="15" readonly="readonly"/></td>
									</tr>
									<tr>
										<td width="50%">War Premium</td>
										<td width="50%"><input type="text" name="warPremium" value="<%=datas[4]%>" class="inputBox" style="text-align: right;" maxlength="15" readonly="readonly"/></td>
									</tr>
								<% } %>
								<tr>
									<td width="50%">Total Premium</td>
									<td width="50%"><input type="text" name="totalPremium" value="<%=datas[5]%>" class="inputBox" style="text-align: right;" maxlength="15" readonly="readonly"/></td>
								</tr>
								<tr>
									<td width="50%">Inception Fee</td>
									<td width="50%"><input type="text" name="inceptionFee" value="<%=datas[7]%>" class="inputBox" style="text-align: right;" maxlength="15" onkeyup="checkDecimals10_2(this);"/></td>
								</tr>
								<tr>
									<td width="50%">Policy Fee</td>
									<td width="50%"><input type="text" name="policyFee" value="<%=datas[6]%>" class="inputBox" style="text-align: right;" maxlength="15" onkeyup="checkDecimals10_2(this);"/></td>
								</tr>
								<tr>
									<td width="50%">Final Premium</td>
									<td width="50%"><input type="text" name="finalPremium" id="finalPremium" value="<%=datas[8]%>" class="inputBox" style="text-align: right;" maxlength="15" readonly="readonly"/></td>
								</tr>
								<tr>
									<td width="50%">Chargeable</td>
									<td width="50%">
										<input id="chargeableYNY" name="chargeableYN" type="radio" value="Y" <%="Y".equalsIgnoreCase(datas[9])?"checked":""%> onclick="toggleChargeable();">Yes
			    						<input id="chargeableYNN" name="chargeableYN" type="radio" value="N" <%="N".equalsIgnoreCase(datas[9])?"checked":""%> onclick="toggleChargeable();">No
									</td>
								</tr>
								<tr>
									<td width="50%">Chargeable %</td>
									<td width="50%"><input type="text" name="chargeablePercent" id="chargeablePercent" value="<%=datas[10]%>" class="inputBox" style="text-align: right;" maxlength="15" onkeyup="checkDecimals10_2(this);"/></td>
								</tr>
								<tr>
									<td width="50%">Total Payable Amount</td>
									<td width="50%"><input type="text" name="totalPayAmount" id="totalPayAmount" value="<%=datas[11]%>" class="inputBox" style="text-align: right;" maxlength="15" disabled="disabled"/></td>
								</tr>
							</tbody>
						</table>
					</form>
					<br/>
				</td>
			</tr>
			<tr>
				<td>
					<table width="100%">
						<tr>
							<td align="center">
								<a href="#" onClick="fnsubmit('Premium.jsp','')"  class="buttonsMenu" >
									 <img src="../images/Back.jpg">
								</a> &nbsp;&nbsp;&nbsp;
								<a href="#" onClick="fnsubmit('PremiumCalculation.jspa', 'PremiumCalculation.jsp')"  class="buttonsMenu" >
									 <img src="../images/Calculate.jpg">
								</a> &nbsp;&nbsp;&nbsp;
								<a href="#" onClick="fnsubmit('PremiumCalculation.jspa','<%=action%>');"  class="buttonsMenu" >
									 <img src="../images/Proceed.jpg" >
								</a>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		<script type="text/javascript">
		function fnsubmit(action, actionPath) {
			document.form1.actionPath.value = actionPath;
			disableForm(form1,false,'');
			document.form1.action = action;
			document.form1.submit();
		}
		function toggleChargeable() {
			if(document.getElementById('chargeableYNY').checked) {
				document.getElementById('chargeablePercent').disabled = false;
			} else {
				document.getElementById('chargeablePercent').disabled = true;
				document.getElementById('chargeablePercent').value = "0";
				document.getElementById('totalPayAmount').value = "0";
			}
		}
		toggleChargeable();
		</script>
	</body>
</html>