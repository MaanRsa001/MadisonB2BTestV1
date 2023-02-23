<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ page language="java" import="com.maan.report.service.ReportService"%>
<!DOCTYPE HTML>
<html>
<head>
<style type="text/css">
  		#loading {
		  width: 100%;
		  height: 100%;
		  top: 0px;
		  left: 0px;
		  position: fixed;
		  display: block;
		  opacity: 0.7;
		  background-color: black;
		  z-index: 99;
		  opacity:0.5;
		  text-align: center;
		}
		
		#loading-image {
		  position: absolute;
		  top: 30%;
		  left: 45%;
		  z-index: 100;
		  width: 100px;
		  height: 100px;
		}
  	</style>
</head>
<body>
<s:form id="newPwd" name="newPwd" method="post" theme="simple">
<div id="loading" style="width:100%">
   <img id="loading-image" src="${pageContext.request.contextPath}/images/madisonSymbolLogo.png"/>
</div>
<div class="row">
	<div class="col-xs-12 col-sm-12 col-md-3 col-lg-3">
		<div class="container vehDtl">
			<div class="Card_Parent card">
				<div class="row">
					<div class="col-xs-6 col-sm-6 col-md-12 col-lg-12">
						<input type="button" class="btn btn-sm btn-info adminMenuBtn" value="View" onclick="fnCall('view')"/>
					</div>
					<div class="col-xs-6 col-sm-6 col-md-12 col-lg-12">
						<input type="button" class="btn btn-sm btn-info adminMenuBtn" value="Edit" onclick="fnCall('edit')"/>
					</div>
				</div>
				<div class="row">
					<div class="col-xs-6 col-sm-6 col-md-12 col-lg-12">
						<input type="button" class="btn btn-sm btn-info adminMenuBtn" value="User Details" onclick="fnCall('userDetail')"/>
					</div>
				</div>
				<div class="row">
					<div class="col-xs-6 col-sm-6 col-md-12 col-lg-12">
						<input type="button" class="btn btn-sm btn-info adminMenuBtn" value="Customer Detail" onclick="fnCall('customerDetail')"/>
					</div>
					<!-- <div class="col-xs-6 col-sm-6 col-md-12 col-lg-12">
						<input type="button" class="btn btn-sm btn-info adminMenuBtn" value="OpenCover" onclick="fnCall('openCover')"/>
					</div> -->
				</div>
				<div class="row">
					<!-- <div class="col-xs-6 col-sm-6 col-md-12 col-lg-12">
						<input type="button" class="btn btn-sm btn-info adminMenuBtn" value="Referral" onclick="fnCall('referal')"/>
					</div> -->
					<!-- <div class="col-xs-6 col-sm-6 col-md-12 col-lg-12">
						<input type="button" class="btn btn-sm btn-info adminMenuBtn" value="Statistics" onclick="fnCall('statistics')"/>		
					</div> -->
				</div>
			</div>
		</div>
	</div>
	<div class="col-xs-12 col-sm-12 col-md-9 col-lg-9">
		<div class="container vehDtl">
			<div class="Card_Parent card">			
			<div class="panel-heading">
				<div class="VehicleTableheading">
	               <div class="row">
	                  <div class="col-lg-6 col-md-6">
	                    <h3><s:label key="broker.brokermanagement"/></h3>
	                  </div>
	                  <div class="col-lg-3 offset-md-3 offset-lg-3 col-md-3">
	                      <div  class="pullRight label label-warning">
	                        <b><s:property value="borganization"/>(<s:property value="bcode"/>)</b>
	                    </div>
	                  </div>
	               </div>
	            </div><hr>
			</div>
			<div class="panel-body">
				<s:if test='"passwordsuccess".equals(display)'>
					<div class="row">
						<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
							<div class="label label-success"><s:text name="broker.passwordsuccess"/></div>
						</div>
					</div>
					<br class="clear" />
					<div class="row">
						<div class="col-xs-12 col-sm-2 col-md-12 col-lg-12" align="center">
							<button type="button" name="back11" class="btn btn-sm btn-success" value="Proceed" onclick="fnCancel()">Ok</button>
						</div>
					</div>
				</s:if>
				<s:else>
				<div class="row">
					<div class="col-xs-12 col-sm-2 col-md-12 col-lg-12">
						<s:if test="'invalid'.equals(display)">
						 	<span style="color: red;"> <s:text name="error.invalid" /> </span>
						</s:if>
						<s:elseif test="'different'.equals(display)">
							<span style="color: red;"> <s:text name="error.different"/> </span>
						</s:elseif>
						<div class="row">
							<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
								<s:actionerror cssStyle="color:red;" /> <s:actionmessage cssStyle="color:green;" />
							</div>
						</div>
						<table width="100%">
							<tbody>
							<tr>
								<td width="30%"><s:text name="broker.broker"/> : </td>
								<td width="30%"><s:property value="borganization"/>( <s:property value="agencyCode"/> )</td>
								<td width="40%"> </td>
							</tr>
							<!--<tr><td colspan="3" height="5"></td></tr>
							<tr>
								<td><s:text name="broker.nameNpassword"/> : </td>
								<td><s:textfield name="loginid" cssClass="form-control" size="20"/> </td>
								<td> </td>
							</tr>-->
							<tr><td colspan="3" height="5"></td></tr>
							<tr>
								<td><s:text name="broker.password"/> : </td>
								<td><s:password name="password" size="35" onkeyup="passwordStrength(this.value)" cssClass="form-control" maxlength="20"/> </td>
								<td> <s:label key="broker.passwordmessage"/> </td>
							</tr>
							<tr><td colspan="3" height="5"></td></tr>
							<tr>
								<td></td>
								<td></td>
								<td>
									<div id="passwordDescription">Password not entered</div><div id="passwordStrength" class="strength0"></div>
								</td>
							</tr>
							<tr><td colspan="3" height="5"></td></tr>
							<tr>
								<td><s:text name="Re-Enter Password"/> : </td>
								<td><s:password name="rpassword" size="35" cssClass="form-control" maxlength="20"/> </td>
								<td></td>
							</tr>
							</tbody>
						</table>
					</div>
				</div>
				<br class="clear" />
				<div class="row">
					<div class="col-xs-12 col-sm-2 col-md-12 col-lg-12" align="center">
						<button type="button" name="submit1" class="btn btn-sm btn-danger" value="Cancel" onclick="fnCancel()">Cancel</button>
						<button type="button" name="submit1" class="btn btn-sm btn-success" value="Submit" onclick="fnSubmit()">Submit</button>
					</div>
				</div>
				</s:else>
			</div>
			</div>			
		</div>
	</div>
</div>
<s:hidden name="agencyCode"/>
<s:hidden name="borganization"/>
<s:hidden name="bcode"/>
<s:hidden name="firstname"/>
<s:hidden name="login_Id"/>
</s:form>
<script type="text/javascript">
function fnCall(from){
	if(from=='edit')
		document.newPwd.action = from+"BrokerMgm.action?mode=edit";
	else if(from=='userDetail')
		document.newPwd.action = "getABListUserMgm.action?mode1=broker";
	else if(from=='customerDetail')
		document.newPwd.action = "getABListCustomerMgm.action?mode1=broker";
	else if(from=='referal')
		document.newPwd.action = "getOCListReferal.action";
	else if(from=='openCover')
		document.newPwd.action = "opencoverOC.action";
	else
		document.newPwd.action = from+"BrokerMgm.action";
	document.newPwd.submit();
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
function fnCancel(){
document.newPwd.action = "getABListBrokerMgm.action";
document.newPwd.submit();
}
function fnSubmit(){
document.newPwd.action = "checkPwdBrokerMgm.action";
document.newPwd.submit();
}
</script>
</body>
</html>