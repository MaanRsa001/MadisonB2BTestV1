package com.maan.adminnew.BrokerManagement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.core.RowMapper;

import com.maan.common.LogManager;
import com.maan.common.MyJdbcTemplate;
import com.opensymphony.xwork2.util.LocalizedTextUtil;

public class BrokerManagementDAO extends MyJdbcTemplate
{
	public List <String> getBrokerDetails(final BrokerMgmBean ba, String agencyCode,String branchCode){
		List brokerDetails=null;
		try{
			String query=getQuery("GET_Broker_Info");
			LogManager.info("Query===>" + query);
			LogManager.info("AgencyCode===>" + agencyCode);
			LogManager.info("BranchCode===>" + branchCode);
			Object[] obj=new Object[2];
			obj[0]=agencyCode;
			obj[1]=branchCode;
			brokerDetails=this.mytemplate.query(query,obj,new RowMapper(){
				public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
					if(rs.getString("CUST_NAME")!=null && rs.getString("missippi_id")!=null)
						ba.setNameAndCode((rs.getString("CUST_NAME"))+"("+rs.getString("missippi_id")+")");
					ba.setApprovedby(rs.getString("APPROVED_PREPARED_BY"));
					ba.setBcode(rs.getString("rsa_broker_code"));
					ba.setBorganization(rs.getString("COMPANY_NAME")==null?rs.getString("FIRST_NAME"):rs.getString("COMPANY_NAME"));
					ba.setAddress1(rs.getString("ADDRESS1"));
					ba.setAddress2(rs.getString("ADDRESS2"));
					ba.setCountry(rs.getString("COUNTRY"));
					ba.setCountryName(rs.getString("COUNTRY_NAME"));
					ba.setPobox(rs.getString("POBOX"));
					ba.setTelephone(rs.getString("TELEPHONE"));
					ba.setFax(rs.getString("FAX"));
					ba.setEmirate(rs.getString("EMIRATE"));
					ba.setOthercity(rs.getString("CITY"));
					ba.setTitle(rs.getString("TITLE"));
					ba.setGender(rs.getString("GENDER"));
					ba.setFirstname(rs.getString("FIRST_NAME"));
					ba.setLastname(rs.getString("LAST_NAME"));
					ba.setNationality(rs.getString("NATIONALITY"));
					ba.setNationalityNa(rs.getString("NATIONALITYNAME"));
					ba.setDob(rs.getString("DOB"));
					ba.setOccupation(rs.getString("OCCUPATION"));
					ba.setMobile(rs.getString("MOBILE"));
					ba.setBemail(rs.getString("EMAIL"));
					ba.setExecutive(rs.getString("AC_EXECUTIVE_ID"));
					ba.setEntryDate(rs.getString("ENTRY_DATE"));
					ba.setLogin_Id(rs.getString("LOGIN_ID"));
					ba.setCustomer_id(rs.getInt("customer_id"));
					ba.setMissippiId(rs.getString("missippi_id"));
					ba.setCompanyName(rs.getString("COMPANY_NAME"));
					ba.setCIMSNO(rs.getString("missippi_id"));
					ba.setCustomerName(rs.getString("CUST_NAME"));
					ba.setStatus(rs.getString("STATUS"));
					ba.setOneOffCommission(rs.getString("ISSUER_COMMISSION_ONEOFF"));
					ba.setOpenCoverCommission(rs.getString("ISSUER_COMMISSION_OPENCOVER"));
					ba.setBroImgName(rs.getString("IMAGE_PATH"));
					ba.setBrokerUsertype(rs.getString("BROKER_USERTYPE"));
					String[] array = (rs.getString("ATTACHED_BRANCH")==null?"":rs.getString("ATTACHED_BRANCH").toString()).split(",");
					ba.setBranchId(array);
					ba.setSubBranchId(rs.getString("SUB_BRANCH")==null?"":rs.getString("SUB_BRANCH").toString());
					return null;
				}
		   });
			ba.setAgencyCode(agencyCode);
		}
		catch (Exception e) {
			LogManager.debug("EXCEPTION @ { " + e + " }");
		}
    	return brokerDetails;
	}
	
	public List <Object> getCommisionData(String agencyCode) 
	{
		List <Object> commisionDetails=null;
		try 
		{
			LogManager.push("Method to getCommisionData");
			String query=getQuery("GET_Commission_Data")+" order by lud.product_id";
			LogManager.info("Query===>" + query);
			LogManager.info("AgencyCode===>" + agencyCode);
			Object[] obj=new Object[1];
			obj[0]=agencyCode;
			commisionDetails=this.mytemplate.queryForList(query,obj);
			LogManager.info("getCommisionData() - Exit");
		} 
		catch (Exception e) 
		{
			LogManager.debug("EXCEPTION @ { " + e + " }");
		}
		return commisionDetails;
	}
	
	public List <Object> getProducts(final BrokerMgmBean ba) 
	{
		List <Object> productData=null;
		try 
		{
			LogManager.push("Method to getProducts");
			String query=getQuery("GET_Commission_Data")+" AND lud.PRODUCT_ID=?";
			LogManager.info("Query===>" + query);
			Object[] obj={ba.getAgencyCode(), ba.getProductID()};
			productData=this.mytemplate.queryForList(query,obj);
			if(productData!=null && productData.size()>0){
				Map map=(Map)productData.get(0);
				ba.setProductID(map.get("PRODUCT_ID")==null?"":map.get("PRODUCT_ID").toString());
				ba.setProductName(map.get("product_name")==null?"":map.get("product_name").toString());
				ba.setCommission(map.get("COMMISSION")==null?"":map.get("COMMISSION").toString());
				ba.setInsurance_End_Limit(map.get("INSURANCE_END_LIMIT")==null?"":map.get("INSURANCE_END_LIMIT").toString());
				ba.setUser_Id_Creation(map.get("PROVISION_FOR_PREMIUM")==null?"":map.get("PROVISION_FOR_PREMIUM").toString());
				ba.setDiscountPremium(map.get("DISCOUNT_PREMIUM")==null?"":map.get("DISCOUNT_PREMIUM").toString());
				ba.setMin_Premium_Amount(map.get("MIN_PREMIUM_AMOUNT")==null?"":map.get("MIN_PREMIUM_AMOUNT").toString());
				ba.setBack_Date_Allowed(map.get("BACK_DATE_ALLOWED")==null?"":map.get("BACK_DATE_ALLOWED").toString());
				ba.setProductStatus(map.get("status")==null?"":map.get("status").toString());
				ba.setLoadingPremium(map.get("LOADING_PREMIUM")==null?"":map.get("LOADING_PREMIUM").toString());
				ba.setRemark(map.get("REMARKS")==null?"":map.get("REMARKS").toString());
				ba.setProvision(map.get("RECEIPT_STATUS")==null?"":map.get("RECEIPT_STATUS").toString());
				ba.setFreight(map.get("FREIGHT_DEBIT_OPTION")==null?"":map.get("FREIGHT_DEBIT_OPTION").toString());
				ba.setPayReceipt(map.get("pay_receipt_status")==null?"":map.get("pay_receipt_status").toString());
				ba.setRemark(map.get("REFERAL")==null?"":map.get("REFERAL").toString());
				ba.setMinDiscPercent(map.get("MIN_DISCOUNT")==null?"":map.get("MIN_DISCOUNT").toString());
				ba.setMaxDiscPercent(map.get("MAX_DISCOUNT")==null?"":map.get("MAX_DISCOUNT").toString());
				ba.setMinLoadPercent(map.get("MIN_LOADING")==null?"":map.get("MIN_LOADING").toString());
				ba.setMaxLoadPercent(map.get("MAX_LOADING")==null?"":map.get("MAX_LOADING").toString());
			}
			LogManager.info("getProducts() - Exit");
		}
		catch (Exception e)
		{
			LogManager.debug("EXCEPTION @ { " + e + " }");
		}
		return productData;
	}
	
	public List <Object> getBranchDetails(String branchCode)
	{
		List <Object> branchData=null;
		try 
		{
			LogManager.push("Method to getBranches");
			String query=getQuery("GET_Branch_Detail");
			LogManager.info("Query===>" + query);
			LogManager.info("BranchCode===>" + branchCode);
			Object[] obj=new Object[1];
			obj[0]=branchCode;
			branchData=this.mytemplate.queryForList(query,obj);
			LogManager.info("getBranches() - Exit");
		}
		catch (Exception e) 
		{
			LogManager.debug("EXCEPTION @ { " + e + " }");
		}
		return branchData;
	}
	public List <Object> getBrokerCode() 
	{
		List <Object> brokerCode=null;
		try 
		{
			LogManager.push("Method to getBrokerCode");
			String query=getQuery("GET_Broker_Code");
			LogManager.info("Query===>" + query);
			brokerCode=this.mytemplate.queryForList(query);
			LogManager.info("getBrokerCode() - Exit");
		}
		catch (Exception e) 
		{
			LogManager.debug("EXCEPTION @ { " + e + " }");
		}
		return brokerCode;
	}
	
	public List <Object> getExecutives() {
		List <Object> executives =null;
		   try
		   { 
			   LogManager.push("Method to getExecutives");
				String query=getQuery("GET_Executives");
				LogManager.info("Query===>" + query);
				executives=this.mytemplate.queryForList(query);
				LogManager.info("getExecutives() - Exit");
		   }
		   catch (Exception e) 
			{
				LogManager.debug("EXCEPTION @ { " + e + " }");
			}
		return executives;
	 }
	
	public List <Object> getcoreCustomererInfo(String cust_name, String bcode, String userLoginMode) {
		List <Object> coreCustomerInfo =null;
		   try
		   { 
			   LogManager.push("Method to getcoreCustomererInfo");
				String query="SELECT CIMSNO,(CASE WHEN CUSTCODE IS NULL OR CUSTGRP IS NULL OR CUSTTYPE IS NULL OR CUSTCLAS IS NULL THEN '' ELSE (CUSTCODE ||'-'|| CUSTGRP ||'-'|| CUSTTYPE ||'-'||CUSTCLAS) END) ARACC, CUSTTITL,CUSTNAME,CUSTADD1,CUSTADD2,CUSTADD3,CUSTADD4 FROM C_CUST@ECARGO_"+("Test".equalsIgnoreCase(userLoginMode)?"DEV":"PROD")+" WHERE lower(CUSTNAME) LIKE '%'||lower(?)||'%' AND BRCODE=?";
				LogManager.info("Query===>" + query);
				Object[] obj=new Object[2];
				obj[0]=cust_name;
				obj[1]=bcode;
				coreCustomerInfo=this.mytemplate.queryForList(query,obj);
				LogManager.info("getcoreCustomererInfo() - Exit :   Result-->"+coreCustomerInfo.size());
		   }
		   catch (Exception e) 
			{
				LogManager.debug("EXCEPTION @ { " + e + " }");
			}
		return coreCustomerInfo;
	}
	
	public List <String> getBrokerTaxInfo(final BrokerMgmBean ba,String[] args){
		LogManager.info("Method to getBrokerTaxInfo");
		List customerTaxInfo=null;
		try{
			String query=getQuery("GET_Tax_Info");
			LogManager.info("Query===>" + query);
			customerTaxInfo=this.mytemplate.query(query,args,new RowMapper(){
				public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
					ba.setPolicy_fee(rs.getString("policy_feestatus"));
					ba.setPolicFee(rs.getString("policy_fees"));
					ba.setGov_fee(rs.getString("gov_taxstatus"));
					ba.setGovtTax(rs.getString("govt_tax"));
					ba.setEmer_fee(rs.getString("emergency_fundstatus"));
					ba.setEmergencyfund(rs.getString("emer_fund"));
					ba.setApp_for(rs.getString("TAX_APPLICABLE"));
					ba.setEffecdate(rs.getString("effec_date"));
					return null;
				}
		   });
		}
		catch(Exception e){
			LogManager.debug("EXCEPTION @ { " + e + " }");
		}
		return customerTaxInfo;
	}
	public void newBrokerCreation(Object[] args, Object[] info){
		LogManager.push("ENTER-->Method to newBrokerCreation");
    	try{
			String query=getQuery("INS_Broker_Det");
			String query1=getQuery("INS_BCM_DET");
			LogManager.info("Query===>" + queryFrammer(query, args));
			LogManager.info("Query1===>" + queryFrammer(query1,info));
			this.mytemplate.update(query1,info);
			this.mytemplate.update(query,args);
			LogManager.info("newBrokerCreation() - Exit");
    	}
    	catch (Exception e) {			
    		LogManager.debug("EXCEPTION @ newBrokerCreation { " + e + " }");
    	}
	}
	public int getBroke_Code(){
		int brokerCode=0;
		try{
			String query=getQuery("GET_BRO_CODE");
			LogManager.info("Query===>" + query);	
			brokerCode=this.mytemplate.queryForInt(query);
			LogManager.info("brokerCode===>" + brokerCode);
		}
		catch(Exception e){
			LogManager.debug("EXCEPTION @ { " + e + " }");
		}
		return brokerCode;
	}
	public void getmax_Broke_Code(Object[] args){
		try{
			String query=getQuery("GET_MAX_BRO_CODE");
			LogManager.info("Query===>" + query);
			this.mytemplate.update(query,args);
		}
		catch(Exception e){
			LogManager.debug("EXCEPTION @ { " + e + " }");
		}
	}
	public void update_Broker(Object[] args){
		try{
			String query=getQuery("UPD_BROKER_DET");
			LogManager.info("Query===>" + queryFrammer(query, args));
			this.mytemplate.update(query,args);
			LogManager.info("Exit ===>UPD_BROKER_DET");
		}
		catch(Exception e){
			LogManager.debug("EXCEPTION @ { " + e + " }");
		}
	}
	public void update_PersonalInfo(Object[] args){
		try{
			String query=getQuery("UPD_PERSONAL_INFO");
			LogManager.info("Query===>" + queryFrammer(query, args));
			this.mytemplate.update(query,args);
			LogManager.info("Exit ===>update_PersonalInfo");
		}
		catch(Exception e){
			LogManager.debug("EXCEPTION @ { " + e + " }");
		}
	}
	public int getCustomer_Id(String agencyCode, String branchCode){
		int customer_Id=0;
		try{
			String query=getQuery("GET_CUST_ID");
			LogManager.info("Query===>" + query);	
			String[] args={agencyCode,branchCode};
			customer_Id=this.mytemplate.queryForInt(query,args);
			LogManager.info("brokerCode===>" + customer_Id);
		}
		catch(Exception e){
			LogManager.debug("EXCEPTION @ { " + e + " }");
		}
		return customer_Id;
	}
	public int getMax_amend_Id(String branchCode, int broker_Code){
		int amend_Id=0;
		try{
			String query=getQuery("GET_MAXAMEND_ID");
			LogManager.info("Query===>" + query);	
			Object[] obj=new Object[2];
			obj[0]=branchCode;
			obj[1]=broker_Code;
			amend_Id=this.mytemplate.queryForInt(query, obj);
		}
		catch(Exception e){
			LogManager.debug("EXCEPTION @ { " + e + " }");
		}
    	return amend_Id;
    }
    public void insTaxInfo(Object[] val){
		LogManager.push("ENTER-->Method to newBrokerCreation");
    	try{
			String query=getQuery("INS_TAX_DET");
			LogManager.info("Query===>" + query);
			this.mytemplate.update(query,val);
			LogManager.push("Success===>"+query);
			LogManager.info("insTaxInfo() - Exit");
    	}
    	catch (Exception e) {			
    		LogManager.debug("EXCEPTION @ { " + e + " }");
    	}
	}
    public void updTaxInfo(Object[] val){
		LogManager.push("ENTER-->Method to updTaxInfo");
    	try{
			String query=getQuery("UPD_TAX_DET");
			LogManager.info("Query===>" + query);
			this.mytemplate.update(query,val);
			LogManager.info("updTaxInfo() - Exit");
    	}
    	catch (Exception e) {			
    		LogManager.debug("EXCEPTION @ { " + e + " }");
    	}
	}
    public List <Object> getCommissionDET(String agencyCode, String branchCode){
		LogManager.info("Method to getCommissionDET");
		List <Object> commission_Det=null;
		try{
			String query=getQuery("GET_COMMISSION_DET");
			LogManager.info("Query===>" + query);
			String[] args={agencyCode, branchCode};
			LogManager.info("Query===>" + args[0]+"		"+args[1]);
			commission_Det= this.mytemplate.queryForList(query,args);
		}
		catch(Exception e){
			LogManager.debug("EXCEPTION @ { " + e + " }");
		}
		LogManager.info("EXIT getCommissionDET : RESULT===>" + commission_Det.size());
		return commission_Det;
    }
    
    public List <String> getCompDet(final BrokerMgmBean ba,String agencyCode){
		LogManager.info("Method to getCompDet");
		List comp_Det=null;
		try{
			String query=getQuery("GET_COMP_DET");
			LogManager.info("Query===>" + query);
			String[] args={agencyCode};
			LogManager.info("Query===>" + args[0]);
			comp_Det=this.mytemplate.query(query,args,new RowMapper(){
				public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
					ba.setBorganization(rs.getString("COMPANY_NAME"));
					ba.setFirstname(rs.getString("FIRST_NAME"));
					return null;
				}
		   });
		}
		catch(Exception e){
			LogManager.debug("EXCEPTION @ { " + e + " }");
		}
		return comp_Det;
    }
   
    public void updateProduct(Object[] pId){
		LogManager.push("ENTER-->Method to updateProduct");
    	try
    	{
			String query=getQuery("UPD_PRODUCT_ID");
			LogManager.info("Query===>" + query);
			this.mytemplate.update(query, pId);
    	}catch(Exception e)
    	{
    		LogManager.debug("Exception @ "+e);
    	}
    	LogManager.info("updateProduct() - Exit");
    }
    public List <String> getLogInId(final BrokerMgmBean ba,String agencyCode){
		LogManager.info("Method to getLogInId");
		List logInId_Det=null;
		try{
			String query=getQuery("GET_LOGIN_ID");
			LogManager.info("Query===>" + query);
			String[] args={agencyCode};
			LogManager.info("Query===>" + args[0]);
			logInId_Det=this.mytemplate.query(query,args,new RowMapper(){
				public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
					ba.setLoginid(rs.getString("LOGIN_ID"));
					return null;
				}
		   });
		}
		catch(Exception e){
			LogManager.debug("EXCEPTION @ { " + e + " }");
		}
		return logInId_Det;
    }
    public void insertLogInDet(String[] args){
		LogManager.push("ENTER-->Method to insertLogInDet");
    	try
    	{
			String query=getQuery("INS_LOGIN_DET");
			LogManager.info("Query===>" + query);
			this.mytemplate.update(query, args);
    	}catch(Exception e)
    	{
    		LogManager.debug("Exception @ "+e);
    	}
    	LogManager.info("insertLogInDet() - Exit");
    }
    public void updatePersonalLogin(Object[] params){
		LogManager.push("ENTER-->Method to updatePersonalLogin");
    	try
    	{
			String query=getQuery("UPD_PERSONAL_LOGIN_ID");
			LogManager.info("Query===>" + query);
			this.mytemplate.update(query, params);
    	}catch(Exception e)
    	{
    		LogManager.debug("Exception @ "+e);
    	}
    	LogManager.info("updatePersonalLogin() - Exit");
    }
    public String getBrokerStatus(String agencyCode){
		LogManager.info("getBrokerStatus - Enter");
		String status="";
		try
		{
			String query=getQuery("GET_BROKER_STATUS");
			String[] obj={agencyCode};
			status=this.mytemplate.queryForObject(query,obj,String.class).toString();			
		}
		catch (Exception e)
		{			
			LogManager.debug("EXCEPTION @ { " + e + " }");
		}		
		LogManager.info("getBrokerStatus - Exit ");
		return status;
	}
	public void updateBrokerStatus(String[] obj){
		LogManager.info("updateBrokerStatus - Enter");
		try
		{
			String query=getQuery("UPD_BROKER_STATUS_LM");
			LogManager.info("query==>"+queryFrammer(query, obj));
			for(Object k: obj)
				LogManager.info("query==>"+k);
			this.mytemplate.update(query,obj);
			query=getQuery("UPD_BROKER_STATUS_BCM");
			LogManager.info("query==>"+queryFrammer(query, new Object[]{obj[1], obj[3]}));
			this.mytemplate.update(query,new Object[]{obj[1], obj[3]});
		}
		catch (Exception e)
		{			
			LogManager.debug("EXCEPTION @ { " + e + " }");
		}		
		LogManager.info("updateBrokerStatus - Exit ");
	}
	/*public List <Object> getProductStatus(String[] obj){
		LogManager.info("getProductStatus - Enter");
		List <Object> userInfo=null;
		try
		{
			String query=getQuery("GET_USER_LIST");
			userInfo=this.mytemplate.queryForList(query,obj);		
		}
		catch (Exception e)
		{			
			LogManager.debug("EXCEPTION @ { " + e + " }");
		}		
		LogManager.info("getProductStatus - Exit ");
		return userInfo;
	}*/
	public List <Object> getProductStatuss(String[] obj){
		LogManager.info("getProductStatuss - Enter");
		List <Object> userInfo=null;
		try
		{
			String query=getQuery("GET_USER_LISTT");
			userInfo=this.mytemplate.queryForList(query,obj);		
		}
		catch (Exception e)
		{			
			LogManager.debug("EXCEPTION @ { " + e + " }");
		}		
		LogManager.info("getProductStatuss - Exit ");
		return userInfo;
	}
	public void updateUserIdCreation(String[] obj){
		LogManager.info("updateUserIdCreation - Enter");
		try
		{
			String query=getQuery("UPD_USERID_CREATION");
			this.mytemplate.update(query,obj);			
		}
		catch (Exception e)
		{			
			LogManager.debug("EXCEPTION @ { " + e + " }");
		}		
		LogManager.info("updateUserIdCreation - Exit ");
	}
	public List<Object> getBrokerListAjax(String branchCode, String searchBy, String searchValue, String appID){
		List <Object> brokerList=null;
		String query="";
		try{
			if("name".equals(searchBy)){
				query=getQuery("GET_BROKER_LIST_BY_NAME");
			}
			else 
				query=getQuery("GET_BROKER_LIST_BY_CODE");
			LogManager.info("Query===>" + query);
			LogManager.info("branchCode===>" + branchCode);
			brokerList=this.mytemplate.queryForList(query,new Object[]{branchCode, appID, branchCode, searchValue});		
		   }
		catch (Exception e) {			
			e.printStackTrace();
		}
		return brokerList;
	}
	public void deleteProduct(BrokerMgmBean bean) {
		try{
			String query=getQuery("BROKER_DELETE_PRODUCT");
			String args[]=new String[2];
			args[0]=bean.getAgencyCode();
			args[1]=bean.getProductID();
			LogManager.push("Query "+query);
			LogManager.push("args "+StringUtils.join(args,","));
			this.mytemplate.update(query,args);
			if("65".equalsIgnoreCase(bean.getProductID())){
				String qry="DELETE FROM BROKER_COMMISSION_DETAIL WHERE AGENCY_CODE=? AND PRODUCT_ID=?";
				LogManager.push("BROKER_COMMISSION_DETAIL Del Query "+qry);
				this.mytemplate.update(qry,args);
			}
		}catch(Exception e){
			LogManager.debug("EXCEPTION @ { " + e + " }");
		}
	}
	public int validateBcode(String brokerCode) {
		int result = 0;
		try {
			String query = LocalizedTextUtil.findDefaultText("CHK_BROKERCODE_CNT",Locale.ENGLISH);
			result = this.mytemplate.queryForInt(query,new Object[]{brokerCode});
		}
		catch(Exception ex) {
			
		}
		return result; 
	}
	public void deleteBroLogo(String agencyCode) {
		try{
			String query=getQuery("BROKER_DELETE_LOGO");
			LogManager.push("Query "+query);
			LogManager.push("agencyCode "+agencyCode);
			this.mytemplate.update(query,new Object[]{agencyCode});
		}catch(Exception e){
			LogManager.debug("EXCEPTION @ { " + e + " }");
		}
	}
	public int valBraWiseBcode(String brokerCode,String branchCode) {
		int result = 0;
		try {
			String query = getQuery("BRANCH_BCODE_CNT");
			result = this.mytemplate.queryForInt(query,new Object[]{brokerCode,branchCode});
		}
		catch(Exception ex) {
			
		}
		return result; 
	}

	public List<Map<String, Object>> getPolicyTypeList(BrokerMgmBean bean) {
		LogManager.push("ENTER-->Method to getPolicyTypeList");
		List<Map<String, Object>> result=new ArrayList<Map<String,Object>>();
		 try{
			  String query=getQuery("GET_BROKER_POLICYTYPE_LIST"); 
			  LogManager.info("Query===>" + query);
			  result= this.mytemplate.queryForList(query);
		 }catch (Exception e) {
			e.printStackTrace(); 
		}
		 return result;
	}

	public int brokerCommissionCount(BrokerMgmBean bean) {
		LogManager.push("ENTER-->Method to brokerCommissionCount");
		int count=0;
		String query="SELECT COUNT(*) FROM BROKER_COMMISSION_DETAIL WHERE AGENCY_CODE=? AND PRODUCT_ID=?";
		count=this.mytemplate.queryForInt(query,new Object[]{bean.getAgencyCode(),bean.getProductID()});
		
		return count;
	}

	public int insertBrokerCommission(List<Map<String, Object>> policyTypeList, BrokerMgmBean bean) {
		LogManager.push("ENTER-->Method to insertBrokerCommission");
		int res=0;
		String amendId="";
    	try
    	{
    		String amendIdQuery="SELECT NVL(MAX(AMEND_ID)+1,0) FROM BROKER_COMMISSION_DETAIL WHERE AGENCY_CODE=? AND PRODUCT_ID=?";
    		amendId=(String) this.mytemplate.queryForObject(amendIdQuery,new Object[]{bean.getAgencyCode(),bean.getProductID()}, String.class);
			String query="INSERT INTO BROKER_COMMISSION_DETAIL (AGENCY_CODE,LOGIN_ID,PRODUCT_ID,POLICYTYPE,AMEND_ID,POLICY_FEE,OTHER_FEE,"
					+ "SUMINSURED_START,SUMINSURED_END,ENTRY_DATE,EFFECTIVE_DATE,END_DATE) VALUES "
					+ "(?,(SELECT LOGIN_ID FROM LOGIN_MASTER WHERE AGENCY_CODE=?),?,?,?,?,?,?,?,SYSDATE,TO_DATE(?,'DD/MM/YYYY'),TO_DATE(?,'DD/MM/YYYY'))";
			LogManager.info("Query===>" + query);
			for(int i=0;i<policyTypeList.size();i++){
				/*if(!"3".equalsIgnoreCase(policyTypeList.get(i).get("POLICYTYPE_ID").toString())){*/
				String sumInsSt="";
				String sumInsEnd="";
				if("3".equalsIgnoreCase(policyTypeList.get(i).get("POLICYTYPE_ID").toString())){
					sumInsSt="0";
					sumInsEnd="0";
				}else{
					sumInsSt=StringUtils.isBlank(bean.getSumInsStart().get(i))?"0":bean.getSumInsStart().get(i);
					sumInsEnd=StringUtils.isBlank(bean.getSumInsEnd().get(i))?"0":bean.getSumInsEnd().get(i);
				}
				
				res=this.mytemplate.update(query, new Object[]{bean.getAgencyCode(),bean.getAgencyCode(),bean.getProductID(),
						policyTypeList.get(i).get("POLICYTYPE_ID"),amendId,
						//StringUtils.isBlank(bean.getPolicyFee().get(i))?"0":bean.getPolicyFee().get(i),
						//StringUtils.isBlank(bean.getOtherFee().get(i))?"0":bean.getOtherFee().get(i),
						"0","0",
						sumInsSt,
						sumInsEnd,
						bean.getEffectiveDate(),bean.getEndDate()});
				/*}*/
			}
    	}catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    	LogManager.info("insertBrokerCommission() - Exit");
    	return res;
	}

	public int updateBrokerCommission(List<Map<String, Object>> policyTypeList, BrokerMgmBean bean) {
		LogManager.push("ENTER-->Method to updateBrokerCommission");
		int res=0;
    	try
    	{
			String query="UPDATE BROKER_COMMISSION_DETAIL SET POLICY_FEE=?,OTHER_FEE=?,SUMINSURED_START=?,SUMINSURED_END=?,"
					+ "EFFECTIVE_DATE=TO_DATE(?,'DD/MM/YYYY'),END_DATE=TO_DATE(?,'DD/MM/YYYY') WHERE AGENCY_CODE=? AND PRODUCT_ID=? "
					+ "AND POLICYTYPE=?";
			LogManager.info("Query===>" + query);
			for(int i=0;i<policyTypeList.size();i++){
				res=this.mytemplate.update(query, new Object[]{
						StringUtils.isBlank(bean.getPolicyFee().get(i))?"0":bean.getPolicyFee().get(i),
						StringUtils.isBlank(bean.getOtherFee().get(i))?"0":bean.getOtherFee().get(i),
						StringUtils.isBlank(bean.getSumInsStart().get(i))?"0":bean.getSumInsStart().get(i),
						StringUtils.isBlank(bean.getSumInsEnd().get(i))?"0":bean.getSumInsEnd().get(i),
						bean.getEffectiveDate(),bean.getEndDate(),
						bean.getAgencyCode(),bean.getProductID(),policyTypeList.get(i).get("POLICYTYPE_ID")});
			}
    	}catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    	LogManager.info("updateBrokerCommission() - Exit");
    	return res;
	}

	public void brokerCommDtl(BrokerMgmBean bean) {
		LogManager.push("ENTER-->Method to brokerCommDtl");
		List<Map<String, Object>> result=new ArrayList<Map<String,Object>>();
		 try{
			  String query="SELECT TO_CHAR(EFFECTIVE_DATE,'DD/MM/YYYY') EFFECTIVE_DATE, TO_CHAR(END_DATE,'DD/MM/YYYY') END_DATE,POLICY_FEE,"
			  		+ "OTHER_FEE,SUMINSURED_START,SUMINSURED_END FROM BROKER_COMMISSION_DETAIL BCD WHERE AGENCY_CODE=? AND PRODUCT_ID=?"
			  		+ "AND AMEND_ID=(SELECT MAX(AMEND_ID) FROM BROKER_COMMISSION_DETAIL BCC WHERE BCD.AGENCY_CODE=BCC.AGENCY_CODE AND BCD.PRODUCT_ID=BCC.PRODUCT_ID)"; 
			  LogManager.info("Query===>" + query);
			  result= this.mytemplate.queryForList(query,new Object[]{bean.getAgencyCode(),bean.getProductID()});
			  if(result!=null && result.size()>0){
				  List<String> sumInsStart=new ArrayList<String>();
				  List<String> sumInsEnd=new ArrayList<String>();
				  List<String> policyFee=new ArrayList<String>();
			      List<String> otherFee=new ArrayList<String>();
				  
			      for(int i=0;i<result.size();i++){
			    	  sumInsStart.add(result.get(i).get("SUMINSURED_START")==null?"":result.get(i).get("SUMINSURED_START").toString());
			    	  sumInsEnd.add(result.get(i).get("SUMINSURED_END")==null?"":result.get(i).get("SUMINSURED_END").toString());
			    	  policyFee.add(result.get(i).get("POLICY_FEE")==null?"":result.get(i).get("POLICY_FEE").toString());
			    	  otherFee.add(result.get(i).get("OTHER_FEE")==null?"":result.get(i).get("OTHER_FEE").toString());
			      }
			      bean.setSumInsStart(sumInsStart);
			      bean.setSumInsEnd(sumInsEnd);
			      bean.setPolicyFee(policyFee);
			      bean.setOtherFee(otherFee);
			      bean.setEffectiveDate(result.get(0).get("EFFECTIVE_DATE")==null?"":result.get(0).get("EFFECTIVE_DATE").toString());
			      bean.setEndDate(result.get(0).get("END_DATE")==null?"":result.get(0).get("END_DATE").toString());
			      
			  }
		 }catch (Exception e) {
			e.printStackTrace(); 
		}
	}

	public void loginUserDtl (BrokerMgmBean bean) {
		LogManager.push("ENTER-->Method to loginUserDtl");
		List<Map<String, Object>> result=new ArrayList<Map<String,Object>>();
		 try{
			  String query="SELECT MIN_DISCOUNT,MAX_DISCOUNT,MIN_LOADING,MAX_LOADING,VOLUME_DISCOUNT_LIMIT,CORPORATE_DISCOUNT_LIMIT,"
			  		+ "SPECIAL_DISCOUNT_LIMIT,SCHEME_ID FROM LOGIN_USER_DETAILS WHERE AGENCY_CODE =? AND PRODUCT_ID =? order by SCHEME_ID"; 
			  LogManager.info("Query===>" + query);
			  result= this.mytemplate.queryForList(query,new Object[]{bean.getAgencyCode(),bean.getProductID()});
			  if(result!=null && result.size()>0){
				 
				  List<String> loadingStart=new ArrayList<String>();
				  List<String> loadingEnd=new ArrayList<String>();
				  List<String> discountStart=new ArrayList<String>();
			      List<String> discountEnd=new ArrayList<String>();
			      List<String> volumeDiscount=new ArrayList<String>();
			      List<String> corporateDiscount=new ArrayList<String>();
			      List<String> specialDiscount=new ArrayList<String>();
				  
			      for(int i=0;i<result.size();i++){
			    	  loadingStart.add(result.get(i).get("MIN_LOADING")==null?"":result.get(i).get("MIN_LOADING").toString());
			    	  loadingEnd.add(result.get(i).get("MAX_LOADING")==null?"":result.get(i).get("MAX_LOADING").toString());
			    	  discountStart.add(result.get(i).get("MIN_DISCOUNT")==null?"":result.get(i).get("MIN_DISCOUNT").toString());
			    	  discountEnd.add(result.get(i).get("MAX_DISCOUNT")==null?"":result.get(i).get("MAX_DISCOUNT").toString());
			    	  volumeDiscount.add(result.get(i).get("VOLUME_DISCOUNT_LIMIT")==null?"":result.get(i).get("VOLUME_DISCOUNT_LIMIT").toString());
			    	  corporateDiscount.add(result.get(i).get("CORPORATE_DISCOUNT_LIMIT")==null?"":result.get(i).get("CORPORATE_DISCOUNT_LIMIT").toString());
			    	  specialDiscount.add(result.get(i).get("SPECIAL_DISCOUNT_LIMIT")==null?"":result.get(i).get("SPECIAL_DISCOUNT_LIMIT").toString());
			      }
			      bean.setLoadingStart(loadingStart);
			      bean.setLoadingEnd(loadingEnd);
			      bean.setDiscountStart(discountStart);
			      bean.setDiscountEnd(discountEnd);
			      bean.setVolumeDiscount(volumeDiscount);
			      bean.setCorporateDiscount(corporateDiscount);
			      bean.setSpecialDiscount(specialDiscount);
			      
			  }
		 }catch (Exception e) {
			e.printStackTrace(); 
		}
	}
	
	public List<Map<String, Object>> getSchemeList(BrokerMgmBean bean,String branchCode) {
		List<Map<String, Object>> res=new ArrayList<Map<String, Object>>();
		try {
			String query="SELECT SCHEME_ID,SCHEME_NAME FROM OFS_SCHEME_MASTER WHERE STATUS='Y' AND PRODUCT_ID=? AND BRANCH_CODE=? order by DISPLAY_ORDER";
			Object[] args=new Object[]{bean.getProductID(),branchCode};
			LogManager.info("getSchemeList Query===>" + queryFrammer(query, args));
			res=this.mytemplate.queryForList(query,args);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public int insertLoginUserDtl(Object[] info, Object[] obj1, List<Map<String, Object>> schemeList,BrokerMgmBean bean) {
		LogManager.push("ENTER-->Method to insertLoginUserDtl");
		int res=0;
		String amendId="";
    	try
    	{
    		//String amendIdQuery="SELECT NVL(MAX(AMEND_ID)+1,0) FROM BROKER_COMMISSION_DETAIL WHERE AGENCY_CODE=? AND PRODUCT_ID=?";
    		//amendId=(String) this.mytemplate.queryForObject(amendIdQuery,new Object[]{bean.getAgencyCode(),bean.getProductID()}, String.class);
			String query="INSERT INTO LOGIN_USER_DETAILS(USER_ID,USER_NAME,LOGIN_ID,AGENCY_CODE,OA_CODE, PRODUCT_ID,COMPANY_ID,COMMISSION,INSURANCE_START_LIMIT,"
					+ "INSURANCE_END_LIMIT,DISCOUNT_OF_PREMIUM,RELATIVE_USER_ID,AMEND_ID, INCEPTION_DATE,EXPIRY_DATE,EFFECTIVE_DATE,ENTRY_DATE,REMARKS,STATUS,"
					+ "CUSTOMER_ID,MIN_PREMIUM_AMOUNT,BACK_DATE_ALLOWED,PRO_COMMISSION, PRO_START_DATE,PRO_EXPIRY_DATE,LOADING_OF_PREMIUM, "
					+ "PROVISION_FOR_PREMIUM,FREIGHT_DEBIT_OPTION,PAY_RECEIPT_STATUS, RECEIPT_STATUS, OPEN_COVER_NO, SPECIAL_DISCOUNT,"
					+ " SCHEME_ID,MIN_LOADING, MAX_LOADING, MIN_DISCOUNT, MAX_DISCOUNT,VOLUME_DISCOUNT_LIMIT,CORPORATE_DISCOUNT_LIMIT,SPECIAL_DISCOUNT_LIMIT,ISSUERTYPE,IS_B2C) "
					+ "VALUES(?,?,(SELECT LOGIN_ID FROM LOGIN_MASTER WHERE AGENCY_CODE=?),?,?,?,?,?,?,?,?,?,?,SYSDATE,?,?,SYSDATE,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,'88888','N')";
			
			for(int i=0;i<schemeList.size();i++){
				Object[] info1={schemeList.get(i).get("SCHEME_ID")==null?"":schemeList.get(i).get("SCHEME_ID"),
								bean.getLoadingStart().get(i)==null?"":bean.getLoadingStart().get(i),bean.getLoadingEnd().get(i)==null?"":bean.getLoadingEnd().get(i),
								bean.getDiscountStart().get(i)==null?"":bean.getDiscountStart().get(i),bean.getDiscountEnd().get(i)==null?"":bean.getDiscountEnd().get(i),
								bean.getVolumeDiscount().get(i)==null?"":bean.getVolumeDiscount().get(i),
								bean.getCorporateDiscount().get(i)==null?"":bean.getCorporateDiscount().get(i),
								bean.getSpecialDiscount().get(i)==null?"":bean.getSpecialDiscount().get(i)}; 
				
				List list = new ArrayList(Arrays.asList(info));  
				list.addAll(Arrays.asList(info1));     
				Object[] newObj = list.toArray();
				LogManager.info("insertLoginUserDtl Query===>" + queryFrammer(query, replaceEmptyIfNull(newObj)));
				res=this.mytemplate.update(query, replaceEmptyIfNull(newObj));
				
			}
			if(obj1!=null){
				query=getQuery("UPD_PRODUCT_DET_LM");
				LogManager.info("insertLoginUserDtl LM query===>" + queryFrammer(query, obj1));
				this.mytemplate.update(query, obj1);
			}
    	}catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    	LogManager.info("insertLoginUserDtl() - Exit");
    	return res;
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

	public int updateLoginUserDtl(Object[] info, Object[] obj1, List<Map<String, Object>> schemeList,BrokerMgmBean bean) {
		LogManager.push("ENTER-->Method to updateLoginUserDtl");
		int res=0;
		String amendId="";
    	try
    	{
    		String query="update login_user_details set COMMISSION=?,INSURANCE_END_LIMIT=?,STATUS=?,DISCOUNT_OF_PREMIUM=?,MIN_PREMIUM_AMOUNT=?"
					+ " ,BACK_DATE_ALLOWED=?,LOADING_OF_PREMIUM=?,pay_receipt_status=?,FREIGHT_DEBIT_OPTION=?,PROVISION_FOR_PREMIUM=?, "
					+ "RECEIPT_STATUS=?, open_cover_no=?, SPECIAL_DISCOUNT=?, MIN_LOADING=?, MAX_LOADING=?, MIN_DISCOUNT=?, MAX_DISCOUNT=?,"
					+ "VOLUME_DISCOUNT_LIMIT=?,CORPORATE_DISCOUNT_LIMIT=?,SPECIAL_DISCOUNT_LIMIT=? where AGENCY_CODE=? and product_id=? and SCHEME_ID=?";
			
			for(int i=0;i<schemeList.size();i++){
				Object[] info1={
								bean.getLoadingStart().get(i)==null?"":bean.getLoadingStart().get(i),bean.getLoadingEnd().get(i)==null?"":bean.getLoadingEnd().get(i),
								bean.getDiscountStart().get(i)==null?"":bean.getDiscountStart().get(i),bean.getDiscountEnd().get(i)==null?"":bean.getDiscountEnd().get(i),
								bean.getVolumeDiscount().get(i)==null?"":bean.getVolumeDiscount().get(i),
								bean.getCorporateDiscount().get(i)==null?"":bean.getCorporateDiscount().get(i),
								bean.getSpecialDiscount().get(i)==null?"":bean.getSpecialDiscount().get(i),
								bean.getAgencyCode(), bean.getProductID(),	
								schemeList.get(i).get("SCHEME_ID")==null?"":schemeList.get(i).get("SCHEME_ID")
								}; 
				
				List list = new ArrayList(Arrays.asList(info));  
				list.addAll(Arrays.asList(info1));     
				Object[] newObj = list.toArray();
				LogManager.info("updateLoginUserDtl Query===>" + queryFrammer(query, replaceEmptyIfNull(newObj)));
				res=this.mytemplate.update(query, replaceEmptyIfNull(newObj));
				
			}
			if(obj1!=null){
				query=getQuery("UPD_PRODUCT_DET_LM");
				LogManager.info("Query===>" + query);
				this.mytemplate.update(query, obj1);
			}
    	}catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    	LogManager.info("updateLoginUserDtl() - Exit");
    	return res;
	}

}