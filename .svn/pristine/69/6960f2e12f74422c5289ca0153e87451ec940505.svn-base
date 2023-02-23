package com.maan.quotation.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.core.RowMapper;

import com.maan.adminnew.lcMaster.LCMasterBean;
import com.maan.common.DBConstants;
import com.maan.common.LogManager;
import com.maan.common.MyJdbcTemplate;
import com.maan.quotation.QuotationAction;
import com.maan.services.util.runner;
import com.maan.webservice.dao.PolicyGenerationDAO;
import com.opensymphony.xwork2.util.LocalizedTextUtil;

public class QuotationDAO extends MyJdbcTemplate{
	private String sql;
	@SuppressWarnings("unchecked")
	public List<Object> getOptionsList(String option, Object[] objects)
	{
		LogManager.info("getOptionsList - Enter || option: "+option);
		List<Object> list=new ArrayList<Object>();
		try {
			//if(!("branch".equalsIgnoreCase(option)||"broker".equalsIgnoreCase(option))){
			if(!("branch".equalsIgnoreCase(option))){
				if("commodity_oc".equalsIgnoreCase(option)) {
					sql=getQuery(DBConstants.COMMODITY_OC);
				}else if("commodity".equalsIgnoreCase(option)) {
					sql=getQuery(DBConstants.COMMODITY);
				}
				else {
					sql=getQuery(DBConstants.OPTION);
					objects[0]=option;
				}
				LogManager.info("sql==>"+sql);
				for (int i = 0; i < objects.length; i++) {
					if(objects[i]==null){
						objects[i]="";
					}
				}
				LogManager.info("args[] ==> "+StringUtils.join(objects, ","));
				list = this.mytemplate.queryForList(sql, objects);

			}else if("branch".equalsIgnoreCase(option)){
				sql=getQuery("issuer.branch");
				list = this.mytemplate.queryForList(sql, objects);
			}/*else if("broker".equalsIgnoreCase(option)){
				sql=getQuery("issuer.broker");
				list = this.mytemplate.queryForList(sql, objects);
			}*/
		}catch(Exception e) {
			LogManager.debug("Exception @ "+e);
		}
		LogManager.popRemove();
		LogManager.info("getOptionsList - Exit || Result: "+list.size());
		return list;	
	}
	public Map<String, Object> getBranchInfo(String branchCode)
	{
		LogManager.info("getBranchInfo - Enter || branchCode: "+branchCode);
		Map<String, Object> list=new HashMap<String, Object>();
		try
		{
			sql=getQuery(DBConstants.BRANCH_INFO);
			list = this.mytemplate.queryForMap(sql, new String[]{branchCode});
		}catch(Exception e)
		{
			LogManager.debug("Exception @ "+e);
		}
		LogManager.popRemove();
		LogManager.info("getBranchInfo - Exit || Result: "+list.size());
		return list;	
	}
	public String addCommodity(String applicationNo, String refNo, List<Object> commList)
	{
		LogManager.info("addCommodity - Enter || applicationNo: "+applicationNo);
		Map<String, String> commInfo=null;
		try
		{
			if(commList!=null && commList.size()>0)
			{
				sql=getQuery(DBConstants.COMMODITY_DELETE);
				this.mytemplate.update(sql, new String[]{applicationNo});
				sql=getQuery(DBConstants.COMMODITY_ADD);
				for (int i = 0; i < commList.size(); i++) {
					commInfo=(Map<String, String>)commList.get(i);
					this.mytemplate.update(sql, new String[]{commInfo.get("COMMODITY_ID"),
							commInfo.get("COMMODITY_NAME"),commInfo.get("SUM_INSURED"),
							commInfo.get("SUPPLIER_NAME"),commInfo.get("PACKAGE_DESC"),
							commInfo.get("INVOICE_NUMBER"),commInfo.get("INVOICE_DATE"),
							commInfo.get("FRAGILE"),refNo,applicationNo,commInfo.get("EXCESS_DESCRIPTION")});
				}
			}
		}catch(Exception e)
		{
			LogManager.debug("Exception @ "+e);
		}
		LogManager.popRemove();
		LogManager.info("addCommodity - Exit");
		return "";	
	}
	public String updateCommodity(String applicationNo, List<Object> commList)
	{
		LogManager.info("addCommodity - Enter || applicationNo: "+applicationNo);
		Map<String, String> commInfo=null;
		try
		{
			if(commList!=null && commList.size()>0)
			{
				sql=getQuery(DBConstants.COMMODITY_UPDATE);
				for (int i = 0; i < commList.size(); i++) {
					commInfo=(Map<String, String>)commList.get(i);
					this.mytemplate.update(sql, new String[]{commInfo.get("SUPPLIER_NAME"),commInfo.get("PACKAGE_DESC"),
							commInfo.get("INVOICE_NUMBER"),commInfo.get("INVOICE_DATE"),commInfo.get("EXCESS_DESCRIPTION"),applicationNo,commInfo.get("COMMODITY_ID")});
				}
			}
		}catch(Exception e)
		{
			LogManager.debug("Exception @ "+e);
		}
		LogManager.popRemove();
		LogManager.info("addCommodity - Exit");
		return "";	
	}
	public String insertOrUpdateQuote(String applicationNo, Object[] args, String quoteType)
	{
		LogManager.info("insertOrUpdateQuote - Enter || applicationNo: "+applicationNo+"quoteType: "+quoteType+" args: "+StringUtils.join(args, ","));
		try
		{
			if("F".equalsIgnoreCase(quoteType)){
				sql=getQuery(DBConstants.QUOTE_ADD);
			}else{
				sql=getQuery(DBConstants.QUOTE_UPDATE);
			}
			LogManager.info("sql==>"+sql);
			LogManager.info("args: "+StringUtils.join(args, ","));
			for (int i = 0; i < args.length; i++) {
				if(args[i]==null){
					args[i]="";
				}
			}
			this.mytemplate.update(sql, args);
		}catch(Exception e)
		{
			LogManager.debug("Exception @ "+e);
		}
		LogManager.popRemove();
		LogManager.info("insertOrUpdateQuote - Exit");
		return "";	
	}
	@SuppressWarnings("unchecked")
	public List<String> getQuoteInfo(final QuotationAction qa)
	{
		LogManager.info("getQuoteInfo - Enter || applicationNo: "+qa.getApplicationNo()+" quoteNo: "+qa.getQuoteNo());
		List<String> list=null;
		try
		{
			sql=getQuery(DBConstants.QUOTE_EDIT);
			list = (List<String>) this.mytemplate.query(sql, new Object[]{StringUtils.defaultIfEmpty(qa.getApplicationNo(), ""),StringUtils.defaultIfEmpty(qa.getQuoteNo(), "")},new RowMapper() {
				public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
					qa.setCustomerName(rs.getString("CUSTOMER_NAME"));
					qa.setCity(rs.getString("EMIRATE"));
					qa.setMobileNo(rs.getString("MOBILE_NUMBER"));
					qa.setModeOfTransport(rs.getString("MODE_OF_TRANSPORT"));
					qa.setOriginCountry(rs.getString("ORIGINATING_COUNTRY"));
					qa.setDestCountry(rs.getString("DESTINATION_COUNTRY"));
					qa.setOriginCity(rs.getString("ORIGINATING_COUNTRY_CITY"));
					qa.setDestCity(rs.getString("DESTINATION_COUNTRY_CITY"));
					qa.setOriginCityName(rs.getString("ORIGIN_CITY_NAME"));
					qa.setDestCityName(rs.getString("DEST_CITY_NAME"));
					qa.setOriginWarehouse(rs.getString("ORIGINATING_WAREHOUSE_COVERAGE"));
					qa.setDestWarehouse(rs.getString("DESTINATION_WAREHOUSE_COVERAGE"));
					qa.setSaleTerm(rs.getString("SALETERM_VALUE"));
					qa.setCurrency(rs.getString("CURRENCY"));
					qa.setSettlingAgent(rs.getString("SETTLING_AGENT"));
					qa.setAgentOthers(rs.getString("SETTLING_AGENT_NAME"));
					qa.setPolicyStartDate(rs.getString("POLICY_START_DATE") == null?"":dateFormat(rs.getString("POLICY_START_DATE")));
					qa.setSailingDate(rs.getString("SAILING_DATE") == null?"":dateFormat(rs.getString("SAILING_DATE")));
					qa.setVesselName(rs.getString("VESSEL_NAME"));
					qa.setLcNo(rs.getString("LC_NUMBER"));
					qa.setLcBank(rs.getString("ISSUING_BANK"));
					qa.setLcDate(rs.getString("LC_DATE") == null?"":dateFormat(rs.getString("LC_DATE")));
					qa.setPartialShipment(rs.getString("PARTIAL_SHIPMENT"));
					qa.setBlNo(rs.getString("BL_AWB_NUMBER"));
					qa.setBlDate(rs.getString("BL_AWB_DATE") == null?"":dateFormat(rs.getString("BL_AWB_DATE")));
					qa.setCover(rs.getString("COVERAGES"));
					qa.setConveyance(rs.getString("SEA_COVERAGES"));
					qa.setWarSrcc(rs.getString("WSRCC"));
					qa.setTolerance(rs.getString("TOLERANCE"));
					qa.setPackageCode(rs.getString("PACKAGE_TYPE"));
					qa.setSaleTermPercent(rs.getString("SALETERM_PERCENT"));
					qa.setRefNo(rs.getString("REFERENCE_NUMBER"));
					qa.setTitle(rs.getString("CUST_TITLE"));
					qa.setCoreAppCode(rs.getString("CUST_CORE_APP_CODE"));
					qa.setAddress1(rs.getString("ADDRESS1"));
					qa.setAddress2(rs.getString("ADDRESS2"));
					qa.setEmail(rs.getString("EMAIL"));
					qa.setPoBox(rs.getString("PO_BOX"));
					qa.setBranchCode(rs.getString("BRANCH_CODE"));
					qa.setBrokerCode(rs.getString("BROKER_CODE"));
					//qa.setProductId(rs.getString("PRODUCT_ID"));
					qa.setApplicationNo(rs.getString("APPLICATION_NO"));
					qa.setQuoteNo(rs.getString("QUOTE_NO"));
					qa.setOpenCoverNo(rs.getString("OPENCOVER_POLICY_NUMBER"));
					qa.setExecutive(rs.getString("AC_EXECUTIVE_ID"));
					qa.setCustomerId(rs.getString("CUSTOMER_ID"));
					qa.setExposureCurrency(rs.getString("EXPOSURE_CURRENCY"));
					qa.setShipmentExposure(rs.getString("SHIPMENT_EXPOSURE"));
					qa.setCustContractNo(rs.getString("CUST_CONTRACT_NO"));
					qa.setCustFmsCaseNo(rs.getString("CUST_FMS_CASE_NO"));
					qa.setCustRefNo(rs.getString("CUST_REFERENCE_NO"));
					qa.setCustArNo(rs.getString("CUST_AR_NO"));
					qa.setVesselId(rs.getString("VESSEL_ID"));
					qa.setPromotionalCode(rs.getString("PROMOTIONAL_CODE"));
					qa.setConsigneeDetail(rs.getString("CONSIGNEE_DET"));
					qa.setSpecialTerm(rs.getString("SPECIAL_TERM"));
					qa.setAddCustomerName(rs.getString("ADD_CUST_NAME"));
					qa.setCoreCustomerName(rs.getString("core_customer_name"));
					qa.setNameAndCode(rs.getString("core_customer_name")+"("+rs.getString("CUST_CORE_APP_CODE")+")");
					qa.setChannelType(rs.getString("CHANNEL_TYPE"));
					qa.setExchageValue(rs.getString("EXCHANGE_RATE"));
					qa.setVia(rs.getString("VIA"));
					//qa.setLcAmount(rs.getString("LC_AMT"));
					/*qa.setLcexchageValue(rs.getString("LC_EXCHANGEVALUE"));
					qa.setLcCurrency(rs.getString("LC_CURRECNYID"));*/
					return null;
				}
			});
			sql=getQuery(DBConstants.COMMODITY_TOTAL);
			list=this.mytemplate.query(sql, new Object[]{qa.getRefNo()}, new RowMapper() {
				public Object mapRow(ResultSet rs, int rowNum) throws SQLException {				
					qa.setTotalCommodity(rs.getString("TOTAL_COMMODITY"));
					qa.setTotalSI(rs.getString("SUM_INSURED"));
					qa.setCommodity(rs.getString("COMMODITY_DESCRIPTION"));
					return null;
				}
			});
			//For Edit Clause and FinalizeYN
			Map<String,Object> checkYN = new PolicyGenerationDAO().getResultMap("QUOTE_FINALIZE_YN", new String[]{qa.getRefNo()});
			if(checkYN!=null){
	        	qa.setFinalizeYN(checkYN.get("FINALIZE_YN")==null?"N":checkYN.get("FINALIZE_YN").toString());
	        	//qa.setEditClausesYN(checkYN.get("EDIT_CLAUSES_YN")==null?"N":checkYN.get("EDIT_CLAUSES_YN").toString());
	        }
			if("RU".equalsIgnoreCase(qa.getQuoteStatus())){
		        qa.setFinalizeYN("N");
		        //qa.setEditClausesYN("N");
		    }
		}catch(Exception e)
		{
			LogManager.debug("Exception @ "+e);e.printStackTrace();
		}
		LogManager.popRemove();
		LogManager.info("getQuoteInfo - Exit || Result: "+list.size());
		return list;	
	}
	public List<Object> getCustomerSelectionList(String loginId,String searchValue,String openCoverNo)
	{
		LogManager.info("getCustomerSelectionList - Enter || "+loginId+","+searchValue);
		List<Object> customerList=null;		
		searchValue=searchValue==null?"":searchValue;
		try{
			if("".equalsIgnoreCase(searchValue)){
				if(StringUtils.isNotEmpty(openCoverNo) && !"0".equals(openCoverNo)){
					sql=getQuery(DBConstants.OPENCOVER_CUSTOMER_LIST);
					customerList=this.mytemplate.queryForList(sql,new String[]{openCoverNo});
				}else{
					sql=getQuery(DBConstants.CUSTOMER_SELECTION);
					customerList=this.mytemplate.queryForList(sql,new String[]{loginId});}
			}
			else
			{
				if(StringUtils.isNotEmpty(openCoverNo) && !"0".equals(openCoverNo)){
					sql=getQuery(DBConstants.OPENCOVER_CUSTOMER_BYNAME);
					customerList=this.mytemplate.queryForList(sql,new String[]{openCoverNo,"%"+searchValue+"%"});
				}else{			
					sql=getQuery(DBConstants.CUSTOMER_SELECTION_BYNAME);
					customerList=this.mytemplate.queryForList(sql,new String[]{loginId,"%"+searchValue+"%"});
				}
			}					
		}
		catch (Exception e) {			
			LogManager.debug("EXCEPTION @ { " + e + " }");
		}		
		LogManager.info("Query==>" + sql);
		LogManager.info("args: "+loginId+","+searchValue);
		LogManager.info("getCustomerSelectionList() - Exit size"+ customerList.size() );
		LogManager.popRemove();		
		return customerList;
	}
	public List<Object> getCoreCustomerSearch(String searchValue, String searchType) {
		List<Object> customerList=null;
		try {
			String custClass = "";
			if("CUSTOMER".equals(searchType) || "OC_CUSTOMER".equals(searchType)) {
				custClass = LocalizedTextUtil.findDefaultText("GET_CUSTOMER_CUST_CLASS",Locale.ENGLISH);;
			} else if("BROKER".equals(searchType)) {
				custClass = LocalizedTextUtil.findDefaultText("GET_BROKER_CUST_CLASS",Locale.ENGLISH);;
			}
			String sql = LocalizedTextUtil.findDefaultText("GET_CORE_CUSTOMER_LIST",Locale.ENGLISH, new String[]{custClass}) + " AND UPPER(CUST_NAME) LIKE '%"+ searchValue.toUpperCase() +"%'";
			LogManager.info("Query==> " + sql);
			customerList = this.mytemplate.queryForList(sql);
		}
		catch(Exception e) {
			LogManager.debug("EXCEPTION @ { " + e + " }");
		}
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
	public List<Object> getVesselListNew(String alpha)
	{
		LogManager.info("getVesselList - Enter || "+alpha);
		List<Object> vesselList=null;					
		LogManager.push("Method to get Vessel List");
		try{
			sql=getQuery(DBConstants.VESSEL_SELECTIION);
			vesselList=this.mytemplate.queryForList(sql,new String[]{"%"+alpha+"%"});			
		}
		catch (Exception e) {			
			LogManager.debug("EXCEPTION @ { " + e + " }");
		}		
		LogManager.info("Query==>" + sql);
		LogManager.info("getVesselList() - Exit: size"+ vesselList.size());
		LogManager.popRemove();		
		return vesselList;
	}
	public String checkExist(String dataValue,String datatype,String applicationNo)
	{
		String result=null;
		LogManager.push("checkLCNOExist");
		try{
			if("LCNO".equals(datatype))
			{
				sql=getQuery(DBConstants.LCNO_EXIST);
			}
			else {
				sql=getQuery(DBConstants.INVOICE_EXIST);	
			}
			result=(String)this.mytemplate.queryForObject(sql,new String[]{dataValue,applicationNo},String.class);			
		}
		catch (Exception e) {			
			LogManager.debug("EXCEPTION @ { " + e + " }");
		}		
		LogManager.info("Query==>" + sql);
		LogManager.info("Result - Exit:"+ result);
		LogManager.popRemove();		
		return result;
	}
	public String getBrokerLoginId(String brokerCode)
	{
		String result=null;
		LogManager.push("getBrokerLoginId");
		try{	
			sql=getQuery(DBConstants.BROKER_LOGINID);	
			result=(String)this.mytemplate.queryForObject(sql,new String[]{brokerCode},String.class);			
		}
		catch (Exception e) {			
			LogManager.debug("EXCEPTION @ { " + e + " }");
		}		
		LogManager.info("Query==>" + sql+"args :"+brokerCode);
		LogManager.info("Result - Exit:"+ result);
		LogManager.popRemove();		
		return result;
	}
	public String[][] getVesselList(String vesselName)
	{
		String result[][] = new String[0][0];
		String sql = "";
		String args[] = new String[0];
		try{
			sql = "SELECT VESSEL_ID,VESSEL_NAME,VESSEL_TYPE,VESSEL_CLASS,MANUFACTURE_YEAR FROM VESSEL_MASTER WHERE STATUS IN ('Y','R') AND UPPER(VESSEL_NAME) LIKE UPPER(?) ORDER BY VESSEL_NAME";
			result = runner.multipleSelection(sql,new String[]{"%"+vesselName+"%"});
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	public String getPolicyEndtType(String applicationNo)
	{
		LogManager.push("getPolicyEndtType - Enter || applicationNo: "+applicationNo);
		String endtType="";
		try{
			endtType=(String)this.mytemplate.queryForObject(getQuery("GET_POL_ENDT"),new String[]{applicationNo},String.class);
			if(StringUtils.isNotEmpty(endtType)){
				endtType=(String)this.mytemplate.queryForObject(getQuery("GET_QUOTE_ENDT_TYPE").replace("?",endtType),String.class);
			}
		}catch(Exception e){
			LogManager.debug(e);
		}
		LogManager.push("getPolicyEndtType - Exit || Result: " );
		return endtType;
	}
	public String getDirectStatus(String applicationNo,String branchCode)
	{
		LogManager.push("getDirectStatus - Enter || applicationNo: "+applicationNo+" branchCode: "+branchCode);
		String result="";
		try{
			sql=getQuery(DBConstants.DIRECT_STATUS);
			result=(String)this.mytemplate.queryForObject(sql,new String[]{branchCode,applicationNo},String.class);
		}
		catch (Exception e) {			
			LogManager.debug("EXCEPTION @ { " + e + " }");
		}	
		LogManager.info("getDirectStatus - Exit:"+ result);
		return result;
	}
	public String getbranchWiseCountry(String branchCode)
	{
		LogManager.push("getbranchWiseCountry - Enter: branchCode: "+branchCode);
		String result="";
		try{
			sql=getQuery(DBConstants.BRANCH_WISE_COUNTRY);
			result=(String)this.mytemplate.queryForObject(sql,new String[]{branchCode},String.class);
		}
		catch (Exception e) {			
			LogManager.debug("EXCEPTION @ { " + e + " }");
		}	
		LogManager.info("getbranchWiseCountry - Exit:"+ result);
		return result;
	}
	public boolean getDubaiTradeStatus(String brokerCode,String branchCode)
	{
		LogManager.push("getDubaiTradeStatus - Enter: branchCode: "+branchCode);
		boolean dubaiTradeStaus=false;
		try{
			sql=getQuery("GET_DUBAITRADE_STATUS");
			String result=(String)this.mytemplate.queryForObject(sql,new String[]{brokerCode, branchCode}, String.class);
			if(result!=null && "Y".equals(result))
				dubaiTradeStaus=true;
		}
		catch (Exception e) {			
			//LogManager.debug("EXCEPTION @ { " + e + " }");
		}	
		LogManager.info("getDubaiTradeStatus - Exit:"+ dubaiTradeStaus);
		return dubaiTradeStaus;
	}

	public List<Object> lcSelectionList(String ocNo, String searchValue, String exact) {
		LogManager.info("lcSelection - Enter || ocNo==>"+ocNo+"		searchValue==>"+searchValue);
		List<Object> lcSelectionList=null;
		try{
			sql="SELECT OCLM.BANK_ID,OPBM.BANK_NAME, OCLM.LC_ID, OCLM.LC_NUMBER, to_CHAR(OCLM.LC_DATE,'DD/MM/YYYY') LC_DATE, OCLM.LC_AMOUNT, OCLM.LC_CURRENCY_ID, nvl(OCLM.LC_BALANCE_AMOUNT,0) LC_BALANCE_AMOUNT  FROM OPEN_COVER_LC_MASTER OCLM, OPEN_COVER_BANK_MASTER OPBM WHERE OPBM.BANK_ID=OCLM.BANK_ID  and OCLM.OPEN_COVER_NO = ? and OPBM.AMEND_ID=(select max(opbmm.AMEND_ID) from OPEN_COVER_BANK_MASTER opbmm where opbmm.BANK_ID=opbm.BANK_ID and opbmm.status='Y')";
			if("Y".equals(exact) && StringUtils.isNotBlank(searchValue))
				sql=sql+" and upper(OCLM.LC_NUMBER)=upper('"+searchValue+"') order by OCLM.LC_DATE desc";
			else if(StringUtils.isNotBlank(searchValue))
				sql=sql+" and upper(OCLM.LC_NUMBER) like upper('%"+searchValue+"%') order by OCLM.LC_DATE desc";
			LogManager.info("Query==>" + sql);
			lcSelectionList=this.mytemplate.queryForList(sql,new String[]{ocNo});
		}
		catch (Exception e) {			
			LogManager.debug("EXCEPTION @ { " + e + " }");
		}
		return lcSelectionList;
	}

	public int lcNoExist(String lcNo, String ocNo) {
		LogManager.info("lcNoExist - Enter || lcNo==>"+lcNo);
		int lcCount=1;
		try{
			sql="select count(OPEN_COVER_NO) from OPEN_COVER_LC_MASTER OCLM where OCLM.lc_number=? and OCLM.OPEN_COVER_NO!=?";
			LogManager.info("Query==>" + sql);
			lcCount=this.mytemplate.queryForInt(sql,new String[]{lcNo, ocNo});
		}catch (Exception e) {
			LogManager.debug("EXCEPTION @ { " + e + " }");
		}
		return lcCount;
	}
	public List<Object>getLCOCDetail(String branchCode, final LCMasterBean bean, String from){
		List <Object> ocDetail=null;
		try{
			Object obj[];
			sql=getQuery("GET_LCOC_DETAILS_LIST");
			if("add".equals(from)){
				sql+=" and LC_number=? and LC_ID=?";
				obj=new Object[]{branchCode,branchCode,branchCode, bean.getOpenCover(), bean.getLcNum(), bean.getLcId()};
			}else 
				obj=new Object[]{branchCode,branchCode,branchCode, bean.getOpenCover()};
			LogManager.info("Query===>" + sql);
			for(Object a:obj)
				LogManager.info("args===>" + a);
			ocDetail=this.mytemplate.queryForList(sql,obj);
		}catch (Exception e) {			
			LogManager.debug("EXCEPTION @ { " + e + " }");
		}
		return ocDetail;
	}
	public int checkValidPromotionalCode(String promotionalCode, String branchCode) {
		LogManager.info("checkValidPromotionalCode - Enter || promotionalCode==>"+promotionalCode+"		branchCode==>"+branchCode);
		int validCount=0;
		try{
			sql=getQuery("CHECK_VALID_PROMOTIONAL_CODE");
			LogManager.info("Query==>" + sql);
			validCount=this.mytemplate.queryForInt(sql,new String[]{promotionalCode, branchCode});
		}catch (Exception e) {
			LogManager.debug("EXCEPTION @ { " + e + " }");
		}
		return validCount;
	}

	public void updateWSMarineQuote(String[] args) {
		LogManager.info("updateWSMarineQuote - Enter");
		try{
			sql=getQuery("UPD_WS_MARINE_QUOTE");
			LogManager.info("Query==>" + sql);
			LogManager.info("Args==>"+StringUtils.join(args, ",  "));
			this.mytemplate.update(sql, args);
		}catch (Exception e) {
			e.printStackTrace();
			LogManager.debug("updateWSMarineQuote - EXCEPTION @ { " + e + " }");
		}
	}
	public List<Map<String, Object>> searchList(String applicationNo,String branchCode){
		List<Map<String, Object>> searchList=null;		
		String query=getQuery("SER_COMM_SHOW");
		LogManager.push("query "+query+">>>>"+applicationNo+" branchCode==> "+branchCode);
		try{
			searchList=this.mytemplate.queryForList(query,new String[]{branchCode,applicationNo});
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return searchList;
	}
	public String addCommodity(String applicationNo, String refNo, List<Object> commList, String branchCode) {
		LogManager.info("addCommodity - Enter || applicationNo: "+applicationNo);
		Map<String, String> commInfo=null;
		try
		{
			if(commList!=null && commList.size()>0)
			{
				String sql1=getQuery(DBConstants.COMMODITY_DELETE);
				sql=getQuery(DBConstants.COMMODITY_ADD);
				for (int i = 0; i < commList.size(); i++) {
					commInfo=(Map<String, String>)commList.get(i);
					this.mytemplate.update(sql1, new String[]{applicationNo,commInfo.get("COMMODITY_ID")});
					Object[]  args = new Object[]{
							commInfo.get("COMMODITY_ID"),
							commInfo.get("COMMODITY_NAME"), 
							commInfo.get("SUM_INSURED"),
							commInfo.get("SUPPLIER_NAME"),
							commInfo.get("PACKAGE_DESC"),
							commInfo.get("INVOICE_NUMBER"),
							commInfo.get("INVOICE_DATE"),
							commInfo.get("FRAGILE"),refNo,applicationNo,commInfo.get("EXCESS_DESCRIPTION")};
					LogManager.info("Args==> " + StringUtils.join(args,","));
					this.mytemplate.update(sql, args);
				}
			}
		}catch(Exception e)
		{
			e.printStackTrace();
			LogManager.debug("Exception @ "+e);
		}
		LogManager.popRemove();
		LogManager.info("addCommodity - Exit");
		return "";	
	}
	public String deleteCommodity(String applicationNo,String  commodityCode)
	{
		LogManager.info("deleteCommodity - Enter || applicationNo: "+applicationNo +commodityCode);
		try
		{
			sql=getQuery(DBConstants.COMMODITY_DELETE);
			this.mytemplate.update(sql, new String[]{applicationNo,commodityCode});
		}catch(Exception e)
		{
			LogManager.debug("Exception @ "+e);
		}
		LogManager.popRemove();
		LogManager.info("deleteCommodity - Exit");
		return "";	
	}
	public int getCommodityExist(String commodityId, String applicationno){
		LogManager.push("getapplicationno - Enter: commodityId: "+commodityId);
		LogManager.push("getapplicationno - Enter: applicationno: "+applicationno);
		LogManager.push("Enter into getCommodityExist()");
	 	int count=0;
	 	try{
	 		sql=getQuery("COMMODITY_EXIST");
	 		LogManager.push("query =>"+sql);
	        count=this.mytemplate.queryForInt(sql,new String[]{commodityId,applicationno});
	 		}
	        catch(Exception e){
	     		LogManager.push("Exception in getUserInfo()"+e);
	     	}
	     	LogManager.push("Exit from getCommodityExist()");
	     	return count;
		}
	public String getFragile(String category,String appNo) {
		LogManager.push("getFragile() Enter ");
		String fragile="";
		try{
			sql="SELECT   Commodity_Name_Desc, Fragile FROM   Open_Cover_Commodity_Master Occm,Open_Cover_Position_Master Ocpm WHERE Occm.Proposal_No = Ocpm.Proposal_No AND Occm.Amend_Id = (SELECT   MAX (Amend_Id) FROM   Open_Cover_Commodity_Master Oc WHERE   Oc.Proposal_No = Occm.Proposal_No)  AND Ocpm.Status = 'P' AND Ocpm.Open_cover_No=? AND occm.commodity_id =?";
			Object args[]={appNo,category};
			LogManager.push("Query >>"+sql);
			LogManager.push("args>>>>"+StringUtils.join(args,","));
			Map result = this.mytemplate.queryForMap(sql,args);
			fragile=result.get("FRAGILE").toString();
		}catch (Exception e) {
			e.printStackTrace();
			LogManager.push("getFragile() Exception "+e);	
		}
		LogManager.push("getFragile() Exit ");
		return fragile;
	}
	public double getSaleTermValue(String saleTermPercent, String branchCode) {
		double result = 0;
		try {
			String query = getQuery("GET_SALETERM_VAL");
			String res = (String) this.mytemplate.queryForObject(query, new Object[]{saleTermPercent,branchCode},String.class);
			result = StringUtils.isBlank(res)?0:Double.valueOf(res);
		}
		catch(Exception exception) {
			LogManager.debug("getSaleTermValue()==> " + exception);
		}
		return result;
	}
	public double getToleranceValue(String tolerance, String branchCode) {
		double result = 0;
		try {
			String query = getQuery("GET_TOLERANCE_PERCENT");
			String res = (String) this.mytemplate.queryForObject(query, new Object[]{tolerance,branchCode},String.class);
			result = StringUtils.isBlank(res)?0:Double.valueOf(res);
		}
		catch(Exception exception) {
			LogManager.debug("getToleranceValue()==> " + exception);
		}
		return result;
	}
	public void setOpenCustomerInfo(QuotationAction bean, String openCoverNo) {
		try {
			String query = getQuery("GET_OPENCUSTOMER_INFO");
			Map<String, Object> resultMap = this.mytemplate.queryForMap(query, new Object[]{openCoverNo});
			bean.setTitle(resultMap.get("TITLE")==null?"":resultMap.get("TITLE").toString());
			bean.setAddress1(resultMap.get("ADDRESS1")==null?"":resultMap.get("ADDRESS1").toString());
			bean.setAddress2(resultMap.get("ADDRESS2")==null?"":resultMap.get("ADDRESS2").toString());
			bean.setMobileNo(resultMap.get("MOBILE")==null?"":resultMap.get("MOBILE").toString());
			bean.setCity(resultMap.get("EMIRATE")==null?"":resultMap.get("EMIRATE").toString());
			bean.setPoBox(resultMap.get("POBOX")==null?"":resultMap.get("POBOX").toString());
			bean.setCustomerName(resultMap.get("FIRST_NAME")==null?"":resultMap.get("FIRST_NAME").toString());
			bean.setCoreAppCode(resultMap.get("MISSIPPI_CUSTOMER_CODE")==null?"":resultMap.get("MISSIPPI_CUSTOMER_CODE").toString());
			bean.setCustomerId(resultMap.get("CUSTOMER_ID")==null?"":resultMap.get("CUSTOMER_ID").toString());
			bean.setEmail(resultMap.get("EMAIL")==null?"":resultMap.get("EMAIL").toString());
			bean.setCustArNo(resultMap.get("CUST_AR_NO")==null?"":resultMap.get("CUST_AR_NO").toString());
		}
		catch(Exception exception) {
			LogManager.debug("setOpenCustomerInfo()==> " + exception);
		}
	}
	public String updateExcessDesc(String cover,String applicationNo,String openCoverNo) {
		int status=0;
		LogManager.info("updateExcessDesc - Enter || applicationNo: "+applicationNo);
		try
		{
			sql=getQuery("UPD_EXCESS_DESC");
			status = this.mytemplate.update(sql, new String[]{cover,openCoverNo,applicationNo});
		}catch(Exception e)
		{
			LogManager.debug("Exception @ "+e);
		}
		LogManager.popRemove();
		LogManager.info("updateExcessDesc - Exit");
		return "";	
	}
	public int validateCoverReferral(String commodityId, String branchCode,	String coverId) {
		int result = 0;
		try {
			String query = getQuery("COVER_REFERAL_VALIDATE");
			result = this.mytemplate.queryForInt(query,new Object[]{commodityId,branchCode,coverId});
		}
		catch(Exception ex) {
			LogManager.debug(ex);
		}
		return result;
	}
	public String dateFormat(String name){
		String date = "";
		if(StringUtils.contains(name, "-")) {
			String arr[]=name.split("-");
			date = arr[1]+"/"+arr[0]+"/"+arr[2];
		} else if(StringUtils.contains(name, "/")) {
			String arr[]=name.split("/");
			date = arr[1]+"/"+arr[0]+"/"+arr[2];
		}
		return date;
	}
}