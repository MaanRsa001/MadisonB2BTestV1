<link href="../css/style.css" rel="stylesheet" type="text/css">
<%@ page import="java.util.*" %>
<jsp:useBean id="theBean" class="com.maan.opencover.bean.opencoverSummary">
<jsp:setProperty name="theBean" property="*"/>
</jsp:useBean>
<%
String proposalNo="";

proposalNo = request.getParameter("proposalNo")==null?(String)
session.getAttribute("proposalNo"): (String)request.getAttribute("proposalNo");
String reqFrom=request.getParameter("reqFrom")==null?"":request.getParameter("reqFrom");
int n = 1;
int flag = 0;

  String[][] commodities= theBean.getFreeText(proposalNo,request.getParameter("coverNo"),reqFrom);

%>
<%
   String pathq = request.getContextPath();
   String basePaths = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+pathq+"/";
%>
<html>
<script>

</script>
    <head>
        <title>Madison General Insurance</title>
        <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
        <link href="/css/style.css" rel="stylesheet" type="text/css">
        <style type="text/css">
            <!--
#Layer1 {
	position:absolute;
	width:200px;
	height:115px;
	z-index:1;
	left: 128px;
	top: 284px;
}
            -->
        </style>
    </head>
    <body>
   	

     <form name="wsrcc" method="post">
   
	

	
	<br>
           <table width="100%"  border="0" cellspacing="0" cellpadding="0" style="align:justify; font-family:Arial;font-size:12px;font-weight:normal;">
            <tr>
                <td align = "center">&nbsp;</td>
              <td>&nbsp;<strong><span class='heading'> FreeText Details </span></strong></td>
            </tr>
				 <tr>
                <td height="17" ></td>
              <td ></td>
            </tr>		
			<tr >
			<td colspan="2"> 
			<table width="95%"  border="1" cellspacing="1" cellpadding="0" class="" align='center'>
              <tr>		
			    <td align="left" class="ltbgyellow">
			    <div  STYLE="overflow: auto; width:100%; height:450; border-left: 0px gray solid;padding:1px; padding-left:5px; margin: 1px">
                          <table width="100%"  border="0" cellspacing="0" cellpadding="0" >
                            
						   <tr align="left">
                            <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                           </td>
						   <td></td>
                            </tr>
						<%
					if(commodities.length > 0)
												  {
												
							for(int i=0;i<commodities.length;i++)
							{
							  if(!"9999".equals(commodities[i][1]))
							     {%>
													<tr>
														
														<td align="Left" valign = "top" height = "40" ><font size = '2'><%=n%></td><td valign = "top" height = "40"><font size = '2'> <%=commodities[i][1]%></td>
																					</tr>
							<%
							n++;
							}
							else
							  {flag = 1;}
							}
							}
							else
							  { flag = 1; %>
							 
							 <% }
							 
							 if(flag == 1)
							  {%>
							   <td align="Left" valign = "top" height = "40" ><font size = '2'></td><td valign = "top" height = "40"><font size = '2' color = 'red'><b> No FreeText Available<b></td>
							  
							
	<%}%>
		</table>
		</div>
		</td></tr>
		<tr>
			
			<td height="32" align="right" valign="middle" class="text">  
				<input type="hidden" name="totalLength" value="<%=commodities.length%>">
				<input type="hidden" name="coverNo" value="<%=request.getParameter("coverNo")%>">

				<!--<input type="image"  src="../images/btn_cancel.gif"   onClick="back()" tabindex=0 accesskey='c'>-->	
			<!--	<input type="button"  value="ADD CLAUSES"   onClick="return addText()">	-->
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<!--<input type="image"  src="../images/button_submit.gif"   onClick="window.close()" tabindex=0 accesskey='p'>	
				
				&nbsp;&nbsp;&nbsp;</td>
              </tr>-->
              <br>
	      	      	      	      				  <table width="176" border="0" align="center" cellpadding="0" cellspacing="0">
	      	      	      	      				  
	      	      	      	      				  <td width="46" valign="middle" class=""><a href= "#" onClick='history.go(-1)' class="buttonsMenu" >
												  <img src="<%=pathq%>/images/Back.jpg">	</a> 		</td>
	      	      	      	      				
	      	      	      	      						  						
	      	      	      	      			 <td width="46" valign="middle" class=""><a href= "#" onClick='window.close()' class="buttonsMenu" >
											 <img src="<%=pathq%>/images/Close.jpg"  >		</a>	</td>
	      	      	      	      						  					
	      	      	      	      	
	      	      	      	      						  								
	      	      		                          </table>
            </table>
			  </td>
            </tr>
            <tr>
              <td colspan="2" >&nbsp;</td>
			  <input type="hidden" name="coverName" value="<%=request.getParameter("coverName")%>">
			  <input type="hidden" name="n" value="<%=n%>">
            </tr>
          </table>
        </form>
    </body>
</html>

<script>
function back()
{
	document.wsrcc.action="showCoverages.jsp?id=1";
	document.wsrcc.submit();
	return true;
}
function addText()
{
	
	
	document.wsrcc.action="showClausesEdit.jsp?input=new";
	document.wsrcc.submit();
	return true;
}
				
</script>
