package com.maan.upload.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.maan.Health.controller.UploadBean;
import com.maan.common.LogManager;
import com.maan.common.MyJdbcTemplate;
import com.maan.common.exception.BaseException;
import com.maan.dao.ApiCaller.ApiForUpload;
import com.maan.upload.UploadAction;
import com.maan.webservice.PolicyGenerationAction;
import com.maan.webservice.service.QuoteGeneration;


public class UploadDAO extends MyJdbcTemplate 
{
	ApiForUpload uploadApi = new ApiForUpload();
	public String sql="";
	public List getDBColumns(String movementDetailId)
	{
		LogManager.info("getDBColumns - Enter || movementDetailId: "+movementDetailId);
		List list= new ArrayList();
		try {
			sql = "SELECT SOURCE_FIELD, DEST_FIELD, MANDATORY_STATUS, DATA_TYPE, FIELD_ID FROM XL_MASTER_TABLE_CONFIG  WHERE MOVEMENT_DETAIL_ID=? AND STATUS='Y'";
			list=this.mytemplate.queryForList(sql, new String[]{movementDetailId});
			
		} catch (Exception e) {
			
			LogManager.debug("EXCEPTION @ { "+e+" }");
		}
		LogManager.info("getDBColumns  sql: "+sql);
		LogManager.info("getDBColumns - Exit || Result: "+list.size());
		LogManager.popRemove();
		return list;
	}
	public List getDownloadData(String tranId, Map downloadInfo) 
	{
		LogManager.info("getDownloadData - Enter || tranId: "+tranId);
		String tableName=(String)downloadInfo.get("TABLE_NAME");
		String dbColumns=(String)downloadInfo.get("DB_COLUMNS");
		List list=null;
		try {
			if(dbColumns!=null)
			{
				sql="SELECT "+dbColumns+" FROM "+tableName+" WHERE TRAN_ID=? AND STATUS='Y'";
				LogManager.debug("sql==>"+sql);
				list=this.mytemplate.queryForList(sql, new String[]{tranId});
			}
		} catch (Exception e) {
			LogManager.debug("EXCEPTION @ { "+e+" }");
		}
		LogManager.info("getDownloadData - Exit");
		LogManager.popRemove();
		return list;
	}
	public Map insertRawData(String typeId, String tableName, String fields, List records, String movementDetailId, String tranIndex, String transId, String snoIndex, String headers,String finalHeaders) 
	{
		System.out.println("insertRawData() - Enter || recordsCount: "+records.size());
		String[] queries=new String[0];
		
		String sqlRaw = "";
		boolean error = false;
		String validationError = "";
		String errorMsg="", values="",tranId="0";
		Map result = new HashMap();
		//String tempTable = "TEMP_"+Calendar.DATE+Calendar.MONTH+Calendar.YEAR+Calendar.getInstance().getTimeInMillis();
		//String tempCreateTable = "CREATE TABLE  "+tempTable+"("+headers.replaceAll(",", " varchar2(500 byte),")+" varchar2(500 byte),TRANSACTION_ID varchar2(500 byte),SNO varchar2(500 byte))";
		//this.mytemplate.execute(tempCreateTable);
		String sequence = "select raw_sequence from xl_movement_detail where movement_detail_id=?";
		if(!fields.contains("TRANSACTION_ID")){
			fields=fields+", TRANSACTION_ID,SNO";
			if(StringUtils.isBlank(transId)){
			 tranId=(String)this.mytemplate.queryForObject("SELECT TRANSACTION_NO.NEXTVAL FROM DUAL",String.class);
			}else{
				// To addd records to the existing transaction
				tranId = transId;
			}
			String sequenceName = (String)this.mytemplate.queryForObject(sequence, new Object[]{movementDetailId}, String.class);
			values=",'"+tranId+"',"+sequenceName+".nextval";
		} 
		else{
			System.out.println("Error Reupload");
			error = true;
		}
		
		try 
		{
			int errorTransactionStartIndex=0;;
			int errorTransactionEndIndex=0;
			String errorTranID="";
			if(error){
			 errorTransactionStartIndex = nthOccurrence(records.get(0).toString(), '\'', ((Integer.parseInt(tranIndex)*2)-1));
			 errorTransactionEndIndex = nthOccurrence(records.get(0).toString(), '\'', ((Integer.parseInt(tranIndex)*2)));
			 errorTranID= records.get(0).toString().substring(errorTransactionStartIndex,errorTransactionEndIndex).replaceAll("'", "");
			}
			if(!records.isEmpty())
			{
				
				for(int i=0; i<records.size(); i++)
				{
					if(error){
						System.out.println("Error Tran ID"+errorTranID);
						String spli[] = errorTranID.split("\\.");
						tranId =spli[0]==null?errorTranID:spli[0];
						System.out.println(records.get(i).toString());
						int errorSnoStartIndex = nthOccurrence(records.get(i).toString(), '\'', ((Integer.parseInt(snoIndex)*2)-1));
						int errorSnoEndIndex = nthOccurrence(records.get(i).toString(), '\'', ((Integer.parseInt(snoIndex)*2)));
						String errorSNO= records.get(i).toString().substring(errorSnoStartIndex,errorSnoEndIndex).replaceAll("'", "");
						System.out.println("errorSNO"+errorSNO);
						sql= "select count(*) from XL_OPENCOVER_RAW WHERE TRANSACTION_ID=? and sno=?";
						String count = (String)this.mytemplate.queryForObject(sql, new Object[]{errorTranID,errorSNO},String.class);
						if(count.equalsIgnoreCase("0"))
							validationError =validationError+"Reference No "+errorSNO + " Not Exists<br/>";
						sql= "select count(*) from webservice_marine_quote WHERE TRANSACTION_ID=? and reference_number=?";
						 count = (String)this.mytemplate.queryForObject(sql, new Object[]{errorTranID,errorSNO},String.class);
						if(!count.equalsIgnoreCase("0"))
							validationError ="Quote already generated for Reference No "+errorSNO + "<br/>";
						
					}
					
				}
				
			}	
			System.out.println("validationError"+validationError);
			if(!records.isEmpty() && validationError.equalsIgnoreCase(""))
			{
				System.out.println("Error Exists::"+error);
				queries=new String[records.size()];
				
				for(int i=0; i<records.size(); i++)
				{
					if(error){
						int errorSnoStartIndex = nthOccurrence(records.get(i).toString(), '\'', ((Integer.parseInt(snoIndex)*2)-1));
						int errorSnoEndIndex = nthOccurrence(records.get(i).toString(), '\'', ((Integer.parseInt(snoIndex)*2)));
						String errorSNO= records.get(i).toString().substring(errorSnoStartIndex,errorSnoEndIndex).replaceAll("'", "");
						sql= "delete from "+tableName+" WHERE TRANSACTION_ID=? and sno=?";
						System.out.println("delete query ::"+sql+":error::"+errorTranID+"sn::"+errorSNO);
						this.mytemplate.update(sql,new Object[]{errorTranID,errorSNO});
					}
					
					sql="INSERT INTO "+tableName+"("+fields+") VALUES('"+records.get(i)+values+" )";
					System.out.println("Queries"+i+"::"+sql);
					queries[i]=sql;
				//	sql="INSERT INTO "+tableName+"("+fields+") select "+finalHeaders+" TRANSACTION_ID,SNO from "+tempTable+" ";
					//sqlRaw=sql;
					LogManager.info("Sql test1==>"+sql);
				}	
				try {
					if(queries.length>0){
						this.mytemplate.batchUpdate(queries);
					
						//this.mytemplate.update(sqlRaw);
					}	
						//this.mytemplate.execute("DROP TABLE "+tempTable);
					
				} catch (Exception e) {
				
					e.printStackTrace();
				}
				
			}
		}catch (Exception e) 
		{
			LogManager.info("Error Sql==>"+sql);
			LogManager.debug("EXCEPTION @ { "+e+" }");
			e.printStackTrace();
			String excepMsg = e.getMessage();
			if(excepMsg.contains("ORA-")) 
				errorMsg = excepMsg.substring(excepMsg.indexOf("ORA-"),excepMsg.length());
			else
				errorMsg= "Uploaded file contains invalid file format";
		} 
		System.out.println("insertRawData() - Exit || Error: "+errorMsg);
		System.out.println("Validation Error:"+validationError);
		System.out.println("tranId:"+tranId);
		result.put("tranid", tranId);
		result.put("error", validationError);
		return result;
	}
	public String insertMasterData(String typeId, String tableName, String fields, List records,  String tranId) 
	{
		System.out.println("insertMasterData() - Enter || recordsCount: "+records.size());
		String[] queries=new String[0];
		String errorMsg="", values="";
		fields=fields+", TRANSACTION_ID";
		values=tranId+"'";
		
		try 
		{
			if(!records.isEmpty())
			{
				queries=new String[records.size()];
				for(int i=0; i<records.size(); i++)
				{
					sql="INSERT INTO "+tableName+"("+fields+") VALUES('"+records.get(i)+values+" )";
					queries[i]=sql;
					LogManager.info("Sql test1==>"+sql);
				}	
				try {
					if(queries.length>0)
						this.mytemplate.batchUpdate(queries);
				} catch (Exception e) {
				
					e.printStackTrace();
				}
				    
			}	
		}catch (Exception e) 
		{
			LogManager.info("Error Sql==>"+sql);
			LogManager.debug("EXCEPTION @ { "+e+" }");
			
			
		}
		System.out.println("insertMasterData() - Exit || Error: "+errorMsg);
		return errorMsg;
	}
	public void archiveRawData(String movementDetailId, String tranId, String user) 
	{
		System.out.println("archiveRawData() - Enter: "+tranId);
		
		try 
		{
			String qry = "SELECT ARCHIVE_TABLE,DESTINATION_TABLE FROM XL_MOVEMENT_DETAIL WHERE ARCHIVE_TABLE IS NOT NULL AND MOVEMENT_DETAIL_ID=?";
			SqlRowSet rset = this.mytemplate.queryForRowSet(qry, new Object[] { movementDetailId });
			if(rset.next()){
				String archiveTable = rset.getString(1);
				String rawTable = rset.getString(2);
				String sql="insert into "+archiveTable+" (select * from "+rawTable+" where transaction_id=?) ";
				this.mytemplate.update(sql,new Object[]{tranId});
				String updateQry = "update "+archiveTable+" set transaction_date=sysdate , user_id=? where transaction_date is null and transaction_id=?";
				this.mytemplate.update(updateQry,new Object[]{user,tranId});
			}
			
		}catch (Exception e) 
		{
			LogManager.info("Error Sql==>"+sql);
			LogManager.debug("EXCEPTION @ { "+e+" }");
		}
		System.out.println("archiveRawData() - Exit ");
		
	}
	public void updateOpenCoverDetail(String brokerCode, String openCoverNo,
			String tranId) {
		LogManager.info("updateOpenCoverDetail Enter" + tranId);
		
		try { 
			String qry = "update WEBSERVICE_MARINE_QUOTE_RAW set broker_code ='"+brokerCode+"' , MOC_NUMBER='"+openCoverNo+"' where transaction_id='"+tranId+"'";
			this.mytemplate.update(qry);
			System.out.println(qry);
			String brokerValid = "UPDATE WEBSERVICE_MARINE_QUOTE_RAW SET VALIDATION_ERROR=VALIDATION_ERROR||',BROKER CODE INVALID' WHERE BROKER_CODE NOT IN ( SELECT AGENCY_CODE FROM BROKER_COMPANY_MASTER WHERE STATUS='Y' ) AND TRANSACTION_ID=?";
			this.mytemplate.update(brokerValid,new Object[]{tranId});
			System.out.println(brokerValid);
			//String openCoverValid =  "UPDATE WEBSERVICE_MARINE_QUOTE_RAW SET VALIDATION_ERROR=VALIDATION_ERROR||',OPEN COVER NO INVALID' WHERE MOC_NUMBER NOT IN ( SELECT MISSIPPI_OPENCOVER_NO FROM OPEN_COVER_MASTER WHERE STATUS='Y' and broker_id in (select company_name from BROKER_COMPANY_MASTER bc where broker_code=bc.agency_code and status='Y')) AND TRANSACTION_ID=?";
			//this.mytemplate.update(openCoverValid,new Object[]{tranId});
			//System.out.println(openCoverValid);
			String updateProposal = "UPDATE WEBSERVICE_MARINE_QUOTE_RAW SET PROPOSAL_NO=(SELECT PROPOSAL_NO FROM OPEN_COVER_MASTER OCM WHERE MOC_NUMBER =OCM.MISSIPPI_OPENCOVER_NO AND STATUS='Y' AND AMEND_ID=(SELECT MAX(AMEND_ID) FROM OPEN_COVER_MASTER WHERE MISSIPPI_OPENCOVER_NO =OCM.MISSIPPI_OPENCOVER_NO )) WHERE  TRANSACTION_ID=?";
			System.out.println("Update "+updateProposal);
			this.mytemplate.update(updateProposal,new Object[]{tranId});
				
				//Customer Info Updation 
				String customerUpdate = "UPDATE WEBSERVICE_MARINE_QUOTE_RAW WR SET " +
				" WR.OPENCOVER_POLICY_NUMBER=WR.MOC_NUMBER," +
				/*" WR.WSRCC=(SELECT (case when OCM.WRSC_YN ='Y' then 'YES' else 'NO' end) FROM OPEN_COVER_MASTER OCM"+
				"            WHERE OCM.AMEND_ID=(SELECT MAX(AMEND_ID) FROM OPEN_COVER_MASTER WHERE MISSIPPI_OPENCOVER_NO=OCM.MISSIPPI_OPENCOVER_NO)"+
				"            AND OCM.MISSIPPI_OPENCOVER_NO=WR.MOC_NUMBER),"+*/
				" WR.CUSTOMER_ID=(SELECT PI.CUSTOMER_ID FROM PERSONAL_INFO PI,OPEN_COVER_MASTER OCM"+
				"            WHERE OCM.CUSTOMER_ID=PI.CUSTOMER_ID"+
				"            AND OCM.AMEND_ID=(SELECT MAX(AMEND_ID) FROM OPEN_COVER_MASTER WHERE MISSIPPI_OPENCOVER_NO=OCM.MISSIPPI_OPENCOVER_NO)"+
				"            AND PI.AMEND_ID=(SELECT MAX(AMEND_ID) FROM PERSONAL_INFO WHERE CUSTOMER_ID=PI.CUSTOMER_ID)"+
				"            AND OCM.MISSIPPI_OPENCOVER_NO=WR.MOC_NUMBER),"+
				" WR.CUSTOMER_NAME=(SELECT PI.FIRST_NAME FROM PERSONAL_INFO PI,OPEN_COVER_MASTER OCM"+
				"            WHERE OCM.CUSTOMER_ID=PI.CUSTOMER_ID"+
				"            AND OCM.AMEND_ID=(SELECT MAX(AMEND_ID) FROM OPEN_COVER_MASTER WHERE MISSIPPI_OPENCOVER_NO=OCM.MISSIPPI_OPENCOVER_NO)"+
				"            AND PI.AMEND_ID=(SELECT MAX(AMEND_ID) FROM PERSONAL_INFO WHERE CUSTOMER_ID=PI.CUSTOMER_ID)"+
				"            AND OCM.MISSIPPI_OPENCOVER_NO=WR.MOC_NUMBER),"+
				" WR.ADDRESS1=(SELECT PI.ADDRESS1 FROM PERSONAL_INFO PI,OPEN_COVER_MASTER OCM"+
				"            WHERE OCM.CUSTOMER_ID=PI.CUSTOMER_ID"+
				"            AND OCM.AMEND_ID=(SELECT MAX(AMEND_ID) FROM OPEN_COVER_MASTER WHERE MISSIPPI_OPENCOVER_NO=OCM.MISSIPPI_OPENCOVER_NO)"+
				"            AND PI.AMEND_ID=(SELECT MAX(AMEND_ID) FROM PERSONAL_INFO WHERE CUSTOMER_ID=PI.CUSTOMER_ID)"+
				"            AND OCM.MISSIPPI_OPENCOVER_NO=WR.MOC_NUMBER),"+
				" WR.ADDRESS2=(SELECT PI.ADDRESS2 FROM PERSONAL_INFO PI,OPEN_COVER_MASTER OCM"+
				"            WHERE OCM.CUSTOMER_ID=PI.CUSTOMER_ID"+
				"            AND OCM.AMEND_ID=(SELECT MAX(AMEND_ID) FROM OPEN_COVER_MASTER WHERE MISSIPPI_OPENCOVER_NO=OCM.MISSIPPI_OPENCOVER_NO)"+
				"            AND PI.AMEND_ID=(SELECT MAX(AMEND_ID) FROM PERSONAL_INFO WHERE CUSTOMER_ID=PI.CUSTOMER_ID)"+
				"            AND OCM.MISSIPPI_OPENCOVER_NO=WR.MOC_NUMBER),"+
				" WR.PO_BOX=(SELECT PI.POBOX FROM PERSONAL_INFO PI,OPEN_COVER_MASTER OCM"+
				"            WHERE OCM.CUSTOMER_ID=PI.CUSTOMER_ID"+
				"            AND OCM.AMEND_ID=(SELECT MAX(AMEND_ID) FROM OPEN_COVER_MASTER WHERE MISSIPPI_OPENCOVER_NO=OCM.MISSIPPI_OPENCOVER_NO)"+
				"            AND PI.AMEND_ID=(SELECT MAX(AMEND_ID) FROM PERSONAL_INFO WHERE CUSTOMER_ID=PI.CUSTOMER_ID)"+
				"            AND OCM.MISSIPPI_OPENCOVER_NO=WR.MOC_NUMBER),"+
				" WR.COUNTRY=(SELECT PI.COUNTRY FROM PERSONAL_INFO PI,OPEN_COVER_MASTER OCM"+
				"            WHERE OCM.CUSTOMER_ID=PI.CUSTOMER_ID"+
				"            AND OCM.AMEND_ID=(SELECT MAX(AMEND_ID) FROM OPEN_COVER_MASTER WHERE MISSIPPI_OPENCOVER_NO=OCM.MISSIPPI_OPENCOVER_NO)"+
				"            AND PI.AMEND_ID=(SELECT MAX(AMEND_ID) FROM PERSONAL_INFO WHERE CUSTOMER_ID=PI.CUSTOMER_ID)"+
				"            AND OCM.MISSIPPI_OPENCOVER_NO=WR.MOC_NUMBER),"+
				" WR.EMIRATE=(SELECT PI.EMIRATE FROM PERSONAL_INFO PI,OPEN_COVER_MASTER OCM"+
				"            WHERE OCM.CUSTOMER_ID=PI.CUSTOMER_ID"+
				"            AND OCM.AMEND_ID=(SELECT MAX(AMEND_ID) FROM OPEN_COVER_MASTER WHERE MISSIPPI_OPENCOVER_NO=OCM.MISSIPPI_OPENCOVER_NO)"+
				"            AND PI.AMEND_ID=(SELECT MAX(AMEND_ID) FROM PERSONAL_INFO WHERE CUSTOMER_ID=PI.CUSTOMER_ID)"+
				"            AND OCM.MISSIPPI_OPENCOVER_NO=WR.MOC_NUMBER),"+
				" WR.MOBILE_NUMBER=(SELECT PI.MOBILE FROM PERSONAL_INFO PI,OPEN_COVER_MASTER OCM"+
				"            WHERE OCM.CUSTOMER_ID=PI.CUSTOMER_ID"+
				"            AND OCM.AMEND_ID=(SELECT MAX(AMEND_ID) FROM OPEN_COVER_MASTER WHERE MISSIPPI_OPENCOVER_NO=OCM.MISSIPPI_OPENCOVER_NO)"+
				"            AND PI.AMEND_ID=(SELECT MAX(AMEND_ID) FROM PERSONAL_INFO WHERE CUSTOMER_ID=PI.CUSTOMER_ID)"+
				"            AND OCM.MISSIPPI_OPENCOVER_NO=WR.MOC_NUMBER),"+
				" WR.CUST_TITLE=(SELECT PI.TITLE FROM PERSONAL_INFO PI,OPEN_COVER_MASTER OCM"+
				"            WHERE OCM.CUSTOMER_ID=PI.CUSTOMER_ID"+
				"            AND OCM.AMEND_ID=(SELECT MAX(AMEND_ID) FROM OPEN_COVER_MASTER WHERE MISSIPPI_OPENCOVER_NO=OCM.MISSIPPI_OPENCOVER_NO)"+
				"            AND PI.AMEND_ID=(SELECT MAX(AMEND_ID) FROM PERSONAL_INFO WHERE CUSTOMER_ID=PI.CUSTOMER_ID)"+
				"            AND OCM.MISSIPPI_OPENCOVER_NO=WR.MOC_NUMBER),"+
				" WR.EMAIL=(SELECT PI.EMAIL FROM PERSONAL_INFO PI,OPEN_COVER_MASTER OCM"+
				"            WHERE OCM.CUSTOMER_ID=PI.CUSTOMER_ID"+
				"            AND OCM.AMEND_ID=(SELECT MAX(AMEND_ID) FROM OPEN_COVER_MASTER WHERE MISSIPPI_OPENCOVER_NO=OCM.MISSIPPI_OPENCOVER_NO)"+
				"            AND PI.AMEND_ID=(SELECT MAX(AMEND_ID) FROM PERSONAL_INFO WHERE CUSTOMER_ID=PI.CUSTOMER_ID)"+
				"            AND OCM.MISSIPPI_OPENCOVER_NO=WR.MOC_NUMBER)"+
				" WHERE WR.TRANSACTION_ID=? AND VALIDATION_ERROR IS NULL";
				System.out.println("customerUpdate"+customerUpdate);
				this.mytemplate.update(customerUpdate,new Object[]{tranId});
		
			
		} catch (Exception e) {
		
			e.printStackTrace();
			LogManager.info("updateOpenCoverDetail Error"+e);
		}
		
		LogManager.info("updateOpenCoverDetail Exit");
		
	}
	public void generateQuotations(String tranId) {
		System.out.println("generateQuotations Enters");
		SqlRowSet rs = this.mytemplate.queryForRowSet("select reference_number from webservice_marine_quote where transaction_id=?",new Object[]{tranId});
		while (rs.next()){
		QuoteGeneration quoteGeneration = new QuoteGeneration();
		PolicyGenerationAction generate = new PolicyGenerationAction(rs.getString(1));
		String remarks= generate.quoteGeneration();
		
		String response = quoteGeneration.generateResponse(rs.getString(1),remarks);
		System.out.println("Response ::"+rs.getString(1)+"::"+response);
		}
		System.out.println("generateQuotations Exits");
	}
	public void getResult(String tranId, String movementDetailId) {
			
		String destTable = (String)this.mytemplate.queryForObject("select destination_table from xl_movement_detail where movement_detail_id=?",new Object[]{movementDetailId},String.class);
		
		try {
			String count =(String)this.mytemplate.queryForObject("select (select count(*) from "+destTable+" where transaction_id=?)+(select count(*) from webservice_marine_quote where transaction_id=?) from dual", new Object[]{tranId,tranId},String.class);
			System.out.println("Total No Of Records::"+ count);
			this.mytemplate.update("update xl_transaction_details set total_no_of_records=? where transaction_id=?",new Object[]{count,tranId});
						 
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	
        System.out.println("getResult() - Exit");
		
	}
	
	public List getResult2(String tranId, String movementDetailId) {
		String destTable = (String)this.mytemplate.queryForObject("select destination_table from xl_movement_detail where movement_detail_id=?",new Object[]{movementDetailId},String.class);
				
		try {
			String updateTransaction =  "update XL_TRANSACTION_DETAILS set UPLOADED_COUNT=(select (select q.TOTAL_NO_OF_RECORDS from XL_TRANSACTION_DETAILS q where transaction_id=?)-(select count(*) from "+destTable+" where transaction_id=?) from dual),PENDING_COUNT=(select count(*) from "+destTable+" where transaction_id=?) where transaction_id=?";
			this.mytemplate.update(updateTransaction, new Object[]{tranId,tranId,tranId,tranId});
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		List uploadResult=this.mytemplate.queryForList("SELECT TOTAL_NO_OF_RECORDS, UPLOADED_COUNT, PENDING_COUNT FROM XL_TRANSACTION_DETAILS WHERE TRANSACTION_ID=?", new String[]{tranId});
		System.out.println("getResult2() - Exit");
		return uploadResult;
	}
	public List getErrorList(String sno, String tranId) {
		System.out.println("Enter getErrorList");
		List list = null;
		try {
			if(StringUtils.isBlank(sno)){
				list = this.mytemplate.queryForList("select SR_NO, REF_NO, ORDER_NO, INTEREST,COMMODITY_DESCRIPTION, LC_NO, CONVEYANCE, COVER, VESSEL_NAME, SAILING_DATE, PACKING_DETAILS, VOYAGE_FROM, VOYAGE_TO, INVOICE_VALUE, CURRENCY, INCOTERMS, ROE, BASIS_OF_VALUATION, PACKAGE_DESCRIPTION, SETTLING_AGENT, SNO, TRANSACTION_ID, VALIDATION_ERROR, OPENCOVER_POLICY_NUMBER, WSRCC " +
					" from xl_opencover_raw where transaction_id=? and validation_error is not null",new Object[]{tranId});
			}else{
				list = this.mytemplate.queryForList("select SR_NO, REF_NO, ORDER_NO, INTEREST,COMMODITY_DESCRIPTION, LC_NO, CONVEYANCE, COVER, VESSEL_NAME, SAILING_DATE, PACKING_DETAILS, VOYAGE_FROM, VOYAGE_TO, INVOICE_VALUE, CURRENCY, INCOTERMS, ROE, BASIS_OF_VALUATION, PACKAGE_DESCRIPTION, SETTLING_AGENT, SNO, TRANSACTION_ID, VALIDATION_ERROR, OPENCOVER_POLICY_NUMBER, WSRCC " +
						" from xl_opencover_raw where transaction_id=? and SNO=? and validation_error is not null",new Object[]{tranId, sno});
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Enter getErrorList");
		return list;
	}
	public List getErrorListBk(String tranId) {
		
		System.out.println("Enter getErrorList");
		List list = null;
		try {
			list = this.mytemplate.queryForList("select * from xl_opencover_raw where transaction_id=? and validation_error is not null",new Object[]{tranId});
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		System.out.println("Enter getErrorList");
		return list;
	}
	public List getUploadedList(String tranId) {
		
		System.out.println("Enter getUploadedList");
		List list = null;
		list = this.mytemplate.queryForList("SELECT (case when mq.status='Q' then to_char(MQ.PREMIUM) else '' end) PREMIUM_TOTAL,MRD.SUM_INSURED,MD.EXCHANGE_RATE,MRD.SUMINSUREDLOCAL,MD.PREMIUM,MRD.PREMIUM,MD.TOTAL_WAR_CHARGES,MD.POLICY_FEE,MRD.RATE,MRD.WARRATE,X.SR_NO, X.REF_NO, X.ORDER_NO,    X.INTEREST, X.LC_NO, X.CONVEYANCE,    X.COVER, X.VESSEL_NAME, X.SAILING_DATE,    X.PACKING_DETAILS, X.VOYAGE_FROM, X.VOYAGE_TO,    X.INVOICE_VALUE, X.CURRENCY, X.INCOTERMS,    X.ROE, X.BASIS_OF_VALUATION, X.WSRCC,    X.PACKAGE_DESCRIPTION, X.SETTLING_AGENT, X.SUM_INSURED_DHS,    MQ.MARINE_PREMIUM, MQ.WAR_PREMIUM, X.TRANSACTION_ID,    X.SNO, X.VALIDATION_ERROR, X.VALIDATION_STATUS,    X.MOC_NUMBER, X.BROKER_CODE, X.OPENCOVER_POLICY_NUMBER ,MQ.QUOTE_NO,X.COMMODITY_DESCRIPTION,MQ.REMARKS ,MQ.ISSUANCE_FEE," +
				"(select sum(SUMINSUREDLOCAL)+sum(SALE_TERM_CHARGES)+sum(TOLERANCE_CHARGES) from MARINE_RESULT_DETAILS where application_no = mrd.application_no and AMEND_ID=(select max(amend_id) from MARINE_RESULT_DETAILS where application_no = mrd.application_no)) SUM_LOCAL, mrd.exchange_rate" +
				" FROM XL_OPENCOVER_ARC X,WEBSERVICE_MARINE_QUOTE MQ,POSITION_MASTER PM,MARINE_DATA MD,MARINE_RESULT_DETAILS MRD WHERE ( x.transaction_date=(select max(transaction_date) from xl_opencover_arc where SNO=MQ.REFERENCE_NUMBER AND transaction_id=?) )and X.TRANSACTION_ID = ? AND X.SNO=MQ.REFERENCE_NUMBER AND X.TRANSACTION_ID=MQ.TRANSACTION_ID AND MQ.QUOTE_NO=PM.QUOTE_NO AND PM.APPLICATION_NO=MD.APPLICATION_NO AND PM.APPLICATION_NO=MRD.APPLICATION_NO ORDER BY MQ.QUOTE_NO ASC",new Object[]{tranId,tranId});
		System.out.println("Exit getUploadedList");
		return list;
	}
	public String getBrokerId(String loginId) {
		System.out.println("Enter getBrokerId");
		String brokerId="";
		try {
			brokerId = (String)this.mytemplate.queryForObject("SELECT OA_CODE FROM LOGIN_MASTER WHERE LOGIN_ID=?",new Object[]{loginId},String.class);
			System.out.println("Exit getBrokerId");
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return brokerId;
	}
	public String getcustomerId(String openCoverNo) {
		System.out.println("Enter getcustomerId");
		String customerId="";
		try {
			customerId = (String)this.mytemplate.queryForObject("SELECT FIRST_NAME || NVL(LAST_NAME,'') FROM PERSONAL_INFO WHERE CUSTOMER_ID IN( SELECT CUSTOMER_ID FROM OPEN_COVER_MASTER WHERE  MISSIPPI_OPENCOVER_NO=? AND AMEND_ID=(SELECT MAX(AMEND_ID) FROM OPEN_COVER_MASTER WHERE MISSIPPI_OPENCOVER_NO=?))",new Object[]{openCoverNo,openCoverNo},String.class);
			System.out.println("Exit getcustomerId");
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return customerId;
	} 
	public List getTransactions(String openCoverNo, String issuerId) {
		System.out.println("Enter getTransactions");
		List list = null;
		list = this.mytemplate.queryForList("SELECT distinct TR.TRANSACTION_ID, TR.MOVEMENT_DETAIL_ID, TR.TRANSACTION_DATE,TO_CHAR(TR.TRANSACTION_DATE,'DD/MM/YYYY') TRN_DATE, TO_CHAR(TR.TRANSACTION_DATE,'HH24:MI:SS')  TRN_TIME,TR.USER_NAME, TR.FILE_NAME, TR.PATH, TR.TOTAL_NO_OF_RECORDS, TR.UPLOADED_COUNT, TR.PENDING_COUNT, TR.UPLOAD_STATUS, TR.REMARKS, TR.ACTIVE, TR.OPENCOVER_POLICY_NUMBER,TR.TRANSACTION_DESCRIPTION FROM XL_TRANSACTION_DETAILS TR where TR.OPENCOVER_POLICY_NUMBER=?   and TR.transaction_id is not null and tr.issuer is "+((issuerId.trim().toString().equals(""))?"":" not ")+" null "+((issuerId.trim().toString().equals(""))?"":" and  trim(issuer)='"+issuerId.trim()+"' ")+" order by TR.transaction_id desc",new Object[]{openCoverNo});
		System.out.println("SELECT distinct TR.TRANSACTION_ID, TR.MOVEMENT_DETAIL_ID, TR.TRANSACTION_DATE,TO_CHAR(TR.TRANSACTION_DATE,'DD/MM/YYYY') TRN_DATE, TO_CHAR(TR.TRANSACTION_DATE,'HH24:MI:SS')  TRN_TIME,TR.USER_NAME, TR.FILE_NAME, TR.PATH, TR.TOTAL_NO_OF_RECORDS, TR.UPLOADED_COUNT, TR.PENDING_COUNT, TR.UPLOAD_STATUS, TR.REMARKS, TR.ACTIVE, TR.OPENCOVER_POLICY_NUMBER,TR.TRANSACTION_DESCRIPTION FROM XL_TRANSACTION_DETAILS TR where TR.OPENCOVER_POLICY_NUMBER=?   and TR.transaction_id is not null and tr.issuer is "+((issuerId.trim().toString().equals(""))?"":" not ")+" null "+((issuerId.trim().toString().equals(""))?"":" and  trim(issuer)='"+issuerId.trim()+"' ")+" order by TR.transaction_id desc");
		//System.out.println("SELECT TRANSACTION_ID, MOVEMENT_DETAIL_ID, TRANSACTION_DATE,TO_CHAR(TRANSACTION_DATE,'DD/MM/YYYY') TRN_DATE, TO_CHAR(TRANSACTION_DATE,'HH24:MI:SS')  TRN_TIME,USER_NAME, FILE_NAME, PATH, TOTAL_NO_OF_RECORDS, UPLOADED_COUNT, PENDING_COUNT, UPLOAD_STATUS, REMARKS, ACTIVE, OPENCOVER_POLICY_NUMBER FROM XL_TRANSACTION_DETAILS where OPENCOVER_POLICY_NUMBER=? order by transaction_id desc");
		System.out.println("OpencoverNo::"+openCoverNo);
		System.out.println("Exit getTransactions");
		return list;
	}
	public  int nthOccurrence(String str, char c, int n) { 
		int pos = str.indexOf(c, 0);   
		while (n-- > 0 && pos != -1)   
			pos = str.indexOf(c, pos+1); 
		return pos; 
		} 
		
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

	

	public String insertTransactionDetails(String loginId, String fileName, String folderPath, String movementDetailId, String tranId2) 
	{
		String resultTranId = "";
		int count = this.mytemplate.queryForInt("select count(*) from XL_TRANSACTION_DETAILS where transaction_id ='"+tranId2+"'");
		if(count==0){
		 //int tranId=this.mytemplate.queryForInt("SELECT TRANSACTION_NO.NEXTVAL FROM DUAL");
		
		String args[]=new String[5];
		args[0]=tranId2+"";
		args[1]=loginId;
		args[2]=fileName;
		args[3]=folderPath;
		args[4]=movementDetailId;
		sql="INSERT INTO XL_TRANSACTION_DETAILS (TRANSACTION_ID,TRANSACTION_DATE,USER_NAME,FILE_NAME,PATH,ACTIVE,MOVEMENT_DETAIL_ID) " +
				" VALUES(?,SYSDATE,?,?,?,'Y',?)";
		int result=this.mytemplate.update(sql,args);
		System.out.println("insertTransactionDetails() - Exit || Result: "+result+" Tran Id: "+tranId2);
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
	public String getTransDesc(String transId) {
		
		String transDesc = (String) this.mytemplate.queryForObject("select transaction_description from xl_transaction_details where transaction_id=?",new Object[]{transId},String.class);
		return transDesc;
	}
	public void updateIssuer(String issuer , String tranId) {
		
		this.mytemplate.update("update webservice_marine_quote set issuer=? where transaction_id=?",new Object[]{issuer,tranId});
	}

	public List<Object> getCommodityList(String openCover) {
		List ocList=null;
		try{
			String sql=getQuery("GET_COMMODITY_LIST_OC");
			LogManager.info("Query====>"+sql);
			ocList=this.mytemplate.queryForList(sql,new Object[]{openCover});
		}catch(Exception e){
			e.printStackTrace();
		}
		return ocList;
	}
	
	public void reuploadSave(final UploadAction up, String bcode) {
		LogManager.info("ENETR==>reuploadSave");
		try{
			for(int i=1; i<up.getUsno().size();i++){
				Object args[]=new Object[]{i, up.getUinterest().get(i), up.getUconveyance().get(i),bcode,up.getUcover().get(i), up.getUcover().get(i),up.getUvoyageFrom().get(i),
						up.getUvoyageTo().get(i),up.getUcurrency().get(i), bcode, up.getUincoTerms().get(i),up.getUsaleTermPercent().get(i), bcode,up.getUtolerance().get(i), bcode,
						up.getUpkgDesc().get(i),up.getUcommodityDesc().get(i), up.getUwar().get(i),"", up.getTranId(), up.getUsno().get(i)};
				for(Object k:args)
					LogManager.info("args===>"+k.toString());
				String sql=getQuery("UPD_XL_OPENCOVER_RAW_UPLOAD");
				this.mytemplate.update(sql,args);
				sql=getQuery("UPD_XL_OPENCOVER_ARC_UPLOAD");
				this.mytemplate.update(sql,args);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		LogManager.info("EXIT==>reuploadSave");
	}
	
	public String getWarYNOC(String openCoverNo) {
		LogManager.info("ENETR==>getWarYNOC");
		String warYN="";
		try{
			LogManager.info("args===>"+openCoverNo);
			String sql=getQuery("GET_WARYN_OPENCOVER");
			warYN=this.mytemplate.queryForObject(sql,new Object[]{openCoverNo}, String.class).toString();
		}catch(Exception e){
			e.printStackTrace();
		}
		LogManager.info("EXIT==>getWarYNOC");
		return warYN;
	}
	public void insertDocumentDetails(UploadBean bean,String pid) {
		try {
			uploadApi.insertDocumentDetails(bean,pid);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		
//		try {
//			String query = "";
//			Object[] args = null;
//
//			
//			query = getQuery("INS_DOCUMENTS");
//			args = new Object[8];
//			args[0] = quoteNo;
//			args[1] = documentId;
//			args[2] = documentPath;
//			args[3] = docDesc;
//			args[4] = "Y";
//			args[5] = fileName;
//			args[6]=productId;
//			if("65".equalsIgnoreCase(productId)){
//				args[7]=StringUtils.isBlank(vehicleId)?"0":vehicleId;
//			}else if("30".equalsIgnoreCase(productId)){
//				args[7]="";
//			}
//			LogManager.info("Query==>" + query);
//			LogManager.info("Args==>" + StringUtils.join(args,", "));
//			this.mytemplate.update(query,args);
//		} catch(Exception exception) {
//			LogManager.debug(exception);
//		}
//	}
	
	public List<Map<String, Object>> getUploadDocList(String productId, String quoteNo, String vehicleId,String userType) {
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		
		try {
			list = uploadApi.getUploadDocList(productId,quoteNo,vehicleId,userType);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
//		List<Map<String,Object>> resultList = new ArrayList<Map<String,Object>>();
//		try {
//			String query = "";
//			Object[] args=null;
//			/*if("surveyor".equalsIgnoreCase(userType)){
//				if("30".equalsIgnoreCase(productId)){
//					args=new Object[]{quoteNo,productId};
//					query = getQuery("GET_UPLOAD_DOCUMENTS_SUR_LIST_HOME");
//				}else if("65".equalsIgnoreCase(productId)){
//					args=new Object[]{quoteNo,productId};
//					query = getQuery("GET_UPLOAD_DOCUMENTS_SUR_LIST_MOTOR");
//				}
//			}else{*/
//				if("30".equalsIgnoreCase(productId)){
//					args=new Object[]{quoteNo,productId};
//					query = getQuery("GET_UPLOAD_DOCUMENTS_LIST_HOME", new Object[]{"surveyor".equalsIgnoreCase(userType)?" AND DM.USER_TYPE ='surveyor' ":" AND DM.USER_TYPE !='surveyor' "});
//				}else if("65".equalsIgnoreCase(productId)){
//					args=new Object[]{quoteNo,productId,vehicleId};
//					query = getQuery("GET_UPLOAD_DOCUMENTS_LIST_MOTOR", new Object[]{"surveyor".equalsIgnoreCase(userType)?" AND DM.USER_TYPE ='surveyor' ":"AND DM.USER_TYPE !='surveyor' "});
//				}
//			/*}*/
//			LogManager.info("Query==>" + query);
//			LogManager.info("Args==>" + StringUtils.join(args,", "));
//			resultList=this.mytemplate.queryForList(query, args);
//		} catch(Exception exception) {
//			LogManager.debug(exception);
//		}
//
//		return resultList;
//	}
	public List<Map<String,Object>> getDocumentList(String productId,String userType) {
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		
		try {
			list = uploadApi.getDocumentList(productId,userType);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
		
//		List<Map<String,Object>> resultList = new ArrayList<Map<String,Object>>();
//		try {
//			String query = getQuery("GET_DOCUMENTSPRODUCT_LIST",new Object[]{"surveyor".equalsIgnoreCase(userType)?"AND USER_TYPE='"+userType+"'":" AND USER_TYPE !='surveyor'"});
//			Object[] args = new Object[]{productId};
//			LogManager.info("Query==>" + query);
//			LogManager.info("Args==>" + StringUtils.join(args,", "));
//			resultList = this.mytemplate.queryForList(query, args);
//		} catch(Exception exception) {
//			LogManager.debug(exception);
//		}
//		return resultList;
//	}
	public void deleteDocument(UploadBean bean, String productId) {
		try {
			uploadApi.deleteDocument(bean,productId);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
//		Object[] args={bean};
//		try {
//			String query =getQuery("DEL_DOCUMENTS");
//			this.mytemplate.update(query, args);
//		}catch(Exception exception) {
//			LogManager.debug(exception);
//		}		
//	}
	public List<Map<String, Object>> getCustAttachedDocs(String productId,String quoteNo, String reqFrom) {
		List<Map<String,Object>> resultList = new ArrayList<Map<String,Object>>();
		try {
			if("30".equalsIgnoreCase(productId)){
				Object[] args={quoteNo,productId};
				String query = getQuery("GET_ATTACHED_DOCUMENTS_LIST_HOME");
				LogManager.info("Query==>" + query);
				LogManager.info("Args==>" + StringUtils.join(args,", "));
				resultList=this.mytemplate.queryForList(query, args);
			}else if("65".equalsIgnoreCase(productId)){
				Object[] args={quoteNo,productId,};
				String query = getQuery("GET_ATTACHED_DOCUMENTS_LIST_MOTOR");
				LogManager.info("Query==>" + query);
				LogManager.info("Args==>" + StringUtils.join(args,", "));
				resultList=this.mytemplate.queryForList(query, args);
			}
		} catch(Exception exception) {
			LogManager.debug(exception);
		}

		return resultList;
	}

	public List<Map<String, Object>> getSurveyorAttachedDocs(String productId,String quoteNo, String reqFrom) {
		List<Map<String,Object>> resultList = new ArrayList<Map<String,Object>>();
		try {
			if("30".equalsIgnoreCase(productId)){
				Object[] args={quoteNo,productId};
				String query = getQuery("GET_ATTACHED_DOCUMENTS_LIST_HOME");
				LogManager.info("Query==>" + query);
				LogManager.info("Args==>" + StringUtils.join(args,", "));
				resultList=this.mytemplate.queryForList(query, args);
			}else if("65".equalsIgnoreCase(productId)){
				Object[] args={quoteNo,productId,};
				String query = getQuery("GET_ATTACHED_DOCUMENTS_LIST_MOTOR");
				LogManager.info("Query==>" + query);
				LogManager.info("Args==>" + StringUtils.join(args,", "));
				resultList=this.mytemplate.queryForList(query, args);
			}
		} catch(Exception exception) {
			LogManager.debug(exception);
		}

		return resultList;
	}
	
	public List<Map<String, Object>> getAttachmentDocList(String productId, String contentId) {
		List<Map<String,Object>> resultList = new ArrayList<Map<String,Object>>();
		try {
			String query = "";
			Object[] args=null;
					args=new Object[]{productId,contentId};
					query = getQuery("GET_EMAIL_ATTACHMENT_LIST");
			LogManager.info("Query==>" + query);
			LogManager.info("Args==>" + StringUtils.join(args,", "));
			resultList=this.mytemplate.queryForList(query, args);
		} catch(Exception exception) {
			LogManager.debug(exception);
		}

		return resultList;
	}
	
	public void insertAttachmentDetails(String ContentId,String documentPath, String docDesc, String fileName, String productId,String remarks) {
		try {
			String query = "";
			Object[] args = null;
			//Insert into EMAIL_ATTACHMENT_DETAILS (DOC_ID, CONTENT_ID, FILE_PATH_NAME, DESCRIPTION, PRODUCT_ID, STATUS, FILE_NAME) 
			//Values (select max((doc_id+1),0) from EMAIL_ATTACHMENT_DETAILS, ?, ?, ?, ?, ?, ?)
			query = getQuery("INS_ATTACHMENT");
			args = new Object[7];
			args[0] = ContentId;
			args[1] = documentPath;
			args[2] = docDesc;
			args[3] = productId;
			args[4] = "Y";
			args[5] = fileName;
			args[6] = remarks;
			LogManager.info("Query==>" + query);
			LogManager.info("Args==>" + StringUtils.join(args,", "));
			this.mytemplate.update(query,args);
		} catch(Exception exception) {
			LogManager.debug(exception);
		}
	}
	
	public void deleteAttachment(String filePath,String docId) {
		Object[] args={filePath,docId};
		try {
			String query =getQuery("DEL_ATTACHMENT");
			this.mytemplate.update(query, args);
			LogManager.info("Query==>" + query);
			LogManager.info("Args==>" + StringUtils.join(args,", "));
		}catch(Exception exception) {
			LogManager.debug(exception);
		}		
	}
	public String getTransactionId() {
	String result="";
	try{
		String sql="select trans_seq.nextval from dual";
		result= this.mytemplate.queryForObject(sql,String.class).toString();
	}catch (Exception e) {
		e.printStackTrace();
	}
	return result;
}
	public void insertTransactionDtl(String tranId, String policyType,String factorId, String status) {
		LogManager.info("Enter into insertTransactionDtl "+tranId);
	try {
		String query = "";
		Object[] args=null;
		args=new Object[]{tranId,policyType,factorId,status};
		query = "INSERT INTO RATE_TRANSACTION_DETAILS (S_NO,TRAN_ID,POLICYTYPE_ID,FACTOR_ID,STATUS) VALUES ((SELECT MAX(S_NO)+1 FROM RATE_TRANSACTION_DETAILS),?,?,?,?)";
		LogManager.info("Query==>" + query);
		LogManager.info("Args==>" + StringUtils.join(args,", "));
		this.mytemplate.update(query, args);
	} catch (Exception e) {
		e.printStackTrace();
	}
}
	public List getFactDBColumns(String typeId) throws BaseException{
		List list=null;
		try {
			String sql = "";
			sql = "SELECT EXCEL_HEADER_NAME,DB_COLUMN_NAME,MANDATORY_YN FROM factor_upload_config WHERE TYPE_ID='"+typeId+"' and STATUS='Y' ORDER BY SERIAL_NO";
			list=this.mytemplate.queryForList(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	public String insertRawTable(String fields,final List records, String tranId,String fileName,String filePath,String fileType,String rawTable, String typeId,String policyType,String factorID,String broker,String effectivedate) {
		LogManager.info("Enter into insertRawTable(), tranId= "+tranId+" policyType= "+policyType+" factorID= "+factorID+" rawTable= "+rawTable +" fields= "+fields +" records="+records);
		String errorMsg="";
		String sql = "";
		int totLinesProcessed = 0, i, j=0;
		int prev=0, lineNo=0;
		Object[] args=null;
		try 
		{ 
			if(!records.isEmpty())
			{ 
				for(i=0; i<records.size(); i++)
				{
					try{
						sql="INSERT INTO "+rawTable +"("+fields+",TRAN_ID,POLICYTYPE_ID,FACTOR_ID) VALUES ('"+StringUtils.trim(records.get(i).toString().trim())+","+tranId+","+policyType+","+factorID+")";				
						totLinesProcessed = i+1;
						this.mytemplate.update(sql);
					}
					catch (Exception e){
						LogManager.info("Error Raw Insert Query ==>"+sql);
						String excepMsg = e.getMessage();
						if(excepMsg.contains("not enough values")) {
							lineNo = (totLinesProcessed+1)-j;
							if(prev != lineNo){
							errorMsg+= "~Uploaded file has not enough values in line no: "+lineNo+"";
							j++;
							}
							prev = lineNo;
						}
						else
							if(excepMsg.contains("invalid number") || excepMsg.contains("value too large for column")) {
								lineNo = (totLinesProcessed+1)-j;
								if(prev != lineNo){
								errorMsg+= "~Uploaded file contains invalid number in line no: "+lineNo+"";
								}
								prev = lineNo;
							}
						else
						if(excepMsg.contains("ORA-")) {
							errorMsg+= excepMsg.substring(excepMsg.indexOf("ORA-"),excepMsg.length());
						break;
						}
						else
						{
							errorMsg+= "Uploaded file contains invalid file format";
							break;
						}
					}
				}
					/*if(totLinesProcessed>0) {
					sql="INSERT INTO IRCTC_TRANSACTION_DETAILS (TRAN_DATE,TRAN_ID,FILE_NAME,TOTAL,FILE_TYPE,UPLOADED, NOT_UPLOADED, DUPLICATES, DELETED) VALUES(sysdate,'"+tranId+"','"+fileName+"','"+totLinesProcessed+"','"+typeId+"',0,0,0,0)";
					this.mytemplate.update(sql);
				} */
			}try{
				sql="select count(*) from MOTOR_RATING_IMP_RAW where tran_id='"+tranId+"'";
	            int count = this.mytemplate.queryForInt(sql);
	            if(count>0){
	            	sql="UPDATE MOTOR_RATING_IMP_RAW SET EFFECTIVE_DATE=to_char(TO_DATE(?,'dd/mm/yyyy'),'dd-mm-yyyy') WHERE TRAN_ID='"+tranId+"'";
	            	args=new Object[]{effectivedate};
	            	this.mytemplate.update(sql, args);
	            }else{
	            	errorMsg+= "Error in Raw Table Insert";
	            	return errorMsg;
	            }
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
				
			/*try{
				sql="RATE_UPDATER";
				logger.info("Main Table insert==> ("+tranId+")" + sql);
				 SimpleJdbcCall procedure=new SimpleJdbcCall(this.mytemplate.getDataSource()).withCatalogName("MASTER_PKG").withProcedureName(sql);
				 Map<String,Object> inputValues=new HashMap<String, Object>();
				  inputValues.put("PVTRANID",tranId);
				  inputValues.put("PVPOLICYTYPE",policyType);
				  inputValues.put("PVFACTOR",factorID);
				  inputValues.put("PVAGENCYCODE",broker);
				  inputValues.put("PVACTIVEDATE",""); 
				  inputValues.put("PVTYPE","UPDATE"); 
				  
				  logger.info("Main Table insert ==>("+tranId+")"+ inputValues);
				  Map<String, Object> outputValues = procedure.execute(new MapSqlParameterSource(inputValues)); 
				  logger.info("Main Table insert ==>("+tranId+")"+ outputValues.toString());
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}*/
		}catch (Exception e){
			String excepMsg = e.getMessage();
			if(excepMsg.contains("not enough values")) {
				errorMsg= "Uploaded file contains invalid data in line no: "+(totLinesProcessed+1);
			}	
			else
			if(excepMsg.contains("ORA-")) 
				errorMsg += excepMsg.substring(excepMsg.indexOf("ORA-"),excepMsg.length());
			else
				errorMsg+= "Uploaded file contains invalid file format";
		}
		/*if(errorMsg!=null && !errorMsg.equals("")){
			sql="delete from "+rawTable+" where UPLOAD_TRANID="+tranId;
			this.mytemplate.update(sql);
		}*/
		return errorMsg;
		}
		public void updateTransactionDtl(String status, String message, String tranId) {
			LogManager.info("Enter into updateTransactionDtl "+tranId+" , "+status);
		try {
			String query = "";
			Object[] args=null;
			args=new Object[]{status,message,tranId};
			query = "UPDATE RATE_TRANSACTION_DETAILS SET STATUS=?,TRANSACTION_DESC=? WHERE TRAN_ID=?";
			LogManager.info("Query==>" + query);
			LogManager.info("Args==>" + StringUtils.join(args,", "));
			this.mytemplate.update(query, args);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public String mainTableInsert(String tranId,String policyType,String factorID,String broker,String vehicleUsage,String branchCode){
		LogManager.info("Enter Main Table insert");
		try{
			sql="RATE_UPDATER";
			LogManager.info("Main Table insert==> ("+tranId+")" + sql);
			 SimpleJdbcCall procedure=new SimpleJdbcCall(this.mytemplate.getDataSource()).withCatalogName("MASTER_PKG").withProcedureName(sql);
			 Map<String,Object> inputValues=new HashMap<String, Object>();
			  inputValues.put("PVTRANID",tranId);
			  inputValues.put("PVPOLICYTYPE",policyType);
			  inputValues.put("PVFACTOR",factorID);
			  inputValues.put("PVVEHUSAGEID",vehicleUsage);
			  inputValues.put("PVDIVISIONCODE",branchCode);
			  inputValues.put("PVAGENCYCODE",broker);
			  inputValues.put("PVACTIVEDATE",""); 
			  inputValues.put("PVTYPE","UPDATE"); 
			  
			  LogManager.info("Main Table insert ==>("+tranId+")"+ inputValues);
			  Map<String, Object> outputValues = procedure.execute(new MapSqlParameterSource(inputValues)); 
			  LogManager.info("Main Table insert ==>("+tranId+")"+ outputValues.toString());
		}catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

}
