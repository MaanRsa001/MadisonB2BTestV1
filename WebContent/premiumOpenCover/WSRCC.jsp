<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page import="java.util.*" %>
<%@ include file="home1.jsp" %>

<jsp:useBean id="ct" class = "com.maan.opencover.ConditionsOpenCover">
<jsp:setProperty property="*" name="ct"/>
</jsp:useBean>
<%
	Calendar cal = Calendar.getInstance();
	java.util.Date today = cal.getTime();
	java.text.SimpleDateFormat fmt =new java.text.SimpleDateFormat("dd-MM-yyyy");
	String todayStr = fmt.format(today);

%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	String clauseValue = "";
	String wsrcc = (String)session.getAttribute("coverName");
	if(wsrcc!= null && wsrcc.trim().indexOf("BY LAND OR SEA OR AIR") != -1)
	{
		wsrcc = wsrcc.replaceAll("BY LAND OR SEA OR AIR","MULTI MODE");
	} 
	int sea=0, air=0,road = 0,multimode=0;
%>

<%
	
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
%>
<html>
    <head>
    <script type="text/javascript">
function controls()
{
	document.clauses.action='<%=path%>/premiumOpenCover/WSRCC.jsp';
	document.clauses.submit();
}

function formSubmit()
{
	if(window.opener.document.form1.chkProposalNo.value == document.clauses.chkProposalNo.value)
	{
		
		document.clauses.action="ConditionsController"
		document.clauses.submit();
	}
	else
	{
		alert("please close the WSRCC selection window and reopen again");
		window.close();
	}
}
function textLimit(field, maxlen) 
{
	if (field.value.length > maxlen + 1)
		alert('Character is Exceed Maximum Length!'+maxlen);
	if (field.value.length > maxlen)
		field.value = field.value.substring(0, maxlen);
} 
</script>    
        <title>Madison General Insurance</title>
        <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
        <link href="<%=request.getContextPath() %>/css/style.css" rel="stylesheet" type="text/css" />
        <link href="<%=request.getContextPath() %>/css/footable-0.1.css" rel="stylesheet" type="text/css" />
		<script language="JavaScript" src="<%=path%>/css/calendar1.js"></script>
    </head>
    <body>
  <%
		 String search=request.getParameter("search")==null?"":request.getParameter("search");
		 String bgcolor="";
		 String as="";
		 int l=0;
		 int matches=0;
  %>
     <form name="clauses" method="post" action="ConditionsController" >    
     <CENTER>

<%--<input type='text' name='search' value="<%=request.getParameter("search")==null?"":request.getParameter("search")%>"  >&nbsp;&nbsp;<input type='submit'  value='Search' onclick="controls()"  accesskey='s'>--%>

</CENTER>
<%
	
        String sessionId="";
        String companyId="1";
        String productId="0";
        String coverNo="0";
        String loginId="";
        String applicationNo="";
        String proposalNo="";
		String extraCoverIds="";
		//String mainbranch=((Map)session.getAttribute("BrokerDetails")).get("mainbranch").toString();
		String adminBranch = (String) session.getAttribute("AdminBranchCode");
		String belongingBranch = (String) session.getAttribute("BelongingBranch");
		if(adminBranch.indexOf("'")!=-1)
			adminBranch = adminBranch.replaceAll("'","");
		String cid = (String) session.getAttribute("AdminCountryId");
		String openCoverNo="";
		openCoverNo=(String) session.getAttribute("openCoverNo")==null?"0":(String) session.getAttribute("openCoverNo");
		productId=(String) session.getAttribute("product_id")==null?"0":(String) session.getAttribute("product_id");
        applicationNo=(String)session.getAttribute("applicationNo")==null?        (request.getParameter("applicationNo")==null?"0":request.getParameter("applicationNo")):
        (String)session.getAttribute("applicationNo");
		String effectDate=todayStr;
		String next=request.getParameter("next");
		sessionId=(String) session.getAttribute("ses")==null?"":(String) session.getAttribute("ses");
        loginId=(String) session.getAttribute("user")==null?"":(String) session.getAttribute("user");
		companyId=(String) session.getAttribute("company_id")==null?"":(String) session.getAttribute("company_id");
        productId=(String) session.getAttribute("product_id")==null?"":(String) session.getAttribute("product_id");
	    proposalNo = request.getParameter("proposalNo")==null?(String)session.getAttribute("proposalNo"):request.getParameter("proposalNo");
		String co = request.getParameter("coverNo");
		String co1 = (String)session.getAttribute("coverNo");
		coverNo = request.getParameter("coverNo")==null?(String)session.getAttribute("coverNo"):request.getParameter("coverNo");
		ct.setSessionId(sessionId);
        ct.setLoginCode(loginId);
        ct.setCompanyId(companyId);
        ct.setProductId(productId);
		ct.setOpenCoverNo(openCoverNo);
		ct.setProposalNo(proposalNo);
		ct.setCoverId(coverNo);
		ct.setModeOfTransport("1");
		
		String proposalDetails[][] = new String[0][0];
		proposalDetails = ct.getProposalNoDetails(proposalNo,belongingBranch);

        boolean isErrorPage=false;
        StringBuffer error=new StringBuffer();
        if(request.getAttribute("errorMessageClauses") !=null)
        {
	        error=(StringBuffer)request.getAttribute("errorMessageClauses");
		    isErrorPage=true;
        }
		String mode = "";
        String option =request.getParameter("mode");
        boolean isEditMode=false;
        if("edit".equals(option))
        {
            isEditMode=true;
        }

        HashMap conditionsList=new HashMap();
        HashMap coverList=new HashMap();
        HashMap conditionsIdsList=new HashMap();
        HashMap totalConditions=new HashMap();
        HashMap defaultIds=new HashMap();
        extraCoverIds=ct.getExtraCoverIds(proposalNo, adminBranch);
        totalConditions = ct.getWSRCCFromMaster(belongingBranch,extraCoverIds);
	    conditionsList = (HashMap)totalConditions.get("conditionsList")==null?conditionsList:(HashMap)totalConditions.get("conditionsList");
		defaultIds=(HashMap)totalConditions.get("DefaultList")==null?defaultIds:(HashMap)totalConditions.get("DefaultList");
		coverList = (HashMap)totalConditions.get("coverList")==null?coverList:(HashMap)totalConditions.get("coverList");
		conditionsIdsList = (HashMap)totalConditions.get("conditionsIdsList")==null?conditionsIdsList:(HashMap)totalConditions.get("conditionsIdsList");
		
        HashMap selectedAddons = new HashMap();
        HashMap totalSelectedAddons = new HashMap();

        if(isErrorPage)
        {
		    if(request.getAttribute("fullDetails") !=null)
			{
				selectedAddons=(HashMap)request.getAttribute("fullDetails");
	        }
        }
        else
        {
		    //if(session.getAttribute("fullDetailss") !=null)
	        //{
			selectedAddons = (HashMap)ct.getWsrccFromOpenCoverMaster(extraCoverIds,adminBranch);
	    }
        %>
            <table width="100%"  border="0" cellspacing="0" cellpadding="0" style="align:justify; font-family:Arial;font-size:12px;font-weight:normal;">
            <tr class="blueborder">
                <td>&nbsp;</td>
              <td>&nbsp;<strong> WSRCC </strong></td>
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
			    <%if(conditionsList.size()>0)
			{%>	
                          <table width="100%" class="footable">
                          <thead>
			              <tr>
			              <td width="5%"></td>
						  <td width = "20%"><div align="center"><strong>&nbsp;WSRCC&nbsp;ID&nbsp;&nbsp;&nbsp;</div> </strong></td>
						  <td width = "75%">WSRCC Description
						  	<input type="hidden" name="clausesSize" value="<%=conditionsList.size()%>" />
                            <input type="hidden" name="productId" value="<%=productId%>" />
                            <input type="hidden" name="loginId" value="<%=loginId%>" />
                            <input type="hidden" name="sessionId" value="<%=sessionId%>" />
                            <input type="hidden" name="companyId" value="<%=companyId%>" />
                            <input type="hidden" name="proposalNo" value="<%=proposalNo%>" />
                            <input type="hidden" name="chkProposalNo" value="<%=proposalNo%>" />
                            <input type="hidden" name="openCoverNo" value="<%=openCoverNo%>" />
                            <input type ="hidden" name ="coverNo" value = "<%=coverNo%>"/> 
						  </td>
			              </tr>
			              </thead>
                           <tbody> 
               
                          <!--  <tr class="LTyellowBG"> -->
 <%
				 String commName="";
				 String frag_ES="";
				 String dummyK="";
				 int dummyInt=0;
				 String dummyKName="";

				/*for(int kk=0;kk<conditionsIdsList.size();kk++)
				{
				 dummyK=(String)conditionsIdsList.get(""+(kk))==null?"0":(String)conditionsIdsList.get(""+(kk));
				 out.println("my Dummy K is "+dummyK);
				 dummyKName=(String)conditionsList.get(""+(dummyK))==null?"0":(String)conditionsList.get(""+(dummyK));
				 out.println("my Dummy Name K is "+dummyKName);
				}*/

		
			
			for(int r=0; r<proposalDetails.length;r++)
			{
				boolean coverFlag = false;
				for(int k=0;k<conditionsList.size();k++)
				{
					try{
						dummyK=(String)conditionsIdsList.get(""+(k))==null?"0":(String)conditionsIdsList.get(""+(k));
						dummyInt=Integer.parseInt(dummyK);
					}catch(Exception e){e.printStackTrace();}
					String ss = (String)conditionsIdsList.get(""+(k));
					String temp = (String)coverList.get("cover"+ss+"");
			%>
					<input type="hidden" name="clausesIdOrg<%=(k+1)%>" value="<%=dummyInt%>">
					<input type="hidden" name="coverId<%=(k+1)%>" value="<%=temp%>">
					
			<%
					commName=(String)conditionsList.get(""+(dummyInt))==null?"No Clauses":(String)conditionsList.get(""+(dummyInt));
					
					boolean flag = false;
					boolean fragileFlag	= false;
					
					String commName_S="",desc_S="",checkStatus="unchecked";

					String selectedCount=(String)selectedAddons.get("finalCount")==null?"0":(String)selectedAddons.get("finalCount");

					
			    try
				{
					 effectDate=todayStr;					
					if(Integer.parseInt(selectedCount)==0)
					{
					   mode = "fresh";								
					}
				}
				catch(Exception ex)
				{
					ex.printStackTrace();
				}
				 if ((selectedAddons.size()>0)&& (!"fresh".equalsIgnoreCase(mode)))
				 {
						//isEditMode=true;
						for(int tt=0;tt<Integer.parseInt(selectedCount);tt++)
						{
							//commName_S=(String)selectedAddons.get("clauses"+(tt+1));
							commName_S=(String)selectedAddons.get("clausesId"+(tt+1));
							try{
								if(((dummyInt)==Integer.parseInt(commName_S)))
								{
									flag	=	true;
									desc_S=(String)selectedAddons.get("description"+(tt+1));
								}
							}catch(Exception e) { System.out.println("clausesId...302"+e);e.printStackTrace();}
						}
					}
			%>
					<tr>
			<%
					if(flag)
					{
					System.out.println(" if part ");
						if(temp.equalsIgnoreCase(proposalDetails[r][1]))
						{	
							if(!coverFlag)
							{ 	
								coverFlag = true;
								clauseValue = request.getParameter("clauses"+(k+1))==null?"":request.getParameter("clauses"+(k+1));
								System.out.println( "clauseValue " + clauseValue + " commName " + commName);
					%>
					<tr> 
					<td colspan="3"> 
						<%=proposalDetails[r][2]%> <%= proposalDetails[r][3]%>
					</td>
					</tr>
			<%		
							}
			%>
					<tr>
					<td align="left" <%=bgcolor%>><%=k+1%>&nbsp;<input type="checkbox" name="clauses<%=(k+1)%>" 
					<%=isErrorPage?(clauseValue.equalsIgnoreCase(commName)?"checked":""):"checked"%> value="<%= commName %>" ></td>
					<td   align="left" width="20%" ><b>
					<div align="center"><%=(String)conditionsIdsList.get(""+(k))%></div></b>
					</td>
					<td  align="left"><textarea name="description<%=(k+1)%>" rows="4" cols="60" id="description<%=(k+1)%>" onkeyup="textLimit(this,2000)"><%=isErrorPage?(request.getParameter("description"+(k+1))==null?desc_S:request.getParameter("description"+(k+1))):desc_S%></textarea></td>
					
					<!--<input type="hidden" name="coverId<%=(k+1)%>" value="<%=temp%>"> -->
                    </tr>
			<%			}
		}
		else if(!"fresh".equalsIgnoreCase(mode))
		{
			System.out.println(" elseif part != fresh ");		
			clauseValue = request.getParameter("clauses"+(k+1))==null?"":request.getParameter("clauses"+(k+1));
			System.out.println( "clauseValue " + clauseValue + " commName " + commName);
			String event="";
			if(temp.equalsIgnoreCase(proposalDetails[r][1]))
			{	
				if(!coverFlag)
				{ 	
					coverFlag = true;
					
   %>
					<tr> 
					<td colspan="3"> 
						<%=proposalDetails[r][2]%> <%= proposalDetails[r][3]%>
					</td>
					</tr>
	<%		
				}
	%>
		<tr <%=bgcolor%> >
		<td   align="left" ><%=k+1%>&nbsp;<input type="checkbox" name="clauses<%=(k+1)%>" value="<%= commName %>" 		
		<%//=(request.getParameter("clauses"+(k+1))==null?"":request.getParameter("clauses"+(k+1))).equalsIgnoreCase(commName)?"checked":""%> 		
		<%=isErrorPage?(clauseValue.equalsIgnoreCase(commName)?"checked":""):""%>></td>
		<td align="left" width="20%" ><div align="center"><b><%=(String)conditionsIdsList.get(""+(k))%></b></div></td>
        <td align="left"><textarea name="description<%=(k+1)%>" rows="4" cols="60" id="description<%=k%>" onkeyup="textLimit(this,2000)"><%=(request.getParameter("description"+(k+1))==null?commName:request.getParameter("description"+(k+1)))%>
		</textarea></td>
		<!--<input type="hidden" name="coverId<%=(k+1)%>" value="<%=temp%>">-->
		</tr>
	<%		bgcolor="";
			}
		}
		else if("fresh".equalsIgnoreCase(mode))
		{
			System.out.println(" elseif part = fresh ");			
			clauseValue = request.getParameter("clauses"+(k+1))==null?"":request.getParameter("clauses"+(k+1));
			clauseValue=(String)defaultIds.get((String)conditionsIdsList.get(""+(k)))==null?"":(String)defaultIds.get((String)conditionsIdsList.get(""+(k)));
			if(temp.equalsIgnoreCase(proposalDetails[r][1]))
			{	
				if(!coverFlag)
				{ 	
					coverFlag = true;
	%>
		<tr> 
		<td colspan="3"> 
			<%=proposalDetails[r][2]%> <%= proposalDetails[r][3]%>
		</td>
		</tr>
	<%		
			}
	%>
		<tr <%=bgcolor%> >		
		<td   align="left" ><%=k+1%>&nbsp;
		<input type="checkbox" name="clauses<%=(k+1)%>" value="<%= commName %>" 		
		<%=clauseValue.equalsIgnoreCase(commName)?"checked":""%> ></td>
		<td align="left" width="20%" ><div align="center"><b><%=(String)conditionsIdsList.get(""+(k))%></b></div></td>
		<td align="left"><textarea name="description<%=(k+1)%>" rows="4" cols="60" id="description<%=k%>" onkeyup="textLimit(this,2000)"><%=(request.getParameter("description"+(k+1))==null?commName:request.getParameter("description"+(k+1)))%>
		</textarea></td>
		<!--<input type="hidden" name="coverId<%=(k+1)%>" value="<%=temp%>"> -->
		</tr>
<%			}
		}
	}//ENd of FOR
  }// Cover for proposal
%>
<tr>
	<td class="" align="right"></td>
    <td></td>
 <%--    
    <td class="LTblueBG" style="PADDING-LEFT: 10px">
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
Effective Date <input readOnly value = "<%=(effectDate==""?todayStr:todayStr)%>" name="effectDate" size="20" type="text" class="fde1">
<a href="javascript:cal6.popup();">
<img alt="Click Here Pick up the date" src="<%=path%>/images/cal.gif" border="0" width="16" height="16"></a>
</td>
--%>
<td>
  <input  value = "<%=(effectDate==""?todayStr:todayStr)%>" name="effectDate" type="hidden" >
</td>
</tr>
</tbody>
</table>
</td>
</tr>
</table>
</td>
<tr>
<td colspan="2" >
<table width="98%"  border="0" cellspacing="0" cellpadding="0">
<tr>
<td >
</td>
<td height="32"  valign="middle" >
<table width="100%" border="0" cellpadding="0" cellspacing="0">
<tr> 
<td align="center" >
	<a href="javascript:window.close()"  > <img src="../images/Back.jpg"></a>
	&nbsp;&nbsp;&nbsp;
	<a href="#" onClick="formSubmit()"> <img src="../images/Submit.jpg" ></a> 
</td>
</tr>
</table>&nbsp;&nbsp;&nbsp;</td>
</tr>
</table>
</td>
</tr>
</table>
<input type = "hidden" name = "wsrccNoEDIT"  value = "7" >
<input type = "hidden" name = "transportSea" value = "<%=sea%>">
<input type = "hidden" name = "transportAir" value = "<%=air%>">
<input type = "hidden" name = "transportMultimode" value = "<%=multimode%>">
</form>
<script type="text/javascript">

var elem = document.getElementById("sumInsured<%=as%>");
if(elem!=null)
{
	elem.focus();
	elem.select();
}
var cal6 = new calendar1(document.forms['clauses'].elements['effectDate']);
cal6.year_scroll = true;
cal6.time_comp = false;
</script>
</body>
</html>
<%
}
else
{
%>
<table width="98%"  border="0" cellspacing="0" cellpadding="0">
<tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr>
<tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr>
<tr align = center><td align = center><font size = '2' color = 'red'><b>  No WSRCC for Road</td></b></font></tr></center>
              <tr>			
		<tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr>	
				<td height="32" align="center" valign="middle" >				
				<table border="0" align="right" cellpadding="0" cellspacing="0">
			<tr> 
				 <td align="center">
				 <a href="javascript:window.close()"  >
				 <img src="../images/Back.jpg" ></a> </td>
				<td>&nbsp;</td>
              </tr>
            </table>
<%
}
%>