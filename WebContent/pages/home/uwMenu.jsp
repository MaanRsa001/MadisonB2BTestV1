<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html <s:if test="locale.language == 'ar'">dir="rtl"</s:if> >
	<head>
		
	</head>
	<body>
	 <s:set var="disable1" value="%{menuType=='RA'}"/>
	  <s:form name="uwMenu" method="post" theme="simple">
		<div class="panel">
			<%--
			<div class="panel-heading">
				<s:text name="label.underwriting.coverages"/>
			</div>
			--%>
			<div class="" style="padding: 10px;">
				<div class="boxcontent">
					<span style="color:red;"> <s:actionerror/> </span>
				</div>
				<s:set var="cover" value="%{coverageList[0]}"/>
				
				<div>
					<s:label value="Has any insurer in respect of the Proposer or any Partner or Director of the proposer ever" cssStyle="color:red;"/>
				</div>
				
				<div class="boxcontent tCols50" style="margin: 0 auto;">
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
					<div class="textfieldAV">
						<s:text name="Are your premises built of brick, stone or concrete and roofs with slates, tiles, metal, asbestos or concrete?"/>
					</div>
					<div class="textfieldAV">
						<s:radio name="consDays" id="consDays" list="#{'Y':'Yes','N':'No'}" value='%{#cover.UNATTENDED_60_CONSECUTIVE_DAYS=="Y"?"Y":"N"}' disabled="disable1"/>
					</div>
					<br class="clear" />
					<div class="textfieldAV">
						<s:text name="Are the premises currently in a good state of repair"/>
					</div>
					<div class="textfieldAV">
						<s:radio name="decRej" id="decRej" list="#{'Y':'Yes','N':'No'}" value='%{#cover.DECLINED_CANELLED_CONDITIONS=="Y"?"Y":"N"}' disabled="disable1"/>
					</div>
					<br class="clear" />
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
					<div class="textfieldAV">
						<s:text name="build.type.property"/>
					</div>
					<div class="textfieldAV">
						<s:select name="typeProperty" id="typeProperty" list="#{'HOUSE':'HOUSE','FLAT':'FLAT','SEMI-DETACHED':'SEMI-DETACHED','DETACHED':'DETACHED'}" headerKey="" headerValue="---Select---" cssClass="inputBoxS" value="%{#cover.PROPERTY_TYPE}" disabled="disable1"/>
					</div>
					<br class="clear" />
				</div>
				<div class="boxcontent" align="center" style="margin-top: 5px;">
					<s:if test='"Y".equals(tractorCoverStatus)'>
						<s:if test='"admin".equalsIgnoreCase(#session.usertype)'>
	        				<s:submit cssClass="btn btn-sm btn-danger" value="Back" onclick="fnsubmit('tractorPremiuminfoHome');"/>
	        			</s:if>
	        			<s:else>
	        				<s:submit cssClass="btn btn-sm btn-danger" value="Back" onclick="fnsubmit('edittractorHome');"/>
	        			</s:else>
					</s:if>
					<s:elseif test='"Y".equals(paCoverStatus)'>
						<s:if test='"admin".equalsIgnoreCase(#session.usertype)'>
							&nbsp;&nbsp;&nbsp;
	        				<s:submit type="button" name="Back" value="Back" cssClass="btn btn-sm btn-danger" onclick="fnsubmit('paPremiuminfoHome');"/>
	        			</s:if>
	        			<s:else>
	        				&nbsp;&nbsp;&nbsp;
	        				<s:submit type="button" name="Back" value="Back" cssClass="btn btn-sm btn-danger" onclick="fnsubmit('paCoverHome');"/>
	        			</s:else>
					</s:elseif>
					<s:else>
						&nbsp;&nbsp;&nbsp;
						<s:submit cssClass="btn btn-sm btn-danger" value="Back" onclick="fnsubmit('getBackHome');"/>
					</s:else>
					&nbsp;&nbsp;&nbsp;
					<input type="button" name="submitgs" class="btn btn-sm btn-success" value="Proceed" onclick="proceed('saveUWDetailsHome.action')"/>
				</div>
			</div>
		</div>
		<s:hidden name="quoteNo"/>
		<s:hidden name="applicationNo"/>
		<s:hidden name="selectTab"/>
		<s:hidden name="menuType"/>
		<s:hidden name="tabInfo"/>
		</s:form>
	   <script type="text/javascript">
	   	function proceed(url){
   				disableForm(document.uwMenu,false,'');
				document.forms['uwMenu'].action = "${pageContext.request.contextPath}/" + url;
				document.forms['uwMenu'].submit();
		   	}
			function getpastThr(val){
		   		if("Y"==val){
		   			document.getElementById("past3years").style.display = "";
		   		}else{
		   			document.getElementById("past3years").style.display = "none";
		   		}
		   		return true;
	   		}
	   		function validamt(val){
				var value=val.value;
				if(value!=null){
					val.value = value.replace(/[^0-9.]/g,'');
				}
			}
			function fnsubmit(action) {
				document.uwMenu.action = "${pageContext.request.contextPath}/" + action;
				document.uwMenu.submit();
			}
	   </script>
	</body>
</html>