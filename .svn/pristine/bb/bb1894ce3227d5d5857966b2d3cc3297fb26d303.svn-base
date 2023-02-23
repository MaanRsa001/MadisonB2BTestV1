	<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/WEB-INF/displaytag.tld" prefix="display"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE HTML>
<html>
<head>
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/bootstrap/css/easy-responsive-tabs.css" />
	<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/bootstrap/css/bootstrap-multiselect.css" />
	<script type="text/javascript">
	
	$(function() {
		try {
		var dt = new Date();
		dt.setFullYear(new Date().getFullYear()-18);
		
		$('#dob').datepicker({
			todayHighlight: true,
	       	format: "dd/mm/yyyy",
		  	viewMode: "years",
		  	endDate: dt
		}).on('changeDate', function(e){
	           $(this).datepicker('hide');
	       });
		
	} catch(err) {console.error(err);}
	});
	</script>
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
					<s:if test='%{borganization!=null && !"".equals(borganization)}'>
						<div class="row">
							<div class="col-xs-6 col-sm-6 col-md-12 col-lg-12">
								<input type="button" class="btn btn-sm btn-info adminMenuBtn" value="View" onclick="fnCall('viewuser')"/>
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
				 	<h3><b>New User Creation</b></h3><hr>
				 </s:elseif>
				 <s:else>
				 	<h3><b>User Management</b></h3><hr>
				 </s:else>
			</div>
			<div class="panel-body">			
				<div id="horizontalTab">
					<ul class="resp-tabs-list">
						<li><s:text name="user.usermanagement" /></li>
						<li><s:text name="Product Selection" /></li>
						<!--<s:if test='!"new".equals(mode)'>
							<li><s:text name="Password Change" /></li>
						</s:if>-->
					</ul>
					<div class="resp-tabs-container">
						 <s:if test="hasActionErrors()" >
						 	<s:set var="responsiveTab" value='%{"1"}'/>												 	
						 </s:if>
						<div id='<s:property value="%{responsiveTab}" default="1"/>'>
							<s:form id="info" name="info" method="post" action="newUserUserMgm" theme="simple">
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
														<s:actionmessage cssStyle="color: green;" />
													</div>
												</div>
												<br/>
											</s:if>
											<s:if test='!"new".equals(optionMode)'>
												<div class="row">
													<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
														<div class="text"><s:text name="user.status"/></div>
														<div class="tbox">
															<s:select name="ustatus" list="#{'Y':'Active','N':'Deactive','D':'Delete','L':'Lock'}" headerKey="" headerValue="---Select---" cssClass="form-control" />
														</div>
													</div>
												</div>									
											</s:if>
											<div class="row mt-5">
												<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
													<div class="panel panel-primary">
														<div class="panel-heading">
															<h3><s:text name="user.contactpersonInfo" /></h3><hr>
														</div>
														<div class="panel-body">
															<div class="row">
																<s:if test='"edit".equals(mode)'>
																	<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
																		<div class="text"> <s:text name="user.broker" /> </div>
																		<div class="tbox">
																			<s:if test='"".equals(borganization) || borganization==null'>
					                           									<s:select name="broker" list="brokerList" listKey="AGENCY_CODE" listValue="COMPANY_NAME" cssClass="form-control" headerKey="" headerValue="---Select---" />
					                           								</s:if>
					                           								<s:else>
					                           									<s:property value="borganization"/>
					                           								</s:else>
																		</div>
																	</div>
																	<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
																		<div class="text"> <s:text name="user.type" /> </div>
																		<div class="tbox"> <s:property value="utype"/> </div>
																	</div>
																	<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
																		<div class="text"> <s:text name="user.userId" /> </div>
																		<div class="tbox"> <s:property value="userId" /> <s:hidden name="userId" id="userId"/> </div>
																	</div>
																</s:if>
																<s:else>
																	<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
																		<div class="text"> <s:text name="user.broker" /> <font color="red">*</font></div>
																		<div class="tbox">
																			<s:if test='"".equals(borganization) || borganization==null'>
					                           									<s:select name="broker" list="brokerList" listKey="AGENCY_CODE" listValue="COMPANY_NAME" cssClass="form-control" headerKey="" headerValue="---Select---" onchange="userBranch(this.value,'userBranchList');"/>
					                           								</s:if>
					                           								<s:else>
					                           									<s:property value="borganization"/>
					                           								</s:else>
																		</div>
																	</div>
																	<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
																		<div class="text"> <s:text name="user.type" /> <font color="red">*</font></div>
																		<div class="tbox">
																			<s:textfield name="utype" cssClass="form-control" size="35" disabled="true"/>
																		</div>
																	</div>
																</s:else>
																<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
																	<div class="text"> <s:text name="B2C" /> </div>
																	<div class="tbox">
																		<s:radio name="isb2c" id="isb2c" list="#{'Y':'Yes','N':'No'}"  value='%{(isb2c==null || "".equalsIgnoreCase(isb2c))?"N":isb2c}'/>
																	</div>
																</div>
															</div>
															<div class="row">
																<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
																	<div class="text"> <s:text name="user.title" /> <font color="red">*</font></div>
																	<div class="tbox">
																		<s:select name="utitle" id="title" list="titlesInfo" cssClass="form-control" listKey="TITLE_ID" listValue="TITLE_NAME"  headerKey="" headerValue="---Select---"/>
																	</div>
																</div>
																<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
																	<div class="text"> <s:text name="user.firstname" /> <font color="red">*</font></div>
																	<div class="tbox">
																		<s:textfield name="ufirstname" cssClass="form-control" size="35" maxlength="30"  />
																	</div>
																</div>
																<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
																	<div class="text"> <s:text name="user.lastname" /> </div>
																	<div class="tbox">
																		<s:textfield name="ulastname" cssClass="form-control" size="35" maxlength="30"/>
																	</div>
																</div>
																<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
																	<div class="text"> <s:text name="user.gender" /> </div>
																	<div class="tbox">
																		<s:radio name="ugender" id="ugender" list="#{'M':'Male','F':'Female'}" />
																	</div>
																</div>
																<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
																	<div class="text"> <s:text name="user.nationality" /> <font color="red">*</font></div>
																	<div class="tbox">
																		<s:select name="unationality" list="nationalitiesInfo" listKey="COUNTRY_ID" listValue="NATIONALITY_NAME" headerKey="" headerValue="---Select---" cssClass="form-control" />
																	</div>
																</div>
																<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
																	<div class="text"> <s:text name="user.dob" /> </div>
																	<div class="tbox">
																		<s:textfield id="udob" name="udob" cssClass="form-control" size="35" readonly="true" />
																	</div>
																</div>
																<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
																	<div class="text"> <s:text name="user.occupation" /> </div>
																	<div class="tbox">
																		<s:textfield name="uoccupation"  cssClass="form-control" size="35" maxlength="30"/>
																	</div>
																</div>
																<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
																	<div class="text"> <s:text name="user.address1" /> </div>
																	<div class="tbox">
																		<s:textfield name="uaddress1" id="address1" cssClass="form-control" size="35" maxlength="50"/>
																	</div>
																</div>
																<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
																	<div class="text"> <s:text name="user.address2" /> </div>
																	<div class="tbox">
																		<s:textfield name="uaddress2" id="address2" cssClass="form-control" size="35" maxlength="50"/>
																	</div>
																</div>
																<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
																	<div class="text"> <s:text name="user.city" /> <font color="red">*</font></div>
																	<div class="tbox">
																		<s:select name="ucity" id="emirate" cssClass="form-control" list="emiratesInfo" listKey="CITY_NAME" listValue="CITY_NAME"  headerKey="" headerValue="---Select---" />
																	</div>
																</div>
																<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
																	<div class="text"> <s:text name="user.country" /> <font color="red">*</font></div>
																	<div class="tbox">
																		<s:select name="ucountry" id="country" list="countriesInfo" cssClass="form-control" listKey="COUNTRY_ID" listValue="COUNTRY_NAME" />
																	</div>
																</div>
																<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
																	<div class="text"> <s:text name="user.pobox" /> <font color="red">*</font></div>
																	<div class="tbox">
																		<s:textfield name="upobox" id="pobox" cssClass="form-control" size="35" maxlength="6"/>
																	</div>
																</div>
																<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
																	<div class="text"> <s:text name="user.telephone" /> </div>
																	<div class="tbox">
																		<s:textfield name="uphone" id="telephone"  cssClass="form-control" size="35" maxlength="15"/>
																	</div>
																</div>
																<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
																	<div class="text"> <s:text name="Mobile" /> <font color="red">*</font></div>
																	<div class="tbox">
																		<s:textfield name="umobile" data-content="Your primary mobile number without spaces e.g. 0977777777" maxLength="10" onkeyup="checkNumbers(this);" cssClass="form-control tooltipContent empyCustDetails" size="35" />
																	</div>
																</div>
																<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
																	<div class="text"> <s:text name="user.email" /> <font color="red">*</font> </div>
																	<div class="tbox">
																		<s:textfield name="uemail" cssClass="form-control" size="35" maxlength="30"/>
																	</div>
																</div>
																<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
																	<div class="text"> <s:text name="user.fax" /> </div>
																	<div class="tbox">
																		<s:textfield name="ufax" id="ufax" cssClass="form-control" size="35" maxlength="10"/>
																	</div>
																</div>
																<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
																	<div class="text"><s:text name="user.branch"/> <font color="red">*</font></div>
																	<div class="tbox">							
																		<div id="userBranchList">
																			<s:select name="branchId" id="ubranch" list="branchList"  listKey="BRANCH_CODE" listValue="BRANCH_NAME" multiple="true" />
																		</div>
																	</div>
																</div>
																<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
																	<div class="text"><s:text name="Broker Branch"/> </div>
																	<div class="tbox">
																		<s:textarea name="subBranchId" id="subBranchId" cssClass="form-controlA" rows="2" cssStyle="width: 85%;"/>
																		<input class="btn btn-sm btn-primary" value="..." style="float:right;" type="button" name="menu" onclick="return clickMenuPopup('${pageContext.request.contextPath}/subBranchUserMgm.action')"/>							
																	</div>
																</div>
															</div>
														</div>
													</div>
												</div>
											</div>
											<s:if test='"new".equals(mode)'>
											<div class="row mt-5">
												<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
													<div class="panel panel-primary">
														<div class="panel-heading">
															<h3><s:text name="user.login.creation" /></h3><hr>
														</div>
														<div class="panel-body">
															<div class="row">
																<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
																	<div class="text"> <s:text name="user.new" /> <font color="red">*</font> </div>
																	<div class="tbox">
																		<s:textfield name="userId"  cssClass="form-control" size="35" maxlength="15"/>
																	</div>
																</div>
																<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
																	<div class="text"> <s:text name="user.password" /> <font color="red">*</font> </div>
																	<div class="tbox">
																		<s:password name="password" cssClass="form-control" maxlength="20"/>
																	</div>
																</div>
																<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
																	<div class="text"> <s:text name="user.rpassword" /> <font color="red">*</font> </div>
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
											<br class="clear" />
											<div class="row">
												<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" align="center">
													<input type="button" name="submit1" class="btn btn-sm btn-danger" onclick="funSubmitAction(this.form,'getABListUserMgm')"  value="Back" /> &nbsp;&nbsp;&nbsp;
													<s:submit name="submit2" cssClass="btn btn-sm btn-success" value="Submit" />
												</div>
											</div>
										</div>
									</div>
								</s:else>
								<s:hidden name="agencyCode"/>
								<s:hidden name="mode"/>
								<s:hidden name="mode1"/>
								<s:hidden name="uagencyCode"/>
								<s:hidden name="borganization"/>
								<s:hidden name="login_Id" value="%{#ulogin_Id}"/>
								</s:form>
						</div>
						<div id='<s:property value="%{responsiveTab}" default="2"/>'>
							<div class="row">
								<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">	
									<s:form id="form1" name="form1" method="post" action="" theme="simple">
									<s:if test='"product".equals(mode1)'>
										<div class="row">
											<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">	
												<s:actionerror cssStyle="color: red;" />
												<s:actionmessage cssStyle="color: green;" />												
												 <s:if test="hasActionErrors()" >
												 	<s:set var="responsiveTab" value='%{"2"}'/>												 	
												 </s:if>
											</div>
										</div>
										<br/>
									</s:if>
									<div class="row mt-5">
										<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
											<div class="panel panel-primary">
												<div class="panel-heading">
													<h3><s:text name="user.product" /></h3><hr>
												</div>
												<div class="panel-body">
													<s:if test='"updatesuccess".equals(display)'>
														<div class="row">
															<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" align="center">	
																<span class="label label-md label-success" ><s:label key="product.modify.success"/></span>
															</div>
														</div>
														<br/>
														<div class="row">
															<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" align="center">
																<input type="button" onclick="fnsubmit('back','getABListUserMgm', this.form)" name="back" class="btn btn-sm btn-danger" value="Back" />
															</div>
														</div>
													</s:if>
													<s:elseif test='"newsuccess".equals(display)'>
														<div class="row">
															<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" align="center">	
																<span class="label label-md label-success" ><s:label key="product.new.success"/></span>
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
															<s:if test='commisionDetails.size()>0'>
																<div id="commisionDetailss">
																	<table class="table table-bordered table-hover" cellspacing="0" width="100%" id="tadaTableNew">
																		<thead class="bluecolortable" >
																		<tr>
																			<th class="no-sort"></th>
																			<th><s:text name="Products" /></th>
																			<th><s:text name="Special Discount" /></th>
																			<th><s:text name="Sum Insured" /></th>
																			<th><s:text name="Provision for Credit" /></th>
																		</tr>
																		</thead>
																		<tbody class="rowevencolor">																	
																		<s:iterator value="commisionDetails" var="cdet" id="cdet" status="status">
																			<tr>
																				<td align="center"><s:checkbox name="product[%{#status.index}]" value='%{#cdet.PRODUCT=="Y"?true:false}'/></td>
																				<td align="center"><s:property value="%{#cdet.uproductName}"/></td><s:hidden name="uproductId[%{#status.index}]" value="%{#cdet.uproductId}"/><s:hidden name="uproductName[%{#status.index}]" value="%{#cdet.uproductName}"/>
																				<s:if test='%{uproductId!="11"}'>
																					<td align="center"><s:textfield name="specialDis[%{#status.index}]" value="%{#cdet.specialDis}" cssClass="form-control" maxlength="15"/></td>
																					<td align="center"><s:textfield name="insEndLimit[%{#status.index}]" value="%{#cdet.insEndLimit}" cssClass="form-control" maxlength="15"/></td>
																				</s:if>
																				<s:else>
																					<td colspan="2" align="center">
																						<input type="button" name="opencov" value="Open Cover Certificate"  onclick="return callPopup('${pageContext.request.contextPath}/getOCCertificateUserMgm.action?uagencyCode=${uagencyCode}&agencyCode=${agencyCode}')" class="btn btn-sm btn-warning" />
																							<s:hidden name="specialDis[%{#status.index}]" value="%{#cdet.specialDis}"/>
																							<s:hidden name="insEndLimit[%{#status.index}]" value="%{#cdet.insEndLimit}"/>
																							<s:hidden name="openCover" id="openCover" value="%{#cdet.open_cover_no}"/>
																					</td>																					
																				</s:else>
																				<s:hidden name="receipt[%{#status.index}]" value="N"></s:hidden>
																				<td align="center"> 
																				<s:radio name="freight[%{#status.index}]" list="#{'Y':'Yes','N':'No'}"  value="%{#cdet.freight==null?'N':#cdet.freight}"/></td>																			
																			</tr>
																		</s:iterator>																	
																	</tbody>
																</table>
																</div>
																<br class="clear" />
																<div align="center">
																	<input type="button"  name="submit1" class="btn btn-sm btn-danger" value="Back" onclick="fnsubmit('back','getABListUserMgm', this.form)"/> &nbsp;&nbsp;&nbsp;
																	<input type="button" class="btn btn-sm btn-success" value="Submit" onclick="funSubmitAction(this.form,'addProductUserMgm')" />
																</div>
															</s:if>
															<s:else> No products found</s:else>	
															</div>
														</div>
													</s:else>
												</div>
											</div>
										</div>
									</div>
									<s:hidden name="agencyCode"/>
									<s:hidden name="borganization"/>
									<s:hidden name="uagencyCode"/>
									<s:hidden name="mode1"/>
									<s:hidden name="login_Id" value="%{#ulogin_Id}"/>
									</s:form>
								</div>
							</div>
						</div>
						<s:if test='!"new".equals(mode)'>
						<div id="3">
							<s:form id="newPwd" name="newPwd" method="post" theme="simple">
								<div class="row mt-5">
									<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">	
										<div class="panel panel-primary">
											<div class="panel-heading">
												<h3><s:text name="user.usermanagement" /></h3><hr>
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
												<!--<div class="row">
													<div class="">
														<table width="100%">
															<tbody>
															<tr>
																<td width="30%"><s:label key="user.new"/> : </td>
																<td width="70%" colspan="2"> <s:property value="ulogin_Id"/>( <s:property value="uagencyCode"/> ) </td>
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
																	<input type="button" class="btn btn-sm btn-danger" value=" Cancel " onclick="funSubmitAction(this.form,'getABListUserMgm')" /> &nbsp;&nbsp;&nbsp;
	               													<input type="button" class="btn btn-sm btn-success" value=" Submit "  onclick="funSubmitAction(this.form,'checkPwdUserMgm')" />
																</td>
															</tr>
															</tbody>
														</table>
													</div>
												</div>-->
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
								<s:hidden name="login_Id" value="%{#ulogin_Id}"/>
							</s:form>
						</div>
						</s:if>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script src="<%=request.getContextPath()%>/bootstrap/js/easy-responsive-tabs.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/bootstrap/js/bootstrap-multiselect.js" type="text/javascript"></script>
<script type="text/javascript">
$('#horizontalTab').easyResponsiveTabs({
	type: 'default', //Types: default, vertical, accordion           
	width: 'auto', //auto or any width like 600px
	fit: true,   // 100% fit in a container
	closed: 'accordion'
});
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
$(function(){
	var dt = new Date();
	dt.setFullYear(new Date().getFullYear()-18);
	$('#udob').datepicker({
		todayHighlight: true,
    	format: "dd/mm/yyyy",
	  	viewMode: "years",
	  	endDate: dt
	}).on('changeDate', function(e){
	    $(this).datepicker('hide');
	});
});
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
function callPopup(URL) {
 	var bwidth = window.innerWidth;
	var bwidth1 = document.body.clientWidth;
	if(bwidth <= 768) {
		return popUp(URL,bwidth1,'500');
	} else {
		return popUp(URL,'750','500');
	}
}
function funSubmitAction(frm,action){
	frm.action = action+".action";
	frm.submit();
}
function userBranch(agencyCode,id){
	postRequest('${pageContext.request.contextPath}/userBranchAjaxUserMgm.action?reqFrom='+id+'&agencyCode='+agencyCode, id);
}
$(document).ready(function() {     
    $('#ubranch').multiselect({ 
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
function clickMenuPopup(url){	 
	var checkedItems='';
	try
	{
		var elements=document.getElementById("ubranch");		 
		for(i=0; i<elements.length; i++)
		{
			obj=elements[i];
			if(obj.selected)
				checkedItems+=obj.value+',';
		}
		//var menuId=document.getElementById("mid").value;
	}catch(e){}	 
	if(checkedItems.length>1)
		checkedItems=checkedItems.substring(0, checkedItems.length-1);	 
	if(checkedItems!=null && checkedItems!="")
		return callPopup(url+'?branchId='+checkedItems);
	else
		alert('Please Select Attached Branch');	
}
</script>
</body>
</html>