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
	<s:if test='%{borganization!=null && !"".equals(borganization)}'>
		<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
			<div class="container vehDtl">
				<div class="Card_Parent card">
					<div class="panel-body">
						<input type="button" class="btn btn-sm btn-info" value="View" onclick="fnCall('view')"/><br/>
		                <input type="button" class="btn btn-sm btn-info" value="Edit" onclick="fnCall('edit')"/><br/>
		                <input type="button" class="btn btn-sm btn-info" value="Change Password" onclick="fnCall('changePwd')"/><br/>
		                <input type="button" class="btn btn-sm btn-info" value="Customer Details" onclick="fnCall('customerDetail')"/><br/>
		                <input type="button" class="btn btn-sm btn-info" value="OpenCover" onclick="fnCall('openCover')"/><br/>
		                <input type="button" class="btn btn-sm btn-info" value="Referral" onclick="fnCall('referal')"/><br/>
		                <input type="button" class="btn btn-sm btn-info" value="Statistics" onclick="fnCall('statistics')"/><br/>
					</div>
				</div>
			</div>
		</div>
		<div class="col-xs-12 col-sm-12 col-md-8 col-lg-8">
			<div class="container vehDtl">
				<div class="Card_Parent card">
				<%-- <div class="panel-heading">
					<s:property value="borganization"/>(<s:property value="agencyCode"/>)
					<div class="pullRight">
						<a class="btn btn-sm btn-default" style="text-decoration: none;" title="Customer Creation" onclick="more('getNewUnderwriterMgm.action','new','','')" href="#" ><i class="fa fa-user-plus"></i></a>
					</div>
					<br class="clear" />
				</div> --%>
				
				<div class="VehicleTableheading">
                  <div class="row">
                     <div class="col-lg-6 col-md-6">
                       <h3><s:property value="borganization"/>(<s:property value="agencyCode"/>)</h3>
                     </div>
                     <div class="col-lg-3 offset-md-3 offset-lg-3 col-md-3">
                         <div  class="pullRight">
                           <a class="btn btn-primary btn-block bordernone" style="text-decoration: none;" title="Customer Creation" onclick="more('getNewUnderwriterMgm.action','new','','')" href="#" ><i class="fa fa-user-plus"></i></a>
                       </div>
                     </div>
                  </div>
               </div><hr>
	</s:if>
	<s:else>
		<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
			<div class="container vehDtl">
				<div class="Card_Parent card">
				
				<%-- <div class="panel-heading">
					<s:text name="Underwriter Creation" />
					<div class="pullRight">
						<a class="btn btn-sm btn-default" style="text-decoration: none;" title="Customer Creation" onclick="more('getNewUnderwriterMgm.action','new','','')" href="#" ><i class="fa fa-user-plus"></i></a>
					</div>
					<br class="clear" />
				</div> --%>	
				
				<div class="VehicleTableheading">
                  <div class="row">
                     <div class="col-lg-6 col-md-6">
                       <h3><s:text name="Underwriter Creation" /></h3>
                     </div>
                     <div class="col-lg-3 offset-md-3 offset-lg-3 col-md-3">
                         <div  class="pullRight">
                           <a class="btn btn-primary btn-block bordernone" style="text-decoration: none;" title="Customer Creation" onclick="more('getNewUnderwriterMgm.action','new','','')" href="#" ><i class="fa fa-user-plus"></i></a>
                       </div>
                     </div>
                  </div>
               </div><hr>
	</s:else>
				<div class="panel-body">
					<s:form name="info" id="info" method="post" action="" theme="simple" validate="false">
						<table class="table table-bordered table-hover" cellspacing="0" width="100%" id="tadaTable">
                        	<thead class="bluecolortable">
							<tr>
								<th class="no-sort"></th>
								<th><s:text name="S.No." /></th>
								<th><s:text name="Issuer Name" /></th>
								<th><s:text name="Issuer Type" /></th>
								<th><s:text name="Login Id" /></th>
								<th><s:text name="Created Date" /></th>
								<th><s:text name="More" /></th>
								<th><s:text name="Status." /></th>
							</tr>
							</thead>
							<tbody class="rowevencolor">
							<s:iterator value="underwriterList" status="stat" var="record">
							<tr>
								<td></td>
								<td><s:property value="%{#stat.index+1}" /></td>
								<td><s:property value="COMPANY_NAME" /></td>
								<td><s:property value="USERTYPE" /></td>
								<td><s:property value="LOGIN_ID" /></td>
								<td><s:property value="CR_DATE" /></td>
								<td>
									<a class="btn btn-sm btn-warning" style="text-decoration: none;" onclick="more('viewUnderwriterMgm.action','edit','${record.COMPANY_NAME}','${record.LOGIN_ID}')" href="#"><i class="fa fa-ellipsis-h"></i></a>
								</td>
								<td><s:property value="STATUS" /></td>
							</tr>
							</s:iterator>
							</tbody>
						</table>
					</s:form>
					<s:form name="lform">
					  <s:hidden  name="mode"/>
					  <s:hidden  name="issurName"/>
					  <s:hidden  name="loginId"/>
					  <s:hidden name="optionMode" />
					</s:form>
				</div>
				</div>
			</div>
		</div>
</div>
</body>
<script type="text/javascript">
function getIssuer(val, id){
	if(val.length>=2){
		var searchBy=document.getElementById("searchBy").value;
		postRequest('${pageContext.request.contextPath}/getIssuerAjaxUnderwriterMgm.action?reqFrom='+id+'&searchBy='+searchBy+'&searchValue='+val, id);
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
function more(aaction,mode,name,loginId){
	document.lform.action=aaction;
	if('getNewUnderwriterMgm.action'!=aaction) {
		document.lform.mode.value=mode;
	}
	document.lform.name.value=name;
	document.lform.loginId.value=loginId;
	if('getNewUnderwriterMgm.action'==aaction){
		document.lform.optionMode.value=mode;
	} 
	document.lform.submit();
}	
</script>
</html>