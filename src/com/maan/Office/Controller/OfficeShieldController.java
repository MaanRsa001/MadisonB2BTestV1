package com.maan.Office.Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import proj.date.DateFunction;

import com.maan.DBCon.DBConnectionStatus;
import com.maan.Office.DAO.OfficeShieldBean;
import com.maan.common.LogManager;
import com.maan.common.util.StringUtil;
import com.maan.services.util.ValidationFormat;

public class OfficeShieldController extends HttpServlet {
	public void doPost(final HttpServletRequest request,final  HttpServletResponse response)
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

		RequestDispatcher dispatcher = null;
		String path = request.getContextPath();

		/*RequestDispatcher check = request.getRequestDispatcher("/login/sessionsCheckNormal.jsp");
		check.include(request, response);*/

		// SESSION CHECK
		String usrModeSC = (String) session.getAttribute("userLoginMode") == null ? ""
				: (String) session.getAttribute("userLoginMode");
		DBConnectionStatus.statusStatic = usrModeSC;
		// END OF SESSION CHECK

		String customerId = request.getParameter("customerId");
		System.out.println("customerId: "+customerId);
		String sessionId = (String) session.getAttribute("ses");
		String branch = (String) session.getAttribute("LoginBranchCode");
		String productId = (String) session.getAttribute("product_id");
		String schemeId = (String) session.getAttribute("scheme_id");
		String officeUserType = (String) session.getAttribute("user1");

		String error = "";
		String msg = "";
		String quoteNo = "";
		String applicationNo = "";
		String loginId = "";
		
		boolean claimInfoFlag1 = false;
		boolean claimInfoFlag2 = false;
		boolean claimInfoFlag3 = false;
		boolean claimErrFlag = false; 
		
		boolean contFlag = true;
		boolean amtFlag1 = true;
		boolean amtFlag2 = true;
		boolean amtFlag3 = true;
		boolean poboxFlag = true;
		boolean claimFlag1 = true;
		boolean claimFlag2 = true;
		boolean claimFlag3 = true;
		double contentSumIns = 0.0;
		boolean flagAdd = false;

		loginId = (String) session.getAttribute("user");

		loginId = loginId == null ? "" : loginId;
		customerId = customerId == null ? "" : customerId;
		branch = branch == null ? "020" : branch;
		productId = productId == null ? "" : productId;
		schemeId = schemeId == null ? "" : schemeId;
		sessionId = sessionId == null ? "" : sessionId;
		officeUserType = officeUserType ==null ? "" : officeUserType;
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");

		String requestfrom = "";
		requestfrom = request.getParameter("requestfrom");

		if (officeUserType.equalsIgnoreCase("admin")) // User type is admin
		{
			if (schemeId == null || schemeId.equalsIgnoreCase("")
					|| schemeId.equalsIgnoreCase("null")
					|| schemeId.length() <= 0) {
				schemeId = request.getParameter("schemeId");
			}
		}

		OfficeShieldBean OSB = new OfficeShieldBean();
		ValidationFormat validObj = new ValidationFormat();

		String Address_insure = "";
		String address1 = "";
		String address2 = "";
		String emirate1 = "";
		String emirate2 = "";
		String country1 = "";
		String country2 = "";
		String poBox1 = "";
		String poBox2 = "";

		String phoneno1 = "";
		String phoneno2 = "";
		String email1 = "";
		String email2 = "";

		String freezone = "";
		String contentsOffice = "";
		String contValOthers = "";
		String claimInfo = "";
		String lastYr1 = "";
		String lastYr2 = "";
		String lastYr3 = "";
		String amount1 = "";
		String amount2 = "";
		String amount3 = "";
		String claim1 = "";
		String claim2 = "";
		String claim3 = "";
		String inceptionDate = "";
		String inceptionDD = "";
		String inceptionMonth = "";
		String inceptionYear = "";
		String activityProfession = "";
		String mode = "";
		String city1 = "";
		String city2 = "";
		String SILimit = "";
		String ContentLimit = "";
		String freezoneOthers = "";
		String activityProfessionOthers = "";

		Address_insure = request.getParameter("Address_insure");
		address1 = request.getParameter("address1");
		address2 = request.getParameter("address2");
		emirate1 = request.getParameter("emirate1");
		emirate2 = request.getParameter("emirate2");
		country1 = request.getParameter("country1");
		country2 = request.getParameter("country2");
		poBox1 = request.getParameter("poBox1");
		poBox2 = request.getParameter("poBox2");
		freezone = request.getParameter("freezone");
		contentsOffice = request.getParameter("contents_office");
		contValOthers = request.getParameter("cont_val_others");
		claimInfo = request.getParameter("claimInfo");
		activityProfession = request.getParameter("activityProfession");
		freezoneOthers = request.getParameter("freezoneOthers");
		activityProfessionOthers = request
				.getParameter("activityProfessionOthers");

		lastYr1 = request.getParameter("last_3_year1");
		lastYr2 = request.getParameter("last_3_year2");
		lastYr3 = request.getParameter("last_3_year3");

		amount1 = request.getParameter("last_3_cover1");
		amount2 = request.getParameter("last_3_cover2");
		amount3 = request.getParameter("last_3_cover3");
		claim1 = request.getParameter("claim1");
		claim2 = request.getParameter("claim2");
		claim3 = request.getParameter("claim3");

		phoneno1 = request.getParameter("phoneno1");
		phoneno2 = request.getParameter("phoneno2");
		email1 = request.getParameter("email1");
		email2 = request.getParameter("email2");

		inceptionDD = request.getParameter("policy_inception_Day");
		inceptionMonth = request.getParameter("policy_inception_Month");
		inceptionYear = request.getParameter("policy_inception_Year");

		city1 = request.getParameter("city1");
		city2 = request.getParameter("city2");

		mode = request.getParameter("mode");
		quoteNo = request.getParameter("quoteNo");

		Address_insure = Address_insure == null ? "" : Address_insure;
		address1 = address1 == null ? "" : address1;
		address2 = address2 == null ? "" : address2;
		emirate1 = emirate1 == null ? "" : emirate1;
		emirate2 = emirate2 == null ? "" : emirate2;
		country1 = country1 == null ? "" : country1;
		country2 = country2 == null ? "" : country2;

		phoneno1 = phoneno1 == null ? "" : phoneno1;
		phoneno2 = phoneno2 == null ? "" : phoneno2;
		email1 = email1 == null ? "" : email1;
		email2 = email2 == null ? "" : email2;

		poBox1 = poBox1 == null ? "" : poBox1;
		poBox2 = poBox2 == null ? "" : poBox2;
		freezone = freezone == null ? "0" : freezone;

		address2 = address2.trim();
		poBox2 = poBox2.trim();

		contentsOffice = contentsOffice == null ? "0" : contentsOffice;
		contValOthers = contValOthers == null ? "0" : contValOthers;

		claimInfo = claimInfo == null ? "" : claimInfo;
		lastYr1 = lastYr1 == null ? "0" : lastYr1;
		lastYr2 = lastYr2 == null ? "0" : lastYr2;
		lastYr3 = lastYr3 == null ? "0" : lastYr3;

		amount1 = amount1 == null ? "0" : amount1;
		amount2 = amount2 == null ? "0" : amount2;
		amount3 = amount3 == null ? "0" : amount3;

		claim1 = claim1 == null ? "0" : claim1;
		claim2 = claim2 == null ? "0" : claim2;
		claim3 = claim3 == null ? "0" : claim3;

		activityProfession = activityProfession == null ? "0"
				: activityProfession;
		activityProfessionOthers = activityProfessionOthers == null ? ""
				: activityProfessionOthers;
		freezoneOthers = freezoneOthers == null ? "" : freezoneOthers;

		inceptionDD = inceptionDD == null ? "" : inceptionDD;
		inceptionMonth = inceptionMonth == null ? "" : inceptionMonth;
		inceptionYear = inceptionYear == null ? "" : inceptionYear;
		mode = mode == null ? "fresh" : mode;

		city1 = city1 == null ? "" : city1;
		city2 = city2 == null ? "" : city2;

		quoteNo = quoteNo == null ? "" : quoteNo;

		inceptionDate = inceptionDD + "-" + inceptionMonth + "-"
				+ inceptionYear;
		LogManager.info("inceptionDate ......." + inceptionDate);

		/** Back Date Validation * */
		DateFunction df = new DateFunction();

		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		String mm1 = "";
		mm1 = inceptionMonth;
		String[] months = { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul",
				"Aug", "Sep", "Oct", "Nov", "Dec" };
		for (int m = 0; m < months.length; m++) {
			if (months[m].equalsIgnoreCase(mm1)) {
				mm1 = "" + (m + 1);
				break;
			}
		}

		//c2.set(Integer.parseInt(inceptionYear), Integer.parseInt(mm1), Integer.parseInt(inceptionDD));
		String[][] serverDate = new String[0][0];
		serverDate = OSB.getTodaysDate();

		if (serverDate.length > 0)
			c1.set(Integer.parseInt(serverDate[0][0]), Integer
					.parseInt(serverDate[0][1]), Integer
					.parseInt(serverDate[0][2]));

		long diff = 0;
		try {
			System.out.println("c1 DATE " + c1.get(Calendar.DATE) + " " + c1.get(Calendar.MONTH) + " " + c1.get(Calendar.YEAR) );
			System.out.println("c2 DATE " + c2.get(Calendar.DATE) + " " + c2.get(Calendar.MONTH) + " " + c2.get(Calendar.YEAR) );
			diff = df.getDayDifference(c2, c1);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		int backDates = 0;
		String backD = "0";

		if (!officeUserType.equalsIgnoreCase("admin")) // User type is admin
		{
			backD = OSB.getBackDatesAllowed(loginId, productId, schemeId);

			try {
				if (backD != null && !backD.equals(""))
					backDates = Integer.parseInt(backD);
			} catch (Exception e) {
				backDates = 0;
			}

			if (diff > backDates) {
				if (backDates == 0)
					error = error
							+ "<br> * Insurance start date should not be less than today date";
				else if (backDates > 0)
					error = error + "<br> * Back dates allowed maximum "
							+ backDates + " days only";
			}
		} // User type is admin
		error = "";
		
		/** Back Date Validation * */

		if (requestfrom.equalsIgnoreCase("SaveAndExit")) {
			if (freezone.equalsIgnoreCase("select"))
				freezone = "0";

			if (contentsOffice.equalsIgnoreCase("select"))
				contentsOffice = "0";

			if (activityProfession.equalsIgnoreCase("select"))
				activityProfession = "0";
		}

		/** * For Address Field Validation ** */

		/** Exsiting* */
		if (Address_insure.equalsIgnoreCase("Y")) {
			if (emirate2.equalsIgnoreCase("others")
					&& (city2.equalsIgnoreCase("")
							|| city2.equalsIgnoreCase("null") || city2 == null))
				error = error + "<br> * Please enter emirate ";
			if (emirate2.equalsIgnoreCase("select"))
				error = error + "<br> * "+OSB.getUploadErrorMsg("25");//"Please select emirate ";
			if (address2.equalsIgnoreCase("")
					|| address2.equalsIgnoreCase("null") || address2 == null)
				error = error + "<br> * "+OSB.getUploadErrorMsg("275");//"Please enter insured location ";
			if (poBox2.equalsIgnoreCase("") || poBox2.equalsIgnoreCase("null")
					|| poBox2 == null)
				error = error + "<br> * "+OSB.getUploadErrorMsg("27");//"Please enter P.O.Box number ";

			flagAdd = StringUtil.isNumberValue(address2);

			if (flagAdd && address2.length() > 0) {
				error = error + "<br> *"+OSB.getUploadErrorMsg("328");//"Please enter valid insured location ";
			}
			if (!StringUtil.isNumber(phoneno2)) {
				error += "<br> *"+OSB.getUploadErrorMsg("329");//"Please Enter valid Phone Number";
			}
			if (email2.length() > 0) {
				if (validObj.EMailValidation(email2) == true)
					error += "<br> *" + OSB.getUploadErrorMsg("20");
			}
			else
				error += "<br> *" + OSB.getUploadErrorMsg("19");
		}

		/* For Address Field Validation old */
		if (Address_insure.equalsIgnoreCase("Y")) {
			OSB.setAddress2(address2);

			if (!emirate2.equalsIgnoreCase("others"))
				OSB.setEmirate2(emirate2);
			else
				OSB.setEmirate2(city2);

			OSB.setCountry2(country2);
			OSB.setpoBox2(poBox2);

			OSB.setPhoneNo(phoneno2);
			OSB.setEmail(email2);

		} else {
			OSB.setAddress2(address1);

			if (!emirate2.equalsIgnoreCase("others"))
				OSB.setEmirate2(emirate1);
			else
				OSB.setEmirate2(city1);

			OSB.setCountry2(country1);
			OSB.setpoBox2(poBox1);
			OSB.setPhoneNo(phoneno1);
			OSB.setEmail(email1);
		}
		/* For Address Field Validation old */

		LogManager.info("Test Vinoth  now ::" + OSB.getPhoneNo() + "\t"
				+ OSB.getEmail());

		/***********************************************************************
		 * * For Address Field Validation new
		 * 
		 * OSB.setAddress2(address2);
		 * 
		 * if(!emirate2.equalsIgnoreCase("others")) OSB.setEmirate2(emirate2);
		 * else OSB.setEmirate2(city2);
		 * 
		 * OSB.setCountry2(country2); OSB.setpoBox2(poBox2);
		 * 
		 * For Address Field Validation new
		 **********************************************************************/

		OSB.setCustomerId(customerId);
		OSB.setFreezone(freezone);
		OSB.setFreezoneOthers(freezoneOthers);
		OSB.setAddressInsure(Address_insure);
		OSB.setContentsOffice(contentsOffice);
		OSB.setContValOthers(contValOthers);
		OSB.setClaimInfo(claimInfo);
		OSB.setLastYr1(lastYr1);
		OSB.setLastYr2(lastYr2);
		OSB.setLastYr3(lastYr3);
		OSB.setAmount1(amount1);
		OSB.setAmount2(amount2);
		OSB.setAmount3(amount3);
		OSB.setClaim1(claim1);
		OSB.setClaim2(claim2);
		OSB.setClaim3(claim3);
		OSB.setInceptionDate(inceptionDate);
		OSB.setActivityProfession(activityProfession);
		OSB.setActivityProfessionOthers(activityProfessionOthers);
		OSB.setProId(productId);
		OSB.setBranch(branch);
		OSB.setLoginId(loginId);
		OSB.setSchemeId(schemeId);

		OSB.setDobDay(inceptionDD);
		OSB.setDobMonth(inceptionMonth);
		OSB.setDobYear(inceptionYear);
		OSB.setSessionId(sessionId);

		if (contentsOffice.equalsIgnoreCase("4")) {
			SILimit = OSB.getContentCoverageSILimit();
			ContentLimit = OSB.getContentValue();
		}

		SILimit = SILimit == null ? "0" : SILimit;
		ContentLimit = ContentLimit == null ? "0" : ContentLimit;

		if (SILimit.equals("") || SILimit.equals("null"))
			SILimit = "0";

		if (ContentLimit.equals("") || ContentLimit.equals("null"))
			ContentLimit = "0";

		if (!mode.equalsIgnoreCase("fresh")) {
			applicationNo = OSB.getApplicationNo(quoteNo);
			OSB.setApplicationNo(applicationNo);
			OSB.setQuoteNo(quoteNo);
		}

		LogManager.info("senthil");
		LogManager.info("mode..." + mode);
		LogManager.info("applicationNo..." + applicationNo);
		LogManager.info("quoteNo..." + quoteNo);
		LogManager.info("address2..." + address2);
		System.out.println("customerId2: "+customerId);

		if (requestfrom.equalsIgnoreCase("SaveAndExit")) {
			contFlag = OSB.validAmount(contValOthers);

			if (customerId.length() == 0 || customerId.equals("")
					|| customerId.equals("null") || customerId == null)
				error = error + "<br> * Please select customer";

			if (contentsOffice.equalsIgnoreCase("4")
					&& (contValOthers.equalsIgnoreCase("") || contValOthers
							.equalsIgnoreCase("0")))
				error = error
						+ "<br> * "+OSB.getUploadErrorMsg("330");//"Content amount should not be empty or zero";

			if (activityProfession.equalsIgnoreCase("68")
					&& (activityProfessionOthers.equalsIgnoreCase("") || activityProfessionOthers
							.length() <= 0))
				error = error
						+ "<br> * "+OSB.getUploadErrorMsg("331");//"Please enter activity profession others";

			if (freezone.equalsIgnoreCase("36")
					&& (freezoneOthers.equalsIgnoreCase("") || freezoneOthers
							.length() <= 0))
				error = error + "<br> *"+OSB.getUploadErrorMsg("332");//"* Please enter freezone others";

			/*******************************************************************
			 * * Suminsuered Referal Now if(contFlag &&
			 * !contValOthers.equalsIgnoreCase("")) {
			 * if(contentsOffice.equalsIgnoreCase("4") &&
			 * !(Double.parseDouble(contValOthers) <=
			 * Double.parseDouble(SILimit)) ) error = error + "<br> * Content
			 * amount should be lessthan "+SILimit ; } Sum Insured Referal Now
			 ******************************************************************/

			if (contFlag && !contValOthers.equalsIgnoreCase("")) {
				if (contentsOffice.equalsIgnoreCase("4")
						&& (Double.parseDouble(contValOthers) <= Double
								.parseDouble(ContentLimit)))
					error = error
							+ "<br> * "+OSB.getUploadErrorMsg("333")+" "+ContentLimit;//"Content amount should be greater than "
							
			}

			// if( claimInfo.equalsIgnoreCase("Y") && (
			// amount1.equalsIgnoreCase("") || amount2.equalsIgnoreCase("") ||
			// amount3.equalsIgnoreCase("") ) )
			if (claimInfo.equalsIgnoreCase("Y") && amount1.equalsIgnoreCase("")
					&& amount2.equalsIgnoreCase("")
					&& amount3.equalsIgnoreCase(""))
				error = error + "<br> * "+OSB.getUploadErrorMsg("334");//"Claim amount should not be empty";

			if (claimInfo.equalsIgnoreCase("Y")
					&& amount1.equalsIgnoreCase("0")
					&& amount2.equalsIgnoreCase("0")
					&& amount3.equalsIgnoreCase("0")) // new Jan18
				error = error + "<br> * "+OSB.getUploadErrorMsg("335");//"Claim amount should not be zero";

			amtFlag1 = OSB.validAmount(amount1);
			amtFlag2 = OSB.validAmount(amount2);
			amtFlag3 = OSB.validAmount(amount3);
			claimFlag1 = StringUtil.isNumberValue(claim1);
			claimFlag2 = StringUtil.isNumberValue(claim2);
			claimFlag3 = StringUtil.isNumberValue(claim3);
			poboxFlag = StringUtil.isNumberValue(poBox2);

			if (!poboxFlag)
				error = error + "<br> * "+OSB.getUploadErrorMsg("248");//"Please enter valid P.O.Box";
			if (!contFlag)
				error = error + "<br> * "+OSB.getUploadErrorMsg("336");//"Please enter valid content amount";
			if (!amtFlag1)
				error = error + "<br> * "+OSB.getUploadErrorMsg("337");//"Please enter valid amount3";
			if (!amtFlag2)
				error = error + "<br> * "+OSB.getUploadErrorMsg("338");//"Please enter valid amount2";
			if (!amtFlag3)
				error = error + "<br> * "+OSB.getUploadErrorMsg("339");//"Please enter valid amount1";
			if (!claimFlag1)
				error = error
						+ "<br> * "+OSB.getUploadErrorMsg("340");//"Please enter valid no. of claim count 1";
			if (!claimFlag2)
				error = error
						+ "<br> * "+OSB.getUploadErrorMsg("341");//"Please enter valid no. of claim count 2";
			if (!claimFlag3)
				error = error
						+ "<br> * "+OSB.getUploadErrorMsg("342");//"Please enter valid no. of claim count 3";
			error = error + OSB.dateValidation();

			if (error.length() > 0) {
				LogManager.info("<< error In OfficeShield >>" + error);
				request.setAttribute("error", error);
				dispatcher = request
						.getRequestDispatcher("/CustomerInfo/CustomerInfoOShield.jsp?mode=Edit&customerId="
								+ customerId);
			} else {
				quoteNo = OSB.insertOfficeShieldData(mode);
				msg = "Quote No " + quoteNo + " Successfully Saved";
				request.setAttribute("quoteNo", quoteNo);
				request.setAttribute("msg", msg);
				request.setAttribute("Save", "Save");
				dispatcher = request
						.getRequestDispatcher("/CustomerInfo/CustomerInfoOShield.jsp");
			}
		}

		else if (requestfrom.equalsIgnoreCase("SaveAndContinue")) // ||
		// requestfrom.equalsIgnoreCase("AdminSubmit"))
		// //New
		// jan17
		{
			System.out.println("Before " + error);
			contFlag = OSB.validAmount(contValOthers);
			System.out.println("After " + error);
			
			if (customerId.length() == 0 || customerId.equals("")
					|| customerId.equals("null") || customerId == null)
				error = error + "<br> * Please select customer";

			if (freezone.equalsIgnoreCase("select"))
				error = error + "<br> * Please select freezone";

			if (freezone.equalsIgnoreCase("36")
					&& (freezoneOthers.equalsIgnoreCase("") || freezoneOthers
							.length() <= 0))
				error = error + "<br> * "+OSB.getUploadErrorMsg("332");//"Please enter freezone others";

			if (contentsOffice.equalsIgnoreCase("select"))
				error = error + "<br> * Please select contents value";

			if (contentsOffice.equalsIgnoreCase("4")
					&& (contValOthers.equalsIgnoreCase("") || contValOthers
							.equalsIgnoreCase("0")))
				error = error
						+ "<br> * "+OSB.getUploadErrorMsg("343");//"Content amount should not be empty or zero";

			if (activityProfession.equalsIgnoreCase("select"))
				error = error + "<br> * Please select activity profession ";

			if (activityProfession.equalsIgnoreCase("68")
					&& (activityProfessionOthers.equalsIgnoreCase("") || activityProfessionOthers
							.length() <= 0))
				error = error
						+ "<br> * "+OSB.getUploadErrorMsg("331");//"Please enter activity profession others";

			if (freezone.equalsIgnoreCase("36")
					&& !freezoneOthers.equalsIgnoreCase("")
					&& freezoneOthers.length() > 0) {
				flagAdd = false;
				flagAdd = StringUtil.isNumberValue(freezoneOthers);
				if (flagAdd) {
					error = error + "<br> * "+OSB.getUploadErrorMsg("344");//"Please enter valid freezone ";
				}
			}

			if (activityProfession.equalsIgnoreCase("68")
					&& !activityProfessionOthers.equalsIgnoreCase("")
					&& activityProfessionOthers.length() > 0) {
				flagAdd = false;
				flagAdd = StringUtil.isNumberValue(activityProfessionOthers);
				if (flagAdd) {
					error = error
							+ "<br> * "+OSB.getUploadErrorMsg("345");//"Please enter valid activity Profession ";
				}
			}
			/*******************************************************************
			 * * Sum Insured Referal Now if(contFlag &&
			 * !contValOthers.equalsIgnoreCase("")) {
			 * if(contentsOffice.equalsIgnoreCase("4") &&
			 * !(Double.parseDouble(contValOthers) <=
			 * Double.parseDouble(SILimit)) ) error = error + "<br> * Content
			 * amount should be lessthan "+SILimit ; } Sum Insured Referal Now
			 ******************************************************************/

			if (contFlag && !contValOthers.equalsIgnoreCase("")) {
				if (contentsOffice.equalsIgnoreCase("4")
						&& (Double.parseDouble(contValOthers) <= Double
								.parseDouble(ContentLimit)))
					error = error
							+ "<br> * Content amount should be greater than "
							+ ContentLimit;
			}

			// if( claimInfo.equalsIgnoreCase("Y") && (
			// amount1.equalsIgnoreCase("") || amount2.equalsIgnoreCase("") ||
			// amount3.equalsIgnoreCase("") ) )
			if (claimInfo.equalsIgnoreCase("Y") && amount1.equalsIgnoreCase("")
					&& amount2.equalsIgnoreCase("")
					&& amount3.equalsIgnoreCase(""))
				error = error + "<br> * "+OSB.getUploadErrorMsg("334");//"Claim amount should not be empty";

			if (claimInfo.equalsIgnoreCase("Y")
					&& amount1.equalsIgnoreCase("0")
					&& amount2.equalsIgnoreCase("0")
					&& amount3.equalsIgnoreCase("0"))
				error = error + "<br> * "+OSB.getUploadErrorMsg("335");//"Claim amount should not be zero";

			if (claimInfo.equalsIgnoreCase("Y") && claim1.equalsIgnoreCase("")
					&& claim2.equalsIgnoreCase("")
					&& claim3.equalsIgnoreCase(""))
				error = error + "<br> * No. Of Claim count should not be empty";
			else {
				claimFlag1 = StringUtil.isNumberValue(claim1);
				claimFlag2 = StringUtil.isNumberValue(claim2);
				claimFlag3 = StringUtil.isNumberValue(claim3);
			}
			
			// Feb 04
			if (claimInfo.equalsIgnoreCase("Y"))
			{
				if(amount1.equalsIgnoreCase("0")||amount1.equalsIgnoreCase("") && (claim3.equalsIgnoreCase("0")||claim3.equalsIgnoreCase("")))
					claimInfoFlag1 = true;
				if(amount2.equalsIgnoreCase("0")||amount2.equalsIgnoreCase("") && (claim2.equalsIgnoreCase("0")||claim2.equalsIgnoreCase("")))
					claimInfoFlag2 = true;
				if(amount3.equalsIgnoreCase("0")||amount3.equalsIgnoreCase("") && (claim1.equalsIgnoreCase("0")||claim1.equalsIgnoreCase("")))
					claimInfoFlag3 = true;
				
				if(claimInfoFlag1 && claimInfoFlag2 && claimInfoFlag3)
				{
					error = error + "<br> * "+OSB.getUploadErrorMsg("346");//"Please enter valid claim information";
					claimErrFlag = true;
				}
				
				if(amount1.length() > 0 || claim3.length() > 0)
				{
					if(Double.parseDouble(amount1)==0 || Double.parseDouble(claim3)==0 )
						claimInfoFlag1 = true;
					//	error = error + "<br> * "+OSB.getUploadErrorMsg("346")+lastYr1;
				}
				if(amount2.length() > 0 || claim2.length() > 0)
				{
					if(Double.parseDouble(amount2)==0 || Double.parseDouble(claim2)==0 )
						claimInfoFlag2 = true;
					//	error = error + "<br> * "+OSB.getUploadErrorMsg("346")+lastYr2;
				}
				if(amount3.length() > 0 || claim1.length() > 0)
				{
					if(Double.parseDouble(amount3)==0 || Double.parseDouble(claim1)==0 )
						claimInfoFlag3 = true;
					//	error = error + "<br> * "+OSB.getUploadErrorMsg("346")+lastYr3;
				}
				
				if(claimInfoFlag1 && claimInfoFlag2 && claimInfoFlag3 && !claimErrFlag)
					error = error + "<br> * "+OSB.getUploadErrorMsg("346");//"Please enter valid claim information";
			}
			// Feb 04
			
			// Jan06 2009
			if (claimInfo.equalsIgnoreCase("Y")
					&& (amount1.equalsIgnoreCase("0") || amount1
							.equalsIgnoreCase(""))
					&& !claim3.equalsIgnoreCase("")
					&& !claim3.equalsIgnoreCase("0"))
				error = error + "<br> * "+OSB.getUploadErrorMsg("347")+ lastYr1;; //"Please enter valid claim amount "
						

			if (claimInfo.equalsIgnoreCase("Y")
					&& (amount2.equalsIgnoreCase("0") || amount2
							.equalsIgnoreCase(""))
					&& !claim2.equalsIgnoreCase("")
					&& !claim2.equalsIgnoreCase("0"))
				error = error + "<br> * "+OSB.getUploadErrorMsg("347") + lastYr2;

			if (claimInfo.equalsIgnoreCase("Y")
					&& (amount3.equalsIgnoreCase("0") || amount3
							.equalsIgnoreCase(""))
					&& !claim1.equalsIgnoreCase("")
					&& !claim1.equalsIgnoreCase("0"))
				error = error + "<br> * "+OSB.getUploadErrorMsg("347") + lastYr3;
			// Jan 18
			if (claimInfo.equalsIgnoreCase("Y")
					&& (claim3.equalsIgnoreCase("0") || claim3
							.equalsIgnoreCase(""))
					&& !amount1.equalsIgnoreCase("")
					&& !amount1.equalsIgnoreCase("0"))
				error = error + "<br> * "+OSB.getUploadErrorMsg("348") + lastYr1;//"Please enter valid claim count "
						

			if (claimInfo.equalsIgnoreCase("Y")
					&& (claim2.equalsIgnoreCase("0") || claim2
							.equalsIgnoreCase(""))
					&& !amount2.equalsIgnoreCase("")
					&& !amount2.equalsIgnoreCase("0"))
				error = error + "<br> * "+OSB.getUploadErrorMsg("348")	+ lastYr2;

			if (claimInfo.equalsIgnoreCase("Y")
					&& (claim1.equalsIgnoreCase("0") || claim1
							.equalsIgnoreCase(""))
					&& !amount3.equalsIgnoreCase("")
					&& !amount3.equalsIgnoreCase("0"))
				error = error + "<br> * "+OSB.getUploadErrorMsg("348") + lastYr3;
				
			amtFlag1 = OSB.validAmount(amount1);
			amtFlag2 = OSB.validAmount(amount2);
			amtFlag3 = OSB.validAmount(amount3);
			
			poboxFlag = StringUtil.isNumberValue(poBox2);

			if (!poboxFlag)
				error = error + "<br> * Please enter valid P.O.Box";
			if (!contFlag)
				error = error + "<br> * Please enter valid content amount";
			
			if (!amtFlag1)
				error = error + "<br> * "+OSB.getUploadErrorMsg("337");//"Please enter valid amount3";
			if (!amtFlag2)
				error = error + "<br> * "+OSB.getUploadErrorMsg("338");//"Please enter valid amount2";
			if (!amtFlag3)
				error = error + "<br> * "+OSB.getUploadErrorMsg("339");//"Please enter valid amount1";
			if (!claimFlag1)
				error = error + "<br> * "+OSB.getUploadErrorMsg("340");//"Please enter valid no. of claim count 1";
			if (!claimFlag2)
				error = error + "<br> * "+OSB.getUploadErrorMsg("341");//"Please enter valid no. of claim count 2";
			if (!claimFlag3)
				error = error + "<br> * "+OSB.getUploadErrorMsg("342");//"Please enter valid no. of claim count 3";
			error = error + OSB.dateValidation();

			/*if (error.length() > 0) {
				LogManager.info("<< error In OfficeShield >>" + error);
				request.setAttribute("error", error);
				dispatcher = request
						.getRequestDispatcher("/CustomerInfo/CustomerInfoOShield.jsp?mode=Edit");
			} else {
				quoteNo = OSB.insertOfficeShieldData(mode);
				request.setAttribute("linkFrom", "CusInfo");
				request.setAttribute("quoteNo", quoteNo);
				request.setAttribute("customerId", customerId);
				dispatcher = request.getRequestDispatcher("/servlet/generateValue");
			}*/
			quoteNo = OSB.insertOfficeShieldData(mode);
            request.setAttribute("linkFrom", "CusInfo");
            request.setAttribute("quoteNo", quoteNo);
            request.setAttribute("customerId", customerId);
            dispatcher = request.getRequestDispatcher("/servlet/generateValue");
		}

		else if (requestfrom.equalsIgnoreCase("CustomerInfoOShieldBack")) {
			int result = 0;
			result = OSB.getProductDetails(loginId);
			if (result > 1)
				dispatcher = request.getRequestDispatcher("/login/ProductSelection.jsp");
			else
				dispatcher = request.getRequestDispatcher("/HomeInsurance/ViewQuote_B2B.jsp");
				//dispatcher = request.getRequestDispatcher("/HomeDisplayController?DisplayValue=quoteregister");
				
		}

		else if (requestfrom.equalsIgnoreCase("AdminSubmit")) {
		
			// to Start vinoth Validation
			
			contFlag = OSB.validAmount(contValOthers);

			if (customerId.length() == 0 || customerId.equals("")
					|| customerId.equals("null") || customerId == null)
				error = error + "<br> * Please select customer";

			if (freezone.equalsIgnoreCase("select"))
				error = error + "<br> * Please select freezone";

			if (freezone.equalsIgnoreCase("36")
					&& (freezoneOthers.equalsIgnoreCase("") || freezoneOthers
							.length() <= 0))
				error = error + "<br> * "+OSB.getUploadErrorMsg("332");//"Please enter freezone others";

			if (contentsOffice.equalsIgnoreCase("select"))
				error = error + "<br> * Please select contents value";

			if (contentsOffice.equalsIgnoreCase("4")
					&& (contValOthers.equalsIgnoreCase("") || contValOthers
							.equalsIgnoreCase("0")))
				error = error
						+ "<br> * "+OSB.getUploadErrorMsg("343");//"Content amount should not be empty or zero";

			if (activityProfession.equalsIgnoreCase("select"))
				error = error + "<br> * Please select activity profession ";

			if (activityProfession.equalsIgnoreCase("68")
					&& (activityProfessionOthers.equalsIgnoreCase("") || activityProfessionOthers
							.length() <= 0))
				error = error
						+ "<br> * "+OSB.getUploadErrorMsg("331");//"Please enter activity profession others";

			if (freezone.equalsIgnoreCase("36")
					&& !freezoneOthers.equalsIgnoreCase("")
					&& freezoneOthers.length() > 0) {
				flagAdd = false;
				flagAdd = StringUtil.isNumberValue(freezoneOthers);
				if (flagAdd) {
					error = error + "<br> * "+OSB.getUploadErrorMsg("344");//"Please enter valid freezone ";
				}
			}

			if (activityProfession.equalsIgnoreCase("68")
					&& !activityProfessionOthers.equalsIgnoreCase("")
					&& activityProfessionOthers.length() > 0) {
				flagAdd = false;
				flagAdd = StringUtil.isNumberValue(activityProfessionOthers);
				if (flagAdd) {
					error = error
							+ "<br> * "+OSB.getUploadErrorMsg("345");//"Please enter valid activity Profession ";
				}
			}
			
			if (contFlag && !contValOthers.equalsIgnoreCase("")) {
				if (contentsOffice.equalsIgnoreCase("4")
						&& (Double.parseDouble(contValOthers) <= Double
								.parseDouble(ContentLimit)))
					error = error
							+ "<br> * Content amount should be greater than "
							+ ContentLimit;
			}

			
			if (claimInfo.equalsIgnoreCase("Y") && amount1.equalsIgnoreCase("")
					&& amount2.equalsIgnoreCase("")
					&& amount3.equalsIgnoreCase(""))
				error = error + "<br> * "+OSB.getUploadErrorMsg("334");//"Claim amount should not be empty";

			if (claimInfo.equalsIgnoreCase("Y")
					&& amount1.equalsIgnoreCase("0")
					&& amount2.equalsIgnoreCase("0")
					&& amount3.equalsIgnoreCase("0"))
				error = error + "<br> * "+OSB.getUploadErrorMsg("335");//"Claim amount should not be zero";

			if (claimInfo.equalsIgnoreCase("Y") && claim1.equalsIgnoreCase("")
					&& claim2.equalsIgnoreCase("")
					&& claim3.equalsIgnoreCase(""))
				error = error + "<br> * No. Of Claim count should not be empty";
			else {
				claimFlag1 = StringUtil.isNumberValue(claim1);
				claimFlag2 = StringUtil.isNumberValue(claim2);
				claimFlag3 = StringUtil.isNumberValue(claim3);
			}
			
			// Feb 04
			if (claimInfo.equalsIgnoreCase("Y"))
			{
				if(amount1.equalsIgnoreCase("0")||amount1.equalsIgnoreCase("") && (claim3.equalsIgnoreCase("0")||claim3.equalsIgnoreCase("")))
					claimInfoFlag1 = true;
				if(amount2.equalsIgnoreCase("0")||amount2.equalsIgnoreCase("") && (claim2.equalsIgnoreCase("0")||claim2.equalsIgnoreCase("")))
					claimInfoFlag2 = true;
				if(amount3.equalsIgnoreCase("0")||amount3.equalsIgnoreCase("") && (claim1.equalsIgnoreCase("0")||claim1.equalsIgnoreCase("")))
					claimInfoFlag3 = true;
				
				if(claimInfoFlag1 && claimInfoFlag2 && claimInfoFlag3)
				{
					error = error + "<br> * "+OSB.getUploadErrorMsg("346");//"Please enter valid claim information";
					claimErrFlag = true;
				}
				
				if(amount1.length() > 0 || claim3.length() > 0)
				{
					if(Double.parseDouble(amount1)==0 || Double.parseDouble(claim3)==0 )
						claimInfoFlag1 = true;
					//	error = error + "<br> * "+OSB.getUploadErrorMsg("346")+lastYr1;
				}
				if(amount2.length() > 0 || claim2.length() > 0)
				{
					if(Double.parseDouble(amount2)==0 || Double.parseDouble(claim2)==0 )
						claimInfoFlag2 = true;
					//	error = error + "<br> * "+OSB.getUploadErrorMsg("346")+lastYr2;
				}
				if(amount3.length() > 0 || claim1.length() > 0)
				{
					if(Double.parseDouble(amount3)==0 || Double.parseDouble(claim1)==0 )
						claimInfoFlag3 = true;
					//	error = error + "<br> * "+OSB.getUploadErrorMsg("346")+lastYr3;
				}
				
				if(claimInfoFlag1 && claimInfoFlag2 && claimInfoFlag3 && !claimErrFlag)
					error = error + "<br> * "+OSB.getUploadErrorMsg("346");//"Please enter valid claim information";
			}
			// Feb 04
			
			
			if (claimInfo.equalsIgnoreCase("Y")
					&& (amount1.equalsIgnoreCase("0") || amount1
							.equalsIgnoreCase(""))
					&& !claim3.equalsIgnoreCase("")
					&& !claim3.equalsIgnoreCase("0"))
				error = error + "<br> * "+OSB.getUploadErrorMsg("347")+ lastYr1;; //"Please enter valid claim amount "
						

			if (claimInfo.equalsIgnoreCase("Y")
					&& (amount2.equalsIgnoreCase("0") || amount2
							.equalsIgnoreCase(""))
					&& !claim2.equalsIgnoreCase("")
					&& !claim2.equalsIgnoreCase("0"))
				error = error + "<br> * "+OSB.getUploadErrorMsg("347") + lastYr2;

			if (claimInfo.equalsIgnoreCase("Y")
					&& (amount3.equalsIgnoreCase("0") || amount3
							.equalsIgnoreCase(""))
					&& !claim1.equalsIgnoreCase("")
					&& !claim1.equalsIgnoreCase("0"))
				error = error + "<br> * "+OSB.getUploadErrorMsg("347") + lastYr3;
			
			if (claimInfo.equalsIgnoreCase("Y")
					&& (claim3.equalsIgnoreCase("0") || claim3
							.equalsIgnoreCase(""))
					&& !amount1.equalsIgnoreCase("")
					&& !amount1.equalsIgnoreCase("0"))
				error = error + "<br> * "+OSB.getUploadErrorMsg("348") + lastYr1;//"Please enter valid claim count "
						

			if (claimInfo.equalsIgnoreCase("Y")
					&& (claim2.equalsIgnoreCase("0") || claim2
							.equalsIgnoreCase(""))
					&& !amount2.equalsIgnoreCase("")
					&& !amount2.equalsIgnoreCase("0"))
				error = error + "<br> * "+OSB.getUploadErrorMsg("348")	+ lastYr2;

			if (claimInfo.equalsIgnoreCase("Y")
					&& (claim1.equalsIgnoreCase("0") || claim1
							.equalsIgnoreCase(""))
					&& !amount3.equalsIgnoreCase("")
					&& !amount3.equalsIgnoreCase("0"))
				error = error + "<br> * "+OSB.getUploadErrorMsg("348") + lastYr3;
				
			amtFlag1 = OSB.validAmount(amount1);
			amtFlag2 = OSB.validAmount(amount2);
			amtFlag3 = OSB.validAmount(amount3);
			
			poboxFlag = StringUtil.isNumberValue(poBox2);

			if (!poboxFlag)
				error = error + "<br> * Please enter valid P.O.Box";
			if (!contFlag)
				error = error + "<br> * Please enter valid content amount";
			
			if (!amtFlag1)
				error = error + "<br> * "+OSB.getUploadErrorMsg("337");//"Please enter valid amount3";
			if (!amtFlag2)
				error = error + "<br> * "+OSB.getUploadErrorMsg("338");//"Please enter valid amount2";
			if (!amtFlag3)
				error = error + "<br> * "+OSB.getUploadErrorMsg("339");//"Please enter valid amount1";
			if (!claimFlag1)
			
				error = error + "<br> * "+OSB.getUploadErrorMsg("340");//"Please enter valid no. of claim count 1";
			if (!claimFlag2)
				error = error + "<br> * "+OSB.getUploadErrorMsg("341");//"Please enter valid no. of claim count 2";
			if (!claimFlag3)
				error = error + "<br> * "+OSB.getUploadErrorMsg("342");//"Please enter valid no. of claim count 3";
			error = error + OSB.dateValidation();
			
			// To end Vinoth
			
			/*if (error.length() > 0) // new Jan17
			{
				LogManager.info("<<  error In OfficeShield >>" + error);
				request.setAttribute("error", error);
				dispatcher = request
						.getRequestDispatcher("/CustomerInfo/CustomerInfoOShield.jsp?mode=Edit&linkFrom=admin&reqFrom=Pending&schemeId="
								+ schemeId
								+ "&ProId="
								+ productId
								+ "&QuoteNo=" + quoteNo);
			} else {*/
				
				//Block added by chinna
				schemeId=request.getParameter("schemeId");
				session.setAttribute("scheme_id",schemeId);
				System.out.println("schemeId:>>>>>>>>: "+schemeId);
				if(customerId==null || customerId.length()<=0)
				{
					String pid=request.getParameter("ProId")==null?"0":request.getParameter("ProId");
					customerId = OSB.getCustomerIdForAdminSide(quoteNo,pid);
					OSB.setCustomerId(customerId);
					System.out.println("customerId:>>>>>>>>>>>>>>>>>2"+customerId);
				}//End
				quoteNo = OSB.insertOfficeShieldData(mode);
				request.setAttribute("linkFrom", "admin");
				request.setAttribute("quoteNo", quoteNo);
				request.setAttribute("customerId", customerId);
				request.setAttribute("reqFrom","Pending");
				dispatcher = request.getRequestDispatcher("/servlet/generateValue");
//			}
		}
		else if (requestfrom.equalsIgnoreCase("CoverageDetails")) 
		{
			bean.validator v=new bean.validator();
			System.out.println("CoverageDetails - Enter");
			quoteNo = (String)session.getAttribute("quoteNo");
			String sumInsured = request.getParameter("sumInsured") == null ? "0": request.getParameter("sumInsured");
			double sum=Double.parseDouble(sumInsured);
			double amount=0.0;
			String destTable=request.getParameter("destTable") == null ? "": request.getParameter("destTable");
			String conTypId = request.getParameter("conTypId") == null ? "0": request.getParameter("conTypId");
			String cover = request.getParameter("cover") == null ? "": request.getParameter("cover");
			String coverid = request.getParameter("coverid") == null ? "": request.getParameter("coverid");
			String subcoverid = request.getParameter("subcoverid") == null ? "0": request.getParameter("subcoverid");
			String proId = (String) session.getAttribute("product_id");proId = proId == null ? "" : proId;
			schemeId = (String) session.getAttribute("scheme_id");schemeId = schemeId == null ? "" : schemeId;
			String add = request.getParameter("addMore") == null ? "3": request.getParameter("addMore");
			int addMore=Integer.parseInt(add);
			int count=0;
			String val="";
			String type="";
			String paramName="";
			error="";
			msg="";
			String[][] coverValues;
			boolean flag=true;
			
			String[][] coverDetails=session.getAttribute("coverDetails")==null?new String[0][0]:(String[][])session.getAttribute("coverDetails"); 
			java.util.HashMap fields=new java.util.HashMap();
			java.util.ArrayList fieldsList=new java.util.ArrayList();
			if(coverDetails.length>0)
			{
				coverValues=new String[addMore][coverDetails.length];
				for(int k=0; k<addMore; k++) 
				{
					for(int i=0; i<coverDetails.length; i++)
					{
						paramName=coverDetails[i][0]+k;
						val=request.getParameter(paramName);
						coverValues[k][i]=val;
						type=request.getParameter("type:"+paramName);
						
						if(val==null || val.equalsIgnoreCase("") || val.equalsIgnoreCase(" "))
						{
							count++;
						}
						
						if(type.equalsIgnoreCase("String") && val.length()>0)
						{
							/*String err=v.validString(val, 4);
							if(err!=null && err.length()>0)
							{
								error+=err+" "+coverDetails[i][0]+" in Row "+(k+1)+"</br>";
							}*/
						}else if(type.equalsIgnoreCase("number") && val.length()>0)
						{
							String temp=v.validInteger(val);
							if(temp!=null && temp.length()>0)
							{
								error+=temp+" "+coverDetails[i][0]+" in Row "+(k+1)+"</br>";
							}
						}
						if(val.length()>0 && (val!=null) && !val.equalsIgnoreCase("") && !val.equalsIgnoreCase(" "))
						{
							fields.put(coverDetails[i][4], val);
							if(coverDetails[i][0].equalsIgnoreCase("Loan Amount") || coverDetails[i][0].equalsIgnoreCase("Sum Insured"))
							{
								try{
								amount+=Double.parseDouble(val);
								}catch(Exception e){
								//error+="Invalid "+coverDetails[i][0]+" in Row "+(k+1)+"</br>";
								}
							}
						}
					}
					if(fields.size()>0)
					{
						fieldsList.add(fields);
						fields=new java.util.HashMap();
					}
					System.out.println("Count: =====>>"+count+" CoverDetails.length: "+coverDetails.length);
					if(count<coverDetails.length)
					{
						for(int i=0; i<coverDetails.length; i++)
						{
							paramName=coverDetails[i][0]+k;
							val=request.getParameter(paramName);
							if(coverDetails[i][9].equalsIgnoreCase("Y"))
							{
								if(val==null || val.equalsIgnoreCase("") || val.equalsIgnoreCase(" "))
								{
									error+="Invalid "+coverDetails[i][0]+" in Row "+(k+1)+"</br>";
								}
							}
						}
					}
					count=0;
				}
				System.out.println("Count: after=====>>"+count);
				System.out.println("Amount: "+amount);
				error+=amount>sum?"Total amount should not exceed Sum Insured</br>":"";
				if((error==null || error.equals("")) && fieldsList.size()>0) 
				{
					for(int k=0; k<fieldsList.size(); k++)
					{
							if(flag)
							{
								OSB.deleteCoverageDetails(quoteNo, coverid, subcoverid, proId, schemeId, conTypId, destTable);
								flag=false;
							}
							OSB.insertCoverageDetails(quoteNo, coverid, subcoverid, proId, schemeId, conTypId, destTable, (java.util.HashMap)fieldsList.get(k));
					}
					msg="Saved Successfully";
				}else
				{
					System.out.println("Error: "+error);
					request.setAttribute("error", error);
					msg="";
				}
				request.setAttribute("coverValues", coverValues);
				System.out.println("coverValues controller: "+coverValues.length);
				request.setAttribute("msg", msg);
			}
			dispatcher=request.getRequestDispatcher("/OfficeInsurance/popup.jsp?conTypId="+conTypId+"&coverid="+coverid+"&subcoverid="+subcoverid+"&cover="+cover+"&addMore="+addMore+"&sumInsured="+sumInsured);
			System.out.println("CoverageDetails - Exit");
		}else if (requestfrom.equalsIgnoreCase("AddMore")) 
		{
			quoteNo = (String)session.getAttribute("quoteNo");
			String sumInsured = request.getParameter("sumInsured") == null ? "0": request.getParameter("sumInsured");
			String conTypId = request.getParameter("conTypId") == null ? "0": request.getParameter("conTypId");
			String cover = request.getParameter("cover") == null ? "": request.getParameter("cover");
			String coverid = request.getParameter("coverid") == null ? "": request.getParameter("coverid");
			String subcoverid = request.getParameter("subcoverid") == null ? "0": request.getParameter("subcoverid");
			String proId = (String) session.getAttribute("product_id");proId = proId == null ? "" : proId;
			schemeId = (String) session.getAttribute("scheme_id");schemeId = schemeId == null ? "" : schemeId;
			String add = request.getParameter("addMore") == null ? "3": request.getParameter("addMore");
			int addMore=Integer.parseInt(add);
			String[][] coverDetails=session.getAttribute("coverDetails")==null?new String[0][0]:(String[][])session.getAttribute("coverDetails");
			String[][] coverValues;
			String val="";
			addMore++;
			if(coverDetails.length>0)
			{
				coverValues=new String[addMore][coverDetails.length];
				for(int k=0; k<addMore; k++) 
				{
					for(int i=0; i<coverDetails.length; i++)
					{
						val=request.getParameter(coverDetails[i][0]+""+k)==null?"":request.getParameter(coverDetails[i][0]+""+k);
						coverValues[k][i]=val;
					}
				}
				request.setAttribute("coverValues", coverValues);
			}
			
			dispatcher=request.getRequestDispatcher("/OfficeInsurance/popup.jsp?conTypId="+conTypId+"&coverid="+coverid+"&subcoverid="+subcoverid+"&cover="+cover+"&addMore="+addMore+"&sumInsured="+sumInsured);
		}

		if (dispatcher != null){
			dispatcher.forward(request, response);
		}
	}

} // Class

