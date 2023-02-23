
package com.maan.Mail.controller;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;

import com.maan.DBCon.DBConnectionStatus;
import com.maan.Mail.DAO.MailInformation;
import com.maan.Mail.DAO.MailerBean;
import com.maan.common.LogManager;
import com.maan.common.exception.BaseException;
public class mailController extends HttpServlet
{
		
		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private transient String SMTP_HOST_NAME = "";
	private transient String SMTP_AUTH_USER = "";
	private transient String SMTP_AUTH_PWD  = "";
	private transient String SMTP_PORT  = "";
	private transient String COMPANY_NAME  = "";
	private transient String EMAIL_FROM  = "";
	private transient String[] ccreceipients;
	final private String ENTER = "- Enter";
	final private String EXIT = "- Exit";
	final private String CUST = "cust";
	public void doGet(final HttpServletRequest request, final HttpServletResponse response)throws ServletException, IOException
	{
		try{
			final String mailFrom = request.getParameter("mailFrom")==null?"None":request.getParameter("mailFrom");
			
			if(mailFrom.equalsIgnoreCase("FreightBulk")){
				processResult(request, response);
			}else{
				processRequest(request, response);
			}
		} catch(BaseException e) {
			LogManager.debug(e);
		}
	}
	public void doPost(final HttpServletRequest request, final HttpServletResponse response)throws ServletException, IOException
	{
		try{
			final String mailFrom = request.getParameter("mailFrom")==null?"None":request.getParameter("mailFrom");
			System.out.println("Test+"+mailFrom);
			if(mailFrom.equalsIgnoreCase("FreightBulk")){
				processResult(request, response);
			}else{
				processRequest(request, response);
			}
		} catch(BaseException e) {
			LogManager.debug(e);
		}
	}
	public void processRequest(final HttpServletRequest request, final HttpServletResponse response)throws ServletException, IOException,BaseException
	{
		LogManager.push("processRequest mailcontroller method()");
		LogManager.debug(ENTER);
		try{
			final HttpSession session = request.getSession(false);
			final String path = request.getContextPath();
			if(session.getAttribute("ses")==null)
			{	
				response.sendRedirect(path+"/login/error_messg.jsp");
				return;
			}
			DBConnectionStatus.statusStatic=(String)session.getAttribute("userLoginMode")==null?"":(String)session.getAttribute("userLoginMode");
			response.setContentType("text/html");
			
			String emailSubjectTxt;
			String toId = "";
			String ccId = "";
			String mess = "";
			String emailSub = "";
			final MailerBean mailer =new MailerBean();
			final Map freDetails = new HashMap();
			
			String quoteNo = (String)session.getAttribute("quote_no")==null?(String)request.getParameter("quote_no"):(String)session.getAttribute("quote_no");
			String loginId;
			String productId;
			String applicationNo = (String)session.getAttribute("applicationNo");
			String openCoverNo;
			if(applicationNo == null)
			{
				mailer.setQuoteNo(quoteNo);
				mailer.getQuoteDetails("Quote");
				applicationNo = mailer.getApplicationNo();
				loginId = mailer.getLoginId();
				productId = mailer.getProductId();
				openCoverNo = mailer.getOpenCoverNo();
			}else{
				mailer.setApplicationNo(applicationNo);
				mailer.getQuoteDetails("Application");
				applicationNo = mailer.getApplicationNo();
				loginId = mailer.getLoginId();
				productId = mailer.getProductId();
				openCoverNo = mailer.getOpenCoverNo();
				quoteNo =mailer.getQuoteNo();
			}
			
			String freightUser = "";
			if(session.getAttribute("freight")!=null){
				freightUser = (String)session.getAttribute("freight");
			}
			final String mode = mailer.isNull(request.getParameter("mode"),"");
			//rajesh world work stated
			final String brokerBra = (String)session.getAttribute("LoginBranchCode");
			final HashMap brokerDetails = (HashMap)session.getAttribute("BrokerDetails");
			String cid="";
			String decimalPlace ="";
			String CurrencyType="";
			if(!brokerDetails.isEmpty())
			{
				CurrencyType=(String)brokerDetails.get("CurrencyName");
				cid = (String)brokerDetails.get("Orgination");
				decimalPlace = mailer.isNull((String)brokerDetails.get("CurrencyDecimal"),"2");
			}
			final java.text.NumberFormat fmt1 = new java.text.DecimalFormat(mailer.getFormatDigit(decimalPlace));
			//End
			mailer.setQuoteNo(quoteNo);
			mailer.setLoginId(loginId);
			mailer.setApplicationNo(applicationNo);
			
			final String mailFrom = mailer.isNull(request.getParameter("mailFrom"),"None");
			String scrnFrom;
			scrnFrom = mailer.isNull(request.getParameter("scrnFrom"),"None");
			LogManager.info("Madison Insurance test..mailFrom......."+mailFrom);
			if(mailFrom.equalsIgnoreCase("CompViewQuote")){
				toId = request.getParameter("to");
				ccId = request.getParameter("CC");
				emailSub = request.getParameter("sub");
				mess = request.getParameter("messag");
				mailer.setFromId(request.getParameter("from"));
				mailer.setToId(toId);
				mailer.setCcId(ccId);
				mailer.setEmailSub(emailSub);
				mailer.setMess(mess);
				mailer.setQuoteNo(quoteNo);
				final String errorDetail = mailer.validateText();
				if(errorDetail.length()>0)
				{
					request.setAttribute("errorDetail",errorDetail);
					final RequestDispatcher reqDis = getServletContext().getRequestDispatcher("/mail.jsp");
					reqDis.forward(request,response);if(true)return;
				}
			}
			else if(mailFrom.equalsIgnoreCase("FreightGroup")){
				final String premiumStatus = mailer.getPremiumProvision(loginId);
				if(premiumStatus.equalsIgnoreCase("D"))
				{
					final String rateOption = mailer.getFreightRateOption(loginId);
					freDetails.put("rateOption", mailer.isNull(rateOption,""));
				}
				final String status = request.getParameter("status");
				final String freightStatus = request.getParameter("freight_status")==null?"":request.getParameter("freight_status");
				String quoteStatus = "";
				String policyStatus = "";
				if("F".equalsIgnoreCase(freightStatus)){
					quoteStatus = request.getParameter("quote_status")==null?"":request.getParameter("quote_status");
					policyStatus = request.getParameter("policy_status")==null?"":request.getParameter("policy_status");
				}
				LogManager.info("royal freight mail test after mergerd..status..."+status);
				LogManager.info("royal freight mail test after mergerd..freightStatus..."+freightStatus);
				LogManager.info("royal freight mail test after mergerd..quoteStatus..."+quoteStatus);
				LogManager.info("royal freight mail test after mergerd..policyStatus..."+policyStatus);
				freDetails.put("premiumStatus", premiumStatus);
				freDetails.put("freightStatus", freightStatus);
				freDetails.put("status", status);
				freDetails.put("policyStatus", policyStatus);
				freDetails.put("quoteStatus", quoteStatus);
			}
			final String Freight = mailer.isNull(request.getParameter("freightunapp"),"");
			final String refStatus = mailer.isNull(request.getParameter("refRes_status"),""); 
			final String freDirection = mailer.isNull(request.getParameter("freightRedirection"),"");
			
			//Rajesh tring here
			final MailInformation mailBean = new MailInformation();
			final StringBuffer emailMsgTxt = new StringBuffer();
			if(mailFrom.equalsIgnoreCase("FreightGroup")){
				emailMsgTxt.append(mailBean.getFreEmailMsgTxt(quoteNo,freDetails));
			}else{
				emailMsgTxt.append(mailBean.getEmailMsgTxt(quoteNo,mailFrom,refStatus,mess));
			}
			final Map mailInfo = mailBean.getDetailedTable(brokerBra,cid,quoteNo,loginId,applicationNo,CurrencyType,fmt1,mailFrom,freDetails,Freight);
			emailMsgTxt.append((String)mailInfo.get("content"));
			emailMsgTxt.append(mailBean.getCluausesDetails(brokerBra,cid,quoteNo,loginId,applicationNo,productId,freightUser,openCoverNo));		

			final String userType = (String)session.getAttribute("usertype");
			
			final String admrem = (String)mailInfo.get("admrem");
		    String remarks = (String)mailInfo.get("remarks");
		    if("Test".equalsIgnoreCase((String)session.getAttribute("userLoginMode")))
		    		remarks+=" - FROM TEST ENVIRONMENT";
		    final String adminid =mailer.getAdminMail(brokerBra);
		    
		    final String basePaths = request.getSession().getServletContext().getRealPath("/"+"MailServerInfo/MailServerInfo.xml");
		    
		   if(mailFrom.equalsIgnoreCase("FreightGroup")){
			   if("Admin".equalsIgnoreCase(userType.trim())||"Broker".equalsIgnoreCase(userType.trim())){
					emailSubjectTxt = "Freight Forwarded Mail - Response";
			   }else{
					emailSubjectTxt = "Freight Forwarded Mail - Request";
			   }
			   final Map freAddDetails = mailBean.getFreAddDetails(loginId, brokerBra, freDetails,mailInfo);
			   final mailController smtpMailSender = new mailController();
				smtpMailSender.postMail((String[])freAddDetails.get("emailList"),(String[])freAddDetails.get("ccAddress"),
						emailSubjectTxt, emailMsgTxt.toString(), (String)freAddDetails.get("fromName"),basePaths,brokerBra);
		   }else{
			    emailSubjectTxt = mailBean.getEmailSubjectTxt(remarks,mailFrom,emailSub,productId,quoteNo,(String)mailInfo.get("ClientName"));
			    final String mailId = (String)mailInfo.get("id");
			    String mailIds = "";
				 
				String fromName;
				if(mailFrom.equalsIgnoreCase("Admin")){
					fromName = "Quote Marine Insurance";
					mailIds = mailer.getAdminBrokerMailId(quoteNo,freDirection);
				}else{
					fromName = (String)mailInfo.get("brokerName");
				}
				final String[] emailList = mailBean.getEmailList(remarks,Freight,admrem,adminid,mailId,mailFrom,mailIds,toId);
				final String cc1Address[] = mailBean.getCcAddress(mailFrom,brokerBra,ccId,scrnFrom,loginId);
				
				if(scrnFrom.equalsIgnoreCase("viewQuoteRegister"))
				{
					System.out.println("Inside ViewQuote");
					String fileName = "quote"+quoteNo+".html";
					response.setHeader("Content-disposition",
						"attachment; filename=" +fileName);
					String content =(String)mailInfo.get("content");
					content = content.replaceAll("<html><head></head>", "<html><head></head><body onload='window.print()'>");
					/***/
					 try
					  {
						  ServletOutputStream out=response.getOutputStream();
					
					  try	
					  {
						  System.out.println("File::"+request.getRealPath("")+"/quotation.html");

						  BufferedWriter out1 = new BufferedWriter(new FileWriter(request.getRealPath("")+"/quotation.html"));
							out1.write(content.toString());
							out1.close();
							
					
							FileInputStream fis = new FileInputStream(
									request.getRealPath("")+"/quotation.html");
							byte[] buf = new byte[1024];
							int bytesRead = 0;
							while ((bytesRead = fis.read(buf)) != -1) {
								out.write(buf, 0, bytesRead);
							}
							
				          
					  }catch(Exception e)
					  {
						  e.printStackTrace();
					  }
					  finally
					  {
						  try
						  {
							  out.close();
									  
						  }catch(Exception e)
						  {
							  e.printStackTrace();
						  }
					  }
					  
					  }catch(Exception e)
					  {
						  e.printStackTrace();
					  }
					  
					/****/
					
				}
				if("0".equalsIgnoreCase(mailId))
				{
					if(!"Normal".equalsIgnoreCase(remarks))
					{
						final mailController smtpMailSender = new mailController();
						smtpMailSender.postMail( emailList,cc1Address,emailSubjectTxt, emailMsgTxt.toString(), fromName,basePaths,brokerBra);
					}
					else
					{
						LogManager.info("Message Not Sending called for quoteNo...."+quoteNo);
						request.setAttribute("MailStatus","UnknownMail");
						if(mode.equalsIgnoreCase("ClausesEdit")){
							if(true)return;
						}else{
							response.sendRedirect(path+"/Messagenotsending.jsp");
							if(true)return;
						}
					}
				}
				else
				{
					final mailController smtpMailSender = new mailController();
					smtpMailSender.postMail( emailList,cc1Address,emailSubjectTxt, emailMsgTxt.toString(), fromName,basePaths,brokerBra);
					if(mode.equalsIgnoreCase("ClausesEdit"))
					{
						request.setAttribute("MailStatus","Success");if(true)return;
					}else{
						response.sendRedirect(path+"/Message.jsp");
						if(true)return;
					}
				}
				
		   }
		}catch(Exception e){
			LogManager.debug(e);
		}
		
		LogManager.debug(EXIT);
		LogManager.popRemove();
	}
  public void processResult(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException,BaseException {
		
	try{
			String quoteno;
			final MailerBean mailer = new MailerBean();
			final HttpSession session = request.getSession(false);
			System.setProperty("java.net.preferIPv4Stack", "true");
			final Map finalInfo = (HashMap) session.getAttribute("finalInformation");
			response.setContentType("text/html");
			final StringBuffer totalquotes = new StringBuffer();
			String quotes;
			String brokerId;
			String  userCount;
			brokerId = mailer.isNull(request.getParameter("broker"),"");
			String mail;
			String count = "0";
			if (!finalInfo.isEmpty()) {
				count = (String) finalInfo.get("count");
			}
			final Map brokerInf = mailer.getFreightBrokerLoginId(brokerId);
			final String brokerName = (String) brokerInf.get("username");
			for (int i = 0; i < Integer.parseInt(count); i++) {
				quoteno = (String) finalInfo.get("qno" + i);
				totalquotes.append(quoteno);
				totalquotes.append(',');
			}
			quotes = totalquotes.toString();
			if (quotes.length() > 0) {
				quotes = quotes.substring(0, quotes.length() - 1);
			}
			final Map userInf = mailer.getFreightUserLoginId(quotes);
			userCount = (String) userInf.get("count");
			for (int i = 0; i < Integer.parseInt(count); i++) {
				quoteno = (String) finalInfo.get("qno" + i);
			}
			final StringBuffer messages = new StringBuffer(1000);
			for (int i = 0; i < Integer.parseInt(userCount); i++) {
				messages.delete(0, messages.length());
				mail = (String) userInf.get("email" + i);
				String qnoSelect[];// = new String[Integer.parseInt((String) userInf.get("countQuotesLen"))];
				qnoSelect = (String[]) userInf.get("countQuotes");
				messages.append("<html><head><body>");
				messages.append("<b>Dear Sir,</b><br><font size = 3 face = 'arial'> We are send the updated status of the Quote Information for your kind persual. Looking forward for your reply</font><br>");
				messages.append("<table width='100%'  border='1' cellspacing='1' cellpadding='0'><td bgcolor='#C0C0C0'  height='33' align='center'><b>Quote Number</td><td align='center' bgcolor='#C0C0C0'><b>Customer Name </td><td align='center' bgcolor='#C0C0C0'>" +
						"<b>Quote Status</td><td align='center' bgcolor='#C0C0C0'><b>Policy Status</b></td>");
				
				for (int q = 0; q < qnoSelect.length; q++) {
					if (userInf.get(mail+qnoSelect[q]) != null) {
						final String mailQuote = (String) userInf.get(mail + qnoSelect[q]);
						if (finalInfo.containsKey("QuoteAcc" + mailQuote)) {
							if (finalInfo.containsKey("PolAcc" + mailQuote)) {
								messages.append("<tr><td align = 'center'>");
								messages.append(mailQuote);
								messages.append("</td><td align = 'center'>");
								messages.append((finalInfo.get(CUST + mailQuote) == null ?"":(String) finalInfo.get(CUST + mailQuote)));
								messages.append("</td><td align = 'center'>Accepted</td><td align = 'center'>Yes</td></tr>");
							}
							if (finalInfo.containsKey("PolRej" + mailQuote)) {

								messages.append("<tr><td align = 'center'>");
								messages.append(mailQuote);
								messages.append("</td><td align = 'center'> ");
								messages.append((finalInfo.get(CUST + mailQuote) == null ? " ": (String) finalInfo.get(CUST + mailQuote)));
								messages.append("</td><td align = 'center'>Accepted</td><td align = 'center'>No</td></tr>");
							}

						} else if (finalInfo.containsKey("QuoteRej" + mailQuote)) {
							messages.append("<tr><td align = 'center'>");
							messages.append(mailQuote);
							messages.append("</td><td align = 'center'> ");
							messages.append((finalInfo.get(CUST + mailQuote) == null ?" ": (String) finalInfo.get(CUST + mailQuote)));
							messages.append("</td><td align = 'center'>Rejected</td><td align = 'center'>&nbsp;</td></tr>");

						}
					}
				}
				final com.maan.common.MailServerInfo mailInfo = new com.maan.common.MailServerInfo();
				final String basePaths = request.getSession().getServletContext().getRealPath("/"+"MailServerInfo/MailServerInfo.xml");
				//final HashMap PortDet = mailInfo.getMailServerInfo((String)session.getAttribute("LoginBranchCode"),basePaths);
				final HashMap PortDet = new com.maan.Home.DataManipualtion.DataSelect().getMailInfo((String)session.getAttribute("LoginBranchCode"));;
				SMTP_HOST_NAME = (String) PortDet.get("hostname");
				SMTP_AUTH_USER = (String) PortDet.get("username");
				SMTP_AUTH_PWD = (String) PortDet.get("password");
				SMTP_PORT = (String) PortDet.get("port");
				final String emailFromAddress  = (String)PortDet.get("Address");
				final boolean debug = false;
				final Properties props = new Properties();
				props.put("mail.smtp.auth", "true");
		  		  props.put("mail.smtp.starttls.enable", "true");
		  		  props.put("mail.smtp.host", SMTP_HOST_NAME);
				  props.put("mail.smtp.port", SMTP_PORT);
					final Authenticator auth = new SMTPAuthenticator();
					final Session sessionMail = Session.getInstance(props, auth);
					sessionMail.setDebug(debug);
					final Message messag = new MimeMessage(sessionMail);
					final InternetAddress addressFrom = new InternetAddress(emailFromAddress, "Quote Marine Insurance");
					messag.setFrom(addressFrom);
					messag.setRecipients(Message.RecipientType.TO,
							new InternetAddress[] { new InternetAddress(mail) });
					messag.setSubject("Freight - Quote details");

					messages.append("<br><br></table><br>Regards<br>");
					messages.append(brokerName.toUpperCase(Locale.ENGLISH));
					messag.setContent(messages.toString(), "text/html");
					if (messages.length() > 0) {
						Transport.send(messag);
					}
			}
		}catch(BaseException e){
			LogManager.debug(e);
		}catch(Exception e){
			LogManager.debug(e);
		}
		
  }  
  
  private  class SMTPAuthenticator extends javax.mail.Authenticator
  {
        public PasswordAuthentication getPasswordAuthentication()
        {
            return new PasswordAuthentication(SMTP_AUTH_USER, SMTP_AUTH_PWD);
        }
  }
  
  public void postMail(final String recipients[], final String ccreceipients1[], final String subject, final String message,
		   String broCompanyName, final String path, final String branch) throws MessagingException
 {
	LogManager.push("postMail mailcontroller method()");
	LogManager.debug(ENTER);
	try
	{
		final Message msg = commonMailSetup(broCompanyName, ccreceipients1, branch);
		final InternetAddress addressFrom = new InternetAddress(EMAIL_FROM,COMPANY_NAME);
		
		msg.setFrom(addressFrom);
		
		if((recipients != null) || (! "".equals(recipients)))
		{
			InternetAddress[] addressTo = new InternetAddress[recipients.length];
		
			for (int i = 0; i < recipients.length; i++)
			{
				LogManager.info("Madison Insurance test to mail id is..."+recipients[i]);
				addressTo[i] = new InternetAddress(recipients[i]);
			}
			for (int i = 0; i < recipients.length; i++)
			{
				addressTo[i] = new InternetAddress(recipients[i]);
				msg.addRecipient(Message.RecipientType.TO, addressTo[i]);
			}
		}
	
	if((ccreceipients != null) || (! ccreceipients.equals("")))
	{
		InternetAddress[] addressToCC = new InternetAddress[ccreceipients.length];
		{
			for(int i=0;i<ccreceipients.length;i++)
			{
				LogManager.info("Madison Insurance test ccc mail id is..."+ccreceipients[i]);
				addressToCC[i] = new InternetAddress(ccreceipients[i]);
				msg.addRecipient(Message.RecipientType.CC, addressToCC[i]);
			}
		}
	}
	LogManager.info("Madison Insurance test subject is..."+subject);
	msg.setSubject(subject);
	LogManager.info("Madison Insurance test exit1");
	msg.setContent(message, "text/html;charset=UTF-8");
	LogManager.info("Madison Insurance test exit2"+msg);
	Transport.send(msg);
	LogManager.info("Madison Insurance test exit3");
   }
	catch(Exception e){
		LogManager.debug(e);
	}
   LogManager.debug(EXIT);
	LogManager.popRemove();
 }
  
  public void postMail(final String recipients[], final String ccreceipients1[], final String subject, final String message,
		  String broCompanyName, final String path, final String branch, final File file) throws MessagingException
  {
	LogManager.push("postMail mailcontroller method()");
	LogManager.debug(ENTER);
	try
	{
		final Message msg = commonMailSetup(broCompanyName, ccreceipients1, branch);
		final InternetAddress addressFrom = new InternetAddress(EMAIL_FROM,COMPANY_NAME);
		
		msg.setFrom(addressFrom);
		
		if((recipients != null) || (! "".equals(recipients)))
		{
			InternetAddress[] addressTo = new InternetAddress[recipients.length];
		
			for (int i = 0; i < recipients.length; i++)
			{
				LogManager.info("Madison Insurance test to mail id is..."+recipients[i]);
				addressTo[i] = new InternetAddress(recipients[i]);
			}
			for (int i = 0; i < recipients.length; i++)
			{
				addressTo[i] = new InternetAddress(recipients[i]);
				msg.addRecipient(Message.RecipientType.TO, addressTo[i]);
			}
		}
	
	if((ccreceipients != null) || (! ccreceipients.equals("")))
	{
		InternetAddress[] addressToCC = new InternetAddress[ccreceipients.length];
		{
			for(int i=0;i<ccreceipients.length;i++)
			{
				LogManager.info("Madison Insurance test ccc mail id is..."+ccreceipients[i]);
				addressToCC[i] = new InternetAddress(ccreceipients[i]);
				msg.addRecipient(Message.RecipientType.CC, addressToCC[i]);
			}
		}
	}
	//msg.setRecipients(Message.RecipientType.BCC, InternetAddress.parse("maanrsa@gmail.com", false));
	LogManager.info("Madison Insurance test subject is..."+subject);
	msg.setSubject(subject);
	LogManager.info("Madison Insurance test exit1");
	MimeBodyPart body=new MimeBodyPart();
	body.setText(message);
	//body.setContent(message, "text/html");
	body.setContent(message, "text/html;charset=UTF-8");
	//body.setContent(new String(message.toString().getBytes(), "UTF-8"), "text/html; charset=\"UTF-8\"");
	MimeBodyPart attachment=new MimeBodyPart();
	DataSource source=new FileDataSource(file);
	attachment.setDataHandler(new DataHandler(source));
	attachment.setFileName(file.getName());
	Multipart multiPart=new MimeMultipart();
	multiPart.addBodyPart(body);
	multiPart.addBodyPart(attachment);
	msg.setContent(multiPart);
	Transport.send(msg);
    }
	catch(Exception e){
		LogManager.info("Exception in mailController.postMail()==>"+e);
		e.printStackTrace();
	}
	LogManager.popRemove();
  }
  public void postMail(final String recipients[], final String ccreceipients1[], final String subject, final String message,
		  String broCompanyName, final String path, final String branch, final java.util.List<File> files) throws MessagingException
		  {
	  LogManager.push("postMail mailcontroller method()");
	  LogManager.debug(ENTER);
	  try
	  {
		  final Message msg = commonMailSetup(broCompanyName, ccreceipients1, branch);
		  final InternetAddress addressFrom = new InternetAddress(EMAIL_FROM,COMPANY_NAME);
		  msg.setFrom(addressFrom);

		  if((recipients != null) || (! "".equals(recipients)))
		  {
			  InternetAddress[] addressTo = new InternetAddress[recipients.length];

			  for (int i = 0; i < recipients.length; i++)
			  {
				  LogManager.info("Madison Insurance test to mail id is..."+recipients[i]);
				  addressTo[i] = new InternetAddress(recipients[i]);
			  }
			  for (int i = 0; i < recipients.length; i++)
			  {
				  addressTo[i] = new InternetAddress(recipients[i]);
				  msg.addRecipient(Message.RecipientType.TO, addressTo[i]);
			  }
		  }

		  if((ccreceipients != null) || (! ccreceipients.equals("")))
		  {
			  InternetAddress[] addressToCC = new InternetAddress[ccreceipients.length];
			  {
				  for(int i=0;i<ccreceipients.length;i++)
				  {
					  LogManager.info("Madison Insurance test ccc mail id is..."+ccreceipients[i]);
					  addressToCC[i] = new InternetAddress(ccreceipients[i]);
					  msg.addRecipient(Message.RecipientType.CC, addressToCC[i]);
				  }
			  }
		  }
		  //msg.setRecipients(Message.RecipientType.BCC, InternetAddress.parse("maanrsa@gmail.com", false));
		  LogManager.info("Madison Insurance test subject is..."+subject);
		  msg.setSubject(subject);
		  LogManager.info("Madison Insurance test exit1");
		  MimeBodyPart body=new MimeBodyPart();
		  body.setText(message);
		  //	body.setContent(message, "text/html");
		  body.setContent(new String(message.toString().getBytes(), "iso-8859-1"), "text/html; charset=\"iso-8859-1\"");
		  Multipart multiPart=new MimeMultipart();
		  multiPart.addBodyPart(body);
		  msg.setContent(multiPart);
		  
		  for(File file : files) {
			  MimeBodyPart attachment=new MimeBodyPart();
			  DataSource source=new FileDataSource(file);
			  attachment.setDataHandler(new DataHandler(source));
			  attachment.setFileName(file.getName());
			  multiPart.addBodyPart(attachment);
		  }
		  
		  Transport.send(msg);
	  }
	  catch(Exception e){
		  LogManager.info("Exception in mailController.postMail()==>"+e);
	  }
	  LogManager.popRemove();
		  }
  
  public void postMail(final String recipients[], final String subject, final String message,String broCompanyName, final String branch,final String adminCC[]) throws MessagingException
		  {
	  LogManager.push("postMail mailcontroller method()");
	  LogManager.debug(ENTER);
	  try
	  {
		  final Message msg = commonMailSetup(broCompanyName, adminCC, branch);
		  final InternetAddress addressFrom = new InternetAddress(EMAIL_FROM,COMPANY_NAME);
		  msg.setFrom(addressFrom);

		  if((recipients != null) || (! "".equals(recipients)))
		  {
			  InternetAddress[] addressTo = new InternetAddress[recipients.length];

			  for (int i = 0; i < recipients.length; i++)
			  {
				  LogManager.info("Madison Insurance test to mail id is..."+recipients[i]);
				  addressTo[i] = new InternetAddress(recipients[i]);
			  }
			  for (int i = 0; i < recipients.length; i++)
			  {
				  addressTo[i] = new InternetAddress(recipients[i]);
				  msg.addRecipient(Message.RecipientType.TO, addressTo[i]);
			  }
		  }
		  
		  //Admin CC Receipants
		  if((ccreceipients != null) || (! ccreceipients.equals("")))
		  {
			  InternetAddress[] addressToCC = new InternetAddress[ccreceipients.length];
			  {
				  for(int i=0;i<ccreceipients.length;i++)
				  {
					  LogManager.info("Madison Insurance test ccc mail id is..."+ccreceipients[i]);
					  addressToCC[i] = new InternetAddress(ccreceipients[i]);
					  msg.addRecipient(Message.RecipientType.CC, addressToCC[i]);
				  }
			  }
		  }
		  LogManager.info("Madison Insurance test subject is..."+subject);
		  msg.setSubject(subject);
		  LogManager.info("Madison Insurance test exit1");
		  msg.setContent(message, "text/html;charset=UTF-8");
		  LogManager.info("Madison Insurance test exit2"+message);
		  Transport.send(msg);
		  LogManager.info("Madison Insurance test exit3");
	  }
	  catch(Exception e){
		  e.printStackTrace();
		  LogManager.debug(e);
	  }
	  LogManager.debug(EXIT);
	  LogManager.popRemove();
  }
  
  public void postMail(final String recipients[],final String subject, final String message,String broCompanyName, final String path, final String branch, final File file) throws MessagingException
  {
	LogManager.push("postMail mailcontroller method()");
	LogManager.debug(ENTER);
	try
	{
		  final Message msg = commonMailSetup(broCompanyName, "".split(","), branch);
		  final InternetAddress addressFrom = new InternetAddress(EMAIL_FROM,COMPANY_NAME);
		  msg.setFrom(addressFrom);
		
		if((recipients != null) || (! "".equals(recipients)))
		{
			InternetAddress[] addressTo = new InternetAddress[recipients.length];
		
			for (int i = 0; i < recipients.length; i++)
			{
				LogManager.info("Madison Insurance test to mail id is..."+recipients[i]);
				addressTo[i] = new InternetAddress(recipients[i]);
			}
			for (int i = 0; i < recipients.length; i++)
			{
				addressTo[i] = new InternetAddress(recipients[i]);
				msg.addRecipient(Message.RecipientType.TO, addressTo[i]);
			}
		}
	
	if((ccreceipients != null) || (! ccreceipients.equals("")))
	{
		InternetAddress[] addressToCC = new InternetAddress[ccreceipients.length];
		{
			for(int i=0;i<ccreceipients.length;i++)
			{
				LogManager.info("Madison Insurance test ccc mail id is..."+ccreceipients[i]);
				addressToCC[i] = new InternetAddress(ccreceipients[i]);
				msg.addRecipient(Message.RecipientType.CC, addressToCC[i]);
			}
		}
	}
	LogManager.info("Madison Insurance test subject is..."+subject);
	msg.setSubject(subject);
	LogManager.info("Madison Insurance test exit1");
	MimeBodyPart body=new MimeBodyPart();
	body.setText(message);
	body.setContent(new String(message.toString().getBytes(), "iso-8859-1"), "text/html; charset=\"iso-8859-1\"");
	MimeBodyPart attachment=new MimeBodyPart();
	DataSource source=new FileDataSource(file);
	attachment.setDataHandler(new DataHandler(source));
	attachment.setFileName(file.getName());
	Multipart multiPart=new MimeMultipart();
	multiPart.addBodyPart(body);
	multiPart.addBodyPart(attachment);
	msg.setContent(multiPart);
	Transport.send(msg);
    }
	catch(Exception e){
		LogManager.info("Exception in mailController.postMail()==>"+e);
	}
	LogManager.popRemove();
  }
  public void postMail1(final String recipients[],final String ccreceipients[],final String subject, final String message,String broCompanyName, final String path, final String branch, final File file) throws MessagingException
  {
	LogManager.push("postMail mailcontroller method()");
	LogManager.debug(ENTER);
	try
	{
		  final Message msg = commonMailSetup(broCompanyName, "".split(","), branch);
		  final InternetAddress addressFrom = new InternetAddress(EMAIL_FROM,COMPANY_NAME);
		  msg.setFrom(addressFrom);
		
		if((recipients != null) || (! "".equals(recipients)))
		{
			InternetAddress[] addressTo = new InternetAddress[recipients.length];
		
			for (int i = 0; i < recipients.length; i++)
			{
				LogManager.info("Madison Insurance test to mail id is..."+recipients[i]);
				addressTo[i] = new InternetAddress(recipients[i]);
			}
			for (int i = 0; i < recipients.length; i++)
			{
				addressTo[i] = new InternetAddress(recipients[i]);
				msg.addRecipient(Message.RecipientType.TO, addressTo[i]);
			}
		}
	
	if((ccreceipients != null) || (! ccreceipients.equals("")))
	{
		InternetAddress[] addressToCC = new InternetAddress[ccreceipients.length];
		{
			for(int i=0;i<ccreceipients.length;i++)
			{
				LogManager.info("Madison Insurance test ccc mail id is..."+ccreceipients[i]);
				addressToCC[i] = new InternetAddress(ccreceipients[i]);
				msg.addRecipient(Message.RecipientType.CC, addressToCC[i]);
			}
		}
	}
	LogManager.info("Madison Insurance test subject is..."+subject);
	msg.setSubject(subject);
	LogManager.info("Madison Insurance test exit1");
	MimeBodyPart body=new MimeBodyPart();
	body.setText(message);
	body.setContent(new String(message.toString().getBytes(), "iso-8859-1"), "text/html; charset=\"iso-8859-1\"");
	MimeBodyPart attachment=new MimeBodyPart();
	DataSource source=new FileDataSource(file);
	attachment.setDataHandler(new DataHandler(source));
	attachment.setFileName(file.getName());
	Multipart multiPart=new MimeMultipart();
	multiPart.addBodyPart(body);
	multiPart.addBodyPart(attachment);
	msg.setContent(multiPart);
	Transport.send(msg);
    }
	catch(Exception e){
		LogManager.info("Exception in mailController.postMail()==>"+e);
	}
	LogManager.popRemove();
  }
  public static String[] combineString(String[] first, String[] second){
	  
      int length = first.length + second.length;
      String[] result = new String[length];
      System.arraycopy(first, 0, result, 0, first.length);
      System.arraycopy(second, 0, result, first.length, second.length);
      try{
    	  List<String> list = new ArrayList<String>();
	      for (String text : result){
	          if (text != null && text.length() > 0){
	              list.add(text);
	          }
	      }
	      result = list.toArray(new String[0]);
		  }catch (Exception e) {
			e.printStackTrace();
		}
      return result;
  }
  
  private Message commonMailSetup(String broCompanyName,String[] ccreceipients1, String branch){
	  Message msg=null;
	  try{
		  final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
			//final com.maan.common.MailServerInfo mailInfo = new com.maan.common.MailServerInfo();
			//final HashMap PortDet = mailInfo.getMailServerInfo(branch,path);
			final HashMap PortDet = new com.maan.Home.DataManipualtion.DataSelect().getMailInfo(branch);
			 System.setProperty("java.net.preferIPv4Stack", "true");
			SMTP_HOST_NAME = (String) PortDet.get("hostname");
			SMTP_AUTH_USER = (String) PortDet.get("username");
			SMTP_AUTH_PWD =  PortDet.get("password")==null?"":(String)PortDet.get("password");
			SMTP_PORT = (String) PortDet.get("port");
			if(StringUtils.isBlank(broCompanyName)){
				 broCompanyName = (String) PortDet.get("companyName"); 
			  }
			COMPANY_NAME= broCompanyName;
			EMAIL_FROM  = (String)PortDet.get("Address");
			final String[] ccreceipients2 = PortDet.get("MAIL_CC")==null?(new String[]{}):PortDet.get("MAIL_CC").toString().split(",");
			ccreceipients =combineString(ccreceipients1,ccreceipients2);
			final boolean debug = false;
			final Properties props = new Properties();
			
			props.put("mail.smtp.auth", "true");
			//prop.put("mail.transport.protocol", "smtps");
			props.put("mail.smtp.starttls.enable", "true");
			//prop.put("mail.smtp.ssl.enable", "true");	
			props.put("mail.smtp.host", SMTP_HOST_NAME);
			props.put("mail.smtp.port", SMTP_PORT);	
			//prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory"); 
			
			/*props.put("mail.smtp.auth", "true");
			props.put("mail.transport.protocol", "smtps");
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.ssl.enable", "true");
			props.put("mail.smtp.host", SMTP_HOST_NAME);
			props.put("mail.smtp.port", SMTP_PORT);*/
			final Authenticator auth = new SMTPAuthenticator();
			final Session session = Session.getInstance(props, auth);
			session.setDebug(debug);
			msg = new MimeMessage(session);
			
	  }catch (Exception e) {
		 LogManager.debug(e);
	}
	return msg;
	  
  }
} 