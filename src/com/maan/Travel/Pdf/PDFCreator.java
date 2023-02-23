package com.maan.Travel.Pdf;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.HashMap;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.maan.DBCon.DBConnectionStatus;
import com.maan.Home.DataManipualtion.HomePdfDataSelect;
import com.maan.Travel.DAO.TravelBean;
import com.maan.adminnew.common.CommonService;
import com.maan.common.LogManager;
import com.maan.common.exception.BaseException;


public class PDFCreator extends HttpServlet implements Serializable
{
	private static final long serialVersionUID = 1L;
	PrintWriter out=null;
	PDFCreatorBean creatorBean = new PDFCreatorBean();
	
	public void init(ServletConfig  config)throws ServletException
	{
		LogManager.info("Travel PDF creator");
	}
	public void doPost(final HttpServletRequest request,final HttpServletResponse response)throws ServletException, IOException 
	{
		try{
			processResult(request,response);
		}catch(Exception e){
			LogManager.debug(e);
		}
	}
	public void doGet(final HttpServletRequest request,final HttpServletResponse response)throws ServletException, IOException 
	{
		try{
			processResult(request,response);
		}catch(Exception e){
			LogManager.debug(e);
		}
	}
	public void processResult(final HttpServletRequest request,final HttpServletResponse response)throws Exception ,ServletException, IOException,BaseException
	{
		try
		{
			response.setContentType("text/html");
			out = response.getWriter();
			HttpSession session=null;
			session = request.getSession(true);
			final String usrModeSC=(String)session.getAttribute("userLoginMode")==null?"":(String)session.getAttribute("userLoginMode");
			DBConnectionStatus.statusStatic=usrModeSC;
			creatorBean.setUserModeSC(usrModeSC);
			String pid = request.getParameter("selectProd")!=null?"":request.getParameter("selectProd");
			creatorBean.setFormatpath(request.getSession().getServletContext().getRealPath("/"+"/ScheduleFont/arialuni.ttf"));
			if(pid == null){
				pid = (String)session.getAttribute("product_id");
			}
			String QuoteNo = "";
			if(session.getAttribute("QuoteNo") != null)
			{
				QuoteNo = (String)session.getAttribute("QuoteNo");
				session.setAttribute("QuoteNo",QuoteNo);
			}
			String loginId = "";
			if(session.getAttribute("user")!=null)
			{
				loginId = (String)session.getAttribute("user");
				session.setAttribute("user",loginId);
			}
			String option = "";
			if(request.getAttribute("Option")!=null){
				option = (String)request.getAttribute("Option");
			}
			String travelProductId = pid;
			/*if(session.getAttribute("TravelProductId")!=null){
				travelProductId = (String)session.getAttribute("TravelProductId")==null?"31":(String)session.getAttribute("TravelProductId");
			}
			if(travelProductId.length()<=0)
				travelProductId="31";*/
			
			if(QuoteNo ==null || QuoteNo.length()<=0 )
			  { QuoteNo=request.getParameter("quoteNo");}
			final String branch = (String)session.getAttribute("LoginBranchCode");
			final HashMap brokerDetails = (HashMap)session.getAttribute("BrokerDetails");
			String cid = "";
			String currencyType = "";
			if(brokerDetails.size()>0)
			{
				cid = (String)brokerDetails.get("Orgination");
				currencyType = (String)brokerDetails.get("CurrencyAbb");
			}
			String place = "";
			String headImage = "";
			String footImage = "";
			String signImage = "";
			String branchAddress = "";
			String branchAddress1 = "";
			String branchName = "";
			String branchPO = "";
			String branchCountry = "";
			/*String stampImage = "";
			String cols = "";
			String branchPhone = "";
			String branchFax = "";*/
			String branchCity = "";
			String currencyName = "";
			String placeCode[][] = new String[0][0];
			final HomePdfDataSelect hpdf = new HomePdfDataSelect();
			placeCode = hpdf.getPlaceCodes(branch,travelProductId);
			if(placeCode.length>0)
			{
				place      = placeCode[0][0] == null ? "" : placeCode[0][0];
				headImage  = placeCode[0][1] == null ? "" : placeCode[0][1];
				footImage  = placeCode[0][2] == null ? "" : placeCode[0][2];
				signImage  = placeCode[0][3] == null ? "" : placeCode[0][3];
				currencyName = placeCode[0][5] == null ? "" : placeCode[0][5];
				currencyType = placeCode[0][6] == null ? "" : placeCode[0][6];
				branchName = placeCode[0][8] == null ? "" : placeCode[0][8];
				branchPO = placeCode[0][10] == null ? "" : placeCode[0][10];
				branchCity = placeCode[0][11] == null ? "" : placeCode[0][11];
				branchCountry = placeCode[0][12] == null ? "" : placeCode[0][12];
				/*stampImage = placeCode[0][4] == null ? "" : placeCode[0][4];
				cols = placeCode[0][9] == null ? "" : placeCode[0][9];
				branchPhone = placeCode[0][13] == null ? "" : placeCode[0][13];
				branchFax = placeCode[0][14] == null ? "" : placeCode[0][14];*/
			}
			branchAddress = branchPO+",";
			branchAddress1 = branchCity+", "+branchCountry+".";
			creatorBean.setPlace(place);
			creatorBean.setBranchName(branchName);
			creatorBean.setBranchAddress(branchAddress);
			creatorBean.setBranchAddress1(branchAddress1);
			creatorBean.setCurrencyName(currencyName);
			creatorBean.setCid(cid);
			
			if(option.equalsIgnoreCase("Schedule"))
			{
				String disStatus = creatorBean.getPDFStatus(QuoteNo);
				creatorBean.setQuoteNo(QuoteNo);
				creatorBean.setLoginId(loginId);
				creatorBean.setPid(pid);
				creatorBean.setPdfDisplay(disStatus);
				creatorBean.setBranch(branch);
				creatorBean.setCurrencyType(currencyType);
				//creatorBean.setTravelDetails(travelDetails);
				creatorBean.setHeaderImagePath(request.getSession().getServletContext().getRealPath("/"+"/images/"+headImage));
				creatorBean.setFooterImagePath(request.getSession().getServletContext().getRealPath("/"+"/images/"+footImage));

				if(disStatus.length()<=0){
					creatorBean.setFilePath(request.getSession().getServletContext().getRealPath("/"+"/TravelPDFFile/Schedule/PremiumSummary_Schedule_"+QuoteNo+".pdf"));
				}
				else if(disStatus.equalsIgnoreCase("Original Copy")){
					creatorBean.setFilePath(request.getSession().getServletContext().getRealPath("/"+"/TravelPDFFile/Schedule/Original_Copy/PremiumSummary_Schedule_"+QuoteNo+".pdf"));
				}
				else if(disStatus.equalsIgnoreCase("Copy")){
					creatorBean.setFilePath(request.getSession().getServletContext().getRealPath("/"+"/TravelPDFFile/Schedule/Copy/PremiumSummary_Schedule_"+QuoteNo+".pdf"));
				}
				creatorBean.setSignedImagePath(request.getSession().getServletContext().getRealPath("/"+"/images/"+signImage));
				creatorBean.setFormatpath(request.getSession().getServletContext().getRealPath("/"+"/ScheduleFont/arialuni.ttf"));
				//creatorBean.writePDF("Schedule");
				
				//String applicationNo = creatorBean.getApplicationNo(QuoteNo);
				/*com.maan.report.JasperReports jr = new com.maan.report.JasperReports();
				jr.motorSchedule(QuoteNo, pid, branch, creatorBean.getFilePath());*/
				if(disStatus.length()<=0){
					response.sendRedirect(request.getContextPath()+"/TravelPDFFile/Schedule/PremiumSummary_Schedule_"+QuoteNo+".pdf");
				}
				else if(disStatus.equalsIgnoreCase("Original Copy")){
					response.sendRedirect(request.getContextPath()+"/TravelPDFFile/Schedule/Original_Copy/PremiumSummary_Schedule_"+QuoteNo+".pdf");
				}
				else if(disStatus.equalsIgnoreCase("Copy")){
					response.sendRedirect(request.getContextPath()+"/TravelPDFFile/Schedule/Copy/PremiumSummary_Schedule_"+QuoteNo+".pdf");
				}
			/*Mississippi Integration block start
				HomeTravelMissippiEngine mississippiEngine = new HomeTravelMissippiEngine();
				mississippiEngine.setQuoteNo(QuoteNo);
				mississippiEngine.setProductId(travelProductId);
				mississippiEngine.setLoginBra(branch);
				mississippiEngine.setBcid(cid);
				mississippiEngine.LoadingData();
			//Mississippi Integration block start
				String policyMail = request.getParameter("policyMail")!=null?request.getParameter("policyMail"):"";
				if(policyMail.equalsIgnoreCase("Y"))
				{
					RequestDispatcher dispatcher=request.getRequestDispatcher("*.SchedulePDFMail");
					dispatcher.include(request,response);
				}*/
			}
			else if(option.equalsIgnoreCase("Draft"))
			{
				creatorBean.setQuoteNo(QuoteNo);
				creatorBean.setLoginId(loginId);
				creatorBean.setPid(pid);
				creatorBean.setBranch(branch);
				creatorBean.setCurrencyType(currencyType);
				//creatorBean.setTravelDetails(travelDetails);
				creatorBean.setHeaderImagePath(request.getSession().getServletContext().getRealPath("/"+"/images/"+headImage));
				creatorBean.setFooterImagePath(request.getSession().getServletContext().getRealPath("/"+"/images/"+footImage));
				creatorBean.setFilePath(request.getSession().getServletContext().getRealPath("/"+"/TravelPDFFile/Draft/PremiumSummary_Draft_"+QuoteNo+".pdf"));
				creatorBean.setSignedImagePath(request.getSession().getServletContext().getRealPath("/"+"/images/"+signImage));
				//creatorBean.writePDF("Draft");
				String filePath = CommonService.getApplicationPath() +"/TravelPDFFile/Draft/PremiumSummary_Draft_"+QuoteNo+".pdf";
				/*String applicationNo = creatorBean.getApplicationNo(QuoteNo);
				com.maan.report.JasperReports jr = new com.maan.report.JasperReports();
				jr.motorSchedule(applicationNo, pid, branch, filePath);*/
				//response.sendRedirect(request.getContextPath()+"/TravelPDFFile/Draft/PremiumSummary_Draft_"+QuoteNo+".pdf");
				response.sendRedirect(request.getContextPath()+"/TravelPDFFile/Draft/PremiumSummary_Draft_"+QuoteNo+".pdf");
			}
			else if(option.equalsIgnoreCase("Debit"))
			{
				//out.println("Royal Test in Option Debit");if(true)return;
				creatorBean.setQuoteNo(QuoteNo);
				creatorBean.setLoginId(loginId);
				creatorBean.setPid(pid);
				creatorBean.setTravelProductId(travelProductId);
				creatorBean.setBranch(branch);
				creatorBean.setCurrencyType(currencyType);
				creatorBean.setHeaderImagePath(request.getSession().getServletContext().getRealPath("/"+"/images/"+headImage));
				creatorBean.setFooterImagePath(request.getSession().getServletContext().getRealPath("/"+"/images/"+footImage));
				creatorBean.setFilePath(request.getSession().getServletContext().getRealPath("/"+"/TravelPDFFile/Debit/PremiumSummary_Debit_"+QuoteNo+".pdf"));
				creatorBean.setSignedImagePath(request.getSession().getServletContext().getRealPath("/"+"/images/"+signImage));
				creatorBean.writePDF("Debit");
				response.sendRedirect(request.getContextPath()+"/TravelPDFFile/Debit/PremiumSummary_Debit_"+QuoteNo+".pdf");
			}
			else if(option.equalsIgnoreCase("Receipt"))
			{
				//String receipt_no = request.getParameter("receipt_no")!=null?request.getParameter("receipt_no"):"";
				//String modeOfPurchase = com.maan.services.util.runner.singleSelection("select nvl(payment_mode,'Cash') from HOME_POSITION_MASTER where quote_no='"+QuoteNo+"'");
				creatorBean.setQuoteNo(QuoteNo);
				creatorBean.setLoginId(loginId);
				creatorBean.setPid(pid);
				//creatorBean.setReceiptNo(receipt_no);
				creatorBean.setBranch(branch);
				creatorBean.setCurrencyType(currencyType);
				creatorBean.setModeOfPurchase(request.getParameter("modeOfPurchase"));
				creatorBean.setHeaderImagePath(request.getSession().getServletContext().getRealPath("/"+"/images/"+headImage));
				creatorBean.setFooterImagePath(request.getSession().getServletContext().getRealPath("/"+"/images/"+footImage));
				creatorBean.setFilePath(request.getSession().getServletContext().getRealPath("/"+"/TravelPDFFile/Receipt/PremiumSummary_Receipt_"+QuoteNo+".pdf"));
				creatorBean.setSignedImagePath(request.getSession().getServletContext().getRealPath("/"+"/images/"+signImage));
				creatorBean.writePDF("Receipt");
			//Mississippi Integration block start
			/*	HomeTravelMissippiEngine mississippiEngine = new HomeTravelMissippiEngine();
				mississippiEngine.setQuoteNo(QuoteNo);
				mississippiEngine.setProductId(travelProductId);
				mississippiEngine.setLoginBra(branch);
				mississippiEngine.setBcid(cid);
				mississippiEngine.receiptInsertion("22","Travel");*/
			//Mississippi Integration block start
				
				response.sendRedirect(request.getContextPath()+"/TravelPDFFile/Receipt/PremiumSummary_Receipt_"+QuoteNo+".pdf");
			}
	        else if(option.equalsIgnoreCase("PrintQuote"))
			{        
	
                String userType = "";
                if(session.getAttribute("mode")!=null){
                   userType = (String)session.getAttribute("mode");
                }
                creatorBean.setUserType(userType);
				creatorBean.setQuoteNo(QuoteNo);
				creatorBean.setLoginId(loginId);
				creatorBean.setPid(pid);
				creatorBean.setBranch(branch);
				creatorBean.setCurrencyType(currencyType);
				creatorBean.setHeaderImagePath(request.getSession().getServletContext().getRealPath("/"+"/images/"+headImage));
				creatorBean.setFooterImagePath(request.getSession().getServletContext().getRealPath("/"+"/images/"+footImage));
				creatorBean.setFilePath(request.getSession().getServletContext().getRealPath("/"+"/TravelPDFFile/PrintQuote/PremiumSummary_PrintQuote_"+QuoteNo+".pdf"));
				creatorBean.setSignedImagePath(request.getSession().getServletContext().getRealPath("/"+"/images/"+signImage));
				creatorBean.writePDF("PrintQuote");
				response.sendRedirect(request.getContextPath()+"/TravelPDFFile/PrintQuote/PremiumSummary_PrintQuote_"+QuoteNo+".pdf");
				/*String quoteMail = request.getParameter("quoteMail")!=null?request.getParameter("quoteMail"):"";
				String email = request.getParameter("email")!=null?request.getParameter("email"):"";
				System.out.println("PDFCreator for PrintQuote..quoteMail..."+quoteMail);
				if(quoteMail.equalsIgnoreCase("Y"))
				{
					RequestDispatcher dispatcher=request.getRequestDispatcher("/CustomerTravelMail.Customer?QuoteNo="+QuoteNo+"&proid="+pid+"&email"+email);
					dispatcher.include(request,response);
				}*/
			}
			else if(option.equalsIgnoreCase("All"))
			{
				//String receipt_no = request.getParameter("receipt_no")!=null?request.getParameter("receipt_no"):"";
			//Mississippi Integration block start
			/*	HomeTravelMissippiEngine mississippiEngine = new HomeTravelMissippiEngine();
				mississippiEngine.setQuoteNo(QuoteNo);
				mississippiEngine.setProductId(travelProductId);
				mississippiEngine.setLoginBra(branch);
				mississippiEngine.setBcid(cid);
				mississippiEngine.LoadingData();*/
			//Mississippi Integration block start
				creatorBean.setQuoteNo(QuoteNo);
				creatorBean.setLoginId(loginId);
				creatorBean.setPid(pid);
				creatorBean.setHeaderImagePath(request.getSession().getServletContext().getRealPath("/"+"/images/"+headImage));
				creatorBean.setFooterImagePath(request.getSession().getServletContext().getRealPath("/"+"/images/"+footImage));
				creatorBean.setSignedImagePath(request.getSession().getServletContext().getRealPath("/"+"/images/"+signImage));
				creatorBean.setBranch(branch);
				creatorBean.setCurrencyType(currencyType);
				//creatorBean.setModeOfPurchase(modeOfPurchase);
				//creatorBean.setReceiptNo(receipt_no);
				creatorBean.setTravelProductId(travelProductId);
				creatorBean.setFilePath(request.getSession().getServletContext().getRealPath("/"+"/TravelPDFFile/Schedule/PremiumSummary_Schedule_"+QuoteNo+".pdf"));
				creatorBean.setAll("All");
				creatorBean.writePDF("Schedule");
				creatorBean.setAll("");
				creatorBean.setFilePath(request.getSession().getServletContext().getRealPath("/"+"/TravelPDFFile/Draft/PremiumSummary_Draft_"+QuoteNo+".pdf"));
				creatorBean.writePDF("Draft");
				creatorBean.setFilePath(request.getSession().getServletContext().getRealPath("/"+"/TravelPDFFile/Debit/PremiumSummary_Debit_"+QuoteNo+".pdf"));
				creatorBean.writePDF("Debit");
				creatorBean.setFilePath(request.getSession().getServletContext().getRealPath("/"+"/TravelPDFFile/Receipt/PremiumSummary_Receipt_"+QuoteNo+".pdf"));
				creatorBean.writePDF("Receipt");
			}
			else if(option.equalsIgnoreCase("PolicyWording"))
			{
				TravelBean travelBean = new TravelBean();
				String policyWord = travelBean.getClaimPhPolicyWord(branch, "PolicyWording");
				String filePath = request.getContextPath()+"/PDFFile/PolicyWording/"+policyWord;
				response.sendRedirect(filePath);
			}
		}catch(BaseException e){
			throw new BaseException(e,"Error");
		}
	}
}// Class