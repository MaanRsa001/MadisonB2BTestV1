package com.maan.webservice.rest.resource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.gson.Gson;
import com.maan.Home.Controller.HomeAction;
import com.maan.Home.Controller.HomeBean;
import com.maan.Home.Service.HomeService;
import com.maan.common.LogManager;
import com.maan.common.LogUtil;

@Path("homeV1")
public class HomeResourceV1 {
	private final org.slf4j.Logger logger = LogUtil.getLogger(getClass());
	
	@POST
	@Path("/init")
	@Produces(MediaType.APPLICATION_JSON)
	public JSONObject init(@RequestParam HomeBean bean) {
		LogManager.info("homeV1 init - Enter");
		JSONObject json=null;
		try {
			HomeAction homeAction = new HomeAction();
			homeAction.mapRestBean(bean);
			//homeAction.init();
		    json=getJson(bean);
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("Exception @ { " + e + " } ");
		}
		LogManager.info("homeV1 init - Exit");
		return json;
	}
	
	@POST
	@Path("/getQuote")
	@Produces(MediaType.APPLICATION_JSON)
	public JSONObject getRender(@RequestParam HomeBean bean) {
		LogManager.info("homeV1 getRender - Enter");
		JSONObject json = null;
		try {
			HomeAction homeAction = new HomeAction();
			HomeService service = new HomeService();
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
			json=getJson(bean);
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("Exception @ { " + e + " } ");
		}
		LogManager.info("homeV1 getRender - Exit");
		return json;
	}
	
	@POST
	@Path("/uwMenu")
	@Produces(MediaType.APPLICATION_JSON)
	public JSONObject uwMenu(@RequestParam HomeBean bean) {
		LogManager.info("homeV1 uwMenu - Enter");
		JSONObject json = null;
		try {
			HomeAction homeAction = new HomeAction();
			homeAction.mapRestBean(bean);
			homeAction.uwMenu();
			json=getJson(bean);
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("Exception @ { " + e + " } ");
		}
		LogManager.info("homeV1 uwMenu - Exit");
		return json;
	}
	
	@POST
	@Path("/saveUWDetails")
	@Produces(MediaType.APPLICATION_JSON)
	public JSONObject saveUWDetails(@RequestParam HomeBean bean) {
		LogManager.info("homeV1 saveUWDetails - Enter");
		JSONObject json = null;
		try {
			HomeAction homeAction = new HomeAction();
			homeAction.mapRestBean(bean);
			homeAction.saveUWDetails();
			json=getJson(bean);
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("Exception @ { " + e + " } ");
		}	
		LogManager.info("homeV1 saveUWDetails - Exit");
		return json;
	}
	
	@POST
	@Path("/generatePolicy")
	@Produces(MediaType.APPLICATION_JSON)
	public JSONObject generatePolicy(@RequestParam HomeBean bean) {
		LogManager.info("homeV1 generatePolicy - Enter");
		JSONObject json = null;
		try {
			HomeAction homeAction = new HomeAction();
			homeAction.mapRestBean(bean);
			//homeAction.generatePolicy();
			json=getJson(bean);
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("Exception @ { " + e + " } ");
		}	
		LogManager.info("homeV1 generatePolicy - Exit");
		return json;
	}
	
	@POST
	@Path("/subListMenu")
	@Produces(MediaType.APPLICATION_JSON)
	public JSONObject subListMenu(@RequestParam HomeBean bean) {
		LogManager.info("homeV1 subListMenu - Enter");
		JSONObject json=null;
		try {
			HomeAction homeAction = new HomeAction();
			homeAction.mapRestBean(bean);
			homeAction.subListMenu();
			json=getJson(bean);
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("Exception @ { " + e + " } ");
		}	
		LogManager.info("homeV1 subListMenu - Exit");
		return json;
	}
	
	@POST
	@Path("/saveDetail")
	@Produces(MediaType.APPLICATION_JSON)
	public JSONObject saveDetail(@RequestParam HomeBean bean) {
		LogManager.info("homeV1 saveDetail - Enter");
		JSONObject json=null;
		try {
			HomeAction homeAction = new HomeAction();
			HomeService service = new HomeService();
			
			homeAction.mapRestBean(bean);
			homeAction.saveDetail();
			bean.setCoverageList(service.getCoverageList(bean));
			json=getJson(bean);
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("Exception @ { " + e + " } ");
		}	
		LogManager.info("homeV1 saveDetail - Exit");
		return json;
	}
		
	@POST
	@Path("/optionsList")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Object> getOptionsList(@RequestParam HomeBean bean) {
		LogManager.info("homeV1 getOptionsList - Enter");
		List<Object> list;
		try {
			HomeAction homeAction = new HomeAction();
			homeAction.mapRestBean(bean);
			list = homeAction.getDropdownListRest();
		} catch (Exception e) {
			list=new ArrayList<Object>();
			e.printStackTrace();
			logger.debug("Exception @ { " + e + " } ");
		}		
		LogManager.info("homeV1 getOptionsList - Exit");
		return list;
	}
	
	private final boolean hasErrorValues(List<String> actionErrors) {
		boolean result = false;
		if(actionErrors!=null && actionErrors.size()>0) {
			result = true;
		}
		return result;
	}
	
	public JSONObject getJson(HomeBean bean) {
		LogManager.info("Enter - getJson HomeBean--> ");
		JSONObject json=null;
		try {
			Gson gson = new Gson();
			String usersJson = gson.toJson(bean);
			JSONParser parser = new JSONParser();
			json = (JSONObject) parser.parse(usersJson);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		LogManager.info("Exit - getJson HomeBean--> ");
		return json;
	}
}
