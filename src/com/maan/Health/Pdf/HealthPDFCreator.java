package com.maan.Health.Pdf;

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
import com.maan.common.LogManager;
import com.maan.common.exception.BaseException;

public class HealthPDFCreator extends HttpServlet implements Serializable
{
	private static final long serialVersionUID = 1L;
	PrintWriter out=null;
	HealthPDFCreatorBean creatorBean = new HealthPDFCreatorBean();
	
	public void init(ServletConfig  config)throws ServletException
	{
		LogManager.info("Health PDF creator");
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
			placeCode = hpdf.getPlaceCodes(branch,pid);
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
				creatorBean.setHeaderImagePath(request.getSession().getServletContext().getRealPath("/"+"/images/"+headImage));
				creatorBean.setFooterImagePath(request.getSession().getServletContext().getRealPath("/"+"/images/"+footImage));

				if(disStatus.length()<=0){
					creatorBean.setFilePath(request.getSession().getServletContext().getRealPath("/"+"/HealthPDFFile/Schedule/PremiumSummary_Schedule_"+QuoteNo+".pdf"));
				}
				else if(disStatus.equalsIgnoreCase("Original Copy")){
					creatorBean.setFilePath(request.getSession().getServletContext().getRealPath("/"+"/HealthPDFFile/Schedule/Original_Copy/PremiumSummary_Schedule_"+QuoteNo+".pdf"));
				}
				else if(disStatus.equalsIgnoreCase("Copy")){
					creatorBean.setFilePath(request.getSession().getServletContext().getRealPath("/"+"/HealthPDFFile/Schedule/Copy/PremiumSummary_Schedule_"+QuoteNo+".pdf"));
				}
				creatorBean.setSignedImagePath(request.getSession().getServletContext().getRealPath("/"+"/images/"+signImage));
				creatorBean.setFormatpath(request.getSession().getServletContext().getRealPath("/"+"/ScheduleFont/arialuni.ttf"));
				creatorBean.writePDF("Schedule");

				if(disStatus.length()<=0){
					response.sendRedirect(request.getContextPath()+"/HealthPDFFile/Schedule/PremiumSummary_Schedule_"+QuoteNo+".pdf");
				}
				else if(disStatus.equalsIgnoreCase("Original Copy")){
					response.sendRedirect(request.getContextPath()+"/HealthPDFFile/Schedule/Original_Copy/PremiumSummary_Schedule_"+QuoteNo+".pdf");
				}
				else if(disStatus.equalsIgnoreCase("Copy")){
					response.sendRedirect(request.getContextPath()+"/HealthPDFFile/Schedule/Copy/PremiumSummary_Schedule_"+QuoteNo+".pdf");
				}
			}
			else if(option.equalsIgnoreCase("Draft"))
			{
				creatorBean.setQuoteNo(QuoteNo);
				creatorBean.setLoginId(loginId);
				creatorBean.setPid(pid);
				creatorBean.setBranch(branch);
				creatorBean.setCurrencyType(currencyType);
				creatorBean.setHeaderImagePath(request.getSession().getServletContext().getRealPath("/"+"/images/"+headImage));
				creatorBean.setFooterImagePath(request.getSession().getServletContext().getRealPath("/"+"/images/"+footImage));
				creatorBean.setFilePath(request.getSession().getServletContext().getRealPath("/"+"/HealthPDFFile/Draft/PremiumSummary_Draft_"+QuoteNo+".pdf"));
				creatorBean.setSignedImagePath(request.getSession().getServletContext().getRealPath("/"+"/images/"+signImage));
				creatorBean.writePDF("Draft");
				response.sendRedirect(request.getContextPath()+"/HealthPDFFile/Draft/PremiumSummary_Draft_"+QuoteNo+".pdf");
			}
			else if(option.equalsIgnoreCase("Debit"))
			{
				creatorBean.setQuoteNo(QuoteNo);
				creatorBean.setLoginId(loginId);
				creatorBean.setPid(pid);
				creatorBean.setTravelProductId(pid);
				creatorBean.setBranch(branch);
				creatorBean.setCurrencyType(currencyType);
				creatorBean.setHeaderImagePath(request.getSession().getServletContext().getRealPath("/"+"/images/"+headImage));
				creatorBean.setFooterImagePath(request.getSession().getServletContext().getRealPath("/"+"/images/"+footImage));
				creatorBean.setFilePath(request.getSession().getServletContext().getRealPath("/"+"/HealthPDFFile/Debit/PremiumSummary_Debit_"+QuoteNo+".pdf"));
				creatorBean.setSignedImagePath(request.getSession().getServletContext().getRealPath("/"+"/images/"+signImage));
				creatorBean.writePDF("Debit");
				response.sendRedirect(request.getContextPath()+"/HealthPDFFile/Debit/PremiumSummary_Debit_"+QuoteNo+".pdf");
			}
			else if(option.equalsIgnoreCase("Receipt"))
			{
				creatorBean.setQuoteNo(QuoteNo);
				creatorBean.setLoginId(loginId);
				creatorBean.setPid(pid);
				creatorBean.setBranch(branch);
				creatorBean.setCurrencyType(currencyType);
				creatorBean.setModeOfPurchase(request.getParameter("modeOfPurchase"));
				creatorBean.setHeaderImagePath(request.getSession().getServletContext().getRealPath("/"+"/images/"+headImage));
				creatorBean.setFooterImagePath(request.getSession().getServletContext().getRealPath("/"+"/images/"+footImage));
				creatorBean.setFilePath(request.getSession().getServletContext().getRealPath("/"+"/HealthPDFFile/Receipt/PremiumSummary_Receipt_"+QuoteNo+".pdf"));
				creatorBean.setSignedImagePath(request.getSession().getServletContext().getRealPath("/"+"/images/"+signImage));
				creatorBean.writePDF("Receipt");
				response.sendRedirect(request.getContextPath()+"/HealthPDFFile/Receipt/PremiumSummary_Receipt_"+QuoteNo+".pdf");
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
				creatorBean.setFilePath(request.getSession().getServletContext().getRealPath("/"+"/HealthPDFFile/PrintQuote/PremiumSummary_PrintQuote_"+QuoteNo+".pdf"));
				creatorBean.setSignedImagePath(request.getSession().getServletContext().getRealPath("/"+"/images/"+signImage));
				creatorBean.writePDF("PrintQuote");
				response.sendRedirect(request.getContextPath()+"/HealthPDFFile/PrintQuote/PremiumSummary_PrintQuote_"+QuoteNo+".pdf");
			}
			else if(option.equalsIgnoreCase("All"))
			{
				creatorBean.setQuoteNo(QuoteNo);
				creatorBean.setLoginId(loginId);
				creatorBean.setPid(pid);
				creatorBean.setHeaderImagePath(request.getSession().getServletContext().getRealPath("/"+"/images/"+headImage));
				creatorBean.setFooterImagePath(request.getSession().getServletContext().getRealPath("/"+"/images/"+footImage));
				creatorBean.setSignedImagePath(request.getSession().getServletContext().getRealPath("/"+"/images/"+signImage));
				creatorBean.setBranch(branch);
				creatorBean.setCurrencyType(currencyType);
				creatorBean.setTravelProductId(pid);
				creatorBean.setFilePath(request.getSession().getServletContext().getRealPath("/"+"/HealthPDFFile/Schedule/PremiumSummary_Schedule_"+QuoteNo+".pdf"));
				creatorBean.setAll("All");
				creatorBean.writePDF("Schedule");
				creatorBean.setAll("");
				creatorBean.setFilePath(request.getSession().getServletContext().getRealPath("/"+"/HealthPDFFile/Draft/PremiumSummary_Draft_"+QuoteNo+".pdf"));
				creatorBean.writePDF("Draft");
				creatorBean.setFilePath(request.getSession().getServletContext().getRealPath("/"+"/HealthPDFFile/Debit/PremiumSummary_Debit_"+QuoteNo+".pdf"));
				creatorBean.writePDF("Debit");
				creatorBean.setFilePath(request.getSession().getServletContext().getRealPath("/"+"/HealthPDFFile/Receipt/PremiumSummary_Receipt_"+QuoteNo+".pdf"));
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