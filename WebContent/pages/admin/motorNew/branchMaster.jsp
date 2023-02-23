<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ page import="java.util.List,java.util.ArrayList,java.util.Map,java.util.HashMap,java.util.Collections"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page import="nl.captcha.Captcha"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	
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
	<s:form id="branch" name="branch" action=""  method="post" theme="simple" enctype="multipart/form-data">
		<div id="loading" style="width:100%">
		   <img id="loading-image" src="${pageContext.request.contextPath}/images/madisonSymbolLogo.png"/>
		</div>
		<div class="container">
			<div class="row">
				<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
					<div class="container vehDtl">
				  		<div class="Card_Parent ">
			            	<div class="card card-5">
								<div class="panel panel-heading">
									<h3><s:text name="Branch Master"/></h3><hr>
								</div>
								<font color="red"><s:actionerror cssStyle="list-style:none;"/> </font>
								<font color="green"><s:actionmessage cssStyle="list-style:none;"/> </font>
								<b id="errorDesc" style="color:red"></b>
								<s:if test='"list".equalsIgnoreCase(mode)' >
									<div class="panel-body">
										
										<div class="panel panel-primary" >
											<div class="panel-heading">
												<div class="clearfix">
													<%-- <div class="pull-left"><h3><s:text name="Broker Branch List"/></h3></div><hr> --%>
													<div class="pull-right offset-11"><s:submit cssClass="btn btn-sm btn-info" value="Add New" onclick="funEdit('add','');"></s:submit></div>
												</div>
											</div>
											<div class="panel-body">
												<div class="row">
												<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
													<table class="table table-bordered table-hover" cellspacing="0" width="100%" id="tadaTable">
														<thead class="bluecolortable" >
															<tr>
																<th class="no-sort"><s:text name="label.master.sno"/></th>
																<th><s:text name="Branch Name"/></th>	
																<th><s:text name="Branch Code"/></th>
																<th><s:text name="City Name"/></th>
																<th><s:text name="Email Address"/></th>
																<th><s:text name="Branch Status"/></th>
																<th><s:text name="Edit"/></th>
															</tr>
														</thead>
														<tbody class="rowevencolor">
															<s:iterator value="branchList" var="list" status="stat">
																<tr>
																	<td><s:property value="#stat.count" /></td>
																	<td><s:property value="#list.BRANCH_NAME" /></td>
																	<td><s:property value="#list.BRANCH_CODE" /></td>
																	<td><s:property value="#list.CITY" /></td>
																	<td><s:property value="#list.EMAIL" /></td>
																	<td><s:property value="#list.STATUS" /></td>
																	<td style="text-align: center;"><button type="button" class="btn btn-sm btn-warning" onclick="funEdit('edit','<s:property value="#list.BRANCH_CODE"/>');" >Edit</button></td>
																</tr>
															</s:iterator>
														</tbody>
													</table>
													</div>
												</div>
											</div>
										</div>		
									</div>
									
								</s:if>
								<s:elseif test='"add".equalsIgnoreCase(mode) || "edit".equalsIgnoreCase(mode)'>
								<br>
							  	 <div class="panel panel-primary" >
									<%-- <div class="panel-heading">
										<div class="clearfix">
											<div class="pull-left"><h3><s:text name="Add Broker Branch"/></h3></div><hr>
										</div>
									</div> --%>
									<div class="panel-body">
									<div class="panel panel-heading">
										<h3><s:text name="Branch Information"/></h3><hr>
									</div>
										<div class="row">
											<div class="col-xs-12 col-sm-6 col-md-4">
												<b><s:text name="Broker Name" /></b><font color="red">*</font> <br/>
												<s:textfield name="branchName" id="branchName" cssClass="form-control textfiledNew"></s:textfield>
											</div>
											<div class="col-xs-12 col-sm-6 col-md-4">
												<b><s:text name="Branch Prefix" /></b><font color="red">*</font> <br/>
												<s:textfield name="branchPrefix" id="branchPrefix" cssClass="form-control textfiledNew"></s:textfield>
											</div>
											<div class="col-xs-12 col-sm-6 col-md-4">
												<b><s:text name="Belonging Branch" /></b><font color="red">*</font> <br/>
												<%-- <s:select name="belongingBranch" id="belongingBranch" list="belongingBranchList" headerKey="" headerValue="---Select---"  listKey="BRANCH_CODE" listValue="BRANCH_NAME" cssClass="form-control" /> --%>
												Head Office
											</div>
										</div>
										<div class="row mt-3">
											<div class="col-xs-12 col-sm-6 col-md-4">
												<b><s:text name="Address 1" /></b><font color="red">*</font> <br/>
												<s:textfield name="address1" id="address1" cssClass="form-control textfiledNew"></s:textfield>
											</div>
											<div class="col-xs-12 col-sm-6 col-md-4">
												<b><s:text name="Address 2" /></b><font color="red">*</font> <br/>
												<s:textfield name="address2" id="address2" cssClass="form-control textfiledNew"></s:textfield>
											</div>
											<div class="col-xs-12 col-sm-6 col-md-4">
												<b><s:text name="Address 3" /></b><font color="red">*</font> <br/>
												<s:textfield name="address3" id="address3" cssClass="form-control textfiledNew"></s:textfield>
											</div>
										</div>
										<div class="row mt-3">
											<div class="col-xs-12 col-sm-6 col-md-4">
												<b><s:text name="branch.country" /></b><font color="red">*</font> <br/>
												<s:select name="country" id="country" list="countryList" headerKey="" headerValue="---Select---"  listKey="COUNTRY_ID" listValue="COUNTRY_NAME" cssClass="form-control" onchange="loadCities(this.value)"/>
											</div>
											<div class="col-xs-12 col-sm-6 col-md-4">
												<b><s:text name="City/Town" /></b><font color="red">*</font> <br/>
												<div id="cityAjax">
													<s:select name="city" id="city" list="cityList" headerKey="" headerValue="---Select---"  listKey="CITY_ID" listValue="CITY_NAME" cssClass="form-control" />
												</div>
											</div>
											<div class="col-xs-12 col-sm-6 col-md-4">
												<b><s:text name="branch.currency" /></b><font color="red">*</font> <br/>
												<s:select name="currency" id="currency" list="currrencyList" headerKey="" headerValue="---Select---"  listKey="CURRENCY_ID" listValue="CURRENCY_NAME" cssClass="form-control" />
											</div>
										</div>
										<div class="row mt-3">
											<div class="col-xs-12 col-sm-6 col-md-4">
												<b><s:text name="Policy Fees (%)" /></b><font color="red">*</font> <br/>
												<s:textfield name="policyFee" id="policyFee" cssClass="form-control textfiledNew"></s:textfield>
											</div>
											<div class="col-xs-12 col-sm-6 col-md-4">
												<b><s:text name="branch.phone" /></b><font color="red">*</font> <br/>
												<s:textfield name="phone" id="phone" cssClass="form-control textfiledNew"></s:textfield>
											</div>
											<div class="col-xs-12 col-sm-6 col-md-4">
												<b><s:text name="branch.fax" /></b><font color="red">*</font> <br/>
												<s:textfield name="fax" id="fax" cssClass="form-control textfiledNew"></s:textfield>
											</div>
										</div>
										<div class="row mt-3">	
											<div class="col-xs-12 col-sm-6 col-md-4">
												<b><s:text name="branch.email" /></b><font color="red">*</font> <br/>
												<s:textfield name="email" id="email" cssClass="form-control textfiledNew"></s:textfield>
											</div>
											<div class="col-xs-12 col-sm-6 col-md-4">
												<b><s:text name="Effective Date " /></b><font color="red">*</font> <br/>
												<s:textfield name="effectiveDate"  cssClass="form-control textfiledNew datePicker" id="effectiveDate" ></s:textfield>
											</div>
											<div class="col-xs-12 col-sm-6 col-md-4">
												<b><s:text name="branch.status" /></b><font color="red">*</font> <br/>
												<s:radio list="#{'Y':'Activate','N':'Deactivate'}" name="status" id="status" value='%{status==null?"Y":status}' />
											</div>
											<div class="col-xs-12 col-sm-6 col-md-4">
												<b><s:text name="branch.remarks" /></b> <br/>
												<s:textarea name="remarks"  cssClass="form-control textfiledNew" id="remarks" ></s:textarea>
											</div>
										</div>
										<br>
										<div class="panel panel-heading">
											<h3><s:text name="Branch Currency Settings"/></h3><hr>
										</div>
										<div class="row mt-3">
											<div class="col-xs-12 col-sm-6 col-md-4">
												<b><s:text name="branch.originCountry" /></b><font color="red">*</font> <br/>
												<s:select name="originCountry" id="originCountry" list="countryList" headerKey="" headerValue="---Select---"  listKey="COUNTRY_ID" listValue="COUNTRY_NAME" cssClass="form-control" />
											</div>
											<div class="col-xs-12 col-sm-6 col-md-4">
												<b><s:text name="branch.destiCountry" /></b><font color="red">*</font> <br/>
												<s:select name="destiCountry" id="destiCountry" list="countryList" headerKey="" headerValue="---Select---"  listKey="COUNTRY_ID" listValue="COUNTRY_NAME" cssClass="form-control" />
											</div>
											<div class="col-xs-12 col-sm-6 col-md-4">
												<b><s:text name="branch.tax" /></b><font color="red">*</font> <br/>
												<s:textfield name="tax"  cssClass="form-control textfiledNew" id="tax" ></s:textfield>
											</div>
										</div>
										<div class="row mt-3">
											<div class="col-xs-12 col-sm-6 col-md-4">
												<b><s:text name="branch.decimalPlaces" /></b><font color="red">*</font> <br/>
												<s:textfield name="decimalPlaces" id="decimalPlaces" maxlength="3" cssClass="form-control textfiledNew"></s:textfield>
											</div>
											<div class="col-xs-12 col-sm-6 col-md-4">
												<b><s:text name="branch.lang" /></b><font color="red">*</font> <br/>
												<s:radio list="#{'Y':'Yes','N':'No'}" name="lang" id="lang" value='%{lang==null?"Y":lang}' />
											</div>
										</div>
										<br>
										<div class="panel panel-heading">
											<h3><s:text name="File Information"/></h3><hr>
										</div>
										<s:if test='!"add".equals(mode)'>
			          						<div class="row mt-3">
				          						<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" align="right">
				          							<!-- <a href="#" onclick="fnShowImgInput('enew')">Upload New Image</a> -->
				          							<button type="button" class="btn btn-sm btn-warning" onclick="fnShowImgInput('enew')">Upload New Image</button>
			          							</div>
			          						</div>
		          						</s:if>
										<div class="row mt-3">
											<div class="col-xs-12 col-sm-6 col-md-4" id="headerEdit">
												<b><s:text name="branch.headerImage" /></b><font color="red">*</font><br/>
												<s:file name="headerImage" id="headerImage" cssClass="inputBox" onchange="fnImgPreview(this.id);" />
											</div>
											<div class="col-xs-12 col-sm-6 col-md-4">
												<%-- <div style=" width: 504px; height:64px; overflow: auto;"><img id="headerImagePreview" alt="Header Image" src="${pageContext.request.contextPath}/images/<s:property value='headerName'/>" /></div>                        					    	
                       					    	<div style="color: red; display: none;" id="headerImageError" ><br/>You uploaded image width is<span id="headerImageWidth"></span> and height is <span id="headerImageHeight"></span><br/>Please Upload the image containg Width = <b>715 px</b> and Height = <b>64px</b>  </div> --%>
                       					    	<div style=" width: 504px; height:100px; overflow: auto;"><img id="headerImagePreview" alt="Header Image" src="${pageContext.request.contextPath}/images/<s:property value='headerName'/>" /></div>                        					    	
											</div>
										</div>
										<div class="row mt-3">
											<div class="col-xs-12 col-sm-6 col-md-4" id="footerEdit">
												<b><s:text name="branch.footerImage" /></b><font color="red">*</font> <br/>
												<s:file name="footerImage" id="footerImage" cssClass="inputBox" onchange="fnImgPreview(this.id);" />
											</div>
											<div class="col-xs-12 col-sm-6 col-md-4">
												<%-- <div style=" width: 504px; height:80px; overflow: auto;"><img id="footerImagePreview" alt="Footer Image"  src="${pageContext.request.contextPath}/images/<s:property value='footerName'/>"  /></div>
                       					    	<div style="color: red; display: none;" id="footerImageError" ><br/>You uploaded image width is <span id="footerImageWidth"></span> and height is <span id="footerImageHeight"></span><br/>Please Upload the image containg Width = <b>815 px</b> and Height = <b>80px</b>  </div> --%>
                       					    	<div style=" width: 504px; height:100px; overflow: auto;"><img id="footerImagePreview" alt="Footer Image"  src="${pageContext.request.contextPath}/images/<s:property value='footerName'/>"  /></div>
											</div>
										</div>
										<div class="row mt-3">
											<div class="col-xs-12 col-sm-6 col-md-4" id="signEdit">
												<b><s:text name="branch.signImage" /></b><font color="red">*</font> <br/>
												<s:file name="signImage" id="signImage" cssClass="inputBox" onchange="fnImgPreview(this.id);" />
											</div>
											<div class="col-xs-12 col-sm-6 col-md-4">
												<%-- <div style=" width: 504px; height:322px; overflow: auto;"><img id="signImagePreview" alt="Sign Image" width="300" height="192" src="${pageContext.request.contextPath}/images/<s:property value='signImageName'/>"  /></div>
                       					    	<div style="color: red; display: none;" id="signImageError" ><br/>You uploaded image width is <span id="signImageWidth"></span> and height is <span id="signImageHeight"></span><br/>Please Upload the image containg Width = <b>470 px</b> and Height = <b>322px</b>  </div> --%>
                       					    	<div style=" width: 504px; height:100px; overflow: auto;"><img id="signImagePreview" alt="Sign Image" src="${pageContext.request.contextPath}/images/<s:property value='signImageName'/>"  /></div>
											</div>
										</div>
										<div class="row mt-3">
											<div class="col-xs-12 col-sm-6 col-md-4" id="stampEdit">
												<b><s:text name="branch.stamp" /></b><font color="red">*</font> <br/>
												<s:file name="stampImage" id="stampImage" cssClass="inputBox" onchange="fnImgPreview(this.id);" />
											</div>
											<div class="col-xs-12 col-sm-6 col-md-4">
												<%-- <div style=" width: 504px; height:322px; overflow: auto;"><img id="stampImagePreview" alt="Stamp Image" width="300" height="192" src="${pageContext.request.contextPath}/images/<s:property value='stampImageName'/>"/></div>
                       					    	<div style="color: red; display: none;" id="stampImageError" ><br/>You uploaded image width is <span id="stampImageWidth"></span> and height is <span id="stampImageHeight"></span><br/>Please Upload the image containg Width = <b>470 px</b> and Height = <b>322px</b>  </div> --%>
                       					    	<div style=" width: 504px; height:100px; overflow: auto;"><img id="stampImagePreview" alt="Stamp Image" src="${pageContext.request.contextPath}/images/<s:property value='stampImageName'/>"/></div>
											</div>
										</div> 
									</div>
								<br/>
								<div class="row">
								  	 <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" align="center">
								  	 	<button type="button" class="btn btn-sm btn-danger" value="Back" onclick="funSubmit('list','');">Back</button>
								  	 	<button type="button" class="btn btn-sm btn-success" value="Submit" id="submitBtn" onclick="validation();">Submit</button>
								  	 </div>
							  	 </div>
							  	 </div>
							  	
								</s:elseif>
							</div>
						</div>
					</div>
				</div>
			</div>		
		</div>
		<s:hidden name="mode" id="mode"/>
		<s:hidden name="branchCode" id="branchCode"/>
		<s:hidden name="imgMode" id="imgMode"/>
		
	</s:form>
<script type="text/javascript">


function validation(){		 	
	//postFormRequest("ajaxValidationBranchMaster.action?reqFrom=validation","errorDesc",val);
	document.getElementById('errorDesc').innerHTML = "";
	postFormRequest('${pageContext.request.contextPath}/ajaxValidationBranchMaster.action?reqFrom=validation', 'errorDesc','branch');
	return false;
}

function imgUpload() {
 try{
	 var error = "";
	 var imgMode = document.getElementById('imgMode').value;
	 var mode = document.getElementById('mode').value;
	 //alert("imgMode=> "+imgMode+ " mode=> "+mode);
	 document.getElementById('errorDesc').innerHTML = "";
	 if(imgMode=='Y' || mode=='add'){
		 if(document.getElementById('headerImage').value == "") {
			error+="please choose Header Image <br/>"
		 }if(document.getElementById('footerImage').value == "") {
			error+="please choose Footer Image <br/>"
		 }if(document.getElementById('signImage').value == "") {
			error+="please choose Sign Image <br/>"
		 }if(document.getElementById('stampImage').value == "") {
			error+="please choose Stamp Image <br/>"
		 }
	 }
	 if(error.length > 0) {
		document.getElementById('errorDesc').innerHTML = error;
		$("html, body").animate({ scrollTop: 0 }, 600);
	}else{
		funSubmit('<s:property value="mode" />','');
	}
}catch(e) {
		console.debug(e);
	}
}
function funSubmit(mode,val){
	var action="";
	if(mode =='list'){
		action='branchListBranchMaster.action';
	}
	else if(mode=='add' || mode=='edit'){
		action='saveBranchBranchMaster.action';
	}
	document.branch.mode.value=mode;
	document.branch.action=action;
	document.branch.submit();
}
function funEdit(mode,val){
	var action="";
	if(mode=='add'){
		action='editBranchBranchMaster.action';
	}else if(mode=='edit'){
		document.branch.branchCode.value=val;
		action='editBranchBranchMaster.action';
	}
	document.branch.mode.value=mode;
	document.branch.action=action;
	document.branch.submit();
}
$(function() {
	try {
		$('.datePicker').datepicker({
			todayHighlight: true,
			format: "dd/mm/yyyy"
		}).on('changeDate', function(e){
		    $(this).datepicker('hide');			    
		});
	} catch(err) {console.error(err);}
});


function loadCities(val){		 	
	 postRequest("getCityBranchMaster.action?countryAjax="+val+"&&reqFrom=city","cityAjax");
}

function fnImgPreview(id) {
	var width, height;
	var oFReader = new FileReader();
    oFReader.readAsDataURL(document.getElementById(id).files[0]);        
    oFReader.onload = function (oFREvent) {
    	var element = document.getElementById(id+"Preview");
        document.getElementById(id+"Preview").src = oFREvent.target.result;

        //total size in bytes
        var sizeInBytes = oFREvent.total;

        //for this to work, you have to remove the width/height from the img#uploadPreview element
        var imageWidth = element.width;
        var imageHeight = element.height;
        
        /*if (id.toLowerCase() == "headerImage".toLowerCase()) {
	    	if (imageWidth != 715 || imageHeight != 64) {	    		
	    		document.getElementById(id+"Error").style.display = "block";
	    		document.getElementById(id+"Width").innerHTML = imageWidth;
	    		document.getElementById(id+"Height").innerHTML = imageHeight;	    		
	    		document.getElementById("submitBtn").style.display = "none";
	    	} else {
	    		document.getElementById("submitBtn").style.display = "block";
	    		document.getElementById(id+"Error").style.display = "none";
	    	}
	    } else if (id.toLowerCase() == "footerImage".toLowerCase()) {
	    	if (imageWidth != 815 || imageHeight != 80) {	    		
	    		document.getElementById(id+"Error").style.display = "block";
	    		document.getElementById(id+"Width").innerHTML = imageWidth;
	    		document.getElementById(id+"Height").innerHTML = imageHeight;	    		
	    		document.getElementById("submitBtn").style.display = "none";
	    	} else {
	    		document.getElementById("submitBtn").style.display = "block";
	    		document.getElementById(id+"Error").style.display = "none";
	    	}
	    } else if (id.toLowerCase() == "signImage".toLowerCase()) {
	    	if (imageWidth != 470 || imageHeight != 322) {	    		
	    		document.getElementById(id+"Error").style.display = "block";
	    		document.getElementById(id+"Width").innerHTML = imageWidth;
	    		document.getElementById(id+"Height").innerHTML = imageHeight;	    		
	    		document.getElementById("submitBtn").style.display = "none";
	    	} else {
	    		document.getElementById("submitBtn").style.display = "block";
	    		document.getElementById(id+"Error").style.display = "none";
	    	}
	    } else if (id.toLowerCase() == "stampImage".toLowerCase()) {
	    	if (imageWidth != 470 || imageHeight != 322) {	    		
	    		document.getElementById(id+"Error").style.display = "block";
	    		document.getElementById(id+"Width").innerHTML = imageWidth;
	    		document.getElementById(id+"Height").innerHTML = imageHeight;	    		
	    		document.getElementById("submitBtn").style.display = "none";
	    	} else {
	    		document.getElementById("submitBtn").style.display = "block";
	    		document.getElementById(id+"Error").style.display = "none";
	    	}
	    }*/
                        
    }  
}
<s:if test='"add".equals(mode)'>
//alert("add");
document.branch.imgMode.value="N";
fnShowImgInput('new');
</s:if>
<s:else>
//alert("edit");
document.branch.imgMode.value="N";
fnShowImgInput('edit');
</s:else>

function fnShowImgInput(val) {
if (val == "new" || val == "enew") {
	if (val == "enew") {
		document.branch.imgMode.value="Y";
	}
	document.getElementById('headerEdit').style.display = 'block';
	document.getElementById('footerEdit').style.display = 'block';
	document.getElementById('stampEdit').style.display = 'block';
	document.getElementById('signEdit').style.display = 'block';
	document.getElementById('headerImagePreview').src='';
	document.getElementById('footerImagePreview').src='';
	document.getElementById('signImagePreview').src='';
	document.getElementById('stampImagePreview').src='';
} else if (val == "edit") {
	document.getElementById('headerEdit').style.display = 'none';
	document.getElementById('footerEdit').style.display = 'none';
	document.getElementById('stampEdit').style.display = 'none';
	document.getElementById('signEdit').style.display = 'none';
}
}
</script>
</body>
</html>   
