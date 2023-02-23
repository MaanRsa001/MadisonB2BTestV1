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
	</head>
	<body>
	<s:form id="rateConfig" name="rateConfig" action="" theme="simple">
<div class="container vehDtl">
	<div class="Card_Parent ">
		<div class="card card-5">
			<div class="p-3">
				<div class="col-md-12">
					<div class="panel panel-primary">
						<div class="panel-heading">
							<h3><s:text name="lable.master.subPolicytype.master"/></h3>
							<hr>
						</div>
						<div class="panel-body">
							<div class="row">
								<div class="panel panel-primary">
									<div class="panel-heading">
										<s:iterator value="modalHeaderList" var="list"  >
											<div class="row">
												<div class="col-xs-12 col-sm-12 col-md-6 col-lg-4">
													<s:text name="label.master.policyType" /> &nbsp;:&nbsp; <b> <s:property value="%{POLICYTYPE_DESC_ENGLISH}"/></b>
												</div>
												<div class="col-xs-12 col-sm-12 col-md-6 col-lg-4">
													<s:text name="label.master.vehicleType" /> &nbsp;:&nbsp; <b>  <s:property value="%{VEHICLETYPE_DESC}"/> </b>
												</div>
												<div class="col-xs-12 col-sm-12 col-md-6 col-lg-4">
													<s:text name="label.master.bodyType" /> &nbsp;:&nbsp; <b> <s:property value="%{TYPE_OF_BODY_NAME}"/> </b>
												</div>
											</div>
										</s:iterator>									
									</div>
								</div>
								<s:if test="hasActionErrors()">
									<font color="red"><s:actionerror cssStyle="list-style: none;"/></font>
								</s:if>
								<div class="table-responsive" style="padding: 0px 12px 0px 12px; ">
									<table class="table table-bordered table-hover" id="gridTableMake" cellspacing="0" width="100%" >
										<thead class="bluecolortable">
	  										<tr>
		  										<th></th>
		  										<th align="center"><s:text name="label.start"></s:text> </th>
		  										<th align="center"><s:text name="label.end"></s:text></th>
		  										<th align="center"><s:text name="label.status"/></th>
	  										</tr>
	  									</thead>
	  									<tbody class="form-group">
	  										<tr>
	  											<td width="450">
	  												<label><s:text name="Vehicle Age" /> <font color="red">*</font> </label>
	  											</td>
	  											<td width="20%">
  													<s:textfield name="vehicleStartAge" id="vehicleStartAge" maxlength="2" cssClass="form-control numericOnly" onchange="vehicleAgeAjax('checkVehicleAge')" ></s:textfield>
	  											</td>
	  											<td width="20%">
  													<s:textfield name="vehicleEndAge" id="vehicleEndAge" maxlength="2" cssClass="form-control numericOnly" onchange="vehicleAgeAjax('checkVehicleAge');"></s:textfield>
	  											</td>
	  											<td width="15%" align="center">
	  												<div class="col-xs-6">
	  													<button class="btn btn-info" style="border-radius:0px;" type="button"  onclick="$('#vehicleAgeInfo').modal('show');">
															<span class="glyphicon glyphicon-eye-open"> </span>
														</button>
	  												</div>
	  												<div class="col-xs-6" id="checkVehicleAge" >
	  												</div>
	  												<div id="vehicleAgeInfo" class="modal fade" role="dialog">
														<div class="modal-dialog modal-md">
															<div class="modal-content">
																<div class="modal-header">
																	<button type="button" class="close" data-dismiss="modal">&times;</button>
																	<h4 class="modal-title">Vehicle Age</h4>
																</div>
																<div class="modal-body">
																	<s:iterator value="modalHeaderList" var="list"  >
																		<div class="row">
																			<div class="col-xs-12 col-sm-12 col-md-6 col-lg-4">
																				<s:text name="label.master.policyType" /> &nbsp;:&nbsp; <b> <s:property value="%{POLICYTYPE_DESC_ENGLISH}"/></b>
																			</div>
																			<div class="col-xs-12 col-sm-12 col-md-6 col-lg-4">
																				<s:text name="label.master.vehicleType" /> &nbsp;:&nbsp; <b>  <s:property value="%{VEHICLETYPE_DESC}"/> </b>
																			</div>
																			<div class="col-xs-12 col-sm-12 col-md-6 col-lg-4">
																				<s:text name="label.master.bodyType" /> &nbsp;:&nbsp; <b> <s:property value="%{TYPE_OF_BODY_NAME}"/> </b>
																			</div>
																		</div>
																	</s:iterator>
																	<div class="row">
																		<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 table-responsive">
																			<table class="table table-bordered" width="100%">
																				<thead>
																				<tr>
																					<th width=25%" align="center"><s:text name="label.master.vehicle.start.age"></s:text> </th>
																					<th width="25%" align="center"><s:text name="label.master.vehicle.end.age"></s:text> </th>
																					<th width="25%" align="center"><s:text name="label.vehicle.count"></s:text> </th>
																					<th width="25%" align="center"><s:text name="label.status"></s:text> </th>
																				</tr>
																				</thead>
																				<tbody>
																					<s:iterator value="subPolicyList" var="list">
																						<tr>
																							<td align="center"><s:property value="%{AGE_OF_VEHICLE_START}"/></td>
																							<td align="center"><s:property value="%{AGE_OF_VEHICLE_END}"/></td>
																							<td align="center"><s:property value="%{VEHICLE_COUNT}"/></td>
																							<td align="center"><s:property value="%{STATUS}"/></td>
																						</tr>
																					</s:iterator>
																				</tbody>
																			</table>
																		</div>
																	</div>
																</div>
															</div>
														</div>
	  												</div>
												</td>
											</tr>
											<%--<tr>
												<td width="450">
	  												<label><s:text name="label.master.claim"/><font color="red">*</font></label>
	  											</td>
	  											<td width="20%">
  													<s:textfield name="claimStart" cssClass="form-control numericOnly" id="claimStart" maxlength="2" onchange="claimAjax('checkClaim')"></s:textfield>
	  											</td>
	  											<td width="20%">
  													<s:textfield name="claimEnd" cssClass="form-control numericOnly" id="claimEnd" maxlength="2"  onchange="claimAjax('checkClaim')"></s:textfield>
	  											</td>
	  											<td width="15%" align="center">
	  												<div class="col-xs-6">
	  													<button class="btn btn-info" style="border-radius:0px;" type="button"  onclick="$('#claimInfo').modal('show');">
															<span class="glyphicon glyphicon-eye-open"> </span>
														</button>
	  												</div>
	  												<div class="col-xs-6" id="checkClaim" >
	  												
	  												</div>
													<div id="claimInfo" class="modal fade" role="dialog">
														<div class="modal-dialog modal-md">
															<div class="modal-content">
																<div class="modal-header">
																	<button type="button" class="close" data-dismiss="modal">&times;</button>
																	<h4 class="modal-title">Claim Status</h4>
																</div>
																<div class="modal-body">
																	<s:iterator value="modalHeaderList" var="list"  >
																		<div class="row">
																			<div class="col-xs-12 col-sm-12 col-md-6 col-lg-4">
																				<s:text name="label.master.policyType" /> &nbsp;:&nbsp; <b> <s:property value="%{POLICYTYPE_DESC_ENGLISH}"/></b>
																			</div>
																			<div class="col-xs-12 col-sm-12 col-md-6 col-lg-4">
																				<s:text name="label.master.vehicleType" /> &nbsp;:&nbsp; <b>  <s:property value="%{VEHICLETYPE_DESC}"/> </b>
																			</div>
																			<div class="col-xs-12 col-sm-12 col-md-6 col-lg-4">
																				<s:text name="label.master.bodyType" /> &nbsp;:&nbsp; <b> <s:property value="%{TYPE_OF_BODY_NAME}"/> </b>
																			</div>
																		</div>
																	</s:iterator>
																	<div class="row">
																		<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 table-responsive">
																			<table class="table table-bordered" width="100%">
																				<thead>
																				<tr>
																					<th width="50%" align="center"><s:text name="label.master.vehicle.start.age"></s:text> </th>
																					<th width="50%" align="center"><s:text name="label.master.vehicle.end.age"></s:text> </th>
																					<th width="50%" align="center"><s:text name="label.master.claim.start"></s:text> </th>
																					<th width="50%" align="center"><s:text name="label.master.cliam.end"></s:text> </th>
																				</tr>
																				</thead>
																				<tbody>
																					<s:iterator value="subPolicyList" var="list">
																						<tr>
																							<td align="center"><s:property value="%{AGE_OF_VEHICLE_START}"/></td>
																							<td align="center"><s:property value="%{AGE_OF_VEHICLE_END}"/></td>
																							<td align="center"><s:property value="%{CLAIMS_START}"/></td>
																							<td align="center"><s:property value="%{CLAIMS_END}"/></td>
																						</tr>
																					</s:iterator>
																				</tbody>
																			</table>
																		</div>
																	</div>
																</div>
															</div>
														</div>
													</div>
												</td>
	  										</tr>
	  									--%></tbody>
	  								</table>
	  							</div>
								<br/>
								<%--<div class="col-xs-12 col-sm-6 col-md-6 form-group">
									<label><s:text name="label.master.country.applicable" /><font color="red" style="">*</font></label>
									<div class="input-group">
										<s:textfield name="applicableCountry" id="applicableCountry" placeholder="Choose Vehicle Applicable Country" cssStyle="readonly:true;" cssClass="form-control"></s:textfield>
										<span class="input-group-btn">
											<button class="btn btn-info" style="border-radius:0px;" type="button" id="vehicleApplicableCountry"  onclick="$('#vehicleApplicableCountryModal').modal('show');">Choose</button>
										</span>
									</div>
								</div>
								<div class="col-xs-12 col-sm-6 col-md-6 form-group">
									<label><s:text name="label.master.city.applicable" /> <font color="red">*</font></label>
									<div class="input-group">
										<s:textfield name="applicableCity" id="applicableCity" placeholder="Choose Vehicle Applicable City" cssClass="form-control"></s:textfield>
										<span class="input-group-btn">
											<button class="btn btn-info" style="border-radius:0px;" type="button" id="vehicleApplicableCity"  onclick="$('#vehicleApplicableCityModal').modal('show');">Choose</button>
										</span>
									</div>
								</div>
								--%>
								<div class="col-xs-12 col-sm-6 col-md-6">
									<div class="form-group">
    									<label><s:text name="lable.master.effectiveDate" /><font color="red">*</font></label>
    									<s:textfield name="effectiveDate" cssClass="form-control datepicker" id="effectiveDate"></s:textfield>
									</div>
								</div>
								<div class="col-xs-12 col-sm-6 col-md-6">
									<div class="form-group">
    									<label><s:text name="label.vehicle.count"/><font color="red">*</font></label>
    									<s:textfield name="vehicleCount" cssClass="form-control numericOnly" id="vehicleCount" onchange="vehicleAgeAjax('checkVehicleAge');" maxlength="150"></s:textfield>
									</div>
								</div>
								<div class="col-xs-12 col-sm-6 col-md-6">
									<div class="form-group">
    									<label><s:text name="label.master.remarks"/></label>
    									<s:textfield name="remarks" cssClass="form-control" maxlength="150"></s:textfield>
									</div>
								</div>
								<div class="col-xs-12 col-sm-6 col-md-6">
									<div class="form-group">
										<label><s:text name="label.master.status" /><font color="red">*</font></label><br/>
										<s:radio list="#{'Y':'Activate','N':'Deactivate'}" name="status" id="status" value='%{status==null?"N":status}' />
									</div>
								</div>
								<%--<div class="col-xs-12 col-sm-6 col-md-6">
									<div class="form-group">
    									<label><s:text name="label.master.description"/></label>
    									<s:textarea name="description" cssClass="form-control" rows="3" maxlength="1000"></s:textarea>
									</div>
								</div>
								--%>
								<div class="col-xs-12 col-sm-12 col-md-12">
									<div class="form-group table-responsive">
									
									<table class="table table-bordered table-hover" id="PolicyDesc" cellspacing="0" width="100%" >
										<thead class="bluecolortable">
<!--     										<thead style="background: ;"> -->
    											<tr>
													<td>Description</td>
	    											<td align="center">
	    												<input type="button" id="addmore" class="btn btn-sm btn-primary" name="addmore" value="Add More" onclick="addNewRow();" />
	    											</td>    											
    											</tr>
    										</thead>
    										<tbody>
    											<s:if test="descriptionList.size()>0">
					        						<s:iterator value="descriptionList" var="trList" status="stat" >
						        						<tr id='new_<s:property value="#stat.index"/>'>
						        							<td align="right" >
						        								<s:textfield name="descriptionList[%{#stat.count-1}]" id="descriptionList[%{#stat.count-1}]" maxlength="200" cssClass="inputBox form-control"/>
						        							</td>
						        							<td align="center">
																<input type="button" class="btn btn-sm btn-danger" onclick="deleteRow('<s:property value="#stat.index"/>',this);" value="Remove"/>
						        							</td>
						        						</tr>
					        						</s:iterator>
					        					</s:if>
					        					<s:else>
						        					<tr id="new_0">
						        						<td align="center" class="form-group">
					        								<s:textfield name="descriptionList[0]" id="descriptionList[0]" maxlength="200" cssClass="inputBox form-control"/>
					        							</td>
					        							<td align="center">
															<input type="button" name="deleteRow[0]" id="chkBox0" class="btn btn-sm btn-danger" onclick="deleteRow('0',this);" value="Remove"/>
					        							</td>
					        						</tr>
					        					</s:else>
    										</tbody>
    									</table>
    									<%--<label><s:text name="label.master.description"/></label>
    									<s:textarea name="description" cssClass="form-control" rows="3" maxlength="1000"></s:textarea>
									--%></div>
								</div>
								<div class="col-xs-12 col-sm-12 col-md-12" align="center">
									<s:submit cssClass="btn btn-sm btn-success" value="Submit" onclick="funSubmit();"></s:submit> &nbsp;&nbsp;&nbsp;
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
			<s:hidden name="policyType" id="policyType"></s:hidden>
			<s:hidden name="vehicleType" id="vehicleType"></s:hidden>
			<s:hidden name="bodyType" id="bodyType"></s:hidden>
			<s:hidden name="subRateId" id="subRateId"></s:hidden>
			<s:token/>
	</s:form>
	<script type="text/javascript">
	function funBack(){
		document.rateConfig.action='subPolicyMotorAdminNew.action';
		document.rateConfig.submit();
	}
	function funSubmit(){
		document.rateConfig.action='insSubPolicyTypeMotorAdminNew.action?mode=<s:property value="mode"/>';
		document.rateConfig.submit();
	}
	function vehicleAgeAjax(id){
		if($("#vehicleStartAge").val().length != 0 && $("#vehicleEndAge").val().length != 0 && '<s:property value="mode"/>' == 'editSubPolicy'){
				ajax1(id);
		}
	}
	function ajax1(id){
		/*var rateConfig = document.getElementById("rateConfig");
  		var fd = new FormData(rateConfig );*/
  		var url='${pageContext.request.contextPath}/ajaxMotorAdminNew.action?reqFrom='+id+'&vehicleStartAge ='+$("#vehicleStartAge").val()+'&vehicleEndAge='+$("#vehicleEndAge").val()+'&mode=<s:property value="mode"/>'+'&vehicleCount='+$("#vehicleCount").val()+'&policyType='+$("#policyType").val()+'&vehicleType='+$("#vehicleType").val()+'&bodyType='+$("#bodyType").val()+'&subRateId='+$("#subRateId").val();
  		alert(url);
    	$.ajax({
	        /*url: '${pageContext.request.contextPath}/ajaxMotorAdminNew.action?reqFrom='+id,
	        data: fd,*/
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
	function addNewRow(){
		var table = document.getElementById('PolicyDesc');
        var rowCount = table.rows.length;
        var row = table.insertRow(rowCount);
        row.id = "new_"+(rowCount-1);
        row.className = "form-group";
        
 		cell = row.insertCell(0);
        cell.setAttribute("style", "text-align: center;");
 		var element = document.createElement("input");
        element.type = "text";
        element.style.width = "100%";
        element.name = "descriptionList["+(rowCount-1)+"]";
        element.id = "descriptionList["+(rowCount-1)+"]";  
        element.setAttribute("maxlength",'200');    
        element.className = "inputBox form-control";      
        cell.appendChild(element);
        
        cell = row.insertCell(1);
        cell.setAttribute("style", "text-align: center;");
 		element = document.createElement("input");
        element.type = "button";
        element.className = "btn btn-sm btn-danger";
        element.id = "chkBox"+(rowCount-1)+"";
        element.value="Remove"; 
        element.onclick =  function () {deleteRow(rowCount-1,this)};
        cell.appendChild(element);
	}
	function deleteRow(sNo,obj){
    	var decision = confirm("Row will be deleted. Do You Want to continue? ","");
		if (decision==true)
			{
			$('#new_'+sNo).html("");
	    }else
	    {
	    	document.getElementById("chkBox"+sNo).checked=false;
	    }
	}
</script>
</body>
</html>   
