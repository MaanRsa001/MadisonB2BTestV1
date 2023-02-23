package com.maan.Travel.Services;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import nl.captcha.Captcha;

import org.apache.commons.lang3.StringUtils;

import proj.date.DateFunction;

import com.maan.Health.Services.DocUploadService;
import com.maan.Home.Controller.HomeBean;
import com.maan.Travel.DAO.TravelDAO;
import com.maan.Travel.controller.CoverageBean;
import com.maan.Travel.controller.TravelBean;
import com.maan.common.LogManager;
import com.maan.common.Validation;
import com.maan.common.otp.OTPGenerator;
import com.maan.customer.dao.CustomerDAO;
import com.maan.customer.service.CustomerService;
import com.maan.payment.PaymentService;
import com.maan.services.util.ValidationFormat;

public class TravelService {
	TravelDAO travelDAO=new TravelDAO();
	public String getPremium(TravelBean bean,String type)
	{
		return travelDAO.getCalculatePremium(bean,type);
	}
	public String getUpdateTravellersInfo(TravelBean bean) {
		return travelDAO.getUpdateTravellersInfo(bean);
	}
	public void getSecondPageDts(TravelBean bean) {
		// TODO Auto-generated method stub
		travelDAO.getSecondPageDts(bean);
	}
	public List<Object> getDetailsView(TravelBean bean) {
		return travelDAO.getDetailsView(bean);
	}
	public List<Object> getPolicyView(TravelBean bean) {
		return travelDAO.getPolicyView(bean);
	}
	public int getEffectiveDate(TravelBean bean) {
		return travelDAO.getEffectiveDate(bean);
	}
	public Map getCoveragesName(String schemeCover, String travelCover,String branchCode,String productCode) {
		// TODO Auto-generated method stub
		return travelDAO.getCoveragesName(schemeCover,travelCover,branchCode,productCode);
	}
	public List getTravelCoverages(final String schemeId,final String optionId,final String branchCode,String productCode) {
		// TODO Auto-generated method stub
		return travelDAO.getTravelCoverages(schemeId,optionId,branchCode,productCode);
	}
	public String updReferralStatus(String actionType, String referralRemarks, String quoteNo, String referralYN) {
		return travelDAO.updReferralStatus(actionType, referralRemarks, quoteNo, referralYN);
	}
	public String getGeratePolicy(TravelBean bean) {
		return travelDAO.getGeratePolicy(bean);
	}
	public List<Object> getPolicyInformation(String quoteNo) {
		// TODO Auto-generated method stub
		return travelDAO.getPolicyInformation(quoteNo);
	}
	public void getBackShowQuote(TravelBean bean) {
		// TODO Auto-generated method stub
		travelDAO.getBackShowQuote(bean);
	}
	public List<Object> getCoverInfo(String productId,String schemecover,String travelcover,String branchCode){
		 return travelDAO.getCoverInfo(productId,schemecover,travelcover,branchCode);
	 }
	public String getAdminReferralUpdation(TravelBean bean) {
		// TODO Auto-generated method stub
		return travelDAO.getAdminReferralUpdation(bean);
	}
	public String getCancelReissue(TravelBean bean){
		 return travelDAO.getCancelReissue(bean);
	 }
	public String getDeleteTraveller(TravelBean bean) {
		// TODO Auto-generated method stub
		return travelDAO.getDeleteTraveller(bean);
	}
	public String getConstanctDetials(String detialId,String branchCode) {
		// TODO Auto-generated method stub
		return travelDAO.getConstanctDetials(detialId,branchCode);
	}
	public void updateCorrections(TravelBean bean) {
		// TODO Auto-generated method stub
		travelDAO.updateCorrections(bean);
	}
	public LinkedList<String> getValidate(TravelBean bean,String type)
	{
		LinkedList<String> list=new LinkedList<String>();
		ValidationFormat val = new ValidationFormat();
		//Validation validation=new Validation();
		try{
			if("customerInfo".equalsIgnoreCase(type)){
				list=new CustomerService().getMotorCustomerValidate(bean.getIssuer(),bean.getBrokerCode(),bean.getExecutive(),bean.getTitle(),bean.getCustomerName(),bean.getMobileNo(),bean.getEmail(),bean.getCustomerType(),bean.getCompanyRegNo());
			}else if("customerDetailQuote".equalsIgnoreCase(type)){
				list=new CustomerService().getMotorCustomerValidate(/*bean.getIssuer()*/"",bean.getBrokerCode(),bean.getExecutive(),bean.getTitle(),bean.getCustomerName(),bean.getCity(),bean.getPoBox(),bean.getMobileNo(),bean.getEmail(),bean.getCustLastName(),bean.getCustnrc1(),bean.getCustnrc2(),bean.getCustnrc3(),bean.getCustPassportNo(),bean.getCustdob(),bean.getCustAlterMobileNo(),bean.getCustLandLineNo(),bean.getCustomerType(),bean.getCompanyRegNo(),bean.getAddress1(),bean.getGender(),bean.getOccupation());
			}
			else if("getQuote".equalsIgnoreCase(type)||"getPremium".equalsIgnoreCase(type)||"getSave".equalsIgnoreCase(type) || "viewSave".equalsIgnoreCase(type) || "getScheme".equalsIgnoreCase(type)){
	          	List tages = new ArrayList<Integer>();
	          	boolean oldAgeFlag = false;
		        boolean ageGrSixtyFive = false;
		        //int childCount = 0;
			   	int no_of_father = 0;
		       	int no_of_mother = 0;
		        int selfcount = 0;
		        int housemaidcount = 0;
		        String seflGender = "";
		        String spouseGender = "";
		        //int spouse = 0;
		        String emptyRows="";
		        boolean self=false,spouse=false,child=false;
		        int ageCheck =Integer.parseInt(getConstanctDetials("199",bean.getBranchCode()));
	          	for(int i=0;i<bean.getTravelList().size();i++)
	          	{
	          		if(StringUtils.isBlank(bean.getTravelNames().get(i)) && StringUtils.isBlank(bean.getTravelLastNames().get(i)) && StringUtils.isBlank(bean.getDobs().get(i)) && StringUtils.isBlank(bean.getGenders().get(i)) && StringUtils.isBlank(bean.getRelations().get(i)) && StringUtils.isBlank(bean.getNationalitys().get(i)) && StringUtils.isBlank(bean.getPassportNo().get(i)) && StringUtils.isBlank(bean.getPassportExpDate().get(i)) &&!"getSave".equalsIgnoreCase(type) ){
	          			list.add("error.travel.emptyRowFill#"+(i+1)+"#");
	          		}
	              	else if(!"getSave".equalsIgnoreCase(type)){
	              		//First NAME VALIDATION
	              		if(StringUtils.isBlank(bean.getTravelNames().get(i)))
	              			list.add("error.travel.Name#"+(i+1)+"#");
	              		else if(StringUtils.isWhitespace(bean.getTravelNames().get(i)))
	              			list.add("error.travel.Name.valid#"+(i+1)+"#");
	              		//	Last Name Validation
		                if(StringUtils.isBlank(bean.getTravelLastNames().get(i)))
		                	list.add("error.travel.last.name#"+(i+1)+"#");
		                else if(StringUtils.isWhitespace(bean.getTravelLastNames().get(i)))
		                	list.add("error.travel.last.name.valid#"+(i+1)+"#");
		                //Date of Birth
	              		if(StringUtils.isBlank(bean.getDobs().get(i)))
	              			list.add("error.travel.dob#"+(i+1)+"#");
	              		else if(!val.isDateValidationFormat(bean.getDobs().get(i)))
	              			list.add("error.travel.dob.format#"+(i+1)+"#");
	              		else{
				        	int age=-1;
	              			if(!val.sysDateValidation(bean.getDobs().get(i)))
							{
				        		list.add("error.travel.dob.validDate#"+(i+1)+"#");
							}else
				        	{
				        		age=new com.maan.common.dao.CommonDAO().getCalculatedAge(bean.getDobs().get(i));
				        		if(age>100)
				        		{
				        			list.add("error.travel.age.valid#"+(i+1)+"#");
				        		}
				        		if(age>ageCheck)
				        			ageGrSixtyFive = true;
				        		if(age>Integer.parseInt(getConstanctDetials("200",bean.getBranchCode())))//&&  (StringUtils.isBlank(oldage) ||"N".equalsIgnoreCase(oldage)))
				        			oldAgeFlag = true;
				        		if(i>tages.size()&&!"getSave".equalsIgnoreCase(type)&&!list.contains("error.travel.emptyRowFill#"+emptyRows+"#")){
				        			list.add("error.travel.emptyRowFill#"+emptyRows+"#");
				        		}
				        		tages.add(age);
				        	}
		                
	              		}
	              		//Gender Validation
	              		if(StringUtils.isBlank(bean.getGenders().get(i)))
	              			 list.add("error.travel.gender#"+(i+1)+"#");
	              		//RELATION VALIDATION
	              		if(StringUtils.isBlank(bean.getRelations().get(i)))
						   list.add("error.travel.relation#"+(i+1)+"#");
	              		else{
		                	if("1".equals(bean.getRelations().get(i)))
							{
			                   selfcount++;
			                   self=true;
	               			   seflGender = StringUtils.isBlank(bean.getGenders().get(i))?"":bean.getGenders().get(i);
		                    }else if("2".equals(bean.getRelations().get(i)))
							{
		                    	//spouse++;
		                    	spouse=true;
		                	 	spouseGender+=(StringUtils.isBlank(bean.getGenders().get(i))?"":bean.getGenders().get(i));
							}else if("3".equals(bean.getRelations().get(i)))
			                {
							/*	if(age<=18){
			                	  childCount++;
								}*/
								child=true;
			                }
			                else if("4".equals(bean.getRelations().get(i)))
							{
			                	no_of_father++;
			                	if("F".equalsIgnoreCase(bean.getGenders().get(i)))
			                		list.add("error.travel.genderRelationValid#"+(i+1)+"#");
							}
			                else if("5".equals(bean.getRelations().get(i)))
							{
			                	no_of_mother++;
			                	if("M".equalsIgnoreCase(bean.getGenders().get(i)))
			                		list.add("error.travel.genderRelationValid#"+(i+1)+"#");
							}
			                else if("7".equals(bean.getRelations().get(i)))
			                {
			                	housemaidcount++;
			                }
		                }
	              		//NATIONALITY VALIDATION
	              		if(StringUtils.isBlank(bean.getNationalitys().get(i)))
	              			list.add("error.travel.nationality#"+(i+1)+"#");
						//	Passport Number Validation
	              		if (StringUtils.isBlank(bean.getPassportNo().get(i))) 
	              			list.add("error.travel.passport.number#"+(i+1)+"#");
	              		else if(StringUtils.isWhitespace(bean.getPassportNo().get(i)))
	              			list.add("error.travel.passport.number.valid#"+(i+1)+"#");
			            //Passport expiry date check
			            if(StringUtils.isBlank(bean.getPassportExpDate().get(i)))
			            	list.add("error.travel.passport.expiry.date#"+(i+1)+"#");
			            else if(!val.isDateValidationFormat(bean.getPassportExpDate().get(i)))
			            	list.add("error.travel.passport.expiry.date.format#"+(i+1)+"#");
			            else{
			            	if(StringUtils.isNotBlank(bean.getPassportExpDate().get(i)) && StringUtils.isNotBlank(bean.getExpiryDt())){
			            		//Get Month
			            		int month = travelDAO.getPasswordExpiryValidationMonth();
			            		if(!checkPassportExpDate(bean.getPassportExpDate().get(i).toString(),bean.getExpiryDt(),month)){
			            			list.add("Passport Expiry Date Must be Greater Than "+month+" Months From The Policy Expiry Date in The Row No : "+(i+1));
			            		}
			            	}
			            }
	              	}
	          	}
	          	if(!"getSave".equalsIgnoreCase(type)){
	            	if(seflGender.length()>0&&spouseGender.length()>0)
					{
	            		if("M".equalsIgnoreCase(seflGender)&&spouseGender.indexOf("M")!=-1){
	            			list.add("error.travel.relation.selfSpouseValid");
	            		}else if("F".equalsIgnoreCase(seflGender)&&StringUtils.countMatches(spouseGender,"M")>1){
	            			list.add("error.travel.relation.selfSpouseValid");
	            		}
					}
		            if(selfcount==0&&housemaidcount==0){
		            	list.add("error.travel.relation.selfOrHouseMaidatLeastOne");
					}else
					{
						if(selfcount >1){
							list.add("error.travel.relation.selfValid");
			            }else if(housemaidcount >1){
			            	list.add("error.travel.relation.houseMaidValid");
			            }else if((housemaidcount==1&&selfcount!=0)||(housemaidcount!=0&&selfcount==1)){
			            	list.add("error.travel.relation.selfOrHouseMaidValid");
			            }else if(housemaidcount==1&&tages.size()>1){
			            	list.add("error.travel.relation.onlyOneHouseMaid");
			            }
					}
		            if(no_of_mother > 1){
		            	list.add("error.travel.relation.motherValid");
		            }
		            if(no_of_father > 1){
		            	list.add("error.travel.relation.fatherValid");
		            }
		            bean.setAges(tages);
		            
		            if(list==null ||list.size()<=0)
					{
			            int selfage   = 0;
			            int spouseage = 0;
			            for(int i=0;i<bean.getAges().size();i++){
		                if("1".equals(bean.getRelations().get(i))){
		                	 selfage = bean.getAges().get(i);
		                	 if(selfage<=18)
		                		 list.add("error.travel.selfAgeGr18Yers");
		                }
		                if("2".equals(bean.getRelations().get(i)))
		                	 spouseage =bean.getAges().get(i);
		                
		                if("3".equals(bean.getRelations().get(i)))
		                {
			                 if((selfage != 0 &&bean.getAges().get(i) >= selfage) || (spouseage !=0 && bean.getAges().get(i) >= spouseage)){
		                       if(!list.contains("error.travel.kidsAgeGrParentsAge"))
		                    	   list.add("error.travel.kidsAgeGrParentsAge");
			                 }
		                }else if("4".equals(bean.getRelations().get(i)))
						{
							
			                    if(selfage != 0 && selfage >= bean.getAges().get(i))
								{
			                        if(!list.contains("error.travel.selfAgeGrFatherAge")){
			                        	list.add("error.travel.selfAgeGrFatherAge");
			                        }
								}
			                    
								if(spouseage !=0 && spouseage >=  bean.getAges().get(i))
								{
			                        if(!list.contains("error.travel.spouseAgeGrFatherAge")){
			                        	list.add("error.travel.spouseAgeGrFatherAge");
			                        }
								}
						}
						if("5".equals(bean.getRelations().get(i)))
						{
			                    if(selfage != 0 && selfage >= bean.getAges().get(i))
								{
			                        if(!list.contains("error.travel.selfAgeGrMotherAge")){
			                        	list.add("error.travel.selfAgeGrMotherAge");
			                        }
								}
								if(spouseage !=0 && spouseage >= bean.getAges().get(i))
								{
			                        if(!list.contains("error.travel.spouseAgeGrMotherAge")){
			                        		list.add("error.travel.spouseAgeGrMotherAge");
			                        }
								}
						}
						else if("7".equals(bean.getRelations().get(i)))
			            {
							if(bean.getAges().get(i)<=18)
		                		 list.add("error.travel.housemaidAgeGr18Yers");
			            }
						if(StringUtils.isNotEmpty(bean.getSchemeCover())&&!"1".equalsIgnoreCase(bean.getSchemeCover())&&bean.getAges().get(i)>80&&!list.contains("error.travel.schemeCoverApplicable")){
							list.add("error.travel.schemeCoverApplicable");
						}
		            }
					}if(!"viewSave".equalsIgnoreCase(type)){
			          //Schme Cover
						/*if(StringUtils.isEmpty(bean.getSchemeCover()))
						{
							list.add("error.travel.schemeCover");
						}elseif("4".equalsIgnoreCase(bean.getSchemeCover())&&!(self&&spouse&&child))
						{
							list.add("error.travel.familCoverNotApplicable");
						} */
						//Travel Cover
						if(StringUtils.isEmpty(bean.getTravelCover()))
						{
							list.add("error.travel.travelCover");
						}
						if(StringUtils.isNotEmpty(bean.getSchemeCover())&&StringUtils.isNotEmpty(bean.getTravelCover())){
							Map map=getCoveragesName(bean.getSchemeCover(), bean.getTravelCover(),bean.getBranchCode(),bean.getProductId());
							String schemeCoverage=map.get("SCHEME_NAME")==null?"":map.get("SCHEME_NAME").toString();
							String travelCoverage=map.get("OPTION_NAME")==null?"":map.get("OPTION_NAME").toString();
							if(!"None".equalsIgnoreCase(travelCoverage))
							{
								travelCoverage=travelCoverage.trim();
								travelCoverage=travelCoverage.replaceAll(" ", "_");
								bean.setScheme_Covers(schemeCoverage);
								bean.setTravel_Covers(travelCoverage);
							}
							else
							{
								bean.setScheme_Covers(schemeCoverage);
								bean.setTravel_Covers("None");
							}
							//Coverages
							  List coverages =getTravelCoverages(bean.getSchemeCover(),bean.getTravelCover(), bean.getBranchCode(),bean.getProductId());
				              if(coverages != null &&coverages.size()>0){
			                  	for(int i=0;i<coverages.size();i++){
			                  		Map map1=(Map)coverages.get(i);
			                  		if(ageGrSixtyFive&&"Y".equalsIgnoreCase(bean.getCoverages().get(i))){//&&"14".equalsIgnoreCase(map1.get("COVER_ID").toString())
			                  			list.add("error.travel.winterSportsNotApplicable#"+ageCheck);
			                  		}
			                  	}
							  }
						}
						if(!"32".equalsIgnoreCase(bean.getProductId()))
				        {
					        //EXPIRY DATE VALIDATION (INCLUDES COVER PERIOD AND EFFECTIVE DATE)
							boolean effDate=true;
					        if(StringUtils.isEmpty(bean.getCoverPeriod()))
							{
					        	list.add("error.travel.coverPeriod.valid");
					        	effDate=false;
							}
					        else if(Integer.valueOf(bean.getCoverPeriod()) > 366){
					        	list.add("error.travel.coverPeriod.validLimit");
					        	effDate=false;
					        }
					        if(StringUtils.isEmpty(bean.getInceptionDt())){
					        	list.add("error.travel.effectiveDt");
					        	effDate=false;
					        }
					        else if(!val.isDateValidationFormat(bean.getInceptionDt())){
					        	list.add("error.travel.effectiveDt.valid");
					        	effDate=false;
					        }
					       /* else if(val.sysDateValidation(bean.getInceptionDt()))
					        {
					        	list.add("error.travel.expiryDt.less.currentDt");
					        	effDate=false;
					        }*/
					        if(StringUtils.isEmpty(bean.getExpiryDt())){
					        	list.add("error.travel.expiryDt.valid");
					        	effDate=false;
					        }
					        if(effDate)
					        {
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
											list.add("error.travel.expiryDt.less.currentDt");
										}
										else if(diff > backDates)	{
											list.add("error.travel.backDt.valid#"+backDates);
										}
					              }
					             }
				        }
				        if(oldAgeFlag){
				        	if("getPremium".equalsIgnoreCase(type)&&!"admin".equals(bean.getUser()))
				        		list.add("error.travel.oldAgeReferal");
				        	bean.setReferralMsg((bean.getReferralMsg()==null?"":bean.getReferralMsg())+" Old Age Referral.");
				        }
				        if((bean.getAges().size())>10)
			        	{
				        	if("getPremium".equalsIgnoreCase(type)&&!"admin".equals(bean.getUser()))
				        		list.add("error.travel.oldAgeReferal");
			        		bean.setReferralMsg((bean.getReferralMsg()==null?"":bean.getReferralMsg())+" More than 10 Person Travelling.");
			        	}
					}
					if(!list.isEmpty())
						if(!StringUtils.isBlank(bean.getInceptionDt()))
							bean.setInceptionDt(bean.getInceptionDt());
	          	}
			}else if("getPolicy".equalsIgnoreCase(type)){
				if(!"admin".equalsIgnoreCase(bean.getUser())&&"Y".equalsIgnoreCase(bean.getReferralYN())&&StringUtils.isBlank(bean.getReferralComments()))
				{
					list.add("error.travel.comments");
				}
				if("admin".equalsIgnoreCase(bean.getUser())&&bean.getTotalPremium()<0){
					list.add("error.travel.totalPremiumPayable");
				}
			}else if("paymentInfo".equalsIgnoreCase(type)){
				list = new PaymentService().validatePayment(bean.getModeOfPayment(),bean.getChequeNo(),bean.getChequeDate(),bean.getChequeAmount(),Double.toString(bean.getTotalPremium()),bean.getBankName(),bean.getMicrCode(),bean.getCashDepositBank(),bean.getCashAmount(),bean.getCashChellanNo(),bean.getCashInstrumentDate(),bean.getInstallmentYN(),bean.getInsIntialAmount(),bean.getMtnMobileNo());
			}
			else if("captcha".equalsIgnoreCase(type)){
				String error = validateCaptcha(bean.getCaptchavalue(),bean.getCaptcha());
				if(!"".equals(error))
					list.add(error);
			}else if("otpVerfiy".equalsIgnoreCase(type)) {
				 list=new OTPGenerator().getValidate(bean.getOtp(),bean.getOtpId(),bean.getMailOtp());
			}
		}
		
		catch(Exception e)
		{
			LogManager.debug(e);
		}
		return list;
	}
	public List<CoverageBean> getOptCoverList(String applicationNo,String travelCovers) {
		List<CoverageBean> list=travelDAO.getCoveragesNames(applicationNo,travelCovers);
		if(list!=null && list.size()>0){
    		for(int i=0;i<list.size();i++){
    			CoverageBean covBean = list.get(i);
    			DocUploadService clmService = new DocUploadService();
    			covBean.setCoveragesList(travelDAO.getCoverages(applicationNo, covBean.getSchemeId(),travelCovers));
    		}
    	}
		return list;
	}
	public String getUpdateOptionCovers(TravelBean bean) {
		return travelDAO.getUpdateOptionCovers(bean);
	}
	public void getInsertOrUpdateTravelCoverDtls(TravelBean bean){
		travelDAO.getInsertOrUpdateTravelCoverDtls(bean);
	}	
	/*public String insertOrUpdateCustomerInfo(TravelBean bean,String issuer)
	{
		return new CustomerService().getUpdateCustomerDtl(bean.getCustomerId(), bean.getLoginId(), bean.getBranchCode(), bean.getProductId(), bean.getTitle(),bean.getCustomerName(), bean.getMobileNo(), bean.getEmail(), bean.getAddress1(), bean.getAddress2(), bean.getPoBox(), bean.getCity(), "", "", "",bean.getIssuer(),bean.getBrokerCode(),bean.getExecutive());
		//travelDAO.insertOrUpdateCustomerInfo(bean,issuer);
	}*/
	
	public boolean checkPassportExpDate(String passportExpDate,String effectiveDt,int month) {
		boolean result=false;
		try{
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			
			Calendar passExpDate = Calendar.getInstance();
			passExpDate.setTime(sdf.parse(passportExpDate));
			
			Calendar effectiveDate = Calendar.getInstance();
			effectiveDate.setTime(sdf.parse(effectiveDt));
			effectiveDate.add(effectiveDate.MONTH, month);
			if(passExpDate.after(effectiveDate)){
				result=true;
			}
			
			
		}catch(Exception e){
			LogManager.debug("Exception Occured @ checkPassportExpDate"+e);
		}
		return result;
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
	public void getB2CCustomerDetails(TravelBean bean, String loginId) {
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
				/*bean.setCustCoreCode(map.get(i).get("MISSIPPI_CUSTOMER_CODE")==null?"":map.get(i).get("MISSIPPI_CUSTOMER_CODE").toString());
				bean.setCoreAppCode(map.get(i).get("MISSIPPI_CUSTOMER_CODE")==null?"":map.get(i).get("MISSIPPI_CUSTOMER_CODE").toString());
				bean.setClientCustomerId(map.get(i).get("CLIENT_CUSTOMER_ID")==null?"":map.get(i).get("CLIENT_CUSTOMER_ID").toString());
				bean.setCustArNo(map.get(i).get("CUST_AR_NO")==null?"":map.get(i).get("CUST_AR_NO").toString());
				bean.setCustomerType(map.get(i).get("CUSTOMER_TYPE")==null?"":map.get(i).get("CUSTOMER_TYPE").toString());
				bean.setCompanyRegNo(map.get(i).get("COMPANY_REG_NO")==null?"":map.get(i).get("COMPANY_REG_NO").toString());*/
			}
		}
	}
	public String getEmailCount(TravelBean bean) {
		return travelDAO.getEmailCount(bean);
	}
	public void setCustomerDetails(TravelBean bean) {
		travelDAO.setCustomerDetails(bean);
	}
}
