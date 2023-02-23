<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE HTML>
<html>
<head>
	
	<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/bootstrap/css/easy-responsive-tabs.css" />
	<script src="<%=request.getContextPath()%>/bootstrap/js/easy-responsive-tabs.js" type="text/javascript"></script>
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
	<s:if test='%{borganization!=null && borganization!=""}'>
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
			</div>
		</div>
	</div>
	<div class="col-xs-12 col-sm-12 col-md-9 col-lg-9">
		<div class="container vehDtl">
            <div class="Card_Parent card">
			<div class="panel-heading">
				<h3><s:property value="borganization"/>(<s:property value="agencyCode"/>)</h3><hr>
			</div>			
	</s:if>
	<s:else>
	<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
		<div class=" vehDtl">
            <div class="Card_Parent card">
			<div class="panel-heading">
				<h3><s:text name="Customer Management" /></h3><hr>
			</div>
	</s:else>	
			<div class="panel-body">
				<div id="horizontalTab">
					<ul class="resp-tabs-list">
						<li><s:text name="Customer Info" /></li>
						<%-- <li><s:text name="Open Cover List" /></li> --%>
					</ul>
					<div class="resp-tabs-container">					
						<div id="1">
							<s:form id="info" name="info" method="post" action="" theme="simple">
							<div class="row">
								<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">								
									<div class="row">
										<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">	
											<s:actionerror cssStyle="color: red;" />
											<s:actionmessage cssStyle="color: green;" />
										</div>
									</div>
									<div class="row">
										<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
											<div class="panel panel-primary">
												<div class="panel-heading"> <h3><s:text name="customer.contactinfo"/></h3><hr> </div>
												<div class="panel-body">
													<div class="row">
														<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
															<table width="100%">
																<tbody>
																<tr>
																	<td width="40%"><s:text name="customer.customerId"/></td>
																	<td width="60%" style="font-weight: bold;">&nbsp;:&nbsp;<s:property value="customerId"/></td>
																</tr>
																</tbody>
															</table>
														</div>
														<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
															<table width="100%">
																<tbody>
																<tr>
																	<td width="40%"><s:text name="customer.name"/></td>
																	<td width="60%" style="font-weight: bold;">&nbsp;:&nbsp;<s:property value="cfirstname"/></td>
																</tr>
																</tbody>
															</table>
														</div>
														<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
															<table width="100%">
																<tbody>
																<tr>
																	<td width="40%"><s:text name="customer.gender"/></td>
																	<td width="60%" style="font-weight: bold;">&nbsp;:&nbsp;<s:property value='%{cgender=="M"?"Male":"Female"}'/></td>
																</tr>
																</tbody>
															</table>
														</div>
														<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
															<table width="100%">
																<tbody>
																<tr>
																	<td width="40%"><s:text name="customer.dob"/></td>
																	<td width="60%" style="font-weight: bold;">&nbsp;:&nbsp;<s:property value="cdob"/></td>
																</tr>
																</tbody>
															</table>
														</div>
														<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
															<table width="100%">
																<tbody>
																<tr>
																	<td width="40%"><s:text name="customer.nationality"/></td>
																	<td width="60%" style="font-weight: bold;">&nbsp;:&nbsp;<s:property value="cnationalityNa"/></td>
																</tr>
																</tbody>
															</table>
														</div>
														<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
															<table width="100%">
																<tbody>
																<tr>
																	<td width="40%"><s:text name="customer.agencycode"/></td>
																	<td width="60%" style="font-weight: bold;">&nbsp;:&nbsp;<s:property value="cagencyCode"/></td>
																</tr>
																</tbody>
															</table>
														</div>
														<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
															<table width="100%">
																<tbody>
																<tr>
																	<td width="40%"><s:text name="customer.occupation"/></td>
																	<td width="60%" style="font-weight: bold;">&nbsp;:&nbsp;<s:property value="coccupation"/></td>
																</tr>
																</tbody>
															</table>
														</div>
														<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
															<table width="100%">
																<tbody>
																<tr>
																	<td width="40%"><s:text name="customer.address1"/></td>
																	<td width="60%" style="font-weight: bold;">&nbsp;:&nbsp;<s:property value="caddress1"/></td>
																</tr>
																</tbody>
															</table>
														</div>
														<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
															<table width="100%">
																<tbody>
																<tr>
																	<td width="40%"><s:text name="customer.address2"/></td>
																	<td width="60%" style="font-weight: bold;">&nbsp;:&nbsp;<s:property value="caddress2"/></td>
																</tr>
																</tbody>
															</table>
														</div>
														<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
															<table width="100%">
																<tbody>
																<tr>
																	<td width="40%"><s:text name="customer.city"/></td>
																	<td width="60%" style="font-weight: bold;">&nbsp;:&nbsp;<s:property value="ccity"/></td>
																</tr>
																</tbody>
															</table>
														</div>
														<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
															<table width="100%">
																<tbody>
																<tr>
																	<td width="40%"><s:text name="customer.country"/></td>
																	<td width="60%" style="font-weight: bold;">&nbsp;:&nbsp;<s:property value="ccountryNa"/></td>
																</tr>
																</tbody>
															</table>
														</div>
														<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
															<table width="100%">
																<tbody>
																<tr>
																	<td width="40%"><s:text name="customer.pobox"/></td>
																	<td width="60%" style="font-weight: bold;">&nbsp;:&nbsp;<s:property value="cpobox"/></td>
																</tr>
																</tbody>
															</table>
														</div>
														<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
															<table width="100%">
																<tbody>
																<tr>
																	<td width="40%"><s:text name="customer.mobile"/></td>
																	<td width="60%" style="font-weight: bold;">&nbsp;:&nbsp;<s:property value="cmobile"/></td>
																</tr>
																</tbody>
															</table>
														</div>
														<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
															<table width="100%">
																<tbody>
																<tr>
																	<td width="40%"><s:text name="customer.telephone"/></td>
																	<td width="60%" style="font-weight: bold;">&nbsp;:&nbsp;<s:property value="cphone"/></td>
																</tr>
																</tbody>
															</table>
														</div>													
														<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
															<table width="100%">
																<tbody>
																<tr>
																	<td width="40%"><s:text name="customer.email"/></td>
																	<td width="60%" style="font-weight: bold;">&nbsp;:&nbsp;<s:property value="cemail"/></td>
																</tr>
																</tbody>
															</table>
														</div>
														<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
															<table width="100%">
																<tbody>
																<tr>
																	<td width="40%"><s:text name="customer.fax"/></td>
																	<td width="60%" style="font-weight: bold;">&nbsp;:&nbsp;<s:property value="cfax"/></td>
																</tr>
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
							<s:hidden name="agencyCode"/>
							<s:hidden name="customerId"/>
							<s:hidden name="borganization"/>
							</s:form>
						</div>
						<div id="2">
							<s:form name="info1" id="info1" method="post" action="" theme="simple">
							<div class="row">
								<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
									<div class="panel panel-primary">
										<div class="panel-heading"> <h3><s:text name="customer.contactinfo"/></h3><hr> </div>
										<div class="panel-body">
											<div class="row">
												<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
													<table class="table table-bordered table-hover" cellspacing="0" width="100%" id="tadaTable">
                                                        <thead class="bluecolortable" >
														<tr>
															<th class="no-sort"></th>
															<th><s:text name="S.No." /></th>
															<th><s:text name="Core Application Policy No" /></th>
															<th><s:text name="Open Cover No" /></th>
															<th><s:text name="Start Date" /></th>
															<th><s:text name="Expiry Date" /></th>
															<th><s:text name="Status" /></th>
														</tr>
														</thead>
														<tbody class="rowevencolor">
														<s:iterator value="openCover" status="stat" var="record">
														<tr>
															<td></td>
															<td><s:property value="%{#stat.index+1}" /></td>
															<td> <s:property value="MISSIPPI_OPENCOVER_NO" /> </td>
															<td> <s:property value="open_cover_no" /> </td>
															<td> <s:property value="pol_start_date" /> </td>
															<td> <s:property value="pol_expire_date" /> </td>
															<td> <s:property value="STATUS" /> </td>																												
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
							<s:hidden name="agencyCode" id="agencyCode"/>
	 						<s:hidden name="customerId" id="customerId"/>
							<s:hidden name="borganization"/>
							</s:form>
						</div>
					</div>
				</div>
				<br class="clear" />
				<div align="center">
					<input type="button" class="btn btn-sm btn-danger" value="Back" onclick="fnCall('customerDetail')" >
				</div>				
			</div>
		</div>
	</div>
</div>
</div>
<script type="text/javascript">

$('#horizontalTab').easyResponsiveTabs({
	type: 'default', //Types: default, vertical, accordion           
	width: 'auto', //auto or any width like 600px
	fit: true,   // 100% fit in a container
	closed: 'accordion'
});

function getPolicy(val, id){
	if(val.length>=2){
		var searchBy=document.getElementById("searchBy").value;
		var cId=document.getElementById("customerId").value;
		var acode=document.getElementById("agencyCode").value;
		postRequest('${pageContext.request.contextPath}/getCustomerAjaxCustomerMgm.action?agencyCode='+acode+'&customerId='+cId+'&reqFrom='+id+'&searchBy='+searchBy+'&searchValue='+val, id);
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
</script>
</body>
</html>