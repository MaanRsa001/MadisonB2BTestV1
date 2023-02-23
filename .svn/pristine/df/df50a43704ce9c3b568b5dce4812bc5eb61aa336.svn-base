<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ include file="../login/sessionsCheckNormal.jsp" %>
<%@ include file="/pages/sessionCheckAdmin.jsp" %>
<%@ page import="java.util.*,java.io.*,java.text.*" %>
<jsp:useBean id="RoyalAdmin" class="com.maan.admin.BrokerCreationBean">
<jsp:setProperty name="RoyalAdmin" property="out" value="<%= response.getWriter() %>" /> 
</jsp:useBean>
<jsp:useBean id="royalAdminBean" class="com.maan.admin.AdminBean">
</jsp:useBean>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta name="description" content="">
	<meta name="keywords" content="Maan Sarovar, Sea Trans, Marine Insurance">	
	<meta name="author" content="">	
	<title>::: Madiosn General - Admin :::</title>
	<link rel="shortcut icon" href="<%=request.getContextPath()%>/images/favicon.ico" />
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/css/demo.css" />
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/css/font-awesome.min.css" />
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/css/bootstrap-multiselect.css" />
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/bootstrap/css/datepicker3.css">	 
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/bootstrap/css/slimmenu.css">
	<link href="${pageContext.request.contextPath}/bootstrap/css/footable-0.1.css" rel="stylesheet" type="text/css">
	<link href="${pageContext.request.contextPath}/bootstrap/css/table-res.css" rel="stylesheet" type="text/css">
	<link href="${pageContext.request.contextPath}/bootstrap/css/easy-responsive-tabs.css" rel="stylesheet" type="text/css">
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css" />
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/css/style.css" />
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js"></script>
	<script src="${pageContext.request.contextPath}/bootstrap/js/modernizr.custom.63321.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/bootstrap/js/jquery-1.9.1.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/bootstrap/js/jquery-1.9.1.min.js"></script>
	<style type="text/css">
	.panel-heading {
		padding: 5px;
	}
	.header {
		text-align: left;
	}
	</style>
</head>
<body>
	<%--
	<s:url forceAddSchemeHostAndPort="true" includeParams="all" var="myurl" escapeAmp="false" encode="false">
		<s:param name="request_locale"/>
	</s:url>
	
	<div class="layoutHeader">
		<div class="pullLeft">
			<img src="${pageContext.request.contextPath}/images/mgen-logo.png" border="0" >
		</div>
		<div class="pullRight" style="color: #000000;">
			<font color="red" style="font: bold;font-size: 30px;"><s:property value="#session.userLoginMode"/>&nbsp;&nbsp;Environment</font> <br/>
			<span style="font-size: 12px;">
				<a href="${pageContext.request.contextPath}/HomeUser.action">Home</a>&nbsp;&nbsp;|&nbsp;&nbsp;
					<a href="${pageContext.request.contextPath}/Loginout.action">Sign Out</a>&nbsp;&nbsp;&nbsp;&nbsp;
			 	<font style="font-weight: bold;color:#000000"> LoginId&nbsp;:&nbsp;&nbsp;<s:property value="#session.user"/></font>
			</span>
		</div>
		<br class="clear"/>
		<div>
			<ul class="slimmenu">
		      <s:iterator value="%{#session.MenuList}" var="menuList" status="stat">
		           <s:if test='%{PARENT_MENU==null && !"99999".equals(MENU_ID) && !"Parent".equals(MENU_NAME)}'>
		               <li>
		                   <a href="<s:property value="MENU_URL"/>" /><s:property value="MENU_NAME"/></a>
		               </li>
		           </s:if>
		           <s:elseif test='PARENT_MENU=="99999"'>
		           		 <li>
		                   <a href="<s:property value="MENU_URL"/>"><s:property value="MENU_NAME"/></a> 
		                   <ul style="padding: 0px;">
			                   <s:iterator value="%{#session.MenuList}" var="menuSubList" status="stat">
			                       <s:if test="#menuSubList.PARENT_MENU == #menuList.MENU_ID">
			                           <li>
			                               <a href="<s:property value="MENU_URL"/>" /><s:property value="MENU_NAME"/></a>
			                           </li>
			                       </s:if>
			                   </s:iterator>
			               </ul>                  
		              	</li>
		           </s:elseif>
		           <s:elseif test='PARENT_MENU==null'>
		           		<li>
		                   <a href="<s:property value="MENU_URL"/>" /><s:property value="MENU_NAME"/></a>
		               </li>
		           </s:elseif> 	       
		       </s:iterator>
		  	 </ul>
		</div>
	</div>
	--%>
	<div class="layoutHeader">
		<div class="pullLeft">
			<img src="${pageContext.request.contextPath}/images/mgen-logo.png" border="0" >
		</div>
		<div class="pullRight" style="color: #000000;">
			<font color="red" style="font: bold;font-size: 30px;">
				<%=session.getAttribute("userLoginMode")%>
				&nbsp;&nbsp;Environment</font> <br/>
			<span style="font-size: 12px;">
				<a href="${pageContext.request.contextPath}/HomeUser.action">Home</a>&nbsp;&nbsp;|&nbsp;&nbsp;
					<a href="${pageContext.request.contextPath}/Loginout.action">Sign Out</a>&nbsp;&nbsp;&nbsp;&nbsp;
			 	<font style="font-weight: bold;color:#000000"> LoginId&nbsp;:&nbsp;&nbsp;<%=session.getAttribute("user")%></font>
			</span>
		</div>
		<br class="clear"/>
		<div>
			<ul class="slimmenu">
				<%
					List<Map<String,Object>> menuList = (List<Map<String,Object>>) session.getAttribute("MenuList");
					for(int i=0 ; i<menuList.size() ; i++ ) {
						Map<String,Object> menuMap = menuList.get(i);
						String parentMenuId = menuMap.get("PARENT_MENU")==null?"":menuMap.get("PARENT_MENU").toString();
						String menuId = menuMap.get("MENU_ID")==null?"":menuMap.get("MENU_ID").toString();
						String menuName = menuMap.get("MENU_NAME")==null?"":menuMap.get("MENU_NAME").toString();
						String menuUrl = menuMap.get("MENU_URL")==null?"":menuMap.get("MENU_URL").toString();
						if("".equals(parentMenuId) && !"99999".equalsIgnoreCase(menuId) && !"Parent".equalsIgnoreCase(menuName)) {
				%>
		               <li>
		                   <a href="<%=menuUrl%>" ><%=menuName%></a>
		               </li>
		           	<% } else if("99999".equalsIgnoreCase(parentMenuId)) { %>
		           		 <li>
		                   <a href="<%=menuUrl%>"><%=menuName%></a> 
		                   <ul style="padding: 0px;">
		                   		<% 
		                   			List<Map<String,Object>> subMenuList = (List<Map<String,Object>>) session.getAttribute("MenuList");
		                   			for(int j=0 ; j <subMenuList.size() ; j++) {
		                   				Map<String,Object> subMenuMap = subMenuList.get(j);
		                   				String sparentMenuId = subMenuMap.get("PARENT_MENU")==null?"":subMenuMap.get("PARENT_MENU").toString();
		                   				String smenuName = subMenuMap.get("MENU_NAME")==null?"":subMenuMap.get("MENU_NAME").toString();
		        						String smenuUrl = subMenuMap.get("MENU_URL")==null?"":subMenuMap.get("MENU_URL").toString();
		                   				if(sparentMenuId.equalsIgnoreCase(menuId)) {
		                   		%>
			                           <li>
			                               <a href="<%=smenuUrl%>" ><%=smenuName%></a>
			                           </li>
				                   <%
		                   				}
				                   } 
				                   %>
			               </ul>                  
		              	</li>
		          	<% } else if("".equalsIgnoreCase(parentMenuId)) { %>
		           		<li>
		                   <a href="<%=menuUrl%>" ><%=menuName%></a>
		               </li>
		       <%
		          	}
		       } 
		       %>
		  	 </ul>
		</div>
	</div>
</body>
<% String usertype123=(String)session.getAttribute("usertype");

/***Garbage***/

	int totalmemory = (int)Runtime.getRuntime().totalMemory();
    int freememory = (int)Runtime.getRuntime().freeMemory();
	int totalUsedBefore = totalmemory - freememory;
	int garbageMemory;
	garbageMemory = totalUsedBefore/(1024*1024);

	try 
	{   
		DateFormat fmt = new SimpleDateFormat("hh:mm:ss aa");
	    String currenttime = fmt.format(new java.util.Date());
		String time ="current time is "+currenttime;
		String pat = getServletContext().getRealPath("/");
		String nameOfTextFile = pat+"GarbageReport.txt";
		String str  = "Admin Garbage Collection Total Memory is "+(totalmemory/(1024*1024))+"MB \n";
		String str1 = str+"Garbage Collection Free Memory is "+(freememory/(1024*1024))+"MB \n";
		String str2 = str1+"Garbage Collection Total Used Memory is "+garbageMemory+"MB  "+time+"\n";
		String str3= " ";
		if( (totalmemory /(1024*1024))>60)
		{
			str3 = "Admin Garbage Collector Calling Here....Becoz Total Memory Is "+(totalmemory/(1024*1024))+"MB \n";
			System.gc();
		}
		str3 = str2 + str3;
		//System.out.println("File Path "+nameOfTextFile);
		PrintWriter pw = new PrintWriter(new FileOutputStream(nameOfTextFile,true));
	    pw.println(str3);
        pw.close();
	}
	catch(IOException e) 
	{
	   out.println(e.getMessage());
	}

	/***Garbage***/

String adminloginPersonId=request.getParameter("user")==null?"":request.getParameter("user");
if(adminloginPersonId.equals(""))
{
	adminloginPersonId=(String)session.getAttribute("user");
}
session.setAttribute("loginPersonId",adminloginPersonId);
String bracnAdmins[][] = new String[0][0];
String countryAdmins = "";
String productsAdmins = "";
bracnAdmins = RoyalAdmin.getAdminBranch(adminloginPersonId);
countryAdmins = RoyalAdmin.getAdminCountry(adminloginPersonId);
productsAdmins = RoyalAdmin.getAdminProductId(adminloginPersonId);


String braName="";
String braCode="";
String actualBranch="";
if(bracnAdmins.length>0)
{
	if(bracnAdmins.length==1)
	{
		braName = bracnAdmins[0][1]!=null?bracnAdmins[0][1]:"";
		braCode = "'"+bracnAdmins[0][0]+"'";
		actualBranch = "'"+bracnAdmins[0][2]+"'";
		braName = braName + " Branch";
	}
	else
	{
		for(int b=0;b<bracnAdmins.length;b++)
		{
			braName = braName +bracnAdmins[b][1] + ", ";
			braCode = braCode+"'"+bracnAdmins[b][0]+"',";
		}
		braName = braName.substring(0,braName.length()-2);
		braCode = braCode.substring(0,braCode.length()-1);
		braName = braName + " Branches";
	}
}
//Royal World WOrk
java.util.HashMap brokerDetails = new java.util.HashMap();
String brokerBra = braCode;
if(brokerBra.indexOf("'")!=-1)
	brokerBra = brokerBra.replaceAll("'","");
if(actualBranch.indexOf("'")!=-1)
	actualBranch = actualBranch.replaceAll("'","");
brokerDetails = RoyalAdmin.getBrokerUserDetails(actualBranch);
session.setAttribute("LoginBranchCode",brokerBra);//This is defaulted Admin Branch code 01
session.setAttribute("BrokerDetails",brokerDetails);
//For Home
session.setAttribute("adminBranch",actualBranch);//This is actual admin Branch code means corresponding admin branch code
session.setAttribute("AdminBranchCode",braCode);//This is defaulted Admin Branch code 01
session.setAttribute("AdminCountryId",countryAdmins);
session.setAttribute("AdminPid",productsAdmins);
session.setAttribute("branchName",braName);
		
		String adminProductId="";
		adminProductId = (String)session.getAttribute("pro_Id");
		adminProductId = adminProductId == null ? "" : adminProductId;
		if(adminProductId.length()<=0)
		{
			adminProductId = royalAdminBean.getLoginProIds(adminloginPersonId); 
		}
		session.setAttribute("pro_Id",adminProductId);
%>
<script type="text/javascript" src="${pageContext.request.contextPath}/bootstrap/js/jquery.slimmenu.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/bootstrap/js/jquery.easing.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/bootstrap/js/easy-responsive-tabs.js"></script>	
<script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap-datepicker.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap-multiselect.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap-3.2.0.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/bootstrap/js/app-js.js" type="text/javascript"></script>
<script type="text/javascript">
function setLang(obj, url, lang) {
	url=url.replace('request_locale=', 'request_locale='+lang);
	obj.href=url;
}
</script>