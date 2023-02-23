<%@ page language="java" import="java.util.*,java.sql.Connection" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page import="java.util.*,java.io.*,java.text.*" %>
<html>
<head>	
</head>
<body>
	<s:url forceAddSchemeHostAndPort="true" includeParams="all" var="myurl" escapeAmp="false" encode="false">
		<s:param name="request_locale"/>
	</s:url>
	<div class="layoutHeader">
		<div class="pullLeft">			
			<s:if test="#session.user1==getText('admin')">
				<a href="${pageContext.request.contextPath}/homeAdmin.action">
					<img src="${pageContext.request.contextPath}/images/mgen-logo.png" border="0" >
				</a>
			</s:if>
			<s:else>
				<a href="${pageContext.request.contextPath}/HomeUser.action">
					<img src="${pageContext.request.contextPath}/images/mgen-logo.png" border="0" >
				</a>
			</s:else>			
		</div>
		<div class="pullRight" style="color: #000000;">
			<font color="#dca531" style="font: bold;font-size: 30px;">
				<s:if test='"Live".equalsIgnoreCase(#session.userLoginMode)'>
					MGen-Online 	
				</s:if>
				<s:if test='"Test".equalsIgnoreCase(#session.userLoginMode)'>
					MGen-Test  
				</s:if>
			</font> <br/>
			<span style="font-weight: normal;"><i class="fa fa-phone"></i>: 4848 | 378700-5 | <i class="fa fa-envelope"></i>: <a href="mailto:online@madison.co.zm?Subject=Insure" target="_top" style="color: #DBA832;">online@madison.co.zm</a></span> <br/>
			<span style="font-size: 12px;">
				<%--
				<s:if test="#session.user1==getText('admin')">
					<a href="${pageContext.request.contextPath}/homeAdmin.action">Home</a>&nbsp;&nbsp;|&nbsp;&nbsp;
				</s:if>
				<s:else>
					<a href="${pageContext.request.contextPath}/HomeUser.action">Home</a>&nbsp;&nbsp;|&nbsp;&nbsp;
				</s:else>
				--%>
				<a href="${pageContext.request.contextPath}/HomeUser.action">Home</a>&nbsp;&nbsp;|&nbsp;&nbsp;
				<s:if test='%{#session.LoginType=="B2C" && #session.user1!=getText("admin")}'>
					<a href="${pageContext.request.contextPath}/Loginlanding.action">Login In</a>&nbsp;&nbsp;&nbsp;&nbsp;
				</s:if>
				<s:else>
					<a href="${pageContext.request.contextPath}/Loginout.action">Sign Out</a>&nbsp;&nbsp;&nbsp;&nbsp;
					<font style="font-weight: bold;color:#000000"> LoginId&nbsp;:&nbsp;&nbsp;<s:property value="#session.user"/></font>
				</s:else>
			 	<%--<a href="" onclick="setLang(this, '<s:property value="#myurl"/>', 'en')" >English</a>&nbsp;&nbsp;|&nbsp;&nbsp;
				<a href="" onclick="setLang(this, '<s:property value="#myurl"/>', 'pt')" >Portuguese</a>&nbsp;&nbsp;&nbsp;&nbsp;--%>
			</span>
			<s:if test='%{#session.usertype==getText("Broker")}'><br/>
				<span style="font-size: 12px;">
					<font style="font-weight: bold;color:#000000"> Branch&nbsp;:&nbsp;&nbsp;<s:property value="#session.branchName"/></font>
				</span>
			</s:if>
		</div>
		<br class="clear"/>
		<div>
		<%--<font class="blinking" color="red" style="font: bold;font-size: 15px;">“Only Servicing Lusaka based Clients Currently”</font>
		--%></div>
	</div>
</body>
<%--<script type="text/javascript">
function setLang(obj, url, lang) {
	url=url.replace('request_locale=', 'request_locale='+lang);
	obj.href=url;
}
</script>--%>
<script>
//function blinker() {
	//$('.blinking').fadeOut(750);
	//$('.blinking').fadeIn(750);
//}
//setInterval(blinker, 1500);
</script>
</html>
