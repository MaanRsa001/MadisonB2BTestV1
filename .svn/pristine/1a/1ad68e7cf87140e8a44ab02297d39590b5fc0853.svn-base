<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/WEB-INF/displaytag.tld" prefix="display"%>
<html>
<head>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/dataTables/css/dataTables.responsive.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/dataTables/css/dataTables.jqueryui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/dataTables/css/dataTables.tableTools.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/dataTables/js/dataTables.responsive.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/dataTables/js/dataTables.tableTools.js"></script>

<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.13.18/js/bootstrap-select.min.js" integrity="sha512-yDlE7vpGDP7o2eftkCiPZ+yuUyEcaBwoJoIhdXv71KZWugFqEphIS3PU60lEkFaz8RxaVsMpSvQxMBaKVwA5xg==" crossorigin="anonymous"></script>
<!-- <link href="http://netdna.bootstrapcdn.com/twitter-bootstrap/2.3.1/css/bootstrap-combined.min.css" rel="stylesheet"> -->
<link rel="stylesheet" type="text/css" media="screen" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.9.3/css/bootstrap-select.min.css">
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<script src="http://netdna.bootstrapcdn.com/twitter-bootstrap/2.3.1/js/bootstrap.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.9.3/js/bootstrap-select.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>

	<script type="text/javascript">
		jQuery(function ($) {
			try {
				var data = $('#gridTableMake').dataTable( {
					"lengthMenu": [[10, 25, 50, -1], [10, 25, 50, "All"]],
					"order": [[ 0, "asc" ]],
					"columnDefs": [ {
			          "targets": 'no-sort',
			          "orderable": false
				    } ],
					responsive: true
				});
			} catch(err){}
		} );
	 </script>
	 	
</head>
<body>
<s:form id="document" name="document" action="" theme="simple">
<div class="container vehDtl">
<div class="Card_Parent ">
	<div class="card card-5">
		<div class="p-3">
			<div class="col-md-12">
				<div class="panel panel-primary">
					<div class="panel-heading">
						<h3><s:text name="label.master.document"/></h3>
						<hr>
						<s:if test='mode !="Add" && mode !="Edit"'>
							<div align="right">
								<input type="button" class="btn btn-sm btn-info" value="Add New" onclick="funSubmit('Add','');">
							</div>
							<br class="clear" />
						</s:if>
					</div>
					<s:if test='mode != "Add" && mode !="Edit"'>
						<div class="panel-body">
						<font color="green"><s:actionmessage cssStyle="list-style: none;"/></font>
							<div class="row">
								<table class="table table-bordered table-hover" id="gridTableMake" cellspacing="0" width="100%" >
									<thead class="bluecolortable">
									<tr>
										<th class="no-sort"><s:text name="label.master.sno"/></th>
										<th><s:text name="label.documentId"/></th>
										<th><s:text name="label.documentDescription"/></th>
										<th><s:text name="label.policyType"/></th>
										<th><s:text name="label.mandatoryStatus"/></th>
										<th><s:text name="label.status" /></th>
										<th><s:text name="label.remarks" /></th>
										<th><s:text name="label.master.edit"/></th>
									</tr>
									</thead>
									<tbody class="rowevencolor">
										<s:iterator value="documentList" var="list" status="stat">
											<tr>
												<td><s:property value="#stat.count" /></td>
												<td><s:property value="#list.DOCUMENT_ID" /></td>
												<td><s:property value="#list.DOCUMENT_DESC" /></td>
												<td><s:property value="#list.POLICY_TYPE" /></td>
												<td><s:property value="#list.MANDATORY_STATUS" /></td>
												<td><s:property value="#list.STATUS" /></td>
												<td><s:property value="#list.REMARKS" /></td>
												<td style="text-align: center;">
													<input type="button" class="btn btn-warning btn-sm" onclick="funSubmit('Edit','<s:property value="#list.DOCUMENT_ID" />');" value="Modify" />
												</td>
											</tr>
										</s:iterator>
									</tbody>
								</table>								
							</div>
						</div>
					</s:if>
					<s:else>
						<div class="panel-body">
							<s:if test="hasActionErrors()">
								<font color="red"><s:actionerror cssStyle="list-style: none;"/></font>
							</s:if>
							<div class="row">									
								<s:hidden name="documentId" id="documentId"></s:hidden>
							<div class="col-xs-12 col-sm-6 col-md-6 ">
								<label><s:text name="label.policyType"/> <font color="red">*</font></label>
								<div class="form-group">																
									<s:select name="docpolicyType" id="docpolicyType" list="policyTypeCheckBox" listKey="POLICY_SUBTYPE_ID" listValue="POLICYTYPE_DESC_ENGLISH" headerKey="" multiple="true" class="selectpicker" />
								</div>
							</div>
							<div class="col-xs-12 col-sm-6 col-md-6 ">
								<label><s:text name="label.documentApplicableOn"/> <font color="red">*</font></label>
								<div class="form-group">							
									<s:select name="documentApplicable" id="documentApplicable" list="documentdDrpdwn"  listKey="ITEM_CODE" listValue="ITEM_VALUE" headerKey="" headerValue="-----Select-----" cssClass="form-control" />                                
								</div>
							</div>
							<div class="col-xs-12 col-sm-6 col-md-6">
								<div class="form-group">
	    						<label><s:text name="label.documentDisplayOrder"/><font color="red">*</font></label>
	    							<s:textfield name="displayOrder" id="displayOrder" cssClass="form-control numericOnly" maxlength="100"></s:textfield>
								</div>
							</div>
							<div class="col-xs-12 col-sm-6 col-md-6">
								<div class="form-group">
	    						<label><s:text name="label.documentDescription"/><font color="red">*</font></label>
	    							<s:textfield name="documentDescription" cssClass="form-control" maxlength="100"></s:textfield>
								</div>
							</div>
							<div class="col-xs-12 col-sm-6 col-md-6">
								<div class="form-group">
	    							<label><s:text name="label.master.remarks"/></label>
	    							<s:textfield name="remarks" cssClass="form-control" maxlength="100"></s:textfield>
								</div>
							</div>
							<div class="col-xs-12 col-sm-6 col-md-6">
								<div class="form-group">
								<label><s:text name="label.mandatoryStatus" /><font color="red">*</font><br/></label><br/>
									<s:radio list="#{'Y':'Activate','N':'Deactivate'}" name="mandatoryStatus" id="mandatoryStatus" value='%{mandatoryStatus==null?"N":mandatoryStatus}' />
								</div>
							</div><br/>
							<div class="col-xs-12 col-sm-6 col-md-6">
								<div class="form-group">
								<label><s:text name="label.master.status" /><font color="red">*</font><br/></label><br/>
									<s:radio list="#{'Y':'Activate','N':'Deactivate'}" name="status" id="status" value='%{status==null?"N":status}' />
								</div>
							</div>
							</div>
						</div>
						<br class="clear" />
						<div class="row">
							<div class="col-xs-12 col-sm-12 col-md-12" align="center">
								<input type="button" class="btn btn-sm btn-success" value="Submit" onclick="funSubmit('Insert','');">&nbsp;&nbsp;&nbsp;
								<input type="button" class="btn btn-sm btn-danger" value="Back" onclick="funBack();" />
							</div>
						</div>						
					</s:else>					
				</div>
			</div>
		</div>		
	</div>
</div>
</div>
</s:form>
<script type="text/javascript">
$(document).ready(function(e) {
	  $('.selectpicker').selectpicker();
	});

	function funSubmit(mode,val){
		if(mode == "Add"){
		document.document.action='getDocumentEditMotorAdminNew.action?mode='+mode;
		}
	  else if(mode == "Edit"){
		 document.document.action='getDocumentEditMotorAdminNew.action?mode='+mode+'&documentId='+val;
		 }
	  else if(mode == "Insert"){
	     document.document.action='getDocumentInsertMotorAdminNew.action?mode=<s:property value="mode"/>';
	  }
	  else{
	    alert("No Action for Submit");
	    }
		document.document.submit();
	}
	function funBack(){
		document.document.action='getDocumentMotorAdminNew.action?mode=list';
		document.document.submit();
	}
	
var selectedproduct = [];    
$(document).ready(function() {    
    $('#docpolicyType').multiselect({ 
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
  
  <s:if test='docpolicyType!=null && !"".equals(docpolicyType)'>	
 		var uwgrade='<s:property value="docpolicyType"/>';
		 var data=uwgrade.replace(/ /g,'');	
	   	 var dataArray=data.split(",");   	 
	   	$("#docpolicyType").val(dataArray);
		 $("#docpolicyType").multiselect("refresh");
	</s:if>
	/*$( document ).ready(function() {
		var value = '<s:property value="docpolicyType"/>';
		alert(value);
		var substr = value.Split(',');
		if(substr.length > 0){
			for (var i = 0; i < substr.length; ++i) {
				var val = substr[i].prop('updownselect', true);
				}
			}
		});*/
		
	/*$( document ).ready(function() {
	var value = '<s:property value="docpolicyType"/>';
	var data=value.replace(/ /g,'');
	var substr = data.split(',');
	if(substr.length > 0){
			for (var i = 0; i < substr.length; ++i) {
				var val = 	$('#checkbox_'+substr[i]).val();
				console.log(val);
				$('#checkbox_'+substr[i]).prop('checked', true); 
			}
		}
		
	});*/
   /* $(document).ready(function() { 
       var value = '<s:property value="docpolicyType"/>';
       var substr = value.Split(',');
       if(substr.length > 0){
       		for (var i = 0; i < substr.length; ++i) {
	   			 $(substr[i]).multiselect({
	      		includeSelectAllOption: true,
	      		firstItemChecksAll: true,
	        	enableFiltering:true 
	        	});
	        	}
	        }
	});*/
	/*$(document).ready(function() {$("#docpolicyType").dropdownchecklist({ firstItemChecksAll: true, explicitClose: '...close' });});*/
	/*$(document).ready(function() {
	$("#docpolicyType").dropdownchecklist({
	  firstItemChecksAll: true,
	  maxDropHeight: 100 });
	  });*/
</script> 
</body>
</html>   
 