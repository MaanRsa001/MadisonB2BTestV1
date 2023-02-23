<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib uri="http://displaytag.sf.net" prefix="display"%>
<!DOCTYPE HTML>
<html>
<head>		
</head>
<body>
<div class="container wrapper vehDtl">
	<div class="Card_Parent ">
	    <div class="card card-5">
			<div class="container">
				<div class="table0">
					<div class="tablerow">
						<font color="red"><s:actionerror/></font>
					</div>
					<div class="tablerow">
						<div class="boxcontent">
							<div class="panel panel-primary">
									<div class="panel-heading">
										<div class="VehicleTableheading">
											<div class="row">
												<div class="col-md-12">
													<h3><s:text name="copyQuote.copyQuote" /></h3><hr>
												</div>
											</div>
										</div>
									</div><br class="clear" />
									<div class="panel-body">
									<s:if test="display=='init'">
										<s:form name="copyCode" theme="simple" action="searchCopyQuote.action">
											<div class="row">
												<div class="col-xs-12 col-sm-12 col-md-6 col-lg-4">
													<div class="text">
														<b><s:label key="copyQuote.searchBy" /></b>
													</div>
													<div class="tbox">
														<s:select name="searchType" id="searchType" list="#{'policyNo':'Policy No','quoteNo':'Quote No','custName':'Customer Name'}"  headerKey="" headerValue="---Select---" cssClass="form-control" />
													</div>
												</div>
												<div class="col-xs-12 col-sm-12 col-md-6 col-lg-4">
													<div class="text">
														<b><s:label key="copyQuote.enterDataForSearch"/></b>
													</div>
													<div class="tbox">
														<s:textfield name="searchValue" cssClass="form-control" onkeyup="this.value=removeSpaces(this.value);"/>
													</div>
												</div>
												<div class="col-xs-12 col-sm-12 col-md-6 col-lg-4" align="center" style="padding-top: 10px;">
													<s:submit type="button" name="Go"  key="copyQuote.go" cssClass="btn btn-sm btn-success" cssStyle="" />
												</div>
											</div>
										</s:form>
									</s:if>
									<s:elseif test="display=='search'">
									<s:form name="copyCodeSearch" theme="simple" action="copyCopyQuote.action">
									<br/>
										<div class="row">
											<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
												<s:if test="searchResult.size > 0">							
				<!-- 									<table width="100%" class="footable" id="copyQuoteTable"> -->
													<table class="table table-bordered table-hover" cellspacing="0" width="100%" id="tadaTable">
														<thead class="bluecolortable">
															<tr>
																<th><s:text name="S.No." /></th>
																<th>&nbsp;</th>
																<th><s:text name="Quote Number" /></th>
																<th><s:text name="Customer Name" /></th>
																<th><s:text name="Premium" /></th>
															</tr>
														</thead>
														<tbody class="rowevencolor">
															<s:iterator value="searchResult" status="stat" var="record">
															<tr>
																<td><s:property value="%{#stat.index+1}"/></td>
																<td><input type="radio" name="selects"onclick="getQuoteNo('${record.QUOTE_NO}')"/></td>
																<td><s:property value="QUOTE_NO"/> </td>
																<td><s:property value="FIRST_NAME" /></td>
																<td align="right"><s:property value="PREMIUM" /></td>
															</tr>
															</s:iterator>
														</tbody>
													</table>					
													<s:hidden name="searchType"/>
													<s:hidden name="searchValue"/>
												</s:if>
												<s:else>
												<div align="center" style="font-size: 20px;font-weight: bold;">
													<s:label key="copyQuote.notfoundmsg"></s:label>
												</div>
												</s:else>
											</div>
										</div>
										<div class="row">
											<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 mt-4" align="center">
												<s:submit type="button" name="close"  key="Cancel" cssClass="btn btn-sm btn-danger" onclick="backButtonAction();"/>&nbsp;&nbsp;&nbsp;
						                   		<s:if test="searchResult.size > 0">
													<s:submit type="submit" name="submit" key="Submit" cssClass="btn btn-sm btn-success"/>
												</s:if>
											</div>
										</div>
										<s:hidden name="copyQuoteValue"></s:hidden>
									</s:form>
									</s:elseif>
									<s:elseif test="display=='copyquote'">
									<s:form name="copyCode" theme="simple" action="redirectCopyQuote.action">
										<div class="row">
											<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" align="center">
												<div align="center" style="font-size: 20px;color: blue;">
													<s:property value="msg" />
												</div>
												<br class="clear"/>
												<div align="center">
													<s:submit type="submit" name="submit" key="copyQuote.proceed" cssClass="btn btn-sm btn-success"/>
												</div>
												<s:hidden name="searchCriteria"></s:hidden>
											</div>
										</div>
									</s:form>
									</s:elseif>
									<s:elseif test="display=='renew'">
										<s:form name="copyCode" theme="simple" action="redirectCopyQuote.action">
											<div class="row">
												<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" align="center">
													<div align="center" style="font-size: 20px;color: blue;">
														<s:property value="msg" />
													</div>
													<br class="clear"/>
													<div align="center">
														<s:submit type="submit" name="submit" key="copyQuote.proceed" cssClass="btn btn-sm btn-success"/>
													</div>
													<s:hidden name="searchCriteria"></s:hidden>
												</div>
											</div>
										</s:form>
									</s:elseif>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">


$(document).ready(function () {
      $('#tadaTable').DataTable({
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
    
function removeSpaces(string) {
	var a=document.getElementById('searchType').value;
	//alert(a);
	if(a==''){
		alert('Please Choose Search By');
		return string;
	}
	else if(a=='custName'){
		return string;
	}else{
		return string.trim();
	} 
}    
function setDefault(obj) { 
 	var value=obj.value;  
 	var productCode='<s:text name="OPEN_COVER"/>';
 	var session_productCode='<s:property value="#session.product_id"/>'
 	if(value=='policyNo' && session_productCode == productCode ) {  
 		document.getElementById('openCover_No').value='<s:property value="#session.openCoverNo"/>'
 	    document.getElementById('openCover').style.display='block';  	
 	    document.getElementById('oneOff').style.display='none';     		
 	} else {
		document.getElementById('oneOff').style.display='block';   
		document.getElementById('openCover').style.display='none';   
 	}
}
function getQuoteNo(obj) { 
	document.copyCodeSearch.copyQuoteValue.value=obj; 	
}
function backButtonAction() {
 	document.copyCodeSearch.action='${pageContext.request.contextPath}/initCopyQuote.action'
 	document.copyCodeSearch.submit();
}
</script>
</body>
</html>				