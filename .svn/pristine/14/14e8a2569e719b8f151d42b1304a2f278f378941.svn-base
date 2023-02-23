package com.maan.Office.Controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.maan.DBCon.DBConnectionStatus;
import com.maan.Office.DAO.OfficeShieldBean;
import com.maan.services.util.dataCollection;

public class CustomerInformationOS extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ProceesRequest(request, response);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ProceesRequest(request, response);
	}

	public void ProceesRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		if (session.getAttribute("ses") == null) {
			response.sendRedirect(request.getContextPath()
					+ "/login/error_messg.jsp");
			return;
		}

		String path = request.getContextPath();

		/*RequestDispatcher check = request.getRequestDispatcher("login/sessionsCheckNormal.jsp");
		check.include(request, response);*/

		// SESSION CHECK

		String usrModeSC = (String) session.getAttribute("userLoginMode") == null ? ""
				: (String) session.getAttribute("userLoginMode");
		DBConnectionStatus.statusStatic = usrModeSC;
		// END OF SESSION CHECK

		String customerId = request.getParameter("customerId");
		String branch = (String) session.getAttribute("LoginBranchCode");
		String productId = (String) session.getAttribute("product_id");
		String cid = (String) session.getAttribute("cid1");

		customerId = customerId == null ? "" : customerId;
		branch = branch == null ? "020" : branch;
		productId = productId == null ? "" : productId;
		cid = cid == null ? "" : cid;

		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		OfficeShieldBean cust = new OfficeShieldBean();
		dataCollection data = new dataCollection();
		String result[][] = new String[0][0];
		String emirates[][] = new String[0][0];
		String countrys[][] = new String[0][0];
		String optE = "";
		String optC = "";
		String cityStatus1 = "";
		String city1 = "";
		int tabIndex = 1;
		boolean flag = false;
		try {
			emirates = cust.emirateCollection(cid);
			countrys = data.countryCollectionWithId();
			if (customerId != null && customerId.length() > 0) {
				result = cust.getCustomerForOS(customerId);
				if (result.length > 0) {
					result[0][2] = result[0][2] == null ? "" : result[0][2];
					result[0][11] = result[0][11] == null ? " " : result[0][11];
					result[0][13] = result[0][13] == null ? "" : result[0][13];
					result[0][14] = result[0][14] == null ? "" : result[0][14];
					result[0][15] = result[0][15] == null ? "" : result[0][15];
					result[0][10] = result[0][10] == null ? "" : result[0][10];
					String phoneNO = "";
					phoneNO = result[0][8] != null ? result[0][8]
							: result[0][7] != null ? result[0][7] : "";

					if (result[0][14].equalsIgnoreCase("select"))
						result[0][14] = "UNITED ARAB EMIRATES";

					out.println("<TABLE>");
					out.println("<TR class=\"\">");
					out.println("<Td >Company Name&nbsp;: </Td>");
					out
							.println("<TD><input type='text' readonly name='companyName' value=\" "
									+ result[0][2]
									+ " \" size='25' maxlength='100' tabindex=\""
									+ (tabIndex++) + "\" > &nbsp;");
					out
							.println("<input type=\"button\" name='chooseCustomer' value='.....' onClick=\"return Customer('C')\" tabindex=\""
									+ (tabIndex++) + "\"></TD>");
					out.println("</TR>");
					out.println("<TBODY>");
					out.println("<TR class=\"\">");
					out
							.println("<td width='182'>Registered Address in UAE &nbsp;:</td>");
					out.println("<TD width='218'><label>");
					out
							.println("<input type='text' readonly name='address1' value=\" "
									+ result[0][11]
									+ " \" size='25' maxlength='45' tabindex=\""
									+ (tabIndex++) + "\">");
					out.println("</label></TD>");
					out.println("</TR>");
					out.println("<TR class=\"\">");
					out.println("<td>City&nbsp;:</td>");
					out
							.println("<TD><input type='text' name='emirate1' readonly \""
									+ cityStatus1
									+ "\" value =\""
									+ result[0][13].trim()
									+ " \" size='25' maxLength='12' tabindex=\""
									+ (tabIndex++) + "\" ></TD>");
					out.println("</TR>");
					out.println("<TR class=\"\">");
					out.println("<td>P.O.Box&nbsp;: </td>");
					out
							.println("<TD><input type='text' readonly value=\" "
									+ result[0][15]
									+ " \" name='poBox1' maxLength='6' size='25'  tabindex=\""
									+ (tabIndex++) + "\" > </TD></TR>");
					out.println("<TR>");
					out.println("<Td>Country&nbsp;:</Td>");
					out.println("<TD>");
					out
							.println("<input type='text' readonly name='country1' value=\" "
									+ result[0][14]
									+ " \" maxLength='25' tabindex=\""
									+ (tabIndex++) + "\" size='25' ></TD>");
					out.println("</TR>");
					out.println("<TR>");
					out.println("<Td>Phone No&nbsp;:</Td>");
					out.println("<TD>");
					out
							.println("<input type='text' readonly name='phoneno1' value=\" "
									+ phoneNO
									+ " \" maxLength='25' tabindex=\""
									+ (tabIndex++) + "\" size='25' ></TD>");
					out.println("</TR>");
					out.println("<TR>");
					out.println("<Td>E-Mail&nbsp;:</Td>");
					out.println("<TD>");
					out
							.println("<input type='text' readonly name='email1' value=\" "
									+ result[0][10]
									+ " \" maxLength='25' tabindex=\""
									+ (tabIndex++) + "\" size='25' ></TD>");
					out.println("</TR>");
					out.println("</TBODY>");
					out.println("</TABLE>");
				}
			}
		} catch (Exception ex) {
			out
					.println("CustomerSelection Information In OfficeShield Controller");
			ex.printStackTrace();
		}
	}

} // Class

