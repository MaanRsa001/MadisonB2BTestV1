package com.maan.webservice.rest.resource;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.gson.Gson;
import com.maan.common.LogManager;
import com.maan.common.home.HomeService;
import com.maan.userMgt.UserMgtAction;
import com.maan.userMgt.UserMgtBean;

@Path("userMgtV1")
public class UserMgtResourceV1 {

	
	@POST
	@Path("/userLogin")
	@Produces(MediaType.APPLICATION_JSON)
	public JSONObject userLogin(@RequestParam UserMgtBean bean) {
		LogManager.info("Enter - userMgtV1 userLogin  ");
		JSONObject json = null;
		try {
			UserMgtAction userMgtAction = new UserMgtAction();
			userMgtAction.mapRestBean(bean);
			userMgtAction.userLogin();
			
			if(bean.getActionErrorsBean().size()==0) {
				HomeService service = new HomeService();
				bean.setProductDetailsList(service.getProductDetails(bean.getUserLoginId(), bean.getUserType(), "01"));
				bean.setOfsSchemeDetailsList(service.getOfficeProductScheme(bean.getUserLoginId(), "01"));
			}
			
			json=getJson(bean);
			
		} catch(Exception exception) {
			LogManager.debug(exception);
		}
		LogManager.info("Exit - userMgtV1 userLogin  ");
		return json;
	}
	
	
	@POST
	@Path("/UserRegister")
	@Produces(MediaType.APPLICATION_JSON)
	public JSONObject userRegister(@RequestParam UserMgtBean bean) {
		LogManager.info("Enter - userMgtV1 UserRegister --> ");
		//UserMgtBean jsonBean = new Gson().fromJson(paramUserMgtBean, UserMgtBean.class);
		UserMgtAction userAction = new UserMgtAction();
		userAction.mapRestBean(bean);
		//Map<String,String> result = new HashMap<String,String>();
		try{
			userAction.addMobUser();
		}catch (Exception e) {
			e.printStackTrace();
		}
		JSONObject json=getJson(bean);
		LogManager.info("Exit - userMgtV1 UserRegister ");
	 return json;
	}
	@POST
	@Path("/ForgotPwd")
	@Produces(MediaType.APPLICATION_JSON)
	public JSONObject forgotPwd(@RequestParam UserMgtBean bean) {
		LogManager.info("Enter - userMgtV1 ForgotPwd  --> ");
		//UserMgtBean jsonBean = new Gson().fromJson(paramUserMgtBean, UserMgtBean.class);
		UserMgtAction userAction = new UserMgtAction();
		userAction.mapRestBean(bean);
		//Map<String,String> result = new HashMap<String,String>();
		try{
			userAction.forgotPwd();
		}catch (Exception e) {
			e.printStackTrace();
		}
		JSONObject json=getJson(bean);
		LogManager.info("Exit - userMgtV1 ForgotPwd ");
	 return json;
	}
	@POST
	@Path("/ChangePwd")
	@Produces(MediaType.APPLICATION_JSON)
	public JSONObject changePwd(@RequestParam UserMgtBean bean) {
		LogManager.info("Enter - userMgtV1 ChangePwd --> ");
		//UserMgtBean jsonBean = new Gson().fromJson(paramUserMgtBean, UserMgtBean.class);
		UserMgtAction userAction = new UserMgtAction();
		userAction.mapRestBean(bean);
		//Map<String,String> result = new HashMap<String,String>();
		try{
			userAction.pwdChange();
		}catch (Exception e) {
			e.printStackTrace();
		}
		JSONObject json=getJson(bean);
		LogManager.info("Exit - userMgtV1 ChangePwd ");
	 return json;
	}
	
	public JSONObject getJson(UserMgtBean bean) {
		LogManager.info("Enter - getJson UserMgtBean--> ");
		JSONObject json=null;
		try {
			Gson gson = new Gson();
			String usersJson = gson.toJson(bean);
			JSONParser parser = new JSONParser();
			json = (JSONObject) parser.parse(usersJson);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		LogManager.info("Exit - getJson UserMgtBean--> ");
		return json;
	}


}
