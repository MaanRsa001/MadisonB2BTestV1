package com.maan.Office.Controller;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
import java.sql.*;
import org.apache.commons.fileupload.*;

import java.text.*;
import com.maan.DBCon.DBConnectionStatus;
import com.maan.Office.DAO.OfficeShieldBean;

public class OFSUploadFileController extends HttpServlet
{
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		System.out.println("Do get Initialized");
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		ProcessResult(request,response);	
	}

	public void ProcessResult(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException
	{
	    File fullFile = null;
		
	    String[] data = new String[20];
		String[] filename=new String[20];
		String[] extname=new String[20];
		String cusInfo[][] = new String[0][0];
		
		boolean quickFlag = false;
		String savedPath="";
		File savedFile = null;
		int i=0;
		StringBuffer sb = new StringBuffer();
		StringBuffer sb1 = new StringBuffer();
		HttpSession session = request.getSession(true);
		String polNo = (String)session.getAttribute("polNo");
		String sessionId = (String)session.getAttribute("ses");
		String branch = (String)session.getAttribute("LoginBranchCode");
		System.out.println("Policy No is"+polNo);
		int index=0,k=0, count=0;
		String name = null;

		/** Upload Information **/

		String quoteNo = "";
		String coverId = "";
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
		int cnt = 0;
		String dd = "";
		String mm = "";
		String yyyy = "";
		String insuranceStratDate = "";
		
		String quoteInfo[][] = new String[0][0];
		String uploadOption[][] = new String[0][0];

		proId = (String) session.getAttribute("product_id");
		loginId = (String) session.getAttribute("user");
		quoteNo = (String) session.getAttribute("quoteNo");
		//schemeId = (String) session.getAttribute("schemeId");

		proId = proId == null ? "" : proId;
		loginId = loginId == null ? "" : loginId;
		quoteNo = quoteNo == null ? "" : quoteNo;
		sessionId = sessionId == null ? "" : sessionId;
		branch = branch == null ? "" : branch;

		System.out.println("loginId ...OFSUploadFileController..."+loginId);
		System.out.println("quoteNo ...OFSUploadFileController..."+quoteNo);
		System.out.println("proId ...OFSUploadFileController..."+proId);
		
		/** Upload Information **/
		OfficeShieldBean OSB= new OfficeShieldBean();

		//quoteInfo = OSB.getQuoteFileForOS(quoteNo);
		uploadOption = 	OSB.getCoverageUploadOption(quoteNo,loginId);
		quoteInfo = OSB.getCoverageUploadOption(quoteNo,loginId);
		cusInfo = OSB.getPolicyInfo(quoteNo,loginId); // Referal Screen
		schemeId = OSB.getSchemeId(quoteNo,proId);
		schemeId = schemeId == null ? "1" : schemeId;
			
		// first check if the upload request coming in is a multipart request
		PrintWriter out = response.getWriter();
		
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
				System.out.println("OFSUploadFileController ..."+e.toString());
				e.printStackTrace();
			}
			cnt = 0;
			
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
						
						if(fieldName.equals("adminStatus"))
							adminRefStatus = item.getString();

						if(fieldName.equals("adminRemarks"))
							remarks = item.getString();

						if(fieldName.equals("SaveandExit"))
							save = item.getString();

						if(fieldName.equals("reqFrom"))
							reqFrom = item.getString();
						
						if(fieldName.equals("DD"))
							dd = item.getString();
						
						if(fieldName.equals("MM"))
							mm = item.getString();
						
						if(fieldName.equals("YYYY"))
							yyyy = item.getString();
						
						System.out.println("Senthil....Kumar....C....adminRefStatus."+adminRefStatus);
						System.out.println("Senthil....Kumar....C...remarks.."+remarks);
						System.out.println("Senthil....Kumar....C...Insurance Start Date.."+dd+"-"+mm+"-"+yyyy);
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
							OSB.setLoginId(loginId);
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
			System.out.println("Counter ....i"+i);
			System.out.println("Exception is Insertion Of OFS_Upload_Data ...."+e);
			e.printStackTrace();
		}
		System.out.println("Senthil....Kumar....C...Insurance Start DateOut side .."+dd+"-"+mm+"-"+yyyy);
		insuranceStratDate = dd+"-"+mm+"-"+yyyy;
		
		String contentMaster[][]=OSB.getContentMaster(schemeId);
		if(contentMaster.length>0)
		{
			error = error + OSB.uploadSummarySubmit(quoteNo,loginId);
		}else
		{
			error = error + OSB.uploadSummarySubmit2(quoteNo,loginId,proId,schemeId);
		}
		
		StringBuffer dateErr = new StringBuffer();
		dateErr = OSB.validateInsuranceStartDate(insuranceStratDate,loginId,proId,schemeId);
		if(dateErr.length() == 0)
			OSB.updateInsSDateEDate(quoteNo,proId,schemeId,insuranceStratDate);
		else
			error = error + "<br> *"+dateErr.toString();
		
		// Tracking Master
		OSB.setSessionId(sessionId);
		OSB.setLoginId(loginId);
		OSB.setQuoteNo(quoteNo);
		if(error.length() > 0 )
			OSB.updateTrackingDetails("Remarks");
		else
			OSB.updateTrackingDetails("Upload");
		// Tracking Master

		/*if(cusInfo[0][27].equalsIgnoreCase("Referal") )
			adminRefStatus = otherRefStatus;//"Y";*/

		RequestDispatcher dispatcher = null;
		System.out.println("Save ........................................................*********"+save);
		if(!save.equalsIgnoreCase("UploadFileDelete"))
		{	
		 	if(reqFrom.equalsIgnoreCase("Normal"))
			{
				if(!adminRefStatus.equalsIgnoreCase("Y") )
				{
					if(error.length() > 0 || save.equalsIgnoreCase("SaveandExit"))
					{
						request.setAttribute("error",error);
						request.setAttribute("success","Sucessfully Saved");
						request.setAttribute("SaveandExit",save);
						dispatcher = request.getRequestDispatcher("/OfficeInsurance/UploadSummary.jsp");
							
					}
					else
					{
						 System.out.println("reqFrom ..."+reqFrom);
						 System.out.println("adminRefStatus ..."+adminRefStatus);
						 System.out.println("Sucess Fully Uploaded File and Inserted In to OFS_UPLODED_DATA 1");
						 if(cusInfo!=null && cusInfo.length>0 && !cusInfo[0][27].equalsIgnoreCase("Admin") )
							OSB.updateCancelAdminReferal(quoteNo,proId,schemeId);
						
						 //dispatcher = request.getRequestDispatcher("/OfficeInsurance/SummaryUpload.jsp");
						 response.sendRedirect(path+"/OfficeInsurance/SummaryUpload.jsp");
					}	
				}
				else	
				{
					System.out.println("Admin Referal For OfficeShield.1..");
					//OSB.updateAdminReferal(quoteNo,remarks,proId,schemeId);
					String reason = "";
					if(cusInfo!=null && cusInfo.length>0 && !cusInfo[0][27].equalsIgnoreCase("Referal"))
						OSB.updateAdminReferal(quoteNo,remarks,proId,schemeId);
					else
						reason = OSB.getReasonForReferal(quoteNo,proId,branch);
					
					// Admin Referal Mail Controller
					request.setAttribute("RefStatus","Referal");
					request.setAttribute("quoteNo",quoteNo);
					RequestDispatcher mailDispatch = request.getRequestDispatcher("/servlet/OfficeMailController");
					
					if(mailDispatch!=null)
						 mailDispatch.include(request,response);
					// Admin Referal Mail Controller
	
					request.setAttribute("CustomerInfo",cusInfo);
					
					if(cusInfo!=null && cusInfo.length>0 && !cusInfo[0][27].equalsIgnoreCase("Referal"))
						request.setAttribute("allrefValue","Admin Referral");
					else
						request.setAttribute("allrefValue",reason);
	
					dispatcher = request.getRequestDispatcher("/OfficeInsurance/Office_Referral_ShowQuote.jsp"); //Nov 14
						
				}
			} // normal
			else  // reqFrom --- Approved
			{
				if(!adminRefStatus.equalsIgnoreCase("Y") )
				{
					if(error.length() > 0 || save.equalsIgnoreCase("SaveandExit"))
					{
						request.setAttribute("error",error);
						request.setAttribute("success","Sucessfully Saved");
						request.setAttribute("SaveandExit",save);
						dispatcher = request.getRequestDispatcher("/OfficeInsurance/UploadSummary.jsp");
					}
					else
					{
						 System.out.println("Sucess Fully Uploaded File and Inserted In to OFS_UPLODED_DATA 2");
						 //dispatcher = request.getRequestDispatcher("/OfficeInsurance/SummaryUpload.jsp");
						 response.sendRedirect(path+"/OfficeInsurance/SummaryUpload.jsp");
					}
				}
				else	
				{
					if( cusInfo!=null && cusInfo.length>0 &&  cusInfo[0][27].equalsIgnoreCase("Admin") && adminRefStatus.equalsIgnoreCase("Y") ) // admin re referal
					{
						//System.out.println("Admin Referal For OfficeShield.2.."+cusInfo[0][27]);
						OSB.updateAdminReferal(quoteNo,remarks,proId,schemeId);
					
						// Admin Referal Mail Controller
						request.setAttribute("RefStatus","Referal");
						request.setAttribute("quoteNo",quoteNo);
	
						RequestDispatcher mailDispatch = request.getRequestDispatcher("/servlet/OfficeMailController");
					
						if(mailDispatch!=null)
							mailDispatch.include(request,response);
						// Admin Referal Mail Controller
					
						request.setAttribute("CustomerInfo",cusInfo);
						request.setAttribute("allrefValue","Admin Referral");
						dispatcher = request.getRequestDispatcher("/OfficeInsurance/Office_Referral_ShowQuote.jsp");
					}
					else
					{
						if(error.length() > 0 || save.equalsIgnoreCase("SaveandExit"))
						{
							request.setAttribute("error",error);
							request.setAttribute("success","Sucessfully Saved");
							request.setAttribute("SaveandExit",save);
							dispatcher = request.getRequestDispatcher("/OfficeInsurance/UploadSummary.jsp");
						}
						else
						{
							System.out.println("Sucess Fully Uploaded File and Inserted In to OFS_UPLODED_DATA 3");
							//dispatcher = request.getRequestDispatcher("/OfficeInsurance/SummaryUpload.jsp");
							response.sendRedirect(path+"/OfficeInsurance/SummaryUpload.jsp");
						}
					}
				}
			}
		}
		else
			dispatcher = request.getRequestDispatcher("/OfficeInsurance/UploadSummary.jsp");
		
		if(dispatcher!=null)
		     dispatcher.forward(request,response);
		if(true) return;
	}

} //Class
