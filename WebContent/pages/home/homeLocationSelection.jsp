<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ page import="java.util.List,java.util.ArrayList,java.util.Map"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page import="nl.captcha.Captcha"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html <s:if test="locale.language == 'ar'">dir="rtl"</s:if> >
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/dataTables/css/dataTables.responsive.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/dataTables/css/dataTables.jqueryui.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/dataTables/css/dataTables.tableTools.css">
	<script type="text/javascript" src="${pageContext.request.contextPath}/dataTables/js/dataTables.responsive.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/dataTables/js/dataTables.tableTools.js"></script>
	
	<script type="text/javascript">
	 	$(document).ready(function () {
	 		var appNo = $("#quotation input[name=applicationNo]").val();
	 		locationDetailsTable(appNo)
		 });
	 	
	 	function locationDetailsTable(appNo){
	 		$('#locationDetailsTableId').DataTable({
	 			 ajax: {
	 		        url: 'locationAjaxHome.action',
	 		        type: 'POST',
	 		        data: {
	 		        	applicationNo: appNo
	 		        },
	 		        dataSrc: 'locationDtlsList'
	 		    },
	 		   "columns": [
   			   {"render" : function(data,type,row,meta){
   			   		return meta.row+1
   			   }},
                  {"visible": true,"searchable": true,"data":"LOCATION_NAME"},
                  {"visible": true,"searchable": true,"data":"STATUS_DES"},
                  {"render" : function(data,type,row,meta){
                 		return '<a style="cursor:pointer" onclick="locationPopup(\'edit\',\''+row.LOCATION_ID+'\');" data-toggle="modal" data-target="#locationDetailsModal" data-backdrop="static"><i class="fas fa-pencil-alt"></i></a>'
                 },'class': 'text-center'},
                 {"render" : function(data,type,row,meta){
               		return '<a style="cursor:pointer" onclick="locationDel(\''+row.LOCATION_NAME+'\',\''+row.LOCATION_ID+'\');" data-toggle="modal" data-target="#locationDetailsDelModal" data-backdrop="static"><i class="fas fa-trash-alt"></i></a>'
                 },'class': 'text-center'}
	 		    ],
		    	"responsive": true,
		        "columnDefs": [
		          { "orderable": true, "targets": 0 }
		        ],
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
	 	}
 	</script>
 	<style>
        * {
            font-family: 'Nunito', sans-serif !important;
        }

        .fas {
            font-family: "Font Awesome 5 Free" !important;
        }

        .PolicyInformationPage .card {
            padding: 20px 20px 20px 150px;
            border: 0px !important
        }

        .PolicyInformationPage .Card_Parent {
            border-radius: 4px;
            background: #fff;
            box-shadow: 0 6px 10px rgba(0, 0, 0, .08), 0 0 6px rgba(0, 0, 0, .05);
            transition: .3s transform cubic-bezier(.155, 1.105, .295, 1.12), .3s box-shadow, .3s -webkit-transform cubic-bezier(.155, 1.105, .295, 1.12);
        }


        .PolicyInformationPage .card h3 {
            font-weight: 700;
            color: #261e6a;
        }

        .PolicyInformationPage .card-1 {
            background-image: url("${pageContext.request.contextPath}/assets/Images/home-insurances.png");
            background-repeat: no-repeat;
            background-position: left;
            background-size: 200px;
        }

        .PolicyInformationPage .card-2 {
            background-image: url("${pageContext.request.contextPath}/assets/Images/write.png");
            background-repeat: no-repeat;
            background-position: left;
            background-size: 140px;
        }

        .PolicyInformationPage hr {
            margin: 10px 0px;
        }

        .PolicyInformationPage .btn-outline-primary {
            border-radius: 0px;
            font-weight: bolder;
        }

        .form-control,
        .input-group-text {
            border-radius: 0 !important;
            height: 50px;
        }

        .PolicyInformationPage .input-group-text {
            background-color: white !important;
            color: #e2a53a;

        }

        .PolicyInformationPage .LabelHeading {
            font-weight: bolder;
        }
    </style>
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
	<s:form name="quotation" id="quotation" theme="simple">
		<s:hidden name="customerId" id="customerId"/>
		<s:hidden name="coreAppCode" id="coreAppCode"/>
		<s:hidden name="custArNo" id="custArNo"/>
		<s:hidden name="custCoreCode" id="custCoreCode"/>
		<s:hidden name="clientCustomerId" id="clientCustomerId"/>
		<s:hidden name="address1" id="address1"/>
		<s:hidden name="address2" id="address2"/>
		<s:hidden name="city" id="city"/>
		<s:hidden name="poBox" id="poBox"/>
		<s:hidden name="gender" id="gender"/>
		<s:hidden name="occupation" id="occupation"/>
		<s:hidden name="custdob" id="custdob"/>
		<s:hidden name="custAlterMobileNo" id="custAlterMobileNo"/>
		<s:hidden name="custnrc1" id="custnrc1"/>
		<s:hidden name="custnrc2" id="custnrc2"></s:hidden>
		<s:hidden name="custnrc3" id="custnrc3"></s:hidden>
		<s:hidden name="custPassportNo" id="custPassportNo"></s:hidden>
        <s:hidden name="quoteNo" id="quoteNo"/>
        <s:hidden name="applicationNo" id="applicationNo"/>
		<s:hidden name="menuType" id="menuType"/>
		<s:hidden name="quoteStatus" id="quoteStatus"/>
		<s:if test="hasActionErrors()">
	    	<div class="container PolicyInformationPage mt-5">
		    	<div class="Card_Parent  mt-4">
		    		<div class="card card-1">
		                <h3>Validation Errors</h3>
		                <hr>
		                <div class="row">
			        		<s:actionerror style="color:red;"/>
			        	</div>
		            </div>
		    	</div>
		    </div>
    	</s:if>
    	<s:if test='issuer != null'>
			<div class="container PolicyInformationPage mt-5">
				 <div class="Card_Parent PolicyInformation">
		            <div class="card card-1">
		                <h3><s:text name="customer.brokerInfo" /></h3>
		                <hr>
		                <div class="row">
		                    <div class="col-md-5 col-6">
		                        <label class="LabelHeading"><s:text name="customer.broker.name"  /></label>
		                        <div class="input-group mb-3">
		                            <div class="input-group-prepend">
		                                <span class="input-group-text border border-right-0">
		                                	<i class="fas fa-user-check"></i>
		                               	</span>
		                            </div>
		                            <s:select name="brokerCode" id="brokerCode" list="brokerList" headerKey="" headerValue="---Select---" listKey="CODE" listValue="CODEDESC" cssClass="form-control border border-left-0 "  onchange="getExecutive('quotation','?brokerCode='+this.value,'executiveList');"/>
			                 		<s:hidden name="brokerName" />
		                        </div>
		                    </div>
		                    <div class="col-md-5 col-6">
		                        <label class="LabelHeading"><s:text name="customer.executive.bdm"  /></label>
		                        <div class="input-group mb-3" id="executiveList">
		                            <div class="input-group-prepend">
		                                <span class="input-group-text border border-right-0">
		                                	<i class="fas fa-user-check"></i>
		                               	</span>
		                            </div>
		                            <s:select name="executive" list="executiveList" headerKey="" headerValue="---Select---" listKey="CODE" listValue="CODEDESC" cssClass="form-control border border-left-0"  disabled="#disable" value='executive==null?getText("customer.executiveDefault"):executive'/>
		                        </div>
		                    </div>
		                </div>
		            </div>
		         </div>
			</div>
		</s:if>
		<s:else>
			<s:hidden name="brokerCode" />
			<s:hidden name="executive" />
		</s:else>
	    <div class="container PolicyInformationPage mt-5">
	        <div class="Card_Parent PolicyInformation">
	            <div class="card card-1">
	                <h3>Policy Holder Information</h3>
	                <hr>
	                <div class="row">
	                    <div class="col-md-5 col-6">
	                        <label class="LabelHeading">Title</label>
	                        <div class="input-group mb-3">
	                            <div class="input-group-prepend">
	                                <span class="input-group-text border border-right-0">
	                                	<i class="fas fa-heading"></i>
	                               	</span>
	                            </div>
	                            <s:select name="title" id="title" list="titleList" listKey="CODEDESC" listValue="CODEDESC" cssClass="form-control border border-left-0"  disabled="#disable1"/>
	                        </div>
	                    </div>
	                    <div class="col-md-5 col-6">
	                        <label class="LabelHeading">First Name</label>
	                        <div class="input-group mb-3">
	                            <div class="input-group-prepend">
	                                <span class="input-group-text border border-right-0">
	                                	<i class="fas fa-user-check"></i>
	                                </span>
	                            </div>
	                            <s:textfield name="customerName" id="customerName" cssClass="form-control border border-left-0"  maxLength="100" disabled="#disable1" />
	                        </div>
	                    </div>
	                </div>
	                <div class="row">
	                    <div class="col-md-5 col-6">
	                        <label class="LabelHeading">Last Name</label>
	                        <div class="input-group mb-3">
	                            <div class="input-group-prepend">
	                                <span class="input-group-text border border-right-0">
	                                	<i class="fas fa-user-check"></i>
	                                </span>
	                            </div>
	                            <s:textfield name="custLastName" id="custLastName" cssClass="form-control border border-left-0"   maxLength="20" disabled="#endtDisable"  />
	                        </div>
	                    </div>
	                    <div class="col-md-5 col-6">
	                        <label class="LabelHeading">Email</label>
	                        <div class="input-group mb-3">
	                            <div class="input-group-prepend">
	                                <span class="input-group-text border border-right-0">
	                                	<i class="fas fa-envelope"></i>
	                                </span>
	                            </div>
	                            <s:textfield name="email" id="email" cssClass="form-control border border-left-0"  maxLength="100" disabled="#disable1"/>
	                        </div>
	                    </div>
	                </div>
	                <div class="row">
	                    <div class="col-md-5 col-6">
	                        <label class="LabelHeading">Mobile No</label>
	                        <div class="input-group mb-3">
	                            <div class="input-group-prepend">
	                                <span class="input-group-text border border-right-0">
	                                	<i class="fas fa-phone-alt"></i>
	                                </span>
	                            </div>
	                            <s:textfield name="mobileNo" id="mobileNo" cssClass="form-control border border-left-0" maxLength="10" onkeyup="checkNumbers(this);" disabled="#disable1"/>
	                        </div>
	                    </div>
	                    <div class="col-md-5 col-6">
	                        <label class="LabelHeading">Customer Type</label>
	                        <div class="input-group mb-3">
	                            <div class="input-group-prepend">
	                                <span class="input-group-text border border-right-0">
	                                	<i class="fas fa-users"></i>
	                                </span>
	                            </div>
	                            <s:select name="customerType" id="customerType" list="#{'INDIVIDUAL':'Individual','CORPORATE':'Corporate'}" value='%{(customerType==null||"".equals(customerType))?"INDIVIDUAL":customerType}' cssClass="form-control border border-left-0"  disabled='%{(productId==null)?true:false}' />
	                        </div>
	                    </div>
	                </div>
	            </div>
	        </div>
	        <div class="Card_Parent mt-4">
	            <div class="card card-1">
	                <div class="row">
	                     <div class="col-md-6">
	                        <h3>Location Details</h3>
	                     </div>
	                     <div class="col-md-6 text-right">
	                        <a onclick="locationPopup('add','');" data-toggle="modal" data-target="#locationDetailsModal" class="btn btn-primary" data-backdrop="static">Add Location</a>
	                     </div>
	                </div>
	                <hr>
	                <div class="row">
	                     <div class="col-md-12">
			                <table class="table table-bordered table-hover" cellspacing="0" width="100%" id="locationDetailsTableId">
			                    <thead class="bluecolortable">
			                        <tr>
			                            <th>S.No</th>
			                            <th>Location Name</th>
			                            <th>Status</th>
			                            <th>Edit</th>
			                            <th>Delete</th>
			                        </tr>
			                    </thead>
			                </table>
			             </div>
	                </div>
	            </div>
	        </div>
	        <div class="Card_Parent mt-4">
	            <div class="card card-1">
	                <h3>Policy Details</h3>
	                <hr>
	                <div class="row">
	                    <div class="col-md-5 col-6">
	                    	<label class="LabelHeading">Policy Start Date</label>
	                        <div class="input-group mb-3">
	                            <div class="input-group-prepend">
	                                <span class="input-group-text border border-right-0">
	                                	<i class="fas fa-calendar-alt"></i>
	                                </span>
	                            </div>
	                            <s:textfield id="homepolicyStartDate" name="inceptionDt" cssClass="form-control border border-left-0 datePicker "  readonly="true" disabled="#disable" onchange="getAjaxModel(this.form,'?inceptionDt='+this.value,'homepolicyEndList')"/>
	                        </div>
	                    </div>
	                    <div class="col-md-5 col-6">
	                    	<label class="LabelHeading">Policy End Date</label>
	                        <div class="input-group mb-3"  id="homepolicyEndList">
	                            <div class="input-group-prepend">
	                                <span class="input-group-text border border-right-0">
	                                	<i class="fas fa-calendar-alt"></i>
	                                </span>
	                            </div>
	                            <s:select name="expiryDt" id="homeexpiryDt" list="#{}" headerKey="" headerValue="----- Select Policy Start Date -----" cssClass="form-control border border-left-0 "  disabled="#disable" theme="simple"/>
	                        </div>
	                    </div>
	                </div>
	            </div>
	        </div>
	    </div>
	    <div class="row mt-4">
	        <div class="col-md-2 col-2 col-sm-2 offset-md-4">
	            	<a style="cursor:pointer" onclick="<s:if test='("RU".equalsIgnoreCase(quoteStatus) || "RA".equalsIgnoreCase(quoteStatus) || "RR".equalsIgnoreCase(quoteStatus)) && ("RSAIssuer".equalsIgnoreCase(#session.usertype) || "admin".equalsIgnoreCase(#session.usertype))'>
	            											dkSubmit('getOCAjaxReferal.do','QE');
	            									   </s:if>
	            									   <s:else>
	            									   		dkSubmit('initReport.do','QE');
	            									   </s:else>" class="btn btn-danger btn-block">Back</a>
	        </div>
	        <div class="col-md-2 col-2 col-sm-2">
	            <a style="cursor:pointer" onclick="dkSubmit('savePackageHome.do','');" class="btn btn-primary btn-block">Proceed</a>
	        </div>
	    </div>
	    <br>
    </s:form>
    <div id="locationDetailsModal" class="modal fade" role="dialog">
    	<div class="modal-dialog modal-lg" id="openLocationDetails">
	    </div>
	</div>
    <div id="locationDetailsDelModal" class="modal fade" role="dialog">
    	<div class="modal-dialog modal-lg">
    		<div class="modal-content">
		      	<div class="modal-header">
		        	<div class="modal-title">
						<div class="row">
		       				<div class="col-md-12 col-xs-12">
					         	<h3><s:text name="Confirmation" /></h3>
					         </div>
		    			</div>
					</div>
		      	</div>
			    <div class="modal-body" >
			       <div class="row" align="center">
		       			<div class="col-md-12 col-xs-12">
			        		<s:form name="locationDetailsDelForm" id="locationDetailsDelForm" theme="simple">
			        			 <h4>Do you want to Delete <span id="locationNameDivId"></span> Location?</h4>
				        		 <s:hidden name="applicationNo" id="applicationNo"/>
					             <s:hidden name="editLocId" id="editLocId"/>
					             <s:hidden name="mode" id="mode" value="delete"/>
			        		</s:form>
			        	</div>
			        </div>
				    <div class="row mt-4">
				        <div class="col-md-2 col-2 col-sm-2 offset-md-4">
				            <a style="cursor:pointer" data-dismiss="modal" class="btn btn-danger btn-block">No</a>
				        </div>
				        <div class="col-md-2 col-2 col-sm-2">
				            <a style="cursor:pointer" onclick="manipulateDelLoc();" class="btn btn-primary btn-block">Yes</a>
				        </div>
			        </div>
			    </div>
	        </div>
	    </div>
    </div>
    <script type="text/javascript">
	    $(function() {
			try {
				$('#homepolicyStartDate').datepicker({
					todayHighlight: true,
					format: "dd/mm/yyyy",
					startDate: '-0d'
				}).on('changeDate', function(e){
				    $(this).datepicker('hide');
				});
			} catch(err) {
				console.error(err);
			}
		});
	    <s:if test='brokerCode!=null && !"".equalsIgnoreCase(brokerCode)'>
	    	editExecutive();
		</s:if>
		function editExecutive(){
			try{
	    		var bc = $("#quotation select[name=brokerCode]").val();
	    		var executive = '';
	    		<s:if test='executive!=null && !"".equalsIgnoreCase(executive)'>
	    			executive = '&executive=<s:property value="executive"/>';
	    		</s:if>
	    		getExecutive('quotation','?brokerCode='+bc+executive, 'executiveList');
			}catch(err){
				console.log(err);
			}
		}

	    function getExecutive(frm,val,id) {
	    	try{
	    		postRequest('${pageContext.request.contextPath}/'+id+'Customer.action'+val, id);
	    	}catch(err){
	    		console.log(err);
	    	}
	    }
	    
    	<s:if test='inceptionDt!=null && !"".equalsIgnoreCase(inceptionDt)'>
    		editPolicyEndDate();
    	</s:if>
    	function editPolicyEndDate(){
    		try{
        		var policyStartDate = $("#quotation input[name=inceptionDt]").val();
        		var policyEndDate = '';
        		<s:if test='expiryDt!=null && !"".equalsIgnoreCase(expiryDt)'>
        			policyEndDate = '&expiryDt=<s:property value="expiryDt"/>';
        		</s:if>
        		getAjaxModel('quotation','?inceptionDt='+policyStartDate+policyEndDate, 'homepolicyEndList');
    		}catch(err){
    			console.log(err);
    		}
    	}
		function getAjaxModel(frm,val, id) {
			try{
				postRequest('${pageContext.request.contextPath}/'+id+'Home.action'+val, id);
			}catch(err){
				console.log(err);
			}
		}
		
		function locationPopup(mode,lid){
			try{
			    var appNo = document.quotation.applicationNo.value;
				var strUrl = '${pageContext.request.contextPath}/openLocationDetailsHome.do?mode='+mode+'&editLocId='+lid+'&applicationNo='+appNo;
				var id = 'openLocationDetails';
				$.ajax({
					   url: strUrl,		   
					   error: function() {
					      $('#'+id).html('<p>An error has occurred in jquery Ajax</p>');
					   },		   
					   success: function(data) {
					      $('#'+id).html(data);
					      if(appNo!=null && appNo!=''){
					      	document.locationDetailsForm.applicationNo.value = appNo;
					      }
					   },
					   beforeSend : function(){
						   $('#loading').show();
			           },
			           complete : function(){
			        	   $('#loading').hide();
			           },
					   type: 'POST'
				});
			}catch(err){
				console.log(err);
			}
		}
		
		function manipulateLoc() {
			try{
				var strUrl = '${pageContext.request.contextPath}/manipulateLocationDetailsHome.do';
				var formId = 'locationDetailsForm';
				var id = 'openLocationDetails';
				$.ajax({
					url : strUrl,
					type : "POST",
					data : $("#" + formId).serialize(),
					error: function() {
						$('#'+id).html('<p>Your Session has been Expired</p>');
					},		   
					success: function(data) {
						$('#'+id).html(data);
						var status = document.locationDetailsForm.locationDtlsStatus.value;
						if(status!=null && status == 'true'){
							document.quotation.applicationNo.value = document.locationDetailsForm.applicationNo.value;
							$('#locationDetailsModal').modal('hide');
							$('#locationDetailsTableId').DataTable().destroy();
							var appNo = $("#quotation input[name=applicationNo]").val();
							locationDetailsTable(appNo);
						}
					},
					beforeSend : function(){
						$('#loading').show();
					},
					complete : function(){
						$('#loading').hide();
					}
				});
			}catch(err){
				console.log(err);
			}
		}
		
		function hideBtn(id){
			try{
				document.getElementById(''+id).style.display='none';
			}catch(err){
				console.log(err);
			}
		}
		
		function locationDel(locName,locId){
			try{
				document.getElementById('locationNameDivId').innerHTML = locName;
				document.locationDetailsDelForm.applicationNo.value = document.quotation.applicationNo.value;
				document.locationDetailsDelForm.editLocId.value = locId;
			}catch(err){
				console.log(err);
			}
		}
		
		function manipulateDelLoc(){
			try{
				var strUrl = '${pageContext.request.contextPath}/manipulateLocationDetailsHome.do';
				var formId = 'locationDetailsDelForm';
				$.ajax({
					url : strUrl,
					type : "POST",
					data : $("#" + formId).serialize(),
					error: function() {
						$('#'+id).html('<p>Your Session has been Expired</p>');
					},		   
					success: function(data) {
						var jo = JSON.stringify(data);
					 	var jd = $.parseJSON(jo);
						var status = jd.locationDtlsStatus+'';
						if(status!=null && status == 'true'){
							$('#locationDetailsDelModal').modal('hide');
							$('#locationDetailsTableId').DataTable().destroy();
							var appNo = $("#quotation input[name=applicationNo]").val();
							locationDetailsTable(appNo);
						}
					},
					beforeSend : function(){
						$('#loading').show();
					},
					complete : function(){
						$('#loading').hide();
					}
				});
			}catch(err){
				console.log(err);
			}
		}
		
		function dkSubmit(act,mt){
			try{
				document.quotation.action = '${pageContext.request.contextPath}/'+act;
				document.quotation.menuType.value = mt;
				document.quotation.submit();
			}catch(err){
				console.log(err);
			}
		}
		
	  function ensureForm(e) {
	        if(e.keyCode === 13) {
	            e.preventDefault();
	        }
	    }
    </script>
</body>
</html>