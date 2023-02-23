<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML>
<html>
<%-- <%@ include file="/pages/sessionCheckAdmin.jsp"%> --%>
<head>
<style type="text/css">

</style>
</head>
<body>
	<div style="width: 100%; margin: 0 auto;">
		<div class="menubarContainer mt-1 header">
			<nav class="navbar navbar-expand-md bg-light navbar-light">
				<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar"> <span class="navbar-toggler-icon"></span> </button>
				<div class="collapse navbar-collapse" id="collapsibleNavbar">
					<ul class="navbar-nav">
						<s:iterator value="%{#session.MenuList}" var="menuList" status="stat">
							<s:if test='%{PARENT_MENU==null && !"99999".equals(MENU_ID) && !"Parent".equals(MENU_NAME)}'>
								<li class="nav-item"><a class="nav-link" href="<s:property value="MENU_URL"/>"><s:property value="MENU_NAME" /></a></li>
							</s:if>
							 <s:elseif test='PARENT_MENU=="99999"'>
				           		 <li class="nav-item dropdown">
				                   <a class="nav-link dropdown-toggle" id="navbardrop" href="<s:property value="MENU_URL"/>"><s:property value="MENU_NAME"/></a>
				                     <div class="dropdown-menu p-0 dropdownmenus">
					                   <ul style="padding: 0px;">
						                   <s:iterator value="%{#session.MenuList}" var="menuSubList" status="stat">
						                       <s:if test="#menuSubList.PARENT_MENU == #menuList.MENU_ID">
							                       <s:if test='MENU_ID!="79" && MENU_ID!="80"'>
							                           <li>
							                               <a href="<s:property value="MENU_URL"/>" /><s:property value="MENU_NAME"/></a>
							                           </li>
						                           </s:if>
						                       </s:if>
						                   </s:iterator>
						               </ul>  
					               </div>                
				              	</li>
				           </s:elseif>
				            <s:elseif test='PARENT_MENU==null'>
				           		<li class="nav-item">
				                   <a class="nav-link" href="<s:property value="MENU_URL"/>" /><s:property value="MENU_NAME"/></a>
				               </li>
		          			 </s:elseif> 
						</s:iterator>
					</ul>
				</div>
			</nav>
		</div>
	</div>
</body>
</html>