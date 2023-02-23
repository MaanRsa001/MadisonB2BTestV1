<%@ page isELIgnored="false" %>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<s:if test='"city".equals(reqFrom)'>
<div id="cityAjax">
	<s:select name="city" id="city" list="cityList" cssClass="form-control" headerKey="" headerValue="-select-" listKey="CITY_ID" listValue="CITY_NAME"/>
</div>
</s:if>
<s:if test='"validation".equals(reqFrom)'>
	<div id="errorDesc">
		<font color="red"><s:actionerror cssStyle="list-style:none;"/> </font>
		<font color="green"><s:actionmessage cssStyle="list-style:none;"/> </font>
	</div>
</s:if>


<script type="text/javascript">
try{
    <s:if test="hasActionMessages()">
    //alert("No error");
    imgUpload();
    </s:if>
    <s:if test="hasActionErrors()">
    $("html, body").animate({ scrollTop: 0 }, 600);
    	//alert("error");
    </s:if>
	
 }catch(err){
	   console.log(err);
 }
</script>