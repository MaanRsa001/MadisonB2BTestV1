<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>Electrical</title>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<meta name="description" content="">
		<meta name="keywords" content="Maan Sarovar, Sea Trans, Marine Insurance">	
		<meta name="author" content="">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/bootstrap/css/font-awesome.css" />
		<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/bootstrap/css/demo.css" />
		<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/bootstrap/css/bootstrap-multiselect.css" />
		<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/bootstrap/css/easy-responsive-tabs.css" />
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/bootstrap/css/datepicker3.css">
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/bootstrap/css/slimmenu.css">
		<link href="<%=request.getContextPath()%>/bootstrap/css/footable-0.1.css" rel="stylesheet" type="text/css">
		<link href="<%=request.getContextPath()%>/bootstrap/css/table-res.css" rel="stylesheet" type="text/css">
		<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/bootstrap/css/bootstrap.min.css" />
		<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/bootstrap/css/style.css" />
		<script type="text/javascript" src="<%=request.getContextPath()%>/js/common.js"></script>
		<script src="<%=request.getContextPath()%>/bootstrap/js/modernizr.custom.63321.js"></script>
		<script src="<%=request.getContextPath()%>/js/jquery-2.1.1.min.js"></script>
	</head>
	<body>
		<div id="page" class="content">
			<s:form id="form1" name="form1" method="post" theme="simple" enctype="multipart/form-data">
				<br class="clear"/>
				<div class="row">
					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
						<b id="errorDesc" style="color:red"></b>
						<s:actionerror cssStyle="color:red;"/>
						<s:actionmessage cssStyle="color:green;"/>
					</div>
				</div>
				<br class="clear"/>
				 
				<div class="row">
					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">				
						<div class="panel panel-primary">
		        			<div class="panel-heading">
		        				<s:text name="Electrical Details" />
		        			</div>
		        			<div class="panel-body">
								<table style="border-collapse: collapse;" class="footable" border="0" width="100%;" id="EmlTable">
							    			<thead>
								    			<tr>
								    				<th class="deleteCol">S.No</th>
								    				<th class="cCol"> Description </th>
								    				<th class="cCol"> Amount </th>
								    				<th class="cCol"></th>  
								    			</tr>
							    			</thead>
							    			<tbody>
							    			 <s:if test="%{nonelesno!=null && nonelesno.size()>0}">
						            			<s:iterator value="nonelesno" var="snolist" status="stat">			    			
									    			<tr>
									    			    <td class="deleteCol" align="center">
									    			      	<s:text name="%{#stat.count}"></s:text>
									    			     	<s:hidden name="nonelesno[%{#stat.count-1}]" id="nonelesno%{#stat.count-1}"/>
										    			</td>
									    			    <td class="cCol">
									    					<s:textfield name="noneleDescrip[%{#stat.count-1}]" id="noneleDescrip[%{#stat.count-1}]" cssClass="inputBox"  />
									    				</td>									    			    
									    				<td class="cCol">
									    				    <s:textfield name="nonelecAmont[%{#stat.count-1}]" cssClass="inputBox" maxLength="100" disabled="#disableuw"/>
									    				</td> 
									    				<td>
									    					
									    				</td>
									    			</tr>									    		 
									    		</s:iterator>
									    	  </s:if>
									    	  <s:else>									    	  		 
									    			 <tr>
									    			    <td class="deleteCol" align="center">
									    			      	1
									    			     	<s:hidden name="nonelesno[0]" id="nonelesno0"/>
										    			</td>
									    			    <td class="cCol">
									    					<s:textfield name="noneleDescrip[0]" id="noneleDescrip[0]" cssClass="inputBox"  />
									    				</td>									    			    
									    				<td class="cCol">
									    				    <s:textfield name="nonelecAmont[0]" id="nonelecAmont[0]" cssClass="inputBox" maxLength="100" disabled="#disableuw"/>
									    				</td>
									    				 <td class="cCol">
									    				    <input type="checkbox"/>
									    				</td>
									    			</tr> 
									    	  </s:else>   			
							    			</tbody>
							    		</table>	 
							</div>
						</div>
					</div>
				</div> 	 
				<br class="clear"/>
				<div class="row" align="center">
					<div class="boxcontent" align="right">
			    		<input type="button" name="addadd" value="Add" class="iButton rh" onclick="addEml()" />
			    	</div>
			    	<s:hidden name="applicationNo"/>
					<s:submit type="button" cssClass="btn btn-sm btn-danger" value="Close" onclick="wclose();"/>
					<s:submit type="button" cssClass="btn btn-sm btn-success" value="Submit" onclick="return save();"/>
				</div>
				<br class="clear"/>
				 
			</s:form>
		</div>
		<script type="text/javascript">
		function addEml(){
		    
			var table = document.getElementById('EmlTable');
			var rowCount = table.rows.length-1;
			var row = table.insertRow(rowCount);
			row.className="runtext";
			row.id = "new_"+rowCount;
			
			cell = row.insertCell(0);	
		   	cell.className = "deleteCol";	
			cell.innerHTML = (rowCount+1)+'<br><input type="hidden" name="nonelesno['+rowCount+']" value="" id="nonelesno'+rowCount+'"/>';
			
			cell = row.insertCell(1);			
			var element = document.createElement("input");
			element.className = "inputBox";
			element.name = "noneleDescrip["+rowCount+"]";
			element.id = "noneleDescrip";
			element.type = "text";			 
			cell.appendChild(element);
			
			cell = row.insertCell(2);			 
			var element = document.createElement("input");
			element.className = "inputBox";
			element.type = "text";
			element.name = "nonelecAmont["+rowCount+"]";
			element.maxlength = "50";
			element.id = "nonelecAmont[0]";
			cell.appendChild(element);
			
			cell = row.insertCell(3);
			cell.align = "center";		
			var element = document.createElement("input");
			element.type = "checkbox";
			element.id = "chk"+rowCount;
			element.onclick = function () {deleteRow(this.id,this)};	
		    cell.appendChild(element);
			}
		
		function deleteRow(id, el) {
			var decision = confirm("Row will be deleted. Do You Want to continue? ","");{
				if (decision==true){
					while (el.parentNode && el.tagName.toLowerCase() != 'tr') {
						el = el.parentNode;
					}
					if (el.parentNode && el.parentNode.rows.length > 1) {
						el.parentNode.removeChild(el);
					}
				} else {
					document.getElementById(id).checked=false;
				}	   
			}
		}
		function save(){
			document.form1.action = "${pageContext.request.contextPath}/insertNonElectricMotor";
			document.form1.submit();
		}	
		function wclose() {
			window.close();
		}
		</script>
	</body>
</html>