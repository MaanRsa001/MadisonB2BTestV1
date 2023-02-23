<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
	<link href="css/table-design.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/highcharts.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/exporting.js"></script>
	<script language="JavaScript">
		function datealert(dtdiff,flag) {
			if(flag==0) {
				if(dtdiff>=-10 & dtdiff<=-1) {
				 alert("You Must Change Your Password After  "+(Math.abs(dtdiff)-1)+" Days");;
				}
			}
		}
	</script>
	<style type="text/css">			
		.ui-datepicker-trigger {
			width: 10%;
			float: right;
		}
		.sblue {
			width: 30px;
			height: 10px;
			background: #2f7ed8;
		}
		.sblack {
			width: 10px;
			height: 10px;
			background: #0d233a;
		}
		.sgreen {
			width: 10px;
			height: 10px;
			background: #8bbc21;
		}
		.sorange{
			width: 10px;
			height: 10px;
			background: #910000;
		}			
		.svilot {
			width: 10px;
			height: 10px;
			background: #1aadce;
		}
		.spink {
			width: 10px;
			height: 10px;
			background: #492970;
		}
	</style>
</head>
<%
int flag=0;

String dtdiff=request.getParameter("dtdiff")==null?"":request.getParameter("dtdiff");
if(session.getAttribute("collections")!=null)
	session.removeAttribute("collections");
%>
<body onLoad="datealert('<%=dtdiff%>','<%=flag%>')">
	<s:form name="form1" method="post" action="" theme="simple">
		<div class="row">
			<s:iterator value="dashBoard"></s:iterator>
			<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
				<script type="text/javascript">
					$(function () {
					    $('#quoteChart').highcharts({
					        chart: {
					        	plotBackgroundColor: '#FFFFFF',
					            margin: [0, 0, 0, 0],
					            plotShadow: false
					        },
					        title: {
					            text: 'Quote Detail',
					            align: 'center',
					            verticalAlign: 'middle',
					            y: 50
					        },
					        tooltip: {
					            pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
					        },
					        exporting: {
								enabled: false
							},
							credits: {
					            enabled: false
					        },
					        plotOptions: {
					            pie: {
					                dataLabels: {
					                    enabled: true,
					                    distance: -50,
					                    style: {
					                        fontWeight: 'bold',
					                        color: 'white',
					                        textShadow: '0px 1px 2px black'
					                    }
					                },
					                startAngle: -90,
					                endAngle: 90,
					                center: ['50%', '75%']
					            }
					        },
					        series: [{
					            type: 'pie',
					            name: 'Quote Detail',
					            innerSize: '50%',
					            data: [
					                {
					                    name: 'Total Quote',
					                    y:<s:property value="noOfQuote"/>,
					                    dataLabels: {
					                        enabled: false
					                    }
					                },
					                {
					                    name: 'Customer Linked Quote',
					                    y:<s:property value="customerLinkedQuote"/>,
					                    dataLabels: {
					                        enabled: false
					                    }
					                },
					                {
					                    name: 'Policy Accepted',
					                    y:<s:property value="policyAccept"/>,
					                    dataLabels: {
					                        enabled: false
					                    }
					                },
					                {
					                    name: 'Policy Reject',
					                    y:<s:property value="policyReject"/>,
					                    dataLabels: {
					                        enabled: false
					                    }
					                }
					            ]
					        }]
					    });
				    });
				</script>
				<div id="quoteChart" align="center" style="width: 100%; height: 200px;"></div> <br/><br/>
				<table width="100%" class="footable">
					<thead>
					<tr>
					<th colspan="2">Quote Information</th>
					</tr>
					</thead>					
					<tbody>
					<tr>
						<td>Total Quote</td>
						<td><span class="sblue">&nbsp;&nbsp;&nbsp;</span> &nbsp;&nbsp;&nbsp; <s:property value="noOfQuote"/>  </td>
					</tr>
					<tr>
						<td>Customer Linked Quote</td>
						<td><span class="sblack">&nbsp;&nbsp;&nbsp;</span> &nbsp;&nbsp;&nbsp; <s:property value="customerLinkedQuote"/>  </td>
					</tr>
					<tr>
						<td>Policy Accept</td>
						<td><span class="sgreen">&nbsp;&nbsp;&nbsp;</span> &nbsp;&nbsp;&nbsp; <s:property value="policyAccept"/>  </td>
					</tr>
					<tr>
						<td>Policy Reject</td>
						<td><span class="sorange">&nbsp;&nbsp;&nbsp;</span>&nbsp;&nbsp;&nbsp;<s:property value="policyReject"/> </td>
					</tr>
					</tbody>
				</table>
			</div>
			<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
				<script type="text/javascript">
					$(function () {
					    $('#paymentProcess').highcharts({
					        chart: {
					        	plotBackgroundColor: '#FFFFFF',
					            margin: [0, 0, 0, 0],
					            plotShadow: false
					        },
					        title: {
					            text: 'Payment Process',
					            align: 'center',
					            verticalAlign: 'middle',
					            y: 50
					        },
					        tooltip: {
					            pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
					        },
					        exporting: {
								enabled: false
							},
							credits: {
					            enabled: false
					        },
					        plotOptions: {
					            pie: {
					                dataLabels: {
					                    enabled: true,
					                    distance: -50,
					                    style: {
					                        fontWeight: 'bold',
					                        color: 'white',
					                        textShadow: '0px 1px 2px black'
					                    }
					                },
					                startAngle: -90,
					                endAngle: 90,
					                center: ['50%', '75%']
					            }
					        },
					        series: [{
					            type: 'pie',
					            name: 'Payment Process',
					            innerSize: '50%',
					            data: [
					                {
					                    name: 'Credit Control Pending',
					                    y: <s:property value="ccPending"/>,
					                    dataLabels: {
					                        enabled: false
					                    }
					                },
					                {
					                    name: 'Surveyor Pending',
					                    y: <s:property value="ssPending"/>,
					                    dataLabels: {
					                        enabled: false
					                    }
					                },
					                {
					                    name: 'Under Writer Pending',
					                    y: <s:property value="uwPending"/>,
					                    dataLabels: {
					                        enabled: false
					                    }
					                }
					            ]
					        }]
					    });
				    });
				</script>
				<div id="paymentProcess" align="center" style="width: 100%; height: 200px;"></div> <br/><br/>
				<table width="100%" class="footable">
					<thead>
					<tr>
						<th colspan="2">Payment Process Detail</th>
					</tr>
					</thead>					
					<tbody>
					<tr>
						<td>Credit Controller Pending</td>
						<td><span class="sblue">&nbsp;&nbsp;&nbsp;</span>&nbsp;&nbsp;&nbsp;<s:property value="ccPending"/></td>
					</tr>
					<tr>
						<td>Surveyor Pending</td>
						<td><span class="sblack">&nbsp;&nbsp;&nbsp;</span>&nbsp;&nbsp;&nbsp;<s:property value="ssPending"/></td>
					</tr>
					<tr>
						<td>Under Writer Pending</td>
						<td><span class="sgreen">&nbsp;&nbsp;&nbsp;</span>&nbsp;&nbsp;&nbsp;<s:property value="uwPending"/></td>
					</tr>
					</tbody>
				</table>
			</div>
		</div>		
	</s:form>
</body>
</html>