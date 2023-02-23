package com.maan.Office.Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Hashtable;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.maan.DBCon.DBConnection;
import com.maan.DBCon.DBConnectionStatus;
import com.maan.Office.DAO.OfficeShieldBean;

public class CoverageQuickUploadDelete extends HttpServlet  
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
		
		String uploadId = "";
		String AddItem = "";
		String quoteNo = "";
		String conTypId = request.getParameter("conTypId")==null?"":request.getParameter("conTypId");

		AddItem = request.getParameter("AddItem");
		uploadId = request.getParameter("uploadId");

		uploadId = uploadId == null ? "" : uploadId;
		AddItem = AddItem == null ? "" : AddItem;
        quoteNo = (String) session.getAttribute("quoteNo");
		try
		{
			OSB.getQuickUploadCoverageDelete(uploadId,AddItem,quoteNo);
			out.println("<font color=\"white\" size=\"1\"> deleted </font>");
        }
        catch(Exception ex)
		{
            out.println("CoverageQuickUploadDelete OFS Controller");
            ex.printStackTrace();
        }
	}

}  // Class   

