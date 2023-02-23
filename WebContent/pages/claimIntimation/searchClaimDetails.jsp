<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ page import="nl.captcha.Captcha"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
	<head>
	<SCRIPT language=Javascript>
		function isNumberKey(evt) {
			var charCode = (evt.which) ? evt.which : event.keyCode;
			if (charCode != 46 && charCode > 31 
			&& (charCode < 48 || charCode > 57))
			return false;
			return true;
		}
	</SCRIPT>
		<script type="text/javascript" src="js/common.js"></script>
		<script type="text/javascript" src="js/tcal.js"></script>
		<link href="css/styles.css" rel="stylesheet" type="text/css" />
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/cssbootstrap/bootstrap.min.css" />
		<script type="text/javascript" src="pages/admin/ratingEngine/menu.js"> </script>
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/dataTables/css/dataTables.responsive.css">
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/dataTables/css/dataTables.jqueryui.css">
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/dataTables/css/dataTables.tableTools.css">
		<link href="<%=request.getContextPath()%>/dataTables/css/jquery-start-ui.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="<%=request.getContextPath()%>/dataTables/js/jquery-1.11.1.min.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/dataTables/js/jquery.dataTables.min.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/dataTables/js/dataTables.jqueryui.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/dataTables/js/dataTables.responsive.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/dataTables/js/dataTables.tableTools.js"></script>
		<script type="text/javascript">
		jQuery(function ($) {
			try {
				var data = $('#gridTableMake').dataTable( {
					"lengthMenu": [[10, 25, 50, -1], [10, 25, 50, "All"]],
					"order": [[ 0, "asc" ]],
					"columnDefs": [ {
			          "targets": 'no-sort',
			          "orderable": false
				    } ],
					responsive: true
				});
			} catch(err){}
		} );
	 </script>
	</head>
	<body>
		<s:form id="claimIntimation" name="claimIntimation" action="" theme="simple">
		<div class="row">
			<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
				<div class="panel panel-primary">			
					<div class="panel-body" align="center">
						<div class="container-fluid">
							<div class="row">
								<div class="col-xs-12" align="center">
									<s:if test="!'available'.equalsIgnoreCase(sStatus)">
										<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
											<div class="panel panel-primary" style="width: 605px;">
												<div class="panel-heading">
													<s:text name="Claim Search" />
												</div>
												<div class="form-group" >
													<div align="left" style="color: red;"> <s:actionerror/></div>
													<div class="" style="padding: 0 15px 0 15px;" id="tone">
														<div class="row">
															<br/>
															<div class="col-xs-6">
																<div class="text"><s:text name="label.claim.phone" /></div>
																<div class="tbox">
																	<s:textfield name="phone" id="phone" cssClass="form-control decimalOnly" cssStyle="border color:black: 1px;" placeholder="Mobile Number" maxlength="10" />
																</div>
															</div>
															<div class="col-xs-6">
																<div class="text"><s:text name="label.claim.ref" /></div>
																	<div class="tbox">
																		<s:textfield name="claimRefNo" id="claimRefNos" cssClass="form-control decimalOnly" cssStyle="border color:black: 1px;" placeholder="Claim Reference Number" maxlength="10" />
																	</div>
															</div>
														</div>
														<br/>
														<div class="row">
															<br/>
															<br/>
															
															<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" >
														<div class="panel panel-primary">
															<div class="panel-body">
																<div class="row">
																	<div class="col-xs-12 col-sm-12 col-md-4 col-lg-7" align="left">
																		<div class="text"><s:text name="motor.captcha.note" /></div>
																		<div class="tbox">
																			<s:textfield name="captchavalue" id="captchavalue" value="" cssClass="form-control" />
																		</div>
																	</div>
																	<div>
																		<br/>
																		<div class="col-xs-12 col-sm-12 col-md-4 col-lg-5" align="right">
																			<%	Captcha captcha = new Captcha.Builder(200, 50).addText().addBackground().addNoise().gimp().addBorder().build(); %>
																            <div class="captchaBg" style="width: 100px; float: left;">
																            	<img id="captcha" src="<c:url value="simpleCaptcha.jpg"  />" width="100">
																            </div>
																            <a href="#" onclick="reloadCaptcha();" style="float: left;"><img src="<%=request.getContextPath()%>/images/reload.png" alt="reload" width="40" height="40"/> </a>
																		</div>
																	</div>
																</div>
															</div>
														</div>
													</div>
														</div>
														<br/>
														<br class="clear" />
														<div class="row">
															<div class="col-xs-12" align="center">
																<input type="button" class="btn btn-sm btn-success" value="Search" onclick="funSubmit();"> &nbsp;&nbsp;&nbsp;
															</div>
														</div>
													</div>
												</div>
											</div>
										</div>
										<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
											<div class="panel panel-primary" style="width: 605px;">
												<div class="panel-heading">
													<s:text name="Intimate New Claim" />
												</div>
												<div class="form-group" >
												<br/>
														<div class="row">
															<div class="col-xs-12" align="center">
																<input type="button" class="btn btn-lg btn-warning" value="Intimate New Claim" onclick="SubmitNew();"> &nbsp;&nbsp;&nbsp;
															</div>
														</div>
												</div>
											</div>
										</div>
									</s:if>
									<s:else>
										<div class="panel panel-primary" style="width: 502px;">
											<div class="panel-heading">
												<s:text name="label.claim.inti.screen" />
											</div>
											<div class="panel-body">
												<font color="green"><s:actionmessage cssStyle="list-style: none;" /></font>
											</div>
											<div class="body">
										    	<div class="row">
										    	 	<div class="boxContent" >
														<div class="col-xs-12 col-md-12">
															<table class="table"  style="margin-bottom: 0px;" align="center">
																<tbody>
																	<tr>
																		<th class="bg-orange" align="center"><s:text name="label.your.name" /></th>
																		<td><s:property value="name"/></td>
																	</tr>
																	<tr>
																		<th class="bg-orange" align="center"><s:text name="label.claim.phone" /></th>
																		<td><s:property value="phone"/></td>
																	</tr>
																	<tr>
																		<th class="bg-orange" align="center"><s:text name="label.vehicle.reg.num" /></th>
																		<td><s:property value="vehicleRegNo"/></td>
																	</tr>
																	<tr>
																		<th class="bg-orange" align="center"><s:text name="label.policynum" /></th>
																		<td><s:property value="policyNo"/></td>
																	</tr>
																	<tr>
																		<th class="bg-orange" align="center"><s:text name="label.nrc.passport.num" /></th>
																		<td><s:property value="passport"/></td>
																	</tr>
																	<tr>
																		<th class="bg-orange" align="center"><s:text name="label.date.accident" /></th>
																		<td><s:property value="dateOfAccident"/></td>
																	</tr>
																	<tr>
																		<th class="bg-orange" align="center"><s:text name="label.claim.ref" /></th>
																		<td><s:property value="claimRefNo"/></td>
																	</tr>
																	<s:if test='!"Approved".equalsIgnoreCase(status)'>
																		<tr>
																			<th class="bg-orange" align="center"><s:text name="label.claim.status" /></th>
																			<td><b style="color:red"><s:property value="status"/></b></td>
																		</tr>
																	</s:if>
																	<s:else>
																		<tr>
																			<th class="bg-orange" align="center"><s:text name="label.claim.status" /></th>
																			<td><b style="color:green"><s:property value="status"/></b></td>
																		</tr>
																	</s:else>
																	<tr>
																		<th class="bg-orange" align="center"><s:text name="label.claim.status.date" /></th>
																		<td><s:property value="updateDate"/></td>
																	</tr>
																	<tr>
																		<th align="center"><s:text name="View Documents" /></th>
																		<td><input type="button" class="btn btn-sm btn-primary" value="view Files" onclick="funDownload();"></td>
																	</tr>
																</tbody>
															</table>
														</div>
													</div>
												</div>
											</div>
										</div>
									</s:else>
								</div>
							</div>
						</div>
						</div>
					</div>
				</div>
			</div>
		</s:form>
		<script type="text/javascript" src="<%=request.getContextPath()%>/bootstrap/js/jquery-1.9.1.min.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/bootstrap/js/bootstrap.min.js"></script>	
		<script type="text/javascript">
    				
				function funSubmit(){
					var action='searchClaimIntimation.action';
					document.claimIntimation.action=action;
					document.claimIntimation.submit();
				}
				function SubmitNew(){
					var action='insertNewClaimIntimation.action';
					document.claimIntimation.action=action;
					document.claimIntimation.submit();
				}	
					
				function funBack(){
					document.claimIntimation.action='HomeUser.do';
					document.claimIntimation.submit();
				}
				function reloadCaptcha(){
	    			$("#captcha").attr("src", "simpleCaptcha.jpg#" +new Date().getTime());
				}
				
				function funDownload() {
					var URL ='${pageContext.request.contextPath}/docDldAdminClm.action?claimRefNo=<s:property value="claimRefNo"/>';
					return popUp(URL,'700','500');
				}
		</script>
	</body>
</html>