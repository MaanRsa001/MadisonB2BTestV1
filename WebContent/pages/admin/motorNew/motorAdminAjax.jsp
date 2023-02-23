<%@ page isELIgnored="false" %>
<%@ taglib uri="/struts-tags" prefix="s"%>
<s:if test='"resCodeAjaxList".equals(reqFrom1)'>
	<label><s:text name="label.res.code"/></label><font color="red">*</font>
	<s:select name="resCode" id="resCodeList" list="resCodeList" headerKey="" headerValue="---Select---"  listKey="RES_CODE" listValue="RES_DESCRIPTION" cssClass="form-control" />
</s:if>
<s:if test='"bodyType".equals(reqFrom)'>
	<s:text name="label.master.bodyType" /> <br/>
	<s:select name="bodyType" id="bodyTypeList" list="bodyTypeList" headerKey="" headerValue="---Select---"  listKey="TYPE_OF_BODY_ID" listValue="TYPE_OF_BODY_NAME" cssClass="form-control" onchange="hideSupPolicy();"/>
</s:if>
<s:elseif test='"checkVehicleAge".equals(reqFrom)'>
	<s:if test='validationList==null && !hasActionErrors()'>
		<button class="btn btn-success" style="border-radius:0px;" type="button" data-toggle="modal">
			<span class="glyphicon glyphicon-ok"></span>
		</button>
	</s:if>
	<s:else>
		<button class="btn btn-danger" style="border-radius:0px;" type="button" onclick="$('#checkVehicleAgeModal').modal('show');">
			<span class="glyphicon glyphicon-remove"></span>
		</button>
		<div id="checkVehicleAgeModal" class="modal fade" role="dialog">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Vehicle Age Status</h4>
				</div>
				<div class="modal-body table-responsive">
					<s:if test='validationList==null && !hasActionErrors()'>
						Not Exist
					</s:if>
					<s:elseif test='hasActionErrors()'>
						<div style="color: red;">
							<s:actionerror cssStyle="list-style: none;"/>
						</div>
					</s:elseif>
					<s:else>
						<font color="Red">Already Available Vehicle Age Combinations </font>
						<table class="table table-bordered" width="100%" cellspacing="0">
							<thead>
								<tr>
									<td><s:text name="label.master.sub.rate.id"/></td>
									<td><s:text name="label.master.vehicle.start.age"/></td>		
									<td><s:text name="label.master.vehicle.end.age"/></td>
									<%--<td><s:text name="label.master.country.applicable"/></td>
									<td><s:text name="label.master.city.applicable"/></td>
									<td><s:text name="label.master.claim.start"/></td>
									<td><s:text name="label.master.claim.end"/></td>
									--%><td><s:text name="label.master.status"/></td>
									<td><s:text name="label.master.entryDate"/></td>
									<td><s:text name="lable.master.effectiveDate"/></td>
									<td><s:text name="label.master.description"/></td>
								</tr>
							</thead>
							<tbody>
								<s:iterator value="validationList" var="list" status="stat">
									<tr>
										<td><s:property value="#list.SUB_RATE_ID" /></td>
										<td><s:property value="#list.AGE_OF_VEHICLE_START" /></td>
										<td><s:property value="#list.AGE_OF_VEHICLE_END" /></td>
										<%--<td><s:property value="#list.COUNTRY_APPLICABLE" /></td>
										<td><s:property value="#list.CITY_APPLICABLE" /></td>
										<td><s:property value="#list.CLAIMS_START" /></td>
										<td><s:property value="#list.CLAIMS_END" /></td>
										--%><td><s:property value="#list.STATUS" /></td>
										<td><s:property value="#list.ENTRY_DATE" /></td>
										<td><s:property value="#list.EFFECTIVE_DATE" /></td>
										<td><s:property value="#list.DESCRIPTION" /></td>
									</tr>
								</s:iterator>
							</tbody>
						</table>
					</s:else>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal"> Close </button>
				</div>
			</div>
		</div>
	</div>
		
	</s:else>
	
	
</s:elseif>
<s:elseif test='"checkClaim".equals(reqFrom)'>
	<s:if test='validationList==null && !hasActionErrors()'>
		<button class="btn btn-success" style="border-radius:0px;" type="button">
			<span class="glyphicon glyphicon-ok"></span>
		</button>
	</s:if>
	<s:else>
		<button class="btn btn-danger" style="border-radius:0px;" type="button" onclick="$('#checkClaimModal').modal('show');">
			<span class="glyphicon glyphicon-remove"></span>
		</button>
		<div id="checkClaimModal" class="modal fade" role="dialog">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Claim Status</h4>
				</div>
				<div class="modal-body table-responsive">
					<s:if test='validationList==null && !hasActionErrors()'>
						Not Exist
					</s:if>
					<s:elseif test='hasActionErrors()'>
						<div style="color: red;">
							<s:actionerror cssStyle="list-style: none;"/>
						</div>
					</s:elseif>
					<s:else>
						<table class="table table-bordered" width="100%" cellspacing="0">
							<thead>
								<tr>
									<td><s:text name="label.master.sub.rate.id"/></td>
									<td><s:text name="label.master.vehicle.start.age"/></td>		
									<td><s:text name="label.master.vehicle.end.age"/></td>
									<td><s:text name="label.master.country.applicable"/></td>
									<td><s:text name="label.master.city.applicable"/></td>
									<td><s:text name="label.master.claim.start"/></td>
									<td><s:text name="label.master.claim.end"/></td>
									<td><s:text name="label.master.status"/></td>
									<td><s:text name="label.master.entryDate"/></td>
									<td><s:text name="lable.master.effectiveDate"/></td>
									<td><s:text name="label.master.description"/></td>
								</tr>
							</thead>
							<tbody>
								<s:iterator value="validationList" var="list" status="stat">
									<tr>
										<td><s:property value="#list.SUB_RATE_ID" /></td>
										<td><s:property value="#list.AGE_OF_VEHICLE_START" /></td>
										<td><s:property value="#list.AGE_OF_VEHICLE_END" /></td>
										<td><s:property value="#list.COUNTRY_APPLICABLE" /></td>
										<td><s:property value="#list.CITY_APPLICABLE" /></td>
										<td><s:property value="#list.CLAIMS_START" /></td>
										<td><s:property value="#list.CLAIMS_END" /></td>
										<td><s:property value="#list.STATUS" /></td>
										<td><s:property value="#list.ENTRY_DATE" /></td>
										<td><s:property value="#list.EFFECTIVE_DATE" /></td>
										<td><s:property value="#list.DESCRIPTION" /></td>
									</tr>
								</s:iterator>
							</tbody>
						</table>
					</s:else>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal"> Close </button>
				</div>
			</div>
		</div>
	</div>
		
	</s:else>
	
	
</s:elseif>
<s:elseif test='"checkSeatingTon".equals(reqFrom) || "checkCCGVWRange".equals(reqFrom) || "checkUAELiscence".equals(reqFrom) || "checkDriverAge".equals(reqFrom) || "checkSumInsureSd".equals(reqFrom)'>
	<s:if test='validationList==null && !hasActionErrors()'>
		<button class="btn btn-success" style="border-radius:0px;" type="button">
			<span class="glyphicon glyphicon-ok"></span>
		</button>
	</s:if>
	<s:else>
		<button class="btn btn-danger" style="border-radius:0px;" type="button"  onclick='openModel(<s:property value="reqFrom"/>Modal)'>
			<span class="glyphicon glyphicon-remove"></span>
		</button>
		<div id='<s:property value="reqFrom"/>Modal' class="modal fade" role="dialog">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">
						<s:if test='"checkSeatingTon".equals(reqFrom)'>
							<s:text name="label.master.seatingTon"></s:text>
						</s:if>
						<s:elseif test='"checkCCGVWRange".equals(reqFrom)'>
							<s:text name="label.master.cc.gvw.range"></s:text>
						</s:elseif>
						<s:elseif test='"checkUAELiscence".equals(reqFrom)'>
							<s:text name="label.master.uae.liscence"></s:text>
						</s:elseif>
						<s:elseif test='"checkDriverAge".equals(reqFrom)'>
							<s:text name="label.master.driver.age"></s:text>
						</s:elseif>
						<s:elseif test='"checkSumInsureSd".equals(reqFrom)'>
							<s:text name="label.master.sum.insured"></s:text>
						</s:elseif>
					</h4>
				</div>
				<div class="modal-body">
					<s:if test='validationList==null && !hasActionErrors()'>
						Not Exist
					</s:if>
					<s:elseif test='hasActionErrors()'>
						<div style="color: red;">
							<s:actionerror cssStyle="list-style: none;"/>
						</div>
					</s:elseif>
					<s:else>
						<div class="table-responsive">
							<table class="table table-bordered" width="100%" cellspacing="0">
								<thead style="background: #f5f5f5;">
									<tr>
										<th><s:text name="label.master.sno"></s:text> </th>
										<th><s:text name="label.master.seating.ton.start" /></th>
										<th><s:text name="label.master.seating.ton.end" /></th>
										<th><s:text name="label.master.cc.gvw.start.range" /></th>
										<th><s:text name="label.master.cc.gvw.end.range" /></th>
										<th><s:text name="label.master.uae.liscence.start" /></th>
										<th><s:text name="label.master.uae.liscence.end" /></th>
										<th><s:text name="label.master.driver.age.from" /></th>
										<th><s:text name="label.master.driver.age.to" /></th>
										<th><s:text name="label.master.suminsured.start" /></th>
										<th><s:text name="label.master.suminsured.end" /></th>
										<th><s:text name="label.master.policy.start.date" /></th>
										<th><s:text name="label.master.policy.end.date" /></th>
										<th><s:text name="label.master.motor.rate" /></th>
										<th><s:text name="label.master.minimum.premium" /></th>
										<th><s:text name="label.master.online.percent" /></th>
										<th><s:text name="label.master.deduction.percent" /></th>
										<th><s:text name="label.master.deduction.amount" /></th>
										<th><s:text name="label.master.deduction.description" /></th>
										<th><s:text name="label.status" /></th>
										<th><s:text name="label.master.effective.date" /></th>
										<th><s:text name="label.master.remarks" /></th>
									</tr>
								</thead>
								<tbody>
									<s:iterator value="validationList" var="list" status="stat">
										<tr>
											<td><s:property value="#stat.count" /></td>
											<td><s:property value="#list.SEATING_TON_START" /></td>
											<td><s:property value="#list.SEATING_TON_END" /></td>
											<td><s:property value="#list.CC_GVW_START_RANGE" /></td>
											<td><s:property value="#list.CC_GVW_END_RANGE" /></td>
											<td><s:property value="#list.UAE_LISCENCE_START" /></td>
											<td><s:property value="#list.UAE_LISCENCE_END" /></td>
											<td><s:property value="#list.DRIVER_AGE_FROM" /></td>
											<td><s:property value="#list.DRIVER_AGE_TO" /></td>
											<td><s:property value="#list.SUMINSURED_START" /></td>
											<td><s:property value="#list.SUMINSURED_END" /></td>
											<td><s:property value="#list.POLICY_START_DATE" /></td>
											<td><s:property value="#list.POLICY_END_DATE" /></td>
											<td><s:property value="#list.MOTOR_RATE" /></td>
											<td><s:property value="#list.MINIMUM_PREMIUM" /></td>
											<td><s:property value="#list.ONLINE_PERCENT" /></td>
											<td><s:property value="#list.DEDUCTION_PERCENT" /></td>
											<td><s:property value="#list.DEDUCTION_AMOUNT" /></td>
											<td><s:property value="#list.DEDUCTION_DESC" /></td>
											<td><s:property value="#list.STATUS" /></td>
											<td><s:property value="#list.EFFECTIVE_DATE" /></td>
											<td><s:property value="#list.REMARKS" /></td>
										</tr>
									</s:iterator>
								</tbody>
							</table>
						</div>
					</s:else>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal"> Close </button>
				</div>
			</div>
		</div>
	</div>
	</s:else>
</s:elseif>
<s:elseif test='"checkSeatingCylinder".equals(reqFrom)'>
	<s:if test='validationList==null && !hasActionErrors()'>
		<button class="btn btn-success" style="border-radius:0px;" type="button">
			<span class="glyphicon glyphicon-ok"></span>
		</button>
	</s:if>
	<s:else>
		<button class="btn btn-danger" style="border-radius:0px;" type="button"  onclick="$('#checkSeatingCylinderModal').modal('show')">
			<span class="glyphicon glyphicon-remove"></span>
		</button>
		<div id='checkSeatingCylinderModal' class="modal fade" role="dialog">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">
							<s:text name="label.seating.cylinder"></s:text>
					</h4>
				</div>
				<div class="modal-body">
					<s:if test='validationList==null && !hasActionErrors()'>
						Not Exist
					</s:if>
					<s:elseif test='hasActionErrors()'>
						<div style="color: red;">
							<s:actionerror cssStyle="list-style: none;"/>
						</div>
					</s:elseif>
					<s:else>
						<div class="table-responsive">
							<table class="table table-bordered" width="100%" cellspacing="0">
								<thead style="background: #f5f5f5;">
									<tr>
										<th><s:text name="label.master.sno"></s:text> </th>
										<th><s:text name="label.seating.cylinder.start"/></th>
										<th><s:text name="label.seating.cylinder.end"/></th>		
										<th><s:text name="label.third.party.rate"/></th>
										<%-- <th><s:text name="label.master.vehicle.country"/></th>--%>
									</tr>
								</thead>
								<tbody>
									<s:iterator value="validationList" var="list" status="stat">
										<tr>
											<td><s:property value="#stat.count" /></td>
											<td><s:property value="#list.SEATING_CYLINDER_START" /></td>
											<td><s:property value="#list.SEATING_CYLINDER_END" /></td>
											<td><s:property value="#list.THIRD_PARTY_RATE" /></td>
											<%--<td><s:property value="#list.COUNTRY_NAME" /></td>--%>
										</tr>
									</s:iterator>
								</tbody>
							</table>
						</div>
					</s:else>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal"> Close </button>
				</div>
			</div>
		</div>
	</div>
	</s:else>
</s:elseif>
<s:elseif test='"modalPopup".equals(reqFrom)'>
    <div class="panel-body">
		<table width="100%" class="table table-bordered" id="netrateinfo" >
			<thead>
				<tr align="center">
					<th bgcolor="grey" class="tablecol1" align="center"><s:text name="NAME"/></th>
					<th bgcolor="grey" class="tablecol1" align="center"><s:text name="DESCRIPTION" /></th>
					
				</tr>
			</thead>
			<tbody>
				<s:iterator value="factorDetails" var="list"  status="stat">
						<tr>
						<td  class="tablecol" align="right"><s:text name="AGENCY CODE"/></td>
						<td  class="tableval" align="center"><s:property value="#list.AGENCY_CODE" /></td></tr><tr>
						<td  class="tablecol" align="right"><s:text name="FACTOR NAME"/></td>
						<td  class="tableval" align="center"><s:property value="#list.FACTOR_DESC" /></td></tr><tr>
						<td  class="tablecol" align="right"><s:text name="DIVISION CODE"/></td>
						<td  class="tableval" align="center"><s:property value="#list.DIVISION_CODE" /></td></tr><tr>
						<td  class="tablecol" align="right"><s:text name="VEHICLE USAGE"/></td>
						<td  class="tableval" align="center"><s:property value="#list.VEHUSAGE" /></td></tr><tr>
						<td  class="tablecol" align="right"><s:text name="BODY TYPE"/></td>
						<td  class="tableval" align="center"><s:property value="#list.BODYTYPE" /></td></tr><tr>
						<td  class="tablecol" align="right"><s:text name="CUSTOMER TYPE"/></td>
						<td  class="tableval" align="center"><s:property value="#list.CUSTOMER_TYPE" /></td></tr>
						<tr><td  class="tablecol" align="right"><s:text name="PARAM 1"/></td>
						<td  class="tableval" align="center"><s:property value="#list.PARAM1" /></td></tr>
						<tr><td  class="tablecol" align="right"><s:text name="PARAM 2"/></td>
						<td  class="tableval" align="center"><s:property value="#list.PARAM2" /></td></tr>
						<tr><td  class="tablecol" align="right"><s:text name="PARAM 3"/></td>
						<td  class="tableval" align="center"><s:property value="#list.PARAM3" /></td></tr>
						<tr><td  class="tablecol" align="right"><s:text name="PARAM 4"/></td>
						<td  class="tableval" align="center"><s:property value="#list.PARAM4" /></td></tr>
						<tr><td  class="tablecol" align="right"><s:text name="PARAM 5"/></td>
						<td  class="tableval" align="center"><s:property value="#list.PARAM5" /></td></tr>
						<tr><td  class="tablecol" align="right"><s:text name="PARAM 6"/></td>
						<td  class="tableval" align="center"><s:property value="#list.PARAM6" /></td></tr>
						<tr><td  class="tablecol" align="right"><s:text name="PARAM 7"/></td>
						<td  class="tableval" align="center"><s:property value="#list.PARAM7" /></td></tr>
						<tr><td  class="tablecol" align="right"><s:text name="PARAM 8"/></td>
						<td  class="tableval" align="center"><s:property value="#list.PARAM8" /></td></tr>
						<tr><td  class="tablecol" align="right"><s:text name="PARAM 9"/></td>
						<td  class="tableval" align="center"><s:property value="#list.PARAM9" /></td></tr>
						<tr><td  class="tablecol" align="right"><s:text name="PARAM 10"/></td>
						<td  class="tableval" align="center"><s:property value="#list.PARAM10" /></td></tr>
						<%--<tr><td  class="tablecol" align="right"><s:text name="PARAM 11"/></td>
						<td  class="tableval" align="center"><s:property value="#list.PARAM11" /></td></tr>
						<tr><td  class="tablecol" align="right"><s:text name="PARAM 12"/></td>
						<td  class="tableval" align="center"><s:property value="#list.PARAM12" /></td></tr>
						<tr><td  class="tablecol" align="right"><s:text name="PARAM 13"/></td>
						<td  class="tableval" align="center"><s:property value="#list.PARAM13" /></td></tr>
						<tr><td  class="tablecol" align="right"><s:text name="PARAM 14"/></td>
						<td  class="tableval" align="center"><s:property value="#list.PARAM14" /></td></tr>
						<tr><td  class="tablecol" align="right"><s:text name="PARAM 15"/></td>
						<td  class="tableval" align="center"><s:property value="#list.PARAM15" /></td></tr>								
						<tr><td  class="tablecol" align="right"><s:text name="CONFIG TYPE"/></td>
						<td  class="tableval" align="center"><s:property value="#list.CONFIG_TYPE" /></td></tr> --%>
						<tr><td  class="tablecol" align="right"><s:text name="RATING TYPE"/></td>
						<td  class="tableval" align="center"><s:property value="#list.RATING_TYPE" /></td>
					</tr>
				</s:iterator>
			</tbody>
		</table>
	</div>
</s:elseif>