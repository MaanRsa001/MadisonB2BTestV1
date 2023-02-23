package com.maan.quotation.service;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.maan.common.exception.BaseException;
import com.maan.quotation.PremiumAction;
import com.maan.quotation.dao.PremiumDAO;
import com.maan.webservice.PolicyGenerationAction;
import com.maan.webservice.dao.PolicyGenerationDAO;



public class PremiumService {
	final static transient private String NOTHING = "NOTHING";
	PremiumDAO premiumDAO=new PremiumDAO();	
	PolicyGenerationDAO policyDAO=new PolicyGenerationDAO();	
	public List<Object> getQuotationInformation(String applicationNo,String branchCode){
		return premiumDAO.getQuotationInformation(applicationNo,branchCode);
	}
	 public List<Object> getQuotationInsuredValue(String applicationNo,String branchCode){
		 return premiumDAO.getQuotationInsuredValue(applicationNo,branchCode);
	 }
	 public int updatePolicyInformation(PremiumAction premiumAction)throws  BaseException{
		 return premiumDAO.updatePolicyInformation(premiumAction);
	 }
	 public int updatePolicyInformationReferral(PremiumAction premiumAction) throws  BaseException{
		 return premiumDAO.updatePolicyInformationReferral(premiumAction);
	 }
	 public Map<String, Object> calculatePremium(String refNo, String[][] commoidty)throws  BaseException{
		 return new PolicyGenerationAction(refNo).calculate(commoidty);
	 }
	 public String updatePremiumInfo(String applicationNo, String totalPremium, String excessPremium, String policyFee,String totalWarPremium, String govtTax,String inceptionFee)throws  BaseException{
		 String finalPremium= String.valueOf(Double.parseDouble(totalPremium)+Double.parseDouble(policyFee)+Double.parseDouble(govtTax)+Double.parseDouble(inceptionFee));
		 return this.premiumDAO.updatePremiumInfo(applicationNo, totalPremium, excessPremium, policyFee, totalWarPremium, finalPremium, govtTax,inceptionFee);
	 }
	 public void updateReferralInfo(String applicationNo,String refStatus, String adminRefRemarks, String commission, String adminLogin, String stat)throws  BaseException{
		 premiumDAO.updateAdminReferralInfo(applicationNo, refStatus, adminRefRemarks, commission, adminLogin, stat);
	 }
	 public List<Object> getPolicyInformation(String applicationNo)
	 {
		 return premiumDAO.getPolicyInformation(applicationNo);
	 }
	 public Map<String, Object> getConditionsInfo(String applicationNo, String branchCode)throws  BaseException{
		 return premiumDAO.getConditions(applicationNo, branchCode);
	 }
	 public String convertConditions(List<Object> idList, List<Object> valueList)throws  BaseException
	 {
		 String result="";
		 if(idList!=null && !idList.isEmpty())	
			{
				for (int i = 0; i < idList.size(); i++) {
					if(StringUtils.isNotEmpty(valueList.get(i).toString()))
					result+=idList.get(i)+"~"+valueList.get(i)+"#";
				}
				if ("".equals(result)) {
					result = NOTHING;
				} else {
					result = result.substring(0, result.length() - 1);
				}
			}
		 return result;
	 }
	 public String policyGeneration(String refNo)
	 {
		 return new PolicyGenerationAction(refNo).policyGeneration();
	 }
	 public boolean getCommissionStatus(String loginId, String productId, String openCoverNo, String issuer, String applicationNo, String branchCode)
	 {
		 String commission=policyDAO.getCommissionPercent(loginId, productId, openCoverNo, issuer, applicationNo, branchCode);
		 return Double.parseDouble(commission)>0;
	 }
	 public boolean getCustAccountStatus(String applicationNo)
	 {
		 return premiumDAO.getCustAccountStatus(applicationNo);
	 }
	 public void updateClausesInfo(String args[])
	 {
		 premiumDAO.updateClausesInfo(args);
	 }
	 public List<Object> getExistingConditions(String option, String branchCode, String coverId, List<String> id)
	 {
		 return premiumDAO.getExistingConditions(option, branchCode, coverId, id);
	 }
	 public void addConditionsInfo(String args[],String editClausesYN)
	 {
		 premiumDAO.addConditionsInfo(args,editClausesYN);
	 }
	 public void sendMail(String refNo, String userType, String refStatus)throws  BaseException{
		 refStatus="A".equals(refStatus)?"accept":("R".equals(refStatus)?"reject":"none");
		 new PolicyGenerationAction(refNo, userType,userType).mailGeneration(refStatus);
	 }
	 public void quoteMail(String refNo, String userType, String refStatus, String toMailAddress)throws  BaseException{
		 refStatus="A".equals(refStatus)?"accept":("R".equals(refStatus)?"reject":"none");
		 new PolicyGenerationAction(refNo, userType,userType).guoteMailGeneration(refStatus,toMailAddress);
	 }
	 public void referralResponseMail(String refNo, String userType, String refStatus)throws  BaseException{
		 refStatus="A".equals(refStatus)?"accepted":("R".equals(refStatus)?"rejected":"pending");
		 new PolicyGenerationAction(refNo, userType,userType).referralResponseMailGeneration(refStatus);
	 }
	 public boolean getOpenCoverCustomer(String openCoverNo)
	 {
		 return premiumDAO.getOpenCoverCustomer(openCoverNo);
	 }
	 public List<Object> getBasisValList(String branchCode){
		 return premiumDAO.getBasisValList(branchCode);
	 }
	 public Map<String, Object> getPremiumLoadDisc(String loginId)throws  BaseException{
		 return new PolicyGenerationDAO().getResultMap("GET_PREM_LOAD_DISC", new String[]{loginId});
	 }
	 public String getBrokerType(String loginId)
	 {
		 return policyDAO.getValue("brokerType", new String[]{loginId});
	 }
	 public String getLoginId(String applicationNo)
	 {
		 return policyDAO.getValue("GET_LOGIN_ID", new String[]{applicationNo});
	 }
	 public void updateEndtPremium(String quoteNo){
		 policyDAO.updateEndtPremium(quoteNo);
	 }
	 public Map<String, Object> getEndtPremiumInfo(String quoteNo){
		 return premiumDAO.getEndtPremiumInfo(quoteNo);
	 }
	 public boolean isEndt(String applicationNo) {
		 return policyDAO.isEndt(applicationNo);
	 }
	 public boolean isFinancial(String applicationNo) {
		 return policyDAO.isFinancial(applicationNo);
	 }
	 public boolean endtPolicyFee(String applicationNo) {
			return policyDAO.endtPolicyFee(applicationNo);
	 }
	 public void updateEndtPolicyFee(String applicationNo, String policyFee, String govtTax){
		 policyDAO.updateEndtPolicyFee(applicationNo, policyFee, govtTax);
	 }
	 public List<Object> getPolicyDeductibles(String applicationNo)
	 {
		 return premiumDAO.getPolicyDeductibles(applicationNo);
	 }
	 public String getNoteTypeInfo(String applicationNo)
	 {
		 return policyDAO.getNoteTypeInfo(applicationNo);
	 }
	 public String getPaymentModeInfo(String applicationNo)
	 {
		 return policyDAO.getPaymentModeInfo(applicationNo);
	 }
	 public Map<String,Object> getExistingCustInfo(String customerId) {
		 return premiumDAO.getExistingCustInfo(customerId);
	 }
	 public String getCreditNoteStatus(String loginId,String productId){
		 return premiumDAO.getCreditNoteStatus(loginId,productId);
	 }
	 public String validateSumInsured(String applicationNo, String openCoverNo) {
		 return policyDAO.validateSumInsured(applicationNo,openCoverNo);
	 }
	 public void updatePremiumYN(String premiumYN,String finalizeYN,String editClausesYN, String applicationNo) {
	     premiumDAO.updatePremiumYN(premiumYN,finalizeYN,editClausesYN,applicationNo);	
	}
	public void updateExcessDescp(String desc, String applicationNo) {
		premiumDAO.updateExcessDescp(desc,applicationNo);
		
	}
}
