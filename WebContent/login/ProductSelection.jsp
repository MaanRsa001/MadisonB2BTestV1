<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>	
	<style type="text/css">
    	.parent {
    		color: #FFFFFF;
    	}
    	.hover-content {
		    display:none;
		}
						
		.parent:hover .hover-content {
		    display:block;
    		position: absolute;
    		width: 90%;   		
		}
		
		.btnContent {
			position: absolute;
			bottom: 30px;
			width: 90%;
			display:none;
		}
		
		.parent:hover .btnContent {
			display:block;
		}
		
		.panel-body {
			min-height: 300px;			
		}
		
		.smallBox {
			max-height: 140px;			
		}
		
		.carInsurance {
			background: url('images/car_insurance.jpg');
			background-size: cover;
		}
		
		.parent:hover .carInsurance {
			background: url('images/car_insurance1.jpg');
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
		
		.lifeInsurance {
			background: url('images/life_insurance.jpg');
			background-size: cover;
		}
		
		.officePack {
			background: url('images/office_pack.jpg');
			background-size: cover;
		}
		
		.medicalInsurance {
			background: url('images/medical_insurance.jpg');
			background-size: cover;
		}
		
		.engineeringInsurance {
			background: url('images/engineering_insurance.jpg');
			background-size: cover;
		}
		
		.businessPack {
			background: url('images/business_pack.jpg');
			background-size: cover;
		} 
		
		.tradesCover {
			background: url('images/trades_cover.jpg');
			background-size: cover;
		}
		
		.farmPack {
			background: url('images/farm_pack.jpg');
			background-size: cover;
		}
		
		.professionalCover {
			background: url('images/professonal_cover.jpg');
			background-size: cover;
		}
		
		.itProfessionalCover {
			background: url('images/it_professionals_cover.jpg');
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
		
    </style>
</head>
<body>
<%@ include file="../login/home1.jsp" %>
<%@ include file="sessionsCheckNormal.jsp"%>

<% 
	if(session.getAttribute("language") != null)
        session.removeAttribute("language");
	if(session.getAttribute("freight")!=null)
		session.removeAttribute("freight");
	if(session.getAttribute("freightBroker")!=null)
		session.removeAttribute("freightBroker");		
	if(session.getAttribute("product_id")!=null)
		session.removeAttribute("product_id");
	if(session.getAttribute("typeOfProduct")!=null)
		session.removeAttribute("typeOfProduct");
	if(session.getAttribute("quoteNo")!=null)
		session.removeAttribute("quoteNo");
	if(session.getAttribute("openCoverNo") !=null)
		session.removeAttribute("openCoverNo");
	if(session.getAttribute("brokerCode") !=null) {
		session.removeAttribute("brokerCode");
	}
%>
<jsp:useBean id= "product" class = "com.maan.product.ProductSelection">
	<jsp:setProperty name= "product" property = "*"/>
	<jsp:setProperty name="product" property="out" value="<%= response.getWriter() %>" /> 
</jsp:useBean>
<jsp:useBean id= "freightProduct" class = "com.maan.broker.DAO.FreightCreationBean"> </jsp:useBean>
<jsp:useBean id= "customer" class = "com.maan.broker.DAO.CustomerCreationBean"> </jsp:useBean>
<%	String path = request.getContextPath(); %>
<br class="clear" />
<s:form id="" name="ChooseProduct" method="post" action="" theme="simple">
<div class="row">
	<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
		<div class="panel panel-primary">			
			<div class="panel-body" align="center">
				<% 
					String s1=""+session.getAttribute("mode"); 
					String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
					String loginCode	=	(String)session.getAttribute("user");
					String loginType	=	(String)session.getAttribute("LoginIdType");
					String selectedBranch = (String)session.getAttribute("selectedBranch");
					product.setLoginCode(loginCode);
					
					String[][] productdetails=new String[0][0];
					String[][] officeScheme = new String[0][0];
					
					String prodIdentifier="";
					String dtdiff="";
					//Royal World WOrk
					HashMap brokerDetails = new HashMap();
					String brokerBra = "";
					String actualBranch = "";
					Map branchDt = product.getBrokerUserBranch(loginCode,loginType,selectedBranch);
					if(branchDt.size()>0) {
						brokerBra = (String)branchDt.get("defaultBranch");
						actualBranch = (String)branchDt.get("actualBranch");
					}
					brokerDetails = product.getBrokerUserDetails(brokerBra);
					session.setAttribute("LoginBranchCode",brokerBra);//Default Branch Code
					session.setAttribute("adminBranch",actualBranch);//Actual Branch Code
					session.setAttribute("BrokerDetails",brokerDetails);
					session.setAttribute("LANG",brokerDetails.get("LANG"));
					//For RSA Issure
					boolean statusRSAIssuer = product.getStatusRSAIssuer(loginCode);
					if(statusRSAIssuer) {
						session.setAttribute("RSAISSUER", (String) session.getAttribute("user"));	
					}
				
					String rsaIssuer = (String)session.getAttribute("RSAISSUER");
					//productdetails=product.getProductDetailsModified(loginCode,brokerBra,rsaIssuer);
					if(rsaIssuer==null) { 
						productdetails=product.getProductDetailsModified(loginCode,brokerBra,rsaIssuer);
						officeScheme = product.getOfficeProductScheme(loginCode,brokerBra);
					}
					else {
						productdetails=product.getProductDetailsModified(rsaIssuer,brokerBra,rsaIssuer);
						officeScheme = product.getOfficeProductScheme(loginCode,brokerBra);
					}
					if(productdetails.length>0) {
						session.setAttribute("ProductDetails",productdetails);
					}
					
				%>
				<%
					String freight="";
					if(productdetails.length>0) {
					String userType = freightProduct.getUserType(loginCode);
					HashMap cusOpt = new HashMap();
					String QuoteOpt="";
					//if(userType.equalsIgnoreCase("Customer"))
					//{
						String [][] cusRights = new String[0][0];
						cusRights = customer.getCustomerRights(loginCode);
						for(int c=0;c<cusRights.length;c++) {
							String cusPid = cusRights[c][0]!=null?cusRights[c][0]:"";
							String cusQuote = cusRights[c][1]!=null?cusRights[c][1]:"";
							String cusSchedule = cusRights[c][2]!=null?cusRights[c][2]:"";
							//String cusDebit = cusRights[c][3]!=null?cusRights[c][3]:"";
							String cusDebit = cusRights[c][7]!=null?cusRights[c][7]:"";
							String cusCusDebit = cusRights[c][4]!=null?cusRights[c][4]:"";
							String policyOpt = cusRights[c][5]!=null?cusRights[c][5]:"";
							String certificateOpt = cusRights[c][6]!=null?cusRights[c][6]:"";
							cusOpt.put("cusQuote"+cusPid,cusQuote);
							cusOpt.put("cusSchedule"+cusPid,cusSchedule);
							cusOpt.put("cusDebit"+cusPid,cusDebit);
							cusOpt.put("cusCusDebit"+cusPid,cusCusDebit);
							cusOpt.put("policyOpt"+cusPid,policyOpt);
							cusOpt.put("certificateOpt"+cusPid,certificateOpt);
						}
						session.setAttribute("cusOptions",cusOpt);
					//}
					if(customer.getIsBrokerHasDC(loginCode)) {
						session.setAttribute("CDMenu","Yes");
					}
					String filePath="";
					String alternative="row1";
					int t=0;
					for(int i=0;i<productdetails.length;i++) {
						QuoteOpt = (String)cusOpt.get("cusQuote"+productdetails[i][0]);
						QuoteOpt = QuoteOpt!=null?QuoteOpt:"";
						
						//For Home  Common - Rajesh
						String homePid = productdetails[i][0];
						String travelPid="0";
						
						if(!productdetails[i][0].equals("")) {
							if(productdetails[i][0].equals("30")) { %>
								<div class="col-xs-12 col-sm-12 col-md-6">
									<div class="panel panel-primary">
										<div class="panel-body parent homeInsurance">
											<h3 style="color: #FFFFFF;"><%=productdetails[i][1]%></h3>
										    <div class="hover-content"></div>
											<div class="row btnContent">
											<% for(int s=0;s < officeScheme.length;s++) { %>
												<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
									        		<a class="btn btn-lg btn-primary" style="text-decoration: none;" href="#" onClick="getPro('<%=productdetails[i][0]%>','NEW','<%=dtdiff%>','<%=homePid%>','<%=QuoteOpt%>','<%=travelPid%>','<%=officeScheme[s][0]%>','<%=officeScheme[s][1]%>')">
									        			<%--=officeScheme[s][1]--%>Get a Quote									        			
									        		</a>
									        	</div>
											<% } %>
											<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
								        		<a href="#" class="btn btn-lg btn-default"> Learn More </a>
								        	</div>
									        </div>
										</div>
									</div>
								</div>
							<%} else if(productdetails[i][0].equals("3") || productdetails[i][0].equals("11")) {
							%>
							<div class="col-xs-12 col-sm-12 col-md-6">
								<div class="panel panel-primary">
									<div class="panel-body parent marineInsurance">
										<h3 style="color: #FFFFFF;"><%=productdetails[i][1]%></h3>
									    <div class="hover-content"></div>
										<div class="row btnContent">
								        	<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
								        		<a class="btn btn-lg btn-primary" style="text-decoration: none;" href="#" onClick="getPro('<%=productdetails[i][0]%>','NEW','<%=dtdiff%>','<%=homePid%>','<%=QuoteOpt%>','<%=travelPid%>','','')">
													Get a Quote
												</a>
								        	</div>
								        	<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
								        		<a href="#" class="btn btn-lg btn-default"> Learn More </a>
								        	</div>
								        </div>
									</div>
								</div>
							</div>
						<% } else if(productdetails[i][0].equals("65")){ %>
							<div class="col-xs-12 col-sm-12 col-md-6">
								<div class="panel panel-primary">
									<div class="panel-body parent carInsurance">
										<h3><%=productdetails[i][1]%></h3>
									    <div class="hover-content"></div>
										<div class="row btnContent">
								        	<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
								        		<a class="btn btn-lg btn-primary" style="text-decoration: none;" href="#" onClick="getPro('<%=productdetails[i][0]%>','NEW','<%=dtdiff%>','<%=homePid%>','<%=QuoteOpt%>','<%=travelPid%>','','')">
													Get a Quote
												</a>
								        	</div>
								        	<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
								        		<a href="#" class="btn btn-lg btn-default"> Learn More </a>
								        	</div>
								        </div>
									</div>
								</div>
							</div>
						<% } else if(productdetails[i][0].equals("33")){ %>
							<div class="col-xs-12 col-sm-12 col-md-6">
								<div class="panel panel-primary">
									<div class="panel-body parent travelInsurance">
										<h3><%=productdetails[i][1]%></h3>
									    <div class="hover-content"></div>
										<div class="row btnContent">
								        	<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
								        		<a class="btn btn-lg btn-primary" style="text-decoration: none;" href="#" onClick="getPro('<%=productdetails[i][0]%>','NEW','<%=dtdiff%>','<%=homePid%>','<%=QuoteOpt%>','<%=travelPid%>','','')">
													Get a Quote
												</a>
								        	</div>
								        	<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
								        		<a href="#" class="btn btn-lg btn-default"> Learn More </a>
								        	</div>
								        </div>
									</div>
								</div>
							</div>
						<% } else { %>
							<a class="btn btn-sm btn-warning" style="text-decoration: none;" href="#" onClick="getPro('<%=productdetails[i][0]%>','NEW','<%=dtdiff%>','<%=homePid%>','<%=QuoteOpt%>','<%=travelPid%>','','')">
								<b><%=productdetails[i][1]%></b>
							</a> <br/><br/>
							
						<% }
						} if(alternative.equals("row1")) {
							alternative ="row2";   
						} else {
							alternative ="row1";
						}
					}
				%>
				<%
				} else {
				%>
				Sorry! No Products Available
				<%}%>
			</div>
		</div>
	</div>
</div>
<s:hidden name="selectProd" />
<s:hidden name="QuoteOpt" />
<s:hidden name="schemeId" />
<s:hidden name="contenTypeId" />
<s:hidden name="DisplayValue" />
</s:form>
</body>
<script type="text/javascript">
function getPro(pid,mod,dtdiff,homepid,qopt,tpid,officeScheme,OFSName) {
	if(pid=='3') {
		document.ChooseProduct.selectProd.value=pid;
		
		if(qopt=='NONE'||qopt=='None')
			document.ChooseProduct.action ="../final.jsp";
		else
			document.ChooseProduct.action ="${pageContext.request.contextPath}/initReport.action?menuType=CHART&reqFrom=PS&productId="+pid;
		document.ChooseProduct.submit();
	} else if(pid=='11') {
		document.ChooseProduct.selectProd.value=pid;
		document.ChooseProduct.QuoteOpt.value=qopt;
		document.ChooseProduct.action ="<%=request.getContextPath()%>/ViewOpenCovers.jsp";
		document.ChooseProduct.submit();
	} else if(pid=='31'||pid=='32'||pid=='33'||pid=='34'||pid=='41' || pid=='65' || pid=='30') {
		document.ChooseProduct.selectProd.value=pid;
		if(pid=='30') {
			document.ChooseProduct.schemeId.value=officeScheme;
			document.ChooseProduct.contenTypeId.value=tpid;
		}
		if(qopt=='NONE'||qopt=='None') {
			document.ChooseProduct.action ="../final.jsp";
		} else {
			document.ChooseProduct.action ="${pageContext.request.contextPath}/initReport.action?menuType=CHART&productId="+pid;
		}
		document.ChooseProduct.submit();
		
	}
	return false;
}
function singlePro(pid) {
	document.ChooseProduct.selectProd.value=pid;
	document.ChooseProduct.action ="../CustomerInfo/CustomerInfo.jsp";
	document.ChooseProduct.submit();
}
</script>
</html>