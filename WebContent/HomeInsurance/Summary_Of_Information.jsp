<%@ page import="java.util.*,java.io.*"%>
<jsp:useBean id="creatorBean" class="com.maan.Home.MasterController.PDFCreatorBean">
</jsp:useBean>
<jsp:useBean id= "productsBean" class = "com.maan.product.ProductSelection">
</jsp:useBean>
<jsp:useBean id="pdfDataSelect"
	class="com.maan.Home.DataManipualtion.HomePdfDataSelect">
</jsp:useBean>
<%@ page import="com.maan.DBCon.DBConnectionStatus"%>
<html>
	<head>
		<meta http-equiv="Content-Type"
			content="text/html; charset=iso-8859-1">
	</head>
	
	<%
		String pathh = request.getContextPath();
		String usrModeSC = (String) session.getAttribute("userLoginMode") == null ? "" : (String) session.getAttribute("userLoginMode");
		if (usrModeSC.length() > 0)
			DBConnectionStatus.statusStatic = usrModeSC;
		else {
			response.sendRedirect(pathh + "/login/error_messg.jsp");
			return;
		}
		RequestDispatcher dispatcher = null;
		String mode = request.getParameter("policyMode") == null ? "" : request.getParameter("policyMode");
		String quoteNo = request.getParameter("policynumber") == null ? "" : request.getParameter("policynumber");
		String loginId = request.getParameter("loginid") == null ? "" : request.getParameter("loginid");
		//for endorsement - karthick
		String endtStatus = request.getParameter("endtStatus") == null ? "" : "_"+request.getParameter("endtStatus");
		String endrNo = request.getParameter("endtStatus") == null ? "" : "&endorsementNo="+request.getParameter("endtStatus");
		String pid = "";
		if (session.getAttribute("product_id") != null)
			pid = (String) session.getAttribute("product_id");

		File checkFile;
		HashMap CoverageDetails = new HashMap();
		String coverage[][] = new String[0][0];
		coverage = pdfDataSelect.homeCoverageTransationSuminsuredSelect(quoteNo);
		creatorBean.setUserModeSC(usrModeSC);
		String branch = (String) session.getAttribute("LoginBranchCode");
		String typeOfProduct = session.getAttribute("typeOfProduct")==null?productsBean.getProductType(pid,branch):(String)session.getAttribute("typeOfProduct");
		HashMap brokerDetails = (HashMap) session.getAttribute("BrokerDetails");
		String currencyType = "";
		String cid = "";
		if (brokerDetails.size() > 0) {
			cid = (String)brokerDetails.get("Orgination");
			currencyType = (String) brokerDetails.get("CurrencyAbb");
		}
		if (coverage.length > 0) {
			int j = 0;
			for (int i = 0; i < coverage.length; i++) {
				CoverageDetails.put("CoverageDetails" + (j++),coverage[i][2]);
				CoverageDetails.put("CoverageDetails" + (j++),coverage[i][4]);
				CoverageDetails.put("CoverageDetails" + (j++),coverage[i][5]);
			}
		}
		session.setAttribute("CoverageDetails", CoverageDetails);
		String place = "";
		String headImage = "";
		String footImage = "";
		String signImage = "";
		String branchAddress = "";
		String branchName = "";
		String branchPO = "";
		String branchCountry = "";
		String branchCity = "";
		String currencyName = "";

		String placeCode[][] = new String[0][0];
		placeCode = pdfDataSelect.getPlaceCodes(branch, pid);
		if (placeCode.length > 0) {
			place = placeCode[0][0] == null ? "" : placeCode[0][0];
			headImage = placeCode[0][1] == null ? "" : placeCode[0][1];
			footImage = placeCode[0][2] == null ? "" : placeCode[0][2];
			signImage = placeCode[0][3] == null ? "" : placeCode[0][3];
			currencyName = placeCode[0][5] == null ? "" : placeCode[0][5];
			currencyType = placeCode[0][6] == null ? "" : placeCode[0][6];
			branchName = placeCode[0][8] == null ? "" : placeCode[0][8];
			branchPO = placeCode[0][10] == null ? "" : placeCode[0][10];
			branchCity = placeCode[0][11] == null ? "" : placeCode[0][11];
			branchCountry = placeCode[0][12] == null ? "" : placeCode[0][12];
		}
		branchAddress = branchPO + ", " + branchCity + ", " + branchCountry	+ ".";
		creatorBean.setPlace(place);
		creatorBean.setBranchName(branchName);
		creatorBean.setBranchAddress(branchAddress);
		creatorBean.setCurrencyName(currencyName);
		if (quoteNo.length() > 0 && mode.length() > 0) {
			String pdfPrefix = "";
			if("M".equalsIgnoreCase(typeOfProduct) && !mode.equalsIgnoreCase("Receipt")){
				pdfPrefix = "/PDFFile/Motor";
			}else if("T".equalsIgnoreCase(typeOfProduct) || ("M".equalsIgnoreCase(typeOfProduct) && mode.equalsIgnoreCase("Receipt"))){
				pdfPrefix = "/TravelPDFFile";
			}else if("O".equalsIgnoreCase(typeOfProduct)){
				pdfPrefix = "/PDFFile/Office";
			}else{
				pdfPrefix = "/PDFFile";
			}
			if (mode.equalsIgnoreCase("Schedule")) {
				String disStatus = pdfDataSelect.getPDFStatus(quoteNo);
				String pdfFilePath = "";
				if (disStatus.length() <= 0)
					pdfFilePath = request.getSession().getServletContext().getRealPath(pdfPrefix + "/Schedule/PremiumSummary_Schedule_" + quoteNo + endtStatus +".pdf");
				else if (disStatus.equalsIgnoreCase("Original Copy"))
					pdfFilePath = request.getSession().getServletContext().getRealPath(pdfPrefix + "/Schedule/PremiumSummary_Schedule_" + quoteNo + endtStatus +".pdf");
				else if (disStatus.equalsIgnoreCase("Copy"))
					pdfFilePath = request.getSession().getServletContext().getRealPath(pdfPrefix + "/Schedule/PremiumSummary_Schedule_" + quoteNo + endtStatus + ".pdf");
				checkFile = new File(pdfFilePath);
				if (!checkFile.exists()) {
					if (!pid.equalsIgnoreCase("65") && !pid.equalsIgnoreCase("75")) {
						creatorBean.setQuoteNo(quoteNo);
						creatorBean.setLoginId(loginId);
						creatorBean.setPid(pid);
						creatorBean.setPdfDisplay(disStatus);
						creatorBean.setBranch(branch);
						creatorBean.setCurrencyType(currencyType);
						creatorBean.setCoverageDetails(CoverageDetails);
						creatorBean.setHeaderImagePath(request.getSession().getServletContext().getRealPath("/" + "/images/" + headImage));
						creatorBean.setFooterImagePath(request.getSession().getServletContext().getRealPath("/" + "/images/" + footImage));
						if (disStatus.length() <= 0)
							creatorBean.setFilePath(request.getSession().getServletContext().getRealPath("/" + pdfPrefix + "/Schedule/PremiumSummary_Schedule_" + quoteNo + endtStatus + ".pdf"));
						else if (disStatus.equalsIgnoreCase("Original Copy"))
							creatorBean.setFilePath(request.getSession().getServletContext().getRealPath("/" + pdfPrefix + "/Schedule/Original_Copy/PremiumSummary_Schedule_" + quoteNo + endtStatus + ".pdf"));
						else if (disStatus.equalsIgnoreCase("Copy"))
							creatorBean.setFilePath(request.getSession().getServletContext().getRealPath("/" + pdfPrefix + "/Schedule/Copy/PremiumSummary_Schedule_" + quoteNo + endtStatus + ".pdf"));
						creatorBean.setSignedImagePath(request.getSession().getServletContext().getRealPath("/" + "/images/" + signImage));
						creatorBean.writePDF("Schedule");
					}else{
						dispatcher=request.getRequestDispatcher("../servlet/motorPdf?qNo="+quoteNo+"&policyStatus=Schedule"+endrNo);
					    dispatcher.forward(request,response);
					    return;
					}
				}
				if (disStatus.length() <= 0)
					response.sendRedirect(request.getContextPath()+ pdfPrefix + "/Schedule/PremiumSummary_Schedule_" + quoteNo +  endtStatus +".pdf");
				else if (disStatus.equalsIgnoreCase("Original Copy"))
					response.sendRedirect(request.getContextPath()+ pdfPrefix + "/Schedule/PremiumSummary_Schedule_" + quoteNo + endtStatus + ".pdf");
				else if (disStatus.equalsIgnoreCase("Copy"))
					response.sendRedirect(request.getContextPath()+ pdfPrefix + "/Schedule/PremiumSummary_Schedule_" + quoteNo + endtStatus + ".pdf");
			} else if (mode.equalsIgnoreCase("Draft")) {
				checkFile = new File(request.getSession().getServletContext().getRealPath(pdfPrefix + "/Draft/PremiumSummary_Draft_" + quoteNo + endtStatus + ".pdf"));
				if (!checkFile.exists()) {
					if (!pid.equalsIgnoreCase("65") && !pid.equalsIgnoreCase("75")) {
						creatorBean.setQuoteNo(quoteNo);
						creatorBean.setLoginId(loginId);
						creatorBean.setPid(pid);
						creatorBean.setBranch(branch);
						creatorBean.setCurrencyType(currencyType);
						creatorBean.setCoverageDetails(CoverageDetails);
						creatorBean.setHeaderImagePath(request.getSession().getServletContext().getRealPath("/" + "/images/" + headImage));
						creatorBean.setFooterImagePath(request.getSession().getServletContext().getRealPath("/" + "/images/" + footImage));
						creatorBean.setFilePath(request.getSession().getServletContext().getRealPath("/" + pdfPrefix + "/Draft/PremiumSummary_Draft_" + quoteNo + endtStatus + ".pdf"));
						creatorBean.setSignedImagePath(request.getSession().getServletContext().getRealPath("/" + "/images/" + signImage));
						creatorBean.writePDF("Draft");
					}else{
						dispatcher=request.getRequestDispatcher("../servlet/motorPdf?qNo="+quoteNo+"&policyStatus=Draft"+endrNo);
					    dispatcher.forward(request,response);
					    return;
					}
					response.sendRedirect(request.getContextPath()+ pdfPrefix + "/Draft/PremiumSummary_Draft_" + quoteNo + endtStatus + ".pdf");
				} 
				else
					response.sendRedirect(request.getContextPath()+ pdfPrefix + "/Draft/PremiumSummary_Draft_" + quoteNo + endtStatus + ".pdf");
			} else if (mode.equalsIgnoreCase("Debit")) {				
				checkFile = new File(request.getSession().getServletContext().getRealPath(pdfPrefix + "/Debit/PremiumSummary_Debit_" + quoteNo + endtStatus + ".pdf"));
				if (!checkFile.exists()) {
					if (!pid.equalsIgnoreCase("65") && !pid.equalsIgnoreCase("75")) {
						creatorBean.setQuoteNo(quoteNo);
						creatorBean.setLoginId(loginId);
						creatorBean.setPid(pid);
						creatorBean.setBranch(branch);
						creatorBean.setCurrencyType(currencyType);
						creatorBean.setCoverageDetails(CoverageDetails);
						creatorBean.setHeaderImagePath(request.getSession().getServletContext().getRealPath("/" + "/images/" + headImage));
						creatorBean.setFooterImagePath(request.getSession().getServletContext().getRealPath("/" + "/images/" + footImage));
						creatorBean.setFilePath(request.getSession().getServletContext().getRealPath("/" + pdfPrefix + "/Debit/PremiumSummary_Debit_" + quoteNo + endtStatus + ".pdf"));
						creatorBean.setSignedImagePath(request.getSession().getServletContext().getRealPath("/" + "/images/" + signImage));
						creatorBean.writePDF("Debit");
					}else{
						dispatcher=request.getRequestDispatcher("../servlet/motorPdf?qNo="+quoteNo+"&policyStatus=Debit"+endrNo);
					    dispatcher.forward(request,response);
					    return;
					}
					response.sendRedirect(request.getContextPath()+pdfPrefix + "/Debit/PremiumSummary_Debit_"+ quoteNo + endtStatus +".pdf");
				}
				else
					response.sendRedirect(request.getContextPath()+ pdfPrefix + "/Debit/PremiumSummary_Debit_" + quoteNo + endtStatus +".pdf");
			}
			else if(mode.equalsIgnoreCase("Receipt"))
			{
				checkFile = new File(request.getSession().getServletContext().getRealPath("/"+pdfPrefix +"/Receipt/PremiumSummary_Receipt_"+quoteNo+ endtStatus +".pdf"));
				if(!checkFile.exists())
				{
					if (!pid.equalsIgnoreCase("65") && !pid.equalsIgnoreCase("75")) {
						creatorBean.setQuoteNo(quoteNo);
						creatorBean.setLoginId(loginId);
						creatorBean.setPid(pid);
						creatorBean.setCoverageDetails(CoverageDetails);
						creatorBean.setBranch(branch);
						creatorBean.setCurrencyType(currencyType);
						creatorBean.setHeaderImagePath(request.getSession().getServletContext().getRealPath("/"+"/images/" + headImage));
						creatorBean.setFooterImagePath(request.getSession().getServletContext().getRealPath("/"+"/images/" + footImage));
						creatorBean.setFilePath(request.getSession().getServletContext().getRealPath("/"+pdfPrefix +"/Receipt/PremiumSummary_Receipt_"+quoteNo+ endtStatus +".pdf"));
						creatorBean.setSignedImagePath(request.getSession().getServletContext().getRealPath("/"+"/images/" + signImage));
						creatorBean.writePDF("Receipt");
						response.sendRedirect(request.getContextPath()+"/Receipt/PremiumSummary_Receipt_"+quoteNo+ endtStatus +".pdf");
					}else{
						//dispatcher=request.getRequestDispatcher("../TravelInsurance/ViewPolicies.jsp?PdfModee=Receipt&quoteNumber="+quoteNo+"&loginId="+loginId);
						dispatcher=request.getRequestDispatcher("../servlet/motorPdf?qNo="+quoteNo+"&policyStatus=Receipt"+endrNo);
					    dispatcher.forward(request,response);
					    return;
					}
				}else{
					response.sendRedirect(request.getContextPath()+pdfPrefix +"/Receipt/PremiumSummary_Receipt_"+quoteNo+ endtStatus +".pdf");
				}
			}
		}
	%>
