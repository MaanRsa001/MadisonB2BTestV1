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
		<script type="text/javascript">
		
		$(document).ready(function () {
	 		  $('#tadaTable').DataTable({
		    	  "responsive": true,
		        "columnDefs": [
		          { "orderable": false, "targets": 6 },
		          { "responsivePriority": 1, targets: 0 },
		            { "responsivePriority": 10001, targets: 4 },
		            { "responsivePriority": 2, targets: -2 }
		        ],
		        language: {
		          //customize pagination prev and next buttons: use arrows instead of words
		          'paginate': {
		            'previous': '<span><i class="fas fa-chevron-circle-left"></i></span>',
		            'next': '<span><i class="fas fa-chevron-circle-right"></i></span>'
		          },
		          //customize number of elements to be displayed
		          "lengthMenu": '<select class="form-control input-sm">' +
		            '<option value="10">10</option>' +
		            '<option value="20">20</option>' +
		            '<option value="30">30</option>' +
		            '<option value="40">40</option>' +
		            '<option value="50">50</option>' +
		            '<option value="-1">All</option>' +
		            '</select>'
		        }
		      })
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
		</style>
	</head>
	<body>
		<s:form id="claimIntimation" name="claimIntimation" action="" theme="simple">
		<div id="loading" style="width:100%">
		   <img id="loading-image" src="${pageContext.request.contextPath}/images/madisonSymbolLogo.png"/>
		</div>
		<div class="container wrapper vehDtl">
			<div class="Card_Parent ">
				<div class="card card-5">
					<div class="p-3">
						<div class="row">
							<div class="col-md-12">
								<div class="panel panel-primary" style="width:100%">
									<div class="panel-heading">
									<h3>
										<s:if test='"A".equalsIgnoreCase(cStatus)'><s:text name="label.claim.accept" /></s:if>
										<s:if test='"R".equalsIgnoreCase(cStatus)'><s:text name="label.claim.rejected" /></s:if>
										<s:if test='"P".equalsIgnoreCase(cStatus)'><s:text name="label.claim.pending" /></s:if>
									</h3><hr>
									</div>
										<div class="panel-body" style="width:100%">
											<div class="row">
				            					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
												<font color="green"><s:actionmessage cssStyle="list-style: none;" /> </font>
												<table class="table table-bordered table-hover" cellspacing="0" width="100%" id="tadaTable">
													<thead class="bluecolortable">
														<tr>
															<th><s:text name="label.sno" /></th>
															<th><s:text name="label.claim.name" /></th>
															<th><s:text name="label.nrc.passport.num" /></th>
															<th><s:text name="label.claim.phone" /></th>
															<th><s:text name="label.policynum" /></th>
															<th><s:text name="label.vehicle.reg.num" /></th>
															<th><s:text name="label.date.accident" /></th>
															<th><s:text name="label.claim.status" /></th>
															<th><s:text name="label.claim.ref" /></th>
															<th><s:text name="claim.remarks" /></th>
															<th><s:text name="label.edit" /></th>
														</tr>
													</thead>
													<tbody class="rowevencolor">
														<s:iterator value="claimList" var="list" status="stat">
															<tr>
																<td><s:property value="#stat.count" /></td>
																<td><s:property value="#list.NAME" /></td>
																<td><s:property value="#list.NRC_PASSPORT_NO" /></td>
																<td><s:property value="#list.PHONE_NO" /></td>
																<td><s:property value="#list.POLICY_NUMBER" /></td>
																<td><s:property value="#list.VEHICLE_REG_NO" /></td>
																<td><s:property value="#list.DATE_OF_ACCIDENT" /></td>
																<td><s:property value="#list.STATUS" /></td>
																<td><s:property value="#list.CLAIM_REF" /></td>
																<td><s:property value="#list.REMARKS" /></td>
																<td style="text-align: center;">
																	<input type="button" class="btn btn-warning btn-sm" onclick="funSubmit('edit', '<s:property value="#list.CLAIM_REF" />','<s:property value="#list.POLICY_NUMBER" />');" value="View" />
																</td>
															</tr>
														</s:iterator>
													</tbody>
												</table>
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
			<s:hidden name="cStatus"></s:hidden>
		</s:form>
		<script type="text/javascript">
				function funSubmit(mode,claimref,policyno){
					action='editClaimAdminClm.action?claimRefNo='+claimref+'&policyNo='+policyno;
					document.claimIntimation.action=action;
					document.claimIntimation.submit();
					}
				//function funBack(){
			//		document.claimIntimation.action='listclaimClaimIntimation.action';
			//		document.claimIntimation.submit();
			//	}
		</script>
	</body>
</html>