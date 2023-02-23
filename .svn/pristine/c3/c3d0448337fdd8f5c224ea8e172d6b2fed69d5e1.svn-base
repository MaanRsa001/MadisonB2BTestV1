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
<s:form name="form1" method="post" action="" theme="simple" validate="false">
<div id="loading" style="width:100%">
   <img id="loading-image" src="${pageContext.request.contextPath}/images/madisonSymbolLogo.png"/>
</div>
<div class="row">
	<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
		<div class="container vehDtl">
			<div class="Card_Parent ">
				<div class="card">
					<div class="VehicleTableheading">
				       <div class="row">
				          <div class="col-lg-6 col-md-6">
				            <h3><s:text name="broker.brokermanagement" /></h3>
				          </div>
				          <div class="col-lg-2 offset-md-3 offset-lg-4 col-md-3">
				            <a class="btn btn-primary btn-block bordernone" style="text-decoration: none;" title="Broker Creation" href="editBrokerMgm.action?mode=new">
				              <i class="fa fa-user-plus"></i>
				            </a>
				          </div>
				       </div>
				    </div><hr>
					
					<br class="clear" />
					<div class="panel-body">
						<table class="table table-bordered table-hover" cellspacing="0" width="100%" id="tadaTable">
							<thead class="bluecolortable">
							<tr>
								<th><s:text name="S.No." /></th>
								<th><s:text name="Broker Name" /></th>
								<th><s:text name="Broker Code" /></th>
								<th><s:text name="Login Id" /></th>
								<th><s:text name="Created Date" /></th>
								<th><s:text name="More" /></th>
								<th><s:text name="Status" /></th>
							</tr>
							</thead>
							<tbody class="rowevencolor">
							<s:iterator value="brokerList" status="stat" var="record">
							<tr>
								<td><s:property value="%{#stat.index+1}" /></td>
								<td>${record.COMPANY_NAME}&nbsp;(${record.AGENCY_CODE})</td>
								<td><s:property value="RSA_BROKER_CODE" /></td>
								<td><s:property value="LOGIN_ID" /></td>
								<td><s:property value="cr_date" /></td>						
								<td>
									<a class="btn btn-sm btn-warning" style="text-decoration: none;" type="button" href="viewBrokerMgm.action?mode=edit&agencyCode=${record.AGENCY_CODE}" > <i class="fa fa-ellipsis-h"></i> </a>
								</td>
								<td> <span class="label label-success"> <s:property value="STATUS" /> </span> </td>
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
</s:form>
<script type="text/javascript">
function getBroker(val, id){
	if(val.length>=2){
		var searchBy=document.getElementById("searchBy").value;
		var bCode=document.getElementById("branchCode").value;
		postRequest('${pageContext.request.contextPath}/getBrokerAjaxBrokerMgm.action?reqFrom='+id+'&branchCode='+bCode+'&searchBy='+searchBy+'&searchValue='+val, id);
	}
}
</script>
</body>
</html>