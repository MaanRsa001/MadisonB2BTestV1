<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
</head>
<body >
  <s:form name="coverDisplay" theme="simple">
	<s:if test='"coverInfo".equalsIgnoreCase(optionMode)'>
		<div class="panel panel-primary">
			<div class="panel-heading">				
				<s:text name="travel.schemeCovers"/> : <s:property value="scheme_Covers"/>&nbsp;&nbsp;&nbsp;
		 		<s:text name="travel.travelCovers"/> : <s:property value="travel_Covers"/>
		 		<div class="pullRight">
		 			<button type="button" class="btn btn-sm btn-danger" data-dismiss="modal"> <i class="fa fa-remove"></i> </button>
		 		</div>
		 		<br class="clear"/>
			</div>
			<div class="panel-body">
				<table width="100%" class="footable">
					<thead>
					<tr>
						<th width="60%"><s:label key="travelInfo.cover" /></th>
						<th width="20%"><s:label key="travelInfo.schme" />(<s:property value="#session.BrokerDetails.CurrencyAbb"/>)</th>
						<th width="20%"><s:label key="travelInfo.schme" />(<s:label key="travelInfo.destnCurrency" />)</th>
					</tr>
					</thead>
					<tbody>
					<s:if test="cover.size()>0">
						<s:iterator value="cover" id="cover">
							<tr>
								<td align="left"><s:property value="CLAUSES_DESCRIPTION"/></td>
								<td align="right"><s:property value="VALUE"/></td>
								<td align="right"><s:property value="DISTINATION_VALUE"/></td>
							</tr>
						</s:iterator>
					</s:if>
					<s:else>
						<tr>
					    	<td colspan="3" align="center" width="100" ><s:label key="travel.noCoverAvailable" /></td>
						</tr>
				    </s:else>
					</tbody>
				</table>
			</div>
		</div>		
  	</s:if>
  	<s:if test='"travelCoverInfo".equalsIgnoreCase(optionMode)'>
	  	<div class="panel panel-primary">
			<div class="panel-heading">
				<s:text name="travel.cover.details"></s:text>				
		 		<div class="pullRight">
		 			<button type="button" class="btn btn-sm btn-danger" data-dismiss="modal"> <i class="fa fa-remove"></i> </button>
		 		</div>
		 		<br class="clear"/>
			</div>
			<div class="panel-body">
				<table width="100%" class="footable">
			  		<tr class="bg" >
						<td class="heading" align="center">south - East Asia</td>
						<td align="left">Brunei, Cambodia, Indonesia, Laos, Malaysia, Myanmar, Philippines, Thailand and Vietnam</td>
					</tr>
					<tr class="bg" >
						<td class="heading" align="center">Asia </td>
						<td align="left">Pacific Australia, China, Hong Kong, India, Japan, Korea, Macau, New Zealand, Sri Lanka, Taiwan, Brunei, Cambodia, Indonesia, Laos, Malaysia, Myanmar, Philippines, Thailand and Vietnam</td>
					</tr>
					<tr class="bg" >
						<td class="heading" align="center">Worldwide</td>
						<td align="left">including USA & Canada</td>
					</tr>
				</table>
			</div>
		</div>
	  	
  	</s:if>
  </s:form>
  </body>
</html>
