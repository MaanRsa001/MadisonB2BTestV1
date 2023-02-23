package com.maan.Office.Controller;

import com.maan.Office.DAO.OfficeShieldBean;
import com.maan.Office.DAO.pdfGenerate.PDFCreatorBean;
import com.maan.Office.DAO.scheduleBean;
import java.io.*;
import java.util.HashMap;
import java.util.Vector;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class ScheduleCont extends HttpServlet
{

    Runtime run;

    public ScheduleCont()
    {
        run = Runtime.getRuntime();
    }

    public void destroy()
    {
        super.destroy();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
        ProcessResult(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
        ProcessResult(request, response);
    }

    public void ProcessResult(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
        RequestDispatcher rd = null;
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession(true);
        RequestDispatcher dispatcher = null;
        String mainCoverValues[][] = new String[0][0];
        String subCoverValues[][] = new String[0][0];
        String uploadedFilesValues[][] = new String[0][0];
        String uploadedFilescid[][] = new String[0][0];
        String Values[][] = new String[0][0];
        String existReferal[][] = new String[0][0];
        String ExtensionInfo[][] = new String[0][0];
        String cusInfo[][] = new String[0][0];
        String single_Value[] = new String[0];
        String referalVal[] = new String[0];
        Vector maincover = new Vector();
        Vector subcover = new Vector();
        Vector uploadedfiles = new Vector();
        Vector uploadedfilescid = new Vector();
        Vector customerDetails = new Vector();
        Vector premium = new Vector();
        String x[][] = new String[0][0];
        String y[][] = new String[0][0];
        String path = request.getRealPath("/PDFFile/Office/Schedule");
        String Despath = request.getRealPath("/PDFFile/Office/Schedule");
        String path1 = request.getRealPath("/PDFFile/Office/Draft");
        String Despath1 = request.getRealPath("/PDFFile/Office/Draft");
        String path2 = request.getRealPath("/PDFFile/Office/Certificate");
        String Despath2 = request.getRealPath("/PDFFile/Office/Certificate");
        String openPath = request.getContextPath();
        String file_name = "";
        String policy_number = "";
        String waterMark = "";
        double tPremium = 0.0D;
        double sum_insured = 0.0D;
        int pdf_broker_sts = 0;
        String userType = "";
        userType = (String)session.getAttribute("usertype");
        userType = userType != null ? userType : "";
        String waterMarkCount = "0";
        if(userType.equalsIgnoreCase("HomeAdmin"))
        {
            waterMarkCount = request.getParameter("waterMarkCount");
        }
        waterMarkCount = waterMarkCount != null ? waterMarkCount : "0";
        String linkFrom = (String)request.getAttribute("linkFrom") != null ? (String)request.getAttribute("linkFrom") : "";
        if(linkFrom == null || linkFrom.length() <= 0)
        {
            linkFrom = request.getParameter("linkFrom") != null ? request.getParameter("linkFrom") : "";
        }
        String portfolio = request.getParameter("portfolio") != null ? request.getParameter("portfolio") : "";
        String multiDraft = request.getParameter("multiDraft") != null ? request.getParameter("multiDraft") : "";
        String refQuote = request.getParameter("refQuote") != null ? request.getParameter("refQuote") : "";
        String quoteNo = (String)request.getAttribute("quoteNo") != null ? (String)request.getAttribute("quoteNo") : request.getParameter("quoteNo") != null ? request.getParameter("quoteNo") : "";
        if(quoteNo == null || quoteNo.length() <= 0)
        {
            quoteNo = request.getParameter("QuoteNo") != null ? request.getParameter("QuoteNo") : "";
        }
        String insStartDate = request.getParameter("StatDate") != null ? request.getParameter("StatDate") : "";
        String insExpDate = request.getParameter("ExpDate") != null ? request.getParameter("ExpDate") : "";
        String customerID = (String)request.getAttribute("customerId") != null ? (String)request.getAttribute("customerId") : request.getParameter("customer") != null ? request.getParameter("customer") : "";
        String contents_office = "0";
        String referal = "";
        String activity_prof = "";
        String activity_prof_exc = "";
        String activity_status = "";
        String refCover_ids = "";
        String total_premium = "";
        String productId = "";
        String schemeId = "";
        scheduleBean gCInfo = new scheduleBean();
        OfficeShieldBean OSB = new OfficeShieldBean();
        StringBuffer error = new StringBuffer();
        String loginId = "";
        loginId = (String)session.getAttribute("user");
        loginId = loginId != null ? loginId : "";
        HashMap brokerDetails = (HashMap)session.getAttribute("BrokerDetails");
        String cid = "";
        String currencyType = "";
        if(brokerDetails.size() > 0)
        {
            cid = (String)brokerDetails.get("Orgination");
            currencyType = (String)brokerDetails.get("CurrencyAbb");
        }
        String userLoginMode = (String)session.getAttribute("userLoginMode");
        String Quotenos[] = new String[0];
        String TempQuotenos[] = new String[0];
        String qnos = "";
        try
        {
            //Quotenos = (String[])session.getAttribute("scheduleQuotes");
        	Quotenos = new String[]{quoteNo};
            System.out.println("linkFrom...." + linkFrom);
            if(Quotenos.length > 1)
            {
                String temp = "";
                for(int i = 0; i < Quotenos.length - 1; i++)
                {
                    for(int j = 0; j < Quotenos.length - 1 - i; j++)
                    {
                        if(Double.parseDouble(Quotenos[j]) > Double.parseDouble(Quotenos[j + 1]))
                        {
                            temp = Quotenos[j];
                            Quotenos[j] = Quotenos[j + 1];
                            Quotenos[j + 1] = temp;
                        }
                    }

                }

            }
            for(int s = 0; s < Quotenos.length; s++)
            {
                System.out.println("Quotenos...." + s + "...linkFrom" + linkFrom + "...." + Quotenos[s]);
            }

            Quotenos[0] = Quotenos[0] != null ? Quotenos[0] : "0";
        }
        catch(Exception e)
        {
            System.out.println("scheduleQuotes..." + e.toString());
            e.printStackTrace();
        }
        String quoteinfo[][] = gCInfo.getQuoteInfo(Quotenos[0]);
        if(quoteinfo.length > 0)
        {
            productId = quoteinfo[0][0];
            schemeId = quoteinfo[0][1];
            gCInfo.setProduct_id(productId);
            gCInfo.setScheme_id(Integer.parseInt(quoteinfo[0][1]));
        }
        gCInfo.setBranch_code((String)session.getAttribute("LoginBranchCode"));
        gCInfo.setProduct_id(productId);
        PDFCreatorBean Pdf = new PDFCreatorBean();
        //Pdf.setHeaderImagePath(request.getRealPath("//images/headerImage.jpg"));
        //Pdf.setFooterImagePath(request.getRealPath("//images/ChennaiFooterImage.jpg"));
        String[][] images = gCInfo.getImages();
        if(images!=null && images.length>0){
        	Pdf.setHeaderImagePath(request.getRealPath("//images/"+images[0][0]));
        	Pdf.setFooterImagePath(request.getRealPath("//images/"+images[0][1]));
        	Pdf.setSignImg(images[0][2]);
        	Pdf.setStampImg(images[0][3]);
        }
        
        for(int i = 0; i < Quotenos.length; i++)
        {
            qnos = qnos + Quotenos[i] + ",";
            int Qno = Integer.parseInt(Quotenos[i]);
            gCInfo.setQuote_no(Qno);
            Values = gCInfo.getContentID(Qno, linkFrom);
            if(Values.length > 0)
            {
                contents_office = Values[0][0] != null ? Values[0][0] : "0";
                activity_prof = Values[0][1] != null ? Values[0][1] : "";
                refCover_ids = Values[0][4] != null ? Values[0][4] : "";
                activity_status = Values[0][3] != null ? Values[0][3] : "N";
            }
            int cont_type_id = Integer.parseInt(contents_office);
            gCInfo.setCont_type_id(cont_type_id);
            ExtensionInfo = gCInfo.getExtensionInfo();
            single_Value = gCInfo.getQuoteDataInfo(activity_prof);
            activity_prof_exc = single_Value[1] != null ? single_Value[1] : "";
            gCInfo.setActivity_prof_exc(activity_prof_exc);
            if("schedule".equalsIgnoreCase(linkFrom) || "draft".equalsIgnoreCase(linkFrom) || "proceed".equalsIgnoreCase(linkFrom) || "certificate".equalsIgnoreCase(linkFrom))
            {
                mainCoverValues = gCInfo.getMainCoverageWithQuote();
                subCoverValues = gCInfo.getSubCoverageWithQuote();
                uploadedFilesValues = gCInfo.getUploadedFileList("main");
                uploadedFilescid = gCInfo.getUploadedFileList("others");
                maincover.add(i, mainCoverValues);
                subcover.add(i, subCoverValues);
                uploadedfiles.add(i, uploadedFilesValues);
                uploadedfilescid.add(i, uploadedFilescid);
                request.setAttribute("status", "fromSaveInfo");
                cusInfo = gCInfo.getCustomerInfo();
                total_premium = single_Value[2];
                premium.add(i, "0");
                if("proceed".equalsIgnoreCase(linkFrom))
                {
                    for(int mx = 0; mx < mainCoverValues.length; mx++)
                    {
                        sum_insured += Double.parseDouble(mainCoverValues[mx][12] != null ? mainCoverValues[mx][12] : "0");
                    }

                    tPremium += Double.parseDouble(total_premium);
                }
                customerDetails.add(i, cusInfo);
            }
        }

        String CoverageInfo[][] = gCInfo.getCoverageInfo();
        //--Added by chinna
	        
        	String[][] ContentMaster=OSB.getContentMaster(schemeId);
        	Pdf.setContentMaster(ContentMaster);
        	if(ContentMaster!=null && ContentMaster.length<=0)
	        {
	        	java.util.HashMap CoverageDetails = gCInfo.getCoverageDetails(Quotenos[0], loginId, productId, schemeId);
	        	Pdf.setCoverageDetails(CoverageDetails);
	        }
	        System.out.println("Inside SheduleCont linkfrom: "+linkFrom+"Quote NO: "+Quotenos[0]);
	        String incep_date="";
	        
        //--End
        if("schedule".equalsIgnoreCase(linkFrom) || "proceed".equalsIgnoreCase(linkFrom))
        {
            qnos = qnos.substring(0, qnos.length() - 1);
            policy_number = gCInfo.getPolicyNumber(qnos, Quotenos.length);
            Pdf.setPolicyDate(gCInfo.getPolicyDate(policy_number, productId));
            incep_date=gCInfo.getInceptionDate(Quotenos[0]);//Added by chinna
            Pdf.setIncep_date(incep_date);//Added by chinna
            gCInfo.getDebitNumber(qnos);
            gCInfo.updateDebitCommission(Quotenos, productId, schemeId);
            if("schedule".equalsIgnoreCase(linkFrom) && !userType.equalsIgnoreCase("HomeAdmin"))
            {
                pdf_broker_sts = gCInfo.getPdfBrokerStatus(policy_number);
            } else
            {
                pdf_broker_sts = Integer.parseInt(waterMarkCount);
            }
            if(pdf_broker_sts == 0 || "proceed".equalsIgnoreCase(linkFrom))
            {
                file_name = "PremiumSummary_Schedule_" + qnos;
                path = path + "/" + file_name + ".pdf";
                openPath = openPath + "/PDFFile/Office/Schedule/" + file_name + ".pdf";
                Despath = Despath + "/" + file_name + ".doc";
                waterMark = "";
            } else
            if(pdf_broker_sts == 1)
            {
                file_name = "PremiumSummary_Schedule_" + qnos;
                path = path + "/Original_Copy/" + file_name + ".pdf";
                openPath = openPath + "/PDFFile/Office/Schedule/Original_Copy/" + file_name + ".pdf";
                Despath = Despath + "/Original_Copy/" + file_name + ".doc";
                waterMark = "Original Copy";
            } else
            if(pdf_broker_sts > 1)
            {
                file_name = "PremiumSummary_Schedule_" + qnos;
                path = path + "/Copy/" + file_name + ".pdf";
                openPath = openPath + "/PDFFile/Office/Schedule/Copy/" + file_name + ".pdf";
                Despath = Despath + "/Copy/" + file_name + ".doc";
                waterMark = "Copy";
            }
            String imagepath = request.getRealPath("/images");
            try
            {
                File myFile = new File(path);
                Pdf.createPDF("schedule", path, imagepath, CoverageInfo, ExtensionInfo, Quotenos.length, premium, maincover, subcover, customerDetails, contents_office, waterMark, policy_number, currencyType, userLoginMode, uploadedfiles, uploadedfilescid);
                if("schedule".equalsIgnoreCase(linkFrom))
                {
                    convertPDFtoDOC(response, openPath, Despath, "pdf", file_name);
                } else
                if("proceed".equalsIgnoreCase(linkFrom))
                {
                    request.setAttribute("policynumber", policy_number);
                    request.setAttribute("qnos", qnos);
                    request.setAttribute("CustomerInfo", cusInfo);
                    request.setAttribute("tPremium", Double.toString(tPremium));
                    request.setAttribute("sum_insured", Double.toString(sum_insured));
                    rd = request.getRequestDispatcher("/OfficeInsurance/showPolicys.jsp");
                } else
                {
                    out.println("Status not available.Please contact administrator");
                }
            }
            catch(Exception e)
            {
                out.println("Exception : " + e);
            }
        } else
        if("draft".equalsIgnoreCase(linkFrom))
        {
            qnos = qnos.substring(0, qnos.length() - 1);
            file_name = "PremiumSummary_Draft_" + qnos;
            path1 = path1 + "/" + file_name + ".pdf";
            openPath = openPath + "/PDFFile/Office/Draft/" + file_name + ".pdf";
            Despath1 = Despath1 + "/" + file_name + ".doc";
            waterMark = "Draft";
            String imagepath = request.getRealPath("/images");
            try
            {
                File myFile = new File(path);
                Pdf.createPDF("draft", path1, imagepath, CoverageInfo, ExtensionInfo, Quotenos.length, premium, maincover, subcover, customerDetails, contents_office, waterMark, policy_number, currencyType, userLoginMode, uploadedfiles, uploadedfilescid);
                convertPDFtoDOC(response, openPath, Despath, "pdf", file_name);
            }
            catch(Exception e)
            {
                out.println("Exception : " + e);
                e.printStackTrace();
            }
        } else
        if("certificate".equalsIgnoreCase(linkFrom))
        {
            qnos = qnos.substring(0, qnos.length() - 1);
            policy_number = gCInfo.getPolicyNumber(qnos, Quotenos.length);
            gCInfo.getDebitNumber(qnos);
            file_name = "PremiumSummary_Certificate_" + qnos;
            path2 = path2 + "/" + file_name + ".pdf";
            openPath = openPath + "/PDFFile/Office/Certificate/" + file_name + ".pdf";
            Despath2 = Despath2 + "/" + file_name + ".doc";
            waterMark = "Certificate";
            String imagepath = request.getRealPath("/images");
            try
            {
                File myFile = new File(path);
                Pdf.createPDF("certificate", path2, imagepath, CoverageInfo, ExtensionInfo, Quotenos.length, premium, maincover, subcover, customerDetails, contents_office, waterMark, policy_number, currencyType, userLoginMode, uploadedfiles, uploadedfilescid);
                convertPDFtoDOC(response, openPath, Despath, "pdf", file_name);
            }
            catch(Exception e)
            {
                out.println("Exception : " + e);
            }
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

    public void convertPDFtoDOC(HttpServletResponse response, String src, String des, String openSts, String file_name)
    {
        if("doc".equalsIgnoreCase(openSts))
        {
            boolean sts;
            try
            {
                Process pp = run.exec("D:\\ConvertDoc.exe /S \"" + src + "\" /T \"" + des + "\" /C3 /M3 /E");
                int exitVal = pp.waitFor();
                System.out.println("Process exitValue: " + exitVal);
                sts = true;
            }
            catch(Exception e)
            {
                System.out.println("problem in doc conversion" + e);
                sts = false;
            }
            try
            {
                if(sts)
                {
                    response.sendRedirect("../PDFFile/" + file_name + ".doc");
                } else
                {
                    System.out.println("Problem in document Conversion.Contact Administrator");
                }
            }
            catch(Exception e)
            {
                System.out.println("problem in redirect to document file" + e);
            }
        } else
        if("pdf".equalsIgnoreCase(openSts))
        {
            try
            {
                response.sendRedirect(src);
            }
            catch(Exception e)
            {
                System.out.println("problem in redirect to pdf file" + e);
            }
        }
    }
}
