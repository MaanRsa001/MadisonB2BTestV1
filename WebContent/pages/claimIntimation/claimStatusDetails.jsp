<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/struts-tags" prefix="s"%>
<html>
<head>
<style type="text/css">
	#myDIV {
	    overflow-x:scroll;
	    margin: auto:
	}
           }
</style>
	<SCRIPT language=Javascript>
		function isNumberKey(evt) {
			var charCode = (evt.which) ? evt.which : event.keyCode;
			if (charCode != 46 && charCode > 31 
			&& (charCode < 48 || charCode > 57))
			return false;
			return true;
		}
	</SCRIPT>
	
	<script type="text/javascript">
		jQuery(function ($) {
			try {
				var data = $('#gridTable').dataTable( {
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
	 <style type="text/css">
	 .tableColWidth {
	 	min-width: 100px;
	 	max-width: 500px;
	 	width: 100px;
	 	white-space: nowrap;
	 }
	 </style>	
</head>
<body>
<s:form id="claimIntimation" name="claimIntimation" action="" theme="simple">
<div class=" wrapper vehDtl">
	<div class="Card_Parent ">
	    <div class="card card-5">
			<div class="container">

			<div class="panel panel-primary">
				<div class="panel-heading">
					<h3><s:text name="Claim Status Search"/></h3><hr>
				</div>			
				<div class="panel-body">
					<div class="row">
						<font color="green"><s:actionmessage cssStyle="list-style: none;"/></font>
						<s:if test="hasActionErrors()">
							<font color="red"><s:actionerror cssStyle="list-style: none;"/></font>
						</s:if>
					</div>
					<div class="panel-body">
						<!-- <div class="row">
							<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
								<div class="form-group">
					    			<div class ="" style="margin-left: 230px;height: 10px;font-size:18px"><b>Enter any one of the below to check Claim Status</b></div>
					    		</div>
							</div>
						</div> -->
						<div class="row">
							<div class="col-xs-12 col-sm-12 col-md-5 col-lg-5">
								<div class="form-group">
   									<label><b><s:text name="Policy Number"/><font color="red">*</font></b></label>
   									<s:textfield name="policyNo" id="policyNo" cssClass="form-control" ></s:textfield>
								</div>
							</div>
							<div class="col-xs-12 col-sm-12 col-md-2 col-lg-2">
								<div class="form-group">
					    			<div class ="" align="center" style="margin-top: 40px;height: 10px;font-size:18px"><b>(OR)</b></div>
					    		</div>
							</div>
							<div class="col-xs-12 col-sm-12 col-md-5 col-lg-5">
								<div class="form-group">
   									<label><b><s:text name="Claim Number"/><font color="red">*</font></b></label>
   									<s:textfield name="claimNo" id="claimNo" cssClass="form-control" ></s:textfield>
								</div>
							</div>
							
							<br class="clearFix">
							
							<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
								<div class="row">
									<div class="col-xs-12 col-sm-12 col-md-12" align="center">
										<button type="button" class="btn btn-sm btn-success" value="Submit" onclick="funSubmit();">Search</button>
									</div>
								</div>
							</div>
						</div>
						
					</div><br>
					<s:if test='mode == "claimSearchList"'>
						<s:if test='claimDetails.size()>1'>
							<div class=" wrapper vehDtl">
								<div class="panel-heading" align="left">
									<h3><s:text name="Claim Status Detail" /></h3><hr>
								</div><br>
								<div class="panel-body">
									<div class="row">
										<table class="table table-bordered table-hover" cellspacing="0" width="100%" id="tadaTable">
											<thead class="bluecolortable">
											<tr>
												<th><s:text name="Claim Number"/></th>
												<th><s:text name="Claim Status"/></th>
												<th><s:text name="Updated Date"/></th>
												<th><s:text name="Remarks"/></th>
											</tr>
											</thead>
											<tbody>
												<s:iterator value="claimDetails" var="list" status="stat">
													<tr>
														<td><s:property value="#list.ClaimNumber" /></td>
														<td style="color: #2f31f7"><b><s:property value="#list.BcsStatus" /></b></td>
														<td><s:property value="#list.BcsUpdDate" /></td>
														<td><s:property value="#list.BcsRemarks" /></td>
													</tr>
												</s:iterator>
											</tbody>
										</table>								
									</div>
								</div>
							</div>
						</s:if>
						<s:elseif test='claimDetails.size()==1'>
							<div class="col-xs-12 col-sm-12 col-md-12" align="center">
								<div class="panel panel-primary" style="width: 800px;">
									<div class="panel-heading" align="left">
										<h3><s:text name="Claim Status Detail" /></h3><hr>
									</div><br>
									<div class="body">
								    	<%-- <div class="row">
								    	 	<div class="boxContent" >
												<div class="col-xs-12 col-md-12">
													<table class="table"  style="margin-bottom: 0px;" align="center">
														<tbody>
															<s:iterator value="claimDetails" var="list" status="stat">
																<tr>
																	<th class="bg-orange" align="center"><s:text name="Claim Number" /></th>
																	<td><s:property value="#list.ClaimNumber"/></td>
																</tr>
																<tr>
																	<th class="bg-orange" align="center"><s:text name="Claim Status" /></th>
																	<td style="color: #2f31f7"><b><s:property value="#list.BcsStatus"/></b></td>
																</tr>
																<tr>
																	<th class="bg-orange" align="center"><s:text name="Updated Date" /></th>
																	<td><s:property value="#list.BcsUpdDate"/></td>
																</tr>
																<tr>
																	<th class="bg-orange" align="center"><s:text name="Remarks" /></th>
																	<td><s:property value="#list.BcsRemarks"/></td>
																</tr>
															</s:iterator>
														</tbody>
													</table>
												</div>
											</div>
										</div> --%>
										
										<div class="Card_Parent PolicyInformation">
								            <div class="card card-5" align="left">
								            	<s:iterator value="claimDetails" var="list" status="stat">
									                <div class="row">
									                    <div class="col-md-5 col-6">
									                        <label class="LabelHeading"><b><s:text name="Claim Number" /></b></label>
									                    </div>
									                    <div class="col-md-5 col-6">
									                        <label class="labelValues"><s:property value="#list.ClaimNumber"/></label>
									                    </div>
									                </div><hr>
									                <div class="row">
									                    <div class="col-md-5 col-6">
									                        <label class="LabelHeading"><b><s:text name="Claim Status" /></b></label>
									                    </div>
									                    <div class="col-md-5 col-6">
									                        <label class="labelValues" style="color: #2f31f7"><b><s:property value="#list.BcsStatus"/></b></label>
									                    </div>
									                </div><hr>
									                <div class="row">
									                    <div class="col-md-5 col-6">
									                        <label class="LabelHeading"><b><s:text name="Updated Date" /></b></label>
									                    </div>
									                    <div class="col-md-5 col-6">
									                        <label class="labelValues"><s:property value="#list.BcsUpdDate"/></label>
									                    </div>
									                </div><hr>
									                <div class="row">
									                    <div class="col-md-5 col-6">
									                        <label class="LabelHeading"><b><s:text name="Remarks" /></b></label>
									                    </div>
									                    <div class="col-md-5 col-6">
									                        <label class="labelValues"><s:property value="#list.BcsRemarks"/></label>
									                    </div>
									                </div>
								                </s:iterator>
							                </div>
						                </div>
									</div>
								</div>
							</div>
						</s:elseif>
						<s:else>
							<div class="col-xs-12 col-sm-12 col-md-12" align="center">
								<div class="panel panel-primary" style="width: 800px;">
									<div class="panel-heading" align="left">
										<h3><s:text name="Claim Status Detail" /></h3>
									</div><hr>
									<div class="body">
								    	<!-- <div class="row">
								    	 	<div class="boxContent" >
												<div class="col-xs-12 col-md-12">
													<table class="table"  style="margin-bottom: 0px;" align="center">
														<tbody>
															<tr>
																<td><b style="height: 10px;font-size:15px">Kindly coordinate with our nearest branch (or) call 4848 for more information</b></b></td>
															</tr>
														</tbody>
													</table>
												</div>
											</div>
										</div> -->
										
										<div class="Card_Parent PolicyInformation">
								            <div class="card card-5" align="left">
								            	<b style="height: 10px;font-size:15px">Kindly coordinate with our nearest branch (or) call 4848 for more information</b>
							                </div><br>
						                </div>
									</div>
								</div>
							</div>
						</s:else>
					</s:if>
				</div>
			</div>
		</div>
	</div>
</div>
</div>

<s:hidden name="reqForm"/>
</s:form>
</body>
<script type="text/Javascript" >

function funSubmit(){
var action='clmPolSearchClaimIntimation.action';
					document.claimIntimation.action=action;
					document.claimIntimation.submit();

}

$(".numericOnlyNew").on("keypress keyup blur",function (event) {  
	this.value = this.value.replace(/[^\d]/g,'');
    if (event.which > 57) {
       event.preventDefault();
    }
});
</script>
</html>