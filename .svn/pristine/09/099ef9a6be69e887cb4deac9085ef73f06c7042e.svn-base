<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
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
						<input type="button" class="btn btn-sm btn-info adminMenuBtn" value="Edit" onclick="fnCall('edit')"/>
					</div>
					<div class="col-xs-6 col-sm-6 col-md-12 col-lg-12">
						<input type="button" class="btn btn-sm btn-info adminMenuBtn" value="Change Password" onclick="fnCall('changePwd')"/>
					</div>
				</div>
				<div class="row">
					<div class="col-xs-6 col-sm-6 col-md-12 col-lg-12">
						<input type="button" class="btn btn-sm btn-info adminMenuBtn" value="User Details" onclick="fnCall('userDetail')"/>
					</div>
					<!-- <div class="col-xs-6 col-sm-6 col-md-12 col-lg-12">
						<input type="button" class="btn btn-sm btn-info adminMenuBtn" value="OpenCover" onclick="fnCall('openCover')"/>
					</div> -->
				</div>
				<!-- <div class="row">
					<div class="col-xs-6 col-sm-6 col-md-12 col-lg-12">
						<input type="button" class="btn btn-sm btn-info adminMenuBtn" value="Referral" onclick="fnCall('referal')"/>
					</div>					
				</div> -->
			</div>
		</div>
	</div>
	<div class="col-xs-12 col-sm-12 col-md-9 col-lg-9">
		<div class="container vehDtl">
			<div class="Card_Parent card">
			<div class="VehicleTableheading">
               <div class="row">
                  <div class="col-lg-6 col-md-6">
                    <h3><s:text name="broker.brokermanagement"/></h3>
                  </div>
                  <div class="col-lg-3 offset-md-3 offset-lg-3 col-md-3">
                      <div  class="pullRight label label-warning">
                        <b><s:property value="borganization"/>(<s:property value="agencyCode"/>)</b>
                    </div>
                  </div>
               </div>
            </div><hr>
			<div class="panel-body">
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
								<div class="row">
									<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
										<div class="panel panel-primary">
											<div class="panel-heading"> <h3><s:text name="broker.companyinfo"/></h3><hr> </div>
											<div class="panel-body">
												<div class="row">
													<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
														<div class="textV"><s:text name="broker.branch"/></div>
														<div class="tboxV"><s:property value="%{branchData[0].BRANCH_NAME}"/></div>
													</div>
													<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
														<div class="textV"><s:text name="broker.brokercode"/></div>
														<div class="tboxV"><s:property value="bcode"/></div>
													</div>
													<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
														<div class="textV"><s:text name="broker.brokerOrg"/></div>
														<div class="tboxV"><s:property value="companyName"/></div>
													</div>
													<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
														<div class="textV"><s:text name="broker.address1"/></div>
														<div class="tboxV"><s:property value="address1"/></div>
													</div>
													<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
														<div class="textV"><s:text name="broker.address2"/></div>
														<div class="tboxV"><s:property value="address2"/></div>
													</div>
													<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
														<div class="textV"><s:text name="broker.city"/></div>
														<div class="tboxV"><s:property value="emirate"/></div>
													</div>
													<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
														<div class="textV"><s:text name="broker.country"/></div>
														<div class="tboxV"><s:property value="countryName"/></div>
													</div>
													<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
														<div class="textV"><s:text name="broker.pobox"/></div>
														<div class="tboxV"><s:property value="pobox"/></div>
													</div>
													<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
														<div class="textV"><s:text name="broker.telephone"/></div>
														<div class="tboxV"><s:property value="telephone"/></div>
													</div>
													<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
														<div class="textV"><s:text name="broker.fax"/></div>
														<div class="tboxV"><s:property value="fax"/></div>
													</div>
													<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
														<div class="textV"><s:text name="broker.images" /></div>
														<div class="tboxV">
															<s:if test='broImgName!=null'>
																<img src='${pageContext.request.contextPath}<s:property value="broImgName"/>' border="0" width="150" height="60"/><br>
															</s:if>
															<s:else> &nbsp; </s:else>
														</div>
													</div>
												</div>
											</div>
										</div><br>
										<div class="panel panel-primary">
											<div class="panel-heading"> <h3><s:text name="broker.contactpersonInfo"/></h3><hr> </div>
											<div class="panel-body">
												<div class="row">
													<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
														<div class="textV"><s:text name="broker.name"/></div>
														<div class="tboxV"><s:property value="firstname"/><s:property value="lastname"/></div>
													</div>
													<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
														<div class="textV"><s:text name="broker.gender"/></div>
														<div class="tboxV"><s:property value='%{gender=="M"?"Male":(gender=="F"?"Female":"")}'/></div>
													</div>
													<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
														<div class="textV"><s:text name="broker.dob"/></div>
														<div class="tboxV"><s:property value="dob"/></div>
													</div>
													<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
														<div class="textV"><s:text name="broker.occupation"/></div>
														<div class="tboxV"><s:property value="occupation"/></div>
													</div>
													<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
														<div class="textV"><s:text name="broker.mobile"/></div>
														<div class="tboxV"><s:property value="mobile"/></div>
													</div>
													<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
														<div class="textV"><s:text name="broker.email"/></div>
														<div class="tboxV"><s:property value="bemail"/></div>
													</div>
													<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
														<div class="textV"><s:text name="broker.nationality"/></div>
														<div class="tboxV"><s:property value="nationalityNa"/></div>
													</div>
													<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
														<div class="textV"><s:text name="broker.loginid"/></div>
														<div class="tboxV"><s:property value="login_Id"/></div>
													</div>
												</div>
											</div>
										</div><br>
										<div class="panel panel-primary">
											<div class="panel-heading"> <h3><s:text name="broker.productinfo"/></h3><hr> </div>
											<div class="panel-body">
												<div class="row">
													<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
														<table class="table table-bordered table-hover" cellspacing="0" width="100%" id="tadaTable">
															<thead class="bluecolortable" >
															<tr>
																<th><s:text name="Products" /></th>
																<th><s:text name="Sum Insured" /></th>
																<th><s:text name="Commission [%]" /></th>
																<th><s:text name="Min Premium" /></th>
																<%-- <th><s:text name="Loading % Min" /></th>
																<th><s:text name="Loading % Max" /></th>
																<th><s:text name="Discount % Min" /></th>
																<th><s:text name="Discount % Max" /></th> --%>
															</tr>
															</thead>
															<tbody class="rowevencolor">
															<s:iterator value="commisionDetails" status="stat" var="record">
															<tr>
																<td> <s:property value="product_name" /> </td>
																<td> <s:property value="INSURANCE_END_LIMIT" /> </td>
																<td> <s:property value="COMMISSION" /> </td>
																<td> <s:property value="MIN_PREMIUM_AMOUNT" /> </td>
																<%-- <td> <s:property value="MIN_LOADING" /> </td>
																<td> <s:property value="MAX_LOADING" /> </td>
																<td> <s:property value="MIN_DISCOUNT" /> </td>
																<td> <s:property value="MAX_DISCOUNT" /> </td> --%>
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
				<div class="row mt-5" align="center">
					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
						<s:submit onclick="fnBack();" name="submit1" cssClass="btn btn-sm btn-danger" value="Back" />
					</div>
				</div>
				
				<s:hidden name="agencyCode"/>
				<s:hidden name="login_Id"/>
				<s:hidden name="borganization"/>
				<s:hidden name="bcode"/>
				</s:form>
			</div>
		</div>
	</div></div>
</div>
<script type="text/javascript">
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
	else if(from=='statistics')
		document.info.action = "statisticsRE.action";
	<%--alert(from);
		postRequest('${pageContext.request.contextPath}/getABListCustomerMgm.action?agencyCode=10016&borganization='+'<s:property value="borganization"/>'+'&bcode='+'<s:hidden name="bcode"/>'+'&mode=mainTab', 'mainTab');
	}--%>else
		document.info.action = from+"BrokerMgm.action";
	document.info.submit();
}
function fnBack(){
	document.info.action ='BrokerDisplayContoller.action';
	document.info.submit();
}
</script>
</body>
</html>