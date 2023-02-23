<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@page contentType="text/html" pageEncoding="UTF-8" isELIgnored="false"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/WEB-INF/displaytag.tld" prefix="display"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
	<head>
		<title>MarineME</title>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<meta name="description" content="">
		<meta name="keywords" content="Maan Sarovar">
		<meta name="author" content="">
		
	</head>
	<body>
		<s:if test="'forgetPwd'.equals(reqFrom)">
			<div id="error">
				<font style="list-style: none; color: red;"> <s:actionerror/> </font>
				<s:if test='!hasActionMessages()'>
					<div class="form-group">
						<s:text name="Username"></s:text>
						<s:textfield cssClass="form-control" name="userLoginId1" id="loginId" maxlength="20" />
					</div>
					<div class="form-group">
						<s:text name="Email"></s:text>
						<s:textfield cssClass="form-control" name="userEmail" id="email" maxlength="20" />
					</div>
					<a href="#" class="btn btn-primary" onclick="fnForgetPwd()">Submit</a>
				</s:if>
				<s:else>
					<font color="green"><s:text name="forgot.pass.success"></s:text> </font>
				</s:else>
			</div>
		</s:if>
	</body>
</html>