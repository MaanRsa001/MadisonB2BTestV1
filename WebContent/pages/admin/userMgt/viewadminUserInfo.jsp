<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE HTML>
<html>
<head>
</head>
<body>
<div class="row">	
	<div class="col-xs-12 col-sm-12 col-md-3 col-lg-3">
		<div class="container vehDtl">
            <div class="Card_Parent card">
				<s:if test='%{borganization!=null && !"".equals(borganization)}'>
					<div class="row">
						<div class="col-xs-6 col-sm-6 col-md-12 col-lg-12">
							<input type="button" class="btn btn-sm btn-info adminMenuBtn" value="Edit" onclick="fnCall('edituser')"/>
						</div>
						<div class="col-xs-6 col-sm-6 col-md-12 col-lg-12">
							<input type="button" class="btn btn-sm btn-info adminMenuBtn" value="Change Password" onclick="fnCall('changePwd')"/>
						</div>
					</div>
					<div class="row">
						<div class="col-xs-6 col-sm-6 col-md-12 col-lg-12">
							<input type="button" class="btn btn-sm btn-info adminMenuBtn" value="User Details" onclick="fnCall('userDetail')"/>
						</div>
						<div class="col-xs-6 col-sm-6 col-md-12 col-lg-12">
							<input type="button" class="btn btn-sm btn-info adminMenuBtn" value="Customer Details" onclick="fnCall('customerDetail')"/>
						</div>
					</div>
					<!-- <div class="row">
						<div class="col-xs-6 col-sm-6 col-md-12 col-lg-12">
							<input type="button" class="btn btn-sm btn-info adminMenuBtn" value="OpenCover" onclick="fnCall('openCover')"/>
						</div>
						<div class="col-xs-6 col-sm-6 col-md-12 col-lg-12">
							<input type="button" class="btn btn-sm btn-info adminMenuBtn" value="Referral" onclick="fnCall('referal')"/>
						</div>
					</div> -->
					<!-- <div class="row">
						<div class="col-xs-6 col-sm-6 col-md-12 col-lg-12">
							<input type="button" class="btn btn-sm btn-info adminMenuBtn" value="Statistics" onclick="fnCall('statistics')"/>
						</div>
					</div> -->
				</s:if>
				<s:else>
					<div class="row">
						<div class="col-xs-6 col-sm-6 col-md-12 col-lg-12">
							<input type="button" class="btn btn-sm btn-info adminMenuBtn" value="Edit" onclick="fnCall('edituser')"/>
						</div>
						<div class="col-xs-6 col-sm-6 col-md-12 col-lg-12">
							<input type="button" class="btn btn-sm btn-info adminMenuBtn" value="Customer Details" onclick="fnCall('customerDetail')"/>
						</div>
					</div>
				</s:else>
			</div>
		</div>
	</div>
	<div class="col-xs-12 col-sm-12 col-md-9 col-lg-9">	
		<div class="container vehDtl">
            <div class="Card_Parent card">
			<div class="panel-heading">
				<s:if test='%{borganization!=null && !"".equals(borganization)}'>
					<h3><s:property value="borganization"/>(<s:property value="agencyCode"/>)</h3><hr>
				</s:if>
				 <s:else>
				 	<h3>User Management</h3><hr>
				 </s:else>
			</div>
			<div class="panel-body">
				<s:form id="info" name="info" method="post" action="" theme="simple">
				<div class="row">
					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
						<s:if test='!"product".equals(mode1) && !"login".equals(mode1)'>
							<div class="row">
								<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">	
									<s:actionerror cssStyle="color: red;" />
									<s:actionmessage cssStyle="color: green;" />
								</div>
							</div>
							<br/>
						</s:if>
						<div class="row">
							<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
								<div class="panel panel-primary">
									<div class="panel-heading">
										<h3><s:text name="User Details" /></h3><hr>
									</div>
									<div class="panel-body">
										<div class="row">
											<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
												<table width="100%">
													<tbody>
													<tr>
														<td width="40%"><s:text name="user.broker"/></td>
														<%-- <td width="60%" style="font-weight: bold;">&nbsp;:&nbsp;<s:property value="brokerName"/></td> --%>
														<td width="60%" style="font-weight: bold;">&nbsp;:&nbsp;<s:property value="bname"/></td>
													</tr>
													</tbody>
												</table>
											</div>
											<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
												<table width="100%">
													<tbody>
													<tr>
														<td width="40%"><s:text name="user.type"/></td>
														<td width="60%" style="font-weight: bold;">&nbsp;:&nbsp;<s:property value="utype"/></td>
													</tr>
													</tbody>
												</table>
											</div>
											<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
												<table width="100%">
													<tbody>
													<tr>
														<td width="40%"><s:text name="user.userId"/></td>
														<td width="60%" style="font-weight: bold;">&nbsp;:&nbsp;<s:property value="userId"/></td>
													</tr>
													</tbody>
												</table>
											</div>
											<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
												<table width="100%">
													<tbody>
													<tr>
														<td width="40%"><s:text name="user.name"/></td>
														<td width="60%" style="font-weight: bold;">&nbsp;:&nbsp;<s:property value="uname"/></td>
													</tr>
													</tbody>
												</table>
											</div>
											<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
												<table width="100%">
													<tbody>
													<tr>
														<td width="40%"><s:text name="user.gender"/></td>
														<td width="60%" style="font-weight: bold;">&nbsp;:&nbsp;<s:property value='%{ugender=="M"?"Male":"Female"}'/></td>
													</tr>
													</tbody>
												</table>
											</div>
											<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
												<table width="100%">
													<tbody>
													<tr>
														<td width="40%"><s:text name="user.dob"/></td>
														<td width="60%" style="font-weight: bold;">&nbsp;:&nbsp;<s:property value="udob"/></td>
													</tr>
													</tbody>
												</table>
											</div>
											<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
												<table width="100%">
													<tbody>
													<tr>
														<td width="40%"><s:text name="user.occupation"/></td>
														<td width="60%" style="font-weight: bold;">&nbsp;:&nbsp;<s:property value="uoccupation"/></td>
													</tr>
													</tbody>
												</table>
											</div>
											<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
												<table width="100%">
													<tbody>
													<tr>
														<td width="40%"><s:text name="user.address1"/></td>
														<td width="60%" style="font-weight: bold;">&nbsp;:&nbsp;<s:property value="uaddress1"/></td>
													</tr>
													</tbody>
												</table>
											</div>
											<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
												<table width="100%">
													<tbody>
													<tr>
														<td width="40%"><s:text name="user.address2"/></td>
														<td width="60%" style="font-weight: bold;">&nbsp;:&nbsp;<s:property value="uaddress2"/></td>
													</tr>
													</tbody>
												</table>
											</div>
											<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
												<table width="100%">
													<tbody>
													<tr>
														<td width="40%"><s:text name="user.city"/></td>
														<td width="60%" style="font-weight: bold;">&nbsp;:&nbsp;<s:property value="ucity"/></td>
													</tr>
													</tbody>
												</table>
											</div>
											<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
												<table width="100%">
													<tbody>
													<tr>
														<td width="40%"><s:text name="user.country"/></td>
														<td width="60%" style="font-weight: bold;">&nbsp;:&nbsp;<s:property value="ucountryNa"/></td>
													</tr>
													</tbody>
												</table>
											</div>
											<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
												<table width="100%">
													<tbody>
													<tr>
														<td width="40%"><s:text name="user.pobox"/></td>
														<td width="60%" style="font-weight: bold;">&nbsp;:&nbsp;<s:property value="upobox"/></td>
													</tr>
													</tbody>
												</table>
											</div>
											<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
												<table width="100%">
													<tbody>
													<tr>
														<td width="40%"><s:text name="user.telephone"/></td>
														<td width="60%" style="font-weight: bold;">&nbsp;:&nbsp;<s:property value="uphone"/></td>
													</tr>
													</tbody>
												</table>
											</div>
											<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
												<table width="100%">
													<tbody>
													<tr>
														<td width="40%"><s:text name="user.mobile"/></td>
														<td width="60%" style="font-weight: bold;">&nbsp;:&nbsp;<s:property value="umobile"/></td>
													</tr>
													</tbody>
												</table>
											</div>
											<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
												<table width="100%">
													<tbody>
													<tr>
														<td width="40%"><s:text name="user.email"/></td>
														<td width="60%" style="font-weight: bold;">&nbsp;:&nbsp;<s:property value="uemail"/></td>
													</tr>
													</tbody>
												</table>
											</div>
											<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
												<table width="100%">
													<tbody>
													<tr>
														<td width="40%"><s:text name="user.fax"/></td>
														<td width="60%" style="font-weight: bold;">&nbsp;:&nbsp;<s:property value="ufax"/> </td>
													</tr>
													</tbody>
												</table>
											</div>
											<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
												<table width="100%">
													<tbody>
													<tr>
														<td width="40%"><s:text name="user.nationality"/></td>
														<td width="60%" style="font-weight: bold;">&nbsp;:&nbsp;<s:property value="unationalityName"/></td>
													</tr>
													</tbody>
												</table>
											</div>
											<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
												<table width="100%">
													<tbody>
													<tr>
														<td width="40%"><s:text name="user.loginid"/></td>
														<td width="60%" style="font-weight: bold;">&nbsp;:&nbsp;<s:property value="ulogin_Id"/></td>
													</tr>
													</tbody>
												</table>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
						<br class="clear" />
						<div class="row">
							<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" align="center">
							 
								<s:if test='%{borganization!=null && !"".equals(borganization)}'>
									<button type="button" name="submit1" class="btn btn-sm btn-danger" value="Back" onclick="fnBackBkr();">Back</button>
								</s:if>
								<s:else>
									<s:submit onclick="fnBack();" name="submit1" cssClass="btn btn-sm btn-danger" value=" Back " />
								</s:else>
							</div>
						</div>
					</div>
				</div>
				<s:hidden name="agencyCode"/>
				<s:hidden name="uagencyCode"/>
				<s:hidden name="login_Id"/>
				<%--<s:hidden name="borganization"/>--%>
				</s:form>
			</div>
		</div>
	</div>
</div>
</div>
<script type="text/javascript">
function fnCall(from){
	if(from=='edit')
		document.info.action = from+"BrokerMgm.action?mode=edit";
	else if(from=='userDetail')
		document.info.action = "getABListUserMgm.action?mode1=broker";
	else if(from=='edituser')
		document.info.action = "editUserMgm.action?mode1=broker&mode=edit";
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
function fnBack(){
		document.info.action = 'UserCreationContoller.action';
		document.info.submit();
}
function fnBackBkr(){
	document.info.action = 'getABListBrokerMgm.action';
	document.info.submit();
}
</script>
</body>
</html>