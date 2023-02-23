<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<SCRIPT language=Javascript>
		function isNumberKey(evt) {
			var charCode = (evt.which) ? evt.which : event.keyCode;
			if (charCode != 46 && charCode > 31 
			&& (charCode < 48 || charCode > 57))
			return false;
			return true;
		}
	</SCRIPT>
<%-- 	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/dataTables/css/dataTables.responsive.css"> --%>
<%-- 	<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/bootstrap/css/gallery.css" /> --%>
<%-- 	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/dataTables/css/dataTables.jqueryui.css"> --%>
<%-- 	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/dataTables/css/dataTables.tableTools.css"> --%>
<%-- 	<link  rel="stylesheet" type="text/css"  href="<%=request.getContextPath()%>/dataTables/css/jquery-start-ui.css"/> --%>
<%-- 	<script type="text/javascript" src="<%=request.getContextPath()%>/dataTables/js/jquery-1.11.1.min.js"></script> --%>
<%-- 	<script type="text/javascript" src="<%=request.getContextPath()%>/dataTables/js/jquery.dataTables.min.js"></script> --%>
<%-- 	<script type="text/javascript" src="<%=request.getContextPath()%>/dataTables/js/dataTables.jqueryui.js"></script> --%>
<%-- 	<script type="text/javascript" src="<%=request.getContextPath()%>/dataTables/js/dataTables.responsive.js"></script> --%>
<%-- 	<script type="text/javascript" src="<%=request.getContextPath()%>/dataTables/js/dataTables.tableTools.js"></script> --%>
	<script type="text/javascript">
	
		$(document).ready(function () {
			 		  $('#gridTableMake').DataTable({
				    	  "responsive": true,
				        "columnDefs": {
				          "orderable": false
				        },
				        language: {
				          //customize pagination prev and next buttons: use arrows instead of words
				          'paginate': {
				            'previous': '<span><i class="fas fa-chevron-circle-left"></i></span>',
				            'next': '<span><i class="fas fa-chevron-circle-right"></i></span>'
				          },
				          //customize number of elements to be displayed
				          "lengthMenu": '<select class="form-control input-sm">' +
				            '<option value="10">10</option>' +
				            '<option value="20">20</option>' +
				            '<option value="30">30</option>' +
				            '<option value="40">40</option>' +
				            '<option value="50">50</option>' +
				            '<option value="-1">All</option>' +
				            '</select>'
				        }
				      })
				    });
		
		$(function() {
			try {
				$('#startDate').datepicker({
					todayHighlight: true,
					format: "dd/mm/yyyy"
				}).on('changeDate', function(e){
				    $(this).datepicker('hide');
				});
				$('#endDate').datepicker({
					todayHighlight: true,
					format: "dd/mm/yyyy"
				}).on('changeDate', function(e){
				    $(this).datepicker('hide');
				});
			} catch(err) {console.error(err);}
		});
		
	 </script>
	 <style type="text/css">
	 .tableColWidth {
	 	min-width: 100px;
	 	max-width: 750px;
	 	width: 100px;
	 	white-space: normal;
	 }
	 
	  .tableColWidth1 {
	 	min-width: 500px;
	 	max-width: 750px;
	 	width: 450px;
	 	white-space: normal;
	 }
	 </style>
	 <style type="text/css">
	 .tableColNoWrap {
	 	min-width: 100px;
	 	max-width: 750px;
	 	width: 100px;
	 	white-space: nowrap;
	 }
	 </style>	
</head>
<body>
<s:form name="form1" theme="simple">
<div class="container vehDtl">
<div class="Card_Parent ">
<div class="card card-5">
<div class="p-3">
	<div class="row">
		<div class="col-md-12">
			<div class="panel panel-primary">
				<div class="panel-heading">
					<h3><s:text name="label.moUploadImages"/></h3>
				</div>				
				<div class="panel-body">
					<s:if test="hasActionErrors()">
					<div class="row">
						<font color="red"><s:actionerror cssStyle="list-style: none;"/></font>
					</div>
					<br class="clear" />
					</s:if>
					<s:if test="mode != 'list' && mode != 'viewlist'">
						<div class="row">
							<div class="col-xs-12 col-sm-12 col-md-6 col-lg-4">
								<div class="text"><s:text name="label.startDate"/><font color="red">*</font></div>
								<div class="tbox">
									<s:textfield name="startDate" id="startDate" cssClass="form-control" readonly="true" />
								</div>
							</div>
							<div class="col-xs-12 col-sm-12 col-md-6 col-lg-4">
								<div class="text"><s:text name="label.endDate" /><font color="red">*</font></div>
								<div class="tbox">
									<s:textfield name="endDate" id="endDate" cssClass="form-control" readonly="true" />
								</div>
							</div>
							<div class="col-xs-12 col-sm-12 col-md-6 col-lg-4" align="center">
								<br/>
								<input type="button" onclick="getMoUploadImg();" class="btn btn-sm btn-success" value="Submit" />
							</div>
						</div>
						</s:if>
					</div>
					<div class="panel-body">
					<s:if test="mode == 'list'">
						<div class="row">
							<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
								<table class="table table-bordered table-hover" id="gridTableMake" cellspacing="0" width="100%" >
									<thead class="bluecolortable">
										<tr>
											<th class="no-sort"><s:text name="label.master.sno"/></th>
											<th><s:text name="label.policyNo"/></th>					
											<th><s:text name="label.cust.name"/></th>		
											<th align="center"><s:text name="label.refNo"/></th>
											<th align="center"><s:text name="label.doc.type"/></th>					
											<!--<th><s:text name="label.device.detail"/></th>-->
											<th><s:text name="label.upload.date"/></th>
											<th><s:text name="label.no.of.image"/></th>	
											<th><s:text name="View"/></th>
										</tr>
									</thead>
									<tbody class="rowevencolor">
										<s:iterator value="moUploadImgList" var="list" status="stat">
										<tr>
											<td align="center"><s:property value="#stat.count"/></td>
											<td><s:property value="#list.POLICY_NO" /></td>
											<td><s:property value="#list.CUSTOMER_NAME" /></td>
											<td align="center"><s:property value="#list.REF_NO" /></td>
											<td align="center"><s:property value="#list.DOCUMENT_TYPE" /></td>
											<!--<td><s:property value="#list.DEVICE_MANUF" /></td>-->
											<td><s:property value="#list.REQ_DATE" /></td>
											<td><s:property value="#list.NO_OF_IMAGES" /></td>
											<td align="center"><input value="View" type="button"  class="btn btn-sm btn-success" onclick="fnClick('<s:property value="#list.REF_NO"/>');" /></td>
										</tr>
										</s:iterator>
									</tbody>
								</table>
							</div>
						</div>
						<br class="clear" />
						<div class="row">
						<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" align="center">
							<input type="button" onclick="getBack();" class="btn btn-sm btn-danger" value="Back" />
						</div>
						</div>
						<s:hidden name="startDate"  id="startDate"/>
						<s:hidden name="endDate"  id="endDate"/>
					</s:if>
					<s:if test="mode == 'viewlist'">
					
					
					<div class="panel-body">
	        			<div class="row">
	        				<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
								<div class="">
   									<label><b><s:text name="label.refNo"/></b>&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;</label><s:property value="refNo" />
								</div>
							</div>
							<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
								<div class="">
   									<label><b><s:text name="label.policyNo"/></b>&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;</label><s:property value="policyNo" />
								</div>
							</div>
							<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
								<div class="">
   									<label><b><s:text name="label.cust.name"/></b>&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;</label><s:property value="custName" />
								</div>
							</div>
							<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
								<div class="">
   									<label><b><s:text name="label.doc.type"/></b>&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;</label><s:property value="docType" />
								</div>
							</div>
							<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
								<div class="">
   									<label><b><s:text name="label.upload.date"/></b>&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;</label><s:property value="uploadDate" />
								</div>
							</div>
        				</div>
        				<br/>
        				<br/>
			        		
<!-- 						<div class="boxcontent"> -->
<!-- 							<div id="container"> -->
								<ul>
									<s:iterator value="moUploadImgListView" var="list" status="stat">
										<li>
											<a href="<%=request.getContextPath()%>/MobileUploadedImages/<s:property value="#list.FILE_NAME"/>" data-imagelightbox="f">
											<img width="100%" src="<%=request.getContextPath()%>/MobileUploadedImages/<s:property value="#list.FILE_NAME" />" alt="<s:property value="refNo" />"></a><br class="clear" />
											<button  type="button" class="btn btn-sm btn-info" onclick="downloadDoc('<s:property value="#list.DOC_ID"/>','<s:property value="#list.FILE_NAME"/>')">
											<i class="glyphicon glyphicon-save"></i>
											</button>
										</li>
									</s:iterator>
								</ul>
<!-- 							</div> -->
<!-- 						</div> -->
					</div>
						<div class="row">
							<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" align="center">
								<input type="button" onclick="getMoUploadImg();" class="btn btn-sm btn-danger" value="Back" />
							</div>
						</div>
						<s:hidden name="startDate"  id="startDate"/>
						<s:hidden name="endDate"  id="endDate"/>
					</s:if>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
</div>
</div>
</s:form>
</body>
<script src="<%=request.getContextPath()%>/bootstrap/js/imagelightbox.min.js"></script>
<script src="<%=request.getContextPath()%>/bootstrap/js/image_zoomer.js"></script>
<script type="text/Javascript" >
function getMoUploadImg(){
	    document.form1.action='getMobileUploadImgMotorAdminNew.action';
	    document.form1.submit();
}
function getBack(){
    document.form1.action='mUploadImgMotorAdminNew.action';
    document.form1.submit();
}
function fnClick(val){
		document.form1.action='getMobileUploadImgViewMotorAdminNew.action?refNo='+val;
	    document.form1.submit();
}
function downloadDoc(docId,sFileName) {
	document.form1.action = '${pageContext.request.contextPath}/downloadAttachmentMotorAdminNew.action?fileName='+sFileName+'&documentId='+docId;
	document.form1.submit();
}
</script>
</html>