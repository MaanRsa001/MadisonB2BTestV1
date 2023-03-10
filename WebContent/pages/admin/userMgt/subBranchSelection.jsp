<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
	<head>
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title> ::: Madison General - Branch Selection ::: </title>
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
   		</script>
	</head>
<body>
<s:form name="form1" theme="simple">
<div class="row">
	<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
		<div class="vehDtl">
			<div class="Card_Parent card">
				<div class="panel-heading">
					<h3><s:text name="Sub Branch Selection"/></h3><hr>
				</div><br>
				<div class="panel-body">
					<table class="table table-bordered table-hover" cellspacing="0" width="100%" id="tadaTableNew">
						<thead class="bluecolortable">
						<tr>
							<th width="25%"> <input type='checkbox'  onclick='SelectAll()' id='checkall' style="width: 100%;"/> </th>
							<th width="75%"> Branch Name </th>
						</tr>
						</thead>
						<tbody>
						<s:iterator value="subBranchList" status="stat" var="record">
						<tr>
							<td align="center"> <input type="checkbox"  value="${record.BRANCH_ID}" id="checkbox${record.BRANCH_ID}" /> </td>
							<td> <s:property value="BRANCH_NAME" /> </td>
						</tr>
						</s:iterator>
						</tbody>					
					</table>
				</div>
			</div>
		</div>
	</div>
</div>
<br class="clar" />
<div>
	<div align="center">
		<input type="button" onclick="fnsubmit()"  class="btn btn-sm btn-success" value="Submit"/> &nbsp;&nbsp;&nbsp;
		<input name="back" type="button" class="btn btn-sm btn-danger" onclick="javascript:window.close()" value="Back" style ="cursor:hand"/>
	</div>
</div>
</s:form>
<script type="text/javascript">
if(window.opener.info.subBranchId.value.length>=1){
	var val=window.opener.info.subBranchId.value;
	var elements=val.split(',');
	for(i=0; i<elements.length; i++){
		document.getElementById('checkbox'+elements[i]).checked=true;
	}
}

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

function fnsubmit(){
	var checkedItems='';
	try
	{
		var elements=document.forms[0].elements;
		for(i=1; i<elements.length; i++)
		{
			obj=elements[i];
			if(obj.type=='checkbox' && obj.checked)
				checkedItems+=obj.value+',';
		}
	}catch(e){}
	if(checkedItems.length>1)
		checkedItems=checkedItems.substring(0, checkedItems.length-1);
	window.opener.info.subBranchId.value=checkedItems;
	window.close();
}
</script>
</body>
</html>