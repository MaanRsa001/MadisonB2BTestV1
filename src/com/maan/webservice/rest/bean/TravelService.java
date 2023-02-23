package com.maan.webservice.rest.bean;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.maan.common.LogManager;
import com.maan.services.util.ValidationFormat;


public class TravelService {
	TravelDAO travelDAO=new TravelDAO();
	public String getPremium(Travel bean,String type)
	{
		return travelDAO.getCalculatePremium(bean,type);
	}
	public String getUpdateTravellersInfo(Travel bean) {
		return travelDAO.getUpdateTravellersInfo(bean);
	}
	public void getSecondPageDts(Travel travelBean) {
		// TODO Auto-generated method stub
		travelDAO.getSecondPageDts(travelBean);
	}
	public List<Map<String, Object>> getDetailsView(Travel travelBean) {
		return travelDAO.getDetailsView(travelBean);
	}
	public List<Map<String, Object>> getPolicyView(Travel travelBean) {
		return travelDAO.getPolicyView(travelBean);
	}
	public int getEffectiveDate(Travel travelBean) {
		return travelDAO.getEffectiveDate(travelBean);
	}
	public Map getCoveragesName(String schemeCover, String travelCover,String branchCode,String productCode) {
		// TODO Auto-generated method stub
		return travelDAO.getCoveragesName(schemeCover,travelCover,branchCode,productCode);
	}
	public List getTravelCoverages(final String schemeId,final String optionId,final String branchCode,String productCode) {
		// TODO Auto-generated method stub
		return travelDAO.getTravelCoverages(schemeId,optionId,branchCode,productCode);
	}
	public String getGeratePolicy(Travel travelBean) {
		// TODO Auto-generated method stub
		return travelDAO.getGeratePolicy(travelBean);
	}
	public List<Map<String, Object>> getPolicyInformation(String quoteNo) {
		// TODO Auto-generated method stub
		return travelDAO.getPolicyInformation(quoteNo);
	}
	public void getBackShowQuote(Travel travelBean) {
		// TODO Auto-generated method stub
		travelDAO.getBackShowQuote(travelBean);
	}
	public List<Map<String, Object>> getCoverInfo(String productId,String schemecover,String travelcover,String branchCode){
		 return travelDAO.getCoverInfo(productId,schemecover,travelcover,branchCode);
	 }
	public String getAdminReferralUpdation(Travel travelBean) {
		// TODO Auto-generated method stub
		return travelDAO.getAdminReferralUpdation(travelBean);
	}
	public String getCancelReissue(Travel travelBean){
		 return travelDAO.getCancelReissue(travelBean);
	 }
	public String getDeleteTraveller(Travel travelBean) {
		// TODO Auto-generated method stub
		return travelDAO.getDeleteTraveller(travelBean);
	}
	public String getConstanctDetials(String detialId,String branchCode) {
		// TODO Auto-generated method stub
		return travelDAO.getConstanctDetials(detialId,branchCode);
	}
	public void updateCorrections(Travel travelBean) {
		// TODO Auto-generated method stub
		travelDAO.updateCorrections(travelBean);
	}
	public LinkedList<String> getValidate(Travel travelBean,String type)
	{
		LinkedList<String> list=new LinkedList<String>();
		ValidationFormat val = new ValidationFormat();
		//Validation validation=new Validation();
		try{
		 if("getQuote".equalsIgnoreCase(type)||"getPremium".equalsIgnoreCase(type)||"getSave".equalsIgnoreCase(type) || "viewSave".equalsIgnoreCase(type)){
			/*if(StringUtils.isEmpty(travelBean.getCustomerId()) && !"viewSave".equalsIgnoreCase(travelBean.getSelection()))
			{
				list.add("error.travel.curtomer.valid");
			}else
			{*/
			 
			
              	//ValidationFormat val = new ValidationFormat();
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
		        int ageCheck =Integer.parseInt(getConstanctDetials("199",travelBean.getBranchCode()));
              	for(int i=0;i<travelBean.getTravelList().size();i++)
              	{
              		//NAME VALIDATION
	                if(StringUtils.isNotBlank(travelBean.getTravelNames().get(i)))
	                {
	                	if(!val.validateStringWithSpace(travelBean.getTravelNames().get(i))){
	                		list.add("error.travel.Name.valid#"+(i+1)+"#");
	                	}
	                }
	                else if((StringUtils.isNotBlank(travelBean.getDobs().get(i))||!"-1".equalsIgnoreCase(travelBean.getGenders().get(i))||!"-1".equalsIgnoreCase(travelBean.getRelations().get(i))||!"-1".equalsIgnoreCase(travelBean.getNationalitys().get(i)))&&!"getSave".equalsIgnoreCase(type)){
	                	list.add("error.travel.Name#"+(i+1)+"#");
	                }else
	                {
	                	if(list.contains("error.travel.emptyRowFill#"+emptyRows+"#"))
	                		emptyRows="";
	                	emptyRows+=(i+1)+",";
	                }
	                
	                //DATE OF BIRTH VALIDATION
	                int age=-1;
	                if(StringUtils.isNotBlank(travelBean.getDobs().get(i)))
	                {
			        	if(!val.IsDateValidationFormat(travelBean.getDobs().get(i))){
			        		list.add("error.travel.dob.valid#"+(i+1)+"#");
			        	}else if(!val.sysDateValidation(travelBean.getDobs().get(i)))
						{
			        		list.add("error.travel.dob.validDate#"+(i+1)+"#");
						}else
			        	{
			        		age=new com.maan.common.dao.CommonDAO().getCalculatedAge(travelBean.getDobs().get(i));
			        		if(age>100)
			        		{
			        			list.add("error.travel.age.valid#"+(i+1)+"#");
			        		}
			        		if(age>ageCheck)
			        			ageGrSixtyFive = true;
			        		if(age>Integer.parseInt(getConstanctDetials("200",travelBean.getBranchCode())))//&&  (StringUtils.isBlank(oldage) ||"N".equalsIgnoreCase(oldage)))
			        			oldAgeFlag = true;
			        		if(i>tages.size()&&!"getSave".equalsIgnoreCase(type)&&!list.contains("error.travel.emptyRowFill#"+emptyRows+"#")){
			        			list.add("error.travel.emptyRowFill#"+emptyRows+"#");
			        		}
			        		tages.add(age);
			        	}
	                }
		        	else if((StringUtils.isNotBlank(travelBean.getTravelNames().get(i))||!"-1".equalsIgnoreCase(travelBean.getGenders().get(i))||!"-1".equalsIgnoreCase(travelBean.getRelations().get(i))||!"-1".equalsIgnoreCase(travelBean.getNationalitys().get(i)))&&!"getSave".equalsIgnoreCase(type))
		        		list.add("error.travel.dob#"+(i+1)+"#");
	                
	               //GENDER VALIDATION
				   if(("-1".equalsIgnoreCase(travelBean.getGenders().get(i))&&(StringUtils.isNotBlank(travelBean.getTravelNames().get(i))||!"-1".equalsIgnoreCase(travelBean.getRelations().get(i))||!"-1".equalsIgnoreCase(travelBean.getNationalitys().get(i))))&&!"getSave".equalsIgnoreCase(type))
				   {
					   list.add("error.travel.gender#"+(i+1)+"#");
				   }
				   //RELATION VALIDATION
				   if(("-1".equalsIgnoreCase(travelBean.getRelations().get(i))&&(StringUtils.isNotBlank(travelBean.getTravelNames().get(i))||StringUtils.isNotBlank(travelBean.getDobs().get(i))||!"-1".equalsIgnoreCase(travelBean.getGenders().get(i))||!"-1".equalsIgnoreCase(travelBean.getNationalitys().get(i))))&&!"getSave".equalsIgnoreCase(type)){
					   list.add("error.travel.relation#"+(i+1)+"#");
	                }else if(!"getSave".equalsIgnoreCase(type))
	                {
	                	if("1".equals(travelBean.getRelations().get(i)))
						{
		                   selfcount++;
		                   self=true;
               			   seflGender = "-1".equalsIgnoreCase(travelBean.getGenders().get(i))?"":travelBean.getGenders().get(i);
	                    }else if("2".equals(travelBean.getRelations().get(i)))
						{
	                    	//spouse++;
	                    	spouse=true;
	                	 	spouseGender+=("-1".equalsIgnoreCase(travelBean.getGenders().get(i))?"":travelBean.getGenders().get(i));
						}else if("3".equals(travelBean.getRelations().get(i)))
		                {
						/*	if(age<=18){
		                	  childCount++;
							}*/
							child=true;
		                }
		                else if("4".equals(travelBean.getRelations().get(i)))
						{
		                	no_of_father++;
		                	if("F".equalsIgnoreCase(travelBean.getGenders().get(i)))
		                		list.add("error.travel.genderRelationValid#"+(i+1)+"#");
						}
		                else if("5".equals(travelBean.getRelations().get(i)))
						{
		                	no_of_mother++;
		                	if("M".equalsIgnoreCase(travelBean.getGenders().get(i)))
		                		list.add("error.travel.genderRelationValid#"+(i+1)+"#");
						}
		                else if("7".equals(travelBean.getRelations().get(i)))
		                {
		                	housemaidcount++;
		                }
	                }
				   //NATIONALITY VALIDATION
				   if(("-1".equalsIgnoreCase(travelBean.getNationalitys().get(i))&&(StringUtils.isNotBlank(travelBean.getTravelNames().get(i))||StringUtils.isNotBlank(travelBean.getDobs().get(i))||!"-1".equalsIgnoreCase(travelBean.getGenders().get(i))||!"-1".equalsIgnoreCase(travelBean.getRelations().get(i))))&&!"getSave".equalsIgnoreCase(type)){
					   list.add("error.travel.nationality#"+(i+1)+"#");
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
		            travelBean.setAges(tages);
		            
		            if(list==null ||list.size()<=0)
					{
			            int selfage   = 0;
			            int spouseage = 0;
			            for(int i=0;i<travelBean.getAges().size();i++){
		                if("1".equals(travelBean.getRelations().get(i))){
		                	 selfage = travelBean.getAges().get(i);
		                	 if(selfage<=18)
		                		 list.add("error.travel.selfAgeGr18Yers");
		                }
		                if("2".equals(travelBean.getRelations().get(i)))
		                	 spouseage =travelBean.getAges().get(i);
		                
		                if("3".equals(travelBean.getRelations().get(i)))
		                {
			                 if((selfage != 0 &&travelBean.getAges().get(i) >= selfage) || (spouseage !=0 && travelBean.getAges().get(i) >= spouseage)){
		                       if(!list.contains("error.travel.kidsAgeGrParentsAge"))
		                    	   list.add("error.travel.kidsAgeGrParentsAge");
			                 }
		                }else if("4".equals(travelBean.getRelations().get(i)))
						{
							
			                    if(selfage != 0 && selfage >= travelBean.getAges().get(i))
								{
			                        if(!list.contains("error.travel.selfAgeGrFatherAge")){
			                        	list.add("error.travel.selfAgeGrFatherAge");
			                        }
								}
			                    
								if(spouseage !=0 && spouseage >=  travelBean.getAges().get(i))
								{
			                        if(!list.contains("error.travel.spouseAgeGrFatherAge")){
			                        	list.add("error.travel.spouseAgeGrFatherAge");
			                        }
								}
						}
						if("5".equals(travelBean.getRelations().get(i)))
						{
			                    if(selfage != 0 && selfage >= travelBean.getAges().get(i))
								{
			                        if(!list.contains("error.travel.selfAgeGrMotherAge")){
			                        	list.add("error.travel.selfAgeGrMotherAge");
			                        }
								}
								if(spouseage !=0 && spouseage >= travelBean.getAges().get(i))
								{
			                        if(!list.contains("error.travel.spouseAgeGrMotherAge")){
			                        		list.add("error.travel.spouseAgeGrMotherAge");
			                        }
								}
						}
						else if("7".equals(travelBean.getRelations().get(i)))
			            {
							if(travelBean.getAges().get(i)<=18)
		                		 list.add("error.travel.housemaidAgeGr18Yers");
			            }
						if(StringUtils.isNotEmpty(travelBean.getSchemeCover())&&!"1".equalsIgnoreCase(travelBean.getSchemeCover())&&travelBean.getAges().get(i)>80&&!list.contains("error.travel.schemeCoverApplicable")){
							list.add("error.travel.schemeCoverApplicable");
						}
		            }
					}if(!"viewSave".equalsIgnoreCase(type)){
			          //Schme Cover
						/*if(StringUtils.isEmpty(travelBean.getSchemeCover()))
						{
							list.add("error.travel.schemeCover");
						}elseif("4".equalsIgnoreCase(travelBean.getSchemeCover())&&!(self&&spouse&&child))
						{
							list.add("error.travel.familCoverNotApplicable");
						} */
						//Travel Cover
						if(StringUtils.isEmpty(travelBean.getTravelCover()))
						{
							list.add("error.travel.travelCover");
						}
						if(StringUtils.isNotEmpty(travelBean.getSchemeCover())&&StringUtils.isNotEmpty(travelBean.getTravelCover())){
							Map map=getCoveragesName(travelBean.getSchemeCover(), travelBean.getTravelCover(),travelBean.getBranchCode(),travelBean.getProductId());
							String schemeCoverage=map.get("SCHEME_NAME")==null?"":map.get("SCHEME_NAME").toString();
							String travelCoverage=map.get("OPTION_NAME")==null?"":map.get("OPTION_NAME").toString();
							if(!"None".equalsIgnoreCase(travelCoverage))
							{
								travelCoverage=travelCoverage.trim();
								travelCoverage=travelCoverage.replaceAll(" ", "_");
								travelBean.setScheme_Covers(schemeCoverage);
								travelBean.setTravel_Covers(travelCoverage);
							}
							else
							{
								travelBean.setScheme_Covers(schemeCoverage);
								travelBean.setTravel_Covers("None");
							}
							//Coverages
							  List coverages =getTravelCoverages(travelBean.getSchemeCover(),travelBean.getTravelCover(), travelBean.getBranchCode(),travelBean.getProductId());
				              if(coverages != null &&coverages.size()>0){
			                  	for(int i=0;i<coverages.size();i++){
			                  		Map map1=(Map)coverages.get(i);
			                  		if(ageGrSixtyFive&&"Y".equalsIgnoreCase(travelBean.getCoverages().get(i))){//&&"14".equalsIgnoreCase(map1.get("COVER_ID").toString())
			                  			list.add("error.travel.winterSportsNotApplicable#"+ageCheck);
			                  		}
			                  	}
							  }
						}
						if(!"32".equalsIgnoreCase(travelBean.getProductId()))
				        {
					        //EXPIRY DATE VALIDATION (INCLUDES COVER PERIOD AND EFFECTIVE DATE)
							boolean effDate=true;
					        if(StringUtils.isEmpty(travelBean.getCoverPeriod()))
							{
					        	list.add("error.travel.coverPeriod.valid");
					        	effDate=false;
							}
					        else if(Integer.valueOf(travelBean.getCoverPeriod()) > 366){
					        	list.add("error.travel.coverPeriod.validLimit");
					        	effDate=false;
					        }
					        if(StringUtils.isEmpty(travelBean.getInceptionDt())){
					        	list.add("error.travel.effectiveDt.valid");
					        	effDate=false;
					        }
					        /*else if(val.sysDateValidation(travelBean.getInceptionDt()))
					        {
					        	list.add("error.travel.expiryDt.less.currentDt");
					        	effDate=false;
					        }*/
					        if(StringUtils.isEmpty(travelBean.getExpiryDt())){
					        	list.add("error.travel.expiryDt.valid");
					        	effDate=false;
					        }
					        if(effDate)
					        {
					              final com.maan.proj.date.DateFunction datf = new com.maan.proj.date.DateFunction();
					              final Calendar cal1 = Calendar.getInstance();
					              String[] effDT=travelBean.getInceptionDt().split("/");
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
					            	  	backDate = new com.maan.common.dao.CommonDAO().getBackDatesAllowed((String)travelBean.getLoginId(),(String)travelBean.getUserType(),travelBean.getProductId(),travelBean.getBranchCode(),"");
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
				        	if("getPremium".equalsIgnoreCase(type)&&!"admin".equals(travelBean.getUser()))
				        		list.add("error.travel.oldAgeReferal");
				        	travelBean.setReferralMsg((travelBean.getReferralMsg()==null?"":travelBean.getReferralMsg())+" Old Age Referral.");
				        }
				        if((travelBean.getAges().size())>10)
			        	{
				        	if("getPremium".equalsIgnoreCase(type)&&!"admin".equals(travelBean.getUser()))
				        		list.add("error.travel.oldAgeReferal");
			        		travelBean.setReferralMsg((travelBean.getReferralMsg()==null?"":travelBean.getReferralMsg())+" More than 10 Person Travelling.");
			        	}
					}
					if(!list.isEmpty())
						if(!StringUtils.isBlank(travelBean.getInceptionDt()))
							travelBean.setInceptionDt(dateFormat(travelBean.getInceptionDt()));
			}
		//  }
		}else if("getPolicy".equalsIgnoreCase(type)){
			if(!"admin".equalsIgnoreCase(travelBean.getUser())&&"Y".equalsIgnoreCase(travelBean.getReferralYN())&&StringUtils.isBlank(travelBean.getReferralComments()))
			{
				list.add("error.travel.comments");
			}
			if("admin".equalsIgnoreCase(travelBean.getUser())&&travelBean.getTotalPremium()<0){
				list.add("error.travel.totalPremiumPayable");
			}
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
    			covBean.setCoveragesList(travelDAO.getCoverages(applicationNo, covBean.getSchemeId(),travelCovers));
    		}
    	}
		return list;
	}
	public String getUpdateOptionCovers(Travel travelBean) {
		return travelDAO.getUpdateOptionCovers(travelBean);
	}
	public void getInsertOrUpdateTravelCoverDtls(Travel bean){
		travelDAO.getInsertOrUpdateTravelCoverDtls(bean);
	}	
	public void insertOrUpdateCustomerInfo(Travel travelBean,String issuer)
	{
		travelDAO.insertOrUpdateCustomerInfo(travelBean,issuer);
	}
	private String dateFormat(String name){
		String arr[]=name.split("/");
		return arr[1]+"/"+arr[0]+"/"+arr[2];
	}
}
