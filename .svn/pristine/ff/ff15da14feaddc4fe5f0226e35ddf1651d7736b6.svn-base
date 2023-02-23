<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html <s:if test="locale.language == 'ar'">dir="rtl"</s:if> >
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<script language="JavaScript">javascript:window.history.forward(1);</script>
		<script type="text/javascript" >
			function stopRKey(evt) { 
			  var evt = (evt) ? evt : ((event) ? event : null); 
			  var node = (evt.target) ? evt.target : ((evt.srcElement) ? evt.srcElement : null); 
			  if ((evt.keyCode == 13) && (node.type=="text"))  {return false;} 
			} 
			document.onkeypress = stopRKey;
		</script>
</head>

<body>
  <s:form name="homePremium" theme="simple">
	<table width="100%" border="1" align="center" bgcolor="#FFFFFF">
			<s:if test="hasActionErrors()">
		       <tr>
				  <td  style="color:red;"><s:actionerror/></td>
			   </tr>
		   </s:if>
		   <tr>
			  <td colspan="4" class="heading">
		   		<s:label key="homePremium.GeneralInfo"/>
			  </td>
		   </tr>
		   <tr>
		      <td><s:label key="homePremium.QuoteNo"/>&nbsp;:&nbsp;<b><s:property value="quoteNo"/></b></td>
		      <td><s:label key="homePremium.CustomerName"/>&nbsp;:&nbsp;<b><s:property value="customerName"/></b></td>
		      <td><s:label key="homePremium.InsuredLocation"/>&nbsp;:&nbsp;<b><s:property value="insuredLocation"/></b></td>
		      <td><s:label key="homePremium.Email"/>&nbsp;:&nbsp;<b><s:property value="email"/></b></td>
		   </tr>
		   <tr>
				<td colspan="4" class="heading">
				   <s:label key="homePremium.SumInsured"/>
				</td>
		   </tr>
		   <tr>
		   		<td><b><s:label key="homePremium.CoverId"/></b></td>
		   		<td><b><s:label key="homePremium.Coverage"/></b></td>
		   		<td><b><s:label key="homePremium.SumInsured"/></b></td>
		   		<td><b><s:label key="homePremium.Premium"/></b></td>
		   </tr>
			<s:iterator value="Premium" id="Premium">
				<tr class="bg">
				    <td><s:property value="COVERAGES_ID"/></td>
				    <td><s:property value="COVERAGES_DISPLAY_NAME"/></td>
				    <td><s:property value="COVERAGES_SUMINSURED"/></td>
				    <td><s:property value="PREMIUM_AMOUNT"/></td>
				</tr>
			</s:iterator>
		   <tr>
				<td><b><s:label key="homePremium.TotalPremium"/></b></td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td><b><s:property value="totalPremium"/></b></td>
		   </tr>
		   <tr>
				<td colspan="4" class="heading">
				   <s:label key="homePremium.InsuranceDetails"/>
				</td>
		   </tr>
		   <tr>
		   		<td><s:label key="homePremium.InsuranceStartDate"/></td>
		   		<td><s:property value="effectiveDate"/></td>
				<td><s:label key="homePremium.ExpiryDate"/></td>
				<td><s:property value="expiryDate"/></td>
		   </tr>
		   <tr>
				<td colspan="4" class="heading">
				   <s:label key="homePremium.AdminReferral"/>
				</td>
		   </tr>
		    <tr>
		        <td><s:label key="homePremium.AdminReferral.msg"/></td>	
                <td><s:radio list="#{'Y':'Yes','N':'No'}" name="referralUpdate"  id="referralUpdate"  onclick="disablePolicyOption(this.value);enable(this.value, 'referralComment')" value="%{referralUpdate==null?(ADMIN_REFERRAL_STATUS==null?'N':ADMIN_REFERRAL_STATUS):referralUpdate}"/></td>
                <td><b><s:label key="homePremium.comments" /></b></td>
    			<td><s:textarea name="referralComment" id="referralComment" cols="25" rows="2"/></td>
		   </tr>
		   <tr>
				<td colspan="4" class="heading">
				   <s:label key="homePremium.PolicyDetails"/>
				</td>
		   </tr>
		   <tr>
		        <td colspan="4" align="center"> 
                	<s:label key="homePremium.PolicyDetails.msg"/>
                	<s:radio name="generatePolicyYN" list="#{'Y':'Yes','N':'No'}" value="%{'N'}" onclick="togglePolicyYN(this.value)"/>
                </td>
		   </tr> 
	       <tr>
		       <td colspan="4" align="center">
		       	 
		       	 <s:submit type="button" name="Back" value="Back" key="" cssClass="btn" onclick="forward('getBackHome.action')"/>
		       	 <s:submit type="button" name="Proceed" value="Proceed" key="" cssClass="btn" onclick="forward('getBackHome.action')"/>
		       </td>
	       </tr>
      </table>
      <s:hidden name="quoteNo"></s:hidden>
  </s:form> 
<script type="text/javascript">
function forward(url)
{
    //alert(url);
 	document.homePremium.action = "${pageContext.request.contextPath}/"+url;
	document.homePremium.submit();
}
</script>
</body>
</html>
