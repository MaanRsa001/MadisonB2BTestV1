<%@ page language="java" import="com.maan.Office.DAO.*"
	pageEncoding="ISO-8859-1"
	isELIgnored="false"%>
<%
	CoverageBean coverageBean = new CoverageBean();
	String schemeID = request.getParameter("schemeID");
	schemeID = schemeID == null ? "" : schemeID;
	String productID = "30";
	if (schemeID.length() > 0 && !schemeID.equalsIgnoreCase("select")) {
%>
<tr>
	<td>
		Content Type
	</td>
	<td>
		<select name="contentType" class="inputBoxS">
			<option value="select">
				Select Content Type
			</option>
			<%
					String[][] scheme = new String[0][0];
					scheme = coverageBean.getContentType(productID, schemeID);
					for (int index = 0; index < scheme.length; ++index) {
			%>
			<option value="<%=scheme[index][0]%>">
				<%=scheme[index][1]%>
			</option>
			<%
			}
			%>
			<!-- Added by chinna-->
			<option value="0">
				None
			</option>
			<!-- Added by chinna-->
		</select>
	</td>
</tr>
<%
}
%>
