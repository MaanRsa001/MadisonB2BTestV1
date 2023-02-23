package com.maan.webservice.rest.resource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.SystemUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.gson.Gson;
import com.maan.Motor.Services.MotorService;
import com.maan.Motor.controller.MotorAction;
import com.maan.Motor.controller.MotorBean;
import com.maan.adminnew.report.motor.MotorReportBean;
import com.maan.adminnew.report.motor.MotorReportService;
import com.maan.common.LogManager;
import com.maan.payment.PaymentBean;
import com.maan.payment.PaymentService;
import com.maan.quickRenewal.quickRenewalAction;
import com.maan.quickRenewal.bean.quickRenewalBean;
import com.maan.webservice.service.ManipulateImage;

@SuppressWarnings("unused")
@Path("motorV1")
public class MotorResourceV1 {

	@POST
	@Path("/editQuote")
	@Produces(MediaType.APPLICATION_JSON)
	public JSONObject editQuote(@RequestParam MotorBean bean) {
		LogManager.info("motorV1 editQuote - Enter");
		JSONObject json = null;
		try {
			setConstantValues(bean);
			MotorService service = new MotorService();
			service.editQuote(bean,hasActionErrors(bean.getActionErrorsBean()));
			if(StringUtils.isNotBlank(bean.getApplicationNo())) {
				bean.setVehicleDetailsList(service.getMultiVehicleDetails(bean.getApplicationNo(), bean.getProductId(), bean.getBranchCode()));
			}
			json = getJson(bean);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		LogManager.info("motorV1 editQuote - Exit");
		return json;
	}
	
	@POST
	@Path("/getQuote")
	@Produces(MediaType.APPLICATION_JSON)
	public JSONObject getQuote(@RequestParam MotorBean bean) {
		LogManager.info("Enter - motorV1 getQuote --> ");
		JSONObject json = null;
		try {
			MotorAction motorAction = new MotorAction();
			motorAction.mapRestBean(bean);
			motorAction.getQuote();
			bean.setReferralMsg(motorAction.getReferralMsgs());
			if(!hasActionErrors(bean.getActionErrorsBean())) {
				MotorService service = new MotorService();
				bean.setVehicleDetailsList(service.getMultiVehicleDetails(bean.getApplicationNo(), bean.getProductId(), bean.getBranchCode()));
			}
			json = getJson(bean);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		LogManager.info("Exit - motorV1 getQuote "+bean);
		return json;
	}
	
	@POST
	@Path("/insertOptionCover")
	@Produces(MediaType.APPLICATION_JSON)
	public JSONObject insertOptionCover(@RequestParam MotorBean bean) {
		LogManager.info("Enter - motorV1 insertOptionCover --> ");
		JSONObject json = null;
		try {
			MotorAction motorAction = new MotorAction();
			motorAction.mapRestBean(bean);
			motorAction.insertOptionCover();
			if(!hasActionErrors(bean.getActionErrorsBean())) {
				MotorService service = new MotorService();
				bean.setVehicleDetailsList(service.getMultiVehicleDetails(bean.getApplicationNo(), bean.getProductId(), bean.getBranchCode()));
			}
			json = getJson(bean);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		LogManager.info("Exit - motorV1 insertOptionCover ");
		return json;
	}
	
	@POST
	@Path("/getPolicy")
	@Produces(MediaType.APPLICATION_JSON)
	public  JSONObject getGeratePolicy(@RequestParam MotorBean bean) {
		LogManager.info("Enter - motorV1 getPolicy --> ");
		JSONObject json = null;
		try {
			MotorAction motorAction=new MotorAction();
			motorAction.mapRestBean(bean);
			motorAction.getGeratePolicy();
			bean.setPolicyInformationList(motorAction.getPolicyInformation());
			json = getJson(bean);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		LogManager.info("Exit - motorV1 getPolicy ");
		return json;
	}
	
	@POST
	@Path("/optionsList")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Object> getOptionsList(@RequestParam MotorBean bean) {
		LogManager.info("Enter - motorV1 getOptionsList() --> ");
		List<Object> list=null;
		try{
			MotorAction motorAction = new MotorAction();
			motorAction.mapRestBean(bean);
			list = motorAction.getDropdownListRest(); 
			LogManager.info("Exit - motorV1 optionsList - List --->" +list);
		}catch (Exception exception) {
			list=new ArrayList<Object>();
			LogManager.debug(exception);
		}		
		return list;
	}
	
	private boolean hasActionErrors(List<String> actionErrorsList) {
		return actionErrorsList!=null && actionErrorsList.size()>0?true:false;
	}
	
	private void setConstantValues(MotorBean bean) {
		bean.setBranchCode("01");
		bean.setActualBranch("01");
		bean.setDestCountry("1");
		bean.setOriginCountry("1");
		bean.setProductId("65");
		bean.setActionErrorsBean(new ArrayList<String>());
	}
	
	@POST
	@Path("/ConditionClauses")
	@Produces(MediaType.APPLICATION_JSON)
	public JSONObject getConditionClausesList(@RequestParam MotorBean bean) {
		LogManager.info("Enter - motorV1 ConditionClauses --> ");
		JSONObject json = null;
		try {
			MotorAction motorAction = new MotorAction();
			motorAction.mapRestBean(bean);
			bean.setTransList(motorAction.getConditionClausesList());
			json = getJson(bean);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		LogManager.info("Exit - motorV1 ConditionClauses ");
		return json;
	}
	
	/*@POST
	@Path("/uploadimgdetail")
	@Produces(MediaType.APPLICATION_JSON)
	public String uploadimgdetail1(@RequestParam MotorBean bean) {
		String refNo="";
		LogManager.info("uploadimgdetail - Enter --> ");
		MotorService service = new MotorService();
		service.insUploadImgDetail(bean);
		if(StringUtils.isEmpty(bean.getRefNo())){
			refNo="Error the Image Upload";
		}else{
			refNo = bean.getRefNo();
		}
		LogManager.info("uploadimgdetail - Exit");
		return refNo;
	}*/
	
	@POST
	@Path("/uploadimgdetail")
	@Produces(MediaType.APPLICATION_JSON)
	public JSONObject uploadimgdetail(@RequestParam MotorBean bean) {
		String refNo="";
		String error="";
		LogManager.info("motorV1 uploadimgdetail - Enter --> ");
		MotorService service = new MotorService();
		service.insUploadImgDetail(bean);
		String usersJson = "";
		if(StringUtils.isEmpty(bean.getRefNo())){
			error="Error in the Image Upload";
		}else{
			refNo = bean.getRefNo();
		}
		usersJson = "{\"Error\":\""+error+"\",\"referenceNo\":\""+refNo+"\"}";
		Gson gson = new Gson();
		JSONParser parser = new JSONParser();
		JSONObject json = null;
		try {
			json = (JSONObject) parser.parse(usersJson);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		LogManager.info("motorV1 uploadimgdetail - Exit");
		
		return json;
	}
	
	@SuppressWarnings("unchecked")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/uploadimage")
	public Map<String,String> newMethod(Object inputValues){
		String result="";
		Map<String,String> map=null;
		LogManager.info("motorV1 uploadimgdocument - Enter  --> "+inputValues);
		try{
			map=(Map<String,String>)inputValues;
			MotorBean bean= new MotorBean();
			bean.setWebRootPath(getApplicationPath());
			bean.setEncodedImageStr(map.get("image"));
			//String imgEncodedStr = map.get("stars");
			bean.setFileName(map.get("filename"));
			bean.setRefNo(map.get("refernceNo"));
			bean.setDocType(map.get("type"));
			if(bean.getEncodedImageStr() != null){
				ManipulateImage.convertStringtoImage(bean);
				result="SUCCESS";	
			} else{				
				result=("FAILURE : Image is empty");
			}
					
		}catch (Exception e) {
			result=("FAILURE : "+e.getLocalizedMessage());
			e.printStackTrace();			
		}	
		LogManager.info("motorV1 uploadimgdocument - Exit");
		return map;
	}
	 
	
	private String getApplicationPath(){
			String[] resource = this.getClass().getResource("").toString().replace("file:/", "").split("WEB-INF/");
			if(SystemUtils.IS_OS_LINUX) {
				return "/"+resource[0].replaceAll("%20", " ");
			}else{
				return resource[0].replaceAll("%20", " ");
			}
		
	}
	
	@POST
	@Path("/getNotify")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Object> getNotifyList() {	
		LogManager.info("motorV1 getNotify - Enter");
		List<Object> list=null;
		try{
			MotorService service = new MotorService();
			list = service.getNotifyList();
		}catch (Exception exception) {
			list = new ArrayList<Object>();
			LogManager.debug(exception);
		}
		LogManager.info("motorV1 getNotify - Exit");
		return list;
	}
	
	@POST
	@Path("/getTypeAssistant")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Map<String,Object>> getTypeofAssistantList() {	
		LogManager.info("motorV1 getTypeAssistant - Enter");
		List<Map<String,Object>> list=null;
		try{
			MotorService service = new MotorService();
			list = service.getTypeofAssistantList();
		}catch (Exception exception) {
			list = new ArrayList<Map<String,Object>>();
			LogManager.debug(exception);
		}		
		LogManager.info("motorV1 getTypeAssistant - Exit");
		return list;
	}
	
	@POST
	@Path("/insRoadAssistant")
	@Produces(MediaType.APPLICATION_JSON)
	public JSONObject insRoadAssistant(@RequestParam MotorBean bean) {
		String refNo="";
		LogManager.info("motorV1 insRoadAssistant - Enter --> ");
		JSONObject json = null;
		try {
			MotorService service = new MotorService();
			MotorAction action = new MotorAction();
			action.mapRestBean(bean);
			action.insRoadAssistant();
			json = getJson(bean);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		LogManager.info("motorV1 insRoadAssistant - Exit");
		return json;
	}
	
	@POST
	@Path("/getRAStatus")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Map<String,Object>> getRAStatus(@RequestParam MotorReportBean bean) {
		String refNo="";
		LogManager.info("motorV1 getRAStatus - Enter --> ");
		List<Map<String,Object>> list=null;
		try{
			MotorReportService service = new MotorReportService();
			list = service.getRosdAssistReport(bean,"view");
		}catch (Exception exception) {
			list = new ArrayList<Map<String,Object>>();
			LogManager.debug(exception);
		}		
		LogManager.info("motorV1 getRAStatus - Exit");
		return list;
	}
	
	@POST
	@Path("/updRAFeedBack")
	@Produces(MediaType.APPLICATION_JSON)
	public JSONObject updRAFeedBack(@RequestParam MotorBean bean) {
		String refNo="";
		LogManager.info("motorV1 updRAFeedBack - Enter --> ");
		JSONObject json = null;
		try {
			MotorService service = new MotorService();
			MotorAction action = new MotorAction();
			action.mapRestBean(bean);
			action.updRAFeedBack();
			json = getJson(bean);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		LogManager.info("motorV1 updRAFeedBack - Exit");
		return json;
	}
	
	@POST
	@Path("/getInsDetail")
	@Produces(MediaType.APPLICATION_JSON)
	public JSONObject getInsDetail(@RequestParam PaymentBean bean) {
		LogManager.info("motorV1 getInsDetail - Enter --> ");
		JSONObject json = null;
		try {
			PaymentService service=new PaymentService();
			if("Y".equalsIgnoreCase(bean.getInstallmentYN())){
				service.installmentCalc(bean);
				bean.setInsList(service.getInstallmentDetailsList(bean.getQuoteNo()));
			}else{
				MotorAction action = new MotorAction();
				action.deleteinstallment();
			}
			json = getJson(bean);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		LogManager.info("motorV1 getInsDetail - Exit ");
		return json;
	}
	
	@POST
	@Path("/deleteVehicle")
	@Produces(MediaType.APPLICATION_JSON)
	public JSONObject deleteVehicle(@RequestParam MotorBean bean) {
		LogManager.info("motorV1 deleteVehicle - Enter ---> ");
		JSONObject json = null;
		try {
			MotorAction action = new MotorAction();
			action.mapRestBean(bean);
			action.deleteAddVehicle();
			json = getJson(bean);
		} catch (Exception e) {
			e.printStackTrace();
		}
		LogManager.info("motorV1 deleteVehicle - Exit ");
		return json;
	}
	
	/*@POST
	@Path("/renewal")
	@Produces(MediaType.APPLICATION_JSON)
	public JSONObject quickRenewal(@RequestParam quickRenewalBean bean) {
		JSONObject json = null;
		try{
			quickRenewalAction userAction = new quickRenewalAction();
			userAction.mapRestBean(bean);
			userAction.getRenewal();
			Map<String,String> result = new HashMap<String,String>();
			json = getJson(bean);
		}catch (Exception e) {
			e.printStackTrace();
		}
	 return json;
	}*/
	
	public JSONObject getJson(MotorBean bean) {
		LogManager.info("Enter - getJson MotorBean--> ");
		JSONObject json=null;
		try {
			Gson gson = new Gson();
			String usersJson = gson.toJson(bean);
			JSONParser parser = new JSONParser();
			json = (JSONObject) parser.parse(usersJson);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		LogManager.info("Exit - getJson MotorBean--> ");
		return json;
	}
	
	public JSONObject getJson(PaymentBean bean) {
		LogManager.info("Enter - getJson PaymentBean--> ");
		JSONObject json=null;
		try {
			Gson gson = new Gson();
			String usersJson = gson.toJson(bean);
			JSONParser parser = new JSONParser();
			json = (JSONObject) parser.parse(usersJson);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		LogManager.info("Exit - getJson PaymentBean--> ");
		return json;
	}
	
	public JSONObject getJson(quickRenewalBean bean) {
		LogManager.info("Enter - getJson quickRenewalBean--> ");
		JSONObject json=null;
		try {
			Gson gson = new Gson();
			String usersJson = gson.toJson(bean);
			JSONParser parser = new JSONParser();
			json = (JSONObject) parser.parse(usersJson);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		LogManager.info("Exit - getJson quickRenewalBean--> ");
		return json;
	}
	
}
