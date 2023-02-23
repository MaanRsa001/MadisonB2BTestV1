<%@ page import="java.util.*"%>

<%
	String ErrorMessage = "";
	StringBuffer ErrorMsg = new StringBuffer();
	if (request.getAttribute("ErrorMsg") != null) {
		ErrorMsg = (StringBuffer) request.getAttribute("ErrorMsg");
		if (ErrorMsg.length() > 0) {

			ErrorMessage = ErrorMsg.toString();
			StringTokenizer st = new StringTokenizer(ErrorMessage, ",");
%>
<table border="0" cellspacing="0" cellpadding="0" width="50%"
	class="row22" align='center'>
	
	<%
	while (st.hasMoreTokens()) {
	%>
	<tr>
		<td height="25">
			&nbsp;
		</td>
		<td align="left" width="90%" style="padding-left:50px">
			<font color="red">*<%=st.nextToken()%>
			</font>
		</td>
	</tr>
	<%
			}
			}
		}else{
	%>
		<table border="0" cellspacing="0" cellpadding="0" width="50%" align='center'>
	<tr>
		<td height="25">
			&nbsp;
		</td>
		<td align="left" width="90%" style="padding-left:50px">
			&nbsp;
		</td>
	</tr>
	<%
		}
	 %>
</table>
