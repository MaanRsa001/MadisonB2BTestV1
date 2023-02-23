<%@ page isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="sjg" uri="/struts-jquery-grid-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html <s:if test="locale.language == 'ar'">dir="rtl"</s:if> >
	<head>
	<script type="text/javascript" src="pages/admin/ratingEngine/menu.js"></script>
		<sj:head jqueryui="true" jquerytheme="start" />
		
        <script>
        function formatconveyance(cellvalue) {
			var ss='${pageContext.request.contextPath}/editconveyanceRating.action?mode=edit&conveyID='+cellvalue;
			return "<a href=\'"+ss+"\'>Edit</a>";
		}
		function formatcountrymaster(cellvalue) {
			var ss='${pageContext.request.contextPath}/editcountrymasterRating.action?mode=edit&countryID='+cellvalue;
			return "<a href=\'"+ss+"\'>Edit</a>";
		}		
		function formatcountry(cellvalue) {
			var ss='${pageContext.request.contextPath}/editcountryRating.action?mode=edit&countryDetID='+cellvalue;
			return "<a href=\'"+ss+"\'>Edit</a>";
		}	
		function formatbankmaster(cellvalue) {
			var ss='${pageContext.request.contextPath}/editbankmasterRating.action?mode=edit&bankID='+cellvalue;
			return "<a href=\'"+ss+"\'>Edit</a>";
		}
		function formatmaterialmaster(cellvalue) {
			var ss='${pageContext.request.contextPath}/editmaterialmasterRating.action?mode=edit&materialID='+cellvalue;
			return "<a href=\'"+ss+"\'>Edit</a>";
		}	
		function formatwarratemaster(cellvalue) {
			var ss='${pageContext.request.contextPath}/editwarratemasterRating.action?mode=edit&warID='+cellvalue;
			return "<a href=\'"+ss+"\'>Edit</a>";
		}
		function formatcommoditymaster(cellvalue) {
			var ss='${pageContext.request.contextPath}/editcommoditymasterRating.action?mode=edit&commodityID='+cellvalue;
			return "<a href=\'"+ss+"\'>Edit</a>";
		}
		function formatcovercommomaster(cellvalue) {
			//var ss='${pageContext.request.contextPath}/editcovercommomasterRating.action?mode=edit&sno='+cellvalue;
			var ss='${pageContext.request.contextPath}/editcovercommomasterRating.action?mode=edit&'+cellvalue;
			return "<a href=\'"+ss+"\'>Edit</a>";
		}
		function formatcategorymaster(cellvalue) {
			var ss='${pageContext.request.contextPath}/editcategorymasterRating.action?mode=edit&catID='+cellvalue;
			return "<a href=\'"+ss+"\'>Edit</a>";
		}
		function formatsaletermmaster(cellvalue) {
			var ss='${pageContext.request.contextPath}/editsaletermmasterRating.action?mode=edit&saleID='+cellvalue;
			return "<a href=\'"+ss+"\'>Edit</a>";
		}
		function formattolerancemaster(cellvalue) {
			var ss='${pageContext.request.contextPath}/edittolerancemasterRating.action?mode=edit&toleID='+cellvalue;
			return "<a href=\'"+ss+"\'>Edit</a>";
		}
		function formatcommodityexcess(cellvalue) {
			var ss='${pageContext.request.contextPath}/editcommodityexcessRating.action?mode=edit&comExID='+cellvalue;
			return "<a href=\'"+ss+"\'>Edit</a>";
		}
		function formatvesselmaster(cellvalue) {
			var ss='${pageContext.request.contextPath}/editvesselmasterRating.action?mode=edit&vesselID='+cellvalue;
			return "<a href=\'"+ss+"\'>Edit</a>";
		}
		function formatsettlingagent(cellvalue) {
			var ss='${pageContext.request.contextPath}/editsettlingagentRating.action?mode=edit&agentID='+cellvalue;
			return "<a href=\'"+ss+"\'>Edit</a>";
		}
		function formatexchangemaster(cellvalue) {
			var ss='${pageContext.request.contextPath}/editexchangemasterRating.action?mode=edit&exchangeID='+cellvalue;
			return "<a href=\'"+ss+"\'>Edit</a>";
		}
		function formatcurrencymaster(cellvalue) {
			var ss='${pageContext.request.contextPath}/editcurrencymasterRating.action?mode=edit&currencyID='+cellvalue;
			return "<a href=\'"+ss+"\'>Edit</a>";
		}
		function formatextracover(cellvalue) {
			var ss='${pageContext.request.contextPath}/editextracoverRating.action?mode=edit&extraCoverId='+cellvalue;
			return "<a href=\'"+ss+"\'>Edit</a>";
		}
		function formattransport(cellvalue) {
			var ss='${pageContext.request.contextPath}/editmodeoftransportRating.action?mode=edit&transID='+cellvalue;
			return "<a href=\'"+ss+"\'>Edit</a>";
		}
		function formatwarrantymaster(cellvalue) {
			var ss='${pageContext.request.contextPath}/editwarrantymasterRating.action?mode=edit&warrantyID='+cellvalue;
			return "<a href=\'"+ss+"\'>Edit</a>";
		}
		function formatContantMaster(cellvalue) {
			var ss='${pageContext.request.contextPath}/editconstantMasterRating.action?mode=edit&categoryId='+cellvalue;
			return "<a href=\'"+ss+"\'>Edit</a>";
		}
		function formatconstantdetail(cellvalue) {
			//var ss='${pageContext.request.contextPath}/editconstantdetailRating.action?mode=edit&category_detail_id='+cellvalue;
			var ss='${pageContext.request.contextPath}/editconstantdetailRating.action?mode=edit&constantID='+cellvalue;
			return "<a href=\'"+ss+"\'>Edit</a>";
		}
		function formatexclusionmaster(cellvalue) {
			var ss='${pageContext.request.contextPath}/editexclusionmasterRating.action?mode=edit&exclusionID='+cellvalue;
			return "<a href=\'"+ss+"\'>Edit</a>";
		}	
		function formatcitymaster(cellvalue) {
			var ss='${pageContext.request.contextPath}/editcitymasterRating.action?mode=edit&cityID='+cellvalue;
			return "<a href=\'"+ss+"\'>Edit</a>";
		}	
		function formatwsrcc(cellvalue) {
			var ss='${pageContext.request.contextPath}/editwsrccRating.action?mode=edit&wsrccid='+cellvalue;
			return "<a href=\'"+ss+"\'>Edit</a>";
		}
		function formatcovermaster(cellvalue) {
			var ss='${pageContext.request.contextPath}/editcovermasterRating.action?mode=edit&coverID='+cellvalue;
			return "<a href=\'"+ss+"\'>Edit</a>";
		}
		function formatclause(cellvalue) {
			var ss='${pageContext.request.contextPath}/editclauseRating.action?mode=edit&clauseID='+cellvalue;
			return "<a href=\'"+ss+"\'>Edit</a>";
		}
		function formaterror(cellvalue) {
			var ss='${pageContext.request.contextPath}/editerrorRating.action?mode=edit&errorID='+cellvalue;
			return "<a href=\'"+ss+"\'>Edit</a>";
		}
		function setformatter(cellvalue) {
			return cellvalue;
		}
		function formateExecutiveMaster(cellvalue) {
			var ss='${pageContext.request.contextPath}/editexecutivemasterRating.action?mode=edit&exmexecutiveId='+cellvalue;
			return "<a href=\'"+ss+"\'>Edit</a>";
		}
		
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
         $(function(){
    		$("#transID").change(function(e){
            var param = $(this).val(); 
            alert(param); 

         	});  
 		}); 
  
</script>
	</head>
	<body>
	<div style="margin:10px 0;"></div>
    <div class="easyui-layout" style="width:900px;height:1000px;">
       <div data-options="region:'west',split:true" title="Options" style="width:150px;">
            <div class="easyui-accordion" data-options="fit:true,border:false">
                <%@ include file="/pages/admin/ratingEngine/ratingEngineMenu.jsp" %>
            </div>
        </div>
        <div data-options="region:'center',title:'',iconCls:''">
            <div class="easyui-tabs" data-options="fit:true,border:false,plain:true" id="mainTab">
                <div title="${menuType}" style="padding:5px">
		<table width="px;" border="0" cellpadding="0" cellspacing="0">
		  	<tr>
		    	<td>
		    		<table width="80%" border="0" cellpadding="0" cellspacing="1" bgcolor="#E5E5E5">
		      			<tr>
		        			<td bgcolor="#FFFFFF" style="padding:10px; background:#F8F8F8">
		        				<table width="100%">
		        					<tr><td  style="color:red;"><s:actionmessage/></td></tr>
							       	<tr><td height="15px" align="right">
							       		<s:if test='"constantdetail".equalsIgnoreCase(menuType)'>
							       			<a href="edit${menuType}Rating.action?mode=add&categoryID=${categoryID}">Add New</a>
							       		</s:if>
							       		<s:else>
							       			<a href="edit${menuType}Rating.action?mode=add">Add New</a>
							       		</s:else>
							       		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							 		</td>					 		
							 		</tr>
							 		<tr><td bgcolor="#ffffff">
								 		<table width="50%" border="0" cellpadding="4" cellspacing="0">
									        <tr><td width="5%"></td>
									        	<td width="50%">
									        		<s:url id="editurl" action="editWsrccRating" />
												    <sjg:grid
												        id="ratingEnginegrid" caption="%{menuType}" href="%{menuType}Ratingjson.action?menuType=%{menuType}&categoryID=%{categoryID}" pager="true" dataType="json" gridModel="gridList" reloadTopics="reloadScheme"
												        rowList="10,15,20" rownumbers="true" width="700" navigator="true" navigatorView="false" navigatorDelete="false"
												        navigatorAdd="false" navigatorEdit="false" navigatorSearch="true" navigatorRefresh="true" viewrecords="true" editurl="%{editurl}"
												        navigatorEditOptions="{width:450,height:350, reloadAfterSubmit:true, afterSubmit:funcedit}" 
												        navigatorAddOptions="{width:450,height:350, reloadAfterSubmit:true, afterSubmit:funcedit}"
												        navigatorDeleteOptions="{width:450,height:350, reloadAfterSubmit:true, afterSubmit:funcedit}"
												    >
												    <s:if test = 'menuType=="conveyance"'>
												        <sjg:gridColumn name="conveyID" index="conveyID" title="CONVEY ID" sortable="true" search="false" editable="true" editrules="{required: false}" onkeyup="false" width="50"/>
												        <sjg:gridColumn name="transDesc" index="transDesc" title="MODE OF TRANSPORT" search="true" searchoptions="{sopt:['cn','eq']}" editable="true" editrules="{required: true, edithidden: true }" width="100"/>
												        <sjg:gridColumn name="conveyName" index="conveyName" title="CONVEY NAME" search="true" searchoptions="{sopt:['cn','eq']}"  editable="true" editrules="{required: true, edithidden: true }" width="100"/>
												        <sjg:gridColumn name="conveyRate" index="conveyRate" title="CONVEY RATE" search="false" editable="true" editrules="{required: true,edithidden: true }" width="80"/>
												        <sjg:gridColumn name="eff_date" index="eff_date" title="EFFECTIVE DATE" sortable="true" search="false" editable="true" editrules="{required: true}" width="80"/>
												        <sjg:gridColumn name="status" index="status" title="STATUS" sortable="true" search="false" editable="true" editrules="{required: true}" edittype="select" editoptions="{value:'Y:Active;N:DeActive'}" width="40"/>
												        <sjg:gridColumn name="edit" title="EDIT" align="center" formatter="formatconveyance" search="false" width="40"/>
												    </s:if>
												    <s:elseif test = 'menuType=="countrymaster"'>
												        <sjg:gridColumn name="countryID" index="countryID" title="COUNTRY ID" sortable="true" search="false" editable="true" editrules="{required: false}" onkeyup="false" width="50"/>
												        <sjg:gridColumn name="countryName" index="countryName" title="COUNTRY NAME" search="true" searchoptions="{sopt:['cn','eq']}" editable="true" editrules="{required: true, edithidden: true }" width="100"/>
												        <sjg:gridColumn name="countryNat" index="countryNat" title="NATIONALITY NAME" editable="true" search="true" searchoptions="{sopt:['cn','eq']}" editrules="{required: true,edithidden: true }" width="100"/>
												        <sjg:gridColumn name="eff_date" index="startPlace" title="EFFECTIVE DATE" sortable="true" search="false" editable="true" editrules="{required: true}" width="100"/>
												        <sjg:gridColumn name="status" index="status" title="STATUS" sortable="true" search="false" editable="true" editrules="{required: true}" edittype="select" editoptions="{value:'Y:Active;N:DeActive'}" width="40"/>
												        <sjg:gridColumn name="edit" title="EDIT" align="center" formatter="formatcountrymaster" search="false" width="40"/>
												    </s:elseif>
												    <s:elseif test = 'menuType=="country"'>
												        <sjg:gridColumn name="countryDetID" index="countryDetID" title="COUNTRY ID" sortable="true" search="false" editable="true" editrules="{required: false}" onkeyup="false" width="70"/>
												        <sjg:gridColumn name="countryID" index="countryID" title="COUNTRY NAME" search="true" searchoptions="{sopt:['cn','eq']}" editable="true" editrules="{required: true, edithidden: true }" width="100"/>
												        <sjg:gridColumn name="warRate" index="warRate" title="WAR RATE" editable="true" search="false" editrules="{required: true,edithidden: true }" width="50"/>
												        <sjg:gridColumn name="startPlace" index="startPlace" title="IMPORT" sortable="true" search="true" searchoptions="{sopt:['cn','eq']}" editable="true" editrules="{required: true}" width="100"/>
												        <sjg:gridColumn name="endPlace" index="endPlace" title="EXPORT" sortable="true" search="true" searchoptions="{sopt:['cn','eq']}" editable="true" editrules="{required: true}" width="100"/>
												        <sjg:gridColumn name="status" index="status" title="STATUS" sortable="true" search="false" editable="true" editrules="{required: true}" edittype="select" editoptions="{value:'Y:Active;N:DeActive'}" width="40"/>
												        <sjg:gridColumn name="edit" title="EDIT" align="center" formatter="formatcountry" search="false" width="40"/>
												    </s:elseif>
												    <s:elseif test = 'menuType=="bankmaster"'>
												        <sjg:gridColumn name="bankID" index="bankID" title="BANK ID" sortable="true" search="false" editable="true" editrules="{required: false}" onkeyup="false" width="50"/>
												        <sjg:gridColumn name="bankName" index="bankName" title="BANK NAME" search="true" searchoptions="{sopt:['cn','eq']}" editable="true" editrules="{required: true, edithidden: true }" width="100"/>
												        <sjg:gridColumn name="eff_date" index="eff_date" title="EFFECTIVE DATE" sortable="true" search="false" searchoptions="{sopt:['cn','eq']}" editable="true" editrules="{required: true}" width="100"/>
												        <sjg:gridColumn name="status" index="status" title="STATUS" sortable="true" search="false" editable="true" editrules="{required: true}" edittype="select" editoptions="{value:'Y:Active;N:DeActive'}" width="40"/>
												        <sjg:gridColumn name="edit" title="EDIT" align="center" formatter="formatbankmaster" search="false" width="40"/>
												    </s:elseif>
												    <s:elseif test = 'menuType=="materialmaster"'>
												        <sjg:gridColumn name="materialID" index="materialID" title="MATERIAL ID" sortable="true" search="false" editable="true" editrules="{required: false}" onkeyup="false" width="50"/>
												        <sjg:gridColumn name="materialName" index="materialName" title="MATERIAL NAME" search="true" searchoptions="{sopt:['cn','eq']}" editable="true" editrules="{required: true, edithidden: true }" width="100"/>
												        <sjg:gridColumn name="coverName" index="coverName" title="COVER NAME" search="false" searchoptions="{sopt:['cn','eq']}" editable="true" editrules="{required: true, edithidden: true }" width="100"/>
												        <sjg:gridColumn name="materialRate" index="materialRate" title="MATERIAL RATE" search="false" editable="true" editrules="{required: true, edithidden: true }" width="100"/>
												        <sjg:gridColumn name="eff_date" index="eff_date" title="EFFECTIVE DATE" sortable="false" search="false" searchoptions="{sopt:['cn','eq']}" editable="true" editrules="{required: true}" width="100"/>
												        <sjg:gridColumn name="status" index="status" title="STATUS" sortable="true" search="false" editable="true" editrules="{required: true}" edittype="select" editoptions="{value:'Y:Active;N:DeActive'}" width="40"/>
												        <sjg:gridColumn name="edit" title="EDIT" align="center" formatter="formatmaterialmaster" search="false" width="40"/>
												    </s:elseif>
												    <s:elseif test = 'menuType=="commoditymaster"'>
												        <sjg:gridColumn name="commodityID" index="commodityID" title="COMMODITY ID" sortable="true" search="false" editable="true" editrules="{required: false}" onkeyup="false" width="50"/>
												        <sjg:gridColumn name="commodityName" index="commodityName" title="COMMODITY NAME" search="true" searchoptions="{sopt:['cn','eq']}" editable="true" editrules="{required: true, edithidden: true }" width="100"/>
												        <sjg:gridColumn name="eff_date" index="eff_date" title="EFFECTIVE DATE" sortable="false" search="false" searchoptions="{sopt:['cn','eq']}" editable="true" editrules="{required: true}" width="100"/>
												        <sjg:gridColumn name="status" index="status" title="STATUS" sortable="true" search="false" editable="true" editrules="{required: true}" edittype="select" editoptions="{value:'Y:Active;N:DeActive'}" width="40"/>
												        <sjg:gridColumn name="edit" title="EDIT" align="center" formatter="formatcommoditymaster" search="false" width="40"/>
												    </s:elseif>
												    <s:elseif test = 'menuType=="covercommomaster"'>
												    	<sjg:gridColumn name="catname" index="catname" title="CATEGORY" sortable="true" search="false" editable="true" editrules="{required: false}" onkeyup="false" width="250"/>
												        <sjg:gridColumn name="coverName" index="coverName" title="COVER NAME" sortable="true" align="center" search="false" editable="true" editrules="{required: false}" onkeyup="false" width="120"/>
												        <sjg:gridColumn name="coverDesc" index="coverDesc" title="COVER DESCRIPTION" align="center" search="true" searchoptions="{sopt:['cn','eq']}" editable="true" editrules="{required: true, edithidden: true }" width="180"/>
												        <sjg:gridColumn name="coverRate1" index="coverRate1" title="COVER RATE" search="false" editable="true" editrules="{required: true, edithidden: true }" width="100"/>
												        <sjg:gridColumn name="catrate1" index="catrate1" title="CATEGORY RATE" search="true" searchoptions="{sopt:['cn','eq']}" editable="true" editrules="{required: true, edithidden: true }" width="130"/>
												        <sjg:gridColumn name="status" index="status" title="STATUS" sortable="true" align="center" search="false" editable="true" editrules="{required: true}" edittype="select" editoptions="{value:'Y:Active;N:DeActive'}" width="60"/>
												        <sjg:gridColumn name="edit" title="EDIT" align="center" formatter="formatcovercommomaster" search="false" width="40"/>
												    </s:elseif>
												    <s:elseif test = 'menuType=="categorymaster"'>
												        <sjg:gridColumn name="catID" index="catID" title="CATEGORY ID" sortable="true" search="false" editable="true" editrules="{required: false}" onkeyup="false" width="40"/>
												        <sjg:gridColumn name="catname" index="catname" title="CATEGORY NAME" search="true" searchoptions="{sopt:['cn','eq']}" editable="true" editrules="{required: true, edithidden: true }" width="60"/>
												        <sjg:gridColumn name="catrate" index="catrate" title="CATEGORY RATE" sortable="false" search="false" searchoptions="{sopt:['cn','eq']}" editable="true" editrules="{required: true}" width="40"/>
												        <sjg:gridColumn name="status" index="status" title="STATUS" sortable="true" search="false" editable="true" editrules="{required: true}" edittype="select" editoptions="{value:'Y:Active;N:DeActive'}" width="40"/>
												        <sjg:gridColumn name="edit" title="EDIT" align="center" formatter="formatcategorymaster" search="false" width="40"/>
												    </s:elseif>
												    <s:elseif test = 'menuType=="warratemaster"'>
												        <sjg:gridColumn name="warID" index="warID" title="WAR ID" sortable="true" search="false" editable="true" editrules="{required: false}" onkeyup="false" width="50"/>
												        <sjg:gridColumn name="warName" index="warName" title="WAR DESC" search="true" searchoptions="{sopt:['cn','eq']}" editable="true" editrules="{required: true, edithidden: true }" width="100"/>
												        <sjg:gridColumn name="warRate" index="warRate" title="WAR RATE" search="false" editable="true" editrules="{required: true, edithidden: true }" width="100"/>
												        <sjg:gridColumn name="transID" index="transID" title="TRANSPORT DESC" search="true" searchoptions="{sopt:['cn','eq']}" editable="true" editrules="{required: true, edithidden: true }" width="100"/>
												        <sjg:gridColumn name="eff_date" index="eff_date" title="EFFECTIVE DATE" sortable="false" search="false" editable="true" editrules="{required: true}" width="100"/>
												        <sjg:gridColumn name="status" index="status" title="STATUS" sortable="true" search="false" editable="true" editrules="{required: true}" edittype="select" editoptions="{value:'Y:Active;N:DeActive'}" width="40"/>
												        <sjg:gridColumn name="edit" title="EDIT" align="center" formatter="formatwarratemaster" search="false" width="40"/>
												    </s:elseif>
												    <s:elseif test = 'menuType=="saletermmaster"'>
												        <sjg:gridColumn name="saleID" index="saleID" title="SALE TERM ID" sortable="true" search="false" editable="true" editrules="{required: false}" onkeyup="false" width="50"/>
												        <sjg:gridColumn name="saleName" index="saleName" title="SALE TERM NAME" search="true" searchoptions="{sopt:['cn','eq']}" editable="true" editrules="{required: true, edithidden: true }" width="100"/>
												        <sjg:gridColumn name="saleValue" index="saleValue" title="SALE TERM VALUE" search="false" editable="true" editrules="{required: true, edithidden: true }" width="100"/>
												        <sjg:gridColumn name="status" index="status" title="STATUS" sortable="true" search="false" editable="true" editrules="{required: true}" edittype="select" editoptions="{value:'Y:Active;N:DeActive'}" width="40"/>
												        <sjg:gridColumn name="edit" title="EDIT" align="center" formatter="formatsaletermmaster" search="false" width="40"/>
												    </s:elseif>
												    <s:elseif test = 'menuType=="tolerancemaster"'>
												        <sjg:gridColumn name="toleID" index="toleID" title="TOLE ID" sortable="true" search="false" editable="true" editrules="{required: false}" onkeyup="false" width="50"/>
												        <sjg:gridColumn name="toleName" index="toleName" title="TOLERANCE NAME" search="true" searchoptions="{sopt:['cn','eq']}" editable="true" editrules="{required: true, edithidden: true }" width="100"/>
												        <sjg:gridColumn name="toleValue" index="toleValue" title="TOLERANCE VALUE" search="false" editable="true" editrules="{required: true, edithidden: true }" width="100"/>
												        <sjg:gridColumn name="status" index="status" title="STATUS" sortable="true" search="false" editable="true" editrules="{required: true}" edittype="select" editoptions="{value:'Y:Active;N:DeActive'}" width="40"/>
												        <sjg:gridColumn name="edit" title="EDIT" align="center" formatter="formattolerancemaster" search="false" width="40"/>
												    </s:elseif>
												    <s:elseif test = 'menuType=="commodityexcess"'>
												        <sjg:gridColumn name="comExID" index="comExID" title="COMEX ID" sortable="true" search="false" editable="true" editrules="{required: false}" onkeyup="false" width="40"/>
												        <sjg:gridColumn name="startAmt" index="startAmt" title="START AMOUNT" search="true" searchoptions="{sopt:['cn','eq']}" editable="true" editrules="{required: true, edithidden: true }" width="60"/>
												        <sjg:gridColumn name="endAmt" index="endAmt" title="END AMOUNT" search="true" searchoptions="{sopt:['cn','eq']}" editable="true" editrules="{required: true, edithidden: true }" width="60"/>
												        <sjg:gridColumn name="deductible" index="deductible" title="DEDUCTIBLE" search="false" editable="true" editrules="{required: true, edithidden: true }" width="60"/>
												        <sjg:gridColumn name="comExRate" index="comExRate" title="RATE" search="false" editable="true" editrules="{required: true, edithidden: true }" width="60"/>
												        <sjg:gridColumn name="eff_date" index="eff_date" title="EFFECTIVE DATE" search="false" editable="true" editrules="{required: true, edithidden: true }" width="60"/>
												        <sjg:gridColumn name="status" index="status" title="STATUS" sortable="true" search="false" editable="true" editrules="{required: true}" edittype="select" editoptions="{value:'Y:Active;N:DeActive'}" width="40"/>
												        <sjg:gridColumn name="edit" title="EDIT" align="center" formatter="formatcommodityexcess" search="false" width="40"/>
												    </s:elseif>
												    <s:elseif test = 'menuType=="vesselmaster"'>
												        <sjg:gridColumn name="vesselID" index="vesselID" title="VESSEL ID" sortable="true" search="false" editable="true" editrules="{required: false}" onkeyup="false" width="40"/>
												        <sjg:gridColumn name="vesselName" index="vesselName" title="VESSEL NAME" search="true" searchoptions="{sopt:['cn','eq']}" editable="true" editrules="{required: true, edithidden: true }" width="100"/>
												        <sjg:gridColumn name="vesselClass" index="vesselClass" title="VESSEL CLASS" search="true" searchoptions="{sopt:['cn','eq']}" editable="true" editrules="{required: true, edithidden: true }" width="60"/>
												        <sjg:gridColumn name="status" index="status" title="STATUS" sortable="true" search="false" editable="true" editrules="{required: true}" edittype="select" editoptions="{value:'Y:Active;N:DeActive'}" width="40"/>
												        <sjg:gridColumn name="edit" title="EDIT" align="center" formatter="formatvesselmaster" search="false" width="40"/>
												    </s:elseif>
												    <s:elseif test = 'menuType=="settlingagent"'>
												        <sjg:gridColumn name="agentID" index="agentID" title="AGENT ID" sortable="true" search="false" editable="true" editrules="{required: false}" onkeyup="false" width="40"/>
												        <sjg:gridColumn name="agentName" index="agentName" title="SETTLING AGENT NAME" search="true" searchoptions="{sopt:['cn','eq']}" editable="true" editrules="{required: true, edithidden: true }" width="150"/>
												        <sjg:gridColumn name="countryID" index="countryID" title="COUNTRY NAME" search="true" searchoptions="{sopt:['cn','eq']}" editable="true" editrules="{required: true, edithidden: true }" width="60"/>
												        <sjg:gridColumn name="status" index="status" title="STATUS" sortable="true" search="false" editable="true" editrules="{required: true}" edittype="select" editoptions="{value:'Y:Active;N:DeActive'}" width="40"/>
												        <sjg:gridColumn name="edit" title="EDIT" align="center" formatter="formatsettlingagent" search="false" width="40"/>
												    </s:elseif>
												    <s:elseif test = 'menuType=="exchangemaster"'>
												        <sjg:gridColumn name="exchangeID" index="exchangeID" title="EXCHANGE ID" sortable="true" search="false" editable="true" editrules="{required: false}" onkeyup="false" width="40"/>
												        <sjg:gridColumn name="currencyID" index="currencyID" title="CURRENCY TYPE" search="true" searchoptions="{sopt:['cn','eq']}" editable="true" editrules="{required: true, edithidden: true }" width="100"/>
												        <sjg:gridColumn name="exchangeRate" index="exchangeRate" title="EXCHANGE RATE" search="false" editable="true" editrules="{required: true, edithidden: true }" width="100"/>
												        <sjg:gridColumn name="status" index="status" title="STATUS" sortable="true" search="false" editable="true" editrules="{required: true}" edittype="select" editoptions="{value:'Y:Active;N:DeActive'}" width="40"/>
												        <sjg:gridColumn name="edit" title="EDIT" align="center" formatter="formatexchangemaster" search="false" width="40"/>
												    </s:elseif>
												    <s:elseif test = 'menuType=="currencymaster"'>
												        <sjg:gridColumn name="currencyID" index="currencyID" title="CURRENCY ID" sortable="true" search="false" editable="true" editrules="{required: false}" onkeyup="false" width="40"/>
												        <sjg:gridColumn name="currencyType" index="currencyType" title="CURRENCY TYPE" search="true" searchoptions="{sopt:['cn','eq']}" editable="true" editrules="{required: true, edithidden: true }" width="100"/>
												        <sjg:gridColumn name="subCurrency" index="subCurrency" title="SUB CURRENCY" search="false" searchoptions="{sopt:['cn','eq']}" editable="true" editrules="{required: true, edithidden: true }" width="100"/>
												        <sjg:gridColumn name="currencyShortName" index="currencyShortName" title="SHORT NAME" search="false" searchoptions="{sopt:['cn','eq']}" editable="true" editrules="{required: true, edithidden: true }" width="100"/>
												        <sjg:gridColumn name="status" index="status" title="STATUS" sortable="true" search="false" editable="true" editrules="{required: true}" edittype="select" editoptions="{value:'Y:Active;N:DeActive'}" width="40"/>
												        <sjg:gridColumn name="edit" title="EDIT" align="center" formatter="formatcurrencymaster" search="false" width="40"/>
												    </s:elseif>
												    <s:elseif test = 'menuType=="extracover"'>
												        <sjg:gridColumn name="extraCoverId" index="extraCoverId" title="EXTRA COVER ID" sortable="true" search="false" editable="true" editrules="{required: false}" onkeyup="false" width="40"/>
												        <sjg:gridColumn name="extraCoverName" index="extraCoverName" title="EXTRA COVER NAME" search="true" searchoptions="{sopt:['cn','eq']}" editable="true" editrules="{required: true, edithidden: true }" width="100"/>
												        <sjg:gridColumn name="transID" index="transID" title="MODE OF TRANSPORT" search="true" searchoptions="{sopt:['cn','eq']}" editable="true" editrules="{required: true, edithidden: true }" width="100"/>
												        <sjg:gridColumn name="status" index="status" title="STATUS" sortable="true" search="false" editable="true" editrules="{required: true}" edittype="select" editoptions="{value:'Y:Active;N:DeActive'}" width="40"/>
												        <sjg:gridColumn name="edit" title="EDIT" align="center" formatter="formatextracover" search="false" width="40"/>
												    </s:elseif>
												    <s:elseif test = 'menuType=="modeoftransport"'>
												        <sjg:gridColumn name="transID" index="transID" title="MODE_TRANSPORT_ID" sortable="true" search="false" editable="true" editrules="{required: false}" onkeyup="false" width="50"/>
												        <sjg:gridColumn name="transDesc" index="transDesc" title="MODE OF TRANSPORT" search="true" searchoptions="{sopt:['cn','eq']}" editable="true" editrules="{required: true, edithidden: true }" width="200"/>
												        <sjg:gridColumn name="status" index="status" title="STATUS" sortable="true" search="false" editable="true" editrules="{required: true}" edittype="select" editoptions="{value:'Y:Active;N:DeActive'}" width="40"/>
												        <sjg:gridColumn name="edit" title="EDIT" align="center" formatter="formattransport" search="false" width="40"/>
												    </s:elseif>
												    <s:elseif test = 'menuType=="warrantymaster"'>
												        <sjg:gridColumn name="warrantyID" index="warrantyID" title="WARRANTY ID" sortable="true" search="false" editable="true" editrules="{required: false}" onkeyup="false" width="50"/>
												        <sjg:gridColumn name="warrantyDesc" index="warrantyDesc" title="WARRANTY DESCRIPTION" search="true" searchoptions="{sopt:['cn','eq']}" editable="true" editrules="{required: true, edithidden: true }" width="200"/>
												        <sjg:gridColumn name="status" index="status" title="STATUS" sortable="true" search="false" editable="true" editrules="{required: true}" edittype="select" editoptions="{value:'Y:Active;N:DeActive'}" width="40"/>
												        <sjg:gridColumn name="edit" title="EDIT" align="center" formatter="formatwarrantymaster" search="false" width="40"/>
												    </s:elseif>
												     <s:elseif test ='"constantMaster".equalsIgnoreCase(menuType)'>
												        <sjg:gridColumn name="categoryId" index="categoryId" title="Category Id" sortable="true" search="false" editable="true" editrules="{required: false}" onkeyup="false" width="50"/>
												        <sjg:gridColumn name="categoryName" index="categoryName" title="Category Name" search="true" searchoptions="{sopt:['cn','eq']}" editable="true" editrules="{required: true, edithidden: true }" width="200"/>
												        <sjg:gridColumn name="status" index="status" title="Status" sortable="true" search="false" editable="true" editrules="{required: true}" edittype="select" editoptions="{value:'Y:Active;N:DeActive'}" width="40"/>
												        <sjg:gridColumn name="edit" title="Edit" align="center" formatter="formatContantMaster" search="false" width="40"/>
												        <sjg:gridColumn name="addDetail" title="Detail" align="center" formatter="setformatter" search="false" width="40"/>
												    </s:elseif>
												    <s:elseif test = 'menuType=="constantdetail"' >
												        <sjg:gridColumn name="category_detail_id" index="category_detail_id" title="CONSTANT ID" sortable="true" search="false" editable="true" editrules="{required: false}" onkeyup="false" width="50"/>
												        <sjg:gridColumn name="detailName" index="detailName" title="DETAIL NAME" search="true" searchoptions="{sopt:['cn','eq']}" editable="true" editrules="{required: true, edithidden: true }" width="200"/>
												        <sjg:gridColumn name="status" index="status" title="STATUS" sortable="true" search="false" editable="true" editrules="{required: true}" edittype="select" editoptions="{value:'Y:Active;N:DeActive'}" width="40"/>
												        <sjg:gridColumn name="edit" title="EDIT" align="center" formatter="formatconstantdetail" search="false" width="40"/>
												    </s:elseif>
												     <s:elseif test = 'menuType=="exclusionmaster"'>
												        <sjg:gridColumn name="exclusionID" index="exclusionID" title="EXCLUSION ID" sortable="true" search="false" editable="true" editrules="{required: false}" onkeyup="false" width="50"/>
												        <sjg:gridColumn name="exclusionName" index="exclusionName" title="EXCLUSION NAME" search="true" searchoptions="{sopt:['cn','eq']}" editable="true" editrules="{required: true, edithidden: true }" width="200"/>
												        <sjg:gridColumn name="status" index="status" title="STATUS" sortable="true" search="false" editable="true" editrules="{required: true}" edittype="select" editoptions="{value:'Y:Active;N:DeActive'}" width="40"/>
												        <sjg:gridColumn name="edit" title="EDIT" align="center" formatter="formatexclusionmaster" search="false" width="40"/>
												    </s:elseif>
												    <s:elseif test = 'menuType=="citymaster"'>
												        <sjg:gridColumn name="cityID" index="cityID" title="CITY ID" sortable="true" search="false" editable="true" editrules="{required: false}" onkeyup="false" width="50"/>
												        <sjg:gridColumn name="countryID" index="countryID" title="COUNTRY NAME" search="true" searchoptions="{sopt:['cn','eq']}" editable="true" editrules="{required: true, edithidden: true }" width="50"/>
												        <sjg:gridColumn name="cityName" index="cityName" title="CITY NAME" editable="true" search="true" searchoptions="{sopt:['cn','eq']}" editrules="{required: true,edithidden: true }" width="50"/>
												        <sjg:gridColumn name="status" index="status" title="STATUS" sortable="true" search="false" editable="true" editrules="{required: true}" edittype="select" editoptions="{value:'Y:Active;N:DeActive'}" width="40"/>
												        <sjg:gridColumn name="edit" title="EDIT" align="center" formatter="formatcitymaster" search="false" width="40"/>
												    </s:elseif>
												    <s:elseif test = 'menuType=="wsrcc"'>
												        <sjg:gridColumn name="wsrccid" index="wsrccid" title="WSRCC ID" sortable="true" search="false" editable="true" editrules="{required: false}" onkeyup="false" width="60"/>
												        <sjg:gridColumn name="transDesc" index="transDesc" title="MODE OF TRANSPORT" search="false" editable="true" editrules="{required: true, edithidden: true }" width="120"/>
												        <sjg:gridColumn name="coverName" index="coverName" title="COVER" editable="true" search="true" searchoptions="{sopt:['cn','eq']}" editrules="{required: true,edithidden: true }" width="50"/>
												        <sjg:gridColumn name="wsrccdesc" index="wsrccdesc" title="WSRCC DESCRIPTION" sortable="true" search="true" searchoptions="{sopt:['cn','eq']}" editable="true" editrules="{required: true}" width="200"/>
												        <sjg:gridColumn name="status" index="status" title="STATUS" sortable="true" search="false" editable="true" editrules="{required: true}" edittype="select" editoptions="{value:'Y:Active;N:DeActive'}" width="40"/>
												        <sjg:gridColumn name="edit" title="EDIT" align="center" formatter="formatwsrcc" search="false" width="40"/>
												    </s:elseif>
												    <s:elseif test = 'menuType=="covermaster"'>
												        <sjg:gridColumn name="coverID" index="coverID" title="COVER ID" sortable="true" search="false" editable="true" editrules="{required: false}" onkeyup="false" width="50"/>
												        <sjg:gridColumn name="coverName" index="coverName" title="COVER NAME" search="true" searchoptions="{sopt:['cn','eq']}" editable="true" editrules="{required: true, edithidden: true }" width="100"/>
												        <sjg:gridColumn name="coverRate" index="coverRate" title="COVER RATE" editable="true" search="false" editrules="{required: true,edithidden: true }" width="50"/>
												        <sjg:gridColumn name="transID" index="transID" title="TRANSPORT DESCRIPTION" sortable="true" search="true" searchoptions="{sopt:['cn','eq']}" editable="true" editrules="{required: true}" width="100"/>
												        <sjg:gridColumn name="status" index="status" title="STATUS" sortable="true" search="false" editable="true" editrules="{required: true}" edittype="select" editoptions="{value:'Y:Active;N:DeActive'}" width="40"/>
												        <sjg:gridColumn name="edit" title="EDIT" align="center" formatter="formatcovermaster" search="false" width="40"/>
												    </s:elseif>
												    <s:elseif test='menuType=="clause"'>
												     <sjg:gridColumn name="clauseID" index="clauseID" title="CLAUSE ID" sortable="true" search="false" editable="true" editrules="{required: false}" onkeyup="false" width="80"/>
												        <sjg:gridColumn name="transDesc" index="transDesc" title="MODE OF TRANSPORT" search="false" editable="true" editrules="{required: true, edithidden: true }"/>
												        <sjg:gridColumn name="coverName" index="coverName" title="COVER" editable="true" search="true" searchoptions="{sopt:['cn','eq']}" editrules="{required: true,edithidden: true }"/>
												        <sjg:gridColumn name="clauseDesc" index="clauseDesc" title="CLAUSE DESCRIPTION" sortable="true" search="true" searchoptions="{sopt:['cn','eq']}" editable="true" editrules="{required: true}"/>
												        <sjg:gridColumn name="status" index="status" title="STATUS" sortable="true" search="false" editable="true" editrules="{required: true}" edittype="select" editoptions="{value:'Y:Active;N:DeActive'}" width="60"/>
												        <sjg:gridColumn name="edit" title="EDIT" align="center" formatter="formatclause" search="false" width="50"/>
												    </s:elseif>
												    <s:elseif test = 'menuType=="error"'>
												        <sjg:gridColumn name="errorID" index="errorID" title="ERROR ID" sortable="true" search="false" editable="true" editrules="{required: false}" onkeyup="false" width="50"/>
												        <sjg:gridColumn name="errorDesc" index="errorDesc" title="ERROR DESCRIPTION" search="true" searchoptions="{sopt:['cn','eq']}" editable="true" editrules="{required: true, edithidden: true }" width="100"/>
												        <sjg:gridColumn name="stagename" index="stagename" title="STAGE NAME" search="false" editable="true" editrules="{required: true, edithidden: true }" width="100"/>
												        <sjg:gridColumn name="productname" index="productname" title="PRODUCT NAME" search="false" editable="true" editrules="{required: true, edithidden: true }" width="60"/>
												        <sjg:gridColumn name="status" index="status" title="STATUS" sortable="true" search="false" editable="true" editrules="{required: true}" edittype="select" editoptions="{value:'Y:Active;N:DeActive'}" width="40"/>
												        <sjg:gridColumn name="edit" title="EDIT" align="center" formatter="formaterror" search="false" width="40"/>
												    </s:elseif>
												    <s:elseif test = 'menuType=="executivemaster"'>
												    	<sjg:gridColumn name="exmexecutiveId" index="exmexecutiveId" title="EXECUTIVE ID" sortable="true" search="false" editable="true" editrules="{required: false}" onkeyup="false" width="130"/>
												        <sjg:gridColumn name="executiveName" index="executiveName" title="EXECUTIVE NAME" sortable="true" searchoptions="{sopt:['cn','eq']}" editable="true" editrules="{required: false}" onkeyup="false" width="150"/>
												        <sjg:gridColumn name="exmEffectiveDate" index="exmEffectiveDate" title="EFFECTIVE DATE" search="false" editable="true" editrules="{required: true, edithidden: true }" width="130"/>
												        <sjg:gridColumn name="exmStatus" index="exmStatus" title="STATUS" sortable="true" search="false" editable="true" editrules="{required: true}" edittype="select" editoptions="{exmStatus:'Y:Active;N:DeActive'}" width="60"/>
												        <sjg:gridColumn name="edit" title="EDIT" align="center" formatter="formateExecutiveMaster" search="false" width="40"/>
												    </s:elseif>
												    </sjg:grid>
									        	</td>
									 			<td width="5%"></td>
							        		</tr>
							        	</table>
							    	</td></tr>
							    	<s:if test = '"constantdetail".equalsIgnoreCase(menuType)' >
								    	<tr>
								    		<td width="5%" align="center">
							 					<s:hidden name="categoryID"/>
							 					<input type="button" class="btn" value="Back" onclick="fnCall('constantMaster')"/>
							 				</td>
							 			</tr>
						 			</s:if>
								</table>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		   </div>
            </div>
        </div>
    </div>
    <s:hidden name="transID" id="transID"/>
    <s:hidden name="coverID" id="coverID"/>
    <s:hidden name="menuType" id="menuType"/>
    <s:form name="info" id="info">
    </s:form>
    </body>
</html>