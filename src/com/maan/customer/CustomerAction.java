package com.maan.customer;

import java.io.ByteArrayInputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.maan.common.DBConstants;
import com.maan.common.LogManager;
import com.maan.common.Validation;
import com.maan.customer.service.CustomerService;
import com.maan.services.util.ValidationFormat;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.util.LocalizedTextUtil;

public class CustomerAction extends ActionSupport implements ModelDriven<CustomerBean>{
	private CustomerBean cusBean=new CustomerBean(); 
	HttpServletRequest request=ServletActionContext.getRequest();
	Map<String, Object> session=ActionContext.getContext().getSession();
	CustomerService service=new CustomerService();
	private static final long serialVersionUID = 1L;
	private static final String DROPDOWN = "dropdown";
	private static final String STREAM = "stream";
	private static final String FIELD = "ELEMENT_NAME";
	private String branchCode=(String)session.get("LoginBranchCode");
	private String productId=(String) session.get("product_id");
	private String actualBranch=(String)session.get("adminBranch");
	private String brokerCode=(String) session.get("AgencyCode");
	private String loginId=(String)session.get("user");
	private String issuer=(String)session.get("RSAISSUER");
	private String userType=(String)session.get("usertype");
	private String user=(String)session.get("user1");
	private String selectedBranch=(String)session.get("selectedBranch");
		
	public Object[] getParams()
	{
	Object[] objects=new String[]{cusBean.getOption(),productId,actualBranch,"","","",cusBean.getOriginCountry(),
			cusBean.getDestCountry(),"","","",loginId,cusBean.getBrokerCode()};
		return objects;
	}
	
	public List<Object> getTitleList()
	{
		return new com.maan.common.dao.CommonDAO().getOptionsList("title", getParams());
	}
	
	public List<Object> getCityList()
	{
		return new com.maan.common.dao.CommonDAO().getOptionsList("city", getParams());
	}
	public List<Object> getCountryList()
	{
		return new com.maan.common.dao.CommonDAO().getOptionsList("country", getParams());
	}
	public List<Object> getNationalityList()
	{																			 
		return new com.maan.common.dao.CommonDAO().getOptionsList("nationality", getParams());
	}
	public List<Object> getOccupationList()
	{
		return new com.maan.common.dao.CommonDAO().getOptionsList("occupation", getParams());
	}
	public String getDetail()
	{
		if(session.get("b2c")!=null&&"b2c".equalsIgnoreCase(session.get("b2c").toString())){
			session.put("product_id",cusBean.getProductId());
			productId=cusBean.getProductId();
		}
		cusBean.setDisplay("getDetail");
		return INPUT;
	}
	public String getSearch()
	{
		getValidate("SearchCustomer");
		if(!StringUtils.isBlank(cusBean.getDob()))
			cusBean.setDob(dateFormat(cusBean.getDob()));
		if(getActionErrors().isEmpty())
		{
			String result=service.getSearch(cusBean);
			if(!"SUCCESS".equalsIgnoreCase(result))
				addActionError(getText("error.customer.search.valid"));
		}
		
		cusBean.setDisplay("getDetail");
		return INPUT;
	}
	public String getUpdate()
	{
			String forward=INPUT;
			getValidate("CustomerInfo");
			if(getActionErrors().isEmpty())
			{
				if(StringUtils.isEmpty(cusBean.getCustomerIdYN())||!"Y".equalsIgnoreCase(cusBean.getCustomerIdYN())){
					String result=service.getUpdate(cusBean);
					if("SUCCESS".equalsIgnoreCase(result))
					{
						if("Save".equalsIgnoreCase(cusBean.getActionType())){
							//addActionMessage(getText("error.customer.save"));
							session.put("MSG", getText("error.customer.save"));
							forward="initReport";
						}else if("31".equalsIgnoreCase(cusBean.getProductId())||"32".equalsIgnoreCase(cusBean.getProductId())||"33".equalsIgnoreCase(cusBean.getProductId())||"34".equalsIgnoreCase(cusBean.getProductId())){
							forward="redirectTravel";
						}else if("41".equalsIgnoreCase(cusBean.getProductId())){
							forward="redirectHealth";
						}else if("30".equalsIgnoreCase(cusBean.getProductId())){
							forward="redirectHome";
						}
					}
					else
						addActionError(getText("error.customer.update"));
					}else if("Submit".equalsIgnoreCase(cusBean.getActionType()))
					{
						cusBean.setDisplay("getQuote");
						service.getSearch(cusBean);
						cusBean.setDisplay("getDetail");
					}else if("31".equalsIgnoreCase(cusBean.getProductId())||"32".equalsIgnoreCase(cusBean.getProductId())||"33".equalsIgnoreCase(cusBean.getProductId())){
						forward="redirectTravel";
					}else if("41".equalsIgnoreCase(cusBean.getProductId())){
						forward="redirectHealth";
					}else if("30".equalsIgnoreCase(cusBean.getProductId())){
						forward="redirectHome";
					}
			}
		return forward;
	}
	public String edit()
	{
		service.getSearch(cusBean);
		cusBean.setDisplay("getDetail");
		return INPUT;
	}
	public List<Object> getExecutiveList()
	{
		return  new com.maan.common.dao.CommonDAO().getOptionsList("executive", getParams());
	}
	public String executiveList()
	{
		request.setAttribute(FIELD, "executive"); 
		return DROPDOWN;
	}
	public List<Object> getCustomerSelection() {
		if(StringUtils.isNotEmpty(cusBean.getBrokerName())){		
			return new com.maan.common.dao.CommonDAO().getCustomerSelectionList(cusBean.getBrokerName(),cusBean.getSearchBy(),cusBean.getSearchValue(),(String)session.get("openCoverNo"));
		}else{
			return new com.maan.common.dao.CommonDAO().getCustomerSelectionList(loginId,cusBean.getSearchBy(),cusBean.getSearchValue(),(String)session.get("openCoverNo"));
		}
	}
	public String customerSelection()
	{
		LogManager.info("brokerCode==>"+cusBean.getBrokerCode());		
		if(StringUtils.isNotEmpty(cusBean.getBrokerCode())){
			cusBean.setBrokerName(new com.maan.common.dao.CommonDAO().getBrokerLoginId(cusBean.getBrokerCode()));
		}	
		if(cusBean.getSearchValue()==null)
		{
			return "customerSelection";
		}
		else{
			request.setAttribute(FIELD, "customerList");
			return DROPDOWN;
		}	
	}
	public String customerPopulate()
	{
		String value="<script type='text/javascript'>window.close();</script>";
		byte[] byteArray = value.getBytes();
		cusBean.setInputStream(new ByteArrayInputStream(byteArray));
		return STREAM;
	}
	public String coreCustSearch()
	{
		return "coreCustomer";
	}
	public void getValidate(String type)
	{
		LinkedList<String> list=service.getValidate(cusBean,type);
		for (String st : list)
			addActionError(getText(st));
	}
	public CustomerBean getModel() {
		cusBean.setLoginId(loginId);
		cusBean.setBranchCode(branchCode);
		cusBean.setProductId(productId);
		cusBean.setDestCountry("1");
		cusBean.setOriginCountry("1");
		cusBean.setUserType(userType);
		cusBean.setIssuer((String)session.get("RSAISSUER"));
		//cusBean.setUser(user);
		return cusBean;
	}
	private String dateFormat(String name){
		String arr[]=name.split("/");
		return arr[1]+"/"+arr[0]+"/"+arr[2];
	}
	public List<Object> getBrokerList() {
		return  new com.maan.common.dao.CommonDAO().getOptionsList("broker",new Object[]{actualBranch,issuer,selectedBranch,productId});
	}
	
	public String editQuoteCustData() {
		try {
			if(StringUtils.isNotEmpty(cusBean.getProductId())){
				session.put("product_id", cusBean.getProductId());
			}
			//bean.setLoginId((String)getSession().get("user"));
			String userId=loginId;
			customerList(userId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "custQuote";
	}
	
	public String newQuoteIssuer(){
		//bean.setLoginId((String)getSession().get("user"));
		return "custQuote";
	}
	
	public String issuerCustList() {
		try {
			if(StringUtils.isBlank(cusBean.getBrokerCode())){
				addActionError("Please Select Broker Name");
			}
			if(StringUtils.isBlank(cusBean.getExecutive())){
				addActionError("Please Select Executive BDM");
			}
			if(!hasActionErrors()){
				//bean.setLoginId(bean.getBrokerCode());
				String userId=cusBean.getBrokerCode();
				cusBean.setMode("custList");
				customerList(userId);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "custQuote";
	}
	public void customerList(String userId){
		try {
			if(StringUtils.isBlank(cusBean.getDisplay()))
				cusBean.setDisplay("individual");
			if("individual".equalsIgnoreCase(cusBean.getDisplay()))
				cusBean.setCustomerList(service.getCustomerList((cusBean.getIssuer()!=null || StringUtils.isNotBlank(cusBean.getIssuer()))?"custList":"", "INDIVIDUAL",userId,brokerCode));
			if("corporate".equalsIgnoreCase(cusBean.getDisplay()))
				cusBean.setCustomerList(service.getCustomerList((cusBean.getIssuer()!=null || StringUtils.isNotBlank(cusBean.getIssuer()))?"custList":"", "CORPORATE",userId,brokerCode));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public String editCustInfo(){
		try {
			request.setAttribute(DBConstants.FIELD, "custEditAjax");
			service.customerDetails(cusBean);
	    }
	    catch(Exception e) {
	        e.printStackTrace();
	    }
	    return DBConstants.DROPDOWN;
    }
	public String updateCustDetailAjax (){
		request.setAttribute(DBConstants.FIELD, "custEditAjax");
		try {
			getValidate("customerDetails");
			if(!hasActionErrors()){
				String custId=new CustomerService().insertCustomerDetails(cusBean.getCustomerId(),cusBean.getLoginId(),cusBean.getBranchCode(),cusBean.getProductId(),cusBean.getTitle(),cusBean.getCustomerName(),cusBean.getMobileNo(),cusBean.getEmail(),cusBean.getAddress1(),cusBean.getAddress2(),cusBean.getPoBox(),cusBean.getCity(),cusBean.getCoreAppCode(),cusBean.getClientCustomerId(),cusBean.getCustArNo(),cusBean.getCustLastName(),cusBean.getCustPassportNo(),cusBean.getCustdob(),cusBean.getCustAlterMobileNo(),cusBean.getCustLandLineNo(),cusBean.getCustomerType(),cusBean.getCompanyRegNo(),cusBean.getCustNameArabic(),cusBean.getCustdobar(),cusBean.getGender(),cusBean.getOccupation(),"detailQuote",cusBean.getCustnrc1(),cusBean.getCustnrc2(),cusBean.getCustnrc3(),"");
				addActionMessage("Successfully Updated");
			}
		}catch(Exception e) {
	        e.printStackTrace();
	    }
	    return DBConstants.DROPDOWN;
		 
	}
	public String custSearch()
	{
		return "custSearch";
	}
	public String search() {
		searchvalidate();
		if(!hasActionErrors()) {
			cusBean.setSearchCustList(service.getSearchCustomerList(cusBean));
		}
		
		return "custSearch";
	}
	public void searchvalidate() {
		if(StringUtils.isBlank(cusBean.getSearchBy())) {
			addActionError("Please Select Search By");
		}else {
			if("nrcmobile".equalsIgnoreCase(cusBean.getSearchBy())) {
				if(StringUtils.isBlank(cusBean.getSearchnrc())) {
					addActionError("Please Enter NRC");
				}if(StringUtils.isBlank(cusBean.getSearchmobileNo())) {
					addActionError("Please Enter Mobile No");
				}
			}else if("mobileemail".equalsIgnoreCase(cusBean.getSearchBy())) {
				if(StringUtils.isBlank(cusBean.getSearchnrc())) {
					addActionError("Please Enter Passport No");
				}if(StringUtils.isBlank(cusBean.getSearchmobileNo())) {
					addActionError("Please Enter Mobile No");
				}
			}
		}
	}
}
