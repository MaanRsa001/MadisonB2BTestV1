package com.maan.adminnew.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;

import com.maan.common.LogManager;
import com.maan.common.MyJdbcTemplate;
import com.maan.services.util.runner;
import com.opensymphony.xwork2.util.LocalizedTextUtil;

public class CommonDAO extends MyJdbcTemplate{
	String query="";
	public List<Object> getAdminInfo(String user){
		LogManager.push("Method to getAdminInfo");
    	List<Object> menuIds=null;
    	String sql="";
    	try{
			sql=getQuery("GET_MAIN_USER_INFO");
			menuIds=this.mytemplate.queryForList(sql,new Object[]{user});
		   }
		catch (Exception e) {			
			LogManager.debug("EXCEPTION @ { " + e + " }");
		}		
		LogManager.info("Query===>" + sql);
		LogManager.info("getAdminInfo() - Exit || Result: " + menuIds.size());
		LogManager.popRemove();		
    
    	return menuIds;
    }
	
	public List<Object>getMenuList(String menuIds, final String bCode, final String productId){
		LogManager.info("Get Menu List - Enter");
        List<Object> list = null;
        try {
            query = LocalizedTextUtil.findDefaultText("GET_MAIN_MENU_LIST", Locale.ENGLISH, new String[]{menuIds.replaceAll(",", "','"), productId, bCode});
            LogManager.info("Query===> " + query);
            list = this.mytemplate.queryForList(query);
            LogManager.info("Menu List Size=>"+list.size());
        } catch (Exception e) {
        	LogManager.debug("EXCEPTION @ { " + e + " }");
        }
       
        LogManager.info("Params => " + menuIds);
        LogManager.info("Get Menu List - Exit");
        return list;
    }
	public List <Object> getCountries(String branchCode)
	{
		List <Object> countries =null;
		try {
			LogManager.push("Method to getCountries");
			query=getQuery("GET_Countries");
			LogManager.info("Query===>" + query);
			countries=this.mytemplate.queryForList(query, new Object[]{branchCode});
			LogManager.info("getCountries() - Exit");
		}
		catch (Exception e) 
		{
			LogManager.debug("EXCEPTION @ { " + e + " }");
		}
		return countries;
	}
	
	public List <Object> getEmirates(String branchCode)
	{
		List <Object> emirates =null;
		try {
			LogManager.push("Method to getEmirates");
			query=getQuery("GET_Emirates");
			LogManager.info("Query===>" + query);
			emirates=this.mytemplate.queryForList(query, new Object[]{branchCode});
			LogManager.info("getEmirates() - Exit");
		}
		catch (Exception e) 
		{
			LogManager.debug("EXCEPTION @ { " + e + " }");
		}
		return emirates;
	}
	public List <Object> getNationalities() 
	{
		List <Object> nationalities =null;
		try {
			LogManager.push("Method to getNationalities");
			query=getQuery("GET_Nationalities");
			LogManager.info("Query===>" + query);
			nationalities=this.mytemplate.queryForList(query);
			LogManager.info("getNationalities() - Exit");
		}
		catch (Exception e) 
		{
			LogManager.debug("EXCEPTION @ { " + e + " }");
		}
		return nationalities;
	}
	public int getExist(Object[] val,String str)throws EmptyResultDataAccessException,IncorrectResultSizeDataAccessException
	{
		int checkList=0;
		try{
			LogManager.push("Method to getExist()");
			
			if(str.equals("transport"))
			{
				query=getQuery("Check_mode_of_transport");
			}
			else if(str.equals("convey"))
			{
				query=getQuery("Check_convey");
			}
			else if(str.equals("country"))
			{
				query=getQuery("Check_Country");
			}
			else if(str.equals("bank"))
			{
				query=getQuery("Check_Bank");
			}
			else if(str.equals("material"))
			{
				query=getQuery("Check_material");
			}
			else if(str.equals("war"))
			{
				query=getQuery("Check_War");
			}
			else if(str.equals("sale"))
			{
				query=getQuery("Check_Sale");
			}
			else if(str.equals("tole"))
			{
				query=getQuery("Check_tole");
			}
			else if(str.equals("comEx"))
			{
				query=getQuery("Check_comEx");
			}
			else if(str.equals("extraCover"))
			{
				query=getQuery("Check_extraCover");
			}
			else if(str.equals("city"))
			{
				query=getQuery("Check_city");
			}			
			else if(str.equals("vessel"))
			{
				query=getQuery("Check_vessel");
			}
			else if(str.equals("warranty"))
			{
				query=getQuery("Check_Warranty");
			}
			else if(str.equals("constant"))
			{
				query=getQuery("Check_constant");
			}
			else if(str.equals("extra"))
			{
				query=getQuery("Check_extra_name");
			}
			else if(str.equals("countryDet"))
			{
				query=getQuery("Check_countryDet");
			}
			else if(str.equals("agent"))
			{
				query=getQuery("Check_Agent");
			}
			else if(str.equals("commodity"))
			{
				query=getQuery("Check_commodity");
			}
			else if(str.equals("currency"))
			{
				query=getQuery("Check_currency");
			}
			else if(str.equals("exchange"))
			{
				query=getQuery("Check_exchange");
			}
			else if(str.equals("cover"))
			{
				query=getQuery("Check_cover");
			}
			else if(str.equals("exclusion"))
			{
				query=getQuery("Check_Exclusion");
			}
			else if(str.equals("clauseID"))
			{
				query=getQuery("Check_ClauseID");
			}
			else if(str.equals("categ"))
			{
				query=getQuery("Check_Categ");
			}
			else if(str.equals("display"))
			{
				query=getQuery("Check_display_order");
			}
			checkList=this.mytemplate.queryForInt(query,val);
			LogManager.info("Query===>" + query);
			LogManager.info("getExist() - Exit");
			}
		    
		    catch(EmptyResultDataAccessException e){
				LogManager.info("Core Application Code Not Exist");
				
			}
			catch(IncorrectResultSizeDataAccessException e){
				LogManager.info("Core Application Code Not Exist");
				
			}
			return checkList;
	}
	

	public List <Object> getTitles(String branchCode)
	{
		List <Object> titles =null;
		try {
			LogManager.push("Method to getTitles");
			query=getQuery("GET_Titles");
			LogManager.info("Query===>" + query);
			LogManager.info("BranchCode===>" + branchCode);
			Object[] obj=new Object[1];
			obj[0]=branchCode;
			titles=this.mytemplate.queryForList(query,obj);
			LogManager.info("getTitles() - Exit");
		}
		catch (Exception e) 
		{
			LogManager.debug("EXCEPTION @ { " + e + " }");
		}
		return titles;
	}
	
	public List <Map<String,String>> getProductsDET(String branchCode, String agencyCode){
		LogManager.info("Method to getProductsDET");
		List <Map<String,String>> product_Det=null;
		try{
			query=getQuery("GET_PRODUCT_DET");
			if(!"".equals(agencyCode) && agencyCode!=null){
				query+=" and PRODUCT_ID not in (select PRODUCT_ID from LOGIN_USER_DETAILS where AGENCY_CODE='"+agencyCode+"') order by product_id";
			}else query+=" order by product_id";
				String[] args={branchCode};
			LogManager.info("Query===>" + args[0]);
			product_Det=this.mytemplate.queryForList(query,args);
		}
		catch(Exception e){
			LogManager.debug("EXCEPTION @ { " + e + " }");
		}
		return product_Det;
    }
	List<Map<String,String>> getReferralProducts(String branchCode,String productId){
		LogManager.info("Method to getReferralProducts");
		List <Map<String,String>> product_Det=null;
		try{
			String product="";
			query=getQuery("GET_REF_PRODUCT_DET");			
			if("11".equals(productId) || "3".equals(productId)){
				product="3,11";
			}else{
				product=productId;
			}
			query+=" and PRODUCT_ID in("+product+") order by product_id";
			String[] args={branchCode};
			LogManager.info("Query===>" + args[0]);
			product_Det=this.mytemplate.queryForList(query,args);
		}
		catch(Exception e){
			LogManager.debug("EXCEPTION @ { " + e + " }");
		}
		return product_Det;
	}
 
	/*public int getCustomerId(String branchCode){
		int customer_id=0;
		try{
			query=getQuery("GET_Customer_Id");
			LogManager.info("Query===>" + query);
			customer_id=this.mytemplate.queryForInt(query,new Object[]{branchCode});
			query=getQuery("UPD_Customer_Id");
			LogManager.info("Query===>" + query);
			this.mytemplate.update(query,new Object[]{customer_id, customer_id, branchCode});
			LogManager.info("customer_id===>" + customer_id);
		}
		catch(Exception e){
			LogManager.debug("EXCEPTION @ { " + e + " }");
		}
		return customer_id;
	}*/
	public String getUserCode(String branchCode) {
		String agencyCode="";
		try {
			query=getQuery("GET_USER_AGENCYCODE"); 
			int acode=this.mytemplate.queryForInt(query, new Object[]{branchCode});
			agencyCode = String.valueOf(acode);
			query=getQuery("UPD_USER_AGENCYCODE"); 
			this.mytemplate.update(query, new Object[]{acode+1,branchCode});
			LogManager.info("Query===>" + query);
			LogManager.info("agencyCode===>" + agencyCode);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return agencyCode;
	}
	 public int getMaxUserId(){
		int user_Id=0;
		try{
			String query=getQuery("GET_MAXUSER_ID");
			LogManager.info("Query===>" + query);
			user_Id=this.mytemplate.queryForInt(query);
		}
		catch(Exception e){
			LogManager.debug("EXCEPTION @ { " + e + " }");
		}
		LogManager.info("getMaxUserId() - Exit	user_Id===>"+user_Id);
    	return user_Id;
    }
	 public void checkPassword(String[] args) {
	   try
	   { 
			String query=getQuery("UPD_broker_password");
			LogManager.info("Query===>" + query);
			this.mytemplate.update(query,args);
	   }
	   catch (Exception e) 
		{
			LogManager.debug("EXCEPTION @ { " + e + " }");
		}
	}
	 public void insertCommission(Object[] info, Object[] obj1){
		LogManager.push("ENTER-->Method to insertCommission");
    	try
    	{
			String query=getQuery("INS_PRODUCT_DET");
			LogManager.info("Query===>" + queryFrammer(query, info));
			this.mytemplate.update(query, replaceEmptyIfNull(info));
			if(obj1!=null){
				query=getQuery("UPD_PRODUCT_DET_LM");
				LogManager.info("Query===>" + queryFrammer(query, obj1));
				this.mytemplate.update(query, obj1);
			}
    	}catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    	LogManager.info("insertCommission() - Exit");
    }
	 public void updateCommission(Object[] info, Object[] obj1){
		LogManager.push("ENTER-->Method to updateCommission");
    	try
    	{
			String query=getQuery("UPD_PRODUCT_DET");
			LogManager.info("Query===>" + query);
			this.mytemplate.update(query, replaceEmptyIfNull(info));
			if(obj1!=null){
				query=getQuery("UPD_PRODUCT_DET_LM");
				LogManager.info("Query===>" + query);
				this.mytemplate.update(query, obj1);
			}
    	}catch(Exception e)
    	{
    		LogManager.debug("Exception @ "+e);
    	}
    	LogManager.info("updateCommission() - Exit");
    }
	 public int checkExistProduct(String productId, String agencyCode){
		 int count=0;
    	try{
			String query=getQuery("GET_EXIST_PRODUCT_COUNT");
			LogManager.info("Query===>" + query);
			count=this.mytemplate.queryForInt(query, new Object[]{productId, agencyCode});
    	}catch(Exception e){
    		LogManager.debug("Exception @ "+e);
    	}
    	return count;
	 }
	 public List <Object> getAdminBrokerList(String branchCode, String appId){
    	List <Object> brokerList=null;
    	try{
			String query=getQuery("GET_Broker_List");
			LogManager.info("Query===>" + query);
			LogManager.info("branchCode===>" + branchCode);
			brokerList=this.mytemplate.queryForList(query,new Object[]{branchCode, appId, branchCode});		
		   }
		catch (Exception e) {			
			LogManager.debug("EXCEPTION @ { " + e + " }");
		}
    	return brokerList;
	 }
	 public List<Object> getBusinessTypeList(final String branchCode) {
		List <Object> brokerTypeList=null;
    	try{
			String query=getQuery("GET_BROKERTYPE_LIST");
			LogManager.info("Query===>" + query);
			LogManager.info("branchCode===>" + branchCode);
			brokerTypeList=this.mytemplate.queryForList(query,new Object[]{branchCode});		
		   }
		catch (Exception e) {			
			LogManager.debug("EXCEPTION @ { " + e + " }");
		}
    	return brokerTypeList;
    } 
	public List<Object> getCurrencyList() {
		List <Object> currencyList=null;
    	try{
			String query=getQuery("GET_CURRENCY_LIST");
			LogManager.info("Query===>" + query);
			currencyList=this.mytemplate.queryForList(query);		
		   }
		catch (Exception e) {			
			LogManager.debug("EXCEPTION @ { " + e + " }");
		}
    	return currencyList;
    }
	
	public HashMap getBrokerUserDetails(String branch){
		String query ="";
		String args[] = new String[1];
		HashMap<String, Object> broDetails = new HashMap<String, Object> ();
		try{
			args[0] = branch;
			query = "SELECT CURRENCY_ABBREVIATION,ORIGINATION_COUNTRY_ID,DESTINATION_COUNTRY_ID,CURRENCY_ABBREVIATION,DECIMAL_PLACES,nvl(tax,'0'),email,LANG_YN from BRANCH_MASTER where BRANCH_CODE=? ";
			String result[][] = new String[0][0];
			result = runner.multipleSelection(query,args);
			if(result.length>0){
				broDetails.put("Branch",branch);
				broDetails.put("CurrencyName",(result[0][0]!=null?result[0][0]:"SAR"));
				broDetails.put("Orgination",(result[0][1]!=null?result[0][1]:"1"));
				broDetails.put("Destination",(result[0][2]!=null?result[0][2]:"1"));
				broDetails.put("CurrencyAbb",(result[0][3]!=null?result[0][3]:"SAR"));
				broDetails.put("CurrencyDecimal",(result[0][4]!=null?result[0][4]:"2"));
				broDetails.put("Tax",(result[0][5]!=null?result[0][5]:"0"));
				broDetails.put("Site",(result[0][6]!=null?result[0][6]:" "));
				broDetails.put("LANG",(result[0][7]!=null?result[0][7]:" "));
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return broDetails;
	}
	public Object[] replaceEmptyIfNull(Object[] args){
		if(args!=null){
			for (int i = 0; i < args.length; i++) {
				if(args[i]==null){
					args[i]="";
				}
			}
		}
		return args;
	}
	public List<Object> isBetweenTwo(Object[] val) 
    {
		List<Object> list=null;
		try{
			LogManager.info("Enter into isBetweenTwo() ");
			query="SELECT   NVL (DEDUCTIBLE, '0') FROM   COMMODITY_EXCESS_PREMIUM  WHERE  ? BETWEEN START_SUM_INSURED AND END_SUM_INSURED AND STATUS = 'Y' and branch_code=?";
			
			LogManager.info("Values --->"+StringUtils.join(val,","));
			LogManager.info("Query --->"+query);
			
			list=this.mytemplate.queryForList(query,val);
			LogManager.info("Exit into isBetweenTwo() ");
		}catch (Exception e) {
			e.printStackTrace();
			LogManager.info("Exception isBetweenTwo() "+e);
			
		} 
		return list;
    }

	public List<Object> getAdminBranchList(String branchCode, String appId) {
		List <Object> branchList=null;
    	try{
			String query=getQuery("GET_ADMIN_Branch_Detail");
			LogManager.info("Query===>" + query);		
			branchList=this.mytemplate.queryForList(query);				
		}catch (Exception e) {
			e.printStackTrace();
			LogManager.info("Exception getAdminBranchList() "+e);
		}
		return branchList;
	}

	public List<Map<String, String>> getBranchDetails(String loginId) {
		List<Map<String, String>> result=null;
		try{
			String query=getQuery("GET_ADMIN_REF_Branch_Detail");
			LogManager.info("Query===>" + query);		
			result=this.mytemplate.queryForList(query,new Object[]{loginId});
		}catch (Exception e) {
		 e.printStackTrace();
		}
		return result;
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
	
	public String getB2CGuestLoginId(String branchCode) {
		String result = "";
		try {
			String query = getQuery("GET_B2C_LOGINID");
			Object[] args = new Object[]{branchCode};
			result = (String) this.mytemplate.queryForObject(query, args, String.class);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	public String getAgencyCode(String loginId) {
		String result = "";
		try {
			String query = getQuery("GET_AGENCYCODE");
			Object[] args = new Object[]{loginId};
			result = (String) this.mytemplate.queryForObject(query, args, String.class);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	public void updateCommissionMotor(Object[] info, Object[] obj1){
		LogManager.push("ENTER-->Method to updateCommissionMotor");
    	try
    	{
			String query=getQuery("UPD_PRODUCT_DET_MOTOR");
			LogManager.info("Query===>" + query);
			this.mytemplate.update(query, replaceEmptyIfNull(info));
			if(obj1!=null){
				query=getQuery("UPD_PRODUCT_DET_LM");
				LogManager.info("Query===>" + query);
				this.mytemplate.update(query, obj1);
			}
    	}catch(Exception e)
    	{
    		LogManager.debug("Exception @ "+e);
    	}
    	LogManager.info("updateCommissionMotor() - Exit");
    }

	public List<Map<String, Object>> getBranchList() {
		LogManager.push("ENTER-->Method to getBranchList");
		List<Map<String, Object>> result=new ArrayList<Map<String,Object>>();
		 try{
			  String query=getQuery("GET_LOGIN_BRANCH"); 
			  result= this.mytemplate.queryForList(query); 
			  LogManager.info("Query===>" + query);
		 }catch (Exception e) {
			e.printStackTrace(); 
		}
		 return result;
	}

	public List<Map<String, Object>> getUSerBranchList(String agCode) {
		LogManager.push("ENTER-->Method to getUSerBranchList");
		List<Map<String, Object>> result=new ArrayList<Map<String,Object>>();
		 try{
			  String query=getQuery("GET_USER_BRANCH_LIST"); 
			  Object[] args = new Object[]{agCode};
			  LogManager.info("Query===>" + query+", Args => "+StringUtils.join(args, ","));
			  result= this.mytemplate.queryForList(query,args); 
			  
		 }catch (Exception e) {
			e.printStackTrace(); 
		}
		 return result;
	}

	public List<Map<String, Object>> getSubBranchList(String[] branchId) {
		LogManager.push("ENTER-->Method to getSubBranchList");
		List<Map<String, Object>> result=new ArrayList<Map<String,Object>>();
		 try{
			 String query="SELECT BRANCH_ID , BRANCH_NAME, MGEN_BRANCH_ID FROM BROKER_BRANCH_MASTER WHERE MGEN_BRANCH_ID IN(?) AND STATUS='Y' ORDER BY BRANCH_ID";
			 String branch=String.join(",", branchId);
			 String arg="'"+StringUtils.join(branch.split(","),"','")+"'";
			 LogManager.info("query "+query.replace("?", arg));			 
			 result=this.mytemplate.queryForList(query.replace("?", arg));
			 
		 }catch (Exception e) {
			e.printStackTrace(); 
		}
		 return result;
	}
}