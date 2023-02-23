package com.maan.common.home;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.maan.common.LogManager;
import com.maan.common.MyJdbcTemplate;

public class HomeDAOImpl extends MyJdbcTemplate implements HomeDAO {
	
	public String getActualBranch(String user) {
		String result = "";
		try {
			String query = "SELECT NVL(BRANCH_CODE,'020') FROM BROKER_COMPANY_MASTER WHERE AGENCY_CODE=(SELECT OA_CODE FROM LOGIN_MASTER WHERE LOGIN_ID=?)";
			Object[] args = new Object[]{user};
			LogManager.info("Query==> " + query);
			LogManager.info("Args==> " + StringUtils.join(args, ", "));
			result = (String) this.mytemplate.queryForObject(query, args, String.class);
		} catch(Exception exception) {
			//LogManager.debug("Exception @ getActualBranch() " + exception);
		} finally {
			if(StringUtils.isBlank(result)) {
				String query = "select nvl(BRANCH_CODE,'020') from LOGIN_master where login_id=?";
				Object[] args = new Object[]{user};
				LogManager.info("Query==> " + query);
				LogManager.info("Args" + StringUtils.join(args, ", "));
				result = (String) this.mytemplate.queryForObject(query, args, String.class);
			}
		}
		return result;
	}
	
	public String getDefaultBranch(String loginType, String selectedBranch, String branchCode) {
		String result = "";
		try {
			String query = "SELECT DETAIL_NAME FROM CONSTANT_DETAIL WHERE CATEGORY_ID='116' AND CATEGORY_DETAIL_ID=1 AND STATUS='Y' and branch_code=?";
			Object[] args = new Object[]{branchCode};
			LogManager.info("Query==> " + query);
			LogManager.info("Args==> " + StringUtils.join(args, ", "));
			result = (String) this.mytemplate.queryForObject(query, args, String.class);
		} catch(Exception exception) {
			LogManager.debug("Exception @ getDefaultBranch() " + exception);
		} finally {
			result=!("Broker".equalsIgnoreCase(loginType) || "User".equalsIgnoreCase(loginType))?selectedBranch:result;
		}
		return result;
	}
	
	public Map<String,String> getBrokerUserDetails(String brokerBranch) {
		Map<String,String> finalMap = new HashMap<String,String>();
		try {
			String query = "SELECT CURRENCY_ABBREVIATION,ORIGINATION_COUNTRY_ID,DESTINATION_COUNTRY_ID,CURRENCY_ABBREVIATION,DECIMAL_PLACES,NVL(TAX,'0') TAX,EMAIL,LANG_YN FROM BRANCH_MASTER WHERE BRANCH_CODE=?";
			Object[] args = new Object[]{brokerBranch};
			LogManager.info("Query==> " + query);
			LogManager.info("Args==> " + StringUtils.join(args, ", "));
			Map<String,Object> resultMap = this.mytemplate.queryForMap(query, args);
			
			finalMap.put("Branch", brokerBranch);
			finalMap.put("CurrencyName",(resultMap.get("CURRENCY_ABBREVIATION")==null?"SAR":resultMap.get("CURRENCY_ABBREVIATION").toString()));
			finalMap.put("Orgination",(resultMap.get("ORIGINATION_COUNTRY_ID")==null?"1":resultMap.get("ORIGINATION_COUNTRY_ID").toString()));
			finalMap.put("Destination",(resultMap.get("DESTINATION_COUNTRY_ID")==null?"1":resultMap.get("DESTINATION_COUNTRY_ID").toString()));
			finalMap.put("CurrencyAbb",(resultMap.get("CURRENCY_ABBREVIATION")==null?"SAR":resultMap.get("CURRENCY_ABBREVIATION").toString()));
			finalMap.put("CurrencyDecimal",(resultMap.get("DECIMAL_PLACES")==null?"2":resultMap.get("DECIMAL_PLACES").toString()));
			finalMap.put("Tax",(resultMap.get("TAX")==null?"0":resultMap.get("TAX").toString()));
			finalMap.put("Site",(resultMap.get("EMAIL")==null?" ":resultMap.get("EMAIL").toString()));
			finalMap.put("LANG",(resultMap.get("LANG_YN")==null?" ":resultMap.get("LANG_YN").toString()));
		} catch(Exception exception) {
			LogManager.debug("Exception @ getDefaultBranch() " + exception);
		}
		return finalMap;
	}
		
	public List<Map<String,Object>> getProductDetails(String loginId, String userType, String branchCode) {
		List<Map<String,Object>> resultList = null;
		try {
			String query;
			Object[] args;
			if("Broker".equalsIgnoreCase(userType) || "User".equalsIgnoreCase(userType)) {
				query = "SELECT PRODUCT_ID, PRODUCT_NAME FROM PRODUCT_MASTER WHERE STATUS='Y' AND BRANCH_CODE=(SELECT   BELONGING_BRANCH FROM   BRANCH_MASTER BM WHERE   BRANCH_CODE = ? AND STATUS = 'Y'  AND AMEND_ID =(SELECT   MAX (AMEND_ID) FROM   BRANCH_MASTER BM1 WHERE   BM1.BRANCH_CODE = BM.BRANCH_CODE  AND BM1.STATUS = BM.STATUS)) AND PRODUCT_ID IN (SELECT PRODUCT_ID FROM LOGIN_USER_DETAILS WHERE AGENCY_CODE=(SELECT AGENCY_CODE FROM LOGIN_MASTER WHERE  LOGIN_ID=?) AND STATUS='Y') ORDER BY DISPLAY_ORDER ASC";
				args = new Object[]{branchCode,loginId};
				LogManager.info("Query==> " + query);
				LogManager.info("Args==> " + StringUtils.join(args, ", "));
				resultList = this.mytemplate.queryForList(query, args);
			} else {
				query = "SELECT PRODUCT_ID FROM LOGIN_MASTER WHERE LOGIN_ID=?";
				args = new Object[]{loginId};
				LogManager.info("Query==> " + query);
				LogManager.info("Args==> " + StringUtils.join(args, ", "));
				String productIds = (String) this.mytemplate.queryForObject(query, args, String.class);
				
				productIds=productIds.replaceAll(", ", ",");
				productIds=productIds.replaceAll(" ,", ",");
				productIds=productIds.replaceAll(",", "','");
				query = "SELECT PRODUCT_ID, PRODUCT_NAME FROM PRODUCT_MASTER WHERE STATUS='Y' AND BRANCH_CODE=(SELECT   BELONGING_BRANCH FROM   BRANCH_MASTER BM WHERE   BRANCH_CODE = ? AND STATUS = 'Y'  AND AMEND_ID =(SELECT   MAX (AMEND_ID) FROM   BRANCH_MASTER BM1 WHERE   BM1.BRANCH_CODE = BM.BRANCH_CODE  AND BM1.STATUS = BM.STATUS)) AND PRODUCT_ID IN ('" + productIds + "') ORDER BY DISPLAY_ORDER ASC";
				args = new Object[]{branchCode};
				LogManager.info("Query==> " + query);
				LogManager.info("Args" + StringUtils.join(args, ", "));
				resultList = this.mytemplate.queryForList(query, args);
			}
			
		} catch(Exception exception) {
			LogManager.debug("Exception @ getProductDetails() " + exception);
		}
		return resultList;
	}
	public List<Map<String,Object>> getOfficeProductScheme(String loginId, String branchCode) {
		List<Map<String,Object>> resultList = null;
		try {
			String query = "SELECT S.SCHEME_ID,(P.PRODUCT_NAME||' - '||S.SCHEME_NAME) SCHEME_NAME, P.PRODUCT_ID FROM PRODUCT_MASTER P,OFS_SCHEME_MASTER S WHERE P.STATUS=S.STATUS AND S.STATUS='Y' AND P.BRANCH_CODE=S.BRANCH_CODE AND S.BRANCH_CODE=? AND S.PRODUCT_ID=P.PRODUCT_ID AND S.SCHEME_ID IN (SELECT SCHEME_ID FROM LOGIN_USER_DETAILS WHERE SCHEME_ID IS NOT NULL AND AGENCY_CODE=(SELECT AGENCY_CODE FROM LOGIN_MASTER WHERE LOGIN_ID=?) AND STATUS='Y') ORDER BY S.SCHEME_ID";
			Object[] args = new Object[]{branchCode,loginId};
			LogManager.info("Query==> " + query);
			LogManager.info("Args==> " + StringUtils.join(args, ", "));
			resultList = this.mytemplate.queryForList(query, args);
		} catch(Exception exception) {
			LogManager.debug("Exception @ getOfficeProductScheme() " + exception);
		}
		return resultList;
	}
	
	public Map<String,String> getCustomerRights(String loginId, String userType) {
		Map<String,String> resultMap = new HashMap<String, String>();
		try {
			if("RSAIssuer".equalsIgnoreCase(userType) || "Broker".equalsIgnoreCase(userType) || "User".equalsIgnoreCase(userType)) {
				String query = "SELECT PRODUCT_ID,CUSTOMER_QUOTE,CUSTOMER_SCHEDULE,CUSTOMER_DEBIT,CUSTOMER_CUSTOMERDEBIT,CUSTOMER_POLICY,CUSTOMER_CERTIFICATE,FREIGHT_DEBIT_OPTION FROM LOGIN_USER_DETAILS WHERE LOGIN_ID=? ORDER BY PRODUCT_ID";
				Object[] args = new Object[]{loginId};
				LogManager.info("Query==> " + query);
				LogManager.info("Args==> " + StringUtils.join(args, ", "));
				List<Map<String,Object>> resultList = this.mytemplate.queryForList(query, args);
				for(Map<String,Object> tempMap:resultList) {
					String cusPid = tempMap.get("PRODUCT_ID")==null?"":tempMap.get("PRODUCT_ID").toString();
					resultMap.put("cusQuote"+cusPid, tempMap.get("CUSTOMER_QUOTE")==null?"":tempMap.get("CUSTOMER_QUOTE").toString());
					resultMap.put("cusSchedule"+cusPid, tempMap.get("CUSTOMER_SCHEDULE")==null?"":tempMap.get("CUSTOMER_SCHEDULE").toString());
					resultMap.put("cusDebit"+cusPid, tempMap.get("FREIGHT_DEBIT_OPTION")==null?"":tempMap.get("FREIGHT_DEBIT_OPTION").toString());
					resultMap.put("cusCusDebit"+cusPid, tempMap.get("CUSTOMER_CUSTOMERDEBIT")==null?"":tempMap.get("CUSTOMER_CUSTOMERDEBIT").toString());
					resultMap.put("policyOpt"+cusPid, tempMap.get("CUSTOMER_POLICY")==null?"":tempMap.get("CUSTOMER_POLICY").toString());
					resultMap.put("certificateOpt"+cusPid, tempMap.get("CUSTOMER_CERTIFICATE")==null?"":tempMap.get("CUSTOMER_CERTIFICATE").toString());
				}
			}
		} catch(Exception exception) {
			LogManager.debug("Exception @ getCustomerRights() " + exception);
		}
		return resultMap;
	}
	
	public boolean getIsBrokerHasDC(String loginId) {
		boolean status = false;
		try {
			String query = "select count(*) from LOGIN_USER_DETAILS where oa_code=(select oa_code from login_master where login_id=?) and CUSTOMER_CERTIFICATE='Y' and product_id='11'";
			Object[] args = new Object[]{loginId};
			LogManager.info("Query==> " + query);
			LogManager.info("Args==> " + StringUtils.join(args, ", "));
			int count = this.mytemplate.queryForInt(query, args);
			if(count==0) {
				status = true;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return status;
	}

	@Override
	public String getAgencyCode(String loginId) {
		String result = "";
		try {
			String query = "SELECT OA_CODE FROM LOGIN_MASTER WHERE LOGIN_ID=?";
			Object[] args = new Object[]{loginId};
			LogManager.info("Query==> " + query);
			LogManager.info("Args==> " + StringUtils.join(args, ", "));
			result = (String) this.mytemplate.queryForObject(query, args, String.class);
		} catch(Exception exception) {
			exception.printStackTrace();
		} 
		return result;
	}
	
}
