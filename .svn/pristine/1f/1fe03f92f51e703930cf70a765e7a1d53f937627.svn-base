<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@page contentType="text/html" pageEncoding="UTF-8" isELIgnored="false"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/WEB-INF/displaytag.tld" prefix="display"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
	<head>
		<!--<title><s:text name="company.name" /> - <s:text name="application.name" /></title>
	-->
		<title>MarineME</title>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<meta name="description" content="">
		<meta name="keywords" content="Maan Sarovar">
		<meta name="author" content="">
		<!-- CSS STYLE-->
		<link media="screen"
			href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css"
			rel="stylesheet" type="text/css">
		<link media="screen"
			href="${pageContext.request.contextPath}/bootstrap/css/datepicker3.css"
			rel="stylesheet" type="text/css">
		<link rel="stylesheet" class="cloudslider-css"
			href="${pageContext.request.contextPath}/bootstrap/css/cloudslider.css"
			type="text/css">
		<link media="screen"
			href="${pageContext.request.contextPath}/bootstrap/css/style.css"
			rel="stylesheet" type="text/css">
		<style type="text/css">
.textfield33 {
	padding: 0 5px 0 5px;
}

.col-xs-12 col-sm-12 col-md-6 col-lg-6 {
	padding: 0 5px 0 5px;
}

.form-control {
	width: 100%;
}

.conBody {
	float: left;
	width: 70%;
}

.conRight {
	float: left;
	width: auto;
}
</style>
		<script type="text/javascript"
			src="${pageContext.request.contextPath}/js/common.js"></script>
		<script type="text/javascript">
    	function stopRKey(evt) { 
		  var evt = (evt) ? evt : ((event) ? event : null); 
		  var node = (evt.target) ? evt.target : ((evt.srcElement) ? evt.srcElement : null); 
		  if ((evt.keyCode == 13) && (node.type=="text"))  {return false;} 
		} 
		document.onkeypress = stopRKey;
	
		function fnCall(from){
			if(from=='edit')
				document.info.action = from+"BrokerMgm.action?mode=edit";
			else if(from=='userDetail')
				document.info.action = "getABListUserMgm.action?mode1=broker";
			else if(from=='edituser')
				document.info.action = "editUserMgm.action?mode1=broker&mode=edit";
			else if(from=='viewuser')
				document.info.action = "viewUserMgm.action?mode1=broker";
			else if(from=='customerDetail')
				document.info.action = "getABListCustomerMgm.action?mode1=broker";
			else if(from=='referal')
				document.info.action = "getOCListReferal.action";
			else if(from=='openCover')
				document.info.action = "opencoverOC.action";
			else
				document.info.action = from+"BrokerMgm.action";
			document.info.submit();
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
    </script>
	</head>
	<body>
		<div class="container" style="width: 95%;">
			<div class="header">
				<div class="logo">
					<img  alt="Madison General Logo" src="${pageContext.request.contextPath}/images/logo.jpg" />
				</div>
				<div class="headerText" style="float: right;">
					<a href="${pageContext.request.contextPath}"> Home </a>
				</div>
				<br class="clear" />
				<hr />
			</div>
			<div id="menuNavBar">

			</div>
			<div id="bodyContent">
				<s:form id="info" name="info" method="post" action="addNewuserUserReg" theme="simple">
					<div class="panel panel-primary">
						<div class="panel-body">
							<s:if test="'successNew'.equals(display)">
									<div align="center">
										<s:label key="user.new.success"/> <br/>
										<input type="button" onclick="fnsubmit('back','HomeUser', this.form)" name="back" class="btn btn-sm btn-danger" value="Proceed" />
									</div>
							</s:if>
							<s:else>
							<div class="row">
								<div class="col-xs-12 col-sm-12 col-md-9 col-lg-9">
									<s:if test="hasActionErrors()">
										<font color="red" style="list-style:none; "><s:actionerror cssStyle="list-style:none;"/></font>
									</s:if>
									<s:if test="hasActionMessages()">
										<s:actionmessage cssStyle="list-style:none; color:green;"/>
									</s:if>
									
									<div class="panel panel-primary">
										<div class="panel-heading">
											<s:label key="user.contactpersonInfo" />
										</div>
										<div class="panel-body">
											<div class="row">
												<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
													<div class="text"><s:label key="user.title" /></div>
													<div class="tbox">
														<s:select name="utitle" id="title" list="titlesInfo"
														cssClass="form-control" listKey="TITLE_ID"
														listValue="TITLE_NAME" headerKey=""
														headerValue="---Select---" cssStyle="width: 100%;" />
													</div>
												</div>
												<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
													<div class="text"><s:label key="user.firstname" /> <font color="red">*</font></div>
													<div class="tbox">
														<s:textfield name="ufirstname" cssClass="form-control"
														size="35" maxlength="30" />
													</div>
												</div>
												<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
													<div class="text"><s:label key="user.lastname" /></div>
													<div class="tbox">
														<s:textfield name="ulastname" cssClass="form-control"
														size="35" maxlength="30" />
													</div>
												</div>
												<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
													<div class="text"><s:label key="user.gender" /></div>
													<div class="tbox">
														<s:radio name="ugender" id="ugender"
														list="#{'M':'Male','F':'Female'}"  />
													</div>
												</div>
												<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
													<div class="text"><s:label key="user.dob" /></div>
													<div class="tbox">
														<s:textfield id="udob" name="udob"
														cssClass="form-control datepicker" readonly="true"
														disabled="#disable" showOn="%{#disable?'focus':''}" />
													</div>
												</div>
												<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
													<div class="text"><s:label key="user.occupation" /></div>
													<div class="tbox">
														<s:textfield name="uoccupation" cssClass="form-control"
														size="35" maxlength="30" />
													</div>
												</div>
												<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
													<div class="text"><s:label key="user.address1" /></div>
													<div class="tbox">
														<s:textfield name="uaddress1" id="address1"
														cssClass="form-control" size="35" maxlength="50" />
													</div>
												</div>
												<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
													<div class="text"><s:label key="user.address2" /></div>
													<div class="tbox">
														<s:textfield name="uaddress2" id="address2"
														cssClass="form-control" size="35" maxlength="50" />
													</div>
												</div>
												<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
													<div class="text"><s:label key="user.pobox" /><font color="red">*</font></div>
													<div class="tbox">
														<s:textfield name="upobox" id="pobox" cssClass="form-control"
														size="35" maxlength="6" />
													</div>
												</div>
												<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
													<div class="text"><s:label key="user.city" /><font color="red">*</font></div>
													<div class="tbox">
														<s:select name="ucity" id="emirate" cssClass="form-control"
														list="emiratesInfo" listKey="CITY_NAME"
														listValue="CITY_NAME" headerKey=""
														headerValue="---Select---" cssStyle="width: 100%;" />
													</div>
												</div>
												<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6" style="display: none;">
													<div class="text"><s:label key="user.country" /><font color="red">*</font></div>
													<div class="tbox">
														<s:select name="ucountry" id="country" list="countriesInfo"
														cssClass="form-control" listKey="COUNTRY_ID"
														listValue="COUNTRY_NAME" headerKey=""
														headerValue="---Select---" cssStyle="width: 100%;" />
													</div>
												</div>
												<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
													<div class="text"><s:label key="user.telephone" /></div>
													<div class="tbox">
														<s:textfield name="uphone" id="telephone"
														cssClass="form-control" size="35" maxlength="15" />
													</div>
												</div>
												<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
													<div class="text"><s:label key="user.mobile" /> <font color="red">*</font></div>
													<div class="tbox">
														<s:textfield name="umobile" cssClass="form-control" size="35"
														maxlength="20" />
													</div>
												</div>
												<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
													<div class="text"><s:label key="user.email" /></div>
													<div class="tbox">
														<s:textfield name="uemail" cssClass="form-control" size="35"
														maxlength="30" />
													</div>
												</div>
											</div>
										</div>
									</div>
									<div class="panel panel-primary">
										<div class="panel-heading">
											<s:label key="user.login.creation" />
										</div>
										<div class="panel-body">
											<div class="row">
												<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
													<div class="text"><s:label key="user.new" /><font color="red">*</font></div>
													<div class="tbox">
														<s:textfield name="userId" cssClass="form-control" size="35"
															maxlength="15" />
													</div>
												</div>
												<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
													<div class="text"><s:label key="user.password" /><font color="red">*</font></div>
													<div class="tbox">
														<s:password name="password" cssClass="form-control"
															maxlength="20" />
													</div>
												</div>
												<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
													<div class="text"><s:label key="user.rpassword" /><font color="red">*</font></div>
													<div class="tbox">
														<s:password name="repassword" cssClass="form-control"
															maxlength="20" />
													</div>
												</div>
											</div>
										</div>
									</div>
									<br />
									<div align="center">
										<s:submit name="submit" cssClass="btn btn-sm btn-success" value="Submit" />
									</div>
								</div>
								<div class="col-xs-12 col-sm-12 col-md-3 col-lg-3">
									<div class="panel panel-primary">
										<div class="panel-body">
											<div class="panel panel-primary">
												<div class="panel-heading">
													Benefits
												</div>
												<div class="panel-body">
													<ol type="1">
														<li>
															View Quote
														</li>
														<li>
															View Policy
														</li>
														<li>
															Intimate Claim
														</li>
														<li>
															Intimate Endrosement
														</li>
														<li>
															Track your claim
														</li>
													</ol>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
							</s:else>
						</div>
					</div>
					<s:hidden name="utype" value="User" />
					<s:hidden name="agencyCode"/>
					<s:hidden name="mode"/>
					<s:hidden name="mode1"/>
					<s:hidden name="uagencyCode"/>
					<s:hidden name="borganization"/>
					<s:hidden name="login_Id" value="%{#ulogin_Id}"/>
					<s:hidden name="broker" value='%{getText("B2C_AGENCY_CODE")}'/>
				</s:form>
				<br class="clear" />
			</div>

			<div id="footer" align="right">
				<div class="div-footer">
					XYZ Insurance Company Limited. All rights reserved
				</div>
				<div class="div-footer">
					&copy; Owned by
					<a href="http://www.maansarovar.com/" target="_blank">Maan
						Sarovar Tech Solutions Pvt. Ltd.</a>
				</div>
				<br class="clear">
			</div>
		</div>
		<script
			src="${pageContext.request.contextPath}/bootstrap/js/jquery.min.js"
			type="text/javascript"></script>
		<script
			src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.min.js"
			type="text/javascript"></script>
		<script
			src="${pageContext.request.contextPath}/bootstrap/js/bootstrap-datepicker.js"
			type="text/javascript"></script>
		<script
			src="${pageContext.request.contextPath}/bootstrap/js/cloudslider.jquery.min.js"
			type="text/javascript"></script>
		<script type="text/javascript">
$(function() {
	try {
		
		$('#udob').datepicker({
			todayHighlight: true,
			format: "dd/mm/yyyy",
			defaultDate: "01/01/1960",
			//endDate: "-18y"
		}).on('changeDate', function(e){
		    $(this).datepicker('hide');
		});
	} catch(err){}	
});
function fnsubmit(from, action, frm){
	frm.action=action+".action";
	frm.submit();
}
</script>
</body>
</html>