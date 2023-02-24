package com.maan.Motor.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.json.annotations.JSON;

import com.maan.Motor.Services.MotorService;
import com.maan.adminnew.common.CommonService;
import com.maan.adminnew.paymentProcess.PaymentProcessService;
import com.maan.adminnew.report.motor.MotorReportAction;
import com.maan.common.DBConstants;
import com.maan.common.LogManager;
import com.maan.common.Validation;
import com.maan.common.dao.CommonDAO;
import com.maan.common.login.LogInAction;
import com.maan.common.otp.OTPGenerator;
import com.maan.common.sms.CryptoService;
import com.maan.common.sms.SmsEmailDAO;
import com.maan.common.sms.SmsEmailUtil;
import com.maan.common.util.ResourceBundleUtil;
import com.maan.customer.dao.CustomerDAO;
import com.maan.customer.service.CustomerService;
import com.maan.notification.fcm.NotificationService;
import com.maan.payment.PaymentDAO;
import com.maan.payment.airtel.AirtelService;
import com.maan.payment.airtel.model.ReqToPayIpModelAirtel;
import com.maan.payment.airtel.model.Subscriber;
import com.maan.payment.airtel.model.Transaction;
import com.maan.payment.mtn.MtnService;
import com.maan.payment.mtn.model.Payer;
import com.maan.payment.mtn.model.ReqToPayIpModel;
import com.maan.services.util.ValidationFormat;
import com.maan.userMgt.UserMgtAction;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.util.ArrayUtils;

import nl.captcha.Captcha;

public class MotorAction extends ActionSupport implements ModelDriven<MotorBean>{
	private MotorBean bean=new MotorBean();
	MotorService service=new MotorService();
	private com.maan.common.dao.CommonDAO commonDAO = new com.maan.common.dao.CommonDAO();
	Validation validation=new Validation();
	private static final long serialVersionUID = 1L;
	//private static final String DROPDOWN = "dropdown";
	//private static final String FIELD = "ELEMENT_NAME";
	private String fields;
	private static final String DOCUMENT_FILE_PATH = CommonService.getApplicationPath() + "documents/";
	private static final List<String> PLATE_CHARACTER_LIST = Arrays.asList(new String[]{"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"});
	private static final List<String> CURRENCY_TYPE_LIST = Arrays.asList(new String[]{ResourceBundleUtil.findDefaultText("MOTOR_CURRENCY_LOCAL"),ResourceBundleUtil.findDefaultText("MOTOR_CURRENCY_FOREIGN")});

	private InputStream inputStream;
	
	public MotorBean getModel() {
		String productId=(String) getSession().get("product_id");
		bean.setReferal((String)getSession().get("Referal"));
		bean.setIssuer((String)getSession().get("RSAISSUER"));
		bean.setBranchCode((String)getSession().get("LoginBranchCode"));
		bean.setBrokerCode((String)getSession().get("AgencyCode"));
		bean.setActualBranch((String)getSession().get("adminBranch"));
		if(StringUtils.isNotEmpty(getRequest().getParameter("productId"))) {
			getSession().put("product_id", getRequest().getParameter("productId"));
			productId=(String) getSession().get("product_id");
		}
		Captcha captcha = null;
		captcha = (Captcha) getSession().get(Captcha.NAME);
		bean.setProductId(productId);
		bean.setDestCountry("1");
		bean.setOriginCountry("1");
		bean.setUserType((String)getSession().get("usertype"));
		bean.setUser((String)getSession().get("user1"));
		bean.setLoginType(getSession().get("LoginType")==null?"":getSession().get("LoginType").toString());
		//bean.setBranchCode(getSession().get("branchCode")==null?"01":getSession().get("branchCode").toString());
		bean.setBranchCode(StringUtils.isBlank(bean.getBranchCode())?(String)getSession().get("branchCode"):bean.getBranchCode());
		bean.setLoginBranch(getSession().get("selectedBranch")==null?"":(String)getSession().get("selectedBranch"));
		if(StringUtils.isNotEmpty(bean.getIssuer()) && StringUtils.isNotBlank(bean.getBrokerCode())) {
			bean.setLoginId(commonDAO.getSingleInfo("loginId", new String[]{bean.getBrokerCode()}));
		}
		else {
			bean.setLoginId((String)getSession().get("user"));
		}
		bean.setCaptcha(captcha);
		return bean;
	}

	public List<Object> getTitleList()
	{
		return commonDAO.getOptionsList("title", bean.getProductId(), ("title"),bean);
	}
	
	public List<Object> getCityList()
	{
		return commonDAO.getCityList(bean);
	}
	public List<Object> getPlateCharList()
	{
		return commonDAO.getOptionsList("plateCharacter", bean.getProductId(), ("plateCharacter"),bean);
	}
	public List<Object> getMakeList()
	{
		return commonDAO.getMakeList(bean);
	}
	public List<Object> getModelList()
	{
		return commonDAO.getModelList(bean);
	}
	public List<Object> getTypeBodyList()
	{
		return commonDAO.getOptionsList("typeBody", bean.getProductId(), ("typeBody"),bean);
	}
	
	public List<Object> getNoOfClaimsList(){
		return commonDAO.getNoOfClaims(bean);
	}
	
	public LinkedHashMap<Integer,Integer> getMfgYrMap() {
		LinkedHashMap<Integer,Integer>map=new LinkedHashMap<Integer,Integer>();
		for(int i=Calendar.getInstance().get(Calendar.YEAR);i>=Calendar.getInstance().get(Calendar.YEAR)-25;i--)
			map.put(i,i);
		return map;
	}
	public LinkedHashMap<Integer,Integer> getRegYearMap() {
		LinkedHashMap<Integer,Integer>map=new LinkedHashMap<Integer,Integer>();
		for(int i=Calendar.getInstance().get(Calendar.YEAR);i>=Calendar.getInstance().get(Calendar.YEAR)-10;i--)
			map.put(i,i);
		return map;
	}
	public static List<String> getPlateCharacterList() {
		return PLATE_CHARACTER_LIST;
	}
	public static List<String> getCurrencyTypeList() {
		return CURRENCY_TYPE_LIST;
	}
	public List<Object> getNoOfCylinderList()
	{
		return commonDAO.getOptionsList("noOfCylinder", bean.getProductId(), ("noOfCylinder"),bean);
	}
	public List<Object> getVehicleUsageList()
	{
		return commonDAO.getOptionsList("vehicleUsage", bean.getProductId(), ("vehicleUsage"),bean);
	}
	public List<Object> getAreaCoverageList()
	{
		return commonDAO.getOptionsList("areaCoverage", bean.getProductId(), ("areaCoverage"),bean);
	}
	public List<Object> getDriverNationalityList()
	{
		return commonDAO.getOptionsList("driverNationality", bean.getProductId(), ("driverNationality"),bean);
	}
	public List<Object> getNoClaimBonusList() {
		/*LinkedHashMap<Integer,Integer>map=new LinkedHashMap<Integer,Integer>();
		for(int i=0;i<=5;i++)
			map.put(i,i);
		return map;*/
		return commonDAO.getNoClaimBonusList(bean);
	}
	public List<Object> getVehicleColourList()
	{
		return commonDAO.getVehicleColourList(bean);
	}
	public List<Object> getBankOfFinanceList() {
		return commonDAO.getBankOfFinanceList(bean);
	}
	public List<Object> getInsCompanyList() {
		return commonDAO.insCompanyList(bean);
	}
	@JSON(serialize=false)
	public List<Object> getMfgLocList() {
		return commonDAO.getOptionsList("manufactureLoc", bean.getProductId(), ("manufactureLoc"),bean);
	}
	@JSON(serialize=false)
	public List<Object> getDeductibleList() {
		return commonDAO.getDeductibleList("deductible", bean);
	}
	/*public List<Object> getBrokerList()
	{
		Object[] objects=new String[]{bean.getOption(),productId,actualBranch,"","","",bean.getOriginCountry(),
				bean.getDestCountry(),"",bean.getMake(),bean.getTypeBody(),loginId,bean.getBrokerCode()};

		return commonDAO.getOptionsList("broker", objects);
	}*/
	public List<Object> getBrokerList() {
		return commonDAO.getOptionsList("broker", bean.getProductId(), "BrokerList", bean);
	}
	public List<Object> getExecutiveList() {
		/*Object[] objects=new String[]{bean.getOption(),productId,actualBranch,"","","",bean.getOriginCountry(),
				bean.getDestCountry(),"",bean.getMake(),bean.getTypeBody(),loginId,bean.getBrokerCode()};*/
		return  commonDAO.getOptionsList("executive", bean.getProductId(), ("executive"), bean);
	}
	public List<Map<String,Object>> getVehicleTypeDetails() {
		return service.getVehicleTypeDetails(bean.getMake(),bean.getModel(),bean.getBranchCode(),bean.getApplicationNo(),bean.getDeleteVehicleId());
	}
	public List<Object> getModeOfPaymentList() {
		List<Object> list = new ArrayList<Object>();
		List<Object> list1 = commonDAO.getOptionsList("modeOfPayment",bean.getProductId(), ("modeOfPayment"),bean);
		List<Object> list2 = commonDAO.getUserBasedModeOfPayment(bean.getUserType());
		try{
			if(list1!=null && list1.size()>0){
				list.addAll(list1);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		try{
			if(list2!=null && list2.size()>0){
				list.addAll(list2);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
	
	public List<Map<String,Object>> getPaymentList(){
		return service.getPaymentList(bean);
	}
	
	
	public String vehicleTypeDetailsAjax() {
		getRequest().setAttribute(DBConstants.FIELD, "vehicleTypeDetailsAjax");
		return DBConstants.DROPDOWN;
	}
	
	public String modelList(){
		getRequest().setAttribute(DBConstants.FIELD, "model");
		System.out.println(bean.getMake());
		return DBConstants.DROPDOWN;
	}
	public String noOfCylinderList(){
		getRequest().setAttribute(DBConstants.FIELD, "noOfCylinder");
		return DBConstants.DROPDOWN;
	}
	public String makeListAjax(){
		getRequest().setAttribute(DBConstants.FIELD, "makeListAjax");
		return DBConstants.DROPDOWN;
	}
	public String typeBodyAjax(){
		getRequest().setAttribute(DBConstants.FIELD, "typeBody");
		return DBConstants.DROPDOWN;
	}
	public String vehicleUsageAjax(){
		getRequest().setAttribute(DBConstants.FIELD, "vehicleUsageAjax");
		return DBConstants.DROPDOWN;
	}
	public String deductibleAjax(){
		getRequest().setAttribute(DBConstants.FIELD, "deductibleAjax");
		return DBConstants.DROPDOWN;
	}
	public String ncbAjax(){
		getRequest().setAttribute(DBConstants.FIELD, "ncbAjax");
		return DBConstants.DROPDOWN;
	}
	/*public String newQuote() {
		if(userType.equalsIgnoreCase("User")){
			service.getMotorCustomerDetails(bean,loginId);
		}
		bean.setDisplay("newQuote");
		return INPUT;
	}*/
	
	public List<Map<String,Object>> getPolicyTypeList()
	{
		return service.getPolicyTypeList(bean);
	}
	
	public String editQuote() {
		String forward="";
		try {
//			if(StringUtils.isBlank(bean.getApplicationNo()) && StringUtils.isBlank(bean.getMode())) {
//				bean.setCustomerIndividualList(	service.getCustomerList(bean,"INDIVIDUAL"));
//				bean.setCustomerCorporateList(service.getCustomerList(bean,"CORPORATE"));
//				return "custQuote";
//			}
			bean.setPolicyTypeList(service.getPolicyTypeList(bean));
			service.editQuote(bean, hasActionErrors());
			if("admin".equalsIgnoreCase(bean.getUser()))
				forward="adminView";
			else if("Y".equalsIgnoreCase(bean.getReferal())) {
				forward="referalView";
			}
			else
				forward=INPUT;
		}
		catch(Exception exception) {
			LogManager.debug(exception);
		}
		return forward;
	}
	
	public String editQuoteNew() {
		String forward="";
		try {

			if(StringUtils.isNotBlank(bean.getCustomerId())) {
			service.setCustDetail(bean,"custEdit");
			}
			if("RSAIssuer".equalsIgnoreCase(bean.getUserType()) && StringUtils.isBlank(bean.getMenuType()) && StringUtils.isBlank(bean.getQuoteStatus())){
				if(StringUtils.isBlank(bean.getBrokerCode())){
					addActionError("Please Select Broker Name");
				}
				if(StringUtils.isBlank(bean.getExecutive())){
					addActionError("Please Select Executive BDM");
				}
			}
			if(!hasActionErrors()){
				bean.setPolicyTypeList(service.getPolicyTypeList(bean));
				service.editQuoteNew(bean, hasActionErrors());
				if("quoteView".equalsIgnoreCase(bean.getReqFrom()))
					forward="motorQuote";
				else
					forward="inputV1";
			}else
				forward=newQuoteIssuer();
		}
		catch(Exception exception) {
			LogManager.debug(exception);
		}
		return forward;
	}
	
	public void removePolicyDetail(){
		bean.setPolicyType(null);
		bean.setPolicyStartDate(null);
		bean.setPolicyEndDate(null);
		bean.setCurrencyType(null);
	}
	/*public String editQuoteCustData() {
		try {
			//bean.setLoginId((String)getSession().get("user"));
			String userId=(String)getSession().get("user");
			bean.setCustomerIndividualList(service.getCustomerList(bean, "INDIVIDUAL",userId));
			bean.setCustomerCorporateList(service.getCustomerList(bean, "CORPORATE",userId));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "custQuote";
	}*/
	
	public String newQuoteIssuer(){
		//bean.setLoginId((String)getSession().get("user"));
		return "custQuote";
	}
	
	/*public String issuerCustList() {
		try {
			if(StringUtils.isBlank(bean.getBrokerCode())){
				addActionError("Please Select Broker Name");
			}
			if(StringUtils.isBlank(bean.getExecutive())){
				addActionError("Please Select Executive BDM");
			}
			if(!hasActionErrors()){
				//bean.setLoginId(bean.getBrokerCode());
				String userId=bean.getBrokerCode();
				bean.setMode("custList");
				bean.setCustomerIndividualList(service.getCustomerList(bean, "INDIVIDUAL",userId));
				bean.setCustomerCorporateList(service.getCustomerList(bean, "CORPORATE",userId));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "custQuote";
	}*/
	
	public String vehicleDtl(){
		String forward="inputV1";
		try {
			
			if(StringUtils.isBlank(bean.getCustomerId())) {
			bean.setCustomerId(new CustomerService().insertCustomerDetails(bean,"detailQuote"));
			}
			
			APiValidation();
			
			if (!hasActionErrors()) {
				
				if("edit".equalsIgnoreCase(bean.getMode())) {
					service.setCustDetail(bean,"custEdit");
				}else {
					service.setCustDetail(bean,"getVehicle");
				}
				
				bean.setPolicyTypeList(service.getPolicyTypeList(bean));
				service.editQuoteNew(bean, hasActionErrors());
				bean.setDisplay("vehDetails");
				
				if(StringUtils.isNotBlank(bean.getApplicationNo()))
					updateCustTypeMotorDtl();
				
				if(!"getSave".equalsIgnoreCase(bean.getActionType())) {
					service.getQuoteInfo(bean, hasActionErrors());
					if("RA".equalsIgnoreCase(bean.getQuoteStatus())) {
						viewOptionCover();
					}
				}
				else {
					bean.setDisplay("showQuoteInfo");
				}
				
				bean.setCustNameLabel(bean.getCustomerName());
				bean.setCustLastNameLabel(bean.getCustLastName());
				bean.setCustEmailLabel(bean.getEmail());
			}
			if(StringUtils.isNotBlank(bean.getReferralMsg()) && !"RA".equalsIgnoreCase(bean.getQuoteStatus())){
				bean.setDisplay("newQuote");
				forward="policyInfo";
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "inputV1";
	}
			
			
//			if(getActionErrors().isEmpty()) {
//				if(StringUtils.isBlank(bean.getApplicationNo())) {
//					bean.setApplicationNo(new CommonDAO().getSequenceNo("Application",bean.getProductId(),bean.getBranchCode(),"",""));
//				}
//				/*if(StringUtils.isBlank(bean.getQuoteNo())) {
//					bean.setQuoteNo(new CommonDAO().getSequenceNo("Quote",bean.getProductId(),bean.getBranchCode(),bean.getApplicationNo(),""));
//				}*/
//				if(!"getSave".equalsIgnoreCase(bean.getActionType())) {
//					service.getQuoteInfo(bean, hasActionErrors());
//					if("RA".equalsIgnoreCase(bean.getQuoteStatus())) {
//						viewOptionCover();
//					}
//					bean.setMulVehDtls(service.getMultiVehicleDetails(bean));
//				}
//				else {
//					bean.setDisplay("showQuoteInfo");
//				}
//				
//				bean.setCustNameLabel(bean.getCustomerName());
//				bean.setCustLastNameLabel(bean.getCustLastName());
//				bean.setCustEmailLabel(bean.getEmail());
//			}
//			if(StringUtils.isNotBlank(bean.getReferralMsg()) && !"RA".equalsIgnoreCase(bean.getQuoteStatus())){
//				bean.setDisplay("newQuote");
//				forward="policyInfo";
//			}
//		
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return "inputV1";
//	}
	/*public String editCovRate() {
		bean.setDisplay("editCovRate");
		service.getQuoteInfo(bean, hasActionErrors());
		service.getVehiclePremiumInfo(bean);
		return INPUT;
	}*/
	
	public String getBackVehicle(){
		try {
			bean.setDisplay("vehDetails");
			bean.setPolicyTypeList(service.getPolicyTypeList(bean));
			//service.getQuoteInfo(bean, hasActionErrors());
			//service.setCustomerDetailsB2B(bean);
			service.setCustDetail(bean,"");
			bean.setMulVehDtls(service.getMultiVehicleDetails(bean));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "inputV1";
	}
	
	public String editCovRateVehicle() {
		String forward=INPUT;
		try {
			bean.setPolicyTypeList(service.getPolicyTypeList(bean));
			bean.setDisplay(MotorService.DISPLAY_NEWQUOTE);
			bean.setVehDtls(service.getVehicleDetailsByIdNew(bean));
			service.getVehiclePremiumInfo(bean);
			if("admin".equalsIgnoreCase(bean.getUser()))
				forward="adminView";
			else if("Y".equalsIgnoreCase(bean.getReferal())) {
				forward="referalView";
			}
		} catch(Exception exception) {
			LogManager.debug(exception);
		}
		return forward;
	}
	
	public String updateCovRateVehicle() {
		String forward=INPUT;
		try {
			bean.setPolicyTypeList(service.getPolicyTypeList(bean));
			
			if(StringUtils.isNotBlank(bean.getRateVehicleId()) && "getCalculate".equalsIgnoreCase(bean.getActionType())) {
				if("admin".equalsIgnoreCase(bean.getUser()) || "Y".equalsIgnoreCase(bean.getReferal()))
					validateCovRate();
				if(!hasActionErrors()){
					service.updateCovRate(bean);
				}
			}
			if(("admin".equalsIgnoreCase(bean.getUser()) || "Y".equalsIgnoreCase(bean.getReferal())) && "RU".equalsIgnoreCase(bean.getQuoteStatus()) && !hasActionErrors() && !"getCalculate".equalsIgnoreCase(bean.getActionType())){
				bean.setMulVehDtls(service.getMultiVehicleDetails(bean));
				if(bean.getMulVehDtls()!=null && bean.getMulVehDtls().size()>0){
					String vehId="";
					String baseRate="";
					List<String> vehIdList=new ArrayList<String>();
					for(int i=0;i<bean.getMulVehDtls().size();i++){
						vehId=bean.getMulVehDtls().get(i).get("VEHICLE_ID")==null?"":bean.getMulVehDtls().get(i).get("VEHICLE_ID").toString();
						//baseRate=bean.getMulVehDtls().get(i).get("")==null?"":bean.getMulVehDtls().get(i).get("").toString();
						if(StringUtils.isNotBlank(vehId)){
							bean.setRateVehicleId(vehId);
							editCovRateVehicle();
							baseRate=bean.getPremiumInfo().get(0).get("RATE")==null?"":bean.getPremiumInfo().get(0).get("RATE").toString();
							if(StringUtils.isBlank(baseRate)){
								addActionError("Rate is Required to Calculate Premium for Vehicle "+vehId);
								vehIdList.add(vehId);
							}
							else{
								double rate=Double.parseDouble(baseRate);
								if(rate<=0){
									addActionError("Base Premium Rate Should be Greater Than 0 for Vehicle "+vehId);
									vehIdList.add(vehId);
								}
							}
						}
					}
					if(vehIdList!=null&&vehIdList.size()>0)
						bean.setRateVehicleId(vehIdList.get(0));
				}
			}
			if("getQuote".equalsIgnoreCase(bean.getActionType()) && !hasActionErrors()) {
				bean.setDisplay("policyInfo");
				service.getQuoteInfo(bean, hasActionErrors());
				service.getPremiumInfo(bean);
				service.getUpdatePremiumInfo(bean);
				service.premiumInfoNew(bean);
			} else {
				editCovRateVehicle();
			}
			bean.setCustNameLabel(bean.getCustomerName());
			bean.setCustLastNameLabel(bean.getCustLastName());
			bean.setCustEmailLabel(bean.getEmail());
			if("admin".equalsIgnoreCase(bean.getUser()))
				forward="adminView";
			else if("Y".equalsIgnoreCase(bean.getReferal())) {
				forward="referalView";
			}
		}
		catch(Exception exception) {
			exception.printStackTrace();
		}
		return forward;
	}
	
	public void validateCovRate(){
		if(bean.getBaseRate()==null)
			addActionError("Rate is Required to Calculate Premium");
		else{
			if(bean.getBaseRate().get(0)<=0)
				addActionError("Base Premium Rate Should be Greater Than 0");
		}
	}
	
	public String deleteAddVehicle() {
		try {
			service.deleteVehicleIdDetails(bean);
			bean.setDeleteVehicleId("");
			bean.setDisplay("vehDetails");
			service.removeVehicleBean(bean,hasActionErrors());
			getBackVehicle();
		} catch(Exception exception) {
			LogManager.debug(exception);
		}
		return INPUT;
	}
	
	public String deleteAddVehicleNew() {
		try {
			service.deleteVehicleIdDetails(bean);
			service.updateCoveragesInfo(bean.getApplicationNo(),bean.getBranchCode(),bean.getProductId(),bean.getEndTypeId(),bean.getLoginId(),"B",bean.getUserType(),"",bean.getReferralYN());
			int vehCount=service.vehCount(bean.getApplicationNo());
			if(vehCount>0){
				service.getQuoteInfo(bean, hasActionErrors());
				service.getPremiumInfo(bean);
				service.getUpdatePremiumInfo(bean);
			}
			bean.setDeleteVehicleId("");
			bean.setDisplay("vehDetails");
			service.removeVehicleBean(bean,hasActionErrors());
			getBackVehicle();
		} catch(Exception exception) {
			LogManager.debug(exception);
		}
		return "inputV1";
	}
	
	public String editAddVehicle() {
		String forward="";
		try {
			service.editVehicleIdDetails(bean);
			bean.setDeleteVehicleId("");
			bean.setIsVehicleEdit("Y");
			bean.setDisplay(MotorService.DISPLAY_NEWQUOTE);
			if("quoteView".equalsIgnoreCase(bean.getReqFrom()))
				forward="motorQuote";
			else
				forward=INPUT;
		}
		catch(Exception exception) {
			bean.setIsVehicleEdit("Y");
			LogManager.debug(exception);
		}
		return forward;
	}
	
	public String vehicleEditAjax() {
		try {
			if("insert".equalsIgnoreCase(bean.getReqFrom()) || "add".equalsIgnoreCase(bean.getReqFrom()))
				//validatePolicyDetail(bean);
				service.validatePolicy(bean);
				APiValidation();
			
			if(!hasActionErrors() || ("insert".equalsIgnoreCase(bean.getReqFrom()) || "edit".equalsIgnoreCase(bean.getReqFrom()))){
				if("edit".equalsIgnoreCase(bean.getReqFrom()) && !hasActionErrors()){
					service.editVehicleIdDetails(bean);
					getVehicleTypeDetails();
				}
				List<String> leasedYNIdList = new ArrayList<String>();
				try {
					leasedYNIdList.add(StringUtils.isBlank(bean.getLeasedYNIdList().get(0))?"N":bean.getLeasedYNIdList().get(0).toString());
				} catch (Exception e) {
					leasedYNIdList.add("N");
				}
				bean.setLeasedYNIdList(leasedYNIdList);
				List<String> prevClaimsYn = new ArrayList<String>();
				try {
					prevClaimsYn.add(StringUtils.isBlank(bean.getPrevClaimYn().get(0))?"N":bean.getPrevClaimYn().get(0).toString());
				} catch (Exception e) {
					prevClaimsYn.add("N");
				}
				bean.setPrevClaimYn(prevClaimsYn);
//				if("add".equalsIgnoreCase(bean.getReqFrom())){
//					driverEditAjax();
//				}else{
					bean.setDeleteVehicleId("");
					bean.setIsVehicleEdit("Y");
					bean.setDisplay("vehDetails");
					getRequest().setAttribute(DBConstants.FIELD, "vehicleEditAjax");
				//}
			}else{
				getRequest().setAttribute(DBConstants.FIELD, "vehicleEditAjaxError");
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return DBConstants.DROPDOWN;
	}

	private void validatePolicyDetail(MotorBean bean) {
		if(StringUtils.isBlank(bean.getPolicyType())){
			addActionError("Please Select Policy Type");
		}
		if(StringUtils.isBlank(bean.getPolicyStartDate())){
			addActionError("Please Select Policy Start Date");
		}
		if(StringUtils.isBlank(bean.getPolicyEndDate())){
			addActionError("Please Select Policy End Date");
		}
		if(StringUtils.isBlank(bean.getCurrencyType())){
			addActionError("Please Select Currency Type");
		}
	}

	public String addVehicle() {
		try {
			/*if(StringUtils.isNotEmpty(bean.getIssuer()) && StringUtils.isNotBlank(bean.getBrokerCode())) {
				bean.setLoginId(commonDAO.getSingleInfo("loginId", new String[]{bean.getBrokerCode()}));
			}
			getValidate(bean.getActionType());
			if(getActionErrors().isEmpty()) {
				bean.setCustomerId(new CustomerService().insertCustomerDetails(bean.getCustomerId(),bean.getLoginId(),bean.getBranchCode(),bean.getProductId(),bean.getTitle(),bean.getCustomerName(),bean.getMobileNo(),bean.getEmail(),bean.getAddress1(),bean.getAddress2(),bean.getPoBox(),bean.getCity(),bean.getCoreAppCode(),bean.getClientCustomerId(),bean.getCustArNo(),bean.getCustLastName(),bean.getCustnrc(),bean.getCustPassportNo(),bean.getCustdob(),bean.getCustAlterMobileNo(),bean.getCustLandLineNo(),bean.getCustomerType(),bean.getCompanyRegNo(),bean.getCustNameArabic(),bean.getCustdobar(),bean.getGender(),bean.getOccupation(),"detailQuote"));
				String result=service.getQuote(bean);
				if("SUCCESS".equalsIgnoreCase(result)){
					bean.setDisplay(MotorService.DISPLAY_NEWQUOTE);
				}
				else {
					if(DBConstants.DEVICETYPE_ANDROID.equals(bean.getDeviceType()) || DBConstants.DEVICETYPE_HYBRID.equals(bean.getDeviceType())) {
						bean.getActionErrorsBean().add(ResourceBundleUtil.findText(bean.getProductId(),"error.motor.update"));
					} else {
						addActionError(getText("error.motor.update"));
					}
				}
			}
			service.removeVehicleBean(bean,hasActionErrors());*/
			if(bean.getActionErrorsBean()==null) {
				bean.setActionErrorsBean(new ArrayList<String>());
			}
			List<String> errorList = service.addVehicle(bean);
			setActionErrorList(errorList);
			if(errorList==null || errorList.size()==0)
				bean.setIsVehicleEdit("N");
		}
		catch(Exception exception) {
			LogManager.debug(exception);
			exception.printStackTrace();
		}
		return INPUT;
	}
	public String addVehicleNew() {
		try {
			bean.setDisplay("vehDetails");
			List<String> typeBodyIdList = new ArrayList<String>();
			List<String> seatingCapacityList = new ArrayList<String>();
			List<String> vehicleUsageIdList = new ArrayList<String>();
			typeBodyIdList.add(StringUtils.isBlank(bean.getTypeBodyIdList().get(0))?bean.getTypeBody():bean.getTypeBodyIdList().get(0).toString());
			seatingCapacityList.add(StringUtils.isBlank(bean.getSeatingCapacityList().get(0))?bean.getSeatingCapacity():bean.getSeatingCapacityList().get(0).toString());
			vehicleUsageIdList.add(StringUtils.isBlank(bean.getVehicleUsageIdList().get(0))?bean.getVehicleUsage():bean.getVehicleUsageIdList().get(0).toString());
			bean.setTypeBodyIdList(typeBodyIdList);
			bean.setVehicleUsageIdList(vehicleUsageIdList);
			bean.setSeatingCapacityList(seatingCapacityList);		
			if(bean.getActionErrorsBean()==null) {
				bean.setActionErrorsBean(new ArrayList<String>());
			}
			service.addVehicleNew(bean);
			APiValidation();
			
			if(!hasActionErrors()) {
				if(StringUtils.isNotBlank(bean.getCustomerId())&&StringUtils.isBlank(bean.getLoginId())){
					service.setCustDetail(bean,"New");
				}else if(StringUtils.isNotBlank(bean.getLoginId())){
					service.setCustDetail(bean,"RegNewQuote");
				}
				bean.setDisplay("vehDetails");
				addActionMessage("Updated Successfully");
				bean.setIsVehicleEdit("N");
				//bean.setCustomerType(service.getCustomerType(bean));
				
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return "inputV1";
	}
			
//			
//			if(errorList==null || errorList.size()==0){
//				addActionMessage("Updated Successfully");
//				bean.setIsVehicleEdit("N");
//				bean.setCustomerType(service.getCustomerType(bean));
//			}else{
//				//addActionError("Update Failed");
//			}
//		}
//		catch(Exception exception) {
//			addActionError("Something Went Wrong in Update");
//			LogManager.debug(exception);
//			exception.printStackTrace();
//		}
//		return "inputV1";
//	}

	/*public String showPremiumAjax(){
		getRequest().setAttribute(DBConstants.FIELD, "showPremiumAjax");
		service.getPremiumInfo(bean);
		return DBConstants.DROPDOWN;
	}
	public String calcPremiumAjax(){
		getRequest().setAttribute(DBConstants.FIELD, "showPremiumAjax");
		service.getPremiumInfo(bean);
		return DBConstants.DROPDOWN;
	}
	public String subCalcPremiumAjax(){
		return showPolicyInfo();
	}*/
	public String saveVehicleAjax(){
		try {
			getRequest().setAttribute(DBConstants.FIELD, "vehicleEditAjax");
			addVehicleNew();
			String make="";
			String model="";
			String deleteVehicleId="";
			String typeBody="";
			String vehicleUsage="";
			String seatingCapacity="";
			
			make=StringUtils.isBlank(bean.getMakeIdList().get(0))?"":bean.getMakeIdList().get(0).toString();
			model=StringUtils.isBlank(bean.getModelIdList().get(0))?"":bean.getModelIdList().get(0).toString();
			deleteVehicleId=StringUtils.isBlank(bean.getVehicleIdList().get(0))?"":bean.getVehicleIdList().get(0).toString();
			typeBody=StringUtils.isBlank(bean.getTypeBodyIdList().get(0))?"":bean.getTypeBodyIdList().get(0).toString();
			vehicleUsage=StringUtils.isBlank(bean.getVehicleUsageIdList().get(0))?"":bean.getVehicleUsageIdList().get(0).toString();
			seatingCapacity=StringUtils.isBlank(bean.getSeatingCapacityList().get(0))?"":bean.getSeatingCapacityList().get(0).toString();
					
			bean.setMake(StringUtils.isBlank(bean.getMake())?make:bean.getMake());
			bean.setModel(StringUtils.isBlank(bean.getModel())?model:bean.getModel());
			bean.setDeleteVehicleId(StringUtils.isBlank(bean.getDeleteVehicleId())?deleteVehicleId:bean.getDeleteVehicleId());
			
			//bean.setTypeBody(StringUtils.isBlank(bean.getTypeBody())?typeBody:bean.getTypeBody());
			//bean.setVehicleUsage(StringUtils.isBlank(bean.getVehicleUsage())?vehicleUsage:bean.getVehicleUsage());
			bean.setSeatingCapacity(StringUtils.isBlank(bean.getSeatingCapacity())?seatingCapacity:bean.getSeatingCapacity());
			bean.setTypeBody(typeBody);
			bean.setVehicleUsage(vehicleUsage);
			//bean.setSeatingCapacity(seatingCapacity);
			
			if(!hasActionErrors()){
				//vehicleEditAjax();
				bean.setMode("vehSuccess");
				driverEditAjax();
			}
			else{
				vehicleEditAjax();
				bean.setReqFrom("add");
			}
				//service.editVehicleIdDetails(bean);
			//else
				//getVehicleTypeDetails();
			//bean.setReqFrom("addAjax");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return DBConstants.DROPDOWN;
	}
	
	public String driverUpdateAjax(){
		String forward=DBConstants.DROPDOWN;
		try {
			getRequest().setAttribute(DBConstants.FIELD, "driverEditAjax");
			driverUpdate();
			bean.setVehDtls(service.getVehicleDetailsByIdNew(bean.getApplicationNo(),bean.getProductId(),bean.getBranchCode(),bean.getDeleteVehicleId()));
			if(CollectionUtils.isNotEmpty(bean.getVehDtls())) {
			String vType=bean.getVehDtls().get(0).get("VEHICLE_TYPE")==null?"":bean.getVehDtls().get(0).get("VEHICLE_TYPE").toString();
			bean.setVehicleUsage(StringUtils.isBlank(bean.getVehicleUsage())?vType:bean.getVehicleUsage());
			}
			getNoClaimBonusList();
			if(!hasActionErrors()){
				//forward=getBackVehicle();
				bean.setMode("success");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return forward;
	}

//			
//			//bean.setVehDtls(service.getVehicleDetailsByIdNew(bean));
//			if(CollectionUtils.isNotEmpty(bean.getVehDtls())) {
//			vType=bean.getVehDtls().get(0).get("VEHICLE_TYPE")==null?"":bean.getVehDtls().get(0).get("VEHICLE_TYPE").toString();
//			}
//			bean.setVehicleUsage(StringUtils.isBlank(bean.getVehicleUsage())?vType:bean.getVehicleUsage());
//			if(!hasActionErrors()){
//				//forward=getBackVehicle();
//				bean.setMode("success");
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return forward;
//	}

	public String addCustomerDtl() {
		try {
			if(bean.getActionErrorsBean()==null) {
				bean.setActionErrorsBean(new ArrayList<String>());
			}
			List<String> errorList = service.addCustomerDtl(bean);
			setActionErrorList(errorList);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return INPUT;
	}
	
	public String addPolicyDtl() {
		try {
			if(bean.getActionErrorsBean()==null) {
				bean.setActionErrorsBean(new ArrayList<String>());
			}
			service.updateQuoteDetailsNew(bean);
			APiValidation();
			
			
			if(!hasActionErrors()) {
				if(StringUtils.isNotBlank(bean.getCustomerId())&&StringUtils.isBlank(bean.getLoginId())){
					service.setCustDetail(bean,"New");
				}else if(StringUtils.isNotBlank(bean.getLoginId())){
					service.setCustDetail(bean,"RegNewQuote");
				}
				bean.setDisplay("vehDetails");
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return INPUT;
	}

	public String getQuote() {
		try {
			mapLoginDetails();
			bean.setMulVehDtls(service.getMultiVehicleDetails(bean));
			if("User".equalsIgnoreCase(bean.getUserType()) || "B2C".equalsIgnoreCase(bean.getLoginType())){
				getValidate("captcha");
			}
			if(!"2".equals(bean.getPremiumType())) {
				addVehicle();
			}
			if(getActionErrors().isEmpty()) {
				if(!"getSave".equalsIgnoreCase(bean.getActionType())) {
					service.getQuoteInfo(bean, hasActionErrors());
					if("RA".equalsIgnoreCase(bean.getQuoteStatus()) || StringUtils.isNotBlank(bean.getEndTypeId())) {
						viewOptionCover();
					} else if("2".equals(bean.getPremiumType()) && !"RA".equalsIgnoreCase(bean.getQuoteStatus()) && !StringUtils.isNotBlank(bean.getEndTypeId())) {
						bean.setPolicyType(getText("MOTOR_TP_ID"));
						bean.setPolicyCover(new String[]{"0"});
						service.updSelectedPolicyDtls(bean);
						if(!"B2C".equalsIgnoreCase(bean.getLoginType())) {
							viewOptionCover();
						} else {
							return "redirectEditUser";
						}
					} else {
						comparisionDetails();
					}
				}
				else {
					bean.setDisplay("showQuoteInfo");
				}
				/*String smsRequired=new SmsEmailDAO().smsRequired(bean.getQuoteNo(),"quote");
				if("Y".equalsIgnoreCase(smsRequired)) {
					new SmsEmailUtil(SmsEmailUtil.GET_QUOTE, bean.getQuoteNo()).send();
					new SmsEmailUtil(SmsEmailUtil.GET_QUOTE_OPUSER, bean.getQuoteNo()).send();
				}
				new SmsEmailDAO().updateSmsStatus(bean.getQuoteNo(),"quote");*/
			}
		}
		catch(Exception exception) {
			LogManager.debug(exception);
		}
		return INPUT;
	}
	
	public String comparisionDetailsAjax() {
		getRequest().setAttribute(DBConstants.FIELD, "comparisionDetailsAjax");
		service.updateTPLiablity(bean);
		comparisionDetails();
		return DBConstants.DROPDOWN;
	}
	
	private void comparisionDetails() {
		try {
			service.updateCoveragesInfo(bean.getApplicationNo(),bean.getBranchCode(),bean.getProductId(),bean.getEndTypeId(),bean.getLoginId(),"C",bean.getUserType(),"",bean.getReferralYN());
			Map comparisionDetails = service.getComparisionDetails(bean);
			List comparisionPolicyType = (List) comparisionDetails.get("policyType");
			bean.setComparisionDetailsMap(comparisionDetails);
			if(comparisionPolicyType.size()>0) {
				bean.setDisplay("showPrSummary");
				
				// This Block For Display Premium Details ANDROID								
				if(DBConstants.DEVICETYPE_ANDROID.equals(bean.getDeviceType()) || DBConstants.DEVICETYPE_HYBRID.equals(bean.getDeviceType())) {
					/*List<Map<String,Object>> poltype=(List<Map<String,Object>>)comparisionPolicyType;
					HashMap<String, Map<String,Map>> rest=null;
					rest=new HashMap<String, Map<String,Map>>();								 
					for(int i=0;i<poltype.size();i++){				                    	
						Map<String,Map> values=new HashMap<String,Map>();				                    	
						List<Map<String,Object>> polgruop=(List<Map<String,Object>>)comparisionDetails.get("policyGroup");
						for(int j=0;j<polgruop.size();j++){ 
							List<Map<String,Object>> polcover=(List<Map<String,Object>>)comparisionDetails.get("policyCovers"+polgruop.get(j).get("GROUP_ID"));
							Map<String,String> temp=new HashMap<String,String>();
							int z = 0;
							for(int k=0;k<polcover.size();k++){ 
								List<Map<String, Object>> det=(List<Map<String, Object>>)comparisionDetails.get("policyCoverDetails"+polgruop.get(j).get("GROUP_ID")+poltype.get(i).get("X_ID")+polcover.get(k).get("Y_DATA_NAME"));
								if(det!=null && det.size()>0){			                        			 
									for(Map<String,Object> coverDet : det) {
										temp.put("body"+z, coverDet.get("DESCRIPTION")==null?"":coverDet.get("DESCRIPTION").toString());
										temp.put("amount"+z, coverDet.get("DATA_VALUE")==null?"":coverDet.get("DATA_VALUE").toString());
										temp.put("isselected"+z, coverDet.get("IS_SELECTED")==null?"":coverDet.get("IS_SELECTED").toString()); 
									}
									z++;
								}  
							}	
							values.put(polgruop.get(j).get("GROUP_DESC_ENGLISH").toString(),temp);
						}
						rest.put(poltype.get(i).get("X_DATA_NAME").toString(), values);
					}
					bean.setQuote(rest);
					HashMap<String, Map<String,Map>> rest2=new HashMap<String, Map<String,Map>>();
					Map<String,String> policyTypeRateMap = new HashMap<String,String>();
					//For Id
	
					for(int i=0;i<poltype.size();i++){				                    	
						Map<String,Map> values=new HashMap<String,Map>();
						Map<String,String> temp=new HashMap<String,String>();
	
	
						List<Map<String,Object>> polgruop=(List<Map<String,Object>>)comparisionDetails.get("policyGroup");
						for(int j=0;j<polgruop.size();j++){ 
							List<Map<String,Object>> polcover=(List<Map<String,Object>>)comparisionDetails.get("policyCovers"+polgruop.get(j).get("GROUP_ID"));
							//Map<String,String> temp=new HashMap<String,String>();
							int z = 0;
							for(int k=0;k<polcover.size();k++){ 
								List<Map<String, Object>> det=(List<Map<String, Object>>)comparisionDetails.get("policyCoverDetails"+polgruop.get(j).get("GROUP_ID")+poltype.get(i).get("X_ID")+polcover.get(k).get("Y_DATA_NAME"));
								if(det!=null && det.size()>0){			                        			 
									for(Map<String,Object> coverDet : det) {			                        				 
										//temp.put("ID"+z,det.get(d).get("Y_ID").toString());
										temp.put(coverDet.get("DESCRIPTION")==null?"":coverDet.get("DESCRIPTION").toString(),coverDet.get("Y_ID")==null?"":coverDet.get("Y_ID").toString());
									}
									z++;
								}  
							}
							//values.put(polgruop.get(j).get("GROUP_ID").toString(),temp);
							//values.put(polgruop.get(j).get("GROUP_DESC_ENGLISH").toString(),temp);
						}
						values.put(poltype.get(i).get("X_ID").toString(),temp);
						policyTypeRateMap.put(poltype.get(i).get("X_ID").toString(),poltype.get(i).get("RATE").toString());
						//rest2.put(poltype.get(i).get("X_ID").toString(), values);
						rest2.put(poltype.get(i).get("X_DATA_NAME").toString(), values);
					}
					bean.setQuoteId(rest2);
					bean.setPolicyTypeRateMap(policyTypeRateMap);
					System.out.println(rest);*/
				} else {
					//request.setAttribute("referral", bean.getReferralMsg());
					getRequest().setAttribute("comparisionDetails", comparisionDetails);
				}
			}
			else {
				if(DBConstants.DEVICETYPE_ANDROID.equals(bean.getDeviceType()) || DBConstants.DEVICETYPE_HYBRID.equals(bean.getDeviceType())) {
					bean.getActionErrorsBean().add(ResourceBundleUtil.findText(bean.getProductId(),"error.motor.noPolicyType"));
				} else {
					addActionError(getText("error.motor.noPolicyType"));
				}
			}
		} catch(Exception exception) {
			LogManager.debug(exception);
		}
	}
	/*public List<CoverageBean> getOptCoverList() {
		return service.getOptCoverList(bean.getApplicationNo()); 
	}*/
	public String insertOptionCover() {
		String forward=INPUT;
		bean.setBranchCode(StringUtils.isBlank(bean.getBranchCode())?(String)getSession().get("branchCode"):bean.getBranchCode());
		try {
			mapLoginDetails();
			if(StringUtils.isBlank(bean.getOtp())){
				
				if(DBConstants.DEVICETYPE_ANDROID.equals(bean.getDeviceType()) || DBConstants.DEVICETYPE_HYBRID.equals(bean.getDeviceType())) {
					Object[] optionalCovers = bean.getPolicyCoverMap().values().toArray();
					String[] optionalCoversArray = new String[optionalCovers.length];
					for(int i=0 ; i<optionalCovers.length ; i++) {
						optionalCoversArray[i] = (String) optionalCovers[i];
					}
					bean.setPolicyCover(optionalCoversArray);
				}
				else {
					String policyType = getRequest().getParameter("policyType")==null?"":getRequest().getParameter("policyType");
					String[] optionCovers = getRequest().getParameterValues("optionalCovers"+policyType);
					String[] optionalCovers = new String[0];
					int arrayCount=0;
					for(int i=0;i<optionCovers.length;i++){
						if(optionCovers[i]!=null && !"".equalsIgnoreCase(optionCovers[i].trim())){
							arrayCount++;
						}
					}
					optionalCovers = new String[arrayCount];
					arrayCount = 0;
					for(int i=0;i<optionCovers.length;i++){
						if(optionCovers[i]!=null && !"".equalsIgnoreCase(optionCovers[i].trim())){
							optionalCovers[arrayCount] = optionCovers[i];
							arrayCount++;
						}
					}
					bean.setRate(getRequest().getParameter("coverRate"+policyType)==null?"0":getRequest().getParameter("coverRate"+policyType));
					bean.setPolicyType(policyType);
					bean.setPolicyCover(optionalCovers);
				}
				bean.setLoadOrDiscPremium(StringUtils.isBlank(bean.getLoadOrDiscPremium())?"0":bean.getLoadOrDiscPremium());
				if(StringUtils.isNotEmpty(bean.getIssuer()) && StringUtils.isNotBlank(bean.getBrokerCode())) {
					bean.setLoginId(commonDAO.getSingleInfo("loginId", new String[]{bean.getBrokerCode()}));
				}
				/*service.insertOptionCover(bean);
				service.insertPolicyTypePremium(bean);*/
				service.updSelectedPolicyDtls(bean);
				
			}
			if(!"B2C".equalsIgnoreCase(bean.getLoginType())) {
				if(StringUtils.isBlank(service.getPolicyReferralMsgs(bean.getApplicationNo())))
				{
					service.getQuoteInfo(bean, hasActionErrors());
					service.getPremiumInfo(bean);
					service.getUpdatePremiumInfo(bean);
					
					service.getAdditionalVehicleDetails(bean);
					
					service.setCustDetail(bean,"Registered");
					bean.setCity(StringUtils.isBlank(bean.getCity())?ResourceBundleUtil.findDefaultText("MOTOR_DEFAULT_CITY"):bean.getCity());
					/*String premiumInfo[][]=service.collectPremiumInfo(bean);
				    String minpremium=service.collectMinPremium(bean);*/
				    
				    /*bean.setAdminReferalRemarks(request.getParameter("adminReferalRemarks")==null?"":request.getParameter("adminReferalRemarks"));
				    bean.setRemarks(request.getParameter("Remarks")==null?"A":request.getParameter("Remarks"));
				    bean.updateReferralStatus(bean);*/
					
					if(DBConstants.DEVICETYPE_ANDROID.equals(bean.getDeviceType()) || DBConstants.DEVICETYPE_HYBRID.equals(bean.getDeviceType())) {
						String groupId = "";
						double totalCoverPremium = 0;
						List<Map<String,String>> premiumGroupIdList = new ArrayList<Map<String,String>>();
						
						for(int i=0 ; i<bean.getPremiumInfoList().size() ; i++) {
							Map<String,Object> tempMap = (Map<String,Object>) bean.getPremiumInfoList().get(i);
							String tempGroupId = tempMap.get("GROUP_ID")==null?"":tempMap.get("GROUP_ID").toString();
							totalCoverPremium += Double.valueOf(tempMap.get("PREMIUM")==null?"0":tempMap.get("PREMIUM").toString());
							
							if(!groupId.equals(tempGroupId)) {
								Map<String,String> premiumMap = new HashMap<String, String>();
								premiumMap.put("GROUP_ID", tempGroupId);
								premiumMap.put("GROUP_DESC_ENGLISH", tempMap.get("GROUP_DESC_ENGLISH")==null?"":tempMap.get("GROUP_DESC_ENGLISH").toString());
								premiumGroupIdList.add(premiumMap);
								
								groupId = tempGroupId;
							}
						}
						
						bean.setPremiumGroupIdList(premiumGroupIdList);
						bean.setTotalCoverPremium(String.valueOf(totalCoverPremium));
						bean.setTotalPremium(String.valueOf(totalCoverPremium+Double.valueOf(bean.getPolicyFee())));
					}
					bean.setDisplay("policyInfo");
				}else 
				{
					if(!"admin".equalsIgnoreCase(bean.getUser())) {
						bean.setReferralMsg(service.getPolicyReferralMsgs(bean.getApplicationNo()));
						if(!DBConstants.DEVICETYPE_ANDROID.equals(bean.getDeviceType()) && !DBConstants.DEVICETYPE_HYBRID.equals(bean.getDeviceType())) {
							getSession().put("QuoteNo",bean.getQuoteNo());
							getRequest().setAttribute("RefStatus","Referal");
							try {
									new com.maan.Mail.controller.MotorMailController().processRequest(getRequest(), getResponse());
							}
							catch(Exception e) {
								LogManager.debug(e);
							}
							getSession().remove("QuoteNo");
						}
					}
					forward="policyInfo";	
				}
			}
			else {
				viewOptionCover();
				//forward="redirectEditUser";
				//forward="redirectOtpUser";
				otpLogin();
			}
		}
		catch(Exception exception) {
			LogManager.debug(exception);
		}
		return forward;
	}
	
	public String viewOptionCover() {
		mapLoginDetails();
		String forward=INPUT;
		try {
			if(StringUtils.isBlank(service.getPolicyReferralMsgs(bean.getApplicationNo()))) {
				service.getQuoteInfo(bean, hasActionErrors());
				service.getPremiumInfo(bean);
				service.getUpdatePremiumInfo(bean);
				service.getAdditionalVehicleDetails(bean);
				bean.setDisplay("policyInfo");
			} else {
				bean.setReferralMsg(service.getPolicyReferralMsgs(bean.getApplicationNo()));
				if(!DBConstants.DEVICETYPE_ANDROID.equals(bean.getDeviceType()) && !DBConstants.DEVICETYPE_HYBRID.equals(bean.getDeviceType())) {
					getSession().put("QuoteNo",bean.getQuoteNo());
					getRequest().setAttribute("RefStatus","Referal");
					try {
							new com.maan.Mail.controller.MotorMailController().processRequest(getRequest(), getResponse());
					}
					catch(Exception e) {
						LogManager.debug(e);
					}
					getSession().remove("QuoteNo");
				}
				service.getQuoteInfo(bean, hasActionErrors());
				service.getPremiumInfo(bean);
				service.getUpdatePremiumInfo(bean);
				service.getAdditionalVehicleDetails(bean);
				bean.setDisplay("policyInfo");
				forward="policyInfo";	
			}
		}
		catch(Exception e) {
			LogManager.debug(e);
		}
		return forward;
	}
	
	public String viewOptionCoverNew() {
		mapLoginDetails();
		String forward="inputNew";
		try {
			if(StringUtils.isBlank(service.getPolicyReferralMsgs(bean.getApplicationNo()))) {
				service.getQuoteInfo(bean, hasActionErrors());
				service.getPremiumInfo(bean);
				service.getUpdatePremiumInfo(bean);
				service.getAdditionalVehicleDetails(bean);
				bean.setDisplay("policyInfo");
			} else {
				bean.setReferralMsg(service.getPolicyReferralMsgs(bean.getApplicationNo()));
				if(!DBConstants.DEVICETYPE_ANDROID.equals(bean.getDeviceType()) && !DBConstants.DEVICETYPE_HYBRID.equals(bean.getDeviceType())) {
					getSession().put("QuoteNo",bean.getQuoteNo());
					getRequest().setAttribute("RefStatus","Referal");
					try {
							new com.maan.Mail.controller.MotorMailController().processRequest(getRequest(), getResponse());
					}
					catch(Exception e) {
						LogManager.debug(e);
					}
					getSession().remove("QuoteNo");
				}
				service.getQuoteInfo(bean, hasActionErrors());
				service.getPremiumInfo(bean);
				service.getUpdatePremiumInfo(bean);
				service.getAdditionalVehicleDetails(bean);
				bean.setDisplay("policyInfo");
				forward="inputNew";	
			}
		}
		catch(Exception e) {
			LogManager.debug(e);
		}
		return forward;
	}
	
	public String showSummarry()
	{
		try {
			bean.setDisplay("showPrSummary");
			service.getQuoteInfo(bean, hasActionErrors());
			service.getPremiumInfo(bean);
			//request.setAttribute("referral", bean.getReferralMsg());
			getRequest().setAttribute("comparisionDetails", service.getComparisionDetails(bean));
		}
		catch(Exception exception) {
			LogManager.debug(exception);
		}
		return INPUT;
	}
	/*public String updateCovRate() {
		try {
			service.updateCovRate(bean);
			editCovRate();
			if("getQuote".equalsIgnoreCase(bean.getActionType())) {
				if(StringUtils.isBlank(bean.getEndTypeId())) {
					bean.setVehicleId("");
				}
				bean.setDisplay("policyInfo");
			}
			else {
				bean.setDisplay("editCovRate");
			}
			service.getQuoteInfo(bean, hasActionErrors());
			service.getPremiumInfo(bean);
			
			service.getUpdatePremiumInfo(bean);
		}
		catch(Exception exception) {
			LogManager.debug(exception);
		}
		return INPUT;
	}*/
	public String getGeratePolicy() {
		mapLoginDetails();
		String result="";
		String forward=INPUT;
		try {
			if(!"admin".equals(bean.getUser()) && !"Y".equals(bean.getReferal())){
				getValidate("customerDetails");
				//getValidate("AddVehicleDetails");
			}
			getValidate("getPolicy");
			if("Y".equalsIgnoreCase(bean.getGeneratePolicyYN())){
				if("Y".equalsIgnoreCase(bean.getInstallmentYN())){
					service.getInstallmentDetail(bean);
				}
				getValidate("paymentDetails");
				getValidate("documents");
			}
			if(StringUtils.isNotBlank(bean.getApplicationNo()) && StringUtils.isBlank(bean.getCustomerId()))
				service.getCustomerId(bean);
			if (getActionErrors().isEmpty() && (bean.getActionErrorsBean() == null || bean.getActionErrorsBean().isEmpty())) {
				//bean.setCustnrc(bean.getCustnrc1()+"/"+bean.getCustnrc2()+"/"+bean.getCustnrc3());
				commonDAO.updateInstallmentDetail(bean.getQuoteNo(),"HSS",bean.getInstallmentYN());
				
				if(!"admin".equalsIgnoreCase(bean.getUserType()) && !"Y".equals(bean.getReferal()) && StringUtils.isBlank(bean.getQuoteStatus())){
					bean.setCustomerId(new CustomerService().insertCustomerDetails(bean,"detailQuote"));
				}
				
				if("admin".equals(bean.getUser()) || "Y".equals(bean.getReferal())){
					//Admin Referral accepting
					result=service.getAdminReferralUpdation(bean);
					if("SUCCESS".equalsIgnoreCase(result)){
						/*session.put("QuoteNo",travelBean.getQuoteNo());
						request.setAttribute("reqFrom",travelBean.getUser());
						request.setAttribute("quote_status",travelBean.getReferralMsg());
						request.setAttribute("adminReferalRemarks",travelBean.getAdminRemarks());
						try{
								new com.maan.Home.MasterController.HomeMailController().processResult(request,response);
						}catch(Exception e)
						{
							LogManager.debug(e);
						}
						session.remove("QuoteNo");*/
						bean.setDisplay("showRefInfo");
					}else {
						if(DBConstants.DEVICETYPE_ANDROID.equals(bean.getDeviceType()) || DBConstants.DEVICETYPE_HYBRID.equals(bean.getDeviceType())) {
							bean.getActionErrorsBean().add(ResourceBundleUtil.findText(bean.getProductId(),"error.travel.update"));
						} else {
							addActionError(getText("error.travel.update"));
						}
					}
					if("admin".equalsIgnoreCase(bean.getUser()))
						forward="adminView";
					else if("Y".equalsIgnoreCase(bean.getReferal())) {
						forward="referalView";
					}
					
				} else {
					//service.updateAddVehicleDetails(bean);
					if("N".equalsIgnoreCase(bean.getInstallmentYN()) && service.installmentcount(bean)> 0 ){
						deleteinstallment();
					}
					forward = updatePaymentRequest();
					if(hasActionErrors()){
						service.getQuoteInfo(bean, hasActionErrors());
						service.getPremiumInfo(bean);
					}
				}
			} else {
				//bean.setCustdob(StringUtils.isBlank(bean.getCustdob())?"":dateFormat(bean.getCustdob()));
				//service.setCustDetail(bean, "NewQuote");
				
				service.getPremiumInfo(bean);
				bean.setCity(StringUtils.isBlank(bean.getCity())?ResourceBundleUtil.findDefaultText("MOTOR_DEFAULT_CITY"):bean.getCity());
				//forward="inputNew";
			}
			service.getQuoteInfo(bean, hasActionErrors());
			if("admin".equalsIgnoreCase(bean.getUser()))
				forward="adminView";
			else if("Y".equalsIgnoreCase(bean.getReferal())) {
				forward="referalView";
			}
			if(hasActionErrors()){
				service.setCustDetail(bean, "NewQuote");
			}
			bean.setCustNameLabel(bean.getCustomerName());
			bean.setCustLastNameLabel(bean.getCustLastName());
			bean.setCustEmailLabel(bean.getEmail());
		}
		catch(Exception exception) {
			LogManager.debug(exception);
			if(DBConstants.DEVICETYPE_ANDROID.equals(bean.getDeviceType()) || DBConstants.DEVICETYPE_HYBRID.equals(bean.getDeviceType())) {
				bean.getActionErrorsBean().add(ResourceBundleUtil.findText(bean.getProductId(),"error.motor.update"));
			} else {
				addActionError(getText("error.motor.update"));
			}
		}
		return forward;
	}
	
	private String updatePaymentRequest() throws Exception {
		String forward = INPUT;
		String mobNum="";
		if("Y".equalsIgnoreCase(bean.getQuoteEmailYN())){
			new SmsEmailUtil(SmsEmailUtil.EMAIL_QUOTE, bean.getQuoteNo(),bean.getProductId()).send();
			forward = "policyInfo";
		}else{
			String result=service.getGeratePolicy(bean);
			if("SUCCESS".equalsIgnoreCase(result)) {
				if("Y".equalsIgnoreCase(bean.getGeneratePolicyYN())) {
					//service.updatePaymentDetails(bean);
					if(DBConstants.PAYMENT_MTN_MOBILE_MONEY.equalsIgnoreCase(bean.getModeOfPayment()))
						mobNum=bean.getMtnMobileNo();
					else if(DBConstants.PAYMENT_AIRTEL_MOBILE_MONEY.equalsIgnoreCase(bean.getModeOfPayment()))
						mobNum=bean.getAirtelMoneyNumber();
					
					bean.setMerchant_reference(
						new PaymentDAO().insertPaymentDetails(
							bean.getModeOfPayment(), bean.getChequeNo(), bean.getChequeDate(), bean.getChequeAmount(),
							bean.getTotalPremium(),bean.getBankName(), bean.getMicrCode(), bean.getCashDepositBank(),
							bean.getCashAmount(), bean.getCashChellanNo(), bean.getCashInstrumentDate(), bean.getApplicationNo(), bean.getQuoteNo(),
							bean.getProductId(), bean.getMerchant_reference(), bean.getEmail(), bean.getCustomerName(), bean.getBranchCode(),
							bean.getCurrencyType(), bean.getInstallmentYN(), bean.getMobileNo(), bean.getDeviceType(), mobNum
						)
					);
			
					if(DBConstants.PAYMENT_CHEQUE.equalsIgnoreCase(bean.getModeOfPayment()) || DBConstants.PAYMENT_CASH.equalsIgnoreCase(bean.getModeOfPayment()) || DBConstants.PAYMENT_ZAMTEL.equalsIgnoreCase(bean.getModeOfPayment())) {
						forward = updatePolicyInfo();
					} else if(DBConstants.PAYMENT_MTN_MOBILE_MONEY.equalsIgnoreCase(bean.getModeOfPayment())){
						try{
							if(StringUtils.isNotBlank(bean.getMerchant_reference())){
								MtnService ms = new MtnService(bean.getQuoteNo(),"",bean.getMerchant_reference(),bean.getProductId());
								Map<String, Object> mpd = ms.getPaymentDetails();
								if(mpd!=null && mpd.size()>0){
									String quoteNo = mpd.get("QUOTE_NO")==null?"":mpd.get("QUOTE_NO").toString();
									String productId = mpd.get("PRODUCT_ID")==null?"":mpd.get("PRODUCT_ID").toString();
									String premium = mpd.get("PREMIUM")==null?"":mpd.get("PREMIUM").toString();
									String merchantRefNo = mpd.get("MERCHANT_REFERENCE")==null?"":mpd.get("MERCHANT_REFERENCE").toString();
									String currencyType = mpd.get("CURRENCY_TYPE")==null?"":mpd.get("CURRENCY_TYPE").toString();
									String referenceNo = mpd.get("REFERENCE_NO")==null?"":mpd.get("REFERENCE_NO").toString();
									String mobileNo = mpd.get("MTN_MOBILE_NO")==null?"":mpd.get("MTN_MOBILE_NO").toString();
									
									
									if(StringUtils.isNotBlank(quoteNo) && StringUtils.isNotBlank(premium)
											&& StringUtils.isNotBlank(merchantRefNo) && StringUtils.isNotBlank(currencyType)
											&& StringUtils.isNotBlank(referenceNo) && StringUtils.isNotBlank(mobileNo)){
										ReqToPayIpModel rtp = new ReqToPayIpModel();
										rtp.setExternalId(quoteNo);
										rtp.setCurrency(currencyType);
										rtp.setAmount(premium);
										rtp.setPayeeNote("Payment for Madison");
										rtp.setPayerMessage("Pay for Insurance");
										Payer p = new Payer();
										p.setPartyIdType("MSISDN");
										if(!mobileNo.startsWith("26")){
											mobileNo = "26"+mobileNo;
										}
										p.setPartyId(mobileNo);
										rtp.setPayer(p);
										
										MtnService ms1 = new MtnService(quoteNo, referenceNo,
												merchantRefNo, productId);
										if(ms1.requestToPay(rtp)){
											try{
												Thread.sleep(5000);
											}catch(Exception e){
												e.printStackTrace();
											}
											MtnService ms2 = new MtnService(quoteNo, referenceNo,
													merchantRefNo, productId);
											String payStatus = ms2.paymentStatus();
											 if("pending".equalsIgnoreCase(payStatus)
													|| "success".equalsIgnoreCase(payStatus)
													|| "successful".equalsIgnoreCase(payStatus)){
												forward = "mtnWaitingPage";
											}else{
												addActionError("MTN Payment Failed. Please try again later");
											}
										}else{
											throw new Exception(merchantRefNo+" Request Sending Failed");
										}
									}else{
										throw new Exception(merchantRefNo+" Mtn Payment Details Not Found");
									}
								}
							}else{
								throw new Exception(bean.getMerchant_reference()+" Merchant Reference No is Empty");
							}
						}catch(Exception e){
							addActionError("Something went wrong. please try again with other payment method.");
							e.printStackTrace();
						}
					}else if(DBConstants.PAYMENT_AIRTEL_MOBILE_MONEY.equalsIgnoreCase(bean.getModeOfPayment())){
						try{
							if(StringUtils.isNotBlank(bean.getMerchant_reference())){
								AirtelService as = new AirtelService(bean.getQuoteNo(),"",bean.getMerchant_reference(),bean.getProductId());
								Map<String, Object> mpd = as.getPaymentDetails();
								if(mpd!=null && mpd.size()>0){
									String quoteNo = mpd.get("QUOTE_NO")==null?"":mpd.get("QUOTE_NO").toString();
									String productId = mpd.get("PRODUCT_ID")==null?"":mpd.get("PRODUCT_ID").toString();
									String premium = mpd.get("PREMIUM")==null?"":mpd.get("PREMIUM").toString();
									String merchantRefNo = mpd.get("MERCHANT_REFERENCE")==null?"":mpd.get("MERCHANT_REFERENCE").toString();
									String currencyType = mpd.get("CURRENCY_TYPE")==null?"":mpd.get("CURRENCY_TYPE").toString();
									String referenceNo = mpd.get("REFERENCE_NO")==null?"":mpd.get("REFERENCE_NO").toString();
									String mobileNo = mpd.get("MTN_MOBILE_NO")==null?"":mpd.get("MTN_MOBILE_NO").toString();
									
									if(StringUtils.isNotBlank(quoteNo) && StringUtils.isNotBlank(premium)
											&& StringUtils.isNotBlank(merchantRefNo) && StringUtils.isNotBlank(currencyType)
											&& StringUtils.isNotBlank(mobileNo)){
										ReqToPayIpModelAirtel rtp = new ReqToPayIpModelAirtel();
										rtp.setReference("Madison Gen");
										
										Subscriber sub = new Subscriber();
										sub.setCountry("ZM");
										sub.setCurrency(currencyType);
										sub.setMsisdn(Integer.parseInt(mobileNo));
										
										Transaction txn = new Transaction();
										txn.setAmount("1");
										txn.setCountry("ZM");
										txn.setCurrency(currencyType);
										txn.setId(merchantRefNo);
										
										rtp.setSubscriber(sub);
										rtp.setTransaction(txn);
										
										AirtelService as1 = new AirtelService(quoteNo, referenceNo,
												merchantRefNo, productId);
										if(as1.requestToPay(rtp)){
											try{
												Thread.sleep(5000);
											}catch(Exception e){
												e.printStackTrace();
											}
											AirtelService ms2 = new AirtelService(quoteNo, referenceNo,
													merchantRefNo, productId);
											String payStatus = ms2.paymentStatus(rtp);
											 if("pending".equalsIgnoreCase(payStatus)
													|| "success".equalsIgnoreCase(payStatus)
													|| "successful".equalsIgnoreCase(payStatus)
													|| "TIP".equalsIgnoreCase(payStatus)
													|| "TS".equalsIgnoreCase(payStatus)){
												forward = "airtelWaitingPage";
											}else{
												addActionError("Airtel Payment Failed. Please try again later");
											}
										}else{
											throw new Exception(merchantRefNo+" Request Sending Failed");
										}
									}else{
										throw new Exception(merchantRefNo+" Airtel Payment Details Not Found");
									}
								}
							}else{
								throw new Exception(bean.getMerchant_reference()+" Merchant Reference No is Empty");
							}
						}catch(Exception e){
							addActionError("Something went wrong. please try again with other payment method.");
							e.printStackTrace();
						}
					}
					else {
						//getSession().put("merchant_reference", bean.getMerchant_reference());
						forward = "onlinePaymentReq";
					}
				} 
				else {
					forward = "policyInfo";
				}
			} else {
				if(DBConstants.DEVICETYPE_ANDROID.equals(bean.getDeviceType()) || DBConstants.DEVICETYPE_HYBRID.equals(bean.getDeviceType())) {
					bean.getActionErrorsBean().add(ResourceBundleUtil.findText(bean.getProductId(),"error.motor.update"));
				} else {
					addActionError(getText("error.motor.update"));
				}
			}
		}
		return forward;
	}
	public String generateReferral(){
		mapLoginDetails();
		String result="";
		String forward="inputV1";
		try {
			bean.setBrokerBranchList(commonDAO.getSubBranchList(bean.getLoginId(),bean.getLoginBranch()));
			bean.setBrokerUserList(commonDAO.getBrokerUserList(bean.getQuoteNo(),bean.getLoginBranch()));
			if(!"admin".equals(bean.getUser())){
				getValidate("customerDetails");
				//getValidate("AddVehicleDetails");
			}
			getValidate("getPolicy");
			if("Y".equalsIgnoreCase(bean.getGeneratePolicyYN())){
				if("Y".equalsIgnoreCase(bean.getInstallmentYN())){
					service.getInstallmentDetail(bean);
				}
				//getValidate("paymentDetails");
				getValidate("documents");
			}
			if (getActionErrors().isEmpty() && (bean.getActionErrorsBean() == null || bean.getActionErrorsBean().isEmpty())) {
				//bean.setCustnrc(bean.getCustnrc1()+"/"+bean.getCustnrc2()+"/"+bean.getCustnrc3());
				service.updatePolDate(bean);
				commonDAO.updateInstallmentDetail(bean.getQuoteNo(),"HSS",bean.getInstallmentYN());
				bean.setCustomerId(new CustomerService().insertCustomerDetails(bean,"detailQuote"));
				if("admin".equals(bean.getUser())){
					//Admin Referral accepting
					result=service.getAdminReferralUpdation(bean);
					if("SUCCESS".equalsIgnoreCase(result)){
						/*session.put("QuoteNo",travelBean.getQuoteNo());
						request.setAttribute("reqFrom",travelBean.getUser());
						request.setAttribute("quote_status",travelBean.getReferralMsg());
						request.setAttribute("adminReferalRemarks",travelBean.getAdminRemarks());
						try{
								new com.maan.Home.MasterController.HomeMailController().processResult(request,response);
						}catch(Exception e)
						{
							LogManager.debug(e);
						}
						session.remove("QuoteNo");*/
						bean.setDisplay("showRefInfo");
					}else {
						if(DBConstants.DEVICETYPE_ANDROID.equals(bean.getDeviceType()) || DBConstants.DEVICETYPE_HYBRID.equals(bean.getDeviceType())) {
							bean.getActionErrorsBean().add(ResourceBundleUtil.findText(bean.getProductId(),"error.travel.update"));
						} else {
							addActionError(getText("error.travel.update"));
						}
					}
					
				} else {
					//service.updateAddVehicleDetails(bean);
					if("N".equalsIgnoreCase(bean.getInstallmentYN()) && service.installmentcount(bean)> 0 ){
						deleteinstallment();
					}
					//forward = updatePaymentRequest();
					String resultNew=service.getGeratePolicyNew(bean);
					if("SUCCESS".equalsIgnoreCase(resultNew)) {
						try{
							NotificationService ns = new NotificationService("referral", bean.getQuoteNo(),
									bean.getPolicyNo(), bean.getLoginId(), bean.getUserType());
							ns.send();
						}catch(Exception e){
							LogManager.info("Exception @ MotorAction.generateReferral(): "+e);
							e.printStackTrace();
						}
						forward="policyInfo";
					}
					if(hasActionErrors()){
						service.getQuoteInfo(bean, hasActionErrors());
						service.getPremiumInfo(bean);
					}
				}
			} else {
				//bean.setCustdob(StringUtils.isBlank(bean.getCustdob())?"":dateFormat(bean.getCustdob()));
				//service.setCustDetail(bean, "NewQuote");
				
				service.getPremiumInfo(bean);
				bean.setCity(StringUtils.isBlank(bean.getCity())?ResourceBundleUtil.findDefaultText("MOTOR_DEFAULT_CITY"):bean.getCity());
				forward="inputNew";
			}
			//service.getQuoteInfo(bean, hasActionErrors());
			service.getLabelInfo(bean, hasActionErrors());
		}
		catch(Exception exception) {
			LogManager.debug(exception);
			if(DBConstants.DEVICETYPE_ANDROID.equals(bean.getDeviceType()) || DBConstants.DEVICETYPE_HYBRID.equals(bean.getDeviceType())) {
				bean.getActionErrorsBean().add(ResourceBundleUtil.findText(bean.getProductId(),"error.motor.update"));
			} else {
				addActionError(getText("error.motor.update"));
			}
		}
		return forward;
	
	}
	
	public String generateDraft(){
		mapLoginDetails();
		String result="";
		String forward="inputV1";
		try {
			if(!"admin".equals(bean.getUser())){
				getValidate("customerDetails");
				//getValidate("AddVehicleDetails");
			}
			getValidate("getPolicy");
			
			if (getActionErrors().isEmpty() && (bean.getActionErrorsBean() == null || bean.getActionErrorsBean().isEmpty())) {
				//bean.setCustnrc(bean.getCustnrc1()+"/"+bean.getCustnrc2()+"/"+bean.getCustnrc3());
				if("N".equalsIgnoreCase(bean.getReferralYN()))
					service.updateReferralRemarks(bean.getApplicationNo(),"");
				commonDAO.updateInstallmentDetail(bean.getQuoteNo(),"HSS",bean.getInstallmentYN());
				bean.setCustomerId(new CustomerService().insertCustomerDetails(bean,"detailQuote"));
				if("admin".equals(bean.getUser())){
					//Admin Referral accepting
					result=service.getAdminReferralUpdation(bean);
					if("SUCCESS".equalsIgnoreCase(result)){
						
						bean.setDisplay("showRefInfo");
					}else {
						if(DBConstants.DEVICETYPE_ANDROID.equals(bean.getDeviceType()) || DBConstants.DEVICETYPE_HYBRID.equals(bean.getDeviceType())) {
							bean.getActionErrorsBean().add(ResourceBundleUtil.findText(bean.getProductId(),"error.travel.update"));
						} else {
							addActionError(getText("error.travel.update"));
						}
					}
					
				} else {
					//service.updateAddVehicleDetails(bean);
					if("N".equalsIgnoreCase(bean.getInstallmentYN()) && service.installmentcount(bean)> 0 ){
						deleteinstallment();
					}
					//forward = updatePaymentRequestNew();
					
					if("Y".equalsIgnoreCase(bean.getQuoteEmailYN())){
						new SmsEmailUtil(SmsEmailUtil.EMAIL_QUOTE, bean.getQuoteNo(),bean.getProductId()).send();
						forward = "policyInfo";
					}
					else{
						forward = "policyInfo";
					}
				}
			} else {
				//bean.setCustdob(StringUtils.isBlank(bean.getCustdob())?"":dateFormat(bean.getCustdob()));
				//service.setCustDetail(bean, "NewQuote");
				
				service.getPremiumInfo(bean);
				bean.setCity(StringUtils.isBlank(bean.getCity())?ResourceBundleUtil.findDefaultText("MOTOR_DEFAULT_CITY"):bean.getCity());
				forward="inputNew";
			}
			service.getLabelInfo(bean, hasActionErrors());
		}
		catch(Exception exception) {
			LogManager.debug(exception);
			if(DBConstants.DEVICETYPE_ANDROID.equals(bean.getDeviceType()) || DBConstants.DEVICETYPE_HYBRID.equals(bean.getDeviceType())) {
				bean.getActionErrorsBean().add(ResourceBundleUtil.findText(bean.getProductId(),"error.motor.update"));
			} else {
				addActionError(getText("error.motor.update"));
			}
		}
		return forward;
	}
	
	public String geratePolicyNew() {
		String result="";
		String forward="inputV1";
		try {
			if(StringUtils.isNotBlank(bean.getQuoteNo()))
			bean.setLoginId(commonDAO.getLoginIdByQuote(bean.getQuoteNo()));
			mapLoginDetails();
			bean.setBrokerBranchList(commonDAO.getSubBranchList(bean.getLoginId(),bean.getLoginBranch()));
			bean.setBrokerUserList(commonDAO.getBrokerUserList(bean.getQuoteNo(),bean.getLoginBranch()));
			bean.setMtnMobileNo(StringUtils.isBlank(bean.getMtnMobileNo())?bean.getMobileNo():bean.getMtnMobileNo());
			bean.setAirtelMoneyNumber(StringUtils.isBlank(bean.getAirtelMoneyNumber())?(bean.getMobileNo().substring(1, 10)):bean.getAirtelMoneyNumber());
			if(!"admin".equals(bean.getUser())){
				getValidate("customerDetails");
				//getValidate("AddVehicleDetails");
			}
			getValidate("getPolicy");
			if("Y".equalsIgnoreCase(bean.getGeneratePolicyYN())){
				if("Y".equalsIgnoreCase(bean.getInstallmentYN())){
					service.getInstallmentDetail(bean);
					if(StringUtils.isBlank(bean.getInsIntialAmount()))
						addActionError("Installment Detail Not Found, Please Re-Calculate");
				}if("Y".equalsIgnoreCase(bean.getReferQuoteYN())){
					if(StringUtils.isBlank(bean.getBrokeruser()))
						addActionError("Please Select Broker/User");
				}
				//getValidate("paymentDetails");
				getValidate("documents");
			}
			if (getActionErrors().isEmpty() && (bean.getActionErrorsBean() == null || bean.getActionErrorsBean().isEmpty())) {
				//bean.setCustnrc(bean.getCustnrc1()+"/"+bean.getCustnrc2()+"/"+bean.getCustnrc3());
				if("N".equalsIgnoreCase(bean.getReferralYN()))
					service.updateReferralRemarks(bean.getApplicationNo(),"");
				
				service.updatePolDate(bean);
				commonDAO.updateBrokerBranch(StringUtils.isBlank(bean.getBrokerBranch())?bean.getLoginBranch():bean.getBrokerBranch(),bean.getApplicationNo(),bean.getQuoteNo());
				commonDAO.updateInstallmentDetail(bean.getQuoteNo(),"HSS",bean.getInstallmentYN());
				if("Y".equalsIgnoreCase(bean.getReferQuoteYN())){
					commonDAO.updateReferBroker(bean.getBrokeruser(),bean.getApplicationNo(),bean.getQuoteNo());
				}
				bean.setCustomerId(new CustomerService().insertCustomerDetails(bean,"detailQuote"));
				/*if(StringUtils.isNotBlank(bean.getApplicationNo()))
					updateCustTypeMotorDtl();*/
				if("admin".equals(bean.getUser())){
					//Admin Referral accepting
					result=service.getAdminReferralUpdation(bean);
					if("SUCCESS".equalsIgnoreCase(result)){
						/*session.put("QuoteNo",travelBean.getQuoteNo());
						request.setAttribute("reqFrom",travelBean.getUser());
						request.setAttribute("quote_status",travelBean.getReferralMsg());
						request.setAttribute("adminReferalRemarks",travelBean.getAdminRemarks());
						try{
								new com.maan.Home.MasterController.HomeMailController().processResult(request,response);
						}catch(Exception e)
						{
							LogManager.debug(e);
						}
						session.remove("QuoteNo");*/
						bean.setDisplay("showRefInfo");
					}else {
						if(DBConstants.DEVICETYPE_ANDROID.equals(bean.getDeviceType()) || DBConstants.DEVICETYPE_HYBRID.equals(bean.getDeviceType())) {
							bean.getActionErrorsBean().add(ResourceBundleUtil.findText(bean.getProductId(),"error.travel.update"));
						} else {
							addActionError(getText("error.travel.update"));
						}
					}
					
				} else {
					//service.updateAddVehicleDetails(bean);
					if("N".equalsIgnoreCase(bean.getInstallmentYN()) && service.installmentcount(bean)> 0 ){
						deleteinstallment();
					}
					//forward = updatePaymentRequestNew();
					if(hasActionErrors()){
						service.getQuoteInfo(bean, hasActionErrors());
						service.getPremiumInfo(bean);
					}
					if("Y".equalsIgnoreCase(bean.getQuoteEmailYN())){
						new SmsEmailUtil(SmsEmailUtil.EMAIL_QUOTE, bean.getQuoteNo(),bean.getProductId()).send();
						forward = "policyInfo";
					}else if("Y".equalsIgnoreCase(bean.getReferQuoteYN())){
						forward = "policyInfo";
					}
					else{
						//String resultNew=service.getGeratePolicy(bean);
						//if("SUCCESS".equalsIgnoreCase(resultNew)) {
							if("Y".equalsIgnoreCase(bean.getGeneratePolicyYN())) {
								//bean.setPaymentModes(getModeOfPaymentList());
								//forward = "makePayment";
								//String url=getText("payment.redirect.url");
								String url=commonDAO.getPaymentURL();
								String encrPayUrl="";
								try {
									encrPayUrl = getEncrPayUrl(bean, url);
									bean.setForwardURL(encrPayUrl);
								} catch (Exception e) {
									e.printStackTrace();
								}
								
								return "forward";
							} 
							else {
								forward = "policyInfo";
							}
						//} 
					}
				}
			} else {
				//bean.setCustdob(StringUtils.isBlank(bean.getCustdob())?"":dateFormat(bean.getCustdob()));
				//service.setCustDetail(bean, "NewQuote");
				
				service.getPremiumInfo(bean);
				forward="inputNew";
			}
			service.getAdditionalVehicleDetails(bean);
			service.getLabelInfo(bean, hasActionErrors());
		}
		catch(Exception exception) {
			LogManager.debug(exception);
			if(DBConstants.DEVICETYPE_ANDROID.equals(bean.getDeviceType()) || DBConstants.DEVICETYPE_HYBRID.equals(bean.getDeviceType())) {
				bean.getActionErrorsBean().add(ResourceBundleUtil.findText(bean.getProductId(),"error.motor.update"));
			} else {
				addActionError(getText("error.motor.update"));
			}
		}
		return forward;
	}
	
	public String getEncrPayUrl(MotorBean bean, String url) throws Exception {
		String paymentType="madisonPay"; String result="";
		String type="";
		try {
			String stat=commonDAO.checkIsB2C(bean.getLoginId());
			if("Y".equalsIgnoreCase(stat))
				type="b2c";
			else
				type="b2b";
			String encrData=CryptoService.encrypt("quoteNo="+bean.getQuoteNo()+"~~paymentType="+paymentType+"~~productId="+bean.getProductId()+"~~brokertype="+type+"~~logintype=b2b~~branchCode="+bean.getLoginBranch());
			result=url+encrData;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	public String makePayment(){
		String forward="makePayment";
		String mobNum="";
		try {
			getValidate("paymentDetails");
			if (getActionErrors().isEmpty() && (bean.getActionErrorsBean() == null || bean.getActionErrorsBean().isEmpty())) {
				if(!"RSAIssuer".equalsIgnoreCase(bean.getUserType()) && !"RA".equalsIgnoreCase(bean.getQuoteStatus())){
					service.getQuoteInfo(bean, hasActionErrors());
					service.updateCoveragesInfo(bean.getApplicationNo(),bean.getBranchCode(),bean.getProductId(),bean.getEndTypeId(),bean.getLoginId(),"C",bean.getUserType(),"",bean.getReferralYN());
					forward=insertOptionalCovers();
				}
				//if("RSAIssuer".equalsIgnoreCase(bean.getUserType()))
				else
					service.getUpdatePremiumInfo(bean);
				if(StringUtils.isBlank(service.getPolicyReferralMsgs(bean.getApplicationNo()))){
					String result=service.getGeratePolicyNew(bean);
					if("SUCCESS".equalsIgnoreCase(result)) {
						//if("Y".equalsIgnoreCase(bean.getGeneratePolicyYN())) {
							//service.updatePaymentDetails(bean);
						boolean premCond=true;
						if("N".equalsIgnoreCase(bean.getInstallmentYN())){
							if("1".equalsIgnoreCase(bean.getModeOfPayment()) && !bean.getTotalPremium().equals(bean.getCashAmount())){
								premCond=false;
							}else if("2".equalsIgnoreCase(bean.getModeOfPayment()) && !bean.getTotalPremium().equals(bean.getChequeAmount())){
								premCond=false;
							}
						}
						boolean instalmentData=true;
						if("Y".equalsIgnoreCase(bean.getInstallmentYN())) {
							List<Map<String, Object>> installmentDetailsList = new PaymentDAO().getInstallmentDetailsList(bean.getQuoteNo());
							if(installmentDetailsList !=null && installmentDetailsList.size()>0)
								instalmentData=true;
							else
								instalmentData=false;
						}
						if(instalmentData){
						if(premCond){
							if(DBConstants.PAYMENT_MTN_MOBILE_MONEY.equalsIgnoreCase(bean.getModeOfPayment()))
								mobNum=bean.getMtnMobileNo();
							else if(DBConstants.PAYMENT_AIRTEL_MOBILE_MONEY.equalsIgnoreCase(bean.getModeOfPayment()))
								mobNum=bean.getAirtelMoneyNumber();
							bean.setMerchant_reference(
								new PaymentDAO().insertPaymentDetails(
									bean.getModeOfPayment(), bean.getChequeNo(), bean.getChequeDate(), bean.getChequeAmount(),
									bean.getTotalPremium(),bean.getBankName(), bean.getMicrCode(), bean.getCashDepositBank(),
									bean.getCashAmount(), bean.getCashChellanNo(), bean.getCashInstrumentDate(), bean.getApplicationNo(), bean.getQuoteNo(),
									bean.getProductId(), bean.getMerchant_reference(), bean.getEmail(), bean.getCustomerName(), bean.getBranchCode(),
									bean.getCurrencyType(), bean.getInstallmentYN(), bean.getMobileNo(), bean.getDeviceType(), mobNum
								)
							);
					
							/*if(DBConstants.PAYMENT_CHEQUE.equalsIgnoreCase(bean.getModeOfPayment()) || DBConstants.PAYMENT_CASH.equalsIgnoreCase(bean.getModeOfPayment()) || DBConstants.PAYMENT_ZAMTEL.equalsIgnoreCase(bean.getModeOfPayment())) {
								forward = updatePolicyInfoNew();
							} else*/ 
							if(DBConstants.PAYMENT_MTN_MOBILE_MONEY.equalsIgnoreCase(bean.getModeOfPayment())){
								try{
									if(StringUtils.isNotBlank(bean.getMerchant_reference())){
										MtnService ms = new MtnService(bean.getQuoteNo(),"",bean.getMerchant_reference(),bean.getProductId());
										Map<String, Object> mpd = ms.getPaymentDetails();
										if(mpd!=null && mpd.size()>0){
											String quoteNo = mpd.get("QUOTE_NO")==null?"":mpd.get("QUOTE_NO").toString();
											String productId = mpd.get("PRODUCT_ID")==null?"":mpd.get("PRODUCT_ID").toString();
											String premium = mpd.get("PREMIUM")==null?"":mpd.get("PREMIUM").toString();
											String merchantRefNo = mpd.get("MERCHANT_REFERENCE")==null?"":mpd.get("MERCHANT_REFERENCE").toString();
											String currencyType = mpd.get("CURRENCY_TYPE")==null?"":mpd.get("CURRENCY_TYPE").toString();
											String referenceNo = mpd.get("REFERENCE_NO")==null?"":mpd.get("REFERENCE_NO").toString();
											String mobileNo = mpd.get("MTN_MOBILE_NO")==null?"":mpd.get("MTN_MOBILE_NO").toString();
											
											
											if(StringUtils.isNotBlank(quoteNo) && StringUtils.isNotBlank(premium)
													&& StringUtils.isNotBlank(merchantRefNo) && StringUtils.isNotBlank(currencyType)
													&& StringUtils.isNotBlank(referenceNo) && StringUtils.isNotBlank(mobileNo)){
												ReqToPayIpModel rtp = new ReqToPayIpModel();
												rtp.setExternalId(quoteNo);
												rtp.setCurrency(currencyType);
												rtp.setAmount(premium);
												rtp.setPayeeNote("Payment for Madison");
												rtp.setPayerMessage("Pay for Insurance");
												Payer p = new Payer();
												p.setPartyIdType("MSISDN");
												if(!mobileNo.startsWith("26")){
													mobileNo = "26"+mobileNo;
												}
												p.setPartyId(mobileNo);
												rtp.setPayer(p);
												
												MtnService ms1 = new MtnService(quoteNo, referenceNo,
														merchantRefNo, productId);
												if(ms1.requestToPay(rtp)){
													try{
														Thread.sleep(5000);
													}catch(Exception e){
														e.printStackTrace();
													}
													MtnService ms2 = new MtnService(quoteNo, referenceNo,
															merchantRefNo, productId);
													String payStatus = ms2.paymentStatus();
													 if("pending".equalsIgnoreCase(payStatus)
															|| "success".equalsIgnoreCase(payStatus)
															|| "successful".equalsIgnoreCase(payStatus)){
														forward = "mtnWaitingPage";
													}else{
														addActionError("MTN Payment Failed. Please try again later");
													}
												}else{
													throw new Exception(merchantRefNo+" Request Sending Failed");
												}
											}else{
												throw new Exception(merchantRefNo+" Mtn Payment Details Not Found");
											}
										}
									}else{
										throw new Exception(bean.getMerchant_reference()+" Merchant Reference No is Empty");
									}
								}catch(Exception e){
									addActionError("Something went wrong. please try again with other payment method.");
									e.printStackTrace();
								}
							}else if(DBConstants.PAYMENT_AIRTEL_MOBILE_MONEY.equalsIgnoreCase(bean.getModeOfPayment())){
								try{
									if(StringUtils.isNotBlank(bean.getMerchant_reference())){
										AirtelService as = new AirtelService(bean.getQuoteNo(),"",bean.getMerchant_reference(),bean.getProductId());
										Map<String, Object> mpd = as.getPaymentDetails();
										if(mpd!=null && mpd.size()>0){
											String quoteNo = mpd.get("QUOTE_NO")==null?"":mpd.get("QUOTE_NO").toString();
											String productId = mpd.get("PRODUCT_ID")==null?"":mpd.get("PRODUCT_ID").toString();
											String premium = mpd.get("PREMIUM")==null?"":mpd.get("PREMIUM").toString();
											String merchantRefNo = mpd.get("MERCHANT_REFERENCE")==null?"":mpd.get("MERCHANT_REFERENCE").toString();
											String currencyType = mpd.get("CURRENCY_TYPE")==null?"":mpd.get("CURRENCY_TYPE").toString();
											String referenceNo = mpd.get("REFERENCE_NO")==null?"":mpd.get("REFERENCE_NO").toString();
											String mobileNo = mpd.get("MTN_MOBILE_NO")==null?"":mpd.get("MTN_MOBILE_NO").toString();
											
											if(StringUtils.isNotBlank(quoteNo) && StringUtils.isNotBlank(premium)
													&& StringUtils.isNotBlank(merchantRefNo) && StringUtils.isNotBlank(currencyType)
													&& StringUtils.isNotBlank(mobileNo)){
												ReqToPayIpModelAirtel rtp = new ReqToPayIpModelAirtel();
												rtp.setReference("Madison Gen");
												
												Subscriber sub = new Subscriber();
												sub.setCountry("ZM");
												sub.setCurrency(currencyType);
												sub.setMsisdn(Integer.parseInt(mobileNo));
												
												Transaction txn = new Transaction();
												txn.setAmount("1");
												txn.setCountry("ZM");
												txn.setCurrency(currencyType);
												txn.setId(merchantRefNo);
												
												rtp.setSubscriber(sub);
												rtp.setTransaction(txn);
												
												AirtelService as1 = new AirtelService(quoteNo, referenceNo,
														merchantRefNo, productId);
												if(as1.requestToPay(rtp)){
													try{
														Thread.sleep(5000);
													}catch(Exception e){
														e.printStackTrace();
													}
													AirtelService ms2 = new AirtelService(quoteNo, referenceNo,
															merchantRefNo, productId);
													String payStatus = ms2.paymentStatus(rtp);
													 if("pending".equalsIgnoreCase(payStatus)
															|| "success".equalsIgnoreCase(payStatus)
															|| "successful".equalsIgnoreCase(payStatus)
															|| "TIP".equalsIgnoreCase(payStatus)
															|| "TS".equalsIgnoreCase(payStatus)){
														forward = "airtelWaitingPage";
													}else{
														addActionError("Airtel Payment Failed. Please try again later");
													}
												}else{
													throw new Exception(merchantRefNo+" Request Sending Failed");
												}
											}else{
												throw new Exception(merchantRefNo+" Airtel Payment Details Not Found");
											}
										}
									}else{
										throw new Exception(bean.getMerchant_reference()+" Merchant Reference No is Empty");
									}
								}catch(Exception e){
									addActionError("Something went wrong. please try again with other payment method.");
									e.printStackTrace();
								}
							}
							else if(DBConstants.PAYMENT_CHEQUE.equalsIgnoreCase(bean.getModeOfPayment()) || DBConstants.PAYMENT_CASH.equalsIgnoreCase(bean.getModeOfPayment()) || DBConstants.CREDIT_NOTE.equalsIgnoreCase(bean.getModeOfPayment())){
								
								final Map fromPosition = service.checkPolicy(bean);
								LogManager.info("Map Size=>"+fromPosition.size());
								if (!"P".equalsIgnoreCase(fromPosition.get("STATUS").toString())) {
									CommonDAO daoNew=new CommonDAO();
									String res=daoNew.homePolicyGeneration(bean.getQuoteNo(),bean.getProductId(),"01");
									 if("SUCCESS".equalsIgnoreCase(res)){
										 if("Y".equalsIgnoreCase(bean.getInstallmentYN())){
												daoNew.updateInstallmentDetail(bean.getQuoteNo(),"IPS","Y");
											}
											
											LogManager.info("CONV Enter into paymentMode => "+bean.getModeOfPayment());
											try {
												new PaymentProcessService().insPaymentProcessTrac(new CommonDAO().getHomePolicyNo(bean.getQuoteNo()),bean.getQuoteNo(),"uwPending","Y","","onlineUW","onlineUW",bean.getProductId(),bean.getBranchCode());
											} catch (Exception e1) {
												addActionError("Error on Policy Insertion");
											}
											if(!hasActionErrors()){
												LogManager.info("CONV policy Status Upadte Done as P - paymentMode => "+bean.getModeOfPayment());
												String pvOut="";
												try{
													LogManager.info("CONV Integration Calling starts..");
													Map <String ,Object> map=daoNew.commonIntgProcess(bean.getQuoteNo(), bean.getBranchCode());
													pvOut=(String)map.get("PVOUT")==null?"":(String)map.get("PVOUT");
													LogManager.info("CONV Integration Calling end..");
												}catch (Exception e) {
													e.printStackTrace();
													pvOut="Error in Staging Table Movement..";
												}
												
												if(StringUtils.isNotBlank(pvOut) && "Success".equalsIgnoreCase(pvOut)){
													//addActionMessage(pvOut);
													LogManager.info(pvOut);
												}
												else if(StringUtils.isNotBlank(pvOut) && !"Success".equalsIgnoreCase(pvOut)){
													//addActionError("Staging Table "+pvOut);
													LogManager.info(pvOut);
												}
												if("Y".equalsIgnoreCase(daoNew.getInstallmentStatus(bean.getQuoteNo()))){
													daoNew.updateInstallmentDetail(bean.getQuoteNo(),"ISS","Y");
												}
												addActionMessage("Policy Created Successfully");
												forward="policyInfoSuccess";
											}
									 }else{
										 addActionError("Error on Policy Creation");
									 }
								}else if("P".equalsIgnoreCase(fromPosition.get("STATUS").toString())){
									forward="policyInfo";
								}
							}
							else {
								//getSession().put("merchant_reference", bean.getMerchant_reference());
								forward = "onlinePaymentReq";
							}
						/*} 
						else {
							forward = "policyInfo";
						}*/
					}else{
						addActionError("Something went wrong with Premium value, Please try again");
					}
					}else{
						addActionError("Installment Detail Not Found, Please Re-Calculate");
					}
					} else {
						if(DBConstants.DEVICETYPE_ANDROID.equals(bean.getDeviceType()) || DBConstants.DEVICETYPE_HYBRID.equals(bean.getDeviceType())) {
							bean.getActionErrorsBean().add(ResourceBundleUtil.findText(bean.getProductId(),"error.motor.update"));
						} else {
							addActionError(getText("error.motor.update"));
						}
					}
				}
			}
			service.getQuoteInfo(bean, hasActionErrors());
			service.setCustDetail(bean,"NewQuote");
			bean.setCustNameLabel(bean.getCustomerName());
			bean.setCustLastNameLabel(bean.getCustLastName());
			bean.setCustEmailLabel(bean.getEmail());
			if("custType".equalsIgnoreCase(bean.getMode())){
				bean.setDisplay("newQuote");
				editQuoteNew();
				forward="inputV1";
			}
		} catch (Exception e) {
			addActionError("Error in Update Payment Detail");
			service.getQuoteInfo(bean, hasActionErrors());
			service.setCustDetail(bean,"NewQuote");
			bean.setCustNameLabel(bean.getCustomerName());
			bean.setCustLastNameLabel(bean.getCustLastName());
			bean.setCustEmailLabel(bean.getEmail());
			e.printStackTrace();
		}
		return forward;
	}
	public String showPolInfo(){
		try {
			service.getQuoteInfo(bean, hasActionErrors());
			service.setCustDetail(bean,"NewQuote");
			bean.setCustNameLabel(bean.getCustomerName());
			bean.setCustLastNameLabel(bean.getCustLastName());
			bean.setCustEmailLabel(bean.getEmail());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "policyInfo";
	}
	
	public String updatePolicyInfo() {
		String forward = INPUT;
		/*if(StringUtils.isBlank(bean.getQuoteNo())) {
			bean.setQuoteNo(String.valueOf(getSession().get("quoteNo")));
			//session.remove("quoteNo");
		}
		if(StringUtils.isBlank(bean.getApplicationNo())) {
			bean.setApplicationNo(String.valueOf(getSession().get("applicationNo")));
			//session.remove("applicationNo");
		}
		if(StringUtils.isBlank(bean.getMerchant_reference())) {
			bean.setMerchant_reference(String.valueOf(getSession().get("merchant_reference")));
			//session.remove("merchant_reference");
		}*/
		
		String result=commonDAO.updateHomeQuoteStatus(bean.getApplicationNo(),bean.getMerchant_reference(),bean.getQuoteNo(),bean.getProductId(),bean.getBranchCode());
		String paymentType=commonDAO.getPaymentType(bean.getQuoteNo());
		if("SUCCESS".equalsIgnoreCase(result)) {
			if("6".equalsIgnoreCase(paymentType) || "101".equalsIgnoreCase(paymentType) || "102".equalsIgnoreCase(paymentType)){
				LogManager.info("Enter into paymentMode "+paymentType);
				new PaymentProcessService().insPaymentProcessTrac(new CommonDAO().getHomePolicyNo(bean.getQuoteNo()),bean.getQuoteNo(),"uwPending","Y","","onlineUW","onlineUW",bean.getProductId(),bean.getBranchCode());
				//commonDAO.getUpdPolicyNumStaus(bean.getQuoteNo());
				//commonDAO.getUpdPolicyStaus("P",bean.getQuoteNo());
				LogManager.info("policy Status Upadte Done as P");
				String pvOut="";
				try{
					LogManager.info("Integration Calling starts..");
					Map <String ,Object> map=commonDAO.commonIntgProcess(bean.getQuoteNo(), bean.getBranchCode());
					pvOut=(String)map.get("PVOUT")==null?"":(String)map.get("PVOUT");
					LogManager.info("Integration Calling end..");
				}catch (Exception e) {
					e.printStackTrace();
					pvOut="Error in Staging Table Movement..";
				}
				
				if(StringUtils.isNotBlank(pvOut) && "Success".equalsIgnoreCase(pvOut)){
					//addActionMessage(pvOut);
					LogManager.info(pvOut);
				}
				else if(StringUtils.isNotBlank(pvOut) && !"Success".equalsIgnoreCase(pvOut)){
					addActionError("Staging Table "+pvOut);
					LogManager.info(pvOut);
				}
				if("Y".equalsIgnoreCase(commonDAO.getInstallmentStatus(bean.getQuoteNo()))){
					commonDAO.updateInstallmentDetail(bean.getQuoteNo(),"ISS","Y");
				}
				commonDAO.getUpdPaymentProcessStaus(bean.getQuoteNo());
				commonDAO.getUpdPolicyStaus("SP",bean.getQuoteNo());
				LogManager.info("policy Status Upadte Done as SP");
				
			}
			
			forward="policyInfo";
		} else if("101".equalsIgnoreCase(paymentType)){ 
			addActionError("MTN Payment Failed");
			forward = showPolicyInfo();
		}else if("102".equalsIgnoreCase(paymentType)){ 
			addActionError("Airtel Money Payment Failed");
			forward = showPolicyInfo();
		} 
		else {
			if(DBConstants.DEVICETYPE_ANDROID.equals(bean.getDeviceType()) || DBConstants.DEVICETYPE_HYBRID.equals(bean.getDeviceType())) {
				bean.getActionErrorsBean().add(ResourceBundleUtil.findText(bean.getProductId(),"error.motor.update"));
			} else {
				addActionError(getText("error.motor.update"));
			}
		}
		return forward;
	}
	
	public String updatePolicyInfoNew() {
		String forward = "inputV1";
		/*if(StringUtils.isBlank(bean.getQuoteNo())) {
			bean.setQuoteNo(String.valueOf(getSession().get("quoteNo")));
			//session.remove("quoteNo");
		}
		if(StringUtils.isBlank(bean.getApplicationNo())) {
			bean.setApplicationNo(String.valueOf(getSession().get("applicationNo")));
			//session.remove("applicationNo");
		}
		if(StringUtils.isBlank(bean.getMerchant_reference())) {
			bean.setMerchant_reference(String.valueOf(getSession().get("merchant_reference")));
			//session.remove("merchant_reference");
		}*/
		
		String result=commonDAO.updateHomeQuoteStatus(bean.getApplicationNo(),bean.getMerchant_reference(),bean.getQuoteNo(),bean.getProductId(),bean.getBranchCode());
		String paymentType=commonDAO.getPaymentType(bean.getQuoteNo());
		if("SUCCESS".equalsIgnoreCase(result)) {
			if("6".equalsIgnoreCase(paymentType) || "101".equalsIgnoreCase(paymentType)){
				LogManager.info("Enter into paymentMode 6");
				new PaymentProcessService().insPaymentProcessTrac(new CommonDAO().getHomePolicyNo(bean.getQuoteNo()),bean.getQuoteNo(),"uwPending","Y","","onlineUW","onlineUW",bean.getProductId(),bean.getBranchCode());
				//commonDAO.getUpdPolicyNumStaus(bean.getQuoteNo());
				//commonDAO.getUpdPolicyStaus("P",bean.getQuoteNo());
				LogManager.info("policy Status Upadte Done as P");
				String pvOut="";
				try{
					LogManager.info("Integration Calling starts..");
					Map <String ,Object> map=commonDAO.commonIntgProcess(bean.getQuoteNo(), bean.getBranchCode());
					pvOut=(String)map.get("PVOUT")==null?"":(String)map.get("PVOUT");
					LogManager.info("Integration Calling end..");
				}catch (Exception e) {
					e.printStackTrace();
					pvOut="Error in Staging Table Movement..";
				}
				
				if(StringUtils.isNotBlank(pvOut) && "Success".equalsIgnoreCase(pvOut)){
					//addActionMessage(pvOut);
					LogManager.info(pvOut);
				}
				else if(StringUtils.isNotBlank(pvOut) && !"Success".equalsIgnoreCase(pvOut)){
					addActionError("Staging Table "+pvOut);
					LogManager.info(pvOut);
				}
				if("Y".equalsIgnoreCase(commonDAO.getInstallmentStatus(bean.getQuoteNo()))){
					commonDAO.updateInstallmentDetail(bean.getQuoteNo(),"ISS","Y");
				}
				commonDAO.getUpdPaymentProcessStaus(bean.getQuoteNo());
				commonDAO.getUpdPolicyStaus("SP",bean.getQuoteNo());
				LogManager.info("policy Status Upadte Done as SP");
				
			}
			
			forward="policyInfo";
		} else if("101".equalsIgnoreCase(paymentType)){ 
			addActionError("MTN Payment Failed");
			forward = showPolicyInfoNew();
		}else {
			if(DBConstants.DEVICETYPE_ANDROID.equals(bean.getDeviceType()) || DBConstants.DEVICETYPE_HYBRID.equals(bean.getDeviceType())) {
				bean.getActionErrorsBean().add(ResourceBundleUtil.findText(bean.getProductId(),"error.motor.update"));
			} else {
				addActionError(getText("error.motor.update"));
			}
		}
		return forward;
	}
	
	public String showPolicyInfo() {
		try {
			bean.setDisplay("policyInfo");
			new CustomerDAO().setCustomerDetails(bean);
			service.getQuoteInfo(bean, hasActionErrors());
			service.getPremiumInfo(bean);
			service.getAdditionalVehicleDetails(bean);
			service.setCustDetail(bean, "New");
		}
		catch(Exception exception) {
			LogManager.debug(exception);
		}
		return INPUT;
		//return "inputNew";
	}
	
	public String showPolicyInfoNew() {
		try {
			bean.setDisplay("policyInfo");
			new CustomerDAO().setCustomerDetails(bean);
			service.getQuoteInfo(bean, hasActionErrors());
			service.getPremiumInfo(bean);
			service.getAdditionalVehicleDetails(bean);
			service.setCustDetail(bean, "New");
			bean.setBrokerBranchList(commonDAO.getSubBranchList(bean.getLoginId(),bean.getLoginBranch()));
			bean.setBrokerUserList(commonDAO.getBrokerUserList(bean.getQuoteNo(),bean.getLoginBranch()));
		}
		catch(Exception exception) {
			LogManager.debug(exception);
		}
		//return INPUT;
		return "inputNew";
	}
	
	public String documentUpload() {
		return "documentUpload";
	}
	
	public String submitdocument() {
		try {
			int j=0;
			for(int i=0 ; i<bean.getDocumentIdList().size() ; i++) {
				if(StringUtils.isNotBlank(bean.getDocumentIdList().get(i))) {
					SimpleDateFormat sdf = new SimpleDateFormat("'on'ddMMMyyyy h.mm.ss a");
					Calendar cal = Calendar.getInstance();
					String date = sdf.format(cal.getTime());
					
					String documentPath = DOCUMENT_FILE_PATH + bean.getApplicationNo() + "_" + bean.getDeleteVehicleId() + "_" + bean.getDocumentIdList().get(i) + date + "." +FilenameUtils.getExtension(bean.getUploadFileName().get(j));
					FileUtils.copyFile(bean.getUpload().get(j), new File(documentPath));
					service.insertDocumentDetails(bean.getApplicationNo(), bean.getQuoteNo(), bean.getDeleteVehicleId(), bean.getDocumentIdList().get(i), documentPath);
					j++;
				}
			}
			addActionMessage("Added Successfully");
		} catch(Exception exception) {
			LogManager.debug(exception);
		}
		return "documentUpload";
	}

	public String downloaddocument() {
		try {
			inputStream = new FileInputStream(bean.getFilePath());
			bean.setFileName(FilenameUtils.getName(bean.getFilePath()));
		} catch(Exception exception) {
			LogManager.debug(exception);
		}
		return "stream";
	}
		
	public String getHelpInfo(){
		bean.setHelpInfoList(service.getHelpInfoList(bean.getHelpType(), bean.getBranchCode()));
		return "helpinfo";
	}
	
	@JSON(serialize=false)
	public List<Map<String,Object>> getPolicyInformation() {
		return service.getPolicyInformation(bean.getQuoteNo());
	}
	@JSON(serialize=false)
	public Map<String,String> getDocumentDetails() {
		return service.getDocumentDetails(bean.getApplicationNo(), bean.getDeleteVehicleId());
	}
	@JSON(serialize=false)
	public List<Map<String,Object>> getDocumentList() {
		return service.getDocumentList(bean.getApplicationNo());
	}
	@JSON(serialize=false)
	public String getReferralMsgs() {
		return service.getReferralMsgs(bean.getApplicationNo());
	}
	@JSON(serialize=false)
	public String getSelectedPolicyType() {
		return service.getSelectedPolicyType(bean.getApplicationNo());
	}
	
	private void getValidate(String type) {
		LinkedList<String> list=service.getValidate(bean, type);
		setActionErrorList(list);
	}
	
	private void setActionErrorList(List<String> list) {
		if(DBConstants.DEVICETYPE_ANDROID.equals(bean.getDeviceType()) || DBConstants.DEVICETYPE_HYBRID.equals(bean.getDeviceType())) {
			List<String> errorBean = new ArrayList<String>();
			for (String st : list) {
				if(st.indexOf("#")!=-1) {
					Object args[]=(st.indexOf("~")!=-1?(st.substring(st.indexOf('#')+1, st.lastIndexOf('#'))).split("~"):new String[]{st.substring(st.indexOf('#')+1)});
					bean.getActionErrorsBean().add(ResourceBundleUtil.findText(bean.getProductId(),st.substring(0,st.indexOf('#')),args));
				} else {
					bean.getActionErrorsBean().add(ResourceBundleUtil.findText(bean.getProductId(),st));
				}
			}
			bean.getActionErrorsBean().addAll(errorBean);
		} else {
			for (String st : list) {
				if(st.indexOf("#")!=-1) {
					String args[]=(st.indexOf("~")!=-1?(st.substring(st.indexOf('#')+1, st.lastIndexOf('#'))).split("~"):new String[]{st.substring(st.indexOf('#')+1)});
					this.addActionError(getText(st.substring(0,st.indexOf('#')),args));
				} else {
					this.addActionError(getText(st));	
				}
			}
		}
	}
	
	public String getFields() {
		if(StringUtils.isNotEmpty(bean.getEndTypeId())){
			String[] ids=bean.getEndTypeId().split(",");
			if(ArrayUtils.isNotEmpty(ids)){
				fields=getText("endt")+",";
				for (int i = 0; i < ids.length; i++) {
					fields+=getText("endt"+ids[i])+",";
				}
				fields=fields.substring(0, fields.lastIndexOf(","));
			}
		}
		return fields;
	}
	
	/*private String dateFormat(String name){
		try {
			String arr[]=name.split("/");
			return arr[0]+"/"+arr[1]+"/"+arr[2];
		}
		catch(Exception exception) {
			exception.printStackTrace();
			return name;
		}
		
	}*/
	public List<Object> getBankNamelist(){
		return commonDAO.getBankNamelist(bean);
	}
	public List<Object> getPolicyEndList() {
		return commonDAO.getPolicyEndList(bean);
	}
	@JSON(serialize=false)
	public List<Map<String,Object>> getMultiVehicleDetails() {
		return service.getMultiVehicleDetails(bean);
	}
	@JSON(serialize=false)
	public Map<String,Object> getPaymentDetails() {
		return new com.maan.payment.PaymentService().getPaymentDetails(bean.getMerchant_reference());
	}
	@JSON(serialize=false)
	public Map<String,Object> getThirdPartyPremiumInfo() {
		return service.getThirdPartyPremiumInfo(bean.getApplicationNo());
	}
	@JSON(serialize=false)
	public Map<String,Object> getVehicleDetailsById() {
		return service.getVehicleDetailsById(bean.getApplicationNo(),bean.getProductId(),bean.getBranchCode(),bean.getDeleteVehicleId());
	}
	@JSON(serialize=false)
	public String getPaymentYN() {
		String paymentYN = "Y";
		String productId=(String) getSession().get("user1");
		if("User".equalsIgnoreCase(bean.getUserType())) {
			paymentYN = service.getPaymentYN(bean.getApplicationNo());
		}
		return paymentYN; 
	}
	
	private Object[] getParams(String type) {
		Object[] objects = null;
		if("model".equals(type)) {
			objects=new String[]{bean.getOption(),bean.getProductId(),bean.getBranchCode(),bean.getMake(),"","","",""};
		} else if("typeBody".equals(type)) {
			objects=new String[]{bean.getOption(),bean.getProductId(),bean.getBranchCode(),bean.getModel(),"","","",""};
		} else if("vehicleUsage".equals(type)) {
			objects=new String[]{bean.getOption(),bean.getProductId(),bean.getBranchCode(),bean.getModel(),"","","",""};
		} else if("executive".equals(type)) {
			objects=new String[]{bean.getOption(),bean.getProductId(),bean.getBranchCode(),bean.getBrokerCode(),"","","",""};
		} else if("deductible".equals(type)) {
			objects=new String[]{bean.getOption(),bean.getProductId(),bean.getBranchCode(),bean.getSeatingCapacity(),bean.getVehicleUsage(),bean.getTypeBody(),"",""};
		} else if("PolicyExpirydate".equals(type)){
			objects=new String[]{bean.getOption(),bean.getProductId(),bean.getBranchCode(),bean.getPolicyStartDate(),"","","",""};
		} else if("NCB".equalsIgnoreCase(type)){
			objects=new String[]{bean.getOption(),bean.getProductId(),bean.getBranchCode(),bean.getVehicleUsage(),"","","",""};
		} else {
			objects=new String[]{bean.getOption(),bean.getProductId(),bean.getBranchCode(),"","","","",""};
		}
		return objects;
	}
	
	/*****Rest*******/
	public  List<Object> getDropdownListRest(){
		LogManager.info("Enter - getDropdownListRest()");
		List<Object> list = null;
		if("mfrYear".equals(bean.getOption())) {
			list = getMfgYrMapRest();	
		} else if("currencyTypeList".equals(bean.getOption())) {
			list = (List) CURRENCY_TYPE_LIST;
		} else if("vehicleTypeDetails".equals(bean.getOption())) {
			list = (List) getVehicleTypeDetails();
		} else if("policyInformation".equals(bean.getOption())) { 
			list = (List) getPolicyInformation();
		} else if("vehicleDetailsById".equals(bean.getOption())) {
			list = (List) getVehicleDetailsById();
		} else {
			list = commonDAO.getOptionsList(bean.getOption(), bean.getProductId(), (bean.getOption()),bean);
		}
		LogManager.info("bean.getOption()---->"+bean.getOption()+"  bean.getProductId()---->"+bean.getProductId()+" LIST--->"+list);
		return list;
	}
	public List getMfgYrMapRest() {
		List<Map<String, Object>> list=new ArrayList<Map<String, Object>>();
		for(int i=Calendar.getInstance().get(Calendar.YEAR);i>=Calendar.getInstance().get(Calendar.YEAR)-25;i--){
			Map<String,Object> map=new HashMap<String,Object>();
				map.put("CODE",i+"");
				map.put("CODEDESC",i+"");
				list.add(map);
		}
		return list;
	}
	public List getNoClaimBonusListRest() {
		List<Map<String, Object>> list=new ArrayList<Map<String, Object>>();
		for(int i=0;i<=5;i++){
			Map<String,Object> map=new HashMap<String,Object>();			
			map.put("CODE",i+"");
			map.put("CODEDESC",i+"");
			list.add(map);
		}
		return list;
	}
	public void mapRestBean(MotorBean bean) {
		bean.setActionErrorsBean(new ArrayList<String>());
		this.bean = bean;
	}
	public String policyEndList(){
		getRequest().setAttribute(DBConstants.FIELD, "PolicyExpirydate");
		return DBConstants.DROPDOWN;
	}
	public String policyEndListNew(){
		getRequest().setAttribute(DBConstants.FIELD, "PolicyExpirydateNew");
		return DBConstants.DROPDOWN;
	}
	/*****Rest End*******/

	public InputStream getInputStream() {
		return inputStream;
	}
	public String electricalPopup(){
		service.getElectrical(bean);	
		return "electricalPopUp";
	}
	public String nelectricalPopup(){
		service.getnelectricalPopup(bean);	
		return "NonelectricalPopUp";
	}
	public String insertElectric(){
		service.insertElectric(bean);
		return "electricalPopUp";
	}
	public String insertNonElectric(){
		service.insertNonElectric(bean);
		return "NonelectricalPopUp";
	}
	
	private HttpServletRequest getRequest() {
		return ServletActionContext.getRequest();
	}
	private HttpServletResponse getResponse() {
		return ServletActionContext.getResponse();
	}
	private Map<String, Object> getSession() {
		return ActionContext.getContext().getSession();
	}
	public String bankInfoAjax() {
		if(service.installmentcount(bean) > 0 ){
			service.getInstallmentDetail(bean);
		}
		getRequest().setAttribute(DBConstants.FIELD, "bankInfoAjx");
		return DBConstants.DROPDOWN;
	}
	public String bankInfoAjaxNew() {
		if(service.installmentcount(bean) > 0 ){
			service.getInstallmentDetail(bean);
		}
		getRequest().setAttribute(DBConstants.FIELD, "bankInfoAjxNew");
		return DBConstants.DROPDOWN;
	}
	@JSON(serialize=false)
	public Map<String,Object> getBankInformAjax() {
		return new PaymentDAO().getBankInfoAjax(bean.getModeOfPay(),bean.getCurrencyType());
	}
	@JSON(serialize=false)
	public List<Map<String,Object>> getConditionClausesList() {
		return service.getConditionClausesList(bean.getQuoteNo(), bean.getProductId(), bean.getBranchCode());
	}
	private void mapLoginDetails(){
		if( StringUtils.isBlank(bean.getBrokerCode())){
			bean.setBrokerCode(commonDAO.getSingleInfo("GET_MOTOR_AG_CODE", new String[]{bean.getLoginId()}));
		}
		if("B2C".equalsIgnoreCase(bean.getLoginType())){
			bean.setExecutive("5");
		}
	}
	
	public String getRoadAssistant(){
		bean.setTypeofAssistantList(service.getTypeofAssistantList());
		bean.setReqFrom("");
		return "roadassistant";
	}
	
	public String insRoadAssistant(){
		getValidate("roadAssistDetails");
		try{
		if(getActionErrors().isEmpty() && (bean.getActionErrorsBean() == null || bean.getActionErrorsBean().isEmpty())){
			service.insRoadAssistantDetail(bean);
			new SmsEmailUtil(SmsEmailUtil.GET_ROADASSIST,bean.getRefNo()/*,bean.getCustName(),bean.getPolicyNo(),bean.getMobileNo(),bean.getAssistantType(),bean.getDesc(),bean.getLatitude(),bean.getLongitude()*/).send();
			new SmsEmailUtil(SmsEmailUtil.GET_ROADASSIST_OPUSER,bean.getRefNo()/*,bean.getCustName(),bean.getPolicyNo(),bean.getMobileNo(),bean.getAssistantType(),bean.getDesc(),bean.getLatitude(),bean.getLongitude()*/).send();
			addActionMessage("Insert Success");
			bean.setReqFrom("success");
			}
		else{
			getRoadAssistant();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return "roadassistant";
	}

	public String updRAFeedBack() {
		getValidate("roadAssistfdBack");
		try{
			if(getActionErrors().isEmpty() && (bean.getActionErrorsBean() == null || bean.getActionErrorsBean().isEmpty())){
			service.updRAFeedBack(bean);
			}else{
			getRoadAssistant();	
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return "roadassistant";
	}
	
	public String getRAList(){
		bean.setRefNoList(service.getRAList(bean));
		bean.setReqFrom("");
		return "roadassistantstatus";
	}
	public String getRoadAssistView(){
		MotorReportAction action = new MotorReportAction();
			action.getRoadAssistView();
		return "roadassistantstatus";
	}
	
	public void deleteinstallment(){
		if("N".equalsIgnoreCase(bean.getInstallmentYN()) && service.installmentcount(bean)> 0 ){
			service.deleteinstallment(bean);
		}
	}
	
	public String otpLogin() {
		bean.setDisplay("newQuoteOtpLogin");
		return "input";
	}
	public String loginRegDtl(){
		String forward = "input";
		try {
			//List<String> errorList =service.getValidate(bean, "regcust");
			validatingFields("loginRegDtl");
			if(!hasActionErrors()){
				String count = service.getLoginCount(bean,"Reg");
				if(Integer.parseInt(count) > 0){
					service.setCustDetail(bean,"NewQuote");
					int res=service.getUpdateCustDtl(bean,"loginRegDtl");
					
					String smsRequired=new SmsEmailDAO().smsRequired(bean.getQuoteNo(),"quote");
					if("Y".equalsIgnoreCase(smsRequired)) {
						new SmsEmailUtil(SmsEmailUtil.GET_QUOTE, bean.getQuoteNo()).send();
						new SmsEmailUtil(SmsEmailUtil.GET_QUOTE_OPUSER, bean.getQuoteNo()).send();
					}
					new SmsEmailDAO().updateSmsStatus(bean.getQuoteNo(),"quote");
					
					otpGen(bean.getMobileNum());
					forward = "otpGen";
				}else{
					addActionError("You Are Not Registered User. Kindly Use New User Login");
					bean.setDisplay("newQuoteOtpLogin");
				}
				
			}else
				bean.setDisplay("newQuoteOtpLogin");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return forward;
	}
	public String loginNewDtl(){
		String forward = "input";
		try {
			//List<String> errorList =new CustomerService().getMotorCustomerValidate(bean.getIssuer(),bean.getBrokerCode(),bean.getExecutive(),bean.getTitle(),bean.getCustomerName(),bean.getMobileNo(),bean.getEmail(),bean.getCustomerType(),bean.getCompanyRegNo());
			validatingFields("loginNewDtl");
			if(!hasActionErrors()){
				String count = service.getLoginCount(bean,"New");
				if(Integer.parseInt(count) > 0){
					addActionError("You Are Registered User. Kindly Use Registered User Login");
					bean.setDisplay("newQuoteOtpLogin");
				}else {
					int res=service.getUpdateCustDtl(bean,"loginNewDtl");
					String smsRequired=new SmsEmailDAO().smsRequired(bean.getQuoteNo(),"quote");
					if("Y".equalsIgnoreCase(smsRequired)) {
						new SmsEmailUtil(SmsEmailUtil.GET_QUOTE, bean.getQuoteNo()).send();
						new SmsEmailUtil(SmsEmailUtil.GET_QUOTE_OPUSER, bean.getQuoteNo()).send();
					}
					new SmsEmailDAO().updateSmsStatus(bean.getQuoteNo(),"quote");
					otpGen(bean.getMobileNo());
					forward = "otpGen";
				}
			}else
				bean.setDisplay("newQuoteOtpLogin");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return forward;
	}
	
	public void validatingFields(String type){
		try {
			if("loginRegDtl".equalsIgnoreCase(type)){
				if(StringUtils.isBlank(bean.getMobileNum())){
					addActionError("Please Enter Registered Mobile No");
				}else if(Validation.INVALID.equals(validation.validateMobile(bean.getMobileNum()))) {
					addActionError("Please Enter Valid Mobile No");
				}
			}else if("loginNewDtl".equalsIgnoreCase(type)){
				Validation validation=new Validation();
				ValidationFormat val = new ValidationFormat();
				if(StringUtils.isEmpty(bean.getTitle()))
				{
					addActionError("Please select Title");
				}
				if(StringUtils.isEmpty(bean.getCustomerName()))
				{
					addActionError("Please enter First Name");
				}else if(!val.validateStringWithSpace(bean.getCustomerName()))
				{
					addActionError("Please enter valid First Name");
				}
				if(StringUtils.isEmpty(bean.getMobileNo())) {
					addActionError("Please enter Mobile No");
				} else if(Validation.INVALID.equals(validation.validateMobile(bean.getMobileNo()))) {
					addActionError("Please enter valid Mobile No");
				}
				if(StringUtils.isEmpty(bean.getEmail())) {
					addActionError("Please enter Email");
				}
				else if("invalid".equalsIgnoreCase(validation.emailValidate(bean.getEmail()))) {	
					addActionError("Please enter valid Email");
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void otpGen(String mobNum){
		if("B2C".equalsIgnoreCase(bean.getLoginType()) && !"Y".equalsIgnoreCase(bean.getOtpStatus())){
			int otp=new OTPGenerator().getOTP();
			//bean.setMyOtp(otp);
			int mailOtp=new OTPGenerator().getOTP();
			//(String) getSession().get("Id")
			bean.setOtpId(new OTPGenerator().insertOTP(new String[]{bean.getOtpId(),"B2C_QUOTE",
					Integer.toString(otp),mobNum,bean.getEmail(),"","N",Integer.toString(mailOtp)}));
			//new SmsEmailUtil("GET_OTP",bean.getOtpId()).send();
			new SmsEmailUtil("GET_OTP",Integer.toString(otp),mobNum,new OTPGenerator().getOtpExpiry(bean.getOtpId()),Integer.toString(mailOtp),bean.getEmail()).send();
			bean.setOtpStatus("N");
			bean.setOtp("");
			bean.setMailOtp("");
			bean.setDisplay("otpVerify");
			otpPage();
		}
	}
	
	public String otpPage(){
		String forward = INPUT;
		try {
			if(!hasActionErrors())
				new CustomerDAO().setCustomerDetails(bean);
			//service.getCustomerDetails(bean);
			String count = service.getEmailCount(bean);
			if(Integer.parseInt(count) > 0){
				bean.setDisplay("otpVerify");
			} else {
				bean.setCity(StringUtils.isBlank(bean.getCity())?ResourceBundleUtil.findDefaultText("MOTOR_DEFAULT_CITY"):bean.getCity());
				bean.setDisplay("otpVerify");
			}
			forward = "otpGen";
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return forward;
	}
	public String getCover(){
		String result="";
		String mobNum="";
		String branchCode="";
		try{
			//mapLoginId();
			mapLoginDetails();
			if(!bean.getLoginId().contains("05")){
				getValidate("otpVerfiy");
				//service.getFirstPageDtls(bean);
				if(!hasActionErrors()){
					bean.setOtpStatus("Y");
					if("B2C".equalsIgnoreCase(bean.getLoginType())){
						mobNum=StringUtils.isBlank(bean.getMobileNo())?bean.getMobileNum():bean.getMobileNo().toString();
						branchCode=StringUtils.isBlank(bean.getBranchCode())?"01":bean.getBranchCode().toString();
						/*if(StringUtils.isNotBlank(bean.getMobileNum())){
							service.setCustDetail(bean);
						}*/
						
						UserMgtAction mgt=new UserMgtAction();			
						mgt.setBeanForMobile(mobNum, bean.getEmail(),bean.getCustomerName() ,bean.getCustNameArabic(),
						bean.getCustdob(),bean.getCompanyRegNo(),bean.getCustomerId(),bean.getApplicationNo(),bean.getQuoteNo(),bean.getProductId());
						LogInAction login=new LogInAction();
						login.setSession(getSession());								
						login.mobLogin(bean.getMobileNo(), "Admin@01");
						login.submit();
						getSession().put("product_id","65");
						getSession().put("user",mobNum);
						getSession().put("branchCode",branchCode);
						//getSession().put("isArabic",bean.getIsArabic());
						getModel();			
						//mapLoginId();
						mapLoginDetails();
						//bean.setDisplay(DISPLAY_NEWQUOTE);
					}
				}else{
					bean.setOtpStatus("N");
				}
			}/*else{
				service.getFirstPageDtls(bean);
			}*/
			if(!hasActionErrors()){
				//result = otpReg();
				result = insertOptionCover();
			}else{
				result = otpPage();
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public String getQuoteNew123(){
		getQuote();
		
		bean.setDisplay("policyInfo");
		service.getPremiumInfo(bean);
		return INPUT;
	}
	
	public String getQuoteNew(){
		String forward=INPUT;
		try {
			mapLoginDetails();
			bean.setMulVehDtls(service.getMultiVehicleDetails(bean));
			
			if("User".equalsIgnoreCase(bean.getUserType()) || "B2C".equalsIgnoreCase(bean.getLoginType())){
				getValidate("captcha");
			}
			if(!"2".equals(bean.getPremiumType())) {
				addVehicle();
			}
			
			bean.setMulVehDtls(service.getMultiVehicleDetails(bean));
			
			/*String[] ncbYear=new String[bean.getMulVehDtls().size()];
			for(int i=0 ; i<bean.getMulVehDtls().size() ; i++) {
				ncbYear[i]=bean.getMulVehDtls().get(i).get("NO_CLAIM_BONUS")==null?"":bean.getMulVehDtls().get(i).get("NO_CLAIM_BONUS").toString();
			}*/
			
			if(getActionErrors().isEmpty()) {
				if(!"getSave".equalsIgnoreCase(bean.getActionType())) {
					service.getQuoteInfo(bean, hasActionErrors());
					if("RA".equalsIgnoreCase(bean.getQuoteStatus()) || StringUtils.isNotBlank(bean.getEndTypeId())) {
						viewOptionCover();
					} else if("2".equals(bean.getPremiumType()) && !"RA".equalsIgnoreCase(bean.getQuoteStatus()) && !StringUtils.isNotBlank(bean.getEndTypeId())) {
						bean.setPolicyType(getText("MOTOR_TP_ID"));
						bean.setPolicyCover(new String[]{"0"});
						service.updSelectedPolicyDtls(bean);
						if(!"B2C".equalsIgnoreCase(bean.getLoginType())) {
							viewOptionCover();
						} else {
							return "redirectEditUser";
						}
					} else {
						//comparisionDetails();
						service.updateCoveragesInfo(bean.getApplicationNo(),bean.getBranchCode(),bean.getProductId(),bean.getEndTypeId(),bean.getLoginId(),"C",bean.getUserType(),"",bean.getReferralYN());
						insertOptionCoverNew();
						//insertOptionCover();
					}
				}
				else {
					bean.setDisplay("showQuoteInfo");
				}
				/*String smsRequired=new SmsEmailDAO().smsRequired(bean.getQuoteNo(),"quote");
				if("Y".equalsIgnoreCase(smsRequired)) {
					new SmsEmailUtil(SmsEmailUtil.GET_QUOTE, bean.getQuoteNo()).send();
					new SmsEmailUtil(SmsEmailUtil.GET_QUOTE_OPUSER, bean.getQuoteNo()).send();
				}
				new SmsEmailDAO().updateSmsStatus(bean.getQuoteNo(),"quote");*/
				
				bean.setCustNameLabel(bean.getCustomerName());
				bean.setCustLastNameLabel(bean.getCustLastName());
				bean.setCustEmailLabel(bean.getEmail());
			}
			if(StringUtils.isNotBlank(bean.getReferralMsg()) && !"RA".equalsIgnoreCase(bean.getQuoteStatus())){
				bean.setDisplay("newQuote");
				forward="policyInfo";
			}
		}
		catch(Exception exception) {
			LogManager.debug(exception);
		}
		return forward;
	}
	
	public String insertOptionCoverNew(){
		String forward=INPUT;
		bean.setBranchCode(StringUtils.isBlank(bean.getBranchCode())?(String)getSession().get("branchCode"):bean.getBranchCode());
		try {
			mapLoginDetails();
			/*String ncbYrs="";
			for(int j=0;j<ncbYear.length;j++){
				if(!"0".equalsIgnoreCase(ncbYear[j]))
					ncbYrs=ncbYear[j].toString();
			}*/
			if(StringUtils.isBlank(bean.getOtp())){
				
				if(DBConstants.DEVICETYPE_ANDROID.equals(bean.getDeviceType()) || DBConstants.DEVICETYPE_HYBRID.equals(bean.getDeviceType())) {
					Object[] optionalCovers = bean.getPolicyCoverMap().values().toArray();
					String[] optionalCoversArray = new String[optionalCovers.length];
					for(int i=0 ; i<optionalCovers.length ; i++) {
						optionalCoversArray[i] = (String) optionalCovers[i];
					}
					bean.setPolicyCover(optionalCoversArray);
				}
				else {
					String policyType = getRequest().getParameter("policyType")==null?"":getRequest().getParameter("policyType");
					String[] optionCovers = null;
					bean.setPolicyType(policyType);
					List<Map<String,Object>> optCov=service.getOptionalCovers(bean);
					if(optCov!=null && optCov.size()>0){
						optionCovers = new String[optCov.size()];
						for(int i=0; i<optCov.size();i++){
							optionCovers[i]=optCov.get(i).get("Y_ID")==null?"":optCov.get(i).get("Y_ID").toString();
						}
						
					}
					/*if("1".equalsIgnoreCase(policyType))
						if(StringUtils.isNotBlank(ncbYrs))
							optionCovers = new String[] {"0", "101", "102", "105", "103"};
						else
							optionCovers = new String[] {"0", "101", "102", "105"};
					else if("2".equalsIgnoreCase(policyType))
						if(StringUtils.isNotBlank(ncbYrs))
							optionCovers = new String[] {"0", "101", "102", "105", "103", "12"};
						else
							optionCovers = new String[] {"0", "101", "102", "105", "12"};
					else if("3".equalsIgnoreCase(policyType))
							optionCovers = new String[] {"0"};*/
					
					String[] optionalCovers = new String[0];
					int arrayCount=0;
					for(int i=0;i<optionCovers.length;i++){
						if(optionCovers[i]!=null && !"".equalsIgnoreCase(optionCovers[i].trim())){
							arrayCount++;
						}
					}
					optionalCovers = new String[arrayCount];
					arrayCount = 0;
					for(int i=0;i<optionCovers.length;i++){
						if(optionCovers[i]!=null && !"".equalsIgnoreCase(optionCovers[i].trim())){
							optionalCovers[arrayCount] = optionCovers[i];
							arrayCount++;
						}
					}
					bean.setRate(getRequest().getParameter("coverRate"+policyType)==null?"0":getRequest().getParameter("coverRate"+policyType));
					bean.setPolicyType(policyType);
					bean.setPolicyCover(optionalCovers);
					
					

					/*String policyType = getRequest().getParameter("policyType")==null?"":getRequest().getParameter("policyType");
					String[] optionCovers = getRequest().getParameterValues("optionalCovers"+policyType);
					String[] optionalCovers = new String[0];
					int arrayCount=0;
					for(int i=0;i<optionCovers.length;i++){
						if(optionCovers[i]!=null && !"".equalsIgnoreCase(optionCovers[i].trim())){
							arrayCount++;
						}
					}
					optionalCovers = new String[arrayCount];
					arrayCount = 0;
					for(int i=0;i<optionCovers.length;i++){
						if(optionCovers[i]!=null && !"".equalsIgnoreCase(optionCovers[i].trim())){
							optionalCovers[arrayCount] = optionCovers[i];
							arrayCount++;
						}
					}
					bean.setRate(getRequest().getParameter("coverRate"+policyType)==null?"0":getRequest().getParameter("coverRate"+policyType));
					bean.setPolicyType(policyType);
					bean.setPolicyCover(optionalCovers);*/
				
				}
				bean.setLoadOrDiscPremium(StringUtils.isBlank(bean.getLoadOrDiscPremium())?"0":bean.getLoadOrDiscPremium());
				if(StringUtils.isNotEmpty(bean.getIssuer()) && StringUtils.isNotBlank(bean.getBrokerCode())) {
					bean.setLoginId(commonDAO.getSingleInfo("loginId", new String[]{bean.getBrokerCode()}));
				}
				/*service.insertOptionCover(bean);
				service.insertPolicyTypePremium(bean);*/
				service.updSelectedPolicyDtls(bean);
				
			}
			if(!"B2C".equalsIgnoreCase(bean.getLoginType())) {
				if(StringUtils.isBlank(service.getPolicyReferralMsgs(bean.getApplicationNo())))
				{
					service.getQuoteInfo(bean, hasActionErrors());
					service.getPremiumInfo(bean);
					service.getUpdatePremiumInfo(bean);
					
					service.getAdditionalVehicleDetails(bean);
					
					service.setCustDetail(bean,"Registered");
					bean.setCity(StringUtils.isBlank(bean.getCity())?ResourceBundleUtil.findDefaultText("MOTOR_DEFAULT_CITY"):bean.getCity());
					/*String premiumInfo[][]=service.collectPremiumInfo(bean);
				    String minpremium=service.collectMinPremium(bean);*/
				    
				    /*bean.setAdminReferalRemarks(request.getParameter("adminReferalRemarks")==null?"":request.getParameter("adminReferalRemarks"));
				    bean.setRemarks(request.getParameter("Remarks")==null?"A":request.getParameter("Remarks"));
				    bean.updateReferralStatus(bean);*/
					
					if(DBConstants.DEVICETYPE_ANDROID.equals(bean.getDeviceType()) || DBConstants.DEVICETYPE_HYBRID.equals(bean.getDeviceType())) {
						String groupId = "";
						double totalCoverPremium = 0;
						List<Map<String,String>> premiumGroupIdList = new ArrayList<Map<String,String>>();
						
						for(int i=0 ; i<bean.getPremiumInfoList().size() ; i++) {
							Map<String,Object> tempMap = (Map<String,Object>) bean.getPremiumInfoList().get(i);
							String tempGroupId = tempMap.get("GROUP_ID")==null?"":tempMap.get("GROUP_ID").toString();
							totalCoverPremium += Double.valueOf(tempMap.get("PREMIUM")==null?"0":tempMap.get("PREMIUM").toString());
							
							if(!groupId.equals(tempGroupId)) {
								Map<String,String> premiumMap = new HashMap<String, String>();
								premiumMap.put("GROUP_ID", tempGroupId);
								premiumMap.put("GROUP_DESC_ENGLISH", tempMap.get("GROUP_DESC_ENGLISH")==null?"":tempMap.get("GROUP_DESC_ENGLISH").toString());
								premiumGroupIdList.add(premiumMap);
								
								groupId = tempGroupId;
							}
						}
						
						bean.setPremiumGroupIdList(premiumGroupIdList);
						bean.setTotalCoverPremium(String.valueOf(totalCoverPremium));
						bean.setTotalPremium(String.valueOf(totalCoverPremium+Double.valueOf(bean.getPolicyFee())));
					}
					bean.setDisplay("policyInfo");
				}else 
				{
					if(!"admin".equalsIgnoreCase(bean.getUser())) {
						bean.setReferralMsg(service.getPolicyReferralMsgs(bean.getApplicationNo()));
						if(!DBConstants.DEVICETYPE_ANDROID.equals(bean.getDeviceType()) && !DBConstants.DEVICETYPE_HYBRID.equals(bean.getDeviceType())) {
							getSession().put("QuoteNo",bean.getQuoteNo());
							getRequest().setAttribute("RefStatus","Referal");
							try {
									new com.maan.Mail.controller.MotorMailController().processRequest(getRequest(), getResponse());
							}
							catch(Exception e) {
								LogManager.debug(e);
							}
							getSession().remove("QuoteNo");
						}
					}
					bean.setDisplay("newQuote");
					forward="policyInfo";	
				}
			}
			else {
				viewOptionCover();
				//forward="redirectEditUser";
				//forward="redirectOtpUser";
				otpLogin();
			}
		}
		catch(Exception exception) {
			LogManager.debug(exception);
		}
		return forward;
	}
	public String viewQuoteInfo(){
		bean.setCurrencyType(service.getCurrencyType(bean));
		service.getPremiumInfo(bean);
		return "viewQuote";
	}
	
	public String viewQuoteInfoNew(){
		try {
			bean.setCurrencyType(service.getCurrencyType(bean));
			service.getPremiumInfo(bean);
			getRequest().setAttribute(DBConstants.FIELD, "viewQuoteAjax");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return DBConstants.DROPDOWN;
	}
	
	public String driverEditAjax() {
		try {
			if(StringUtils.isNotBlank(bean.getNoClaimBonusIdList()==null?"":bean.getNoClaimBonusIdList().get(0)) || StringUtils.isNotBlank(bean.getPrevPolicyNoList()==null?"":bean.getPrevPolicyNoList().get(0))|| StringUtils.isNotBlank(bean.getClaimAmountList()==null?"":bean.getClaimAmountList().get(0))){
				bean.setIsClaimDtl("Y");
			}
			bean.setVehDtls(service.getVehicleDetailsByIdNew(bean));   
			bean.setDisplay("vehDetails");
			service.getDriverDetails(bean);
			getRequest().setAttribute(DBConstants.FIELD, "driverEditAjax");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return DBConstants.DROPDOWN;
	}
	
	public String driverUpdate() {
		try {
				service.updateDriverDetails(bean);
				APiValidation();
				
				if(!hasActionErrors()) {
					addActionMessage("Updated Successfully");
				}else{
					addActionError("Update Failed");
				}
			service.setCustDetail(bean,"NewQuote");
			service.getQuoteInfo(bean, hasActionErrors());
			bean.setDisplay("vehDetails");
		}
		catch(Exception e) {
			addActionError("Something Went Wrong in Update");
			e.printStackTrace();
		}
			return INPUT;
	}
	/*public List<Map<String,Object>> getBrokerBranchList(){
		return commonDAO.getSubBranchList(bean.getLoginId());
	}*/
	public String quotation(){
		String forward="inputV1";
		try {
			bean.setPolicyTypeList(service.getPolicyTypeList(bean));
			service.setCustDetail(bean,"NewQuote");
			
			addPolicyDtl();
				if(getActionErrors().isEmpty()) {
					bean.setBrokerBranchList(commonDAO.getSubBranchList(bean.getLoginId(),bean.getLoginBranch()));
					bean.setBrokerUserList(commonDAO.getBrokerUserList(bean.getQuoteNo(),bean.getLoginBranch()));
					bean.setLoadOrDiscPremium(StringUtils.isBlank(bean.getLoadOrDiscPremium())?"0":bean.getLoadOrDiscPremium());
					bean.setDisplay("policyInfo");
					forward="inputNew";
			}else {
					bean.setDisplay("showQuoteInfo");
				}
			if(StringUtils.isNotBlank(bean.getReferralMsg()) && !"RA".equalsIgnoreCase(bean.getQuoteStatus())){
				bean.setDisplay("ShowReferralError");
				forward ="referror";
			}
			if("editQuoteQE".equalsIgnoreCase(bean.getActionType()) && hasActionErrors()) {
				bean.setDisplay("newQuote");
				editQuoteNew();
				forward="inputV1";
			}
			
			bean.setCustNameLabel(bean.getCustomerName());
			bean.setCustLastNameLabel(bean.getCustLastName());
			bean.setCustEmailLabel(bean.getEmail());
		}
		catch(Exception exception) {
			exception.printStackTrace();
			LogManager.debug(exception);
		}
		return forward;
	}
				

//				if(StringUtils.isNotEmpty(bean.getIssuer()) && StringUtils.isNotBlank(bean.getBrokerCode())) {
//		            bean.setLoginId(commonDAO.getSingleInfo("loginId", new String[]{bean.getBrokerCode()}));
//		        }
//				bean.setBrokerBranchList(commonDAO.getSubBranchList(bean.getLoginId(),bean.getLoginBranch()));
//				bean.setBrokerUserList(commonDAO.getBrokerUserList(bean.getQuoteNo(),bean.getLoginBranch()));
//				if(!"getSave".equalsIgnoreCase(bean.getActionType())) {
//					
//					if("RA".equalsIgnoreCase(bean.getQuoteStatus()) || StringUtils.isNotBlank(bean.getEndTypeId())) {
//						forward=viewOptionCoverNew();
//						bean.setDisplay("policyInfo");
//						}else {
//						bean.setLoadOrDiscPremium(StringUtils.isBlank(bean.getLoadOrDiscPremium())?"0":bean.getLoadOrDiscPremium());
//						bean.setDisplay("policyInfo");
//						forward="inputNew";
//						
//						
//					}
//				}
//				else {
//					bean.setDisplay("showQuoteInfo");
//				}
//			}
//			if(StringUtils.isNotBlank(bean.getReferralMsg()) && !"RA".equalsIgnoreCase(bean.getQuoteStatus())){
//				bean.setDisplay("newQuote");
//				forward="policyInfo";
//			}
//			if("editQuoteQE".equalsIgnoreCase(bean.getActionType()) && hasActionErrors()) {
//				bean.setDisplay("newQuote");
//				editQuoteNew();
//				forward="inputV1";
//			}
//			
//			bean.setCustNameLabel(bean.getCustomerName());
//			bean.setCustLastNameLabel(bean.getCustLastName());
//			bean.setCustEmailLabel(bean.getEmail());
//			bean.setCity(StringUtils.isBlank(bean.getCity())?ResourceBundleUtil.findDefaultText("MOTOR_DEFAULT_CITY"):bean.getCity());
//		}
//		catch(Exception exception) {
//			exception.printStackTrace();
//			LogManager.debug(exception);
//		}
//		return forward;
//	}
		
				
				
//				addPolicyDtl();
//				bean.setMulVehDtls(bean.getVehiclinfoList());
//				if(!"getSave".equalsIgnoreCase(bean.getActionType())) {
//					
//					if("RA".equalsIgnoreCase(bean.getQuoteStatus()) || StringUtils.isNotBlank(bean.getEndTypeId())) {
//						forward=viewOptionCoverNew();
//					}else {
//						//service.updateCoveragesInfo(bean.getApplicationNo(),bean.getBranchCode(),bean.getProductId(),bean.getEndTypeId(),bean.getLoginId(),"C",bean.getUserType(),"",bean.getReferralYN());
//						forward=insertOptionalCovers();
//					}
//				}
//				else {
//					bean.setDisplay("showQuoteInfo");
//				}
//			}
//			if(StringUtils.isNotBlank(bean.getReferralMsg()) && !"RA".equalsIgnoreCase(bean.getQuoteStatus())){
//				bean.setDisplay("newQuote");
//				forward="policyInfo";
//			}
//			if("editQuoteQE".equalsIgnoreCase(bean.getActionType()) && hasActionErrors()) {
//				bean.setDisplay("newQuote");
//				editQuoteNew();
//				forward="inputV1";
//			}
//			
//			bean.setCustNameLabel(bean.getCustomerName());
//			bean.setCustLastNameLabel(bean.getCustLastName());
//			bean.setCustEmailLabel(bean.getEmail());
//			bean.setCity(StringUtils.isBlank(bean.getCity())?ResourceBundleUtil.findDefaultText("MOTOR_DEFAULT_CITY"):bean.getCity());
//		}
//		catch(Exception exception) {
//			exception.printStackTrace();
//			LogManager.debug(exception);
//		}
//		return forward;
//	}
	
	private String ReferralError() {
		try {
			bean.getReferralMsg();
			bean.setDisplay("policyInfo");
		} catch (Exception e) {
			e.printStackTrace();
		}
			return "inputNew";
		
	}

	public String insertOptionalCovers(){
		String forward="inputNew";
		bean.setBranchCode(StringUtils.isBlank(bean.getBranchCode())?(String)getSession().get("branchCode"):bean.getBranchCode());
		try {
			mapLoginDetails();
			
			if(StringUtils.isBlank(bean.getOtp())){
				
				if(DBConstants.DEVICETYPE_ANDROID.equals(bean.getDeviceType()) || DBConstants.DEVICETYPE_HYBRID.equals(bean.getDeviceType())) {
					Object[] optionalCovers = bean.getPolicyCoverMap().values().toArray();
					String[] optionalCoversArray = new String[optionalCovers.length];
					for(int i=0 ; i<optionalCovers.length ; i++) {
						optionalCoversArray[i] = (String) optionalCovers[i];
					}
					bean.setPolicyCover(optionalCoversArray);
				}
				else {
					String policyType = bean.getPolicyType();
					String[] optionCovers = null;
					List<Map<String,Object>> optCov=service.getOptionalCovers(bean);
					if(optCov!=null && optCov.size()>0){
						optionCovers = new String[optCov.size()];
						for(int i=0; i<optCov.size();i++){
							optionCovers[i]=optCov.get(i).get("Y_ID")==null?"":optCov.get(i).get("Y_ID").toString();
						}
						
					}
					
					String[] optionalCovers = new String[0];
					int arrayCount=0;
					if(optionCovers!=null) {
					for(int i=0;i<optionCovers.length;i++){
						if(optionCovers[i]!=null && !"".equalsIgnoreCase(optionCovers[i].trim())){
							arrayCount++;
						}
					}
					}
					optionalCovers = new String[arrayCount];
					arrayCount = 0;
					if(optionCovers!=null) {
					for(int i=0;i<optionCovers.length;i++){
						if(optionCovers[i]!=null && !"".equalsIgnoreCase(optionCovers[i].trim())){
							optionalCovers[arrayCount] = optionCovers[i];
							arrayCount++;
						}
					}
					}
//					bean.setRate(getRequest().getParameter("coverRate"+policyType)==null?"0":getRequest().getParameter("coverRate"+policyType));
//					bean.setPolicyType(policyType);
//					bean.setPolicyCover(optionalCovers);
					
				}
				bean.setLoadOrDiscPremium(StringUtils.isBlank(bean.getLoadOrDiscPremium())?"0":bean.getLoadOrDiscPremium());
				if(StringUtils.isNotEmpty(bean.getIssuer()) && StringUtils.isNotBlank(bean.getBrokerCode())) {
					bean.setLoginId(commonDAO.getSingleInfo("loginId", new String[]{bean.getBrokerCode()}));
				}
				
				//service.updSelectedPolicyDtls(bean);
				
			}
			if(!"B2C".equalsIgnoreCase(bean.getLoginType())) {
				if(StringUtils.isBlank(service.getPolicyReferralMsgs(bean.getApplicationNo())))
				{
					//service.getQuoteInfo(bean, hasActionErrors());
					//service.getPremiumInfo(bean);
					//service.getUpdatePremiumInfo(bean);
					
					//service.getAdditionalVehicleDetails(bean);
					
					//service.setCustDetail(bean,"Registered");
					//bean.setCity(StringUtils.isBlank(bean.getCity())?ResourceBundleUtil.findDefaultText("MOTOR_DEFAULT_CITY"):bean.getCity());
				
					if(DBConstants.DEVICETYPE_ANDROID.equals(bean.getDeviceType()) || DBConstants.DEVICETYPE_HYBRID.equals(bean.getDeviceType())) {
						String groupId = "";
						double totalCoverPremium = 0;
						List<Map<String,String>> premiumGroupIdList = new ArrayList<Map<String,String>>();
						
						for(int i=0 ; i<bean.getPremiumInfoList().size() ; i++) {
							Map<String,Object> tempMap = (Map<String,Object>) bean.getPremiumInfoList().get(i);
							String tempGroupId = tempMap.get("GROUP_ID")==null?"":tempMap.get("GROUP_ID").toString();
							totalCoverPremium += Double.valueOf(tempMap.get("PREMIUM")==null?"0":tempMap.get("PREMIUM").toString());
							
							if(!groupId.equals(tempGroupId)) {
								Map<String,String> premiumMap = new HashMap<String, String>();
								premiumMap.put("GROUP_ID", tempGroupId);
								premiumMap.put("GROUP_DESC_ENGLISH", tempMap.get("GROUP_DESC_ENGLISH")==null?"":tempMap.get("GROUP_DESC_ENGLISH").toString());
								premiumGroupIdList.add(premiumMap);
								
								groupId = tempGroupId;
							}
						}
						
						//bean.setPremiumGroupIdList(premiumGroupIdList);
						//bean.setTotalCoverPremium(String.valueOf(totalCoverPremium));
						//bean.setTotalPremium(String.valueOf(totalCoverPremium+Double.valueOf(bean.getPolicyFee())));
					}
					bean.setDisplay("policyInfo");
				}else 
				{
					service.getQuoteInfo(bean, hasActionErrors());
					if(!"admin".equalsIgnoreCase(bean.getUser())) {
						bean.setReferralMsg(service.getPolicyReferralMsgs(bean.getApplicationNo()));
						if(!DBConstants.DEVICETYPE_ANDROID.equals(bean.getDeviceType()) && !DBConstants.DEVICETYPE_HYBRID.equals(bean.getDeviceType())) {
							getSession().put("QuoteNo",bean.getQuoteNo());
							getRequest().setAttribute("RefStatus","Referal");
							try {
									new com.maan.Mail.controller.MotorMailController().processRequest(getRequest(), getResponse());
							}
							catch(Exception e) {
								LogManager.debug(e);
							}
							getSession().remove("QuoteNo");
						}
					}
					bean.setDisplay("newQuote");
					forward="policyInfo";	
				}
			}
			
		}
		catch(Exception exception) {
			LogManager.debug(exception);
		}
		return forward;
	}
	
	/*public String editCustInfo(){
		try {
	      getRequest().setAttribute(DBConstants.FIELD, "custEditAjax");
	      service.setCustDetail(bean,"custEdit");
	    }
	    catch(Exception e) {
	        e.printStackTrace();
	    }
	    return DBConstants.DROPDOWN;
    }
	public String updateCustDetailAjax (){
		getRequest().setAttribute(DBConstants.FIELD, "custEditAjax");
		try {
			getValidate("customerDetails");
			if(!hasActionErrors()){
				String custId=new CustomerService().insertCustomerDetails(bean.getCustomerId(),bean.getLoginId(),bean.getBranchCode(),bean.getProductId(),bean.getTitle(),bean.getCustomerName(),bean.getMobileNo(),bean.getEmail(),bean.getAddress1(),bean.getAddress2(),bean.getPoBox(),bean.getCity(),bean.getCoreAppCode(),bean.getClientCustomerId(),bean.getCustArNo(),bean.getCustLastName(),bean.getCustPassportNo(),bean.getCustdob(),bean.getCustAlterMobileNo(),bean.getCustLandLineNo(),bean.getCustomerType(),bean.getCompanyRegNo(),bean.getCustNameArabic(),bean.getCustdobar(),bean.getGender(),bean.getOccupation(),"detailQuote",bean.getCustnrc1(),bean.getCustnrc2(),bean.getCustnrc3());
				addActionMessage("Successfully Updated");
			}
		}catch(Exception e) {
	        e.printStackTrace();
	    }
	    return DBConstants.DROPDOWN;
		 
	}*/
	
	public String emailQuote(){
		try {
			service.getQuoteInfo(bean, hasActionErrors());
			//service.getPremiumInfo(bean);
			new SmsEmailUtil(SmsEmailUtil.EMAIL_QUOTE, bean.getQuoteNo(),bean.getProductId()).send();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "policyInfo";
	}
	
	public String baseRateUpdate(){
		try {
			int id=Integer.parseInt(bean.getRateVehicleId());
			if(bean.getBaseRateVeh()==null || bean.getBaseRateVeh().size()==0 || bean.getBaseRateVeh().get(id)==null){
				addActionError("Please Enter Rate");
			}else if(bean.getBaseRateVeh().get(id)==0){
				addActionError("Please Enter Valid Rate");
			}else if(!isNumber(String.valueOf(bean.getBaseRateVeh().get(id)))) {
				addActionError("Please Enter Valid Rate ");
			}
			if(!hasActionErrors()){
				service.updateCovRateVeh(bean);
				service.getUpdatePremiumInfo(bean);
				//service.updatePremiumFeeTotal(bean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return showPolicyInfoNew();
	}
	
	public static boolean isNumber(String str) {
	    try {
	        double v = Double.parseDouble(str);
	        return true;
	    } catch (NumberFormatException nfe) {
	    }
	    return false;
	}
	public String clausesEditAjax(){
		getRequest().setAttribute(DBConstants.FIELD, "clausesEditAjax");
		try {
			bean.setMode("editlist");
			service.getQuoteInfo(bean, hasActionErrors());
			//bean.setConditionList(service.getConditionClausesList(bean));
			bean.setConditionList(service.getConditionClausesEdit(bean));
		}catch(Exception e) {
	        e.printStackTrace();
	    }
	    return DBConstants.DROPDOWN;
	}
	public String clausesAddAjax(){
		getRequest().setAttribute(DBConstants.FIELD, "clausesEditAjax");
		try {
			//bean.setMode("addlist");
			service.getQuoteInfo(bean, hasActionErrors());
			bean.setConditionList(service.getConditionClausesListRemain(bean));
			bean.setConditionEditList(service.getConditionClausesEdit(bean));
		}catch(Exception e) {
	        e.printStackTrace();
	    }
	    return DBConstants.DROPDOWN;
	}
	
	public String saveConditions(){
		getRequest().setAttribute(DBConstants.FIELD, "clausesEditAjax");
		try {
			bean.setMode("addNew");
			service.getQuoteInfo(bean, hasActionErrors());
			bean.setConditionList(service.getConditionClausesListRemain(bean));
			if(StringUtils.isBlank(bean.getConditionDesc()))
				addActionError("Please Enter Condition Description");
			else{
				int res=service.addConditionDetail(bean);
				if(res>0)
					addActionMessage("Condition Added Successfully");
				else
					addActionError("Error in Adding Condition");
			}
			clausesAddAjax();

		}catch(Exception e) {
	        e.printStackTrace();
	    }
	    return DBConstants.DROPDOWN;
	}
	
	public String addExistConditions(){
		getRequest().setAttribute(DBConstants.FIELD, "clausesEditAjax");
		try {
			bean.setMode("addList");
			service.getQuoteInfo(bean, hasActionErrors());
			bean.setConditionList(service.getConditionClausesListRemain(bean));
			if(bean.getCondition().size()<=0)
				addActionError("Please Choose Condition Description");
			else{
				int res=service.addChooseConditionDetail(bean);
				if(res>0)
					addActionMessage("Condition Added Successfully");
				else
					addActionError("Error in Adding Condition");
			}
			clausesAddAjax();

		}catch(Exception e) {
	        e.printStackTrace();
	    }
	    return DBConstants.DROPDOWN;
	}
	
	public String updateConditions(){
		getRequest().setAttribute(DBConstants.FIELD, "clausesEditAjax");
		try {
			bean.setMode("editlist");
			String[] condition=bean.getConditionSelected().split(",");
			List<String> conditionList=new ArrayList<String>();
			for (String list : condition) {
				conditionList.add(list.trim());
			}
			bean.setCondition(conditionList);
			bean.setConditionEditList(service.getConditionClausesEdit(bean));
			int res=service.insertConditionDetail(bean);
			if(res>0)
				addActionMessage("Condition Added Successfully");
			else
				addActionError("Error in Adding Condition");
			service.getQuoteInfo(bean, hasActionErrors());
			bean.setConditionList(service.getConditionClausesList(bean));
		}catch(Exception e) {
	        e.printStackTrace();
	    }
	    return DBConstants.DROPDOWN;
	}
	
	public String deductibleEditAjax(){
		getRequest().setAttribute(DBConstants.FIELD, "deductibleEditAjax");
		try {
			service.getQuoteInfo(bean, hasActionErrors());
			bean.setDeductibleAddList(service.getDeductibleClausesListRemain(bean));
			bean.setDeductibleEditList(service.getDeductibleClausesEdit(bean));
		}catch(Exception e) {
	        e.printStackTrace();
	    }
	    return DBConstants.DROPDOWN;
	}
	
	public String saveDeductible(){
		getRequest().setAttribute(DBConstants.FIELD, "deductibleEditAjax");
		try {
			bean.setMode("addNew");
			service.getQuoteInfo(bean, hasActionErrors());
			bean.setDeductibleAddList(service.getDeductibleClausesListRemain(bean));
			if(StringUtils.isBlank(bean.getDeductibleDesc()))
				addActionError("Please Enter Deductible Description");
			else{
				int res=service.addDeductibleDetail(bean);
				if(res>0)
					addActionMessage("Deductible Added Successfully");
				else
					addActionError("Error in Adding Deductible");
			}
			deductibleEditAjax();

		}catch(Exception e) {
	        e.printStackTrace();
	    }
	    return DBConstants.DROPDOWN;
	}
	
	public String addExistDeductible(){
		getRequest().setAttribute(DBConstants.FIELD, "deductibleEditAjax");
		try {
			bean.setMode("addList");
			service.getQuoteInfo(bean, hasActionErrors());
			bean.setDeductibleAddList(service.getDeductibleClausesListRemain(bean));
			if(bean.getDeductible().size()<=0)
				addActionError("Please Choose Deductible Description");
			else{
				int res=service.addChooseDeductibleDetail(bean);
				if(res>0)
					addActionMessage("Deductible Added Successfully");
				else
					addActionError("Error in Adding Deductible");
			}
			deductibleEditAjax();

		}catch(Exception e) {
	        e.printStackTrace();
	    }
	    return DBConstants.DROPDOWN;
	}
	
	public String updateDeductible(){
		getRequest().setAttribute(DBConstants.FIELD, "deductibleEditAjax");
		try {
			bean.setMode("editlist");
			String[] deductible=bean.getDeductibleSelected().split(",");
			List<String> deductibleList=new ArrayList<String>();
			for (String list : deductible) {
				deductibleList.add(list.trim());
			}
			bean.setDeductible(deductibleList);
			bean.setDeductibleEditList(service.getDeductibleClausesEdit(bean));
			int res=service.insertDeductibleDetail(bean);
			if(res>0)
				addActionMessage("Deductible Added Successfully");
			else
				addActionError("Error in Adding Deductible");
			service.getQuoteInfo(bean, hasActionErrors());
			bean.setDeductibleAddList(service.getDeductibleClausesList(bean));
		}catch(Exception e) {
	        e.printStackTrace();
	    }
	    return DBConstants.DROPDOWN;
	}
	
	public String updateCustTypeMotorDtl(){
		try {
			service.updateCustomerType(bean);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	
	public String payResponse() { 
	 	try{
	 		setPayEnctData(bean);
	 		//service.generatePolicy(bean);
	 		service.getQuoteInfo(bean);
		}catch (Exception e) {
			e.printStackTrace();
		}
	 	if("Y".equalsIgnoreCase(bean.getQuotationStatus()) || StringUtils.isBlank(bean.getQuotationStatus())) {
	 		return showPolicyInfoNew();
	 	}
		return "policyInfo";
		
	}
	
	public void setPayEnctData(MotorBean bean) throws Exception {
		String decrypt = CryptoService.decrypt(bean.getE());
		System.out.println("Motor decrypt value => "+decrypt);
		if(decrypt.indexOf("~~")!=1){
			String[] split = decrypt.split("~~");
			if(split.length>0){
				String[] split2 = split[0].split("=");
				bean.setQuoteNo(split2[1]);
				String[] split3 = split[1].split("=");
				//bean.setPayment_method(split3[1]);
				String[] split4 = split[2].split("=");
				//bean.setProductId(split4[1]);
				String[] split5 = split[3].split("=");
				bean.setMerchant_reference(split5[1]);
				String[] split6 = split[5].split("=");
				bean.setBranchCode(split6[1]);
				
			} 
		}

	}
	
	public void APiValidation() {
		if(bean.getErrors()!=null && bean.getErrors().size()>0) {
			for (int i =0;i<bean.getErrors().size();i++) {
				HashMap<String, Object> map=bean.getErrors().get(i);
				addActionError(map.get("Message").toString());
			}
		}
	}

public String vehicleUpdate() {
	try {
		getRequest().setAttribute(DBConstants.FIELD, "vehicleEditAjax");
		addVehicleNew();
	} catch (Exception e) {
		e.printStackTrace();
	}
	return DBConstants.DROPDOWN;
}

public String getBuypolicy() {
	String result ="";
	String forward="inputNew";
	try {
		bean.setBrokerBranchList(commonDAO.getSubBranchList(bean.getLoginId(),bean.getLoginBranch()));
		result= service.getBuypolicy(bean);
		APiValidation();
		
		if(!hasActionErrors()) {
		if("Success".equalsIgnoreCase(result)) {
			forward="makePayment";
		}
	}else {
		service.updateQuoteDetailsNew(bean);
		bean.setBrokerBranchList(commonDAO.getSubBranchList(bean.getLoginId(),bean.getLoginBranch()));
		bean.setBrokerUserList(commonDAO.getBrokerUserList(bean.getQuoteNo(),bean.getLoginBranch()));
		bean.setLoadOrDiscPremium(StringUtils.isBlank(bean.getLoadOrDiscPremium())?"0":bean.getLoadOrDiscPremium());
		bean.setDisplay("policyInfo");
		forward="inputNew";
	}
	} catch (Exception e) {
		e.printStackTrace();
	}
	return forward;
}

public String makePay() {
	String forward ="makePayment";
	try {
		String result =service.makepay(bean);
		APiValidation();
		
		if(!hasActionErrors()) {
		if("Success".equalsIgnoreCase(result)) {
			payResponse();
			forward="policyInfo";
		}else {
			service.setCustDetail(bean, "");
			addActionError("Something Went Wrong Please try Again");
		}
		}else {
			service.setCustDetail(bean, "");
		}
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	return forward;
}


}