package com.maan.adminnew.openCover;

import java.util.List;
import com.maan.common.LogManager;
import com.maan.common.MyJdbcTemplate;

public class OpenCoverDAO extends MyJdbcTemplate
{
	private String query="";
	private Object[] obj=null;
	
	public List <Object> getregQuote(final String broker){
    	List <Object> regQuote=null;
    	obj=new Object[]{broker};
    	try{
    		query=getQuery("GET_QUOTE_REGISTER_LIST");
			LogManager.info("Query===>" + query);
			for(Object a:obj)
				LogManager.info("args===>" + a);
			regQuote=this.mytemplate.queryForList(query,obj);
		   }
		catch (Exception e) {			
			LogManager.debug("EXCEPTION @ { " + e + " }");
		}
    	return regQuote;
    }
	
	public List <Object> getPortfolio(final String brokerLoginId){
    	List <Object> regQuote=null;
    	obj=new Object[]{brokerLoginId};
    	try{
    		query="SELECT DISTINCT (A.PROPOSAL_NO), NVL (A.OPEN_COVER_NO, '0'), NVL (TO_CHAR (B.OPEN_COVER_START_DATE, 'DD-MON-YYYY'), ' ') START_DATE, NVL (TO_CHAR (B.OPEN_COVER_END_DATE, 'DD-MON-YYYY'), ' ') END_DATE, NVL (C.FIRST_NAME, C.COMPANY_NAME) CUSTOMER_NAME, D.CUSTOMER_ID, B.AMEND_ID, D.MISSIPPI_OPENCOVER_NO,A.STATUS, A.ENDT_STATUS, A.ORIGINAL_POLICY_NO FROM OPEN_COVER_POSITION_MASTER A, OPEN_COVER_DETAIL B, PERSONAL_INFO C, OPEN_COVER_MASTER D WHERE (A.STATUS = 'P' OR (A.STATUS='Y' AND ORIGINAL_POLICY_NO IS NOT NULL)) AND A.PROPOSAL_NO = B.PROPOSAL_NO AND TRUNC(A.EXPIRY_DATE) >= TRUNC(SYSDATE) AND B.AMEND_ID = (SELECT MAX (AMEND_ID) FROM OPEN_COVER_DETAIL WHERE PROPOSAL_NO = A.PROPOSAL_NO) AND A.PROPOSAL_NO IN (SELECT (PROPOSAL_NO) FROM OPEN_COVER_MASTER WHERE BROKER_ID = D.BROKER_ID) AND D.PROPOSAL_NO = A.PROPOSAL_NO AND C.CUSTOMER_ID = D.CUSTOMER_ID AND D.AMEND_ID = (SELECT MAX (AMEND_ID) FROM OPEN_COVER_MASTER WHERE PROPOSAL_NO = A.PROPOSAL_NO) AND (A.ADMIN_STATUS IS NULL OR A.ADMIN_STATUS = 'Y') AND D.BROKER_ID=? ORDER BY A.PROPOSAL_NO DESC";
			LogManager.info("Query===>" + query);
			for(Object a:obj)
				LogManager.info("args===>" + a);
			regQuote=this.mytemplate.queryForList(query,obj);
		}catch (Exception e) {
			LogManager.debug("EXCEPTION @ { " + e + " }");
		}
    	return regQuote;
    }
	public List <Object> getCustomerList(final String broker, final String appId){
		List <Object> customerList=null;
    	obj=new Object[]{broker, appId};
    	try{
    		query=getQuery("GET_CUSTOMER_LIST")+" USERTYPE in ('Broker', 'User')) ORDER BY FIRST_NAME";
			LogManager.info("Query===>" + query);
			for(Object a:obj)
				LogManager.info("args===>" + a);
			customerList=this.mytemplate.queryForList(query,obj);
		}catch (Exception e) {			
			LogManager.debug("EXCEPTION @ { " + e + " }");
		}
    	return customerList;
	}
	public List<Object> getLcList(String branchCode, String policynumber) {
		List <Object> lcList=null;
    	obj=new Object[]{branchCode, policynumber};
    	try{
    		query=getQuery("GET_LCDETAIL_LIST");
			LogManager.info("Query===>" + query);
			for(Object a:obj)
				LogManager.info("args===>" + a);
			lcList=this.mytemplate.queryForList(query,obj);
		}catch (Exception e) {			
			LogManager.debug("EXCEPTION @ { " + e + " }");
		}
    	return lcList;
	}
}