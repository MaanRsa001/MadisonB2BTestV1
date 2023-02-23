<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ page import="java.util.List,java.util.ArrayList,java.util.Map"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<div class="Card_Parent  mt-4">
    <div class="card card-1">
    	<div class="row">
    		<div class="col-md-6">
        		<h4>Policy Information</h4>
        	</div>
    		<div class="col-md-4">
    			<b>Quote No :</b> <s:property value="quoteNo"/>
    		</div>
    		<div class="col-md-2">
    			<a class="btn btn-primary btn-sm" style="cursor: pointer" id="custShBtnId" onclick="toggleCustDtls();">Show Details</a>
    			<s:hidden name="shCustDtls" id="shCustDtls" value="show"/>
    		</div>
        </div>
        <hr>
        <div class="row" id="custDtlsDivId" style="display: none;">
        	<div class="row rowFlex">
		    	 <div class="col-md-4" style="padding-bottom: 15px;">
	    			<label class="LabelHeading">Inception Date</label>
	    			<div class="input-group mb-2"><s:property value="inceptionDt"/></div>
		    	 </div>
		    	 <div class="col-md-4" style="padding-bottom: 15px;">
	    			<label class="LabelHeading">Expiry Date</label>
	    			<div class="input-group mb-2"><s:property value="expiryDt"/></div>
		    	 </div>
			     <div class="col-md-4" style="padding-bottom: 15px;">
			     	<label class="LabelHeading">Customer Type</label>
			     	<div class="input-group mb-2"><s:property value="customerType"/></div>
			     </div>
			     <s:if test='companyRegNo!=null && !"".equalsIgnoreCase(companyRegNo)'>
				     <div class="col-md-4" style="padding-bottom: 15px;">
				     	<label class="LabelHeading">Company Registration No</label>
				     	<div class="input-group mb-2"><s:property value="companyRegNo"/></div>
				     </div>
			     </s:if>
			     <div class="col-md-4" style="padding-bottom: 15px;">
			     	<label class="LabelHeading">Customer Name</label>
			     	<div class="input-group mb-2"><s:property value="title"/>.<s:property value="customerName"/>&nbsp;<s:property value="custLastName"/></div>
			     </div>
			     <s:if test='custGenderDesc!=null && !"".equalsIgnoreCase(custGenderDesc)'>
				     <div class="col-md-4" style="padding-bottom: 15px;">
				     	<label class="LabelHeading">Gender</label>
				     	<div class="input-group mb-2"><s:property value="custGenderDesc"/></div>
				     </div>
			     </s:if>
			     <div class="col-md-4" style="padding-bottom: 15px;">
			     	<label class="LabelHeading">Date Of Birth</label>
			     	<div class="input-group mb-2"><s:property value="custdob"/></div>
			     </div>
			     <s:if test='occupation!=null && !"".equalsIgnoreCase(occupation)'>
				     <div class="col-md-4" style="padding-bottom: 15px;">
				     	<label class="LabelHeading">Occupation</label>
				     	<div class="input-group mb-2"><s:property value="occupation"/></div>
				     </div>
			     </s:if>
			     <div class="col-md-4" style="padding-bottom: 15px;">
			     	<label class="LabelHeading">Email</label>
			     	<div class="input-group mb-2"><s:property value="email"/></div>
			     </div>
			     <div class="col-md-4" style="padding-bottom: 15px;">
			     	<label class="LabelHeading">Mobile No</label>
			     	<div class="input-group mb-2"><s:property value="mobileNo"/></div>
			     </div>
			     <s:if test='custAlterMobileNo!=null && !"".equalsIgnoreCase(custAlterMobileNo)'>
				     <div class="col-md-4" style="padding-bottom: 15px;">
				     	<label class="LabelHeading">Alternate Mobile No</label>
				     	<div class="input-group mb-2"><s:property value="custAlterMobileNo"/></div>
				     </div>
			     </s:if>
			     <div class="col-md-4" style="padding-bottom: 15px;">
			     	<label class="LabelHeading">Address</label>
			     	<div class="input-group mb-2"><s:property value="address1"/>&nbsp;<s:property value="address2"/></div>
			     </div>
			     <s:if test='custCityName!=null && !"".equalsIgnoreCase(custCityName)'>
				     <div class="col-md-4" style="padding-bottom: 15px;">
				     	<label class="LabelHeading">City</label>
				     	<div class="input-group mb-2"><s:property value="custCityName"/></div>
				     </div>
			     </s:if>
			     <s:if test='poBox!=null && !"".equalsIgnoreCase(poBox)'>
				     <div class="col-md-4" style="padding-bottom: 15px;">
				     	<label class="LabelHeading">PO Box</label>
				     	<div class="input-group mb-2"><s:property value="poBox"/></div>
				     </div>
			     </s:if>
			     <div class="col-md-4" style="padding-bottom: 15px;">
				     <label class="LabelHeading">National Registration Card No</label>
				     <div class="input-group mb-2"><s:property value="custnrc1"/>/<s:property value="custnrc2"/>/<s:property value="custnrc3"/></div>
			     </div>
			     <s:if test='custPassportNo!=null && !"".equalsIgnoreCase(custPassportNo)'>
				     <div class="col-md-4" style="padding-bottom: 15px;">
				     	<label class="LabelHeading">Passport No</label>
				     	<div class="input-group mb-2"><s:property value="custPassportNo"/></div>
				     </div>
			     </s:if>
			</div>
        </div>
    </div>
</div>
<SCRIPT type="text/javascript">

	function toggleCustDtls(){
		try{
			var sh = document.getElementById('shCustDtls').value;
			if(sh == 'show'){
				document.getElementById('shCustDtls').value = 'hide';
				document.getElementById('custDtlsDivId').style.display = 'block';
				document.getElementById('custShBtnId').innerHTML = 'Hide Details'
			}else{
				document.getElementById('shCustDtls').value = 'show';
				document.getElementById('custDtlsDivId').style.display = 'none';
				document.getElementById('custShBtnId').innerHTML = 'Show Details'
			}
		}catch(err){
			console.error(err);
		}
	}

</SCRIPT>