package com.maan.Travel.controller;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.maan.Travel.Services.TravelService;
import com.maan.common.DBConstants;
import com.maan.common.LogManager;
import com.maan.common.Validation;
import com.maan.common.login.LogInAction;
import com.maan.common.otp.OTPGenerator;
import com.maan.common.sms.SmsEmailDAO;
import com.maan.common.sms.SmsEmailUtil;
import com.maan.common.util.ResourceBundleUtil;
import com.maan.customer.dao.CustomerDAO;
import com.maan.customer.service.CustomerService;
import com.maan.payment.PaymentDAO;
import com.maan.payment.mtn.MtnService;
import com.maan.payment.mtn.model.Payer;
import com.maan.payment.mtn.model.ReqToPayIpModel;
import com.maan.userMgt.UserMgtAction;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.util.ArrayUtils;

import nl.captcha.Captcha;

public class TravelAction extends ActionSupport implements ModelDriven<TravelBean>{
	private TravelBean bean=new TravelBean(); 
	HttpServletRequest request=ServletActionContext.getRequest();
	HttpServletResponse response=ServletActionContext.getResponse();
	Map<String, Object> session=ActionContext.getContext().getSession();
	TravelService service=new TravelService();
	com.maan.common.dao.CommonDAO commonDAO = new com.maan.common.dao.CommonDAO();
	Validation validation=new Validation();
	private static final long serialVersionUID = 1L;
	private static final String DROPDOWN = "dropdown";
	private static final String FIELD = "ELEMENT_NAME";
	//private String brokerOne=getText("BROKER_ONE");
	private String endTypeId;
	private String fields;
	private int count;
	private List<Object> trList=null;
	private List<Object> trPolicyList=null;
	
	/*
	 *Get Drop Down List 
	 */
	public List<Object> getTitleList()
	{
		return commonDAO.getOptionsList("title", bean.getProductId(), getParams());
	}
	public List<Object> getBrokerList()
	{
		return commonDAO.getOptionsList("broker", bean.getProductId(), new Object[]{bean.getActualBranch(),bean.getIssuer(),bean.getBranchCode(),bean.getProductId()});
	}
	public List<Object> getExecutiveList()
	{
		Object[] objects=new String[]{"executive",bean.getProductId(),bean.getActualBranch(),"","","","","","","","",bean.getIssuer(),bean.getBrokerCode()};
		return commonDAO.getOptionsList("executive", bean.getProductId(), objects);
	}
	public List<Object> getCityList()
	{
		return commonDAO.getOptionsList("city", bean.getProductId(), getParams());
	}
	public List<Object> getCountryList()
	{
		return commonDAO.getOptionsList("country", bean.getProductId(), getParams());
	}
	public List<Object> getOccupationList()
	{
		return commonDAO.getOptionsList("occupation", bean.getProductId(), getParams());
	}
	public List<Object> getNationalityList()
	{																			 
		return commonDAO.getOptionsList("nationality", bean.getProductId(), getParams());
	}
	public List<Object> getRelationList()
	{
		return commonDAO.getOptionsList("relation", bean.getProductId(), getParams());
	}
	public List<Object> getSchemeCoverList()
	{
		return commonDAO.getOptionsList("schemecover", bean.getProductId(), getParams());
	}
	/*public List<Object> getTravelCoverList()
	{
		List tcover=new ArrayList();
		if(StringUtils.isNotBlank(bean.getSchemeCover()))
			tcover=commonDAO.getOptionsList("travelcover", getParams());
		return tcover;
	}*/
	public List<Object> getCoverPeriodList()
	{																			 
		return commonDAO.getOptionsList("coverPeriod", bean.getProductId(), getParams());
	}
	public List<Object> getTravelCoverList()
	{
		return commonDAO.getOptionsList("travelcover", bean.getProductId(), getParams());
	}
	@SuppressWarnings("unchecked")
	public List<Object> getCoveragesList()
	{
		List li=new ArrayList();
		if(StringUtils.isNotBlank(bean.getSchemeCover())&&StringUtils.isNotBlank(bean.getTravelCover()))
		{
			li=commonDAO.getOptionsList("coverages", bean.getProductId(), getParams());
		}
		return li;
	}
	public List<Object> getCoverPeriodMap(){
		return commonDAO.getOptionsList("coverPeriod", bean.getProductId(), getParams());
	}
	public List<Object> getBankNamelist(){
		return commonDAO.getOptionsList("BankList", bean.getProductId(), getParams());
	}
	public List<Object> getModeOfPaymentList() {
		List<Object> list = new ArrayList<Object>();
		List<Object> list1 = commonDAO.getOptionsList("modeOfPayment",bean.getProductId(), getParams());
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
	
	public Object[] getParams(){
		Object[] objects=new String[]{bean.getOption(),bean.getProductId(),bean.getBranchCode(),"","",bean.getSchemeCover(),bean.getOriginCountry(),
				bean.getDestCountry(),"",bean.getTravelCover(),"",bean.getLoginId(),bean.getBrokerCode()};
			return objects;
	}
		
	public String executiveList()
	{
		request.setAttribute(FIELD, "executive"); 
		return DROPDOWN;
	}
	public List<Object> getTrPolicyList() {
		return trPolicyList;
	}

	public void setTrPolicyList(List<Object> trPolicyList) {
		this.trPolicyList = trPolicyList;
	}

	public List<Object> getTrList() {
		return trList;
	}

	public void setTrList(List<Object> trList) {
		this.trList = trList;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getEndTypeId() {
		return endTypeId;
	}

	public void setEndTypeId(String endTypeId) {
		this.endTypeId = endTypeId;
	}
	public String getFields() {
		if(StringUtils.isNotEmpty(endTypeId)){
			String[] ids=endTypeId.split(",");
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
	private Map<String, String> genderList;
	private Map<String, String> coverageYNList;
	public Map<String, String> getGenderList() {
		genderList = new LinkedHashMap<String, String>();
		genderList.put("M", "Male");
		genderList.put("F", "Female");
		return genderList;
	}
	public Map<String, String> getCoverageYNList() {
		coverageYNList = new LinkedHashMap<String, String>();
		coverageYNList.put("Y", "Yes");
		coverageYNList.put("N", "No");
		return coverageYNList;
	}
	
	@SuppressWarnings("unchecked")
	public String getCoverInfo() {
		bean.setCover(service.getCoverInfo(bean.getProductId(),bean.getSchemeCover(), bean.getTravelCover(),bean.getBranchCode()));
		if(StringUtils.isNotEmpty(bean.getSchemeCover())&&StringUtils.isNotEmpty(bean.getTravelCover())){
			Map map=service.getCoveragesName(bean.getSchemeCover(), bean.getTravelCover(),bean.getBranchCode(),bean.getProductId());
			String schemeCoverage=map.get("SCHEME_NAME")==null?"":map.get("SCHEME_NAME").toString();
			String travelCoverage=map.get("OPTION_NAME")==null?"":map.get("OPTION_NAME").toString();
			if(!"None".equalsIgnoreCase(travelCoverage))
			{
				travelCoverage=travelCoverage.trim();
				bean.setScheme_Covers(schemeCoverage);
				bean.setTravel_Covers(travelCoverage);
			}
			else
			{
				bean.setScheme_Covers(schemeCoverage);
				bean.setTravel_Covers("None");
			}
		}
		bean.setOptionMode("coverInfo");
		return "coverSelection";
	}
	public String gettravelcoverinfo() {
		bean.setOptionMode("travelCoverInfo");
		return "coverSelection";
	}
	public String cancelReissue() {
		return "cancelReissue";
	}
	public String getCancelReissue() {
		bean.setDisplay(service.getCancelReissue(bean));
		return 	"cancelReissue";
	}
	public String getCustomer()
	{
		//bean.setDisplay("getCustomer");
		return INPUT;
	}
	public String init(){
		try{
			mapLoginId();
			bean.setMobileNo("09");
			if(StringUtils.isBlank(bean.getApplicationNo()) && getActionErrors().isEmpty()) {
				if("User".equalsIgnoreCase(bean.getUserType()) && getActionErrors().isEmpty() && (StringUtils.isBlank(bean.getQuoteNo()) || StringUtils.isBlank(bean.getApplicationNo()))){
					new CustomerDAO().setB2CCustomerDetails(bean, new CustomerDAO().getCustomerDetails(bean.getLoginId(),"2"));
				}
			}
			bean.setDisplay("getQuote");
			service.getSecondPageDts(bean);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return INPUT;
	}
	public String showQuote()
	{
		service.getBackShowQuote(bean);
		service.getDetailsView(bean);
		bean.setCover(service.getCoverInfo(bean.getProductId(),bean.getSchemeCover(), bean.getTravelCover(),bean.getBranchCode()));
		if(StringUtils.isNotBlank(bean.getLoginId()))
			service.setCustomerDetails(bean);
		bean.setDisplay("showQuote");
		return "showQuote";
	}
	public String travelCover(){
		request.setAttribute(FIELD, "travelCover");
		return DROPDOWN;
	}
	public String coverageList(){
		request.setAttribute(FIELD, "coverages");
		return DROPDOWN;
	}
	@SuppressWarnings("unchecked")
	public String getDelete(){
		service.getDeleteTraveller(bean);
		List trList = new ArrayList<String>();
		List snoList = new ArrayList<String>();
    	List tName = new ArrayList<String>();
    	List tdob = new ArrayList<String>();
    	List trelation = new ArrayList<String>();
    	List tnationality = new ArrayList<String>();
		for(int i=0,j=1;i<bean.getTravelList().size();i++)
		{
			if((bean.getSerialNos().get(i))!=bean.getSerialNo()){
				trList.add(j);
				snoList.add(bean.getSerialNos().get(i));
				tName.add(bean.getTravelNames().get(i));
				tdob.add(bean.getDobs().get(i));
				trelation.add(bean.getRelations().get(i));
				tnationality.add(bean.getNationalitys().get(i));
				j++;
			}
		}
		bean.setTravelList(trList);
		bean.setSerialNos(snoList);
		bean.setTravelNames(tName);
		bean.setDobs(tdob);
		bean.setRelations(trelation);
		bean.setNationalitys(tnationality);
		return INPUT;
	}
	public String view() {
 		if("profile".equalsIgnoreCase(bean.getSelection())){
			trList=service.getDetailsView(bean);
			trPolicyList=service.getPolicyView(bean);
		}
		else if("viewSave".equalsIgnoreCase(bean.getSelection()))
		{
			getValidate("viewSave");
			if(getActionErrors().isEmpty()){
				try{
					service.updateCorrections(bean);
					bean.setSelection("updateSuccess");
				}
				catch(Exception e){
					LogManager.debug(e);
				}
			}
			else{
				//service.getSecondPageDts(bean);
			}
		}
		else{
			count=service.getEffectiveDate(bean);
			if(count>0){
				service.getSecondPageDts(bean);
				trList=service.getDetailsView(bean);
				trPolicyList=service.getPolicyView(bean);
			}
		}
		return "viewCustomer";
	}
	@SuppressWarnings("unchecked")
	public String getQuote(){
		String result="",forward=INPUT;
		try{
			mapLoginId();
			if("getScheme".equalsIgnoreCase(bean.getActionType()))
			{
				getValidate("customerInfo");
				getValidate(bean.getActionType());
				if(!hasActionErrors()){
					/*bean.setCustomerId(service.insertOrUpdateCustomerInfo(bean,bean.getIssuer()));*/
					/*if(StringUtils.isNotBlank(bean.getCustnrc1()) && StringUtils.isNotBlank(bean.getCustnrc3()) && StringUtils.isNotBlank(bean.getCustnrc3()))
						bean.setCustnrc(bean.getCustnrc1()+"/"+bean.getCustnrc2()+"/"+bean.getCustnrc3());
					else 
						bean.setCustnrc("");*/
					bean.setCustomerId(new CustomerService().insertCustomerDetails(bean.getCustomerId(),bean.getLoginId(),bean.getBranchCode(),bean.getProductId(),bean.getTitle(),bean.getCustomerName(),bean.getMobileNo(),bean.getEmail(),bean.getAddress1(),bean.getAddress2(),bean.getPoBox(),bean.getCity(),bean.getCoreAppCode(),bean.getClientCustomerId(),bean.getCustArNo(),bean.getCustLastName(),bean.getCustPassportNo(),bean.getCustdob(),bean.getCustAlterMobileNo(),bean.getCustLandLineNo(),bean.getCustomerType(),bean.getCompanyRegNo(),bean.getCustNameArabic(),bean.getCustdobar(),bean.getGender(),bean.getOccupation(),"detailQuote",bean.getCustnrc1(),bean.getCustnrc2(),bean.getCustnrc3()));
					bean.setCover(service.getCoverInfo(bean.getProductId(),bean.getSchemeCover(), bean.getTravelCover(),bean.getBranchCode()));
					result=service.getUpdateTravellersInfo(bean);
					if("SUCCESS".equalsIgnoreCase(result)){
						result=service.getPremium(bean,"C");
					}
					if(!"admin".equalsIgnoreCase(bean.getUser())){
						String[] optionCovers = request.getParameterValues("optionalCovers"+bean.getSchemeCover());
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
						bean.setOptionalCovers(optionalCovers);
						//service.insertOrUpdateCustomerInfo(bean);			
						result=service.getUpdateOptionCovers(bean);
						String[] cov=request.getParameterValues("coverages")==null?new String[0]:request.getParameterValues("coverages");
						List<String> coverages=new ArrayList();
						for(int i=0;i<cov.length;i++){
								coverages.add(cov[i]);
						}
						bean.setCoverages(coverages);
						service.getInsertOrUpdateTravelCoverDtls(bean);	
					}
					if("SUCCESS".equalsIgnoreCase(result) || "admin".equalsIgnoreCase(bean.getUser())){
						if(!"B2C".equalsIgnoreCase(bean.getLoginType())) {
							forward = viewCoverDetails();
							if(StringUtils.isNotBlank(bean.getLoginId()))
								service.setCustomerDetails(bean);
						} else {
							return "redirectEditUser";
						}
					}else {
						addActionError(getText("error.travel.update"));
						if(!StringUtils.isBlank(bean.getInceptionDt()))
							bean.setInceptionDt(bean.getInceptionDt());
					}
				}else{
					forward=INPUT;
				}
			}else
			{
				getValidate("customerInfo");
				getValidate(bean.getActionType());
				if("User".equalsIgnoreCase(bean.getUserType()) || "B2C".equalsIgnoreCase(bean.getLoginType()) && bean.getCoverages()==null){
					getValidate("captcha");
				}
				if(getActionErrors().isEmpty())
				{				
					/*if(StringUtils.isNotBlank(bean.getCustnrc1()) && StringUtils.isNotBlank(bean.getCustnrc3()) && StringUtils.isNotBlank(bean.getCustnrc3()))
						bean.setCustnrc(bean.getCustnrc1()+"/"+bean.getCustnrc2()+"/"+bean.getCustnrc3());
					else 
						bean.setCustnrc("");*/
					bean.setCustomerId(new CustomerService().insertCustomerDetails(bean.getCustomerId(),bean.getLoginId(),bean.getBranchCode(),bean.getProductId(),bean.getTitle(),bean.getCustomerName(),bean.getMobileNo(),bean.getEmail(),bean.getAddress1(),bean.getAddress2(),bean.getPoBox(),bean.getCity(),bean.getCoreAppCode(),bean.getClientCustomerId(),bean.getCustArNo(),bean.getCustLastName(),bean.getCustPassportNo(),bean.getCustdob(),bean.getCustAlterMobileNo(),bean.getCustLandLineNo(),bean.getCustomerType(),bean.getCompanyRegNo(),bean.getCustNameArabic(),bean.getCustdobar(),bean.getGender(),bean.getOccupation(),"detailQuote",bean.getCustnrc1(),bean.getCustnrc2(),bean.getCustnrc3()));
					bean.setCover(service.getCoverInfo(bean.getProductId(),bean.getSchemeCover(), bean.getTravelCover(),bean.getBranchCode()));
					result=service.getUpdateTravellersInfo(bean);
					if("SUCCESS".equalsIgnoreCase(result)){
						String smsRequired=new SmsEmailDAO().smsRequired(bean.getQuoteNo(),"quote");
						if("Y".equalsIgnoreCase(smsRequired))
							new SmsEmailUtil(SmsEmailUtil.GET_QUOTE, bean.getQuoteNo()).send();
						new SmsEmailDAO().updateSmsStatus(bean.getQuoteNo(),"quote");
						if(!"getSave".equalsIgnoreCase(bean.getActionType())){
							result=service.getPremium(bean,"C");
							if("SUCCESS".equalsIgnoreCase(result)){
								if("getQuote".equalsIgnoreCase(bean.getActionType())){
									service.getBackShowQuote(bean);
								}
								else{
									bean.setDisplay("getQuote");								
								}
							}
							else
								addActionError(getText("error.travel.premium"));
						}else{
							bean.setDisplay("showQuoteInfo");
						}
					}
					else
						addActionError(getText("error.travel.update"));
				}
				bean.setDisplay("getQuote");
				/*if (StringUtils.isBlank(bean.getInceptionDt()))
					bean.setInceptionDt(dateFormat(bean.getInceptionDt()));*/
				
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return forward;
	}
	
	public String otpLogin(){
		LogManager.info("Enter into otpLogin");
		String result="",forward=INPUT;
		try{
			mapLoginId();
				getValidate("customerInfo");
				getValidate(bean.getActionType());
				if("User".equalsIgnoreCase(bean.getUserType()) || "B2C".equalsIgnoreCase(bean.getLoginType())){
					getValidate("captcha");
				}
				if(getActionErrors().isEmpty())
				{				
					/*if(StringUtils.isNotBlank(bean.getCustnrc1()) && StringUtils.isNotBlank(bean.getCustnrc3()) && StringUtils.isNotBlank(bean.getCustnrc3()))
						bean.setCustnrc(bean.getCustnrc1()+"/"+bean.getCustnrc2()+"/"+bean.getCustnrc3());
					else 
						bean.setCustnrc("");*/
					bean.setCustomerId(new CustomerService().insertCustomerDetails(bean.getCustomerId(),bean.getLoginId(),bean.getBranchCode(),bean.getProductId(),bean.getTitle(),bean.getCustomerName(),bean.getMobileNo(),bean.getEmail(),bean.getAddress1(),bean.getAddress2(),bean.getPoBox(),bean.getCity(),bean.getCoreAppCode(),bean.getClientCustomerId(),bean.getCustArNo(),bean.getCustLastName(),bean.getCustPassportNo(),bean.getCustdob(),bean.getCustAlterMobileNo(),bean.getCustLandLineNo(),bean.getCustomerType(),bean.getCompanyRegNo(),bean.getCustNameArabic(),bean.getCustdobar(),bean.getGender(),bean.getOccupation(),"detailQuote",bean.getCustnrc1(),bean.getCustnrc2(),bean.getCustnrc3()));
					bean.setCover(service.getCoverInfo(bean.getProductId(),bean.getSchemeCover(), bean.getTravelCover(),bean.getBranchCode()));
					result=service.getUpdateTravellersInfo(bean);
					if("SUCCESS".equalsIgnoreCase(result)){
						int otp=new OTPGenerator().getOTP();
						//bean.setMyOtp(otp);
						int mailOtp=new OTPGenerator().getOTP();
						//(String) getSession().get("Id")
						bean.setOtpId(new OTPGenerator().insertOTP(new String[]{bean.getOtpId(),"B2C_QUOTE",
								Integer.toString(otp),bean.getMobileNo(),bean.getEmail(),"","N",Integer.toString(mailOtp)}));
						//new SmsEmailUtil("GET_OTP",bean.getOtpId()).send();
						new SmsEmailUtil("GET_OTP",Integer.toString(otp),bean.getMobileNo(),new OTPGenerator().getOtpExpiry(bean.getOtpId()),Integer.toString(mailOtp),bean.getEmail()).send();
						bean.setOtpStatus("N");
						bean.setOtp("");
						bean.setMailOtp("");
						forward=otpPage();
						//bean.setDisplay("otpVerify");
						//forward = "editMotorUser";
					}
					else{
						addActionError(getText("error.travel.update"));
						bean.setDisplay("getQuote");
					}
				}else
					bean.setDisplay("getQuote");
				/*if (StringUtils.isBlank(bean.getInceptionDt()))
					bean.setInceptionDt(dateFormat(bean.getInceptionDt()));*/
				
				LogManager.info("Exit from otpLogin, forward=> "+forward);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return forward;
	}
	public String otpPage(){
		LogManager.info("Enter into otpPage");
		String forward = INPUT;
		try {
			if(!hasActionErrors())
				new CustomerDAO().setCustomerDetails(bean);
			//service.getCustomerDetails(bean);
			String count = service.getEmailCount(bean);
			LogManager.info("Enter into otpPage, count=>"+count);
			if(Integer.parseInt(count) > 0){
				bean.setDisplay("otpVerify");
			} else {
				bean.setCity(StringUtils.isBlank(bean.getCity())?ResourceBundleUtil.findDefaultText("MOTOR_DEFAULT_CITY"):bean.getCity());
				bean.setDisplay("otpVerify");
			}
			forward = "editMotorUser";
			LogManager.info("Exit from otpPage, forward=> "+forward);

		}catch (Exception e) {
			e.printStackTrace();
		}
		return forward;
	}
	public String quoteDtl(){
		LogManager.info("Enter into quoteDtl");
		String result="",forward=INPUT;
		try{
			mapLoginId();
			getValidate("otpVerfiy");
				if(getActionErrors().isEmpty()){
					String smsRequired=new SmsEmailDAO().smsRequired(bean.getQuoteNo(),"quote");
					if("Y".equalsIgnoreCase(smsRequired))
						new SmsEmailUtil(SmsEmailUtil.GET_QUOTE, bean.getQuoteNo()).send();
					new SmsEmailDAO().updateSmsStatus(bean.getQuoteNo(),"quote");
					LogManager.info("Action Type 1 ==> "+bean.getActionType());
					if(!"getSave".equalsIgnoreCase(bean.getActionType())){
						result=service.getPremium(bean,"C");
						LogManager.info("Result from premium ==> "+result);
						if("SUCCESS".equalsIgnoreCase(result)){
							LogManager.info("Action Type 2 ==> "+bean.getActionType());
							if("getQuote".equalsIgnoreCase(bean.getActionType())){
								service.getBackShowQuote(bean);
								bean.setDisplay("getQuote");
								bean.setLoginId(bean.getLoginId()==null?bean.getMobileNo():bean.getLoginId());
								bean.setOtpStatus("Y");
								UserMgtAction mgt=new UserMgtAction();			
								mgt.setBeanForMobile(bean.getMobileNo(), bean.getEmail(),bean.getCustomerName() ,bean.getCustNameArabic(),
								bean.getCustdob(),bean.getCompanyRegNo(),bean.getCustomerId(),bean.getApplicationNo(),bean.getQuoteNo(),bean.getProductId());
								LogInAction login=new LogInAction();
								login.setSession(session);								
								login.mobLogin(bean.getMobileNo(), "Admin@01");
								login.submit();
								session.put("product_id",bean.getProductId());
								session.put("user",bean.getMobileNo());
								
								
							}
							else{
								bean.setDisplay("getQuote");								
							}
						}
						else
							addActionError(getText("error.travel.premium"));
					}else{
						bean.setDisplay("showQuoteInfo");
					}
				}
				else{
					bean.setOtpStatus("N");
					forward = otpPage();
				}
				/*if (StringUtils.isBlank(bean.getInceptionDt()))
					bean.setInceptionDt(dateFormat(bean.getInceptionDt()));*/
				
				LogManager.info("Exit from quoteDtl, forward=> "+forward);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return forward;
	}
	public String viewCoverDetails(){
		String forward=INPUT;
		try{
			if(!"admin".equalsIgnoreCase(bean.getUser())){
			service.getPremium(bean,"B");
			}
			service.getBackShowQuote(bean);
			bean.setCover(service.getCoverInfo(bean.getProductId(),bean.getSchemeCover(), bean.getTravelCover(),bean.getBranchCode()));
			bean.setDisplay("showQuote");
			forward = "showQuote";
			if(StringUtils.isNotBlank(bean.getReferralMsg())&&!"admin".equalsIgnoreCase(bean.getUser())){
				//session.put("QuoteNo",bean.getQuoteNo());
				request.setAttribute("reqFrom","Referral");
				try{
						new com.maan.Home.MasterController.HomeMailController().processResult(request,response);
				}catch(Exception e)
				{
					LogManager.debug(e);
				}
				//session.remove("QuoteNo");
				return "policyInfo";
			}else
			{
				bean.setDisplay("showQuote");
				forward = "showQuote";
			}
			if(!StringUtils.isBlank(bean.getInceptionDt()))
				bean.setInceptionDt(bean.getInceptionDt());
		}catch (Exception e) {
			e.printStackTrace();
		}
		return forward;
	}
	public String buyPolicy(){
		return viewCoverDetails();
	}
	public String getCalculate()
	{
		double premium=0.0;
		for(int i=0;i<bean.getTravelPremium().size();i++){
			premium +=bean.getTravelPremium().get(i);
			}
		/*for(int i=0;i<bean.getCoveragePremium().size();i++){
			premium +=bean.getCoveragePremium().get(i);
		}*/
		premium = premium - bean.getDiscountAmt();
		bean.setFinalPremium(Double.toString(premium));
		premium = premium + bean.getPolicyFee();
		if("+".equals(bean.getSign()))
		{
			premium = premium + bean.getLoadOrDiscPremium();
		}
		else
		{
			premium = premium - bean.getLoadOrDiscPremium();
		}
		bean.setTotalPremium(premium);
		return "showQuote";
	}
	public List<CoverageBean> getOptCoverList()
	{
		return service.getOptCoverList(bean.getApplicationNo(),bean.getTravelCover()); 
	}
	
	public List<Object> getPolicyInformation() {
		return service.getPolicyInformation(bean.getQuoteNo());
	}
	public String getGeratePolicy() //getGeratePolicyTravel
	{
		String forward="showQuote";
		String result="";
		try{
			mapLoginId();
			if("Y".equalsIgnoreCase(bean.getGeneratePolicyYN()))
				getValidate("paymentInfo");
			getValidate("getPolicy");
			if(!"admin".equals(bean.getUser()))
				getValidate("customerDetailQuote");
			//service.updatePersonalInfo(bean);
			if(!hasActionErrors()){
				if("admin".equals(bean.getUser())){
					//Admin Referral accepting
					result=service.getAdminReferralUpdation(bean);
					if("SUCCESS".equalsIgnoreCase(result)){
						session.put("QuoteNo",bean.getQuoteNo());
						request.setAttribute("reqFrom",bean.getUser());
						request.setAttribute("quote_status",bean.getReferralMsg());
						request.setAttribute("adminReferalRemarks",bean.getAdminRemarks());
						try{
								new com.maan.Home.MasterController.HomeMailController().processResult(request,response);
						}catch(Exception e)
						{
							LogManager.debug(e);
						}
						session.remove("QuoteNo");
						forward=INPUT;
						bean.setDisplay("showRefInfo");
					}else
						addActionError(getText("error.travel.update"));	
				} else {
					if("otpFlow".equalsIgnoreCase(bean.getDisMode()))
						new CustomerService().insertCustomerDetails(bean.getCustomerId(),bean.getLoginId(),bean.getBranchCode(),bean.getProductId(),bean.getTitle(),bean.getCustomerName(),bean.getMobileNo(),bean.getEmail(),bean.getAddress1(),bean.getAddress2(),bean.getPoBox(),bean.getCity(),bean.getCoreAppCode(),bean.getClientCustomerId(),bean.getCustArNo(),bean.getCustLastName(),bean.getCustPassportNo(),bean.getCustdob(),bean.getCustAlterMobileNo(),bean.getCustLandLineNo(),bean.getCustomerType(),bean.getCompanyRegNo(),bean.getCustNameArabic(),bean.getCustdobar(),bean.getGender(),bean.getOccupation(),"detailQuote",bean.getCustnrc1(),bean.getCustnrc2(),bean.getCustnrc3(),bean.getDisMode());
					else
						new CustomerService().insertCustomerDetails(bean.getCustomerId(),bean.getLoginId(),bean.getBranchCode(),bean.getProductId(),bean.getTitle(),bean.getCustomerName(),bean.getMobileNo(),bean.getEmail(),bean.getAddress1(),bean.getAddress2(),bean.getPoBox(),bean.getCity(),bean.getCoreAppCode(),bean.getClientCustomerId(),bean.getCustArNo(),bean.getCustLastName(),bean.getCustPassportNo(),bean.getCustdob(),bean.getCustAlterMobileNo(),bean.getCustLandLineNo(),bean.getCustomerType(),bean.getCompanyRegNo(),bean.getCustNameArabic(),bean.getCustdobar(),bean.getGender(),bean.getOccupation(),"detailQuote",bean.getCustnrc1(),bean.getCustnrc2(),bean.getCustnrc3());
					if("Y".equalsIgnoreCase(bean.getReferralYN())) {
						session.put("QuoteNo",bean.getQuoteNo());
						request.setAttribute("reqFrom","Referral");
						try{
								new com.maan.Home.MasterController.HomeMailController().processResult(request,response);
						}catch(Exception e)
						{
							LogManager.debug(e);
						}
						session.remove("QuoteNo");
						service.updReferralStatus(bean.getActionType(),bean.getReferralComments(),bean.getQuoteNo(),bean.getReferralYN());
					} 
					if("Y".equalsIgnoreCase(bean.getQuoteEmailYN())) {
						session.put("QuoteNo",bean.getQuoteNo());
						request.setAttribute("reqFrom","Customer");
						try{
							new com.maan.Home.MasterController.HomeMailController().processResult(request,response);
						}catch(Exception e)
						{
							LogManager.debug(e);
						}
						session.remove("QuoteNo");
					}
					if("Y".equalsIgnoreCase(bean.getPolicyEmailYN())){
						session.put("QuoteNo",bean.getQuoteNo());
						request.setAttribute("Option","All");
						request.setAttribute("reqFrom","Schedule");
						new com.maan.Travel.Pdf.PDFCreator().processResult(request,response);
						new com.maan.Home.MasterController.HomeMailController().processResult(request,response);
						session.remove("QuoteNo");
					}
					if("Y".equalsIgnoreCase(bean.getGeneratePolicyYN())) {
						service.getGeratePolicy(bean);
						commonDAO.updateInstallmentDetail(bean.getQuoteNo(),"HSS",bean.getInstallmentYN());
						bean.setMerchant_reference(
							new PaymentDAO().insertPaymentDetails(
									bean.getModeOfPayment(), bean.getChequeNo(), bean.getChequeDate(), bean.getChequeAmount(),
									Double.toString(bean.getTotalPremium()),bean.getBankName(), bean.getMicrCode(), bean.getCashDepositBank(),
									bean.getCashAmount(), bean.getCashChellanNo(), bean.getCashInstrumentDate(), bean.getApplicationNo(), bean.getQuoteNo(),
									bean.getProductId(), bean.getMerchant_reference(), bean.getEmail(), bean.getCustomerName(),
									bean.getBranchCode(), "ZMW", bean.getInstallmentYN(), bean.getMobileNo(), bean.getDeviceType(), bean.getMtnMobileNo()
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
						}else {
							forward = "onlinePaymentReq";
						}
					}else{
						if("N".equalsIgnoreCase(commonDAO.checkPolicyGeneration(bean.getApplicationNo()))) {
							forward = updatePolicyInfo();
						}else{
							forward = "policyInfo";
						}
					}
				}
			}else{
				/*service.getSecondPageDts(bean);*/
				mapLoginId();
				bean.setShowReferralYN(commonDAO.getReferralYN(bean.getLoginId()));
				bean.setCover(service.getCoverInfo(bean.getProductId(),bean.getSchemeCover(), bean.getTravelCover(),bean.getBranchCode()));
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return forward;
	}
	
	public String updatePolicyInfo() {
		bean.setDisplay("showQuoteInfo");
		@SuppressWarnings("unused")
		String result=commonDAO.updateHomeQuoteStatus(bean.getApplicationNo(),bean.getMerchant_reference(),bean.getQuoteNo(),bean.getProductId(),bean.getBranchCode());
		String paymentType=commonDAO.getPaymentType(bean.getQuoteNo());
		if("SUCCESS".equalsIgnoreCase(result)) {
			
		}else if("101".equalsIgnoreCase(paymentType)){
			addActionError("MTN Payment Failed");
			return "showQuote";
		}
		return "policyInfo";
	}
	
	private void getValidate(String type)
	{
		LinkedList<String> list=service.getValidate(bean, type);
		for (String st : list)
		{
			if(st.indexOf("#")!=-1)
			{
				String args[]=(st.indexOf("~")!=-1?(st.substring(st.indexOf('#')+1, st.lastIndexOf('#'))).split("~"):new String[]{st.substring(st.indexOf('#')+1)});
				addActionError(getText(st.substring(0,st.indexOf('#')),args));
			}else
			{
				addActionError(getText(st));	
			}
		}
	}

	@SuppressWarnings("unchecked")
	public TravelBean getModel() {
		String branchCode=(String)session.get("LoginBranchCode");
		String actualBranch=(String)session.get("adminBranch");
		String productId=(String) session.get("product_id");
		String issuer=(String)session.get("RSAISSUER");
		Captcha captcha = null;
		captcha = (Captcha) session.get(Captcha.NAME);
		if(StringUtils.isBlank(issuer)){
			String brokerCode="";
			brokerCode =(String) session.get("brokerCode");
			bean.setBrokerCode(brokerCode);
		}
		String loginId=(String)session.get("user");
		String userType=(String)session.get("usertype");
		String user=(String)session.get("user1");
		String loginType=session.get("LoginType")==null?"":session.get("LoginType").toString();
		if(request.getParameter("productId")!=null && !"".equals(request.getParameter("productId"))){
			session.put("product_id", request.getParameter("productId"));
			productId=(String) session.get("product_id");
		}
		if(StringUtils.isBlank(bean.getCurrencyType())){
			Map<String,Object> res = (Map<String, Object>) session.get("BrokerDetails");
			if(res!=null && res.size()>0)
				bean.setCurrencyType(res.get("CurrencyName")==null?"":res.get("CurrencyName").toString());
		}
		bean.setBranchCode(branchCode);
		bean.setActualBranch(actualBranch);
		bean.setProductId(productId);
		bean.setLoginId(loginId);
		bean.setIssuer(issuer);
		bean.setUserType(userType);
		bean.setDestCountry("1");
		bean.setOriginCountry("1");
		bean.setOriginCountry("1");
		bean.setLoginType(loginType);
		bean.setUser(user);
		bean.setCaptcha(captcha);
		return bean;
	}
	private void mapLoginId() throws Exception {
		if(StringUtils.isNotEmpty(bean.getIssuer())) {
			bean.setLoginId(commonDAO.getSingleInfo("loginId", new String[]{bean.getBrokerCode()}));
		}
		else {
			bean.setLoginId(bean.getLoginId());
		}
	}


}
