<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ page language="java" import="java.util.*,com.maan.Office.DAO.*" %>
<%
	String path = request.getContextPath();
String schemeName = request.getScheme();
String serverName = request.getServerName();
String serverPort =  String.valueOf(request.getServerPort());
if(serverName.startsWith("online")){
	schemeName = "https";
	serverPort = "";
}
String basePath = schemeName + "://"
		+ serverName + (!"".equalsIgnoreCase(serverPort)?(":"+serverPort):"")
		+ path + "/";
%>
<!DOCTYPE html>
<html>
<head>
	<base href="<%=basePath%>">
	<title>Extension Master</title>
	<%
		String mode=(String)request.getAttribute("mode")==null?"contentinsert":(String)request.getAttribute("mode");
		String contentType=(String)request.getAttribute("contentType")==null?"contentinsert":(String)request.getAttribute("contentType");  
		String values[][] = (String[][]) request.getAttribute("values") ==null?new String[0][0] : (String[][]) request.getAttribute("values");
	%>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/dataTables/css/dataTables.responsive.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/dataTables/css/dataTables.jqueryui.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/dataTables/css/dataTables.tableTools.css">
	<link href="<%=request.getContextPath() %>/dataTables/css/jquery-start-ui.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="<%=request.getContextPath() %>/dataTables/js/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/dataTables/js/jquery.dataTables.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/dataTables/js/dataTables.jqueryui.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/dataTables/js/dataTables.responsive.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/dataTables/js/dataTables.tableTools.js"></script>
	<script type="text/javascript">
  	jQuery(function ($) {
  		try {
  			var data = $('#record').dataTable( {
				"lengthMenu": [[10, 25, 50, -1], [10, 25, 50, "All"]],
				"order": [[ 0, "asc" ]],
				"columnDefs": [ {
		          "targets": 'no-sort',
		          "orderable": false
			    } ],
				responsive: true
			});
		} catch(err){}
	} );
  	</script>
</head>
<body>
<div id="page" class="content">
	<div class="header">
		<%@ include file="../admin/header.jsp"%>
	</div>
	<div class="body">
		<div class="row">
			<div class="col-xs-12">
				<form name="contentForm"  method="post">
					<div class="panel panel-primary">
						<div class="panel-heading"> Extension Master </div>
						<div class="panel-body">
							<%
								String error = (String) request.getAttribute("error");
								error = error == null ? "" : error;
								if (error.length() > 0) {
							%>
							<div class="row">
								<div class="col-xs-12">
									<font color="red"><%=error%> </font>
								</div>
							</div>
							<br/>
							<%	} %>
							<div class="row">
								<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
									<table class="display responsive no-wrap" id="record" width="100%" cellspacing="0">
										<thead>
										<tr>
											<th>Coverage</th>
											<th>Effective Date</th>				
										    <th>Core Application Code</th>
											<th>Description</th>
											<th>Status</th>
											<th>Display order</th>	
											<th>Option</th>
										</tr>
										</thead>
										<tbody>
										<%	for(int i=0;i<values.length;i++) { %>
											<tr>
												<td> <%=values[i][0] %> </td>
												<td> <input type="text" name="effectiveDate<%=i%>" value="<%=values[i][7]==null?"N/A":values[i][7]%>" size="10" maxlength="20" class="inputBox datePicker" > </td>
											   	<td> <input type="text" name="rsa_code<%=i%>" value="<%=values[i][8]%>" size="2" class="inputBox"> </td>
												<td> <input type="text" name="ext_name<%=i%>" value="<%=values[i][1]%>" size="50" class="inputBox">	</td>
												<td>
													<select name="status<%=i%>" class="inputBoxS">
														<option value="Y" <%=(values[i][2].equalsIgnoreCase("Y"))?"selected":""%>> Activate </option>
														<option value="N" <%=(values[i][2].equalsIgnoreCase("N"))?"selected":""%>> DeActivate </option>
													</select>
												</td>
												<td> <input type="text" name="dis_order<%=i%>" value="<%=values[i][3]%>" size="2" class="inputBox">	</td>
												<td>
													<input type="button"  class="btn btn-sm btn-warning" value="Edit" onClick="return SubmitSaveAndContinue('<%=mode%>','<%=i%>','<%=values[i][4]%>','<%=values[i][5]%>','<%=values[i][6]%>','<%=contentType%>')"  tabindex="31">	
												</td>
											</tr>													
										<%} %>
										</tbody>
									</table>
								</div>
							</div>
						</div>
					</div>
					<input type="hidden" name="linkFrom" value="">
					<input type="hidden" name="ival" value="">
					<input type="hidden" name="cover_id" value="">
					<input type="hidden" name="ext_id" value="">
					<input type="hidden" name="scheme_id" value="">
					<input type="hidden" name="content_id" value="">
				</form>
			</div>
		</div>
	</div>
</div>
</body>
<script type="text/javascript">
function SubmitSaveAndContinue(val,i,cover_id,ext_id,scheme_id,cont_type) {
	document.contentForm.linkFrom.value=val;
	document.contentForm.ival.value=i;
	document.contentForm.cover_id.value=cover_id;
	document.contentForm.ext_id.value=ext_id;
	document.contentForm.scheme_id.value=scheme_id;
	document.contentForm.content_id.value=cont_type;
	document.contentForm.action="<%=path%>/servlet/adminEntry";
	document.contentForm.submit();
	return false;
}		
</script>
</html>