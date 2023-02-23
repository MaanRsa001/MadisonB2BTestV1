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
	<title><tiles:insertAttribute name="title" /></title>
	
 	<link rel="shortcut icon" href="<%=request.getContextPath()%>/images/favicon.ico" />
   
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/assets/CssLibrary/bootstrap.min.css" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/assets/CssLibrary/glyphicon.min.css" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/StyleV1.css" />
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/all.css" /> 
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/bootstrap/css/datepicker3.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/bootstrap/css/bootstrap-datetimepicker.css">	 
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/assets/CssLibrary/dataTables.bootstrap.min.css">  
   
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/bootstrap/css/datepicker3.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/bootstrap/css/bootstrap-datetimepicker.css">	 
	
	<script type="text/javascript" src="<%=request.getContextPath()%>/assets/CssLibrary/jquery.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/assets/CssLibrary/popper.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/assets/CssLibrary/bootstrap.min.js"></script>
    
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/common.js"></script>
	<script src="${pageContext.request.contextPath}/js/numeral.min.js"></script>
	
	<script src="<%=request.getContextPath()%>/bootstrap/js/bootstrap-datepicker.js" type="text/javascript"></script>
	<script src="<%=request.getContextPath()%>/bootstrap/js/bootstrap-datetimepicker.js" type="text/javascript"></script>
	
	<script type="text/javascript" src="<%=request.getContextPath()%>/assets/CssLibrary/dataTables.bootstrap.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/assets/CssLibrary/dataTables.responsive.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/assets/CssLibrary/jquery.dataTables.min.js"></script>

<style>
.content.container-fluid {
    padding-right: 25px;
    padding-left: 25px;
    }
</style>
</head>
<body>
<div id="page" class="content container-fluid">
	<div>
		<tiles:insertAttribute name="header" />
	</div>	
	<div>
		<tiles:insertAttribute name="menu" />
	</div>&nbsp; 
	<br class="clear"/>
	<div class="body">
		<tiles:insertAttribute name="body" />
	</div>	
</div>
<br class="clear"/>
<div class="footer">
	<tiles:insertAttribute name="footer" />
</div>
 <script src="<%=request.getContextPath()%>/bootstrap/js/app-js.js" type="text/javascript"></script>  
 <script type="text/javascript">
	 $(function() {
		//$(window).load(function() {
		   	$('#loading').css("display","none");
		//});	
	 });
 </script>
 
 
		<script src="https://www.gstatic.com/firebasejs/8.2.10/firebase-app.js"></script>
		<script src="https://www.gstatic.com/firebasejs/8.2.10/firebase-messaging.js"></script>

	    <script>
			    var firebaseConfig = {
			      apiKey: "AIzaSyD1mLTAfLc518VIIKLeWa6lMJSNaLWTp3I",
			      authDomain: "maan-sarovar-geocode.firebaseapp.com",
			      projectId: "maan-sarovar-geocode",
			      storageBucket: "maan-sarovar-geocode.appspot.com",
			      messagingSenderId: "643413612415",
			      appId: "1:643413612415:web:0a9f5bd49eaf68e5fb2c4a",
			      measurementId: "G-NVWT0847L3"
			    };
			 	
			  	firebase.initializeApp(firebaseConfig);
				const messaging = firebase.messaging();
				messaging.usePublicVapidKey("BIAbi2HoN_H71aB3gLgatSCJakz9kFoUVYEaw7AGUNNV9z1iDw02rpe_H6okSaUsb9zV6tT2-yVxDv7llSA073I");
			
			
				messaging.getToken().then((currentToken) => {
				  if (currentToken) {
				      saveToken(currentToken);
				  } else {
				    console.log('No Instance ID token available. Request permission to generate one.');
				   
				  }
				}).catch((err) => {
				  console.log('An error occurred while retrieving token. ', err);
				 
				});
			
			
				messaging.onTokenRefresh(() => {
				  messaging.getToken().then((refreshedToken) => {
				  }).catch((err) => {
				    console.log('Unable to retrieve refreshed token ', err);
				  });
				});
				
				function saveToken(token){
					try{
						$.ajax({
					      url: "${pageContext.request.contextPath}/saveTokenNotification.do",
					      method: "POST",
					      data: {
					    	token: token
					      },
					      success: (response) => {
					      },
					      error: (err) => {
					      },
					    });
					}catch(err){
						console.log(err);
					}
				}
		
		</script>
</body>
</html>