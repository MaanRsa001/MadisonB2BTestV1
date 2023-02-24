package com.maan.Home.Controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.json.annotations.JSON;

import com.maan.Home.Service.HomeService;
import com.maan.adminnew.paymentProcess.PaymentProcessService;
import com.maan.common.DBConstants;
import com.maan.common.LogManager;
import com.maan.common.dao.CommonDAO;
import com.maan.common.sms.CryptoService;
import com.maan.common.sms.SmsEmailUtil;
import com.maan.common.util.ResourceBundleUtil;
import com.maan.customer.dao.CustomerDAO;
import com.maan.customer.service.CustomerService;
import com.maan.payment.PaymentDAO;
import com.maan.payment.mtn.MtnService;
import com.maan.payment.mtn.model.Payer;
import com.maan.payment.mtn.model.ReqToPayIpModel;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import nl.captcha.Captcha;

public class HomeAction extends ActionSupport implements ModelDriven<HomeBean> {
	private static final long serialVersionUID = 1L;
	private HomeBean bean=new HomeBean();
	//private HttpServletRequest request=ServletActionContext.getRequest();
	
	private Map<String,List<Map<String, Object>>> dynamicList=new HashMap<String, List<Map<String,Object>>>();
	private HomeService service=new HomeService();
	/********************* Premium Status String - Start *****************************/
	private static final String HOME_PREMIUMSTATUS = "home";
	private static final String PACOVER_PREMIUMSTATUS = "paCover";
	private static final String MOTOR_PREMIUMSTATUS = "motor";
	/********************* Premium Status String - End *****************************/
	/********************* Return String - Start *****************************/
	private static final String PREMIUM = "premium";
	private static final String SUBLIST = "sublist";
	private static final String ADDROW = "addRow";
	private static final String COVERAGEUPLOAD = "coverageUpload";
	//private static final String UWMENU = "uwmenu";
	private static final String POLICY_INFO = "policyInfo";
	private static final String HOME_POLICY = "policy";
	private static final String FARMER_PA_COVER = "farmerPACover";
	private static final String FARM_PA_PREMIUM_INFO = "farmPAPremiuminfo";
	private static final String FARMER_MOTOR = "farmerMotor";
	private static final String TRACTOR_PREMIUM_INFO = "tractorPremiuminfo";
	private static final String HOME_PREMIUMINFO = "homePremiuminfo";
	/********************* Return String - End *****************************/
	/******* Farmer Insurance | Motor - start *******/
	com.maan.common.dao.CommonDAO commonDAO = new com.maan.common.dao.CommonDAO();
	private static final String FIELD = "ELEMENT_NAME";
	private static final String DROPDOWN = "dropdown";
	/******* Farmer Insurance | Motor - end *******/
	
	private List<Object> premium=new ArrayList<Object>();
	
	private List <HomeBean> sumInsured;
	
	public List<HomeBean> getSumInsured() {
		return sumInsured;
	}
	public void setSumInsured(List<HomeBean> sumInsured) {
		this.sumInsured = sumInsured;
	}
	public Map<String,List<Map<String, Object>>> getDynamicList() {
		return dynamicList;
	}
	public void setDynamicList(Map<String,List<Map<String, Object>>> dynamicList) {
		this.dynamicList = dynamicList;
	}

	public List<Map<String,Object>> getBusinessTypeList() {
		return service.getBusinessTypeList(bean.getSchemeId());
	}

	public List<Object>getTypeClaimList(){
		return service.getTypeClaimList(bean);
	}

	public String getBackDate(){
		return commonDAO.getBackDatesAllowed((String)bean.getLoginId(),(String)bean.getUserType(),bean.getProductId(),bean.getBranchCode(), bean.getSchemeId());
	}

	public Object[] getParams(String type){
		Object[] objects = null;
		if("PolicyExpirydate".equals(type)) {
			objects = new String[]{bean.getOption(),bean.getProductId(),bean.getBranchCode(),bean.getInceptionDt(),"","",bean.getOriginCountry(),
					bean.getDestCountry(),"","","",bean.getLoginId(),""};
		} else {
			objects=new String[]{bean.getOption(),bean.getProductId(),bean.getBranchCode(),"","","",bean.getOriginCountry(),
					bean.getDestCountry(),"","","",bean.getLoginId(),""};
		}
		return objects;
	}

	public List<Object> getTitleList(){
		return commonDAO.getOptionsList("title",bean.getProductId(), getParams("title"));
	}

	public List<Object> getCityList(){
		return commonDAO.getOptionsList("city",bean.getProductId(), getParams("city"));
	}
	public List<Object> getOccupationList() {
		return commonDAO.getOptionsList("Occupation",bean.getProductId(), getParams("Occupation"));
	}
	public List<Object> getBrokerList(){
		return new com.maan.common.dao.CommonDAO().getOptionsList("broker", bean.getProductId(), new Object[]{bean.getBranchCode(),bean.getIssuer(),bean.getBranchCode(),bean.getProductId()});
	}
	public List<Object> getExecutiveList(){
		Object[] objects=new String[]{"executive",bean.getProductId(),bean.getBranchCode(),"","","","","","","","",bean.getIssuer(),bean.getBrokerCode()};
		return new com.maan.common.dao.CommonDAO().getOptionsList("executive", bean.getProductId(), objects);
	}
	public List<Object> getPremium() {
		return premium;
	}
	public List<Object> getBankNamelist(){
		return new com.maan.common.dao.CommonDAO().getOptionsList("BankList", bean.getProductId(), getParams("BankList"));
	}
	
	/**
	 * Mode Of Payment List Start
	 */
	
	public List<Object> getModeOfPaymentList() {
		List<Object> list = new ArrayList<Object>();
		List<Object> list1 = commonDAO.getOptionsList("modeOfPayment",bean.getProductId(), getParams("modeOfPayment"));
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
	
	/**
	 * Mode Of Payment List End
	 */
	
	/**
	 * Bank Details Start
	 */
	
	public String bankDtlsAjax(){
		getRequest().setAttribute(FIELD, "paymentBankDetails");
		return "homeAjax";
	}
	
	@JSON(serialize=false)
	public Map<String,Object> getBankDetails() {
		return new PaymentDAO().getBankInfoAjax(bean.getModeOfPay(),bean.getCurrencyType());
	}
	
	/**
	 * Bank Details End
	 */
	
	/*public String getCustomer(){
		bean.setDisplay("getCustomer");
		return INPUT;
	}*/
	public String getHelpInfo(){
		bean.setHelpInfoList(service.getHelpInfo(bean));
		return "helpinfo";
	}
	
	/**
	 * Non Motor - First Page Start
	 */
	
	public String packageSelection() {
		try {
			getEditQuoteDetails();
			service.setCustDetail(bean);
			if(StringUtils.isBlank(bean.getMobileNo())) {
				bean.setMobileNo("09");
			}
		} catch (Exception e) {
			addActionError("Something Went Wrong. Please Try Again Later");
			LogManager.info("Exception @ HomeAction.packageSelection(): "+e);
			e.printStackTrace();
		}
		return "homeLocSelection";
	}
	
	public void getEditQuoteDetails(){
		service.editQuoteDetails(bean);
	}
	
	/**
	 * Non Motor - First Page End
	 */
	
	/**
	 * Non Motor - Second Page Start
	 */
	
	public String savePackage(){
		String returnz="homeLocSelection";
		try{
			getValidate("customerInfo");
			getValidate("getQuoteHomeNew");
			if(!hasActionErrors()){
				if(StringUtils.isBlank(bean.getQuoteNo())){
					bean.setQuoteNo(new CommonDAO().getSequenceNo("Quote",bean.getProductId(),bean.getBranchCode(),"",""));
				}
				bean.setCustomerId(new CustomerService().insertCustomerDetails(bean.getCustomerId(),bean.getLoginId(),bean.getBranchCode(),bean.getProductId(),bean.getTitle(),bean.getCustomerName(),bean.getMobileNo(),bean.getEmail(),bean.getAddress1(),bean.getAddress2(),bean.getPoBox(),bean.getCity(),bean.getCoreAppCode(),bean.getClientCustomerId(),bean.getCustArNo(),bean.getCustLastName(),bean.getCustPassportNo(),bean.getCustdob(),bean.getCustAlterMobileNo(),bean.getCustLandLineNo(),bean.getCustomerType(),bean.getCompanyRegNo(),bean.getCustNameArabic(),bean.getCustdobar(),bean.getGender(),bean.getOccupation(),"detailQuote",bean.getCustnrc1(),bean.getCustnrc2(),bean.getCustnrc3(),""));
				if(StringUtils.isNotBlank(bean.getCustomerId())){
					if(service.manipulateHomePosition(bean)){
						service.getQuoteInfo(bean);
						service.updateLocationDtls(bean);
						return schemeSelection();
					}else{
						throw new Exception("Error in Manipulating Home Position");
					}
				}else{
					throw new Exception("Error in Manipulating Personal Info");
				}
			}
		}catch(Exception e){
			addActionError("Something Went Wrong. Please Try Again Later");
			LogManager.info("Exception @ HomeAction.savePackage(): "+e);
			e.printStackTrace();
		}
		return returnz;
	}
	
	public String schemeSelection(){
		try{
			service.editLocationScheme(bean);
		}catch(Exception e){
			addActionError("Something Went Wrong. Please Try Again Later");
			LogManager.info("Exception @ HomeAction.schemeSelection(): "+e);
			e.printStackTrace();
		}
		return "schemeSelection";
	}
	
	public List<Map<String, Object>> getActiveLocationDtls(){
		return service.activeLocationDtls(bean);
	}
	
	public List<Map<String, Object>> getActiveSchemeList(){
		return service.activeSchemeList(bean);
	}
		
	/**
	 * Non Motor - Second Page End
	 */
	
	/**
	 * Non Motor - Third Page Start
	 */
	
	public String saveScheme(){
		String returnz="schemeSelection";
		try{
			service.singleLocationAutoSelect(bean);
			getValidate("schemeSelection");
			if(!hasActionErrors()){
				if(service.updateLocationScheme(bean)){
					return insLocDetails();
				}else{
					throw new Exception("Error in Updating Location Scheme");
				}
			}
		}catch(Exception e){
			addActionError("Something Went Wrong. Please Try Again Later");
			LogManager.info("Exception @ HomeAction.saveScheme(): "+e);
			e.printStackTrace();
		}
		return returnz;
	}
	
	/**
	 * Non Motor - Third Page End
	 */
	
	/**
	 * Location Start
	 */
	
	public String locationAjax(){
		bean.setLocationDtlsList(service.locationDtlsList(bean));
		return "jsonAjax";
	}
	
	public String openLocationDetails(){
		try{
			if("edit".equalsIgnoreCase(bean.getMode())){
				boolean status = service.editLocationDetails(bean);
				if(!status){
					addActionError("Something Went Wrong. Please Try Again Later");
				}
			}
		}catch(Exception e){
			LogManager.info("Exception @ HomeAction.openLocationDetails(): "+e);
			e.printStackTrace();
		}finally{
			getRequest().setAttribute(FIELD, "openLocationDetails");
		}
		return DROPDOWN;
	}
	
	public String manipulateLocationDetails(){
		boolean status = false;
		try{
			if(!"delete".equalsIgnoreCase(bean.getMode())){
				if(StringUtils.isBlank(bean.getLocationDesc())){
					addActionError("Please Enter Location Name ");
				}
				if(StringUtils.isBlank(bean.getLocStatus())){
					addActionError("Please Choose Status");
				}
			}
			if(!hasActionErrors()){
				if(StringUtils.isBlank(bean.getApplicationNo())){
					bean.setApplicationNo(new CommonDAO().getSequenceNo("Application",bean.getProductId(),bean.getBranchCode(),"",""));
				}
				status = service.manipulateLocationDetails(bean);
			}
		}catch(Exception e){
			LogManager.info("Exception @ HomeAction.manipulateLocationDetails(): "+e);
			e.printStackTrace();
		}finally{
			bean.setLocationDtlsStatus(status);
			if(!status && !hasActionErrors()){
				addActionError("Something Went Wrong. Please Try Again Later");
			}
			if("delete".equalsIgnoreCase(bean.getMode())){
				return "jsonAjax";
			}else{
				getRequest().setAttribute(FIELD, "openLocationDetails");
			}
		}
		return DROPDOWN;
	}
	
	/**
	 * Location End
	 */
	
	public String insLocDetails(){
		try {
			service.schemeSelected(bean);
			if(StringUtils.isNotBlank(bean.getSchemeSelected())){
				
				String[] scheme=bean.getSchemeSelected().split(",");
				List<String> schemeList=new ArrayList<String>();
				for (String list : scheme) {
					schemeList.add(list.trim());
				}
				bean.setSchemeList(schemeList);
				
				if(schemeList.size()==1 && StringUtils.isBlank(bean.getSchemeId())){
					bean.setSchemeId(bean.getSchemeList().get(0).toString());
				}
				if(schemeList.size()>0){
					bean.setSchemeId(schemeList.get(0));
				}
				
				bean.setLocationSize(service.getLocationSizeDetails(bean,"scheme"));
				if("1".equalsIgnoreCase(bean.getLocationSize()) && StringUtils.isNotBlank(bean.getQuoteNo())){
					List<Map<String, Object>> locationId=service.getSelectedSchemeLocation(bean);
					if(locationId!=null && locationId.size()>0){
						bean.setLocationId(locationId.get(0).get("LOCATION_ID")==null?"":locationId.get(0).get("LOCATION_ID").toString());
						bean.setSingleLocName(locationId.get(0).get("LOCATION_NAME")==null?"":locationId.get(0).get("LOCATION_NAME").toString());	
					}
				}
				if(!"calculate".equalsIgnoreCase(bean.getReqFrom()) && !"proceed".equalsIgnoreCase(bean.getReqFrom())){
					if(schemeList.size()>0){
						getSession().put("scheme_id", schemeList.get(0));
						bean.setSchemeId(schemeList.get(0));
						getSession().put("ContentType", "0");
					}
					if(schemeList.size()>1){
						bean.setNextScheme(schemeList.get(1));
					}
				}
			}
			if(StringUtils.isNotBlank(bean.getQuoteNo())){
				bean.setBtnYN("Y");
			}
			if(StringUtils.isBlank(bean.getApplicationNo()) && !hasErrorValues()) {
				Calendar cal = Calendar.getInstance();
				Date today = cal.getTime();
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				bean.setInceptionDt(sdf.format(today));
				if(StringUtils.isBlank(bean.getMobileNo())) {
					bean.setMobileNo("09");
				}
				if("User".equalsIgnoreCase(bean.getUserType()) && !hasErrorValues() && (StringUtils.isBlank(bean.getQuoteNo()) || StringUtils.isBlank(bean.getApplicationNo()))){
					new CustomerDAO().setB2CCustomerDetails(bean, new CustomerDAO().getCustomerDetails(bean.getLoginId(),"2"));
				}
			}
			//mapSchemeDetails();
			mapLoginDetails();
			bean.setDisplay("getQuote");
			bean.setHomeDesc(service.getHomeInfoDesc(bean));
			bean.setHome(service.getHomeInfo(bean));
			bean.setSubhome(service.getSubHomeInfo(bean));
			service.setSumInsuredDetails(bean);
			bean.setModifyYN("Y");
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		return INPUT;
	}
	
	public String modifySumIns(){
		try {
			String[] scheme=bean.getSchemeSelected().split(",");
			List<String> schemeList=new ArrayList<String>();
			for (String list : scheme) {
				schemeList.add(list.trim());
			}
			bean.setSchemeList(schemeList);
			if("1".equalsIgnoreCase(bean.getLocationSize()) && StringUtils.isNotBlank(bean.getQuoteNo())){
				List<Map<String, Object>> locationId=service.getSelectedSchemeLocation(bean);
				if(locationId!=null && locationId.size()>0){
					bean.setLocationId(locationId.get(0).get("LOCATION_ID")==null?"":locationId.get(0).get("LOCATION_ID").toString());
					bean.setSingleLocName(locationId.get(0).get("LOCATION_NAME")==null?"":locationId.get(0).get("LOCATION_NAME").toString());
				}
			}
			bean.setSchemeListNew(getDropDownSchemeList());
			bean.setLocationDetails(getDropDownLocationList());
			bean.setHomeDesc(service.getHomeInfoDesc(bean));
			//bean.setLocationId("");
			bean.setHome(service.getHomeInfo(bean));
			bean.setSubhome(service.getSubHomeInfo(bean));
			if(!hasErrorValues()){
				service.setSumInsuredDetails(bean);
			}
			//bean.setModifyYN("Y");
			bean.setBtnYN("Y");
			System.out.println("---> Modify YN=> "+bean.getModifyYN()+" Scheme Id=> "+bean.getPrevScheme()+", "+bean.getSchemeId()+", "+bean.getNextScheme());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return INPUT;
	}
	
	@JSON(serialize=false)
	public String getBack() {
		try {
			bean.setEditFrom("");
			bean.setModifyYN("N");
			if(StringUtils.isNotBlank(bean.getSchemeSelected())){
				String[] scheme=bean.getSchemeSelected().split(",");
				List<String> schemeList=new ArrayList<String>();
				for (String list : scheme) {
					schemeList.add(list.trim());
				}
				bean.setSchemeList(schemeList);
				if(schemeList.size()>0){
					getSession().put("scheme_id", schemeList.get(0));
					bean.setSchemeId(schemeList.get(0));
					getSession().put("ContentType", "0");
				}
				if(schemeList.size()>1){
					bean.setNextScheme(schemeList.get(1));
				}
			}else if(StringUtils.isNotBlank(bean.getQuoteNo())){
				service.setQuoteSchemeDtls(bean);
				String[] scheme=bean.getSchemeSelected().split(",");
				List<String> schemeList=new ArrayList<String>();
				for (String list : scheme) {
					schemeList.add(list.trim());
				}
				bean.setSchemeList(schemeList);
				if(schemeList.size()>0){
					getSession().put("scheme_id", schemeList.get(0));
					bean.setSchemeId(schemeList.get(0));
					getSession().put("ContentType", "0");
				}
				if(schemeList.size()>1){
					bean.setNextScheme(schemeList.get(1));
				}
				bean.setBtnYN("Y");
			}
			List<String> locationList=new ArrayList<String>();
			if(StringUtils.isNotBlank(bean.getLocationSelected())){
				String locationIds="";
				String[] locations=bean.getLocationSelected().split(","); // 1,2,1,2
				//[3~1,3~2,6~1,6~2] 
				for(int i=0;i<locations.length;i++){
					String a=locations[i];
					String arr[]=a.split("~"); //[3,1] 
					
					String loc=arr[1];
					locationList.add(loc);
					
					locationIds=locationIds+","+a.replace("~", "");
					
				}
				if(StringUtils.isNotBlank(locationIds)) 
					locationIds=locationIds.substring(1);
				
				bean.setLocationIds(locationIds);
				
				Set<String> set = new LinkedHashSet<String>(); 
				set.addAll(locationList);
				System.out.println("My SET --->"+set);
				
				List<String> aList = new ArrayList<String>(); 
			    for (String x : set) 
			      aList.add(x);
				
				for(int i=0;i<aList.size();i++){     // 1,2 
					List<String> schemeSelList=new ArrayList<String>();
					for(int j=0;j<locations.length;j++){
						String a=locations[j];
						String arr[]=a.split("~"); //[3,1] 
						if(aList.get(i).equalsIgnoreCase(arr[1])){
							String loc=arr[0];
							schemeSelList.add(loc);
						}
					}
					Set<String> setNew = new LinkedHashSet<String>(); 
					setNew.addAll(schemeSelList);
					System.out.println("My SET --->"+setNew);
					
					List<String> sList = new ArrayList<String>(); 
				    for (String x : setNew) 
				      sList.add(x);
					
				}
			}
			
			bean.setLocationSize(service.getLocationSizeDetails(bean,"scheme"));
			
			bean.setBtnYN("Y");
			if(!hasErrorValues()) {
				service.getOFSData(bean);
			}
			personalInformation();
			
			if("1".equalsIgnoreCase(bean.getLocationSize())){
				List<Map<String, Object>> locationId=service.getSelectedSchemeLocation(bean);
				if(locationId!=null && locationId.size()>0){
					bean.setLocationId(locationId.get(0).get("LOCATION_ID")==null?"":locationId.get(0).get("LOCATION_ID").toString());
					bean.setSingleLocName(locationId.get(0).get("LOCATION_NAME")==null?"":locationId.get(0).get("LOCATION_NAME").toString());
				}
			}
			bean.setHomeDesc(service.getHomeInfoDesc(bean));
			bean.setHome(service.getHomeInfo(bean));
			bean.setSubhome(service.getSubHomeInfo(bean));
			
			service.setSumInsuredDetails(bean);
			
			service.deleteOfsDataDtlTempIns(bean);
			bean.setDisplay("getQuote");
		}
		catch(Exception exception) {
			LogManager.debug("Exception @ getBack() " + exception);
		}
		return INPUT;
	}

	public HomeBean getModel() {
		//Map<String, Object> session=ActionContext.getContext().getSession();
		String branchCode=(String)getSession().get("LoginBranchCode");
		String productId=(String) getSession().get("product_id");
		String loginId=(String)getSession().get("user");
		String userType=(String)getSession().get("usertype");
		String user=(String)getSession().get("user1");
		String issuer=(String)getSession().get("RSAISSUER");
		Captcha captcha = null;
		captcha = (Captcha) getSession().get(Captcha.NAME);
		String agencyCode=(String)getSession().get("AgencyCode");
		//String product=getSession().get("PRODUCT_NAME")==null?"":getSession().get("PRODUCT_NAME").toString();
		
		bean.setBranchCode(branchCode);
		bean.setProductId(productId);
		bean.setUserType(userType);
		bean.setUser(user);
		bean.setDestCountry("1");
		bean.setOriginCountry("1");
		bean.setCaptcha(captcha);
		bean.setAgencyCode(agencyCode);
		//bean.setProduct(product);
		
		/*if(request.getParameter("productId")!=null && !"".equals(request.getParameter("productId"))) {
			session.put("product_id", request.getParameter("productId"));
		}
		if(request.getParameter("schemeId")!=null && !"".equals(request.getParameter("schemeId"))) {
			session.put("scheme_id", request.getParameter("schemeId"));
		}*/
		if("sirEdit".equalsIgnoreCase(bean.getEditFrom())){
		}else{
			bean.setSchemeId((String) getSession().get("scheme_id"));
		}
		/*if(request.getParameter("contentTypeId")!=null && !"".equals(request.getParameter("contentTypeId"))) {
			session.put("ContentType", request.getParameter("contentTypeId"));
		}*/
		bean.setContentTypeId(getSession().get("ContentType")==null?"":getSession().get("ContentType").toString());
		bean.setLoginType(getSession().get("LoginType")==null?"":getSession().get("LoginType").toString());
		bean.setIssuer(issuer);
		if(StringUtils.isNotEmpty(bean.getIssuer()) && StringUtils.isNotBlank(bean.getBrokerCode())) {
				bean.setLoginId(new com.maan.common.dao.CommonDAO().getSingleInfo("loginId", new String[]{bean.getBrokerCode()}));
		} else {
			bean.setLoginId(loginId);
		}
		return bean;
	}
	
	public String premiumSummary() {
		try {
			LogManager.info("Enter into Home-premiumSummary()");
			personalInformation();
			Map<String,Object> personalInfo = bean.getPersonalInfo().get(0);
			bean.setReferralYN("N");
			if(StringUtils.isBlank(getCoverReferralRemarks())) {
				bean.setReferralComments(personalInfo.get("SUMMARY_REMARKS")==null?"":personalInfo.get("SUMMARY_REMARKS").toString());
				if(StringUtils.isNotBlank(bean.getReferralComments())) {
					bean.setReferralYN("Y");
				}
			}
			bean.setPremiumList(service.getHomePremiumInfo(bean));
			bean.setSubPremium(service.subPremium(bean));
			//bean.setSubhome(service.getSubHomeInfo(bean));
			bean.setExtraBenefitsList(service.getExtraBenefitsList());
			bean.setExcessList(service.getExcessList());
			
			bean.setActualPremium(personalInfo.get("ACTUAL_PREMIUM")==null?"":personalInfo.get("ACTUAL_PREMIUM").toString());
			bean.setActualOptionalPremium(personalInfo.get("ACTUAL_OPPREMIUM")==null?"":personalInfo.get("ACTUAL_OPPREMIUM").toString());
			bean.setDiscountPercent(personalInfo.get("DISCOUNT_PERCENT")==null?"":personalInfo.get("DISCOUNT_PERCENT").toString());
			bean.setDiscountPremium(personalInfo.get("DISCOUNT_PREMIUM")==null?"":personalInfo.get("DISCOUNT_PREMIUM").toString());
			bean.setTotalPremium(personalInfo.get("PREMIUM")==null?"":personalInfo.get("PREMIUM").toString());
			bean.setMinPremiumYN(personalInfo.get("MIN_PREMIUM_YN")==null?"":personalInfo.get("MIN_PREMIUM_YN").toString());
			LogManager.info("Exit from Home-premiumSummary()");
			//bean.setPolicyFee(service.getPolicyFees(bean.getQuoteNo(), bean.getUserType(), bean.getMenuType(), bean.getBranchCode()));
			//bean.setFinalPremium(Formatter.amount(Double.parseDouble(bean.getTotalPremium())+Double.parseDouble(bean.getPolicyFee())));
			bean.setPolicyFee(personalInfo.get("POLICY_FEE")==null?"":personalInfo.get("POLICY_FEE").toString());
			bean.setFinalPremium(personalInfo.get("OVERALL_PREMIUM")==null?"":personalInfo.get("OVERALL_PREMIUM").toString());
		}
		catch(Exception exception) {
			LogManager.debug("Exception @ premiumSummary() " + exception);
		}
		return PREMIUM;
	}

	public String subListMenu(){
		LogManager.info("Enter into Home-subListMenu()");
		try{
			service.setQuoteSchemeDtls(bean);
			String[] scheme=bean.getSchemeSelected().split(",");
			List<String> schemeList=new ArrayList<String>();
			for (String list : scheme) {
				schemeList.add(list.trim());
			}
			bean.setSchemeList(schemeList);
			if("admin".equalsIgnoreCase(bean.getUserType())  || "RSAIssuer".equals(bean.getUserType()) || "RA".equalsIgnoreCase(bean.getMenuType()) || StringUtils.isBlank(bean.getReferralComments())){
				//bean.setPremiumList(service.getHomePremiumInfo(bean));
				bean.setPremiumList(service.getHomePremiumInfoNew(bean));
				bean.setSubPremium(service.subPremium(bean));
			}
			bean.setSubList(service.getSubList(bean));
			String str=StringUtils.isBlank(bean.getSubCoverId())?"0":bean.getSubCoverId();
			if(("valid"+bean.getCoverId()+str).equals(bean.getFrom())){
				if(!hasErrorValues()) {
					bean.setCoverageList(service.getCoverageList(bean));
					bean.setFrom("");
				}
			}else
				bean.setCoverageList(service.getCoverageList(bean));
			/*if(bean.getSubCoverId()==null)
				bean.setSubCoverId("0");*/
			bean.setSubCoverId(StringUtils.isBlank(bean.getSubCoverId())?"0":bean.getSubCoverId());
			if("0".equals(bean.getSubCoverId())){
				for(int i=0; i<bean.getPremiumList().size();i++){
					Map<String,Object> map=bean.getPremiumList().get(i);
					if(bean.getCoverId().equalsIgnoreCase(map.get("coverages_id").toString())){
						bean.setAddRowYN(map.get("MULTIPLE_ROWS")==null?"S":map.get("MULTIPLE_ROWS").toString());
						LogManager.info("AddRowYN==>"+bean.getAddRowYN());
					}
				}
			}else{
				for(int i=0; i<bean.getSubPremium().size();i++){
					Map<String,Object> map= bean.getSubPremium().get(i);
					if(bean.getSubCoverId().equalsIgnoreCase(map.get("COVERAGES_SUB_ID").toString())){
						bean.setAddRowYN(map.get("MULTIPLE_ROWS")==null?"S":map.get("MULTIPLE_ROWS").toString());
						LogManager.info("AddRowYN==>"+bean.getAddRowYN());
					}
				}
			}
			if(bean.getSubList()!=null){
				for(int i=0; i<bean.getSubList().size(); i++){
					Map<String,Object> subMap = bean.getSubList().get(i);
					if("Radio".equals(subMap.get("DISPLAY_TYPE").toString()) || "dropdown".equals(subMap.get("DISPLAY_TYPE").toString())){
						dynamicList.put(subMap.get("DEST_COLUMN").toString(),service.getDropDownList(subMap.get("DROPDOWN_TABLE").toString()));
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		bean.setPremiumInfoType("TOGGLE");
		LogManager.info("Exit from Home-subListMenu()");
		return SUBLIST;
	}

	public String addRow(){
		LogManager.info("Enter into Home-addRow()");
		try{
			bean.setSubList(service.getSubList(bean));
			if(bean.getSubList()!=null){
				for(int i=0; i<bean.getSubList().size(); i++){
					Map<String,Object> subMap = bean.getSubList().get(i);
					if("Radio".equals(subMap.get("DISPLAY_TYPE").toString()) || "dropdown".equals(subMap.get("DISPLAY_TYPE").toString())) {
						dynamicList.put(subMap.get("DEST_COLUMN").toString(),service.getDropDownList(subMap.get("DROPDOWN_TABLE").toString()));
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		LogManager.info("Exit from Home-addRow()");
		return ADDROW;
	}

	public String saveDetail(){
		LogManager.info("Enter into Home-saveDetail()");
		bean.setFrom("");
		try{
			subListMenu();
			bean.setFrom("valid"+bean.getCoverId()+(StringUtils.isBlank(bean.getSubCoverId())?"0":bean.getSubCoverId()));
			getValidate("coverageUpload");
			if(!hasErrorValues()) {
				service.saveCoverages(bean);
				if(bean.getCoverageList()!=null && bean.getDeleteRowList().size()>0) {
					if(DBConstants.DEVICETYPE_ANDROID.equals(bean.getDeviceType()) || DBConstants.DEVICETYPE_HYBRID.equals(bean.getDeviceType())) {
						bean.getActionMessagesBean().add(ResourceBundleUtil.findText(bean.getProductId(),"message.coverageUpload.delete"));
					} else {
						addActionMessage(ResourceBundleUtil.findText(bean.getProductId(),"message.coverageUpload.delete"));
					}
				} else if(bean.getCoverageList()==null || bean.getCoverageList().size()<=0) {
					if(DBConstants.DEVICETYPE_ANDROID.equals(bean.getDeviceType()) || DBConstants.DEVICETYPE_HYBRID.equals(bean.getDeviceType())) {
						bean.getActionMessagesBean().add(ResourceBundleUtil.findText(bean.getProductId(),"error.coverageUpload.empty"));
					} else {
						addActionMessage(ResourceBundleUtil.findText(bean.getProductId(),"error.coverageUpload.empty"));
					}
				} else {
					
					if(DBConstants.DEVICETYPE_ANDROID.equals(bean.getDeviceType()) || DBConstants.DEVICETYPE_HYBRID.equals(bean.getDeviceType())) {
						bean.getActionMessagesBean().add(ResourceBundleUtil.findText(bean.getProductId(),"message.coverageUpload.added"));
						if(Double.parseDouble(bean.getCoverTS())>Double.parseDouble(bean.getCoverSumInsured())) {
							bean.getActionMessagesBean().add(ResourceBundleUtil.findText(bean.getProductId(),"error.total.suminsured.exceeds"));
						}
					} else {
						addActionMessage(ResourceBundleUtil.findText(bean.getProductId(),"message.coverageUpload.added"));
						if(Double.parseDouble(bean.getCoverTS())>Double.parseDouble(bean.getCoverSumInsured())) {
							addActionMessage(ResourceBundleUtil.findText(bean.getProductId(),"error.total.suminsured.exceeds"));
						}
						
					}
				}
			} else {
				subListMenu();
			}
		} catch(Exception e) {
			LogManager.debug(e);
		}
		LogManager.info("Exit from Home-saveDetail()");
		return COVERAGEUPLOAD;
	}

	public String getDelete(){
		LogManager.info("Enter into Home-getDelete()");
		String returnResult=saveDetail();
		LogManager.info("Exit from Home-getDelete()");
		return returnResult;
	}

	public String uwMenu() {
		LogManager.info("Enter into Home-uwMenu()");
		service.setQuoteSchemeDtls(bean);
		String[] scheme=bean.getSchemeSelected().split(",");
		List<String> schemeList=new ArrayList<String>();
		for (String list : scheme) {
			schemeList.add(list.trim());
		}
		bean.setSchemeList(schemeList);
		bean.setLocationSize(service.getLocationSizeDetails(bean,""));
		premiumSummary();
		service.consolidatePremiumDetails(bean);
		service.editReferralRemarks(bean);
//		if("validuw".equals(bean.getFrom())){
//			if(!hasErrorValues()) {
//				bean.setCoverageList(service.getUWDetails(bean));
//				if(bean.getCoverageList()!=null && bean.getCoverageList().size()>0) {
//					if(DBConstants.DEVICETYPE_ANDROID.equals(bean.getDeviceType()) || DBConstants.DEVICETYPE_HYBRID.equals(bean.getDeviceType())) {
//						bean.getActionErrorsBean().add(ResourceBundleUtil.findText(bean.getProductId(),"coverage.value.added"));
//					} else {
//						addActionError(ResourceBundleUtil.findText(bean.getProductId(),"coverage.value.added"));
//					}
//				}
//				bean.setFrom("");
//			}
//		} else {
//			bean.setCoverageList(service.getUWDetails(bean));
//		} if(bean.getCoverageList()==null || bean.getCoverageList().size()<=0){
//			List<Map<String, Object>> list=new ArrayList<Map<String, Object>>();
//			Map<String, Object> map=new HashMap<String, Object>();
//			map.put("BUILT_OF_CONCRETE", "N");
//			map.put("PRIVATE_LIVING_ACCOMODATION", "N");
//			map.put("BUILT_ON_RECLAIMED_LAND", "N");
//			map.put("UNATTENDED_60_CONSECUTIVE_DAYS", "N");
//			map.put("DECLINED_CANELLED_CONDITIONS", "N");
//			map.put("ANY_CLAIM_IN_3YRS", "N");
//			list.add(map);
//			bean.setCoverageList(list);
//		}
//		bean.setPremiumInfoType("TOGGLE");
		LogManager.info("Exit from Home-uwMenu()");
		return HOME_PREMIUMINFO;
	}
	
	
	public String policyinfo() {
		try {
			String validCoverage = getCoverReferralRemarks();
			bean.setValidCoverage(validCoverage);
			if(StringUtils.isBlank(validCoverage) && !"admin".equals(bean.getUserType())){
				bean.setShowReferralYN(service.getReferalYNStatus(bean));
			}
		} catch(Exception e) {
			LogManager.debug(e);
		}
		return POLICY_INFO;
	}
	
	public String updpolicyinfo() {
		try {
			String validCoverage = getCoverReferralRemarks();
			bean.setValidCoverage(validCoverage);
			bean.setValidCoverage(bean.getValidCoverage().replaceAll(",", " & "));
			service.updReferralRemarks(bean.getValidCoverage(), bean.getQuoteNo(),bean.getIssuer(),"System");
			bean.setShowReferralYN(service.getReferalYNStatus(bean));
			premiumSummary();
		} catch(Exception e) {
			LogManager.debug(e);
		}
		return POLICY_INFO;
	}

	public String saveUWDetails() {
		LogManager.info("Enter into Home-saveUWDetails()");
		String returnResult=PREMIUM;
		bean.setFrom("");
		/*String validCoverage=null;
		if(!"RA".equalsIgnoreCase(bean.getMenuType())) {
			//validCoverage=session.get("ValidCoverage")==null?"":session.get("ValidCoverage").toString();
			validCoverage = getCoverReferralRemarks();
		}*/
		try {
			if("RSAIssuer".equalsIgnoreCase(bean.getUserType()) || "Admin".equalsIgnoreCase(bean.getUserType())){
				getValidate("loadDisc");
				if(!hasErrorValues()){
					service.updateLoadingDiscountHpm(bean);
				}
			}
			if(("RR".equalsIgnoreCase(bean.getQuoteStatus()) || "RU".equalsIgnoreCase(bean.getQuoteStatus()) || "RA".equalsIgnoreCase(bean.getQuoteStatus()))
					&& ("RSAIssuer".equalsIgnoreCase(bean.getUserType()) || "Admin".equalsIgnoreCase(bean.getUserType()))){
				 getValidate("referralStatus");
				 if(!hasErrorValues()){
					 if(service.adminReferralUpdateHpm(bean)){
						 addActionMessage("Referral Status Updated for Quote No. "+bean.getQuoteNo());
					 }else{
						 addActionError("Something Went Wrong. Please Contact Technical Team");
					 }
				 }
				 if(hasErrorValues()){
					 return uwMenu();
				 }else{
					 return "referralRedirect";
				 }
			}else{
				String[] scheme=bean.getSchemeSelected().split(",");
				List<String> schemeList=new ArrayList<String>();
				for (String list : scheme) {
					schemeList.add(list.trim());
				}
				bean.setSchemeList(schemeList);
				bean.setLocationSize(service.getLocationSizeDetails(bean,"scheme"));
				bean.setFrom("validuw");
				getValidate("uwQuestions");
				if(!hasErrorValues()) {
					service.saveUWDetails(bean);
					bean.setShowReferralYN(service.getReferalYNStatus(bean));
					if(!"RA".equalsIgnoreCase(bean.getMenuType())){
						String validCoverage = getCoverReferralRemarks();
						String coverSubDetailReferral = getSubDetailReferralRemarks();
						validCoverage += StringUtils.isBlank(coverSubDetailReferral)?"":("~"+coverSubDetailReferral);
						
//						List<Map<String,Object>> uwvalidation=service.getUWMaster(bean);
//						for(int i=0; i<uwvalidation.size(); i++){
//							Map<String,Object> map= uwvalidation.get(i);
//							if(!StringUtils.isBlank(map.get("EXPECTED_RESULT")==null?"":map.get("EXPECTED_RESULT").toString()) && !StringUtils.isNumeric(map.get("EXPECTED_RESULT")==null?"":map.get("EXPECTED_RESULT").toString())){
//								if(0==i && !bean.getBuildCon().equalsIgnoreCase(map.get("EXPECTED_RESULT").toString()))
//									validCoverage+="~"+map.get("UW_DESCRIPTION").toString();
//								else if(1==i && !bean.getLiveAccom().equalsIgnoreCase(map.get("EXPECTED_RESULT").toString()))
//									validCoverage+="~"+map.get("UW_DESCRIPTION").toString();
//								else if(2==i && !bean.getReclaimLand().equalsIgnoreCase(map.get("EXPECTED_RESULT").toString()))
//									validCoverage+="~"+map.get("UW_DESCRIPTION").toString();
//								else if(3==i && !bean.getConsDays().equalsIgnoreCase(map.get("EXPECTED_RESULT").toString()))
//									validCoverage+="~"+map.get("UW_DESCRIPTION").toString();
//								else if(4==i && !bean.getDecRej().equalsIgnoreCase(map.get("EXPECTED_RESULT").toString()))
//									validCoverage+="~"+map.get("UW_DESCRIPTION").toString();
//								else if(5==i && !bean.getPastThr().equalsIgnoreCase(map.get("EXPECTED_RESULT").toString()))
//									validCoverage+="~"+map.get("UW_DESCRIPTION").toString();
//								else if("Y".equalsIgnoreCase(bean.getPastThr())){
//									if(6==i && !bean.getNoClaim().equalsIgnoreCase(map.get("EXPECTED_RESULT").toString()))
//										validCoverage+="~"+map.get("UW_DESCRIPTION").toString();
//									else if(7==i && !bean.getTypeClaim().equalsIgnoreCase(map.get("EXPECTED_RESULT").toString()))
//										validCoverage+="~"+map.get("UW_DESCRIPTION").toString();
//									else if(8==i && !bean.getClaimAmt().equalsIgnoreCase(map.get("EXPECTED_RESULT").toString()))
//										validCoverage+="~"+map.get("UW_DESCRIPTION").toString();
//								}else if(9==i && !bean.getTypeProperty().equalsIgnoreCase(map.get("EXPECTED_RESULT").toString()))
//									validCoverage+="~"+map.get("UW_DESCRIPTION").toString();
//							}else if(!StringUtils.isBlank(map.get("EXPECTED_RESULT")==null?"":map.get("EXPECTED_RESULT").toString()) && StringUtils.isNumeric(map.get("EXPECTED_RESULT")==null?"":map.get("EXPECTED_RESULT").toString())){
//								if(0==i && Double.parseDouble(bean.getBuildCon())>Double.parseDouble((map.get("EXPECTED_RESULT").toString())))
//									validCoverage+="~"+map.get("UW_DESCRIPTION").toString();
//								else if(1==i && Double.parseDouble(bean.getLiveAccom())>Double.parseDouble((map.get("EXPECTED_RESULT").toString())))
//									validCoverage+="~"+map.get("UW_DESCRIPTION").toString();
//								else if(2==i && Double.parseDouble(bean.getReclaimLand())>Double.parseDouble((map.get("EXPECTED_RESULT").toString())))
//									validCoverage+="~"+map.get("UW_DESCRIPTION").toString();
//								else if(3==i && Double.parseDouble(bean.getConsDays())>Double.parseDouble((map.get("EXPECTED_RESULT").toString())))
//									validCoverage+="~"+map.get("UW_DESCRIPTION").toString();
//								else if(4==i && Double.parseDouble(bean.getDecRej())>Double.parseDouble((map.get("EXPECTED_RESULT").toString())))
//									validCoverage+="~"+map.get("UW_DESCRIPTION").toString();
//								else if(5==i && Double.parseDouble(bean.getPastThr())>Double.parseDouble((map.get("EXPECTED_RESULT").toString())))
//									validCoverage+="~"+map.get("UW_DESCRIPTION").toString();
//								else if("Y".equalsIgnoreCase(bean.getPastThr())){
//									if(6==i && Double.parseDouble(bean.getNoClaim())>Double.parseDouble((map.get("EXPECTED_RESULT").toString())))
//										validCoverage+="~"+map.get("UW_DESCRIPTION").toString();
//									else if(7==i && Double.parseDouble(bean.getTypeClaim())>Double.parseDouble((map.get("EXPECTED_RESULT").toString())))
//										validCoverage+="~"+map.get("UW_DESCRIPTION").toString();
//									else if(8==i && Double.parseDouble(bean.getClaimAmt())>Double.parseDouble((map.get("EXPECTED_RESULT").toString())))
//										validCoverage+="~"+map.get("UW_DESCRIPTION").toString();
//								}else if(9==i && Double.parseDouble(bean.getTypeProperty())>Double.parseDouble((map.get("EXPECTED_RESULT").toString())))
//									validCoverage+="~"+map.get("UW_DESCRIPTION").toString();
//							}
//						}
						if(StringUtils.isNotBlank(validCoverage)) {
							validCoverage = validCoverage.charAt(0)==','?validCoverage.substring(1):validCoverage;
						}
						bean.setValidCoverage(validCoverage);
						if(validCoverage!=null && validCoverage.length()>0) {
							//bean.setValidCoverage(bean.getValidCoverage().replaceAll(",", " & "));
							service.updReferralRemarks(bean.getValidCoverage(), bean.getQuoteNo(),bean.getIssuer(),"System");
							
						}else{
							service.updReferralRemarks(("N".equalsIgnoreCase(bean.getReferralYN())?"":bean.getReferralComments()), bean.getQuoteNo(),bean.getIssuer(),"Manual");
						}
							/* else {
							service.updReferralRemarks(getCoverReferralRemarks(), bean.getQuoteNo(),bean.getIssuer());
						}*/
						
					}
				}
				if(hasErrorValues()) {
					returnResult = uwMenu();
				} else {
					returnResult = showQuote();
				}
				LogManager.info("Exit from Home-saveUWDetails()");
				return returnResult;
			}
		} catch(Exception e){
			e.printStackTrace();
		}
		return returnResult;
	}
	
	/**
	 * Calculate Premium Start
	 */
	
	public String premiumCalc(){
		try{
			if(!"schemeLoc".equalsIgnoreCase(bean.getCalcFrom())){
				String premCalcError = "";
				if(StringUtils.isNotBlank(bean.getExcessSign())){
					if(!"+".equalsIgnoreCase(bean.getExcessSign()) && !"-".equalsIgnoreCase(bean.getExcessSign())){
						premCalcError += "Please Select Valid Loading/Discount Sign~";
					}
				}
				if(StringUtils.isNotBlank(bean.getExcessPremium())){
					try{
						Double.parseDouble(bean.getExcessPremium());
					}catch(Exception e){
						premCalcError += "Please Enter Valid Loading/Discount Premium";
					}
				}
				if(StringUtils.isBlank(premCalcError)){
					premCalcError = service.validateLoadDiscLimit(bean);
				}
				bean.setPremiumCalcValidation(premCalcError);
				if(StringUtils.isBlank(bean.getPremiumCalcValidation())
						&& ("RSAIssuer".equalsIgnoreCase(bean.getUserType()) || "Admin".equalsIgnoreCase(bean.getUserType()))){
					service.updateLoadingDiscountHpm(bean);
				}
			}
			service.updatePremiumHpm(bean);
			service.consolidatePremiumDetails(bean);
		}catch(Exception e){
			LogManager.info("Exception @ HomeAction.premiumCalc() Error: "+e);
			e.printStackTrace();
		}finally{
			getRequest().setAttribute(FIELD, "premiumDetailsDivId");
		}
		return "homeAjax";
	}
	
	/**
	 * Calculate Premium End
	 */
	
	/**
	 * Customer Extra Information Update Start
	 */
	
	public String updCustInfo(){
		try{
			getValidate("customerDetails");
			getValidate("policyDateEI");
			if(!hasActionErrors()){
				new CustomerService().insertCustomerDetails(bean.getCustomerId(),bean.getLoginId(),bean.getBranchCode(),
						bean.getProductId(),bean.getTitle(),bean.getCustomerName(),bean.getMobileNo(),bean.getEmail(),
						bean.getAddress1(),bean.getAddress2(),bean.getPoBox(),bean.getCity(),bean.getCoreAppCode(),
						bean.getClientCustomerId(),bean.getCustArNo(),bean.getCustLastName(),bean.getCustPassportNo(),
						bean.getCustdob(),bean.getCustAlterMobileNo(),bean.getCustLandLineNo(),bean.getCustomerType(),
						bean.getCompanyRegNo(),bean.getCustNameArabic(),bean.getCustdobar(),bean.getGender(),
						bean.getOccupation(),"detailQuote",bean.getCustnrc1(),bean.getCustnrc2(),bean.getCustnrc3(),"");
				service.updateInceptionDate(bean);
				commonDAO.updateInstallmentDetail(bean.getQuoteNo(),"HSS",bean.getInstallmentYN());
				if("N".equalsIgnoreCase(bean.getInstallmentYN()) && service.installmentcount(bean)> 0 ){
					service.deleteinstallment(bean);
				}
				if("N".equalsIgnoreCase(bean.getPolicyGenerateYn()) || "E".equalsIgnoreCase(bean.getPolicyGenerateYn())){
					addActionMessage("Quote No. "+bean.getQuoteNo()+" Saved Successfully");
					if("E".equalsIgnoreCase(bean.getPolicyGenerateYn())){
						new SmsEmailUtil(SmsEmailUtil.EMAIL_QUOTE, bean.getQuoteNo(),bean.getProductId()).send();
						addActionMessage("Email Sent to "+bean.getEmail());
					}
					return "msgPage";
				}else if("Y".equalsIgnoreCase(bean.getPolicyGenerateYn())){
					getEditQuoteDetails();
					service.setCustDetail(bean);
					service.consolidatePremiumDetails(bean);
					//return "paymentPage";
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
			}
		}catch(Exception e){
			addActionError("Something Went Wrong. Please Contact Technical Team");
			LogManager.info("Exception @ updCustInfo() Error: "+e); 
			e.printStackTrace();
		}finally{
			if(hasActionErrors()){
				service.consolidatePremiumDetails(bean);
			}
		}
		return "extraInformation";
	}
	
	public String getEncrPayUrl(HomeBean bean, String url) throws Exception {
		String paymentType="madisonPay"; String result="";
		String type="";
		try {
			String stat=commonDAO.checkIsB2C(bean.getLoginId());
			if("Y".equalsIgnoreCase(stat))
				type="b2c";
			else
				type="b2b";
			String encrData=CryptoService.encrypt("quoteNo="+bean.getQuoteNo()+"~~paymentType="+paymentType+"~~productId="+bean.getProductId()+"~~brokertype="+type+"~~logintype=b2b~~branchCode="+bean.getBranchCode());
			result=url+encrData;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * Customer Extra Information Update End
	 */
	
	/**
	 * Extra Information Page Start
	 */
	
	public String extraInfo(){
		try{
			getEditQuoteDetails();
			service.setCustDetail(bean);
			service.consolidatePremiumDetails(bean);
		}catch(Exception e){
			LogManager.info("Exception @ extraInfo() Error: "+e); 
			e.printStackTrace();
		}
		return "extraInformation";
	}
	
	/**
	 * Extra Information Page End
	 */

	public List<Object> getPolicyInformation() {
		return service.getPolicyInformation(bean.getQuoteNo());
	}
	
	public String showQuote() {
		String result;
		String referralMsg = getReferralRemarks();
		if("B2C".equalsIgnoreCase(bean.getLoginType())) {
			result = "redirectEditUser";
		} else if(!"admin".equals(bean.getUserType()) && !"RA".equalsIgnoreCase(bean.getMenuType()) && StringUtils.isNotBlank(referralMsg)) {
			bean.setValidCoverage(referralMsg);
			result = POLICY_INFO;
		} else {
			getEditQuoteDetails();
			service.setCustDetail(bean);
			//premiumSummary();
			service.consolidatePremiumDetails(bean);
			//bean.setShowReferralYN(service.getReferalYNStatus(bean));
			//result = HOME_POLICY;
			result = "extraInformation";
		}
		return result;
	}
	
	private void getValidate(String type) {
		LinkedList<String> list=service.getValidate(bean, type);
		if(list!=null && list.size()>0){
			setActionErrorList(list);
		}
	}

	private void setActionErrorList(List<String> list) {
		if(DBConstants.DEVICETYPE_ANDROID.equals(bean.getDeviceType()) || DBConstants.DEVICETYPE_HYBRID.equals(bean.getDeviceType())) {
			List<String> errorBean = new ArrayList<String>();
			for (String st : list) {
				if(st.indexOf("#")!=-1) {
					Object args[]=(st.indexOf("~")!=-1?(st.substring(st.indexOf('#')+1, st.lastIndexOf('#'))).split("~"):new String[]{st.substring(st.indexOf('#')+1)});
					errorBean.add(ResourceBundleUtil.findText(bean.getProductId(),st.substring(0,st.indexOf('#')),args));
				} else {
					errorBean.add(ResourceBundleUtil.findText(bean.getProductId(),st));
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

	private void personalInformation() throws Exception {
		if(StringUtils.isNotBlank(bean.getQuoteNo())) {
			List<Map<String, Object>> personalInfo = service.personalInfo(bean);
			bean.setPersonalInfo(personalInfo);
			if(!hasErrorValues() && personalInfo!=null && personalInfo.size()>0) {
				new CustomerDAO().setCustomerDetails(bean);
			}
		}
			
	}
	@JSON(serialize=false)
	public String getPaCoverStatus() {
		return service.getCoverStatus(bean.getQuoteNo(),PACOVER_PREMIUMSTATUS);
	}
	@JSON(serialize=false)
	public String getTractorCoverStatus() {
		return service.getCoverStatus(bean.getQuoteNo(), MOTOR_PREMIUMSTATUS);
	}
	@JSON(serialize=false)
	public String getPaTractorOption() {
		return service.getPaTractorOption(bean.getSchemeId(), bean.getBranchCode());
	}
	@JSON(serialize=false)
	public final List<Map<String,Object>> getIteratorCoverageList() {
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		try {
			if(bean.getCoverageList()==null || bean.getCoverageList().size()==0) {
				if("M".equals(bean.getAddRowYN())) {
					list.add(new HashMap<String, Object>());
					list.add(new HashMap<String, Object>());
					list.add(new HashMap<String, Object>());
				} else {
					list.add(new HashMap<String, Object>());
				}
			} else {
				list = bean.getCoverageList();
			}
		} catch(Exception exception) {
			LogManager.debug(exception);
		}
		return list;
	}
	@JSON(serialize=false)
	public List<Map<String,Object>> getBuildingAddressList() {
		return service.getBuildingAddressList(bean.getQuoteNo());
	}
	/***************************** Farmer Insurance | PACover - Start ***************************************/

	public String paCover() {
		String forward = "";
		try {
			if("Y".equals(getPaCoverStatus())) {
				bean.setGeneralPremiumStatus(PACOVER_PREMIUMSTATUS);
				personalInformation();
				service.getPaCoverDetails(bean);
				bean.getPaInsuredId();
				bean.setPaName("");
				bean.setPaDob("");
				bean.setPaIndurer("");
				bean.setPaRelationship("");
				bean.setPaOccupation("");
				bean.setPaTableOfBenifits("");
				bean.setPaSumInsured("");
				bean.setPaAnnualIncome("");
				bean.setPaFatherFirstName("");
				bean.setPaFatherMiddleName("");
				bean.setPaFatherLastName("");
				bean.setPaExitingDisability("");
				bean.setPaDisabilityDesc("");
				bean.setPaOptionType("");
				bean.setPaInsuredId("");
				bean.setPaMedicalExtn("");
				bean.setPaCostOfTravel("");
				bean.setPaSupportingItem("");
				forward = FARMER_PA_COVER;
			}
			else {
				forward = edittractor();
			}
		} catch(Exception exception) {
			LogManager.debug(exception);
		}
		return forward;
	}
	
	public String editpaCover() {
		try {
			bean.setGeneralPremiumStatus(PACOVER_PREMIUMSTATUS);
			personalInformation();
			service.getPaCoverDetails(bean);
			service.editpaInsuredDetails(bean);
		} catch(Exception exception) {
			LogManager.debug(exception);
		}
		return FARMER_PA_COVER;
	}
	
	public String addpaCover() {
		try {
			getValidate("PA_INSURED_DETAILS");
			if(hasErrorValues()) {
				bean.setGeneralPremiumStatus(PACOVER_PREMIUMSTATUS);
			}
			else {
				if(!"RA".equalsIgnoreCase(bean.getMenuType())) {
					service.updatePaCoverDetails(bean);
					service.addpaInsuredDetails(bean);
					service.paPremiumCalc(bean.getApplicationNo(), bean.getBranchCode(), "C");
				}
				paCover();
			}
		} catch(Exception exception) {
			LogManager.debug(exception);
		}
		return FARMER_PA_COVER;
	}
	
	public String deletepaCover() {
		try {
			service.delpaInsuredDetails(bean.getApplicationNo(), bean.getPaInsuredId());
			paCover();
		} catch(Exception exception) {
			LogManager.debug(exception);
		}
		return FARMER_PA_COVER;
	}
	
	public String updatepaCover() {
		String forward = "";
		try {
			addpaCover();
			if(!hasErrorValues()) {
				getValidate("PA_INSURED_DETAILS_LIST");
			}
			if(hasErrorValues()) {
				forward = FARMER_PA_COVER;
			} else if("admin".equalsIgnoreCase(bean.getUserType())) {
				forward = paPremiuminfo();
			} else {
				forward = edittractor();
			}
		} catch(Exception exception) {
			LogManager.debug(exception);
		}
		return forward;
	}
	
	public String paPremiuminfo() {
		try {
			bean.setGeneralPremiumStatus(MOTOR_PREMIUMSTATUS);
			bean.setReqFromq("personalAccident");
			bean.setPremiumEditStatus("Y");
			premiumSummary();
		}
		catch(Exception exception) {
			LogManager.debug(exception);
		}
		return FARM_PA_PREMIUM_INFO;
	}
	public String updatepaPremiuminfo() {
		String forward = FARM_PA_PREMIUM_INFO;
		try {
			if("calc".equalsIgnoreCase(bean.getProcessType()) || "update".equalsIgnoreCase(bean.getProcessType())) {
				String result = service.updatepaPremiuminfo(bean);
				if("SUCCESS".equalsIgnoreCase(result) && "update".equalsIgnoreCase(bean.getProcessType())) {
					forward = edittractor();
				} else {
					if("FAILURE".equalsIgnoreCase(result)) {
						if(DBConstants.DEVICETYPE_ANDROID.equals(bean.getDeviceType()) || DBConstants.DEVICETYPE_HYBRID.equals(bean.getDeviceType())) {
							bean.getActionErrorsBean().add(ResourceBundleUtil.findDefaultText("error.update"));
						} else {
							addActionError(ResourceBundleUtil.findDefaultText("error.update"));
						}
					}
					forward = paPremiuminfo();
				}
			}
			else {
				forward = edittractor();
			}
		}
		catch(Exception exception) {
			LogManager.debug(exception);
		}
		bean.setProcessType("");
		return forward;
	}
	
	@JSON(serialize=false)
	public List<Map<String,Object>> getPaInsuredDetailsList() {
		return service.getPaInsuredDetailsList(bean.getApplicationNo());
	}
	@JSON(serialize=false)
	public Map<String,Object> getPaDetailsList() {
		return service.getPaDetailsList(bean.getApplicationNo());
	}
	@JSON(serialize=false)
	public List<Object> getPaPolicyTypeList() {
		return commonDAO.getOptionsList("POLICYTYPE",bean.getProductId(), getPACoverParams());
	}
	@JSON(serialize=false)
	public List<Object> getPaInsurerList() {
		return commonDAO.getOptionsList("INSURERLIST",bean.getProductId(), getPACoverParams());
	}
	@JSON(serialize=false)
	public List<Object> getPaReliationShipList() {
		return commonDAO.getOptionsList("RELATIONSHIP",bean.getProductId(), getPACoverParams());
	}
	@JSON(serialize=false)
	public List<Object> getPaTableBenifitsList() {
		return commonDAO.getOptionsList("TABLEBENIFITS",bean.getProductId(), getPACoverParams());
	}
	@JSON(serialize=false)
	public List<Object> getPaOccupationList() {
		return commonDAO.getOptionsList("PAOCCUPATION",bean.getProductId(), getPACoverParams());
	}
	@JSON(serialize=false)
	public List<Object> getPaCostOfTravelList() {
		return commonDAO.getOptionsList("COSTOFTRAVEL",bean.getProductId(), getPACoverParams());
	}
	@JSON(serialize=false)
	public List<Object> getPaCriticalExtentionList() {
		return commonDAO.getOptionsList("CRITICALEXTENTION",bean.getProductId(), getPACoverParams());
	}
	@JSON(serialize=false)
	public List<Object> getPaMedicalExtnList() {
		return commonDAO.getOptionsList("MEDICALEXTENSION",bean.getProductId(), getPACoverParams());
	}
	@JSON(serialize=false)
	public List<Object> getPaPrevInsuranceList() {
		return commonDAO.getOptionsList("COINSURANCE",bean.getProductId(), getPACoverParams());
	}
	@JSON(serialize=false)
	public List<Object> getPaOptionTypeList() {
		return commonDAO.getOptionsList("COINSURANCE",bean.getProductId(), getPACoverParams());
	}
	private Object[] getPACoverParams() {
		Object[] objects=new String[]{"",bean.getProductId(),bean.getBranchCode(),"","","","",
				"","","","",bean.getLoginId(),bean.getBrokerCode()};
		return objects;
	}
	/***************************** Farmer Insurance | PACover - End ***************************************/
	
	/***************************** Farmer Insurance | Motor ***************************************/
	
	public String edittractor() {
		String forward = "";
		try {
			if("Y".equals(getTractorCoverStatus())) {
				bean.setGeneralPremiumStatus(MOTOR_PREMIUMSTATUS);
				forward = FARMER_MOTOR;
				if(StringUtils.isNotBlank(bean.getApplicationNo())) {
					service.getFirstPageDtls(bean);
					bean.setDisplay("newQuote");
					mapAdditionalVehicleDetails();
					premiumSummary();
				}
			}
			else {
				forward = uwMenu();
			}
		}
		catch(Exception exception) {
			LogManager.debug(exception);
		}
		return forward;
	}
	
	public String getQuote() {
		String forward= FARMER_MOTOR;
		try {
			if(!"RA".equalsIgnoreCase(bean.getMenuType())) {
				mapRegNo("join");
				addVehicle();
			}
			personalInformation();
			if(!hasErrorValues()) {
				if("admin".equalsIgnoreCase(bean.getUserType())) {
					forward = tractorPremiuminfo();
				} else {
					forward = uwMenu();
				}
			}
		}
		catch(Exception exception) {
			LogManager.debug(exception);
		}
		return forward;
	}
	
	public String addVehicle() {
		try {
			getValidate(bean.getActionType());
			if(hasErrorValues()) {
				bean.setGeneralPremiumStatus(MOTOR_PREMIUMSTATUS);
			} else {
				String result=service.getQuoteMotor(bean);
				if("SUCCESS".equalsIgnoreCase(result)){
					mapAdditionalVehicleDetails();
				} else {
					if(DBConstants.DEVICETYPE_ANDROID.equals(bean.getDeviceType()) || DBConstants.DEVICETYPE_HYBRID.equals(bean.getDeviceType())) {
						bean.getActionErrorsBean().add(ResourceBundleUtil.findDefaultText("error.update"));
					} else {
						addActionError(ResourceBundleUtil.findDefaultText("error.update"));
					}
				}
			}
		}
		catch(Exception exception) {
			LogManager.debug(exception);
		}
		return INPUT;
	}
	
	public String tractorPremiuminfo() {
		try {
			premiumSummary();
			bean.setPremiumEditStatus("Y");
			bean.setReqFromq("tractor");
		}
		catch(Exception exception) {
			LogManager.debug(exception);
		}
		return TRACTOR_PREMIUM_INFO;
	}
	
	public String updatetractorPremiuminfo() {
		String forward = TRACTOR_PREMIUM_INFO;
		try {
			if("calc".equalsIgnoreCase(bean.getProcessType()) || "update".equalsIgnoreCase(bean.getProcessType())) {
				String result = service.updatetractorPremiuminfo(bean);
				if("SUCCESS".equalsIgnoreCase(result) && "update".equalsIgnoreCase(bean.getProcessType())) {
					forward = uwMenu();
				} else {
					if("FAILURE".equalsIgnoreCase(result)) {
						if(DBConstants.DEVICETYPE_ANDROID.equals(bean.getDeviceType()) || DBConstants.DEVICETYPE_HYBRID.equals(bean.getDeviceType())) {
							bean.getActionErrorsBean().add(ResourceBundleUtil.findDefaultText("error.update"));
						} else {
							addActionError(ResourceBundleUtil.findDefaultText("error.update"));
						}
						
					}
					forward = tractorPremiuminfo();
				}
			}
			else {
				forward = uwMenu();
			}
		}
		catch(Exception exception) {
			LogManager.debug(exception);
		}
		bean.setProcessType("");
		return forward;
	}
	
	public List<Map<String,Object>> getTractorCoverList() {
		return service.getTractorCoverList(bean.getApplicationNo());
	}
	
	public String homepolicyEndList() {
		getRequest().setAttribute(FIELD, "homepolicyEndList");
		return DROPDOWN;
	}
	public String homeLossOfLimbs() {
		getRequest().setAttribute(FIELD, "homeLossOfLimbs");
		bean.setLossLimbsList(service.setLossLimbsList(bean));
		return DROPDOWN;
	}
	public String premiumSubUploadAjax() {
		getRequest().setAttribute(FIELD, "premiumSubUploadAjax");
		bean.setAjScheme(bean.getSchemeId());
		subListMenu();
		return DROPDOWN;
	}
	public String saveDetailAjax() {
		getRequest().setAttribute(FIELD, "premiumSubUploadAjax");
		bean.setSchemeId(StringUtils.isNotBlank(bean.getAjScheme())?bean.getAjScheme():bean.getSchemeId());
		saveDetail();
		return DROPDOWN;
	}
	public String getDeleteAjax() {
		getRequest().setAttribute(FIELD, "premiumSubUploadAjax");
		bean.setSchemeId(StringUtils.isNotBlank(bean.getAjScheme())?bean.getAjScheme():bean.getSchemeId());
		getDelete();
		return DROPDOWN;
	}
	
	/**
	 * View Extended Cover, Excess, Warranties, Exclusions Start
	 */
	
	public String extendedCoverageAjax() {
		try{
			if("extendCover".equalsIgnoreCase(bean.getReqFrom())){
				bean.setExtendedCoverList(service.extendedCoverList(bean));
				service.setSelectedCover(bean);
			}else if("excess".equalsIgnoreCase(bean.getReqFrom())){
				bean.setExcessList(service.excessList(bean));
			}else if("warranties".equalsIgnoreCase(bean.getReqFrom())){
				bean.setWarrantiesList(service.warrantiesList(bean));
			}else if("excludeRisk".equalsIgnoreCase(bean.getReqFrom())){
				bean.setExclusionsList(service.exclusionsList(bean));
			}
		}catch(Exception e){
			addActionError("Something Went Wrong. Please Contact Technical Team.");
			LogManager.info("Exception @ HomeAction.saveExtendedCoverage() Error: "+e);
			e.printStackTrace();
		}finally{
			getRequest().setAttribute(FIELD, "extendedCoverageAjax");
			bean.setSchemeName(service.schemeNameById(bean.getSchemeId(), bean.getProductId()));
		}
		return DROPDOWN;
	}

	/**
	 * View Extended Cover, Excess, Warranties, Exclusions End
	 */
	
	/**
	 * Save Extended Cover, Excess, Warranties, Exclusions Start
	 */
	
	public String saveCoveragesAr(){
		try{
			if("extendCover".equalsIgnoreCase(bean.getReqFrom())){
				int ins=service.insExtendedCoverage(bean);
				if(ins>0)
					addActionMessage("Saved Successfully");
				else
					addActionError("Details Not Saved");
			}else if("excess".equalsIgnoreCase(bean.getReqFrom())){
				int ins = service.manipulateExcessDtls(bean);
				if(ins>0)
					addActionMessage("Saved Successfully");
				else
					addActionMessage("Default Excess Enabled.");
			}else if("warranties".equalsIgnoreCase(bean.getReqFrom())){
				int ins = service.manipulateWarrantyDtls(bean);
				if(ins>0)
					addActionMessage("Saved Successfully");
				else
					addActionMessage("Default Excess Enabled.");
			}else if("excludeRisk".equalsIgnoreCase(bean.getReqFrom())){
				int ins = service.manipulateExclusionDtls(bean);
				if(ins>0)
					addActionMessage("Saved Successfully");
				else
					addActionMessage("Default Excess Enabled.");
			}
		}catch(Exception e){
			LogManager.info("Exception @ HomeAction.saveCoveragesAr() Error: "+e);
			e.printStackTrace();
			addActionError("Something Went Wrong. Please Contact Technical Team.");
		}
		return extendedCoverageAjax();
	}
	
	/**
	 * Save Extended Cover, Excess, Warranties, Exclusions End
	 */
	
	public String saveExcess(){
		bean.setSchemeId(StringUtils.isNotBlank(bean.getAjScheme())?bean.getAjScheme():bean.getSchemeId());
		int ins=service.updateExcess(bean);
		if(ins>0)
			addActionMessage("Saved Successfully");
		else
			addActionError("Details Not Saved");
		
		bean.setReqFrom("excess");
		bean.setExcessList(service.excessList(bean));
		getRequest().setAttribute(FIELD, "extendedCoverageAjax");
		return DROPDOWN;
	}
	
	public List<Object> getPolicyEndList() {
		return commonDAO.getOptionsList("PolicyExpirydate2",bean.getProductId(), getParams("PolicyExpirydate"));
	}
	/*public List<Map<String,Object>> getExtraBenefitsList() {
		return service.getExtraBenefitsList();
	}*/
	/*public List<Map<String,Object>> getExcessList() {
		return service.getExcessList();
	}*/
	/************* Motor - Drop Down | Start *****************/
	public String noOfCylinderList(){
		getRequest().setAttribute(FIELD, "noOfCylinder");
		return DROPDOWN;
	}
	public String modelList(){
		getRequest().setAttribute(FIELD, "model");
		return DROPDOWN;
	}
	public List<Object> getMakeList() {
		return commonDAO.getOptionsList("make",bean.getProductId(), getMotorParams());
	}
	public List<Object> getModelList() {
		return commonDAO.getOptionsList("model",bean.getProductId(), getMotorParams());
	}
	public List<Object> getTypeBodyList() {
		return commonDAO.getOptionsList("TractorHP",bean.getProductId(), getMotorParams());
	}
	public LinkedHashMap<Integer,Integer> getMfgYrMap() {
		LinkedHashMap<Integer,Integer>map=new LinkedHashMap<Integer,Integer>();
		for(int i=Calendar.getInstance().get(Calendar.YEAR);i>=Calendar.getInstance().get(Calendar.YEAR)-10;i--)
			map.put(i,i);
		return map;
	}
	public List<Object> getNoOfCylinderList() {
		return commonDAO.getOptionsList("noOfCylinder",bean.getProductId(), getMotorParams());
	}
	public List<Object> getVehicleUsageList() {
		return commonDAO.getOptionsList("vehicleUsage",bean.getProductId(), getMotorParams());
	}
	public List<Object> getAreaCoverageList() {
		return commonDAO.getOptionsList("areaCoverage",bean.getProductId(), getMotorParams());
	}
	public List<Object> getVehicleColourList() {
		return commonDAO.getOptionsList("vehicleColour",bean.getProductId(), getMotorParams());
	}
	public List<Object> getBankOfFinanceList() {
		return commonDAO.getOptionsList("bankOfFinance",bean.getProductId(), getMotorParams());
	}
	public List<Object> getMotorPolicyTypeList() {
		return commonDAO.getOptionsList("PolicyType",bean.getProductId(), getMotorParams());
	}
	public List<Object> getUnNamedPassengerList() {
		return commonDAO.getOptionsList("UnNamedPassenger",bean.getProductId(), getMotorParams());
	}
	public List<Object> getPaidDriversList() {
		return commonDAO.getOptionsList("PaidDrivers",bean.getProductId(), getMotorParams());
	}
	public List<Object> getNoClaimBonusList() {
		return commonDAO.getOptionsList("NoClaimBonus",bean.getProductId(), getMotorParams());
	}
	public List<Object> getVoluntaryDeductibleList() {
		return commonDAO.getOptionsList("VolunataryDeductible",bean.getProductId(), getMotorParams());
	}
	private Object[] getMotorParams() {
		Object[] objects=new String[]{bean.getOption(),bean.getProductId(),bean.getBranchCode(),"","","",bean.getOriginCountry(),
				bean.getDestCountry(),"",bean.getMake(),bean.getTypeBody(),bean.getLoginId(),bean.getBrokerCode()};
		return objects;
	}
	
	private void mapAdditionalVehicleDetails() {
		try {
			List<Map<String,Object>> multiVehicleList = getMultiVehicleDetails();
			if(multiVehicleList.size()>0) {
				Map<String,Object> tempMap = multiVehicleList.get(0);
				
				bean.setMake(tempMap.get("MAKE_ID")==null?"":tempMap.get("MAKE_ID").toString());
				bean.setModel(tempMap.get("MODEL_ID")==null?"":tempMap.get("MODEL_ID").toString());
				bean.setTypeBody(tempMap.get("BODY")==null?"":tempMap.get("BODY").toString());
				bean.setMfgYr(tempMap.get("MANUFACTURE_YEAR")==null?"":tempMap.get("MANUFACTURE_YEAR").toString());
				bean.setSumInsured(tempMap.get("SUMINSURED_VALUE_LOCAL")==null?"":tempMap.get("SUMINSURED_VALUE_LOCAL").toString());
				bean.setCubicCapacity(tempMap.get("CUBIC_CAPACITY")==null?"":tempMap.get("CUBIC_CAPACITY").toString());
				bean.setSeatingCapacity(tempMap.get("SEATING_CAPACITY")==null?"":tempMap.get("SEATING_CAPACITY").toString());
				bean.setNoOfCylinder(tempMap.get("NO_OF_CYLINDER_ID")==null?"":tempMap.get("NO_OF_CYLINDER_ID").toString());
				bean.setVehicleUsage(tempMap.get("VEHICLE_TYPE")==null?"":tempMap.get("VEHICLE_TYPE").toString());
				bean.setAreaCoverage(tempMap.get("AREA_COVERAGE_ID")==null?"":tempMap.get("AREA_COVERAGE_ID").toString());
				bean.setDriverDOB(tempMap.get("DRIVER_DOB")==null?"":tempMap.get("DRIVER_DOB").toString());
				bean.setNoClaimBonus(tempMap.get("NO_CLAIM_BONUS")==null?"":tempMap.get("NO_CLAIM_BONUS").toString());
				bean.setClaimYN(tempMap.get("CLAIMYN")==null?"":tempMap.get("CLAIMYN").toString());
				bean.setClaimAmount(tempMap.get("CLAIM_AMOUNT")==null?"":tempMap.get("CLAIM_AMOUNT").toString());
				
				bean.setRegNo(tempMap.get("REGISTRATION_NO")==null?"":tempMap.get("REGISTRATION_NO").toString());
				bean.setTrailer1RegNo(tempMap.get("TRAILER1_REG_NO")==null?"":tempMap.get("TRAILER1_REG_NO").toString());
				bean.setTrailer2RegNo(tempMap.get("TRAILER2_REG_NO")==null?"":tempMap.get("TRAILER2_REG_NO").toString());
				mapRegNo("split");
				bean.setChassisNo(tempMap.get("CHASSIS_NO")==null?"":tempMap.get("CHASSIS_NO").toString());
				bean.setEngineNo(tempMap.get("ENGINE_NUMBER")==null?"":tempMap.get("ENGINE_NUMBER").toString());
				bean.setVehicleColour(tempMap.get("VEHICLE_COLOR_ID")==null?"":tempMap.get("VEHICLE_COLOR_ID").toString());
				bean.setVehicleRegLoc(tempMap.get("REGISTER_LOCATION_ID")==null?"":tempMap.get("REGISTER_LOCATION_ID").toString());
				bean.setPlateCharacter(tempMap.get("PLATE_COLOR_ID")==null?"":tempMap.get("PLATE_COLOR_ID").toString());
				bean.setLeasedYN(tempMap.get("LEASED")==null?"N":tempMap.get("LEASED").toString());
				bean.setBankOfFinance(tempMap.get("BANK_ID")==null?"":tempMap.get("BANK_ID").toString());
				bean.setInsNameArabic(tempMap.get("INSURED_NAME_ARABIC")==null?"":tempMap.get("INSURED_NAME_ARABIC").toString());
				bean.setInsAddressArabic(tempMap.get("INSURED_ADDRESS_ARABIC")==null?"":tempMap.get("INSURED_ADDRESS_ARABIC").toString());
				
				bean.setManufactureDate(tempMap.get("MANUFACTUREDATE")==null?"":tempMap.get("MANUFACTUREDATE").toString());
				bean.setMotorLampsYN(tempMap.get("LAMPSYN")==null?"":tempMap.get("LAMPSYN").toString());
				bean.setTrailerIdv1(tempMap.get("TRAILER1IDVSI")==null?"":tempMap.get("TRAILER1IDVSI").toString());
				bean.setTrailer1MfrDate(tempMap.get("TRAILER1DATE")==null?"":tempMap.get("TRAILER1DATE").toString());
				bean.setMotorPolicyType(tempMap.get("POLICYTYPE")==null?"":tempMap.get("POLICYTYPE").toString());
				bean.setTrailerIdv2(tempMap.get("TRAILER2IDVSI")==null?"":tempMap.get("TRAILER2IDVSI").toString());
				bean.setTrailer2MfrDate(tempMap.get("TRAILER2DATE")==null?"":tempMap.get("TRAILER2DATE").toString());
				bean.setOwnerDriver(tempMap.get("OWNDRIVEROPTED")==null?"":tempMap.get("OWNDRIVEROPTED").toString());
				bean.setUnNamedPassengersNos(tempMap.get("NOOFUNNAMED")==null?"":tempMap.get("NOOFUNNAMED").toString());
				bean.setUnNamedPassengersSi(tempMap.get("UNNAMEDSI")==null?"":tempMap.get("UNNAMEDSI").toString());
				bean.setPaidDriversNos(tempMap.get("NOOFDRIVERS")==null?"":tempMap.get("NOOFDRIVERS").toString());
				bean.setPaidDriversSi(tempMap.get("PAIDDRIVERSSI")==null?"":tempMap.get("PAIDDRIVERSSI").toString());
				bean.setLegalLibCover(tempMap.get("LIABILITYCOVEROPTED")==null?"":tempMap.get("LIABILITYCOVEROPTED").toString());
				bean.setDriverCondCleaner(tempMap.get("DRIVERCLEAN")==null?"":tempMap.get("DRIVERCLEAN").toString());
				bean.setNonFarePassenger(tempMap.get("NONFAREPAYING")==null?"":tempMap.get("NONFAREPAYING").toString());
				bean.setEmployeesInOperation(tempMap.get("EMPOPERATE")==null?"":tempMap.get("EMPOPERATE").toString());
				bean.setVoluntaryDeductible(tempMap.get("VOLUNTARY_DISC")==null?"":tempMap.get("VOLUNTARY_DISC").toString());
				bean.setElectricalAcc(tempMap.get("ELECTRICAL_SI")==null?"":tempMap.get("ELECTRICAL_SI").toString());
				bean.setNonElectricalAcc(tempMap.get("NONELECTRICAL_SI")==null?"":tempMap.get("NONELECTRICAL_SI").toString());
				bean.setBifuelKit(tempMap.get("BIFUELKITSI")==null?"":tempMap.get("BIFUELKITSI").toString());
				bean.setNoClaimBonusVal(tempMap.get("NO_CLAIM_BONUS_ID")==null?"":tempMap.get("NO_CLAIM_BONUS_ID").toString());
				bean.setLoadingOd(tempMap.get("LOADING_OD")==null?"":tempMap.get("LOADING_OD").toString());
				bean.setLoadingTp(tempMap.get("LOADING_TP")==null?"":tempMap.get("LOADING_TP").toString());
				bean.setNoOfTrailer(tempMap.get("NO_OF_TRAILER")==null?"":tempMap.get("NO_OF_TRAILER").toString());
				bean.setLtFinanceYN(tempMap.get("LT_FINANCE")==null?"":tempMap.get("LT_FINANCE").toString());
				bean.setRestrictedTPPDYN(tempMap.get("RESTRICT_TPPD")==null?"":tempMap.get("RESTRICT_TPPD").toString());
			}
		}
		catch(Exception exception) {
			LogManager.debug(exception);
		}
	}
	
	private void mapRegNo(String type) {
		try {
			if("join".equals(type)) {
				if(StringUtils.isNotBlank(bean.getRegNo1()) && StringUtils.isNotBlank(bean.getRegNo2()) && StringUtils.isNotBlank(bean.getRegNo3()) && StringUtils.isNotBlank(bean.getRegNo4())) {
					bean.setRegNo(bean.getRegNo1()+"-"+bean.getRegNo2()+"-"+bean.getRegNo3()+"-"+bean.getRegNo4());
				}
				if(StringUtils.isNotBlank(bean.getTrailer1RegNo1()) && StringUtils.isNotBlank(bean.getTrailer1RegNo2()) && StringUtils.isNotBlank(bean.getTrailer1RegNo3()) && StringUtils.isNotBlank(bean.getTrailer1RegNo4())) {
					bean.setTrailer1RegNo(bean.getTrailer1RegNo1()+"-"+bean.getTrailer1RegNo2()+"-"+bean.getTrailer1RegNo3()+"-"+bean.getTrailer1RegNo4());
				}
				if(StringUtils.isNotBlank(bean.getTrailer2RegNo1()) && StringUtils.isNotBlank(bean.getTrailer2RegNo2()) && StringUtils.isNotBlank(bean.getTrailer2RegNo3()) && StringUtils.isNotBlank(bean.getTrailer2RegNo4())) {
					bean.setTrailer2RegNo(bean.getTrailer2RegNo1()+"-"+bean.getTrailer2RegNo2()+"-"+bean.getTrailer2RegNo3()+"-"+bean.getTrailer2RegNo4());
				}
			}
			else if("split".equals(type)) {
				if(StringUtils.isNotBlank(bean.getRegNo())) {
					String[] tempRegNo = bean.getRegNo().split("-");
					bean.setRegNo1(tempRegNo[0]);
					bean.setRegNo2(tempRegNo[1]);
					bean.setRegNo3(tempRegNo[2]);
					bean.setRegNo4(tempRegNo[3]);
				}
				if(StringUtils.isNotBlank(bean.getTrailer1RegNo())) {
					String[] tempRegNo = bean.getTrailer1RegNo().split("-");
					bean.setTrailer1RegNo1(tempRegNo[0]);
					bean.setTrailer1RegNo2(tempRegNo[1]);
					bean.setTrailer1RegNo3(tempRegNo[2]);
					bean.setTrailer1RegNo4(tempRegNo[3]);
				}
				if(StringUtils.isNotBlank(bean.getTrailer2RegNo())) {
					String[] tempRegNo = bean.getTrailer2RegNo().split("-");
					bean.setTrailer2RegNo1(tempRegNo[0]);
					bean.setTrailer2RegNo2(tempRegNo[1]);
					bean.setTrailer2RegNo3(tempRegNo[2]);
					bean.setTrailer2RegNo4(tempRegNo[3]);
				}
			}
		}
		catch(Exception exception) {
			LogManager.debug(exception);
		}
	}
	
	public String coverPremiumInfo() {
		try {
			if("home".equalsIgnoreCase(bean.getReqFromq())) {
				bean.setPremiumList(service.getHomePremiumInfo(bean));
				bean.setSubPremium(service.subPremium(bean));
			}/* else if("personalAccident".equalsIgnoreCase(bean.getReqFromq())) {
				
			} else if("tractor".equalsIgnoreCase(bean.getReqFromq())) {
				
			}*/
		} catch(Exception exception) {
			LogManager.debug(exception);
		}
		return "coverPremiumInfo";
	}
	
	@JSON(serialize=false)
	public List<Map<String,Object>> getMultiVehicleDetails() {
		return service.getMultiVehicleDetails(bean.getApplicationNo(),bean.getProductId(), bean.getBranchCode());
	}
	@JSON(serialize=false)
	public List<Map<String,Object>> getFarmCoverDetailsList() {
		return service.getFarmCoverDetailsList(bean.getApplicationNo());
	}
	@JSON(serialize=false)
	public String getPaPremium() {
		return service.getCoverPremium(bean.getQuoteNo(), PACOVER_PREMIUMSTATUS);
	}
	@JSON(serialize=false)
	public String getMotorPremium() {
		return service.getCoverPremium(bean.getQuoteNo(), MOTOR_PREMIUMSTATUS);
	}
	@JSON(serialize=false)
	public String getHomePremium() {
		return service.getCoverPremium(bean.getQuoteNo(), HOME_PREMIUMSTATUS);
	}
	@JSON(serialize=false)
	public String getHomeReferralRemarks() {
		return service.getCoverReferralRemarks(bean.getQuoteNo(), HOME_PREMIUMSTATUS);
	}
	@JSON(serialize=false)
	public String getPaReferralRemarks() {
		return service.getCoverReferralRemarks(bean.getQuoteNo(), PACOVER_PREMIUMSTATUS);
	}
	@JSON(serialize=false)
	public String getMotorReferralRemarks() {
		return service.getCoverReferralRemarks(bean.getQuoteNo(), MOTOR_PREMIUMSTATUS);
	}
	@JSON(serialize=false)
	public String getReferralRemarks() {
		return service.getCoverReferralRemarks(bean.getQuoteNo(), "");
	}
	@JSON(serialize=false)
	public String getCoverReferralRemarks() {
		String extendedCoverRef=getExtendedReferralRemarks();
		String referralRemarks = (StringUtils.isNotBlank(getHomeReferralRemarks())?getHomeReferralRemarks()+"~":"")
								+ (StringUtils.isNotBlank(getPaReferralRemarks())?getPaReferralRemarks()+"~":"")
								+ (StringUtils.isNotBlank(getMotorReferralRemarks())?getMotorReferralRemarks()+"~":"")
								+ (StringUtils.isNotBlank(extendedCoverRef)?extendedCoverRef:"");
		return referralRemarks;
	}
	@JSON(serialize=false)
	private String getExtendedReferralRemarks() {
		return service.getExtendedReferralRemarks(bean.getQuoteNo(), "extendedCover");
	}
	@JSON(serialize=false)
	public String getSubDetailReferralRemarks() {
		return service.getSubDetailReferralRemarks(bean.getQuoteNo());
	}
	/************** Motor - Drop Down | End *******************/
	
	
	/************** Rest - Webservice | Start *******************/
	public void mapRestBean(HomeBean bean) {
		bean.setDestCountry("1");
		bean.setOriginCountry("1");
		bean.setActionErrorsBean(new ArrayList<String>());
		bean.setActionMessagesBean(new ArrayList<String>());
		this.bean = bean;
	}
	public  List<Object> getDropdownListRest() {
		List<Object> list = null;
		if("policyInformation".equals(bean.getOption())) {
			list = (List) getPolicyInformation();
		} else {
			list = commonDAO.getOptionsList(bean.getOption(), bean.getProductId() ,getParams(bean.getOption()));
		}
		return list;
	}
	/************** Rest - Webservice | End *******************/
	/*****************************************************************************/
	private final javax.servlet.http.HttpServletRequest getRequest() {
		return org.apache.struts2.ServletActionContext.getRequest();
	}
	private final Map<String, Object> getSession() {
		return com.opensymphony.xwork2.ActionContext.getContext().getSession();
	}
	private final boolean hasErrorValues() {
		boolean result = false;
		if( DBConstants.DEVICETYPE_ANDROID.equals(bean.getDeviceType()) || DBConstants.DEVICETYPE_HYBRID.equals(bean.getDeviceType()) ) {
			if(bean.getActionErrorsBean()!=null && bean.getActionErrorsBean().size()>0) {
				result = true;
			}
		} else if(hasActionErrors()) {
			result = true;
		}
		return result;
	}
	private void mapLoginDetails(){
		if(StringUtils.isBlank(bean.getIssuer()) && StringUtils.isBlank(bean.getBrokerCode())){
			bean.setBrokerCode(commonDAO.getSingleInfo("GET_MOTOR_AG_CODE", new String[]{bean.getLoginId()}));
		}
		if("B2C".equalsIgnoreCase(bean.getLoginType())){
			bean.setExecutive("5");
		}
	}
	
	public String getNext(String uid,List<String> list) {
	    int idx = list.indexOf(uid);
	    if (idx < 0 || idx+1 == list.size()) return "";
	    return list.get(idx + 1);
	}
	public String getPrevious(String uid,List<String> list) {
	    int idx = list.indexOf(uid);
	    if (idx <= 0) return "";
	    return list.get(idx - 1);
	}
	public String paCoverNew() {
		String forward = "";
		try {
			if("Y".equals(getPaCoverStatus())) {
				bean.setGeneralPremiumStatus(PACOVER_PREMIUMSTATUS);
				personalInformation();
				service.getPaCoverDetails(bean);
				bean.getPaInsuredId();
				bean.setPaName("");
				bean.setPaDob("");
				bean.setPaIndurer("");
				bean.setPaRelationship("");
				bean.setPaOccupation("");
				bean.setPaTableOfBenifits("");
				bean.setPaSumInsured("");
				bean.setPaAnnualIncome("");
				bean.setPaFatherFirstName("");
				bean.setPaFatherMiddleName("");
				bean.setPaFatherLastName("");
				bean.setPaExitingDisability("");
				bean.setPaDisabilityDesc("");
				bean.setPaOptionType("");
				bean.setPaInsuredId("");
				bean.setPaMedicalExtn("");
				bean.setPaCostOfTravel("");
				bean.setPaSupportingItem("");
				forward = FARMER_PA_COVER;
			}
			else {
				forward = edittractorNew();
			}
		} catch(Exception exception) {
			LogManager.debug(exception);
		}
		return forward;
	}
	public String edittractorNew() {
		String forward = "";
		try {
			if("Y".equals(getTractorCoverStatus())) {
				bean.setGeneralPremiumStatus(MOTOR_PREMIUMSTATUS);
				forward = FARMER_MOTOR;
				if(StringUtils.isNotBlank(bean.getApplicationNo())) {
					service.getFirstPageDtls(bean);
					bean.setDisplay("newQuote");
					mapAdditionalVehicleDetails();
					premiumSummary();
				}
			}
			else {
				forward = uwMenuNew();
			}
		}
		catch(Exception exception) {
			LogManager.debug(exception);
		}
		return forward;
	}
	public String uwMenuNew() {
		LogManager.info("Enter into Home-uwMenu()");
		
		premiumSummary();
		if("validuw".equals(bean.getFrom())){
			if(!hasErrorValues()) {
				bean.setCoverageList(service.getUWDetails(bean));
				if(bean.getCoverageList()!=null && bean.getCoverageList().size()>0) {
					if(DBConstants.DEVICETYPE_ANDROID.equals(bean.getDeviceType()) || DBConstants.DEVICETYPE_HYBRID.equals(bean.getDeviceType())) {
						bean.getActionErrorsBean().add(ResourceBundleUtil.findText(bean.getProductId(),"coverage.value.added"));
					} else {
						addActionError(ResourceBundleUtil.findText(bean.getProductId(),"coverage.value.added"));
					}
				}
				bean.setFrom("");
			}
		} else {
			bean.setCoverageList(service.getUWDetails(bean));
		} if(bean.getCoverageList()==null || bean.getCoverageList().size()<=0){
			List<Map<String, Object>> list=new ArrayList<Map<String, Object>>();
			Map<String, Object> map=new HashMap<String, Object>();
			map.put("BUILT_OF_CONCRETE", "N");
			map.put("PRIVATE_LIVING_ACCOMODATION", "N");
			map.put("BUILT_ON_RECLAIMED_LAND", "N");
			map.put("UNATTENDED_60_CONSECUTIVE_DAYS", "N");
			map.put("DECLINED_CANELLED_CONDITIONS", "N");
			map.put("ANY_CLAIM_IN_3YRS", "N");
			list.add(map);
			bean.setCoverageList(list);
		}
		bean.setPremiumInfoType("TOGGLE");
		LogManager.info("Exit from Home-uwMenu()");
		return HOME_PREMIUMINFO;
	}
	
	public String nextSchemeNew(){
		try {
			String[] scheme=bean.getSchemeSelected().split(",");
			List<String> schemeList=new ArrayList<String>();
			for (String list : scheme) {
				schemeList.add(list.trim());
			}
			bean.setSchemeList(schemeList);
			
			
			service.setExistSchemeDtls(bean);
			String[] schemeExist=bean.getExistSchemeSelected().split(",");
			List<String> schemeListNew=new ArrayList<String>();
			for (String listNew : schemeExist) {
				schemeListNew.add(listNew.trim());
			}
			
			for(int i=0; i<schemeListNew.size(); i++){
				if(!schemeList.contains(schemeListNew.get(i)))
					service.checkSchemeExist(bean,schemeListNew.get(i));
			}
			personalInformation();
			getRenderNextSchemeNew();
			if(!hasErrorValues()) {
				service.getOFSData(bean);
				 if(StringUtils.isNotBlank(bean.getNextScheme())){
					bean.setPrevScheme(bean.getSchemeId());
					getSession().put("scheme_id", bean.getNextScheme());
					bean.setSchemeId(bean.getNextScheme());
					bean.setNextScheme(getNext(bean.getNextScheme(),bean.getSchemeList()));
				 }
			}
			if("QE".equalsIgnoreCase(bean.getMenuType()))
				bean.setBtnYN("Y");
			
			getSession().put("ContentType", "0");
			
			bean.setLocationSize(service.getLocationSizeDetails(bean,"scheme"));
			if("1".equalsIgnoreCase(bean.getLocationSize()) && StringUtils.isNotBlank(bean.getQuoteNo())){
				List<Map<String, Object>> locationId=service.getSelectedSchemeLocation(bean);
				if(locationId!=null && locationId.size()>0){
					bean.setLocationId(locationId.get(0).get("LOCATION_ID")==null?"":locationId.get(0).get("LOCATION_ID").toString());
					bean.setSingleLocName(locationId.get(0).get("LOCATION_NAME")==null?"":locationId.get(0).get("LOCATION_NAME").toString());	
				}
			}
			bean.setHomeDesc(service.getHomeInfoDesc(bean));
			bean.setHome(service.getHomeInfo(bean));
			bean.setSubhome(service.getSubHomeInfo(bean));
			service.setSumInsuredDetails(bean);
			service.deleteOfsDataDtlTempIns(bean);
			bean.setDisplay("getQuote");
		}
		catch(Exception exception) {
			LogManager.debug("Exception @ nextSchemeNew() " + exception);
			exception.printStackTrace();
		}
		return INPUT;
	}
	
	public String editSchemeNew(){
		try {
			String[] scheme=bean.getSchemeSelected().split(",");
			List<String> schemeList=new ArrayList<String>();
			for (String list : scheme) {
				schemeList.add(list.trim());
			}
			bean.setSchemeList(schemeList);
			 if(StringUtils.isNotBlank(bean.getPrevScheme())){
				 bean.setNextScheme(bean.getSchemeId());
				 getSession().put("scheme_id", bean.getPrevScheme());
				 bean.setSchemeId(bean.getPrevScheme());
				bean.setPrevScheme(getPrevious(bean.getPrevScheme(),bean.getSchemeList()));
			 }
			if("QE".equalsIgnoreCase(bean.getMenuType()))
				bean.setBtnYN("Y");
				
			getSession().put("ContentType", "0");
			if(!hasErrorValues()) {
				service.getOFSData(bean);
			}
			personalInformation();
			
			bean.setLocationSize(service.getLocationSizeDetails(bean,"scheme"));
			if("1".equalsIgnoreCase(bean.getLocationSize()) && StringUtils.isNotBlank(bean.getQuoteNo())){
				List<Map<String, Object>> locationId=service.getSelectedSchemeLocation(bean);
				if(locationId!=null && locationId.size()>0){
					bean.setLocationId(locationId.get(0).get("LOCATION_ID")==null?"":locationId.get(0).get("LOCATION_ID").toString());
					bean.setSingleLocName(locationId.get(0).get("LOCATION_NAME")==null?"":locationId.get(0).get("LOCATION_NAME").toString());
				}
			}
			bean.setHomeDesc(service.getHomeInfoDesc(bean));
			bean.setHome(service.getHomeInfo(bean));
			bean.setSubhome(service.getSubHomeInfo(bean));
			service.setSumInsuredDetails(bean);
			
			service.deleteOfsDataDtlTempIns(bean);
			bean.setDisplay("getQuote");
		}catch(Exception exception) {
			LogManager.debug("Exception @ editSchemeeditSchemeNew() " + exception);
		}
		return INPUT;
	
	}
	
	public String getRenderNextSchemeNew(){
		String forward=INPUT;
		try{
			//getValidate("getQuoteHomeLocation");
			getValidate("particularScheme");
			if(!hasErrorValues()){
				if(StringUtils.isNotEmpty(bean.getIssuer())) {
					bean.setLoginId(new com.maan.common.dao.CommonDAO().getSingleInfo("loginId", new String[]{bean.getBrokerCode()}));
				}
				List<Map<String,Object>> mainCoverValues=null;
				List<Map<String,Object>> subCoverValues=null;
					double base_premium=0.0D;
					double option_premium=0.0D;
					double discount_premium = 0;
					String referal="NR";
					String validationStatus = "";
					String validationMessage = "";
					if("Y".equalsIgnoreCase(bean.getModifyYN())){
						try{
							String linkFrom="";
							String sum="";
							List<Map<String,Object>> calcType=null;
							HashMap<String,Object> baseSts = new HashMap<String,Object>();
							HashMap<String,Object> baserate = new HashMap<String,Object>();
							HashMap<String,Object> subbaseids = new HashMap<String,Object>();
							HashMap<String,Object> subbaserate = new HashMap<String,Object>();
							HashMap<String,Object> baseratests=new HashMap<String,Object>();
							HashMap<String,Object> subbaseratests=new HashMap<String,Object>();
							HashMap<String,Object> forumulaSum=new HashMap<String,Object>();
							String temp="";
							
							int del=service.deleteExist(bean);
							
							List<Map<String,Object>> locationId=service.getSelectedSchemeLocation(bean);
							
							for(int a=0;a<Integer.parseInt(bean.getLocationSize());a++){
								bean.setLocationId(locationId.get(a).get("LOCATION_ID")==null?"":locationId.get(a).get("LOCATION_ID").toString());
								bean.setCallFrom("insert");
								mainCoverValues = service.getHomeInfo(bean);
								subCoverValues = service.getSubHomeInfo(bean);
								
								List<Object> mainPremium=new ArrayList<Object>();
								List<Object> submainPremium=new ArrayList<Object>();
	
								if(mainCoverValues!=null && mainCoverValues.size()>0){
									for(int i=0; i<mainCoverValues.size(); i++){
										Map<String,Object> map = mainCoverValues.get(i);
										if(map.get("SUB_COVERAGES")!=null && map.get("SUB_COVERAGES").toString().equalsIgnoreCase("N")){
											map.put("LOCATION_ID", bean.getLocationId());
											if("1".equalsIgnoreCase(bean.getLocationSize()))
												map.put("SUM_INSURED", StringUtils.isBlank(bean.getSUM_INSURED().get(i+1))?"0":bean.getSUM_INSURED().get(i+1).replaceAll(",", ""));
											else
												map.put("SUM_INSURED", StringUtils.isBlank(bean.getSumInsuredNM()[a][i])?"0":bean.getSumInsuredNM()[a][i].replaceAll(",", ""));
											if("admin".equals(bean.getUserType()) || "RSAIssuer".equals(bean.getUserType())) {
												//map.put("base_rate", bean.getCOVERAGES_BASE_RATE().get(Integer.valueOf(bean.getCOVER_ID().get(i+1))));
												map.put("base_rate", bean.getCOVERAGES_BASE_RATE().get(Integer.valueOf(i+1)));
											}
		
											if(map.get("calc_type")!=null && map.get("calc_type").toString().equalsIgnoreCase("G")){
												if(map.get("base_rate")!=null)//) && map.get("base_rate").toString().indexOf(",")!=-1)
												{
													temp=map.get("SUM_INSURED").toString();
												}else{temp=map.get("SUM_INSURED_LIMIT").toString();}
												calcType=service.getBaseRate(map.get("COVERAGES_ID").toString(), "0", bean.getProductId(), bean.getSchemeId()+"", bean.getContentTypeId(), temp);
												if(calcType!=null && calcType.size()>0 && (calcType.get(0)).values().toArray()[1]!=null){
													map.put("MAIN_CALC_TYPE", (calcType.get(0)).values().toArray()[1]);
												}else{
													baseratests.put("MAIN_CALC_TYPE", map.get("CALC_TYPE").toString());
													map.put("MAIN_CALC_TYPE", map.get("CALC_TYPE").toString());
												}
												if(calcType!=null && !"admin".equalsIgnoreCase(linkFrom) && calcType.size()>0){
													map.put("NEW_BASE_RATE", (calcType.get(0)).values().toArray()[0]);
													map.put("MINIMUM_PREMIUM", (calcType.get(0)).values().toArray()[2]);
												}
											}else if(map.get("CALC_TYPE").toString()!=null && !map.get("CALC_TYPE").toString().equalsIgnoreCase("G")){
												baseratests.put(map.get("COVERAGES_ID").toString(), map.get("CALC_TYPE").toString());
											}
		
											boolean status=service.getFormulaCount(bean.getProductId(), bean.getSchemeId()+"", map.get("COVERAGES_ID").toString());
											System.out.println("formula status: ==>"+status);
											if(status){
												sum=service.getFormulaSum(bean.getProductId(), bean.getSchemeId()+"", map.get("COVERAGES_ID").toString(), bean.getQuoteNo());
												if(sum!=null && sum.length()>0)forumulaSum.put(map.get("COVERAGES_ID").toString(), sum);
												System.out.println("formula sum: ==>"+sum);
											}
											mainPremium.add(map);
										}
									}
								}
		
								if(subCoverValues!=null && subCoverValues.size()>0){
									for(int i=0; i<subCoverValues.size(); i++){
										Map<String,Object> map=(Map<String,Object>)subCoverValues.get(i);
										map.put("SUM_INSURED", bean.getCOVERAGES_COVERED_EMPLOYEES().get(i+1));
										if("admin".equals(bean.getUserType()) || "RSAIssuer".equals(bean.getUserType())) {
											map.put("SUB_RATE", bean.getCOVERAGES_SUB_BASE_RATE().get(Integer.valueOf(map.get("COVERAGES_SUB_ID").toString())));
										}
										if(map.get("CALC_TYPE")!=null && map.get("CALC_TYPE").toString().equalsIgnoreCase("G")){
											if(map.get("base_rate")!=null)//) && map.get("base_rate").toString().indexOf(",")!=-1)
											{
												temp=map.get("SUM_INSURED").toString();
											}else{temp=map.get("SUB_COVERAGES_LIMIT").toString();}
											System.out.println("cover==>"+map.get("COVERAGES_ID")+": "+map.get("COVERAGES_SUB_ID"));
											calcType=service.getBaseRate(map.get("COVERAGES_ID").toString(), map.get("COVERAGES_SUB_ID").toString(), bean.getProductId(), bean.getSchemeId(), bean.getContentTypeId(), temp);
											Object calcType1[]=(calcType.get(0)).values().toArray();
											if(calcType!=null && calcType.size()>0 && (calcType.get(0)).values().toArray()[1]!=null){
												map.put("MAIN_CALC_TYPE", calcType1[1]);
											}else{
												subbaseratests.put("MAIN_CALC_TYPE", map.get("CALC_TYPE").toString());
											}
											if(calcType!=null && calcType.size()>0 && !"admin".equalsIgnoreCase(linkFrom) && calcType1[0]!=null){
												map.put("NEW_BASE_RATE", calcType1[0]);
												map.put("MINIMUM_PREMIUM", calcType1[2]);
											}
										}else if(map.get("CALC_TYPE")!=null && !map.get("CALC_TYPE").toString().equalsIgnoreCase("G")){
											subbaseratests.put(map.get("COVERAGES_SUB_ID").toString(), map.get("CALC_TYPE"));
										}
		
										boolean status=service.getFormulaCount(bean.getProductId(), bean.getSchemeId()+"", map.get("COVERAGES_SUB_ID").toString());
										System.out.println("formula status: =====>>>>>"+status);
										if(status)
										{
											sum=service.getFormulaSum(bean.getProductId(), bean.getSchemeId()+"", map.get("COVERAGES_SUB_ID").toString(), bean.getQuoteNo());
											if(sum!=null && sum.length()>0)forumulaSum.put((String)map.get("COVERAGES_SUB_ID"), sum);
											System.out.println("formula sum: =====>>>>>"+sum);
										}
										submainPremium.add(map);
									}
								}
								Set<String> hotkeys=baseSts.keySet();
								Iterator<String> en=hotkeys.iterator();
								while(en.hasNext())
								{
									String coverid=(String)en.next();
									String value="true";
									String calType=(String)baseratests.get(coverid);
									System.out.println("cover: "+coverid+" sum: "+value+" type: "+calType);
									System.out.println("Base Rate%: "+baserate.get(coverid));
									if(calType.equalsIgnoreCase("G"))
									{
										List<Map<String,Object>> rate=service.getBaseRate(coverid, "0", bean.getProductId(), bean.getSchemeId(), bean.getContentTypeId(), value);
										System.out.println("Rate: =====> "+" rate: "+rate.size());
										if(rate.size()>0){
											Map<String,Object> map= rate.get(0);
											baseratests.remove(coverid);
											baseratests.put(coverid, map.values().toArray()[1]);
											if(!"adminCalculate".equalsIgnoreCase(linkFrom) && !"adminProceedInfo".equalsIgnoreCase(linkFrom))
											{
												baserate.remove(coverid);
												baserate.put(coverid, map.values().toArray()[0]);
											}
										}
										System.out.println("calcType==>: "+baseratests.get(coverid));
									}
								}
		
								//block for subbase_rate
								Set<String> hotkeys1=subbaseids.keySet();
								Iterator<String> en1=hotkeys1.iterator();
								while(en1.hasNext())
								{
									String subcoverid=(String)en1.next();
									String value="true";
									String calType=(String)subbaseratests.get(subcoverid);
									System.out.println("cover: "+subcoverid+" sum: "+value+" type: "+calType);
									if(calType.equalsIgnoreCase("G"))
									{
										List<Map<String,Object>> rate= service.getBaseRate(subcoverid, "0", bean.getProductId(), bean.getSchemeId(), bean.getContentTypeId(), value);
										System.out.println("Rate: =====> "+" rate: "+rate.size());
										if(rate.size()>0){
											Map<String,Object> map = rate.get(0);
											subbaseratests.remove(subcoverid);
											subbaseratests.put(subcoverid, map.values().toArray()[1]);
											if(!"adminCalculate".equalsIgnoreCase(linkFrom) && !"adminProceedInfo".equalsIgnoreCase(linkFrom))
											{
												subbaserate.remove(subcoverid);
												subbaserate.put(subcoverid, map.values().toArray()[0]);
											}
										}
									}
								}
								
								base_premium = service.insertMasterPremiumInfo(bean,mainPremium);
								option_premium = service.insertOptionalPremiumInfo(bean,submainPremium);
								service.updateCalculatedSumIns(bean);
								
							}
							service.updRefRemarks(bean,bean.getValidCoverage());
							
							Map<String,Object> outputValues = service.getCalculatePremium(bean.getQuoteNo(),bean.getSchemeId(),bean.getLocationId());
							base_premium = outputValues.get("PVBASEOUT")==null?0:Double.valueOf(outputValues.get("PVBASEOUT").toString());
							option_premium = outputValues.get("PVOPTIONALOUT")==null?0:Double.valueOf(outputValues.get("PVOPTIONALOUT").toString());
							discount_premium = outputValues.get("PVOLINEDISCOUNT")==null?0:Double.valueOf(outputValues.get("PVOLINEDISCOUNT").toString());
					     	validationStatus = outputValues.get("PVVALIDSTATUS")==null?"":outputValues.get("PVVALIDSTATUS").toString();
					     	validationMessage = outputValues.get("PVVALIDERROR")==null?"":outputValues.get("PVVALIDERROR").toString();
						     	
							service.premiumCalcIndividualScheme(bean);
							mainCoverValues = service.getMainCoverageWithQuote(bean, linkFrom);
							subCoverValues = service.getSubCoverageWithQuote(bean, linkFrom);
	
							//block for sum_insured amount from formula
							Set<String> hotkeys2=baseSts.keySet();
							Iterator<String> en2=hotkeys2.iterator();
							while(en2.hasNext())
							{
								String coverid=(String)en2.next();
								boolean status=service.getFormulaCount(bean.getProductId(), bean.getSchemeId(), coverid);
								System.out.println("formula status: =====>>>>>"+status);
								if(status){
									sum=service.getFormulaSum(bean.getProductId(), bean.getSchemeId(), coverid, bean.getQuoteNo());
									if(sum!=null && sum.length()>0)forumulaSum.put(coverid, sum);
									System.out.println("formula sum: =====>>>>>"+sum);
								}
							}
							
							if("ProceedInfo".equalsIgnoreCase(linkFrom) || "adminProceedInfo".equalsIgnoreCase(linkFrom))
							{
								double temp_premium=0.0;
								String amount="";
								double totalPremium=0.0;
								if(mainCoverValues.size()>0){
									for(int i=0; i<mainCoverValues.size(); i++){
										Map<String,Object> map = mainCoverValues.get(i);
										if ("Y".equalsIgnoreCase(map.get("CALC_STATUS").toString())){
											if("textbox".equalsIgnoreCase(map.get("SUM_INSURED_CONTROL_TYPE").toString()) || "dropdown".equalsIgnoreCase(map.get("SUM_INSURED_CONTROL_TYPE").toString())){
												amount=map.get("COVERAGES_LIMIT")==null?"":map.get("COVERAGES_LIMIT").toString();
											}
											else
												amount=map.get("SUM_INSURED_LIMIT").toString();
	
											if(forumulaSum.size()>0 && forumulaSum.containsKey(map.get("COVERAGES_ID"))){
												amount=(String)forumulaSum.get(map.get("COVERAGES_ID"));
											}
											temp_premium=service.getPremium((String)baseratests.get(map.get("COVERAGES_ID")),Double.parseDouble(map.get("BASE_RATE") == null ? "0": map.get("BASE_RATE").toString()),amount == null ? "0" : amount,"1");
											totalPremium+=temp_premium;
										}
									}
									//LogManager.info("Total: "+totalPremium+"icon: "+icon.get("11"));
									service.updatePremium(totalPremium, referal, bean.getUserType(),bean.getQuoteNo(),bean.getProductId(),bean.getSchemeId(),bean.getContentTypeId(),bean.getMenuType(),bean.getBranchCode());
								}
							}
						}catch(Exception e){
							LogManager.debug(e);
						}
					}

					
					if(bean.getValidCoverage()!=null && bean.getValidCoverage().length()>0 && !"admin".equals(bean.getUserType()) && !"RSAIssuer".equalsIgnoreCase(bean.getUserType())){
						service.updOfsReferralRemarks(bean.getQuoteNo(),bean.getValidCoverage());
					}else if((!"admin".equals(bean.getUserType()))){
						bean.setPremiumList(service.getHomePremiumInfo(bean));
						bean.setSubPremium(service.subPremium(bean));
					}
					
					int bcoverCount = service.getBaseCoverCnt(bean.getQuoteNo());
					if("Y".equals(validationStatus)) {
							addActionError(validationMessage);
					} else if(bcoverCount==0) {
							addActionError(getText("error.coverage.na"));
					}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return forward;
	
	}
	public String getRenderV1() {
		String forward=INPUT;
		try{
			//getValidate("getQuoteHomeLocation");
			getValidate("particularScheme");
			if(!hasErrorValues()){
				if(("RR".equalsIgnoreCase(bean.getQuoteStatus()) || "RU".equalsIgnoreCase(bean.getQuoteStatus()) || "RA".equalsIgnoreCase(bean.getQuoteStatus()))){
					service.adminReferralStatus(bean);
				}
				if(StringUtils.isNotBlank(bean.getSchemeSelected())){
					String[] scheme=bean.getSchemeSelected().split(",");
					List<String> schemeList=new ArrayList<String>();
					for (String list : scheme) {
						schemeList.add(list.trim());
					}
					bean.setSchemeList(schemeList);
					
					service.setExistSchemeDtls(bean);
					String[] schemeExist=bean.getExistSchemeSelected().split(",");
					List<String> schemeListNew=new ArrayList<String>();
					for (String listNew : schemeExist) {
						schemeListNew.add(listNew.trim());
					}
					
					for(int i=0; i<schemeListNew.size(); i++){
						if(!schemeList.contains(schemeListNew.get(i)))
							service.checkSchemeExist(bean,schemeListNew.get(i));
					}
					
				}
				if(StringUtils.isNotEmpty(bean.getIssuer())) {
					bean.setLoginId(new com.maan.common.dao.CommonDAO().getSingleInfo("loginId", new String[]{bean.getBrokerCode()}));
				}
				List<Map<String,Object>> mainCoverValues=null;
				List<Map<String,Object>> subCoverValues=null;
				 
					double base_premium=0.0D;
					double option_premium=0.0D;
					double discount_premium = 0;
					String referal="NR";
					String validationStatus = "";
					String validationMessage = "";
					if("Y".equalsIgnoreCase(bean.getModifyYN())){
						try{
							String linkFrom="";
							String sum="";
							List<Map<String,Object>> calcType=null;
							HashMap<String,Object> baseSts = new HashMap<String,Object>();
							HashMap<String,Object> baserate = new HashMap<String,Object>();
							HashMap<String,Object> subbaseids = new HashMap<String,Object>();
							HashMap<String,Object> subbaserate = new HashMap<String,Object>();
							HashMap<String,Object> baseratests=new HashMap<String,Object>();
							HashMap<String,Object> subbaseratests=new HashMap<String,Object>();
							HashMap<String,Object> forumulaSum=new HashMap<String,Object>();
							String temp="";
							
							int del=service.deleteExist(bean);
							List<Map<String, Object>> locationId=service.getSelectedSchemeLocation(bean);
							
							for(int a=0;a<Integer.parseInt(bean.getLocationSize());a++){
								bean.setLocationId(locationId.get(a).get("LOCATION_ID")==null?"":locationId.get(a).get("LOCATION_ID").toString());
								bean.setCallFrom("insert");
								mainCoverValues = service.getHomeInfo(bean);
								subCoverValues = service.getSubHomeInfo(bean);
								
								List<Object> mainPremium=new ArrayList<Object>();
								List<Object> submainPremium=new ArrayList<Object>();
								
								if(mainCoverValues!=null && mainCoverValues.size()>0){
									for(int i=0; i<mainCoverValues.size(); i++){
										Map<String,Object> map = mainCoverValues.get(i);
										if(map.get("SUB_COVERAGES")!=null && map.get("SUB_COVERAGES").toString().equalsIgnoreCase("N")){
											map.put("LOCATION_ID", bean.getLocationId());
											if("1".equalsIgnoreCase(bean.getLocationSize()))
												map.put("SUM_INSURED", StringUtils.isBlank(bean.getSUM_INSURED().get(i+1))?"0":bean.getSUM_INSURED().get(i+1).replaceAll(",", ""));
											else
												map.put("SUM_INSURED", StringUtils.isBlank(bean.getSumInsuredNM()[a][i])?"0":bean.getSumInsuredNM()[a][i].replaceAll(",", ""));
											if("admin".equals(bean.getUserType()) || "RSAIssuer".equals(bean.getUserType())) {
												map.put("base_rate", bean.getCOVERAGES_BASE_RATE().get(Integer.valueOf(i+1)));
											}
		
											if(map.get("calc_type")!=null && map.get("calc_type").toString().equalsIgnoreCase("G")){
												if(map.get("base_rate")!=null)//) && map.get("base_rate").toString().indexOf(",")!=-1)
												{
													temp=map.get("SUM_INSURED").toString();
												}else{temp=map.get("SUM_INSURED_LIMIT").toString();}
												calcType=service.getBaseRate(map.get("COVERAGES_ID").toString(), "0", bean.getProductId(), bean.getSchemeId()+"", bean.getContentTypeId(), temp);
												if(calcType!=null && calcType.size()>0 && (calcType.get(0)).values().toArray()[1]!=null){
													map.put("MAIN_CALC_TYPE", (calcType.get(0)).values().toArray()[1]);
												}else{
													baseratests.put("MAIN_CALC_TYPE", map.get("CALC_TYPE").toString());
													map.put("MAIN_CALC_TYPE", map.get("CALC_TYPE").toString());
												}
												if(calcType!=null && !"admin".equalsIgnoreCase(linkFrom) && calcType.size()>0){
													map.put("NEW_BASE_RATE", (calcType.get(0)).values().toArray()[0]);
													map.put("MINIMUM_PREMIUM", (calcType.get(0)).values().toArray()[2]);
												}
											}else if(map.get("CALC_TYPE").toString()!=null && !map.get("CALC_TYPE").toString().equalsIgnoreCase("G")){
												baseratests.put(map.get("COVERAGES_ID").toString(), map.get("CALC_TYPE").toString());
											}
		
											boolean status=service.getFormulaCount(bean.getProductId(), bean.getSchemeId()+"", map.get("COVERAGES_ID").toString());
											System.out.println("formula status: ==>"+status);
											if(status){
												sum=service.getFormulaSum(bean.getProductId(), bean.getSchemeId()+"", map.get("COVERAGES_ID").toString(), bean.getQuoteNo());
												if(sum!=null && sum.length()>0)forumulaSum.put(map.get("COVERAGES_ID").toString(), sum);
												System.out.println("formula sum: ==>"+sum);
											}
											mainPremium.add(map);
										}
									}
								}
		
								if(subCoverValues!=null && subCoverValues.size()>0){
									for(int i=0; i<subCoverValues.size(); i++){
										Map<String,Object> map=(Map<String,Object>)subCoverValues.get(i);
										map.put("SUM_INSURED", bean.getCOVERAGES_COVERED_EMPLOYEES().get(i+1));
										if("admin".equals(bean.getUserType()) || "RSAIssuer".equals(bean.getUserType())) {
											map.put("SUB_RATE", bean.getCOVERAGES_SUB_BASE_RATE().get(Integer.valueOf(map.get("COVERAGES_SUB_ID").toString())));
										}
										if(map.get("CALC_TYPE")!=null && map.get("CALC_TYPE").toString().equalsIgnoreCase("G")){
											if(map.get("base_rate")!=null)//) && map.get("base_rate").toString().indexOf(",")!=-1)
											{
												temp=map.get("SUM_INSURED").toString();
											}else{temp=map.get("SUB_COVERAGES_LIMIT").toString();}
											System.out.println("cover==>"+map.get("COVERAGES_ID")+": "+map.get("COVERAGES_SUB_ID"));
											calcType=service.getBaseRate(map.get("COVERAGES_ID").toString(), map.get("COVERAGES_SUB_ID").toString(), bean.getProductId(), bean.getSchemeId(), bean.getContentTypeId(), temp);
											Object calcType1[]=(calcType.get(0)).values().toArray();
											if(calcType!=null && calcType.size()>0 && (calcType.get(0)).values().toArray()[1]!=null){
												map.put("MAIN_CALC_TYPE", calcType1[1]);
											}else{
												subbaseratests.put("MAIN_CALC_TYPE", map.get("CALC_TYPE").toString());
											}
											if(calcType!=null && calcType.size()>0 && !"admin".equalsIgnoreCase(linkFrom) && calcType1[0]!=null){
												map.put("NEW_BASE_RATE", calcType1[0]);
												map.put("MINIMUM_PREMIUM", calcType1[2]);
											}
										}else if(map.get("CALC_TYPE")!=null && !map.get("CALC_TYPE").toString().equalsIgnoreCase("G")){
											subbaseratests.put(map.get("COVERAGES_SUB_ID").toString(), map.get("CALC_TYPE"));
										}
		
										boolean status=service.getFormulaCount(bean.getProductId(), bean.getSchemeId()+"", map.get("COVERAGES_SUB_ID").toString());
										System.out.println("formula status: =====>>>>>"+status);
										if(status)
										{
											sum=service.getFormulaSum(bean.getProductId(), bean.getSchemeId()+"", map.get("COVERAGES_SUB_ID").toString(), bean.getQuoteNo());
											if(sum!=null && sum.length()>0)forumulaSum.put((String)map.get("COVERAGES_SUB_ID"), sum);
											System.out.println("formula sum: =====>>>>>"+sum);
										}
										submainPremium.add(map);
									}
								}
								Set<String> hotkeys=baseSts.keySet();
								Iterator<String> en=hotkeys.iterator();
								while(en.hasNext())
								{
									String coverid=(String)en.next();
									String value="true";
									String calType=(String)baseratests.get(coverid);
									System.out.println("cover: "+coverid+" sum: "+value+" type: "+calType);
									System.out.println("Base Rate%: "+baserate.get(coverid));
									if(calType.equalsIgnoreCase("G"))
									{
										List<Map<String,Object>> rate=service.getBaseRate(coverid, "0", bean.getProductId(), bean.getSchemeId(), bean.getContentTypeId(), value);
										System.out.println("Rate: =====> "+" rate: "+rate.size());
										if(rate.size()>0){
											Map<String,Object> map= rate.get(0);
											baseratests.remove(coverid);
											baseratests.put(coverid, map.values().toArray()[1]);
											if(!"adminCalculate".equalsIgnoreCase(linkFrom) && !"adminProceedInfo".equalsIgnoreCase(linkFrom))
											{
												baserate.remove(coverid);
												baserate.put(coverid, map.values().toArray()[0]);
											}
										}
										System.out.println("calcType==>: "+baseratests.get(coverid));
									}
								}
		
								//block for subbase_rate
								Set<String> hotkeys1=subbaseids.keySet();
								Iterator<String> en1=hotkeys1.iterator();
								while(en1.hasNext())
								{
									String subcoverid=(String)en1.next();
									String value="true";
									String calType=(String)subbaseratests.get(subcoverid);
									System.out.println("cover: "+subcoverid+" sum: "+value+" type: "+calType);
									if(calType.equalsIgnoreCase("G"))
									{
										List<Map<String,Object>> rate= service.getBaseRate(subcoverid, "0", bean.getProductId(), bean.getSchemeId(), bean.getContentTypeId(), value);
										System.out.println("Rate: =====> "+" rate: "+rate.size());
										if(rate.size()>0){
											Map<String,Object> map = rate.get(0);
											subbaseratests.remove(subcoverid);
											subbaseratests.put(subcoverid, map.values().toArray()[1]);
											if(!"adminCalculate".equalsIgnoreCase(linkFrom) && !"adminProceedInfo".equalsIgnoreCase(linkFrom))
											{
												subbaserate.remove(subcoverid);
												subbaserate.put(subcoverid, map.values().toArray()[0]);
											}
										}
									}
								}
								
								base_premium = service.insertMasterPremiumInfo(bean,mainPremium);
								option_premium = service.insertOptionalPremiumInfo(bean,submainPremium);
								service.updateCalculatedSumIns(bean);
								
								}
							
								service.updRefRemarks(bean,bean.getValidCoverage());
								
								Map<String,Object> outputValues = service.getCalculatePremium(bean.getQuoteNo(),bean.getSchemeId(),bean.getLocationId());
								base_premium = outputValues.get("PVBASEOUT")==null?0:Double.valueOf(outputValues.get("PVBASEOUT").toString());
								option_premium = outputValues.get("PVOPTIONALOUT")==null?0:Double.valueOf(outputValues.get("PVOPTIONALOUT").toString());
								discount_premium = outputValues.get("PVOLINEDISCOUNT")==null?0:Double.valueOf(outputValues.get("PVOLINEDISCOUNT").toString());
						     	validationStatus = outputValues.get("PVVALIDSTATUS")==null?"":outputValues.get("PVVALIDSTATUS").toString();
						     	validationMessage = outputValues.get("PVVALIDERROR")==null?"":outputValues.get("PVVALIDERROR").toString();
							     	
									
						     	service.premiumCalcIndividualScheme(bean);
								
							mainCoverValues = service.getMainCoverageWithQuote(bean, linkFrom);
							subCoverValues = service.getSubCoverageWithQuote(bean, linkFrom);
	
							//block for sum_insured amount from formula
							Set<String> hotkeys2=baseSts.keySet();
							Iterator<String> en2=hotkeys2.iterator();
							while(en2.hasNext())
							{
								String coverid=(String)en2.next();
								boolean status=service.getFormulaCount(bean.getProductId(), bean.getSchemeId(), coverid);
								System.out.println("formula status: =====>>>>>"+status);
								if(status){
									sum=service.getFormulaSum(bean.getProductId(), bean.getSchemeId(), coverid, bean.getQuoteNo());
									if(sum!=null && sum.length()>0)forumulaSum.put(coverid, sum);
									System.out.println("formula sum: =====>>>>>"+sum);
								}
							}
							
							if("ProceedInfo".equalsIgnoreCase(linkFrom) || "adminProceedInfo".equalsIgnoreCase(linkFrom))
							{
								double temp_premium=0.0;
								String amount="";
								double totalPremium=0.0;
								if(mainCoverValues.size()>0){
									for(int i=0; i<mainCoverValues.size(); i++){
										Map<String,Object> map = mainCoverValues.get(i);
										if ("Y".equalsIgnoreCase(map.get("CALC_STATUS").toString())){
											if("textbox".equalsIgnoreCase(map.get("SUM_INSURED_CONTROL_TYPE").toString()) || "dropdown".equalsIgnoreCase(map.get("SUM_INSURED_CONTROL_TYPE").toString())){
												amount=map.get("COVERAGES_LIMIT")==null?"":map.get("COVERAGES_LIMIT").toString();
											}
											else
												amount=map.get("SUM_INSURED_LIMIT").toString();
	
											if(forumulaSum.size()>0 && forumulaSum.containsKey(map.get("COVERAGES_ID"))){
												amount=(String)forumulaSum.get(map.get("COVERAGES_ID"));
											}
											temp_premium=service.getPremium((String)baseratests.get(map.get("COVERAGES_ID")),Double.parseDouble(map.get("BASE_RATE") == null ? "0": map.get("BASE_RATE").toString()),amount == null ? "0" : amount,"1");
											totalPremium+=temp_premium;
										}
									}
									//LogManager.info("Total: "+totalPremium+"icon: "+icon.get("11"));
									service.updatePremium(totalPremium, referal, bean.getUserType(),bean.getQuoteNo(),bean.getProductId(),bean.getSchemeId(),bean.getContentTypeId(),bean.getMenuType(),bean.getBranchCode());
								}
							}
						}catch(Exception e){
							LogManager.debug(e);
						}
					}else
						service.updRefRemarks(bean,bean.getValidCoverage());
					
					service.editReferralRemarks(bean);
					service.updatePremiumHpm(bean);
					service.consolidatePremiumDetails(bean);
					if(bean.getValidCoverage()!=null && bean.getValidCoverage().length()>0 && (!"admin".equals(bean.getUserType()))){
						service.updOfsReferralRemarks(bean.getQuoteNo(),bean.getValidCoverage());
					}else if((!"admin".equals(bean.getUserType()))){
						bean.setPremiumList(service.getHomePremiumInfo(bean));
						bean.setSubPremium(service.subPremium(bean));
					}
					if(("admin".equalsIgnoreCase(bean.getUserType()) || "RSAIssuer".equals(bean.getUserType())) && "calculate".equals(bean.getReqFrom())){
						forward=modifySumIns();
					} else {
						forward = paCover();
						
					}
					int bcoverCount = service.getBaseCoverCnt(bean.getQuoteNo());
					if("Y".equals(validationStatus)) {
							addActionError(validationMessage);
					} else if(bcoverCount==0) {
							addActionError(getText("error.coverage.na"));
					}
					if(hasErrorValues()) {
						forward=modifySumIns();
					}
			}else{
				forward=modifySumIns();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return forward;
	}
	
	public List<Map<String,Object>> getDropDownSchemeList() {
		return service.getDropDownSchemeList(bean);
	}
	public List<Map<String,Object>> getDropDownLocationList() {
		return service.getSchemeLocationList(bean);
	}
	public String homeLocationList(){
		getRequest().setAttribute(FIELD, "homeLocationList");
		return DROPDOWN;
	}
	public String homeCoverageDetails(){
		getRequest().setAttribute(FIELD, "homeCoverageDetails");
		bean.setPremiumList(service.getHomePremiumInfoNew(bean));
		service.calcDiscountPremium(bean);
		service.setlocationPremiumValues(bean);
		return DROPDOWN;
	}
	
	/**
	 * Make Payment Start
	 */
	
	public String makePayment(){
		String returnz  = "paymentPage";
		try{
			getValidate("paymentDetails");
			if(!hasActionErrors()){
				service.consolidatePremiumDetails(bean);
				bean.setMerchant_reference(
						new PaymentDAO().insertPaymentDetails(
							bean.getModeOfPayment(), bean.getChequeNo(), bean.getChequeDate(), bean.getChequeAmount(),
							bean.getFinalPremium(),bean.getBankName(), bean.getMicrCode(), bean.getCashDepositBank(),
							bean.getCashAmount(), bean.getCashChellanNo(), bean.getCashInstrumentDate(), bean.getApplicationNo(), bean.getQuoteNo(),
							bean.getProductId(), bean.getMerchant_reference(), bean.getEmail(), bean.getCustomerName(), bean.getBranchCode(),
							bean.getCurrencyType(), bean.getInstallmentYN(), bean.getMobileNo(), bean.getDeviceType(), bean.getMtnMobileNo()
						)
					);
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
											returnz = "mtnWaitingPage";
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
				}else if(DBConstants.PAYMENT_CHEQUE.equalsIgnoreCase(bean.getModeOfPayment())
						|| DBConstants.PAYMENT_CASH.equalsIgnoreCase(bean.getModeOfPayment())
						|| DBConstants.CREDIT_NOTE.equalsIgnoreCase(bean.getModeOfPayment())){
					
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
									returnz = "policyInfoSuccess";
								}
						 }else{
							 addActionError("Error on Policy Creation");
						 }
					}else if("P".equalsIgnoreCase(fromPosition.get("STATUS").toString())){
						returnz = "policyInfo";
					}
				}else {
					returnz = "onlinePaymentReq";
				}
			}
		}catch(Exception e){
			addActionError("Something Went Wrong");
			LogManager.info("Exception @ HomeAction.makePayment() Error: " +e);
			e.printStackTrace();
		}finally{
			if(hasActionErrors()){
				getEditQuoteDetails();
				service.setCustDetail(bean);
				service.consolidatePremiumDetails(bean);
				return "paymentPage";
			}
		}
		return returnz;
	}
	
	/**
	 * Make Payment End
	 */
	
	/**
	 * Policy Details Start
	 */
	
	public String policyDtls(){
		try{
			service.editQuoteDetails(bean);
		}catch(Exception e){
			addActionError("Something Went Wrong");
			LogManager.info("Exception @ HomeAction.policyDtls() Error: " +e);
			e.printStackTrace();
		}
		return "policyInfo";
	}
	
	/**
	 * Policy Details End
	 */
	
	public String editScheme(){
		try{
			bean.setSchemeId(bean.getDropDownScheme());
			bean.setLocationSize(service.getLocationSizeDetails(bean,"scheme"));
			if("1".equalsIgnoreCase(bean.getLocationSize()) && StringUtils.isNotBlank(bean.getQuoteNo())){
				List<Map<String, Object>> locationId=service.getSelectedSchemeLocation(bean);
				if(locationId!=null && locationId.size()>0){
					bean.setLocationId(locationId.get(0).get("LOCATION_ID")==null?"":locationId.get(0).get("LOCATION_ID").toString());
					bean.setSingleLocName(locationId.get(0).get("LOCATION_NAME")==null?"":locationId.get(0).get("LOCATION_NAME").toString());	
				}
			}
			getSession().put("ContentType", "0");
			bean.setHomeDesc(service.getHomeInfoDesc(bean));
			bean.setHome(service.getHomeInfo(bean));
			bean.setSubhome(service.getSubHomeInfo(bean));
			service.setSumInsuredDetails(bean);
		}catch(Exception e){
			LogManager.info("Exception @ HomeAction.editScheme() Error: "+e); 
			e.printStackTrace();
		}finally{
			bean.setEditFrom("sirEdit");
			bean.setModifyYN("Y");
		}
		return INPUT;
	}
	
	public String payResponse() { 
	 	try{
	 		setPayEnctData(bean);
	 		//service.generatePolicy(bean);
	 		service.getQuoteInfoDtl(bean);
		}catch (Exception e) {
			e.printStackTrace();
		}
	 	if("Y".equalsIgnoreCase(bean.getQuotationStatus()) || StringUtils.isBlank(bean.getQuotationStatus())) {
	 		return extraInfo();
	 	}
		return "policyInfo";
		
	}
	
	public void setPayEnctData(HomeBean bean) throws Exception {
		String decrypt = CryptoService.decrypt(bean.getE());
		System.out.println("Home decrypt value => "+decrypt);
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
	
}
