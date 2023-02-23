package com.maan.quotation.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import com.maan.common.DBConstants;
import com.maan.common.LogManager;
import com.maan.common.LogUtil;
import com.maan.common.MyJdbcTemplate;
import com.maan.common.exception.BaseException;
import com.maan.quotation.PremiumAction;
import com.maan.webservice.dao.PolicyGenerationDAO;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.util.LocalizedTextUtil;
@SuppressWarnings("unchecked")
public class PremiumDAO extends MyJdbcTemplate {
	
	 String sql="";
	 Logger logger = LogUtil.getLogger(getClass());
	 Map<String, Object> session=ActionContext.getContext().getSession();
	 private String loginUserType=(String)session.get("usertype");
	 
	 public List<Object> getQuotationInformation(String applicationNo,String branchCode){
			List<Object> quoteInfo=null;			
			
			LogManager.push("Method to quotation information");
			LogManager.push("applicationNo"+applicationNo+","+branchCode);
			try{
				sql=getQuery(DBConstants.PREMIUM_QUOTATION_INFO);
				quoteInfo=this.mytemplate.queryForList(sql,new String[]{branchCode,branchCode,branchCode,applicationNo,branchCode});			
			   }
			catch (Exception e) {			
				LogManager.debug("EXCEPTION @ { " + e + " }");
			}		
			LogManager.info("Query===>" + sql);
			LogManager.info("getQuotationInformation() - Exit || Result: " + quoteInfo.size());
			LogManager.popRemove();		
			return quoteInfo;
		}
	 public List<Object> getQuotationInsuredValue(String applicationNo,String branchCode){
			List<Object> quoteInfo=null;
			Object args[]=new Object[2];
			args[0]=applicationNo;
			args[1]=branchCode;
			LogManager.push("Method to quotation information");
			try{
				sql=getQuery(DBConstants.PREMIUM_GOODS_INFO);
				quoteInfo=this.mytemplate.queryForList(sql,args);			
			   }
			catch (Exception e) {			
				LogManager.debug("EXCEPTION @ { " + e + " }");
			}		
			LogManager.info("Query===>" + sql);
			LogManager.info("getQuotationInsuredValue() - Exit || Result: " + quoteInfo.size());
			LogManager.popRemove();		
			return quoteInfo;
		}
	 
	 public int updatePolicyInformation(PremiumAction premiumAction) throws  BaseException{
		 LogManager.info("updatePolicyInformation() - Enter");
		 int result=0;
		 Object args[]=new Object[15];
		 Object arg[]=new Object[3];
		 try{			
		
							
				 arg[0]=StringUtils.defaultIfEmpty(premiumAction.getReferralUpdate(), "N");
				 arg[1]=StringUtils.defaultIfEmpty(premiumAction.getReferralComment(), "");
				 arg[2]=premiumAction.getApplicationNo();					 
				 sql=getQuery(DBConstants.PREMIUM_UPDATE_REFERRAL);				 
				 result=this.mytemplate.update(sql,arg);
				 result=this.mytemplate.update(getQuery("UPD_WS_ADMIN_REFERRAL"),arg);
				 
			if(!"Y".equalsIgnoreCase(premiumAction.getReferralUpdate())){
				 if(!"RA".equalsIgnoreCase(premiumAction.getQuoteStatus()) && !premiumAction.isEndt()){
					this.mytemplate.update(getQuery("UPD_PM_ADMIN_REM"), new String[]{"Y", "Normal", premiumAction.getApplicationNo()});
				 }
				 String debitCustId="", paymentMode=premiumAction.getPaymentMode();
				 if("CR".equalsIgnoreCase(paymentMode)){
					 if("11".equals(premiumAction.getProductId())){
						 debitCustId=new PolicyGenerationDAO().getValue("GET_OC_CUST_ID", new String[]{premiumAction.getOpenCoverNo()}) ; 
					 }else{
						 debitCustId=premiumAction.getCustomerId();
					 }
				 }else if("CRO".equalsIgnoreCase(paymentMode)){
					 paymentMode="CR";
					 debitCustId=premiumAction.getCustomerId();
				 }
				 args[0]="false".equalsIgnoreCase(premiumAction.getPremiumYN())? "NO":premiumAction.getPremiumYN();
				 args[1]=StringUtils.isEmpty(premiumAction.getBanker())|| "false".equals(premiumAction.getBanker())?"NO":premiumAction.getBanker();					
				 args[2]="NO";
				 args[3]=StringUtils.isEmpty(premiumAction.getBoth())|| "false".equals(premiumAction.getBoth())? "NO":premiumAction.getBoth();
				 args[4]="false".equalsIgnoreCase(premiumAction.getForeign())? "NO":premiumAction.getForeign();
				 args[5]="false".equalsIgnoreCase(premiumAction.getRubberStamp())? "N":premiumAction.getRubberStamp();
				 args[6]=premiumAction.getNoteType();				
				 args[7]=paymentMode==null?"":paymentMode;				
				 args[8]=premiumAction.getBasisValDesc();				
				 args[9]=StringUtils.isEmpty(premiumAction.getCertClausesYN())|| "false".equals(premiumAction.getCertClausesYN())? "N":premiumAction.getCertClausesYN();				
				 args[10]=debitCustId==null?"":debitCustId;	
				 args[11]=StringUtils.isEmpty(premiumAction.getShowpremiumYN())||"false".equalsIgnoreCase(premiumAction.getShowpremiumYN())? "N":premiumAction.getShowpremiumYN();
				 args[12]=StringUtils.isEmpty(premiumAction.getPrintClausesYN())||"false".equalsIgnoreCase(premiumAction.getPrintClausesYN())? "N":premiumAction.getPrintClausesYN();
				 args[13]="false".equalsIgnoreCase(premiumAction.getExcess())? "NO":premiumAction.getExcess();
				 args[14]=premiumAction.getApplicationNo();				
				 sql=getQuery(DBConstants.PREMIUM_UPDATE_POLICY);
				 result=this.mytemplate.update(sql,args);
				 
				 if(!"Y".equalsIgnoreCase(premiumAction.getGeneratePolicy())){
					 args=new Object[12];
					 args[0]="false".equalsIgnoreCase(premiumAction.getPremiumYN())? "NO":premiumAction.getPremiumYN();
					 args[1]=StringUtils.isEmpty(premiumAction.getBanker())|| "false".equals(premiumAction.getBanker())?"NO":premiumAction.getBanker();					
					 args[2]="NO";
					 args[3]=StringUtils.isEmpty(premiumAction.getBoth())|| "false".equals(premiumAction.getBoth())? "NO":premiumAction.getBoth();
					 args[4]="false".equalsIgnoreCase(premiumAction.getForeign())? "NO":premiumAction.getForeign();
					 args[5]="false".equalsIgnoreCase(premiumAction.getRubberStamp())? "N":premiumAction.getRubberStamp();
					 args[6]=StringUtils.isEmpty(premiumAction.getCertClausesYN())|| "false".equals(premiumAction.getCertClausesYN())? "N":premiumAction.getCertClausesYN();
					 args[7]=StringUtils.isEmpty(premiumAction.getShowpremiumYN())||"false".equalsIgnoreCase(premiumAction.getShowpremiumYN())? "N":premiumAction.getShowpremiumYN();
					 args[8]=StringUtils.isEmpty(premiumAction.getPrintClausesYN())||"false".equalsIgnoreCase(premiumAction.getPrintClausesYN())? "N":premiumAction.getPrintClausesYN();
					 args[9]="false".equalsIgnoreCase(premiumAction.getExcess())? "NO":premiumAction.getExcess();
					 args[10]="false".equalsIgnoreCase(premiumAction.getBasisValDesc())? "ACTUAL AMOUNT":premiumAction.getBasisValDesc();
					 args[11]=premiumAction.getApplicationNo();	
					 result=this.mytemplate.update(getQuery("UPD_POLICY_DOC_INFO"),args);
				 }
			}
		 }catch(Exception e){
			 LogManager.debug("EXCEPTION @ { " + e + " }");
			 e.printStackTrace();
		 }
		 LogManager.info("Query===>" + sql);
		 LogManager.info("Args===>" + StringUtils.join(args,","));		 
		 LogManager.info("updatePolicyInformation() - Exit || Result: " + result);
		 LogManager.popRemove();
		 return result;
	 }
	 public String updatePremiumInfo(String applicationNo,String premium, String excessPremium, String policyFee, String totalWarPremium, String finalPremium, String govtTax,String inceptionFee)
	 {
		LogManager.push("updatePremiumInfo - Enter || applicationNo: "+applicationNo+" premium: "+premium+" excess: "+excessPremium+" fee: "+policyFee + " govtTax: " + govtTax);
		String result="";
		try 
		{
			sql=getQuery(DBConstants.PREMIUM_UPDATE_MD);
			this.mytemplate.update(sql, new String[]{premium,excessPremium,policyFee,govtTax,totalWarPremium,inceptionFee,applicationNo});
			sql=getQuery(DBConstants.PREMIUM_UPDATE_PM);
			this.mytemplate.update(sql, new String[]{String.valueOf(trunc("round",finalPremium,"0")),excessPremium,applicationNo});
			sql=getQuery("UPD_WS_PRE_INFO");
			this.mytemplate.update(sql, new String[]{premium,excessPremium,totalWarPremium,policyFee,inceptionFee,applicationNo});
		} catch (Exception e) {
			LogManager.debug("EXCEPTION @ { " + e + " }");
		}
		LogManager.push("updatePremiumInfo - Exit || Result: "+result);
		LogManager.popRemove();
		return result;
	 }
	 public List<Object> getPolicyInformation(String applicationNo)
	 {
			List<Object> policyInfo=new ArrayList<Object>();		
			LogManager.push("getPolicyInformation - Enter");
			try{
				sql=getQuery(DBConstants.PREMIUM_POLICYINFO);
				policyInfo=this.mytemplate.queryForList(sql,new String[]{applicationNo});			
			   }
			catch (Exception e) {			
				LogManager.debug("EXCEPTION @ { " + e + " }");
			}		
			LogManager.info("Query===>" + sql+" args==>" +applicationNo);
			LogManager.info("getPolicyInformation - Exit || Result: " + policyInfo.size());
			LogManager.popRemove();		
			return policyInfo;
	}	
	 public boolean getCustAccountStatus(String applicationNo)
	 {
		 LogManager.push("getCustAccountStatus - Enter || applicationNo: "+applicationNo);
		 boolean sts=false;
		 try{
			 sql=getQuery(DBConstants.CUST_ACCOUNT);
			 String result=(String) this.mytemplate.queryForObject(sql,new String[]{applicationNo}, String.class);
			 if(!StringUtils.isEmpty(result)){
				 sts=true;
			 }
		 }
		 catch (Exception e) {			
			 LogManager.debug("EXCEPTION @ { " + e + " }");
		 }		
		 LogManager.info("Query===>" + sql+" args==>" +applicationNo);
		 LogManager.info("getCustAccountStatus() - Exit || Result: " + sts);
		 LogManager.popRemove();		
		 return sts;
	 }	
	 public void updateClausesInfo(String[] args)
	 {
		LogManager.push("updateClausesInfo - Enter || args: "+StringUtils.join(args,","));
		int result=0;
		try 
		{
			sql=getQuery(DBConstants.CLAUSES_UPDATE);
			result=this.mytemplate.update(sql, args);
		} catch (Exception e) {
			LogManager.debug("EXCEPTION @ { " + e + " }");
		}
		LogManager.push("updateClausesInfo - Exit || Result: "+result);
		LogManager.popRemove();
	 }
	 public List<Object> getExistingConditions(String option, String branchCode, String coverId, List<String> id)
	 {
	 	LogManager.info("getConditionsList - Enter || "+option);
	 	List<Object> result=new ArrayList<Object>();					
	 	try{
	 		NamedParameterJdbcTemplate namedJdbcTemplate=new NamedParameterJdbcTemplate(this.mytemplate);
	 		MapSqlParameterSource paramters=new MapSqlParameterSource();
	 		paramters.addValue("cover", coverId);
	 		paramters.addValue("id", id);
	 		paramters.addValue("branch", branchCode);
	 		sql=getQuery("condition"+option);
	 		result=namedJdbcTemplate.queryForList(sql, paramters);	
	 	}
	 	catch (Exception e) {			
	 		LogManager.debug("EXCEPTION @ { " + e + " }");
	 	}		
	 	LogManager.info("Query==>" + sql);
	 	LogManager.info("getConditionsList() - Exit || Result: "+result.size());
	 	LogManager.popRemove();		
	 	return result;
	 }
	 public void addConditionsInfo(String[] args,String editClausesYN)
	 {
		LogManager.push("addConditionsInfo - Enter || args: "+StringUtils.join(args,","));
		int result=0;
		try 
		{
			String clauses="",war="",exclusion="",warranty="";
			List<Object> pdfModifyClauses=this.mytemplate.queryForList(getQuery("GET_PDF_MODIFY_CLAUSES"),new Object[]{args[4],args[4]});
			if(pdfModifyClauses!=null && "Y".equalsIgnoreCase(editClausesYN)){
				Map<String,Object> temp=(Map<String,Object>)pdfModifyClauses.get(0);
				String pdfClauses=temp.get("PDF_MODIFY_CLAUSE")==null?"":temp.get("PDF_MODIFY_CLAUSE").toString();
				//For Normal Clauses
				if(StringUtils.isBlank(pdfClauses)){
					 String applicationNo=temp.get("APPLICATION_NO")==null?"":temp.get("APPLICATION_NO").toString();
					 String clausesQuery=getQuery("GET_CONDITIONS_CLAUSES");
					 LogManager.info("clausesQuery===>" + clausesQuery);
					 List<Object> editClauses=this.mytemplate.queryForList(clausesQuery, new String[]{"Clauses",applicationNo, "01",""});
					 if(editClauses!=null && !editClauses.isEmpty())	
						{
							for (int i = 0; i < editClauses.size(); i++) {
								Map<String,Object> map=(Map<String,Object>)editClauses.get(i);
								clauses+=map.get("CODE")+"~"+map.get("CODEDESC")+"#";
							}
							if ("".equals(clauses)) {
								clauses = "NOTHING";
							} else {
								clauses = clauses.substring(0, clauses.length() - 1);
							}
						}
				  }
				  //For War Clauses
				  String warClauses=temp.get("PDF_MODIFY_EXTRACLAUSES")==null?"":temp.get("PDF_MODIFY_EXTRACLAUSES").toString();
				  if(StringUtils.isBlank(warClauses)){
					 	 String applicationNo=temp.get("APPLICATION_NO")==null?"":temp.get("APPLICATION_NO").toString();
						 String clausesQuery=getQuery("GET_CONDITIONS");
						 LogManager.info("clausesQuery===>" + clausesQuery);
						 List<Object> editClauses=this.mytemplate.queryForList(clausesQuery, new String[]{"War",applicationNo, "01",""});
						 if(editClauses!=null && !editClauses.isEmpty())	
							{
								for (int i = 0; i < editClauses.size(); i++) {
									Map<String,Object> map=(Map<String,Object>)editClauses.get(i);
									war+=map.get("CODE")+"~"+map.get("CODEDESC")+"#";
								}
								if ("".equals(clauses)) {
									war = "NOTHING";
								} else {
									war = war.substring(0, war.length() - 1);
								}
							}
						
				  }
				  String warranties=temp.get("PDF_MODIFY_WARRANTY")==null?"":temp.get("PDF_MODIFY_WARRANTY").toString();
				  if(StringUtils.isBlank(warranties)){
					     String applicationNo=temp.get("APPLICATION_NO")==null?"":temp.get("APPLICATION_NO").toString();
						 String clausesQuery=getQuery("GET_CONDITIONS");
						 LogManager.info("clausesQuery===>" + clausesQuery);
						 List<Object> editClauses=this.mytemplate.queryForList(clausesQuery, new String[]{"Warranty",applicationNo, "01",""});
						 if(editClauses!=null && !editClauses.isEmpty())	
							{
								for (int i = 0; i < editClauses.size(); i++) {
									Map<String,Object> map=(Map<String,Object>)editClauses.get(i);
									warranty+=map.get("CODE")+"~"+map.get("CODEDESC")+"#";
								}
								if ("".equals(clauses)) {
									warranty = "NOTHING";
								} else {
									warranty = warranty.substring(0, warranty.length() - 1);
								}
							}
				  }
				  String exclusions=temp.get("PDF_MODIFY_EXCLUSION")==null?"":temp.get("PDF_MODIFY_EXCLUSION").toString();
				  if(StringUtils.isBlank(exclusions)){
					     String applicationNo=temp.get("APPLICATION_NO")==null?"":temp.get("APPLICATION_NO").toString();
						 String clausesQuery=getQuery("GET_CONDITIONS");
						 LogManager.info("clausesQuery===>" + clausesQuery);
						 List<Object> editClauses=this.mytemplate.queryForList(clausesQuery, new String[]{"Exclusion",applicationNo, "01",""});
						 if(editClauses!=null && !editClauses.isEmpty())	
							{
								for (int i = 0; i < editClauses.size(); i++) {
									Map<String,Object> map=(Map<String,Object>)editClauses.get(i);
									exclusion+=map.get("CODE")+"~"+map.get("CODEDESC")+"#";
								}
								if ("".equals(clauses)) {
									exclusion = "NOTHING";
								} else {
									exclusion = exclusion.substring(0, exclusion.length() - 1);
								}
							}
				  }
				  if(StringUtils.isBlank(pdfClauses)){
					//Update pdf_modify_clauses in position_master    
					  this.mytemplate.update(getQuery("UPDATE_PDF_MODIFY_CLAUSES"),new Object[]{clauses,args[4],args[4]});
				  }
				  if(StringUtils.isBlank(warClauses)){
					//Update PDF_MODIFY_EXTRACLAUSES in position_master    
					  this.mytemplate.update(getQuery("UPDATE_PDF_MODIFY_EXTRACLAUSES"),new Object[]{war,args[4],args[4]});
				  }
				  if(StringUtils.isBlank(warranties)){
					//Update PDF_MODIFY_EXTRACLAUSES in position_master    
					  this.mytemplate.update(getQuery("UPDATE_PDF_MODIFY_WARRANTY"),new Object[]{warranty,args[4],args[4]});
				  }
				  if(StringUtils.isBlank(exclusions)){
					//Update PDF_MODIFY_EXTRACLAUSES in position_master    
					  this.mytemplate.update(getQuery("UPDATE_PDF_MODIFY_EXCLUSION"),new Object[]{exclusion,args[4],args[4]});
				  }
				  
			}
			if(("RSAIssuer".equalsIgnoreCase(loginUserType) && "Y".equalsIgnoreCase(editClausesYN)) || "admin".equalsIgnoreCase(loginUserType))
			{
				sql=getQuery(DBConstants.CLAUSES_ADD);
				result=this.mytemplate.update(sql, args);
			}
		} catch (Exception e) {
			LogManager.debug("EXCEPTION @ { " + e + " }");
		}
		LogManager.push("addConditionsInfo - Exit || Result: "+result);
		LogManager.popRemove();
	 }
	
	 public boolean getOpenCoverCustomer(String openCoverNo)
	 {
		 LogManager.push("getInsuredArnoStatus - Enter || customerId: "+openCoverNo);
		 boolean sts=false;
		 try{
			 sql=getQuery(DBConstants.OPENCOVER_CUSTOMER);
			 String result=(String) this.mytemplate.queryForObject(sql,new String[]{openCoverNo}, String.class);
			 if(!StringUtils.isEmpty(result)){
				 sts=true;
			 }
		 }
		 catch (Exception e) {			
			 LogManager.debug("EXCEPTION @ { " + e + " }");
		 }		
		 LogManager.info("Query===>" + sql+" args==>" +openCoverNo);
		 LogManager.info("getArnoStatus() - Exit || Result: " + sts);
		 LogManager.popRemove();		
		 return sts;
		 }	
	 public List<Object> getBasisValList(String branchCode)
	 {
			List<Object> list=null;		
			LogManager.push("getBasisValList - Enter || branchCode: "+branchCode);
			try{
				sql=getQuery(DBConstants.BASIS_VAL_LIST);
				list=this.mytemplate.queryForList(sql,new String[]{branchCode});			
		    }catch (Exception e) {			
				LogManager.debug("EXCEPTION @ { " + e + " }");
			}		
			LogManager.info("getBasisValList - Exit || Result: " + list.size());
			LogManager.popRemove();		
			return list;
	}
	 
	public Map<String, Object> getConditions(String applicationNo,String branchCode)
	 {
	 	LogManager.push("getConditions - Exit || applicationNo: "+applicationNo+" branchCode: "+branchCode);
	 	Map<String, Object> list=new HashMap<String, Object>();
	 	List<Object> result=null;
	 	String sql=getQuery("GET_CONDITIONS");
	 	logger.info("Query==> " + sql);
	 	result=this.mytemplate.queryForList(sql, new String[]{"Clauses",applicationNo, branchCode,""});
	 	list.put("clausesDesc", result);
	 	result=this.mytemplate.queryForList(sql, new String[]{"War",applicationNo, branchCode,""});
	 	list.put("extraClausesDesc", result);
	 	result=this.mytemplate.queryForList(sql, new String[]{"Warranty",applicationNo, branchCode,""});
	 	list.put("warrantyDesc", result);
	 	result=this.mytemplate.queryForList(sql, new String[]{"Exclusion",applicationNo, branchCode,""});
	 	list.put("exclusionsDesc", result);
	 	LogManager.push("getConditions - Exit || result: "+list.size());
	 	return list;
	 }
	public void updateAdminReferralInfo(String applicationNo,String refStatus, String adminRefRemarks, String commission, String adminLogin, String stat) {
		LogManager.push("updateAdminReferralInfo - Enter || applicationNo: "+applicationNo+"		refStatus: "+refStatus+"		adminRefRemarks: "+adminRefRemarks+"		commission: "+commission+"		adminLogin: "+adminLogin+"		stat:"+stat);
		int result=0, count=0;
		/*try{
			if("A".equalsIgnoreCase(refStatus)){
				result=this.mytemplate.update(getQuery("UPD_PM_ADMIN_APPR"), new String[]{adminRefRemarks, commission, adminLogin, applicationNo});
				result=this.mytemplate.update(getQuery("UPD_MD_ADMIN_STS"), new String[]{"N", applicationNo});
			 }else if("N".equalsIgnoreCase(refStatus)){
				 count=this.mytemplate.queryForInt(getQuery("CNT_QUOTE_REFERRAL"), new String[]{applicationNo});
				 if(count<=0){
					 result=this.mytemplate.update(getQuery("UPD_MD_ADMIN_STS"), new String[]{"Y", applicationNo});
					 result=this.mytemplate.update(getQuery("UPD_PM_ADMIN_REM"), new String[]{"Normal", applicationNo});
				 }else{
					 result=this.mytemplate.update(getQuery("UPD_PM_ADMIN_REM"), new String[]{"Referal", applicationNo});
				 }
			 }else if("R".equalsIgnoreCase(refStatus)){
				 result=this.mytemplate.update(getQuery("UPD_MD_ADMIN_STS"), new String[]{"Y", applicationNo});
				 result=this.mytemplate.update(getQuery("UPD_PM_ADMIN_REJ"), new String[]{adminRefRemarks, applicationNo});
				 result=this.mytemplate.update(getQuery("DEL_ENDT_MD"), new String[]{applicationNo});
				 result=this.mytemplate.update(getQuery("DEL_ENDT_MRD"), new String[]{applicationNo});
				 result=this.mytemplate.update(getQuery("UPD_ENDT_STS"), new String[]{applicationNo});
				 result=this.mytemplate.update(getQuery("UPD_ENDT_STS_REJ"), new String[]{applicationNo});
			 }
		} catch (Exception e) {
			LogManager.debug("EXCEPTION @ { " + e + " }");
		}*/
		try  {
			if("A".equalsIgnoreCase(refStatus)){
				result=this.mytemplate.update(getQuery("UPD_PM_ADMIN_APPR"), new String[]{stat, adminRefRemarks, commission, adminLogin, applicationNo});
				result=this.mytemplate.update(getQuery("UPD_MD_ADMIN_STS"), new String[]{"N", applicationNo});
			 }else if("N".equalsIgnoreCase(refStatus)){
				 count=this.mytemplate.queryForInt(getQuery("CNT_QUOTE_REFERRAL"), new String[]{applicationNo});
				 if(count<=0){
					 result=this.mytemplate.update(getQuery("UPD_MD_ADMIN_STS"), new String[]{"Y", applicationNo});
					 result=this.mytemplate.update(getQuery("UPD_PM_ADMIN_REM"), new String[]{stat, "Normal", applicationNo});
				 }else{
					 result=this.mytemplate.update(getQuery("UPD_PM_ADMIN_REM"), new String[]{stat, "Referal", applicationNo});
				 }
				 List<Object> referralStatus=this.mytemplate.queryForList("select a.REMARKS, b.ADMIN_REFERRAL_STATUS,a.status from Position_master a,marine_data b where a.application_no=b.application_no and a.application_no=?",new Object[]{applicationNo});
				 if(referralStatus!=null){
					 Map<String,Object> temp=(Map<String,Object>)referralStatus.get(0);
					 String status=temp.get("STATUS")==null?"":temp.get("STATUS").toString();
					 String adminReferral=temp.get("ADMIN_REFERRAL_STATUS")==null?"":temp.get("ADMIN_REFERRAL_STATUS").toString();
					 String updateRemarks="",updateStatus="";
					 if("R".equalsIgnoreCase(status) && "Y".equalsIgnoreCase(adminReferral)){
						 updateRemarks="Normal";
						 updateStatus=stat;
					 }else if("R".equalsIgnoreCase(status) && "N".equalsIgnoreCase(adminReferral)){
						 updateRemarks="Referal";
						 updateStatus=stat;
					 }
					 if(StringUtils.isNotBlank(updateRemarks) && StringUtils.isNotBlank(updateStatus)){
					     this.mytemplate.update("update position_master set remarks=? , status=? where application_no=?",new Object[]{updateRemarks,updateStatus,applicationNo});
					 }
				 }
				 
			 }else if("R".equalsIgnoreCase(refStatus)){
				 result=this.mytemplate.update(getQuery("UPD_PM_ADMIN_REJ"), new String[]{adminRefRemarks,adminLogin,applicationNo});
				 //result=this.mytemplate.update(getQuery("DEL_ENDT_MD"), new String[]{applicationNo});
				 //result=this.mytemplate.update(getQuery("DEL_ENDT_MRD"), new String[]{applicationNo});
				 //result=this.mytemplate.update(getQuery("UPD_ENDT_STS"), new String[]{applicationNo});
				 //result=this.mytemplate.update(getQuery("UPD_ENDT_STS_REJ"), new String[]{applicationNo});
			 }
			
		} catch (Exception e) {
			LogManager.debug("EXCEPTION @ { " + e + " }");
		}
		LogManager.push("updateAdminReferralInfo - Exit || Result: "+result);
		LogManager.popRemove();
	 }
	public Map<String, Object> getEndtPremiumInfo(final String quoteNo)
	{
		LogManager.info("getEndtPremiumInfo - Enter || quoteNo: "+quoteNo);
		Map<String, Object> map=new HashMap<String, Object>();
		try{
			map=this.mytemplate.queryForMap(getQuery("GET_ENDT_PRE_INFO"), new String[]{quoteNo});
		}catch(Exception e){
			LogManager.debug(e);
		}
		LogManager.info("getEndtPremiumInfo - Exit ");
		return 	map;
	}
	public List<Object> getPolicyDeductibles(String policyNo)
	 {
			List<Object> list=null;		
			LogManager.push("getPolicyDeductibles - Enter || policyNo: "+policyNo);
			try{
				sql=getQuery("GET_POL_DEDUCT");
				list=this.mytemplate.queryForList(sql,new String[]{policyNo});			
		    }catch (Exception e) {			
				LogManager.debug("EXCEPTION @ { " + e + " }");
			}		
			LogManager.info("getPolicyDeductibles - Exit || Result: " + list.size());
			LogManager.popRemove();		
			return list;
	}
	public Map<String,Object> getExistingCustInfo(String customerId) {
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try {
			String query = LocalizedTextUtil.findDefaultText("GET_EXISTINGCUST_INFO",Locale.ENGLISH);
			resultMap = this.mytemplate.queryForMap(query,new Object[]{customerId});
		}
		catch(Exception exception) {
			LogManager.debug(exception);
		}
		LogManager.popRemove();
		return resultMap;
	}
	public String getCreditNoteStatus(String loginId,String productId){
		String result = "";
		try {
			result = (String) this.mytemplate.queryForObject("select FREIGHT_DEBIT_OPTION from login_user_details where login_id=? and PRODUCT_ID=?", new Object[]{loginId,productId},String.class);
		}
		catch(Exception exception) {
			exception.printStackTrace();
		}
		return result;
	}
	public int updatePolicyInformationReferral(PremiumAction premiumAction) throws  BaseException{
		 LogManager.info("updatePolicyInformationReferral() - Enter");
		 int result=0;
		 Object arg[]=new Object[3];
		 try{			
			 	String stat=new PolicyGenerationDAO().isEndt(premiumAction.getApplicationNo())?"E":"Y";
				 arg[0]=StringUtils.defaultIfEmpty(premiumAction.getReferralUpdate(), "N");
				 arg[1]=StringUtils.defaultIfEmpty("Admin Referral~"+(StringUtils.isBlank(premiumAction.getReferralComment())?"Modified Clauses/Rates":premiumAction.getReferralComment().replaceAll("Admin Referral~", "")), "");
				 arg[2]=premiumAction.getApplicationNo();					 
				 sql=getQuery(DBConstants.PREMIUM_UPDATE_REFERRAL);				 
				 result=this.mytemplate.update(sql,arg);
				 result=this.mytemplate.update(getQuery("UPD_WS_ADMIN_REFERRAL"),arg);
				 this.mytemplate.update(getQuery("UPD_PM_ADMIN_REM"), new String[]{stat,"Normal", premiumAction.getApplicationNo()});				  
				 this.mytemplate.update("update marine_data set REFERRAL_TYPE=? where application_no=?", new Object[]{arg[1],arg[2]});
         }catch(Exception e){
			 LogManager.debug("EXCEPTION @ { " + e + " }");
			 e.printStackTrace();
		 }
		 LogManager.info("Query===>" + sql);
		 LogManager.info("updatePolicyInformationReferral() - Exit || Result: " + result);
		 LogManager.popRemove();
		 return result; 
	 }
	public void updatePremiumYN(String premiumYN,String finalizeYN,String editClausesYN, String applicationNo) {
		try{
				String query=getQuery("GET_UPDATE_PREMIUMYN");
				this.mytemplate.update(query,new Object[]{("false".equalsIgnoreCase(premiumYN)|| null==premiumYN)? "NO":premiumYN,finalizeYN==null?"":finalizeYN,editClausesYN==null?"N":editClausesYN,applicationNo,applicationNo});
				if(premiumYN!=null){	
					query="UPDATE WEBSERVICE_MARINE_QUOTE SET PDF_PRE_SHOW_STATUS=? where APPLICATION_NO=?";
					this.mytemplate.update(query,new Object[]{("false".equalsIgnoreCase(premiumYN)|| null==premiumYN)? "NO":premiumYN,applicationNo});
				}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public  double trunc(String type,String valueOf,String round) {
		double value=0.0;
			try{
				if("trunc".equalsIgnoreCase(type)){
				  value =(Double)this.mytemplate.queryForObject("select nvl(trunc(?,?),0) from dual",new Object[]{valueOf,round}, Double.class);
				}else{
					value =(Double)this.mytemplate.queryForObject("select nvl(round(?,?),0) from dual",new Object[]{valueOf,round}, Double.class);
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		LogManager.info("round - Exit || Result: "+value);
		return value;
	}
	public void updateExcessDescp(String desc, String applicationNo) {
		int status=0;
		LogManager.info("updateExcessDescp - Enter || applicationNo: "+applicationNo);
		try
		{
			sql=getQuery("UPD_EXCESS_DESC_CHD_WEB");
			status = this.mytemplate.update(sql, new String[]{desc,applicationNo});
			sql=getQuery("UPD_EXCESS_DESC_CHD_MRD");
			status = this.mytemplate.update(sql, new String[]{desc,applicationNo});
		}catch(Exception e)
		{
			LogManager.debug("Exception @ "+e);
		}
		LogManager.popRemove();
		LogManager.info("updateExcessDescp - Exit");
	}
}
