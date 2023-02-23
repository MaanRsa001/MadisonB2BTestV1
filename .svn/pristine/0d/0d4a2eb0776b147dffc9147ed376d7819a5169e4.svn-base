package com.maan.Office.Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Hashtable;
import java.io.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.maan.DBCon.DBConnection;
import com.maan.DBCon.DBConnectionStatus;
import com.maan.Office.DAO.OfficeShieldBean;

public class SetSessionValues extends HttpServlet  
{
    public  void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException
	{ 
        ProceesRequest(request,response);
    }
    public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException
    {
         ProceesRequest(request,response);
    }
	public void ProceesRequest(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException
	{
		HttpSession session = request.getSession(true);
         if(session.getAttribute("ses")==null)
		 {	
			response.sendRedirect(request.getContextPath()+"/login/error_messg.jsp");
			return;
		 }    
		
		String path = request.getContextPath();
          
       /* RequestDispatcher check = request.getRequestDispatcher("login/sessionsCheckNormal.jsp");
        check.include(request,response);*/
   	
		//SESSION CHECK
        
		String usrModeSC=(String)session.getAttribute("userLoginMode")==null?"":(String)session.getAttribute("userLoginMode");
        DBConnectionStatus.statusStatic=usrModeSC;
        //END OF SESSION CHECK
        
		String branch = (String)session.getAttribute("LoginBranchCode");
		String productId=(String) session.getAttribute("product_id");
		
		branch = branch == null?"020":branch;
		productId = productId ==null?"":productId;
        
		PrintWriter out = response.getWriter();
        response.setContentType("text/html");
		OfficeShieldBean OSB = new OfficeShieldBean();
		
		String[] TempQuotenos=new String[0];
		String[] Quotenos = new String[1];
		String[][] mulQnos = new String[0][0];
		String qNo = "";
		String status = "";
		String pNo = "";
		String conCatQuotesP="";
		String[] mulQnos1 = new String[0];
		int i = 0;
		
		status = request.getParameter("status");
		qNo = request.getParameter("pqno");
		pNo = request.getParameter("pqno");

		status = status == null ? "" : status;
		qNo = qNo == null ? "0" : qNo;
		pNo = pNo == null ? "0" : pNo;
		
		System.out.println(".....status......."+status);
		System.out.println(".....Quote No......."+qNo);

		try
		{
			if(status.equalsIgnoreCase("ScheduleMultiple") || status.equalsIgnoreCase("DebitMultiple"))
			{	
				mulQnos = OSB.getQuoteNosForDeclaration(pNo,productId);
				mulQnos1 = new String[mulQnos.length];
				for (i=0; i < mulQnos.length; i++ )
				{
					mulQnos1[i] = mulQnos[i][0];
					conCatQuotesP = conCatQuotesP + mulQnos[i][0] + ",";
				}
			}
			
			if(conCatQuotesP.length() >0 && !conCatQuotesP.equalsIgnoreCase(""))
				conCatQuotesP = conCatQuotesP.substring(0,conCatQuotesP.length()-1);	
			if(conCatQuotesP.length() >1)
			{
				if(conCatQuotesP.substring(0,1) == "0")
					conCatQuotesP = conCatQuotesP.substring(1,conCatQuotesP.length());
			}
		}
		catch(Exception e)
		{
			System.out.println("Exception is Set Session Values.."+e);
			e.printStackTrace();
		}
		
		for(int s=0;s<mulQnos1.length;s++)
			System.out.println("mulQnos1...s..."+s +"   "+mulQnos1[s]);
		
		try
		{
			if(status.equalsIgnoreCase("ScheduleMultiple"))
			{
				System.out.println("Schedule Multiple..."+status);
				if(session.getAttribute("scheduleQuotes") != null)
					session.removeAttribute("scheduleQuotes");
				
				session.setAttribute("scheduleQuotes",mulQnos1);
				System.out.println("scheduleQuotes...Set Session Values..."+session.getAttribute("scheduleQuotes"));
			}
			else if(status.equalsIgnoreCase("DebitMultiple"))
			{
				System.out.println("Debit Multiple..."+status);
				if(session.getAttribute("PDFQuoteNo") != null)
					session.removeAttribute("PDFQuoteNo");
				
				session.setAttribute("PDFQuoteNo",conCatQuotesP);
				System.out.println("PDFQuoteNo..."+session.getAttribute("PDFQuoteNo"));
			}
			else if(status.equalsIgnoreCase("Schedule"))
			{
				if(session.getAttribute("scheduleQuotes") != null)
					session.removeAttribute("scheduleQuotes");
				Quotenos[0] = qNo;
				session.setAttribute("scheduleQuotes",Quotenos);
				System.out.println("scheduleQuotes..."+session.getAttribute("scheduleQuotes"));
			}
			else if(status.equalsIgnoreCase("Draft"))
			{
				if(session.getAttribute("scheduleQuotes") != null)
					session.removeAttribute("scheduleQuotes");
				Quotenos[0] = qNo;
				session.setAttribute("scheduleQuotes",Quotenos);
				System.out.println("scheduleQuotes...Draft "+session.getAttribute("scheduleQuotes"));
			}
	    }
		catch(Exception e)
		{
			System.out.println("Schedule Quotes...."+e);
			e.printStackTrace();
		}

		try
		{
			out.println("<input type=\"hidden\" name=\"sesQno\" value=\""+qNo+"\"/>");
        }
        catch(Exception ex)
		{
            out.println("SetSessionValues Controller");
            ex.printStackTrace();
        }
	}

}  // Class   

