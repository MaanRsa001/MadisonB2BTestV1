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
	<meta name="keywords" content="Maan Sarovar, Sea Trans, Marine Insurance">	
	<meta name="author" content="">
	<title> ::: Madison General - Login ::: </title>
	<link rel="shortcut icon" href="<%=request.getContextPath()%>/images/favicon.ico" />
	<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/bootstrap/css/demo.css" />
	<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/bootstrap/css/font-awesome.min.css" />
	<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/bootstrap/css/bootstrap-multiselect.css" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/bootstrap/css/datepicker3.css">	 
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/bootstrap/css/slimmenu.css">
	<link href="<%=request.getContextPath()%>/bootstrap/css/footable-0.1.css" rel="stylesheet" type="text/css">
	<link href="<%=request.getContextPath()%>/bootstrap/css/table-res.css" rel="stylesheet" type="text/css">
	<link href="<%=request.getContextPath()%>/bootstrap/css/easy-responsive-tabs.css" rel="stylesheet" type="text/css">
	<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/bootstrap/css/bootstrap.min.css" />
	<link href="${pageContext.request.contextPath}/bootstrap/css/loginStyle.css" rel="stylesheet" type="text/css">
	<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/bootstrap/css/style.css" />
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/common.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/shortcut.js"></script>
	<script src="<%=request.getContextPath()%>/bootstrap/js/modernizr.custom.63321.js"></script>
	<script src="<%=request.getContextPath()%>/bootstrap/js/jquery-1.9.1.min.js"></script>
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
		shortcut.add("f5",function() {window.status = "Refresh Functionality Disabled";});
		shortcut.add("Ctrl+f5",function() {window.status = "Refresh Functionality Disabled";});
		shortcut.add("Ctrl+r",function() {window.status = "Refresh Functionality Disabled";});
		shortcut.add("Ctrl+Shift+r",function() {window.status = "Refresh Functionality Disabled";});
		shortcut.add("Ctrl+n",function() {window.status = "New Window Functionality Disabled";});
		
		function callSubmit(val){
			if(val=='login'){
		    	document.form1.action="${pageContext.request.contextPath}/Loginsubmit.action";
		    }
		    document.form1.submit();
		}
	</script>
	<STYLE type="text/css">
		b{
			color:#043966;
		}
	</STYLE>
</head>
<!--[if lt IE 7 ]>   <body class="ie6">          <![endif]-->
<!--[if IE 7 ]>      <body class="ie7">          <![endif]-->
<!--[if IE 8 ]>      <body class="ie8">          <![endif]-->
<!--[if IE 9 ]>      <body class="ie9">          <![endif]-->
<!--[if (gt IE 9) ]> <body class="modern">       <![endif]-->
<!--[!(IE)]><!-->    <body class="notIE modern"> <!--<![endif]-->
<div id="page" class="content">
	<div class="ielogo">
		<div class="pullLeft">
			<a href="${pageContext.request.contextPath}">
				<img src="${pageContext.request.contextPath}/images/mgen-logo.png" border="0" >
			</a> 	
		</div>
		<div class="pullRight" align="center">		
			<font color="#dca531" style="font: bold;font-size: 30px;">
				<s:if test='"Live".equalsIgnoreCase(#session.userLoginMode)'>
					MGen-Online 	
				</s:if>
				<s:if test='"Test".equalsIgnoreCase(#session.userLoginMode)'>
					MGen-Test  
				</s:if>
			</font> <br/>
			<i class="fa fa-phone"></i>: 4848 | 378700-5 | <i class="fa fa-envelope"></i>: <a href="mailto:online@madison.co.zm?Subject=Insure" target="_top" style="color: #DBA832;">online@madison.co.zm</a> <br/>
			<span style="font-size: 12px;">
				<a type="button" class="btn btn-sm btn-primary" href="${pageContext.request.contextPath}" style="width: 100px; height: 30px;">Home</a>
			</span>
		</div>
		<br class="clear" />
	</div>
	<br class="clear" />	
	<div class="row">
		<div class="col-xs-12">
			<div class="panel panel-primary">
				<div class="panel-heading">
					<s:text name="FREQUENTLY ASKED QUESTIONS (FAQs)" />
				</div>
				<div class="panel-body">
					<p>
						<b>WHAT IS THE POLICY START DATE?</b> <br/>
						The date at which you want your insurance to be effective, can only be from today or a further future date.
					</p>
					<hr/>
					<p>
						<b>WHAT IS THE POLICY END DATE?</b> <br/>
						This is the date that your insurance cover ends. The end period is at the end of the respective quarter of the year as stipulated by the Insurance Association of Zambia.						
					</p>
					<hr/>
					<p>
						<b>WHAT CURRENCY CAN BE USED FOR PAYMENT?</b> <br/>
						The system of money being used in the portal is American dollar ($) or Zambian Kwacha (ZMW)						
					</p>
					<hr/>
					<p>
						<b>WHAT IS EXCESS LIMIT?</b> <br/>
						Excess is the first amount you will bear in case of a claim. Generally, Insurance covers you for only 90%, the 10% has to be borne by you. This is because you are encouraged to act as though you are not insured. You are supposed to take reasonable care of your property and mitigate losses. If you choose a higher excess which is more than 10%, e.g. 20% or 30%, you will received reduced premiums.
					</p>
					<hr/>
					<p>
						<b>WHAT IS THE VEHICLE VALUE?</b> <br/>
						The market value of your vehicle upon which premium calculation will be based, also being the maximum amount that an insurance company will pay out in case of a claim.
					</p>
					<hr/>
					<p>
						<b>WHAT IS PREMIUM?</b> <br/>
						Premium is the amount charged for the property being insured.
					</p>
					<hr/>
					<p>
						<b>WHAT ARE CLAIM FREE YEARS (NO CLAIMS BONUS)?</b> <br/>
						These are insurance rewards or discount to the policy holder for not making a claim on their motor insurance in the preceding years. The discount increases as the number of claim free years increases.
					</p>
					<hr/>
					<p>
						<b>WHAT ARE VEHICLE DETAILS?</b> <br/>
						The vehicle details entail the body type, the usage (private or commercial) and the seating capacity as shown on the vehicle registration book.</b>						
					</p>
					<hr/>
					<p>					
						<b>WHAT IS THE BASIS OF CLAIM SETTLEMENT?</b> <br/>
						Typically General Insurance policies are policies of indemnity (security or protection against a loss or other financial burden) and therefore uses the following ways to settle claims: Repair, Replacement, Reinstatement and Cash in lieu.
					</p>
					<hr/>
					<p>
						<b>WHAT PAYMENT METHODS ARE AVAILABLE?</b> <br/>
						Cash, cheque, paypal, cash transfer, credit/debit card.
					</p>
					<hr/>					
					<p>
						<b>HOW MANY CARS OR HOUSES CAN I INSURE WITH MADISON?</b> <br/>
						As many as you have. We guarantee you the highest security for your assets and we value your business support.
					</p>
					<hr/>
					<p>
						<b>HOW DO I MAKE A CLAIM?</b> <br/>
						Please refer to our………………………. Section of the portal to process your claim.
					</p>
					<hr/>
					<p>
						<b>WHAT IS HOME INSURANCE?</b> <br/>
						Home owners insurance is a type of insurance that covers financial protection against disasters. A standard policy insures the home itself and the things you keep in it. This is a package policy, this means that it can cover both damage to your property and your liability or legal responsibility for any property damage you or family members cause to other people.
					</p>
					<hr/>
					<p>
						<b>WHAT ARE BUILDINGS?</b> <br/>
						Building includes house main house, guest wing, garage, car port and landlord’s fixtures and fittings, walls, gates, servant’s quarters and fences excluding hedges.
					</p>
					<hr/>
					<p>
						<b>WHAT ARE DOMESTIC CONTENTS?</b> <br/>
						Domestic contents consist of furniture, non-electrical household goods and personal effects kept within the premises.
					</p>
					<hr/>
					<p>
						<b>WHAT ARE ALL RISKS?</b> <br/>
						All risks are items that consist of electrical and electronic items, jewellery and portable items. Such as smart phones, laptops, TVs, and works of art.
					</p>
					<hr/>
					<p>
						<b>WHAT IS LOSS OF RENT?</b> <br/>
						This is the rental amount lost whilst the house is undergoing repairs. Loss of rent can cover you for lost rental income should the insurer deem your property to be uninhabitable, and your tenants are unable to live in the property as a result of building damage.
					</p>
					<hr/>
					<p>
						<b>WHAT IS PROPERTY OWNER’S LIABILITY?</b> <br/>
						This covers costs awarded to the public for damages or injuries caused by your property.
					</p>
					<hr/>
					<p>					
						<b>WHAT IS DEBRIS REMOVAL?</b> <br/>
						These are clean-up costs associated with damage to property caused by an insured peril.
					</p>
					<hr/>
					<p>
						<b>WHAT IS THIRD LIMIT OF LIABILITY?</b> <br/>
						This is the maximum amount the insurance company will pay you in case of loss on injury, death or property damage.
					</p>
					<hr/>
					<p>
						<b>WHAT IS COMBINED THIRD PARTY LIMIT?</b> <br/>
						Combined Third Limit basically combines all of your third party liability coverage into one lump sum versus third party liability where coverage is split amongst different incidents.
					</p>
					<hr/>
					<p>
						<b>WHAT IS A COURTESY CAR?</b> <br/>
						A courtesy car is a vehicle provided by a garage or repair shop for use while your car is being repaired. A courtesy car is not provided on your standard car insurance policy. You may need to purchase it as an optional extra.
					</p>
					<hr/>
					<p>
						<b>WHAT TYPES OF MOTOR INSURANCE COVER ARE AVAILABLE ON THE PORTAL?</b> <br/>
						<ul>
							<li>Comprehensive</li>
							<li>Full Third Party Fire and Theft</li>
							<li>Third Party Only</li>
						</ul>
					</p>
					<hr/>
					<p>
						<b>WHAT IS COVERED ON COMPREHENSIVE INSURANCE?</b> <br/>
						<ul>
							<li>Accidental/fire damage to your vehicle</li>
							<li>Loss of Vehicle or spare parts/accessories</li>
							<li>Protection and removal after accident</li>
							<li>Third Party Liabilities( damage to property, death or injury)</li>
							<li>Riot and strike cover (non-political)</li>
							<li>Medical expenses cover up to a stated limit (private cars only)</li>
							<li>Personal accident cover for insured and spouse up to a stated Limit (private cars only) WHAT IS COVERED ON FULL THIRD PARTY FIRE AND THEFT INSURANCE?</li>
							<li>Loss or damage caused by fire, lightning, explosion, theft or attempted theft.</li>
							<li>Third party liabilities (property, death and/or injury)</li>
						</ul>
					</p>
					<hr/>
					<p>
						<b>WHAT IS COVERED ON THIRD PARTY ONLY?</b> <br/>
						Third party liabilities (property, death and/or injury) only.
					</p>
					<hr/>
					<p>
						<b>WHAT IS THIRD PARTY LOSS OF USE?</b> <br/>
						The cost of hiring another vehicle for use by the third party while their car is being repaired in the garage or repair shop.
					</p>
					<hr/>
				</div>
			</div>
		</div>
	</div>
</div>
<div id="footer">
	<s:include value="../templates/footer.jsp" />
</div>
</body>
</html>