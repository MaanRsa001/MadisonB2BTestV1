<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<style type="text/css">
    	
        
       .GetGouteContainer .nav-pills .nav-item .nav-link {
            background-color: #e2a53a;
            color: white;
            border-radius: 0px;
            padding: 20px;

        }
        .card{
            background-color: transparent;
        }

        .motorInsurance {
            background-image: url("assets/Images/realcar-1.jpg");
            background-repeat: no-repeat;
            background-position: center center;
            background-size: cover;
            height: 300px;
        }
       
        .paymentImg {
            background-image: url("assets/Images/Payment_img.jpg");
            background-repeat: no-repeat;
            background-position: center center;
            background-size: cover;
            height: 300px;
        }
        /*.motorInsuranceNew{
        	    height: 270px;
        	    border:1px solid red;
        }*/
        .homeInsurance{
            background-image: url("assets/Images/realhome-2.jpg");
            background-repeat: no-repeat;
            background-position: center center;
            background-size: cover;
            height: 320px;
        }

        h2,
        p {
            font-family: 'Nunito', sans-serif;
            font-weight: bold;
            color: #261e6a;

        }

        .GetGouteContainer .motorInsurance_Contents h1 {
            font-weight: bold;
            color: white;
            font-family: 'Nunito', sans-serif;
        }

        .GetGouteContainer .motorInsurance_Contents p {
            font-family: 'Nunito', sans-serif;
            color: white;
        }

        .GetGouteContainer .motorInsurance_Contents .btn {
            font-size: 15px;
            height: 60px;
            border-radius: 0px;
            color: white;
            border: 2px solid white;
            font-family: 'Nunito', sans-serif;
            font-weight: bold;


        }

        .GetGouteContainer .Card_Parent .btn {
            font-size: 15px;
            height: 60px;
            border-radius: 0px;
            color: #261e6a;
            border: 2px solid #261e6a;
            font-family: 'Nunito', sans-serif;
            font-weight: bold;
        }

        .GetGouteContainer .Card_Parent .btn:hover {
            background-color: #261e6a;
            color: white;

        }

        .GetGouteContainer .motorInsurance_Contents .btn:hover {
            background-color: #e2a53a;

        }

        .GetGouteContainer .motorInsurance_Contents {
            background-color: #261e6a;
            padding: 50px 40px 40px 40px;
        }

        #motor, #home{
            padding: 10px 10px 10px 14px;

        }

        .GetGouteContainer .Card_Parent {
            background: #fff;
            box-shadow: 0 6px 10px rgba(0, 0, 0, .08), 0 0 6px rgba(0, 0, 0, .05);
            padding: 20px;
            border: 4px solid #261e6a;
            min-height: 360px;
            max-height: max-content;
        }

        .GetGouteContainer .card {
            padding: 10px 10px 10px 50px;
            cursor: pointer;
            border: 0px !important;
            border-radius: 0px;

        }

        .GetGouteContainer .card-1 {
            background-image: url("assets/Images/protection.png");
            background-repeat: no-repeat;
            background-position: left;
        }
        .GetGouteContainer .card-2 {
            background-image: url("assets/Images/home-insurance-1.png");
            background-repeat: no-repeat;
            background-position: left;
        }

        .ListContents li {
            font-size: 15px;
            padding: 8px 8px 8px 0px;
            border: 0;
            background-color: inherit;
            color: #261e6a;
            font-family: 'Nunito', sans-serif;

        }

        .GetGouteContainer .Card_Parent .fas .GetGouteContainer .modal-body .fas {
            padding-right: 8px;
        }
         .GetGouteContainer .tab-content{
         	padding:15px;
         }

        /*.modal-content {
            border: 4px solid #261e6a;

        }*/

        .close:focus {
            outline: white;
        }

        marquee {
            font-size: 22px;
            color: #261e6a;
        }

        @media only screen and (max-width: 992px) {
        	.motorInsuranceNew{
        	    max-height: 100%;
        }
            .paddingLeft {
                padding-left: 0px;
               
            }
        }
		
		</style>
	</head>
	<body>
		<s:form name="ProductSelection" id="ProductSelection" action="" theme="simple">
			<div class="GetGouteContainer mt-3">
		        <s:set var="adminMarineCnt" value="0"/>
		        	<s:if test="#session.ProductDetails.size()>0">
					   		<ul class="nav row nav-pills nav-justified">
					   			<s:iterator value="#session.ProductDetails" var="productVarNew" status="stat">
						   			<s:if test='"65".equalsIgnoreCase(#productVarNew.PRODUCT_ID) || "30".equalsIgnoreCase(#productVarNew.PRODUCT_ID) || "92".equalsIgnoreCase(#productVarNew.PRODUCT_ID)' >
						   			   <li class="nav-item col-md-4 col-12">
						                    <a class='nav-link ${stat.index=="0"?" active":""}' data-toggle="pill" href="#prod${productVarNew.PRODUCT_ID}"><b><s:property value="#productVarNew.PRODUCT_NAME"/></b></a>
						                </li>
						   			</s:if>
				                </s:iterator>
				            </ul>
					   		<div class="tab-content mt-2">
						   		<s:iterator value="#session.ProductDetails" var="productVar" status="stat">
								   <s:if test='"65".equalsIgnoreCase(#productVar.PRODUCT_ID)'>
								   		<div class="tab-pane active" id="prod${productVar.PRODUCT_ID}">
						                    <div class="row">
						                        <div class="col-md-12 col-lg-8 motorInsurance_Contents motorInsuranceNew">
							                            <h1>Motor Insurance</h1>
							                            <p>Choose from our range of Motor insurance and find the cover that’s right for you</p>
							                             <div class="row">
								                            <s:if test='"admin".equalsIgnoreCase(#session.usertype) || "creditcontroller".equalsIgnoreCase(#session.usertype) || "surveyor".equalsIgnoreCase(#session.usertype) || "underwriter".equalsIgnoreCase(#session.usertype)'>
																<div class="col-md-4 col-12 col-sm-4 mt-2">
																	<button class="btn btn-outline-primary btn-block" onClick="getAdminHome('<s:property value="#productVar.PRODUCT_ID" />')">GET DETAILS</button>
																</div>
															</s:if>
															<s:else>
															   <s:if test='"RSAIssuer".equalsIgnoreCase(#session.usertype)' >
																   <div class="col-md-4 col-12 col-sm-4 mt-2">
														        		<button class="btn btn-outline-primary btn-block" onClick="getPro('<s:property value="#productVar.PRODUCT_ID" />','NEW','','<s:property value="#productVar.PRODUCT_ID" />','','0','','')">GET DETAILS</button>
														        	</div>
															   </s:if>
															   <s:else>
															   		<%-- <s:if test='"B2CB".equalsIgnoreCase(#session.LoginType)' >
															   			<div class="col-md-3 col-12 col-sm-4 mt-2">
										                                    <button class="btn btn-default btn-block" onClick="newQuote('<s:property value="#productVar.PRODUCT_ID" />')">Get a Quote</button>
										                                </div>
															   		</s:if>
															   		<s:else> --%>
															   			<%-- <div class="col-md-4 col-12 col-sm-4 mt-2">
									                                    <button class="btn btn-default btn-block" onClick="getPro('<s:property value="#productVar.PRODUCT_ID" />','NEW','','<s:property value="#productVar.PRODUCT_ID" />','','0','','')">Get a Quote</button>
										                                </div> --%>
										                                <div class="col-md-2 col-12 col-sm-4 mt-1">
										                                    <button class="btn btn-default btn-block" onClick="newQuote('<s:property value="#productVar.PRODUCT_ID" />')">New Quote</button>
										                                </div>
										                                <s:if test='!"Y".equals(#session.B2cCustYN)'>
										                                <div class="col-md-2 col-12 col-sm-4 mt-1">
										                                    <button class="btn btn-default btn-block" onClick="menuSelect('QE','<s:property value="#productVar.PRODUCT_ID" />')">Quote Registered</button>
										                                </div>
										                                <div class="col-md-2 col-12 col-sm-4 mt-1">
										                                    <button type="button" class="btn btn-default btn-block" onClick="menuSelect('P','<s:property value="#productVar.PRODUCT_ID" />')">Policies</button>
										                                </div>
										                                <div class="col-md-2 col-12 col-sm-4  mt-1">
										                                    <button class="btn btn-default btn-block" onClick="claimIntimate('<s:property value="#productVar.PRODUCT_ID" />')">Intimate Claim</button>
										                                </div>
										                                <div class="col-md-2 col-12 col-sm-4  mt-1">
										                                    <button class="btn btn-default btn-block" onClick="quickRenewal('<s:property value="#productVar.PRODUCT_ID" />')">Quick Renewal</button>
										                                </div>
										                                 </s:if>
										                                 <s:else>
										                                  <div class="col-md-2 col-12 col-sm-4 mt-1">
										                                    <button class="btn btn-default btn-block" onClick="existingSearch('<s:property value="#productVar.PRODUCT_ID" />')">Search Quote</button>
										                                </div>
										                                 </s:else>
															   		<%-- </s:else> --%>
									                            </s:else>
								                            </s:else>
							                            </div>
							                        </div>
						                        <div class="col-md-12 col-lg-4 p-0 paddingMobileImg">
						                            <div class="motorInsurance motorInsuranceNew">
						
						                            </div>
						                        </div>
						
						                    </div>
					
					
					                    <div class="row mt-4">
					                        <div class="col-lg-6 col-md-12 p-0">
					                            <div class="Card_Parent">
					                                <div class="card card-1">
					                                    <h2>Comprehensive</h2>
					                                    <p><i class="fas fa-chevron-circle-right"></i>Comprehensive policy holders are
					                                        covered in the following circumstances:</p>
					                                    <ul class="list-group ListContents">
					                                        <li class="list-group-item list-group-item-primary">
					                                            <i class="fas fa-check-circle"></i>
					                                            Accidental /fire damage to your vehicle
					                                        </li>
					                                        <li class="list-group-item list-group-item-primary">
					                                            <i class="fas fa-check-circle"></i>
					                                            Loss of vehicle or spare parts / accessories
					                                        </li>
					                                        <li class="list-group-item list-group-item-primary">
					                                            <i class="fas fa-check-circle"></i>
					                                            Protection and removal after accident
					                                        </li>
					                                    </ul>
					                                    <div class="row pr-3">
					                                        <div class="col-md-6 col-7 col-sm-6  mt-2">
					                                            <a class="btn btn-default btn-block" data-toggle="modal"
					                                                data-target="#myModal"><label style="margin-top:10px">Tell me more</label></a>
					                                        </div>
					                                    </div>
					                                    
					                                </div>
					                            </div>
					                        </div>
					                        <div class="col-lg-6 col-md-12 pr-0 paddingLeft">
					                            <div class="Card_Parent">
					                                <div class="card card-1">
					                                    <h2>Executive</h2>
					                                    <p><i class="fas fa-chevron-circle-right"></i>This policy is designed for:</p>
					                                    <ul class="list-group ListContents">
					                                        <li class="list-group-item list-group-item-primary">
					                                            <i class="fas fa-check-circle"></i>
					                                            Professionals
					                                        </li>
					                                        <li class="list-group-item list-group-item-primary">
					                                            <i class="fas fa-check-circle"></i>
					                                            Top executives
					                                        </li>
					                                        <li class="list-group-item list-group-item-primary">
					                                            <i class="fas fa-check-circle"></i>
					                                            Officers or diplomats
					                                        </li>
					
					                                    </ul>
					                                    <div class="row pr-3">
					                                        <div class="col-md-6 col-7 col-sm-6  mt-2">
					                                            <a class="btn btn-default btn-block" data-toggle="modal"
					                                                data-target="#myModal1"><label style="margin-top:10px">Tell me more</label></a>
					                                        </div>
					                                    </div>
					                                </div>
					                            </div>
					
					                        </div>
					
					                    </div>
					
					                    <div class="row mt-3">
					                        <div class="col-lg-6 col-md-12 p-0">
					                            <div class="Card_Parent">
					                                <div class="card card-1">
					                                    <h2>Full Third Party Fire And Theft</h2>
					                                    <p><i class="fas fa-chevron-circle-right"></i>This policy covers:</p>
					                                    <ul class="list-group ListContents">
					                                        <li class="list-group-item list-group-item-primary">
					                                            <i class="fas fa-check-circle"></i>
					                                            Loss or damage caused by fire
					                                        </li>
					                                        <li class="list-group-item list-group-item-primary">
					                                            <i class="fas fa-check-circle"></i>
					                                            Lighting
					                                        </li>
					                                        <li class="list-group-item list-group-item-primary">
					                                            <i class="fas fa-check-circle"></i>
					                                            Explosion
					                                        </li>
					                                    </ul>
					                                    <div class="row pr-3">
					                                        <div class="col-md-6 col-7 col-sm-6  mt-2">
					                                            <a class="btn btn-default btn-block" data-toggle="modal"
					                                                data-target="#myModal2"><label style="margin-top:10px">Tell me more</label></a>
					                                        </div>
					                                    </div>
					                                </div>
					                            </div>
					                        </div>
					                        <div class="col-lg-6 col-md-12 pr-0 paddingLeft">
					                            <div class="Card_Parent">
					                                <div class="card card-1">
					                                    <h2>Motor Traders – Internal And External Policies</h2>
					                                    <p><i class="fas fa-chevron-circle-right"></i>
					                                        These policies cover motor vehicles which are in the custody/ care of motor
					                                        traders against:
					                                    </p>
					                                    <ul class="list-group ListContents">
					                                        <li class="list-group-item list-group-item-primary">
					                                            <i class="fas fa-check-circle"></i>
					                                            Accidental / fire damage to the vehicle
					                                        </li>
					                                        <li class="list-group-item list-group-item-primary">
					                                            <i class="fas fa-check-circle"></i>
					                                            Liability to third parties
					                                        </li>
					                                        <li class="list-group-item list-group-item-primary">
					                                            <i class="fas fa-check-circle"></i>
					                                            Theft of vehicle.
					                                        </li>
					                                    </ul>
					                                </div>
					                            </div>
					
					                        </div>
					
					                    </div>
					                    <div class="row mt-3">
					                        <div class="col-lg-6 col-md-12 p-0  paddingMobile">
					                            <div class="Card_Parent">
					                                <div class="card card-1">
					                                    <h2>Third party Liabilities</h2>
					                                    <p><i class="fas fa-chevron-circle-right"></i>This policy covers:</p>
					                                    <ul class="list-group ListContents">
					                                        <li class="list-group-item list-group-item-primary">
					                                            <i class="fas fa-check-circle"></i>
					                                            Third party liabilities (property, death and / or injury) only.
					                                        </li>
					                                    </ul>
					                                   
					                                </div>
					                            </div>
					                        </div>
					                    </div>
					                </div>
							       </s:if>
							       <s:elseif test='"30".equalsIgnoreCase(#productVar.PRODUCT_ID)'>
						       			<div class="tab-pane fade" id="prod${productVar.PRODUCT_ID}">
					                    <div class="row">
					                        <div class="col-md-12 col-lg-6 motorInsurance_Contents">
					                            <h1>Non Motor Insurance</h1>
					                            <p>Choose from our range of Non Motor Insurance and find the cover that’s right for you</p>
					                            <div class="row pr-3">
					                            	<s:if test='"admin".equalsIgnoreCase(#session.usertype) || "creditcontroller".equalsIgnoreCase(#session.usertype) || "surveyor".equalsIgnoreCase(#session.usertype) || "underwriter".equalsIgnoreCase(#session.usertype)'>
														<div class="col-md-4 col-12 col-sm-4 mt-2">
															<button class="btn btn-outline-primary btn-block" onClick="getAdminHome('<s:property value="#productVar.PRODUCT_ID" />')">GET DETAILS</button>
														</div>
													</s:if>
													<s:else>
													 	<s:if test='"RSAIssuer".equalsIgnoreCase(#session.usertype)' >
														   <div class="col-md-4 col-12 col-sm-4 mt-2">
												        		<button class="btn btn-outline-primary btn-block" onClick="getPro('<s:property value="#productVar.PRODUCT_ID" />','NEW','','<s:property value="#productVar.PRODUCT_ID" />','','0','3','HOME INSURANCE - HOME INSURANCE')">GET DETAILS</button>
												        	</div>
													  	</s:if>
												  	</s:else>
												  	<s:else>
						                                <div class="col-md-4 col-12 col-sm-4 mt-2">
						                                    <button class="btn btn-default btn-block" onClick="getPro('<s:property value="#productVar.PRODUCT_ID" />','NEW','','<s:property value="#productVar.PRODUCT_ID" />','','0','<s:property value="#ofsVar.SCHEME_ID" />','<s:property value="#ofsVar.SCHEME_NAME" />')">Get a Quote</button>
						                                </div>
					                                </s:else>
					                            </div>
					                        </div>
					                        <div class="col-md-12 col-lg-6 p-0 paddingMobileImg">
					                            <div class="homeInsurance">
					
					                            </div>
					                        </div>
					
					                    </div>
					
					
					                    <div class="row mt-4">
					                        <div class="col-lg-6 col-md-12 p-0">
					                            <div class="Card_Parent">
					                                <div class="card card-2">
					                                    <h2>Home Buildings</h2>
					                                    <p><i class="fas fa-chevron-circle-right"></i>We'll rebuild or repair your home if it's damaged or destroyed by an incident we cover.</p>
					                                    <p><i class="fas fa-chevron-circle-right"></i>
					                                        Plus, if your home’s damaged by a covered incident and you can’t live in it anymore, we can arrange temporary accommodation for up to a year. That way you'll have a roof over your head while your home’s being repaired or rebuilt.
					                                    </p>
					                                   
					                                    <div class="row pr-3">
					                                        <div class="col-md-6 col-7 col-sm-6  mt-2">
					                                            <a class="btn btn-default btn-block" data-toggle="modal"
					                                                data-target="#myModal"><label style="margin-top:10px">Tell me more</label></a>
					                                        </div>
					                                    </div>
					                                    
					                                </div>
					                            </div>
					                        </div>
					                        <div class="col-lg-6 col-md-12 pr-0 paddingLeft">
					                            <div class="Card_Parent">
					                                <div class="card card-2">
					                                    <h2>Home Contents</h2>
					                                    <p><i class="fas fa-chevron-circle-right"></i>
					                                        We provide new for old replacement of your household contents and personal belongings.
					                                    </p>
					                                    <p><i class="fas fa-chevron-circle-right"></i>
					                                        Even if you accidentally leave a window or door unlocked in your home, we've got you covered.
					                                    </p>
					                                    
					                                    <div class="row pr-3">
					                                        <div class="col-md-6 col-7 col-sm-6  mt-2">
					                                            <a class="btn btn-default btn-block" data-toggle="modal"
					                                                data-target="#myModal1"><label style="margin-top:10px">Tell me more</label></a>
					                                        </div>
					                                    </div>
					                                </div>
					                            </div>
					
					                        </div>
					
					                    </div>
					
					                    <div class="row mt-3">
					                        <div class="col-lg-6 col-md-12 p-0">
					                            <div class="Card_Parent">
					                                <div class="card card-2">
					                                    <h2>Home Buildings & Contents</h2>
					                                    <p><i class="fas fa-chevron-circle-right"></i>
					                                        Protect your home inside and out with combined Buildings and Contents Insurance.
					                                    </p>
					                                    <p><i class="fas fa-chevron-circle-right"></i>
					                                        Your buildings and items will be covered for a range of incidents. Plus, we offer a lifetime guarantee on the workmanship of authorised repairs to your home.
					                                    </p>
					                                   
					                                    <div class="row pr-3">
					                                        <div class="col-md-6 col-7 col-sm-6  mt-2">
					                                            <a class="btn btn-default btn-block" data-toggle="modal"
					                                                data-target="#myModal2"><label style="margin-top:10px">Tell me more</label></a>
					                                        </div>
					                                    </div>
					                                </div>
					                            </div>
					                        </div>
					                        <div class="col-lg-6 col-md-12 pr-0 paddingLeft">
					                            <div class="Card_Parent">
					                                <div class="card card-2">
					                                    <h2>Landlord</h2>
					                                    <p><i class="fas fa-chevron-circle-right"></i>
					                                        Protect your investment property, its contents, or both. We can cover damage to your property from a range of incidents like theft, vandalism, fire, storms and lightning.
					                                    </p>
					                                   
					                                </div>
					                            </div>
					
					                        </div>
					
					                    </div>
					                    <div class="row mt-3">
					                        <div class="col-lg-6 col-md-12 p-0  paddingMobile">
					                            <div class="Card_Parent">
					                                <div class="card card-2">
					                                    <h2>Single Item Insurance</h2>
					                                    <p><i class="fas fa-chevron-circle-right"></i>
					                                        Simple, affordable insurance for things in your home, such as your laptop, tablet or TV. You choose what to insure and how much to insure it for.
					                                    </p>
					                                </div>
					                            </div>
					                        </div>
					                    </div>
					                </div>
							       </s:elseif>
							       <%-- <s:elseif test='"33".equalsIgnoreCase(#productVar.PRODUCT_ID)'>
							       		
							       </s:elseif>
							       <s:elseif test='"0".equalsIgnoreCase(#adminMarineCnt) && ("3".equalsIgnoreCase(#productVar.PRODUCT_ID) || "11".equalsIgnoreCase(#productVar.PRODUCT_ID))'>
							       		
							       </s:elseif> --%>
							       <s:elseif test='"92".equalsIgnoreCase(#productVar.PRODUCT_ID)'>
								   		<div class="tab-pane fade" id="prod${productVar.PRODUCT_ID}">
						                    <div class="row">
						                        <div class="col-md-12 col-lg-8 motorInsurance_Contents">
						                            <h1>MGen Payment</h1>
						                             <div class="row">
							                            <s:if test='"admin".equalsIgnoreCase(#session.usertype) || "creditcontroller".equalsIgnoreCase(#session.usertype) || "surveyor".equalsIgnoreCase(#session.usertype) || "underwriter".equalsIgnoreCase(#session.usertype)'>
															<div class="col-md-4 col-12 col-sm-4 mt-2">
																<button class="btn btn-outline-primary btn-block" onClick="getAdminHome('<s:property value="#productVar.PRODUCT_ID" />')">GET DETAILS</button>
															</div>
														</s:if>
						                            </div>
						                        </div>
						                        <div class="col-md-12 col-lg-4 p-0 paddingMobileImg">
						                            <div class="paymentImg motorInsuranceNew">
						
						                            </div>
						                        </div>
						                    </div>
					                	</div>
							       </s:elseif>
						        </s:iterator>
					       </div>
				       	  
		            </s:if>
		            <s:else>
						Sorry! No Products Available
					</s:else>
					
					<div class="modal fade" id="myModal">
				        <div class="modal-dialog modal-lg modal-dialog-centered">
				            <div class="modal-content">
				                <div class="modal-header">
				                	<h5 class="modal-title">Comprehensive</h5>
				                    <button type="button" class="close" data-dismiss="modal">
				                        <i class="far fa-times-circle text-light"></i>
				                    </button>
				
				                </div>
				                <div class="modal-body">
				                    <ul class="list-group ListContents">
				                        <li class="list-group-item list-group-item-primary">
				                            <i class="fas fa-check-circle"></i>
				                            Riot and strike cover (non-political)
				                        </li>
				                        <li class="list-group-item list-group-item-primary">
				                            <i class="fas fa-check-circle"></i>
				                            Medical expenses cover up to a stated limit (private cars only)
				                        </li>
				                        <li class="list-group-item list-group-item-primary">
				                            <i class="fas fa-check-circle"></i>
				                            Personal accident cover for insured and spouse up to a stated limit (private
				                            cars only)
				                        </li>
				                        <li class="list-group-item list-group-item-primary">
				                            <i class="fas fa-check-circle"></i>
				                            Authority to repair damage to vehicle up to a stated limit.
				                        </li>
				                    </ul>
				                </div>
				
				            </div>
				        </div>
				    </div>
				
				    <!-- The Modal Executive -->
				    <div class="modal fade" id="myModal1">
				        <div class="modal-dialog modal-lg modal-dialog-centered">
				            <div class="modal-content">
				                <div class="modal-header">
				                    <h5 class="modal-title">Executive</h5>
				                    <button type="button" class="close" data-dismiss="modal">
				                        <i class="fas fa-times-circle text-light"></i>
				                    </button>
				
				                </div>
				                <div class="modal-body">
				                    <ul class="list-group ListContents">
				                        <li class="list-group-item list-group-item-primary">
				                            <i class="fas fa-check-circle"></i>
				                            MGen provides comprehensive cover at a favorable rate. We reserve the right to deem who is
				                            eligible to be considered in this category.
				                        </li>
				                    </ul>
				                </div>
				
				            </div>
				        </div>
				    </div>
				
				    <!-- The Modal Full Third Party Fire And Theft
				-->
				    <div class="modal fade" id="myModal2">
				        <div class="modal-dialog modal-lg modal-dialog-centered">
				            <div class="modal-content">
				                <div class="modal-header">
				                    <h5 class="modal-title">Full Third Party Fire And Theft</h5>
				                    <button type="button" class="close" data-dismiss="modal">
				                        <i class="fas fa-times-circle text-light"></i>
				                    </button>
				
				                </div>
				                <div class="modal-body">
				                    <ul class="list-group ListContents">
				                        <li class="list-group-item list-group-item-primary">
				                            <i class="fas fa-check-circle"></i>
				                            Theft, or attempted theft.
				                        </li>
				                        <li class="list-group-item list-group-item-primary">
				                            <i class="fas fa-check-circle"></i>
				                            Third party liabilities (property, death and / or injury).
				                        </li>
				                    </ul>
				                </div>
				
				            </div>
				        </div>
				    </div>
    
			</div>
			<s:hidden name="selectProd" />
			<s:hidden name="schemeId" />
			<s:hidden name="contenTypeId" />
			<s:hidden name="menuType" />
			<s:hidden name="reqFrom" />
			<s:hidden name="productId" />
			<s:hidden name="product" />
			<s:hidden name="quoteStatus" />
			<s:hidden name="loginId" />
			
		</s:form>
		<script type="text/javascript">
		function getPro(pid,mod,dtdiff,homepid,qopt,tpid,officeScheme,OFSName) {
			if(pid=='3') {
				document.ProductSelection.selectProd.value=pid;
				document.ProductSelection.menuType.value='CHART';
				document.ProductSelection.reqFrom.value='PS';
				document.ProductSelection.productId.value=pid;
				document.ProductSelection.action ="${pageContext.request.contextPath}/initReport.action";
				document.ProductSelection.submit();
			} else if(pid=='11') {
				document.ProductSelection.selectProd.value=pid;
				//document.ChooseProduct.QuoteOpt.value=qopt;
				document.ProductSelection.action ="${pageContext.request.contextPath}/ViewOpenCovers.jsp";
				document.ProductSelection.submit();
			} else if(pid=='31'||pid=='32'||pid=='33'||pid=='34'||pid=='41' || pid=='65' || pid=='30') {
				document.ProductSelection.selectProd.value=pid;
				if(pid=='30') {
					document.ProductSelection.schemeId.value=officeScheme;
					document.ProductSelection.contenTypeId.value=tpid;
				}
				document.ProductSelection.menuType.value='CHART';
				document.ProductSelection.reqFrom.value='PS';
				document.ProductSelection.productId.value=pid;
				document.ProductSelection.action ="${pageContext.request.contextPath}/initReport.action";
				document.ProductSelection.submit();
			}
			return false;
		}
		function getAdminHome(productId) {
			document.ProductSelection.product.value=productId;
			document.ProductSelection.loginId.value='<s:property value="#session.user"/>';
			if(productId=='92')
				document.ProductSelection.action ="${pageContext.request.contextPath}/Logindoauth.action";
			else
				document.ProductSelection.action ="${pageContext.request.contextPath}/homeAdmin.action";
			document.ProductSelection.submit();
		}
		function getLearMore(productId) {
			document.ProductSelection.productId.value=productId;
			document.ProductSelection.action ="${pageContext.request.contextPath}/LearnMore.action?";
			document.ProductSelection.submit();
		}
		function claimIntimate(productId) {
			document.ProductSelection.productId.value=productId;
			document.ProductSelection.action ="${pageContext.request.contextPath}/insertB2BClaimIntimation.action?";
			document.ProductSelection.submit();
		}
		function quickRenewal(productId) {
			document.ProductSelection.productId.value=productId;
			document.ProductSelection.action ="${pageContext.request.contextPath}/pageQuickRenewal.action?";
			document.ProductSelection.submit();
		}
		function downloadApk(){
			document.ProductSelection.action ="${pageContext.request.contextPath}/HomeapkDownload.action?";
			document.ProductSelection.submit();
		}
		function claimStatus(productId){
			document.ProductSelection.productId.value=productId;
			document.ProductSelection.action ="${pageContext.request.contextPath}/initSearchClaimIntimation.action?";
			document.ProductSelection.submit();
		}
		function menuSelect(obj,productId)
		{	
			//var user='<s:property value="#session.user"/>';
			//alert(user);
			document.ProductSelection.menuType.value=obj;
			document.ProductSelection.productId.value=productId;
			//document.ProductSelection.loginId.value='<s:property value="#session.user"/>';
			document.ProductSelection.action='${pageContext.request.contextPath}/initReport.action';	
			document.ProductSelection.submit();
		}
		function existingSearch(){
			document.ProductSelection.action='${pageContext.request.contextPath}/initB2CSearchReport.action';	
			document.ProductSelection.submit();
		}
		function newQuote(productId)
		{
			var user='<s:property value="#session.RSAISSUER"/>';
			var loginType='<s:property value="#session.LoginType"/>';
			if(null=='<s:property value="#session.RSAISSUER"/>' || ''=='<s:property value="#session.RSAISSUER"/>')
			{
				document.ProductSelection.productId.value=productId;
				document.ProductSelection.quoteStatus.value='QE';	
				if(productId=='65' && loginType!='B2CB')
					document.ProductSelection.action="${pageContext.request.contextPath}/editQuoteCustDataCustomer.action";
				else if(productId=='65' && loginType=='B2CB')
					document.ProductSelection.action="${pageContext.request.contextPath}/editQuoteNewMotor.action";
				
			}else{
				document.ProductSelection.quoteStatus.value='QE';
				if(productId=='65')
					document.ProductSelection.action="${pageContext.request.contextPath}/newQuoteIssuerCustomer.action";
			}
			document.ProductSelection.submit();
			return false;
		}
		</script>
		
	</body>
</html>