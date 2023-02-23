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
    <style>
        * {
            font-family: 'Nunito', sans-serif !important;
        }

        .fas {
            font-family: "Font Awesome 5 Free" !important;
        }

        .QuotationInformationPage .card {
            padding: 20px 20px 20px 120px;
            border: 0px !important
        }

        .QuotationInformationPage .card-2 {
            padding: 20px 30px 20px 30px;

        }

        .QuotationInformationPage .Card_Parent {
            border-radius: 4px;
            background: #fff;
            box-shadow: 0 6px 10px rgba(0, 0, 0, .08), 0 0 6px rgba(0, 0, 0, .05);
            transition: .3s transform cubic-bezier(.155, 1.105, .295, 1.12), .3s box-shadow, .3s -webkit-transform cubic-bezier(.155, 1.105, .295, 1.12);
        }

        .QuotationInformationPage .plan_Card {
            box-shadow: 0 1px 1px rgba(0, 0, 0, 0.25),
                0 2px 2px rgba(0, 0, 0, 0.20),
                0 4px 4px rgba(0, 0, 0, 0.15),
                0 8px 8px rgba(0, 0, 0, 0.10),
                0 16px 16px rgba(0, 0, 0, 0.05);
            padding: 10px;
            border-radius: 5px;
            background-color: aliceblue;
        }

        .PremiumCoverDetails {
            box-shadow: 0 1px 1px rgba(0, 0, 0, 0.25),
                0 2px 2px rgba(0, 0, 0, 0.20),
                0 4px 4px rgba(0, 0, 0, 0.15),
                0 8px 8px rgba(0, 0, 0, 0.10),
                0 16px 16px rgba(0, 0, 0, 0.05);
            border-radius: 5px;
            background-color: white;
        }

        .QuotationInformationPage .card h4 {
            font-weight: 700;
            color: #261e6a;
        }

        .QuotationInformationPage .card-1 {
            background-image: url("assets/Images/home-insurances.png");
            background-repeat: no-repeat;
            background-position: left;
            background-size: 200px;
        }

        .QuotationInformationPage .LabelHeading {
            font-weight: bolder;
        }

        .PremiumCoverDetails .list-group .list-group-item {
            border-right: white;
            border-left: white;

        }

        .PremiumCoverDetails .list-group .list-group-item:first-child {
            border-top: white;
        }

        .PremiumCoverDetails .list-group .list-group-item:last-child {
            border-bottom: white;
        }

        .rowFlex {
            display: flex;
        }

        .rowFlex ul li {
            border-bottom: 1px solid #ccc;
        }

        .rowFlex ul li a {
            border-radius: 0px !important;
        }

        .colsame {
            flex: 1;
            /* border: 1px solid #ccc; */
        }

        .tabsidebar {
            box-shadow: 0 1px 1px rgba(0, 0, 0, 0.25),
                0 2px 2px rgba(0, 0, 0, 0.20),
                0 4px 4px rgba(0, 0, 0, 0.15),
                0 8px 8px rgba(0, 0, 0, 0.10),
                0 16px 16px rgba(0, 0, 0, 0.05);
        }

        .submenus li {
            box-shadow: 0 1px 2px rgba(0, 0, 0, 0.07),
                0 2px 4px rgba(0, 0, 0, 0.07),
                0 4px 8px rgba(0, 0, 0, 0.07),
                0 8px 16px rgba(0, 0, 0, 0.07),
                0 16px 32px rgba(0, 0, 0, 0.07),
                0 32px 64px rgba(0, 0, 0, 0.07);
            border: 0 !important;
        }

        .coveragepadding {
            padding: 60px 10px 10px 10px;
        }

        .OnlyPremiumTable .table {
            width: 100%;
            border-collapse: collapse;
            border-radius: 20px;
            box-shadow: 0 1px 2px rgba(0, 0, 0, 0.07),
                0 2px 4px rgba(0, 0, 0, 0.07),
                0 4px 8px rgba(0, 0, 0, 0.07),
                0 8px 16px rgba(0, 0, 0, 0.07),
                0 16px 32px rgba(0, 0, 0, 0.07),
                0 32px 64px rgba(0, 0, 0, 0.07);
        }

        .OnlyPremiumTable .table td,
        .table th {

            border-top: 0;
        }

        .OnlyPremiumTable thead th:first-child {
            border-radius: 20px 0px 0px 0px !important;
        }

        .OnlyPremiumTable thead th:last-child {
            border-radius: 0px 20px 0px 0px !important;

        }

        .OnlyPremiumTable tbody tr:last-child td:first-child {
            border-radius: 0px 0px 0px 20px !important;

        }

        .OnlyPremiumTable tbody tr:last-child td:last-child {
            border-radius: 0px 0px 20px 0px !important;

        }

        .OnlyPremiumTable .table td,
        .OnlyPremiumTable .table th {
            vertical-align: middle;
        }



        .OnlyPremiumTable .table td,
        .OnlyPremiumTable .table th {
            padding: 10px;
            /* border: 1px solid #ddd; */
            font-size: 14px;
        }

        .OnlyPremiumTable .table th {
            color: black;
        }

        /* .OnlyPremiumTable .table tbody tr:nth-child(even) {
            background-color: #f5f5f5;
        } */

        /*responsive*/

        @media(max-width: 500px) {
            .OnlyPremiumTable .table thead {
                display: none;
            }

            .OnlyPremiumTable .table,
            .OnlyPremiumTable .table tbody,
            .OnlyPremiumTable .table tr,
            .OnlyPremiumTable .table td {
                display: block;
                width: 100%;
            }

            .OnlyPremiumTable .table tr {
                margin-bottom: 15px;
            }

            .OnlyPremiumTable .table td {
                text-align: right;
                padding-left: 50%;
                text-align: right;
                position: relative;
            }

            .OnlyPremiumTable .table td::before {
                content: attr(data-label);
                position: absolute;
                left: 0;
                width: 50%;
                padding-left: 15px;
                font-size: 13px;
                font-weight: bold;
                text-align: left;
            }
        }
        
        .nav-pills .nav-link.active, .nav-pills .show>.nav-link {
		    color: #fff; 
		    background-color: #261e6a;
		}
		.flex-column a{
			color: #261e6a;
			font-weight: bold;
		}
		
		.submenus a{
			color: #261e6a;
			font-weight: bold;
		}
    </style>
</head>
<body>
	<div id="loading" style="width:100%">
	   <img id="loading-image" src="${pageContext.request.contextPath}/images/madisonSymbolLogo.png"/>
	</div>
	<s:if test="hasActionErrors()">
		<div class="container QuotationInformationPage mt-5">
	    	<div class="Card_Parent  mt-4">
	           	<div class="card card-1">
	                <h4><s:label value="Validation Errors"/></h4>
	                <hr>
	                <div class="row">
	                    <div class="col-md-12 col-12" style="color: red;">
							<s:actionerror style="color:red;"/>
	                    </div>
	                </div>
	           	</div>
	        </div>
        </div>
    </s:if>
   <s:form name="extraInformation" id="extraInformation" theme="simple">
	    <div class="container QuotationInformationPage mt-5">
	    	<s:include value="./viewPremiumDtls.jsp"/>
           	<div class="Card_Parent  mt-4">
	            <div class="card card-1">
	                <h4>Policy Holder Information</h4>
	                <hr>
	                <div class="row mt-3">
                       	<div class="col-md-4">
	                        <label class="LabelHeading">Customer Type</label>
	                        <div class="input-group mb-3">
	                            <div class="input-group-prepend">
	                                <span class="input-group-text border border-right-0">
	                                	<i class="fas fa-users"></i>
	                                </span>
	                            </div>
	                            <s:select name="customerType" id="customerType" list="#{'INDIVIDUAL':'Individual','CORPORATE':'Corporate'}" value='%{(customerType==null||"".equals(customerType))?"INDIVIDUAL":customerType}' cssClass="form-control border border-left-0" onchange="cmpyRegNoDis();"  disabled='%{(productId==null)?true:false}' />
	                        </div>
	                    </div>
                           <div class="col-md-4" id="cmpyRegNoDivId">
                           	<label class="labelHeader"><s:text name="customer.companyRegNo"  /><font color="red">*</font></label>
                               <div class="input-group mb-3">
                                   <div class="input-group-prepend">
                                       <span class="input-group-text border border-right-0"><i class="fas fa-passport"></i></span>
                                   </div>
                                   <s:textfield name="companyRegNo" id="companyRegNo" class="form-control border empyCustDetails"  maxLength="10" />
                               </div>
                           </div>
                           <div class="col-md-4">
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
                           <div class="col-md-4">
                           	<label class="labelHeader"><s:text name="customer.passportNo"/></label>
                               <div class="input-group mb-3">
                                   <div class="input-group-prepend">
                                       <span class="input-group-text border border-right-0"><i
                                               class="fas fa-passport"></i></span>
                                   </div>
                                   <s:textfield name="custPassportNo" id="custPassportNo" class="form-control border empyCustDetails"  maxLength="10" disabled="#endtDisable"/>
                               </div>
                           </div>
                           <div class="col-md-4">
                           	<label class="labelHeader"><s:text name="customer.title"  /><font color="red">*</font></label>
                               <div class="input-group mb-3">
                                   <div class="input-group-prepend">
                                       <span class="input-group-text border border-right-0">
                                           <i class="fas fa-heading"></i>
                                       </span>
                                   </div>
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
								<s:textfield name="customerName" id="customerName" class="form-control border empyCustDetails"  maxLength="100" disabled="#disable1" />
                               </div>
                           </div>
                           <div class="col-md-4">
                           	<label class="labelHeader"><s:text name="customer.lastName"/><font color="red">*</font></label>
                               <div class="input-group mb-3">
                                   <div class="input-group-prepend">
                                       <span class="input-group-text border border-right-0"><i class="fas fa-user-check"></i></span>
                                   </div>
                                   <s:textfield name="custLastName" id="custLastName" class="form-control border empyCustDetails" maxLength="20" disabled="#endtDisable"  />
                               </div>
                           </div>
                       	<div class="col-md-4">
                           	<label class="labelHeader"><s:text name="customer.dob" />&nbsp;<s:text name="(DD/MM/YYYY)" /><font color="red">*</font></label>
                               <div class="input-group mb-3">
                                   <div class="input-group-prepend">
                                       <span class="input-group-text border border-right-0"><i
                                               class="fas fa-calendar-alt"></i></span>
                                   </div>
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
                                       <span class="input-group-text border border-right-0"><i
                                               class="fas fa-calendar-alt"></i></span>
                                   </div>
                                   <s:textfield name="occupation" maxlength="30" class="form-control border L empyCustDetails" disabled="#endtDisable" />
                               </div>
                           </div>
                           <div class="col-md-4">
                           	<label class="labelHeader"><s:text name="Address 1"/><font color="red">*</font></label>
                               <div class="input-group mb-3">
                                   <div class="input-group-prepend">
                                       <span class="input-group-text border border-right-0">
                                           <i class="fas fa-address-card"></i>
                                       </span>
                                   </div>
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
                                   <s:select name="city" id="city" list="cityList" headerKey="" headerValue="---Select City---" listKey="CODEDESC" listValue="CODEDESC" class="form-control empyCustDetails"  disabled="#endtDisable"/>
                               </div>
                           </div>
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
                                   <s:textfield name="mobileNo" id="mobileNo" class="form-control border empyCustDetails"  maxLength="10" onkeyup="checkNumbers(this);" disabled="#disable1"/>
                               </div>
                           </div>
                           <div class="col-md-4">
                           	<label class="labelHeader"><s:text name="customer.alterMobile"  /></label>
                               <div class="input-group mb-3">
                                   <div class="input-group-prepend">
                                       <span class="input-group-text border border-right-0">
                                           <i class="fas fa-address-card"></i>
                                       </span>
                                   </div>
                                   <s:textfield name="custAlterMobileNo" id="custAlterMobileNo" class="form-control border empyCustDetails"  maxLength="10" onkeyup="checkNumbers(this);" disabled="#endtDisable"/>
                               </div>
                           </div>
                           <div class="col-md-4">
                           	<label class="labelHeader"><s:text name="customer.email"  /><font color="red">*</font></label>
                               <div class="input-group mb-3">
                                   <div class="input-group-prepend">
                                       <span class="input-group-text border border-right-0">
                                           <i class="fas fa-envelope"></i>
                                       </span>
                                   </div>
                                   <s:textfield name="email" id="email" class="form-control border empyCustDetails" maxLength="100" disabled="#endtDisable"/>
                               </div>
                           </div>
						<s:hidden name="customerId"  id="customerId"/>
	                </div>
	            </div>
	        </div>
           	<div class="Card_Parent  mt-4">
	            <div class="card card-1">
	                <h4>Policy Period<span style="font-size:15px;">&nbsp;(DD/MM/YYYY)</span></h4>
	                <hr>
	                <div class="row mt-3">
		                <div class="col-md-4">
	                    	<label class="labelHeader"><s:text name="motor.policyStartDt"/><font color="red">*</font></label>
	                   		<div class="input-group mb-3">
	                            <div class="input-group-prepend">
	                                <span class="input-group-text border border-right-0"><i class="fas fa-calendar-alt"></i></span>
	                            </div>
	                            <s:textfield name="inceptionDt" id="inceptionDt" class="form-control border datePicker" readonly="true"/>
	                        </div>
	                    </div>
						<div class="col-md-4">
							<label class="labelHeader"><s:text name="motor.policyEndDt"/></label>
	                   		<div class="input-group mb-3">
	                           <s:property value="expiryDt"/>
	                           <s:hidden name="expiryDt" id="expiryDt"/>
	                        </div>
						</div>
					</div>
	            </div>
	        </div>
           	<div class="Card_Parent  mt-4">
	            <div class="card card-1">
	                <h4>Proceed Options</h4>
	                <hr>
	                <div class="row mt-3">
	                	<b>I Need to &nbsp;<s:radio list="#{'Y':'Generate Policy  /','N':'Save Quote  /','E':'Save & Email Quote'}" name="policyGenerateYn" id="policyGenerateYn" value='%{"Y".equals(policyGenerateYn)?"Y":"N"}' onchange="toggleGp();"/></b>
	                </div>
	                <div class="row mt-3">
						<div class="col-xs-12 col-sm-12 col-md-3 col-lg-3" id="installmentDivId" style="display: none;">
							<div class="text"><b><s:text name="label.installment"/></b></div>
                               <s:radio list="#{'Y':'Yes','N':'No'}" name="installmentYN" id="installmentYN" value='%{"Y".equals(installmentYN)?"Y":"N"}' onchange="toggleInstallment('INSTALLMENT_CALC');" />
						</div>
						<div class="col-xs-12 col-sm-12 col-md-3 col-lg-3" id="installmentCalcDivId" style="display: none;">
							<button type="button" class="btn btn-sm btn-primary" data-toggle="modal" data-refresh="true" data-target="#installmentDetailsModal" data-backdrop="static" data-keyboard="false" onclick="installmenDetailsAjax();">Calculate</button>
  								<div id="installmentDetailsModal" class="modal fade" role="dialog">
  									<div class="modal-dialog" id="installmentCalcAjaxNew"></div>
  								</div>
						</div>
               		</div>
		        </div>
		    </div>
	        <div class="row mt-4">
	            <div class="col-md-2 col-2 col-sm-2 offset-md-4">
					<a class="btn btn-danger btn-block" id="bpBackBtnId" style="cursor: pointer;" onclick="fnsubmit('uwMenuHome');hideBtn('bpBackBtnId');">Back</a>
	            </div>
		        <div class="col-md-2 col-2 col-sm-2">
		        	<a class="btn btn-primary btn-block" style="cursor: pointer" id="bpSumbitBtnId" onclick="fnsubmit('updCustInfoHome');hideBtn('bpSumbitBtnId');">Save Quote</a>
		        </div>
	        </div>
	     </div>
		<s:hidden name="quoteNo"/>
		<s:hidden name="applicationNo"/>
		<s:hidden name="menuType"/>
		<s:hidden name="schemeSelected"/>
		<s:hidden name="quoteStatus" id="quoteStatus"/>
	 </s:form>
    <SCRIPT type="text/javascript">
    	
		function fnsubmit(action) {
			try{
				document.extraInformation.action = "${pageContext.request.contextPath}/" + action;
				document.extraInformation.submit();
			}catch(err){
				console.error(err);
			}
		}
		
		function isNumberKey(evt){
			try{
	          var charCode = (evt.which) ? evt.which : evt.keyCode;
	          if (charCode != 46 && charCode > 31 
	            && (charCode < 48 || charCode > 57))
	             return false;

	          return true;
			}catch(err){
				console.log(err);
			}
	    }
		
		function hideBtn(id){
			try{
				document.getElementById(''+id).style.display = 'none';
			}catch(err){
				console.log(err);
			}
		}
		
		cmpyRegNoDis();
		function cmpyRegNoDis(){
			try{
				var cusType = document.extraInformation.customerType.value;
				if(cusType ==  "INDIVIDUAL"){
					document.getElementById('cmpyRegNoDivId').style.display = 'none';
				}else if(cusType ==  "CORPORATE"){
					document.getElementById('cmpyRegNoDivId').style.display = 'block';
				}
			}catch(err){
				console.log(err);
			}
		}
		
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
		
		toggleGp();
		function toggleGp(){
			try{
				var generateYn = document.getElementById('policyGenerateYnY').checked;
				if(generateYn == true){
					document.getElementById('installmentDivId').style.display = 'block';
					document.getElementById('bpSumbitBtnId').innerHTML = 'Make Payment';
				}else{
					document.getElementById('installmentDivId').style.display = 'none';
					document.getElementById('installmentCalcDivId').style.display = 'none';
					document.getElementById('installmentYNN').checked=true;
					document.getElementById('installmentYNY').checked=false;
					document.getElementById('bpSumbitBtnId').innerHTML = 'Save Quote';
				}
			}catch(err){
				console.error(err);
			}
		}
		
		toggleInstallment('INSTALLMENT_CALC');
		function toggleInstallment(type){
			try{
				if(type=="INSTALLMENT_CALC"){
					var instalmentYn = document.getElementById('installmentYNY').checked;
					if(instalmentYn == true){
						document.getElementById('installmentCalcDivId').style.display = 'block';
					}else{
						document.getElementById('installmentCalcDivId').style.display = 'none';
					}
				}else if(type=="INSTALLMENT_SUBMIT"){
					var instalmentAgree = document.getElementById('installmentAgreeYN').checked;
					if(instalmentAgree == true){
						$('#installmentDetailsModal').modal('toggle');
					}else{
						$('#modalErrorSpan').html('Please Agree the Installement');
					}
				}else if(type=="INSTALLMENT_CANCEL"){
					document.getElementById("installmentCalcDivId").style.display = 'none';
					document.getElementById('installmentYNN').checked=true;
					document.getElementById('installmentYNY').checked=false;
					$('#installmentDetailsModal').modal('toggle');
				}
			}catch(err){
				console.error(err);
			}
		}
		 
		 
		function installmenDetailsAjax() {
			try{
				var id = 'installmentCalcAjaxNew';
				var val = '?quoteNo=<s:property value="quoteNo"/>'
						+ '&applicationNo=<s:property value="applicationNo"/>';
				postRequest('${pageContext.request.contextPath}/'+id+'Payment.action'+val, id);
			}catch(err){
				console.error(err);
			}
		}
		
	</SCRIPT>
</body>
</html>