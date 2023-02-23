<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>Cargo Pass</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="<%=request.getContextPath() %>/css/style.css" rel="stylesheet" type="text/css">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    <s:form action="getCommoditiesAdmin" name="commodities" id="commodities" theme="simple">
    <table width="100%" border="0" cellspacing="0" cellpadding="5">
         <tr>
     		<td  style="color:red;"><s:actionerror/></td>
         </tr>
         <tr>
         </tr>
         <tr style="padding-top: 20px;">
            <td colspan="2" align="left">&nbsp;&nbsp;&nbsp;&nbsp;<span class="heading"><s:property value='%{"commodities".equalsIgnoreCase(clausesType)?"Choose Commodities":"Choose Covers"}'/> </span> </td>
         </tr>
	</table>
    <table width="95%"  border="1" cellspacing="1" cellpadding="0" class="" align='center'>
        <tr>
		    <td>
               <table width="100%"  border="0" cellspacing="0" cellpadding="3" >
				   <s:iterator value="commodityList" status="stat" var="commodities">
			     	   <tr>
			 	           <td>
			 	              <s:checkbox name="commodities[%{#stat.index+1}]" id="checkbox%{#commodities.CODE}" fieldValue="%{#commodities.CODE}" />
			 	           </td>
			 	           <td width="90%">
			 	              <s:property value="%{#commodities.CODE_DESC}"/>
			 	           </td> 
				      </tr>
				   </s:iterator>
			   </table>
			</td>
	   </tr>
	  </table>
	  <table align="right">
		  <tr>
			  <td align="center" class="">
			      <a href="javascript:window.close()"  class="buttonsMenu" >
					 <img src="${pageContext.request.contextPath}/images/Cancel.jpg" ></a>
			  </td>
			  <td>&nbsp;</td>
				 <td align="center" class="">
				    <a onClick="getSubmit('proceed')" class="buttonsMenu" >
					   <img src="${pageContext.request.contextPath}/images/Proceed.jpg"> </a>
		      </td>
			  <td>&nbsp;</td>
		  </tr>
	  </table>
	  <s:hidden name="clauseId"/>
	 </s:form>
  </body>
  <SCRIPT type="text/javascript">
  if('commodities'=='<s:property value="clausesType" />'){
	  if(window.opener.document.getElementById('commodities<s:property value="clauseId" />').value.length>=1){
		var selectedPack=window.opener.document.getElementById('commodities<s:property value="clauseId" />').value;
		var elements=selectedPack.split('~');
	    for(i=1; i<elements.length-1; i++){
	      document.getElementById('checkbox'+elements[i]).checked=true;
	    }
	  }
  }else{
     if(window.opener.document.getElementById('covers<s:property value="clauseId" />').value.length>=1){
		var selectedPack=window.opener.document.getElementById('covers<s:property value="clauseId" />').value;
		var elements=selectedPack.split('~');
	    for(i=0; i<elements.length; i++){
	      document.getElementById('checkbox'+elements[i]).checked=true;
	    }
	  }
  }
  function getSubmit(val){
	    var selectedPack="";
	    if(val=='proceed'){
	    var c = document.getElementById('commodities').getElementsByTagName('input');
	    for (var i = 0; i < c.length; i++) {
	        if (c[i].type == 'checkbox') {
	            if(c[i].checked){
	              selectedPack= selectedPack +"~" +c[i].value;
	            }
	        }
	     }
         if('commodities'=='<s:property value="clausesType" />'){
            window.opener.document.getElementById('commodities<s:property value="clauseId" />').value=selectedPack+'~';
	     }else{
	        window.opener.document.getElementById('covers<s:property value="clauseId" />').value=selectedPack+'~';
	     }
	     window.close(); 
	 
     }else{
      window.close();
    } 
  }
  </SCRIPT>
</html>
