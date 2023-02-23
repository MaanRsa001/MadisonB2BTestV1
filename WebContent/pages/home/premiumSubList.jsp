<%@ page isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html <s:if test="locale.language == 'ar'">dir="rtl"</s:if> >
<head>
<script type="text/javascript">
</script>
</head>
<body>
<s:if test='%{!"admin".equals(#session.usertype)}'>
	<s:set var="disable1" value="%{menuType=='RA'}"/>
</s:if>
<s:set var="format" value="%{'number.format'}"/>

<s:form name="PremiumSubForm" id="PremiumSubForm" theme="simple" action="saveDetailHome">
	<s:if test='%{!"RA".equals(menuType)}'>
		<div class="col-md-12 col-12">
			<font color="red"><s:property value="subDetailReferralRemarks"/></font>
		</div>
	</s:if>
	<s:if test='subCoverId==null || "".equals(subCoverId) || "0".equals(subCoverId)'>
		<s:iterator value="premiumList" var="prem" status="stat">
			<s:if test='%{#prem.COVERAGES_id==coverId}'>
				<div class="col-md-12 col-12">
					<table class="table">
				       <thead>
					        <tr>
					         	<th style="background-color:#261e6a;color:white;"><s:text name="label.sum.insured"/></th>
					         	<th style="background-color:#261e6a;color:white;"><s:text name="label.premium"/></th>
					         	<th style="background-color:#261e6a;color:white;">Total</th>
					        </tr>
				       </thead>
				       <tbody>
				       		<tr>
				       			<td><s:property value="getText('{0,number,#,##0.00}',{@java.lang.Double@valueOf(#prem.SUM_INSURED)})"/>&nbsp;<s:property value="#session.BrokerDetails.CurrencyAbb"/></td>
				       			<td>
				       				<s:property value="getText('{0,number,#,##0.00}',{@java.lang.Double@valueOf(#prem.PREMIUM_AMOUNT)})"/>
									&nbsp;<s:property value="#session.BrokerDetails.CurrencyAbb"/>
								</td>
				       			<td><span id="subtotalSpan"></span> &nbsp;<s:property value="#session.BrokerDetails.CurrencyAbb"/></td>
								<s:hidden name="coverSumInsured" id="coverSumInsured" value="%{SUM_INSURED}"/>
				       		</tr>
				       </tbody>
				    </table>
				</div>
			</s:if>
		</s:iterator>
	</s:if>
	<s:elseif test='subCoverId!=null || !"0".equals(subCoverId)'>
		<s:iterator value="subPremium" var="subprem" status="stat">
			<s:if test='%{#subprem.COVERAGES_id==coverId && #subprem.COVERAGES_SUB_ID==subCoverId}'>
				<div class="col-md-12 col-12">
					<table class="table">
				       <thead>
					        <tr>
					         	<th style="background-color:#261e6a;color:white;"><s:text name="label.sum.insured"/></th>
					         	<th style="background-color:#261e6a;color:white;"><s:text name="label.premium"/></th>
					         	<th style="background-color:#261e6a;color:white;">Total</th>
					        </tr>
				       </thead>
				       <tbody>
				       		<tr>
				       			<td><s:property value="getText('{0,number,#,##0.00}',{@java.lang.Double@valueOf(#subprem.COVERAGES_COVERED_EMPLOYEES)})"/><s:property value="#session.BrokerDetails.CurrencyAbb"/></td>
				       			<td>
				       				<s:property value="getText('{0,number,#,##0.00}',{@java.lang.Double@valueOf(#subprem.PREMIUM_AMOUNT)})"/><s:property value="#session.BrokerDetails.CurrencyAbb"/>
								</td>
				       			<td><span id="subtotalSpan"></span> &nbsp;<s:property value="#session.BrokerDetails.CurrencyAbb"/></td>
								<s:hidden name="coverSumInsured" id="coverSumInsured" value="%{COVERAGES_COVERED_EMPLOYEES}"/>
				       		</tr>
				       </tbody>
				    </table>
				</div>
			</s:if>
		</s:iterator>
	</s:elseif>
	<div class="col-md-12 col-12">
	    <s:actionerror style="color:red;"/>
		<s:actionmessage style="color:green"/> 
	</div>
	<div class="col-md-12 col-12">
		<s:set var="iterValue"/>
		<s:set var="iterBegin"/>
		<s:set var="iterEnd"/>
		<s:if test="%{coverageList==null || coverageList.size==0}">
			<s:if test='"M".equals(addRowYN)'>
				<s:set var="iterValue"/>
				<s:set var="iterBegin" value="0"/>
				<s:set var="iterEnd" value="2"/>
			</s:if>
			<s:else>
			</s:else>
		</s:if>
		<table class="table" id="dynaTable${coverId}${subCoverId}">
			<thead>
				<tr>
					<th style="background-color:#261e6a;color:white;">S.No.</th>
					<s:iterator value="subList" var="build">
					<s:if test='"3".equalsIgnoreCase(schemeId) && "Address".equalsIgnoreCase(#build.DISPLAY_NAME) && ("57".equals(coverId) || "58".equals(coverId))'>
						<th style="background-color:#261e6a;color:white;">Building Address</th>
						<th style="background-color:#261e6a;color:white;">Insured Address<s:if test='"Y".equals(Mandatory)'><font color="red">*</font></s:if></th>
					</s:if>
					<s:else>					
						<th style="background-color:#261e6a;color:white;"><s:property value="DISPLAY_NAME"/><s:if test='"Y".equals(Mandatory)'><font color="red">*</font></s:if></th>
					</s:else>						
					</s:iterator>
					<th style="background-color:#261e6a;color:white;"><s:text name="home.delete"/></th>
				</tr>
			</thead>
			<tbody>					
				<s:set var="iter" value="0"/>
				<s:set var="sbuildingAddressList" value="buildingAddressList"/>
				<s:iterator value="iteratorCoverageList" var="cover" status="cov">
					<tr>
						<td style="text-align: center;"><s:property value="%{#cov.index+1}"/></td>
						<s:iterator value="subList" var="build" status="stat">
							<s:if test='"3".equalsIgnoreCase(schemeId) && "Address".equalsIgnoreCase(#build.DISPLAY_NAME) && ("57".equals(coverId) || "58".equals(coverId))'>
								<td style="text-align: center;">
									<s:select id="quick%{#stat.index}[%{#iter}]" list="#sbuildingAddressList" listKey="ADDRESS" listValue="ADDRESS" headerKey="" headerValue="--QUICK SELECT--" onchange="setBuildingAddress(this.value,'%{#stat.index}[%{#iter}]')" cssClass="form-control"/>
								</td>
							</s:if>
							<td style="text-align: center;">
								<s:if test='"textbox".equals(#build.DISPLAY_TYPE)'>
									<s:textfield name="colDyn%{#stat.index}[%{#iter}]" id="colDyn%{#stat.index}[%{#iter}]" cssClass='form-control %{"Y".equals(#build.TOTAL_SUMINSURED_YN)?"runningTotal":""}' cssStyle="width:85;"
									value='%{(!"Y".equals(#build.TOTAL_SUMINSURED_YN) || #cover.get(#build.DEST_COLUMN)==null || "".equals(#cover.get(#build.DEST_COLUMN)))?#cover.get(#build.DEST_COLUMN):getText(#format,{@java.lang.Double@valueOf(#cover.get(#build.DEST_COLUMN))})}'
									onkeyup='%{"Y".equals(#build.TOTAL_SUMINSURED_YN)?"sumTotalValues();checkDecimals13(this);javascript:this.value=Comma(this.value);":""}' maxLength="2000" disabled="disable1"/>
								</s:if>
								<s:elseif test='"Radio".equals(#build.DISPLAY_TYPE)'>
									<s:set var="dynamic" value="dynamicList"/>
									<s:radio name="colDyn%{#stat.index}[%{#iter}]" id="colDyn%{#stat.index}[%{#iter}]" list="%{dynamicList.get(#build.DEST_COLUMN)}" listKey="ITEM_CODE" listValue="ITEM_VALUE" value='%{(#cover==null || #cover.get(#build.DEST_COLUMN)==null||"".equals(#cover.get(#build.DEST_COLUMN)))?"1":#cover.get(#build.DEST_COLUMN) }' disabled="disable1"/>
								</s:elseif>
								<s:elseif test='"dropdown".equals(#build.DISPLAY_TYPE)'>
									<s:set var="dynamic" value="dynamicList"/>
									<s:select name="colDyn%{#stat.index}[%{#iter}]" listKey="ITEM_CODE" listValue="ITEM_VALUE" id="colDyn%{#stat.index}[%{#iter}]" list="%{dynamicList.get(#build.DEST_COLUMN)}" value='%{#cover.get(#build.DEST_COLUMN)}' headerKey="" headerValue="-Select-" cssClass="form-control" disabled="disable1"/>
								</s:elseif>
							</td>
						</s:iterator>
						<td style="text-align: center;">
							<s:checkbox name="deleteRow[%{#cov.count-1}]" id="chkBox%{#cov.count-1}" onclick="deleteRow(%{#cov.count-1});" value="false" disabled="disable1"/>		
						</td>
					</tr>
					<s:set var="iter" value="%{@java.lang.Integer@parseInt(#iter)+1}"/>
				</s:iterator>
		    </tbody>
		</table>
		<s:div id="dynamic%{coverId}%{coverageList==null?'0':coverageList.size()}part"></s:div>
	</div>
	<s:if test='"M".equals(addRowYN)'>
		<div align="right" style="margin-top: 5px;">
			<s:submit type="button" name="addmore" id="addmore" cssClass="btn btn-sm btn-primary" value="Add More" onclick="return addNewRow(%{coverId}%{subCoverId});return false;" disabled="disable1"/>&nbsp;&nbsp;
		</div>
	</s:if>
	<div class="tablerow" style="margin-top: 5px;">
		<div class="boxcontent" align="center">
			<s:submit type="button" cssClass="btn btn-sm btn-danger" value="Close" id="CloseButton" data-dismiss="modal" onclick="return false;"/>
			<s:submit type="button" name="saveDetailAjaxButton" id="saveDetailAjaxButton" cssClass="btn btn-sm btn-primary" value="Save" onclick="return saveDetailAjax();" disabled="disable1"/>
			<br/>
			(Without clicking <b>Save</b> the data will be lost)
		</div>
	</div>
	<s:hidden name="quoteNo"/>
	<s:hidden name="applicationNo"/>
 	<s:hidden name="coverId"/>
 	<s:hidden name="subCoverId"/>
 	<s:hidden name="selectTab"/>
 	<s:hidden name="from"/>
 	<s:hidden name="menuType"/>
 	<s:hidden name="tabInfo" id="tabInfo" />
 	<s:hidden name="ajScheme"/>
 	<s:hidden name="dropDownScheme"/>
	<s:hidden name="dropDownLocation"/>
</s:form>
<script>
	function stopRKey(evt) { 
	  var evt = (evt) ? evt : ((event) ? event : null); 
	  var node = (evt.target) ? evt.target : ((evt.srcElement) ? evt.srcElement : null); 
	  if ((evt.keyCode == 13) && (node.type=="text"))  {return false;} 
	}
	document.onkeypress = stopRKey;	
</script>
<script type="text/javascript">
	function setBuildingAddress(val, rowId) {
		document.getElementById("quick"+rowId).value = "";
		document.getElementById("colDyn"+rowId).value = val;
	}
	function addNewRow(val) {
	var dynaTable="dynaTable"+val;
	
	var table = document.getElementById(dynaTable);
	var rowCount = table.rows.length;
	var row = table.insertRow(rowCount);
		row.id = "new_"+rowCount;
	var onkeyupVal = "sumTotalValues();checkDecimals13(this);javascript:this.value=Comma(this.value);";
	var statCount = 0;
	var cell = row.insertCell(statCount++);            
		cell.innerHTML = parseInt(rowCount);
		cell.setAttribute("style", "text-align: center;");
		<s:iterator value="subList" var="build" status="stat">
			
			<s:if test='"3".equalsIgnoreCase(schemeId) && "Address".equalsIgnoreCase(#build.DISPLAY_NAME) && ("57".equals(coverId) || "58".equals(coverId))'>
				addBuildingAddress(row, rowCount, '<s:property value="#stat.index"/>', statCount++);
			</s:if>
			var cell = row.insertCell(statCount++);
			cell.setAttribute("style", "text-align: center;");
			
			<s:if test='"textbox".equals(#build.DISPLAY_TYPE)'>
				var element = document.createElement("input");
				if('Y' == '<s:property value="#build.TOTAL_SUMINSURED_YN"/>') {
					element.className = "form-control runningTotal";
					element.setAttribute("onkeyup",onkeyupVal);
				} else {
					element.className = "form-control";
				}
				element.name ="colDyn"+"<s:property value='#stat.index'/>"+"["+(rowCount-1)+"]";
				element.id = "colDyn"+"<s:property value='#stat.index'/>"+"["+(rowCount-1)+"]";
				element.type = "text";  
				cell.appendChild(element);
			</s:if>
			<s:elseif test='"dropdown".equals(#build.DISPLAY_TYPE)'>
				var element = document.createElement("select");
				//element.style.width = "154px";
				element.className = "form-control";
				element.name ="colDyn"+"<s:property value='#stat.index'/>"+"["+(rowCount-1)+"]";
				element.id = "colDyn"+"<s:property value='#stat.index'/>"+"["+(rowCount-1)+"]";
				var objOption = document.createElement("option");
	            objOption.text = '-Select-';
	            objOption.value = '';
	            if(document.all && !window.opera){
	            	element.add(objOption);
	            }else{
	            	element.add(objOption, null);
	            }
				<s:iterator value='%{dynamicList.get(#build.DEST_COLUMN)}' var='build1' status='stat1'>
					var objOption = document.createElement("option");
					objOption.text = "<s:property value='ITEM_VALUE' />";
					objOption.value = "<s:property value='ITEM_CODE' />";
					if(document.all && !window.opera){
						element.add(objOption);
					}else{
						element.add(objOption, null);
					}
				</s:iterator>
	            cell.appendChild(element);
			</s:elseif>
		</s:iterator>
		
		var cellsize=<s:property value='subList.size'/>;
		var cell = row.insertCell(statCount++);
        cell.setAttribute("style", "text-align: center;");
 		element = document.createElement("input");
           element.type = "checkbox";
           element.name = "deleteRow["+(rowCount-1)+"]";
           element.id = "chkBox"+(rowCount-1)+""; 
           element.style.width = "30px";
           element.onclick =  function () {deleteRow(this.form)};
           cell.appendChild(element);
           return false;
	}

	function addBuildingAddress(row, rowCount, indexVal, statCount) {
		var cell = row.insertCell(statCount);
		cell.setAttribute("style", "text-align: center;");
		
		var element = document.createElement("select");
		element.className = "form-control";
		rowId = indexVal+"["+(rowCount-1)+"]";
		element.id = "quick"+rowId;
		element.setAttribute("onchange", "setBuildingAddress(this.value,'" + rowId + "')");
		var objOption = document.createElement("option");
           objOption.text = '--QUICK SELECT--';
           objOption.value = '';
           if(document.all && !window.opera){
           	element.add(objOption);
           }else{
           	element.add(objOption, null);
           }
		<s:iterator value='buildingAddressList' var='build1' status='stat1'>
			var objOption = document.createElement("option");
			objOption.text = "<s:property value='#build1.ADDRESS' />";
			objOption.value = "<s:property value='#build1.ADDRESS' />";
			if(document.all && !window.opera){
				element.add(objOption);
			}else{
				element.add(objOption, null);
			}
		</s:iterator>
           cell.appendChild(element);
	}
	
	function deleteRow(sNo){
		/*var decision = confirm('<s:text name="row.delete.wish.continue"/>');
		if (decision==true){
        	document.form1.action="${pageContext.request.contextPath}/getDeleteHome.action";
		    document.form1.submit();
		    return false;
	    }else {
	    	document.getElementById("chkBox"+sNo).checked=false;
	    }*/
		var decision = confirm('<s:text name="row.delete.wish.continue"/>');
		if (decision==true) {
		    postFormRequest('${pageContext.request.contextPath}/getDeleteAjaxHome.action', 'premiumSubUploadAjax', 'PremiumSubForm');
	    } else {
	    	document.getElementById("chkBox"+sNo).checked=false;
	    }
	}
	/*function fnsubmit(action) {
		document.form1.action = "${pageContext.request.contextPath}/" + action;
		document.form1.submit();
	}*/
	function saveDetailAjax() {
		postFormRequest('${pageContext.request.contextPath}/saveDetailAjaxHome.action', 'premiumSubUploadAjax', 'PremiumSubForm');
		return false;
	}
	function sumTotalValues() {
		var sum = 0;
	    $('.runningTotal').each(function() {
	    	var temp = Number( ($(this).val()).replace(/,/g, '') );
		    if( !isNaN( temp ) ) {
	        	sum += temp ;
		    }
	    });
	    if( sum>Number($('#coverSumInsured').val()) ) {
	    	$('#subtotalSpan').html('<font color="red">' + numeral(sum).format('0,0.00') + '</font>');
	    } else {
	    	$('#subtotalSpan').html(numeral(sum).format('0,0.00'));
	    }
	}
	try {
		sumTotalValues();
	} catch(err) {
		console.error(err);
	}
</script>
</body>
</html>