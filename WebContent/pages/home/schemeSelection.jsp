<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ page import="java.util.List,java.util.ArrayList,java.util.Map"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page import="nl.captcha.Captcha"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html <s:if test="locale.language == 'ar'">dir="rtl"</s:if> >
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/dataTables/css/dataTables.responsive.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/dataTables/css/dataTables.jqueryui.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/dataTables/css/dataTables.tableTools.css">
	<script type="text/javascript" src="${pageContext.request.contextPath}/dataTables/js/dataTables.responsive.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/dataTables/js/dataTables.tableTools.js"></script>
    <style>
        .SchemePlanPage .card {
            padding: 20px 20px 20px 130px;
            border: 0px !important
        }

        .SchemePlanPage .Card_Parent {
            border-radius: 4px;
            background: #fff;
            box-shadow: 0 6px 10px rgba(0, 0, 0, .08), 0 0 6px rgba(0, 0, 0, .05);
            transition: .3s transform cubic-bezier(.155, 1.105, .295, 1.12), .3s box-shadow, .3s -webkit-transform cubic-bezier(.155, 1.105, .295, 1.12);
        }

        .SchemePlanPage .plan_Card {
            box-shadow: 0 1px 1px rgba(0, 0, 0, 0.25),
                0 2px 2px rgba(0, 0, 0, 0.20),
                0 4px 4px rgba(0, 0, 0, 0.15),
                0 8px 8px rgba(0, 0, 0, 0.10),
                0 20px 20px rgba(0, 0, 0, 0.05);
            padding: 10px;
            border-radius: 5px;
            background-color: aliceblue;
            min-height: 65px;
            max-height: 100%;
            font-weight: bold;
        }


        .SchemePlanPage .card h3 {
            font-weight: 700;
            color: #261e6a;
        }

        .SchemePlanPage .card-1 {
            background-image: url("${pageContext.request.contextPath}/assets/Images/home-insurances.png");
            background-repeat: no-repeat;
            background-position: left;
            background-size: 200px;
        }
        .SchemePlanPage .plan_child_Card{
            box-shadow: rgba(0, 0, 0, 0.19) 0px 10px 20px, rgba(0, 0, 0, 0.23) 0px 6px 6px;
            padding: 15px;
            border-radius: 5px;
            background-color: white;
        }
        .SchemePlanPage span.float-right{
            color: slateblue;
        }
        .checkBoxList [type="checkbox"]{
        	width: 7%;
        }
        .checkBoxList label{
        	width: 86%;
        }
    </style>
</head>
<body>
	<div id="loading" style="width:100%">
	   <img id="loading-image" src="${pageContext.request.contextPath}/images/madisonSymbolLogo.png"/>
	</div>
	<s:form name="schemeSelection" id="schemeSelection" theme="simple" >
        <s:hidden name="quoteNo" id="quoteNo"/>
        <s:hidden name="applicationNo" id="applicationNo"/>
		<s:hidden name="menuType" id="menuType"/>
		<s:hidden name="quoteStatus" id="quoteStatus"/>
	    <div class="container SchemePlanPage mt-5">
	    	<s:set var="activeLocationDtlsVar" value="activeLocationDtls"/>
	    	<s:set var="activeSchemeListVar" value="activeSchemeList"/>
	        <div class="Card_Parent SchemePlan">
	        	<div class="row">
	        		<s:actionerror style="color:red;"/>
	        	</div>
	            <div class="card card-1">
	            	<div class="row rowFlex">
		            	<div class="col-md-4 col-12">
	                		<h3>Scheme Plan</h3>
	                	</div>
		            	<div class="col-md-8 col-12">
	                		<b>(Click <a class="btn btn-success btn-sm" style="width: fit-content;cursor: none;"><i class="fa fa-chevron-circle-down" aria-hidden="true"></i></a> near to the respective scheme name to select their locations)</b>
	                	</div>
	                </div>
	                <hr>
	                <div class="row">
	                	<s:iterator value="#activeSchemeListVar" var="aslv" status="stat">
		                    <div class="col-md-4 mt-2">
		                        <div class="plan_Card">
		                            <table>
		                            	<tr>
		                            		<td width="5%"><s:checkbox name="schemeSelect[%{#stat.index}]" id="schemeSelect_%{#aslv.SCHEME_ID}" onclick="enableLoc('%{#aslv.SCHEME_ID}');"/></td>
		                            		<td width="5%">&nbsp;</td>
		                                	<td width="80%" height="50px;"><s:property value="#aslv.SCHEME_NAME"/></td>
		                                	<td width="5%">&nbsp;</td>
		                                	<td width="5%"><a style="cursor:pointer; display:none;" id='selectLocBtnId_<s:property value="#aslv.SCHEME_ID"/>' class="btn btn-success btn-block" data-toggle="collapse" data-target='#locDivId_<s:property value="#aslv.SCHEME_ID"/>'><i class="fa fa-chevron-circle-down" aria-hidden="true"></i></a></td>
		                                </tr>
		                            </table>
		                            <div id='locDivId_<s:property value="#aslv.SCHEME_ID"/>' class="collapse">
		                                <div class="plan_child_Card mt-3">
		                                    <div class="checkBoxList">
		                                        <s:checkboxlist name="schemeLocation[%{#stat.index}]" id="schemeLocation_%{#aslv.SCHEME_ID}" list="#activeLocationDtlsVar" listKey="LOCATION_ID" listValue="LOCATION_NAME" cssClass="custom-control-input"/>
		                                    </div>
		                                </div>
		                            </div>
		                        </div>
		                    </div>
	                    </s:iterator>
	                </div>
	            </div>
	        </div>
	        <div class="row mt-4">
	            <div class="col-md-2 col-2 col-sm-2 offset-md-4">
	                <a style="cursor:pointer" onclick="dkSubmit('packageSelectionHome.do');" class="btn btn-danger btn-block">Back</a>
	            </div>
	            <div class="col-md-2 col-2 col-sm-2">
	                <a  style="cursor:pointer" onclick="dkSubmit('saveSchemeHome.do');" class="btn btn-primary btn-block">Proceed</a>
	            </div>
	        </div>
	    </div>
    </s:form>
    <script type="text/javascript">
	
		function dkSubmit(act){
			try{
				document.schemeSelection.action = '${pageContext.request.contextPath}/'+act;
				document.schemeSelection.submit();
			}catch(err){
				console.log(err);
			}
		}
		
		function enableLoc(id){
			try{
				var isCheck = document.getElementById('schemeSelect_'+id).checked;
				if(isCheck){
					document.getElementById('selectLocBtnId_'+id).style.display = 'block';
				}else{
					document.getElementById('locDivId_'+id).className = 'collapse';
					document.getElementById('selectLocBtnId_'+id).style.display = 'none';
				}
			}catch(err){
				console.log(err);
			}
		}
		
		<s:if test='schemeSelect!=null && schemeSelect.size()>0'>
			editEnableLoc();
		</s:if>
		function editEnableLoc(){
			try{
				<s:iterator value="#activeSchemeListVar" var="aslv" status="stat">
					enableLoc('<s:property value="#aslv.SCHEME_ID"/>');
				</s:iterator>
			}catch(err){
				console.log(err);
			}
		}
    </script>
</body>
</html>