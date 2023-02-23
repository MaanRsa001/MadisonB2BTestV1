<%@page import="java.util.List,java.util.Map,org.apache.commons.lang3.*"%>
<table class="footable" width="100%" style="font-size: 12px;">
	<tbody>
	<% 
	String vesselName=StringUtils.defaultIfEmpty((String)request.getAttribute("vesselName"),"");
	String[][] vesselInfo=(String[][])request.getAttribute("vesselList");
	if(vesselInfo!=null && vesselInfo.length>0){
		for(int i=0; i<vesselInfo.length; i++){
	%>
	
	<tr>
		<td width="5%">
			<input type="radio" name="select" onclick="selectVessel('<%=StringUtils.defaultIfEmpty(vesselInfo[i][0], "")%>','<%=StringUtils.defaultIfEmpty(vesselInfo[i][1], "")%>')" <%=vesselName.equalsIgnoreCase(vesselInfo[i][1])?"checked":""%> value="<%=StringUtils.defaultIfEmpty(vesselInfo[i][0], "")%>" title="<%=StringUtils.defaultIfEmpty(vesselInfo[i][1], "")%>"/>
		</td>
		<td width="23.75%"><%=StringUtils.defaultIfEmpty(vesselInfo[i][1], "")%></td>
		<td width="23.75%"><%=StringUtils.defaultIfEmpty(vesselInfo[i][2], "")%></td>
		<td width="23.75%"><%=StringUtils.defaultIfEmpty(vesselInfo[i][3], "")%></td>
		<td width="23.75%"><%=StringUtils.defaultIfEmpty(vesselInfo[i][4], "")%></td>
	</tr>
	<%}}else{ %>
	<tr>
		<td colspan="6">
			No records found
		</td>
	</tr>
	<%} %>
</tbody>
</table>