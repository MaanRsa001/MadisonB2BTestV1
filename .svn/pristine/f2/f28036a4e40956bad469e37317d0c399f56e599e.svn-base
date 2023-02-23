package com.maan.admin.DAO;

import com.maan.common.LogManager;
import com.maan.services.util.runner;
import com.maan.Home.DataManipualtion.HomeValidationBean;
import com.maan.common.exception.BaseException;

public class AdminCommonBean
{
	private transient String query = "";
	final private transient static String ENTER = "- Enter";
	final private transient static String EXIT ="- Exit";
	final private transient static String BROKER = "Broker";
	final private transient static String USER = "User";
	final private transient static String ALL = "all";
	final private transient static char SINGLECOMMA = ',';
	final private transient HomeValidationBean homeValid = new HomeValidationBean();
	
	
	public String getBrokerCodesQuery(String brokerCodes,final String loginBranch,final String broktype)
	{
		LogManager.push("getBrokerCodesQuery method()");
		LogManager.debug(ENTER);
		String syntax = "";
		if("".equalsIgnoreCase(brokerCodes)){
			syntax = "select agency_code from broker_company_master where branch_code in("+loginBranch+") and agency_code in(select oa_code from login_master where login_id='"+broktype+"')";
		}
		else{
			brokerCodes = brokerCodes.replaceAll(",","','");
			String syntax1 = "'"+brokerCodes+"'";
			syntax = "select agency_code from broker_company_master where branch_code in("+loginBranch+") and agency_code in(select oa_code from login_master where login_id='"+broktype+"') and agency_code in("+syntax1+")";
		}
		LogManager.debug(EXIT);
		LogManager.popRemove();
		return syntax;
	}
	
	public String getAdminBrokerCodesQuery(String brokerCodes,final String branchCode)
	{
		LogManager.push("getAdminBrokerCodesQuery method()");
		LogManager.debug(ENTER);
		String syntax = "";
		if("".equalsIgnoreCase(brokerCodes)){
			syntax = "select agency_code from broker_company_master where branch_code in("+branchCode+")";
		}
		else{
			brokerCodes = brokerCodes.replaceAll(",","','");
			syntax = "'"+brokerCodes+"'";
		}
		LogManager.debug(EXIT);
		LogManager.popRemove();
		return syntax;
	}
	
	public String[][] getBrokerUserPersonalInfo(final String userType,final String broktype) throws BaseException
	{
		LogManager.push("getBrokerUserPersonalInfo method(2)");
		LogManager.debug(ENTER);
		if(userType != null  && BROKER.equalsIgnoreCase(userType)){
        	query = "select application_id,login_id,agency_code,oa_Code,first_name from personal_info where oa_code=(select oa_code from login_master where login_id=?) and login_id!='NONE' and application_id in ('2','3')";
		}
		else if(userType!=null && USER.equalsIgnoreCase(userType)){
			query = "select application_id,login_id,agency_code,oa_code,first_name from personal_info where agency_code=(select agency_code from login_master where login_id=?) and login_id!='NONE' and application_id in ('2','3')";
		}
		final String args[] = {broktype};
		final String values[][] = runner.multipleSelection(query,args);
		LogManager.debug(EXIT);
		LogManager.popRemove();
		return values;
	}
	
	public String[][] getBrokerUserPersonalInfo(final String userType,final String broktype,final String syntax) throws BaseException
	{
		LogManager.push("getBrokerUserPersonalInfo method(3)");
		LogManager.debug(ENTER);
		if(userType != null && BROKER.equalsIgnoreCase(userType)){
		   query = "select application_id,login_id,agency_code,oa_Code,first_name from personal_info where oa_code in("+syntax+") and login_id!='NONE' and application_id in ('2','3')";
		 }
		else if(userType!=null && USER.equalsIgnoreCase(userType)){
			query = "select application_id,login_id,agency_code,oa_code,first_name from personal_info where agency_code=(select agency_code from login_master where login_id='"+broktype+"') and login_id!='NONE' and application_id in ('2','3')";
		}
		//final String args[] = {broktype};
		final String values[][] = runner.multipleSelection(query);
		LogManager.debug(EXIT);
		LogManager.popRemove();
		return values;
	}
	
	public String getAllHomeTravelPids(final String adminPids,String loginBranch) throws BaseException
	{
		LogManager.push("getAllHomeTravelPids method()");
		LogManager.debug(ENTER);
		String proids[][] = new String[0][0];
		if(ALL.equalsIgnoreCase(adminPids) || adminPids.length() <= 0){
			proids = runner.multipleSelection("select product_id from HOME_PRODUCT_MASTER where status='Y' order by product_id");
		}
		else
		{
			if(loginBranch.indexOf('\'')!=-1){
				loginBranch = loginBranch.replaceAll("'","");
			}
			query = "select product_id from HOME_PRODUCT_MASTER where status='Y' and (product_id in("+adminPids+") or product_id in (select PRODUCT_ID from HOME_PRODUCT_MASTER where BROKER_ID in(select REMARKS from PRODUCT_MASTER where PRODUCT_ID in(select PRODUCT_ID from HOME_PRODUCT_MASTER where BROKER_ID is not null and PRODUCT_ID in("+adminPids+")) and BRANCH_CODE=?))) order by product_id";
			final String args[] = {loginBranch};
			proids = runner.multipleSelection(query,args);
		}
		final String produc_ids = homeValid.removeLastChar(homeValid.getStringFromArray(proids),SINGLECOMMA);
		LogManager.debug(EXIT);
		LogManager.popRemove();
		return produc_ids;
	}

	public String getAllHomeTravelPids() throws BaseException
	{
		LogManager.push("getAllHomeTravelPids method()");
		LogManager.debug(ENTER);
		final String proids[][] = runner.multipleSelection("select product_id from HOME_PRODUCT_MASTER where status='Y' order by product_id");
		final String produc_ids = homeValid.removeLastChar(homeValid.getStringFromArray(proids), SINGLECOMMA);
		LogManager.debug(EXIT);
		LogManager.popRemove();
		return produc_ids;
	}
	
	public String getUserTypeInformation(final String login) throws BaseException
	{
    	LogManager.push("getUserTypeInformation method()");
		LogManager.debug(ENTER);
		final String args[] = {login};
		final String userType = runner.singleSelection("select USERTYPE from login_master where login_id=?",args);
		LogManager.debug(EXIT);
        LogManager.popRemove();
        return userType;
    }
	
	public String getAllTravelPids(final String pid,String loginBranch) throws BaseException
	{
		LogManager.push("getAllTravelPids method()");
		LogManager.debug(ENTER);
		if(loginBranch.indexOf('\'')!=-1){
			loginBranch = loginBranch.replaceAll("'","");
		}
		final String args[] = {pid,loginBranch};
		final String valuess[][] = runner.multipleSelection("select PRODUCT_ID from HOME_PRODUCT_MASTER where BROKER_ID=(select REMARKS from PRODUCT_MASTER where PRODUCT_ID=? and BRANCH_CODE=?)",args);
		final String result = homeValid.removeLastChar(homeValid.getStringFromArray(valuess), SINGLECOMMA);
		LogManager.debug(EXIT);
        LogManager.popRemove();
		return result;
	}
	
	public String getAgencyCode(final String logpersonId) throws BaseException
	{
    	LogManager.push("getAgencyCode method()");
		LogManager.debug(ENTER);
		final String args[] = {logpersonId};
        final  String agencyCode=runner.singleSelection("select agency_code from login_master where login_id=?",args);
		LogManager.debug(EXIT);
		LogManager.popRemove();
        return agencyCode;
    }
} // Class