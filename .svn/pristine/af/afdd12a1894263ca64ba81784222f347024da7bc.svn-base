<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<div class="panel panel-primary">
	<div class="panel-heading">
		<s:text name="Underwriting Questions"/>
	</div>
	<div class="panel-body">
		<div>
			<s:label value="Has any insurer in respect of the Proposer or any Partner or Director of the proposer ever" cssStyle="color:red;"/>
		</div>
		<div class="boxcontent tCols50" style="margin: 0 auto;">
			<s:set var="cover" value="%{coverageList[0]}"/>
			<div class="textfieldAV">
				<s:text name="Declined a proposal?"/>
			</div>
			<div class="textfieldAV">
				<s:radio name="buildCon" id="buildCon" list="#{'Y':'Yes','N':'No'}" value='%{(#cover.BUILT_OF_CONCRETE=="Y"?"Y":"N")}' disabled="disable1"/>
			</div>
			<br class="clear" />
			<div class="textfieldAV">
				<s:text name="Cancelled or refused to renew a Policy?"/>
			</div>
			<div class="textfieldAV">
				<s:radio name="liveAccom" id="liveAccom" list="#{'Y':'Yes','N':'No'}" value='%{#cover.PRIVATE_LIVING_ACCOMODATION=="Y"?"Y":"N"}' disabled="disable1"/>
			</div>
			<br class="clear" />
			<div class="textfieldAV">
				<s:text name="Imposed special conditions?"/>
			</div>
			<div class="textfieldAV">
				<s:radio name="reclaimLand" id="reclaimLand" list="#{'Y':'Yes','N':'No'}" value='%{#cover.BUILT_ON_RECLAIMED_LAND=="Y"?"Y":"N"}' disabled="disable1"/>
			</div>
			<br class="clear" />
			<div style="display:none;">
				<div class="textfieldAV">
					<s:text name="Are your premises built of brick, stone or concrete and roofs with slates, tiles, metal, asbestos or concrete?"/>
				</div>
				<div class="textfieldAV">
					<s:radio name="consDays" id="consDays" list="#{'Y':'Yes','N':'No'}" value='%{#cover.UNATTENDED_60_CONSECUTIVE_DAYS=="Y"?"Y":"N"}' disabled="disable1"/>
				</div>
				<br class="clear" />
			</div>
			<div style="display:none;">
				<div class="textfieldAV">
					<s:text name="Are the premises currently in a good state of repair"/>
				</div>
				<div class="textfieldAV">
					<s:radio name="decRej" id="decRej" list="#{'Y':'Yes','N':'No'}" value='%{#cover.DECLINED_CANELLED_CONDITIONS=="Y"?"Y":"N"}' disabled="disable1"/>
				</div>
				<br class="clear" />
			</div>
			<div style="display:none;">
				<div class="textfieldAV">
					<s:text name="Are the premises currently in a good state of repair"/>
				</div>
				<div class="textfieldAV">
					<s:radio name="pastThr" id="pastThr" list="#{'Y':'Yes','N':'No'}" onclick="getpastThr(this.value)" value='%{#cover.ANY_CLAIM_IN_3YRS=="Y"?"Y":"N"}' disabled="disable1"/>
				</div>
				<br class="clear" />
			</div>
			<%--
			<div id="past3years" style="display:<s:if test='%{"Y".equals(#cover.ANY_CLAIM_IN_3YRS)}'></s:if><s:else>none</s:else>;">
			--%>
			<div id="past3years" style="display:none;">
			<div class="textfieldAV">
				<s:text name="build.no.claims"/><font color="red">*</font>
			</div>
			<div class="textfieldAV">
				<s:textfield name="noClaim" id="noClaim" cssClass="inputBox" value="%{#cover.NO_OF_CLAIMS}" maxlength="2" onkeyup="checkDecimals(this);" onblur="validamt(this);" disabled="disable1"/>
			</div>
			<br class="clear" />
			<div class="textfieldAV">
				<s:text name="build.type.claim"/><font color="red">*</font>
			</div>
			<div class="textfieldAV">
				<s:iterator value="typeClaimList" var="type">
		                   		<s:checkbox name="typeClaim" cssClass="input" value="%{#type.cover_id in #cover.TYPE_OF_CLAIMS}" fieldValue="%{#type.cover_id}" disabled="disable1"/>${COVER_DESCRIPTION}<br/>
		                   	</s:iterator>
			</div>
			<br class="clear" />
			<div class="textfieldAV">
				<s:text name="build.claim.amount"/><font color="red">*</font>
			</div>
			<div class="textfieldAV">
				<s:textfield name="claimAmt" id="claimAmt" cssClass="inputBox" value="%{#cover.CLAIM_AMOUNT}" maxlength="10" onkeyup="checkDecimals(this);" onblur="validamt(this);" disabled="disable1"/>
			</div>
			<br class="clear" />
			<br class="clear" />
			</div>
			<div style="display:none;">
				<div class="textfieldAV">
					<s:text name="build.type.property"/>
				</div>
				<div class="textfieldAV">
					<s:select name="typeProperty" id="typeProperty" list="#{'HOUSE':'HOUSE','FLAT':'FLAT','SEMI-DETACHED':'SEMI-DETACHED','DETACHED':'DETACHED'}" headerKey="" headerValue="---Select---" cssClass="inputBoxS" value="%{#cover.PROPERTY_TYPE}" disabled="disable1"/>
				</div>
				<br class="clear" />
			</div>
		</div>
	</div>
</div>