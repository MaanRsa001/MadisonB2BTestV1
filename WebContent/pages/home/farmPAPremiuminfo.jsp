<%@ page isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html <s:if test="locale.language == 'ar'">dir="rtl"</s:if> >
	<head>		
	</head>
	<body>
		<div class="table0">
			<div class="tablerow">
				<div class="table1">
					<div class="tablerow">
						<s:include value="/pages/home/generalInfo.jsp"/>
					</div>
					<div class="tablerow">
						<s:include value="/pages/home/coverPremiumInfo.jsp"/>
					</div>
					<br class="clear" />
					<s:form name="quotation" theme="simple" action="">
						<s:if test='!"".equalsIgnoreCase(referralRemarks) && !"admin".equalsIgnoreCase(userType) && !"RA".equalsIgnoreCase(menuType)'>
							<div class="panel panel-primary">
								<div class="panel-heading">
									<s:text name="home.referralInfo"/>
								</div>
								<div class="panel-body">
									<div style="color:red;font-size: 15px;">
										<b>
											<s:property value="referralRemarks"/>
										</b>
									</div>
								</div>
							</div>
						</s:if>
						<s:else>
							<div class="tablerow">
								<s:include value="/pages/home/premiumInfo.jsp"/>
							</div>
						</s:else>
						<div class="tablerow" style="margin-top: 5px;">
							<div class="boxcontent" align="center">
								<s:submit value="Back" cssClass="btn btn-sm btn-danger" onclick="fnsubmit('paCoverHome','');"/>
								<s:if test='%{"admin".equalsIgnoreCase(#session.usertype)}'>
									<s:submit value="Calculate" cssClass="btn btn-sm btn-primary" onclick="fnsubmit('updatepaPremiuminfoHome','calc');"/> &nbsp;&nbsp;&nbsp;
									<s:submit value="Proceed" cssClass="btn btn-sm btn-success" onclick="fnsubmit('updatepaPremiuminfoHome','update');"/>
								</s:if>
								<s:else>
									<s:submit value="Proceed" cssClass="btn btn-sm btn-success" onclick="fnsubmit('updatepaPremiuminfoHome','proceed');"/>
								</s:else>
							</div>
						</div>
						<s:hidden name="quoteNo"/>
						<s:hidden name="applicationNo"/>
						<s:hidden name="menuType"/>
						<s:hidden name="processType"/>
					</s:form>
				</div>		
			</div>	
		</div>
		<br class="clear"/>
		<script type="text/javascript">
		function fnsubmit(action, processType) {
			document.quotation.action = "${pageContext.request.contextPath}/" + action;
			document.quotation.processType.value = processType;
			document.quotation.submit();
		}
		</script>
	</body>
</html>