package com.maan.webservice.rest.resource;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.maan.Motor.Claim.ClaimAction;
import com.maan.Motor.Claim.ClaimBean;
import com.maan.Motor.Claim.ClaimService;
import com.maan.adminnew.common.CommonService;
import com.maan.common.LogManager;
import com.maan.report.ReportAction;
import com.maan.report.service.PdfService;
import com.maan.report.service.ReportService;

@Path("reportV1")
public class ReportResourceV1 {
	
	@POST
	@Path("/getReportList")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Map<String,Object>> getReportList(@FormParam("paramReportBean") String paramReportBean) {
		LogManager.info("/reportV1/getReportList - Enter");
		List<Map<String,Object>> resultList = new ArrayList<Map<String,Object>>();
		try {
			ReportService service = new ReportService();
			Type type = new TypeToken<Map<String,Object>>(){}.getType();
			Map<String,Object> inputParams = new Gson().fromJson(paramReportBean, type);
			String loginId = (String) inputParams.get("loginId");
			String productId = (String) inputParams.get("productId");
			String issuer = (String) inputParams.get("issuer");
			String menuType = (String) inputParams.get("menuType");
			String openCoverNo = (String) inputParams.get("openCoverNo");
			String startDate = (String) inputParams.get("startDate");
			String endDate = (String) inputParams.get("endDate");
			String quoteNo = (String) inputParams.get("quoteNo");
			String policyNo = (String) inputParams.get("policyNo");
			String searchBy = (String) inputParams.get("searchBy");
			String searchValue = (String) inputParams.get("searchValue");
			String searchField = (String) inputParams.get("searchField");
			String searchString = (String) inputParams.get("searchString");
			String searchOper = (String) inputParams.get("searchOper");
			String branchCode = (String) inputParams.get("branchCode");
			String userLogin = (String) inputParams.get("userLogin");
			String schemeId = StringUtils.isBlank((String) inputParams.get("schemeId"))?"1":(String) inputParams.get("schemeId");
			String vehicleId = (String) inputParams.get("vehicleId");
			String loginBranch=(String) inputParams.get("loginBranch");
			
			if("AP".equals(menuType)) {
				resultList = service.getHomeApproverPendingList(loginId,branchCode,productId,"ALL");
			} else if("PVD".equals(menuType)) {
				if("65".equalsIgnoreCase(productId)){
					resultList = service.getMultiVehicleDetails(policyNo, productId, branchCode);
				}else if("30".equalsIgnoreCase(productId)){
					resultList = service.getHomePolicyDetails(policyNo, productId, branchCode);
				}
			} else if("CE".equals(menuType)) {
				resultList = service.getPolicyNoList(productId,loginId);
			} /*else if("ENDO".equals(menuType)){
				resultList = service.getEndorseMentDetails(policyNo, productId, branchCode);
			}*/
			else {
				resultList = service.getReportList(loginId, productId, issuer, menuType,openCoverNo,
						startDate,endDate, quoteNo, policyNo,searchBy,searchValue, searchField, 
						searchString, searchOper, branchCode,userLogin,schemeId,vehicleId,loginBranch);
			}
		} catch (Exception exception) {
			LogManager.debug(exception);
		}
		LogManager.info("/report/getReportList - Exit");
		return resultList;
	}
	@POST
	@Path("/getPolicyDocument")
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String,String> getPolicyDocument(@FormParam("paramReportBean") String paramReportBean) {
		Map<String,String> fileDetails = new HashMap<String,String>();
		try {
			String filePath = "";
			PdfService service = new PdfService();
			Type type = new TypeToken<Map<String,Object>>(){}.getType();
			Map<String,Object> inputParams = new Gson().fromJson(paramReportBean, type);
			
			String reqFrom = inputParams.get("type")==null?"":inputParams.get("type").toString();
			String productId = inputParams.get("productId")==null?"":inputParams.get("productId").toString();
			String branchCode = inputParams.get("branchCode")==null?"":inputParams.get("branchCode").toString();
			String schemeId = inputParams.get("schemeId")==null?"":inputParams.get("schemeId").toString();
			String contentTypeId = inputParams.get("contentTypeId")==null?"":inputParams.get("contentTypeId").toString();
			String policyNo = inputParams.get("policyNo")==null?"":inputParams.get("policyNo").toString();
			String quoteNo = inputParams.get("quoteNo")==null?"":inputParams.get("quoteNo").toString();
			String loginId = inputParams.get("loginId")==null?"":inputParams.get("loginId").toString();
			
			String belongingBranch = "01";
			
			String displayText = service.getDisplayText(reqFrom, "User", productId, policyNo);
			if("QUOTE_PRINT".equals(reqFrom)) {
				filePath = service.quotePrint(productId, schemeId, contentTypeId, quoteNo, branchCode, belongingBranch);
			} else if("DRAFT".equals(reqFrom)) {
				filePath = service.schedule(productId, schemeId, contentTypeId, branchCode, belongingBranch, quoteNo, displayText, "draftMode");
			} else if("SCHEDULE".equals(reqFrom)) {
				filePath = service.schedule(productId, schemeId, contentTypeId, branchCode, belongingBranch, policyNo, displayText, "");
			} else if("RECEIPT".equals(reqFrom)) {
				filePath = service.receipt(quoteNo, productId, branchCode);
			} else if("MOTOR_FLEET_SCHEDULE".equals(reqFrom)) {
				String vehicleId = inputParams.get("vehicleId")==null?"":inputParams.get("vehicleId").toString();
				filePath = service.motorFleetSchedule(quoteNo, vehicleId, branchCode,"",loginId);
			} else if("DEBIT".equals(reqFrom)) {
				filePath = service.debit(policyNo, displayText, productId, branchCode);
			}else if("".equalsIgnoreCase(reqFrom)){
				filePath = new ReportAction().policyWording();
			}
			
			fileDetails.put("FILE_NAME", FilenameUtils.getName(CommonService.getApplicationPath() + filePath));
			fileDetails.put("FILE_PATH", filePath);
		} catch (Exception exception) {
			LogManager.debug(exception);
		}
		return fileDetails;
	}
	
	@POST
	@Path("/getClaimList")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Map<String,Object>> getClaimList(@FormParam("paramClaimBean") String paramClaimBean) {
		LogManager.info("getClaimList - Enter");
		ClaimBean bean = new Gson().fromJson(paramClaimBean, ClaimBean.class);
		List<Map<String,Object>> list=null;
		try{
			ClaimService service = new ClaimService();
			if("showlist".equalsIgnoreCase(bean.getMode())){
				list = service.getEndtReqList(bean);
			}
			else if("showlistClaim".equalsIgnoreCase(bean.getMode())){
				list = service.getClaimReqList(bean);
			}
			else if("newReq".equalsIgnoreCase(bean.getMode())){
				list = service.getEndtNewReqList(bean);
			}else if("newClaimReq".equalsIgnoreCase(bean.getMode())){
				if("3".equalsIgnoreCase(bean.getPolicyType())){
					list=service.getIntimateSameTpaVehicleList(bean);
				}else{
					list=service.getIntimateSameVehicleList(bean);
				}
			}
			
			
		}catch (Exception exception) {
			list = new ArrayList<Map<String,Object>>();
			LogManager.debug(exception);
		}		
		LogManager.info("getClaimList - Exit");
		return list;
	}
	
	@POST
	@Path("/getEndorsement")
	@Produces(MediaType.APPLICATION_JSON)
	public ClaimBean getEndorsement(@FormParam("paramClaimBean") String paramClaimBean) {
		LogManager.info("getClaimList - Enter");
		ClaimBean bean = new Gson().fromJson(paramClaimBean, ClaimBean.class);
		try{
			ClaimService service = new ClaimService();
			bean.setIntimateMobilePolicyList(service.getIntimatePolicy(bean));
			if("30".equalsIgnoreCase(bean.getProductId())){
				bean.setHomeMobileList(service.getHomeList(bean));
			}else if("65".equalsIgnoreCase(bean.getProductId())){
				bean.setIntimateMobileVehicleList(service.getIntimateVehicle(bean));
			}
			bean.setEndorsementMobileDropdown(service.getEndorsementDropdown(bean));
			
		}catch (Exception exception) {
			LogManager.debug(exception);
		}		
		LogManager.info("getClaimList - Exit");
		return bean;
	}
	
	@POST
	@Path("/getEndorsementInsert")
	@Produces(MediaType.APPLICATION_JSON)
	public ClaimBean getEndorsementInsert(@FormParam("paramClaimBean") String paramClaimBean) {
		LogManager.info("getEndorsementInsert - Enter");
		ClaimBean bean = new Gson().fromJson(paramClaimBean, ClaimBean.class);
		try{
			ClaimAction action = new ClaimAction();
			action.mapRestBean(bean);
			action.endorsementInsert();
		}catch (Exception exception) {
			LogManager.debug(exception);
		}		
		LogManager.info("getEndorsementInsert - Exit");
		return bean;
	}
	
	@POST
	@Path("/getClaim")
	@Produces(MediaType.APPLICATION_JSON)
	public ClaimBean getClaim(@FormParam("paramClaimBean") String paramClaimBean) {
		LogManager.info("getClaimList - Enter");
		ClaimBean bean = new Gson().fromJson(paramClaimBean, ClaimBean.class);
		try{
			ClaimAction action = new ClaimAction();
			ClaimService service = new ClaimService();
			bean.setIntimateMobilePolicyList(service.getIntimatePolicy(bean));
			bean.setIntimateMobileStatusList(service.getIntimateStatus(bean));
			bean.setIntimateMobileVehicleList(service.getIntimateVehicle(bean));
			if("30".equalsIgnoreCase(bean.getProductId())){
				bean.setHomeMobileList(service.getHomeList(bean));
			}
			if("Update".equalsIgnoreCase(bean.getMode())){
				bean.setUpdStatus(service.getUpdateStatus(bean));
				if("3".equalsIgnoreCase(bean.getPolicyType())){
					service.getIntimateThirdPartyEdit(bean);
				}else{
					service.getIntimateEdit(bean);
				}
			}
			action.mapRestBean(bean);
			action.intimate();
			
		}catch (Exception exception) {
			LogManager.debug(exception);
		}		
		LogManager.info("getClaimList - Exit");
		return bean;
	}
	
	@POST
	@Path("/getClaimInsert")
	@Produces(MediaType.APPLICATION_JSON)
	public ClaimBean getClaimInsert(@FormParam("paramClaimBean") String paramClaimBean) {
		LogManager.info("getEndorsementInsert - Enter");
		ClaimBean bean = new Gson().fromJson(paramClaimBean, ClaimBean.class);
		try{
			ClaimAction action = new ClaimAction();
			action.mapRestBean(bean);
			action.intimateInsert();
			/*ClaimService service = new ClaimService();
			bean.setIntimateMobilePolicyList(service.getIntimatePolicy(bean));
			bean.setIntimateMobileStatusList(service.getIntimateStatus(bean));
			bean.setIntimateMobileVehicleList(service.getIntimateVehicle(bean));
			bean.setEndorsementMobileDropdown(service.getEndorsementDropdown(bean));*/
			
		}catch (Exception exception) {
			LogManager.debug(exception);
		}		
		LogManager.info("getClaimInsert - Exit");
		return bean;
	}
}
