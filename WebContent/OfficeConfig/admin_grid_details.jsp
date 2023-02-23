<%@ page language="java" import="java.util.*,com.maan.Office.DAO.*"	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%
	String path = request.getContextPath();
String schemeName = request.getScheme();
String serverName = request.getServerName();
String serverPort =  String.valueOf(request.getServerPort());
if(serverName.startsWith("online")){
	schemeName = "https";
	serverPort = "";
}
String basePath = schemeName + "://"
		+ serverName + (!"".equalsIgnoreCase(serverPort)?(":"+serverPort):"")
		+ path + "/";
	CoverageBean coverageBean = new CoverageBean();
//	try{
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title><%@ include file="include/title.jsp"%>
		</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<script language="JavaScript" src="<%=path%>/css/calendar1.js"></script>
		<meta http-equiv="description" content="This is my page">
		<%@ include file="include/includeFile.jsp"%>
		<style type="text/css">
		.content_align{
			padding-left: 10px;
			font-weight: bold;
		}
		.content_align1{
			padding-left: 5px;
			font-weight: bold;
		}
		.textbox{
			border: 1px solid #000000;
		}
		
		</style>

		<%
			GregorianCalendar gc = new GregorianCalendar();
			String effectiveDate = gc.get(Calendar.DATE) + "-"
					+ (gc.get(Calendar.MONTH) + 1) + "-"
					+ gc.get(Calendar.YEAR);

					String error = (String) request.getAttribute("error");
					error = error == null ? "" : error;

			String proId = (String) request.getParameter("product_id");
			proId = proId == null ? "0" : proId;
			
			String schemeId = (String) request.getParameter("scheme_id");
			schemeId = schemeId == null ? "0" : schemeId;

			String contentTypeID = request.getParameter("contentType");
			contentTypeID = contentTypeID == null ? "0" : contentTypeID;

			String coverageID = request.getParameter("coverage");
			coverageID = coverageID == null ? "0" : coverageID;
			
			String coverid = request.getParameter("coverage") == null ? "": request.getParameter("coverage");
			String subcoverid = request.getParameter("subcoverage") == null ? "0": request.getParameter("subcoverage");
			String requestfrom = request.getParameter("requestfrom") == null ? "": request.getParameter("requestfrom");
			String schemeValue = coverageBean.getIDRelatedValue("SCHEME_NAME","OFS_SCHEME_MASTER", "SCHEME_ID", schemeId);
			String contentTypeValue = coverageBean.getIDRelatedValue("CONTENT_DESCRIPTION", "OFS_CONTENT_MASTER","CONTENT_TYPE_ID", contentTypeID);
			contentTypeValue=contentTypeValue==null?"None":"None";
			String coverageValue = coverageBean.getIDRelatedValue("COVERAGES_NAME", "OFS_MASTER", "COVERAGES_ID", coverageID);
			if(proId.equalsIgnoreCase("65"))
			{
				schemeValue = request.getParameter("shemeName") == null ? "": request.getParameter("shemeName");
				contentTypeValue=request.getParameter("typeName") == null ? "": request.getParameter("typeName");
				coverageValue = request.getParameter("coverName") == null ? "": request.getParameter("coverName");
			}
			
			String add = request.getParameter("addMore") == null ? "3": request.getParameter("addMore");
			int addMore=Integer.parseInt(add);
			
			String[][] gridDetails=request.getAttribute("gridDetails")==null?new String[0][0]:(String[][])request.getAttribute("gridDetails");
			if(gridDetails.length<=0)
			{
				gridDetails=coverageBean.getGridDetails(proId, schemeId, contentTypeID, coverid, subcoverid);
			}
			int arrayLength = 0;
			if(gridDetails != null && gridDetails.length>0)
			{
				addMore=(gridDetails.length>addMore)?gridDetails.length:addMore;
				arrayLength = gridDetails.length;
			}
		
		%>
	</head>

	<body>
		<form action="CoverageController" method="post" name="form1">
			<table align="center" width="95%">
				<tr class="formtxtf" style="font-weight: bold;">
					<td class="content_align" style="width: 50%" >
					<%if(proId.equalsIgnoreCase("65")) {%>
						Product : Motor Insurance
					<%}else{ %>
						Product : Office Shield
					<%} %>
					</td>
					<td class="content_align" style="width: 50%" >
						Scheme :
						<%=schemeValue%>
					</td>
					</tr><tr class="formtxtf" style="font-weight: bold;">
					<td class="content_align" style="width: 50%">
						Content Type :
						<%=contentTypeValue%>
					</td>
					<td class="content_align" style="width: 50%">
						Coverage Name :
						<%=coverageValue%>
					</td>
				</tr>
				<%
					System.out.println("error1: "+error);
					if (error.length() > 0) {
				%>
				<tr >
					<td colspan="2" align="center" valign="middle">
						<table><tr >
						<td colspan="2" align="left" valign="middle">
						<font color="red"><%=error%> </font>
						</td>
						</tr></table>
					</td>
				</tr>
				<%
				}
				%></table>
				
				<table align="center" width="95%"  >
				<tr   class="formtxtf" ><%--
					<td >
						Minimum Premium
					</td>
					--%><td style="font-weight: bold">
						Start Sum Insured
					</td>
					<td style="font-weight: bold">
						End Sum Insured
					</td>
					<td style="font-weight: bold">
						Base Rate
					</td>
					<td style="font-weight: bold">
						Calculation Type
					</td>
					<td style="font-weight: bold">
						Status
					</td>
					<td style="font-weight: bold">
						Effective Date
					</td>
					
				</tr>
				<%for(int k=0; k<addMore; k++){   %>
				<tr class="formtxtf">
					
					<td >
						<input class="textbox" type="hidden" name="minPremium<%=k%>" 
							value="<%=(arrayLength>k && gridDetails[k][0]!=null)?gridDetails[k][0]:"" %>" maxlength="10" size="8">							
						<input class="textbox" type="text" name="startSum<%=k%>"
							value="<%=(arrayLength>k && gridDetails[k][1]!=null)?gridDetails[k][1]:"" %>"  maxlength="10" size="8">
					</td>
					<td >
						<input class="textbox" type="text" name="endSum<%=k%>"
							value="<%=(arrayLength>k && gridDetails[k][2]!=null)?gridDetails[k][2]:"" %>"  maxlength="10" size="8">
					</td>
					<td >
						<input class="textbox" type="text" name="baseRate<%=k%>"
							value="<%=(arrayLength>k && gridDetails[k][3]!=null)?gridDetails[k][3]:"" %>"  maxlength="10" size="2">
					</td>
					
					<td >
							<table><tr><td>
								<input type="radio" class="textbox" name="calculationType<%=k%>"
									value="A" <%= (arrayLength>k && gridDetails[k][4]!=null && gridDetails[k][4].equals("A")) ? "checked" : ""%>>
								&nbsp;Amount</br>
								<input type="radio" class="textbox" name="calculationType<%=k%>"
									value="M" <%=(arrayLength>k && gridDetails[k][4]!=null&& gridDetails[k][4].equals("M")) ? "checked" : ""%>>
								&nbsp;Mile</br>
								<input type="radio" class="textbox" name="calculationType<%=k%>"
									value="P" <%=(arrayLength>k && gridDetails[k][4]!=null&& gridDetails[k][4].equals("P")) ? "checked" : ""%>>
								&nbsp;Percentage&nbsp;
							</td></tr></table>
					</td>
					<td >
						<input type="radio" class="textbox" name="status<%=k%>" value="Y"
							<%=(arrayLength>k && gridDetails[k][5]!=null && gridDetails[k][5].equals("Y")) ? "checked" : ""%>>
						&nbsp;Active</br>
						<input type="radio" class="textbox" name="status<%=k%>" value="N"
							<%=(arrayLength>k && gridDetails[k][5]!=null&& gridDetails[k][5].equals("N")) ? "checked" : ""%>>
						&nbsp;Deactive
					</td>
				<%
						try {
						String str = effectiveDate.substring(6, effectiveDate.length());
						Integer.parseInt(str);
					} catch (Exception e) {
						if (effectiveDate.length() > 0) {
							StringTokenizer token = new StringTokenizer(effectiveDate,
							" ");
							effectiveDate = token.nextToken();
							token = new StringTokenizer(effectiveDate, "-");
							effectiveDate = token.nextToken();
							effectiveDate = token.nextToken() + "-" + effectiveDate;
							effectiveDate = token.nextToken() + "-" + effectiveDate;
						}
					}
				%>
					<td >
						<input type="text" name="effectiveDate<%=k%>" value="<%=(arrayLength>k && gridDetails[k][6]!=null)?gridDetails[k][6]:"" %>"
							size="8" maxlength="10" class="fde1"
							id="effectiveDate" ReadOnly>
						<a href="javascript:getDate('effectiveDate<%=k%>');"
							onClick="document.form1.effectiveDate<%=k%>.focus()"><img
								src="<%=path%>/images/cal.gif" width="16" height="16" border="0"
								alt="Click Here Pick up the date"> </a>
					
						<input type="hidden" name="requestForm" id="requestForm">
						<input type="hidden" name="scheme" value="<%=schemeId%>">
						<input type="hidden" name="contentType" value="<%=contentTypeID%>">
						<input type="hidden" name="coverage" value="<%=coverageID%>">
						<input type="hidden" name="addMore" value="<%=addMore%>">
						<input type="hidden" name=scheme_id value="<%=schemeId%>">
						<input type="hidden" name="product_id" value="<%=proId%>">
						<input type="hidden" name="coverage" value="<%=coverid%>">
						<input type="hidden" name="subcoverage" value="<%=subcoverid%>">
					</td>
					
				</tr>
			<%} %>
			
			</table></br>
			
			<table width="40%" border="0" cellspacing="0" cellpadding="0"
			align="center">
			<tr>
				<td class="buttonsMenu">
					<a ><img src="/DemoB2C/images/Back.gif" alt="Back" onClick="history.go(-1)" style="cursor: pointer;"> </a>
				</td>
				
				<td align='center' class="buttonsMenu">
					<a><img src="<%=request.getContextPath() %>/images/Submit.gif" alt="Submit" onclick="return SubmitSaveAndExit()" width="104" height="20" style="cursor: pointer;" /></a>
				</td>
				<td class="buttonsMenu" align='center'>
					<img src="<%=request.getContextPath() %>/images/AddMore.gif" alt="Add More Rows" style="cursor: pointer;" onclick="return AddMore()">
				</td>
				<td align='center' class="buttonsMenu">
					<a > <img src="<%=request.getContextPath() %>/images/Close.gif" onclick="fnSubmit()" alt="Close" style="cursor: pointer;" width="99" height="20" /> </a>
				</td>
			</tr>
		</table>
		</form>
	
	<script>
	function getDate(fieldName){
	var cal1 = new calendar1(document.forms["form1"].elements[fieldName]);
	cal1.year_scroll = true;
	cal1.time_comp = false;
	cal1.popup();
	}

function ismaxlength(obj){
	 
var mlength=obj.getAttribute? parseInt(obj.getAttribute("maxlength")) : ""
//alert(mlength);
if (obj.getAttribute && obj.value.length>mlength)
obj.value=obj.value.substring(0,mlength)
}

function SubmitSaveAndExit()
{
document.getElementById('requestForm').value = 'gridInsert';
document.form1.submit();
return false;
}
function AddMore()
{
document.getElementById('requestForm').value='gridAddMore';
document.form1.submit();
return false;
}
function SubmitBack()
{
//document.getElementById('requestForm').value='back';
//document.form1.action='';
//document.form1.submit();
history.go(-1);
return false;
}
function fnSubmit()
{
window.close();
<% if(!proId.equalsIgnoreCase("65")){%>
window.opener.location.reload(true);
<%}%>
}
</script>
</body>
</html>
<%-- }catch(Exception e){e.printStackTrace();}--%>