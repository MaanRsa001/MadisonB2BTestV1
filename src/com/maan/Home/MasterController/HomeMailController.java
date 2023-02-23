package com.maan.Home.MasterController;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.StringTokenizer;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
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
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.maan.DBCon.DBConnectionStatus;
import com.maan.Health.controller.HealthBean;
import com.maan.Home.DataManipualtion.HomePdfDataSelect;
import com.maan.common.LogManager;
import com.maan.common.exception.BaseException;

public class HomeMailController extends HttpServlet {
	/**
	 * Author : Rajesh R
	 */
	private static final long serialVersionUID = 1L;
	final static transient private String BREAK="<br>";
	final static transient private String CONTENT="text/html";
	final static transient private String SPACE = "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
	final static transient private String COVERSUM = "covtra.product_suminsured";
	final static transient private String ADMIN = "Admin";
	final static transient private String FONT = "</font>";
	final static transient private String STYLE= "<td align='left'  width='5%'><font style='font-family: Arial, Helvetica, sans-serif' size = 2><b>Yes</b></font></td>";
	final static transient private String QUOTENO = "QuoteNo";
	final static transient private String SCHEDULE = "Schedule";
	final static transient private String pdf = ".pdf";
	final static transient private String EMAILLIST = "emailList";
	final static transient private String CCADDRESS = "cc1Address";
	final static transient private String EMAILID = "emailId";
	final static transient private String SUBJ = "subj";

	private transient String smtpAuthUser = "";
	private transient String smtpAuthPwd = "";

	public void init(final ServletConfig config) throws ServletException {
		LogManager.info("MasterController");
	}
	public void doPost(final HttpServletRequest request, final HttpServletResponse response)
			throws ServletException, IOException {
		try{
			processResult(request, response);
		} catch(BaseException e) {
			LogManager.debug(e);
		}
	}

	public void doGet(final HttpServletRequest request, final HttpServletResponse response)
			throws ServletException, IOException {
		try{
			processResult(request, response);
		} catch(BaseException e) {
			LogManager.debug(e);
		}
	}
	public String isNull(final String content)throws BaseException{
		final StringBuffer contents = new StringBuffer();
		
		if(content==null||content.equalsIgnoreCase("null")){
			contents.append("");
		}
		else{
			contents.append(content);
		}
		return contents.toString();
	}
	public String isNullBr(final String content)throws BaseException{
		final StringBuffer contents = new StringBuffer();
		if(content==null||content.equalsIgnoreCase("null")||content.equalsIgnoreCase("select")){
			contents.append("");
		}
		else{
			contents.append(content);
			contents.append(BREAK);
		}
		return contents.toString();
	}
	public String isNull(final String content,final String value)throws BaseException{
		final StringBuffer contents = new StringBuffer();
		if(content==null){
			contents.append(value);
		}
		else{
			contents.append(content);
		}
		return contents.toString();
	}
	public String isNullOrSelect(final String content,final String value)throws BaseException{
		final StringBuffer contents = new StringBuffer();
		if(content==null||content.equalsIgnoreCase(value)){
			contents.append("");
		}
		else{
			contents.append(content);
		}
		return contents.toString();
	}
	public String getTravelMailContent(final HttpServletRequest request, final HttpServletResponse response,
			final String quoteNo,final String loginId,final String branch,final String reqFrom,
			final String adminRemarks,final String fullAddr)throws ServletException, IOException,BaseException	{
		String proId = "";
		HttpSession session = request.getSession(true);
		String path = request.getContextPath();
		StringBuffer fullName = new StringBuffer();
		StringBuffer broker = new StringBuffer();
		String rels ="";
		
		String[] names = {};
		String[] dobs   = {};
		String[] rel   = {};
		int[]    age   = {};
		String[] nationalities   = {};
		String[] genders   = {};
		final com.maan.Home.DataManipualtion.DataSelect cover = new com.maan.Home.DataManipualtion.DataSelect();
		final String referralName = cover.getReferralNames(quoteNo);
		
		Calendar calendar = Calendar.getInstance();
		String sysDay=""+calendar.get(java.util.Calendar.DATE);
		String sysMonth=""+(calendar.get(java.util.Calendar.MONTH)+1);
		String sysYear=""+calendar.get(java.util.Calendar.YEAR);
		com.maan.Travel.DAO.TravelBean travelMail= new com.maan.Travel.DAO.TravelBean();
		

		HashMap travelMailDetails = new HashMap();
		HashMap ht = new HashMap();
		travelMailDetails = travelMail.getTravelMailDetails(quoteNo,loginId);
		ht = travelMail.getDetailsForPremiumCalculation(quoteNo);
		if(ht != null && ht.size() > 0)
		{
			names        = (String[])ht.get("names");
			rel          = (String[])ht.get("rel");
			nationalities  = (String[])ht.get("nationalities");
			genders      = (String[])ht.get("genders");
			age          = (int[])ht.get("age");
			dobs          = (String[])ht.get("dob");
		}
		if(travelMailDetails.size()>0)
		{
			final String fName = isNull((String)travelMailDetails.get("per.first_name"));
			final String cName = isNull((String)travelMailDetails.get("per.companyName"));
			broker.append(isNull((String)travelMailDetails.get("bro.COMPANY_NAME")));
			if (fName.length() > 0){
				fullName.append(isNullOrSelect((String)travelMailDetails.get("per.title"),"select"));
				fullName.append("&nbsp;");
				fullName.append(fName);
			}
			else if (cName.length() > 0){
				fullName.append(isNullOrSelect((String)travelMailDetails.get("per.title"),"select"));
				fullName.append("&nbsp;");
				fullName.append(cName);
			}
			proId = travelMailDetails.get("pos.proposal")==null?"":((String)travelMailDetails.get("pos.proposal"));
		}
		String proName = travelMail.getProductandCoverName(proId,(String)session.getAttribute("LoginBranchCode"));
		String proType = travelMail.getProductNamePDF((String)travelMailDetails.get("pos.PRODUCT_ID"));
		proName = (proName==null || proName.equalsIgnoreCase("null"))?"":proName;
		final StringBuffer mailDatas = new StringBuffer(10000);
			
			mailDatas.append("<html>");
			mailDatas.append("<head></head>");
			mailDatas.append("<link rel=\"stylesheet\" href= \""+path+"\\css\\rsa.css\" type=\"text/css\">");
			mailDatas.append("<body>");
			mailDatas.append("<form name='summary' method='post'>");
			mailDatas.append("<table border='0' cellpadding='3' style='border-collapse:collapse' width='90%' align='center'>");
			mailDatas.append("<tr ><td><font style='font-family: Arial, Helvetica, sans-serif' size = 3><u><center>"+proName+"</center>");
			mailDatas.append("</font></b></u></td></tr></table>");
			mailDatas.append("<table border='0' cellpadding='3' style='border-collapse:collapse' width='90%' align='center'>");
			mailDatas.append("<tr >");
			mailDatas.append("</tr>");
			mailDatas.append("<br>");
			mailDatas.append("<tr class=\"blueborder\">");
			mailDatas.append("<td class='heading' colspan='6' ><b><font style='font-family: Arial, Helvetica, sans-serif' size = 3><u>Proposer 's Information</font></b></u></td>");
			mailDatas.append("</tr>");
			mailDatas.append("<tr>");
			mailDatas.append("<td><b><font style='font-family: Arial, Helvetica, sans-serif' size = 2>Proposer Name</b></td><td ><font style='font-family: Arial, Helvetica, sans-serif' size = 2>"+fullName.toString()+"</font></td>");
			mailDatas.append("<td><b><font style='font-family: Arial, Helvetica, sans-serif' size = 2>Occupation</b></td><td><font style='font-family: Arial, Helvetica, sans-serif' size = 2>"+isNull((String)travelMailDetails.get("per.occupation"),"select")+"</font></td>");
			mailDatas.append("</tr>");
			mailDatas.append("<tr>");
			mailDatas.append("<td><b><font style='font-family: Arial, Helvetica, sans-serif' size = 2>Nationality</B></td><td><font style='font-family: Arial, Helvetica, sans-serif' size = 2>"+isNull((String)travelMailDetails.get("per.nationality"),"Select")+"</font></td>");
			mailDatas.append("<td><b><font style='font-family: Arial, Helvetica, sans-serif' size = 2>DOB</b></td><td><font style='font-family: Arial, Helvetica, sans-serif' size = 2>"+isNull((String)travelMailDetails.get("per.dob"),"select")+"</font></td>");
			mailDatas.append("</tr>");
			mailDatas.append("<tr>");
			mailDatas.append("<td><font style='font-family: Arial, Helvetica, sans-serif' size = 2><b>P.O. Box</b></td><td><font style='font-family: Arial, Helvetica, sans-serif' size = 2>"+isNull((String)travelMailDetails.get("per.pobox"))+"</font></td>");
			mailDatas.append("<td><font style='font-family: Arial, Helvetica, sans-serif' size = 2><b>Emirate</b></td><td><font style='font-family: Arial, Helvetica, sans-serif' size = 2>"+isNull((String)travelMailDetails.get("per.emirate"),"select")+"</font></td>");
			mailDatas.append("</tr>");
			mailDatas.append("<tr>");
			mailDatas.append("<td><b><font style='font-family: Arial, Helvetica, sans-serif' size = 2>Telephone</b></td><td><font style='font-family: Arial, Helvetica, sans-serif' size = 2>"+isNull((String)travelMailDetails.get("per.telephone"))+"</font></td>");
			mailDatas.append("<td><b><font style='font-family: Arial, Helvetica, sans-serif' size = 2>Mobile</b></td><td><font style='font-family: Arial, Helvetica, sans-serif' size = 2>"+isNull((String)travelMailDetails.get("per.mobile"))+"</font></td>");
			mailDatas.append("</tr>");
			mailDatas.append("<tr> ");
			mailDatas.append("<td><b><font style='font-family: Arial, Helvetica, sans-serif' size = 2>Fax</b></td><td><font style='font-family: Arial, Helvetica, sans-serif' size = 2>"+isNull((String)travelMailDetails.get("per.fax"))+"</font></td>");
			mailDatas.append("<td><b><font style='font-family: Arial, Helvetica, sans-serif' size = 2>E-Mail Id</b></td><td><font style='font-family: Arial, Helvetica, sans-serif' size = 2>"+isNull((String)travelMailDetails.get("per.email"))+"</font></td>");
			mailDatas.append("</tr><tr>");
			mailDatas.append("<td><b><font style='font-family: Arial, Helvetica, sans-serif' size = 2>Address</b></td><td><font style='font-family: Arial, Helvetica, sans-serif' size = 2><font style='font-family: Arial, Helvetica, sans-serif' size = 2>"+isNull((String)travelMailDetails.get("per.address1"))+"</font></td>");
			mailDatas.append("</tr>");
			mailDatas.append("<tr><td colspan='6'><b><font style='font-family: Arial, Helvetica, sans-serif' size = 3><u>Traveler's Information</u></b></td></tr></table>");
			mailDatas.append("<table border='0' cellspacing='0' width='95%' class='data-table-border' cellpadding='4' align='center'>");
			mailDatas.append("<tr class='heading'><td width='30%' align='center' class='heading'><b>Name</b></td>");
		    mailDatas.append("<td width='30%' align='center' class='heading'><b>Date&nbsp;of&nbsp;Birth</b></td>");
			mailDatas.append("<td width='5%' align='center' class='heading'><b>Age</b></td>");
			mailDatas.append("<td width='10%' align='center' class='heading'><b>Gender</b></td>");
			mailDatas.append("<td width='15%' align='center' class='heading'><b>Relation</b></td>");
			mailDatas.append("<td width='25%' align='center' class='heading'><b>Nationality</b></td></tr>");
			for(int i=0;i<names.length;i++)
			{
				mailDatas.append("<tr><td align='center'><font style='font-family: Arial, Helvetica, sans-serif' size = 2>&nbsp;&nbsp;"+names[i]+"</font></td><td><font style='font-family: Arial, Helvetica, sans-serif' size = 2><center>"+dobs[i]+"</font></center></td>");
				    mailDatas.append("<td><center>&nbsp;&nbsp;<font style='font-family: Arial, Helvetica, sans-serif' size = 2>"+age[i]+"</font></center></td>");
				if(rel[i].equalsIgnoreCase("1"))
					rels = "Self";
				else if(rel[i].equalsIgnoreCase("2"))
					rels = "Spouse";
				else if(rel[i].equalsIgnoreCase("3"))
					rels = "Child";
				else if(rel[i].equalsIgnoreCase("4"))
					rels = "Father";
				else if(rel[i].equalsIgnoreCase("5"))
					rels = "Mother";
				else if(rel[i].equalsIgnoreCase("6"))
					rels = "Other";
				String gen="";
		        if(genders[i] != null && genders[i].equalsIgnoreCase("M")) 
					gen = "Male";
		        else if(genders[i] != null && genders[i].equalsIgnoreCase("F")) 
					gen = "Female";
				mailDatas.append("<td><center>&nbsp;&nbsp;<font style='font-family: Arial, Helvetica, sans-serif' size = 2>"+gen+"</font></center></td>");
				mailDatas.append("<td><center>&nbsp;&nbsp;<font style='font-family: Arial, Helvetica, sans-serif' size = 2>"+rels+"</font></center></td>");
		        nationalities[i] = (nationalities[i] == null || nationalities[i].equalsIgnoreCase("null"))?"":nationalities[i];
				mailDatas.append("<td><center>&nbsp;&nbsp;<font style='font-family: Arial, Helvetica, sans-serif' size = 2>"+nationalities[i]+"</font></center></td></tr>");
			}
			String[][] coverDuration = new String[0][0];
			coverDuration = travelMail.getCoverDuration(quoteNo);
			mailDatas.append("<br>");
			mailDatas.append("<tr><td></td><td></td><td></td><td></td></tr>");
			//mailDatas.append("<tr><td></td><td></td><td></td><td></td></tr>");
			mailDatas.append("<tr class='blueborder'>");
			mailDatas.append("<td class='heading' colspan='6' ><b><font style='font-family: Arial, Helvetica, sans-serif' size = 3><u>Quote Information</font></b></u></td>");
			mailDatas.append("</tr> </table>");
			mailDatas.append("<table class='data-table-border' border='0' cellpadding='4' cellspacing='0' width='95%' align='center'>");
		    mailDatas.append("<tr><td><b><font style='font-family: Arial, Helvetica, sans-serif' size = 2>Quote No</b></td><td><font style='font-family: Arial, Helvetica, sans-serif' size = 2>"+quoteNo+" </td>");
			mailDatas.append("<td><b><font style='font-family: Arial, Helvetica, sans-serif' size = 2>Quoted&nbsp;Date</b></td><td ><font style='font-family: Arial, Helvetica, sans-serif' size = 2>"+coverDuration[0][4]+"</td>");
			mailDatas.append("<td><b><font style='font-family: Arial, Helvetica, sans-serif' size = 2>PRODUCT&nbsp;TYPES</font></b></td> <td><font style='font-family: Arial, Helvetica, sans-serif' size = 2>&nbsp;</font></td><td>"+proType+"</td></tr><tr>");
			
		    if(coverDuration.length >0)
			{
				mailDatas.append("<td><b><font style='font-family: Arial, Helvetica, sans-serif' size = 2>Cover Period</b></td><td><font style='font-family: Arial, Helvetica, sans-serif' size = 2>"+coverDuration[0][3]+" days</td>");
				mailDatas.append("<td class='runtext-white'><b>Effective&nbsp;Date</sb></td>");
				mailDatas.append("<td class='runtext-white'>"+coverDuration[0][1]+"</td>");
				mailDatas.append("<td class='runtext-white'><b>&nbsp;Expiry&nbsp;Date </b></td>");
				mailDatas.append("<td class='runtext-white'>"+coverDuration[0][2]+"</td>");	
			}
		    
			mailDatas.append("</tr>");
			String excessandSign[][] = new String[0][0];
			double totPre = 0.0;
			String preOriginal ="";
			String exPre ="";
			String sign ="";
		
			if(travelMailDetails.size()>0)
			{
				preOriginal =(travelMailDetails.get("pos.PREMIUM")==null?"":(String)travelMailDetails.get("pos.PREMIUM"));
				
				/*excessandSign=(ht.get("PremiumDetails1")==null?(new String[0][0]):(String[][])ht.get("PremiumDetails1"));
				if(excessandSign.length>0)
				{
					exPre = excessandSign[0][0]!=null?excessandSign[0][1]:"";
					sign = excessandSign[0][3]!=null?excessandSign[0][3]:"";
				}
				if((!preOriginal.equals("")&&preOriginal.length()>0) && (!exPre.equals("")&&exPre.length()>0))
				{
					if(sign.equalsIgnoreCase("+"))
						totPre = Double.parseDouble(preOriginal) + Double.parseDouble(exPre);
					else if(sign.equalsIgnoreCase("-"))
						totPre = Double.parseDouble(preOriginal) - Double.parseDouble(exPre);
					else
						totPre = Double.parseDouble(preOriginal) + Double.parseDouble(exPre);
				}
				else if(!preOriginal.equals("") && preOriginal.length()>0 && totPre==0)*/
					totPre = Double.parseDouble(preOriginal);	
			}
			else
			{
			 	mailDatas.append("<tr>");
				mailDatas.append("<td><b><font style='font-family: Arial, Helvetica, sans-serif' size = 2>Product</b></td><td ></td>");
				mailDatas.append("<td><b><font style='font-family: Arial, Helvetica, sans-serif' size = 2>Premium</b></td><td ></td>");
				mailDatas.append("</tr><tr>");
				mailDatas.append("<td><b><font style='font-family: Arial, Helvetica, sans-serif' size = 2>Policy Start Date </b></td><td ></td>");
				mailDatas.append("<td><b><font style='font-family: Arial, Helvetica, sans-serif' size = 2>Policy End Date</b></td><td ></td></tr>");
			 }
		
			mailDatas.append("</tr>");
			mailDatas.append("<tr class='blueborder'>"); 
			mailDatas.append("<td class='heading' colspan='6' height='2'></td>");
			mailDatas.append("</tr>");
			mailDatas.append("</table>");
			mailDatas.append("<font style='font-family: Arial, Helvetica, sans-serif' size = 3><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<u>Reason For Referral</u></b></font>");
			StringTokenizer st = new StringTokenizer(referralName,"*");
			String temp="";
			int j=0;
			while(st.hasMoreTokens())
			{
				temp = temp.trim()+"<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font size=2 color='red' align='left'>"+(++j)+".&nbsp;"+st.nextToken()+"</font>";
			}
			temp = temp.trim()+"<br/><br/>";
			mailDatas.append("<font style='font-family: Arial, Helvetica, sans-serif' size = 2>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+temp+"</font>");
				 mailDatas.append("</div></td></tr>");
				 mailDatas.append("</table>");
				 mailDatas.append("<td width='20%'>&nbsp;</td>");
				 mailDatas.append("</tr></table> </table>");
			String[][] coverDetails = new String[0][0];
			
			coverDetails = travelMail.getCoverageValues(quoteNo);
			
			String schemeName="",optionName="",schemeCover=(String)ht.get("scheme_cover");
			int index=schemeCover.indexOf("_");
			
			if(index!=-1)
			{
				schemeName=schemeCover.substring(0, index);
				optionName=schemeCover.substring(index+1, schemeCover.length()).replaceAll("_", " ");
				optionName="-"+optionName;
			}
			else
			{
				schemeName=schemeCover;
				optionName="";
			}
			
			HashMap travelCovers = new HashMap();
			
			//travelCovers = travelMail.getTravelCovers(quoteNo);
			
			travelCovers = travelMail.getTravelCoveragesList((String)ht.get("scheme_id"),(String)ht.get("option_id"),branch,proId); 
			
			System.out.println("travelMailDetails.get('pos.PRODUCT_ID'): "+travelMailDetails.get("pos.PRODUCT_ID"));
			String travelOptionStatus = travelMail.getTravelOptionStatus((String)travelMailDetails.get("pos.PRODUCT_ID"));
			LogManager.info("royal test travelOptionStatus..."+travelOptionStatus+" coverDetails.length: "+coverDetails.length);
			mailDatas.append("<table border='0' cellpadding='3' cellspacing='1' style='border-collapse:collapse' width='90%' align='center'>");
			mailDatas.append("<tr><td align='left' colspan='8' ><font style='font-family: Arial, Helvetica, sans-serif' size = 2>Scheme Covers : </font>&nbsp;");
			mailDatas.append("<font style='font-family: Arial, Helvetica, sans-serif' size = 2><b>"+schemeName+optionName+"</b></font></td></tr>");
			for(int i=0;i<coverDetails.length;i++)
			{
				mailDatas.append("<tr class='blueborder'> ");
				mailDatas.append("<td class='heading' colspan='8' ><b><font style='font-family: Arial, Helvetica, sans-serif' size = 3><u>Cover Details</u></b></font></td>");
				mailDatas.append("</tr>");
				if(travelCovers.size()>0)
				{
					if(((String)travelCovers.get("CoverId"+i)).equalsIgnoreCase((coverDetails[i][1]!=null?coverDetails[i][1]:"")))
					{
						mailDatas.append("<tr><td align='left'  width='40%' colspan='2' ><font style='font-family: Arial, Helvetica, sans-serif' size = 2>"+(travelCovers.get("Coveres"+i)).toString()+" :</font></td>");
						if((coverDetails[i][0]!=null?coverDetails[i][0]:"").equalsIgnoreCase("Y"))
						{
							mailDatas.append("<td align='left'  width='5%'><font style='font-family: Arial, Helvetica, sans-serif' size = 2><b>YES</b></font></td>");
						}
						else
						{
							mailDatas.append("<td align='left'  width='5%'><font style='font-family: Arial, Helvetica, sans-serif' size = 2><b>NO</b></font></td>");
						}
						mailDatas.append("</tr>");
					}
				}
			}// CoverDetails
			 if(ADMIN.equalsIgnoreCase(reqFrom)){
				 HashMap brokerDetails = (HashMap) session.getAttribute("BrokerDetails");
				 String currencyType = "";
				 if (brokerDetails.size() > 0) {
					currencyType = (String) brokerDetails.get("CurrencyAbb");
				 }
				 mailDatas.append("<tr class='blueborder'> ");
				 mailDatas.append("<td class='heading' colspan='8' ><b><font style='font-family: Arial, Helvetica, sans-serif' size = 3><u>Premium</u></b></font></td>");
				 mailDatas.append("</tr>");
				 mailDatas.append("<tr><td align='left'  width='40%' colspan='2' ><font style='font-family: Arial, Helvetica, sans-serif' size = 2>Premium</font></td>");
				 mailDatas.append("<td align='left'  width='5%' ><font style='font-family: Arial, Helvetica, sans-serif' size = 2><b>"+currencyType+" "+totPre+"</b></font></td></tr>");
			 }
			mailDatas.append("<tr class='blueborder'><td class='heading' colspan='8' height='2'></td></tr></table>");
			
			if (adminRemarks.trim().length() > 0&&ADMIN.equalsIgnoreCase(reqFrom)) {
				mailDatas.append("<br><br><table><tr><td><font style='font-family: Arial, Helvetica, sans-serif' size = 3><b><u>Admin Referral Remarks<u>" +
						"</b></font></td><tr></tr><td><font style='font-family: Arial, Helvetica, sans-serif' size = 2>");
				mailDatas.append(adminRemarks);
				mailDatas.append("</font></td></tr></table>");
				if(broker.length()>0){
					broker.delete(0, broker.length());
				}
				broker.append(fullAddr);
			}
			else if(ADMIN.equalsIgnoreCase(reqFrom)){
				if(broker.length()>0){
					broker.delete(0, broker.length());
				}
				broker.append(fullAddr);
			}
	   		 mailDatas.append("</table>");
     		 mailDatas.append("<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font style='font-family: Arial, Helvetica, sans-serif' size = 3><b>Regards,</font>");
			 mailDatas.append("<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font style='font-family: Arial, Helvetica, sans-serif' size = 3>"+broker.toString()+"</font>");
			 mailDatas.append("<br><br>");
			 mailDatas.append("</form>");
			 mailDatas.append("</html>");
			
		return mailDatas.toString();
	}
	
	public String getHealthMailContent(final HttpServletRequest request, final HttpServletResponse response,
			final String quoteNo,final String loginId,final String branch,final String reqFrom,
			final String adminRemarks,final String fullAddr)throws ServletException, IOException,BaseException	{
		String proId = "";
		HttpSession session = request.getSession(true);
		String path = request.getContextPath();
		StringBuffer fullName = new StringBuffer();
		StringBuffer broker = new StringBuffer();
		String rels ="";
		
		/*String[] names = {};
		String[] dobs   = {};
		String[] rel   = {};
		int[]    age   = {};
		String[] nationalities   = {};
		String[] genders   = {};*/
		final com.maan.Home.DataManipualtion.DataSelect cover = new com.maan.Home.DataManipualtion.DataSelect();
		final String referralName = cover.getReferralNames(quoteNo);
		
		Calendar calendar = Calendar.getInstance();
		String sysDay=""+calendar.get(java.util.Calendar.DATE);
		String sysMonth=""+(calendar.get(java.util.Calendar.MONTH)+1);
		String sysYear=""+calendar.get(java.util.Calendar.YEAR);
		com.maan.Travel.DAO.TravelBean travelMail= new com.maan.Travel.DAO.TravelBean();
		HashMap travelMailDetails = new HashMap();
		HealthBean hb=new HealthBean();
		hb.setApplicationNo(cover.getApplicaitonNo(quoteNo));
		hb.setLoginId("maanbroker1");
		new com.maan.Health.DAO.HealthDAO().getSecondPageDts(hb);
		//HashMap ht = new HashMap();
		travelMailDetails = travelMail.getTravelMailDetails(quoteNo,loginId);
		/*ht = travelMail.getDetailsForPremiumCalculation(quoteNo);
		if(ht != null && ht.size() > 0)
		{
			names        = (String[])ht.get("names");
			rel          = (String[])ht.get("rel");
			nationalities  = (String[])ht.get("nationalities");
			genders      = (String[])ht.get("genders");
			age          = (int[])ht.get("age");
			dobs          = (String[])ht.get("dob");
		}*/
		if(travelMailDetails.size()>0)
		{
			final String fName = isNull((String)travelMailDetails.get("per.first_name"));
			final String cName = isNull((String)travelMailDetails.get("per.companyName"));
			broker.append(isNull((String)travelMailDetails.get("bro.COMPANY_NAME")));
			if (fName.length() > 0){
				fullName.append(isNullOrSelect((String)travelMailDetails.get("per.title"),"select"));
				fullName.append("&nbsp;");
				fullName.append(fName);
			}
			else if (cName.length() > 0){
				fullName.append(isNullOrSelect((String)travelMailDetails.get("per.title"),"select"));
				fullName.append("&nbsp;");
				fullName.append(cName);
			}
			proId = travelMailDetails.get("pos.proposal")==null?"":((String)travelMailDetails.get("pos.proposal"));
		}
		String proName = travelMail.getProductandCoverName(proId,(String)session.getAttribute("LoginBranchCode"));
		String proType = travelMail.getProductNamePDF((String)travelMailDetails.get("pos.PRODUCT_ID"));
		proName = (proName==null || proName.equalsIgnoreCase("null"))?"":proName;
		final StringBuffer mailDatas = new StringBuffer(10000);
			
			mailDatas.append("<html>");
			mailDatas.append("<head></head>");
			mailDatas.append("<link rel=\"stylesheet\" href= \""+path+"\\css\\rsa.css\" type=\"text/css\">");
			mailDatas.append("<body>");
			mailDatas.append("<form name='summary' method='post'>");
			mailDatas.append("<table border='0' cellpadding='3' style='border-collapse:collapse' width='90%' align='center'>");
			mailDatas.append("<tr ><td><font style='font-family: Arial, Helvetica, sans-serif' size = 3><u><center>"+proName+"</center>");
			mailDatas.append("</font></b></u></td></tr></table>");
			mailDatas.append("<table border='0' cellpadding='3' style='border-collapse:collapse' width='90%' align='center'>");
			mailDatas.append("<tr >");
			mailDatas.append("</tr>");
			mailDatas.append("<br>");
			mailDatas.append("<tr class=\"blueborder\">");
			mailDatas.append("<td class='heading' colspan='6' ><b><font style='font-family: Arial, Helvetica, sans-serif' size = 3><u>Proposer 's Information</font></b></u></td>");
			mailDatas.append("</tr>");
			mailDatas.append("<tr>");
			mailDatas.append("<td><b><font style='font-family: Arial, Helvetica, sans-serif' size = 2>Proposer Name</b></td><td ><font style='font-family: Arial, Helvetica, sans-serif' size = 2>"+fullName.toString()+"</font></td>");
			mailDatas.append("<td><b><font style='font-family: Arial, Helvetica, sans-serif' size = 2>Occupation</b></td><td><font style='font-family: Arial, Helvetica, sans-serif' size = 2>"+isNull((String)travelMailDetails.get("per.occupation"),"select")+"</font></td>");
			mailDatas.append("</tr>");
			mailDatas.append("<tr>");
			mailDatas.append("<td><b><font style='font-family: Arial, Helvetica, sans-serif' size = 2>Nationality</B></td><td><font style='font-family: Arial, Helvetica, sans-serif' size = 2>"+isNull((String)travelMailDetails.get("per.nationality"),"Select")+"</font></td>");
			mailDatas.append("<td><b><font style='font-family: Arial, Helvetica, sans-serif' size = 2>DOB</b></td><td><font style='font-family: Arial, Helvetica, sans-serif' size = 2>"+isNull((String)travelMailDetails.get("per.dob"),"select")+"</font></td>");
			mailDatas.append("</tr>");
			mailDatas.append("<tr>");
			mailDatas.append("<td><font style='font-family: Arial, Helvetica, sans-serif' size = 2><b>P.O. Box</b></td><td><font style='font-family: Arial, Helvetica, sans-serif' size = 2>"+isNull((String)travelMailDetails.get("per.pobox"))+"</font></td>");
			mailDatas.append("<td><font style='font-family: Arial, Helvetica, sans-serif' size = 2><b>Emirate</b></td><td><font style='font-family: Arial, Helvetica, sans-serif' size = 2>"+isNull((String)travelMailDetails.get("per.emirate"),"select")+"</font></td>");
			mailDatas.append("</tr>");
			mailDatas.append("<tr>");
			mailDatas.append("<td><b><font style='font-family: Arial, Helvetica, sans-serif' size = 2>Telephone</b></td><td><font style='font-family: Arial, Helvetica, sans-serif' size = 2>"+isNull((String)travelMailDetails.get("per.telephone"))+"</font></td>");
			mailDatas.append("<td><b><font style='font-family: Arial, Helvetica, sans-serif' size = 2>Mobile</b></td><td><font style='font-family: Arial, Helvetica, sans-serif' size = 2>"+isNull((String)travelMailDetails.get("per.mobile"))+"</font></td>");
			mailDatas.append("</tr>");
			mailDatas.append("<tr> ");
			mailDatas.append("<td><b><font style='font-family: Arial, Helvetica, sans-serif' size = 2>Fax</b></td><td><font style='font-family: Arial, Helvetica, sans-serif' size = 2>"+isNull((String)travelMailDetails.get("per.fax"))+"</font></td>");
			mailDatas.append("<td><b><font style='font-family: Arial, Helvetica, sans-serif' size = 2>E-Mail Id</b></td><td><font style='font-family: Arial, Helvetica, sans-serif' size = 2>"+isNull((String)travelMailDetails.get("per.email"))+"</font></td>");
			mailDatas.append("</tr><tr>");
			mailDatas.append("<td><b><font style='font-family: Arial, Helvetica, sans-serif' size = 2>Address</b></td><td><font style='font-family: Arial, Helvetica, sans-serif' size = 2><font style='font-family: Arial, Helvetica, sans-serif' size = 2>"+isNull((String)travelMailDetails.get("per.address1"))+"</font></td>");
			mailDatas.append("</tr>");
			mailDatas.append("<tr><td colspan='6'><b><font style='font-family: Arial, Helvetica, sans-serif' size = 3><u>Insurer's Information</u></b></td></tr></table>");
			mailDatas.append("<table border='0' cellpadding='3' style='border-collapse:collapse' width='90%' align='center'>");
			mailDatas.append("<tr>");
			mailDatas.append("<td><b><font style='font-family: Arial, Helvetica, sans-serif' size = 2>Title</b></td><td ><font style='font-family: Arial, Helvetica, sans-serif' size = 2>"+hb.getTitle()+"</font></td>");
			mailDatas.append("<td><b><font style='font-family: Arial, Helvetica, sans-serif' size = 2>Name</b></td><td><font style='font-family: Arial, Helvetica, sans-serif' size = 2>"+hb.getInsurerName()+"</font></td>");
			mailDatas.append("</tr>");
			mailDatas.append("<tr>");
			mailDatas.append("<td><b><font style='font-family: Arial, Helvetica, sans-serif' size = 2>Customer Civil ID</b></td><td ><font style='font-family: Arial, Helvetica, sans-serif' size = 2>"+hb.getCusCivilId()+"</font></td>");
			mailDatas.append("<td><b><font style='font-family: Arial, Helvetica, sans-serif' size = 2>Date Of Birth</b></td><td><font style='font-family: Arial, Helvetica, sans-serif' size = 2>"+hb.getDob()+"</font></td>");
			mailDatas.append("</tr>");
			mailDatas.append("<tr>");
			mailDatas.append("<td><b><font style='font-family: Arial, Helvetica, sans-serif' size = 2>Gender</b></td><td ><font style='font-family: Arial, Helvetica, sans-serif' size = 2>"+hb.getGender()+"</font></td>");
			mailDatas.append("<td><b><font style='font-family: Arial, Helvetica, sans-serif' size = 2>Marital Status</b></td><td><font style='font-family: Arial, Helvetica, sans-serif' size = 2>"+hb.getMaritalStatus()+"</font></td>");
			mailDatas.append("</tr>");
			mailDatas.append("<tr>");
			mailDatas.append("<td><b><font style='font-family: Arial, Helvetica, sans-serif' size = 2>Occupation</b></td><td ><font style='font-family: Arial, Helvetica, sans-serif' size = 2>"+hb.getOccupation()+"</font></td>");
			mailDatas.append("<td><b><font style='font-family: Arial, Helvetica, sans-serif' size = 2>Relation</b></td><td><font style='font-family: Arial, Helvetica, sans-serif' size = 2>"+hb.getRelation()+"</font></td>");
			mailDatas.append("</tr>");
			mailDatas.append("<tr>");
			mailDatas.append("<td><b><font style='font-family: Arial, Helvetica, sans-serif' size = 2>Nationality</b></td><td ><font style='font-family: Arial, Helvetica, sans-serif' size = 2>"+hb.getNationality()+"</font></td>");
			mailDatas.append("<td><b><font style='font-family: Arial, Helvetica, sans-serif' size = 2></b></td><td><font style='font-family: Arial, Helvetica, sans-serif' size = 2></font></td>");
			mailDatas.append("</tr>");
		/*	mailDatas.append("<tr class='heading'><td width='30%' align='center' class='heading'><b>Name</b></td>");
		    mailDatas.append("<td width='30%' align='center' class='heading'><b>Date&nbsp;of&nbsp;Birth</b></td>");
			mailDatas.append("<td width='5%' align='center' class='heading'><b>Age</b></td>");
			mailDatas.append("<td width='10%' align='center' class='heading'><b>Gender</b></td>");
			mailDatas.append("<td width='15%' align='center' class='heading'><b>Relation</b></td>");
			mailDatas.append("<td width='25%' align='center' class='heading'><b>Nationality</b></td></tr>");*/
			/*for(int i=0;i<names.length;i++)
			{
				mailDatas.append("<tr><td align='center'><font style='font-family: Arial, Helvetica, sans-serif' size = 2>&nbsp;&nbsp;"+names[i]+"</font></td><td><font style='font-family: Arial, Helvetica, sans-serif' size = 2><center>"+dobs[i]+"</font></center></td>");
				    mailDatas.append("<td><center>&nbsp;&nbsp;<font style='font-family: Arial, Helvetica, sans-serif' size = 2>"+age[i]+"</font></center></td>");
				if(rel[i].equalsIgnoreCase("1"))
					rels = "Self";
				else if(rel[i].equalsIgnoreCase("2"))
					rels = "Spouse";
				else if(rel[i].equalsIgnoreCase("3"))
					rels = "Child";
				else if(rel[i].equalsIgnoreCase("4"))
					rels = "Father";
				else if(rel[i].equalsIgnoreCase("5"))
					rels = "Mother";
				else if(rel[i].equalsIgnoreCase("6"))
					rels = "Other";
				String gen="";
		        if(genders[i] != null && genders[i].equalsIgnoreCase("M")) 
					gen = "Male";
		        else if(genders[i] != null && genders[i].equalsIgnoreCase("F")) 
					gen = "Female";
				mailDatas.append("<td><center>&nbsp;&nbsp;<font style='font-family: Arial, Helvetica, sans-serif' size = 2>"+gen+"</font></center></td>");
				mailDatas.append("<td><center>&nbsp;&nbsp;<font style='font-family: Arial, Helvetica, sans-serif' size = 2>"+rels+"</font></center></td>");
		        nationalities[i] = (nationalities[i] == null || nationalities[i].equalsIgnoreCase("null"))?"":nationalities[i];
				mailDatas.append("<td><center>&nbsp;&nbsp;<font style='font-family: Arial, Helvetica, sans-serif' size = 2>"+nationalities[i]+"</font></center></td></tr>");
			}*/
			
		
			mailDatas.append("<br>");
			mailDatas.append("<tr><td></td><td></td><td></td><td></td></tr>");
			mailDatas.append("<tr class='blueborder'>");
			mailDatas.append("<td class='heading' colspan='6' ><b><font style='font-family: Arial, Helvetica, sans-serif' size = 3><u>Sponsor Details</font></b></u></td>");
			mailDatas.append("</tr> </table>");
			mailDatas.append("<table border='0' cellpadding='3' style='border-collapse:collapse' width='90%' align='center'>");
			mailDatas.append("<tr>");
			mailDatas.append("<td><b><font style='font-family: Arial, Helvetica, sans-serif' size = 2>Sponsor ID</b></td><td ><font style='font-family: Arial, Helvetica, sans-serif' size = 2>"+hb.getSponsorId()+"</font></td>");
			mailDatas.append("<td><b><font style='font-family: Arial, Helvetica, sans-serif' size = 2>Sponsor Name</b></td><td><font style='font-family: Arial, Helvetica, sans-serif' size = 2>"+hb.getSponsorName()+"</font></td>");
			mailDatas.append("</tr>");
			mailDatas.append("<tr>");
			mailDatas.append("<td><b><font style='font-family: Arial, Helvetica, sans-serif' size = 2>City</b></td><td ><font style='font-family: Arial, Helvetica, sans-serif' size = 2>"+hb.getSponsorCity()+"</font></td>");
			mailDatas.append("<td><b><font style='font-family: Arial, Helvetica, sans-serif' size = 2>Mobile Number</b></td><td><font style='font-family: Arial, Helvetica, sans-serif' size = 2>"+hb.getSponsorMobileNo()+"</font></td>");
			mailDatas.append("</tr>");
			mailDatas.append("<tr>");
			mailDatas.append("<td><b><font style='font-family: Arial, Helvetica, sans-serif' size = 2>Nationality</b></td><td ><font style='font-family: Arial, Helvetica, sans-serif' size = 2>"+hb.getNationality()+"</font></td>");
			mailDatas.append("<td><b><font style='font-family: Arial, Helvetica, sans-serif' size = 2></b></td><td><font style='font-family: Arial, Helvetica, sans-serif' size = 2></font></td>");
			mailDatas.append("</tr>");
			
			
			//mailDatas.append("<tr><td></td><td></td><td></td><td></td></tr>");
			mailDatas.append("<tr class='blueborder'>");
			mailDatas.append("<td class='heading' colspan='6' ><b><font style='font-family: Arial, Helvetica, sans-serif' size = 3><u>Quote Information</font></b></u></td>");
			mailDatas.append("</tr> </table>");
			mailDatas.append("<table class='data-table-border' border='0' cellpadding='4' cellspacing='0' width='95%' align='center'>");
		    mailDatas.append("<tr><td><b><font style='font-family: Arial, Helvetica, sans-serif' size = 2>Quote No</b></td><td><font style='font-family: Arial, Helvetica, sans-serif' size = 2>"+quoteNo+" </td>");
			mailDatas.append("<td><b><font style='font-family: Arial, Helvetica, sans-serif' size = 2>Quoted&nbsp;Date</b></td><td ><font style='font-family: Arial, Helvetica, sans-serif' size = 2>"+sysDay+"-"+sysMonth+"-"+sysYear+"</td>");
			mailDatas.append("<td><b><font style='font-family: Arial, Helvetica, sans-serif' size = 2>PRODUCT&nbsp;TYPES</font></b></td> <td><font style='font-family: Arial, Helvetica, sans-serif' size = 2>&nbsp;</font></td><td>"+proType+"</td></tr><tr>");
			
			//String[][] coverDuration = new String[0][0];
			/*coverDuration = travelMail.getCoverDuration(quoteNo);
		    if(coverDuration.length >0)
			{*/
				mailDatas.append("<td><b><font style='font-family: Arial, Helvetica, sans-serif' size = 2>Cover Period</b></td><td><font style='font-family: Arial, Helvetica, sans-serif' size = 2>"+hb.getCoverPeriod()+" days</td>");
				mailDatas.append("<td class='runtext-white'><b>Inception&nbsp;Date</sb></td>");
				mailDatas.append("<td class='runtext-white'>"+hb.getInceptionDt()+"</td>");
				mailDatas.append("<td class='runtext-white'><b>&nbsp;Expiry&nbsp;Date </b></td>");
				mailDatas.append("<td class='runtext-white'>"+hb.getExpiryDt()+"</td>");	
			//}
		    
			mailDatas.append("</tr>");
			String excessandSign[][] = new String[0][0];
			double totPre = 0.0;
			String preOriginal ="";
			String exPre ="";
			String sign ="";
		
			/*if(travelMailDetails.size()>0)
			{
				preOriginal =(travelMailDetails.get("pos.PREMIUM")==null?"":(String)travelMailDetails.get("pos.PREMIUM"));
				
				excessandSign=(ht.get("PremiumDetails1")==null?(new String[0][0]):(String[][])ht.get("PremiumDetails1"));
				if(excessandSign.length>0)
				{
					exPre = excessandSign[0][0]!=null?excessandSign[0][1]:"";
					sign = excessandSign[0][3]!=null?excessandSign[0][3]:"";
				}
				if((!preOriginal.equals("")&&preOriginal.length()>0) && (!exPre.equals("")&&exPre.length()>0))
				{
					if(sign.equalsIgnoreCase("+"))
						totPre = Double.parseDouble(preOriginal) + Double.parseDouble(exPre);
					else if(sign.equalsIgnoreCase("-"))
						totPre = Double.parseDouble(preOriginal) - Double.parseDouble(exPre);
					else
						totPre = Double.parseDouble(preOriginal) + Double.parseDouble(exPre);
				}
				else if(!preOriginal.equals("") && preOriginal.length()>0 && totPre==0)
					totPre = Double.parseDouble(preOriginal);	
			}
			else
			{*/
			 	mailDatas.append("<tr>");
				mailDatas.append("<td><b><font style='font-family: Arial, Helvetica, sans-serif' size = 2>Product</b></td><td ></td>");
				mailDatas.append("<td><b><font style='font-family: Arial, Helvetica, sans-serif' size = 2>Premium</b></td><td ></td>");
				mailDatas.append("</tr><tr>");
				mailDatas.append("<td><b><font style='font-family: Arial, Helvetica, sans-serif' size = 2>Policy Start Date </b></td><td ></td>");
				mailDatas.append("<td><b><font style='font-family: Arial, Helvetica, sans-serif' size = 2>Policy End Date</b></td><td ></td></tr>");
			// }
		
			mailDatas.append("</tr>");
			mailDatas.append("<tr class='blueborder'>"); 
			mailDatas.append("<td class='heading' colspan='6' height='2'></td>");
			mailDatas.append("</tr>");
			mailDatas.append("</table>");
			mailDatas.append("<font style='font-family: Arial, Helvetica, sans-serif' size = 3><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<u>Reason For Referral</u></b></font>");
			StringTokenizer st = new StringTokenizer(referralName,"*");
			String temp="";
			int j=0;
			while(st.hasMoreTokens())
			{
				temp = temp.trim()+"<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font size=2 color='red' align='left'>"+(++j)+".&nbsp;"+st.nextToken()+"</font>";
			}
			temp = temp.trim()+"<br/><br/>";
			mailDatas.append("<font style='font-family: Arial, Helvetica, sans-serif' size = 2>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+temp+"</font>");
				 mailDatas.append("</div></td></tr>");
				 mailDatas.append("</table>");
				 mailDatas.append("<td width='20%'>&nbsp;</td>");
				 mailDatas.append("</tr></table> </table>");
			/*String[][] coverDetails = new String[0][0];
			
			coverDetails = travelMail.getCoverageValues(quoteNo);
			
			String schemeName="",optionName="",schemeCover=(String)ht.get("scheme_cover");
			int index=schemeCover.indexOf("_");
			
			if(index!=-1)
			{
				schemeName=schemeCover.substring(0, index);
				optionName=schemeCover.substring(index+1, schemeCover.length()).replaceAll("_", " ");
				optionName="-"+optionName;
			}
			else
			{
				schemeName=schemeCover;
				optionName="";
			}*/
			
			/*HashMap travelCovers = new HashMap();
			
			//travelCovers = travelMail.getTravelCovers(quoteNo);
			
			travelCovers = travelMail.getTravelCoveragesList((String)ht.get("scheme_id"),(String)ht.get("option_id"),branch,proId); 
			
			System.out.println("travelMailDetails.get('pos.PRODUCT_ID'): "+travelMailDetails.get("pos.PRODUCT_ID"));
			String travelOptionStatus = travelMail.getTravelOptionStatus((String)travelMailDetails.get("pos.PRODUCT_ID"));
			LogManager.info("royal test travelOptionStatus..."+travelOptionStatus+" coverDetails.length: "+coverDetails.length);*/
			mailDatas.append("<table border='0' cellpadding='3' cellspacing='1' style='border-collapse:collapse' width='90%' align='center'>");
			mailDatas.append("<tr><td align='left' colspan='8' ><font style='font-family: Arial, Helvetica, sans-serif' size = 2>Scheme Covers : </font>&nbsp;");
			mailDatas.append("<font style='font-family: Arial, Helvetica, sans-serif' size = 2><b>"+hb.getSchemeCover()+"</b></font></td></tr>");
			/*for(int i=0;i<coverDetails.length;i++)
			{
				mailDatas.append("<tr class='blueborder'> ");
				mailDatas.append("<td class='heading' colspan='8' ><b><font style='font-family: Arial, Helvetica, sans-serif' size = 3><u>Cover Details</u></b></font></td>");
				mailDatas.append("</tr>");
				if(travelCovers.size()>0)
				{
					if(((String)travelCovers.get("CoverId"+i)).equalsIgnoreCase((coverDetails[i][1]!=null?coverDetails[i][1]:"")))
					{
						mailDatas.append("<tr><td align='left'  width='40%' colspan='2' ><font style='font-family: Arial, Helvetica, sans-serif' size = 2>"+(travelCovers.get("Coveres"+i)).toString()+" :</font></td>");
						if((coverDetails[i][0]!=null?coverDetails[i][0]:"").equalsIgnoreCase("Y"))
						{
							mailDatas.append("<td align='left'  width='5%'><font style='font-family: Arial, Helvetica, sans-serif' size = 2><b>YES</b></font></td>");
						}
						else
						{
							mailDatas.append("<td align='left'  width='5%'><font style='font-family: Arial, Helvetica, sans-serif' size = 2><b>NO</b></font></td>");
						}
						mailDatas.append("</tr>");
					}
				}
			}*/// CoverDetails
			 if(ADMIN.equalsIgnoreCase(reqFrom)){
				 HashMap brokerDetails = (HashMap) session.getAttribute("BrokerDetails");
				 String currencyType = "";
				 if (brokerDetails.size() > 0) {
					currencyType = (String) brokerDetails.get("CurrencyAbb");
				 }
				 mailDatas.append("<tr class='blueborder'> ");
				 mailDatas.append("<td class='heading' colspan='8' ><b><font style='font-family: Arial, Helvetica, sans-serif' size = 3><u>Premium</u></b></font></td>");
				 mailDatas.append("</tr>");
				 mailDatas.append("<tr><td align='left'  width='40%' colspan='2' ><font style='font-family: Arial, Helvetica, sans-serif' size = 2>Premium</font></td>");
				 mailDatas.append("<td align='left'  width='5%' ><font style='font-family: Arial, Helvetica, sans-serif' size = 2><b>"+currencyType+" "+hb.getTotalPremium()+"</b></font></td></tr>");
			 }
			mailDatas.append("<tr class='blueborder'><td class='heading' colspan='8' height='2'></td></tr></table>");
			
			if (adminRemarks.trim().length() > 0&&ADMIN.equalsIgnoreCase(reqFrom)) {
				mailDatas.append("<br><br><table><tr><td><font style='font-family: Arial, Helvetica, sans-serif' size = 3><b><u>Admin Referral Remarks<u>" +
						"</b></font></td><tr></tr><td><font style='font-family: Arial, Helvetica, sans-serif' size = 2>");
				mailDatas.append(adminRemarks);
				mailDatas.append("</font></td></tr></table>");
				if(broker.length()>0){
					broker.delete(0, broker.length());
				}
				broker.append(fullAddr);
			}
			else if(ADMIN.equalsIgnoreCase(reqFrom)){
				if(broker.length()>0){
					broker.delete(0, broker.length());
				}
				broker.append(fullAddr);
			}
	   		 mailDatas.append("</table>");
     		 mailDatas.append("<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font style='font-family: Arial, Helvetica, sans-serif' size = 3><b>Regards,</font>");
			 mailDatas.append("<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font style='font-family: Arial, Helvetica, sans-serif' size = 3>"+broker.toString()+"</font>");
			 mailDatas.append("<br><br>");
			 mailDatas.append("</form>");
			 mailDatas.append("</html>");
			
		return mailDatas.toString();
	}
	
	public String getHomeMailContent(final HttpServletRequest request, final HttpServletResponse response,
			final String quoteNo,final String proId,final String loginId,final String branch,final String reqFrom,
			final String adminRemarks,final String fullAddr)
	throws ServletException, IOException,BaseException {
		final com.maan.Home.DataManipualtion.HomePdfDataSelect pdfDataSelect = new com.maan.Home.DataManipualtion.HomePdfDataSelect();
		final com.maan.Home.DataManipualtion.DataSelect cover = new com.maan.Home.DataManipualtion.DataSelect();
		final com.maan.Home.DataManipualtion.HomeValidationBean util = new com.maan.Home.DataManipualtion.HomeValidationBean();
		final StringBuffer mailDatas = new StringBuffer(10000);
		response.setContentType(CONTENT);
		final HttpSession session = request.getSession(true);
		final String path = request.getContextPath();
		final StringBuffer fullName = new StringBuffer();
		final String referralName = cover.getReferralNames(quoteNo);
		final Calendar calendar = Calendar.getInstance();
		final String sysDay = Integer.toString(calendar.get(java.util.Calendar.DATE));
		final String sysMonth = Integer.toString((calendar.get(java.util.Calendar.MONTH) + 1));
		final String sysYear = Integer.toString(calendar.get(java.util.Calendar.YEAR));

			Map CoverageDetails = new HashMap();
			if (session.getAttribute("CoverageDetails") != null) {
				CoverageDetails = (HashMap) session.getAttribute("CoverageDetails");
				session.setAttribute("CoverageDetails", CoverageDetails);
			}
			
			final java.text.NumberFormat fmt = new java.text.DecimalFormat("##,##0");
			final Map homePdfDetails = pdfDataSelect.getHomePdfDetails(quoteNo, loginId);
			final StringBuffer broker = new StringBuffer();
			if (!homePdfDetails.isEmpty()) {
				final String fName = isNull((String)homePdfDetails.get("per.first_name"));
				final String cName = isNull((String)homePdfDetails.get("per.companyName"));
				broker.append(isNull((String)homePdfDetails.get("bro.COMPANY_NAME")));
				if (fName.length() > 0){
					fullName.append(isNullOrSelect((String)homePdfDetails.get("per.title"),"select"));
					fullName.append("&nbsp;");
					fullName.append(fName);
				}
				else if (cName.length() > 0){
					fullName.append(isNullOrSelect((String)homePdfDetails.get("per.title"),"select"));
					fullName.append("&nbsp;");
					fullName.append(cName);
				}
			}
			final String bname = util.removeLastChar(util.getStringFromArray(pdfDataSelect.getFinanceBankName(quoteNo)), ',');
			mailDatas.append("<html><head></head><link rel='stylesheet' href= '");
			mailDatas.append(path);
			mailDatas.append("\\css\\rsa.css' type='text/css\'><body><form name='summary' method='post'><table border='0' cellpadding='3' style='border-collapse:collapse' width='90%' " +
					"align='center'><tr><td colspan='6' ><center><font style='font-family: Arial, Helvetica, sans-serif' size = 3><b><u>");
			mailDatas.append(cover.productDes(proId, branch));
			mailDatas.append("</u></b><center></td></tr>");
			mailDatas.append(BREAK);
			mailDatas.append("<tr class='blueborder'><td class='heading' colspan='6'><b><font style='font-family: Arial, Helvetica, sans-serif' size = 3><u>Proposer 's Information</font></b></u></td>" +
					"</tr><tr><td><b><font style='font-family: Arial, Helvetica, sans-serif' size = 2>Proposer Name</b></td><td ><font style='font-family: Arial, Helvetica, sans-serif' size = 2>");
			mailDatas.append(fullName.toString());
			mailDatas.append("</font></td><td><b><font style='font-family: Arial, Helvetica, sans-serif' size = 2>Occupation</b></td><td><font style='font-family: Arial, Helvetica, sans-serif' size = 2>");
			mailDatas.append(isNull((String)homePdfDetails.get("per.occupation")));
			mailDatas.append("</font></td></tr><tr><td><b><font style='font-family: Arial, Helvetica, sans-serif' size = 2>Nationality</B></td><td><font style='font-family: Arial, Helvetica, sans-serif' size = 2>");
			mailDatas.append(isNullOrSelect((String)homePdfDetails.get("per.nationality"),"Select"));
			mailDatas.append("</font></td><td><b><font style='font-family: Arial, Helvetica, sans-serif' size = 2>DOB</b></td><td><font style='font-family: Arial, Helvetica, sans-serif' size = 2>");
			mailDatas.append(isNullOrSelect((String)homePdfDetails.get("per.dob"),"Select"));
			mailDatas.append("</font></td></tr><tr><td><font style='font-family: Arial, Helvetica, sans-serif' size = 2><b>P.O. Box</b></td><td><font style='font-family: Arial, Helvetica, sans-serif' size = 2>");
			mailDatas.append(isNull((String)homePdfDetails.get("per.pobox")));
			mailDatas.append("</font></td><td><font style='font-family: Arial, Helvetica, sans-serif' size = 2><b>Emirate</b></td><td><font style='font-family: Arial, Helvetica, sans-serif' size = 2>");
			mailDatas.append(isNullOrSelect((String)homePdfDetails.get("per.emirate"),"Select"));
			mailDatas.append("</font></td></tr><tr><td><b><font style='font-family: Arial, Helvetica, sans-serif' size = 2>Telephone</b></td><td><font style='font-family: Arial, Helvetica, sans-serif' size = 2>");
			mailDatas.append(isNull((String)homePdfDetails.get("per.telephone")));
			mailDatas.append("</font></td><td><b><font style='font-family: Arial, Helvetica, sans-serif' size = 2>Mobile</b></td><td><font style='font-family: Arial, Helvetica, sans-serif' size = 2>");
			mailDatas.append(isNull((String)homePdfDetails.get("per.mobile")));
			mailDatas.append("</font></td></tr><tr><td><b><font style='font-family: Arial, Helvetica, sans-serif' size = 2>Fax</b></td><td><font style='font-family: Arial, Helvetica, sans-serif' size = 2>");
			mailDatas.append(isNull((String)homePdfDetails.get("per.fax")));
			mailDatas.append("</font></td><td><b><font style='font-family: Arial, Helvetica, sans-serif' size = 2>E-Mail Id</b></td><td><font style='font-family: Arial, Helvetica, sans-serif' size = 2>");
			mailDatas.append(isNull((String)homePdfDetails.get("per.email")));
			mailDatas.append("</font></td></tr><tr><td><b><font style='font-family: Arial, Helvetica, sans-serif' size = 2>Address</b></td><td><font style='font-family: Arial, Helvetica, sans-serif' size = 2><font style='font-family: Arial, Helvetica, sans-serif' size = 2>");
			mailDatas.append(isNull((String)homePdfDetails.get("per.address1")));
			mailDatas.append("</font></td><td><b><font style='font-family: Arial, Helvetica, sans-serif' size = 2>Insured Location</b></td><td><font style='font-family: Arial, Helvetica, sans-serif' size = 2>");
			mailDatas.append(pdfDataSelect.getBuildingLocation(quoteNo));
			mailDatas.append("</font></td></tr>");
			if ((isNull((String)homePdfDetails.get("pos.bedrooms"))).length() <= 0 || "0".equals(isNull((String)homePdfDetails.get("pos.bedrooms")))) {
				mailDatas.append("<td><b><font style='font-family: Arial, Helvetica, sans-serif' size = 2>Named Interest</font></b></td><td ><font style='font-family: Arial, Helvetica, sans-serif' size = 2>");
				mailDatas.append(bname);
				mailDatas.append("</font></td><td><b><font style='font-family: Arial, Helvetica, sans-serif' size = 2>&nbsp;</font></b></td><td ><font style='font-family: Arial, Helvetica, sans-serif' size = 2>&nbsp;</font></td></tr>");
			}
			else {

				mailDatas.append("<td><b><font style='font-family: Arial, Helvetica, sans-serif' size = 2>No of Bedrooms</font></b></td><td ><font style='font-family: Arial, Helvetica, sans-serif' size = 2>");
				mailDatas.append(isNull((String)homePdfDetails.get("pos.bedrooms")));
				mailDatas.append("</font></td><td><b><font style='font-family: Arial, Helvetica, sans-serif' size = 2>Named Interest</font></b></td><td ><font style='font-family: Arial, Helvetica, sans-serif' size = 2>");
				mailDatas.append(bname);
				mailDatas.append("</font></td></tr>");
			}
			mailDatas.append(BREAK);
			mailDatas.append("<tr><td></td><td></td><td></td><td></td></tr><tr><td></td><td></td><td></td><td></td></tr><tr class='blueborder'>" +
					"<td class='heading' colspan='6' ><b><font style='font-family: Arial, Helvetica, sans-serif' size = 3><u>Quote Information</font></b></u>" +
					"</tr> <tr></td><td><b><font style='font-family: Arial, Helvetica, sans-serif' size = 2>Quote No</b></td><td><font style='font-family: Arial, Helvetica, sans-serif' size = 2>");
			mailDatas.append(quoteNo);
			mailDatas.append("</td><td><b><font style='font-family: Arial, Helvetica, sans-serif' size = 2>Quoted Date</b></td><td ><font style='font-family: Arial, Helvetica, sans-serif' size = 2>");
			mailDatas.append(sysDay);
			mailDatas.append('/');
			mailDatas.append(sysMonth);
			mailDatas.append('/');
			mailDatas.append(sysYear);
			mailDatas.append("</td></tr></tr></table><table border='0' cellpadding='3' cellspacing='1' style='border-collapse:collapse' width='90%' align='center'>");
			mailDatas.append(BREAK);
			mailDatas.append("<tr class='blueborder'><td class='heading' align='left' width='10%' colspan='4'><b><font style='font-family: Arial, Helvetica, sans-serif' size = 3><u>Sum Insured Information</u></font></b>" +
					"</td></tr></table><table border='1' cellpadding='3' cellspacing='1' style='border-collapse:collapse' width='90%' " +
					"align='center'><tr class='blueborder'><td class='heading' align='center' width='10%' colspan='2'><font style='font-family: Arial, Helvetica, sans-serif' size = 2><b>SNO</b></font>" +
					"</td><td class='heading' align='center' width='30%'><font style='font-family: Arial, Helvetica, sans-serif' size = 2><b>COVERAGE</b>" +
					"</font></td><td class='heading' align='center' width='20%'><font style='font-family: Arial, Helvetica, sans-serif' size = 2><b>SUM INSURED [AED]</b>" +
					"</font></td></tr></table><table border='1' cellpadding='3' cellspacing='1' style='border-collapse:collapse' width='90%' align='center'>");
			double sum = 0.0;
			double pre = 0.0;
			if (!homePdfDetails.isEmpty()) {

				final int size = Integer.parseInt((String) homePdfDetails.get("covtra.length"));

				for (int i = 0; i < size; i++) {
					if (!(((String) homePdfDetails.get(COVERSUM + i)).equalsIgnoreCase("1"))
							&& !(((String) homePdfDetails.get(COVERSUM + i)).equalsIgnoreCase("0"))){
						sum += Double.parseDouble(homePdfDetails.get(COVERSUM + i).equals("")? "0": (String) homePdfDetails.get(COVERSUM+ i));
					}
					pre += Double.parseDouble(homePdfDetails.get("covtra.premium"+ i).equals("") ? "0" :(String)homePdfDetails.get("covtra.premium" + i));

					mailDatas.append("<tr><td align='center' width='10%' colspan='2'><b><font style='font-family: Arial, Helvetica, sans-serif' size = 2>");
					mailDatas.append(Integer.toString((i + 1)));
					mailDatas.append("</font></b></td><td align='left' width='30%' > <b><font style='font-family: Arial, Helvetica, sans-serif' size = 2>");
					mailDatas.append(homePdfDetails.get("covtra.Product_description"+ i));
					mailDatas.append("</font></b></td>");

					if ((((String) homePdfDetails.get(COVERSUM + i)).equalsIgnoreCase("1"))
							|| (((String) homePdfDetails.get(COVERSUM + i)).equalsIgnoreCase("0"))) {
						mailDatas.append("<td align='center' width='20%'><font style='font-family: Arial, Helvetica, sans-serif' size = 2></td>");
					} else {
						mailDatas.append("<td align='center' width='20%'><font style='font-family: Arial, Helvetica, sans-serif' size = 2><b>");
						mailDatas.append(fmt.format(Double.parseDouble(homePdfDetails.get(COVERSUM+ i).equals("1") ? "-": (String) homePdfDetails.get(COVERSUM+ i))));
						mailDatas.append("</b></font></td>");
					}
					mailDatas.append("</tr>");
				}
			}

			final String[][] claimDetails = cover.getClaimDetails(quoteNo);
			mailDatas.append("<tr class='blueborder'><td class='heading' colspan='6' height='2'></td></tr>" +
					"</table><table border='0' cellpadding='3' cellspacing='1' style='border-collapse:collapse' width='90%' align='center'>");
			final StringBuffer preStatus = new StringBuffer();
			if (!"0".equals(isNull((String)homePdfDetails.get("pos.EXCESS_PREMIUM"),"0")) && (isNull((String)homePdfDetails.get("pos.EXCESS_PREMIUM"),"0")).length() > 0&&ADMIN.equalsIgnoreCase(reqFrom)) {
				if ("+".equalsIgnoreCase(isNull((String)homePdfDetails.get("pos.EXCESS_SIGN")))) {
					preStatus.append("Additional");
				} else if ("-".equalsIgnoreCase(isNull((String)homePdfDetails.get("pos.EXCESS_SIGN")))) {
					preStatus.append("Discount");
				}
				mailDatas.append("<tr><td align='right'  width='10%' colspan='2'><b>&nbsp;</b></td><td align='left'  width='30%' ><b></b>" +
						"</td><td align='left'  width='20%' colspan='2' class='MDblueBG1'><b><font style='font-family: Arial, Helvetica, sans-serif' size = 2>Premium [AED]</font></b></font>" +
						"</td><td align='center' width='20%' class='MDblueBG1'><font style='font-family: Arial, Helvetica, sans-serif' size = 2><b>");
				mailDatas.append(fmt.format(Double.parseDouble(isNull((String)homePdfDetails.get("pos.PREMIUM"),"0"))));
				mailDatas.append("</b></font></td></tr><tr><td align='right'  width='10%' colspan='2'><b>&nbsp;</b></td>" +
						"<td align='left'  width='30%' ><b></b></td><td align='left'  width='20%' colspan='2' class='MDblueBG1'><b><font style='font-family: Arial, Helvetica, sans-serif' size = 2>");
				mailDatas.append(preStatus.toString());
				mailDatas.append(" Premium [AED]</b></font></font></td><td align='center' width='20%' class='MDblueBG1'><b><font style='font-family: Arial, Helvetica, sans-serif' size = 2>");
				mailDatas.append(fmt.format(Double.parseDouble(isNull((String)homePdfDetails.get("pos.EXCESS_PREMIUM"),"0"))));
				mailDatas.append("</tr><tr></font></b></td><td align='right'  width='10%' colspan='2'><b>&nbsp;</b></td>" +
						"<td align='left'  width='30%' ><b></b></td><td align='left'  width='20%' colspan='2' class='MDblueBG1'><b><font style='font-family: Arial, Helvetica, sans-serif' size = 2> Premium Payable [AED]</b>" +
						"</font></font></td><td align='center' width='20%' class='MDblueBG1'><b><font style='font-family: Arial, Helvetica, sans-serif' size = 2>");
				mailDatas.append(fmt.format(Double.parseDouble(isNull((String)homePdfDetails.get("pos.OVERALL_PREMIUM"),"0"))));
				mailDatas.append("</font></b></td></tr>");
			} 
			else if(ADMIN.equalsIgnoreCase(reqFrom)){
				mailDatas.append("<tr><td align='right'  width='10%' colspan='2'><b>&nbsp;</b></td><td align='left'  width='30%'>" +
						"<b></b></td><td align='left'  width='20%' colspan='2' class='MDblueBG1'><b><font style='font-family: Arial, Helvetica, sans-serif' size = 2>Premium [AED]</font>" +
						"</b></font></td><td align='center' width='20%' class='MDblueBG1'><font style='font-family: Arial, Helvetica, sans-serif' size = 2><b>");
				mailDatas.append(fmt.format(Double.parseDouble(isNull((String)homePdfDetails.get("pos.PREMIUM"),"0"))));
				mailDatas.append("</b></font></td></tr>");
			}
			mailDatas.append("</table><font style='font-family: Arial, Helvetica, sans-serif' size = 3><b>");
			mailDatas.append(SPACE);			
			mailDatas.append(new String("<u>Reason For Referral</u></b></font>"));
			final StringTokenizer refToken = new StringTokenizer(referralName, "*");
			final StringBuffer refBuf = new StringBuffer(200);
			int loopJ = 0;
			while (refToken.hasMoreTokens()) {
				refBuf.append("<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font size=2 color='red' align='left'>");
				refBuf.append(Integer.toString((++loopJ)));
				refBuf.append(".&nbsp;");
				refBuf.append(refToken.nextToken());
				refBuf.append(FONT);
			}
			refBuf.append("<br/><br/>");
			mailDatas.append("<font style='font-family: Arial, Helvetica, sans-serif' size = 2>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
			mailDatas.append(refBuf.toString());
			mailDatas.append(FONT);

			for (int i = 0; i < claimDetails.length; i++) {
				mailDatas.append("<table border='0' cellpadding='3' cellspacing='1' style='border-collapse:collapse' width='90%' " +
						"align='center'><tr class='blueborder'><td class='heading' colspan='8' ><b><font style='font-family: Arial, Helvetica, sans-serif' size = 3>" +
						"<u>Underwriting Questions</u></b></font></td></tr><tr><td align='left'  width='40%' colspan='2' ><font style='font-family: Arial, Helvetica, sans-serif' size = 2>Is your home built of concrete</font></td>");
				if (isNull(claimDetails[i][0]).equalsIgnoreCase("Y")) {
					mailDatas.append(STYLE);
				} else {
					mailDatas.append("<td align='left'  width='5%' ><font style='font-family: Arial, Helvetica, sans-serif' size = 2><b>No</b></font></td>");
				}

				mailDatas.append("<td align='left'  width='40%' colspan='2'><font style='font-family: Arial, Helvetica, sans-serif' size = 2>Is your Home Used Solely as a private living accommodation for your household</font></td>");
				if (isNull(claimDetails[i][1]).equalsIgnoreCase("Y")) {
					mailDatas.append("<td align='left'  width='5%'><font style='font-family: Arial, Helvetica, sans-serif' size = 2><b>Yes</font></b></td>");
				} else {
					mailDatas.append("<td align='left'  width='5%'><font style='font-family: Arial, Helvetica, sans-serif' size = 2><b>No</font></b></td>");
				}

				mailDatas.append("</tr><tr><td align='left'  width='40%' colspan='2' ><font style='font-family: Arial, Helvetica, sans-serif' size = 2>Has your home been built on reclaimed land</font></td>");
				if (isNull(claimDetails[i][2]).equalsIgnoreCase("Y")) {
					mailDatas.append(STYLE);
				} else {
					mailDatas.append("<td align='left'  width='5%'><b><font style='font-family: Arial, Helvetica, sans-serif' size = 2>No</font></b></td>");
				}
				mailDatas.append("<td align='left'  width='40%' colspan='2' ><font style='font-family: Arial, Helvetica, sans-serif' size = 2>Do you ever leave your home unattended for more than 60 consecutive days</font></td>");
				if (isNull(claimDetails[i][3]).equalsIgnoreCase("Y")) {
					mailDatas.append(STYLE);
				} else {
					mailDatas.append("<td align='left'  width='5%'><font style='font-family: Arial, Helvetica, sans-serif' size = 2><b>No</b></font></td>");
				}
				mailDatas.append("</tr><tr><td align='left'  width='40%' colspan='2' ><font style='font-family: Arial, Helvetica, sans-serif' size = 2>Has any insurer declined/cancelled/imposed special terms or conditions</font></td>");
				if (isNull(claimDetails[i][4]).equalsIgnoreCase("Y")) {
					mailDatas.append(STYLE);
				} else {
					mailDatas.append("<td align='left'  width='5%'><font style='font-family: Arial, Helvetica, sans-serif' size = 2><b>No</b></font></td>");
				}

				mailDatas.append("<td align='left'  width='20%'>&nbsp;</td></tr>");
				if (isNull(claimDetails[i][5]).equalsIgnoreCase("Y")) {
					mailDatas.append("<tr><td align='left'  width='40%' colspan='2' ><font style='font-family: Arial, Helvetica, sans-serif' size = 2>Have you made any claim in the past 3 years" +
							"</font></td><td align='left'  width='5%'><font style='font-family: Arial, Helvetica, sans-serif' size = 2><b>Yes</b></font>" +
							"</td></tr><tr><td align='left'  width='40%' colspan='2' ><font style='font-family: Arial, Helvetica, sans-serif' " +
							"size = 2>Number of Claims</font></td><td align='left'  width='5%'><b><font style='font-family: Arial, Helvetica, sans-serif' size = 2>");
					mailDatas.append(isNull(claimDetails[i][6]));
					mailDatas.append("</b></font></td><td align='left'  width='40%' colspan='2' ><font style='font-family: Arial, Helvetica, sans-serif' size = 2>Claim Amount</font></td>" +
							"<td align='left' width='5%'><b><font style='font-family: Arial, Helvetica, sans-serif' size = 2>");
					mailDatas.append(isNull(claimDetails[i][8])); 
					mailDatas.append("</font></b></td></tr><tr><td align='left'  width='40%' colspan='2' ><font style='font-family: Arial, Helvetica, sans-serif' size = 2>Type of Claims" +
							"</font></td><td align='left'  width='50%' colspan='4'>");
					final String claimTypeName[][] = cover.getClaimTypeNames(isNull(claimDetails[i][7]), proId);
					for (int c = 0; c < claimTypeName.length; c++) {
						if ((c + 1) == claimTypeName.length) {
							mailDatas.append("<b><font style='font-family: Arial, Helvetica, sans-serif' size = 2>");
							mailDatas.append(isNull(claimTypeName[c][0]));
							mailDatas.append("</font></b>");
						} else {
							mailDatas.append("<b><font style='font-family: Arial, Helvetica, sans-serif' size = 2>");
							mailDatas.append(isNull(claimTypeName[c][0]));
							mailDatas.append("</font></b>");
						}
					}
					mailDatas.append("</td></tr>");

				} else {
					mailDatas.append("<tr><td align='left'  width='40%' colspan='2' ><font style='font-family: Arial, Helvetica, sans-serif' size = 2>Have you made any claim in the past 3 years</font></td>" +
							"<td align='left'  width='5%'><font style='font-family: Arial, Helvetica, sans-serif' size = 2><b>No</b></font></td></tr>");
				}
				mailDatas.append("<tr class='blueborder'><td class='heading' colspan='8' height='2'></td></tr></table>");
				if (adminRemarks.length() > 0&&ADMIN.equalsIgnoreCase(reqFrom)) {
					mailDatas.append("<br><br><table><tr><td><font style='font-family: Arial, Helvetica, sans-serif' size = 3><b><u>Admin Referral Remarks<u>" +
							"</b></font></td><tr></tr><td><font style='font-family: Arial, Helvetica, sans-serif' size = 2>");
					mailDatas.append(adminRemarks);
					mailDatas.append("</font></td></tr></table>");
					if(broker.length()>0){
						broker.delete(0, broker.length());
					}
					broker.append(fullAddr);
				}
				else if(ADMIN.equalsIgnoreCase(reqFrom)){
					if(broker.length()>0){
						broker.delete(0, broker.length());
					}
					broker.append(fullAddr);
				}
			}

			mailDatas.append("</div></td></tr></table><td width='20%'>&nbsp;</td></tr></table></table><br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
					"<font style='font-family: Arial, Helvetica, sans-serif' size = 3><b>Regards,</font><br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font style='font-family: Arial, Helvetica, sans-serif' size = 3>");
			mailDatas.append(broker.toString());
			mailDatas.append("</font><br><br></form></html>");

			return mailDatas.toString();
	}
	public void processResult(final HttpServletRequest request,
			final HttpServletResponse response) throws ServletException, IOException,BaseException {
		try {
			final com.maan.Home.DataManipualtion.DataSelect cover = new com.maan.Home.DataManipualtion.DataSelect();
			final com.maan.Home.DataManipualtion.HomePdfDataSelect pdfDataSelects = new com.maan.Home.DataManipualtion.HomePdfDataSelect();
			response.setContentType(CONTENT);
			final HttpSession session = request.getSession(true);
			final String path = request.getContextPath();
			
			if (session.getAttribute("ses") == null) {
				response.sendRedirect(path + "/login/error_messg.jsp");
				return;
			}
			DBConnectionStatus.statusStatic = isNull((String) session.getAttribute("userLoginMode"));
			String proId = "";
			if (session.getAttribute("product_id") != null) {
				proId = (String) session.getAttribute("product_id");
				session.setAttribute("product_id", proId);
			}
			String quoteNo = isNull(request.getParameter("QUOTENO"));
			if ("".equals(quoteNo) || quoteNo.length() <= 0){
				quoteNo = isNull(request.getParameter(QUOTENO));
			}
			if ("".equals(quoteNo) || quoteNo.length() <= 0){
				quoteNo = isNull((String)request.getAttribute(QUOTENO));}
			if ("".equals(quoteNo) || quoteNo.length() <= 0){
				quoteNo = isNull((String) session.getAttribute(QUOTENO));}
			
			session.setAttribute(QUOTENO, quoteNo);
			session.setAttribute("PDFQuoteNo", quoteNo);
			final String values[][] = cover.getLoginProId(quoteNo);
			final String loginId = values[0][0];
			String refStatus = isNull(request.getParameter("quote_status"));
			if ("".equals(refStatus) || refStatus.length() <= 0){
				refStatus = isNull((String)request.getAttribute("quote_status"));}
			session.setAttribute("quote_status", refStatus);
			String adminRemarks = isNull(request.getParameter("adminReferalRemarks"));
			if ("".equals(adminRemarks) || adminRemarks.length() <= 0){
				adminRemarks = isNull((String)request.getAttribute("adminReferalRemarks"));}
			session.setAttribute("adminReferalRemarks",	adminRemarks);
			
			final String btoc = cover.getBTOCQuote(quoteNo);
			String reqFrom =  isNull(request.getParameter("reqFrom"));
			if ("".equals(reqFrom) || reqFrom.length() <= 0){
				reqFrom = isNull((String)request.getAttribute("reqFrom"));}
			//final String productTypes =  isNull(request.getParameter("productTypes"),"Home");
			final String productTypes =  isNull(request.getParameter("productTypes"),("31".equalsIgnoreCase(proId)||"32".equalsIgnoreCase(proId)||"33".equalsIgnoreCase(proId))?"Travel":"Health");
			final String branch = (String) session.getAttribute("LoginBranchCode");
			
			final StringBuffer emailMsgTxt = new StringBuffer();
			final StringBuffer fullAddr = new StringBuffer();
			final StringBuffer emailSubjectTxt = new StringBuffer(100);
			Map rDetails = new HashMap();
			final Map attachMap = new HashMap();
			HashMap brokerDetails = (HashMap) session.getAttribute("BrokerDetails");
			String currencyType = "";
			if (brokerDetails.size() > 0) {
				currencyType = (String) brokerDetails.get("CurrencyAbb");
			}
			//
			String place = "";
			String headImage = "";
			String footImage = "";
			String signImage = "";
			String branchAddress = "";
			String branchName = "";
			String branchPO = "";
			String branchCountry = "";
			String branchCity = "";
			String currencyName = "";
			String placeCode[][] = new String[0][0];
			HomePdfDataSelect hpdf = new HomePdfDataSelect();
			placeCode = hpdf.getPlaceCodes(branch, proId);
			
			if (placeCode.length > 0) 
			{
				place = placeCode[0][0] == null ? "" : placeCode[0][0];
				headImage = placeCode[0][1] == null ? "" : placeCode[0][1];
				footImage = placeCode[0][2] == null ? "" : placeCode[0][2];
				signImage = placeCode[0][3] == null ? "" : placeCode[0][3];
				currencyName = placeCode[0][5] == null ? "" : placeCode[0][5];
				currencyType = placeCode[0][6] == null ? "" : placeCode[0][6];
				branchName = placeCode[0][8] == null ? "" : placeCode[0][8];
				branchPO = placeCode[0][10] == null ? "" : placeCode[0][10];
				branchCity = placeCode[0][11] == null ? "" : placeCode[0][11];
				branchCountry = placeCode[0][12] == null ? "": placeCode[0][12];
				/*stampImage = placeCode[0][4] == null ? "" : placeCode[0][4];
				cols = placeCode[0][9] == null ? "" : placeCode[0][9];
				branchPhone = placeCode[0][13] == null ? "" : placeCode[0][13];
				branchFax = placeCode[0][14] == null ? "" : placeCode[0][14];*/
			}
			branchAddress = branchPO + ", " + branchCity + ", " + branchCountry+ ".";
			
			//
			if(SCHEDULE.equalsIgnoreCase(reqFrom)||"Portfolio".equalsIgnoreCase(reqFrom)){
				String pwordingFName  = "";
				if("Travel".equalsIgnoreCase(productTypes)){
					pwordingFName = "Travel_Policy";
				}else if("Health".equalsIgnoreCase(productTypes)){
					pwordingFName = "Health_Policy";					
				}else{
					pwordingFName  = cover.getPolicyWordingFileName(proId,branch);
				}
				if(pwordingFName.length()<=0){
					pwordingFName = "Policy_Wording_Standard";}
				final StringBuffer policyNames = new StringBuffer(100);
				//policyNames.append(request.getContextPath()+"/PDFFile/PolicyWording/");
				policyNames.append(request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/PDFFile/PolicyWording/");
				policyNames.append(pwordingFName);
				policyNames.append(pdf);
				String filePath = "";
				if(btoc.equalsIgnoreCase("BTOC"))
				{	
						fullAddr.append("XYZ Insurance <br> <br> Contact details <br>Call centre:<br> 800 - 772 <br>	8.00 am to 8.00 pm(Sunday to Thursday)<br>" +
								"Claims: <br>Tel 04 3029995 Fax 04 3348851 <br>	claims@XYZInsurance.com <br><br>Branch address:<br> XYZ Insurance <br>Ground Floor,Office Court" +
								"<br>Oud Mehta Road <br>PO Box 28648<br>Dubai, UAE");
						emailSubjectTxt.append("Your 'Home Contents' insurance policy");
						emailMsgTxt.append("<font style='font-family: Arial, Helvetica, sans-serif' size = 2 face = 'arial'><b>Dear customer,</b><br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Thank you for insuring your Home Contents with XYZ Insurance. Please find enclosed your Policy schedule and Policy booklet.<br> Please contact us for any queries and we will be glad to be assist you.<br><br>Regards<br>");
						emailMsgTxt.append(fullAddr.toString());
						emailMsgTxt.append(FONT); 
				}
				else
				{
					
					if(productTypes.equalsIgnoreCase("Travel")){
						emailSubjectTxt.append("Travel Insurance -- Policy Schedule");
						filePath = "/TravelPDFFile";
					}else if("Health".equalsIgnoreCase(productTypes)){
						emailSubjectTxt.append("Health Insurance -- Policy Schedule");
						filePath = "/HealthPDFFile";
					}else{
						emailSubjectTxt.append("Home Insurance -- Policy Schedule");
						filePath = "/PDFFile";
					}
					final String messs[][] = pdfDataSelects.brokerCompanyMasterSelectForMail(loginId);
		            if(messs != null && messs.length >0 )
					{
		            	fullAddr.append(isNullBr(messs[0][7]));
		            	fullAddr.append(isNullBr( messs[0][6]));                            
		            	fullAddr.append( isNullBr(messs[0][0]));
		            	fullAddr.append(isNullBr(messs[0][1]));
		            	fullAddr.append(isNullBr( messs[0][2]));
		            	fullAddr.append(isNullBr( messs[0][3]));
		            	fullAddr.append( isNullBr(messs[0][4]));
		            	fullAddr.append(isNullBr(messs[0][5]));
		            	fullAddr.append(isNullBr( messs[0][8]));
					}
		            emailMsgTxt.append("<font style='font-family: Arial, Helvetica, sans-serif' size = 2 face = 'arial'>Dear Sir<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Thank you for insuring with XYZ Insurance. Please find attached your "+productTypes+" Insurance Policy Schedule. In case of any query, please contact the undersigned. " +
		            		"Please click on the link to access your <a href='");
		            emailMsgTxt.append(policyNames.toString());
		            emailMsgTxt.append("'>Policy Booklet</a><br><br><br>Regards<br>");
		            emailMsgTxt.append(fullAddr.toString());
		            emailMsgTxt.append("</font><br><br><br>");
				}
				final String[] emailList = {isNull(cover.getBTOCQuoteCustomerMailId(quoteNo))};
				rDetails.put(EMAILLIST,emailList);
				rDetails.put(CCADDRESS, new Object[0]);
				final String mailFrom[][] = cover.getFromEmailForLogin(loginId);
				rDetails.put(EMAILID,isNull(mailFrom[0][0]));
				rDetails.put(SUBJ,isNull(mailFrom[0][1]));
				attachMap.put(QUOTENO, quoteNo);
				attachMap.put(SCHEDULE, request.getSession().getServletContext().getRealPath("/"+filePath+"/Schedule/PremiumSummary_Schedule_"));
				/*attachMap.put("PolicyWording", request.getSession().getServletContext().getRealPath("/"+"PDFFile/PolicyWording"));
				attachMap.put("pwordingFName", pwordingFName);*/
				
				if(reqFrom.equalsIgnoreCase("portfolio")){
					
					final String pdfFilePath = request.getSession().getServletContext().getRealPath(filePath+"/Schedule/PremiumSummary_Schedule_"+quoteNo+pdf);
					final File checkFile = new File(pdfFilePath);
					if(!checkFile.exists())
					{
						if(productTypes.equalsIgnoreCase("Travel")){
							final com.maan.Travel.Pdf.PDFCreatorBean  creatorBean = new com.maan.Travel.Pdf.PDFCreatorBean();
							creatorBean.setPlace(place);
							creatorBean.setBranchName(branchName);
							creatorBean.setBranch(branch);
							creatorBean.setBranchAddress(branchAddress);
							creatorBean.setCurrencyName(currencyName);
							creatorBean.setPid(proId);
							creatorBean.setQuoteNo(quoteNo);
							creatorBean.setLoginId(loginId);
							creatorBean.setPdfDisplay("");
							creatorBean.setCurrencyType(currencyType);
							creatorBean.setHeaderImagePath(request.getSession().getServletContext().getRealPath("/"+"/images/" + headImage));
							creatorBean.setFooterImagePath(request.getSession().getServletContext().getRealPath("/"+"/images/" + footImage));
							creatorBean.setFilePath(request.getSession().getServletContext().getRealPath("/"+"/"+filePath+"/Schedule/PremiumSummary_Schedule_"+quoteNo+pdf));
							creatorBean.setSignedImagePath(request.getSession().getServletContext().getRealPath("/"+"/images/" + signImage));
							creatorBean.writePDF(SCHEDULE);
						}else if("Health".equalsIgnoreCase(productTypes)){
							final com.maan.Health.Pdf.HealthPDFCreatorBean  creatorBean = new com.maan.Health.Pdf.HealthPDFCreatorBean();
							creatorBean.setPlace(place);
							creatorBean.setBranchName(branchName);
							creatorBean.setBranch(branch);
							creatorBean.setBranchAddress(branchAddress);
							creatorBean.setCurrencyName(currencyName);
							creatorBean.setPid(proId);
							creatorBean.setQuoteNo(quoteNo);
							creatorBean.setLoginId(loginId);
							creatorBean.setPdfDisplay("");
							creatorBean.setCurrencyType(currencyType);
							creatorBean.setHeaderImagePath(request.getSession().getServletContext().getRealPath("/"+"/images/" + headImage));
							creatorBean.setFooterImagePath(request.getSession().getServletContext().getRealPath("/"+"/images/" + footImage));
							creatorBean.setFilePath(request.getSession().getServletContext().getRealPath("/"+"/"+filePath+"/Schedule/PremiumSummary_Schedule_"+quoteNo+pdf));
							creatorBean.setSignedImagePath(request.getSession().getServletContext().getRealPath("/"+"/images/" + signImage));
							creatorBean.writePDF(SCHEDULE);
						}
						else{
							final com.maan.Home.MasterController.PDFCreatorBean  creatorBean = new com.maan.Home.MasterController.PDFCreatorBean();
							creatorBean.setPlace(place);
							creatorBean.setBranchName(branchName);
							creatorBean.setBranchAddress(branchAddress);
							creatorBean.setCurrencyName(currencyName);
							creatorBean.setPid(proId);
							creatorBean.setQuoteNo(quoteNo);
							creatorBean.setLoginId(loginId);
							creatorBean.setPdfDisplay("");
							creatorBean.setCurrencyType(currencyType);
							creatorBean.setHeaderImagePath(request.getSession().getServletContext().getRealPath("/"+"/images/" + headImage));
							creatorBean.setFooterImagePath(request.getSession().getServletContext().getRealPath("/"+"/images/" + footImage));
							creatorBean.setFilePath(request.getSession().getServletContext().getRealPath("/"+"/"+filePath+"/Schedule/PremiumSummary_Schedule_"+quoteNo+pdf));
							creatorBean.setSignedImagePath(request.getSession().getServletContext().getRealPath("/"+"/images/" + signImage));
							creatorBean.writePDF(SCHEDULE);
						}
					}
				}
			}
			else if("Customer".equalsIgnoreCase(reqFrom)){
				if(fullAddr.length()>0){
					fullAddr.delete(0, fullAddr.length());
					emailMsgTxt.delete(0, emailMsgTxt.length());
				}
				if(btoc.equalsIgnoreCase("BTOC"))
				{	
					fullAddr.append("XYZ Insurance <br> <br> Contact details <br>Call centre:<br> 800 - 772 <br>	8.00 am to 8.00 pm(Sunday to Thursday)<br>" +
							"Branch address:<br> XYZ Insurance <br>Ground Floor,Office Court 	<br>Oud Mehta Road <br>PO Box 28648<br>Dubai, UAE"); 
					emailMsgTxt.append("<font style='font-family: Arial, Helvetica, sans-serif' size = 2 face = 'arial'><b>Dear customer,</b><br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; We are pleased to offer a quotation for your 'Home Contents' insurance. If you wish to convert your quotation and purchase the policy please click here.<br>Please contact the undersigned for any queries by mentioning your Quote number.<br><br>Regards,<br>");
					emailMsgTxt.append(fullAddr.toString());
					emailMsgTxt.append(FONT);
					emailSubjectTxt.append("Your 'Insurance Quotation' from XYZ Insurance");
				}
				else
				{
					final String messs[][] = pdfDataSelects.brokerCompanyMasterSelectForMail(loginId);
		            if(messs.length >0 )
					{
		            	fullAddr.append(isNullBr(messs[0][7]));
		            	fullAddr.append(isNullBr( messs[0][6]));                            
		            	fullAddr.append( isNullBr(messs[0][0]));
		            	fullAddr.append(isNullBr(messs[0][1]));
		            	fullAddr.append(isNullBr( messs[0][2]));
		            	fullAddr.append(isNullBr( messs[0][3]));
		            	fullAddr.append( isNullBr(messs[0][4]));
		            	fullAddr.append(isNullBr(messs[0][5]));
		            	fullAddr.append(isNullBr( messs[0][8]));
					}
		            final String subj = cover.productDes(proId,branch)+" " +"Quotation";
		            emailSubjectTxt.append(subj);
		            emailMsgTxt.append("<font style='font-family: Arial, Helvetica, sans-serif' size = 2 face = 'arial'><b>Dear Sir/Madam</b><br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; We are pleased to offer the quotation for your ");
		            emailMsgTxt.append(subj);
		            emailMsgTxt.append(". In case of any query please contact at the under mentioned address.<br><br>Regards<br>");
		            emailMsgTxt.append(fullAddr.toString());
		            emailMsgTxt.append(FONT);
				}
				
				final String[] emailList = {isNull(cover.getBTOCQuoteCustomerMailId(quoteNo))};
				rDetails.put(EMAILLIST,emailList);
				rDetails.put(CCADDRESS, new Object[0]);
				final String mailFrom[][] = cover.getFromEmailForLogin(loginId);
				rDetails.put(EMAILID,isNull(mailFrom[0][0]));
				rDetails.put(SUBJ,isNull(mailFrom[0][1]));
				attachMap.put(QUOTENO, quoteNo);
				String filePath ="";
				if(productTypes.equalsIgnoreCase("Home")){
					filePath = "PDFFile/Quote";
				}else{
					filePath = "TravelPDFFile/PrintQuote";
				}
				
				attachMap.put(SCHEDULE, request.getSession().getServletContext().getRealPath("/"+filePath+"/PremiumSummary_Quote_"));
				final String pdfFilePath = request.getSession().getServletContext().getRealPath(filePath+"/PremiumSummary_Quote_"+quoteNo+pdf);
				final File checkFile = new File(pdfFilePath);
				if(!checkFile.exists())
				{
					if(productTypes.equalsIgnoreCase("Home")){
						final com.maan.Home.MasterController.PDFCreatorBean  creatorBean = new com.maan.Home.MasterController.PDFCreatorBean();
						creatorBean.setPlace(place);
						creatorBean.setBranchName(branchName);
						creatorBean.setBranch(branch);
						creatorBean.setBranchAddress(branchAddress);
						creatorBean.setCurrencyName(currencyName);
						creatorBean.setQuoteNo(quoteNo);
						creatorBean.setLoginId(loginId);
						creatorBean.setPid(proId);
						creatorBean.setCurrencyType(currencyType);
						creatorBean.setHeaderImagePath(request.getSession().getServletContext().getRealPath("/"+"/images/" + headImage));
						creatorBean.setFooterImagePath(request.getSession().getServletContext().getRealPath("/"+"/images/" + footImage));
						creatorBean.setFilePath(request.getSession().getServletContext().getRealPath("/"+filePath+"/PremiumSummary_Quote_"+quoteNo+pdf));
						creatorBean.setSignedImagePath(request.getSession().getServletContext().getRealPath("/"+"/images/" + signImage));
						creatorBean.setBetterImagePath(request.getSession().getServletContext().getRealPath("/"+"/images/betterImage.jpg"));
						creatorBean.setEmptyImage(request.getSession().getServletContext().getRealPath("/"+"/images/EmptyImage.jpg"));
						creatorBean.writePDF("PrintQuote");
					}else if(productTypes.equalsIgnoreCase("Travel")){
						final  com.maan.Travel.Pdf.PDFCreatorBean  creatorBean = new com.maan.Travel.Pdf.PDFCreatorBean();
						creatorBean.setFormatpath(request.getSession().getServletContext().getRealPath("/"+"/ScheduleFont/arialuni.ttf"));
						creatorBean.setPlace(place);
						creatorBean.setBranchName(branchName);
						creatorBean.setBranch(branch);
						creatorBean.setBranchAddress(branchAddress);
						creatorBean.setCurrencyName(currencyName);
						creatorBean.setQuoteNo(quoteNo);
						creatorBean.setLoginId(loginId);
						creatorBean.setPid(proId);
						creatorBean.setCurrencyType(currencyType);
						creatorBean.setHeaderImagePath(request.getSession().getServletContext().getRealPath("/"+"/images/" + headImage));
						creatorBean.setFooterImagePath(request.getSession().getServletContext().getRealPath("/"+"/images/" + footImage));
						creatorBean.setFilePath(request.getSession().getServletContext().getRealPath("/"+filePath+"/PremiumSummary_Quote_"+quoteNo+pdf));
						creatorBean.setSignedImagePath(request.getSession().getServletContext().getRealPath("/"+"/images/" + signImage));
						//creatorBean.writePDF("PrintQuote");
						creatorBean.writePDF("Draft");
					}else if(productTypes.equalsIgnoreCase("Health")){
						final  com.maan.Health.Pdf.HealthPDFCreatorBean  creatorBean = new com.maan.Health.Pdf.HealthPDFCreatorBean();
						creatorBean.setFormatpath(request.getSession().getServletContext().getRealPath("/"+"/ScheduleFont/arialuni.ttf"));
						creatorBean.setPlace(place);
						creatorBean.setBranchName(branchName);
						creatorBean.setBranch(branch);
						creatorBean.setBranchAddress(branchAddress);
						creatorBean.setCurrencyName(currencyName);
						creatorBean.setQuoteNo(quoteNo);
						creatorBean.setLoginId(loginId);
						creatorBean.setPid(proId);
						creatorBean.setCurrencyType(currencyType);
						creatorBean.setHeaderImagePath(request.getSession().getServletContext().getRealPath("/"+"/images/" + headImage));
						creatorBean.setFooterImagePath(request.getSession().getServletContext().getRealPath("/"+"/images/" + footImage));
						creatorBean.setFilePath(request.getSession().getServletContext().getRealPath("/"+filePath+"/PremiumSummary_Quote_"+quoteNo+pdf));
						creatorBean.setSignedImagePath(request.getSession().getServletContext().getRealPath("/"+"/images/" + signImage));
						//creatorBean.writePDF("PrintQuote");
						creatorBean.writePDF("Draft");
						
					}
				}
			}
			else{
				fullAddr.delete(0, fullAddr.length());
				emailMsgTxt.delete(0, emailMsgTxt.length());
				if("Referral".equalsIgnoreCase(reqFrom)){
						emailMsgTxt.append("<b></b><br> <font style='font-family: Arial, Helvetica, sans-serif' size = 2 face = 'arial'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
							"This e-mail contains an automated underwriting referral from the on-line "+productTypes+" System for Quote Number:&nbsp;<b>");
						emailMsgTxt.append(quoteNo);
						emailMsgTxt.append("</b>. &nbsp;&nbsp;Please respond as soon as possible as the client is awaiting a decision.<br></font>");
						if(productTypes.equalsIgnoreCase("Home")){
							emailSubjectTxt.append("HOME INSURANCE REFERRAL");
						}
						else if(productTypes.equalsIgnoreCase("Travel")){
							emailSubjectTxt.append("TRAVEL INSURANCE REFERRAL");
						}else if(productTypes.equalsIgnoreCase("Health")){
							emailSubjectTxt.append("HEALTH INSURANCE REFERRAL");
						}
				}
				else if (btoc.equalsIgnoreCase("BTOC")&&ADMIN.equalsIgnoreCase(reqFrom)){
						emailMsgTxt.append("<b></b><br> <font style='font-family: Arial, Helvetica, sans-serif' size = 2 face = 'arial'> <br><b>Dear customer,</b><br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Thank you for your interest in XYZ Insurance. We have reviewed your request and are pleased to offer you a revised quotation for your 'Home Contents' insurance. If you wish to convert your quotation and purchase the policy please click here." +
								"<br> Please contact the undersigned for any queries by mentioning your Quote number.<br> This Quote is ");
						emailMsgTxt.append(refStatus);
						emailMsgTxt.append(" by Admin.</font>");
						emailSubjectTxt.append("Quote number ");
						emailSubjectTxt.append(quoteNo);
						emailSubjectTxt.append(", XYZ Insurance ");
					fullAddr.append("XYZ Insurance <br> <br>");
					fullAddr.append(SPACE);
					fullAddr.append(" Contact details <br>"); 
					fullAddr.append(SPACE);
					fullAddr.append("Call centre:<br>");
					fullAddr.append(SPACE);
					fullAddr.append(" 800 - 772 <br>");
					fullAddr.append(SPACE);
					fullAddr.append("8.00 am to 8.00 pm(Sunday to Thursday)<br>");
					fullAddr.append(SPACE);
					fullAddr.append("Branch address:<br>");
					fullAddr.append(SPACE);
					fullAddr.append(" XYZ Insurance <br>");
					fullAddr.append(SPACE);
					fullAddr.append("Ground Floor,Office Court<br>");
					fullAddr.append(SPACE);
					fullAddr.append("Oud Mehta Road <br>");
					fullAddr.append(SPACE);
					fullAddr.append("PO Box 28648<br>");
					fullAddr.append(SPACE);
					fullAddr.append("Dubai, UAE");
					//toAdd = Cover.getBTOCQuoteCustomerMailId(QuoteNo);
				} else if(ADMIN.equalsIgnoreCase(reqFrom)) {
					emailMsgTxt.append("<b></b><br> <font style='font-family: Arial, Helvetica, sans-serif' size = 2 face = 'arial'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;This e-mail contains an automated underwriting referral from the on-line "+productTypes+" System for Quote Number:&nbsp;<b>");
					emailMsgTxt.append(quoteNo);
					emailMsgTxt.append("</b>. &nbsp;&nbsp; This Quote is ");
					emailMsgTxt.append(refStatus);
					emailMsgTxt.append(" by Admin . <br></font>");
					if(productTypes.equalsIgnoreCase("Home")){
						emailSubjectTxt.append("HOME INSURANCE REFERRAL RESPONSE");
					}else if(productTypes.equalsIgnoreCase("Travel")){
						emailSubjectTxt.append("TRAVEL INSURANCE REFERRAL RESPONSE");
					}else if(productTypes.equalsIgnoreCase("Health")){
						emailSubjectTxt.append("HEALTH INSURANCE REFERRAL RESPONSE");
					}
					
					fullAddr.append(ADMIN);
					//toAdd = (String) rDetails.get("emailto");
				}
				if(productTypes.equalsIgnoreCase("Home")){
					emailMsgTxt.append(getHomeMailContent(request,response,quoteNo,proId,loginId,branch,reqFrom,adminRemarks,fullAddr.toString()));
				}else if(productTypes.equalsIgnoreCase("Travel")){
					emailMsgTxt.append(getTravelMailContent(request,response,quoteNo,loginId,branch,reqFrom,adminRemarks,fullAddr.toString()));
				}else if(productTypes.equalsIgnoreCase("Health")){
					emailMsgTxt.append(getHealthMailContent(request,response,quoteNo,loginId,branch,reqFrom,adminRemarks,fullAddr.toString()));
				}
				
				//rDetails = cover.getReferralDetails(branch);
				rDetails = cover.getReferralDetails(branch,productTypes);
				
				final StringTokenizer ccToken = new StringTokenizer(isNull((String)rDetails.get("emailcc")), ",");
				final ArrayList ccAddress = new ArrayList();
				while (ccToken.hasMoreElements()) {
					ccAddress.add((String)ccToken.nextElement());
				}
				
				System.out.println("ccAddress: "+ccAddress.size());
				System.out.println("ccAddress Array: "+ccAddress.toArray().length);
				if("Referral".equalsIgnoreCase(reqFrom)){
					final String[] emailList = {isNull((String)rDetails.get("emailto"))};
					rDetails.put(EMAILLIST,emailList);
					rDetails.put(CCADDRESS,ccAddress.toArray());
					final String mailFrom[][] = cover.getFromEmailForLogin(loginId);
					rDetails.put(EMAILID,isNull(mailFrom[0][0]));
					rDetails.put(SUBJ,isNull(mailFrom[0][1]));
				}
				else if(ADMIN.equalsIgnoreCase(reqFrom)){
					
					final String mailIds = pdfDataSelects.getAdminBrokerMailId(quoteNo);
					ccAddress.add(mailIds.substring(0, mailIds.indexOf(',')));
					final String[] emailList = {mailIds.substring(mailIds.indexOf(',') + 1,mailIds.length())};
					rDetails.put(EMAILLIST,emailList);
					rDetails.put(CCADDRESS,ccAddress.toArray());
					//rDetails.put(EMAILID,ADMIN);
					rDetails.put(EMAILID,isNull((String)rDetails.get("emailto")));
					rDetails.put(SUBJ,cover.productDes(proId, branch));
				}
			}
			
			System.out.println("rDetails.get(EMAILLIST): "+rDetails.get(EMAILLIST)+" rDetails.get(CCADDRESS): "+rDetails.get(CCADDRESS)+"rDetails.get(EMAILID): "+rDetails.get(EMAILID)+" rDetails.get(SUBJ): "+rDetails.get(SUBJ));
			
			final HomeMailController home = new HomeMailController();
			
			String basePaths = request.getSession().getServletContext().getRealPath("/"+"MailServerInfo/MailServerInfo.xml");
			if("Portfolio".equalsIgnoreCase(reqFrom)||SCHEDULE.equalsIgnoreCase(reqFrom)){
				String toAddress[] = ((String[])rDetails.get(EMAILLIST));
				if(toAddress!=null && toAddress[0].length()>0){
					home.postMail((String[])rDetails.get(EMAILLIST), toAddress,
							emailSubjectTxt.toString(),emailMsgTxt.toString(), (String)rDetails.get(EMAILID),
							(String)rDetails.get(SUBJ),branch,basePaths,attachMap);
					//response.sendRedirect(path+"/TravelInsurance/MailToTravelCustomer.jsp?reqFrom="+reqFrom);
				}
				else{
					//response.sendRedirect(path+"/HomeInsurance/Messagenotsending.jsp");
				}
			}
			else if("Customer".equalsIgnoreCase(reqFrom)){
				final String toAdd[] = (String[])rDetails.get(EMAILLIST);
				System.out.println("toAdd[0]: "+toAdd[0]);
				if(toAdd[0].length()>1){
					home.postMail((String[])rDetails.get(EMAILLIST), (Object[])rDetails.get(CCADDRESS),
							emailSubjectTxt.toString(),emailMsgTxt.toString(), (String)rDetails.get(EMAILID),
							(String)rDetails.get(SUBJ),branch,basePaths,attachMap);
					if(productTypes.equalsIgnoreCase("Travel")||productTypes.equalsIgnoreCase("Health")){
						String requestFrom = isNull(request.getParameter("requestfrom"));
					    String reqFromFinal =  isNull(request.getParameter("reqFromFinal"));
					    String modeOfPurchase =  isNull(request.getParameter("modeOfPurchase"));
						if(reqFromFinal.length()>0)
						{
							request.setAttribute("reqFrom",reqFromFinal);
							request.setAttribute("modeOfPurchase",modeOfPurchase);
							response.sendRedirect(path+"/TravelInsurance/MailToTravelCustomer.jsp");
						}
						else
						{
							request.setAttribute("modeOfPurchase",modeOfPurchase);
							request.setAttribute("reqFrom",requestFrom);
							//response.sendRedirect(path+"/TravelInsurance/MailToTravelCustomer.jsp");
						}
					}else{
						response.sendRedirect(path+"/HomeInsurance/Mail2HomeCustomer.jsp");
					}
				}
				else{
					response.sendRedirect(path+"/HomeInsurance/Messagenotsending.jsp");
				}
			}
			else{	
				
				home.postMail((String[])rDetails.get(EMAILLIST), (Object[])rDetails.get(CCADDRESS),
						emailSubjectTxt.toString(),emailMsgTxt.toString(), (String)rDetails.get(EMAILID),
						(String)rDetails.get(SUBJ),branch,basePaths,attachMap);
			}
				
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
			//throw new BaseException(e, "Error");
		}
	}
	// create some properties and get the default Session
	public void postMail(final String recipients[], final Object ccreceipients[],
			final String subject, final String message, final String emailFromAddress, final String subj,final String branch,
			final String path,final Map attachMap)
			throws MessagingException,Exception {
		try{
			final com.maan.common.MailServerInfo mailInfo = new com.maan.common.MailServerInfo();
			final HashMap PortDet = mailInfo.getMailServerInfo(branch,path);
			final String smtpHostName = (String) PortDet.get("hostname");
			smtpAuthUser = (String) PortDet.get("username");
			smtpAuthPwd = (String) PortDet.get("password");
			final boolean debug = false;
			// Set the host smtp address
			final Properties props = new Properties();
			props.put("mail.smtp.auth", "true");
	  		  props.put("mail.smtp.starttls.enable", "true");
	  		  props.put("mail.smtp.host", smtpHostName);
			  props.put("mail.smtp.port", "587");
			final Authenticator auth = new SMTPAuthenticator();
			final Session session = Session.getInstance(props, auth);
			session.setDebug(debug);
			// create a message
			final Message msg = new MimeMessage(session);
			// set the from and to address
			final InternetAddress addressFrom = new InternetAddress(ADMIN.equalsIgnoreCase(emailFromAddress)?(String)PortDet.get("Address"):emailFromAddress,subj);
			msg.setFrom(addressFrom);
			if (recipients.length>0) {
				InternetAddress[] addressTo = new InternetAddress[recipients.length];
				for (int i = 0; i < recipients.length; i++) {
					addressTo[i] = new InternetAddress(recipients[i]);
					msg.addRecipient(Message.RecipientType.TO, addressTo[i]);
				}

			}
			if (ccreceipients.length>0) {
				
				InternetAddress[] addressToCC = new InternetAddress[ccreceipients.length];
				for (int i = 0; i < ccreceipients.length; i++) {
					addressToCC[i] = new InternetAddress((String)ccreceipients[i]);
					msg.addRecipient(Message.RecipientType.CC,addressToCC[i]);
				}
			}
			msg.setSubject(subject);
			if(attachMap.size()>1){
				final Multipart multiPart = new MimeMultipart();
			    multiPart.addBodyPart(getHtmlMailContent(message));
				multiPart.addBodyPart(getMailAttachment((String)attachMap.get(SCHEDULE),(String)attachMap.get(QUOTENO)));
				//multiPart.addBodyPart(getMailAttachment(path2,pfname,"royal"));
			    msg.setContent(multiPart);
			}
			else{
				msg.setContent(message, CONTENT);
			}
			Transport.send(msg);
	}catch(MessagingException Me)
	{
			LogManager.push("Mailing Exception: "+Me);
	}
	catch(Exception e)
	{
		LogManager.push("Mail Exception: "+e);
	}
}
	public BodyPart getMailAttachment(final String path ,final String qnum) throws MessagingException
	{
		BodyPart messageAttach = null;
		try
		{
        	messageAttach = new MimeBodyPart();
		    final DataSource source = new FileDataSource(path+qnum+pdf);
		    messageAttach.setDataHandler(new DataHandler(source));		
		    //final String fileName = (path+qnum+pdf).substring((path+qnum+pdf).lastIndexOf('/')+1);
		    final String fileName = (path+qnum+pdf).substring((path+qnum+pdf).lastIndexOf("_")+1);
		    messageAttach.setFileName(fileName);
        }
        catch(Exception e)
		{
			LogManager.debug(e);
        }
		return messageAttach;
	}
	public MimeBodyPart getHtmlMailContent(final String content) throws MessagingException
    {
		final MimeBodyPart messageBody1 = new MimeBodyPart();
       messageBody1.setContent(content,CONTENT);     
	   return messageBody1;
    }
	private class SMTPAuthenticator extends javax.mail.Authenticator {
		public PasswordAuthentication getPasswordAuthentication() {
			final String username = smtpAuthUser;
			final String password = smtpAuthPwd;
			return new PasswordAuthentication(username, password);
		}
	}
	
}
