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
				<s:if test='"65".equalsIgnoreCase(productId)'>
				<div class="panel-heading">
					Motor (You can insure both Private and Commercial use vehicles)
				</div>
				<div class="panel-body">
					<p>
						<b>WHAT ARE PRIVATE VEHICLES?</b> <br/>
						These are used for pleasure only or in connection to the insured’s business but excluding commercial travelling						
					</p>
					<hr/>
					<p>
						<b>WHAT ARE COMMERCIAL VEHICLES?</b> <br/>
						<ul>
							<li>these are used for the following
								<ol>
									<li>carriage of goods for hire and reward</li>
									<li>used for hire and reward</li>
								</ol>
							</li>							
						</ul>
						used for carriage of Fare paying passengers					
					</p>
					<hr/>
					<p>
						<b>MOTOR SPECIAL TYPE</b> <br/>
						These are vehicles of special construction e.g. Ambulances, fire trucks, tippers etc <br/>
						<ul>
							<li>You can insure vehicles up to a value of K 2, 000, 000</li>
						</ul>
					</p>
					<hr/>
					<p>
						<b>WHO SHOULD BUY MOTOR INSURANCE?</b> <br/>
						<ul>
							<li>All motor vehicle owners.</li>
							<li>This is because motor insurance is compulsory by law.</li>
						</ul>
						<p>As of January 2016, all vehicle owners are required to display ( stick)  Insurance discs on the windscreens</p>
						<ul>
							<li><b>Fleets</b>. A group of vehicles under one ownership and covered under one policy. You can insure more than one vehicle on your policy and a special fleet rate applies when you insure a minimum of five vehicles</li>
							<li><b>Discounts</b>. You enjoy a 15% discount on your premium if you buy insurance via Mgen portal</li>
							<li><b>Convenience</b>. You purchase policies from your convenient locations anywhere, anytime</li>
							<li><b>Door step delivery</b>. Certificate and policy documents delivery to your premises within two (2) working days from the date payment is made </li>
							<li><b>Door step surveys</b>. Insured vehicles will be surveyed from your premises</li>
							<li><b>Customer care</b>. Available 24 hours 7 days a week</li>
							<li><b>Payment modes</b>. Debit/Credit card online payment, direct cash or cheque deposit. More payment options will be made available soon</li>
							<li><b>Flexibility</b>. We offer 3 unique cover blends to suit your needs and pocket i.e. Comprehensive, Third party fire and theft and Full Third Party cover</li>
							<li>
								Comprehensive cover includes: The widest possible protection <br/>
								<ol>
									<li>Accidental/Fire damage to your vehicle</li>
									<li>Loss of vehicle or spare parts/accessories</li>
									<li>Protection and removal after accident</li>
									<li>Third party liabilities (damage to property, death or injury)</li>
									<li>Riot and strike cover (Non-political)</li>
									<li>Medical expenses cover up to a stated limit (private cars only)</li>
									<li>Personal accident cover for insured and spouse up to a stated limit (private cars only)</li>
								</ol>
							</li>
							<li>
								Full Third Party Fire and Theft cover includes: <br/>
								<ol>
									<li>Loss or damage caused by fired, lightning, explosion, theft or attempted theft</li>
									<li>Third party liabilities (property, death and/or injury)</li>
								</ol>
							</li>
							<li>
								Third Party Only cover includes: <br/>
								<ol>
									<li>Third party liabilities (property, death and/or injury) only</li>
									<li>Contact Madison General Insurance on 378700-05  for any queries.</li>
								</ol>
							</li>
						</ul>
					</p>
					<hr/>					
				</div>
				</s:if>
				<s:if test='"30".equalsIgnoreCase(productId)'>
				<div class="panel-heading">
					Home Insurance (You can insure property up to a value of K 5,000,000)
				</div>
				<div class="panel-body">
					<p>
						<ul>
							<li><b>Discounts.</b> You enjoy a 15% discount on your premium if you buy insurance via our portal</li>
							<li><b>Convenience.</b> You purchase policies from your convenient locations anywhere, anytime</li>
							<li><b>Door step delivery.</b> Policy documents delivery to your premises within two (2) working days from the date payment is made</li>
							<li><b>Door step surveys.</b> Insured vehicles will be surveyed from your premises</li>
							<li><b>Customer care</b> available 24 hours 7 days a week</li>
							<li><b>Payment modes.</b> Debit/Credit card online payment, direct cash or cheque deposit. More payment options will be made available soon</li>
							<li><b>Flexibility.</b> We offer 3 covers that you can combine to suit your preference all on one policy or separately i.e. Buildings, Domestic Contents and All Risks.</li>
						</ul>
											
					</p>
					<hr/>
					<p>
						<b>WHAT IS HOME  INSURANCE?</b> <br/>
						<p>This is an Insurance policy that provides cover for your Domestic building and household contents</p>
						
						<ul>
							<li><b>Cover</b> includes:
								<ol>
									<li>Fire and Allied Perils</li>
									<li>Housebreak-in or any attempt threat</li>
									<li>Theft or any attempt threat of landlord’s fixtures and fittings</li>
								</ol>
							</li>							
						</ul>			
					</p>
					<hr/>
					<p>
						<b>WHAT KIND OF FIRE IS NOT COVERED</b> <br/>
						<ul>
							<li>Arson- This is Fire that is has been set deliberately by the owner of the property.</li>
							<li>Fire that starts in a vacant home.</li>
						</ul>
						A vacant home is a home that is left unattended and empty for 60 consecutive days.
					</p>
					<hr/>
					<p>
						<b>CAN I CHOOSE TO INSURE THE BUILDING ONLY?</b> <br/>
						<ul>
							<li>Yes, you can choose to cover the building only. This is for people who own houses.</li>
						</ul>
						If you are renting, you can only insure your household contents because the building would be  insured by the landlord	- Loss of rent
					</p>
					<hr/>
					<p>
						<b>WHAT KIND OF A HOUSE CAN I INSURE?</b> <br/>
						You can insure any building ranging from Flats, Semi-detached, Single or Double Storey as long as the building is of Standard Construction
					</p>
					<hr/>
					<p>
						<b>CAN I INSURE MY HOUSE IF I USE IT FOR BUSINESS?</b> <br/>
						Yes you can although on a different policy. Kindly refer such cases to the Underwriter.
					</p>
					<hr/>
					<p>
						<b>WILL MY BUILDING INSURANCE COVER FOR MY GARAGE AND OTHER OUTDOOR BUILDINGS?</b> <br/>
						<ul>
							<li>Yes, all outdoor buildings that are within the boundary wall of your premise are covered in the definition of building.</li>
						</ul>
						The policy also provides cover to water tanks and pipes as well as DSTV dishes and solar panels.
					</p>
					<hr/>
					<p>
						<b>Domestic Contents (Covers Household Contents)</b> <br/>
						<ul>
							<li>Fire and Allied Perils</li>
							<li>Theft accompanied by violent and forcible entry into or exit from a building</li>
							<li>Accidental damage and loss/damage to contents and effects whilst temporarily away from premises can be covered at an additional premium.</li>
						</ul>
					</p>
					<hr/>					
				</div>
				</s:if>
				<div align="center">
					<a type="button" class="btn btn-sm btn-danger" href="${pageContext.request.contextPath}" style="width: 100px; height: 30px;">Back</a>
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