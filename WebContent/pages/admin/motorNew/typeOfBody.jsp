<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/WEB-INF/displaytag.tld" prefix="display"%>
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
		var data = $('#gridTable').dataTable( {
			"lengthMenu": [[10, 25, 50, -1], [10, 25, 50, "All"]],
			"order": [[ 1, "asc" ]],
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
<div class="container-fluid">
	<div class="row">
		<div class="col-xs-2">
			<%@ include file="/pages/admin/motor/motormenu.jsp" %>
		</div>
		<div class="col-xs-10">
			<div class="panel panel-primary">
				<div class="panel-heading">
					<s:text name="adminMaster.comprehensiveRateConfig"/>
				</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-xs-4">
							<s:text name="adminMaster.policyType" /> <br/>
							<s:select list="#{}" cssClass="inputBoxS" headerKey="" headerValue="---Select---" />
						</div>
						<div class="col-xs-4">
							<s:text name="adminMaster.bodyType" /> <br/>
							<s:select list="#{}" cssClass="inputBoxS" headerKey="" headerValue="---Select---" />
						</div>
						<div class="col-xs-4" align="center">
							<input type="button" class="btn btn-sm btn-success" value="Submit" onclick="displayGridTable();">
						</div>
					</div> <br/>
					<div class="row" id="gridTableRow" style="display: none;">
						<table class="display responsive no-wrap" id="gridTable" width="100%" cellspacing="0">
							<thead>
							<tr>
								<th class="no-sort"></th>
								<th>Product</th>
								<th>Medical Description</th>
								<th>Effective Date</th>
								<th>Status</th>
								<th>View</th>
								<th>Third Party Rate</th>
							</tr>
							</thead>
							<tbody>
							<tr>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td style="text-align: center;">
									<button type="button" class="btn btn-info btn-sm" onclick="openModal();">View</button> 
									<button type="button" class="btn btn-warning btn-sm" >Modify</button>
								</td>
								<td style="text-align: center;"><button type="button" class="btn btn-primary btn-sm" onclick="displayRateTable();" >Rate</button></td>
							</tr>
							</tbody>
						</table>
					</div>					
				</div>
			</div>
			<div class="panel panel-primary" id="gridTable2" style="display: none;">
				<div class="panel-heading">
					<s:text name="adminMaster.thirdPartyRate" />
				</div>
				<div class="panel-body">
					<table class="footable" width="100%" >
						<thead>
						<tr>
							<th>S.No.</th>
							<th>Product</th>
							<th>Third Party</th>
							<th>Type of Body</th>
							<th>Third Party Rate</th>
							<th>Status</th>
							<th>View</th>
						</tr>
						</thead>
						<tbody>
						<tr>
							<td>1</td>
							<td>65</td>
							<td>1</td>
							<td>1</td>
							<td  style="text-align: right;">1600</td>
							<td style="text-align: center;">
								Y
							</td>
							<td style="text-align: center;"><button type="button" class="btn btn-info btn-sm" onclick="openModal1();" >View</button></td>
						</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div> <br/>
	<div class="row">
		<div class="col-xs-12" align="center">
			<input type="button" class="btn btn-sm btn-danger" value="Back">
		</div>
	</div>			
</div>
<div id="viewRateModal" class="modal fade" role="dialog">
	<div class="modal-dialog">
	<!-- Modal content-->
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4 class="modal-title"> <s:text name="adminMaster.rateDetails" /> </h4>
			</div>
			<div class="modal-body">
				<table width="100%" class="footable">
					<tbody>
					<tr>
						<td width="33%"> <s:text name="adminMaster.policyType" /> &nbsp;:&nbsp; 1 </td>
						<td width="33%"> <s:text name="adminMaster.bodyType" /> &nbsp;:&nbsp; 1 </td>
						<td width="33%"> <s:text name="adminMaster.subRate" /> &nbsp;:&nbsp; 1 </td>
					</tr>
					<tr>
						<td> <s:text name="adminMaster.ageOfVehicleStart" /> &nbsp;:&nbsp; 0 </td>
						<td> <s:text name="adminMaster.ageOfVehicleEnd" /> &nbsp;:&nbsp; 3 </td>
						<td> <s:text name="adminMaster.country" /> &nbsp;:&nbsp; 1 </td>
					</tr>
					<tr>
						<td> <s:text name="adminMaster.city" /> &nbsp;:&nbsp; 1 </td>
						<td> <s:text name="adminMaster.effectiveDate" /> &nbsp;:&nbsp; 28-01-2016 </td>
						<td> <s:text name="adminMaster.status" /> &nbsp;:&nbsp; Y </td>
					</tr>
					<tr>
						<td colspan="3">
							<s:text name="adminMaster.description" /> &nbsp;:&nbsp; Loss of or damage to vehicle covered~Third Party damage up to ZMW 250,000
						</td>
					</tr>
					</tbody>
				</table>
			</div>				      
		</div>
	</div>
</div>
<div id="viewRateModal1" class="modal fade" role="dialog">
	<div class="modal-dialog">
	<!-- Modal content-->
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4 class="modal-title"> <s:text name="adminMaster.thirdPartyRateDetails" /> </h4>
			</div>
			<div class="modal-body">
				<table width="100%" class="footable">
					<tbody>
					<tr>
						<td width="33%"> <s:text name="adminMaster.product" /> &nbsp;:&nbsp; 1 </td>
						<td width="33%"> <s:text name="adminMaster.thirdParty" /> &nbsp;:&nbsp;  </td>
						<td width="33%"> <s:text name="adminMaster.typeOfBody" /> &nbsp;:&nbsp; 1 </td>
					</tr>
					<tr>
						<td> <s:text name="adminMaster.location" /> &nbsp;:&nbsp; 28-01-2016  </td>
						<td> <s:text name="adminMaster.seatingCylinder" /> &nbsp;:&nbsp; Y </td>
						<td> <s:text name="adminMaster.thirdPartyRate" /> &nbsp;:&nbsp; 1500 </td>
					</tr>
					<tr>
						<td> <s:text name="adminMaster.effectiveDate" /> &nbsp;:&nbsp; 23-01-2016  </td>
						<td> <s:text name="adminMaster.status" /> &nbsp;:&nbsp; Y </td>
						<td> &nbsp; </td>
					</tr>					
					</tbody>
				</table>
			</div>				      
		</div>
	</div>
</div>
<script type="text/javascript" src="<%=request.getContextPath()%>/bootstrap/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript">
function openModal() {
	$('#viewRateModal').modal('show');
}
function openModal1() {
	$('#viewRateModal1').modal('show');
}
function displayRateTable() {
	document.getElementById('gridTable2').style.display = 'block';
}
function displayGridTable() {
	document.getElementById('gridTableRow').style.display = 'block';
}
</script>
</body>
</html>   
