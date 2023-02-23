<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ page import="nl.captcha.Captcha"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
	 <style type="text/css">
	 	.PolicyPage .card {
                padding: 20px 300px 50px 300px;
                border: 0px !important
            }
	 </style>
	</head>
	<body>
		<s:form id="claimIntimation" name="claimIntimation" action="" theme="simple">
			
			<div class="container PolicyPage">
				<div class="row">
					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
						<span style="color:red;"><s:actionerror/></span>
					</div>
				</div>
		        <div class="Card_Parent PolicyInformation">
			        <s:if test="!'insert'.equalsIgnoreCase(mode) && !'success'.equalsIgnoreCase(mode)" >
			            <div class="card">
			            	<div class="row">
			                	<div class="col-md-12">
			                		<h3><s:text name="label.claim.inti.screen" /></h3><hr>
		                		</div>
		               		</div>
		               		<div class="row">
								<div class="col-xs-12 col-sm-10 col-md-10 col-lg-12" align="left">
									<div class="form-group">
										<label class="labelHeader">
											<s:text name="label.your.name" /><font color="red">*</font>
										</label>
										<s:textfield name="name" cssClass="form-control" data-content="Your Name" maxlength="150" onkeypress="return blockSpecialChar(event);"></s:textfield>
									</div>
								</div>
								<div class="col-xs-12 col-sm-10 col-md-10 col-lg-12" align="left">
									<div class="form-group">
										<label class="labelHeader"><s:text name="label.nrc.passport.num" /> <font color="red">*</font> </label>
										<s:textfield name="passport"  id="passport" cssClass="form-control"  maxlength="10" onkeypress="return blocksplchar(event);" ></s:textfield>
									</div>
								</div>
								<div class="col-xs-12 col-sm-10 col-md-10 col-lg-12" align="left">
									<div class="form-group">
										<label class="labelHeader"> <s:text name="label.claim.phone" /><font color="red">*</font> </label>
										<s:textfield name="phone" cssClass="form-control decimalOnly"  maxlength="10"></s:textfield>
									</div>
								</div>
								<div class="col-xs-12 col-sm-10 col-md-10 col-lg-12" align="left">
									<div class="form-group">
										<label class="labelHeader"><s:text name="label.vehicle.reg.num" /><font color="red">*</font></label>
										<s:textfield name="vehicleRegNo" maxlength="20" cssClass="form-control alphanumeric"  id="policyNo" onkeypress="return blocksplchar(event);" ></s:textfield>
									</div>
								</div>
								
								<div class="col-md-12">
	                            	<label class="labelHeader"><s:text name="label.policynum"/><font color="red">*</font></label>
	                                <div class="input-group mb-3">
	                                    <s:textfield name="policyNo1" id="policyNo1" class="form-control " cssStyle="width:10%" maxLength="1" onkeyup="autotab(this, this.form.policyNo2);" onkeypress="return blocksplchar(event);" />&nbsp;/&nbsp;
	  									<s:textfield name="policyNo2" id="policyNo2" class="form-control decimalOnly" cssStyle="width:20%" maxLength="4" onkeyup="checkDecimals6_0(this);autotab(this, this.form.policyNo3)"/>&nbsp;/&nbsp;
	  									<s:textfield name="policyNo3" id="policyNo3" class="form-control decimalOnly" cssStyle="width:40%" maxLength="10" onkeyup="autotab(this, this.form.policyNo4)"/>&nbsp;/&nbsp;
	  									<s:textfield name="policyNo4" id="policyNo4" class="form-control decimalOnly" cssStyle="width:20%" maxLength="4" onkeyup="checkDecimals6_0(this);"/>
	                                </div>
	                            </div>
								<div class="col-xs-12 col-sm-10 col-md-10 col-lg-12 mt-2" align="left">
									<div class="form-group">
										<label class="labelHeader"><s:text name="label.date.accident" /><font color="red">*</font></label>
										<s:textfield name="dateOfAccident" cssClass="form-control datePicker" data-date-end-date="0d" id="dateOfAccident"></s:textfield>
									</div>
								</div>
		               		</div>
			            </div>
		            </s:if>
		            <s:else>
		            <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
	               		<div class="col-md-12">
	               			<h3><s:text name="label.claim.inti.screen" /></h3><hr>
						</div>
						<div class="col-md-12 mt-4">
							<font color="green"><s:actionmessage cssStyle="list-style: none;" /></font>
						</div>
					</div>
					</s:else>
					<br class="clear" />
					<s:if test='mode != "insert" && mode != "success"'>
						<div class="row">
							<div class="col-xs-12 col-sm-12 col-md-12" align="center">
								<input type="button" class="btn btn-sm btn-danger" value="Back" onclick="funBack();" />
								<input type="button" class="btn btn-sm btn-success" value="Submit" onclick="funSubmit('insert','');"> &nbsp;&nbsp;&nbsp;
							</div>
						</div>
					</s:if>
					<s:hidden name="claimId" />
					<s:hidden name="productId" />
	            </div>
            </div>
			
		</s:form>

		<script type="text/javascript">
				var input = document.getElementById('passport');
				input.onkeyup = function(){
    				this.value = this.value.toUpperCase();}

				function funSubmit(mode,id){
					var action="";
					//SerialQuarter=val
					if(mode=='insert')
						{
						action='insertB2BClaimClaimIntimation.action?mode='+mode;
						}
					else{
						alert("Action is empty");
						}
						document.claimIntimation.action=action;
						document.claimIntimation.submit();
					}

				function funBack(){
					document.claimIntimation.action='HomeUser.action';
					document.claimIntimation.submit();
				}
				
				/*$(function() {
					try {
					var dt = new Date();
					dt.setFullYear(new Date().getFullYear()-1);
					
					$('.datePicker').datepicker({
						todayHighlight: true,
						format: "dd/mm/yyyy"
					}).on('changeDate', function(e){
					    $(this).datepicker('hide');			    
					});
				} catch(err) {console.error(err);}
				});*/
				
				$(function() {
					try {
					var dt = new Date();
					dt.setFullYear(new Date().getFullYear()-1);
					
					$('#dateOfAccident').datepicker({
						todayHighlight: true,
				       	format: "dd/mm/yyyy",
					  	viewMode: "years",
					  	startDate: dt,
					  	endDate: "0d"
					}).on('changeDate', function(e){
				           $(this).datepicker('hide');
				       });
					
					
					$('.datePicker').datepicker({
						todayHighlight: true,
						format: "dd/mm/yyyy"
					}).on('changeDate', function(e){
					    $(this).datepicker('hide');			    
					});
				} catch(err) {console.error(err);}
				});
				$(function() {
					try {
						$('.datePicker').datepicker({
							todayHighlight: true,
							format: "dd/mm/yyyy"
						}).on('changeDate', function(e){
						    $(this).datepicker('hide');			    
						});
					} catch(err) {console.error(err);}
				});
				
				function reloadCaptcha(){
	    			$("#captcha").attr("src", "simpleCaptcha.jpg#" +new Date().getTime());
				}
				
				function blockSpecialChar(e){
					var k;
					 k = event.which || event.keyCode;
					return ((k > 64 && k < 91) ||(k > 96 && k<123 )|| k == 8 || k == 32 || k == 46);
				}
				
				function blocksplchar(e){
					var k;
					 k = event.which || event.keyCode;
					return ((k > 64 && k < 91) ||(k > 96 && k<123 )|| k == 8 || k == 32 || (k  > 47 && k < 58));
				}

				function autotab(current,to){
  					  if (current.getAttribute && current.value.length==current.getAttribute("maxlength"))
  					  {
  				      to.focus() 
       				 }
				}
		</script>
	</body>
</html>