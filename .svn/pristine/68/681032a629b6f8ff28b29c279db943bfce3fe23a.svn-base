package com.maan.webservice.rest.resource;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.commons.lang3.SystemUtils;
import org.apache.commons.lang3.StringUtils;

import com.google.gson.Gson;
import com.maan.Motor.Services.MotorService;
import com.maan.Motor.controller.MotorAction;
import com.maan.Motor.controller.MotorBean;
import com.maan.adminnew.report.motor.MotorReportAction;
import com.maan.adminnew.report.motor.MotorReportBean;
import com.maan.adminnew.report.motor.MotorReportService;
import com.maan.common.LogManager;
import com.maan.payment.PaymentAction;
import com.maan.payment.PaymentBean;
import com.maan.payment.PaymentService;
import com.maan.webservice.service.ManipulateImage;


@SuppressWarnings("unused")
@Path("motor")
public class MotorResource {
	
	@POST
	@Path("/editQuote")
	@Produces(MediaType.APPLICATION_JSON)
	public MotorBean editQuote(@FormParam("paramMotorBean") String paramMotorBean) {
		LogManager.info("editQuote - Enter");
		LogManager.info("Enter - paramMotorBean --> "+paramMotorBean);
		MotorBean bean = new Gson().fromJson(paramMotorBean, MotorBean.class);
		setConstantValues(bean);
		MotorService service = new MotorService();
		service.editQuote(bean,hasActionErrors(bean.getActionErrorsBean()));
		if(StringUtils.isNotBlank(bean.getApplicationNo())) {
			bean.setVehicleDetailsList(service.getMultiVehicleDetails(bean.getApplicationNo(), bean.getProductId(), bean.getBranchCode()));
		}
		LogManager.info("editQuote - Exit");
		return bean;
	}
	
	@POST
	@Path("/getQuote")
	@Produces(MediaType.APPLICATION_JSON)
	public MotorBean getQuote(@FormParam("paramMotorBean") String paramMotorBean) {
		LogManager.info("Enter - getQuote --> "+paramMotorBean);
		System.out.println("getQuote"+paramMotorBean);
		
		MotorBean bean = new Gson().fromJson(paramMotorBean, MotorBean.class);
		MotorAction motorAction = new MotorAction();
		motorAction.mapRestBean(bean);
		motorAction.getQuote();
		bean.setReferralMsg(motorAction.getReferralMsgs());
		if(!hasActionErrors(bean.getActionErrorsBean())) {
			MotorService service = new MotorService();
			bean.setVehicleDetailsList(service.getMultiVehicleDetails(bean.getApplicationNo(), bean.getProductId(), bean.getBranchCode()));
		}
		LogManager.info("Exit - getQuote "+bean);
		return bean;
	}
	
	@POST
	@Path("/insertOptionCover")
	@Produces(MediaType.APPLICATION_JSON)
	public  MotorBean insertOptionCover(@FormParam("paramMotorBean") String paramMotorBean) {
		System.out.println("paramerters :::"+paramMotorBean);
		LogManager.info("Enter - insertOptionCover --> "+paramMotorBean);
		MotorBean bean = new Gson().fromJson(paramMotorBean, MotorBean.class);
		MotorAction motorAction = new MotorAction();
		motorAction.mapRestBean(bean);
		motorAction.insertOptionCover();
		if(!hasActionErrors(bean.getActionErrorsBean())) {
			MotorService service = new MotorService();
			bean.setVehicleDetailsList(service.getMultiVehicleDetails(bean.getApplicationNo(), bean.getProductId(), bean.getBranchCode()));
		}
		LogManager.info("Exit - insertOptionCover ");
		return bean;
	}
	
	@POST
	@Path("/getPolicy")
	@Produces(MediaType.APPLICATION_JSON)
	public  MotorBean getGeratePolicy(@FormParam("paramMotorBean") String 
			paramMotorBean) {
		LogManager.info("Enter - getPolicy --> "+paramMotorBean);
		MotorBean jsonBean = new Gson().fromJson(paramMotorBean, MotorBean.class);
		MotorAction motorAction=new MotorAction();
		motorAction.mapRestBean(jsonBean);
		motorAction.getGeratePolicy();
		jsonBean.setPolicyInformationList(motorAction.getPolicyInformation());
		LogManager.info("Exit - getPolicy ");
		return jsonBean;
	}
	
	@POST
	@Path("/optionsList")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Object> getOptionsList(@FormParam("paramMotorBean") String paramMotorBean) {
		LogManager.info("Enter - getOptionsList() --> "+paramMotorBean);
		List<Object> list=null;
		try{
			//paramMotorBean="{\"userType\":\"User\",\"branchCode\":\"01\",\"quoteNo\":\"\",\"applicationNo\":\"\",\"customerId\":\"\",\"userId\":\"\",\"deviceType\":\"HYBRID\",\"contentTypeId\":\"0\",\"productId\":\"65\",\"loginId\":\"\",\"option\":\"city\"}";
			Gson gson = new Gson();
			MotorBean jsonBean = gson.fromJson(paramMotorBean, MotorBean.class);
			MotorAction motorAction = new MotorAction();
			motorAction.mapRestBean(jsonBean);
			list = motorAction.getDropdownListRest(); 
			LogManager.info("Exit - optionsList - List --->" +list);
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
	public MotorBean getConditionClausesList(@FormParam("paramMotorBean") String paramMotorBean) {
		LogManager.info("Enter - ConditionClauses --> "+paramMotorBean);
		MotorBean jsonBean = new Gson().fromJson(paramMotorBean, MotorBean.class);
		MotorAction motorAction = new MotorAction();
		motorAction.mapRestBean(jsonBean);
		jsonBean.setTransList(motorAction.getConditionClausesList());    
		LogManager.info("Exit - ConditionClauses ");
		return jsonBean;
	}
	
	@POST
	@Path("/uploadimgdetail")
	@Produces(MediaType.APPLICATION_JSON)
	public String uploadimgdetail(@FormParam("paramMotorBean") String paramMotorBean) {
		String refNo="";
		LogManager.info("uploadimgdetail - Enter --> "+paramMotorBean);
		MotorBean bean = new Gson().fromJson(paramMotorBean, MotorBean.class);
		MotorService service = new MotorService();
		service.insUploadImgDetail(bean);
		if(StringUtils.isEmpty(bean.getRefNo())){
			refNo="Error the Image Upload";
		}else{
			refNo = bean.getRefNo();
		}
		LogManager.info("uploadimgdetail - Exit");
		return refNo;
	}
	
	/*@POST
	@Path("/uploadimgdocument")
	@Produces(MediaType.APPLICATION_JSON)
	public MotorBean uploadimgdocument(@FormParam("paramMotorBean") String paramMotorBean,@FormParam("encodedImgStr") String encodedImgStr) {
		LogManager.info("uploadimgdocument - Enter");
		MotorBean bean = new Gson().fromJson(paramMotorBean, MotorBean.class);
		MotorService service = new MotorService();
		bean.setWebRootPath(getApplicationPath());
		ManipulateImage.convertStringtoImage(bean,encodedImgStr);
		//String encodedImageStr,	String fileName, String webRootPath,String refNo,String type
		LogManager.info("uploadimgdocument - Exit");
		return bean;
	}*/
	
	@SuppressWarnings("unchecked")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/uploadimage")
	public Map<String,String> newMethod(Object inputValues){
		String result="";
		Map<String,String> map=null;
		LogManager.info("uploadimgdocument - Enter  --> "+inputValues);
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
		LogManager.info("uploadimgdocument - Exit");
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
		LogManager.info("getNotify - Enter");
		List<Object> list=null;
		try{
			MotorService service = new MotorService();
			list = service.getNotifyList();
		}catch (Exception exception) {
			list = new ArrayList<Object>();
			LogManager.debug(exception);
		}
		LogManager.info("getNotify - Exit");
		return list;
	}
	
	@POST
	@Path("/getTypeAssistant")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Map<String,Object>> getTypeofAssistantList() {	
		LogManager.info("getTypeAssistant - Enter");
		List<Map<String,Object>> list=null;
		try{
			MotorService service = new MotorService();
			list = service.getTypeofAssistantList();
		}catch (Exception exception) {
			list = new ArrayList<Map<String,Object>>();
			LogManager.debug(exception);
		}		
		LogManager.info("getTypeAssistant - Exit");
		return list;
	}
	
	/*@POST
	@Path("/getPolicyNoList")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Object> getPolicyNoList() {		
		List<Object> list=null;
		try{
			MotorService service = new MotorService();
			list = service.getPolicyNoList();
		}catch (Exception exception) {
			list = new ArrayList<Object>();
			LogManager.debug(exception);
		}		
		return list;
	}*/
	
	@POST
	@Path("/insRoadAssistant")
	@Produces(MediaType.APPLICATION_JSON)
	public MotorBean insRoadAssistant(@FormParam("paramMotorBean") String paramMotorBean) {
		String refNo="";
		LogManager.info("insRoadAssistant - Enter --> "+paramMotorBean);
		MotorBean bean = new Gson().fromJson(paramMotorBean, MotorBean.class);
		MotorService service = new MotorService();
		MotorAction action = new MotorAction();
		action.mapRestBean(bean);
		action.insRoadAssistant();
		//service.insRoadAssistantDetail(bean);
		LogManager.info("insRoadAssistant - Exit");
		return bean;
	}
	
	@POST
	@Path("/getRAStatus")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Map<String,Object>> getRAStatus(@FormParam("paramMotorBean") String paramMotorBean) {
		String refNo="";
		LogManager.info("getRAStatus - Enter --> "+paramMotorBean);
		MotorReportBean bean = new Gson().fromJson(paramMotorBean, MotorReportBean.class);
		List<Map<String,Object>> list=null;
		try{
			MotorReportService service = new MotorReportService();
			list = service.getRosdAssistReport(bean,"view");
		}catch (Exception exception) {
			list = new ArrayList<Map<String,Object>>();
			LogManager.debug(exception);
		}		
		LogManager.info("getRAStatus - Exit");
		return list;
	}
	
	@POST
	@Path("/updRAFeedBack")
	@Produces(MediaType.APPLICATION_JSON)
	public MotorBean updRAFeedBack(@FormParam("paramMotorBean") String paramMotorBean) {
		String refNo="";
		LogManager.info("updRAFeedBack - Enter --> "+paramMotorBean);
		MotorBean bean = new Gson().fromJson(paramMotorBean, MotorBean.class);
		MotorService service = new MotorService();
		MotorAction action = new MotorAction();
		action.mapRestBean(bean);
		action.updRAFeedBack();
		//service.insRoadAssistantDetail(bean);
		LogManager.info("updRAFeedBack - Exit");
		return bean;
	}
	
	@POST
	@Path("/getInsDetail")
	@Produces(MediaType.APPLICATION_JSON)
	public PaymentBean getInsDetail(@FormParam("paramPaymentBean") String paramPaymentBean) {
		LogManager.info("getInsDetail - Enter --> "+paramPaymentBean);
		PaymentBean jsonBean = new Gson().fromJson(paramPaymentBean, PaymentBean.class);
		PaymentService service=new PaymentService();
		if("Y".equalsIgnoreCase(jsonBean.getInstallmentYN())){
			service.installmentCalc(jsonBean);
			jsonBean.setInsList(service.getInstallmentDetailsList(jsonBean.getQuoteNo()));
		}else{
			MotorAction action = new MotorAction();
			action.deleteinstallment();
		}
		LogManager.info("getInsDetail - Exit ");
		return jsonBean;
	}
	
	@POST
	@Path("/deleteVehicle")
	@Produces(MediaType.APPLICATION_JSON)
	public MotorBean deleteVehicle(@FormParam("paramMotorBean") String paramMotorBean) {
		LogManager.info("deleteVehicle - Enter ---> "+paramMotorBean);
		MotorBean jsonBean = new Gson().fromJson(paramMotorBean, MotorBean.class);
		MotorAction action = new MotorAction();
		action.mapRestBean(jsonBean);
		action.deleteAddVehicle();
		LogManager.info("deleteVehicle - Exit ");
		return jsonBean;
	}
	
	/*@POST
	@Path("/insList")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Map<String, Object>> getinsList(@FormParam("paramPaymentBean") String paramPaymentBean) {		
		List<Map<String, Object>> list=null;
		try{
			PaymentBean jsonBean = new Gson().fromJson(paramPaymentBean, PaymentBean.class);
			PaymentAction action = new PaymentAction();
			list = action.getInstallmentDetailsList();       
		}catch (Exception exception) {
			LogManager.debug(exception);
		}		
		return list;
	}*/
	
}