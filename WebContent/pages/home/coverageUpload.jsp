<%@ page isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
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
					<div class="tablerow">
						<s:include value="/pages/home/coverPremiumInfo.jsp"/>
					</div>
					<br class="clear" />
					<div class="tabBorder rEdge">
						<div class="tabsBg rEdge">
							<s:set value="0" var="iterat"/>
							<s:iterator value="premiumTabList" var="premium" status="status">
								<s:if test='%{#premium.SUM_INSURED!=null && "Y".equalsIgnoreCase(#premium.UPLOAD_OPTION)}'>
									<input type="button" class="tabBg rEdge" name="tabMainCover${status.count}" id='tabMainCover${status.count}' value="${coverages_display_name}" onclick="getTab('tabMainCover${status.count}','${iterat}','${COVERAGES_id}','')" />
									<s:set var="iterat" value="%{@java.lang.Integer@parseInt(#iterat)+1}"/>
								</s:if>
							</s:iterator>
							<s:iterator value="subPremiumTab" var="subprem" status="status"> 
								<s:if test='%{"Y".equalsIgnoreCase(#subprem.UPLOAD_OPTION)}'>
									<input type="button" class="tabBg rEdge" name="tabSubCover${status.count}" id='tabSubCover${status.count}' value="${coverages_display_name}" onclick="getTab('tabSubCover${status.count}','${iterat}','${COVERAGES_ID}','${COVERAGES_SUB_ID}')" />
									<s:set var="iterat" value="%{@java.lang.Integer@parseInt(#iterat)+1}"/>
								</s:if>
							</s:iterator>
							<input type="button" class="tabBg rEdge" name="tabunder" id='tabunder' value="Underwriting Questions" onclick="getTab('tabunder','','','')" />
							<br class="clear rEdge" />
						</div>
						<div>
							<s:if test='"tabunder".equalsIgnoreCase(tabInfo)'>
								<s:include value="/pages/home/uwMenu.jsp" />
							</s:if>
							<s:else>
								<s:include value="/pages/home/premiumSubList.jsp" />
							</s:else>
							<br class="clear" />
						</div>
					</div>			
				</div>		
			</div>	
		</div>
		<br class="clear" />
		<s:form name="tabSubmit" id="tabSubmit" action="">
			<s:hidden name="quoteNo"/>
			<s:hidden name="applicationNo"/>
			<s:hidden name="menuType"/>
			<s:hidden name="from"/>
			<s:hidden name="coverId" id="coverId"/>
			<s:hidden name="subCoverId" id="subCoverId"/>
			<s:hidden name="selectTab" id="selectTab"/>
			<s:hidden name="tabInfo" id="tabInfo" />
		</s:form>
		<script type="text/javascript">
			try{
				var tabId='<s:property value="tabInfo"/>';
				document.getElementById('<s:property value="tabInfo"/>').className="tabBgA rEdge";
				document.getElementById('<s:property value="tabInfo"/>').removeAttribute("onclick");
			}
			catch(err){}
			function getTab(tabInfo,selectTab,coverId,subCoverId){
				document.forms['tabSubmit'].action='tabRenderHome.action';
				document.forms['tabSubmit'].tabInfo.value=tabInfo;
				document.forms['tabSubmit'].selectTab.value=selectTab;
				document.forms['tabSubmit'].coverId.value=coverId;
				document.forms['tabSubmit'].subCoverId.value=subCoverId;
				document.forms['tabSubmit'].submit();
			}
		</script>		
	</body>
</html>