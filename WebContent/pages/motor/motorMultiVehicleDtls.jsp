<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:set name="multiVehicleDtls" value="%{multiVehicleDetails}"/>
<s:if test='applicationNo !=null && applicationNo.length() > 0 && #multiVehicleDtls.size()>0'>
	<div class="tablerow">
		<div class="tablerow">
			<div class="tablerow">
				<div class="tablerow">
					<table cellpadding="1" class="footable" cellspacing="1" border="1">
						<thead>
					        <tr>
								<th style="width:5%;"><s:label value="S.No." /></th>
								<th style="width:20%;"><s:label key="motor.make" /></th>
								<th style="width:20%;"><s:label key="motor.model" /></th>
								<th style="width:15%;"><s:label key="motor.sumInsured" /></th>
								<th style="width:15%;"><s:label key="motor.cubicCapacity" /></th>
								<th style="width:15%;"><s:label key="motor.typeOfBody"/></th>
								<th style="width:10%;">Action</th>
							</tr>
						</thead>
						<tbody>
							<s:iterator value="#multiVehicleDtls" var="view" status="status">
								<tr>
									<td><s:property value="#status.count" /></td>
									<td><s:property value="#view.MAKE_NAME" /></td>					
									<td><s:property value="#view.MODEL_NAME" default=" " /> </td>
									<td><s:property value="#view.SUMINSURED_VALUE_LOCAL" /></td>
									<td><s:property value="#view.CUBIC_CAPACITY" /></td>
									<td><s:property value="#view.TYPE_OF_BODY_NAME" /></td>
									<td>
										<s:submit type="button" value="Edit" onclick="disableForm(this.form,false,'');fnVehiclesubmit('edit','%{VEHICLE_ID}');" cssClass="btn btn-sm btn-warning" disabled="#disable1"/>
										<br/>
										<s:submit type="button" name="deleteButton" value="Delete" cssStyle="margin-top: 2px;" onclick="disableForm(this.form,false,'');fnVehiclesubmit('delete','%{VEHICLE_ID}');" cssClass="btn btn-sm btn-danger" disabled="#disable1"/>
									</td>
									
								</tr>
							</s:iterator>
						</tbody>
					</table>
					<br class="clear"/>
				</div>
			</div>
		</div>
	</div>
	<s:hidden name="deleteVehicleId" id="deleteVehicleId"/>
</s:if>