package com.maan.Travel.DAO;

import java.io.Serializable;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.maan.Home.DataManipualtion.HomeValidationBean;
import com.maan.common.CommonHelp;
import com.maan.common.LogManager;
import com.maan.common.exception.BaseException;
import com.maan.product.ProductSelection;
import com.maan.services.util.runner;
import com.maan.services.util.TestRunner;

public class TravelBean implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6152465883290335716L;
	final static transient private String QUERY = "Query";
	final static transient private String ARGS = "Args";
	final static transient private String ENTER = "- Enter";
	final static transient private String EXIT = "- Exit";
	final static transient private String NULL = "null";
	final static transient private String SELECT = "select";
	final static transient private String INVALID = "Invalid";
	final static transient private String YES = "YES";
	final static transient private String MOTOR = "motor";
	final static transient private String MEMBERNUMBER = "memberNo" ;
	final static transient private String POLSTATUS =  "PolStatus";
	final static transient private String  ADMINCLAUSE = "Adminclauses";
	final static transient private String ZERO =  "0.0";
	final static transient private String CLAIMAMOUNT =  "claimamount";
	final static transient private String CLAIMDETAILS = "claimdetails";
	transient private  boolean isReferralFlag = true;
	transient private final String[] months={"0","Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
	transient private final HomeValidationBean homeValid = new HomeValidationBean();
	public String prId;
	public long noDays;
	public HttpSession sess;
	
	
	public long getNoDays() {
		return noDays;
	}

	public void setNoDays(long noDays) {
		this.noDays = noDays;
	}

	public String getPrId() {
		return prId;
	}

	public void setPrId(String prId) {
		this.prId = prId;
	}

	public String getMaxQuoteId(final String productTypeId,final String loginBra)throws BaseException
	{
		LogManager.push("TravelBean getMaxQuoteId method()");
		LogManager.debug(ENTER);

		String sql;
		sql = "select nvl(max(current_no)+1,max(start_no)) from policyno_generate where type_id=(select QUOTE_TYPE_ID from BRANCH_DETAIL where BRANCH_CODE = ? and PRODUCT_ID = ?) and status='Y' and " +
		"amend_id=(select max(amend_id) from policyno_generate where type_id=(select QUOTE_TYPE_ID from BRANCH_DETAIL where BRANCH_CODE = ? and PRODUCT_ID = ?) and branch_code=? and product_id=?) and branch_code=?";
		String[] args;args = new String[7];
		args[0] = loginBra;
		args[1] = productTypeId;
		args[2] = loginBra;
		args[3] = productTypeId;
		args[4] = loginBra;
		args[5] = productTypeId;
		args[6] = loginBra;
		String current_no;
		current_no = runner.singleSelection(sql, args);
		args = new String[9];
		sql = "update policyno_generate set current_no = ? , remarks  = ? where type_id = (select QUOTE_TYPE_ID from BRANCH_DETAIL where BRANCH_CODE = ?  and PRODUCT_ID = ? ) and status='Y' and amend_id=(" +
				"select max(amend_id) from policyno_generate where type_id=(select QUOTE_TYPE_ID from BRANCH_DETAIL where BRANCH_CODE = ? and PRODUCT_ID = ? ) and branch_code=? and product_id=?) and branch_code=?";
		args[0] = current_no;
		args[1] = current_no;
		args[2] = loginBra;
		args[3] = productTypeId;
		args[4] = loginBra;
		args[5] = productTypeId;
		args[6] = loginBra;
		args[7] = productTypeId;
		args[8] = loginBra;
		runner.multipleUpdation(sql, args);

		LogManager.debug(EXIT);
		LogManager.popRemove();

		return current_no;
	}

	public double getMinimumPremium(String loginId,String productId)
	{
		LogManager.push("TravelBean getMinimumPremium method(): "+loginId);
		LogManager.debug(ENTER);
		final double result;
		
		final String query="select MIN_PREMIUM_AMOUNT from LOGIN_USER_DETAILS where upper(LOGIN_ID)=upper(?) and PRODUCT_ID=?";
		String args[]=new String[2];
		args[0]=loginId;
		args[1]=productId;
		result=Double.parseDouble(runner.singleSelection(query,args));
		
		LogManager.debug(EXIT);
		LogManager.popRemove();
		
		return result;
	}
	
	public String insertForTravellers(final HashMap hasM, final String pid, String travelPid,final String loginBra)throws BaseException
	{
		LogManager.push("TravelBean insertForTravellers method()");		
		LogManager.debug(ENTER);
		
		LogManager.info("Testing the Quote Series "+travelPid);
		LogManager.info("Testing the new AOIC Travel "+hasM);
		final Map sqlMap = new HashMap();
		int mapCnt = 0;
		String cur_num = "";
		int current_num = 0;
		String queryheader = "";
		String querydetails = "";
		String[] names = {};
		String[] dbday = {};
		String[] dbmonth = {};
		String[] dbyear = {};
		String[] relation = {};
		String[] nationality = {};
		String[] genders = {};
		int num_adults = 0;
		int num_childs = 0;
		int small_kids = 0;
		int totalpeople = 0;
		String usaext = "";
		String winext = "";
		String golfext = "";
		String worldWide = "";
		String covperiod = "";
		String effday = "";
		String effmonth = "";
		String effyear = "";
		
		String endday = "";
		String endmonth = "";
		String endyear = "";
		String countryOrigin = "";
		String countryDestn = "";
		
		String claim = "";
		String claimdetails = "";
		String suminsured = "";
		String expdate = "";
		String schemeName = "";
		String additionalcover = "";
		String startdate = "";
		String user = "";
		String customerid = "";
		String mon = "";
		long sum = 0;
		String temp1 = "";
		String temp2 = "";
		String temp3 = "";
		String oldage = "";
		String treatment = "";
		String familyDiscount = "";
		String spouseDiscount = "";
		String terrorism_ext = "";
		String memoranda = "";
		boolean above75Ref = false;
		//String ratingFactorNames[][] = new String[0][0];
		String[] args = new String[0];
		//ratingFactorNames = getRatingFactorNames(pid);
		cur_num = getMaxQuoteId(travelPid, loginBra);
		current_num = Integer.parseInt(cur_num);
		String dob = "";
		names = (String[]) hasM.get("names");
		dbday = (String[]) hasM.get("dbday");
		dbmonth = (String[]) hasM.get("dbmonth");
		dbyear = (String[]) hasM.get("dbyear");
		relation = (String[]) hasM.get("relations");
		nationality = (String[]) hasM.get("nationality");
		genders = (String[]) hasM.get("genders");
		temp1 = (String) hasM.get("adults");
		temp2 = (String) hasM.get("kids");
		temp3 = (String) hasM.get("smallKids");
		customerid = (String) hasM.get("customerid");
		customerid = CommonHelp.notNullCheck(customerid);
		int[] age = (int[]) hasM.get("age");
		temp1 = CommonHelp.notNullCheckZero(temp1);
		temp2 = CommonHelp.notNullCheckZero(temp2);
		temp3 = CommonHelp.notNullCheckZero(temp3);
		num_adults = Integer.parseInt(temp1);
		num_childs = Integer.parseInt(temp2);
		small_kids = Integer.parseInt(temp3);
		
		LogManager.info ("hasM   hasM  "+hasM);
		String ratingSubName = getRatingFactorSubName("11",pid);
		schemeName = (String)hasM.get(ratingSubName);
		
		System.out.println("Test 1");
		
		for (int i = 0; i < names.length; i++)
			{
				mon = months[Integer.parseInt(dbmonth[i])] ;

				if ("0".equals(dbday[i]) || "0".equals(dbyear[i])){
					dob = "";
				}
				else {
					dob = dbday[i] + "-" + mon + "-" + dbyear[i];
				}
				names[i] = names[i].replaceAll("'", "''");
				if(age == null){
					args = new String[9];
					querydetails = "insert into TRAVEL_DETAIL(QUOTE_NO,SERIAL_NO,PASSENGER_NAME,DOB,"
							+ "AMEND_ID,STATUS,RELATION,GENDER,NATIONALITY) values(?,?,?,?,?,?,?,?,?)";
					args[0] = Integer.toString(current_num);
					args[1] = Integer.toString(i + 1);
					args[2] = names[i];
					args[3] = dob;
					args[4] = "0";
					args[5] = "Y";
					args[6] = relation[i];
					args[7] = genders[i];
					args[8] = nationality[i];
					sqlMap.put(QUERY+mapCnt,querydetails);
					sqlMap.put(ARGS+mapCnt++,args );
				}
				else{
					if (age[i] > 75) {
						above75Ref = true;
					}
					args = new String[10];

					querydetails = "insert into TRAVEL_DETAIL(QUOTE_NO,SERIAL_NO,PASSENGER_NAME,DOB,"
							+ "AMEND_ID,STATUS,RELATION,GENDER,NATIONALITY,age) values( ?,?,?,?,?,?,?,?,?,?)";

					args[0] = Integer.toString(current_num);
					args[1] = Integer.toString(i + 1);
					args[2] = names[i];
					args[3] = dob;
					args[4] = "0";
					args[5] = "Y";
					args[6] = relation[i];
					args[7] = genders[i];
					args[8] = nationality[i];
					args[9] = Integer.toString(age[i]);
					sqlMap.put(QUERY+mapCnt,querydetails);
					sqlMap.put(ARGS+mapCnt++,args );
				}
				//runner.multipleInsertion(querydetails, args); //May 17
			}
			totalpeople = num_adults + num_childs + small_kids;

			System.out.println("Test 2");
			
			String tot = (String) hasM.get("saveoption");

			if (tot == null || tot.equals(NULL) || tot.length() <= 0) {
				tot = "0";
			} else {
				totalpeople = Integer.parseInt(tot);
			}

			usaext = (String) hasM.get("UsaExt");
			worldWide = (String) hasM.get("WorldWide");
			String baggageExclusion = "";
			String medicalExclusion = "";

			/*if (ratingFactorNames.length > 0) {
				medicalExclusion = (String) hasM.get(ratingFactorNames[0][0]);
				baggageExclusion = (String) hasM.get(ratingFactorNames[1][0]);
				winext = (String) hasM.get(ratingFactorNames[2][0]);
				golfext = (String) hasM.get(ratingFactorNames[3][0]);
				terrorism_ext = (String) hasM.get(ratingFactorNames[4][0]);
			}*/
			
			
			medicalExclusion = (String) hasM.get("medical_excluded");
			baggageExclusion = (String) hasM.get("personal_baggage");
			winext = (String) hasM.get("winter_sports");
			golfext = (String) hasM.get("Golf_Extension");
			terrorism_ext = "" ;
			
			if(!"32".equalsIgnoreCase(pid))
			covperiod = (String) hasM.get("coverPeriod");
			else
			covperiod = String.valueOf(hasM.get("noOfDaysCount"));
			
			System.out.println("Test 3");
			
			effday = (String) hasM.get("EffDay");
			effmonth = (String) hasM.get("EffMonth");
			effyear = (String) hasM.get("EffYear");
			
			endday = (String) hasM.get("EndDay");
			endmonth = (String) hasM.get("EndMonth");
			endyear = (String) hasM.get("EndYear");
			countryOrigin=hasM.get("countryOrigin")==null?"":(String)hasM.get("countryOrigin");
			countryDestn=hasM.get("countryDestn")==null?"":(String)hasM.get("countryDestn");
			
			claim = (String) hasM.get("claim");
			claimdetails = (String) hasM.get("claimDetails");
			suminsured = (String) hasM.get("suminsured");
			expdate = (String) hasM.get("ExpDate");
			user = (String) hasM.get("user");
			oldage = (String) hasM.get("oldage");
			treatment = (String) hasM.get("treatment");
			familyDiscount = (String) hasM.get("familyDiscount");
			spouseDiscount = (String) hasM.get("spouseDiscount");
			memoranda = (String) hasM.get("memoranda");

			if (familyDiscount == null || familyDiscount.equalsIgnoreCase(NULL)	|| "".equals(familyDiscount)) {
				familyDiscount = "N";
			}
			if (spouseDiscount == null || spouseDiscount.equalsIgnoreCase(NULL)	|| "".equals(spouseDiscount)) {
				spouseDiscount = "N";
			}

			if(memoranda == null || memoranda.equalsIgnoreCase(NULL)){
				memoranda =  "";
			}

			if (suminsured != null && suminsured.length() > 0) {
				sum = Long.parseLong(suminsured);
			}
			if(usaext == null || usaext.equalsIgnoreCase(NULL) || usaext.equalsIgnoreCase(SELECT)){
				usaext =  "";
			}
			if(winext == null || winext.equalsIgnoreCase(NULL) || winext.equalsIgnoreCase(SELECT)){
				winext =  "";
			}
			if(golfext == null || golfext.equalsIgnoreCase(NULL) || golfext.equalsIgnoreCase(SELECT)) {
				golfext = "" ;
			}
			if(baggageExclusion == null || baggageExclusion.equalsIgnoreCase(NULL) || baggageExclusion.equalsIgnoreCase(SELECT)) {
				baggageExclusion =  "" ;
			}
			if(medicalExclusion == null || medicalExclusion.equalsIgnoreCase(NULL) || medicalExclusion.equalsIgnoreCase(SELECT)) {
				medicalExclusion = "";
			}
			if(terrorism_ext == null || terrorism_ext.equalsIgnoreCase(NULL) || terrorism_ext.equalsIgnoreCase(SELECT)) {
				terrorism_ext =  "";
			}

			if ("Y".equals(usaext) || "Y".equals(winext) || "Y".equals(golfext)){
				additionalcover = "Y";
			}

			if ("Y".equalsIgnoreCase(usaext)){
				worldWide = "N";
			}

			if ("Y".equalsIgnoreCase(worldWide)){
				usaext = "N";
			}

			if (effmonth != null)
			{
				mon = months[Integer.parseInt(effmonth)] ;
				startdate = effday + "-" + mon + "-" + effyear;
			}
			if(!"32".equalsIgnoreCase(pid))
			{
				expdate = getEndDate(effyear+effmonth+effday,covperiod);
			}
			else
			{
				mon = months[Integer.parseInt(endmonth)] ;
				expdate = endday + "-" + mon + "-" + endyear;
			}
			
			args = new String[30];
			com.maan.Home.DataManipualtion.DataSelect data = new com.maan.Home.DataManipualtion.DataSelect();
			String hour = "";
			hour = data.getSysdateTime(loginBra,"Branch");
			queryheader = "insert into TRAVEL_HEADER(QUOTE_NO,TOTAL_ADULT_PASSENGERS,TOTAL_CHILD_PASSENGERS,"
					+ "TOTAL_NO_OF_PEOPLE_COVERED,CHILD_BETWEEN_3_TO_17,COVERAGE_WINTERSPORTS,COVERAGE_GOLFCOVER,"
					+ "ADDITIONAL_COVER,POLICY_TERM,CLAIM_Y_N,CLAIM_REMARKS,CLAIM_AMOUNT,INSURANCE_START_DATE,"
					+ "INSURANCE_END_DATE,ENTRY_DATE,EFFECTIVE_DATE,AMEND_ID,CUSTOMER_ID,LOGIN_ID,STATUS,EXCLUDE_USA_CANADA, "
					+ " WORLDWIDE,MEDICAL_EXPENSES_EXCLUDED,BAGGAGE_EXCLUDED,SPOUSE_DISCOUNT,FAMILY_DISCOUNT,PRODUCTID,TERRORISM_EXTENSION,scheme_cover,COUNTRY_ORIGIN,COUNTRY_DESTN) values("
					+ "?,?,?,?,?,?,?,?,?,?,?,?,?,?,(select "+hour+" from dual),?, ? ,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

			args[0] = Integer.toString(current_num);
			args[1] = Integer.toString(num_adults);
			args[2] = Integer.toString(small_kids);
			args[3] = Integer.toString(totalpeople);
			args[4] = Integer.toString(num_childs);
			args[5] = winext;
			args[6] = golfext;
			args[7] = additionalcover;
			args[8] = covperiod;
			args[9] = claim;
			args[10] = claimdetails;
			args[11] = Long.toString(sum);
			args[12] = startdate;
			args[13] = expdate;
			args[14] = startdate;
			args[15] = "0";
			args[16] = customerid;
			args[17] = user;
			args[18] = "Y";
			args[19] = usaext;
			args[20] = worldWide;
			args[21] = medicalExclusion;
			args[22] = baggageExclusion;
			args[23] = spouseDiscount;
			args[24] = familyDiscount;
			args[25] = pid;			
			args[26] = terrorism_ext;
			args[27] = schemeName; //NEW COLUMN ADDED IN TRAVEL_HEADER SCHEME_COVER
			
			args[28] = countryOrigin;
			args[29] = countryDestn;
			
			
			sqlMap.put(QUERY+mapCnt,queryheader);
			sqlMap.put(ARGS+mapCnt++,args );


			// INSERTION TO HOME_POSITION_MASTER TABLE
			int maxnum = 0;
			String pos_query = "";
			String referral_desc = "";
			claim         =	CommonHelp.notNullCheck(claim);
			oldage        =	CommonHelp.notNullCheck(oldage);
			treatment 	  = CommonHelp.notNullCheck(treatment);
			System.out.println("getReferralDescription => pid: "+pid+" oldage: "+oldage+" treatment: "+treatment+" claim: "+claim+" above75Ref: "+above75Ref+" totalpeople: "+totalpeople+" loginBra: "+loginBra);
			referral_desc = getReferralDescription(pid, oldage, treatment,claim, above75Ref, totalpeople, loginBra,Integer.parseInt(covperiod));
			oldage 		  = CommonHelp.notNullCheck(oldage);
			treatment     = CommonHelp.notNullCheck(treatment);
			
			if("Cleared".equalsIgnoreCase(referral_desc))
			{
				referral_desc = "";
			}
			
			maxnum = com.maan.common.CopyQuote.DataSelect.getMaximumApplicationNo(travelPid,loginBra);
			
			String schemeId =  getSchemeId(travelPid, "11", schemeName);

			if (referral_desc.length() > 0 && isReferralFlag)
			{
				pos_query = "insert into HOME_POSITION_MASTER(QUOTE_NO,CUSTOMER_ID,LOGIN_ID,PRODUCT_ID,COMPANY_ID,POLICY_TERM,AMEND_ID,INCEPTION_DATE,EXPIRY_DATE,EFFECTIVE_DATE,ENTRY_DATE,STATUS,APPLICATION_NO,AGE_ABOVE_SIXTY_FIVE,EXISTING_MEDICAL_CONDITION,MEDICAL_TRAVEL_CLAIMS,REMARKS,REFERRAL_DESCRIPTION,PROPOSAL_NO) values(?,?,?,?,?,?,?,(select "+hour+" from dual),to_Date(?,'dd-mm-yyyy'),to_date(?,'dd-mm-yyyy'),(select "+hour+" from dual),?,?,?,?,?,?,?,?)";
				System.out.println("==========================================================pos_query: "+pos_query);
				args = new String[17];
				args[0] = Integer.toString(current_num);
				args[1] = customerid;
				args[2] = user;
				args[3] = pid;
				//args[3] = (String)hasM.get("optPids");
				args[4] = "20";
				args[5] = covperiod;
				args[6] = "0";
				args[7] = expdate;
				args[8] = startdate;
				args[9] = "Y";
				args[10] = Integer.toString(maxnum);
				args[11] = oldage;
				args[12] = treatment;
				args[13] = claim;
				args[14] = "Referal";
				args[15] = referral_desc;
				//args[16] = travelPid;
				//args[16] = schemeId;
				args[16] = pid;
				
				sqlMap.put(QUERY+mapCnt,pos_query);
				sqlMap.put(ARGS+mapCnt++,args );
			} else {
				args = new String[16];
				pos_query = "insert into HOME_POSITION_MASTER(QUOTE_NO,CUSTOMER_ID,LOGIN_ID,PRODUCT_ID,COMPANY_ID,POLICY_TERM,AMEND_ID,INCEPTION_DATE,EXPIRY_DATE,EFFECTIVE_DATE,ENTRY_DATE,STATUS,APPLICATION_NO,AGE_ABOVE_SIXTY_FIVE,EXISTING_MEDICAL_CONDITION,MEDICAL_TRAVEL_CLAIMS,ADMIN_REMARKS,PROPOSAL_NO) values(?,?,?,?,?,?,?,(select "+hour+" from dual),to_date(?,'dd-mm-yyyy'),to_date(?,'dd-mm-yyyy'),(select "+hour+" from dual),?,?,?,?,?,?,?)";
				System.out.println("==========================================================pos_query: "+pos_query);
				args[0] = Integer.toString(current_num);
				args[1] = customerid;
				args[2] = user;
				args[3] = pid;
				//args[3] = (String)hasM.get("optPids");
				args[4] = "20";
				args[5] = covperiod;
				args[6] = "0";
				args[7] = expdate;
				args[8] = startdate;
				args[9] = "Y";
				args[10] = Integer.toString(maxnum);
				args[11] = oldage;
				args[12] = treatment;
				args[13] = claim;
				args[14] = memoranda;
				args[15] = pid;
				//args[15] = schemeId;
				sqlMap.put(QUERY+mapCnt,pos_query);
				sqlMap.put(ARGS+mapCnt++,args );
			}
			// runner.multipleInsertion(pos_query, args); // May 17
			sqlMap.put("Count",Integer.toString(mapCnt));
			runner.multipleUpdateTransaction((HashMap)sqlMap);
			LogManager.debug(EXIT);
			LogManager.popRemove();
			return cur_num;
	}
	public HashMap getBackInsertedData(final String qnum,final String user,final String customerid)throws BaseException
	{
		final HashMap hasM = new HashMap();
		LogManager.push("TravelBean getBackInsertedData method()");
		LogManager.debug(ENTER);
		String query1 = "";
		String query2 = "";
		String[][] result1 = new String[0][0];
		String[][] result2 = new String[0][0];
		String[] names = {};
		String[] dbday = {};
		String[] dbmonth = {};
		String[] dbyear = {};
		String[] relations = {};
		String[] nationality = {};
		String[] genders = {};
		String[] remarks = {};
		String[] serialnum = {};
		String usaext = "";
		String winext = "";
		String golfext = "";
		String med_excluded = "";
		String bag_excluded = "";
		String covperiod = "";
		String effday = "";
		String effmonth = "";
		String effyear = "";
		
		String endday = "";
		String endmonth = "";
		String endyear = "";
		String countryOrigin = "";
		String countryDestn = "";
		
		String claim = "";
		String claimdetails = "";
		String suminsured = "";
		String expdate = "";
		int count = 0;
		String productId = "";
		String terrorism_ext = "";
		String memoranda = "";
		String sixty = "";
		String medical = "";
		String schemeId = "";
		//String ratingFactorNames[][] = new String[0][0];
		String[] args = null;
		args = new String[3];
		query1 = "select TOTAL_NO_OF_PEOPLE_COVERED,COVERAGE_US_CANADA,COVERAGE_WINTERSPORTS,COVERAGE_GOLFCOVER,ADDITIONAL_COVER,POLICY_TERM,CLAIM_Y_N,CLAIM_REMARKS,CLAIM_AMOUNT,extract(day from INSURANCE_START_DATE),extract(month from INSURANCE_START_DATE),extract(year from INSURANCE_START_DATE),extract(day from INSURANCE_END_DATE), extract(month from INSURANCE_END_DATE),extract(year from INSURANCE_END_DATE),QUOTE_NO, BAGGAGE_EXCLUDED,MEDICAL_EXPENSES_EXCLUDED,EXCLUDE_USA_CANADA,PRODUCTID,TERRORISM_EXTENSION,SCHEME_COVER,COUNTRY_ORIGIN,COUNTRY_DESTN from TRAVEL_HEADER where CUSTOMER_ID = ? and LOGIN_ID = (select login_id from home_position_master where STATUS = 'Y' and QUOTE_NO = ?)  and STATUS = 'Y' and QUOTE_NO = ?";
		args[0] = customerid;
		args[1] = qnum;
		args[2] = qnum;
		result1 = runner.multipleSelection(query1, args);
		args = new String[1];
		query2 = "select SERIAL_NO,PASSENGER_NAME,extract(day from DOB),extract(month from DOB),extract(year from DOB),RELATION,ILLNESS_Y_N,ILLNESS_REMARKS,GENDER,NATIONALITY from TRAVEL_DETAIL where QUOTE_NO = ? and status = 'Y' order by SERIAL_NO";
		System.out.println("Test-1");
		
		args[0] = Integer.toString(Integer.parseInt(qnum));
		result2 = runner.multipleSelection(query2, args);
		
		System.out.println("Test-2");
		
		if (result1 != null && result1.length != 0) {
			count = Integer.parseInt(result1[0][0]);
			names = new String[count];
			dbday = new String[count];
			dbmonth = new String[count];
			dbyear = new String[count];
			relations = new String[count];
			nationality = new String[count];
			genders = new String[count];
			remarks = new String[count];
			serialnum = new String[count];
		}
		if (names != null && names.length != 0 && result2 != null && result2.length != 0)
		{
			for (int i = 0; i < result2.length; i++)
			{
				serialnum[i] = result2[i][0];
				names[i] = result2[i][1];
				dbday[i] = result2[i][2];
				dbmonth[i] = result2[i][3];
				dbyear[i] = result2[i][4];
				relations[i] = result2[i][5];
				remarks[i] = result2[i][7];
				genders[i] = result2[i][8];
				nationality[i] = result2[i][9];
				if (remarks[i] != null){
					remarks[i] = remarks[i].trim();
				}
			}
		}
		if (result1.length > 0)
		{
			usaext = CommonHelp.notNullCheck(result1[0][18]);
			winext = CommonHelp.notNullCheck(result1[0][2]);
			golfext = CommonHelp.notNullCheck(result1[0][3]);
			med_excluded = CommonHelp.notNullCheck(result1[0][17]);
			bag_excluded = CommonHelp.notNullCheck(result1[0][16]);
			covperiod = CommonHelp.notNullCheck(result1[0][5]);
			effday = CommonHelp.notNullCheck(result1[0][9]);
			effmonth = CommonHelp.notNullCheck(result1[0][10]);
			effyear = CommonHelp.notNullCheck(result1[0][11]);
			claim = CommonHelp.notNullCheck(result1[0][6]);
			claimdetails = CommonHelp.notNullCheck(result1[0][7]);
			suminsured = CommonHelp.notNullCheck(result1[0][8]);
			productId = CommonHelp.notNullCheck(result1[0][19]);
			terrorism_ext = CommonHelp.notNullCheck(result1[0][20]);
			schemeId    =  CommonHelp.notNullCheck(result1[0][21]);
		}
		if (Integer.parseInt(effday) < 10) {
			effday = "0" + effday;
		}
		if (Integer.parseInt(effmonth) < 10) {
			effmonth = "0" + effmonth;
		}
		String query3 = "";
		String result3[][] = new String[0][0];
		args = new String[1];
		query3 = "select AGE_ABOVE_SIXTY_FIVE,EXISTING_MEDICAL_CONDITION,MEDICAL_TRAVEL_CLAIMS,ADMIN_REMARKS from HOME_POSITION_MASTER where QUOTE_NO = ? ";
		args[0] = qnum;
		result3 = runner.multipleSelection(query3, args);
		claim = "";
		if (result3.length > 0) {
			sixty = CommonHelp.notNullCheck(result3[0][0]);
			medical = CommonHelp.notNullCheck(result3[0][1]);
			claim = CommonHelp.notNullCheck(result3[0][2]);
			memoranda = CommonHelp.notNullCheck(result3[0][3]);
		}
		//
		String stVal = "";
		
		System.out.println("==========================Inside0============================");
		if (result1[0][13] != null)
		{
			System.out.println("==========================Inside1============================"+prId);
			stVal = months[Integer.parseInt(result1[0][13])] ;
			expdate = result1[0][12] + "-" + stVal + "-" + result1[0][14];
			if(prId==null || "".equalsIgnoreCase(prId))
			prId=getProductValue();
				
			if("32".equalsIgnoreCase(prId))
			{
				System.out.println("==========================Inside Start============================");
				endday   =  result1[0][12];
				endmonth =  result1[0][13];
				endyear  =  result1[0][14];
				countryOrigin = result1[0][22];
				countryDestn = result1[0][23];
				System.out.println("==========================Inside-End=======countryOrigin: "+countryOrigin+"====countryDestn: "+countryDestn+"=================");
			}
		}
		System.out.println("==========================Outside============================");
		
		if (serialnum != null){
			hasM.put("serialnum", serialnum);
		}
		if (names != null){
			hasM.put("names", names);
		}
		if (dbday != null){
			hasM.put("dbday", dbday);
		}
		if (dbmonth != null){
			hasM.put("dbmonth", dbmonth);
		}
		if (dbyear != null){
			hasM.put("dbyear", dbyear);
		}
		if (relations != null){
			hasM.put("relations", relations);
		}
		if (nationality != null){
			hasM.put("nationality", nationality);
		}
		if (genders != null){
			hasM.put("genders", genders);
		}
		if (usaext != null) {
			hasM.put("UsaExt", usaext);
		}
		if (memoranda != null){
			hasM.put("memoranda", memoranda);
		}
		terrorism_ext = CommonHelp.notNullCheck(terrorism_ext );
		/*ratingFactorNames = getRatingFactorNames(productId);
		if (ratingFactorNames.length > 0) {
			hasM.put(ratingFactorNames[0][0], med_excluded);
			hasM.put(ratingFactorNames[1][0], bag_excluded);
			hasM.put(ratingFactorNames[2][0], winext);
			hasM.put(ratingFactorNames[3][0], golfext);
			hasM.put(ratingFactorNames[4][0], terrorism_ext);
		}*/
		
		hasM.put("medical_excluded",med_excluded);
		hasM.put("personal_baggage",bag_excluded);
		hasM.put("winter_sports",winext);
		hasM.put("Golf_Extension",golfext);
		//hasM.put(getRatingFactorSubName("11","100"),schemeId);
		
		String proID = runner.singleSelection("select proposal_no from home_position_master where quote_no = "+qnum);
		LogManager.info("home_position_master proposal_no==> "+proID);
		hasM.put(getRatingFactorSubName("11",proID),schemeId);
		terrorism_ext = "" ;
		hasM.put("optPids", productId);
		if (covperiod != null){
			hasM.put("coverPeriod", covperiod);
		}
		if (effday != null){
			hasM.put("EffDay", effday);
		}
		if (effmonth != null){
			hasM.put("EffMonth", effmonth);
		}
		if (effyear != null){
			hasM.put("EffYear", effyear);
		}
		
		if (endday != null){
			hasM.put("EndDay", endday);
		}
		if (endmonth != null){
			hasM.put("EndMonth", endmonth);
		}
		if (endyear != null){
			hasM.put("EndYear", endyear);
		}
		if (countryOrigin != null){
			hasM.put("countryOrigin", countryOrigin);
		}
		if (countryDestn != null){
			hasM.put("countryDestn", countryDestn);
		}
		
		
		if (expdate != null){
			hasM.put("ExpDate", expdate);
		}
		if (claim != null){
			hasM.put("claim", claim);
		}
		if (claimdetails != null){
			hasM.put("claimDetails", claimdetails);
		}
		if (suminsured != null){
			hasM.put("suminsured", suminsured);
		}
		int cnt = 0;
		cnt = Integer.parseInt(result1[0][0]);
		hasM.put("TotalPersons", Integer.toString(cnt));
		hasM.put("cnt", Integer.toString(count));
		if (remarks != null){
			hasM.put("Remarks", remarks);
		}
		if (result1[0][15] != null){
			hasM.put("QUOTE_NO", result1[0][15]);
		}
		if (sixty != null){
			hasM.put("oldage", sixty);
		}
		if (medical != null){
			hasM.put("treatment", medical);
		}
		LogManager.debug(EXIT);
		LogManager.popRemove();
	
		return hasM;
	}
	public HashMap getBackInsertedDataAmend(final String qnum,final String user,final String customerid) throws BaseException
	{
		LogManager.push("TravelBean getBackInsertedDataAmend method()");
		LogManager.debug(ENTER);
		String query1 = "";
		String query2 = "";
		String[][] result1 = new String[0][0];
		String[][] result2 = new String[0][0];
		final HashMap hasM = new HashMap();
		String[] names = {};
		String[] dbday = {};
		String[] dbmonth = {};
		String[] dbyear = {};
		String[] relations = {};
		String[] nationality = {};
		String[] genders = {};
		String[] remarks = {};
		String[] serialnum = {};
		String usaext = "";
		String winext = "";
		String golfext = "";
		String med_excluded = "";
		String bag_excluded = "";
		String covperiod = "";
		String effday = "";
		String effmonth = "";
		String effyear = "";
		String claim = "";
		String claimdetails = "";
		String suminsured = "";
		String expdate = "";
		int count = 0;
		String productId = "";
		String terrorism_ext = "";
		String memoranda = "";
		String sixty = "";
		String medical = "";
		String ratingFactorNames[][] = new String[0][0];
		String[] args = new String[0];
		args = new String[6];
		query1 = "select TOTAL_NO_OF_PEOPLE_COVERED,COVERAGE_US_CANADA,COVERAGE_WINTERSPORTS, COVERAGE_GOLFCOVER,ADDITIONAL_COVER,POLICY_TERM,CLAIM_Y_N,CLAIM_REMARKS, CLAIM_AMOUNT,extract(day from INSURANCE_START_DATE),extract(month from INSURANCE_START_DATE), extract(year from INSURANCE_START_DATE),extract(day from INSURANCE_END_DATE), extract(month from INSURANCE_END_DATE),extract(year from INSURANCE_END_DATE),QUOTE_NO, BAGGAGE_EXCLUDED,MEDICAL_EXPENSES_EXCLUDED,EXCLUDE_USA_CANADA,PRODUCTID,TERRORISM_EXTENSION from TRAVEL_HEADER where CUSTOMER_ID = ? and LOGIN_ID = (select login_id from home_position_master where QUOTE_NO = ?) and STATUS = 'Y' and QUOTE_NO = ? and amend_id=(select max(amend_id) from TRAVEL_HEADER where CUSTOMER_ID = ? and LOGIN_ID = (select login_id from home_position_master where QUOTE_NO = ?) and STATUS = 'Y' and QUOTE_NO = ?)";
		args[0] = customerid;
		args[1] = qnum;
		args[2] = qnum;
		args[3] = customerid;
		args[4] = qnum;
		args[5] = qnum;
		result1 = runner.multipleSelection(query1, args);

		args = new String[2];
		query2 = "select SERIAL_NO,PASSENGER_NAME,extract(day from DOB),extract(month from DOB),extract(year from DOB),RELATION,ILLNESS_Y_N,ILLNESS_REMARKS,GENDER,NATIONALITY from TRAVEL_DETAIL where QUOTE_NO = ? and status = 'Y' and amend_id=(select max(amend_id) from TRAVEL_DETAIL where QUOTE_NO = ? and status = 'Y') order by SERIAL_NO";
		args[0] = Integer.toString(Integer.parseInt(qnum));
		args[1] = qnum;

		result2 = runner.multipleSelection(query2, args);

		if (result1 != null && result1.length != 0) {
			count = Integer.parseInt(result1[0][0]);
			names = new String[count];
			dbday = new String[count];
			dbmonth = new String[count];
			dbyear = new String[count];
			relations = new String[count];
			nationality = new String[count];
			genders = new String[count];
			remarks = new String[count];
			serialnum = new String[count];
		}
		if (names != null && names.length != 0 && result2 != null && result2.length != 0)
		{
			for (int i = 0; i < result2.length; i++)
			{
				serialnum[i] = result2[i][0];
				names[i] = result2[i][1];
				dbday[i] = result2[i][2];
				dbmonth[i] = result2[i][3];
				dbyear[i] = result2[i][4];
				relations[i] = result2[i][5];
				remarks[i] = result2[i][7];
				genders[i] = result2[i][8];
				nationality[i] = result2[i][9];
				if (remarks[i] != null){
					remarks[i] = remarks[i].trim();
				}
			}
		}
		if (result1.length > 0) {
			usaext = CommonHelp.notNullCheck(result1[0][18]);
			winext = CommonHelp.notNullCheck(result1[0][2]);
			golfext = CommonHelp.notNullCheck(result1[0][3]);
			med_excluded = CommonHelp.notNullCheck(result1[0][17]);
			bag_excluded = CommonHelp.notNullCheck(result1[0][16]);
			covperiod = CommonHelp.notNullCheck(result1[0][5]);
			effday = CommonHelp.notNullCheck(result1[0][9]);
			effmonth = CommonHelp.notNullCheck(result1[0][10]);
			effyear = CommonHelp.notNullCheck(result1[0][11]);
			claim = CommonHelp.notNullCheck(result1[0][6]);
			claimdetails = CommonHelp.notNullCheck(result1[0][7]);
			suminsured = CommonHelp.notNullCheck(result1[0][8]);
			productId = CommonHelp.notNullCheck(result1[0][19]);
			terrorism_ext = CommonHelp.notNullCheck(result1[0][20]);
		}
		if (Integer.parseInt(effday) < 10) {
			effday = "0" + effday;
		}
		if (Integer.parseInt(effmonth) < 10) {
			effmonth = "0" + effmonth;
		}
		String query3 = "";
		String result3[][] = new String[0][0];
		args = new String[1];
		query3 = "select AGE_ABOVE_SIXTY_FIVE,EXISTING_MEDICAL_CONDITION,MEDICAL_TRAVEL_CLAIMS,ADMIN_REMARKS from HOME_POSITION_MASTER where QUOTE_NO = ? ";
		args[0] = Integer.toString(Integer.parseInt(qnum)) ;
		result3 = runner.multipleSelection(query3, args);
		claim = "";
		if (result3.length > 0) {
			sixty = CommonHelp.notNullCheck(result3[0][0]);
			medical = CommonHelp.notNullCheck(result3[0][1]);
			claim = CommonHelp.notNullCheck(result3[0][2]);
			memoranda = CommonHelp.notNullCheck(result3[0][3]);
		}
		//
		String stValue = "";

		if (result1[0][13] != null)
		{
			stValue = months[Integer.parseInt(result1[0][13])] ;
			expdate = result1[0][12] + "-" + stValue + "-" + result1[0][14];
		}

		if (serialnum != null){
			hasM.put("serialnum", serialnum);
		}
		if (names != null){
			hasM.put("names", names);
		}
		if (dbday != null){
			hasM.put("dbday", dbday);
		}
		if (dbmonth != null){
			hasM.put("dbmonth", dbmonth);
		}
		if (dbyear != null){
			hasM.put("dbyear", dbyear);
		}
		if (relations != null){
			hasM.put("relations", relations);
		}
		if (nationality != null){
			hasM.put("nationality", nationality);
		}
		if (genders != null){
			hasM.put("genders", genders);
		}
		if (usaext != null) {
			hasM.put("UsaExt", usaext);
		}
		if (memoranda != null){
			hasM.put("memoranda", memoranda);
		}
		terrorism_ext = terrorism_ext == null ? "" : terrorism_ext;
		ratingFactorNames = getRatingFactorNames(productId);
		if (ratingFactorNames.length > 0) {
			hasM.put(ratingFactorNames[0][0], med_excluded);
			hasM.put(ratingFactorNames[1][0], bag_excluded);
			hasM.put(ratingFactorNames[2][0], winext);
			hasM.put(ratingFactorNames[3][0], golfext);
			hasM.put(ratingFactorNames[4][0], terrorism_ext);
		}

		hasM.put("optPids", productId);
		if (covperiod != null){
			hasM.put("coverPeriod", covperiod);
		}
		if (effday != null){
			hasM.put("EffDay", effday);
		}
		if (effmonth != null){
			hasM.put("EffMonth", effmonth);
		}
		if (effyear != null){
			hasM.put("EffYear", effyear);
		}
		if (expdate != null){
			hasM.put("ExpDate", expdate);
		}
		if (claim != null){
			hasM.put("claim", claim);
		}
		if (claimdetails != null){
			hasM.put("claimDetails", claimdetails);
		}
		if (suminsured != null){
			hasM.put("suminsured", suminsured);
		}
		final int cnt = Integer.parseInt(result1[0][0]);
		hasM.put("TotalPersons", Integer.toString(cnt));
		hasM.put("cnt", Integer.toString(count));
		if (remarks != null){
			hasM.put("Remarks", remarks);
		}
		if (result1[0][15] != null){
			hasM.put("QUOTE_NO", result1[0][15]);
		}
		if (sixty != null){
			hasM.put("oldage", sixty);
		}
		if (medical != null){
			hasM.put("treatment", medical);
		}
		LogManager.debug(EXIT);
		LogManager.popRemove();
		return hasM;
	}
	public String insertForTravellersEndorsementDetails(final HashMap hasM,final String quoteNo)throws BaseException
	{
		LogManager.push("TravelBean insertForTravellersEndorsementDetails method()");
		LogManager.debug(ENTER);
		final Map sqlMap = new HashMap();
		int mapCnt = 0;
		String queryheader = "";
		String querydetails = "";
		String effday = "";
		String effmonth = "";
		String effyear = "";
		String expdate = "";
		String startdate = "";
		String mon = "";
		String[] args = null;

		final String[] names = (String[]) hasM.get("names");
		final String[] dbmonth = (String[]) hasM.get("dbmonth");
		final String[] nationality = (String[]) hasM.get("nationality");
		final String[] genders = (String[]) hasM.get("genders");
		int amend = getMaxAmendId("TRAVEL_DETAIL", quoteNo);

		for (int i = 0; i < names.length; i++)
		{
			mon = months[Integer.parseInt(dbmonth[i])] ;
			names[i] = names[i].replaceAll("'", "''");
			args = new String[8];
			querydetails = "insert into TRAVEL_DETAIL(QUOTE_NO,SERIAL_NO,PASSENGER_NAME,DOB,AMEND_ID,STATUS,RELATION,GENDER,NATIONALITY,ILLNESS_Y_N,ILLNESS_REMARKS" +
					",REMARKS,PREMIUM,AGE) select QUOTE_NO,?,?,DOB,?,STATUS, RELATION,?,?,ILLNESS_Y_N,ILLNESS_REMARKS,REMARKS,PREMIUM,AGE from TRAVEL_DETAIL where QUOTE_NO = ? and AMEND_ID = ? and SERIAL_NO  = ?";
			args[0] = Integer.toString(i+1);
			args[1] = names[i];
			args[2] = Integer.toString(amend);
			args[3] = genders[i];
			args[4] = nationality[i];
			args[5] = quoteNo;
			args[6] = Integer.toString(amend - 1);
			args[7] = Integer.toString(i + 1);
			sqlMap.put(QUERY+mapCnt,querydetails);
			sqlMap.put(ARGS+mapCnt++,args );
			//runner.multipleInsertion(querydetails, args); // may 17
		}
			//final String covperiod = (String) hasM.get("coverPeriod");
			effday = (String) hasM.get("EffDay");
			effmonth = (String) hasM.get("EffMonth");
			effyear = (String) hasM.get("EffYear");
			expdate = (String) hasM.get("ExpDate");

			if (effmonth != null)
			{
				mon = months[Integer.parseInt(effmonth)] ;
				startdate = effday + "-" + mon + "-" + effyear;
			}
			int amendHead = getMaxAmendId("TRAVEL_HEADER", quoteNo);
			com.maan.Home.DataManipualtion.DataSelect data = new com.maan.Home.DataManipualtion.DataSelect();
			final String hour = data.getSysdateTime(quoteNo,"QuoteNo");
			args = new String[6];
			queryheader = "insert into TRAVEL_HEADER(QUOTE_NO,TOTAL_ADULT_PASSENGERS,TOTAL_CHILD_PASSENGERS,TOTAL_NO_OF_PEOPLE_COVERED,COVERAGE_US_CANADA,COVERAGE_WINTERSPORTS,COVERAGE_GOLFCOVER,ADDITIONAL_COVER,POLICY_TERM,POLICY_TRAVEL_TRIPS,CLAIM_Y_N,CLAIM_REMARKS,CLAIM_AMOUNT,ADULT_PREMUIM,CHILD_PREMIUM,WINTER_PREMIUM,GOLF_PREMIUM,ADDITIONAL_PREMIUM,TOTAL_PREMIUM,INSURANCE_START_DATE,INSURANCE_END_DATE,PREMIUM_LOAD_DIS_PERCENT,PREMIUM_LOAD_DIS_DESC,PREMIUM_LOAD_DIS_AMOUNT,ENTRY_DATE,EFFECTIVE_DATE,AMEND_ID,CUSTOMER_ID,LOGIN_ID,REMARKS,STATUS,NET_PREMIUM,PRODUCTID,CHILD_BETWEEN_3_TO_17,WORLDWIDE,BAGGAGE_EXCLUDED,MEDICAL_EXPENSES_EXCLUDED,EXCLUDE_USA_CANADA,SPOUSE_DISCOUNT,FAMILY_DISCOUNT,DISCOUNTED_VALUE,TERRORISM_EXTENSION) select QUOTE_NO, TOTAL_ADULT_PASSENGERS,TOTAL_CHILD_PASSENGERS,TOTAL_NO_OF_PEOPLE_COVERED,COVERAGE_US_CANADA,COVERAGE_WINTERSPORTS,COVERAGE_GOLFCOVER,ADDITIONAL_COVER,POLICY_TERM,POLICY_TRAVEL_TRIPS,CLAIM_Y_N,CLAIM_REMARKS,CLAIM_AMOUNT,ADULT_PREMUIM,CHILD_PREMIUM,WINTER_PREMIUM,GOLF_PREMIUM,ADDITIONAL_PREMIUM,TOTAL_PREMIUM,?,?,PREMIUM_LOAD_DIS_PERCENT,PREMIUM_LOAD_DIS_DESC,PREMIUM_LOAD_DIS_AMOUNT,(select "+hour+" from dual),?,?,CUSTOMER_ID,LOGIN_ID,REMARKS,STATUS,NET_PREMIUM,PRODUCTID,CHILD_BETWEEN_3_TO_17,WORLDWIDE,BAGGAGE_EXCLUDED,MEDICAL_EXPENSES_EXCLUDED,EXCLUDE_USA_CANADA,SPOUSE_DISCOUNT,FAMILY_DISCOUNT,DISCOUNTED_VALUE,TERRORISM_EXTENSION from TRAVEL_HEADER where quote_no = ? and amend_id = ?";
			args[0] = startdate;
			args[1] = expdate;
			args[2] = startdate;
			args[3] = Integer.toString(amendHead);
			args[4] = quoteNo;
			args[5] = Integer.toString(amendHead - 1);
			sqlMap.put(QUERY+mapCnt,queryheader);
			sqlMap.put(ARGS+mapCnt++,args );
			//runner.multipleInsertion(queryheader, args); May 17
			args = new String[1];
			args[0] = quoteNo;
			sqlMap.put(QUERY+mapCnt,"update HOME_POSITION_MASTER set Pdf_Broker_Status=0 where QUOTE_NO = ?");
			sqlMap.put(ARGS+mapCnt++,args );
			sqlMap.put("Count",Integer.toString(mapCnt));
			runner.multipleUpdateTransaction((HashMap)sqlMap);

			//runner.multipleUpdation("update HOME_POSITION_MASTER set Pdf_Broker_Status=0 where QUOTE_NO = ?", args); // May 17
			LogManager.debug(EXIT);
			LogManager.popRemove();
			return Integer.toString(amendHead);
	}

	public int getMaxAmendId(final String tableName,final String quoteNo)throws BaseException
	{
		LogManager.push("TravelBean getMaxAmendId method()");
		LogManager.debug(ENTER);
		final String sql = "select max(amend_id) from "+tableName+" where QUOTE_NO = ?";
		int amend = 0;
		final String[] args = {quoteNo};
		final String res = runner.singleSelection(sql, args);
		if (res != null)
		{
			if (res.length() > 0){
				amend = Integer.parseInt(res);
				amend = amend + 1;
			}
			else{
				amend = 0;
			}
		}
		else{
			amend = 0;
		}
		LogManager.debug(EXIT);
		LogManager.popRemove();
		return amend;
	}

	public boolean getEndronsementStatus(final String qno)throws BaseException
	{
		LogManager.push("TravelBean getEndronsementStatus method()");
		LogManager.debug(ENTER);
		boolean result = false;
		com.maan.Home.DataManipualtion.DataSelect data = null;
		data =  new com.maan.Home.DataManipualtion.DataSelect();
		final String hour = data.getSysdateTime(qno,"QuoteNo");
		final String sql = "select count(*) from Home_position_master where EFFECTIVE_DATE>(select "+hour+" from dual) and quote_no = ? ";
		final String[] args = {qno};
		final String res = runner.singleSelection(sql, args);
		if ("0".equals(res)){
			result = false;
		}
		else{
			result = true;
		}
		LogManager.debug(EXIT);
		LogManager.popRemove();
		return result;
	}
	public String updateTravellersDetails(final String qnum,final HashMap hasM,final String tot_persons,final String pid,final String loginBra)throws BaseException
	{
		String temp = "sysdate";
		//temp = runner.getSysdate(loginBra);		
		LogManager.push("TravelBean updateTravellersDetails method()");
		LogManager.debug(ENTER);
		final Map sqlMap = new HashMap();
		int mapCnt = 0;
		String queryDetail = "";
		String queryHeader = "";
		String[] names = {};
		String[] dbday = {};
		String[] dbmonth = {};
		String[] dbyear = {};
		String[] relation = {};
		String[] nationality = {};
		String[] genders = {};
		String[] serialnumber = {};
		int[] age = new int[0];
		String usaext = "";
		String winext = "";
		String golfext = "";
		String covperiod = "";
		String effday = "";
		String effmonth = "";
		String effyear = "";
		
		String endday = "";
		String endmonth = "";
		String endyear = "";
		String countryOrigin = "";
		String countryDestn = "";
		
		String claim = "";
		String claimdetails = "";
		String suminsured = "0";
		String expdate = "";
		String customerid = "";
		String oldage = "";
		String treatment = "";
		String terrorism_ext = "";
		String memoranda = "";
		int num_adults = 0;
		int num_childs = 0;
		int tot_num = 0;
		String additionalcover = "";
		String mon = "";
		String startdate = "";
		String dob = "";
		String temp1 = "";
		String temp2 = "";
		String schemeName = "";
		int total_persons = 0;
		int i = 0;
		int count = 0;
		String cnt1 = (String) hasM.get("cnt"); // TOTAL NUMBER OF ROWS ENTERED IN FRONT END
		if (cnt1 != null) {
			count = Integer.parseInt(cnt1);
		}
		if (tot_persons != null) { // THE VALUE OBTAINED FROM TRAVEL_HEADER TABLE
			total_persons = Integer.parseInt(tot_persons);
		}
		if(total_persons<=0){
			final String totArgs[] = {qnum};
			final String totPersons = runner.singleSelection("select nvl(TOTAL_NO_OF_PEOPLE_COVERED,'0') from TRAVEL_HEADER where quote_no=?", totArgs);
			total_persons = Integer.parseInt(totPersons);
		}
		String[] args = new String[0];
		//String ratingFactorNames[][] = new String[0][0];
		//ratingFactorNames = getRatingFactorNames(pid);
		names = (String[]) hasM.get("names");
		dbday = (String[]) hasM.get("dbday");
		dbmonth = (String[]) hasM.get("dbmonth");
		dbyear = (String[]) hasM.get("dbyear");
		relation = (String[]) hasM.get("relations");
		nationality = (String[]) hasM.get("nationality");
		genders = (String[]) hasM.get("genders");
		serialnumber = (String[]) hasM.get("serialnum");
		temp1 = (String) hasM.get("adults");
		temp2 = (String) hasM.get("kids");
		age = (int[]) hasM.get("age");
		memoranda = (String) hasM.get("memoranda");
		customerid = (String) hasM.get("customerid");
		customerid = customerid == null ? "" : customerid;
		temp1 = temp1 == null ? "0" : temp1;
		temp2 = temp2 == null ? "0" : temp2;
		num_adults = Integer.parseInt(temp1);
		num_childs = Integer.parseInt(temp2);

			boolean above75Ref = false;
			for (i = 0; i < total_persons; i++)
			{
				if (dbmonth != null && dbday != null && dbyear != null)
				{
					mon = months[Integer.parseInt(dbmonth[i])] ;
					dob = dbday[i] + "-" + mon + "-" + dbyear[i];
					if (!"0".equals(dbday[i]) && !"0".equals(dbmonth[i]) && !"0".equals(dbyear[i])){
						dob = dbday[i] + "-" + mon + "-" + dbyear[i];
					}
					else{
						dob = "";
					}
				} else {
					dob = "";
				}
				String scVal = "1";
				if (serialnumber != null) {
					if (i == 0){
						scVal = Integer.toString(1);
					}
					else{
						scVal = serialnumber[i];
					}
				}
				if (age != null) {
					if (age[i] > 75) {
						above75Ref = true;
					}

					args = new String[9];
					queryDetail = "update TRAVEL_DETAIL set PASSENGER_NAME = ? ,DOB = ?,RELATION = ? , AMEND_ID =  ? , GENDER = ? ,NATIONALITY= ? ,age = ? where QUOTE_NO = ? and  STATUS = 'Y' and serial_no = ?";
					args[0] = names[i];
					args[1] = dob;
					args[2] = relation[i];
					args[3] = "0";
					args[4] = genders[i];
					args[5] = nationality[i];
					args[6] = Integer.toString(age[i]);
					args[7] = qnum;
					args[8] = scVal;
					sqlMap.put(QUERY+mapCnt,queryDetail);
					sqlMap.put(ARGS+mapCnt++,args );
				} else {
					String args1[] = {names[i],dob,relation[i],"0",genders[i],nationality[i],qnum,scVal};
					queryDetail = "update TRAVEL_DETAIL set PASSENGER_NAME = ?,DOB =?,RELATION = ?,AMEND_ID = ?,GENDER = ?,NATIONALITY=? where QUOTE_NO =? and  STATUS = 'Y' and serial_no= ?";
					sqlMap.put(QUERY+mapCnt,queryDetail);
					sqlMap.put(ARGS+mapCnt++,args1);
				}

				// runner.multipleUpdation(queryDetail, args); // May 17
			}
			// IF NEW ROW IS ADDED THEN

			if (count > total_persons) {
				int sn = 0;
				int pos = 0;
				for (i = 0; i < serialnumber.length; i++) {
					if ("".equals(serialnumber[i])|| "0".equals(serialnumber[i])) {
						pos = i;
						break;
					}
				}
				try {
					sn = Integer.parseInt(serialnumber[pos - 1]) + 1;
				} catch (Exception e) {
					sn = 1;
				}
				for (i = total_persons; i < count; i++)
				{
					mon = months[Integer.parseInt(dbmonth[i])] ;
					String dob1 = "";
					if (!"0".equals(dbday[i]) && !"0".equals(dbmonth) && !"0".equals(dbyear)){
						dob1 = dbday[i] + "-" + mon + "-" + dbyear[i];
					}
					else{
						dob1 = "";
					}
					if (age != null) {
						if (age[i] > 75) {
							above75Ref = true;
						}

						args = new String[10];
						queryDetail = "insert into TRAVEL_DETAIL(QUOTE_NO,SERIAL_NO,PASSENGER_NAME,DOB,"
								+ "AMEND_ID,STATUS,RELATION,GENDER,NATIONALITY,age) values( ?,?,?,?,?,?,?,?,?,? )";

						args[0] = qnum;
						args[1] = Integer.toString(sn);
						args[2] = names[i];
						args[3] = dob1;
						args[4] = "0";
						args[5] = "Y";
						args[6] = relation[i];
						args[7] = genders[i];
						args[8] = nationality[i];
						args[9] = Integer.toString(age[i]);
					} else {
						args = new String[9];
						queryDetail = "insert into TRAVEL_DETAIL(QUOTE_NO,SERIAL_NO,PASSENGER_NAME,DOB,"
								+ "AMEND_ID,STATUS,RELATION,GENDER,NATIONALITY) values( ?,?,?,?,?,?,?,?,? )";
						args[0] = qnum;
						args[1] = Integer.toString(sn);
						args[2] = names[i];
						args[3] = dob1;
						args[4] = "0";
						args[5] = "Y";
						args[6] = relation[i];
						args[7] = genders[i];
						args[8] = nationality[i];
					}
					sqlMap.put(QUERY+mapCnt,queryDetail);
					sqlMap.put(ARGS+mapCnt++,args );
					//runner.multipleUpdation(queryDetail, args); //may 17
					sn = sn + 1;
				}
			}

			int smallChild = 0;
			if (hasM.get("smallKids") != null){
				smallChild = Integer.parseInt((String) hasM.get("smallKids"));
			}
			tot_num = num_adults + num_childs + smallChild;
			String tot = "";
			tot = (String) hasM.get("saveoption");
			if (tot == null || tot.equals(NULL) || tot.length() <= 0){
				tot = "0";
			}
			if (tot_num == 0) {
				tot_num = Integer.parseInt(tot);
			}

			usaext = (String) hasM.get("UsaExt");
			winext = (String) hasM.get("WinterExt");
			golfext = (String) hasM.get("GolfExt");
			
			if(!"32".equalsIgnoreCase(pid))
			covperiod = (String) hasM.get("coverPeriod");
			else
			covperiod = String.valueOf(hasM.get("noOfDaysCount"));
			
			effday = (String) hasM.get("EffDay");
			effmonth = (String) hasM.get("EffMonth");
			effyear = (String) hasM.get("EffYear");
			
			endday = (String) hasM.get("EndDay");
			endmonth = (String) hasM.get("EndMonth");
			endyear = (String) hasM.get("EndYear");
			countryOrigin = hasM.get("countryOrigin")==null?"":(String)hasM.get("countryOrigin");
			countryDestn = hasM.get("countryDestn")==null?"":(String)hasM.get("countryDestn");
			
			claim = (String) hasM.get("claim");
			oldage = (String) hasM.get("oldage");
			treatment = (String) hasM.get("treatment");
			 LogManager.info("travel issue test from bean..."+effday);
		     LogManager.info("travel issue test from bean..."+effmonth);
		     LogManager.info("travel issue test from bean..."+effyear);

			if (claim != null && claim.length() > 0 && "Y".equals(claim)) {
				claimdetails = (String) hasM.get("claimDetails");
				suminsured = (String) hasM.get("suminsured");
			}
			if (claimdetails == null && claimdetails.length() == 0){
				claimdetails = "";
			}
			expdate = (String) hasM.get("ExpDate");

			if (usaext != null && winext != null && golfext != null)
			{
				if ("Y".equals(usaext) || "Y".equals(winext) || "Y".equals(golfext)){
					additionalcover = "Y";
				}
				else {
					additionalcover = "";
				}
			} else {
				additionalcover = "";
			}

			mon = months[Integer.parseInt(effmonth)] ;

			LogManager.info("travel issue test from bean..."+effday);
		     LogManager.info("travel issue test from bean.mon.."+mon);
		     LogManager.info("travel issue test from bean..."+effyear);
			startdate = effday + "-" + mon + "-" + effyear;
			LogManager.info("travel issue test from bean startdate after merge..."+startdate);
			String med_excluded = "";
			String bag_excluded = "";
			String spouseDiscount = (String) hasM.get("spouseDiscount");
			String familyDiscount = (String) hasM.get("familyDiscount");

			spouseDiscount = (spouseDiscount == null
					|| spouseDiscount.equalsIgnoreCase(NULL) || spouseDiscount
					.equals("")) ? "N" : spouseDiscount;// New
			familyDiscount = (familyDiscount == null
					|| familyDiscount.equalsIgnoreCase(NULL) || familyDiscount
					.equals("")) ? "N" : familyDiscount;// New

			/*if (ratingFactorNames.length > 0) {
				med_excluded = (String) hasM.get(ratingFactorNames[0][0]);
				bag_excluded = (String) hasM.get(ratingFactorNames[1][0]);
				winext = (String) hasM.get(ratingFactorNames[2][0]);
				golfext = (String) hasM.get(ratingFactorNames[3][0]);
				terrorism_ext = (String) hasM.get(ratingFactorNames[4][0]);
			}*/
			
			med_excluded = (String) hasM.get("medical_excluded");
			bag_excluded = (String) hasM.get("personal_baggage");
			winext = (String) hasM.get("winter_sports");
			golfext = (String) hasM.get("Golf_Extension");
			
			schemeName = (String) hasM.get(getRatingFactorSubName("11", pid)); 
			terrorism_ext = "" ;

			usaext = (usaext == null || usaext.equalsIgnoreCase(NULL) || usaext
					.equalsIgnoreCase(SELECT)) ? "" : usaext;
			winext = (winext == null || winext.equalsIgnoreCase(NULL) || winext
					.equalsIgnoreCase(SELECT)) ? "" : winext;
			golfext = (golfext == null || golfext.equalsIgnoreCase(NULL) || golfext
					.equalsIgnoreCase(SELECT)) ? "" : golfext;

			bag_excluded = (bag_excluded == null
					|| bag_excluded.equalsIgnoreCase(NULL) || bag_excluded
					.equalsIgnoreCase(SELECT)) ? "" : bag_excluded;
			med_excluded = (med_excluded == null
					|| med_excluded.equalsIgnoreCase(NULL) || med_excluded
					.equalsIgnoreCase(SELECT)) ? "" : med_excluded;
			terrorism_ext = (terrorism_ext == null
					|| terrorism_ext.equalsIgnoreCase(NULL) || terrorism_ext
					.equalsIgnoreCase(SELECT)) ? "" : terrorism_ext;

			if ("Y".equals(usaext) || "Y".equals(winext) || "Y".equals(golfext)){
				additionalcover = "Y";
			}
			else {
				additionalcover = "";
			}
			
			if(!"32".equalsIgnoreCase(pid))
			{
				expdate = getEndDate(effyear+effmonth+effday,covperiod);
			}
			else
			{
				mon = months[Integer.parseInt(endmonth)] ;
				expdate = endday + "-" + mon + "-" + endyear;
			}
			
			args = new String[25];
			queryHeader = "update TRAVEL_HEADER set TOTAL_ADULT_PASSENGERS = ?"
					+ " ,TOTAL_CHILD_PASSENGERS = ? "
					+ " ,CHILD_BETWEEN_3_TO_17  = ? "
					+ " ,TOTAL_NO_OF_PEOPLE_COVERED = ? "
					+ " ,COVERAGE_WINTERSPORTS = ? "
					+ " ,COVERAGE_GOLFCOVER = ? ,ADDITIONAL_COVER = ? "
					+ " ,BAGGAGE_EXCLUDED = ? "
					+ " ,MEDICAL_EXPENSES_EXCLUDED = ? "
					+ " ,EXCLUDE_USA_CANADA = ?"
					+ " ,SPOUSE_DISCOUNT = ?"
					+ " ,FAMILY_DISCOUNT = ?"
					+ " ,POLICY_TERM =  ? ,CLAIM_Y_N = ? ,CLAIM_REMARKS = ?,CLAIM_AMOUNT = ?,INSURANCE_START_DATE = ?,INSURANCE_END_DATE = ? ,EFFECTIVE_DATE = ?,ENTRY_DATE =  to_date("+temp+"),PRODUCTID = ? ,AMEND_ID = 0 ,TERRORISM_EXTENSION = ?,SCHEME_COVER = ?,COUNTRY_ORIGIN=?,COUNTRY_DESTN=? where  QUOTE_NO = ? and STATUS = 'Y'";

			args[0] = Integer.toString(num_adults);
			args[1] = Integer.toString(smallChild);
			args[2] = Integer.toString(num_childs) ;
			args[3] = Integer.toString(tot_num);
			args[4] = winext;
			args[5] = golfext;
			args[6] = additionalcover;
			args[7] = bag_excluded;
			args[8] = med_excluded;
			args[9] = usaext;
			args[10] = spouseDiscount;
			args[11] = familyDiscount;
			args[12] = covperiod;
			args[13] = claim;
			args[14] = claimdetails;
			args[15] = suminsured;
			args[16] = startdate;
			args[17] = expdate;
			args[18] = startdate;
			args[19] = pid;
			args[20] = terrorism_ext;
			args[21] = schemeName;
			
			args[22] = countryOrigin;
			args[23] = countryDestn;
			
			args[24] = qnum;

			sqlMap.put(QUERY+mapCnt,queryHeader);
			sqlMap.put(ARGS+mapCnt++,args );
			//runner.multipleUpdation(queryHeader, args); May 17

			final com.maan.Home.DataManipualtion.DataSelect data = new com.maan.Home.DataManipualtion.DataSelect();
			final String hour = data.getSysdateTime(qnum,"QuoteNo");
			// UPDATION IN HOME_POSITION_MASTER TABLE
			String queryPosition = "";
			claim = CommonHelp.notNullCheck(claim);
			oldage = CommonHelp.notNullCheck(oldage);
			treatment = treatment == null ? "" : treatment;
			System.out.println("getReferralDescription => pid: "+pid+" oldage: "+oldage+" treatment: "+treatment+" claim: "+claim+" above75Ref: "+above75Ref+" totalpeople: "+tot_num+" loginBra: "+loginBra);
			String refDesc = getReferralDescription(pid, oldage, treatment, claim, above75Ref, tot_num, loginBra,Integer.parseInt(covperiod));
			
			if("Cleared".equalsIgnoreCase(refDesc))
			{
				updateReferalStatus(qnum,"","","");
				refDesc = getCheckReferral(qnum);
			}
				
			if (refDesc.length() <= 0){
				refDesc = getCheckReferral(qnum);
			}
			
			String schemeId =  getSchemeId(pid, "11", schemeName);

			if (refDesc.length() > 0&&isReferralFlag)
			{
				args = new String[10];
				queryPosition = "update HOME_POSITION_MASTER set AGE_ABOVE_SIXTY_FIVE = ?, EFFECTIVE_DATE = to_date(?,'dd-mm-yyyy'), EXISTING_MEDICAL_CONDITION = ?, EXPIRY_DATE = to_Date(?,'dd-mm-yyyy'), INCEPTION_DATE = (select "+hour+" from dual) , MEDICAL_TRAVEL_CLAIMS = ?,POLICY_TERM = ?, ENTRY_DATE =  (select "+hour+" from dual)  , REFERRAL_DESCRIPTION = ?, REMARKS = 'Referal',PRODUCT_ID = ?,premium='',OVERALL_PREMIUM='',PROPOSAL_NO = ? where  QUOTE_NO = ?";
				args[0] = oldage;
				args[1] = startdate;
				args[2] = treatment;
				args[3] = expdate;
				args[4] = claim;
				args[5] = covperiod;
				args[6] = refDesc;
				//args[7] = pid;
				//args[8] = schemeId//;
				//args[7] = (String)hasM.get("optPids");
				args[7] = pid;
				args[8] = pid;
				args[9] = qnum;
			} else {
				args = new String[10];
				queryPosition = "update HOME_POSITION_MASTER set AGE_ABOVE_SIXTY_FIVE = ? ,EFFECTIVE_DATE=to_date(?,'dd-mm-yyyy'),EXISTING_MEDICAL_CONDITION = ? ,EXPIRY_DATE=to_date(?,'dd-mm-yyyy'),INCEPTION_DATE=(select "+hour+" from dual),MEDICAL_TRAVEL_CLAIMS = ? ,ENTRY_DATE=(select "+hour+" from dual),POLICY_TERM= ? ,PRODUCT_ID = ? ,ADMIN_REMARKS = ? ,REFERRAL_DESCRIPTION='',REMARKS='',ADMIN_REFERRAL_STATUS='',premium='',OVERALL_PREMIUM='',PROPOSAL_NO = ?  where  QUOTE_NO = ?";
				args[0] = oldage;
				args[1] = startdate;
				args[2] = treatment;
				args[3] = expdate;
				args[4] = claim;
				args[5] = covperiod;
				//args[6] = pid;
				//args[6] =  (String)hasM.get("optPids");
				args[6] = pid;
				args[7] = memoranda;
				//args[8] = schemeId;
				args[8] = pid;
				args[9] = qnum;
			}
			sqlMap.put(QUERY+mapCnt,queryPosition);
			sqlMap.put(ARGS+mapCnt++,args );
			sqlMap.put("Count",Integer.toString(mapCnt));
			runner.multipleUpdateTransaction((HashMap)sqlMap);
			//runner.multipleUpdation(queryPosition, args); // may 17
			LogManager.debug(EXIT);
			LogManager.popRemove();
			return qnum;
	}

	public String getCountryName(String cId)
	{
		LogManager.push("TravelBean getCountryName method()");
		LogManager.debug(ENTER);
		final String result;
		
		final String query="select country_name from country_master where COUNTRY_ID=? and AMEND_ID=(select max(AMEND_ID) from country_master where COUNTRY_ID=? )";
		String args[]=new String[2];
		args[0]=cId;
		args[1]=cId;
		result=runner.singleSelection(query,args);
		
		LogManager.debug(EXIT);
		return result;
	}
	
	public HashMap getDetailsForPremiumCalculation(final String qnum)throws BaseException
	{
		LogManager.push("TravelBean getDetailsForPremiumCalculation method()");
		LogManager.debug(ENTER);
		String[] names = {};
		String[] dob = {};
		String[] rel = {};
		String[] nationality = {};
		String[] genders = {};
		String[] rem = {};
		int[] age = {};
		String memoranda = "";
		String remarks = "";
		String[] args = new String[0];
		String headerQuery = "";
		String[][] headerResult = new String[0][0];
		String[][] detailPremium = new String[1][6];
		String[][] detailPremium1 = new String[1][4];
		String customerid = "";
		final Map hasM = new HashMap();
		int num_persons = 0;

		args = new String[1];
		args[0] = qnum;
		customerid = runner.singleSelection("select CUSTOMER_ID from HOME_POSITION_MASTER where QUOTE_NO = ? ",	args);
		args = new String[2];
		args[0] = qnum;
		args[1] = customerid;
		headerQuery = "select pos.AGE_ABOVE_SIXTY_FIVE,pos.EXISTING_MEDICAL_CONDITION,pos.MEDICAL_TRAVEL_CLAIMS,pos.SUMMARY_CLAUSES,pos.ADMIN_REMARKS,pos.REMARKS,to_char(pos.inception_date,'dd/mm/yyyy'),per.email,per.ADDRESS1,per.ADDRESS2,per.POBOX,per.EMIRATE,per.TELEPHONE,per.MOBILE,per.FAX,per.COMPANY_NAME,per.DOB,per.FIRST_NAME,per.LAST_NAME,per.NATIONALITY,per.TITLE,per.login_id,th.TOTAL_NO_OF_PEOPLE_COVERED," +
				"th.COVERAGE_US_CANADA,th.COVERAGE_WINTERSPORTS,th.COVERAGE_GOLFCOVER,th.POLICY_TERM,th.CLAIM_Y_N,th.CLAIM_REMARKS,th.CLAIM_AMOUNT,to_char(th.EFFECTIVE_DATE,'dd/mm/yyyy'),th.TOTAL_CHILD_PASSENGERS,th.TOTAL_ADULT_PASSENGERS,th.LOGIN_ID,th.QUOTE_NO," +
				"to_char(th.effective_date,'dd/mm/yyyy'),to_char(th.INSURANCE_END_DATE,'dd/mm/yyyy'),th.WORLDWIDE,th.EXCLUDE_USA_CANADA, th.BAGGAGE_EXCLUDED, th.MEDICAL_EXPENSES_EXCLUDED, th.PRODUCTID, th.TERRORISM_EXTENSION,td.PASSENGER_NAME," +
				"to_char(td.DOB,'dd/mm/yyyy'),td.relation,td.illness_remarks,to_char(td.dob,'DD'),to_char(td.dob,'MM'),to_char(td.dob,'YYYY'),td.GENDER,td.NATIONALITY,th.DISCOUNTED_VALUE,th.EXCLUDE_USA_CANADA," +
				"th.WORLDWIDE,th.CHILD_PREMIUM,th.ADULT_PREMUIM,th.GOLF_PREMIUM,th.WINTER_PREMIUM,th.TOTAL_PREMIUM,th.login_id,pos.PREMIUM,pos.EXCESS_PREMIUM,pos.OVERALL_PREMIUM," +
				"pos.EXCESS_SIGN,nvl(pos.admin_remarks,' '),pos.status,pos.airmiles_no,pos.insurance_details,pos.csh_id_typ_code,th.scheme_cover,th.COUNTRY_ORIGIN,th.COUNTRY_DESTN,th.SCHEME_ID,th.OPTION_ID,per.CUSTOMER_SOURCE,per.OCCUPATION from HOME_POSITION_MASTER pos,personal_info per,TRAVEL_HEADER th,TRAVEL_DETAIL td where pos.QUOTE_NO = ? and per.customer_id=pos.customer_id and  th.CUSTOMER_ID = ? and  th.STATUS = 'Y' and th.QUOTE_NO=pos.QUOTE_NO  and td.QUOTE_NO=pos.QUOTE_NO";

		headerResult = runner.multipleSelection(headerQuery, args);
		if (headerResult.length > 0)
		{
			num_persons = Integer.parseInt((headerResult[0][22] != null ? headerResult[0][22]: "0"));
			hasM.put("cnt", Integer.toString(num_persons));
			if (headerResult[0][22] != null){
				hasM.put("num_persons", headerResult[0][22]);
			}
			if (headerResult[0][24] != null){
				hasM.put("winext", headerResult[0][24]);
			}
			if (headerResult[0][25] != null){
				hasM.put("golfext", headerResult[0][25]);
			}
			if (headerResult[0][26] != null){
				hasM.put("covperiod", headerResult[0][26]);
			}
			if (headerResult[0][27] != null){
				hasM.put("claim", headerResult[0][27]);
			}
			if (headerResult[0][28] != null){
				hasM.put(CLAIMDETAILS, headerResult[0][28]);
			}
			else{
				hasM.put(CLAIMDETAILS, "");
			}
			if (headerResult[0][29] != null){
				hasM.put(CLAIMAMOUNT, headerResult[0][29]);
			}
			else{
				hasM.put(CLAIMAMOUNT, "");
			}
			if (headerResult[0][30] != null){
				hasM.put("effdate", headerResult[0][30]);
			}
			if (headerResult[0][31] != null){
				hasM.put("num_childs", headerResult[0][31]);
			}
			if (headerResult[0][32] != null){
				hasM.put("num_adults", headerResult[0][32]);
			}
			if (headerResult[0][33] != null){
				hasM.put("login_id", headerResult[0][33]);
			}
			if (qnum != null){
				hasM.put("qNumber", qnum);
			}
			if (headerResult[0][35] != null){
				hasM.put("efectdate", headerResult[0][35]);
			}
			if (headerResult[0][36] != null){
				hasM.put("expDATE", headerResult[0][36]);
			}
			if (headerResult[0][38] != null){
				hasM.put("usaext", headerResult[0][38]);
			}
			if (headerResult[0][39] != null){
				hasM.put("bagagexl", headerResult[0][39]);
			}
			if (headerResult[0][40] != null){
				hasM.put("medicalexl", headerResult[0][40]);
			}
			if (headerResult[0][41] != null){
				hasM.put("ProductId", headerResult[0][41]);
			}
			if (headerResult[0][42] != null){
				hasM.put("TerrorismExtension", headerResult[0][42]);
			}
			if (headerResult[0][52] != null){
				hasM.put("DISCOUNTED_VALUE", headerResult[0][52]);
			}
			else{
				hasM.put("DISCOUNTED_VALUE", "0");
			}
			if (headerResult[0][53] != null){
				hasM.put("EXCLUDE_USA_CANADA", headerResult[0][53]);
			}
			else{
				hasM.put("EXCLUDE_USA_CANADA", "N");
			}
			if (headerResult[0][54] != null){
				hasM.put("WORLDWIDE", headerResult[0][54]);
			}
			else{
				hasM.put("WORLDWIDE", "N");
			}

			if (headerResult[0][55] != null){
				detailPremium[0][0] = headerResult[0][55];
			}
			else{
				detailPremium[0][0] = ZERO;
			}
			if (headerResult[0][56] != null){
				detailPremium[0][1] = headerResult[0][56];
			}
			else{
				detailPremium[0][1] = ZERO;
			}
			if (headerResult[0][57] != null){
				detailPremium[0][2] = headerResult[0][57];
			}
			else{
				detailPremium[0][2] = ZERO;
			}
			if (headerResult[0][58] != null){
				detailPremium[0][3] = headerResult[0][58];
			}
			else{
				detailPremium[0][3] = ZERO;
			}
			if (headerResult[0][59] != null){
				detailPremium[0][4] = headerResult[0][59];
			}
			else{
				detailPremium[0][4] = ZERO;
			}
			if (headerResult[0][60] != null){
				detailPremium[0][5] = headerResult[0][60];
			}
			else{
				detailPremium[0][5] = ZERO;
			}

			hasM.put("PremiumDetails", detailPremium);

			if (headerResult[0][61] != null){
				detailPremium1[0][0] = headerResult[0][61];
			}
			else{
				detailPremium1[0][0] = "0";
			}
			if (headerResult[0][62] != null){
				detailPremium1[0][1] = headerResult[0][62];
			}
			else{
				detailPremium1[0][1] = "0";
			}
			if (headerResult[0][63] != null){
				detailPremium1[0][2] = headerResult[0][63];
			}
			else{
				detailPremium1[0][2] = "0";
			}
			if (headerResult[0][64] != null){
				detailPremium1[0][3] = headerResult[0][64];
			}
			else{
				detailPremium1[0][3] = "0";
			}

			hasM.put("PremiumDetails1", detailPremium1);

			if (headerResult[0][65] != null	&& !headerResult[0][65].equals(NULL)){
				hasM.put(ADMINCLAUSE, headerResult[0][65]);
			}
			else{
				hasM.put(ADMINCLAUSE, "");
			}
			if (headerResult[0][66] != null){
				hasM.put(POLSTATUS, headerResult[0][66]);
			}
			else{
				hasM.put(POLSTATUS, "Y");
			}
			if (headerResult[0][67] != null){
				hasM.put(MEMBERNUMBER, headerResult[0][67]);
			}
			else{
				hasM.put(MEMBERNUMBER, "");
			}
			if (headerResult[0][68] != null){
				hasM.put(MOTOR, headerResult[0][68]);
			}
			else{
				hasM.put(MOTOR, "");
			}
			if (headerResult[0][69] != null)
				hasM.put("loyalty", headerResult[0][69]);
			else
				hasM.put("loyalty", "0");
			
			if(headerResult[0][70] != null){
				hasM.put("scheme_cover",headerResult[0][70]);
			}
			else{
				hasM.put("scheme_cover","");
			}
			if(headerResult[0][71] != null){
				hasM.put("countryOrigin",headerResult[0][71]);
			}
			else{
				hasM.put("countryOrigin","");
			}
			
			if(headerResult[0][72] != null){
				hasM.put("countryDestn",headerResult[0][72]);
			}
			else{
				hasM.put("countryDestn","");
			}
			if(headerResult[0][73] != null){
				hasM.put("scheme_id",headerResult[0][73]);
			}
			else{
				hasM.put("scheme_id","");
			}
			if(headerResult[0][74] != null){
				hasM.put("option_id",headerResult[0][74]);
			}
			else{
				hasM.put("option_id","");
			}
			if(headerResult[0][75] != null){
				hasM.put("civil_id",headerResult[0][75]);
			}
			else{
				hasM.put("civil_id","");
			}
			if(headerResult[0][76] != null){
				hasM.put("occupation",runner.singleSelection("select OCCUPATION_NAME from HOME_OCCUPATION_MASTER where OCCUPATION_ID='"+headerResult[0][76]+"'"));
			}
			else{
				hasM.put("occupation","");
			}
			
			names = new String[num_persons];
			dob = new String[num_persons];
			rel = new String[num_persons];
			nationality = new String[num_persons];
			genders = new String[num_persons];
			rem = new String[num_persons];
			age = new int[num_persons];

			for (int i = 0; i < num_persons; i++)
			{
				names[i] = headerResult[i][43];
				dob[i] = headerResult[i][44];
				rel[i] = headerResult[i][45];
				genders[i] = headerResult[i][50];
				nationality[i] = headerResult[i][51];
				rem[i] = headerResult[i][46];
				age[i] = calculateAge(headerResult[i][49],headerResult[i][48], headerResult[i][47],runner.getuserHomeBranch(qnum));
			}

			if (names != null){
				hasM.put("names", names);
			}
			if (dob != null) {
				hasM.put("dob", dob);
			}
			if (age != null){
				hasM.put("age", age);
			}
			if (rel != null){
				hasM.put("rel", rel);
			}
			if (genders != null){
				hasM.put("genders", genders);
			}
			if (nationality != null){
				hasM.put("nationalities", nationality);
			}
			if (rem != null){
				hasM.put("rem", rem);
			}
				hasM.put("InceptionDate", (headerResult[0][6] == null ? "": headerResult[0][6]));

				headerResult[0][7] = CommonHelp.notNullCheck(headerResult[0][7]);
				headerResult[0][8] = CommonHelp.notNullCheck(headerResult[0][8]);
				headerResult[0][9] = CommonHelp.notNullCheck(headerResult[0][9]);
				headerResult[0][10] = CommonHelp.notNullCheck(headerResult[0][10]);
				headerResult[0][11] = CommonHelp.notNullCheck(headerResult[0][11]);
				headerResult[0][12] = CommonHelp.notNullCheck(headerResult[0][12]);
				headerResult[0][13] = CommonHelp.notNullCheck(headerResult[0][13]);
				headerResult[0][14] = CommonHelp.notNullCheck(headerResult[0][14]);
				headerResult[0][15] = CommonHelp.notNullCheck(headerResult[0][15]);
				headerResult[0][16] = CommonHelp.notNullCheck(headerResult[0][16]);
				headerResult[0][17] = CommonHelp.notNullCheck(headerResult[0][17]);
				headerResult[0][18] = CommonHelp.notNullCheck(headerResult[0][18]);
				headerResult[0][19] = CommonHelp.notNullCheck(headerResult[0][19]);
				headerResult[0][20] = CommonHelp.notNullCheck(headerResult[0][20]);

				hasM.put("email", headerResult[0][7]);
				hasM.put("address1", headerResult[0][8]);
				hasM.put("address2", headerResult[0][9]);
				hasM.put("pobox", headerResult[0][10]);
				hasM.put("emirate", headerResult[0][11]);
				hasM.put("telephone", headerResult[0][12]);
				hasM.put("mobile", headerResult[0][13]);
				hasM.put("fax", headerResult[0][14]);
				hasM.put("company_name", headerResult[0][15]);
				hasM.put("dobcustomer", headerResult[0][16]);
				hasM.put("first_name", headerResult[0][17]);
				hasM.put("last_name", headerResult[0][18]);
				hasM.put("nationality", headerResult[0][19]);
				hasM.put("title", headerResult[0][20]);
				
				String oldage = "";
				String treatment = "";
				String medical = "";
				String clauses = "";

				oldage = CommonHelp.notNullCheck(headerResult[0][0]);
				treatment = CommonHelp.notNullCheck(headerResult[0][1]);
				medical = CommonHelp.notNullCheck(headerResult[0][2]);
				clauses = CommonHelp.notNullCheck(headerResult[0][3]);
				memoranda = CommonHelp.notNullCheck(headerResult[0][4]);
				remarks = CommonHelp.notNullCheck(headerResult[0][5]);

				if ("N".equalsIgnoreCase(oldage)){
					oldage = "NO";
				}
				else if ("Y".equalsIgnoreCase(oldage)){
					oldage = YES;
				}
				if ("N".equalsIgnoreCase(treatment)){
					treatment = "NO";
				}
				else if ("Y".equalsIgnoreCase(treatment)){
					treatment = YES;
				}
				if ("N".equalsIgnoreCase(medical)){
					medical = "NO";
				}
				else if ("Y".equalsIgnoreCase(medical)){
					medical = YES;
				}

				hasM.put("oldage", oldage);
				hasM.put("treatment", treatment);
				hasM.put("medical", medical);
				hasM.put("clauses", clauses);
				if (memoranda != null){
					hasM.put("memoranda", memoranda);
				}
				if (remarks != null) {
					hasM.put("ReferalChecking", remarks);
				}
			}
		LogManager.debug(EXIT);
		LogManager.popRemove();
		return (HashMap)hasM;
	}
	public HashMap getDetailsForPremiumCalculation(final String qnum,final String customerid,final String loginid)throws BaseException
	{
		LogManager.push("TravelBean getDetailsForPremiumCalculation method()");
		LogManager.debug(ENTER);

		String[] names = {};
		String[] dob = {};
		String[] rel = {};
		String[] nationality = {};
		String[] genders = {};
		String[] rem = {};
		int[] age = {};
		String memoranda = "";
		String remarks = "";
		String headerQuery = "";
		String[][] headerResult = new String[0][0];
		String[][] detailPremium = new String[1][6];
		String[][] detailPremium1 = new String[1][4];

		HashMap hasM = new HashMap();
		int num_persons = 0;

		String[] args = new String[0];
		headerQuery = "select pos.AGE_ABOVE_SIXTY_FIVE,pos.EXISTING_MEDICAL_CONDITION,pos.MEDICAL_TRAVEL_CLAIMS,pos.SUMMARY_CLAUSES,pos.ADMIN_REMARKS,pos.REMARKS,to_char(pos.inception_date,'dd-MON-yyyy'),per.email,per.ADDRESS1,per.ADDRESS2,per.POBOX,per.EMIRATE,per.TELEPHONE,per.MOBILE,per.FAX,per.COMPANY_NAME,to_char(per.DOB),per.FIRST_NAME,per.LAST_NAME,initcap(per.NATIONALITY),per.TITLE,per.login_id,th.TOTAL_NO_OF_PEOPLE_COVERED,th.COVERAGE_US_CANADA,th.COVERAGE_WINTERSPORTS,th.COVERAGE_GOLFCOVER,th.POLICY_TERM,th.CLAIM_Y_N,th.CLAIM_REMARKS,th.CLAIM_AMOUNT,to_char(th.EFFECTIVE_DATE,'dd-MON-yyyy'),th.TOTAL_CHILD_PASSENGERS,th.TOTAL_ADULT_PASSENGERS,th.LOGIN_ID,th.QUOTE_NO,to_char(th.effective_date,'DD-MM-YYYY'),to_char(th.INSURANCE_END_DATE,'dd-MON-yyyy'),th.WORLDWIDE,th.EXCLUDE_USA_CANADA," +
				" th.BAGGAGE_EXCLUDED, th.MEDICAL_EXPENSES_EXCLUDED, th.PRODUCTID, th.TERRORISM_EXTENSION,td.PASSENGER_NAME,to_char(td.DOB,'dd-MON-yyyy'),td.relation,td.illness_remarks,extract(day from td.dob),extract(month from td.dob),extract(year from td.dob),td.GENDER,initcap(td.NATIONALITY),th.DISCOUNTED_VALUE,th.EXCLUDE_USA_CANADA,th.WORLDWIDE,th.CHILD_PREMIUM,th.ADULT_PREMUIM,th.GOLF_PREMIUM," +
				"th.WINTER_PREMIUM,th.TOTAL_PREMIUM,th.login_id,pos.PREMIUM,pos.EXCESS_PREMIUM,pos.OVERALL_PREMIUM,pos.EXCESS_SIGN,nvl(pos.admin_remarks,' ')," +
				"pos.status,pos.airmiles_no,pos.insurance_details,pos.csh_id_typ_code  from HOME_POSITION_MASTER pos,personal_info per,TRAVEL_HEADER th,TRAVEL_DETAIL td where pos.QUOTE_NO = ? and per.customer_id=pos.customer_id and th.QUOTE_NO=pos.QUOTE_NO and th.CUSTOMER_ID = ? and  th.STATUS = 'Y' and th.QUOTE_NO=pos.QUOTE_NO and td.QUOTE_NO=pos.QUOTE_NO";
		args = new String[2];
		args[0] = qnum;
		args[1] = customerid;

		headerResult = runner.multipleSelection(headerQuery, args);

		if (headerResult.length > 0)
		{
			num_persons = Integer.parseInt((headerResult[0][22] != null ? headerResult[0][22]: "0"));
			hasM.put("cnt", Integer.toString(num_persons));
			if (headerResult[0][22] != null){
				hasM.put("num_persons", headerResult[0][22]);
			}
			if (headerResult[0][24] != null){
				hasM.put("winext", headerResult[0][24]);
			}
			if (headerResult[0][25] != null){
				hasM.put("golfext", headerResult[0][25]);
			}
			if (headerResult[0][26] != null){
				hasM.put("covperiod", headerResult[0][26]);
			}
			if (headerResult[0][27] != null){
				hasM.put("claim", headerResult[0][27]);
			}
			if (headerResult[0][28] != null){
				hasM.put(CLAIMDETAILS, headerResult[0][28]);
			}
			else{
				hasM.put(CLAIMDETAILS, "");
			}
			if (headerResult[0][29] != null){
				hasM.put(CLAIMAMOUNT, headerResult[0][29]);
			}
			else{
				hasM.put(CLAIMAMOUNT, "");
			}
			if (headerResult[0][30] != null){
				hasM.put("effdate", headerResult[0][30]);
			}
			if (headerResult[0][31] != null){
				hasM.put("num_childs", headerResult[0][31]);
			}
			if (headerResult[0][32] != null){
				hasM.put("num_adults", headerResult[0][32]);
			}
			if (headerResult[0][33] != null){
				hasM.put("login_id", headerResult[0][33]);
			}
			if (qnum != null){
				hasM.put("qNumber", qnum);
			}
			if (headerResult[0][35] != null){
				hasM.put("efectdate", headerResult[0][35]);
			}
			if (headerResult[0][36] != null){
				hasM.put("expDATE", headerResult[0][36]);
			}
			if (headerResult[0][38] != null){
				hasM.put("usaext", headerResult[0][38]);
			}
			if (headerResult[0][39] != null){
				hasM.put("bagagexl", headerResult[0][39]);
			}
			if (headerResult[0][40] != null){
				hasM.put("medicalexl", headerResult[0][40]);
			}
			if (headerResult[0][41] != null){
				hasM.put("ProductId", headerResult[0][41]);
			}
			if (headerResult[0][42] != null){
				hasM.put("TerrorismExtension", headerResult[0][42]);
			}
			if (headerResult[0][52] != null){
				hasM.put("DISCOUNTED_VALUE", headerResult[0][52]);
			}
			if (headerResult[0][53] != null){
				hasM.put("EXCLUDE_USA_CANADA", headerResult[0][53]);
			}
			if (headerResult[0][54] != null){
				hasM.put("WORLDWIDE", headerResult[0][54]);
			}
			if (headerResult[0][55] != null){
				detailPremium[0][0] = headerResult[0][55];
			}
			else{
				detailPremium[0][0] = ZERO;
			}
			if (headerResult[0][56] != null){
				detailPremium[0][1] = headerResult[0][56];
			}
			else{
				detailPremium[0][1] = ZERO;
			}
			if (headerResult[0][57] != null){
				detailPremium[0][2] = headerResult[0][57];
			}
			else{
				detailPremium[0][2] = ZERO;
			}
			if (headerResult[0][58] != null){
				detailPremium[0][3] = headerResult[0][58];
			}
			else{
				detailPremium[0][3] = ZERO;
			}
			if (headerResult[0][59] != null){
				detailPremium[0][4] = headerResult[0][59];
			}
			else{
				detailPremium[0][4] = ZERO;
			}
			if (headerResult[0][60] != null){
				detailPremium[0][5] = headerResult[0][60];
			}
			else{
				detailPremium[0][5] = ZERO;
			}

			hasM.put("PremiumDetails", detailPremium);

			if (headerResult[0][61] != null) {
				detailPremium1[0][0] = headerResult[0][61];
			} else{
				detailPremium1[0][0] = "0";
			}
			if (headerResult[0][62] != null) {
				detailPremium1[0][1] = headerResult[0][62];
			} else{
				detailPremium1[0][1] = "0";
			}
			if (headerResult[0][63] != null) {
				detailPremium1[0][2] = headerResult[0][63];
			} else{
				detailPremium1[0][2] = "0";
			}
			if (headerResult[0][64] != null) {
				detailPremium1[0][3] = headerResult[0][64];
			} else{
				detailPremium1[0][3] = "0";
			}
			hasM.put("PremiumDetails1", detailPremium1);
			if (headerResult[0][65] != null	&& !headerResult[0][65].equals(NULL)){
				hasM.put(ADMINCLAUSE, headerResult[0][65]);
			}
			else{
				hasM.put(ADMINCLAUSE, "");
			}
			if (headerResult[0][66] != null){
				hasM.put(POLSTATUS, headerResult[0][66]);
			}
			else{
				hasM.put(POLSTATUS, "Y");
			}
			if (headerResult[0][67] != null){
				hasM.put(MEMBERNUMBER, headerResult[0][67]);
			}
			else{
				hasM.put(MEMBERNUMBER, "");
			}
			if (headerResult[0][68] != null){
				hasM.put(MOTOR, headerResult[0][68]);
			}
			else{
				hasM.put(MOTOR, "");
			}
			if (headerResult[0][69] != null){
				hasM.put("loyalty", headerResult[0][69]);
			}else{
				hasM.put("loyalty", "0");
			}
			names = new String[num_persons];
			dob = new String[num_persons];
			rel = new String[num_persons];
			nationality = new String[num_persons];
			genders = new String[num_persons];
			rem = new String[num_persons];
			age = new int[num_persons];

			for (int i = 0; i < num_persons; i++) {
				names[i] = headerResult[i][43];
				dob[i] = headerResult[i][44];
				rel[i] = headerResult[i][45];
				genders[i] = headerResult[i][50];
				nationality[i] = headerResult[i][51];
				rem[i] = headerResult[i][46];
				age[i] = calculateAge(headerResult[i][49],
						headerResult[i][48], headerResult[i][47],runner.getuserBranch(loginid));
			}
			if (names != null){
				hasM.put("names", names);
			}
			if (dob != null) {
				hasM.put("dob", dob);
			}
			if (age != null){
				hasM.put("age", age);
			}
			if (rel != null){
				hasM.put("rel", rel);
			}
			if (genders != null){
				hasM.put("genders", genders);
			}
			if (nationality != null){
				hasM.put("nationalities", nationality);
			}
			if (rem != null){
				hasM.put("rem", rem);
			}
			hasM.put("InceptionDate", CommonHelp.notNullCheck(headerResult[0][6]));
			headerResult[0][7]  = CommonHelp.notNullCheck(headerResult[0][7]);
			headerResult[0][8]  = CommonHelp.notNullCheck(headerResult[0][8]);
			headerResult[0][9]  = CommonHelp.notNullCheck(headerResult[0][9]);
			headerResult[0][10] = CommonHelp.notNullCheck( headerResult[0][10]);
			headerResult[0][11] = CommonHelp.notNullCheck( headerResult[0][11]);
			headerResult[0][12] = CommonHelp.notNullCheck(headerResult[0][12]);
			headerResult[0][13] = CommonHelp.notNullCheck(headerResult[0][13]);
			headerResult[0][14] = CommonHelp.notNullCheck(headerResult[0][14]);
			headerResult[0][15] = CommonHelp.notNullCheck(headerResult[0][15]);
			headerResult[0][16] = CommonHelp.notNullCheck(headerResult[0][16]);
			headerResult[0][17] = CommonHelp.notNullCheck(headerResult[0][17]);
			headerResult[0][18] = CommonHelp.notNullCheck(headerResult[0][18]);
			headerResult[0][19] = CommonHelp.notNullCheck(headerResult[0][19]);
			headerResult[0][20] = CommonHelp.notNullCheck(headerResult[0][20]);
			hasM.put("email", headerResult[0][7]);
			hasM.put("address1", headerResult[0][8]);
			hasM.put("address2", headerResult[0][9]);
			hasM.put("pobox", headerResult[0][10]);
			hasM.put("emirate", headerResult[0][11]);
			hasM.put("telephone", headerResult[0][12]);
			hasM.put("mobile", headerResult[0][13]);
			hasM.put("fax", headerResult[0][14]);
			hasM.put("company_name", headerResult[0][15]);
			hasM.put("dobcustomer", headerResult[0][16]);
			hasM.put("first_name", headerResult[0][17]);
			hasM.put("last_name", headerResult[0][18]);
			hasM.put("nationality", headerResult[0][19]);
			hasM.put("title", headerResult[0][20]);
			// Rajesh Modified on 06/12
			String oldage = "";
			String treatment = "";
			String medical = "";
			String clauses = "";

			oldage = CommonHelp.notNullCheck(headerResult[0][0]);
			treatment = CommonHelp.notNullCheck(headerResult[0][1]);
			medical = CommonHelp.notNullCheck(headerResult[0][2]);
			clauses = CommonHelp.notNullCheck(headerResult[0][3]);
			memoranda = CommonHelp.notNullCheck(headerResult[0][4]);
			remarks = CommonHelp.notNullCheck(headerResult[0][5]);

			if ("N".equalsIgnoreCase(oldage)){
				oldage = "NO";
			}
			else if ("Y".equalsIgnoreCase(oldage)){
				oldage = YES;
			}
			if ("N".equalsIgnoreCase(treatment)){
				treatment = "NO";
			}
			else if ("Y".equalsIgnoreCase(treatment)){
				treatment = YES;
			}
			if ("N".equalsIgnoreCase(medical)){
				medical = "NO";
			}
			else if ("Y".equalsIgnoreCase(medical)){
				medical = YES;
			}

			hasM.put("oldage", oldage);
			hasM.put("treatment", treatment);
			hasM.put("medical", medical);
			hasM.put("clauses", clauses);
			if (memoranda != null){
				hasM.put("memoranda", memoranda);
			}
			if (remarks != null) {
				hasM.put("ReferalChecking", remarks);
			}
		}
		LogManager.debug(EXIT);
		LogManager.popRemove();
		return hasM;
	}
	public int calculateAge(String yyyy, String mmmm, String dddd,String branch)throws BaseException
	{
		LogManager.push("TravelBean calculateAge method()");
		LogManager.debug(ENTER);
		yyyy = CommonHelp.notNullCheckZero(yyyy);
		mmmm = CommonHelp.notNullCheckZero(mmmm);
		dddd = CommonHelp.notNullCheckZero(dddd);
		int age = 0;
		String mon = "";
		int yyear = Integer.parseInt(yyyy);
		int mmon = Integer.parseInt(mmmm);
		int ddat = Integer.parseInt(dddd);
		
		if (yyear == 0 && mmon == 0 && ddat == 0){
			age = 0;
		}else{
			mon = months[mmon] ;
			final String[] args = new String[4];
			final String date = ddat + "/" + mmon + "/" + yyear;
			final String query = "select  trunc( months_between(SYSDATE, to_date(?,'dd/mm/yyyy')) /12 ) Years ,mod( trunc( months_between(SYSDATE, to_date(?,'dd/mm/yyyy') ) ), 12 ) months ,to_date(to_char(SYSDATE,'dd-mon-yyyy'))  - add_months(to_date(?,'dd/mm/yyyy'),trunc( months_between( SYSDATE, to_date(?,'dd/mm/yyyy') ) )) days from dual";
			//final String query="SELECT DATEDIFF(MONTH,CONVERT(DATETIME,?,'dd/mm/yyyy'),sysdate)/12 YEARS,DATEDIFF(MONTH,CONVERT(DATETIME,?,'dd/mm/yyyy'),sysdate)%12 MONTHS,DATEDIFF(DAY,(DATEADD(MONTH,DATEDIFF(MONTH,CONVERT(DATETIME,?,'dd/mm/yyyy'),sysdate),CONVERT(DATETIME,?,'dd/mm/yyyy'))),CONVERT(DATETIME,sysdate,'dd/mm/yyyy')) DAYS";
			args[0] = date;
			args[1] = date;
			args[2] = date;
			args[3] = date;
			final String[][] result = runner.multipleSelection(query, args);
			if (result != null && result.length != 0) {
				result[0][1] = CommonHelp.notNullCheckZero(result[0][1]);
				result[0][2] = CommonHelp.notNullCheckZero(result[0][2]);
				age = Integer.parseInt(result[0][0]);
			}
		}
		LogManager.debug(EXIT);
		LogManager.popRemove();
		return age;
	}
	public String[][] getForQuoteRegister(final String loginid,final String productid)throws BaseException
	{
		LogManager.push("TravelBean getForQuoteRegister method()");
		LogManager.debug(ENTER);
		String temp = "sysdate";
		//temp = runner.getSysdate(runner.getuserBranch(loginid));		
		final String query = "select a.quote_no,b.first_name,to_char(a.effective_date),to_char(a.expiry_date),a.customer_id from HOME_POSITION_MASTER a,personal_info b where a.customer_id=b.customer_id and a.EXPIRY_DATE < (select "+temp+" from dual) and a.product_id = ? and a.status='Y' and  a.login_id in ('"	+ loginid + "')";
		String[] args = {productid};
		final String[][] result = runner.multipleSelection(query, args);
		LogManager.debug(EXIT);
		LogManager.popRemove();
		return result;
	}

	public String[][] getDetailsForPortfolio(final String user)throws BaseException
	{
		LogManager.push("TravelBean getForQuoteRegister method()");
		LogManager.debug(ENTER);
		final String query = "select a.policy_no,a.customer_id,a.premium,to_char(a.inception_date),nvl(b.COMPANY_NAME,b.first_name),b.last_name,a.login_id,a.quote_no,b.COMPANY_NAME from HOME_POSITION_MASTER a,personal_info b where a.login_id in ('"
					+ user
					+ "') and lower(a.status)=lower('P') and b.customer_id=a.customer_id order by a.inception_date desc";
		final String[][] result = runner.multipleSelection(query);
		LogManager.debug(EXIT);
		LogManager.popRemove();
		return result;
	}

	public String updateIndividualPremium(final String childsPremium,final String adultsPremium,final String winterSports,final  String golfCover,String total,final String quoteNo,final String pid,final String loginId) throws BaseException
	{
		LogManager.push("TravelBean updateIndividualPremium method()");
		LogManager.debug(ENTER);
		final Map sqlMap = new HashMap();
		int mapCnt = 0;
		int stValue = 0;
		double commis = 0.00;
		double totcom = 0.00;
		String[] args = new String[0];
		final String minAmount = getMinimumPremium(quoteNo);
		double amt = Double.parseDouble((minAmount == null || "".equals(minAmount) || minAmount.equals(NULL)) ? ZERO: minAmount);
		double totalPre = Double.parseDouble(total);

		if (totalPre < amt) {
			total = Double.toString(amt);
		}

		commis = Double.parseDouble(getCommisions(loginId, pid));

		if (commis != 0){
			totcom = Double.parseDouble(total) * (commis / 100);
		}
		
		System.out.println("Total Premium => "+total);
		
		args = new String[6];
		final String sql = "update TRAVEL_HEADER set CHILD_PREMIUM = ?,ADULT_PREMUIM = ?,GOLF_PREMIUM= ?,WINTER_PREMIUM = ? ,TOTAL_PREMIUM= ? where QUOTE_NO = ?";
		args[0] = childsPremium;
		args[1] = adultsPremium;
		args[2] = golfCover;
		args[3] = winterSports;
		args[4] = total;
		args[5] = quoteNo;
		sqlMap.put(QUERY+mapCnt,sql);
		sqlMap.put(ARGS+mapCnt++,args );
		// runner.multipleUpdation(sql, args); // May 17

		args = new String[4];
		args[0] = total;
		args[1] = total;
		args[2] = Double.toString(totcom);
		args[3] = quoteNo;
		final String sql1 = "update HOME_POSITION_MASTER set PREMIUM = ? ,OVERALL_PREMIUM= ? ,COMMISSION = ? where QUOTE_NO  = ?";
		sqlMap.put(QUERY+mapCnt,sql1);
		sqlMap.put(ARGS+mapCnt++,args );
		sqlMap.put("Count",Integer.toString(mapCnt));
		runner.multipleUpdateTransaction((HashMap)sqlMap);
		//runner.multipleUpdation(sql1, args); //may 17
		LogManager.debug(EXIT);
		LogManager.popRemove();
		return Integer.toString(stValue);
	}

	public String[][] getPremiumDetails(final String QNO)throws BaseException
	{
		LogManager.push("TravelBean getPremiumDetails method()");
		LogManager.debug(ENTER);
		final String[] args ={QNO};
		final String query = "select CHILD_PREMIUM,ADULT_PREMUIM,GOLF_PREMIUM,WINTER_PREMIUM,TOTAL_PREMIUM,login_id  from  TRAVEL_HEADER  where QUOTE_NO = ?";
		final String[][] result = runner.multipleSelection(query, args);
		LogManager.debug(EXIT);
		LogManager.popRemove();
		return result;
	}

	public String[][] getBrokerLimits(final String lid, String pid)throws BaseException
	{
		LogManager.push("TravelBean getPremiumDetails method()");
		LogManager.debug(ENTER);
		if ("35".equals(pid) || "36".equals(pid) || "37".equals(pid)) {
			pid = "22";
		} else if ("50".equals(pid) || "51".equals(pid) || "52".equals(pid)) {
			pid = "31";
		}
		final String query = "select agency_code,oa_code,commission,insurance_end_limit,special_discount,min_premium_amount from login_user_details where agency_code=(select agency_Code from login_master where login_id = ? ) and product_id = ?";
		final String[]args={lid,pid};
		final String[][] result = runner.multipleSelection(query, args);
		LogManager.debug(EXIT);
		LogManager.popRemove();
		return result;
	}
	public String[][] getViewQuotesNotApproved(final String loginIds,final String pid)throws BaseException
	{
		LogManager.push("TravelBean getViewQuotesNotApproved method()");
		LogManager.debug(ENTER);

		String sql = "select login_id from login_master where oa_code=(select agency_code from login_master where login_id = ? ) or created_by=(select agency_code from login_master where login_id = ? ) or login_id = ? and login_id not in('NONE','NON')";
		final String args[] ={loginIds,loginIds,loginIds};
		final String[][] valuess = runner.multipleSelection(sql, args);

		String loginAllIds = homeValid.getStringFromArray(valuess);

		final String last = loginAllIds.substring(loginAllIds.length() - 1,loginAllIds.length());
		if (",".equals(last)) {
			loginAllIds = loginAllIds.substring(0, loginAllIds.length() - 1);
		}
		sql = "select a.quote_no,nvl(b.COMPANY_NAME,b.first_name),to_char(a.effective_date),to_char(a.expiry_date),a.customer_id from HOME_POSITION_MASTER a,personal_info b where a.login_id in ("
				+ loginAllIds
				+ ") and  a.status='Y'  and b.customer_id=a.customer_id order by quote_no DESC";
		final String[][] details = runner.multipleSelection(sql);
		LogManager.debug(EXIT);
		LogManager.popRemove();
		return details;
	}

	public String[][] getBrokerExPremium(final String LoginId) throws BaseException
	{
		LogManager.push("TravelBean getBrokerExPremium method()");
		LogManager.debug(ENTER);
		final String[] args = new String[1];
		final String sql = "select agency_code from login_master where agency_code=(select oa_code from login_master where login_id = ?) ";
		args[0] = LoginId;
		final String agencyCode = runner.singleSelection(sql, args);
		final String sql1 = "select discrete_id,data_name,data_value from single_dimension_discrete where rating_factor_sub_id='16'  and discrete_id in (select  max(discrete_id) from single_dimension_discrete  where status='Y' and rating_factor_sub_id='16' and data_name = ?  group by relative_discrete_id)";
		args[0] = agencyCode.toUpperCase(Locale.ENGLISH);
		final String[][] commodity = runner.multipleSelection(sql1, args);
		LogManager.debug(EXIT);
		LogManager.popRemove();
		return commodity;
	}

	public void UpdateAdditionalCommision(final String commission, final String quoteNo)throws BaseException
	{
		LogManager.push("TravelBean UpdateAdditionalCommision method()");
		LogManager.debug(ENTER);
		final String args[] = {commission,quoteNo};
		runner.multipleUpdation("update HOME_POSITION_MASTER set BROKER_ADDITIONAL_COMMISSION = ? where QUOTE_NO = ?", args);
		LogManager.debug(EXIT);
		LogManager.popRemove();
	}

	public String[][] getLapsedDetails(final String quotenumber) throws BaseException
	{
		LogManager.push("TravelBean UpdateAdditionalCommision method()");
		LogManager.debug(ENTER);
		String qry;qry = "select  a.customer_id,to_char(a.entry_date,'dd')as days,to_char(a.entry_date,'MM')as months,to_char(a.entry_date,'YYYY')as years,to_char(a.entry_date+30,'dd')as days,to_char(a.entry_date+30,'MM')as months,to_char(a.entry_date+30,'YYYY')as years" +
		",b.first_name,b.last_name,b.company_name from HOME_POSITION_MASTER a, personal_info b WHERE a.quote_no = ? and a.customer_id = b.customer_id";
		final String args[] ={quotenumber};
		String[][] viewQuotes;viewQuotes = runner.multipleSelection(qry, args);
		LogManager.debug(EXIT);
		LogManager.popRemove();
		return viewQuotes;
	}
	public String[][] getLapsedStatus(final String loginBra)throws BaseException
	{
		LogManager.push("TravelBean getLapsedStatus method()");
		LogManager.debug(ENTER);
		final String sql = "select category_detail_id,detail_name from constant_detail where category_id = (select category_id from constant_master where category_name = 'LAPSED_QUOTE_REASON' and BRANCH_CODE = ?) and BRANCH_CODE = ?";
		final String args[] ={loginBra,loginBra};
		final String [][]LapsedStatus = runner.multipleSelection(sql, args);
		LogManager.debug(EXIT);
		LogManager.popRemove();
		return LapsedStatus;
	}
	public String deleteTable(final String quoteNo,final String LapsedRemarks)throws BaseException
	{
		LogManager.push("TravelBean deleteTable method()");
		LogManager.debug(ENTER);
		String temp = "sysdate";
//		temp = runner.getSysdate(runner.getuserHomeBranch(quoteNo));		
		final String[] args = new String[2];
		final String qry = "update HOME_POSITION_MASTER set status = 'D', LAPSED_DATE = (select "+temp+" from dual),LAPSED_REMARKS= ? where quote_no = ? ";
		args[0] = LapsedRemarks;
		args[1] = quoteNo;
		runner.multipleUpdation(qry, args);
		LogManager.debug(EXIT);
		LogManager.popRemove();
		return quoteNo;
	}

	// Start Modified By Rajesh For Travel PDF//
	public String[][] travelPersonalInfoSelect(final String quoteNo) throws BaseException
	{
		LogManager.push("TravelBean TRAVEL_PERSONAL_INFO_SELECT method()");
		LogManager.debug(ENTER);
		final String sql = "select title,nvl(FIRST_NAME,COMPANY_NAME),last_name,to_char(dob,'dd/mm/yyyy'),gender,telephone,mobile,fax,email,address1,address2,occupation,pobox,country from PERSONAL_INFO where customer_id in(select distinct(customer_id) from HOME_POSITION_MASTER where quote_no = ?)";
		final String args[] ={quoteNo};
		final String[][] result = runner.multipleSelection(sql, args);
		LogManager.debug(EXIT);
		LogManager.popRemove();
		return result;
	}
	public String[][] travelInsuredPersonsInfoSelect(final String quoteNo,final String branchCode) throws BaseException
	{
		LogManager.push("TravelBean travelInsuredPersonsInfoSelect method()");
		LogManager.debug(ENTER);
		final String sql = "SELECT TD.PASSENGER_NAME, to_char(TD.DOB,'dd/mm/yyyy'), CD.DETAIL_NAME , TD.GENDER, TD.NATIONALITY,td.age,nvl(td.PREMIUM,0) FROM TRAVEL_DETAIL TD,CONSTANT_DETAIL CD WHERE TD.QUOTE_NO = ? AND CD.CATEGORY_DETAIL_ID=TD.RELATION AND CD.CATEGORY_ID='53'   AND CD.BRANCH_CODE=? AND TD.AMEND_ID=(SELECT MAX(AMEND_ID) FROM TRAVEL_DETAIL WHERE QUOTE_NO =TD.QUOTE_NO AND STATUS = 'Y') ORDER BY TD.SERIAL_NO";
		final String[] args ={quoteNo,branchCode};
		final String [][]result = runner.multipleSelection(sql, args);
		LogManager.debug(EXIT);
		LogManager.popRemove();
		return result;
	}

	public String[][] TRAVEL_DETAILS_INFO_SELECT(final String quoteNo)throws BaseException
	{
		LogManager.push("TravelBean BrokerCompanyMasterSelect method()");
		LogManager.debug(ENTER);
		final String sql = "select TOTAL_ADULT_PASSENGERS,TOTAL_CHILD_PASSENGERS,TOTAL_NO_OF_PEOPLE_COVERED,COVERAGE_US_CANADA,COVERAGE_WINTERSPORTS, COVERAGE_GOLFCOVER, ADDITIONAL_COVER, POLICY_TERM, POLICY_TRAVEL_TRIPS, CLAIM_Y_N, CLAIM_AMOUNT, ADULT_PREMUIM, CHILD_PREMIUM, WINTER_PREMIUM, GOLF_PREMIUM, ADDITIONAL_PREMIUM, TOTAL_PREMIUM, to_char(INSURANCE_START_DATE,'DD-MM-YYYY'), to_char(INSURANCE_END_DATE,'DD-MM-YYYY'), PREMIUM_LOAD_DIS_PERCENT, PREMIUM_LOAD_DIS_DESC, PREMIUM_LOAD_DIS_AMOUNT, NET_PREMIUM,MEDICAL_EXPENSES_EXCLUDED,BAGGAGE_EXCLUDED,DISCOUNTED_VALUE from TRAVEL_HEADER where quote_no = ?";
		final String[] args = {quoteNo};
		final String[][] result = runner.multipleSelection(sql, args);
		LogManager.debug(EXIT);
		LogManager.popRemove();
		return result;
	}

	public String[][] brokerCompanyMasterSelect(final String loginId)throws BaseException
	{
		LogManager.push("TravelBean BrokerCompanyMasterSelect method()");
		LogManager.debug(ENTER);
		final String sql = "select company_name,ADDRESS1,ADDRESS2,ADDRESS3,CITY,COUNTRY,POBOX,CONTACT_PERSON,PHONE,BRANCH_CODE from BROKER_COMPANY_MASTER where AGENCY_CODE in(select oa_code from LOGIN_MASTER where login_id = ?)";
		final String[] args = {loginId};
		final String[][] result = runner.multipleSelection(sql, args);
		LogManager.debug(EXIT);
		LogManager.popRemove();
		return result;
	}
	public int updateBrokerStatus(final String quoteNo) throws BaseException
	{
		LogManager.push("TravelBean updateBrokerStatus method()");
		LogManager.debug(ENTER);
		int status = 0;
		String[] args = new String[1];
		final String sql = "select max(Pdf_Broker_Status) from HOME_POSITION_MASTER where QUOTE_NO = ?";
		args[0] = quoteNo;
		final String res = runner.singleSelection(sql, args);
		if (res != null) {
			if (res.length() > 0){
				status = Integer.parseInt(res);
			}
			else{
				status = 0;
			}
		} else{
			status = 0;
		}
		int newStatus = status + 1;
		args = new String[2];
		final String sql1 = "update HOME_POSITION_MASTER set Pdf_Broker_Status  = ? where QUOTE_NO = ?";
		args[0] = Integer.toString(newStatus);
		args[1] = quoteNo;
		runner.multipleUpdation(sql1, args);
		LogManager.debug(EXIT);
		LogManager.popRemove();
		return status;
	}
	public String getCommision(final String loginId,final String pid)throws BaseException
	{
		LogManager.push("TravelBean getCommision method()");
		LogManager.debug(ENTER);
		final String sql = "select nvl(commission,0) from login_user_details where agency_code=(select oa_code from login_master where login_id = ? and status='Y') and status='Y' and product_id = ?";
		final String[] args = {loginId,pid};
		final String commision = runner.singleSelection(sql, args);
		LogManager.debug(EXIT);
		LogManager.popRemove();
		return commision;
	}

	public void generateDebitNoteNo(final String quoteno)throws BaseException
	{
		LogManager.push("TravelBean generateDebitNoteNo method()");
		LogManager.debug(ENTER);
		String temp = "sysdate";
//		temp = runner.getSysdate(runner.getuserHomeBranch(quoteno));		
		com.maan.common.Customer.DataSelect dataSelect = new com.maan.common.Customer.DataSelect();
		String debit_note_no = "";
		String[] args = new String[1];
		args[1] = quoteno;
		debit_note_no = runner.singleSelection("select debit_note_no from HOME_POSITION_MASTER where quote_no = ? and debit_note_no is not null",args);
		if ("".equalsIgnoreCase(debit_note_no)) {
			debit_note_no = dataSelect.getMaxDebitNo(quoteno, "020");
			args = new String[2];
			args[0] = debit_note_no;
			args[1] = quoteno;
			runner.multipleUpdation("update HOME_POSITION_MASTER set debit_note_no = ?,debit_note_date=(select "+temp+" from dual) where quote_no = ?",args);
		}
		LogManager.debug(EXIT);
		LogManager.popRemove();
	}
	public void generatePolicyNo(final String quoteno,final String pid) throws BaseException
	{
		LogManager.push("TravelBean generatePolicyNo method()");
		LogManager.debug(ENTER);
		String temp = "sysdate";
	//	temp = runner.getSysdate(runner.getuserHomeBranch(quoteno));		
		com.maan.common.Customer.DataSelect dataSelect = new com.maan.common.Customer.DataSelect();
		String[] args = new String[1];
		args[0] = quoteno;
		String PolicyNo = "";
		PolicyNo = runner.singleSelection("select policy_no from HOME_POSITION_MASTER where quote_no = ? and policy_no is not null");
		if ("".equalsIgnoreCase(PolicyNo) || PolicyNo.equalsIgnoreCase(NULL)) {
			final String remarks = dataSelect.policyGeneration(quoteno, pid, "020");
			args = new String[2];
			args[0] = remarks;
			args[1] = quoteno;
			runner.multipleUpdation("update HOME_POSITION_MASTER set policy_no = ? ,status='P',INCEPTION_DATE=(select "+temp+" from dual) where quote_no = ?",args);
		}
		LogManager.debug(EXIT);
		LogManager.popRemove();
	}
	public String[][] getTrashQuote(final String loginIds,final String allPids)throws BaseException
	{
		LogManager.push("TravelBean getTrashQuote method()");
		LogManager.debug(ENTER);
		String[] args = {loginIds};
		String sql = "select login_id from login_master where agency_code=(select agency_code from login_master where login_id= ?) and login_id not in('NONE','NON')";
		final String[][] valuess = runner.multipleSelection(sql, args);

		String loginAllIds = homeValid.getStringFromArray(valuess);

		loginAllIds = loginAllIds.substring(0, loginAllIds.lastIndexOf(','));

		sql = "select a.quote_no,a.customer_id,to_char(a.effective_date,'dd/mm/yyyy'),to_char(a.EXPIRY_DATE,'dd/mm/yyyy'),b.first_name,b.last_name,a.login_id,a.OVERALL_PREMIUM,a.status,a.application_no,b.COMPANY_NAME,a.LAPSED_REMARKS from HOME_POSITION_MASTER a,personal_info b where  a.login_id in ("
				+ loginAllIds
				+ ") and a.product_id in ("
				+ allPids
				+ ") and (a.status in ('D')) and b.customer_id=a.customer_id order by a.quote_no DESC";
		final String details[][] = runner.multipleSelection(sql);
		LogManager.debug(EXIT);
		LogManager.popRemove();
		return details;
	}

	public String getLapsedStatusRemarks(final String Quote_No) throws BaseException
	{
		LogManager.push("Travel Bean getLapsedStatusRemarks method()");
		LogManager.debug(ENTER);
		final String[] args = {Quote_No};
		final String lapsedRemaks = runner.singleSelection("select LAPSED_REMARKS from HOME_POSITION_MASTER where Quote_No = ?", args);
		LogManager.debug(EXIT);
		LogManager.popRemove();
		return lapsedRemaks;
	}

	public String[][] getLogIds(final String loginIds)throws BaseException
	{
		LogManager.push("Travel Bean getLogIds method()");
		LogManager.debug(ENTER);
		final String[] args = new String[3];
		args[0] = loginIds;
		args[1] = loginIds;
		args[2] = loginIds;
		final String sql = "select login_id from login_master where (oa_code=(select agency_code from login_master where login_id = ? ) or created_by=(select agency_code from login_master where login_id = ?) or login_id = ?) and login_id not in('NONE','NON')";
		final String[][] valuess = runner.multipleSelection(sql, args);
		LogManager.debug(EXIT);
		LogManager.popRemove();
		return valuess;
	}
	public String[][] getTotalPolicy123(final String loginIds,final String searchOption,String searchValue,final String fullTravelIds,final String status)throws BaseException
	{
		LogManager.push("Travel Bean getTotalPolicy123 method()");
		LogManager.debug(ENTER);
		String sql = "select login_id from login_master where agency_code=(select agency_code from login_master where login_id = ?) and login_id not in('NONE','NON')";
		final String args[] = {loginIds};
		final String[][] valuess = runner.multipleSelection(sql, args);

		String loginAllIds = homeValid.getStringFromArray(valuess);

		loginAllIds = loginAllIds.substring(0, loginAllIds.lastIndexOf(','));

		if (SELECT.equalsIgnoreCase(searchOption)
				|| ("").equalsIgnoreCase(searchOption)) {

			sql = "select a.policy_no,a.customer_id,a.OVERALL_PREMIUM,to_char(a.inception_date,'dd')as days,to_char(a.inception_date,'MM')as months,to_char(a.inception_date,'YYYY')as years,nvl(b.COMPANY_NAME,b.first_name),b.last_name,a.login_id,a.quote_no,b.COMPANY_NAME,a.inception_date,to_char(a.cancelled_date,'dd-mm-yyyy'),a.cancelled_by,a.cancelled_reason,a.reissued_quote_no from HOME_POSITION_MASTER a,personal_info b where a.login_id in ("
					+ loginAllIds
					+ ") and a.status='"
					+ status
					+ "' and b.customer_id=a.customer_id and a.product_id in ("
					+ fullTravelIds + ") order by a.inception_date desc";
		} else if ("FIRST_NAME".equalsIgnoreCase(searchOption)) {

			sql = "select a.policy_no,a.customer_id,a.OVERALL_PREMIUM,to_char(a.inception_date,'dd')as days,to_char(a.inception_date,'MM')as months,to_char(a.inception_date,'YYYY')as years,nvl(b.COMPANY_NAME,b.first_name),b.last_name,a.login_id,a.quote_no,b.COMPANY_NAME,a.inception_date,to_char(a.cancelled_date,'dd-mm-yyyy'),a.cancelled_by,a.cancelled_reason,a.reissued_quote_no from HOME_POSITION_MASTER a,personal_info b where a.login_id in ("
					+ loginAllIds
					+ ") and a.status='"
					+ status
					+ "' and b.customer_id=a.customer_id and(lower(trim(b.company_name)) like '%"
					+ (searchValue.trim()).toLowerCase()
					+ "%' or lower(trim(b.first_name)) like '%"
					+ (searchValue.trim()).toLowerCase()
					+ "%') and a.product_id in ("
					+ fullTravelIds
					+ ") order by a.inception_date desc";
		} else if ("quote_nos".equalsIgnoreCase(searchOption)) {

			sql = "select a.policy_no,a.customer_id,a.OVERALL_PREMIUM,to_char(a.inception_date,'dd')as days,to_char(a.inception_date,'MM')as months,to_char(a.inception_date,'YYYY')as years,nvl(b.COMPANY_NAME,b.first_name),b.last_name,a.login_id,a.quote_no,b.COMPANY_NAME,a.inception_date,to_char(a.cancelled_date,'dd-mm-yyyy'),a.cancelled_by,a.cancelled_reason,a.reissued_quote_no from HOME_POSITION_MASTER a,personal_info b where a.login_id in ("
					+ loginAllIds
					+ ") and a.status='"
					+ status
					+ "' and b.customer_id=a.customer_id and (a.quote_no like'%"
					+ searchValue
					+ "%') and a.product_id in ("
					+ fullTravelIds + ") order by a.inception_date desc";
		} else if ("policy_nos".equalsIgnoreCase(searchOption)) {

			sql = "select a.policy_no,a.customer_id,a.OVERALL_PREMIUM,to_char(a.inception_date,'dd')as days,to_char(a.inception_date,'MM')as months,to_char(a.inception_date,'YYYY')as years,nvl(b.COMPANY_NAME,b.first_name),b.last_name,a.login_id,a.quote_no,b.COMPANY_NAME,a.inception_date,to_char(a.cancelled_date,'dd-mm-yyyy'),a.cancelled_by,a.cancelled_reason,a.reissued_quote_no from HOME_POSITION_MASTER a,personal_info b where a.login_id in ("
					+ loginAllIds
					+ ") and a.status='"
					+ status
					+ "' and b.customer_id=a.customer_id and (a.policy_no like '%"
					+ searchValue
					+ "%') and a.product_id in ("
					+ fullTravelIds + ") order by a.inception_date desc";
		} else if ("DateWise".equalsIgnoreCase(searchOption)) {
			com.maan.Home.DataManipualtion.HomeQuoteRegisterBean quoteBean;
			quoteBean = new com.maan.Home.DataManipualtion.HomeQuoteRegisterBean();
			searchValue = quoteBean.getDateSearchValue(searchValue.trim(),searchOption);
			sql = "select a.policy_no,a.customer_id,a.OVERALL_PREMIUM,to_char(a.inception_date,'dd')as days,to_char(a.inception_date,'MM')as months,to_char(a.inception_date,'YYYY')as years,nvl(b.COMPANY_NAME,b.first_name),b.last_name,a.login_id,a.quote_no,b.COMPANY_NAME,a.inception_date,to_char(a.cancelled_date,'dd-mm-yyyy'),a.cancelled_by,a.cancelled_reason,a.reissued_quote_no from HOME_POSITION_MASTER a,personal_info b where a.login_id in ("
					+ loginAllIds
					+ ") and a.status='"
					+ status
					+ "' and b.customer_id=a.customer_id and to_char(a.inception_date,'dd/MM/YYYY')='"
					+ searchValue
					+ "' and a.product_id in ("
					+ fullTravelIds + ") order by a.inception_date desc";
		}
		final String details[][] = runner.multipleSelection(sql);
		LogManager.debug(EXIT);
		LogManager.popRemove();
		return details;
	}

	public String[][] getTotalPolicy1234(final String loginIds,final String searchOption,String searchValue,final String fullTravelIds,final String status) throws BaseException
	{
		LogManager.push("Travel Bean getTotalPolicy1234 method()");
		LogManager.debug(ENTER);
		String sql = "select login_id from login_master where agency_code=(select agency_code from login_master where login_id= ?) and login_id not in('NONE','NON')";
		final String args[] ={loginIds};
		final String[][] valuess = runner.multipleSelection(sql, args);

		String loginAllIds = homeValid.getStringFromArray(valuess);

		loginAllIds = loginAllIds.substring(0, loginAllIds.lastIndexOf(','));

		if (SELECT.equalsIgnoreCase(searchOption)
				|| ("").equalsIgnoreCase(searchOption)) {
			sql = "select a.policy_no,a.customer_id,a.OVERALL_PREMIUM,to_char(a.inception_date,'dd')as days,to_char(a.inception_date,'MM')as months,to_char(a.inception_date,'YYYY')as years,nvl(b.COMPANY_NAME,b.first_name),b.last_name,a.login_id,a.quote_no,b.COMPANY_NAME,a.inception_date,to_char(a.cancelled_date,'dd-mm-yyyy'),a.cancelled_by,a.cancelled_reason,a.reissued_quote_no from HOME_POSITION_MASTER a,personal_info b where a.login_id in ("
					+ loginAllIds
					+ ") and a.status in('C','I') and b.customer_id=a.customer_id and a.product_id in ("
					+ fullTravelIds + ") order by a.inception_date desc";
		} else if ("FIRST_NAME".equalsIgnoreCase(searchOption)) {
			sql = "select a.policy_no,a.customer_id,a.OVERALL_PREMIUM,to_char(a.inception_date,'dd')as days,to_char(a.inception_date,'MM')as months,to_char(a.inception_date,'YYYY')as years,nvl(b.COMPANY_NAME,b.first_name),b.last_name,a.login_id,a.quote_no,b.COMPANY_NAME,a.inception_date,to_char(a.cancelled_date,'dd-mm-yyyy'),a.cancelled_by,a.cancelled_reason,a.reissued_quote_no from HOME_POSITION_MASTER a,personal_info b where a.login_id in ("
					+ loginAllIds
					+ ") and a.status in('C','I') and b.customer_id=a.customer_id and(lower(trim(b.company_name)) like '%"
					+ (searchValue.trim()).toLowerCase()
					+ "%' or lower(trim(b.first_name)) like '%"
					+ (searchValue.trim()).toLowerCase()
					+ "%') and a.product_id in ("
					+ fullTravelIds
					+ ") order by a.inception_date desc";
		} else if ("quote_nos".equalsIgnoreCase(searchOption)) {
			sql = "select a.policy_no,a.customer_id,a.OVERALL_PREMIUM,to_char(a.inception_date,'dd')as days,to_char(a.inception_date,'MM')as months,to_char(a.inception_date,'YYYY')as years,nvl(b.COMPANY_NAME,b.first_name),b.last_name,a.login_id,a.quote_no,b.COMPANY_NAME,a.inception_date,to_char(a.cancelled_date,'dd-mm-yyyy'),a.cancelled_by,a.cancelled_reason,a.reissued_quote_no from HOME_POSITION_MASTER a,personal_info b where a.login_id in ("
					+ loginAllIds
					+ ") and a.status in('C','I') and b.customer_id=a.customer_id and (a.quote_no like'%"
					+ searchValue
					+ "%') and a.product_id in ("
					+ fullTravelIds + ") order by a.inception_date desc";
		} else if ("policy_nos".equalsIgnoreCase(searchOption)) {

			sql = "select a.policy_no,a.customer_id,a.OVERALL_PREMIUM,to_char(a.inception_date,'dd')as days,to_char(a.inception_date,'MM')as months,to_char(a.inception_date,'YYYY')as years,nvl(b.COMPANY_NAME,b.first_name),b.last_name,a.login_id,a.quote_no,b.COMPANY_NAME,a.inception_date,to_char(a.cancelled_date,'dd-mm-yyyy'),a.cancelled_by,a.cancelled_reason,a.reissued_quote_no from HOME_POSITION_MASTER a,personal_info b where a.login_id in ("
					+ loginAllIds
					+ ") and a.status in('C','I') and b.customer_id=a.customer_id and (a.policy_no like '%"
					+ searchValue
					+ "%') and a.product_id in ("
					+ fullTravelIds + ") order by a.inception_date desc";
		} else if ("DateWise".equalsIgnoreCase(searchOption)) {
			com.maan.Home.DataManipualtion.HomeQuoteRegisterBean quoteBean;
			quoteBean = new com.maan.Home.DataManipualtion.HomeQuoteRegisterBean();
			searchValue = quoteBean.getDateSearchValue(searchValue.trim(),searchOption);

			sql = "select a.policy_no,a.customer_id,a.OVERALL_PREMIUM,to_char(a.inception_date,'dd')as days,to_char(a.inception_date,'MM')as months,to_char(a.inception_date,'YYYY')as years,nvl(b.COMPANY_NAME,b.first_name),b.last_name,a.login_id,a.quote_no,b.COMPANY_NAME,a.inception_date,to_char(a.cancelled_date,'dd-mm-yyyy'),a.cancelled_by,a.cancelled_reason,a.reissued_quote_no from HOME_POSITION_MASTER a,personal_info b where a.login_id in ("
					+ loginAllIds
					+ ") and a.status in('C','I') and b.customer_id=a.customer_id and to_char(a.inception_date,'dd/MM/YYYY')='"
					+ searchValue
					+ "' and a.product_id in ("
					+ fullTravelIds + ") order by a.inception_date desc";
		}
		final String details[][] = runner.multipleSelection(sql);
		LogManager.debug(EXIT);
		LogManager.popRemove();
		return details;
	}
	public String[][] getViewQuotesNotApproved(final String loginIds,final String searchOption,String searchValue,
			final String fullTravelIds)throws BaseException
	{
		LogManager.push("Travel Bean getViewQuotesNotApprovedExDate method()");
		LogManager.debug(ENTER);
		String temp = "sysdate";
		//temp = runner.getSysdate(runner.getuserBranch(loginIds));		
		searchValue = searchValue.trim();
		String sql;sql = "select login_id from login_master where agency_code=(select agency_code from login_master where login_id = ?) and login_id not in('NONE','NON')";
		final String args[] = {loginIds};
		String[][] valuess;valuess = runner.multipleSelection(sql, args);

		String loginAllIds;loginAllIds = homeValid.getStringFromArray(valuess);
		loginAllIds = loginAllIds.substring(0, loginAllIds.lastIndexOf(','));

		if (SELECT.equalsIgnoreCase(searchOption)|| ("").equalsIgnoreCase(searchOption)) {

			sql = "select a.quote_no,a.customer_id,to_char(a.entry_date,'dd')as days,to_char(a.entry_date,'MM')as months,to_char(a.entry_date,'YYYY')as years," +
					"to_char(a.entry_date+30,'dd') as days,to_char(a.entry_date+30,'MM') as months,to_char(a.entry_date+30,'YYYY') as years,nvl(b.COMPANY_NAME,b.first_name),b.last_name,a.login_id,b.COMPANY_NAME,a.effective_date from HOME_POSITION_MASTER a,personal_info b " +
					"where a.login_id in ("	+ loginAllIds+ ") and  a.entry_date + 30  >=  (select "+temp+" from dual) and a.status='Y' and a.remarks is null and a.ADMIN_REFERRAL_STATUS is null and b.customer_id=a.customer_id and a.product_id in ("
					+ fullTravelIds + ") and a.payment_status is null order by quote_no DESC";
		} else if ("FIRST_NAME".equalsIgnoreCase(searchOption)) {

			sql = "select a.quote_no,a.customer_id,to_char(a.entry_date,'dd')as days,to_char(a.entry_date,'MM')as months,to_char(a.entry_date,'YYYY')as years," +
					"to_char(a.entry_date+30,'dd') as days,to_char(a.entry_date+30,'MM') as months,to_char(a.entry_date+30,'YYYY') as years,nvl(b.COMPANY_NAME,b.first_name),b.last_name,a.login_id,b.COMPANY_NAME,a.effective_date from HOME_POSITION_MASTER a,personal_info b where a.login_id in ("
					+ loginAllIds
					+ ") and a.entry_date + 30 >= (select "+temp+" from dual) and a.status='Y' and a.remarks is null and a.ADMIN_REFERRAL_STATUS is null and a.remarks is null and a.ADMIN_REFERRAL_STATUS is null and  b.customer_id=a.customer_id and (lower(trim(b.company_name)) like '%"
					+ searchValue.trim().toLowerCase()
					+ "%' or lower(trim(b.first_name)) like '%"
					+ searchValue.trim().toLowerCase()
					+ "%') and a.product_id in ("
					+ fullTravelIds
					+ ") and a.payment_status is null order by quote_no DESC";
		} else if ("quote_nos".equalsIgnoreCase(searchOption)) {

			sql = "select a.quote_no,a.customer_id,to_char(a.entry_date,'dd')as days,to_char(a.entry_date,'MM')as months,to_char(a.entry_date,'YYYY')as years," +
					"to_char(a.entry_date+30,'dd') as days,to_char(a.entry_date+30,'MM') as months,to_char(a.entry_date+30,'YYYY') as years,nvl(b.COMPANY_NAME,b.first_name),b.last_name,a.login_id,b.COMPANY_NAME,a.effective_date from HOME_POSITION_MASTER a,personal_info b where a.login_id in ("
					+ loginAllIds
					+ ") and a.entry_date + 30 >= (select "+temp+" from dual) and a.status='Y' and a.remarks is null and a.ADMIN_REFERRAL_STATUS is null and  b.customer_id=a.customer_id and (a.quote_no like'%"
					+ searchValue
					+ "%')   and a.product_id in ("
					+ fullTravelIds + ") and a.payment_status is null order by quote_no DESC";
		} else if ("DateWise".equalsIgnoreCase(searchOption)) {
			com.maan.Home.DataManipualtion.HomeQuoteRegisterBean quoteBean;
			quoteBean = new com.maan.Home.DataManipualtion.HomeQuoteRegisterBean();
			searchValue = quoteBean.getDateSearchValue(searchValue.trim(),searchOption);

			sql = "select a.quote_no,a.customer_id,to_char(a.entry_date,'dd')as days,to_char(a.entry_date,'MM')as months,to_char(a.entry_date,'YYYY')as years," +
					"to_char(a.entry_date+30,'dd') as days,to_char(a.entry_date+30,'MM') as months,to_char(a.entry_date+30,'YYYY') as years,nvl(b.COMPANY_NAME,b.first_name),b.last_name,a.login_id,b.COMPANY_NAME,a.effective_date from HOME_POSITION_MASTER a,personal_info b where a.login_id in ("
					+ loginAllIds
					+ ") and a.product_id in ("
					+ fullTravelIds
					+ ")  and a.entry_date + 30 >= (select "+temp+" from dual) and a.status='Y' and a.remarks is null and a.ADMIN_REFERRAL_STATUS is null and b.customer_id=a.customer_id and to_char(a.entry_date,'dd/MM/YYYY')='"
					+ searchValue + "' and a.payment_status is null order by quote_no DESC";
		}
		String details[][];details = runner.multipleSelection(sql);
		LogManager.debug(EXIT);
		LogManager.popRemove();
		return details;
	}
	public String[][] getViewQuotesNotApprovedExDate(final String loginIds)throws BaseException
	{
		LogManager.push("Travel Bean getViewQuotesNotApprovedExDate method()");
		LogManager.debug(ENTER);
		String temp = "sysdate";
		//temp = runner.getSysdate(runner.getuserBranch(loginIds));		
		final String[] args = new String[3];
		args[0] = loginIds;
		args[1] = loginIds;
		args[2] = loginIds;
		String sql = "select login_id from login_master where oa_code=(select agency_code from login_master where login_id  = ? ) or created_by=(select agency_code from login_master where login_id = ? ) or login_id = ?";
		final String[][] valuess = runner.multipleSelection(sql, args);

		String loginAllIds = homeValid.getStringFromArray(valuess);

		loginAllIds = loginAllIds.substring(0, loginAllIds.lastIndexOf(','));
		sql = "select a.quote_no,a.customer_id,to_char(a.entry_date,'dd')as days,to_char(a.entry_date,'MM')as months,to_char(a.entry_date,'YYYY')as years,to_char(a.EXPIRY_DATE,'dd')as days,to_char(a.EXPIRY_DATE,'MM')as months,to_char(a.EXPIRY_DATE,'YYYY')as years,b.first_name from HOME_POSITION_MASTER a, personal_info b WHERE a.login_id in ("
					+ loginAllIds
					+ ") and a.expiry_date < (select "+temp+" from dual) and a.customer_id = b.customer_id and a.status='Y'";

		final String details[][] = runner.multipleSelection(sql);
		LogManager.debug(EXIT);
		LogManager.popRemove();
		return details;
	}

	public String[][] getNewLogIds(final String loginIds) throws BaseException
	{
		LogManager.push("Travel Bean getNewLogIds method()");
		LogManager.debug(ENTER);
		final String[] args = {loginIds};
		final String sql = "select login_id from login_master where oa_code=(select oa_code from login_master where login_id = ? and usertype!='User')";
		final String [][]valuess = runner.multipleSelection(sql, args);
		LogManager.debug(EXIT);
		LogManager.popRemove();
		return valuess;
	}

	public String checkDate(final String strDate)throws BaseException
	{
		LogManager.push("Travel Bean checkDate method()");
		LogManager.debug(ENTER);
		final StringBuffer dateCheck = new StringBuffer();
		final SimpleDateFormat simpleDate = new SimpleDateFormat("dd/MM/yyyy",Locale.ENGLISH);
		simpleDate.setLenient(false);
		final ParsePosition pos = new ParsePosition(0);
		Date date = simpleDate.parse(strDate, pos);
		if ((date == null) || (pos.getErrorIndex() != -1)) {
			LogManager.info("Error: " + pos.getIndex());
			if (date == null) {
				LogManager.info("Date is null");
				dateCheck.append(INVALID);
			}
			else if (pos.getErrorIndex() != -1) {
				LogManager.info("Error index: " + pos.getErrorIndex());
				dateCheck.append(INVALID);
			}
			else{
				dateCheck.append(INVALID);
				}
		}
		LogManager.debug(EXIT);
		LogManager.popRemove();
		return dateCheck.toString();
	}
	public String[][] getViewQuotesNotApprovedExDate(final String loginIds,final String productId) throws BaseException
	{
		String temp = "sysdate";
	//	temp = runner.getSysdate(runner.getuserBranch(loginIds));		
		String[] args = new String[3];
		String sql = "select login_id from login_master where oa_code=(select agency_code from login_master where login_id = ? ) or created_by=(select agency_code from login_master where login_id = ?) or login_id = ?";
		args[0] = loginIds;
		args[1] = loginIds;
		args[2] = loginIds;
		final String[][] valuess = runner.multipleSelection(sql, args);
		String loginAllIds = homeValid.getStringFromArray(valuess);
		loginAllIds = loginAllIds.substring(0, loginAllIds.lastIndexOf(','));
		sql = "select a.quote_no,a.customer_id,to_char(a.entry_date,'dd')as days,to_char(a.entry_date,'MM')as months,to_char(a.entry_date,'YYYY')as years,to_char(a.expiry_date,'dd')as days,to_char(a.expiry_date,'MM')as months,to_char(a.expiry_date,'YYYY')as years,nvl(b.COMPANY_NAME,b.first_name) from HOME_POSITION_MASTER a, personal_info b WHERE a.login_id in ("
				+ loginAllIds
				+ ") and a.effective_date + 30  < (select "+temp+" from dual) and a.customer_id = b.customer_id and a.product_id in ("
				+ productId
				+ ") and a.status='Y' and a.remarks is null and a.ADMIN_REFERRAL_STATUS is null";
		//final String details[][] = runner.multipleSelection(sql);
		return runner.multipleSelection(sql);
	}
	public String insertOrUpdate(final String quotenum,final HashMap hasM,final String totalPers, final String pid,final String travelPid,final String loginBra)throws BaseException
	{
		LogManager.push("TravelBean insertOrUpdate method()");
		LogManager.debug(ENTER);
		String result;
		isReferralFlag = false;
		if (quotenum != null && !quotenum.equals(NULL)&& quotenum.length() > 0)
		{
			result = updateTravellersDetails(quotenum, hasM, totalPers, pid,loginBra);
		} else {
			result = insertForTravellers(hasM, pid, travelPid, loginBra);
		}
		LogManager.debug(EXIT);
		LogManager.popRemove();
		return result;
	}
	public String[][] travelHeaderPremium(final String quoteNo)throws BaseException
	{
		LogManager.push("TravelBean travelHeaderPremium method()");
		LogManager.debug(ENTER);
		final String sql = "select TOTAL_ADULT_PASSENGERS,TOTAL_CHILD_PASSENGERS,TOTAL_NO_OF_PEOPLE_COVERED, "
				+ " COVERAGE_US_CANADA,COVERAGE_WINTERSPORTS, COVERAGE_GOLFCOVER, ADDITIONAL_COVER, "
				+ " POLICY_TERM, POLICY_TRAVEL_TRIPS, CLAIM_Y_N, CLAIM_AMOUNT, ADULT_PREMUIM, "
				+ " CHILD_PREMIUM, WINTER_PREMIUM, GOLF_PREMIUM, ADDITIONAL_PREMIUM, TOTAL_PREMIUM, "
				+ " to_char(INSURANCE_START_DATE,'DD-MM-YYYY'), to_char(INSURANCE_END_DATE,'DD-MM-YYYY'), "
				+ " PREMIUM_LOAD_DIS_PERCENT, PREMIUM_LOAD_DIS_DESC, PREMIUM_LOAD_DIS_AMOUNT, NET_PREMIUM ,"
				+ " BAGGAGE_EXCLUDED,CHILD_BETWEEN_3_TO_17,CUSTOMER_ID,EXCLUDE_USA_CANADA,MEDICAL_EXPENSES_EXCLUDED, "
				+ " PRODUCTID,WORLDWIDE,spouse_discount,family_discount,TERRORISM_EXTENSION,scheme_cover "
				+ " from TRAVEL_HEADER where quote_no = ?";
		final String args[] ={quoteNo};
		final String[][] result = runner.multipleSelection(sql, args);
		LogManager.debug(EXIT);
		LogManager.popRemove();
		return result;
	}
	public String[][] getProductNames() throws BaseException
	{
		LogManager.push("TravelBean getProductNames method()");
		LogManager.debug(ENTER);
		final String[][] result = runner.multipleSelection("select product_id,product_name from product_master");
		LogManager.debug(EXIT);
		LogManager.popRemove();
		return result;
	}
	public String[][] getBranchNames()throws BaseException
	{
		LogManager.push("TravelBean getBranchNames method()");
		LogManager.debug(ENTER);
		final String [][]result = runner.multipleSelection("SELECT branch_code,branch_name FROM branch_master");
		LogManager.debug(EXIT);
		LogManager.popRemove();
		return result;
	}

	public String[][] getDescription() throws BaseException
	{
		LogManager.push("TravelBean getDescription method()");
		LogManager.debug(ENTER);
			final String result[][] = runner.multipleSelection("select distinct description from policyno_generate");
		LogManager.debug(EXIT);
		LogManager.popRemove();
		return result;
	}
	public String deleteRow(final String serialnumber,final String quotenumber)throws BaseException
	{
		LogManager.push("TravelBean deleteRow method()");
		LogManager.debug(ENTER);
		String[][] arr1;
		String[][] arr2;
		String query1;
		String query2;
		String query3;
		String query4;
		String[] args;
		int age = 0;
			args = new String[2];
			args[0] = serialnumber;
			args[1] = quotenumber;
			query1 = "select extract(year from dob),extract(month from dob),extract(day from dob) from travel_detail where serial_no = ? and quote_no = ?";
			arr1 = runner.multipleSelection(query1, args);
			if (arr1 != null && arr1.length > 0) {
				age = calculateAge(arr1[0][0], arr1[0][1], arr1[0][2], runner.getuserHomeBranch(quotenumber));
			}

			args = new String[1];
			args[0] = quotenumber;
			query2 = "select TOTAL_ADULT_PASSENGERS,TOTAL_CHILD_PASSENGERS,TOTAL_NO_OF_PEOPLE_COVERED,child_between_3_to_17 from Travel_header where quote_no = ?";
			arr2 = runner.multipleSelection(query2, args);

			if (arr2 != null) {
				if (!arr2[0][0].equals("0") || !arr2[0][1].equals("0") ||!arr2[0][3].equals("0")) {
					if (age >= 16) {
						arr2[0][0] = Integer.toString((Integer.parseInt(arr2[0][0]) - 1));
					} else if (age>3 && age < 16) {
						arr2[0][3] = Integer.toString((Integer.parseInt(arr2[0][3]) - 1));
					}else if (age <= 3){
						arr2[0][1] = Integer.toString((Integer.parseInt(arr2[0][1]) - 1));
					}
					int tot = Integer.parseInt(arr2[0][0])
							+ Integer.parseInt(arr2[0][1]) + Integer.parseInt(arr2[0][3]);
					arr2[0][2] = Integer.toString(tot);
				} else {
					arr2[0][2] = Integer.toString((Integer.parseInt(arr2[0][2]) - 1));
				}
			}
			
			
			args = new String[2];
			args[0] = serialnumber;
			args[1] = quotenumber;
			query3 = "Delete from travel_detail where serial_no = ? and quote_no = ?";
			runner.multipleUpdation(query3, args);

			args = new String[5];
			query4 = "update travel_header set TOTAL_ADULT_PASSENGERS = ?"
					+ " , TOTAL_CHILD_PASSENGERS = ?"
					+ " , TOTAL_NO_OF_PEOPLE_COVERED = ?,child_between_3_to_17=?"
					+ "  where quote_no = ?";
			args[0] = arr2[0][0];
			args[1] = arr2[0][1];
			args[2] = arr2[0][2];
			args[3] = arr2[0][3];
			args[4] = quotenumber;
			runner.multipleUpdation(query4, args);
			LogManager.debug(EXIT);
			LogManager.popRemove();
		return "success";
	}
	public String[][] getTodaysDate(String branch)throws BaseException
	{
		LogManager.push("TravelBean getTodaysDate method()");
		LogManager.debug(ENTER);
		String temp = "sysdate";
		//temp = runner.getSysdate(branch);		
		final String query = "select extract(year from "+temp+") ,extract(month from "+temp+"),extract(day from "+temp+") from dual";
		final String[][] result = runner.multipleSelection(query);
		LogManager.debug(EXIT);
		LogManager.popRemove();
		return result;
	}
	public String getMinimumPremium(final String qno)throws BaseException
	{
		LogManager.push("TravelBean getMinimumPremium method()");
		LogManager.debug(ENTER);
		final String[] args = {qno,qno};
		final String sql = "select nvl(MIN_PREMIUM_AMOUNT,'0.0') from LOGIN_USER_DETAILS where agency_CODE in(select OA_CODE from LOGIN_MASTER where login_id = (select login_id from HOME_POSITION_MASTER where quote_no = ?)) and product_id in (select product_id from HOME_POSITION_MASTER where quote_no = ?)";
		final String min_amount = runner.singleSelection(sql, args);
		LogManager.debug(EXIT);
		LogManager.popRemove();
		return min_amount;
	}
	public String[][] getUserBrokerInformationForQuoteRegister(final String login,final String pid,final String pids)throws BaseException
	{
		LogManager.push("TravelBean getUserBrokerInformationForQuoteRegister method()");
		LogManager.debug(ENTER);
		String[][] UserBroker = new String[0][0];
		final String userType = getUserTypeInformation(login);
		if (userType != null && "Broker".equalsIgnoreCase(userType))
		{

				final String[] args = {pid,login};
				final String sql = "select login_id from personal_info where login_id in(select distinct login_id from HOME_POSITION_MASTER where QUOTE_NO is not null and POLICY_NO is null and lower(status) in('y') and REMARKS is null and PRODUCT_ID in("
						+ pids
						+ ") and login_id in(select login_id from login_master where agency_code in(select agency_code from login_user_details where product_id=? and agency_code in (select agency_code from login_master where oa_code=(select oa_code from login_master where login_id = ? ))))) and login_id!='NONE' and application_id in ('2','3')";
				UserBroker = runner.multipleSelection(sql, args);

		}
		LogManager.debug(EXIT);
		LogManager.popRemove();
		return UserBroker;
	}
	public String getUserTypeInformation(final String login)throws BaseException
	{
		LogManager.push("TravelBean getUserTypeInformation method()");
		LogManager.debug(ENTER);
		final String[] args = {login};
		final String userType = runner.singleSelection("select USERTYPE from login_master where login_id = ?", args);
		LogManager.debug(EXIT);
		LogManager.popRemove();
		return userType;
	}
	public String[][] getUserBrokerInformationforPorfolio(final String login,final String pid,final String pids) throws BaseException
	{
		LogManager.push("TravelBean getUserBrokerInformationforPorfolio method()");
		LogManager.debug(ENTER);
		String[][] UserBroker = new String[0][0];
		final String userType = getUserTypeInformation(login);
		if (userType != null && "Broker".equalsIgnoreCase(userType))
		{

				final String args[] = {pid,login};
				final String sql = "select login_id from personal_info where login_id in(select distinct login_id from HOME_POSITION_MASTER where QUOTE_NO is not null and POLICY_NO is not null and lower(status) in('p') and PRODUCT_ID in("
						+ pids
						+ ") and login_id in(select login_id from login_master where agency_code in(select agency_code from login_user_details where product_id = ? and agency_code in (select agency_code from login_master where oa_code=(select oa_code from login_master where login_id = ? ))))) and login_id!='NONE' and application_id in ('2','3')";
				UserBroker = runner.multipleSelection(sql, args);

		}
		LogManager.debug(EXIT);
		LogManager.popRemove();
		return UserBroker;
	}
	public String[][] getUserBrokerInformationforLapsed(final String login,final String pid,final String pids)throws BaseException
	{
		LogManager.push("TravelBean getUserBrokerInformationforLapsed method()");
		LogManager.debug(ENTER);
		String[][] UserBroker = new String[0][0];
		final String userType = getUserTypeInformation(login);
		if(userType != null && "Broker".equalsIgnoreCase(userType))
		{

				final String[] args = {pid,login};
				final String sql = "select login_id from personal_info where login_id in(select distinct login_id from HOME_POSITION_MASTER where QUOTE_NO is not null and lower(status) in('d') and PRODUCT_ID in("
						+ pids
						+ ") and login_id in(select login_id from login_master where agency_code in(select agency_code from login_user_details where product_id = ? and agency_code in (select agency_code from login_master where oa_code=(select oa_code from login_master where login_id = ?))))) and login_id!='NONE' and application_id in ('2','3')";
				UserBroker = runner.multipleSelection(sql, args);

		}
		LogManager.debug(EXIT);
		LogManager.popRemove();
		return UserBroker;
	}
	public String getReferralDescription(String pid,final  String screferral,final String treatment,final String claim,final boolean above75age,final int above10person,final String loginBra,final int nofDays) throws BaseException
	{
		LogManager.push("TravelBean getReferralDescription method()");
		LogManager.debug(ENTER);
		String[] args;
		String query;
		StringBuffer desc = new StringBuffer(1000);
		pid = "50";
		args = new String[3];
		args[1] = loginBra;
		args[2] = pid;
		query = "select DETAIL_NAME from constant_detail where CATEGORY_DETAIL_ID = ? and BRANCH_CODE = ? and status='Y' and  CATEGORY_ID = ?";
		if ("Y".equalsIgnoreCase(screferral)) {
		   args[0] = "1";
		}if ("Y".equalsIgnoreCase(treatment)) {
		  args[0] = "2";
		}
		if ("Y".equalsIgnoreCase(claim)) {
		  args[0] = "3";
		}
		if (above75age) {
		  args[0] = "5";
		}
		if (above10person > 10) {
		  args[0] = "4";
		}
		if(nofDays>365){
			args[0] = "6";
		}
		
		
		
		System.out.println("nofDays=> "+nofDays);
		if(query.length()>0){
			final String temp = runner.singleSelection(query, args);
			if (temp.length() > 0){
				desc.append("*");
				desc.append(temp);
			}else
			{
				if(args[0]==null || "".equalsIgnoreCase(args[0]))
				{
					desc.append("Cleared");
				}
			}
			
		}
		LogManager.debug(EXIT);
		LogManager.popRemove();
		return desc.toString();
	}
	public String[][] checkReferral(final String quoteno)throws BaseException
	{
		String result[][]=new String[1][1];
		try{
		LogManager.push("TravelBean getCheckReferral method()");
		LogManager.debug(ENTER);
		
		final String query = "select nvl(REFERRAL_DESCRIPTION,'') from HOME_POSITION_MASTER where remarks is not null and quote_no = ? ";
		final String[] args = {quoteno};
		result = runner.multipleSelection(query, args);
		
		if(result.length<1)
		{
			result=new String[1][1];
			result[0][0]="";
		}
			
		LogManager.debug(EXIT);
		LogManager.popRemove();
		}catch(Exception e){System.out.println("Exception === "+e);}
		System.out.println("Result Length: "+result.length);
		return result;
	}
	public String getCheckReferral(final String quoteno)throws BaseException
	{
		LogManager.push("TravelBean getCheckReferral method()");
		LogManager.debug(ENTER);
		final String query = "select nvl(REFERRAL_DESCRIPTION,' ') from HOME_POSITION_MASTER where remarks is not null and quote_no = ? ";
		final String[] args = {quoteno};
		final String result = runner.singleSelection(query, args);
		LogManager.debug(EXIT);
		LogManager.popRemove();
		return result;
	}
	public String getcutomerName(final String quote)throws BaseException
	{
		LogManager.push("TravelBean getcutomerName method()");
		LogManager.debug(ENTER);
		final String[] args = {quote};
		final String qry = "select nvl(first_name,company_name),last_name from personal_info where customer_id=(select customer_id from HOME_POSITION_MASTER where quote_no = ?)";
		final String[][] returnVal = runner.multipleSelection(qry, args);
		final String names = CommonHelp.notNullCheck(returnVal[0][0])+" "+CommonHelp.notNullCheck(returnVal[0][1]);
		LogManager.debug(EXIT);
		LogManager.popRemove();
		return names;
	}
	public String[][] getViewQuotes123(final String loginIds,final String option,final String productId) throws BaseException
	{
		LogManager.push("TravelBean getViewQuotes123 method()");
		LogManager.debug(ENTER);
		String loginids = "";
		String remarks = "";
		String fullTravelIds = "";
		fullTravelIds = getFullTravelIds();

		if ("app".equalsIgnoreCase(option)){
			remarks = "Admin";
		}
		else if ("unapp".equalsIgnoreCase(option)){
			remarks = "Referal";
		}
		final String args[] ={loginIds};
		String sql = "select login_id from login_master where agency_code=(select agency_code from login_master where login_id = ?) and login_id not in('NONE','NON')";
		final String[][] valuess = runner.multipleSelection(sql, args);
		for (int i = 0; i < valuess.length; i++) {
			loginids = "'" + valuess[i][0] + "'," + loginids;
		}
		loginids = loginids.substring(0, loginids.lastIndexOf(','));
		if (!"rej".equalsIgnoreCase(option))
		{
			sql = "select a.quote_no,a.customer_id,to_char(a.entry_date,'dd')as days,to_char(a.entry_date,'MM')as months,to_char(a.entry_date,'YYYY')as years,to_char(a.effective_date,'dd')as days,to_char(a.effective_date,'MM')as months,to_char(a.effective_date,'YYYY')as years,initcap(b.first_name),initcap(b.LAST_NAME),a.login_id,b.COMPANY_NAME from HOME_POSITION_MASTER a,personal_info b where a.login_id in ("
					+ loginids
					+ ") and a.status='Y' and b.customer_id=a.customer_id and (a.REMARKS in ('"
					+ remarks
					+ "')) and a.product_id in ("
					+ fullTravelIds
					+ ") order by a.quote_no desc";
		} else if ("rej".equalsIgnoreCase(option)) {
			sql = "select a.quote_no,a.customer_id,to_char(a.entry_date,'dd')as days,to_char(a.entry_date,'MM')as months,to_char(a.entry_date,'YYYY')as years,to_char(a.effective_date,'dd')as days,to_char(a.effective_date,'MM')as months,to_char(a.effective_date,'YYYY')as years,initcap(b.first_name),initcap(b.LAST_NAME),a.login_id,b.COMPANY_NAME from HOME_POSITION_MASTER a,personal_info b where a.login_id in ("
					+ loginids
					+ ") and a.status='R' and b.customer_id=a.customer_id and a.ADMIN_REFERRAL_STATUS='N' and a.product_id in ("
					+ fullTravelIds + ") order by a.quote_no desc";
		}
		final String details[][] = runner.multipleSelection(sql);
		LogManager.debug(EXIT);
		LogManager.popRemove();
		return details;
	}
	public String[][] getUserBrokerInformationforReferral(final String login,final String pid,final String pids) throws BaseException
	{
		LogManager.push("TravelBean getUserBrokerInformationforReferral method()");
		LogManager.debug(ENTER);
		String[][] UserBroker = new String[0][0];
		String[] args = new String[0];
		final String userType = getUserTypeInformation(login);
		if (userType != null && "Broker".equalsIgnoreCase(userType)) {
			args = new String[2];
			args[0] = pid;
			args[1] = login;
			final String sql = "select login_id from personal_info where login_id in(select distinct login_id from HOME_POSITION_MASTER where REMARKS is not null and lower(status) in('y') and PRODUCT_ID in("
						+ pids+ ") and login_id in(select login_id from login_master where agency_code in(select agency_code from login_user_details where product_id = ? and agency_code in (select agency_code from login_master where oa_code=(select oa_code from login_master where login_id= ?))))) and login_id!='NONE' and application_id in ('2','3')";
			UserBroker = runner.multipleSelection(sql, args);
		}
		LogManager.debug(EXIT);
		LogManager.popRemove();
		return UserBroker;
	}
	public String[][] getReferralQuotesSearch(final String loginIds,final String searchOption,String searchValue,final String productId,final String option)throws BaseException
	{
		LogManager.push("TravelBean getReferralQuotesSearch method()");
		LogManager.debug(ENTER);
		String sql = "";
		String loginids = "";
		String remarks = "";
		if ("app".equalsIgnoreCase(option)){
			remarks = "Admin";
		}
		else if ("unapp".equalsIgnoreCase(option)){
			remarks = "Referal";
		}

		final String args[] = {loginIds};
		sql = "select login_id from login_master where agency_code=(select agency_code from login_master where login_id = ? ) and login_id not in('NONE','NON')";
		final String[][] valuess = runner.multipleSelection(sql, args);
		for (int i = 0; i < valuess.length; i++) {
			loginids = "'" + valuess[i][0] + "'," + loginids;
		}
		loginids = loginids.substring(0, loginids.lastIndexOf(','));
		if (SELECT.equalsIgnoreCase(searchOption) || ("").equalsIgnoreCase(searchOption))
		{
				if (!"rej".equalsIgnoreCase(option))
				{
					sql = "select a.quote_no,a.customer_id,to_char(a.entry_date,'dd')as days,to_char(a.entry_date,'MM')as months,to_char(a.entry_date,'YYYY')as years,to_char(a.effective_date,'dd')as days,to_char(a.effective_date,'MM')as months,to_char(a.effective_date,'YYYY')as years,initcap(b.first_name),initcap(b.LAST_NAME),a.login_id,b.COMPANY_NAME from HOME_POSITION_MASTER a,personal_info b where a.login_id in ("
							+ loginids
							+ ") and a.status='Y' and b.customer_id=a.customer_id and (a.REMARKS in ('"
							+ remarks
							+ "')) and a.product_id='"
							+ productId
							+ "' order by a.quote_no desc";
				}
				else if ("rej".equalsIgnoreCase(option)) {
					sql = "select a.quote_no,a.customer_id,to_char(a.entry_date,'dd')as days,to_char(a.entry_date,'MM')as months,to_char(a.entry_date,'YYYY')as years,to_char(a.effective_date,'dd')as days,to_char(a.effective_date,'MM')as months,to_char(a.effective_date,'YYYY')as years,initcap(b.first_name),initcap(b.LAST_NAME),a.login_id,b.COMPANY_NAME from HOME_POSITION_MASTER a,personal_info b where a.login_id in ("
							+ loginids
							+ ") and a.status='R' and b.customer_id=a.customer_id and a.ADMIN_REFERRAL_STATUS='N' and a.product_id='"
							+ productId + "' order by a.quote_no desc";
				}
			} else if ("FIRST_NAME".equalsIgnoreCase(searchOption)) {
				if (!"rej".equalsIgnoreCase(option)) {
					sql = "select a.quote_no,a.customer_id,to_char(a.entry_date,'dd')as days,to_char(a.entry_date,'MM')as months,to_char(a.entry_date,'YYYY')as years,to_char(a.effective_date,'dd')as days,to_char(a.effective_date,'MM')as months,to_char(a.effective_date,'YYYY')as years,initcap(b.first_name),initcap(b.LAST_NAME),a.login_id,b.COMPANY_NAME from HOME_POSITION_MASTER a,personal_info b where a.login_id in ("
							+ loginids
							+ ") and a.status='Y' and b.customer_id=a.customer_id and (a.REMARKS in ('"
							+ remarks
							+ "')) and (lower(trim(b.company_name)) like '%"
							+ searchValue.trim().toLowerCase()
							+ "%' or lower(trim(b.first_name)) like '%"
							+ searchValue.trim().toLowerCase()
							+ "%') and a.product_id='"
							+ productId
							+ "' order by a.quote_no desc";
				} else if ("rej".equalsIgnoreCase(option)) {
					sql = "select a.quote_no,a.customer_id,to_char(a.entry_date,'dd')as days,to_char(a.entry_date,'MM')as months,to_char(a.entry_date,'YYYY')as years,to_char(a.effective_date,'dd')as days,to_char(a.effective_date,'MM')as months,to_char(a.effective_date,'YYYY')as years,initcap(b.first_name),initcap(b.LAST_NAME),a.login_id,b.COMPANY_NAME from HOME_POSITION_MASTER a,personal_info b where a.login_id in ("
							+ loginids
							+ ") and a.status='R' and b.customer_id=a.customer_id and a.ADMIN_REFERRAL_STATUS='N' and (lower(trim(b.company_name)) like '%"
							+ searchValue.trim().toLowerCase()
							+ "%' or lower(trim(b.first_name)) like '%"
							+ searchValue.trim().toLowerCase()
							+ "%') and a.product_id='"
							+ productId
							+ "' order by a.quote_no desc";
				}

			} else if ("quote_nos".equalsIgnoreCase(searchOption)) {
				if (!option.equalsIgnoreCase("rej")) {
					sql = "select a.quote_no,a.customer_id,to_char(a.entry_date,'dd')as days,to_char(a.entry_date,'MM')as months,to_char(a.entry_date,'YYYY')as years,to_char(a.effective_date,'dd')as days,to_char(a.effective_date,'MM')as months,to_char(a.effective_date,'YYYY')as years,initcap(b.first_name),initcap(b.LAST_NAME),a.login_id,b.COMPANY_NAME from HOME_POSITION_MASTER a,personal_info b where a.login_id in ("
							+ loginids
							+ ") and a.status='Y' and b.customer_id=a.customer_id and (a.REMARKS in ('"
							+ remarks
							+ "')) and (a.quote_no like '%"
							+ searchValue
							+ "%') and a.product_id='"
							+ productId + "' order by a.quote_no desc";
				} else if ("rej".equalsIgnoreCase(option)) {
					sql = "select a.quote_no,a.customer_id,to_char(a.entry_date,'dd')as days,to_char(a.entry_date,'MM')as months,to_char(a.entry_date,'YYYY')as years,to_char(a.effective_date,'dd')as days,to_char(a.effective_date,'MM')as months,to_char(a.effective_date,'YYYY')as years,initcap(b.first_name),initcap(b.LAST_NAME),a.login_id,b.COMPANY_NAME from HOME_POSITION_MASTER a,personal_info b where a.login_id in ("
							+ loginids
							+ ") and a.status='R' and b.customer_id=a.customer_id and a.ADMIN_REFERRAL_STATUS='N' and (a.quote_no like '%"
							+ searchValue
							+ "%') and a.product_id='"
							+ productId + "' order by a.quote_no desc";
				}

			} else if ("DateWise".equalsIgnoreCase(searchOption)) {
				com.maan.Home.DataManipualtion.HomeQuoteRegisterBean quoteBean;
				quoteBean = new com.maan.Home.DataManipualtion.HomeQuoteRegisterBean();
				searchValue = quoteBean.getDateSearchValue(searchValue.trim(),searchOption);
				if ("rej".equalsIgnoreCase(option)) {
					sql = "select a.quote_no,a.customer_id,to_char(a.entry_date,'dd')as days,to_char(a.entry_date,'MM')as months,to_char(a.entry_date,'YYYY')as years,to_char(a.effective_date,'dd')as days,to_char(a.effective_date,'MM')as months,to_char(a.effective_date,'YYYY')as years,initcap(b.first_name),initcap(b.LAST_NAME),a.login_id,b.COMPANY_NAME from HOME_POSITION_MASTER a,personal_info b where a.login_id in ("
							+ loginids
							+ ") and a.status='R' and b.customer_id=a.customer_id and a.ADMIN_REFERRAL_STATUS='N' and to_char(a.entry_date,'dd/MM/YYYY')='"
							+ searchValue
							+ "'  and a.product_id='"
							+ productId
							+ "' order by a.quote_no desc";
				}
				else {
					sql = "select a.quote_no,a.customer_id,to_char(a.entry_date,'dd')as days,to_char(a.entry_date,'MM')as months,to_char(a.entry_date,'YYYY')as years,to_char(a.effective_date,'dd')as days,to_char(a.effective_date,'MM')as months,to_char(a.effective_date,'YYYY')as years,initcap(b.first_name),initcap(b.LAST_NAME),a.login_id,b.COMPANY_NAME from HOME_POSITION_MASTER a,personal_info b where a.login_id in ("
							+ loginids
							+ ") and a.status='Y' and b.customer_id=a.customer_id and (a.REMARKS in ('"
							+ remarks
							+ "')) and to_char(a.entry_date,'dd/MM/YYYY')='"
							+ searchValue
							+ "' and a.product_id='"
							+ productId
							+ "' order by a.quote_no desc";
				}
			}
		final String details[][] = runner.multipleSelection(sql);

		LogManager.debug(EXIT);
		LogManager.popRemove();
		return details;
	}
	public String[][] getAdminRefStatussandSign(final String qno)throws BaseException
	{
		LogManager.push("TravelBean getAdminRefStatussandSign method()");
		LogManager.debug(ENTER);
		final String[] args = {qno};
		final String sql = "select ADMIN_REMARKS,EXCESS_SIGN,EXCESS_PREMIUM,ADMIN_REFERRAL_STATUS from HOME_POSITION_MASTER where QUOTE_NO = ?";
		final String[][] details = runner.multipleSelection(sql, args);
		LogManager.debug(EXIT);
		LogManager.popRemove();
		return details;
	}
	public String getAdminReferralUpdation(final String quoteNo,final String adminRefStatus,final  String adminRemarks,final String sign, final String excesPre) throws BaseException
	{
		LogManager.push("TravelBean getAdminReferralUpdation method()");
		LogManager.debug(ENTER);
		String sqlRefUpdate = "";
		String excesSign = "";
		String res = "";
		String temp = "sysdate";
		//temp = runner.getSysdate(runner.getuserHomeBranch(quoteNo));		
		String[] args = new String[0];
		if ("Plus".equalsIgnoreCase(sign) && (!"".equals(excesPre) || excesPre.length() > 0)){
			excesSign = "+";
		}
		else if ("Minus".equalsIgnoreCase(sign)	&& (!"".equals(excesPre) || excesPre.length() > 0)){
			excesSign = "-";
		}
		if ("Y".equalsIgnoreCase(adminRefStatus))
		{
			args = new String[4];
			args[0] = adminRemarks;
			args[1] = excesPre;
			args[2] = excesSign;
			args[3] = quoteNo;
			sqlRefUpdate = "update HOME_POSITION_MASTER set REMARKS='Admin',ADMIN_REMARKS = ?,EXCESS_PREMIUM = ?,ADMIN_REFERRAL_STATUS='Y'," +
					"EXCESS_SIGN=?,EFFECTIVE_DATE=(select "+temp+" from dual) where QUOTE_NO = ?";
			res = "Accepted";
		}
		else if ("N".equalsIgnoreCase(adminRefStatus))
		{
			args = new String[4];
			args[0] = adminRemarks;
			args[1] = excesPre;
			args[2] = excesSign;
			args[3] = quoteNo;
			sqlRefUpdate = "update HOME_POSITION_MASTER set REMARKS='Referal',ADMIN_REMARKS='"+ adminRemarks+ "',EXCESS_PREMIUM="
						+ excesPre
						+ ",ADMIN_REFERRAL_STATUS='N',STATUS='R',EXCESS_SIGN='"
						+ excesSign
						+ "',EFFECTIVE_DATE=(select "+temp+" from dual) where QUOTE_NO='"
						+ quoteNo + "'";
			res = "Rejected";
		}
		runner.multipleUpdation(sqlRefUpdate, args);
		final String preSql = "select PREMIUM from HOME_POSITION_MASTER where QUOTE_NO='"	+ quoteNo + "'";
		String pre = runner.singleSelection(preSql);
		if (pre == null || pre.equals(NULL)){
				pre = "0";
		}
		double premium = 0.0;
		if ("+".equalsIgnoreCase(excesSign) && !"".equals(excesPre)	&& (!"".equals(pre) && pre != null)){
				premium = Double.parseDouble(pre)+ Double.parseDouble(excesPre);
		}
		else if ("-".equalsIgnoreCase(excesSign) && !"".equals(excesPre) && (!"".equals(pre) && pre != null)){
				premium = Double.parseDouble(pre)- Double.parseDouble(excesPre);
		}
		else if ((!"".equals(pre) && pre != null)){
			premium = Double.parseDouble(pre);
		}
		double commis = 0.00;
		double totcom = 0.00;
		final String result1[][] = getLoginProId(quoteNo);
		if (result1.length > 0) {
			final String tempPid = CommonHelp.notNullCheck(result1[0][1]);
			commis = Double.parseDouble(getCommisions(CommonHelp.notNullCheckZero(result1[0][0]),tempPid));
		}
		if (commis != 0){
			totcom = premium * (commis / 100);
		}
		args = new String[3];
		args[0] = Double.toString(premium);
		args[1] = Double.toString(totcom);
		args[2] = quoteNo;
		final String upPreSql = "update HOME_POSITION_MASTER set OVERALL_PREMIUM = ?,COMMISSION =? where QUOTE_NO = ?";
		runner.multipleUpdation(upPreSql, args);

		LogManager.debug(EXIT);
		LogManager.popRemove();
		return res;
	}

	public String[][] getLoginProId(final String qno)throws BaseException
	{
		LogManager.push("TravelBean getLoginProId method()");
		LogManager.debug(ENTER);
		final String[] args = {qno};
		final String[][] result = runner.multipleSelection("select LOGIN_ID,nvl(PROPOSAL_NO,'31')  from HOME_POSITION_MASTER where QUOTE_NO = ?",args);
		LogManager.debug(EXIT);
		LogManager.popRemove();
		return result;
	}
	public String getCommisions(final String loginId,final String pid)throws BaseException
	{
		LogManager.push("TravelBean getCommisions method()");
		LogManager.debug(ENTER);
		final String sql = "select nvl(commission,0)  from login_user_details where agency_code=(select oa_code from login_master where login_id = ?  and status='Y') and status='Y' and product_id = ?";
		final String args[] = {loginId,pid};
		String commision = runner.singleSelection(sql, args);
		LogManager.info("Commission value "+commision);
		commision = commision==null?"":commision;
		if(commision.length() == 0) {
			commision = "0";
		}
		LogManager.debug(EXIT);
		LogManager.popRemove();
		return commision;
	}
	public String[][] getNewPremiumDetails(final String qno) throws BaseException
	{
		LogManager.push("TravelBean getNewPremiumDetails method()");
		LogManager.debug(ENTER);
		final String[] args = {qno};
		final String sql = "select PREMIUM,nvl(EXCESS_PREMIUM,'0'),OVERALL_PREMIUM,EXCESS_SIGN,nvl(POLICY_FEE,'0') from HOME_POSITION_MASTER where QUOTE_NO = ?";
		final String[][] result = runner.multipleSelection(sql, args);
		LogManager.debug(EXIT);
		LogManager.popRemove();
		return result;
	}
	public String getMemoranda(final String qno)throws BaseException
	{
		LogManager.push("TravelBean getMemoranda method()");
		LogManager.debug(ENTER);
		String result = "";
		final String[] args ={qno};
		result = runner.singleSelection("select SUMMARY_CLAUSES from HOME_POSITION_MASTER where QUOTE_NO = ?", args);
		if (result == null || result.equals(NULL)){
			result = "";
		}
		LogManager.debug(EXIT);
		LogManager.popRemove();
		return result;
	}
	
	public String[][] getSchemeCoverages(String branchCode)throws BaseException
	{
		String query="SELECT COVERAGES_ID,COVERAGES_VALUE FROM TRAVEL_MAS_COVERAGES WHERE STATUS='Y' AND BRANCH_CODE=?";
		final String args[]=new String[1];
		args[0]=branchCode;
		
		final String[][] result = TestRunner.multipleSelection(query, args);
		return result;
	}
	
	public String[][] getCoveragesName(String schemeId,String optionId)throws BaseException
	{
		String query="select SC.SCHEME_NAME,OP.OPTION_NAME FROM TRAVEL_SCHEME_COVERS SC,TRAVEL_OPTION_COVERS OP WHERE SC.SCHEME_ID=? AND OP.OPTION_ID=?";
		final String args[]=new String[2];
		args[0]=schemeId;
		args[1]=optionId;
		
		final String[][] result = TestRunner.multipleSelection(query, args);
		return result;
	}
	
	public String[][] getCoverages(final String productId)throws BaseException
	{
		LogManager.push("TravelBean getCoverages method()");
		LogManager.debug(ENTER);
		//final String sql = "select PRODUCT_ID,COVER_ID,COVER_DESCRIPTION,GROUP_ID,EFFECTIVE_DATE,END_DATE,REMARKS,STATUS,RATING_FACTOR_ID,DISPLAY_TYPE,DISPLAY_ID,RATING_FACTOR_SUB_ID,nvl(DISPLAY_SIZE,' '),RATING_FACTOR_SUB_NAME,status from Home_Cover_Master where product_id= ? and status = 'Y' and cover_id not in('0') order by (cover_id)";
		
		//final String sql = "select PRODUCT_ID,COVER_ID,COVER_DESCRIPTION,GROUP_ID,EFFECTIVE_DATE,END_DATE,REMARKS,STATUS,RATING_FACTOR_ID,DISPLAY_TYPE,DISPLAY_ID,RATING_FACTOR_SUB_ID,nvl(DISPLAY_SIZE,' '),RATING_FACTOR_SUB_NAME,status from Home_Cover_Master where product_id= ? and status = 'Y' and cover_id not in('0') and cover_id in (11,12) order by (cover_id)";
		final String sql = "SELECT PRODUCT_ID,COVER_ID,COVER_DESCRIPTION,GROUP_ID,EFFECTIVE_DATE,END_DATE,REMARKS,STATUS,DISPLAY_TYPE,DISPLAY_ID,TABLE_NAME,DISPLAY_NAME FROM TRAVEL_COVERAGE_MASTER where product_id= ? and status = 'Y' and cover_id not in('0') and cover_id in (11,12) order by (cover_id)";
		
		final String args[] = {productId};
		final String[][] result = TestRunner.multipleSelection(sql, args);
		System.out.println("Result Length: "+result.length);
		
		LogManager.debug(EXIT);
		LogManager.popRemove();
		return result;
	}
	public String getTravelOptionStatus(final String productId) throws BaseException
	{
		LogManager.push("TravelBean getTravelOptionStatus method()");
		LogManager.debug(ENTER);
		final String[] args = {productId};
		final String result = "";//runner.singleSelection("select STATUS from Home_Cover_Master where product_id = ? and cover_id in('0')", args);
		LogManager.debug(EXIT);
		LogManager.popRemove();
		return result;
	}
	public void updateDeductedValue(final double value, final String quoteNumber)throws BaseException
	{
		LogManager.push("TravelBean updateDeductedValue method()");
		LogManager.debug(ENTER);
		final String[] args = {Double.toString(value),quoteNumber};
		runner.multipleUpdation("update travel_header set DISCOUNTED_VALUE = ? where QUOTE_NO = ?", args);
		LogManager.debug(EXIT);
		LogManager.popRemove();
	}
	public void updateIndValue(final HashMap value,final String quoteNumber) throws BaseException
	{
		LogManager.push("TravelBean updateIndValue method()");
		LogManager.debug(ENTER);
		String[] args = new String[0];
		if (value.size() > 0)
		{
			int len = 0;
			if (value.get("SnoLen") != null) {
				len = Integer.parseInt((String) value.get("SnoLen"));
			}
			for (int l = 0; l < len; l++) {
				args = new String[3];
				String sno = (String) value.get("Sno" + l);
				String values = (String) value.get("SnoPre" + sno);
				String query = "update TRAVEL_DETAIL  set PREMIUM = ?  where SERIAL_NO = ? and QUOTE_NO = ?";
				args[0] = values;
				args[1] = sno;
				args[2] = quoteNumber;
				runner.multipleUpdation(query, args);
			}
		}
		LogManager.debug(EXIT);
		LogManager.popRemove();
	}
	public String[][] getDiscountedValue(String qno) throws BaseException
	{
		LogManager.push("TravelBean getDiscountedValue method()");
		LogManager.debug(ENTER);
		final String[] args = {qno};
		final String sql = "select DISCOUNTED_VALUE,EXCLUDE_USA_CANADA,WORLDWIDE from travel_header where QUOTE_NO = ?";
		final String[][] result = runner.multipleSelection(sql, args);
		LogManager.debug(EXIT);
		LogManager.popRemove();
		return result;
	}

	public String[][] getPremiumByPolicyNo(final String PolicyNo)throws BaseException
	{
		LogManager.push("TravelBean getPremiumByPolicyNo method()");
		LogManager.debug(ENTER);
		final String args[] ={PolicyNo};
		final String sql = "select PREMIUM,POLICY_NO,QUOTE_NO,EXCESS_PREMIUM,OVERALL_PREMIUM,EXCESS_SIGN from HOME_POSITION_MASTER where POLICY_NO  = ?";
		final String[][] result = runner.multipleSelection(sql, args);
		LogManager.debug(EXIT);
		LogManager.popRemove();
		return result;
	}
	public HashMap getTravelMailDetails(final String quoteNo,final String loginId)throws BaseException
	{
		LogManager.push("TravelBean getTravelMailDetails method()");
		LogManager.debug(ENTER);
		final String[] args = {quoteNo,loginId};
		final Map travelMailDetails = new HashMap();
		final String sql = "select per.title,per.first_name,nvl(per.last_name,' '),to_char(per.dob,'dd/mm/yyyy'),per.gender,per.telephone,per.mobile,per.fax,per.email,per.address1,per.address2,per.occupation,per.pobox,per.country,per.emirate,per.nationality,pos.POLICY_NO,pos.overall_premium,to_char(pos.EXPIRY_DATE,'dd/mm/yyyy'),to_char(pos.EFFECTIVE_DATE,'dd/mm/yyyy'),pos.DEBIT_NOTE_NO,to_char(pos.DEBIT_NOTE_DATE,'dd/mm/yyyy'),bro.COMPANY_NAME,bro.CONTACT_PERSON,bro.ADDRESS1,bro.ADDRESS2,bro.ADDRESS3,bro.CITY,bro.EMIRATE,bro.POBOX,pos.PRODUCT_ID,pos.NO_CLAIM_DISCOUNT,pos.NO_CLAIM_DISCOUNT_VALUE,per.COMPANY_NAME ,bro.AGENCY_CODE,pos.status,nvl(bro.PHONE,'0'),nvl(pos.proposal_no,pos.product_id) from PERSONAL_INFO per, HOME_POSITION_MASTER pos, BROKER_COMPANY_MASTER bro where per.customer_id = pos.customer_id  and pos.quote_no = ? and bro.AGENCY_CODE in(select log.oa_code from LOGIN_MASTER log where log.login_id = ?)";
		final String[][] mailDetails = runner.multipleSelection(sql, args);
		if (mailDetails.length > 0)
		{
			travelMailDetails.put("per.title",CommonHelp.notNullCheck(mailDetails[0][0]));
			travelMailDetails.put("per.first_name",CommonHelp.notNullCheck(mailDetails[0][1]));
			travelMailDetails.put("per.last_name",CommonHelp.notNullCheck(mailDetails[0][2]));
			travelMailDetails.put("per.dob",CommonHelp.notNullCheck(mailDetails[0][3]));
			travelMailDetails.put("per.gender",CommonHelp.notNullCheck(mailDetails[0][4]));
			travelMailDetails.put("per.telephone",CommonHelp.notNullCheck(mailDetails[0][5]));
			travelMailDetails.put("per.mobile",CommonHelp.notNullCheck(mailDetails[0][6]));
			travelMailDetails.put("per.fax",CommonHelp.notNullCheck(mailDetails[0][7]));
			travelMailDetails.put("per.email",CommonHelp.notNullCheck(mailDetails[0][8]));
			travelMailDetails.put("per.address1",CommonHelp.notNullCheck(mailDetails[0][9]));
			travelMailDetails.put("per.address2",CommonHelp.notNullCheck(mailDetails[0][10]));
			travelMailDetails.put("per.occupation",CommonHelp.notNullCheck(mailDetails[0][11]));
			travelMailDetails.put("per.pobox",CommonHelp.notNullCheck(mailDetails[0][12]));
			travelMailDetails.put("per.country",CommonHelp.notNullCheck(mailDetails[0][13]));
			travelMailDetails.put("per.emirate",CommonHelp.notNullCheck(mailDetails[0][14]));
			travelMailDetails.put("per.nationality",CommonHelp.notNullCheck(mailDetails[0][15]));
			travelMailDetails.put("pos.POLICY_NO",CommonHelp.notNullCheck(mailDetails[0][16]));
			travelMailDetails.put("pos.PREMIUM",CommonHelp.notNullCheck(mailDetails[0][17]));
			travelMailDetails.put("pos.EXPIRY_DATE",CommonHelp.notNullCheck(mailDetails[0][18]));
			travelMailDetails.put("pos.EFFECTIVE_DATE",CommonHelp.notNullCheck(mailDetails[0][19]));
			travelMailDetails.put("pos.DEBIT_NOTE_NO",CommonHelp.notNullCheck(mailDetails[0][20]));
			travelMailDetails.put("pos.DEBIT_NOTE_DATE",CommonHelp.notNullCheck(mailDetails[0][21]));
			travelMailDetails.put("bro.COMPANY_NAME",CommonHelp.notNullCheck(mailDetails[0][22]));
			travelMailDetails.put("bro.CONTACT_PERSON",CommonHelp.notNullCheck(mailDetails[0][23]));
			travelMailDetails.put("bro.ADDRESS1",CommonHelp.notNullCheck(mailDetails[0][24]));
			travelMailDetails.put("bro.ADDRESS2",CommonHelp.notNullCheck(mailDetails[0][25]));
			travelMailDetails.put("bro.ADDRESS3",CommonHelp.notNullCheck(mailDetails[0][26]));
			travelMailDetails.put("bro.CITY",CommonHelp.notNullCheck(mailDetails[0][27]));
			travelMailDetails.put("bro.COUNTRY",CommonHelp.notNullCheck(mailDetails[0][28]));
			travelMailDetails.put("bro.POBOX",CommonHelp.notNullCheck(mailDetails[0][29]));
			travelMailDetails.put("pos.PRODUCT_ID",CommonHelp.notNullCheck(mailDetails[0][30]));
			travelMailDetails.put("pos.ncd", CommonHelp.notNullCheckZero(mailDetails[0][31]));
			travelMailDetails.put("pos.ncdval",CommonHelp.notNullCheckZero(mailDetails[0][32]));
			travelMailDetails.put("per.companyName",CommonHelp.notNullCheck(mailDetails[0][33]));
			travelMailDetails.put("bro.AGENCY_CODE",CommonHelp.notNullCheck(mailDetails[0][34]));
			travelMailDetails.put("pos.status",CommonHelp.notNullCheck(mailDetails[0][35]));
			travelMailDetails.put("pos.proposal",CommonHelp.notNullCheck(mailDetails[0][37]));
		}
		LogManager.debug(EXIT);
		LogManager.popRemove();
		return (HashMap)travelMailDetails;
	}
	public String[][] getCoverageValues(final String qno)throws BaseException
	{
		LogManager.push("TravelBean getCoverageValues method()");
		LogManager.debug(ENTER);
		final String sql = "SELECT STATUS,COVERAGES_ID,PREMIUM FROM TRAVEL_COVERAGES_DETAIL WHERE QUOTE_NO=? ORDER BY COVERAGES_ID";
		final String args[]= {qno};
		final String[][] result = runner.multipleSelection(sql, args);
		LogManager.debug(EXIT);
		LogManager.popRemove();
		return result;
	}
	public String[][] getUnderWritingOption(final String qno) throws BaseException
	{
		LogManager.push("TravelBean getUnderWritingOption method()");
		LogManager.debug(ENTER);
		final String sql = "select AGE_ABOVE_SIXTY_FIVE,EXISTING_MEDICAL_CONDITION,MEDICAL_TRAVEL_CLAIMS from HOME_POSITION_MASTER where QUOTE_NO = ?";
		final String args[] = {qno};
		final String [][]result = runner.multipleSelection(sql, args);
		LogManager.debug(EXIT);
		LogManager.popRemove();
		return result;
	}
	public String getAdminBrokerMailId(final String qno) throws BaseException
	{
		LogManager.push("TravelBean getAdminBrokerMailId method()");
		LogManager.debug(ENTER);
		final String[] args = {qno};
		final String sql = "select distinct(email) from personal_info where  APPLICATION_ID!=1 and agency_code = (select agency_code from login_master where login_id = (select login_id from HOME_POSITION_MASTER where quote_no = ?))";
		final String broker = runner.singleSelection(sql, args);
		final String admin = runner.singleSelection("select email_to from mail_details where mail_no =14");
		final String mailIds = admin + "," + broker;
		LogManager.debug(EXIT);
		LogManager.popRemove();
		return mailIds;
	}
	public String[][] getCoverDuration(final String qno)throws BaseException
	{
		LogManager.push("TravelBean getCoverDuration method()");
		LogManager.debug(ENTER);
		final String args[] = {qno};
		final String sql = "select QUOTE_NO,to_char(inception_date,'dd/mm/yyyy'),to_char(EXPIRY_DATE,'dd/mm/yyyy'),POLICY_TERM,to_char(EFFECTIVE_DATE,'dd/mm/yyyy') from HOME_POSITION_MASTER where QUOTE_NO = ?";
		final String[][] result = runner.multipleSelection(sql, args);
		LogManager.debug(EXIT);
		LogManager.popRemove();
		return result;
	}
	public String getBrokerCompanyName(final String login)throws BaseException
	{
		LogManager.push("TravelBean getBrokerCompanyName method()");
		LogManager.debug(ENTER);
		final String query = "select COMPANY_NAME from broker_company_master where AGENCY_CODE in(select oa_code from LOGIN_MASTER log where log.login_id='"+login + "')";
		final String companyName = runner.singleSelection(query);
		LogManager.debug(EXIT);
		LogManager.popRemove();
		return companyName;
	}
	public String[][] getTravelProductTypes(final String pid)throws BaseException
	{
		LogManager.push("TravelBean getTravelProductTypes method()");
		LogManager.debug(ENTER);
		final String sql = "select product_id,PRODUCT_DESCRIPTION from home_product_master where status='Y' and BROKER_ID in(select REMARKS from PRODUCT_MASTER where PRODUCT_ID = ?) order by product_id";
		final String args[] = {pid};
		final String[][] result = runner.multipleSelection(sql, args);
		LogManager.debug(EXIT);
		LogManager.popRemove();
		return result;
	}
	public String[][] getCoveragesForVal(final String productId)throws BaseException
	{
		LogManager.push("TravelBean getCoveragesForVal method()");
		LogManager.debug(ENTER);
		final String sql = "select PRODUCT_ID,COVER_ID,COVER_DESCRIPTION,GROUP_ID,EFFECTIVE_DATE,END_DATE,REMARKS,STATUS,RATING_FACTOR_ID,DISPLAY_TYPE,DISPLAY_ID,RATING_FACTOR_SUB_ID,nvl(DISPLAY_SIZE,' '),RATING_FACTOR_SUB_NAME from Home_Cover_Master where product_id= ? and cover_id not in('0') order by (cover_id)";
		final String args[] ={productId};
		final String[][] result = runner.multipleSelection(sql, args);
		LogManager.debug(EXIT);
		LogManager.popRemove();
		return result;
	}
	public String getBackDatesAllowed(final String user)throws BaseException
	{
		LogManager.push("TravelBean getBackDatesAllowed method()");
		LogManager.debug(ENTER);
		String result = "0";
		String backDateQry = "";
		String[] args = {user};
		final String userType = runner.singleSelection("select usertype from login_master where login_id = ?", args);
		if (userType != null)
		{
			if (userType.equalsIgnoreCase("RSAUSER")){
				result = "30";
			}
			else {
				args[0] = user;
				backDateQry = "select BACK_DATE_ALLOWED from login_user_details where agency_code in(select oa_code from login_master where login_id = ?) and product_id in('22','31')";
				result = runner.singleSelection(backDateQry, args);
			}
		}
		if (result == null){
			result = "0";
		}
		LogManager.debug(EXIT);
		LogManager.popRemove();
		return result;
	}

	public String comparewithMinPre(final double curPre, final String login,final String pid) throws BaseException
	{
		LogManager.push("TravelBean comparewithMinPre method()");
		LogManager.debug(ENTER);
		String sql = "";
		String min_amount = ZERO;
		double minPre = 0.00;
		String result = "";
		final String[] args = {login,pid};
		sql = "select MIN_PREMIUM_AMOUNT from LOGIN_USER_DETAILS where AGENCY_CODE in(select agency_code from LOGIN_MASTER where login_id = ?) and product_id = ?";
		min_amount = runner.singleSelection(sql, args);
		if (min_amount != null && min_amount.length() > 0) {
			minPre = Double.parseDouble(min_amount);
		}
		if (curPre < minPre){
			result =Double.toString(minPre);
		}
		else{
			result = Double.toString(curPre);
		}
		LogManager.debug(EXIT);
		LogManager.popRemove();
		return result;
	}

	public String getReferralCases(final String quoteNumber)throws BaseException
	{
		LogManager.push("TravelBean getReferralCases method()");
		LogManager.debug(ENTER);
		final String[] args = {quoteNumber};
		final String referalName = runner.singleSelection("select REFERRAL_DESCRIPTION from HOME_POSITION_MASTER where QUOTE_NO = ?", args);
		LogManager.debug(EXIT);
		LogManager.popRemove();
		return referalName;
	}

	public String getLoginByQuoteNo(final String qno)throws BaseException
	{
		LogManager.push("TravelBean getLoginByQuoteNo method()");
		LogManager.debug(ENTER);
		final String[] args = {qno};
		final String login = runner.singleSelection("select login_id from HOME_POSITION_MASTER where QUOTE_NO = ?", args);
		LogManager.debug(EXIT);
		LogManager.popRemove();
		return login;
	}

	public String[][] getCustomerRelation(final String qno) throws BaseException
	{
		LogManager.push("TravelBean getCustomerRelation method()");
		LogManager.debug(ENTER);
		final String[] args = {qno};
		final String query = "select QUOTE_NO,SERIAL_NO,PASSENGER_NAME,RELATION,DOB from TRAVEL_DETAIL where QUOTE_NO = ?";
		final String[][] relation = runner.multipleSelection(query, args);
		LogManager.debug(EXIT);
		LogManager.popRemove();
		return relation;
	}

	public String[][] getRelations(final String loginBra)throws BaseException
	{
		LogManager.push("TravelBean getRelations method()");
		LogManager.debug(ENTER);
		final String[] args = {loginBra};
		final String query = "select CATEGORY_DETAIL_ID,DETAIL_NAME from CONSTANT_DETAIL where CATEGORY_ID = 53 and BRANCH_CODE= ?";
		final String[][] result = runner.multipleSelection(query, args);
		LogManager.debug(EXIT);
		LogManager.popRemove();
		return result;
	}

	public String getTotalPremium(final String qno)throws BaseException
	{
		LogManager.push("TravelBean getTotalPremium method()");
		LogManager.debug(ENTER);
		final String[] args = {qno};
		final String result = runner.singleSelection("select PREMIUM from HOME_POSITION_MASTER where quote_no = ?", args);
		LogManager.debug(EXIT);
		LogManager.popRemove();
		return result;
	}

	public String getProductName(final String pid)throws BaseException
	{
		LogManager.push("TravelBean getProductName method()");
		LogManager.debug(ENTER);
		final String[] args = {pid};
		final String result = runner.singleSelection("select PRODUCT_NAME from PRODUCT_MASTER where PRODUCT_ID = ?", args);
		LogManager.debug(EXIT);
		LogManager.popRemove();
		return result;
	}

	public String getProductandCoverName(final String pid,final String bcode)throws BaseException
	{
		LogManager.push("TravelBean getProductandCoverName method()");
		LogManager.debug(ENTER);
		final String[] args = {pid,bcode};
		final String result = runner.singleSelection("select PRODUCT_NAME from PRODUCT_MASTER where PRODUCT_ID = ? and branch_Code=?", args);
		LogManager.debug(EXIT);
		LogManager.popRemove();
		return result;
	}

	public String getBrokerBranchName(final String qno) throws BaseException
	{
		LogManager.push("TravelBean getBrokerBranchName method()");
		LogManager.debug(ENTER);
		final String[] args = {qno};
		final String sql = "select BRANCH_NAME from BRANCH_MASTER where BRANCH_CODE=(select BRANCH_CODE from BROKER_COMPANY_MASTER where AGENCY_CODE in(select log.OA_CODE from LOGIN_MASTER log,HOME_POSITION_MASTER home where quote_no= ? and log.LOGIN_ID=home.LOGIN_ID))";
		final String broker = runner.singleSelection(sql, args);
		LogManager.debug(EXIT);
		LogManager.popRemove();
		return broker;
	}

	public String[][] getHeadresDetails(final String qno)throws BaseException
	{
		LogManager.push("TravelBean getHeadresDetails method()");
		LogManager.debug(ENTER);
		final String args[] = {qno, qno};
		final String sql = "select nvl(DISCOUNTED_VALUE,'0'), nvl(EXCLUDE_USA_CANADA,'N'),nvl(WORLDWIDE,'N'), to_char(INSURANCE_END_DATE,'dd/mm/yyyy'), to_char(EFFECTIVE_DATE,'dd/mm/yyyy'), nvl(COVERAGE_WINTERSPORTS,'N'), nvl(COVERAGE_GOLFCOVER,'N'), nvl(MEDICAL_EXPENSES_EXCLUDED,'N'),nvl(BAGGAGE_EXCLUDED,'N'),nvl(TOTAL_NO_OF_PEOPLE_COVERED,'0'),SCHEME_COVER,(select country_name from country_master where COUNTRY_ID=tr.COUNTRY_ORIGIN and AMEND_ID=(select max(AMEND_ID) from country_master where COUNTRY_ID=tr.COUNTRY_ORIGIN )) COUNTRY_ORIGIN,(select country_name from country_master where COUNTRY_ID=tr.COUNTRY_DESTN and AMEND_ID=(select max(AMEND_ID) from country_master where COUNTRY_ID=tr.COUNTRY_DESTN )) COUNTRY_DESTN from travel_header tr where QUOTE_NO=? and amend_id=(select max(amend_id) from TRAVEL_HEADER where STATUS = 'Y' and QUOTE_NO =?)";
		final String[][] result = runner.multipleSelection(sql, args);
		LogManager.debug(EXIT);
		LogManager.popRemove();
		return result;
	}

	public String getFullTravelIds() throws BaseException
	{
		LogManager.push("TravelBean getFullTravelIds method()");
		LogManager.debug(ENTER);
		final ProductSelection select = new ProductSelection();
		final String[][] proList = select.getTravelProductDetails();
		String fullTravelIds = "";

		if (proList != null && proList.length > 0)
		{
			for (int i = 0; i < proList.length; i++)
			{
				if (i < (proList.length - 1))
				{
					fullTravelIds = fullTravelIds + proList[i][0] + ",";
				} else {
					fullTravelIds = fullTravelIds + proList[i][0];
				}
			}
		}
		LogManager.debug(EXIT);
		LogManager.popRemove();
		return fullTravelIds;
	}
	public String getIssuerName(final String login)throws BaseException
	{
		LogManager.push("TravelBean getIssuerName method()");
		LogManager.debug(ENTER);
		final String sql = "select nvl(first_name,company_name) from personal_info where AGENCY_CODE=(select agency_code from LOGIN_MASTER where login_id = ?)";
		final String[] args = {login};
		final String issuer = runner.singleSelection(sql, args);
		LogManager.debug(EXIT);
		LogManager.popRemove();
		return issuer;
	}
	public String getAdminRemarks(final String login)throws BaseException
	{
		LogManager.push("TravelBean getAdminRemarks method()");
		LogManager.debug(ENTER);
		final String sql = "select nvl(admin_remarks,' ') from HOME_POSITION_MASTER where quote_no = ?";
		final String args[] ={login};
		final String remarks = runner.singleSelection(sql, args);
		LogManager.debug(EXIT);
		LogManager.popRemove();
		return remarks;
	}
	public String[][] travelPositionMasterSelect(final String quoteNo)throws BaseException
	{
		LogManager.push("TravelBean travelPositionMasterSelect method()");
		LogManager.debug(ENTER);
		final String args[] ={quoteNo};
		final String sql = "select POLICY_NO,nvl(PREMIUM,'0'),DEBIT_NOTE_NO,to_char(DEBIT_NOTE_DATE,'dd/mm/yyyy'),to_char(nvl(DEBIT_NOTE_DATE,EFFECTIVE_DATE), 'dd/mm/yyyy')|| ' ' ||TO_date (NVL(DEBIT_NOTE_DATE,EFFECTIVE_DATE)),to_char(INCEPTION_DATE, 'dd/mm/yyyy'),to_char(INCEPTION_DATE,'dd/mm/yyyy'),POLICY_TERM,PRODUCT_ID,nvl(EXCESS_SIGN,'+'),nvl(EXCESS_PREMIUM,'0'),login_id,nvl(APPROVED_BY,'Nil'),nvl(PROPOSAL_NO,PRODUCT_ID),nvl(RECEIPT_NO,' '),to_char(RECEIPT_DATE,'dd/mm/yyyy'),nvl(PAYMENT_MODE,'Q'),PROPOSAL_NO,nvl(POLICY_FEE,'0') from HOME_POSITION_MASTER where QUOTE_NO=?";
		//final String sql = "select POLICY_NO,nvl(PREMIUM,'0'),DEBIT_NOTE_NO,to_char(DEBIT_NOTE_DATE,'dd-mm-yyyy'),to_char(INCEPTION_DATE, 'dd-mm-yyyy'),to_char(EFFECTIVE_DATE,'dd-mm-yyyy'),to_char(ENTRY_DATE,'dd-mm-yyyy'),POLICY_TERM,PRODUCT_ID,nvl(EXCESS_SIGN,'+'),nvl(EXCESS_PREMIUM,'0'),login_id,nvl(APPROVED_BY,'Nil'),PRODUCT_ID,nvl(RECEIPT_NO,' '),to_char(RECEIPT_DATE,'dd-mm-yyyy'),nvl(PAYMENT_MODE,'Q'),PROPOSAL_NO from HOME_POSITION_MASTER where QUOTE_NO=?";
		final String[][] result = runner.multipleSelection(sql, args);
		LogManager.debug(EXIT);
		LogManager.popRemove();
		return result;
	}
	public String getProductNamePDF(final String pid)throws BaseException
	{
		LogManager.push("TravelBean getProductNamePDF method()");
		LogManager.debug(ENTER);
		final String query = "select PRODUCT_DESCRIPTION from HOME_PRODUCT_MASTER where PRODUCT_ID =?";
		final String[] args = {pid};
		final String result = runner.singleSelection(query, args);
		LogManager.debug(EXIT);
		LogManager.popRemove();
		return result;
	}
	public int[] getAgeValue(final String quoteNumber)throws BaseException
	{
		LogManager.push("TravelBean getAgeValue method()");
		LogManager.debug(ENTER);
		final String query = "select age from travel_detail where quote_no = ? order by SERIAL_NO";
		int[] age = new int[0];
		String[] args = {quoteNumber};

		try {
			final String[][] result = runner.multipleSelection(query, args);
			if (result != null && result.length > 0) {
				age = new int[result.length];
				for (int i = 0; i < result.length; i++) {
					try {
						age[i] = Integer.parseInt(result[i][0]);
					} catch (Exception ex) {
						age[i] = 0;
					}
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogManager.debug(EXIT);
		LogManager.popRemove();
		return age;
	}
	
	public double getTravelCoveragesPremiumRate(String coveragesId,String covetId,String branch,String productCode)
	{
		LogManager.push("TravelBean getTravelCoveragesPremiumRate method(): ");
		LogManager.debug(ENTER);
		double returnVal=1.0;
		String query="SELECT RATE FROM TRAVEL_LINK_COVERAGES WHERE COVERAGES_ID=? AND COVER_ID=? AND BRANCH_CODE=? AND PRODUCT_CODE=?";
		String args[]=new String[4];
		
		args[0]=coveragesId;
		args[1]=covetId;
		args[2]=branch;
		args[3]=productCode;
		
		try{
		
			returnVal=Double.parseDouble(runner.singleSelection(query,args));

		}catch(Exception e){
			System.out.println("Exception In getTravelCoveragesPremiumRate(): "+e);
		}
		
		LogManager.debug(EXIT);
		LogManager.popRemove();
		
		return returnVal;
	}
	
	 public String[][] getTravelCoverages(final String schemeCover,final String branchCode,String productCode) throws BaseException
	 {
		 LogManager.push("TravelBean getTravelCoverages method(): "+schemeCover);
		 LogManager.debug(ENTER);
		 String schemeName="",optionName="";
			int index=schemeCover.indexOf("_");
			
			if(index!=-1)
			{
				schemeName=schemeCover.substring(0, index);
				optionName=schemeCover.substring(index+1, schemeCover.length()).replaceAll("_", " ");
			}
			else
			{
				schemeName=schemeCover;
				optionName="None";
			}
			
			System.out.println("schemeName: "+schemeName+" optionName: "+optionName);
			final String query="SELECT COVERAGES_ID,COVERAGES_VALUE,COVERAGES_NAME,( SELECT COVER_ID FROM TRAVEL_SCHEME_OPTION_COVERS WHERE SCHEME_ID=(SELECT SCHEME_ID FROM TRAVEL_SCHEME_COVERS WHERE UPPER(SCHEME_NAME)=UPPER(?)) AND OPTION_ID=(SELECT OPTION_ID FROM TRAVEL_OPTION_COVERS WHERE UPPER(OPTION_NAME)=UPPER(?)) AND BRANCH_CODE=? ) COVER_ID FROM TRAVEL_MAS_COVERAGES WHERE COVERAGES_ID IN ( SELECT COVERAGES_ID FROM TRAVEL_LINK_COVERAGES WHERE COVER_ID=( SELECT COVER_ID FROM TRAVEL_SCHEME_OPTION_COVERS WHERE SCHEME_ID=(SELECT SCHEME_ID FROM TRAVEL_SCHEME_COVERS WHERE UPPER(SCHEME_NAME)=UPPER(?)) AND OPTION_ID=(SELECT OPTION_ID FROM TRAVEL_OPTION_COVERS WHERE UPPER(OPTION_NAME)=UPPER(?)) AND BRANCH_CODE=? AND PRODUCT_CODE=? ) AND BRANCH_CODE=? AND PRODUCT_CODE=? ) AND BRANCH_CODE=?";
			String args[]=new String[10];
			
			args[0]=schemeName;
			args[1]=optionName;
			args[2]=branchCode;
			args[3]=schemeName;
			args[4]=optionName;
			args[5]=branchCode;
			args[6]=productCode;
			args[7]=branchCode;
			args[8]=productCode;
			args[9]=branchCode;
			
			final String[][] travelCovers =runner.multipleSelection(query,args);
			System.out.println("travelCovers: "+travelCovers.length);
			
		  LogManager.debug(EXIT);
		  LogManager.popRemove();	
		 return travelCovers;
	 }
	
	public HashMap getTravelCoveragesList(final String scheme_id,final String option_id,final String branchCode,final String productCode) throws BaseException
	{
	//	LogManager.push("TravelBean getTravelCoveragesList method(): "+schemeCover);
		LogManager.debug(ENTER);
		final Map travelCoversHas = new HashMap();
		/*String schemeName="",optionName="";
		int index=schemeCover.indexOf("_");
		
		if(index!=-1)
		{
			schemeName=schemeCover.substring(0, index);
			optionName=schemeCover.substring(index+1, schemeCover.length()).replaceAll("_", " ");
		}
		else
		{
			schemeName=schemeCover;
			optionName="None";
		}
		
		System.out.println("schemeName: "+schemeName+" optionName: "+optionName);*/
		
		final String query="SELECT COVERAGES_ID,COVERAGES_VALUE,COVERAGES_NAME FROM TRAVEL_MAS_COVERAGES WHERE COVERAGES_ID IN ( SELECT COVERAGES_ID FROM TRAVEL_LINK_COVERAGES WHERE COVER_ID=( SELECT COVER_ID FROM TRAVEL_SCHEME_OPTION_COVERS WHERE SCHEME_ID=? AND OPTION_ID=?  and product_code=? AND BRANCH_CODE=?))  ORDER BY COVERAGES_ID";
		String args[]=new String[4];
		
		args[0]=scheme_id;
		args[1]=option_id;
		args[2]=productCode;
		args[3]=branchCode;
		
		final String[][] travelCovers =runner.multipleSelection(query,args);
		System.out.println("travelCovers : "+travelCovers);
		if(travelCovers!=null && travelCovers.length>0)
		{
			for (int i = 0; i < travelCovers.length; i++)
			{
				travelCovers[i][0] = CommonHelp.notNullCheck(travelCovers[i][0]);
				travelCovers[i][1] = CommonHelp.notNullCheck(travelCovers[i][1]);
				travelCovers[i][2] = CommonHelp.notNullCheck(travelCovers[i][2]);
				travelCoversHas.put("CoverId"+i, travelCovers[i][0]);
				travelCoversHas.put("Coveres"+i, travelCovers[i][2]);
			}
		}
		
		LogManager.debug(EXIT);
		LogManager.popRemove();
		return (HashMap)travelCoversHas;
	}
	
	public HashMap getTravelCovers(final String quoteNo)throws BaseException
	{ 
		LogManager.push("TravelBean getTravelCovers method()");
		LogManager.debug(ENTER);
		final Map travelCoversHas = new HashMap();
		//final String[] args = {quoteNo};
		//final String sql = "select cover_id,cover_description,status from home_cover_master where product_id =(select product_id from HOME_POSITION_MASTER where QUOTE_NO =?) and cover_id not in('0') order by cover_id";
		//final String[][] travelCovers = runner.multipleSelection(sql, args);
		
		final String[][] travelCovers = getSchemeCovers(runner.singleSelection("select proposal_no from HOME_POSITION_MASTER where QUOTE_NO ="+quoteNo),"12");
		for (int i = 0; i < travelCovers.length; i++)
		{
			travelCovers[i][0] = CommonHelp.notNullCheck(travelCovers[i][0]);
			travelCovers[i][1] = CommonHelp.notNullCheck(travelCovers[i][1]);
			travelCovers[i][2] = CommonHelp.notNullCheck(travelCovers[i][2]);
			travelCoversHas.put("TC" + travelCovers[i][0], "Y");
		}
		LogManager.debug(EXIT);
		LogManager.popRemove();
		return (HashMap)travelCoversHas;
	}
	// Getting Customer mail Id for Policy schedule Pdf mail
	public String getCustomerMailId(final String quoteNo) throws BaseException
	{
		LogManager.push("TravelBean getCustomerMailId method()");
		LogManager.debug(ENTER);
		final String args[] = {quoteNo};
		final String sql = "select email from personal_info where customer_id=(select customer_id from HOME_POSITION_MASTER where quote_no = ?)";
		final String customerMail = runner.singleSelection(sql, args);
		LogManager.debug(EXIT);
		LogManager.popRemove();
		return customerMail;
	}
	public void updateReferalStatus(final String QuoteNo,final String referralName,String adminRemarks,final String adminStatus)throws BaseException
	{
		LogManager.push("TravelBean updateReferalStatus method()");
		LogManager.debug(ENTER);
		final String[] args = new String[4];
		adminRemarks = adminRemarks.replaceAll("'", "''");
		args[0] = adminRemarks;
		args[1] = adminStatus;
		args[2] = referralName;
		args[3] = QuoteNo;
		final String sql = "update HOME_POSITION_MASTER set REMARKS='Referal',SUMMARY_REMARKS= ?,ADMIN_REFERRAL_STATUS = ?, REFERRAL_DESCRIPTION = ? where quote_no = ?";
		runner.multipleUpdation(sql, args);
		LogManager.debug(EXIT);
		LogManager.popRemove();
	}
	public boolean getReferralStatus(final String qno) throws BaseException
	{
		LogManager.push("TravelBean getReferralStatus method()");
		LogManager.debug(ENTER);
		boolean flag = true;
		final String[] args = {qno};
		final String res = runner.singleSelection("select nvl(remarks,'NIl') from HOME_POSITION_MASTER WHERE QUOTE_NO =?",args);
		if (res.equalsIgnoreCase("Referal")){
			flag = true;
		}
		else{
			flag = false;
		}
		LogManager.debug(EXIT);
		LogManager.popRemove();
		return flag;
	}
	
	public void updateReferralStatus(final String qno) throws BaseException
	{
		LogManager.push("TravelBean updateReferralStatus method()");
		LogManager.debug(ENTER);
		boolean flag = true;
		final String[] args = {qno};
		final String res = runner.singleSelection("UPDATE HOME_POSITION_MASTER SET remarks='' WHERE QUOTE_NO =?",args);
		
		LogManager.debug(EXIT);
		LogManager.popRemove();
	}
	
	public String getBranchName(final String loginBra)throws BaseException
	{
		LogManager.push("TravelBean getBranchName method()");
		LogManager.debug(ENTER);
		final String[] args = {loginBra};
		final String res = runner.singleSelection("select BRANCH_NAME from BRANCH_MASTER WHERE BRANCH_CODE=?",args);
		LogManager.debug(EXIT);
		LogManager.popRemove();
		return res;
	}
	public String[] getRelValue(final String quoteNumber)throws BaseException
	{
		LogManager.push("TravelBean getRelValue method()");
		LogManager.debug(ENTER);
		String[] rel = new String[0];
		final String[] args = {quoteNumber};
		final String[][] result = runner.multipleSelection("select relation from travel_detail where quote_no = ? order by SERIAL_NO", args);
		if (result != null && result.length > 0) {
			rel = new String[result.length];
			for (int i = 0; i < result.length; i++) {
				rel[i] = result[i][0];
			}
		}
		LogManager.debug(EXIT);
		LogManager.popRemove();
		return rel;
	}
	public String[] getSno(final String quoteNumber)throws BaseException
	{
		LogManager.push("TravelBean getSno method()");
		LogManager.debug(ENTER);
		String[] rel = new String[0];
		final String[] args = {quoteNumber};
		final String[][] result = runner.multipleSelection("select SERIAL_NO from travel_detail where quote_no = ? order by SERIAL_NO", args);
		if (result != null && result.length > 0) {
			rel = new String[result.length];
			for (int i = 0; i < result.length; i++) {
				rel[i] = result[i][0];
			}
		}
		LogManager.debug(EXIT);
		LogManager.popRemove();
		return rel;
	}
	public String getLoginUsertype(final String loginId)throws BaseException
	{
		LogManager.push("TravelBean getLoginUsertype method()");
		LogManager.debug(ENTER);
		final String[] args = {loginId};
		final String loginUsertype = runner.singleSelection("select nvl(usertype,'') from LOGIN_MASTER where login_id= ?", args);
		LogManager.debug(EXIT);
		LogManager.popRemove();
		return loginUsertype;
	}
	public String getLoginUserNames(final String loginId) throws BaseException
	{
		LogManager.push("TravelBean getLoginUserNames method()");
		LogManager.debug(ENTER);
		final String[] args = {loginId};
		//final String qry = "select trim(nvl(nvl(FIRST_NAME||' '||LAST_NAME,company_name),login_id)) from personal_info where agency_code in(select agency_code from LOGIN_MASTER where login_id =?)";
		final String qry = "select COMPANY_NAME from broker_company_master WHERE   AGENCY_CODE=(SELECT oa_code FROM LOGIN_MASTER WHERE login_id = ?)";
		final String loginUsertype = runner.singleSelection(qry, args);
		LogManager.debug(EXIT);
		LogManager.popRemove();
		return loginUsertype;
	}
	public String[][] getClaimIds(final String pid) throws BaseException
	{
		LogManager.push("TravelBean getClaimIds method()");
		LogManager.debug(ENTER);
		final String[] args = {pid};
		final String qry = "select RATING_FACTOR_ID,RATING_FACTOR_SUB_ID,STATUS from Home_Cover_Master where product_id=? and cover_id='0'";
		final String[][] claimIds = runner.multipleSelection(qry, args);
		LogManager.debug(EXIT);
		LogManager.popRemove();
		return claimIds;
	}
	public String[][] getRatingFactorNames(final String pid) throws BaseException
	{
		LogManager.push("TravelBean getRatingFactorNames method()");
		LogManager.debug(ENTER);
		final String[] args = {pid};
		final String qry = "select RATING_FACTOR_SUB_NAME from Home_Cover_Master where product_id=? and cover_id not in('0') order by cover_id";
		final String[][] claimIds = runner.multipleSelection(qry, args);
		LogManager.debug(EXIT);
		LogManager.popRemove();
		return claimIds;
	}

	public String[][] getRatingFactorNamesAll(final String pid)throws BaseException
	{
		LogManager.push("TravelBean getRatingFactorNamesAll method()");
		LogManager.debug(ENTER);
		final String[] args = {pid};
		final String qry = "select RATING_FACTOR_SUB_NAME,VALID_RATING_FACTOR_SUB_NAME,remarks from Home_Cover_Master where product_id= ? and cover_id='0'";
		final String[][] claimIds = runner.multipleSelection(qry, args);
		LogManager.debug(EXIT);
		LogManager.popRemove();
		return claimIds;
	}

	public String getDefaultId(final String pid) throws BaseException
	{
		LogManager.push("TravelBean getDefaultId method()");
		LogManager.debug(ENTER);
		final String args[] = {pid};
		final String res = runner.singleSelection("select COMPANY_ID from PRODUCT_MASTER where product_id=?", args);
		LogManager.debug(EXIT);
		LogManager.popRemove();
		return res;
	}

	public String getPaymentStatus(final String qid)throws BaseException
	{
		LogManager.push("TravelBean getPaymentStatus method()");
		LogManager.debug(ENTER);
		final String[] args = {qid};
		final String status = runner.singleSelection("select STATUS from PAYMENT where MERCHTXNREF_POLICY_NO =?", args);
		LogManager.debug(EXIT);
		LogManager.popRemove();
		return status;
	}

	public String[][] getPaymentCheckStatus(final String loginId,final String pid)throws BaseException
	{
		LogManager.push("TravelBean getPaymentCheckStatus method()");
		LogManager.debug(ENTER);
		final String[] args = {loginId,pid};
		final String qry = "select nvl(PAY_RECEIPT_STATUS,'N'),nvl(RECEIPT_STATUS,'Y'),nvl(MEMBERSHIP_STATUS,'Y'),nvl(ENDORSEMENT_STATUS,'N'),nvl(RENEWAL_STATUS,'N') from LOGIN_USER_DETAILS where agency_code=(select oa_code from login_master where login_id= ? ) and product_id = ?";
		final String statuses[][] = runner.multipleSelection(qry, args);
		LogManager.debug(EXIT);
		LogManager.popRemove();
		return statuses;
	}

	public String getReceiptNo(final String quoteno,final String travelProductId,final String branch)throws BaseException
	{
		LogManager.push("TravelBean getReceiptNo method()");
		LogManager.debug(ENTER);
		String[] args = new String[1];
		com.maan.common.Customer.DataSelect dataSelection;dataSelection = new com.maan.common.Customer.DataSelect();
		String receiptNo = "";
		args[0] = quoteno;
		receiptNo = runner.singleSelection("select receipt_no from home_position_master where quote_no= ? and receipt_no is not null",	args);
		com.maan.Home.DataManipualtion.DataSelect data = new com.maan.Home.DataManipualtion.DataSelect();
		String hour;hour = data.getSysdateTime(quoteno,"QuoteNo");
		if ("".equalsIgnoreCase(receiptNo))
		{
			receiptNo = dataSelection.getMaxReceiptNo(travelProductId,branch);
			args = new String[2];
			args[0] = receiptNo;
			args[1] = quoteno;
			runner.multipleUpdation("update home_position_master set receipt_no = ? ,receipt_date=(select "+hour+" from dual) where quote_no = ?",args);
		}
		LogManager.debug(EXIT);
		LogManager.popRemove();
		return receiptNo;
	}
	public String getTravelOriginalId(final String pid) throws BaseException
	{
		LogManager.push("TravelBean getTravelOriginalId method()");
		LogManager.debug(ENTER);
		final String qry = "select PRODUCT_ID from PRODUCT_MASTER where remarks=(select BROKER_ID from HOME_PRODUCT_MASTER where product_id=?)";
		final String[] args ={ pid};
		final String status = runner.singleSelection(qry, args);
		LogManager.debug(EXIT);
		LogManager.popRemove();
		return status;
	}
	/*public int getMaxApplicationNo() throws BaseException
	{
		LogManager.push("TravelBean getMaxApplicationNo method()");
		LogManager.debug(ENTER);
		final String maxappquery = "select max(application_no) from home_position_master where product_id not in ('30')";
		int result = 0;
		final String res = runner.singleSelection(maxappquery);
		if (res.length() > 0){
			result = Integer.parseInt(res) + 1;
		}
		LogManager.debug(EXIT);
		LogManager.popRemove();
		return result;
	}*/
	public boolean getDebitStatus(final String login,final String pid) throws BaseException
	{
		LogManager.push("TravelBean getDebitStatus method()");
		LogManager.debug(ENTER);
		final String[] args = {login,pid};
		final String sql = "select nvl(FREIGHT_DEBIT_OPTION,'N') from login_user_details where login_id =  ? and product_id= ?";
		boolean result = false;
		final String res = runner.singleSelection(sql, args);
		if (res != null) {
			if ("Y".equalsIgnoreCase(res)){
				result = true;
			}
			else{
				result = false;
			}
		}
		LogManager.debug(EXIT);
		LogManager.popRemove();
		return result;
	}
	public String[][] getPolicyClauses(final String pid,final String status,final String schemeId) throws BaseException
	{
		LogManager.push("TravelBean getPolicyClauses method()");
		LogManager.debug(ENTER);
		final String sql = "select SECTION_ID,CLAUSES_DESCRIPTION,CURRENCY_NAME,VALUE,status from TRAVEL_CLAUSES_MASTER where status in("
				+ status + ") and product_id=? and cover_id = ? order by display_order";
		final String[] args = {pid,schemeId};
		final String[][] result = runner.multipleSelection(sql, args);
		LogManager.debug(EXIT);
		LogManager.popRemove();
		return result;
	}

	public String[][] paymentDetails(final String qno, final String proId)throws BaseException
	{
		LogManager.push("TravelBean paymentDetails method()");
		LogManager.debug(ENTER);
		String[] args = {qno,proId};
		final String sql = "select MERCHTXNREF_POLICY_NO,AMOUNT_PREMIUM,RECEIPTNO,TRANSACTIONNO,CARDTYPE from payment where MERCHTXNREF_POLICY_NO = ? and product_id = ?";
		final String[][] result = runner.multipleSelection(sql, args);
		LogManager.debug(EXIT);
		LogManager.popRemove();
		return result;
	}
	public String getProductName(final String quoteNumber,final String branchCode)throws BaseException
	{
		LogManager.push("TravelBean getProductName method()");
		LogManager.debug(ENTER);
		final String query =  "select PRODUCT_NAME from PRODUCT_MASTER where PRODUCT_ID in (select productid from travel_header where quote_no = "+quoteNumber+") and branch_code = "+branchCode;
		final String productName = runner.singleSelection(query);
		LogManager.debug(EXIT);
		LogManager.popRemove();
		return productName;
	}
	public String isReferralQuote(final String qno)throws BaseException
	{
		LogManager.push("TravelBean isReferralQuote method()");
		LogManager.debug(ENTER);
		String sql;
		sql = "select nvl(remarks,'Normal') from home_position_master where quote_no=?";
		String args[] ={qno};
		String res;
		res = runner.singleSelection(sql,args);
		LogManager.debug(EXIT);
		LogManager.popRemove();
		return res;
	}
	public String[][] getClausesDetails(final String quoteNo,final String clausesStatus)throws BaseException
	{
		LogManager.push("TravelBean getClausesDetails method()");
		LogManager.debug(ENTER);
		String sql;
		sql =  "select RSACODE,PRM_SITYPE_CODE,replace(replace(replace(replace(value,',',''),'Excluded','0'),'Not Covered','0'),'Covered','0') from TRAVEL_CLAUSES_MASTER where PRODUCT_ID=(select PRODUCT_ID from home_position_master where quote_no=?) and status in("+clausesStatus+") and value not in('Not Covered') order by RSACODE";
		String args[] = {quoteNo};
		String[][] result;
		result = runner.multipleSelection(sql,args);
		LogManager.debug(EXIT);
		LogManager.popRemove();
		return result;
	}
	public String getTotalSumInusred(final String quoteNo,final String clausesStatus)throws BaseException
	{
		LogManager.push("TravelBean getTotalSumInusred method()");
		LogManager.debug(ENTER);
		String sql;
		sql = "select sum(replace(value,',','')) from TRAVEL_CLAUSES_MASTER where PRODUCT_ID=(select proposal_no from home_position_master where quote_no=?) and value not in('Not Covered','Covered') and status in("+clausesStatus+")";
		String args[] = {quoteNo};
		String res;
		res = runner.singleSelection(sql,args);
		if(res.length()>0){
			String args1[] = {res,quoteNo};
			runner.multipleUpdation("update TRAVEL_HEADER set TOTAL_SUM_INSURED=? where quote_no=?",args1);
		}
		LogManager.debug(EXIT);
		LogManager.popRemove();
		return res;
	}
	public String getPurchaseModeStatus(final String quoteNo)throws BaseException
	{
		LogManager.push("TravelBean getPurchaseModeStatus method()");
		LogManager.debug(ENTER);
		String purchaseMode = "";
		String sql = "";
		String count = "";
		String args[] = new String[1];
		args[0] = quoteNo;
		sql = "select count(*) from PAYMENT where MERCHTXNREF_POLICY_NO =?";
		count = runner.singleSelection(sql,args);
		if("1".equals(count)){
			purchaseMode = "D";
		}else{
			sql = "select (case when payment_mode='Cash' then 'C' else (case when payment_mode='Cheque' then 'Q' else (case when payment_mode='Credit Card' then 'D' else 'C' end)end)end) from home_position_master where quote_no=?";
			purchaseMode = runner.singleSelection(sql, args);
		}
		LogManager.debug(EXIT);
		LogManager.popRemove();
		return purchaseMode;
	}
	public String getClaimPhPolicyWord(final String branch, final String phWord)throws BaseException
	{
		LogManager.push("TravelBean getClaimPhPolicyWord method()");
		LogManager.debug(ENTER);
		String phoneWord;
		String sql;
		String args[] = new String[3];
			if(phWord.equalsIgnoreCase("ClaimPhone")){
				args[0] = "112";
				args[1] = "1";
				args[2] = branch;
			}else if(phWord.equalsIgnoreCase("PolicyWording")) {
				args[0] = "113";
				args[1] = "1";
				args[2] = branch;
			}
			sql = "select detail_name from constant_detail where category_id=? and category_detail_id=? and branch_code=?";
			phoneWord = runner.singleSelection(sql, args);
		LogManager.debug(EXIT);
		LogManager.popRemove();	
		return phoneWord;
	}
	public String getEndDate(String date,String daystoadd)throws BaseException
	{
		LogManager.push("TravelBean getEndDate method()");
		LogManager.debug(ENTER);
		 String returnDate="";
	        if(date != null && daystoadd != null){
	            int dt = Integer.parseInt(date);
	            Calendar cal = dateToCalendar(dt);
	            if(daystoadd.equals("Annual Cover")){
	                 cal.add(Calendar.MONTH,12);
	                 //cal.add(Calendar.DATE,-1);
	            }
	            else{
	                 int daysToAdd = Integer.parseInt(daystoadd);
	                 cal.add(Calendar.DATE,daysToAdd);
	            } 
	           returnDate = calendarToDate(cal);
	        }
        LogManager.debug(EXIT);
        LogManager.popRemove();	
        return returnDate;
	}
    public static Calendar dateToCalendar(int date) throws BaseException
    {
        int day    = date % 100;
        int month  = (date / 100) % 100-1;
        int year = date / 10000;
        Calendar cal = Calendar.getInstance();
        cal.set(year,month,day);
        cal.add(Calendar.DATE,-1);
        return cal;
   }
   public String calendarToDate(Calendar cal){
       String returndate;
       returndate = cal.get(Calendar.DATE)+"-"+months[(cal.get(Calendar.MONTH) + 1)]+"-"+cal.get(Calendar.YEAR);
       return returndate;
   }
   public String getTravelProductID(final String productId,final String branch){
	   String optids = "";
	   LogManager.info("Travel Product ID Query is => SELECT DETAIL_NAME FROM CONSTANT_DETAIL WHERE CATEGORY_ID=100 AND CATEGORY_DETAIL_ID='"+productId+"' AND BRANCH_CODE='"+branch+"' AND STATUS='Y';");
	   optids = runner.singleSelection("SELECT DETAIL_NAME FROM CONSTANT_DETAIL WHERE CATEGORY_ID=100 AND CATEGORY_DETAIL_ID='"+productId+"' AND BRANCH_CODE='"+branch+"' AND STATUS='Y'");
	   return optids;
   }
   
   
   public String[][] getSchemeCovers(final String productId,final String coverId) throws BaseException
   {
	   String[][] result = new String[0][0];	   
	   try{
		   String tableName = TestRunner.singleSelection("select pfm.table_name from product_field_master_new pfm where pfm.rating_factor_id = (select RATING_FACTOR_ID from home_cover_master where product_id = "+productId+" and COVER_ID = "+coverId+" ) and pfm.status='Y'");
		   String query = "Select * from "+tableName;		   
		   result = TestRunner.multipleSelection(query);
	   }
	   catch(Exception ex){
		   ex.printStackTrace();
	   }			   
	   return result ;
   }
   
   
   public String[][] getSchemeCoversFirst(final String tableName,final String branchCode,final String productCode) throws BaseException
   {
	   String[][] result = new String[0][0];
	   
	   try{
		   String query = "SELECT SCHEME_ID,SCHEME_NAME FROM "+tableName+" WHERE BRANCH_CODE='"+branchCode+"' AND STATUS='Y' AND SCHEME_ID IN ( SELECT SCHEME_ID FROM TRAVEL_SCHEME_OPTION_COVERS WHERE PRODUCT_CODE="+productCode+" AND BRANCH_CODE='"+branchCode+"' AND STATUS='Y' )";		   
		   result = TestRunner.multipleSelection(query);
	   }
	   catch(Exception ex){
		   ex.printStackTrace();
	   }			   
	   return result ;
   }
   
   public String[][] getSchemeCoversSecond(final String branchCode,final String schemeId,final String productCode) throws BaseException
   {
	   final String tableName="TRAVEL_OPTION_COVERS";
	   String[][] result = new String[0][0];
	   
	   try{
		   String query = "SELECT OPTION_ID,OPTION_NAME FROM "+tableName+" WHERE BRANCH_CODE='"+branchCode+"' AND STATUS='Y' AND OPTION_ID IN ( SELECT OPTION_ID FROM TRAVEL_SCHEME_OPTION_COVERS WHERE BRANCH_CODE='"+branchCode+"' AND STATUS='Y' AND SCHEME_ID="+schemeId+" AND PRODUCT_CODE="+productCode+" )";		   
		   result = TestRunner.multipleSelection(query);
	   }
	   catch(Exception ex){
		   ex.printStackTrace();
	   }			   
	   return result ;
   }
   
   public String[][] getSchemeCoversRadio(final String tableName,final String branchCode,final String schemeId,final String optionId,final String productCode) throws BaseException
   {
	   String[][] result = new String[0][0];
	   try{
		   String query = "SELECT COVERAGES_ID,COVERAGES_VALUE,COVERAGES_NAME FROM "+tableName+" WHERE BRANCH_CODE='"+branchCode+"' AND STATUS='Y' AND COVERAGES_ID IN ( SELECT COVERAGES_ID FROM TRAVEL_LINK_COVERAGES WHERE BRANCH_CODE='"+branchCode+"' AND STATUS='Y' AND COVER_ID IN ( SELECT COVER_ID FROM TRAVEL_SCHEME_OPTION_COVERS WHERE BRANCH_CODE='"+branchCode+"' AND STATUS='Y' AND SCHEME_ID="+schemeId+" AND OPTION_ID="+optionId+" AND PRODUCT_CODE="+productCode+" ) AND PRODUCT_CODE="+productCode+" ) ORDER BY COVERAGES_ID ";		   
		   result = TestRunner.multipleSelection(query);
	   }
	   catch(Exception ex){
		   ex.printStackTrace();
	   }			   
	   return result ;
   }
   

   public String getRatingFactorSubName(final String coverId,final String productId)
   {
	         String name = "";
	         try{
	        	 name = TestRunner.singleSelection("select rating_factor_sub_name from home_cover_master where product_id = "+productId+" and cover_id = "+coverId);
	         }
	         catch(Exception ex){
	        	 ex.printStackTrace();
	         }
	         return name;
   }
   
   public String getRatingClaimTypeId(final String productId)
   {
	   String claimId = "";
	   try{
		   claimId = TestRunner.singleSelection("select claim_type_id from claim_type where product_id = "+productId +" and status = 'Y'");
	   }
	   catch(Exception ex){
		   ex.printStackTrace();
	   }
	   return claimId;
	   
   }
   
   public String getSchemeId(final String productId,final String coverId,final String coverName) throws BaseException
   {
	   String result = "";	   
	   try{
		   String tableName = TestRunner.singleSelection("select pfm.table_name from product_field_master_new pfm where pfm.rating_factor_id = (select RATING_FACTOR_ID from home_cover_master where product_id = "+productId+" and COVER_ID = "+coverId+" ) and pfm.status='Y'");
		   String query = "Select SNO__ from "+tableName +" where SCHEME_COVER_NAME = '"+coverName+"'";		   
		   result = TestRunner.singleSelection(query);
	   }
	   catch(Exception ex){
		   ex.printStackTrace();
	   }			   
	   return result ;
   }
   
   public String getSchemeCoverId(final String quoteno,final String scheme) throws BaseException
   {
	   String result = "";	   
	   try{
		   /*  String schemename = runner.singleSelection("select scheme_cover from travel_header  where quote_no = "+quoteno);
		   String query = "select sno__ from scheme_covers where scheme_cover_name in ('" + schemename + "')";*/
		   String query="SELECT TSOC.COVER_ID FROM TRAVEL_SCHEME_OPTION_COVERS TSOC,TRAVEL_HEADER TH WHERE TSOC.SCHEME_ID=TH.SCHEME_ID AND TSOC.OPTION_ID=TH.OPTION_ID AND TSOC.PRODUCT_CODE=TH.PRODUCTID AND TH.QUOTE_NO='"+quoteno+"'";
		   result = TestRunner.singleSelection(query);
		 /*  if("nameonly".equalsIgnoreCase(scheme)){
			   result = schemename;
		   }*/
	   }
	   catch(Exception ex){
		   ex.printStackTrace();
	   }			   
	   return result ;
   }
   
   public String getProductValue()
   {
	String result="";   
	result=(String)getSess().getAttribute("product_id");
	return result;
   }

public HttpSession getSess() {
	return sess;
}

public void setSess(HttpSession sess) {
	this.sess = sess;
}
} 