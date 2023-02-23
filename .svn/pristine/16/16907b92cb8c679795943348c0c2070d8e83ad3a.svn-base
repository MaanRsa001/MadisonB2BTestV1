package com.maan.Office.Controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.maan.Office.DAO.SubCoverageBean;

public class SubCoverageController extends HttpServlet {

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
		RequestDispatcher dispatcher = null;

		if (requestForm.equalsIgnoreCase("subCoverage")) {

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
						.getRequestDispatcher("OfficeConfig/admin_sub_coverage.jsp");
			} else {
				dispatcher = request
						.getRequestDispatcher("OfficeConfig/admin_sub_main_coverage.jsp");
			}
			if (dispatcher != null)
				dispatcher.forward(request, response);
			return;

		} else if (requestForm.equalsIgnoreCase("subCoverageMain")) {
			System.out.println("Coverage: "+request.getParameter("coverage"));
			dispatcher = request
					.getRequestDispatcher("OfficeConfig/admin_sub_all_coverage.jsp");
			if (dispatcher != null)
				dispatcher.forward(request, response);
			return;

		} else if (requestForm.equalsIgnoreCase("subCoverageInsert")) {

			String productID = request.getParameter("product");
			productID = productID == null ? "" : productID;

			String subCoverageLimit = request.getParameter("subCoverageLimit");
			subCoverageLimit = subCoverageLimit == null ? "" : subCoverageLimit;

			String schemeID = request.getParameter("scheme");
			schemeID = schemeID == null ? "" : schemeID;

			String contentTypeID = request.getParameter("contentType");
			contentTypeID = contentTypeID == null ? "" : contentTypeID;

			String coverageID = request.getParameter("coverage");
			coverageID = coverageID == null ? "" : coverageID;

			String subCoverageID = request.getParameter("subCoverage");
			subCoverageID = subCoverageID == null ? "" : subCoverageID;

			String displayOrder = request.getParameter("displayOrder");
			displayOrder = displayOrder == null ? "" : displayOrder;

			String controlType = request.getParameter("controlType");
			controlType = controlType == null ? "" : controlType;

			String coverageLimit = request.getParameter("coverageLimit");
			coverageLimit = coverageLimit == null ? "" : coverageLimit;

			String subRate = request.getParameter("subRate");
			subRate = subRate == null ? "" : subRate;

			String calculationType = request.getParameter("calculationType");
			calculationType = calculationType == null ? "" : calculationType;

			String status = request.getParameter("status");
			status = status == null ? "" : status;

			String effectiveDate = request.getParameter("effectiveDate");
			effectiveDate = effectiveDate == null ? "" : effectiveDate;

			String rsaCode = request.getParameter("rsaCode");
			rsaCode = rsaCode == null ? "" : rsaCode;

			SubCoverageBean coverageBean = new SubCoverageBean();
			coverageBean.setProductID(productID);
			coverageBean.setSchemeID(schemeID);
			coverageBean.setContentTypeID(contentTypeID);
			coverageBean.setCoverageID(coverageID);
			coverageBean.setSubCoverageID(subCoverageID);
			coverageBean.setDisplayOrder(displayOrder);
			coverageBean.setControlType(controlType);
			coverageBean.setCoverageLimit(coverageLimit);
			coverageBean.setSubRate(subRate);
			coverageBean.setCalculationType(calculationType);
			coverageBean.setStatus(status);
			coverageBean.setEffectiveDate(effectiveDate);
			coverageBean.setRsaCode(rsaCode);
			coverageBean.setSubCoverageLimit(subCoverageLimit);

			String error = "";
			coverageBean.validation();
			error = coverageBean.getError();
			if (error.length() > 0) {
				request.setAttribute("error", error);
				dispatcher = request
						.getRequestDispatcher("OfficeConfig/admin_sub_coverage_insert.jsp");
			} else {
				coverageBean.manipulationData();
				response.setContentType("text/html");
				PrintWriter out = response.getWriter();
				if(calculationType.equalsIgnoreCase("G"))
				{
					dispatcher = request.getRequestDispatcher("OfficeConfig/admin_grid_details.jsp?coverage="+coverageID+"&subcoverage="+subCoverageID+"&contentType="+contentTypeID+"&scheme_id="+schemeID+"&product_id=30");
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
		}

	}

}
