<%@ page isELIgnored="false" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html <s:if test="locale.language == 'ar'">dir="rtl"</s:if> >
<head>
<script type="text/javascript">
</script>

<style type="">

.myCheckBox [type="checkbox"]{
	width:5%;
}
.myCheckBox label{
	width:89%;
	font-size:15px;
}
</style>
</head>
<body>
<div id="loading" class="ajaxLoader" style="width:100%">
   <img id="loading-image" src="${pageContext.request.contextPath}/images/madisonSymbolLogo.png"/>
</div>
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
<div class="container PolicyPage">
	<div class="premiumBorder mt-2">
  	  <ul class="nav nav-tabs" role="tablist">
	      <li id="tab1" class="nav-item ">
	           <a class='nav-link in active' data-toggle="tab" href="#deduc1">Add From Master</a>
	      </li>
	      <li id="tab2" class="nav-item ">
	           <a class='nav-link' data-toggle="tab" href="#deduc2">Edit</a>
	      </li>
	      <li id="tab3" class="nav-item ">
	           <a class='nav-link' data-toggle="tab" href="#deduc3">Add New</a>
	      </li>
      </ul>
	  <div class="tab-content mt-3">
	    <div id="deduc1" class="tab-pane fade in active show">
	    	<b><s:text name="Deductible List" /></b><hr/>
			 <s:if test='deductibleAddList.size()>0'>
				 <div class="row mt-2 myCheckBox" align="left">
		       				       			<%-- <input type="checkbox" name="extendedCover" id='<s:property value="#list.OPCOVEREXT_ID"/>' value='<s:property value="#list.OPCOVEREXT_ID"/>'/> --%>
		       			<s:checkboxlist name="deductible" id="deductible" theme="simple"  fieldValue="true" listKey="CODE" listValue="CODE_DESC" list="deductibleAddList" /><br/>
		       	</div>
		        <div class="row mt-2">
		       		<div class="col-lg-3 offset-md-1 offset-lg-4 col-md-3" align="right">
		            	<a class="btn btn-success btn-block bordernone" onclick="return fnSubmitExistDeductible();">
		             		 Submit
		            	</a>
		            </div>
		       </div>
	       </s:if>
	       <s:else>
	       		<div class="row" align="center">
					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
							No Deductibles to Add
					</div>
				</div>
		   </s:else>
	    </div>
	    <div id="deduc2" class="tab-pane fade">
	      <b><s:text name="Edit Deductible Details" /></b><hr/>
		      <s:if test='deductibleEditList.size()>0 '>
				<div class="row" align="center">
					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
						<table class="footable" style="width:100%">
							<tbody>
								<s:iterator value="deductibleEditList" var="list" status ="stat">
									<tr>
										<td align="left">
											<input type="checkbox" name="deductibles" id="myCheck<s:property value="#list.CODE" />" value='<s:property value="#stat.count-1" />' checked="checked" style="height: 40px;"/>
											<label style="width:90%"><s:textarea theme="simple" name="deductibleText[%{#stat.index}]" id="deductibleText[%{#list.CODE}]" value="%{CODE_DESC}" class="form-control"/></label>
										</td>
									</tr>
								</s:iterator>
							</tbody>
						</table>
					</div>
				</div><br/>
				<div class="row">
		       		<div class="col-lg-3 offset-md-1 offset-lg-4 col-md-3" align="right">
		            	<a class="btn btn-success btn-block bordernone" onclick="return fnEditExistDeductible();">
		             		 Submit
		            	</a>
		            </div>
		       </div>
	       </s:if>
	       <s:else>
	       		<div class="row" align="center">
	       			<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
						No Deductibles Added
					</div>
				</div>	
		   </s:else>
		   <s:hidden name="deductibleSelected" id="deductibleSelected"/>
	    </div>
	    <div id="deduc3" class="tab-pane fade">
	      <b><s:text name="Add New Deductible" /></b><hr/>
	      	<div class="row">
	       		<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
	       			<label><s:text name="Deductible Description"  /><font color="red">*</font></label>
	       			<s:textfield name="deductibleDesc" id="deductibleDesc" class="form-control"/>
	       		</div>
       		</div>
       		<div class="row mt-3">
       			<div class="col-lg-3 offset-md-1 offset-lg-4 col-md-3" align="right">
            		<a class="btn btn-success btn-block bordernone" onclick="return fnSubmitDeductible();">
             		 	Submit
            		</a>
            	</div>
       		</div>
	    </div>
	  </div>
	</div>                  
</div>
<script  type="text/javascript">
	 $(".myCheckBox [type='checkbox']").addClass("col-md-1");
	 $(".myCheckBox label").addClass("col-md-11");
	function fnSubmitDeductible() {
		postFormRequest('${pageContext.request.contextPath}/saveDeductibleMotor.action', 'deductibleEditAjax','motor');
		return false;
	}
	function fnSubmitExistDeductible() {
		postFormRequest('${pageContext.request.contextPath}/addExistDeductibleMotor.action', 'deductibleEditAjax','motor');
		return false;
	}
	function fnEditExistDeductible(){
		var array = []; 
        $("input:checkbox[name=deductibles]:checked").each(function() { 
            array.push($(this).val()); 
        }); 
        if(array.length<=0){
			alert("Please Select Atleast One Condition");
		}
		else{
			//alert(array);
			document.getElementById('deductibleSelected').value = array;
			postFormRequest('${pageContext.request.contextPath}/updateDeductibleMotor.action', 'deductibleEditAjax','motor');
			return false;
		}
		
	}
	try{
	      <s:if test="hasActionMessages()">
	      	$("#dedutibleModal").modal('hide');
	      </s:if>
     }catch(err){
  	   console.log(err);
     }
     enableTab('<s:property value="mode"/>');
     function enableTab(val){
    	 if(val=='addNew'){
		 	$('[href="#deduc3"]').tab('show');
     	 }
	}
</script>
</body>
</html>