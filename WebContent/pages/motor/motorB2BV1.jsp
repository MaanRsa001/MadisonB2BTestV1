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
<s:set var="disable1" value="%{((endTypeId!=null && endTypeId.length() > 0)||(quoteStatus=='RA'))}"/>
<s:set var="endtDisable" value="%{endTypeId!=null && endTypeId.length() > 0}"/>
	
<s:form id="motor" name="motor" method="post" theme="simple" action="getQuoteMotor.action">
<div id="loading" style="width:100%">
   <img id="loading-image" src="${pageContext.request.contextPath}/images/madisonSymbolLogo.png"/>
</div>
	<s:hidden name="display" />
	<s:hidden name="actionType" />
	<s:hidden name="fleetNo" />
	<s:hidden name="applicationNo" id="applicationNo"/>
	<s:hidden name="quoteNo" />
	<s:hidden name="quoteStatus"/>
	<%--<s:hidden name="referralMsg"/>--%>
	<s:hidden name="endTypeId"/>
	<s:hidden name="policyNo"/>
	<s:hidden name="brokerCompany"/>
	<s:hidden name="custName"/>
	<s:hidden name="vehicleId"/>
	<s:hidden name="isVehicleEdit"/>
	<%--<s:hidden name="companyRegNo" id="companyRegNo"></s:hidden> --%>
	<s:hidden name="customerId"  id="customerId"/>
	<s:hidden name="isClaimDtl"  id="isClaimDtl"/>
	<s:hidden name="reqFrom"  id="reqFrom"/>
	<s:hidden name="policyTypeDesc"  id="policyTypeDesc"/>
	<s:hidden name="brokerCode"  id="brokerCode"/>
	<s:hidden name="executive"  id="executive"/>
	
	<s:if test="'newQuote'.equalsIgnoreCase(display)">
		<section>
		    <div class="container PolicyInformationPage PolicyInformation">
			    <div class="row">
					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
						<span style="color:red" ><s:actionerror/></span>
		        	</div>
				</div>	
		        
		        <div class="Card_Parent PolicyInformation PolicyPage">
					    <div class="card card-1">
					      <h3>Policy Holder Information</h3>
					      <s:if test='"Y".equals(#session.B2cCustYN)'>
						     <div class="col-md-12" align="right">
								<input type="button" onclick="CustomerSearch();" class="btn btn-sm btn-primary" value="Search Customer">
							 </div>
						</s:if>
					      <hr>
					      
					      <div class="row">
		                    	<div class="col-md-5">
			                    	<label class="labelHeader"><s:text name="customer.title"  /><font color="red">*</font></label>
			                    	<div class="input-group mb-3">
			                            <div class="input-group-prepend">
			                                <span class="input-group-text border border-right-0"><i class="fas fa-heading"></i></span>
			                            </div>
			                            <s:select name="title" id="title" list="titleList" listKey="Code" listValue="Description" headerKey="" headerValue="---Title---" class="form-control border"  />
			                        </div>
		                    	</div>
		                    	<div class="col-md-5">
		                    		<label class="labelHeader"><s:text name="customer.firstName"  /><font color="red">*</font></label>
		                    		<div class="input-group mb-3">
		                            <div class="input-group-prepend">
		                                <span class="input-group-text border border-right-0"><i class="fas fa-user-check"></i></span>
		                            </div>
		                            <%-- <s:if test='%{#session.LoginType!="B2C" && !"User".equalsIgnoreCase(#session.usertype)}'>
										<s:textfield name="customerName" id="customerName" class="form-control border border-left-0 empyCustDetails"  maxLength="100" disabled="#disable1" placeholder="Firstname"/>
										
										<s:if test='"add".equalsIgnoreCase(mode)'>
											<s:submit type="button" value="..." cssClass="inputButton" onclick="return customerSelectionAction(this.form)" disabled="#disable1"/>
										</s:if>
										<s:hidden name="mode"/>
									</s:if>
									<s:else> --%>
										<s:textfield name="customerName" id="customerName" class="form-control border empyCustDetails" maxLength="100" disabled="#disable1" />
									<%-- </s:else> --%>
		                        </div>
		                    	</div>
		                    </div>
		                    <div class="row">
		                    	<div class="col-md-5">
		                    		<label class="labelHeader"><s:text name="customer.lastName"/></label>
		                    		<div class="input-group mb-3">
		                            <div class="input-group-prepend">
		                                <span class="input-group-text border border-right-0"><i class="fas fa-user-check"></i></span>
		                            </div>
		                            <s:textfield name="custLastName" id="custLastName" class="form-control border empyCustDetails"  maxLength="20" disabled="#endtDisable"  />
		                        </div>
		                    	</div>
		                    <div class="col-md-5">
		                    		<label class="labelHeader"><s:text name="customer.email"  /><font color="red">*</font></label>
		                    		<div class="input-group mb-3">
		                            <div class="input-group-prepend">
		                                <span class="input-group-text border border-right-0"><i class="fas fa-envelope"></i></span>
		                            </div>
		                            <s:textfield name="email" id="email" class="form-control border empyCustDetails" maxLength="100" disabled="#disable1"/>
		                        </div>
		                    	</div>
		                    
		                    </div>
		                    <div class="row">
		                    	<div class="col-md-5">
		                    		<label class="labelHeader"><s:text name="customer.mobileNo1"  /><font color="red">*</font></label>
		                    		<div class="input-group mb-3">
		                            <div class="input-group-prepend">
		                                <span class="input-group-text border border-right-0"><i class="fas fa-phone-alt"></i></span>
		                            </div>
		                            <s:textfield name="mobileNo" id="mobileNo" class="form-control border empyCustDetails"  maxLength="10" onkeyup="checkNumbers(this);" disabled="#disable1"/>
		                        </div>
		                    	</div>
		                    	
		                    	<div class="col-md-5">
		                    		<label class="labelHeader"><s:text name="customer.customerType"  /><font color="red">*</font></label>
		                    		<div class="input-group mb-3">
		                            <div class="input-group-prepend">
		                                <span class="input-group-text border border-right-0"><i class="fas fa-users"></i></span>
		                            </div>
		                            <s:select name="customerType" id="customerType" list="#{'':'---Select---','INDIVIDUAL':'Individual','CORPORATE':'Corporate'}" class="form-control border" />
	                        	</div>
                    			</div>
		                    	
	                    	</div>
					    </div>
  					</div><br>
					<%-- <div class="Card_Parent">
					    <div class="card card-21 ">
					      <h3>Policy Details</h3>
					      <hr>
					      <div class="row">
		                    	<div class="col-md-5">
		                    		<label class="labelHeader">Policy Type<font color="red">*</font></label>
		                    		<div class="input-group mb-3">
			                            <div class="input-group-prepend">
			                                <span class="input-group-text border border-right-0"><img src="./assets/Images/insurance (1).png"></span>
			                            </div>
			                            <s:select name="policyType" id="policyType" list="#{'':'---Select---','1':'Comprehensive','2':'Third Party Fire and Theft','3':'Third Party Liability Only'}"  class="form-control border "  disabled="#disable1" cssStyle="color: #000000;"/>
			                            <s:select name="policyType" id="policyType" list="policyTypeList" listKey="POLICYTYPE_ID" listValue="POLICYTYPE_DESC_ENGLISH" headerKey="" headerValue="---Select---" class="form-control border"  disabled="#disable1"/>
			                        </div>
		                    	</div>
		                    	<div class="col-md-5">
		                    		<label class="labelHeader"><s:text name="motor.policyStartDt"/><s:text name="(DD/MM/YYYY)" /><font color="red">*</font></label>
		                    		<div class="input-group mb-3">
			                            <div class="input-group-prepend">
			                                <span class="input-group-text border border-right-0"><i class="fas fa-calendar-alt"></i></span>
			                            </div>
			                            <s:textfield name="policyStartDate" id="motorPolicyStartDate" onchange="getAjaxModel(this.form,'?policyStartDate='+this.value,'policyEndListNew')" class="form-control border datePicker"  disabled="#disable1"/>
			                        </div>
		                    	</div>
		                    </div>
	                    	<div class="row">
		                    	<div class="col-md-5">
		                    		<label class="labelHeader"><s:text name="motor.policyEndDt"/><font color="red">*</font></label>
		                    		<div class="input-group mb-3" id="policyEndListNew">
			                            <div class="input-group-prepend">
			                                <span class="input-group-text border border-right-0"><i class="fas fa-calendar-alt"></i></span>
			                            </div>
			                           <s:select name="policyEndDate" id="policyEndDate" list="policyEndList" listKey="CODE" listValue="CODEDESC" class="form-control border"  disabled="#disable1"/>
			                        </div>
		                    	</div>
		                    	<div class="col-md-5">
		                    		<label class="labelHeader"><s:text name="motor.currencyType"/><font color="red">*</font></label>
		                    		<div class="input-group mb-3">
			                            <div class="input-group-prepend">
			                                <span class="input-group-text border border-right-0"><img src="./assets/Images/insurance (1).png"></span>
			                            </div>
			                            <s:select name="currencyType" id="currencyType" list="currencyTypeList" headerKey="" headerValue="---Select---" class="form-control border" placeholder="Current Type" disabled="#disable1" cssStyle="color: #000000;"/>
			                        </div>
		                    	</div>
	                    	</div>
		                    <br>
    </div>
   
  </div> --%> <br>
  <div class="row">
	<s:if test='#session.LoginType != "B2C"'> 
	 	<s:if test='issuer != null'>
	 		<div class="col-lg-2 col-md-3 offset-md-3 offset-lg-4 mb-3">
	           <button type="button" class="btn btn-danger btn-block" onclick="getBack('homeIssuer')">Back</button>
        </div>
	 	</s:if>
	 	<s:else>
	 		<div class="col-lg-2 col-md-3 offset-md-3 offset-lg-4 mb-3">
	           <button type="button" class="btn btn-danger btn-block" onclick="getBack('home')">Back</button>
        	</div>
	 	</s:else>
	</s:if>
    <s:if test='"admin".equalsIgnoreCase(#session.usertype)'>
    	 <div class="col-lg-2 col-md-3 getQuote mb-3">
     	<s:if test='rateVehicleId!=null && !"".equals(rateVehicleId)'>
			<button type="button" class="btn btn-info btn-block" onclick="this.form.actionType.value='getCalculate';disableForm(this.form,false,'');fnsubmit('updateCovRateVehicleMotor');">Calculate</button>
		</s:if>
			<button type="button" class="btn btn-primary btn-block" onclick="this.form.actionType.value='getQuote';disableForm(this.form,false,'');fnsubmit('updateCovRateVehicleMotor');">Proceed</button>
		</div>
   </s:if>
   <s:else>
        <div class="col-lg-2 col-md-3 getQuote mb-3">
    		<span id="proceedTPSpan" style='${"2"==premiumType?"":"display:none;"}'><button type="button" class="btn btn-block" onclick="this.form.actionType.value='getQuote';disableForm(this.form,false,'');fnsubmit('addVehicleMotor')">Submit</button></span>
    		<span id="proceedSpan" style='${"2"!=premiumType?"":"display:none;"}'><button type="button" class="btn btn-block" onclick="this.form.actionType.value='getVehicle';fnsubmitVeh();disableForm(this.form,false,'');">Proceed</button></span>
    	</div>
   </s:else>
		                    
 </div>
  
  </div>	        
	  
		</section>
		
		<br />
		<div>
				<br class="clear" />
		        
				<div id="premiumCard" class="panel panel-danger" <s:if test='!("2".equals(premiumType) && !hasActionErrors() && #multiVehicleDtls!=null && #multiVehicleDtls.size()>0)'> style="display:none;" </s:if> >
					<div class="panel-heading">
						<s:set var="tpPremiumInfo" value="%{thirdPartyPremiumInfo}"/>
						<s:property value='#tpPremiumInfo.get("X_DATA_NAME")'/> Premium : <s:property value='#tpPremiumInfo.get("DATA_VALUE")'/> 
						<span class="pullRight">
							<s:submit type="button" cssClass="btn btn-sm btn-warning" value="Buy Now" onclick="this.form.actionType.value='getQuote';disableForm(this.form,false,'');"/>
						</span> 
						<br class="clear" />
					</div>
				</div>
			</div>
			
	</s:if>
	<s:elseif test="'vehDetails'.equalsIgnoreCase(display)">
		<div class="container modal_VehicleDetails">
		    <div class="modal fade" id="myModal">
		      <div class="modal-dialog  modal-lg">
		        <div class="modal-content">
		          	<div id="vehicleEditAjax"></div>
		        </div>
		      </div>
		    </div>
		</div>
		<div class="container modal_DriverDetails">
		  <div class="modal fade" id="myModal2">
		      <div class="modal-dialog  modal-lg">
		        <div class="modal-content">
		    		<div id="driverEditAjax"></div>
		    	</div>
	   		  </div>
	      </div>
		</div>
	
		<div class="container vehInfo PolicyPage">
			<div class="row">
				<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
					<span style="color:red;font-family: 'Nunito Sans', sans-serif;" ><s:actionerror/></span>
	        	</div>
			</div>
			<div class="Card_Parent PolicyInformation">
	            <div class="card card-5">
	                
	                <div class="row">
	                	<div class="col-md-12">
	                		<h3>Customer Information</h3><hr>
	                		
			                <div class="row">
			                    <div class="col-md-5 col-6">
			                        <label class="LabelHeading"><s:text name="motor.customerName"  /> </label>
			                    </div>
			                    <div class="col-md-5 col-6">
			                        <label class="labelValues"><s:property value="title"/>.<s:property value="customerName"/>&nbsp;<s:property value="custLastName"/></label>
			                    </div>
			                </div>
			                <div class="row">
			                    <div class="col-md-5 col-6">
			                        <label class="LabelHeading"><s:text name="motor.email" /></label>
			                    </div>
			                    <div class="col-md-5 col-6">
			                        <label class="labelValues"><s:property value="email"/></label>
			                    </div>
			                </div>
			                <div class="row">
			                    <div class="col-md-5 col-6">
			                        <label class="LabelHeading"><s:text name="Mobile No" /></label>
			                    </div>
			                    <div class="col-md-5 col-6">
			                        <label class="labelValues"><s:property value="mobileNo"/></label>
			                    </div>
			                </div>
	                		
	                	</div>
	                </div>
	                
	            </div>
	        </div>
	        
	    </div>
	    <br>
	    <div class="container vehInfo PolicyPage PolicyInformation PolicyInformationPage">
		    <div class="Card_Parent">
			    <div class="card card-21 ">
			      <h3>Policy Details</h3>
			      <hr>
			      <div class="row">
	                   	<div class="col-md-5">
	                   		<label class="labelHeader">Policy Type<font color="red">*</font></label>
	                   		<div class="input-group mb-3">
	                            <div class="input-group-prepend">
	                                <span class="input-group-text border border-right-0"><img src="./assets/Images/insurance (1).png"></span>
	                            </div>
	                            <%-- <s:select name="policyType" id="policyType" list="#{'':'---Select---','1':'Comprehensive','2':'Third Party Fire and Theft','3':'Third Party Liability Only'}"  class="form-control border "  disabled="#disable1" cssStyle="color: #000000;"/> --%>
	                            <s:select name="policyType" id="policyType" list="policyTypeList" listKey="Code" listValue="Description" headerKey="" headerValue="---Select---" class="form-control border"  disabled="#disable1"/>
	                        </div>
	                   	</div>
	                   	<div class="col-md-5">
	                   		<label class="labelHeader"><s:text name="motor.policyStartDt"/><s:text name="(DD/MM/YYYY)" /><font color="red">*</font></label>
	                   		<div class="input-group mb-3">
	                            <div class="input-group-prepend">
	                                <span class="input-group-text border border-right-0"><i class="fas fa-calendar-alt"></i></span>
	                            </div>
	                            <s:textfield name="policyStartDate" id="motorPolicyStartDate" onchange="getAjaxModel(this.form,'?policyStartDate='+this.value,'policyEndListNew')" class="form-control border datePicker"  disabled="#disable1" readonly="true"/>
	                        </div>
	                   	</div>
	                   </div>
	                  	<div class="row">
	                   	<div class="col-md-5">
	                   		<label class="labelHeader"><s:text name="motor.policyEndDt"/><font color="red">*</font></label>
	                   		<div class="input-group mb-3" id="policyEndListNew">
	                            <div class="input-group-prepend">
	                                <span class="input-group-text border border-right-0"><i class="fas fa-calendar-alt"></i></span>
	                            </div>
	                           <s:select name="policyEndDate" id="policyEndDate" list="policyEndList" listKey="Code" listValue="Description" class="form-control border"  disabled="#disable1"/>
	                        </div>
	                   	</div>
	                   	<div class="col-md-5">
	                   		<label class="labelHeader"><s:text name="motor.currencyType"/><font color="red">*</font></label>
	                   		<div class="input-group mb-3">
	                            <div class="input-group-prepend">
	                                <span class="input-group-text border border-right-0"><img src="./assets/Images/insurance (1).png"></span>
	                            </div>
	                            <s:select name="currencyType" id="currencyType" list="currencyTypeList" headerKey="" headerValue="---Select---" class="form-control border" placeholder="Current Type" disabled="#disable1" cssStyle="color: #000000;"/>
	                        </div>
	                   	</div>
	                  	</div>
	                   <br>
			  		</div>
				</div>
		  </div><br>
		  <div class="container">
			<div class="row">
				<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
					<div class="vehDtl">
					  	<div class="Card_Parent ">
				            <div class="card card-5">
				            <div class="panel panel-heading">
					            <div class="VehicleTableheading">
							       <div class="row">
							          <div class="col-lg-6 col-md-6">
							            <h3>Vehicle Details</h3>
							          </div>
							          <div class="col-lg-2 offset-md-3 offset-lg-4 col-md-3">
							            <a class="btn btn-primary btn-block bordernone" data-refresh="true" data-backdrop="static" data-keyboard="false" data-toggle="modal" data-target="#myModal" onclick="return fnVehicleEdit(this.form,'add','<s:property value="#view.VehicleId" />','vehicleEditAjax');">
							              Add Vehicle
							            </a>
							          </div>
							       </div>
							    </div><hr>
				            </div>
				            <div class="panel-body" style="padding-top: 0px;">
				            <div class="row">
				            	<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
				            		<s:set name="multiVehicleDtls" value="%{multiVehicleDetails}"/>
									<%-- <s:if test='applicationNo !=null && applicationNo.length() > 0 && #multiVehicleDtls.size()>0'> --%>
										<table class="table table-bordered table-hover" cellspacing="0" width="100%" id="tadaTable">
											<thead class="bluecolortable" >
												<tr>
										          <th>S.No</th>
										          <th>Vehicle Usage</th>
										          <th>Make</th>
										          <th>Model</th>
										          <th>Type Of Body</th>
										          <th>Vehicle Value</th>
										          <s:if test='"admin".equalsIgnoreCase(#session.usertype)'>
										          	<th>Action</th>
										          </s:if>
										          <s:else>
										          	<s:if test='"INDIVIDUAL".equalsIgnoreCase(customerType)'>
										          	<th>Edit Driver</th>
										          	</s:if> <!-- Commented on 14/01/2023 on request from mapande mail --%> -->
										          	<th>Edit Vehicle</th>
										         	<th>Delete</th>
										          </s:else>
										        </tr>
										    </thead>
										    <tbody class="rowevencolor">
											    <s:iterator value="#multiVehicleDtls" var="view" status="status">
													<tr valign="middle">
														<td align="center"><s:property value="#status.count" /></td>
														<td align="center"><s:property value="#view.VehicleUseageId" /></td>
														<td align="center"><s:property value="#view.MakeId" /></td>					
														<td align="center"><s:property value="#view.ModelId" default=" " /> </td>
														<td align="center"><s:property value="#view.BodyId" /></td>
														<td align="center"> <s:property value="#view.VehicleValue"/> </td>
														<%-- <td align="center"> <s:property value="getText('{0,number,#,##0.00}',{#view.VehicleValue})"/> </td> --%>
														
														<s:if test='"admin".equalsIgnoreCase(#session.usertype)'>
														<td align="center">
															<s:submit type="button" name="rateEditButton" id="rateEditButton" onclick="return fnVehiclesubmit(this.form,'rate','%{VehicleId}');" cssClass="btn btn-sm btn-warning" value="Modify Rate"/>
														</td>
														</s:if>
														<s:else>
										              		<%--  <a href="#" onclick="return fnUpdateDriver(this.form,'edit','<s:property value="#view.VEHICLE_ID" />','<s:property value="#view.VEHICLE_TYPE" />','driverEditAjax');" data-toggle="modal" data-target="#myModal2"><i class="fas fa-user-plus" 
											                  data-type="primary" data-placement="top" title="Add Driver Details"></i></a>
											                 <a href="#" onclick="return fnVehicleEdit(this.form,'edit','<s:property value="#view.VEHICLE_ID" />','vehicleEditAjax');" data-toggle="modal" data-target="#myModal"><i class="fas fa-pencil-alt" 
											                  data-type="primary" data-placement="top" title="Edit Vehicle Details"></i></a>
											                  <a href="#" data-toggle="modal" id="vehicleDeleteButton" data-target="#vehicleDeleteModal%{#view.VEHICLE_ID}" onclick="return false;"><i class="fas fa-trash-alt" 
											                  data-type="primary" data-placement="top" title="Delete Vehicle Details"></i></a>
											              --%>
											              	<%--<s:if test='"INDIVIDUAL".equalsIgnoreCase(customerType)'>
											              	  <td align="center">
											                  	<button type="button" class="btn btn-sm btn-primary" data-refresh="true" data-backdrop="static" data-keyboard="false" data-toggle="modal" data-target="#myModal2" onclick="return fnUpdateDriver(this.form,'edit','<s:property value="#view.VEHICLE_ID" />','<s:property value="#view.VEHICLE_TYPE" />','<s:property value="applicationNo"/>','driverEditAjax');"><i class="fas fa-user-plus" 
											                  	data-type="primary" data-placement="top" title="Add Driver Details"></i></button>
											                  </td>
											                 </s:if>--%><%--Commented on 14/01/2023 on request from mapande mail --%>
											                 <td align="center">
											                 <a href="#" onclick="return fnUpdateDriver(this.form,'edit','<s:property value="#view.VehicleId" />','<s:property value="#view.ApplicationNo" />','driverEditAjax');" data-toggle="modal" data-target="#myModal2"><i class="fas fa-user-plus" 
											                  data-type="primary" data-placement="top" title="Add Driver Details"></i></a>
											                  </td>
											                 
											                  <td align="center">
											                  	<button type="button" class="btn btn-sm btn-warning" data-toggle="modal" data-target="#myModal" onclick="return fnVehicleEdit(this.form,'edit','<s:property value="#view.VehicleId" />','vehicleEditAjax');"><i class="fas fa-pencil-alt" 
											                  	data-type="primary" data-placement="top" title="Edit Vehicle Details"></i></button>
										                  	  </td>
										                  	  <td align="center">
										                  	  	<s:submit type="button" name="vehicleDeleteButton" id="vehicleDeleteButton" class="btn btn-sm btn-danger" data-toggle="modal" data-target="#vehicleDeleteModal%{#view.VehicleId}" onclick="return false;"><i class="fas fa-trash-alt" 
											                  	data-type="primary" data-placement="top" title="Delete Vehicle Details"></i></s:submit>
											                  </td>
												            <div id="vehicleDeleteModal${view.VehicleId}" class="modal fade" role="dialog">
																<div class="modal-dialog">
																	<div class="modal-content" >
																		<div class="modal-header">
																			<h4>Confirmation</h4>
																		</div>
																		<div class="modal-body">
																			<h5> You are about to delete the vehicle added </h5>
																		</div>
																		<div class="modal-footer">
																			<button type="button" class="btn btn-sm btn-danger" data-dismiss="modal">Cancel</button>
																			<button type="button" class="btn btn-sm btn-warning" data-dismiss="modal" onclick="return fnVehiclesubmit(this.form,'delete','${view.VehicleId}');">Delete</button>
																		</div>
																	</div>
																</div>
															</div>
														</s:else>
											        </tr>
										        </s:iterator>
										    </tbody>
										    </table>
										    <s:hidden name="deleteVehicleId" id="deleteVehicleId"/>
											<s:hidden name="rateVehicleId" id="rateVehicleId"/>
											<s:hidden name="vehicleType" id="vehicleType"/>
									   <%--  </s:if> --%>
									    <%-- <s:else>
									    	<table class="table table-bordered table-hover" cellspacing="0" width="100%" id="tadaTable">
												<thead class="bluecolortable" >
													<tr>
											          <th>S.No</th>
											          <th>Vehicle Usage</th>
											          <th>Make</th>
											          <th>Model</th>
											          <th>Type Of Body</th>
											          <th>Vehicle Value</th>
											          <th>Action</th>
											        </tr>
											    </thead>
											    <tbody class="rowevencolor">
												    <tr>
										        		<td align="center" colspan="7">No Vehicle Details Found, Please Click Add Vehicle.</td>
										        	</tr>
												</tbody>
									    	</table>
										</s:else> --%><br><br>
									     <div class="row">
									     <s:if test='#multiVehicleDtls.size()>0'>
									     	<div class="col-lg-2 col-md-3 offset-md-3 offset-lg-4 mb-3">
										    	<button type="button" class="btn btn-danger btn-block" onclick="getBack('newQuote')">Back</button>
										    </div> 
										    <div class="col-lg-2 col-md-3 getQuote mb-3">
												<button type="button" class="btn btn-block" onclick="this.form.actionType.value='getQuotation';fnsubmit('quotationMotor.action');disableForm(this.form,false,'');">Get Quote</button>
										    </div> 
									     </s:if>
									     <s:else>
									     	 <div class="col-lg-2 col-md-3 offset-md-3 offset-lg-5 mb-3">
										    	<button type="button" class="btn btn-danger btn-block" onclick="getBack('newQuote')">Back</button>
										    </div> 
									     </s:else>
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
		<s:hidden name="typeBodyNameList[0]" id="typeBodyName"/>
		<s:hidden name="typeBodyIdList[0]" id="typeBody"/>
		<s:hidden name="vehicleUsageNameList[0]" id="vehicleUsageName"/>
		<s:hidden name="vehicleUsageIdList[0]" id="vehicleUsage"/>
		<s:hidden name="seatingCapacityList[0]" id="seatingCapacityList"/>
	</s:elseif>
	<s:elseif test="'policyInfo'.equalsIgnoreCase(display)">
        <s:set var="multiVehicleDtls" value="%{multiVehicleDetails}"/>
        <s:set var="totPremium" value="0"/>
        <div class="row">
			<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
				<span style="color:red;font-family:Lato-Regular;"><s:actionerror/></span>
			</div>
		</div>
		<div class="row">
			<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
				<div class="panel panel-default">
					<div class="panel-heading">
						<div class="row">
							<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
								<div class="btn-group" role="group" aria-label="...">
									<button type="button" title="Quote Information" class="btnGroupMargin btn btn-lg btn-warning"><i class="fa fa-user"></i></button>
								  	<button type="button" title="Premium Comparison" class="btnGroupMargin btn btn-lg btn-warning"><i class="fa fa-eye"></i></button>
								  	<button type="button" title="Personal Information" class="btnGroupMargin btn btn-lg btn-warning"><i class="fa fa-users"></i></button>
								  	<button type="button" title="Policy Information" class="btnGroupMargin btn btn-lg btn-primary active" disabled="disabled"><i class="fa fa-file-text-o"></i></button>
								</div>
							</div>
							<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6 text-success">
								<span class="textHeading"><s:text name="label.policyInfo" /> </span> 
							</div>
						</div>	
					</div>		
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
				<div class="panel panel-primary">
        			<div class="panel-heading txtB">
        				<s:text name="motor.policyInfo" />
        			</div>
        			<div class="panel-body">
        				<div class="row">
        					<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
        						<span class="text"> <s:text name="motor.quoteNo"  /> </span> &nbsp; : &nbsp; <s:property value="quoteNo"/> 
							</div>
							<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
        						<span class="text"> <s:text name="motor.quoteDate" /> </span> &nbsp; : &nbsp; <s:property value="quoteDate"/>
							</div>
							<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
        						<span class="text"> <s:text name="motor.product" /> </span> &nbsp; : &nbsp; <s:property value="product"/> 
							</div>
							<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
        						<span class="text"> <s:text name="motor.customerName"  /> </span> &nbsp; : &nbsp; <s:property value="title"/>.<s:property value="customerName"/>&nbsp;<s:property value="custLastName"/>
							</div>
							<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
								<span class="text"> <s:text name="motor.email" /> </span> &nbsp; : &nbsp; <s:property value="email"/>
							</div>
							<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
								<span class="text"> <s:text name="motor.policyStartDt" /> </span> &nbsp; : &nbsp; <s:property value="policyStartDate"/>
							</div>
							<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
								<span class="text"> <s:text name="motor.policyEndDt" /> </span> &nbsp; : &nbsp; <s:property value="policyEndDatePeriod"/>
							</div>
						</div>
						<div class="row">
						 	<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
				        		<font color="red" size="5px">Currency&nbsp;:&nbsp;<s:property value="currencyType"/></font>
				        	</div>
				        </div>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
				<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
					<div class="panel panel-primary">
	        			<div class="panel-heading txtB">
							<s:text name="label.quoteDetail" />
	        			</div>
	        			<div class="panel-body">
	        				<div class="row">
	        					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
									<table width="100%" class="footable">
										<s:set name="groupId" value=""/>
										<s:set var="preAmt" value="0.0" scope="page"/>
										<s:iterator value="premiumInfo" var="prInfo" status="stat">
										<s:if test="%{#groupId != #prInfo.GROUP_ID}">
											<s:set name="groupId" value="%{#prInfo.GROUP_ID}"/>
											<thead>
											<tr>	     
										        <th colspan="5" ><s:property  value="%{#prInfo.GROUP_DESC_ENGLISH}"/></th>
											</tr>
											</thead>
										</s:if>
										<tbody>
										<tr>       
											<td align="center" width="10%">
												<s:hidden name="coverId[%{#stat.count-1}]" value="%{#prInfo.POLICYTYPE_COVERID}"/>
											</td>
											<td align="left" width="20%">
												<s:property  value="%{#prInfo.OPCOVER_DESC_ENGLISH}"/>
											</td>
											<td align="left" width="25%">
												<s:property value="getText('{0,number,#,##0.00}',{#prInfo.SUM_INSURED})"/>
												<s:hidden name="sI[%{#stat.count-1}]"  cssClass="inputBox" value="%{#prInfo.SUM_INSURED}" cssStyle="text-align:right;"/>
											</td>
											<td align="left" width="25%">
												<s:textfield name="baseRate[%{#stat.count-1}]" cssClass="inputBox "  value="%{#prInfo.RATE}" size="11" cssStyle="text-align:right; width:100%;" maxLength="16" onkeyup="checkDecimals(this);" readonly="true"/>
											</td>
											<td align="right" width="20%"> 
												<s:property value="getText('{0,number,#,##0.00}',{#prInfo.PREMIUM})"/>
												<s:set var="preAmt" value="%{@java.lang.Double@parseDouble(#attr.preAmt)+@java.lang.Double@parseDouble(#prInfo.PREMIUM)}" scope="page"/>
											</td>
										</tr>								
										</tbody>
										</s:iterator>
										<%--<s:if test="@java.lang.Double@parseDouble(miniPre) >  @java.lang.Double@parseDouble(#attr.preAmt)" >
										<s:set var="preAmt" value="%{@java.lang.Double@parseDouble(miniPre)}" scope="page"/>
										</s:if>
										--%><tbody>
										<tr>
											<td></td>
											<td></td>									
											<td></td>
											<td>
												<font style="float:left;"><s:text name="motor.Premium"/></font>
												<b style="float:right;">[<s:property value="currencyType"/>]</b>
											</td>
											<td width="100%" align="right">
												<%-- &nbsp;&nbsp;&nbsp;
												<s:textfield name="premium" id="premium" cssClass="inputBox" value="%{#attr.preAmt}" onkeyup="checkDecimals(this);" maxlength="11" cssStyle="text-align:right;" readonly="true"/>
												--%>
												<s:textfield name="premium" id="premium" cssClass="inputBox "  value="%{getText('{0,number,0.00}',{#attr.preAmt})}" onkeyup="checkDecimals(this);" maxlength="11" cssStyle="text-align:right;" readonly="true"/>
											</td>
										</tr>
										<s:if test='#session.user1 == "admin" || quoteStatus == "RA"'>
										<tr>
											<td></td>
											<td></td>									
											<td></td>
											<td>
											<font style="float:left;"><s:text name="motor.loadingOrDiscountPremium"/></font>
											<b style="float:right;">[<s:property value="currencyType"/>]</b>
											</td>
											<td align="right">
												<s:select list="#{'+':'+','-':'-'}" name="sign" onchange="getTotalPremium(this.form)" disabled="%{quoteStatus=='RA' && #session.user1 != 'admin'?'true':'false'}" cssClass="inputBox" cssStyle="width:30%;"/>&nbsp;&nbsp;&nbsp;
					                            <s:textfield name="loadOrDiscPremium" id="loadOrDiscPremium" cssClass="inputBox "  disabled="%{quoteStatus=='RA' && #session.user1 != 'admin'?'true':'false'}" onkeyup="checkDecimals4_2(this);getTotalPremium(this.form);" maxlength="11" cssStyle="text-align:right;width:60%;"/>
					                            <s:if test='"+".equalsIgnoreCase(sign)'>
					                            	<s:set var="preAmt" value="%{@java.lang.Double@parseDouble(#attr.preAmt)+@java.lang.Double@parseDouble(loadOrDiscPremium)}" scope="page"/>
					                            </s:if>
					                            <s:else>
					                            	<s:set var="preAmt" value="%{@java.lang.Double@parseDouble(#attr.preAmt)-@java.lang.Double@parseDouble(loadOrDiscPremium)}" scope="page"/>
					                            </s:else>
											</td>
										</tr>
										</s:if>
										<tr>
											<td></td>
											<td></td>									
											<td></td>
											<td>
												<font style="float:left;"><s:text name="motor.policyFee"/></font>
												<b style="float:right;">[<s:property value="currencyType"/>]</b>
											</td>
											<td width="100%" align="right">
												<s:textfield name="policyFee" id="policyFee" cssClass="inputBox "  onkeyup="checkDecimals(this);" maxlength="11" cssStyle="text-align:right;" readonly="true"/>
											</td>
										</tr>
										<tr>
											<td></td>
											<td></td>									
											<td></td>
											<td>
												<font style="float:left;"><s:text name="motor.totalPremiumPayable" /></font>
												<b style="float:right;">[<s:property value="currencyType"/>]</b>
											</td>
												
											<td width="100%" align="right">
												<%-- 
												<s:textfield name="totalPremium" id="totalPremium" cssClass="inputBox" value="%{@java.lang.Double@parseDouble(#attr.preAmt)+@java.lang.Double@parseDouble(policyFee)}" onkeyup="checkDecimals(this);" maxlength="11" cssStyle="text-align:right;" readonly="true"/>
												--%>
												<s:set var="totPremium" value="%{getText('{0,number,0.00}',{@java.lang.Double@parseDouble(#attr.preAmt)+@java.lang.Double@parseDouble(policyFee)})}"/>
												<s:textfield name="totalPremium" id="totalPremium" cssClass="inputBox "  value="%{#totPremium}" onkeyup="checkDecimals(this);" maxlength="11" cssStyle="text-align:right;" readonly="true"/>
											</td>
										</tr>
										<s:if test='#session.user1 == "admin" || (!"".equals(adminRemarks)&&(adminRemarks!=null)&& #session.user1 != "admin")'>
										<tr>
											<td></td>
											<td></td>									
											<td></td>
											<td align="right"><s:text name="motor.specialCondition"/></td>
											<td>
												<s:textarea name="adminRemarks" onkeyup="textLimit(this,'470')"  cols="50" rows="3" readonly="%{quoteStatus=='RA' && #session.user1 != 'admin'?'true':'false'}"/>
											</td>
										</tr>
										</s:if>
										<s:if test='#session.user1 == "admin"'>
										<tr>
											<td></td>
											<td></td>									
											<td></td>
											<td align="right"><s:text name="motor.referralStatus"/></td>
											<td><s:radio list="#{'Y':'Accept','N':'Reject','A':'Pending'}" name="adminRefStatus" value="%{adminRefStatus==null?'A':adminRefStatus}" /></td>
										</tr>
										<br /></s:if>
										</tbody>	
									</table>
		       						<br class="clear"/>
		       					</div>
		       				</div>
		       			</div>
		       		</div>
				</div>
			</div>
			<s:if test='showReferralYN == "Y" && #session.user1 != "admin"'>
        		<div class="row">
	        		<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
	        			<div class="panel panel-primary">
		        			<div class="panel-heading txtB">
								<s:text name="motor.referalInfo" />
		        			</div>
		        			<div class="panel-body">
		        				<div class="row">
		        					<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
		        						<span class="text"> <s:text name="motor.policyStartDt"/> </span> &nbsp; : &nbsp; <s:property value="policyStartDate"/>
		        					</div>
		        					<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">								        						
		        						<span class="text"> <s:text name="motor.policyEndDt"/> </span> &nbsp; : &nbsp; <s:property value="policyEndDate"/>
		        					</div>
		        				</div>
		        				<div class="row">
		        					<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
		        						<div class="text"><s:text name="motor.referralYN"/></div>
     											<div class="tbox">
     												<s:radio list="#{'Y':'Yes','N':'No'}" name="referralYN" id="referralYN"  onclick="disablePolicyOption(this.value);"/>
     											</div>					        						
		        					</div>
		        					<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
		        						<div class="text"><s:text name="motor.comments"/></div>
     											<div class="tbox">
     												<s:textarea name="referralComments" id="referralComments" cssClass="inputBoxA "  cssStyle="width: 100%;" onkeyup="textLimit(this,'200')"/>
     											</div>
		        					</div>
		        				</div>
		        			</div>
		        		</div>
	        		</div>
	        	</div>
	        	<s:hidden name="showReferralYN"/>
        	</s:if>
		<div class="row">
			<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
				<s:include value="/pages/customer/customerDetailsMotor.jsp"></s:include>
			</div>
		</div>
		<%--<div class="row">
       		<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
       			<div class="panel panel-primary">
        			<div class="panel-heading txtB">
						<s:text name="Attach Document" />
        			</div>
        			<div class="panel-body">
        				<div class="row">
        					<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
								<div class="text"><s:text name="Documents" /><font color="red">*</font></div>
								<div class="tbox">
									<s:submit type="button" value="Attach Documents" onclick="return uploadDocuments('%{vehicleIdList[0]}');" cssClass="btn btn-sm btn-danger "  />
								</div>
							</div>
        				</div>
       				</div>
   				</div>
			</div>
		</div>--%>
		
		<div class="row">
			<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
				<s:if test='#session.user1 != "admin"'>
					<div class="panel panel-primary">
	        			<div class="panel-heading txtB">
	        				<s:text name="motor.additionalVehicleDetails" />
	        				<%--<button type="button" class="btn btn-sm btn-primary" data-toggle="modal" data-refresh="true" href="${pageContext.request.contextPath}/getHelpInfoMotor.action?helpType=ADDITIONAL_VEHICLE_DETAILS" data-target="#modaladdVehicleDetails"> <img src="${pageContext.request.contextPath}/images/Help.png" width="20" height="20"/> </button> --%>
							<div class="modal fade" data-refresh="true" id="modaladdVehicleDetails" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
								<div class="modal-dialog modal-lg">
									<div class="modal-content">
										<div class="modal-header">
											<h4 class="modal-title"> <s:label key="motor.additionalVehicleDetails"/> </h4>
										</div>
										<div class="modal-body">
											<div class="te"></div>
										</div>
									</div>
								</div>
							</div>
	        			</div>
	        			<div class="panel-body">
	        				<ul class="nav nav-tabs responsive">
		        				<s:iterator value="#multiVehicleDtls" var="var" status="status">
			        				<li id="tab${status.count}" class='${status.index=="0"?"active":""}'>
								  		<a href="#tabBody${status.count}" data-toggle="pill">
								  			Vehicle&nbsp;${status.count}
										</a>
									</li>
								</s:iterator>
							</ul>
							<br class="clear"/>
							<div class="tab-content responsive">
								<s:iterator value="#multiVehicleDtls" var="var" status="status">
									<div id="tabBody${status.count}" class='tab-pane fade ${status.index=="0"?"in active":""}'>
										
       									<div class="row">
       										
					       					<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
       											<div class="text"><s:text name="Documents" /></div>
       											<div class="tbox">
       												<s:submit type="button" value="Attach Documents" onclick="return uploadDocuments('%{vehicleIdList[#status.index]}');" cssClass="btn btn-sm btn-danger " />
       											</div>
       										</div>
       									</div>
       									<s:hidden name="noClaimBonusIdList[%{#status.index}]"/>
       									<s:hidden name="vehicleIdList[%{#status.index}]"/>
									</div>
								</s:iterator>
							</div>
			        	</div>
					</div>
				</s:if>
	       	</div>
		</div>
		
        	<s:if test=' #session.user1 != "admin"'>
				<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
					<s:include value="/pages/payment/paymentInfo.jsp"/>
				</div>
	        </s:if>
			<div class="row">
				<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" align="center">
					<s:if test='#session.user1 != "admin"'>
						<s:if test='quoteStatus=="RA" || (endTypeId!=null && !"".equalsIgnoreCase(endTypeId)) || "2".equals(premiumType)'>
							<input type="button" name="Submit1" class="btn btn-sm btn-danger" value="Back" onclick="getBack('vehDetail');" />
							&nbsp;&nbsp;&nbsp;
						</s:if>
						<s:else>
							<!--  <input type="button" name="Submit1" class="btn btn-sm btn-danger" value="Back" onclick="getBack('showSummarry');" />-->
							<input type="button" name="Submit1" class="btn btn-sm btn-danger" value="Back" onclick="getBack('vehDetail');" />
							&nbsp;&nbsp;&nbsp;
						</s:else>
						<%--<input type="button" name="Submit2" class="btn" value="Save" onclick="this.form.actionType.value='getSave';this.form.submit();" />
						                  &nbsp;--%>
					</s:if>
					<s:else>
						<%--
						<s:submit type="button" name="close"  key="Back" cssClass="btn btn-sm btn-danger" onclick="getBack('editCovRate')"/>
						--%>
						<input type="button" name="Submit1" class="btn btn-sm btn-danger" value="Back" onclick="getBack('vehDetail');" />
						&nbsp;&nbsp;&nbsp;
					</s:else>
						<input type="button" name="Submit3" class="btn btn-sm btn-success" value="Proceed" onclick="this.form.actionType.value='getQuote';disableForm(this.form,false,'');funGeneratePolicy()"/>
					
	                <s:hidden name="currencyType"/>
				</div>
			</div>
	       	<%--</s:if>	--%>
    </s:elseif>
	
</s:form>




<script  type="text/javascript">

$(function() {
    $("body").delegate(".datePicker", "focusin", function(){
        $(this).datepicker();
    });
});

$(function() {
	try {
	var dt = new Date();
	dt.setFullYear(new Date().getFullYear()-18);
	
	$('#custdob').datepicker({
		todayHighlight: true,
       	format: "dd/mm/yyyy",
	  	viewMode: "years",
	  	endDate: dt
	}).on('changeDate', function(e){
           $(this).datepicker('hide');
       });
	
	
	$('.datePicker').datepicker({
		todayHighlight: true,
		format: "dd/mm/yyyy"
	}).on('changeDate', function(e){
	    $(this).datepicker('hide');			    
	});
} catch(err) {console.error(err);}
});

try {
	// New Block
	function funGeneratePolicy(){
		document.motor.action = "${pageContext.request.contextPath}/getGeratePolicyMotor.action";
		document.motor.submit();
	}
	function fnsubmitVeh(action) {
		//var policyType = document.getElementById("policyType");
		//var selectedText = policyType.options[policyType.selectedIndex].text;
		//document.getElementById("policyTypeDesc").value=selectedText;
		document.motor.action = "${pageContext.request.contextPath}/vehicleDtlMotor.action";
		document.motor.submit();
	}
	
	    // $(document).ready(function () {
	    //   $('#dataTable').DataTable();
	    // });

	    $("#switch1").change(function () {
	      if ($(this).prop("checked") == true) {
	        console.log('1')
	        alert("cust on")
	      } else {
	        console.log('2')
	        alert("cust off")

	      }
	    });
        
	    function TapActiveFunction(val) {
	      console.log(val)
	      if (val == 'Tap-1') {
	        $(".taps2").removeClass("active")
	        $(".taps1").addClass("active")
	      }
	      if (val == 'Tap-2') {
	        $(".taps2").addClass("active")
	        $(".taps1").removeClass("active")
	      }

	    }
	
	
	
	
	// New Block End
	
	function fnNewsubmit(action) {
		document.motor.action = "${pageContext.request.contextPath}/getQuoteNewMotor.action";
		document.motor.submit();
	}
	function fnsubmit(action) {
		document.motor.action = "${pageContext.request.contextPath}/" + action;
		document.motor.submit();
	}
	appPath = "${pageContext.request.contextPath}/";
	function getAjaxModel(frm,val, id)
	{
			postRequest('${pageContext.request.contextPath}/'+id+'Motor.action'+val, id);
	}
	function getDeductibleAjax(frm) {
		//var ded=$("#seatingCapacity").val();
		//alert('getDeductibleAjax '+ded);
		var val = "?seatingCapacity=" + $("#seatingCapacityList").val()
					+ "&vehicleUsage=" + $("#vehicleUsage").val()
					+ "&typeBody=" + $("#typeBody").val();
		getAjaxModel(frm,val,'deductibleAjax');
	}
	function getNCBAjax(frm) {
		var val = "?vehicleUsage=" + $("#vehicleUsage").val();
		getAjaxModel(frm,val,'ncbAjax');
	}
	function vehicleTypeDetailsAjax(frm) {
		//var vehty=$("#seatingCapacity").val();
		//alert('vehicleTypeDetailsAjax '+vehty);
		$("#modalErrorSpan").html("");
		var val = "?make=" + $("#make").val()
					+ "&model=" + $("#model").val()
					+ "&typeBody=" + $("#typeBody").val()
					+ "&vehicleUsage=" + $("#vehicleUsage").val()
					+ "&seatingCapacity=" + $("#seatingCapacityList").val()
					+ "&deleteVehicleId=" + $("#rvehicleId").val()
					+ "&applicationNo=<s:property value='applicationNo'/>";
		getAjaxModel(frm,val,'vehicleTypeDetailsAjax');
		return false;
	}
	function setModalVehicleDetails(modalSelectId,modalTypeBody,modalVehicleUsage,modalTypeBodyName,modalVehicleUsageName) {
		//alert("Hi-- "+modalSelectId+","+modalTypeBody+","+modalVehicleUsage+","+modalTypeBodyName+","+modalVehicleUsageName);
		$("#modalSelectId").val(modalSelectId);
		$("#modalTypeBody").val(modalTypeBody);
		$("#modalVehicleUsage").val(modalVehicleUsage);
		$("#modalTypeBodyName").val(modalTypeBodyName);
		$("#modalVehicleUsageName").val(modalVehicleUsageName);
	}
	
	function checkBodyClick (frm){
		/*var gender = document.querySelector('input[name = "selects"]:checked').value;
		alert(gender);
		var a=$("#modalTypeBody").val();
		var b=$("#modalVehicleUsage").val();
		alert(a +","+b);
		var c = document.getElementById('modalTypeBody').value;
		var d = document.getElementById('modalVehicleUsage').value;
		alert(c +","+d);*/
		var seatingCapacity = $("#tSeatingCapacity_" + $("#modalTypeBody").val()+ $("#modalVehicleUsage").val()).val();
		//alert("checkBodyClick = "+seatingCapacity);
		if(seatingCapacity!="" && parseInt(seatingCapacity) != NaN) {
			if($("#modalSelectId").val()!="") {
				//alert("yes");
				$("#typeBody").val($("#modalTypeBody").val());
				$("#vehicleUsage").val($("#modalVehicleUsage").val());
				$("#seatingCapacityList").val($("#tSeatingCapacity_" + $("#modalTypeBody").val()+ $("#modalVehicleUsage").val()).val());
				getDeductibleAjax(frm);
			}else{
				//alert("No");
			}
		}
	}
	function setVehicleTypeDetails(frm) {
		
		var req=document.getElementById('reqFrom').value;
		//alert(req);
		//if(req=='edit'){
			var modalTypeBody="";
			var modalVehicleUsage="";
			try{
				//alert();
				//var select = document.querySelector('input[name = "selects"]:checked').value;
				var select = $("input[type='radio'][name='selects']:checked").val();
				//alert("select "+select);
				if(select!=null) {
					//alert("select in "+select);
					var str_array = select.split('~');
			
					for(var i = 0; i < str_array.length; i++) {
					   // Trim the excess whitespace.
					   str_array[i] = str_array[i].replace(/^\s*/, "").replace(/\s*$/, "");
					   // Add additional code here, such as:
					   //alert(str_array[i]);
					   if(i==0){
						   modalTypeBody=str_array[i];
					   }else{
						   modalVehicleUsage=str_array[i];
					   }
					}
				}
			}catch(err) {console.error(err);}
	
			$("#modalTypeBody").val(modalTypeBody);
			$("#modalVehicleUsage").val(modalVehicleUsage);
		//}
		
		var error = "";
		var seatingCapacity=document.getElementById('tSeatingCapacity_'+$("#modalTypeBody").val()+$("#modalVehicleUsage").val()).value;
		//alert('seatingCapacity = '+seatingCapacity);
		//var seatingCapacity = $("#tSeatingCapacity_" + $("#modalTypeBody").val()+ $("#modalVehicleUsage").val()).val();
		//alert(seatingCapacity);
		
		if(seatingCapacity!="" && parseInt(seatingCapacity) != NaN) {
			//alert('inside seat');
			//alert($("#modalTypeBody").val());
			
				//$("#typeBody").val($("#modalTypeBody").val());
				//$("#vehicleUsage").val($("#modalVehicleUsage").val());
				//$("#seatingCapacity").val($("#tSeatingCapacity_" + $("#modalTypeBody").val()+ $("#modalVehicleUsage").val()).val());
				//$("#typeBody").val(modalTypeBody);
				//$("#vehicleUsage").val(modalVehicleUsage);
				//$("#seatingCapacity").val(seatingCapacity);
				document.getElementById('typeBody').value=modalTypeBody;
				document.getElementById('vehicleUsage').value=modalVehicleUsage;
				document.getElementById('seatingCapacityList').value=seatingCapacity;
				//getDeductibleAjax(frm);
		}
		
		/*if($("#make").val()=="") {
			error += "Please select Make <br>";
		} if($("#model").val()==""){
			error += "Please select Model <br>";
		} if($("#sumInsured").val()==""){
			error += "Please Enter Vehicle Value <br>";
		} if($("#mfgYr").val()==""){
			error += "Please Select Manufacturer Year <br>";
		} if($("#deductible").val()==""){
			error += "Please Select Excess Limit <br>";
		} if($("#regNoList").val()==""){
			error += "Please Enter Registration Number <br>";
		} if($("#chassisNoList").val()==""){
			error += "Please Enter Chassis Number <br>";
		} if($("#engineNoList").val()==""){
			error += "Please Enter Engine Number <br>";
		} if($("#leasedYNIdListN").val()=="Y" && $("#bankOfFinanceIdList").val()==""){
			error += "Please Select Bank of Finance <br>";
		}
		 if($("#modalSelectId").val()=="") {
			error += "Please select Vehicle Type <br>";
		} else if(seatingCapacity=="") {
			error += "Please Enter Seating Capacity <br>";
		} else if(parseInt(seatingCapacity) == NaN) {
			error += "Please Enter valid Seating Capacity <br>";
		}*/
		$("#modalErrorSpan").html(error);
		//alert(error);
		if(error=="") {
			/*var bodyId =$("#modalTypeBody").val();
			if(bodyId==9 || bodyId==27){
				$( '#seatingCapacityBlock' ).attr('style','display:none;');
			}else{
				$( '#seatingCapacityBlock' ).attr('style','display:block;');
			}*/
			$("#typeBody").val($("#modalTypeBody").val());
			$("#vehicleUsage").val($("#modalVehicleUsage").val());
			$("#seatingCapacityList").val($("#tSeatingCapacity_" + $("#modalTypeBody").val()+ $("#modalVehicleUsage").val()).val());
			$("#typeBodyDiv").html($("#modalTypeBodyName").val());
			$("#vehicleUsageDiv").html($("#modalVehicleUsageName").val());
			$("#seatingCapacityDiv").html($("#seatingCapacityList").val());
			$("#typeBodyName").val($("#modalTypeBodyName").val());
			$("#vehicleUsageName").val($("#modalVehicleUsageName").val());
			$('#vehicleTypeDetails').modal('toggle');
			//var seatCap=document.getElementById('seatingCapacityList').value;
			//alert('error null '+seatCap);
			getDeductibleAjax(frm);
			getNCBAjax(frm);
		}
		//alert($("#typeBody").val());
	}
	function removeVehicleTypeDetails() {
		$("#typeBody").val("");
		$("#vehicleUsage").val("");
		$("#seatingCapacityList").val("");
		$("#typeBodyDiv").html("");
		$("#vehicleUsageDiv").html("");
		$("#seatingCapacityDiv").html("");
		$("#typeBodyName").val("");
		$("#vehicleUsageName").val("");
		$("#deductible").find('option').remove();
		$("#noClaimBonus").find('option').remove().end().append('<option value="">---Select---</option>').val("");
	}

	function toggleAddVehicle(value) {
		if(value=="show") {
			$( '.addVehicle' ).attr('style','');
			$( '#toggleAddVehicleDiv' ).attr('style','display:none;');
		} else {
			$( '.addVehicle' ).attr('style','display:none;');
		}
	}

	function toggleTpLiablity(value) {
		if("Y"==value) {
			document.getElementById('tpLiablityAmount').disabled = false;
		} else {
			document.getElementById('tpLiablityAmount').value = "";
			document.getElementById('tpLiablityAmount').disabled = true;
		}
	}

	function getComparisionDetails(frm) {
		if( validateTpLiablity()==""  ) {
			$('#errorMessageDiv').html('');
			var val = '?applicationNo=<s:property value="applicationNo"/>'
				+ '&quoteNo=<s:property value="quoteNo"/>'
				+ '&currencyType=<s:property value="currencyType"/>'
				+ '&tpLiablityYN=' + ($('#tpLiablityYNN').is(':checked')==true?"N":"Y")
				+ '&tpLiablityAmount=' + $('#tpLiablityAmount').val();
			getAjaxModel(frm,val,'comparisionDetailsAjax');
		}
	}

	function validateTpLiablity() {
		var errorMessage = "";
		if( $('#tpLiablityYNY').is(':checked')==true) {
			var val = parseFloat($('#tpLiablityAmount').val());
			if(isNaN(val)) {
				errorMessage = 'Please enter valid Combined Third Party Liability Amount';
			} else {
				var minLimit = 90000;
				var maxLimit = 500000;
				if('<s:property value="currencyType"/>'=='USD') {
					minLimit = minLimit/11.37;
					maxLimit = maxLimit/11.37;
				}
				if(val<minLimit) {
					errorMessage = 'Please enter Combined Third Party Liability Amount above ' + numeral(Number(minLimit)).format('0,0.00');
				} else if(val>maxLimit) {
					errorMessage = 'Please enter Combined Third Party Liability Amount below ' + numeral(Number(maxLimit)).format('0,0.00');
				}
			}
		}
		$('#errorMessageDiv').html(errorMessage);
		$('html, body').animate(
			{'scrollTop' : $("#errorMessageDiv").offset().top},
			'fast'
		);
		return errorMessage;
	}

	function disablePolicyOption(value) {
		if(value=="Y") {
			if('Y'=='<s:property value="showReferralYN"/>') {
				document.getElementById('referralComments').readOnly=false;
			}
			document.getElementById('policyGeneration').style.display='none';
		} else {   
		 	document.getElementById('policyGeneration').style.display='block';
		 	if('Y'=='<s:property value="showReferralYN"/>') {
			 	document.getElementById('referralComments').value='';
			 	document.getElementById('referralComments').readOnly=true;
		 	}
		}
    }
    function toggleClaimDetails(value)
    {
     		 if(value=="Y")
			 {
			 	document.getElementById('claimAmount').readOnly=false;
			 	document.getElementById('noClaimBonus').value='';
			 	document.getElementById('noClaimBonus').disabled=true;
			 	
			 }   
			 else
			 {  //alert('1');
			 	document.getElementById('claimAmount').value='';
			 	document.getElementById('claimAmount').readOnly=true;
			 	document.getElementById('noClaimBonus').disabled=false;
			 } 
    }
    function toggleNCB(value)
    {
   		 if(value=="" || value=="0")
		 {
		 //document.getElementById('prevPolicyNoList').value='';
		// document.getElementById('prevExpiryDateList').value='';
		 //document.getElementById('prevInsCompanyIdList').value='';
		 
		 //$(".prevInfo").hide();
		 
		 // document.getElementById('claimYNN').checked=true;
		  //document.getElementById('claimYNN').disabled=false;
		  //document.getElementById('claimYNY').disabled=false;
		 
		 }   
		 else
		 {  
		  //$(".prevInfo").show();
		  
		  //document.getElementById('claimYNN').checked=true;
		 // document.getElementById('claimYNN').disabled=true;
		  //document.getElementById('claimYNY').disabled=true;
		  //alert('2');
		  if( $('#claimYNN').is(':checked')==true) {
			  document.getElementById('claimAmount').value='';
			  document.getElementById('claimAmount').readOnly=true;
		  }
		  
		 } 
    }
   /* function toggleLeasedYN(value, id) {
    	if(value=="Y") {
			document.getElementById('bankOfFinanceIdList['+id+"]").disabled=false;
		}   
		else {  
			document.getElementById('bankOfFinanceIdList['+id+"]").value='';
			document.getElementById('bankOfFinanceIdList['+id+"]").disabled=true;
		}
    }*/
     function toggleLeasedYN(value, id) {
    	if(value=="Y") {
			document.getElementById('bankOfFinanceIdList').disabled=false;
		}   
		else {  
			document.getElementById('bankOfFinanceIdList').value='';
			document.getElementById('bankOfFinanceIdList').disabled=true;
		}
    }
     function toggleClaimsYN(value, id) {
    	if(value=="Y") {
			document.getElementById('noOfClaims').disabled=false;
		}   
		else {  
			document.getElementById('noOfClaims').value='0';
			document.getElementById('noOfClaims').disabled=true;
		}
    }
	function uploadDocuments(vehicleId) {
		var URL ='${pageContext.request.contextPath}/documentUploadDoUpload.action?applicationNo=<s:property value="applicationNo"/>&quoteNo=<s:property value="quoteNo"/>&deleteVehicleId='+vehicleId;
		return popUp(URL,'700','500');
	}
	function electricalPopup(vehicleId) {
		var URL ='${pageContext.request.contextPath}/electricalPopupMotor.action?applicationNo=<s:property value="applicationNo"/>';
		return popUp(URL,'700','500');
	}
	 
	function nelectricalPopup(vehicleId) {
		var URL ='${pageContext.request.contextPath}/nelectricalPopupMotor.action?applicationNo=<s:property value="applicationNo"/>';
		return popUp(URL,'700','500');
	}
		
    function getBack(page) {
		if(page=='showSummarry') {
			document.motor.action ='${pageContext.request.contextPath}/showSummarryMotor.action';
		} else if(page=='newQuote') {
				document.motor.action ='${pageContext.request.contextPath}/editQuoteNewMotor.action';
		}else if(page=='vehDetail'){
			document.motor.action ='${pageContext.request.contextPath}/getBackVehicleMotor.action';
			//$("#myModal2").modal();
		}
		else if(page=='editCovRate'){
			document.motor.action ='${pageContext.request.contextPath}/editCovRateMotor.action';
		}else if(page=='home'){
			if('admin'=='<s:property value="#session.user1"/>'){
					document.motor.action ='${pageContext.request.contextPath}/getOCAjaxReferal.action';
			}else if('b2c'=='<s:property value="#session.b2c"/>')
				document.motor.action ='${pageContext.request.contextPath}/login/ProductSelection.jsp';
			else{
				if('RA'=='<s:property value="quoteStatus"/>')
					document.motor.action ='${pageContext.request.contextPath}/initReport.action?menuType=RA&loginId=<s:property value="loginId"/>';
				else if('RU'=='<s:property value="quoteStatus"/>')
					document.motor.action ='${pageContext.request.contextPath}/initReport.action?menuType=RU&loginId=<s:property value="loginId"/>';
				else if(('getSave'=='<s:property value="actionType"/>' || 'QS'=='<s:property value="quoteStatus"/>'))
					document.motor.action ='${pageContext.request.contextPath}/initReport.action?menuType=QS&loginId=<s:property value="loginId"/>';
				else
					if('edit'=='<s:property value="mode"/>')
						document.motor.action ='${pageContext.request.contextPath}/editQuoteCustDataCustomer.action?menuType=<s:property value="quoteStatus"/>&loginId=<s:property value="loginId"/>';
					else{
						if(''=='<s:property value="quoteStatus"/>' || 'null'=='<s:property value="quoteStatus"/>' || null=='<s:property value="quoteStatus"/>')
							document.motor.action ='${pageContext.request.contextPath}/initReport.action?menuType=QE&loginId=<s:property value="loginId"/>';
						else
							document.motor.action ='${pageContext.request.contextPath}/initReport.action?menuType=<s:property value="quoteStatus"/>&loginId=<s:property value="loginId"/>';
					}
			}
		}else if(page=='customer'){
			if('admin'=='<s:property value="#session.user1"/>'){
					document.motor.action ='${pageContext.request.contextPath}/getOCAjaxReferal.action';
			}else{
				if('ET'=='<s:property value="quoteStatus"/>')
					document.motor.action ='${pageContext.request.contextPath}/initReport.action?menuType=ET&custName=<s:property value="fullName"/>';
				else
					document.motor.action ='${pageContext.request.contextPath}/editCustomer.action';
			}
		}else if(page=='quote'){
			if('admin'=='<s:property value="#session.user1"/>'){
     			if('RA'=='<s:property value="quoteStatus"/>'){
					document.motor.action ='${pageContext.request.contextPath}/getOCAjaxReferal.action';
				}else
					document.motor.action ='${pageContext.request.contextPath}/initTravel.action';
			}else{
    			if('RA'=='<s:property value="quoteStatus"/>')
					document.motor.action ='${pageContext.request.contextPath}/initReport.action?menuType=RA&loginId=<s:property value="loginId"/>';
				else
					document.motor.action ='${pageContext.request.contextPath}/initTravel.action';
			}
		}else if(page=='adminHome'){
				document.motor.action ='${pageContext.request.contextPath}/getOCAjaxReferal.action';
		}else if(page=='homeIssuer'){
			
			if('edit'=='<s:property value="mode"/>')
				//document.motor.action ='${pageContext.request.contextPath}/editQuoteCustDataMotor.action?menuType=<s:property value="quoteStatus"/>&loginId=<s:property value="loginId"/>';
				document.motor.action ='${pageContext.request.contextPath}/issuerCustListCustomer.action';
			else{
				if(''=='<s:property value="quoteStatus"/>' || 'null'=='<s:property value="quoteStatus"/>' || null=='<s:property value="quoteStatus"/>')
					document.motor.action ='${pageContext.request.contextPath}/initReport.action?menuType=QE&loginId=<s:property value="loginId"/>';
				else
					document.motor.action ='${pageContext.request.contextPath}/initReport.action?menuType=<s:property value="quoteStatus"/>&loginId=<s:property value="loginId"/>';
			}
		}
		document.motor.submit();
	}
	
	function fnVehiclesubmit(frm, type, vehicleId) {
		//alert(frm +" "+type + " " + vehicleId);
		if("delete"==type) {
			document.getElementById('deleteVehicleId').value = vehicleId;
			document.motor.action = "${pageContext.request.contextPath}/deleteAddVehicleNewMotor";
			disableForm(frm,false,'');
			document.motor.submit();
		}
		else if("edit"==type) {
			document.getElementById('deleteVehicleId').value = vehicleId;
			document.motor.action = "${pageContext.request.contextPath}/editAddVehicleMotor";
			disableForm(frm,false,'');
			document.motor.submit();
		} else if("rate"==type) {
			document.getElementById('rateVehicleId').value = vehicleId;
			document.motor.action = "${pageContext.request.contextPath}/editCovRateVehicleMotor";
			disableForm(frm,false,'');
			document.motor.submit();
		}
		return false;
	}
	
	function fnVehicleEdit(frm, type, vehicleId, id) {
		var polStDate=document.getElementById("motorPolicyStartDate").value;
		var polEndDate=document.getElementById("policyEndDate").value;
		var polType=document.getElementById("policyType").value;
		var currencyType=document.getElementById("currencyType").value;
		var val = '?productId=<s:property value="productId"/>'
			+ '&branchCode=<s:property value="branchCode"/>'
			+ '&deleteVehicleId='+vehicleId
			+ '&reqFrom='+type
			+ '&applicationNo=<s:property value="applicationNo"/>'
			+ '&policyStartDate='+polStDate
			+ '&policyEndDate='+polEndDate
			+ '&policyType='+polType
			+ '&currencyType='+currencyType;
		postRequest('${pageContext.request.contextPath}/'+id+'Motor.action'+val, id);
	}
	
	function changeBtn(val) {
		if('true'!='<s:property value="#disable1"/>') {
			if (val=='2') {
				document.getElementById("tplBtn").className = 'btnGroupType btn btn-lg btn-success active';
				document.getElementById("comprehensiveBtn").className = 'btnGroupType btn btn-lg btn-warning';
				document.getElementById("bothBtn").className = 'btnGroupType btn btn-lg btn-warning';
	
				//document.getElementById("tp").style.display = 'block';
				//document.getElementById("tpOthers").style.display = 'none'; 
				document.getElementById("proceedTPSpan").style.display = '';
				document.getElementById("proceedSpan").style.display = 'none';
				
			} else if (val=='1') {
				document.getElementById("tplBtn").className = 'btnGroupType btn btn-lg btn-warning';
				document.getElementById("comprehensiveBtn").className = 'btnGroupType btn btn-lg btn-success active';
				document.getElementById("bothBtn").className = 'btnGroupType btn btn-lg btn-warning';
	
				document.getElementById("premiumCard").style.display = 'none';
				document.getElementById("proceedTPSpan").style.display = 'none';
				document.getElementById("proceedSpan").style.display = '';
			} else if (val=='0') {
				document.getElementById("tplBtn").className = 'btnGroupType btn btn-lg btn-warning';
				document.getElementById("comprehensiveBtn").className = 'btnGroupType btn btn-lg btn-warning';
				document.getElementById("bothBtn").className = 'btnGroupType btn btn-lg btn-success active';
	
				//document.getElementById("tpOthers").style.display = 'block';
				//document.getElementById("tp").style.display = 'none';
	
				document.getElementById("premiumCard").style.display = 'none';
				document.getElementById("proceedTPSpan").style.display = 'none';
				document.getElementById("proceedSpan").style.display = '';
			}
			document.getElementById("premiumType").value = val;
		}
		return false;
	}
	
	/*function showPremium() {
		document.getElementById("premiumCard").style.display = 'block';
		document.getElementById("submitBtn").style.display = 'none';
	}*/
	
	function showBuyNow() {
		if (document.getElementById("buyNow").checked == true) {
			document.getElementById("tpBuyInner").style.display = 'block';
		} else {
			document.getElementById("tpBuyInner").style.display = 'none';
		}
	}
	
	} catch(err) {console.error(err);}
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
	function reloadCaptcha(){
	    $("#captcha").attr("src", "${pageContext.request.contextPath}/simpleCaptcha.jpg");
	}
	function editCoverRate(frm, vehicleId) {
		document.motor.vehicleId.value = vehicleId;
		document.motor.action = "${pageContext.request.contextPath}/editCovRateMotor";
		disableForm(frm,false,'');
		document.motor.submit();
	}	
function funSubmitOtp(page,loginType) {
	if(page=='motor' && loginType=='registered') {
		document.motor.action = '${pageContext.request.contextPath}/loginRegDtlMotor.action';
	}else if(page=='motor' && loginType=='new'){
		document.motor.action = '${pageContext.request.contextPath}/loginNewDtlMotor.action';
	}
	document.motor.submit();
}
function funBackQuote() {
	document.motor.action = '${pageContext.request.contextPath}/showSummarryMotor.action';
	document.motor.submit();
}

function getExecutive(val, id) {
	postRequest('${pageContext.request.contextPath}/'+id+'Customer.action'+val, id);
}
	
function emptyCustInfo() {
	var s="poBox&mobileNo&email&title&customerName&coreAppCode&address2&address1&city"; 
	var stringToArray = new Array;
	stringToArray = s.split("&");
	for ( var int = 0; int < stringToArray.length; int++) {		
		var obj=stringToArray[int]
		document.getElementById(""+obj).value="";
	}
}
function userCustomerSelection(frm){
	var agencyCode='';	
	if(frm.agencyCode){			
		agencyCode=frm.agencyCode.value;
		alert(agencyCode);		
	}
	alert(frm.agencyCode);	
	var URL ='${pageContext.request.contextPath}/customerSelectionCustomer.action?agencyCode='+agencyCode;
	alert(URL);
	//return popUp(URL,'779','537');
	return "";
}

function customerSelectionAction(frm) {	
	var brokerCode='';	
	if(frm.brokerCode){			
			brokerCode=frm.brokerCode.value;		
	}
	var URL ='${pageContext.request.contextPath}/customerSelectionCustomer.action?brokerCode='+brokerCode;
	return popUp(URL,'779','537');
}
function CustomerSearch() {
	var URL ='${pageContext.request.contextPath}/custSearchCustomer.action';
	return popUp(URL,'700','500');
}	
function coreCustomerSearch() {
	var URL ='${pageContext.request.contextPath}/coreCustSearchCustomer.action';
	return popUp(URL,'700','500');
}

function toggleCompanyRegistration(val) {
	if(val=="CORPORATE") {
		document.getElementById('companyRegNo').disabled = false;
	}
	else {
		document.getElementById('companyRegNo').value = "";
		document.getElementById('companyRegNo').disabled = true;
	}
}
function  empyCustDetails() {
	$('.empyCustDetails').val('');
}


<s:if test="'vehDetails'.equalsIgnoreCase(display)">
//toggleClaimDetails('<s:property value="claimYNIdList[0]"/>');
try {
	if('<s:property value="make"/>'!='' || '<s:property value="applicationNo"/>'=='') {
		toggleAddVehicle("show");
	} else {
		toggleAddVehicle("hide");
	}
} catch(err) {
	console.error(err);
}
</s:if>
<s:elseif test='%{"policyInfo".equalsIgnoreCase(display) && #session.user1 != "admin"}'>
disablePolicyOption('<s:property value="referralYN"/>');
</s:elseif>
<s:if test='endTypeId!=null && !"".equalsIgnoreCase(endTypeId)'>
enableForm(document.motor,false,'<s:property value="%{fields}"/>');
</s:if>

$(function() {
		try {
			$('#prevExpiryDateList').datepicker({
				todayHighlight: true,
				format: "dd/mm/yyyy"
			}).on('changeDate', function(e){
			    $(this).datepicker('hide');			    
			});
		} catch(err) {console.error(err);}
		
	});
	//<s:if test='"vehDetails".equalsIgnoreCase(display) && #session.user1 != "admin"'>
		//toggleNCB('<s:property value="noClaimBonusIdList[0]"/>');
		//toggleLeasedYN('<s:property value="leasedYNIdList[0]"/>','');
	//</s:if>
	
	/*$(function(){
	    $( "input[type=checkbox]" ).on( "click", function(){
	        if($(this).is(':checked')){
	            alert("on");
	        	document.motor.isClaimDtl.value='Y';
	   		 }
	        else{
	        	alert("off");
	        	document.motor.isClaimDtl.value='N';
			}
	    });
	});*/
	
	function DriverClaimDetails(val){
		if (document.getElementById('switch1').checked) 
		  {
		      document.motor.isClaimDtl.value='Y';
		  } else {
			  document.motor.isClaimDtl.value='N';
		  }
	}
	
	function fnUpdateDriver(frm, type, vehicleId,appNo, id) {
		//document.getElementId('isClaimDtl').checked = true;
			var val = '?productId=<s:property value="productId"/>'
				+ '&branchCode=<s:property value="branchCode"/>'
				+ '&deleteVehicleId='+vehicleId
				+ '&applicationNo='+appNo;
				//+ '&applicationNo=<s:property value="applicationNo"/>';
			postRequest('${pageContext.request.contextPath}/'+id+'Motor.action'+val, id);
		}
	function fnSaveDriver(action,vehicleId){
		document.motor.deleteVehicleId.value=vehicleId;
		if (document.getElementById('switch1').checked) 
		  {
		      document.motor.isClaimDtl.value='Y';
		  } else {
			  document.motor.isClaimDtl.value='N';
		  }
		document.motor.action = "${pageContext.request.contextPath}/" + action;
		document.motor.submit();
	}
	function funCollapse(type){
		if(type=='more'){
			$( '#less' ).attr('style','display:none;');
			$( '#more' ).attr('style','display:block;');
		}
		else if (type=='less'){
			$( '#more' ).attr('style','display:none;');
			$( '#less' ).attr('style','display:block;');
		}
	}
	
</script>
</body>
</html>