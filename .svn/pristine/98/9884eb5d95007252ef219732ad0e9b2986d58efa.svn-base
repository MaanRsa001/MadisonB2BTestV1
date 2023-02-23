package com.maan.Home.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import nl.captcha.Captcha;

import org.apache.commons.lang3.StringUtils;

import proj.date.DateFunction;

import com.maan.Home.Controller.HomeBean;
import com.maan.Home.DAO.HomeDAO;
import com.maan.Motor.controller.MotorBean;
import com.maan.common.LogManager;
import com.maan.common.Validation;
import com.maan.common.otp.OTPGenerator;
import com.maan.common.util.StringUtil;
import com.maan.customer.dao.CustomerDAO;
import com.maan.customer.service.CustomerService;
import com.maan.payment.PaymentService;
import com.maan.services.util.ValidationFormat;

public class HomeService{
	final HomeDAO dao=new HomeDAO();

	public double getPremium(String baserateSts,double baserateVal,String sum_insured,String value) {
		return dao.getPremium(baserateSts,baserateVal,sum_insured,value);
	}

	public double insertMasterPremiumInfo(HomeBean bean, List<Object> list) {
		return dao.insertMasterPremiumInfo(bean, list);
	}

	public double insertOptionalPremiumInfo(HomeBean bean, List<Object> list) {
		return dao.insertOptionalPremiumInfo(bean,list);
	}

	/*public double updatePremium(HomeBean bean,double total_premium,String referral,String userType) {
		return dao.updatePremium(bean,total_premium,referral,userType);
	}*/

	public String getQuoteInfo(HomeBean bean) {
		return dao.getQuoteInfo(bean);
	}
	public void getOFSData(HomeBean bean) {
		dao.getOFSData(bean);
	}
	public List<Map<String,Object>> getHomeInfo(HomeBean bean){
		return dao.getHomeInfo(bean);
	}
	
	public List<Map<String,Object>> getHomePremiumInfo(HomeBean bean){
		return dao.getHomePremiumInfo(bean);
	}

	public List<Map<String,Object>> getUploadCoverList(HomeBean bean){
		return dao.getUploadCoverList(bean);
	}

	public List<Map<String,Object>> subUploadCoverList(HomeBean bean){
		return dao.subUploadCoverList(bean);
	}

	public List<Map<String,Object>> getSubHomeInfo(HomeBean bean){
		return dao.getSubHomeInfo(bean);
	}

	public List<Object> getPremiumInfo(HomeBean bean){
		return dao.getPremiumInfo(bean);
	}

	/*public List<Map<String, Object>> premiumSummary(final HomeBean bean){
		return dao.premiumSummary(bean);
	}*/

	public List<Map<String, Object>> personalInfo(final HomeBean bean){
		return dao.personalInfo(bean);
	}

	/*public List<Map<String, Object>> customerPersonalInfo(final HomeBean bean){
		return dao.customerPersonalInfo(bean);
	}*/

	public List<Map<String, Object>> getListValue(String lkey, String lvalue, String tname){
		return dao.getListValue(lkey, lvalue, tname);
	}

	public List<Map<String, Object>> getSubList(final HomeBean bean){
		return dao.getSubList(bean);
	}

	public List<Map<String, Object>> getCoverageList(final HomeBean bean){
		return dao.getCoverageList(bean);
	}

	public int saveCoverages(final HomeBean bean){
		return dao.saveCoverages(bean);
	}

	public List<Object> getUWMenu(final HomeBean bean){
		return dao.getUWMenu(bean);
	}

	public List<Map<String,Object>> getBusinessTypeList(String SchemeAppCode) {
		return dao.getBusinessTypeList(SchemeAppCode);
	}

	public List<Object>getTypeClaimList(final HomeBean bean){
		return dao.getTypeClaimList(bean);
	}

	public List<Map<String, Object>> getUWDetails(final HomeBean bean){
		return dao.getUWDetails(bean);
	}

	public void saveUWDetails(final HomeBean bean){
		dao.saveUWDetails(bean);
	}

	public String getReferalYNStatus(final HomeBean bean){
		return dao.getReferalYNStatus(bean);
	}

	/*public String getGeneratePolicy(final HomeBean bean){
		return dao.getGeneratePolicy(bean);
	}*/

	public List<Object> getPolicyInformation(String quoteNo) {
		return dao.getPolicyInformation(quoteNo);
	}

	/*public String getPolicyFees(final String quoteNo, final String userType, final String menuType, final String branchCode){
		return dao.getPolicyFees(quoteNo, userType, menuType, branchCode);
	}*/

	public List<Object> getHelpInfo(final HomeBean bean){
		return dao.getHelpInfo(bean);
	}

	public String adminRefstage(final HomeBean bean){
		return dao.adminRefstage(bean);
	}

	public List<Map<String,Object>> getBaseRate(String mainCover, String subCover, String productid, String schemeid, String contentTypeId, String sum){
		return dao.getBaseRate( mainCover, subCover, productid, schemeid, contentTypeId, sum);
	}

	public boolean getFormulaCount(String proid, String schemeId, String coverid){
		return dao.getFormulaCount(proid, schemeId, coverid);
	}

	public String getFormulaSum(String proid, String schemeId, String coverid, String quoteno){
		return dao.getFormulaSum(proid, schemeId, coverid, quoteno);
	}

	public List<Object> getSubCoverageDetails(final HomeBean bean) {
		return dao.getSubCoverageDetails(bean);
	}

	public double updatePremium(double total_premium,String referral,String userType, String quoteNo, String productId, String schemeId, String contentTypeId,String menuType,String branchCode) {
		return dao.updatePremium(total_premium,referral,userType,quoteNo,productId,schemeId,contentTypeId,menuType,branchCode);
	}

	public void getClaimExperience(String linkfrom, final HomeBean bean) {
		dao.getClaimExperience(linkfrom, bean);
	}

	public String[] getReferalDetailInfo(String linkfrom, final HomeBean bean){
		return dao.getReferalDetailInfo(linkfrom, bean);
	}

	public List<Map<String,Object>> getSubCoverageWithQuote(final HomeBean bean, String linkfrom){
		return dao.getSubCoverageWithQuote(bean, linkfrom);
	}

	public List<Map<String,Object>> getMainCoverageWithQuote(final HomeBean bean,String linkfrom) {
		return dao.getMainCoverageWithQuote(bean, linkfrom);
	}

	public String getHomeCommision(final double premium,final String appNo, final HomeBean bean){
		return dao.getHomeCommision(premium, appNo, bean);
	}

	public void savePremiumInfo(final HomeBean bean){
		dao.savePremiumInfo(bean);
	}

	public List<Object> gmDetails(final HomeBean bean) {
		return dao.gmDetails(bean);
	}

	public List<Map<String,Object>> subPremium(final HomeBean bean){
		return dao.subPremium(bean);
	}

	public List<Map<String,Object>> getUWMaster(final HomeBean bean){
		return dao.getUWMaster(bean);
	}
	public void adminScheme(final HomeBean bean){
		dao.adminScheme(bean);
	}
	public String updReferralRemarks(String referralRemarks, String quoteNo, String issuer, String type){
		return dao.updReferralRemarks(referralRemarks, quoteNo,issuer,type);
	}
	public LinkedList<String> getValidate(HomeBean bean,String type){
		LinkedList<String> list=new LinkedList<String>();
		try{
			Validation validation=new Validation();
			if("customerInfo".equalsIgnoreCase(type)) {
				/*list=new CustomerService().getCustomerValidate(bean.getIssuer(),bean.getBrokerCode(),bean.getExecutive(),
						bean.getTitle(),bean.getCustomerName(),bean.getCity(),bean.getPoBox(),bean.getMobileNo(),
						bean.getEmail(),bean.getAddress1());*/
				list=new CustomerService().getMotorCustomerValidate(bean.getIssuer(),bean.getBrokerCode(),bean.getExecutive(),bean.getTitle(),bean.getCustomerName(),bean.getMobileNo(),bean.getEmail(),bean.getCustomerType(),bean.getCompanyRegNo());
			} else if("getQuote".equalsIgnoreCase(type) || "getSave".equalsIgnoreCase(type)){
				//Cover Start Date-Start
				/*if(StringUtils.isEmpty(bean.getPolicyStartDate())&&!"getSave".equalsIgnoreCase(type)){
					list.add("error.motor.policyStartDate");
				}
				else if(StringUtils.isBlank(bean.getEndTypeId())) {
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
						backDate = new com.maan.common.dao.CommonDAO().getBackDatesAllowed((String)bean.getLoginId(),(String)bean.getUserType(),bean.getProductId(),bean.getBranchCode(), bean.getSchemeId());
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
				}*/
				//Cover Start Date-End
				//Vehicle Details-Start
				if(StringUtils.isEmpty(bean.getMake())) {
					list.add("error.motor.make");
				}
				if(StringUtils.isEmpty(bean.getModel())) {
					list.add("error.motor.model");
				}
				if(StringUtils.isEmpty(bean.getTypeBody())) {
					list.add("error.motor.typeBody");
				}
				if(StringUtils.isBlank(bean.getManufactureDate())) {
					list.add("error.motor.manufactureDate");
				}
				if(StringUtils.isBlank(bean.getSumInsured())) {
					list.add("error.motor.sumInsured");
				}
				if(StringUtil.isBlankOrNull(bean.getMotorLampsYN())) {
					list.add("error.motor.motorLampsYN");
				}
				if(StringUtils.isEmpty(bean.getAreaCoverage())) {
					list.add("error.motor.areaCoverage");
				}
				if(StringUtils.isBlank(bean.getMotorPolicyType())) {
					list.add("error.motor.motorPolicyType");
				}
				if("1".equals(bean.getNoOfTrailer()) || "2".equals(bean.getNoOfTrailer())) {
					if(StringUtils.isBlank(bean.getTrailerIdv1())) {
						list.add("error.motor.trailerIdv1");
					}
					if(StringUtils.isBlank(bean.getTrailer1MfrDate())) {
						list.add("error.motor.trailer1MfrDate");
					}
				}
				else {
					bean.setTrailer1MfrDate("");
				}
				if("2".equals(bean.getNoOfTrailer())) {
					if(StringUtils.isBlank(bean.getTrailerIdv2())) {
						list.add("error.motor.trailerIdv2");
					}
					if(StringUtils.isBlank(bean.getTrailer2MfrDate())) {
						list.add("error.motor.trailer2MfrDate");
					}
				}
				else {
					bean.setTrailer2MfrDate("");
				}
				if(StringUtils.isBlank(bean.getSeatingCapacity())) {
					list.add("error.motor.seatingCapacity");
				}
				else if("invalid".equalsIgnoreCase(validation.validInteger(bean.getSeatingCapacity()))) {
					list.add("error.motor.seatingCapacity.valid");
				}
				else if(Integer.parseInt(bean.getSeatingCapacity())<2) {
					list.add("error.motor.seatingCapacity.grETwo");
				}
				if(StringUtils.isBlank(bean.getVehicleUsage())) {
					list.add("error.motor.vehicleUsage");
				}
				if(StringUtils.isBlank(bean.getVoluntaryDeductible())) {
					list.add("error.motor.VoluntaryDeductible");
				}
				/*if(StringUtils.isBlank(bean.getElectricalAcc())) {
					list.add("error.motor.electricalAcc");
				}
				if(StringUtils.isBlank(bean.getNonElectricalAcc())) {
					list.add("error.motor.nonElectricalAcc");	
				}
				if(StringUtils.isBlank(bean.getBifuelKit())) {
					list.add("error.motor.bifuelKit");	
				}*/
				//Vehicle Details-End
				//Additional Vehicle Details-Start
				boolean errorStatus = false;
				//String vechicleRow = "#in vehicle " + (i+1);
				if(StringUtils.isNotBlank(bean.getRegNo())&&StringUtils.isNotBlank(bean.getChassisNo())&&bean.getRegNo().equals(bean.getChassisNo())) {
					list.add("error.motor.regNoNEChassisNo");
					errorStatus = true;
				}
				if(StringUtils.isNotBlank(bean.getRegNo())&&StringUtils.isNotBlank(bean.getEngineNo())&&bean.getRegNo().equals(bean.getEngineNo())) {
					list.add("error.motor.regNoNEEngineNo");
					errorStatus = true;
				}
				if(StringUtils.isNotBlank(bean.getChassisNo())&&StringUtils.isNotBlank(bean.getEngineNo())&&bean.getChassisNo().equals(bean.getEngineNo())) {
					list.add("error.motor.ChassisNoNEEngineNo");
					errorStatus = true;
				}
				if(!errorStatus) {
					if((StringUtils.isBlank(bean.getEngineNo())&&StringUtils.isBlank(bean.getChassisNo()))||(StringUtils.isBlank(bean.getEngineNo())&&StringUtils.isBlank(bean.getRegNo()))||(StringUtils.isBlank(bean.getRegNo())&&StringUtils.isBlank(bean.getChassisNo()))) {
						list.add("error.motor.additionalVehicleDetails");
						errorStatus = true;
					}
					if(StringUtils.isNotBlank(bean.getRegNo())&&StringUtils.isNotBlank(bean.getChassisNo())&&bean.getRegNo().equals(bean.getChassisNo())) {
						list.add("error.motor.regNoNEChassisNo");
						errorStatus = true;
					}
					if(StringUtils.isNotBlank(bean.getRegNo())&&StringUtils.isNotBlank(bean.getEngineNo())&&bean.getRegNo().equals(bean.getEngineNo())) {
						list.add("error.motor.regNoNEEngineNo");
						errorStatus = true;
					}
					if(StringUtils.isNotBlank(bean.getChassisNo())&&StringUtils.isNotBlank(bean.getEngineNo())&&bean.getChassisNo().equals(bean.getEngineNo())) {
						list.add("error.motor.ChassisNoNEEngineNo");
						errorStatus = true;
					}
				}
				if(StringUtils.isEmpty(bean.getLeasedYN())) {
					list.add("error.motor.LeasedYN");
				}
				else if("Y".equalsIgnoreCase(bean.getLeasedYN()) && "N".equalsIgnoreCase(bean.getLtFinanceYN()) && StringUtils.isEmpty(bean.getBankOfFinance())) {
					list.add("error.motor.bankOfFinance");
				}
				if(("1".equals(bean.getNoOfTrailer()) || "2".equals(bean.getNoOfTrailer())) && StringUtils.isBlank(bean.getTrailer1RegNo())) {
					list.add("error.motor.trailer1RegNo");
				}
				if("2".equals(bean.getNoOfTrailer()) && StringUtils.isBlank(bean.getTrailer2RegNo())) {
					list.add("error.motor.trailer2RegNo");
				}
				//Additional Vehicle Details-End
				//PA Cover - Start
				if(StringUtils.isBlank(bean.getOwnerDriver())) {
					list.add("error.motor.ownerDriver");
				}
				if(StringUtils.isBlank(bean.getUnNamedPassengersNos()) && StringUtils.isNotBlank(bean.getUnNamedPassengersSi())) {
					list.add("error.motor.unNamedPassengersNos");
				}
				if(StringUtils.isNotBlank(bean.getUnNamedPassengersNos()) && StringUtils.isBlank(bean.getUnNamedPassengersSi())) {
					list.add("error.motor.unNamedPassengersSi");
				}
				if(StringUtils.isBlank(bean.getPaidDriversNos()) && StringUtils.isNotBlank(bean.getPaidDriversSi())) {
					list.add("error.motor.paidDriversNos");
				}
				if(StringUtils.isNotBlank(bean.getPaidDriversNos()) && StringUtils.isBlank(bean.getPaidDriversSi())) {
					list.add("error.motor.paidDriversSi");
				}
				//PA Cover - End
				//Legal Liability - Start
				if(StringUtils.isNotBlank(bean.getDriverCondCleaner()) && !StringUtils.isNumeric(bean.getDriverCondCleaner())) {
					list.add("error.motor.driverCondCleaner.invalid");
				}
				if(StringUtils.isNotBlank(bean.getNonFarePassenger()) && !StringUtils.isNumeric(bean.getNonFarePassenger())) {
					list.add("error.motor.nonFarePassenger.invalid");
				}
				else if(StringUtils.isNotBlank(bean.getNonFarePassenger()) && Integer.parseInt(bean.getNonFarePassenger())>3) {
					list.add("error.motor.nonFarePassenger.invalidsize");
				}
				if(StringUtils.isNotBlank(bean.getEmployeesInOperation()) && !StringUtils.isNumeric(bean.getEmployeesInOperation())) {
					list.add("error.motor.employeesInOperation.invalid");
				}
				//Legal Liability - End
			}
			else if("PA_INSURED_DETAILS_LIST".equalsIgnoreCase(type)) {
				List<Map<String,Object>> paInsuredDtlsList = getPaInsuredDetailsList(bean.getApplicationNo());
				if(paInsuredDtlsList==null || paInsuredDtlsList.size()==0) {
					list.add("error.pa.insuredDetails");
				}
				else {
					ValidationFormat val = new ValidationFormat();

					String emptyRows="";

					int selfcount = 0,no_of_father = 0,no_of_mother = 0;;

					List<Integer> tages = new ArrayList<Integer>();

					for(int i=0 ; i<paInsuredDtlsList.size() ; i++)
					{
						Map<String,Object> paInsuredDtlsMap = paInsuredDtlsList.get(i);
						String insuredName = paInsuredDtlsMap.get("INSURED_NAME")==null?"":paInsuredDtlsMap.get("INSURED_NAME").toString();
						String dob = paInsuredDtlsMap.get("DOB")==null?"":paInsuredDtlsMap.get("DOB").toString();
						String relationShip = paInsuredDtlsMap.get("RELATIONSHIP")==null?"":paInsuredDtlsMap.get("RELATIONSHIP").toString();

						//NAME VALIDATION
						if(StringUtils.isNotBlank(insuredName))
						{
							if(!val.validateStringWithSpace(insuredName)){
								list.add("error.pa.Name.valid#"+(i+1)+"#");
							}
						}
						else if(StringUtils.isBlank(insuredName)){
							list.add("error.pa.Name#"+(i+1)+"#");
						}else
						{
							if(list.contains("error.pa.emptyRowFill#"+emptyRows+"#"))
								emptyRows="";
							emptyRows+=(i+1)+",";
						}

						//DATE OF BIRTH VALIDATION
						int age=-1;
						if(StringUtils.isNotBlank(dob))
						{
							if(!val.IsDateValidationFormat(dob)){
								list.add("error.pa.dob.valid#"+(i+1)+"#");
							}else if(!val.sysDateValidation(dob))
							{
								list.add("error.pa.dob.validDate#"+(i+1)+"#");
							}else
							{
								age=new com.maan.common.dao.CommonDAO().getCalculatedAge(dob);
								if(age>100)
								{
									list.add("error.pa.age.valid#"+(i+1)+"#");
								}
								if(i>tages.size()&&!list.contains("error.pa.emptyRowFill#"+emptyRows+"#")){
									list.add("error.pa.emptyRowFill#"+emptyRows+"#");
								}
								tages.add(age);
							}
						}
						else if(StringUtils.isBlank(dob))
						{	list.add("error.pa.dob#"+(i+1)+"#"); }

						//RELATION VALIDATION
						if(StringUtils.isBlank(relationShip)){
							list.add("error.pa.relation#"+(i+1)+"#");
						} else {
							if("1".equals(relationShip)) {
								selfcount++;
							} else if("2".equals(relationShip) || "3".equals(relationShip)) {
								
							} else if("8".equals(relationShip)) {
								
							} else if("4".equals(relationShip)) {
								no_of_father++;
							} else if("5".equals(relationShip)) {
								no_of_mother++;
							}
						}
					}
					if(selfcount==0){
						list.add("error.pa.relation.selfOrHouseMaidatLeastOne");
					} else {
						if(selfcount >1){
							list.add("error.pa.relation.selfValid");
						}
					}
					if(no_of_mother > 1){
						list.add("error.pa.relation.motherValid");
					}
					if(no_of_father > 1){
						list.add("error.pa.relation.fatherValid");
					}

					if(list==null ||list.size()<=0)
					{
						int selfage   = 0;
						int spouseage = 0;
						for(int i=0 ; i<paInsuredDtlsList.size() ; i++) {
							Map<String,Object> paInsuredDtlsMap = paInsuredDtlsList.get(i);
							String relationShip = paInsuredDtlsMap.get("RELATIONSHIP")==null?"":paInsuredDtlsMap.get("RELATIONSHIP").toString();
							
							if("1".equals(relationShip)){
								selfage = tages.get(i);
								if(selfage<=18)
									list.add("error.pa.selfAgeGr18Yers");
							}
							if("2".equals(relationShip) || "3".equals(relationShip))
								spouseage = tages.get(i);

							if("4".equals(relationShip))
							{
								if((selfage != 0 && tages.get(i) >= selfage) || (spouseage !=0 && tages.get(i) >= spouseage)){
									if(!list.contains("error.pa.kidsAgeGrParentsAge"))
										list.add("error.pa.kidsAgeGrParentsAge");
								}
							}else if("4".equals(relationShip))
							{

								if(selfage != 0 && selfage >= tages.get(i))
								{
									if(!list.contains("error.pa.selfAgeGrFatherAge")){
										list.add("error.pa.selfAgeGrFatherAge");
									}
								}

								if(spouseage !=0 && spouseage >=  tages.get(i))
								{
									if(!list.contains("error.pa.spouseAgeGrFatherAge")){
										list.add("error.pa.spouseAgeGrFatherAge");
									}
								}
							}
							if("5".equals(relationShip))
							{
								if(selfage != 0 && selfage >= tages.get(i))
								{
									if(!list.contains("error.pa.selfAgeGrMotherAge")){
										list.add("error.pa.selfAgeGrMotherAge");
									}
								}
								if(spouseage !=0 && spouseage >= tages.get(i))
								{
									if(!list.contains("error.pa.spouseAgeGrMotherAge")){
										list.add("error.pa.spouseAgeGrMotherAge");
									}
								}
							}
						}
					}
				}
			}else if("paymentInfo".equalsIgnoreCase(type)){
				list = new PaymentService().validatePayment(bean.getModeOfPayment(),bean.getChequeNo(),bean.getChequeDate(),bean.getChequeAmount(),bean.getFinalPremium(),bean.getBankName(),bean.getMicrCode(),bean.getCashDepositBank(),bean.getCashAmount(),bean.getCashChellanNo(),bean.getCashInstrumentDate(),bean.getInstallmentYN(),bean.getInsIntialAmount(),bean.getMtnMobileNo());
			}	else if("captcha".equalsIgnoreCase(type)){
				String error = validateCaptcha(bean.getCaptchavalue(),bean.getCaptcha());
				if(!"".equals(error))
					list.add(error);
			} else if("customerDetails".equalsIgnoreCase(type)) {
				list=new CustomerService().getMotorCustomerValidate(/*bean.getIssuer()*/"",bean.getBrokerCode(),bean.getExecutive(),bean.getTitle(),bean.getCustomerName(),bean.getCity(),bean.getPoBox(),bean.getMobileNo(),bean.getEmail(),bean.getCustLastName(),bean.getCustnrc1(),bean.getCustnrc2(),bean.getCustnrc3(),bean.getCustPassportNo(),bean.getCustdob(),bean.getCustAlterMobileNo(),bean.getCustLandLineNo(),bean.getCustomerType(),bean.getCompanyRegNo(),bean.getAddress1(),bean.getGender(),bean.getOccupation());
			} else if("generatePolicy".equalsIgnoreCase(type)) {
				if(!"admin".equals(bean.getUserType())){
					if(StringUtils.isBlank(bean.getReferralYN()))
						list.add("error.referral.YN.required");
					if("Y".equalsIgnoreCase(bean.getReferralYN())){
						if(StringUtils.isBlank(bean.getReferralComments()))
							list.add("error.referral.comments.required");
					}
					if("N".equalsIgnoreCase(bean.getReferralYN())){
						if(StringUtils.isBlank(bean.getGeneratePolicyYN()))
							list.add("error.generate.PolicyYN.required");
						else if("Y".equals(bean.getGeneratePolicyYN())) {
							//if(StringUtils.isBlank(bean.getModeOfPayment())) {
								//list.add("error.mode.pay.required");
							//}
						}
					}
				}else{
					if(StringUtils.isBlank(bean.getLoadOrDiscPremium()))
						bean.setLoadOrDiscPremium("0");
					else if(Double.parseDouble(bean.getLoadOrDiscPremium())>99999999.99)
						list.add("error.loadOrDiscPremium.invalid");
					if(Double.parseDouble(bean.getTotalPremium())<0)
						list.add("error.total.premium.invalid");
				}
			} else if("uwQuestions".equalsIgnoreCase(type)) {
				try {
					List<Map<String, Object>> actLocList = activeLocationDtls(bean);
					if(actLocList!=null && actLocList.size()>0){
						for(int i=0;i<actLocList.size();i++){
							Map<String, Object> actLocMap = actLocList.get(i);
							if(actLocMap!=null && actLocMap.size()>0){
								String locId = actLocMap.get("LOCATION_ID")==null?"":actLocMap.get("LOCATION_ID").toString();
								String locName = actLocMap.get("LOCATION_NAME")==null?"":actLocMap.get("LOCATION_NAME").toString();
								String selScheme = actLocMap.get("SCHEME_ID")==null?"":actLocMap.get("SCHEME_ID").toString();
								String[] selSchemeArr = selScheme.split(",");
								if(selSchemeArr!=null && selSchemeArr.length>0){
									for(int s=0;s<selSchemeArr.length;s++){
										String schemeId = selSchemeArr[s];
										if(StringUtils.isNotBlank(schemeId)){
											List<Map<String,Object>> countResult = dao.getSubDetailsCount(bean.getQuoteNo(),locId,schemeId);
											if(countResult!= null && countResult.size()>0){
												for (Map<String, Object> res : countResult) {
													if(res!=null && res.size()>0){
														String schemeName = res.get("SCHEME_NAME")==null?"":res.get("SCHEME_NAME").toString();
														String coverageName = res.get("COVERAGES_DISPLAY_NAME")==null?"":res.get("COVERAGES_DISPLAY_NAME").toString();
														String valError = "Add Cover Details";
														list.add("Scheme: "+schemeName+" | Location: "+locName+" | Coverage: "+coverageName+" | Error - "+valError);
													}
												}
											}else{
												List<Map<String,Object>> insResult = dao.getSubDetailsValidateAmount(bean.getProductId(),schemeId,
														bean.getContentTypeId(),bean.getQuoteNo(),locId);
												if(insResult!= null && insResult.size()>0){
													for (Map<String, Object> res : insResult) {
														if(res!=null && res.size()>0){
															String schemeName = res.get("SCHEME_NAME")==null?"":res.get("SCHEME_NAME").toString();
															String coverageName = res.get("COVERAGES_DISPLAY_NAME")==null?"":res.get("COVERAGES_DISPLAY_NAME").toString();
															String sumInsured = res.get("COVERAGES_SUMINSURED")==null?"":res.get("COVERAGES_SUMINSURED").toString();
															String valError = "Total Coverages SumInsured is greater than ";
															list.add("Scheme: "+schemeName+" | Location: "+locName+" | Coverage: "+coverageName+" | Error - "+valError+sumInsured);
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
					if("Y".equalsIgnoreCase(bean.getReferralYN())){
						if(StringUtils.isBlank(bean.getReferralComments())){
							list.add("Enter Referral Comments");
						}
					}
				} catch(Exception exception) {
					list.add("error.coverageUpload.error");
				}
				
//				if(bean.getSchemeList().size()>0){
//					//for(int i=0;i<bean.getSchemeList().size();i++){
//						String[] dtl=dao.getExtendedCoverageCount(bean,bean.getSchemeList());
//						if(dtl[0].equalsIgnoreCase("0"))
//							list.add(dtl[1]);
//						//else{
//						//	list.add(dtl[1]);
//						//}
//					//}
//				}
				
//				Map<String, Object> map=new HashMap<String,Object>();
//				List<Map<String, Object>> coverList=new ArrayList<Map<String,Object>>();
//				if(StringUtils.isBlank(bean.getBuildCon()))
//					bean.setBuildCon("N");
//				if(StringUtils.isBlank(bean.getLiveAccom()))
//					bean.setLiveAccom("N");
//				if(StringUtils.isBlank(bean.getReclaimLand()))
//					bean.setReclaimLand("N");
//				if(StringUtils.isBlank(bean.getConsDays()))
//					bean.setConsDays("N");
//				if(StringUtils.isBlank(bean.getDecRej()))
//					bean.setDecRej("N");
//				if(StringUtils.isBlank(bean.getPastThr()))
//					bean.setPastThr("N");
//				else if("Y".equals(bean.getPastThr())){
//					map.put("ANY_CLAIM_IN_3YRS", bean.getPastThr());
//					if(StringUtils.isBlank(bean.getNoClaim()) || !(StringUtils.isNumeric(bean.getNoClaim()))){
//						list.add("error.no.claims.valid");
//					}if(StringUtils.isBlank(bean.getTypeClaim())){
//						list.add("error.type.claims.valid");
//					}if(StringUtils.isBlank(bean.getClaimAmt()) || !StringUtils.isNumeric(bean.getClaimAmt())){
//						list.add("error.claims.amt.valid");
//					}
//				}map.put("NO_OF_CLAIMS", bean.getNoClaim()==null?"":bean.getNoClaim());
//				map.put("TYPE_OF_CLAIMS", bean.getTypeClaim()==null?"":bean.getTypeClaim());
//				map.put("CLAIM_AMOUNT", bean.getClaimAmt()==null?"": bean.getClaimAmt());
//				map.put("BUILT_OF_CONCRETE", bean.getBuildCon());
//				map.put("PRIVATE_LIVING_ACCOMODATION", bean.getLiveAccom());
//				map.put("BUILT_ON_RECLAIMED_LAND", bean.getReclaimLand());
//				map.put("UNATTENDED_60_CONSECUTIVE_DAYS", bean.getConsDays());
//				map.put("DECLINED_CANELLED_CONDITIONS", bean.getDecRej());
//				map.put("ANY_CLAIM_IN_3YRS", bean.getPastThr());
//				map.put("PROPERTY_TYPE", bean.getTypeProperty());
//				coverList.add(map);
//				bean.setTypeClaim(bean.getTypeClaim()==null?"":bean.getTypeClaim());
//				bean.setCoverageList(coverList);
			} else if("coverageUpload".equalsIgnoreCase(type)) {
				List<Map<String, Object>> coverList=new ArrayList<Map<String,Object>>();
				List<String> deleteRowList = new ArrayList<String>();
				//List<Object> elist=new ArrayList<Object>();
				Double coverTS=0.0D;
				List <String> list1=new ArrayList<String>();
				if(bean.getDeleteRow().size()!=bean.getColDyn0().size()){
					for(int i=0; i<bean.getColDyn0().size(); i++){
						list1.add("");
					}
					bean.setDeleteRow(list1);
				}
				for(int j=0; j<bean.getColDyn0().size(); j++){
					boolean empty=true;
					if(bean.getSubList().size()>=1){
						if(!StringUtils.isBlank(bean.getColDyn0().get(j)))
							empty=false;
					}if(bean.getSubList().size()>=2){
						if(!StringUtils.isBlank(bean.getColDyn1().get(j)))
							empty=false;
					}if(bean.getSubList().size()>=3){
						if(!StringUtils.isBlank(bean.getColDyn2().get(j)))
							empty=false;
					}if(bean.getSubList().size()>=4){
						if(!StringUtils.isBlank(bean.getColDyn3().get(j)))
							empty=false;
					}if(bean.getSubList().size()>=5){
						if(!StringUtils.isBlank(bean.getColDyn4().get(j)))
							empty=false;
					}if(bean.getSubList().size()>=6){
						if(!StringUtils.isBlank(bean.getColDyn5().get(j)))
							empty=false;
					}if(bean.getSubList().size()>=7){
						if(!StringUtils.isBlank(bean.getColDyn6().get(j)))
							empty=false;
					}if(bean.getSubList().size()>=8){
						if(!StringUtils.isBlank(bean.getColDyn7().get(j)))
							empty=false;
					}if(bean.getSubList().size()>=9){
						if(!StringUtils.isBlank(bean.getColDyn8().get(j)))
							empty=false;
					}if(bean.getSubList().size()>=10){
						if(!StringUtils.isBlank(bean.getColDyn9().get(j)))
							empty=false;
					}
					if(!empty){
						if(StringUtils.isBlank(bean.getDeleteRow().get(j)) || "false".equalsIgnoreCase(bean.getDeleteRow().get(j))){
							Map<String, Object> map=new HashMap<String, Object>();
							if(bean.getSubList().size()>=1){
								String colDyn = bean.getColDyn0().get(j);
								if(StringUtils.isBlank(colDyn)){
									if("Y".equals(((Map<String, Object>)bean.getSubList().get(0)).get("Mandatory")==null?"N":((Map<String, Object>)bean.getSubList().get(0)).get("Mandatory").toString())) {
										list.add("coverageid.required" + "#" + ((Map<String, Object>)bean.getSubList().get(0)).get("DISPLAY_NAME").toString() + "~" + (j+1) + "#");
									}
								} else {
									if("Number".equals(((Map<String, Object>)bean.getSubList().get(0)).get("VALIDATION_TYPE").toString())){
										colDyn = colDyn.replace(",", "");
										if(!StringUtils.isNumeric(colDyn)) {
											list.add("coverageid.invalid" + "#" + ((Map<String, Object>)bean.getSubList().get(0)).get("DISPLAY_NAME").toString() + "~" + (j+1) + "#");
										} else if("Y".equalsIgnoreCase(((Map<String, Object>)bean.getSubList().get(0)).get("TOTAL_SUMINSURED_YN")==null?"N":((Map<String, Object>)bean.getSubList().get(0)).get("TOTAL_SUMINSURED_YN").toString())){
											coverTS+=Double.parseDouble(colDyn);
										}
									}
								}
								map.put(((Map<String, Object>)bean.getSubList().get(0)).get("DEST_COLUMN").toString(), colDyn);
							}if(bean.getSubList().size()>=2){
								String colDyn = bean.getColDyn1().get(j);
								if(StringUtils.isBlank(colDyn)){
									if("Y".equals(((Map<String, Object>)bean.getSubList().get(1)).get("Mandatory")==null?"N":((Map<String, Object>)bean.getSubList().get(1)).get("Mandatory").toString())) {
										list.add("coverageid.required" + "#" + ((Map<String, Object>)bean.getSubList().get(1)).get("DISPLAY_NAME").toString() + "~" + (j+1) + "#");
									}
								} else {
									if("Number".equals(((Map<String, Object>)bean.getSubList().get(1)).get("VALIDATION_TYPE").toString())){
										colDyn = colDyn.replace(",", "");
										if(!StringUtils.isNumeric(colDyn)) {
											list.add("coverageid.invalid" + "#" + ((Map<String, Object>)bean.getSubList().get(1)).get("DISPLAY_NAME").toString() + "~" + (j+1) + "#");
										} else if("Y".equalsIgnoreCase(((Map<String, Object>)bean.getSubList().get(1)).get("TOTAL_SUMINSURED_YN")==null?"N":((Map<String, Object>)bean.getSubList().get(1)).get("TOTAL_SUMINSURED_YN").toString())){
											coverTS+=Double.parseDouble(colDyn);
										}
									}
								}
								map.put(((Map<String, Object>)bean.getSubList().get(1)).get("DEST_COLUMN").toString(), colDyn);
							}if(bean.getSubList().size()>=3){
								String colDyn = bean.getColDyn2().get(j);
								if(StringUtils.isBlank(colDyn)){
									if("Y".equals(((Map<String, Object>)bean.getSubList().get(2)).get("Mandatory")==null?"N":((Map<String, Object>)bean.getSubList().get(2)).get("Mandatory").toString())) {
										list.add("coverageid.required" + "#" + ((Map<String, Object>)bean.getSubList().get(2)).get("DISPLAY_NAME").toString() + "~" + (j+1) + "#");
									}
								}else{
									if("Number".equals(((Map<String, Object>)bean.getSubList().get(2)).get("VALIDATION_TYPE").toString())){
										colDyn = colDyn.replace(",", "");
										if(!StringUtils.isNumeric(colDyn)) {
											list.add("coverageid.invalid" + "#" + ((Map<String, Object>)bean.getSubList().get(2)).get("DISPLAY_NAME").toString() + "~" + (j+1) + "#");
										} else if("Y".equalsIgnoreCase(((Map<String, Object>)bean.getSubList().get(2)).get("TOTAL_SUMINSURED_YN")==null?"N":((Map<String, Object>)bean.getSubList().get(2)).get("TOTAL_SUMINSURED_YN").toString())){
											coverTS+=Double.parseDouble(colDyn);
										}
									}
								}
								map.put(((Map<String, Object>)bean.getSubList().get(2)).get("DEST_COLUMN").toString(), colDyn);
							}if(bean.getSubList().size()>=4){
								String colDyn = bean.getColDyn3().get(j);
								if(StringUtils.isBlank(colDyn)){
									if("Y".equals(((Map<String, Object>)bean.getSubList().get(3)).get("Mandatory")==null?"N":((Map<String, Object>)bean.getSubList().get(3)).get("Mandatory").toString())) {
										list.add("coverageid.required" + "#" + ((Map<String, Object>)bean.getSubList().get(3)).get("DISPLAY_NAME").toString() + "~" + (j+1) + "#");
									}
								}else{
									if("Number".equals(((Map<String, Object>)bean.getSubList().get(3)).get("VALIDATION_TYPE").toString())){
										colDyn = colDyn.replace(",", "");
										if(!StringUtils.isNumeric(colDyn)) {
											list.add("coverageid.invalid" + "#" + ((Map<String, Object>)bean.getSubList().get(3)).get("DISPLAY_NAME").toString() + "~" + (j+1) + "#");
										} else if("Y".equalsIgnoreCase(((Map<String, Object>)bean.getSubList().get(3)).get("TOTAL_SUMINSURED_YN")==null?"N":((Map<String, Object>)bean.getSubList().get(3)).get("TOTAL_SUMINSURED_YN").toString())){
											coverTS+=Double.parseDouble(colDyn);
										}
									}
								}
								map.put(((Map<String, Object>)bean.getSubList().get(3)).get("DEST_COLUMN").toString(), colDyn);
							}if(bean.getSubList().size()>=5){
								String colDyn = bean.getColDyn4().get(j);
								if(StringUtils.isBlank(colDyn)){
									if("Y".equals(((Map<String, Object>)bean.getSubList().get(4)).get("Mandatory")==null?"N":((Map<String, Object>)bean.getSubList().get(4)).get("Mandatory").toString())) {
										list.add("coverageid.required" + "#" + ((Map<String, Object>)bean.getSubList().get(4)).get("DISPLAY_NAME").toString() + "~" + (j+1) + "#");
									}
								}else{
									if("Number".equals(((Map<String, Object>)bean.getSubList().get(4)).get("VALIDATION_TYPE").toString())){
										colDyn = colDyn.replace(",", "");
										if(!StringUtils.isNumeric(colDyn)) {
											list.add("coverageid.invalid" + "#" + ((Map<String, Object>)bean.getSubList().get(4)).get("DISPLAY_NAME").toString() + "~" + (j+1) + "#");
										} else if("Y".equalsIgnoreCase(((Map<String, Object>)bean.getSubList().get(4)).get("TOTAL_SUMINSURED_YN")==null?"N":((Map<String, Object>)bean.getSubList().get(4)).get("TOTAL_SUMINSURED_YN").toString())){
											coverTS+=Double.parseDouble(colDyn);
										}
									}
								}
								map.put(((Map<String, Object>)bean.getSubList().get(4)).get("DEST_COLUMN").toString(), colDyn);
							}if(bean.getSubList().size()>=6){
								String colDyn = bean.getColDyn5().get(j);
								if(StringUtils.isBlank(colDyn)){
									if("Y".equals(((Map<String, Object>)bean.getSubList().get(5)).get("Mandatory")==null?"N":((Map<String, Object>)bean.getSubList().get(5)).get("Mandatory").toString())) {
										list.add("coverageid.required" + "#" + ((Map<String, Object>)bean.getSubList().get(5)).get("DISPLAY_NAME").toString() + "~" + (j+1) + "#");
									}
								}else{
									if("Number".equals(((Map<String, Object>)bean.getSubList().get(5)).get("VALIDATION_TYPE").toString())){
										colDyn = colDyn.replace(",", "");
										if(!StringUtils.isNumeric(colDyn)) {
											list.add("coverageid.invalid" + "#" + ((Map<String, Object>)bean.getSubList().get(5)).get("DISPLAY_NAME").toString() + "~" + (j+1) + "#");
										} else if("Y".equalsIgnoreCase(((Map<String, Object>)bean.getSubList().get(5)).get("TOTAL_SUMINSURED_YN")==null?"N":((Map<String, Object>)bean.getSubList().get(5)).get("TOTAL_SUMINSURED_YN").toString())){
											coverTS+=Double.parseDouble(colDyn);
										}
									}
								}
								map.put(((Map<String, Object>)bean.getSubList().get(5)).get("DEST_COLUMN").toString(), colDyn);
							}if(bean.getSubList().size()>=7){
								String colDyn = bean.getColDyn6().get(j);
								if(StringUtils.isBlank(colDyn)){
									if("Y".equals(((Map<String, Object>)bean.getSubList().get(6)).get("Mandatory")==null?"N":((Map<String, Object>)bean.getSubList().get(6)).get("Mandatory").toString())) {
										list.add("coverageid.required" + "#" + ((Map<String, Object>)bean.getSubList().get(6)).get("DISPLAY_NAME").toString() + "~" + (j+1) + "#");
									}
								}else{
									if("Number".equals(((Map<String, Object>)bean.getSubList().get(6)).get("VALIDATION_TYPE").toString())){
										colDyn = colDyn.replace(",", "");
										if(!StringUtils.isNumeric(colDyn)) {
											list.add("coverageid.invalid" + "#" + ((Map<String, Object>)bean.getSubList().get(6)).get("DISPLAY_NAME").toString() + "~" + (j+1));
										} else if("Y".equalsIgnoreCase(((Map<String, Object>)bean.getSubList().get(6)).get("TOTAL_SUMINSURED_YN")==null?"N":((Map<String, Object>)bean.getSubList().get(6)).get("TOTAL_SUMINSURED_YN").toString())){
											coverTS+=Double.parseDouble(colDyn);
										}
									}
								}
								map.put(((Map<String, Object>)bean.getSubList().get(6)).get("DEST_COLUMN").toString(), colDyn);
							}if(bean.getSubList().size()>=8){
								String colDyn = bean.getColDyn7().get(j);
								if(StringUtils.isBlank(colDyn)){
									if("Y".equals(((Map<String, Object>)bean.getSubList().get(7)).get("Mandatory")==null?"N":((Map<String, Object>)bean.getSubList().get(7)).get("Mandatory").toString())) {
										list.add("coverageid.required" + "#" + ((Map<String, Object>)bean.getSubList().get(7)).get("DISPLAY_NAME").toString() + "~" + (j+1) + "#");
									}
								}else {
									if("Number".equals(((Map<String, Object>)bean.getSubList().get(7)).get("VALIDATION_TYPE").toString())){
										colDyn = colDyn.replace(",", "");
										if(!StringUtils.isNumeric(colDyn)) {
											list.add("coverageid.invalid" + "#" + ((Map<String, Object>)bean.getSubList().get(7)).get("DISPLAY_NAME").toString() + "~" + (j+1) + "#");
										} else if("Y".equalsIgnoreCase(((Map<String, Object>)bean.getSubList().get(7)).get("TOTAL_SUMINSURED_YN")==null?"N":((Map<String, Object>)bean.getSubList().get(7)).get("TOTAL_SUMINSURED_YN").toString())){
											coverTS+=Double.parseDouble(colDyn);
										}
									}
								}
								map.put(((Map<String, Object>)bean.getSubList().get(7)).get("DEST_COLUMN").toString(), colDyn);
							}if(bean.getSubList().size()>=9){
								String colDyn = bean.getColDyn8().get(j);
								if(StringUtils.isBlank(colDyn)){
									if("Y".equals(((Map<String, Object>)bean.getSubList().get(8)).get("Mandatory")==null?"N":((Map<String, Object>)bean.getSubList().get(8)).get("Mandatory").toString())) {
										list.add("coverageid.required" + "#" + ((Map<String, Object>)bean.getSubList().get(8)).get("DISPLAY_NAME").toString() + "~" + (j+1) + "#");
									}
								}else{
									if("Number".equals(((Map<String, Object>)bean.getSubList().get(8)).get("VALIDATION_TYPE").toString())){
										colDyn = colDyn.replace(",", "");
										if(!StringUtils.isNumeric(colDyn)) {
											list.add("coverageid.invalid" + "#" + ((Map<String, Object>)bean.getSubList().get(8)).get("DISPLAY_NAME").toString() + "~" + (j+1) + "#");
										} else if("Y".equalsIgnoreCase(((Map<String, Object>)bean.getSubList().get(8)).get("TOTAL_SUMINSURED_YN")==null?"N":((Map<String, Object>)bean.getSubList().get(8)).get("TOTAL_SUMINSURED_YN").toString())){
											coverTS+=Double.parseDouble(colDyn);
										}
									}
								}
								map.put(((Map<String, Object>)bean.getSubList().get(8)).get("DEST_COLUMN").toString(), colDyn);
							}if(bean.getSubList().size()>=10){
								String colDyn = bean.getColDyn9().get(j);
								if(StringUtils.isBlank(colDyn)){
									if("Y".equals(((Map<String, Object>)bean.getSubList().get(9)).get("Mandatory")==null?"N":((Map<String, Object>)bean.getSubList().get(9)).get("Mandatory").toString())) {
										list.add("coverageid.required" + "#" + ((Map<String, Object>)bean.getSubList().get(9)).get("DISPLAY_NAME").toString() + "~" + (j+1) + "#");
									}
								}else{
									if("Number".equals(((Map<String, Object>)bean.getSubList().get(9)).get("VALIDATION_TYPE").toString())){
										colDyn = colDyn.replace(",", "");
										if(!StringUtils.isNumeric(colDyn)) {
											list.add("coverageid.invalid" + "#" + ((Map<String, Object>)bean.getSubList().get(9)).get("DISPLAY_NAME").toString() + "~" + (j+1) + "#");
										} else if("Y".equalsIgnoreCase(((Map<String, Object>)bean.getSubList().get(9)).get("TOTAL_SUMINSURED_YN")==null?"N":((Map<String, Object>)bean.getSubList().get(9)).get("TOTAL_SUMINSURED_YN").toString())){
											coverTS+=Double.parseDouble(colDyn);
										}
									}
								}
								map.put(((Map<String, Object>)bean.getSubList().get(9)).get("DEST_COLUMN").toString(), colDyn);
							}
							coverList.add(map);
						} else {
							deleteRowList.add(String.valueOf(j));
						}
					}
				}
				/*if(coverTS>Double.parseDouble(bean.getCoverSumInsured())) {
					list.add("error.total.suminsured.exceeds");
				}*/
				bean.setCoverTS(coverTS.toString());
				bean.setCoverageList(coverList);
				bean.setDeleteRowList(deleteRowList);
			} else if("getQuoteHome".equalsIgnoreCase(type)) {
				/*if(StringUtils.isBlank(bean.getInceptionDt())) {
					list.add("error.inceptionDate.required");
				} else if(StringUtils.isBlank(bean.getEndTypeId())) {
					final DateFunction datf = new DateFunction();
					final Calendar cal1 = Calendar.getInstance();
					String[] effDT=bean.getInceptionDt().split("/");
					cal1.set(Integer.parseInt(effDT[2]),Integer.parseInt(effDT[1]),Integer.parseInt(effDT[0]));
					final Calendar cal2 = Calendar.getInstance();
					final Map<String,Object> todayDate =  new com.maan.common.dao.CommonDAO().getTodaysDate();
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
						backDate = new com.maan.common.dao.CommonDAO().getBackDatesAllowed(bean.getLoginId(),bean.getUserType(),bean.getProductId(),bean.getBranchCode(),bean.getSchemeId());
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
				}*/
								
				List<Map<String,Object>> mainCoverValues = getHomeInfo(bean);
				List<String> sumInsuredList = new ArrayList<String>();
				for(int i=1 ; i<=mainCoverValues.size() ; i++) {
					Map<String,Object> mainCoverMap = mainCoverValues.get(i-1);
					String coverDisplayName = mainCoverMap.get("COVERAGES_DISPLAY_NAME").toString();
					sumInsuredList.add(bean.getSUM_INSURED().get(i)==null?"":bean.getSUM_INSURED().get(i));
					if(StringUtils.isNotBlank(bean.getSUM_INSURED().get(i))){
						bean.getSUM_INSURED().put(i,bean.getSUM_INSURED().get(i).replaceAll(",", ""));
						if(!(bean.getSUM_INSURED().get(i)==null?"0":bean.getSUM_INSURED().get(i)).matches("^\\d+([.]\\d+)?$"))
							list.add("error.suminsured.for"+"#"+ coverDisplayName );
						else if(!StringUtils.isNumeric((bean.getSUM_INSURED().get(i)==null?"0":bean.getSUM_INSURED().get(i)))) {
							list.add("error.suminsured.for"+"#"+ coverDisplayName );
						}
					}
				}
				bean.setSumInsuredList(sumInsuredList);
				
				List<Map<String,Object>> subCoverValues = getSubHomeInfo(bean);
				List<String> subSumInsuredList = new ArrayList<String>();
				for(int i=1 ; i<= subCoverValues.size() ; i++) {
					Map<String,Object> subCoverMap = subCoverValues.get(i-1);
					String coverDisplayName = subCoverMap.get("COVERAGES_DISPLAY_NAME").toString();
					
					subSumInsuredList.add(bean.getCOVERAGES_COVERED_EMPLOYEES().get(i)==null?"":bean.getCOVERAGES_COVERED_EMPLOYEES().get(i));
					if(StringUtils.isNotBlank(bean.getCOVERAGES_COVERED_EMPLOYEES().get(i))){
						bean.getCOVERAGES_COVERED_EMPLOYEES().put(i,bean.getCOVERAGES_COVERED_EMPLOYEES().get(i).replaceAll(",", ""));
						if(!(bean.getCOVERAGES_COVERED_EMPLOYEES().get(i)==null?"0":bean.getCOVERAGES_COVERED_EMPLOYEES().get(i)).matches("^\\d+([.]\\d+)?$"))
							list.add("error.suminsured.for"+"#"+ coverDisplayName);
						else if(!StringUtils.isNumeric(bean.getCOVERAGES_COVERED_EMPLOYEES().get(i)==null?"0":bean.getCOVERAGES_COVERED_EMPLOYEES().get(i))) {
							list.add("error.suminsured.for"+"#"+coverDisplayName);
						}
					}
				}
				bean.setSubSumInsuredList(subSumInsuredList);
				
				if(list.size()==0) {
					String valid="";
					for(int i=1 ; i<=mainCoverValues.size() ; i++){
						Map<String,Object> mainCoverMap = mainCoverValues.get(i-1);
						String coverages_id=mainCoverMap.get("COVERAGES_ID").toString();
						String coverDisplayName = mainCoverMap.get("COVERAGES_DISPLAY_NAME").toString();
						String minSumInsured = mainCoverMap.get("MIN_SUM_INSURED")==null?"":mainCoverMap.get("MIN_SUM_INSURED").toString();
						String maxSumInsured = mainCoverMap.get("COVERAGES_LIMIT")==null?"":mainCoverMap.get("COVERAGES_LIMIT").toString();
						if("N".equalsIgnoreCase(mainCoverMap.get("SUB_COVERAGES").toString())) {
							String sumInsured="0";
							/*if(StringUtils.isBlank(bean.getSUM_INSURED().get(i))) {
								sumInsured="0";
							} else {
								sumInsured = bean.getSUM_INSURED().get(i);
								if( StringUtils.isNotBlank(maxSumInsured) || StringUtils.isNotBlank(minSumInsured) ){
									if( (maxSumInsured!=null && Double.parseDouble(sumInsured)>Double.parseDouble(maxSumInsured))
											|| (minSumInsured!=null && Double.parseDouble(sumInsured)<Double.parseDouble(minSumInsured))
											) {
										valid+=","+ coverDisplayName + " exceeded Sum Insured Limit";
									}
								}
							}*/
							if(StringUtils.isBlank(bean.getSUM_INSURED().get(i))) 
								sumInsured="0";
							else
								sumInsured = bean.getSUM_INSURED().get(i);
							if( (StringUtils.isNotBlank(maxSumInsured) || StringUtils.isNotBlank(minSumInsured)) && !"99999".equalsIgnoreCase(coverages_id)){
								if( (maxSumInsured!=null && Double.parseDouble(sumInsured)>Double.parseDouble(maxSumInsured))
										|| (minSumInsured!=null && Double.parseDouble(sumInsured)<Double.parseDouble(minSumInsured))
										) {
									valid+=","+ coverDisplayName + " exceeded Sum Insured Limit";
								}
							}
						}
					}

					for(int i=1 ; i<= subCoverValues.size() ; i++) {
						Map<String,Object> subCoverMap = subCoverValues.get(i-1);
						String coverages_id=subCoverMap.get("COVERAGES_ID").toString();
						String coverDisplayName = subCoverMap.get("COVERAGES_DISPLAY_NAME").toString();
						String minSumInsured = subCoverMap.get("MIN_SUM_INSURED")==null?"0":subCoverMap.get("MIN_SUM_INSURED").toString();
						String maxSumInsured = subCoverMap.get("SUB_COVERAGES_LIMIT")==null?"":subCoverMap.get("SUB_COVERAGES_LIMIT").toString();
						
						String sumInsured="0";
						/*if(StringUtils.isBlank(bean.getCOVERAGES_COVERED_EMPLOYEES().get(i))) {
							sumInsured="0";
						} else  {
							sumInsured=bean.getCOVERAGES_COVERED_EMPLOYEES().get(i);
							if( StringUtils.isNotBlank(maxSumInsured) || StringUtils.isNotBlank(minSumInsured) ) {
								if( (maxSumInsured!=null && Double.parseDouble(sumInsured)>Double.parseDouble(maxSumInsured))
										|| (minSumInsured!=null && Double.parseDouble(sumInsured)<Double.parseDouble(minSumInsured))
										) {
									valid+="," + coverDisplayName + " exceeded Sum Insured Limit";
								}
							}
						}*/
						if(StringUtils.isBlank(bean.getCOVERAGES_COVERED_EMPLOYEES().get(i))) 
							sumInsured="0";
						else
							sumInsured=bean.getCOVERAGES_COVERED_EMPLOYEES().get(i);
						if( (StringUtils.isNotBlank(maxSumInsured) || StringUtils.isNotBlank(minSumInsured)) && !"99999".equalsIgnoreCase(coverages_id) ) {
							if( (maxSumInsured!=null && Double.parseDouble(sumInsured)>Double.parseDouble(maxSumInsured))
									|| (minSumInsured!=null && Double.parseDouble(sumInsured)<Double.parseDouble(minSumInsured))
									) {
								valid+="," + coverDisplayName + " exceeded Sum Insured Limit";
							}
						}
					}
					
					if(valid.length()>0) {
						valid=valid.substring(1);
					}
					bean.setValidCoverage(valid);
				}
			}else if("getQuoteHomeLocation".equalsIgnoreCase(type)) {
				if("Y".equalsIgnoreCase(bean.getModifyYN())){
					String[][] sumInsuredVal=new  String[30][30];
					String schemeName="";
					String locationName="";
					String locationId="";
					String valid="";
					List<Map<String,Object>> schemeDetail=dao.getSchemeName(bean);
					List<Map<String,Object>> locDetail=dao.getLocationNames(bean);
					
					schemeName=schemeDetail.get(0).get("SCHEME_NAME")==null?"":schemeDetail.get(0).get("SCHEME_NAME").toString();
					
					for(int a=0;a<locDetail.size();a++){
						
						locationName=locDetail.get(a).get("LOCATION_NAME")==null?"":locDetail.get(a).get("LOCATION_NAME").toString();
						locationId=locDetail.get(a).get("LOCATION_ID")==null?"":locDetail.get(a).get("LOCATION_ID").toString();
						bean.setLocationId(locationId);
						bean.setCallFrom("insert");
						List<Map<String,Object>> mainCoverValues = getHomeInfo(bean);
						List<String> sumInsuredList = new ArrayList<String>();
						for(int i=1 ; i<=mainCoverValues.size() ; i++) {
							Map<String,Object> mainCoverMap = mainCoverValues.get(i-1);
							String coverDisplayName = mainCoverMap.get("COVERAGES_DISPLAY_NAME").toString();
							if("1".equalsIgnoreCase(bean.getLocationSize())){
	
								sumInsuredList.add(bean.getSUM_INSURED().get(i)==null?"":bean.getSUM_INSURED().get(i));
								if(StringUtils.isNotBlank(bean.getSUM_INSURED().get(i))){
									bean.getSUM_INSURED().put(i,bean.getSUM_INSURED().get(i).replaceAll(",", ""));
									if(!(bean.getSUM_INSURED().get(i)==null?"0":bean.getSUM_INSURED().get(i)).matches("^\\d+([.]\\d+)?$"))
										list.add("error.suminsured.for"+"#"+ coverDisplayName );
									else if(!StringUtils.isNumeric((bean.getSUM_INSURED().get(i)==null?"0":bean.getSUM_INSURED().get(i)))) {
										list.add("error.suminsured.for"+"#"+ coverDisplayName );
									}
								}
							}
							else{
								sumInsuredList.add(bean.getSumInsuredNM()[a][i-1]==null?"":bean.getSumInsuredNM()[a][i-1]);
								if(StringUtils.isNotBlank(bean.getSumInsuredNM()[a][i-1])){
									//bean.getSUM_INSURED().put(i,bean.getSumInsuredNM()[a][i-1].replaceAll(",", ""));
									sumInsuredVal[a][i-1]=bean.getSumInsuredNM()[a][i-1].replaceAll(",", "");
									if(!(sumInsuredVal[a][i-1]==null?"0":sumInsuredVal[a][i-1]).matches("^\\d+([.]\\d+)?$"))
										list.add("error.suminsured.for"+"#"+ coverDisplayName +" in Location "+locationName+" For Product "+schemeName);
									else if(!StringUtils.isNumeric((sumInsuredVal[a][i-1]==null?"0":sumInsuredVal[a][i-1]))) {
										list.add("error.suminsured.for"+"#"+ coverDisplayName +" in Location "+locationName+" For Product "+schemeName);
									}
								}
							}
							
						}
						bean.setSumInsuredList(sumInsuredList);
						
						List<Map<String,Object>> subCoverValues = getSubHomeInfo(bean);
						List<String> subSumInsuredList = new ArrayList<String>();
						for(int i=1 ; i<= subCoverValues.size() ; i++) {
							Map<String,Object> subCoverMap = subCoverValues.get(i-1);
							String coverDisplayName = subCoverMap.get("COVERAGES_DISPLAY_NAME").toString();
							
							subSumInsuredList.add(bean.getCOVERAGES_COVERED_EMPLOYEES().get(i)==null?"":bean.getCOVERAGES_COVERED_EMPLOYEES().get(i));
							if(StringUtils.isNotBlank(bean.getCOVERAGES_COVERED_EMPLOYEES().get(i))){
								bean.getCOVERAGES_COVERED_EMPLOYEES().put(i,bean.getCOVERAGES_COVERED_EMPLOYEES().get(i).replaceAll(",", ""));
								if(!(bean.getCOVERAGES_COVERED_EMPLOYEES().get(i)==null?"0":bean.getCOVERAGES_COVERED_EMPLOYEES().get(i)).matches("^\\d+([.]\\d+)?$"))
									list.add("error.suminsured.for"+"#"+ coverDisplayName);
								else if(!StringUtils.isNumeric(bean.getCOVERAGES_COVERED_EMPLOYEES().get(i)==null?"0":bean.getCOVERAGES_COVERED_EMPLOYEES().get(i))) {
									list.add("error.suminsured.for"+"#"+coverDisplayName);
								}
							}
						}
						bean.setSubSumInsuredList(subSumInsuredList);
						
						if(list.size()==0) {
							
							for(int i=1 ; i<=mainCoverValues.size() ; i++){
								Map<String,Object> mainCoverMap = mainCoverValues.get(i-1);
								String coverages_id=mainCoverMap.get("COVERAGES_ID").toString();
								String coverDisplayName = mainCoverMap.get("COVERAGES_DISPLAY_NAME").toString();
								String minSumInsured = mainCoverMap.get("MIN_SUM_INSURED")==null?"":mainCoverMap.get("MIN_SUM_INSURED").toString();
								String maxSumInsured = mainCoverMap.get("COVERAGES_LIMIT")==null?"":mainCoverMap.get("COVERAGES_LIMIT").toString();
								if("N".equalsIgnoreCase(mainCoverMap.get("SUB_COVERAGES").toString())) {
									String sumInsured="0";
									/*if(StringUtils.isBlank(bean.getSUM_INSURED().get(i))) {
										sumInsured="0";
									} else {
										sumInsured = bean.getSUM_INSURED().get(i);
										if( StringUtils.isNotBlank(maxSumInsured) || StringUtils.isNotBlank(minSumInsured) ){
											if( (maxSumInsured!=null && Double.parseDouble(sumInsured)>Double.parseDouble(maxSumInsured))
													|| (minSumInsured!=null && Double.parseDouble(sumInsured)<Double.parseDouble(minSumInsured))
													) {
												valid+=","+ coverDisplayName + " exceeded Sum Insured Limit";
											}
										}
									}*/
									if("1".equalsIgnoreCase(bean.getLocationSize())){
										if(StringUtils.isBlank(bean.getSUM_INSURED().get(i))) 
											sumInsured="0";
										else
											sumInsured = bean.getSUM_INSURED().get(i);
										if( (StringUtils.isNotBlank(maxSumInsured) || StringUtils.isNotBlank(minSumInsured)) && !"99999".equalsIgnoreCase(coverages_id)){
											if( (maxSumInsured!=null && Double.parseDouble(sumInsured)>Double.parseDouble(maxSumInsured))
													|| (minSumInsured!=null && Double.parseDouble(sumInsured)<Double.parseDouble(minSumInsured))
													) {
												valid+=","+ coverDisplayName + " exceeded Sum Insured Limit";
											}
										}
									}else{
										if(StringUtils.isBlank(sumInsuredVal[a][i-1])) 
											sumInsured="0";
										else
											sumInsured = sumInsuredVal[a][i-1];
										if( (StringUtils.isNotBlank(maxSumInsured) || StringUtils.isNotBlank(minSumInsured)) && !"99999".equalsIgnoreCase(coverages_id)){
											if( (maxSumInsured!=null && Double.parseDouble(sumInsured)>Double.parseDouble(maxSumInsured))
													|| (minSumInsured!=null && Double.parseDouble(sumInsured)<Double.parseDouble(minSumInsured))
													) {
												valid+=","+ coverDisplayName + " exceeded Sum Insured Limit in Location "+locationName+" For Product "+schemeName;
											}
										}
									}
								}
							}
			
							for(int i=1 ; i<= subCoverValues.size() ; i++) {
								Map<String,Object> subCoverMap = subCoverValues.get(i-1);
								String coverages_id=subCoverMap.get("COVERAGES_ID").toString();
								String coverDisplayName = subCoverMap.get("COVERAGES_DISPLAY_NAME").toString();
								String minSumInsured = subCoverMap.get("MIN_SUM_INSURED")==null?"0":subCoverMap.get("MIN_SUM_INSURED").toString();
								String maxSumInsured = subCoverMap.get("SUB_COVERAGES_LIMIT")==null?"":subCoverMap.get("SUB_COVERAGES_LIMIT").toString();
								
								String sumInsured="0";
								/*if(StringUtils.isBlank(bean.getCOVERAGES_COVERED_EMPLOYEES().get(i))) {
									sumInsured="0";
								} else  {
									sumInsured=bean.getCOVERAGES_COVERED_EMPLOYEES().get(i);
									if( StringUtils.isNotBlank(maxSumInsured) || StringUtils.isNotBlank(minSumInsured) ) {
										if( (maxSumInsured!=null && Double.parseDouble(sumInsured)>Double.parseDouble(maxSumInsured))
												|| (minSumInsured!=null && Double.parseDouble(sumInsured)<Double.parseDouble(minSumInsured))
												) {
											valid+="," + coverDisplayName + " exceeded Sum Insured Limit";
										}
									}
								}*/
								if(StringUtils.isBlank(bean.getCOVERAGES_COVERED_EMPLOYEES().get(i))) 
									sumInsured="0";
								else
									sumInsured=bean.getCOVERAGES_COVERED_EMPLOYEES().get(i);
								if( (StringUtils.isNotBlank(maxSumInsured) || StringUtils.isNotBlank(minSumInsured)) && !"99999".equalsIgnoreCase(coverages_id) ) {
									if( (maxSumInsured!=null && Double.parseDouble(sumInsured)>Double.parseDouble(maxSumInsured))
											|| (minSumInsured!=null && Double.parseDouble(sumInsured)<Double.parseDouble(minSumInsured))
											) {
										valid+="," + coverDisplayName + " exceeded Sum Insured Limit";
									}
								}
							}
							
							if(valid.length()>0 && ",".equalsIgnoreCase(valid.substring(0,1))) {
								valid=valid.substring(1);
							}
							bean.setValidCoverage(valid);
						}
					}
				}
			}
			else if("otpVerfiy".equalsIgnoreCase(type)) {
				 list=new OTPGenerator().getValidate(bean.getOtp(),bean.getOtpId(),bean.getMailOtp());
				}
			else if("getQuoteHomeNew".equalsIgnoreCase(type)){
				
				/*for(int i=0;i<bean.getLocation().size();i++){
					if(StringUtils.isBlank(bean.getLocation().get(i))){
						list.add("Please Enter Location Name in Row "+(i+1));
					}
					try {
						if(StringUtils.isBlank(bean.getLocationStatus().get(i))){
							list.add("Please Choose Location Status in Row "+(i+1));
						}
					} catch (Exception e) {
						list.add("Please Choose Location Status in Row "+(i+1));
					}
				}*/
				
				
				if(StringUtils.isBlank(bean.getInceptionDt())) {
					list.add("error.inceptionDate.required");
				} else if(StringUtils.isNotBlank(bean.getInceptionDt())) {
					final DateFunction datf = new DateFunction();
					final Calendar cal1 = Calendar.getInstance();
					String[] effDT=bean.getInceptionDt().split("/");
					cal1.set(Integer.parseInt(effDT[2]),Integer.parseInt(effDT[1]),Integer.parseInt(effDT[0]));
					final Calendar cal2 = Calendar.getInstance();
					final Map<String,Object> todayDate =  new com.maan.common.dao.CommonDAO().getTodaysDate();
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
						backDate = new com.maan.common.dao.CommonDAO().getBackDatesAllowed(bean.getLoginId(),bean.getUserType(),bean.getProductId(),bean.getBranchCode(),bean.getSchemeId());
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
				
				if(StringUtils.isBlank(bean.getApplicationNo()) || dao.locationDtlsCount(bean,"all")<=0){
					list.add("Please Add Location Details");
				}else if(dao.locationDtlsCount(bean,"active")<=0){
					list.add("Atleast One Location Should be Active");
				}
			}else if("schemeSelection".equalsIgnoreCase(type)){
				if(bean.getSchemeSelect()!=null && bean.getSchemeSelect().size()>0){
					List<Map<String, Object>> activeSchemes = activeSchemeList(bean);
					if(activeSchemes != null && activeSchemes.size()>0){
						boolean schemeSelected = false;
						for(int i=0;i<activeSchemes.size();i++){
							Map<String, Object> activeScheme = activeSchemes.get(i);
							if(bean.getSchemeSelect().get(i)){
								schemeSelected = true;
								if(bean.getSchemeLocation()!=null && bean.getSchemeLocation().size()>0){
									if(bean.getSchemeLocation().get(i)!=null && bean.getSchemeLocation().get(i).size()>0){
									}else{
										list.add("Please Select Atleast One Location For "+(activeScheme.get("SCHEME_NAME")==null?"":activeScheme.get("SCHEME_NAME").toString())+" Scheme");
									}
								}else{
									list.add("Please Select Atleast One Scheme Location");
								}
							}
						}
						if(!schemeSelected){
							list.add("Please Select Atleast One Scheme");
						}
					}else{
						list.add("No Schemes Available To Select");
					}
				}else{
					list.add("Please Select Atleast One Scheme");
				}
			}else if("loadDisc".equalsIgnoreCase(type)){
				if(StringUtils.isNotBlank(bean.getExcessSign())){
					if(!"+".equalsIgnoreCase(bean.getExcessSign()) && !"-".equalsIgnoreCase(bean.getExcessSign())){
						list.add("Please Select Valid Loading/Discount Sign");
					}
				}
				if(StringUtils.isNotBlank(bean.getExcessPremium())){
					try{
						Double.parseDouble(bean.getExcessPremium());
					}catch(Exception e){
						list.add("Please Enter Valid Loading/Discount Premium");
					}
				}

				if(list==null || list.size()<=0){
					String loadDiscError = validateLoadDiscLimit(bean);
					if(StringUtils.isNotBlank(loadDiscError)){
						list.add(loadDiscError);
					}
				}
				
			}else if("referralStatus".equalsIgnoreCase(type)){
				if(StringUtils.isBlank(bean.getAdminRefStatus())){
					list.add("Please Select Referral Status");
				}else if(!"Y".equalsIgnoreCase(bean.getAdminRefStatus()) && !"N".equalsIgnoreCase(bean.getAdminRefStatus()) && !"A".equalsIgnoreCase(bean.getAdminRefStatus())){
					list.add("Please Select Valid Referral Status");
				}
			}else if("policyDateEI".equalsIgnoreCase(type)){
				if(StringUtils.isBlank(bean.getInceptionDt())){
					list.add("Please Select Inception Date");
				}else{
					final DateFunction datf = new DateFunction();
		              final Calendar cal1 = Calendar.getInstance();
		              String[] effDT=bean.getInceptionDt().split("/");
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
		                 e.printStackTrace();
		              }
					  int backDates=0;
					  String backDate="";
		              if(diff > 0){
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
					String numOfDays=new com.maan.common.dao.CommonDAO().getNumberOfDays(bean.getInceptionDt(), bean.getQuoteNo());
					if(Integer.parseInt(numOfDays)<=0){
						list.add("Policy Period is not valid, Please change policy Start and End date");
					}
				}
			}else if("paymentDetails".equalsIgnoreCase(type)) {
				consolidatePremiumDetails(bean);
				list = new PaymentService().validatePayment(bean.getModeOfPayment(),bean.getChequeNo(),bean.getChequeDate(),bean.getChequeAmount(),bean.getFinalPremium(),bean.getBankName(),bean.getMicrCode(),bean.getCashDepositBank(),bean.getCashAmount(),bean.getCashChellanNo(),bean.getCashInstrumentDate(),bean.getInstallmentYN(),bean.getInsIntialAmount(),bean.getMtnMobileNo());
			}else if("particularScheme".equalsIgnoreCase(type)){
				if("Y".equalsIgnoreCase(bean.getModifyYN())){
					List<Map<String, Object>> locationsList = dao.parSchemeActiveLocationsDetail(bean);
					if(locationsList!=null && locationsList.size()>0){
						int locationSize = locationsList.size();
						for(int j=0;j<locationSize;j++){
							Map<String, Object> locationmap = locationsList.get(j);
							if(locationmap!=null && locationmap.size()>0){
								String locationId = locationmap.get("LOCATION_ID")==null?"":locationmap.get("LOCATION_ID").toString();
								String locationName = locationmap.get("LOCATION_NAME")==null?"":locationmap.get("LOCATION_NAME").toString();
								List<Map<String, Object>> coveragesDetail = dao.parSchemeActiveCoveragesDetail(bean);
								if(coveragesDetail!=null && coveragesDetail.size()>0){
									boolean mainCoverageSelected = false;
									for(int i=0;i<coveragesDetail.size();i++){
										Map<String, Object> coveragesMap = coveragesDetail.get(i);
										if(coveragesMap!=null && coveragesMap.size()>0){
											String coverageId = coveragesMap.get("COVERAGES_ID")==null?"":coveragesMap.get("COVERAGES_ID").toString();
											String coverageName = coveragesMap.get("COVERAGES_NAME")==null?"":coveragesMap.get("COVERAGES_NAME").toString();
											String coverageType = coveragesMap.get("COVERAGES_TYPE")==null?"":coveragesMap.get("COVERAGES_TYPE").toString();
											String controlType = coveragesMap.get("CONTROL_TYPE")==null?"":coveragesMap.get("CONTROL_TYPE").toString();
											String smControlType = coveragesMap.get("SUM_INSURED_CONTROL_TYPE")==null?"":coveragesMap.get("SUM_INSURED_CONTROL_TYPE").toString();
											String coverageLimit = coveragesMap.get("COVERAGES_LIMIT")==null?"":coveragesMap.get("COVERAGES_LIMIT").toString();
											String baseRate = coveragesMap.get("BASE_RATE")==null?"":coveragesMap.get("BASE_RATE").toString();
											String sumInsuredLimit = coveragesMap.get("SUM_INSURED_LIMIT")==null?"":coveragesMap.get("SUM_INSURED_LIMIT").toString();
											String minSumInsuredLimit = coveragesMap.get("MIN_SUM_INSURED")==null?"":coveragesMap.get("MIN_SUM_INSURED").toString();
											
											String sumInsured = "0";
											
											if(locationSize==1){
												sumInsured = bean.getSUM_INSURED().get(i+1);
											}else if(locationSize>1){
												sumInsured = bean.getSumInsuredNM()[j][i];
											}
											
											if(StringUtils.isNotBlank(sumInsured)){
												sumInsured = sumInsured.replace(",", "");
											}
											
											if("textbox".equalsIgnoreCase(smControlType)){
												if(StringUtils.isNotBlank(sumInsured) && !isValidDouble(sumInsured)){
													list.add("Location: "+locationName+"| Coverage: "+coverageName+"| Invalid SumInsured");
												}else if(StringUtils.isNotBlank(sumInsured) && isValidDouble(sumInsured)){
													if("B".equalsIgnoreCase(coverageType) && Double.valueOf(sumInsured)>0){
														mainCoverageSelected = true;
													}
												}
											}else if("dropdown".equalsIgnoreCase(smControlType)){
												if("B".equalsIgnoreCase(coverageType) && StringUtils.isNotBlank(sumInsured)){
													mainCoverageSelected = true;
												}
											}
											
											
										}
									}
									if(!mainCoverageSelected){
										list.add("Location: "+locationName+"| Select Atleast One Main Coverage");
									}
								}else{
									list.add("Location: "+locationName+"| No Active Coverages Detail Found");
								}
							}
						}
					}else{
						list.add("No Active Location Detail Found");
					}
				}
			}
		}catch(Exception e){
			list.add("error.validation");
			LogManager.debug(e);
		}
		return list;
	}
	private boolean isValidDouble(String sumInsured) {
		try{
			Double.parseDouble(sumInsured);
			return true;
		}catch(Exception e){
			return false;
		}
	}

	public void updRefRemarks(HomeBean bean, String valid){
		if("Y".equalsIgnoreCase(bean.getModifyYN()))
			dao.updateReferralSumInsRemarks(bean,valid);
		
		List<Map<String,Object>> refList=dao.getReferralList(bean);
		if(refList!=null && refList.size()>0){
			valid="";
			for(int a=0;a<refList.size();a++){
				String referral=refList.get(a).get("REFERRAL_REMARKS")==null?"":refList.get(a).get("REFERRAL_REMARKS").toString();
				if(StringUtils.isNotBlank(referral))
					valid+="," + referral;
			}
		}
		
		if(StringUtils.isNotBlank(valid)  && ",".equalsIgnoreCase(valid.substring(0,1)))
			valid=valid.substring(1);
		
		bean.setValidCoverage(valid);
	}
	public void updOfsReferralRemarks(final String quoteNo, final String referralComments){
		dao.updOfsReferralRemarks(quoteNo, referralComments);
	}
	public void updateReferralRemarks(final String quoteNo, final String referralComments){
		dao.updateReferralRemarks(quoteNo, referralComments);
	}
	public List<Map<String,Object>> getContentList(String productId, String schemeId) {
		return dao.getContentList(productId, schemeId);
	}
	public String getContentName(String productId, String schemeId, String contentTypeId) {
		return dao.getContentName(productId, schemeId, contentTypeId);
	}
	public List<Map<String,String>> getContentCover(HomeBean bean, String productId, String schemeId) {
		return dao.getContentCover(bean,productId,schemeId);
	}
	public Map<String,Object> getCalculatePremium(String quoteNo,String schemeId,String location) {
		return dao.getCalculatePremium(quoteNo,schemeId,location);
	}
	public List<Map<String,Object>> getDropDownList(String type) {
		return dao.getDropDownList(type);
	}
	public String getCoverStatus(String quoteNo, String type) {
		return dao.getCoverStatus(quoteNo, type);
	}
	public String getPaTractorOption(String schemeId, String branchCode) {
		return dao.getPaTractorOption(schemeId, branchCode);
	}
	public List<Map<String,Object>> getExtraBenefitsList() {
		return dao.getExtraBenefitsList();
	}
	public List<Map<String,Object>> getExcessList() {
		return dao.getExcessList();
	}
	public String getSubDetailReferralRemarks(String quoteNo) {
		return dao.getSubDetailReferralRemarks(quoteNo);
	}
	public List<Map<String,Object>> getBuildingAddressList(String quoteNo) {
		return dao.getBuildingAddressList(quoteNo);
	}
	public int getBaseCoverCnt(String quoteNo) {
		return dao.getBaseCoverCnt(quoteNo);
	}
	/*************** Farmer Insurance | PA Cover - Start *******************/
	public void getPaCoverDetails(HomeBean bean) {
		dao.getPaCoverDetails(bean);
	}
	public void updatePaCoverDetails(HomeBean bean) {
		dao.updatePaCoverDetails(bean);
	}
	public void editpaInsuredDetails(HomeBean bean) {
		dao.editpaInsuredDetails(bean);
	}
	public void addpaInsuredDetails(HomeBean bean) {
		dao.addpaInsuredDetails(bean);
	}
	public void delpaInsuredDetails(String applicationNo, String insuredId) {
		dao.delpaInsuredDetails(applicationNo, insuredId);
	}
	public List<Map<String,Object>> getPaInsuredDetailsList(String applicationNo) {
		return dao.getPaInsuredDetailsList(applicationNo);
	}
	public Map<String,Object> getPaDetailsList(String applicationNo) {
		return dao.getPaDetailsList(applicationNo);
	}
	public void paPremiumCalc(String applicationNo, String branchCode, String type) {
		dao.paPremiumCalc(applicationNo, branchCode, type);
	}
	public String updatepaPremiuminfo(HomeBean bean) {
		return dao.updatepaPremiuminfo(bean);
	}
	/*************** Farmer Insurance | PA Cover - End *******************/
	/*************** Farmer Insurance | Motor - Start *******************/
	public void getFirstPageDtls(HomeBean bean) {
		dao.getFirstPageDtls(bean);
	}
	public String getQuoteMotor(HomeBean bean) {
		return dao.getQuoteMotor(bean);
	}
	public List<Map<String,Object>> getMultiVehicleDetails(String applicationNo, String productId, String branchCode) {
		return dao.getMultiVehicleDetails(applicationNo, productId, branchCode);
	}
	public List<Map<String,Object>> getFarmCoverDetailsList(String applicationNo) {
		return dao.getFarmCoverDetailsList(applicationNo);
	}
	public String getCoverPremium(String applicationNo, String type) {
		return dao.getCoverPremium(applicationNo, type);
	}
	public String getCoverReferralRemarks(String quoteNo, String type) {
		return dao.getCoverReferralRemarks(quoteNo, type);
	}
	public List<Map<String,Object>> getTractorCoverList(String applicationNo) {
		return dao.getTractorCoverList(applicationNo);
	}
	public String updatetractorPremiuminfo(HomeBean bean) {
		return dao.updatetractorPremiuminfo(bean);
	}
	public String validateCaptcha(String captchavalue, Captcha captcha) {
		String error = "";
		try {
			if (!captcha.isCorrect(captchavalue)) 
				error = "error.captchacode.invalid";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return error;
	}
	/*************** Farmer Insurance | Motor - End *******************/

	public void getB2CCustomerDetails(HomeBean bean, String loginId) {
		List<Map<String,Object>> map =new CustomerDAO().getB2CCustomerDetails(loginId);
		if(map!=null && map.size()>0){
			for(int i=0;i<map.size();i++){
				bean.setTitle(map.get(i).get("TITLE")==null?"":map.get(i).get("TITLE").toString());
				bean.setCustomerName(map.get(i).get("NAME")==null?"":map.get(i).get("NAME").toString());
				bean.setEmail(map.get(i).get("EMAIL")==null?"":map.get(i).get("EMAIL").toString());
				bean.setMobileNo(map.get(i).get("MOBILE")==null?"":map.get(i).get("MOBILE").toString());
				bean.setAddress1(map.get(i).get("ADDRESS1")==null?"":map.get(i).get("ADDRESS1").toString());
				bean.setAddress2(map.get(i).get("ADDRESS2")==null?"":map.get(i).get("ADDRESS2").toString());
				bean.setPoBox(map.get(i).get("POBOX")==null?"":map.get(i).get("POBOX").toString());
				bean.setCity(map.get(i).get("EMIRATE")==null?"":map.get(i).get("EMIRATE").toString());
				bean.setCustCoreCode(map.get(i).get("MISSIPPI_CUSTOMER_CODE")==null?"":map.get(i).get("MISSIPPI_CUSTOMER_CODE").toString());
				bean.setCoreAppCode(map.get(i).get("MISSIPPI_CUSTOMER_CODE")==null?"":map.get(i).get("MISSIPPI_CUSTOMER_CODE").toString());
				bean.setClientCustomerId(map.get(i).get("CLIENT_CUSTOMER_ID")==null?"":map.get(i).get("CLIENT_CUSTOMER_ID").toString());
				bean.setCustArNo(map.get(i).get("CUST_AR_NO")==null?"":map.get(i).get("CUST_AR_NO").toString());
				bean.setCustomerType(map.get(i).get("CUSTOMER_TYPE")==null?"":map.get(i).get("CUSTOMER_TYPE").toString());
				bean.setCompanyRegNo(map.get(i).get("COMPANY_REG_NO")==null?"":map.get(i).get("COMPANY_REG_NO").toString());
				//bean.setCustLastName()
			}
		}
	}
	/*public List<Map<String,Object>> getSubDetailsCount(String quoteNo) {
		return dao.getSubDetailsCount(quoteNo);
	}*/

	public List<Map<String, Object>> getHomePolicyDetails(String policyNo,String productId, String branchCode) {
		return dao.getHomePolicyDetails(policyNo,productId,branchCode);
	}

	public String getEmailCount(HomeBean bean) {
		return dao.getEmailCount(bean);
	}

	public List<Map<String, Object>> getSumInsuredList(HomeBean bean) {
		return dao.getSumInsuredList(bean);
	}

	public void ofsDataDetailTempInsert(HomeBean bean) {
		dao.ofsDataDetailTempInsert(bean);
	}

	public void deleteOfsDataDtlTempIns(HomeBean bean) {
		dao.deleteOfsDataDtlTempIns(bean);
	}

	public void setQuoteSchemeDtls(HomeBean bean) {
		dao.setQuoteSchemeDtls(bean);
	}

	public List<Map<String, Object>> extendedCoverList(HomeBean bean) {
		return dao.extendedCoverList(bean);
	}

	public int insExtendedCoverage(HomeBean bean) {
		return dao.insExtendedCoverage(bean);
	}

	public List<Map<String, Object>> warrantiesList(HomeBean bean) {
		return dao.warrantiesList(bean);
	}

	public List<Map<String, Object>> exclusionsList(HomeBean bean) {
		return dao.exclusionsList(bean);
	}

	public List<Map<String, Object>> excessList(HomeBean bean) {
		return dao.excessList(bean);
	}

	public void setSelectedCover(HomeBean bean) {
		dao.setSelectedCover(bean);
	}

	public int checkSchemeExist(HomeBean bean, String schemeId) {
		return dao.checkSchemeExist(bean,schemeId);
	}

	public void setExistSchemeDtls(HomeBean bean) {
		dao.setExistSchemeDtls(bean);
		
	}

	public void updateCalculatedSumIns(HomeBean bean) {
		dao.updateCalculatedSumIns(bean);
		
	}

	public int updateExcess(HomeBean bean) {
		return dao.updateExcess(bean);
	}

	public String getExtendedReferralRemarks(String quoteNo, String type) {
		return dao.getExtendedReferralRemarks(quoteNo,type);
	}

	public List<Map<String, Object>> setLossLimbsList(HomeBean bean) {
		return dao.setLossLimbsList(bean);
	}

	public int getInsertLocationDetails(HomeBean bean) {
		return dao.getInsertLocationDetails(bean);
	}

	public List<Map<String, Object>> setCustomerAndLocDetail(HomeBean bean) {
		return dao.setCustomerAndLocDetail(bean);
	}

	public int updateLocationScheme(HomeBean bean, String location,List<String> sList) {
		return dao.updateLocationScheme(bean,location,sList);
	}

	public List<String> getLocationDtls(HomeBean bean) {
		return dao.getLocationDtls(bean);
	}

	public String getLocationSizeDetails(HomeBean bean, String type) {
		return dao.getLocationSizeDetails(bean,type);
	}

	public void setSumInsuredDetails(HomeBean bean) {
		dao.setSumInsuredDetails(bean);
	}

	public void setQuoteDetails(HomeBean bean) {
		dao.setQuoteDetails(bean);
	}
	
	public List<Map<String,Object>> getHomeInfoDesc(HomeBean bean){
		return dao.getHomeInfoDesc(bean);
	}

	public int deleteExist(HomeBean bean) {
		return dao.deleteExist(bean);
	}

	public int updateLocationSchemeEmpty(HomeBean bean) {
		return dao.updateLocationSchemeEmpty(bean);
	}

	public List<Map<String, Object>> getSelectedSchemeLocation(HomeBean bean) {
		return dao.getSelectedSchemeLocation(bean);
	}

	public void setLocationDetails(HomeBean bean) {
		dao.setLocationDetails(bean);
		
	}

	public int getDeleteLocationDetails(HomeBean bean) {
		return dao.getDeleteLocationDetails(bean);
	}

	public int getUpdateLocationDetails(HomeBean bean) {
		return dao.getUpdateLocationDetails(bean);
	}

	public List<Map<String, Object>> getSchemeLocationList(HomeBean bean) {
		return dao.getSchemeLocationList(bean);
	}

	public List<Map<String, Object>> getDropDownSchemeList(HomeBean bean) {
		return dao.getDropDownSchemeList(bean);
	}
	
	public List<Map<String,Object>> getHomePremiumInfoNew(HomeBean bean){
		return dao.getHomePremiumInfoNew(bean);
	}

	public void setlocationPremiumValues(HomeBean bean) {
		dao.setlocationPremiumValues(bean);
	}
	
	
	
	
	public List<Map<String, Object>> locationDtlsList(HomeBean bean) {
		return dao.locationDtlsList(bean);
	}

	public boolean editLocationDetails(HomeBean bean) {
		return dao.editLocationDetails(bean);
	}

	public boolean manipulateLocationDetails(HomeBean bean) {
		return dao.manipulateLocationDetails(bean);
	}

	public void editQuoteDetails(HomeBean bean) {
		dao.editQuoteDetails(bean);
	}

	public boolean manipulateHomePosition(HomeBean bean) {
		return dao.manipulateHomePosition(bean);
	}

	public void updateLocationDtls(HomeBean bean) {
		dao.updateLocationDtls(bean);
	}

	public List<Map<String, Object>> activeSchemeList(HomeBean bean) {
		return dao.activeSchemeList(bean);
	}

	public List<Map<String, Object>> activeLocationDtls(HomeBean bean) {
		return dao.activeLocationDtls(bean);
	}

	public void editLocationScheme(HomeBean bean) {
		try{
			if(StringUtils.isNotBlank(bean.getQuoteNo()) && StringUtils.isNotBlank(bean.getApplicationNo())){
				List<Map<String, Object>> activeLocations = activeLocationDtls(bean);
				List<Map<String, Object>> activeSchemes = activeSchemeList(bean);
				if(activeSchemes!=null && activeSchemes.size()>0){
					List<Boolean> schemeSelect = new ArrayList<Boolean>();
					List<List<String>> schemeLocation = new ArrayList<List<String>>();
					for(int i=0;i<activeSchemes.size();i++){
						boolean isSchemeAvail = false;
						List<String> selectedLocations = new ArrayList<String>();
						Map<String, Object> activeScheme = activeSchemes.get(i);
						if(activeScheme!=null && activeScheme.size()>0){
							String schemeId = activeScheme.get("SCHEME_ID")==null?"":activeScheme.get("SCHEME_ID").toString();
							if(activeLocations!=null && activeLocations.size()>0){
								for(int j=0;j<activeLocations.size();j++){
									Map<String, Object> activeLocation = activeLocations.get(j);
									if(activeLocation!=null && activeLocation.size()>0){
										String locationId = activeLocation.get("LOCATION_ID")==null?"":activeLocation.get("LOCATION_ID").toString();
										String locationSchemes = activeLocation.get("SCHEME_ID")==null?"":activeLocation.get("SCHEME_ID").toString();
										List<String> loactionSchemeList =  Arrays.asList(locationSchemes.split(","));
										if(loactionSchemeList.contains(schemeId)){
											isSchemeAvail = true;
											selectedLocations.add(locationId);
										}
									}
								}
							}
						}
						schemeSelect.add(i, isSchemeAvail);
						schemeLocation.add(i, selectedLocations);
					}
					bean.setSchemeSelect(schemeSelect);
					bean.setSchemeLocation(schemeLocation);
				}
			}
		}catch(Exception e){
			LogManager.info("Exception @ HomeService.editLocationScheme(): "+e);
			e.printStackTrace();
		}
	}

	public boolean updateLocationScheme(HomeBean bean) {
		boolean status = false;
		try{
			if(bean.getSchemeSelect()!=null && bean.getSchemeSelect().size()>0
					&& bean.getSchemeLocation()!=null && bean.getSchemeLocation().size()>0){
				List<Map<String, Object>> activeLocations = activeLocationDtls(bean);
				List<Map<String, Object>> activeSchemes = activeSchemeList(bean);
				if(activeLocations!=null && activeLocations.size()>0){
					for(int i=0;i<activeLocations.size();i++){
						Map<String, Object> activeLocation = activeLocations.get(i);
						if(activeLocation!=null && activeLocation.size()>0){
							String locationId = activeLocation.get("LOCATION_ID")==null?"":activeLocation.get("LOCATION_ID").toString();
							String locationSchemes = "";
							if(StringUtils.isNotBlank(locationId)){
								if(activeSchemes!=null && activeSchemes.size()>0){
									for(int j=0;j<activeSchemes.size();j++){
										if(bean.getSchemeSelect().get(j)){
											Map<String, Object> activeScheme = activeSchemes.get(j);
											if(activeScheme!=null && activeScheme.size()>0){
												String schemeId = activeScheme.get("SCHEME_ID")==null?"":activeScheme.get("SCHEME_ID").toString();
												if(StringUtils.isNotBlank(schemeId)){
													if(bean.getSchemeLocation().get(j)!=null && bean.getSchemeLocation().get(j).size()>0){
														if(bean.getSchemeLocation().get(j).contains(locationId)){
															locationSchemes += schemeId+",";
														}
													}
												}
											}
										}
									}
								}
								if(locationSchemes.endsWith(",")){
									locationSchemes = locationSchemes.substring(0, locationSchemes.length()-1);
								}
								status = dao.updateLocationScheme(locationSchemes, locationId, bean.getQuoteNo(), bean.getApplicationNo());
							}
						}
					}
				}
			}
		}catch(Exception e){
			status = false;
			LogManager.info("Exception @ HomeService.updateLocationScheme(): "+e);
			e.printStackTrace();
		}
		return status;
	}

	public void schemeSelected(HomeBean bean) {
		try{
			if(StringUtils.isNotBlank(bean.getQuoteNo()) && StringUtils.isNotBlank(bean.getApplicationNo())){
				List<Map<String, Object>> activeLocations = activeLocationDtls(bean);
				List<Map<String, Object>> activeSchemes = activeSchemeList(bean);
				if(activeSchemes!=null && activeSchemes.size()>0){
					List<String> schemeSelect = new ArrayList<String>();
					for(int i=0;i<activeSchemes.size();i++){
						boolean isSchemeAvail = false;
						Map<String, Object> activeScheme = activeSchemes.get(i);
						if(activeScheme!=null && activeScheme.size()>0){
							String schemeId = activeScheme.get("SCHEME_ID")==null?"":activeScheme.get("SCHEME_ID").toString();
							if(activeLocations!=null && activeLocations.size()>0){
								for(int j=0;j<activeLocations.size();j++){
									Map<String, Object> activeLocation = activeLocations.get(j);
									if(activeLocation!=null && activeLocation.size()>0){
										String locationSchemes = activeLocation.get("SCHEME_ID")==null?"":activeLocation.get("SCHEME_ID").toString();
										List<String> loactionSchemeList =  Arrays.asList(locationSchemes.split(","));
										if(loactionSchemeList.contains(schemeId)){
											isSchemeAvail = true;
										}
									}
								}
							}
							if(isSchemeAvail){
								schemeSelect.add(schemeId);
							}
						}
					}
					bean.setSchemeSelected(StringUtils.join(schemeSelect,","));
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public boolean updatePremiumHpm(HomeBean bean) {
		return dao.updatePremiumHpm(bean);
	}
	
	public void consolidatePremiumDetails(HomeBean bean){
		try{
			Map<String, Object> map = dao.consolidatePremiumDetails(bean);
			if(map!=null && map.size()>0){
				 bean.setActualPremium(map.get("ACTUAL_PREMIUM")==null?"0":map.get("ACTUAL_PREMIUM").toString());
				 bean.setActualOptionalPremium(map.get("ACTUAL_OPPREMIUM")==null?"0":map.get("ACTUAL_OPPREMIUM").toString());
				 bean.setVolDiscountAmount(map.get("VOLUME_DISCOUNT_AMOUNT")==null?0:Double.valueOf(map.get("VOLUME_DISCOUNT_AMOUNT").toString()));
				 bean.setCorpDiscountAmount(map.get("CORPORATE_DISCOUNT_AMOUNT")==null?0:Double.valueOf(map.get("CORPORATE_DISCOUNT_AMOUNT").toString()));
				 bean.setSplDiscountAmount(map.get("SPECIAL_DISCOUNT_AMOUNT")==null?0:Double.valueOf(map.get("SPECIAL_DISCOUNT_AMOUNT").toString()));
				 bean.setTotalPremium(map.get("PREMIUM")==null?"0":map.get("PREMIUM").toString());
				 bean.setPolicyFeePercent(map.get("POLICY_FEE_PERCENT")==null?"0":map.get("POLICY_FEE_PERCENT").toString());
				 bean.setPolicyFee(map.get("POLICY_FEE")==null?"0":map.get("POLICY_FEE").toString());
				 bean.setExcessSign(map.get("EXCESS_SIGN")==null?"0":map.get("EXCESS_SIGN").toString());
				 bean.setExcessPremium(map.get("EXCESS_PREMIUM")==null?"0":map.get("EXCESS_PREMIUM").toString());
				 bean.setFinalPremium(map.get("OVERALL_PREMIUM")==null?"0":map.get("OVERALL_PREMIUM").toString());
			 }
		}catch(Exception e){
			LogManager.info("Exception @ consolidatePremiumDetails() Error: "+e);
			e.printStackTrace();
		}
	}
	
	public void setCustDetail(HomeBean bean) {
        try {
			List<Map<String,Object>> map =new CustomerDAO().customerDetails(bean.getCustomerId());
			if(map!=null && map.size()>0){
			    for(int i=0;i<map.size();i++){
			    	bean.setTitle(map.get(i).get("TITLE")==null?"":map.get(i).get("TITLE").toString());
			        bean.setCustomerName(map.get(i).get("FIRST_NAME")==null?"":map.get(i).get("FIRST_NAME").toString());
			        bean.setCustLastName(map.get(i).get("LAST_NAME")==null?"":map.get(i).get("LAST_NAME").toString());
			        bean.setEmail(map.get(i).get("EMAIL")==null?"":map.get(i).get("EMAIL").toString());
			        bean.setMobileNo(map.get(i).get("MOBILE")==null?"":map.get(i).get("MOBILE").toString());
			        bean.setAddress1(map.get(i).get("ADDRESS1")==null?"":map.get(i).get("ADDRESS1").toString());
			        bean.setAddress2(map.get(i).get("ADDRESS2")==null?"":map.get(i).get("ADDRESS2").toString());
			        bean.setPoBox(map.get(i).get("POBOX")==null?"":map.get(i).get("POBOX").toString());
			        bean.setCity(map.get(i).get("EMIRATE")==null?"":map.get(i).get("EMIRATE").toString());
			        bean.setCustCoreCode(map.get(i).get("MISSIPPI_CUSTOMER_CODE")==null?"":map.get(i).get("MISSIPPI_CUSTOMER_CODE").toString());
			        bean.setCoreAppCode(map.get(i).get("MISSIPPI_CUSTOMER_CODE")==null?"":map.get(i).get("MISSIPPI_CUSTOMER_CODE").toString());
			        bean.setClientCustomerId(map.get(i).get("CLIENT_CUSTOMER_ID")==null?"":map.get(i).get("CLIENT_CUSTOMER_ID").toString());
			        bean.setCustArNo(map.get(i).get("CUST_AR_NO")==null?"":map.get(i).get("CUST_AR_NO").toString());
			        bean.setCustomerType(map.get(i).get("CUSTOMER_TYPE")==null?"":map.get(i).get("CUSTOMER_TYPE").toString());
			        bean.setCompanyRegNo(map.get(i).get("COMPANY_REG_NO")==null?"":map.get(i).get("COMPANY_REG_NO").toString());
			        bean.setCustdob(map.get(i).get("DOB")==null?"":map.get(i).get("DOB").toString());
			        bean.setGender(map.get(i).get("GENDER")==null?"":map.get(i).get("GENDER").toString());
			        bean.setOccupation(map.get(i).get("OCCUPATION")==null?"":map.get(i).get("OCCUPATION").toString());
			        bean.setCustAlterMobileNo(map.get(i).get("ALTERNATE_MOBILE")==null?"":map.get(i).get("ALTERNATE_MOBILE").toString());
			        bean.setCustnrc1(map.get(i).get("NRC1")==null?"":map.get(i).get("NRC1").toString());
					bean.setCustnrc2(map.get(i).get("NRC2")==null?"":map.get(i).get("NRC2").toString());
					bean.setCustnrc3(map.get(i).get("NRC3")==null?"":map.get(i).get("NRC3").toString());
					bean.setCustPassportNo(map.get(i).get("PASSPORT_NUMBER")==null?"":map.get(i).get("PASSPORT_NUMBER").toString());
			    }
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

	public void editReferralRemarks(HomeBean bean) {
		dao.editReferralRemarks(bean);
	}

	public boolean updateLoadingDiscountHpm(HomeBean bean) {
		return dao.updateLoadingDiscountHpm(bean);
	}

	public boolean adminReferralUpdateHpm(HomeBean bean) {
		return dao.adminReferralUpdateHpm(bean);
	}

	public void adminReferralStatus(HomeBean bean) {
		dao.adminReferralStatus(bean);
	}

	public int installmentcount(HomeBean bean) {
		return dao.installmentcount(bean);
	}

	public void deleteinstallment(HomeBean bean) {
		dao.deleteinstallment(bean);
	}

	public boolean updateInceptionDate(HomeBean bean) {
		return dao.updateInceptionDate(bean);
	}

	public Map checkPolicy(HomeBean bean) {
		return dao.checkPolicy(bean);
	}

	public String validateLoadDiscLimit(HomeBean bean) {
		String error = "";
		try{
			String loginId = quoteBelongingLoginid(bean);
			if(StringUtils.isNotBlank(loginId)){
				Map<String,Object> map = loadDiscValPremiumDtls(loginId, bean.getProductId());
				if(map!= null && map.size()>0){
					double minLoading = map.get("MIN_LOADING")==null?0:Double.valueOf(map.get("MIN_LOADING").toString());
					double maxLoading = map.get("MAX_LOADING")==null?0:Double.valueOf(map.get("MAX_LOADING").toString());
					double minDiscount = map.get("MIN_DISCOUNT")==null?0:Double.valueOf(map.get("MIN_DISCOUNT").toString());
					double maxDiscount = map.get("MAX_DISCOUNT")==null?0:Double.valueOf(map.get("MAX_DISCOUNT").toString());
					double loadDiscPercent = loadDiscPercent(bean);
					String excessSign = StringUtils.isBlank(bean.getExcessSign())?"+":bean.getExcessSign();
					
					if("+".equalsIgnoreCase(excessSign)){
						if(loadDiscPercent>=minLoading && loadDiscPercent<=maxLoading){
						}else{
							error = "Loading Amount Should be in the range from "+minLoading+"% to "+maxLoading+"%";
						}
					}else if("-".equalsIgnoreCase(excessSign)){
						if(loadDiscPercent>=minDiscount && loadDiscPercent<=maxDiscount){
						}else{
							error = "Discount Amount Should be in the range from "+minDiscount+"% to "+maxDiscount+"%";
						}
					}
				}else{
					error = "Loading and Dicount Configuration missing for this Broker";
				}
			}else{
				error = "No Specific Broker Attached for this Quote.";
			}
		}catch(Exception e){
			LogManager.info("Exception @ HomeService.validateLoadDiscLimit() Error: "+e);
			e.printStackTrace();
			error =  "Something Went Wrong. Please Contact technical Team.";
		}
		return error;
	}

	private double loadDiscPercent(HomeBean bean) {
		return dao.loadDiscPercent(bean);
	}

	public Map<String, Object> loadDiscValPremiumDtls(String loginId, String productId) {
		return dao.loadDiscValPremiumDtls(loginId, productId);
	}

	public String quoteBelongingLoginid(HomeBean bean) {
		return dao.quoteBelongingLoginid(bean);
	}

	public String schemeNameById(String schemeId, String productId) {
		return dao.schemeNameById(schemeId, productId);
	}

	public int manipulateExcessDtls(HomeBean bean) {
		int ins = 0;
		try{
			dao.deleteExcessDetails(bean);
			if(bean.getExcessIdAr()!=null && bean.getExcessIdAr().size()>0){
				for(int i=0; i<bean.getExcessIdAr().size();i++){
					if(StringUtils.isNotBlank(bean.getExcessIdAr().get(i))
							&& StringUtils.isNotBlank(bean.getExcessPercentAr().get(i))
							&& StringUtils.isNotBlank(bean.getExcessAmountAr().get(i))
							&& StringUtils.isNotBlank(bean.getExcessDescAr().get(i))){
						if(!bean.getExcessDeleteAr().get(i)){
							ins += dao.insertExcessDetails(bean.getQuoteNo(), bean.getSchemeId(), bean.getProductId(),
									bean.getExcessIdAr().get(i), bean.getExcessPercentAr().get(i), bean.getExcessAmountAr().get(i),
									bean.getExcessDescAr().get(i));
						}
					}
				}
				List<Boolean> deletereset = new ArrayList<Boolean>();
				bean.setExcessDeleteAr(deletereset);
			}
		}catch(Exception e){
			LogManager.info("Exception @ HomeService.manipulateExcessDtls() Error: "+e);
			e.printStackTrace();
		}
		return ins;
	}

	public int manipulateWarrantyDtls(HomeBean bean) {
		int ins = 0;
		try{
			dao.deleteWarrantyDetails(bean);
			if(bean.getWarrantiesIdAr()!=null && bean.getWarrantiesIdAr().size()>0){
				for(int i=0; i<bean.getWarrantiesIdAr().size();i++){
					if(StringUtils.isNotBlank(bean.getWarrantiesIdAr().get(i))
							&& StringUtils.isNotBlank(bean.getWarrantiesDescAr().get(i))){
						if(!bean.getWarrantiesDeleteAr().get(i)){
							ins += dao.insertWarrantyDetails(bean.getQuoteNo(), bean.getSchemeId(), bean.getProductId(),
									bean.getWarrantiesIdAr().get(i), bean.getWarrantiesDescAr().get(i));
						}
					}
				}
				List<Boolean> deletereset = new ArrayList<Boolean>();
				bean.setWarrantiesDeleteAr(deletereset);
			}
		}catch(Exception e){
			LogManager.info("Exception @ HomeService.manipulateWarrantyDtls() Error: "+e);
			e.printStackTrace();
		}
		return ins;
	}

	public int manipulateExclusionDtls(HomeBean bean) {
		int ins = 0;
		try{
			dao.deleteExclusionDetails(bean);
			if(bean.getExclusionsIdAr()!=null && bean.getExclusionsIdAr().size()>0){
				for(int i=0; i<bean.getExclusionsIdAr().size();i++){
					if(StringUtils.isNotBlank(bean.getExclusionsIdAr().get(i))
							&& StringUtils.isNotBlank(bean.getExclusionsDescAr().get(i))){
						if(!bean.getExclusionsDeleteAr().get(i)){
							ins += dao.insertExclusionDetails(bean.getQuoteNo(), bean.getSchemeId(), bean.getProductId(),
									bean.getExclusionsIdAr().get(i), bean.getExclusionsDescAr().get(i));
						}
					}
				}
				List<Boolean> deletereset = new ArrayList<Boolean>();
				bean.setExclusionsDeleteAr(deletereset);
			}
		}catch(Exception e){
			LogManager.info("Exception @ HomeService.manipulateExclusionDtls() Error: "+e);
			e.printStackTrace();
		}
		return ins;
	}
	
	public boolean premiumCalcIndividualScheme(HomeBean bean){
		try{
			double minimumPremium = 0;
			
			List<Map<String, Object>> minimumPremiumList = dao.brokerMinimumPremium(bean);
			if(minimumPremiumList!=null && minimumPremiumList.size()>0){
				Map<String, Object> minimumPremiumMap = minimumPremiumList.get(0);
				if(minimumPremiumMap!=null && minimumPremiumMap.size()>0){
					try{
						minimumPremium = (minimumPremiumMap.get("min_premium_amount")==null?0:Double.valueOf(minimumPremiumMap.get("min_premium_amount").toString()));
					}catch(Exception e){
						minimumPremium = 0;
					}
				}
			}
			
			dao.deleteSchemePremDtls(bean.getQuoteNo(), bean.getProductId(), bean.getSchemeId());
			
			List<Map<String, Object>> schemeAvailLocPremList = dao.schemeAvailLocPremList(bean);
			if(schemeAvailLocPremList!=null && schemeAvailLocPremList.size()>0){
				for(int i=0;i<schemeAvailLocPremList.size();i++){
					Map<String, Object> schemeAvailLocPremMap = schemeAvailLocPremList.get(i);
					if(schemeAvailLocPremMap!=null && schemeAvailLocPremMap.size()>0){
						String locationId = schemeAvailLocPremMap.get("LOCATION_ID")==null?"":schemeAvailLocPremMap.get("LOCATION_ID").toString();
						double totalPremium = 0;
						try{
							totalPremium = schemeAvailLocPremMap.get("PREMIUM_AMOUNT")==null?0:Double.valueOf(schemeAvailLocPremMap.get("PREMIUM_AMOUNT").toString());
						}catch(Exception e){
							totalPremium = 0;
						}
						
						double basePremium = dao.schemeLocPremium(bean.getQuoteNo(), bean.getProductId(),
								bean.getSchemeId(), locationId, "B");
						double optionalPremium = dao.schemeLocPremium(bean.getQuoteNo(), bean.getProductId(),
								bean.getSchemeId(), locationId, "O");
						
						double totalPremiumDerived = (basePremium+optionalPremium);
						
						LogManager.info("QuoteNo: "+bean.getQuoteNo()+" SchemeId: "+bean.getSchemeId()
								+ " ProductId: "+bean.getProductId()+" LocationId: "+locationId
								+ " Total Premium Derived: "+totalPremiumDerived+" Total Premium: "+totalPremium);
						
						String minimumPremiumFlag = "N";
						String remarks = "";
						
						if(totalPremium<minimumPremium){
							minimumPremiumFlag = "Y";
							remarks = "Actual Premium "+totalPremium+" Falls Below Minimum Premium. So Minimum Premium of "+minimumPremium+" is Applied";
						}
						
						dao.insertScheemLocPremDtls(bean.getQuoteNo(), bean.getApplicationNo(), bean.getProductId(), bean.getSchemeId(),
								locationId, basePremium, optionalPremium, totalPremium, minimumPremiumFlag,
								("Y".equalsIgnoreCase(minimumPremiumFlag)?minimumPremium:totalPremium), remarks);
					}
				}
			}
			dao.deleteUnAvailableSchemes(bean);
			return true;
		}catch(Exception e){
			LogManager.info("Exception @ HomeService.premiumCalcIndividualScheme()"
					+ " QuoteNo: "+bean.getQuoteNo()+" ApplicationNo: "+bean.getApplicationNo()+" ProductId: "+bean.getProductId()+""
					+ " SchemeId: "+bean.getSchemeId()+" Error: "+e);
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean premiumCalcIndividualSchemeLoc(HomeBean bean){
		try{
			double minimumPremium = 0;
			
			List<Map<String, Object>> minimumPremiumList = dao.brokerMinimumPremium(bean);
			if(minimumPremiumList!=null && minimumPremiumList.size()>0){
				Map<String, Object> minimumPremiumMap = minimumPremiumList.get(0);
				if(minimumPremiumMap!=null && minimumPremiumMap.size()>0){
					try{
						minimumPremium = (minimumPremiumMap.get("min_premium_amount")==null?0:Double.valueOf(minimumPremiumMap.get("min_premium_amount").toString()));
					}catch(Exception e){
						minimumPremium = 0;
					}
				}
			}
			
			dao.deleteSchemeLocPremDtls(bean.getQuoteNo(), bean.getProductId(), bean.getSchemeId(), bean.getLocationId());
			
			List<Map<String, Object>> schemeAvailLocPremList = dao.schemeAvailLocParPremList(bean);
			if(schemeAvailLocPremList!=null && schemeAvailLocPremList.size()>0){
				for(int i=0;i<schemeAvailLocPremList.size();i++){
					Map<String, Object> schemeAvailLocPremMap = schemeAvailLocPremList.get(i);
					if(schemeAvailLocPremMap!=null && schemeAvailLocPremMap.size()>0){
						String locationId = schemeAvailLocPremMap.get("LOCATION_ID")==null?"":schemeAvailLocPremMap.get("LOCATION_ID").toString();
						double totalPremium = 0;
						try{
							totalPremium = schemeAvailLocPremMap.get("PREMIUM_AMOUNT")==null?0:Double.valueOf(schemeAvailLocPremMap.get("PREMIUM_AMOUNT").toString());
						}catch(Exception e){
							totalPremium = 0;
						}
						
						double basePremium = dao.schemeLocPremium(bean.getQuoteNo(), bean.getProductId(),
								bean.getSchemeId(), locationId, "B");
						double optionalPremium = dao.schemeLocPremium(bean.getQuoteNo(), bean.getProductId(),
								bean.getSchemeId(), locationId, "O");
						
						double totalPremiumDerived = (basePremium+optionalPremium);
						
						LogManager.info("QuoteNo: "+bean.getQuoteNo()+" SchemeId: "+bean.getSchemeId()
								+ " ProductId: "+bean.getProductId()+" LocationId: "+locationId
								+ " Total Premium Derived: "+totalPremiumDerived+" Total Premium: "+totalPremium);
						
						String minimumPremiumFlag = "N";
						String remarks = "";
						
						if(totalPremium<minimumPremium){
							minimumPremiumFlag = "Y";
							remarks = "Actual Premium "+totalPremium+" Falls Below Minimum Premium. So Minimum Premium of "+minimumPremium+" is Applied";
						}
						
						dao.insertScheemLocPremDtls(bean.getQuoteNo(), bean.getApplicationNo(), bean.getProductId(), bean.getSchemeId(),
								locationId, basePremium, optionalPremium, totalPremium, minimumPremiumFlag,
								("Y".equalsIgnoreCase(minimumPremiumFlag)?minimumPremium:totalPremium), remarks);
					}
				}
			}
			dao.deleteUnAvailableSchemes(bean);
			return true;
		}catch(Exception e){
			LogManager.info("Exception @ HomeService.premiumCalcIndividualSchemeLoc()"
					+ " QuoteNo: "+bean.getQuoteNo()+" ApplicationNo: "+bean.getApplicationNo()+" ProductId: "+bean.getProductId()+""
					+ " SchemeId: "+bean.getSchemeId()+" LocationId: "+bean.getLocationId()+" Error: "+e);
			e.printStackTrace();
		}
		return false;
	}

	public boolean singleLocationAutoSelect(HomeBean bean) {
		try{
			List<Map<String, Object>> activeLocations = activeLocationDtls(bean);
			if(activeLocations!=null && activeLocations.size()==1){
				Map<String, Object> activeLocation = activeLocations.get(0);
				if(activeLocation!=null && activeLocation.size()>0){
					String singleActiveLocationId = activeLocation.get("LOCATION_ID")==null?"":activeLocation.get("LOCATION_ID").toString();
					if(bean.getSchemeSelect()!=null && bean.getSchemeSelect().size()>0){
						List<List<String>> schemeLocationList = new ArrayList<List<String>>();
						for(int i=0;i<bean.getSchemeSelect().size();i++){
							List<String> specificSchemeLocationList = new ArrayList<String>();
							if(bean.getSchemeSelect().get(i)){
								specificSchemeLocationList.add(singleActiveLocationId);
							}
							schemeLocationList.add(specificSchemeLocationList);
						}
						bean.setSchemeLocation(schemeLocationList);
					}
				}
			}
			return true;
		}catch(Exception e){
			LogManager.info("Exception @ HomeService.singleLocationAutoSelect() Error: "+e);
			e.printStackTrace();
		}
		return false;
	}

	public void calcDiscountPremium(HomeBean bean) {
		try{
			if("SchemeLoc".equalsIgnoreCase(bean.getCalcFrom())){
				
				bean.setSchemeId(bean.getDropDownScheme());
				bean.setLocationId(bean.getDropDownLocation());
				premiumCalcIndividualSchemeLoc(bean);
				
				Map<String, Object> schLocDiscDtls = dao.validationSchLocDiscCalcDtls(bean);
				if(schLocDiscDtls!=null && schLocDiscDtls.size()>0){
					double volDiscLimit = schLocDiscDtls.get("VOLUME_DISCOUNT_LIMIT")==null?0:Double.valueOf(schLocDiscDtls.get("VOLUME_DISCOUNT_LIMIT").toString());
					double corpDiscLimit = schLocDiscDtls.get("CORPORATE_DISCOUNT_LIMIT")==null?0:Double.valueOf(schLocDiscDtls.get("CORPORATE_DISCOUNT_LIMIT").toString());
					double splDiscLimit = schLocDiscDtls.get("SPECIAL_DISCOUNT_LIMIT")==null?0:Double.valueOf(schLocDiscDtls.get("SPECIAL_DISCOUNT_LIMIT").toString());
					double minPremLimit = schLocDiscDtls.get("MIN_PREMIUM_AMOUNT")==null?0:Double.valueOf(schLocDiscDtls.get("MIN_PREMIUM_AMOUNT").toString());
					double premiumApplied = schLocDiscDtls.get("PREMIUM_APPLIED")==null?0:Double.valueOf(schLocDiscDtls.get("PREMIUM_APPLIED").toString());
					String minPremiumYn = schLocDiscDtls.get("MINIMUM_PREMIUM_YN")==null?"N":schLocDiscDtls.get("MINIMUM_PREMIUM_YN").toString();
					String errors = "";
					
					if(!"Y".equalsIgnoreCase(minPremiumYn)){
						double volDiscPercIns = 0;
						double volDiscAmtIns = 0;
						double corpDiscPercIns = 0;
						double corpDiscAmtIns = 0;
						double splDiscPercIns = 0;
						double splDiscAmtIns = 0;
						double premiumAppliedIns = 0;
						String minPremiumYnIns = "N";
						
						premiumAppliedIns = premiumApplied;
						if(premiumAppliedIns<minPremLimit){
							premiumAppliedIns = minPremLimit;
							minPremiumYnIns = "Y";
						}
						
						if(!"Y".equalsIgnoreCase(minPremiumYn)){
							
							if(bean.getAjVolDiscountPercent()>0){
								if(volDiscLimit>=bean.getAjVolDiscountPercent()){
									double volDiscountTemp = premiumAppliedIns*(bean.getAjVolDiscountPercent()/100);
									double premiumAfterVolDicountTemp = premiumAppliedIns - volDiscountTemp;
									if(premiumAfterVolDicountTemp<minPremLimit){
										errors += "Larger Volume Discount will not be Apllied. Since it will falls below Minimum Premium: "+minPremLimit+"~";
									}else{
										volDiscPercIns = bean.getAjVolDiscountPercent();
										volDiscAmtIns = volDiscountTemp;
										premiumAppliedIns = premiumAfterVolDicountTemp;
									}
								}else{
									errors += "Larger Volume Discount Should not be greater than "+volDiscLimit+"%"+"~";
								}
							}
							
							if(bean.getAjCorpDiscountPercent()>0){
								if(corpDiscLimit>=bean.getAjCorpDiscountPercent()){
									double corpDiscountTemp = premiumAppliedIns*(bean.getAjCorpDiscountPercent()/100);
									double premiumAfterCorpDicountTemp = premiumAppliedIns - corpDiscountTemp;
									if(premiumAfterCorpDicountTemp<minPremLimit){
										errors += "Corporate Discount will not be Apllied. Since it will falls below Minimum Premium: "+minPremLimit+"~";
									}else{
										corpDiscPercIns = bean.getAjCorpDiscountPercent();
										corpDiscAmtIns = corpDiscountTemp;
										premiumAppliedIns = premiumAfterCorpDicountTemp;
									}
								}else{
									errors += "Corporate Discount Should not be greater than "+corpDiscLimit+"%"+"~";
								}
							}
							
							if(bean.getAjSplDiscountPercent()>0){
								if(splDiscLimit>=bean.getAjSplDiscountPercent()){
									double splDiscountTemp = premiumAppliedIns*(bean.getAjSplDiscountPercent()/100);
									double premiumAfterSplDicountTemp = premiumAppliedIns - splDiscountTemp;
									if(premiumAfterSplDicountTemp<minPremLimit){
										errors += "Special Discount will not be Apllied. Since it will falls below Minimum Premium: "+minPremLimit+"~";
									}else{
										splDiscPercIns = bean.getAjSplDiscountPercent();
										splDiscAmtIns = splDiscountTemp;
										premiumAppliedIns = premiumAfterSplDicountTemp;
									}
								}else{
									errors += "Special Discount Should not be greater than "+splDiscLimit+"%"+"~";
								}
							}
							
							dao.updateSchLocPremCalc(volDiscPercIns, volDiscAmtIns, corpDiscPercIns, corpDiscAmtIns,
									splDiscPercIns, splDiscAmtIns, premiumAppliedIns, minPremiumYnIns, bean.getProductId(),
									bean.getQuoteNo(), bean.getDropDownScheme(), bean.getDropDownLocation());
							
							
						}else{
							errors += "Already Minimum Premium Applied. So Discount will not be Allowed";
						}
					}else{
						errors += "Already Minimum Premium Applied. So Discount will not be Allowed";
					}
					
					bean.setPremCalcErrorSchLoc(errors);
				}
			}
		}catch(Exception e){
			LogManager.info("Exception @ HomeService.calcDiscountPremium()"
					+ " QuoteNo: "+bean.getQuoteNo()+" ApplicationNo: "+bean.getApplicationNo()+" ProductId: "+bean.getProductId()+""
					+ " SchemeId: "+bean.getDropDownScheme()+" Location: "+bean.getDropDownLocation()+" Error: "+e);
			e.printStackTrace();
		}
	}

	public void getQuoteInfoDtl(HomeBean bean) {
		dao.getQuoteInfoDtl(bean);
	}
}