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
		
	 </script>
	 <style type="text/css">
 		.list-group-item {
		    padding: 10px 1px;
		    border: 0px solid #ddd;
		}
		@media (min-width: 1200px){
			.container {
		    	width: 765px;
			}
		}
		
 	</style>
	</head>
	<body>
		<s:form id="quickRenewal" name="quickRenewal" action="" theme="simple">
			<div class="container-fluid">
				<div class="row">
					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
					<div class="container vehDtl">
				  		
<!-- 						<div class="panel panel-primary" > -->
						<div class="Card_Parent ">
				            <div class="card card-5">
							<div class="panel panel-heading">
								<h3><s:text name="QUICK RENEWAL - OTP  verification" /></h3>
							</div>
							<div class="panel-body">
								<div align="center">
									<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
										<span style="color: green;"><s:property value="otpMessage"/></span>
									</div>
									<div class="row">
										<s:include value="/pages/otpDetail.jsp" />
									</div>
									<div align="center">
										<div class="col-xs-12 col-sm-12 col-md-12" align="center">
											<input type="button" class="btn btn-sm btn-danger" value="Back" onclick="funBack();" /> &nbsp;&nbsp;&nbsp;
											<input type="button" class="btn btn-sm btn-success" value="Submit" onclick="funSubmit();"> &nbsp;&nbsp;&nbsp;
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
			<s:hidden name="policyNo"/>
		</s:form>
		<script type="text/javascript" src="<%=request.getContextPath()%>/bootstrap/js/jquery-1.9.1.min.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/bootstrap/js/bootstrap.min.js"></script>	
		<script type="text/javascript">
    				
				function funSubmit(){
					var action='verifyOtpQuickRenewal.action';
					document.quickRenewal.action=action;
					document.quickRenewal.submit();
					}
					
				function funBack(){
					action='pageQuickRenewal.action';
					document.quickRenewal.action=action;
					document.quickRenewal.submit();
				}
		</script>
	</body>
</html>