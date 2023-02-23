<%@ page isELIgnored="false" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html <s:if test="locale.language == 'ar'">dir="rtl"</s:if> >
<head>
<script type="text/javascript">
</script>
</head>
<body>

<div id="loading" class="ajaxLoader" style="width:100%">
   <img id="loading-image" src="${pageContext.request.contextPath}/images/madisonSymbolLogo.png"/>
</div>


	<div class="modal-header">
      	<h5 class="modal-title">Vehicle Details</h5>
      	<i class="far fa-times-circle mt-2" data-dismiss="modal"></i>
    </div>
    <div class="modal-body">
     	<div class="row" align="left" style="padding-left: 80px;">
			<s:if test="hasActionErrors()">
				<span style="color:red;">
					<s:actionerror/>
				</span>
			</s:if>
			<%-- <s:if test="hasActionMessages()">
				<span style="color:green;">
					<s:actionmessage/> 
				</span>
			</s:if> --%>
		</div>
      <div class="row mt-2">
         <div class="col-md-5 mt-2 modalTextFielRemove offset-md-1">
         	<label class="labelHeader"><s:text name="motor.make" /><font color="red">*</font></label>
            <div id="makeListAjax">
				<s:select name="makeIdList[0]" id="make" list="makeList" listKey="Code" listValue="Description" headerKey="" headerValue="---Make---" onchange="getAjaxModel(this.form,'?make='+this.value,'modelList');removeVehicleTypeDetails(); vehicleTypeDetailsAjax(this.form);" class="form-control"  disabled="#disable1" placeholder="Make" theme="simple"/>
			</div>
         </div>
         <div class="col-md-5 mt-2 mobileResolutionTop">
         	<label class="labelHeader"><s:text name="motor.model" /><font color="red">*</font></label>
           	<div id="modelList">
				<s:select name="modelIdList[0]" id="model" list="modelList" listKey="Code" listValue="Description" headerKey="" headerValue="---Model---" onchange="removeVehicleTypeDetails(); vehicleTypeDetailsAjax(this.form);clearVehDtl();" class="form-control"  disabled="#disable1" placeholder="Model" theme="simple"/>
			</div>
         </div>
      </div>
       <div class="row mt-2">
       	
         <div class="col-md-10 offset-md-1 Vehiclechoosed">
         	<label class="labelHeader"><s:text name="motor.vehicleDetails" /><font color="red">*</font></label>
           <div id="vehicleTypeDetailsAjax">
           <s:if test='"add".equalsIgnoreCase(reqFrom)'>
	            <table class="table table-bordered table-sm">
	              <thead>
	                <tr>
	                  <th>Choose</th>
	                  <th>Type of Body</th>
	                  <th>Vehicle Usage</th>
	                  <th>Seating Capacity</th>
	                </tr>
	              </thead>
	              <tbody>
	                <tr><td align="center" colspan="4">Please Select Make and Model</td></tr>
	              </tbody>
	            </table>
            </s:if>
            <s:else>
            	<s:set var="#sVehicleTypeDetails" value="%{vehicleTypeDetails}"/>
            	<table class="table table-bordered table-sm">
	              <thead>
	                <tr>
	                  <th>Choose</th>
	                  <th>Type of Body</th>
	                  <th>Vehicle Usage</th>
	                  <th>Seating Capacity</th>
	                </tr>
	              </thead>
	              <tbody>
		           <s:if test='#sVehicleTypeDetails.size()>0'>
		             <s:iterator value="#sVehicleTypeDetails" var="record1" status="status1">
						<tr>
							<td> <input type="radio" name="selects" id="btn_${record1.BodyId}${record1.VehicleUseageId}" value="<s:property value="#record1.BodyId" />~<s:property value="#record1.VehicleUseageId" />" onclick="setModalVehicleDetails('${status1.index}','${record1.BodyId}','${record1.VehicleUseageId}','${record1.BodyType}','${record1.VehicleUsage}');checkBodyClick(this.form);" /></td>
							<td> <s:property value="#record1.BodyType"/></td>
							<td> <s:property value="#record1.VehicleUsage"/> </td>
							<td> 
								<s:if test='#record1.BodyId!="9" && #record1.BodyId!="27"'>
									<s:textfield name="tSeatingCapacity[%{#status1.count}]" id="tSeatingCapacity_%{#record1.BodyId}%{#record1.VehicleUseageId}"  maxLength="2" onkeyup="checkNumbers(this);setVehicleTypeDetails(this.form);" cssClass="form-control " data-content="Seating Capacity" theme="simple" style="height:30px !important"/>
								</s:if>
								<s:else>
									<s:textfield name="tSeatingCapacity[%{#status1.count}]" id="tSeatingCapacity_%{#record1.BodyId}%{#record1.VehicleUseageId}" maxLength="2" onkeyup="checkNumbers(this);setVehicleTypeDetails(this.form);" cssClass="form-control " data-content="Seating Capacity" theme="simple" value="2" readonly="true" style="height:30px !important"/>
								</s:else>							
							</td>
						</tr>
					</s:iterator>
					<tr>
						<td colspan="4">
							<div class="text-primary" style="font-size: 12px;">
								<b>Help <i class="fa fa-help"></i> </b>
								<ol type="a">
									<li>Type of Body and Vehicle Usage – Select the correct combination of body type and use of your vehicle e.g. a corolla that is not used for business can be Sedan with Private Use while a corolla used as a taxi can be sedan with public transport use.</li>
									<li>Seating Capacity – Maximum number of people the vehicle can carry including the driver</li>
								</ol>
							</div>
						</td>
					</tr>
					</s:if>
				<s:else>
					<tr><td align="center" colspan="4">No Data Found For Selected Make and Model</td></tr>
				</s:else>
           </tbody>
	            </table>
	            <s:hidden name="modalSelectId" id="modalSelectId"/>
				<s:hidden name="modalTypeBody" id="modalTypeBody"/>
				<s:hidden name="modalVehicleUsage" id="modalVehicleUsage"/>
				<s:hidden name="modalTypeBodyName" id="modalTypeBodyName"/>
				<s:hidden name="modalVehicleUsageName" id="modalVehicleUsageName"/>
            </s:else>
           </div>
         </div>
       </div>
       <div class="row mt-2">
         <div class="col-md-5 mt-2 modalTextFielRemove offset-md-1">
         	<s:if test='"3".equalsIgnoreCase(policyType)'>
         		<label class="labelHeader"><s:text name="motor.sumInsured" /></label>
                <s:textfield name="sumInsuredList[0]" id="sumInsured" class="form-control"  maxlength="8" onkeyup="checkNumbers(this);"  disabled="true"/>
         	</s:if>
         	<s:else>
         		<label class="labelHeader"><s:text name="motor.sumInsured" /><font color="red">*</font></label>
          		<s:textfield name="sumInsuredList[0]" id="sumInsured" class="form-control"  maxlength="8" onkeyup="checkNumbers(this);"  disabled="#disable1"/>
         	</s:else>
         	
         </div>
         <div class="col-md-5 mt-2 mobileResolutionTop">
         	<label class="labelHeader"><s:text name="motor.electricalAcc" /></label>
           <s:textfield name="electricalAccList[0]" id="electricalAcc" class="form-control"   maxlength="8" onkeyup="checkNumbers(this);" disabled="#disable1"/>
         </div>
       </div>
       <div class="row mt-2">
         <div class="col-md-5 mt-2 modalTextFielRemove offset-md-1">
         	<label class="labelHeader"><s:text name="motor.nonElectricalAcc" /></label>
           <s:textfield name="nonElectricalAccList[0]" id="nonElectricalAcc" class="form-control"  maxlength="8" onkeyup="checkNumbers(this);" disabled="#disable1"/>
         </div>
         <div class="col-md-5 mt-2 mobileResolutionTop">
         	<label class="labelHeader"><s:text name="motor.mfgYr" /><font color="red">*</font></label>
           <s:select name="mfgYrIdList[0]"  id="mfgYr" list="mfgYrMap" headerKey="" headerValue="---Manufacture Year---" class="form-control"  disabled="#disable1" />
         </div>
       </div>
       <div class="row mt-2">
         <div class="col-md-5 mt-2 modalTextFielRemove offset-md-1">
         	<label class="labelHeader"><s:text name="motor.deductible" /><font color="red">*</font></label>
           <div id="deductibleAjax">
	       <s:select name="deductibleIdList[0]" id="deductible" list="deductibleList" listKey="Code" listValue="Description" headerKey="" headerValue="---Excess Limit---" class="form-control" disabled="#disable1" />
	</div>
         </div>
         <div class="col-md-5 mt-2 mobileResolutionTop">
         	<label class="labelHeader"><s:text name="motor.regNo" /><font color="red">*</font></label>
           <s:textfield name="regNoList[0]" id="regNoList" class="form-control"  maxLength="20" disabled="#endtDisable"/>
         </div>
         <s:hidden name="agencyRepairYNIdList[0]" value="N"/>
			  <s:hidden name="vehicleIdList[0]" id="rvehicleId"/>
       </div>
       <div class="row mt-2">
         <div class="col-md-5 mt-2 modalTextFielRemove offset-md-1">
         	<label class="labelHeader"><s:text name="motor.chassisNo" /><font color="red">*</font></label>
           <s:textfield name="chassisNoList[0]" id="chassisNoList" class="form-control"  maxLength="20" disabled="#endtDisable"/>
         </div>
         <div class="col-md-5 mt-2 mobileResolutionTop">
         	<label class="labelHeader"><s:text name="motor.engineNo" /><font color="red">*</font></label>
           <s:textfield name="engineNoList[0]" id="engineNoList" class="form-control"  maxLength="20" disabled="#endtDisable"/>
         </div>
       </div>
       <div class="row mt-2">
         <div class="col-md-5 mt-2 modalTextFielRemove offset-md-1">
         	<label class="labelHeader"><s:text name="motor.vehicleColour" /></label>
           <s:select name="vehicleColourIdList[0]" id="vehicleColourIdList" list="vehicleColourList" listKey="Code" listValue="Description" headerKey="" headerValue="---Vehicle Color---" class="form-control"  disabled='#endtDisable'/>
         </div>
         <div class="col-md-5 mt-2 mobileResolutionTop">
         	<label class="labelHeader"><s:text name="motor.cubicCapacity" /></label>
           <s:textfield name="cubicCapacityList[0]" id="cubicCapacity" cssClass="inputBox "  maxLength="5" onkeyup="checkNumbers(this);" class="form-control" disabled="#disable1"/>
         </div>
         
       </div>
       <div class="row mt-2">
       	 <div class="col-md-5 mt-4 modalTextFielRemove offset-md-1">
             	<label for="leasedYNIdList" class="mr-sm-2">Leased Yes/No<font color="red">*</font></label>
               <s:radio name="leasedYNIdList[0]" id="leasedYNIdList" list="#{'Y':'Yes','N':'No'}" value='%{(leasedYNIdList==null||leasedYNIdList[0]==null||"".equals(leasedYNIdList[0]))?"N":leasedYNIdList[0]}' onclick="toggleLeasedYN(this.value,'%{#status.index}');" disabled="#endtDisable" class="" />
         </div>
         <div class="col-md-5 mt-2 mobileResolutionTop">
         	<label class="labelHeader"><s:text name="motor.bankOfFinance" /><font color="red">*</font></label>
           <s:select name="bankOfFinanceIdList[0]" id="bankOfFinanceIdList" list="bankOfFinanceList" listKey="Code" listValue="Description" headerKey="" headerValue="---Bank Of Finance---" class="form-control"  disabled='%{(#endtDisable || leasedYNIdList[0]==null||"".equals(leasedYNIdList[0])||"N".equals(leasedYNIdList[0]))?"true":"false"}'/>
         </div>
         
       </div>
       <div class="row mt-2">
       	 <div class="col-md-5 mt-4 modalTextFielRemove offset-md-1">
             	<label for="prevClaimYn" class="mr-sm-2">Previous Claims Yes/No<font color="red">*</font></label>
               <s:radio name="prevClaimYn[0]" id="prevClaimYn" list="#{'Y':'Yes','N':'No'}" value='%{(prevClaimYn==null||prevClaimYn[0]==null||"".equals(prevClaimYn[0]))?"N":prevClaimYn[0]}' onclick="toggleClaimsYN(this.value,'%{#status.index}');" disabled="#endtDisable" class="" />
         </div>
         <div class="col-md-5 mt-2 mobileResolutionTop">
         	<label class="labelHeader">No Of Claims<font color="red">*</font></label>
           <s:select name="noOfClaims[0]" id="noOfClaims" list="noOfClaimsList" listKey="Code" listValue="Description" headerKey="" headerValue="---No Of Claims---" class="form-control"  disabled='%{(#endtDisable || prevClaimYn[0]==null||"".equals(prevClaimYn[0])||"N".equals(prevClaimYn[0]))?"true":"false"}'/>
         </div>
         
       </div>
       
        <s:if test='!"add".equalsIgnoreCase(reqFrom)'>
	       <s:hidden  name="typeBody" id="bodyName" />
	       <s:hidden  name="vehicleUsage" id="usageName" />
	       <s:hidden  name="seatingCapacity" id="seatingCapacity" />
	       <s:hidden  name="typeBodyNameList[0]" id="bodyNameDesc" />
	       <s:hidden  name="vehicleUsageNameList[0]" id="usageNameDesc" />
		</s:if>

     </div>
       <div class="modal-footer">
         <s:if test='!"add".equalsIgnoreCase(reqFrom)'>
         	<%--<button type="button" class="btn btn-danger btn-block col-md-3" onclick="this.form.actionType.value='getSaveVehicle';this.form.reqFrom.value='edit';disableForm(this.form,false,'');setVehicleTypeDetails(this.form);fnsubmit('addVehicleNewMotor')">Save</button> --%>
         	 <s:if test="hasActionMessages()">
					<button type="button" class="btn btn-danger btn-block col-md-3" data-dismiss="modal" onclick="getBack('vehDetail');">Close</button>	
			 </s:if>
			 <s:else>
			  	<button type="button" id="save" class="btn btn-danger btn-block col-md-3" onclick="this.form.actionType.value='getSaveVehicle';this.form.reqFrom.value='update';disableForm(this.form,false,'');vehicleUpdate(this.form);">Save</button>
			 </s:else>
         </s:if>
         <s:else>
         	<%--<button type="button" class="btn btn-danger btn-block col-md-3" onclick="this.form.actionType.value='getSaveVehicle';this.form.reqFrom.value='add';disableForm(this.form,false,'');setVehicleTypeDetails(this.form);fnsubmit('addVehicleNewMotor')">Save</button>  --%>
         	 <s:if test="hasActionMessages()">
					<button type="button" class="btn btn-danger btn-block col-md-3" data-dismiss="modal" onclick="getBack('vehDetail');">Close</button>	
			 </s:if>
			 <s:else>
			  	<button type="button" id="save" class="btn btn-danger btn-block col-md-3" onclick="this.form.actionType.value='getSaveVehicle';this.form.reqFrom.value='insert';disableForm(this.form,false,'');checkfields(this.form);">Save</button>
			 </s:else>
         </s:else>
       </div>

  <script>
       try{
	       var name = document.getElementById('bodyName').value;
	       var usage = document.getElementById('usageName').value;
	       var capacity = document.getElementById('seatingCapacityList').value;
	       if(capacity==null || capacity==''){
	    	   var capacity = document.getElementById('seatingCapacity').value;
	       }
	       //alert(name+",, "+usage+",, "+capacity);
	       if(name!=null && name!='' && usage!=null && usage!=''){
	    	   //alert(name+",, "+usage);
	    	   document.getElementById('btn_'+name+usage).checked = true;
		       document.getElementById('tSeatingCapacity_'+name+usage).value = capacity;
	       }
	       
	       
	       //tSeatingCapacity
       }catch(err){
    	   console.log(err);
       }
       function clearVehDtl(){
    	   try{
	    	   document.getElementById('bodyName').value='';
		       document.getElementById('usageName').value='';
		       //document.getElementById('seatingCapacityList').value='';
		      
		       //alert(name+",, "+usage+",, "+capacity);
		       if(name!=null && name!='' && usage!=null && usage!=''){
		       	document.getElementById('btn_'+name+usage).checked = false;
		       	document.getElementById('tSeatingCapacity_'+name+usage).value = '';
		       }
    	   }catch(err){
        	   console.log(err);
           }
       }
       function checkfields(frm){
    	   var select = $("input[type='radio'][name='selects']:checked").val();
			//alert("select "+select);
			if(select!=null) {
				$('#save').attr('disabled',true);
    	   		setVehicleTypeDetails(frm);
    	   		return fnAddVeh('addVehicleNewMotor')
			}else{
				alert("Please Choose Vehicle Details");
			}
       }
       function fnAddVeh() {
			postFormRequest('${pageContext.request.contextPath}/saveVehicleAjaxMotor.action', 'vehicleEditAjax','motor');
			return false;
		}
       
       function vehicleUpdate(frm){
    	   postFormRequest('${pageContext.request.contextPath}/vehicleUpdateMotor.action', 'vehicleEditAjax','motor');
			return false;
		}
       
       /*$(document).ready(function()
		    {
		        $('#save').click(function()
		        {
		            $(this).attr('disabled',true);
		            return false;
		        });
		    });*/
      try{
    	  <s:if test="hasActionMessages()">
	      //alert();
	      $("#myModal").modal('hide');
	      //getBack('vehDetail')
	      //var reqFrom='<s:property value="reqFrom"/>';
	       //alert(reqFrom);
	       //commented on 14/01/2023 start
	       /*  var customerType='<s:property value="customerType"/>';
	      	  <s:if test='"add".equalsIgnoreCase(reqFrom) || "insert".equalsIgnoreCase(reqFrom)'>
	      		<s:if test='"INDIVIDUAL".equalsIgnoreCase(customerType)'>
		      	fnUpdateDriver('','edit','<s:property value="deleteVehicleId" />','<s:property value="vehicleUsage" />','<s:property value="applicationNo" />','driverEditAjax');
		      	$("#myModal2").modal('show');
		      	 </s:if>
		      	<s:else>
			      	getBack('vehDetail')
			      </s:else>
		      </s:if>
		      <s:else>
		      //alert("2");
		      	getBack('vehDetail')
		      </s:else> */
		    //commented on 14/01/2023 end
		    getBack('vehDetail');
	      </s:if>
       }catch(err){
    	   console.log(err);
       }
       try{
       		$('#myModal').scrollTop(0);
       }catch(err){
    	   console.log(err);
       }
      </script>
</body>
</html>