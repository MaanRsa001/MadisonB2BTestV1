<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html <s:if test="locale.language == 'ar'">dir="rtl"</s:if> >
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>HomeHelp</title>
		<link href="${pageContext.request.contextPath}/bootstrap/css/footable-0.1.css" rel="stylesheet" type="text/css">
		<STYLE type="text/css">
		.tableHeading {
			background: #0078ae url(images/ui-bg_glass_45_0078ae_1x400.png) 50% 50% repeat-x;
			color: #ffffff; font-weight: bold; font-size: 14px;
		}
		</STYLE>
	</head>
	<body>
	 	<s:form name="helpInfo" theme="simple">
			<table width="100%" border="0" align="center" class="footable">
				<tbody>
		   			<tr>
						<td align="center" class="tableHeading">S.No</td>
						<td align="center" class="tableHeading">Title</td>
						<td align="center" class="tableHeading">Description</td>
					</tr>
		   			<s:iterator value="helpInfoList" var="var" status="status">
		   				<tr>
		   					<td align="center">${status.count}</td>
		   					<td align="center">${var.DESCRIPTION}</td>
		   					<td align="center">${var.REMARKS}</td>
		   				</tr>
		   			</s:iterator>
		   		</tbody>
			</table>
	  	</s:form>
	</body>
</html>