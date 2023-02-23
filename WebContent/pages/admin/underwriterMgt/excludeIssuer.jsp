<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ page language="java" import="com.maan.report.service.ReportService"%>
<!DOCTYPE HTML>
<html>
<head>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/dataTables/css/dataTables.responsive.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/dataTables/css/dataTables.jqueryui.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/dataTables/css/dataTables.tableTools.css">
    <link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/bootstrap/css/bootstrap-multiselect.css" />
    <script type="text/javascript" src="${pageContext.request.contextPath}/dataTables/js/dataTables.responsive.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/dataTables/js/dataTables.tableTools.js"></script>
	<script type="text/javascript">
	$(document).ready(function () {
        $('#tadaTable').DataTable({
            "responsive": true,
            //"scrollY":        "500px",
            //"scrollCollapse": true,
            "paging":         false,
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
        .btn-group, .btn-group-vertical {
		    display: block; 
		}
		.btn-group, .multiselect {
		    width: 100%;
		}
		.btn-default {
		    color: #333;
		    background-color: #fff;
		    border-color: #ccc;
		}
		.text{
			padding-top:10px;
			padding-bottom:3px;
			font-weight:bold;
		}
		.Content
		{
		  height:100%;
		  min-height:350px;
		  max-height:600px;
	   	  overflow:auto;
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
				<div class="panel-body">
					<div class="row">
						<div class="col-xs-6 col-sm-6 col-md-12 col-lg-12">
							<input type="button" class="btn btn-sm btn-info adminMenuBtn" value="Underwriter Details" onclick="fnCall('list')"/> 
						</div>
						<div class="col-xs-6 col-sm-6 col-md-12 col-lg-12">
							<input type="button" class="btn btn-sm btn-info adminMenuBtn" value="Change Password" onclick="fnCall('changePwd')"/>
						</div>
					</div>
					<div class="row">
						<div class="col-xs-6 col-sm-6 col-md-12 col-lg-12">
							<input type="button" class="btn btn-sm btn-info adminMenuBtn" value="Included Brokers" onclick="fnCall('include')"/> 
						</div>
						<div class="col-xs-6 col-sm-6 col-md-12 col-lg-12">
							<input type="button" class="btn btn-sm btn-info adminMenuBtn" value="Excluded Brokers" onclick="fnCall('exclude')"/>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="col-xs-12 col-sm-12 col-md-9 col-lg-9">
		<div class="container vehDtl">
			<div class="Card_Parent card">
			<%-- <div class="panel-heading">
				<s:text name="EXCLUDED BROKER" />
				<s:if test='%{borganization!=null && borganization!=""}'>
					<s:property value="borganization"/>(<s:property value="agencyCode"/>)
				</s:if>
				<s:else>
					<div class="pullRight">
						<s:property value="loginId"/>
					</div>
					<br class="clear" />
				</s:else>
			</div> --%>
			<div class="VehicleTableheading">
               <div class="row">
	               <s:if test='%{borganization!=null && borganization!=""}'>
	                  <div class="col-lg-6 col-md-6">
	                    <h3><s:text name="EXCLUDED BROKER" />
						<s:property value="borganization"/>(<s:property value="agencyCode"/>)</h3>
	                  </div>
                  </s:if>
                  <s:else>
                  	<div class="col-lg-6 col-md-6">
	                    	<h3><s:text name="EXCLUDED BROKER" /></h3>
	                  </div>
	                  <div class="col-lg-3 offset-md-3 offset-lg-3 col-md-3">
	                      <div  class="pullRight label label-warning">
	                          <b><s:property value="loginId"/></b>
	                      </div>
	                  </div>
                  </s:else>
                  
               </div>
            </div><hr>
			<div class="panel-body">
				<s:form id="info" name="underwriter" method="post" action="updateIncludeUnderwriterMgm" theme="simple">
				<div class="row">
					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">	
						<s:actionerror cssStyle="color: red;" />
						<s:actionmessage cssStyle="color: green;" />
					</div>
				</div>
				<div class="row">
					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 Content">	
						<div id="underwriterInfo">
							<input name="type[0]" type="hidden" value="hi"/>
							<table class="table table-bordered table-hover" cellspacing="0" width="100%" id="tadaTable">
                        		<thead class="bluecolortable">
								<tr>
									<th class="no-sort">S.No</th>
									<th><s:text name="Select" /></th>
									<th><s:text name="Broker Code" /></th>
									<th><s:text name="Broker Name" /></th>
									<th><s:text name="Branch." /></th>
									<th><s:text name="Products." /></th>
								</tr>
								</thead>
								<tbody class="rowevencolor">
								<s:iterator value="excludeIssuer" status="stat" var="record">
								<tr>
									<td><s:property value="%{#stat.index+1}" /></td>
									<td> 
										<s:checkbox name="statusChk[%{#stat.index}]" id="statusChk_%{#stat.index}"/> 
									</td>
									<td> <s:property value="AGENCY_CODE" />
										<s:hidden name="brokerCode[%{#stat.index}]" id="brokerCode[%{#stat.index}]" value="%{AGENCY_CODE}"/> </td>
									<td> <s:property value="COMPANY_NAME" /> </td>
									<td> <s:property value="BRANCH_NAME" /> </td>
									<td> 
										<s:select name="productId[%{#stat.index}]" id="productId_%{#stat.index}" list="%{PRODUCT_LIST}" cssClass="productSelect" listKey="PRODUCT_ID" listValue="PRODUCT_NAME" headerKey="" label="" multiple="true" />
										<%--
											<s:checkboxlist name="productId[%{#attr.record_rowNum-1}]" list="productList" listKey="PRODUCT_ID" listValue="PRODUCT_NAME" /> 
										--%>
									</td>
								</tr>
								</s:iterator>
								</tbody>
							</table>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" align="center">
						<input name='type[<s:property value="%{underwriterInfo.size+1}"/>]' type="hidden" value="oi"/>
						<s:hidden name="loginId" /><s:submit type="button" name="Submit" value="Submit" cssClass="btn btn-sm btn-success" />
					</div>
				</div>
				</s:form>
			</div>
			</div>
		</div>
	</div>
</div>
<script src="<%=request.getContextPath()%>/bootstrap/js/bootstrap-multiselect.js" type="text/javascript"></script>
</body>
<script type="text/javascript">
function fnCall(from){
	if(from=='edit')
		document.underwriter.action ="viewUnderwriterMgm.action?mode=edit";
	else if(from=='list')
		document.underwriter.action = "UnderwriterCreationContoller.action";
	else if(from=='changePwd')
		document.underwriter.action = "changePassUnderwriterMgm.action?mode=changePass";	
	else if(from=='include')
		document.underwriter.action = "includeIssuerUnderwriterMgm.action?type1=include";
	else if(from=='exclude')
		document.underwriter.action = "excludeIssuerUnderwriterMgm.action?type1=exclude";
	else if(from=='openCover')
		document.info.action = "opencoverOC.action";
	else if(from=='statistics')
		document.info.action = "statisticsRE.action";
	<%--alert(from);
		postRequest('${pageContext.request.contextPath}/getABListCustomerMgm.action?agencyCode=10016&borganization='+'<s:property value="borganization"/>'+'&bcode='+'<s:hidden name="bcode"/>'+'&mode=mainTab', 'mainTab');
	}--%>else
		document.underwriter.action = from+"BrokerMgm.action";
	document.underwriter.submit();
}
$(document).ready(function() {     
      $('.productSelect').multiselect({ 
        includeSelectAllOption: true,
          enableFiltering:true,
          buttonText: function (options) {
	         if (options.length == 0) {
	             return 'None selected';
	         } else {
	             var selected = 0;
	             options.each(function () {
	                 selected += 1;
	             });
	             return selected +  ' Selected ';
	         }
	     }
    });            
});
/*
<s:iterator value="excludeIssuer" status="stat">
alert('<s:property value="productId[%{#stat.index}]"/>');
<s:if test='productId[%{#stat.index}]!=null && !"".equals(productId[%{#stat.index}])'>
	var uwgrade='<s:property value="productId[%{#stat.index}]"/>';
    var data=uwgrade.replace(/ /g,'');
    var dataArray=data.split(",");
	$("#productId_"+'<s:property value="#stat.index"/>').val(dataArray);
 $("#productId_"+'<s:property value="#stat.index"/>').multiselect("refresh");
</s:if>
</s:iterator>
*/
</script>
</html>