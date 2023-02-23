<%
	String path = request.getContextPath();
	StringBuffer urlfrom=request.getRequestURL();
	String cpath = request.getContextPath();
	String cargoPath = "";
	cpath = cpath.replaceAll("/","");
	String cargoName = "Madison General";
	if(cpath.equalsIgnoreCase("E-Cargo-Madison General Insurance"))
		cargoPath = "971 4 302 9800/334 4474";
	else if(cpath.equalsIgnoreCase("TE-Cargo-Global"))
		cargoPath = "971 4 302 9800/334 4474";	
	else if(cpath.equalsIgnoreCase("E-Cargo-Madison General Insurance"))
	{
		cargoPath = "966 2 692 7085";	
		cargoName = "AL ALAMIYA";
	}
	else if(cpath.equalsIgnoreCase("E-Cargo-Oman"))
		cargoPath = "971 4 302 9800/334 4474";	
	else if(cpath.equalsIgnoreCase("E-Cargo-Bahrain"))
		cargoPath = "966 2 692 7085";	
%>
<html>
<head>
<title> ::: Madison General - E-Way ::: </title>
<link rel="shortcut icon" href="<%=request.getContextPath()%>/images/favicon.ico" />
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/bootstrap/css/bootstrap.min.css" />
<link href="style.css" rel="stylesheet" type="text/css">
</head>
<body bgcolor="#FFFFFF">
<br><form name ='form1' action="<%=path%>/Loginout.action">

<table width="760" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td height="21" align="right" valign="middle"  class="bodytext"><font color="#FFFFFF"></font></td>
  </tr>
  <tr>
<table width="550" border="1" cellspacing="0" cellpadding="0" align="center" class="RSA_Cream_BG3">
  <tr bgcolor="#000099" align="right" valign="middle"> 
    <td > 
      <table width="500" border="0" cellspacing="0" cellpadding="0" align="left">
        <tr>
          <td><font face="Verdana, Arial, Helvetica, sans-serif" size="2" color="#FFFFFF"><b>&nbsp;&nbsp;Error</b></font></td>
        </tr>
      </table>
      <img src="../images/top_but.gif" width="50" height="14"></td>
  </tr>
  <tr bgcolor="#F2F2F2" align="center" valign="top"> 
    <td height="212"> 
      <table width="89%" border="0" cellspacing="0" cellpadding="0" class="RSA_Cream_BG3">
        <tr> 
          <td colspan="2">&nbsp;</td>
        </tr>
        <tr> 
          <td height="38" width="10%" valign="top"><img src="<%=path%>/images/error.gif" width="34" height="33"></td>
          <td height="38" width="90%"><b><font face="Arial, Helvetica, sans-serif" size="2"><%=cargoName%></font></b></td>
        </tr>
        <tr> 
          <td height="90" width="10%">&nbsp;</td>
          <td height="90" width="90%" valign="top"> <font face="Arial, Helvetica, sans-serif" size="2">
		  
	   						  <table width='100%' border='1' cellspacing='1' cellpadding='1' align='center' class="bodytext">
						 <tr> 
						   <td> 
							 <p><b><br>Sorry, the  requested page could not be found. The reason may be :</b><br>
							   <br>1.Session ID is not valid.<br>
							   </p>
							   <br/>
							   <div align="center">
							   	<a href="javascript:onClick=document.form1.submit()" class="btn btn-sm btn-danger" > Home </a>
							   </div>
						   </td>
						 </tr>
					   </table>		  
		  
		  </font></td>
        </tr></table>
	</td></tr>
	</table>

  <tr align="right">
   <td class="formtxt" height="21" background="../images/bottom_bg.gif"><font color="#FFFFFF" align="right">&nbsp;Developed by </font><a href ="http://www.maansarovar.com" target="_blank"><font color="#FFFFFF">Maan Sarovar Software </font></a></td>
  </tr>
  </table>
 
</body>
</html>
