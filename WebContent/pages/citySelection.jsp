<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
	<head>
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<meta name="description" content="">
		<meta name="keywords" content="Maan Sarovar, Sea Trans, Marine Insurance">	
		<meta name="author" content="">
		<title> ::: Madison General - City Selection ::: </title>
		<link rel="shortcut icon" href="<%=request.getContextPath()%>/images/favicon.ico" />	
		<script type="text/javascript" src="js/common.js"></script>
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/bootstrap/css/bootstrap.min.css" />
		<link href="<%=request.getContextPath()%>/bootstrap/css/style.css" rel="stylesheet" type="text/css" />
		<style type="text/css">
		label {
			margin-left: 10px;
		}
		</style>	
		<script type="text/javascript">
		
		function stopRKey(evt) { 
		  var evt = (evt) ? evt : ((event) ? event : null); 
		  var node = (evt.target) ? evt.target : ((evt.srcElement) ? evt.srcElement : null); 
		  if ((evt.keyCode == 13) && (node.type=="text"))  {return false;} 
		} 
		document.onkeypress = stopRKey; 
		
   		$(document).ready(function() {
		    $('table.display').dataTable( {
			        "lengthMenu": [[10, 25, 50, -1], [10, 25, 50, "All"]],
			        responsive: true
			} );
		} );
   		</script>
	</head>
<body>
<s:form name="citySelection" theme="simple" >
<div class="table0">
	<div class="tablerow">
		<div class="panel panel-primary">
			<div class="panel-heading">
				<s:if test='countrySelect=="O"'>
					<s:label key="quotation.cityOfOrigin" />
				</s:if>
				<s:else>
					<s:label key="quotation.cityOfDest" />
				</s:else>
			</div>
			<div class="panel-body">
				<div class="row">
					<s:if test='countrySelect=="O"'>
				      	<s:iterator value="orgCityList" var="city">
				      		<div class="textfield33">
				      			<s:radio list="#{city}" name="cityName"  listKey="CODE" listValue="CODEDESC" title="%{#city.CODEDESC}" onclick="toggleField('')" ></s:radio>
							</div>
		    	     	</s:iterator>
				     </s:if>
				     <s:else>
				     	<s:iterator value="destCityList" var="city">
				     		<div class="textfield33">
				     			<s:radio list="#{city}" name="cityName"  listKey="CODE" listValue="CODEDESC" title="%{#city.CODEDESC}" onclick="toggleField('')"></s:radio>
							</div>
		    	     	</s:iterator>
				     </s:else><br/>
				    <div class="textfield33">
				    	<s:radio list="#{'9999':'Others'}" name="cityName" onclick="toggleField('Others')" title="Others"/><s:textfield id="otherCity" maxlength="120" cssClass="inputBox"/>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="tablerow" align="center">
		<s:submit type="button" name="close"  key="commodity.close" cssClass="btn btn-sm btn-danger" onclick="window.close();return false;"/>&nbsp;&nbsp;&nbsp;
		<s:submit type="button" name="submit" key="commodity.submit" cssClass="btn btn-sm btn-success" onclick="return fnSubmit()"/>
		<s:hidden name="countrySelect"/>
	</div>
</div>
</s:form>
<script type="text/javascript">
setValues();
function setValues() {
	var countrySelect=document.citySelection.countrySelect.value; 
	if(countrySelect=="O") {
		var cityId=window.opener.quotation.originCity.value;
	} else if(countrySelect=="D") {
		var cityId=window.opener.quotation.destCity.value;
	}  	 
 	var elements=document.citySelection.elements;
 	for ( var int = 0; int < elements.length; int++) {
		var obj=elements[int];
		if(obj.name=='cityName' && obj.value==cityId) {
			obj.checked=true;
		}
		else if(cityId=="9999") {			
			obj.checked[cityId]=true;
			if(countrySelect=="O") {
			document.citySelection.otherCity.value=window.opener.quotation.originCityName.value
			} else {
				document.citySelection.otherCity.value=window.opener.quotation.destCityName.value
			}
		}	
	}
}

function fnSubmit() {  
	try {	
		var countrySelect=document.forms[0].countrySelect.value 
		var array=document.citySelection.cityName;
		var cityObj, cityNameObj, obj;
		if(countrySelect=="O") {
			cityObj=window.opener.quotation.originCity;	
			cityNameObj=window.opener.quotation.originCityName;	
		} else if(countrySelect=="D") {
			cityObj=window.opener.quotation.destCity;	
			cityNameObj=window.opener.quotation.destCityName;	
		}
		if (array.length > 0) {
			for ( var int = 0; int < array.length; int++) {	  	
				obj=array[int];
				if(obj.checked) {
					cityObj.value=obj.value;
					var regex = /^[a-zA-Z ]{2,30}$/;
					if(obj.title=='Others') {
						if(document.getElementById("otherCity").value==''){
							alert('Please enter city');
							return false;
						}
						/*else if(!regex.test(document.getElementById("otherCity").value)){
							alert('Please enter valid city');
							return false;
						}*/	
						cityNameObj.value=document.getElementById("otherCity").value;	
					} else {
						cityNameObj.value=obj.title;
					}
				}
			}
		} else {
			if(document.getElementById("otherCity").value=='') {
				alert('Please enter city');
				return false;
			} else {	
				cityNameObj.value=document.getElementById("otherCity").value;
				cityObj.value="9999";
			}
		}	  	
	} catch(e){alert(e);} 	  
	window.close();
	return false;
}
function toggleField(value) {
	if(value=='Others') {
		document.getElementById('otherCity').readOnly=false;
	}else{
		document.getElementById('otherCity').readOnly=true;
	}
} 
</script>
</body>
</html>