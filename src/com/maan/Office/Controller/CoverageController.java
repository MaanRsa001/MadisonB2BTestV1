package com.maan.Office.Controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.maan.Office.DAO.CoverageBean;

public class CoverageController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		transfer(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		transfer(request, response);
	}

	private void transfer(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String requestForm = request.getParameter("requestForm");
		requestForm = requestForm == null ? "" : requestForm;
		System.out.println("requestForm: "+requestForm);
		RequestDispatcher dispatcher = null;

		if (requestForm.equalsIgnoreCase("coverageMain")) {

			String schemeID = request.getParameter("scheme");
			schemeID = schemeID == null ? "" : schemeID;

			String contentTypeID = request.getParameter("contentType");
			contentTypeID = contentTypeID == null ? "" : contentTypeID;

			String error = "";
			if (schemeID.equals("")
					|| schemeID.trim().equalsIgnoreCase("select")
					|| schemeID.equalsIgnoreCase("null")) {
				error = "* Select Scheme<br>";
			}
			if (contentTypeID.equals("")
					|| contentTypeID.trim().equalsIgnoreCase("select")
					|| contentTypeID.equalsIgnoreCase("null")) {
				error = "* Select Content Type<br>";
			}

			if (error.length() > 0) {
				request.setAttribute("error", error);
				dispatcher = request
						.getRequestDispatcher("OfficeConfig/admin_coverage.jsp");
			} else {

				dispatcher = request
						.getRequestDispatcher("OfficeConfig/admin_coverage_data.jsp");
			}
			if (dispatcher != null)
				dispatcher.forward(request, response);
			return;

		} else if (requestForm.equalsIgnoreCase("coverageInsert")) {

			String schemeID = request.getParameter("scheme");
			schemeID = schemeID == null ? "" : schemeID;

			String contentTypeID = request.getParameter("contentType");
			contentTypeID = contentTypeID == null ? "" : contentTypeID;

			String coverageType = request.getParameter("coverageType");
			coverageType = coverageType == null ? "" : coverageType;

			String uploadOption = request.getParameter("uploadOption");
			uploadOption = uploadOption == null ? "" : uploadOption;

			String displayOrder = request.getParameter("displayOrder");
			displayOrder = displayOrder == null ? "" : displayOrder;

			String controlType = request.getParameter("controlType");
			controlType = controlType == null ? "" : controlType;

			String coverageLimit = request.getParameter("coverageLimit");
			coverageLimit = coverageLimit == null ? "" : coverageLimit;

			String excess = request.getParameter("excess");
			excess = excess == null ? "" : excess;

			String baseRate = request.getParameter("baseRate");
			baseRate = baseRate == null ? "" : baseRate;

			String calculationStatus = request
					.getParameter("calculationStatus");
			calculationStatus = calculationStatus == null ? ""
					: calculationStatus;

			String calculationType = request.getParameter("calculationType");
			calculationType = calculationType == null ? "" : calculationType;

			String status = request.getParameter("status");
			status = status == null ? "" : status;

			String helpContents = request.getParameter("helpContent");
			helpContents = helpContents == null ? "" : helpContents;

			String coverageName = request.getParameter("coverage");
			coverageName = coverageName == null ? "" : coverageName;

			String effectiveDate = request.getParameter("effectiveDate");
			effectiveDate = effectiveDate == null ? "" : effectiveDate;

			String rsaCode = request.getParameter("rsaCode");
			rsaCode = rsaCode == null ? "" : rsaCode;

			String sumControlType = request.getParameter("sumControlType");
			sumControlType = sumControlType == null ? "" : sumControlType;

			String sumCoverageLimit = request.getParameter("sumCoverageLimit");
			sumCoverageLimit = sumCoverageLimit == null ? "" : sumCoverageLimit;
			
			String minSumLimit = request.getParameter("minSumLimit");
			minSumLimit = minSumLimit == null ? "" : minSumLimit;
			
			String error = "";

			CoverageBean coverageBean = new CoverageBean();
			coverageBean.setBaseRate(baseRate);
			coverageBean.setCalculationStatus(calculationStatus);
			coverageBean.setCalculationType(calculationType);
			coverageBean.setContentTypeID(contentTypeID);
			coverageBean.setCoverageLimit(coverageLimit);
			coverageBean.setControlType(controlType);
			coverageBean.setCoverageNameID(coverageName);
			coverageBean.setCoverageType(coverageType);
			coverageBean.setDisplayOrder(displayOrder);
			coverageBean.setExcess(excess);
			coverageBean.setHelpContents(helpContents);
			coverageBean.setSchemeID(schemeID);
			coverageBean.setStatus(status);
			coverageBean.setUploadOption(uploadOption);
			coverageBean.setEffectiveDate(effectiveDate);
			coverageBean.setRsaCode(rsaCode);
			coverageBean.setProductID("30");
            coverageBean.setSumControlType(sumControlType);
			coverageBean.setSumCoverageLimit(sumCoverageLimit);
			coverageBean.setMinSumLimit(minSumLimit);
			coverageBean.validation();
			error = coverageBean.getError();

			if (error.length() > 0) {
				request.setAttribute("error", error);
				dispatcher = request
						.getRequestDispatcher("OfficeConfig/admin_coverage_insert.jsp");
			} else {
				coverageBean.manipulationData();
				response.setContentType("text/html");
				PrintWriter out = response.getWriter();
					if(calculationType.equalsIgnoreCase("G"))
					{
						dispatcher = request.getRequestDispatcher("OfficeConfig/admin_grid_details.jsp?coverage="+coverageName+"&contentType="+contentTypeID+"&scheme_id="+schemeID+"&product_id=30");
					}else
					{
						out.println("<HTML>");
						out.println("  <HEAD><script type=\"text/javascript\">function fnSubmit(){window.close();window.opener.location.reload(true);}</script></HEAD>");
						out.println("  <BODY onload=\"fnSubmit()\">");
						out.println("  </BODY>");
						out.println("</HTML>");
						out.flush();
						out.close();
					}
			}
			if (dispatcher != null)
				dispatcher.forward(request, response);
			return;
		} else if (requestForm.equalsIgnoreCase("gridInsert")) {
			CoverageBean coverageBean = new CoverageBean();
			bean.validator v=new bean.validator();
			
			String add = request.getParameter("addMore") == null ? "3": request.getParameter("addMore");
			int addMore=Integer.parseInt(add);
			
			String schemeID = request.getParameter("scheme");
			schemeID = schemeID == null ? "" : schemeID;
			
			String proId = request.getParameter("product_id");
			proId = proId == null ? "30" : proId;
			System.out.println("proId: "+proId);
			String contentTypeID = request.getParameter("contentType");
			contentTypeID = contentTypeID == null ? "" : contentTypeID;

			String coverid = request.getParameter("coverage") == null ? "": request.getParameter("coverage");
			String subcoverid = request.getParameter("subcoverage") == null ? "0": request.getParameter("subcoverage");
			
			String error = "";
			String err;
			String values="";
			String[][] values1=new String[addMore][7];
			String amendId=coverageBean.getMaxAmendId(proId, schemeID, contentTypeID, coverid, subcoverid);
			java.util.ArrayList val=new java.util.ArrayList();
			for(int k=0; k<addMore; k++)
			{
				values+=",'"+(request.getParameter("startSum"+k)==null?"":request.getParameter("startSum"+k))+"'";
				values+=",'"+(request.getParameter("endSum"+k)==null?"":request.getParameter("endSum"+k))+"'";
				values+=",'"+(request.getParameter("baseRate"+k)==null?"":request.getParameter("baseRate"+k))+"'";
				values+=",'"+(request.getParameter("minPremium"+k)==null?"":request.getParameter("minPremium"+k))+"'";
				values+=",'"+amendId+"'";
				values+=",'"+(request.getParameter("status"+k)==null?"":request.getParameter("status"+k))+"'";
				values+=",'"+(request.getParameter("remarks"+k)==null?"":request.getParameter("remarks"+k))+"'";
				values+=",to_date('"+(request.getParameter("effectiveDate"+k)==null?"":request.getParameter("effectiveDate"+k))+"', 'dd-mm-yy')";
				values+=",to_date('"+(request.getParameter("effectiveDate"+k)==null?"":request.getParameter("effectiveDate"+k))+"', 'dd-mm-yy')+365";
				values+=",'"+(request.getParameter("calculationType"+k)==null?"":request.getParameter("calculationType"+k))+"'";
				System.out.println("Values: "+values);
				
				
				values1[k][0]=request.getParameter("minPremium"+k)==null?"":request.getParameter("minPremium"+k);
				values1[k][1]=request.getParameter("startSum"+k)==null?"":request.getParameter("startSum"+k);
				values1[k][2]=request.getParameter("endSum"+k)==null?"":request.getParameter("endSum"+k);
				values1[k][3]=request.getParameter("baseRate"+k)==null?"":request.getParameter("baseRate"+k);
				values1[k][4]=request.getParameter("calculationType"+k)==null?"":request.getParameter("calculationType"+k);
				values1[k][5]=request.getParameter("status"+k)==null?"":request.getParameter("status"+k);
				values1[k][6]=request.getParameter("effectiveDate"+k)==null?"":request.getParameter("effectiveDate"+k);
				System.out.println("Values[][]: "+values1.length);
				
				//Validation goes here
				if(!(values1[k][0].equals("")&&values1[k][1].equals("")&&values1[k][2].equals("")
						&&values1[k][3].equals("")&&values1[k][4].equals("")&&values1[k][5].equals("")&&values1[k][6].equals("")))
				{
					val.add(values);
					/*err=v.validInteger(values1[k][0]);
					if((err!=null && err.length()>0)||values1[k][0].equals(""))
					{
						error+=err+" Minimum Premim in Row "+(k+1)+"</br>";
					}*/
					err=v.validInteger(values1[k][1]);
					if((err!=null && err.length()>0)||values1[k][1].equals(""))
					{
						error+=err+" Start Sum Insured in Row "+(k+1)+"</br>";
					}
					err=v.validInteger(values1[k][2]);
					if((err!=null && err.length()>0)||values1[k][2].equals(""))
					{
						error+=err+" End Sum Insured in Row "+(k+1)+"</br>";
					}
					if(values1[k][1].length()>0 && values1[k][2].length()>0)
					{
						try{
						int val1=Integer.parseInt(values1[k][1]);
						int val2=Integer.parseInt(values1[k][2]);
							if(val2<val1)
							{
								error+=" End Sum Insured is less than Start Sum Insured in Row "+(k+1)+"</br>";
							}
						}catch(Exception e){
							error+="Invalid End Sum Insured in Row "+(k+1)+"</br>";
						}
					}
					if (values1[k][3].length() == 0) {
						error += "Invalid Bass Rate in Row "+(k+1)+"</br>";
					} else {
						try {
							Double.parseDouble(values1[k][3]);
						} catch (Exception e) {
							error += "Invalid Bass Rate in Row "+(k+1)+"</br>";
						}
					}
					
					if(values1[k][4].equalsIgnoreCase(""))
					{
						error+="Select Calculation Type in Row "+(k+1)+"</br>";
					}
					if(values1[k][5].equalsIgnoreCase(""))
					{
						error+="Select Status in Row "+(k+1)+"</br>";
					}
					if(values1[k][6].equalsIgnoreCase(""))
					{
						error+="Select Effective Date in Row "+(k+1)+"</br>";
					}
				}
				values="";
			}
			
			if (error.length() > 0) {
				System.out.println("error:2 "+error);
				request.setAttribute("error", error);
				request.setAttribute("gridDetails", values1);
				dispatcher = request.getRequestDispatcher("OfficeConfig/admin_grid_details.jsp?coverage="+coverid+"&contentType="+contentTypeID+"&scheme_id="+schemeID+"&product_id="+proId+"&addMore="+addMore);
			} else {
				for(int k=0; k<val.size(); k++)
				{
					coverageBean.insertGridDetails(proId, schemeID, contentTypeID, coverid, subcoverid,  (String)val.get(k));
				}
				response.setContentType("text/html");
				PrintWriter out = response.getWriter();
				out.println("<HTML>");
				if(proId.equalsIgnoreCase("65"))
				out.println("  <HEAD><script type=\"text/javascript\">function fnSubmit(){window.close();}</script></HEAD>");
				else
				out.println("  <HEAD><script type=\"text/javascript\">function fnSubmit(){window.close();window.opener.location.reload(true);}</script></HEAD>");
				out.println("  <BODY onload=\"fnSubmit()\">");
				out.println("  </BODY>");
				out.println("</HTML>");
				out.flush();
				out.close();
			}
			
		} else if (requestForm.equalsIgnoreCase("gridAddMore")) {
			System.out.println("GridAddMore - Enter");
			String proId = (String) request.getParameter("product_id");
			proId = proId == null ? "0" : proId;
			
			String schemeId = (String) request.getParameter("scheme_id");
			schemeId = schemeId == null ? "0" : schemeId;

			String contentTypeID = request.getParameter("contentType");
			contentTypeID = contentTypeID == null ? "0" : contentTypeID;

			String coverageID = request.getParameter("coverage");
			coverageID = coverageID == null ? "0" : coverageID;
			
			String add = request.getParameter("addMore") == null ? "3": request.getParameter("addMore");
			int addMore=Integer.parseInt(add);
			addMore++;
			String[][] values=new String[addMore][7];
			for(int k=0; k<addMore; k++)
			{
				values[k][0]=request.getParameter("minPremium"+k)==null?"":request.getParameter("minPremium"+k);
				values[k][1]=request.getParameter("startSum"+k)==null?"":request.getParameter("startSum"+k);
				values[k][2]=request.getParameter("endSum"+k)==null?"":request.getParameter("endSum"+k);
				values[k][3]=request.getParameter("baseRate"+k)==null?"":request.getParameter("baseRate"+k);
				values[k][4]=request.getParameter("calculationType"+k)==null?"":request.getParameter("calculationType"+k);
				values[k][5]=request.getParameter("status"+k)==null?"":request.getParameter("status"+k);
				values[k][6]=request.getParameter("effectiveDate"+k)==null?"":request.getParameter("effectiveDate"+k);
				System.out.println("Values[][]: "+values.length);
			}
			request.setAttribute("gridDetails", values);
			dispatcher = request.getRequestDispatcher("OfficeConfig/admin_grid_details.jsp?coverage="+coverageID+"&contentType="+contentTypeID+"&scheme_id="+schemeId+"&product_id="+proId+"&addMore="+addMore);
		}else if (requestForm.equalsIgnoreCase("configInsert")) {
			CoverageBean coverageBean = new CoverageBean();
			bean.validator v=new bean.validator();
			
			String add = request.getParameter("addMore") == null ? "3": request.getParameter("addMore");
			int addMore=Integer.parseInt(add);
			
			String schemeID = request.getParameter("scheme");
			schemeID = schemeID == null ? "" : schemeID;
			
			String proId = request.getParameter("product_id");
			proId = proId == null ? "30" : proId;
			System.out.println("proId: "+proId);
			String contentTypeID = request.getParameter("contentType");
			contentTypeID = contentTypeID == null ? "" : contentTypeID;

			String coverid = request.getParameter("coverage") == null ? "": request.getParameter("coverage");
			String subcoverid = request.getParameter("subcoverage") == null ? "0": request.getParameter("subcoverage");
			
			String error = "";
			String err;
			String values="";
			String[][] values1=new String[addMore][12];
			String amendId=coverageBean.getMaxAmendId1(proId, schemeID, contentTypeID, coverid, subcoverid);
			java.util.ArrayList val=new java.util.ArrayList();
			for(int k=0; k<addMore; k++)
			{
				values+=",'"+(request.getParameter("displayName"+k)==null?"":request.getParameter("displayName"+k))+"'";
				values+=",'"+(request.getParameter("controlType"+k)==null?"":request.getParameter("controlType"+k))+"'";
				values+=",'"+(request.getParameter("displayOrder"+k)==null?"":request.getParameter("displayOrder"+k))+"'";
			 /* values+=",'"+(request.getParameter("dropdownKey"+k)==null?"":request.getParameter("dropdownKey"+k))+"'";
				values+=",'"+(request.getParameter("dropdownValue"+k)==null?"":request.getParameter("dropdownValue"+k))+"'"; */
				values+=",'"+""+"'";
				values+=",'"+""+"'";
				values+=",'"+(request.getParameter("dropdownTable"+k)==null?"":request.getParameter("dropdownTable"+k))+"'";
				values+=",'"+(request.getParameter("validationType"+k)==null?"":request.getParameter("validationType"+k))+"'";
				values+=",'"+(request.getParameter("destTable"+k)==null?"":request.getParameter("destTable"+k))+"'";
				values+=",'"+(request.getParameter("destColumn"+k)==null?"":request.getParameter("destColumn"+k))+"'";
				values+=",'"+amendId+"'";
				values+=",'"+(request.getParameter("status"+k)==null?"":request.getParameter("status"+k))+"'";
				values+=",'"+(request.getParameter("mandatory"+k)==null?"":request.getParameter("mandatory"+k))+"'";
				values+=",'"+(request.getParameter("totalSumInsured"+k)==null?"":request.getParameter("totalSumInsured"+k))+"'";
				System.out.println("Values: "+values);
				
				
				values1[k][0]=request.getParameter("displayName"+k)==null?"":request.getParameter("displayName"+k);
				values1[k][1]=request.getParameter("controlType"+k)==null?"":request.getParameter("controlType"+k);
			    values1[k][2]=request.getParameter("displayOrder"+k)==null?"":request.getParameter("displayOrder"+k);
			 /* values1[k][3]=request.getParameter("dropdownKey"+k)==null?"":request.getParameter("dropdownKey"+k); 
				values1[k][4]=request.getParameter("dropdownValue"+k)==null?"":request.getParameter("dropdownValue"+k); */
			    values1[k][3]="";
			    values1[k][4]="";
				values1[k][5]=request.getParameter("dropdownTable"+k)==null?"":request.getParameter("dropdownTable"+k);
				values1[k][6]=request.getParameter("validationType"+k)==null?"":request.getParameter("validationType"+k);
				values1[k][7]=request.getParameter("destTable"+k)==null?"":request.getParameter("destTable"+k);
				values1[k][8]=request.getParameter("destColumn"+k)==null?"":request.getParameter("destColumn"+k);
				values1[k][9]=request.getParameter("status"+k)==null?"":request.getParameter("status"+k);
				values1[k][10]=request.getParameter("mandatory"+k)==null?"":request.getParameter("mandatory"+k);
				values1[k][11]=request.getParameter("totalSumInsured"+k)==null?"":request.getParameter("totalSumInsured"+k);
				System.out.println("Values[][]: "+values1.length);
				
				//Validation goes here
				if(!(values1[k][0].equals("")&&values1[k][1].equals("")&&values1[k][2].equals("")
						&&values1[k][3].equals("")&&values1[k][4].equals("")&&values1[k][5].equals("")&&values1[k][6].equals("")
						&&values1[k][8].equals("") /* &&values1[k][9].equals("")&&values1[k][10].equals("") */ ))
				{
					val.add(values);
					err=v.validString(values1[k][0],4);
					if((err!=null && err.length()>0)||values1[k][0].equals(""))
					{
						error+=err+" Display Name in Row "+(k+1)+"</br>";
					}
					err=v.validString(values1[k][1],4);
					if((err!=null && err.length()>0)||values1[k][1].equals(""))
					{
						error+=err+" Control Type in Row "+(k+1)+"</br>";
					}
					err=v.validInteger(values1[k][2]);
					if((err!=null && err.length()>0)||values1[k][2].equals(""))
					{
						error+=err+" Display Order in Row "+(k+1)+"</br>";
					}
				 /* err=v.validString(values1[k][3],4); */
						
					if("dropdown".equalsIgnoreCase(values1[k][1]))
					{
						/*if(values1[k][3].equals(""))
						{
							error+=err+" Dropdown Key in Row "+(k+1)+"</br>";
						}
						if(values1[k][4].equals(""))
						{
							error+=err+" Dropdown Value in Row "+(k+1)+"</br>";
						}*/
						if(values1[k][5].equals(""))
						{
							//error+=err+" Dropdown Table in Row "+(k+1)+"</br>";
							error+=err+" Dropdown Table in Row "+(k+1)+"</br>";
						}
						
					}
					err=v.validString(values1[k][6],4);
					if((err!=null && err.length()>0)||values1[k][6].equals(""))
					{
						error+=err+" Validation Type in Row "+(k+1)+"</br>";
					}
					if(values1[k][7].equalsIgnoreCase(""))
					{
						error+="Select Destination Table in Row "+(k+1)+"</br>";
					}
					if(values1[k][8].equalsIgnoreCase(""))
					{
						error+="Select Destination Column in Row "+(k+1)+"</br>";
					}
					if(values1[k][9].equalsIgnoreCase(""))
					{
						error+="Select Status in Row "+(k+1)+"</br>";
					}
					if(values1[k][10].equalsIgnoreCase(""))
					{
						error+="Select Mandatory in Row "+(k+1)+"</br>";
					}
					if(values1[k][11].equalsIgnoreCase(""))
					{
						error+="Select Mandatory in Row "+(k+1)+"</br>";
					}
				}
				values="";
			}
			
			if (error.length() > 0) {
				System.out.println("error:2 "+error);
				request.setAttribute("error", error);
				request.setAttribute("configDetails", values1);
				String flag = request.getParameter("flag") == null ? "": request.getParameter("flag");
				dispatcher = request.getRequestDispatcher("OfficeConfig/admin_config_details.jsp?coverage="+coverid+"&contentType="+contentTypeID+"&scheme_id="+schemeID+"&product_id=30&addMore="+addMore+"&flag=true");
			} else {
				for(int k=0; k<val.size(); k++)
				{
					coverageBean.insertConfigDetails(proId, schemeID, contentTypeID, coverid, subcoverid,  (String)val.get(k));
				}
				response.setContentType("text/html");
				PrintWriter out = response.getWriter();
				out.println("<HTML>");
				out.println("  <HEAD><script type=\"text/javascript\">function fnSubmit(){window.close();}</script></HEAD>");
				out.println("  <BODY onload=\"fnSubmit()\">");
				out.println("  </BODY>");
				out.println("</HTML>");
				out.flush();
				out.close();
			}
			
		} else if (requestForm.equalsIgnoreCase("configAddMore") || requestForm.equalsIgnoreCase("dropdown") || requestForm.equalsIgnoreCase("dropdownTable")) {
			System.out.println("configAddMore - Enter");
			CoverageBean coverageBean = new CoverageBean();
			String proId = (String) request.getParameter("product_id");
			proId = proId == null ? "0" : proId;
			
			String schemeId = (String) request.getParameter("scheme_id");
			schemeId = schemeId == null ? "0" : schemeId;

			String contentTypeID = request.getParameter("contentType");
			contentTypeID = contentTypeID == null ? "0" : contentTypeID;

			String coverageID = request.getParameter("coverage");
			coverageID = coverageID == null ? "0" : coverageID;
			
			String add = request.getParameter("addMore") == null ? "3": request.getParameter("addMore");
			String flag = request.getParameter("flag") == null ? "": request.getParameter("flag");
			String error = request.getParameter("error") == null ? "": request.getParameter("error");
			System.out.println("flag: "+flag);
			int addMore=Integer.parseInt(add);
			String row = request.getParameter("row") == null ? "": request.getParameter("row");
			System.out.println("row in cont: "+row);
			if(requestForm.equalsIgnoreCase("configAddMore"))
			{
				addMore++;
			}
			//String[][] tableNames=coverageBean.getTableNames();
			//session.setAttribute("tableNames", tableNames);
			java.util.StringTokenizer st=new java.util.StringTokenizer(row,",");
			/*while(st.hasMoreTokens())
			{
				java.util.StringTokenizer st1=new java.util.StringTokenizer(st.nextToken(),":");
				while(st1.hasMoreTokens())
				{
					String name=st1.nextToken();
					String rowNo=st1.nextToken();
					String[][] dropdownColumnNames=coverageBean.getDropdownColumnNames(name);
					request.setAttribute("dropdownTable"+rowNo, dropdownColumnNames);
				}
			}*/
			
			String[][] values=new String[addMore][12];
			for(int k=0; k<addMore; k++)
			{
				values[k][0]=request.getParameter("displayName"+k)==null?"":request.getParameter("displayName"+k);
				values[k][1]=request.getParameter("controlType"+k)==null?"":request.getParameter("controlType"+k);
				values[k][2]=request.getParameter("displayOrder"+k)==null?"":request.getParameter("displayOrder"+k);
				values[k][3]=request.getParameter("dropdownKey"+k)==null?"":request.getParameter("dropdownKey"+k);
				values[k][4]=request.getParameter("dropdownValue"+k)==null?"":request.getParameter("dropdownValue"+k);
				values[k][5]=request.getParameter("dropdownTable"+k)==null?"":request.getParameter("dropdownTable"+k);
				values[k][6]=request.getParameter("validationType"+k)==null?"":request.getParameter("validationType"+k);
				values[k][7]=request.getParameter("destTable"+k)==null?"":request.getParameter("destTable"+k);
				values[k][8]=request.getParameter("destColumn"+k)==null?"":request.getParameter("destColumn"+k);
				values[k][9]=request.getParameter("status"+k)==null?"":request.getParameter("status"+k);
				values[k][10]=request.getParameter("mandatory"+k)==null?"":request.getParameter("mandatory"+k);
				values[k][11]=request.getParameter("totalSumInsured"+k)==null?"":request.getParameter("totalSumInsured"+k);
			}
			request.setAttribute("configDetails", values);
			request.setAttribute("error", error);
			dispatcher = request.getRequestDispatcher("OfficeConfig/admin_config_details.jsp?coverage="+coverageID+"&contentType="+contentTypeID+"&scheme_id="+schemeId+"&product_id=30&addMore="+addMore+"&row="+row+"&flag="+flag);
		}else if (requestForm.equalsIgnoreCase("formulaInsert")) {
			CoverageBean coverageBean = new CoverageBean();
			bean.validator v=new bean.validator();
			String formulaDetails[][]=new String[1][3];
			String schemeID = request.getParameter("scheme");
			schemeID = schemeID == null ? "" : schemeID;
			
			String proId = request.getParameter("product_id");
			proId = proId == null ? "30" : proId;
			System.out.println("proId: "+proId);
			
			String coverid = request.getParameter("coverage") == null ? "": request.getParameter("coverage");
			String subcoverid = request.getParameter("subcoverage") == null ? "0": request.getParameter("subcoverage");
			String branchCode = request.getParameter("branchCode") == null ? "0": request.getParameter("branchCode");
			String error = "";
			String msg="";
			boolean flag=true;
			String formula=request.getParameter("formula")==null?"":request.getParameter("formula");
			String remarks=request.getParameter("remarks")==null?"":request.getParameter("remarks");
			String status=request.getParameter("status")==null?"":request.getParameter("status");
			formulaDetails[0][0]=formula;
			formulaDetails[0][1]=remarks;
			formulaDetails[0][2]=status;
			//Validation goes here
				if(!(formula.equals("")&&remarks.equals("")&&status.equals("")))
				{
					
					if(formula.equalsIgnoreCase(""))
					{
						error+="Invalid Formula </br>";
					}
					if(status.equalsIgnoreCase(""))
					{
						error+="Select Status</br>";
					}
					
				}else
				{
					flag=false;
				}
			
			if (error.length() > 0) {
				System.out.println("error:2 "+error);
				request.setAttribute("error", error);
				request.setAttribute("formulaDetails", formulaDetails);
				dispatcher = request.getRequestDispatcher("OfficeConfig/admin_formula_details.jsp?coverage="+coverid+"&scheme_id="+schemeID+"&product_id=30&msg=Saved Successfully!!");
			} else {
				if(flag)
				msg=coverageBean.insertFormulaDetails(proId, schemeID, coverid,branchCode, formula, remarks, status);
				response.setContentType("text/html");
				request.setAttribute("formulaDetails", formulaDetails);
				dispatcher = request.getRequestDispatcher("OfficeConfig/admin_formula_details.jsp?coverage="+coverid+"&scheme_id="+schemeID+"&product_id=30&msg="+msg);
			}
			
		} 
		if (dispatcher != null)
			dispatcher.forward(request, response);
		return;
	}

}
