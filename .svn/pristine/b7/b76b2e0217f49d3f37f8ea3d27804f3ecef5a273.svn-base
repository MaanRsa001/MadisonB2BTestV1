package com.maan.payment.mtn;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.maan.common.LogManager;
import com.maan.common.MyJdbcTemplate;

public class MtnDao extends MyJdbcTemplate{

	public String getJwtToken() {
		String token = "";
		try{
			String query = "SELECT JWT_TOKEN FROM JWT_TOKEN WHERE TOKEN_FOR = 'MTN_PAYMENT'" +
					" AND SYSDATE BETWEEN ENTRY_DATE AND EXPIRY_DATE ORDER BY ENTRY_DATE DESC";
			LogManager.info("MtnDao.getJwtToken() Query: "+query);
			List<Map<String, Object>> list = this.mytemplate.queryForList(query);
			if(list!=null && list.size()>0){
				Map<String, Object> map = list.get(0);
				if(map!=null && map.size()>0){
					token = map.get("JWT_TOKEN")==null?"":map.get("JWT_TOKEN").toString();
				}
			}
		}catch(Exception e){
			LogManager.info("Exception @ MtnDao.getJwtToken(): "+e);
			e.printStackTrace();
		}
		return token;
	}

	public void savePaymentDetail(String externalId, String referenceNo,
			String paymentType, String amount, String currency, String payerMessage,
			String payeeNote, String status, String reason, String payerId,
			String payerIdType, String productId, String merchantReferenceNo, String financeTranId) {
		try{
			String query = "INSERT INTO MTN_PAYMENT_STATUS(QUOTE_NO,REFERENCE_NO,PAYMENT_TYPE,PREMIUM,CURRENCY_TYPE,PAYER_MSG," +
					" PAYEE_NOTE,STATUS,REASON,PARTYID,PARTYID_TYPE,PRODUCT_ID,RESPONSE_TIME,MERCHANT_REFERENCE,FINANCE_TRAN_ID)" +
					" VALUES(?,?,?,?,?,?,?,?,?,?,?,?,SYSDATE,?,?)";
			Object[] args = new Object[]{externalId,referenceNo,paymentType,amount,currency,payerMessage,
					payeeNote,status,reason,payerId,payerIdType,productId,merchantReferenceNo,financeTranId};
			removeNull(args);
			LogManager.info("MtnDao.savePaymentDetail() Query: "+query+" Arguments: '"+StringUtils.join(args,"','")+"'");
			this.mytemplate.update(query, args);
		}catch(Exception e){
			LogManager.info("Exception @ MtnDao.savePaymentDetail(): "+e);
			e.printStackTrace();
		}
	}

	public Map<String, Object> getPaymentDetails(String merchantReferenceNo) {
		Map<String, Object> map = new HashMap<String, Object>();
		try{
			String query = "SELECT QUOTE_NO, PRODUCT_ID, PREMIUM, MERCHANT_REFERENCE, CURRENCY_TYPE, REFERENCE_NO, MOBILE_NO,"
					+ " MTN_MOBILE_NO FROM PAYMENT_DETAIL WHERE MERCHANT_REFERENCE = ?";
			Object[] args = new Object[]{merchantReferenceNo};
			LogManager.info("MtnDao.getPaymentDetails() Query: "+query+" Arguments: '"+StringUtils.join(args,"','")+"'");
			List<Map<String, Object>> list = this.mytemplate.queryForList(query, args);
			if(list!=null && list.size()>0){
				map = list.get(0);
			}
		}catch(Exception e){
			LogManager.info("Exception @ MtnDao.getPaymentDetails(): "+e);
			e.printStackTrace();
		}
		return map;
	}

	public Map<String, Object> getPaymentResult(String merchantReferenceNo) {
		Map<String, Object> map =  new HashMap<String, Object>();
		try{
			String query = "SELECT STATUS, REASON, REFERENCE_NO, FINANCE_TRAN_ID, RESPONSE_TIME"
					+ " FROM MTN_PAYMENT_STATUS WHERE MERCHANT_REFERENCE = ? ORDER BY RESPONSE_TIME DESC";
			Object[] args = new Object[]{merchantReferenceNo};
			LogManager.info("MtnDao.getPaymentDetails() Query: "+query+" Arguments: '"+StringUtils.join(args,"','")+"'");
			List<Map<String, Object>> list = this.mytemplate.queryForList(query, args);
			if(list!=null && list.size()>0){
				map = list.get(0);
			}
		}catch(Exception e){
			LogManager.info("Exception @ MtnDao.getPaymentResult(): "+e);
			e.printStackTrace();
		}
		return map;
	}

}
