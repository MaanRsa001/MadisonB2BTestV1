<%@ page isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html <s:if test="locale.language == 'ar'">dir="rtl"</s:if> >
<head>
</head>
<body>
<s:form name="coverageDtl" id="coverageDtl" theme="simple" action="">
	<h4>
		<s:if test="reqFrom=='extendCover'">Extended Coverage</s:if>
		<s:elseif test='reqFrom=="excess"'>Excess</s:elseif>
		<s:elseif test='reqFrom=="warranties"'>Warranties</s:elseif>
		<s:elseif test='reqFrom=="excludeRisk"'>Excluded Risk</s:elseif>&nbsp;&nbsp;-&nbsp;&nbsp;<s:property value="schemeName"/>
	</h4>
	<hr/>
	<div class="row rowFlex OnlyPremiumTable p-3" align="center">
		<div class="col-md-12 col-12">
			<font color="red"><s:actionerror cssStyle="list-style: none;"/></font>
			<font color="green"><s:actionmessage cssStyle="list-style:none;"/> </font>
		</div>
		<s:if test="reqFrom=='extendCover'">
			<div class="col-md-12 col-12">
				<table class="table" id="extendedCoverTableId" width="100%">
			       <thead>
				        <tr align="center">
				         	<th style="background-color:#261e6a;color:white;" width="50%">Extended Coverage</th>
				         	<th style="background-color:#261e6a;color:white;" width="25%">Rate</th>
				         	<th style="background-color:#261e6a;color:white;" width="25%">Percentage</th>
				        </tr>
			       </thead>
			        <tbody>
						<s:set var="extendedCoverListVar" value="extendedCoverList"/>
						<s:if test='#extendedCoverListVar != null && #extendedCoverListVar.size()>0'>
				        	<s:iterator var="list" value="#extendedCoverListVar" status="stat">
						       	<tr>
						       		<td align="left">
						       			<input type="checkbox" name="extendedCover" id='<s:property value="#list.OPCOVEREXT_ID"/>' value='<s:property value="#list.OPCOVEREXT_ID"/>'/>&nbsp;
						       			<label for='<s:property value="#list.OPCOVEREXT_ID"/>' class=""><s:property value="#list.OPCOVEREXT_DESC"/></label>
						       		</td>
						       		<td align="center"><s:property value="#list.RATE"/></td>
						       		<td align="center"><s:property value="#list.PERCENTAGE"/></td>
						       	</tr>
					       	</s:iterator>
						</s:if>
						<s:else>
							<tr>
								<td align="center" colspan="3">No Extended Covers Available</td>
							</tr>
						</s:else>
				    </tbody>
			    </table>
			</div>
		</s:if>
		<s:elseif test='reqFrom=="excess"'>
			<s:set var="excessListVar" value="excessList"/>
			<div class="col-md-12 col-12" id="excessAryeaDivId" style="display:<s:if test='#excessListVar != null && #excessListVar.size()>0'>block;</s:if><s:else>none;</s:else>">
				<table class="table" id="excessArTableId">
					<thead>
						<tr align="center">
							<th style="background-color:#261e6a;color:white;">Excess %</th>
							<th style="background-color:#261e6a;color:white;">Excess Amount</th>
							<th style="background-color:#261e6a;color:white;">Excess Description</th>
							<s:if test='"RSAIssuer".equalsIgnoreCase(#session.usertype) || "admin".equalsIgnoreCase(#session.usertype)'>
								<th style="background-color:#261e6a;color:white;">Delete</th>
							</s:if>
						</tr>
					</thead>
				    <tbody>
						<s:if test='#excessListVar != null && #excessListVar.size()>0'>
					    	<s:iterator var="list" value="#excessListVar" status="stat">
					    		<s:if test='"RSAIssuer".equalsIgnoreCase(#session.usertype) || "admin".equalsIgnoreCase(#session.usertype)'>
					    			<tr align="center">
										<td>
											<s:textfield name="excessPercentAr[%{#stat.index}]" id="excessPercentAr_%{#stat.index}" value="%{#list.EXCESS_PERCENT}" onkeypress="return isNumberKey(event);" maxlength="6" cssClass="form-control"/>
											<s:hidden name="excessIdAr[%{#stat.index}]" id="excessIdAr_%{#stat.index}" value="%{#list.EXCESS_ID}"/>
										</td>
										<td>
											<s:textfield name="excessAmountAr[%{#stat.index}]" id="excessAmountAr_%{#stat.index}" value="%{#list.EXCESS_AMOUNT}" onkeypress="return isNumberKey(event);" maxlength="10" cssClass="form-control"/>
										</td>
										<td>
											<s:textarea  name="excessDescAr[%{#stat.index}]" id="excessDescAr_%{#stat.index}" value="%{#list.EXCESS_DESCRIPTION}" cssClass="form-control"/>
										</td>
										<td>
											<s:checkbox name="excessDeleteAr[%{#stat.index}]" id="excessDeleteAr_%{#stat.index}" onclick="disableExcessAr('%{#stat.index}');"/>
										</td>
									</tr>
					    		</s:if>
					    		<s:else>
									<tr align="center">
										<td>
											<s:property value="#list.EXCESS_PERCENT"/>
										</td>
										<td>
											<s:property value="#list.EXCESS_AMOUNT"/>
										</td>
										<td>
											<s:property value="#list.EXCESS_DESCRIPTION"/>
										</td>
									</tr>
								</s:else>
							</s:iterator>
						</s:if>
				    </tbody>
			    </table>
		    </div>
		    <div class="col-md-12 col-12" id="excessArneaDivId" align="center"style="display:<s:if test='#excessListVar != null && #excessListVar.size()>0'>none;</s:if><s:else>block;</s:else>">
		    	<h5>No Excess Available</h5>
		    </div>
		    <br/>
		    <s:if test='"RSAIssuer".equalsIgnoreCase(#session.usertype) || "admin".equalsIgnoreCase(#session.usertype)'>
			    <div class="col-md-12 col-12" align="right">
			    	<div class="col-md-9">
			    	</div>
			    	<div class="col-md-3">
			    		<a style="cursor: pointer;" class="btn btn-primary btn-sm" onclick="addNewExcessAr();">Add New</a>
			    	</div>
			    </div>
			    <br/>
		    </s:if>
		</s:elseif>
		<s:elseif test='reqFrom=="warranties"'>
			<s:set var="warrantiesListVar" value="warrantiesList"/>
			<div class="col-md-12 col-12" id="warrantiesAryeaDivId" style="display:<s:if test='#warrantiesListVar != null && #warrantiesListVar.size()>0'>block;</s:if><s:else>none;</s:else>">
				<table class="table" id="warrantiesArTableId">
					<thead>
						<tr align="center">
							<th style="background-color:#261e6a;color:white;"><s:text name="SNo"/></th>
							<th style="background-color:#261e6a;color:white;"><s:text name="Warranties"/></th>
							<s:if test='"RSAIssuer".equalsIgnoreCase(#session.usertype) || "admin".equalsIgnoreCase(#session.usertype)'>
								<th style="background-color:#261e6a;color:white;">Delete</th>
							</s:if>
						</tr>
					</thead>
					<tbody>
						<s:if test='#warrantiesListVar != null && #warrantiesListVar.size()>0'>
							<s:iterator var="list" value="#warrantiesListVar" status="stat">
								<s:if test='"RSAIssuer".equalsIgnoreCase(#session.usertype) || "admin".equalsIgnoreCase(#session.usertype)'>
									<tr align="center">
										<td>
											<s:property value="#stat.count"/>
											<s:hidden name="warrantiesIdAr[%{#stat.index}]" id="warrantiesIdAr_%{#stat.index}" value="%{#list.WARRENTY_ID}"/>
										</td>
										<td>
											<s:textarea  name="warrantiesDescAr[%{#stat.index}]" id="warrantiesDescAr_%{#stat.index}" value="%{#list.WARRENTY_DESCRIPTION}" cssClass="form-control"/>
										</td>
										<td>
											<s:checkbox name="warrantiesDeleteAr[%{#stat.index}]" id="warrantiesDeleteAr_%{#stat.index}" onclick="disableWarrantiesAr('%{#stat.index}');"/>
										</td>
									</tr>
								</s:if>
								<s:else>
									<tr align="center">
										<td>
											<s:property value="#stat.count"/>
										</td>
										<td>
											<s:property value="#list.WARRENTY_DESCRIPTION"/>
										</td>
									</tr>
								</s:else>
							</s:iterator>
						</s:if>
					</tbody>
				</table>
			</div>
		    <div class="col-md-12 col-12" id="warrantiesArneaDivId" align="center"style="display:<s:if test='#warrantiesListVar != null && #warrantiesListVar.size()>0'>none;</s:if><s:else>block;</s:else>">
		    	<h5>No Warranties Available</h5>
		    </div>
		    <br/>
		    <s:if test='"RSAIssuer".equalsIgnoreCase(#session.usertype) || "admin".equalsIgnoreCase(#session.usertype)'>
			    <div class="col-md-12 col-12" align="right">
			    	<div class="col-md-9">
			    	</div>
			    	<div class="col-md-3">
			    		<a style="cursor: pointer;" class="btn btn-primary btn-sm" onclick="addNewWarrantiesAr();">Add New</a>
			    	</div>
			    </div>
			    <br/>
		    </s:if>
		</s:elseif>
		<s:elseif test='reqFrom=="excludeRisk"'>
			<s:set var="exclusionsListVar" value="exclusionsList"/>
			<div class="col-md-12 col-12" id="exclusionsAryeaDivId" style="display:<s:if test='#exclusionsListVar != null && #exclusionsListVar.size()>0'>block;</s:if><s:else>none;</s:else>">
				<table class="table" id="exclusionsArTableId">
					<thead>
						<tr align="center">
							<th style="background-color:#261e6a;color:white;"><s:text name="SNo"/></th>
							<th style="background-color:#261e6a;color:white;"><s:text name="Excluded Risk"/></th>
							<s:if test='"RSAIssuer".equalsIgnoreCase(#session.usertype) || "admin".equalsIgnoreCase(#session.usertype)'>
								<th style="background-color:#261e6a;color:white;">Delete</th>
							</s:if>
						</tr>
					</thead>
					<tbody>
						<s:if test='#exclusionsListVar != null && #exclusionsListVar.size()>0'>
							<s:iterator var="list" value="#exclusionsListVar" status="stat">
								<s:if test='"RSAIssuer".equalsIgnoreCase(#session.usertype) || "admin".equalsIgnoreCase(#session.usertype)'>
									<tr align="center">
										<td>
											<s:property value="#stat.count"/>
											<s:hidden name="exclusionsIdAr[%{#stat.index}]" id="exclusionsIdAr_%{#stat.index}" value="%{#list.EXCLUSION_ID}"/>
										</td>
										<td>
											<s:textarea  name="exclusionsDescAr[%{#stat.index}]" id="exclusionsDescAr_%{#stat.index}" value="%{#list.EXCLUSION_DESCRIPTION}" cssClass="form-control"/>
										</td>
										<td>
											<s:checkbox name="exclusionsDeleteAr[%{#stat.index}]" id="exclusionsDeleteAr_%{#stat.index}" onclick="disableExclusionsAr('%{#stat.index}');"/>
										</td>
									</tr>
								</s:if>
								<s:else>
									<tr align="center">
										<td>
											<s:property value="#stat.count"/>
										</td>
										<td>
											<s:property value="#list.EXCLUSION_DESCRIPTION"/>
										</td>
									</tr>
								</s:else>
							</s:iterator>
						</s:if>
					</tbody>
				</table>
			</div>
		    <div class="col-md-12 col-12" id="exclusionsArneaDivId" align="center"style="display:<s:if test='#exclusionsListVar != null && #exclusionsListVar.size()>0'>none;</s:if><s:else>block;</s:else>">
		    	<h5>No Exclusions Available</h5>
		    </div>
		    <br/>
		    <s:if test='"RSAIssuer".equalsIgnoreCase(#session.usertype) || "admin".equalsIgnoreCase(#session.usertype)'>
			    <div class="col-md-12 col-12" align="right">
			    	<div class="col-md-9">
			    	</div>
			    	<div class="col-md-3">
			    		<a style="cursor: pointer;" class="btn btn-primary btn-sm" onclick="addNewExclusionsAr();">Add New</a>
			    	</div>
			    </div>
			    <br/>
		    </s:if>
		</s:elseif>
		<div class="col-md-12 col-12">
			<div class="row">
				<div class="col-md-4">
				</div>
				<div class="col-md-2">
					<a style="cursor:pointer" type="button" class="btn btn-sm btn-danger" id="CloseButton" data-dismiss="modal" onclick="return false;">Close</a>
				</div>
				<s:if test='"extendCover".equalsIgnoreCase(reqFrom) || ("excess".equalsIgnoreCase(reqFrom) && ("RSAIssuer".equalsIgnoreCase(#session.usertype) || "admin".equalsIgnoreCase(#session.usertype))) || ("warranties".equalsIgnoreCase(reqFrom) && ("RSAIssuer".equalsIgnoreCase(#session.usertype) || "admin".equalsIgnoreCase(#session.usertype))) || ("excludeRisk".equalsIgnoreCase(reqFrom) && ("RSAIssuer".equalsIgnoreCase(#session.usertype) || "admin".equalsIgnoreCase(#session.usertype)))'>
					<div class="col-md-2">
						<button type="button" class="btn btn-sm btn-success" id="coverageDtlSaveBtnId" onclick='saveCoveragesAr();'>Save</button>
					</div>
					<div class="col-md-4">
					</div>
				</s:if>
		    </div>
		</div>
		<br/>
	</div>
	<s:hidden name="coverSelected" id="coverSelected"/>
	<s:hidden name="quoteNo"/>
	<s:hidden name="applicationNo"/>
	<s:hidden name="schemeId"/>
	<s:hidden name="reqFrom"/>
</s:form>
<script type="text/javascript">
	$('body').append('width = ' + $('.cell').width());
	
	/*$(function(){
	    $( "input[type=checkbox]" ).on( "click", function(){
	        if($(this).is(':checked'))
	            $(this).parent().css('background-color', '#5cb85c');
	        else
	            $(this).parent().css('background-color', '#ccc');
	    });
	});*/
	
	$( document ).ready(function() {
		var values=$("#coverSelected").val().split(",");
		$("#extendedCoverTableId").find('[value=' + values.join('], [value=') + ']').prop("checked", true);
		//$("#list").find('[value=' + values.join('], [value=') + ']').parent().addClass("checkbgcolor"); 
		
	});
	
</script>
</body>
</html>