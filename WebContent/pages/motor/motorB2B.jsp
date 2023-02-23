<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ page import="java.util.List,java.util.ArrayList,java.util.Map,java.util.HashMap,java.util.Collections"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page import="nl.captcha.Captcha"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
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
	.nav-pills>li.active>a,.nav-pills>li.active>a:focus,.nav-pills>li.active>a:hover {
		color: #ffffff;
		background-color: #ff6f00;
	}
	.nav-justified>li>a {    
	    background-color: #ffb300;
	    color: #000000; 
	}
	.textSize {
		font-size: 12px;
	}
	/*.row {
		margin-bottom: 10px;
	}*/
	.text {
		font-size: 14px;
		font-weight: bold;
	}
	
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
	
	#tadaTable_paginate .paginate_button {
    display: none;
}
.dataTables_info {
    display: none;
}
.dataTables_length {
    display: none;
}
.dataTables_filter{
    display: none;
}
.adminClass .form-control{
 display:inline;
}
.PolicyPage .QuotationDetails .list-group-item-primary {
    background-color: #261e6a;
	color: white;
	font-weight: normal;
}
.buttonCls {
  background-color: #DBA832;
  border: none;
  color: white;
  padding: 6px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 16px;
  margin: 4px 2px;
  cursor: pointer;
  border-radius: 6px;
}
.buttonClsF {
  background-color: #261e6a;
  border: none;
  color: white;
  padding: 6px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 16px;
  margin: 4px 2px;
  cursor: pointer;
  border-radius: 6px;
}
.PolicyPage .QuotationDetails .list-group-item-primary {
    border-radius: 6px;
}
	</style>
</head>
<body>
<s:set var="disable1" value="%{((endTypeId!=null && endTypeId.length() > 0)||(quoteStatus=='RA'))}"/>
<s:set var="endtDisable" value="%{endTypeId!=null && endTypeId.length() > 0}"/>
<div id="loading" style="width:100%">
   <img id="loading-image" src="${pageContext.request.contextPath}/images/madisonSymbolLogo.png"/>
</div>
<div class="row">
	<div class='${("User"==userType||"B2C"==session.LoginType)?"col-xs-12 col-sm-12 col-md-12 col-lg-12":"col-md-12"}'>
	<s:if test="'newQuote'.equalsIgnoreCase(display)">
		<s:form id="motor" name="motor" method="post" theme="simple" action="getQuoteMotor.action">
		<%--<div class="row">
			<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
				<div class="panel panel-primary">
					<div class="panel-body"  style="padding: 0px;">
						<img alt="Car Banner" src="<%=request.getContextPath()%>/images/car-quote-header.jpg" style="width: 100%; height: auto;">
					</div>				
				</div>
			</div>
		</div> --%>
		<%-- <div class="row">
			<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
				<span style="color:red;"><s:actionerror/></span>
        	</div>
		</div>	 --%>	
		<%-- <div class="row">
			<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
				<div class="panel panel-default">
					<div class="panel-heading">
						<div class="row">
							<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
								<div class="btn-group" role="group" aria-label="...">
								  <button type="button" title="Customer Information" class="btnGroupMargin btn btn-lg btn-success active"><i class="fa fa-user"></i></button>
								  <button type="button" title="Premium Comparison" class="btnGroupMargin btn btn-lg btn-primary" disabled="disabled"><i class="fa fa-eye"></i></button>
								  <button type="button" title="Policy Information" class="btnGroupMargin btn btn-lg btn-primary" disabled="disabled"><i class="fa fa-users"></i></button>
								  <button type="button" title="Policy Information" class="btnGroupMargin btn btn-lg btn-primary" disabled="disabled"><i class="fa fa-file-text-o"></i></button>
								</div>
							</div>
							<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6 text-success">
								<span class="textHeading"><s:text name="label.quoteInfo" /> </span> 
							</div>
						</div>						
					</div>		
				</div>
			</div>
		</div> --%>
		
		<div class="container PolicyInformationPage PolicyInformation">
			    <div class="row">
					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
						<span style="color:red" ><s:actionerror/></span>
		        	</div>
				</div>	
		        
		        <div class="Card_Parent PolicyInformation">
					    <div class="card card-1">
					      <h3>Policy Holder Information</h3>
					      <hr>
					      <div class="row">
		                    	<div class="col-md-5">
			                    	<label class="labelHeader"><s:text name="customer.title"  /><font color="red">*</font></label>
			                    	<div class="input-group mb-3">
			                            <div class="input-group-prepend">
			                                <span class="input-group-text border border-right-0"><i class="fas fa-heading"></i></span>
			                            </div>
			                            <s:select name="title" id="title" list="titleList" listKey="CODEDESC" listValue="CODEDESC" headerKey="" headerValue="---Title---" class="form-control border"  />
			                        </div>
		                    	</div>
		                    	<div class="col-md-5">
		                    		<label class="labelHeader"><s:text name="customer.firstName"  /><font color="red">*</font></label>
		                    		<div class="input-group mb-3">
		                            <div class="input-group-prepend">
		                                <span class="input-group-text border border-right-0"><i class="fas fa-user-check"></i></span>
		                            </div>
										<s:textfield name="customerName" id="customerName" class="form-control border empyCustDetails" maxLength="100" disabled="#disable1" />
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
   
  </div> <br>
  <div class="row">
				<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
					<div class="vehDtl">
					  	<div class="Card_Parent ">
				            <div class="card" style="padding-right: 50px;padding-left: 50px;">
				            <div class="panel panel-heading">
							     <h3>Vehicle Details</h3><hr>
				            </div>
				            <div class="panel-body" style="padding-top: 0px;">
				            <div class="row">
				            	<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
				            		<s:set name="multiVehicleDtls" value="%{multiVehicleDetails}"/>
										<table class="table table-bordered table-hover" cellspacing="0" width="100%" id="tadaTable">
											<thead class="bluecolortable" >
												<tr>
										          <th>S.No</th>
										          <th>Vehicle Usage</th>
										          <th>Make</th>
										          <th>Model</th>
										          <th>Type Of Body</th>
										          <th>Vehicle Value</th>
										          <s:if test='"admin".equalsIgnoreCase(#session.usertype) || "Y".equalsIgnoreCase(#session.Referal)'>
										          	<th>Action</th>
										          </s:if>
										          <s:else>
										          	<th>Edit Driver</th>
										          	<th>Edit Vehicle</th>
										         	<th>Delete</th>
										          </s:else>
										        </tr>
										    </thead>
										    <tbody class="rowevencolor">
											    <s:iterator value="#multiVehicleDtls" var="view" status="status">
													<tr valign="middle">
														<td align="center"><s:property value="#status.count" /></td>
														<td align="center"><s:property value="#view.VEHICLETYPE_DESC" /></td>
														<td align="center"><s:property value="#view.MAKE_NAME" /></td>					
														<td align="center"><s:property value="#view.MODEL_NAME" default=" " /> </td>
														<td align="center"><s:property value="#view.TYPE_OF_BODY_NAME" /></td>
														<td align="center"> <s:property value="getText('{0,number,#,##0.00}',{#view.SUMINSURED_VALUE_LOCAL})"/> </td>
														
														<s:if test='"admin".equalsIgnoreCase(#session.usertype) || "Y".equalsIgnoreCase(#session.Referal)'>
														<td align="center">
															<s:submit type="button" name="rateEditButton" id="rateEditButton" onclick="return fnVehiclesubmit(this.form,'rate','%{VEHICLE_ID}');" cssClass="btn btn-sm btn-warning" value="Modify Rate"/>
														</td>
														</s:if>
														<s:else>
											              	  <td align="center">
											                  	<button type="button" class="btn btn-sm btn-primary" data-refresh="true" data-backdrop="static" data-keyboard="false" data-toggle="modal" data-target="#myModal2" onclick="return fnUpdateDriver(this.form,'edit','<s:property value="#view.VEHICLE_ID" />','<s:property value="#view.VEHICLE_TYPE" />','driverEditAjax');"><i class="fas fa-user-plus" 
											                  	data-type="primary" data-placement="top" title="Add Driver Details"></i></button>
											                  </td>
											                  <td align="center">
											                  	<button type="button" class="btn btn-sm btn-warning" data-toggle="modal" data-target="#myModal" onclick="return fnVehicleEdit(this.form,'edit','<s:property value="#view.VEHICLE_ID" />','vehicleEditAjax');"><i class="fas fa-pencil-alt" 
											                  	data-type="primary" data-placement="top" title="Edit Vehicle Details"></i></button>
										                  	  </td>
										                  	  <td align="center">
										                  	  	<s:submit type="button" name="vehicleDeleteButton" id="vehicleDeleteButton" class="btn btn-sm btn-danger" data-toggle="modal" data-target="#vehicleDeleteModal%{#view.VEHICLE_ID}" onclick="return false;"><i class="fas fa-trash-alt" 
											                  	data-type="primary" data-placement="top" title="Delete Vehicle Details"></i></s:submit>
											                  </td>
												            <div id="vehicleDeleteModal${view.VEHICLE_ID}" class="modal fade" role="dialog">
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
																			<button type="button" class="btn btn-sm btn-warning" data-dismiss="modal" onclick="return fnVehiclesubmit(this.form,'delete','${view.VEHICLE_ID}');">Delete</button>
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
									  <br>
									     
			    						</div>
			    					</div>
			    				</div>
          					</div>
          				</div>
          			</div>
          		</div>
          	</div><br>
          	<s:if test="premiumInfo.size()>0">
						<div class="row">
							<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
								<div class="vehDtl">
								  	<div class="Card_Parent ">
							            <div class="card" style="padding-right: 80px;padding-left: 80px;">
				        			<div class="panel-heading txtB">
										<h3><s:text name="motor.premiumInfo"/></h3><hr>
				        			</div>
				        			<div class="panel-body">
				        				<div class="boxcontent">
					        				<s:set name="vehDtl" value="%{vehDtls}"/>
				        					<table width="100%">
				        						<tbody>
				        							<s:iterator value="#vehDtl" var="view" status="status">
					        							<tr>
					        								<td>Vehicle Usage : <b><s:property value="#view.VEHICLETYPE_DESC" /></b></td>
					        								<td>Make : <b><s:property value="#view.MAKE_NAME" /></b></td>
					        								<td>Model : <b><s:property value="#view.MODEL_NAME" /></b></td>
					        								<td>Type Of Body : <b><s:property value="#view.TYPE_OF_BODY_NAME" /></b></td>
					        							</tr>
				        							</s:iterator>
				        						</tbody>
			        						</table>
		        						</div><br>
		        						
		        						<div class="PolicyPage premiumBorder">
				                            <ul class="list-group QuotationDetails">
				                                <li class="list-group-item list-group-item-primary">
				                                    <div class="row">
				                                        <div class="col-md-3 col-3">
				                                            <label class="LabelHeading">Description</label>
				                                        </div>
				                                        <div class="col-md-3 col-3" align="center">
				                                            <label class="LabelHeading">Sum Insured</label>
				                                        </div>
				                                        <div class="col-md-3 col-3" align="center">
				                                            <label class="LabelHeading">Rate</label>
				                                        </div>
				                                        <div class="col-md-3 col-3"  align="right">
				                                            <label class="LabelHeading">Premium</label>
				                                        </div>
				                                    </div>
				                                </li>
				                                <hr>
				                                <s:set name="groupId" value=""/>
												<s:set var="preAmt" value="0.0" scope="page"/>
				                                <s:iterator value="premiumInfo" var="prInfo" status="stat">
				                                	<s:set name="groupId" value="%{#prInfo.GROUP_ID}"/>
				                                	<s:if test='!"106".equalsIgnoreCase(#prInfo.POLICYTYPE_COVERID) && !"11".equalsIgnoreCase(#prInfo.POLICYTYPE_COVERID) && !"105".equalsIgnoreCase(#prInfo.POLICYTYPE_COVERID)'>
					                                	<li class="list-group-item" style="height: 46px;">
						                                    <div class="row">
						                                        <div class="col-md-3 col-3">
						                                        	<s:hidden name="coverId[%{#stat.count-1}]" value="%{#prInfo.POLICYTYPE_COVERID}"/>
						                                            <label class="LabelHeading"><s:property  value="%{#prInfo.GROUP_DESC_ENGLISH}"/></label>
						                                        </div>
						                                        <div class="col-md-3 col-3" align="right">
							                                        <s:if test='!"103".equalsIgnoreCase(#prInfo.POLICYTYPE_COVERID) && !"104".equalsIgnoreCase(#prInfo.POLICYTYPE_COVERID)'>
							                                        	<label><s:textfield name="basePrem[%{#stat.count-1}]" id="basePrem[%{#prInfo.POLICYTYPE_COVERID}]" cssClass="form-control" value="%{#prInfo.SUM_INSURED}" cssStyle="text-align:right;" maxLength="16" onkeyup="checkDecimals10_4(this); " onchange="myChangeFunction(this.id)" disabled='("103".equalsIgnoreCase(#prInfo.POLICYTYPE_COVERID) || "104".equalsIgnoreCase(#prInfo.POLICYTYPE_COVERID))?true:false' /></label>
							                                        </s:if>
							                                        <s:else>
									                             		<s:hidden name="basePrem[%{#stat.count-1}]"  id="basePrem[%{#prInfo.POLICYTYPE_COVERID}]" cssClass="inputBox" value="%{#prInfo.SUM_INSURED}" cssStyle="text-align:right;"/>
							                                        </s:else>
								                             		<s:hidden name="sI[%{#stat.count-1}]"  cssClass="inputBox" value="%{#prInfo.SUM_INSURED}" cssStyle="text-align:right;"/>
						                                        </div>
						                                        <div class="col-md-3 col-3" align="right">
						                                        	<s:if test='!"104".equalsIgnoreCase(#prInfo.POLICYTYPE_COVERID) '>
							                                        	<label><s:textfield name="baseRate[%{#stat.count-1}]" cssClass="form-control" value="%{#prInfo.RATE}" cssStyle="text-align:right;" maxLength="16" onkeyup="checkDecimals10_4(this);" disabled='"R".equals(#prInfo.CALC_TYPE)?"false":true'/></label>
							                                        </s:if>
							                                        <s:else>
									                             		<s:hidden name="baseRate[%{#stat.count-1}]"  cssClass="inputBox" value="%{#prInfo.RATE}" cssStyle="text-align:right;"/>
							                                        </s:else>
						                                        
						                                            
						                                        </div>
						                                        <div class="col-md-3 col-3 "  align="right">
						                                            <label class="labelValues"><s:property value="getText('{0,number,#,##0.00}',{#prInfo.PREMIUM})"/>
																		<s:set var="preAmt" value="%{@java.lang.Double@parseDouble(#attr.preAmt)+@java.lang.Double@parseDouble(#prInfo.PREMIUM)}" scope="page"/>
																	</label>
						                                        </div>
						                                    </div>
					                                	</li><hr>
				                                	</s:if>
				                                
				                                </s:iterator>
				                                
				                            </ul>
				                        </div>
		        						
		        						
		        						
				        				
				        			</div>
				        		</div>
				        		</div>
				        		</div>
							</div>
						</div>
					</s:if>
  <div class="row mt-5">
	<s:if test='#session.LoginType != "B2C"'> 
	 	<s:if test='issuer != null && !"Y".equalsIgnoreCase(#session.Referal)'>
	 		<div class="col-lg-2 col-md-3 offset-md-3 offset-lg-3 mb-3">
	           <button type="button" class="btn btn-danger btn-block" onclick="getBack('homeIssuer')">Back</button>
        </div>
	 	</s:if>
	 	<s:else>
	 		<div class="col-lg-2 col-md-3 offset-md-3 offset-lg-3 mb-3">
	           <button type="button" class="btn btn-danger btn-block" onclick="getBack('home')">Back</button>
        	</div>
	 	</s:else>
	</s:if>
    <s:if test='"admin".equalsIgnoreCase(#session.usertype) || "Y".equalsIgnoreCase(#session.Referal)'>
    	 <div class="col-lg-2 col-md-3 mb-3">
     		<s:if test='rateVehicleId!=null && !"".equals(rateVehicleId)'>
				<button type="button" class="btn btn-primary btn-block" onclick="this.form.actionType.value='getCalculate';disableForm(this.form,false,'');fnsubmit('updateCovRateVehicleMotor');">Calculate</button>
			</s:if>
		</div>
		<div class="col-lg-2 col-md-3 mb-3">
			<button type="button" class="btn btn-success btn-block" onclick="this.form.actionType.value='getQuote';disableForm(this.form,false,'');fnsubmit('updateCovRateVehicleMotor');">Proceed</button>
		</div>
   </s:if>
   <s:else>
        <div class="col-lg-2 col-md-3 getQuote mb-3">
    		<span id="proceedTPSpan" style='${"2"==premiumType?"":"display:none;"}'><button type="button" class="btn btn-block" onclick="this.form.actionType.value='getQuote';disableForm(this.form,false,'');fnsubmit('addVehicleMotor')">Submit</button></span>
    		<span id="proceedSpan" style='${"2"!=premiumType?"":"display:none;"}'><button type="button" class="btn btn-block" onclick="this.form.actionType.value='getVehicle';fnsubmitVeh();disableForm(this.form,false,'');">Proceed</button></span>
    	</div>
   </s:else>
 </div>
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
		
		
			<s:hidden name="display" />
			<s:hidden name="actionType" />
			<s:hidden name="fleetNo" />
			<s:hidden name="applicationNo" />
			<s:hidden name="quoteNo" />
			<s:hidden name="quoteStatus"/>
			<%--<s:hidden name="referralMsg"/>--%>
			<s:hidden name="endTypeId"/>
			<s:hidden name="policyNo"/>
			<s:hidden name="brokerCompany"/>
			<s:hidden name="custName"/>
			<s:hidden name="vehicleId"/>
			<s:hidden name="isVehicleEdit"/>
			<!--<s:hidden name="noClaimBonusIdList[0]" id="noClaimBonus" value=""/>
			<s:hidden name="claimYNIdList[0]" id="claimYN" value=""/>
			<s:hidden name="claimAmountList[0]" id="claimAmount" value=""/>
			--><!--<s:hidden name="customerId"/>-->
			<s:hidden name="claimAmountList[0]" id="claimAmount" value=""/>
		</s:form>
	</s:if>
	<s:elseif test="'showPrSummary'.equalsIgnoreCase(display)">
        <s:form id="motor" name="motor" method="post" theme="simple" action="insertOptionCoverMotor.action">
        <div class="row">
			<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
				<span style="color:red;"><s:actionerror/></span>
			</div>
		</div>
		<div class="row">
			<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
				<div class="panel panel-default">
					<div class="panel-heading">
						<div class="row">
							<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
								<div class="btn-group" role="group" aria-label="...">							
								  <button type="button" title="Customer Information" class="btnGroupMargin btn btn-lg btn-warning"><i class="fa fa-user"></i></button>
								  <button type="button" title="Premium Comparison" class="btnGroupMargin btn btn-lg btn-success active"><i class="fa fa-eye"></i></button>
								  <button type="button" title="Personal Information" class="btnGroupMargin btn btn-lg btn-primary" disabled="disabled"><i class="fa fa-users"></i></button>
								  <button type="button" title="Policy Information" class="btnGroupMargin btn btn-lg btn-primary" disabled="disabled"><i class="fa fa-file-text-o"></i></button>
								</div>
							</div>
							<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6 text-success">
								<span class="textHeading"><s:text name="label.quoteDetail" /> </span> 
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
        						<span class="text">	<s:text name="motor.quoteNo" /> </span> &nbsp; : &nbsp; <s:property value="quoteNo"/> 
							</div>
							<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
        						<span class="text"> <s:text name="motor.quoteDate" /> </span> &nbsp; : &nbsp; <s:property value="quoteDate"/>
							</div>
							<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
        						<span class="text"> <s:text name="motor.product" /> </span> &nbsp; : &nbsp; <s:property value="product"/> 
							</div>
							<!-- <div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
        						<span class="text"> <s:text name="motor.customerName"  /> </span> &nbsp; : &nbsp; <s:property value="title"/>.<s:property value="customerName"/>&nbsp;<s:property value="custLastName"/>
							</div>
							<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
								<span class="text"> <s:text name="motor.email" /> </span> &nbsp; : &nbsp; <s:property value="email"/>
							</div> -->
							<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
								<span class="text"> <s:text name="motor.policyStartDt" /> </span> &nbsp; : &nbsp; <s:property value="policyStartDate"/>
							</div>
							<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
								<!--<span class="text"> <s:text name="motor.policyEndDt" /> </span> &nbsp; : &nbsp; <s:property value="policyEndDatePeriod"/>-->
								<span class="text"> <s:text name="motor.policyEndDt" /> </span> &nbsp; : &nbsp; <s:property value="policyEndDate"/>
							</div>
						</div>
						<div class="row">
						 	<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
				        		<font color="red" size="5px">Currency&nbsp;:&nbsp;<s:property value="currencyType"/></font>
				        	</div>
				        </div>
						<div class="row">
        					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
        						<s:set name="multiVehicleDtls" value="%{multiVehicleDetails}"/>
								<table cellpadding="1" class="footable" cellspacing="1" border="1">
									<thead>
								        <tr>
											<th style="width:5%;"><s:label value="S.No." /></th>
											<th style="width:15%;"><s:text name="motor.vehicleUsage"/></th>
											<th style="width:20%;"><s:text name="motor.make" /></th>
											<th style="width:20%;"><s:text name="motor.model" /></th>
											<th style="width:15%;"><s:text name="motor.typeOfBody" /></th>
											<th style="width:15%;"><s:text name="motor.sumInsured" /></th>
											
										</tr>
									</thead>
									<tbody>
										<s:iterator value="#multiVehicleDtls" var="view" status="status">
											<tr>
												<td align="center"><s:property value="#status.count" /></td>
												<td align="center"><s:property value="#view.VEHICLETYPE_DESC" /></td>
												<td align="center"><s:property value="#view.MAKE_NAME" /></td>					
												<td align="center"><s:property value="#view.MODEL_NAME" default=" " /> </td>
												<td align="center"><s:property value="#view.TYPE_OF_BODY_NAME" /></td>
												<td align="right"><s:property value="getText('{0,number,#,##0.00}',{#view.SUMINSURED_VALUE_LOCAL})"/></td>
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
		<div class="row">
			<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
				<div class="panel panel-primary">
        			<div class="panel-heading">
        				<div class="row">
							<div class="col-xs-12 col-sm-12 col-md-6 col-lg-4">
								<s:text name="motor.premiumInfo" />
							</div>
							<div class="col-xs-12 col-sm-12 col-md-8 col-lg-8"><span style="color: yellow;"> <s:text name="Choose one cover to buy" /> </span></div>
						</div>
        			</div>
        			<div class="panel-body">
        				<div class="row" align="center">
        					<s:if test='referralMsgs!=null && referralMsgs.length()>0'>
        						<font color="red" size="3">	
					 				<s:text name="motor.referralMessage"/> 
					 				<%--<s:property value="referralMsg"/>--%> 
					 				<s:text name="referralMsgs"/>
				 				</font>
        					</s:if>
        				</div>
        				<div id="errorMessageDiv" align="center" style="color: red;">
        				</div>
        				<div>
       						<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
       						<span style="font-size: 12px;font-weight: normal;">
	        					 <a data-toggle="modal" data-refresh="true" href="#" data-target="#modalStdLimits" style="color: red;" >Click to view Standard Limits</a> 
	        				</span>
       						<div id="modalStdLimits" class="modal fade" role="dialog">
									<div class="modal-dialog">
										<div class="modal-content">
											<div class="modal-header">
												<button type="button" class="close" data-dismiss="modal">&times;</button>
												<h4 class="modal-title" align="center">Standard Limits</h4>
											</div>
											<div class="modal-body">
												<ul>
													<s:iterator value="conditionClausesList" var="ccVar" status="ccStatus">
														<li> <s:property value="#ccVar.CONDITION_DESC"/> </li>
													</s:iterator>
												</ul>
											</div>
										</div>
									</div>
								</div>								
        						<div class="text">
        							<s:text name="motor.doyouwanttoincreaseCombinedThirdPartyLiability"/><font color="red">*</font>
        							&nbsp;&nbsp;&nbsp;<s:radio name="tpLiablityYN" id="tpLiablityYN" list="#{'Y':'Yes','N':'No'}" onchange="toggleTpLiablity(this.value);" disabled="#disable1" value='%{"Y".equals(tpLiablityYN)?"Y":"N"}'/>        										        				
        						</div>
        						<!--  <div id="modalStdLimits" class="modal fade" role="dialog">
									<div class="modal-dialog">
										<div class="modal-content">
											<div class="modal-header">
												<button type="button" class="close" data-dismiss="modal">&times;</button>
												<h4 class="modal-title" align="center">Standard Limits</h4>
											</div>
											<div class="modal-body">
												<ul>
													<s:iterator value="conditionClausesList" var="ccVar" status="ccStatus">
														<li> <s:property value="#ccVar.CONDITIONS"/> </li>
													</s:iterator>
												</ul>
											</div>
										</div>
									</div>
								</div>  -->
        					</div>
        					<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
        						<div class="text"><s:text name="Combined Third Party Liability Amount"/><font color="red">*</font></div>
        						<div class="tbox">
        							<s:textfield name="tpLiablityAmount" id="tpLiablityAmount" cssClass="inputBox tooltipContent" cssStyle="width:75%" disabled='%{#disable1||!"Y".equals(tpLiablityYN)?"true":"false"}' onkeyup="checkDecimals10_2(this);" value='%{(tpLiablityAmount==null || "".equals(tpLiablityAmount))?"":tpLiablityAmount}'  />
        							<b>&nbsp;<s:property value="currencyType"/></b>
        						</div>
        					</div>
        					<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
        						<div class="text">&nbsp;</div>
								<div class="tbox">
									<button type="button" class="btn btn-danger" style="width: 100%;" onclick="getComparisionDetails(this.form);">Calculate</button>
								</div>
        					</div>
       					</div>
       					<br class="clear"/>
       					<div style="color: red;">
        					<s:label>Please click the “add” button</s:label>&nbsp;<img src="${pageContext.request.contextPath}/images/add_cover.png" height="24" width="24" >&nbsp;<s:label>to add Additional Covers in the quotations below</s:label>
        				</div>
       					<br class="clear"/>
						<div id="comparisionDetailsAjax">
							<s:include value="/pages/motor/premiumComparision.jsp"/>
       					</div>
        				<div class="row" align="center">
        					<s:submit type="button" name="close"  key="Back" cssClass="btn btn-sm btn-danger" onclick="getBack('newQuote')"/>
        					<s:hidden name="brokerCode"/>
        					<s:hidden name="applicationNo"/>
							<s:hidden name="quoteNo"/>
							<s:hidden name="custdob"/>
							<s:hidden name="quoteStatus"/>
							<s:hidden name="endTypeId"/>
							<s:hidden name="policyNo"/>
							<s:hidden name="brokerCompany"/>
							<s:hidden name="custName"/>
							<s:hidden name="custmerId"/>
							<s:hidden name="vehicleId"/>
        				</div>        				
        			</div>
        		</div>
        	</div>
        </div>
        </s:form>
    </s:elseif>
    <s:elseif test='"editCovRate".equalsIgnoreCase(display)'>
        <s:form id="motor" name="motor" method="post" theme="simple" action="updateCovRateMotor.action">
        <div class="row">
			<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
				<span style="color:red;"><s:actionerror/></span>
			</div>
		</div>
		<div class="row">
			<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
				<div class="panel panel-default">
					<div class="panel-heading">
						<div class="row">
							<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
								<div class="btn-group" role="group" aria-label="...">							
								  <button type="button" title="Customer Information" title="Customer Information" class="btnGroupMargin btn btn-lg btn-warning"><i class="fa fa-user"></i></button>
								  <button type="button" title="Premium Comparison" class="btnGroupMargin btn btn-lg btn-warning""><i class="fa fa-eye"></i></button>
								  <button type="button" title="Policy Information" class="btnGroupMargin btn btn-lg btn-success active"><i class="fa fa-users"></i></button>
								</div>
							</div>
							<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6 text-success">
								<span class="textHeading"><s:text name="label.premiumInfo" /> </span> 
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
						<br/>
						<div class="row">
							<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
								<s:set name="multiVehicleDtls" value="%{multiVehicleDetails}"/>
								<table cellpadding="1" class="footable" cellspacing="1" border="1">
									<thead>
								        <tr>
											<th style="width:5%;"><s:label value="S.No." /></th>
											<th style="width:15%;"><s:text name="motor.vehicleUsage"/></th>
											<th style="width:20%;"><s:text name="motor.make" /></th>
											<th style="width:20%;"><s:text name="motor.model" /></th>
											<th style="width:15%;"><s:text name="motor.typeOfBody" /></th>
											<th style="width:15%;"><s:text name="motor.sumInsured" /></th>
											<th style="width:10%;"> <s:text name="Modify Rate" /> </th>
										</tr>
									</thead>
									<tbody>
										<s:iterator value="#multiVehicleDtls" var="view" status="status">
											<tr>
												<td align="center"><s:property value="#status.count" /></td>
												<td align="center"><s:property value="#view.VEHICLETYPE_DESC" /></td>
												<td align="center"><s:property value="#view.MAKE_NAME" /></td>					
												<td align="center"><s:property value="#view.MODEL_NAME" default=" " /> </td>
												<td align="center"><s:property value="#view.TYPE_OF_BODY_NAME" /></td>
												<td align="right"> <s:property value="getText('{0,number,#,##0.00}',{#view.SUMINSURED_VALUE_LOCAL})"/> </td>
												<td align="center">
													<button type="button" name="vehicleEditButton" id="vehicleEditButton" onclick="editCoverRate(this.form,'${view.VEHICLE_ID}');" class="btn btn-sm btn-danger"> <i class="glyphicon glyphicon-pencil"></i> </button>
												</td>
											</tr>
										</s:iterator>
									</tbody>
								</table>
							</div>
						</div>
						<s:if test="premiumInfo.size()>0">
						<br/>
						<div class="row">
							<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
								<div class="panel panel-primary">
				        			<div class="panel-heading txtB">
										<s:text name="motor.premiumInfo"/>
				        			</div>
				        			<div class="panel-body">
				        				<div class="boxcontent">
				        					<table class="footable" width="100%">
				        						<tbody>
					        						<s:set name="groupId" value=""/>
								                    <s:set var="preAmt" value="0.0" scope="page"/>
								                    <s:iterator value="premiumInfo" var="prInfo" status="stat">
					        						
								                    <s:if test="%{#groupId != #prInfo.GROUP_ID}">
								                    <s:set name="groupId" value="%{#prInfo.GROUP_ID}"/>
								                    <tr class="tableHeading">
								                    	<td colspan="5"><s:property value="%{#prInfo.GROUP_DESC_ENGLISH}"/></td>
								                    </tr>
								                    </s:if>
					        						<tr>
					        							<td><s:hidden name="coverId[%{#stat.count-1}]" value="%{#prInfo.POLICYTYPE_COVERID}"/></td>
					        							<td><s:property  value="%{#prInfo.OPCOVER_DESC_ENGLISH}"/></td>
					        							<td>
					        								<s:property value="getText('{0,number,#,##0.00}',{#prInfo.SUM_INSURED})"/>
						                             		<s:hidden name="sI[%{#stat.count-1}]"  cssClass="inputBox" value="%{#prInfo.SUM_INSURED}" cssStyle="text-align:right;"/>
					        							</td>
					        							<td>
					        								<s:textfield name="baseRate[%{#stat.count-1}]" cssClass="inputBox" value="%{#prInfo.RATE}" cssStyle="text-align:right;" maxLength="16" onkeyup="checkDecimals(this);" />
					        							</td>
					        							<td>
					        								<s:property value="getText('{0,number,#,##0.00}',{#prInfo.PREMIUM})"/>
						                            		<s:set var="preAmt" value="%{@java.lang.Double@parseDouble(#attr.preAmt)+@java.lang.Double@parseDouble(#prInfo.PREMIUM)}" scope="page"/>
					        							</td>
					        						</tr>        						
					        						</s:iterator>
				        						</tbody>
				        					</table>
				        					<br class="clear"/>
				        				</div>
				        			</div>
				        		</div>
							</div>
						</div>
						</s:if>
        			</div>
        		</div>
        	</div>
        </div>
        <div class="row">
			<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" align="center">
				<input type="button" name="Submit1" class="btn btn-sm btn-danger" value="Back" onclick="getBack('newQuote')"/> &nbsp;&nbsp;&nbsp;
	            <input type="button" name="Submit2" class="btn btn-sm btn-info" value="Calculate" onclick="this.form.actionType.value='getCalculate';this.form.submit();"/>&nbsp;&nbsp;&nbsp;
				<input type="button" name="Submit3" class="btn btn-sm btn-success" value="Proceed" onclick="this.form.actionType.value='getQuote';this.form.submit();"/>
				<s:hidden name="applicationNo"/>
		 		<s:hidden name="quoteNo"/>
		 		<s:hidden name="quoteStatus"/>
		 		<s:hidden name="actionType" />
		 		<s:hidden name="endTypeId"/>
				<s:hidden name="policyNo"/>
                <s:hidden name="brokerCompany"/>
                <s:hidden name="custName"/>
                <s:hidden name="vehicleId"/>
                <s:hidden name="currencyType"/>
			</div>
		</div>
        </s:form>
    </s:elseif>
    
    <s:elseif test="'policyInfo'.equalsIgnoreCase(display)">
        <s:set var="multiVehicleDtls" value="%{multiVehicleDetails}"/>
        <s:set var="totPremium" value="0"/>
        <s:form id="motor" name="motor" method="post" theme="simple" action="getGeratePolicyMotor.action">
        <div class="container PolicyPage">
        <div class="row">
			<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
				<span style="color:red;"><s:actionerror/></span>
			</div>
		</div>
		<%-- <div class="row">
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
		</div> --%>
		 <div class="Card_Parent PolicyInformation">
            <div class="card card-5">
				<%-- <div class="row">
					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
	        			<h3><s:text name="motor.policyInfo" /></h3><hr>
	        			<div class="panel-body mt-5">
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
	        						<span class="text"> <s:text name="motor.customerName"  /> </span> &nbsp; : &nbsp; <s:property value="title"/>.<s:property value="custNameLabel"/>&nbsp;<s:property value="custLastNameLabel"/>
								</div>
								<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
									<span class="text"> <s:text name="motor.email" /> </span> &nbsp; : &nbsp; <s:property value="custEmailLabel"/>
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
				</div> --%>
				<div class="row">
	                	<div class="col-md-12">
	                		<h3><s:text name="motor.policyInfo" /></h3><hr>
	                		<div class="row">
			                    <div class="col-md-5 col-6">
			                        <label class="LabelHeading"><s:text name="motor.quoteNo"  /></label>
			                    </div>
			                    <div class="col-md-5 col-6">
			                        <label class="labelValues"><s:property value="quoteNo"/></label>
			                    </div>
			                </div>
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
			                        <label class="LabelHeading"><s:text name="Policy Type"  /> </label>
			                    </div>
			                    <div class="col-md-5 col-6">
			                        <label class="labelValues"><s:property value="policyTypeDesc"/></label>
			                    </div>
			                </div>
	                	</div>
	                </div>
	                
	                <div id="demo" class="collapse">
		                <%-- <div class="row">
		                    <div class="col-md-5 col-6">
		                        <label class="LabelHeading"><s:text name="motor.quoteNo"  /></label>
		                    </div>
		                    <div class="col-md-5 col-6">
		                        <label class="labelValues"><s:property value="quoteNo"/></label>
		                    </div>
		                </div> --%>
		                <div class="row">
		                    <div class="col-md-5 col-6">
		                        <label class="LabelHeading"><s:text name="motor.quoteDate" /></label>
		                    </div>
		                    <div class="col-md-5 col-6">
		                        <label class="labelValues"> <s:property value="quoteDate"/></label>
		                    </div>
		                </div>
		                <div class="row">
		                    <div class="col-md-5 col-6">
		                        <label class="LabelHeading"><s:text name="motor.product" /></label>
		                    </div>
		                    <div class="col-md-5 col-6">
		                        <label class="labelValues"><s:property value="product"/> </label>
		                    </div>
		                </div>
		                <%-- <div class="row">
		                    <div class="col-md-5 col-6">
		                        <label class="LabelHeading"><s:text name="motor.customerName"  /> </label>
		                    </div>
		                    <div class="col-md-5 col-6">
		                        <label class="labelValues"><s:property value="title"/>.<s:property value="customerName"/>&nbsp;<s:property value="custLastName"/></label>
		                    </div>
		                </div> --%>
		                <div class="row">
		                    <div class="col-md-5 col-6">
		                        <label class="LabelHeading"><s:text name="motor.email" /></label>
		                    </div>
		                    <div class="col-md-5 col-6">
		                        <label class="labelValues"><s:property value="email"/></label>
		                    </div>
		                </div>
		                 <%-- <div class="row">
		                    <div class="col-md-5 col-6">
		                        <label class="LabelHeading"><s:text name="motor.policyStartDt" /></label>
		                    </div>
		                    <div class="col-md-5 col-6">
		                        <label class="labelValues"><s:property value="policyStartDate"/></label>
		                    </div>
		                </div>
		                 <div class="row">
		                    <div class="col-md-5 col-6">
		                        <label class="LabelHeading"><s:text name="motor.policyEndDt" /></label>
		                    </div>
		                    <div class="col-md-5 col-6">
		                        <label class="labelValues"><s:property value="policyEndDatePeriod"/></label>
		                    </div>
		                </div> --%>
		                 <div class="row">
		                    <div class="col-md-5 col-6">
		                        <font color="red"><label class="LabelHeading">Currency</label></font>
		                    </div>
		                    <div class="col-md-5 col-6">
		                        <label class="labelValues"><s:property value="currencyType"/></label>
		                    </div>
		                </div>
	                </div>
	                <div class="row">
	                	<div class="col-md-12 text-right">
	                		<a style="text-decoration:none" id="more" data-toggle="collapse" data-target="#demo" onclick="funCollapse('less')">View More <i class="fas fa-chevron-circle-down"></i></a>
	                		<a style="text-decoration:none;display:none" id="less" data-toggle="collapse" data-target="#demo" onclick="funCollapse('more')">View Less <i class="fas fa-chevron-circle-up"></i></a>
	                	</div> 
	                </div>
			</div>
		</div><br>
		<%-- <div class="row">
			<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
				<s:include value="/pages/customer/customerDetailsMotor.jsp"></s:include>
			</div>
		</div> --%>
		<%-- <div class="Card_Parent">
	            <div class="card card-1">
	                <h3>Policy Holder Information</h3>
	                <hr>
	                <div class="row mt-3">
	                    <div class="col-md-12">
	                        <div class="row mt-3">
	                            <div class="col-md-4">
	                            	<label class="labelHeader"><s:text name="customer.title"  /><font color="red">*</font></label>
	                                <div class="input-group mb-3">
	                                    <div class="input-group-prepend">
	                                        <span class="input-group-text border border-right-0">
	                                            <i class="fas fa-heading"></i>
	                                        </span>
	                                    </div>
	                                    <!-- <input type="text" class="form-control border border-left-0" placeholder="Title"> -->
	  									<s:select name="title" id="title" list="titleList" listKey="CODEDESC" listValue="CODEDESC" class="form-control border "  disabled="#disable1"/>
	  						
	                                </div>
	                            </div>
	                            <div class="col-md-4">
	                            	<label class="labelHeader"><s:text name="customer.firstName"  /><font color="red">*</font></label>
	                                <div class="input-group mb-3">
	                                    <div class="input-group-prepend">
	                                        <span class="input-group-text border border-right-0">
	                                            <i class="fas fa-user-check"></i>
	                                        </span>
	                                    </div>
	                                    <!-- <input type="text" class="form-control border border-left-0" placeholder="Firstname"> -->
										<s:textfield name="customerName" id="customerName" class="form-control border empyCustDetails"  maxLength="100" disabled="#disable1" />
	                                </div>
	                            </div>
	                            <div class="col-md-4">
	                            	<label class="labelHeader"><s:text name="customer.lastName"/><font color="red">*</font></label>
	                                <div class="input-group mb-3">
	                                    <div class="input-group-prepend">
	                                        <span class="input-group-text border border-right-0"><i class="fas fa-user-check"></i></span>
	                                    </div>
	                                    <!-- <input type="text" class="form-control border border-left-0" placeholder="Lastname"> -->
	                                    <s:textfield name="custLastName" id="custLastName" class="form-control border empyCustDetails" maxLength="20" disabled="#endtDisable"  />
	                                </div>
	                            </div>
	                        </div>
	                        <div class="row">
	                        	<div class="col-md-4">
	                            	<label class="labelHeader"><s:text name="customer.dob" /><s:text name="(DD/MM/YYYY)" /><font color="red">*</font></label>
	                                <div class="input-group mb-3">
	                                    <div class="input-group-prepend">
	                                        <span class="input-group-text border border-right-0"><i
	                                                class="fas fa-calendar-alt"></i></span>
	                                    </div>
	                                    <!-- <input type="text" class="form-control border border-left-0" placeholder="DOB DD/MM/YYYY"> -->
	                                    <s:textfield name="custdob" id="custdob" class="form-control border datePicker L empyCustDetails"  disabled="#endtDisable"/>
	                                </div>
	                            </div>
	                            <div class="col-md-4">
	                                <div class="row Genders mt-1">
	                                    <div class="col-md-12 col-12">
	                                        <label for="gender" class="labelHeader"><s:text name="customer.gender"/><font color="red">*</font></label>
	                                    </div>
	                                    <div class="col-md-12 col-12">
	                                         <s:radio name="gender" id="gender" list="#{'M':'Male','F':'Female'}" />
	                                    </div>
	                                </div>
	                            </div>
	                            <div class="col-md-4">
	                            	<label class="labelHeader"><s:text name="customer.occupation"/></label>
	                                <div class="input-group mb-3">
	                                    <div class="input-group-prepend">
	                                        <span class="input-group-text border border-right-0"><img
	                                                src="./assets/Images/employee.png"></span>
	                                    </div>
	                                    <!-- <input type="text" class="form-control border border-left-0" placeholder="Occupation"> -->
	                                    <s:textfield name="occupation" maxlength="30" class="form-control border L empyCustDetails" disabled="#endtDisable" />
	                                </div>
	                            </div>
	                        </div>
	                        <div class="row">
	                            <div class="col-md-4">
	                            	<label class="labelHeader"><s:text name="Address 1"/><font color="red">*</font></label>
	                                <div class="input-group mb-3">
	                                    <div class="input-group-prepend">
	                                        <span class="input-group-text border border-right-0">
	                                            <i class="fas fa-address-card"></i>
	                                        </span>
	                                    </div>
	                                    <!-- <input type="text" class="form-control border border-left-0" placeholder="Residentail Address"> -->
	                                    <s:textfield name="address1" id="address1" class="form-control border  empyCustDetails"  maxLength="200" disabled="#endtDisable"/>
	                                </div>
	                            </div>
	                            <div class="col-md-4">
	                            	<label class="labelHeader"><s:text name="Address 2"/></label>
	                                <div class="input-group mb-3">
	                                    <div class="input-group-prepend">
	                                        <span class="input-group-text border border-right-0">
	                                            <i class="fas fa-address-card"></i>
	                                        </span>
	                                    </div>
	                                    <!-- <input type="text" class="form-control border border-left-0" placeholder="Work Address"> -->
	                                    <s:textfield name="address2" id="address2" class="form-control border empyCustDetails" maxlength="200" disabled="#endtDisable"/>
	                                </div>
	                            </div>
	                            <div class="col-md-4">
	                            	<label class="labelHeader"><s:text name="customer.city"  /><font color="red">*</font></label>
	                                <div class="input-group mb-3">
	                                    <div class="input-group-prepend">
	                                        <span class="input-group-text border border-right-0">
	                                            <i class="fas fa-address-card"></i>
	                                        </span>
	                                    </div>
	                                    <!-- <input type="text" class="form-control border border-left-0" placeholder="Residentail Address"> -->
	                                    <s:select name="city" id="city" list="cityList" headerKey="" headerValue="---Select City---" listKey="CODEDESC" listValue="CODEDESC" class="form-control empyCustDetails"  disabled="#endtDisable"/>
	                                </div>
	                            </div>
	                        </div>
	                        <div class="row">
	                            
	                            <div class="col-md-4">
	                            	<label class="labelHeader"><s:text name="customer.poBox"  /></label>
	                                <div class="input-group mb-3">
	                                    <div class="input-group-prepend">
	                                        <span class="input-group-text border border-right-0">
	                                            <i class="fas fa-envelope"></i>
	                                        </span>
	                                    </div>
	                                    <s:textfield name="poBox" id="poBox" class="form-control border empyCustDetails"  maxLength="6" disabled="#endtDisable"/>
	                                </div>
	                            </div>
	                            <div class="col-md-4">
	                            	<label class="labelHeader"><s:text name="customer.mobileNo1"  /><font color="red">*</font></label>
	                                <div class="input-group mb-3">
	                                    <div class="input-group-prepend">
	                                        <span class="input-group-text border border-right-0">
	                                            <i class="fas fa-address-card"></i>
	                                        </span>
	                                    </div>
	                                    <!-- <input type="text" class="form-control border border-left-0" placeholder="Residentail Address"> -->
	                                    <s:textfield name="mobileNo" id="mobileNo" class="form-control border empyCustDetails"  maxLength="10" onkeyup="checkNumbers(this);" disabled="#disable1"/>
	                                </div>
	                            </div>
	                            <div class="col-md-4">
	                            	<label class="labelHeader"><s:text name="customer.alterMobile"  /></label>
	                                <div class="input-group mb-3">
	                                    <div class="input-group-prepend">
	                                        <span class="input-group-text border border-right-0">
	                                            <i class="fas fa-envelope"></i>
	                                        </span>
	                                    </div>
	                                    <s:textfield name="custAlterMobileNo" id="custAlterMobileNo" class="form-control border empyCustDetails"  maxLength="10" onkeyup="checkNumbers(this);" disabled="#endtDisable"/>
	                                </div>
	                            </div>
	                        </div>
	                       
	                        <div class="row">
	                            <div class="col-md-4">
	                            	<label class="labelHeader"><s:text name="customer.email"  /><font color="red">*</font></label>
	                                <div class="input-group mb-3">
	                                    <div class="input-group-prepend">
	                                        <span class="input-group-text border border-right-0">
	                                            <i class="fas fa-envelope"></i>
	                                        </span>
	                                    </div>
	                                    <!-- <input type="text" class="form-control border border-left-0" placeholder="Email Address"> -->
	                                    <s:textfield name="email" id="email" class="form-control border empyCustDetails" maxLength="100" disabled="#endtDisable"/>
	                                </div>
	                            </div>
	                            <div class="col-md-8">
	                            	<label class="labelHeader"><s:text name="customer.nrc"/><font color="red">*</font></label>
	                                <div class="input-group mb-3">
	                                    <div class="input-group-prepend">
	                                        <span class="input-group-text border border-right-0">
	                                            <i class="fas fa-id-card"></i>
	                                        </span>
	                                    </div>
	                                    <s:textfield name="custnrc1" id="custnrc1" class="form-control border  empyCustDetails" cssStyle="width:30%" maxLength="6" size="6" onkeyup="checkDecimals6_0(this);autotab(this, this.form.custnrc2);" disabled="#endtDisable" />&nbsp;/&nbsp;
	  									<s:textfield name="custnrc2" id="custnrc2" class="form-control border  empyCustDetails" cssStyle="width:25%" maxLength="2" size="2" onkeyup="checkDecimals6_0(this);autotab(this, this.form.custnrc3)" disabled="#endtDisable"/>&nbsp;/&nbsp;
	  									<s:textfield name="custnrc3" id="custnrc3" class="form-control border  empyCustDetails" cssStyle="width:15%" maxLength="1" size="1" onkeyup="checkDecimals6_0(this);" disabled="#endtDisable"/>
	                                
	                                </div>
	                            </div>
	                            
	                            <div class="col-md-6">
	                                <div class="input-group mb-3">
	                                    <div class="input-group-prepend">
	                                        <span class="input-group-text border border-right-0">
	                                            <i class="fas fa-id-card"></i>
	                                        </span>
	                                    </div>
	                                    <!-- <input type="text" class="form-control border border-left-0" placeholder="National Registration Card"> -->
	                                    <s:textfield name="custnrc1" id="custnrc1" class="form-control border border-left-0 empyCustDetails" cssStyle="width:30%" maxLength="6" size="6" onkeyup="checkDecimals6_0(this);autotab(this, this.form.custnrc2);" disabled="#endtDisable" placeholder="NRC"/>&nbsp;/&nbsp;
	  									<s:textfield name="custnrc2" id="custnrc2" class="form-control border  empyCustDetails" cssStyle="width:25%" maxLength="2" size="2" onkeyup="checkDecimals6_0(this);autotab(this, this.form.custnrc3)" disabled="#endtDisable"/>&nbsp;/&nbsp;
	  									<s:textfield name="custnrc3" id="custnrc3" class="form-control border  empyCustDetails" cssStyle="width:15%" maxLength="1" size="1" onkeyup="checkDecimals6_0(this);" disabled="#endtDisable"/>
	                                </div>
	                            </div>
	                        </div>
	                        <div class="row">
	                            <div class="col-md-4">
	                            	<label class="labelHeader"><s:text name="customer.passportNo"/></label>
	                                <div class="input-group mb-3">
	                                    <div class="input-group-prepend">
	                                        <span class="input-group-text border border-right-0"><i
	                                                class="fas fa-passport"></i></span>
	                                    </div>
	                                    <!-- <input type="text" class="form-control border border-left-0" placeholder="Passport No"> -->
	                                    <s:textfield name="custPassportNo" id="custPassportNo" class="form-control border empyCustDetails"  maxLength="10" disabled="#endtDisable"/>
	                                </div>
	                            </div>
	                            <div class="col-md-4">
	                                <div class="row Genders mt-1">
	                                    <div class="col-md-12 col-12">
	                                        <label for="customerType" class="labelHeader"><s:text name="customer.customerType"/><font color="red">*</font></label>
	                                    </div>
	                                    <div class="col-md-12 col-12">
	                                         <s:radio name="customerType" id="customerType" list="#{'INDIVIDUAL':'Individual','CORPORATE':'Corporate'}" value='%{(customerType==null||"".equals(customerType))?"INDIVIDUAL":customerType}' cssClass="input "  onclick="toggleCompanyRegistration(this.value);"/>
	                                    </div>
	                                </div>
	                            </div>
	                            
	                            <div class="col-md-4">
	                            	<label class="labelHeader"><s:text name="customer.companyRegNo"  /><font color="red">*</font></label>
	                                <div class="input-group mb-3">
	                                    <div class="input-group-prepend">
	                                        <span class="input-group-text border border-right-0"><i class="fas fa-passport"></i></span>
	                                    </div>
	                                    <s:textfield name="companyRegNo" id="companyRegNo" class="form-control border empyCustDetails"  maxLength="10" disabled='%{(customerType==null||"".equals(customerType)||#disable1||"INDIVIDUAL".equals(customerType))?"true":"false"}'/>
	                                </div>
	                            </div>
	                        </div>
	                       
	                    </div>
	                    
	                </div>
	            </div>
	        </div><br> --%>
		<div class="Card_Parent adminClass">
            <div class="card card-5" >
				<div class="row">
						<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
							<h3><s:text name="label.quoteDetail" /></h3><hr>
		        			<div class="panel-body">
		        			
		        				<div class="row">
		        					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
		        					 <table width="100%" class="footable">
											<s:set name="groupId" value=""/>
											<s:set var="preAmt" value="0.0" scope="page"/>
											<s:iterator value="premiumInfoNew" var="prInfo" status="stat">
											<s:if test="%{#groupId != #prInfo.GROUP_ID}">
												<s:set name="groupId" value="%{#prInfo.GROUP_ID}"/>
												<%-- <thead>
												<tr>	     
											        <th colspan="5" ><s:property  value="%{#prInfo.GROUP_DESC_ENGLISH}"/></th>
												</tr>
												</thead> --%>
											</s:if>
											<%-- <tbody>
											<tr>       
												<td align="center" width="10%">
													<s:hidden name="coverId[%{#stat.count-1}]" value="%{#prInfo.POLICYTYPE_COVERID}"/>
												</td>
												<s:if test='"11".equalsIgnoreCase(#prInfo.POLICYTYPE_COVERID)'>
													<td align="left" width="20%">
														
													</td>&nbsp;&nbsp;
													<td align="center" width="40%">
														<s:property value="getText('{0,number,#,##0.00}',{#prInfo.SUM_INSURED})"/>
														<s:hidden name="sI[%{#stat.count-1}]"  cssClass="inputBox" value="%{#prInfo.SUM_INSURED}" cssStyle="text-align:right;"/>
													</td>
												</s:if>
												<s:else>
													<td align="left" width="20%">
														<s:property  value="%{#prInfo.OPCOVER_DESC_ENGLISH}"/>
													</td>
													<td align="center" width="40%">
														<s:property value="getText('{0,number,#,##0.00}',{#prInfo.SUM_INSURED})"/>
														<s:hidden name="sI[%{#stat.count-1}]"  cssClass="inputBox" value="%{#prInfo.SUM_INSURED}" cssStyle="text-align:right;"/>
													</td>
												</s:else>
												<td align="left" width="25%">
													<s:textfield name="baseRate[%{#stat.count-1}]" cssClass="form-control"  value="%{#prInfo.RATE}" size="11" cssStyle="text-align:right; width:100%;" maxLength="16" onkeyup="checkDecimals(this);" readonly="true"/>
												</td>
												<td align="right" width="20%"> 
													<s:property value="getText('{0,number,#,##0.00}',{#prInfo.PREMIUM})"/>
													<s:set var="preAmt" value="%{@java.lang.Double@parseDouble(#attr.preAmt)+@java.lang.Double@parseDouble(#prInfo.PREMIUM)}" scope="page"/>
												</td>
											</tr>								
											</tbody> --%>
											<s:hidden name="sI[%{#stat.count-1}]"  cssClass="inputBox" value="%{#prInfo.SUM_INSURED}" cssStyle="text-align:right;"/>
											<s:set var="preAmt" value="%{@java.lang.Double@parseDouble(#attr.preAmt)+@java.lang.Double@parseDouble(#prInfo.PREMIUM)}" scope="page"/>
											</s:iterator>
											
											<tbody>
											<tr>
												<td colspan="5" class="p-3" align="right">
													<button type="button" class="btn btn-sm btn-primary" data-toggle="modal" data-target="#myModalPremNew" >View More</button>
												</td>
											</tr>
											<tr>
												<td align="center" width="5%"></td>
												<td align="left" width="5%"></td>									
												
												<td align="center" width="50%">
													<font style="float:left;"><s:text name="motor.Premium"/></font>
													<b style="float:right;">[<s:property value="currencyType"/>]</b>
												</td>
												<td align="left" width="25%"></td>
												<td width="30%" align="right">
													<s:textfield name="premium" id="premium" cssClass="form-control"  value="%{getText('{0,number,0.00}',{#attr.preAmt})}" onkeyup="checkDecimals(this);" maxlength="11" cssStyle="text-align:right;" readonly="true"/>
												</td>
											</tr>
											<s:if test='#session.user1 == "admin" || #session.Referal == "Y" || quoteStatus == "RA"'>
											<tr>
												<td align="center" width="5%"></td>
												<td align="left" width="5%"></td>									
												
												<td align="center" width="50%">
												<font style="float:left;"><s:text name="motor.loadingOrDiscountPremium"/></font>
												<b style="float:right;">[<s:property value="currencyType"/>]</b>
												</td>
												<td align="left" width="25%"></td>
												<td width="30%" align="right">
													<s:select list="#{'+':'+','-':'-'}" name="sign" onchange="getTotalPremium(this.form)" disabled="%{quoteStatus=='RA' && #session.user1 != 'admin'?'true':'false'}" cssClass="form-control" cssStyle="width:34%;"/>&nbsp;&nbsp;&nbsp;
						                            <s:textfield name="loadOrDiscPremium" id="loadOrDiscPremium" cssClass="form-control"  disabled="%{quoteStatus=='RA' && #session.user1 != 'admin'?'true':'false'}" onkeyup="checkDecimals4_2(this);getTotalPremium(this.form);" maxlength="11" cssStyle="text-align:right;width:60%;"/>
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
												
												<td>
													<font style="float:left;"><s:text name="motor.policyFee"/></font>
													<b style="float:right;">[<s:property value="currencyType"/>]</b>
												</td>
												<td></td>
												<td width="100%" align="right">
													<s:textfield name="policyFee" id="policyFee" cssClass="form-control"  onkeyup="checkDecimals(this);" maxlength="11" cssStyle="text-align:right;" readonly="true"/>
												</td>
											</tr>
											<tr>
												<td></td>
												<td></td>									
												
												<td>
													<font style="float:left;"><s:text name="motor.totalPremiumPayable" /></font>
													<b style="float:right;">[<s:property value="currencyType"/>]</b>
												</td>
												<td></td>	
												<td width="100%" align="right">
												
													<s:set var="totPremium" value="%{getText('{0,number,0.00}',{@java.lang.Double@parseDouble(#attr.preAmt)+@java.lang.Double@parseDouble(policyFee)})}"/>
													<s:textfield name="totalPremium" id="totalPremium" cssClass="form-control"  value="%{#totPremium}" onkeyup="checkDecimals(this);" maxlength="11" cssStyle="text-align:right;" readonly="true"/>
												</td>
											</tr>
											<s:if test='#session.user1 == "admin" || #session.Referal == "Y" || (!"".equals(adminRemarks)&&(adminRemarks!=null)&& #session.user1 != "admin")'>
											<tr>
												<td></td>
												<td></td>									
												
												<td align="right"><s:text name="motor.specialCondition"/></td>
												<td></td>
												<td>
													<s:textarea name="adminRemarks" onkeyup="textLimit(this,'470')"  cols="50" rows="2" readonly="%{quoteStatus=='RA' && #session.user1 != 'admin'?'true':'false'}"/>
												</td>
											</tr>
											</s:if>
											<%-- <s:if test='#session.user1 == "admin"'>
											<tr>
												<td></td>
												<td></td>									
												
												<td align="right"><s:text name="motor.referralStatus"/></td>
												<td></td>
												<td><s:radio list="#{'Y':'Accept','N':'Reject','A':'Pending'}" name="adminRefStatus" value="%{adminRefStatus==null?'A':adminRefStatus}" /></td>
											</tr>
											<br /></s:if> --%>
											</tbody>	
										</table> 
										<%-- <div class="col-md-6 p-3">
			                            	<s:set name="groupId" value=""/>
											<s:set var="preAmt" value="0.0" scope="page"/>
											<s:iterator value="premiumInfo" var="prInfo" status="stat">
			                                	<s:set name="groupId" value="%{#prInfo.GROUP_ID}"/>
			                                	<s:set var="preAmt" value="%{@java.lang.Double@parseDouble(#attr.preAmt)+@java.lang.Double@parseDouble(#prInfo.PREMIUM)}" scope="page"/>
			                                </s:iterator>
			                                <s:if test='#session.user1 == "admin" || quoteStatus == "RA"'>
				                                    <div class="row">
							                            <s:if test='"+".equalsIgnoreCase(sign)'>
							                            	<s:set var="preAmt" value="%{@java.lang.Double@parseDouble(#attr.preAmt)+@java.lang.Double@parseDouble(loadOrDiscPremium)}" scope="page"/>
							                            </s:if>
							                            <s:else>
							                            	<s:set var="preAmt" value="%{@java.lang.Double@parseDouble(#attr.preAmt)-@java.lang.Double@parseDouble(loadOrDiscPremium)}" scope="page"/>
							                            </s:else>
				                                    </div>
			                                </s:if>
		                                    <label class="LabelHeading"><s:text name="motor.totalPremiumPayable" />  [<s:property value="currencyType"/>]</label>
		                                    <label class="LabelHeading"><s:set var="totPremium" value="%{getText('{0,number,0.00}',{@java.lang.Double@parseDouble(#attr.preAmt)+@java.lang.Double@parseDouble(policyFee)})}"/>
												:&nbsp;&nbsp;<b><s:property value="%{#totPremium}"/></b>&nbsp;&nbsp;
											</label>
											
			                            </div> --%>
			                            <br><h3><s:text name="Condition Details" /></h3><hr>
			                            <div class="row mt-3">
						                    <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6 mt-2">
												<div>View and Edit Conditions / Deductibles</div>
											</div>
											<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
												<div class="row">
													<div class="tbox">
														<button type="button" class="btn btn-sm btn-primary" data-toggle="modal" data-target="#conModal" onclick="return fnClausesAddAjax(this.form,'clausesEditAjax');" >Conditions</button>
													</div>
													<!-- <div class="tbox offset-1">
														<button type="button" class="btn btn-sm btn-warning" data-toggle="modal" data-target="#conModal" onclick="return fnClausesEditAjax(this.form,'clausesEditAjax');" >View/Edit</button>
													</div> -->
													<div class="tbox offset-1">
														<button type="button" class="btn btn-sm btn-primary" data-toggle="modal" data-target="#dedutibleModal" onclick="return fnDeductibleAddAjax(this.form,'deductibleEditAjax');" >Deductible</button>
													</div>
												</div>
											</div>
						                </div>
										
										<div class="container modal_VehicleDetails">
										    <div class="modal fade" id="conModal">
										      <div class="modal-dialog  modal-lg">
										        <div class="modal-content">
										        	<div class="modal-header">
												      	<h5 class="modal-title">Condition Information</h5>
												      	<i class="far fa-times-circle mt-1" data-dismiss="modal"></i>
												    </div>
												    <div class="modal-body">
											          	<div id="clausesEditAjax"></div>
										          	</div>
										        </div>
										      </div>
										    </div>
										</div>
										<div class="container modal_VehicleDetails">
										    <div class="modal fade" id="dedutibleModal">
										      <div class="modal-dialog  modal-lg">
										        <div class="modal-content">
										        	<div class="modal-header">
												      	<h5 class="modal-title">Deductible Information</h5>
												      	<i class="far fa-times-circle mt-1" data-dismiss="modal"></i>
												    </div>
												    <div class="modal-body">
											          	<div id="deductibleEditAjax"></div>
										          	</div>
										        </div>
										      </div>
										    </div>
										</div>
										<div class="container modal_VehicleDetails">
										    <div class="modal fade" id="myModalPremNew">
										      <div class="modal-dialog  modal-lg">
										        <div class="modal-content">
										        	<div class="modal-header">
												      	<h5 class="modal-title">Premium Information</h5>
												      	<i class="far fa-times-circle mt-2" data-dismiss="modal"></i>
												    </div>
												    <div class="modal-body">
											          	<div class="premiumBorder mt-2">
								                            <ul class="list-group QuotationDetails">
								                            <s:iterator value="vehicleDetailsList" var="vehInfo" status="vehStat">
								                            <s:set name="myVehId" value="%{#vehInfo.VEHICLE_ID}"/>
								                            <s:if test='(#myVehId)!="1"'>
								                            	<br>
								                            </s:if>
									                            <label class="LabelHeading buttonCls">Vehicle <s:property  value="%{#myVehId}"/></label>
									                                <div class="p-1">
									                                <table width="100%">
										        						<tbody>
										        							<tr>
										        								<td>Vehicle Usage : <b><s:property  value="%{#vehInfo.VEHICLETYPE_DESC}"/></b></td>
										        								<td>Make : <b><s:property  value="%{#vehInfo.MAKE_NAME}"/></b></td>
										        								<td>Model : <b><s:property  value="%{#vehInfo.MODEL_NAME}"/></b></td>
										        								<td>Type Of Body : <b><s:property  value="%{#vehInfo.TYPE_OF_BODY_NAME}"/></b></td>
										        							</tr>
										        						</tbody>
									        						</table>
									        						</div>
									                                <li class="list-group-item list-group-item-primary mt-1">
									                                    <div class="row">
									                                        <div class="col-md-5 col-5">
									                                            <label class="LabelHeading">Description</label>
									                                        </div>
									                                        <div class="col-md-2 col-2" align="right">
									                                            <label class="LabelHeading">Sum Insured</label>
									                                        </div>
									                                        <div class="col-md-2 col-2" align="right">
									                                            <label class="LabelHeading">Base Rate</label>
									                                        </div>
									                                        <div class="col-md-3 col-3"  align="right">
									                                            <label class="LabelHeading">Premium</label>
									                                        </div>
									                                    </div>
									                                </li>
									                                <hr>
									                                <s:set name="groupId" value=""/>
																	<s:set var="preAmt" value="0.0" scope="page"/>
									                                <s:iterator value="premiumInfoNew" var="prInfo" status="stat">
									                                	<s:set name="myVehIds" value="%{#prInfo.VEHICLE_ID}"/>
									                                	<s:set name="groupId" value="%{#prInfo.GROUP_ID}"/>
									                                	<s:if test='!"11".equalsIgnoreCase(#prInfo.POLICYTYPE_COVERID) && !"105".equalsIgnoreCase(#prInfo.POLICYTYPE_COVERID)'>
										                                	<s:if test='(#myVehId)==(#myVehIds)'>
										                                		<li class="list-group-item">
												                                    <div class="row">
												                                        <div class="col-md-5 col-5">
												                                            <label class="LabelHeading"><s:property  value="%{#prInfo.GROUP_DESC_ENGLISH}"/></label>
												                                            <%-- <s:hidden name="sI[%{#stat.count-1}]"  cssClass="inputBox" value="%{#prInfo.SUM_INSURED}" cssStyle="text-align:right;"/> --%>
												                                        </div>
												                                        <div class="col-md-2 col-2" align="right">
												                                            <label class="labelValues"><s:property  value="getText('{0,number,#,##0.00}',{#prInfo.SUM_INSURED})"/></label>
												                                        </div>
												                                        <div class="col-md-2 col-2" align="right">
												                                            <label class="labelValues"><s:property  value="getText('{0,number,#,##0.00}',{#prInfo.RATE})"/></label>
												                                        </div>
												                                        <div class="col-md-3 col-3 "  align="right">
												                                            <label class="labelValues"><s:property value="getText('{0,number,#,##0.00}',{#prInfo.PREMIUM})"/>
																								<s:set var="preAmt" value="%{@java.lang.Double@parseDouble(#attr.preAmt)+@java.lang.Double@parseDouble(#prInfo.PREMIUM)}" scope="page"/>
																							</label>
												                                        </div>
												                                    </div>
												                                </li>
										                                	</s:if>
									                                	</s:if>
									                                	<s:if test='(#myVehId)==(#myVehIds)'>
										                                	<hr>
										                                </s:if>
									                                </s:iterator>
									                                 <div class="col-md-12 col-12 buttonClsF">
									                                	<label class="labelValues">Total Premium for Vehicle <s:property  value="%{#myVehId}"/></label>&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;&nbsp;<label class="LabelHeading"><s:property value="getText('{0,number,#,##0.00}',{@java.lang.Double@parseDouble(#attr.preAmt)})"/>&nbsp;&nbsp;<s:property value="currencyType"/></label>
									                                </div>
								                                </s:iterator>
								                            </ul>
								                        </div>
							                        </div>
										        </div>
										      </div>
										    </div>
										</div>
										<div class="container modal_VehicleDetails">
										    <div class="modal fade" id="myModalPrem">
										      <div class="modal-dialog  modal-lg">
										        <div class="modal-content">
										        	<div class="modal-header">
												      	<h5 class="modal-title">Premium Information</h5>
												      	<i class="far fa-times-circle mt-2" data-dismiss="modal"></i>
												    </div>
												    <div class="modal-body">
											          	<div class="premiumBorder mt-2">
								                            <ul class="list-group QuotationDetails">
								                                <li class="list-group-item list-group-item-primary">
								                                    <div class="row">
								                                        <div class="col-md-5 col-5">
								                                            <label class="LabelHeading">Description</label>
								                                        </div>
								                                        <div class="col-md-2 col-2" align="right">
								                                            <label class="LabelHeading">Sum Insured</label>
								                                        </div>
								                                        <div class="col-md-2 col-2" align="right">
								                                            <label class="LabelHeading">Base Rate</label>
								                                        </div>
								                                        <div class="col-md-3 col-3"  align="right">
								                                            <label class="LabelHeading">Premium</label>
								                                        </div>
								                                    </div>
								                                </li>
								                                <hr>
								                                <s:set name="groupId" value=""/>
																<s:set var="preAmt" value="0.0" scope="page"/>
								                                <s:iterator value="premiumInfo" var="prInfo" status="stat">
								                                	<s:set name="groupId" value="%{#prInfo.GROUP_ID}"/>
								                                	<s:if test='!"106".equalsIgnoreCase(#prInfo.POLICYTYPE_COVERID) && !"11".equalsIgnoreCase(#prInfo.POLICYTYPE_COVERID) && !"105".equalsIgnoreCase(#prInfo.POLICYTYPE_COVERID)'>
									                                	<li class="list-group-item">
										                                    <div class="row">
										                                        <div class="col-md-5 col-5">
										                                            <label class="LabelHeading"><s:property  value="%{#prInfo.GROUP_DESC_ENGLISH}"/></label>
										                                            <%-- <s:hidden name="sI[%{#stat.count-1}]"  cssClass="inputBox" value="%{#prInfo.SUM_INSURED}" cssStyle="text-align:right;"/> --%>
										                                        </div>
										                                        <div class="col-md-2 col-2" align="right">
										                                            <label class="labelValues"><s:property  value="getText('{0,number,#,##0.00}',{#prInfo.SUM_INSURED})"/></label>
										                                        </div>
										                                        <div class="col-md-2 col-2" align="right">
										                                            <label class="labelValues"><s:property  value="getText('{0,number,#,##0.00}',{#prInfo.RATE})"/></label>
										                                        </div>
										                                        <div class="col-md-3 col-3 "  align="right">
										                                            <label class="labelValues"><s:property value="getText('{0,number,#,##0.00}',{#prInfo.PREMIUM})"/>
																						<s:set var="preAmt" value="%{@java.lang.Double@parseDouble(#attr.preAmt)+@java.lang.Double@parseDouble(#prInfo.PREMIUM)}" scope="page"/>
																					</label>
										                                        </div>
										                                    </div>
										                                </li>
								                                	</s:if>
								                                <hr>
								                                </s:iterator>
								                                <%-- <li class="list-group-item mt-3">
								                                    <div class="row">
								                                        <div class="col-md-9 col-9">
								                                            <label class="LabelHeading"><s:text name="motor.Premium"/>  [<s:property value="currencyType"/>]</label>
								                                        </div>
								                                        <div class="col-md-3 col-3 text-center">
								                                            <label class="labelValues"><s:textfield name="premium" id="premium" cssClass="form-control "  value="%{getText('{0,number,0.00}',{#attr.preAmt})}" onkeyup="checkDecimals(this);" maxlength="11" cssStyle="text-align:right; width:100%;" readonly="true"/></label>
								                                        </div>
								                                    </div>
								                                </li>
								                               <s:if test='#session.user1 == "admin" || quoteStatus == "RA"'>
									                                <li class="list-group-item">
									                                    <div class="row">
									                                        <div class="col-md-6 col-6">
									                                            <label class="LabelHeading"><s:text name="motor.loadingOrDiscountPremium"/>  [<s:property value="currencyType"/>]</label>
									                                        </div>
									                                        <div class="col-md-3 col-3">
								                                            	<label><s:select list="#{'+':'+','-':'-'}" name="sign" onchange="getTotalPremium(this.form)" disabled="%{quoteStatus=='RA' && #session.user1 != 'admin'?'true':'false'}" cssClass="form-control" cssStyle="width:100%;"/>&nbsp;&nbsp;&nbsp;</label>
									                                        </div>
									                                        <div class="col-md-3 col-3 text-center">
									                                            <label class="labelValues">
									                                            	<s:textfield name="loadOrDiscPremium" id="loadOrDiscPremium" cssClass="form-control "  disabled="%{quoteStatus=='RA' && #session.user1 != 'admin'?'true':'false'}" onkeyup="checkDecimals4_2(this);getTotalPremium(this.form);" maxlength="11" cssStyle="text-align:right;width:100%;"/>
														                            <s:if test='"+".equalsIgnoreCase(sign)'>
														                            	<s:set var="preAmt" value="%{@java.lang.Double@parseDouble(#attr.preAmt)+@java.lang.Double@parseDouble(loadOrDiscPremium)}" scope="page"/>
														                            </s:if>
														                            <s:else>
														                            	<s:set var="preAmt" value="%{@java.lang.Double@parseDouble(#attr.preAmt)-@java.lang.Double@parseDouble(loadOrDiscPremium)}" scope="page"/>
														                            </s:else>
									                                            </label>
									                                        </div>
									                                    </div>
									                                </li>
								                                </s:if>
								                                <li class="list-group-item">
								                                    <div class="row">
								                                        <div class="col-md-9 col-9">
								                                            <label class="LabelHeading"><s:text name="motor.policyFee"/>  [<s:property value="currencyType"/>]</label>
								                                        </div>
								                                       
								                                        <div class="col-md-3 col-3 text-center">
								                                            <label class="labelValues"><s:textfield name="policyFee" id="policyFee" cssClass="form-control "  onkeyup="checkDecimals(this);" maxlength="11" cssStyle="text-align:right; width:100%;" readonly="true"/></label>
								                                        </div>
								                                    </div>
								                                </li>
								                                <li class="list-group-item">
								                                    <div class="row">
								                                        <div class="col-md-9 col-9">
								                                            <label class="LabelHeading"><s:text name="motor.totalPremiumPayable" />  [<s:property value="currencyType"/>]</label>
								                                        </div>
								                                        
								                                        <div class="col-md-3 col-3 text-center">
								                                            <label class="labelValues"><s:set var="totPremium" value="%{getText('{0,number,0.00}',{@java.lang.Double@parseDouble(#attr.preAmt)+@java.lang.Double@parseDouble(policyFee)})}"/>
																				<s:textfield name="totalPremium" id="totalPremium" cssClass="form-control "  value="%{#totPremium}" onkeyup="checkDecimals(this);" maxlength="11" cssStyle="text-align:right; width:100%;" readonly="true"/>
																			</label>
								                                        </div>
								                                    </div>
								                                </li>
								                               <s:if test='#session.user1 == "admin" || (!"".equals(adminRemarks)&&(adminRemarks!=null)&& #session.user1 != "admin")'>
								                                	<div class="row">
								                                        <div class="col-md-6 col-6">
								                                            <label class="LabelHeading"><s:text name="motor.specialCondition"/></label>
								                                        </div>
								                                       
								                                        <div class="col-md-6 col-6 text-center">
								                                            <label class="labelValues">
								                                            	<s:textarea name="adminRemarks" onkeyup="textLimit(this,'470')"  cssStyle="width:100%;" cols="50" rows="3" readonly="%{quoteStatus=='RA' && #session.user1 != 'admin'?'true':'false'}"/>
								                                            </label>
								                                        </div>
								                                    </div>
																	
																</s:if> --%>
								                            </ul>
								                        </div>
							                        </div>
										        </div>
										      </div>
										    </div>
										</div>
										
										
			       						<br class="clear"/>
			       					</div>
			       				</div>
			       			</div>
						</div>
					</div>
				</div>
			</div><br>
			<div class="Card_Parent adminClass">
	            <div class="card card-5" >
					<div class="row">
						<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
							<h3><s:text name="motor.referralStatus" /></h3><hr>
							<div class="p-3"><label class="LabelHeading"><b><s:text name="motor.referralStatus" /></b></label>
		        			<div class="panel-body">
		        				<s:radio list="#{'Y':'Accept','N':'Reject','A':'Pending'}" name="adminRefStatus" value="%{adminRefStatus==null?'A':adminRefStatus}" />
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
     												<s:textarea name="referralComments" id="referralComments" cssClass="inputBoxA tooltipContent"  cssStyle="width: 100%;" onkeyup="textLimit(this,'200')"/>
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
				<s:if test='#session.user1 != "admin" && !("Y".equals(#session.Referal))'>
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
       												<s:submit type="button" value="Attach Documents" onclick="return uploadDocuments('%{vehicleIdList[#status.index]}');" cssClass="btn btn-sm btn-danger tooltipContent" />
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
			<s:if test=' #session.user1 != "admin" && !("Y".equals(#session.Referal))'>
				<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
					<s:include value="/pages/payment/paymentInfo.jsp"/>
				</div>
	        </s:if>
			<div class="row mt-5">
					<s:if test='#session.user1 != "admin"'>
						<s:if test='quoteStatus=="RA" || (endTypeId!=null && !"".equalsIgnoreCase(endTypeId)) || "2".equals(premiumType)'>
							<div class="col-lg-2 col-md-3 offset-md-3 offset-lg-4 mb-3">
								<input type="button" name="Submit1" class="btn btn-danger btn-block" value="Back" onclick="getBack('newQuote');" />
							</div>
						</s:if>
						<s:else>
							<!--  <input type="button" name="Submit1" class="btn btn-sm btn-danger" value="Back" onclick="getBack('showSummarry');" />-->
							<div class="col-lg-2 col-md-3 offset-md-3 offset-lg-4 mb-3">
								<input type="button" name="Submit1" class="btn btn-danger btn-block" value="Back" onclick="getBack('newQuote');" />
							</div>
						</s:else>
						<%--<input type="button" name="Submit2" class="btn" value="Save" onclick="this.form.actionType.value='getSave';this.form.submit();" />
						                  &nbsp;--%>
					</s:if>
					<s:else>
						<%--
						<s:submit type="button" name="close"  key="Back" cssClass="btn btn-sm btn-danger" onclick="getBack('editCovRate')"/>
						--%>
						<div class="col-lg-2 col-md-3 offset-md-3 offset-lg-4 mb-3">
							<input type="button" name="Submit1" class="btn btn-danger btn-block" value="Back" onclick="getBack('newQuote');" />
						</div>
					</s:else>
						<div class="col-lg-2 col-md-3  mb-3">
							<!-- <input type="button" name="Submit3" class="btn btn-success btn-block" value="Proceed" onclick="this.form.actionType.value='getQuote';disableForm(this.form,false,'');this.form.submit();"/> -->
							<button type="button" name="Submit3" class="btn btn-success btn-block" value="Proceed" onclick="this.form.actionType.value='getQuote';disableForm(this.form,false,'');fnDecision();">Proceed</button>
						</div>
					<s:hidden name="applicationNo"/>
					<s:hidden name="quoteNo"/>
					<s:hidden name="quoteStatus"/>
					<%--<s:hidden name="referralMsg"/>--%>
					<s:hidden name="actionType" />
					<s:hidden name="display" />
					<s:hidden name="endTypeId"/>
					<s:hidden name="policyNo"/>
	                <s:hidden name="brokerCompany"/>
	                <s:hidden name="custName"/>
	                <s:hidden name="vehicleId"/>
	                <s:hidden name="currencyType"/>
			</div>
			</div>
	       	<%--</s:if>	--%>
        </s:form>
    </s:elseif>
    <s:elseif test="'showQuoteInfo'.equalsIgnoreCase(display)">
		<s:form id="motor" name="motor" method="post"  action="getGeratePolicyMotor.action" theme="simple">
		<div class="table1">
			<div class="tablerow">
				<div class="panel panel-primary">
        			<div class="panel-heading txtB">
						<s:text name="motor.quoteInfo" />
        			</div>
        			<div class="panel-body">
        				<div class="boxcontent" align="center">
        					<div style="color:red;font-size: 15px;"><b><s:text name="motor.refInfo"/> Saved. Your Quote No is : <s:property value="quoteNo"/></b></div>
        				</div>
        			</div>
        		</div>
			</div>
			<div class="tablerow">
				<div class="boxcontent" align="center">
					<input type="button" name="Submit" class="btn btn-sm btn-success" value="Proceed" onclick="getBack('home');"/>
				</div>
			</div>
		</div>
		</s:form>
		</s:elseif>
		<s:elseif test="'showRefInfo'.equalsIgnoreCase(display)">
		<s:form id="motor" name="motor" method="post"  action="getGeratePolicyMotor.action"  theme="simple">
		<div class="table1">
			<div class="container PolicyPage">
			<div class="tablerow">
			<div class="Card_Parent mt-4">
	            <div class="card" style="padding: 12px 20px 14px 13px;">
					<h3><s:text name="quotation.quoteInfo" /></h3><hr>
		       			<div class="panel-body">
		       				<div class="boxcontent" align="center">
		       					<div style="color:red;font-size: 15px;"><b><s:text name="motor.refInfo"/> <s:property value="referralMsg"/></b></div>
		       				</div>
		       			</div>
	       			</div>
       			</div>
			</div>
			</div>
			<div class="tablerow mt-5">
				<div class="boxcontent" align="center">
					<input type="button" name="Submit" class="btn btn-sm btn-success" value="Proceed" onclick="getBack('adminHome');"/>
				</div>
			</div>
		</div>
		</s:form>
		</s:elseif>
		<s:elseif test="'newQuoteOtpLogin'.equalsIgnoreCase(display)">
			<s:form id="motor" name="motor" method="post"  theme="simple">
				<div class="panel panel-primary">
					<div class="panel-body">
						<div class="row">
							<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
								<s:if test="hasActionErrors()">
									<font color="red" style="list-style:none; "><s:actionerror cssStyle="list-style:none;"/></font>
								</s:if>
								<s:if test="hasActionMessages()">
									<s:actionmessage cssStyle="list-style:none; color:green;"/>
								</s:if>
							</div>
						</div>
						<div class="row">
							<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
								<div class="panel panel-primary">
									<div class="panel-heading">
										Registered User
									</div>
									<div class="panel-body">
										<div class="row">
											<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
						  						<div class="text"><s:text name="Registered Mobile No"/><font color="red">*</font></div>
						  						<div class="tbox">
						  							<s:textfield name="mobileNum" id="mobileNum" cssClass="inputBox tooltipContent empyCustDetails"  maxLength="10" onkeyup="checkNumbers(this);" disabled="#disable1"/>
						  						</div>
						  					</div>
						  					<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4" align="center" style="margin-top: 17px;">
							            		<button type="button" class="btn btn-sm btn-success" onclick="funSubmitOtp('motor','registered')">Submit</button>
											</div>
										</div>
									</div>
								</div>
								<div class="panel panel-primary">
									<div class="panel-heading">
										New User
									</div>
									<div class="panel-body">
										<s:include value="/pages/customer/customerDetailsMotor.jsp"/>
										<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" align="center">
											<button type="button" class="btn btn-sm btn-success" onclick="funSubmitOtp('motor','new')">Submit</button>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" align="center">
			            		<s:submit name="Submit" type="submit" cssClass="btn btn-sm btn-danger" value='Back' onclick="funBackQuote()"/>
							</div>
						</div>
					</div>
				</div>
				<s:hidden name="applicationNo"/>
				<s:hidden name="quoteNo"/>
				<s:hidden name="product" id="product"/>
				<s:hidden name="policyStartDate" id="policyStartDate"/>
				<s:hidden name="policyEndDate" id="policyEndDate"/>
				<s:hidden name="quoteDate" id="quoteDate"/>
				<s:hidden name="currencyType" id="currencyType"/>
				<s:hidden name="branchCode" id="branchCode"/>
				<!--<s:hidden name="customerId" id="customerId"/>-->
				<s:hidden name="policyType" id="policyType"/>
				<s:hidden name="optionalCovers1"/>
				<s:hidden name="optionalCovers2"/>
				<s:hidden name="optionalCovers3"/>
			</s:form>
		</s:elseif>
	</div>
	<!--<s:if test='"User".equalsIgnoreCase(userType)||"B2C".equalsIgnoreCase(#session.LoginType)'>
		<div class="col-xs-12 col-sm-12 col-md-3 col-lg-3">
			<div class="row">
				<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
					<s:include value="/pages/motor/sideBar.jsp"/>
				</div>
			</div>		
		</div>
	</s:if>
--></div>
<script  type="text/javascript">
<s:if test='"Y".equals(paymentYN) && #session.user1 != "admin"'>
	<s:if test='"Y".equalsIgnoreCase(generatePolicyYN) || "viewPayment".equalsIgnoreCase(mode)'>
		getPaymentModeDetails('<s:property value="modeOfPayment"/>');
	</s:if>
</s:if>

 function myChangeFunction(myId) {
	if("basePrem[0]"==myId){
		var val=document.getElementById('basePrem[0]').value;
		try{document.getElementById("basePrem[103]").value=val;}catch(err) {console.error(err);}
		try{document.getElementById("basePrem[104]").value=val;}catch(err) {console.error(err);}
		
	}
  }
  
try {
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
		var val = "?seatingCapacity=" + $("#seatingCapacity").val()
					+ "&vehicleUsage=" + $("#vehicleUsage").val()
					+ "&typeBody=" + $("#typeBody").val();
		getAjaxModel(frm,val,'deductibleAjax');
	}
	function getNCBAjax(frm) {
		var val = "?vehicleUsage=" + $("#vehicleUsage").val();
		getAjaxModel(frm,val,'ncbAjax');
	}
	function vehicleTypeDetailsAjax(frm) {
		$("#modalErrorSpan").html("");
		var val = "?make=" + $("#make").val()
					+ "&model=" + $("#model").val()
					+ "&typeBody=" + $("#typeBody").val()
					+ "&vehicleUsage=" + $("#vehicleUsage").val()
					+ "&seatingCapacity=" + $("#seatingCapacity").val()
					+ "&deleteVehicleId=" + $("#rvehicleId").val()
					+ "&applicationNo=<s:property value='applicationNo'/>";
		getAjaxModel(frm,val,'vehicleTypeDetailsAjax');
		return false;
	}
	function setModalVehicleDetails(modalSelectId,modalTypeBody,modalVehicleUsage,modalTypeBodyName,modalVehicleUsageName) {
		$("#modalSelectId").val(modalSelectId);
		$("#modalTypeBody").val(modalTypeBody);
		$("#modalVehicleUsage").val(modalVehicleUsage);
		$("#modalTypeBodyName").val(modalTypeBodyName);
		$("#modalVehicleUsageName").val(modalVehicleUsageName);
	}
	function setVehicleTypeDetails(frm) {
		var error = "";
		var seatingCapacity = $("#tSeatingCapacity_" + $("#modalSelectId").val()).val();
		if($("#modalSelectId").val()=="") {
			error = "Please select Vehicle Type";
		} else if(seatingCapacity=="") {
			error = "Please Enter Seating Capacity";
		} else if(parseInt(seatingCapacity) == NaN) {
			error = "Please Enter valid Seating Capacity";
		}
		$("#modalErrorSpan").html(error);
		if(error=="") {
			/*var bodyId =$("#modalTypeBody").val();
			if(bodyId==9 || bodyId==27){
				$( '#seatingCapacityBlock' ).attr('style','display:none;');
			}else{
				$( '#seatingCapacityBlock' ).attr('style','display:block;');
			}*/
			$("#typeBody").val($("#modalTypeBody").val());
			$("#vehicleUsage").val($("#modalVehicleUsage").val());
			$("#seatingCapacity").val($("#tSeatingCapacity_" + $("#modalSelectId").val()).val());
			$("#typeBodyDiv").html($("#modalTypeBodyName").val());
			$("#vehicleUsageDiv").html($("#modalVehicleUsageName").val());
			$("#seatingCapacityDiv").html($("#seatingCapacity").val());
			$("#typeBodyName").val($("#modalTypeBodyName").val());
			$("#vehicleUsageName").val($("#modalVehicleUsageName").val());
			$('#vehicleTypeDetails').modal('toggle');
			getDeductibleAjax(frm);
			getNCBAjax(frm);
		}
	}
	function removeVehicleTypeDetails() {
		$("#typeBody").val("");
		$("#vehicleUsage").val("");
		$("#seatingCapacity").val("");
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
			 }   
			 else
			 {  
			 	document.getElementById('claimAmount').value='';
			 	document.getElementById('claimAmount').readOnly=true;
			 } 
    }
    function toggleNCB(value)
    {
   		 if(value=="" || value=="0")
		 {
		 	document.getElementById('prevPolicyNoList').value='';
		 	document.getElementById('prevExpiryDateList').value='';
			document.getElementById('prevInsCompanyIdList').value='';
		 $(".prevInfo").hide();
		 
		 }   
		 else
		 {  
		  $(".prevInfo").show();
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
				document.motor.action ='${pageContext.request.contextPath}/editQuoteMotor.action';
		}else if(page=='editCovRate'){
			document.motor.action ='${pageContext.request.contextPath}/editCovRateMotor.action';
		}else if(page=='home'){
			if('admin'=='<s:property value="#session.user1"/>' || 'Y'=='<s:property value="#session.Referal"/>'){
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
					document.motor.action ='${pageContext.request.contextPath}/initReport.action?menuType=<s:property value="quoteStatus"/>&loginId=<s:property value="loginId"/>';
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
		}
		document.motor.submit();
	}
	
	function fnVehiclesubmit(frm, type, vehicleId) {
		if("delete"==type) {
			document.getElementById('deleteVehicleId').value = vehicleId;
			document.motor.action = "${pageContext.request.contextPath}/deleteAddVehicleMotor";
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
	/*$(function() {
		try {
			$('.datePicker').datepicker({
				todayHighlight: true,
				format: "dd/mm/yyyy"
			}).on('changeDate', function(e){
			    $(this).datepicker('hide');			    
			});
		} catch(err) {console.error(err);}
	});*/
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

</script>
<SCRIPT type="text/javascript">
<s:if test="'newQuote'.equalsIgnoreCase(display)">
toggleClaimDetails('<s:property value="claimYNIdList[0]"/>');
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
			$('.prevExpiryDateList').datepicker({
				todayHighlight: true,
				format: "dd/mm/yyyy"
			}).on('changeDate', function(e){
			    $(this).datepicker('hide');			    
			});
		} catch(err) {console.error(err);}
	});
	<s:if test='!"policyInfo".equalsIgnoreCase(display) && #session.user1 != "admin"'>
		toggleNCB('<s:property value="noClaimBonusIdList[0]"/>');
		toggleLeasedYN('<s:property value="leasedYNIdList[0]"/>','');
	</s:if>
	function toggleCompanyRegistration(val) {
		if(val=="CORPORATE") {
			document.getElementById('companyRegNo').disabled = false;
		}
		else {
			document.getElementById('companyRegNo').value = "";
			document.getElementById('companyRegNo').disabled = true;
		}
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
	
	function fnDecision(){
		var adminRefStatus=$("input[name='adminRefStatus']:checked").val();
		//alert(adminRefStatus);
		if('admin'=='<s:property value="userType"/>'){
			if('Y'==adminRefStatus){var conf=confirm("Do you want to Proceed this quote to Referral Accept ?"); }
			if('N'==adminRefStatus){var conf=confirm("Do you want to Proceed this quote to Referral Reject ?"); }
			if('A'==adminRefStatus){var conf=confirm("Do you want to Proceed this quote to Referral Pending ?");}
			if(conf){
				document.motor.action="getGeratePolicyMotor.action";
				document.motor.submit();
				}
		}
		else {
			document.motor.action="getGeratePolicyMotor.action";
			document.motor.submit();
			
		}
		
	}
	function fnClausesAddAjax(frm, id) {
		var val = '?productId=<s:property value="productId"/>'
			+ '&branchCode=<s:property value="branchCode"/>'
			+ '&quoteNo=<s:property value="quoteNo"/>'
			+ '&applicationNo=<s:property value="applicationNo"/>';
		postRequest('${pageContext.request.contextPath}/clausesAddAjaxMotor.action'+val, id);
	}
	function fnDeductibleAddAjax(frm, id) {
		var val = '?productId=<s:property value="productId"/>'
			+ '&branchCode=<s:property value="branchCode"/>'
			+ '&quoteNo=<s:property value="quoteNo"/>'
			+ '&applicationNo=<s:property value="applicationNo"/>';
		postRequest('${pageContext.request.contextPath}/deductibleEditAjaxMotor.action'+val, id);
	}
</SCRIPT>
</body>
</html>