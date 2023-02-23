<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:if test="#request.ELEMENT_NAME=='package'">
	<s:select name="packageCode" list="packageList" headerKey="" headerValue="---Select---" listKey="CODE" listValue="CODEDESC" cssClass="inputBoxS " data-content="Package Code" theme="simple" id="packageCode" />
</s:if>
<s:elseif  test="#request.ELEMENT_NAME=='cover'">
   	<s:if test="%{productId==openCover}" >		   		 
    		<s:select name="cover" id="cover" list="coverList" headerKey="" headerValue="---Select---" listKey="CODE" listValue="CODEDESC" cssClass="inputBoxS " data-content="Cover"  onchange="getList('?modeOfTransport=%{modeOfTransport}&cover='+this.value,'conveyanceList');" theme="simple"/>
    		  
    	<%--</s:else>--%>
    </s:if>	
    <s:else>
    	<s:select name="cover" id="cover" list="coverList" headerKey="" headerValue="---Select---" listKey="CODE" listValue="CODEDESC" cssClass="inputBoxS " data-content="Cover" theme="simple"/>                                
   	</s:else>
</s:elseif>
<s:elseif  test="#request.ELEMENT_NAME=='conveyance'">
	<%--
	<s:if test="%{productId==openCover}" >
		<s:if test='conveyanceList!=null && conveyanceList.size==1'>
			<s:select name="conveyance" list="conveyanceList" listKey="CODE" listValue="CODEDESC" cssClass="inputBoxS" theme="simple" id="conveyanceList" />
		</s:if>
		<s:else>
			<s:select name="conveyance" list="conveyanceList" headerKey="" headerValue="---Select---" listKey="CODE" listValue="CODEDESC" cssClass="inputBoxS" theme="simple" id="conveyanceList" />
		</s:else>
	</s:if>
	<s:else>
		<s:select name="conveyance" list="conveyanceList" listKey="CODE" listValue="CODEDESC" cssClass="inputBoxS" theme="simple" id="conveyanceList" />
	</s:else>
	--%>
	<s:select name="conveyance" list="conveyanceList" headerKey="" headerValue="---Select---" listKey="CODE" listValue="CODEDESC" cssClass="inputBoxS " data-content="Conveyance" theme="simple" id="conveyanceList" />
</s:elseif>
<s:elseif  test="#request.ELEMENT_NAME=='agent'">
	<s:select name="settlingAgent" list="agentList" headerKey="" headerValue="---Select---" listKey="CODE" listValue="CODEDESC" cssClass="inputBoxS " data-content="Settling Agent" theme="simple" id="agentList" onchange="disableOthers(this);" />
</s:elseif>
<s:elseif  test="#request.ELEMENT_NAME=='saleTermPercent'">
	<s:if test="%{productId==openCover}" >
		<s:if test='conveyanceList!=null && conveyanceList.size==1'>
   			<s:select name="saleTermPercent" list="percentList" listKey="CODE" listValue="CODEDESC" cssClass="inputBoxS " data-content="Sale Term Percent"  onchange="getList('?saleTermPercent='+this.value,'toleranceList');" theme="simple"/>
   		</s:if>
		<s:else>
    		<s:select name="saleTermPercent" list="percentList" headerKey="" headerValue="---Select---" listKey="CODE" listValue="CODEDESC" cssClass="inputBoxS " data-content="Sale Term Percent"  onchange="getList('?saleTermPercent='+this.value,'toleranceList');" theme="simple"/>
    	</s:else>
    </s:if>	
    <s:else>
    	<s:select name="saleTermPercent" list="percentList" headerKey="" headerValue="---Select---" listKey="CODE" listValue="CODEDESC" cssClass="inputBoxS " data-content="Sale Term Percent"   onchange="getList('?saleTermPercent='+this.value,'toleranceList');" theme="simple"/>                                
   	</s:else>
</s:elseif>
<s:elseif  test="#request.ELEMENT_NAME=='tolerance'">
	<s:select name="tolerance" list="toleranceList" listKey="CODE" listValue="CODEDESC" cssClass="inputBoxS " data-content="Tolerance" theme="simple"/>
</s:elseif>
<s:elseif  test="#request.ELEMENT_NAME=='lcNo'">
	<s:if test='lcList!=null && lcList.size==1'>
		<s:select name="lcNo" list="lcList" listKey="CODE" listValue="CODEDESC" cssClass="inputBoxS  " data-content="LC No." disabled="%{#disable2==false?(quoteStatus=='RA'):#disable2}" theme="simple"/>
	</s:if>
	<s:else>
		<s:select name="lcNo" list="lcList" headerKey="" headerValue="---Select---" listKey="CODE" listValue="CODEDESC" cssClass="inputBoxS " data-content="Lc No." disabled="%{#disable2==false?(quoteStatus=='RA'):#disable2}" theme="simple"/>
	</s:else>
</s:elseif>
<s:elseif  test="#request.ELEMENT_NAME=='brokersList'">
	<s:select name="brokerCode" id="brokerCode" list="brokerList" headerKey="" headerValue="---Select---"  listKey="CODE" listValue="CODEDESC" cssClass="inputBoxS  " data-content="Broker" onchange="getList('?brokerCode='+this.value,'executiveList');getList('?brokerCode='+this.value,'promotionalList');emptyCustInfo();" theme="simple"/>
</s:elseif>
<s:elseif  test="#request.ELEMENT_NAME=='executive'">
	 <div class="input-group-prepend">
      	<%-- <span class="input-group-text border border-right-0">
      		<i class="fas fa-user-check"></i>
     	</span> --%>
   </div> 
   <s:select name="executive" list="executiveList" headerKey="" headerValue="---Select---" listKey="CODE" listValue="CODEDESC" cssClass="form-control" data-content="Executive" value='executive==null?getText("quotation.executiveDefault"):executive' onchange="getCustList();"/>
   <%-- <s:select name="executive" list="executiveList" headerKey="" headerValue="---Select---" listKey="CODE" listValue="CODEDESC" cssClass="form-control "  value='executive==null?getText("customer.executiveDefault"):executive' onchange="getCustList();"/> --%>
</s:elseif>
<s:elseif  test="#request.ELEMENT_NAME=='brokerList'">
	<s:label key="report.selectuser" theme="simple"/>&nbsp;<s:select list="brokerList" listKey="LOGIN_ID" listValue="USERNAME" name="loginId" cssClass="inputBoxS  " data-content="Select User"  headerKey="" headerValue="---Select---" theme="simple" />
</s:elseif>
<s:elseif  test="#request.ELEMENT_NAME=='coverUpload'">
	<s:select name="ucover[%{rowNum}]" list="coverList" headerKey="" headerValue="---Select---" listKey="CODE" listValue="CODEDESC" cssClass="inputBoxS  " data-content="Cover" disabled="#disable" cssStyle="width:154px;" theme="simple"/>
</s:elseif>
<s:elseif  test="#request.ELEMENT_NAME=='saleTermPercentUpload'">
	<s:select name="usaleTermPercent[%{rowNum}]" id="percent" list="percentList" headerKey="" headerValue="---Select---" listKey="CODE" listValue="CODEDESC" cssClass="inputBoxS " data-content="Sale Term Percent" disabled="%{saleTerm=='205' || #disable}" onchange="getList('?saleTermPercent='+this.value+'&rowNum=%{rowNum}','toleranceList[%{rowNum}]','toleranceList');" cssStyle="width:70px;"/>
</s:elseif>
<s:elseif  test="#request.ELEMENT_NAME=='toleranceUpload'">
	<s:select name="utolerance[%{rowNum}]" list="toleranceList" listKey="CODE" listValue="CODEDESC" cssClass="inputBoxS " data-content="tolerance" disabled="#disable" cssStyle="width:70px;"/>
</s:elseif>
<s:elseif test="#request.ELEMENT_NAME=='packageUpload'">
	<s:select name="upkgDesc[%{rowNum}]" list="packageList" headerKey="" headerValue="---Select---" listKey="CODEDESC" listValue="CODEDESC" cssClass="inputBoxS " data-content="Package List" disabled="#disable2" cssStyle="width:154px;"/>
</s:elseif>
<s:elseif  test="#request.ELEMENT_NAME=='travelCover'">
<s:select name="travelCover" list="travelCoverList" headerKey="" headerValue="---Select---" listKey="CODE" listValue="CODEDESC" cssClass="inputBoxS " data-content="Travel Cover" onchange="getAjaxCoverage(this.form,'?schemeCover=%{schemeCover}&travelCover='+this.value,'coverageList')"/>
</s:elseif>
<s:elseif  test="#request.ELEMENT_NAME=='coverages'">
<s:if test="coveragesList.size()>0">
      <table width="100%" border="0" align="center" cellpadding="4" cellspacing="0">
      	<s:iterator value="coveragesList" var="cov" status="stat">
            	<tr>
              	<td width="2%">&nbsp;</td>
               	<td width="40%">		             
               	<s:property value="CODEDESC"/>
               	</td>
               	<td width="35%">
               	<s:radio list="coverageYNList" name="coverages[%{#stat.count-1}]" id="coverages" value="%{'N'}" theme="simple" />
              	</td>
               	<td width="2%">&nbsp;</td>
              </tr>
         </s:iterator>
      </table>
</s:if>
<s:else>
      <table width="100%" border="0" align="center" cellpadding="4" cellspacing="0">
      <tr>
	      <td width="2%">&nbsp;</td>
	      <td><s:text name="travel.noSchemeAvailable"/></td>
	      <td width="2%">&nbsp;</td>
      </tr> 
      </table>
</s:else>
</s:elseif>
<s:elseif  test="#request.ELEMENT_NAME=='searchResult'">
<display:table name="policyList" pagesize="10"  requestURI="${pageContext.request.contextPath}/initReport.action?reqFrm=Normal"
					class="table" uid="row" id="record" style="width:100%; align:center; " cellpadding="1" cellspacing="1" >
					<display:setProperty name="paging.banner.one_item_found" value="" />
					<display:setProperty name="paging.banner.one_items_found" value="" />
					<display:setProperty name="paging.banner.all_items_found" value="" />
					<display:setProperty name="paging.banner.some_items_found" value="" />
					<display:setProperty name="paging.banner.placement"	value="bottom" />
					<display:setProperty name="paging.banner.onepage" value="" />
					<display:setProperty name="basic.empty.showtable" value="true"/>
					<display:setProperty name="paging.banner.no_items_found" value=""/>	
					<s:if test='menuType!="T" && menuType!="PD" && menuType!="C"'>									
						<display:column sortable="true" style="text-align:center;width:3%" titleKey="report.sno" value="${record_rowNum}"/> 
						<display:column sortable="true" style="text-align:center;width:9%" titleKey="report.quoteNo" property="QUOTE_NO"/>
						<display:column sortable="true" style="text-align:left;width:16%" titleKey="report.custName" property="CUSTOMER_NAME"/>
						<display:column sortable="true"	style="text-align:center;width:13%" titleKey="report.quoteDate"	property="QUOTATION_DATE"/>
					</s:if>							
					<s:if test='menuType=="P" || menuType=="PE"'> 
						<s:if test='#session.product_id!="31" && #session.product_id!="32" && #session.product_id!="33" && #session.product_id!="41" && #session.product_id!="65" && #session.product_id!="30"'>
						<display:column sortable="true"	style="text-align:right;width:6%" titleKey="report.premium" format="{0,number,##,##0.00}"  property="PREMIUM"/>					
						<display:column sortable="true" style="text-align:center;width:13%" titleKey="report.policyNo" property="POLICY_NO"/>
						<display:column sortable="true"	style="text-align:center;width:10%" titleKey="report.schedule" >
							<a href="#" onclick="return popUp('${pageContext.request.contextPath}/Copyofinformation.jsp?policyMode=schedule&policynumber=${record.POLICY_NO}&loginid=${record.LOGIN_ID}','1000','500')">Schedule</a>
						</display:column>
						<display:column sortable="true"	style="text-align:center;width:10%" titleKey="report.debitNote" >
							<c:if test="${record.DEBIT_NOTE_NO != null}">
								<a href="#" onclick="return popUp('${pageContext.request.contextPath}/Copyofinformation.jsp?policyMode=debit&policynumber=${record.POLICY_NO}&loginid=${record.LOGIN_ID}','1000','500')">Debit Note</a>
							</c:if>
						</display:column>
						</s:if>
						<s:else>
						<display:column sortable="true"	style="text-align:right;width:6%" titleKey="report.premium" format="{0,number,##,##,###}"  property="OVERALL_PREMIUM"/>					
						<display:column sortable="true" style="text-align:center;width:13%" titleKey="report.policyNo" property="POLICY_NO"/>
						<%--<display:column sortable="true"	style="text-align:center;width:8%" titleKey="report.corrections">
							<a href="#" onclick="popUp('viewTravel.action?quoteNo=${record.QUOTE_NO}&applicationNo=${record.APPLICATION_NO}',650,550)">Correction</a>
						</display:column>--%>
						<display:column sortable="true"	style="text-align:center;width:10%" titleKey="report.schedule" >
							   <a href="#" onclick="return popUp('${pageContext.request.contextPath}/PdfSummary_Schedule.Travel?quoteNo=${record.QUOTE_NO}','1000','500')">Schedule</a>
						</display:column>
						<display:column sortable="true"	style="text-align:center;width:10%" titleKey="report.debitNote" >
							<c:if test="${record.DEBIT_NOTE_NO != null}">
							    <a href="#" onclick="return popUp('${pageContext.request.contextPath}/PdfSummary_Debit.Travel?quoteNo=${record.QUOTE_NO}','1000','500')">Debit</a>
							</c:if>
						</display:column>
						<display:column sortable="true"	style="text-align:center;width:10%" titleKey="report.receipt" >
							<c:if test="${record.DEBIT_NOTE_NO != null}">
							    <a href="#" onclick="return popUp('${pageContext.request.contextPath}/PdfSummary_Receipt.Travel?quoteNo=${record.QUOTE_NO}','1000','500')">Receipt</a>
							</c:if>
						</display:column>
						</s:else>
						<s:if test='#session.product_id!="31" && #session.product_id!="32" && #session.product_id!="33"&& #session.product_id!="41" && #session.product_id!="65" && #session.product_id!="30"'>
						<display:column sortable="true"	style="text-align:center;width:10%" titleKey="report.creditNote" >
							<c:if test="${record.CREDIT_NOTE_NO != null}">
								<a href="#" onclick="return popUp('${pageContext.request.contextPath}/Copyofinformation.jsp?policyMode=credit&policynumber=${record.POLICY_NO}&loginid=${record.LOGIN_ID}','1000','500')">Credit Note</a>
							</c:if>
						</display:column>
						</s:if>
						<%--<s:else>
						<display:column sortable="true"	style="text-align:center;width:10%" titleKey="report.cancelReIssue" >
		                    <a href="#" onclick="return popUp('${pageContext.request.contextPath}/cancelReissueTravel.action?quoteNo=${record.QUOTE_NO}&applicationNo=${record.APPLICATION_NO}&policyNo=${record.POLICY_NO}&product_id=#session.product_id &branch_code=#session.branch_code','650','420');">Cancel/ReIssue</a>
						</display:column>
						</s:else>--%>
						<display:column sortable="true"	style="text-align:center;width:10%" titleKey="report.endorse" >
							<a href="#" onclick='endorsement("E","${record.QUOTE_NO}","${record.POLICY_NO}","${record.CUSTOMER_NAME}","${record.BROKER_NAME}")'>Endorse</a>
						</display:column>
					</s:if>
					<s:elseif test='menuType=="QL"'>
						<display:column sortable="true"	style="text-align:center;width:13%" title="Validity Date"	property="VALIDITY_DATE"/>
						<display:column sortable="true"	style="text-align:center;width:8%" title="Active">
							<a href="#" onclick="sentEMail('','ACTIVE','${record.QUOTE_NO}')" >Active</a>
						</display:column>
						<display:column sortable="true"	style="text-align:center;width:8%" title="DeActive">
						<a href="#" onclick="sentEMail('','LAPSED','${record.QUOTE_NO}')">Reject</a>
						</display:column>
					</s:elseif>
					<s:elseif test='menuType=="QE" || menuType=="QS"'>
						<display:column sortable="true"	style="text-align:center;width:13%" title="Validity Date"	property="VALIDITY_DATE"/>
						<s:if test='menuType=="QE"'>
						<s:if test='#session.product_id=="31" || #session.product_id=="32" || #session.product_id=="33" || #session.product_id=="65" && #session.product_id=="30"'>					
						<display:column sortable="true"	style="text-align:right;width:6%" title="Premium" format="{0,number,##,##,###}"  property="PREMIUM"/>
						</s:if>
						<s:else>					
						<display:column sortable="true"	style="text-align:right;width:6%" title="Premium" format="{0,number,##,##,###.00}"  property="PREMIUM"/>
						</s:else>					
						</s:if>
						<display:column sortable="true"	style="text-align:center;width:8%" title="Edit">
							<a href="#" onclick="editQuote('${record.APPLICATION_NO}','${record.QUOTE_NO}','<s:property value="%{menuType}" />','${record.CUSTOMER_ID}')">Edit</a>
						</display:column>
						<s:if test='menuType=="QE"'>
						<s:if test='#session.product_id!="31" && #session.product_id!="32" && #session.product_id!="33" && #session.product_id!="65" && #session.product_id!="30"'>
						<display:column sortable="true"	style="text-align:center;width:8%" title="Email">
							<a href="#" onclick="sentEMail('${record.APPLICATION_NO}','MAIL','${record.QUOTE_NO}')">Email</a>
						</display:column>
						<display:column sortable="true"	style="text-align:center;width:8%" title="Print">
							<a href="#" onclick="viewQuote('${record.QUOTE_NO}')">Print</a>
						</display:column>
						</s:if>
						<s:else>
						<display:column sortable="true"	style="text-align:center;width:8%" title="Email">
							<a href="#" onclick="sentEMail('${record.APPLICATION_NO}','MAIL','${record.QUOTE_NO}')">Email</a>
						</display:column>
						<display:column sortable="true"	style="text-align:center;width:8%" title="Print">
							<a href="#" onclick="return popUp('${pageContext.request.contextPath}/PdfSummary_Draft.Travel?quoteNo=${record.QUOTE_NO}','1000','500')">Print</a>
						</display:column>
						</s:else>
						</s:if>
						<display:column sortable="true"	style="text-align:center;width:8%" title="Reject">
							<a href="#" onclick="sentEMail('${record.APPLICATION_NO}','LAPSED','${record.QUOTE_NO}')">Reject</a>
						</display:column>	
					</s:elseif>
					<s:elseif test='menuType=="RU" || menuType=="RA"'>
						<display:column sortable="true"	style="text-align:center;width:8%" title="Edit">
							<a href="#" onclick="editQuote('${record.APPLICATION_NO}','${record.QUOTE_NO}','<s:property value="%{menuType}" />','${record.CUSTOMER_ID}')">Edit</a>
						</display:column>
						<display:column sortable="true"	style="text-align:center;width:8%" title="Reject">
							<a href="#" onclick="sentEMail('','LAPSED','${record.QUOTE_NO}')">Reject</a>
						</display:column>
					</s:elseif>
					<s:elseif test='menuType=="RR"'>
						<display:column sortable="true"	style="text-align:center;width:13%" title="Remarks"	property="LAPSED_REMARKS"/>
						<display:column sortable="true"	style="text-align:center;width:8%" title="View">
							<a href="#" onclick="popUp('viewTravel.action?quoteNo=${record.QUOTE_NO}&applicationNo=${record.APPLICATION_NO}&selection=profile',650,650)">View</a>
						</display:column>
					</s:elseif>
					<s:elseif test='menuType=="L"'>
						<display:column sortable="true"	style="text-align:center;width:13%" title="Validity Date"	property="VALIDITY_PERIOD"/>
						<display:column sortable="true"	style="text-align:center;width:15%" title="Rejected Date" property="LAPSED_DATE"/>	
						<display:column sortable="true"	style="text-align:center;width:15%" title="Remarks" property="LAPSED_REMARKS"/>
						<display:column sortable="true"	style="text-align:center;width:8%" title="View">
							<a href="#" onclick="popUp('viewTravel.action?quoteNo=${record.QUOTE_NO}&applicationNo=${record.APPLICATION_NO}&selection=profile',650,650)">View</a>
						</display:column>
					</s:elseif>
					<s:elseif test='menuType=="T"'>
						<display:column sortable="true" style="text-align:center;width:6%" title="S.No" value="${record_rowNum}"/>
						<display:column sortable="true" style="text-align:center;width:9%" title="Transaction Id" property="TRANSACTION_ID"/>
						<display:column sortable="true" style="text-align:center;width:16%" title="Valid Records" >
							<a href="#" onclick="return declaration('${record.TRANSACTION_ID}')">${record.VALID_RECORDS}</a>
						</display:column>
						<display:column sortable="true"	style="text-align:center;width:13%" title="Invalid Records"	property="INVALID_RECORDS"/>
					</s:elseif>
					<s:elseif test='menuType=="PD"'>
						<display:column sortable="true" style="text-align:center;width:6%" title="S.No" value="${record_rowNum}"/> 
						<display:column sortable="true" style="text-align:center;width:10%" title="Policy No." property="POLICY_NO"/>
						<display:column sortable="true" style="text-align:center ;width:14%" title="OpenCover Customer Name" property="OPENCOVER_CUST_NAME"/>
						<display:column sortable="true"	style="text-align:right;width:6%" title="Premium"  format="{0,number,##,##,###.00}"	property="PREMIUM"/>						
						<display:column sortable="true"	style="text-align:center;width:8%" title="Total of Certificates" >
							<a href="#" onclick="declaration('${record.POLICY_NO}')"/><s:property value="#attr.record.POLICY_COUNT"/> </a>
						</display:column>
						<display:column sortable="true"	style="text-align:center;width:8%" title="Schedule" >
							<a href="#" onclick="return popUp('${pageContext.request.contextPath}/Copyofinformation.jsp?policyMode=scheduleMultiple&policynumber=${record.POLICY_NO}&loginid=${record.LOGIN_ID}','1000','500')">Schedule</a>
						</display:column>
						<display:column sortable="true"	style="text-align:center;width:8%" title="Debit Note" >
							<c:if test="${record.DEBIT_NOTE_NO != null}">
								<a href="#" onclick="return popUp('${pageContext.request.contextPath}/Copyofinformation.jsp?policyMode=debitMultiple&policynumber=${record.POLICY_NO}&loginid=${record.LOGIN_ID}','1000','500')">Debit</a>
							</c:if>
						</display:column>
						<display:column sortable="true"	style="text-align:center;width:8%" title="Credit Note" >
							<c:if test="${record.CREDIT_NOTE_NO != null}">
								<a href="#" onclick="return popUp('${pageContext.request.contextPath}/Copyofinformation.jsp?policyMode=creditMultiple&policynumber=${record.POLICY_NO}&loginid=${record.LOGIN_ID}','1000','500')">Credit</a>
							</c:if>
						</display:column>
					</s:elseif>		
					<s:elseif test='menuType=="C"'>
						<display:column sortable="true" style="text-align:center;width:6%" title="S.No" value="${record_rowNum}"/> 
						<display:column sortable="true"	style="text-align:left;width:13%" title="Customer Name"	property="FIRST_NAME"/>					
						<display:column sortable="true"	style="text-align:left;width:10%" title="Address"  property="ADDRESS"/>
						<display:column sortable="true"	style="text-align:left;width:10%" title="Customer Civil ID" property="CUSTOMER_SOURCE"/>					
						<display:column sortable="true"	style="text-align:left;width:15%" title="Email Id" property="EMAIL"/>
						<display:column sortable="true"	style="text-align:left;width:10%" title="Contact No." property="MOBILE"/>	
					<%--	<display:column sortable="true"	style="text-align:left;width:10%" title="Core App. Code" property="MISSIPPI_CUSTOMER_CODE"/> --%>
						<display:column sortable="true"	style="text-align:center;width:10%" title="Edit">
							<a href="#" onclick="editQuote('${record.APPLICATION_NO}','${record.QUOTE_NO}','<s:property value="%{menuType}" />','${record.CUSTOMER_ID}')">Edit</a>						
						</display:column>									
					</s:elseif>	
					<s:elseif test='menuType=="E"'> 
						<display:column sortable="true"	style="text-align:right;width:6%" title="Premium" format="{0,number,##,###}"  property="PREMIUM"/>					
						<display:column sortable="true" style="text-align:center;width:13%" title="Policy Number">
							<c:if test='${record.STATUS == "P"}'>
								<c:out value="${record.POLICY_NO}"/>
							</c:if>
						</display:column>
						<s:if test='#session.product_id=="3" || #session.product_id=="11"'>
						<display:column sortable="true"	style="text-align:center;width:10%" title="Schedule" >
							<c:if test='${record.STATUS == "P"}'>
								<a href="#" onclick="return popUp('${pageContext.request.contextPath}/Copyofinformation.jsp?policyMode=schedule&policynumber=${record.POLICY_NO}&loginid=${record.LOGIN_ID}','1000','500')">Schedule</a>
							</c:if>
						</display:column>
						<display:column sortable="true"	style="text-align:center;width:10%" title="Debit Note" >
							<c:if test='${record.STATUS == "P" && record.DEBIT_NOTE_NO != null}'>
								<a href="#" onclick="return popUp('${pageContext.request.contextPath}/Copyofinformation.jsp?policyMode=debit&policynumber=${record.POLICY_NO}&loginid=${record.LOGIN_ID}','1000','500')">Debit Note</a>
							</c:if>
						</display:column>
						<display:column sortable="true"	style="text-align:center;width:10%" title="Credit Note" >
							<c:if test='${record.STATUS == "P" && record.CREDIT_NOTE_NO != null}'>
								<a href="#" onclick="return popUp('${pageContext.request.contextPath}/Copyofinformation.jsp?policyMode=credit&policynumber=${record.POLICY_NO}&loginid=${record.LOGIN_ID}','1000','500')">Credit Note</a>
							</c:if>
						</display:column>
						</s:if>
						<s:else>
						<display:column sortable="true"	style="text-align:center;width:10%" title="Schedule" >
						    <c:if test='${record_rowNum == "1"}'>
								<c:set var="eQuoteNo" value="${record.QUOTE_NO}"/>
								<c:set var="eApplicationNo" value="${record.APPLICATION_NO}"/>
					        </c:if>
							<c:if test='${record.STATUS == "P"}'>
								<a href="#" onclick="return popUp('${pageContext.request.contextPath}/PdfSummary_Schedule.Travel?quoteNo=${record.QUOTE_NO}','1000','500')">Schedule</a>
							</c:if>
						</display:column>
						<display:column sortable="true"	style="text-align:center;width:10%" title="Debit Note" >
							<c:if test='${record.STATUS == "P" && record.DEBIT_NOTE_NO != null}'>
								<a href="#" onclick="return popUp('${pageContext.request.contextPath}/PdfSummary_Debit.Travel?quoteNo=${record.QUOTE_NO}','1000','500')">Debit Note</a>
							</c:if>
						</display:column>
						 <display:column sortable="true"	style="text-align:center;width:10%" title="Receipt" >
							<c:if test='${record.STATUS == "P" && record.RECEIPT_NO != null}'>
								<a href="#" onclick="return popUp('${pageContext.request.contextPath}/PdfSummary_Receipt.Travel?quoteNo=${record.QUOTE_NO}','1000','500')">Receipt</a>
							</c:if>
						  </display:column>
						</s:else>
						<display:column sortable="true"	style="text-align:center;width:10%" title="Status" >
								<c:choose>
									<c:when test='${record.REF_STATUS == "RU"}'>
										Referral
									</c:when>
									<c:when test='${record.REF_STATUS == "RA"}'>
										Referral Approved
									</c:when>
									<c:when test='${record.REF_STATUS == "RR"}'>
										Referral Rejected
									</c:when>
									<c:when test='${record.REF_STATUS == "N"}'>
										Normal
									</c:when>
								</c:choose>
						</display:column>
						<display:column sortable="true"	style="text-align:center;width:10%" titleKey="report.endorse" >
							<s:if test='issuer !=null && issuer.length() > 0' >
								<c:choose>
									<c:when test='${record.STATUS == "E" && record.ENDT_STATUS == "Y"}'>
										<a href="#" onclick="endorsementType('ET','P','${record.ENDT_TYPE}','${record.QUOTE_NO}','${record.REF_STATUS}','${record.APPLICATION_NO}')">Edit</a>
										<s:set var="endtStatus" value="%{'Y'}" ></s:set>
									</c:when>
									<c:when test='#session.product_id=="3" || #session.product_id=="11"'>
									<c:when test='${record.STATUS == "P" && record.ENDT_STATUS == "Y"}'>
										<a href="#" onclick="popUp('${pageContext.request.contextPath}/documentReport.action?policyNo=${record.POLICY_NO}&applicationNo=${record.APPLICATION_NO}&endTypeId=${record.ENDT_TYPE}','1000','500')">Endt Schedule</a>
										<s:set var="endtStatus" value="%{'N'}" ></s:set>
										<c:set var="quoteNo" value="${record.QUOTE_NO}" ></c:set>
										<c:set var="applicationNo" value="${record.APPLICATION_NO}" ></c:set>
									</c:when>
									<c:when test='${record.STATUS == "P"}'>
										<a href="#" onclick="popUp('${pageContext.request.contextPath}/documentReport.action?policyNo=${record.POLICY_NO}&applicationNo=${record.APPLICATION_NO}&endTypeId=${record.ENDT_TYPE}','1000','500')">Endt Schedule</a>
									</c:when>
									</c:when>
								</c:choose>
							</s:if>
						</display:column>
					</s:elseif>
					</display:table>
</s:elseif>
<s:elseif  test="#request.ELEMENT_NAME=='showDoc'">
<s:hidden name="FileCountforDrm%{docId}" id="FileCountforDrm%{docId}" value="%{updDocumentList.size()}" />
	<table align="center" width="100%">
	<s:if test='updDocumentList!=null && updDocumentList.size()>0'>
	<tr>
      	<td width="5%" align="center" class="heading"><s:text name="label.file.id" /></td>
	    <td width="20%" align="center" class="heading"><s:text name="label.file.name" /></td>
	    <td width="45%" align="center" class="heading"><s:text name="label.file.desc" /></td>
	    <td width="10%" align="center" class="heading"><s:text name="label.upload" />&nbsp;<s:text name="label.date" /></td>
	    <td width="10%" align="center" class="heading"><s:text name="label.download" /></td>
	    <td width="10%" align="center" class="heading"><s:text name="label.delete" /></td>
   	</tr>
   	<s:hidden name="FileCountforDrm%{docId}" id="FileCountforDrm%{docId}" value="%{updDocumentList.size()}" />
   	<s:iterator value="updDocumentList" var="stat">
    	<tr style="background-color: #F7F7F7;">
    		<td style="text-align: center;"><s:property value="#stat.docSNo" /></td>
    		<td>
    			<a href="#" onclick="openUploadedDoc('<s:property value="#stat.docOurName" />')">
    				<s:property value="#stat.docName" />
    			</a>
   			</td>
    		<td><s:property value="#stat.docDesc" /></td>
    		<td style="text-align: center;"><s:property value="#stat.docUploadDate" /></td>
    		<td align="center">
			    <input type="button" class="btn" name="download" value="Download" onclick="downloadFile('<s:property value="#stat.docOurName" />','<s:property value="#stat.docName" />',this.form)" />
			</td>
			<td align="center">
				<input type="button" class="btn" name="delete" value="Delete" onclick="deleteDocuments('<s:property value="#stat.docId" />', '<s:property value="#stat.docSNo" />')" />
   			</td>  
    	</tr>
   	</s:iterator>
   	</s:if>
   	<s:else>
   		<tr>
   			<td align="center"><s:text name="msg.details.not.found" /></td>
   		</tr>
   	</s:else>
   	</table>
</s:elseif>
<s:elseif  test="#request.ELEMENT_NAME=='customerList'">
	<table width="100%" border="1" bordercolor="#A4A4A4"  align="center" cellpadding="4" cellspacing="0" >
					<tr>     				   
						<td width="2%" bgcolor="#4f6180" style="color: #FFFFFF" align="center"></td>
						<td width="4%" bgcolor="#4f6180" style="color: #FFFFFF" align="center"><b><s:label key="customer.customerName"  theme="simple"/></b></td>
						<td width="10%"  bgcolor="#4f6180" style="color: #FFFFFF" align="center"><b><s:label key="customer.coreAppCode"  theme="simple"/></b></td>
						<td width="6%"  bgcolor="#4f6180" style="color: #FFFFFF" align="center"><b><s:label key="customer.city"  theme="simple"/></b></td>
						<td width="6%"  bgcolor="#4f6180" style="color: #FFFFFF" align="center"><b><s:label key="customer.mobileNo"  theme="simple"/></b></td>
						<td width="10%"  bgcolor="#4f6180" style="color: #FFFFFF" align="center"><b><s:label key="customer.email"  theme="simple"/></b></td>                       
					</tr>
					<s:if test="customerSelection.size > 0">					
					<s:iterator value="customerSelection" var="customerdetail" status="stat">
						<tr>   
						 	<td width="2%"><input type="radio" name="selects"onclick="customerDetail('<s:property value="%{#customerdetail.TITLE}"/>','<s:property value="%{#customerdetail.ADDRESS1}"/>','<s:property value="%{#customerdetail.ADDRESS2}"/>','<s:property value="%{#customerdetail.MOBILE}"/>','<s:property value="%{#customerdetail.EMIRATE}"/>','<s:property value="%{#customerdetail.POBOX}"/>','<s:property value="%{#customerdetail.FIRST_NAME}"/>','<s:property value="%{#customerdetail.MISSIPPI_CUSTOMER_CODE}"/>','<s:property value="%{#customerdetail.CUSTOMER_ID}"/>','<s:property value="%{#customerdetail.EMAIL}"/>','<s:property value="%{#customerdetail.CUST_AR_NO}"/>','<s:property value="%{#customerdetail.cust_name}"/>')"/></td>   				  
							<td width="10%" class="bg"><s:property value="FIRST_NAME" /></td>
							<td width="4%" class="bg"><s:property value="MISSIPPI_CUSTOMER_CODE"  /></td>
							<td width="10%" class="bg"><s:property value="CITY_NAME" /></td>
							<td width="6%" class="bg"><s:property value="MOBILE" /></td>
							<td width="10%" class="bg"><s:property value="EMAIL" /></td>
						</tr>
					</s:iterator>
					</s:if>
					<s:else>
					<tr><td colspan="5"><s:label key="customer.msg.nothingFound" theme="simple"></s:label> </tr>
					</s:else>
	</table>					
</s:elseif>
<s:elseif  test="#request.ELEMENT_NAME=='promotionalList'">
	<s:if test='dubaiTradeStatus'>
		<s:text name="Promotional Code" /><br />
	    <s:textfield name="promotionalCode" cssClass="inputBox " data-content="Promotional Code"  maxLength="25" disabled="#disable"/>
	</s:if>
</s:elseif>
<s:elseif  test="#request.ELEMENT_NAME=='fragileoff'">
	<s:radio name="fragile[0]" list="#{true:'Yes',false:'No'}" disabled="true" value='false' />
</s:elseif>	
<s:elseif  test="#request.ELEMENT_NAME=='fragileon'">
	<s:radio name="fragile[0]" list="#{true:'Yes',false:'No'}" disabled="#disable" value='%{#comLst.size()>0 ? #comLst[0].FRAGILE_SELECTED: false}'/>
</s:elseif>
<%--<s:elseif test="#request.ELEMENT_NAME=='vehicleTypeDetailsAjax'">
	<s:set var="sVehicleTypeDetails" value="%{vehicleTypeDetails}"/>
	<div class="row">
		<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
			<s:text name="motor.make" />&nbsp;:&nbsp;<s:property value="#sVehicleTypeDetails[0].MAKE_NAME"/>
		</div>
		<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
			<s:text name="motor.model" />&nbsp;:&nbsp;<s:property value="#sVehicleTypeDetails[0].MODEL_NAME"/>
		</div>
	</div>
	<div class="row">
		<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
			<table class="footable" width="100%">
				<thead>
					<tr>
						<th></th>
						<th><s:text name="motor.typeOfBody" /></th>
						<th><s:text name="motor.vehicleUsage" /></th>
						<th><s:text name="motor.seatingCapacity" /></th>
					</tr>
				</thead>
				<tbody>
					<s:iterator value="#sVehicleTypeDetails" var="record1" status="status1">
						<tr>
							<td> <input type="radio" name="selects" onclick="setModalVehicleDetails('${status1.index}','${record1.BODY_ID}','${record1.VTYPE_ID}','${record1.BODYNAME}','${record1.VTYPE_NAME}');" /></td>
							<td> <s:property value="#record1.BODYNAME"/> </td>
							<td> <s:property value="#record1.VTYPE_NAME"/> </td>
							<td> 
								<s:if test='#record1.BODY_ID!="9" && #record1.BODY_ID!="27"'>
									<s:textfield name="tSeatingCapacity[%{#status1.count}]" id="tSeatingCapacity_%{#status1.index}" maxLength="2" onkeyup="checkNumbers(this);" cssClass="inputBox " data-content="Seating Capacity" theme="simple"/>
								</s:if>
								<s:else>
									<s:textfield name="tSeatingCapacity[%{#status1.count}]" id="tSeatingCapacity_%{#status1.index}" maxLength="2" onkeyup="checkNumbers(this);" cssClass="inputBox " data-content="Seating Capacity" theme="simple" value="2" readonly="true"/>
								</s:else>							
							</td>
							
						</tr>
					</s:iterator>
				</tbody>
			</table>
			<br class="clear" />
			<div class="text-primary">
				<b>Help <i class="fa fa-help"></i> </b>
				<ol type="a">
					<li>Type of Body and Vehicle Usage – Select the correct combination of body type and use of your vehicle e.g. a corolla that is not used for business can be Sedan with Private Use while a corolla used as a taxi can be sedan with public transport use.</li>
					<li>Seating Capacity – Maximum number of people the vehicle can carry including the driver</li>
				</ol>
			</div>
		</div>
	</div>
	<s:hidden name="modalSelectId" id="modalSelectId"/>
	<s:hidden name="modalTypeBody" id="modalTypeBody"/>
	<s:hidden name="modalVehicleUsage" id="modalVehicleUsage"/>
	<s:hidden name="modalTypeBodyName" id="modalTypeBodyName"/>
	<s:hidden name="modalVehicleUsageName" id="modalVehicleUsageName"/>
</s:elseif> --%>
<s:elseif test="#request.ELEMENT_NAME=='vehicleTypeDetailsAjax'">
	<s:set var="sVehicleTypeDetails" value="%{vehicleTypeDetails}"/>
         <table class="table table-bordered table-sm">
           <thead>
             <tr>
               <th>Choose</th>
               <th>Type of Body</th>
               <th>Vehicle Usage</th>
               <th>Seating Capacity</th>
             </tr>
           </thead>
           <tbody>
	           <s:if test='#sVehicleTypeDetails.size()>0'>
	             <s:iterator value="#sVehicleTypeDetails" var="record1" status="status1">
					<tr>
						<%--<td> <input type="radio" name="selects" id="btn_${record1.BODY_ID}_${record1.VTYPE_ID}" onclick="setModalVehicleDetails('${status1.index}','${record1.BODY_ID}','${record1.VTYPE_ID}','${record1.BODYNAME}','${record1.VTYPE_NAME}');checkBodyClick(this.form);" /></td> --%>
						<td> <input type="radio" name="selects" id="btn_${record1.BodyId}${record1.VehicleUseageId}" value="<s:property value="#record1.BodyId" />~<s:property value="#record1.VehicleUseageId" />" onclick="setModalVehicleDetails('${status1.index}','${record1.BodyId}','${record1.VehicleUseageId}','${record1.BodyType}','${record1.VehicleUsage}');checkBodyClick(this.form);" /></td>
						<td> <s:property value="#record1.BodyType"/> </td>
						<td> <s:property value="#record1.VehicleUsage"/> </td>
						<td> 
							<s:if test='#record1.BodyId!="9" && #record1.BodyId!="27"'>
								<s:textfield name="tSeatingCapacity[%{#status1.count}]" id="tSeatingCapacity_%{#record1.BodyId}%{#record1.VehicleUseageId}" maxLength="2" onkeyup="checkNumbers(this);checkBodyClick(this.form);setVehicleTypeDetails(this.form);" cssClass="form-control " data-content="Seating Capacity" theme="simple" style="height:30px !important"/>
							</s:if>
							<s:else>
								<s:textfield name="tSeatingCapacity[%{#status1.count}]" id="tSeatingCapacity_%{#record1.BodyId}%{#record1.VehicleUseageId}" maxLength="2" onkeyup="checkNumbers(this);checkBodyClick(this.form);setVehicleTypeDetails(this.form);" cssClass="form-control " data-content="Seating Capacity" theme="simple" value="2" readonly="true" style="height:30px !important"/>
							</s:else>							
						</td>
					</tr>
				</s:iterator>
				<tr>
					<td colspan="4">
						<div class="text-primary" style="font-size: 12px;">
							<b>Help <i class="fa fa-help"></i> </b>
							<ol type="a">
								<li>Type of Body and Vehicle Usage – Select the correct combination of body type and use of your vehicle e.g. a corolla that is not used for business can be Sedan with Private Use while a corolla used as a taxi can be sedan with public transport use.</li>
								<li>Seating Capacity – Maximum number of people the vehicle can carry including the driver</li>
							</ol>
						</div>
					</td>
				</tr>
				</s:if>
				<s:else>
					<tr><td align="center" colspan="4">No Data Found For Selected Make and Model</td></tr>
				</s:else>
           </tbody>
         </table>
	<s:hidden name="modalSelectId" id="modalSelectId"/>
	<s:hidden name="modalTypeBody" id="modalTypeBody"/>
	<s:hidden name="modalVehicleUsage" id="modalVehicleUsage"/>
	<s:hidden name="modalTypeBodyName" id="modalTypeBodyName"/>
	<s:hidden name="modalVehicleUsageName" id="modalVehicleUsageName"/>
	
	<script>
       try{
	       var name = document.getElementById('bodyName').value;
	       var usage = document.getElementById('usageName').value;
	       var capacity = document.getElementById('seatingCapacity').value;
	       //alert(name+", "+usage+", "+capacity);
	       document.getElementById('btn_'+name+usage).checked = true;
	       document.getElementById('tSeatingCapacity_'+name+usage).value = capacity;
	       //tSeatingCapacity
       }catch(err){
    	   console.log(err);
       }
      
      </script>
</s:elseif>
<s:elseif test="#request.ELEMENT_NAME=='makeListAjax'">
	<%--
	<s:select name="makeIdList[0]" id="make" list="makeList" listKey="CODE" listValue="CODEDESC" headerKey="" headerValue="---Select---" onchange="getAjaxModel(this.form,'?make='+this.value,'modelList')" cssClass="inputBoxS" disabled="#disable1" theme="simple"/>
	--%>
	<s:select name="makeIdList[0]" id="make" list="makeList" listKey="Code" listValue="Description" headerKey="" headerValue="---Select---" onchange="getAjaxModel(this.form,'?make='+this.value,'modelList');removeVehicleTypeDetails();" cssClass="inputBoxS " data-content="Select your vehicle Make e.g. Toyota" disabled="#disable1" theme="simple"/>
</s:elseif>
<s:elseif  test="#request.ELEMENT_NAME=='model'">
	<%--
	<s:select name="modelIdList[0]" id="model" list="modelList" listKey="CODE" listValue="CODEDESC" headerKey="" headerValue="---Select---" onchange="getAjaxModel(this.form,'?model='+this.value,'typeBodyAjax');getAjaxModel(this.form,'?model='+this.value,'vehicleUsageAjax');getDeductibleAjax(this.form);" cssClass="inputBoxS" disabled="#disable1" theme="simple"/>
	--%>
	<s:select name="modelIdList[0]" id="model" list="modelList" listKey="Code" listValue="Description" headerKey="" headerValue="---Select---" onchange="removeVehicleTypeDetails(); vehicleTypeDetailsAjax(this.form);" class="form-control" data-content="Select your vehicle Model e.g. Corolla" disabled="#disable1" theme="simple"/>
</s:elseif>
<s:elseif  test="#request.ELEMENT_NAME=='noOfCylinder'">
	<s:select name="noOfCylinderIdList[0]" list="noOfCylinderList" listKey="CODE" listValue="CODEDESC" headerKey="" headerValue="-Select-" cssClass="inputBoxS " data-content="No. of Cylinder" theme="simple"/>
</s:elseif>
<s:elseif test="#request.ELEMENT_NAME=='typeBody'">
	<s:select name="typeBodyIdList[0]" id="typeBody" list="typeBodyList" listKey="CODE" listValue="CODEDESC" cssClass="inputBoxS " data-content="Body Type" disabled="true" theme="simple"/>
</s:elseif>
<s:elseif test="#request.ELEMENT_NAME=='vehicleUsageAjax'">
	<s:select name="vehicleUsageIdList[0]"  id="vehicleUsage" list="vehicleUsageList" listKey="CODE" listValue="CODEDESC" headerKey="" headerValue="---Select---" cssClass="inputBoxS " data-content="Vehicle Usage" onchange="getDeductibleAjax(this.form);" disabled="#disable1" theme="simple"/>
</s:elseif>
<s:elseif  test="#request.ELEMENT_NAME=='PolicyExpirydate'">
	<%-- <s:select name="policyEndDate" id="policyEndDate" list="policyEndList" listKey="CODE" listValue="CODEDESC" cssClass="inputBoxS " data-content="Policy End Date" disabled="#disable1" theme="simple"/> --%>
	<s:select name="policyEndDate" id="policyEndDate" list="policyEndList" listKey="CODE" listValue="CODEDESC" class="form-control border border-left-0"  placeholder="Policy End Date" disabled="#disable1"  theme="simple"/>
</s:elseif>
<s:elseif  test="#request.ELEMENT_NAME=='PolicyExpirydateNew'">
	<%-- <s:select name="policyEndDate" id="policyEndDate" list="policyEndList" listKey="CODE" listValue="CODEDESC" cssClass="inputBoxS " data-content="Policy End Date" disabled="#disable1" theme="simple"/> --%>
	<%-- <s:select name="policyEndDate" id="policyEndDate" list="policyEndList" listKey="CODE" listValue="CODEDESC" class="form-control border border-left-0"  placeholder="Policy End Date" disabled="#disable1"  theme="simple"/>--%>
	<div class="input-group-prepend">
        <span class="input-group-text border border-right-0"><i class="fas fa-calendar-alt"></i></span>
    </div>
	<s:select name="policyEndDate" id="policyEndDate" list="policyEndList" listKey="Code" listValue="Description" class="form-control"  placeholder="Policy End Date" disabled="#disable1"  theme="simple"/>
			                        
</s:elseif>
<s:elseif  test="#request.ELEMENT_NAME=='deductibleAjax'">
	<s:select name="deductibleIdList[0]" id="deductible" list="deductibleList" listKey="Code" listValue="Description" headerKey="" headerValue="---Excess Limit---" class="form-control" placeholder="Excess Limit" disabled="#disable1" theme="simple"/>
</s:elseif>
<s:elseif  test="#request.ELEMENT_NAME=='ncbAjax'">
	<s:select name="noClaimBonusIdList[0]" id="noClaimBonus" list="noClaimBonusList" headerKey="" headerValue="---Select---" onchange="toggleNCB(this.value);" cssClass="inputBoxS R" data-content="The number of years you have driven without a claim" disabled="#disable1" listKey="CODE" listValue="CODE" theme="simple"/>
</s:elseif>
<s:elseif  test="#request.ELEMENT_NAME=='bankInfoAjx'">
	<div class="row">	 
<%-- 		<s:set var="info" value="bankInformAjax" ></s:set>	 --%>
<!-- 		<div class="textfield33V"> -->
<%-- 			<div class="textV"><s:text name="label.accountName" /></div> --%>
<!-- 			<div class="tboxV"> -->
<%-- 				<s:property value="%{#info.ACCOUNT_HOLDER}"/> --%>
<!-- 			</div> -->
<!-- 		</div> -->
<!-- 		<div class="textfield33V"> -->
<%-- 			<div class="textV"><s:text name="label.bank" /></div> --%>
<!-- 			<div class="tboxV"> -->
<%-- 				<s:property value="%{#info.BANK_NAME}"/> --%>
<!-- 			</div> -->
<!-- 		</div> -->
<!-- 		<div class="textfield33V"> -->
<%-- 			<div class="textV"><s:text name="label.accountNumber" /></div> --%>
<!-- 			<div class="tboxV"> -->
<%-- 				<s:property value="%{#info.ACCOUNT_NUMBER}"/> --%>
<!-- 			</div> -->
<!-- 		</div> -->
<!-- 		<div class="textfield33V"> -->
<%-- 			<div class="textV"><s:text name="label.branch" /></div> --%>
<!-- 			<div class="tboxV"> -->
<%-- 				<s:property value="%{#info.BRANCH_NAME}"/> --%>
<!-- 			</div> -->
<!-- 		</div> -->
<!-- 		<div class="textfield33V"> -->
<%-- 			<div class="textV"><s:text name="label.currency" /></div> --%>
<!-- 			<div class="tboxV"> -->
<%-- 				<s:property value="%{#info.CURRENCY_TYPE}"/> --%>
<!-- 			</div> -->
<!-- 		</div> -->
<!-- 		<div class="textfield33V"> -->
<%-- 			<div class="textV"><s:text name="Branch Code" /></div> --%>
<!-- 			<div class="tboxV"> -->
<%-- 				<s:property value="%{#info.BRANCH_CODE}"/> --%>
<!-- 			</div> -->
<!-- 		</div>		 -->
<!-- 		<div class="textfield33V"> -->
<%-- 			<div class="textV"><s:text name="label.swiftCode" /></div> --%>
<!-- 			<div class="tboxV"> -->
<%-- 				<s:property value="%{#info.SWIFT_CODE}"/> --%>
<!-- 			</div> -->
<!-- 		</div> -->
						<div class="row" id="bankInfoAjx" style="padding: 0 15px 0 15px;">
							<h4><s:text name="bank.info.payment" /></h4>		
							<br/>
							<s:set var="info" value="bankInformAjax" ></s:set>
							<table style="width: 100%">
								<tr style="width: 100%">
									<th style="width: 15%"><s:text name="label.accountName" /></th>
									<td style="width: 15%"><s:property value="%{#info.AccountName}"/></td>
									<th style="width: 15%"><s:text name="label.bank" /></th>
									<td style="width: 15%"><s:property value="%{#info.BankName}"/></td>
									<th style="width: 15%"><s:text name="label.accountNumber" /></th>
									<td style="width: 15%"><s:property value="%{#info.AccountNo}"/></td>
								</tr>
								<tr style="width: 100%">
									<th style="width: 15%"><s:text name="label.branch" /></th>
									<td style="width: 15%"><s:property value="%{#info.Branch}"/></td>
									<th style="width: 15%"><s:text name="label.currency" /></th>
									<td style="width: 15%"><s:property value="%{#info.Currency}"/></td>
									<th style="width: 15%"><s:text name="Branch Code" /></th>
									<td style="width: 15%"><s:property value="%{#info.BranchCode}"/></td>
								</tr>
								<tr style="width: 100%">
									<th style="width: 15%"><s:text name="label.swiftCode" /></th>
									<td style="width: 15%"><s:property value="%{#info.SwiftCode}"/></td>
								</tr>
							</table>
							<br>
							<br>
						</div>
						<br>
		
		
		<s:if test='insIntialAmount != null && !"".equals(insIntialAmount)'>
			<div class="textfield33V">
				<div class="textV"><s:text name="Payment Amount" /></div>
				<div class="tboxV" >
					<span style="color:green;"><s:property value="insIntialAmount"/></span>
					<script type="text/javascript">
					fnSetInsAmount('<s:property value="insIntialAmount"/>');
					function fnSetInsAmount(val){
						if(val != '' || val == null){
							$('#chequeAmount').val('<s:property value="insIntialAmount"/>');
							$('#cashAmount').val('<s:property value="insIntialAmount"/>');
						}
					}
					</script>
				</div>
			</div>
		</s:if>
	</div>
</s:elseif>
<s:elseif  test="#request.ELEMENT_NAME=='homepolicyEndList'">
	<div class="input-group-prepend">
        <span class="input-group-text border border-right-0">
        	<i class="fas fa-calendar-alt"></i>
        </span>
    </div>
    <s:select name="expiryDt" id="homeexpiryDt" list="policyEndList" listKey="CODE" listValue="CODEDESC" cssClass="form-control border border-left-0 "  disabled="#disable" theme="simple"/>
</s:elseif>
<s:elseif  test='#request.ELEMENT_NAME=="comparisionDetailsAjax"'>
	<s:include value="/pages/motor/premiumComparision.jsp"/>
</s:elseif>
<s:elseif test='#request.ELEMENT_NAME=="installmentCalcAjax"'>
	<div class="modal-content" >
		<div class="modal-header">
			<h4 class="modal-title" align="center">Installment Details</h4>
			<div class="row" align="center">
				<SPAN id="modalErrorSpan" style="color: red;">
				</SPAN>
			</div>
		</div>
		<div class="modal-body">
			<s:if test='"Y".equals(installmentErrorStatus)'>
				<s:set var="sinstallmentDetails" value="%{installmentDetailsList}"/>
				<div class="row">
					<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
						<div class="text"> <s:text name="payment.quoteNo"></s:text> </div>
						<div class="tbox"> <s:property value="#sinstallmentDetails[0].QUOTE_NO"/> </div>
					</div>
					<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
						<div class="text"> <s:text name="payment.totalPremium"></s:text> </div>
						<div class="tbox"> <s:property value="#sinstallmentDetails[0].OVERALL_PREMIUM"/> </div>
					</div>
					<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
						<div class="text"> <s:text name="payment.noOfInstallment"></s:text> </div>
						<div class="tbox"> <s:property value="#sinstallmentDetails[0].NO_OF_TERMS"/> </div>
					</div>
				</div>
				<div class="row">
					<table class="footable" width="100%">
						<thead>
							<tr>
								<th>S.No.</th>
								<th>Description</th>
								<th>Date</th>
								<th>Amount</th>
								<th>Currency</th>
							</tr>
						</thead>
						<tbody>
							<s:iterator value="#sinstallmentDetails" var="idVar" status="idStatus">
								<tr>
									<td align="center"> <s:property value="#idStatus.count"/> </td>
									<td align="left"> <s:property value="#idVar.DESCRIPTION"/> </td>
									<td align="center"> <s:property value="#idVar.PREMIUM_DATE_VAL"/> </td>
									<td align="right"> <s:property value="#idVar.PREMIUM_AMOUNT"/> </td>
									<td align="center"> <s:property value="#idVar.CURRENCY_TYPE"/> </td>
								</tr>
							</s:iterator>
							
						</tbody>
					</table>
				</div>
				<br class="clear" />
				<s:checkbox name="installmentAgreeYN" id="installmentAgreeYN" fieldValue="Y"/> &nbsp; I agree for the above installment Plan.
			</s:if>
			<s:else>
				<s:label value="%{installmentErrorStatus}" cssStyle="color : red;"/>
			</s:else>
		</div>
		<div class="modal-footer">
			<s:if test='"Y".equals(installmentErrorStatus)'>
				<button type="button" class="btn btn-sm btn-danger" onclick="togglePayment('INSTALLMENT_CANCEL');">Cancel</button>
				<button type="button" class="btn btn-sm btn-success" onclick="togglePayment('INSTALLMENT_SUBMIT');">Submit</button>
			</s:if>
			<s:else>
				<button type="button" class="btn btn-sm btn-danger" onclick="togglePayment('INSTALLMENT_CANCEL');">Close</button>
			</s:else>
		</div>
	</div>
</s:elseif>
<s:elseif test='#request.ELEMENT_NAME=="installmentCalcAjaxNew"'>
	<div class="modal-content" >
		<div class="modal-header">
			<h4 class="modal-title" align="center">Installment Details</h4>
			<div class="row" align="center">
				<SPAN id="modalErrorSpan" style="color: red;">
				</SPAN>
			</div>
		</div>
		<div class="modal-body">
			<s:if test='"Y".equals(installmentErrorStatus)'>
				<s:set var="sinstallmentDetails" value="%{installmentDetailsList}"/>
				<div class="row">
					<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
						<div class="text"> <s:text name="payment.quoteNo"></s:text> </div>
						<div class="tbox"> <s:property value="#sinstallmentDetails[0].QUOTE_NO"/> </div>
					</div>
					<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
						<div class="text"> <s:text name="payment.totalPremium"></s:text> </div>
						<div class="tbox"> <s:property value="#sinstallmentDetails[0].OVERALL_PREMIUM"/> </div>
					</div>
					<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
						<div class="text"> <s:text name="payment.noOfInstallment"></s:text> </div>
						<div class="tbox"> <s:property value="#sinstallmentDetails[0].NO_OF_TERMS"/> </div>
					</div>
				</div>
				<div class="row">
					<table class="footable" width="100%">
						<thead>
							<tr>
								<th>S.No.</th>
								<th>Description</th>
								<th>Date</th>
								<th>Amount</th>
								<th>Currency</th>
							</tr>
						</thead>
						<tbody>
							<s:iterator value="#sinstallmentDetails" var="idVar" status="idStatus">
								<tr>
									<td align="center"> <s:property value="#idStatus.count"/> </td>
									<td align="left"> <s:property value="#idVar.DESCRIPTION"/> </td>
									<td align="center"> <s:property value="#idVar.PREMIUM_DATE_VAL"/> </td>
									<td align="right"> <s:property value="#idVar.PREMIUM_AMOUNT"/> </td>
									<td align="center"> <s:property value="#idVar.CURRENCY_TYPE"/> </td>
								</tr>
							</s:iterator>
							
						</tbody>
					</table>
				</div>
				<br class="clear" />
				<s:checkbox name="installmentAgreeYN" id="installmentAgreeYN" fieldValue="Y"/> &nbsp; I agree for the above installment Plan.
			</s:if>
			<s:else>
				<s:label value="%{installmentErrorStatus}" cssStyle="color : red;"/>
			</s:else>
		</div>
		<div class="modal-footer">
			<s:if test='"Y".equals(installmentErrorStatus)'>
				<button type="button" class="btn btn-sm btn-danger" onclick="toggleInstallment('INSTALLMENT_CANCEL');">Cancel</button>
				<button type="button" class="btn btn-sm btn-success" onclick="toggleInstallment('INSTALLMENT_SUBMIT');">Submit</button>
			</s:if>
			<s:else>
				<button type="button" class="btn btn-sm btn-danger" onclick="toggleInstallment('INSTALLMENT_CANCEL');">Close</button>
			</s:else>
		</div>
	</div>
</s:elseif>
<s:elseif test='#request.ELEMENT_NAME=="premiumSubUploadAjax"'>
	<s:include value="/pages/home/premiumSubList.jsp" />
</s:elseif>
<s:elseif test='#request.ELEMENT_NAME=="PM"'>
	<s:select name="paymentVal" id="paymentVal" list="paymentList"  listKey="CODE" listValue="CODEDESC" headerKey="" label=""  cssClass="form-control" />
</s:elseif>
<s:elseif test='#request.ELEMENT_NAME=="CT"'>
	<s:select name="coverTypeVal" id="coverTypeVal" list="coverTypeList"  listKey="POLICY_SUBTYPE_ID" listValue="POLICYTYPE_DESC_ENGLISH" headerKey="" label="" cssClass="form-control" />
</s:elseif>
<s:elseif test='#request.ELEMENT_NAME=="IR" || #request.ELEMENT_NAME=="AG"'>
	<s:if test='#request.ELEMENT_NAME=="IR"' >
		<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
			<div class="">
				<label><s:text name="Choose Status" /></label>
				<s:select name="status" id="status" list="#{'ALL':'All','Y':'Success','N':'Pending'}" headerKey="" label="" cssClass="form-control" />
			</div>
		</div>
	</s:if>
	<s:if test='#request.ELEMENT_NAME=="AGI"' >
		<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
			<div class="">
				<label><s:text name="Choose Status" /></label>
				<s:select name="status" id="status" list="#{'ALL':'All','30':'30 Days','60':'60 Days','90':'90 Days','120':'120 Days'}" headerKey="" label="" cssClass="inputBoxS " />
			</div>
		</div>
	</s:if>
		<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
			<div class="">
				<label><s:text name="Payment Method" /></label>
				<s:select name="paymentVal" id="paymentVal" list="paymentList"  listKey="CODE" listValue="CODEDESC" headerKey="ALL" headerValue="All" label=""  cssClass="form-control" />
			</div>
		</div>
</s:elseif>
<s:elseif test='#request.ELEMENT_NAME=="CC" || #request.ELEMENT_NAME=="SS" || #request.ELEMENT_NAME=="UW" '>
	<div class="col-xs-12 col-sm-12 col-md-3 col-lg-3" >
		<div class="">
			<label><s:text name="User Id" /></label>
			<s:select name="approveVal" id="approveVal" list="approverIdList"  listKey="CODE" listValue="CODEDESC" headerKey="ALL" headerValue="All" label="" cssClass="form-control" />
		</div>
	</div>
</s:elseif>
<s:elseif test='#request.ELEMENT_NAME=="ET"'>
	<s:select name="endorsementTypeVal" id="endorsementTypeVal" list="endorsementTypeList"  listKey="ENDT_TYPE_ID" listValue="ENDT_TYPE" headerKey="" label="" cssClass="form-control" />
</s:elseif>
<s:elseif test='#request.ELEMENT_NAME=="VU"'>
	<s:select name="vehicleUsageVal" id="vehicleUsageVal" list="vehicleUsageList"  listKey="CODE" listValue="CODEDESC" headerKey="" label="" cssClass="form-control" />
</s:elseif>
<s:elseif test='#request.ELEMENT_NAME=="CI"'>
	<s:select name="cityVal" id="cityVal" list="cityList"  listKey="city_name" listValue="city_name" headerKey="" label="" cssClass="form-control" />
</s:elseif>
<s:elseif test='#request.ELEMENT_NAME=="ST"'>
	<s:if test="reportType == 'INSTALMENTPAYMENT'">
		<s:select name="status" id="status" list="#{'Y':'Success','':'Pending'}" headerKey="" label="" cssClass="form-control" />
	</s:if>
	<s:elseif test="reportType == 'CCAPRQ'">
		<s:select name="status" id="status" list="#{'SP':'Approved','CCP':'Pending','CCR':'Rejected'}" headerKey="" label="" cssClass="form-control" />
	</s:elseif>
	<s:elseif test="reportType == 'INSTALMENTPAYMENT'">
		<s:select name="status" id="status" list="#{'Y':'Success','':'Pending'}" headerKey="" label="" cssClass="form-control" />
	</s:elseif>
</s:elseif>

<s:elseif test='#request.ELEMENT_NAME=="extendedCoverageAjax"'>
	<s:include value="/pages/home/homeCoverageDetails.jsp" />
</s:elseif>
<s:elseif  test="#request.ELEMENT_NAME=='homeLossOfLimbs'">
	<s:select name="SUM_INSURED[19]" id="text[105]" list="lossLimbsList" listKey="LISTKEY" listValue="LISTVALUE" headerKey="" headerValue="---None---" value="%{lossOfLimbs}" cssClass="form-control " data-content="%{REMARKS}" disabled="true"/>
</s:elseif>
<s:elseif  test="#request.ELEMENT_NAME=='homeLocationList'">
	<s:set var="dropDownLocationListVar" value="dropDownLocationList"/>
	<s:iterator var="ddllv" value="#dropDownLocationListVar" status="stat">
		<li class="nav-item">
	        <a class='nav-link <s:if test="(dropDownLocation).equalsIgnoreCase(#ddllv.CODE)">active</s:if>' data-toggle="tab" style="cursor:pointer;" onclick="coverageDtls('','<s:property value="#ddllv.CODE"/>','','');"><s:property value="#ddllv.CODEDESC" /></a>
	    </li>
    </s:iterator>
    <s:hidden name="dropDownLocation" id="dropDownLocation"/>
</s:elseif>
<s:elseif  test="#request.ELEMENT_NAME=='homeCoverageDetails'">
	<s:include value="/pages/home/coveragePremiumInfoAjax.jsp" />
</s:elseif>
<s:elseif  test="#request.ELEMENT_NAME=='vehicleEditAjax'">
   <s:include value="/pages/motor/motorVehicleAjax.jsp" />
       
</s:elseif>
<s:elseif  test="#request.ELEMENT_NAME=='driverEditAjax'">
	<s:include value="/pages/motor/motorDriverAjax.jsp" />
	       
</s:elseif>

<s:elseif  test="#request.ELEMENT_NAME=='bankInfoAjxNew'">
		<s:set var="info" value="bankInformAjax" ></s:set>	
		<div class="row">
			<div class="col-md-4 col-4">
			    <label class="LabelHeading"><s:text name="label.accountName" /></label>
			
			</div>
			<div class="col-md-8 col-8">
			    <label class="labelValues"><s:property value="%{#info.ACCOUNT_HOLDER}"/></label>
			    </div>
			</div>
			<div class="row">
			    <div class="col-md-4 col-4">
			        <label class="LabelHeading"><s:text name="label.bank" /></label>
			
			</div>
			<div class="col-md-8 col-8">
			    <label class="labelValues"><s:property value="%{#info.BANK_NAME}"/></label>
			    </div>
			</div>
			<div class="row">
			    <div class="col-md-4 col-4">
			        <label class="LabelHeading"><s:text name="label.accountNumber" /></label>
			
			</div>
			<div class="col-md-8 col-8">
			    <label class="labelValues"><s:property value="%{#info.ACCOUNT_NUMBER}"/></label>
			    </div>
			</div>
			<div class="row">
			    <div class="col-md-4 col-4">
			        <label class="LabelHeading"><s:text name="label.branch" /></label>
			
			</div>
			<div class="col-md-8 col-8">
			    <label class="labelValues"><s:property value="%{#info.BRANCH_NAME}"/></label>
			    </div>
			</div>
			 <div class="row">
			    <div class="col-md-4 col-4">
			        <label class="LabelHeading"><s:text name="label.currency" /></label>
			
			</div>
			<div class="col-md-8 col-8">
			    <label class="labelValues"><s:property value="%{#info.CURRENCY_TYPE}"/></label>
			    </div>
			</div>
			 <div class="row">
			    <div class="col-md-4 col-4">
			        <label class="LabelHeading"><s:text name="Branch Code" /></label>
			
			</div>
			<div class="col-md-8 col-8">
			    <label class="labelValues"><s:property value="%{#info.BRANCH_CODE}"/></label>
			    </div>
			</div>
			<div class="row">
			    <div class="col-md-4 col-4">
			        <label class="LabelHeading"><s:text name="label.swiftCode" /></label>
			
			</div>
			<div class="col-md-8 col-8">
			    <label class="labelValues"><s:property value="%{#info.SWIFT_CODE}"/></label>
			    </div>
			</div>
			<s:if test='insIntialAmount != null && !"".equals(insIntialAmount)'>
				<div class="row">
					<div class="col-md-4 col-4">
						<label class="LabelHeading"><s:text name="Payment Amount" /></label>
					</div>
					<div class="col-md-8 col-8">
						<label class="labelValues">
							<span style="color:green;"><s:property value="insIntialAmount"/></span>
							<script type="text/javascript">
							fnSetInsAmount('<s:property value="insIntialAmount"/>');
							function fnSetInsAmount(val){
								if(val != '' || val == null){
									$('#chequeAmount').val('<s:property value="insIntialAmount"/>');
									$('#cashAmount').val('<s:property value="insIntialAmount"/>');
								}
							}
							</script>
						</label>
					</div>
				</div>
				<%-- <div class="textfield33V">
					<div class="textV"><s:text name="Payment Amount" /></div>
					<div class="tboxV" >
						<span style="color:green;"><s:property value="insIntialAmount"/></span>
						<script type="text/javascript">
						fnSetInsAmount('<s:property value="insIntialAmount"/>');
						function fnSetInsAmount(val){
							if(val != '' || val == null){
								$('#chequeAmount').val('<s:property value="insIntialAmount"/>');
								$('#cashAmount').val('<s:property value="insIntialAmount"/>');
							}
						}
						</script>
					</div>
				</div> --%>
			</s:if>
</s:elseif>
<s:elseif  test="#request.ELEMENT_NAME=='viewQuoteAjax'">
	<s:include value="/pages/motor/QuoteViewNew.jsp" />
</s:elseif>
<s:elseif  test="#request.ELEMENT_NAME=='attachDocumentAjax'">
	<s:include value="/pages/documentUploadNew.jsp" />
</s:elseif>
<s:elseif  test="#request.ELEMENT_NAME=='openLocationDetails'">
		<div class="modal-content">
	      <div class="modal-header">
	       	<div class="modal-title">
				<div class="row">
	      			<div class="col-md-12 col-xs-12" align="left">
			         	<h3><s:if test='"add".equalsIgnoreCase(mode)'>Add</s:if><s:elseif test='"edit".equalsIgnoreCase(mode)'>Edit</s:elseif> Location</h3>
			        </div>
	   			</div>
			</div>
	      </div>
	      <div class="modal-body" >
	      	<s:form name="locationDetailsForm" id="locationDetailsForm" action="" theme="simple">
	      		<div class="row">
		        	<s:actionerror style="color:red"/>
	      		</div>
		        <div class="row">
	                 <div class="col-md-5 col-6">
	                     <label class="LabelHeading">Location Name</label>
	                     <div class="input-group mb-3">
	                         <div class="input-group-prepend">
	                             <span class="input-group-text border border-right-0">
	                             	<i class="fas fa-user-check"></i>
	                             </span>
	                         </div>
	                         <s:textfield name="locationDesc" id="locationDesc" onkeypress="ensureForm(event);" cssClass="form-control border border-left-0" maxLength="100" disabled="#disable1"/>
	                     </div>
	                 </div>
	                 <div class="col-md-5 col-6">
	                     <label class="LabelHeading">Status</label>
	                     <div class="input-group mb-3">
	                         <s:radio list="#{'Y':'Active','N':'Deactive'}" name="locStatus" id="locStatus" value='%{locStatus==null?"Y":locStatus}' onkeypress="ensureForm(event);"/>
	                     </div>
	                 </div>
	             </div>
				    <div class="row mt-4">
				        <div class="col-md-2 col-2 col-sm-2 offset-md-4">
				            <a style="cursor:pointer" data-dismiss="modal" class="btn btn-danger btn-block" onkeypress="ensureForm(event);">Cancel</a>
				        </div>
				        <div class="col-md-2 col-2 col-sm-2" id="locDtlsSubmitBtnId">
				            <a style="cursor:pointer" onclick="hideBtn('locDtlsSubmitBtnId');manipulateLoc();" class="btn btn-primary btn-block" onkeypress="ensureForm(event);">Submit</a>
				        </div>
				    </div>
        		 <s:hidden name="applicationNo" id="applicationNo"/>
	             <s:hidden name="editLocId" id="editLocId"/>
	             <s:hidden name="mode" id="mode"/>
	             <s:hidden name="locationDtlsStatus" id="locationDtlsStatus"/>
	        </s:form>
	      </div>
	    </div>
</s:elseif>
<s:elseif  test="#request.ELEMENT_NAME=='custEditAjax'">
	<div class="row" align="left" style="padding-left: 80px;">
		<s:if test="hasActionErrors()">
			<span style="color:red;">
				<s:actionerror/>
			</span>
		</s:if>
	</div>
	<div class="Card_Parent mt-4">
        <div class="card">
            <div class="row">
                <div class="col-md-12">
                    <div class="row">
                        <div class="col-md-4">
                        	<label class="labelHeader"><s:text name="customer.title"  /><font color="red">*</font></label>
                            <div class="input-group mb-3">
                                <div class="input-group-prepend">
                                    <span class="input-group-text border border-right-0">
                                        <i class="fas fa-heading"></i>
                                    </span>
                                </div>
                                <!-- <input type="text" class="form-control border border-left-0" placeholder="Title"> -->
								<s:select name="title" id="title" list="titleList" listKey="CODEDESC" listValue="CODEDESC" class="form-control border "  disabled="#disable1"/>
				
                            </div>
                        </div>
                        <div class="col-md-4">
                        	<label class="labelHeader"><s:text name="customer.firstName"  /><font color="red">*</font></label>
                            <div class="input-group mb-3">
                                <div class="input-group-prepend">
                                    <span class="input-group-text border border-right-0">
                                        <i class="fas fa-user-check"></i>
                                    </span>
                                </div>
                                <!-- <input type="text" class="form-control border border-left-0" placeholder="Firstname"> -->
								<s:textfield name="customerName" id="customerName" class="form-control border empyCustDetails"  maxLength="100" disabled="#disable1" />
                            </div>
                        </div>
                        <div class="col-md-4">
                        	<label class="labelHeader"><s:text name="customer.lastName"/><font color="red">*</font></label>
                            <div class="input-group mb-3">
                                <div class="input-group-prepend">
                                    <span class="input-group-text border border-right-0"><i class="fas fa-user-check"></i></span>
                                </div>
                                <!-- <input type="text" class="form-control border border-left-0" placeholder="Lastname"> -->
                                <s:textfield name="custLastName" id="custLastName" class="form-control border empyCustDetails" maxLength="20" disabled="#endtDisable"  />
                            </div>
                        </div>
                    </div>
                    <div class="row">
                    	<div class="col-md-4">
                        	<label class="labelHeader"><s:text name="customer.dob" /><s:text name="(DD/MM/YYYY)" /><font color="red">*</font></label>
                            <div class="input-group mb-3">
                                <div class="input-group-prepend">
                                    <span class="input-group-text border border-right-0"><i
                                            class="fas fa-calendar-alt"></i></span>
                                </div>
                                <!-- <input type="text" class="form-control border border-left-0" placeholder="DOB DD/MM/YYYY"> -->
                                <s:textfield name="custdob" id="custdob" class="form-control border datePicker L empyCustDetails"  disabled="#endtDisable"/>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="row Genders mt-1">
                                <div class="col-md-12 col-12">
                                    <label for="gender" class="labelHeader"><s:text name="customer.gender"/><font color="red">*</font></label>
                                </div>
                                <div class="col-md-12 col-12">
                                     <s:radio name="gender" id="gender" list="#{'M':'Male','F':'Female'}" />
                                </div>
                            </div>
                        </div>
                        <div class="col-md-4">
                        	<label class="labelHeader"><s:text name="customer.occupation"/></label>
                            <div class="input-group mb-3">
                                <div class="input-group-prepend">
                                    <span class="input-group-text border border-right-0"><img
                                            src="./assets/Images/employee.png"></span>
                                </div>
                                <s:textfield name="occupation" maxlength="30" class="form-control border L empyCustDetails" disabled="#endtDisable" />
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-4">
                        	<label class="labelHeader"><s:text name="customer.address1"/><font color="red">*</font></label>
                            <div class="input-group mb-3">
                                <div class="input-group-prepend">
                                    <span class="input-group-text border border-right-0">
                                        <i class="fas fa-address-card"></i>
                                    </span>
                                </div>
                                <s:textfield name="address1" id="address1" class="form-control border  empyCustDetails"  maxLength="200" disabled="#endtDisable"/>
                            </div>
                        </div>
                        <div class="col-md-4">
                        	<label class="labelHeader"><s:text name="customer.address2"/></label>
                            <div class="input-group mb-3">
                                <div class="input-group-prepend">
                                    <span class="input-group-text border border-right-0">
                                        <i class="fas fa-address-card"></i>
                                    </span>
                                </div>
                                <s:textfield name="address2" id="address2" class="form-control border empyCustDetails" maxlength="200" disabled="#endtDisable"/>
                            </div>
                        </div>
                        <div class="col-md-4">
                        	<label class="labelHeader"><s:text name="customer.city"  /><font color="red">*</font></label>
                            <div class="input-group mb-3">
                                <div class="input-group-prepend">
                                    <span class="input-group-text border border-right-0">
                                        <i class="fas fa-address-card"></i>
                                    </span>
                                </div>
                                <s:select name="city" id="city" list="cityList" headerKey="" headerValue="---Select City---" listKey="CODEDESC" listValue="CODEDESC" class="form-control empyCustDetails"  disabled="#endtDisable"/>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        
                        <div class="col-md-4">
                        	<label class="labelHeader"><s:text name="customer.poBox"  /></label>
                            <div class="input-group mb-3">
                                <div class="input-group-prepend">
                                    <span class="input-group-text border border-right-0">
                                        <i class="fas fa-envelope"></i>
                                    </span>
                                </div>
                                <s:textfield name="poBox" id="poBox" class="form-control border empyCustDetails"  maxLength="6" disabled="#endtDisable"/>
                            </div>
                        </div>
                        <div class="col-md-4">
                        	<label class="labelHeader"><s:text name="customer.mobileNo1"  /><font color="red">*</font></label>
                            <div class="input-group mb-3">
                                <div class="input-group-prepend">
                                    <span class="input-group-text border border-right-0">
                                        <i class="fas fa-address-card"></i>
                                    </span>
                                </div>
                                <s:textfield name="mobileNo" id="mobileNo" class="form-control border empyCustDetails"  maxLength="10" onkeyup="checkNumbers(this);" disabled="#disable1"/>
                            </div>
                        </div>
                        <div class="col-md-4">
                        	<label class="labelHeader"><s:text name="customer.alterMobile"  /></label>
                            <div class="input-group mb-3">
                                <div class="input-group-prepend">
                                    <span class="input-group-text border border-right-0">
                                        <i class="fas fa-envelope"></i>
                                    </span>
                                </div>
                                <s:textfield name="custAlterMobileNo" id="custAlterMobileNo" class="form-control border empyCustDetails"  maxLength="10" onkeyup="checkNumbers(this);" disabled="#endtDisable"/>
                            </div>
                        </div>
                    </div>
                   
                    <div class="row">
                        <div class="col-md-4">
                        	<label class="labelHeader"><s:text name="customer.email"  /><font color="red">*</font></label>
                            <div class="input-group mb-3">
                                <div class="input-group-prepend">
                                    <span class="input-group-text border border-right-0">
                                        <i class="fas fa-envelope"></i>
                                    </span>
                                </div>
                                <s:textfield name="email" id="email" class="form-control border empyCustDetails" maxLength="100" disabled="#endtDisable"/>
                            </div>
                        </div>
                        <div class="col-md-8">
                        	<label class="labelHeader"><s:text name="customer.nrc"/><font color="red">*</font></label>
                            <div class="input-group mb-3">
                                <div class="input-group-prepend">
                                    <span class="input-group-text border border-right-0">
                                        <i class="fas fa-id-card"></i>
                                    </span>
                                </div>
                                <s:textfield name="custnrc1" id="custnrc1" class="form-control border  empyCustDetails" cssStyle="width:30%" maxLength="6" size="6" onkeyup="checkDecimals6_0(this);autotab(this, this.form.custnrc2);" disabled="#endtDisable" />&nbsp;/&nbsp;
							<s:textfield name="custnrc2" id="custnrc2" class="form-control border  empyCustDetails" cssStyle="width:25%" maxLength="2" size="2" onkeyup="checkDecimals6_0(this);autotab(this, this.form.custnrc3)" disabled="#endtDisable"/>&nbsp;/&nbsp;
							<s:textfield name="custnrc3" id="custnrc3" class="form-control border  empyCustDetails" cssStyle="width:15%" maxLength="1" size="1" onkeyup="checkDecimals6_0(this);" disabled="#endtDisable"/>
                            
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-4">
                        	<label class="labelHeader"><s:text name="customer.passportNo"/></label>
                            <div class="input-group mb-3">
                                <div class="input-group-prepend">
                                    <span class="input-group-text border border-right-0"><i
                                            class="fas fa-passport"></i></span>
                                </div>
                                <!-- <input type="text" class="form-control border border-left-0" placeholder="Passport No"> -->
                                <s:textfield name="custPassportNo" id="custPassportNo" class="form-control border empyCustDetails"  maxLength="10" disabled="#endtDisable"/>
                            </div>
                        </div>
                        
                        <div class="col-md-4">
                            <div class="row Genders mt-1">
                                <div class="col-md-12 col-12">
                                    <label for="customerType" class="labelHeader"><s:text name="customer.customerType"/><font color="red">*</font></label>
                                </div>
                                <div class="col-md-12 col-12">
                                    <s:radio name="customerType" id="customerType" list="#{'INDIVIDUAL':'Individual','CORPORATE':'Corporate'}" value='%{(customerType==null||"".equals(customerType))?"INDIVIDUAL":customerType}' cssClass="input "  onclick="toggleCompanyRegistration(this.value);"/>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-4">
                        	<label class="labelHeader"><s:text name="customer.companyRegNo"  /><font color="red">*</font></label>
                            <div class="input-group mb-3">
                                <div class="input-group-prepend">
                                    <span class="input-group-text border border-right-0"><i class="fas fa-passport"></i></span>
                                </div>
                                <s:textfield name="companyRegNo" id="companyRegNo" class="form-control border empyCustDetails"  maxLength="10" disabled='%{(customerType==null||"".equals(customerType)||#disable1||"INDIVIDUAL".equals(customerType))?"true":"false"}'/>
                            </div>
                        </div>
                    </div>
                   
                </div>
            </div>
        </div>
    </div>
    <div class="modal-footer">
		<button type="button" id="save" class="btn btn-danger btn-block col-md-3" onclick="disableForm(this.form,false,'');return fnUpdCustomer('<s:property value="customerId" />')">Save</button>
    </div>
    <script>
    $(function() {
   		try {

   			var dt = new Date();
   			dt.setFullYear(new Date().getFullYear()-18);
   			
   			$('#custdob').datepicker({
   				todayHighlight: true,
   	        	format: "dd/mm/yyyy",
   			  	viewMode: "years",
   			  	endDate: dt
   			}).on('changeDate', function(e){
   	            $(this).datepicker('hide');
   	        });
   			
   			
   			$('.datePicker').datepicker({
   				todayHighlight: true,
   				format: "dd/mm/yyyy"
   			}).on('changeDate', function(e){
   			    $(this).datepicker('hide');			    
   			});
   		} catch(err) {console.error(err);}
   		});
    try{
        <s:if test="hasActionMessages() && 'custUpd'.equalsIgnoreCase(mode)">
        $("#custModal").modal('hide');
        document.custList.mode.value='';
        var issuer='<s:property value="issuer"/>'
        //alert(issuer);
        if(issuer==null || issuer==''){
        	document.custList.action = "${pageContext.request.contextPath}/editQuoteCustDataCustomer.action";
        }
        else{
        	document.custList.action = "${pageContext.request.contextPath}/issuerCustListCustomer.action";
        }
        		
    	document.custList.submit();
        </s:if>
    }catch(err){
        console.log(err);
    }
    </script>
</s:elseif>
<s:elseif test="#request.ELEMENT_NAME=='clausesEditAjax'">
	<s:include value="/pages/motor/clausesDetails.jsp" />
</s:elseif>
<s:elseif test="#request.ELEMENT_NAME=='deductibleEditAjax'">
	<s:include value="/pages/motor/deductibleDetails.jsp" />
</s:elseif>
<s:elseif test="#request.ELEMENT_NAME=='vehicleEditAjaxError'">
	<div class="modal-header">
      	<h5 class="modal-title">Erorr Details</h5>
      	<i class="far fa-times-circle mt-2" data-dismiss="modal"></i>
    </div>
    <div class="modal-body">
		<s:if test="hasActionErrors()">
			<span style="color:red;">
				<s:actionerror/>
			</span>
		</s:if>
	</div>
</s:elseif>

<s:elseif  test="#request.ELEMENT_NAME=='editRate'">
<div class="modal-header">
      	<h5 class="modal-title">Rate Detail</h5>
      	<i class="far fa-times-circle mt-2" data-dismiss="modal"></i>
    </div>
    <div class="modal-body">
     	<div class="row" align="left" style="padding-left: 80px;">
			<s:if test="hasActionErrors()">
				<span style="color:red;">
					<s:actionerror/>
				</span>
			</s:if>
			<%-- <s:if test="hasActionMessages()">
				<span style="color:green;">
					<s:actionmessage/> 
				</span>
			</s:if> --%>
		</div>
		<div class="row mt-2">
         <div class="col-md-5 mt-2 modalTextFielRemove offset-md-1">
         	<label class="labelHeader"><s:text name="Enter New Rate " /><font color="red">*</font></label>
			<s:textfield name="rate" id="rate" class="form-control"  onkeyup="checkNumbers(this);" maxLength="20" />
         </div>
      </div>
	</div>
	<div class="modal-footer" style="justify-content: center;">
		<button type="button" class="btn btn-danger btn-block col-md-3 " data-dismiss="modal">Close</button>
	  	<button type="button" id="save" class="btn btn-success btn-block col-md-3" onclick="updateRate('<s:property value="factorRateId"/>');">Save</button>
     </div>
     <script>
     
      try{
	      <s:if test="hasActionMessages()">
	      //alert();
	        $("#myModal").modal('hide');
	        document.rateConfig.mode.value='rateList';
	  		document.rateConfig.action='rateListMotorAdminNew.action';
	        document.rateConfig.submit();
	     
	      </s:if>
       }catch(err){
    	   console.log(err);
       }
      </script>
</s:elseif>