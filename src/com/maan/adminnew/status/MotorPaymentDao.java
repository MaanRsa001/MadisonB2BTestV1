package com.maan.adminnew.status;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.maan.adminnew.paymentProcess.PaymentProcessBean;
import com.maan.common.LogManager;
import com.maan.common.MyJdbcTemplate;
import com.maan.common.sms.SmsEmailUtil;
import com.opensymphony.xwork2.ActionContext;

public class MotorPaymentDao extends MyJdbcTemplate {
	private String query="";
	private Object[] args = null;
	
		public List<Map<String,Object>> getMotorPayment(MotorPaymentBean bean){
			LogManager.info("Enter into the getMotorPayment()");
			List<Map<String,Object>> drpdwn=new ArrayList<Map<String,Object>>();
			try{
					if("quoteStatus".equalsIgnoreCase(bean.getReqFrom())){
					//query = getQuery("GET_MOTOR_PAYMENT_DETAIL");
						query = getQuery("GET_MOTOR_PAYMENT_DETAIL_NEW");
					}
					if("ccpStatus".equalsIgnoreCase(bean.getReqFrom())){
						query = getQuery("GET_MOTOR_PAYMENT_CC_DETAIL");
						bean.setPaymentType("all");
					}
					if("all".equalsIgnoreCase(bean.getPaymentType())){
						args = new Object[]{bean.getStartDate(),bean.getEndDate(),bean.getProductId()};
					}else{
						query += "AND PAYMENT_TYPE = ?";
						args = new Object[]{bean.getStartDate(),bean.getEndDate(),bean.getProductId(),bean.getPaymentType()};
					}
				LogManager.info("Query==>"+query);
				LogManager.info("Arguments => "+StringUtils.join(args,","));
				drpdwn=this.mytemplate.queryForList(query,args);
			}catch(Exception e){
				LogManager.debug("Exception Occured @ getMotorPayment()"+e);
				e.printStackTrace();
			}
			LogManager.info("Exit from getMotorPayment()");
			return drpdwn;
		}
		
		public List<Map<String,Object>> getMotorProduct(MotorPaymentBean bean){
			LogManager.info("Enter into the getMotorProduct()");
			List<Map<String,Object>> drpdwn=new ArrayList<Map<String,Object>>();
			try{
				args = new Object[1];
					args[0]=bean.getBranchCode();
					query = getQuery("GET_PRODUCT_NAME_LIST");	
				LogManager.info("Query==>"+query);
				LogManager.info("Arguments => "+StringUtils.join(args,","));
				drpdwn=this.mytemplate.queryForList(query,args);
			}catch(Exception e){
				LogManager.debug("Exception Occured @ getMotorProduct()"+e);
				e.printStackTrace();
			}
			LogManager.info("Exit from getMotorProduct()");
			return drpdwn;
		}
		
		public List<Map<String,Object>> getMotorPaymentType(MotorPaymentBean bean){
			LogManager.info("Enter into the getMotorPaymentType()");
			List<Map<String,Object>> drpdwn=new ArrayList<Map<String,Object>>();
			try{
				//query = getQuery("GET_PAYMENT_TYPE");
				query = getQuery("GET_PAYMENT_TYPE_REPORT");
				LogManager.info("Query==>"+query);
				drpdwn=this.mytemplate.queryForList(query);
			}catch(Exception e){
				LogManager.debug("Exception Occured @ getMotorPaymentType()"+e);
				e.printStackTrace();
			}
			LogManager.info("Exit from getMotorPaymentType()");
			return drpdwn;
		}
		public List<Map<String, Object>> viewPayment(MotorPaymentBean bean) {
			LogManager.info("Enter into viewPayment()");
			List<Map<String, Object>> result = null;
			try {
				String query = getQuery("GET_PAYMENT_VIEW_NEW");
				LogManager.info("query =>" + query);
				Object args[] = { bean.getQuoteNo(), bean.getReqTranNo(),bean.getBranchCode(),bean.getProductId()};
				LogManager.info("Arguments =>" + StringUtils.join(args, ","));
				result = this.mytemplate.queryForList(query, args);
				if (result.size() > 0) {
					Map map = (Map) result.get(0);
					String paymentId=map.get("PAYMET_ID") == null ? "": map.get("PAYMET_ID").toString();
					
					bean.setFirstName(map.get("FIRST_NAME") == null ? "": map.get("FIRST_NAME").toString());
					bean.setLastName(map.get("LAST_NAME") == null ? "": map.get("LAST_NAME").toString());
					bean.setAdd1(map.get("ADDRESS1") == null ? "": map.get("ADDRESS1").toString());
					bean.setAdd2(map.get("ADDRESS2") == null ? "": map.get("ADDRESS2").toString());
					bean.setPoBox(map.get("POBOX") == null ? "": map.get("POBOX").toString());
					bean.setMobileNo(map.get("MOBILE") == null ? "": map.get("MOBILE").toString());
					bean.setMailId(map.get("EMAIL") == null ? "": map.get("EMAIL").toString());
					bean.setQuoteNo(map.get("QUOTE_NO") == null ? "": map.get("QUOTE_NO").toString());
					bean.setCustomerId(map.get("CUSTOMER_ID") == null ? "": map.get("CUSTOMER_ID").toString());
					bean.setAppNo(map.get("APPLICATION_NO") == null ? "": map.get("APPLICATION_NO").toString());
					bean.setPolicyNo(map.get("POLICY_NO") == null ? "": map.get("POLICY_NO").toString());
					bean.setPremium(map.get("PREMIUM") == null ? "": map.get("PREMIUM").toString());
					bean.setVat(map.get("POLICY_FEE") == null ? "": map.get("POLICY_FEE").toString());
					bean.setTotalPremium(map.get("OVERALL_PREMIUM") == null ? "": map.get("OVERALL_PREMIUM").toString());
					bean.setFromDate(map.get("INCEPTION_DATE") == null ? "": map.get("INCEPTION_DATE").toString());
					bean.setToDate(map.get("EXPIRY_DATE") == null ? "": map.get("EXPIRY_DATE").toString());
					bean.setChequeNo(map.get("CHEQUE_NO") == null ? "": map.get("CHEQUE_NO").toString());
					bean.setBankName(map.get("BANK_NAME") == null ? "": map.get("BANK_NAME").toString());
					bean.setMicr(map.get("MICR_CODE") == null ? "": map.get("MICR_CODE").toString());
					bean.setChequeAmount(map.get("CHEQUE_AMOUNT") == null ? "": map.get("CHEQUE_AMOUNT").toString());
					bean.setChequeDate(map.get("CHEQUE_DATE") == null ? "": map.get("CHEQUE_DATE").toString());
					bean.setReqTime(map.get("REQUEST_TIME") == null ? "": map.get("REQUEST_TIME").toString());
					bean.setResTime(map.get("RESPONSE_TIME") == null ? "": map.get("RESPONSE_TIME").toString());
					if("101".equalsIgnoreCase(paymentId) || "102".equalsIgnoreCase(paymentId)){
						bean.setResTranNo(map.get("RESPONSE_TRAN_NO") == null ? "": map.get("RESPONSE_TRAN_NO").toString());
					}
					else
						bean.setResTranNo(map.get("RES_TRANSACTION_ID") == null ? "": map.get("RES_TRANSACTION_ID").toString());
					
					bean.setResStatus(map.get("RESPONSE_STATUS") == null ? "": map.get("RESPONSE_STATUS").toString());
					if("101".equalsIgnoreCase(paymentId) || "102".equalsIgnoreCase(paymentId)){
						bean.setResMsge(map.get("RESPONSE_MESSAGE") == null ? "": map.get("RESPONSE_MESSAGE").toString());
					}else
						bean.setResMsge(map.get("RES_MESSAGE") == null ? "": map.get("RES_MESSAGE").toString());
					
					bean.setMaskedCard(map.get("CARD_NUMBER_MASKED") == null ? "": map.get("CARD_NUMBER_MASKED").toString());
					bean.setResCode(map.get("RES_REASON_CODE") == null ? "": map.get("RES_REASON_CODE").toString());
					bean.setPaymentMode(map.get("PAYMENT_TYPE") == null ? "": map.get("PAYMENT_TYPE").toString());
					bean.setReqTranNo(map.get("MERCHANT_REFERENCE") == null ? "": map.get("MERCHANT_REFERENCE").toString());
					
					if("101".equalsIgnoreCase(paymentId) || "102".equalsIgnoreCase(paymentId)){
						bean.setResDeci(map.get("RESPONSE_STATUS") == null ? "": map.get("RESPONSE_STATUS").toString());
					}else
						bean.setResDeci(map.get("RES_DECISION") == null ? "": map.get("RES_DECISION").toString());
					
					bean.setResBillTranNo(map.get("RES_BILL_TRANS_REF_NO") == null ? "": map.get("RES_BILL_TRANS_REF_NO").toString());
					bean.setInsYN(map.get("INSTALLMENT_YN") == null ? "": map.get("INSTALLMENT_YN").toString());
					bean.setInsFrq(map.get("INSTALLMENT_FREQUENCY") == null ? "": map.get("INSTALLMENT_FREQUENCY").toString());
					bean.setInsAmount(map.get("INSTALLMENT_AMOUNT") == null ? "": map.get("INSTALLMENT_AMOUNT").toString());
					bean.setInsStartdate(map.get("INSTALLMENT_START_DATE") == null ? "": map.get("INSTALLMENT_START_DATE").toString());
					bean.setNoInstallment(map.get("NO_OF_INSTALLMENT") == null ? "": map.get("NO_OF_INSTALLMENT").toString());
					bean.setCurrency(map.get("CURRENCY_TYPE") == null ? "": map.get("CURRENCY_TYPE").toString());
					
				}	
			} catch (Exception e) {
				LogManager.error("Exception Occured @ viewPayment()" + e);
				e.printStackTrace();
			}
			LogManager.info("Exit from viewPayment() ");
			return result;
		}
		
		public List<Map<String,Object>> getCreditPayment(MotorPaymentBean bean){
			LogManager.info("Enter into the getCreditPayment()");
			List<Map<String,Object>> drpdwn=new ArrayList<Map<String,Object>>();
			try{
				args = new Object[1];
					args[0]=bean.getQuoteNo();
					query = getQuery("GET_CP_PAYMENT_LIST");	
				LogManager.info("Query==>"+query);
				LogManager.info("Arguments => "+StringUtils.join(args,","));
				drpdwn=this.mytemplate.queryForList(query,args);
			}catch(Exception e){
				LogManager.debug("Exception Occured @ getCreditPayment()"+e);
				e.printStackTrace();
			}
			LogManager.info("Exit from getCreditPayment()");
			return drpdwn;
		}
	
		public String updateCCP(MotorPaymentBean bean) {
			String result="";
			try{
				LogManager.info("Enter into the updateCCP()");
				query = getQuery("UPDATE_PAYMENT_CREDIT_DETAIL");
				Object args[]=new Object[19];
				
				args[0] = bean.getResTime();
				args[1] = bean.getResTranNo();
				args[2] = bean.getResTranNo();
				args[3] = bean.getResTranNo();
				args[4] = bean.getResTranNo();
				args[5] = bean.getCardDate();
				args[6] = bean.getResCode();
				args[7] = bean.getPayAmount();
				args[8] = bean.getTotalPremium();
				args[9] = bean.getResCode();
				args[10] = bean.getCurrency();
				args[11] = bean.getResDeci();
				args[12] = bean.getResCode();
				args[13] = bean.getInsFrq();
				args[14] = bean.getNoInstallment();
				args[15] = bean.getInsAmount();
				args[16] = bean.getLoginId();
				args[17] = bean.getReqTranNo();
				args[18] = bean.getQuoteNo();
				//args[4] = bean.getResMsge();
				//args[5] = bean.getResBillTranNo();
				//args[6] = bean.getResDeci();
				//args[7] = bean.getLoginId();
				//args[8] = "Y";
				//args[9] = bean.getResCode();
				//args[10] = bean.getMaskedCard();
				//args[11] = bean.getReqTranNo();
				//args[12] = bean.getQuoteNo();
				removeNull(args);
				LogManager.info("Query==>"+query);
				LogManager.info("Arguments => "+StringUtils.join(args,","));
				this.mytemplate.update(query,args);
				
				//Personal Detail In Payment Details
				query=getQuery("UPD_PAYMENT_INFO");
				args =new Object[2];
				args[0] = bean.getQuoteNo();
				args[1] = bean.getReqTranNo();
				LogManager.info("Update Query => "+query);
				LogManager.info("Update Arguments => "+StringUtils.join(args, ","));
				this.mytemplate.update(query,args);
				
			}catch(Exception e){
				e.printStackTrace();
			}
			LogManager.info("Exit from updateCCP()");
			return result;
			
		}

		public List<Map<String, Object>> getResCodeDescription(MotorPaymentBean bean) {
			LogManager.info("Enter into the getResCodeDecription()");
			List<Map<String,Object>> drpdwn=new ArrayList<Map<String,Object>>();
				try{
					Object args[] = new Object[]{bean.getResDeci()};
					query = getQuery("GET_RES_CODE");	
					LogManager.info("Query==>"+query);
					LogManager.info("Arguments => "+StringUtils.join(args,","));
					drpdwn=this.mytemplate.queryForList(query,args);
				}catch(Exception e){
					LogManager.debug("Exception Occured @ getResCodeDecription()"+e);
					e.printStackTrace();
				}
				LogManager.info("Exit from getResCodeDecription()");
			return drpdwn;
		}
		
		public List<Map<String, Object>> getResCodeDecision(MotorPaymentBean bean) {
			LogManager.info("Enter into the getResCodeDecision()");
			List<Map<String,Object>> drpdwn=new ArrayList<Map<String,Object>>();
				try{
					query = getQuery("GET_RES_DECISION");	
					LogManager.info("Query==>"+query);
					drpdwn=this.mytemplate.queryForList(query);
				}catch(Exception e){
					LogManager.debug("Exception Occured @ getResCodeDecision()"+e);
					e.printStackTrace();
				}
				LogManager.info("Exit from getResCodeDecision()");
			return drpdwn;
		}
		
	//Installment Report
		public List<Map<String,Object>> getInstallmentReport(MotorPaymentBean bean){
			LogManager.info("Enter into the getInstallmentReport()");
			List<Map<String,Object>> drpdwn=new ArrayList<Map<String,Object>>();
			try{
					query = getQuery("GET_INSTALLMENT_LIST");
					/*if("all".equalsIgnoreCase(bean.getPaymentType())){*/
					args = new Object[]{bean.getStartDate(),bean.getEndDate(),bean.getProductId()};
					/*}else{
						query += "AND PAYMENT_TYPE = ?";
						args = new Object[]{bean.getStartDate(),bean.getEndDate(),bean.getProductId(),bean.getPaymentType()};
					}*/
				LogManager.info("Query==>"+query);
				LogManager.info("Arguments => "+StringUtils.join(args,","));
				drpdwn=this.mytemplate.queryForList(query,args);
			}catch(Exception e){
				LogManager.debug("Exception Occured @ getInstallmentReport()"+e);
				e.printStackTrace();
			}
			LogManager.info("Exit from getInstallmentReport()");
			return drpdwn;
		}
		
		public List<Map<String,Object>> getNoInstallment(String quoteNo){
			LogManager.info("Enter into the getNoInstallment()");
			List<Map<String,Object>> drpdwn=new ArrayList<Map<String,Object>>();
			try{
				args = new Object[1];
					args[0]=quoteNo;
					query = getQuery("GET_INSTALLMENT_REPORT");	
				LogManager.info("Query==>"+query);
				LogManager.info("Arguments => "+StringUtils.join(args,","));
				drpdwn=this.mytemplate.queryForList(query,args);
			}catch(Exception e){
				LogManager.debug("Exception Occured @ getNoInstallment()"+e);
				e.printStackTrace();
			}
			LogManager.info("Exit from getNoInstallment()");
			return drpdwn;
		}
		public List<Map<String, Object>> viewInstallment(MotorPaymentBean bean) {
			LogManager.info("Enter into viewInstallment()");
			List<Map<String, Object>> result = null;
			try {
				String query = getQuery("GET_INSTALLMENT_VIEW");
				LogManager.info("query =>" + query);
				Object args[] = {bean.getQuoteNo()};
				LogManager.info("Arguments =>" + StringUtils.join(args, ","));
				result = this.mytemplate.queryForList(query, args);
				if (result.size() > 0) {
					Map map = (Map) result.get(0);
					bean.setFirstName(map.get("FIRST_NAME") == null ? "": map.get("FIRST_NAME").toString());
					bean.setLastName(map.get("LAST_NAME") == null ? "": map.get("LAST_NAME").toString());
					bean.setAdd1(map.get("ADDRESS1") == null ? "": map.get("ADDRESS1").toString());
					bean.setAdd2(map.get("ADDRESS2") == null ? "": map.get("ADDRESS2").toString());
					bean.setPoBox(map.get("POBOX") == null ? "": map.get("POBOX").toString());
					bean.setMobileNo(map.get("MOBILE") == null ? "": map.get("MOBILE").toString());
					bean.setMailId(map.get("EMAIL") == null ? "": map.get("EMAIL").toString());
					bean.setQuoteNo(map.get("QUOTE_NO") == null ? "": map.get("QUOTE_NO").toString());
					bean.setCustomerId(map.get("CUSTOMER_ID") == null ? "": map.get("CUSTOMER_ID").toString());
					bean.setPolicyNo(map.get("POLICY_NO") == null ? "": map.get("POLICY_NO").toString());
					bean.setPremium(map.get("PREMIUM") == null ? "": map.get("PREMIUM").toString());
					bean.setVat(map.get("POLICY_FEE") == null ? "": map.get("POLICY_FEE").toString());
					bean.setTotalPremium(map.get("OVERALL_PREMIUM") == null ? "": map.get("OVERALL_PREMIUM").toString());
					bean.setFromDate(map.get("INCEPTION_DATE") == null ? "": map.get("INCEPTION_DATE").toString());
					bean.setToDate(map.get("EXPIRY_DATE") == null ? "": map.get("EXPIRY_DATE").toString());
					bean.setNoOfEmi(map.get("NO_OF_EMI") == null ? "": map.get("NO_OF_EMI").toString());
					bean.setPaymentMode(map.get("PAYMENT_MODE") == null ? "": map.get("PAYMENT_MODE").toString());
					bean.setPaymentType(map.get("MODE_OF_PAYMENT") == null ? "": map.get("MODE_OF_PAYMENT").toString());
					bean.setCurrency(map.get("CURRENCY") == null ? "": map.get("CURRENCY").toString());
				}	
			} catch (Exception e) {
				LogManager.error("Exception Occured @ viewInstallment()" + e);
				e.printStackTrace();
			}
			LogManager.info("Exit from viewInstallment() ");
			return result;
		}
		
		public List<Map<String, Object>> viewInstallmentPayment(MotorPaymentBean bean) {
			LogManager.info("Enter into viewInstallmentPayment()");
			List<Map<String, Object>> result = null;
			try {
				String query = getQuery("GET_INSTALLMENT_PAYMENT");
				LogManager.info("query =>" + query);
				Object args[] = {bean.getQuoteNo(),bean.getNoInstallment()};
				LogManager.info("Arguments =>" + StringUtils.join(args, ","));
				result = this.mytemplate.queryForList(query, args);
				if (result.size() > 0) {
					Map map = (Map) result.get(0);
					bean.setChequeNo(map.get("CHEQUE_NO") == null ? "": map.get("CHEQUE_NO").toString());
					bean.setBankName(map.get("BANK_NAME") == null ? "": map.get("BANK_NAME").toString());
					bean.setMicr(map.get("MICR_CODE") == null ? "": map.get("MICR_CODE").toString());
					bean.setChequeAmount(map.get("CHEQUE_AMOUNT") == null ? "": map.get("CHEQUE_AMOUNT").toString());
					bean.setChequeDate(map.get("CHEQUE_DATE") == null ? "": map.get("CHEQUE_DATE").toString());
					bean.setCashDepositBank(map.get("BANK_NAME") == null ? "": map.get("BANK_NAME").toString());
					bean.setCashChellanNo(map.get("CHEQUE_NO") == null ? "": map.get("CHEQUE_NO").toString());
					bean.setCashAmount(map.get("CHEQUE_AMOUNT") == null ? "": map.get("CHEQUE_AMOUNT").toString());
					bean.setCashInstrumentDate(map.get("CHEQUE_DATE") == null ? "": map.get("CHEQUE_DATE").toString());
					bean.setReqTime(map.get("REQUEST_TIME") == null ? "": map.get("REQUEST_TIME").toString());
					bean.setResTime(map.get("RESPONSE_TIME") == null ? "": map.get("RESPONSE_TIME").toString());
					bean.setResTranNo(map.get("RESPONSE_TRAN_NO") == null ? "": map.get("RESPONSE_TRAN_NO").toString());
					bean.setResCode(map.get("RES_REASON_CODE") == null ? "": map.get("RES_REASON_CODE").toString());
					bean.setResDeci(map.get("RES_DECISION") == null ? "": map.get("RES_DECISION").toString());
					bean.setPaymentMode(map.get("PAYMENT_TYPE") == null ? "": map.get("PAYMENT_TYPE").toString());
					bean.setReqTranNo(map.get("MERCHANT_REFERENCE") == null ? "": map.get("MERCHANT_REFERENCE").toString());
					bean.setPaymentType(map.get("MODE_OF_PAYMENT") == null ? "": map.get("MODE_OF_PAYMENT").toString());
					bean.setResMsge(map.get("RES_MESSAGE") == null ? "": map.get("RES_MESSAGE").toString());
					bean.setCardDate(map.get("RES_REQ_CARD_EXPIRY_DATE") == null ? "": map.get("RES_REQ_CARD_EXPIRY_DATE").toString());
					bean.setPayAmount(map.get("RES_AUTH_AMOUNT") == null ? "": map.get("RES_AUTH_AMOUNT").toString());
					
					
				}	
			} catch (Exception e) {
				LogManager.error("Exception Occured @ viewInstallmentPayment()" + e);
				e.printStackTrace();
			}
			LogManager.info("Exit from viewInstallmentPayment() ");
			return result;
		}

		public List<Map<String, Object>> getDueCount(MotorPaymentBean bean) {
			List<Map<String, Object>> drpdown=new ArrayList<Map<String,Object>>();
			LogManager.info("Enter into the getDueCount()");
			try{
				query=getQuery("GET_DUE_COUNT");
				LogManager.info("Query==>"+query);
				drpdown=this.mytemplate.queryForList(query,new Object[]{bean.getStartDate(),bean.getEndDate(),bean.getProductId()});
				LogManager.info("Arguments => "+StringUtils.join(new Object[]{bean.getProductId(),bean.getStartDate(),bean.getEndDate()},","));
			}catch(Exception e){
				e.printStackTrace();
			}
			LogManager.info("Exit from the getDueCount()");
			return drpdown;
		}

		public List<Map<String, Object>> getPaymentDue(MotorPaymentBean bean) {
			List<Map<String, Object>> drpdown=new ArrayList<Map<String,Object>>();
			LogManager.info("Enter into the getPaymentDue()");
			try{
				if("P".equalsIgnoreCase(bean.getSearchType()) || "Q".equalsIgnoreCase(bean.getSearchType()) || "C".equalsIgnoreCase(bean.getSearchType())){
				query=getQuery("GET_DUE_LIST_SEARCH");
					args =new Object[]{bean.getProductId(),bean.getSearchType(),bean.getSearchValue()};
				}else{
						query=getQuery("GET_DUE_LIST");
						args =new Object[]{bean.getProductId(),bean.getStartDate(),bean.getEndDate()};
				}
				LogManager.info("Query==>"+query);
				LogManager.info("Arguments => "+StringUtils.join(args,","));
				drpdown=this.mytemplate.queryForList(query,args);
			}catch(Exception e){
				e.printStackTrace();
			}
			LogManager.info("Exit from the getPaymentDue()");
			return drpdown;
		}
		
		public List<Object> getBankNameList(MotorPaymentBean bean) {
			List<Object> result=null;
			try{
				String sql= getQuery("GET_BANK_NAME_LIST_ADMIN");
				LogManager.info("Query => "+sql);
				result = this.mytemplate.queryForList(sql,new Object[]{bean.getBranchCode()}); 
			}catch(Exception e){
				e.printStackTrace();
			}
			return result;
		}

		public String insPaymentInsert(MotorPaymentBean bean) {
			String refNo="";
			String result="";
			try{
				LogManager.info("Enter Into insPaymentInsert()");
				String query = getQuery("GET_PAYMENT_TRANID_SEQ");
				refNo = (String) this.mytemplate.queryForObject(query, String.class);
				query=getQuery("INSERT_INSTALLMENT_PAYMENT");
				Object[] args=new Object[40];
				args[0]= bean.getPaymentMode();
				args[1]= bean.getInsAmount();
				if("2".equalsIgnoreCase(bean.getPaymentMode())){
					args[2]= bean.getChequeNo();
					args[3]= bean.getBankName();
					args[4]= bean.getMicr();
					args[5]= bean.getChequeAmount();
					args[6]= bean.getChequeDate();
					args[7]= bean.getChequeDate();
				}else if("1".equalsIgnoreCase(bean.getPaymentMode())){
					args[2]= bean.getCashChellanNo();
					args[3]= bean.getCashDepositBank();
					args[4]= "";
					args[5]= bean.getCashAmount();
					args[6]= bean.getChequeDate();
					args[7]= bean.getChequeDate();
				}
					args[8]= bean.getResTime();
					args[9]= bean.getResTime();
					args[10]= bean.getResTranNo();
					args[11]= refNo;
					args[12]= bean.getResCode();
					args[13] = bean.getResTranNo();
					args[14] = "Y";
					args[15] = bean.getCardDate();
					args[16] = bean.getResCode();
					args[17] = bean.getPayAmount();
					args[18] = "00";
					args[19] = bean.getResTranNo();
					args[20] = "card";
					args[21] = bean.getResTranNo();
					args[22] = bean.getInsAmount();
					args[23] = bean.getResCode();
					args[24] = bean.getResTranNo();
					args[25] = bean.getResCode();
					args[26] = bean.getResCode();
					args[27] = bean.getLoginId();
					args[28] ="Y";
					args[29] =bean.getInsAmount();
					args[30] = "Y";
					args[31] = "sale";
					args[32] = "00";
					args[33] = "monthly";
					args[34] = bean.getNoInstallment();
					args[35] = bean.getInsAmount();
					args[36] = bean.getResTranNo();
					args[37] = bean.getNoInstallment();
					args[38] = bean.getDesci();
					args[39] = bean.getQuoteNo();
				removeNull(args);
				LogManager.info("Query => "+query);
				LogManager.info("Arguments => "+StringUtils.join(args, ","));
				this.mytemplate.update(query,args);	
				LogManager.info("Exit From updatePaymentDetails()");
			}catch (Exception e) {
				LogManager.debug("Exception occured @ updatePaymentDetails{"+e+"}");
				e.printStackTrace();
				result = e.toString();
			}
			return result;
		}
		
		public void updateInsPay(MotorPaymentBean bean) {
			LogManager.info("Entr Into updateInsPay()");
			String query="";
			try {
					query = getQuery("UPDATE_INS_PAYSTATUS");
					Object args[]={"Y",bean.getQuoteNo(),bean.getNoInstallment()};
					LogManager.info("Query => " + query);
					LogManager.info("Arguments => "+StringUtils.join(args,","));
					this.mytemplate.update(query,args );
					
			} catch (Exception e) {
				e.printStackTrace();
			}
			LogManager.info("Exit From updateInsPay()");
		}
		
}



