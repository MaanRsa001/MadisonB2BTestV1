package com.maan.adminnew.smsEmail;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;

import com.maan.common.LogManager;
import com.maan.common.LogUtil;
import com.maan.common.MyJdbcTemplate;

public class SmsEmailMasterDAO extends MyJdbcTemplate
{
	private String query="";

	public List<Object> getSmsEmailTempList() {
		List<Object> result=null;
		try{
			LogManager.info("Enter into the @getSmsEmailTempList");
			query= getQuery("GET_SMS_EMAIL_TEMPLATE_LIST");
			LogManager.info("Query => "+query);
			result = this.mytemplate.queryForList(query);
			LogManager.info("Exit from the @getSmsEmailTempList");
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

	public void insertEmaiSmsMaster(String mode, Object[] args) {
		try{
			LogManager.info("Enter into the @insertEmaiSmsMaster");
			if("add".equalsIgnoreCase(mode))
				query=getQuery("INSERT_SMS_EMAIL_TEMPLATE");
			else if("edit".equalsIgnoreCase(mode))
				query=getQuery("UPDATE_SMS_EMAIL_TEMPLATE");
			removeNull(args);
			LogManager.info("Query => "+query);
			LogManager.info("Arguments => "+StringUtils.join(args,","));
			this.mytemplate.update(query,args);
			LogManager.info("Exit from the @insertEmaiSmsMaster");
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void getEditSmsEmailMaster(SmsEmailMasterBean bean){
		try{
			LogManager.info("Enter into the @getEditSmsEmailMaster");
			query = getQuery("EDIT_SMS_EMAIL_TEMPLATE");
			Object args[]={bean.getTempId()};
			LogManager.info("Query => "+query);
			LogManager.info("Arguments => "+StringUtils.join(args,","));
			Map<String,Object> result = this.mytemplate.queryForMap(query,args);
			if(result.size()>0){
				bean.setSmsRequired(result.get("SMS_REQUIRED")==null?"":result.get("SMS_REQUIRED").toString());
				bean.setSmsSubject(result.get("SMS_SUBJECT")==null?"":result.get("SMS_SUBJECT").toString());
				bean.setSmsBody(result.get("SMS_BODY")==null?"":result.get("SMS_BODY").toString());
				bean.setSmsRegards(result.get("SMS_REGARDS")==null?"":result.get("SMS_REGARDS").toString());
				bean.setEmailRequired(result.get("MAIL_REQUIRED")==null?"":result.get("MAIL_REQUIRED").toString());
				bean.setEmailSubject(result.get("MAIL_SUBJECT")==null?"":result.get("MAIL_SUBJECT").toString());
				bean.setEmailBody(result.get("MAIL_BODY")==null?"":result.get("MAIL_BODY").toString());
				bean.setEmailRegards(result.get("MAIL_REGARDS")==null?"":result.get("MAIL_REGARDS").toString());
				bean.setEmailTo(result.get("EMAIL_TO")==null?"":result.get("EMAIL_TO").toString());
				bean.setEmailCc(result.get("EMAIL_CC")==null?"":result.get("EMAIL_CC").toString());
				bean.setSmsTo(result.get("SMS_TO")==null?"":result.get("SMS_TO").toString());
				bean.setStatus(result.get("STATUS")==null?"":result.get("STATUS").toString());
				bean.setRemarks(result.get("REMARKS")==null?"":result.get("REMARKS").toString());
				bean.setUserType(result.get("USER_TYPE")==null?"":result.get("USER_TYPE").toString());
				if(StringUtils.isNotBlank(bean.getUserType())){
				bean.setUserTypeArray(result.get("USER_TYPE").toString().trim().replaceAll(" ", "").split(","));
				}
				LogManager.info("Exit from the @getEditSmsEmailMaster");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
}
