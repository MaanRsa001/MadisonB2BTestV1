package com.maan.adminnew.smsEmail;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class SmsEmailMasterAction extends ActionSupport implements ModelDriven<SmsEmailMasterBean>
{
	//LCCreation/LCCreationBrokerList.jsp
	private static final long serialVersionUID = 1236479L;
	final SmsEmailMasterService service=new SmsEmailMasterService();
	final SmsEmailMasterBean bean=new SmsEmailMasterBean();
	Map<String, Object> session=ActionContext.getContext().getSession();
	String branchCode=(String)session.get("BranchCode");
	String belongingBranch=(String)session.get("BelongingBranch");
	String login_id=(String)session.get("user");
	String appId=(String)session.get("AppId");
	private String userType=(String) session.get("user1") ;
	
	public SmsEmailMasterBean getModel() {
		return bean;
	}
	
	public List<Object> getSmsEmailTempList(){
		return service.getSmsEmailTempList();
	}
	public String view(){
		bean.setMode("list");
		return INPUT;
	}
	public String edit(){
		if("edit".equalsIgnoreCase(bean.getMode()))
			service.getEditSmsEmailMaster(bean);
		return INPUT;
	}
	public String insert(){
		String doc = "";
		if(bean.getUserTypeArray() != null && bean.getUserTypeArray().length>0){
			for(String str: bean.getUserTypeArray())
				doc = doc+","+str; 
			doc = doc.substring(1);
		}
		bean.setUserType(doc);
		validateSmsEmail();
		if(!hasActionErrors()){
			Object args[]=null;
			if("add".equalsIgnoreCase(bean.getMode())){
				args=new Object[]{bean.getEmailRequired(),bean.getEmailSubject(),bean.getEmailBody(),bean.getEmailRegards(),bean.getSmsRequired(),bean.getSmsSubject(),bean.getSmsBody(),bean.getSmsRegards(),bean.getStatus(),bean.getRemarks(),bean.getEmailTo(),bean.getEmailCc(),bean.getSmsTo(),bean.getUserType()};
			}else if("edit".equalsIgnoreCase(bean.getMode())){
				args=new Object[]{bean.getEmailRequired(),bean.getEmailSubject(),bean.getEmailBody(),bean.getEmailRegards(),bean.getSmsRequired(),bean.getSmsSubject(),bean.getSmsBody(),bean.getSmsRegards(),bean.getStatus(),bean.getRemarks(),bean.getEmailTo(),bean.getEmailCc(),bean.getSmsTo(),bean.getUserType(),bean.getTempId()};
			}
			service.insertEmaiSmsMaster(bean.getMode(),args);
			bean.setMode("list");
		}
		return INPUT;
	}

	private void validateSmsEmail() {
		if(StringUtils.isBlank(bean.getStatus()))
			addActionError(getText("error.enter.status"));
		if(StringUtils.isBlank(bean.getSmsRequired()))
			addActionError(getText("error.choose.sms.required"));
		if(StringUtils.isBlank(bean.getEmailRequired()))
			addActionError(getText("error.choose.email.required"));
		if(StringUtils.isBlank(bean.getSmsRequired()) && StringUtils.isBlank(bean.getEmailRequired()))
			addActionError(getText("error.choose.sms.required.or.email.required"));
		else {
			if("Y".equalsIgnoreCase(bean.getSmsRequired())){
				if(StringUtils.isBlank(bean.getSmsSubject()))
					addActionError(getText("error.enter.sms.subject"));
				if(StringUtils.isBlank(bean.getSmsBody()))
					addActionError(getText("error.enter.sms.body"));
				if(StringUtils.isBlank(bean.getSmsRegards()))
					addActionError(getText("error.enter.sms.regards"));
			}if("Y".equalsIgnoreCase(bean.getEmailRequired())){
				if(StringUtils.isBlank(bean.getEmailSubject()))
					addActionError(getText("error.enter.email.subject"));
				if(StringUtils.isBlank(bean.getEmailBody()))
					addActionError(getText("error.enter.email.body"));
				if(StringUtils.isBlank(bean.getEmailRegards()))
					addActionError(getText("error.enter.email.regards"));
			}
		}
	}
	
	public String getHelpDetail(){
		return "helpdetailEmail";
	}
	
}
