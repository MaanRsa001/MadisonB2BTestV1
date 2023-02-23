<%@ page isELIgnored="false" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html <s:if test="locale.language == 'ar'">dir="rtl"</s:if> >
<head>
<script type="text/javascript">
</script>

<style type="">
@media only screen and (max-width: 992px) {
  
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
    content: "Vehicle Usage";
  }
  td:nth-of-type(3):before {
    content: "Make";
  }
  td:nth-of-type(4):before {
    content: "Model";
  }
  td:nth-of-type(5):before {
    content: "Type Of Body";
  }
  td:nth-of-type(6):before {
    content: "Vehicle Value";
  }
  
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
        
</style>
</head>
<body>
 <s:set var="uploadDocList" value="%{uploadDocList}"/>
 <s:set var="documentList" value="%{documentList}"/>

	<div id="loading" class="ajaxLoader" style="width:100%">
	   <img id="loading-image" src="${pageContext.request.contextPath}/images/madisonSymbolLogo.png"/>
	</div>
	 <div class="modal-header">
       <h5 class="modal-title">Attach Documents</h5>
       <i class="far fa-times-circle mt-3" data-dismiss="modal"></i>
     </div>
	        
     <div class="modal-body">
     
     <s:if test='"view".equals(reqFrom)'>
  			<ul class="nav nav-tabs">
		    	<li class="active"><a data-toggle="tab" href="#customer">Cusotmer Uploaded</a></li>
		    	<li><a data-toggle="tab" href="#surveyor">Surveyor Uploaded</a></li>
		  	</ul>
		  	<div class="tab-content">
		  		<div id="customer" class="tab-pane fade in active">
			   		<s:if test='custAttachedDocs!=null && custAttachedDocs.size()>0'>
						<div class="row">
							<table class="table table-bordered table-hover" cellspacing="0" width="100%" id="tadaTable">
								<thead class="bluecolortable">
									<tr>
										<th>S.No.</th>
										<th>Type</th>
										<th>File</th>
										<th>Description</th>
										<th></th>
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
							<table class="table table-bordered table-hover" cellspacing="0" width="100%" id="tadaTable">
								<thead class="bluecolortable">
									<tr>
										<th>S.No.</th>
										<th>Type</th>
										<th>File</th>
										<th>Description</th>
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
        				<%-- <h3><s:text name="motor.vehicleDetails" /></h3>
                        <hr><br> --%>
						<table class="table table-bordered table-hover" cellspacing="0" width="100%" id="tadaTable">
							<thead class="bluecolortable">
						        <tr>
									<th><s:label theme="simple" value="S.No." /></th>
									<th><s:label theme="simple" value="Vehicle Usuage"/></th>
									<th><s:label theme="simple" value="Make" /></th>
									<th><s:label theme="simple" value="Model" /></th>
									<th><s:label theme="simple" value="Sum Insured" /></th>
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
			<br class="clear"/>
		</s:if>
		<div class="row">
			<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
				
				<table id="QuoteDocTable" class="table table-bordered table-hover" cellspacing="0" width="100%" id="tadaTable">
					<thead class="bluecolortable">
						<tr>
							<th>S.No.</th>
							<th>Type</th>
							<th>File</th>
							<th>Description</th>
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
								<s:select name="documentIdList[0]" theme="simple"  id="documentIdList[0]" cssClass="form-control" list="documentList" listKey="DOCUMENT_ID" listValue="DOCUMENT_DESC" headerKey="" headerValue="-Select-" />
							</td>
							<td>
								<s:file name="upload" id="upload[0]" theme="simple"  cssClass="form-control"/>
								<!-- <input type='file' name='upload' id='upload[0]'  class='form-control' /> -->
							</td> 
							<td>
								<s:textfield name="docDescription[0]" theme="simple"  id="docDescription[0]" cssClass="form-control" cssStyle="text:" maxlength="50"/>
							</td>
	
							<td colspan="2" >
								<s:hidden id="chk1" theme="simple"  onclick="deleteRow(chk1, this)"/>
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
		<br class="clear"/>
		<div class="row" align="center">
			<%-- <s:submit type="button" cssClass="btn btn-sm btn-danger" value="Close" data-dismiss="modal" />
			<s:submit type="button" cssClass="btn btn-sm btn-success" value="Upload" onclick="return docupload();"/> --%>
			<s:submit type="button" cssClass="btn btn-sm btn-danger" value="Close" data-dismiss="modal" />
			<button type="button" class="btn btn-sm btn-success" value="Upload" onclick="return docupload();">Upload</button>
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
   
     </div>
     <script type="text/javascript">
		function docupload() {
			//alert($('#form1').data);
		 		try{
		 			//var upload=document.getElementById('upload[0]').files ;
		 			//alert($('input[type=file]').val().replace(/C:\\fakepath\\/i, ''));
		 			//var myFile = $('#upload[0]').prop('files');
		 			//alert(myFile);
					document.getElementById('errorDesc').innerHTML = "";
					var error = "";
					var table = document.getElementById('QuoteDocTable');
					var rowCount = table.rows.length;
					var size='<s:property value="uploadDocList.size()"/>';
					var i=parseInt(size);
					while( i<(rowCount-1)) {
						
						var file=document.getElementById('upload['+ i +']').files ;
						//alert(file);
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
					//document.motor.action = "${pageContext.request.contextPath}/submitdocumentDoUpload";
					//document.motor.submit();
					
					//postFormRequest('${pageContext.request.contextPath}/submitdocumentNewDoUpload.action', 'attachDocumentAjax','form1');
					document.getElementById('reqFrom').value='upload';
					document.form1.action = "${pageContext.request.contextPath}/submitdocumentNewDoUpload.action";
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
			document.motor.action = "${pageContext.request.contextPath}/downloaddocumentDoUpload";
			document.motor.submit();
		}
	function deleteDoc(filePath) {
		var decision = confirm("File will be deleted. Do You Want to continue? ","");
			if (decision==true){
				document.getElementById('filePath').value = filePath;
				document.motor.action = "${pageContext.request.contextPath}/deletedocumentDoUpload";
				document.motor.submit();
				}
			return false;
		}
			
		function createDocList(cell, rowCount){
			element = document.createElement("select");
        	element.className = "form-control";
        	element.name = "documentIdList["+(rowCount-1)+"]";
        	element.id = "documentIdList["+(rowCount-1)+"]";
        	element.theme="simple";
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
			element.className = "form-control";	 
		 	element.type = "hidden";	
			element.value=rowCount;
			cell.appendChild(element);
			
			
			cell = row.insertCell(1);
	   		createDocList(cell, rowCount);	 	
			
		
			
			cell = row.insertCell(2);
			var element = document.createElement("input");
			element.className = "form-control";
			element.name = "upload";
			element.theme="simple";
			element.id = "upload["+(rowCount-1)+"]";
			element.type = "file";

			cell.appendChild(element);
			
			cell = row.insertCell(3);
			var element = document.createElement("input");
			element.className = "form-control";
			element.type = "text";
			element.name = "docDescription["+(rowCount-1)+"]";
			element.maxlength = "50";
			element.id = "docDescription["+(rowCount-1)+"]";
			element.theme="simple";
			cell.appendChild(element);
			
			cell = row.insertCell(4);
			cell.align = "center";
			cell.setAttribute('colSpan', '2');
			var element = document.createElement("input");
			element.type = "checkbox";
			element.id = "chk"+rowCount;
			element.theme="simple";
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