package com.maan.Office.Mail;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.ServletConfig;
import java.sql.Connection;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import com.maan.Office.Mail.OfficeMailBean;
import com.maan.Office.DAO.OfficeShieldBean;
import com.maan.Home.DataManipualtion.DataSelect; 
import com.maan.Home.DataManipualtion.HomePdfDataSelect;

public class OfficeMailController extends HttpServlet
{
	PrintWriter out=null;
	Connection con=null;
	java.util.Date dd=new java.util.Date();
	
	String SMTP_AUTH_USER = "";
	String SMTP_AUTH_PWD  = "";
	String SMTP_HOST_NAME = "";
	String emailSubjectTxt = "";
	
	OfficeMailBean OMB = new OfficeMailBean();
	OfficeShieldBean OSB = new OfficeShieldBean();
	HomePdfDataSelect pdfDataSelect = new HomePdfDataSelect();
	DataSelect Cover = new DataSelect();

	public void init(ServletConfig  config)throws ServletException
	{
		System.out.println("Office Referal Mail Controller");
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException
	{
		ProcessResult(request,response);
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException
	{
		//ProcessResult(request,response);
		System.out.println("Office Referal Mail Controller doGet");
	}
	
	public void ProcessResult(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException
	{
		HttpSession session = request.getSession(true);

		String quoteNo = "";
		String loginId = "";
		String proId = "";
		String path = ""; 
		String branch = "";
		String msg = "";
		String subj = "";
		String cc1Address[] = new String[0];
		String status = "";
		String schemeId = "";
		String schemeName = "";
		String CustomerMailQuote = "";
		HashMap mailQuoteMailId  = new HashMap();
		
		if(session.getAttribute("ses")==null)
		{	
			response.sendRedirect(path+"/login/error_messg.jsp");
			return;
		}
		
		path = request.getContextPath();
		loginId = (String) session.getAttribute("user");
		proId = (String) session.getAttribute("product_id");
		quoteNo = (String)request.getAttribute("quoteNo");
		branch = (String)session.getAttribute("LoginBranchCode");
		status = (String)request.getAttribute("RefStatus");
		CustomerMailQuote = (String) request.getAttribute("CustomerMailQuote");
		
		HashMap schemeHash = new HashMap();
		String brokerBra = (String)session.getAttribute("LoginBranchCode");
		HashMap brokerDetails = (HashMap)session.getAttribute("BrokerDetails");
		String cid="";
		String dcid="";
		String decimalPlace ="";
		String CurrencyType="";
		if(brokerDetails.size()>0)
		{
			CurrencyType=(String)brokerDetails.get("CurrencyName");
			decimalPlace =(String) brokerDetails.get("CurrencyDecimal");
			decimalPlace = decimalPlace == null ? "2" :decimalPlace;
		}

		status = status == null ? "" : status;
		branch = branch == null ? "020" : branch;
		loginId = loginId == null ? "" : loginId;
		quoteNo = quoteNo == null ? "" : quoteNo;
		CustomerMailQuote = CustomerMailQuote == null ? "" : CustomerMailQuote;
		//proId = proId == null ? "30" : proId;
		
		proId = OMB.getProductId(quoteNo);
		OMB.setBranch(branch);
		OMB.setProId(proId);
		OMB.setCurrencyType(CurrencyType);
		
		try
		{
			if(CustomerMailQuote != null && CustomerMailQuote.length() > 0 && CustomerMailQuote.equalsIgnoreCase("CustomerMailQuote"))
			{
				msg = OMB.officeBodyOfMsg(quoteNo,status,CustomerMailQuote);
				mailQuoteMailId = OMB.getMailQuoteMailIds(quoteNo);
			}
			else
				msg = OMB.officeBodyOfMsg(quoteNo,status,"ReferralResponse");
			
			schemeId = OSB.getSchemeId(quoteNo,proId);
			OMB.setSchemeId(schemeId);
			schemeHash = OSB.getOFSSchemeMaster(branch,proId);
			
			if(schemeHash.size() > 0)
				schemeName = (String)schemeHash.get(schemeId);

			java.util.HashMap rDetails = new java.util.HashMap();
			rDetails = OMB.getReferralDetails("Home Referral");
			String ccAdd = (String)rDetails.get("emailcc");
			String toAdd = (String)rDetails.get("emailto");
			StringTokenizer st1 = new StringTokenizer(ccAdd,",");
			int count = 0;
			int i=0;
			String subject="";
			String id="";
			String bremail = "";
			while(st1.hasMoreElements())
			{
				String k = (String)st1.nextElement();
				count++;
			}
			
			System.out.println("Count value is "+count);
			cc1Address = new String[count];
			StringTokenizer st2 = new StringTokenizer(ccAdd,",");
			i=0;
			while(st2.hasMoreElements())
			{
				System.out.println("Inside blocke");
				cc1Address[i] = (String)st2.nextElement();
				System.out.println("VAlue is"+cc1Address[i]);
				i++;
			}

			subject = (String)rDetails.get("emailsubject");

			String[] emailList = {""+toAdd};
			String emailList1 = "";
			String mailFrom[][] = new String[0][0];
			mailFrom = Cover.getFromEmailForLogin(loginId);
			if(mailFrom.length>0)
			{
				bremail = mailFrom[0][0]!=null?mailFrom[0][0]:"";
				subj = mailFrom[0][1]!=null?mailFrom[0][1]:"";
			}
			System.out.println("RoyalTest from mail address for Broker/user...."+bremail);
			System.out.println("Email list is"+emailList);
			String mailIds = pdfDataSelect.getAdminBrokerMailId(quoteNo);
			OfficeMailController OMC = new OfficeMailController();
			
			if(!msg.equalsIgnoreCase("Some Exception OfficeMailBean"))
			{	
				if(CustomerMailQuote != null && CustomerMailQuote.length() > 0 && CustomerMailQuote.equalsIgnoreCase("CustomerMailQuote") && mailQuoteMailId.size() > 0)
				{
					System.out.println("Customer Mail Quote .........................."+CustomerMailQuote);
					emailSubjectTxt = schemeName+" � Quotation";
					String fromId = "";
					String toId = "";
					if(mailQuoteMailId.size() > 0 )
					{	
						fromId = (String) mailQuoteMailId.get("FromId");
						toId = (String) mailQuoteMailId.get("ToId");
						OMC.CustomerEmailQuotePostMail(toId,emailSubjectTxt, msg, fromId, subj );
					}
				}
				else
				{
					if(status.equalsIgnoreCase("Referal"))
					{
						emailSubjectTxt = schemeName+" � REFERRAL";
						OMC.referalPostMail( emailList,cc1Address,emailSubjectTxt, msg, bremail,subj);
					}
					else
					{
						if(mailIds.length() >0)
						{
								bremail = mailIds.substring(0,mailIds.indexOf(","));
								emailList1 = mailIds.substring(mailIds.indexOf(",")+1, mailIds.length());
						}
						emailSubjectTxt = schemeName+" � REFERRAL Response";
						OMC.responsePostMail( bremail,cc1Address,emailSubjectTxt, msg,emailList1,subj);
					}
				}
			}
			else
			{
				System.out.println("Some Exception OfficeMailBean...");
			}
			System.out.println(".emailList....:"+emailList);
			System.out.println(".cc1Address....:"+cc1Address);
			System.out.println(".emailSubjectTxt....:"+emailSubjectTxt);
			System.out.println(".subj....:"+subj);

		}
		catch(Exception e1)
		{
			System.out.println("Error"+e1);
			e1.printStackTrace();
		}
		
	} // Process Result

	public void referalPostMail( String recipients[], String ccreceipients[],String subject, String message, String emailFromAddress, String subj ) throws MessagingException
	{
		try
		{
			HashMap PortDet = OMB.getPortDetails();
			SMTP_HOST_NAME = (String)PortDet.get("hostname");   // Getting the values from beans in the form of Hashtable
			SMTP_AUTH_USER = (String)PortDet.get("username");
			SMTP_AUTH_PWD = (String)PortDet.get("password");
		
			boolean debug = false;
			//Set the host smtp address
			Properties props = new Properties();
			props.put("mail.smtp.auth", "true");
	  		  props.put("mail.smtp.starttls.enable", "true");
	  		  props.put("mail.smtp.host", SMTP_HOST_NAME);
			  props.put("mail.smtp.port", "587");
			Authenticator auth = new SMTPAuthenticator();
			Session session = Session.getInstance(props, auth);
			session.setDebug(debug);
			// create a message
			Message msg1 = new MimeMessage(session);
			// set the from and to address
			System.out.println("Subject is"+subj);
			InternetAddress addressFrom = new InternetAddress(emailFromAddress,subj);
			msg1.setFrom(addressFrom);
				
			if((recipients != null) || (! recipients.equals("")))
			{
				InternetAddress[] addressTo = new InternetAddress[recipients.length];
			
				for (int i = 0; i < recipients.length; i++)
				{
					addressTo[i] = new InternetAddress(recipients[i]);
					msg1.addRecipient(Message.RecipientType.TO, addressTo[i]);
				}
			}
			
			if((ccreceipients != null) || (! ccreceipients.equals("")))
			{
				InternetAddress[] addressToCC = new InternetAddress[ccreceipients.length];
			
				for(int i=0;i<ccreceipients.length;i++)
				{
					addressToCC[i] = new InternetAddress(ccreceipients[i]);
					msg1.addRecipient(Message.RecipientType.CC, addressToCC[i]);
				}
			}
			// Setting the Subject and Content Type
			msg1.setSubject(subject);
			// System.out.println("Message from out "+message);
			msg1.setContent(message, "text/html");
			System.out.println(" referalPostMail Before sending "+msg1);
			try{
			Transport.send(msg1);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			System.out.println(" referalPostMail After sending ");
			System.out.println(" Message send successfully ");
		}
		catch(Exception ex)
		{
			System.out.println("  referalPostMail postMail   "+ex);
			ex.printStackTrace();
		}
	}
	
	public void responsePostMail( String recipients, String ccreceipients[],String subject, String message, String emailFromAddress, String subj ) throws MessagingException
	{
		  try
		  {
				 HashMap PortDet = OMB.getPortDetails();
		  		 SMTP_HOST_NAME = (String)PortDet.get("hostname");   // Getting the values from beans in the form of Hashtable
		  		 SMTP_AUTH_USER = (String)PortDet.get("username");
		  		 SMTP_AUTH_PWD = (String)PortDet.get("password");
		  		 emailFromAddress  = (String)PortDet.get("Address");
		  		 System.out.println("Host name"+SMTP_HOST_NAME);
		  		 System.out.println("Authorized usr"+SMTP_AUTH_USER);
		  		 System.out.println("Authorized pss"+SMTP_AUTH_PWD);
				 System.out.println("Email"+emailFromAddress);
			     boolean debug = false;
			     //Set the host smtp address
			     System.out.println("Host name"+SMTP_HOST_NAME);
			     Properties props = new Properties();
			     props.put("mail.smtp.auth", "true");
		  		  props.put("mail.smtp.starttls.enable", "true");
		  		  props.put("mail.smtp.host", SMTP_HOST_NAME);
				  props.put("mail.smtp.port", "587");
			     Authenticator auth = new SMTPAuthenticator();
			     Session session = Session.getInstance(props, auth);
			     session.setDebug(debug);
			     // create a message
			     Message msg = new MimeMessage(session);
			     // set the from and to address
			     // emailFromAddress = bremail;
			     System.out.println("Subject is"+subj);
			     InternetAddress addressFrom = new InternetAddress(emailFromAddress,subj);
			     System.out.println("addressFrom"+emailFromAddress);
			     msg.setFrom(addressFrom);
			     System.out.println("Address To");
			     /*if((recipients != null) || (! recipients.equals("")))
			     {
				    InternetAddress[] addressTo = new InternetAddress[recipients.length];
    			    System.out.println("Lenght is"+recipients.length);
					for (int i = 0; i < recipients.length; i++)
					addressTo[i] = new InternetAddress(recipients[i]);
					for (int i = 0; i < recipients.length; i++)
					{
					   addressTo[i] = new InternetAddress(recipients[i]);
					   msg.addRecipient(Message.RecipientType.TO, addressTo[i]);
					}
			     }*/

				 if((ccreceipients != null) || (! ccreceipients.equals("")))
				  {
					  InternetAddress[] addressToCC = new InternetAddress[ccreceipients.length];
							for(int i=0;i<ccreceipients.length;i++)
							{
									  addressToCC[i] = new InternetAddress(ccreceipients[i]);
									  msg.addRecipient(Message.RecipientType.CC, addressToCC[i]);
							}
				  }

				InternetAddress addressTo = new InternetAddress(recipients);
				msg.addRecipient(Message.RecipientType.TO,addressTo);
				
				/*InternetAddress addressToCC = new InternetAddress(ccreceipients);
				msg.addRecipient(Message.RecipientType.CC,addressToCC);*/
				// Setting the Subject and Content Type
				msg.setSubject(subject);
				msg.setContent(message, "text/html");
				System.out.println(" responsePostMail Before sending");
				try{
					Transport.send(msg);
				}catch(Exception e){
					System.out.println("Exception is ..."+e);
					e.printStackTrace();
				}

				System.out.println(" responsePostMail After sending");
				System.out.println(" responsePostMail Message send successfully");
		  }	
		  catch(Exception ex)
		  {
   				System.out.println(ex);
   				ex.printStackTrace();
		  }
	}
	
	public void CustomerEmailQuotePostMail(String recipients, String subject, String message, String emailFromAddress, String subj ) throws MessagingException
	{
		 try
		  {
				 HashMap PortDet = OMB.getPortDetails();
		  		 SMTP_HOST_NAME = (String)PortDet.get("hostname");   // Getting the values from beans in the form of Hashtable
		  		 SMTP_AUTH_USER = (String)PortDet.get("username");
		  		 SMTP_AUTH_PWD = (String)PortDet.get("password");
		  		 emailFromAddress  = (String)PortDet.get("Address");
		  		 System.out.println("Host name"+SMTP_HOST_NAME);
		  		 System.out.println("Authorized usr"+SMTP_AUTH_USER);
		  		 System.out.println("Authorized pss"+SMTP_AUTH_PWD);
				 System.out.println("Email"+emailFromAddress);
			     boolean debug = false;
			     //Set the host smtp address
			     System.out.println("Host name"+SMTP_HOST_NAME);
			     Properties props = new Properties();
			     props.put("mail.smtp.auth", "true");
		  		  props.put("mail.smtp.starttls.enable", "true");
		  		  props.put("mail.smtp.host", SMTP_HOST_NAME);
				  props.put("mail.smtp.port", "587");
			     Authenticator auth = new SMTPAuthenticator();
			     Session session = Session.getInstance(props, auth);
			     session.setDebug(debug);
			     // create a message
			     Message msg = new MimeMessage(session);
			     // set the from and to address
			     // emailFromAddress = bremail;
			     System.out.println("Subject is"+subj);
			     InternetAddress addressFrom = new InternetAddress(emailFromAddress,subj);
			     System.out.println("addressFrom"+emailFromAddress);
			     msg.setFrom(addressFrom);
			     System.out.println("Address To");
			     InternetAddress addressTo = new InternetAddress(recipients);
				 msg.addRecipient(Message.RecipientType.TO,addressTo);
				 // Setting the Subject and Content Type
				msg.setSubject(subject);
				msg.setContent(message, "text/html");
				System.out.println(" CustomerEmailQuotePostMail Before sending");
				try{
					Transport.send(msg);
				}catch(Exception e){
					System.out.println("Exception is ..."+e);
					e.printStackTrace();
				}

				System.out.println(" CustomerEmailQuotePostMail After sending");
				System.out.println("CustomerEmailQuotePostMail Message send successfully");
		  }	
		  catch(Exception ex)
		  {
  				System.out.println(ex);
  				ex.printStackTrace();
		  }
	}

	private class SMTPAuthenticator extends javax.mail.Authenticator
	{
		public PasswordAuthentication getPasswordAuthentication()
		{
			System.out.println("Inside authentication");
			String username = SMTP_AUTH_USER;
			String password = SMTP_AUTH_PWD;
			return new PasswordAuthentication(username, password);
		}
	}

}// Class
