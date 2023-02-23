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
		$('#effectiveDate').datepicker({
			todayHighlight: true,
	       	format: "dd/mm/yyyy",
		  	viewMode: "years",
		  	endDate: dt
		}).on('changeDate', function(e){
	           $(this).datepicker('hide');
	       });
		$('#endDate').datepicker({
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
	<style>
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
<s:if test='!"new".equals(mode)'>
<s:form id="infoNew" name="infoNew" method="post" action="" theme="simple">
	<div class="row">
	<div class="col-xs-12 col-sm-12 col-md-3 col-lg-3">
		<div class="container vehDtl">
			<div class="Card_Parent card">
				<div class="row">
					<div class="col-xs-6 col-sm-6 col-md-12 col-lg-12">
						<input type="button" class="btn btn-sm btn-info adminMenuBtn" value="View" onclick="fnCallNew('view');"/>
					</div>
					<div class="col-xs-6 col-sm-6 col-md-12 col-lg-12">
						 <input type="button" class="btn btn-sm btn-info adminMenuBtn" value="Change Password" onclick="fnCallNew('changePwd')"/>
					</div>
				</div>
				<div class="row">
					<div class="col-xs-6 col-sm-6 col-md-12 col-lg-12">
						<input type="button" class="btn btn-sm btn-info adminMenuBtn" value="User Details" onclick="fnCallNew('userDetail')"/>
					</div>
					<div class="col-xs-6 col-sm-6 col-md-12 col-lg-12">
						 <input type="button" class="btn btn-sm btn-info adminMenuBtn" value="Customer Detail" onclick="fnCallNew('customerDetail')"/>
					</div>
				</div>
				<!-- <div class="row">
					<div class="col-xs-6 col-sm-6 col-md-12 col-lg-12">
						<input type="button" class="btn btn-sm btn-info adminMenuBtn" value="OpenCover" onclick="fnCallNew('openCover')"/>
					</div>
					<div class="col-xs-6 col-sm-6 col-md-12 col-lg-12">
						 <input type="button" class="btn btn-sm btn-info adminMenuBtn" value="Referral" onclick="fnCallNew('referal')"/>
					</div>
				</div> -->
			</div>
		</div>
	</div>
	<%-- <s:hidden name="agencyCode"/> --%>
	</s:form>	
</s:if>
<s:if test='"new".equals(mode)'>
	<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
		<div class="container vehDtl">
			<div class="Card_Parent card">
				<!-- <div class="card"> --> 
				<h3><s:text name="New Broker Creation" /></h3><hr> 
			
			<div class="panel-body">				
</s:if>
<s:else>
	<div class="col-xs-12 col-sm-12 col-md-9 col-lg-9">
		<div class="container vehDtl">
			<div class="Card_Parent card"> 
				<div class="VehicleTableheading">
	               <div class="row">
	                  <div class="col-lg-6 col-md-6">
	                    <h3><s:text name="broker.brokermanagement" /></h3>
	                  </div>
	                  <div class="col-lg-3 offset-md-3 offset-lg-3 col-md-3">
	                      <div  class="pullRight label label-warning">
	                        <b><s:property value="borganization"/>(<s:property value="agencyCode"/>)</b>
	                    </div>
	                  </div>
	               </div>
	            </div><hr>
			<div class="panel-body">
</s:else>
			
			<div class="row">
				<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
					
					<%-- <s:else> --%>
						<div class="row">
							<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
								<ul class="nav nav-tabs" role="tablist">
								      <li id="tab1" class="nav-item ">
								           <a class='nav-link in active' data-toggle="tab" href="#deduc1"><s:text name="Broker Management" /></a>
								      </li>
								      <li id="tab2" class="nav-item ">
								           <a class='nav-link' data-toggle="tab" href="#deduc2"><s:text name="Product Selection" /></a>
								      </li>
							      </ul>
									
								<div >
									<%-- <ul class="resp-tabs-list">
										<li><s:text name="user.usermanagement" /></li>
										<li><s:text name="Product Selection" /></li>
									</ul> --%>
									<div class="tab-content">
										<div id="deduc1" class="tab-pane fade in active show">
											<s:if test='!("newAjax".equals(mode1) || "editAjax".equals(mode1))'>
												<div class="row">
													<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
														<s:actionerror cssStyle="color:red;" /> <s:actionmessage cssStyle="color:green;" />
													</div>
												</div>
											<br class="clear" />
											</s:if>
											<s:form id="BrokerInfoEdit" name="BrokerInfoEdit" method="post" action="" theme="simple" enctype="multipart/form-data">
											<s:if test="'successNew'.equals(display)">
												<div class="row">
													<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
														<span class="label label-success"> <s:text name="broker.new.success"/> </span>
													</div>
												</div>
												<br class="clear" />
											<%--<div class="row">
													<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" align="center">
														<input type="button" onclick="fnsubmit('back')" name="back" class="btn btn-sm btn-danger" value="Back" />
													</div>
												</div> --%>	
												</s:if>
											<s:elseif test="'successUpdate'.equals(display)">
												<div class="row">
													<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
														<span class="label label-success"> <s:text name="broker.update.success"/> </span>
													</div>
												</div>
												<br class="clear" />
												<%--<div class="row">
													<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" align="center">
														<button type="button" onclick="fnsubmit('back')" name="back" class="btn btn-sm btn-danger" value="Back" >Back</button>
													</div>
												</div> --%>
												<s:hidden name="othercity"></s:hidden>
											</s:elseif>
											
											<s:elseif test='"edit".equals(mode)'>
												<div class="row">
													<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
														<div class="text"><s:text name="broker.status"/><font color="red">*</font></div>
														<div class="tbox">
															<s:select name="status" list="#{'Y':'Active','N':'Deactive','D':'Delete','L':'Lock'}" headerKey="" headerValue="---Select--" cssClass="form-control" />
														</div>
													</div>
												</div>
												<br class="clear" />
											</s:elseif>
											<div class="row">
												<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
													<div class="panel panel-primary">
														<div class="panel-heading"> 
														<h3><s:text name="broker.companyinfo" /></h3><hr> 
														</div>
														<div class="panel-body">
															<div class="row">
																<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
																	<div class="text"><s:text name="broker.approve"/> <font color="red">*</font></div>
																	<div class="tbox">
																		<s:textfield name="approvedby" id="approvedby" cssClass="form-control" size="35" maxlength="30"/>
																	</div>
																</div>
																<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
																	<div class="text"><s:text name="User Type" /> <font color="red">*</font></div>
																	<div class="tbox">
																		<s:select name="brokerUsertype" id="brokerUsertype"  cssClass="form-control" list="#{'BR':'Broker','AG':'Agent','DC':'Direct'}" headerKey="" headerValue="-----Select-----"/>
																	</div>
																</div>
																<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
																	<div class="text"><s:text name="broker.brokercode" /> <font color="red">*</font></div>
																	<div class="tbox">
																		<s:textfield name="bcode" id="bcode"  cssClass="form-control"  size="35" maxlength="10" />
																	</div>
																</div>
																<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
																	<div class="text"><s:text name="broker.brokerOrg" /> <font color="red">*</font></div>
																	<div class="tbox">
																		<div class="tbox">
						                           							<%-- <s:textfield name="borganization" id="borganization" cssClass="form-control" cssStyle="border: none;background: transparent;" maxlength="70"/> --%>
						                           							<%--<s:submit type="button" value="..." cssClass="inputButton" onclick="return customerSelectionAction()"/>--%>
						                           							<s:textfield name="borganization" id="borganization" cssClass="form-control" maxlength="70"/>
						                           						</div>
																	</div>
																</div>
																<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
																	<div class="text"><s:text name="broker.address1" /></div>
																	<div class="tbox">
																		<s:textfield name="address1" id="address1" cssClass="form-control" size="35" maxlength="50"/>
																	</div>
																</div>
																<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
																	<div class="text"><s:text name="broker.address2" /></div>
																	<div class="tbox">
																		<s:textfield name="address2" id="address2" cssClass="form-control" size="35" maxlength="50"/>
																	</div>
																</div>
																<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
																	<div class="text"><s:text name="broker.city" /> <font color="red">*</font></div>
																	<div class="tbox">
																		<s:select name="emirate" id="emirate" list="emiratesInfo" listKey="CITY_NAME" listValue="CITY_NAME" cssClass="form-control" headerKey="" headerValue="---Select---" onchange="fnOtherCity(this.value)"/> 
																	</div>
																</div>
																<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
																	<div class="text"><s:text name="broker.othercity" /></div>
																	<div class="tbox">
																		<s:textfield name="othercity" id="othercity" cssClass="form-control" size="35" maxlength="30"/>
																	</div>
																</div>
																<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
																	<div class="text"><s:text name="broker.country" /> <font color="red">*</font></div>
																	<div class="tbox">
																		<s:select name="country" id="country" list="countriesInfo" cssClass="form-control" listKey="COUNTRY_ID" listValue="COUNTRY_NAME"/>
																	</div>
																</div>
																<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
																	<div class="text"><s:text name="broker.pobox" /></div>
																	<div class="tbox">
																		<s:textfield name="pobox" id="pobox" cssClass="form-control" size="35" maxlength="6"/>
																	</div>
																</div>
																<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
																	<div class="text"><s:text name="broker.telephone" /><font color="red">*</font></div>
																	<div class="tbox">
																		<s:textfield name="telephone" id="telephone"  cssClass="form-control" size="35" maxLength="15"/>
																	</div>
																</div>
																<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
																	<div class="text"><s:text name="broker.fax" /></div>
																	<div class="tbox">
																		<s:textfield name="fax" id="fax" cssClass="form-control" size="35" maxLength="10"/>
																	</div>
																</div>
																<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
																	<div class="text"><s:text name="broker.customercode" /></div>
																	<div class="tbox">
																		<s:textfield name="nameAndCode"  id="nameAndCode" cssClass="form-control" size="30" />
					                        							<s:hidden name="CIMSNO"/>
					                        							<s:hidden name="ARACC"/>
					                        							<s:hidden name="customerName"/>
																	</div>
																</div>
																<s:if test='broImgName!=null'>
																	<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
																		<div class="text"> <s:text name="broker.images" /> </div>
																		<div class="tbox">
																			<img src='${pageContext.request.contextPath}<s:property value="broImgName"/>' border="0" width="150" height="60"  alt="" >
																			<a href="#" onclick="deleteLogo('','delete');"> <i class="fa fa-times"></i> </a>
		                         											<s:hidden name="broImgName"/>
																		</div>
																	</div>
																</s:if>
																<s:else>
																	<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
																		<div class="text"><s:text name="broker.images" /></div>
																		<div class="tbox">
																			<s:file name="upload" id="upload" cssClass="form-control"/>
																		</div>
																	</div>
																</s:else>
																<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
																	<div class="text"><s:text name="broker.attachedBranch"/> <font color="red">*</font></div>
																	<div class="tbox attachBranch">							
																		<s:select name="branchId" id="attachedBranch" list="branchList"  listKey="BRANCH_CODE" listValue="BRANCH_NAME" headerKey="" label="" multiple="true"  />
																	</div>
																</div>
																<%-- <div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
																	<div class="text"><s:text name="Sub Branch"/> <font color="red">*</font></div>
																	<div class="tbox">							
																		<button type="button" class="btn btn-sm btn-primary" onclick="return clickMenuPopup('${pageContext.request.contextPath}/subBranchBrokerMgm.action')">Choose Sub Branch</button>
																	</div>
																</div> --%>
																<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
																	<div class="text"><s:text name="Broker Branch"/> </div>
																	<div class="tbox">
																		<s:textarea name="subBranchId" id="subBranchId" cssClass="form-controlA" rows="2" cssStyle="width: 85%;"/>
																		<input class="btn btn-sm btn-primary" value="..." style="float:right;" type="button" name="menu" onclick="return clickMenuPopup('${pageContext.request.contextPath}/subBranchBrokerMgm.action')"/>							
																	</div>
																</div>
															</div>
														</div>
													</div><br>
													<div class="panel panel-primary">
														<div class="panel-heading"> <h3><s:text name="broker.contactpersonInfo" /></h3><hr> </div>
														<div class="panel-body">
															<div class="row">
																<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
																	<div class="text"><s:text name="broker.title" /></div>
																	<div class="tbox">
																		<s:select name="title" id="title" list="titlesInfo" cssClass="form-control" listKey="TITLE_ID" listValue="TITLE_NAME"  headerKey="" headerValue="---Select---"/>
																	</div>
																</div>
																<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
																	<div class="text"><s:text name="broker.firstname" /> <font color="red">*</font></div>
																	<div class="tbox">
																		<s:textfield name="firstname" cssClass="form-control" maxlength="70"/>
																	</div>
																</div>
																<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
																	<div class="text"> <s:text name="broker.lastname" /> </div>
																	<div class="tbox">
																		<s:textfield name="lastname" cssClass="form-control" size="35" maxlength="30"/>
																	</div>
																</div>
																<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
																	<div class="text"> <s:text name="broker.gender" /> </div>
																	<div class="tbox">
																		<s:radio name="gender" id="gender" list="#{'M':'Male','F':'Female'}" />
																	</div>
																</div>
																<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
																	<div class="text"> <s:text name="broker.dob" /> </div>
																	<div class="tbox">
																		<s:textfield id="dob" name="dob" cssClass="form-control datePicker" />
																	</div>
																</div>
																<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
																	<div class="text"> <s:text name="broker.nationality" /> <font color="red">*</font> </div>
																	<div class="tbox">
																		<s:select name="nationality" list="nationalitiesInfo" cssClass="form-control" listKey="COUNTRY_ID" listValue="NATIONALITY_NAME" headerKey="" headerValue="---Select---"/>
																	</div>
																</div>
																<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
																	<div class="text"><s:text name="broker.occupation" /></div>
																	<div class="tbox">
																		<s:textfield name="occupation"  cssClass="form-control" size="35" maxlength="30"/>
																	</div>
																</div>
																<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
																	<div class="text"><s:text name="broker.mobile" /><font color="red">*</font></div>
																	<div class="tbox">
																		<s:textfield name="mobile" cssClass="form-control" size="35" maxLength="10"/>
																	</div>
																</div>
																<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
																	<div class="text"><s:text name="broker.email" /> <font color="red">*</font></div>
																	<div class="tbox">
																		<s:textfield name="bemail" cssClass="form-control" size="35"/>
																	</div>
																</div>
																<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
																	<div class="text"><s:text name="broker.executive" /></div>
																	<div class="tbox">
																		<s:select name="executive" list="executivesInfo" cssClass="form-control" listKey="AC_EXECUTIVE_ID" listValue="AC_EXECUTIVE_NAME" headerKey="" headerValue="---Select---" />
																	</div>
																</div>
															</div>
														</div>
													</div>
													<s:if test='"new".equals(mode)'>
													<br>
														<div class="panel panel-primary">
															<div class="panel-heading"> <h3><s:text name="broker.login.creation" /></h3><hr> </div>
															<div class="panel-body">
																<div class="row">
																	<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
																		<div class="text"><s:text name="broker.new" /></div>
																		<div class="tbox">
																			<s:textfield name="loginid"  cssClass="form-control" size="35" maxlength="15"/>
																		</div>
																	</div>
																	<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
																		<div class="text"><s:text name="broker.password" /></div>
																		<div class="tbox">
																			<s:password name="password" cssClass="form-control" size="35" maxlength="20"/>
																		</div>
																	</div>
																	<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
																		<div class="text"><s:text name="broker.rpassword" /> <font color="red">*</font></div>
																		<div class="tbox">
																			<s:password name="repassword" cssClass="form-control" size="35" maxlength="20"/>
																		</div>
																	</div>
																</div>
															</div>
														</div>
													</s:if><br>
													<%-- <div class="panel panel-primary">
														<div class="panel-heading"> <h3><s:text name="tax.info" /></h3><hr> </div>
														<div class="panel-body">
															<s:set id="taxInfo" value="customerTaxInfo[0]" />
															<div class="row">
																<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
																	<table class="table table-bordered table-hover" cellspacing="0" width="100%" id="tadaTableNew">
																		<tbody>
																		<tr>
																			<td width="2%">1.</td>
																			<td width="35%"><s:text name="policy.fee" /></td>
																			<td width="63%"><s:radio id="policy_fee" name="policy_fee" list="#{'Y':'Yes','N':'No'}" onclick="policyStatusDisplay(this.value)" /></td>
																		</tr>
																		<tr id="policy_Status_Display" style="display: <s:if test='"Y".equals(policy_fee)'></s:if><s:else>none</s:else>">
																			<td></td>
																			<td><s:text name="policy.feein" /></td>
																			<td><s:textfield name="policFee" cssClass="form-control" maxlength="5" size="15"/></td>
																		</tr>
																		<tr>
																			<td>2.</td>
																			<td><s:text name="policy.app" /></td>
																			<td><s:radio name="app_for" list="#{'user':'Operation','broker':'Broker','both':'Both'}" /></td>
																		</tr>
																		<tr>
																			<td>3.</td>
																			<td>Commission for Issuer Quotes</td>
																			<td>
																				For One Off Policy&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<s:textfield name="oneOffCommission" cssClass="form-control" maxlength="15"  size="15"/>
																			</td>
																		</tr>
																		<tr>
																			<td></td>
																			<td></td>
																			<td>
																				For Open Cover Policy<s:textfield name="openCoverCommission" maxlength="15" cssClass="form-control" size="15"/>
																			</td>
																		</tr>
																		<tr>
																			<td></td>
																			<td><s:text name="policy.eff" /> : <font color="red">*</font></td>
																			<td> <s:textfield id="effecdate" name="effecdate" cssClass="form-control datepicker" /> </td>
																		</tr>																						
																		</tbody>
																	</table>
																</div>
															</div>
														</div>
													</div>  --%>
												</div>
											</div>
											<s:hidden name="agencyCode"/>
											<s:hidden name="login_Id"/>
											<s:hidden name="mode"/>		
											</s:form>
											<br class="clear" />
											<div class="row">
												<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" align="center">
													<button type="button" onclick="fnsubmit('back')" name="back" class="btn btn-sm btn-danger" value="Back" >Back</button>&nbsp;&nbsp;&nbsp;
													<button type="button" name="submit2" class="btn btn-sm btn-success" value="Submit" onclick="fnsubmit('info')">Submit</button>
												</div>
											</div>
										</div>
										<div id="deduc2" class="tab-pane fade">
											<div class="row">
												<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
													<s:if test='"newAjax".equals(mode1) || "editAjax".equals(mode1)'>
							     						<s:actionerror cssStyle="color:red;" /> <s:actionmessage cssStyle="color:green;" />
								       				</s:if>
												</div>
											</div>
											<div class="row">
												<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
													<div class="panel panel-primary">
														<div class="panel-heading"> 
															<div class="VehicleTableheading">
														       <div class="row">
														          <div class="col-lg-6 col-md-6">
														            <h3><s:text name="Product Selection" /></h3>
														          </div>
														          
														          <div class="col-lg-2 offset-md-3 offset-lg-4 col-md-3">
														          		<a class="btn btn-primary btn-block bordernone" onclick="forward(${agencyCode},'productselections', '','newAjax');">
														              		<i class="fa fa-user-plus"></i>
														            	</a>
														            <%-- <a href="#" onclick="forward(${agencyCode},'productselections', '','newAjax');" class="btn btn-sm btn-default" > <i class="fa fa-user-plus"></i> </a> --%>
														          </div>
														       </div>
														    </div><hr>
														</div>
														<div class="panel-body">
															<s:form id="info" name="info" method="post" action="" theme="simple">
															<div class="row">
																<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">																	 																	 
																		<div id="commisionDetailss">
																			<!-- <table class="footable commissionDetails" id="record" width="100%" cellspacing="0"> -->
																			<table class="table table-bordered table-hover" cellspacing="0" width="100%" id="tadaTableNew">
																				<thead class="bluecolortable" >
																				<tr>
																					<th><s:text name="Products" /></th>
																					<th><s:text name="Sum Insured" /></th>
																					<th><s:text name="Commission [%]" /></th>
																					<th><s:text name="Min Premium" /></th>
																					<%-- <th><s:text name="Loading % Min" /></th>
																					<th><s:text name="Loading % Max" /></th>
																					<th><s:text name="Discount % Min" /></th>
																					<th><s:text name="Discount % Max" /></th> --%>
																					<th><s:text name="Back Days Allowed" /></th>
																					<th><s:text name="Edit" /></th>
																				</tr>
																				</thead>
																				<tbody class="rowevencolor">
																				<s:iterator value="commisionDetails" var="cdet" id="cdet" status="status">
																				<tr>
																					<td> <s:property value="product_name" /> </td>
																					<td> <s:property value="INSURANCE_END_LIMIT" /> </td>
																					<td> <s:property value="COMMISSION" /> </td>
																					<td> <s:property value="MIN_PREMIUM_AMOUNT" /> </td>
																					<%-- <td> <s:property value="MIN_LOADING" /> </td>
																					<td> <s:property value="MAX_LOADING" /> </td>
																					<td> <s:property value="MIN_DISCOUNT" /> </td>
																					<td> <s:property value="MAX_DISCOUNT" /> </td> --%>
																					<td> <s:property value="BACK_DATE_ALLOWED" /> </td>
																					<td style="text-align: center;">																						 
																						<a type="button" href="#"  name="edit" class="btn btn-sm btn-warning" onclick="forward('${agencyCode}','productselections', '${PRODUCT_ID}', 'editAjax');">
																							<i class="fa fa-edit"></i>
																						</a>
																					</td>
																				</tr>
																				</s:iterator>
																				</tbody>
																			</table>
																		</div>
																</div>
															</div>
															<s:hidden name="mode1"/>
															<s:hidden name="agencyCode"/>
															<s:hidden name="borganization"/>
															<s:hidden name="bcode"/>
															<s:hidden name="firstname"/>
															<s:hidden name="login_Id"/>
															</s:form>																			
															<br class="clear" />
															<%-- <div class="row">
																<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
																	<s:if test='"newAjax".equals(mode1) || "editAjax".equals(mode1)'>
											     						<s:actionerror cssStyle="color:red;" /> <s:actionmessage cssStyle="color:green;" />
												       				</s:if>
																</div>
															</div> --%>
															<div class="row">
																<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" ">																
																	<div id="productselections"></div>
																</div>
															</div>
														</div>																		
													</div>
												</div>
											</div>															
										</div>
									</div>
								</div>
							</div>
						</div>
					<%-- </s:else> --%>
				</div>
			</div>
			<s:hidden name="agencyCode"/>
			<s:hidden name="login_Id"/>
			<s:hidden name="mode"/>		  
 		</div>
	</div>
	</div>
</body>

<script src="<%=request.getContextPath()%>/bootstrap/js/easy-responsive-tabs.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/bootstrap/js/bootstrap-multiselect.js" type="text/javascript"></script>
<script type="text/javascript">

$('#horizontalTab').easyResponsiveTabs({
	type: 'default', //Types: default, vertical, accordion           
	width: 'auto', //auto or any width like 600px
	fit: true,   // 100% fit in a container
	closed: 'accordion'
});
if('<s:property value="mode1"/>'=='newAjax' || '<s:property value="mode1"/>'=='editAjax'){
	postRequest('${pageContext.request.contextPath}/getBrokerAjaxBrokerMgm.action?reqFrom=productselections&agencyCode='+'<s:property value="agencyCode"/>'+'&productID='+'<s:property value="productID"/>'+'&mode1='+'<s:property value="mode1"/>'+'&mode=ajax'+'&productName='+'<s:property value="productName"/>'+'&commission='+'<s:property value="commission"/>'
				+'&insurance_End_Limit='+'<s:property value="insurance_End_Limit"/>'+'&min_Premium_Amount='+'<s:property value="min_Premium_Amount"/>'+'&loadingPremium='+'<s:property value="loadingPremium"/>'+'&discountPremium='+'<s:property value="discountPremium"/>'+'&back_Date_Allowed='+'<s:property value="back_Date_Allowed"/>'
				+'&user_Id_Creation='+'<s:property value="user_Id_Creation"/>'+'&payReceipt='+'<s:property value="payReceipt"/>'+'&freight='+'<s:property value="freight"/>'+'&provision='+'<s:property value="provision"/>'+'&effectiveDate='+'<s:property value="effectiveDate"/>'
				+'&endDate='+'<s:property value="endDate"/>'+'&sumInsStart='+'<s:property value="sumInsStart"/>'+'&sumInsEnd='+'<s:property value="sumInsEnd"/>'+'&policyFee='+'<s:property value="policyFee"/>'+'&otherFee='+'<s:property value="otherFee"/>'
				+'&loadingStart='+'<s:property value="loadingStart"/>'+'&loadingEnd='+'<s:property value="loadingEnd"/>'
				+'&discountStart='+'<s:property value="discountStart"/>'+'&discountEnd='+'<s:property value="discountEnd"/>'
				+'&volumeDiscount='+'<s:property value="volumeDiscount"/>'+'&corporateDiscount='+'<s:property value="corporateDiscount"/>'
				+'&specialDiscount='+'<s:property value="specialDiscount"/>'
				+'&index='+'<s:property value="index"/>', 'productselections');
	}
function getCoreCustomerInfo()
{
var URL='${pageContext.request.contextPath}/getccInfoBrokerMgm.action?mode=info';
var windowName = "BrokerInfo";
var width  = screen.availWidth;
var height = screen.availHeight;
var bwidth = window.innerWidth;
var bwidth1 = document.body.clientWidth;
if(bwidth <= 768) {
	var w = bwidth;
	var h = 400;
} else {
	var w = 700;
	var h = 400;
}
var features =
	'width='          + w +
	',height='		  + h +
	',left='		  + ((width - w - 0) * .4)  +
	',top='			  + ((height - h - 0) * .4) +
	',directories=no' +
	',location=no'	  +
	',menubar=no'	  +
	',scrollbars=yes' +
	',status=yes'	  +
	',toolbar=no'	  +
	',resizable=false';
var strOpen = window.open (URL, windowName, features);
strOpen.focus();
}
function policyStatusDisplay(val){
	if("Y"==val){
		document.getElementById("policy_Status_Display").style.display = "";	
	}else{
		document.getElementById("policy_Status_Display").style.display = "none";
	}
	return true;
}
function govStatusDisplay(val){
	if("Y"==val){
		document.getElementById("gov_Status_Display").style.display = "";	
	}else{
		document.getElementById("gov_Status_Display").style.display = "none";
	}
	return true;
}
function emerStatusDisplay(val){
	if("Y"==val){
		document.getElementById("emer_Status_Display").style.display = "";	
	}else{
		document.getElementById("emer_Status_Display").style.display = "none";
	}
	return true;
}
function provisionDisplay(val){
	if("Y"==val){
		document.getElementById("provision_Display").style.display = "";	
	}else{
		document.getElementById("provision_Display").style.display = "none";    			
	}
	return true;
}  
function productsel(val)
{	 
	 
	if(val==11){
		document.getElementById("sme").style.display = "none";
		document.getElementById("travel").style.display = "none";
		document.getElementById("motor").style.display = "none";
		document.getElementById("opencov").style.display = "";
		document.getElementById("oneoff").style.display = "none";
	}
	else if(val==3){
		document.getElementById("sme").style.display = "none";
		document.getElementById("travel").style.display = "none";
		document.getElementById("motor").style.display = "none";
		document.getElementById("oneoff").style.display = "";
		document.getElementById("opencov").style.display = "none";
	}
	else if(val==30){ 
		document.getElementById("sme").style.display = "block";
		document.getElementById("travel").style.display = "none";
		document.getElementById("motor").style.display = "none";
		document.getElementById("opencov").style.display = "none";
		document.getElementById("oneoff").style.display = "none";
	}
	else if(val==33){
		document.getElementById("sme").style.display = "none";
		document.getElementById("travel").style.display = "";
		document.getElementById("motor").style.display = "none";
		document.getElementById("opencov").style.display = "none";
		document.getElementById("oneoff").style.display = "none";
	}
	else if(val==65){
		document.getElementById("sme").style.display = "none";
		document.getElementById("travel").style.display = "none";
		document.getElementById("motor").style.display = "";
		document.getElementById("opencov").style.display = "none";
		document.getElementById("oneoff").style.display = "none";
	}
	else{
		document.getElementById("sme").style.display = "none";
		document.getElementById("travel").style.display = "none";
		document.getElementById("motor").style.display = "none";
		document.getElementById("opencov").style.display = "none";
		document.getElementById("oneoff").style.display = "none";
	}
	document.getElementById("ajxproductID").value=val;
	return true;
}  	

function forward(agcode, id, value, mode1){
	postRequest('${pageContext.request.contextPath}/getBrokerAjaxBrokerMgm.action?reqFrom='+id+'&agencyCode='+agcode+'&productID='+value+'&mode1='+mode1, id);
}
<s:if test='emirate!=null && !"".equals(emirate)'>
	fnOtherCity('<s:property value="emirate"/>');
</s:if>
function fnOtherCity(val){
	if(val=='VARIOUS'){
		document.getElementById('othercity').disabled=false;
	}else{
		document.getElementById('othercity').value='';
		document.getElementById('othercity').disabled=true;
	}
}
function deleteProduct(){
var conf=confirm("Do you want do delete this product?"); 
if(conf){
document.productSelect.action="deleteProductBrokerMgm.action";
document.productSelect.submit();
}
}
function deleteLogo(agencyCode,mode){
document.BrokerInfoEdit.action = "editBrokerMgm.action?mode1=delete";
document.BrokerInfoEdit.submit();
}
function customerSelectionAction() {
 var URL ="${pageContext.request.contextPath}/customerSelectionQuotation.action?searchType=BROKER";
 return callPopup(URL);
}
function fnCall(from){
	if(from=='edit')
		document.info.action = from+"BrokerMgm.action?mode=edit";
	else if(from=='userDetail')
		document.info.action = "getABListUserMgm.action?mode1=broker";
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
function fnCallNew(from){
	if(from=='edit')
		document.infoNew.action = from+"BrokerMgm.action?mode=edit";
	else if(from=='userDetail')
		document.infoNew.action = "getABListUserMgm.action?mode1=broker&borganization=<s:property value='borganization'/>";
	else if(from=='customerDetail')
		document.infoNew.action = "getABListCustomerMgm.action?mode1=broker";
	else if(from=='referal')
		document.infoNew.action = "getOCListReferal.action";
	else if(from=='openCover')
		document.infoNew.action = "opencoverOC.action";
	else
		document.infoNew.action = from+"BrokerMgm.action";
	document.infoNew.submit();
}
function fnsubmit(from){
	if(from=='info'){
		document.BrokerInfoEdit.action = "newBrokerBrokerMgm.action";
		document.BrokerInfoEdit.submit();
	}else if(from=='back'){
		document.BrokerInfoEdit.action = "getABListBrokerMgm.action";
		document.BrokerInfoEdit.submit();
	}
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
function addProduct(){
	document.productSelect.action='addProductBrokerMgm.action';
	document.productSelect.submit();
}

$(document).ready(function() {     
    $('#attachedBranch').multiselect({ 
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
enableTab('<s:property value="index"/>');
function enableTab(val){
	 if(val=='1'){
	 	$('[href="#deduc2"]').tab('show');
	 }
}
function subBranchAjax(branchId, id){
	alert(branchId);
	postRequest('${pageContext.request.contextPath}/subBranchAjaxBrokerMgm.action?reqFrom='+id+'&branchId='+branchId, id);
}

function clickMenuPopup(url){	 
	var checkedItems='';
	try
	{
		var elements=document.getElementById("attachedBranch");		 
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
</html>