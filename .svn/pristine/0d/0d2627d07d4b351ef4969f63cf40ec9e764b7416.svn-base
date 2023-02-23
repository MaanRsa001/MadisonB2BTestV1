package com.maan.Office.Mail;

import java.sql.*;
import java.io.PrintWriter;
import java.util.*;

import com.maan.common.error.ErrorInfo;
import com.maan.services.util.runner;
import com.maan.Office.DAO.OfficeShieldBean;

public class OfficeMailBean extends ErrorInfo 
{
	private PrintWriter out = null;
	private Statement smt = null;
	private PreparedStatement psmt = null;
	private ResultSet rs = null;

	private String branch = "";
	private String premium = "";
	private String brokerCompany = "";
	private	String namedInterest = "";
	private String insuredLocation = "";
	private String customerName = "";
	private String content = "";
	private String profession = "";
	private String freezone = "";
	private String dob = "";
	private String occupation = "";
	private String nationality = "";
	private String emirate = "";
	private String pobox = "";
	private String tphone = "";
	private String mobile = "";
	private String fax = "";
	private String email = "";
	private String loginId = "";
	private String optionUW = "";
	private String proId = "";
	private String schemeId = "";
	private String inceptionDate = "";
	private String currencyType = "";
	public void setProId(String proId)
	{
		this.proId = proId;
	}

	public String getProId()
	{
		return proId;
	}

	public void setSchemeId(String schemeId)
	{
		this.schemeId = schemeId;
	}

	public String getSchemeId()
	{
		return schemeId;
	}
	
	public void setBranch(String branch)
	{
		this.branch = branch;
	}

	public String getBranch()
	{
		return branch;
	}

	public void setCurrencyType(String currencyType)
	{
		this.currencyType = currencyType;
	}

	public String getCurrencyType()
	{
		return currencyType;
	}
	
	public String officeBodyOfMsg(String quoteNo, String status, String mailFrom)
	{
		String msg = "";
		StringBuffer mailDatas = new StringBuffer();
		StringBuffer uwQuest = new StringBuffer();
		String policyInfo[][] = new String[0][0];
		String coverageSumInfo [][] = new String[0][0];
		String coverageUploadInfo [][] = new String[0][0];
		String FidelityUW [][] = new String[0][0];
		OfficeShieldBean OSB = new OfficeShieldBean();
		String header = "";
		String referalDescription = "";
		String adminRemarks = "";
		String referalReason = "";
		String schemeName = "";
		HashMap schemeHash = new HashMap();
		String brokerAddress = "";
		String detailsOfBroker[][] = new String[0][0];
		
		schemeId = OSB.getSchemeId(quoteNo,proId);
		schemeHash = OSB.getOFSSchemeMaster(branch,proId);
		
		if(schemeHash.size() > 0)
			schemeName = (String)schemeHash.get(schemeId);
		
		boolean uwFlag = false;
		String optionUW = "";
		String temp = "";
		java.text.NumberFormat amountFormat=new java.text.DecimalFormat("##,##0") ;
		try
		{
			if(quoteNo.length() > 0)
			{
				loginId = getLoginIdForQno(quoteNo);
				System.out.println("Login id ... OMB.."+loginId);
				if( (quoteNo.length() > 0) && !quoteNo.equalsIgnoreCase("null") && !quoteNo.equalsIgnoreCase("") && quoteNo != null && (loginId.length() > 0) && !loginId.equalsIgnoreCase("null") && !loginId.equalsIgnoreCase("") && loginId != null)
				{
					policyInfo = OSB.getPolicyInfo(quoteNo,loginId);
					coverageSumInfo = OSB.getCoverageInfo(quoteNo,loginId);
					//coverageUploadInfo = OSB.getCoverageUploadOption(quoteNo,loginId);
				}
				
				detailsOfBroker = brokerPersonalDetailsForCustomerMailQuote(quoteNo);
				System.out.println(".policyInfo   ."+policyInfo.length);
				mailDatas.append("<html>");
				mailDatas.append("<head></head>");
				mailDatas.append("<link rel='stylesheet' href='\\E-Cargo\\css\\rsa.css' type='text/css'>");
				mailDatas.append("<body>");
				
				if( policyInfo.length > 0 )
				{
					System.out.println(" Office Mail Bean policyInfo ...length.."+policyInfo.length);

					brokerCompany = policyInfo[0][10] == null ? "" :policyInfo[0][10];
					namedInterest = policyInfo[0][9] == null ? "" :policyInfo[0][9];
					premium = policyInfo[0][11] == null ? "" :policyInfo[0][11];
					customerName = policyInfo[0][2] == null ? "" :policyInfo[0][2];
					insuredLocation = policyInfo[0][4] == null ? "" :policyInfo[0][4];
					content = policyInfo[0][6] == null ? "" :policyInfo[0][6];
					profession = policyInfo[0][7] == null ? "" :policyInfo[0][7];
					freezone = policyInfo[0][8] == null ? "" :policyInfo[0][8];
					dob = policyInfo[0][12] == null ? "" :policyInfo[0][12]; //dob 12
					occupation = policyInfo[0][13] == null ? "" :policyInfo[0][13]; 
					nationality = policyInfo[0][14] == null ? "" :policyInfo[0][14]; 
					emirate = policyInfo[0][15] == null ? "" :policyInfo[0][15]; 
					pobox = policyInfo[0][16] == null ? "" :policyInfo[0][16]; 
					tphone = policyInfo[0][17] == null ? "" :policyInfo[0][17]; 
					mobile = policyInfo[0][18] == null ? "" :policyInfo[0][18]; 
					fax = policyInfo[0][19] == null ? "" :policyInfo[0][19]; 
					email = policyInfo[0][20] == null ? "" :policyInfo[0][20]; 
					inceptionDate = policyInfo[0][21] == null ? "" :policyInfo[0][21]; 
					referalDescription = policyInfo[0][29] == null ? "" :policyInfo[0][29];
					//referalDescription = referalDescription + policyInfo[0][23] == null ? "" :policyInfo[0][23];
					adminRemarks = policyInfo[0][25] == null ? "" :policyInfo[0][25];
					
					if(nationality.equalsIgnoreCase("Select"))
						nationality = "United Arab Emirates";

					if(adminRemarks.equalsIgnoreCase("null") || adminRemarks.equals(""))
						adminRemarks = "";
					
					if(referalDescription.length() > 0 && referalDescription != null && !referalDescription.equalsIgnoreCase("null"))
						referalReason = getReasonForReferal(referalDescription,branch);

					mailDatas.append("<table border='1' cellpadding='3' style='border-collapse:collapse' width='90%' align='center'>");
					mailDatas.append("<tr class='blueborder'>");
					mailDatas.append("<td class='heading' colspan='4'><font size='3'><b>Proposer's Information</b></font></td>");
					mailDatas.append("</tr>");

					mailDatas.append("<tr>");
					mailDatas.append("<td width='20%'><b>Proposer Name</b></td><td width='30%'>"+customerName+"</td>");
					mailDatas.append("<td width='20%'><b>DOB</b></td><td width='30%'>"+dob+"</td>");
					mailDatas.append("</tr>");

					mailDatas.append("<tr>");
					mailDatas.append("<td width='20%'><b>Occupation</b></td><td width='30%'>"+occupation+"</td>");
					mailDatas.append("<td width='20%'><b>Nationality</B></td><td width='30%'>"+nationality+"</td>");
					mailDatas.append("</tr>");

					mailDatas.append("<tr>");
					mailDatas.append("<td width='20%'><b>Emirate</b></td><td width='30%'>"+emirate+"</td>");
					mailDatas.append("<td width='20%'><b>Insured Location</b></td><td width='30%'>"+insuredLocation+"</td>");
					mailDatas.append("</tr>");

					mailDatas.append("<tr>");
					mailDatas.append("<td width='20%'><b>P.O. Box</b></td><td width='30%'>"+pobox+"</td>");
					mailDatas.append("<td width='20%'><b>Telephone</b></td><td width='30%'>"+tphone+"</td>");
					mailDatas.append("</tr>");
					
					mailDatas.append("<tr>");
					mailDatas.append("<td width='20%'><b>Mobile</b></td><td width='30%'>"+mobile+"</td>");
					mailDatas.append("<td width='20%'><b>Fax</b></td><td width='30%'>"+fax+"</td>");
					mailDatas.append("</tr>");

					mailDatas.append("<td width='20%'><b>E-Mail Id</b></td><td width='30%'>"+email+"</td>");
					mailDatas.append("<td width='20%'><b></b></td><td width='30%'></td>");
					//mailDatas.append("<td width='20%'><b>Insured Location</b></td><td>"+insuredLocation+"</td>");
					mailDatas.append("</tr>");
					mailDatas.append("</table>");
	
					mailDatas.append("<br>");
					
					mailDatas.append("<table border='1' cellpadding='3' style='border-collapse:collapse' width='90%' align='center'>");
					mailDatas.append("<tr class='blueborder'>");
					mailDatas.append("<td class='heading' colspan='4'><font size='3'><b>Quote Information</b></font></td>");
					mailDatas.append("</tr>");
					
					mailDatas.append("<tr>");
					mailDatas.append("<td width='20%'><b>Quote No</b></td><td width='30%'>"+quoteNo+"</td>");
					mailDatas.append("<td width='20%'><b>Date</b></td><td width='30%'>"+inceptionDate+"</td>");
					mailDatas.append("</tr>");

					mailDatas.append("<tr>");
					mailDatas.append("<td width='20%'><b>Broker</b></td><td width='30%'>"+brokerCompany+"</td>");

					//	Modified by chinna
					if(content!=null && content.trim().length()>0)
					{
						mailDatas.append("<td width='20%'><b>Content</b></td><td width='30%'>"+content+"</td>");
					}else{mailDatas.append("<td width='20%'><b></b></td><td width='30%'></td>");}
					//
					mailDatas.append("</tr>");
					
					//Modified by chinna
					System.out.print("profession: ================>>>"+profession.length()+":"+profession);
					if(profession!=null && profession.trim().length()>0)
					{
						mailDatas.append("<tr>");
						mailDatas.append("<td width='20%'><b>Profession</b></td><td colspan='3' width='30%'>"+profession+"</td>");
						mailDatas.append("</tr>");
					}
					if(freezone!=null && freezone.trim().length()>0)
					{
						mailDatas.append("<tr>");
						mailDatas.append("<td width='20%'><b>Freezone</b></td><td colspan='3' width='30%'>"+freezone+"</td>");
						mailDatas.append("</tr>");
					}//Condition checked
					
					mailDatas.append("</table>");
					mailDatas.append("<br>");
				}
				
				if(coverageSumInfo.length > 0)
				{
					mailDatas.append("<table border='1' cellpadding='3' cellspacing='1' style='border-collapse:collapse' width='90%' align='center'>");
					mailDatas.append("<tr class='blueborder'>");
					mailDatas.append("<td class='heading' align='left' width='10%' colspan='4'><font size='3'><b>Coverages</b></font></td>");    
					mailDatas.append("</tr>");
					mailDatas.append("</table>");

					mailDatas.append("<table border='1' cellpadding='3' cellspacing='1' style='border-collapse:collapse' width='90%' align='center'>");
					mailDatas.append("<tr class='blueborder'>");
					mailDatas.append("<td class='heading' align='center' ><b>SNO</b></td>");
					mailDatas.append("<td class='heading' align='center' width='60%'><b>COVERAGE</b></td>");
					mailDatas.append("<td class='heading' align='center' width='30%'><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;SUM INSURED ["+currencyType+"]</b></td>");
					mailDatas.append("</tr>");
					double sumInsDou = 0.0;
					for(int i=0;i<coverageSumInfo.length;i++)
					{
						if(coverageSumInfo[i][2].equalsIgnoreCase("8"))
						{
							uwFlag = true;
							FidelityUW = OSB.getFidelityUWQuestion(quoteNo,coverageSumInfo[i][2]);
						}

						coverageSumInfo[i][0] = coverageSumInfo[i][0]==null?"":coverageSumInfo[i][0];
						coverageSumInfo[i][1] = coverageSumInfo[i][1]==null?"0":coverageSumInfo[i][1];
						
						if(coverageSumInfo[i][1].indexOf(",") != -1)
							coverageSumInfo[i][1] = coverageSumInfo[i][1].replaceAll(",","");

						sumInsDou = Double.parseDouble(coverageSumInfo[i][1]);
						if(sumInsDou != 0)
						{
							mailDatas.append("<tr>");
							mailDatas.append("<td align='center'>"+(i+1)+"</td>");
							mailDatas.append("<td align='left' width='60%'>"+coverageSumInfo[i][0]+"</td>");
							mailDatas.append("<td align='right' width='30%'>"+amountFormat.format(sumInsDou)+"</td>");
							mailDatas.append("</tr>");
						}
					}
					if(policyInfo.length>0 && !status.equalsIgnoreCase("N") && !status.equalsIgnoreCase("A")&&!status.equalsIgnoreCase("Referal"))
					{
						mailDatas.append("<tr> <td align='left' colspan='2' >Total Premium: </td><td align='right'>"+premium+"</td>	</tr>");
					}
					mailDatas.append("<tr> <td colspan='3' ></td>	</tr>"); 
					mailDatas.append("</table>");
				}
				
				if(uwFlag && FidelityUW.length > 0)
				{
					uwQuest.append("<br>");
					uwQuest.append("<table border='1' cellpadding='2' cellspacing='1' style='border-collapse:collapse' width='90%' align='center'>");
					uwQuest.append("<tr class='blueborder'> <td class='heading' align='left' width='10%' colspan='4'>");
					uwQuest.append("<font size='3'> <b> Fidelity Guarnteed Underwriting Questions </b></font></td> </tr>");
					
					for(int i=0;i<FidelityUW.length;i++)
					{
						if(FidelityUW[i][1].equalsIgnoreCase("Y"))
							optionUW = "Yes";
						else
							optionUW = "No";
						
						uwQuest.append("<TR>");
						uwQuest.append("<TD align='center'>"+(i+1)+"</TD>");
						uwQuest.append("<TD width='80%'>"+FidelityUW[i][0]+"</TD>");
						uwQuest.append("<TD align='center' width='10%'><b>"+optionUW+"</b></TD>");
						uwQuest.append("</TR>");
					}
					uwQuest.append("</table>");
				}
				System.out.println("Status===========>>>>>>>>: "+status+ " mailFrom: "+mailFrom);
				if(!mailFrom.equalsIgnoreCase("CustomerMailQuote"))
				{
					if(status.equalsIgnoreCase("Referal"))
					{
						// Referal Description 
						mailDatas.append("<br>");
						mailDatas.append("<table border='0' cellpadding='3' style='border-collapse:collapse' width='90%' align='center'>");
						mailDatas.append("<tr class='blueborder'>");
						mailDatas.append("<td class='heading'><b>Reason For Referral :<b></td>");
						mailDatas.append("</tr>");
						mailDatas.append("<tr class='blueborder'>");
						mailDatas.append("<td class='heading'><font color='red'>"+referalReason+"</font></td>");
						mailDatas.append("</tr></table>");
						
						if( uwFlag && FidelityUW.length > 0 && uwQuest.length() > 0 )
						{
							mailDatas.append(uwQuest.toString());
						}
	
						mailDatas.append("<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
						mailDatas.append("<font style='font-family: Arial, Helvetica, sans-serif' size = 3><b>Regards,</font>");
						mailDatas.append("<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
						mailDatas.append("<font style='font-family: Arial, Helvetica, sans-serif' size = 3>"+brokerCompany+"</font>");
					}
					else 
					{
						// Admin Remarks
						if(!status.equalsIgnoreCase("Y") && !status.equalsIgnoreCase("N") && !status.equalsIgnoreCase("A"))
						{
							mailDatas.append("<br>");
							mailDatas.append("<table border='0' cellpadding='3' style='border-collapse:collapse' width='90%' align='center'>");
							mailDatas.append("<tr class='blueborder'>");
							mailDatas.append("<td class='heading'><b>Reason For Referral :<b></td>");
							mailDatas.append("</tr>");
							mailDatas.append("<tr class='blueborder'>");
							mailDatas.append("<td class='heading'><font color='red'>"+referalReason+"</font></td>");
							mailDatas.append("</tr></table>");
						}
						
						if(adminRemarks.trim().length() > 0)
						{
							mailDatas.append("<table border='0' cellpadding='3' style='border-collapse:collapse' width='90%' align='center'>");
							mailDatas.append("<tr class='blueborder'>");
							mailDatas.append("<td class='heading'>Admin Remarks :</td>");
							mailDatas.append("</tr>");
							mailDatas.append("<tr class='blueborder'>");
							mailDatas.append("<td class='heading'>"+adminRemarks+"</td>");
							mailDatas.append("</tr></table>");
						}
						mailDatas.append("<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
						mailDatas.append("<font style='font-family: Arial, Helvetica, sans-serif' size=3><b>Regards,</font>");
						mailDatas.append("<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
						mailDatas.append("<font style='font-family: Arial, Helvetica, sans-serif' size=3>Admin</font>");
					}
				 }// CustomerMailQuote
				 else
				 {
					 if(detailsOfBroker.length > 0 )
					 {
						 // brokerAddress = detailsOfBroker[0][0]+detailsOfBroker[0][1]!=null ?detailsOfBroker[0][1]+"<br>":"" +detailsOfBroker[0][4]!=null ?detailsOfBroker[0][4]+"<br>":"" +"<br>"+detailsOfBroker[0][5]+"<br>"+detailsOfBroker[0][6] ;
						 //brokerAddress = detailsOfBroker[0][0]+"<br>"+detailsOfBroker[0][5]+"<br>"+detailsOfBroker[0][6] ;
					 
						 mailDatas.append("<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
						 mailDatas.append("<font style='font-family: Arial, Helvetica, sans-serif' size = 3><b>Regards,</font>");
						 mailDatas.append("<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
						 mailDatas.append("<font style='font-family: Arial, Helvetica, sans-serif' size = 3>"+detailsOfBroker[0][0]+"</font>");
						 mailDatas.append("<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
						 mailDatas.append("<font style='font-family: Arial, Helvetica, sans-serif' size = 3>"+detailsOfBroker[0][5]+"</font>");
						 mailDatas.append("<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
						 mailDatas.append("<font style='font-family: Arial, Helvetica, sans-serif' size = 3>"+detailsOfBroker[0][6]+"</font>");
					 }
				 }
				 mailDatas.append("<br><br>");
				 mailDatas.append("</body>");
				 mailDatas.append("</html>");
			}

			if(mailDatas.length() > 0)
			{
				msg = msg + mailDatas.toString();
				if(!mailFrom.equalsIgnoreCase("CustomerMailQuote"))
				{
					if(status.equalsIgnoreCase("Referal"))
					{
						header = "<b></b><br> <font style='font-family: Arial, Helvetica, sans-serif' size = 2 face = 'arial'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;This e-mail contains an automated underwriting referral from the on-line Office Shield System for Quote Number:&nbsp;<b>"+quoteNo+"</b>. &nbsp;&nbsp;Please respond as soon as possible as the client is awaiting a decision.<br></font><br><br>"+msg+"</a>";
					}
					else
					{		System.out.println("Status ..OMB......."+status);
						if(status.equalsIgnoreCase("Y"))
							temp = "accepted";
						else if(status.equalsIgnoreCase("N"))
							temp = "rejected";
						else if(status.equalsIgnoreCase("A"))
							temp = "holding";
	
						header = "<b></b><br> <font style='font-family: Arial, Helvetica, sans-serif' size = 2 face = 'arial'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;This e-mail contains an automated underwriting referral from the on-line Office Shield System for Quote Number:&nbsp;<b>"+quoteNo+"</b>. &nbsp;&nbsp; This Quote is "+temp+" by Admin . <br></font><br><br>"+msg+"</a>";
					}
				} // CustomerMailQuote
				else
				{
					header = "<b></b><br> <font style='font-family: Arial, Helvetica, sans-serif' size = 2 face = 'arial'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;We are pleased to offer the quotation for your "+schemeName+"  Quote Number:&nbsp;<b>"+quoteNo+"</b>. In case of any query please contact at the under mentioned address.<br></font><br><br>"+msg+"</a>";
				}
			}
		}
		catch(Exception e)
		{
			System.out.println(" Exception is OfficeMailBean officeBodyOfMsg "+e);
			e.printStackTrace();
			return "Some Exception OfficeMailBean";
		}
		return  header;
	}
	
	public String getLoginIdForQno(String quoteNo)
	{
		runner run = new runner();
		String sql = "";
		String loginId = "";
				
		try
		{
			sql ="select login_id from home_position_master where quote_no in("+quoteNo+") and product_id='"+proId+"'";
			loginId = run.singleSelection(sql);
		}
		catch(Exception e)
		{
			System.out.println("getPolicyNo..."+e);
			e.printStackTrace();
		}
		return loginId;
	}

	public HashMap getPortDetails()
    {
		HashMap hashtable = new HashMap();
        String as[][] = new String[0][0];
        String s = "";
        try
        {
           String s1 = "select host_name,user_name, password, remarks, from_address from mail_constant_values";
           String as1[][] = runner.multipleSelection(s1);
           hashtable.put("PortDetails", as1);
           if(as1.length > 0)
           {
                for(int i = 0; i < as1.length; i++)
                {
                    hashtable.put("hostname", (as1[0][0]!=null?as1[0][0]:""));
                    hashtable.put("username", (as1[0][1]!=null?as1[0][1]:""));
                    hashtable.put("password", (as1[0][2]!=null?as1[0][2]:""));
                    hashtable.put("webaddress", (as1[0][3]!=null?as1[0][3]:""));
                    hashtable.put("Address", (as1[0][4]!=null?as1[0][4]:""));
                }
           }
        }
        catch(Exception exception)
        {
            System.out.println("exception in retreiving values OSB" + exception);
			exception.printStackTrace();
        }
        return hashtable;
    }

	public HashMap getReferralDetails(final String emailSubject)
	{
       HashMap hashtable = new HashMap();
	   String as[][] = new String[0][0];
       try
       {
           String sql = "";
           //sql = "select email_to, email_cc, email_subject, email_message, email_from_name, email_from_phoneno,email_from_faxno, email_type, remarks, status from mail_details where email_subject = 'Home Insurance Referral'";
           sql = "select email_to, email_cc, email_subject, email_message, email_from_name, email_from_phoneno,email_from_faxno, email_type, remarks, status from mail_details where email_subject = '"+emailSubject+"'";
            
            as = runner.multipleSelection(sql);
            hashtable.put("referal", as);
            if(as.length > 0)
            {
                for(int i = 0; i < as.length; i++)
                {
                    hashtable.put("emailto", as[0][0]);
                    hashtable.put("emailcc", as[0][1]);
                    hashtable.put("emailsubject", as[0][2]);
                    hashtable.put("emailmessage", as[0][3]);
                    hashtable.put("emailname", as[0][4]);
                    hashtable.put("emailphno", as[0][5]);
                    hashtable.put("emailfax", as[0][6]);
                    hashtable.put("emailtype", as[0][7]);
                    hashtable.put("emailrema", as[0][8]);
                    hashtable.put("emailstat", as[0][9]);
                }
            }
        }
        catch(Exception exception)
        {
            System.out.println(" Office mail Controller Get Referal Mail Details "+exception);
			exception.printStackTrace();
        }
        return hashtable;
    } 
	
	public String getReasonForReferal(String referalDescription,String branch)
	{
		String query = "";
		HashMap HM = new HashMap(); 
		String referalReason = "";
		String args[] = new String[1];
		String result[][] = new String[0][0];
		try
		{
			args[0] = branch;
			query = "select referal_id,referal_name from REFERAL_MASTER where branch_code=? order by referal_id";
			result = runner.multipleSelection(query,args);
			for(int i=0;i<result.length;i++)
			{
				HM.put(result[i][0],result[i][1]); 
			}
		} 
		catch(Exception ex) 
		{
			System.out.println(" getReasonForReferal 1  "+ex);
			ex.printStackTrace();		
		}
		
		try
		{
			if(referalDescription.length() > 0)
				referalDescription = referalDescription.substring(1,referalDescription.length()-1);
			if(referalDescription.indexOf(",") != -1)
				referalDescription = referalDescription.replaceAll(",,",",");
			StringTokenizer st2=new StringTokenizer(referalDescription,",");
			while(st2.hasMoreTokens()) 
			{
				String x=st2.nextToken();
				if( HM.containsKey(x));
					referalReason += "<br>" + (HM.get(x)==null?"":HM.get(x));
			}
		}
		catch(Exception e)
		{
			System.out.println("Parsing Referral Description.."+e);
			e.printStackTrace();
		}
		if(referalReason.length() > 0 && !referalReason.equalsIgnoreCase("<br>"))
		{
			System.out.println("Not Admin referral....");
		}
		else
			referalReason = "Admin Referral";	
		return referalReason;
	}

	public String getProductId(String quoteNo)
	{
		String sql = "";
		String proId = "";
		String args[] = new String[1];
		try
		{
			args[0] = quoteNo;
			sql = "select product_id from home_position_master where quote_no=? ";
			proId = runner.singleSelection(sql,args);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return proId;
	}
	
	public String[][] brokerPersonalDetailsForCustomerMailQuote(String quoteNo)
	{
		String brokerAddress[][] =	new String[0][0];
		String sql = "";
        String args[] = new String[1];
		try
		{
			args[0] = quoteNo;
			sql = "select COMPANY_NAME,ADDRESS1,ADDRESS2,ADDRESS3,CITY,initcap(COUNTRY),POBOX,CONTACT_PERSON,PHONE,agency_code from BROKER_COMPANY_MASTER where AGENCY_CODE in(select oa_code from LOGIN_MASTER where login_id=(select login_id from home_position_master where quote_no=?))";
			brokerAddress = runner.multipleSelection(sql,args);
		}
		catch(Exception e)
		{
			System.out.println("Exception is"+e);
            e.printStackTrace();
		}
		return brokerAddress;
	}
	
	public HashMap getMailQuoteMailIds(String quoteNo)
	{
		HashMap mailIds = new HashMap();
		String sql = "";
        String args[] = new String[1];
        String from = "";
        String to = "";
		try
		{
			args[0] = quoteNo;
			sql = "select EMAIL from personal_info where AGENCY_CODE in(select oa_code from LOGIN_MASTER where login_id=(select login_id from home_position_master where quote_no=?))";
			from = runner.singleSelection(sql,args);
			sql = "select EMAIL from personal_info where customer_id =(select customer_id from home_position_master where quote_no=?)";
			to = runner.singleSelection(sql,args);
			if(from!=null && from.length() > 0 && to!=null && to.length() > 0 )
			{
				mailIds.put("FromId",from);
				mailIds.put("ToId",to);
			}
		}
		catch(Exception e)
		{
			System.out.println("Exception is"+e);
            e.printStackTrace();
		}
		return mailIds;
	}
	
} //Class

