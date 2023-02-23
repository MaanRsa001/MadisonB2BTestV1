package com.maan.Office.Controller;

import com.maan.Office.DAO.OfficeShieldBean;
import com.maan.Office.DAO.getCoverageInfo;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.util.*;
public class GenerateValue extends HttpServlet
{

    public GenerateValue()
    {
    }

    public void destroy()
    {
        super.destroy();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
        System.out.println("hello doget");
        ProcessResult(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
        System.out.println("hello dopost");
        ProcessResult(request, response);
    }

    public void ProcessResult(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
        RequestDispatcher rd = null;
        response.setContentType("text/html");
        java.io.PrintWriter out = response.getWriter();
        HttpSession session = request.getSession(false);
        RequestDispatcher dispatcher = null;
       /* RequestDispatcher dispatcher1 = request.getRequestDispatcher("/login/sessionsCheckNormalHome.jsp");
        if(dispatcher1 != null)
        {
            dispatcher1.include(request, response);
        }*/
        String mainCoverValues[][] = new String[0][0];
        String subCoverValues[][] = new String[0][0];
        String Values[][] = new String[0][0];
        String existReferal[][] = new String[0][0];
        String cusInfo[][] = new String[0][0];
        String single_Value[] = new String[0];
        String referalVal[] = new String[0];
        String userType = "";
        String offPath = request.getContextPath();
        userType = (String)session.getAttribute("user1");
        userType = userType != null ? userType : "";
        String linkFrom = (String)request.getAttribute("linkFrom") != null ? (String)request.getAttribute("linkFrom") : "";
        if(linkFrom == null || linkFrom.length() <= 0)
        {
            linkFrom = request.getParameter("linkFrom") != null ? request.getParameter("linkFrom") : "";
        }
        String quoteNo = (String)request.getAttribute("quoteNo") != null ? (String)request.getAttribute("quoteNo") : request.getParameter("quoteNo") != null ? request.getParameter("quoteNo") : "";
        if(quoteNo == null || quoteNo.length() <= 0)
        {
            quoteNo = request.getParameter("QuoteNo") != null ? request.getParameter("QuoteNo") : "";
        }
        String customerID = (String)request.getAttribute("customerId") != null ? (String)request.getAttribute("customerId") : request.getParameter("customer") != null ? request.getParameter("customer") : "";
        String contents_office = "0";
        String referal = "";
        String activity_prof = "";
        String activity_prof_exc = "";
        String activity_status = "";
        String refCover_ids = "";
        String error = "";
        System.out.println("from servlet............. " + linkFrom);
        getCoverageInfo gCInfo = new getCoverageInfo();
        if(quoteNo == null || quoteNo.length() == 0)
        {
            quoteNo = (String)session.getAttribute("quoteNo");
        }
        int Qno = Integer.parseInt(quoteNo);
        gCInfo.setQuote_no(Qno);
        String quoteinfo[][] = gCInfo.getQuoteInfo();
        if(quoteinfo.length > 0)
        {
            gCInfo.setProduct_id(quoteinfo[0][0]);
            gCInfo.setScheme_id(Integer.parseInt(quoteinfo[0][1]));
        }
        gCInfo.setBranch_code((String)session.getAttribute("LoginBranchCode"));
        Values = gCInfo.getContentID(quoteNo, linkFrom);
        if(Values.length > 0)
        {
            contents_office = Values[0][0] != null ? Values[0][0] : "0";
            activity_prof = Values[0][1] != null ? Values[0][1] : "0";
            refCover_ids = Values[0][4] != null ? Values[0][4] : "";
            if(Values[0][2] != null && "Y".equalsIgnoreCase(Values[0][2]))
            {
                referal = "R";
            }
            if(Values[0][2] != null && "Y".equalsIgnoreCase(Values[0][2]) && refCover_ids.length() > 0)
            {
                gCInfo.setRefCover_ids(refCover_ids);
            }
            activity_status = Values[0][3] != null ? Values[0][3] : "N";
        }
        int cont_type_id = Integer.parseInt(contents_office);
        cusInfo = gCInfo.getCustomerInfo();
        request.setAttribute("CustomerInfo", cusInfo);
        request.setAttribute("customerId", customerID);
        gCInfo.setCont_type_id(cont_type_id);
        single_Value = gCInfo.getQuoteDataInfo(activity_prof);
        activity_prof_exc = single_Value[1] != null ? single_Value[1] : "";
        gCInfo.setActivity_prof_exc(activity_prof_exc);
        if("CusInfo".equalsIgnoreCase(linkFrom) || "ClearInfo".equalsIgnoreCase(linkFrom) || "admin".equalsIgnoreCase(linkFrom) || "BackInfo".equalsIgnoreCase(linkFrom))
        {
            double total_premium = 0.0D;
            if(!"N".equalsIgnoreCase(single_Value[0]) && ("admin".equalsIgnoreCase(linkFrom) || activity_status.equalsIgnoreCase("N") || "BackInfo".equalsIgnoreCase(linkFrom)))
            {
                mainCoverValues = gCInfo.getMainCoverageWithQuote(linkFrom);
                subCoverValues = gCInfo.getSubCoverageWithQuote(linkFrom);
                String excessInfo[][] = gCInfo.getReferalExcessInfo();
                request.setAttribute("excessinfo", excessInfo);
                try
                {
                    if(cont_type_id == 4 && mainCoverValues[0][0].equalsIgnoreCase("1"))
                    {
                        double limit = 0.0D;
                        double evalue = 0.0D;
                        limit = Double.parseDouble(mainCoverValues[0][12] != null ? mainCoverValues[0][12] : "0");
                        evalue = Double.parseDouble(mainCoverValues[0][7] != null ? mainCoverValues[0][7] : "0");
                        gCInfo.updateContent4RefStatus(limit, evalue);
                    }
                }
                catch(Exception e)
                {
                    System.out.println("error in referral comparision" + e);
                }
                request.setAttribute("status", "fromSaveInfo");
                String tot_pre = single_Value[2] != null ? single_Value[2] : "N/A";
                request.setAttribute("total_premium", tot_pre);
            } else
            {
                mainCoverValues = gCInfo.getMainCoverageDetails(linkFrom);
                subCoverValues = gCInfo.getSubCoverageDetails(linkFrom);
                total_premium = gCInfo.updatePremium(0.0D, "CusInfo", userType);
                request.setAttribute("total_premium", String.valueOf(total_premium));
            }
            gCInfo.getClaimExperience(quoteNo, linkFrom);
            referalVal = gCInfo.getReferalDetailInfo(linkFrom);
            if(referalVal[1].equalsIgnoreCase("Available"))
            {
                String ref = referalVal[0];
                String allRef = referalVal[2];
                request.setAttribute("refstatus", "R");
                request.setAttribute("refValue", ref);
                request.setAttribute("allrefValue", allRef);
            }
            //Added by chinna
            	String sum="";
            	String calcType[][]=new String[0][0];
            	HashMap baseratests=new HashMap();
            	HashMap subbaseratests=new HashMap();
            	HashMap forumulaSum=new HashMap();
            	String temp="";
            	if(mainCoverValues!=null && mainCoverValues.length>0)
            	{
            		for(int i=0; i<mainCoverValues.length; i++)
            		{
            			
            			if(mainCoverValues[i][11]!=null && mainCoverValues[i][11].equalsIgnoreCase("G"))
            			{
            				if(mainCoverValues[i][7]!=null && mainCoverValues[i][7].indexOf(",")!=-1)
            				{
            					temp=mainCoverValues[i][12];
            				}else{temp=mainCoverValues[i][7];}
            				calcType=gCInfo.getBaseRate(mainCoverValues[i][0], "0", gCInfo.getProduct_id(), gCInfo.getScheme_id()+"", cont_type_id, temp);
            				if(calcType!=null && calcType.length>0 && calcType[0][1]!=null)
            				{
            					baseratests.put(mainCoverValues[i][0], calcType[0][1]);
            				}else
            				{
            					baseratests.put(mainCoverValues[i][0], mainCoverValues[i][11]);
            				}
            				if(calcType!=null && calcType.length>0 && !"admin".equalsIgnoreCase(linkFrom) && calcType[0][0]!=null)
            				{
            					mainCoverValues[i][8]=calcType[0][0];
            				}
            				calcType=new String[0][0];
            			}else if(mainCoverValues[i][11]!=null && !mainCoverValues[i][11].equalsIgnoreCase("G"))
            			{
            				baseratests.put(mainCoverValues[i][0], mainCoverValues[i][11]);
            			}
            			
            			boolean status=gCInfo.getFormulaCount(gCInfo.getProduct_id(), gCInfo.getScheme_id()+"", mainCoverValues[i][0]);
     	        	    System.out.println("formula status: =====>>>>>"+status);
     	        	    if(status)
     	        	    {
     	        		  sum=gCInfo.getFormulaSum(gCInfo.getProduct_id(), gCInfo.getScheme_id()+"", mainCoverValues[i][0], quoteNo);
     	        		  if(sum!=null && sum.length()>0)forumulaSum.put(mainCoverValues[i][0], sum);
     	        		  System.out.println("formula sum: =====>>>>>"+sum);
     	        	    }
            		}
            	}
            	
            	if(subCoverValues!=null && subCoverValues.length>0)
            	{
            		for(int i=0; i<subCoverValues.length; i++)
            		{
            			if(subCoverValues[i][8]!=null && subCoverValues[i][8].equalsIgnoreCase("G"))
            			{
            				System.out.println("cover================"+subCoverValues[i][1]+": "+subCoverValues[i][0]);
            				calcType=gCInfo.getBaseRate(subCoverValues[i][0], "0", gCInfo.getProduct_id(), gCInfo.getScheme_id()+"", cont_type_id, subCoverValues[i][7]);
            				if(calcType!=null && calcType.length>0 && calcType[0][1]!=null)
            				subbaseratests.put(subCoverValues[i][0], calcType[0][1]);
            				if(calcType!=null && calcType.length>0 && !"admin".equalsIgnoreCase(linkFrom) && calcType[0][0]!=null)
            				{
            					subCoverValues[i][4]=calcType[0][0];
            				}
            				calcType=new String[0][0];
            			}else if(subCoverValues[i][8]!=null && !subCoverValues[i][8].equalsIgnoreCase("G"))
            			{
            				subbaseratests.put(subCoverValues[i][0], subCoverValues[i][8]);
            			}
            			
            			boolean status=gCInfo.getFormulaCount(gCInfo.getProduct_id(), gCInfo.getScheme_id()+"", subCoverValues[i][0]);
     	        	    System.out.println("formula status: =====>>>>>"+status);
     	        	    if(status)
     	        	    {
     	        		  sum=gCInfo.getFormulaSum(gCInfo.getProduct_id(), gCInfo.getScheme_id()+"", subCoverValues[i][0], quoteNo);
     	        		  if(sum!=null && sum.length()>0)forumulaSum.put(subCoverValues[i][0], sum);
     	        		  System.out.println("formula sum: =====>>>>>"+sum);
     	        	    }
            		}
            	}
            	
            	request.setAttribute("MainCalcTypes", baseratests);
                request.setAttribute("SubCalcTypes", subbaseratests);
                request.setAttribute("FormulaValues", forumulaSum);
            //End
            request.setAttribute("MainCoverage", mainCoverValues);
            request.setAttribute("SubCoverage", subCoverValues);
            request.setAttribute("quoteNo", quoteNo);
            rd = request.getRequestDispatcher("/OfficeInsurance/CustomerCoverage.jsp");
        } else
        if("SaveInfo".equalsIgnoreCase(linkFrom) || "ProceedInfo".equalsIgnoreCase(linkFrom) || "CoverageInfo".equalsIgnoreCase(linkFrom) || "adminProceedInfo".equalsIgnoreCase(linkFrom) || "Calculate".equalsIgnoreCase(linkFrom) || "adminCalculate".equalsIgnoreCase(linkFrom) || linkFrom.equalsIgnoreCase("MailQuote"))
        {
            Enumeration e = request.getParameterNames();
            String param_Name = "";
            String param_Value = "";
            HashMap baseSts = new HashMap();
            HashMap baserate = new HashMap();
            HashMap baseratests = new HashMap();
            HashMap basesumins = new HashMap();
            HashMap basetextsumins = new HashMap();
            HashMap subbaseids = new HashMap();
            HashMap subbaserate = new HashMap();
            HashMap subbaseratests = new HashMap();
            HashMap subbasesumins = new HashMap();
            HashMap cidnames = new HashMap();
            HashMap cidnamests = new HashMap();
            HashMap cValues = new HashMap();
            double sum_insured = 100000D;
            double base_premium = 0.0D;
            double option_premium = 0.0D;
            double total_premium = 0.0D;
            double final_premium = 0.0D;
            String refStatus[] = new String[0];
            String refSts[] = new String[0];
            HashMap hm=new HashMap();
            HashMap hm1=new HashMap();
            String subCover="0";
            int contentTypeId=gCInfo.getCont_type_id();
            String productId = (String) session.getAttribute("product_id");
    		String schemeId = (String) session.getAttribute("scheme_id");
    		int flag=0;
            while(e.hasMoreElements()) 
            {
                param_Name = (String)e.nextElement();
                if(param_Name.indexOf("Bmainradiocid") != -1)
                {
                    param_Value = request.getParameter(param_Name);
                    baseSts.put(param_Name.replaceAll("Bmainradiocid", ""), param_Value);
                }
                if(param_Name.indexOf("Bmainbasesuminscid") != -1)
                {
                    param_Value = request.getParameter(param_Name);
                    basesumins.put(param_Name.replaceAll("Bmainbasesuminscid", ""), param_Value);
                    basetextsumins.put(param_Name.replaceAll("Bmainbasesuminscid", ""), param_Value);
                }
                if(param_Name.indexOf("Bmainsuminsuredcid") != -1)
                {
                    param_Value = request.getParameter(param_Name).trim();
                    basetextsumins.put(param_Name.replaceAll("Bmainsuminsuredcid", ""), param_Value);
                    if(param_Name.indexOf("Bmainsuminsuredcidsts") == -1){
                    hm.put(param_Name.replaceAll("Bmainsuminsuredcid", ""), param_Value);
                    }
                }
                if(param_Name.indexOf("Bmainbasenamecid") != -1)
                {
                    param_Value = request.getParameter(param_Name);
                    cidnames.put(param_Name.replaceAll("Bmainbasenamecid", ""), param_Value);
                }
                if(param_Name.indexOf("Bmainsuminsuredcidsts") != -1)
                {
                    param_Value = request.getParameter(param_Name);
                    cidnamests.put(param_Name.replaceAll("Bmainsuminsuredcidsts", ""), param_Value);
                }
                if(param_Name.indexOf("subbaseratestssid") != -1)
                {
                	param_Value = request.getParameter(param_Name);
                    subbaseratests.put(param_Name.replaceAll("subbaseratestssid", ""), param_Value);
                }
                if(param_Name.indexOf("subradiosid") != -1)
                {
                    param_Value = request.getParameter(param_Name);
                    cValues.put(param_Name.replaceAll("subradiosid", ""), param_Value);
                }
                if(param_Name.indexOf("subbaseratesid") != -1)
                {
                    param_Value = request.getParameter(param_Name);
                    subbaserate.put(param_Name.replaceAll("subbaseratesid", ""), param_Value);
                }
                
                if(param_Name.indexOf("subsid") != -1)
                {
                    param_Value = request.getParameter(param_Name);
                    subbaseids.put(param_Name.replaceAll("subsid", ""), param_Value);
                }
                
                if(param_Name.indexOf("subDisplaysid") != -1)
                {
                    param_Value = request.getParameter(param_Name);
                    cValues.put(param_Name.replaceAll("subDisplaysid", ""), param_Value);
                }
                if(param_Name.indexOf("subtextsid") != -1)
                {
                    param_Value = request.getParameter(param_Name);
                    cValues.put(param_Name.replaceAll("subtextsid", ""), param_Value);
                }
                if(param_Name.indexOf("subtextareasid") != -1)
                {
                    param_Value = request.getParameter(param_Name);
                    cValues.put(param_Name.replaceAll("subtextareasid", ""), param_Value);
                }
                if(param_Name.indexOf("subdropdownsid") != -1)
                {
                    param_Value = request.getParameter(param_Name);
                    cValues.put(param_Name.replaceAll("subdropdownsid", ""), param_Value);
                }
                if(param_Name.indexOf("subbasesuminssid") != -1)
                {
                    param_Value = request.getParameter(param_Name);
                    subbasesumins.put(param_Name.replaceAll("subbasesuminssid", ""), param_Value);
                    basetextsumins.put(param_Name.replaceAll("subbasesuminssid", ""), param_Value);
                    hm1.put(param_Name.replaceAll("Bmainsuminsuredcid", ""), param_Value);
                }
                if(param_Name.indexOf("subbasenamesid") != -1)
                {
                    param_Value = request.getParameter(param_Name);
                    cidnames.put(param_Name.replaceAll("subbasenamesid", ""), param_Value);
                }
                if(param_Name.indexOf("Bmainbaseratecid") != -1)
                {
                    param_Value = request.getParameter(param_Name);
                    baserate.put(param_Name.replaceAll("Bmainbaseratecid", ""), param_Value);
                  
                }
                if(param_Name.indexOf("Bmainbaseratestscid") != -1)
                {
                    param_Value = request.getParameter(param_Name);
                    baseratests.put(param_Name.replaceAll("Bmainbaseratestscid", ""), param_Value);
                }
          }
            		//Block for base_rate for grid option
	            	Set hotkeys=baseSts.keySet();
	            	Iterator en=hotkeys.iterator();
	            	while(en.hasNext())
	            	{
		        	   String coverid=(String)en.next();
		        	   String value=(String)hm.get(coverid);
		        	   String calType=(String)baseratests.get(coverid);
		        	   System.out.println("cover: "+coverid+" sum: "+value+" type: "+calType);
		        	   System.out.println("Base Rate%%%%%%%%%%%%%%%%%%%%%%: "+baserate.get(coverid));
		        	   if(calType.equalsIgnoreCase("G"))
		        	   {
		        		 String rate[][]=  gCInfo.getBaseRate(coverid, subCover, productId, gCInfo.getScheme_id()+"", contentTypeId, value);
		        		 System.out.println("Rate: =====> "+" rate: "+rate.length);
		        		 if(rate.length>0){
			        		 baseratests.remove(coverid);
			        		 baseratests.put(coverid, rate[0][1]);
			        		 if(!"adminCalculate".equalsIgnoreCase(linkFrom) && !"adminProceedInfo".equalsIgnoreCase(linkFrom))
			             	 {
			        			 baserate.remove(coverid);
			        			 baserate.put(coverid, rate[0][0]);
			             	 }
		        		 }
		        		 System.out.println("calcType=================================>>>: "+baseratests.get(coverid));
		        	   }
	            	}
	            	
	            	//block for subbase_rate
	            	Set hotkeys1=subbaseids.keySet();
	            	Iterator en1=hotkeys1.iterator();
	            	while(en1.hasNext())
	            	{
		        	   String subcoverid=(String)en1.next();
		        	   String value=(String)hm1.get(subcoverid);
		        	   String calType=(String)subbaseratests.get(subcoverid);
		        	   System.out.println("cover: "+subcoverid+" sum: "+value+" type: "+calType);
		        	   if(calType.equalsIgnoreCase("G"))
		        	   {
		        		 String rate[][]=  gCInfo.getBaseRate(subcoverid, subCover, productId, schemeId, contentTypeId, value);
		        		 System.out.println("Rate: =====> "+" rate: "+rate.length);
		        		 if(rate.length>0)
		        		 {
		        			subbaseratests.remove(subcoverid);
		        			subbaseratests.put(subcoverid, rate[0][1]);
		        			if(!"adminCalculate".equalsIgnoreCase(linkFrom) && !"adminProceedInfo".equalsIgnoreCase(linkFrom))
		                 	{
		        				 subbaserate.remove(subcoverid);
		        				 subbaserate.put(subcoverid, rate[0][0]);
		                 	}
		        		 }
		        	   }
	            	}
           
            refStatus = gCInfo.validateOptionalPremiumInfo(linkFrom, baseSts, subbaseratests, subbaserate, subbaseids, cValues, subbasesumins, cidnames);
            refSts = gCInfo.validateMasterPremiumInfo(linkFrom, baseSts, baseratests, baserate, basesumins, basetextsumins, cidnames, cidnamests);
            gCInfo.getClaimExperience(quoteNo, linkFrom);
            error = error + refSts[0];
            error = error + refStatus[0];
            System.out.println(" error is .............." + error);
            
            referalVal = gCInfo.getReferalDetailInfo(linkFrom);
            if(referalVal[1].equalsIgnoreCase("Available"))
            {
                referal = "R";
                String ref = referalVal[0];
                String allRef = referalVal[2];
                request.setAttribute("refstatus", "R");
                request.setAttribute("refValue", ref);
                request.setAttribute("allrefValue", allRef);
            } else
            {
                referal = "NR";
            }
            if(error.length() > 0)
            {
                option_premium = Double.parseDouble(refStatus[2]);
                mainCoverValues = gCInfo.getMainCoverageDetails(linkFrom);
                subCoverValues = gCInfo.getSubCoverageDetails(linkFrom);
                if(!"CoverageInfo".equalsIgnoreCase(linkFrom))
                {
                    request.setAttribute("status", "error");
                    request.setAttribute("error", error);
                }
                base_premium = Double.parseDouble(refSts[1]);
                total_premium = gCInfo.updatePremium(base_premium + option_premium, "CusInfo", userType);
            } else
            {
                System.out.println("Base-Premium calculateion Enter");
            	base_premium = gCInfo.insertMasterPremiumInfo(baseSts, baseratests, baserate, basesumins, basetextsumins, cidnamests);
                option_premium = gCInfo.insertOptionalPremiumInfo(baseSts, subbaseratests, subbaserate, subbaseids, cValues, subbasesumins);
            	System.out.println("Base-Premium calculateion Exit");
            	total_premium = gCInfo.updatePremium(base_premium + option_premium, referal, userType);
            	System.out.println("premium: ------"+request.getParameter("premium"));
            	double premium=Double.parseDouble(request.getParameter("premium"));
            	//gCInfo.updatePremium(premium, referal, userType);
            	System.out.println("premium: ------"+premium);
            	//premium = gCInfo.updatePremium(premium, referal, userType);
            	mainCoverValues = gCInfo.getMainCoverageWithQuote(linkFrom);
                subCoverValues = gCInfo.getSubCoverageWithQuote(linkFrom);
                //request.setAttribute("status", "fromSaveInfo");
            }
            if("adminProceedInfo".equalsIgnoreCase(linkFrom))
            {
                String excessInfo[][] = gCInfo.getReferalExcessInfo();
                request.setAttribute("excessinfo", excessInfo);
            }
            request.setAttribute("total_premium", String.valueOf(total_premium));
            request.setAttribute("MainCoverage", mainCoverValues);
            request.setAttribute("SubCoverage", subCoverValues);
            request.setAttribute("contents_office", contents_office);
            request.setAttribute("quoteNo", quoteNo);
            
            //block for sum_insured amount from formula
        	HashMap forumulaSum=new HashMap();
        	String sum="";
        	Set hotkeys2=baseSts.keySet();
        	Iterator en2=hotkeys2.iterator();
        	while(en2.hasNext())
        	{
        		String coverid=(String)en2.next();
        		boolean status=gCInfo.getFormulaCount(productId, schemeId, coverid);
	        	    System.out.println("formula status: =====>>>>>"+status);
	        	    if(status)
	        	    {
	        		  sum=gCInfo.getFormulaSum(productId, schemeId, coverid, quoteNo);
	        		  if(sum!=null && sum.length()>0)forumulaSum.put(coverid, sum);
	        		  System.out.println("formula sum: =====>>>>>"+sum);
	        	    }
        	}
        	//End
            
            //New Added by chinna for grid base rate-calculation type
            request.setAttribute("MainCalcTypes", baseratests);
            request.setAttribute("SubCalcTypes", subbaseratests);
            request.setAttribute("FormulaValues", forumulaSum);
            //Newly added for updating premimum
            HashMap icon=new HashMap();
            if("ProceedInfo".equalsIgnoreCase(linkFrom) || "adminProceedInfo".equalsIgnoreCase(linkFrom))
            {
            double temp_premium=0.0;
            String amount="";
            double totalPremium=0.0;
            if(mainCoverValues.length>0)
            {
            	for(int i=0; i<mainCoverValues.length; i++)
            	{
            		if ("Y".equalsIgnoreCase(mainCoverValues[i][10])) 
            		{
            			if("textbox".equalsIgnoreCase(mainCoverValues[i][13]) || "dropdown".equalsIgnoreCase(mainCoverValues[i][13])){
	            				amount=mainCoverValues[i][7];
							}
							else{amount=mainCoverValues[i][12];}
            			
            			if(forumulaSum.size()>0 && forumulaSum.containsKey(mainCoverValues[i][0]))
						{
							amount=(String)forumulaSum.get(mainCoverValues[i][0]);
						}
            			temp_premium=gCInfo.getPremium((String)baseratests.get(mainCoverValues[i][0]),Double.parseDouble(mainCoverValues[i][8] == null ? "0": mainCoverValues[i][8]),amount == null ? "0" : amount,"1");
            			totalPremium+=temp_premium;
            		}
//            		Block for Coverage Details Icon display
            		String coverDetails[][]=new OfficeShieldBean().getCoverDetails(mainCoverValues[i][0], "0", productId, schemeId, cont_type_id+"");
            		if(coverDetails!=null && coverDetails.length>0)
            		{
            			icon.put(mainCoverValues[i][0],"true");
            		}else
            		{
            			icon.put(mainCoverValues[i][0],"false");
            		}
            		//End of Coverage Details
            	}
            	System.out.println("Totalllllllllllllllllllllllllllll: "+totalPremium+"icon: "+icon.get("11"));
            	gCInfo.updatePremium(totalPremium, referal, userType);
            	session.setAttribute("icon",icon);
            }
            }
            //End
            
        } else
        if("adminaddPremium".equalsIgnoreCase(linkFrom))
        {
            String adminReferalRemarks = request.getParameter("adminReferalRemarks") != null ? request.getParameter("adminReferalRemarks") : "";
            String Remarks = request.getParameter("Remarks") != null ? request.getParameter("Remarks") : "";
            gCInfo.updateReferralStatus(adminReferalRemarks, Remarks);
            String reqFrom = request.getParameter("reqFrom") != null ? request.getParameter("reqFrom") : "";
            String addSign = request.getParameter("addSign") != null ? request.getParameter("addSign") : "";
            String addPremium = request.getParameter("addPremium") != null ? request.getParameter("addPremium") : "";
            String payablePremium = request.getParameter("payablePremium") != null ? request.getParameter("payablePremium") : "";
            gCInfo.updatefinalPremium(addSign, addPremium, payablePremium);
            request.setAttribute("reqFrom", reqFrom);
            request.setAttribute("quoteNo", quoteNo);
            System.out.println("OFFICE MAIL CONTROLLER ......." + Remarks);
            if(quoteinfo.length > 0)
            {
                request.setAttribute("pid", quoteinfo[0][0]);
            }
            request.setAttribute("refStatus", Remarks);
            response.sendRedirect(offPath + "/OfficeInsurance/adminUploadSummary.jsp?refStatus=" + Remarks + "&reqFrom=" + reqFrom);
        }
        if("admin".equalsIgnoreCase(linkFrom) || "adminProceedInfo".equalsIgnoreCase(linkFrom) || "adminCalculate".equalsIgnoreCase(linkFrom))
        {
            String QuoteNoSearch = request.getParameter("QuoteNoSearch") != null ? request.getParameter("QuoteNoSearch") : "";
            String eDate = request.getParameter("eDate") != null ? request.getParameter("eDate") : "";
            String reqFrom = request.getParameter("reqFrom") != null ? request.getParameter("reqFrom") : "";
            if(reqFrom.length() <= 0)
            {
                reqFrom = (String)request.getAttribute("reqFrom") != null ? (String)request.getAttribute("reqFrom") : "";
            }
            request.setAttribute("eDate", eDate);
            request.setAttribute("reqFrom", reqFrom);
            request.setAttribute("QuoteNoSearch", QuoteNoSearch);
        }
        if("SaveInfo".equalsIgnoreCase(linkFrom))
        {
            request.setAttribute("saveSts", "Information Successfully saved");
            rd = request.getRequestDispatcher("/OfficeInsurance/CustomerCoverage.jsp");
        } else
        if("CoverageInfo".equalsIgnoreCase(linkFrom))
        {
            request.setAttribute("customer", customerID);
            //rd = request.getRequestDispatcher("/CustomerInfo/CustomerInfoOShield.jsp");
            //rd = request.getRequestDispatcher("/CustomerInfo/CustomerInfoHome.jsp?mode=Edit&QuoteNo="+quoteNo+"&requestfrom=ViewQuote&reqPath=ViewQuote_B2B.jsp");
            if("R".equalsIgnoreCase((String)request.getAttribute("refstatus")))
            rd = request.getRequestDispatcher("/HomeDisplayController?DisplayValue=referralPolicyUnApprove");
            else		
            rd = request.getRequestDispatcher("/HomeDisplayController?DisplayValue=quoteregister");
        } else
        if("ProceedInfo".equalsIgnoreCase(linkFrom) && error.length() <= 0 && referal.equalsIgnoreCase("NR"))
        {
            response.sendRedirect(offPath + "/OfficeInsurance/UploadSummary.jsp");
        } else
        if("ProceedInfo".equalsIgnoreCase(linkFrom) && error.length() <= 0 && referal.equalsIgnoreCase("R"))
        {
            request.setAttribute("RefStatus", "Referal");
            response.sendRedirect(offPath + "/OfficeInsurance/UploadSummary.jsp");
        } else
        if("ProceedInfo".equalsIgnoreCase(linkFrom) && error.length() > 0)
        {
            rd = request.getRequestDispatcher("/OfficeInsurance/CustomerCoverage.jsp");
        } else
        if("admin".equalsIgnoreCase(linkFrom))
        {
            rd = request.getRequestDispatcher("/admin/CustomerCoverage.jsp");
        	//rd = request.getRequestDispatcher("/OfficeInsurance/CustomerCoverage.jsp");
        } else
        if("adminProceedInfo".equalsIgnoreCase(linkFrom) && error.length() <= 0)
        {
            request.setAttribute("adminstatus", "viewInfo");
            rd = request.getRequestDispatcher("/admin/CustomerCoverage.jsp");
        } else
        if("adminProceedInfo".equalsIgnoreCase(linkFrom) && error.length() > 0)
        {
            rd = request.getRequestDispatcher("/admin/CustomerCoverage.jsp");
        } else
        if("Calculate".equalsIgnoreCase(linkFrom))
        {
            rd = request.getRequestDispatcher("/OfficeInsurance/CustomerCoverage.jsp");
        } else
        if("adminCalculate".equalsIgnoreCase(linkFrom))
        {
            rd = request.getRequestDispatcher("/admin/CustomerCoverage.jsp");
        } else
        if(linkFrom.equalsIgnoreCase("MailQuote"))
        {
            request.setAttribute("CustomerMailQuote", "CustomerMailQuote");
            request.setAttribute("quoteNo", quoteNo);
            String mailStatus=gCInfo.checkMailIds(quoteNo);
            if(mailStatus==null || mailStatus.length()<=0)//If and Else block added by Chinna for Email Id's Availabilities
            {
	            RequestDispatcher mailDispatch = request.getRequestDispatcher("/servlet/OfficeMailController");
	            if(mailDispatch != null)
	            {
	                mailDispatch.include(request, response);
	            }
	            request.setAttribute("CustomerMailStatus", "Mail has been sent successfully");
            }else
            {
            	request.setAttribute("CustomerMailStatus", mailStatus);
            }
            request.setAttribute("quoteNo", quoteNo);
            rd = request.getRequestDispatcher("/OfficeInsurance/information.jsp");
        }
        if(rd != null)
        {
            rd.forward(request, response);
            return;
        } else
        {
            return;
        }
    }
}
