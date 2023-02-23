<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ page import="java.util.*" %>
<%//@ page import="java.util.Hashtable" %>
<%@ include file="home1.jsp" %>

<jsp:useBean id="ct" class = "com.maan.opencover.bean.opencoverSummary">
<jsp:setProperty property="*" name="ct"/>
</jsp:useBean>

<%
boolean flag	=	false;
int tt=0, tt1=0;
String values="";
boolean fragileFlag	=	false;
String commName_S="",desc_S="",checkStatus="unchecked";
String reqFrom=request.getParameter("reqFrom")==null?"":request.getParameter("reqFrom");
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
int k=0;
		String adminBranch = (String) session.getAttribute("AdminBranchCode");
		String belongingBranch = (String) session.getAttribute("BelongingBranch");
		//String mainbranch=((Map)session.getAttribute("BrokerDetails")).get("mainbranch").toString();
		if(adminBranch.indexOf("'")!=-1)
			adminBranch = adminBranch.replaceAll("'","");
		String cid = (String) session.getAttribute("AdminCountryId");
		String[][] showCoverages=null;
		showCoverages=ct.showCoveragesWithMode((String)session.getAttribute("proposalNo"),belongingBranch);
			String action="";
			if("1".equalsIgnoreCase(request.getParameter("id"))){
  				if("both".equalsIgnoreCase(reqFrom)){
    			action="show1Freetext.jsp?reqFrom=both";
  			}else{
    			action="showFreetext.jsp";
  			} 			
  			
}
else
	action="freetext.jsp";
%>
<html>
<script>
function controls()
{
	//alert('hai');
	document.clauses.action= '<%=path%>/premiumOpenCover/freetext.jsp';
	document.clauses.submit();
}
function getSubmit(){
var reqFrom='<%=reqFrom%>';
	if('others'==reqFrom){
		document.clauses.action='<%=path%>/'+'getOthersAdmin.action';
		document.clauses.submit();
	}else if('warranties'==reqFrom){
	    document.clauses.action='<%=path%>/premiumOpenCover/warrantiesShow.jsp';
	    document.clauses.submit();
	}else if('exclusions'==reqFrom){
	    document.clauses.action='<%=path%>/premiumOpenCover/exclusionShow.jsp';
	    document.clauses.submit();
	}
	else{
	  document.clauses.submit();
	}
}
</script>
    <head>
        <title>Madison General Insurance</title>
        <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
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

<script language="JavaScript" src="<%=path%>/css/calendar1.js"></script>
    </head>
    <body>
   		
     <form name="clauses" method="post" action="<%=action%>" >
    <BR>
     <CENTER>
     </CENTER>
	<br>
            <table width="100%"  border="0" cellspacing="0" cellpadding="0" style="align:justify; font-family:Arial;font-size:12px;font-weight:normal;">
            <tr class="" >
                <td >&nbsp;</td>
              <td >&nbsp;<strong><span class="heading"> <%if("others".equals(reqFrom)){ %>Optional Cover Entries <%}else if("freetext".equalsIgnoreCase(reqFrom)){ %> Free Text Entries <%} else if("warranties".equalsIgnoreCase(reqFrom)){ %> Warranty Entries <% } else if("exclusions".equalsIgnoreCase(reqFrom)){%>Exclusion Entries <%} %>  </span> </strong></td>
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
			    <div  STYLE=" width:100%; height:150; border-left: 0px gray solid;padding:1px; padding-left:5px; margin: 1px">
                          <table width="100%"  border="0" cellspacing="0" cellpadding="0" style="align:justify; font-family:Arial;font-size:12px;font-weight:normal;" >

						   <tr align="left">
                            <td colspan="2" align='left'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;                           </td>
                            </tr>
                            <tr class="">
                              <td  width="29%"  align="center" ></td>

							  <td  width="71%"  align="left"><strong><span class="heading">Coverage Selection For <%if("others".equals(reqFrom)){ %>Optional Cover Entries <%}else if("freetext".equalsIgnoreCase(reqFrom)){ %> Free Text Entries <%}else if("warranties".equalsIgnoreCase(reqFrom)){ %> Warranties <% } else if("exclusions".equalsIgnoreCase(reqFrom)){%> Exclusions <%} %>  </span></strong></td>
                            </tr>
							<tr>
								<td>&nbsp;&nbsp;</td>
								<td>&nbsp;&nbsp;</td>

							</tr>

							<tr align="center">
								<td align="right">&nbsp;</td>
								<td align="left">
								<%
								if(showCoverages.length==0)
								{%>

								<font color="red" size="2">There is no Coverages Available in SEA Tranport</font>
								<%}else{%>
								Select Coverages
								  <select  name="coverNo">
								<%
                                if("exclusions".equalsIgnoreCase(reqFrom)||"warranties".equalsIgnoreCase(reqFrom)){%>
                                  <option value="">ALL</option>
                               <% } else if("others".equalsIgnoreCase(reqFrom)) { %>
                            	   <option value="0">ALL</option>
                               <% }
                             	for(int i=0;i<showCoverages.length;i++)
								{
												
								%>
									<option value="<%=showCoverages[i][1]%>"><%=showCoverages[i][5]%></option>
								<%}
								String value="";
for(int i=0;i<showCoverages.length;i++)
{
	if(value.length()>1)
		break;
	//if(showCoverages!=null && showCoverages.length>0)
						//		{
									if(showCoverages[i][4].equalsIgnoreCase("1") && showCoverages[i][2]!=null)
	{
									StringTokenizer tokens=new StringTokenizer(showCoverages[i][2],",");
									while(tokens.hasMoreTokens())
		{
										value=tokens.nextToken();
										if("FCL".equalsIgnoreCase(value))
											values="101";
										else if("LCL".equalsIgnoreCase(value))
											values="102";
										else if("BREAK BULK".equalsIgnoreCase(value))
											values="103";
										else if("BULK".equalsIgnoreCase(value))
										values="104";
										else 
											values="0";
									
										if(Integer.parseInt(values)>100)
			{%>
<option value="<%=values%>"><%=value%></option>
								<%}}}}%>
								<%if("freetext".equalsIgnoreCase(reqFrom)){ %>
								<option value="105">WARRANTIES</option>
								<option value="106">EXCLUSIONS</option>
								<%} %>
								</select>		
								<%}%></td>
							</tr>
							<tr>
								<td>&nbsp;&nbsp;</td>
								<td>&nbsp;&nbsp;</td>

							</tr>
							<tr align="center">
							  <td align="right">&nbsp;</td>
							  <td align="left">
							<!--  <input border='0' name="image" type="image" src="../images/btn_cancel.gif"  onClick="window.close()" height="25" width="74">-->

							<table border="0" align="left" cellpadding="0" cellspacing="0">
			<tr> 
				 <td align="center" class="">
				 <a href="javascript:window.close()"  class="buttonsMenu" >
				 <img src="../images/Back.jpg"></a> </td>
				<td>&nbsp;</td>
							<%if(showCoverages.length!=0)
							{%>
							 <!-- <input name="image" accesskey="p"  src='<%=path%>/images/btn_proceed.gif'  accesskey="p" height="25" type="image">-->
					 <td align="center" class="">
					 <a href="#" onclick="getSubmit();" class="buttonsMenu" >
					 <img src="../images/Proceed.jpg"></a> </td>
						<td>&nbsp;</td>
							  <%}%>
							  </table>
							  </td>
						    </tr>
							</table>
                  </div>
					</td>
					</tr>
					</table>
                </td>
              </tr>
          </table>
          <input type="hidden" name="reqFrom" value="<%=reqFrom%>" />
     </form>
</body>
</html>

			