package com.maan.adminnew.referal;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.maan.common.LogManager;
import com.maan.common.MyJdbcTemplate;

public class ReferalDAO extends MyJdbcTemplate
{
	String query="";
	
	public List <Object> getOCList(String reqFrom, String productID, String branchCode, String agencyCode,String login_id,String attched_Branch){
    	List <Object> occList=null;
    	String[] obj=null;
    	try{
    		if("3".equals(productID)||"11".equals(productID)){
	    		if("pending".equals(reqFrom)){
	    			query=getQuery("GET_OCLIST_PENDING");
	    		}else if("approved".equals(reqFrom)){
	    			query=getQuery("GET_OCLIST_APPROVED");
	    		}else if("rejected".equals(reqFrom)){
	    			query=getQuery("GET_OCLIST_REJECTED");
	    		}
    		}else{
	    		if("pending".equals(reqFrom)){
	    			query=getQuery("GET_HOMEOCLIST_PENDING");
	    		}else if("approved".equals(reqFrom)){
	    			query=getQuery("GET_HOMEOCLIST_APPROVED");
	    		}else if("rejected".equals(reqFrom)){
	    			query=getQuery("GET_HOMEOCLIST_REJECTED");
	    		}
    		}
    		if("3".equals(productID)||"11".equals(productID)){
	    		if(StringUtils.isEmpty(agencyCode)){
	    			query+=" oa_code in(select agency_code from broker_company_master where branch_code in("+("ALL".equals(branchCode)?"'"+StringUtils.join(attched_Branch.split(","),"','")+"'":branchCode)+"))) and  (Select attached_uw from login_master Where login_id=?) like  ',%' || b.UWGRADE || ',%' group by to_char(a.entry_date,'YYYY-MM-DD') order by to_char(a.entry_date,'YYYY-MM-DD') desc";
	    			obj=new String[]{productID,login_id};
	    		}else{
	    			query+=" oa_code=?) and  (Select attached_uw from login_master Where login_id=?) like  ',%' || b.UWGRADE || ',%') group by to_char(a.entry_date,'YYYY-MM-DD') order by to_char(a.entry_date,'YYYY-MM-DD') desc";
	    			obj=new String[]{productID, agencyCode,login_id};
	    		}
    		}if("65".equals(productID)){
    			if(StringUtils.isEmpty(agencyCode)){
	    			query+=" oa_code in(select agency_code from broker_company_master where branch_code in("+("'"+StringUtils.join(attched_Branch.split(","),"','")+"'")+")))  AND A.BRANCH_CODE in ("+("ALL".equals(branchCode)?"'"+StringUtils.join(attched_Branch.split(","),"','")+"'":branchCode)+") group by to_char(a.entry_date,'YYYY-MM-DD') order by to_char(a.entry_date,'YYYY-MM-DD') desc";
	    			obj=new String[]{productID};
	    		}else{
	    			query+=" oa_code=?) group by to_char(a.entry_date,'YYYY-MM-DD') order by to_char(a.entry_date,'YYYY-MM-DD') desc";
	    			obj=new String[]{productID, agencyCode};
	    		}
    		}
    		else{
    			if(StringUtils.isEmpty(agencyCode)){
	    			query+=" oa_code in(select agency_code from broker_company_master where branch_code in("+("ALL".equals(branchCode)?"'"+StringUtils.join(attched_Branch.split(","),"','")+"'":branchCode)+"))) group by to_char(a.entry_date,'YYYY-MM-DD') order by to_char(a.entry_date,'YYYY-MM-DD') desc";
	    			obj=new String[]{productID};
	    		}else{
	    			query+=" oa_code=?) group by to_char(a.entry_date,'YYYY-MM-DD') order by to_char(a.entry_date,'YYYY-MM-DD') desc";
	    			obj=new String[]{productID, agencyCode};
	    		}
    		}
			LogManager.info("Query===>" + query);
			//LogManager.info("Query===>" +StringUtils.join((String[])obj,","));
			occList=this.mytemplate.queryForList(query,obj);
		   }
		catch (Exception e) {			
			LogManager.debug("EXCEPTION @ { " + e + " }");
		}
    	return occList;
    }
	
	public List <Object> getPolicyList(String branchCode, String date, String productID, String reqFrom, String agencyCode,String loginId,String attched_Branch){
    	List <Object> policyList=null;
    	Object[] obj=null;
    	try{
    		if("3".equals(productID)||"11".equals(productID)){
	    		if("pending".equals(reqFrom)){
	    			query=getQuery("GET_POLICIES_PENDING");
	    		}else if("approved".equals(reqFrom)){
	    			query=getQuery("GET_POLICIES_APPROVED");
	    		}else if("rejected".equals(reqFrom)){
	    			query=getQuery("GET_POLICIES_REJECTED");
	    		}
    		}else{
    			if("pending".equals(reqFrom)){
	    			query=getQuery("GET_HOMEPOLICIES_PENDING");
	    		}else if("approved".equals(reqFrom)){
	    			query=getQuery("GET_HOMEPOLICIES_APPROVED");
	    		}else if("rejected".equals(reqFrom)){
	    			query=getQuery("GET_HOMEPOLICIES_REJECTED");
	    		}
    		}
    		if(StringUtils.isEmpty(agencyCode)){
    			if("65".equals(productID)){
    				query+=" oa_code in(select agency_code from broker_company_master where branch_code in("+("'"+StringUtils.join(attched_Branch.split(","),"','")+"'")+")) AND pm.BRANCH_CODE in ("+("ALL".equals(branchCode)?"'"+StringUtils.join(attched_Branch.split(","),"','")+"'":branchCode)+")";
    				query+=	")order by quote_no desc";
    			}else{
    				query+=" oa_code in(select agency_code from broker_company_master where branch_code in("+("ALL".equals(branchCode)?"'"+StringUtils.join(attched_Branch.split(","),"','")+"'":branchCode)+"))";
    				query+= ("3".equals(productID)||"11".equals(productID))?" and (Select attached_uw from login_master Where login_id="+loginId+") like  ',%' || md.UWGRADE || ',%'":""; 
    				query+=	")order by quote_no desc";
    			}
    			obj=new Object[]{date, productID};
    		}else if("3".equals(productID)||"11".equals(productID)){
    			query+=" oa_code=?) and (Select attached_uw from login_master Where login_id=?) like  ',%' || md.UWGRADE || ',%') order by quote_no desc";
    			obj=new Object[]{date, productID, agencyCode,loginId};
    		}else{
    			query+=" oa_code=?)  order by quote_no desc";
    			obj=new Object[]{date, productID, agencyCode};
    		}
			LogManager.info("Query===>" + query);
			policyList=this.mytemplate.queryForList(query,obj);
		}catch (Exception e) {
			LogManager.debug("EXCEPTION @ { " + e + " }");
		}
    	return policyList;
    }
	public List<Object> getRegionList(String loginId, String accesstype) {
		List<Object> regionList=null;
		try{
			regionList=this.mytemplate.queryForList(getQuery("GET_ATTACHED_REGIONS"),new Object[]{loginId});
		}catch(Exception e){
			e.printStackTrace();
		}
		return regionList;
	}
}