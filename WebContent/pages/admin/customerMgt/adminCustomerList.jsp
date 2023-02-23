<%@ page isELIgnored="false" %>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/WEB-INF/displaytag.tld" prefix="display"%>
<!DOCTYPE HTML>
<html>
<head>
	<title> ::: Madison General - Customer List ::: </title>
	<link rel="shortcut icon" href="<%=request.getContextPath()%>/images/favicon.ico" />
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
	<s:if test='%{borganization!=null && !"".equals(borganization)}'>
	<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
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
	<div class="col-xs-12 col-sm-12 col-md-8 col-lg-8">
		<div class="container vehDtl">
			<div class="Card_Parent card">
			<div class="panel-heading">
				<h3><s:property value="borganization"/>(<s:property value="agencyCode"/>)</h3><hr>
			</div>
	</s:if>
	<s:else>
	<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
		<div class="container vehDtl">
			<div class="Card_Parent card">
			<div class="panel-heading">
				<h3><s:text name="Customer Management" /></h3>
			</div>	
	</s:else>
			<div class="panel-body">
				<s:form name="info" id="info" method="post" action="" theme="simple" validate="false">
					<table class="table table-bordered table-hover" cellspacing="0" width="100%" id="tadaTable">
						<thead class="bluecolortable">
						<tr>
							<th><s:text name="S.No." /></th>
							<th><s:text name="Customer Name" /></th>
							<th><s:text name="Customer Code" /></th>
							<th><s:text name="Broker Name" /></th>
							<th><s:text name="Created Date" /></th>
							<th><s:text name="More" /></th>
						</tr>
						</thead>
						<tbody class="rowevencolor">
						<s:iterator value="customerList" status="stat" var="record">
						<tr>
							<td><s:property value="%{#stat.index+1}" /></td>
							<td><s:property value="COMPANY_NAME" /></td>
							<td><s:property value="customer_id" /></td>
							<td><s:property value="broker_name" /></td>
							<td><s:property value="ENTRY_DATE" /></td>
							<td>
								<a class="btn btn-sm btn-warning" style="text-decoration: none;" type="button" href="#" class="btn btn-sm btn-primary" onclick="forward('view','${record.customer_id}');" style="text-decoration: none;" ><i class="fa fa-ellipsis-h"></i></a>
							</td>
						</tr>
						</s:iterator>
						</tbody>						
					</table>
					<s:hidden name="borganization"/>
					<s:hidden name="agencyCode" id="agencyCode"/>
					<s:hidden name="customerId" id="customerId"/>
					<s:hidden name="login_Id" />
				</s:form>
			</div>
		</div>
	</div>
</div>
</div>
<script type="text/javascript">
function getCustomer(val, id){
	if(val.length>=2){
		var searchBy=document.getElementById("searchBy").value;
		var aCode=document.getElementById("agencyCode").value;
		postRequest('${pageContext.request.contextPath}/getCustomerAjaxCustomerMgm.action?agencyCode='+aCode+'&reqFrom='+id+'&searchBy='+searchBy+'&searchValue='+val, id);
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
function forward(from,val){
	document.getElementById("customerId").value=val;
	document.info.action ="viewCustomerMgm.action?mode=edit";
	document.info.submit();
}
</script>
</body>
</html>