package com.maan.notification.fcm;

import java.util.Map;

import com.maan.common.LogManager;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class NotificationAction extends ActionSupport implements ModelDriven<NotificationBean>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	NotificationBean bean = new NotificationBean();
	Map<String, Object> session = ActionContext.getContext().getSession();
	String sessionLoginId = session.get("user")==null?"":session.get("user").toString();
	String sessionUserType = session.get("usertype")==null?"":session.get("usertype").toString();

	@Override
	public NotificationBean getModel() {
		return bean;
	}
	
	public String saveToken(){
		try{
			NotificationService service = new NotificationService(sessionLoginId, sessionUserType, bean.getToken());
			service.saveToken();
		}catch(Exception e){
			LogManager.info("Exception @ NotificationAction.saveToken(): "+e);
			e.printStackTrace();
		}
		return "jsonReturn";
	}

}
