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
	<div class="col-xs-12 col-sm-12 col-md-3 col-lg-3">
		<div class="container vehDtl">
            <div class="Card_Parent card">
				<div class="panel-body">
					<div class="row">
						<div class="col-xs-6 col-sm-6 col-md-12 col-lg-12">
							<input type="button" class="btn btn-sm btn-info adminMenuBtn" value="Underwriter Details" onclick="fnCall('list')"/> 
						</div>
						<div class="col-xs-6 col-sm-6 col-md-12 col-lg-12">
							<input type="button" class="btn btn-sm btn-info adminMenuBtn" value="Change Password" onclick="fnCall('changePwd')"/>
						</div>
					</div>
					<div class="row">
						<div class="col-xs-6 col-sm-6 col-md-12 col-lg-12">
							<input type="button" class="btn btn-sm btn-info adminMenuBtn" value="Included Brokers" onclick="fnCall('include')"/> 
						</div>
						<div class="col-xs-6 col-sm-6 col-md-12 col-lg-12">
							<input type="button" class="btn btn-sm btn-info adminMenuBtn" value="Excluded Brokers" onclick="fnCall('exclude')"/>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="col-xs-12 col-sm-12 col-md-9 col-lg-9">
		<div class="container vehDtl">
            <div class="Card_Parent card">
			<%-- <div class="panel-heading">
				<s:text name="Under writer Info" />
				<div class="pullRight">
					<s:property value="loginId"/>
				</div>
				<br class="clear" />
			</div> --%>
			
			<div class="VehicleTableheading">
               <div class="row">
                  <div class="col-lg-6 col-md-6">
                    <h3><s:text name="Under writer Info" /></h3>
                  </div>
                  <div class="col-lg-3 offset-md-3 offset-lg-3 col-md-3">
                      <div  class="pullRight label label-warning">
                        <b><s:property value="loginId"/></b>
                    </div>
                  </div>
               </div>
            </div><hr>
			
			<div class="panel-body">
				<s:form id="info" name="underwriter" method="post" action="updateissuerUnderwriterMgm" theme="simple">
				<s:if test="'successNew'.equals(display)">
					<div class="row">
						<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" align="center">	
							<span class="label label-md label-success" ><s:label key="user.new.success"/></span>
						</div>
					</div>
					<br/>
					<div class="row">
						<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" align="center">	
							<input type="button" onclick="fnsubmit('back','getABListUserMgm', this.form)" name="back" class="btn btn-sm btn-danger" value="Back" />
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
							<input type="button" onclick="fnsubmit('back','getABListUserMgm', this.form)" name="back" class="btn btn-sm btn-danger" value="Back" />
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
								</div>
							</div>
							</s:if>
							<s:if test='!"new".equals(optionMode)'>
							<div class="row">
								<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
									<div class="text"><s:text name="user.status"/></div>
									<div class="tbox">
										<s:select name="ustatus" list="#{'Y':'Active','N':'Deactive','D':'Delete','L':'Lock'}" headerKey="" headerValue="---Select---" cssClass="form-control" />
									</div>
								</div>
							</div><br>									
							</s:if>
							<div class="row">
								<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
									<div class="panel panel-primary">
										<div class="panel-heading">
											<h3><s:text name="Edit Underwriter" /></h3><hr>
										</div>
										<div class="panel-body">
											<div class="row">
												<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
													<div class="text"><s:text name="User Name" /> <font color="red">*</font></div>
													<div class="tbox">
														<s:textfield name="issurName"  cssClass="form-control" size="35" />
													</div>
												</div>
												<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
													<div class="text"><s:text name="Email Id" /> <font color="red">*</font></div>
													<div class="tbox">
														<s:textfield name="emailId"  cssClass="form-control" size="35"/>
													</div>
												</div>
												<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
													<div class="text"><s:text name="Login Id" /> <font color="red">*</font></div>
													<div class="tbox">
														<s:textfield name="loginId"  cssClass="form-control" size="35" readonly="true"/>
													</div>
												</div>
												<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
													<div class="text"><s:text name="Core Login Id" /> <font color="red">*</font></div>
													<div class="tbox">
														<s:textfield name="coreLoginId" maxlength="3" cssClass="form-control" size="35"/>
													</div>
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
														<input class="btn btn-sm btn-primary" value="..." style="float:right;" type="button" name="menu" onclick="return callPopup('${pageContext.request.contextPath}/branchSelectionUnderwriterMgm.do','600','500')"/>
													</div>
												</div>
												<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
													<div class="text"><s:text name="Referral Cases YN" /> <font color="red">*</font></div>
													<div class="tbox"><s:radio list="#{'Y':'Yes','N':'No' }" name="referralYN" id="referralYN" value="%{(referralYN==null || referralYN=='') ?'N':'Y'}"/></div>
												</div>
											</div>
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
						</div>
					</div>
					<br class="clear" />
					<div class="row">
						<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" align="center">
							<input type="button" onclick="fnSubmit('UnderwriterCreationContoller.action');" class="btn btn-sm btn-danger" value="Back" >
							&nbsp;&nbsp;&nbsp;
							<s:submit name="submit2" cssClass="btn btn-sm btn-success" value="Submit" />
						</div>
					</div>
				</s:else>
				<s:hidden name="agencyCode"/>
				<s:hidden name="mode"/>
				<s:hidden name="mode1"/>
				<s:hidden name="uagencyCode"/>
				<s:hidden name="borganization"/>
				</s:form>
			</div>
		</div>
	</div>
	</div>
</div>
<script src="<%=request.getContextPath()%>/bootstrap/js/bootstrap-multiselect.js" type="text/javascript"></script>
</body>
<script type="text/javascript">
function fnCall(from){
	if(from=='edit')
		document.underwriter.action ="viewUnderwriterMgm.action?mode=edit";
	else if(from=='list')
		document.underwriter.action = "UnderwriterCreationContoller.action";
	else if(from=='changePwd')
		document.underwriter.action = "changePassUnderwriterMgm.action?mode=changePass";	
	else if(from=='include')
		document.underwriter.action = "includeIssuerUnderwriterMgm.action?type1=include";
	else if(from=='exclude')
		document.underwriter.action = "excludeIssuerUnderwriterMgm.action?type1=exclude";
	else if(from=='openCover')
		document.info.action = "opencoverOC.action";
	else if(from=='statistics')
		document.info.action = "statisticsRE.action";
	<%--alert(from);
		postRequest('${pageContext.request.contextPath}/getABListCustomerMgm.action?agencyCode=10016&borganization='+'<s:property value="borganization"/>'+'&bcode='+'<s:hidden name="bcode"/>'+'&mode=mainTab', 'mainTab');
	}--%>else
		document.underwriter.action = from+"BrokerMgm.action";
	document.underwriter.submit();
}
function branchSelection(){
  var params = "?branchSelected="+document.getElementById('branchSelected').value==null?'':document.getElementById('branchSelected').value;
  var URL ='${pageContext.request.contextPath}/branchSelectionUnderwriterMgm.do?branchSelected='+params;
  return callPopup(URL);
}
function callPopup(URL) {
 	var bwidth = window.innerWidth;
	var bwidth1 = document.body.clientWidth;
	if(bwidth <= 768) {
		return popUp(URL,bwidth1,'500');
	} else {
		return popUp(URL,'750','500');
	}
}
$(document).ready(function() {     
      $('#products').multiselect({ 
        includeSelectAllOption: true,
          enableFiltering:true                   
    });            
});
function fnSubmit(action){	
	document.underwriter.action =action;		
	document.underwriter.submit();
}
</script>
</html>