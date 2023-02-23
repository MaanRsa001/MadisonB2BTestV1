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
		<script type="text/javascript" src="js/common.js"></script>
		<script type="text/javascript" src="js/tcal.js"></script>
		<link href="css/styles.css" rel="stylesheet" type="text/css" />
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/cssbootstrap/bootstrap.min.css" />
		<script type="text/javascript" src="pages/admin/ratingEngine/menu.js"> </script>
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/dataTables/css/dataTables.responsive.css">
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/dataTables/css/dataTables.jqueryui.css">
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/dataTables/css/dataTables.tableTools.css">
		<link href="<%=request.getContextPath()%>/dataTables/css/jquery-start-ui.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="<%=request.getContextPath()%>/dataTables/js/jquery-1.11.1.min.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/dataTables/js/jquery.dataTables.min.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/dataTables/js/dataTables.jqueryui.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/dataTables/js/dataTables.responsive.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/dataTables/js/dataTables.tableTools.js"></script>
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
	</head>
	<body>
		<s:form id="claimIntimation" name="claimIntimation" action="" theme="simple">
			<div class="container-fluid">
				<div class="row">
					<div class="col-xs-12" align="center">
						<s:if test="!'insert'.equalsIgnoreCase(mode) && !'success'.equalsIgnoreCase(mode)" >
							<div class="panel panel-primary" style="width: 502px;">
								<div class="panel-heading">
									<s:text name="label.claim.inti.screen" />
								</div>
								<div class="panel-body">
									<div class="row">
										<div align="left">
											<s:if test="hasActionErrors()">
												<font color="red"><s:actionerror/></font>
											</s:if>
											<font color="green"><s:actionmessage cssStyle="list-style: none;" /></font>
										</div>
<!--									<s:if test="!'insert'.equalsIgnoreCase(mode)"></s:if>-->
										<div class="col-xs-12 col-sm-10 col-md-10 col-lg-12" align="left">
											<div class="form-group">
												<label>
													<s:text name="label.your.name" /><font color="red">*</font>
												</label>
												<s:textfield name="name" cssClass="form-control" data-content="Your Name" maxlength="150" onkeypress="return blockSpecialChar(event);"></s:textfield>
											</div>
										</div>
										<div class="col-xs-12 col-sm-10 col-md-10 col-lg-12" align="left">
											<div class="form-group">
												<label><s:text name="label.nrc.passport.num" /> <font color="red">*</font> </label>
												<s:textfield name="passport"  id="passport" cssClass="form-control"  maxlength="10" onkeypress="return blocksplchar(event);" ></s:textfield>
											</div>
										</div>
										<div class="col-xs-12 col-sm-10 col-md-10 col-lg-12" align="left">
											<div class="form-group">
												<label> <s:text name="label.claim.phone" /><font color="red">*</font> </label>
												<s:textfield name="phone" cssClass="form-control decimalOnly"  maxlength="10"></s:textfield>
											</div>
										</div>
										<div class="col-xs-12 col-sm-10 col-md-10 col-lg-12" align="left">
											<div class="form-group">
												<label><s:text name="label.vehicle.reg.num" /><font color="red">*</font></label>
												<s:textfield name="vehicleRegNo" maxlength="20" cssClass="form-control alphanumeric"  id="policyNo" onkeypress="return blocksplchar(event);" ></s:textfield>
											</div>
										</div>
										<div class="col-xs-12 col-sm-10 col-md-10 col-lg-12" align="left" style="width: 500px;height: 70px;">
											<label><s:text name="label.policynum" /></label>
											<div class="row" align="center">
												<s:textfield name="policyNo1" id="policyNo1" 						cssStyle="width:17%" maxLength="1" onkeyup="autotab(this, this.form.policyNo2);" onkeypress="return blocksplchar(event);"/> /
												<s:textfield name="policyNo2" id="policyNo2" cssClass="decimalOnly" cssStyle="width:20%" maxLength="4" onkeyup="checkDecimals6_0(this);autotab(this, this.form.policyNo3)" /> /
												<s:textfield name="policyNo3" id="policyNo3" cssClass="decimalOnly" cssStyle="width:30%" maxLength="10" onkeyup="autotab(this, this.form.policyNo4)" /> /
												<s:textfield name="policyNo4" id="policyNo4" cssClass="decimalOnly" cssStyle="width:20%" maxLength="4" onkeyup="checkDecimals6_0(this);"/>
											</div>
										</div>
										<div class="col-xs-12 col-sm-10 col-md-10 col-lg-12" align="left">
											<div class="form-group">
												<label><s:text name="label.date.accident" /><font color="red">*</font></label>
												<s:textfield name="dateOfAccident" cssClass="form-control datepicker" data-date-end-date="0d" id="effectiveDate"></s:textfield>
											</div>
										</div>
									</div>
									<br class="clearFix"/>
									<br class="clearFix"/>
										<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" >
											<div class="panel panel-primary">
												<div class="panel-body">
													<div class="row">
														<div class="col-xs-12 col-sm-12 col-md-4 col-lg-7" align="left">
															<div class="text"><s:text name="motor.captcha.note" /></div>
															<div class="tbox">
																<s:textfield name="captchavalue" id="captchavalue" value="" cssClass="inputBox tooltipContent" />
															</div>
														</div>
														<div class="col-xs-12 col-sm-12 col-md-4 col-lg-5" align="right">
															<%	Captcha captcha = new Captcha.Builder(200, 50).addText().addBackground().addNoise().gimp().addBorder().build(); %>
												            <div class="captchaBg" style="width: 100px; float: left;">
												            	<img id="captcha" src="<c:url value="simpleCaptcha.jpg"  />" width="100">
												            </div>
												            <a href="#" onclick="reloadCaptcha();" style="float: left;"><img src="<%=request.getContextPath()%>/images/reload.png" alt="reload" width="40" height="40"/> </a>
														</div>
													</div>
												</div>
											</div>
										</div>
								</div>
							</div>
						</s:if>
						<s:else>
							<div class="panel panel-primary" style="width: 502px;">
								<div class="panel-heading">
									<s:text name="label.claim.inti.screen" />
								</div>
								<div class="panel-body">
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
			</div>
		</s:form>
		<script type="text/javascript" src="<%=request.getContextPath()%>/bootstrap/js/jquery-1.9.1.min.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/bootstrap/js/bootstrap.min.js"></script>	
		<script type="text/javascript">
				var input = document.getElementById('passport');
				input.onkeyup = function(){
    				this.value = this.value.toUpperCase();}

				function funSubmit(mode,id){
					var action="";
					//SerialQuarter=val
					if(mode=='insert')
						{
						action='insertClaimClaimIntimation.action?mode='+mode;
						}
					else{
						alert("Action is empty");
						}
						document.claimIntimation.action=action;
						document.claimIntimation.submit();
					}

				function funBack(){
					document.claimIntimation.action='addClaimClaimIntimation.action';
					document.claimIntimation.submit();
				}

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