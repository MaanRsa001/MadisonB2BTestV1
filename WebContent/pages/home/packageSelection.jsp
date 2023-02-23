<%@ page isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html <s:if test="locale.language == 'ar'">dir="rtl"</s:if> >
	<head>
	</head>
	<body>
		<s:form name="PackageSelection" theme="simple" action="">
			<div class="table0">
				<div class="tablerow">
					<br/>
					<div class="boxcontent">
						<div class="panel panel-primary">
							<div class="panel-heading">
								<s:text name="home.packagePlan"/>
							</div>
							<div class="panel-body" align="center" style="min-height: 400px;">
								<table width="100%" class="footable responsive">
									<tbody>
									<tr>
										<td> <b> <s:text name="Select Plan" /> </b> </td>
										<s:iterator value="contentTypeList">
											<td align="center"><button type="button" class="btn btn-sm btn-primary" onclick='getHome(<s:property value="CONTENT_TYPE_ID"/>);'> <s:property value="CONTENT_DESCRIPTION"/> </button></td>
										</s:iterator>
									</tr>
									<tr>
										<td> <b> Premium <br/> [ in <s:property value="#session.BrokerDetails.CurrencyAbb"/> ] </b> </td>
										<s:iterator value="contentTypeList">
											<td style="text-align: center;"> <b> <s:property value="MINIMUM_PREMIUM"/> </b> </td>
										</s:iterator>										
									</tr>
									</tbody>
								</table>
								<br class="clear"/>
							</div>
						</div>
					</div>
					<br/>
				</div>	
			</div>
			<br class="clear"/>
			<s:hidden name="contentTypeId"/>
		</s:form>
		<script type="text/javascript" >
			function getHome(contentTypeId) {
				document.PackageSelection.contentTypeId.value = contentTypeId;
				document.PackageSelection.action = "initHome.action";
				document.PackageSelection.submit();
			}
			
			function fnViewTable() {
				document.getElementById('hideBtn').style.display = 'initial';
				document.getElementById('coverDetailTable').style.display = 'block';
				document.getElementById('viewBtn').style.display = 'none';
			}
			
			function fnHideTable() {
				document.getElementById('viewBtn').style.display = 'initial';
				document.getElementById('hideBtn').style.display = 'none';
				document.getElementById('coverDetailTable').style.display = 'none';
			}
		</script>
	</body>
</html>