<%@ page isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
	</head>
	<body>
		<table width="100%">
       	 	<s:iterator  status="st" begin="%{@java.lang.Integer@parseInt(rowNum)+1}" end="%{@java.lang.Integer@parseInt(rowNum)+1}">
       	 		<tr align="center">
       	 			<td align="center" style="width:20px;"><s:property value="%{@java.lang.Integer@parseInt(rowNum)+2}"/></td>
	 				<s:iterator value="subList" var="build" status="stat">
		        		<td style="width:154px;"><s:if test='"textbox".equals(#build.DISPLAY_TYPE)'>
		        				<s:textfield name="colDyn[%{#stat.index}]" id="colDyn[%{#stat.index}]" cssClass="inputBox" theme="simple"/>
		        			</s:if>
		        			<s:elseif test='"dropdown".equals(#build.DISPLAY_TYPE)'>
		        				<s:select name="colDyn[%{#stat.index}]" id="colDyn[%{#stat.index}]" listKey="DROPDOWN_KEY" listValue="DROPDOWN_VALUE" list="%{dynamicList.get(#build.DEST_COLUMN)}" headerKey="" headerValue="-Select-" cssClass="inputBoxS" theme="simple"/>
		        			</s:elseif>
		        		</td>
		        	</s:iterator>
		        </tr>
   			</s:iterator>
		</table>
        <table width="100%">
 			 <tr><td>
 			 	<%--
				<sj:div id="dynamic%{coverId}%{@java.lang.Integer@parseInt(rowNum)+1}part">
					<table width="100%">
       					<tr><td align="right">
 								<sj:submit name="addMoreBut" value="Add More" targets="dynamic%{coverId}%{@java.lang.Integer@parseInt(rowNum)+1}part" cssClass="btn btn-sm btn-primary" href="addRowHome.action?coverId=%{coverId}&rowNum=%{@java.lang.Integer@parseInt(rowNum)+1}"/>
						</td></tr>
					</table>
				</sj:div>
				--%>
			</td></tr>
		</table>
	</body>
</html>