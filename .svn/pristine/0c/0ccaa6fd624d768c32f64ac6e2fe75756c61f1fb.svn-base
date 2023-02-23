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
	 <div class="navbarContainer">
      <div class="row">
        <div class="col-md-2 col-12">
          <s:if test="#session.user1==getText('admin')">
				<%-- <a href="${pageContext.request.contextPath}/homeAdmin.action">
					<img src="${pageContext.request.contextPath}/images/mgen-logo.png" border="0" >
				</a> --%>
				<a href="${pageContext.request.contextPath}/HomeUser.action">
					<img src="${pageContext.request.contextPath}/images/mgen-logo.png" border="0" >
				</a>
			</s:if>
			<s:else>
				<a href="${pageContext.request.contextPath}/HomeUser.action">
					<img src="${pageContext.request.contextPath}/images/mgen-logo.png" border="0" >
				</a>
			</s:else>
        </div>
        <div class="col-md-4 col-12 offset-md-6 leftsidecontents">
          <ul class="list-group ml-5">
            <%-- <li class="list-group-item heading-brand">
	           <font color="#dca531"> 
		           <s:if test='"Live".equalsIgnoreCase(#session.userLoginMode)'>
		              <h3>MGen-Online</h3>
	               </s:if>
	               <s:else>
	              	 <h3>MGen-Test</h3>
	               </s:else>
              </font>
            </li> --%>
            <li class="list-group-item numbersEmail">
              <span><i class="fas fa-mobile-alt"> </i> 4848 | 378700-5 | </span>
              <span><i class="fas fa-envelope"></i> <a href="mailto:online@madison.co.zm?Subject=Insure" target="_top" style="color: #DBA832;">online@madison.co.zm</a></span>
            </li>
            <li class="list-group-item loginuser">
            
            	<span><i class="fas fa-home"></i> <a href="${pageContext.request.contextPath}/HomeUser.action">Home</a>&nbsp;&nbsp;|&nbsp;&nbsp;</span>
              	<s:if test='%{#session.LoginType=="B2C" && #session.user1!=getText("admin")}'>
              		<span><i class="fas fa-sign-out-alt"></i><a href="${pageContext.request.contextPath}/Loginlanding.action">Login In</a>&nbsp;&nbsp;&nbsp;&nbsp;</span>
              	</s:if>
              	<s:else>
              		<span><i class="fas fa-sign-out-alt"></i><a href="${pageContext.request.contextPath}/Loginout.action">Sign Out</a>&nbsp;&nbsp;&nbsp;&nbsp;</span>
              		<span><i class="fas fa-user-shield"></i> LoginId : <s:property value="#session.user"/></span>
              	</s:else>
            </li>
            <li class="list-group-item loginuser">
              <span><i class="fas fa-building"></i> Branch</span> :
              <span><s:property value="#session.branchName"/></span>
            </li>
            <li class="list-group-item insurancename">
            	<s:if test='#session.product_id=="33" || #session.product_id=="34"'>
					<h3><i class="fa fa-plane"></i>&nbsp;<s:text name="Travel Insurance"/></h3>
				</s:if>
				<s:elseif test='#session.product_id=="41"'>
					<h3><i class="fa fa-heartbeat"></i>&nbsp;<s:text name="Health Insurance"/></h3>
				</s:elseif>
				<s:elseif test='#session.product_id=="65"'>
					<h3><i class="fa fa-car"></i>&nbsp;<s:text name="Motor Insurance"/></h3>
				</s:elseif>
				<s:elseif test='#session.product_id=="30"'>
					<h3><i class="fa fa-home"></i>&nbsp;<s:text name="Non Motor Insurance"/></h3>
				</s:elseif>
				<s:elseif test='#session.product_id=="3" || #session.product_id=="11"'>
					<h3><i class="fa fa-ship"></i>&nbsp;<s:text name="Marine Insurance"/></h3>
				</s:elseif>
            </li>
          </ul>
        </div>
      </div>
    </div>

</body>

<script>
var $theWindowSize = $(this).width();
if ($theWindowSize > 300) {
 $(".leftsidecontents").removeClass("offset-7")
} if($theWindowSize  > 600){
  $(".leftsidecontents").addClass("offset-7")
}
$(window).resize(function () {
var $theWindowSize = $(this).width();
if ($theWindowSize > 300) {
 $(".leftsidecontents").removeClass("offset-7")
} if($theWindowSize  > 600){
  $(".leftsidecontents").addClass("offset-7")
}

});
</script>
</html>
