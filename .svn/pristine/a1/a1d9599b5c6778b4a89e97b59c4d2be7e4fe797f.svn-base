<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta name="description" content="">
	<meta name="keywords" content="Maan Sarovar">	
	<meta name="author" content="">
	<title> ::: Madison General - Login ::: </title>
	
	<link rel="shortcut icon" href="<%=request.getContextPath()%>/images/favicon.ico" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/assets/CssLibrary/bootstrap.min.css" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/StyleV1.css" />
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/all.css" />
   
	<script type="text/javascript" src="<%=request.getContextPath()%>/assets/CssLibrary/jquery.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/assets/CssLibrary/bootstrap.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/assets/CssLibrary/popper.min.js"></script>
    
	<script type="text/javascript">
		window.history.forward();
		function noBack(){ 
		    window.history.forward(); 
		}
		if(document.attachEvent)
		    document.attachEvent("onkeydown", my_onkeydown_handler);
		function my_onkeydown_handler() {
		    switch (event.keyCode) {
		        case 116 : // 'F5'
		            event.returnValue = false;
		            event.keyCode = 0;
		            window.status = "Refresh Functionality Disabled";
		            break;
		    }
		}
	</script>
	<style type="text/css">
		
		.card {

            padding: 12px 75px 14px 20px;
            cursor: pointer;
            border: 0px !important
        }

        .Card_Parent {
            border-radius: 4px;
            background: #fff;
            /* box-shadow: 0 5px 10px rgba(154, 160, 185, 0.05),
                0 15px 40px rgba(166, 173, 201, 0.2);
            ; */
            box-shadow: 0 6px 10px rgba(0, 0, 0, .08), 0 0 6px rgba(0, 0, 0, .05);
            transition: .3s transform cubic-bezier(.155, 1.105, .295, 1.12), .3s box-shadow, .3s -webkit-transform cubic-bezier(.155, 1.105, .295, 1.12);
        }

       /* .Card_Parent:hover {
            transform: scale(1.02);
            box-shadow: 0 10px 20px rgba(0, 0, 0, .12), 0 4px 8px rgba(0, 0, 0, .06);
        }*/

        .card h3 {
            font-weight: 700;
            color: #261e6a;
            font-size: 19px;
        }

        .card-1 {
            background-image: url("assets/Images/car-insurance5.png");
            background-repeat: no-repeat;
            background-position: right;
            background-size: 110px;
        }

        .card2-1 {
            background-image: url("assets/Images/claim_money.png");
            background-repeat: no-repeat;
            background-position: right;
            background-size: 90px;
        }

        .card-2 {
            background-image: url("assets/Images/home-insurance.png");
            background-repeat: no-repeat;
            background-position: right;
            background-size: 110px;
        }

        .card2-2 {
            background-image: url("assets/Images/user-interface.png");
            background-repeat: no-repeat;
            background-position: right;
            background-size: 90px;
        }


        .card-3 {
            background-image: url("assets/Images/plane.png");
            background-repeat: no-repeat;
            background-position: right;
            background-size: 110px;
        }

        .card2-3 {
            background-image: url("images/RTSA.png");
            background-repeat: no-repeat;
            background-position: right;
            background-size: 80px;
        }

        @media(max-width: 990px) {
            .card {
                margin: 20px;
            }
        }

        .LoginContainer .btn {
            font-size: 10.50px;
            padding: 3px;
            height: 35px;
            border-radius: 0px;
            box-shadow: rgba(60, 64, 67, 0.3) 0px 1px 2px 0px, rgba(60, 64, 67, 0.15) 0px 1px 3px 1px;
           
        }

        .ListContents li {
            font-size: 11px;
            padding: 5px;
            border: 0;
        }

        .ListContents li:first-child .Listheading {
            font-weight: 700;
            color: #261e6a;
        }

        .list-group-item-success,
        .badge {
            background-color: white;
            border-radius: 0px !important;
            font-weight: bold;
            font-size: 11px;
            border: 0px;
        }

        .badge {
            color: dodgerblue;
            cursor: pointer;
        }
        .fa-check-circle, .fa-shield-alt{
            padding: 3px;
        }
	</style>
</head>
<body>
<div id="page" class="content" style="margin-top:24px">
	<section>
        <div class="navbarContainer">
            <div class="row">
                <div class="col-md-2 col-12 offset-1">
                    <a href="${pageContext.request.contextPath}">
						<img src="${pageContext.request.contextPath}/images/mgen-logo.png" border="0" >
					</a>
                </div>
                <div class="col-md-4 col-12 offset-md-5 loginpagebutton">
                    <ul class="list-group">
                       <li class="list-group-item heading-brand">
				           <font color="#dca531"> 
					           <s:if test='"Live".equalsIgnoreCase(#session.userLoginMode)'>
					              <h3><b>MGen-Online</b></h3>
				               </s:if>
				               <s:else>
				              	 <h3><b>MGen-Test</b></h3>
				               </s:else>
			              </font>
			            </li>
                        <li class="list-group-item numbersEmail">
                            <span><i class="fas fa-mobile-alt"> </i> 4848 | 378700-5 | </span>
                            <span><i class="fas fa-envelope"></i> <a href="mailto:online@madison.co.zm?Subject=Insure" target="_top" style="color: #DBA832;">online@madison.co.zm</a></span>
                        </li>
                        <%-- <li class="list-group-item numbersEmail">
                            
								<a type="button" class="btn btn-sm btn-primary ml-5" href="${pageContext.request.contextPath}" style="margin-left: 7rem!important;width: 100px; height: 30px;">Home</a>
                        </li> --%>
                    </ul>
                </div>
            </div>
        </div>
    </section>
	<br class="clear" />	
	<div class="row">
		<s:form name="form1" method="post" id="form1" theme="simple">
			<section>
				<div class="row">
					<div class="col-xs-6 col-sm-6 col-md-7 col-lg-7">
		        	</div>
					<div class="col-xs-6 col-sm-4 col-md-2 col-lg-2" align="right">
						<span style="font-size: 16px; color:#337ab7; font-weight: bold;"> <i class="fa fa-download"></i> Mobile App</span><br/>
						<a href="https://play.google.com/store/apps/details?id=com.pss.madison" target="_blank">
							<img src="${pageContext.request.contextPath}/images/Android-Logo.jpg"  width="50" height="35" border="0" >
						</a>		<b>|</b>
						<a href="https://apps.apple.com/us/app/id1522891577" target="_blank"> 
							<img src="${pageContext.request.contextPath}/images/Apple-Logo.jpg"  width="45" height="45" border="0" >
						</a>
					</div>
					<div class="col-xs-6 col-sm-2 col-md-2 col-lg-2" align="center">
						<a href="https://eservices.gov.zm/#/service/4" target="_blank"> 
							<img src="${pageContext.request.contextPath}/images/RTSA.png"  width="40" height="40" border="0" >
							<br><span style="font-weight: bold;font-size: 15px;">Buy Road Tax</span>
						</a>
					</div>
				</div>
		        <div class="LoginContainer mt-3" id="LoginContainer">
		            <div class="row">
		                <div class="col-md-4 col-12 offset-md-1 jumbotronpadd">
		                    <div class="jumbotron">
		                        <div class="row">
		                            <div class="col-md-12 text-center">
		                                <h4>LOGIN</h4>
		                            </div>
		                        </div>
		                        <div style="color: red;">
									<s:actionerror/>
								</div>
		                        <div class="row">
		                            <div class="col-md-12">
		                                <div class="input-group mb-3">
		                                    <div class="input-group-prepend">
		                                        <span class="input-group-text border border-right-0"><i class="fas fa-user-tie"></i></span>
		                                    </div>
		                                    <s:textfield name="loginId" id="loginId" class="form-control border border-left-0"  cssStyle="border: 1px;" placeholder="Login Id" maxlength="50" />
		                                </div>
		                            </div>
		                        </div>
		                        <div class="row">
		                            <div class="col-md-12">
		                                <div class="input-group mb-3">
		                                    <div class="input-group-prepend">
		                                        <span class="input-group-text border border-right-0"><i class="fas fa-lock"></i></span>
		                                    </div>
		                                    <s:password name="pwd" id="pwd" class="form-control border border-left-0" placeholder="Password" />
		                                </div>
		                            </div>
		                        </div>
		                        <div class="row">
		                            <div class="col-md-12">
		                                <div class="input-group mb-3">
		                                    <div class="input-group-prepend">
		                                        <span class="input-group-text border border-right-0"><i class="fas fa-building"></i></span>
		                                    </div>
	                                        <s:select name="branch" id="branch" class="form-control border border-left-0" list="branchList"  listKey="BRANCH_CODE" listValue="BRANCH_NAME"  placeholder="Branch"/>
		                                </div>
		                            </div>
		                        </div>
		                        <div class="row">
		                            <div class="col-md-4 col-4 offset-4 offset-md-4 text-center">
                                        <s:submit type="button" id="userLogin" class="btn btn-primary btn-block" onclick="funSubmitUsr('user');" theme="simple">LOGIN <i class="fas fa-sign-in-alt"></i></s:submit>
		                            </div>
		                        </div>
		                        <div class="row oops">
		                            <div class="col-md-6 col-6">
		                              <a href="${pageContext.request.contextPath}/Loginoption.action?status=changePwd" onclick="changePass()" >Change Password?</a><s:hidden name="appId" value="16"/>
		                            </div>
		                            <div class="col-md-6 col-6 text-right">
		                               <a href="${pageContext.request.contextPath}/Loginoption.action?status=forgotPwd" onclick="forgotPass()" >Forgot Password?</a>
		                            </div>
		                        </div>
		                    </div>
		                </div>
		                <div class="col-md-6 col-12 jumbotronpadd">
		                    <div class="jumbotron imgcontent">
		                        <div class="row">
		                            <div class="col-md-12 text-center">
		                                <h4>WELCOME TO MGEN</h4>
		                            </div>
		                        </div>
		                        <div class="row mgencontents">
		                            <div class="col-md-12">
		                                <p>
		                                    Madison General Insurance has been the insurer of preferred choice since 1992. We
		                                    have a rich portfolio of satisfied clients and the list keeps growing. We boast a
		                                    presence in 6 out of 10 provinces in Zambia and plans are underway to increase our
		                                    customer touch-points through more branches and mobile offices.
		                                </p>
		                                <p>
		                                    Our claims payment period is comparable to none in the industry coupled with
		                                    unprecedented quality customer service. You do not just buy insurance with us, but
		                                    you earn the right to a peace of mind. What are you waiting for? Get on the winning
		                                    team today! It's worth it!
		                                </p>
		                            </div>
		                        </div>
		                    </div>
		                </div>
		            </div>
		        </div>
		    </section>
			<s:hidden name="swidth" id="swidth" value=""/>
			<s:hidden name="loginType" id="loginType"/>
			<s:hidden name="isArabic" id="isArabic"/>
			<s:hidden name="mode" value="b2c"/>
			<s:hidden name="showValue" />
			<s:hidden name="branchName"/>
		</s:form>
	</div>
	<br class="clear" />
</div>
<script src="<%=request.getContextPath()%>/bootstrap/js/jquery.placeholder.js"></script>
<script type="text/javascript">	
try{
	var width = (window.innerWidth > 0) ? window.innerWidth : screen.width;
	document.getElementById("swidth").value=width;
	}catch(err){
		
	}
	jQuery(document).ready(function($) {
		$('#loginId').placeholder();
		$('#pwd').placeholder();
	  });
	  
	  $(".numericOnly").on("keypress keyup blur",function (event) {  
			this.value = this.value.replace(/[^\d]/g,'');
	            if (event.which > 57) {
	                event.preventDefault();
	            }
	      });
  
function funSubmitUsr(loginType){
	var e = document.getElementById('branch'); 
   	var branchName = e.options[e.selectedIndex].text; 
   	document.form1.branchName.value = branchName;
	document.form1.loginType.value = loginType;
   	document.form1.action="${pageContext.request.contextPath}/Loginsubmit.action";
    document.form1.submit();
}		
function claimStatus(productId){
	document.form1.action ="${pageContext.request.contextPath}/initSearchClaimIntimation.action?";
	document.form1.submit();
}


</script>
<!--Start of Tawk.to Script-->
<script type="text/javascript">
var Tawk_API=Tawk_API||{}, Tawk_LoadStart=new Date();
(function(){
var s1=document.createElement("script"),s0=document.getElementsByTagName("script")[0];
s1.async=true;
s1.src='https://embed.tawk.to/623b365e5a88d50db1a6f7dc/1furkop3e';
s1.charset='UTF-8';
s1.setAttribute('crossorigin','*');
s0.parentNode.insertBefore(s1,s0);
})();
</script>
<!--End of Tawk.to Script-->
</body>
</html>