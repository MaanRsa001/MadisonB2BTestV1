<%@ page isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html <s:if test="locale.language == 'ar'">dir="rtl"</s:if> >
	<head>
		<script>
			function getContentList(val, id){
				postRequest('${pageContext.request.contextPath}/ajaxAdminAhome.action?reqFrom='+id+'&schemeId='+val, id);
			}
	</script>
	</head>
	<body>
		<table width="950px;" border="0" align="center" cellpadding="0" cellspacing="0">
		  	<tr>
		    	<td>
		    		<table width="100%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#E5E5E5">
		      			<tr>
		        			<td bgcolor="#FFFFFF" style="padding:10px; background:#F8F8F8">
								<s:include value="/admin/left.jsp"></s:include>
							</td>
		        			<td bgcolor="#FFFFFF" style="padding:10px; background:#F8F8F8">
		        				<table width="100%" align="center">
							        <tr><td class="heading" width="100%"><s:text name="label.coverages.master"/></td></tr>
							 		<tr><td height="10px"></td></tr>
							 		<tr><td bgcolor="#ffffff">
								 		<table width="100%" border="0" align="center" cellpadding="4" cellspacing="0">
									        <tr><td width="15%"></td>
									        	<td width="23%"><s:text name="label.product.id"/></td>
									        	<td width="2%"></td>
									        	<td width="45"><s:property value="productName"/></td>
									 			<td width="15%"></td>
							        		</tr>
							        		<tr><td width="15%"></td>
									        	<td width="23%"><s:text name="label.scheme.id"/></td>
									        	<td width="2%"></td>
									        	<td width="45"><sj:select name="schemeId" list="schemeList" cssClass="input" headerKey="" headerValue="-Select-" listKey="" listValue="" onchange="getContentList(this.value,'contenTypes')" targets=""/></td>
									 			<td width="15%"></td>
							        		</tr>
							        		<tr><td width="15%"></td>
									        	<td width="23%"><s:text name="label.content.id"/></td>
									        	<td width="2%"></td>
									        	<td width="45" id="contenTypes"><s:select name="cobtentTypeId" list="#{}" cssClass="input" headerKey="" headerValue="-Select-"/></td>
									 			<td width="15%"></td>
							        		</tr>
							        	</table>
							    	</td></tr>
								</table>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</body>
</html>