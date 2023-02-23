package com.maan.adminnew.userManagement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.maan.adminnew.common.CommonService;
import com.maan.common.LogManager;
import com.maan.common.Validation;
import com.maan.common.dao.CommonDAO;
import com.maan.common.login.LogInAction;
import com.maan.common.password.passwordEnc;
import com.maan.common.sms.CryptoService;
import com.maan.common.sms.GoogleAPIClient;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class UserMgtAction extends ActionSupport implements
		ModelDriven<UserMgtBean> {
	private static final long serialVersionUID = 1L;
	private UserMgtBean bean = new UserMgtBean();
	private CommonService cservice = new CommonService();
	private GoogleAPIClient api=new GoogleAPIClient();
	Validation validation = new Validation();
	UserMgtService service = new UserMgtService();
	Map<String, Object> session = ActionContext.getContext().getSession();
	String branchCode = (String) session.get("BranchCode");
	String login_id = (String) session.get("user");
	private List<Object> userList;
	private List<Object> userInfo;
	private List<Object> commisionDetails = new ArrayList<Object>();
	private List<Map<String, String>> productList;
	private List<Object> productData;
	private List<Object> occList;
	String appId = (String) session.get("AppId");
	passwordEnc pass = new passwordEnc();

	public List<Object> getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(List<Object> userInfo) {
		this.userInfo = userInfo;
	}

	public List<Object> getCommisionDetails() {
		return commisionDetails;
	}

	public void setCommisionDetails(List<Object> commisionDetails) {
		this.commisionDetails = commisionDetails;
	}

	public List<Object> getUserList() {
		return userList;
	}

	public void setUserList(List<Object> userList) {
		this.userList = userList;
	}

	public List<Object> getProductData() {
		return productData;
	}

	public void setProductData(List<Object> productData) {
		this.productData = productData;
	}

	public List<Object> getOccList() {
		return occList;
	}

	public void setOccList(List<Object> occList) {
		this.occList = occList;
	}

	public List<Map<String, String>> getProductList() {
		return productList;
	}

	public void setProductList(List<Map<String, String>> productList) {
		this.productList = productList;
	}

	public UserMgtBean getModel() {
		return bean;
	}

	public List<Object> getCountriesInfo() {
		return cservice.getCountries(branchCode);
	}

	public List<Object> getEmiratesInfo() {
		return cservice.getEmirates(branchCode);
	}

	public List<Object> getBrokerList() {
		return cservice.getAdminBrokerList(branchCode, appId);
	}

	public List<Object> getNationalitiesInfo() {
		return cservice.getNationalities();
	}

	public List<Object> getTitlesInfo() {
		return cservice.getTitles(branchCode);
	}

	public List<Map<String, String>> getProductDet() {
		return cservice.getProductsDET(branchCode, "");
	}

	public String getOCCertificate() {
		occList = service.getOCCertificate(bean.getAgencyCode());
		return "openCover";
	}

	public String getABList() {
		LogManager.push("ENTER-->Method to getABList");
		LogManager.info("getABList() - Exit");
		userList = service.getAdminUserList(bean, bean.getAgencyCode(), bean
				.getMode1(), branchCode);
		return "userList";
	}

	public String view() {
		LogManager.push("Method to view");
		userInfo = service.getUserDetails(bean, bean.getUagencyCode());
		// commisionDetails=service.getCommisionData(bean.getUagencyCode(),
		// bean.getAgencyCode(), branchCode);
		LogManager.info("view() - Exit");
		return "view";
	}

	public String edit() {
		LogManager.push("Method to edit");
		try {
			if (!"new".equals(bean.getMode())) {
				userInfo = service.getUserDetails(bean, bean.getUagencyCode());
				commisionDetails = service.getCommisionData(bean
						.getUagencyCode(), bean.getAgencyCode(), branchCode);
				// commisionDetailsUser=service.getCommisionData(bean.getAgencyCode());
			}
			bean.setBranchList(cservice.getUSerBranchList(bean.getAgencyCode()));
			LogManager.info("edit() - Exit");
		} catch (Exception e) {
			LogManager.info(e);
			e.printStackTrace();
		}
		return "edit";
	}
	
	public String userBranchAjax(){
		bean.setBranchList(cservice.getUSerBranchList(bean.getAgencyCode()));
		return "adminAjax";
	}

	public String newUser() {
		LogManager.push("Enter==>newUser()");
		bean.setBranchList(cservice.getUSerBranchList(bean.getAgencyCode()));
		String bid="";
		if(bean.getBranchId()!=null && bean.getBranchId().length>0){
			for(String str: bean.getBranchId())
				bid=bid+","+str;
			bid=bid.substring(1);
		}

		bean.setUbranch(bid);
		
		validatenewUser();
		if (getActionErrors().isEmpty()) {
			try {
				bean.setIssuerType("Y".equalsIgnoreCase(bean.getIsb2c())?"90011":"88888");
				
				if ("new".equalsIgnoreCase(bean.getMode())) {
					String customerId = new CommonDAO().getSequenceNo(
							"CUSTOMER_ID", (String) session.get("product_id"),
							branchCode, "", "");
					bean.setUagencyCode(cservice.getUserCode(branchCode));
					if (bean.getAgencyCode() == null
							|| "".equals(bean.getAgencyCode()))
						bean.setAgencyCode(bean.getBroker());
					
					Object[] args = { customerId, appId, bean.getUtitle(),
							bean.getUfirstname(), bean.getUlastname(), "1",
							bean.getUnationality(), bean.getUdob(),
							bean.getUgender() == null ? "" : bean.getUgender(),
							bean.getUphone(), bean.getUmobile(),
							bean.getUfax(), bean.getUemail(),
							bean.getUaddress1(), bean.getUaddress2(),
							bean.getUoccupation(), bean.getUpobox(),
							bean.getUcountry(), bean.getUcity(), "Y",
							bean.getUserId(), bean.getUagencyCode(),
							bean.getAgencyCode(), bean.getUcity(), "" };
					String args1[] = {
							bean.getUserId(),
							pass.crypt(bean.getPassword()), bean.getUtype(),
							bean.getUfirstname(), "1", bean.getUagencyCode(),
							bean.getAgencyCode(), "BOTH", "", "", "", "", "1",
							bean.getAgencyCode(), "Y", "N", "N", "Y",
							bean.getUemail(), branchCode, bean.getUcountry(),bean.getUmobile(),bean.getUbranch(),bean.getSubBranchId(),
							bean.getIsb2c()};
					/*Object[] args = { customerId, appId, bean.getUtitle(),
							bean.getUfirstname(), bean.getUlastname(), "1",
							bean.getUnationality(), bean.getUdob(),
							bean.getUgender() == null ? "" : bean.getUgender(),
							bean.getUphone(), bean.getUmobile(),
							bean.getUfax(), bean.getUemail(),
							bean.getUaddress1(), bean.getUaddress2(),
							bean.getUoccupation(), bean.getUpobox(),
							bean.getUcountry(), bean.getUcity(), "Y",
							bean.getUmobile(), bean.getUagencyCode(),
							bean.getAgencyCode(), bean.getUcity(), "" };
					String args1[] = {
							bean.getUmobile(),
							pass.crypt("Admin@01"), bean.getUtype(),
							bean.getUfirstname(), "1", bean.getUagencyCode(),
							bean.getAgencyCode(), "BOTH", "", "", "", "", "1",
							bean.getAgencyCode(), "Y", "N", "N", "Y",
							bean.getUemail(), branchCode, bean.getUcountry(),bean.getUmobile()};*/

					service.insertUserInfo(args, args1);
					bean.setDisplay("successNew");
					bean.setIndex("0");
				} else {
					// bean.setUagencyCode(cservice.getUserCode(branchCode));
					Object[] args = { bean.getUtitle(), bean.getUfirstname(),
							bean.getUlastname(), bean.getUnationality(),
							bean.getUdob(),
							bean.getUgender() == null ? "" : bean.getUgender(),
							bean.getUphone(), bean.getUmobile(),
							bean.getUfax(), bean.getUemail(),
							bean.getUaddress1(), bean.getUaddress2(),
							bean.getUoccupation(), bean.getUpobox(),
							bean.getUcountry(), bean.getUcity(),
							bean.getUstatus(), bean.getUcity(),
							bean.getUagencyCode() };

					String args1[] = { bean.getUfirstname(), bean.getUstatus(),
							bean.getUemail(), bean.getUbranch(),bean.getSubBranchId(),bean.getIsb2c(), bean.getUagencyCode() };
					service.updateUserInfo(args, args1);
					
					service.updateLoginUserDtl(bean);
					
					bean.setDisplay("successUpdate");
					bean.setIndex("0");
				}
				String urlString="";
				if("Y".equalsIgnoreCase(bean.getIsb2c())){
					if(service.checkShortUrlNotExist(bean)){
						urlString=CryptoService.encrypt("agencyCode="+bean.getUagencyCode());
			    		String shorternURL = api.getShorternURL(urlString);
			    		String shortUrl=shorternURL.replace("click here", "").trim();
			    		service.updateShortUrl(bean,shortUrl);
					}
				}else{
					service.updateShortUrl(bean,"");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		commisionDetails = service.getCommisionData(bean.getUagencyCode(), bean
				.getAgencyCode(), branchCode);
		return "edit";
	}

	public String getUserAjax() {
		if ("userLists".equals(bean.getReqFrom())) {
			userList = service.getUserListAjax(bean.getUagencyCode(), bean
					.getSearchBy(), bean.getSearchValue(), bean.getMode1());
		} else if ("userproduct".equals(bean.getReqFrom())) {
			commisionDetails = service.getCommisionData(bean.getUagencyCode(),
					bean.getAgencyCode(), branchCode);
			productList = cservice.getProductsDET(branchCode, bean
					.getAgencyCode());
		}
		return "adminAjax";
	}

	public String checkPwd() {
		LogManager.push("Method to checkPwd()");
		validatePassword();
		try {
			if (getActionErrors().isEmpty()) {
				bean.setPassword(pass.crypt(bean.getPassword()));
				String args[] = { bean.getPassword(), bean.getUagencyCode() };
				cservice.checkPassword(args);
				bean.setDisplay("passwordsuccess");
			} else
				bean.setMode1("login");
		} catch (Exception e) {
			e.printStackTrace();
		}
		userInfo = service.getUserDetails(bean, bean.getUagencyCode());
		commisionDetails = service.getCommisionData(bean.getUagencyCode(), bean
				.getAgencyCode(), branchCode);
		bean.setIndex("2");
		return "edit";
	}

	public String addProduct() {
		LogManager.push("ENTER===> addProduct");
		String returnResult = "edit";
		try {
			bean.setBranchList(cservice.getUSerBranchList(bean.getAgencyCode()));
			validateProducts();
			userInfo = service.getUserDetails(bean, bean.getUagencyCode());
			if (!hasActionErrors()) {
				for (int i = 0; i < commisionDetails.size(); i++) {

					Map map = (Map) commisionDetails.get(i);
					if ("Y".equalsIgnoreCase(map.get("product").toString())) {
						int count = cservice
								.checkExistProduct(map.get("uproductId")
										.toString(), bean.getUagencyCode());
						String ocover = "";
						if ("11".equalsIgnoreCase(map.get("uproductId")
								.toString()))
							ocover = bean.getOpenCover();
						if (count == 0) {
							Object[] info = { cservice.getMaxUserId(),
									bean.getUfirstname(),
									bean.getUagencyCode(),
									bean.getUagencyCode(),
									bean.getAgencyCode(),
									map.get("uproductId"), "1", "", "",
									map.get("insEndLimit"), "", "0", "1", "",
									"", "", "Y", bean.getUserId(), "", "", "",
									"", "", "", "", map.get("freight"),
									map.get("receipt"), "", ocover,
									map.get("specialDis"),
									"30".equals(bean.getProductID()) ? "7" : "" ,
									bean.getIssuerType(),bean.getIsb2c()};

							for (Object k : info) {
								LogManager.info("info===>" + k);
							}
							Object obj[] = null;
							cservice.insertCommission(info, obj);
							bean.setDisplay("newsuccess");
						} else {
							Object[] args = { "", map.get("insEndLimit"), "Y",
									"", "", "", "", map.get("receipt"),
									map.get("freight"), "", map.get("receipt"),
									ocover, map.get("specialDis"),bean.getUagencyCode(),bean.getIssuerType(),bean.getIsb2c(),
									bean.getUagencyCode(),
									map.get("uproductId") };
							for (Object k : args) {
								LogManager.info("args===>" + k);
							}
							Object obj[] = null;
							cservice.updateCommissionMotor(args, obj);
							bean.setDisplay("updatesuccess");
						}
					}
				}
			} else {
				bean.setMode1("product");
				commisionDetails = service.getCommisionData(bean
						.getUagencyCode(), bean.getAgencyCode(), branchCode);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		bean.setIndex("1");
		return returnResult;
	}

	public void validatePassword() {
		if (StringUtils.isEmpty(bean.getPassword()))
			addActionError(getText("error.broker.newpassword"));
		else if (StringUtils.contains(bean.getPassword(), " "))
			addActionError("Password should not contain white spaces");
		if (StringUtils.isEmpty(bean.getRepassword()))
			addActionError(getText("error.broker.repassword"));
		else if (!bean.getPassword().equals(bean.getRepassword()))
			addActionError(getText("error.different"));
	}

	public void validateProducts() {
		int count = 0;
		List<Object> commisionDetail = new ArrayList<Object>();
		for (int i = 0; i < bean.getProduct().size(); i++) {
			if ("true".equals(bean.getProduct().get(i))) {
				if (!"11".equals(bean.getUproductId().get(i))) {
					if (StringUtils.isEmpty(bean.getSpecialDis().get(i))) {
						addActionError(getText("error.product.specialdis")
								+ " " + bean.getUproductName().get(i));
					} else if (!StringUtils.isNumeric(bean.getSpecialDis().get(
							i))) {
						addActionError(getText("error.product.specialdis.valid")
								+ " " + bean.getUproductName().get(i));
					}
					if (StringUtils.isEmpty(bean.getInsEndLimit().get(i))) {
						addActionError(getText("error.product.insEndLimit")
								+ " " + bean.getUproductName().get(i));
					} else if (!StringUtils.isNumeric(bean.getInsEndLimit()
							.get(i))) {
						addActionError(getText("error.product.insEndLimit.valid")
								+ " " + bean.getUproductName().get(i));
					}
				}
				count++;
			}
			Map<String, String> map = new HashMap<String, String>();
			// map.put("product",bean.getProduct().get(i)=="true"?"Y":"N");
			if ("true".equalsIgnoreCase(bean.getProduct().get(i)))
				map.put("product", "Y");
			else
				map.put("product", "N");

			map.put("uproductId", bean.getUproductId().get(i) == null ? ""
					: bean.getUproductId().get(i));
			map.put("uproductName", bean.getUproductName().get(i) == null ? ""
					: bean.getUproductName().get(i));
			map.put("specialDis", bean.getSpecialDis().get(i) == null ? ""
					: bean.getSpecialDis().get(i));
			map.put("insEndLimit", bean.getInsEndLimit().get(i) == null ? ""
					: bean.getInsEndLimit().get(i));
			map.put("receipt", bean.getReceipt() == null ? "N" : bean
					.getReceipt().get(i));
			map.put("freight", bean.getFreight() == null ? "N" : bean
					.getFreight().get(i));
			if ("11".equals(bean.getUproductId().get(i)))
				map.put("open_cover_no", bean.getOpenCover());
			else
				map.put("open_cover_no", "");
			commisionDetail.add(map);
		}
		if (count == 0) {
			addActionError(getText("error.product.select"));
		}
		this.commisionDetails = commisionDetail;
	}

	public void validatenewUser(){
    	if("edit".equals(bean.getUstatus())){
	    	if(StringUtils.isEmpty(bean.getUstatus())){
	    		addActionError(getText("error.quotation.status"));
	    	}
    	}if("new".equals(bean.getUstatus())){
    		if(StringUtils.isEmpty(bean.getUtype())){
    			addActionError(getText("error.user.type"));
    		}
    	}if(StringUtils.isEmpty(bean.getUfirstname())){
    		addActionError(getText("error.quotation.firstname"));
    	}else if(StringUtils.isNumeric(bean.getUfirstname())){
    		addActionError(getText("error.quotation.firstname.valid"));
    	}if(StringUtils.isEmpty(bean.getUnationality())){
    		addActionError(getText("error.quotation.nationality"));
    	}if(StringUtils.isEmpty(bean.getUcity())){
    		addActionError(getText("error.quotation.emirate"));
    	}if(StringUtils.isEmpty(bean.getUcountry())){
    		addActionError("error.select.country");
    	}if(StringUtils.isNotBlank(bean.getUpobox()) &&!StringUtils.isNumeric(bean.getUpobox())){
    		addActionError(getText("error.quotation.pobox.valid"));
    	}if(!StringUtils.isBlank(bean.getUphone()) && !StringUtils.isNumeric(bean.getUphone()))
    		addActionError(getText("error.broker.phone"));
    	if(StringUtils.isBlank(bean.getUmobile()))
    		addActionError(getText("Please Enter Mobile Number"));
    	else if(Validation.INVALID.equals(validation.validateMobile(bean.getUmobile()))) {
			addActionError("Please Enter Valid Mobile No");
		}
    	if(!StringUtils.isBlank(bean.getUmobile()) && !StringUtils.isNumeric(bean.getUmobile()))
    		addActionError(getText("error.broker.mobile"));
    	if(StringUtils.isEmpty(bean.getUemail()))
    		addActionError(getText("error.quotation.email"));
    	else if(StringUtils.contains(bean.getUemail(), " "))
	 		addActionError(getText("error.email.contains.white.space"));
    	else if("invalid".equalsIgnoreCase(validation.emailValidate(bean.getUemail()))){
    		addActionError(getText("error.quotation.valid.bemail"));
    	}
    	if(StringUtils.isBlank(bean.getUbranch()))
    		addActionError(getText("error.attached.branch"));
    	if("new".equals(bean.getMode())){
    		if(StringUtils.isEmpty(bean.getUserId()))
        		addActionError(getText("error.broker.loginid"));
    		else if(StringUtils.contains(bean.getUserId(), " "))
    	 		addActionError("Login Id should not contain white spaces");
        	if(StringUtils.isEmpty(bean.getPassword()))
        		addActionError(getText("error.broker.newpassword"));
        	else if(StringUtils.contains(bean.getPassword(), " "))
    	 		addActionError("Password should not contain white spaces");
        	if(StringUtils.isEmpty(bean.getRepassword())){
        		addActionError(getText("error.broker.repassword"));
        	}else if(!bean.getPassword().equals(bean.getRepassword())){
        		addActionError(getText("error.different"));
        	}else if(!new LogInAction().validPassword(bean.getPassword())){
        		addActionError(getText("error.invalid"));
        	}else if(cservice.getAdminInfo(bean.getUserId()).size()>0){
        		addActionError(getText("error.loginid.notavailable"));
        	}
    	}
    }
	/*public List<Map<String,Object>> getBranchList(){
		return cservice.getBranchList();
	}*/
	
	public List<Map<String,Object>> getSubBranchList(){
		return cservice.getSubBranchList(bean.getBranchId());
	}
    
    public String subBranch(){
    	return "subBranchList";
    }
}