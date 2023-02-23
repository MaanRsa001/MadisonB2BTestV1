package com.maan.payment.airtel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.maan.common.LogManager;
import com.maan.common.MyJdbcTemplate;

public class AirtelDao extends MyJdbcTemplate{
	public String getJwtToken(String type) {
		String token = "";
		try{
			String query = "SELECT JWT_TOKEN FROM JWT_TOKEN WHERE TOKEN_FOR = ?" +
					" AND SYSDATE BETWEEN ENTRY_DATE AND EXPIRY_DATE ORDER BY ENTRY_DATE DESC";
			LogManager.info("AirtelDao.getJwtToken() Query: "+query + " Args =>"+type);
			List<Map<String, Object>> list = this.mytemplate.queryForList(query,new Object[]{type});
			if(list!=null && list.size()>0){
				Map<String, Object> map = list.get(0);
				if(map!=null && map.size()>0){
					token = map.get("JWT_TOKEN")==null?"":map.get("JWT_TOKEN").toString();
				}
			}
		}catch(Exception e){
			LogManager.info("Exception @ AirtelDao.getJwtToken(): "+e);
			e.printStackTrace();
		}
		return token;
	}

	public Map<String, Object> getPaymentDetails(String merchantReferenceNo) {
		Map<String, Object> map = new HashMap<String, Object>();
		try{
			String query = "SELECT QUOTE_NO, PRODUCT_ID, PREMIUM, MERCHANT_REFERENCE, CURRENCY_TYPE, REFERENCE_NO, MOBILE_NO,"
					+ " MTN_MOBILE_NO FROM PAYMENT_DETAIL WHERE MERCHANT_REFERENCE = ?";
			Object[] args = new Object[]{merchantReferenceNo};
			LogManager.info("AirtelDao.getPaymentDetails() Query: "+query+" Arguments: '"+StringUtils.join(args,"','")+"'");
			List<Map<String, Object>> list = this.mytemplate.queryForList(query, args);
			if(list!=null && list.size()>0){
				map = list.get(0);
			}
		}catch(Exception e){
			LogManager.info("Exception @ AirtelDao.getPaymentDetails(): "+e);
			e.printStackTrace();
		}
		return map;
	}

	public void savePaymentDetail(String quoteNo, String productId, String paymentType, String merchantReferenceNo, String reference,
			String country, String currency, int msisdn, String amount, String countryTxn, String currencyTxn,
			String id, String txnId, String airtelmoneyid, String status, String txnmessage, String statmessage, String code,
			String resultcode, boolean success) {
		try{
			String query = "INSERT INTO AIRTEL_PAYMENT_STATUS (QUOTE_NO, PRODUCT_ID, PAYMENT_TYPE, RESPONSE_TIME, "
					+ "MERCHANT_REFERENCE, REFERENCE_NAME, SUB_COUNTRY, SUB_CURRENCY, SUB_MSISDN, TXNIP_AMOUNT, "
					+ "TXNIP_COUNTRY, TXNIP_CURRENCY, TXNIP_ID, TXNOP_ID, TXNOP_AIRTELMONEYID, TXNOP_STATUS, TXNOP_MESSAGE, "
					+ "STATUS_MESSAGE, STATUS_CODE, STATUS_RESULT_CODE, STATUS_SUCCESS)"
					+ "VALUES (?,?,?,SYSDATE,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			Object[] args = new Object[]{quoteNo,productId,paymentType,merchantReferenceNo,reference,country,currency,
					msisdn,amount,countryTxn,currencyTxn,id,txnId,airtelmoneyid,status,txnmessage,statmessage,code,resultcode,success};
			removeNull(args);
			LogManager.info("AirtelDao.savePaymentDetail() Query: "+query+" Arguments: '"+StringUtils.join(args,"','")+"'");
			this.mytemplate.update(query, args);
		}catch(Exception e){
			LogManager.info("Exception @ AirtelDao.savePaymentDetail(): "+e);
			e.printStackTrace();
		}
	}

	public Map<String, Object> getPaymentResult(String merchantReferenceNo) {
		Map<String, Object> map =  new HashMap<String, Object>();
		try{
			String query = "SELECT TXNOP_STATUS, TXNOP_AIRTELMONEYID, TXNOP_STATUS, TXNOP_MESSAGE, RESPONSE_TIME"
					+ " FROM AIRTEL_PAYMENT_STATUS WHERE MERCHANT_REFERENCE = ? ORDER BY RESPONSE_TIME DESC";
			Object[] args = new Object[]{merchantReferenceNo};
			LogManager.info("AirtelDao.getPaymentDetails() Query: "+query+" Arguments: '"+StringUtils.join(args,"','")+"'");
			List<Map<String, Object>> list = this.mytemplate.queryForList(query, args);
			if(list!=null && list.size()>0){
				map = list.get(0);
			}
		}catch(Exception e){
			LogManager.info("Exception @ AirtelDao.getPaymentResult(): "+e);
			e.printStackTrace();
		}
		return map;
	}
}
