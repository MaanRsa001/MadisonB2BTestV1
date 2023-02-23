package com.maan.webservice.rest.resource;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.maan.common.LogManager;
import com.maan.common.home.HomeService;
import com.maan.userMgt.UserMgtAction;
import com.maan.userMgt.UserMgtBean;

@Path("userMgt")
public class UserMgtResource {
	
	@POST
	@Path("/userLogin")
	@Produces(MediaType.APPLICATION_JSON)
	public UserMgtBean userLogin(@FormParam("paramUserBean") String paramUserBean) {
		LogManager.info("Enter - userLogin --> "+paramUserBean);
		UserMgtBean jsonBean = null;
		try {
			jsonBean = new Gson().fromJson(paramUserBean, UserMgtBean.class);
			UserMgtAction userMgtAction = new UserMgtAction();
			userMgtAction.mapRestBean(jsonBean);
			userMgtAction.userLogin();
			
			if(jsonBean.getActionErrorsBean().size()==0) {
				HomeService service = new HomeService();
				jsonBean.setProductDetailsList(service.getProductDetails(jsonBean.getUserLoginId(), jsonBean.getUserType(), "01"));
				jsonBean.setOfsSchemeDetailsList(service.getOfficeProductScheme(jsonBean.getUserLoginId(), "01"));
			}
			
		} catch(Exception exception) {
			LogManager.debug(exception);
		}
		LogManager.info("Exit - userLogin  ");
		return jsonBean;
	}
	
	
	@POST
	@Path("/UserRegister")
	@Produces(MediaType.APPLICATION_JSON)
	public UserMgtBean userRegister(@FormParam("paramUserBean") String paramUserMgtBean) {
		LogManager.info("Enter - UserRegister --> "+paramUserMgtBean);
		UserMgtBean jsonBean = new Gson().fromJson(paramUserMgtBean, UserMgtBean.class);
		UserMgtAction userAction = new UserMgtAction();
		userAction.mapRestBean(jsonBean);
		Map<String,String> result = new HashMap<String,String>();
		try{
			userAction.addMobUser();
		}catch (Exception e) {
			e.printStackTrace();
		}
		LogManager.info("Exit - UserRegister ");
	 return jsonBean;
	}
	@POST
	@Path("/ForgotPwd")
	@Produces(MediaType.APPLICATION_JSON)
	public UserMgtBean forgotPwd(@FormParam("paramUserBean") String paramUserMgtBean) {
		LogManager.info("Enter - ForgotPwd  --> "+paramUserMgtBean);
		UserMgtBean jsonBean = new Gson().fromJson(paramUserMgtBean, UserMgtBean.class);
		UserMgtAction userAction = new UserMgtAction();
		userAction.mapRestBean(jsonBean);
		Map<String,String> result = new HashMap<String,String>();
		try{
			userAction.forgotPwd();
		}catch (Exception e) {
			e.printStackTrace();
		}
		LogManager.info("Exit - ForgotPwd ");
	 return jsonBean;
	}
	@POST
	@Path("/ChangePwd")
	@Produces(MediaType.APPLICATION_JSON)
	public UserMgtBean changePwd(@FormParam("paramUserBean") String paramUserMgtBean) {
		LogManager.info("Enter - ChangePwd --> "+paramUserMgtBean);
		UserMgtBean jsonBean = new Gson().fromJson(paramUserMgtBean, UserMgtBean.class);
		UserMgtAction userAction = new UserMgtAction();
		userAction.mapRestBean(jsonBean);
		Map<String,String> result = new HashMap<String,String>();
		try{
			userAction.pwdChange();
		}catch (Exception e) {
			e.printStackTrace();
		}
		LogManager.info("Exit - ChangePwd ");
	 return jsonBean;
	}
	
}
