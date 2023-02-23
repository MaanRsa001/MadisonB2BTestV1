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
		
		<link href="css/styles.css" rel="stylesheet" type="text/css" />
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/cssbootstrap/bootstrap.min.css" />
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/dataTables/css/dataTables.responsive.css">
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/dataTables/css/dataTables.jqueryui.css">
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/dataTables/css/dataTables.tableTools.css">
		<link href="<%=request.getContextPath()%>/dataTables/css/jquery-start-ui.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="pages/admin/ratingEngine/menu.js"> </script>
		<script type="text/javascript" src="js/common.js"></script>
		<script type="text/javascript" src="js/tcal.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/dataTables/js/jquery-1.11.1.min.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/dataTables/js/jquery.dataTables.min.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/dataTables/js/dataTables.jqueryui.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/dataTables/js/dataTables.responsive.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/dataTables/js/dataTables.tableTools.js"></script>
		
		<link rel="stylesheet" href="ajs/styles.css" />
		<script type="text/javascript" src="ajs/qr_packed.js"></script>
		<script type="text/javascript" src="ajs/grid.js"></script>
		<script type="text/javascript" src="ajs/version.js"></script>
		<script type="text/javascript" src="ajs/detector.js"></script>
		<script type="text/javascript" src="ajs/formatinf.js"></script>
		<script type="text/javascript" src="ajs/errorlevel.js"></script>
		<script type="text/javascript" src="ajs/bitmat.js"></script>
		<script type="text/javascript" src="ajs/datablock.js"></script>
		<script type="text/javascript" src="ajs/bmparser.js"></script>
		<script type="text/javascript" src="ajs/datamask.js"></script>
		<script type="text/javascript" src="ajs/rsdecoder.js"></script>
		<script type="text/javascript" src="ajs/gf256poly.js"></script>
		<script type="text/javascript" src="ajs/gf256.js"></script>
		<script type="text/javascript" src="ajs/decoder.js"></script>
		<script type="text/javascript" src="ajs/qrcode.js"></script>
		<script type="text/javascript" src="ajs/findpat.js"></script>
		<script type="text/javascript" src="ajs/alignpat.js"></script>
		<script type="text/javascript" src="ajs/databr.js"></script>

		<script type="text/javascript">
		
	 	</script>
	 	<style type="text/css">
	 		.list-group-item {
			    padding: 10px 1px;
			    border: 0px solid #ddd;
			}
	 	</style>
	</head>
	<body>
		<s:form id="claimIntimation" name="claimIntimation" action="" theme="simple" enctype="multipart/form-data">
			<div class="">
				<div class="row">
					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
						<div align="left">
							<s:if test="hasActionErrors()">
								<font color="red"><s:actionerror/></font>
							</s:if>
							<font color="green"><s:actionmessage cssStyle="list-style: none;" /></font>
						</div>
					</div>
				</div>
				<div class="row vehDtl">
					<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
	<!-- 					<div class="panel panel-primary"> -->
						<div class="">
							<div class="Card_Parent">
					            <div class="card card-5">
									<div class="panel-heading" align="center">
										<h3><s:text name="QR Code Reader" /></h3><hr>
									</div>
									<div class="panel-body" style="padding-top: 0px;">
										<s:if test='"qrRead".equalsIgnoreCase(qrMode)'>
											<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
												<div id="container" align="center">
													<h3><u>Scan QR Code</u></h3>	
											    	<a id="btn-scan-qr">
														<img src="ajs/QRScanner.jpg">
													</a>
													
													<canvas hidden="" id="qr-canvas"></canvas>
												
												    <div id="qr-result" hidden="">
												        <b>Data:</b> <span id="outputData"></span>
												        <br/>
												        <div class="row">
												        </div>
												        <div class="row" align="center">
												        	<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
												        		<button type="button" class="btn btn-sm btn-success" onclick="processQrInfo();">Proceed</button>
												        	</div>
												        </div>
												    </div>
												</div>
											</div>
											<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
												<div align="center">
												    <h3><u>Upload QR Code</u></h3>	
											    	<a id="btn-upld-qr" onclick="showUpload('qrupload');">
														<img src="ajs/QRUpload.jpg">
													</a>
												</div>
											</div>
										</s:if>
										<s:elseif test='"qrUpload".equalsIgnoreCase(qrMode)'>
											<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
												<div class="row">
													<h5>&nbsp;&nbsp;&nbsp;<b>Choose QR Code File In Image Format to Upload</b></h5>
												</div>
												<div class="row">
													<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12"> 
														<s:file name="upload" class="inputBox" style="padding-bottom: 27px;padding-top: 6px;" accept='image/*'></s:file>
													</div>
												</div>
												<br>
												<div class="row" align="center">
													<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
														<button type="button"  class="btn btn-sm btn-danger" onclick="showUpload('qrpage');">Back</button>
														<button type="button"  class="btn btn-sm btn-success" onclick="uploadQR();">Upload</button>
													</div>
												</div>
											</div>
										</s:elseif>
									</div>
			<!-- 					</div> -->
								</div>
							</div>
						</div>
					</div>
					<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
	<!-- 					<div class="panel panel-primary"> -->
						<div class="" >
							<div class="Card_Parent">
					            <div class="card card-5">
									<div class="panel-heading" align="center">
										<h3><s:text name="Quick Renewal"/></h3><hr>
									</div>
									<div class="form-group">
										<div class="" style="padding: 0 15px 0 15px;" id="tone">
											<div class="row">
											<br/>
												<div class="col-md-5 col-xs-12">
													<div class="text"><b><s:text name="label.policynum" /><font color="red">*</font></b></div>
													<div class="tbox">
														<s:textfield name="policyNo" id="policyNo" cssClass="form-control" cssStyle="border color:black: 1px;" placeholder="Policy Number" maxlength="30" />
													</div>
												</div>
												<div class="col-md-2 col-xs-12 p-5" align="center">
													<s:text name="(OR)"/>
												</div>
												<div class="col-md-5 col-xs-12">
													<div class="text"><b><s:text name="label.renewal.regno" /><font color="red">*</font></b></div>
													<div class="tbox">
														<s:textfield name="vehPlateNo" id="vehPlateNo" cssClass="form-control alphanumericNoSpace" cssStyle="border color:black: 1px;" placeholder="Vehicle Plate Number" maxlength="50"/>
													</div>
												</div>
											</div>
											<br/>
											<%-- <div class="row">
												<div class="col-md-6 col-xs-12" align="center">
													<s:text name="(OR)"/>
												</div>
											</div> --%>
											<br/>
											<div class="row">
												<div class="col-md-6 col-xs-12">
													<div class="text"><b><s:text name="label.claim.phone" /><font color="red">*</font></b></div>
													<div class="tbox">
														<s:textfield name="mobileNo" id="mobileNo" cssClass="form-control numericOnly" cssStyle="border color:black: 1px;" placeholder="Mobile Number" maxlength="10" />
													</div>
												</div>
											</div>
											<br class="clear" />
											<br/>
											<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" >
												<div class="panel panel-primary">
													<div class="panel-body">
														<div class="row">
															<div class="col-xs-12 col-sm-12 col-md-4 col-lg-7" align="left">
																<div class="text"><b><s:text name="motor.captcha.note" /><font color="red">*</font></b></div>
																<div class="tbox">
																	<s:textfield name="captchavalue" id="captchavalue" value="" cssClass="inputBox tooltipContent" />
																</div>
															</div>
															<br/>
															<div class="col-xs-12 col-sm-12 col-md-4 col-lg-5" align="center">
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
											<div class="row">
												<div class="col-xs-12" align="center">
													<input type="button" class="btn btn-sm btn-success" value="Submit" onclick="funSubmit();"> &nbsp;&nbsp;&nbsp;
												</div>
											</div>
										</div>
									</div>
									</div>
								</div>
							</div>
	<!-- 					</div> -->
					</div>
				</div>
			</div>
			<s:hidden name="qrCodeResult" id="qrCodeResult"></s:hidden>
			<s:hidden name="mode" id="mode"></s:hidden>
			<s:hidden name="qrMode" id="qrMode"></s:hidden>
		</s:form>
		<script type="text/javascript" src="<%=request.getContextPath()%>/bootstrap/js/jquery-1.9.1.min.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/bootstrap/js/bootstrap.min.js"></script>	
		<script type="text/javascript">
    				
		function funSubmit(){
			var action='getRenewalQuickRenewal.action';
			document.claimIntimation.action=action;
			document.claimIntimation.submit();
			}
			
		function funBack(){
			document.claimIntimation.action='HomeUser.do';
			document.claimIntimation.submit();
		}
		
		function autotab(current,to){
					  if (current.getAttribute && current.value.length==current.getAttribute("maxlength"))
					  {
				      to.focus() 
     				 }
		}
		function blocksplchar(e){
			var k;
			 k = event.which || event.keyCode;
			return ((k > 64 && k < 91) ||(k > 96 && k<123 ));
		}
		function reloadCaptcha(){
   			$("#captcha").attr("src", "simpleCaptcha.jpg#" +new Date().getTime());
		}
		$('.alphanumericNoSpace').keypress(function(e) {
		    if ((e.which < 65 || e.which > 122) && (e.which < 48 || e.which > 57)) {
		               return false;
		    }
		});
		</script>
		<script type="text/javascript">
		
		const qrcode1 = window.qrcode;
		
		const video = document.createElement("video");
		const canvasElement = document.getElementById("qr-canvas");
		const canvas = canvasElement.getContext("2d");
		
		const qrResult = document.getElementById("qr-result");
		const outputData = document.getElementById("outputData");
		const btnScanQR = document.getElementById("btn-scan-qr");
		let scanning = false;
		
		
		qrcode.callback = (res) => {
			  if (res) {
			    outputData.innerText = res;
			    document.getElementById('qrCodeResult').value = res;
			    scanning = false;
		
			    video.srcObject.getTracks().forEach(track => {
			      track.stop();
			    });
		
			    qrResult.hidden = false;
			    btnScanQR.hidden = false;
			    canvasElement.hidden = true;
			  }
			};
		
			btnScanQR.onclick = () =>{
			  navigator.mediaDevices
			    .getUserMedia({ video: { facingMode: "environment" } })
			    .then(function(stream) {
			      scanning = true;
			      qrResult.hidden = true;
			      btnScanQR.hidden = true;
			      canvasElement.hidden = false;
			      video.setAttribute("playsinline", true); // required to tell iOS safari we don't want fullscreen
			      video.srcObject = stream;
			      video.play();
			      tick();
			      scan();
			    });
			};
		
			function tick() {
				  canvasElement.height = video.videoHeight;
				  canvasElement.width = video.videoWidth;
				  canvas.drawImage(video, 0, 0, canvasElement.width, canvasElement.height);
		
				  scanning && requestAnimationFrame(tick);
				}
			
			function scan() {
				  try {
				    qrcode.decode();
				  } catch (e) {
				    setTimeout(scan, 300);
				  }
				}
				
			function processQrInfo(){
				var action='processQRCodeQuickRenewal.action';
				document.getElementById('qrMode').value = 'qrRead';
				document.claimIntimation.action=action;
				document.claimIntimation.submit();
			}	
				
			function showUpload(option){
				if(option=="qrupload") {
					var action='qrUploadQuickRenewal.action';
					document.claimIntimation.action=action;
				}else {
					var action='pageQuickRenewal.action';
					document.claimIntimation.action=action;
				}
				document.claimIntimation.submit();
			}
			
			function uploadQR(){
			 	var action='qrCodeUploadQuickRenewal.action';
			 	document.getElementById('qrMode').value = 'qrUpload';
				document.claimIntimation.action=action;
				document.claimIntimation.submit();
			}
			
		</script>
	</body>
</html>