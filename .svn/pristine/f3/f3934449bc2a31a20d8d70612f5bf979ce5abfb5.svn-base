package com.maan.notification.fcm;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.maan.common.LogManager;
import com.maan.common.MyJdbcTemplate;

public class NotificationDao extends MyJdbcTemplate{

	public boolean isTokenAvail(String loginId, String userType, String token) {
		try{
			String query = "SELECT * FROM BROWSER_NOTIFICATION_TOKEN WHERE LOGIN_ID = ? AND USER_TYPE = ?"
					+ " AND TOKEN  = ?";
			//"AND SYSDATE BETWEEN ENTRY_DATE AND EXPIRY_DATE";
			Object[] args = new Object[]{loginId, userType, token};
			LogManager.info("NotificationDao.isTokenAvail() Query: "+query+" Arguments: '"+StringUtils.join(args,"','")+"'");
			@SuppressWarnings("unchecked")
			List<Map<String, Object>> list = this.mytemplate.queryForList(query, args);
			if(list !=null && list.size()>0){
				return true;
			}
		}catch(Exception e){
			LogManager.info("Exception @ NotificationDao.isTokenAvail(): "+e);
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean saveTokenValues(String loginId, String userType, String token){
		try{
			String query = "INSERT INTO BROWSER_NOTIFICATION_TOKEN (LOGIN_ID, USER_TYPE, TOKEN, ENTRY_DATE, EXPIRY_DATE)"
					+ " VALUES (?,?,?,SYSDATE, SYSDATE + INTERVAL '1' HOUR)";
			Object[] args = new Object[]{loginId, userType, token};
			LogManager.info("NotificationDao.saveTokenValues() Query: "+query+" Arguments: '"+StringUtils.join(args,"','")+"'");
			int count = this.mytemplate.update(query, args);
			if(count>0){
				return true;
			}
		}catch(Exception e){
			LogManager.info("Exception @ NotificationDao.saveTokenValues(): "+e);
			e.printStackTrace();
		}
		return false;
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> commonList(String key, String userType){
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		try{
			String query = "";
			Object[] args = null;
			if("AdminList".equalsIgnoreCase(key)){
				query = "SELECT LOGIN_ID, USERTYPE USER_TYPE, USERNAME USER_NAME FROM LOGIN_MASTER"
					+ " WHERE UPPER(USERTYPE) = UPPER(?) AND STATUS = 'Y'";
				args = new Object[]{userType};
			}
			LogManager.info("NotificationDao.commonList() "+key+" Query: "+query+" Arguments: '"+StringUtils.join(args,"','")+"'");
			list = this.mytemplate.queryForList(query, args);
		}catch(Exception e){
			LogManager.info("Exception @ NotificationDao.commonList(): "+e);
			e.printStackTrace();
		}
		return list;
	}

	public String lastLoginToken(String loginIdToSend) {
		String token = "";
		try{
			String query = "SELECT TOKEN FROM BROWSER_NOTIFICATION_TOKEN A WHERE LOGIN_ID = ?"
					+ " AND ENTRY_DATE = (SELECT MAX(ENTRY_DATE) FROM BROWSER_NOTIFICATION_TOKEN B"
					+ " WHERE A.LOGIN_ID = B.LOGIN_ID)";
			Object[] args = new Object[]{loginIdToSend};
			LogManager.info("NotificationDao.lastLoginToken() Query: "+query+" Arguments: '"+StringUtils.join(args,"','")+"'");
			token = String.valueOf(this.mytemplate.queryForObject(query, args, String.class));
		}catch(Exception e){
			LogManager.info("Exception @ NotificationDao.lastLoginToken(): "+e);
		}
		return token;
	}

	public boolean saveNotificationMsg(String loginId, String userType, String loginIdToSend, String userTypeToSend,
			String quoteNo, String policyNo, String token, String notificationTitle, String notificationBody, String multicast_id,
			String success, String failure, String canonical_ids, String messageId, String error) {
		try{
			String query = "INSERT INTO NOTIFICATION_DETAILS(FROM_LOGIN_ID,FROM_USERTYPE,TO_LOGIN_ID,TO_USERTYPE,QUOTE_NO,"
					+ "POLICY_NO,TOKEN,TITLE,BODY,MULTICAST_ID,SUCCESS,FAILURE,CANONICAL_IDS,MESSAGE_ID,ERROR,ENTRY_DATE)"
					+ " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,SYSDATE)";
			Object[] args = new Object[]{loginId,userType, loginIdToSend, userTypeToSend,quoteNo, policyNo, token,
					notificationTitle, notificationBody, multicast_id, success, failure, canonical_ids, messageId, error};
			removeNull(args);
			LogManager.info("NotificationDao.lastLoginToken() Query: "+query+" Arguments: '"+StringUtils.join(args,"','")+"'");
			int count = this.mytemplate.update(query, args);
			if(count>0){
				return true;
			}
		}catch(Exception e){
			LogManager.info("Exception @ NotificationDao.lastLoginToken(): "+e);
			e.printStackTrace();
		}
		return false;
	}

	public List<Map<String, Object>> notificationList(String loginId, String userType, String token) {
		return null;
	}

}
