<%@page contentType="text/html" pageEncoding="UTF-8" isELIgnored="false"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta name="description" content="">
	<meta name="keywords" content="Maan Sarovar">	
	<meta name="author" content="">
	 <!-- CSS STYLE-->
    <style type="text/css">
    .textfield33 {
    	padding: 0 5px 0 5px;
    }
    .textfield50 {
    	padding: 0 5px 0 5px;
    }
    .form-control {
    	width: 100%;
    }
    .conBody {			
		float: left;
		width: 100%;
	}
	.conRight {			
		float: left;
		width: auto;
	}
    </style>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js"></script>
    <script type="text/javascript">
    	function stopRKey(evt) { 
		  var evt = (evt) ? evt : ((event) ? event : null); 
		  var node = (evt.target) ? evt.target : ((evt.srcElement) ? evt.srcElement : null); 
		  if ((evt.keyCode == 13) && (node.type=="text"))  {return false;} 
		} 
		document.onkeypress = stopRKey;
	
		function fnCall(from){
			if(from=='edit')
				document.info.action = from+"BrokerMgm.action?mode=edit";
			else if(from=='userDetail')
				document.info.action = "getABListUserMgm.action?mode1=broker";
			else if(from=='edituser')
				document.info.action = "editUserMgm.action?mode1=broker&mode=edit";
			else if(from=='viewuser')
				document.info.action = "viewUserMgm.action?mode1=broker";
			else if(from=='customerDetail')
				document.info.action = "getABListCustomerMgm.action?mode1=broker";
			else if(from=='referal')
				document.info.action = "getOCListReferal.action";
			else if(from=='openCover')
				document.info.action = "opencoverOC.action";
			else
				document.info.action = from+"BrokerMgm.action";
			document.info.submit();
		}
		function passwordStrength(password){
			var desc = new Array();
			desc[0] = "Very Weak";
			desc[1] = "Weak";
			desc[2] = "Better";
			desc[3] = "Medium";
			desc[4] = "Strong";
			desc[5] = "Strongest";
			var score   = 0;
			if (password.length > 6) score++;
			if ( ( password.match(/[a-z]/) ) && ( password.match(/[A-Z]/)) ) score++;
			if ( password.match(/\d+/)) score++;
			if ( password.match(/.[@,#,$,%]/))score++;
			if ( (password.length > 10) && (password.match(/.[@,#,$,%]/)) && (password.match(/[a-z]/) ) && (password.match(/[A-Z]/)) && (password.match(/\d+/)))score++;
			document.getElementById("passwordDescription").innerHTML = desc[score];
			document.getElementById("passwordStrength").className = "strength" + score;
		}
    </script>
</head>
<body>
<div id="bodyContent">
	<s:form id="info" name="info" method="post" action="updateUserReg" theme="simple">
	
	<div class="panel panel-primary">
		<div class="panel-body">
			<s:if test="'successNew'.equals(display)">
			<div align="center">
				<s:label key="user.new.success"/> <br/>
				<input type="button" onclick="fnsubmit('back','modifyUserReg', this.form)" name="back" class="btn btn-sm btn-danger" value="Back" />
			</div>
			</s:if>
			<s:elseif test="'successUpdate'.equals(display)">
			<div align="center">
				<s:label key="user.update.success"/> <br/>
				<input type="button" onclick="fnsubmit('back','modifyUserReg', this.form)" name="back" class="btn btn-sm btn-danger" value="Back" />
			</div>
			</s:elseif>
			<s:else>
				<div class="conBody">
					<s:if test='!"product".equals(mode1) && !"login".equals(mode1)'>
						<span style="color:red;"><s:actionerror/> </span>
						<span style="color:green;"><s:actionmessage/> </span>					
					</s:if>
					<div class="row">
						<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">					
							<s:include value="/pages/customer/customerDetailsMotor.jsp"/>
						</div>
					</div>
					<div align="center">
						<s:submit name="submit2" cssClass="btn btn-sm btn-success" value="Submit" />
					</div>
				</div>
			</s:else>
		</div>
	</div>
	<s:hidden name="agencyCode"/>
	<s:hidden name="mode"/>
	<s:hidden name="mode1"/>
	<s:hidden name="uagencyCode"/>
	<s:hidden name="borganization"/>
	<s:hidden name="broker" value='%{getText("B2C_AGENCY_CODE")}'/>
	
	</s:form>
	<br class="clear"/>	
</div>

<script type="text/javascript">
$(function() {
	try {
		
		$('#udob').datepicker({
			todayHighlight: true,
			format: "dd/mm/yyyy",
			defaultViewDate: "01/01/1960",
			//endDate: "-18y"
		}).on('changeDate', function(e){
		    $(this).datepicker('hide');
		});
	} catch(err){}	
});

function forward1(agcode, id, value, mode1){
	postRequest('${pageContext.request.contextPath}/getUserAjaxUserMgm.action?reqFrom='+id+'&agencyCode='+agcode+'&productID='+value+'&mode1='+mode1, id);
}
   	
function fnsubmit(from, action, frm){
	frm.action=action+".action";
	frm.submit();
}
</script>
</body>
</html>