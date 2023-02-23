<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
	<head>
        <meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<meta name="description" content="">
		<meta name="keywords" content="Maan Sarovar, Sea Trans, Marine Insurance">	
		<meta name="author" content="">
		<title>::: Madison General - Menu Selection :::</title>
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
	<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/bootstrap/css/bootstrap-multiselect.css" />
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
		<style type="text/css">
		.btn-group, .btn-group-vertical {
		    display: block; 
		}
		.btn-group, .multiselect {
		    width: 100%;
		}
		.btn-default {
		    color: #333;
		    background-color: #fff;
		    border-color: #ccc;
		}
		.text{
		padding-top:10px;
		padding-bottom:3px;
		font-weight:bold;
		}
		.bluecolortable th:first-child{
  border-radius: 20px 0px 0px 0px  !important;

}
.bluecolortable th:last-child{
  border-radius: 0px 20px 0px 0px  !important;

}
#dataTableNew {
  border-collapse: collapse;
  border-radius: 20px !important;
  font-family: 'NunitoSans-Black', sans-serif;
  box-shadow: 0 1px 1px rgb(0 0 0 / 25%), 0 2px 2px rgb(0 0 0 / 20%), 0 4px 4px rgb(0 0 0 / 15%), 0 8px 8px rgb(0 0 0 / 10%), 0 16px 16px rgb(0 0 0 / 5%);
  font-size: 15px;
}
		</style>
	</head>
<body>
<s:form name="form1" theme="simple">
<div class="row">
	<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
		<div class="vehDtl">
			<div class="Card_Parent card">
				<div class="panel-heading">
					<h3><s:text name="label.menu.selection"/></h3><hr>
				</div><br>
				<div class="panel-body">
				 	<table class="footable" id="dataTableNew">
                        <thead class="bluecolortable">
						<tr>
							<th width="25%">  </th>
							<th width="75%"> Menu Name </th>
						</tr>
						</thead>
						<tbody class="rowevencolor">
						<s:iterator value="productsWiseMenu" status="stat" var="record">
						<tr>
							<td align="center">
								${record.key}							 
							 </td>
							<td>
								<s:select name="menuId[]" id="products%{#stat.index}" list="%{#record.value}"  cssClass="selectmulti" listKey="MENU_ID" listValue="MENU_NAME" headerKey="" label="" multiple="true" />
							</td>
						</tr>
						</s:iterator>
						</tbody>					
					</table>
				</div>
			</div>
		</div>
	</div>
</div>
<br class="clear" />
<div>
	<div align="center">
		<input type="button" onclick="fnsubmit()"  class="btn btn-sm btn-success" value="Submit"/> &nbsp;&nbsp;&nbsp;
		<input name="back" type="button" class="btn btn-sm btn-danger" onclick="javascript:window.close()" value="Back" style ="cursor:hand"/>
	</div>
</div><s:property value="%{mid.length==0}"/> 
</s:form>
<script src="<%=request.getContextPath()%>/bootstrap/js/bootstrap-multiselect.js" type="text/javascript"></script>
<script type="text/javascript">

/*
function SelectAll(){
	try{
		var elements=document.forms[0].elements;
		if(document.getElementById('checkall').checked){
		 	for(i=0;i<elements.length;i++){
		    	obj=elements[i];
			   	if(obj.type=='checkbox'){
		    		obj.checked=true;
		    	}
		 	}
	 	}else{
	 		for(i=0;i<elements.length;i++){
	    		obj=elements[i];
	   			if(obj.type=='checkbox'){
	    			obj.checked=false;
	    		}
	 		}
	 	}
	}catch(e){}
}
*/
function fnsubmit(){
	var checkedItems='';
	try
	{
		var elements=document.forms[0].elements;
		for(i=1; i<elements.length; i++)
		{
			obj=elements[i];
			if(obj.checked)
				checkedItems+=obj.value+',';
		}
	}catch(e){}
	if(checkedItems.length>1)
		checkedItems=checkedItems.substring(0, checkedItems.length-1);
	alert(checkedItems);
	window.opener.adminlist.mid.value=checkedItems;
	window.close();
}


	$(document).ready(function() { 
		 
		    $('.selectmulti').multiselect({ 
		      includeSelectAllOption: true,
		        enableFiltering:true ,
		        allSelectedText: 'All Selected',
		        selectAllValue: -1,
		        maxHeight: 200,
		        onChange: function(element, checked) {
		        	var menuId='<s:property value="mid"/>';
			        var brands =menuId.split(","); 
			        var selected = [];
			        $(brands).each(function(index, brand){
			            selected.push([$(this).val()]);
			        });
			        console.log(selected);
			    },
			    onSelectAll: function() {
		           
		        },
		        buttonText: function (options) {
			        if (options.length == 0) {
			            return 'None selected';
			        } else {
			            var selected = 0;
			            options.each(function () {
			                selected += 1;
			            });
			            return selected +  ' Selected ';
			        }
			    }
		  	});   
		   <s:if test='mid!=null && !"".equals(mid)'>
		   	 var data='<s:property value="mid"/>';
		   	 var dataArray=data.split(",");
		   	$(".selectmulti").val(dataArray);
		   	$(".selectmulti").multiselect("refresh");
		   </s:if>
		   
		   
		   
		    	 
	});


</script>
</body>
</html>