package com.maan.webservice.rest.resource;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


import com.google.gson.Gson;
import com.maan.Home.Controller.HomeAction;
import com.maan.Home.Controller.HomeBean;
import com.maan.Home.Service.HomeService;
import com.maan.common.LogManager;
import com.maan.common.LogUtil;

@Path("home")
public class HomeResource {
	private final org.slf4j.Logger logger = LogUtil.getLogger(getClass());
	
	@POST
	@Path("/init")
	@Produces(MediaType.APPLICATION_JSON)
	public HomeBean init(@FormParam("paramHomeBean") String paramHomeBean) {
		LogManager.info("init - Enter --> "+paramHomeBean);
		HomeBean jsonBean = null;
		try {
			jsonBean = new Gson().fromJson(paramHomeBean, HomeBean.class);
			HomeAction homeAction = new HomeAction();
			homeAction.mapRestBean(jsonBean);
			//homeAction.init();
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("Exception @ { " + e + " } ");
		}		
		LogManager.info("init - Exit ");
		return jsonBean;
	}
	
	@POST
	@Path("/getQuote")
	@Produces(MediaType.APPLICATION_JSON)
	public HomeBean getRender(@FormParam("paramHomeBean") String paramHomeBean) {
		LogManager.info("getRender - Enter --> "+paramHomeBean);
		HomeBean bean = null;
		try {
			HomeAction homeAction = new HomeAction();
			HomeService service = new HomeService();
			bean = new Gson().fromJson(paramHomeBean, HomeBean.class);
			homeAction.mapRestBean(bean);
			//homeAction.getRender();
			if(!hasErrorValues(bean.getActionErrorsBean())) {
				Map<String,Object> detailsToCoverMap = new HashMap<String,Object>();
				for(Map<String,Object> premiumMap : bean.getPremiumList()) {
					if("Y".equalsIgnoreCase(String.valueOf(premiumMap.get("UPLOAD_OPTION")))) {
						Map<String,Object> tempMap = new HashMap<String,Object>();
						bean.setCoverId(String.valueOf(premiumMap.get("COVERAGES_ID")));
						bean.setSubCoverId("0");
						tempMap.put("COVERAGES_ID", bean.getCoverId());
						tempMap.put("SUB_COVERAGES_ID", bean.getSubCoverId());
						tempMap.put("TOTAL_SUM_INSURED", premiumMap.get("SUM_INSURED"));
						tempMap.put("DETAILS_LIST", service.getCoverageList(bean));
						detailsToCoverMap.put(bean.getCoverId(), tempMap);
					}
				}
				bean.setDetailsToCoverMap(detailsToCoverMap);
				bean.setCoverId("");
				bean.setSubCoverId("");
				
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("Exception @ { " + e + " } ");
		}	
		LogManager.info("getRender - Exit ");
		return bean;
	}
	
	@POST
	@Path("/uwMenu")
	@Produces(MediaType.APPLICATION_JSON)
	public HomeBean uwMenu(@FormParam("paramHomeBean") String paramHomeBean) {
		LogManager.info("uwMenu - Enter --> "+paramHomeBean);
		HomeBean jsonBean = null;
		try {
			jsonBean = new Gson().fromJson(paramHomeBean, HomeBean.class);
			HomeAction homeAction = new HomeAction();
			homeAction.mapRestBean(jsonBean);
			homeAction.uwMenu();
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("Exception @ { " + e + " } ");
		}		
		LogManager.info("uwMenu - Exit ");
		return jsonBean;
	}
	
	@POST
	@Path("/saveUWDetails")
	@Produces(MediaType.APPLICATION_JSON)
	public HomeBean saveUWDetails(@FormParam("paramHomeBean") String paramHomeBean) {
		LogManager.info("saveUWDetails - Enter --> "+paramHomeBean);
		HomeBean jsonBean = null;
		try {
			jsonBean = new Gson().fromJson(paramHomeBean, HomeBean.class);
			HomeAction homeAction = new HomeAction();
			homeAction.mapRestBean(jsonBean);
			homeAction.saveUWDetails();
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("Exception @ { " + e + " } ");
		}		
		LogManager.info("saveUWDetails - Exit ");
		return jsonBean;
	}
	
	@POST
	@Path("/generatePolicy")
	@Produces(MediaType.APPLICATION_JSON)
	public HomeBean generatePolicy(@FormParam("paramHomeBean") String paramHomeBean) {
		LogManager.info("generatePolicy - Enter --> "+paramHomeBean);
		HomeBean jsonBean = null;
		try {
			jsonBean = new Gson().fromJson(paramHomeBean, HomeBean.class);
			HomeAction homeAction = new HomeAction();
			homeAction.mapRestBean(jsonBean);
			//homeAction.generatePolicy();
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("Exception @ { " + e + " } ");
		}	
		LogManager.info("generatePolicy - Exit ");
		return jsonBean;
	}
	
	@POST
	@Path("/subListMenu")
	@Produces(MediaType.APPLICATION_JSON)
	public HomeBean subListMenu(@FormParam("paramHomeBean") String paramHomeBean) {
		LogManager.info("subListMenu - Enter --> "+paramHomeBean);
		HomeBean jsonBean = null;
		try {
			jsonBean = new Gson().fromJson(paramHomeBean, HomeBean.class);
			HomeAction homeAction = new HomeAction();
			homeAction.mapRestBean(jsonBean);
			homeAction.subListMenu();
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("Exception @ { " + e + " } ");
		}	
		LogManager.info("subListMenu - Exit ");
		return jsonBean;
	}
	
	@POST
	@Path("/saveDetail")
	@Produces(MediaType.APPLICATION_JSON)
	public HomeBean saveDetail(@FormParam("paramHomeBean") String paramHomeBean) {
		LogManager.info("saveDetail - Enter --> "+paramHomeBean);
		HomeBean bean = null;
		try {
			bean = new Gson().fromJson(paramHomeBean, HomeBean.class);
			HomeAction homeAction = new HomeAction();
			HomeService service = new HomeService();
			
			homeAction.mapRestBean(bean);
			homeAction.saveDetail();
			bean.setCoverageList(service.getCoverageList(bean));
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("Exception @ { " + e + " } ");
		}		
		LogManager.info("saveDetail - Exit ");
		return bean;
	}
		
	@POST
	@Path("/optionsList")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Object> getOptionsList(@FormParam("paramHomeBean") String paramHomeBean) {
		LogManager.info("getOptionsList - Enter --> "+paramHomeBean);
		List<Object> list;
		try {
			HomeBean jsonBean = new Gson().fromJson(paramHomeBean, HomeBean.class);
			HomeAction homeAction = new HomeAction();
			homeAction.mapRestBean(jsonBean);
			list = homeAction.getDropdownListRest();
		} catch (Exception e) {
			list=new ArrayList<Object>();
			e.printStackTrace();
			logger.debug("Exception @ { " + e + " } ");
		}		
		LogManager.info("getOptionsList - Exit ");
		return list;
	}
	
	private final boolean hasErrorValues(List<String> actionErrors) {
		boolean result = false;
		if(actionErrors!=null && actionErrors.size()>0) {
			result = true;
		}
		return result;
	}
}