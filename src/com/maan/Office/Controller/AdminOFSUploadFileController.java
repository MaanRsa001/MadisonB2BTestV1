package com.maan.Office.Controller;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
import java.sql.*;
import org.apache.commons.fileupload.*;

import java.text.*;
import java.util.Date;
import master.bean.*;
import com.maan.DBCon.DBConnectionStatus;
import com.maan.Office.DAO.OfficeShieldBean;

public class AdminOFSUploadFileController extends HttpServlet
{
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		System.out.println("Do get Initialized");
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		File fullFile = null;
		String[] data = new String[10];
		String[] table_name=new String[10];
		String[] c_id=new String[10];
		String[] cycle_date=new String[10];
		String[] filename=new String[10];
		String[] newfilename=new String[10];
		String[] extname=new String[10];
		String cusInfo[][] = new String[0][0];
		boolean quickFlag = false;
		String savedPath="";
		File savedFile = null;
		int i=0,j=0,m=0,l=0;
		StringBuffer sb = new StringBuffer();
		StringBuffer sb1 = new StringBuffer();
		HttpSession session = request.getSession(true);
		String polNo = (String)session.getAttribute("polNo");
		String sessionId = (String)session.getAttribute("ses");
		System.out.println("Policy No is"+polNo);
		int index=0,k=0, count=0,ct=0;
		Vector a1 = new Vector();
		String name = null;
		Connection CON=null;
		String status="",RcodeStatus="";

		/** Upload Information **/

		String quoteNo = "";
		String coverId = "";
		String contentTypeId = "";
		String proId = "";
		String loginId = "";
		String schemeId = "";
		String custFileName = "";
		String systemFileName = "";
		String error = "";
		String remarks = "";
		String adminRefStatus = "";
		String save = "";
		String reqFrom = "";
		String quoteLogin = "";
		String quoteInfo[][] = new String[0][0];
		String uploadOption[][] = new String[0][0];
		String UploadSes = "";
		UploadSes = (String) session.getAttribute("UploadSes");
		proId = (String) session.getAttribute("product_id");
		loginId = (String) session.getAttribute("user");
		quoteNo = (String) session.getAttribute("quoteNo");
		//schemeId = (String) session.getAttribute("schemeId");

		UploadSes = UploadSes == null ? "" : UploadSes;
		proId = proId == null ? "" : proId;
		loginId = loginId == null ? "" : loginId;
		quoteNo = quoteNo == null ? "" : quoteNo;
		sessionId = sessionId == null ? "" : sessionId;
		
		if(session.getAttribute("UploadSes")!= null)
			session.removeAttribute("UploadSes");

		System.out.println("loginId ...AdminOFSUploadFileController..."+loginId);
		System.out.println("quoteNo ...AdminOFSUploadFileController..."+quoteNo);
		System.out.println("proId ...AdminOFSUploadFileController..."+proId);
		
		/** Upload Information **/

		OfficeShieldBean OSB= new OfficeShieldBean();

		//quoteInfo = OSB.getQuoteFileForOS(quoteNo);
		quoteLogin = OSB.getQuoteLogin(quoteNo);
		uploadOption = 	OSB.getCoverageUploadOption(quoteNo,quoteLogin);
		quoteInfo = OSB.getCoverageUploadOption(quoteNo,quoteLogin);
		cusInfo = OSB.getPolicyInfo(quoteNo,quoteLogin); // Referal Screen
		schemeId = OSB.getSchemeId(quoteNo,proId);

		schemeId = schemeId == null ? "1" : schemeId;
			
		// first check if the upload request coming in is a multipart request
		
		PrintWriter out = response.getWriter();
		
		boolean isMultipart = FileUpload.isMultipartContent(request);
		
		// Create a new file upload handler
		
		DiskFileUpload upload = new DiskFileUpload();

		// parse this request by the handler this gives us a list of items from the request
		
		List items = null;

		//SESSION CHECK
		String path = request.getContextPath();
        /*RequestDispatcher check = request.getRequestDispatcher("login/sessionsCheckNormal.jsp");
        check.include(request,response);*/
        if(session.getAttribute("ses")==null)
		{	
			response.sendRedirect(path+"/login/error_messg.jsp");
			return;
		}          
        String usrModeSC=(String)session.getAttribute("userLoginMode")==null?"":(String)session.getAttribute("userLoginMode");
        DBConnectionStatus.statusStatic=usrModeSC;
        //END OF SESSION CHECK
	  
	  RequestDispatcher dispatcher = null;
	  if(UploadSes.equalsIgnoreCase("Upload"))
	  {
		try
		{
			try
			{
				items = upload.parseRequest(request);
				System.out.println("OFS Upload File Controller....."+items);	
			} 
			catch (FileUploadException e)
			{
				// TODO Auto-generated catch block
				System.out.println("AdminOFSUploadFileController ..."+e.toString());
				e.printStackTrace();
			}
			int cnt = 0;
			
			Iterator itr = items.iterator();
			while(itr.hasNext())
			{
					savedPath="";
					FileItem item = (FileItem) itr.next();
			
				if(item.isFormField())
				{
						String fieldName = item.getFieldName();
						
						if(fieldName.equals("coverId_1"))
							coverId = item.getString();

						if(fieldName.equals("coverId_2"))
							coverId = item.getString();

						if(fieldName.equals("coverId_3"))
							coverId = item.getString();

						if(fieldName.equals("coverId_4"))
							coverId = item.getString();
						
						if(fieldName.equals("reqFrom"))
							reqFrom = item.getString();
						
						if(fieldName.equals("SaveandExit"))
							save = item.getString();

						if(fieldName.equals("refStatus"))
							adminRefStatus = item.getString();

						System.out.println("Senthil....Kumar....C....adminRefStatus."+adminRefStatus);
						System.out.println("Senthil....Kumar....C...save.."+save);
				}
				else
				{
					// check if the current item is a form field or an uploaded file
					// the item must be an uploaded file save it to disk. Note that there
					// seems to be a bug in item.getName() as it returns the full path on
					// the client's machine for the uploaded file name, instead of the file
					// name only. To overcome that, I have used a workaround using
					// fullFile.getName().
				 
					String fileRealPath = request.getRealPath("/"+"OFSUploadedFiles/");
					fullFile = new File(item.getName());
					
					// savedFile = new File(""+s, fullFile.getName());
					 System.out.println("Full File is....Senthilkumar... "+fullFile);
					// System.out.println("Saved file is"+savedFile);
				if(fullFile!=null)
				{
					index = fullFile.getName().lastIndexOf("\\");
					k = fullFile.getName().length();
					name = fullFile.getName().substring(index+1,k);
					custFileName = fullFile.getName().substring(index+1,k);
					System.out.println("OfficeShield Old File Name is ...."+custFileName);
					System.out.println("OfficeShield Old File Name Length is...."+custFileName.length());
					
					if( name.length() > 0)
					{
						index = name.lastIndexOf(".");
						 //System.out.println("Full File index"+index);
						try
						{
						  filename[i] = name.substring(0,index);
						  extname[i] = name.substring(index+1,name.length());
						}
						catch(Exception e)
						{
							System.out.println("error in file name");
							e.printStackTrace();
						}
						//getting system time and adding to file name
						Calendar cal = Calendar.getInstance();
						//SimpleDateFormat sdf = new SimpleDateFormat("ddMMMyyyy");
						SimpleDateFormat sdf = new SimpleDateFormat("'on'ddMMMyyyy h.mm.ss a ");
						String date=sdf.format(cal.getTime());
						System.out.println("extension Name is"+extname[i]);
						/*newfilename[i]=filename[i]+date;
						systemFileName = filename[i]+date.trim()+"."+extname[i];*/
						if(quoteInfo.length > 0)
						{
							systemFileName = //quoteNo+"pId"+proId+"sId"+schemeId+"cvId"+quoteInfo[cnt++][1]+"ctId"+contentTypeId+date.trim()+"."+extname[i];
							systemFileName = quoteNo+"pId"+proId+"sId"+schemeId+"cvId"+quoteInfo[cnt][1]+"ctId"+quoteInfo[cnt][3]+date.trim()+"."+extname[i];
							
							System.out.println("OfficeShield Old File Name is ...."+systemFileName);
						}
						//savedFile = new File(""+s, filename[i]+date.trim()+"."+extname[i]);
						savedFile = new File(""+fileRealPath, systemFileName);
						savedPath=savedFile.toString();
				   }
						count++;
					   if(!name.equals(""))
					   {
							sb.append(""+count);
							//sb1.append(""+name);
					   }
					  if(!name.equals(""))
					  {
						 sb1.append(""+name);
					  }
					//data[i] = ""+name;
					data[i] = savedPath;
					System.out.println("i is"+i+" "+savedPath);
					
				/*	if("xls".equalsIgnoreCase(extname[i]))
					{	*/
					 if(!name.equals(""))
					 {
						System.out.println("coming to write file");
						try 
						{
							item.write(savedFile);
							System.out.println("file written");
						}
						catch (Exception e1)
						{
						  // TODO Auto-generated catch block
						  System.out.println("unable to write file");
						  e1.printStackTrace();
						}
					 }
				  //} // extname if
					
				 //Insertion Of OFS_Upload_Data
					
					if(name.length() > 0)
					{
						quickFlag = OSB.checkingForQuickFileUpload(quoteNo,proId,schemeId,quoteInfo[cnt][3],quoteInfo[cnt][1],"AddInfo");
					}
				
					if(!quickFlag)
					{
						if(name.length() > 0)
						{	System.out.println("...COUNT..."+name+"..."+cnt);
							OSB.setProId(proId);
							OSB.setLoginId(quoteLogin);
							OSB.setQuoteNo(quoteNo);
							OSB.setSchemeId(schemeId);
							OSB.setCoverId(quoteInfo[cnt][1]);
							OSB.setContentTypeId(quoteInfo[cnt][3]);
							OSB.setCustFileName(custFileName);
							OSB.setSystemFileName(systemFileName);
							OSB.insertOFSUploadedData();
						}
					}
					else
					{
						String uplodCover = "";
						for(int p=0;p<uploadOption.length;p++)
						{
							if(quoteInfo[cnt][1].equalsIgnoreCase(uploadOption[p][1]))
							{
								uplodCover = uploadOption[p][0];
								break;
							}
						}
						error = error+"<br> * Already quick upload option is done in "+uplodCover;
						quickFlag = false;
					}
					cnt++;
				 } // fullfile
			  } // formfield else
			i++;
			}//while Loop			
		}
		catch(Exception e)
		{ 
			System.out.println("Exception is Insertion Of OFS_Upload_Data ...."+e);
			e.printStackTrace();
		}
		
		error = error + OSB.uploadSummarySubmit(quoteNo,quoteLogin);
		
		// Tracking Master
		OSB.setSessionId(sessionId);
		OSB.setLoginId(quoteLogin);
		OSB.setQuoteNo(quoteNo);
		if(error.length() > 0 )
			OSB.updateTrackingDetails("Remarks");
		else
			OSB.updateTrackingDetails("Upload");
		
		// Tracking Master

		//RequestDispatcher dispatcher = null;
		System.out.println("SaveandContinue........Admin OFS Upload File Controller....."+save);
		if(save.equalsIgnoreCase("SaveandContinue"))
		{
			/*if( error.length() > 0 )
			{
				request.setAttribute("error",error);
				dispatcher = request.getRequestDispatcher("../OfficeInsurance/adminUploadSummary.jsp");
			}
			else
			{*/
				System.out.println("AdminOFSUploadFileController........"+quoteNo);
				OSB.updateReferralStatus(quoteNo,proId,schemeId,adminRefStatus);
				// Response Mail Jan 23
				request.setAttribute("RefStatus",adminRefStatus);
				request.setAttribute("quoteNo",quoteNo);
				RequestDispatcher mailDispatch = request.getRequestDispatcher("/servlet/OfficeMailController");
				if(mailDispatch!=null)
					mailDispatch.include(request,response);
				//Response Mail Jan 23
				
				// Referesh Problem
				session.setAttribute("referralStatus",adminRefStatus);
				request.setAttribute("refStatus",adminRefStatus);
				dispatcher = request.getRequestDispatcher("../OfficeInsurance/RefInfo.jsp");
			//}	
		}
	} // New Chking Jan 31	
	else
	{
		String adminRefStatusReferesh = (String) session.getAttribute("referralStatus");
		adminRefStatusReferesh = adminRefStatusReferesh == null ? "" :  adminRefStatusReferesh;
		request.setAttribute("refStatus",adminRefStatusReferesh);
		request.setAttribute("quoteNo",quoteNo);
		dispatcher = request.getRequestDispatcher("../OfficeInsurance/RefInfo.jsp");
	}
		if(dispatcher!=null)
			dispatcher.forward(request,response);
	}

} //Class
