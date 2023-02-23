<%@ include file="submenu.jsp"%>

<%@ page import="java.util.*" %>
<%!
int i=0;
String wsrcc =null;
String proposalNo = null;


%>
<%
   String pathq = request.getContextPath();
   String basePaths = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+pathq+"/";
%>
<jsp:useBean id="theBean" class="com.maan.opencover.ConditionsOpenCover">
<jsp:setProperty name="theBean" property="*"/>
</jsp:useBean>
<%

wsrcc = (String)session.getAttribute("coverName");

if(wsrcc!= null && wsrcc.trim().indexOf("BY LAND OR SEA OR AIR") != -1)
{
	wsrcc = wsrcc.replaceAll("BY LAND OR SEA OR AIR","MULTI MODE");
} 
int sea=0, air=0,road = 0, multimode=0;

proposalNo = request.getParameter("proposalNo")==null?(String)
session.getAttribute("proposalNo"): (String)request.getAttribute("proposalNo");

int n = 1;

/*air = wsrcc.lastIndexOf("AIR");
road = wsrcc.lastIndexOf("ROAD");
sea = wsrcc.lastIndexOf("SEA");
multimode = wsrcc.lastIndexOf("MULTI MODE");
if(air>0)
  {
    air=2;
  }
 else
   {
   air = 101;
   }
if(sea>0)
  {
    sea = 1;
  }
  else
    {
      sea = 101;
      }
if(multimode>0)
  {
    multimode = 4;
  }
else
{
   multimode = 101;
}
if(road>0)
  {
  road = 0;
  }*/
  String extraCoverIds=theBean.getExtraCoverIds(proposalNo, (String) session.getAttribute("AdminBranchCode"));
  theBean.setProposalNo(proposalNo);
  //String[][] commodities= theBean.getWsrccFromOpenCoverMasterResult(extraCoverIds);
  String[][] commodities= theBean.getWsrccFromOpenCoverMasterResult(extraCoverIds);

%>
<html>
<script>

</script>
    <head>
        <title>Madison General Insurance</title>
        <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
        <link href="../css/footable-0.1.css" rel="stylesheet" type="text/css">
         <link href="../css/style.css" rel="stylesheet" type="text/css">
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
           <table width="100%"  border="0" cellspacing="0" cellpadding="0">
            <tr>
                <td align = "middle" class='heading'>&nbsp;</td>
              <td class='heading'>&nbsp;<strong> WSRCC Details </strong></td>
            </tr>
				 <tr>
                <td height="17" ></td>
              <td ></td>
            </tr>		
			<tr >
			<td colspan="2"> 
			<table width="95%"  border="0" cellspacing="1" cellpadding="0" class="" align='center'>
              <tr>		
			    <td align="left" class="ltbgyellow">			    
                          <table width="100%"  border="0" cellspacing="0" cellpadding="0" class="footable">
                           <tbody> 
						<%
						if(commodities.length > 0)
						  {
						
	for(int i=0;i<commodities.length;i++)
	{%>
							<tr>
								
								<td align="Left" valign = "top" height = "40" ><%=n%></td><td valign = "top" height = "40"><%=commodities[i][1]%></td>
															</tr>
	<%
	n++;}
	}
	else
	  {%>
	  <tr>
	  <td align="Left" valign = "top" height = "40" ></td><td valign = "top" height = "40"><span color = 'red'><b> No warranties for Commodity</b></span></td>
	  </tr>
	 <% }
	  
	
	%>
		</tbody>
		</table>
		</td></tr>
		<tr>
			
			<td height="32" align="right" valign="middle" class="text">  
				<input type="hidden" name="totalLength" value="<%=commodities.length%>">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
              <br>
	      	      				  <table width="176" border="0" align="center" cellpadding="0" cellspacing="0">
	      	      						  						
	      	      			 <td width="46">
							 <a href= "#" onClick='window.close()' >
							 <img src="<%=pathq%>/images/Close.jpg" >			</td>
	      	      						  					
	      	      	
	      	      						  								
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

