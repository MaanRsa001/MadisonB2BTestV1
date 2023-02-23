<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<%--<style type="text/css">
.otpHide {
  display: none;
}
</style>
--%></head>
	<body>
<!-- 	<div class="row"> -->
		<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
			<div class="panel panel-vehicle">
				<div class="panel-heading" align="center">
						<s:text name="label.otp.verify" />
					<br class="clear"/>
			    </div>
				<div class="panel-body">
				 <s:if test='hasActionErrors()'>
					<div class="row">
						<div class="col-xs-6 col-sm-6 col-md-6 col-lg-6" align="left">
							<span style="color: red;"><s:actionerror /></span>
						</div>
					</div>
				</s:if>
					<div class="row">
						<div class="col-xs-6 col-sm-6 col-md-6 col-lg-6" align="center" id="otpRegen">
							<span style="color: green;"><s:actionmessage /></span>
						</div>
					</div>
					<div class="row">
					<div class="col-xs-3 col-sm-3 col-md-3 col-lg-3">
					</div>
					<div class="col-xs-6 col-sm-6 col-md-6 col-lg-6" align="center">
						<!--<s:text name="OTP Num" /> : <s:property value="myOtp"/>-->
						<div class="text"><b><s:text name="label.otp.number" /></b><font color="red">*</font></div>
						<div class="tbox" dir="ltr">
						
							<s:textfield name="otp" id="otp"  cssClass="form-control tooltipContent"  maxlength="10" />
						
							<button type="button" id="reotp" style="display: none;" class="btn btn-sm btn-warning" onclick="getOtpRegen('otpRegen');">
								<s:text name="label.regen.otp" />
							</button>
							<s:hidden name="otpId" id="otpId" />
							<s:hidden name="otpStatus" id="otpStatus"/>
							<s:hidden name="mobileNo" id="mobileNo"/>
							<s:hidden name="title" id="title"/>
							<s:hidden name="customerName" id="customerName"/>
							<s:hidden name="custLastName" id="custLastName"/>
							<s:hidden name="email" id="email"/>
							<s:hidden name="mobileNum" id="mobileNum"/>
							<s:hidden name="customerType" id="customerType"/>
						</div>
						<br class="clear">
						<br class="clear">
						<div align="center">
							<span style="color: red;" ><s:text name="label.otp.timing" /></span>
						</div>
					</div>
					<div class="col-xs-3 col-sm-3 col-md-3 col-lg-3">
					</div>
					</div>
					<br class="clear" />
				</div>
			</div>
		</div>
<!-- 	</div> -->
</body>
<script  type="text/javascript">

function getOtpRegen(id){
		otpId=document.getElementById("otpId").value;
		mobile=document.getElementById("mobileNo").value;
		postRequest('${pageContext.request.contextPath}/regenAjaxOtp.action?otpId='+otpId+'&mobile='+mobile+'&reqFrom='+id, id);
		document.getElementById("reotp").style.display = "none";
		setTimeout(function(){
			document.getElementById("reotp").style.display = "block";
		 }, 60000);
		}

setTimeout(function(){
		document.getElementById("reotp").style.display = "block";
	 }, 60000);

</script>
</html>	