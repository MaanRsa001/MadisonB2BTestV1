<%@ page isELIgnored="false" %>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/WEB-INF/displaytag.tld" prefix="display"%>
<!DOCTYPE HTML>
<html>
<head>
<%-- 	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/dataTables/css/dataTables.responsive.css"> --%>
<%-- 	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/dataTables/css/dataTables.jqueryui.css"> --%>
<%-- 	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/dataTables/css/dataTables.tableTools.css"> --%>
<%-- 	<link href="${pageContext.request.contextPath}/dataTables/css/jquery-start-ui.css" rel="stylesheet" type="text/css" /> --%>
<%-- 	<script type="text/javascript" src="${pageContext.request.contextPath}/dataTables/js/jquery-1.11.1.min.js"></script> --%>
<%-- 	<script type="text/javascript" src="${pageContext.request.contextPath}/dataTables/js/jquery.dataTables.min.js"></script> --%>
<%-- 	<script type="text/javascript" src="${pageContext.request.contextPath}/dataTables/js/dataTables.jqueryui.js"></script> --%>
<%-- 	<script type="text/javascript" src="${pageContext.request.contextPath}/dataTables/js/dataTables.responsive.js"></script> --%>
<%-- 	<script type="text/javascript" src="${pageContext.request.contextPath}/dataTables/js/dataTables.tableTools.js"></script> --%>
	
	
	
	<script type="text/javascript">
  	jQuery(function ($) {
  		try {
			var data1 = $('.display').dataTable( {
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
  	<style>
	#loading {
	  width: 100%;
	  height: 100%;
	  top: 0px;
	  left: 0px;
	  position: fixed;
	  display: block;
	  opacity: 0.7;
	  background-color: #fff;
	  z-index: 99;
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
<div class="container vehDtl">
<div class="Card_Parent ">
<div class="card card-5">
			<div class="panel-heading">
				<h3><s:text name="Referral Cases" /></h3><hr>
			</div>			
			<div class="panel-body">
				<div class="row">
					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
						<div id="errors1" style="color: red; ">&nbsp;</div><br/>
					</div>
				</div>
<!-- 				<div class="row"> -->
				
				<ul class="nav nav-pills mb-3" id="pills-tab" role="tablist">
				  <li class="nav-item">
				    <a class="nav-link active" id="pills-home-tab" data-toggle="pill" href="#pills-home" role="tab" aria-controls="pills-home" aria-selected="true" onclick="getTab('pendingTab')"><b>Pending Quotes</b></a>
				  </li>
				  <li class="nav-item">
				    <a class="nav-link" id="pills-profile-tab" data-toggle="pill" href="#pills-profile" role="tab" aria-controls="pills-profile" aria-selected="false" onclick="getTab('approvedTab')"><b>Approved Quotes</b></a>
				  </li>
				  <li class="nav-item">
				    <a class="nav-link" id="pills-contact-tab" data-toggle="pill" href="#pills-contact" role="tab" aria-controls="pills-contact" aria-selected="false" onclick="getTab('rejectedTab')"><b>Rejected Quotes</b></a>
				  </li>
				</ul>
				<br/>
				<br/>
				<div class="tab-content" id="pills-tabContent">
				  <div class="tab-pane fade show active" id="pills-home" role="tabpanel" aria-labelledby="pills-home-tab">
					<div title="Pending Quotes" style="padding:10px"  id="tone">
							<div id="pendingPolicies">
								<s:form id="pending" name="pending" theme="simple">									
									<div class="row">
										<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
											<div class="text"> <s:text name="product.product"/> </div>
											<div class="tbox">
												<s:select name="productID" cssClass="form-control" id="productID" list="productDet" headerKey="" headerValue="---Select---" listKey="PRODUCT_ID" listValue="PRODUCT_NAME" />
											</div>
										</div>
										<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
											<div class="text"><s:text name="Regions"/></div>
											<div class="tbox">
												<s:select name="region" id="region1" cssClass="form-control" list="regionList" listKey="BRANCH_CODE" listValue="BRANCH_NAME" onchange="getBranches(this.value,'pendingBranch');" />
											</div>
										</div>
										<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
											<div class="text"><s:text name="admin.user.branch"/></div>
											<div class="tbox">
												<s:select name="branchId" cssClass="form-control" id="branchId" list="branchDet" headerKey="ALL" headerValue="ALL" listKey="BRANCH_CODE" listValue="BRANCH_NAME" />
											</div>
										</div>
										<s:if test='(rdate!=null || "".equals(rdate)) && "0".equals(index)'>
											<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
												Selected Date is <b><s:property value="rdate"/></b>
											</div>
										</s:if>
									</div>
									<br class="clear" />
									<div class="row">
										<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" align="center">
											<input type="button" class="btn btn-sm btn-success" value="Search"  onclick="fnTab('', 'pending','','');">
										</div>
									</div>
									<br class="clear" /> 
								</s:form>
							</div>
						</div>
					</div>
					
				  <div class="tab-pane fade" id="pills-profile" role="tabpanel" aria-labelledby="pills-profile-tab">
				  <div title="Approved Quotes" style="padding:10px"  id="ttwo">
							<div id="approvedPolicies">
								<s:form id="approved" name="approved" theme="simple">
									 
									<div class="row">
										<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
											<div class="text"> <s:text name="product.product"/> </div>
											<div class="tbox">														
												<s:select name="productID" cssClass="form-control" id="productID" list="productDet" headerKey="" headerValue="---Select---" listKey="PRODUCT_ID" listValue="PRODUCT_NAME" />
											</div>
										</div>
										<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
											<div class="text"><s:text name="Regions"/></div>
											<div class="tbox">
												<s:select name="region" id="region1" cssClass="form-control" list="regionList" listKey="BRANCH_CODE" listValue="BRANCH_NAME" onchange="getBranches(this.value,'pendingBranch');" />
											</div>
										</div>
										<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
											<div class="text"><s:text name="admin.user.branch"/></div>
											<div class="tbox">
												<s:select name="branchId" cssClass="form-control" id="branchId" list="branchDet" headerKey="ALL" headerValue="ALL" listKey="BRANCH_CODE" listValue="BRANCH_NAME" />
											</div>
										</div>
										<s:if test='(rdate!=null || "".equals(rdate)) && "1".equals(index)'>
											<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
												Selected Date is <b><s:property value="rdate"/></b>
											</div>
										</s:if>
									</div>
									<br class="clear" />
									<div class="row">
										<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" align="center">
											<input type="button" class="btn btn-sm btn-success" value="Submit" onclick="fnTab('', 'approved','','');">
										</div>
									</div>
									<br class="clear" />											
								</s:form>
							</div>
						</div>
				  </div>
				  <div class="tab-pane fade" id="pills-contact" role="tabpanel" aria-labelledby="pills-contact-tab">
				  	<div title="Approved Quotes" style="padding:10px"  id="tthree">
							<div id="rejectedPolicies">
								<s:form id="rejected" name="rejected" theme="simple">
									
									<div class="row">
										<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
											<div class="text"> <s:text name="product.product"/> </div>
											<div class="tbox">														
												<s:select name="productID" cssClass="form-control" id="productID" list="productDet" headerKey="" headerValue="---Select---" listKey="PRODUCT_ID" listValue="PRODUCT_NAME"  />
											</div>
										</div>
										<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
											<div class="text"><s:text name="Regions"/></div>
											<div class="tbox">
												<s:select name="region" id="region1" cssClass="form-control" list="regionList" listKey="BRANCH_CODE" listValue="BRANCH_NAME" onchange="getBranches(this.value,'pendingBranch');" />
											</div>
										</div>
										<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
											<div class="text"><s:text name="admin.user.branch"/></div>
											<div class="tbox">
												<s:select name="branchId" cssClass="form-control" id="branchId" list="branchDet" headerKey="ALL" headerValue="ALL" listKey="BRANCH_CODE" listValue="BRANCH_NAME" />
											</div>
										</div>
										<s:if test='(rdate!=null || "".equals(rdate)) && "2".equals(index)'>
											<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
												Selected Date is <b><s:property value="rdate"/></b>
											</div>
										</s:if>
									</div>
									<br class="clear" />
									<div class="row">
										<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" align="center">
											<input type="button" class="btn btn-sm btn-success" value="Submit" onclick="fnTab('', 'rejected','','');" >
										</div>
									</div>
									<br class="clear" />											
								</s:form>
							</div>
						</div>	
				  </div>
				</div>
				
				
					
<!-- 				</div> -->
			</div>
		</div>
	</div>
</div>
<s:form name="form1" id="form1" theme="simple">
	<s:hidden name="agencyCode" id="agencyCode1"/>
	<s:hidden name="borganization" id="borganization1"/>
	<s:hidden name="quoteStatus" id="quoteStatus"/>
	<s:hidden name="applicationNo" id="applicationNo"/>
	<s:hidden name="loginId" id="loginId"/>
	<s:hidden name="quoteNo" id="quoteNo"/>
	<s:hidden name="productId" id="productId"/>
	<s:hidden name="entryDate" id="entryDate"/>
	<s:hidden name="pid" id="pid"/>
	<s:hidden name="reqFrom" id="reqFrom"/>
	<s:hidden name="schemeId" id="schemeId"/>
</s:form>
<script type="text/javascript">
function fnCall(from){
	if(from=='edit')
		document.rejected.action = from+"BrokerMgm.action?mode=edit";
	else if(from=='userDetail')
		document.rejected.action = "getABListUserMgm.action?mode1=broker";
	else if(from=='customerDetail')
		document.rejected.action = "getABListCustomerMgm.action?mode1=broker";
	else if(from=='referal')
		document.rejected.action = "getOCListReferal.action";
	else if(from=='openCover')
		document.rejected.action = "opencoverOC.action";
	else
		document.rejected.action = from+"BrokerMgm.action";
	document.rejected.submit();
}

function fnTab(pid, reqFrom, value, index){
	var agencyCode=document.getElementById('agencyCode1').value;
	var borg=document.getElementById('borganization1').value;		
	var productId=document.getElementById('productID').value;
    if(reqFrom=="pending"){ 
    	var error="";
		var branch=document.pending.branchId.value;
		var region=document.pending.region.value;
		if(""==pid){
	    	 pid=document.pending.productID.value;
	  	 }
	    if(""==pid){
	      error=error+"Please select Product</br>";
	    }
	    if(""==branch){
	      error=error+"Please select Branch";
	    }
	    if(""==error){document.getElementById("errors1").innerHTML=error;
	    		postRequest('${pageContext.request.contextPath}/getOCAjaxReferal.action?reqFrom='+reqFrom+'&productID='+pid+'&index=0&from1=ajax&agencyCode='+agencyCode+'&borganization='+borg+'&branchId='+branch+'&region='+region, 'pendingPolicies');	
	    }else{
			document.getElementById("errors1").innerHTML=error;
		}				 	
  		}else if(reqFrom=="approved"){
  			var error="";
		var branch=document.approved.branchId.value;
		var region=document.approved.region.value;
		if(""==pid){
	    	 pid=document.approved.productID.value;
	  	 }
	    if(""==pid){
	      error=error+"Please select Product</br>";
	    }
	    if(""==branch){
	      error=error+"Please select Branch";
	    }
	    if(""==error){
	    	document.getElementById("errors1").innerHTML=error;
	    	postRequest('${pageContext.request.contextPath}/getOCAjaxReferal.action?reqFrom='+reqFrom+'&productID='+pid+'&index=1&from1=ajax&agencyCode='+agencyCode+'&borganization='+borg+'&branchId='+branch+'&region='+region, 'approvedPolicies');	
	    }else{
			document.getElementById("errors1").innerHTML=error;
		}
		
  		}else if(reqFrom=="rejected"){
  			var error="";
		var branch=document.rejected.branchId.value;
		var region=document.rejected.region.value;
		if(""==pid){
	    	 pid=document.rejected.productID.value;
	  	 }
	    if(""==pid){
	      error=error+"Please select Product</br>";
	    }
	    if(""==branch){
	      error=error+"Please select Branch";
	    }
	    if(""==error){
	    	document.getElementById("errors1").innerHTML=error;
	    	postRequest('${pageContext.request.contextPath}/getOCAjaxReferal.action?reqFrom='+reqFrom+'&productID='+pid+'&index=2&from1=ajax&agencyCode='+agencyCode+'&borganization='+borg+'&branchId='+branch+'&region='+region+'&region='+region, 'rejectedPolicies');	
	    }else{
			document.getElementById("errors1").innerHTML=error;
		}
		
  		}else{
  			var error="";
		var branch=document.pending.branchId.value;
		if(""==pid){
	    	 pid=document.pending.productID.value;
	  	 }
	    if(""==pid){
	      error=error+"Please select Product</br>";
	    }
	    if(""==branch){
	      error=error+"Please select Branch";
	    }
	    if(""==error){
	    	document.getElementById("errors1").innerHTML=error;
	    	postRequest('${pageContext.request.contextPath}/getOCAjaxReferal.action?reqFrom='+value+'&productID='+pid+'&from1=ajax&index='+index+'&rdate='+reqFrom+'&agencyCode='+agencyCode+'&borganization='+borg+'&branchId='+branch+'&region='+region, value+'Policies');	
	    }else{
			document.getElementById("errors1").innerHTML=error;
		}
  			
  		}
    		
				
  	}		   	
/*
$(function(){
	var index = '<s:property value="index"/>';
	var t = $('#tabs');
	var tabs = t.tabs('tabs');
	t.tabs('select', tabs[index].panel('options').title);
});
*/
function editQuote(applicationNo, loginId, proID, eDate,quoteNo,schemeId, quoteStatus,quoteNo1){
	document.form1.quoteStatus.value='RU';
	document.form1.quoteNo.value=quoteNo;
	document.form1.applicationNo.value=applicationNo;
	document.form1.loginId.value=loginId;
	document.form1.pid.value=proID;
	document.form1.productId.value=proID;
	document.form1.quoteStatus.value=quoteStatus;
	document.form1.schemeId.value=schemeId;
	if(proID=='3'|| proID=='11'){
		document.form1.action = "editQuoteQuotation.action";
	}
	else if(proID=='65'){
		document.form1.action = "editQuoteMotor.action";
	}
	else if(proID=='30'){
		document.form1.action = "packageSelectionHome.action";
	}
	else if(proID=='33'){
		document.form1.action = "initTravel.action";
	}
	document.form1.submit();	
}
function getTab(loginType){
	if(loginType=="pendingTab") {
		//document.getElementById("pendingTab").className="tabBgA rEdge";
		//document.getElementById("approvedTab").className="tabBg rEdge";
		//document.getElementById("rejectedTab").className="tabBg rEdge";
		document.getElementById("tone").style.display = "block";
		document.getElementById("ttwo").style.display = "none";
		document.getElementById("tthree").style.display = "none";
		//document.getElementById("loginType").value = loginType;
	}
	else if(loginType=="approvedTab") {
		//document.getElementById("approvedTab").className="tabBgA rEdge";
		//document.getElementById("pendingTab").className="tabBg rEdge";
		//document.getElementById("rejectedTab").className="tabBg rEdge";
		document.getElementById("ttwo").style.display = "block";
		document.getElementById("tone").style.display = "none";
		document.getElementById("tthree").style.display = "none";
		//document.getElementById("loginType").value = loginType;
	}else if(loginType=="rejectedTab") {
		//document.getElementById("rejectedTab").className="tabBgA rEdge";
		//document.getElementById("pendingTab").className="tabBg rEdge";
		//document.getElementById("approvedTab").className="tabBg rEdge";
		document.getElementById("tthree").style.display = "block";
		document.getElementById("tone").style.display = "none";
		document.getElementById("ttwo").style.display = "none";
		//document.getElementById("loginType").value = loginType;
	}
	document.getElementById("loading").style.display = "none";
}
try{
getTab('pendingTab');
}catch(e){}
</script>
</body>
</html>