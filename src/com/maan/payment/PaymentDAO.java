package com.maan.payment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;

import com.maan.common.DBConstants;
import com.maan.common.LogManager;
import com.maan.common.MyJdbcTemplate;
import com.maan.common.SimpleLogger;
import com.maan.common.sms.SmsEmailUtil;
import com.maan.dao.ApiCaller.ApiForMotor;
import com.maan.payment.mtn.MtnService;

public class PaymentDAO extends MyJdbcTemplate {
	ApiForMotor motorApi = new ApiForMotor();
	
	public Map<String,Object> getPaymentDetails(String merchant_reference) {
		Map<String,Object> resultMap = new HashMap<String, Object>();
		try {
			if(StringUtils.isNotBlank(merchant_reference)){
				String query = getQuery("GET_PAYMENT_DETAILS");
				Object[] args = new Object[]{merchant_reference};
				LogManager.info("Query=>"+query);
				LogManager.info("obj[] ==> "+StringUtils.join(args, ","));
				resultMap = this.mytemplate.queryForMap(query, args);
			}
		} catch(Exception exception) {
			LogManager.debug(exception);
		}
		return resultMap;
	}
	
	public void updatePaymentResponse(PaymentBean bean) {
		try {
			String query = getQuery("UPD_MOTOR_PAY_DETAILS");
			Object[] args = new Object[32];
			args[0] = bean.getAuth_trans_ref_no();
			args[1] = bean.getPayer_authentication_enroll_veres_enrolled();
			args[2] = bean.getReq_bill_to_surname();
			args[3] = bean.getReq_bill_to_address_city();
			args[4] = bean.getReq_card_expiry_date();
			args[5] = bean.getReq_bill_to_address_postal_code();
			args[6] = bean.getReason_code();
			args[7] = bean.getAuth_amount();
			args[8] = bean.getAuth_response();
			args[9] = bean.getBill_trans_ref_no();
			args[10] = bean.getReq_bill_to_forename();
			args[11] = bean.getReq_payment_method();
			args[12] = bean.getRequest_token();
			args[13] = bean.getAuth_time();
			args[14] = bean.getReq_amount();
			args[15] = bean.getReq_bill_to_email();
			args[16] = bean.getPayer_authentication_reason_code();
			args[17] = bean.getAuth_avs_code_raw();
			args[18] = bean.getTransaction_id();
			args[19] = bean.getReq_currency();
			args[20] = bean.getReq_card_type();
			args[21] = bean.getPayer_authentication_pares_status();
			args[22] = bean.getDecision();
			args[23] = bean.getPayer_authentication_cavv();
			args[24] = bean.getMessage();
			args[25] = bean.getReq_transaction_type();
			args[26] = bean.getAuth_code();
			args[27] = bean.getReq_recurring_frequency();
			args[28] = bean.getReq_recurring_number_of_installments();
			args[29] = bean.getReq_recurring_amount();
			args[30] = bean.getPayment_token();
			
			args[31] = bean.getMerchant_reference();
			
			removeNull(args);
			LogManager.info("Query=>"+query);
			LogManager.info("obj[] ==> "+StringUtils.join(args, ","));
			this.mytemplate.update(query, args);
		} catch(Exception exception) {
			LogManager.debug(exception);
		}
	}
	
	public String insertPaymentDetails(String modeOfPayment,String chequeNo, String chequeDate, String chequeAmount, 
			String totalPremium, String bankName, String micrCode,String cashDepositBank,String cashAmount,
			String cashChellanNo,String cashInstrumentDate,String applicationNo,String quoteNo,
			String productId,String merchant_reference,String email,String customerName,String branchCode,
			String currencyType, String installmentYN, String mobileNo, String deviceType, String mtnMobileNo ) {
		String refNo="";
		try{
			LogManager.info("Enter Into updatePaymentDetails()");
			String query = getQuery("GET_PAYMENT_TRANID_SEQ");
			refNo = (String) this.mytemplate.queryForObject(query, String.class);
			query=getQuery("INS_PAY_DETAILS");
			Object[] args=new Object[26];
			args[0]=applicationNo;
			args[1]=quoteNo;
			args[2]=productId;
			args[3]=modeOfPayment;
			if("1".equalsIgnoreCase(modeOfPayment)) {
				args[4]=cashChellanNo;
				args[5]=cashDepositBank;
				args[6]="";
				args[7]=cashAmount;
				args[8]=cashInstrumentDate;
			} else if("2".equalsIgnoreCase(modeOfPayment)) {
				args[4]=chequeNo;
				args[5]=bankName;
				args[6]=micrCode;
				args[7]=chequeAmount;
				args[8]=chequeDate;
			} else {
				args[4]="";
				args[5]="";
				args[6]="";
				args[7]="";
				args[8]="";
			}
			args[9] = refNo;
			args[10] = email;
			args[11] = customerName;
			args[12] = branchCode;
			args[13] = currencyType;
			if("Y".equals(installmentYN)) {
				List<Map<String,Object>> installmentDetailsList = getInstallmentDetailsList(quoteNo);
				args[14] = installmentDetailsList.get(0).get("PREMIUM_AMOUNT")==null?"":installmentDetailsList.get(0).get("PREMIUM_AMOUNT").toString();
				args[15] = installmentDetailsList.get(1).get("NO_OF_TERMS")==null?"":installmentDetailsList.get(1).get("NO_OF_TERMS").toString();
				args[16] = installmentDetailsList.get(1).get("PREMIUM_AMOUNT")==null?"":installmentDetailsList.get(1).get("PREMIUM_AMOUNT").toString();
				args[17] = "monthly";
				args[18] = installmentDetailsList.get(1).get("PAYMENT_PREMIUM_DATE")==null?"":installmentDetailsList.get(1).get("PAYMENT_PREMIUM_DATE").toString();
				args[19] = installmentYN;
				args[20] = installmentDetailsList.get(0).get("INSTALLMENT_NO")==null?"":installmentDetailsList.get(0).get("INSTALLMENT_NO").toString();
				args[21] = installmentDetailsList.get(0).get("DESCRIPTION")==null?"":installmentDetailsList.get(0).get("DESCRIPTION").toString();
			} else {
				args[14] = totalPremium;
			}
			args[22] = mobileNo;
			args[23] = deviceType;
			if("101".equalsIgnoreCase(modeOfPayment)) {
				MtnService ms = new MtnService(quoteNo, "", refNo, productId);
				args[24] = ms.getUuidNo();
				args[25] = mtnMobileNo;
			}else if("102".equalsIgnoreCase(modeOfPayment)) {
				args[24] = "";
				args[25] = mtnMobileNo;
			}
			else{
				args[24] = "";
				args[25] = "0";
			}
			removeNull(args);
			LogManager.info("Query => "+query);
			LogManager.info("Arguments => '"+StringUtils.join(args, "','")+"'");
			this.mytemplate.update(query,args);
			
			//update personal info details into payment deatails 
			query=getQuery("UPD_PAYMENT_INFO");
			args =new Object[2];
			args[0] = quoteNo;
			args[1] = refNo;
			LogManager.info("Update Query => "+query);
			LogManager.info("Update Arguments => "+StringUtils.join(args, "','")+"'");
			this.mytemplate.update(query,args);
			
			
			//SMS Send
			new SmsEmailUtil(SmsEmailUtil.BUY_POLICY,quoteNo).send();
			new SmsEmailUtil(SmsEmailUtil.BUY_POLICY_OPUSER,quoteNo).send();
			LogManager.info("Exit Into updatePaymentDetails()");
		}catch (Exception e) {
			LogManager.debug("Exception occured @ updatePaymentDetails{"+e+"}");
			e.printStackTrace();
		}
		return refNo;
	}
	
	public String updatePaymentDetailsForCC(String modeOfPayment,String chequeNo, String chequeDate, String chequeAmount, String totalPremium, String bankName, String micrCode,String cashDepositBank,String cashAmount,String cashChellanNo,String cashInstrumentDate,String applicationNo,String quoteNo,String productId,String merchant_reference,String email,String customerName,String branchCode) {
		String resul="";
		try{
			LogManager.info("Enter Into updatePaymentDetailsForCC()");
			String query=getQuery("UPDATE_PAY_CC_DETAILS");
			Object[] args=new Object[6];
			if("1".equalsIgnoreCase(modeOfPayment)) {
				args[0]=cashChellanNo;
				args[1]=cashDepositBank;
				args[2]="";
				args[3]=cashAmount;
				args[4]=cashInstrumentDate;
			} else if("2".equalsIgnoreCase(modeOfPayment)) {
				args[0]=chequeNo;
				args[1]=bankName;
				args[2]=micrCode;
				args[3]=chequeAmount;
				args[4]=chequeDate;
			} 
			args[5] = merchant_reference;
			LogManager.info("Query => "+query);
			LogManager.info("Arguments => "+StringUtils.join(args, ","));
			this.mytemplate.update(query,args);	
			LogManager.info("Exit Into updatePaymentDetailsForCC()");
		}catch (Exception e) {
			LogManager.debug("Exception occured @ updatePaymentDetailsForCC{"+e+"}");
			e.printStackTrace();
		}
		return resul;
	}
	
	
	
	public Map<String, Object> getBankInfoAjax(String modeOfPay, String currencyType) {
		Map<String, Object> resMap = null;
		try {
			resMap = motorApi.getBankInfoAjax(modeOfPay,currencyType);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return resMap;
	}
//		Map<String,Object> result=null;
//		try{
//			if(StringUtils.isNotBlank(modeOfPay) && StringUtils.isNotEmpty(currencyType)){
//				String query=getQuery("GET_PAYMENT_BANK_INFO");
//				Object[] args = new Object[]{modeOfPay,currencyType};
//				LogManager.info("Query==> " + query);
//				LogManager.info("Args==> " + StringUtils.join(args, ", "));
//				result=this.mytemplate.queryForMap(query,args);
//			}
//		}catch (Exception e) {
//			e.printStackTrace();
//		}
//		return result;
//	}
	
	public List<Map<String,Object>> getInstallmentDetailsList(String quoteNo) {
		List<Map<String,Object>> resultList = new ArrayList<Map<String,Object>>();
		try {
			String query = getQuery("GET_INSTALLMENTDETAILS_LIST");
			Object[] args = new Object[1];
			args[0] = quoteNo;
			LogManager.info("Query==> " + query);
			LogManager.info("Args==> " + StringUtils.join(args, ", "));
			resultList = this.mytemplate.queryForList(query, args);
		} catch(Exception exception) {
			LogManager.debug(exception);
		}
		return resultList;
	}
	
	public void installmentCalc(PaymentBean bean) {
		try {
			SimpleJdbcCall procedure= mySimpleJdbcCall.withProcedureName("MOTOR_INSTALLMENT_CALC");
	     	Map<String,Object> inputValues=new java.util.TreeMap<String, Object>(String.CASE_INSENSITIVE_ORDER);
	     	inputValues.put("pvquote",bean.getQuoteNo());
	     	Map<String,Object> outputValues = procedure.execute(new MapSqlParameterSource(inputValues)); //Executes the procedure.
	     	bean.setInstallmentErrorStatus(outputValues.get("PVOUT")==null?"":outputValues.get("PVOUT").toString());
		} catch(Exception exception) {
			LogManager.debug(exception);
		}
	}
	
	public Map<String,Object> paymentProfileDetails(String currencyType) {
		Map<String,Object> resultMap = null;
		try {
			String query = getQuery("GET_PAYMENTPROFILE_DETAILS");
			Object[] args = new Object[1];
			args[0] = currencyType;
			LogManager.info("Query==> " + query);
			LogManager.info("Args==> " + StringUtils.join(args, ", "));
			resultMap = this.mytemplate.queryForMap(query, args);
			
		} catch(Exception exception) {
			LogManager.debug(exception);
		}
		return resultMap;
	}
	
	public Map<String,Object> getwsQuoteInfo(String quoteNo) {
		Map<String,Object> resulMap = new HashMap<String, Object>();
		try {
			String query = getQuery("GET_PAYMENTWS_QUOTEDTLS", new Object[]{DBConstants.PAYMENT_ZAMTEL});
			Object[] args = new Object[1];
			args[0] = quoteNo;
			SimpleLogger.info("Query==>"+query);
			SimpleLogger.info("Arg==>"+StringUtils.join(args,", "));
			List<Map<String,Object>> resultList = this.mytemplate.queryForList(query, args);
			if(resultList.size()>0) {
				resulMap = resultList.get(0);
			}
		} catch(Exception exception) {
			LogManager.debug(exception);
		}
		return resulMap;
	}
	
	public int insWSReq(String quoteNo, String reqTime, String resStatus) {
		int result=0;
		try {
			SimpleLogger.info("Enter into the insResReq");
			String query = getQuery("INS_PAYMENTWS_REQTRAN");
			Object args[]=new Object[]{quoteNo,reqTime,resStatus};
			SimpleLogger.info("Query==>"+query);
			SimpleLogger.info("Arg==>"+StringUtils.join(args,", "));
			result=this.mytemplate.update(query,args);
			SimpleLogger.info("Exit from the insResReq");
		} catch(Exception exception){
			LogManager.debug(exception);
		}
		return result;
	}
	
	//QUOTE_NO~FIRST_NAME~LAST_NAME~PREMIUM~RESPONSE_TRAN_NO~RESPONSE_MESSAGE~DECISION
	public void updatePaymentWSDetails(String[] result) {
		try {
			String query = getQuery("UPD_PAYMENTWS_DTLS");
			Object[] args = new Object[7];
			args[0] = result[6];
			args[1] = result[1];
			args[2] = result[2];
			args[3] = result[3];
			args[4] = result[4];
			args[5] = result[5];
			args[6] = result[0];
			SimpleLogger.info("Query==>"+query);
			SimpleLogger.info("Arg==>"+StringUtils.join(args,", "));
			this.mytemplate.update(query, args);
		} catch(Exception exception){
			LogManager.debug(exception);
		}
	}
	
	public String getmerchant_referenceByQuote(String quoteNo) {
		String result = "";
		try {
			String query = getQuery("GET_MERCHANT_REFERENCE_BYQUOTENO");
			Object[] args = new Object[1];
			args[0] = quoteNo;
			SimpleLogger.info("Query==>"+query);
			SimpleLogger.info("Arg==>"+StringUtils.join(args,", "));
			result = (String) this.mytemplate.queryForObject(query, args, String.class);
		} catch(Exception exception){
			LogManager.debug(exception);
		}
		return result;
	}
	
	public void updateMtnPaymentResponse(String transactionId, String transactionStatus, String reason,
			String referenceNo, String responseTime, String merchant_reference) {
		try{
			String query = "UPDATE PAYMENT_DETAIL SET RESPONSE_TIME = SYSDATE, RESPONSE_TRAN_NO = ?, RESPONSE_STATUS = ?,"
					+ " RESPONSE_MESSAGE = ? WHERE MERCHANT_REFERENCE = ?";
			Object args[] = new Object[]{transactionId, transactionStatus, reason, merchant_reference};
			LogManager.info("Query: "+query+" Argumnets: "+StringUtils.join(args,", "));
			this.mytemplate.update(query, args);
		}catch(Exception e){
			LogManager.info("Exception Occurred @ PaymentDAO.updateMtnPaymentResponse(): "+e);
			e.printStackTrace();
		}
	}
	
}
