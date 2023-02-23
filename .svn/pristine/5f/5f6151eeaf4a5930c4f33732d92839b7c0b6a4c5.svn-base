<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
       <meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta name="description" content="">
	<meta name="keywords" content="Maan Sarovar, Sea Trans, Marine Insurance">	
	<meta name="author" content="">
	<title> ::: Madison General - Clauses Information ::: </title>
	<link rel="shortcut icon" href="<%=request.getContextPath()%>/images/favicon.ico" />		
	<script type="text/javascript" src="js/common.js"></script>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/bootstrap/css/bootstrap.min.css" />
	<link href="<%=request.getContextPath()%>/bootstrap/css/style.css" rel="stylesheet" type="text/css" />	
</head>
<body> 
<s:form name="clauses" theme="simple" action="updateConditionsPremium.action">
<div class="table0">
	<div class="tablerow">
		<s:actionmessage/>
	</div>
	<div class="tablerow">
		<s:if test='#session.user1 == "admin" || "yes".equalsIgnoreCase(editStatus)'>
			<s:if test="conditionsList['clausesDesc'].size > 0">
				<div class="panel panel-primary">
					<div class="panel-heading">
						<s:label key="premiumInfo.clauses" />
					</div>
					<div class="panel-body">
						<table class="footable" width="100%;">
							<tbody>
							<s:iterator value="conditionsList['clausesDesc']" var="clauses">
					    		<s:if test="%{CODE!=null}">
					    			<tr>
										<td width="10%" align="center"><s:checkbox name="" id="clausesDesc%{CODE}Check" fieldValue="%{CODE}" value="true" onclick="fnRemoveClauses('clausesDesc%{CODE}');"/><s:hidden name="clausesId" value="%{CODE}"/> </td>
										<td width="90%"><s:textarea id="clausesDesc%{CODE}" name="clausesDesc" value="%{CODEDESC}" cols="119" onkeyup="textLimit(this,250)" cssClass="inputBoxA" cssStyle="width: 100%;" /></td>
									</tr>
						  		</s:if>
					    	</s:iterator>
					    	<s:iterator value="conditionsList['extraClausesDesc']" >
					    		<s:if test="%{CODE!=null}">
					    			<tr>
										<td width="10%" align="center"><s:checkbox name="" id="warDesc%{CODE}Check" fieldValue="%{CODE}" value="true" onclick="fnRemoveClauses('warDesc%{CODE}');"/><s:hidden name="warId" value="%{CODE}"/> </td>
										<td width="90%"><s:textarea id="warDesc%{CODE}" name="warDesc" value="%{CODEDESC}" cols="119" onkeyup="textLimit(this,250)" cssClass="inputBoxA" cssStyle="width: 100%;" /></td>
									</tr>
							  	</s:if>
					    	</s:iterator>
							</tbody>
						</table>						
					</div>
				</div>
			</s:if>
			<s:if test="conditionsList['exclusionsDesc'].size > 0">
				<div class="panel panel-primary">
					<div class="panel-heading">
						<s:label key="premiumInfo.exclusions" />
					</div>
					<div class="panel-body">
						<table class="footable">
							<tbody>
							<s:iterator value="conditionsList['exclusionsDesc']" >
	    						<s:if test="%{CODE!=null}">	
					    			<tr>
										<td width="10%" align="center"><s:checkbox name="" id="exclusionDesc%{CODE}Check" fieldValue="%{CODE}" value="true" onclick="fnRemoveClauses('exclusionDesc%{CODE}');"/><s:hidden name="exclusionId" value="%{CODE}"/> </td>
										<td width="90%"><s:textarea id="exclusionDesc%{CODE}" name="exclusionDesc" value="%{CODEDESC}" cols="119" onkeyup="textLimit(this,250)"/></td>
									</tr>
						  		</s:if>
					    	</s:iterator>
							</tbody>
						</table>						
					</div>
				</div>
			</s:if>
			<s:if test="conditionsList['warrantyDesc'].size > 0">
				<div class="panel panel-primary">
					<div class="panel-heading">
						<s:label key="premiumInfo.warranties" />
					</div>
					<div class="panel-body">
						<table class="footable">
							<tbody>
							<s:iterator value="conditionsList['warrantyDesc']" >
	    						<s:if test="%{CODE!=null}">
					    			<tr>
										<td width="10%" align="center"><s:checkbox name="" id="warrantyDesc%{CODE}Check" fieldValue="%{CODE}" value="true" onclick="fnRemoveClauses('warrantyDesc%{CODE}');"/><s:hidden name="warrantyId" value="%{CODE}"/> </td>
										<td width="90%"><s:textarea id="warrantyDesc%{CODE}" name="warrantyDesc" value="%{CODEDESC}" cols="119" onkeyup="textLimit(this,250)"/></td>
									</tr>
						  		</s:if>
					    	</s:iterator>
							</tbody>
						</table>						
					</div>
				</div>
			</s:if>
		</s:if>
		<s:else>
			<s:if test="conditionsList['clausesDesc'].size > 0">
				<div class="panel panel-primary">
					<div class="panel-heading">
						<s:label key="premiumInfo.clauses" />
					</div>
					<div class="panel-body">
						<ol type="1">
							<s:iterator value="conditionsList['clausesDesc']" var="clauses">			  		
						  		<li><s:property value="CODEDESC"/></li>
					    	</s:iterator>		
					    	<s:iterator value="conditionsList['extraClausesDesc']" >			  		
							  	<li><s:property value="CODEDESC"/></li>
					    	</s:iterator>
						</ol>												
					</div>
				</div>
			</s:if>
			<s:if test="conditionsList['exclusionsDesc'].size > 0">
				<div class="panel panel-primary">
					<div class="panel-heading">
						<s:label key="premiumInfo.exclusions" />
					</div>
					<div class="panel-body">
						<ol type="1">
							<s:iterator value="conditionsList['exclusionsDesc']" >			  		
						  		<li><s:property value="CODEDESC"/></li>
					    	</s:iterator>
						</ol>												
					</div>
				</div>
			</s:if>
			<s:if test="conditionsList['warrantyDesc'].size > 0">
				<div class="panel panel-primary">
					<div class="panel-heading">
						<s:label key="premiumInfo.warranties" />
					</div>
					<div class="panel-body">
						<ol type="1">
							<s:iterator value="conditionsList['warrantyDesc']" >			  		
						  		<li><s:property value="CODEDESC"/></li>
					    	</s:iterator>
						</ol>												
					</div>
				</div>
			</s:if>
		</s:else>
	</div>
	<br class="clear" />
	<div class="tablerow" align="center">
		<s:hidden name="applicationNo"/>			
		<input type="button" name="submit" class="btn btn-sm btn-danger" value="Close" onclick="window.close();return true;"/> &nbsp;&nbsp;&nbsp;
		<s:if test='(#session.user1 == "admin" || ("RSAIssuer".equalsIgnoreCase(#session.usertype) && "yes".equalsIgnoreCase(editStatus))) && !hasActionMessages()'>
			<s:submit type="submit" name="submit" key="commodity.submit" cssClass="btn btn-sm btn-success" onclick="disableForm(this.form,false,'');window.opener.premiumInfo.updateClauses.value='N'"/>
		</s:if>
	</div>
</div>
</s:form>
</body>
<script type="text/javascript">
function fnRemoveClauses(id){
	if(document.getElementById(id+'Check').checked){
		document.getElementById(id).disabled=false;
	}else{
		document.getElementById(id).value='';
		document.getElementById(id).disabled=true;
	}
}
</script>
</html>