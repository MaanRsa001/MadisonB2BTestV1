<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>
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
	 <style type="text/css">
	 .tableColWidth {
	 	min-width: 100px;
	 	max-width: 200px;
	 	width: 100px;
	 }
	 </style>	
</head>
<body>
<s:form name="form1" theme="simple">
<div class="container-fluid">
	<div class="row">
		<div class="col-xs-12">
			<div class="panel panel-primary">
				<div class="panel-heading">
					<s:text name="Road Assistant Register"/>
				</div>				
				<div class="panel-body">
					<s:if test="hasActionErrors()">
					<div class="row">
						<font color="red"><s:actionerror cssStyle="list-style: none;"/></font>
					</div>
					<br class="clear" />
					</s:if>
					<s:if test=" reqFrom != 'success'">
						<div class="row">
							<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
								<div class="text"><s:text name="label.assist"/><font color="red">*</font></div>
								<div class="tbox">
									<s:select name="assistantType" id="assistantType" list="TypeofAssistantList" listKey="ITEM_VALUE" listValue="ITEM_VALUE" headerValue="---Select Value---"  cssClass="inputBoxS"/>
								</div>
							</div>
							<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
								<div class="text"><s:text name="motor.customerName"/><font color="red">*</font></div>
								<div class="tbox">
									<s:textfield name="custName" id="custName" cssClass="form-control" />
								</div>
							</div>
							<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
								<div class="text"><s:text name="label.email"/><font color="red">*</font></div>
								<div class="tbox">
									<s:textfield name="email" id="email" cssClass="form-control"/>
								</div>
							</div>
							<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
								<div class="text"><s:text name="label.mobile"/><font color="red">*</font></div>
								<div class="tbox">
									<s:textfield name="mobileNo" id="mobileNo" cssClass="form-control" maxlength="10"/>
								</div>
							</div>
							<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
								<div class="text"><s:text name="label.policyNo"/><font color="red">*</font></div>
								<div class="tbox">
									<s:textfield name="policyNo" id="policyNo" cssClass="form-control" />
								</div>
							</div>
							<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
								<div class="text"><s:text name="label.loc"/><font color="red">*</font></div>
								<div class="tbox">
									<s:textarea name="loc" id="loc" cssClass="form-control" rows="2"/>
								</div>
							</div>
							<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
								<div class="text"><s:text name="label.desc"/><font color="red">*</font></div>
								<div class="tbox">
									<s:textarea name="desc" id="desc" cssClass="form-control" rows="2" />
								</div>
							</div>
							</div>
							<%--<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
								<div class="text"><s:text name="label.long"/></div>
								<div class="tbox">
									<s:textfield name="longitude" id="longitude" cssClass="inputBox" />
								</div>
							</div>
							<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
								<div class="text"><s:text name="label.lat"/></div>
								<div class="tbox">
									<s:textfield name="latitude" id="latitude" cssClass="inputBox"/>
								</div>
							</div>
							<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
								<div class="text"><s:text name="label.desc"/><font color="red">*</font></div>
								<div class="tbox">
									<s:textarea name="desc" id="desc" cssClass="inputBox"/>
								</div>
							</div>
						
						<input type="button" onclick="geoFindMe();" class="btn btn-sm btn-success" value="Get Location" />
						<div id="out"></div>
						--%>
						<br class="clear" />
						<br class="clear" />
						<div class="row" align="center">
							<div><br/>
								<input type="button" onclick="funSubmit();" class="btn btn-sm btn-success" value="Submit" />
							</div>
						</div>
					</s:if>
					<s:if test=" reqFrom == 'success'">
					<div class="row" align="center">
						<font color="green"><s:actionmessage cssStyle="list-style: none;"/> </font>
							<div>
							 	<font> Thank you for your Request. Your Reference number is <s:property value="refNo"/> . Keep it for your reference. Customer Care will be in touch shortly.</font> 
							</div>
							<div><br/>
								<input align="middle"  type="button" onclick="fnBack();" class="btn btn-sm btn-danger" value="Back" />
							</div>
					<br class="clear" />
					</div>
					</s:if>
				</div>
			</div>
		</div>
	</div>
</div>
</s:form>
</body>
<script type="text/Javascript" >
function fnBack(){
		document.form1.action='getRoadAssistantMotor.action';
	    document.form1.submit();
}
function funSubmit(){
		document.form1.action='insRoadAssistantMotor.action';
		document.form1.submit();
}
<%--function geoFindMe() {
	  var output = document.getElementById("out");

	  if (!navigator.geolocation){
	    output.innerHTML = "<p>Geolocation is not supported by your browser</p>";
	    return;
	  }

	  function success(position) {
	    var latitude  = position.coords.latitude;
	    var longitude = position.coords.longitude;

	    output.innerHTML = '<p>Latitude is ' + latitude + '° <br>Longitude is ' + longitude + '°</p>';

	    var img = new Image();
	    img.src = "https://maps.googleapis.com/maps/api/staticmap?center=" + latitude + "," + longitude + "&zoom=13&size=300x300&sensor=false";

	    output.appendChild(img);
	  };

	  function error() {
	    output.innerHTML = "Unable to retrieve your location";
	  };

	  output.innerHTML = "<p>Locating…</p>";

	  navigator.geolocation.getCurrentPosition(success, error);
	}

--%></script>
</html>