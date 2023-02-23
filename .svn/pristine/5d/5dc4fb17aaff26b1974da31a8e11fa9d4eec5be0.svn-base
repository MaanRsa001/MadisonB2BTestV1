<%@ page isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="sjg" uri="/struts-jquery-grid-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html <s:if test="locale.language == 'ar'">dir="rtl"</s:if> >
	<head>
		<script>
			function reloadGridScheme() { 
	            $.publish('reloadScheme');
	        } 
	        
			function funcedit(response, postdata){
	   			var success = true;
	   			var message = ""; 
	   			if(response.responseText==''){
	            	reloadGridScheme();
	            	success = false;
	            	message="success";
	            }else{
		            var json = eval('(' + response.responseText + ')');
		            if(json.actionErrors){
		            	success = false;
		                for(i=0; i < json.actionErrors.length; i++){
		                	message += json.actionErrors[i] + '<br/>'; 
		                }
		            }
				}
				 return [success,message];
	         } 
		</script>
	</head>
	<body>
		<table width="px;" border="0" align="center" cellpadding="0" cellspacing="0">
		  	<tr>
		    	<td>
		    		<table width="100%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#E5E5E5">
		      			<tr>
		        			<td bgcolor="#FFFFFF" style="padding:10px; background:#F8F8F8">
		        				<table width="100%" align="center">
							        <tr><td class="heading" width="100%"><s:text name="label.coverages.master"/></td></tr>
							 		<tr><td height="10px"></td></tr>
							 		<tr><td bgcolor="#ffffff">
								 		<table width="100%" border="0" align="center" cellpadding="4" cellspacing="0">
									        <tr><td width="5%"></td>
									        	<td width="90%">
												    <sjg:grid
												        id="coveragegrid" caption="Coverages Master" href="coverageHadminjson.action?option=coverage" pager="true" dataType="json" gridModel="gridList" reloadTopics="reloadCoverage"
												        rowList="10,15,20" rownumbers="true" width="800" navigator="true" navigatorView="false" navigatorDelete="false" 
												        navigatorAdd="false" navigatorEdit="false" navigatorSearch="true" navigatorRefresh="true" viewrecords="true" 
												    >
												        <sjg:gridColumn name="coverId" index="coverId" title="Coverage Id" sortable="true" search="true" searchoptions="{sopt:['cn','nc']}"/>
												        <sjg:gridColumn name="coverDisName" index="coverDisName" title="Coverage Name" sortable="true" search="true" searchoptions="{sopt:['cn','nc']}"/>
												        <sjg:gridColumn name="productName" index="productName" title="Product Name" sortable="true" search="true" searchoptions="{sopt:['cn','nc']}"/>
												        <sjg:gridColumn name="schemeName" index="schemeName" title="Scheme Name" sortable="true" search="true" searchoptions="{sopt:['cn','nc']}"/>
												        <sjg:gridColumn name="contentTypeName" index="contentTypeName" title="Content Type Name" sortable="true" search="true" searchoptions="{sopt:['cn','nc']}"/>
												    </sjg:grid>
									        	</td>
									 			<td width="5%"></td>
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