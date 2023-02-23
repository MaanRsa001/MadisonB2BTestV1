package com.maan.common.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;

import com.maan.Motor.controller.MotorBean;
import com.maan.common.DBConstants;
import com.maan.common.LogManager;
import com.maan.common.MyJdbcTemplate;
import com.maan.common.SimpleLogger;
import com.maan.common.exception.BaseException;
import com.maan.dao.ApiCaller.ApiForCommon;


public class CommonDAO extends MyJdbcTemplate 
{
	ApiForCommon commonApi = new ApiForCommon();
	
	public String sql="";

	public List getTypeInfo(String typeId,String campaignId, String partnerId)
	{
		LogManager.info("getTypeInfo - Enter");
		List list=null;

		try {
			sql = "SELECT DESTINATION_TABLE FROM XL_MOVEMENT_DETAIL WHERE MOVEMENT_ID='1' and product_id=? and campaign_id= ? and partner_id=? and status='Y'";
			list=this.mytemplate.queryForList(sql, new String[]{typeId,campaignId,partnerId});
		} catch (Exception e) {

			LogManager.debug("EXCEPTION @ { "+e+" }");
		}
		LogManager.info("getTypeInfo - Exit || Result: "+list.size());
		LogManager.popRemove();
		return list;
	}



	public String insertTransactionDetails(String loginId, String fileName, String folderPath, String movementDetailId, String tranId2,String openCoverNo, String transactionDesc, String issuer) 
	{
		String resultTranId = "";
		int count = this.mytemplate.queryForInt("select count(*) from XL_TRANSACTION_DETAILS where transaction_id ='"+tranId2+"'");
		if(count==0){
			//int tranId=this.mytemplate.queryForInt("SELECT TRANSACTION_NO.NEXTVAL FROM DUAL");

			String args[]=new String[8];
			args[0] = tranId2+"";
			args[1] = loginId;
			args[2] = fileName;
			args[3] = folderPath;
			args[4] = movementDetailId;
			args[5] = openCoverNo;
			args[6] = transactionDesc;
			args[7] = issuer;
			sql="INSERT INTO XL_TRANSACTION_DETAILS (TRANSACTION_ID,TRANSACTION_DATE,USER_NAME,FILE_NAME,PATH,ACTIVE,MOVEMENT_DETAIL_ID,OPENCOVER_POLICY_NUMBER,TRANSACTION_DESCRIPTION,ISSUER) " +
			" VALUES(?,SYSDATE,?,?,?,'Y',?,?,?,?)";
			if(!tranId2.equalsIgnoreCase("")){
				int result=this.mytemplate.update(sql,args);
				System.out.println("insertTransactionDetails() - Exit || Result: "+result+" Tran Id: "+tranId2);

			}
			resultTranId=tranId2+"";
		}
		else
		{
			//sql= "delete from XL_OPENCOVER_RAW WHERE TRANSACTION_ID=?";
			//this.mytemplate.update(sql,new Object[]{tranId2});
			resultTranId=tranId2;
		}
		return resultTranId;
	}


	public List getMovements(String product, String partner, String campaign) {

		List movements = null;
		String sql = "SELECT MOVEMENT_DETAIL_ID FROM XL_MOVEMENT_DETAIL WHERE MOVEMENT_ID<>1 AND PRODUCT_ID=? AND PARTNER_ID=? AND CAMPAIGN_ID=?  AND STATUS='Y' AND MOVEMENT_TYPE='A' ORDER BY PRIORITY ASC";
		movements= this.mytemplate.queryForList(sql,new Object[]{product,partner,campaign});
		return movements;
	}
	public List getExcelMovement(String product, String partner, String campaign) {

		//LogManager.info("getExcelMovement");
		List movements = null;
		String sql = "SELECT MOVEMENT_DETAIL_ID FROM XL_MOVEMENT_DETAIL WHERE MOVEMENT_ID=1 AND PRODUCT_ID=? AND PARTNER_ID=? AND CAMPAIGN_ID=?  AND STATUS='Y' ORDER BY PRIORITY ASC";
		movements= this.mytemplate.queryForList(sql,new Object[]{product,partner,campaign});
		//LogManager.info("getExcelMovement query "+ sql);
		return movements;
	}
	/*public synchronized String getPolicyNo(String branchCode,String productId,String type) throws BaseException {
		LogManager.push("getPolicyNo method() Enter");
		String maxId = null;
		String preFix = null;
		try{
			Object obj[] = new Object[]{branchCode, productId, branchCode, productId};
			String sql=getQuery("GET_MAX_ID1")+" " +type+" "+getQuery("GET_MAX_ID2");
			LogManager.info("Query=>"+sql);
			LogManager.info("obj[] ==> "+StringUtils.join(obj, ","));
			Map polMap=this.mytemplate.queryForMap(sql, obj);
			preFix=(polMap.get("PREFIX")==null?"":polMap.get("PREFIX").toString()).trim();
			maxId =(polMap.get("MAXNO")==null?"":polMap.get("MAXNO").toString()).trim(); 
			LogManager.info("Result=>"+maxId);
			sql=getQuery("UPD_MAX_ID1")+" " +type+" "+getQuery("UPD_MAX_ID2");
			LogManager.info("Query=>"+sql);
			obj = new Object[]{maxId,maxId,branchCode, productId, branchCode, productId};
			LogManager.info("obj[] ==> "+StringUtils.join(obj, ","));
			int res=this.mytemplate.update(sql,obj);
			LogManager.info("Result=>"+res);
		}catch(Exception e)
		{
			LogManager.debug(e);
		}
		LogManager.popRemove();
		LogManager.push("getPolicyNo method() Exit || maxId=>"+preFix+maxId);
		return preFix+maxId;
	}*/

	public synchronized String getMinimumPremium(String applicationNo) throws BaseException
	{
		LogManager.push("CommonDAO getMinimumPremium() Enter");
		String minPremium = null;
		try{
			sql=getQuery("GET_MINIUM_PREMIUM");
			Object[] obj=new Object[2];
			obj[0]=applicationNo;
			obj[1]=applicationNo;
			LogManager.info("Query=>"+sql);
			LogManager.info("Obj[0-2]=>"+StringUtils.join(obj,","));
			minPremium=(String)this.mytemplate.queryForObject(sql,obj,String.class);
			LogManager.info("Minimum Premium=>"+minPremium);
		}catch(Exception e)
		{
			LogManager.debug(e);
		}
		LogManager.popRemove();
		LogManager.push("CommonDAO getMinimumPremium() Exit || minPremium=>"+minPremium);
		return minPremium;
	}

	public synchronized double getPolicyFee(String applicationNo,String branchCode) throws BaseException
	{
		LogManager.push("CommonDAO getPolicyFee() Enter");
		double policyFee = 0.0;
		try{
			Object[] obj=new Object[2];
			obj[0]=applicationNo;
			obj[1]=branchCode;
			sql=getQuery("GET_POLICY_FEE");
			LogManager.info("Query=>"+sql);
			LogManager.info("Obj[0-1]=>"+StringUtils.join(obj,","));
			List li=this.mytemplate.queryForList(sql,obj);
			if(li!=null && li.size()>0)
			{
				Map map=(Map)li.get(0);
				policyFee=Double.parseDouble(map.get("POLICY_FEE").toString());
			}
			LogManager.info("policyFee=>"+policyFee);
		}catch(Exception e)
		{
			LogManager.debug(e);
		}
		LogManager.popRemove();
		LogManager.push("CommonDAO getPolicyFee() Exit || policyFee=>"+policyFee);
		return policyFee;
	}
	public String getCommision(final double premium,final String appNo)throws BaseException {
		LogManager.push("getCommision method() Enter||");
		double commision=0; 
		try{
			String sql=getQuery("GET_LOGIN_PROD_ID");
			LogManager.info("Query=>"+sql);
			LogManager.info("Obj[0]=>"+appNo);
			Map map=this.mytemplate.queryForMap(sql,new Object[]{appNo});
			LogManager.info("Map Size=>"+map.size());
			sql=getQuery("GET_COMM");
			Object obj[] = {map.get("LOGIN_ID"),map.get("PROD_ID")};
			LogManager.info("Query=>"+sql);
			LogManager.info("Obj[0]=>"+map.get("LOGIN_ID"));
			LogManager.info("Obj[1]=>"+map.get("PROD_ID"));
			String comPer =(String)this.mytemplate.queryForObject(sql, obj,String.class);
			LogManager.info("Result=>"+comPer);
			if (0==Double.parseDouble(comPer)){
				commision = 0.0;
			}
			else{
				commision = premium * (Double.parseDouble(comPer) / 100.0);
			}
		}catch(Exception e)
		{
			LogManager.debug(e);
		}
		LogManager.info("getCommision method() Exit|| Commision=>"+commision);
		return commision+"";
	}

	public synchronized String getReferralYN(String loginId) throws BaseException
	{
		LogManager.push("getPolicyNo getReferralYN() Enter");
		String refYN = "";
		try{
			sql=getQuery("GET_REFERAL_YN");
			Object[] obj=new Object[1];
			obj[0]=loginId;
			LogManager.info("Query=>"+sql);
			LogManager.info("Obj[0]=>"+loginId);
			refYN=(String)this.mytemplate.queryForObject(sql, obj,String.class);
			LogManager.info("Result=>"+refYN);
		}catch(Exception e)
		{
			LogManager.debug(e);
		}
		LogManager.popRemove();
		LogManager.push("getPolicyNo getReferralYN() Exit || ReferralYN=>"+refYN);
		return refYN;
	}

	public List<Object> getOptionsList(String option, Object[] objects)
	{
		LogManager.info("getOptionsList - Enter || option: "+option);
		List<Object> list=new ArrayList<Object>();
		try
		{
			sql=getQuery(DBConstants.OPTION);
			objects[0]=option;
			LogManager.info("sql==>"+sql);
			removeNull(objects);
			LogManager.info("args[] ==> "+StringUtils.join(objects, ","));
			list = this.mytemplate.queryForList(sql, objects);
		}catch(Exception e)
		{
			LogManager.debug("Exception @ "+e);
		}
		LogManager.popRemove();
		LogManager.info("getOptionsList - Exit || Result: "+list.size());
		return list;	
	}
	
	public List<Object> getOptionsList(String option, String productId, String type,MotorBean bean) {
		List<Object> list = new ArrayList<Object>();
		
		try {
			list = commonApi.getOptionsList(option,productId,type,bean);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
		
		
//		
//		LogManager.info("getOptionsList - Enter || option: "+option);
//		List<Object> list=new ArrayList<Object>();
//		try {
//			String sql = "";
//			if("broker".equalsIgnoreCase(option)){
//				sql=getQuery("issuer.broker");				
//			}else if("PolicyExpirydate".equalsIgnoreCase(option)){
//				//sql="SELECT TO_CHAR(EXPIRY_DATE,'DD/MM/YYYY') CODE,TO_CHAR(EXPIRY_DATE,'DD/MM/YYYY') ||'(' || (TO_DATE(TO_CHAR(EXPIRY_DATE,'DD/MM/YYYY'),'DD/MM/YYYY')-TO_DATE(?,'DD/MM/YYYY')) || ' Days)' CODEDESC  FROM (SELECT EXPIRY_DATE  FROM (SELECT LAST_DAY(TO_DATE('01/01'||'/'||TO_CHAR(SYSDATE,'YYYY'),'DD/MM/YYYY') + LEVEL * 89) EXPIRY_DATE FROM DUAL CONNECT BY LEVEL <=100) WHERE TO_DATE(TO_CHAR(EXPIRY_DATE,'DD/MM/YYYY'),'DD/MM/YYYY') > TO_DATE(?,'DD/MM/YYYY')) WHERE ROWNUM <= CASE WHEN (TO_DATE('31/12'||'/'||TO_CHAR(SYSDATE,'YYYY'),'DD/MM/YYYY')-TO_DATE(?,'DD/MM/YYYY'))  >= 364 THEN 4 WHEN (TO_DATE('31/12'||'/'||TO_CHAR(SYSDATE,'YYYY'),'DD/MM/YYYY')-TO_DATE(?,'DD/MM/YYYY'))  < 365 THEN 5  END  ORDER  BY EXPIRY_DATE ASC";
//				sql="SELECT TO_CHAR(EXPIRY_DATE,'DD/MM/YYYY') CODE,TO_CHAR(EXPIRY_DATE,'DD/MM/YYYY') ||'(' || (TO_DATE(TO_CHAR(EXPIRY_DATE,'DD/MM/YYYY'),'DD/MM/YYYY')-TO_DATE(?,'DD/MM/YYYY')) || ' Days)' CODEDESC  FROM (SELECT EXPIRY_DATE  FROM (SELECT LAST_DAY(TO_DATE('01/01'||'/'||TO_CHAR(SYSDATE,'YYYY'),'DD/MM/YYYY') + LEVEL * 89) EXPIRY_DATE FROM DUAL CONNECT BY LEVEL <=100) WHERE TO_DATE(TO_CHAR(EXPIRY_DATE,'DD/MM/YYYY'),'DD/MM/YYYY') > TO_DATE(?,'DD/MM/YYYY')) WHERE ROWNUM <= CASE WHEN (TO_DATE('31/12'||'/'||TO_CHAR(SYSDATE,'YYYY'),'DD/MM/YYYY')-TO_DATE(?,'DD/MM/YYYY'))  >= 364 THEN 4 WHEN (TO_DATE('31/12'||'/'||TO_CHAR(SYSDATE,'YYYY'),'DD/MM/YYYY')-TO_DATE(?,'DD/MM/YYYY'))  < 365 THEN 4  END  ORDER  BY EXPIRY_DATE ASC";
//				objects=new Object[]{objects[3],objects[3],objects[3],objects[3]};
//			}
//			else {
//				if("65".equals(productId)) {
//					sql=getQuery(DBConstants.OPTION_MOTOR);
//				} else {
//					sql=getQuery(DBConstants.OPTION);
//				}
//				objects[0]=option;
//				
//				for (int i = 0; i < objects.length; i++) {
//					if(objects[i]==null){
//						objects[i]="";
//					}
//				}
//			}
//			LogManager.info("sql==>"+ queryFrammer(sql, objects));
//			list = this.mytemplate.queryForList(sql, objects);
//		}catch(Exception e)
//		{
//			LogManager.debug("Exception @ "+e);
//		}
//		LogManager.popRemove();
//		LogManager.info("getOptionsList - Exit || Result: "+list.size());
//		return list;	
//	}
	
	public int getCalculatedAge(String date)
	{
		LogManager.info("getCalculatedAge - Enter || Date=>"+date);
		int age=0;
		try
		{
			String sql=getQuery("GET_AGE_CALC");
			LogManager.info("Obj[0-3]=>"+date);
			Map map=this.mytemplate.queryForMap(sql,new Object[]{date,date,date,date});
			age=Integer.parseInt(map.get("YEARS").toString());
			LogManager.info("Query=>"+sql);
		}catch(Exception e)
		{
			LogManager.debug(e);
		}
		LogManager.info("getCalculatedAge - Exit Age=>"+age);
		return age;
	}
	public String getBackDatesAllowed(final String user,final String userType,final String productId, final String branchCode ,final String schemeId)
	{
		LogManager.info("getBackDatesAllowed - Enter");
		String result = "0";
		if (userType != null)
		{
			String sql="";
			if ("RSAUSER".equalsIgnoreCase(userType)||"Admin".equalsIgnoreCase(userType)||"RSAIssuer".equalsIgnoreCase(userType)){
				sql = getQuery("GET_CONSTRANCT_DTLS");
				LogManager.info("Query=>"+sql);
				LogManager.info("Obj[0]=>"+203);
				LogManager.info("Obj[1]=>"+branchCode);
				result = (String)this.mytemplate.queryForObject(sql,new Object[]{"203",branchCode}, String.class);
			}
			else {
				sql = getQuery("GET_BACK_DATE_ALLOWED");
				LogManager.info("Query=>"+sql);
				LogManager.info("Obj[0]=>"+user);
				LogManager.info("Obj[1]=>"+productId);
				if("30".equalsIgnoreCase(productId)){
					//sql+=" and scheme_id=?";
					//result =(String) this.mytemplate.queryForObject(sql, new Object[]{user,productId, schemeId},String.class);
					result =(String) this.mytemplate.queryForObject(sql, new Object[]{user,productId},String.class);
				}else
					result =(String) this.mytemplate.queryForObject(sql, new Object[]{user,productId},String.class);
			}
		}
		if (result == null){
			result = "0";
		}
		LogManager.info("getBackDatesAllowed - Exit");
		return result;
	}
	public synchronized String getSumInsuredLimit(String appNo,String loginId,String productId) throws BaseException
	{
		LogManager.push("CommonDAO getSumInsuredLimit() Enter");
		String siLimit = null;
		Object[] obj=new Object[2];
		try{
			if(StringUtils.isBlank(appNo))
			{
				sql=getQuery("GET_SI_LIMIT");
				obj[0]=loginId;
				obj[1]=productId;
			}else
			{
				sql=getQuery("GET_SI_LIMIT_APPNO");
				obj[0]=appNo;
				obj[1]=appNo;
			}
			LogManager.info("Query=>"+sql);
			LogManager.info("Obj[0-2]=>"+StringUtils.join(obj,","));
			siLimit=(String)this.mytemplate.queryForObject(sql,obj,String.class);
			LogManager.info("SI Limit=>"+siLimit);
		}catch(Exception e)
		{
			LogManager.debug(e);
		}
		LogManager.popRemove();
		LogManager.push("CommonDAO getSumInsuredLimit() Exit || SI Limit=>"+siLimit);
		return siLimit;
	}
	public Map<String,Object> getTodaysDate()
	{
		LogManager.info("getTodaysDate - Enter");
		Map<String,Object> todayDate=null;
		try{
			final String sql = getQuery("GET_TODAY_DT");
			LogManager.info("Query=>"+sql);
			todayDate =this.mytemplate.queryForMap(sql);
			LogManager.info("Map Size=>"+todayDate.size());
		}catch(Exception e)
		{
			LogManager.debug(e);
		}
		LogManager.info("getTodaysDate - Exit");
		return todayDate;
	}
	public void closeTrnDateCalculation(final String quoteNo,final String loginBranch,final String policy)throws BaseException{
		LogManager.info("closeTrnDateCalculation method() Enter");
		try{
			Date sysDates;
			Date sysDates1;
			Date closeDate;
			Date openDate;
			List closeDates;
			closeDates = getCloseDate(loginBranch,quoteNo,policy);
			sysDates = new Date();
			if(closeDates.size()>0){
				Map closeDatesMap=(Map)closeDates.get(0);
				sysDates1 = new Date(sysDates.getYear(), sysDates.getMonth(),sysDates.getDate());
				sysDates = sysDates1;
				closeDate = new Date(Integer.parseInt(closeDatesMap.get("CLOSE_YEARS").toString()) - 1900,Integer.parseInt(closeDatesMap.get("CLOSE_MONTHS").toString()) - 1, Integer
						.parseInt(closeDatesMap.get("CLOSE_DAYS").toString()));
				openDate = new Date(Integer.parseInt(closeDatesMap.get("OPEN_YEARS").toString()) - 1900,Integer.parseInt(closeDatesMap.get("OPEN_MONTHS").toString()) - 1, Integer
						.parseInt(closeDatesMap.get("OPEN_DAYS").toString()));
				if (!(sysDates.compareTo(openDate) >= 0&& sysDates.compareTo(closeDate) <= 0)) {
					String sql="";
					Object obj[] = new Object[3];
					obj[0] =closeDatesMap.get("OPEN_DAYS").toString()+ "/"+ (closeDatesMap.get("OPEN_MONTHS").toString()) + "/"+closeDatesMap.get("OPEN_YEARS").toString()+ " 01:01:01";
					obj[1] =closeDatesMap.get("OPEN_DAYS").toString()+ "/"+ (closeDatesMap.get("OPEN_MONTHS").toString()) + "/"+closeDatesMap.get("OPEN_YEARS").toString()+ " 01:01:01";

					if("HTOS".equalsIgnoreCase(policy)){
						sql=this.getQuery("UPD_HPMDATE_QUOTENO");
						obj[2] = quoteNo;
					}else{
						sql=this.getQuery("UPD_HPMDATE_POLICYNO");
						obj[2] = policy;
					}
					LogManager.info("sql=>"+sql);
					LogManager.info("obj[]=>"+StringUtils.join(obj,","));
					this.mytemplate.update(sql,obj);
				}
			}
		}catch(Exception e)
		{
			LogManager.debug(e);
		}
		LogManager.info("closeTrnDateCalculation method() Exit");
	}
	private List getCloseDate(final String loginBranch,final String quoteNo,final String policy)throws BaseException{
		LogManager.info("getCloseDate method() Enter");
		List closeDates=new ArrayList();
		try{
			String sql="";
			String pdtCoreCode = "";
			Object obj[] = new Object[2];
			obj[0] = quoteNo;
			obj[1] = loginBranch;
			sql=getQuery("GET_RSACODE");
			LogManager.info("Query=>"+sql);
			LogManager.info("obj[0]=>"+quoteNo);
			LogManager.info("obj[1]=>"+loginBranch);
			pdtCoreCode =(String)this.mytemplate.queryForObject(sql,obj,String.class);
			LogManager.info("Result=>"+pdtCoreCode);
			if("HTOS".equalsIgnoreCase(policy)){
				obj = new Object[3];
				obj[0] = loginBranch;
				obj[1] = pdtCoreCode;
				obj[2] = quoteNo;
				sql=getQuery("GET_CLOSE_TRNRSACODE");
				LogManager.info("Query=>"+sql);
				LogManager.info("Obj[0]=>"+StringUtils.join(obj,","));
				closeDates = this.mytemplate.queryForList(sql,obj);
			}else{
				obj = new Object[1];
				obj[0] = loginBranch;
				sql=getQuery("GET_CLOSE_TRN");
				LogManager.info("Query=>"+sql);
				LogManager.info("Obj[0]=>"+loginBranch);
				closeDates = this.mytemplate.queryForList(sql,obj);
			}
		}catch(Exception e)
		{
			LogManager.debug(e);
		}
		LogManager.info("getCloseDate method() Exit List Size=>"+closeDates.size());
		return closeDates;
	}
	public String getBrokerLoginId(String brokerCode)
	{
		LogManager.info("getBrokerLoginId - Enter||");
		String result=null;
		String sql="";
		LogManager.push("getBrokerLoginId");
		try{	
			sql=getQuery("quotation.brokerloginId");
			LogManager.info("Sql=>" + sql+"\nArgs =>"+brokerCode);				
			result=(String)this.mytemplate.queryForObject(sql,new String[]{brokerCode},String.class);			
		}
		catch (Exception e) {			
			LogManager.debug(e);
		}		
		LogManager.info("getBrokerLoginId - Exit||"+ result);
		LogManager.popRemove();		
		return result;
	}
	public List<Object> getCustomerSelectionList(String loginId,String searchBy,String searchValue,String openCoverNo)
	{
		LogManager.info("getCustomerSelectionList - Enter || "+loginId+","+searchValue);
		List<Object> customerList=new ArrayList<Object>();		
		searchValue=searchValue==null?"":searchValue;
		String sql="";
		try{
			if("".equalsIgnoreCase(searchValue)){
				if(StringUtils.isNotEmpty(openCoverNo) && !"0".equals(openCoverNo)){
					sql=getQuery("customer.opencover");
					sql+=" ORDER BY PI.FIRST_NAME";
					LogManager.info("SQL=>" + sql);
					LogManager.info("Args[]=>"+openCoverNo);
					customerList=this.mytemplate.queryForList(sql,new String[]{openCoverNo});
				}else{
					sql=getQuery("travel.customer.selection");
					sql+=" ORDER BY PI.FIRST_NAME";
					LogManager.info("SQL=>" + sql);
					LogManager.info("Args[]=>"+loginId);
					customerList=this.mytemplate.queryForList(sql,new String[]{loginId});}
			}else{
				if(StringUtils.isNotEmpty(openCoverNo) && !"0".equals(openCoverNo)){
					sql=getQuery("customer.opencover.byname");
					LogManager.info("SQL=>" + sql);
					LogManager.info("Args[]=>"+openCoverNo+","+searchValue);
					customerList=this.mytemplate.queryForList(sql,new String[]{openCoverNo,"%"+searchValue+"%"});
				}else{
					Object[] obj=new Object[]{loginId};
					sql=getQuery("customer.selection.common");
					if("custName".equalsIgnoreCase(searchBy)){
						sql+=" "+getQuery("customer.selection.byname");
						obj=new Object[]{loginId,"%"+searchValue+"%"};
					}else if("coreAppCode".equalsIgnoreCase(searchBy)){
						sql+=" "+getQuery("customer.selection.coreAppCode");
						obj=new Object[]{loginId,"%"+searchValue+"%"};
					}else if("mobileNo".equalsIgnoreCase(searchBy)){
						sql+=" "+getQuery("customer.selection.mobileNo");
						obj=new Object[]{loginId,"%"+searchValue+"%"};
					}else if("email".equalsIgnoreCase(searchBy)){
						sql+=" "+getQuery("customer.selection.email");
						obj=new Object[]{loginId,"%"+searchValue+"%"};
					}
					LogManager.info("SQL=>" + sql);
					LogManager.info("Obj[]=>"+StringUtils.join(obj,","));
					customerList=this.mytemplate.queryForList(sql,obj);
				}
			}	
		}
		catch (Exception e) {			
			LogManager.debug("EXCEPTION @ { " + e + " }");
		}		
		LogManager.info("getCustomerSelectionList() - Exit Result=>"+ customerList.size() );
		LogManager.popRemove();		
		return customerList;
	}
	public String getSingleInfo(String option, String[] args)
	{
		LogManager.info("getSingleInfo - Enter || "+option+" args: "+ StringUtils.join(args, ","));
		String result="";					
		try{
			sql=getQuery(option);
			result=(String)this.mytemplate.queryForObject(sql,args, String.class);			
		}
		catch (Exception e) {			
			LogManager.debug("EXCEPTION @ { " + e + " }");
		}		
		LogManager.info("Query==>" + sql);
		LogManager.info("getSingleInfo() - Exit || Result: "+result );
		LogManager.popRemove();		
		return result;
	}
	
	public String getProductIdByAppNo(String applicationNo) {
		String productId = "";
		try {
			String query = "SELECT PRODUCT_ID FROM position_master PM WHERE APPLICATION_NO=? AND AMEND_ID=(SELECT MAX(AMEND_ID) FROM POSITION_MASTER PM1 WHERE PM1.APPLICATION_NO=PM.APPLICATION_NO)";
			productId = (String) this.mytemplate.queryForObject(query, new Object[]{applicationNo}, String.class);
		}
		catch(Exception exception) {
			LogManager.debug(exception);
		}
		return productId;
	}
	
	public String getUserMail(String loginId) {
		String userMail = "";
		try {
			String query = "SELECT USER_MAIL FROM LOGIN_MASTER WHERE LOGIN_ID=?";
			userMail = (String) this.mytemplate.queryForObject(query, new Object[]{loginId}, String.class);
		}
		catch(Exception exception) {
			LogManager.debug("Exception @ { " + exception + " } ");
		}
		return userMail;
	}
	
	public String getBrokerExecutiveId(String productId, String loginId, String openCoverNo) {
		String result = "";
		try {
			String query = "";
			Object[] args = null;
			if("11".equals(productId)) {
				query = getQuery("executive_oc");
				args =new Object[]{openCoverNo};
			}
			else {
				query = getQuery("executive");
				args =new Object[]{loginId};
			}
			result = (String) this.mytemplate.queryForObject(query, args, String.class);
		}
		catch(Exception exception) {
			LogManager.debug("Exception @ getBrokerExecutiveId() { " + exception + " } ");
		}
		return result;
	}
	public String calculatePolicyFee(String premium, String branchCode) {
		String result ="";
		try{
			final java.text.NumberFormat fmt1 = new java.text.DecimalFormat("0.00");
			String query = getQuery("GET_POLICYFEE_PERCENT");
			Object args[] = new Object[]{branchCode};
			String policyPercent = (String) this.mytemplate.queryForObject(query, args, String.class);
			double policyFee = (Double.valueOf(premium)*Double.valueOf(policyPercent))/100;
			result = fmt1.format(policyFee);
		}
		catch (Exception e) {
			LogManager.debug("Exception occured @ calculatePolicyFee{"+e+"}");
		}
		return result;
	}
	public synchronized String getSequenceNo(String type, String productId, String branchCode, String quoteNo, String customerId) {
		String result = "";
		try {
			String query = "";
			Object[] args = null;
			if("CUSTOMER_ID".equals(type)) {
				/*query = getQuery("GET_CUST_SEQ");
				args = new Object[]{applicationNo, customerId};*/
				query = getQuery("GET_CUST_ID_MAX");
			} else {
				query = getQuery("GET_SEQUENCE");
				args = new Object[]{quoteNo,branchCode,type,productId};
			}
			LogManager.info("Query==>" + queryFrammer(query, args));
			result = (String) this.mytemplate.queryForObject(query, args, String.class);
		} catch(Exception exception) {
			exception.printStackTrace();
		} finally {
			result = StringUtils.isBlank(result)?"":result;
		}
		
		return result;
	}
	/*public synchronized String getPolicyNo(String branchCode,String productId,String type) throws BaseException
	{
		LogManager.push("getPolicyNo method() Enter");
		String maxId = null;
		String preFix = null;
		try{
			Object obj[] = new Object[]{branchCode, productId, branchCode, productId};
			String sql=getQuery("GET_MAX_ID1")+" " +type+" "+getQuery("GET_MAX_ID2");
			LogManager.info("Query=>"+sql);
			LogManager.info("obj[] ==> "+StringUtils.join(obj, ","));
			Map polMap=this.mytemplate.queryForMap(sql, obj);
			preFix=(polMap.get("PREFIX")==null?"":polMap.get("PREFIX").toString()).trim();
			maxId =(polMap.get("MAXNO")==null?"":polMap.get("MAXNO").toString()).trim(); 
			LogManager.info("Result=>"+maxId);
			sql=getQuery("UPD_MAX_ID1")+" " +type+" "+getQuery("UPD_MAX_ID2");
			LogManager.info("Query=>"+sql);
			obj = new Object[]{maxId,maxId,branchCode, productId, branchCode, productId};
			LogManager.info("obj[] ==> "+StringUtils.join(obj, ","));
			int res=this.mytemplate.update(sql,obj);
			LogManager.info("Result=>"+res);
		}catch(Exception e)
		{
			LogManager.debug(e);
		}
		LogManager.popRemove();
		LogManager.push("getPolicyNo method() Exit || maxId=>"+preFix+maxId);
		return preFix+maxId;
	}*/
	public String getHomeQuoteNo(String policyNo) {
		String result = null;
		try {
			result = (String) this.mytemplate.queryForObject("SELECT QUOTE_NO FROM HOME_POSITION_MASTER WHERE POLICY_NO=? AND STATUS in ('P','D')",new String[]{policyNo},String.class);
		} catch(Exception exception) {
			LogManager.error(exception);
		} finally {
			result = result==null?"":result;
		}
		return 	result;
	}
	public String getHomeApplicationNo(String policyNo) {
		String result = null;
		try {
			result = (String) this.mytemplate.queryForObject("SELECT APPLICATION_NO FROM HOME_POSITION_MASTER WHERE POLICY_NO=? AND STATUS in ('P','D')",new String[]{policyNo},String.class);
		} catch(Exception exception) {
			LogManager.error(exception);
		} finally {
			result = result==null?"":result;
		}
		return 	result;
	}
	public String getHomePolicyNo(String quoteNo) {
		String result = "";
		try {
			result = (String) this.mytemplate.queryForObject("SELECT POLICY_NO FROM HOME_POSITION_MASTER WHERE QUOTE_NO=? ",new String[]{quoteNo},String.class);
		} catch(Exception exception) {
			LogManager.error(exception);
		} finally {
			result = result==null?"":result;
		}
		return 	result;
	}
	public String getHomeProductIdByQuoteNo(String quoteNo) {
		String result = null;
		try {
			result = (String) this.mytemplate.queryForObject("SELECT PRODUCT_ID FROM HOME_POSITION_MASTER WHERE QUOTE_NO=?",new String[]{quoteNo},String.class);
		} catch(Exception exception) {
			LogManager.debug(exception);
		} finally {
			result = result==null?"":result;
		}
		return 	result;
	}
	
	public String getHomeBranchCodeByQuoteNo(String quoteNo) {
		String result = null;
		try {
			result = (String) this.mytemplate.queryForObject("SELECT BRANCH_CODE FROM HOME_POSITION_MASTER WHERE QUOTE_NO=?",new String[]{quoteNo},String.class);
		} catch(Exception exception) {
			LogManager.debug(exception);
		} finally {
			result = result==null?"":result;
		}
		return 	result;
	}
	
	public String updateHomeQuoteStatus(String applicationNo, String merchant_reference,String quoteNo,String productId,String branchCode) {
		LogManager.info("updateQuoteStatus - Enter");
		String result="SUCCESS";
		try {
			String query = null;
			Object[] args = null;
			Map<String,Object> paymentDetails = new com.maan.payment.PaymentService().getPaymentDetails(merchant_reference);
			String paymentTypeId = paymentDetails.get("PAYMENT_TYPE")==null?"":paymentDetails.get("PAYMENT_TYPE").toString();
			String responseStatus = paymentDetails.get("RES_DECISION")==null?"":paymentDetails.get("RES_DECISION").toString();
			String mtnStatus = paymentDetails.get("RESPONSE_STATUS")==null?"":paymentDetails.get("RESPONSE_STATUS").toString();
			if(DBConstants.PAYMENT_CHEQUE.equals(paymentTypeId) || DBConstants.PAYMENT_CASH.equals(paymentTypeId) || DBConstants.PAYMENT_ZAMTEL.equals(paymentTypeId) || "ACCEPT".equalsIgnoreCase(responseStatus)) {
				query = getQuery("UPD_HPMSTATUS");
				args = new Object[2];
				args[0] = (DBConstants.PAYMENT_CHEQUE.equals(paymentTypeId) || DBConstants.PAYMENT_CASH.equals(paymentTypeId) || DBConstants.PAYMENT_ZAMTEL.equals(paymentTypeId))?"CCP":"SP";
				args[1] = applicationNo;
				LogManager.info("query==>"+query);
				LogManager.info("args[]==>"+StringUtils.join(args,", "));
				this.mytemplate.update(query, args);
				
				/*if("N".equalsIgnoreCase(checkPolicyGeneration(applicationNo))) {
					result = homePolicyGeneration(quoteNo,productId,branchCode);
					updatePolicyStatus(quoteNo,"P");
				}*/
			}
			else if ("101".equalsIgnoreCase(paymentTypeId) 
					&& ("success".equalsIgnoreCase(mtnStatus) || "successful".equalsIgnoreCase(mtnStatus))){
				LogManager.info("Enter into MTN Mobile Online Payment HPM Update");
				query = getQuery("UPD_HPMSTATUS");
				args = new Object[2];
				args[0] = "SP";
				args[1] = applicationNo;
				LogManager.info("query==>"+query);
				LogManager.info("args[]==>"+StringUtils.join(args,", "));
				this.mytemplate.update(query, args);
				LogManager.info("Exit from MTN Mobile Online Payment HPM Update");
				
			}else if("101".equalsIgnoreCase(paymentTypeId)){
				result="FAILED";
			}
			else if ("102".equalsIgnoreCase(paymentTypeId) 
					&& ("success".equalsIgnoreCase(mtnStatus) || "successful".equalsIgnoreCase(mtnStatus)) || "TS".equalsIgnoreCase(mtnStatus)){
				LogManager.info("Enter into Airtel Money Online Payment HPM Update");
				query = getQuery("UPD_HPMSTATUS");
				args = new Object[2];
				args[0] = "SP";
				args[1] = applicationNo;
				LogManager.info("query==>"+query);
				LogManager.info("args[]==>"+StringUtils.join(args,", "));
				this.mytemplate.update(query, args);
				LogManager.info("Exit from Airtel Money Online Payment HPM Update");
				
			}else if("102".equalsIgnoreCase(paymentTypeId)){
				result="FAILED";
			}
		} catch(Exception e) {
			LogManager.debug(e);
			result="FAILED";
		}
		LogManager.info("updateQuoteStatus - Exit");
		return result;
	}
	
	public String homePolicyGeneration(String quoteNo, String productId, String branchCode) {
		String result="SUCCESS";
		try {
			LogManager.info("policyGeneration method() Enter||");
			String policyNo = "";
			String receiptNo = "";
			String debitNo = "";
			
			Object obj[] = new Object[]{quoteNo};
			String sql=getQuery("GET_POLICY_STATUS");
			LogManager.info("Query=>"+sql);
			LogManager.info("Obj[]=>"+StringUtils.join(obj,","));
			final Map fromPosition = this.mytemplate.queryForMap(sql,obj);
			LogManager.info("Map Size=>"+fromPosition.size());
			if ("E".equalsIgnoreCase(fromPosition.get("STATUS").toString()) || "P".equalsIgnoreCase(fromPosition.get("STATUS").toString())) {
				policyNo = fromPosition.get("POLICY_NO").toString();
				receiptNo = fromPosition.get("RECEIPT_NO").toString();
				debitNo = fromPosition.get("DEBIT_NOTE_NO").toString();
			} else {
				/*//sql = getQuery("GET_POLICY_NO");
				obj = new Object[]{travelBean.getBranchCode(),travelBean.getProductId(),travelBean.getBranchCode(),travelBean.getProductId(),travelBean.getBranchCode(),travelBean.getProductId(),travelBean.getBranchCode(),travelBean.getProductId()};
				LogManager.info("Query=>"+sql);
				LogManager.info("Obj[]=>"+StringUtils.join(obj,","));
				Map brCodePrefix =  this.mytemplate.queryForMap(sql,obj);*/
				policyNo = new CommonDAO().getSequenceNo("Policy",productId,branchCode,quoteNo,"");
				receiptNo = new CommonDAO().getSequenceNo("Receipt",productId,branchCode,"","");
				debitNo = new CommonDAO().getSequenceNo("Debit",productId,branchCode,"","");
				
				
				try {
					String appId="";
					String userQry="SELECT LOGIN_ID,APPLICATION_ID FROM HOME_POSITION_MASTER WHERE QUOTE_NO=?";
					List<Map<String,Object>> usrDtl=this.mytemplate.queryForList(userQry,new Object[]{quoteNo});
					if(usrDtl!=null && usrDtl.size()>0)
						appId=usrDtl.get(0).get("APPLICATION_ID")==null?"":usrDtl.get(0).get("APPLICATION_ID").toString();
					if("1".equalsIgnoreCase(appId))
						policyNo=policyNo+"/B";
					else
						policyNo=policyNo+"/E";
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				if("65".equalsIgnoreCase(productId)){
					sql=getQuery("GET_MOTOR_DATA_DETAILS");
					LogManager.info("Query=>"+sql);
					LogManager.info("obj[] ==> "+quoteNo);
					List<Map<String,Object>> res = this.mytemplate.queryForList(sql,obj);
					if(res!=null && res.size()>0){
						for(int i=0;i<res.size();i++){
							if(StringUtils.isBlank(res.get(i).get("CERTIFICATE_NO")==null?"":res.get(i).get("CERTIFICATE_NO").toString())){
								String certificateNo =  new CommonDAO().getSequenceNo("MotorCertificate",productId,branchCode,"","");
								String vehicleId = res.get(i).get("VEHICLE_ID")==null?"":res.get(i).get("VEHICLE_ID").toString();
								sql=getQuery("UPD_MOTOR_CERTIFICATE_NO");
								obj=new Object[]{certificateNo,quoteNo,vehicleId};
								LogManager.info("Query=>"+sql);
								LogManager.info("obj[] ==> "+StringUtils.join(obj, ","));
								this.mytemplate.update(sql,obj);
							}
						}
					}
				}
			}
			sql= getQuery("GET_HOME_PAYMENT_MODE");
			Object args[]={quoteNo};
			LogManager.info("Query => "+sql);
			LogManager.info("Arguments  => "+StringUtils.join(args,","));
			String paymentMode = (String) this.mytemplate.queryForObject(sql,args,String.class);
			
			sql=getQuery("UPD_POLICY_NO");
			obj=new Object[5];
			obj[0] = policyNo;
			obj[1] = receiptNo;
			obj[2] = debitNo;
			obj[3] = paymentMode;
			obj[4] = quoteNo;
			LogManager.info("Query=>"+sql);
			LogManager.info("obj[] ==> "+StringUtils.join(obj, ","));
			int res=this.mytemplate.update(sql,obj);
			LogManager.info("Result=>"+res);
			new com.maan.common.dao.CommonDAO().closeTrnDateCalculation(quoteNo,branchCode,"HTOS");
			LogManager.info("policyGeneration method() Exit||");
		} catch(Exception e) {
			e.printStackTrace();
			result="FAILED";
		}
		LogManager.info("getGeratePolicy - Exit");
		return result;

	}
	public String checkPolicyGeneration(String applicationNo){
		String result="";
		try{
			String sql= getQuery("CHECK_POLICY_GENERATION");
			Object args[]={applicationNo};
			LogManager.info("Query => "+sql);
			LogManager.info("Arguments  => "+StringUtils.join(args,","));
			result = (String) this.mytemplate.queryForObject(sql,args,String.class);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	public void updatePolicyStatus(String quoteNo,String status){
		try{
			String sql= getQuery("UPD_PAYMENT_PROCESS_DETAILS");
			Object args[]={status,quoteNo};
			LogManager.info("Query => "+sql);
			LogManager.info("Arguments  => "+StringUtils.join(args,","));
			this.mytemplate.update(sql,args); 
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public void updateInstallmentDetail(String quoteNo,String type,String status) {
		String query="";
		try {
			if("ISS".equalsIgnoreCase(type)){
				query = getQuery("UPDATE_INSTALLMENT");
			}else if("IPS".equalsIgnoreCase(type)){
				query = getQuery("UPDATE_INSTALLMENT_PAYSTATUS");
			}else if("HSS".equalsIgnoreCase(type)){
				query = getQuery("UPDATE_INSTALLMENT_HOME_STATUS");
			}
				Object args[]={status==null?"N":status,quoteNo};
				LogManager.info("Query => " + query);
				LogManager.info("Arguments => "+StringUtils.join(args,","));
				removeNull(args);
				this.mytemplate.update(query,args );
				
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public String getInstallmentStatus(String quoteNo) {
		String emiStatus="";
		try {
				String query = getQuery("GET_INSTALLMENT_DETAIL");
				LogManager.info("Query => " + query);
				LogManager.info("Arguments => " + quoteNo);
				emiStatus=(String)this.mytemplate.queryForObject(query,new Object[] {quoteNo}, String.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return emiStatus;
	}
	public String getRequestedTime() {
		String reqTime = "";
		try {
			String query = "select to_char(systimestamp,'mm/dd/yyyy HH24:MI:SS.FF') request_time from dual";
			SimpleLogger.info("Query ==> "+ query);
			reqTime = (String) this.mytemplate.queryForObject(query, String.class);
		} catch (Exception e) {
			SimpleLogger.info("Error in getRequestedTime :: "+e);
		}
		return reqTime;
	}
	public List<Map<String,Object>> getOnlineSurveyor(){
		List<Map<String,Object>> result=null;
		try{
			String query=getQuery("GET_SURVEYOUR_LOGIN_DETAILS");
			SimpleLogger.info("Query => "+query);
			result = this.mytemplate.queryForList(query);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	public boolean validateDocument(String quoteNo, String vehicleId, String previousCompany,String productId) {
		boolean result=false;
		try{
			
			String sql = getQuery("GET_DOUMENT_LIST_VALIDATION", new Object[]{"65".equalsIgnoreCase(productId)?" and VTYPE_ID='"+vehicleId+"' ":""});
			Object[] args = {quoteNo};
			LogManager.info("Query==> " + sql);
			LogManager.info("Args==> " + StringUtils.join(args, ", "));
			List<Map<String,Object>> res=this.mytemplate.queryForList(sql,args);
			if(res!=null && res.size()>0){
				result= true;
			}else{
				result = false;
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	public Map<String,Object> commonIntgProcess(String quoteNo, String branchCode) {
		Map<String,Object> outputValues = null;
	try{
			LogManager.info("commonIntgProcess - Enter ||  quoteNo: "+quoteNo);
			SimpleJdbcCall procedure= mySimpleJdbcCall.withProcedureName("MOTOR_INTEGRATION");
	     	Map<String,Object> inputValues=new java.util.TreeMap<String, Object>(String.CASE_INSENSITIVE_ORDER);
	     	inputValues.put("pvquote", quoteNo);
	     	inputValues.put("pvbranch", branchCode);
	     	LogManager.info("Args==> " + inputValues.toString());
	     	outputValues = procedure.execute(new MapSqlParameterSource(inputValues));
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return outputValues;
	}
	
	public String updateRenewalRiskDtl(String quoteNo, String merchantRefNo,String policyNo,String modeOfPayment) {
		String result="SUCCESS";
		String query="";
		String status="";
		try {
			LogManager.info("updateRenewalRiskDtl method() Enter||");
			
			if("1".equalsIgnoreCase(modeOfPayment)||"2".equalsIgnoreCase(modeOfPayment)){
				status = "CCP";
			}else {
				status = "Y";
				LogManager.info("Renewal Integration Calling starts.. Online");
				Map <String ,Object> map=renewalIntgProcess(policyNo,quoteNo);
				LogManager.info("Renewal Integration pvOut => "+(String)map.get("PVOUT")==null?"":(String)map.get("PVOUT"));
			}
			query = getQuery("UPDATE_RENEWAL_POLICY");
			Object args[]={status,quoteNo,policyNo};
			LogManager.info("Query => " + query);
			LogManager.info("Arguments => "+StringUtils.join(args,","));
			removeNull(args);
			this.mytemplate.update(query,args );
			
			query = getQuery("UPDATE_RENEWAL_RISK");
			Object args1[]={merchantRefNo,status,quoteNo,policyNo};
			LogManager.info("Query => " + query);
			LogManager.info("Arguments => "+StringUtils.join(args1,","));
			removeNull(args1);
			this.mytemplate.update(query,args1);
			
			LogManager.info("updateRenewalRiskDtl method() Exit||");
		} catch(Exception e) {
			LogManager.debug(e);
			result="FAILED";
		}
		LogManager.info("getGeratePolicy - Exit");
		return result;

	}
	
	public Map<String, Object> renewalIntgProcess(String policyNo,String quoteNo) {
		Map<String,Object> outputValues = null;
		try{
				LogManager.info("renewalIntgProcess - Enter ||  policyNo: "+policyNo+" and Quote Num: "+quoteNo);
				SimpleJdbcCall procedure= mySimpleJdbcCall.withProcedureName("MOTOR_INTEGRATION_RENEWAL");
		     	Map<String,Object> inputValues=new java.util.TreeMap<String, Object>(String.CASE_INSENSITIVE_ORDER);
		     	inputValues.put("pvquoteno", quoteNo);
		     	inputValues.put("pvpolicy", policyNo);
		     	LogManager.info("Args==> " + inputValues.toString());
		     	outputValues = procedure.execute(new MapSqlParameterSource(inputValues));
			}
			catch (Exception e) {
				LogManager.info("Exception in renewalIntgProcess");
				e.printStackTrace();
			}
			return outputValues;
	}


	public String getPaymentType(String quoteNo) {
		String paymentTypeId = "";
		try {
			sql= getQuery("GET_HOME_PAYMENT_MODE");
			Object args[]={quoteNo};
			LogManager.info("Query => "+sql);
			LogManager.info("Arguments  => "+StringUtils.join(args,","));
			paymentTypeId = (String) this.mytemplate.queryForObject(sql,args,String.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return paymentTypeId;
	}

	public void getUpdPolicyNumStaus(String quoteNo) {
		try {
			String sql = getQuery("UPD_POLICY_NO_VEHICLE_ID");
			LogManager.info("Query => "+sql);
			String polyNo=getHomePolicyNo(quoteNo);
			LogManager.info("Args => "+polyNo+" , "+quoteNo);
			int value =this.mytemplate.update(sql,new Object[]{polyNo,quoteNo});
			System.out.println(value);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		
	}

	public void getUpdPolicyStaus(String status,String quoteNo) {
		try {
			String sql ="UPDATE HOME_POSITION_MASTER SET STATUS=? WHERE QUOTE_NO=?";
			LogManager.info("Query => "+sql);
			LogManager.info("Args => "+quoteNo +" , " +status);
			int value =this.mytemplate.update(sql,new Object[]{status,quoteNo});
			System.out.println(value);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
	}
	
	public void getUpdPaymentProcessStaus(String quoteNo) {
		try {
			String sql ="UPDATE PAYMENT_PROCESS_DETAIL SET TYPE='CC',STATUS='Y' WHERE QUOTE_NO=?";
			LogManager.info("Query => "+sql);
			LogManager.info("Args => "+quoteNo);
			int value =this.mytemplate.update(sql,new Object[]{quoteNo});
			System.out.println(value);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getQrcodeInfo(String quoteNo,String branchCode, String vehicleId) {
		LogManager.info("getQrcodeInfo - Enter ");
		List<Map<String, Object>> result=null;					
		try{
			//sql="SELECT A.*,TO_CHAR(SYSDATE+6/24,'dd/mm/yyyy') SYSDATE1,TO_CHAR(SYSDATE+6/24,'hh24:mi' ) HOURS FROM TABLE(MOTOR_SCHEDULE(?,'CERTIFICATE',?,?,?)) A";
			//Object args[]={quoteNo,branchCode,vehicleId,""};
			sql="SELECT MDD.CERTIFICATE_NO CERTIFICATE_NO, HPM.POLICY_NO POLICY_NO, REGISTRATION_NO VECHICLE_REG_NO ,TO_CHAR(HPM.INCEPTION_DATE,'dd-MON-yyyy hh24:mi:ss') ISSUE_DATE ,(SELECT TO_CHAR(POLICYENDDATE,'dd-MON-yyyy')||' 23:59:00 ' FROM MOTOR_POLICY_DETAILS WHERE QUOTENO=MDD.QUOTE_NO) EXPIRY_DATE FROM HOME_POSITION_MASTER HPM, MOTOR_DATA_DETAIL MDD ,MOTOR_CONDITION_MASTER MCM WHERE HPM.QUOTE_NO=? AND HPM.BRANCH_CODE=? AND MCM.POLICY_TYPE_ID=MDD.VEHICLE_TYPE AND CASE POLICY_TYPE_ID WHEN '1' THEN COREAPPCODE WHEN '2' THEN COREAPPCODE WHEN '3' THEN COREAPPCODE WHEN '4' THEN COREAPPCODE END= CASE POLICY_TYPE_ID WHEN '1' THEN '400105' WHEN '2' THEN '400108' WHEN '3' THEN '400112' WHEN '4' THEN '400105' END AND MDD.VEHICLE_ID=? AND MDD.QUOTE_NO=HPM.QUOTE_NO AND HPM.APPLICATION_NO=MDD.APPLICATION_NO AND HPM.AMEND_ID=(SELECT MAX(AMEND_ID) FROM HOME_POSITION_MASTER HOPM WHERE HOPM.APPLICATION_NO=HPM.APPLICATION_NO AND HOPM.QUOTE_NO=HPM.QUOTE_NO)";
			Object args[]={quoteNo,branchCode,vehicleId,};
			LogManager.info("Query ==> "+sql+" ||  args ==> "+ StringUtils.join(args, ","));
			result=this.mytemplate.queryForList(sql,args);			
		}
		catch (Exception e) {			
			LogManager.debug("EXCEPTION @ { " + e + " }");
		}		
		LogManager.info("Query==>" + sql);
		LogManager.info("getQrcodeInfo() - Exit ");
		LogManager.popRemove();		
		return result;
	}
	
	public List<Object> getUserBasedModeOfPayment(String userType) {
		List<Object> list = new ArrayList<Object>();
		try{
			if("Broker".equalsIgnoreCase(userType) || "RSAIssuer".equalsIgnoreCase(userType)){
				userType="USER";
			}
			String query = "SELECT ITEM_CODE CODE, ITEM_VALUE CODEDESC, ITEM_DESC CODEVALUE " +
					"FROM LIST_ITEM_VALUE WHERE ITEM_TYPE = ? AND STATUS = 'Y' ORDER BY ITEM_CODE";
			Object[] args = new Object[]{userType.toUpperCase()+"_PAYMENTTYPE"};
			LogManager.info("CommonDAO.getUserBasedModeOfPayment(): "+query+" Arguments: "+StringUtils.join(args,","));
			list = this.mytemplate.queryForList(query, args);
		}catch(Exception e){
			LogManager.info("Exception @ CommonDAO.getUserBasedModeOfPayment(): "+e);
			e.printStackTrace();
		}
		return list;
	}
	
	public void saveApiResponse(String transactionId, String apiDescription, String requestMethod, String apiUrl,
			String requestHeaders, String requestString, int responseStatus, String responseString, String errorResponse){
		try{
			String query = "INSERT INTO API_CALL_HISTORY(TRANSACTION_ID ,API_DESCRIPTION ,REQUEST_METHOD ,REQUEST_URL ," +
					" REQUEST_HEADERS ,REQUEST_STRING ,RESPONSE_STATUS ,RESPONSE_STRING ,ERROR_RESPONSE ,ENTRY_DATE) VALUES " +
					" (?,?,?,?,?,?,?,?,?,SYSDATE)";
			Object[] args = new Object[]{transactionId==null?"":transactionId.toString(), apiDescription==null?"":apiDescription.toString(),
					requestMethod==null?"":requestMethod.toString(), apiUrl==null?"":apiUrl.toString(),
					requestHeaders==null?"":requestHeaders.toString(), requestString==null?"":requestString.toString(),
					responseStatus, responseString==null?"":responseString.toString(),
					errorResponse==null?"":errorResponse.toString()};
			LogManager.info("CommonDAO.saveApiResponse() Query: "+query+" Args: '"+StringUtils.join(args,"','")+"'");
			this.mytemplate.update(query, args);
		}catch(Exception e){
			LogManager.info("Exception @ CommonDAO.saveApiResponse(): "+e);
			e.printStackTrace();
		}
	}

	public void saveJwtToken(String tokenFor, String tokenType,
			String accessToken, int expiresIn) {
		try{
			String query = "INSERT INTO JWT_TOKEN(TOKEN_FOR, TOKEN_TYPE, JWT_TOKEN, ENTRY_DATE, EXPIRY_DATE, EXPIRES_SECOND)" +
					" VALUES(?,?,?,SYSDATE,(SYSDATE+INTERVAL '"+(expiresIn>600?(expiresIn-600):expiresIn)+"' SECOND),?)";
			Object[] args = new Object[]{tokenFor, tokenType, accessToken, expiresIn};
			LogManager.info("CommonDAO.saveJwtToken() Query: "+query+" Args: "+StringUtils.join(args,","));
			this.mytemplate.update(query, args);
		}catch(Exception e){
			LogManager.info("Exception @ CommonDAO.saveJwtToken(): "+e);
			e.printStackTrace();
		}
	}



	public String getNumberOfDays(String inceptDt, String quoteNo) {
		String numOfDays="0";
		try {
			String qry="SELECT TRUNC(EXPIRY_DATE)-TRUNC(TO_DATE(?,'DD/MM/YYYY')) FROM HOME_POSITION_MASTER WHERE QUOTE_NO=?";
			Object[] args = new Object[]{inceptDt,quoteNo};
			numOfDays=(String) this.mytemplate.queryForObject(qry, args, String.class);
			LogManager.info("getNumberOfDays Query: "+queryFrammer(qry, args));
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return numOfDays;
	}



	public List<Map<String, Object>> getSubBranchList(String loginId,String mgenBranch) {
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		
		try {
			list = commonApi.getSubBranchList(loginId,mgenBranch);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
		
//		List<Map<String, Object>> res=new ArrayList<Map<String, Object>>();
//		try {
//			String query="SELECT BRANCH_ID , BRANCH_NAME, MGEN_BRANCH_ID FROM BROKER_BRANCH_MASTER WHERE BRANCH_ID IN "
//					+ "(SELECT DISTINCT(REGEXP_SUBSTR(LC_LOGIN,'[^,]+',1,LEVEL)) LC_LOGIN FROM "
//					+ "( SELECT SUB_BRANCH LC_LOGIN FROM LOGIN_MASTER WHERE LOGIN_ID=?) B "
//					+ "CONNECT BY LEVEL <= LENGTH(LC_LOGIN) - LENGTH(REPLACE(LC_LOGIN,',',''))+ 1 AND "
//					+ "LC_LOGIN IS NOT NULL) AND STATUS='Y' AND MGEN_BRANCH_ID=?";
//			
//			Object[] args = new Object[]{loginId,mgenBranch};
//			LogManager.info("getSubBranchList Query: "+queryFrammer(query, args));
//			res=this.mytemplate.queryForList(query,args);
//		} catch (DataAccessException e) {
//			e.printStackTrace();
//		}
//		
//		return res;
//	}



	public void updateBrokerBranch(String brokerBranch, String applicationNo, String quoteNo) {
		try {
			String query="UPDATE HOME_POSITION_MASTER SET BROKER_BRANCH=? WHERE APPLICATION_NO=?";
			Object[] args = new Object[]{brokerBranch,applicationNo};
			LogManager.info("updateBrokerBranch Query: "+queryFrammer(query, args));
			this.mytemplate.update(query,args);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
	}



	public String getLoginIdByQuote(String quoteNo) {
		String loginId="";
		try {
			String query="SELECT LOGIN_ID FROM HOME_POSITION_MASTER WHERE QUOTE_NO=?";
			Object args[] = new Object []{quoteNo};
			LogManager.info("getLoginIdByQuote Query: "+queryFrammer(query, args));
			loginId=this.mytemplate.queryForObject(query, args ,String.class).toString();
		} catch (Exception e) {
			loginId="";
		}
		return loginId;
	}



	public String checkIsB2C(String loginId) {
		String res="";
		try {
			String query="SELECT NVL(IS_B2C,'N') IS_B2C FROM LOGIN_MASTER WHERE LOGIN_ID=?";
			Object args[] = new Object []{loginId};
			LogManager.info("getLoginIdByQuote Query: "+queryFrammer(query, args));
			res=this.mytemplate.queryForObject(query, args ,String.class).toString();
		} catch (Exception e) {
		}
		return res;
	}



	public String getPaymentURL() {
		String res="";
		try {
			String query="SELECT NVL(PAYMENT_REDIR_URL,'') PAYMENT_REDIR_URL FROM PRODUCT_MASTER WHERE PRODUCT_ID=?";
			Object args[] = new Object []{"92"};
			LogManager.info("getPaymentURL Query: "+queryFrammer(query, args));
			res=this.mytemplate.queryForObject(query, args ,String.class).toString();
		} catch (Exception e) {
		}
		return res;
	}
	public List<Map<String, Object>> getBrokerUserList(String quoteNo,String mgenBranch) {
		List<Map<String, Object>> res=new ArrayList<Map<String, Object>>();
		try {
			String query=getQuery("issuer.broker.user");
			Object[] args = new Object[]{mgenBranch,quoteNo};
			LogManager.info("getSubBranchList Query: "+queryFrammer(query, args));
			res=this.mytemplate.queryForList(query,args);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		
		return res;
	}



	public void updateReferBroker(String brokeruser, String applicationNo, String quoteNo) {
		try {
			String query="UPDATE HOME_POSITION_MASTER SET APPLICATION_ID='1',LOGIN_ID=? WHERE APPLICATION_NO=?";
			Object[] args = new Object[]{brokeruser,applicationNo};
			LogManager.info("updateBrokerBranch Query: "+queryFrammer(query, args));
			this.mytemplate.update(query,args);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
	}

	public List<Object> getModelList(MotorBean bean) {
		List<Object> list = new ArrayList<Object>();
		
		try {
			list = commonApi.getModelList(bean);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}



	public List<Object> getMakeList(MotorBean bean) {
		List<Object> list = new ArrayList<Object>();
		
		try {
			list = commonApi.getMakeList(bean);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}



	public List<Object> getDeductibleList(String option, MotorBean bean) {
		List<Object> list = new ArrayList<Object>();
		
		try {
			list = commonApi.getDeductibleList(option,bean);
		} catch (Exception e) {
			e.printStackTrace();
		}
			return list;
	}


	public List<Object> getVehicleColourList(MotorBean bean) {
		List<Object> list = new ArrayList<Object>();
		
		try {
			list = commonApi.getVehicleColourList(bean);
		} catch (Exception e) {
			e.printStackTrace();
		}
			return list;
	}

	public List<Object> getBankOfFinanceList(MotorBean bean) {
		List<Object> list = new ArrayList<Object>();
		
		try {
			list = commonApi.getBankOfFinanceList(bean);
		} catch (Exception e) {
			e.printStackTrace();
		}
			return list;
	}

	public List<Object> getNoOfClaims(MotorBean bean) {
		List<Object> list = new ArrayList<Object>();
		try {
			list = commonApi.getNoOfClaims(bean);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}


	public List<Object> getNoClaimBonusList(MotorBean bean) {
		List<Object> list = new ArrayList<Object>();
		try {
			list = commonApi.getNoClaimBonusList(bean);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}


	public List<Object> insCompanyList(MotorBean bean) {
		List<Object> list = new ArrayList<Object>();
		try {
			list = commonApi.insCompanyList(bean);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}


	public List<Object> getCityList(MotorBean bean) {
		List<Object> list = new ArrayList<Object>();
		try {
			list = commonApi.getCityList(bean);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}


	public List<Object> getPolicyEndList(MotorBean bean) {
		List<Object> list = new ArrayList<Object>();
		try {
			list = commonApi.getPolicyEndList(bean);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}



	public List<Object> getBankNamelist(MotorBean bean) {
		List<Object> list = new ArrayList<Object>();
		
		try {
			list = commonApi.getBankNamelist(bean);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
}

