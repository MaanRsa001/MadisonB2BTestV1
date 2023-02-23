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
					var data = $('#paymentDetailsList').dataTable( {
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
		<s:form id="quickRenewal" name="quickRenewal" method="post" action="" theme="simple">
	<div class="container vehDtl">
		<div class="Card_Parent ">
         <div class="card card-5">
		<div class="row">
			<div class="col-md-12">
				<div class="panel">
					<div class="panel-heading">
						<h3><s:text name="QUICK RENEWAL" /></h3><hr>
					</div>
					<div class="panel-body"  >
							<div class="container-fluid" >
							   <div class="row"> 
								   	<div class="col-md-6 offset-3">
								   	<h4><s:text name="Customer Details" /></h4>
								   	<hr>
								   		<div><label ><b><s:text name="Insured Name" /></b> : <s:property value="custInsuredName"/></label></div>
										<div><label ><b><s:text name="label.policynum" /></b> : <s:property value="policyNo"/></label></div>
										<div><label ><b><s:text name="Quote No" /></b> : <s:property value="renewRefNo"/></label></div>
										<br>
										<br>
										<div class="panel-body">
											<div class="row">
												<div class="col-xs-12">
													<div class="tbox"></div>
												</div>
												<div class="col-xs-12" style="color:red;font-size: 15px;" align="center">
													<b><label id="response">Your Renewal Policy is Pending for Approval !. </label></b>
								   				</div>
											</div>
										</div>
								   	</div>
								   	<br>
							   </div>
							</div>
							<div class="row">
								<div class="col-xs-12 col-sm-12 col-md-12" align="center">
									<input type="button" class="btn btn-sm btn-danger" readOnly="true" value="Back" onclick="funBack();" /> &nbsp;&nbsp;&nbsp;
								</div>
								<br/>
							</div>
						</div>
					</div>
				</div>
			</div>
			</div>
			</div>
			</div>
			<s:hidden name="policyNo"></s:hidden>
			<s:hidden name="mobileNo"></s:hidden>
			<s:hidden name="tempEmailId"/>
			<s:hidden name="tempPassportNo"/>
			<s:hidden name="tempMobileNo"></s:hidden>
		</s:form>
		<script type="text/javascript" src="<%=request.getContextPath()%>/bootstrap/js/jquery-1.9.1.min.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/bootstrap/js/bootstrap.min.js"></script>
		<script type="text/javascript">
				function funBack(){
					document.quickRenewal.action='vehDetailQuickRenewal.action?mode=back';
					document.quickRenewal.submit();
					}
		</script>
	</body>
</html>