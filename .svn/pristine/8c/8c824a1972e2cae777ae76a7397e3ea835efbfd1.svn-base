<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>Documents Upload</title>
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
		<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
		<script type="text/javascript" src="<%=request.getContextPath()%>/js/common.js"></script>
		<script src="<%=request.getContextPath()%>/bootstrap/js/modernizr.custom.63321.js"></script>
		<script src="<%=request.getContextPath()%>/bootstrap/js/jquery-1.9.1.min.js"></script>
  		<script src="<%=request.getContextPath()%>/bootstrap/js/bootstrap.min.js"></script>
	</head>
	<body>
	<s:set var="uploadDocList" value="%{uploadDocList}"/>
	<s:set var="documentList" value="%{documentList}"/>
		<div id="page" class="content">
			<s:form id="form1" name="form1" method="post" theme="simple" enctype="multipart/form-data">
				<s:if test='"view".equals(reqFrom)'>
					<div class="container">
					  	<div class="panel panel-primary">
					  		<div class="panel-body">
					  			<ul class="nav nav-tabs">
							    	<li class="active"><a data-toggle="tab" href="#customer">Cusotmer Uploaded</a></li>
							    	<li><a data-toggle="tab" href="#surveyor">Surveyor Uploaded</a></li>
							  	</ul>
							  	<div class="tab-content">
							  		<div id="customer" class="tab-pane fade in active">
								   		<s:if test='custAttachedDocs!=null && custAttachedDocs.size()>0'>
											<div class="row">
												<table class="footable">
								       				<thead>
														<tr>
															<th width="5%">S.No.</th>
															<th width="15%">Type</th>
															<th width="15%">File</th>
															<th width="25%">Description</th>
															<th ></th>
														</tr>
													</thead>
													<tbody>
														<s:iterator value="custAttachedDocs" var="list" status="stat">
															<tr>
																<td align="center"><s:property value="#stat.count"/></td>
																<td>
																	<s:property value="#list.DOCUMENT_DESC"/>
																</td>
																<td>
																	<s:property value="#list.FILE_NAME"/>
																</td> 
																<td>
																	<s:property value="#list.DESCRIPTION"/>
																</td>
																<td align="center">
																	<button type="button" class="btn btn-sm btn-info" onclick="downloadDoc('<s:property value="#list.FILE_PATH_NAME"/>','<s:property value="#list.FILE_NAME"/>')">
																		<i class="glyphicon glyphicon-save"></i>
																	</button>
																</td>
															</tr>
														</s:iterator>
													</tbody>
												</table>
											</div>
						       			</s:if>
						       			<s:else>
						       				<font color="blue">No Documents Uploaded</font>
						       			</s:else>
							    	</div>
							    	<div id="surveyor" class="tab-pane fade">
							      		<s:if test='surveyorAttachedDocs!=null && surveyorAttachedDocs.size()>0'>
											<div class="row">
												<table class="footable">
									       			<thead>
														<tr>
															<th width="10%">S.No.</th>
															<th width="30%">Type</th>
															<th width="30%">File</th>
															<th width="30%">Description</th>
															<th ></th>
														</tr>
													</thead>
													<tbody>
															<s:iterator value="surveyorAttachedDocs" var="list" status="stat">
															<tr>
																<td align="center"><s:property value="#stat.count"/></td>
																<td>
																	<s:property value="#list.DOCUMENT_DESC"/>
																</td>
																<td>
																	<s:property value="#list.FILE_NAME"/>
																</td> 
																<td>
																	<s:property value="#list.DESCRIPTION"/>
																</td>
																<td align="center">
																	<button type="button" class="btn btn-sm btn-info" onclick="downloadDoc('<s:property value="#list.FILE_PATH_NAME"/>','<s:property value="#list.FILE_NAME"/>')">
																		<i class="glyphicon glyphicon-save"></i>
																	</button>
																</td>
															</tr>
															</s:iterator>
													</tbody>
												</table>
											</div>
							       		</s:if>
							       		<s:else>
						       				<font color="blue">No Documents Uploaded</font>
						       			</s:else>
							    	</div>
							  	</div>
					  		</div>
					  	</div>
					</div>
				</s:if>
				<s:else>
					<br class="clear"/>
					<div class="row">
						<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
							<b id="errorDesc" style="color:red"></b>
							<s:actionerror cssStyle="color:red;"/>
							<s:actionmessage cssStyle="color:green;"/>
						</div>
					</div>
					<br class="clear"/>
					<s:if test='"65".equalsIgnoreCase(#session.product_id) && vehicleDetailsById != null && vehicleDetailsById.size()>0'>
						<s:set var="multiVehicleDtls" value="%{vehicleDetailsById}"/>
						<div class="row">
							<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">				
								<div class="panel panel-primary">
				        			<div class="panel-heading">
				        				<s:text name="motor.vehicleDetails" />
				        			</div>
				        			<div class="panel-body">
										<table cellpadding="1" class="footable" cellspacing="1" border="1">
											<thead>
										        <tr>
													<th style="width:5%;"><s:label value="S.No." /></th>
													<th style="width:15%;"><s:label value="Vehicle Usuage"/></th>
													<th style="width:20%;"><s:label value="Make" /></th>
													<th style="width:20%;"><s:label value="Model" /></th>
													<th style="width:15%;"><s:label value="Sum Insured" /></th>
												</tr>
											</thead>
											<tbody>
												<tr>
													<td>1</td>
													<td><s:property value='#multiVehicleDtls.get("VEHICLETYPE_DESC")' /></td>
													<td><s:property value='#multiVehicleDtls.get("MAKE_NAME")' /></td>					
													<td><s:property value='#multiVehicleDtls.get("MODEL_NAME")'/> </td>
													<td><s:property value='#multiVehicleDtls.get("SUMINSURED_VALUE_LOCAL")' /></td>
												</tr>
											</tbody>
										</table>
									</div>
								</div>
							</div>
						</div>
						<br class="clear"/>
					</s:if>
					<div class="row">
						<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">				
							<div class="panel panel-primary">
			        			<div class="panel-heading">
			        				Documents
			        			</div>
			        			<div class="panel-body">
			        				<%--
			        				<s:set var="documentDtls" value="%{documentDetails}"/>
			        				--%>
									<table id="QuoteDocTable" cellpadding="1" class="footable" cellspacing="1" border="1">
										<thead>
											<tr>
												<th width="5%">S.No.</th>
												<th width="30%">Type</th>
												<th width="35%">File</th>
												<th width="25%">Description</th>
												<th ></th>
												<th ></th>
											</tr>
										</thead>
										<tbody>
											<s:if test="uploadDocList != null && uploadDocList.size>0">
											<s:iterator value="uploadDocList" var="list" status="stat">
											<tr>
											<td align="center"><s:property value="#stat.count"/></td>
											<td>
													<s:property value="#list.DOCUMENT_DESC"/>
											</td>
											<td>
													<s:property value="#list.FILE_NAME"/>
											</td> 
											<td>
													<s:property value="#list.DESCRIPTION"/>
											</td>
											<td align="center">
														<button type="button" class="btn btn-sm btn-info" onclick="downloadDoc('<s:property value="#list.FILE_PATH_NAME"/>','<s:property value="#list.FILE_NAME"/>')">
															<i class="glyphicon glyphicon-save"></i>
														</button>
												</td>
												<td align="center">
														<button type="button" class="btn btn-sm btn-danger" onclick="return deleteDoc('<s:property value="#list.FILE_PATH_NAME"/>')">
															<i class="glyphicon glyphicon-remove-sign"></i>
														</button>
												</td>
												
												<!--<td align="center">
													<s:if test='#documentDtls.get(""+#record.DOCUMENT_ID)!=null && !"".equals(#documentDtls.get(""+#record.DOCUMENT_ID))'>
														<button type="button" class="btn btn-sm btn-info" onclick="downloadDoc('<s:property value='#documentDtls.get(""+#record.DOCUMENT_ID)'/>')">
															<i class="glyphicon glyphicon-save"></i>
														</button>
													</s:if>
												</td>-->
											</tr>
											</s:iterator>
											</s:if>
											<s:else>
											<tr>
												<td align="center">1</td>
												<!--<td>
													<s:property value="#record.DOCUMENT_DESC"/>
													<s:if test='"Y".equals(#record.MANDATORY_STATUS)'>
														<font color="red">*</font>
													</s:if>
												</td>-->
												<td>
													<s:select name="documentIdList[0]" id="documentIdList[0]" cssClass="inputBoxS" list="documentList" listKey="DOCUMENT_ID" listValue="DOCUMENT_DESC" headerKey="" headerValue="-Select-" />
												</td>
												<td>
													<s:file name="upload" id="upload[0]" cssClass="inputBox"/>
												</td> 
												<td>
													<s:textfield name="docDescription[0]" id="docDescription[0]" cssClass="inputBox" cssStyle="text:" maxlength="50"/>
												</td>
	
												<td colspan="2" >
													<s:hidden id="chk1" onclick="deleteRow(chk1, this)"/>
												</td>
												</tr>
										</s:else>
										</tbody>
									</table>
										<div class="boxcontent" align="right">
									    	<input type="button" name="addadd" value="Add" class="btn btn-sm btn-primary" onclick="addQuoDocRow()" />
									    </div>
								</div>
							</div>
						</div>
					</div>
					<br class="clear"/>
					<div class="row" align="center">
						<s:submit type="button" cssClass="btn btn-sm btn-danger" value="Close" onclick="wclose();"/>
						<s:submit type="button" cssClass="btn btn-sm btn-success" value="Upload" onclick="return docupload();"/>
					</div>
					<br class="clear"/>
				</s:else>
				<s:hidden name="applicationNo"/>
				<s:hidden name="quoteNo"/>
				<s:hidden name="vehicleId"/>
				<s:hidden name="deleteVehicleId"/>
				<s:hidden name="filePath" id="filePath"/>
				<s:hidden name="fileName" id="fileName"/>
				<s:hidden name="reqFrom" id="reqFrom"/>
				<s:hidden name="product_id" id="product_id" value="#session.product_id"/>
			</s:form>
		</div>
		<script type="text/javascript">
		function docupload() {
		 		try{
		 			
					document.getElementById('errorDesc').innerHTML = "";
					var error = "";
					var table = document.getElementById('QuoteDocTable');
					var rowCount = table.rows.length;
					var size='<s:property value="uploadDocList.size()"/>';
					var i=parseInt(size);
					while( i<(rowCount-1)) {
						
						var file=document.getElementById('upload['+ i +']').files ;
						if(document.getElementById('upload['+ i +']').value == "") {
							error+="please choose file at row "+ (i+1) + "<br/>"
						} else{
							error += validateFile((i+1), document.getElementById('upload['+i+']').value);
							/*if(error.length > 0) {
							
							var size=getElementById('upload['+ i +']').size
							var sz=size/(1024*1024);
                      
                                  //RESTRICTING UPLOAD FILE SIZE TO 2MB

                                 if(sz >= 2)
                                 {
                                 }
							}*/
						}
						if(document.getElementById('documentIdList['+ i +']').value == "") {
								error+="please select document Type at row "+ (i+1) + "<br/>"
						} 
						i++;
					}
				if(error.length > 0) {
					document.getElementById('errorDesc').innerHTML = error;
					   $("html, body").animate({ scrollTop: 0 }, 600);
					 
				} else if(i!=parseInt(size)){
					document.form1.action = "${pageContext.request.contextPath}/submitdocumentDoUpload";
					document.form1.submit();
					
					}
			 	}catch(e) {
				console.debug(e);
			}
			  return false;
		}
		
		/*function doupload() {
			 
				document.getElementById('errorDesc').innerHTML = "";
				var error = "";
				var size = '<s:property value="documentList.size()"/>';
				for(i=0 ; i<size ; i++) {
					if(document.getElementById('uploadMadatoryYN['+i+']').value == "Y") {
						error += validateFile((i+1), document.getElementById('upload['+i+']').value);
					}
					if(document.getElementById('upload['+ i +']').value == "") {
						document.getElementById('documentIdList['+i+']').value = "";
					} else {
						$('#documentIdList['+i+']').val($('#documentIdOrgList['+i+']').val());
						document.getElementById('documentIdList['+i+']').value = document.getElementById('documentIdOrgList['+i+']').value;
					}
				}
				if(error.length > 0) {
					document.getElementById('errorDesc').innerHTML = error;
					$('html').animate({ scrollTop: 0 }, 'fast');
				} else {
					document.form1.action = "${pageContext.request.contextPath}/submitdocumentMotor";
					document.form1.submit();
				}
			 
			return false;
		}*/
		function validateFile(rownum, sFileName) {
			var error = "";
			var _validFileExtensions = [".jpg", ".jpeg", ".bmp", ".gif",".docx", ".png",".pdf",".doc",".xls",".xlsx",".txt"];
			//var sFileName = oInput.value;
            if (sFileName.length > 0) {
            	var blnValid = false;
                for (var j = 0; j < _validFileExtensions.length; j++) {
                    var sCurExtension = _validFileExtensions[j];
                    if (sFileName.substr(sFileName.length - sCurExtension.length, sCurExtension.length).toLowerCase() == sCurExtension.toLowerCase()) {
                        blnValid = true;
                        break;
                    }
                }
                if (!blnValid) {
                	error = "Please select valid file ( " + _validFileExtensions.join(", ") + " ) at " + rownum + "<br/>";
                }
            } else {
            	error = "Please select file ( " + _validFileExtensions.join(", ") + " ) at " + rownum + "<br/>";
			}
			return error;
		}
		function wclose() {
			window.close();
		}
		function downloadDoc(filePath,sFileName) {
			document.getElementById('fileName').value = sFileName;
			document.getElementById('filePath').value = filePath;
			document.form1.action = "${pageContext.request.contextPath}/downloaddocumentDoUpload";
			document.form1.submit();
		}
	function deleteDoc(filePath) {
		var decision = confirm("File will be deleted. Do You Want to continue? ","");
			if (decision==true){
				document.getElementById('filePath').value = filePath;
				document.form1.action = "${pageContext.request.contextPath}/deletedocumentDoUpload";
				document.form1.submit();
				}
			return false;
		}
			
		function createDocList(cell, rowCount){
			element = document.createElement("select");
        	element.className = "inputBoxS";
        	element.name = "documentIdList["+(rowCount-1)+"]";
        	element.id = "documentIdList["+(rowCount-1)+"]";
        		var objOption = document.createElement("option");
        	 objOption.text = '-Select-';
        	 objOption.value = '';
         	if(document.all && !window.opera){
         		element.add(objOption);
        		}else{
         			element.add(objOption, null);
         		}
        		 <s:iterator value="documentList">
	        	 var objOption = document.createElement("option");
	         	objOption.text = '<s:property value="DOCUMENT_DESC"/>';
	         	objOption.value = '<s:property value="DOCUMENT_ID"/>';
	         	if(document.all && !window.opera){
	         		element.add(objOption);
	         	}else{
	         		element.add(objOption, null);
	         }
         </s:iterator>
         
         
       cell.appendChild(element);
	}
		function addQuoDocRow(){
			var table = document.getElementById('QuoteDocTable');
			var rowCount = table.rows.length;
			var row = table.insertRow(rowCount);
			row.className="runtext";
			row.id = "new_"+rowCount;
			
			cell = row.insertCell(0);     
	    	cell.align = "center"; 
	     	cell.innerHTML = rowCount;
	     	var element = document.createElement("input");
			element.className = "inputBoxS";	 
		 	element.type = "hidden";	
			element.value=rowCount;
			cell.appendChild(element);
			
			
			cell = row.insertCell(1);
	   		createDocList(cell, rowCount);	 	
			
		
			
			cell = row.insertCell(2);
			var element = document.createElement("input");
			element.className = "inputBox";
			element.name = "upload";
			element.id = "upload["+(rowCount-1)+"]";
			element.type = "file";

			cell.appendChild(element);
			
			cell = row.insertCell(3);
			var element = document.createElement("input");
			element.className = "inputBox";
			element.type = "text";
			element.name = "docDescription["+(rowCount-1)+"]";
			element.maxlength = "50";
			element.id = "docDescription["+(rowCount-1)+"]";
			cell.appendChild(element);
			
			cell = row.insertCell(4);
			cell.align = "center";
			cell.setAttribute('colSpan', '2');
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
	</script>
	</body>
</html>