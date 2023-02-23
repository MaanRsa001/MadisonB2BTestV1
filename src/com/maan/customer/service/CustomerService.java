package com.maan.customer.service;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.maan.Motor.controller.MotorBean;
import com.maan.common.LogManager;
import com.maan.common.Validation;
import com.maan.common.dao.CommonDAO;
import com.maan.customer.CustBean;
import com.maan.customer.CustomerBean;
import com.maan.customer.dao.CustomerDAO;
import com.maan.dao.ApiCaller.ApiForCustomer;
import com.maan.services.util.ValidationFormat;

public class CustomerService{
	CustomerDAO cusDAO=new CustomerDAO();
	ApiForCustomer customerApi = new ApiForCustomer();
	public String getSearch(CustomerBean cusBean) {
		return cusDAO.getSearch(cusBean);
	}
	public String getUpdate(CustomerBean cusBean) {
		return cusDAO.getUpdate(cusBean);
	}
	/*public String getUpdateCustomerDtlMotor(String customerId,String loginId,String branchCode,String productId,String title,String customerName,String mobileNo,String customerType,String email,String custNameArabic){
		return cusDAO.getUpdateCustomerDtlMotor(customerId, loginId, branchCode, productId, title, customerName, mobileNo, customerType, email,custNameArabic);	
	}
	public String getUpdateCustomerDtlMotor(String customerId,String loginId,String branchCode,String productId,String title,String customerName,String mobileNo,String email,String address1,String address2,String poBox,String city,String custAppCode,String clientCustomerId,String custArNo, String custLastName, String custnrc, String custPassportNo, String custdob, String custAlterMobileNo, String custLandLineNo, String customerType, String companyRegNo,String custNameArabic, String custdobar, String gender, String occupation,String applicationId) {
		return cusDAO.getUpdateCustomerDtlMotor(customerId, loginId, branchCode, productId, title, customerName, mobileNo, email, address1, address2, poBox, city, custAppCode, clientCustomerId, custArNo, custLastName, custnrc, custPassportNo, custdob, custAlterMobileNo, custLandLineNo, customerType, companyRegNo,custNameArabic,custdobar,gender,occupation,applicationId);
	}*/
	public String insertCustomerDetails(MotorBean bean,String detailQuote) {
		return cusDAO.insertCustomerDetails(bean, detailQuote);
	}
	public String insertCustomerDetails(String customerId,String loginId,String branchCode,String productId,String title,String customerName,String mobileNo,String email,String address1,String address2,String poBox,String city,String custAppCode,String clientCustomerId,String custArNo, String custLastName, String custPassportNo, String custdob, String custAlterMobileNo, String custLandLineNo, String customerType, String companyRegNo,String custNameArabic, String custdobar, String gender, String occupation,String type, String custnrc1, String custnrc2, String custnrc3, String flow) {
		return cusDAO.insertCustomerDetails(customerId, loginId, branchCode, productId, title, customerName, mobileNo, email, address1, address2, poBox, city, custAppCode, clientCustomerId, custArNo, custLastName, custPassportNo, custdob, custAlterMobileNo, custLandLineNo, customerType, companyRegNo,custNameArabic,custdobar,gender,occupation,type,custnrc1,custnrc2,custnrc3,flow);
	}
	
	public Map<String,Object> getCustomerDetails(String customerId)  {
		return cusDAO.getCustomerDetails(customerId);
	}
	public LinkedList<String> getValidate(CustomerBean cusBean,String type)
	{
		LinkedList<String> list=new LinkedList<String>();
		try{
		ValidationFormat val = new ValidationFormat();
		Validation validation=new Validation();
		if("CustomerInfo".equalsIgnoreCase(type))
		{
			if(StringUtils.isEmpty(cusBean.getCustomerIdYN())||!"Y".equalsIgnoreCase(cusBean.getCustomerIdYN()))
			{
				if(StringUtils.isEmpty(cusBean.getTitle()))
				{
					list.add("error.customer.title");
				}
				if(StringUtils.isEmpty(cusBean.getFullName()))
				{
					list.add("error.customer.fullName");
				}else if(!val.validateStringWithSpace(cusBean.getFullName()))
				{
					list.add("error.customer.fullName.valid");
				}
				if(StringUtils.isEmpty(cusBean.getGender()))
				{
					list.add("error.customer.gender");
				}
				if(StringUtils.isEmpty(cusBean.getAddress()))
				{
					list.add("error.customer.address");
				}
				if(StringUtils.isEmpty(cusBean.getCusCivilId()))
				{
					list.add("error.customer.cusCivilId");
				}else if(!StringUtils.isNumeric(cusBean.getCusCivilId()))
				{
					list.add("error.customer.cusCivilId.valid");
				}else if(cusBean.getCusCivilId().length()!=10)
				{
					list.add("error.customer.cusCivilId.digitValid");
				}else
				{ 
					String tdisplay=cusBean.getDisplay();
					cusBean.setDisplay("checkCivilId");
					/*if("EXISTS".equalsIgnoreCase(getSearch(cusBean)))
					{
						list.add("error.customer.cusCivilId.exists");
					}*/
					cusBean.setDisplay(tdisplay);
				}
				if(StringUtils.isEmpty(cusBean.getPoBox()))
				{
					list.add("error.customer.poBox");
				}else if(!StringUtils.isNumeric(cusBean.getPoBox()))
				{
					list.add("error.customer.poBox.valid");
				}else if(cusBean.getPoBox().length()>6)
				{
					list.add("error.customer.poBox.digitValid");
				}
				if(StringUtils.isEmpty(cusBean.getCity()))
				{
					list.add("error.customer.city");
				}
				if(StringUtils.isEmpty(cusBean.getTelephoneNo()))
				{
					list.add("error.customer.telephoneNo");
				}else if(!StringUtils.isNumeric(cusBean.getTelephoneNo()))
				{
					list.add("error.customer.telephoneNo.valid");
				}else if(cusBean.getTelephoneNo().length()!=10)
				{
					list.add("error.customer.telephoneNo.dightValid");
				}
				if(StringUtils.isEmpty(cusBean.getMobileNo()))
				{
					list.add("error.customer.mobileNo");
				}else if(!StringUtils.isNumeric(cusBean.getMobileNo()))
				{
					list.add("error.customer.mobileNo.valid");
				}else if(cusBean.getMobileNo().length()!=10)
				{
					list.add("error.customer.mobileNo.dightValid");
				}
				if(StringUtils.isNotBlank(cusBean.getFax()))
				{
					if(!StringUtils.isNumeric(cusBean.getFax()))
					{
						list.add("error.customer.fax.valid");
					}else if(cusBean.getFax().length()!=10)
					{
						list.add("error.customer.fax.dightValid");
					}
				}
				if(StringUtils.isEmpty(cusBean.getEmailId()))
				{
					list.add("error.customer.emailId");
				}else if("invalid".equalsIgnoreCase(validation.emailValidate(cusBean.getEmailId())))
				{	
					list.add("error.customer.valid.emailId");		
				}
				if(StringUtils.isNotEmpty(cusBean.getDob()))
				{
					if(!val.IsDateValidationFormat(cusBean.getDob())){
						list.add("error.customer.dob.validFormat");
					}else if(!val.sysDateValidation(cusBean.getDob()))
					{
						list.add("error.customer.dob.valid");
					}else if(new com.maan.common.dao.CommonDAO().getCalculatedAge(cusBean.getDob())>100)
					{
						list.add("error.customer.age.valid");
					}
				}
				/*if(StringUtils.isEmpty(cusBean.getOccupation()))
				{
					list.add("error.customer.occupation");
				}*/
			}else if(StringUtils.isEmpty(cusBean.getCustomerId())){
				list.add("error.customer.customerId.select");
				getSearch(cusBean);
			}
			if(!list.isEmpty()) {
				if(!StringUtils.isBlank(cusBean.getDob()))
					cusBean.setDob(cusBean.getDob());
			}
		}else if("SearchCustomer".equalsIgnoreCase(type))
		{
			if(StringUtils.isEmpty(cusBean.getFullName()) &&StringUtils.isEmpty(cusBean.getCusCivilId()) && StringUtils.isEmpty(cusBean.getMobileNo())&&StringUtils.isEmpty(cusBean.getTelephoneNo())&&StringUtils.isEmpty(cusBean.getEmailId())&&StringUtils.isEmpty(cusBean.getDob()))
			{
				list.add("error.customer.search");
			}else
			{
				if(!StringUtils.isNumeric(cusBean.getMobileNo()))
				{
					list.add("error.customer.mobileNo.valid");
				}
				if(!StringUtils.isNumeric(cusBean.getTelephoneNo()))
				{
					list.add("error.customer.telephoneNo.valid");
				}
			}
		}
		else if("customerDetails".equalsIgnoreCase(type)) {
			list=new CustomerService().getMotorCustomerValidate(/*bean.getIssuer()*/"",cusBean.getBrokerCode(),cusBean.getExecutive(),cusBean.getTitle(),cusBean.getCustomerName(),cusBean.getCity(),cusBean.getPoBox(),cusBean.getMobileNo(),cusBean.getEmail(),cusBean.getCustLastName(),cusBean.getCustnrc1(),cusBean.getCustnrc2(),cusBean.getCustnrc3(),cusBean.getCustPassportNo(),cusBean.getCustdob(),cusBean.getCustAlterMobileNo(),cusBean.getCustLandLineNo(),cusBean.getCustomerType(),cusBean.getCompanyRegNo(),cusBean.getAddress1(),cusBean.getGender(),cusBean.getOccupation());
		}
		}catch(Exception e)
		{
			LogManager.debug(e);
		}
		return list;
	}
	/* 
	 * Customer Info Validation For Travel & Home
	 
	public LinkedList<String> getCustomerValidate(String issuer,String brokerCode,String executive,String title,String customerName,String city,String poBox,String mobileNo,String email, String address1)
	{
		Validation validation=new Validation();
		ValidationFormat val = new ValidationFormat();
		LinkedList<String> list=new LinkedList<String>();
		try{
			//Customer Information-Start
			if(StringUtils.isNotBlank(issuer))
			{
				if(StringUtils.isEmpty(brokerCode)){
					//list.add("error.customer.title");	
					list.add("error.customer.broker.code");	
				}
				if(StringUtils.isEmpty(brokerCode)){
					list.add("error.customer.title");	
				}
				
			}
			if(StringUtils.isEmpty(title))
			{
				list.add("error.customer.title");
			}
			if(StringUtils.isEmpty(customerName))
			{
				list.add("error.customer.customerName");
			}else if(!val.validateStringWithSpace(customerName))
			{
				list.add("error.customer.customerName.valid");
			}
			if(StringUtils.isBlank(address1)){
				list.add("error.customer.address1.blank");
			}
			if(StringUtils.isEmpty(city))
			{
				list.add("error.customer.city");
			}
			if(StringUtils.isEmpty(poBox))
			{
				list.add("error.customer.poBox");
			}else if(!StringUtils.isNumeric(poBox))
			{
				list.add("error.customer.poBox.valid");
			}
			if(StringUtils.isNotBlank(poBox) && !StringUtils.isNumeric(poBox)){
				list.add("error.customer.poBox.valid");
			}
			if(StringUtils.isEmpty(mobileNo.replace("09","")))
			{
				list.add("error.customer.mobileNo");
			}else if(!StringUtils.isNumeric(mobileNo))
			{
				list.add("error.customer.mobileNo.valid");
			}else if(mobileNo.length()!=1)
			{
				list.add("error.customer.mobileNo.dightValid");
			}
			if(StringUtils.isEmpty(mobileNo))
			{
				list.add("error.customer.mobileNo1");
			}else if(Validation.INVALID.equals(validation.validateMobile(mobileNo)))
			{
				list.add("error.customer.mobileNo1.valid");
			}
			if(StringUtils.isEmpty(mobileNo))
			{
				list.add("error.customer.email");
			}
			else if("invalid".equalsIgnoreCase(validation.emailValidate(email)))
			{	
				list.add("error.customer.valid.email");
			}
				
			//Customer Information-End
		}catch(Exception e)
		{
			LogManager.debug(e);
		}
		return list;
	}
	public String getUpdateCustomerDtl(String customerId,String loginId,String branchCode,String productId,String title,String customerName,String mobileNo,String email,String address1,String address2,String poBox,String city,String custAppCode,String clientCustomerId,String custArNo, String issuer, String brokerCode, String executiveId){
		return cusDAO.getUpdateCustomerDtl(customerId, loginId, branchCode, productId, title, customerName, mobileNo, email, address1, address2, poBox, city, custAppCode, clientCustomerId, custArNo,issuer,brokerCode,executiveId);	
	}*/
	public LinkedList<String> getMotorCustomerValidate(String issuer,String brokerCode,String executive,String title,String customerName,String mobileNo,String email,String customerType,String companyRegNo)
	{
		Validation validation=new Validation();
		ValidationFormat val = new ValidationFormat();
		LinkedList<String> list=new LinkedList<String>();
		try{
			//Customer Information-Start
			if(StringUtils.isNotBlank(issuer))
			{
				/*if(StringUtils.isEmpty(brokerCode)){
					list.add("error.customer.broker");
				}
				if(StringUtils.isEmpty(executive)){
					list.add("error.customer.executive");
				}*/
				
			}
			if(StringUtils.isEmpty(title))
			{
				list.add("error.customer.title");
			}
			if(StringUtils.isEmpty(customerName))
			{
				list.add("error.customer.firstName");
			}else if(!val.validateStringWithSpace(customerName))
			{
				list.add("error.customer.firstName.valid");
			}
			if(StringUtils.isEmpty(mobileNo)) {
				list.add("error.customer.mobileNo");
			} else if(Validation.INVALID.equals(validation.validateMobile(mobileNo))) {
				list.add("error.customer.mobileNo.valid");
			}
			if(StringUtils.isEmpty(email)) {
				list.add("error.customer.email");
			}
			else if("invalid".equalsIgnoreCase(validation.emailValidate(email))) {	
				list.add("error.customer.valid.email");
			}
			/*if(Validation.NEEDED.equals(validateCustomerRegNo(customerType, companyRegNo))) {
				list.add("error.customer.companyRegNo");
			}
			else if(Validation.INVALID.equals(validateCustomerRegNo(customerType, companyRegNo))) {
				list.add("error.customer.companyRegNo.valid");
			}*/
			//Customer Information-End
		}catch(Exception e)
		{
			LogManager.debug(e);
		}
		return list;
	}
	public LinkedList<String> getMotorCustomerValidate(String issuer,String brokerCode,String executive,String title,String customerName,String city,String poBox,String mobileNo,String email, String lastName, String nrc1, String nrc2, String nrc3, String passportNo, String custdob, String alterMobileNo, String landLineNo,String customerType,String companyRegNo,String address1,String gender, String occupation)
	{
		Validation validation=new Validation();
		ValidationFormat val = new ValidationFormat();
		LinkedList<String> list=new LinkedList<String>();
		try{
			//Customer Information-Start
			if(StringUtils.isNotBlank(issuer))
			{
				if(StringUtils.isEmpty(brokerCode)){
					list.add("error.customer.broker");
				}
				if(StringUtils.isEmpty(executive)){
					list.add("error.customer.executive");
				}
			}
			if(StringUtils.isEmpty(title))
			{
				list.add("error.customer.title");
			}
			if(StringUtils.isEmpty(customerName)) {
				list.add("error.customer.firstName");
			} else if(!val.validateStringWithSpace(customerName)) {
				list.add("error.customer.firstName.valid");
			}
			if(!"CORPORATE".equals(customerType)) {
			if(StringUtils.isBlank(lastName)) {
				list.add("error.customer.lastName");
			}
			
			if(StringUtils.isBlank(gender))
				list.add("error.customer.gender");
			}
			/*if(StringUtils.isBlank(occupation))
				list.add("error.customer.occupation");*/
			if(StringUtils.isBlank(address1)){
				list.add("error.customer.address1.blank");
			}
			if(StringUtils.isEmpty(city))
			{
				list.add("error.customer.city");
			}
			/*if(StringUtils.isEmpty(poBox))
			{
				list.add("error.customer.poBox");
			}else if(!StringUtils.isNumeric(poBox))
			{
				list.add("error.customer.poBox.valid");
			}*/
			if(StringUtils.isNotBlank(poBox) && !StringUtils.isAlphanumeric(poBox))
				list.add("error.customer.poBox.valid");
			if(!"CORPORATE".equals(customerType)) {
			if(StringUtils.isBlank(custdob)) {
				list.add("error.customer.dob");
			} else if(!val.IsDateValidationFormat(custdob)) {
				list.add("error.customer.dob.invalid");
			}
			}
			
			if(StringUtils.isEmpty(mobileNo))
			{
				list.add("error.customer.mobileNo1");
			}else if(Validation.INVALID.equals(validation.validateMobile(mobileNo)))
			{
				list.add("error.customer.mobileNo1.valid");
			}
			if(Validation.INVALID.equals(validation.validateMobile(mobileNo))) {
				//list.add("error.customer.alterMobile");
			}
			else if(StringUtils.isNotBlank(alterMobileNo) && !StringUtils.isNumeric(alterMobileNo))
			{
				list.add("error.customer.alterMobile.valid");
			}
			/*if(StringUtils.isEmpty(landLineNo)) {
				list.add("error.customer.landLineNo");
			}
			else if(!StringUtils.isNumeric(landLineNo)) {
				list.add("error.customer.landLineNo.valid");
			}*/
			if(StringUtils.isEmpty(email)) {
				list.add("error.customer.email");
			}
			else if("invalid".equalsIgnoreCase(validation.emailValidate(email))) {	
				list.add("error.customer.valid.email");
			}
			if("INDIVIDUAL".equalsIgnoreCase(customerType) && 
					StringUtils.isBlank(nrc1) && StringUtils.isBlank(nrc2) && StringUtils.isBlank(nrc3) && StringUtils.isBlank(passportNo)) {
				list.add("error.customer.nrc_PassportNo");
			}
			/*if(((nrc1+nrc2+nrc3).length()!=0 && (nrc1+nrc2+nrc3).length()!=9) || 
					(!StringUtils.isNumeric(nrc1+nrc2+nrc3))
				) {
				list.add("error.customer.nrc.invalid");
			}*/
			if((nrc1+nrc2+nrc3).length()!=0){
				if((nrc1+nrc2+nrc3).length()!=9 || (!StringUtils.isNumeric(nrc1+nrc2+nrc3))){
					list.add("error.customer.nrc.invalid");
				}
			}
				
			if(Validation.NEEDED.equals(validateCustomerRegNo(customerType, companyRegNo))) {
				list.add("error.customer.companyRegNo");
			}
			else if(Validation.INVALID.equals(validateCustomerRegNo(customerType, companyRegNo))) {
				list.add("error.customer.companyRegNo.valid");
			}
			
			//Customer Information-End
		}catch(Exception e)
		{
			LogManager.debug(e);
		}
		return list;
	}
	
	public String validateCustomerRegNo(String customerType, String companyRegNo) {
		if("CORPORATE".equals(customerType)) {
			if(StringUtils.isBlank(companyRegNo)) {
				return Validation.NEEDED;
			} else if(!StringUtils.isAlphanumeric(companyRegNo)) {
				return Validation.INVALID;
			} else {
				return Validation.VALID;
			}
		} else {
			return Validation.VALID;
		}
	}
	public LinkedList<String> validateCustomerInfo(CustBean bean,String type){
		LinkedList<String> list=new LinkedList<String>();
		Validation validation=new Validation();
		ValidationFormat val = new ValidationFormat();
		try{
			if("profile".equalsIgnoreCase(type)){
				if(StringUtils.isEmpty(bean.getTitle())) {
					list.add("error.customer.title");
				}
				if(StringUtils.isEmpty(bean.getCustomerName())) {
					list.add("error.customer.customerName");
				}else if(!val.validateStringWithSpace(bean.getCustomerName())) {
					list.add("error.customer.customerName.valid");
				}
				if(StringUtils.isNotBlank(bean.getCustLastName()) && !val.validateStringWithSpace(bean.getCustLastName())){
					list.add("error.customer.customerLastName.valid");
				}
				if(StringUtils.isBlank(bean.getOccupation())){
					list.add("error.customer.occupation");
				}
				if(StringUtils.isBlank(bean.getCompanyRegNo())){
					
				}else if((StringUtils.isNotBlank(bean.getCustdobar()) || StringUtils.isNotBlank(bean.getCustdob()))){
					if('1' == bean.getCompanyRegNo().charAt(0) && StringUtils.isNotBlank(bean.getCustdobar())){
						bean.setCustdob(new CommonDAO().getSingleInfo("GET_DATE_CONVERT",new String[]{"GRE",bean.getCustdobar()}));
					}else if(StringUtils.isNotBlank(bean.getCustdob())){
						bean.setCustdobar(new CommonDAO().getSingleInfo("GET_DATE_CONVERT",new String[]{"HIJ",bean.getCustdob()}));
					}
				}
				
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
	public List<Map<String, Object>> getCustomerList(String mode, String type, String userId, String BrokerCode) {
		return cusDAO.getCustomerList(mode,type,userId,BrokerCode);
	}
	public void customerDetails(CustomerBean bean) {
		
			try {
				Map<String,Object> resMap = customerApi.customerDetails(bean);
				if(resMap!=null) {
					bean.setTitle(resMap.get("Title")==null?"": resMap.get("Title").toString());
					bean.setCustomerName(resMap.get("FirstName")==null?"": resMap.get("FirstName").toString());
					bean.setCustLastName(resMap.get("LastName")==null?"": resMap.get("LastName").toString());
					bean.setCustdob(resMap.get("DateOfBirth")==null?"": resMap.get("DateOfBirth").toString());
					bean.setGender(resMap.get("Gender")==null?"": resMap.get("Gender").toString());
					bean.setOccupation(resMap.get("Occupation")==null?"": resMap.get("Occupation").toString());
					bean.setAddress(resMap.get("Address")==null?"": resMap.get("Address").toString());
					bean.setCity(resMap.get("City")==null?"": resMap.get("City").toString());
					bean.setPoBox(resMap.get("PoBox")==null?"": resMap.get("PoBox").toString());
					bean.setMobileNo(resMap.get("MobileNo")==null?"": resMap.get("MobileNo").toString());
					bean.setCustAlterMobileNo(resMap.get("AlternatMobileNo")==null?"": resMap.get("AlternatMobileNo").toString());
					bean.setEmail(resMap.get("Email")==null?"": resMap.get("Email").toString());
					
					if(resMap.get("Nrc")!=null) {
						String [] nrc = resMap.get("Nrc").toString().split("/");
						if(nrc!=null) {
							bean.setCustnrc1(nrc[0].toString());
							bean.setCustnrc2(nrc[1].toString());
							bean.setCustnrc3(nrc[2].toString());
						}else {
							bean.setCustnrc1(null);
							bean.setCustnrc2(null);
							bean.setCustnrc3(null);
						}
					}
					
					bean.setCustPassportNo(resMap.get("PassportNo")==null?"": resMap.get("PassportNo").toString());
					bean.setCustomerType(resMap.get("CustomerType")==null?"": resMap.get("CustomerType").toString());
					bean.setCompanyRegNo(resMap.get("CompanyRegNo")==null?"": resMap.get("CompanyRegNo").toString());
					bean.setBrokerCode(resMap.get("BrokerCode")==null?"": resMap.get("BrokerCode").toString());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
		
//		try {
//			List<Map<String,Object>> map =new CustomerDAO().customerDetails(bean.getCustomerId());
//			if(map!=null && map.size()>0){
//			    for(int i=0;i<map.size();i++){
//			        bean.setTitle(map.get(i).get("TITLE")==null?"":map.get(i).get("TITLE").toString());
//			        bean.setCustomerName(map.get(i).get("FIRST_NAME")==null?"":map.get(i).get("FIRST_NAME").toString());
//			        bean.setCustLastName(map.get(i).get("LAST_NAME")==null?"":map.get(i).get("LAST_NAME").toString());
//			        bean.setEmail(map.get(i).get("EMAIL")==null?"":map.get(i).get("EMAIL").toString());
//			        bean.setMobileNo(map.get(i).get("MOBILE")==null?"":map.get(i).get("MOBILE").toString());
//			        bean.setAddress1(map.get(i).get("ADDRESS1")==null?"":map.get(i).get("ADDRESS1").toString());
//			        bean.setAddress2(map.get(i).get("ADDRESS2")==null?"":map.get(i).get("ADDRESS2").toString());
//			        bean.setPoBox(map.get(i).get("POBOX")==null?"":map.get(i).get("POBOX").toString());
//			        bean.setCity(map.get(i).get("EMIRATE")==null?"":map.get(i).get("EMIRATE").toString());
//			        bean.setCustCoreCode(map.get(i).get("MISSIPPI_CUSTOMER_CODE")==null?"":map.get(i).get("MISSIPPI_CUSTOMER_CODE").toString());
//			        bean.setCoreAppCode(map.get(i).get("MISSIPPI_CUSTOMER_CODE")==null?"":map.get(i).get("MISSIPPI_CUSTOMER_CODE").toString());
//			        bean.setClientCustomerId(map.get(i).get("CLIENT_CUSTOMER_ID")==null?"":map.get(i).get("CLIENT_CUSTOMER_ID").toString());
//			        bean.setCustArNo(map.get(i).get("CUST_AR_NO")==null?"":map.get(i).get("CUST_AR_NO").toString());
//			        bean.setCustomerType(map.get(i).get("CUSTOMER_TYPE")==null?"":map.get(i).get("CUSTOMER_TYPE").toString());
//			        bean.setCompanyRegNo(map.get(i).get("COMPANY_REG_NO")==null?"":map.get(i).get("COMPANY_REG_NO").toString());
//			        bean.setCustdob(map.get(i).get("DOB")==null?"":map.get(i).get("DOB").toString());
//			        bean.setGender(map.get(i).get("GENDER")==null?"":map.get(i).get("GENDER").toString());
//			        bean.setOccupation(map.get(i).get("OCCUPATION")==null?"":map.get(i).get("OCCUPATION").toString());
//			        bean.setCustAlterMobileNo(map.get(i).get("ALTERNATE_MOBILE")==null?"":map.get(i).get("ALTERNATE_MOBILE").toString());
//			        bean.setCustnrc1(map.get(i).get("NRC1")==null?"":map.get(i).get("NRC1").toString());
//					bean.setCustnrc2(map.get(i).get("NRC2")==null?"":map.get(i).get("NRC2").toString());
//					bean.setCustnrc3(map.get(i).get("NRC3")==null?"":map.get(i).get("NRC3").toString());
//					bean.setCustPassportNo(map.get(i).get("PASSPORT_NUMBER")==null?"":map.get(i).get("PASSPORT_NUMBER").toString());
//			    }
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
	public List<Map<String, Object>> getSearchCustomerList(CustomerBean cusBean) {
		return cusDAO.getSearchCustomerList(cusBean);
	}
}
