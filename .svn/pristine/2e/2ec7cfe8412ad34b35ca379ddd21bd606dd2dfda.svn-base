<%@ page isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="sjg" uri="/struts-jquery-grid-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html <s:if test="locale.language == 'ar'">dir="rtl"</s:if> >
	<head>
		<script><%--
			function isError(text) {
				if(text.indexOf('ERROR') >= 0) { return [false, text]; }
				return [true,''];
			}
		
			//$.publish("reloadMyGridTopic");
		--%>
		
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
		<table width="px;" border="0" cellpadding="0" cellspacing="0">
		  	<tr>
		    	<td>
		    		<table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#E5E5E5">
		      			<tr>
		        			<td bgcolor="#FFFFFF" style="padding:10px; background:#F8F8F8">
		        				<table width="100%">
							        <tr><td class="heading" width="100%"><s:text name="label.scheme.master"/></td></tr>
							 		<tr><td height="10px"></td></tr>
							 		<tr><td bgcolor="#ffffff">
								 		<table width="100%" border="0" cellpadding="4" cellspacing="0">
									        <tr><td width="5%"></td>
									        	<td width="90%">
									        		<s:url id="editurl" action="editSchemeHadmin" />
												    <sjg:grid
												        id="schemegrid" caption="Scheme Master" href="schemeHadminjson.action?option=scheme" pager="true" dataType="json" gridModel="gridList" reloadTopics="reloadScheme"
												        rowList="10,15,20" rownumbers="true" width="800" navigator="true" navigatorView="false" navigatorDelete="true"
												        navigatorAdd="true" navigatorEdit="true" navigatorSearch="true" navigatorRefresh="true" viewrecords="true" editurl="%{editurl}"
												        navigatorEditOptions="{width:450,height:350, reloadAfterSubmit:true, afterSubmit:funcedit}" 
												        navigatorAddOptions="{width:450,height:350, reloadAfterSubmit:true, afterSubmit:funcedit}"
												        navigatorDeleteOptions="{width:450,height:350, reloadAfterSubmit:true, afterSubmit:funcedit}"
												    >
												        <sjg:gridColumn name="schemeId" index="schemeId" title="Scheme Id" sortable="true" search="true" searchoptions="{sopt:['cn','nc','eq','ne']}" editable="true" editrules="{number: true, required: true, readonly: true}"/>
												        <sjg:gridColumn name="schemeName" index="schemeName" title="Scheme Name" sortable="true" search="true" searchoptions="{sopt:['cn','nc','eq','ne']}" editable="true" editrules="{required: true}"/>
												        <sjg:gridColumn name="displayOrder" index="displayOrder" title="Display Order" sortable="true" search="true" searchoptions="{sopt:['cn','nc','ne','nq']}" editable="true" editrules="{number: true, required: true}" editrules="{number: true}"/>
												        <sjg:gridColumn name="productName" index="productName" title="Product Name" sortable="true" search="true" searchoptions="{sopt:['cn','nc','eq','ne']}" editable="true" edittype="select" editoptions="%{listSelect}" editrules="{required: true}"/>
												        <sjg:gridColumn name="remarks" index="remarks" title="Remarks" sortable="true" search="true" searchoptions="{sopt:['cn','nc','eq','ne']}"  editable="true"/>
												        <sjg:gridColumn name="rsaCode" index="rsaCode" title="RSA Code" sortable="true" search="true" searchoptions="{sopt:['cn','nc','eq','ne']}" editable="true" editrules="{number: true, required: true}"/>
												        <sjg:gridColumn name="branchName" index="branchName" title="Branch Name" sortable="true" search="true" searchoptions="{sopt:['cn','nc','eq','ne']}" editable="true" edittype="select" editoptions="%{listSelect1}" editrules="{required: true}"/>
												        <sjg:gridColumn name="status1" index="status1" title="Status" sortable="true" search="true" searchoptions="{sopt:['cn','nc']}" editable="true" editrules="{required: true}" edittype="select" editoptions="{value:'Y:Active;N:DeActive'}" />
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