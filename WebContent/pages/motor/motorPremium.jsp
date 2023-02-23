<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ page import="java.util.List,java.util.ArrayList,java.util.Map,java.util.HashMap,java.util.Collections"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page import="nl.captcha.Captcha"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<meta http-equiv='cache-control' content='no-cache'>
	<meta http-equiv='expires' content='0'>
	<meta http-equiv='pragma' content='no-cache'>
	<script type="text/javascript">
	
	 </script>
	<style type="text/css">
	.inputBox {
          outline :none !important;
          border :none !important;
        }
        .inputBox : focus {
          outline :none !important;
          border :none !important;
        }
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
		.PolicyPage .instalmentBorder {
            border: 2px solid white;
            padding: 10px;
            border-radius: 4px;
            background-color: white;
            box-shadow: 0 1px 1px rgba(0, 0, 0, 0.25),
                0 2px 2px rgba(0, 0, 0, 0.20),
                0 4px 4px rgba(0, 0, 0, 0.15),
                0 8px 8px rgba(0, 0, 0, 0.10),
                0 16px 16px rgba(0, 0, 0, 0.05);
        }

@media only screen and (max-width: 992px) {
  
  /* Force table to not be like tables anymore */
  .mtrVehDetail table,
  .mtrVehDetail thead,
  .mtrVehDetail tbody,
  .mtrVehDetail th,
  .mtrVehDetail td,
  .mtrVehDetail tr {
    display: block;
  }
  /* Hide table headers (but not display: none;, for accessibility) */
  .mtrVehDetail thead tr,
  .mtrVehDetail tfoot tr {
    position: absolute;
    top: -9999px;
    left: -9999px;
  }
  .mtrVehDetail td {
    /* Behave  like a "row" */
    border: none;
    border-bottom: 1px solid #eee;
    position: relative;
    padding-left: 50% !important;
  }
  .mtrVehDetail td:before {
    /* Now like a table header */
    position: absolute;
    /* Top/left values mimic padding */
    top: 6px;
    left: 6px;
    width: 45%;
    padding-right: 10px;
    white-space: nowrap;
  }
  
  .mtrVehDetail .table td:nth-child(1) {
      background: #ccc;
      height: 100%;
      top: 0;
      left: 0;
      font-weight: bold;
  }
  /*
	Label the data
	*/
  .mtrVehDetail td:nth-of-type(1):before {
    content: "S.No";
  }
  .mtrVehDetail td:nth-of-type(2):before {
    content: "Vehicle Usage";
  }
  .mtrVehDetail td:nth-of-type(3):before {
    content: "Make";
  }
  .mtrVehDetail td:nth-of-type(4):before {
    content: "Model";
  }
  .mtrVehDetail td:nth-of-type(5):before {
    content: "Type Of Body";
  }
  .mtrVehDetail td:nth-of-type(6):before {
    content: "Vehicle Value";
  }
  .mtrVehDetail td:nth-of-type(7):before {
    content: "Premium Amount";
  }
  .mtrVehDetail td:nth-of-type(8):before {
    content: "Documents";
  }
  
  .mtrVehDetail .dataTables_length {
    display: none;
  }
  }
 

	</style>
</head>
<body>
<s:set var="disable1" value="%{((endTypeId!=null && endTypeId.length() > 0)||(quoteStatus=='RA'))}"/>
<s:set var="endtDisable" value="%{endTypeId!=null && endTypeId.length() > 0}"/>
	
<s:form id="motor" name="motor" method="post" theme="simple" action="quotationMotor.action">
<div id="loading" style="width:100%">
   <img id="loading-image" src="${pageContext.request.contextPath}/images/madisonSymbolLogo.png"/>
</div>
	<s:hidden name="display" />
	<s:hidden name="actionType" />
	<s:hidden name="fleetNo" />
	<s:hidden name="applicationNo" id="applicationNo"/>
	<s:hidden name="quoteNo" />
	<s:hidden name="quoteStatus"/>
	<%-- <s:hidden name="referralMsg"/> --%>
	<s:hidden name="endTypeId"/>
	<s:hidden name="policyNo"/>
	<s:hidden name="brokerCompany"/>
	<s:hidden name="custName"/>
	<s:hidden name="vehicleId"/>
	<s:hidden name="isVehicleEdit"/>
	<s:hidden name="policyType" id="policyType"/>
	<%--<s:hidden name="companyRegNo" id="companyRegNo"></s:hidden> --%>
	<s:hidden name="customerId"  id="customerId"/>
	<s:hidden name="isClaimDtl"  id="isClaimDtl"/>
	<s:hidden name="reqFrom"  id="reqFrom"/>
	<s:hidden name="policyTypeDesc"  id="policyTypeDesc"/>
	
	<s:if test="'policyInfo'.equalsIgnoreCase(display)">
        <s:set var="multiVehicleDtls" value="%{multiVehicleDetails}"/>
        <s:set var="totPremium" value="0"/>
		
		<div class="container PolicyPage">
			<div class="row">
				<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
					<span style="color:red;"><s:actionerror/></span>
				</div>
			</div>
	        <div class="Card_Parent PolicyInformation">
	            <div class="card card-5">
	                
	                <div class="row">
	                	<div class="col-md-12">
	                		<h3>Quote Information</h3><hr>
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
			                        <label class="labelValues"><s:property value="policyName"/></label>
			                    </div>
			                </div>
	                	</div>
	                </div>
	                <s:hidden name="policyName"/>
	                
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
	        </div>
	        <br>
	       <%--  <s:iterator value="policyInformation"> --%>
			<div class="row">
				<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
					<div class="vehDtl">
					  	<div class="Card_Parent ">
				            <div class="">
				            <div class="panel panel-heading">
					            <div class="VehicleTableheading">
							       <div class="row">
							          <div class="col-lg-6 col-md-6">
							            <h3>Vehicle Details</h3>
							          </div>
							          <div class="col-lg-2 offset-md-3 offset-lg-4 col-md-3">
							            <%-- <a class="btn btn-primary btn-block bordernone" data-refresh="true" data-backdrop="static" data-keyboard="false" data-toggle="modal" data-target="#myModal" onclick="return fnVehicleEdit(this.form,'add','<s:property value="#view.VEHICLE_ID" />','vehicleEditAjax');">
							              Add Vehicle
							            </a> --%>
							            <s:submit name="Submit2" type="button" cssClass="btn btn-sm btn-info" value="Print Quote" onclick="return getPopup('print4Schedule.pdfSchedule?policynumber=%{QUOTE_NO}&loginid=%{LOGIN_ID}&printoption=%{PDF_PRE_SHOW_STATUS}&bankerOption=%{PDF_BANKER_STATUS}&displayText=QUOTE&displayMode=draftMode&bankerAssuredOption=%{PDF_BANKER_ASSURED_STATUS}')"/>
							          </div>
							       </div>
							    </div><hr>
				            </div>
				            <div class="panel-body" style="padding-left: 15px;padding-right: 15px">
				            <div class="row">
				            	<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 mtrVehDetail">
				            		<s:set name="multiVehicleDtls" value="%{vehiclinfoList}"/>
									<%-- <s:if test='applicationNo !=null && applicationNo.length() > 0 && #multiVehicleDtls.size()>0'> --%>
										<table class="table table-bordered table-hover" cellspacing="0" width="100%" id="tadaTableNew">
											<thead class="bluecolortable" >
												<tr>
										          <th>S.No</th>
										          <th>Vehicle Usage</th>
										          <th>Make</th>
										          <th>Model</th>
										          <th>Type Of Body</th>
										          <th>Vehicle Value</th>
										          <th>Premium Amount</th>
										          <th>Documents</th>
										          <s:if test='"RSAIssuer".equalsIgnoreCase(#session.usertype)'>
										          	<th>Base Rate</th>
										          	<th>Calculate</th>
										          </s:if>
										        </tr>
										    </thead>
										    <tbody class="rowevencolor">
											    <s:iterator value="multiVehicleDtls" var="view" status="status">
													<tr valign="middle">
														<td align="center"><s:property value="#status.count" /></td>
														<td align="center"><s:property value="#view.VehicleUsage" /></td>
														<td align="center"><s:property value="#view.Make" /></td>					
														<td align="center"><s:property value="#view.Model" default=" " /> </td>
														<td align="center"><s:property value="#view.BodyType" /></td>
														<td align="center"><s:property value="#view.VehicleValue" /></td>
														<td align="center"><s:property value="#view.Premium" /></td>
														<%-- <td align="center"> <s:property value="getText('{0,number,#,##0.00}',{#view.VehicleValue})"/> </td> --%>
														<%-- <td align="center"><s:property value="getText('{0,number,#,##0.00}',{#view.Premium})"/></td> --%>
														<td align="center"><s:submit type="button" value="Attach" onclick="return uploadDocumentsV1(%{#view.VehicleId});" cssClass="btn btn-sm btn-danger btn-block" /></td>
														<s:if test='"RSAIssuer".equalsIgnoreCase(#session.usertype)'>
										          			<td align="center"><s:textfield name="baseRateVeh[%{vehicleIdList[#status.index]}]" value="%{#view.BASE_RATE}" class="form-control" maxlength="7" onkeyup="checkNumbers(this);"/></td>
										          			<td align="center"><s:submit type="button" value="Calculate" onclick="return premCalculate('%{vehicleIdList[#status.index]}');" cssClass="btn btn-sm btn-primary btn-block" /></td>
										         		</s:if>
											        </tr>
										        </s:iterator>
										    </tbody>
										    </table>
										    <s:hidden name="deleteVehicleId" id="deleteVehicleId"/>
											<s:hidden name="rateVehicleId" id="rateVehicleId"/>
											<s:hidden name="vehicleType" id="vehicleType"/>
									   <br><br>
									     
			    						</div>
			    					</div>
			    				</div>
          					</div>
          				</div>
          			</div>
          		</div>
          	</div>
	       <%--  </s:iterator> --%>
	        <div class="Card_Parent mt-4">
	            <div class="card card-1">
	                <h3>Policy Holder Information</h3>
	                <hr>
	                <div class="row mt-3">
	                    <div class="col-md-12">
	                        <div class="row">
	                            <div class="col-md-6 customerType">
	                                <ul class="list-group list-group-horizontal">
	                                    <li class="list-group-item ">Customer Type</li>
	                                     <%-- <s:if test="'RA'.equalsIgnoreCase(quoteStatus)"> --%>
	                                    	<li class="list-group-item " id="Individual">Individual</li>
	                                    	<li class="list-group-item " id="Corporate">Corporate</li>
	                                     <%-- </s:if>
	                                    <s:else>
	                                    	<li class="list-group-item " id="Individual" onclick="selectCustomerType('INDIVIDUAL');toggleCompanyRegistration('INDIVIDUAL');">Individual</li>
	                                    	<li class="list-group-item " id="Corporate" onclick="selectCustomerType('CORPORATE');toggleCompanyRegistration('CORPORATE');">Corporate</li>
	                                    </s:else> --%>
	                                </ul>
	                            </div>
	                            <div class="col-md-6 customerType">
	                            	<s:set name="groupId" value=""/>
									<s:set var="preAmt" value="0.0" scope="page"/>
									<s:iterator value="premiumInfo" var="prInfo" status="stat">
	                                	<s:set name="groupId" value="%{#prInfo.GROUP_ID}"/>
	                                	<s:set var="preAmt" value="%{@java.lang.Double@parseDouble(#attr.preAmt)+@java.lang.Double@parseDouble(#prInfo.PREMIUM)}" scope="page"/>
	                                </s:iterator>
	                                <%-- <s:if test='#session.user1 == "admin" || quoteStatus == "RA"'> --%>
		                                <!-- <li class="list-group-item"> -->
		                                    <div class="row">
					                            <s:if test='"+".equalsIgnoreCase(sign)'>
					                            	<s:set var="preAmt" value="%{@java.lang.Double@parseDouble(#attr.preAmt)+@java.lang.Double@parseDouble(loadOrDiscPremium)}" scope="page"/>
					                            </s:if>
					                            <s:else>
					                            	<s:set var="preAmt" value="%{@java.lang.Double@parseDouble(#attr.preAmt)-@java.lang.Double@parseDouble(loadOrDiscPremium)}" scope="page"/>
					                            </s:else>
		                                    </div>
		                                <!-- </li>
		                                <hr> -->
	                                <%-- </s:if> --%>
                                    <label class="LabelHeading"><s:text name="motor.totalPremiumPayable" />  [<s:property value="currencyType"/>]</label>
                                    <label class="LabelHeading"><s:set var="totPremium" value="%{getText('{0,number,0.00}',{@java.lang.Double@parseDouble(#attr.preAmt)+@java.lang.Double@parseDouble(policyFee)})}"/>
										<%-- <s:textfield name="totalPremium" id="totalPremium" cssClass="inputBox "  value="%{#totPremium}" onkeyup="checkDecimals(this);" maxlength="11" cssStyle="text-align:right; width:100%;" readonly="true"/> --%>
										:&nbsp;&nbsp;<b><s:property value="%{#prInfo.OverAllPremium}"/></b>&nbsp;&nbsp;
									</label>
									<button type="button" class="btn btn-sm btn-primary" data-toggle="modal" data-target="#myModalPrem" >View More</button>
	                            </div>
	                        </div> 
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
	  									<s:select name="title" id="title" list="titleList" listKey="Code" listValue="Description" class="form-control border "  disabled="#disable1"/>
	  						
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
	                                    <s:textfield name="custdob" id="custdob" class="form-control border datePicker L empyCustDetails"  disabled="#endtDisable" readonly="true"/>
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
	                                    <s:select name="city" id="city" list="cityList" headerKey="" headerValue="---Select City---" listKey="Code" listValue="Description" class="form-control empyCustDetails"  disabled="#endtDisable"/>
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
	                            
	                            <%-- <div class="col-md-6">
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
	                            </div> --%>
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
	                            	<label class="labelHeader"><s:text name="customer.companyRegNo"  /><font color="red">*</font></label>
	                                <div class="input-group mb-3">
	                                    <div class="input-group-prepend">
	                                        <span class="input-group-text border border-right-0"><i class="fas fa-passport"></i></span>
	                                    </div>
	                                    <s:textfield name="companyRegNo" id="companyRegNo" class="form-control border empyCustDetails"  maxLength="10" disabled='%{(customerType==null||"".equals(customerType)||#disable1||"INDIVIDUAL".equals(customerType))?"true":"false"}'/>
	                                </div>
	                            </div>
	                            <s:hidden name="customerType" id="customerType"/>
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
							          	<div class="premiumBorder mt-5">
				                            <ul class="list-group QuotationDetails">
				                                <li class="list-group-item list-group-item-primary">
				                                    <div class="row">
				                                        <div class="col-md-6 col-6">
				                                            <label class="LabelHeading">Description</label>
				                                        </div>
				                                        <div class="col-md-3 col-3 text-middle">
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
					                                	<li class="list-group-item">
						                                    <div class="row">
						                                        <div class="col-md-6 col-6">
						                                            <label class="LabelHeading">Base Premium</label>
						                                        </div>
						                                        <div class="col-md-3 col-3 text-middle">
				                                            		<s:property  value="%{#prInfo.BasePremiumRate}"/>
				                                       			 </div>
						                                        <div class="col-md-3 col-3" align="right">
						                                        	<s:property  value="%{#prInfo.BasePremium}"/>
						                                        </div>
						                                    </div>
						                                </li>
						                                <hr>
						                                <li class="list-group-item">
						                                    <div class="row">
						                                        <div class="col-md-6 col-6">
						                                            <label class="LabelHeading">Electrical AccesPremium</label>
						                                        </div>
						                                        <div class="col-md-3 col-3">
				                                            		<s:property  value="%{#prInfo.BaseRate}"/>
				                                       			 </div>
						                                        <div class="col-md-3 col-3" align="right">
						                                        	<s:property  value="%{#prInfo.ElectricalAccesPremium}"/>
						                                        </div>
						                                    </div>
						                                </li>
						                                <hr>
						                                <li class="list-group-item">
						                                    <div class="row">
						                                        <div class="col-md-6 col-6">
						                                            <label class="LabelHeading">NonElectrical AccesPremium</label>
						                                        </div>
						                                        <div class="col-md-3 col-3">
				                                            		<s:property  value="%{#prInfo.BaseRate}"/>
				                                       			 </div>
						                                        <div class="col-md-3 col-3" align="right">
						                                        	<s:property  value="%{#prInfo.NonElectricalAccesPremium}"/>
						                                        </div>
						                                    </div>
						                                </li>
						                                <hr>
						                                <li class="list-group-item">
						                                    <div class="row">
						                                        <div class="col-md-6 col-6">
						                                            <label class="LabelHeading">Deductibles</label>
						                                        </div>
						                                        <div class="col-md-3 col-3">
				                                            		<s:property  value="%{#prInfo.DeductiblesRate}"/>
				                                       			 </div>
						                                        <div class="col-md-3 col-3" align="right">
						                                        	<s:property  value="%{#prInfo.Deductibles}"/>
						                                        </div>
						                                    </div>
						                                </li>
						                                <hr>
						                                <li class="list-group-item">
						                                    <div class="row">
						                                        <div class="col-md-6 col-6">
						                                            <label class="LabelHeading">Minimum Premium Adjustment</label>
						                                        </div>
						                                        <div class="col-md-3 col-3">
				                                            		<s:property  value="%{#prInfo.BaseRate}"/>
				                                       			 </div>
						                                        <div class="col-md-3 col-3" align="right">
						                                        	<s:property  value="%{#prInfo.MinimumPremiumAdjust}"/>
						                                        </div>
						                                    </div>
						                                </li>
						                                <hr>
				                                </s:iterator>
				                                
				                                <li class="list-group-item">
				                                    <div class="row">
				                                        <div class="col-md-9 col-9">
				                                            <label class="LabelHeading"><s:text name="motor.Premium"/>  [<s:property value="currencyType"/>]</label>
				                                        </div>
				                                        <div class="col-md-3 col-3 text-center">
				                                            <label class="labelValues"><s:textfield name="premium" id="premium" cssClass="inputBox "  value="%{#prInfo.PremiumBeforTax}" onkeyup="checkDecimals(this);" maxlength="11" cssStyle="text-align:right; width:100%;" readonly="true"/></label>
				                                        </div>
				                                    </div>
				                                </li>
				                                <hr>
				                               <%-- <s:if test='#session.user1 == "admin" || quoteStatus == "RA"'> --%>
					                                <li class="list-group-item">
					                                    <div class="row">
					                                        <div class="col-md-6 col-6">
					                                            <label class="LabelHeading"><s:text name="motor.loadingOrDiscountPremium"/>  [<s:property value="currencyType"/>]</label>
					                                        </div>
					                                        <div class="col-md-3 col-3">
				                                            	<label><s:select list="#{'+':'+','-':'-'}" name="sign" onchange="getTotalPremium(this.form)" disabled="%{quoteStatus=='RA' && #session.user1 != 'admin'?'true':'true'}" cssClass="inputBox" cssStyle="width:100%;"/>&nbsp;&nbsp;&nbsp;</label>
					                                        </div>
					                                        <div class="col-md-3 col-3 text-center">
					                                            <label class="labelValues">
					                                            	<s:textfield name="loadOrDiscPremium" id="loadOrDiscPremium" cssClass="inputBox "  disabled="%{quoteStatus=='RA' && #session.user1 != 'admin'?'true':'true'}" onkeyup="checkDecimals4_2(this);getTotalPremium(this.form);" maxlength="11" cssStyle="text-align:right;width:100%;"/>
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
					                                <hr>
				                                <%-- </s:if> --%>
				                                <li class="list-group-item">
				                                    <div class="row">
				                                        <div class="col-md-9 col-9">
				                                            <label class="LabelHeading"><s:text name="motor.policyFee"/>  [<s:property value="currencyType"/>]</label>
				                                        </div>
				                                       
				                                        <div class="col-md-3 col-3 text-center">
				                                            <label class="labelValues"><s:textfield name="policyFee" id="policyFee" value="%{#prInfo.PolicyFees}" cssClass="inputBox "  onkeyup="checkDecimals(this);" maxlength="11" cssStyle="text-align:right; width:100%;" readonly="true"/></label>
				                                        </div>
				                                    </div>
				                                </li>
				                                <hr>
				                                <li class="list-group-item">
				                                    <div class="row">
				                                        <div class="col-md-9 col-9">
				                                            <label class="LabelHeading"><s:text name="motor.totalPremiumPayable" />  [<s:property value="currencyType"/>]</label>
				                                        </div>
				                                        
				                                        <div class="col-md-3 col-3 text-center">
				                                            <label class="labelValues"><s:set var="totPremium" value="%{getText('{0,number,0.00}',{@java.lang.Double@parseDouble(#attr.preAmt)+@java.lang.Double@parseDouble(policyFee)})}"/>
																<s:textfield name="totalPremium" id="totalPremium" cssClass="inputBox "  value="%{#prInfo.OverAllPremium}" onkeyup="checkDecimals(this);" maxlength="11" cssStyle="text-align:right; width:100%;" readonly="true"/>
															</label>
				                                        </div>
				                                    </div>
				                                </li>
				                               <s:if test='#session.user1 == "admin" || (!"".equals(adminRemarks)&&(adminRemarks!=null)&& #session.user1 != "admin")'>
				                                	<hr>
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
													
												</s:if>
				                            </ul>
				                        </div>
			                        </div>
						        </div>
						      </div>
						    </div>
						</div>
	                </div>
	            </div>
	
	        </div>
	        
	        <%-- <s:if test='showReferralYN == "Y" && #session.user1 != "admin"'> --%>
	        <s:if test='#session.user1 != "admin" && #session.usertype !="RSAIssuer" && !"Y".equals(#session.B2cCustYN)'>
		        <br>
		        <div class="Card_Parent PolicyInformation">
		            <div class="card card-5">
		                <h3><s:text name="motor.referalInfo" /></h3>
		                <hr>
		                <%-- <div class="row">
		                    <div class="col-md-5 col-6">
		                        <label class="LabelHeading"><s:text name="motor.policyStartDt"/></label>
		                    </div>
		                    <div class="col-md-5 col-6">
		                        <label class="labelValues"><s:property value="policyStartDate"/></label>
		                    </div>
		                </div>
		                <div class="row">
		                    <div class="col-md-5 col-6">
		                        <label class="LabelHeading"><s:text name="motor.policyEndDt"/></label>
		                    </div>
		                    <div class="col-md-5 col-6">
		                        <label class="labelValues"> <s:property value="policyEndDate"/></label>
		                    </div>
		                </div> --%>
		                <div class="row mt-2">
		                    <div class="col-xs-12 col-sm-12 col-md-7 col-lg-7">
								<div class="text"><s:text name="motor.referralYN"/></div>
								<div class="tbox">
									<s:radio list="#{'Y':'Yes','N':'No'}" name="referralYN" id="referralYN"  onclick="disablePolicyOption(this.value);"/>
								</div>					        						
							</div>
							<div class="col-xs-12 col-sm-12 col-md-5 col-lg-5">
								<div class="text"><s:text name="motor.comments"/></div>
								<div class="tbox">
									<s:textarea name="referralComments" id="referralComments" cssClass="form-control "  cssStyle="width: 100%;" onkeyup="textLimit(this,'2000')"/>
								</div>
							</div>
		                </div>
		            </div>
	        	</div>
	        	<s:hidden name="showReferralYN"/>
        	</s:if>
        	<s:if test='"RSAIssuer".equalsIgnoreCase(#session.usertype)'>
		        <br>
		        <div class="Card_Parent PolicyInformation">
		            <div class="card card-5">
		                <h3><s:text name="Condition Details" /></h3>
		                <hr>
		                <div class="row mt-3">
		                    <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6 mt-2">
								<div class="text">View and Edit Conditions / Deductibles</div>
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
		                </div><br>
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
	        	
        	</s:if>
        	<br>
        	<div class="Card_Parent policyGeneration">
	            <div class="card card-3">
	            	 <s:if test='!"admin".equalsIgnoreCase(userType) && !"creditcontroller".equalsIgnoreCase(userType) && !"surveyor".equalsIgnoreCase(userType) && !"underwriter".equalsIgnoreCase(userType)'>
                		<h3><s:text name="Policy Detail" /></h3>
                		<hr>
                		<div class="row">
		                    <div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
								<label class="labelHeader"><s:text name="motor.policyStartDt"/><s:text name="(DD/MM/YYYY)" /><font color="red">*</font></label>
	                    		<div class="input-group mb-3">
		                            <div class="input-group-prepend">
		                                <span class="input-group-text border border-right-0"><i class="fas fa-calendar-alt"></i></span>
		                            </div>
		                            <s:textfield name="policyStartDate" id="motorPolicyStartDate" class="form-control border datePicker"  disabled="#disable1"/>
		                        </div>		        						
							</div>
							<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4" >
								<label class="labelHeader"><s:text name="motor.policyEndDt"/><font color="red">*</font></label>
	                    		<div class="input-group mb-3">
	                    		<s:textfield name="policyEndDate" cssClass="form-control" value="%{policyEndDate}" disabled="#disable1" readonly="true"/>
		                        </div>
							</div>
							<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4" >
								<label class="labelHeader"><s:text name="Broker Branch"/></label>
	                    		<div class="input-group mb-3">
		                           <s:select name="brokerBranch" id="brokerBranch" list="brokerBranchList" listKey="Code" headerKey="" headerValue="---Select---" listValue="Description" class="form-control border "  disabled="#disable1"/>
		                        </div>
							</div>
                		</div><br>
                		<s:if test='"RSAIssuer".equalsIgnoreCase(#session.usertype)'>
                		<div id="referbroker">
	                	<h3><s:text name="Refer Quote to Broker/User" /></h3>
	                	<hr>
	               			<div id="referbroker">
		                		<div class="row">
									<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6" >
										<div class="text"><b><s:text name="Refer Quote to Broker/User?"/></b></div>
	                                   		<s:radio list="#{'Y':'Yes','N':'No'}" name="referQuoteYN" id="referQuoteYN" value='%{"Y".equals(referQuoteYN)?"Y":"N"}' onchange="togglereferQuote(this.value);" />
									</div>
									<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6" id="brokeruserid" style="display:none;">
										<label class="labelHeader"><s:text name="Broker/User"/></label>
			                    		<div class="input-group mb-3">
				                           <s:select name="brokeruser" id="brokeruser" list="brokerUserList" listKey="CODE" headerKey="" headerValue="---Select---" listValue="CODEDESC" class="form-control border " />
				                        </div>
									</div>
		                		</div>
	                		</div>
	                	</div><br>
	                	</s:if>
						<div id="policyGeneration">
	                	<h3><s:text name="Declaration" /></h3>
	                	<hr>
	               			<div id="policyGeneration">
		                		<div class="row">
				                    <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
										<s:checkbox name="quoteEmailYN" fieldValue="Y" id="quoteEmailYN" onclick="toggleEmailQuote();"/>
										<s:text name="label.email.quote"></s:text>					        						
									</div>
									<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6" id="installmentDiv">
										<div class="text"><b><s:text name="label.installment"/></b></div>
	                                   		<s:radio list="#{'Y':'Yes','N':'No'}" name="installmentYN" id="installmentYN" value='%{"Y".equals(installmentYN)?"Y":"N"}' onchange="toggleInstallment('INSTALLMENT_CALC');" />
	                               			&nbsp;&nbsp;&nbsp;&nbsp;<button style="display:none" id="installmentCalcDiv"  type="button" class="btn btn-sm btn-primary" data-toggle="modal" data-refresh="true" data-target="#installmentDetailsModal" data-backdrop="static" data-keyboard="false" onclick="installmenDetailsAjax();"> Calculate </button>
	    								<div id="installmentDetailsModal" class="modal fade" role="dialog">
	    									<div class="modal-dialog" id="installmentCalcAjaxNew"></div>
	    								</div>
									</div>
		                		</div>
		                		<br>
		                		<div class="row">
				                    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
										<s:checkbox name="generatePolicyYN" fieldValue="Y" id="generatePolicyYN" onclick="enablePayment();"/>
										<s:text name="label.declarationStatement"></s:text>					        						
									</div>
		                		</div>
	                		</div>
	                	</div>
	                </s:if>
	            </div>
		        <br>
        	</div>
	        
	        <br>
	        <div class="row" >
				<div  id="draft" class="col-lg-2 col-md-3 offset-md-4 offset-lg-4 mb-3">
					<s:if test='#session.user1 != "admin"'>
						<s:if test='quoteStatus=="RA" || (endTypeId!=null && !"".equalsIgnoreCase(endTypeId)) || "2".equals(premiumType)'>
							<input type="button" name="Submit1" class="btn btn-danger btn-block" value="Back" onclick="getBack('home');" />
							&nbsp;&nbsp;&nbsp;
						</s:if>
						<s:else>
							<!--  <input type="button" name="Submit1" class="btn btn-sm btn-danger" value="Back" onclick="getBack('showSummarry');" />-->
							<input type="button" name="Submit1" class="btn btn-danger btn-block" value="Back" onclick="getBack('vehDetail');" />
							&nbsp;&nbsp;&nbsp;
						</s:else>
						<%--<input type="button" name="Submit2" class="btn" value="Save" onclick="this.form.actionType.value='getSave';this.form.submit();" />
						                  &nbsp;--%>
					</s:if>
					<s:else>
						<%--
						<s:submit type="button" name="close"  key="Back" cssClass="btn btn-sm btn-danger" onclick="getBack('editCovRate')"/>
						--%>
						<input type="button" name="Submit1" class="btn btn-danger btn-block" value="Back" onclick="getBack('vehDetail');" />
						&nbsp;&nbsp;&nbsp;
					</s:else>
				</div>
				<div id="makePay" class="col-lg-2 col-md-3 mb-3" align="center">
                    <input type="button" name="Submit3" id="makePayment1" class="btn btn-success btn-block" value="Make Payment" onclick="this.form.actionType.value='getQuote';disableForm(this.form,false,'');funGeneratePolicy()" disabled="true"/>
                </div>
                <div id="emailQuote" class="col-lg-2 col-md-3 mb-3" align="center" style="display: none">
                    <input type="button" name="Submit3" class="btn btn-success btn-block" value="Email Quote" onclick="this.form.actionType.value='getQuote';disableForm(this.form,false,'');funGeneratePolicy()"/>
                </div>
                 <div id="referQuote" class="col-lg-2 col-md-3 mb-3" align="center" style="display: none">
                    <input type="button" name="Submit3" class="btn btn-success btn-block" value="Refer Quote" onclick="this.form.actionType.value='getQuote';disableForm(this.form,false,'');funGeneratePolicy()"/>
                </div>
				<!-- <div id="draft1" class="col-lg-2 col-md-3 mb-3">
					<input type="button" name="Submit3" class="btn btn-warning btn-block" value="Proceed" onclick="this.form.actionType.value='getQuote';disableForm(this.form,false,'');funGenerateDraft()"/>
				</div> -->
				<div id="referralB" class="col-lg-2 col-md-3 mb-3" style="display: none">
					<input type="button" name="Submit3" class="btn btn-success btn-block" value="Proceed" onclick="this.form.actionType.value='getQuote';disableForm(this.form,false,'');funGenerateReff()"/>
				</div>
	                <s:hidden name="currencyType"/>
			</div>
			<div class="row" id="draftB" style="display: none">
				<div  class="col-lg-2 col-md-4 offset-md-3 offset-lg-5 mb-3">
					<s:if test='#session.user1 != "admin"'>
						<s:if test='quoteStatus=="RA" || (endTypeId!=null && !"".equalsIgnoreCase(endTypeId)) || "2".equals(premiumType)'>
							<input type="button" name="Submit1" class="btn btn-danger btn-block" value="Back" onclick="getBack('home');" />
							&nbsp;&nbsp;&nbsp;
						</s:if>
						<s:else>
							<!--  <input type="button" name="Submit1" class="btn btn-sm btn-danger" value="Back" onclick="getBack('showSummarry');" />-->
							<input type="button" name="Submit1" class="btn btn-danger btn-block" value="Back" onclick="getBack('vehDetail');" />
							&nbsp;&nbsp;&nbsp;
						</s:else>
						<%--<input type="button" name="Submit2" class="btn" value="Save" onclick="this.form.actionType.value='getSave';this.form.submit();" />
						                  &nbsp;--%>
					</s:if>
					<s:else>
						<%--
						<s:submit type="button" name="close"  key="Back" cssClass="btn btn-sm btn-danger" onclick="getBack('editCovRate')"/>
						--%>
						<input type="button" name="Submit1" class="btn btn-danger btn-block" value="Back" onclick="getBack('vehDetail');" />
						&nbsp;&nbsp;&nbsp;
					</s:else>
				</div>
				
			</div>
        </div>
			
	       	<%--</s:if>	--%>
    </s:if>
	<s:elseif test="'paymentInformation'.equalsIgnoreCase(display)">
			
	</s:elseif>

	
</s:form>
<s:form id="form1" name="form1" method="post" theme="simple" enctype="multipart/form-data">
<div class="container modal_VehicleDetails">
    <div class="modal fade" id="myModal">
      <div class="modal-dialog  modal-lg">
        <div class="modal-content">
          	<div id="attachDocumentAjax"></div>
        </div>
      </div>
    </div>
</div>
</s:form>

<script  type="text/javascript">
/*<s:if test='"Y".equals(paymentYN) && #session.user1 != "admin"'>
	<s:if test='"Y".equalsIgnoreCase(generatePolicyYN) || "viewPayment".equalsIgnoreCase(mode)'>
		getPaymentModeDetails('<s:property value="modeOfPayment"/>');
	</s:if>
</s:if>*/
function enablePayment(){
	if(document.getElementById('generatePolicyYN').checked){
		document.getElementById("makePayment1").disabled = false;
	}else {		
		document.getElementById("makePayment1").disabled = true;
	}
}
//toggleEmailQuote();
function toggleEmailQuote(){
	//alert();
	if (document.getElementById("quoteEmailYN").checked == true) {
		document.getElementById("installmentDiv").style.display = "none";
		document.getElementById('makePay').style.display='none';
		document.getElementById('emailQuote').style.display='block';
		
	}else{
		document.getElementById("installmentDiv").style.display = "block";
		document.getElementById('makePay').style.display='block';
		document.getElementById('emailQuote').style.display='none';
	}
}
function togglereferQuote(val){
	//alert();
	if (val=='Y') {
		document.getElementById("installmentDiv").style.display = "none";
		document.getElementById('makePay').style.display='none';
		document.getElementById('referQuote').style.display='block';
		document.getElementById('brokeruserid').style.display='block';
		
		
	}else{
		document.getElementById("installmentDiv").style.display = "block";
		document.getElementById('makePay').style.display='block';
		document.getElementById('referQuote').style.display='none';
		document.getElementById('brokeruserid').style.display='none';
	}
}
function getPopup(URL) {
	var bwidth = window.innerWidth;
	var bwidth1 = document.body.clientWidth;
	if(bwidth <= 768) {
		return popUp(URL,bwidth1,'500');
	} else {
		return popUp(URL,'750','500');
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
function fnClausesEditAjax(frm, id) {
	var val = '?productId=<s:property value="productId"/>'
		+ '&branchCode=<s:property value="branchCode"/>'
		+ '&quoteNo=<s:property value="quoteNo"/>'
		+ '&applicationNo=<s:property value="applicationNo"/>';
	postRequest('${pageContext.request.contextPath}/'+id+'Motor.action'+val, id);
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
try {
	// New Block
	function funGeneratePolicy(){
		document.motor.generatePolicyYN.value="Y";
		document.motor.policyType.value='<s:property value="policyType"/>'; 
		document.motor.action = "${pageContext.request.contextPath}/getBuypolicyMotor.action";
		document.motor.submit();
	}
	function fnsubmitVeh(action) {
		document.motor.action = "${pageContext.request.contextPath}/vehicleDtlMotor.action";
		document.motor.submit();
	}
	function funGenerateDraft(){
		//document.motor.action = "${pageContext.request.contextPath}/generateDraftMotor.action";
		document.motor.action = "${pageContext.request.contextPath}/initReport.action?menuType=QE";
		//document.motor.action = "${pageContext.request.contextPath}/getBackQEMotor.action";
		document.motor.submit();
	}
	function funGenerateReff(){
		document.motor.action = "${pageContext.request.contextPath}/generateReferralMotor.action";
		document.motor.submit();
	}
	
	// New Block End
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
		//alert("Hi-- "+modalSelectId+","+modalTypeBody+","+modalVehicleUsage+","+modalTypeBodyName+","+modalVehicleUsageName);
		$("#modalSelectId").val(modalSelectId);
		$("#modalTypeBody").val(modalTypeBody);
		$("#modalVehicleUsage").val(modalVehicleUsage);
		$("#modalTypeBodyName").val(modalTypeBodyName);
		$("#modalVehicleUsageName").val(modalVehicleUsageName);
	}
	
	function checkBodyClick (frm){
		//alert();
		/*var gender = document.querySelector('input[name = "selects"]:checked').value;
		alert(gender);
		var a=$("#modalTypeBody").val();
		var b=$("#modalVehicleUsage").val();
		alert(a +","+b);
		var c = document.getElementById('modalTypeBody').value;
		var d = document.getElementById('modalVehicleUsage').value;
		alert(c +","+d);*/
		var seatingCapacity = $("#tSeatingCapacity_" + $("#modalTypeBody").val()+ $("#modalVehicleUsage").val()).val();
		//alert("seat = "+seatingCapacity);
		if(seatingCapacity!="" && parseInt(seatingCapacity) != NaN) {
			if($("#modalSelectId").val()!="") {
				//alert("yes");
				$("#typeBody").val($("#modalTypeBody").val());
				$("#vehicleUsage").val($("#modalVehicleUsage").val());
				$("#seatingCapacity").val($("#tSeatingCapacity_" + $("#modalTypeBody").val()+ $("#modalVehicleUsage").val()).val());
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
			}catch(err) {console.error(err);}
	
			$("#modalTypeBody").val(modalTypeBody);
			$("#modalVehicleUsage").val(modalVehicleUsage);
		//}
		
		var error = "";
		var seatingCapacity = $("#tSeatingCapacity_" + $("#modalTypeBody").val()+ $("#modalVehicleUsage").val()).val();
		//alert(seatingCapacity);
		
		if(seatingCapacity!="" && parseInt(seatingCapacity) != NaN) {
			//alert('inside seat');
			//alert($("#modalTypeBody").val());
				$("#typeBody").val($("#modalTypeBody").val());
				$("#vehicleUsage").val($("#modalVehicleUsage").val());
				$("#seatingCapacity").val($("#tSeatingCapacity_" + $("#modalTypeBody").val()+ $("#modalVehicleUsage").val()).val());
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
			$("#seatingCapacity").val($("#tSeatingCapacity_" + $("#modalTypeBody").val()+ $("#modalVehicleUsage").val()).val());
			$("#typeBodyDiv").html($("#modalTypeBodyName").val());
			$("#vehicleUsageDiv").html($("#modalVehicleUsageName").val());
			$("#seatingCapacityDiv").html($("#seatingCapacity").val());
			$("#typeBodyName").val($("#modalTypeBodyName").val());
			$("#vehicleUsageName").val($("#modalVehicleUsageName").val());
			$('#vehicleTypeDetails').modal('toggle');
			getDeductibleAjax(frm);
			getNCBAjax(frm);
		}
		//alert($("#typeBody").val());
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
		//alert(value);
		if(value=="Y") {
			//if('Y'=='<s:property value="showReferralYN"/>') {
				//alert('Y');
				document.getElementById('referralComments').readOnly=false;
			//}
			//document.getElementById("generatePolicyYN").checked = false;
			document.getElementById('quoteEmailYN').checked=false;
			//togglePayment("GENERATE_POLICY");
			document.getElementById('policyGeneration').style.display='none';
			document.getElementById("installmentCalcDiv").style.display = "none";
			document.getElementById('installmentYNN').checked=true;
			document.getElementById('installmentYNY').checked=false;
			//document.getElementById("installmentDiv").style.display = "none";
			//document.getElementById('draft1').style.display='none';
			document.getElementById('referralB').style.display='block';
			document.getElementById('makePay').style.display='none';
			document.getElementById('emailQuote').style.display='none';
			try{
				installmenDelete();
			}catch(err) {console.error(err);}
			
		} else {
			//alert('N');
		 	document.getElementById('policyGeneration').style.display='block';
		 	document.getElementById('makePay').style.display='block';
		 	//document.getElementById("installmentDiv").style.display = "block";
			//document.getElementById('draft1').style.display='block';
			document.getElementById('referralB').style.display='none';
		 	//if('Y'=='<s:property value="showReferralYN"/>') {
			 	document.getElementById('referralComments').value='';
			 	document.getElementById('referralComments').readOnly=true;
		 	//}
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
		  document.getElementById('claimYNN').checked=true;
		  document.getElementById('claimYNN').disabled=true;
		  document.getElementById('claimYNY').disabled=true;
		  document.getElementById('claimAmount').value='';
		  document.getElementById('claimAmount').readOnly=true;
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
	function uploadDocumentsV1(vehicleId) {
		var URL ='${pageContext.request.contextPath}/documentUploadV1DoUpload.action?applicationNo=<s:property value="applicationNo"/>&quoteNo=<s:property value="quoteNo"/>&deleteVehicleId='+vehicleId;
		return popUp(URL,'700','500');
	}
	function uploadDocumentsNew(vehicleId) {
		var URL ='${pageContext.request.contextPath}/documentUploadNewDoUpload.action?applicationNo=<s:property value="applicationNo"/>&quoteNo=<s:property value="quoteNo"/>&deleteVehicleId='+vehicleId;
		//return popUp(URL,'700','500');
		postRequest(URL, 'attachDocumentAjax');
	}
	function electricalPopup(vehicleId) {
		var URL ='${pageContext.request.contextPath}/electricalPopupMotor.action?applicationNo=<s:property value="applicationNo"/>';
		return popUp(URL,'700','500');
	}
	 
	function nelectricalPopup(vehicleId) {
		var URL ='${pageContext.request.contextPath}/nelectricalPopupMotor.action?applicationNo=<s:property value="applicationNo"/>';
		return popUp(URL,'700','500');
	}
	function premCalculate(vehicleId){
		//alert(vehicleId);
		document.getElementById('rateVehicleId').value=vehicleId;
		document.motor.action = "${pageContext.request.contextPath}/baseRateUpdateMotor.action";
		document.motor.submit();
	}	
    function getBack(page) {
		if(page=='showSummarry') {
			document.motor.action ='${pageContext.request.contextPath}/showSummarryMotor.action';
		} else if(page=='newQuote') {
				document.motor.action ='${pageContext.request.contextPath}/editQuoteMotor.action';
		}else if(page=='vehDetail'){
			document.motor.action ='${pageContext.request.contextPath}/getBackVehicleMotor.action';
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
						document.motor.action ='${pageContext.request.contextPath}/editQuoteCustDataMotor.action?menuType=<s:property value="quoteStatus"/>&loginId=<s:property value="loginId"/>';
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
		//alert(frm +" "+type + " " + vehicleId);
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
	
	function fnVehicleEdit(frm, type, vehicleId, id) {
		var val = '?productId=<s:property value="productId"/>'
			+ '&branchCode=<s:property value="branchCode"/>'
			+ '&deleteVehicleId='+vehicleId
			+ '&reqFrom='+type
			+ '&applicationNo=<s:property value="applicationNo"/>';
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
<s:elseif test='%{"policyInfo".equalsIgnoreCase(display) && #session.user1 != "admin" && #session.usertype !="RSAIssuer"}'>
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
	
	function fnUpdateDriver(frm, type, vehicleId,vehicleType, id) {
		//document.getElementId('isClaimDtl').checked = true;
			var val = '?productId=<s:property value="productId"/>'
				+ '&branchCode=<s:property value="branchCode"/>'
				+ '&deleteVehicleId='+vehicleId
				+ '&vehicleType='+vehicleType
				+ '&applicationNo=<s:property value="applicationNo"/>';
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
	
</script>
<script type="text/javascript">
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

<s:if test="'INDIVIDUAL'.equalsIgnoreCase(customerType)">
try {
	document.getElementById('customerType').value='INDIVIDUAL';
	toggleCompanyRegistration('INDIVIDUAL');
	$("#Individual").addClass('active');
	$("#Corporate").removeClass('active');
} catch(err) {
	console.error(err);
}
</s:if>
<s:if test="'CORPORATE'.equalsIgnoreCase(customerType)">
try {
	document.getElementById('customerType').value='CORPORATE';
	toggleCompanyRegistration('CORPORATE');
	$("#Corporate").addClass('active');
	$("#Individual").removeClass('active');
} catch(err) {
	console.error(err);
}
</s:if>

    function selectCustomerType(val){
    	//console.log(val);
    	document.getElementById('customerType').value=val;
    	if(val=='INDIVIDUAL'){
    		$("#Individual").addClass('active');
    		$("#Corporate").removeClass('active');
  		}
    	if(val=='CORPORATE'){
    		$("#Corporate").addClass('active');
    		$("#Individual").removeClass('active');
    	}
    }
    
	function enable(){
			document.getElementById("change").value= "Edit";
		 	$('#modeOfPay input').removeAttr('disabled');
		 	$('#modeOfPay select').removeAttr('disabled');
		 	$('#mode').attr("value", 'editPayment');
			}
	/*if('<s:property value="generatePolicyYN"/>' == "Y" || '<s:property value="mode"/>' == "viewPayment" ){
			getPaymentModeDetails('<s:property value="modeOfPayment"/>');
		}*/
		function getPaymentModeDetails(val) {
			//alert(val);
			//document.getElementById('modeOfPayment').value=val;
			hidePaymentDetails();
			if(val=='1') {
				callBankInfo(val);
				$("#bankDetailsDiv").show();
				$("#cash").show();
				$("#pay1").show();
				if('<s:property value="userType"/>'!="admin" && '<s:property value="userType"/>'!="creditcontroller" && '<s:property value="userType"/>'!="surveyor" && '<s:property value="userType"/>'!="underwriter") {
					if(document.getElementById("installmentYNY").checked){
						$('#cashAmount').val('<s:property value="#insIntialAmount"/>');
					}else{
						$('#cashAmount').val('<s:property value="#sPayAmount"/>');
					}
					}
				//callBankInfo(val);
				//$("#bankDetailsDiv").show();
			} else if(val=='2') {
				callBankInfo(val); 
				$("#bankDetailsDiv").show();
				$("#cheque").show();
				$("#pay2").show();
				if('<s:property value="userType"/>'!="admin" && '<s:property value="userType"/>'!="creditcontroller" && '<s:property value="userType"/>'!="surveyor" && '<s:property value="userType"/>'!="underwriter") {
					if(document.getElementById("installmentYNY").checked){
						$('#chequeAmount').val('<s:property value="#insIntialAmount"/>');
					}else{
						$('#chequeAmount').val('<s:property value="#sPayAmount"/>');
					}
				}
				//callBankInfo(val); 
				//$("#bankDetailsDiv").show();
			}else if(val=='6'){
			$("#credit").show();
			$("#pay6").show();
			}
			   
		}

		function hidePaymentDetails() {
			$("#pay1").hide();
			$("#pay2").hide();
			$("#pay6").hide();
			
			$("#cheque").hide();
			$("#cash").hide();
			$("#net").hide();
			$("#bankDetailsDiv").hide();
			$("#credit").hide();
			if('<s:property value="userType"/>'!="admin" && '<s:property value="userType"/>'!="creditcontroller" && '<s:property value="userType"/>'!="surveyor" && '<s:property value="userType"/>'!="underwriter") {
				if($('#modeOfPayment').val() !='2') {
					$('#cheque input').val('');
				} if($('#modeOfPayment').val() !='1') {
					$('#cash input').val('');
				}
			}
				
		}

		function callBankInfo(val){
			var currencyId='';
			var quoteNo = '<s:property value="quoteNo"/>'
				
			if('<s:property value="currencyType"/>'=='USD'){
				currencyId='<s:property value="currencyType"/>';
			}else{
				currencyId='<s:property value="#session.BrokerDetails.CurrencyAbb"/>';
			}
			//postRequest('${pageContext.request.contextPath}/bankInfoAjaxMotor.action?modeOfPay='+val+'&currencyType='+currencyId+'&quoteNo='+quoteNo, 'bankInfoAjx');
			postRequest('${pageContext.request.contextPath}/bankInfoAjaxNewMotor.action?modeOfPay='+val+'&currencyType='+currencyId+'&quoteNo='+quoteNo, 'bankInfoAjxNew');
		}

		/*function callInsAmount(val){
			var currencyId='';
			if('<s:property value="currencyType"/>'=='' && '<s:property value="currencyType"/>'==null){
				currencyId='<s:property value="currencyType"/>';
			}else{
				currencyId='<s:property value="#session.BrokerDetails.CurrencyAbb"/>';
			}
			postRequest('${pageContext.request.contextPath}/bankInfoAjaxMotor.action?modeOfPay='+val+'&currencyType='+currencyId, 'bankInfoAjx');	
		}*/
		
		 function onlyNos(e, t) {
	            try {
	                if (window.event) {
	                    var charCode = window.event.keyCode;
	                }
	                else if (e) {
	                    var charCode = e.which;
	                }
	                else { return true; }
	                if (charCode > 31 && (charCode < 48 || charCode > 57)) {
	                    return false;
	                }
	                return true;
	            }
	            catch (err) {
	                alert(err.Description);
	            }
	        }
		 function toggleInstallment(type) {
			 if(type=="INSTALLMENT_CALC") {
				 if (document.getElementById("installmentYNY").checked) {
						document.getElementById("installmentCalcDiv").style.display = "";
						//document.getElementById("modeOfPay").style.display = "none";
						//document.getElementById('modeOfPayment').disabled=false;
						$('#modeOfPayment').val('');
						hidePaymentDetails();
					}
				 else {
					//document.getElementById('modeOfPayment').disabled=false;
					document.getElementById("installmentCalcDiv").style.display = "none";
					//document.getElementById("modeOfPay").style.display = "block";
					installmenDelete();
					hidePaymentDetails();
				}
		 	}else if(type=="INSTALLMENT_SUBMIT") {
				if(document.getElementById('installmentAgreeYN').checked==true) {
					//document.getElementById("modeOfPay").style.display = "block";
					//$('#modeOfPayment').val('6');
					//document.getElementById('modeOfPayment').disabled=true;
					$('#installmentDetailsModal').modal('toggle');
					hidePaymentDetails();
					//$("#credit").show();
				} else {
					$('#modalErrorSpan').html('Please Agree the Installement');
					hidePaymentDetails();
				}
			} else if(type=="INSTALLMENT_CANCEL") {
				document.getElementById("installmentCalcDiv").style.display = "none";
				document.getElementById('installmentYNN').checked=true;
				document.getElementById('installmentYNY').checked=false;
				//document.getElementById("modeOfPay").style.display = "block";
				//document.getElementById('modeOfPayment').disabled=false;
				installmenDelete();
				hidePaymentDetails();
				$('#installmentDetailsModal').modal('toggle');
			}
		 }
		 
		 
		function installmenDetailsAjax() {
			var id = 'installmentCalcAjaxNew';
			var val = '?quoteNo=<s:property value="quoteNo"/>'
					+ '&applicationNo=<s:property value="applicationNo"/>';
			postRequest('${pageContext.request.contextPath}/'+id+'Payment.action'+val, id);
		}

		function installmenDelete() {
			var id = 'N';
			var quoteNo = '<s:property value="quoteNo"/>'
			postRequest('${pageContext.request.contextPath}/deleteinstallmentMotor.action?quoteNo='+quoteNo+'&installmentYN='+id);
		}
		
		function viewQuote(val)
		{
			var URL ='${pageContext.request.contextPath}/QuotePrint.pdfSchedule?quote_no='+val+'&reqFrom=QuotePrint';
			var windowName = "QuotatiionPrint";
			var width  = screen.availWidth;
			var height = screen.availHeight;
			var bwidth = window.innerWidth;
			var bwidth1 = document.body.clientWidth;
			if(bwidth <= 768) {
				var w = bwidth1;
				var h =	500;
			} else {
				var w = 750;
				var h =	500;
			}
			var features =
				'width='          + w +
				',height='		  + h +
				',left='		  + ((width - w - 10) * .5)  +
				',top='			  + ((height - h - 30) * .5) +
				',directories=no' +
				',location=no'	  +
				',menubar=no'	  +
				',scrollbars=yes' +
				',status=no'	  +
				',toolbar=no'	  +
				',resizable=false';
			var strOpen = window.open (URL, windowName, features);
			strOpen.focus();
			return false;
		}
	</script>
	<s:if test='!"admin".equalsIgnoreCase(userType) && !"creditcontroller".equalsIgnoreCase(userType) && !"surveyor".equalsIgnoreCase(userType) && !"underwriter".equalsIgnoreCase(userType)'>
		<SCRIPT type="text/javascript">
		if('<s:property value="quoteEmailYN"/>' == "Y") {
			document.getElementById("quoteEmailYN").checked = true;
			document.getElementById("installmentDiv").style.display = "none";
			document.getElementById('makePay').style.display='none';
			document.getElementById('emailQuote').style.display='block';
		} else {
			document.getElementById("quoteEmailYN").checked = false;
			<s:if test='%{#session.usertype !="RSAIssuer"}'>
			if (document.getElementById('referralYNN').checked == true) {
				document.getElementById("installmentDiv").style.display = "block";
				document.getElementById('makePay').style.display='block';
			}
			</s:if>
			document.getElementById('emailQuote').style.display='none';
		}
		/*if('<s:property value="generatePolicyYN"/>' == "Y") {
			document.getElementById("generatePolicyYN").checked = true;
		} else {
			document.getElementById("generatePolicyYN").checked = false;
		}*/
		//togglePayment('GENERATE_POLICY');
		</SCRIPT>
	</s:if>
</body>
</html>