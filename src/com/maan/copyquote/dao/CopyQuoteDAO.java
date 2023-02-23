package com.maan.copyquote.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.core.SqlInOutParameter;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.jdbc.object.StoredProcedure;

import com.maan.common.DBConstants;
import com.maan.common.LogManager;
import com.maan.common.MyJdbcTemplate;

import oracle.jdbc.OracleTypes;

public class CopyQuoteDAO  extends MyJdbcTemplate{
	public String sql="";
	
	public List<Object> getCopyQuoteSearch(String type,String value,String openCoverNo, String productId)
	{
		List<Object> list=null;	
		try
		{
		if(type.equals("quoteNo"))
		{ 
			sql=getQuery(DBConstants.COPYQUOTE_QUOTENO);		
		}
		else if(type.equals("policyNo")){
			sql=getQuery(DBConstants.COPYQUOTE_POLICYNO);
		}
		else
		{
			sql=getQuery(DBConstants.COPYQUOTE_CUSTNAME);
		}		
		list=this.mytemplate.queryForList(sql, new String[]{value,productId});		
		LogManager.info("args[] ==> "+type+","+value);
		LogManager.info("Qurey ==> "+sql);
		}
		catch(Exception e)
		{
			LogManager.debug("Exception @ "+e);
		}
		LogManager.popRemove();
		LogManager.info("getOptionsList - Exit || Result: "+list.size());
		return list;		
	}
	public Map<String, Object> copyQuote(String loginId,String quoteNo, String type, String typeId, String issuer)
	{
		LogManager.info("copyQuote - Enter || loginId: "+loginId+" quoteNo: "+quoteNo+" type: "+type+" typeId: "+typeId);
		LogManager.info("args[]"+loginId+","+quoteNo);
		//CopyQuoteStroredProcedure sp=new CopyQuoteStroredProcedure(this.mytemplate.getDataSource(), "CopyQuote_newversion");
		CopyQuoteStroredProcedure sp=new CopyQuoteStroredProcedure(this.mytemplate.getDataSource(), "CopyQuote");
		Map<String, String> inParams=new HashMap<String, String>();
		inParams.put("TYPE", type);
		inParams.put("LOGIN_ID", loginId);
		inParams.put("ISSUER_ID", issuer);
		inParams.put("END_TYPE", typeId);
		inParams.put("QUOTE_NO", quoteNo);	
		inParams.put("CUSTOMER_NAME", "");	
		inParams.put("ERROR_STATUS", "");
		LogManager.info("copyQuote - Exit");
		return sp.executeProc(inParams);
	}
	public List<Object> getTravelCopyQuoteSearch(String type,String value,String loginID,String productId) {
		List<Object> list=null;	
		try
		{
			if(type.equals("quoteNo"))
			{ 
				sql=getQuery(DBConstants.TRAVEL_COPYQUOTE_QUOTENO);		
			}
			else if(type.equals("policyNo")){
				sql=getQuery(DBConstants.TRAVEL_COPYQUOTE_POLICYNO);
			}
			else
			{
				sql=getQuery(DBConstants.TRAVEL_COPYQUOTE_CUSTNAME);
			}		
			list=this.mytemplate.queryForList(sql, new String[]{value,loginID,productId});		
			LogManager.info("args[] ==> "+type+","+value);
			LogManager.info("Qurey ==> "+sql);
		}
		catch(Exception e) {
			LogManager.debug("Exception @ "+e);
		}
		LogManager.popRemove();
		LogManager.info("getTravelOptionsList - Exit || Result: "+list.size());
		return list;		
	}
	
	public Map<String,Object> travelcopyQuote(String loginId,String quoteNo, String productId,String branchCode, String endtTypeId, String vehicleId, String type) {
		Map<String,Object> outputValues = null;
		//LogManager.info("travelcopyQuote - Enter || loginId: "+loginId+" quoteNo: "+quoteNo);
		//LogManager.info("args[]"+(StringUtils.isBlank(typeId)?"Normal":"Endt")+","+quoteNo+","+(","+typeId+",")+","+loginId+","+branchCode+","+productId+",,,,,");
		String spName = "";
	try{
		LogManager.info("travelcopyQuote - Enter || loginId: "+loginId+" quoteNo: "+quoteNo);
		if("41".equals(productId)) {
			spName = "HEALTH_COPYQUOTE_ENDT";
		} else if("65".equals(productId)) {
			spName = "MOTOR_COPYQUOTE_ENDT";
		} else if("30".equals(productId)) {
			spName = "HOME_COPYQUOTE_ENDT";
		} else {
			spName = "TRAVEL_COPYQUOTE_ENDT";
		}
		if("65".equals(productId)) {
			SimpleJdbcCall procedure= mySimpleJdbcCall.withProcedureName(spName);
	     	Map<String,Object> inputValues=new java.util.TreeMap<String, Object>(String.CASE_INSENSITIVE_ORDER);
	     	inputValues.put("pvType", type);
	     	inputValues.put("PvQuoteNo", quoteNo);
	     	inputValues.put("pvEndtTypeId", (","+endtTypeId+","));
	     	inputValues.put("pvLoginId", loginId);
	     	inputValues.put("pvBranch", branchCode);
	     	inputValues.put("pnProduct", productId);
	     	inputValues.put("pnvehicleid", vehicleId); 
	     	LogManager.info("Args==> " + inputValues.toString());
	     	outputValues = procedure.execute(new MapSqlParameterSource(inputValues));
		} else {	
			LogManager.info("Copy Quote SP Name=>"+spName);
			TravelCopyQuoteStroredProcedure sp=new TravelCopyQuoteStroredProcedure(this.mytemplate.getDataSource(), spName);
			Map<String, String> map=new HashMap<String, String>();
			map.put("STATUS", (type));
			map.put("QUOTE_NO1", quoteNo);
			map.put("END_TYPE", (","+endtTypeId+","));
			map.put("LOGIN_ID", loginId);
			map.put("BRANCH_CODE", branchCode);
			map.put("PRODUCT_ID", productId);
			map.put("CUSTOMER_NAME", "");
			map.put("ERROR_STATUS", "");
			map.put("QUOTE_NO", "");
			map.put("APPLICATION_NO", "");
			if(map.size()>0){
				//LogManager.info("Arguments => "+(StringUtils.isBlank(typeId)?"Normal":"Endt")+","+quoteNo+","+typeId+","+loginId+","+branchCode+","+productId+","+","+","+","+"");
				LogManager.info("Args==> " + map.toString());
			}
			LogManager.info("travelcopyQuote - Exit");
			outputValues = sp.executeProc(map);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return outputValues;
	}
	public List<Object> getTravelCopyQuoteSearchIssuer(String type, String value, String loginID, String productId) {
		List<Object> list=null;	
		try
		{
			if(type.equals("quoteNo"))
			{ 
				sql=getQuery("travel.copyquote.quoteNo.issuer");		
			}
			else if(type.equals("policyNo")){
				sql=getQuery("travel.copyquote.policyNo.issuer");
			}
			else
			{
				sql=getQuery("travel.copyquote.custName.issuer");
			}		
			list=this.mytemplate.queryForList(sql, new String[]{value,loginID,loginID,productId});		
			LogManager.info("args[] ==> "+type+","+value);
			LogManager.info("Qurey ==> "+sql);
		}
		catch(Exception e) {
			LogManager.debug("Exception @ "+e);
		}
		LogManager.popRemove();
		LogManager.info("getTravelOptionsList - Exit || Result: "+list.size());
		return list;		
	}
}
class CopyQuoteStroredProcedure extends StoredProcedure 
{
	public CopyQuoteStroredProcedure(DataSource ds, String spName)
	{
		super(ds,spName);
		declareParameter(new SqlParameter("TYPE",OracleTypes.VARCHAR));		
		declareParameter(new SqlParameter("LOGIN_ID",OracleTypes.VARCHAR));
		declareParameter(new SqlParameter("ISSUER_ID",OracleTypes.VARCHAR));		
		declareParameter(new SqlParameter("END_TYPE",OracleTypes.VARCHAR));
		declareParameter(new SqlInOutParameter("QUOTE_NO",OracleTypes.VARCHAR));
		declareParameter(new SqlOutParameter("CUSTOMER_NAME",OracleTypes.VARCHAR));
		declareParameter(new SqlOutParameter("ERROR_STATUS",OracleTypes.VARCHAR));		
		super.compile();
	}
	public Map<String, Object> executeProc(Map<String, String> inParams)
	{
		return super.execute(inParams);
	}
}
class TravelCopyQuoteStroredProcedure extends StoredProcedure 
{
	public TravelCopyQuoteStroredProcedure(DataSource ds, String spName)
	{
		super(ds,spName);
		declareParameter(new SqlParameter("STATUS",OracleTypes.VARCHAR));		
		declareParameter(new SqlParameter("QUOTE_NO1",OracleTypes.VARCHAR));
		declareParameter(new SqlParameter("END_TYPE",OracleTypes.VARCHAR));
		declareParameter(new SqlParameter("LOGIN_ID",OracleTypes.VARCHAR));
		declareParameter(new SqlParameter("BRANCH_CODE",OracleTypes.VARCHAR));			
		declareParameter(new SqlParameter("PRODUCT_ID",OracleTypes.VARCHAR));
		declareParameter(new SqlOutParameter("CUSTOMER_NAME",OracleTypes.VARCHAR));
		declareParameter(new SqlOutParameter("ERROR_STATUS",OracleTypes.VARCHAR));
		declareParameter(new SqlOutParameter("QUOTE_NO",OracleTypes.VARCHAR));
		declareParameter(new SqlOutParameter("APPLICATION_NO",OracleTypes.VARCHAR));
		super.compile();
	}
	public Map<String, Object> executeProc(Map<String, String> map)
	{
		return super.execute(map);
	}
}
