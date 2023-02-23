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
	.content.container-fluid {
    padding-right: 25px;
    padding-left: 25px;
    }
		.bluecolortable th:first-child{
  border-radius: 20px 0px 0px 0px  !important;

}
.bluecolortable th:last-child{
  border-radius: 0px 20px 0px 0px  !important;

}
.rowevencolor td:first-child{
  border-radius: 0px 0px 0px 20px  !important;

}
.rowevencolor td:last-child{
  border-radius: 0px 0px 20px 0px  !important;

}
.rowevencolor tr:nth-child(even) {
  background-color: powderblue !important
}

#QuoteDocTable{
  font-family: "Lato_Regular";
  border-radius: 20px !important;
  box-shadow: 0 1px 1px rgba(0,0,0,0.25), 
              0 2px 2px rgba(0,0,0,0.20), 
              0 4px 4px rgba(0,0,0,0.15), 
              0 8px 8px rgba(0,0,0,0.10),
              0 16px 16px rgba(0,0,0,0.05);
  font-size: 15px;
  overflow: hidden;         
}
@media only screen and (max-width: 600px) {
  
  /* Force table to not be like tables anymore */
  table,
  thead,
  tbody,
  th,
  td,
  tr {
    display: block;
  }
  /* Hide table headers (but not display: none;, for accessibility) */
  thead tr,
  tfoot tr {
    position: absolute;
    top: -9999px;
    left: -9999px;
  }
  td {
    /* Behave  like a "row" */
    border: none;
    border-bottom: 1px solid #eee;
    position: relative;
    padding-left: 50% !important;
  }
  td:before {
    /* Now like a table header */
    position: absolute;
    /* Top/left values mimic padding */
    top: 6px;
    left: 6px;
    width: 45%;
    padding-right: 10px;
    white-space: nowrap;
  }
  
  .table td:nth-child(1) {
      background: #ccc;
      height: 100%;
      top: 0;
      left: 0;
      font-weight: bold;
  }
  /*
	Label the data
	*/
  td:nth-of-type(1):before {
    content: "S.No";
  }	
  td:nth-of-type(2):before {
    content: "Type";
  }
  td:nth-of-type(3):before {
    content: "File";
  }
  td:nth-of-type(4):before {
    content: "Descriptin";
  }
 /* td:nth-of-type(5):before {
    content: "Type Of Body";
  }
  td:nth-of-type(5):before {
    content: "Vehicle Value";
  }*/
  
  .dataTables_length {
    display: none;
  }
  }

.inputBox {
  outline :none !important;
  border :none !important;
}
.inputBox : focus {
  outline :none !important;
  border :none !important;
}
    .form-control-sm{
    height :35px;
    }   
		
		</style>
		
	</head>
	<body>
	<s:set var="claimIntimUploadList" value="%{claimIntimUploadList}"/>
		<div id="page" class="content">
			<s:form id="form1" name="form1" method="post" theme="simple" enctype="multipart/form-data">
				<br class="clear"/>
				<div class="container PolicyPage">
					
					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
						<span style="color:red;"><s:actionerror/></span>
					</div>
					
			        <div class="Card_Parent PolicyInformation">
	                	
	               			<s:if test='"download".equals(mode)'>
							<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">				
					                	<div class="col-md-12">
					                		<h3><s:text name="Download Documents" /></h3><hr>
				                		</div><br>
				               		
					        			<%-- <s:if test="claimAttachedDocs != null && claimAttachedDocs.size>0"> --%>
											<table id="QuoteDocTable" cellpadding="1" class="footable" cellspacing="1" border="1">
												<thead class="bluecolortable">
													<tr>
														<th width="5%">S.No.</th>
														<th width="30%">Type</th> 
														<th width="35%">File</th>
														<th width="25%">Description</th>
														<th ></th>
													</tr>
												</thead>
												<tbody>
												<s:if test="claimAttachedDocs != null && claimAttachedDocs.size>0">
													<s:iterator value="claimAttachedDocs" var="list" status="stat">
														<tr>
														<td align="center"><s:property value="#stat.count"/></td>
														<td>
															<s:property value="#list.DOC_TYPE_ID"/>
														</td>
														<td>
															<s:property value="#list.FILE_NAME"/>
														</td> 
														<td>
															<s:property value="#list.DESCRIPTION"/>
														</td>
														<td align="center">
															<button type="button" class="btn btn-sm btn-info" onclick="downloadDocAdmin('<s:property value="#list.FILE_PATH_NAME"/>','<s:property value="#list.FILE_NAME"/>')">
																<i class="glyphicon glyphicon-save"></i>
															</button>
														</td>
														</tr>
													</s:iterator>
													</s:if>
													<s:else>
														<tr>
														<td colspan="5" align="center">
									       				<font color="blue">No Documents Uploaded</font>
									       				</td>
									       				</tr>
									       			</s:else>
												</tbody>
											</table>
										<%-- </s:if>
										<s:else>
						       				<font color="blue">No Documents Uploaded</font>
						       			</s:else> --%>
								</div><br>
							</s:if>
							</div>
							</div>
				<s:else>	
					<div class="row">
						<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
							<div class="panel panel-primary">			
								<div class="panel-body" align="center">
								<br class="clear"/>
								<div class="row">
									<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
										<b id="errorDesc" style="color:red"></b>
										<s:actionerror cssStyle="color:red;"/>
										<s:actionmessage cssStyle="color:green;"/>
									</div>
								</div>
								<div class="row">
									<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">				
										<div class="panel panel-primary">
						        			<div class="panel-heading" align="left">
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
														<s:if test="claimIntimUploadList != null && claimIntimUploadList.size>0">
														<s:iterator value="claimIntimUploadList" var="list" status="stat">
														<tr>
														<td align="center"><s:property value="#stat.count"/></td>
														<td>
																<s:property value="#list.DOC_TYPE_ID"/>
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
															<!-- <td align="center">1</td> -->
															<td align="center"><strong>1</strong>
															<s:hidden name="serialNos[%{0}]" id="serialNos%{0}" value="1"/></td>
															<!--<td>
																<s:property value="#record.DOCUMENT_DESC"/>
																<s:if test='"Y".equals(#record.MANDATORY_STATUS)'>
																	<font color="red">*</font>
																</s:if>
															</td>-->
															<%--<td>
																<s:select name="documentIdList[0]" id="documentIdList[0]" cssClass="inputBoxS" list="documentList" listKey="DOCUMENT_ID" listValue="DOCUMENT_DESC" headerKey="" headerValue="-Select-" />
															</td>--%>
															<td>
																<s:select name="documentIdList[0]" id="documentIdList[0]" cssClass="inputBoxS" list="#{'Others':'Others'}" headerKey="" headerValue="-Select-" />
															</td>
															<td>
																<s:file name="upload" id="upload[0]" cssClass="inputBox"/>
															</td> 
															<td>
																<s:textfield name="docDescription[0]"  id="docDescription[0]" cssClass="inputBox" cssStyle="text:" maxlength="50"/>
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
												    
												    <div class="row" align="center">
														<s:submit type="button" cssClass="btn btn-sm btn-primary" value="Upload" onclick="return docupload();"/>
													</div>
											</div>
										</div>
									</div>
								</div>
								<br class="clear"/>
								<div class="row" align="center">
									<s:submit type="button" cssClass="btn btn-sm btn-danger" value="Back" onclick="wclose();"/>
									<s:submit type="button" cssClass="btn btn-sm btn-success" value="Submit" onclick="return clmSuccess();"/>
								</div>
							</div>
						</div>
					</div>
				</div>
			</s:else>
				<br class="clear"/>
				<s:hidden name="applicationNo"/>
				<s:hidden name="claimRefNo"/>
				<s:hidden name="vehicleId"/>
				<s:hidden name="deleteVehicleId"/>
				<s:hidden name="filePath" id="filePath"/>
				<s:hidden name="fileName" id="fileName"/>
				<s:hidden name="reqFrom" id="reqFrom"/>
				<s:hidden name="product_id" id="product_id" value="#session.product_id"/>
				
				
				<s:hidden name="name"/>
				<s:hidden name="passport"/>
				<s:hidden name="phone"/>
				<s:hidden name="vehicleRegNo"/>
				<s:hidden name="dateOfAccident"/>
				<s:hidden name="policyNo1"/>
				<s:hidden name="policyNo2"/>
				<s:hidden name="policyNo3"/>
				<s:hidden name="policyNo4"/>
				<s:hidden name="policyNo"/>
				<s:hidden name="mode"/>
			</s:form>
		</div>
		<script type="text/javascript">
		
		var slNo=2;

		function docupload() {
			var error = "";
			 	 try{
			document.getElementById('errorDesc').innerHTML = "";
			var table = document.getElementById('QuoteDocTable');
			var rowCount = table.rows.length;
			//var size='<s:property value="claimUploadDocList.size()"/>';
			var size='0';
			var i=parseInt(size);
			var j=i;
			while( i<(slNo-1)) {
				try{
					var value =document.getElementById("serialNos"+i+"").value;
					if(value != null && value != ''){
						var file=document.getElementById('upload['+ i +']').files ;
						if(document.getElementById('upload['+ i +']').value == "") {
							error+="please choose file at row "+ (j+1) + "<br/>"
						} else{
							error += validateFile((i+1), document.getElementById('upload['+i+']').value);
						}
						if(document.getElementById('documentIdList['+ i +']').value == "") {
							error+="please select document Type at row "+ (j+1) + "<br/>"
						}
						j++;
					}
					i++;
				}catch(e) {
					//j++;
					i++;
					console.debug(e);
				}
			}
			if(error.length > 0) {
				document.getElementById('errorDesc').innerHTML = error;
				//document.getElementById('proceed1').disabled = false;
				$("html, body").animate({ scrollTop: 0 }, 600);
			}else if(i!=parseInt(size)){
			//alert("i "+i+"size "+parseInt(size));
				document.form1.action = "${pageContext.request.contextPath}/submitdocumentClaimIntimation.action";
				document.form1.submit();
			}
			 	}catch(e) {
			console.debug(e);
			}
			  return false;
		}
		
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
         var objOption = document.createElement("option");
	         	objOption.text = 'Others';
	         	objOption.value = 'Others';
	         	if(document.all && !window.opera){
	         		element.add(objOption);
	         	}else{
	         		element.add(objOption, null);
	         }
         
         
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
	     	cell.innerHTML = "<strong>"+rowCount+"</strong>";
	     	var element = document.createElement("input");
			element.className = "inputBoxS";	 
		 	element.type = "hidden";	
			element.value=slNo;
			cell.appendChild(element);
			
			element = document.createElement("input");
		       element.type = "hidden";
		       element.name = "serialNos["+(slNo-1)+"]";
		       element.id = "serialNos"+(slNo-1)+"";
		       element.value = slNo;
		       cell.appendChild(element);
			
			cell = row.insertCell(1);
	   		createDocList(cell, slNo);	 	
			
			cell = row.insertCell(2);
			var element = document.createElement("input");
			element.className = "inputBox";
			element.name = "upload";
			element.id = "upload["+(slNo-1)+"]";
			element.type = "file";

			cell.appendChild(element);
			
			cell = row.insertCell(3);
			var element = document.createElement("input");
			element.className = "inputBox";
			element.type = "text";
			element.name = "docDescription["+(slNo-1)+"]";
			element.maxlength = "50";
			element.id = "docDescription["+(slNo-1)+"]";
			cell.appendChild(element);
			
			cell = row.insertCell(4);
			cell.align = "center";
			cell.setAttribute('colSpan', '2');
			var element = document.createElement("input");
			element.type = "checkbox";
			element.id = "chk"+slNo;
			element.onclick = function () {deleteRow(this.id,this)};	
		    cell.appendChild(element);
		    slNo++;
		}
		
		function deleteRow(id, el) {
		var table = document.getElementById('QuoteDocTable');
			var rowCount = table.rows.length;
		
			var decision = confirm("Row will be deleted. Do You Want to continue? ","");{
				if (decision==true){
					while (el.parentNode && el.tagName.toLowerCase() != 'tr') {
						el = el.parentNode;
					}
					if (el.parentNode && el.parentNode.rows.length > 1) {
						el.parentNode.removeChild(el);
					}
					var renum = 1;
					$("tr td strong").each(function() {
					    $(this).text(renum);
					    renum++;
					});
					
				} else {
					document.getElementById(id).checked=false;
				}
			}
		}
		
		function wclose() {
			document.form1.action = "${pageContext.request.contextPath}/insertNewClaimIntimation";
			document.form1.submit();
		}
		function downloadDoc(filePath,sFileName) {
			document.getElementById('fileName').value = sFileName;
			document.getElementById('filePath').value = filePath;
			document.form1.action = "${pageContext.request.contextPath}/downloaddocumentClaimIntimation";
			document.form1.submit();
		}
		function deleteDoc(filePath) {
			var decision = confirm("File will be deleted. Do You Want to continue? ","");
			if (decision==true){
				document.getElementById('filePath').value = filePath;
				document.form1.action = "${pageContext.request.contextPath}/deletedocumentClaimIntimation";
				document.form1.submit();
				}
			return false;
		}
		function clmSuccess(){
			var size='<s:property value="claimIntimUploadList.size()"/>';
			if(size==0){
				var decision = confirm("Do You Want to continue without upload any file ? ","");
			}else {
				var decision =true;
			}
			if (decision){
				document.form1.action = "${pageContext.request.contextPath}/clmCompleteClaimIntimation.action";
				document.form1.submit();
			}
			return false;
		}
		function downloadDocAdmin(filePath,sFileName) {
			document.getElementById('fileName').value = sFileName;
			document.getElementById('filePath').value = filePath;
			document.form1.action = "${pageContext.request.contextPath}/downloadDocAdminClm";
			document.form1.submit();
		}
	</script>
	</body>
</html>