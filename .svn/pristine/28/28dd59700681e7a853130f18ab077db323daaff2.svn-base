<%@ page isELIgnored="false" %>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/WEB-INF/displaytag.tld" prefix="display"%>
<!DOCTYPE HTML>
<html>
<head>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/dataTables/css/dataTables.responsive.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/dataTables/css/dataTables.jqueryui.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/dataTables/css/dataTables.tableTools.css">
	<script type="text/javascript" src="${pageContext.request.contextPath}/dataTables/js/dataTables.responsive.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/dataTables/js/dataTables.tableTools.js"></script>
	<script type="text/javascript">
	$(document).ready(function () {
		  $('#tadaTable').DataTable({
	    	  "responsive": true,
	        "columnDefs": {
	          "orderable": false
	        },
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
<div id="loading" style="width:100%">
   <img id="loading-image" src="${pageContext.request.contextPath}/images/madisonSymbolLogo.png"/>
</div>
<div class="row">
	<div class="col-xs-12 col-sm-12 col-md-3 col-lg-3">
		<div class="container vehDtl">
			<div class="Card_Parent card">
				<div class="row">
					<div class="col-xs-6 col-sm-6 col-md-12 col-lg-12">
						<input type="button" class="btn btn-sm btn-info adminMenuBtn" value="View" onclick="fnCall('view')"/>
					</div>
					<div class="col-xs-6 col-sm-6 col-md-12 col-lg-12">
						<input type="button" class="btn btn-sm btn-info adminMenuBtn" value="Edit" onclick="fnCall('edit')"/>
					</div>
				</div>
				<div class="row">
					<div class="col-xs-6 col-sm-6 col-md-12 col-lg-12">
						<input type="button" class="btn btn-sm btn-info adminMenuBtn" value="Change Password" onclick="fnCall('changePwd')"/>
					</div>
					<div class="col-xs-6 col-sm-6 col-md-12 col-lg-12">
						<input type="button" class="btn btn-sm btn-info adminMenuBtn" value="User Details" onclick="fnCall('userDetail')"/>
					</div>
				</div>
				<div class="row">
					<div class="col-xs-6 col-sm-6 col-md-12 col-lg-12">
						<input type="button" class="btn btn-sm btn-info adminMenuBtn" value="OpenCover" onclick="fnCall('customerDetail')"/>
					</div>
					<div class="col-xs-6 col-sm-6 col-md-12 col-lg-12">
						<input type="button" class="btn btn-sm btn-info adminMenuBtn" value="Referral" onclick="fnCall('referal')"/>
					</div>
				</div>
				<!-- <div class="row">
					<div class="col-xs-6 col-sm-6 col-md-12 col-lg-12">
						<input type="button" class="btn btn-sm btn-info adminMenuBtn" value="Statistics" onclick="fnCall('statistics')"/>
					</div>
				</div> -->
			</div>
		</div>
	</div>
	<div class="col-xs-12 col-sm-12 col-md-9 col-lg-9">
		<s:form id="info" name="info" theme="simple">
		<div class="container vehDtl">
			<div class="Card_Parent card">
			<div class="panel-heading">
				<h3><s:property value="borganization"/>(<s:property value="agencyCode"/>)</h3><hr>
			</div>
			<div class="panel-body">
				<div id="portfo">
					<table class="table table-bordered table-hover" cellspacing="0" width="100%" id="tadaTable">
						<thead class="bluecolortable" >
						<tr>
							<th><s:text name="Core Application Policy No" /></th>
							<th><s:text name="Proposal No" /></th>
							<th><s:text name="Customer Name" /></th>
							<th><s:text name="Policy Start Date" /></th>
							<th><s:text name="Policy End date" /></th>
							<th><s:text name="Schedule" /></th>
							<th><s:text name="LC Details" /></th>
						</tr>
						</thead>
						<tbody class="rowevencolor">
						<s:iterator value="portfolioList" status="stat" var="record">
						<tr>
							<td><s:property value="MISSIPPI_OPENCOVER_NO" /></td>
							<td><s:property value="PROPOSAL_NO" /></td>
							<td><s:property value="CUSTOMER_NAME" /></td>
							<td><s:property value="START_DATE" /></td>
							<td><s:property value="END_DATE" /></td>						
							<td align="center">
								<a href="#" type="button" class="btn btn-sm btn-info" onclick="fndoc('${record.MISSIPPI_OPENCOVER_NO}', '${session.user}', 'schedule', '${record.MISSIPPI_OPENCOVER_NO}', '', '${record.proposal_no}', 'false')"> <i class="fa fa-book"></i> </a>
							</td>
							<td align="center">
								<a href="#" type="button" class="btn btn-sm btn-default" onclick="fnlcdetail('${record.MISSIPPI_OPENCOVER_NO}', '${record.PROPOSAL_NO}', '${session.user}')"><i class="fa fa-eye"></i></a>
							</td>
						</tr>
						</s:iterator>
						</tbody>						
					</table>
				</div>
			</div>
		</div>
		</div>
		<s:hidden name="agencyCode"/>
		<s:hidden name="borganization"/>
		<s:hidden name="bcode"/>
		<s:hidden name="firstname"/>
		</s:form>
	</div>
</div>
<script type="text/javascript">
function fndoc(policynumber,loginId,docType,docNo,amendId,proposalNo,endtstatus){
 	var URL ="${pageContext.request.contextPath}/scheduleOC.action?docType="+docType+"&policynumber="+policynumber+"&loginId="+loginId+"&docNo="+docNo+"&amendId="+amendId+"&proposalNo="+proposalNo+"&endtstatus="+endtstatus;
 	viewPopUp(URL);
 }
function fnlcdetail(policynumber, proposalNo ,loginId){
 	var URL ="${pageContext.request.contextPath}/lcDetailOC.action?policynumber="+policynumber+"&loginId="+loginId+"&proposalNo="+proposalNo;
 	viewPopUp(URL);
 }
function fnTab(val, reqFrom, value, index){
	if(reqFrom=="portfo"){
		postRequest('${pageContext.request.contextPath}/getPortfolioOC.action?broker='+val+'&reqFrom='+reqFrom+'&index=2&from1=ajax', reqFrom);
		}
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
$(function(){
	var index = '<s:property value="index"/>';
	var t = $('#tabs');
	var tabs = t.tabs('tabs');
		t.tabs('select', tabs[index].panel('options').title);
});
function viewPopUp(URL) {
	var bwidth = window.innerWidth;
	var bwidth1 = document.body.clientWidth;
	if(bwidth <= 768) {
		return popUp(URL,bwidth1,'500');
	} else {
		return popUp(URL,'750','500');
	}
}
</script>
</body>
</html>