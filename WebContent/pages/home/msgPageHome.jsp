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
        * {
            font-family: 'Nunito', sans-serif !important;
        }

        .fas {
            font-family: "Font Awesome 5 Free" !important;
        }

        .QuotationInformationPage .card {
            padding: 20px 20px 20px 120px;
            border: 0px !important
        }

        .QuotationInformationPage .card-2 {
            padding: 20px 30px 20px 30px;

        }

        .QuotationInformationPage .Card_Parent {
            border-radius: 4px;
            background: #fff;
            box-shadow: 0 6px 10px rgba(0, 0, 0, .08), 0 0 6px rgba(0, 0, 0, .05);
            transition: .3s transform cubic-bezier(.155, 1.105, .295, 1.12), .3s box-shadow, .3s -webkit-transform cubic-bezier(.155, 1.105, .295, 1.12);
        }

        .QuotationInformationPage .plan_Card {
            box-shadow: 0 1px 1px rgba(0, 0, 0, 0.25),
                0 2px 2px rgba(0, 0, 0, 0.20),
                0 4px 4px rgba(0, 0, 0, 0.15),
                0 8px 8px rgba(0, 0, 0, 0.10),
                0 16px 16px rgba(0, 0, 0, 0.05);
            padding: 10px;
            border-radius: 5px;
            background-color: aliceblue;
        }

        .PremiumCoverDetails {
            box-shadow: 0 1px 1px rgba(0, 0, 0, 0.25),
                0 2px 2px rgba(0, 0, 0, 0.20),
                0 4px 4px rgba(0, 0, 0, 0.15),
                0 8px 8px rgba(0, 0, 0, 0.10),
                0 16px 16px rgba(0, 0, 0, 0.05);
            border-radius: 5px;
            background-color: white;
        }

        .QuotationInformationPage .card h4 {
            font-weight: 700;
            color: #261e6a;
        }

        .QuotationInformationPage .card-1 {
            background-image: url("assets/Images/home-insurances.png");
            background-repeat: no-repeat;
            background-position: left;
            background-size: 200px;
        }

        .QuotationInformationPage .LabelHeading {
            font-weight: bolder;
        }

        .PremiumCoverDetails .list-group .list-group-item {
            border-right: white;
            border-left: white;

        }

        .PremiumCoverDetails .list-group .list-group-item:first-child {
            border-top: white;
        }

        .PremiumCoverDetails .list-group .list-group-item:last-child {
            border-bottom: white;
        }

        .rowFlex {
            display: flex;
        }

        .rowFlex ul li {
            border-bottom: 1px solid #ccc;
        }

        .rowFlex ul li a {
            border-radius: 0px !important;
        }

        .colsame {
            flex: 1;
            /* border: 1px solid #ccc; */
        }

        .tabsidebar {
            box-shadow: 0 1px 1px rgba(0, 0, 0, 0.25),
                0 2px 2px rgba(0, 0, 0, 0.20),
                0 4px 4px rgba(0, 0, 0, 0.15),
                0 8px 8px rgba(0, 0, 0, 0.10),
                0 16px 16px rgba(0, 0, 0, 0.05);
        }

        .submenus li {
            box-shadow: 0 1px 2px rgba(0, 0, 0, 0.07),
                0 2px 4px rgba(0, 0, 0, 0.07),
                0 4px 8px rgba(0, 0, 0, 0.07),
                0 8px 16px rgba(0, 0, 0, 0.07),
                0 16px 32px rgba(0, 0, 0, 0.07),
                0 32px 64px rgba(0, 0, 0, 0.07);
            border: 0 !important;
        }

        .coveragepadding {
            padding: 60px 10px 10px 10px;
        }

        .OnlyPremiumTable .table {
            width: 100%;
            border-collapse: collapse;
            border-radius: 20px;
            box-shadow: 0 1px 2px rgba(0, 0, 0, 0.07),
                0 2px 4px rgba(0, 0, 0, 0.07),
                0 4px 8px rgba(0, 0, 0, 0.07),
                0 8px 16px rgba(0, 0, 0, 0.07),
                0 16px 32px rgba(0, 0, 0, 0.07),
                0 32px 64px rgba(0, 0, 0, 0.07);
        }

        .OnlyPremiumTable .table td,
        .table th {

            border-top: 0;
        }

        .OnlyPremiumTable thead th:first-child {
            border-radius: 20px 0px 0px 0px !important;
        }

        .OnlyPremiumTable thead th:last-child {
            border-radius: 0px 20px 0px 0px !important;

        }

        .OnlyPremiumTable tbody tr:last-child td:first-child {
            border-radius: 0px 0px 0px 20px !important;

        }

        .OnlyPremiumTable tbody tr:last-child td:last-child {
            border-radius: 0px 0px 20px 0px !important;

        }

        .OnlyPremiumTable .table td,
        .OnlyPremiumTable .table th {
            vertical-align: middle;
        }



        .OnlyPremiumTable .table td,
        .OnlyPremiumTable .table th {
            padding: 10px;
            /* border: 1px solid #ddd; */
            font-size: 14px;
        }

        .OnlyPremiumTable .table th {
            color: black;
        }

        /* .OnlyPremiumTable .table tbody tr:nth-child(even) {
            background-color: #f5f5f5;
        } */

        /*responsive*/

        @media(max-width: 500px) {
            .OnlyPremiumTable .table thead {
                display: none;
            }

            .OnlyPremiumTable .table,
            .OnlyPremiumTable .table tbody,
            .OnlyPremiumTable .table tr,
            .OnlyPremiumTable .table td {
                display: block;
                width: 100%;
            }

            .OnlyPremiumTable .table tr {
                margin-bottom: 15px;
            }

            .OnlyPremiumTable .table td {
                text-align: right;
                padding-left: 50%;
                text-align: right;
                position: relative;
            }

            .OnlyPremiumTable .table td::before {
                content: attr(data-label);
                position: absolute;
                left: 0;
                width: 50%;
                padding-left: 15px;
                font-size: 13px;
                font-weight: bold;
                text-align: left;
            }
        }
        
        .nav-pills .nav-link.active, .nav-pills .show>.nav-link {
		    color: #fff; 
		    background-color: #261e6a;
		}
		.flex-column a{
			color: #261e6a;
			font-weight: bold;
		}
		
		.submenus a{
			color: #261e6a;
			font-weight: bold;
		}
    </style>
</head>
<body>
	<div id="loading" style="width:100%">
	   <img id="loading-image" src="${pageContext.request.contextPath}/images/madisonSymbolLogo.png"/>
	</div>
	<div class="container QuotationInformationPage mt-5">
    	<div class="Card_Parent  mt-4">
           	<div class="card card-1">
                <h4><s:label value="Success Message"/></h4>
                <hr>
                <div class="row">
                    <div class="col-md-12 col-12" style="color: red;">
						<s:actionmessage style="color:green;"/>
                    </div>
                </div>
           	</div>
        </div>
    </div>
    <s:form name="msgHome" id="msgHome" theme="simple">
    	<s:hidden name="menuType" id="menuType"/>
    </s:form>
    <div class="row mt-4" align="center">
        <div class="col-md-5 col-5 col-sm-5">
        </div>
        <div class="col-md-2 col-2 col-sm-2">
        	<a class="btn btn-primary btn-block" style="cursor: pointer" id="bpSumbitBtnId" onclick="fnsubmit('initReport.action');hideBtn('bpSumbitBtnId');">Proceed</a>
        </div>
        <div class="col-md-5 col-5 col-sm-5">
        </div>
    </div>
    <SCRIPT type="text/javascript">
    	
		function fnsubmit(action) {
			try{
				document.msgHome.menuType.value='QE';
				document.msgHome.action='${pageContext.request.contextPath}/'+action;
				document.msgHome.submit();
			}catch(err){
				console.log(err);
			}
		}
		
		function hideBtn(id){
			try{
				document.getElementById(''+id).style.display = 'none';
			}catch(err){
				console.log(err);
			}
		}
	</SCRIPT>
</body>
</html>