package com.maan.Office.DAO;
//
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Calendar;
import java.util.HashMap;
import java.util.StringTokenizer;

import proj.date.DateFunction;

import com.maan.Home.DataManipualtion.DataSelect;
import com.maan.common.LogManager;
import com.maan.common.error.ErrorInfo;
import com.maan.common.exception.BaseException;
import com.maan.services.util.runner;

public class OfficeShieldBean extends ErrorInfo 
{
	final static transient private String ENTER = "- Enter";
	final static transient private String EXIT = "- Exit";
	
	private String applicationNo = "";
	private String quoteNo = "";

	private String branch = "";
	private String cid = "";
	private String loginId = "";
	private String proId = "";
	private String sessionId = "";
	private String email = "";
	private String address1 = "";
	private String address2 = "";
	private String emirate1 = "";
	private String emirate2 = "";
	private String country1 = "";
	private String country2 = "";
	private String poBox1 = "";
	private String poBox2 = "";
	private String contactPerson = "";
	private String brokerMail = "";
	private String customerId = "";
	private String freezone = "";
	private String freezoneOthers = "";
	private String addressInsure = "";
	private String contentsOffice = "";
	private String contValOthers = "";
	private String claimInfo = "";
	private String lastYr1 = "";
	private String lastYr2 = "";
	private String lastYr3 = "";
	private String amount1 = "";
	private String amount2 = "";
	private String amount3 = "";
	private String inceptionDate = "";
	private String activityProfession = "";
	private String activityProfessionOthers = "";
	private String dobDay = "";
	private String dobMonth = "";
	private String dobYear = "";

	private String coverId = "";
	private String schemeId = "";
	private String contentTypeId = "";
	private String custFileName = "";
	private String systemFileName = "";
	private String uploadId = "";
	private String uploadRemarks = "";
	private String claim1 = "";
	private String claim2 = "";
	private String claim3 = "";
	private String phoneNo = "";

	public void setPhoneNo(String phoneNo) 
	{
		this.phoneNo = phoneNo;
	}
	public void setBranch(String branch)
	{
		this.branch = branch;
	}

	public String getBranch()
	{
		return branch;
	}
	
	public void setCID(final String cid)
	{
		this.cid = cid;
	}

	public String getCID()
	{
		return cid;
	}
	
	public void setProId(String proId)
	{
		this.proId = proId;
	}

	public String getProId()
	{
		return proId;
	}
	
	public void setLoginId(String loginId)
	{
		this.loginId = loginId;
	}

	public String getLoginId()
	{
		return loginId;
	}

	public void setAddress1(String address1)
	{
		this.address1 = address1;
	}

	public String getAddress1()
	{
		return address1;
	}
	
	public void setAddress2(String address2)
	{
		this.address2 = address2;
	}

	public String getAddress2()
	{
		return address2;
	}

	public void setEmirate1(String emirate1)
	{
		this.emirate1 = emirate1;
	}

	public String getEmirate1()
	{
		return address2;
	}
	
	public void setEmirate2(String emirate2)
	{
		this.emirate2 = emirate2;
	}

	public String getEmirate2()
	{
		return address2;
	}

	public void setCountry1(String country1)
	{
		this.country1 = country1;
	}

	public String getCountry1()
	{
		return country1;
	}
	
	public void setCountry2(String country2)
	{
		this.country2 = country2;
	}

	public String getCountry2()
	{
		return country2;
	}
	
	public void setpoBox1(String poBox1)
	{
		this.poBox1 = poBox1;
	}

	public String getpoBox1()
	{
		return poBox1;
	}

	public void setpoBox2(String poBox2)
	{
		this.poBox2 = poBox2;
	}

	public String getpoBox2()
	{
		return poBox2;
	}
	
	public void setCustomerId(String customerId)
	{
		this.customerId = customerId;
	}

	public String getCustomerId()
	{
		return customerId;
	}
	
	public void setFreezone(String freezone)
	{
		this.freezone = freezone;
	}

	public String getFreezone()
	{
		return freezone;
	}
	
	public void setFreezoneOthers(String freezoneOthers)
	{
		this.freezoneOthers = freezoneOthers;
	}

	public String getFreezoneOthers()
	{
		return freezoneOthers;
	}

	public void setAddressInsure(String addressInsure)
	{
		this.addressInsure = addressInsure;
	}

	public String getAddressInsure()
	{
		return addressInsure;
	}
	
	public void setContentsOffice(String contentsOffice)
	{
		this.contentsOffice = contentsOffice;
	}

	public String getContentsOffice()
	{
		return contentsOffice;
	}
	
	public void setContValOthers(String contValOthers)
	{
		this.contValOthers = contValOthers;
	}

	public String getContValOthers()
	{
		return contValOthers;
	}
	
	public void setClaimInfo(String claimInfo)
	{
		this.claimInfo = claimInfo;
	}
	public String getClaimInfo()
	{
		return claimInfo;
	}
	
	public void setLastYr1(String lastYr1)
	{
		this.lastYr1 = lastYr1;
	}
	public String getLastYr1()
	{
		return lastYr1;
	}
	
	public void setLastYr2(String lastYr2)
	{
		this.lastYr2 = lastYr2;
	}
	public String getLastYr2()
	{
		return lastYr2;
	}

	public void setLastYr3(String lastYr3)
	{
		this.lastYr3 = lastYr3;
	}
	public String getLastYr3()
	{
		return lastYr3;
	}
	
	public void setAmount1(String amount1)
	{
		this.amount1 = amount1;
	}
	public String getAmount1()
	{
		return amount1;
	}

	public void setAmount2(String amount2)
	{
		this.amount2 = amount2;
	}
	public String getAmount2()
	{
		return amount2;
	}

	public void setAmount3(String amount3)
	{
		this.amount3 = amount3;
	}
	public String getAmount3()
	{
		return amount3;
	}

	public void setInceptionDate(String inceptionDate)
	{
		this.inceptionDate = inceptionDate;
	}
	
	public String getInceptionDate()
	{
		return inceptionDate;
	}
	
	public void setActivityProfession(String activityProfession)
	{
		this.activityProfession = activityProfession;
	}
	
	public String getActivityProfession()
	{
		return activityProfession;
	}
	
	public void setActivityProfessionOthers(String activityProfessionOthers)
	{
		this.activityProfessionOthers = activityProfessionOthers;
	}
	
	public String getActivityProfessionOthers()
	{
		return activityProfessionOthers;
	}

	public void setDobDay(String dobDay)
	{
		this.dobDay=dobDay;
	}
	
	public String getDobDay()
	{
		return dobDay;
	}

	public void setDobMonth(String dobMonth)
	{
		this.dobMonth=dobMonth;
	}
	
	public String getDobMonth()
	{
		return dobMonth;
	}

	public void setDobYear(String dobYear)
	{
		this.dobYear=dobYear;
	}
		
	public String getDobYear()
	{
		return dobYear;
	}

	public void setApplicationNo(String applicationNo)
	{
		this.applicationNo=applicationNo;
	}
		
	public String getApplicationNo()
	{
		return applicationNo;
	}
	
	public void setQuoteNo(String quoteNo)
	{
		this.quoteNo=quoteNo;
	}
		
	public String getQuoteNo()
	{
		return quoteNo;
	}
	
	public void setCoverId(String coverId)
	{
		this.coverId = coverId;
	}

	public String getCoverId()
	{
		return coverId;
	}
	
	public void setContentTypeId(String contentTypeId)
	{
		this.contentTypeId = contentTypeId;
	}

	public String getContentTypeId()
	{
		return contentTypeId;
	}

	public void setSchemeId(String schemeId)
	{
		this.schemeId = schemeId;
	}

	public String getSchemeId()
	{
		return schemeId;
	}

	public void setCustFileName(String custFileName)
	{
		this.custFileName = custFileName;
	}

	public String getCustFileName()
	{
		return custFileName;
	}

	public void setSystemFileName(String systemFileName)
	{
		this.systemFileName = systemFileName;
	}

	public String getSystemFileName()
	{
		return systemFileName;
	}
	
	public void setUploadRemarks(String uploadRemarks)
	{
		this.uploadRemarks = uploadRemarks;
	}

	public String getUploadRemarks()
	{
		return uploadRemarks;
	}

	public void setClaim1(String claim1)
	{
		this.claim1 = claim1;
	}

	public String getClaim1()
	{
		return claim1;
	}

	public void setClaim2(String claim2)
	{
		this.claim2 = claim2;
	}

	public String getClaim2()
	{
		return claim2;
	}

	public void setClaim3(String claim3)
	{
		this.claim3 = claim3;
	}

	public String getClaim3()
	{
		return claim3;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoneNo() 
	{
		return phoneNo;
	}
	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	/** Setter & Getter Methods End**/

	public String[][] getLoginIdInformation()
	{
		String sql = "";
		String result[][] = new String[0][0];
		String args[] = new String[1];
		try
		{
			args[0] = loginId;
			sql = "select first_name,email from personal_info where agency_code =(select oa_code from login_master where login_id=?)";
			result = runner.multipleSelection(sql,args);
			System.out.println("getLoginIdInformation ..."+result.length);
		}
		catch(Exception E)
		{
			System.out.println("getLoginIdInformation ..."+E.toString());
			E.printStackTrace();
		}
		return result;
	}
	
	public String[][] emirateCollection(String cid)
	{
		String sql= "";
		String[][] title = new String[0][0];
		String args[] = new String[1];
		try
		{
			args[0] = cid;
			sql = "select trim(city_name) from city_master where country_id=? and lower(city_name) not in('others') and city_name not in ('Jebel Ali') order by city_name";
			title = runner.multipleSelection(sql,args);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return title;
	}

	public String[][] getCustomerForOS(String custId)
	{
		String sql="";
		String[][] values=new String[0][0];
		String args[] = new String[1];
		try
		{
			args[0] = custId;
			sql="select title,gender,nvl(first_name,last_name||company_name),nationality,to_char(dob,'DD') as dobday,to_char(dob,'MM') as dobmonth,to_char(dob,'YYYY') as dobyear,telephone,mobile,fax,email,address1,occupation,emirate,country,pobox,company_name,address2,customer_source from personal_info where customer_id=?";
			values = runner.multipleSelection(sql,args);
		}
		catch(Exception e)
		{
			System.out.println("getCustomerForOS ...DataSelect"+e.toString());
			e.printStackTrace();
		}
		return values;
	}
	
	public String getContentCoverageSILimit()
	{
		String sql ="";
		String sumInsured = "";
		String args[] = new String[3];
		try
		{
			args[0] = proId;
			args[1] = schemeId;
			args[2] = contentsOffice;
			sql = "select sum_insured_limit from ofs_coverages_master where coverages_id='1' and product_id=? and scheme_id=? and content_type_id=?";
			sumInsured = runner.singleSelection(sql,args);
		}
		catch(Exception e)
		{
			System.out.println("getContentCoverageSILimit.."+e);
				e.printStackTrace();
		}
		return sumInsured;
	}

	public String[][] getCustomerForOS(String custId,String quoteNo)
	{
		String sql="";
		String[][] values=new String[0][0];
		String args[] = new String[3];
		
		try
		{
			sql = "select nvl(per.first_name,per.last_name||per.company_name),per.address1,per.emirate,per.country,per.pobox,ofd.INSURED_ADDRESS_DIFFERENT,ofd.ADDRESS1,ofd.POBOX,ofd.COUNTRY,ofd.EMIRATE,ofd.FREEZONE,ofd.CONTENT_VALUE,ofd.CONTENT_VALUE_OTHERS,ofd.ACTIVITY_PROFESSION,to_char(ofd.inception_date,'dd') as policyDay,to_char(ofd.inception_date,'MON') as policyMonth,to_char(ofd.inception_date,'YYYY') as policyYear,CLAIM_STATUS,LAST_YEARS_1,LAST_YEARS_2,LAST_YEARS_3,AMOUNT_1,AMOUNT_2,AMOUNT_3,ofd.PROF_OTHERS,ofd.FREEZONE_OTHERS,NO_OF_CLAIM1,NO_OF_CLAIM2,NO_OF_CLAIM3,ofd.TELEPHONE,ofd.EMAIL,per.TELEPHONE,per.mobile,per.email from personal_info per,OFS_DATA ofd,home_position_master hpm  where per.customer_id=ofd.customer_id and hpm.customer_id=? and ofd.quote_no=hpm.quote_no and hpm.quote_no=? and ofd.application_no=hpm.application_no and hpm.application_no=(select application_no from home_position_master where quote_no=?)";

			args[0] = custId;
			args[1] = quoteNo;
			args[2] = quoteNo;
			values = runner.multipleSelection(sql,args);
		}
		catch(Exception e)
		{
			System.out.println("getCustomerForOS ...DataSelect"+e.toString());
			e.printStackTrace();
		}
		return values;
	}

	public String[][] getFreezoneMaster()
	{
		String sql="";
		String[][] freez=new String[0][0];
		
		try
		{
			//sql="select FREEZONE,FREEZONE_DESCRIPTION from OFS_FREEZONE_MASTER where status='Y' order by FREEZONE_DESCRIPTION";
			sql="select FREEZONE,FREEZONE_DESCRIPTION from OFS_FREEZONE_MASTER where status='Y' order by DISPLAY_ORDER";
			freez = runner.multipleSelection(sql);

		}
		catch(Exception e)
		{
			System.out.println("getFreezoneMaster ...DataSelect"+e.toString());
			e.printStackTrace();
		}
		return freez;
	}

	public String[][] getContentMaster(String schemeId)
	{
		String sql="";
		String[][] content=new String[0][0];
		String args[] = new String[1];
		try
		{
			args[0] = schemeId;
			sql="select CONTENT_TYPE_ID,CONTENT_DESCRIPTION from OFS_CONTENT_MASTER where status='Y' and scheme_id=? order by content_type_id";
			//content = runner.multipleSelection(sql,args);
		}
		catch(Exception e)
		{
			System.out.println("getContentMaster ...DataSelect"+e.toString());
			e.printStackTrace();
		}
		return content;
	}

	public String[][] getActProfMaster(String schemeId)
	{
		String sql="";
		String[][] profMaster=new String[0][0];
		String args[] = new String[1];
		try
		{
			args[0] = schemeId;
			sql="select ACTIVITY_PROFESSION,PROFESSION from OFS_ACT_PROF_MASTER where status='Y' and scheme_id=? order by PROFESSION";
			profMaster = runner.multipleSelection(sql,args);
		}
		catch(Exception e)
		{
			System.out.println("getActProfMaster ...OfficeShield Bean"+e.toString());
			e.printStackTrace();
		}
		return profMaster;
	}
	
	public String insertOfficeShieldData(String mode)
	{
		String temp = "sysdate";
		//temp = runner.getSysdate(branch);		
		DataSelect data = new DataSelect();
		String insertQry = "";
		String sqlQuery_ = "";
		String args[] = new String[0];
		
		int f =0;
		int rows = 0;
		String newQuoteNo="";
		String amendId="";
		String newApplicationNo="";
		System.out.println("..insertOfficeShieldData..."+mode);
		
		// Office Shield Data Table Insert
		
		try
		{
			args = new String[1];
			args[0] = applicationNo;
			sqlQuery_ ="select count(*) from OFS_DATA where application_no=?";	
			String res = runner.singleSelection(sqlQuery_,args);
			if(res.length() > 0 && !res.equalsIgnoreCase("null") && !res.equalsIgnoreCase("") )
			{
				rows = Integer.parseInt(res);
				System.out.println("RoyalTest rows true are.."+rows);
			}
			else
			{
				rows = 0;
				System.out.println("RoyalTest rows are.."+rows);
			}					
		}
		catch(Exception e){
			System.out.println("Exception in insertOrUpdateCompanyInfo() for Count :"+e.toString());
			e.printStackTrace();
		}
		
		if(!activityProfession.equalsIgnoreCase("68"))
			activityProfessionOthers = "";
		if(!freezone.equalsIgnoreCase("36"))
			freezoneOthers = "";

		System.out.println("Ofs Data Application No count ..."+rows);
		if(rows<=0)
		{
			newApplicationNo = getMaxApplicationNo(proId,branch);
			newQuoteNo = getMaxQuote(proId,branch);
			amendId = "0";

			System.out.println("OfficeShield QuoteNo ..."+quoteNo);
			System.out.println("OfficeShield applicationNo ..."+applicationNo);
			System.out.println("OfficeShield amendId ..."+amendId);
			
			if(amount1==null || amount1.equalsIgnoreCase("") || amount1.equalsIgnoreCase("null"))
				amount1 = "0";
			if(amount2==null || amount2.equalsIgnoreCase("") || amount2.equalsIgnoreCase("null"))
				amount2 = "0";
			if(amount3==null || amount3.equalsIgnoreCase("") || amount3.equalsIgnoreCase("null"))
				amount3 = "0";
			if(claim1==null || claim1.equalsIgnoreCase("") || claim1.equalsIgnoreCase("null"))
				claim1 = "0";
			if(claim2==null || claim2.equalsIgnoreCase("") || claim2.equalsIgnoreCase("null"))
				claim2 = "0";
			if(claim3==null || claim3.equalsIgnoreCase("") || claim3.equalsIgnoreCase("null"))
				claim3 = "0";
			try
			{
				args = new String[30];
				//insertQry = "insert into OFS_DATA(QUOTE_NO,AMEND_ID,CUSTOMER_ID,INSURED_ADDRESS_DIFFERENT,ADDRESS1,POBOX,COUNTRY,EMIRATE,FREEZONE,CONTENT_VALUE,CONTENT_VALUE_OTHERS,CLAIM_STATUS,LAST_YEARS_1,LAST_YEARS_2,LAST_YEARS_3,AMOUNT_1,AMOUNT_2,AMOUNT_3,INCEPTION_DATE,ENTRY_DATE,APPLICATION_NO,STATUS,ACTIVITY_PROFESSION,ACTIVITY_STATUS,EXPIRY_DATE,PROF_OTHERS,FREEZONE_OTHERS,CONTENT_STATUS,NO_OF_CLAIM1,NO_OF_CLAIM2,NO_OF_CLAIM3,TELEPHONE,EMAIL) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,TO_DATE('"+inceptionDate+"','dd-mm-yyyy hh24:mi:ss'),"+temp+",?,?,?,?,(add_months(to_date('"+inceptionDate+"'),12)-1),?,?,?,?,?,?,?,?)";
				insertQry = "insert into OFS_DATA(QUOTE_NO,AMEND_ID,CUSTOMER_ID,INSURED_ADDRESS_DIFFERENT,ADDRESS1,POBOX,COUNTRY,EMIRATE,FREEZONE,CONTENT_VALUE,CONTENT_VALUE_OTHERS,CLAIM_STATUS,LAST_YEARS_1,LAST_YEARS_2,LAST_YEARS_3,AMOUNT_1,AMOUNT_2,AMOUNT_3,INCEPTION_DATE,ENTRY_DATE,APPLICATION_NO,STATUS,ACTIVITY_PROFESSION,ACTIVITY_STATUS,EXPIRY_DATE,PROF_OTHERS,FREEZONE_OTHERS,CONTENT_STATUS,NO_OF_CLAIM1,NO_OF_CLAIM2,NO_OF_CLAIM3,TELEPHONE,EMAIL) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,TO_DATE(sysdate,'dd-mm-yyyy hh24:mi:ss'),"+temp+",?,?,?,?,(add_months(to_date(sysdate),12)-1),?,?,?,?,?,?,?,?)";
				
				args[0] = ""+Integer.parseInt(newQuoteNo);
				args[1] = ""+Integer.parseInt(amendId);
				args[2] = ""+Integer.parseInt(customerId);
				args[3] = addressInsure;
				args[4] = address2;
				args[5] = poBox2;
				args[6] = country2;
				args[7] = emirate2;
				args[8] = ""+Integer.parseInt(freezone);
				args[9] = ""+Integer.parseInt(contentsOffice);
				args[10] = ""+Integer.parseInt(contValOthers);
				args[11] = claimInfo;
				args[12] = ""+Integer.parseInt(lastYr1);
				args[13] = ""+Integer.parseInt(lastYr2);
				args[14] = ""+Integer.parseInt(lastYr3);
				args[15] = ""+Integer.parseInt(amount1);
				args[16] = ""+Integer.parseInt(amount2);
				args[17] = ""+Integer.parseInt(amount3);
				args[18] = ""+Integer.parseInt(newApplicationNo);
				args[19] = "Y";
				args[20] = ""+Integer.parseInt(activityProfession);
				args[21] = "N";
				args[22] = activityProfessionOthers;
				args[23] = freezoneOthers;
				args[24] = "N";
				args[25] = ""+Integer.parseInt(claim1);
				args[26] = ""+Integer.parseInt(claim2);
				args[27] = ""+Integer.parseInt(claim3);
				args[28] = ""+phoneNo;
				args[29] = ""+email;
				runner.multipleInsertion(insertQry,args);
						
				// Position Master.....

				//insertQry = "insert into HOME_POSITION_MASTER (QUOTE_NO,CUSTOMER_ID,LOGIN_ID,PRODUCT_ID,COMPANY_ID,AMEND_ID,INCEPTION_DATE,EXPIRY_DATE,EFFECTIVE_DATE,ENTRY_DATE,APPLICATION_NO,STATUS,SCHEME_ID,CONTENT_TYPE_ID) values(?,?,?,?,?,?,TO_DATE('"+inceptionDate+"','dd-mm-yyyy  hh24:mi:ss'),(add_months(to_date('"+inceptionDate+"'),12)-1),TO_DATE('"+inceptionDate+"','dd-mm-yyyy hh24:mi:ss'),(select "+temp+" from dual),?,?,?,?)";
				insertQry = "insert into HOME_POSITION_MASTER (QUOTE_NO,CUSTOMER_ID,LOGIN_ID,PRODUCT_ID,COMPANY_ID,AMEND_ID,INCEPTION_DATE,EXPIRY_DATE,EFFECTIVE_DATE,ENTRY_DATE,APPLICATION_NO,STATUS,SCHEME_ID,CONTENT_TYPE_ID) values(?,?,?,?,?,?,sysdate,(add_months(sysdate,12)-1),sysdate,(select "+temp+" from dual),?,?,?,?)";
				args = new String[10];
				args[0] = ""+Integer.parseInt(newQuoteNo);
				args[1] = ""+Integer.parseInt(customerId);
				args[2] = loginId;
				args[3] = proId;
				args[4] = "20";
				args[5] = ""+Integer.parseInt(amendId);
				args[6] = newApplicationNo;
				args[7] = "Y";
				args[8] = schemeId;
				args[9] = ""+Integer.parseInt(contentsOffice);
				
				runner.multipleInsertion(insertQry,args);
				// Tracking Details
				setApplicationNo(newApplicationNo);
				setQuoteNo(newQuoteNo);
				insertTrackingDetails();
			}
			catch(Exception e)
			{
				System.out.println("InsertOfficePositionMaster ..."+ insertQry);
				System.out.println("Error in insertHomePositionMaster values" + e.toString());
				e.printStackTrace();
			}
		}//rows check
		else
		{
			String activityStatus = "";
			String contentStatus = "";
			String result[][] = new String[0][0];
			result = checkContentProfessionStatus(applicationNo,quoteNo);
			if(result.length > 0)
			{
				if(result[0][0].equalsIgnoreCase(contentsOffice))
					contentStatus = "N";
				else
					contentStatus = "Y";

				if(result[0][1].equalsIgnoreCase(activityProfession))
					activityStatus = "N";
				else
					activityStatus = "Y";
			}

			System.out.println("Office Shield Data Update.application No.."+applicationNo);
			System.out.println("Office Shield Data Update.Quote No.."+quoteNo);

			try
			{
				args = new String[28];
				//insertQry = "update OFS_DATA set CUSTOMER_ID=?,INSURED_ADDRESS_DIFFERENT=?,ADDRESS1=?,POBOX=?,COUNTRY=?,EMIRATE=?,FREEZONE=?,CONTENT_VALUE=?,CONTENT_VALUE_OTHERS=?,CLAIM_STATUS=?,LAST_YEARS_1=?,LAST_YEARS_2=?,LAST_YEARS_3=?,AMOUNT_1=?,AMOUNT_2=?,AMOUNT_3=?,INCEPTION_DATE=TO_DATE('"+inceptionDate+"','dd-mm-yyyy hh24:mi:ss'),ENTRY_DATE="+temp+",ACTIVITY_PROFESSION=?,ACTIVITY_STATUS=?,EXPIRY_DATE=(add_months(to_date('"+inceptionDate+"'),12)-1),PROF_OTHERS=?,FREEZONE_OTHERS=?,CONTENT_STATUS=?,NO_OF_CLAIM1=?,NO_OF_CLAIM2=?,NO_OF_CLAIM3=?,TELEPHONE=?,EMAIL=?  where application_no=? and quote_no=? "; 
				insertQry = "update OFS_DATA set CUSTOMER_ID=?,INSURED_ADDRESS_DIFFERENT=?,ADDRESS1=?,POBOX=?,COUNTRY=?,EMIRATE=?,FREEZONE=?,CONTENT_VALUE=?,CONTENT_VALUE_OTHERS=?,CLAIM_STATUS=?,LAST_YEARS_1=?,LAST_YEARS_2=?,LAST_YEARS_3=?,AMOUNT_1=?,AMOUNT_2=?,AMOUNT_3=?,INCEPTION_DATE=sysdate,ENTRY_DATE="+temp+",ACTIVITY_PROFESSION=?,ACTIVITY_STATUS=?,EXPIRY_DATE=(add_months(sysdate,12)-1),PROF_OTHERS=?,FREEZONE_OTHERS=?,CONTENT_STATUS=?,NO_OF_CLAIM1=?,NO_OF_CLAIM2=?,NO_OF_CLAIM3=?,TELEPHONE=?,EMAIL=?  where application_no=? and quote_no=? ";
				
				args[0] = ""+Integer.parseInt(customerId);
				args[1] = addressInsure;
				args[2] = address2;
				args[3] = poBox2;
				args[4] = country2;
				args[5] = emirate2;
				args[6] = ""+Integer.parseInt(freezone);
				args[7] = ""+Integer.parseInt(contentsOffice);
				args[8] = ""+Integer.parseInt(contValOthers);
				args[9] = claimInfo;
				args[10] = ""+Integer.parseInt(lastYr1);
				args[11] = ""+Integer.parseInt(lastYr2);
				args[12] = ""+Integer.parseInt(lastYr3);
				args[13] = ""+Integer.parseInt(amount1);
				args[14] = ""+Integer.parseInt(amount2);
				args[15] = ""+Integer.parseInt(amount3);
				args[16] = ""+Integer.parseInt(activityProfession);
				args[17] = activityStatus;
				args[18] = activityProfessionOthers;
				args[19] = freezoneOthers;
				args[20] = contentStatus;
				args[21] = ""+Integer.parseInt(claim1);
				args[22] = ""+Integer.parseInt(claim2);
				args[23] = ""+Integer.parseInt(claim3);
				args[24] = ""+phoneNo;
				args[25] = ""+email;

				args[26] = ""+Integer.parseInt(applicationNo);
				args[27] = ""+Integer.parseInt(quoteNo);
	
				runner.multipleUpdation(insertQry,args);

//				insertQry = "update HOME_POSITION_MASTER set CUSTOMER_ID=?,INCEPTION_DATE=TO_DATE('"+inceptionDate+"','dd-mm-yyyy hh24:mi:ss'),EXPIRY_DATE=(add_months(to_date('"+inceptionDate+"'),12)-1),EFFECTIVE_DATE=TO_DATE('"+inceptionDate+"','dd-mm-yyyy hh24:mi:ss'),ENTRY_DATE=(select "+temp+" from dual),CONTENT_TYPE_ID=? where application_no=? and quote_no=?  ";
				insertQry = "update HOME_POSITION_MASTER set CUSTOMER_ID=?,INCEPTION_DATE=sysdate,EXPIRY_DATE=(add_months(sysdate,12)-1),EFFECTIVE_DATE=sysdate,ENTRY_DATE=(select "+temp+" from dual),CONTENT_TYPE_ID=? where application_no=? and quote_no=?  ";
				
				args = new String[4];

				args[0] = ""+Integer.parseInt(customerId);
				args[1] = ""+Integer.parseInt(contentsOffice);
				args[2] = applicationNo;
				args[3] = ""+Integer.parseInt(quoteNo);
				runner.multipleUpdation(insertQry,args);

				// TrackingDetails
				setApplicationNo(applicationNo);
				setQuoteNo(quoteNo);
				updateTrackingDetails("Remarks");
				/*String error = ""; 
					error = uploadSummarySubmit(quoteNo,loginId);
				if(error.length() > 0 )
					updateTrackingDetails("Remarks");
				else
					updateTrackingDetails("Upload");*/
			}
			catch(Exception e)
			{
				System.out.println("InsertOfficePositionMaster ..."+ insertQry);
				System.out.println("Error in insertHomePositionMaster values" + e.toString());
				e.printStackTrace();
			}
		}
		System.out.println("OfficeShieldDatanew.....QuoteNo...."+newQuoteNo);
		System.out.println("OfficeShieldData.....quoteNo...."+quoteNo);
		if(rows<=0)
			return newQuoteNo;
		else
			return quoteNo;
	} //insertOfficeShieldData
	
	public synchronized String getMaxQuote(String productTypeId,String loginBra)
	{
		String current_no="";
		String sql="";
		String typeId="";
		String args[] = new String[0];
		try
		{
			args = new String[4];
			args[0] = loginBra;
			args[1] = productTypeId;
			args[2] = loginBra;
			args[3] = productTypeId;

			sql = "select nvl(max(current_no)+1,max(start_no)) from policyno_generate where type_id=(select QUOTE_TYPE_ID from BRANCH_DETAIL where BRANCH_CODE=? and PRODUCT_ID=?) and status='Y' and amend_id=(select max(amend_id) from policyno_generate where type_id=(select QUOTE_TYPE_ID from BRANCH_DETAIL where BRANCH_CODE=? and PRODUCT_ID=?))";
	
			current_no = runner.singleSelection(sql,args);
			
			args = new String[6];
			args[0] = current_no;
			args[1] = current_no;
			args[2] = loginBra;
			args[3] = productTypeId;
			args[4] = loginBra;
			args[5] = productTypeId;

			sql="";
			sql = "update policyno_generate set current_no=?,remarks=? where type_id=(select QUOTE_TYPE_ID from BRANCH_DETAIL where BRANCH_CODE=? and PRODUCT_ID=?) and status='Y' and amend_id=(select max(amend_id) from policyno_generate where type_id=(select QUOTE_TYPE_ID from BRANCH_DETAIL where BRANCH_CODE=? and PRODUCT_ID=?))";
			
			runner.multipleUpdation(sql,args);
		}
		catch(Exception e)
		{
			System.out.println("ERROR in get MaxQuote in Office Shield Bean  "+e.toString());
			e.printStackTrace();
		}
		return current_no;
	}
	
	public synchronized String getMaxApplicationNo(String productTypeId,String loginBra)
	{
		String current_no="";
		String sql="";
		String typeId="";
		String args[] = new String[0];
		try
		{
			args = new String[4];
			args[0] = loginBra;
			args[1] = productTypeId;
			args[2] = loginBra;
			args[3] = productTypeId;

			sql = "select nvl(max(current_no)+1,max(start_no)) from policyno_generate where type_id=(select APPLICATION_TYPE_ID from BRANCH_DETAIL where BRANCH_CODE=? and PRODUCT_ID=?) and status='Y' and amend_id=(select max(amend_id) from policyno_generate where type_id=(select APPLICATION_TYPE_ID from BRANCH_DETAIL where BRANCH_CODE=? and PRODUCT_ID=?))";
	
			current_no = runner.singleSelection(sql,args);
			
			sql="";
			args = new String[6];
			args[0] = current_no;
			args[1] = current_no;
			args[2] = loginBra;
			args[3] = productTypeId;
			args[4] = loginBra;
			args[5] = productTypeId;

			sql = "update policyno_generate set current_no=?,remarks=? where type_id=(select APPLICATION_TYPE_ID from BRANCH_DETAIL where BRANCH_CODE=? and PRODUCT_ID=?) and status='Y' and amend_id=(select max(amend_id) from policyno_generate where type_id=(select APPLICATION_TYPE_ID from BRANCH_DETAIL where BRANCH_CODE=? and PRODUCT_ID=?))";
			
			runner.multipleUpdation(sql,args);
		}
		catch(Exception e)
		{
			System.out.println("ERROR in get MaxApplication in Office Shield Bean  "+e.toString());
			e.printStackTrace();
		}
		return current_no;
	}

	public String getMaxAmendId(String applicationNo)
	{
		String sql = "";
		String amendId ="";
		String args[] = new String[1];
		try
		{
			args[0] = applicationNo;
			sql = "select max(amend_id)+1 from OFS_DATA where APPLICATION_NO=?";
			amendId = runner.singleSelection(sql,args);
		}
		catch(Exception e)
		{
			System.out.println("getMaxAmendId Office Shield Bean"+e.toString());
			e.printStackTrace();
		}
		amendId = amendId == null ?"0": amendId;
		System.out.println("getMaxAmendId...."+amendId);
		return amendId;
	}

	public String[][] checkContentProfessionStatus(String applicationNo,String quoteNo)
	{
		String sql = "";
		String result[][] = new String[0][0];
		String args[] = new String[2];
		try
		{
			args[0] = applicationNo;
			args[1] = quoteNo;
			sql = "select CONTENT_VALUE,ACTIVITY_PROFESSION from OFS_DATA where APPLICATION_NO=? and quote_no=?";
			result = runner.multipleSelection(sql,args);
		}
		catch(Exception e)
		{
			System.out.println("checkActivityProfession Office Shield Bean"+e.toString());
			e.printStackTrace();
		}
		System.out.println("checkActivityProfession...."+activityProfession);
		return result;
	}

	public String dateValidation()
	{
		String error="";
		String values=null;
		try
		{
			values = checkDate(dobDay+"/"+dobMonth+"/"+dobYear);
			if("Invalid".equalsIgnoreCase(values))
				error=error+"<br>*"+runner.getErrormsg("62");
		}
		catch (Exception e)
		{
			System.out.println("Exception in "+e.toString());
			e.printStackTrace();
		}
		return error;		
	}

	public String checkDate(final String strDate)
	{
		String returnVal=null;
		java.text.SimpleDateFormat df = new java.text.SimpleDateFormat("dd/MMM/yyyy");
		df.setLenient(false);
		java.text.ParsePosition pos = new java.text.ParsePosition(0);

		java.util.Date date = df.parse(strDate, pos);
		System.out.println("date ........."+ date);
		try {
			if((date == null) || (pos.getErrorIndex() != -1)) 
			{
				System.out.println("Error: " + pos.getIndex());
				if (date == null) 
				{
					System.out.println("Date is null"+strDate);
					return "Invalid";
				}
				
				if (pos.getErrorIndex() != -1) {
					return "Invalid";
				}

				return "Invalid";
			}
		} catch (Exception e) {
			System.out.println("Check Date ...." + e.toString());
			e.printStackTrace();
			return "Invalid";
		}
		return returnVal;
	}

	public String getApplicationNo(String quoteNo)
	{
		String sql = "";
		String appNo ="";
		String args[] = new String[1];
		try
		{
			args[0] = quoteNo;
			sql = "select application_no from OFS_DATA where quote_no =?";
			appNo = runner.singleSelection(sql,args);
		}
		catch(Exception e)
		{
			System.out.println("getApplicationNo Office Shield Bean"+e.toString());
			e.printStackTrace();
		}
		appNo = appNo == null ?"0": appNo;
		System.out.println("getApplicationNo...."+appNo);
		return appNo;
	}
		
	public String amountValidation(String amt)
	{
		System.out.println("amountValidation ... OFSBean.."+amt);
        try 
		{
            Double.parseDouble(amt);
            return "";
        }
		catch (NumberFormatException ex) 
		{
			System.out.println("amountValidation ... OfficeShieldBean.java"+ex);
			//ex.printStackTrace();
            return "<br> * Please Enter Valid Amount";
        }
    }
	
	public boolean validAmount(String value)
	{
		boolean flag=true;
		String validChar=null;
		try
		{
			value=value.trim();
			if(value.length()>0 && value!=null )
			{
				validChar="1234567890.";
				for(int i=0;i<value.length();i++)
				{
					if(validChar.indexOf(value.charAt(i))== -1)
					{
						flag = false;
						break;
					}
				}
			}
			else
				return flag;
		}
		catch(Exception e)
		{
			System.out.println("Valid Amount in OfficeShiledBean.java1 "+value);
			return flag;
		}
		System.out.println("Valid Amount in OSB  "+ flag);
		return flag;
	}
	

	public String getCustomerId(String quoteNo)
	{
		String sql = "";
		String custId ="";
		String args[] = new String[1];
		try
		{
			args[0] = quoteNo;
			sql = "select customer_id from home_position_master where quote_no=?";
			custId = runner.singleSelection(sql,args);
		}
		catch(Exception e)
		{
			System.out.println("getCustomerId Home Position master  OSF "+e.toString());
			e.printStackTrace();
		}
		//custId = custId == null ?"": custId;
		if(custId == null){
			custId = "";
		}
		System.out.println(" custId Data...."+custId);
		return custId;
	}
	
	public String[][] getPolicyInfo(String quoteNo,String loginId)
	{
		String sql = "";
		String policyInfo[][] = new String[0][0];
		String args[] = new String[4];
		try
		{
			args[0] = quoteNo;
			args[1] = quoteNo;
			args[2] = quoteNo;
			args[3] = loginId;

			//sql = "select a.quote_no,c.title,(c.first_name||c.company_name),c.last_name,c.address1,c.address2,d.content_description,(case when a.PROF_OTHERS is not null then (f.profession||'-'||a.PROF_OTHERS) else f.profession end),(case when a.freezone_others is not null then (g.freezone_description||'-'||a.freezone_others) else g.freezone_description end),i.CONTACT_PERSON,i.company_name,e.premium,to_char(c.dob,'dd-mm-yyyy'),c.OCCUPATION,Initcap(c.country),a.EMIRATE,a.POBOX,c.TELEPHONE,c.mobile,c.FAX,c.EMAIL,to_char(e.inception_date,'dd-mm-yyyy'),to_char(e.expiry_date,'dd-mm-yyyy'),e.summary_remarks,e.admin_summary_status,e.admin_remarks,e.admin_referral_status,nvl(e.remarks,' '),d.content_type_id,e.referral_description,a.address1,a.TELEPHONE,a.EMAIL from OFS_DATA a,OFS_ACT_PROF_MASTER b,PERSONAL_INFO c,OFS_CONTENT_MASTER d,HOME_POSITION_MASTER e,OFS_ACT_PROF_MASTER f,OFS_FREEZONE_MASTER g,LOGIN_MASTER h,BROKER_COMPANY_MASTER i  where a.quote_no=? and a.amend_id in (select max(amend_id) from OFS_DATA where quote_no=?) and a.activity_profession=b.activity_profession and a.customer_id=c.customer_id and a.content_value=d.content_type_id  and a.quote_no=e.quote_no and d.product_id=e.product_id and d.scheme_id=e.scheme_id and d.content_type_id=e.content_type_id and a.activity_profession=f.activity_profession and f.scheme_id=e.scheme_id and a.freezone=g.freezone and e.login_id=h.login_id and h.oa_code=i.agency_code and e.quote_no=?";// and e.login_id=?";
			//sql = "select a.quote_no,c.title,(c.first_name||c.company_name),c.last_name,c.address1,c.address2,d.content_description,(case when a.PROF_OTHERS is not null then (f.profession||'-'||a.PROF_OTHERS) else f.profession end),(case when a.freezone_others is not null then (g.freezone_description||'-'||a.freezone_others) else g.freezone_description end),i.CONTACT_PERSON,i.company_name,e.premium,to_char(c.dob,'dd-mm-yyyy'),c.OCCUPATION,Initcap(c.country),a.EMIRATE,a.POBOX,c.TELEPHONE,c.mobile,c.FAX,c.EMAIL,to_char(e.effective_date,'dd-mm-yyyy'),to_char(e.expiry_date,'dd-mm-yyyy'),e.summary_remarks,e.admin_summary_status,e.admin_remarks,e.admin_referral_status,nvl(e.remarks,' '),d.content_type_id,e.referral_description,a.address1,a.TELEPHONE,a.EMAIL from OFS_DATA a,OFS_ACT_PROF_MASTER b,PERSONAL_INFO c,OFS_CONTENT_MASTER d,HOME_POSITION_MASTER e,OFS_ACT_PROF_MASTER f,OFS_FREEZONE_MASTER g,LOGIN_MASTER h,BROKER_COMPANY_MASTER i  where a.quote_no=? and a.amend_id in (select max(amend_id) from OFS_DATA where quote_no=?) and a.activity_profession=b.activity_profession and a.customer_id=c.customer_id and a.content_value=d.content_type_id  and a.quote_no=e.quote_no and d.product_id=e.product_id and d.scheme_id=e.scheme_id and d.content_type_id=e.content_type_id and a.activity_profession=f.activity_profession and f.scheme_id=e.scheme_id and a.freezone=g.freezone and e.login_id=h.login_id and h.oa_code=i.agency_code and e.quote_no=? and e.login_id=?";
			sql = "SELECT   a.quote_no, c.title, (c.first_name || c.company_name), c.last_name, c.address1, c.address2,nvl((select d.content_description from OFS_CONTENT_MASTER d where a.content_value = d.content_type_id AND d.product_id = e.product_id AND d.scheme_id = e.scheme_id AND d.content_type_id = e.content_type_id),' '), (CASE WHEN a.PROF_OTHERS IS NOT NULL THEN (nvl((select f.profession from OFS_ACT_PROF_MASTER f where a.activity_profession = f.activity_profession AND f.scheme_id = e.scheme_id),' ') || '-' || a.PROF_OTHERS) ELSE nvl((select f.profession from OFS_ACT_PROF_MASTER f where a.activity_profession = f.activity_profession AND f.scheme_id = e.scheme_id),' ') END), (CASE WHEN a.freezone_others IS NOT NULL THEN (nvl((select g.freezone_description from OFS_FREEZONE_MASTER g where a.freezone = g.freezone),' ') || '-' || a.freezone_others) ELSE nvl((select g.freezone_description from OFS_FREEZONE_MASTER g where a.freezone = g.freezone),' ') END), i.CONTACT_PERSON, i.company_name, e.premium,  TO_CHAR (c.dob, 'dd-mm-yyyy'), c.OCCUPATION, INITCAP (c.country), a.EMIRATE, a.POBOX, c.TELEPHONE, c.mobile, c.FAX, c.EMAIL, TO_CHAR (e.effective_date, 'dd-mm-yyyy'), TO_CHAR (e.expiry_date, 'dd-mm-yyyy'), e.summary_remarks, e.admin_summary_status, e.admin_remarks,  e.admin_referral_status, NVL (e.remarks, ' '), nvl((select d.content_type_id from OFS_CONTENT_MASTER d where a.content_value = d.content_type_id AND d.product_id = e.product_id AND d.scheme_id = e.scheme_id AND d.content_type_id = e.content_type_id),'0'), e.referral_description, a.address1, a.TELEPHONE, a.EMAIL FROM OFS_DATA a, PERSONAL_INFO c,  HOME_POSITION_MASTER e, LOGIN_MASTER h, BROKER_COMPANY_MASTER i WHERE  a.quote_no = ?  AND a.amend_id IN (SELECT   MAX (amend_id) FROM   OFS_DATA WHERE   quote_no = ?)  AND a.customer_id = c.customer_id AND a.quote_no = e.quote_no AND e.login_id = h.login_id  AND h.oa_code = i.agency_code  AND e.quote_no = ?  AND e.login_id = ?";
			policyInfo = runner.multipleSelection(sql,args);
		}
		catch(Exception e)
		{
			System.out.println("getPolicyInfo Home Position master  OSF "+e.toString());
			e.printStackTrace();
		}
		for(int i=0;i<policyInfo.length;i++)
			policyInfo[0][i] = policyInfo[0][i] == null ? "" :policyInfo[0][i];
		System.out.println("getPolicyInfo length..."+policyInfo.length);
		return policyInfo;
	}
	
	//Added by chinna for admin
	public String[][] getAdminPolicyInfo(String quoteNo)
	{
		String sql = "";
		String policyInfo[][] = new String[0][0];
		String args[] = new String[3];
		try
		{
			args[0] = quoteNo;
			args[1] = quoteNo;
			args[2] = quoteNo;
			
			//sql = "select a.quote_no,c.title,(c.first_name||c.company_name),c.last_name,c.address1,c.address2,d.content_description,(case when a.PROF_OTHERS is not null then (f.profession||'-'||a.PROF_OTHERS) else f.profession end),(case when a.freezone_others is not null then (g.freezone_description||'-'||a.freezone_others) else g.freezone_description end),i.CONTACT_PERSON,i.company_name,e.premium,to_char(c.dob,'dd-mm-yyyy'),c.OCCUPATION,Initcap(c.country),a.EMIRATE,a.POBOX,c.TELEPHONE,c.mobile,c.FAX,c.EMAIL,to_char(e.inception_date,'dd-mm-yyyy'),to_char(e.expiry_date,'dd-mm-yyyy'),e.summary_remarks,e.admin_summary_status,e.admin_remarks,e.admin_referral_status,nvl(e.remarks,' '),d.content_type_id,e.referral_description,a.address1,a.TELEPHONE,a.EMAIL from OFS_DATA a,OFS_ACT_PROF_MASTER b,PERSONAL_INFO c,OFS_CONTENT_MASTER d,HOME_POSITION_MASTER e,OFS_ACT_PROF_MASTER f,OFS_FREEZONE_MASTER g,LOGIN_MASTER h,BROKER_COMPANY_MASTER i  where a.quote_no=? and a.amend_id in (select max(amend_id) from OFS_DATA where quote_no=?) and a.activity_profession=b.activity_profession and a.customer_id=c.customer_id and a.content_value=d.content_type_id  and a.quote_no=e.quote_no and d.product_id=e.product_id and d.scheme_id=e.scheme_id and d.content_type_id=e.content_type_id and a.activity_profession=f.activity_profession and f.scheme_id=e.scheme_id and a.freezone=g.freezone and e.login_id=h.login_id and h.oa_code=i.agency_code and e.quote_no=?";// and e.login_id=?";
			//sql = "select a.quote_no,c.title,(c.first_name||c.company_name),c.last_name,c.address1,c.address2,d.content_description,(case when a.PROF_OTHERS is not null then (f.profession||'-'||a.PROF_OTHERS) else f.profession end),(case when a.freezone_others is not null then (g.freezone_description||'-'||a.freezone_others) else g.freezone_description end),i.CONTACT_PERSON,i.company_name,e.premium,to_char(c.dob,'dd-mm-yyyy'),c.OCCUPATION,Initcap(c.country),a.EMIRATE,a.POBOX,c.TELEPHONE,c.mobile,c.FAX,c.EMAIL,to_char(e.effective_date,'dd-mm-yyyy'),to_char(e.expiry_date,'dd-mm-yyyy'),e.summary_remarks,e.admin_summary_status,e.admin_remarks,e.admin_referral_status,nvl(e.remarks,' '),d.content_type_id,e.referral_description,a.address1,a.TELEPHONE,a.EMAIL from OFS_DATA a,OFS_ACT_PROF_MASTER b,PERSONAL_INFO c,OFS_CONTENT_MASTER d,HOME_POSITION_MASTER e,OFS_ACT_PROF_MASTER f,OFS_FREEZONE_MASTER g,LOGIN_MASTER h,BROKER_COMPANY_MASTER i  where a.quote_no=? and a.amend_id in (select max(amend_id) from OFS_DATA where quote_no=?) and a.activity_profession=b.activity_profession and a.customer_id=c.customer_id and a.content_value=d.content_type_id  and a.quote_no=e.quote_no and d.product_id=e.product_id and d.scheme_id=e.scheme_id and d.content_type_id=e.content_type_id and a.activity_profession=f.activity_profession and f.scheme_id=e.scheme_id and a.freezone=g.freezone and e.login_id=h.login_id and h.oa_code=i.agency_code and e.quote_no=? and e.login_id=?";
			sql = "SELECT   a.quote_no, c.title, (c.first_name || c.company_name), c.last_name, c.address1, c.address2,nvl((select d.content_description from OFS_CONTENT_MASTER d where a.content_value = d.content_type_id AND d.product_id = e.product_id AND d.scheme_id = e.scheme_id AND d.content_type_id = e.content_type_id),' '), (CASE WHEN a.PROF_OTHERS IS NOT NULL THEN (nvl((select f.profession from OFS_ACT_PROF_MASTER f where a.activity_profession = f.activity_profession AND f.scheme_id = e.scheme_id),' ') || '-' || a.PROF_OTHERS) ELSE nvl((select f.profession from OFS_ACT_PROF_MASTER f where a.activity_profession = f.activity_profession AND f.scheme_id = e.scheme_id),' ') END), (CASE WHEN a.freezone_others IS NOT NULL THEN (nvl((select g.freezone_description from OFS_FREEZONE_MASTER g where a.freezone = g.freezone),' ') || '-' || a.freezone_others) ELSE nvl((select g.freezone_description from OFS_FREEZONE_MASTER g where a.freezone = g.freezone),' ') END), i.CONTACT_PERSON, i.company_name, e.premium,  TO_CHAR (c.dob, 'dd-mm-yyyy'), c.OCCUPATION, INITCAP (c.country), a.EMIRATE, a.POBOX, c.TELEPHONE, c.mobile, c.FAX, c.EMAIL, TO_CHAR (e.effective_date, 'dd-mm-yyyy'), TO_CHAR (e.expiry_date, 'dd-mm-yyyy'), e.summary_remarks, e.admin_summary_status, e.admin_remarks,  e.admin_referral_status, NVL (e.remarks, ' '), nvl((select d.content_type_id from OFS_CONTENT_MASTER d where a.content_value = d.content_type_id AND d.product_id = e.product_id AND d.scheme_id = e.scheme_id AND d.content_type_id = e.content_type_id),'0'), e.referral_description, a.address1, a.TELEPHONE, a.EMAIL FROM OFS_DATA a, PERSONAL_INFO c,  HOME_POSITION_MASTER e, LOGIN_MASTER h, BROKER_COMPANY_MASTER i WHERE  a.quote_no = ?  AND a.amend_id IN (SELECT   MAX (amend_id) FROM   OFS_DATA WHERE   quote_no = ?)  AND a.customer_id = c.customer_id AND a.quote_no = e.quote_no AND e.login_id = h.login_id  AND h.oa_code = i.agency_code  AND e.quote_no = ?";
			policyInfo = runner.multipleSelection(sql,args);
		}
		catch(Exception e)
		{
			System.out.println("getPolicyInfo Home Position master  OSF "+e.toString());
			e.printStackTrace();
		}
		for(int i=0;i<policyInfo.length;i++)
			policyInfo[0][i] = policyInfo[0][i] == null ? "" :policyInfo[0][i];
		System.out.println("getPolicyInfo length..."+policyInfo.length);
		return policyInfo;
	}
//End
	/** Upload Bean **/
	
	public String insertOFSUploadedData()
	{
		String temp = "sysdate";
		//temp = runner.getSysdate(branch);		
		String insertQry = "";
		String sqlQuery_ = "";
		String amendId = "";
		int rows = 0;
		String args[] = new String[0];	
		try
		{
			args = new String[1];
			args[0] = quoteNo;
			sqlQuery_ ="select count(*) from OFS_UPLOADED_DATA where quote_no=?";	
			
			String res = runner.singleSelection(sqlQuery_,args);
			if(res.length()>0 && !res.equalsIgnoreCase("null"))
			{
				rows = Integer.parseInt(res);
				System.out.println("insertOFSUploadedData rows true are.."+rows);
			}
			else
			{
				rows = 0;
				System.out.println("insertOFSUploadedData rows are.."+rows);
			}
		}
		catch(Exception e)
		{
			System.out.println("Exception in insertOFSUploadedData for Count :"+e.toString());
			e.printStackTrace();
		}
				
		System.out.println("OFSUploadedData  QuoteNo No count ..."+rows);
		
		if(rows<=0)
			amendId ="0";
		else
			amendId = getMaxAmendIdUpload(quoteNo,proId,schemeId);

			try
			{
				uploadId = getMaxUploadId(quoteNo);

				insertQry = "insert into OFS_UPLOADED_DATA(UPLOADED_ID,AMEND_ID,QUOTE_NO,COVERAGES_ID,CUSTOMER_FILE_NAME,SYSTEM_FILE_NAME,LOGIN_ID,UPLOADED_DATE,START_DATE,END_DATE,PRODUCT_ID,STATUS,SCHEME_ID,CONTENT_TYPE_ID,REMARKS) values(?,?,?,?,?,?,?,"+temp+","+temp+","+temp+",?,?,?,?,?)"; 
				
				args = new String[12];
				//args[0] = ""+Integer.parseInt(uploadId);
				args[0] = Integer.toString(Integer.parseInt(uploadId));
				
				args[1] = ""+Integer.parseInt(amendId);
				args[2] = ""+Integer.parseInt(quoteNo);
				args[3] = ""+Integer.parseInt(coverId);
				args[4] = custFileName;
				args[5] = systemFileName;
				args[6] = loginId;
				args[7] = ""+Integer.parseInt(proId);
				args[8] = "Y";
				args[9] = ""+Integer.parseInt(schemeId);
				args[10] = ""+Integer.parseInt(contentTypeId);
				args[11] = uploadRemarks;
					
				runner.multipleInsertion(insertQry,args);
			} 
			catch (Exception e) 
			{
				System.out.println("InsertOfficeShieldUploadData ..."+ insertQry);
				System.out.println("Error in insertOFShieldUploadData values" + e.toString());
				e.printStackTrace();
			}
			return quoteNo;
	}	

	public String getMaxAmendIdUpload(String quoteNo,String proId,String schemeId)
	{
		String sql = "";
		String amendId ="";
		String args[] = new String[0];
		try
		{
			
			if("30".equalsIgnoreCase(proId))
			{
				args = new String[5];
				args[0] = quoteNo;
				args[1] = contentTypeId;
				args[2] = coverId;
				args[3] = proId;
				args[4] = schemeId;
				sql = "select max(amend_id)+1 from OFS_UPLOADED_DATA where quote_no=? and CONTENT_TYPE_ID=? and coverages_id=? and product_id=? and scheme_id=?";
			}
			else
			{
				args = new String[3];
				args[0] = quoteNo;
				args[1] = proId;
				args[2] = schemeId;
				sql = "select max(amend_id)+1 from OFS_UPLOADED_DATA where quote_no=? and product_id=? and scheme_id=?";
			}
			amendId = runner.singleSelection(sql,args);
		}
		catch(Exception e)
		{
			System.out.println("getMaxAmendId OFS_UPLOADED_DATA Bean"+e.toString());
			e.printStackTrace();
		}
		amendId = amendId == null ?"0": amendId;
		System.out.println("getMaxAmendIdUpload OFSUpload Data...."+amendId);
		return amendId;
	}

	public String getMaxUploadId(String quoteNo)
	{
		String sql = "";
		String amendId ="";
		try
		{
			sql = "select nvl(max(uploaded_id),0)+1 from OFS_UPLOADED_DATA";
			amendId = runner.singleSelection(sql);
		}
		catch(Exception e)
		{
			System.out.println("getMaxUploadId OFS_UPLOADED_DATA Bean"+e.toString());
			e.printStackTrace();
		}
		amendId = amendId == null ?"0": amendId;
		System.out.println("getMaxUploadId OFSUpload Data...."+amendId);
		return amendId;
	}


	public String[][] getQuoteFileForOS(String quoteNo)
	{
		String sql="";
		String[][] values=new String[0][0];
		String args[] = new String[2];
		try
		{
			args[0] = quoteNo;
			args[1] = quoteNo;
			sql = "select hpm.Quote_No,hpm.PRODUCT_ID,hpm.SCHEME_ID,ofd.FREEZONE,ofd.CONTENT_VALUE,ofd.CONTENT_VALUE_OTHERS,ofd.ACTIVITY_PROFESSION,to_char(ofd.inception_date,'dd') as policyDay,to_char(ofd.inception_date,'MON') as policyMonth,to_char(ofd.inception_date,'YYYY') as policyYear from OFS_DATA ofd,home_position_master hpm  where ofd.quote_no=hpm.quote_no and hpm.quote_no=? and ofd.application_no=hpm.application_no and hpm.application_no=(select application_no from home_position_master where quote_no=?)";

			values = runner.multipleSelection(sql,args);
		}
		catch(Exception e)
		{
			System.out.println("getQuoteFileForOS ...Office Shiled Bean"+e.toString());
			e.printStackTrace();
		}
		return values;
	}

	/** Upload Bean **/

	public String[][] getCoverageInfo(String quoteNo,String loginId)
	{
		String sql = "";
		String[][] values = new String[0][0];
		String[][] info = new String[0][0];
		String args[] = new String[0];
		try
		{
			/*args = new String[2];
			args[0] = quoteNo;
			args[1] = loginId;
			sql = "select quote_no,product_id,scheme_id,content_type_id from home_position_master where quote_no=? and login_id=?";*/
			args = new String[1];
			args[0] = quoteNo;
			sql = "select quote_no,product_id,scheme_id,content_type_id from home_position_master where quote_no=? ";
			info = runner.multipleSelection(sql,args);
			System.out.println("Info...Length..."+info.length);
			if(info.length > 0)
			{
				args = new String[8];
				args[0] = info[0][0];
				args[1] = info[0][0];;
				args[2] = info[0][1];
				args[3] = info[0][2];
				args[4] = info[0][0];
				args[5] = info[0][1];
				args[6] = info[0][2];
				args[7] = info[0][3];

				//sql="select ofm.coverages_display_name,odd.COVERAGES_SUMINSURED,odd.COVERAGES_id from ofs_master ofm,ofs_data_details odd where odd.coverages_id=ofm.coverages_id and ofm.coverages_id in (select COVERAGES_ID from OFS_DATA_DETAILS where quote_no=? and amend_id||PRODUCT_ID||SCHEME_ID||CONTENT_TYPE_ID in (select max(amend_id)||PRODUCT_ID||SCHEME_ID||CONTENT_TYPE_ID from OFS_DATA_DETAILS where quote_no= ? and PRODUCT_ID =? and SCHEME_ID =? and COVERAGES_Y_N_OPTION ='Y' group by PRODUCT_ID,SCHEME_ID,CONTENT_TYPE_ID ) and COVERAGES_Y_N_OPTION ='Y' group by PRODUCT_ID,SCHEME_ID,CONTENT_TYPE_ID,COVERAGES_ID ) and odd.status='Y' and odd.quote_no= ? and odd.PRODUCT_ID =? and odd.SCHEME_ID =? and odd.COVERAGES_Y_N_OPTION ='Y' and odd.content_type_id=? order by odd.COVERAGES_id";//odd.COVERAGES_SUMINSURED";
				sql="select ofm.coverages_display_name,odd.COVERAGES_SUMINSURED,odd.COVERAGES_id,odd.PREMIUM_AMOUNT from ofs_master ofm,ofs_data_details odd where odd.coverages_id=ofm.coverages_id and ofm.coverages_id in (select COVERAGES_ID from OFS_DATA_DETAILS where quote_no=? and amend_id||PRODUCT_ID||SCHEME_ID||CONTENT_TYPE_ID in (select max(amend_id)||PRODUCT_ID||SCHEME_ID||CONTENT_TYPE_ID from OFS_DATA_DETAILS where quote_no= ? and PRODUCT_ID =? and SCHEME_ID =? and COVERAGES_Y_N_OPTION ='Y' group by PRODUCT_ID,SCHEME_ID,CONTENT_TYPE_ID ) and COVERAGES_Y_N_OPTION ='Y' group by PRODUCT_ID,SCHEME_ID,CONTENT_TYPE_ID,COVERAGES_ID ) and odd.status='Y' and odd.quote_no= ? and odd.PRODUCT_ID =? and odd.SCHEME_ID =? and odd.COVERAGES_Y_N_OPTION ='Y' and odd.content_type_id=? order by odd.COVERAGES_id";//odd.COVERAGES_SUMINSURED";
				values = runner.multipleSelection(sql,args);
			}
		}
		catch(Exception e)
		{
			System.out.println("getCoverageInfo ...Office Shiled Bean1"+e.toString());
			e.printStackTrace();
		}
		System.out.println("getCoverageInfo....Length ...: "+values.length);
		return values;
	}

	public String[][] getCoverageUploadOption(String quoteNo,String loginId)
	{
		String sql = "";
		String[][] values = new String[0][0];
		String[][] info = new String[0][0];
		String args[] = new String[0];
		try
		{
			/*args = new String[2];
			args[0] = quoteNo;
			args[1] = loginId;
			sql = "select quote_no,product_id,scheme_id,content_type_id from home_position_master where quote_no=? and login_id=?";*/
			args = new String[1];
			args[0] = quoteNo;
			sql = "select quote_no,product_id,scheme_id,content_type_id from home_position_master where quote_no=? ";
			info = runner.multipleSelection(sql,args);
			System.out.println("Info...Length..."+info.length);
			if(info.length > 0)
			{
				args = new String[12];
				args[0] = info[0][3];
				args[1] = info[0][1];
				args[2] = info[0][2];
				args[3] = info[0][0];
				args[4] = info[0][0];
				args[5] = info[0][1];
				args[6] = info[0][2];
				args[7] = info[0][0];
				args[8] = info[0][1];
				args[9] = info[0][2];
				args[10] = info[0][3];
				args[11] = info[0][0];


				//sql ="select ofm.coverages_display_name,ofcm.COVERAGES_ID,ofcm.UPLOAD_OPTION,odd.CONTENT_TYPE_ID,odd.quote_no,odd.SCHEME_ID,odd.product_id from ofs_master ofm,OFS_COVERAGES_MASTER ofcm,ofs_data_details odd where odd.coverages_id=ofcm.coverages_id and ofcm.coverages_id=ofm.coverages_id and ofcm.coverages_id in (select COVERAGES_ID from OFS_DATA_DETAILS where quote_no=? and amend_id||PRODUCT_ID||SCHEME_ID||CONTENT_TYPE_ID in (select max(amend_id)||PRODUCT_ID||SCHEME_ID||CONTENT_TYPE_ID from OFS_DATA_DETAILS where quote_no= ? and PRODUCT_ID =? and SCHEME_ID =? and COVERAGES_Y_N_OPTION ='Y' group by PRODUCT_ID,SCHEME_ID,CONTENT_TYPE_ID ) and COVERAGES_Y_N_OPTION ='Y' group by PRODUCT_ID,SCHEME_ID,CONTENT_TYPE_ID,COVERAGES_ID) and odd.status='Y' and odd.quote_no= ? and ofcm.PRODUCT_ID =?  and ofcm.SCHEME_ID =? and odd.COVERAGES_Y_N_OPTION ='Y' and odd.content_type_id=ofcm.content_type_id and odd.content_type_id=? and ofcm.upload_option='Y' ";
				
				sql = "select ofm.coverages_display_name,ofcm.COVERAGES_ID,ofcm.UPLOAD_OPTION,odd.CONTENT_TYPE_ID,odd.quote_no,odd.SCHEME_ID,odd.product_id from ofs_master ofm,OFS_COVERAGES_MASTER ofcm,ofs_data_details odd where odd.coverages_id=ofcm.coverages_id and ofcm.coverages_id=ofm.coverages_id and ofcm.amend_id||ofcm.coverages_id in (select max(amend_id)||coverages_id from OFS_COVERAGES_MASTER where CONTENT_TYPE_ID=?  and product_id=? and scheme_id=? group by coverages_id) and ofcm.coverages_id in (select COVERAGES_ID from OFS_DATA_DETAILS where quote_no=? and amend_id||PRODUCT_ID||SCHEME_ID||CONTENT_TYPE_ID in (select max(amend_id)||PRODUCT_ID||SCHEME_ID||CONTENT_TYPE_ID from OFS_DATA_DETAILS where quote_no= ? and PRODUCT_ID =?  and SCHEME_ID =? and COVERAGES_Y_N_OPTION ='Y' group by PRODUCT_ID,SCHEME_ID,CONTENT_TYPE_ID ) and COVERAGES_Y_N_OPTION ='Y'  group by PRODUCT_ID,SCHEME_ID,CONTENT_TYPE_ID,COVERAGES_ID) and odd.status='Y' and odd.quote_no= ? and ofcm.PRODUCT_ID =?  and ofcm.SCHEME_ID =? and odd.COVERAGES_Y_N_OPTION ='Y' and odd.content_type_id=ofcm.content_type_id and odd.content_type_id=? and ofcm.upload_option='Y'  and ( to_date((select to_char(inception_date,'dd-mm-yyyy') from home_position_master where quote_no=?),'dd-mm-yyyy') between ofcm.effective_date and ofcm.expiry_date) order by ofcm.COVERAGES_ID";
				// and ( to_date((select to_char(inception_date,'dd-mm-yyyy') from home_position_master where quote_no='1000001629'),'dd-mm-yyyy') between ofcm.effective_date and ofcm.expiry_date) 
				values = runner.multipleSelection(sql,args);
			}
		}
		catch(Exception e)
		{
			System.out.println("getCoverageUploadOption ...Office Shiled Bean1"+e.toString());
			e.printStackTrace();
		}
		System.out.println("getCoverageUploadOption....Length ...: "+values.length);
		return values;
	}
	
	public String getCustFileName(String quoteNo,String contentType,String coverId,String schemeId,String proId)
	{
		String sql = "";
		String custFileName = "";
		String args[] = new String[12];
		try
		{
			args[0] = quoteNo;
			args[1] = quoteNo;
			args[2] = proId;
			args[3] = schemeId;
			args[4] = contentType;
			args[5] = coverId;
			args[6] = "Y";
			args[7] = proId;
			args[8] = schemeId;
			args[9] = contentType;
			args[10] = coverId;
			args[11] = "Y";

			sql ="select customer_file_name from OFS_UPLOADED_DATA where quote_no=? and amend_id||PRODUCT_ID||SCHEME_ID||CONTENT_TYPE_ID in (select max(amend_id)||PRODUCT_ID||SCHEME_ID||CONTENT_TYPE_ID from OFS_UPLOADED_DATA where quote_no= ? and PRODUCT_ID =? and SCHEME_ID =? and content_type_id=? and coverages_id=? and status=? group by PRODUCT_ID,SCHEME_ID,CONTENT_TYPE_ID ) and PRODUCT_ID =? and SCHEME_ID =? and content_type_id=? and coverages_id=? and status=?";
			custFileName = runner.singleSelection(sql,args);
		}
		catch(Exception e)
		{
			System.out.println("getCustFileName ...Office Shiled "+e.toString());
			e.printStackTrace();
		}
		System.out.println("getCustFileName....Length ...: "+custFileName.length());
		return custFileName;
	}
	
	public String[][] getCustFileNameForDelete(String quoteNo,String contentType)
	{
		String sql = "";
		String custFileName[][] = new String[0][0];
		String args[] = new String[5];
		try
		{
			args[0] = "Y";
			args[1] = quoteNo;
			args[2] = "Y";
			args[3] = quoteNo;
			args[4] = contentType;
			//sql ="select customer_file_name,UPLOADED_ID from OFS_UPLOADED_DATA where quote_no='"+quoteNo+"' and amend_id||PRODUCT_ID||SCHEME_ID||CONTENT_TYPE_ID in (select amend_id||PRODUCT_ID||SCHEME_ID||CONTENT_TYPE_ID from OFS_UPLOADED_DATA where quote_no= '"+quoteNo+"' and PRODUCT_ID ='"+proId+"' and SCHEME_ID ='"+schemeId+"' and content_type_id='"+contentType+"' and coverages_id='"+coverId+"' group by amend_id,PRODUCT_ID,SCHEME_ID,CONTENT_TYPE_ID ) and PRODUCT_ID ='"+proId+"' and SCHEME_ID ='"+schemeId+"' and content_type_id='"+contentType+"' and coverages_id='"+coverId+"'";
			sql ="select customer_file_name,UPLOADED_ID,coverages_id from OFS_UPLOADED_DATA where status = ? and amend_id in( select amend_id from OFS_UPLOADED_DATA where quote_no = ? and status = ? group by amend_id ) and quote_no = ? and content_type_id=? order by coverages_id,amend_id";
			custFileName = runner.multipleSelection(sql,args);
		}
		catch(Exception e)
		{
			System.out.println("getCustFileName ...Office Shiled "+e.toString());
			e.printStackTrace();
		}
		System.out.println("getCustFileName....Length ...: "+custFileName.length);
		return custFileName;
	}
	
	public void getCustFileDelete(String uploadId)
	{
		String sqlQuery_ = "";
		String args[] = new String[2];
			
		try
		{

			args[0] = "N";
			args[1] = uploadId;
			//sqlQuery_ ="delete from OFS_UPLOADED_DATA where UPLOADED_ID = ?";	
			sqlQuery_ ="update OFS_UPLOADED_DATA set status=? where UPLOADED_ID = ?";	
			runner.multipleUpdation(sqlQuery_,args);	
		}
		catch(Exception e)
		{
			System.out.println("Exception in getCustFileDelete : "+e.toString());
			e.printStackTrace();
		}
	}

	public String[][] getFidelityUW(String quoteNo,String contentType,String coverId,String schemeId,String proId) // old
	{
		String sql="";
		String fidelityUW[][] = new String[0][0];
		String args[] = new String[6];
		try
		{
			args[0] = coverId;
			args[1] = quoteNo;
			args[2] = quoteNo;
			args[3] = proId;
			args[4] = schemeId;
			args[5] =contentType;

			sql = "select mas.COVERAGES_DISPLAY_NAME,sub.COVERAGES_COVERED_EMPLOYEES from ofs_data_sub_details sub,ofs_master mas where sub.coverages_id=? and mas.coverages_id=sub.coverages_sub_id and sub.quote_No=? and sub.amend_id||sub.PRODUCT_ID||sub.SCHEME_ID||sub.CONTENT_TYPE_ID in (select max(amend_id)||PRODUCT_ID||SCHEME_ID||CONTENT_TYPE_ID from ofs_data_sub_details where quote_no= ? and PRODUCT_ID =? and SCHEME_ID =? and content_type_id=? group by product_id,scheme_id,content_type_id) and sub.coverages_sub_id not in(18)";
			fidelityUW = runner.multipleSelection(sql,args);
		}
		catch(Exception e)
		{
			System.out.println("getFidelityUW..."+e);
			e.printStackTrace();
		}
		System.out.println("getFidelityUW....length..."+fidelityUW.length);
		return fidelityUW;
	}
	
	public String[][] getFidelityUWQuestion(String quoteNo,String coverId)
	{
		String sql="";
		String args[] = new String[0];
		String info[][] = new String[0][0];
		String fidelityUW[][] = new String[0][0];
		try
		{
			args = new String[1];
			args[0] = quoteNo;
			sql = "select quote_no,product_id,scheme_id,content_type_id from home_position_master where quote_no=?";
			info = runner.multipleSelection(sql,args);
			System.out.println("Info.getFidelityUWQuestion.Length..."+info.length);
			if(info.length > 0 )
			{
				args = new String[6];
				args[0] = coverId;
				args[1] = quoteNo;
				args[2] = quoteNo;
				args[3] = info[0][1];
				args[4] = info[0][2];
				args[5] = info[0][2];
				sql = "select mas.COVERAGES_DISPLAY_NAME,sub.COVERAGES_COVERED_EMPLOYEES from ofs_data_sub_details sub,ofs_master mas where sub.coverages_id=? and mas.coverages_id=sub.coverages_sub_id and sub.quote_No=? and sub.amend_id||sub.PRODUCT_ID||sub.SCHEME_ID||sub.CONTENT_TYPE_ID in (select max(amend_id)||PRODUCT_ID||SCHEME_ID||CONTENT_TYPE_ID from ofs_data_sub_details where quote_no= ? and PRODUCT_ID =? and SCHEME_ID =? and content_type_id=? group by product_id,scheme_id,content_type_id) and sub.coverages_sub_id not in(18)";
			}
			fidelityUW = runner.multipleSelection(sql,args);
		}
		catch(Exception e)
		{
			System.out.println("getFidelityUW..."+e);
			e.printStackTrace();
		}
		System.out.println("getFidelityUW....length..."+fidelityUW.length);
		return fidelityUW;
	}

	/** Portable Equipment Add Info - Start**/
	public String portableEquipmentInsert(String quoteNo,HashMap portHash,String coverId)
	{
		String result = "";
		String amendId = "";
		String insertQry = "";		
		String portEquipId = "";
		String temp = "";
		String year = "";
		String replace = "";
		String equipDesc = "";
		String args[] = new String[8];
		amendId = getMaxPortableAmendId(quoteNo);
		
		try
		{
			for(int i=0;i<portHash.size();i++)
			{
					temp = (String)portHash.get("port"+(i+1));
					int count=0;
					StringTokenizer st = new StringTokenizer(temp,"~");
					count = st.countTokens();
					System.out.println("...Count..."+count);
					while(st.hasMoreElements())
					{
						year = st.nextToken();
						equipDesc = st.nextToken();
						replace = st.nextToken();
					}
					insertQry ="insert into OFS_PORTABLE_EQUIPMENT (PORTABLE_EQUIPMENT_ID,QUOTE_NO,AMEND_ID,MAKE_YEAR,EQUIPMENT_DESCRIPTION,REPLACEMENT_VALUE,COVERAGES_ID,STATUS) values(?,?,?,?,?,?,?,?)";
					
					portEquipId = getPortEquipId(quoteNo);

					args[0] = ""+Integer.parseInt(portEquipId);
					args[1] = ""+ Integer.parseInt(quoteNo);
					args[2] = ""+Integer.parseInt(amendId);
					args[3] = ""+Integer.parseInt(year);
					args[4] = equipDesc;
					args[5] = ""+ Double.parseDouble(replace);
					args[6] = ""+Integer.parseInt(coverId);
					args[7] = "Y";
					
					runner.multipleInsertion(insertQry,args);
			} // For Loop
		}
		catch(Exception e)
		{
			System.out.println("portableEquipmentInsert... Insert .."+e);
				e.printStackTrace();
		} 
		return result;
	}
	
	public String getMaxPortableAmendId(String quoteNo)
	{
		String args[] = new String[1];
		String sql = "";
		String amendId ="";
		try
		{
			args[0] = quoteNo;
			sql = "select nvl(max(amend_id),0)+1 from OFS_PORTABLE_EQUIPMENT where quote_no=?";
			amendId = runner.singleSelection(sql,args);
		}
		catch(Exception e)
		{
			System.out.println("getMaxPortableAmendId OFS_PORTABLE_EQUIPMENT Bean"+e.toString());
			e.printStackTrace();
		}
		amendId = amendId == null ?"0": amendId;
		System.out.println("getMaxPortableAmendId Portable Data...."+amendId);
		return amendId;
	}

	public String getPortEquipId(String quoteNo)
	{
		String sql = "";
		String portEquipId ="";
		try
		{
			sql = "select nvl(max(PORTABLE_EQUIPMENT_ID),0)+1 from OFS_PORTABLE_EQUIPMENT";
			portEquipId = runner.singleSelection(sql);
		}
		catch(Exception e)
		{
			System.out.println("getPortEquipId OFS_PORTABLE_EQUIPMENT Bean"+e.toString());
			e.printStackTrace();
		}
		portEquipId = portEquipId == null ?"0": portEquipId;
		System.out.println("getPortEquipId Office Shield Bean Data...."+portEquipId);
		return portEquipId;
	}

	public String[][] getPortableEquipmentDetails(String quoteNo,String coverageId)
	{
		String args[] = new String[4];
		String sql ="";
		String [][]details = new String[0][0];
		try
		{
			args[0] = quoteNo;
			args[1] = coverageId;
			args[2] = quoteNo;
			args[3] = coverageId;
			sql = "select MAKE_YEAR,EQUIPMENT_DESCRIPTION,REPLACEMENT_VALUE,PORTABLE_EQUIPMENT_ID from OFS_PORTABLE_EQUIPMENT where quote_no=? and coverages_id=? and amend_id =(select max(amend_id) from OFS_PORTABLE_EQUIPMENT where quote_no=? and coverages_id=?)";
			details = runner.multipleSelection(sql,args);
		}
		catch(Exception e)
		{
			System.out.println("getPortableEquipmentDetails..."+e);
			e.printStackTrace();
		}
		System.out.println("getPortableEquipmentDetails ..length .."+details.length);
		return details;
	}

	/** Portable Equipment Add Info - End **/
	
	/** FIDELITY GUARANTEE old- Start **/
	
	public String fidelityGuaranteeInsert(String quoteNo,HashMap fidelityHash,String coverId)
	{
		String result = "";
		String amendId = "";
		String insertQry = "";		
		String fidelityId = "";
		String temp = "";
		String name = "";
		String dob = "";
		String designation = "";
		String annualSalary = "";
		String coverage = "";
		String dd = "";
		String mon = "";
		String yr = "";
		String args[] = new String[9];

		amendId = getMaxFidelityGuaranteeAmendId(quoteNo);
		
		try
		{
			for(int i=0;i<fidelityHash.size();i++)
			{
					temp = (String)fidelityHash.get("fidelity"+(i+1));
					int count=0;
					StringTokenizer st = new StringTokenizer(temp,"~");
					count = st.countTokens();
					System.out.println("...Count..."+count);
					while(st.hasMoreElements())
					{
						name = st.nextToken();
						designation = st.nextToken();
						annualSalary = st.nextToken();
						coverage = st.nextToken();
						dob = st.nextToken();
					}
					insertQry ="insert into OFS_FIDELITY_GUARANTEE (FIDELITY_GUARANTEE_ID,QUOTE_NO,AMEND_ID,NAME,DOB,DESIGNATION,ANNUAL_SALARY,COVERAGE,COVERAGES_ID,STATUS) values(?,?,?,?,TO_DATE('"+dob+"','dd-mm-yyyy'),?,?,?,?,?)";
					
					fidelityId = getFidelityGuaranteeId(quoteNo);

					args[0] = ""+Integer.parseInt(fidelityId);
					args[1] = ""+Integer.parseInt(quoteNo);
					args[2] = ""+Integer.parseInt(amendId);
					args[3] = name;
					args[4] = designation;
					args[5] = ""+Double.parseDouble(annualSalary);
					args[6] = coverage;
					args[7] = ""+Integer.parseInt(coverId);
					args[8] = "Y";
					
					runner.multipleInsertion(insertQry,args);
			} // For Loop
		}
		catch(Exception e)
		{
			System.out.println("portableEquipmentInsert... Insert .."+e);
				e.printStackTrace();
		} 
		return result;
	}
	
	/** FIDELITY GUARANTEE NEW - Start **/
	
	public String fidelityGuaranteeInsertNew(String quoteNo,HashMap fidelityHash,String coverId)
	{
		String result = "";
		String amendId = "";
		String insertQry = "";		
		String fidelityId = "";
	
		String temp = "";
		String name = "";
		String dob = "";
		String designation = "";
		String annualSalary = "";
		String coverage = "";
		String dd = "";
		String mon = "";
		String yr = "";
		String args[] = new String[7];
		amendId = getMaxFidelityGuaranteeAmendId(quoteNo);
		
		try
		{
			for(int i=0;i<fidelityHash.size();i++)
			{
					temp = (String)fidelityHash.get("fidelity"+(i+1));
					int count=0;
					StringTokenizer st = new StringTokenizer(temp,"~");
					count = st.countTokens();
					System.out.println("...Count..."+count);
					while(st.hasMoreElements())
					{
						name = st.nextToken();
						designation = st.nextToken();
					}
					insertQry ="insert into OFS_FIDELITY_GUARANTEE (FIDELITY_GUARANTEE_ID,QUOTE_NO,AMEND_ID,NAME,DESIGNATION,COVERAGES_ID,STATUS) values(?,?,?,?,?,?,?)";
					
					fidelityId = getFidelityGuaranteeId(quoteNo);
					
					args[0] = ""+Integer.parseInt(fidelityId);
					args[1] = ""+Integer.parseInt(quoteNo);
					args[2] = ""+Integer.parseInt(amendId);
					args[3] = name;
					args[4] = designation;
					args[5] = ""+Integer.parseInt(coverId);
					args[6] = "Y";

					runner.multipleInsertion(insertQry,args);
			} // For Loop
		}
		catch(Exception e)
		{
			System.out.println("fidelityGuaranteeInsertNew... Insert .."+e);
				e.printStackTrace();
		} 
		return result;
	}

	public String getMaxFidelityGuaranteeAmendId(String quoteNo)
	{
		String sql = "";
		String amendId ="";
		String args[] = new String[1];
		try
		{
			args[0] = quoteNo;
			sql = "select nvl(max(amend_id),0)+1 from OFS_FIDELITY_GUARANTEE where quote_no=?";
			amendId = runner.singleSelection(sql,args);
		}
		catch(Exception e)
		{
			System.out.println("getMaxFidelityGuaranteeAmendId OFS_FIDELITY_GUARANTEE Bean"+e.toString());
			e.printStackTrace();
		}
		amendId = amendId == null ?"0": amendId;
		System.out.println("getMaxFidelityGuaranteeAmendId Portable Data...."+amendId);
		return amendId;
	}

	public String getFidelityGuaranteeId(String quoteNo)
	{
		String sql = "";
		String fidelityId ="";
		try
		{
			sql = "select nvl(max(FIDELITY_GUARANTEE_ID),0)+1 from OFS_FIDELITY_GUARANTEE";
			fidelityId = runner.singleSelection(sql);
		}
		catch(Exception e)
		{
			System.out.println("getFidelityGuaranteeId OFS_FIDELITY_GUARANTEE Bean"+e.toString());
			e.printStackTrace();
		}
		fidelityId = fidelityId == null ?"0": fidelityId;
		System.out.println("getFidelityGuaranteeId Office Shield Bean Data...."+fidelityId);
		return fidelityId;
	}

	public String dateValidation(String dob)
	{
		String error="";
		String values=null;
		try
		{
			values = checkDate(dob);
			if("Invalid".equalsIgnoreCase(values))
				error=error+"<br>*"+runner.getErrormsg("62");
		}
		catch (Exception e)
		{
			System.out.println("Exception in dateValidation "+e.toString());
			e.printStackTrace();
		}
		return error;		
	}

	public String[][] getFidelityDetails(String quoteNo,String coverageId)
	{

		String sql ="";
		String [][]details = new String[0][0];
		String args[] = new String[4];
		try
		{
			args[0] = quoteNo;
			args[1] = coverageId;
			args[2] = quoteNo;
			args[3] = coverageId;

			//sql = "select name,designation,annual_salary,coverage,to_char(dob,'dd'),to_char(dob,'MON'),to_char(dob,'yyyy') from OFS_FIDELITY_GUARANTEE where quote_no=? and coverages_id=? and amend_id =(select max(amend_id) from OFS_FIDELITY_GUARANTEE where quote_no=? and coverages_id=?)";
			sql = "select NAME,DESIGNATION,FIDELITY_GUARANTEE_ID from OFS_FIDELITY_GUARANTEE where quote_no=? and coverages_id=? and amend_id =(select max(amend_id) from OFS_FIDELITY_GUARANTEE where quote_no=? and coverages_id=?)";
			details = runner.multipleSelection(sql,args);
		}
		catch(Exception e)
		{
			System.out.println("getFidelityDetails..."+e);
			e.printStackTrace();
		}
		System.out.println("getFidelityDetails ..length .."+details.length);
		return details;
	}

	/** FIDELITY GUARENTEE END **/

	/** GROUP PERSONAL ACCIDENT **/

	public String groupPersonalInsert(String quoteNo,HashMap fidelityHash,String coverId)
	{
		String result = "";
		String amendId = "";
		String insertQry = "";		
		String gpAccid = "";
		String temp = "";
		String name = "";
		String dob = "";
		String designation = "";
		String annualSalary = "";
		String coverage = "";
		String dd = "";
		String mon = "";
		String yr = "";
		String capSumIns = "";
		String args[] = new String[9];
		amendId = getMaxGPAcidAmendId(quoteNo);
		
		try
		{
			for(int i=0;i<fidelityHash.size();i++)
			{
					temp = (String)fidelityHash.get("fidelity"+(i+1));
					int count=0;
					StringTokenizer st = new StringTokenizer(temp,"~");
					count = st.countTokens();
					System.out.println("...Count..."+count);
					while(st.hasMoreElements())
					{
						name = st.nextToken();
						designation = st.nextToken();
						annualSalary = st.nextToken();
						//coverage = st.nextToken();
						dob = st.nextToken();
						capSumIns = st.nextToken();
					}
					insertQry ="insert into OFS_PERSONAL_ACCIDENT (PERSONAL_ACCIDENT_ID,QUOTE_NO,AMEND_ID,NAME,DOB,DESIGNATION,ANNUAL_SALARY,COVERAGES_ID,STATUS,CAPITAL_SUM_INSURED_REQUIRED) values(?,?,?,?,TO_DATE('"+dob+"','dd-mm-yyyy'),?,?,?,?,?)";
					
					gpAccid = getGPAccidentId(quoteNo);
					
					args[0] = ""+Integer.parseInt(gpAccid);
					args[1] = ""+Integer.parseInt(quoteNo);
					args[2] = ""+Integer.parseInt(amendId);
					args[3] = name;
					args[4] = designation;
					args[5] = ""+Double.parseDouble(annualSalary);
					args[6] = ""+Integer.parseInt(coverId);
					args[7] = "Y";
					args[8] = capSumIns;

					runner.multipleInsertion(insertQry,args);
			} // For Loop
		}
		catch(Exception e)
		{
			System.out.println("groupPersonalInsert... Insert .."+e);
				e.printStackTrace();
		} 
		return result;
	}

	public String getGPAccidentId(String quoteNo)
	{
		String sql = "";
		String gpAccid ="";
		try
		{
			sql = "select nvl(max(PERSONAL_ACCIDENT_ID),0)+1 from OFS_PERSONAL_ACCIDENT";
			gpAccid = runner.singleSelection(sql);
		}
		catch(Exception e)
		{
			System.out.println("getGPAccidentId OFS_PERSONAL_ACCIDENT Bean"+e.toString());
			e.printStackTrace();
		}
		gpAccid = gpAccid == null ?"0": gpAccid;
		System.out.println("getGPAccidentId Office Shield Bean Data...."+gpAccid);
		return gpAccid;
	}
	
	public String getMaxGPAcidAmendId(String quoteNo)
	{
		String sql = "";
		String amendId ="";
		String args[] = new String[1];
		try
		{
			args[0] = quoteNo;
			sql = "select nvl(max(amend_id),0)+1 from OFS_PERSONAL_ACCIDENT where quote_no=?";
			amendId = runner.singleSelection(sql,args);
		}
		catch(Exception e)
		{
			System.out.println("getMaxGPAcidAmendId OFS_PERSONAL_ACCIDENT Bean"+e.toString());
			e.printStackTrace();
		}
		amendId = amendId == null ?"0": amendId;
		System.out.println("getMaxGPAcidAmendId Portable Data...."+amendId);
		return amendId;
	}

	public String[][] getGPADetails(String quoteNo,String coverageId)
	{
		String sql ="";
		String [][]details = new String[0][0];
		String args[] = new String[4];
		try
		{
			args[0] = quoteNo;
			args[1] = coverageId;
			args[2] = quoteNo;
			args[3] = coverageId;
			sql = "select NAME,DESIGNATION,ANNUAL_SALARY,COVERAGE,TO_CHAR(DOB,'DD'),TO_CHAR(DOB,'MON'),TO_CHAR(DOB,'YYYY'),CAPITAL_SUM_INSURED_REQUIRED,PERSONAL_ACCIDENT_ID from OFS_PERSONAL_ACCIDENT where quote_no=? and coverages_id=? and amend_id =(select max(amend_id) from OFS_PERSONAL_ACCIDENT where quote_no=? and coverages_id=?)";
			details = runner.multipleSelection(sql,args);
		}
		catch(Exception e)
		{
			System.out.println("getGPADetails..."+e);
			e.printStackTrace();
		}
		System.out.println("getGPADetails ..length .."+details.length);
		return details;
	}
	
	/** GROUP PERSONAL ACCIDENT **/		

	/** TRAVEL BAGGAGE - START **/

	public String travelBaggageInsert(String quoteNo,HashMap fidelityHash,String coverId)
	{
		String result = "";
		String amendId = "";
		String insertQry = "";		
		String travelId = "";
		String temp = "";
		String name = "";
		String dob = "";
		String designation = "";
		String annualSalary = "";
		String coverage = "";
		String dd = "";
		String mon = "";
		String yr = "";
		String args[] = new String[7];
		amendId = getMaxTravelBaggageAmendId(quoteNo);
		
		try
		{
			for(int i=0;i<fidelityHash.size();i++)
			{
					temp = (String)fidelityHash.get("fidelity"+(i+1));
					int count=0;
					StringTokenizer st = new StringTokenizer(temp,"~");
					count = st.countTokens();
					System.out.println("...Count..."+count);
					while(st.hasMoreElements())
					{
						name = st.nextToken();
						designation = st.nextToken();
					}
					insertQry ="insert into OFS_TRAVEL_BAGGAGE (TRAVEL_BAGGAGE_ID,QUOTE_NO,AMEND_ID,NAME,DESIGNATION,COVERAGES_ID,STATUS) values(?,?,?,?,?,?,?)";
					
					travelId = getTravelBaggageId(quoteNo);

					args[0] = ""+Integer.parseInt(travelId);
					args[1] = ""+Integer.parseInt(quoteNo);
					args[2] = ""+Integer.parseInt(amendId);
					args[3] = name;
					args[4] = designation;
					args[5] = ""+Integer.parseInt(coverId);
					args[6] = "Y";
					
					runner.multipleInsertion(insertQry,args);
				
			} // For Loop
		}
		catch(Exception e)
		{
			System.out.println("travelBaggageInsert... Insert .."+e);
				e.printStackTrace();
		} 
		return result;
	}

	public String getMaxTravelBaggageAmendId(String quoteNo)
	{
		String args[] = new String[1];
		String sql = "";
		String amendId ="";
		try
		{
			args[0] = quoteNo;
			sql = "select nvl(max(amend_id),0)+1 from OFS_TRAVEL_BAGGAGE where quote_no=?";
			amendId = runner.singleSelection(sql,args);
		}
		catch(Exception e)
		{
			System.out.println("getMaxTravelBaggageAmendId OFS_TRAVEL_BAGGAGE Bean"+e.toString());
			e.printStackTrace();
		}
		amendId = amendId == null ?"0": amendId;
		System.out.println("getMaxTravelBaggageAmendId Data...."+amendId);
		return amendId;
	}

	public String getTravelBaggageId(String quoteNo)
	{
		String sql = "";
		String TravelId ="";
		try
		{
			sql = "select nvl(max(TRAVEL_BAGGAGE_ID),0)+1 from OFS_TRAVEL_BAGGAGE";
			TravelId = runner.singleSelection(sql);
		}
		catch(Exception e)
		{
			System.out.println("getTravelBaggageId OFS_TRAVEL_BAGGAGE Bean"+e.toString());
			e.printStackTrace();
		}
		TravelId = TravelId == null ?"0": TravelId;
		System.out.println("getTravelBaggageId Office Shield Bean Data...."+TravelId);
		return TravelId;
	}

	public String[][] getTravelBaggageDetails(String quoteNo,String coverageId)
	{
		String args[] = new String[4];
		String sql ="";
		String [][]details = new String[0][0];
		try
		{
			args[0] = quoteNo;
			args[1] = coverageId;
			args[2] = quoteNo;
			args[3] = coverageId;
			sql = "select NAME,DESIGNATION,TRAVEL_BAGGAGE_ID from OFS_TRAVEL_BAGGAGE where quote_no=? and coverages_id=? and amend_id =(select max(amend_id) from OFS_TRAVEL_BAGGAGE where quote_no=? and coverages_id=?)";
			details = runner.multipleSelection(sql,args);
		}
		catch(Exception e)
		{
			System.out.println("getTravelBaggageDetails..."+e);
			e.printStackTrace();
		}
		System.out.println("getTravelBaggageDetails ..length .."+details.length);
		return details;
	}

	/** TRAVEL BAGGAGE END **/

	public String uploadSummarySubmit(String quoteNo,String loginId)
	{
		runner run = new runner();
		String error = "";
		String info[][] = new String[0][0];
		
		boolean flag = true;
		try
		{
			info = getCoverageUploadOption(quoteNo,loginId);

			int chk[][] = new int[info.length][info.length];
			int upldChk[][] = new int[info.length][info.length];

			for(int i=0;i<info.length;i++)
				chk[0][i] = coverageFileDataChecking(info[i][4],info[i][1],info[i][3],info[i][5],info[i][6],"AddInfo");
			for(int i=0;i<info.length;i++)
				upldChk[0][i] = coverageFileDataChecking(info[i][4],info[i][1],info[i][3],info[i][5],info[i][6],"UploadData");
			
			for(int i =0;i<chk.length;i++)
			{
				System.out.println("addInfo.."+chk[0][i]+"...Upload"+upldChk[0][i]);
				if( chk[0][i] > 0 || upldChk[0][i] > 0 ) 
				{
					flag = true;
				}
				else
				{
					flag = false;
					error = error+"<br>* Please Enter Coverage File Upload Details for "+info[i][0];
					//break;
				}
			}
		}
		catch(Exception e)
		{
			System.out.println("uploadSummarySubmit ..."+e);
			e.printStackTrace();
		}
		return error;
	}

	public int coverageFileDataChecking(String quoteNo, String coverId,String contentTypeId, String schemeId,String proId,String upld)
	{
		int rows = 0;
		String sql = "";
		String args[] = new String[0];
		String empCount = "0";
		try
		{
			args = new String[4];
			args[0] = quoteNo;
			args[1] = coverId;
			args[2] = quoteNo;
			args[3] = coverId;

			if(coverId.equalsIgnoreCase("2"))
			{
				sql = "select count(*) from OFS_PORTABLE_EQUIPMENT where quote_no=? and coverages_id=? and amend_id=(select max(amend_id) from OFS_PORTABLE_EQUIPMENT where quote_no=? and coverages_id=?)";
			}
			if(coverId.equalsIgnoreCase("8"))
			{
				sql = "select count(*) from OFS_FIDELITY_GUARANTEE where quote_no=? and coverages_id=? and amend_id=(select max(amend_id) from OFS_FIDELITY_GUARANTEE where quote_no=? and coverages_id=?)";
			}
			if(coverId.equalsIgnoreCase("9"))
			{
				sql = "select count(*) from OFS_TRAVEL_BAGGAGE where quote_no=? and coverages_id=? and amend_id=(select max(amend_id) from OFS_TRAVEL_BAGGAGE where quote_no=? and coverages_id=?)";
			}
			if(coverId.equalsIgnoreCase("10"))
			{
				sql = "select count(*) from OFS_PERSONAL_ACCIDENT where quote_no=? and coverages_id=? and amend_id=(select max(amend_id) from OFS_PERSONAL_ACCIDENT where quote_no=? and coverages_id=?)";
			}
			if(upld.equalsIgnoreCase("UploadData"))
			{
				args = new String[12];
				args[0] = quoteNo;
				args[1] = coverId;
				args[2] = contentTypeId;
				args[3] = proId;
				args[4] = schemeId;
				args[5] = "Y";
				args[6] = quoteNo;
				args[7] = coverId;
				args[8] = contentTypeId;
				args[9] = proId;
				args[10] = schemeId;
				args[11] = "Y";

				sql = "select count(*) from OFS_UPLOADED_DATA where quote_no=? and coverages_id=? and content_type_id=? and product_id=? and scheme_id=? and status=? and amend_id=(select max(amend_id) from OFS_UPLOADED_DATA where quote_no=? and coverages_id=? and content_type_id=? and product_id=? and scheme_id=? and status=?)";
			}
		
			String res="";
			if(!sql.equalsIgnoreCase(""))
			{
				res = runner.singleSelection(sql,args);
				if(res.length() > 0 && !res.equalsIgnoreCase("null"))
					rows	=	Integer.parseInt(res);
				else
					rows	=	0;
			}
			if (coverId.equalsIgnoreCase("10") && !upld.equalsIgnoreCase("UploadData")) // New Jan16 GPA
			{
				empCount = getNoOfEmpForGPA(quoteNo, proId, schemeId, coverId,contentTypeId);
				if (rows != Integer.parseInt(empCount))
					rows = 0;
			}
			if (coverId.equalsIgnoreCase("8") && !upld.equalsIgnoreCase("UploadData")) // New Jan16 FG
			{
				empCount = getNoOfEmpForFG(quoteNo, proId, schemeId, coverId,contentTypeId);
				if (rows != Integer.parseInt(empCount))
					rows = 0;
			}

			System.out.println("....coverageFileDataChecking..res."+res+"....coverageFileDataChecking..rows."+rows);
		}
		catch(Exception e)
		{
			System.out.println("coverageFileDataChecking....."+sql);
			System.out.println("coverageFileDataChecking....."+e);
			e.printStackTrace();
		}
		return rows;
	}

	public void updateAdminReferal(String quoteNo,String remarks,String proId,String schemeId)
	{
		String sql = "";
		String args[] = new String[4];
		try
		{
			args[0] = remarks;
			args[1] = quoteNo;
			args[2] = proId;
			args[3] = schemeId;

			sql = "update HOME_POSITION_MASTER set REMARKS='Referal',SUMMARY_REMARKS=?,ADMIN_SUMMARY_STATUS='Y', REFERRAL_DESCRIPTION='Admin Referral' where quote_no=? and PRODUCT_ID=? and scheme_Id=?";
					
			runner.multipleUpdation(sql,args);
		}
		catch(Exception e)
		{
			System.out.println("..updateAdminReferal..."+e);
				e.printStackTrace();
		}
	}
	
	public void updateAdminReferalForDeclaration(String quoteNo[],String remarks,String proId,String schemeId)
	{
		String sql = "";
		String refStatus =",27,";
		String args[] = new String[4];
		try
		{
			for(int i=0; i<quoteNo.length;i++)
			{
				/*sql = "update HOME_POSITION_MASTER set REMARKS='Referal',SUMMARY_REMARKS=?,ADMIN_SUMMARY_STATUS='Y', REFERRAL_DESCRIPTION='Declaration Referal' where quote_no=? and PRODUCT_ID=? and scheme_Id=?";
				psd.setString(1, remarks);
				psd.setString(2, quoteNo[i]);
				psd.setString(3, proId);
				psd.setString(4, schemeId);*/
				
				sql = "update HOME_POSITION_MASTER set REMARKS='Referal',REFERRAL_DESCRIPTION=? where quote_no=? and product_id=? and scheme_Id=?";
				
				args[0] = refStatus;
				args[1] = quoteNo[i];
				args[2] = proId;
				args[3] = schemeId;
				
				runner.multipleUpdation(sql,args);
			}
		}
		catch(Exception e)
		{
			System.out.println("..updateAdminReferal..."+e);
			e.printStackTrace();
		}
	}

	public String[][] homePositionMasterDateSelect(String quoteNo,String proId,String schemeId)
	{
		String result[][] =	new String[0][0];
		String sql="";
		String args[] = new String[3];
		try
		{
	
			sql = "select 	to_char(effective_date,'DD'),to_char(effective_date,'MM'),to_char(effective_date,'YYYY'),to_char(EXPIRY_DATE,'DD'),to_char(EXPIRY_DATE,'MM'),to_char(EXPIRY_DATE,'YYYY'),premium,product_id from home_position_master where quote_no=? and product_id=? and scheme_id=?";
			args[0] = quoteNo;
			args[1] = proId;
			args[2] = schemeId;
			result	= runner.multipleSelection(sql,args);
		}
		catch(Exception e)
		{
			System.out.println("homePositionMasterDateSelect--"+e);
			e.printStackTrace();
		}
		return result;
	}
	
	/** For Declaration - START **/
	public String[][] getQuotesForDeclaration(String loginId,String proId,String schemeId)
	{
		String temp = "sysdate";
		//temp = runner.getSysdate(branch);		
		String sql ="";
		String [][]details = new String[0][0];
		try 
		{
			//sql = "select a.quote_no,nvl(b.COMPANY_NAME,b.first_name),a.premium,a.login_id from home_position_master a,personal_info b,ofs_data c where a.login_id in ('"+loginId+"') and a.EXPIRY_DATE >= (select sysdate from dual) and a.status='Y' and (a.remarks is null or a.remarks ='Admin') and (a.ADMIN_REFERRAL_STATUS is null or a.ADMIN_REFERRAL_STATUS='N') and a.premium is not null and b.customer_id=a.customer_id and a.customer_id=c.customer_id and a.quote_no=c.quote_no and c.INSURED_ADDRESS_DIFFERENT='Y' and a.product_id='"+proId+"' and a.scheme_id='"+schemeId+"' order by quote_no DESC";

			sql = "select a.quote_no,nvl(b.COMPANY_NAME,b.first_name),a.premium,a.login_id,(case when d.remarks='Upload' then 'Completed' else 'Info Missing' end),to_char(a.EFFECTIVE_DATE,'dd-mm-YYYY') from home_position_master a,personal_info b,ofs_data c,tracking_master d where a.login_id in ('"+loginId+"') and a.EXPIRY_DATE >= (select "+temp+" from dual) and a.status='Y' and d.remarks!='Policy' and (a.remarks is null or a.remarks ='Admin') and (a.ADMIN_REFERRAL_STATUS is null or a.ADMIN_REFERRAL_STATUS='N') and a.premium is not null and b.customer_id=a.customer_id and a.customer_id=c.customer_id and a.quote_no=c.quote_no and a.quote_no=d.quote_no and a.product_id=d.product_id and a.product_id='"+proId+"' and a.scheme_id='"+schemeId+"' order by quote_no DESC";
			details = runner.multipleSelection(sql);
		}
		catch(Exception e)
		{
			System.out.println("getQuotesForDeclaration..."+e);
			e.printStackTrace();
		}
		System.out.println("getQuotesForDeclaration ..length .."+details.length);
		return details;
	}

	public HashMap declarationQuoteStatus(String[][] quoteNo,String loginId)
	{
		runner run = new runner();
		String msg = "";
		HashMap declare = new HashMap();
		String info[][] = new String[0][0];
		boolean flag = true;
		for(int c=0; c<quoteNo.length; c++)
		{
			try
			{
				info = getCoverageUploadOption(quoteNo[c][0],loginId);

				int chk[][] = new int[info.length][info.length];
				int upldChk[][] = new int[info.length][info.length];

				for(int i=0;i<info.length;i++)
					chk[0][i] = coverageFileDataChecking(info[i][4],info[i][1],info[i][3],info[i][5],info[i][6],"AddInfo");
				for(int i=0;i<info.length;i++)
					upldChk[0][i] = coverageFileDataChecking(info[i][4],info[i][1],info[i][3],info[i][5],info[i][6],"UploadData");
				
				for(int i =0;i<chk.length;i++)
				{
					System.out.println("addInfo.."+chk[0][i]+"...Upload"+upldChk[0][i]);
					if( chk[0][i] > 0 || upldChk[0][i] > 0 ) 
					{
						flag = true;
						msg = "completed";
					}
					else
					{
						flag = false;
						msg = "Info Missing";
					}
				}
			}
			catch(Exception e)
			{
				System.out.println("uploadSummarySubmit ..."+e);
				e.printStackTrace();
			}
			declare.put("declare"+quoteNo[c][0],msg);
		}
		return declare;
	}
	
	public String customerIdChkingForDeclaration(String quoteNos,String proId,String schemeId)
	{
		int rows = 0;
		String sql = "";
		String msg = "";
		String res = "";
		try
		{
			sql = "select count(distinct hpm.customer_id) from home_position_master hpm,ofs_data odd,personal_info pi where pi.customer_id =odd.customer_id and pi.customer_id=odd.customer_id and odd.quote_no=hpm.quote_no and hpm.quote_no in ("+quoteNos+") and hpm.product_id='"+proId+"' and hpm.scheme_id='"+schemeId+"'";
			
			res = runner.singleSelection(sql);
			if(res.length() > 0)
				rows	=	Integer.parseInt(res);
			else
				rows	=	0;
			System.out.println("<<..customerIdChkingForDeclaration..>>"+sql);
		}
		catch(Exception e)
		{
			System.out.println("customerIdChkingForDeclaration...1.."+sql);
			System.out.println("customerIdChkingForDeclaration..2..."+e);
			e.printStackTrace();
		}
		if(rows == 1)
			msg = "Sucess";
		else
			msg ="Fail";
		System.out.println("customerIdChkingForDeclaration ... msg"+msg);
		return msg;
	}

	public String[][] getDeclarationQuoteDetails(String quoteNos,String proId,String schemeId)
	{
		String sql ="";
		String [][]details = new String[0][0];
		try
		{
			sql = "select hpm.quote_no,hpm.premium,(pi.first_name||pi.company_name),hpm.customer_id,hpm.login_id from home_position_master hpm,ofs_data odd,personal_info pi where pi.customer_id =odd.customer_id and pi.customer_id=odd.customer_id and odd.quote_no=hpm.quote_no and hpm.quote_no in ("+quoteNos+") and hpm.product_id='"+proId+"' and hpm.scheme_id='"+schemeId+"' order by hpm.quote_no DESC";
			details = runner.multipleSelection(sql);
		}
		catch(Exception e)
		{
			System.out.println("getDeclarationQuoteDetails..."+e);
			e.printStackTrace();
		}
		System.out.println("getDeclarationQuoteDetails ..length .."+details.length);
		return details;
	}
	/** For Declaration - END **/
	
	public String getBackDatesAllowed(String loginId, String pid,String schemeId)
	{
		String args[] = new String[2];
		String result 		= "0";
		String backDateQry 	= "";
		try
		{
			args[0] = loginId;
			args[1] = pid;
			backDateQry = "select BACK_DATE_ALLOWED from login_user_details where agency_code in(select agency_code from login_master where login_id=?) and product_id=?";
			result = runner.singleSelection(backDateQry,args);
		}
        catch(Exception ex)
		{
			System.out.println("getBackDatesAllowed..."+backDateQry);
              ex.printStackTrace();
        }
		if(result==null)
			result = "0";
        return result;
    }
	
	public String[][] getTodaysDate()
	{
		String temp = "sysdate";
		//temp = runner.getSysdate(branch);		
		String query = "";
        String[][] result = new String[0][0];
        try
		{
			query = "select extract(year from "+temp+") ,extract(month from "+temp+"),extract(day from "+temp+") from dual";
            result = runner.multipleSelection(query);
        }
        catch(Exception ex)
		{
			System.out.println("getTodaysDate..."+ex);
            ex.printStackTrace();
        }
        return result;
    }
	
	public String[][] getTodaysDate(final String qnoBranch,final String status)throws BaseException
	{
		LogManager.push("getTodaysDate method()");
		LogManager.debug(ENTER);
		DataSelect homeData = null;
		homeData = new DataSelect();
		final String sysDat = homeData.getSysdateTime(qnoBranch,status);
		final String[][] result = runner.multipleSelection("select extract(year from "+sysDat+") ,extract(month from "+sysDat+"),extract(day from "+sysDat+") from dual");
		LogManager.debug(EXIT);
		LogManager.popRemove();
		return result;
	}
	
	/** Back Date Validation **/
	public StringBuffer validateInsuranceStartDate(String insStartDate,String loginId,String productId,String schemeId)
	{
		DateFunction df = new DateFunction();
		
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		String inceptionDD = "";
		String inceptionMonth = "";
		String inceptionYear = "";
		String mnth = "";
		String tempErr = "";
		StringBuffer error = new StringBuffer();
		String mm1 = "";
		String[][] serverDate =  new String[0][0];
		try
		{
			if(insStartDate.length() > 0)
			{
				inceptionDD = insStartDate.substring(0,insStartDate.indexOf("-"));
				inceptionMonth = insStartDate.substring(insStartDate.indexOf("-")+1,insStartDate.lastIndexOf("-"));
				inceptionYear =  insStartDate.substring(insStartDate.lastIndexOf("-")+1,insStartDate.length());
			}
			if (inceptionDD.equalsIgnoreCase("Select") || inceptionMonth.equalsIgnoreCase("Select") || inceptionYear.equalsIgnoreCase("Select")) 
			{
				error.append("" + runner.getErrormsg("62"));
				System.out.println("Error..................." + error);
				return error;
			}
		}
		catch(Exception e)
		{
			System.out.println("Summary Upload Insurance Start Date .."+insStartDate);
			e.printStackTrace();
		}
		System.out.println("Summary Upload Insurance Start Date .."+insStartDate);
		System.out.println("Summary Upload Insurance Start Date After Parsing.."+inceptionDD+"-"+inceptionMonth+"-"+inceptionYear);

		mm1 = inceptionMonth;
		String[] months={"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
		for(int m=0;m<months.length;m++)
		{
			if(months[m].equalsIgnoreCase(mm1))
			{
				 mm1 = ""+(m+1);
				 break;
			}
		}
		
		for(int m=0;m<months.length;m++)
		{
			if(m == Integer.parseInt(inceptionMonth))
			{
				 mnth = months[m+1] ;
				 break;
			}
		}

		//Jan
		setDobDay(inceptionDD);
		setDobMonth(mnth);
		setDobYear(inceptionYear);
		tempErr = dateValidation();

		try {
			c2.set(Integer.parseInt(inceptionYear), Integer.parseInt(mm1),Integer.parseInt(inceptionDD));
		}
		catch (Exception e) {
			e.printStackTrace();
			error.append("" + runner.getErrormsg("62"));
			return error;
		}
				
		serverDate = getTodaysDate();

		if(serverDate.length>0)
			c1.set(Integer.parseInt(serverDate[0][0]),Integer.parseInt(serverDate[0][1]),Integer.parseInt(serverDate[0][2]));
		
		long diff = 0;
		 try{
			   diff = df.getDayDifference(c2,c1);
		 }
		 catch(Exception ex){
			   ex.printStackTrace();
		 }
		
		int backDates = 0;
		String backD = "0";	
			
		backD = getBackDatesAllowed(loginId,productId,schemeId);
		
		try{
			if(backD!=null&&!backD.equals(""))
				backDates = Integer.parseInt(backD);
		}
		catch(Exception e){
			backDates = 0;
			e.printStackTrace();
		}

		if(tempErr.length() > 0)
		{
			error.append(tempErr);
		}
		
		if(error.length() == 0 && diff > backDates)
		{
			if(backDates == 0)
				 error.append(" Insurance start date should not be less than today date");
			else if(backDates > 0)
			  error.append(" Back dates allowed maximum "+backDates+" days only");
		}
		return error;
	}
	/** Back Date Validation **/
	
	public String[][] getNoOfEmpForFidelity(String quoteNo,String proId,String schemeId,String coverId,String conTypId)
	{
		String args[] = new String[0];
		String sql ="";
		String [][]details = new String[0][0];
		String qry = "";
		String values[][] = new String[0][0];
		try
		{
			args = new String[1];
			args[0] = quoteNo;
			qry = "select product_id,scheme_id,content_type_id from home_position_master where quote_no=?";
			values = runner.multipleSelection(qry,args);
		
			if(values.length > 0)
			{
				args = new String[10];
				args[0] = proId;
				args[1] = values[0][1];
				args[2] = conTypId;
				args[3] = coverId;
				args[4] = quoteNo;
				args[5] = proId;
				args[6] = values[0][1];
				args[7] = conTypId;
				args[8] = coverId;
				args[9] = quoteNo;

				sql ="select QUOTE_NO,COVERAGES_ID,COVERAGES_SUB_ID,COVERAGES_COVERED_EMPLOYEES from OFS_DATA_SUB_DETAILS where amend_id=(select max(amend_id) from ofs_data_sub_details where product_id=? and scheme_id=? and content_type_id=? and coverages_id=? and coverages_sub_id='18' and quote_no=?) and product_id=? and scheme_id=? and content_type_id=? and coverages_id=? and coverages_sub_id='18' and quote_no=?";
				details = runner.multipleSelection(sql,args);
			}
		}
		catch(Exception e)
		{
			System.out.println("getNoOfEmpForFidelity..."+e);
			e.printStackTrace();
		}
		System.out.println("getNoOfEmpForFidelity ..length .."+details.length);
		return details;
	}
	
	public boolean validEmpDOB18(String dd1, String mm1, String yy1)
	{
		String temp = "sysdate";
		//temp = runner.getSysdate(branch);		
		int yy = 0; 
        int mm = 0;
        int dd = 0; 
		int age = 0;
		int month = 0;
        int days  = 0;
		boolean flag = false;
		String query = "";
		String date = "";
		runner run = new runner();
		String[] months={"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
		String[] monNo={"1","2","3","4","5","6","7","8","9","10","11","12"}; 
		String[][] result = new String[0][0];
		for(int i=0;i<months.length;i++)
		{
		   if(monNo[i].equalsIgnoreCase(mm1))
				mm = i+1;
		}    
		yy = Integer.parseInt(yy1);	
		dd = Integer.parseInt(dd1);
		date = dd+"-"+mm1 +"-"+yy;
		query = "select  trunc( months_between( "+temp+", '"+date+"' ) /12 ) Years ,mod( trunc( months_between( "+temp+", '"+date+"' ) ), 12 ) months ,to_date(to_char("+temp+",'dd-mon-yyyy'))  - add_months(to_date('"+date+"'),trunc( months_between( "+temp+", '"+date+"' ) )) days from dual";
		try
	    { 
	       result = run.multipleSelection(query);
           if(result != null && result.length != 0)
		   {
				result[0][1] = result[0][1]==null?"0":result[0][1];
				result[0][2] = result[0][2]==null?"0":result[0][2];
				month = Integer.parseInt(result[0][1]);
				days  = Integer.parseInt(result[0][2]);
				age = Integer.parseInt(result[0][0]);
            }
		}
		catch(Exception e)
		{
			System.out.println("validEmpDOB18..."+e);
			e.printStackTrace();
		}
		System.out.println("validEmpDOB18 ...DOB ..Age is .."+age);

		if(age >= 18 && age <= 65)
			flag = true;
		else
			flag = false;

		return flag;
	}

	// For Declaration Portfolio

	//public String[][] getDeclarations(String loginIds,String searchOption,String searchValue,String productId,String policyNo)
	public String[][] getDeclarations(String loginIds,String productId,String policyNo)
	{
		HashMap getsTotal = new HashMap();
		String[][] declare = new String[0][0];
		String  sql = "";
		try
		{
			String[][] valuess = new String[0][0];
			String loginAllIds="";
			sql="select login_id from login_master where agency_code=(select agency_code from login_master where login_id='"+loginIds+"') and login_id not in('NONE','NON')";
			valuess = runner.multipleSelection(sql);
			for(int i=0;i<valuess.length;i++)
			{
				loginAllIds="'"+valuess[i][0]+"',"+loginAllIds;
			}
			if(loginAllIds.length()>0)
				loginAllIds=loginAllIds.substring(0,loginAllIds.lastIndexOf(','));
			getsTotal.put("total",""+valuess.length);
			getsTotal.put("getCus",valuess);
			
			sql="select a.policy_no,a.customer_id,a.OVERALL_PREMIUM,to_char(a.EFFECTIVE_DATE,'dd')as days,to_char(a.EFFECTIVE_DATE,'MM')as months,to_char(a.EFFECTIVE_DATE,'YYYY')as years,nvl(b.COMPANY_NAME,b.first_name),b.last_name,a.login_id,a.quote_no,b.COMPANY_NAME from home_position_master a,personal_info b where a.login_id in ("+loginAllIds+") and lower(a.status)=lower('P') and b.customer_id=a.customer_id and a.product_id='"+productId+"' and nvl(a.declaration_status,'0') in ('LINKED')  and a.policy_no in ('"+policyNo+"') order by a.inception_date desc";

			declare = runner.multipleSelection(sql);
			System.out.println(" Declaration Policies length  "+declare.length);
		}
		catch(Exception e)
		{
			System.out.println("getTotalPolicy123 for porfolio selection qry.."+e.toString());
			e.printStackTrace();
		}
		return declare;
	}
	
	public String[][] getQuoteNosForDeclaration(String policyNo,String pid)
	{
		String sql ="";
		String [][]qnos = new String[0][0];
		try
		{
			sql ="select QUOTE_NO from home_position_master where policy_no in('"+policyNo+"') and product_id='"+pid+"' and  nvl(declaration_status,'0') in ('LINKED') and status='P' ";
			qnos = runner.multipleSelection(sql);
		}
		catch(Exception e)
		{
			System.out.println("getQuoteNosForDeclaration..."+e);
			e.printStackTrace();
		}
		System.out.println("getQuoteNosForDeclaration ..length .."+sql);
		return qnos;
	}

	//Broker Side Declaration Referal
	public HashMap getViewReferalDeclaration(String loginIds,String option,String productId,String custId,String schemeId)
	{
		HashMap refDetails = new HashMap();
		String loginids="";
		String[][] valuess=new String[0][0];
		String[][] ss=new String[0][0];
		String[][] ss1=new String[0][0];
		String remarks = "";
		String msql = "";
		String declarationRef = ",27,";
		String[][] empByBroker=new String[0][0];
		if(option.equalsIgnoreCase("app"))
			remarks = "Admin";
		else if(option.equalsIgnoreCase("unapp"))
			remarks = "Referal";
		try
		{
            String  sql = "select login_id from login_master where agency_code=(select agency_code from login_master where login_id='"+loginIds+"') and login_id not in('NONE','NON')";
			valuess = runner.multipleSelection(sql);
			empByBroker=valuess;
			for(int i=0;i<valuess.length;i++)
			{
				loginids="'"+valuess[i][0]+"',"+loginids;
			}
			if(loginids.length()>0)
				loginids=loginids.substring(0,loginids.lastIndexOf(','));
			if(!option.equalsIgnoreCase("rej"))
			{
				sql="select a.quote_no,a.customer_id,to_char(a.entry_date,'dd')as days,to_char(a.entry_date,'MM')as months,to_char(a.entry_date,'YYYY')as years,to_char(a.INCEPTION_DATE,'dd')as days,to_char(a.INCEPTION_DATE,'MM')as months,to_char(a.INCEPTION_DATE,'YYYY')as years,initcap(b.first_name),initcap(b.LAST_NAME),a.login_id,b.COMPANY_NAME ,a.REFERRAL_DESCRIPTION from home_position_master a,personal_info b where a.login_id in ("+loginids+") and a.status='Y' and b.customer_id=a.customer_id and a.customer_id='"+custId+"' and (a.REMARKS in ('"+remarks+"')) and a.REFERRAL_DESCRIPTION='"+declarationRef+"' and a.product_id in("+productId+") and a.scheme_id='"+schemeId+"' order by a.quote_no desc";

				msql = "select a.customer_id,initcap(b.first_name||b.LAST_NAME),a.login_id,b.COMPANY_NAME,a.REFERRAL_DESCRIPTION,count(a.quote_no) from home_position_master a,personal_info b where a.login_id in ("+loginids+") and a.status='Y' and b.customer_id=a.customer_id and a.customer_id='"+custId+"' and (a.REMARKS in ('"+remarks+"'))  and a.REFERRAL_DESCRIPTION='"+declarationRef+"' and a.product_id in("+productId+") and a.scheme_id='"+schemeId+"' group by a.customer_id,initcap(b.first_name||b.LAST_NAME),a.login_id,b.COMPANY_NAME,a.REFERRAL_DESCRIPTION";
			}
			else if(option.equalsIgnoreCase("rej"))
			{
				sql="select a.quote_no,a.customer_id,to_char(a.entry_date,'dd')as days,to_char(a.entry_date,'MM')as months,to_char(a.entry_date,'YYYY')as years,to_char(a.INCEPTION_DATE,'dd')as days,to_char(a.INCEPTION_DATE,'MM')as months,to_char(a.INCEPTION_DATE,'YYYY')as years,initcap(b.first_name),initcap(b.LAST_NAME),a.login_id,b.COMPANY_NAME ,a.REFERRAL_DESCRIPTION from home_position_master a,personal_info b where a.login_id in ("+loginids+") and a.status='R' and b.customer_id=a.customer_id and a.customer_id='"+custId+"' and a.ADMIN_REFERRAL_STATUS='N' and a.REFERRAL_DESCRIPTION='"+declarationRef+"' and a.product_id in("+productId+") and a.scheme_id='"+schemeId+"' order by a.quote_no desc";
				
				msql = "select a.customer_id,initcap(b.first_name||b.LAST_NAME),a.login_id,b.COMPANY_NAME,a.REFERRAL_DESCRIPTION,count(a.quote_no) from home_position_master a,personal_info b where a.login_id in ("+loginids+") and a.status='R' and b.customer_id=a.customer_id and a.customer_id='"+custId+"' and (a.REMARKS in ('"+remarks+"'))  and a.REFERRAL_DESCRIPTION='"+declarationRef+"' and a.product_id in("+productId+") and a.scheme_id='"+schemeId+"' group by a.customer_id,initcap(b.first_name||b.LAST_NAME),a.login_id,b.COMPANY_NAME,a.REFERRAL_DESCRIPTION";
			}
			ss = runner.multipleSelection(sql);
			ss1 = runner.multipleSelection(msql);
			refDetails.put("Single",ss);
			refDetails.put("Multiple",ss1);
		}
		catch(Exception e)
		{
			System.out.println("Exception in gettin refferal data"+e.toString());
            e.printStackTrace();
		}

		return refDetails;
	}

	// Admin Side Referal HomePendingPolicy
	public String[][] getOfficeReferalByCustomer(String pid,String branchCode,String brokerCodes)
	{
		String sql = "";
		String[][] ss = new String[0][0];
		String syntax = "";
		String refStatus =",27,";
		if(brokerCodes.equalsIgnoreCase("") || brokerCodes.length() ==0)
		{
			syntax = "select agency_code from broker_company_master where branch_code in("+branchCode+")";
		}
		else
		{
			brokerCodes = brokerCodes.replaceAll(",","','");
			syntax = "'"+brokerCodes+"'";
		}
		try
		{
			sql = "select a.customer_id,count(a.customer_id),(p.first_name||' '||p.company_name) from home_position_master a,personal_info p where a.REMARKS= 'Referal' and a.status='Y' and a.product_id in("+pid+") and a.customer_id=p.customer_id and a.login_id in(select login_id from login_master where oa_code in("+syntax+")) group by a.customer_id,(p.first_name||' '||p.company_name) order by a.customer_id desc";
			ss = runner.multipleSelection(sql);
		}
		catch(Exception e)
		{
			System.out.println("Exception in getOfficeReferalByCustomer "+e.toString());
			e.printStackTrace();
		}
		return ss;
	}
	
	// Admin Referal Pendings/Approved/Reject  Customer - Only For Office Declaration
	public String[][] getHomeReferalByDate(String custId,String type,String pid,String branchCode,String brokerCodes)
	{
		String[][] ss=new String[0][0];
		String sql="";
		try
		{
			String syntax="";
			if(brokerCodes.equalsIgnoreCase("") || brokerCodes.length() ==0)
			{
				syntax = "select agency_code from broker_company_master where branch_code in("+branchCode+")";
			}
			else
			{
				brokerCodes = brokerCodes.replaceAll(",","','");
				syntax = "'"+brokerCodes+"'";
			}
			
			//sql = "select distinct pm.application_no,pm.login_id,pm.quote_no,pm.REFERRAL_DESCRIPTION,nvl(pm.SUMMARY_REMARKS,'') from home_position_master pm where to_char(pm.entry_date,'YYYY-MM-DD')='"+eDate+"' and pm.product_id in("+pid+") and pm.REMARKS='"+type+"' and pm.referral_description=',27,' and pm.status='Y' and pm.login_id in(select login_id from login_master where oa_code in("+syntax+")) order by pm.quote_no desc";
			System.out.println("getHomeReferalByDate type ..."+type);
			
			if(!type.equalsIgnoreCase("Rejected"))
			{
				sql = "select distinct pm.application_no,pm.login_id,pm.quote_no,pm.REFERRAL_DESCRIPTION,nvl(pm.SUMMARY_REMARKS,'') from home_position_master pm where pm.customer_id='"+custId+"' and pm.product_id in("+pid+") and pm.REMARKS='"+type+"' and pm.referral_description=',27,' and pm.status='Y' and pm.login_id in(select login_id from login_master where oa_code in("+syntax+")) order by pm.quote_no desc";
			}
			else
			{
				sql = "select distinct pm.application_no,pm.login_id,pm.quote_no,pm.REFERRAL_DESCRIPTION,nvl(pm.SUMMARY_REMARKS,'') from home_position_master pm where pm.customer_id='"+custId+"' and pm.product_id in("+pid+") and pm.status='R' and pm.referral_description=',27,' and pm.login_id in(select login_id from login_master where oa_code in("+syntax+")) order by pm.quote_no desc";
			}
			ss = runner.multipleSelection(sql);
		}
		catch(Exception e)
		{
			System.out.println("Exception in getting refferal get Home ReferalByDate branchCode"+e.toString());
		}
		return ss;
	}

	public void updateDeclarationReferralStatus(String quoteNo,String adminReferalRemarks,String Remarks,String pid,String schemeId)
	{
		String qry="";
		
		if("Y".equalsIgnoreCase(Remarks))
			qry = "update HOME_POSITION_MASTER set REMARKS='Admin',ADMIN_REFERRAL_STATUS='N' ,status='Y',admin_remarks=' "+adminReferalRemarks+"' where quote_no in("+quoteNo+") and product_id="+pid+" and scheme_id="+schemeId;
		else if("N".equalsIgnoreCase(Remarks))
			qry = "update HOME_POSITION_MASTER set REMARKS='Referal',ADMIN_REFERRAL_STATUS='N' ,status='R',admin_remarks=' "+adminReferalRemarks+"' where quote_no in("+quoteNo+") and product_id="+pid+" and scheme_id="+schemeId;
		else if("A".equalsIgnoreCase(Remarks))
			qry = "update HOME_POSITION_MASTER set REMARKS='Referal',ADMIN_REFERRAL_STATUS='N' ,status='Y',admin_remarks=' "+adminReferalRemarks+"' where quote_no in("+quoteNo+") and product_id="+pid+" and scheme_id="+schemeId;

       try 
	   {
		   System.out.println("updateReferralStatus OSB" + qry);
		   runner.updation(qry);
	   }
	   catch (Exception ex) 
	   {
		   System.out.println("updateReferralStatus OSB" + qry);
		   System.out.println("updateReferralStatus OSB" + ex.toString());
		   ex.printStackTrace();		
	   }
	}

	// Admin Side Declaration Referal HomeApprovedPolicy

	public String[][] getOfficeDeclarationApproved(String pid,String branchCode,String brokerCodes)
	{
		String pname="";
       	String[][] ss=new String[0][0];
		String sql="";
     	String syntax="";
		
		if(brokerCodes.equalsIgnoreCase("") || brokerCodes.length() ==0)
		{
			syntax = "select agency_code from broker_company_master where branch_code in("+branchCode+")";
		}
		else
		{
			brokerCodes = brokerCodes.replaceAll(",","','");
			syntax = "'"+brokerCodes+"'";
		}

		try
		{
			String fullTravelIds = "";
			String produc_ids = "";
			sql = "select a.customer_id,count(a.customer_id),(p.first_name+p.company_name) from home_position_master a,personal_info p where (a.REMARKS='Admin') and a.referral_description=',27,' and a.status='Y' and a.product_id in("+pid+") and a.customer_id=p.customer_id and a.login_id in(select login_id from login_master where oa_code in("+syntax+")) group by a.customer_id,(p.first_name+p.company_name) order by a.customer_id desc";
            ss = runner.multipleSelection(sql);
		}
		catch(Exception e)
		{
			System.out.println("Exception in getOfficeDeclarationApproved branch Code "+e.toString());
			e.printStackTrace();
		}
		return ss;
	}

	// Admin Side Declaration Referal HomeRejectedPolicy

	public String[][] getOfficeDeclarationRejected(String pid,String branchCode,String brokerCodes)
	{
		String pname="";
       	String[][] ss=new String[0][0];
		String sql="";
       	String syntax="";
		
		if(brokerCodes.equalsIgnoreCase("") || brokerCodes.length() ==0)
		{
			syntax = "select agency_code from broker_company_master where branch_code in("+branchCode+")";
		}
		else
		{
			brokerCodes = brokerCodes.replaceAll(",","','");
			syntax = "'"+brokerCodes+"'";
		}

		try
		{
			String fullTravelIds = "";
			String produc_ids = "";
			sql = "select a.customer_id,count(a.customer_id),(p.first_name+' '+p.company_name) from home_position_master a,personal_info p where (a.REMARKS='Admin' or a.REMARKS='Referal') and a.referral_description=',27,' and a.status='R' and a.product_id in("+pid+") and a.customer_id=p.customer_id and a.login_id in(select login_id from login_master where oa_code in("+syntax+")) group by a.customer_id,(p.first_name+' '+p.company_name) order by a.customer_id desc";
            ss = runner.multipleSelection(sql);
		}
		catch(Exception e)
		{
			System.out.println("Exception in getOfficeDeclarationRejected "+e.toString());
			e.printStackTrace();
		}
		return ss;
	}

	// Portfolio Download Files..

	public HashMap getCoverageFileDownload(String quoteNo,String status)
	{
		String sql = "";
		HashMap downloadInfo = new HashMap();
		String[][] values = new String[0][0];
		String[][] info = new String[0][0];
		String args[] = new String[0];
		/*
		if(status.equalsIgnoreCase("Single"))
			sql = "select quote_no,product_id,scheme_id,content_type_id from home_position_master where quote_no='"+quoteNo+"'";
		else
			sql = "select quote_no,product_id,scheme_id,content_type_id from home_position_master where quote_no in (select quote_no from home_position_master where policy_no='"+quoteNo+"' and nvl(declaration_status,0) in ('LINKED') )";
		*/
		args = new String[1];
		args[0] = quoteNo;
		if(quoteNo.indexOf("/") == -1)
			sql ="select quote_no,product_id,scheme_id,content_type_id from home_position_master where quote_no=?";
		else
			sql ="select quote_no,product_id,scheme_id,content_type_id from home_position_master where policy_no=?";

		try
		{
			info = runner.multipleSelection(sql,args);
			System.out.println("Info...Length..."+info.length);
			for(int i=0;i<info.length;i++)
			{
				//sql ="select a.QUOTE_NO,a.COVERAGES_ID,a.CUSTOMER_FILE_NAME,a.SYSTEM_FILE_NAME,b.COVERAGES_DISPLAY_NAME,MAX(a.AMEND_ID) from ofs_uploaded_data a,ofs_master b where a.quote_no='"+info[i][0]+"' and a.coverages_id=b.coverages_id and a.amend_id||a.coverages_id in (select max(a.amend_id)||coverages_id from ofs_uploaded_data where quote_no='"+info[i][0]+"' and coverages_id in (select ofcm.COVERAGES_ID from ofs_master ofm,OFS_COVERAGES_MASTER ofcm,ofs_data_details odd where odd.coverages_id=ofcm.coverages_id and ofcm.coverages_id=ofm.coverages_id and ofcm.coverages_id in (select COVERAGES_ID from OFS_DATA_DETAILS where quote_no='"+info[i][0]+"' and amend_id||PRODUCT_ID||SCHEME_ID||CONTENT_TYPE_ID in (select max(amend_id)||PRODUCT_ID||SCHEME_ID||CONTENT_TYPE_ID from OFS_DATA_DETAILS where quote_no= '"+info[i][0]+"' and PRODUCT_ID ='"+info[i][1]+"'  and SCHEME_ID ='"+info[i][2]+"' and COVERAGES_Y_N_OPTION ='Y' group by PRODUCT_ID,SCHEME_ID,CONTENT_TYPE_ID ) and COVERAGES_Y_N_OPTION ='Y' group by PRODUCT_ID,SCHEME_ID,CONTENT_TYPE_ID,COVERAGES_ID) and odd.status='Y' and odd.quote_no = '"+info[i][0]+"' and ofcm.PRODUCT_ID ='"+info[i][1]+"' and ofcm.SCHEME_ID ='"+info[i][2]+"' and odd.COVERAGES_Y_N_OPTION ='Y' and odd.content_type_id=ofcm.content_type_id and odd.content_type_id='"+info[i][3]+"' and ofcm.upload_option='Y') group by coverages_id) group by a.COVERAGES_ID,a.QUOTE_NO,a.CUSTOMER_FILE_NAME,a.SYSTEM_FILE_NAME,b.COVERAGES_DISPLAY_NAME order by a.SYSTEM_FILE_NAME ";
				
				args = new String[12];
				args[0] = "Y";
				args[1] = info[i][0];
				args[2] = "Y";
				args[3] = info[i][0];
				args[4] = info[i][0];
				args[5] = info[i][0];
				args[6] = info[i][1];
				args[7] = info[i][2];
				args[8] = info[i][0];
				args[9] = info[i][1];
				args[10] = info[i][2];
				args[11] = info[i][3];


				sql ="select a.QUOTE_NO,a.COVERAGES_ID,a.CUSTOMER_FILE_NAME,a.SYSTEM_FILE_NAME,b.COVERAGES_DISPLAY_NAME,MAX(a.AMEND_ID) from ofs_uploaded_data a,ofs_master b where a.status=? and a.quote_no=? and a.coverages_id=b.coverages_id and a.coverages_id in (select coverages_id from ofs_uploaded_data where status=? and quote_no=? and coverages_id in (select ofcm.COVERAGES_ID from ofs_master ofm,OFS_COVERAGES_MASTER ofcm,ofs_data_details odd where odd.coverages_id=ofcm.coverages_id and ofcm.coverages_id=ofm.coverages_id and ofcm.coverages_id in (select COVERAGES_ID from OFS_DATA_DETAILS where quote_no=? and amend_id||PRODUCT_ID||SCHEME_ID||CONTENT_TYPE_ID in (select max(amend_id)||PRODUCT_ID||SCHEME_ID||CONTENT_TYPE_ID from OFS_DATA_DETAILS where quote_no=? and PRODUCT_ID =? and SCHEME_ID =? and COVERAGES_Y_N_OPTION ='Y' group by PRODUCT_ID,SCHEME_ID,CONTENT_TYPE_ID) and COVERAGES_Y_N_OPTION ='Y' group by PRODUCT_ID,SCHEME_ID,CONTENT_TYPE_ID,COVERAGES_ID) and odd.status='Y' and odd.quote_no =? and ofcm.PRODUCT_ID =? and ofcm.SCHEME_ID =? and odd.COVERAGES_Y_N_OPTION ='Y' and odd.content_type_id=ofcm.content_type_id and odd.content_type_id=? and ofcm.upload_option='Y') group by coverages_id) group by a.COVERAGES_ID,a.QUOTE_NO,a.CUSTOMER_FILE_NAME,a.SYSTEM_FILE_NAME,b.COVERAGES_DISPLAY_NAME order by a.SYSTEM_FILE_NAME ";
				values = runner.multipleSelection(sql,args);
				if(values.length >0)
					downloadInfo.put("download"+(i+1),values);
			}
			if(info.length > 0)
				downloadInfo.put("quoteInfo",info);
		}
		catch(Exception e)
		{
			System.out.println("getCoverageFileDownload ...Office Shiled Bean1"+e.toString());
			e.printStackTrace();
		}
		System.out.println("getCoverageFileDownload....Length ...: "+values.length);
		return downloadInfo;
	}
	
	// Admin Side PortFolio_OfficeDeclaration.jsp
	public String[][] getPortfolioDeclaration(String policyNo,String pid,String branchCode,String brokerCodes) 
	{
	    String[][] result = new String[0][0];
    	String syntax="";
		String sql="";
		if(brokerCodes.equalsIgnoreCase("") || brokerCodes.length() ==0)
		{
			syntax = "select agency_code from broker_company_master where branch_code in("+branchCode+")";
		}
		else
		{
			brokerCodes = brokerCodes.replaceAll(",","','");
			syntax = "'"+brokerCodes+"'";
		}
		if(pid.equalsIgnoreCase("All"))
			pid ="30";
		try
		{
			sql = "select  bro.COMPANY_NAME,pol.policy_no,pi.title,(pi.first_name||pi.last_name||pi.company_name),pi.last_name,pol.OVERALL_PREMIUM,pol.login_id,pol.product_id,pol.QUOTE_NO,prd.product_name from HOME_POSITION_MASTER pol,personal_info pi,BROKER_COMPANY_MASTER bro,product_master prd where prd.product_id=pol.product_id and pol.policy_no= '"+policyNo+"' and pol.product_id='"+pid+"' and pol.customer_id=pi.customer_id and lower(pol.status) in('p') and bro.AGENCY_CODE in(select log.oa_code from LOGIN_MASTER log where log.login_id=pol.login_id) and pol.policy_no is not null and nvl(pol.declaration_status,0) in('LINKED') and pol.login_id in(select login_id from login_master where oa_code in("+syntax+")) and bro.branch_code = prd.branch_code and prd.branch_code='"+branchCode+"' order by pol.policy_no";
	
			System.out.println("getPortfolioDeclaration ..."+sql);
			result = runner.multipleSelection(sql);
        } 
		catch(Exception e) 
		{
            System.out.println("Exception in getPortfolio OfficeDeclaration OSB .. "+e.toString());
			e.printStackTrace();
        } 
        return result;	
	}

	public boolean chkPolicyDeclaration(String policyNo,String pid)
	{
		String sql ="";
		String [][]qnos = new String[0][0];
		boolean flag = false;
		try
		{
			sql ="select QUOTE_NO from home_position_master where policy_no in('"+policyNo+"') and product_id='"+pid+"' and  nvl(declaration_status,'0') in ('LINKED') and status='P' ";
			qnos = runner.multipleSelection(sql);
		}
		catch(Exception e)
		{
			System.out.println("chkPolicyDeclaration..."+e);
			e.printStackTrace();
		}
		System.out.println("chkPolicyDeclaration ..length .."+sql);
		System.out.println("chkPolicyDeclaration ..length .."+qnos.length);
		if(qnos.length > 0)
			flag = true;
		else
			flag = false;
		return flag;
	}

	public boolean getQuoteStatus(String[] qnos,String loginId,String productId,String schemeId)
	{
		String sql ="";
		String quoteNo = "";
		boolean flag = false;
		int rows =0;
		String res = "";
		
		for (int i=0;i<qnos.length;i++)
		{
			quoteNo = quoteNo + qnos[i] + "," ;
		}
		
		if(quoteNo.length() > 0)
			quoteNo = quoteNo.substring(0,quoteNo.length()-1);

		try
		{
			sql ="select count(*) from home_position_master where quote_no in("+quoteNo+") and product_id='"+productId+"' and status='P'";
			res = runner.singleSelection(sql);
			if(res.length() > 0 && !res.equalsIgnoreCase("null") && res != null )
			{
				rows	=	Integer.parseInt(res);
			}
			else
			{
				rows	=	0;
			}								
		}
		catch(Exception e)
		{
			System.out.println("Exception in getQuoteStatus() for Count :"+sql);
			System.out.println("Exception in getQuoteStatus() for Count :"+e.toString());
			e.printStackTrace();
		}
		
		System.out.println("getQuoteStatus ..length .."+qnos.length);
		
		if(rows <= 0)
			flag = false;
		else
			flag = true;

		return flag;
	}

	public String getPolicyNo(String[] qnos,String loginId,String productId,String schemeId)
	{
		String sql = "";
		String policyNo = "";
		String quoteNo = "";
		
		for (int i=0;i<qnos.length;i++)
		{
			quoteNo = quoteNo + qnos[i] + "," ;
		}
		
		if(quoteNo.length() > 0)
			quoteNo = quoteNo.substring(0,quoteNo.length()-1);
		
		try
		{
			sql ="select policy_no from home_position_master where quote_no in("+quoteNo+"') and product_id='"+productId+"' and status='P'";
			policyNo = runner.singleSelection(sql);
		}
		catch(Exception e)
		{
			System.out.println("getPolicyNo..."+e);
			e.printStackTrace();
		}
		return policyNo;
	}
	
	public int getProductDetails(String loginId)
	{
		String res = "";
		String sql = "";
		int count = 0;
		runner run = new runner();
		try
		{
			sql = "select count(*) from login_user_details where login_id='"+loginId+"' and status='Y'";
			res = run.singleSelection(sql);
			if(res.length() > 0)
			{
				count = Integer.parseInt(res);
			}
		}
		catch(Exception e)
		{
			System.out.println("getProductDetails ..."+e);
			System.out.println("getProductDetails ..."+sql);
			e.printStackTrace();
		}
		return count;
	}
	
	public void updateCancelAdminReferal(String quoteNo,String proId,String schemeId)
	{
		String sql = "";
		String args[] = new String[3];
		try
		{
			args[0] = quoteNo;
			args[1] = proId;
			args[2] = schemeId;
			sql = "update HOME_POSITION_MASTER set REMARKS=null,SUMMARY_REMARKS=null,ADMIN_SUMMARY_STATUS='N', REFERRAL_DESCRIPTION=null where quote_no=? and PRODUCT_ID=? and scheme_Id=?";
			runner.multipleUpdation(sql,args);
		}
		catch(Exception e)
		{
			System.out.println("..updateCancelAdminReferal..."+e);
			e.printStackTrace();
		}
	}

	public String getSchemeId(String quoteNo,String proId)
	{
		String sql = "";
		String scheme = "";
		String args[] = new String[2];
		try
		{
			args[0] = quoteNo;
			args[1] = proId;
			sql = "select scheme_id from home_position_master where quote_no=? and product_id=?";
			scheme = runner.singleSelection(sql,args);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return scheme;
	}
	
	public String getCustomerIdForAdminSide(String quoteNo,String proId)
	{
		String sql = "";
		String custId = "";
		String args[] = new String[3];
		try
		{
			args[0] = quoteNo;
			args[1] = proId;
			args[2] = "Y";
			sql = "select customer_id from home_position_master where quote_no=? and product_id=? and status=?";
			custId = runner.singleSelection(sql,args);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return custId;
	}

	public String getReasonForReferal(String quoteNo,String proId,String branch)
	{
		String query="",refid="",refval="";
		HashMap HM = new HashMap(); 
		String res[][] = new String[0][0];
		String referalReason = "";
		String referalDescription = "";
		String args[] = new String[0];
		try
		{
			args = new String[1];
			args[0] = branch;
			query = "select referal_id,referal_name from REFERAL_MASTER where branch_code=? order by referal_id";
			res = runner.multipleSelection(query,args);
			for(int i=0;i<res.length;i++)
			{
				refid = res[i][0];
				refval = res[i][1];
				HM.put(refid,refval); 
			}
		} 
		catch(Exception ex) 
		{
			System.out.println(" getReasonForReferal 1  "+ex);
			ex.printStackTrace();		
		}

		try
		{
			args = new String[3];
			args[0] = quoteNo;
			args[1] = proId;
			args[2] = "Y";
			query = "select referral_description from home_position_master where quote_no=? and product_id=? and status=?";
			referalDescription = runner.singleSelection(query,args);
		}
		catch(Exception e)
		{
			e.printStackTrace();
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
				if(HM.containsKey(x));
					referalReason += "*" + (HM.get(x) == null ? "" : HM.get(x))	+ "<br>";
			}
		}
		catch(Exception e)
		{
			System.out.println("Parsing Referal Description.."+e);
			e.printStackTrace();
		}
		if(referalReason.length() > 0 && !referalReason.equalsIgnoreCase("*<br>"))
		{
			System.out.println("Not Admin referal....");
		}
		else
			referalReason = "Admin Referral";	
		System.out.println("referalReason...."+referalReason);
		return referalReason;
	}
	
	public HashMap getOFSSchemeMaster(String branch,String proId)
	{
		String sql = "";
		String result[][] = new String[0][0];
		HashMap schemeHash = new HashMap();
		String args[] = new String[2];
		try
		{
			args[0] = proId;
			args[1] = branch;
			sql = "select s.scheme_id,(p.product_name||' - '||s.scheme_name) from ofs_scheme_master s,product_master p where p.product_id=s.product_id and s.branch_code=p.branch_code and p.product_id=? and p.branch_code=?";
			result = runner.multipleSelection(sql,args);
			for(int i=0;i<result.length;i++)
			{
				schemeHash.put(result[i][0],result[i][1]);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return schemeHash;
	}
	
	//Jan 06
	public String getContentValue()
	{
		String sql ="";
		String contentValue = "";
		String args[] = new String[3];
		try
		{
			args[0] = proId;
			args[1] = schemeId;
			args[2] = contentsOffice;
			sql = "select content_value from ofs_content_master where product_id=? and scheme_id=? and content_type_id=?";
			contentValue = runner.singleSelection(sql,args);
		}
		catch(Exception e)
		{
			System.out.println("getContentValue.."+e);
				e.printStackTrace();
		}
		return contentValue;
	}
	
	public HashMap getCoverageUploadOption(String quoteNo)
	{
		String sql = "";
		HashMap quickUpload = new HashMap();
		String[][] values = new String[0][0];
		String[][] info = new String[0][0];
		String[][] quick = new String[0][0];
		String qno = "";
		String args[] = new String[0];

		args = new String[1];

		if(quoteNo.indexOf("/") == -1)
			sql ="select quote_no,product_id,scheme_id,content_type_id from home_position_master where quote_no=?";
		else
			sql ="select quote_no,product_id,scheme_id,content_type_id from home_position_master where policy_no=?";

		try
		{
			args[0] = quoteNo;
			System.out.println("getCoverageUploadOption.."+sql);
			info = runner.multipleSelection(sql,args);
			System.out.println("Info...Length..."+info.length);
			args = new String[11];
			for(int r=0;r<info.length;r++)
			{
				args[0] = info[r][3];
				args[1] = info[r][1];
				args[2] = info[r][2];
				args[3] = info[r][0];
				args[4] = info[r][0];
				args[5] = info[r][1];
				args[6] = info[r][2];
				args[7] = info[r][0];
				args[8] = info[r][1];
				args[9] = info[r][2];
				args[10] = info[r][3];

				sql ="select ofm.coverages_display_name,ofcm.COVERAGES_ID,ofcm.UPLOAD_OPTION,odd.CONTENT_TYPE_ID,odd.quote_no,odd.SCHEME_ID,odd.product_id from ofs_master ofm,OFS_COVERAGES_MASTER ofcm,ofs_data_details odd where odd.coverages_id=ofcm.coverages_id and ofcm.coverages_id=ofm.coverages_id and ofcm.amend_id||ofcm.coverages_id in (select max(amend_id)||coverages_id from OFS_COVERAGES_MASTER where CONTENT_TYPE_ID=? and product_id=? and scheme_id=? group by coverages_id) and ofcm.coverages_id in (select COVERAGES_ID from OFS_DATA_DETAILS where quote_no=? and amend_id||PRODUCT_ID||SCHEME_ID||CONTENT_TYPE_ID in (select max(amend_id)||PRODUCT_ID||SCHEME_ID||CONTENT_TYPE_ID from OFS_DATA_DETAILS where quote_no=? and PRODUCT_ID =? and SCHEME_ID =? and COVERAGES_Y_N_OPTION ='Y' group by PRODUCT_ID,SCHEME_ID,CONTENT_TYPE_ID ) and COVERAGES_Y_N_OPTION ='Y' group by PRODUCT_ID,SCHEME_ID,CONTENT_TYPE_ID,COVERAGES_ID) and odd.status='Y' and odd.quote_no= ? and ofcm.PRODUCT_ID =? and ofcm.SCHEME_ID =? and odd.COVERAGES_Y_N_OPTION ='Y' and odd.content_type_id=ofcm.content_type_id and odd.content_type_id=? and ofcm.upload_option='Y' ";
				values = runner.multipleSelection(sql,args);
			
				for(int s=0;s<values.length;s++)
				{
					quick = new String[0][0];
					if(values[s][1].equalsIgnoreCase("2"))
					{
						quick = getPortableEquipmentDetails(info[r][0],values[s][1]);
					}
					if(values[s][1].equalsIgnoreCase("8"))
					{
						quick = getFidelityDetails(info[r][0],values[s][1]);
					}
					if(values[s][1].equalsIgnoreCase("9"))
					{
						quick = getTravelBaggageDetails(info[r][0],values[s][1]);
					}
					if(values[s][1].equalsIgnoreCase("10"))
					{
						quick = getGPADetails(info[r][0],values[s][1]);
					}
					
					if(quick.length > 0 )
						quickUpload.put("Q"+info[r][0]+"C"+values[s][1],quick);
				}
			} // FOR
		}
		catch(Exception e)
		{
			System.out.println("getCoverageUploadOption ...Office Shiled Bean1"+e.toString());
			e.printStackTrace();
		}
		return quickUpload;
	}
	
	public boolean checkingForQuickFileUpload(String quoteNo,String proId,String schemeId,String contentTypeId,String coverageId,String status)
	{
		boolean flag = false;
		String sql = "";
		int chk = 0;
		String qry= "";
		String args[] = new String[1];
		String values[][] = new String[0][0];
		try
		{
			args[0] = quoteNo;
			qry = "select product_id,scheme_id,content_type_id from home_position_master where quote_no=?";
			values = runner.multipleSelection(qry,args);
			if (values.length > 0) 
			{
				if("AddInfo".equalsIgnoreCase(status))
					chk = coverageFileDataChecking(quoteNo,coverageId,contentTypeId,values[0][1],proId,"AddInfo");
				else
					chk = coverageFileDataChecking(quoteNo,coverageId,contentTypeId,values[0][1],proId,"UploadData");
	
				if(chk > 0 )
					flag = true;
			}
		}
		catch(Exception e)
		{
			System.out.println("checkingForQuickFileUpload..."+e);
			e.printStackTrace();
		}
		return flag;
	}
	
	public String getNoOfEmpForGPA(String quoteNo,String proId,String schemeId,String coverId,String conTypId)
	{
		String sql ="";
		String counter = "";
		String args[] = new String[0];
		String qry = "";
		String values[][] = new String[0][0];
		try
		{
			args = new String[1];
			args[0] = quoteNo;
			qry = "select product_id,scheme_id,content_type_id from home_position_master where quote_no=?";
			values = runner.multipleSelection(qry,args);
			if(values.length > 0)
			{
				args = new String[10];
				args[0] = proId;
				args[1] = values[0][1];
				args[2] = conTypId;
				args[3] = coverId;
				args[4] = quoteNo;
				args[5] = proId;
				args[6] = values[0][1];
				args[7] = conTypId;
				args[8] = coverId;
				args[9] = quoteNo;

				sql ="select nvl(sum(COVERAGES_COVERED_EMPLOYEES),0) from OFS_DATA_SUB_DETAILS where amend_id=(select max(amend_id) from ofs_data_sub_details where product_id=? and scheme_id=? and content_type_id=? and coverages_id=? and coverages_sub_id in (27,28,29) and quote_no=?) and product_id=? and scheme_id=? and content_type_id=? and coverages_id=? and coverages_sub_id in (27,28,29) and quote_no=?";
				counter = runner.singleSelection(sql,args);
			}
		}
		catch(Exception e)
		{
			System.out.println("getNoOfEmpForGPA..."+e);
			e.printStackTrace();
		}
		System.out.println("getNoOfEmpForGPA ..length .."+counter);
		return counter;
	}
	
	//Jan 30 New

	public String getNoOfEmpForFG(String quoteNo, String proId,String schemeId, String coverId, String conTypId)
	{
		runner run = new runner();
		String sql = "";
		String counter = "";
		String qry = "";
		String values[][] = new String[0][0];
		String args[] = new String[0];
		try 
		{
			args = new String[1];
			args[0] = quoteNo;
			qry = "select product_id,scheme_id,content_type_id from home_position_master where quote_no=?";
			values = run.multipleSelection(qry,args);
			if(values.length > 0)
			{
				args = new String[10];
				args[0] = proId;
				args[1] = values[0][1];
				args[2] = conTypId;
				args[3] = coverId;
				args[4] = quoteNo;
				args[5] = proId;
				args[6] = values[0][1];
				args[7] = conTypId;
				args[8] = coverId;
				args[9] = quoteNo;

				sql = "select COVERAGES_COVERED_EMPLOYEES from OFS_DATA_SUB_DETAILS where amend_id=(select max(amend_id) from ofs_data_sub_details where product_id=? and scheme_id=? and content_type_id=? and coverages_id=? and coverages_sub_id='18' and quote_no=?) and product_id=? and scheme_id=? and content_type_id=? and coverages_id=? and coverages_sub_id='18' and quote_no=?";
				counter = run.singleSelection(sql,args);
			}
		} catch (Exception e) {
			System.out.println("getNoOfEmpForFG..." + e);
			e.printStackTrace();
		}
		System.out.println("getNoOfEmpForFG ..length .." + counter);
		return counter;
	}

	public String[][] getEmpDesignationForGPA(String branch)
	{
		runner run = new runner();
		String sql = "";
		String values[][] = new String[0][0];
		String args[] = new String[1];
		try 
		{
			args[0] = branch;
			sql = "select category_detail_id,detail_name from constant_detail where category_id='30' and branch_code=?";
			values = run.multipleSelection(sql,args);
		}catch (Exception e) {
			System.out.println("getEmpDesignationForGPA..." + e);
			e.printStackTrace();
		}
		System.out.println("getEmpDesignationForGPA ..length .." + values);
		return values;
	}

	public void getQuickUploadCoverageDelete(String uploadId, String coverId,String quoteNo) 
	{
		String sqlQuery_ = "";
		String amendId = "";
		String args[] = new String[1];
		try 
		{
			//
			args[0] = uploadId;
			if (coverId.equalsIgnoreCase("2"))
				sqlQuery_ = "select nvl(amend_id,'0') from OFS_PORTABLE_EQUIPMENT where PORTABLE_EQUIPMENT_ID =?";
			if (coverId.equalsIgnoreCase("8"))
				sqlQuery_ = "select nvl(amend_id,'0') from OFS_FIDELITY_GUARANTEE where FIDELITY_GUARANTEE_ID =?";
			if (coverId.equalsIgnoreCase("9"))
				sqlQuery_ = "select nvl(amend_id,'0') from OFS_TRAVEL_BAGGAGE where TRAVEL_BAGGAGE_ID = ?";
			if (coverId.equalsIgnoreCase("10"))
				sqlQuery_ = "select nvl(amend_id,'0') from OFS_PERSONAL_ACCIDENT where PERSONAL_ACCIDENT_ID = ?";

			amendId = runner.singleSelection(sqlQuery_,args);
			//

			if (coverId.equalsIgnoreCase("2"))
				sqlQuery_ = "delete from OFS_PORTABLE_EQUIPMENT where PORTABLE_EQUIPMENT_ID = ? ";
			if (coverId.equalsIgnoreCase("8"))
				sqlQuery_ = "delete from OFS_FIDELITY_GUARANTEE where FIDELITY_GUARANTEE_ID = ? ";
			if (coverId.equalsIgnoreCase("9"))
				sqlQuery_ = "delete from OFS_TRAVEL_BAGGAGE where TRAVEL_BAGGAGE_ID = ? ";
			if (coverId.equalsIgnoreCase("10"))
				sqlQuery_ = "delete from OFS_PERSONAL_ACCIDENT where PERSONAL_ACCIDENT_ID = ? ";

			runner.multipleUpdation(sqlQuery_,args);

			getQuickUploadCoverageDeleteFORALL(uploadId, coverId, quoteNo,amendId);
		} catch (Exception e) {
			System.out.println("Exception in getQuickUploadCoverageDelete : "+ e.toString());
			e.printStackTrace();
		}
	}

	public void getQuickUploadCoverageDeleteFORALL(String uploadId,	String coverId, String quoteNo, String amendId)
	{
		String sqlQuery_ = "";
		String counter = "";
		String args[] = new String[0];
		try 
		{
			args = new String[2];
			args[0] = quoteNo;
			args[1] = amendId;

			if (coverId.equalsIgnoreCase("2"))
				sqlQuery_ = "select count(*) from OFS_PORTABLE_EQUIPMENT where quote_no=? and amend_id=?";
			if (coverId.equalsIgnoreCase("8"))
				sqlQuery_ = "select count(*) from OFS_FIDELITY_GUARANTEE where quote_no=? and amend_id=?";
			if (coverId.equalsIgnoreCase("9"))
				sqlQuery_ = "select count(*) from OFS_TRAVEL_BAGGAGE where quote_no=? and amend_id=?";
			if (coverId.equalsIgnoreCase("10"))
				sqlQuery_ = "select count(*) from OFS_PERSONAL_ACCIDENT where quote_no=? and amend_id=?";

			counter = runner.singleSelection(sqlQuery_,args);
			
			args = new String[1];
			args[0] = quoteNo;
			if (counter.length() > 0 && counter.equalsIgnoreCase("0")) 
			{
				if (coverId.equalsIgnoreCase("2"))
					sqlQuery_ = "delete from OFS_PORTABLE_EQUIPMENT where quote_no = ? ";
				if (coverId.equalsIgnoreCase("8"))
					sqlQuery_ = "delete from OFS_FIDELITY_GUARANTEE where quote_no = ? ";
				if (coverId.equalsIgnoreCase("9"))
					sqlQuery_ = "delete from OFS_TRAVEL_BAGGAGE where quote_no = ? ";
				if (coverId.equalsIgnoreCase("10"))
					sqlQuery_ = "delete from OFS_PERSONAL_ACCIDENT where quote_no = ? ";
				runner.multipleUpdation(sqlQuery_,args);
			}
		} catch (Exception e) {
			System.out.println("Exception in getQuickUploadCoverageDelete : "+ e.toString());
			e.printStackTrace();
		}
	}

	public void updateTrackingDetails(String remarks)
	{
		/*String[] qryvalues = new String[6];
		String trackQry = "";
		try {
			System.out.println("Inside updateTrackingDetails TRACKING_MASTER");
			System.out.println("Application No :" + applicationNo);
			System.out.println("sessionId No :" + sessionId);
			System.out.println("loginCode No :" + loginId);

			trackQry = "update TRACKING_MASTER set STAGE_ID=?,END_TIME=SYSDATE,REMARKS=? where QUOTE_NO=? and STATUS=? and session_id=? and login_id=? ";

			qryvalues[0] = "2";
			qryvalues[1] = remarks;
			qryvalues[2] = quoteNo;
			qryvalues[3] = "Y";
			qryvalues[4] = sessionId;
			qryvalues[5] = loginId;
			runner.multipleUpdation(trackQry, qryvalues);
		} catch (Exception e) {
			System.out.println("Exception in updateTrackingDetails() OSB :"	+ e.toString());
			e.printStackTrace();
		}*/
		String temp = "sysdate";
		//temp = runner.getSysdate(branch);		
		String[] qryvalues = new String[5];
		String trackQry = "";
		try {
			System.out.println("Inside updateTrackingDetails TRACKING_MASTER");
			System.out.println("Application No :" + applicationNo);
			System.out.println("sessionId No :" + sessionId);
			System.out.println("loginCode No :" + loginId);

			trackQry = "update TRACKING_MASTER set STAGE_ID=?,END_TIME="+temp+",REMARKS=? where QUOTE_NO=? and STATUS=? and login_id=? ";

			qryvalues[0] = "2";
			qryvalues[1] = remarks;
			qryvalues[2] = quoteNo;
			qryvalues[3] = "Y";
			qryvalues[4] = loginId;
			runner.multipleUpdation(trackQry, qryvalues);
		} catch (Exception e) {
			System.out.println("Exception in updateTrackingDetails() OSB :"	+ e.toString());
			e.printStackTrace();
		}
	}

	public void insertTrackingDetails()
	{
		String temp = "sysdate";
		//temp = runner.getSysdate(branch);		
		String msno = "";
		String[] qryvalues = new String[9];
		String trackQry = "";
		try {
			msno = getMaximumSerialNO();
		} catch (Exception e) {
			System.out.println("Exception getting max serial no in insertTrackingDetails method "+ e.toString());
			e.printStackTrace();
		}
		try 
		{
			System.out.println("Inside Insertion of TRACKING_MASTER");
			trackQry = "insert into TRACKING_MASTER (SERIAL_NO,SESSION_ID,LOGIN_ID,STAGE_ID,APPLICATION_NO,QUOTE_NO,PRODUCT_ID,REMARKS,STATUS,START_TIME) values(?,?,?,?,?,?,?,?,?,"+temp+")";
			qryvalues[0] = "" + msno;
			qryvalues[1] = sessionId;
			qryvalues[2] = loginId;
			qryvalues[3] = "2";
			qryvalues[4] = "" + Integer.parseInt(applicationNo);
			qryvalues[5] = "" + Integer.parseInt(quoteNo);
			qryvalues[6] = proId;
			qryvalues[7] = "Remarks";
			qryvalues[8] = "Y";
			runner.multipleInsertion(trackQry, qryvalues);
		} catch (Exception e) {
			System.out.println("Exception in insertTrackingDetails()  Insertion :"	+ e.toString());
			e.printStackTrace();
		}
	}

	public String getMaximumSerialNO()
	{
		runner run = new runner();
		String sql = "";
		String sno = "";
		try {
			sql = "select max(serial_no)+1 from tracking_master";
			sno = run.singleSelection(sql);
		} catch (Exception e) {
			System.out.println("getMaximumSerialNO tracking_master OSB"	+ e.toString());
			e.printStackTrace();
		}
		sno = sno == null ? "0" : sno;
		return sno;
	}

	public boolean checkZero(String value) 
	{
		boolean flag = true;
		try {
			if (Integer.parseInt(value) == 0)
				flag = false;
		} catch (Exception e) {
			System.out.println("Valid Amount in OfficeShiledBean.java1 "+ value);
			return flag;
		}
		System.out.println("Valid Amount in OSB  " + flag);
		return flag;
	}

	public String getUploadErrorMsg(String errorNo)
	{
		runner run = new runner();
		String err = "";
		try {
			err = run.getErrormsg(errorNo);
		} catch (Exception e) {
			System.out.println("getMaximumSerialNO tracking_master OSB"	+ e.toString());
			e.printStackTrace();
		}
		return err;
	}

	public String getQuoteLogin(String quoteNo) 
	{
		runner run = new runner();
		String loginId = "";
		String sql = "";
		String args[] = new String[1];
		try {
			args[0] = quoteNo;
			sql = "select login_id from home_position_master where quote_no=?";
			loginId = run.singleSelection(sql,args);
		}catch (Exception e) {
			System.out.println("getQuoteLogin OSB" + e.toString());
			e.printStackTrace();
		}
		return loginId;
	}
	
	public void updateReferralStatus(String quoteNo,String proId,String schemeId,String Remarks) 
	{
		String qry = "";
		String args[] = new String[3];

		if ("Y".equalsIgnoreCase(Remarks))
			qry = "update HOME_POSITION_MASTER set REMARKS='Admin',ADMIN_REFERRAL_STATUS='N' ,status='Y' where quote_no=? and product_id=? and scheme_id=?";
		else if ("N".equalsIgnoreCase(Remarks))
			qry = "update HOME_POSITION_MASTER set REMARKS='Referal',ADMIN_REFERRAL_STATUS='N' ,status='R' where quote_no=? and product_id=? and scheme_id=?";
		else if ("A".equalsIgnoreCase(Remarks))
			qry = "update HOME_POSITION_MASTER set REMARKS='Referal',ADMIN_REFERRAL_STATUS='N' ,status='Y' where quote_no=? and product_id=? and scheme_id=?";
		try
		{
			args[0] = quoteNo;
			args[1] = proId;
			args[2] = schemeId;
			System.out.println("updateReferralStatus OSB ...." + qry);
			runner.multipleUpdation(qry,args);
		} 
		catch (Exception ex) {
			System.out.println("updateReferralStatus...OSB....."+ex);
			ex.printStackTrace();
		}
	}
	
	public String InsuranceStartDateValidation(String quoteNos,String proId,String schemeId) // Show Quotes Multiples Page
	{
		int rows = 0;
		String sql = "";
		String msg = "";
		String result[][] = new String[0][0];
		String temp = "sysdate";
		//temp = runner.getSysdate(branch);		
		try
		{
			sql = " select quote_no from home_position_MASTER where to_char(effective_date,'dd-mm-yyyy')<(select to_char("+temp+",'dd-mm-yyyy') from dual) and quote_no in("+quoteNos+")";
			result = runner.multipleSelection(sql);
			for(int i=0;i<result.length;i++)
				msg = msg+"<br> <font color=red >* Insurance Start Date is Invalid "+result[i][0]+"</font>";
		}
		catch(Exception e)
		{
			System.out.println("customerIdChkingForDeclaration...1.."+sql);
			System.out.println("customerIdChkingForDeclaration..2..."+e);
			e.printStackTrace();
		}
		return msg;
	}
	
	public void updateInsSDateEDate(String quoteNo,String proId,String schemeId,String sdate)
	{
	   String args[] = new String[3];
	   String Sql = "";
		try
		{	
			args[0] = quoteNo;
			args[1] = proId;
			args[2] = schemeId;
			System.out.println("sdate "+sdate);
			Sql = "update HOME_POSITION_MASTER set EFFECTIVE_DATE=to_date('"+sdate+"','DD-MM-YYYY'),EXPIRY_DATE=(add_months(to_date('"+sdate+"','DD-MM-YYYY'),12)-1) where quote_no=? and product_id=? and scheme_id=?";
			runner.multipleUpdation(Sql,args);
		}
		catch(Exception e)
		{
			System.out.println("ERROR in get MaxDebitNo in getMax DebitNo  "+e.toString());
			e.printStackTrace();
		}
	}
	
	// Admin Customer Creation 
	
	public String[][] getExistingCustomers(String brokerLogin)
	{
		String args[] = new String[1];
		String qry = ""; 
		String[][] returnVal = new String[0][0];
		try
		{
			args[0] = brokerLogin;
			qry = "select nvl(first_name,company_name),nvl(last_name,'0'), nvl(email,'0'), nvl(mobile,TELEPHONE), customer_id,nvl(missippi_customer_code,0) from personal_info where login_id in(select l.login_id from broker_company_master bk,login_master l where l.oa_code=bk.agency_code and l.oa_code in(select oa_code from login_master where login_id = ?)) and application_id='1'";
			returnVal = runner.multipleSelection(qry,args);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return returnVal;
	}
	
	public String[][] getCountryNameWithID(String adminCid)
	{
		String country[][] = new String[0][0];
		String sql = "";
		String args[] = new String[1];
		try
		{
			args[0] = adminCid;
			sql = "select country_name,country_id from country_master where country_id=?";
			country = runner.multipleSelection(sql,args);
		}
		catch(Exception e)
		{
			System.out.println("get Country name With Country Id  " + e);
			e.printStackTrace();
		}
		return  country;
	}
	
	public String[][] getAdminCustomerForOS(String custId)
	{
		String sql="";
		String[][] values=new String[0][0];
		String args[] = new String[1];
		try
		{
			args[0] = custId;
			sql="select title,gender,nvl(first_name,last_name||company_name),nationality,to_char(dob,'DD') as dobday,to_char(dob,'MM') as dobmonth,to_char(dob,'YYYY') as dobyear,nvl(telephone,mobile),fax,email,address1,occupation,emirate,country,pobox,company_name,address2,customer_source from personal_info where customer_id=?";
			values = runner.multipleSelection(sql,args);
		}
		catch(Exception e)
		{
			System.out.println("getCustomerForOS ...DataSelect"+e.toString());
			e.printStackTrace();
		}
		return values;
	}
//Added by chinna
	public String[][] getCoverDetails(String coverid, String subcoverid, String proId, String schemeId, String contId)
	{
		String q="select DISPLAY_NAME, DISPLAY_TYPE, DISPLAY_ORDER, DEST_TABLE, DEST_COLUMN, DROPDOWN_KEY, DROPDOWN_VALUE, DROPDOWN_TABLE, VALIDATION_TYPE, Mandatory   from ofs_config_master where coverages_id=? and coverages_sub_id=? and product_id=? and scheme_id=? and content_type_id=? and STATUS='Y' and coverages_id||amend_id=(select coverages_id||max(amend_id) from ofs_config_master where coverages_id=? and coverages_sub_id=? and product_id=? and scheme_id=? and content_type_id=? group by coverages_id) order by display_order,ofs_config_id";
		String args[]=new String[10];
		args[0]=coverid;
		args[1]=subcoverid;
		args[2]=proId;
		args[3]=schemeId;
		args[4]=contId;
		args[5]=coverid;
		args[6]=subcoverid;
		args[7]=proId;
		args[8]=schemeId;
		args[9]=contId;
		String [][] coverDetails=runner.multipleSelection(q,args);
		return coverDetails;
	}
	
	public void insertCoverageDetails(String quoteNo, String coverid, String subcoverid, String proId, String schemeId, String contId, String destTable, HashMap fields)
	{
		System.out.println("insertCoverageDetails - Enter");
		java.util.Set hotkeys=fields.keySet();
		java.util.Iterator itr=hotkeys.iterator();
		StringBuffer fieldNames=new StringBuffer();
		StringBuffer fieldValues=new StringBuffer();
		String moreId="";
		String field="";
		String args[]=new String[6];
		String q="select (case when count(*)=0 then 0 else max(MORE_DETAILS_ID) end)+1 from "+destTable+" where quote_no=? and product_id=? and scheme_id=? and content_type_id=? and coverages_id=? and coverages_sub_id=?";
		args[0]=quoteNo;
		args[1]=proId;
		args[2]=schemeId;
		args[3]=contId;
		args[4]=coverid;
		args[5]=subcoverid;
		moreId=runner.singleSelection(q,args);
		System.out.println("Query is: "+q);
		
		while(itr.hasNext())
		{
			field=(String)itr.next();
			fieldNames.append(","+field);
			fieldValues.append(",'"+fields.get(field)+"'");
		}
		q="INSERT INTO "+destTable+" (QUOTE_NO,PRODUCT_ID, SCHEME_ID, CONTENT_TYPE_ID, COVERAGES_ID, COVERAGES_SUB_ID,MORE_DETAILS_ID"+fieldNames+", AMEND_ID, STATUS) VALUES (?,?, ?, ?, ?, ?,?"+fieldValues+", '0','Y')";
		args=new String[7];
		args[0]=quoteNo;
		args[1]=proId;
		args[2]=schemeId;
		args[3]=contId;
		args[4]=coverid;
		args[5]=subcoverid;
		args[6]=moreId;
		String result=runner.multipleInsertion(q, args);
		System.out.println("Query: "+q);
		System.out.println(result);
		System.out.println("insertCoverageDetails - Exit");
	}
	
	public void deleteCoverageDetails(String quoteNo, String coverid, String subcoverid, String proId, String schemeId, String contId, String destTable)
	{
		System.out.println("deleteCoverageDetails - Enter");
		String moreId="";
		String args[]=new String[6];
		String q="select count(*) from "+destTable+" where quote_no=? and product_id=? and scheme_id=? and content_type_id=? and coverages_id=? and coverages_sub_id=?";
		args[0]=quoteNo;
		args[1]=proId;
		args[2]=schemeId;
		args[3]=contId;
		args[4]=coverid;
		args[5]=subcoverid;
		moreId=runner.singleSelection(q,args);
		if(!moreId.equals("0"))
		{
			q="delete from "+destTable+" where quote_no="+quoteNo+" and product_id="+proId+" and scheme_id="+schemeId+" and content_type_id="+contId+" and coverages_id="+coverid+" and coverages_sub_id="+subcoverid;
			runner.deletion(q);
		}
		System.out.println("Query is: "+q);
		System.out.println("deleteCoverageDetails - Exit");
	}
	public String getCoverValue(String quoteNo, String coverid, String subcoverid, String proId, String schemeId, String contId, String field, String destTable, int moreId)
	{
		String q="select "+field+" from "+destTable+" where quote_no=? and coverages_id=? and coverages_sub_id=? and product_id=? and scheme_id=? and content_type_id=? and MORE_DETAILS_ID="+moreId;
		String args[]=new String[6];
		args[0]=quoteNo;
		args[1]=coverid;
		args[2]=subcoverid;
		args[3]=proId;
		args[4]=schemeId;
		args[5]=contId;
		String coverDetails=runner.singleSelection(q, args);
		return coverDetails;
	}
	
	public String getMoreId(String quoteNo, String coverid, String subcoverid, String proId, String schemeId, String contId, String destTable)
	{
		String q="select max(MORE_DETAILS_ID) from "+destTable+" where quote_no=? and coverages_id=? and coverages_sub_id=? and product_id=? and scheme_id=? and content_type_id=? ";
		String args[]=new String[6];
		args[0]=quoteNo;
		args[1]=coverid;
		args[2]=subcoverid;
		args[3]=proId;
		args[4]=schemeId;
		args[5]=contId;
		String moreId = runner.singleSelection(q, args);
		return moreId;
	}
	public String[][] getDropdownValues(String tableName, String value, String key)
	{
		String q="select "+value+","+key+" from "+tableName;
		String values[][] = runner.multipleSelection(q);
		return values;
	}
	public String uploadSummarySubmit2(String quoteNo,String loginId, String proId, String shemeID)
	{
		runner run = new runner();
		String error = "";
		String info[][] = new String[0][0];
		String subcoverid="0";
		String contId="0";
		String q="";
		
		
		boolean flag = true;
		try
		{
			//upload Option is not checked in the commented query
			//q="select ofm.coverages_display_name,odd.COVERAGES_id from ofs_master ofm,ofs_data_details odd where odd.coverages_id=ofm.coverages_id and ofm.coverages_id in (select COVERAGES_ID from OFS_DATA_DETAILS where quote_no=? and amend_id||PRODUCT_ID||SCHEME_ID||CONTENT_TYPE_ID in (select max(amend_id)||PRODUCT_ID||SCHEME_ID||CONTENT_TYPE_ID from OFS_DATA_DETAILS where quote_no= ? and PRODUCT_ID =? and SCHEME_ID =? and COVERAGES_Y_N_OPTION ='Y' group by PRODUCT_ID,SCHEME_ID,CONTENT_TYPE_ID ) and COVERAGES_Y_N_OPTION ='Y' group by PRODUCT_ID,SCHEME_ID,CONTENT_TYPE_ID,COVERAGES_ID ) and odd.status='Y' and odd.quote_no= ? and odd.PRODUCT_ID =? and odd.SCHEME_ID =? and odd.COVERAGES_Y_N_OPTION ='Y' and odd.content_type_id=? order by odd.COVERAGES_id";
			q="select ofm.coverages_display_name,odd.COVERAGES_id from ofs_master ofm,ofs_data_details odd,OFS_COVERAGES_MASTER ofcm where ofcm.coverages_id=odd.coverages_id and ofcm.upload_option='Y' and ofcm.amend_id||ofcm.coverages_id in (select max(amend_id)||coverages_id from ofs_coverages_master group by coverages_id) and odd.coverages_id=ofm.coverages_id and ofm.coverages_id in (select COVERAGES_ID from OFS_DATA_DETAILS where quote_no=? and amend_id||PRODUCT_ID||SCHEME_ID||CONTENT_TYPE_ID in (select max(amend_id)||PRODUCT_ID||SCHEME_ID||CONTENT_TYPE_ID from OFS_DATA_DETAILS where quote_no= ? and PRODUCT_ID =? and SCHEME_ID =? and COVERAGES_Y_N_OPTION ='Y' group by PRODUCT_ID,SCHEME_ID,CONTENT_TYPE_ID ) and COVERAGES_Y_N_OPTION ='Y' group by PRODUCT_ID,SCHEME_ID,CONTENT_TYPE_ID,COVERAGES_ID ) and odd.status='Y' and odd.quote_no= ? and odd.PRODUCT_ID =? and odd.SCHEME_ID =? and odd.COVERAGES_Y_N_OPTION ='Y' and odd.content_type_id=? order by odd.COVERAGES_id";
			String args[]=new String[8];
			args[0]=quoteNo;
			args[1]=quoteNo;
			args[2]=proId;
			args[3]=shemeID;
			args[4]=quoteNo;
			args[5]=proId;
			args[6]=shemeID;
			args[7]=contId;
			info = runner.multipleSelection(q, args);
			System.out.print("Info======>>>"+info.length);	
			if(info!=null && info.length>0)
			{
				for(int i=0;i<info.length;i++)
				{
					q="select count(*) from OFS_TRANSACTION_DETAILS where quote_no=? and coverages_id=? and coverages_sub_id=? and product_id=? and scheme_id=? and content_type_id=? ";
					args=new String[6];
					args[0]=quoteNo;
					args[1]=info[i][1];
					args[2]=subcoverid;
					args[3]=proId;
					args[4]=shemeID;
					args[5]=contId;
					String count = runner.singleSelection(q, args);
					count=count==null?"0":count;
					String coverDetails[][]=getCoverDetails(info[i][1], subcoverid, proId, shemeID, contId);
					if(coverDetails.length>0)
					{
						error+=Integer.parseInt(count)==0?"<br>* Please Enter Coverage Details for "+info[i][0]:"";
					}
				}
			}
		}
		catch(Exception e)
		{
			System.out.println("uploadSummarySubmit2 ..."+e);
			e.printStackTrace();
		}
		return error;
	}
	
	public String getClientTime(String branch, String format)
	{
		System.out.println("getClientTime - Enter");
		String result="";
		String q="";
		String timeZone="sysdate";//runner.getSysdate(branch);
		if(timeZone!=null && timeZone.length()>0)
		{
			if(format!=null && format.equalsIgnoreCase("time"))
			q="select to_char("+timeZone+", 'HH12:MI:SS AM') from dual";
			else if(format!=null && format.equalsIgnoreCase("dateTime"))
			q="select to_char("+timeZone+", 'DD/MM/YYYY HH12:MI:SS AM') from dual";
			else 
			q="select to_char("+timeZone+", 'DD/MM/YYYY') from dual";
			result=runner.singleSelection(q);
		}
		System.out.println("getClientTime - Exit - Result: "+result);
		return result;
	}
} //Class


