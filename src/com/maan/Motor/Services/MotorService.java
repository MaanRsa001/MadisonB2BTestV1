package com.maan.Motor.Services;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import nl.captcha.Captcha;

import org.apache.commons.lang3.StringUtils;

import proj.date.DateFunction;

import com.maan.Motor.DAO.MotorDAO;
import com.maan.Motor.controller.MotorBean;
import com.maan.common.DBConstants;
import com.maan.common.LogManager;
import com.maan.common.Validation;
import com.maan.common.otp.OTPGenerator;
import com.maan.common.util.ResourceBundleUtil;
import com.maan.customer.dao.CustomerDAO;
import com.maan.customer.service.CustomerService;
import com.maan.payment.PaymentService;
import com.maan.services.util.ValidationFormat;

public class MotorService {
	public static final String DISPLAY_NEWQUOTE = "newQuote";
	MotorDAO dao=new MotorDAO();

	/*public String getQuote(MotorBean bean) {
		return dao.getQuote(bean);
	}*/

	public Map getComparisionDetails(MotorBean bean){
		return dao.getComparisionDetails(bean);
	}

	/*public String insertOptionCover(MotorBean bean) {
		return dao.insertOptionCover(bean);
	}*/

	/*public String insertPolicyTypePremium(MotorBean bean) {
		String referral="";
		boolean status=true;
		if(bean.getPolicyType()!=null && !("2".equalsIgnoreCase(bean.getPolicyType()))){
			LogManager.info("inside Comprehensive method");
			status=dao.comprehensivePremium(bean);
		}else{
			status=dao.thirdPartyPremium(bean);
			LogManager.info("inside thirdPartyPremium method");
		}
		LogManager.info("inside insertPolicyTypePremium method");
		LogManager.info("status==="+status);
		if(!status && bean.getPolicyType()!=null){
			LogManager.info("inside status==="+status);
			//referral=dao.premiumReferral(bean);
		}
		return referral;
	}*/
	public String getQuoteInfo(MotorBean bean, boolean errorStatus){
		return dao.getQuoteInfo(bean, errorStatus);
	}
	
	public String getVehiclePremiumInfo(MotorBean bean){
		return dao.getVehiclePremiumInfo(bean);
	}
	public String getPremiumInfo(MotorBean bean){
		return dao.getPremiumInfo(bean);
	}

	public void editQuote(MotorBean bean, boolean hasActionErrors) {
		//dao.getFirstPageDtls(bean);
		removeVehicleBean(bean,hasActionErrors);
		if("User".equalsIgnoreCase(bean.getUserType()) && !hasActionErrors && (StringUtils.isBlank(bean.getQuoteNo()) || StringUtils.isBlank(bean.getApplicationNo()))){
			new CustomerDAO().setB2CCustomerDetails(bean, new CustomerDAO().getCustomerDetails(bean.getLoginId(),"2"));
		}
		if(StringUtils.isNotBlank(bean.getApplicationNo())) {
			dao.getFirstPageDtls(bean);
		}
		if(StringUtils.isBlank(bean.getPolicyStartDate())){
			Calendar cal = Calendar.getInstance();
			Date today = cal.getTime();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			bean.setPolicyStartDate(sdf.format(today));
			if(StringUtils.isBlank(bean.getMobileNo()))
				bean.setMobileNo("09");
		}
		/*if(StringUtils.isBlank(bean.getPremiumType())) {
			bean.setPremiumType("2");
		}*/
		bean.setPremiumType("0");
		bean.setDisplay(DISPLAY_NEWQUOTE);
	}
	public void editQuoteNew(MotorBean bean, boolean hasActionErrors) {
		removeVehicleBean(bean,hasActionErrors);
		if(StringUtils.isNotBlank(bean.getApplicationNo())) {
			dao.getFirstPageDtlsNew(bean);
		}
		if(StringUtils.isBlank(bean.getPolicyStartDate())){
			Calendar cal = Calendar.getInstance();
			Date today = cal.getTime();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			bean.setPolicyStartDate(sdf.format(today));
			if(StringUtils.isBlank(bean.getMobileNo()))
				bean.setMobileNo("09");
		}

		bean.setPremiumType("0");
		bean.setDisplay(DISPLAY_NEWQUOTE);
	}
	
	public List<String> addVehicle(MotorBean bean) throws Exception {
		List<String> errorList=new ArrayList<String>();
		try {
			if(StringUtils.isNotEmpty(bean.getIssuer()) && StringUtils.isNotBlank(bean.getBrokerCode())) {
				bean.setLoginId(new com.maan.common.dao.CommonDAO().getSingleInfo("loginId", new String[]{bean.getBrokerCode()}));
			}
			errorList = getValidate(bean, bean.getActionType());
			if(errorList==null || errorList.size()==0) {
				//bean.setCustomerId(new CustomerService().getUpdateCustomerDtlMotor(bean.getCustomerId(),bean.getLoginId(),bean.getBranchCode(),bean.getProductId(),bean.getTitle(),bean.getCustomerName(),bean.getMobileNo(),bean.getCustomerType(),bean.getEmail(), bean.getCustNameArabic()));
				//bean.setCustnrc(bean.getCustnrc1()+"/"+bean.getCustnrc2()+"/"+bean.getCustnrc3());
				if(StringUtils.isNotBlank(bean.getCustomerId())&&StringUtils.isBlank(bean.getLoginId())){
					dao.setCustDetail(bean,"New");
				}else if(StringUtils.isNotBlank(bean.getLoginId())){
					dao.setCustDetail(bean,"RegNewQuote");
				}
				bean.setCustomerId(new CustomerService().insertCustomerDetails(bean,"detailQuote"));
				String result = dao.addVehicle(bean);
				if("SUCCESS".equalsIgnoreCase(result)){
					/*if(StringUtils.isNotBlank(bean.getReferralValidation())) {
						addActionError(bean.getReferralValidation());
					}*/
					bean.setDisplay(DISPLAY_NEWQUOTE);
				}
				else {
					if(DBConstants.DEVICETYPE_ANDROID.equals(bean.getDeviceType()) || DBConstants.DEVICETYPE_HYBRID.equals(bean.getDeviceType())) {
						bean.getActionErrorsBean().add(ResourceBundleUtil.findText(bean.getProductId(),"error.motor.update"));
					} else {
						errorList.add("error.motor.update");
					}
				}
			}
			//bean.getActionErrorsBean().addAll(errorList);
			removeVehicleBean(bean, (errorList!=null && errorList.size()>0));
				
		} catch (Exception e) {
			e.printStackTrace();
		}
		return errorList;
	}
	
	public String addVehicleNew(MotorBean bean) {
		return dao.addVehicleNew(bean);
	}
//		List<String> errorList=new ArrayList<String>();
//		try {
//			if(StringUtils.isNotEmpty(bean.getIssuer()) && StringUtils.isNotBlank(bean.getBrokerCode())) {
//				bean.setLoginId(new com.maan.common.dao.CommonDAO().getSingleInfo("loginId", new String[]{bean.getBrokerCode()}));
//			}
//			errorList = getValidate(bean, bean.getActionType());
//			if(errorList==null || errorList.size()==0) {
//				//bean.setCustomerId(new CustomerService().getUpdateCustomerDtlMotor(bean.getCustomerId(),bean.getLoginId(),bean.getBranchCode(),bean.getProductId(),bean.getTitle(),bean.getCustomerName(),bean.getMobileNo(),bean.getCustomerType(),bean.getEmail(), bean.getCustNameArabic()));
//				//bean.setCustnrc(bean.getCustnrc1()+"/"+bean.getCustnrc2()+"/"+bean.getCustnrc3());
//				if(StringUtils.isNotBlank(bean.getCustomerId())&&StringUtils.isBlank(bean.getLoginId())){
//					dao.setCustDetail(bean,"New");
//				}else if(StringUtils.isNotBlank(bean.getLoginId())){
//					dao.setCustDetail(bean,"RegNewQuote");
//				}
//				//bean.setCustomerId(new CustomerService().insertCustomerDetails(bean.getCustomerId(),bean.getLoginId(),bean.getBranchCode(),bean.getProductId(),bean.getTitle(),bean.getCustomerName(),bean.getMobileNo(),bean.getEmail(),bean.getAddress1(),bean.getAddress2(),bean.getPoBox(),bean.getCity(),bean.getCoreAppCode(),bean.getClientCustomerId(),bean.getCustArNo(),bean.getCustLastName(),bean.getCustPassportNo(),bean.getCustdob(),bean.getCustAlterMobileNo(),bean.getCustLandLineNo(),bean.getCustomerType(),bean.getCompanyRegNo(),bean.getCustNameArabic(),bean.getCustdobar(),bean.getGender(),bean.getOccupation(),"detailQuote",bean.getCustnrc1(),bean.getCustnrc2(),bean.getCustnrc3()));
//				String result = dao.addVehicleNew(bean);
//				if("SUCCESS".equalsIgnoreCase(result)){
//					/*if(StringUtils.isNotBlank(bean.getReferralValidation())) {
//						addActionError(bean.getReferralValidation());
//					}*/
//					bean.setDisplay("vehDetails");
//				}
//				else {
//					if(DBConstants.DEVICETYPE_ANDROID.equals(bean.getDeviceType()) || DBConstants.DEVICETYPE_HYBRID.equals(bean.getDeviceType())) {
//						bean.getActionErrorsBean().add(ResourceBundleUtil.findText(bean.getProductId(),"error.motor.update"));
//					} else {
//						errorList.add("error.motor.update");
//					}
//				}
//			}
//			//bean.getActionErrorsBean().addAll(errorList);
//			//removeVehicleBean(bean, (errorList!=null && errorList.size()>0));
//				
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return errorList;
//	}
	
	public List<String> addCustomerDtl(MotorBean bean) throws Exception {
		List<String> errorList=new ArrayList<String>();
		try {
			if(StringUtils.isNotEmpty(bean.getIssuer()) && StringUtils.isNotBlank(bean.getBrokerCode())) {
				bean.setLoginId(new com.maan.common.dao.CommonDAO().getSingleInfo("loginId", new String[]{bean.getBrokerCode()}));
			}
			errorList = getValidate(bean, bean.getActionType());
			if(errorList==null || errorList.size()==0) {
				if("getVehicle".equalsIgnoreCase(bean.getActionType())){
					dao.setCustDetail(bean,"getVehicle");
				}
				if(StringUtils.isBlank(bean.getCustomerId())) {
				bean.setCustomerId(new CustomerService().insertCustomerDetails(bean,"detailQuote"));
				}else {
					dao.getMultiVehicleDetails(bean);
				}
				bean.setDisplay("vehDetails");
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return errorList;
	}
	
	public List<String> addPolicyDetails (MotorBean bean) throws Exception {
		List<String> errorList=new ArrayList<String>();
		try {
			String result = dao.addCustomerPolicy(bean);
			if(StringUtils.isNotBlank(bean.getCustomerId())&&StringUtils.isBlank(bean.getLoginId())){
				dao.setCustDetail(bean,"New");
			}else if(StringUtils.isNotBlank(bean.getLoginId())){
				dao.setCustDetail(bean,"RegNewQuote");
			}
			if("SUCCESS".equalsIgnoreCase(result)){
			bean.setDisplay("vehDetails");
			}
			else {
				if(DBConstants.DEVICETYPE_ANDROID.equals(bean.getDeviceType()) || DBConstants.DEVICETYPE_HYBRID.equals(bean.getDeviceType())) {
					bean.getActionErrorsBean().add(ResourceBundleUtil.findText(bean.getProductId(),"error.motor.update"));
			} else {
				errorList.add("error.motor.update");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return errorList;
	}
	
	public String getGeratePolicy(MotorBean bean) {
		return dao.getGeratePolicy(bean);
	}
	/*public String policyGeneration(MotorBean bean) {
		return dao.policyGeneration(bean);
	}*/
	public List<Map<String,Object>> getPolicyInformation(String quoteNo) {
		return dao.getPolicyInformation(quoteNo);
	}

	public String getAdminReferralUpdation(MotorBean bean) {
		return dao.getAdminReferralUpdation(bean);
	}

	public String updateCovRate(MotorBean bean) {
		return dao.updateCovRate(bean);	
	}
	public LinkedList<String> getValidate(MotorBean bean,String type)
	{
		LinkedList<String> list=new LinkedList<String>();
		Validation validation=new Validation();
		ValidationFormat val = new ValidationFormat();
		try{
			if("getVehicle".equalsIgnoreCase(type) || "getSave".equalsIgnoreCase(type)){
				list=new CustomerService().getMotorCustomerValidate(bean.getIssuer(),bean.getBrokerCode(),bean.getExecutive(),bean.getTitle(),bean.getCustomerName(),bean.getMobileNo(),bean.getEmail(),bean.getCustomerType(),bean.getCompanyRegNo());
				if(StringUtils.isEmpty(bean.getCustomerType()))
					list.add("Please Select Customer Type");
				if("getSave".equalsIgnoreCase(type)){
					if(StringUtils.isEmpty(bean.getPolicyType()))
						list.add("error.motor.policyType");
					if(StringUtils.isEmpty(bean.getPolicyStartDate())&&!"getSave".equalsIgnoreCase(type)){
			        	list.add("error.motor.policyStartDate");
				    }
					if(StringUtils.isEmpty(bean.getPolicyEndDate())&&!"getSave".equalsIgnoreCase(type)){
			        	list.add("error.motor.policyEndDate");
					} else if(StringUtils.isBlank(bean.getEndTypeId())) {
				    	  final DateFunction datf = new DateFunction();
			              final Calendar cal1 = Calendar.getInstance();
			              String[] effDT=bean.getPolicyStartDate().split("/");
			              cal1.set(Integer.parseInt(effDT[2]),Integer.parseInt(effDT[1]),Integer.parseInt(effDT[0]));
			              final Calendar cal2 = Calendar.getInstance();
			              final Map todayDate =  new com.maan.common.dao.CommonDAO().getTodaysDate();
			              cal2.set(Integer.parseInt(todayDate.get("YEAR").toString()),Integer.parseInt(todayDate.get("MONTH").toString()),Integer.parseInt(todayDate.get("DAY").toString()));
			              long diff = 0;
			              try{
			                  diff = datf.getDayDifference(cal1,cal2);
			              }
			              catch(Exception e){
			                   LogManager.debug(e);
			              }
						  int backDates=0;
						  String backDate="";
			              if(diff > 0)
						  {
			            	  	backDate = new com.maan.common.dao.CommonDAO().getBackDatesAllowed((String)bean.getLoginId(),(String)bean.getUserType(),bean.getProductId(),bean.getBranchCode(),"");
			                    if(backDate!=null&&!"".equals(backDate)){
									backDates = Integer.parseInt(backDate);
								}
								if(backDates<0){
									list.add("error.motor.inceptionDt.less.currentDt");
								}
								else if(diff > backDates)	{
									list.add("error.motor.backDt.valid#"+backDates);
								}
			              }
				     }
					if(StringUtils.isEmpty(bean.getCurrencyType()))
						list.add("Please Select Currency Type");
				}
				if( DBConstants.DEVICETYPE_HYBRID.equals(bean.getDeviceType()) && "Y".equals(bean.getTpLiablityYN()) ) {
					if(StringUtils.isBlank(bean.getTpLiablityAmount())) {
						list.add("error.motor.TpLiablityAmount");
					} else {
						double minLimit = 90000;
						double maxLimit = 500000;
						double tplAmount = Double.parseDouble(bean.getTpLiablityAmount());
						if("USD".equals(bean.getCurrencyType())) {
							minLimit = minLimit/11.37;
							maxLimit = maxLimit/11.37;
						}
						if(tplAmount<minLimit) {
							list.add("error.motor.TpLiablityAmount.minInvalid#" + minLimit);
						} else if(tplAmount>maxLimit) {
							list.add("error.motor.TpLiablityAmount.maxInvalid#" + maxLimit);
						}
					}
				}
			}
			else if("getSaveVehicle".equalsIgnoreCase(type)){
				int i=0;
				String modelName="";
				//if(StringUtils.isBlank(bean.getQuoteNo()) && StringUtils.isBlank(bean.getMakeIdList().get(i))) {
				if(bean.getMulVehDtls()==null ||bean.getMulVehDtls().size()<=0 || "Y".equalsIgnoreCase(bean.getIsVehicleEdit())) {
				/*	
				}
				else {*/
					//Vehicle Details-Start
				
					if(StringUtils.isEmpty(bean.getMakeIdList().get(i)))
					{
						list.add("error.motor.make");
					}
					if(StringUtils.isEmpty(bean.getModelIdList().get(i)))
					{
						list.add("error.motor.model");
					}else{
						modelName=dao.getModelName(bean);
					}
					if(StringUtils.isEmpty(bean.getTypeBodyIdList().get(i))) {
						list.add("error.motor.typeBody");
					}
					if(StringUtils.isEmpty(bean.getSeatingCapacityList().get(i)))
					{
						list.add("error.motor.seatingCapacity");
					}else if("invalid".equalsIgnoreCase(validation.validInteger(bean.getSeatingCapacityList().get(i))))
					{
						list.add("error.motor.seatingCapacity.valid");
					}else if(Integer.parseInt(bean.getSeatingCapacityList().get(i))<2)
					{
						list.add("error.motor.seatingCapacity.grETwo");
					}
					/*if(StringUtils.isEmpty(bean.getNoOfCylinderIdList().get(i))) {
						list.add("error.motor.noOfCylinder");
					}*/
					if(StringUtils.isEmpty(bean.getVehicleUsageIdList().get(i))) {
						list.add("error.motor.vehicleUsage");
					} else if(!bean.getVehicleUsageIdList().get(i).equals(dao.validateVehicleUsage(bean.getApplicationNo(), bean.getVehicleIdList().get(i), bean.getVehicleUsageIdList().get(i)))) {
						list.add("error.motor.vehicleUsage.invalid");
					}
					if(!"3".equalsIgnoreCase(bean.getPolicyType())){
						if(StringUtils.isEmpty(bean.getSumInsuredList().get(i)))
						{
							list.add("error.motor.sumInsured");
						}else if("invalid".equalsIgnoreCase(validation.validDouble(bean.getSumInsuredList().get(i))))
						{
							list.add("error.motor.sumInsured.valid");
						}else if(Double.parseDouble(bean.getSumInsuredList().get(i))<=0)
						{
							list.add("error.motor.sumInsured.grZero");
						}
					}
					if(StringUtils.isEmpty(bean.getMfgYrIdList().get(i))) {
						list.add("error.motor.manufactureYear");
					}
					if(bean.getDeductibleIdList()==null || StringUtils.isBlank(bean.getDeductibleIdList().get(i))) {
						list.add("error.motor.deductible");
					}
					if(!"quoteView".equalsIgnoreCase(bean.getReqFrom())){
						try{
						if(StringUtils.isBlank(bean.getRegNoList().get(i))){
							list.add("error.motor.regNo.enterNew");
							
						}
						else if(StringUtils.isNotBlank(bean.getRegNoList().get(i)) && !StringUtils.isAlphanumeric(bean.getRegNoList().get(i))) {
							list.add("error.motor.regNo.validNew" );
							
						}
						if(StringUtils.isBlank(bean.getChassisNoList().get(i))){
							list.add("error.motor.chassisNo.enterNew" );
						}
						else if(StringUtils.isNotBlank(bean.getChassisNoList().get(i)) && !validation.isValidAlphHypen(bean.getChassisNoList().get(i))) {
							list.add("error.motor.chassisNo.validNew" );
						}
						if(!"TRAILER".equalsIgnoreCase(modelName)){
							if(StringUtils.isBlank(bean.getEngineNoList().get(i))){
								list.add("error.motor.engineNo.enterNew" );
							}
							if(StringUtils.isNotBlank(bean.getEngineNoList().get(i)) && !validation.isValidAlphHypen(bean.getEngineNoList().get(i))) {
								list.add("error.motor.engineNo.validNew" );
							}
						}
						if(StringUtils.isNotBlank(bean.getRegNoList().get(i))&&StringUtils.isNotBlank(bean.getChassisNoList().get(i))&&bean.getRegNoList().get(i).equals(bean.getChassisNoList().get(i))) {
							list.add("error.motor.regNoNEChassisNoNew" );
						
						}
						if(StringUtils.isNotBlank(bean.getRegNoList().get(i))&&StringUtils.isNotBlank(bean.getEngineNoList().get(i))&&bean.getRegNoList().get(i).equals(bean.getEngineNoList().get(i))) {
							list.add("error.motor.regNoNEEngineNoNew" );
							
						}
						if(StringUtils.isNotBlank(bean.getChassisNoList().get(i))&&StringUtils.isNotBlank(bean.getEngineNoList().get(i))&&bean.getChassisNoList().get(i).equals(bean.getEngineNoList().get(i))) {
							list.add("error.motor.ChassisNoNEEngineNoNew" );
							
						}
						
						if(StringUtils.isEmpty(bean.getLeasedYNIdList().get(i))) {
							list.add("error.motor.LeasedYNNew" );
							
						} else if("Y".equalsIgnoreCase(bean.getLeasedYNIdList().get(i))&&StringUtils.isEmpty(bean.getBankOfFinanceIdList().get(i))) {
							list.add("error.motor.bankOfFinanceNew" );
							
						}
						if(StringUtils.isEmpty(bean.getPrevClaimYn().get(i))) {
							list.add("Please Choose Previous Claims Yes/No" );
							
						} else if("Y".equalsIgnoreCase(bean.getPrevClaimYn().get(i))&&StringUtils.isEmpty(bean.getNoOfClaims().get(i))) {
							list.add("Please Select No. Of Claims" );
							
						}
						if(StringUtils.isEmpty(bean.getCubicCapacityList().get(i))) {
							//list.add("error.motor.cubicCapacity" + vechicleRow);
						} else if("invalid".equalsIgnoreCase(validation.validInteger(bean.getCubicCapacityList().get(i)))) {
							list.add("error.motor.cubicCapacity.valid" );
						} else if(Integer.parseInt(bean.getCubicCapacityList().get(i))<2) {
							list.add("error.motor.cubicCapacity.grETwoNew" );
						}
						}catch(Exception e){
							e.printStackTrace();
						}
					}
					
				}
			}else if("getSaveDriver".equalsIgnoreCase(type)){
				int i=0;
				if(StringUtils.isBlank(bean.getDriverIdList().get(i))) {
					list.add("error.motor.vdriverIdNew" );
					
				}else if(!StringUtils.isAlphanumeric(bean.getDriverIdList().get(i))) {
					list.add("Please enter valid Driver ID (Licence No)");
				}
				if(StringUtils.isEmpty(bean.getDriverDOBList().get(i))) {
					list.add("error.motor.DateOfBirth");
				} else if(!val.IsDateValidationFormat(bean.getDriverDOBList().get(i))){
					list.add("error.motor.DateOfBirth.validFormat");
				}/* else if(!val.sysDateValidation(bean.getDriverDOBList().get(i))) {
					list.add("error.motor.DateOfBirth.valid");
				} */else {
					int age=new com.maan.common.dao.CommonDAO().getCalculatedAge(bean.getDriverDOBList().get(i));
					if(age>100 || age<18) {
						list.add("error.motor.DateOfBirth.must.be.greater.18");
					}
				}if(StringUtils.isEmpty(bean.getOwnerDriverYNList().get(i))){
					list.add("Please Choose Vehicle owner and driver");
				}
				if("Y".equalsIgnoreCase(bean.getIsClaimDtl())){
					if("N".equalsIgnoreCase(bean.getClaimYNIdList().get(i))){
						if(bean.getNoClaimBonusIdList()==null || StringUtils.isEmpty(bean.getNoClaimBonusIdList().get(i)))
							list.add("error.motor.noClaimBonus");
					}
					/*List<String> claimYnList=new ArrayList<String>();
					try {
						claimYnList.add(bean.getClaimYNIdList().get(i));
					} catch (Exception e) {
						claimYnList.add("");
						e.printStackTrace();
					}*/
					
					if(StringUtils.isEmpty(bean.getClaimYNIdList().get(i)))
					{
						list.add("error.motor.driverClaimMadeYN");
					}
					else if("Y".equals(bean.getClaimYNIdList().get(i)))
					{
						if(StringUtils.isEmpty(bean.getClaimAmountList().get(i)))
							list.add("error.motor.claimAmount");
						else if("invalid".equalsIgnoreCase(validation.validDouble(bean.getClaimAmountList().get(i))))
						{
							list.add("error.motor.claimAmount.valid");
						}
					}
					if(!"0".equals(bean.getNoClaimBonusIdList().get(i)) && !"".equals(bean.getNoClaimBonusIdList().get(i))) {
					
						/*if(StringUtils.isBlank(bean.getPrevPolicyNoList().get(i))) {
							list.add("error.motor.prevPolicyNo" + vechicleRow);
							errorStatus = true;
						}*/
						if(StringUtils.isBlank(bean.getPrevPolicyNoList().get(i))) {
							list.add("error.motor.prevPolicyNoNew");
						}
							
						if(StringUtils.isBlank(bean.getPrevExpiryDateList().get(i))) {
							/*list.add("error.motor.prevInsExpiryDate" + vechicleRow);
							errorStatus = true;*/
							list.add("error.motor.prevInsExpiryDateNew");
						} else if(!val.IsDateValidationFormat(bean.getPrevExpiryDateList().get(i))) {
							list.add("error.motor.prevInsExpiryDate.invalidNew" );
							
						}
						if(StringUtils.isBlank(bean.getPrevInsCompanyIdList().get(i))) {
							list.add("error.motor.prevInsCompanyNew" );
								
						}else if("13".equalsIgnoreCase(bean.getPrevInsCompanyIdList().get(i)) && StringUtils.isNotBlank(bean.getPrevPolicyNoList().get(i))){
							int count=dao.isExistInRenewal(bean.getPrevPolicyNoList().get(i));
							if(count<=0)
								list.add("error.motor.invalidRenewalPolicy" );
						}
					
					}
					/*if(!"13".equalsIgnoreCase(bean.getPrevInsCompanyIdList().get(i))){
						if(Integer.parseInt(dao.getDocumenCount(bean.getQuoteNo(),bean.getVehicleIdList().get(i)))<=0) {
							list.add("error.motor.documents" + vechicleRow);
							}
					}*/
				}
			}
			else if("getQuotation".equalsIgnoreCase(type)){
				

				if(StringUtils.isEmpty(bean.getPolicyType()))
					list.add("error.motor.policyType");
				if(StringUtils.isEmpty(bean.getPolicyStartDate())&&!"getSave".equalsIgnoreCase(type)){
		        	list.add("error.motor.policyStartDate");
			    }
				if(StringUtils.isEmpty(bean.getPolicyEndDate())&&!"getSave".equalsIgnoreCase(type)){
		        	list.add("error.motor.policyEndDate");
				} else if(StringUtils.isBlank(bean.getEndTypeId())) {
			    	  final DateFunction datf = new DateFunction();
		              final Calendar cal1 = Calendar.getInstance();
		              String[] effDT=bean.getPolicyStartDate().split("/");
		              cal1.set(Integer.parseInt(effDT[2]),Integer.parseInt(effDT[1]),Integer.parseInt(effDT[0]));
		              final Calendar cal2 = Calendar.getInstance();
		              final Map todayDate =  new com.maan.common.dao.CommonDAO().getTodaysDate();
		              cal2.set(Integer.parseInt(todayDate.get("YEAR").toString()),Integer.parseInt(todayDate.get("MONTH").toString()),Integer.parseInt(todayDate.get("DAY").toString()));
		              long diff = 0;
		              try{
		                  diff = datf.getDayDifference(cal1,cal2);
		              }
		              catch(Exception e){
		                   LogManager.debug(e);
		              }
					  int backDates=0;
					  String backDate="";
		              if(diff > 0)
					  {
		            	  	backDate = new com.maan.common.dao.CommonDAO().getBackDatesAllowed((String)bean.getLoginId(),(String)bean.getUserType(),bean.getProductId(),bean.getBranchCode(),"");
		                    if(backDate!=null&&!"".equals(backDate)){
								backDates = Integer.parseInt(backDate);
							}
							if(backDates<0){
								list.add("error.motor.inceptionDt.less.currentDt");
							}
							else if(diff > backDates)	{
								list.add("error.motor.backDt.valid#"+backDates);
							}
		              }
			     }
				if(StringUtils.isEmpty(bean.getCurrencyType()))
					list.add("Please Select Currency Type");
			
				
				list=dao.getVehicleDetailsValidation(bean,list);
			}
			else if("getQuote".equalsIgnoreCase(type) || "getSave".equalsIgnoreCase(type)){
				list=new CustomerService().getMotorCustomerValidate(bean.getIssuer(),bean.getBrokerCode(),bean.getExecutive(),bean.getTitle(),bean.getCustomerName(),bean.getMobileNo(),bean.getEmail(),bean.getCustomerType(),bean.getCompanyRegNo());
				
				if(StringUtils.isEmpty(bean.getPolicyType()))
					list.add("error.motor.policyType");
				
				int i=0;
				//if(StringUtils.isBlank(bean.getQuoteNo()) && StringUtils.isBlank(bean.getMakeIdList().get(i))) {
				if(bean.getMulVehDtls()==null ||bean.getMulVehDtls().size()<=0 || "Y".equalsIgnoreCase(bean.getIsVehicleEdit())) {
				/*	
				}
				else {*/
					//Vehicle Details-Start
				
					if(StringUtils.isEmpty(bean.getMakeIdList().get(i)))
					{
						list.add("error.motor.make");
					}
					if(StringUtils.isEmpty(bean.getModelIdList().get(i)))
					{
						list.add("error.motor.model");
					}
					if(StringUtils.isEmpty(bean.getTypeBodyIdList().get(i))) {
						list.add("error.motor.typeBody");
					}
					if(StringUtils.isEmpty(bean.getMfgYrIdList().get(i))) {
						list.add("error.motor.manufactureYear");
					}
					if(!"3".equalsIgnoreCase(bean.getPolicyType())){
						if(StringUtils.isEmpty(bean.getSumInsuredList().get(i)))
						{
							list.add("error.motor.sumInsured");
						}else if("invalid".equalsIgnoreCase(validation.validDouble(bean.getSumInsuredList().get(i))))
						{
							list.add("error.motor.sumInsured.valid");
						}else if(Double.parseDouble(bean.getSumInsuredList().get(i))<=0)
						{
							list.add("error.motor.sumInsured.grZero");
						}
					}
					if(StringUtils.isEmpty(bean.getSeatingCapacityList().get(i)))
					{
						list.add("error.motor.seatingCapacity");
					}else if("invalid".equalsIgnoreCase(validation.validInteger(bean.getSeatingCapacityList().get(i))))
					{
						list.add("error.motor.seatingCapacity.valid");
					}else if(Integer.parseInt(bean.getSeatingCapacityList().get(i))<2)
					{
						list.add("error.motor.seatingCapacity.grETwo");
					}
					/*if(StringUtils.isEmpty(bean.getNoOfCylinderIdList().get(i))) {
						list.add("error.motor.noOfCylinder");
					}*/
					if(StringUtils.isEmpty(bean.getVehicleUsageIdList().get(i))) {
						list.add("error.motor.vehicleUsage");
					} else if(!bean.getVehicleUsageIdList().get(i).equals(dao.validateVehicleUsage(bean.getApplicationNo(), bean.getVehicleIdList().get(i), bean.getVehicleUsageIdList().get(i)))) {
						list.add("error.motor.vehicleUsage.invalid");
					}
					if(bean.getDeductibleIdList()==null || StringUtils.isBlank(bean.getDeductibleIdList().get(i))) {
						list.add("error.motor.deductible");
					}
					if(!"quoteView".equalsIgnoreCase(bean.getReqFrom())){
						try{
						if(StringUtils.isBlank(bean.getRegNoList().get(i))){
							list.add("error.motor.regNo.enterNew");
							
						}
						else if(StringUtils.isNotBlank(bean.getRegNoList().get(i)) && !StringUtils.isAlphanumeric(bean.getRegNoList().get(i))) {
							list.add("error.motor.regNo.validNew" );
							
						}
						if(StringUtils.isBlank(bean.getChassisNoList().get(i))){
							list.add("error.motor.chassisNo.enterNew" );
						}
						else if(StringUtils.isNotBlank(bean.getChassisNoList().get(i)) && !validation.isValidAlphHypen(bean.getChassisNoList().get(i))) {
							list.add("error.motor.chassisNo.validNew" );
						}
						if(StringUtils.isBlank(bean.getEngineNoList().get(i))){
							list.add("error.motor.engineNo.enterNew" );
						}
						if(StringUtils.isNotBlank(bean.getEngineNoList().get(i)) && !validation.isValidAlphHypen(bean.getEngineNoList().get(i))) {
							list.add("error.motor.engineNo.validNew" );
						}
						if(StringUtils.isNotBlank(bean.getRegNoList().get(i))&&StringUtils.isNotBlank(bean.getChassisNoList().get(i))&&bean.getRegNoList().get(i).equals(bean.getChassisNoList().get(i))) {
							list.add("error.motor.regNoNEChassisNoNew" );
						
						}
						if(StringUtils.isNotBlank(bean.getRegNoList().get(i))&&StringUtils.isNotBlank(bean.getEngineNoList().get(i))&&bean.getRegNoList().get(i).equals(bean.getEngineNoList().get(i))) {
							list.add("error.motor.regNoNEEngineNoNew" );
							
						}
						if(StringUtils.isNotBlank(bean.getChassisNoList().get(i))&&StringUtils.isNotBlank(bean.getEngineNoList().get(i))&&bean.getChassisNoList().get(i).equals(bean.getEngineNoList().get(i))) {
							list.add("error.motor.ChassisNoNEEngineNoNew" );
							
						}
						
						if(StringUtils.isEmpty(bean.getLeasedYNIdList().get(i))) {
							list.add("error.motor.LeasedYNNew" );
							
						} else if("Y".equalsIgnoreCase(bean.getLeasedYNIdList().get(i))&&StringUtils.isEmpty(bean.getBankOfFinanceIdList().get(i))) {
							list.add("error.motor.bankOfFinanceNew" );
							
						}
						if(StringUtils.isEmpty(bean.getCubicCapacityList().get(i))) {
							//list.add("error.motor.cubicCapacity" + vechicleRow);
						} else if("invalid".equalsIgnoreCase(validation.validInteger(bean.getCubicCapacityList().get(i)))) {
							list.add("error.motor.cubicCapacity.valid" );
						} else if(Integer.parseInt(bean.getCubicCapacityList().get(i))<2) {
							list.add("error.motor.cubicCapacity.grETwoNew" );
						}
						}catch(Exception e){
							e.printStackTrace();
						}
					}
					
					if(bean.getNoClaimBonusIdList()==null || StringUtils.isEmpty(bean.getNoClaimBonusIdList().get(i)))
						list.add("error.motor.noClaimBonus");
					if(StringUtils.isEmpty(bean.getClaimYNIdList().get(i)))
					{
						list.add("error.motor.driverClaimMadeYN");
					}else if("Y".equals(bean.getClaimYNIdList().get(i)))
					{
						if(StringUtils.isEmpty(bean.getClaimAmountList().get(i)))
							list.add("error.motor.claimAmount");
						else if("invalid".equalsIgnoreCase(validation.validDouble(bean.getClaimAmountList().get(i))))
						{
							list.add("error.motor.claimAmount.valid");
						}
					}
					if(!"0".equals(bean.getNoClaimBonusIdList().get(i)) && !"".equals(bean.getNoClaimBonusIdList().get(i))) {
						/*if(StringUtils.isBlank(bean.getPrevPolicyNoList().get(i))) {
							list.add("error.motor.prevPolicyNo" + vechicleRow);
							errorStatus = true;
						}*/
						if(StringUtils.isBlank(bean.getPrevPolicyNoList().get(i))) {
							list.add("error.motor.prevPolicyNoNew");
						}
							
						if(StringUtils.isBlank(bean.getPrevExpiryDateList().get(i))) {
							/*list.add("error.motor.prevInsExpiryDate" + vechicleRow);
							errorStatus = true;*/
							//list.add("error.motor.prevInsExpiryDateNew");
						} else if(!val.IsDateValidationFormat(bean.getPrevExpiryDateList().get(i))) {
							list.add("error.motor.prevInsExpiryDate.invalidNew" );
							
						}
						if(StringUtils.isBlank(bean.getPrevInsCompanyIdList().get(i))) {
							list.add("error.motor.prevInsCompanyNew" );
								
						}else if("13".equalsIgnoreCase(bean.getPrevInsCompanyIdList().get(i)) && StringUtils.isNotBlank(bean.getPrevPolicyNoList().get(i))){
							int count=dao.isExistInRenewal(bean.getPrevPolicyNoList().get(i));
							if(count<=0)
								list.add("error.motor.invalidRenewalPolicy" );
						}
						/*if(!"13".equalsIgnoreCase(bean.getPrevInsCompanyIdList().get(i))){
							if(Integer.parseInt(dao.getDocumenCount(bean.getQuoteNo(),bean.getVehicleIdList().get(i)))<=0) {
								list.add("error.motor.documents" + vechicleRow);
								}
						}*/
					}
					/** Commented on 14/01/2023 on request from mapande mail start**/
					/*if(StringUtils.isBlank(bean.getDriverIdList().get(i))) {
						list.add("error.motor.vdriverIdNew" );
						
					}else if(!StringUtils.isAlphanumeric(bean.getDriverIdList().get(i))) {
						list.add("Please enter valid Driver ID (Licence No)");
					}*/
					/** Commented on 14/01/2023 on request from mapande mail end**/
					/* else if(!StringUtils.isAlphanumeric(bean.getDriverIdList().get(i))) {
						list.add("error.motor.vdriverId.invalid" + vechicleRow);
						errorStatus = true;
					}*/
					
					
					
					/*if(StringUtils.isEmpty(bean.getAreaCoverageIdList().get(i))) {
						list.add("error.motor.areaCoverage");
					}*/
					/*if(StringUtils.isEmpty(bean.getAgencyRepairYNIdList().get(i)))
					{
						list.add("error.motor.agencyRepair");
					}*/
					//Vehicle Details-End
					
					//Driver Details-Start
					/*if(StringUtils.isNotBlank(bean.getDriverIdList().get(i)) && !StringUtils.isAlphanumeric(bean.getDriverIdList().get(i))) {
						list.add("error.motor.driverId.invalid");
					}*/
					if(StringUtils.isEmpty(bean.getDriverDOBList().get(i))) {
						list.add("error.motor.DateOfBirth");
					} else if(!val.IsDateValidationFormat(bean.getDriverDOBList().get(i))){
						list.add("error.motor.DateOfBirth.validFormat");
					}/* else if(!val.sysDateValidation(bean.getDriverDOBList().get(i))) {
						list.add("error.motor.DateOfBirth.valid");
					} */else {
						int age=new com.maan.common.dao.CommonDAO().getCalculatedAge(bean.getDriverDOBList().get(i));
						if(age>100 || age<18) {
							list.add("error.motor.DateOfBirth.must.be.greater.18");
						}
					}
					/*if(StringUtils.isEmpty(bean.getDriverNationality())&&!"getSave".equalsIgnoreCase(type))
					{
						list.add("error.motor.nationality");
					}*/
					//Driver Details-End
					
					//Driver Claim Details-Start
					/*if(bean.getNoClaimBonusIdList()==null || StringUtils.isEmpty(bean.getNoClaimBonusIdList().get(i)))
						list.add("error.motor.noClaimBonus");
					
					if(StringUtils.isEmpty(bean.getClaimYNIdList().get(i)))
					{
						list.add("error.motor.driverClaimMadeYN");
					}else if("Y".equals(bean.getClaimYNIdList().get(i)))
					{
						if(StringUtils.isEmpty(bean.getClaimAmountList().get(i)))
							list.add("error.motor.claimAmount");
						else if("invalid".equalsIgnoreCase(validation.validDouble(bean.getClaimAmountList().get(i))))
						{
							list.add("error.motor.claimAmount.valid");
						}
					}*/
					//Driver Claim Details-End
				}
				//Cover Start Date-Start
				if(StringUtils.isEmpty(bean.getPolicyStartDate())&&!"getSave".equalsIgnoreCase(type)){
			        	list.add("error.motor.policyStartDate");
			    }
				if(StringUtils.isEmpty(bean.getPolicyEndDate())&&!"getSave".equalsIgnoreCase(type)){
		        	list.add("error.motor.policyEndDate");
				} else if(StringUtils.isBlank(bean.getEndTypeId())) {
			    	  final DateFunction datf = new DateFunction();
		              final Calendar cal1 = Calendar.getInstance();
		              String[] effDT=bean.getPolicyStartDate().split("/");
		              cal1.set(Integer.parseInt(effDT[2]),Integer.parseInt(effDT[1]),Integer.parseInt(effDT[0]));
		              final Calendar cal2 = Calendar.getInstance();
		              final Map todayDate =  new com.maan.common.dao.CommonDAO().getTodaysDate();
		              cal2.set(Integer.parseInt(todayDate.get("YEAR").toString()),Integer.parseInt(todayDate.get("MONTH").toString()),Integer.parseInt(todayDate.get("DAY").toString()));
		              long diff = 0;
		              try{
		                  diff = datf.getDayDifference(cal1,cal2);
		              }
		              catch(Exception e){
		                   LogManager.debug(e);
		              }
					  int backDates=0;
					  String backDate="";
		              if(diff > 0)
					  {
		            	  	backDate = new com.maan.common.dao.CommonDAO().getBackDatesAllowed((String)bean.getLoginId(),(String)bean.getUserType(),bean.getProductId(),bean.getBranchCode(),"");
		                    if(backDate!=null&&!"".equals(backDate)){
								backDates = Integer.parseInt(backDate);
							}
							if(backDates<0){
								list.add("error.motor.inceptionDt.less.currentDt");
							}
							else if(diff > backDates)	{
								list.add("error.motor.backDt.valid#"+backDates);
							}
		              }
			     }
				if( DBConstants.DEVICETYPE_HYBRID.equals(bean.getDeviceType()) && "Y".equals(bean.getTpLiablityYN()) ) {
					if(StringUtils.isBlank(bean.getTpLiablityAmount())) {
						list.add("error.motor.TpLiablityAmount");
					} else {
						double minLimit = 90000;
						double maxLimit = 500000;
						double tplAmount = Double.parseDouble(bean.getTpLiablityAmount());
						if("USD".equals(bean.getCurrencyType())) {
							minLimit = minLimit/11.37;
							maxLimit = maxLimit/11.37;
						}
						if(tplAmount<minLimit) {
							list.add("error.motor.TpLiablityAmount.minInvalid#" + minLimit);
						} else if(tplAmount>maxLimit) {
							list.add("error.motor.TpLiablityAmount.maxInvalid#" + maxLimit);
						}
					}
				}
				/* if(StringUtils.isNotEmpty(bean.getPolicyStartDate())&&val.sysDateValidation(bean.getPolicyStartDate()))
			     {
			        	list.add("error.motor.policyStartDate.less.currentDt");
			     }*/
				 //Cover Start Date-End
			}
			else if("getPolicy".equalsIgnoreCase(type)){
				if(!"admin".equalsIgnoreCase(bean.getUser())&&!"getSave".equalsIgnoreCase(bean.getActionType()))
				{
					if("Y".equalsIgnoreCase(bean.getReferralYN())&&StringUtils.isBlank(bean.getReferralComments()))
						list.add("error.motor.comments");
					if(!"admin".equalsIgnoreCase(bean.getUser())){
						if("Y".equalsIgnoreCase(bean.getGeneratePolicyYN()) && !"RA".equalsIgnoreCase(bean.getQuoteStatus())) {
							if(StringUtils.isNotBlank(bean.getPolicyStartDate())){
								final DateFunction datf = new DateFunction();
					              final Calendar cal1 = Calendar.getInstance();
					              String[] effDT=bean.getPolicyStartDate().split("/");
					              cal1.set(Integer.parseInt(effDT[2]),Integer.parseInt(effDT[1]),Integer.parseInt(effDT[0]));
					              final Calendar cal2 = Calendar.getInstance();
					              final Map todayDate =  new com.maan.common.dao.CommonDAO().getTodaysDate();
					              cal2.set(Integer.parseInt(todayDate.get("YEAR").toString()),Integer.parseInt(todayDate.get("MONTH").toString()),Integer.parseInt(todayDate.get("DAY").toString()));
					              long diff = 0;
					              try{
					                  diff = datf.getDayDifference(cal1,cal2);
					              }
					              catch(Exception e){
					                   LogManager.debug(e);
					              }
								  int backDates=0;
								  String backDate="";
					              if(diff > 0)
								  {
					            	  	backDate = new com.maan.common.dao.CommonDAO().getBackDatesAllowed((String)bean.getLoginId(),(String)bean.getUserType(),bean.getProductId(),bean.getBranchCode(),"");
					                    if(backDate!=null&&!"".equals(backDate)){
											backDates = Integer.parseInt(backDate);
										}
										if(backDates<0){
											list.add("error.motor.inceptionDt.less.currentDt");
										}
										else if(diff > backDates)	{
											list.add("error.motor.backDt.valid#"+backDates);
										}
					              }
							}
							String numOfDays=new com.maan.common.dao.CommonDAO().getNumberOfDays(bean.getPolicyStartDate(), bean.getQuoteNo());
							if(Integer.parseInt(numOfDays)<=0){
								list.add("Policy Period is not valid, Please change policy Start and End date");
							}
						}
						/*if(StringUtils.isBlank(bean.getModeOfPay())){
							list.add("error.motor.modeOfpayment");
						}*/
						/*if(StringUtils.isNotBlank(bean.getModeOfPay())&& !"Credit Card".equalsIgnoreCase(bean.getModeOfPay())&&StringUtils.isBlank(bean.getChqInvNo()) ){
							list.add("error.motor.chqInvNo");
						}*/
						/*if(!"Y".equalsIgnoreCase(bean.getDeclarationYN())) {
							list.add("error.declaration");
						}*/
					}
				}
				if("admin".equalsIgnoreCase(bean.getUser())&&StringUtils.isNotBlank(bean.getTotalPremium())&&Double.parseDouble(bean.getTotalPremium())<=0){
					//list.add("error.motor.totalPremiumPayable");
					list.add("Total Premium Payable should be greater than 0");
				}
			}
			else if("AddVehicleDetails".equalsIgnoreCase(type)) {
				//Additional Vehicle Details-Start
				for(int i=0 ; i<bean.getVehicleIdList().size() ; i++) {
					boolean errorStatus = false;
					String vechicleRow = "#in vehicle " + (i+1);
					
					if(StringUtils.isBlank(bean.getRegNoList().get(i))){
						list.add("error.motor.regNo.enter" + vechicleRow);
						errorStatus = true;
					}
					else if(StringUtils.isNotBlank(bean.getRegNoList().get(i)) && !StringUtils.isAlphanumeric(bean.getRegNoList().get(i))) {
						list.add("error.motor.regNo.valid" + vechicleRow);
						errorStatus = true;
					}
					if(StringUtils.isBlank(bean.getChassisNoList().get(i))){
						list.add("error.motor.chassisNo.enter" + vechicleRow);
						errorStatus = true;
					}
					else if(StringUtils.isNotBlank(bean.getChassisNoList().get(i)) && !validation.isValidAlphHypen(bean.getChassisNoList().get(i))) {
						list.add("error.motor.chassisNo.valid" + vechicleRow);
						errorStatus = true;
					}
					if(StringUtils.isBlank(bean.getEngineNoList().get(i))){
						list.add("error.motor.engineNo.enter" + vechicleRow);
						errorStatus = true;
					}
					if(StringUtils.isNotBlank(bean.getEngineNoList().get(i)) && !validation.isValidAlphHypen(bean.getEngineNoList().get(i))) {
						list.add("error.motor.engineNo.valid" + vechicleRow);
						errorStatus = true;
					}
					if(StringUtils.isNotBlank(bean.getRegNoList().get(i))&&StringUtils.isNotBlank(bean.getChassisNoList().get(i))&&bean.getRegNoList().get(i).equals(bean.getChassisNoList().get(i))) {
						list.add("error.motor.regNoNEChassisNo" + vechicleRow);
						errorStatus = true;
					}
					if(StringUtils.isNotBlank(bean.getRegNoList().get(i))&&StringUtils.isNotBlank(bean.getEngineNoList().get(i))&&bean.getRegNoList().get(i).equals(bean.getEngineNoList().get(i))) {
						list.add("error.motor.regNoNEEngineNo" + vechicleRow);
						errorStatus = true;
					}
					if(StringUtils.isNotBlank(bean.getChassisNoList().get(i))&&StringUtils.isNotBlank(bean.getEngineNoList().get(i))&&bean.getChassisNoList().get(i).equals(bean.getEngineNoList().get(i))) {
						list.add("error.motor.ChassisNoNEEngineNo" + vechicleRow);
						errorStatus = true;
					}
					/*if(StringUtils.isBlank(bean.getPlateNo1().get(i)) || StringUtils.isBlank(bean.getPlateNo2().get(i)) || StringUtils.isBlank(bean.getPlateNo3().get(i)) || StringUtils.isBlank(bean.getPlateNo4().get(i))) {
						list.add("error.motor.plateCharacter" + vechicleRow);
						errorStatus = true;
					} else{
						bean.getPlateCharacterIdList().set(i, bean.getPlateNo1().get(i)
								+ "-" + bean.getPlateNo2().get(i)
								+ "-" + bean.getPlateNo3().get(i)
								+ "-" + bean.getPlateNo4().get(i));
					}*/
					if(StringUtils.isEmpty(bean.getLeasedYNIdList().get(i))) {
						list.add("error.motor.LeasedYN" + vechicleRow);
						errorStatus = true;
					} else if("Y".equalsIgnoreCase(bean.getLeasedYNIdList().get(i))&&StringUtils.isEmpty(bean.getBankOfFinanceIdList().get(i))) {
						list.add("error.motor.bankOfFinance" + vechicleRow);
						errorStatus = true;
					}
					if(StringUtils.isEmpty(bean.getCubicCapacityList().get(i))) {
						//list.add("error.motor.cubicCapacity" + vechicleRow);
					} else if("invalid".equalsIgnoreCase(validation.validInteger(bean.getCubicCapacityList().get(i)))) {
						list.add("error.motor.cubicCapacity.valid" + vechicleRow);
					} else if(Integer.parseInt(bean.getCubicCapacityList().get(i))<2) {
						list.add("error.motor.cubicCapacity.grETwo" + vechicleRow);
					}
					if(!"0".equals(bean.getNoClaimBonusIdList().get(i)) && !"".equals(bean.getNoClaimBonusIdList().get(i))) {
						/*if(StringUtils.isBlank(bean.getPrevPolicyNoList().get(i))) {
							list.add("error.motor.prevPolicyNo" + vechicleRow);
							errorStatus = true;
						}*/
						if(StringUtils.isBlank(bean.getPrevExpiryDateList().get(i))) {
							/*list.add("error.motor.prevInsExpiryDate" + vechicleRow);
							errorStatus = true;*/
							} else if(!val.IsDateValidationFormat(bean.getPrevExpiryDateList().get(i))) {
								list.add("error.motor.prevInsExpiryDate.invalid" + vechicleRow);
								errorStatus = true;
							}
							if(StringUtils.isBlank(bean.getPrevInsCompanyIdList().get(i))) {
								list.add("error.motor.prevInsCompany" + vechicleRow);
								errorStatus = true;
						}
						/*if(!"13".equalsIgnoreCase(bean.getPrevInsCompanyIdList().get(i))){
							if(Integer.parseInt(dao.getDocumenCount(bean.getQuoteNo(),bean.getVehicleIdList().get(i)))<=0) {
								list.add("error.motor.documents" + vechicleRow);
								}
						}*/
					}
					if(StringUtils.isBlank(bean.getDriverIdList().get(i))) {
						list.add("error.motor.vdriverId" + vechicleRow);
						errorStatus = true;
					}/* else if(!StringUtils.isAlphanumeric(bean.getDriverIdList().get(i))) {
						list.add("error.motor.vdriverId.invalid" + vechicleRow);
						errorStatus = true;
					}*/
					
					if(!errorStatus && "Y".equalsIgnoreCase(bean.getGeneratePolicyYN())) {
						if(((StringUtils.isBlank(bean.getEngineNoList().get(i))&&StringUtils.isBlank(bean.getChassisNoList().get(i)))||(StringUtils.isBlank(bean.getEngineNoList().get(i))&&StringUtils.isBlank(bean.getRegNoList().get(i))||(StringUtils.isBlank(bean.getRegNoList().get(i))&&StringUtils.isBlank(bean.getChassisNoList().get(i)))))) {
							list.add("error.motor.additionalVehicleDetails" + vechicleRow);
							errorStatus = true;
						}
						if(StringUtils.isNotBlank(bean.getRegNoList().get(i))&&StringUtils.isNotBlank(bean.getChassisNoList().get(i))&&bean.getRegNoList().get(i).equals(bean.getChassisNoList().get(i))) {
							list.add("error.motor.regNoNEChassisNo" + vechicleRow);
							errorStatus = true;
						}
						if(StringUtils.isNotBlank(bean.getRegNoList().get(i))&&StringUtils.isNotBlank(bean.getEngineNoList().get(i))&&bean.getRegNoList().get(i).equals(bean.getEngineNoList().get(i))) {
							list.add("error.motor.regNoNEEngineNo" + vechicleRow);
							errorStatus = true;
						}
						if(StringUtils.isNotBlank(bean.getChassisNoList().get(i))&&StringUtils.isNotBlank(bean.getEngineNoList().get(i))&&bean.getChassisNoList().get(i).equals(bean.getEngineNoList().get(i))) {
							list.add("error.motor.ChassisNoNEEngineNo" + vechicleRow);
							errorStatus = true;
						}
						/*if(StringUtils.isBlank(bean.getRegYrIdList().get(i))) {
							list.add("error.motor.regYr" + vechicleRow);
							errorStatus = true;
						}*/
						/*if(StringUtils.isBlank(bean.getInsNameArabic())) {
							list.add("error.motor.insuredNameArabic");
							errorStatus = true;
						}
						if(StringUtils.isBlank(bean.getInsAddressArabic())) {
							list.add("error.motor.insuredAddressArabic");
							errorStatus = true;
						}
						
						if(StringUtils.isBlank(bean.getVehicleRegLocIdList().get(i))) {
							list.add("error.motor.vehicleRegLoc" + vechicleRow);
							errorStatus = true;
						}*/
					}
					if(!errorStatus) { 
					 String	result= updateAddVehicleDetails(bean, i);
						 if("FAILED".equalsIgnoreCase(result)){
							 list.add("Error in Update" + vechicleRow);
						 }
					}
				}
				//Additional Vehicle Details-End
			}
			else if("customerDetails".equalsIgnoreCase(type)) {
				list=new CustomerService().getMotorCustomerValidate(/*bean.getIssuer()*/"",bean.getBrokerCode(),bean.getExecutive(),bean.getTitle(),bean.getCustomerName(),bean.getCity(),bean.getPoBox(),bean.getMobileNo(),bean.getEmail(),bean.getCustLastName(),bean.getCustnrc1(),bean.getCustnrc2(),bean.getCustnrc3(),bean.getCustPassportNo(),bean.getCustdob(),bean.getCustAlterMobileNo(),bean.getCustLandLineNo(),bean.getCustomerType(),bean.getCompanyRegNo(),bean.getAddress1(),bean.getGender(),bean.getOccupation());
			}else if("roadAssistDetails".equalsIgnoreCase(type)){
				if(StringUtils.isBlank(bean.getMobileNo())){
					list.add("Enter Mobile Number");
				}
				else if(!StringUtils.isNumeric(bean.getMobileNo()) || bean.getMobileNo().length()!=10 || !"09".equals(bean.getMobileNo().subSequence(0, 2)) ) {
					list.add("Enter Valid Mobile Number");
				}
				if(StringUtils.isBlank(bean.getPolicyNo())){
					list.add("Enter Policy No");
				}
				if(!"B2C".equalsIgnoreCase(bean.getLoginType())){
					if(StringUtils.isEmpty(bean.getEmail())) {
						list.add("Enter Email Address");
					}
					else if("invalid".equalsIgnoreCase(validation.emailValidate(bean.getEmail()))) {	
						list.add("Email Address Invalid");
					}
				}
				if(StringUtils.isBlank(bean.getCustName())){
					list.add("Enter Customer Name");
				}
				if(StringUtils.isBlank(bean.getAssistantType())){
					list.add("Choose Road Assistant Type");
				}
				if(StringUtils.isBlank(bean.getDesc())){
					list.add("Enter Description");
				}
			}else if("roadAssistfdBack".equalsIgnoreCase(type)){
				if(StringUtils.isEmpty(bean.getFeedBack())){
					list.add("Enter Feed Back");
					}
			}
			else if("paymentDetails".equalsIgnoreCase(type)) {
				list = new PaymentService().validatePayment(bean.getModeOfPayment(),bean.getChequeNo(),bean.getChequeDate(),bean.getChequeAmount(),bean.getTotalPremium(),bean.getBankName(),bean.getMicrCode(),bean.getCashDepositBank(),bean.getCashAmount(),bean.getCashChellanNo(),bean.getCashInstrumentDate(),bean.getInstallmentYN(),bean.getInsIntialAmount(),"101".equalsIgnoreCase(bean.getModeOfPayment())?bean.getMtnMobileNo():bean.getAirtelMoneyNumber());
				if(list.isEmpty()){
					boolean stat=dao.checkCustomerType(bean);
					if(!stat){
						list.add("This Quote is not Valid due to Customer Type Change, Please Proceed From Here");
						bean.setMode("custType");
					}
				}
				
			}	else if("captcha".equalsIgnoreCase(type)){
				String error = validateCaptcha(bean.getCaptchavalue(),bean.getCaptcha());
				if(!"".equals(error))
					list.add(error);
			}
			else if("otpVerfiy".equalsIgnoreCase(type)) {
				 list=new OTPGenerator().getValidate(bean.getOtp(),bean.getOtpId(),bean.getMailOtp());
				}
			else if("editQuoteQE".equalsIgnoreCase(type)) {
				boolean stat=dao.checkCustomerType(bean);
				if(!stat)
					list.add("This Quote is not Valid due to Customer Type Change, Please Proceed From Here");
			}
		} catch(Exception e) {
			LogManager.debug(e);
			e.printStackTrace();
			list.add("error.validation");
		}
		return list;
	}

	/*public List<CoverageBean> getOptCoverList(String applicationNo) {
		List<CoverageBean> list=null;//dao.getCoveragesNames(applicationNo);
		if(list!=null && list.size()>0){
    		for(int i=0;i<list.size();i++){
    			CoverageBean covBean = list.get(i);
    			DocUploadService clmService = new DocUploadService();
    		//	covBean.setCoveragesList(dao.getCoverages(applicationNo, covBean.getSchemeId()));
    		}
    	}
		return list;
	}*/

	public String getUpdatePremiumInfo(MotorBean bean) {
			return dao.getUpdatePremiumInfo(bean);		
	}
	public List<Map<String,Object>> getHelpInfoList(String helpType, String branchCode) {
		return dao.getHelpInfoList(helpType, branchCode);
	}
	
	public String updateAddVehicleDetails(MotorBean bean, int index) {
		return dao.updateAddVehicleDetails(bean, index);
	}

	/*public void updatePaymentDetails(MotorBean bean) {
		dao.updatePaymentDetails(bean);
	}*/
	public List<Map<String,Object>> getMultiVehicleDetails(MotorBean bean) {
		return dao.getMultiVehicleDetails(bean);
	}
	public void deleteVehicleIdDetails(MotorBean bean) {
		dao.deleteVehicleIdDetails(bean);
	}
	public void editVehicleIdDetails(MotorBean bean) {
		dao.editVehicleIdDetails(bean);
	}
	public void updSelectedPolicyDtls(MotorBean bean) {
		dao.updSelectedPolicyDtls(bean);
	}
	public void updateCoveragesInfo(String applicationNo, String branchCode, String productId, String endtTypeId, String loginId, String type, String userType, String vehicleId,String referal) {
		dao.updateCoveragesInfo(applicationNo, branchCode, productId, endtTypeId, loginId, type, userType, vehicleId,referal);
	}
	/*public void getMotorCustomerDetails(MotorBean bean, String customerId) {
		dao.getMotorCustomerDetails(bean,customerId);
	}*/
	public Map<String,Object> getThirdPartyPremiumInfo(String applicationNo) {
		return dao.getThirdPartyPremiumInfo(applicationNo);
	}
	public Map<String,Object> getVehicleDetailsById(String applicationNo, String productId, String branchCode, String vehicleId) {
		return dao.getVehicleDetailsById(applicationNo,productId,branchCode,vehicleId);
	}
	public void insertDocumentDetails(String applicationNo, String quoteNo, String vehicleId, String documentId, String documentPath) {
		dao.insertDocumentDetails(applicationNo, quoteNo, vehicleId, documentId, documentPath);
	}
	public Map<String,String> getDocumentDetails(String applicationNo, String vehicleId) {
		return dao.getDocumentDetails(applicationNo,vehicleId);
	}
	public List<Map<String,Object>> getDocumentList(String applicationNo) {
		return dao.getDocumentList(applicationNo);
	}
	public String getReferralMsgs(String applicationNo) {
		return dao.getReferralMsgs(applicationNo);
	}
	public String getPolicyReferralMsgs(String applicationNo) {
		return dao.getPolicyReferralMsgs(applicationNo);
	}
	public String getSelectedPolicyType(String applicationNo) {
		return dao.getSelectedPolicyType(applicationNo);
	}
	public String getPaymentYN(String applicationNo) {
		return dao.getPaymentYN(applicationNo);
	}
	public void insertElectric(MotorBean bean) {
		 dao.insertElectric(bean);
	}

	public void insertNonElectric(MotorBean bean) {
		 dao.insertNonElectric(bean);	
	}

	public void getElectrical(MotorBean bean) {
		dao.getElectrical(bean);	
	}

	public void getnelectricalPopup(MotorBean bean) {
		dao.getnelectricalPopup(bean);
	}
	public List<Map<String,Object>> getVehicleTypeDetails(String make, String model, String branchCode, String applicationNo,String vehicleId) {
		return dao.getVehicleTypeDetails(make,model,branchCode,applicationNo,vehicleId);
	}
	public String validateCaptcha(String captchavalue, Captcha captcha) {
		String error = "";
		try {
			if (!captcha.isCorrect(captchavalue)) 
				error = "error.captchacode.invalid";
		} catch (Exception e) {
			//e.printStackTrace();
		}
		return error;
	}
	public void updateTPLiablity(MotorBean bean) {
		dao.updateTPLiablity(bean);
	}
	
	public void getAdditionalVehicleDetails(MotorBean bean) {
		try {
			List<Map<String,Object>> multiVehicleList = getMultiVehicleDetails(bean);
			List<String> regNoList = new ArrayList<String>();
			List<String> chassisNoList = new ArrayList<String>();
			List<String> engineNoList = new ArrayList<String>();
			List<String> vehicleColourIdList = new ArrayList<String>();
			/*List<String> vehicleRegLocIdList = new ArrayList<String>();*/
			/*List<String> plateCharacterIdList = new ArrayList<String>();*/
			List<String> leasedYNIdList = new ArrayList<String>();
			List<String> bankOfFinanceIdList = new ArrayList<String>();
			List<String> insNameArabicList = new ArrayList<String>();
			List<String> insAddressArabicList = new ArrayList<String>();
			List<String> driverIdList = new ArrayList<String>();
			List<String> cubicCapacityList = new ArrayList<String>();
			List<String> noClaimBonusIdList = new ArrayList<String>();
			List<String> prevPolicyNoList = new ArrayList<String>();
			List<String> prevInsCompanyIdList = new ArrayList<String>();
			List<String> prevExpiryDateList = new ArrayList<String>();
			List<String> vehicleIdList = new ArrayList<String>();
						
			for(int i=0; i<multiVehicleList.size() ; i++) {
				Map<String,Object> tempMap = multiVehicleList.get(i);
				regNoList.add(tempMap.get("REGISTRATION_NO")==null?"":tempMap.get("REGISTRATION_NO").toString());
				chassisNoList.add(tempMap.get("CHASSIS_NO")==null?"":tempMap.get("CHASSIS_NO").toString());
				engineNoList.add(tempMap.get("ENGINE_NUMBER")==null?"":tempMap.get("ENGINE_NUMBER").toString());
				vehicleColourIdList.add(tempMap.get("VEHICLE_COLOR_ID")==null?"":tempMap.get("VEHICLE_COLOR_ID").toString());
				/*vehicleRegLocIdList.add(tempMap.get("REGISTER_LOCATION_ID")==null?"":tempMap.get("REGISTER_LOCATION_ID").toString());*/
				leasedYNIdList.add(tempMap.get("LEASED")==null?"N":tempMap.get("LEASED").toString());
				bankOfFinanceIdList.add(tempMap.get("BANK_ID")==null?"":tempMap.get("BANK_ID").toString());
				insNameArabicList.add(tempMap.get("INSURED_NAME_ARABIC")==null?"":tempMap.get("INSURED_NAME_ARABIC").toString());
				insAddressArabicList.add(tempMap.get("INSURED_ADDRESS_ARABIC")==null?"":tempMap.get("INSURED_ADDRESS_ARABIC").toString());
				driverIdList.add(tempMap.get("DRIVER_ID")==null?"":tempMap.get("DRIVER_ID").toString());
				cubicCapacityList.add(tempMap.get("CUBIC_CAPACITY")==null?"":tempMap.get("CUBIC_CAPACITY").toString());
				noClaimBonusIdList.add(tempMap.get("NO_CLAIM_BONUS")==null?"":tempMap.get("NO_CLAIM_BONUS").toString());
				prevPolicyNoList.add(tempMap.get("PREV_POLICYNO")==null?"":tempMap.get("PREV_POLICYNO").toString());
				prevInsCompanyIdList.add(tempMap.get("INS_COMPANY")==null?"":tempMap.get("INS_COMPANY").toString());
				prevExpiryDateList.add(tempMap.get("PREV_POLICYEXPIRYDATE")==null?"":tempMap.get("PREV_POLICYEXPIRYDATE").toString());
				vehicleIdList.add(tempMap.get("VEHICLE_ID")==null?"":tempMap.get("VEHICLE_ID").toString());
			}
			
			bean.setRegNoList(regNoList);
			bean.setChassisNoList(chassisNoList);
			bean.setEngineNoList(engineNoList);
			bean.setVehicleColourIdList(vehicleColourIdList);
			bean.setLeasedYNIdList(leasedYNIdList);
			bean.setBankOfFinanceIdList(bankOfFinanceIdList);
			bean.setDriverIdList(driverIdList);
			bean.setCubicCapacityList(cubicCapacityList);
			bean.setNoClaimBonusIdList(noClaimBonusIdList);
			bean.setPrevPolicyNoList(prevPolicyNoList);
			bean.setPrevInsCompanyIdList(prevInsCompanyIdList);
			bean.setPrevExpiryDateList(prevExpiryDateList);
			bean.setVehicleIdList(vehicleIdList);
		}
		catch(Exception exception) {
			LogManager.debug(exception);
		}
	}
		
	public  void removeVehicleBean(MotorBean bean, boolean hasActionErrors) {
		if(hasActionErrors) {
			bean.setMake(bean.getMakeIdList().get(0));
			bean.setModel(bean.getModelIdList().get(0));
			bean.setSeatingCapacity(bean.getSeatingCapacityList().get(0));
			bean.setTypeBody(bean.getTypeBodyIdList().get(0));
			bean.setVehicleUsage(bean.getVehicleUsageIdList().get(0));
		} else {
			bean.setMakeIdList(null);
			bean.setModelIdList(null);
			bean.setTypeBodyIdList(null);
			bean.setMfgYrIdList(null);
			bean.setSumInsuredList(null);
			/*bean.setNoOfCylinderIdList(null);*/
			bean.setSeatingCapacityList(null);
			bean.setVehicleUsageIdList(null);
			bean.setVehicleUsageNameList(null);
			bean.setTypeBodyNameList(null);
			/*bean.setAreaCoverageIdList(null);*/
			bean.setDriverDOBList(null);
			bean.setDriverIdList(null);
			bean.setNoClaimBonusIdList(null);
			bean.setClaimYNIdList(null);
			bean.setClaimAmountList(null);
			bean.setDeductibleIdList(null);
			bean.setElectricalAccList(null);
			bean.setNonElectricalAccList(null);
			bean.setOwnerDriverYNList(null);
			bean.setVehicleIdList(null);
			bean.setRegNoList(null);
			bean.setChassisNoList(null);
			bean.setEngineNoList(null);
		}
	}
	
	public List<Map<String,Object>> getConditionClausesList(String quoteNo, String productId, String branchCode) {
		return dao.getConditionClausesList(quoteNo, productId, branchCode);
	}

	public List<Map<String, Object>> getMototVehicleDetails(String quoteNo) {
		return dao.getMototVehicleDetails(quoteNo);
	}
	
	public void insUploadImgDetail(MotorBean bean) {
		dao.insUploadImgDetail(bean);
	}
	
	public void insUploadImgDocument(MotorBean bean) {
		bean.setFilePath(bean.getWebRootPath()+"MobileUploadedImages/" + bean.getFileName());
		dao.insUploadImgDocument(bean);
	}

	public String getRefNo() {
		return dao.getRefNo();
	}

	public void deleteinstallment(MotorBean bean) {
			dao.deleteinstallment(bean);
	}
	public int installmentcount(MotorBean bean) {
		return dao.installmentcount(bean);
	}

	public List<Object> getNotifyList() {
		return dao.getNotifyList();
	}
	
	public List<Map<String,Object>> getTypeofAssistantList() {
		return dao.getTypeofAssistantList();
	}
	
	public List<Map<String,Object>> getRAList(MotorBean bean) {
		return dao.getRAList(bean);
	}

	public void insRoadAssistantDetail(MotorBean bean) {
		dao.insRoadAssistantDetail(bean);
	}

	public void getInstallmentDetail(MotorBean bean) {
		dao.getInstallmentDetail(bean);		
	}

	public void updRAFeedBack(MotorBean bean) {
		dao.updRAFeedBack(bean);
	}
	
	/*public List<Object> getPolicyNoList() {
		return dao.getPolicyNoList();
	}*/
	public String getEmailCount(MotorBean bean) {
		return dao.getEmailCount(bean);
	}

	public String getLoginCount(MotorBean bean, String type) {
		return dao.getLoginCount(bean,type);
	}

	public int getUpdateCustDtl(MotorBean bean, String type) {
		return dao.getUpdateCustDtl(bean,type);
	}

	public void setCustDetail(MotorBean bean,String type) {
		dao.setCustDetail(bean,type);
	}

	public String getCurrencyType(MotorBean bean) {
		return dao.getCurrencyType(bean);
	}

	public List<Map<String, Object>> getPolicyTypeList(MotorBean bean) {
		return dao.getPolicyTypeList(bean);
	}

	public List<Map<String, Object>> getOptionalCovers(MotorBean bean) {
		return dao.getOptionalCovers(bean);
	}

	public int updateDriverDetails(MotorBean bean) {
		return dao.updateDriverDetails(bean);
	}
	
	public List<Map<String, Object>> getCustomerList(MotorBean bean, String type, String userId) {
		return dao.getCustomerList(bean,type,userId);
	}
	
	public int updateVehicleDetials(MotorBean bean) {
		return dao.updateVehiclePolicyDtl(bean);
	}

	public List<Map<String, Object>> getVehicleDetailsByIdNew(String applicationNo, String productId, String branchCode,String deleteVehicleId) {
		return dao.getVehicleDetailsByIdNew(applicationNo,productId,branchCode,deleteVehicleId);
	}

	public String getGeratePolicyNew(MotorBean bean) {
		return dao.getGeratePolicyNew(bean);
	}

	public Map checkPolicy(MotorBean bean) {
		return dao.checkPolicy(bean);
	}

	public String getLabelInfo(MotorBean bean, boolean hasActionErrors) {
		return dao.getLabelInfo(bean, hasActionErrors);
	}

	public String updatePolDate(MotorBean bean) {
		return dao.updatePolDate(bean);
	}

	public int vehCount(String applicationNo) {
		return dao.vehCount(applicationNo);
	}
	
	public String updateCovRateVeh(MotorBean bean) {
		return dao.updateCovRateVeh(bean);	
	}

	public List<Map<String, Object>> getConditionClausesList(MotorBean bean) {
		return dao.getConditionClausesList(bean);
	}

	public int addConditionDetail(MotorBean bean) {
		return dao.addConditionDetail(bean);
	}

	public int addChooseConditionDetail(MotorBean bean) {
		return dao.addChooseConditionDetail(bean);
	}

	public List<Map<String, Object>> getConditionClausesListRemain(MotorBean bean) {
		return dao.getConditionClausesListRemain(bean);
	}

	public int insertConditionDetail(MotorBean bean) {
		return dao.insertConditionDetail(bean);
	}

	public List<Map<String, Object>> getConditionClausesEdit(MotorBean bean) {
		return dao.getConditionClausesEdit(bean);
	}

	public void updateReferralRemarks(String applicationNo, String referralRemarks) {
		dao.updateReferralRemarksNew(applicationNo,referralRemarks);
	}

	public List<Map<String, Object>> getDeductibleClausesListRemain(MotorBean bean) {
		return dao.getDeductibleClausesListRemain(bean);
	}

	public List<Map<String, Object>> getDeductibleClausesEdit(MotorBean bean) {
		return dao.getDeductibleClausesEdit(bean);
	}

	public int addDeductibleDetail(MotorBean bean) {
		return dao.addDeductibleDetail(bean);
	}

	public int addChooseDeductibleDetail(MotorBean bean) {
		return dao.addChooseDeductibleDetail(bean);
	}

	public int insertDeductibleDetail(MotorBean bean) {
		return dao.insertDeductibleDetail(bean);
	}

	public List<Map<String, Object>> getDeductibleClausesList(MotorBean bean) {
		return dao.getDeductibleClausesList(bean);
	}

	public void getCustomerId(MotorBean bean) {
		dao.getCustomerId(bean);
	}

	public void setCustomerDetailsB2B(MotorBean bean) {
		dao.setCustomerDetailsB2B(bean);
	}

	public int updateCustomerType(MotorBean bean) {
		return dao.updateCustomerType(bean);
	}

	public void premiumInfoNew(MotorBean bean) {
		dao.premiumInfoNew(bean);
	}
	
	public void getQuoteInfo(MotorBean bean) {
		dao.getQuoteInfo(bean);
	}

	public String getCustomerType(MotorBean bean) {
		return dao.getCustomerType(bean);
	}

	public List<Map<String,Object>> getDriverDetails(MotorBean bean) {
		return dao.getDriverDetails(bean);
		
	}

	public List<Map<String, Object>> getVehicleDetailsByIdNew(MotorBean bean) {
		return dao.getVehicleDetailsByIdNew(bean);
	}

	public String getBuypolicy(MotorBean bean) {
		return dao.getBuypolicy(bean);
		
	}

	public void validatePolicy(MotorBean bean) {
		dao.validatePolicy(bean);
		
	}

	public void updateQuoteDetailsNew(MotorBean bean) {
		dao.updateQuoteDetailsNew(bean);
		
	}

	public List<Map<String, Object>> getPaymentList(MotorBean bean) {
		return dao.getPaymentList(bean);
	}

	public List<Map<String,Object>> makepay(MotorBean bean) {
		return dao.makepay(bean);
		
	}
}
