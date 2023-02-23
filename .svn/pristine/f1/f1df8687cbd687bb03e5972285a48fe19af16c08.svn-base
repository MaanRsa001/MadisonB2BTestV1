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
	           <a class='nav-link in active' data-toggle="tab" href="#menu1">Add From Master</a>
	      </li>
	      <li id="tab2" class="nav-item ">
	           <a class='nav-link' data-toggle="tab" href="#menu2">Edit</a>
	      </li>
	      <li id="tab3" class="nav-item ">
	           <a class='nav-link' data-toggle="tab" href="#menu3">Add New</a>
	      </li>
      </ul>
	  <div class="tab-content mt-3">
	    <div id="menu1" class="tab-pane fade in active show">
	    	<b><s:text name="Condition List" /></b><hr/>
			 <s:if test='conditionList.size()>0'>
				 <div class="row mt-2 myCheckBox" align="left">
		       			<%-- <input type="checkbox" name="extendedCover" id='<s:property value="#list.OPCOVEREXT_ID"/>' value='<s:property value="#list.OPCOVEREXT_ID"/>'/> --%>
		       			<s:checkboxlist name="condition" id="condition" theme="simple"  fieldValue="true" listKey="CODE" listValue="CODE_DESC" list="conditionList" /><br/>
		       	</div>
		        <div class="row mt-2">
		       		<div class="col-lg-3 offset-md-1 offset-lg-4 col-md-3" align="right">
		            	<a class="btn btn-success btn-block bordernone" onclick="return fnSubmitExistCondition();">
		             		 Submit
		            	</a>
		            </div>
		            <!-- <div class="col-lg-3 offset-md-4 offset-lg-4 col-md-3" align="right">
		            	<a class="btn btn-primary btn-block bordernone" onclick="return fnAddCondition('Y');">
		             		 Add New
		            	</a>
		            </div> -->
		       </div>
	       </s:if>
	       <s:else>
	       		<div class="row" align="center">
					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
							No Conditions to Add
					</div>
				</div>
		   </s:else>
	    </div>
	    <div id="menu2" class="tab-pane fade">
	      <b><s:text name="Edit Condition Details" /></b><hr/>
		      <s:if test='conditionEditList.size()>0'>
				<div class="row" align="center">
					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
						<table class="footable" style="width:100%">
							<tbody>
								<s:iterator value="conditionEditList" var="list" status ="stat">
									<tr>
										<td align="left">
											<input type="checkbox" name="conditions" id="myCheck<s:property value="#list.CODE" />" value='<s:property value="#stat.count-1" />' checked="checked" style="height: 40px;"/>
											<label style="width:90%"><s:textarea theme="simple" name="conditionText[%{#stat.index}]" id="conditionText[%{#list.CODE}]" value="%{CODE_DESC}" class="form-control"/></label>
										</td>
										<%-- <td>
											<s:checkbox theme="simple" name="conditionList[%{#stat.count-1}]" id="conditionList[%{#list.CODE}]" fieldValue="true" ></s:checkbox>
										</td>
										<td>
											<s:textarea theme="simple" name="conditionText[%{#stat.count-1}]" id="conditionText[%{#list.CODE}]" value="%{CODE_DESC}" class="form-control"/>
										</td> --%>
									</tr>
								</s:iterator>
							</tbody>
						</table>
					</div>
				</div><br/>
				<div class="row">
		       		<div class="col-lg-3 offset-md-1 offset-lg-4 col-md-3" align="right">
		            	<a class="btn btn-success btn-block bordernone" onclick="return fnEditExistCondition();">
		             		 Submit
		            	</a>
		            </div>
		       </div>
	       </s:if>
	       <s:else>
	       		<div class="row" align="center">
	       			<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
						No Conditions Added
					</div>
				</div>	
		   </s:else>
		   <s:hidden name="conditionSelected" id="conditionSelected"/>
	    </div>
	    <div id="menu3" class="tab-pane fade">
	      <b><s:text name="Add New Condition" /></b><hr/>
	      	<div class="row">
	       		<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
	       			<label><s:text name="Condition Description"  /><font color="red">*</font></label>
	       			<s:textfield name="conditionDesc" id="conditionDesc" class="form-control"/>
	       		</div>
       		</div>
       		<div class="row mt-3">
       			<div class="col-lg-3 offset-md-1 offset-lg-4 col-md-3" align="right">
            		<a class="btn btn-success btn-block bordernone" onclick="return fnSubmitCondition();">
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

	function fnAddCondition(val){
		if(val=='Y')
			document.getElementById('newDtl').style.display='block';
		else
			document.getElementById('newDtl').style.display='none';
	}
	function fnSubmitCondition() {
		postFormRequest('${pageContext.request.contextPath}/saveConditionsMotor.action', 'clausesEditAjax','motor');
		return false;
	}
	function fnSubmitExistCondition() {
		postFormRequest('${pageContext.request.contextPath}/addExistConditionsMotor.action', 'clausesEditAjax','motor');
		return false;
	}
	function fnEditExistCondition(){
		var array = []; 
        $("input:checkbox[name=conditions]:checked").each(function() { 
            array.push($(this).val()); 
        }); 
        if(array.length<=0){
			alert("Please Select Atleast One Condition");
		}
		else{
			//alert(array);
			document.getElementById('conditionSelected').value = array;
			postFormRequest('${pageContext.request.contextPath}/updateConditionsMotor.action', 'clausesEditAjax','motor');
			return false;
		}
		
	}
	try{
	      <s:if test="hasActionMessages()">
	      	$("#conModal").modal('hide');
	      </s:if>
     }catch(err){
  	   console.log(err);
     }
     
     enableTab('<s:property value="mode"/>');
     function enableTab(val){
    	 if(val=='addNew'){
		 	$('[href="#menu3"]').tab('show');
     	 }
	}
</script>
</body>
</html>