<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/struts-tags" prefix="s"%>
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
	
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/dataTables/css/dataTables.responsive.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/dataTables/css/dataTables.jqueryui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/dataTables/css/dataTables.tableTools.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/dataTables/js/dataTables.responsive.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/dataTables/js/dataTables.tableTools.js"></script>
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
		
	 </script>
	 <style type="text/css">
	 .premiumFixed {
	width: 300px;
	position: fixed;
	top: 50%;
	left: 75%;
	z-index: 1000;
}

.popover {
    background: #D6A441;
    color: #FFFFFF;
}

.popover.bottom .arrow:after {
    border-bottom-color: #D6A441;
}

	 </style>	
</head>
<body>
<s:form id="smsEmail" name="smsEmail" action="" theme="simple">
<div class="container vehDtl">
<div class="Card_Parent ">
	<div class="card card-5">
		<div class="p-3">
			<div class="col-md-12">
				<div class="panel panel-primary">
					<div class="panel-heading">
						<h3><s:text name="label.master.sms.email"/>&nbsp;&nbsp;<button type="button" class="btn btn-sm btn-primary" data-toggle="modal" data-refresh="true" href="<%=request.getContextPath()%>/getHelpDetailSmsEmailMaster.action" data-target="#modaladdVehicleDetails"> <img src="<%=request.getContextPath()%>/images/Help.png" width="20" height="20"/> </button></h3>
							<div class="modal fade" data-refresh="true" id="modaladdVehicleDetails" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
								<div class="modal-dialog modal-lg">
									<div class="modal-content">
										<div class="modal-header">
											<h4 class="modal-title"> <s:label key="motor.additionalVehicleDetails"/> </h4>
										</div>
										<div class="modal-body">
											<div class="te"></div>
										</div>
									</div>
								</div>
							</div>
					</div>
					<font color="green"><s:actionmessage cssStyle="list-style: none;"/></font>
					<s:if test='mode == "list"'>
						<div class="panel-body">
								<table class="table table-bordered table-hover" id="gridTableMake" cellspacing="0" width="100%" >
									<thead class="bluecolortable">
									<tr>
										<th class="no-sort"></th>
										<th><s:text name="label.master.sno"/></th>
										<th><s:text name="label.description"/></th>
										<th><s:text name="label.sms.required"/></th>
										<th><s:text name="label.sms.content"/></th>
										<th><s:text name="label.email.required"/></th>
										<th><s:text name="label.email.content"/></th>
										<th width="10px"><s:text name="label.email.to"/></th>
										<th width="10px"><s:text name="label.email.cc"/></th>
										<th width="10px"><s:text name="label.sms.to"/></th>
										<th><s:text name="label.master.status"/></th>
										<th><s:text name="label.master.edit"/></th>
									</tr>
									</thead>
									<tbody class="rowevencolor">
										<s:iterator value="smsEmailTempList" var="list" status="stat">
											<tr>
												<td></td>
												<td><s:property value="#stat.count" /></td>
												<td><s:property value="#list.REMARKS" /></td>
												<td><s:property value="#list.SMS_REQUIRED" /></td>
												<td><s:property value="#list.SMS_BODY" /></td>
												<td><s:property value="#list.MAIL_REQUIRED" /></td>
												<td><s:property value="#list.MAIL_BODY" /></td>
												<td width="10px"><s:property value="#list.EMAIL_TO" /></td>
												<td width="10px"><s:property value="#list.EMAIL_CC" /></td>
												<td width="10px"><s:property value="#list.SMS_TO" /></td>
												<td><s:property value="#list.STATUS" /></td>
												<td style="text-align: center;">
													<input type="button" class="btn btn-warning btn-sm" onclick="funSubmit('edit','<s:property value="#list.SNO" />');" value="Modify" />
												</td>
											</tr>
										</s:iterator>
									</tbody>
								</table>								
<!-- 							</div> -->
						</div>
					</s:if>
					<s:else>
						<div class="panel-body">
						<font color="red"><s:actionerror cssStyle="list-style: none;"/></font>
							<div class="row">									
								<div class="col-xs-12 col-sm-12 col-md-6 col-lg-4">
									<div class="form-group">
    									<label><s:text name="label.description"/></label>
    									<s:textfield name="remarks" id="remarks" cssClass="form-control" maxlength="150"></s:textfield>
									</div>
								</div>
								<div class="col-xs-12 col-sm-12 col-md-6 col-lg-4">
									<div class="form-group">
    									<label><s:text name="label.sms.required"/><font color="red">*</font></label>
    									<s:radio list="#{'Y':'Yes','N':'No'}" name="smsRequired" id="smsRequired" />
									</div>
								</div>
							</div>
							<div class="row">	
								<div class="col-xs-12 col-sm-12 col-md-6 col-lg-4">
									<div class="form-group">
    									<label><s:text name="label.sms.subject"/><font color="red">*</font></label>
    									<s:textarea name="smsSubject" id="smsSubject" rows="3" cssClass="form-control" maxlength="200"/>
									</div>
								</div>
								<div class="col-xs-12 col-sm-12 col-md-6 col-lg-4">
									<div class="form-group">
    									<label><s:text name="label.sms.body"/><font color="red">*</font></label>
    									<s:textarea name="smsBody" id="smsBody" rows="3" cssClass="form-control" maxlength="1000"/>
									</div>
								</div>
								<div class="col-xs-12 col-sm-12 col-md-6 col-lg-4">
									<div class="form-group">
    									<label><s:text name="label.sms.regards"/><font color="red">*</font></label>
    									<s:textarea name="smsRegards" id="smsRegards" rows="3" cssClass="form-control" maxlength="1000"/>
									</div>
								</div>
							</div>
							<div class="row">	
								<div class="col-xs-12 col-sm-12 col-md-6 col-lg-4">
									<div class="form-group">
    									<label><s:text name="label.email.required"/><font color="red">*</font></label>
    									<s:radio list="#{'Y':'Yes','N':'No'}" name="emailRequired" id="emailRequired" />
									</div>
								</div>
							</div>
							<div class="row">	
								<div class="col-xs-12 col-sm-12 col-md-6 col-lg-4">
									<div class="form-group">
    									<label><s:text name="label.email.subject"/><font color="red">*</font></label>
    									<s:textarea name="emailSubject" id="emailSubject" rows="3" cssClass="form-control" maxlength="200"/>
									</div>
								</div>
								<div class="col-xs-12 col-sm-12 col-md-6 col-lg-4">
									<div class="form-group">
    									<label><s:text name="label.email.body"/><font color="red">*</font></label>
    									<s:textarea name="emailBody" id="emailBody" rows="3" cssClass="form-control" maxlength="1000"/>
									</div>
								</div>
								<div class="col-xs-12 col-sm-12 col-md-6 col-lg-4">
									<div class="form-group">
    									<label><s:text name="label.email.regards"/><font color="red">*</font></label>
    									<s:textarea name="emailRegards" id="emailRegards" rows="3" cssClass="form-control" maxlength="1000"/>
									</div>
								</div>
								<div class="col-xs-12 col-sm-12 col-md-6 col-lg-4">
									<div class="form-group">
    									<label><s:text name="label.email.to"/></label>
    									<s:textarea name="emailTo" id="emailTo" cssClass="form-control tooltipContent" data-content="Enter the Email ID with Comma Separater(,)" maxlength="4000"/>
									</div>
								</div>
								<div class="col-xs-12 col-sm-12 col-md-6 col-lg-4">
									<div class="form-group">
    									<label><s:text name="label.email.cc"/></label>
    									<s:textarea name="emailCc" id="emailCc" cssClass="form-control tooltipContent" data-content="Enter the Email ID with Comma Separater(,)" maxlength="4000"/>
									</div>
								</div>
								<div class="col-xs-12 col-sm-12 col-md-6 col-lg-4">
									<div class="form-group">
    									<label><s:text name="label.sms.to"/></label>
    									<s:textarea name="smsTo" id="smsTo" cssClass="form-control tooltipContent" data-content="Enter the Mobile no with Comma Separater(,)"  maxlength="4000"/>
									</div>
								</div>
								<div class="col-xs-12 col-sm-12 col-md-6 col-lg-4">
									<label><s:text name="label.usertype"/></label>
									<div class="form-group">																
										<s:select name="userTypeArray" id="userTypeArray"  list="#{'admin':'Admin', 'RSAIssuer':'Under Writer', 'creditcontroller':'Credit Controller', 'surveyor':'Surveyor'}" label="" multiple="true" />	
										</div>
								</div>
								<div class="col-xs-12 col-sm-12 col-md-6 col-lg-4">
       											<div class="text"><s:text name="Documents"/></div>
       											<div class="tbox">
       												<s:submit type="button" value="Attach Documents" onclick="return uploadDocuments('%{tempId}');" cssClass="btn btn-sm btn-danger tooltipContent" data-content="Attach supporting documentse.g white book, drivers licence, letter of no claim record,  pictures of the vehicle" />
       											</div>
       							</div>
								<div class="col-xs-12 col-sm-12 col-md-6 col-lg-4">
									<div class="form-group">
    									<label><s:text name="label.master.status" /></label><br/>
										<s:radio list="#{'Y':'Activate','N':'Deactivate'}" name="status" id="status" value='%{status==null?"N":status}' />
									</div>
								</div>
							</div>
						</div>
						<br class="clear" />
						<div class="row">
							<div class="col-xs-12 col-sm-12 col-md-12" align="center">
								<input type="button" class="btn btn-sm btn-success" value="Submit" onclick="funSubmit('update','');">&nbsp;&nbsp;&nbsp;
								<input type="button" class="btn btn-sm btn-danger" value="Back" onclick="funSubmit('back','');" />
							</div>
						</div>	
						<s:hidden id="tempId" name="tempId"/>		
						<s:hidden name="mode" id="mode"></s:hidden>			
					</s:else>					
				</div>
			</div>
		</div>		
	</div>
</div>
</div>
<s:token />
</s:form>
<script type="text/javascript">
function funSubmit(mode,val){
	if(mode=="back"){
		document.smsEmail.action='viewSmsEmailMaster.action';		
	}else if(mode=="update"){
		document.smsEmail.action='insertSmsEmailMaster.action';
	}else if(mode=="add" || mode=="edit"){
		document.smsEmail.action='editSmsEmailMaster.action?mode='+mode+"&tempId="+val;
	}
	document.smsEmail.submit();
}

function uploadDocuments(tempId) {
	var URL ='${pageContext.request.contextPath}/emailAttachmentDoUpload.action?contentId='+tempId;
	return popUp(URL,'700','500');
}

var selectedproduct = [];    
$(document).ready(function() {    
    $('#userTypeArray').multiselect({ 
      includeSelectAllOption: true,
        enableFiltering:true,
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
  });
  	
   <s:if test='userTypeArray!=null && !"".equals(userTypeArray)'>	
 		var uwgrade='<s:property value="userTypeArray"/>';
		 var data=uwgrade.replace(/ /g,'');	
	   	 var dataArray=data.split(",");   	 
	   	$("#userTypeArray").val(dataArray);
		 $("#userTypeArray").multiselect("refresh");
	</s:if>
</script>
</body>
</html>   
