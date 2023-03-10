<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
	<head>
        <meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<meta name="description" content="">
		<meta name="keywords" content="Maan Sarovar, Sea Trans, Marine Insurance">	
		<meta name="author" content="">
		<title> ::: Madison General - Commodity Selection ::: </title>
		<link rel="shortcut icon" href="<%=request.getContextPath()%>/images/favicon.ico" />
		<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/bootstrap/css/demo.css" />
		<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/bootstrap/css/font-awesome.min.css" />
		<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/bootstrap/css/bootstrap-multiselect.css" />
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/bootstrap/css/datepicker3.css">
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/bootstrap/css/slimmenu.css">
		<link href="<%=request.getContextPath()%>/bootstrap/css/footable-0.1.css" rel="stylesheet" type="text/css">
		<link href="<%=request.getContextPath()%>/bootstrap/css/table-res.css" rel="stylesheet" type="text/css">
		<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/bootstrap/css/bootstrap.min.css" />
		<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/bootstrap/css/style.css" />
		<script type="text/javascript" src="<%=request.getContextPath()%>/js/common.js"></script>
		<script src="<%=request.getContextPath()%>/bootstrap/js/modernizr.custom.63321.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/bootstrap/js/jquery-1.9.1.min.js"></script>		
	</head>
<body>
<s:form name="commoditySelection" id="commodityForm" theme="simple" >
<s:set var="disable" value='%{(quoteStatus=="RA")||(endTypeId!=null && endTypeId.length() > 0)}'/>
<s:set var="disable2" value="%{endTypeId!=null && endTypeId.length() > 0}"/>
<div class="table0">
	<div class="tablerow">
		<div class="panel panel-primary">
			<div class="panel-heading">
				<s:label key="commodity.commodityInfo" />
			</div>
			<div class="panel-body" style="padding: 0px;">
				<span style="color:red;"> <s:actionerror/> </span><br/>
				<s:set var="comLst" value="commodityList"/>
				<s:set var="disable1" value="%{((#session.user1==getText('admin')) || status2=='edit') && !'delete'.equals(status1) && (!''.equals(#comLst.size()>0 ? #comLst[0].COMMODITY_ID : ''))}"/>
				<div class="boxcontent">
					<s:if test='productId==11'>
					<div class="textfield50">
						<div class="text"><s:text name="Commodity Name" /><font color="red">*</font></div>
						<div class="tbox" id="categoryBased">
							<s:select name="commodityId" list="goodsCategoryList" headerKey="" headerValue="-Select-" listKey="CODE" listValue="CODEDESC" disabled="%{#disable1}" value='#comLst.size()>0 ? #comLst[0].COMMODITY_ID : ""' onchange="this.form.selectCommodity.value=this.value;this.form.__checkbox_selectCommodity.value=this.value;callAjax(this.value);" cssClass="inputBoxS"/>
							<s:checkbox  name="selectCommodity" value="true" fieldValue='%{#comLst.size()>0 ? #comLst[0].COMMODITY_ID : ""}' disabled="#disable" cssStyle="visibility:hidden;"/>
						</div>
					</div>
					</s:if>
					<s:else>
					<div class="textfield50">
						<div class="text"><s:text name="Commodity Name" /><font color="red">*</font></div>
						<div class="tbox" id="categoryBased">
							<s:select name="commodityId" list="goodsCategoryList" headerKey="" headerValue="-Select-" listKey="CODE" listValue="CODEDESC" disabled="%{#disable1}" value='#comLst.size()>0 ? #comLst[0].COMMODITY_ID : ""' onchange="this.form.selectCommodity.value=this.value;this.form.__checkbox_selectCommodity.value=this.value;" cssClass="inputBoxS"/>
							<s:checkbox  name="selectCommodity" value="true" fieldValue='%{#comLst.size()>0 ? #comLst[0].COMMODITY_ID : ""}' disabled="#disable" cssStyle="visibility:hidden;"/>
						</div>
					</div>
					</s:else>
					<div class="textfield50A">
						<div class="text"><s:text name="Goods Description" /><font color="red">*</font></div>
						<div class="tbox">
							<s:textarea name="commodityDesc" onkeyup="textLimit(this,450)" value='%{#comLst.size()>0 ? #comLst[0].COMMODITY_DESC==null? #comLst[0].COMMODITY_NAME:#comLst[0].COMMODITY_DESC: ""}' cssClass="inputBoxA" disabled="#disable" cols="34" rows="3" cssStyle="width: 100%;" />
						</div>
					</div>
					<div class="textfield50">
						<div class="text">
							<s:text name="commodity.insuredValue" /><font color="red">*</font>
						</div>
						<div class="tbox">
							<s:textfield name="insuredValue" cssClass="inputBox" maxLength="13" onkeyup="checkNumbers(this);"  value='%{#comLst.size()>0 ? #comLst[0].SUM_INSURED : ""}' disabled="#disable" />
						</div>
					</div>
					<s:hidden name="commodityPackDesc" value=""/>
					<div class="textfield50">
						<div class="text">
							<s:text name="commodity.invoiceNo" />
						</div>
						<div class="tbox">
							<s:textfield name="invoiceNo" cssClass="inputBox" maxLength="50" disabled="%{#disable2}"  value='%{#comLst.size()>0 ? #comLst[0].INVOICE_NUMBER : ""}' />
						</div>
					</div> 
					<div class="textfield50">
						<div class="text">
							<s:text name="commodity.invoiceDate" />
						</div>
						<div class="tbox">
							<s:textfield id="invoiceDate" name="invoiceDate" onblur="check_date(this);" cssClass="inputBox" cssStyle="background: transparent;" disabled="%{#disable2}" value='%{#comLst.size()>0 ? #comLst[0].INVOICE_DATE : ""}' readonly="true" />
						</div>
					</div> 
					<div class="textfield50">
						<div class="text">
							<s:text name="Supplier Name" />
						</div>
						<div class="tbox">
							<s:textfield name="supplierName" cssClass="inputBox" maxLength="50" disabled="%{#disable2 || #disable}"  value='%{#comLst.size()>0 ? #comLst[0].SUPPLIER_NAME: ""}' />
						</div>
					</div>
					<s:if test='productId==11'>
					<div class="textfield50">
						<div class="text">
							<s:label key="commodity.fragile" />
						</div>
						<div class="tbox">
							<div id="ajaxFragile" >
								<s:radio name="fragile[0]" list="#{true:'Yes',false:'No'}" disabled="#disable" value='%{#comLst.size()>0 ? #comLst[0].FRAGILE_SELECTED: false}'/>
							</div>
						</div>
					</div>
					</s:if>
					<s:else>
					<div class="textfield50">
						<div class="text">
							<s:label key="commodity.fragile" />
						</div>
						<div class="tbox">
							<s:radio name="fragile[0]" list="#{true:'Yes',false:'No'}" disabled="#disable" value='%{#comLst.size()>0 ? #comLst[0].FRAGILE_SELECTED: false}'/>
						</div>
					</div>
					</s:else>
					<s:if test='%{"3".equals(#session.product_id) && ("admin".equalsIgnoreCase(#session.usertype) || "RSAIssuer".equalsIgnoreCase(#session.usertype))}'>
					<div class="textfield50A">
						<div class="text"><s:label key="commodity.excessdesc" /></div>
						<div class="tbox">
							<s:textarea name="excessDesc" onkeyup="textLimit(this,450)" value='%{#comLst.size()>0 ? #comLst[0].EXCESS_DESCRIPTION : ""}' disabled='%{"11".equals(#session.product_id)?"true":#disable}' cssClass="inputBoxA" cols="34" rows="3" cssStyle="width: 100%;" />
						</div>
					</div>
					</s:if>
					<s:else>
						<s:hidden name="excessDesc" value='%{#comLst.size()>0 ? #comLst[0].EXCESS_DESCRIPTION : ""}'/>
					</s:else> 
					<br class="clear" />
				</div>						
			</div>
		</div>
	</div>
	<div class="tablerow" align="center">
		<s:hidden name="applicationNo" />
		<s:hidden name="refNo" />
		<s:hidden name="status2" id="status2"/>
		<s:hidden name="endTypeId" />
		<s:hidden name="quoteStatus" />
		<s:hidden name="originCountry" />
		<s:hidden name="destCountry" />
	    <input type="button" value="Close" onclick="disableForm(this.form,false,'');fnsubmit('close','');" class="btn btn-sm btn-danger"/>&nbsp;&nbsp;&nbsp;
		<input type="button"  value="Submit" onclick="disableForm(this.form,false,'');fnsubmit('submit','');" class="btn btn-sm btn-success"/>&nbsp;&nbsp;&nbsp;		
		<s:if test='%{(applicationNo !=null && applicationNo.length() > 0) && (viewList.get(0).get("COMMODITY_CODE")!=null && !"".equals(viewList.get(0).get("COMMODITY_CODE"))) && !((quoteStatus=="RA" ||(endTypeId!=null && endTypeId.length() > 0)))}'>
			<input type="button"  value="Delete" onclick="disableForm(this.form,false,'');fnsubmit('delete',<s:property value="viewList.get(0).get('COMMODITY_CODE')"/>);" class="btn btn-sm btn-warning" /> 
		</s:if>
	</div>
</div>
</s:form>
<script type="text/javascript" src="<%=request.getContextPath()%>/bootstrap/js/jquery.slimmenu.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/bootstrap/js/jquery.easing.min.js"></script>	
<script src="<%=request.getContextPath()%>/bootstrap/js/bootstrap-datepicker.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/bootstrap/js/bootstrap-3.2.0.min.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/bootstrap/js/app-js.js" type="text/javascript"></script>
<script type="text/javascript">
for (i=0; i<document.forms.length; i++) {
            document.forms[i].setAttribute("AutoComplete","off");
}
if(window.opener.quotation.endTypeId.value!='') {
	try {
		enableForm(document.commoditySelection,false,'<s:property value="%{fields}"/>');
	} catch(err){}
}
function fnsubmit(status,val) {
	try {
	   if(status=='addmore'){
			document.commoditySelection.action='addCommodityQuotation.action?status1='+status;
			document.commoditySelection.submit();
		} else if(status=='edit') {
			<%--/***Multiple Commodity Removed -- Start ****/--%>
			//document.getElementById('status2').value='edit';
			document.commoditySelection.action='addCommodityQuotation.action?status1='+status+'&commodityCode='+val;
			//document.commoditySelection.submit();
			if(!(document.getElementById('status2').value=='edit'))
				document.commoditySelection.submit();
				<%--/***Multiple Commodity Removed -- End ****/--%>
		} else if(status=='delete') {
			document.commoditySelection.action='addCommodityQuotation.action?status1='+status+'&commodityCode='+val;
			document.commoditySelection.submit();
		} else {
			document.commoditySelection.action='addCommodityQuotation.action?status1='+status;
			document.commoditySelection.submit();
		}
	} catch(err){}
}
function removeComma(object) {
	try {
		replaceComma(document.commoditySelection,'insuredValue');
		document.commoditySelection.submit();
	} catch(err) { console.log(err); }
}
function fngetCommodityList(val, id) {
	postRequest('${pageContext.request.contextPath}/'+id+'Quotation.action?category='+val, id);
}
function callAjax(value) {
	postRequest('${pageContext.request.contextPath}/getFrgileQuotation.action?category='+value, "ajaxFragile");
}
<%--/***Multiple Commodity Removed -- Start ****/--%>
<s:if test='(applicationNo !=null && applicationNo.length() > 0) && !("delete".equals(status2)||"else".equals(status2)) && viewList.get(0).get("COMMODITY_CODE")!=null && !"".equals(viewList.get(0).get("COMMODITY_CODE"))' >
		fnsubmit('edit','<s:property value="viewList.get(0).get('COMMODITY_CODE')"/>'); 
</s:if>
<%--/***Multiple Commodity Removed -- Start ****/--%>
</script>
</body>
</html>