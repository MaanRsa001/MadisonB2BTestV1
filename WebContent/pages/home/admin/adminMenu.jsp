<%@ page isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html <s:if test="locale.language == 'ar'">dir="rtl"</s:if> >
	<body>
		<table width="950px;" border="0" cellpadding="0" cellspacing="0">
		  	<tr>
		    	<td>
		    		<table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#E5E5E5">
		      			<tr>
		        			<td bgcolor="#FFFFFF" style="padding:10px; background:#F8F8F8" width="213px;">
		        				<table width="100%">
		        					<tr><td><s:include value="/admin/left.jsp"></s:include></td></tr>
		        				</table>
							</td>
							<td width="1"></td>
		        			<td bgcolor="#FFFFFF" style="padding:10px; background:#F8F8F8" width="735px;">
		        				<table width="100%">
		        					<tr>
		        						<td>
		        							<sj:tabbedpanel id="premiumTab" cache="false" selectedTab="%{selectTab}">
		        								<sj:tab id="tab0" href="coverageHadmin.action?&selectTab=0" key="label.coverages.master"/>
		        								<sj:tab id="tab1" href="coveragesListHadmin.action?&selectTab=1" key="label.sub.coverages.master"/>
		        								<sj:tab id="tab2" href="schemeHadmin.action?&selectTab=2" key="label.scheme.master"/>
		        								<sj:tab id="tab3" href="coveragesListHadmin.action?&selectTab=3" key="label.content.master"/>
		        								<sj:tab id="tab4" href="coveragesListHadmin.action?&selectTab=4" key="label.coverages.entry"/>
		        								<sj:tab id="tab5" href="coveragesListHadmin.action?&selectTab=5" key="label.pdf.master"/>
										    </sj:tabbedpanel>
		        						</td>
		        					</tr>
		        				</table>
		        			</td>
		        		</tr>
					</table>
				</td>
			</tr>
		</table>
	</body>
</html>