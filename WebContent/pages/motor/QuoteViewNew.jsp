<%@ page isELIgnored="false" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html <s:if test="locale.language == 'ar'">dir="rtl"</s:if> >
<head>
<script type="text/javascript">
</script>

<style type="">
@media only screen and (max-width: 992px) {
  
  /* Force table to not be like tables anymore */
  table,
  thead,
  tbody,
  th,
  td,
  tr {
    display: block;
  }
  /* Hide table headers (but not display: none;, for accessibility) */
  thead tr,
  tfoot tr {
    position: absolute;
    top: -9999px;
    left: -9999px;
  }
  td {
    /* Behave  like a "row" */
    border: none;
    border-bottom: 1px solid #eee;
    position: relative;
    padding-left: 50% !important;
  }
  td:before {
    /* Now like a table header */
    position: absolute;
    /* Top/left values mimic padding */
    top: 6px;
    left: 6px;
    width: 45%;
    padding-right: 10px;
    white-space: nowrap;
  }
  
  .table td:nth-child(1) {
      background: #ccc;
      height: 100%;
      top: 0;
      left: 0;
      font-weight: bold;
  }
  /*
	Label the data
	*/
  td:nth-of-type(1):before {
    content: "S.No";
  }	
  td:nth-of-type(2):before {
    content: "Vehicle Usage";
  }
  td:nth-of-type(3):before {
    content: "Make";
  }
  td:nth-of-type(4):before {
    content: "Model";
  }
  td:nth-of-type(5):before {
    content: "Type Of Body";
  }
  td:nth-of-type(6):before {
    content: "Vehicle Value";
  }
  
  .dataTables_length {
    display: none;
  }
  }

.inputBox {
  outline :none !important;
  border :none !important;
}
.inputBox : focus {
  outline :none !important;
  border :none !important;
}
        
</style>
</head>
<body>
 
<div id="loading" class="ajaxLoader" style="width:100%">
   <img id="loading-image" src="${pageContext.request.contextPath}/images/madisonSymbolLogo.png"/>
</div>
	 <div class="modal-header">
       <h5 class="modal-title">Quote Details</h5>
       <i class="far fa-times-circle mt-3" data-dismiss="modal"></i>
     </div>
	        
     <div class="modal-body">
     <s:set var="multiVehicleDtls" value="%{multiVehicleDetails}"/>
      <s:set var="totPremium" value="0"/>
       	<div class="row">
            <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
				<table class="table table-bordered table-hover" cellspacing="0" width="100%" id="tadaTable">
					<thead class="bluecolortable">
				        <tr>
							<th><s:text name="S.No." /></th>
							<th><s:text name="motor.vehicleUsage"/></th>
							<th><s:text name="motor.make" /></th>
							<th><s:text name="motor.model" /></th>
							<th><s:text name="motor.typeOfBody" /></th>
							<th><s:text name="motor.sumInsured" /></th>
						</tr>
					</thead>
					<tbody>
						<s:if test='#multiVehicleDtls.size()>0'>
							<s:iterator value="#multiVehicleDtls" var="view" status="status">
								<tr>
									<td><s:property value="#status.count" /></td>
									<td><s:property value="#view.VEHICLETYPE_DESC" /></td>
									<td><s:property value="#view.MAKE_NAME" /></td>					
									<td><s:property value="#view.MODEL_NAME" default=" " /> </td>
									<td><s:property value="#view.TYPE_OF_BODY_NAME" /></td>
									<td><s:property value="getText('{0,number,#,##0.00}',{#view.SUMINSURED_VALUE_LOCAL})"/></td>
								</tr>
							</s:iterator>
						</s:if>
						<s:else>
						    <tr>
				        		<td align="center" colspan="6">No Vehicle Details Found</td>
				        	</tr>
						</s:else>
					</tbody>
				</table>
			</div>
		</div>
		<div class="container PolicyPage">
		<div class="Card_Parent mt-4">
		<div class="premiumBorder mt-5">
	        <ul class="list-group QuotationDetails">
	            <li class="list-group-item list-group-item-primary">
	                <div class="row">
	                     <div class="col-md-6 col-6">
	                          <label class="LabelHeading">Description</label>
	                      </div>
	                      <div class="col-md-3 col-3">
	                           <label class="LabelHeading">Base Rate</label>
	                      </div>
	                      <div class="col-md-3 col-3"  align="right">
	                           <label class="LabelHeading">Premium</label>
	                      </div>
	                </div>
	            </li>
	            <hr>
	            <s:set name="groupId" value=""/>
				<s:set var="preAmt" value="0.0" scope="page"/>
	            <s:iterator value="premiumInfo" var="prInfo" status="stat">
		            <s:set name="groupId" value="%{#prInfo.GROUP_ID}"/>
	                <li class="list-group-item">
	                    <div class="row">
	                         <div class="col-md-6 col-6">
	                             <label class="LabelHeading"><s:property  value="%{#prInfo.GROUP_DESC_ENGLISH}"/></label>
	                             <s:hidden name="sI[%{#stat.count-1}]"  cssClass="inputBox" value="%{#prInfo.SUM_INSURED}" cssStyle="text-align:right;"/>
	                         </div>
	                         <div class="col-md-3 col-3">
	                             <label><s:textfield name="baseRate[%{#stat.count-1}]" cssClass="inputBox "  value="%{#prInfo.RATE}" size="11" cssStyle="text-align:center; width:100%;" maxLength="16" onkeyup="checkDecimals(this);" readonly="true"/></label>
	                         </div>
	                         <div class="col-md-3 col-3 "  align="right">
	                             	<label class="labelValues"><s:property value="getText('{0,number,#,##0.00}',{#prInfo.PREMIUM})"/>
								 		<s:set var="preAmt" value="%{@java.lang.Double@parseDouble(#attr.preAmt)+@java.lang.Double@parseDouble(#prInfo.PREMIUM)}" scope="page"/>
									</label>
	                         </div>
	                    </div>
	                </li>
		            <hr>
	            </s:iterator>
	            <li class="list-group-item">
                      <div class="row">
                          <div class="col-md-9 col-9">
                              <label class="LabelHeading"><s:text name="motor.Premium"/>  [<s:property value="currencyType"/>]</label>
                          </div>
                          <!-- <div class="col-md-3 col-3">
                              <label></label>
                          </div> -->
                          <div class="col-md-3 col-3 text-center">
                              <label class="labelValues"><s:textfield name="premium" id="premium" cssClass="inputBox "  value="%{getText('{0,number,0.00}',{#attr.preAmt})}" onkeyup="checkDecimals(this);" maxlength="11" cssStyle="text-align:right; width:100%;" readonly="true"/></label>
                          </div>
                      </div>
                  </li>
	              <hr>
				 <s:if test='#session.user1 == "admin" || quoteStatus == "RA"'>
                        <li class="list-group-item">
                            <div class="row">
                                <div class="col-md-6 col-6">
                                    <label class="LabelHeading"><s:text name="motor.loadingOrDiscountPremium"/>  [<s:property value="currencyType"/>]</label>
                                </div>
                                <div class="col-md-3 col-3">
                                   	<label><s:select list="#{'+':'+','-':'-'}" name="sign" onchange="getTotalPremium(this.form)" disabled="%{quoteStatus=='RA' && #session.user1 != 'admin'?'true':'false'}" cssClass="inputBox" cssStyle="width:100%;"/>&nbsp;&nbsp;&nbsp;</label>
                                </div>
                                <div class="col-md-3 col-3 text-center">
                                    <label class="labelValues">
                                    	<s:textfield name="loadOrDiscPremium" id="loadOrDiscPremium" cssClass="inputBox "  disabled="%{quoteStatus=='RA' && #session.user1 != 'admin'?'true':'false'}" onkeyup="checkDecimals4_2(this);getTotalPremium(this.form);" maxlength="11" cssStyle="text-align:right;width:100%;"/>
                         <s:if test='"+".equalsIgnoreCase(sign)'>
                         	<s:set var="preAmt" value="%{@java.lang.Double@parseDouble(#attr.preAmt)+@java.lang.Double@parseDouble(loadOrDiscPremium)}" scope="page"/>
                         </s:if>
                         <s:else>
                         	<s:set var="preAmt" value="%{@java.lang.Double@parseDouble(#attr.preAmt)-@java.lang.Double@parseDouble(loadOrDiscPremium)}" scope="page"/>
                         </s:else>
                                    </label>
                                </div>
                            </div>
                        </li>
			         <hr>
				 </s:if>
                        <li class="list-group-item">
                            <div class="row">
                                <div class="col-md-9 col-9">
                                    <label class="LabelHeading"><s:text name="motor.policyFee"/>  [<s:property value="currencyType"/>]</label>
                                </div>
                                <!-- <div class="col-md-3 col-3">
                                    <label></label>
                                </div> -->
                                <div class="col-md-3 col-3 text-center">
                                    <label class="labelValues"><s:textfield name="policyFee" id="policyFee" cssClass="inputBox "  onkeyup="checkDecimals(this);" maxlength="11" cssStyle="text-align:right; width:100%;" readonly="true"/></label>
                                </div>
                            </div>
                        </li>
                          <hr>
                          <li class="list-group-item">
                              <div class="row">
                                  <div class="col-md-9 col-9">
                                      <label class="LabelHeading"><s:text name="motor.totalPremiumPayable" />  [<s:property value="currencyType"/>]</label>
                                  </div>
                                  <!-- <div class="col-md-3 col-3">
                                      <label></label>
                                  </div> -->
                                  <div class="col-md-3 col-3 text-center">
                                      <label class="labelValues"><s:set var="totPremium" value="%{getText('{0,number,0.00}',{@java.lang.Double@parseDouble(#attr.preAmt)+@java.lang.Double@parseDouble(policyFee)})}"/>
											<s:textfield name="totalPremium" id="totalPremium" cssClass="inputBox "  value="%{#totPremium}" onkeyup="checkDecimals(this);" maxlength="11" cssStyle="text-align:right; width:100%;" readonly="true"/>
										</label>
                                  </div>
                              </div>
                          </li>
                         <s:if test='#session.user1 == "admin" || (!"".equals(adminRemarks)&&(adminRemarks!=null)&& #session.user1 != "admin")'>
                          	<hr>
                          	<div class="row">
                                  <div class="col-md-6 col-6">
                                      <label class="LabelHeading"><s:text name="motor.specialCondition"/></label>
                                  </div>
                                 
                                  <div class="col-md-6 col-6 text-center">
                                      <label class="labelValues">
                                      	<s:textarea name="adminRemarks" onkeyup="textLimit(this,'470')"  cssStyle="width:100%;" cols="50" rows="3" readonly="%{quoteStatus=='RA' && #session.user1 != 'admin'?'true':'false'}"/>
                                      </label>
                                  </div>
                              </div>
							</s:if>
                     	</ul>
                  </div>
		</div></div>
     </div>
	        
</body>
</html>