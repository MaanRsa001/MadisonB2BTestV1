<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib uri="http://displaytag.sf.net" prefix="display"%>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<!-- <table id="searchResult" style="width:100%; align:center;" cellpadding="1" class="footable"> -->
<div class=" wrapper vehDtl">
<s:if test="hasActionErrors()">
	<font color="red" style="list-style:none; "><s:actionerror cssStyle="list-style:none;"/></font>
</s:if>
<s:else>
	<table class="table table-bordered table-hover" cellspacing="0" width="100%" id="tadaTable">
	<s:set name="policyList" value="%{policyList}"/>
		<thead class="bluecolortable">
			<tr>
				<th>S.No</th>
				<th>Quote No.</th>
				<th>Customer Name</th>
				<th>Quotation Date</th>
				<th>Policy Start Date</th>
				<th>Policy Number</th>
				<th>Status</th>
				<th>View/Modify</th>
			</tr>
		</thead>
		<tbody class="rowevencolor">
		<s:if test="policyList !=null && policyList.size()>0">
			<s:iterator value="policyList" var="result" status="stat">
				<tr>
					<td><s:property value="#stat.index+1"/></td>
					<td><s:property value="QUOTE_NO"/></td>
					<td><s:property value="CUSTOMER_NAME"/></td>
					<td><s:property value="QUOTATION_DATE"/></td>
					<td><s:property value="POLICY_START_DATE"/></td>
					<td><s:property value="POLICY_NO"/></td>
					<s:if test='"policyNo".equals(searchBy)'>
						<td>Portfolio</td>
					</s:if>
					<s:else>
						<td><s:property value="STATUS"/></td>
					</s:else>
					<!--<s:elseif test='"quoteNo".equals(searchBy)'>
						<td><s:property value="STATUS"/></td>
					</s:elseif>
					<s:else>
						<td><s:property value="STATUS_TYPE_NAME"/></td>
					</s:else>-->
					<td style="text-align: center;">
						<s:if test='"policyNo".equals(searchBy) || "P".equals(STATUS_TYPE)'>
							<a href="#" type="button" class="btn btn-sm btn-outline-primary" onClick="motorVehicleDetails('<s:property value="POLICY_NO"/>')"><i class="fa fa-list"></i></a>
							
						</s:if>
						<s:elseif test='"QE".equals(STATUS_TYPE) || "RA".equals(STATUS_TYPE) || "RU".equals(STATUS_TYPE)'>
	<%-- 						<a href="#" type="button" class="btn btn-sm btn-warning" onclick="editQuote('<s:property value="APPLICATION_NO"/>', '<s:property value="QUOTE_NO"/>','<s:property value="STATUS_TYPE"/>','<s:property value="CUSTOMER_ID)"/>');"><i class="glyphicon glyphicon-pencil" ></i></a> --%>
							<button type="submit" onClick="editQuote('<s:property value="APPLICATION_NO"/>', '<s:property value="QUOTE_NO"/>','<s:property value="STATUS_TYPE"/>','<s:property value="CUSTOMER_ID)"/>');" class="btn btn-sm btn-warning">
						    	<i class="fa fa-pencil"></i>
							</button>
						</s:elseif>
						<s:elseif test='"QL".equals(STATUS_TYPE)'>
							<a href="#" type="button" class="btn btn-sm btn-success" onclick="sentMail('','ACTIVE','<s:property value="QUOTE_NO"/>');"><i class="fa fa-check-circle"></i></a>
						</s:elseif>
						<s:elseif test='"L".equals(STATUS_TYPE) || "RR".equals(STATUS_TYPE)'>
							<a href="#" type="button" class="btn btn-sm btn-info" onclick="view('<s:property value="APPLICATION_NO"/>','<s:property value="QUOTE_NO"/>','<s:property value="productId"/>');"><i class="fa fa-eye"></i></a>
						</s:elseif>
						<s:elseif test='"PP".equals(STATUS_TYPE) || "R".equals(STATUS_TYPE)'>
							<a href="#" type="button" class="btn btn-sm btn-info" onclick="viewPending('<s:property value="QUOTE_NO"/>');"><i class="fa fa-eye"></i></a>
						</s:elseif>
					</td>
				</tr>
			</s:iterator>
		</s:if>
		<s:else>
		<tr class="odd">
			<td style="text-align:center;width:6%" colspan="8"><s:label key="error.search.nodata" theme="simple"/></td>
		</tr>
		</s:else>
		</tbody>
	</table>
</s:else>
</div>
<script>

$(document).ready(function () {
      $('#tadaTable').DataTable({
        "columnDefs": 
          { "orderable": false },
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