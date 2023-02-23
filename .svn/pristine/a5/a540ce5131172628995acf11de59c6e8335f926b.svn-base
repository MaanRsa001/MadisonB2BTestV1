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
    
    <title>My JSP 'others.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link href="<%=path%>/css/style.css" rel="stylesheet" type="text/css">
<link href="<%=path%>/css/footable-0.1.css" rel="stylesheet" type="text/css">
   <script language="JavaScript" src="${pageContext.request.contextPath}/css/calendar1.js"></script>
  </head>
  
  <body>
    <s:form action="getOthersAdmin" name="others" theme="simple">
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
         <tr>
     		<td  style="color:red;"><s:actionerror/></td>
         </tr>
         <tr>
         
         </tr>
         <tr>
            <td colspan="2" align="left">&nbsp;&nbsp;&nbsp;&nbsp;<span class="heading">Optional Cover Entries</span> </td>
         </tr>
	</table>
    <s:if test='"first".equals(from1)'>
    <table width="95%"  border="1" cellspacing="1" cellpadding="0" class="" align='center'>
        <tr>
		    <td>
               <table width="100%"  border="0" cellspacing="0" cellpadding="0" class="footable">
               	<tbody>
				   <s:iterator value="optionalCoverList" status="stat" var="covers">
			     	   <tr>
			 	           <td width="10%">
			 	              <s:checkbox name="optionalId[%{#stat.count-1}]" id="checkbox%{#covers.RSACODE}" fieldValue="%{#covers.RSACODE}" />
			 	           </td>
			 	           <td width="90%">
			 	              <s:textarea name="optionalDesc[%{#stat.count-1}]" rows="3" cols="75" value="%{#covers.CLAUSES_DESCRIPTION}"></s:textarea>
			 	           </td> 
				      </tr>
				   </s:iterator>
				   <tr>
				     <td colspan="2" align="right">
				        Effective Date<s:textfield name="effectDate" /><a href="javascript:cal6.popup();">
                        <img src="${pageContext.request.contextPath}/images/cal.gif" width="16" height="16" border="0" title="Click Here Pick up the date"></a><br>
				        
				     </td>
				   </tr>
				  </tbody>
			   </table>
			</td>
	   </tr>
	  </table>
	  </s:if>
	  <s:elseif test='"second".equals(from1)'>
	  <s:if test='selectedCovers.size()>0'>
	  <table>
	    <tr>
	     <td><b>Sno &nbsp;&nbsp;&nbsp;Opted Covers</b></td>
	    </tr>
	    <s:iterator value="selectedCovers" status="stat" var="covers">
	     <tr>
	        <td>
	           <b><s:property value="%{#stat.index+1}"/></b> &nbsp;&nbsp;&nbsp; <s:property value="%{#covers.CLAUSES_DESCRIPTION}"/>
	        </td> 
	     </tr> 
	    </s:iterator>   
      </table>	
      </s:if>
      <s:else>
      <table>
	    <tr>
	     <td><b>No Records Inserted</b></td>
	    </tr>
	    </table>
      </s:else>     
	  </s:elseif>
	  <s:hidden name="from" /> 
	  <s:hidden name="coverNo" />
	  <table align="center" width="100%">
		  <tr>
			  <td align="center" class="">
			      <a href="javascript:window.close()"  class="buttonsMenu" >
					 <img src="${pageContext.request.contextPath}/images/Back.jpg" ></a>
					 &nbsp;&nbsp;&nbsp;
				<a onClick="getSubmit('proceed')" class="buttonsMenu" >
					   <img src="${pageContext.request.contextPath}/images/Proceed.jpg"> </a>
			  </td>			  
		  </tr>
	  </table>
	 </s:form>
  </body>
  <SCRIPT type="text/javascript">
  <s:iterator value="selectedCovers" status="stat" var="covers">
      try{
         document.getElementById('checkbox<s:property value="%{#covers.RSACODE}"/>').checked=true;
      }catch(err){}
   </s:iterator> 
  <s:if test='"first".equals(from1)'>
	  var cal6 = new calendar1(document.forms['others'].elements['effectDate']);
	  cal6.year_scroll = true;
	  cal6.time_comp = false;
  </s:if>
  function getSubmit(val){
    if(val=='proceed' && 'first'=='<s:property value="from1" />'){
       document.others.from.value='proceed';
       document.others.submit();
    }else{
      window.close();
    } 
  }
  </SCRIPT>
</html>
