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
	<div id="loading" style="width:100%" align="center">
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
    <s:form name="paymentForm" id="paymentForm" theme="simple">
	    <div class="container QuotationInformationPage mt-5">
	    	<s:include value="./viewPolicyDtls.jsp"/>
	    	<s:include value="./viewPremiumDtls.jsp"/>
	    	<div class="Card_Parent  mt-4">
	            <div class="card card-2">
	            	<div class="row rowFlex">
		            	<div class="col-md-8 col-12">
		                	<h4>Mode Of Payment</h4>
		                </div>
		            </div>
	                <hr>
	                <div class="row rowFlex">
	                    <div class="col-md-2 col-12 colsame p-0 tabsidebar">
	                        <div>
	                            <ul class="nav nav-pills nav-justified flex-column">
									<s:set var="modeOfPaymentList" value="%{modeOfPaymentList}"/>
									 <s:iterator value="#modeOfPaymentList" var="var" status="status">
										 <s:if test='!"1".equalsIgnoreCase(#var.CODE)'>
										 	<li class="nav-item mofPay">
											   <a class='nav-link' style="cursor: pointer;" id='paymentLink<s:property value="#var.CODE"/>' data-toggle="tab" onclick="toggleMop('<s:property value="#var.CODE"/>')">${var.CODEDESC}</a>
											</li>
										 </s:if>
									 </s:iterator>
									 <s:hidden name="modeOfPayment" id="modeOfPayment" value="2"/>
	                            </ul>
	                        </div>
	                    </div>
	                    <div class="col-md-10 col-12 tabsidebar" style="padding: 0px 0px 0px 10px !important;">
		                    <div id="payment2" class='tab-pane container' style="display:none;">
		                    	<div class="row rowFlex">
					            	<div class="col-md-8 col-12">
					                	<h4>Cheque</h4>
					                </div>
					            	<div class="col-md-2 col-12">
					            		<a class="btn btn-primary btn-sm" style="cursor: pointer" onclick="payBankDtls('2');" data-toggle="modal" data-target="#paymentBankDtlsModal" data-backdrop="static">Bank Details</a>
					            	</div>
					            </div>
				                <hr>
				                <div class="row rowFlex">
									<div class="col-md-4">
										<label class="labelHeader"><s:text name="payment.cheque.No" /><font color="red">*</font></label>
										<div class="input-group mb-2">
											<div class="input-group-prepend">
												<span class="input-group-text border border-right-0"><i class="fas fa-list-ol"></i></span>
											</div>
											 <s:textfield name="chequeNo" id="chequeNo" class="form-control border border-left-0" placeholder="Cheque Number" onkeypress="checkNumbers(this);" disabled="#disable2" maxlength="10" />
										</div>
									</div>
									<div class="col-md-4">
										<label class="labelHeader"><s:text name="payment.cheque.Date" /><font color="red">*</font></label>
										<div class="input-group mb-2">
											<div class="input-group-prepend">
												<span class="input-group-text border border-right-0"><i class="fas fa-calendar-alt"></i></span>
											</div>
											<s:textfield name="chequeDate" id="chequeDate" class="form-control border border-left-0 datePicker" placeholder="Cheque Date"   />
										</div>
									</div>
									<div class="col-md-4">
										<label class="labelHeader"><s:text name="payment.cheque.Amount" /><font color="red">*</font></label>
										<div class="input-group mb-2">
											<div class="input-group-prepend">
												<span class="input-group-text border border-right-0"><i class="fas fa-dollar-sign"></i></span>
											</div>
											<s:textfield name="chequeAmount" id="chequeAmount"  class="form-control border border-left-0" placeholder="Cheque Amount" disabled="#disable2" maxlength="20" onkeyup="checkDecimals10_2(this);" value='%{(userType!="admin" && userType!="creditcontroller" && userType!="surveyor" && userType!="underwriter" && (chequeAmount==null || "".equals(chequeAmount)))?#sPayAmount:chequeAmount}'/>
										</div>
									</div>
									<div class="col-md-4">
										<label class="labelHeader"><s:text name="payment.cheque.bankName" /><font color="red">*</font></label>
										<div class="input-group mb-2">
											<div class="input-group-prepend">
												<span class="input-group-text border border-right-0"><i class="fas fa-university"></i></span>
											</div>
												<s:select name="bankName" id="bankName" list="bankNamelist" listKey="CODE" listValue="CODEDESC" headerKey="" headerValue="---Cheque Bank---" class="form-control border border-left-0" placeholder="Cheque Bank"  disabled="#disable1"/>
										</div>
									</div>
									<div class="col-md-4">
										<label class="labelHeader"><s:text name="MICR Code" /><font color="red">*</font></label>
										<div class="input-group mb-2">
											<div class="input-group-prepend">
												<span class="input-group-text border border-right-0"><i class="fas fa-list-ol"></i></span>
											</div>
											<s:textfield name="micrCode" id="micrCode"  class="form-control border border-left-0" placeholder="MICR Code" onkeyup="checkNumbers(this);" disabled="#disable2" maxlength="9" />
										</div>
									</div>
				                </div>
				                <br/>
						  		<div class="row rowFlex">
						  			<div class="col-lg-4">
						  			</div>
						  			<div class="col-lg-4">
						  				<a class="btn btn-primary btn-block" style="cursor: pointer" id="cqSumbitBtnId" onclick="makePayment('2');hideBtn('cqSumbitBtnId');">Convert To Policy</a>
									</div>
						  			<div class="col-lg-4">
						  			</div>
						  		</div>
						  		<br/>
		                    </div>
		                    <div id="payment6" class='tab-pane container'  style="display:none;">
		                    	<div class="row rowFlex">
					            	<div class="col-md-8 col-12">
					                	<h4>Credit / Debit Card</h4>
					                </div>
					            </div>
				                <hr>
				                <div class="row rowFlex" align="center">
									<h5><font color="red">Important Note: Please do not give out your card details to anyone. Madison General Insurance Company Zambia LTD will not be liable for any misuse of your card.</font></h5>
				                </div>
				                <br/>
						  		<div class="row rowFlex">
						  			<div class="col-lg-4">
						  			</div>
						  			<div class="col-lg-4">
						  				<a class="btn btn-primary btn-block" style="cursor: pointer" id="cdSumbitBtnId" onclick="makePayment('6');hideBtn('cdSumbitBtnId');">Proceed To Pay</a>
									</div>
						  			<div class="col-lg-4">
						  			</div>
						  		</div>
						  		<br/>
		                    </div>
		                    <div id="payment9" class='tab-pane container'  style="display:none;">
		                    	<div class="row rowFlex">
					            	<div class="col-md-8 col-12">
					                	<h4>Credit Note</h4>
					                </div>
					            </div>
				                <hr>
				                <div class="row rowFlex" align="center">
									<h5><font color="red">Important Note: Amount will be deducted from you credit Balance.</font></h5>
				                </div>
				                <br/>
						  		<div class="row rowFlex">
						  			<div class="col-lg-4">
						  			</div>
						  			<div class="col-lg-4">
						  				<a class="btn btn-primary btn-block" style="cursor: pointer" id="cSumbitBtnId" onclick="makePayment('9');hideBtn('cSumbitBtnId');">Convert To Policy</a>
									</div>
						  			<div class="col-lg-4">
						  			</div>
						  		</div>
						  		<br/>
		                    </div>
		                    <div id="payment101" class='tab-pane container'  style="display:none;">
		                    	<div class="row rowFlex">
					            	<div class="col-md-8 col-12">
					                	<h4>MTN Mobile Money</h4>
					                </div>
					            </div>
				                <hr>
				                <div class="row rowFlex">
				                	<div class="col-md-6">
										<label class="labelHeader"><s:text name="payment.mtnMobileNo" /><font color="red">*</font></label>
										<div class="input-group mb-3">
											<div class="input-group-prepend">
												<span class="input-group-text border border-right-0"><i class="fas fa-mobile"></i></span>
											</div>
											<s:textfield name="mtnMobileNo" id="mtnMobileNo"  cssClass="form-control border border-left-0" placeholder="MTN Mobile No." disabled="#disable2" onkeypress="checkNumbers(this);" maxlength="10" />
										</div>
									</div>
				                </div>
				                <br/>
						  		<div class="row rowFlex">
						  			<div class="col-lg-4">
						  			</div>
						  			<div class="col-lg-4">
						  				<a class="btn btn-primary btn-block" style="cursor: pointer" id="cSumbitBtnId" onclick="makePayment('101');hideBtn('cSumbitBtnId');">Proceed</a>
									</div>
						  			<div class="col-lg-4">
						  			</div>
						  		</div>
						  		<br/>
		                    </div>
		                    <div id="payment102" class='tab-pane container'  style="display:none;">
		                    	<div class="row rowFlex">
					            	<div class="col-md-8 col-12">
					                	<h4>Airtel Money</h4>
					                </div>
					            </div>
				                <hr>
				                <div class="row rowFlex">
				                	<div class="col-md-6">
										<label class="labelHeader">Airtel Mobile Number<font color="red">*</font></label>
										<div class="input-group mb-3">
											<div class="input-group-prepend">
												<span class="input-group-text border border-right-0"><i class="fas fa-mobile"></i></span>
											</div>
											<s:textfield name="airtelMobileNo" id="airtelMobileNo"  cssClass="form-control border border-left-0" placeholder="Airtel Mobile No." disabled="#disable2" onkeypress="checkNumbers(this);" maxlength="10" />
										</div>
									</div>
				                </div>
				                <br/>
						  		<div class="row rowFlex">
						  			<div class="col-lg-4">
						  			</div>
						  			<div class="col-lg-4">
						  				<a class="btn btn-primary btn-block" style="cursor: pointer" id="cSumbitBtnId" onclick="makePayment('102');hideBtn('cSumbitBtnId');">Proceed</a>
									</div>
						  			<div class="col-lg-4">
						  			</div>
						  		</div>
						  		<br/>
		                    </div>
	                    </div>
	                </div>
		        </div>
		     </div>
	        <div class="row mt-4">
				<div class="col-md-5">
				</div>
				<div class="col-md-2">
					<a class="btn btn-danger btn-block" id="ppBackBtnId" style="cursor: pointer;" onclick="fnsubmit('extraInfoHome');hideBtn('ppBackBtnId');">Back</a>
	            </div>
				<div class="col-md-5">
				</div>
	        </div>
	    </div>
	    <div id="paymentBankDtlsModal" class="modal fade" role="dialog">
	    	<div class="modal-dialog modal-lg">
	    		<div class="modal-content">
			      	<div class="modal-header">
			        	<div class="modal-title">
							<div class="row">
			       				<div class="col-md-12 col-xs-12">
						         	<h3>Bank Details</h3>
						         </div>
			    			</div>
						</div>
			      	</div>
				    <div class="modal-body" id="paymentBankDetails">
				    </div>
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
   	try{
   		var idz = document.paymentForm.modeOfPayment.value;
   		toggleMop(idz);
   	}catch(err){
		console.log(err);
	}
    function toggleMop(id){
    	try{
		   	  var i, tabcontent, tablink;
		   	  tabcontent = document.getElementsByClassName('tab-pane container');
		   	  for (i = 0; i < tabcontent.length; i++){
		   	    tabcontent[i].style.display = 'none';
		   	  }
		   	  
		   	  tabcontent = document.getElementsByClassName('nav-link');
		   	  for (i = 0; i < tabcontent.length; i++){
		   	    tabcontent[i].className = 'nav-link';
		   	  }
		   	  document.getElementById('payment'+id).style.display = 'block';
		   	  document.paymentForm.modeOfPayment.value = id+'';
		   	  document.getElementById('paymentLink'+id).className += ' active';
    	}catch(err){
			console.log(err);
		}
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
	
	function hideBtn(id){
		try{
			document.getElementById(''+id).style.display = 'none';
		}catch(err){
			console.log(err);
		}
	}
	
	function payBankDtls(val){
		try{
			var quoteNo = document.paymentForm.quoteNo.value;
			postRequest('${pageContext.request.contextPath}/bankDtlsAjaxHome.action?modeOfPay='+val+'&currencyType=ZMW&quoteNo='+quoteNo, 'paymentBankDetails');
		}catch(err){
			console.log(err);
		}
	}
	
	function fnsubmit(action) {
		try{
			document.paymentForm.action = "${pageContext.request.contextPath}/" + action;
			document.paymentForm.submit();
		}catch(err){
			console.error(err);
		}
	}
	
	function makePayment(modeOfPay){
		try{
		   	document.paymentForm.modeOfPayment.value = modeOfPay;
			document.paymentForm.action = "${pageContext.request.contextPath}/makePaymentHome.action";
			document.paymentForm.submit();
		}catch(err){
			console.error(err);
		}
	}
	</SCRIPT>
</body>
</html>