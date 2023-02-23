<%@ page isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html <s:if test="locale.language == 'ar'">dir="rtl"</s:if> >
	<head>
	</head>
	<body>
		<div class="row">
			<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
				<div class="panel panel-default">
					<div class="panel-heading">
						<div class="row">
							<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
								<div class="btn-group" role="group" aria-label="...">							
								  <button type="button" title="Customer Information" class="btnGroupMargin btn btn-lg btn-warning"><i class="fa fa-user"></i></button>
								  <button type="button" title="Premium Comparison" class="btnGroupMargin btn btn-lg btn-success active"><i class="fa fa-eye"></i></button>
								</div>
							</div>
							<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6 text-success">
								<span class="textHeading"><s:text name="label.quoteDetail" /> </span> 
							</div>
						</div>
					</div>		
				</div>
			</div>
		</div>
		<div class="tablerow" style="padding:10px; background:#F8F8F8">
			<s:set var="coverReferralRemarks" value="%{coverReferralRemarks}"/>
			<div class="panel panel-primary">
				<div class="panel-heading">
					<s:if test='%{"admin".equalsIgnoreCase(#session.usertype)}'>
						<s:text name="head.general.info"/>
					</s:if>
					<s:else>
						<s:form name="GeneralInfo" action="packageSelectionHome" theme="simple">
							<s:text name="head.general.info"/>
							<s:submit type="button" value="Edit Quote" cssClass="btn btn-sm btn-danger pullRight"/>
							<s:hidden name="quoteNo"/>
							<s:hidden name="applicationNo"/>
							<s:hidden name="menuType"/>
							<br class="clear"/>
						</s:form>
					</s:else>
				</div>
				<div class="panel-body" style="padding: 0px;">					
					<div class="boxcontent">
      					<div class="textfield33V">
      						<div class="textV">
								<s:text name="label.quoteno"/>
							</div>
							<div class="tboxV">												   
								 ${personalInfo[0].QUOTE_NO}
							</div>
      					</div>
      					<div class="textfield33V">
      						<div class="textV">
								<s:text name="policy.start.date"/>
							</div>
							<div class="tboxV">												   
								${personalInfo[0].INCEPTION_DATE1} 
							</div>
      					</div>
      					<div class="textfield33V">
      						<div class="textV">
								<s:text name="label.expiry.date"/>
							</div>
							<div class="tboxV">												   
								${personalInfo[0].COVER_END_PERIODS} 
							</div>
      					</div>
      					<%--
      					<div class="tboxV">
							<s:if test='!"".equalsIgnoreCase(coverReferralRemarks) && !"admin".equalsIgnoreCase(userType) && !"RA".equalsIgnoreCase(menuType)'>
								Referral
							</s:if>
							<s:else>
								<s:set var="total" value="%{totalPremium}" scope="page"/>
								<s:if test='"paCover".equalsIgnoreCase(generalPremiumStatus)'>
									<s:set var="total" value="%{homePremium}" scope="page"/>
								</s:if>
								<s:elseif test='"motor".equalsIgnoreCase(generalPremiumStatus)'>
									<s:set var="total" value="%{@java.lang.Double@valueOf(homePremium)+@java.lang.Double@valueOf(paPremium)}" scope="page"/>
								</s:elseif>
								<s:else>
									<s:if test='"+".equals(personalInfo[0].EXCESS_SIGN)'>
										<s:set var="total" value="%{@java.lang.Double@valueOf(personalInfo[0].EXCESS_PREMIUM)+@java.lang.Double@valueOf(totalPremium)}" scope="page"/>
									</s:if>
									<s:else>
										<s:set var="total" value="%{@java.lang.Double@valueOf(totalPremium)-@java.lang.Double@valueOf(personalInfo[0].EXCESS_PREMIUM)}" scope="page"/>
									</s:else>
									<s:set var="total" value="%{@java.lang.Double@valueOf(policyFee)+@java.lang.Double@valueOf(#attr.total)}" scope="page"/>
								</s:else>
								<s:property value="getText('{0,number,#,##0.00}',{@java.lang.Double@valueOf(#attr.total)})"/>&nbsp;<s:property value="#session.BrokerDetails.CurrencyAbb"/>
							</s:else>
						</div>
						--%>
      					<br class="clear"/>
      				</div>
      			</div>
      		</div>
      	</div>
	</body>
</html>