<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ page language="java" import="com.maan.report.service.ReportService"%>
<!DOCTYPE HTML>
<html>
<head>
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/bootstrap/css/bootstrap-multiselect.css" />
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
        .btn-group, .btn-group-vertical {
		    display: block; 
		}
		.btn-group, .multiselect {
		    width: 100%;
		}
		.btn-default {
		    color: #333;
		    background-color: #fff;
		    border-color: #ccc;
		}
		.text{
		padding-top:10px;
		padding-bottom:3px;
		font-weight:bold;
		}
      </style>
</head>
<body>
<div id="loading" style="width:100%">
   <img id="loading-image" src="${pageContext.request.contextPath}/images/madisonSymbolLogo.png"/>
</div>
<div class="row">
	<s:if test='%{borganization!=null && !"".equals(borganization)}'>
		<div class="col-xs-12 col-sm-12 col-md-3 col-lg-3">
			<div class="container vehDtl">
				<div class="Card_Parent card">
					<div class="panel-body">
						<s:if test='%{borganization!=null && borganization!=""}'>
							<div class="row">
								<div class="col-xs-6 col-sm-6 col-md-12 col-lg-12">
									<input type="button" class="btn btn-sm btn-info adminMenuBtn" value="View" onclick="fnCall('viewuser')"/>
								</div>
								<div class="col-xs-6 col-sm-6 col-md-12 col-lg-12">
									<input type="button" class="btn btn-sm btn-info adminMenuBtn" value="User Details" onclick="fnCall('userDetail')"/>
								</div>
							</div>
							<div class="row">
								<div class="col-xs-6 col-sm-6 col-md-12 col-lg-12">
									<input type="button" class="btn btn-sm btn-info adminMenuBtn" value="Customer Details" onclick="fnCall('customerDetail')"/>
								</div>
								<div class="col-xs-6 col-sm-6 col-md-12 col-lg-12">
									<input type="button" class="btn btn-sm btn-info adminMenuBtn" value="OpenCover" onclick="fnCall('openCover')"/>
								</div>
							</div>
							<div class="row">
								<div class="col-xs-6 col-sm-6 col-md-12 col-lg-12">
									<input type="button" class="btn btn-sm btn-info adminMenuBtn" value="Referral" onclick="fnCall('referal')"/>
								</div>
								<div class="col-xs-6 col-sm-6 col-md-12 col-lg-12">
									<input type="button" class="btn btn-sm btn-info adminMenuBtn" value="Statistics" onclick="fnCall('statistics')"/>
								</div>
							</div>
						</s:if>
						<s:else>
							<div class="row">
								<div class="col-xs-6 col-sm-6 col-md-12 col-lg-12">
									<input type="button" class="btn btn-sm btn-info adminMenuBtn" value="View" onclick="fnCall('viewuser')"/>
								</div>
								<div class="col-xs-6 col-sm-6 col-md-12 col-lg-12">
									<input type="button" class="btn btn-sm btn-info adminMenuBtn" value="Customer Details" onclick="fnCall('customerDetail')"/>
								</div>
							</div>
						</s:else>
					</div>
				</div>
			</div>
		</div>
		<div class="col-xs-12 col-sm-12 col-md-9 col-lg-9">
	</s:if>
	 <s:elseif test='"new".equals(optionMode)'>
		<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
	</s:elseif>
	<s:else>
		<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
	</s:else>
		<div class="container vehDtl">
			<div class="Card_Parent card">
			<div class="panel-heading">
				<s:if test='%{borganization!=null && !"".equals(borganization)}'>
					<h3><s:property value="borganization"/>(<s:property value="agencyCode"/>)</h3><hr>
				</s:if>
				 <s:elseif test='"new".equals(optionMode)'>
				 	<h3>New Underwriter Creation</h3><hr>
				 </s:elseif>
				 <s:else>
				 	<h3>Enter Underwriter Info</h3><hr>
				 </s:else>
			</div>
			<div class="panel-body">
				<s:form id="info" name="underwriter" method="post" theme="simple">
				<s:if test="'successNew'.equals(display)">
					<div class="row">
						<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" align="center">	
							<span class="label label-md label-success" ><s:label key="user.new.success"/></span>
						</div>
					</div>
					<br/>
					<div class="row">
						<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" align="center">
							<input type="button" onclick="fnsubmit('back','getABListUnderwriterMgm', this.form)" name="back" class="btn btn-sm btn-danger" value="Back" />
						</div>
					</div>
				</s:if>
				<s:elseif test="'successUpdate'.equals(display)">
					<div class="row">
						<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" align="center">	
							<span class="label label-md label-success" ><s:label key="user.update.success"/></span>
						</div>
					</div>
					<br/>
					<div class="row">
						<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" align="center">
							<input type="button" onclick="fnsubmit('back','getABListUnderwriterMgm', this.form)" name="back" class="btn btn-sm btn-danger" value="Back" />
						</div>
					</div>
				</s:elseif>
				<s:else>
					<div class="row">
						<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">	
							<s:if test='!"product".equals(mode1) && !"login".equals(mode1)'>
								<div class="row">
									<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">	
										<s:actionerror cssStyle="color: red;" />
										<s:actionmessage cssStyle="color: green;" />
									</div>
								</div>
							</s:if>
							<s:if test='!"new".equals(optionMode)'>
								<div class="row">
									<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
										<div class="text"><s:text name="user.status"/></div>
										<div class="tbox">
											<s:select name="ustatus" list="#{'Y':'Active','N':'Deactive','D':'Delete','L':'Lock'}" headerKey="" headerValue="---Select---" cssClass="form-controlS" />
										</div>
									</div>
								</div>									
							</s:if>
							<div class="row">
								<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
									<div class="row">
										<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
											<div class="text"><s:text name="User Name" /> <font color="red">*</font></div>
											<div class="tbox">
												<s:textfield name="IssurName"  cssClass="form-control" size="35"/>
											</div>
										</div>
										<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
											<div class="text"><s:text name="Email Id" /> <font color="red">*</font></div>
											<div class="tbox"><s:textfield name="emailId"  cssClass="form-control" size="35"/></div>
										</div>
										<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
											<div class="text"><s:text name="Login Id" /> <font color="red">*</font></div>
											<div class="tbox"><s:textfield name="loginId"  cssClass="form-control" size="35"/></div>
										</div>
										<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
											<div class="text"><s:text name="Core Login Id" /> <font color="red">*</font></div>
											<div class="tbox"><s:textfield name="coreLoginId" maxlength="15" cssClass="form-control" size="35"/></div>
										</div>
										<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
											<div class="text">Password <font color="red">*</font></div>
											<div class="tbox"><s:password name="password"  cssClass="form-control" size="35"/></div>
										</div>
										<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
											<div class="text"><s:text name="Choose Products" /> <font color="red">*</font></div>
											<div class="tbox">
												<s:select name="products" id="products" list="productList"  listKey="PRODUCT_ID" listValue="PRODUCT_NAME" headerKey="" label="" multiple="true" />
											</div>
										</div>
										<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
											<div class="text"><s:text name="Attached Branch"/> <font color="red">*</font></div>
											<div class="tbox">
												<s:textarea name="branchId" id="branchId" cssClass="form-controlA" rows="2" readonly="true" cssStyle="width: 85%;"/>
												<input class="btn btn-sm btn-primary" value="..." style="float:right;" type="button" name="menu" onclick="return callPopup('${pageContext.request.contextPath}/branchSelectionUnderwriterMgm.do')"/>
											</div>
										</div>
										<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
											<div class="text"><s:text name="Referral Cases YN" /> <font color="red">*</font></div>
											<div class="tbox"><s:radio list="#{'Y':'Yes','N':'No' }" name="referralYN" id="referralYN" value="%{referralYN==null?'N':'Y'}"/></div>
										</div>
										<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
											<div class="text"></div>
											<div class="tbox"></div>
										</div>
									</div>
								</div>
							</div>
							<s:if test='"new".equals(mode)'>
							<div class="row">
								<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
									<div class="panel panel-primary">
										<div class="panel-heading">
											<s:label key="user.login.creation" />
										</div>
										<div class="panel-body">
											<div class="row">
												<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
													<div class="text"><s:label key="user.new" /> <font color="red">*</font></div>
													<div class="tbox">
														<s:textfield name="userId"  cssClass="form-control" size="35"/>
													</div>
												</div>
												<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
													<div class="text"><s:label key="user.password" /> <font color="red">*</font></div>
													<div class="tbox">
														<s:password name="password" cssClass="form-control" maxlength="20"/>
													</div>
												</div>
												<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
													<div class="text"><s:label key="user.rpassword" /> <font color="red">*</font></div>
													<div class="tbox">
														<s:password name="repassword" cssClass="form-control" maxlength="20"/>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
							</s:if>
							<hr class="clear" />
							<div class="row">
								<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" align="center">
									<input type="button" class="btn btn-sm btn-danger" value="Back" onclick="fnSubmit('getABListUnderwriterMgm.action');" > &nbsp;&nbsp;&nbsp;
									<input type="button" class="btn btn-sm btn-success" value="Submit" onclick="fnSubmit('insertIssuerUnderwriterMgm.action');" >
								</div>
							</div>
						</div>
					</div>
				</s:else>
				<s:hidden name="agencyCode"/>
				<s:hidden name="mode"/>
				<s:hidden name="mode1"/>
				<s:hidden name="type1"/>
				<s:hidden name="uagencyCode"/>
				<s:hidden name="borganization"/>
				<s:hidden name="optionMode"/>
				</s:form>
				<s:if test='!"new".equals(optionMode)'>
					<div class="panel panel-primary">
						<div class="panel-heading">
							<s:text name="Password Change" />
						</div>
						<div class="panel-body">
							<s:form id="newPwd" name="newPwd" method="post" theme="simple">
								<div class="row">
									<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">	
										<div class="panel panel-primary">
											<div class="panel-heading">
												<s:text name="Underwriter Management" />
											</div>
											<div class="panel-body">
												<s:if test='"login".equals(mode1)'>
													<div class="row">
														<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">	
															<s:actionerror cssStyle="color: red;" />
															<s:actionmessage cssStyle="color: green;" />
														</div>
													</div>
													<br/>
												</s:if>
												<div class="row">
													<div class="">
														<table width="100%">
															<tbody>
															<tr>
																<td width="30%"><s:label key="user.new"/> : </td>
																<td width="70%" colspan="2"><s:property value="ulogin_Id"/>( <s:property value="uagencyCode"/> )</td>
															</tr>	
															<tr>
																<td width="30%"><s:text name="broker.nameNpassword"/> : </td>
																<td width="30%"><s:password name="password" cssClass="form-control" maxlength="20" onkeyup="passwordStrength(this.value)"/></td>
																<td width="40%"><s:text name="broker.passwordmessage"/> </td>
															</tr>
															<tr align="center">
																<td width="30%">&nbsp;</td>
																<td colspan="2" align="left" style="padding-left: 10px;"><div id="passwordDescription">Password not entered</div><div id="passwordStrength" class="strength0"></div></td>
															</tr>
															<tr>
																<td width="30%"><s:label key="user.rpassword"/> : </td>
																<td width="30%"><s:password name="repassword" cssClass="form-control" maxlength="20"/> </td>
																<td width="40%">&nbsp;</td>
															</tr>
															<tr><td>&nbsp;</td></tr>
															<tr>
																<td colspan="4" align="center">
																	<input type="button" class="btn btn-sm btn-danger" value="Cancel" onclick="fnSubmit('getABListUserMgm.action');" > &nbsp;&nbsp;&nbsp;
																	<input type="button" class="btn btn-sm btn-success" value="Submit" onclick="fnSubmit('checkPwdUserMgm.action');" >
																</td>
															</tr>
															</tbody>
														</table>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
							<s:hidden name="index" value="2"/>
							<s:hidden name="borganization"/>
							<s:hidden name="agencyCode"/>
							<s:hidden name="firstname"/>
							<s:hidden name="uagencyCode"/>
							<s:hidden name="mode1"/>
							</s:form>
						</div>
					</div>
				</s:if> 
			</div>
		</div>
	</div>
	</div>
</div>
<script src="<%=request.getContextPath()%>/bootstrap/js/bootstrap-multiselect.js" type="text/javascript"></script>
</body>
<script type="text/javascript">
$(document).ready(function() {     
    $('#products').multiselect({ 
      includeSelectAllOption: true,
        enableFiltering:true,
        buttonText: function (options) {
        if (options.length == 0) {
            return 'None selected';
        } else {
            var selected = 0;
            options.each(function () {
                selected += 1;
            });
            return selected +  ' Selected ';
        }
    }   
  });            
});

function fnCall(from){
	if(from=='edit'){
		document.underwriter.action ="viewUnderwriterMgm.action";
		document.underwriter.mode.value="edit";
		}
	else if(from=='list'){
		document.underwriter.action = "UnderwriterCreationContoller.action";
		}
	else if(from=='changePwd'){
		document.underwriter.action = "changePassUnderwriterMgm.action";
		document.underwriter.mode.value="changePass";
		}	
	else if(from=='include'){
		document.underwriter.action = "includeIssuerUnderwriterMgm.action";
		document.underwriter.type1.value="include";
		}
	else if(from=='exclude'){
		document.underwriter.action = "excludeIssuerUnderwriterMgm.action";
		document.underwriter.type1.value="exclude";
		}
	else if(from=='openCover')
		document.info.action = "opencoverOC.action";
	else if(from=='statistics')
		document.info.action = "statisticsRE.action";
	else
		document.underwriter.action = from+"BrokerMgm.action";
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
$(document).ready(function() {
	try{
		var index = '<s:property value="index"/>';
		var t = $('#tabs');
		var tabs = t.tabs('tabs');
			t.tabs('select', tabs[index].panel('options').title);
	}catch(e){}
});
function forward1(agcode, id, value, mode1){
	postRequest('${pageContext.request.contextPath}/getUserAjaxUserMgm.action?reqFrom='+id+'&agencyCode='+agcode+'&productID='+value+'&mode1='+mode1, id);
	}
	
function fnsubmit(from, action, frm){
	frm.action=action+".action";
	frm.submit();
}
function branchSelection(){
  var params = "?branchSelected="+document.getElementById('branchSelected').value==null?'':document.getElementById('branchSelected').value+"&formName=underwriter";
  var URL ='${pageContext.request.contextPath}/branchSelectionUnderwriterMgm.do?branchSelected='+params;
  return callPopup(URL);
}

function callPopup(URL) {
 	var bwidth = window.innerWidth;
	var bwidth1 = document.body.clientWidth;
	if(bwidth <= 768) {
		return popUp(URL,bwidth1,'500');
	} else {
		return popUp(URL,'750','600');
	}
}
function fnSubmit(action){	
	document.underwriter.action =action;		
	document.underwriter.submit();
}
</script>
</html>