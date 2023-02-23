package com.maan.adminnew.MotorNew;

import java.util.List;
import java.util.Map;

import com.maan.common.LogManager;


public class MotorAdminServiceNew {
	MotorAdminDaoNew dao = new MotorAdminDaoNew();

	public List<Map<String, Object>> getSubPolicyList(MotorAdminBeanNew bean) {
		return dao.getSubPolicyList(bean);
	}

	public List<Map<String, Object>> getComprehensiveRateList(MotorAdminBeanNew bean) {
		return dao.getComprehensiveRateList(bean);
	}

	public List<Map<String, Object>> getAjaxValidationList(MotorAdminBeanNew bean) {
		return dao.getAjaxValidationList(bean);
	}

	public String insertSubPolicyType(MotorAdminBeanNew bean) {
		return dao.insertSubPolicyType(bean);
	}

	public int checkVehicleAge(MotorAdminBeanNew bean) {
		return dao.checkVehicleAge(bean);
	}

	public void editSubPolicyDetails(MotorAdminBeanNew bean) {
		dao.editSubPolicyDetails(bean);
	}

	public void getComprehensiveRateDropDown(MotorAdminBeanNew bean,String branchCode) {
		dao.getComprehensiveRateDropDown(bean,branchCode);
	}

	public long diffInDays(String policyStartDate, String policyEndDate) {
		return dao.diffInDays(policyStartDate, policyEndDate);
	}

	public String insertCompreRate(MotorAdminBeanNew bean) {
		return dao.insertinsCompreRate(bean);
	}
	public int checkCombinationOfSubPolicy(MotorAdminBeanNew bean) {
		return dao.checkCombinationOfSubPolicy(bean);
	}
	public void getEditCompreRateDetails(MotorAdminBeanNew bean) {
		dao.getEditCompreRateDetails(bean);
	}

	public List<Map<String, Object>> getMakeList(MotorAdminBeanNew bean) {
		return dao.getMakeList(bean);
	}

	public List<Map<String, Object>> getModelList(MotorAdminBeanNew bean) {
		return dao.getModelList(bean);
	}

	public List<Map<String, Object>> getHeaderDetails(MotorAdminBeanNew bean, String type) {
		return dao.getHeaderDetails(bean,type);
	}

	public List<Map<String, Object>> getDropDown(MotorAdminBeanNew bean, String type) {
		return dao.getDropDown(bean, type);
	}

	public String insertMakeMaster(MotorAdminBeanNew bean) {
		return dao.insertMakeMaster(bean);
	}

	public void getEditMakeDetails(MotorAdminBeanNew bean) {
		dao.getEditMakeDetails(bean);
	}

	public String insertModelMaster(MotorAdminBeanNew bean) {
		return dao.insertModelMaster(bean);
	}

	public void getEditModelDetails(MotorAdminBeanNew bean) {
		dao.getEditModelDetails(bean);
	}
	public List<Map<String,Object>> getDocument(MotorAdminBeanNew bean){
		return dao.getDocument(bean);
	}
	public List<Map<String,Object>> getDocumentEdit(MotorAdminBeanNew bean){
		return dao.getDocumentEdit(bean);
	}
	public List<Map<String,Object>> getMaxDocumentId(MotorAdminBeanNew bean){
		return dao.getMaxDocumentId(bean);
	}
	public boolean getDocumentUpdate(MotorAdminBeanNew bean){
		return dao.getDocumentUpdate(bean);
	}
	public List<Map<String, Object>> getDocumentdrpdwn(MotorAdminBeanNew bean,String type){
		return dao.getDropDown(bean, type);
	}
	public List<Map<String, Object>> getOpCoverList(MotorAdminBeanNew bean) {
		return dao.getOpCoverList(bean);
	}
	public void getEditOpCover(MotorAdminBeanNew bean) {
		dao.getEditOpCover(bean);
	}	
	public void insertinsOpCover(MotorAdminBeanNew bean) {
		dao.insertinsOpCover(bean);
	}
	public List<Map<String, Object>> getOpCoverDetailList(MotorAdminBeanNew bean) {
		return dao.getOpCoverDetailList(bean);
	}
	public void getEditOpCoverDetail(MotorAdminBeanNew bean) {
		dao.getEditOpCoverDetail(bean);
	}	
	public void insertinsOpCoverDetail(MotorAdminBeanNew bean) {
		dao.insertinsOpCoverDetail(bean);
	}
//BODY TYPE MASTER
	public List<Map<String, Object>> BodyTypeMasterList(MotorAdminBeanNew bean, String branchCode) {
		return dao.BodyTypeMasterList(bean,branchCode);
	}

	public void editbodyTypeMaster(MotorAdminBeanNew bean) {
		dao.editbodyTypeMaster(bean);
	}

	public String updatebodyTypeMaster(MotorAdminBeanNew bean) {
		return dao.updatebodyTypeMaster(bean);
	}

	public List<Map<String, Object>> vehiclelinkforBodyTypeMaster(MotorAdminBeanNew bean) {
		return dao.vehiclelinkforBodyTypeMaster(bean);
	}

	public void editvehiclebodytypeMaster(MotorAdminBeanNew bean) {
		dao.editvehiclebodytypeMaster(bean);
	}

	public List<Map<String, Object>> vehicleList(MotorAdminBeanNew bean) {
		return dao.vehicleList(bean);
	}

	public int count(MotorAdminBeanNew bean) {
		return dao.count(bean);
	}

	public void bodyName(MotorAdminBeanNew bean) {
		dao.bodyName(bean);
	}

	public void vehicleInsert(MotorAdminBeanNew bean) {
		dao.vehicleInsert(bean);
	}

	public String vehicleInsertBodyTyeId(MotorAdminBeanNew bean) {
		return	dao.vehicleInsertBodyTyeId(bean);
	}

	public int coreappcodecount(MotorAdminBeanNew bean) {
		return dao.coreappcodecount(bean);
	}
	public List<Map<String, Object>> getThirdPartList(MotorAdminBeanNew bean) {
		return dao.getThirdPartyList(bean);
	}

	public void getEditThirdParty(MotorAdminBeanNew bean) {
		dao.getEditThirdParty(bean);
	}

	public int checkThirdPartyCoreCode(MotorAdminBeanNew bean) {
		return dao.checkThirdPartyCoreCode(bean);
	}

	public String insertThirdPartyMaster(MotorAdminBeanNew bean) {
		return dao.insertThirdPartyMaster(bean);
	}

	public int checkThirdPartySeating(MotorAdminBeanNew bean) {
		return dao.checkThirdPartySeating(bean);
	}

	public List<Map<String, Object>> getThirdPartModelList(MotorAdminBeanNew bean) {
		return dao.getThirdPartyModelList(bean);
	}

	public int checkMakeCoreCode(MotorAdminBeanNew bean) {
		return dao.checkMakeCoreCode(bean);
	}

	public int checkModelCoreCode(MotorAdminBeanNew bean) {
		return dao.checkModelCoreCode(bean);
	}
	public List<Map<String,Object>> getdeductible(MotorAdminBeanNew bean){
		return dao.getDeductible(bean);
	}
	public List<Map<String,Object>> getDeductibleEdit(MotorAdminBeanNew bean){
		return dao.getDeductibleEdit(bean);
	}
	public void getDeductibleInsert(MotorAdminBeanNew bean){
		 dao.getDeductibleInsert(bean);
	}
	//Vehicle Type Master
	public List<Map<String, Object>> getVehicleType(MotorAdminBeanNew bean) {
		return dao.getVehicleType(bean);
	}

	public void getEditVehicleType(MotorAdminBeanNew bean) {
		dao.getEditVehicleType(bean);
	}

	public String insertVehicleTypeMaster(MotorAdminBeanNew bean) {
		 return dao.insertVehicleTypeMaster(bean);		
	}
	
	public int checkVTCoreAppCode(MotorAdminBeanNew bean){
		return dao.checkVTCoreAppCode(bean);
		}
	public List<Map<String,Object>> getAreaCoverage(MotorAdminBeanNew bean){
		return dao.getAreaCoverage(bean);
	}

	public List<Map<String, Object>> getBankMasterList(MotorAdminBeanNew bean) {
		return dao.getBankMasterList(bean);
	}
	public void getEditBankMaster(MotorAdminBeanNew bean) {
		dao.getEditBankMaster(bean);
	}	
	public String insertinsBankMaster(MotorAdminBeanNew bean) {
		return dao.insertinsBankMaster(bean);
	}
	public List<Map<String, Object>> getMfgCountryMasterList(MotorAdminBeanNew bean) {
		return dao.getMfgCountryMasterList(bean);
	}
	public void getEditMfgCountryMaster(MotorAdminBeanNew bean) {
		dao.getEditMfgCountryMaster(bean);
	}	
	public void insertinsMfgCountryMaster(MotorAdminBeanNew bean) {
		dao.insertinsMfgCountryMaster(bean);
	}
	public int checkCoreAppCodeCountryExists(MotorAdminBeanNew bean){
		return dao.checkCoreAppCodeCountryExists(bean);
		
	}
	public List<Map<String, Object>> getPolicyTypeMasterList(MotorAdminBeanNew bean) {
		return dao.getPolicyTypeMasterList(bean);
	}

	public void getEditPolicyTypeMaster(MotorAdminBeanNew bean) {
		dao.getEditPolicyTypeMaster(bean);
	}

	public String insertPolicyTypeMaster(MotorAdminBeanNew bean) {
		return dao.insertPolicyTypeMaster(bean);
	}

	public int checkPolicyTypeCoreCode(MotorAdminBeanNew bean) {
		return dao.checkPolicyTypeCoreCode(bean);
	}

	public List<Map<String, Object>> getConstantDetailsList(MotorAdminBeanNew bean) {
		return dao.getConstatntDetailList(bean);
	}

	public List<Map<String, Object>> getNCBList(MotorAdminBeanNew bean) {
		return dao.getNCBList(bean);
	}

	public void getEditNCBMaster(MotorAdminBeanNew bean) {
		dao.getEditNCBMaster(bean);
	}

	public int checkNCBYear(MotorAdminBeanNew bean) {
		return dao.checkNCBYear(bean);
	}

	public int checkNCBCoreCode(MotorAdminBeanNew bean) {
		return dao.checkNCBCoreCode(bean);
	}

	public String insertNCBMaster(MotorAdminBeanNew bean) {
		return dao.insertNCBMaster(bean);
	}
	public List<Map<String,Object>> getbankFinance(MotorAdminBeanNew bean) {
		return dao.getbankFinance(bean);
	}

	public void getEditBankFinance(MotorAdminBeanNew bean) {
			dao.getEditBankFinance(bean);	
	}

	public String insertBankFinanceMaster(MotorAdminBeanNew bean) {		
		return dao.insertBankFinanceMaster(bean);
		
	}

	public int checkBFMBankCode(MotorAdminBeanNew bean) {
		return dao.checkBFMBankCode(bean);
	}

	public List<Map<String,Object>> getGroup(MotorAdminBeanNew bean) {
		return dao.getGroup(bean);
	}

	public void getEditGroup(MotorAdminBeanNew bean) {
		dao.getEditGroup(bean);	
	}

	public String getInsertGroup(MotorAdminBeanNew bean) {
		return dao.getInsertGroup(bean);
	}

	public int checkDisplayOrder(MotorAdminBeanNew bean) {
		return dao.checkDisplayOrder(bean);
	}
	public int checkCoreAppCodeOpCoverExists(MotorAdminBeanNew bean){
		return dao.checkCoreAppCodeOpCoverExists(bean);
		
	}
	public int checkCoreAppCodeOpCoverDetExists(MotorAdminBeanNew bean){
		return dao.checkCoreAppCodeOpCoverDetExists(bean);
	}
	public List<Map<String,Object>> getAreaCoverageEdit(MotorAdminBeanNew bean){
		return dao.getAreaCoverageEdit(bean);
	}
	public String getAreaCoverageInsert(MotorAdminBeanNew bean){
		return dao.getAreaCoverageInsert(bean);
	}
	public int checkAreaCoverageCode(MotorAdminBeanNew bean){
		return dao.checkAreaCoverageCode(bean);
		}

	public int checkDocumentDisplayOrder(MotorAdminBeanNew bean) {
		return dao.checkDocumentDisplayOrder(bean);
	}

	public int checkPolicyDate(MotorAdminBeanNew bean) {
		return dao.checkPolicyDate(bean);
	}
	public boolean checkOpPolicyExists(MotorAdminBeanNew bean) {
		return dao.checkOpPolicyExists(bean);
	}
	public int checkThirdPartyVehicleUsage(MotorAdminBeanNew bean) {
		return dao.checkThirdPartyVehicleUsage(bean);
	}
	public List<Map<String,Object>> getQuarter(MotorAdminBeanNew bean) {
		return dao.getQuarter(bean);
	}

	public void getQuarterEdit(MotorAdminBeanNew bean) {
		dao.getQuarterEdit(bean);	
	}
	public String getInsertQuarter(MotorAdminBeanNew bean) {
	return dao.getQuarterInsert(bean);
		
	}
	public int getQuarterExists(MotorAdminBeanNew bean)
	{
		return dao.getQuarterExists(bean);
	}
	public int checkNCBVehicleUsage(MotorAdminBeanNew bean) {
		return dao.checkNCBVehicleUsage(bean);
	}

	public int checkModelBody(MotorAdminBeanNew bean) {
		return dao.checkModelBody(bean);
	}
	public int checkBMBankCode(MotorAdminBeanNew bean) {
		return dao.checkBMBankCode(bean);
	}

	public void getInsertConstant(MotorAdminBeanNew bean) {
		dao.getInsertConstant(bean);
		
	}
	
	public void getEditConstant(MotorAdminBeanNew bean) {
		dao.getEditConstant(bean);
	}

	public List<Map<String, Object>> getModelDetail(MotorAdminBeanNew bean) {
		return dao.getModelDetail(bean);
	}

	public void getEditModelDetail(MotorAdminBeanNew bean) {
		dao.getEditModelDetail(bean);
		
	}

	public String insertModelDetail(MotorAdminBeanNew bean) {
		return dao.insertModelDetail(bean);
	}

	public int checkModelBodyType(MotorAdminBeanNew bean) {
		// TODO Auto-generated method stub
		return dao.checkModelBodyType(bean);
	}

	public List<Map<String, Object>> getMoUploadImg(MotorAdminBeanNew bean) {
		return dao.getMoUploadImg(bean);
	}

	public List<Map<String, Object>> getMoUploadImgView(MotorAdminBeanNew bean) {
		return dao.getMoUploadImgView(bean);
	}
	
	public List<Map<String, Object>> getMoUploadImgViewList(MotorAdminBeanNew bean) {
		return dao.getMoUploadImgViewList(bean);
	}

	public String getFilePath(MotorAdminBeanNew bean) {
		return dao.getFilePath(bean);
	}

	public List<Map<String, Object>> getNotifyList(MotorAdminBeanNew bean) {
		return dao.getNotifyList();
	}

	public void getEditNotify(MotorAdminBeanNew bean) {
		dao.getEditNotify(bean);
	}

	public void getInsertNotify(MotorAdminBeanNew bean) {
		dao.getInsertNotify(bean);
	}
	
	public List<Map<String, Object>> getPaymentBankList(MotorAdminBeanNew bean) {
		return dao.getPaymentBankList();
	}

	public void getEditPaymentBank(MotorAdminBeanNew bean) {
		dao.getEditPaymentBank(bean);
	}

	public String getInsertPaymentBank(MotorAdminBeanNew bean) {
		return dao.getInsertPaymentBank(bean);
	}
	
	public boolean checkPBExit(MotorAdminBeanNew bean) {
		return dao.checkPBExit(bean);
	}
	/*public List<Map<String, Object>> getMaxofTypeBodyId(MotorAdminBeanNew bean) {
		// TODO Auto-generated method stub
		return dao.MaxofTypeBodyId(bean);
	}*/

	public List<Object> getUserList(String user, String productId) {
		return dao.getUserList(user,productId);
	}

	public List<Map<String, Object>> getBranchList() {
		return dao.getBranchList();
	}

	public List<Map<String, Object>> getMotorRateList(MotorAdminBeanNew bean) {
		return dao.getMotorRateList(bean);
	}

	public void setRateConfigDetails(MotorAdminBeanNew bean) {
		dao.setRateConfigDetails(bean);
	}

	public List<Map<String, Object>> getMotorFactorList(MotorAdminBeanNew bean) {
		return dao.getMotorFactorList(bean);
	}

	public void gettransactionDtls(MotorAdminBeanNew bean) {
		dao.gettransactionDtls(bean);
	}

	public List<Map<String, Object>> getfactortrandetails() {
		return dao.getfactortrandetails();
	}

	public List<Map<String, Object>> rateDetailsList(MotorAdminBeanNew bean) {
		return dao.rateDetailsList(bean);
	}

	public List<Map<String, Object>> getFactorDetailList(MotorAdminBeanNew bean) {
		return dao.getFactorDetailList(bean);
	}

	public void setLabelValues(MotorAdminBeanNew bean) {
		dao.setLabelValues(bean);
	}

	public List<Map<String, Object>> getBrokerBranchList() {
		return dao.getBrokerBranchList();
	}

	public void getEditBrokerbranch(MotorAdminBeanNew bean) {
		dao.getEditBrokerbranch(bean);
	}

	public int updatebrokerBranch(MotorAdminBeanNew bean) {
		return dao.updatebrokerBranch(bean);
	}

	public void editRateDetails(MotorAdminBeanNew bean) {
		dao.editRateDetails(bean);
	}

	public int updateRateModified(MotorAdminBeanNew bean) {
		return dao.updateRateModified(bean);
	}
	
	public List<Map<String, Object>> getExecutiveList() {
		return dao.getExecutiveList();
	}

	public void getEditExecutive(MotorAdminBeanNew bean) {
		dao.getEditExecutive(bean);
	}

	public int updateExecutive(MotorAdminBeanNew bean) {
		return dao.updateExecutive(bean);
	}

	public int branchExist(MotorAdminBeanNew bean) {
		return dao.branchExist(bean);
	}

	public List<Map<String, Object>> conditionsList() {
		return dao.conditionsList();
	}

	public void editConditions(MotorAdminBeanNew bean) {
		try {
			List<Map<String, Object>> list = dao.specificCondition(bean);
			if(list!=null && list.size()>0) {
				Map<String, Object> map = list.get(0);
				if(map!=null && map.size()>0) {
					bean.setPolicyType(map.get("POLICY_TYPE_ID")==null?"":map.get("POLICY_TYPE_ID").toString());
					bean.setConditionType(map.get("CONDITION_TYPE")==null?"":map.get("CONDITION_TYPE").toString());
					bean.setConditionDesc(map.get("CONDITION_DESC")==null?"":map.get("CONDITION_DESC").toString());
					bean.setCoreappCode(map.get("COREAPPCODE")==null?"":map.get("COREAPPCODE").toString());
					bean.setStatus(map.get("STATUS")==null?"":map.get("STATUS").toString());
					bean.setSno(map.get("SNO")==null?"":map.get("SNO").toString());
				}
			}
		}catch(Exception e) {
			LogManager.error("Exception @ MotorAdminServiceNew.editConditions() "+e);
			e.printStackTrace();
		}
	}

	public boolean insUpdCondition(MotorAdminBeanNew bean) {
		return dao.insUpdCondition(bean);
	}

	public List<Map<String, Object>> getFactorDetails(MotorAdminBeanNew bean) {
		return dao.getFactorDetails(bean);
	}

	public List<Map<String, Object>> getExchangeList() {
		return dao.getExchangeList();
	}

	public List<Map<String, Object>> getCurrencyList() {
		return dao.getCurrencyList();
	}

	public List<Map<String, Object>> getExchangeCountryList() {
		return dao.getExchangeCountryList();
	}

	public void editExchangeData(MotorAdminBeanNew bean) {
		try {
			List<Map<String, Object>> res =dao.editExchangeData(bean);
			if(res!=null && res.size()>0) {
				bean.setExchangeRate(res.get(0).get("EXCHANGE_RATE")==null?"":res.get(0).get("EXCHANGE_RATE").toString());
				bean.setCurrencyName(res.get(0).get("CURRENCY_ID")==null?"":res.get(0).get("CURRENCY_ID").toString());
				bean.setCountryName(res.get(0).get("COUNTRY_ID")==null?"":res.get(0).get("COUNTRY_ID").toString());
				bean.setStartDate(res.get(0).get("INCEPTION_DATE")==null?"":res.get(0).get("INCEPTION_DATE").toString());
				bean.setEndDate(res.get(0).get("EXPIRY_DATE")==null?"":res.get(0).get("EXPIRY_DATE").toString());
				bean.setEffectiveDate(res.get(0).get("EFFECTIVE_DATE")==null?"":res.get(0).get("EFFECTIVE_DATE").toString());
				bean.setCoreAppCode(res.get(0).get("RSACODE")==null?"":res.get(0).get("RSACODE").toString());
				bean.setStatus(res.get(0).get("STATUS")==null?"":res.get(0).get("STATUS").toString());
				bean.setRemarks(res.get(0).get("REMARKS")==null?"":res.get(0).get("REMARKS").toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int updateExchangeData(MotorAdminBeanNew bean) {
		return dao.updateExchangeData(bean);
	}

	public int checkExist(MotorAdminBeanNew bean) {
		return dao.checkExist(bean);
	}
}
