<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<style type="text/css">
    	.parent {
    		color: #FFFFFF;
    	}
    	.hover-content {
		    display:none;
		}
						
		/*.parent:hover .hover-content {
		    display:block;
		    width: 90%;
    		position: absolute;
    		text-align: left;   		
		}*/
		.hover-content {
		    display:block;
		    width: 90%;
    		position: absolute;
    		text-align: left;   		
		}
		.btnContent {
			position: absolute;
			bottom: 30px;
			width: 90%;
			display:none;
		}
		.parent h3 {
			text-align: center;
			color: #FFF;
		} 
		/*.parent:hover .btnContent {
			display:block;
		}*/
		.btnContent {
			display:block;
		}
		.parent:focus .btnContent {
			display:block;
		}
		
		.panel-body {
			min-height: 400px;			
		}
		
		@media screen and (max-width: 600px) {
			.panel-body {
				min-height: 500px;			
			}
		}
		
		/*.carInsurance {
			background: url('images/car_insurance.jpg');
			background-size: cover;
		}
		
		.travelInsurance {
			background: url('images/travel_insurance.jpg');
			background-size: cover;
		}
		
		.marineInsurance {
			background: url('images/marine_insurance.jpg');
			background-size: cover;
		}
		
		.homeInsurance {
			background: url('images/home_insurance.jpg');
			background-size: cover;
		}
		
		.carInsurance:hover {
			background: url('images/car_insurance_a.jpg');
			background-size: cover;
		}
		
		.travelInsurance:hover {
			background: url('images/travel_insurance_a.jpg');
			background-size: cover;
		}
		
		.marineInsurance:hover {
			background: url('images/marine_insurance_a.jpg');
			background-size: cover;
		}
		
		.homeInsurance:hover {
			background: url('images/home_insurance_a.jpg');
			background-size: cover;
		}*/
		
		.carInsurance {
			background: url('images/car_insurance_a.jpg');
			background-size: cover;
		}
		
		.travelInsurance {
			background: url('images/travel_insurance_a.jpg');
			background-size: cover;
		}
		
		.marineInsurance {
			background: url('images/marine_insurance_a.jpg');
			background-size: cover;
		}
		
		.homeInsurance {
			background: url('images/home_insurance_a.jpg');
			background-size: cover;
		}
		
		.btn {
			width: 100%;
		}
		
		.btnContent .col-xs-6 {
			margin-bottom: 5px;
		}
		
		.btnContent .col-xs-12 {
			margin-bottom: 5px;
		}
		.shadow {
		text-shadow:1px 1px #000000;
		}
		</style>
	</head>
	<body>
		<s:form name="ProductSelection" id="ProductSelection" action="" theme="simple">
			<div class="row">
				<div class="col-xs-6 col-sm-6 col-md-3 col-lg-3">
	        		<s:a class="btn btn-lg btn-primary" style="text-decoration: none;" href="#" onClick="claimStatus('%{#productVar.PRODUCT_ID}')">Claim Status Search</s:a>
	        	</div>
				<div class="col-xs-6 col-sm-4 col-md-7 col-lg-7">
					<div class="pullRight">
						<!--<span style="font-weight: bold;"> <a href="" onclick="downloadApk();" target="_blank" ><span style="font-size: 20px;"> <i class="fa fa-download"></i> Download</span> <br>Android Mobile App</a></span> <br/>-->
						<span style="font-size: 16px; color:#337ab7; font-weight: bold;"> <i class="fa fa-download"></i> Mobile App</span><br/>
						<%--<a href="" target="_blank" onclick="downloadApk();">
							<img src="${pageContext.request.contextPath}/images/Android-Logo.jpg"  width="50" height="35" border="0" >
						</a>--%>
						<a href="https://play.google.com/store/apps/details?id=com.pss.madison" target="_blank">
							<img src="${pageContext.request.contextPath}/images/Android-Logo.jpg"  width="50" height="35" border="0" >
						</a>		<b>|</b>
						<a href="https://apps.apple.com/us/app/id1522891577" target="_blank"> 
							<img src="${pageContext.request.contextPath}/images/Apple-Logo.jpg"  width="45" height="45" border="0" >
						</a>
					</div>
				</div>
				<div class="col-xs-6 col-sm-2 col-md-2 col-lg-2">
					<div class="" align="center">
						<a href="https://eservices.gov.zm/#/service/4" target="_blank"> 
							<img src="${pageContext.request.contextPath}/images/RTSA.png"  width="40" height="40" border="0" ><br><span style="font-weight: bold;font-size: 15px;">Buy Road Tax</span>
						</a>
					</div>
				</div>
			</div>
			<br class = clearfix/>
			<s:set var="adminMarineCnt" value="0"/>
			<div class="row">
				<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
					<div class="panel panel-primary">	
						<div class="panel-body" align="center">
							<s:if test="#session.ProductDetails.size()>0">
								<s:iterator value="#session.ProductDetails" var="productVar" status="stat">
									<s:if test='"65".equalsIgnoreCase(#productVar.PRODUCT_ID)'>
										<div class="col-xs-12 col-sm-12 col-md-6">
											<div class="panel panel-primary">
												<div class="panel-body parent carInsurance" onclick="">
													<h3 class ="shadow"> <s:property value="#productVar.PRODUCT_NAME"/> </h3>
												    <div class="hover-content" >
												    	<p style="color:red; font-weight: bold;">Benefits of Buying online</p>
												    	<div style="color: #000000; font-weight: bold;">
												    		<ul>
												    			<li> Special Discount </li>
												    			<li> Door-step documents delivery </li>
												    			<li> Door-step inspection of property </li>
												    			<li> Convenient and can be accessed from any location with mobile network coverage. </li>
												    			<li> Easy to use </li>
												    			<li> No need to wait in long queues </li>
												    			<li> You can buy insurance even on public holidays </li>
												    			<li> You can buy insurance after hours </li>
												    			<li> You can review policies in advance before your current policies expires </li>
												    			<li> You can buy/pay for policies of your family members even when abroad </li>
												    			<li> You can pay the installments interest free </li>
												    			<li> Flexible payment options: Check deposit | Cash deposit | debit/Credit cards </li>
												    			<li> Instant notification via Email and SMS </li>
												    		</ul>
												    	</div>
												    </div>
													<div class="row btnContent">
														<s:if test='"admin".equalsIgnoreCase(#session.usertype) || "creditcontroller".equalsIgnoreCase(#session.usertype) || "surveyor".equalsIgnoreCase(#session.usertype) || "underwriter".equalsIgnoreCase(#session.usertype)'>
															<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
																<s:a class="btn btn-lg btn-primary" style="text-decoration: none;" href="#" onClick="getAdminHome('%{#productVar.PRODUCT_ID}')">GET DETAILS</s:a>
															</div>
														</s:if>
														<s:else>
														   <s:if test='"RSAIssuer".equalsIgnoreCase(#session.usertype)' >
															   <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
													        		<s:a class="btn btn-lg btn-primary" style="text-decoration: none;" href="#" onClick="getPro('%{#productVar.PRODUCT_ID}','NEW','','%{#productVar.PRODUCT_ID}','','0','','')">GET DETAILS</s:a>
													        	</div>
														   </s:if>
														   <s:else>
													        	<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
													        		<s:a class="btn btn-lg btn-primary" style="text-decoration: none;" href="#" onClick="getPro('%{#productVar.PRODUCT_ID}','NEW','','%{#productVar.PRODUCT_ID}','','0','','')">Get a Quote</s:a>
													        	</div>
													        	<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
													        		<s:a class="btn btn-lg btn-warning" style="text-decoration: none;" href="#" onClick="claimIntimate('%{#productVar.PRODUCT_ID}')">Intimate Claim</s:a>
													        	</div>
													        	<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
													        		<s:a class="btn btn-lg btn-success" style="text-decoration: none;" href="#" onClick="quickRenewal('%{#productVar.PRODUCT_ID}')">Quick Renewal</s:a>
													        	</div>
<!--													        	<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">-->
<!--													        		<s:a href="#" class="btn btn-lg btn-default"  onClick="getLearMore('%{#productVar.PRODUCT_ID}')"> Learn More </s:a>-->
<!--													        	</div>-->
												        	</s:else>
												        </s:else>
											        </div>
												</div>
											</div>
										</div>
									</s:if>
									<s:elseif test='"30".equalsIgnoreCase(#productVar.PRODUCT_ID)'>
										<div class="col-xs-12 col-sm-12 col-md-6">
											<div class="panel panel-primary">
												<div class="panel-body parent homeInsurance" onclick=""> 
													<h3 class ="shadow"> <s:property value="#productVar.PRODUCT_NAME"/> </h3>
												    <div class="hover-content">
												    	<p style="color:red; font-weight: bold;">Benefits of Buying online</p>
												    	<div style="color:#000000 ; font-weight: bold;">
												    		<ul>
												    			<li> Special Discount </li>
												    			<li> Door-step documents delivery </li>
												    			<li> Door-step inspection of property </li>
												    			<li> Convenient and can be accessed from any location with mobile network coverage. </li>
												    			<li> Easy to use </li>
												    			<li> No need to wait in long queues </li>
												    			<li> You can buy insurance even on public holidays </li>
												    			<li> You can buy insurance after hours </li>
												    			<li> You can review policies in advance before your current policies expires </li>
												    			<li> You can buy/pay for policies of your family members even when abroad </li>
												    			<li> You can pay the installments interest free </li>
												    			<li> Flexible payment options: Check deposit | Cash deposit | debit/Credit cards </li>
												    			<li> Instant notification via Email and SMS </li>
												    		</ul>
												    	</div>
												    </div>
													<div class="row btnContent">
														<s:if test='"admin".equalsIgnoreCase(#session.usertype) || "creditcontroller".equalsIgnoreCase(#session.usertype) || "surveyor".equalsIgnoreCase(#session.usertype) || "underwriter".equalsIgnoreCase(#session.usertype)'>
															<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
																<s:a class="btn btn-lg btn-primary" style="text-decoration: none;" href="#" onClick="getAdminHome('%{#productVar.PRODUCT_ID}')">GET DETAILS</s:a>
															</div>
														</s:if>
														<s:else>
															 <s:if test='"RSAIssuer".equalsIgnoreCase(#session.usertype)' >
																   <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
														        		<s:a class="btn btn-lg btn-primary" style="text-decoration: none;" href="#" onClick="getPro('%{#productVar.PRODUCT_ID}','NEW','','%{#productVar.PRODUCT_ID}','','0','3','HOME INSURANCE - HOME INSURANCE')">GET DETAILS</s:a>
														        	</div>
															  </s:if>
															  <s:else>
														        <%--<s:iterator value="officeProductScheme" var="ofsVar" status="ofsStat">--%>
																	<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
														        		<s:a class="btn btn-lg btn-primary" style="text-decoration: none;" href="#" onClick="getPro('%{#productVar.PRODUCT_ID}','NEW','','%{#productVar.PRODUCT_ID}','','0','%{#ofsVar.SCHEME_ID}','%{#ofsVar.SCHEME_NAME}')">Get a Quote</s:a>
														        	</div>
													        	<%--</s:iterator>--%>
														        	<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
													        			<s:a href="#" class="btn btn-lg btn-default"  onClick="getLearMore('%{#productVar.PRODUCT_ID}')"> Learn More </s:a>
													        		</div>
													        </s:else>
												        </s:else>
											        </div>
												</div>
											</div>
										</div>
									</s:elseif>
									<s:elseif test='"33".equalsIgnoreCase(#productVar.PRODUCT_ID)'>
										<div class="col-xs-12 col-sm-12 col-md-6">
											<div class="panel panel-primary">
												<div class="panel-body parent travelInsurance" onclick="">
													<h3 class ="shadow"> <s:property value="#productVar.PRODUCT_NAME"/> </h3>
												    <div class="hover-content">
												    	<p style="color:red; font-weight: bold;">Benefits of Buying online</p>
												    	<div style="color:#000000 ; font-weight: bold;">
												    		<ul>
												    			<li> Special Discount </li>
												    			<li> Door-step documents delivery </li>
												    			<li> Door-step inspection of property </li>
												    			<li> Convenient and can be accessed from any location with mobile network coverage. </li>
												    			<li> Easy to use </li>
												    			<li> No need to wait in long queues </li>
												    			<li> You can buy insurance even on public holidays </li>
												    			<li> You can buy insurance after hours </li>
												    			<li> You can review policies in advance before your current policies expires </li>
												    			<li> You can buy/pay for policies of your family members even when abroad </li>
												    			<li> You can pay the installments interest free </li>
												    			<li> Flexible payment options: Check deposit | Cash deposit | debit/Credit cards </li>
												    			<li> Instant notification via Email and SMS </li>
												    		</ul>
												    	</div>
												    </div>
													<div class="row btnContent">
														<s:if test='"admin".equalsIgnoreCase(#session.usertype) || "creditcontroller".equalsIgnoreCase(#session.usertype) || "surveyor".equalsIgnoreCase(#session.usertype) || "underwriter".equalsIgnoreCase(#session.usertype)'>
															<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
																<s:a class="btn btn-lg btn-primary" style="text-decoration: none;" href="#" onClick="getAdminHome('%{#productVar.PRODUCT_ID}')">GET DETAILS</s:a>
															</div>
														</s:if>
														<s:else>
															<s:if test='"RSAIssuer".equalsIgnoreCase(#session.usertype)' >
																   <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
												        				<s:a class="btn btn-lg btn-primary" style="text-decoration: none;" href="#" onClick="getPro('%{#productVar.PRODUCT_ID}','NEW','','%{#productVar.PRODUCT_ID}','','0','','')">GET DETAILS</s:a>
												        			</div>
															   </s:if>
															   <s:else>
														        	<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
														        		<s:a class="btn btn-lg btn-primary" style="text-decoration: none;" href="#" onClick="getPro('%{#productVar.PRODUCT_ID}','NEW','','%{#productVar.PRODUCT_ID}','','0','','')">Get a Quote</s:a>
														        	</div>
														        	<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
														        		<s:a href="#" class="btn btn-lg btn-default"  onClick="getLearMore('%{#productVar.PRODUCT_ID}')"> Learn More </s:a>
														        	</div>
													        </s:else>
												        </s:else>
											        </div>
												</div>
											</div>
										</div>
									</s:elseif>
									<s:elseif test='"0".equalsIgnoreCase(#adminMarineCnt) && ("3".equalsIgnoreCase(#productVar.PRODUCT_ID) || "11".equalsIgnoreCase(#productVar.PRODUCT_ID))'>
										<div class="col-xs-12 col-sm-12 col-md-6">
											<div class="panel panel-primary">
												<div class="panel-body parent marineInsurance" onclick="">
													<h3>
														<s:if test='"admin".equalsIgnoreCase(#session.usertype)'>
															<s:set var="adminMarineCnt" value="1"/>
															MARINE INSURANCE
														</s:if>
														<s:else>
															<h3 class ="shadow"> <s:property value="#productVar.PRODUCT_NAME"/> </h3>
														</s:else>
													</h3>
												    <div class="hover-content">
												    	<p style="color:red; font-weight: bold;">Benefits of Buying online</p>
												    	<div style="color:#000000 ; font-weight: bold;">
												    		<ul>
												    			<li> Special Discount </li>
												    			<li> Door-step documents delivery </li>
												    			<li> Door-step inspection of property </li>
												    			<li> Convenient and can be accessed from any location with mobile network coverage. </li>
												    			<li> Easy to use </li>
												    			<li> No need to wait in long queues </li>
												    			<li> You can buy insurance even on public holidays </li>
												    			<li> You can buy insurance after hours </li>
												    			<li> You can review policies in advance before your current policies expires </li>
												    			<li> You can buy/pay for policies of your family members even when abroad </li>
												    			<li> You can pay the installments interest free </li>
												    			<li> Flexible payment options: Check deposit | Cash deposit | debit/Credit cards </li>
												    			<li> Instant notification via Email and SMS </li>
												    		</ul>
												    	</div>
												    </div>
													<div class="row btnContent">
														<s:if test='"admin".equalsIgnoreCase(#session.usertype) || "creditcontroller".equalsIgnoreCase(#session.usertype) || "surveyor".equalsIgnoreCase(#session.usertype) || "underwriter".equalsIgnoreCase(#session.usertype)'>
															<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
																<s:a class="btn btn-lg btn-primary" style="text-decoration: none;" href="#" onClick="getAdminHome('%{#productVar.PRODUCT_ID}')">GET DETAILS</s:a>
															</div>
														</s:if>
														<s:else>
															<s:if test='"RSAIssuer".equalsIgnoreCase(#session.usertype)' >
																   <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
													        			<s:a class="btn btn-lg btn-primary" style="text-decoration: none;" href="#" onClick="getPro('%{#productVar.PRODUCT_ID}','NEW','','%{#productVar.PRODUCT_ID}','','0','','')">GET DETAILS</s:a>
													        		</div>
															   </s:if>
															   <s:else>
														        	<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
													        		<s:a class="btn btn-lg btn-primary" style="text-decoration: none;" href="#" onClick="getPro('%{#productVar.PRODUCT_ID}','NEW','','%{#productVar.PRODUCT_ID}','','0','','')">Get a Quote</s:a>
													        	</div>
													        	<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
													        		<s:a href="#" class="btn btn-lg btn-default"  onClick="getLearMore('%{#productVar.PRODUCT_ID}')"> Learn More </s:a>
													        	</div>
													        </s:else>
												        </s:else>
											        </div>
												</div>
											</div>
										</div>
									</s:elseif>
								</s:iterator>
							</s:if>
							<s:else>
								Sorry! No Products Available
							</s:else>
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
			document.ProductSelection.action ="${pageContext.request.contextPath}/addClaimClaimIntimation.action?";
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
		</script>
	</body>
</html>