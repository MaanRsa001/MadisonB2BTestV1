<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<s:div theme="simple">
	<s:if test="%{#request.comparisionDetails.get('policyType') !=null && #request.comparisionDetails.get('policyType').size()>0}">
		<s:iterator value="%{#request.comparisionDetails.get('policyType')}" var="polType" status="type">
			<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
				<div class="panel panel-primary<s:property value="#type.count" />">			        						
					<div class="panel-heading" id="preDetails1">
						<span class="pullLeft">									
							<span class="fa fa-car"></span> &nbsp; <s:property value="X_DATA_NAME"/>
						</span>
						<br/>									
						<span class="pullRight" style="color: #ffff66; font-size: 15px;">
							<input style="visibility: hidden;" type="radio" <s:property  value="%{(#request.comparisionDetails.get('selectedPolicyType') == #polType.X_ID)?'checked':''}"/> name="policyType" id="policyType${X_ID}" value="${X_ID}" />
							<s:hidden id="policyTypeRef%{X_ID}" name="policyTypeRef%{X_ID}" value="%{#polType.REFERAL_STATUS}"/>
							<span id="totaldisplay${X_ID}" style="vertical-align: middle;"></span>
						</span>
						<div class="clear"></div>			        							
					</div>
					<div class="panel-body" id="preDetailsData1" style="padding: 0px">									
						<div class="tableRow">
							<table width="100%" class="premiumTable<s:property value="#type.count" />" cellspacing="5">
								<tbody>
									<s:set var="count" value="0"/>
									<s:iterator value="%{#request.comparisionDetails.get('policyGroup')}" var="polGroup" status="group">
										<s:iterator value="%{#request.comparisionDetails.get('policyCovers'+#polGroup.GROUP_ID)}" var="covers" status="covStat">
											<s:set var="coverDetails" value="%{#request.comparisionDetails.get('policyCoverDetails'+#polGroup.GROUP_ID+#polType.X_ID+#covers.Y_DATA_NAME)}"/>
											<s:set var='img' value='""'/>
											<s:set var='alt' value='""'/>
											<s:set var='cursor' value='""'/>
											<s:set var='adddisp' value='""'/>
											<s:set var='disp' value='""'/>
											<s:set var='optCovId' value='""'/>
											<s:if test='"Y" == #coverDetails[0].IS_SELECTED'>
												<s:set var='img' value='"/images/selected_cover.png"'/>
												<s:set var='alt' value='"Default Cover"'/>
												<s:set var='cursor' value='"default"'/>
												<s:set var='optCovId' value='%{#coverDetails[0].Y_ID}'/>
												<s:set var='preVal' value='#coverDetails[0].DATA_VALUE'/>
												<s:set var='preValUSD' value='#coverDetails[0].USD_DATA_VALUE'/>
												<s:if test='"Y" == #coverDetails[0].DELETABLE'>
													<s:set var='adddisp' value='"none"'/>
													<s:set var='disp' value='"inline"'/>
												</s:if>
												<s:else>
													<s:set var='adddisp' value='"inline"'/>
													<s:set var='disp' value='"none"'/>
												</s:else>
											</s:if>
											<s:else>
												<s:if test='%{ "Y" == #coverDetails[0].IS_ADDON}'>
													<s:if test="%{ #request.comparisionDetails.get('selectedPolicyType').equalsIgnoreCase(#polType.X_ID) && optCovers.contains((#coverDetails[0].Y_ID +'~'))}">
														<s:set var='img' value='"/images/selected_cover.png"'/>
														<s:set var='alt' value='"Default Cover"'/>
														<s:set var='cursor' value='"pointer"'/>
														<s:set var='adddisp' value='"none"'/>
														<s:set var='disp' value='"inline"'/>
														<s:set var='optCovId' value='%{#coverDetails[0].Y_ID}'/>
														<s:set var='preVal' value='#coverDetails[0].DATA_VALUE'/>
														<s:set var='preValUSD' value='#coverDetails[0].USD_DATA_VALUE'/>
													</s:if>
													<s:else>
														<s:set var='img' value='"/images/add_cover.png"'/>
														<s:set var='alt' value='"Add Cover"'/>
														<s:set var='cursor' value='"pointer"'/>
														<s:set var='adddisp' value='"inline"'/>
														<s:set var='disp' value='"none"'/>
														<s:set var='preVal' value='0'/>
														<s:set var='preValUSD' value='0'/>
														<s:set var='optCovId' value=''/>
													</s:else>
												</s:if>
											</s:else>
											<s:if test='%{#covStat.index == "0" && (#polType.X_ID!=getText("MOTOR_TP_ID") || (#polType.X_ID==getText("MOTOR_TP_ID") && #polGroup.GROUP_ID<100))}'>
												<s:if test='#polGroup.GROUP_ID == "104"'>
												<tr style="display:none;"><td colspan="3"></td></tr>
												<tr style="display:none;">
													<s:if test="#polGroup.GROUP_ID<50 || #polGroup.GROUP_ID>100">
														<td width="5%"></td>
														<td width="65%">
															<span class="preText"><b><s:property value="GROUP_DESC_ENGLISH"/></b></span>
														</td>
														<td width="30%" align="right" style="font-size: 12px;">
															<s:if test='#polGroup.GROUP_ID == "0" || (#polGroup.GROUP_ID>100 && #polGroup.GROUP_ID<150)'>
																<s:if test='"N".equals(#polType.REFERAL_STATUS)'>
																	<div class="currencyLocal" <s:if test='%{!(getText("MOTOR_CURRENCY_LOCAL")).equals(currencyType)}'>style="display:none;"</s:if>>
																		<b>
																			<s:if test='%{#coverDetails[0].DATA_VALUE!=null && !"".equalsIgnoreCase(#coverDetails[0].DATA_VALUE)}'> <s:property value="currencyType"/>&nbsp; </s:if>
																			<s:property value="getText('{0,number,#,##0.00}',{#coverDetails[0].DATA_VALUE})"/>
																		</b>
																	</div>
																	<div class="currencyForeign" <s:if test='%{!(getText("MOTOR_CURRENCY_FOREIGN")).equals(currencyType)}'>style="display:none;"</s:if>>
																		<b>
																			<s:if test='%{#coverDetails[0].USD_DATA_VALUE!=null && !"".equalsIgnoreCase(#coverDetails[0].USD_DATA_VALUE)}'> <s:property value="currencyType"/>&nbsp; </s:if>
																			<s:property value="getText('{0,number,#,##0.00}',{#coverDetails[0].USD_DATA_VALUE})"/> 
																		</b>
																	</div>
																</s:if>
																<s:else> Referral </s:else>
																<input type="hidden" name="coverRate${X_ID}" id="coverRate${X_ID}" value="${RATE}" />
																<input type="hidden" name="total${X_ID}" id="total${X_ID}" value="#coverDetails[0].DATA_VALUE" readonly="readonly"/>
																<%--<input type="hidden" name="minPremimum${X_ID}" id="minPremimum${X_ID}" value="${MIN_PREMIUM}" readonly="readonly"/>
																<input type="hidden" name="minPremimumUSD${X_ID}" id="minPremimumUSD${X_ID}" value="${MIN_PREMIUM_USD}" readonly="readonly"/>
																--%><input type="hidden" name="totalUSD<s:property value="#polType.X_ID"/>"  id="totalUSD<s:property value="#polType.X_ID"/>" value="<s:property value='#coverDetails[0].USD_DATA_VALUE'/>" />
																<input type="hidden" name="default<s:property value="#polType.X_ID"/>" value="<s:property value='#coverDetails[0].DATA_VALUE'/>" />
																<input type="hidden" name="defaultUSD<s:property value="#polType.X_ID"/>" value="<s:property value='#coverDetails[0].USD_DATA_VALUE'/>" />
																<input   style="position: absolute;visibility:hidden"  type="checkbox" checked="checked" name="optionalCovers<s:property value="#polType.X_ID"/>" id="optionalCovers<s:property value="#polType.X_ID"/>" value='<s:property value="#optCovId"/>'/>
																<s:set var="count" value="%{@java.lang.Integer@parseInt(#count)+1}"/>
															</s:if>
														</td>
													</s:if>
												</tr>
												</s:if>
												<s:else>
												<tr>
													<s:if test="#polGroup.GROUP_ID<50 || #polGroup.GROUP_ID>100">
														<td width="5%"></td>
														<td width="65%">
															<span class="preText"><b><s:property value="GROUP_DESC_ENGLISH"/></b></span>
														</td>
														<td width="30%" align="right" style="font-size: 12px;">
															<s:if test='#polGroup.GROUP_ID == "0" || (#polGroup.GROUP_ID>100 && #polGroup.GROUP_ID<150)'>
																<s:if test='"N".equals(#polType.REFERAL_STATUS)'>
																	<div class="currencyLocal" <s:if test='%{!(getText("MOTOR_CURRENCY_LOCAL")).equals(currencyType)}'>style="display:none;"</s:if>>
																		<b>
																			<s:if test='%{#coverDetails[0].DATA_VALUE!=null && !"".equalsIgnoreCase(#coverDetails[0].DATA_VALUE)}'> <s:property value="currencyType"/>&nbsp; </s:if>
																			<s:property value="getText('{0,number,#,##0.00}',{#coverDetails[0].DATA_VALUE})"/>
																		</b>
																	</div>
																	<div class="currencyForeign" <s:if test='%{!(getText("MOTOR_CURRENCY_FOREIGN")).equals(currencyType)}'>style="display:none;"</s:if>>
																		<b>
																			<s:if test='%{#coverDetails[0].USD_DATA_VALUE!=null && !"".equalsIgnoreCase(#coverDetails[0].USD_DATA_VALUE)}'> <s:property value="currencyType"/>&nbsp; </s:if>
																			<s:property value="getText('{0,number,#,##0.00}',{#coverDetails[0].USD_DATA_VALUE})"/> 
																		</b>
																	</div>
																</s:if>
																<s:else> Referral </s:else>
																<input type="hidden" name="coverRate${X_ID}" id="coverRate${X_ID}" value="${RATE}" />
																<input type="hidden" name="total${X_ID}" id="total${X_ID}" value="#coverDetails[0].DATA_VALUE" readonly="readonly"/>
																<%--<input type="hidden" name="minPremimum${X_ID}" id="minPremimum${X_ID}" value="${MIN_PREMIUM}" readonly="readonly"/>
																<input type="hidden" name="minPremimumUSD${X_ID}" id="minPremimumUSD${X_ID}" value="${MIN_PREMIUM_USD}" readonly="readonly"/>
																--%><input type="hidden" name="totalUSD<s:property value="#polType.X_ID"/>"  id="totalUSD<s:property value="#polType.X_ID"/>" value="<s:property value='#coverDetails[0].USD_DATA_VALUE'/>" />
																<input type="hidden" name="default<s:property value="#polType.X_ID"/>" value="<s:property value='#coverDetails[0].DATA_VALUE'/>" />
																<input type="hidden" name="defaultUSD<s:property value="#polType.X_ID"/>" value="<s:property value='#coverDetails[0].USD_DATA_VALUE'/>" />
																<input   style="position: absolute;visibility:hidden"  type="checkbox" checked="checked" name="optionalCovers<s:property value="#polType.X_ID"/>" id="optionalCovers<s:property value="#polType.X_ID"/>" value='<s:property value="#optCovId"/>'/>
																<s:set var="count" value="%{@java.lang.Integer@parseInt(#count)+1}"/>
															</s:if>
														</td>
													</s:if>
												</tr>
												</s:else>
											</s:if>
											<s:if test="%{#coverDetails[0].DESCRIPTION !=null && #coverDetails[0].DESCRIPTION.length() >0 && (#coverDetails[0].DESCRIPTION.indexOf('~') !=-1)}">
												<s:iterator value="%{#coverDetails[0].DESCRIPTION.split('~')}" var="covDesc" status="desc">
													<tr>
														<td width="5%">
															<img name='firstImg<s:property value="#polGroup.GROUP_ID"/><s:property value="#polType.X_ID"/><s:property value="#coverDetails[0].Y_ID"/>' id='firstImg<s:property value="#polGroup.GROUP_ID"/><s:property value="#polType.X_ID"/><s:property value="#coverDetails[0].Y_ID"/>' src='<s:url value="%{#img}"/>' style="cursor: <s:property value='#cursor'/>;display:<s:property value='%{#adddisp}'/>;" alt="<s:property value='%{#alt}'/>" height="24" width="24" onclick="return addCover(this,'<s:property value="#polType.X_ID"/>','<s:property value="#coverDetails[0].Y_ID"/>','<s:property value="%{#count}" />','<s:property value="#coverDetails[0].DATA_VALUE"/>','<s:property value="#coverDetails[0].USD_DATA_VALUE"/>','<s:property value="#polGroup.GROUP_ID"/><s:property value="#polType.X_ID"/><s:property value="#coverDetails[0].Y_ID"/>');"/>
															<img name='secondImg<s:property value="#polGroup.GROUP_ID"/><s:property value="#polType.X_ID"/><s:property value="#coverDetails[0].Y_ID"/>' id='secondImg<s:property value="#polGroup.GROUP_ID"/><s:property value="#polType.X_ID"/><s:property value="#coverDetails[0].Y_ID"/>' src="<s:url value="/images/selected_cover.png"/>"  style="cursor: pointer; display:<s:property value='%{#disp}'/>;" alt="Remove Cover" height="24" width="24" onclick="return deleteCover(this,'<s:property value="#polType.X_ID"/>','<s:property value="#coverDetails[0].Y_ID"/>','<s:property value="%{#count}" />','<s:property value="#coverDetails[0].DATA_VALUE"/>','<s:property value="#coverDetails[0].USD_DATA_VALUE"/>','<s:property value="#polGroup.GROUP_ID"/>'+'<s:property value="#polType.X_ID"/>'+'<s:property value="#coverDetails[0].Y_ID"/>');" />
														</td>
														<td width="65%">
															<div class="textSize">
																<s:property/>
															</div>
														</td>
														<td width="30%">												 		
														</td>
													</tr>
												</s:iterator>
											</s:if>
											<s:elseif test="%{#coverDetails[0].DESCRIPTION!=null && #coverDetails[0].DESCRIPTION!=''}">
												<tr>
													<td width="5%">
														<img name='firstImg<s:property value="#polGroup.GROUP_ID"/><s:property value="#polType.X_ID"/><s:property value="#coverDetails[0].Y_ID"/>' id='firstImg<s:property value="#polGroup.GROUP_ID"/><s:property value="#polType.X_ID"/><s:property value="#coverDetails[0].Y_ID"/>' src='<s:url value="%{#img}"/>' style="cursor: <s:property value='#cursor'/>;display:<s:property value='%{#adddisp}'/>;" alt="<s:property value='%{#alt}'/>" height="24" width="24" onclick="return addCover(this,'<s:property value="#polType.X_ID"/>','<s:property value="#coverDetails[0].Y_ID"/>','<s:property value="%{#count}" />','<s:property value="#coverDetails[0].DATA_VALUE"/>','<s:property value="#coverDetails[0].USD_DATA_VALUE"/>','<s:property value="#polGroup.GROUP_ID"/><s:property value="#polType.X_ID"/><s:property value="#coverDetails[0].Y_ID"/>');"/>
														<img name='secondImg<s:property value="#polGroup.GROUP_ID"/><s:property value="#polType.X_ID"/><s:property value="#coverDetails[0].Y_ID"/>' id='secondImg<s:property value="#polGroup.GROUP_ID"/><s:property value="#polType.X_ID"/><s:property value="#coverDetails[0].Y_ID"/>' src="<s:url value="/images/selected_cover.png"/>"  style="cursor: pointer; display:<s:property value='%{#disp}'/>;" alt="Remove Cover" height="24" width="24" onclick="return deleteCover(this,'<s:property value="#polType.X_ID"/>','<s:property value="#coverDetails[0].Y_ID"/>','<s:property value="%{#count}" />','<s:property value="#coverDetails[0].DATA_VALUE"/>','<s:property value="#coverDetails[0].USD_DATA_VALUE"/>','<s:property value="#polGroup.GROUP_ID"/><s:property value="#polType.X_ID"/><s:property value="#coverDetails[0].Y_ID"/>');" />
													</td>
													<td width="65%">
														<div class="textSize">													 		
															<s:property value="#coverDetails[0].DESCRIPTION"/>
														</div>
													</td>
													<td width="30%" align="right">
														<s:if test='#polGroup.GROUP_ID != "0"'>
															<input type="hidden" name="default<s:property value="#polType.X_ID"/>" value="<s:property value='#preVal'/>" />
															<input type="hidden" name="defaultUSD<s:property value="#polType.X_ID"/>" value="<s:property value='#preValUSD'/>" />
															<input   style="position: absolute;visibility:hidden"  type="checkbox" checked="checked" name="optionalCovers<s:property value="#polType.X_ID"/>" id="optionalCovers<s:property value="#polType.X_ID"/>" value='<s:property value="#optCovId"/>' />
															<s:set var="count" value="%{@java.lang.Integer@parseInt(#count)+1}"/>
															<s:if test='#polGroup.GROUP_ID != "6"'>
																<div class="currencyLocal" <s:if test='%{!(getText("MOTOR_CURRENCY_LOCAL")).equals(currencyType)}'>style="display:none;"</s:if>>
																	<s:if test='%{#coverDetails[0].DATA_VALUE!=null && !"".equalsIgnoreCase(#coverDetails[0].DATA_VALUE)}'> <s:property value="currencyType"/>&nbsp; </s:if>
																	 <s:property value="getText('{0,number,#,##0.00}',{#coverDetails[0].DATA_VALUE})"/>
																</div>
																<div class="currencyForeign" <s:if test='%{!(getText("MOTOR_CURRENCY_FOREIGN")).equals(currencyType)}'>style="display:none;"</s:if>>
																	<s:if test='%{#coverDetails[0].USD_DATA_VALUE!=null && !"".equalsIgnoreCase(#coverDetails[0].USD_DATA_VALUE)}'> <s:property value="currencyType"/>&nbsp; </s:if>
																	<s:property value="getText('{0,number,#,##0.00}',{#coverDetails[0].USD_DATA_VALUE})"/>
																</div>
															</s:if>
														</s:if>
													</td>
												</tr>
											</s:elseif>
										</s:iterator>
									</s:iterator> 
								</tbody>
							</table>							
						</div>		        						
					</div>
					<div class="panel-footer">
						<span class="pullLeft">									
							<input type="button"  class="btn btn-sm btn-warning"  name="tdCovers" value='<s:property value='%{#session.user1 != "admin" ? "Buy Policy" : "Approve Quote"}'/>' onclick="buyPolicy('<s:property value="#polType.X_ID"/>');" />
						</span>
						<br/>									
						<span class="pullRight" style="color: #ffff66; font-size: 15px;">
							<span id="totaldisplayBottom${X_ID}" style="vertical-align: middle;"></span>
						</span>
						<div class="clear"></div>			        							
					</div>
				</div>
			</div>			                
		</s:iterator>
	</s:if>
	<s:else>
		<table align="center" cellpadding="0" cellspacing="0">
			<tr>
				<td height="32" align="center" valign="middle">
					<font color="red" size="3">	
						<s:text name="motor.quotationInfo"/>
					</font>
				</td>
			</tr>
		</table>
	</s:else>
	<br class="clear"/>
</s:div>
<script>
	function getTotalPremium(frm) {
		var premium=parseFloat(frm.premium.value);
		var loadOrDiscPremium=parseFloat(frm.loadOrDiscPremium.value==""?"0":frm.loadOrDiscPremium.value);
		var sign=frm.sign.value;
		var policyFee=parseFloat(frm.policyFee.value);
		var val=0;
		if(sign=='+') {
			val=premium+loadOrDiscPremium+policyFee;
			frm.totalPremium.value=val.toFixed(2);
		} else  {
			val=premium-loadOrDiscPremium+policyFee;
			frm.totalPremium.value=val.toFixed(2);
		}
		frm.totalPremium.value = roundNumber(frm.totalPremium.value,0);
	}
	function roundNumber(number,decimal_points) {
		if(!decimal_points) return Math.round(number);
		if(number == 0) {
			return number;
		}
		var exponent = Math.pow(10,decimal_points);
		var num = Math.round((number * exponent)).toString();
		var result = num.slice(0,-1*decimal_points);
		if(num.slice(-1*decimal_points)!='00')
			result = result + "." + num.slice(-1*decimal_points);
		return result;
	}
	function SubmitAction(qno,appNo,isMail,typeId){
		document.getElementById("policyType"+typeId).checked=true;
		var ischecked = false;
		if(document.motor.policyType){
			if(document.motor.policyType.length!=null){
				var len = document.motor.policyType.length;
				for(var i=0;i<len;i++){
					if(document.motor.policyType[i].checked==true){
						var val = document.motor.policyType[i].value;
						if(document.getElementById("coverRateButton"+val)){
							if(!calculatePremium(val))
								return false;
						}
						ischecked = true;
					}
				}
			}else{
				if(document.motor.policyType.checked==true){
					var val = document.motor.policyType.value;
					if(document.getElementById("coverRateButton"+val)){
						if(!calculatePremium(val))
							return false;
					}
					ischecked = true;
				}
			}
		}else{
			alert("You Cannot Proceed");
			return false;
		}
		if(ischecked==false){
			alert("Please Choose Policy Coverage");
			return false;
		}
		document.motor.applicationNo.value=appNo;
		document.motor.quoteNo.value = qno;
		//document.motor.typeBody.value=bodyid;
		/*document.motor.referral.value=status;
		if(isMail=='true'){
			document.motor.DisplayValue.value = "mail";
		}
		document.motor.linkFrom.value="usersummary";*/
		document.motor.action='${pageContext.request.contextPath}/insertOptionCoverMotor.action';
		document.motor.submit();
		return false;
	}
	
	function deleteCover(val,typeId,coverId,coverCount,premium,premiumUSD,imgId){
		//if(document.getElementById("policyType"+typeId).checked==true){
			if((val.src).indexOf("selected_cover.png")!=-1){
				val.style.display = "none";
				var object = document.getElementsByName("optionalCovers"+typeId);
				object[coverCount].value = '';
				//if('<s:property value="referralMsg"/>'==null || '<s:property value="referralMsg"/>'==''){
				if(document.getElementById("policyTypeRef"+typeId).value=="N"){
					var val = document.getElementById("total"+typeId).value;
					var valUSD = document.getElementById("totalUSD"+typeId).value;
					val= val==""?0:val;
					valUSD= valUSD==""?0:valUSD;
					var tot = parseFloat(val) - parseFloat(premium);
					var totUSD = parseFloat(valUSD) - parseFloat(premiumUSD);
					document.getElementById("total"+typeId).value = roundNumber(tot,2);
					document.getElementById("totalUSD"+typeId).value = roundNumber(totUSD,2);	
				}else{ 
					document.getElementById("total"+typeId).value = 'Referral';
					document.getElementById("totalUSD"+typeId).value = 'Referral';
				}	
				diplayTotal(typeId);
				var object1 =  document.getElementById("firstImg"+imgId);
				object1.src = "${pageContext.request.contextPath}/images/add_cover.png";
				object1.alt = "Add Cover";
				object1.style.cursor = "pointer";
				object1.style.display = "inline";
			}
		/*}else{
		    alert("Please Choose Policy Coverage");
		}*/
		return false;
	}
	
	function addCover(val,typeId,coverId,coverCount,premium,premiumUSD,imgId){
		//if(document.getElementById("policyType"+typeId).checked==true){
			if((val.src).indexOf("add_cover.png")!=-1){
				val.src = "${pageContext.request.contextPath}/images/selected_cover.png";
				val.alt = "Added";
				val.style.cursor = "default";
				val.style.display = "none";
				var object = document.getElementsByName("optionalCovers"+typeId);
				object[coverCount].value = coverId;
				//if('<s:property value="referralMsg"/>'==null || '<s:property value="referralMsg"/>'==''){
				if(document.getElementById("policyTypeRef"+typeId).value=="N"){
					var val = document.getElementById("total"+typeId).value;
					var valUSD = document.getElementById("totalUSD"+typeId).value;
					val= val==""?0:val;
					valUSD= valUSD==""?0:valUSD;
					var tot = parseFloat(val) + parseFloat(premium);
					var totUSD = parseFloat(valUSD) + parseFloat(premiumUSD);
					document.getElementById("total"+typeId).value = roundNumber(tot,2);
					document.getElementById("totalUSD"+typeId).value = roundNumber(totUSD,2);
				}else{ 
					document.getElementById("total"+typeId).value = 'Referral';
					document.getElementById("totalUSD"+typeId).value = 'Referral';
				}
				diplayTotal(typeId);
				var object1 =  document.getElementById("secondImg"+imgId);
				object1.style.display = "inline";		
			}
		/*}else{
		    alert("Please Choose Policy Type");
		}*/
		return false;
	}
	function addTotal(){
		if(document.motor.policyType){
		if(document.motor.policyType.length){
			var policylen = document.motor.policyType.length;		
			for(var i=0;i<policylen;i++){			
				var typeId = document.motor.policyType[i].value;
				var obj = null;
				obj = document.getElementsByName("default"+typeId);
				//if('<s:property value="referralMsg"/>'==null || '<s:property value="referralMsg"/>'==''){
				if(document.getElementById("policyTypeRef"+typeId).value=="N"){
					var tot = 0, totUSD = 0;
					if(obj.length){
						var len = obj.length;
						for(var j=0;j<len;j++){
							tot = parseFloat(tot) + parseFloat(obj[j].value==""?0:obj[j].value);
						}
					}else{
						tot = parseFloat(tot) + parseFloat(obj.value==""?0:obj.value);
					}
					$( 'input[name="defaultUSD'+typeId+'"]' ).each( function( ii , e ) {
					    var v = parseFloat( $( e ).val() );
					    if ( !isNaN( v ) )
					        totUSD += v;
					} );
					document.getElementById("total"+typeId).value = roundNumber(tot,2);
					$("#totalUSD"+typeId).val(roundNumber(totUSD,2));
				}else{ 
					document.getElementById("total"+typeId).value = 'Referral';
					$("#totalUSD"+typeId).val('Referral');
				}
				diplayTotal(typeId);
				/* if(document.motor.policyType[i].checked==true)
				{
				alert(document.getElementById("plusImg"+document.motor.policyType[i].value));
				showHideDetails(document.getElementById("plusImg"+document.motor.policyType[i].value), 'OptCoverDiplay'+document.motor.policyType[i].value);
				}*/
			}		
		}else{
			var typeId = document.motor.policyType.value;
			var obj =  document.getElementsByName("default"+typeId);
			
			//if('<s:property value="referralMsg"/>'==null || '<s:property value="referralMsg"/>'==''){
			if(document.getElementById("policyTypeRef"+typeId).value=="N"){
				var tot = 0, totUSD = 0;
				if(obj.length){
					var len = obj.length;
					for(var j=0;j<len;j++){
						tot = parseFloat(tot) + parseFloat(obj[j].value==""?0:obj[j].value);
					}
				}else{
					tot = parseFloat(tot) + parseFloat(obj.value==""?0:obj.value);
				}
				$( 'input[name="defaultUSD'+typeId+'"]' ).each( function( ii , e ) {
				    var v = parseInt( $( e ).val() );
				    if ( !isNaN( v ) )
				        totUSD += v;
				} );
				document.getElementById("total"+typeId).value = roundNumber(tot,2);
				$("#totalUSD"+typeId).val(roundNumber(totUSD,2));
			}else{ 
				document.getElementById("total"+typeId).value = 'Referral';
				$("#totalUSD"+typeId).val('Referral');
			}
			diplayTotal(typeId);
		}
		}
	}
	
	function toggleDisplayTotal() {
		if('<s:property value="%{getText('MOTOR_CURRENCY_LOCAL')}"/>'=='<s:property value="currencyType"/>') {
			$( '[name="policyType"]' ).each( function( i , e ) {
				var typeId = parseInt( $( e ).val() );
				diplayTotal(typeId);
			} );
			$( '.currencyLocal' ).attr('style','');
			$( '.currencyForeign' ).attr('style','display:none;');
		} else if('<s:property value="%{getText('MOTOR_CURRENCY_FOREIGN')}"/>'== '<s:property value="currencyType"/>' ) {
			$( '[name="policyType"]' ).each( function( i , e ) {
				var typeId = parseInt( $( e ).val() );
				diplayTotal(typeId);
			} );
			$( '.currencyLocal' ).attr('style','display:none;');
			$( '.currencyForeign' ).attr('style','');
		}
	}

	function diplayTotal(typeId) {
		if('<s:property value="referralMsgs"/>'!='') {
			document.getElementById("totaldisplay"+typeId).innerHTML = 'Referral';
			document.getElementById("totaldisplayBottom"+typeId).innerHTML = 'Referral';
		} else {
			if('<s:property value="%{getText('MOTOR_CURRENCY_LOCAL')}"/>'=='<s:property value="currencyType"/>') {
				var total1 = numeral(parseFloat($("#total"+typeId).val())).format('0,0.00');
				/*var minPremimum = numeral(parseFloat($("#minPremimum"+typeId).val()));
				if( minPremimum > total1 ){
				minPremimum = minPremimum.format('0,0.00')
				document.getElementById("totaldisplay"+typeId).innerHTML = '<s:property value="currencyType"/> ' + minPremimum;
				document.getElementById("totaldisplayBottom"+typeId).innerHTML = '<s:property value="currencyType"/> ' + minPremimum;
				}else{}*/
					document.getElementById("totaldisplay"+typeId).innerHTML = '<s:property value="currencyType"/> ' + total1;
					document.getElementById("totaldisplayBottom"+typeId).innerHTML = '<s:property value="currencyType"/> ' + total1;
			} else if('<s:property value="%{getText('MOTOR_CURRENCY_FOREIGN')}"/>'== '<s:property value="currencyType"/>' ) {
				var totalUSD = numeral(parseFloat($("#totalUSD"+typeId).val())).format('0,0.00');
				/*var minPremimumUSD = numeral(parseFloat($("#minPremimumUSD"+typeId).val())).format('0,0.00');
				if( minPremimumUSD > totalUSD ){
				document.getElementById("totaldisplay"+typeId).innerHTML = '<s:property value="currencyType"/> ' + minPremimumUSD;
				document.getElementById("totaldisplayBottom"+typeId).innerHTML = '<s:property value="currencyType"/> ' + minPremimumUSD;
				}else{}*/
				document.getElementById("totaldisplay"+typeId).innerHTML = '<s:property value="currencyType"/> ' + totalUSD;
				document.getElementById("totaldisplayBottom"+typeId).innerHTML = '<s:property value="currencyType"/> ' + totalUSD;
			}
		}
	}
	try {
		addTotal();
	} catch(err) {
		console.error(err);
	}

	function buyPolicy(policyType) {
		SubmitAction('<s:property value="quoteNo"/>','<s:property value="applicationNo"/>','false',policyType);
	}
</script>