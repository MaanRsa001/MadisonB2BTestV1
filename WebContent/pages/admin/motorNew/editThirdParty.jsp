<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/struts-tags" prefix="s"%>
<html>
	<head>
	</head>
	<body>
	<s:form id="thirdPartyRate" name="thirdPartyRate" action="" theme="simple">
<div class="container vehDtl">
<div class="Card_Parent ">
<div class="card card-5">
<div class="p-3">
				<div class="col-xs-12">
					<div class="panel panel-primary">
						<div class="panel-heading">
							<h3><s:text name="label.master.third.party.configuration"/></h3>
							<hr>
						</div>
						<div class="panel-body">
						<s:if test="hasActionErrors()">
							<font color="red"><s:actionerror cssStyle="list-style: none;"/></font>
						</s:if>
							<div class="row">
								<div class="panel panel-primary">
									<div class="panel-heading">
										<s:iterator value="modalHeaderList" var="list"  >
											<div class="row">
												<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
													<s:text name="label.master.vehicleType" /> &nbsp;:&nbsp; <b>  <s:property value="%{VEHICLETYPE_DESC}"/> </b>
												</div>
												<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
													<s:text name="label.master.bodyType" /> &nbsp;:&nbsp; <b> <s:property value="%{TYPE_OF_BODY_NAME}"/> </b>
												</div>
											</div>
										</s:iterator>									
									</div>
								</div>
								<table class="table table-bordered table-hover" id="gridTableMake" cellspacing="0" width="100%" >
	  									<thead class="bluecolortable">
	  										<tr>
		  										<th></th>
		  										<th align="center"><s:text name="label.start"></s:text> </th>
		  										<th align="center"><s:text name="label.end"></s:text></th>
		  										<th align="center"><s:text name="label.status"/></th>
	  										</tr>
	  									</thead>
	  									<tbody class="rowevencolor form-group">
	  										<tr>
	  											<td width="40%">
	  												<label><s:text name="label.seating.cylinder"/><font color="red">*</font></label>
	  											</td>
	  											<td width="20%">
  													<s:textfield name="seatingCylinderStart" id="seatingCylinderStart" cssClass="form-control numericOnly" maxlength="5" ></s:textfield>
	  											</td>
	  											<td width="20%">
  													<s:textfield name="seatingCylinderEnd" id="seatingCylinderEnd" cssClass="form-control numericOnly" maxlength="5" onblur="callAjax('checkSeatingCylinder');"></s:textfield>
	  											</td>
	  											<td width="20%" align="center">
	  												<div class="col-xs-6">
	  													<button class="btn btn-info" style="border-radius:0px;" type="button" onclick="$('#seatingCylinderInfo').modal('show');">
															<span class="glyphicon glyphicon-eye-open"> </span>
														</button>
	  												</div>
	  												<div class="col-xs-6" id="checkSeatingCylinder" >
	  												
	  												</div>
													<div id="seatingCylinderInfo" class="modal fade" role="dialog">
														<div class="modal-dialog modal-md">
															<div class="modal-content">
																<div class="modal-header">
																	<button type="button" class="close" data-dismiss="modal">&times;</button>
																	<h4 class="modal-title"><s:text name="label.seating.cylinder" /> </h4>
																</div>
																<div class="modal-body">
																	<s:iterator value="modalHeaderList" var="list"  >
																		<div class="row">
																			<div class="col-xs-12 col-sm-12 col-md-6 col-lg-4">
																				<s:text name="label.master.vehicleType" /> &nbsp;:&nbsp; <b>  <s:property value="%{VEHICLETYPE_DESC}"/> </b>
																			</div>
																			<div class="col-xs-12 col-sm-12 col-md-6 col-lg-4">
																				<s:text name="label.master.bodyType" /> &nbsp;:&nbsp; <b> <s:property value="%{TYPE_OF_BODY_NAME}"/> </b>
																			</div>
																		</div>
																	</s:iterator>
																	<s:if test="thirdPartyList.size() > 0">
																		<div class="row">
																			<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 table-responsive">
																				<table class="table table-bordered">
		  																			<thead style="background: #f5f5f5">
																						<tr>
																							<th><s:text name="label.seating.cylinder.start"/></th>
																							<th><s:text name="label.seating.cylinder.end"/></th>		
																							<th><s:text name="label.third.party.rate"/></th>
																							<th><s:text name="label.master.core.app.code"/></th>
																							<th><s:text name="lable.master.effectiveDate"/></th>
																							<th><s:text name="label.master.status"/></th>
																						</tr>
																					</thead>
																					<tbody>
																						<s:iterator value="thirdPartyList" var="list" status="stat">
																							<tr>
																								<td><s:property value="#list.SEATING_CYLINDER_START" /></td>
																								<td><s:property value="#list.SEATING_CYLINDER_END" /></td>
																								<td><s:property value="#list.THIRD_PARTY_RATE" /></td>
																								<td><s:property value="#list.COREAPP_CODE" /></td>
																								<td><s:property value="#list.EFFECTIVE_DATE" /></td>
																								<td><s:property value="#list.STATUS" /></td>
																							</tr>
																						</s:iterator>
																					</tbody>
																				</table>
																			</div>
																		</div>
																		</s:if> 
																		<s:else>
																			<font color="green"><s:text name="label.no.records.available"></s:text></font>
																		</s:else>
																	</div>
																</div>
															</div>
														</div>
												</td>
	  										</tr>
	  									</tbody>
	  								</table>
								<%--<div class="col-xs-12 col-sm-6 col-md-6">
									<label><s:text name="label.master.vehicleType" /><font color="red">*</font></label>
									<s:select name="vehicleType" id="vehicleType" list="vehicleTypeList" headerKey="" headerValue="---Select---"  listKey="CODE" listValue="CODEDESC" cssClass="form-control inputBoxS"/>
								</div>
								--%>
								<div class="col-xs-12 col-sm-6 col-md-6">
									<label><s:text name="label.userName" /><font color="red">*</font></label>
									<s:select name="userNameLogin" id="userNameLogin" list="userNameLoginList" headerKey="" headerValue="---Select---"  listKey="AGENCY_CODE" listValue="USERNAME" cssClass="form-control inputBoxS" disabled='%{(mode =="edit")?"true":"false"}'/>
								</div>
								<div class="col-xs-12 col-sm-6 col-md-6">
									<div class="form-group">
    									<label><s:text name="label.third.party.rate"/><font color="red">*</font></label>
    									<s:textfield name="thirdPartyRate" id="thirdPartyRate" cssClass="form-control decimalOnly" maxlength="12"></s:textfield>
									</div>
								</div>
								<div class="col-xs-12 col-sm-6 col-md-6">
									<div class="form-group">
    									<label><s:text name="label.master.core.app.code"/><font color="red">*</font></label>
    									<s:textfield name="coreAppCode" cssClass="form-control" maxlength="150"></s:textfield>
									</div>
								</div>
								<div class="col-xs-12 col-sm-6 col-md-6">
									<div class="form-group">
    									<label><s:text name="lable.master.effectiveDate" /><font color="red">*</font></label>
    									<s:textfield name="effectiveDate" cssClass="form-control datepicker" id="effectiveDate"></s:textfield>
									</div>
								</div>
								
								<%--<div class="col-xs-12 col-sm-6 col-md-6">
									<div class="form-group">
    									<label><s:text name="label.master.vehicle.country" /><font color="red">*</font></label>
    									<s:select name="locationId" id="locationId" list="countryList" headerKey="" headerValue="---Select---"  listKey="CODE" listValue="CODEDESC" cssClass="form-control inputBoxS"/>
									</div>
								</div>
								--%><div class="col-xs-12 col-sm-6 col-md-6">
									<div class="form-group">
										<label><s:text name="label.master.status" /><font color="red">*</font></label><br/>
										<s:radio list="#{'Y':'Activate','N':'Deactivate'}" name="status" id="status" value='%{status==null?"N":status}' />
									</div>
								</div>
								<div class="col-xs-12 col-sm-12 col-md-12" align="center">
									<s:submit cssClass="btn btn-sm btn-success" value="Submit" onclick="disableForm(this.form,false,'');funSubmit();"></s:submit> &nbsp;&nbsp;&nbsp;
									<s:submit cssClass="btn btn-sm btn-danger" value="Back" onclick="funBack();"></s:submit>
								</div>
							</div> <br/>
						</div>
					</div>
				</div>
			</div> <br/>			
		</div>
	</div>
</div>
			<s:hidden name="thirdPartyId" id="thirdPartyId"></s:hidden>
			<s:hidden name="mode" id="mode"></s:hidden>
			<s:hidden name="bodyType" id="bodyType"></s:hidden>
			<s:hidden name="vehicleType" id="vehicleType"></s:hidden>
	</s:form>
<script type="text/javascript">
	function funBack(){
		document.thirdPartyRate.action='thirdPartyMotorAdminNew.action';
		document.thirdPartyRate.submit();
	}
	function funSubmit(){
		document.thirdPartyRate.action='insthirdPartyMotorAdminNew.action';
		document.thirdPartyRate.submit();
	}
	function callAjax(id){
		if(id=='checkSeatingCylinder'){
			if($("#seatingCylinderStart").val().length != 0 && $("#seatingCylinderEnd").val().length != 0 && '<s:property value="mode"/>' =='edit'){
				ajax1(id);
			}
		}
	}
	function ajax1(id){
		var url = '${pageContext.request.contextPath}/ajaxMotorAdminNew.action?reqFrom='+id+'&seatingCylinderStart='+$("#seatingCylinderStart").val()+'&seatingCylinderEnd='+$("#seatingCylinderEnd").val()+'&vehicleType='+$("#vehicleType").val()+'&bodyType='+$("#bodyType").val()+'&thirdPartyId='+$("#thirdPartyId").val()+'&mode='+$("#mode").val();
		$.ajax({
	        url: url,
	        cache: false,
	        processData: false,
	        contentType: false,
	        type: 'POST',
	        success: function (dataofconfirm) {
	        	document.getElementById(id).innerHTML=dataofconfirm;
	        	$('#'+id+'Modal').modal('show');
	        }
	    });
	}
	
</script>
</body>
</html>   
