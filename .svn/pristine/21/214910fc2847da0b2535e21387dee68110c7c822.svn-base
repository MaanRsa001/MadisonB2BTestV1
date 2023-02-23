package com.maan.webservice.rest.resource;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.maan.Motor.controller.MotorAction;
import com.maan.Motor.controller.MotorBean;

@Produces(MediaType.APPLICATION_JSON)
@Path("quote")
public class TravelResources {

	/*@POST
	@Path("/travel/")
	@Produces(MediaType.APPLICATION_JSON)
	public Travel getQuote(@FormParam("paramTravelBean") String paramTravelBean) {	
		Travel bean = new Gson().fromJson(paramTravelBean, Travel.class);
		List<CoverageBean> quote = new TravelAction().getQuote(bean);
		bean.setOptCoverList(quote);
		return bean;
	}
	@POST
	@Path("/motor/")
	@Produces(MediaType.APPLICATION_JSON)
	public MotorBean getMotorQuote(@FormParam("paramMotorBean") String paramMotorBean) {
		Type typeOf = new TypeToken<String[]>(){}.getType();
		String[] args= new Gson().fromJson(paramMotorBean, typeOf);
		MotorBean bean = new Gson().fromJson(paramMotorBean, MotorBean.class);
		bean= new MotorAction().getQuote(bean);
		//bean.setOptCoverList(quote);
		//bean.setQuote(quote);
		//System.out.println(">>>"+quote);
		//bean.setResult(quote);
		return bean;
	}
	@POST
	@Path("/motor/make/")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Object> getMotorMake(@FormParam("paramMotorBean") String paramMotorBean) {		
		List<Object> list=null;
		try{
			Type typeOf = new TypeToken<Map<String,Object>>(){}.getType();
			Map<String,Object> map= new Gson().fromJson(paramMotorBean, typeOf);
			String object = (String)map.get("type");
			if("mfrYear".equals(object)){
				list = new MotorAction().getMfgYrMapRest();	
			}else if("claimbonus".equals(object)){
				list = new MotorAction().getNoClaimBonusListRest();	
			}else{
				Object[] args=((List<String>)map.get("params")).toArray();
				list = new MotorAction().getDropdownListRest(object,args);
			}
						
            
		}catch (Exception e) {
			e.printStackTrace();
			list=new ArrayList<Object>();		
		}		
		return list;
	}
	
	@POST
	@Path("/motor/insertOptionCover")
	@Produces(MediaType.APPLICATION_JSON)
	public  MotorBean insertOptionCover(@FormParam("paramMotorBean") String paramMotorBean) {	 
		System.out.println("paramerters :::"+paramMotorBean);
		MotorBean bean = new Gson().fromJson(paramMotorBean, MotorBean.class);
		new MotorAction().insertOptionCover(bean);	 
		return bean;
	}
	
	@POST
	@Path("/motor/getPolicy")
	@Produces(MediaType.APPLICATION_JSON)
	public  List<Object> getMotorgetPolicy(@FormParam("paramMotorBean") String paramMotorBean) {	 
		System.out.println("paramerters :::"+paramMotorBean);
		MotorBean bean = new Gson().fromJson(paramMotorBean, MotorBean.class);
		new MotorAction().getGeratePolicy(bean);	 
		List<Object> list=new MotorAction().getPolicyInformation(bean);
		return list;
	}*/
}