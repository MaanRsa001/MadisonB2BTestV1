<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
  <%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<s:form id="referralerror" name="referralerror" method="post" theme="simple" action="">
	<s:hidden name="quoteNo" id="quoteNo"/>
	<s:hidden name="policyStartDate" id="policyStartDate"/>
	<s:hidden name="policyEndDate" id="policyEndDate"/>
	<s:hidden name="customerId" id="customerId"/>
	<s:hidden name="applicationNo" id="applicationNo"/>
	<s:hidden name="policyType" id="policyType"/>
	<s:hidden name="display" />
	<s:hidden name="vehicleId"/>
	<s:hidden name="currencyType"/>


<s:if test='"ShowReferralError".equalsIgnoreCase(display)'>
	<div class="panel panel-primary">
		<div class="container PolicyPage mt-5">
			<div class="Card_Parent PolicyInformation">
		            <div class="card card-5">
		                <div class="row">
		                	<div class="col-md-12">
		                		<h3>Policy Information</h3><hr>
		                		<div class="row">
				                    <div class="col-md-5 col-6">
				                        <label class="LabelHeading">Quote No</label>
				                    </div>
				                    <div class="col-md-5 col-6">
				                        <label class="labelValues"><s:property value="quoteNo"/></label>
				                    </div>
			                </div>
			                <div class="row">
			                    <div class="col-md-5 col-6">
			                        <label class="LabelHeading">Customer Name</label>
			                    </div>
			                    <div class="col-md-5 col-6">
			                        <label class="labelValues"><s:property value="title"/>.<s:property value="customerName"/>&nbsp;<s:property value="custLastName"/></label>
			                    </div>
			                </div>
	                		<div class="row">
			                    <div class="col-md-5 col-6">
			                        <label class="LabelHeading">Policy Type</label>
			                    </div>
			                    <div class="col-md-5 col-6">
			                        <label class="labelValues"><s:property value="policyName"/></label>
			                    </div>
			                </div>
	                	</div>
	                </div>
	                <div id="demo" class="collapse">
		                <div class="row">
		                    <div class="col-md-5 col-6">
		                        <label class="LabelHeading">Quote Date</label>
		                    </div>
		                    <div class="col-md-5 col-6">
		                        <label class="labelValues"> <s:property value="quoteDate"/></label>
		                    </div>
		                </div>
		                <div class="row">
		                    <div class="col-md-5 col-6">
		                        <label class="LabelHeading">Product</label>
		                    </div>
		                    <div class="col-md-5 col-6">
		                        <label class="labelValues"><s:property value="product"/> </label>
		                    </div>
		                </div>
		                <div class="row">
		                    <div class="col-md-5 col-6">
		                        <label class="LabelHeading">Email</label>
		                    </div>
		                    <div class="col-md-5 col-6">
		                        <label class="labelValues"><s:property value="email"/></label>
		                    </div>
		                </div>
		                <div class="row">
		                    <div class="col-md-5 col-6">
		                        <label class="LabelHeading">PolicyStartDate</label>
		                    </div>
		                    <div class="col-md-5 col-6">
		                        <label class="labelValues"><s:property value="policyStartDate"/></label>
		                    </div>
		                </div>
		                <div class="row">
		                    <div class="col-md-5 col-6">
		                        <label class="LabelHeading">PolicyEndDate</label>
		                    </div>
		                    <div class="col-md-5 col-6">
		                        <label class="labelValues"><s:property value="policyEndDate"/></label>
		                    </div>
		                </div>
		                 <div class="row">
		                    <div class="col-md-5 col-6">
		                        <font color="red"><label class="LabelHeading">Currency</label></font>
		                    </div>
		                    <div class="col-md-5 col-6">
		                        <label class="labelValues"><s:property value="currencyType"/></label>
		                    </div>
		                </div>
	                </div>
	                 <div class="row">
	                	<div class="col-md-12 text-right">
	                		<a style="text-decoration:none" id="more" data-toggle="collapse" data-target="#demo" onclick="funCollapse('less')">View More <i class="fas fa-chevron-circle-down"></i></a>
	                		<a style="text-decoration:none;display:none" id="less" data-toggle="collapse" data-target="#demo" onclick="funCollapse('more')">View Less <i class="fas fa-chevron-circle-up"></i></a>
	                	</div> 
	                </div>
	              </div>
	             </div>
	             <br>
	               
	        
	       <div class="Card_Parent PolicyInformation mt-4">
	         <div class="card card-5">
	         	<div class="card container">
	         			<div class="card-body mb-4 mr-5">
	         				<div style="color:red;" align="center">
        				<b>
         					<br>
	         					<s:property value="%{referralMsg}"/>
	         					<s:hidden name="referralMsg"/>
         					<br>
        				<br>
	         				<lable>Your Quotation has been referred to our Underwriting team. You will be contacted shortly.</lable>
	         			</b>
	         				</div>
	         			</div>
	         		</div>
	         	</div>
	         </div>
	         <br>
	     <div class="row">
	    	 <div class="col-lg-2 col-md-3 offset-md-3 offset-lg-4 mb-3">
	    	 	<input type="submit" value="Back" id="initReport_Submit" name="submit" class="btn btn-sm btn-danger btn-block" onclick="checkfrom('getBack')"/>
	    	 </div>
	    	 <div class="col-lg-2 col-md-3 mb-3">
	    	 	<input type="submit" value="Proceed" id="initReport_Submit" name="submit" class="btn btn-sm btn-success btn-block" onclick="checkfrom('proceed')" disabled="true"/>
	    	 </div>
	     
	     </div>
	     
	   </div>
	       </div>
	     </s:if>
	       

</s:form>
<script type="text/javascript">
function funCollapse(type){
	if(type=='more'){
		$( '#less' ).attr('style','display:none;');
		$( '#more' ).attr('style','display:block;');
	}
	else if (type=='less'){
		$( '#more' ).attr('style','display:none;');
		$( '#less' ).attr('style','display:block;');
	}
}

function checkfrom(type){
	if(type=='getBack'){
		document.referralerror.customerId.value='<s:property value="customerId"/>';
		document.referralerror.applicationNo.value='<s:property value="applicationNo"/>';
		document.referralerror.policyType.value='<s:property value="policyType"/>';
		document.referralerror.vehicleId.value='<s:property value="vehicleId"/>';
		document.referralerror.currencyType.value='<s:property value="currencyType"/>';
		document.referralerror.action = "${pageContext.request.contextPath}/getBackVehicleMotor.action";
		document.referralerror.submit();
	}else{
		document.referralerror.action = "${pageContext.request.contextPath}/initReport.action";
		document.referralerror.submit();
	}
}
</script>
</body>
</html>