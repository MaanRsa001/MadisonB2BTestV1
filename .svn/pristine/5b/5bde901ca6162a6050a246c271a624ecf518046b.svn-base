<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ page language="java" import="com.maan.report.service.ReportService"%>
<!DOCTYPE HTML>
<html>
<head>
</head>
<body>
<div class="row">
	<div class="col-xs-12 col-sm-12 col-md-3 col-lg-3">
		<div class="container vehDtl">
			<div class="Card_Parent card">
				<div class="panel-body">
					<div class="row">
						<div class="col-xs-6 col-sm-6 col-md-12 col-lg-12">
							<input type="button" class="btn btn-sm btn-info adminMenuBtn" value="Admin Details" onclick="fnCall('list')"/> 
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="col-xs-12 col-sm-12 col-md-9 col-lg-9">
		<div class="container vehDtl">
			<div class="Card_Parent card">
				<div class="panel-heading">
					<%-- <s:text name="Change Password" />
					<div class="pullRight">
						Admin : <s:property value="loginID"/>
					</div>
					<br class="clear" /> --%>
					
					<div class="VehicleTableheading">
		               <div class="row">
		                  <div class="col-lg-6 col-md-6">
		                    <h3><s:text name="Change Password" /></h3>
		                  </div>
		                  <div class="col-lg-3 offset-md-3 offset-lg-3 col-md-3">
		                      <div  class="pullRight label label-warning">
		                        <b>Admin : <s:property value="loginID"/></b>
		                    </div>
		                  </div>
		               </div>
		            </div><hr>
            
            
				</div>
				<div class="panel-body">
					<s:form id="newPwd" name="underwriter" action="updatePassAdmin" method="post" theme="simple">
					<div class="row">
						<div class="col-xs-12">
							<s:actionerror cssStyle="color: red;" />
						</div>
					</div>
					<div class="row">
						<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">	
							<s:if test='"passwordsuccess".equals(display)'>
								<div class="row">
									<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">	
										<s:label key="Password Changed Successfully" /> 
									</div>
								</div>
								<br/>
								<div class="row">
									<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" align="center">
										<input type="button" class="btn btn-sm btn-danger" value="Close" onclick="fnCall('list')"/>
									</div>
								</div>
							</s:if>
							<s:else>
								<table width="100%">
									<tbody>
									<tr>
										<td width="30%"><s:text name="broker.nameNpassword"/> : </td>
										<td width="30%"><s:password name="pwd" size="35" onkeyup="passwordStrength(this.value)"  cssClass="form-control" maxlength="20"/></td>
										<td width="40%"><s:text name="broker.passwordmessage"/> </td>
									</tr>
									<tr align="center">
										<td width="30%">&nbsp;</td>
										<td colspan="2" align="left" style="padding-left: 10px;"><div id="passwordDescription">Password not entered</div><div id="passwordStrength" class="strength0"></div></td>
									</tr>
									<tr>
										<td width="30%">Re enter new Password : </td>
										<td width="30%"><s:password name="rpassword" size="35" cssClass="form-control" maxlength="20"/> </td>
										<td width="40%">&nbsp;</td>
									</tr>
									<tr><td>&nbsp;</td></tr>
									<tr>
										<td colspan="4" align="center">
											<input type="button" onclick="fnSubmit('UnderwriterCreationContoller.action');" class="btn btn-sm btn-danger" value="Cancel" > &nbsp;&nbsp;&nbsp;
											<s:submit name="submit1" cssClass="btn btn-sm btn-success" value="Submit" />
										</td>
									</tr>
									</tbody>
								</table>
							</s:else>
						</div>
					</div>
					<s:hidden name="agencyCode"/>
					<s:hidden name="borganization"/>
					<s:hidden name="bcode"/>
					<s:hidden name="firstname"/>
					<s:hidden name="loginID" />
					</s:form>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
function stopRKey(evt) { 
  var evt = (evt) ? evt : ((event) ? event : null); 
  var node = (evt.target) ? evt.target : ((evt.srcElement) ? evt.srcElement : null); 
  if ((evt.keyCode == 13) && (node.type=="text"))  {return false;} 
} 
document.onkeypress = stopRKey;

function fnCall(from){	
	document.underwriter.action = "adminMgtAdmin.action";	
	document.underwriter.submit();
}


function passwordStrength(password){
	var desc = new Array();
	desc[0] = "Very Weak";
	desc[1] = "Weak";
	desc[2] = "Better";
	desc[3] = "Medium";
	desc[4] = "Strong";
	desc[5] = "Strongest";
	var score   = 0;
	if (password.length > 6) score++;
	if ( ( password.match(/[a-z]/) ) && ( password.match(/[A-Z]/)) ) score++;
	if ( password.match(/\d+/)) score++;
	if ( password.match(/.[@,#,$,%]/))score++;
	if ( (password.length > 10) && (password.match(/.[@,#,$,%]/)) && (password.match(/[a-z]/) ) && (password.match(/[A-Z]/)) && (password.match(/\d+/)))score++;
	document.getElementById("passwordDescription").innerHTML = desc[score];
	document.getElementById("passwordStrength").className = "strength" + score;
}
function fnSubmit(action){	
	document.underwriter.action =action;		
	document.underwriter.submit();
}
</script>
</body>
</html>