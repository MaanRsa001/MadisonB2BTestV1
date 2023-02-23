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
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/dataTables/css/dataTables.responsive.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/dataTables/css/dataTables.jqueryui.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/dataTables/css/dataTables.tableTools.css">
	<script type="text/javascript" src="${pageContext.request.contextPath}/dataTables/js/dataTables.responsive.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/dataTables/js/dataTables.tableTools.js"></script>	
<%-- 		<script type="text/javascript" src="js/tcal.js"></script> --%>
<!-- 		<link href="css/styles.css" rel="stylesheet" type="text/css" /> -->
<%-- 		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/cssbootstrap/bootstrap.min.css" /> --%>
<%-- 		<script type="text/javascript" src="pages/admin/ratingEngine/menu.js"> </script> --%>
<%-- 		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/dataTables/css/dataTables.responsive.css"> --%>
<%-- 		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/dataTables/css/dataTables.jqueryui.css"> --%>
<%-- 		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/dataTables/css/dataTables.tableTools.css"> --%>
<%-- 		<link href="<%=request.getContextPath()%>/dataTables/css/jquery-start-ui.css" rel="stylesheet" type="text/css" /> --%>
<%-- 		<script type="text/javascript" src="<%=request.getContextPath()%>/dataTables/js/jquery-1.11.1.min.js"></script> --%>
<%-- 		<script type="text/javascript" src="<%=request.getContextPath()%>/dataTables/js/jquery.dataTables.min.js"></script> --%>
<%-- 		<script type="text/javascript" src="<%=request.getContextPath()%>/dataTables/js/dataTables.jqueryui.js"></script> --%>
<%-- 		<script type="text/javascript" src="<%=request.getContextPath()%>/dataTables/js/dataTables.responsive.js"></script> --%>
<%-- 		<script type="text/javascript" src="<%=request.getContextPath()%>/dataTables/js/dataTables.tableTools.js"></script> --%>
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
<div class="container wrapper vehDtl">
	<div class="Card_Parent offset-3" style="width: 620px;">
		<div class="card card-5">
			<div class="p-3">	
			<div class="container-fluid">
				<div class="row">
					<div class="col-md-12" >
					<font color="green"><s:actionmessage cssStyle="list-style: none;" /></font>
						<s:if test="!'insert'.equalsIgnoreCase(mode)">
							<div class="panel panel-primary" style="width: 502px;">
								<div >
									<h3><s:text name="label.claim.inti.screen" /></h3><hr>
								</div>
								<div class="panel-body">
									<div class="row">
										<div align="left">
											<s:if test="hasActionErrors()">
												<font color="red"><s:actionerror/></font>
											</s:if>
											<font color="green"><s:actionmessage cssStyle="list-style: none;" /></font>
										</div>
<!--										<s:if test="!'insert'.equalsIgnoreCase(mode)"></s:if>-->
										<div class="col-xs-12 col-sm-10 col-md-10 col-lg-12" align="left">
											<div class="form-group">
												<label>
													<s:text name="label.your.name" /><font color="red">*</font>
												</label>
												<s:textfield name="name" cssClass="form-control" data-content="Your Name" maxlength="150" readOnly="true"></s:textfield>
											</div>
										</div>
										<div class="col-xs-12 col-sm-10 col-md-10 col-lg-12" align="left">
											<div class="form-group">
												<label><s:text name="label.nrc.passport.num" /> <font color="red">*</font> </label>
												<s:textfield name="passport" cssClass="form-control" data-content="Your NRC/Passport Number" maxlength="10" readOnly="true"></s:textfield>
											</div>
										</div>
										<div class="col-xs-12 col-sm-10 col-md-10 col-lg-12" align="left">
											<div class="form-group">
												<label> <s:text name="label.claim.phone" /><font color="red">*</font> </label>
												<s:textfield name="phone" cssClass="form-control decimalOnly" data-content="Your Mobile Number without spaces eg. 0977777777" maxlength="10" readOnly="true"></s:textfield>
											</div>
										</div>
										<div class="col-xs-12 col-sm-10 col-md-10 col-lg-12" align="left">
											<div class="form-group">
												<label><s:text name="label.vehicle.reg.num" /><font color="red">*</font></label>
												<s:textfield name="vehicleRegNo" cssClass="form-control" data-content="Your vehicle Registration Number" id="vehicleRegNo" readOnly="true"></s:textfield>
											</div>
										</div>
										<div class="col-xs-12 col-sm-10 col-md-10 col-lg-12" align="left" style="width: 500px;height: 70px;">
											<label><s:text name="label.policynum" /><font color="red">*</font></label>
											<div class="row" align="center">
												&nbsp;&nbsp;&nbsp;<s:textfield name="policyNo1" id="policyNo1"  cssClass="form-control" readOnly="true" cssStyle="width:17%" maxLength="1" /> /
												<s:textfield name="policyNo2" id="policyNo2"  cssClass="form-control" readOnly="true" cssStyle="width:20%" maxLength="4" /> /
												<s:textfield name="policyNo3" id="policyNo3"  cssClass="form-control" readOnly="true" cssStyle="width:30%" maxLength="10" /> /
												<s:textfield name="policyNo4" id="policyNo4"  cssClass="form-control"  readOnly="true" cssStyle="width:20%" maxLength="4" />
											</div>
										</div>
										<div class="col-xs-12 col-sm-10 col-md-10 col-lg-12" align="left">
											<div class="form-group">
												<label><s:text name="label.date.accident" /><font color="red">*</font></label>
												<s:textfield name="dateOfAccident" cssClass="form-control" data-content="Date of Accident" id="dateOfAccident" readOnly="true"></s:textfield>
											</div>
										</div>
										<div class="col-xs-12 col-sm-10 col-md-10 col-lg-12" align="left" >
											<div class="form-group">
												<label><s:text name="label.claim.status" /><font color="red">*</font></label>
												<br />
												<s:select name="status" id="status" list="#{'A':'Accepted','R':'Rejected','P':'Pending'}" value='%{status==null?"P":status}' cssClass="form-control"/>
											</div>
										</div>
										<div class="col-xs-12 col-sm-10 col-md-10 col-lg-12" align="left" id="remarkfield" >
											<div class="form-group">
												<label><s:text name="claim.remarks" /><font color="red">*</font></label>
												<s:textarea name="remarks" cssClass="form-control" id="remarks"></s:textarea>
											</div>
										</div>
										<div class="col-xs-12 col-sm-10 col-md-10 col-lg-12" align="left" >
											<div class="form-group">
												<label><s:text name="View Attached Documents : " /></label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												<input type="button" class="btn btn-sm btn-primary" value="view Files" onclick="funDownload();"> 
											</div>
										</div>
										
									</div>
								</div>
							</div>
						</s:if>
							<br class="clear" />
							<div class="row">
								<div class="col-xs-12 col-sm-12 col-md-12" align="center">
<!--									<input type="button" class="btn btn-sm btn-danger" value="Back" onclick="funBack();" />-->
									<input type="button" class="btn btn-sm btn-success" value="Submit" onclick="funSubmit('update','');"> &nbsp;&nbsp;&nbsp;
								</div>
							</div>
							<s:hidden name="claimId" />
							<s:hidden name="claimRefNo" />
							<s:hidden name="productId" />
							<s:hidden name="cStatus" />
							<s:hidden name="policyNo" />
					</div>
				</div>
			</div>
			</div>
			</div>
			</div>
			</div>
		</s:form>
			
		<script type="text/javascript">
		document.getElementById
				function funSubmit(mode,id){
					var action="";
					//SerialQuarter=val
					if(mode=='update')
						{
						action='updateClaimAdminClm.action?mode='+mode;
						}
					else{
						alert("Action is empty");
						}
						document.claimIntimation.action=action;
						document.claimIntimation.submit();
					}
				function funBack(){
					document.claimIntimation.action='viewAdminClm.action';
					document.claimIntimation.submit();
				}
				
				function funDownload() {
					//var URL ='${pageContext.request.contextPath}/documentUploadDoUpload.action?applicationNo=<s:property value="applicationNo"/>&quoteNo=<s:property value="quoteNo"/>&deleteVehicleId='+vehicleId;
					var URL ='${pageContext.request.contextPath}/docDldAdminClm.action?claimRefNo=<s:property value="claimRefNo"/>';
					return popUp(URL,'700','500');
				}

		</script>
	</body>
</html>