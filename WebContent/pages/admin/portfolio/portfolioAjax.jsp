<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/WEB-INF/displaytag.tld" prefix="display"%>
<s:if test='"portfolioLists".equals(reqFrom)'>
	<div id="portfolioLists">
	   	<table class="table table-bordered table-hover" cellspacing="0" width="100%" id="tadaTable">
			<thead class="bluecolortable">
			<tr >
				<th align="center"> <s:text name="S.No." /> </th>
				<th align="center"> <s:text name="DATE" /> </th>
				<th align="center"> <s:text name="NUMBER OF QUOTES" /> </th>
			</tr>
			</thead>
			<tbody class="rowevencolor">
			<s:iterator value="portfolioList" status="stat" var="record">
			<tr>
				<td align="center"><s:property value="%{#stat.index+1}" /></td>
				<td align="center">
					<s:if test='"A".equals(rep)'>
						<s:property value="ENTRY_DATE" />
					</s:if>
					<s:else>
						<a href="#" type="buttton" class="btn btn-sm btn-info" onclick="getViewLists('${record.ENTRY_DATE}','portfolioLists','<s:property value="rep"/>','<s:property value="productID"/>')" > <s:property value="ENTRY_DATE" /> </a>
					</s:else>
				</td>
				<td align="center"> <s:property value="COU" /> </td>
			</tr>
			</s:iterator>
			</tbody>
		</table>
		<s:if test='"A".equals(rep)'>
			<br class="clear" />
			<div align="center">
				<input type="button" class="btn btn-sm btn-success" value="Save" onclick="pendingExcel();"/>
			</div>
		</s:if>	
	</div>
</s:if>
<s:elseif test='"viewLists".equals(reqFrom)'>
<div id="viewLists">
   <b>Selected Date :</b><s:property value="viewdate"/>
	<s:hidden name="viewdate"  /> <br class="clear" />
	<table class="table table-bordered table-hover portfolioTable" id="tadaTable" cellspacing="0" width="100%" >
		<thead class="bluecolortable">
		<tr>
			<th> <s:text name="S.No." /> </th>
			<s:if test='"c".equals(rep)'>
				<th> <s:text name="BROKER ORAGANIZATION" /> </th>
				<th> <s:text name="QUOTE CREATED BY" /> </th>
				<th> <s:text name="CUSTOMER NAME" /> </th>
				<th> <s:text name="QUOTE NO" /> </th>
				<th> <s:text name="POLICY START DATE" /> </th>
				<th> <s:text name="POLICY END DATE" /> </th>
				<th> <s:text name="POLICY TYPE" /> </th>
				<th> <s:text name="PREMIUM" /> </th>
				<th> <s:text name="VIEW DRAFT" /> </th>
			</s:if>
			<s:elseif test='"p".equals(rep)'>
				<th> <s:text name="BROKER ORAGANIZATION" /> </th>
				<th> <s:text name="QUOTE CREATED BY" /> </th>
				<th> <s:text name="CUSTOMER NAME" /> </th>
				<th> <s:text name="POLICY NO" /> </th>
				<th> <s:text name="POLICY START DATE" /> </th>
				<th> <s:text name="POLICY END DATE" /> </th>
				<th> <s:text name="POLICY TYPE" /> </th>
				<th> <s:text name="PAYMENT TYPE" /> </th>
				<th> <s:text name="PREMIUM" /> </th>
				<th> <s:text name="VIEW PDF" /> </th>
			</s:elseif>
			<s:elseif test='"pc".equals(rep)'>
				<th> <s:text name="BROKER ORAGANIZATION" /> </th>
				<th> <s:text name="QUOTE CREATED BY" /> </th>
				<th> <s:text name="CUSTOMER NAME" /> </th>
				<th> <s:text name="POLICY NO" /> </th>
				<th> <s:text name="POLICY START DATE" /> </th>
				<th> <s:text name="POLICY END DATE" /> </th>
				<th> <s:text name="POLICY TYPE" /> </th>
				<th> <s:text name="PAYMENT TYPE" /> </th>
				<th> <s:text name="PREMIUM" /> </th>
				<th> <s:text name="VIEW PDF" /> </th>
				<th> <s:text name="Status" /> </th>
			</s:elseif>
			<s:elseif test='"r".equals(rep)'>
				<th> <s:text name="BROKER ORAGANIZATION" /> </th>
				<th> <s:text name="QUOTE CREATED BY" /> </th>
				<th> <s:text name="CUSTOMER NAME" /> </th>
				<th> <s:text name="QUOTE NO" /> </th>
				<th> <s:text name="POLICY START DATE" /> </th>
				<th> <s:text name="POLICY END DATE" /> </th>
				<th> <s:text name="POLICY TYPE" /> </th>
				<th> <s:text name="PREMIUM" /> </th>
			</s:elseif>
			<s:elseif test='"d".equals(rep)'>
				<th> <s:text name="BROKER ORAGANIZATION" /> </th>
				<th> <s:text name="QUOTE CREATED BY" /> </th>
				<th> <s:text name="CUSTOMER NAME" /> </th>
				<th> <s:text name="QUOTE NO" /> </th>
				<th> <s:text name="POLICY NO" /> </th>
				<th> <s:text name="POLICY START DATE" /> </th>
				<th> <s:text name="POLICY END DATE" /> </th>
				<th> <s:text name="POLICY TYPE" /> </th>
				<th> <s:text name="PAYMENT TYPE" /> </th>
				<th> <s:text name="PREMIUM" /> </th>
			</s:elseif>
			<s:elseif test='"sr".equals(rep)'>
				<th> <s:text name="BROKER ORAGANIZATION" /> </th>
				<th> <s:text name="QUOTE CREATED BY" /> </th>
				<th> <s:text name="CUSTOMER NAME" /> </th>
				<th> <s:text name="QUOTE NO" /> </th>
				<th> <s:text name="POLICY NO" /> </th>
				<th> <s:text name="POLICY START DATE" /> </th>
				<th> <s:text name="POLICY END DATE" /> </th>
				<th> <s:text name="POLICY TYPE" /> </th>
				<th> <s:text name="PAYMENT TYPE" /> </th>
				<th> <s:text name="PREMIUM" /> </th>
				<th> <s:text name="VIEW PDF" /> </th>
			</s:elseif>
		</tr>
		</thead>
		<tbody class="rowevencolor">
		<s:iterator value="viewList" status="stat" var="record">
		<tr>
			<td><s:property value="%{#stat.index+1}" /></td>
			<s:if test='"c".equals(rep)'>
				<td> <s:property value="BROKER_ORGANIZATION" /> </td>
				<td> <s:property value="LOGIN_ID" /> </td>
				<td> <s:property value="FIRST_NAME" /> </td>
				<td> <s:property value="QUOTE_NO" /> </td>
				<td> <s:property value="POLICY_START_DATE" /> </td>
				<td> <s:property value="POLICY_END_DATE" /> </td>
				<td> <s:property value="POLICY_TYPE" /> </td>
				<td> <s:property value="PREMIUM" /> </td>
				<td align="center">
					<a href="#" type="button" class="btn btn-sm btn-info" onclick="viewPolicys('${record.QUOTE_NO}','${record.LOGIN_ID}','draft','${record.PRODUCT_ID}','${record.OPEN_COVER_NO}')"> <i class="fa fa-book"></i> </a>
				</td>
			</s:if>
			<s:elseif test='"p".equals(rep)'>
				<td> <s:property value="BROKER_ORGANIZATION" /> </td>
				<td> <s:property value="LOGIN_ID" /> </td>
				<td> <s:property value="FIRST_NAME" /> </td>
				<td> <s:property value="POLICY_NO" /> </td>
				<td> <s:property value="POLICY_START_DATE" /> </td>
				<td> <s:property value="POLICY_END_DATE" /> </td>
				<td> <s:property value="POLICY_TYPE" /> </td>
				<td> <s:property value="PAYMENT_TYPE" /> </td>
				<td> <s:property value="PREMIUM" /> </td>
				<td>
					<a href="#" type="button" class="btn btn-sm btn-info" onclick="viewPolicys('${record.POLICY_NO}','${record.LOGIN_ID}','schedule','${record.PRODUCT_ID}','${record.OPEN_COVER_NO}')"> <i class="fa fa-file"></i> </a>
				</td>
			</s:elseif>
			<s:elseif test='"pc".equals(rep)'>
				<td> <s:property value="BROKER_ORGANIZATION" /> </td>
				<td> <s:property value="LOGIN_ID" /> </td>
				<td> <s:property value="FIRST_NAME" /> </td>
				<td> <s:property value="POLICY_NO" /> </td>
				<td> <s:property value="POLICY_START_DATE" /> </td>
				<td> <s:property value="POLICY_END_DATE" /> </td>
				<td> <s:property value="POLICY_TYPE" /> </td>
				<td> <s:property value="PAYMENT_TYPE" /> </td>
				<td> <s:property value="PREMIUM" /> </td>
				<td>
					<a href="#" type="button" class="btn btn-sm btn-info" onclick="viewPolicys('${record.POLICY_NO}','${record.LOGIN_ID}','schedule','${record.PRODUCT_ID}','${record.OPEN_COVER_NO}')"> <i class="fa fa-eye"></i> </a>
				</td>
				<td>
					<s:hidden name="policyNo[%{#attr.record_rowNum-1}]" value="%{#myrow.POLICY_NO}" />
					<s:radio list="#{'A':'Active','D':'Deactive'}" name="status[%{#attr.record_rowNum-1}]" value="%{#myrow.POLICY_CANCEL_STATUS}"></s:radio>
				</td>
			</s:elseif>
			<s:elseif test='"r".equals(rep)'>
				<td> <s:property value="BROKER_ORGANIZATION" /> </td>
				<td> <s:property value="LOGIN_ID" /> </td>
				<td> <s:property value="FIRST_NAME" /> </td>
				<td> <s:property value="QUOTE_NO" /> </td>
				<td> <s:property value="POLICY_START_DATE" /> </td>
				<td> <s:property value="POLICY_END_DATE" /> </td>
				<td> <s:property value="POLICY_TYPE" /> </td>
				<td> <s:property value="PREMIUM" /> </td>
			</s:elseif>
			<s:elseif test='"d".equals(rep)'>
				<td> <s:property value="BROKER_ORGANIZATION" /> </td>
				<td> <s:property value="LOGIN_ID" /> </td>
				<td> <s:property value="FIRST_NAME" /> </td>
				<td> <s:property value="QUOTE_NO" /> </td>
				<td> <s:property value="POLICY_NO" /> </td>
				<td> <s:property value="POLICY_START_DATE" /> </td>
				<td> <s:property value="POLICY_END_DATE" /> </td>
				<td> <s:property value="POLICY_TYPE" /> </td>
				<td> <s:property value="PAYMENT_TYPE" /> </td>
				<td> <s:property value="PREMIUM" /> </td>
			</s:elseif>
			<s:elseif test='"sr".equals(rep)'>
				<td> <s:property value="BROKER_ORGANIZATION" /> </td>
				<td> <s:property value="LOGIN_ID" /> </td>
				<td> <s:property value="FIRST_NAME" /> </td>
				<td> <s:property value="QUOTE_NO" /> </td>
				<td> <s:property value="POLICY_NO" /> </td>
				<td> <s:property value="POLICY_START_DATE" /> </td>
				<td> <s:property value="POLICY_END_DATE" /> </td>
				<td> <s:property value="POLICY_TYPE" /> </td>
				<td> <s:property value="PAYMENT_TYPE" /> </td>
				<td> <s:property value="PREMIUM" /> </td>
				<td>
					<a href="#" type="button" class="btn btn-sm btn-info" onclick="viewPolicys('${record.POLICY_NO}','${record.LOGIN_ID}','schedule','${record.PRODUCT_ID}','${record.OPEN_COVER_NO}')"><i class="fa fa-eye"></i></a>
				</td>
			</s:elseif>
		</tr>
		</s:iterator>
		</tbody>
	</table>
<s:if test='"c".equals(rep)'><br/><s:submit align="center" value="Back" cssClass="btn btn-sm btn-danger" onclick="getBack();" /></s:if>
<s:if test='"p".equals(rep)'><br/><s:submit align="center" value="Back" cssClass="btn btn-sm btn-danger" /></s:if>
<s:if test='"pc".equals(rep)'><br/><s:submit align="center" value="Back" cssClass="btn btn-sm btn-danger" onclick="getBack();"  /><input type="button" onclick="getSubmit();" value="Submit" /></s:if>
<s:if test='"r".equals(rep)'><br/><s:submit align="center" value="Back" cssClass="btn btn-sm btn-danger" />
<!-- <a href="#" type="button" onclick="print('<s:property value="viewdate"/>')" class="btn btn-sm btn-info" > <i class="fa fa-print"></i> </a> -->
<br/></s:if>
</div>
</s:elseif>

<script type="text/javascript">

	$(document).ready(function () {
		  $('#tadaTable').DataTable({
	    	  "responsive": true,
	        "columnDefs": {
	          "orderable": false
	        },
	        language: {
	          //customize pagination prev and next buttons: use arrows instead of words
	          'paginate': {
	            'previous': '<span><i class="fas fa-chevron-circle-left"></i></span>',
	            'next': '<span><i class="fas fa-chevron-circle-right"></i></span>'
	          },
	          //customize number of elements to be displayed
	          "lengthMenu": '<select class="form-control input-sm">' +
	            '<option value="10">10</option>' +
	            '<option value="20">20</option>' +
	            '<option value="30">30</option>' +
	            '<option value="40">40</option>' +
	            '<option value="50">50</option>' +
	            '<option value="-1">All</option>' +
	            '</select>'
	        }
	      })
	    });

</script>

