<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/WEB-INF/displaytag.tld" prefix="display"%>
<!DOCTYPE HTML>
<html>
<head>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/dataTables/css/dataTables.responsive.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/dataTables/css/dataTables.jqueryui.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/dataTables/css/dataTables.tableTools.css">
    <link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/bootstrap/css/bootstrap-multiselect.css" />
    <script type="text/javascript" src="${pageContext.request.contextPath}/dataTables/js/dataTables.responsive.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/dataTables/js/dataTables.tableTools.js"></script>
    
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
  	<style type="text/css">
          #loading {
          width: 100%;
          height: 100%;
          top: 0px;
          left: 0px;
          position: fixed;
          display: block;
          opacity: 0.7;
          background-color: black;
          z-index: 99;
          opacity:0.5;
          text-align: center;
        }
        
        #loading-image {
          position: absolute;
          top: 30%;
          left: 45%;
          z-index: 100;
          width: 100px;
          height: 100px;
        }
        .btn-group, .btn-group-vertical {
		    display: block; 
		}
		.btn-group, .multiselect {
		    width: 100%;
		}
		.btn-default {
		    color: #333;
		    background-color: #fff;
		    border-color: #ccc;
		}
		.text{
		padding-top:10px;
		padding-bottom:3px;
		font-weight:bold;
		}
      </style>
</head>
<body>
<s:form id="adminlist" name="adminlist" method="post" theme="simple">
<div id="loading" style="width:100%">
   <img id="loading-image" src="${pageContext.request.contextPath}/images/madisonSymbolLogo.png"/>
</div>
<div class="row">
	<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
		<div class="container vehDtl">
			<div class="Card_Parent card">
			<div class="panel-heading">
				<s:if test='"alist".equals(from)'>
					<%-- <s:text name="label.admin.existing.list" />
					<div class="pullRight">
						<a class="btn btn-sm btn-default" title="new admin" href="editadminAdmin.action?mode=new" style="text-decoration: none;"><i class="fa fa-user-plus"></i></a>
					</div>
					<br class="clear" /> --%>
					
					<div class="VehicleTableheading">
		               <div class="row">
		                  <div class="col-lg-6 col-md-6">
		                    <h3><s:text name="label.admin.existing.list" /></h3>
		                  </div>
		                  <div class="col-lg-3 offset-md-3 offset-lg-3 col-md-3">
		                      <div  class="pullRight">
		                        <a class="btn btn-primary btn-block bordernone" title="new admin" href="editadminAdmin.action?mode=new" style="text-decoration: none;"><i class="fa fa-user-plus"></i></a>
		                    </div>
		                  </div>
		               </div>
		            </div><hr>
				</s:if>
				<s:elseif test='"aform".equals(from) && "new".equals(mode)'>
					<h3><s:text name="label.admin.new" /></h3><hr>
				</s:elseif>
				<s:elseif test='"aform".equals(from) && "edit".equals(mode)'>
					<h3><s:text name="label.admin.edit" /></h3><hr>
				</s:elseif>
			</div>
			<div class="panel-body">
				<s:if test='"aform".equals(from)'>
				<div class="row">
					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
						<s:actionerror cssStyle="color: red;" /> <s:actionmessage cssStyle="color: green;" />
					</div>
				</div>
				<br/>
				</s:if>
				<s:if test='"alist".equals(from)'>
				<div class="row">
					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
						<table class="table table-bordered table-hover" cellspacing="0" width="100%" id="tadaTable">
                        	<thead class="bluecolortable">
							<tr>
								<th><s:text name="S.No." /></th>
								<th><s:text name="Login ID" /></th>
								<th><s:text name="User Name" /></th>
								<th><s:text name="UserType." /></th>
								<th><s:text name="Branch." /></th>
								<th><s:text name="Status." /></th>
								<th><s:text name="Edit" /></th>
							</tr>
							</thead>
							<tbody class="rowevencolor">
							<s:iterator value="adminList" status="stat" var="record">
							<tr>
								<td><s:property value="%{#stat.index+1}" /></td>
								<td><s:property value="LOGIN_ID" /></td>
								<td><s:property value="USERNAME" /></td>
								<td><s:property value="userType" /></td>
								<td><s:property value="BRANCH_NAME" /></td>								
								<td><s:property value="STATUS1" /></td>
								<td>
									<a class="btn btn-sm btn-warning" type="button" href="#" onclick="forward('<s:property value="LOGIN_ID"/>','editadminAdmin','adminlist')"><i class="fas fa-edit"></i></a>
								</td>
							</tr>
							</s:iterator>
							</tbody>
						</table>
					</div>
				</div>
				<s:hidden name="loginID" id="loginID"/>
				</s:if>
				<s:elseif test='"aform".equals(from)'>
				<div class="row">
					<div class="col-xs-12 col-sm-12 col-md-3 col-lg-3">
						<div class="container vehDtl">
							<div class="Card_Parent card">
								<div class="panel-body">
									<div class="row">
										<div class="col-xs-6 col-sm-6 col-md-12 col-lg-12">
											<input type="button" class="btn btn-sm btn-info adminMenuBtn" value="Change Password" onclick="fnCall('changePwd')"/>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="col-xs-12 col-sm-12 col-md-9 col-lg-9">
						<div class="container vehDtl">
							<div class="Card_Parent card">
								<div class="panel-heading">
									<h3><s:text name="Admin Management" /></h3><hr>
								</div>
								<div class="panel-body">
									<div class="row">
										<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
											<div class="text"><s:text name="admin.user.status"/> <font color="red">*</font></div>
											<div class="tbox">
												<s:select name="status" list="#{'Y':'Active','N':'Deactive','D':'Delete','L':'Lock'}" headerKey="" headerValue="---Select---" cssClass="form-control" />
											</div>
										</div>
										<%--<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
											<div class="text"><s:text name="admin.user.name"/> <font color="red">*</font></div>
											<div class="tbox"><s:textfield name="uname" id="uname" cssClass="form-control" /></div>
										</div>	 --%>
										<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
											<div class="text">First Name <font color="red">*</font></div>
											<div class="tbox"><s:textfield name="firstName" id="firtName" cssClass="form-control" maxlength="30" /></div>
										</div>								
									</div>
									<div class="row">
										<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
											<div class="text">Middle Name </div>
											<div class="tbox"><s:textfield name="middleName" id="middleName" cssClass="form-control" maxlength="30" /></div>
										</div>
										<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
											<div class="text">Last Name <font color="red">*</font></div>
											<div class="tbox"><s:textfield name="lastName" id="lastName" cssClass="form-control" maxlength="30" /></div>
										</div>
									</div>
									<div class="row">
										<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
											<div class="text"><s:text name="admin.user.login.Id"/> <font color="red">*</font></div>
											<div class="tbox">
												<s:if test='"new".equals(mode)'>
					   					    		<s:textfield name="loginID" id="loginID" cssClass="form-control" />
					   					    	</s:if>
					   					    	<s:elseif test='"edit".equals(mode)'>
					   					    		<s:property value="loginID"/><s:hidden name="loginID"/>
					   					    	</s:elseif>
											</div>
										</div>
										<s:if test='"new".equals(mode)'>									 
										<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
											<div class="text"><s:text name="admin.user.pwd"/><s:if test='"new".equals(mode)'><font color="red">*</font></s:if></div>
											<div class="tbox">
												<s:password name="pwd" id="pwd" cssClass="form-control" maxlength="20" />
											</div>
										</div>
										</s:if>
										<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
											<div class="text"><s:text name="admin.user.branch"/> <font color="red">*</font></div>
											<div class="tbox"><s:property value="branch"/><s:hidden name="branch"/></div>
										</div>
										<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
												<!-- Default Menus -->
											<s:iterator value="defaultMenus" var="utypeVar" status="stat"> 
												<s:hidden name="%{'hid_'+#utypeVar.DETAIL_NAME}"  id="%{'hid_'+#utypeVar.DETAIL_NAME}" value="%{#utypeVar.REMARKS}" />
											</s:iterator>
											<div class="text"><s:text name="admin.user.type"/> <font color="red">*</font></div>
											<div class="tbox">
												<s:select name="utype" id="utype" list="utypeList" headerKey="" headerValue="---Select---" listKey="DETAIL_NAME"  listValue="DETAIL_NAME" cssClass="form-control"  onchange="chooseDefaultmenus();chooseOnlineYN(this.value);"/>
											</div>
										</div>
										<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6" id="onlineYN" style="display:none;">
											<div class="text"><s:text name="Applicaple For Online Policies"/> <font color="red">*</font></div>
												<div class="tbox">
													<s:radio name="onlineYN" list="#{'Y':'Yes','N':'No'}"  value="%{onlineYN==null?'N':onlineYN}"/>
												</div>
										</div>
										<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
											<div class="text"><s:text name="admin.user.product.select"/> <font color="red">*</font></div>
											<div class="tbox">							
												<s:select name="productID" id="products" list="productList"  listKey="PRODUCT_ID" listValue="PRODUCT_NAME" headerKey="" label="" multiple="true" />
											</div>
										</div>
										<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
											<div class="text"><s:text name="admin.user.menu.select"/> <font color="red">*</font></div>
											<div class="tbox">
												<s:hidden name="mid" id="mid"></s:hidden>
												<%--<s:textarea name="mid" id="mid" cssClass="form-control" rows="2" cssStyle="width: 85%;"/>--%>
												<input class="btn btn-sm btn-primary" value="Choose Excluded Menus" type="button" name="menu" onclick="return clickMenuPopup('${pageContext.request.contextPath}/menuSelectionAdmin.action')"/>
											</div>
										</div>									
									</div>
									<div class="row">
										<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
											<div class="text"><s:text name="admin.user.mail"/> <font color="red">*</font></div>
											<div class="tbox">
												<s:textfield name="email" id="email" cssClass="form-control" />
											</div>
										</div>
										<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
											<div class="text"><s:text name="admin.user.mobile"/> <font color="red">*</font></div>
											<div class="tbox">
												<s:textfield name="mobileNo" id="mobileNo" maxlength="10" cssClass="form-control numericOnly" />
											</div>
										</div>
									</div>
									<br/>
									<div class="row">
										<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
											<div class="text"><s:text name="admin.user.branch.select"/> <font color="red">*</font></div>
											<div class="tbox">
												<s:textarea name="branchId" id="branchId" cssClass="form-controlA" rows="2" cssStyle="width: 85%;"/>
												<input class="btn btn-sm btn-primary" value="..." style="float:right;" type="button" name="menu" onclick="return callPopup('${pageContext.request.contextPath}/branchSelectionAdmin.action')"/>							
											</div>
										</div>
										<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
											<div class="text"><s:text name="admin.user.broker.select"/> <font color="red">*</font></div>
											<div class="tbox">
												<s:textarea name="broker" id="broker" cssClass="form-controlA" rows="2" cssStyle="width: 85%;" />
												<input class="btn btn-sm btn-primary" style="float:right;" value="..." type="button" name="menu" onclick="return callPopup('${pageContext.request.contextPath}/brokerSelectionAdmin.action')"/>
											</div>
										</div>
									</div>
									<s:hidden name="uwgrade" value="9" />
									<br/><%--
									<div class="row">
										<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
											<div class="text"><s:text name="admin.user.uw.select"/> <font color="red">*</font></div>
											<div class="tbox">							
												<s:select name="uwgrade" id="uwgrades" list="uwGradeList"  listKey="CODE" listValue="CODEDESC" headerKey="" label="" multiple="true" />							
											</div>
										</div>
										 
										<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
											<div class="text"><s:text name="admin.user.pwd"/><s:if test='"new".equals(mode)'><font color="red">*</font></s:if></div>
											<div class="tbox">
												<s:password name="pwd" id="pwd" cssClass="form-control" maxlength="20" />
											</div>
										</div> 
									</div>--%>								
								</div>
							</div>
						</div>
					</div>									
				</div>
				<br class="clear" />
				<div class="row">
					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" align="center">
						<input type="button" name="submit2" class="btn btn-sm btn-danger" value="Back" onclick="fnsubmit('adminback',this.form)"/> &nbsp;&nbsp;&nbsp;
						<input type="button" name="submit1" class="btn btn-sm btn-success" value="Submit" onclick="fnsubmit('admin',this.form)"/>
					</div>
				</div>
				</s:elseif>
				<s:elseif test='"addSuccess".equals(from)'>
					<div class="row">
						<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" align="center">
							<span class="label label-md label-success" ><s:text name="admin.new.add.success"/></span>
						</div>
					</div>
					<br class="clear" />
					<div class="row">
						<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" align="center">
							<input type="button" name="submit1" class="btn btn-sm btn-danger" value="Back" onclick="fnsubmit('adminback',this.form)"/>
						</div>
					</div>
				</s:elseif>
				<s:elseif test='"updateSuccess".equals(from)'>
					<div class="row">
						<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" align="center">
							<span class="label label-md label-success" ><s:text name="admin.exist.update.success"/></span>
						</div>
					</div>
					<br class="clear" />
					<div class="row">
						<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" align="center">
							<input type="button" name="submit1" class="btn btn-sm btn-danger" value="Back" onclick="fnsubmit('adminback',this.form)" />
						</div>
					</div>
				</s:elseif>		
			</div>
		</div>
	</div>
</div>
</div>
<s:hidden name="mode" id="mode"/>
<s:hidden name="from"/>
<s:hidden name="mode1"/>
<s:hidden name="from1"/>
</s:form>
<script src="<%=request.getContextPath()%>/bootstrap/js/bootstrap-multiselect.js" type="text/javascript"></script>
<script type="text/javascript">
function callPopup(URL) {
 	var bwidth = window.innerWidth;
	var bwidth1 = document.body.clientWidth;
	if(bwidth <= 768) {
		return popUp(URL,bwidth1,'500');
	} else {
		return popUp(URL,'750','500');
	}
}
function stopRKey(evt) { 
  var evt = (evt) ? evt : ((event) ? event : null); 
  var node = (evt.target) ? evt.target : ((evt.srcElement) ? evt.srcElement : null); 
  if ((evt.keyCode == 13) && (node.type=="text"))  {return false;} 
} 
document.onkeypress = stopRKey;

function getMenu(val, id){
	if(val.length>=2){
		var searchBy=document.getElementById("searchBy1").value;
		postRequest('${pageContext.request.contextPath}/ajaxListAdmin.action?reqFrom='+id+'&searchBy='+searchBy+'&searchValue='+val, id);
	}
}

function getBroker(val, id){
	if(val.length>=2){
		var searchBy=document.getElementById("searchBy").value;
		postRequest('${pageContext.request.contextPath}/ajaxListAdmin.action?reqFrom='+id+'&searchBy='+searchBy+'&searchValue='+val, id);
	}
}


function chooseDefaultmenus(){
	var val=document.getElementById('utype').value;
	console.log('hid_'+val);
	 var hiddenValue=document.getElementById('hid_'+val).value;
	 document.getElementById('mid').value=hiddenValue;
}

function fnsubmit(from, frm){
	if(from=='menuback'){
		document.menulist.action="adminMgtAdmin.action?index=1";
		document.menulist.submit();
	}else if(from=='menu'){
		document.menulist.action="menuSaveAdmin.action?index=1";
		document.menulist.submit();
	}else if(from=='adminback'){
		document.adminlist.action="adminMgtAdmin.action?index=0";
		document.adminlist.submit();
	}else if(from=='admin'){
		/*if(document.getElementById('pwd').value!='' && document.getElementById('mode').value!='new'){
			conf=false;
			conf = confirm("Are You want to change Password?");
			if(!conf)
				document.getElementById('pwd').value='';
		}*/
		document.adminlist.action="adminSaveAdmin.action?index=0";
		document.adminlist.submit();
	}
}

function forward(id, action, frm){
	if(frm=='menulist'){
		document.menulist.action=action+".action";
		document.getElementById('mid').value=id;
		document.getElementById('mode1').value='edit';
		document.menulist.submit();
	}else if(frm=='adminlist'){
		document.adminlist.action=action+".action";
		document.getElementById('mode').value='edit';
		document.getElementById('loginID').value=id;
		document.adminlist.submit();
	}
}
var selectedproduct = [];
$(document).ready(function() {     
    $('#products').multiselect({ 
      includeSelectAllOption: true,
        enableFiltering:true,
        buttonText: function (options) {
	        if (options.length == 0) {
	            return 'None selected';
	        } else {
	            var selected = 0;
	            options.each(function () {
	                selected += 1;
	            });
	            return selected +  ' Selected ';
	        }
    	}
  	});	
    $('#uwgrades').multiselect({ 
        includeSelectAllOption: true,
          enableFiltering:true,
	        buttonText: function (options) {
	        if (options.length == 0) {
	            return 'None selected';
	        } else {
	            var selected = 0;
	            options.each(function () {
	                selected += 1;
	            });
	            return selected +  ' Selected ';
	        }
	    } 
    });   
 	<s:if test='uwgrade!=null && !"".equals(uwgrade)'>	
 		var uwgrade='<s:property value="uwgrade"/>';
		 var data=uwgrade.replace(/ /g,'');	
	   	 var dataArray=data.split(",");   	 
	   	$("#uwgrades").val(dataArray);
		 $("#uwgrades").multiselect("refresh");
	</s:if>
	        
   
    
});

function clickMenuPopup(url){	 
	var checkedItems='';
	try
	{
		var elements=document.getElementById("products");		 
		for(i=0; i<elements.length; i++)
		{
			obj=elements[i];
			if(obj.selected)
				checkedItems+=obj.value+',';
		}
		var menuId=document.getElementById("mid").value;
	}catch(e){}	 
	if(checkedItems.length>1)
		checkedItems=checkedItems.substring(0, checkedItems.length-1);	 
	if(checkedItems!=null && checkedItems!="")
		return callPopup(url+'?selProducts='+checkedItems+'&mid='+menuId);
	else
		alert('Please Select Product');	
}

function fnCall(from){
	document.adminlist.action = "changePassAdmin.action?from="+from;
	document.adminlist.submit();
}
chooseOnlineYN('<s:property value="utype"/>');
function chooseOnlineYN(utype){
	if(utype=="surveyor"){
		$("#onlineYN").show();
	}else{
		$("#onlineYN").hide();
	}
}
</script>
</body>
</html>