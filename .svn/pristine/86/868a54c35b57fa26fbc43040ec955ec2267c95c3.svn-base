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
						<s:include value="/pages/home/generalInfo.jsp"></s:include>
					</div>
					<br class="clear" />
					<div class="tablerow">
						<s:include value="/pages/home/premiumInfo.jsp"/>
					</div>
					<s:form name="quotation" theme="simple" action="">
						<div class="tablerow" style="margin-top: 5px;">
							<div class="boxcontent" align="center">
								<s:if test='"Y".equals(tractorCoverStatus)'>
									<s:submit cssClass="btn btn-sm btn-danger" value="Back" onclick="fnsubmit('tractorPremiuminfoHome');"/>
								</s:if>
								<s:elseif test='"Y".equals(paCoverStatus)'>
									<s:submit cssClass="btn btn-sm btn-danger" value="Back" onclick="fnsubmit('paPremiuminfoHome');"/>
								</s:elseif>
								<s:else>
									<s:submit cssClass="btn btn-sm btn-danger" value="Back" onclick="fnsubmit('getBackHome');"/>
								</s:else>
								<s:submit value="Submit" cssClass="btn btn-sm btn-success" onclick="fnsubmit('tabRenderHome');"/> 
								<br/>
							</div>
						</div>
						<s:hidden name="quoteNo"/>
						<s:hidden name="applicationNo"/>
						<s:hidden name="menuType"/>
					</s:form>
				</div>		
			</div>	
		</div>
		<br class="clear"/>
		<%--
		<script type="text/javascript" >
		
		$.subscribe('tabchange', function(event,data){
		<s:if test="hasActionError()">
			alert('Due to following Errors the given values are not saved/updated.<br/><s:actionerror/>');
		</s:if>
		});
		</script>
		--%>
		<script type="text/javascript">
		function fnsubmit(action) {
			document.quotation.action = "${pageContext.request.contextPath}/" + action;
			document.quotation.submit();
		}
		</script>
	</body>
</html>