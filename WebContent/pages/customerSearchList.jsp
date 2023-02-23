<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<table class="footable">
<thead>
<tr>     				   
<th width="5%"></th>
<th width="19%"><b>Customer Name</b></th>
<th width="19%"><b>P.O.Box</b></th>
<th width="19%"><b>City/Town</b></th>
<th width="19%"><b>Email</b></th>
<th width="19%"><b>Core Application Code</b></th>               
</tr>
</thead>
<tbody>
<s:if test="customerSelection.size > 0">
	<s:iterator value="customerSelection" var="customerdetail" status="stat">
		<tr>   
		 <td><input type="radio" name="selects" onclick="customerDetail('<s:property value="%{#customerdetail.TITLE}"/>','<s:property value="%{#customerdetail.ADDRESS1}"/>','<s:property value="%{#customerdetail.ADDRESS2}"/>','<s:property value="%{#customerdetail.MOBILE}"/>','<s:property value="%{#customerdetail.EMIRATE}"/>','<s:property value="%{#customerdetail.POBOX}"/>','<s:property value="%{#customerdetail.FIRST_NAME}"/>','<s:property value="%{#customerdetail.MISSIPPI_CUSTOMER_CODE}"/>','<s:property value="%{#customerdetail.CUSTOMER_ID}"/>','<s:property value="%{#customerdetail.EMAIL}"/>','<s:property value="%{#customerdetail.CUST_AR_NO}"/>','<s:property value="%{#customerdetail.cust_name}"/>')"/></td>   				  
		 <td><s:property value="FIRST_NAME" /></td>
		 <td><s:property value="POBOX"  /></td>
		 <td><s:property value="CITY_NAME" /></td>
		 <td><s:property value="EMAIL" /></td>
		 <td><s:property value="MISSIPPI_CUSTOMER_CODE" /></td>
		 <%--
		 <td width="10%" class="bg"><s:property value="CUST_AR_NO" /></td>
		 --%>
		</tr>
	</s:iterator>
</s:if>
<s:else>
<tr><td colspan="6"><s:label key="quotation.norecords" theme="simple"></s:label> </td> </tr>
</s:else>
</tbody>
</table>
