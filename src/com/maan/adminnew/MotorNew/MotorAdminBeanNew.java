package com.maan.adminnew.MotorNew;

import java.util.List;
import java.util.Map;

public class MotorAdminBeanNew {
	private String PolicyTypeId,QuarterId,StartRange,EndRange,Discount,SerialQuarter ;
	private String PolicyType;
	private String mode;
	private String reqFrom;
	private String policyType;
	private String vehicleType;
	private String bodyType;
	private String subRateId;
	private String rateId;
	private String seatingTonStart;
	private String seatingTonEnd;
	private String CCGVWStartRange;
	private String CCGVWEndRange;
	private String UAELiscenceStart;
	private String UAELiscenceEnd;
	private String driverAgeFrom;
	private String driverAgeTo;
	private String suminsuredStart;
	private String suminsuredEnd;
	private String policyStartDate;
	private String policyEndDate;
	private String motorRate;
	private String minimumPremium;
	private String onlinePercent;
	private String deductionPercent;
	private String deductionAmount;
	private String entryDate;
	private String effectiveDate;
	private String status;
	private String remarks;
	private String vehicleStartAge;
	private String vehicleEndAge;
	private String applicableCountry;
	private String applicableCity;
	private String claimStart;
	private String claimEnd;
	private String description;
	private String deductionDesc;
	private String makeId;
	private String modelId;
	private String makeName;
	private String makeNameArabic;
	private String modelName;
	private String modelNameArabic;
	private String expiryDate;
	private String referralStatus;
	private String mandatoryStatus;
	private String documentId;
	private String documentDescription;
	private String opCoverId;
	private String opCoverDesc;
	private String coreAppCode;
	private String bodyTypeName;
	private String bodyTypeId;
	private String vehiclebodyTypeId;
	private String vehicleIdforBodyType;
	private int count;
	private int coreappcodecount;
	private String thirdPartyId;
	private String seatingCylinderStart;
	private String seatingCylinderEnd;
	private String thirdPartyRate;
	private String locationId;
	private String areaCoverId;
	private String areaCoverDescriptionEnglish;
	private String areaCoverDescriptionArabic;
	private String deductibleId;
	private String vehicleName;
	private String bodyName;
	private String seatingStart;
	private String seatingEnd;
	private String deductibleStart;
	private String deductibleEnd;
	private String deductibleRate;
	private String discountAmount;
	private String vtypeId;
	private String vehicletypeDesc;
	private String coreappCode;
	private String policyTyeId;
	private String policyTypeDescEng;
	private String policyTypeDesArabic;
	private String policySubTypeId;
	private String constantMasterId;
	private String constItemType;
	private String ncbYear;
	private String ncbRate;
	private String ncbId;
	private String bankNameEnglish;
	private String bankNameArabic;
	private String groupId;
	private String groupDescEng;
	private String displayOrder;
	private String bankId;
	private String bankCode;
	private String bankEngName;
	private String bankArbName;
	private String policyTypeDesc;
	private String countryId;
	private String countryCode;
	private String countryName;
	private String nationalityName;
	private String areaCoverageId;
	private String areaCoverageCode;
	private String areaCoverageDescriptionEnglish;
	private String areaCoverageDescriptionArabic;
	private String isDeletable;
	private String isCalcType;
	private String isAddon;
	private String isSelected;
	private String opDesc;
	private String opCoverRate;
	private String paymentYN;
	private String vehicleCount;
	private String quarterId;
	private String documentApplicable;
	private String[] docpolicyType;
	private String docPolicy;
	private String itemId;
	private String itemCode;
	private String itemType;
	private String itemValue;
	private String itemDesc;
	private String param1;
	private String param2;
	private String item;
	private String modelTypeId;
	private String opCoverAmount;
	private String dataModified;
	private String addressA;
	private String addressB;
	private String telephoneNo;
	private String userNameLogin;
	private String opCoverSubId;
	private String startDate;
	private String endDate;
	private String refNo;
	private String custName;
	private String policyNo;
	private String docType;
	private String deviceManuf;
	private String uploadDate;
	private String count1;
	private String fileName;
	private String filePath;
	private String notifyId;
	private String notifyDesc;
	
	private String paymentType;
	private String accHolder;
	private String accNumber;
	private String bankBranch;
	private String currencyType;
	private String swiftCode;
	private String paymentBankId;
	private String sno;
	private String conditionDesc;
	private String conditionType;
	
	private List<String> descriptionList;
	private List<Map<String,Object>> policyTypeList;
	private List<Map<String,Object>> vehicleTypeList;
	private List<Map<String,Object>> bodyTypeList;
	private List<Map<String,Object>> transList;
	private List<Map<String,Object>> applicableCountryList;
	private List<Map<String,Object>> applicableCityList;
	private List<Map<String,Object>> subPolicyList;
	private List<Map<String,Object>> comprehensiveRateList;
	private List<Map<String,Object>> validationList;
	private List<Map<String,Object>> modalHeaderList;
	private List<Map<String,Object>> makeList;
	private List<Map<String,Object>> modelList;
	private List<Map<String,Object>> documentList;
	private List<Map<String,Object>> bankFinanceList;
	private List<Map<String,Object>> bodyTypeMasterList;
	private List<Map<String,Object>> vehicleLinkforBodyTypeMaster;
	private List<Map<String,Object>> vehicleListforBodyTypeMaster;
	private List<Map<String,Object>> policyTypeCheckBox;
	private List<Map<String,Object>> areaCoverList;
	private List<Map<String,Object>> deductibleList;
	private List<Map<String,Object>> areaCoverageList;
	private List<Map<String,Object>> thirdPartyList;
	private List<Map<String,Object>> constantList;
	private List<Map<String,Object>> groupList;
	private List<Map<String,Object>> opCoverList;	
	private List<Map<String,Object>> bankMasterList;	
	private List<Map<String,Object>> mfgCountryList;
	private List<Map<String, Object>> quarterList;
	private List<Map<String,Object>> opCoverDetailList;	
	private List<Map<String,Object>> documentdDrpdwn;
	private List<Map<String,Object>> itemTypeList;
	private List<Map<String,Object>> userNameLoginList;
	private List<Map<String,Object>> moUploadImgList;
	private List<Map<String,Object>> moUploadImgListView;
	private List<Map<String,Object>> notifyList;
	private List<Map<String,Object>> paymentBankList;
	private List<Map<String,Object>> paymentTypeList;

	
	public int getCoreappcodecount() {
		return coreappcodecount;
	}
	public void setCoreappcodecount(int coreappcodecount) {
		this.coreappcodecount = coreappcodecount;
	}
	public String getVehiclebodyTypeId() {
		return vehiclebodyTypeId;
	}
	public void setVehiclebodyTypeId(String vehiclebodyTypeId) {
		this.vehiclebodyTypeId = vehiclebodyTypeId;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public List<Map<String, Object>> getVehicleListforBodyTypeMaster() {
		return vehicleListforBodyTypeMaster;
	}
	public void setVehicleListforBodyTypeMaster(
			List<Map<String, Object>> vehicleListforBodyTypeMaster) {
		this.vehicleListforBodyTypeMaster = vehicleListforBodyTypeMaster;
	}
	public String getVehicleIdforBodyType() {
		return vehicleIdforBodyType;
	}
	public void setVehicleIdforBodyType(String vehicleIdforBodyType) {
		this.vehicleIdforBodyType = vehicleIdforBodyType;
	}
	public String getBodyTypeId() {
		return bodyTypeId;
	}
	public void setBodyTypeId(String bodyTypeId) {
		this.bodyTypeId = bodyTypeId;
	}
	public String getBodyTypeName() {
		return bodyTypeName;
	}
	public void setBodyTypeName(String bodyTypeName) {
		this.bodyTypeName = bodyTypeName;
	}
	public String getMode() {
		//System.out.println(mode);
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	public String getReqFrom() {
		return reqFrom;
	}
	public void setReqFrom(String reqFrom) {
		this.reqFrom = reqFrom;
	}
	public String getPolicyType() {
		return policyType;
	}
	public void setPolicyType(String policyType) {
		this.policyType = policyType;
	}
	public String getVehicleType() {
		return vehicleType;
	}
	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}
	public String getBodyType() {
		return bodyType;
	}
	public void setBodyType(String bodyType) {
		this.bodyType = bodyType;
	}
	public String getSubRateId() {
		return subRateId;
	}
	public void setSubRateId(String subRateId) {
		this.subRateId = subRateId;
	}
	public String getRateId() {
		return rateId;
	}
	public void setRateId(String rateId) {
		this.rateId = rateId;
	}
	public String getSeatingTonStart() {
		return seatingTonStart;
	}
	public void setSeatingTonStart(String seatingTonStart) {
		this.seatingTonStart = seatingTonStart;
	}
	public String getSeatingTonEnd() {
		return seatingTonEnd;
	}
	public void setSeatingTonEnd(String seatingTonEnd) {
		this.seatingTonEnd = seatingTonEnd;
	}
	public String getCCGVWStartRange() {
		return CCGVWStartRange;
	}
	public void setCCGVWStartRange(String cCGVWStartRange) {
		CCGVWStartRange = cCGVWStartRange;
	}
	public String getCCGVWEndRange() {
		return CCGVWEndRange;
	}
	public void setCCGVWEndRange(String cCGVWEndRange) {
		CCGVWEndRange = cCGVWEndRange;
	}
	public String getUAELiscenceStart() {
		return UAELiscenceStart;
	}
	public void setUAELiscenceStart(String uAELiscenceStart) {
		UAELiscenceStart = uAELiscenceStart;
	}
	public String getUAELiscenceEnd() {
		return UAELiscenceEnd;
	}
	public void setUAELiscenceEnd(String uAELiscenceEnd) {
		UAELiscenceEnd = uAELiscenceEnd;
	}
	public String getDriverAgeFrom() {
		return driverAgeFrom;
	}
	public void setDriverAgeFrom(String driverAgeFrom) {
		this.driverAgeFrom = driverAgeFrom;
	}
	public String getDriverAgeTo() {
		return driverAgeTo;
	}
	public void setDriverAgeTo(String driverAgeTo) {
		this.driverAgeTo = driverAgeTo;
	}
	public String getSuminsuredStart() {
		return suminsuredStart;
	}
	public void setSuminsuredStart(String suminsuredStart) {
		this.suminsuredStart = suminsuredStart;
	}
	public String getSuminsuredEnd() {
		return suminsuredEnd;
	}
	public void setSuminsuredEnd(String suminsuredEnd) {
		this.suminsuredEnd = suminsuredEnd;
	}
	public String getPolicyStartDate() {
		return policyStartDate;
	}
	public void setPolicyStartDate(String policyStartDate) {
		this.policyStartDate = policyStartDate;
	}
	public String getPolicyEndDate() {
		return policyEndDate;
	}
	public void setPolicyEndDate(String policyEndDate) {
		this.policyEndDate = policyEndDate;
	}
	public String getMotorRate() {
		return motorRate;
	}
	public void setMotorRate(String motorRate) {
		this.motorRate = motorRate;
	}
	public String getMinimumPremium() {
		return minimumPremium;
	}
	public void setMinimumPremium(String minimumPremium) {
		this.minimumPremium = minimumPremium;
	}
	public String getOnlinePercent() {
		return onlinePercent;
	}
	public void setOnlinePercent(String onlinePercent) {
		this.onlinePercent = onlinePercent;
	}
	public String getDeductionPercent() {
		return deductionPercent;
	}
	public void setDeductionPercent(String deductionPercent) {
		this.deductionPercent = deductionPercent;
	}
	public String getDeductionAmount() {
		return deductionAmount;
	}
	public void setDeductionAmount(String deductionAmount) {
		this.deductionAmount = deductionAmount;
	}
	public String getEntryDate() {
		return entryDate;
	}
	public void setEntryDate(String entryDate) {
		this.entryDate = entryDate;
	}
	public String getEffectiveDate() {
		return effectiveDate;
	}
	public void setEffectiveDate(String effectiveDate) {
		this.effectiveDate = effectiveDate;
	}
	public String getStatus()
	{
		//System.out.println("status  "+status);
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public List<Map<String, Object>> getBodyTypeMasterList() {
		return bodyTypeMasterList;
	}
	public void setBodyTypeMasterList(List<Map<String, Object>> bodyTypeMasterList) {
		this.bodyTypeMasterList = bodyTypeMasterList;
	}
	public List<Map<String, Object>> getPolicyTypeList() {
		return policyTypeList;
	}
	public void setPolicyTypeList(List<Map<String, Object>> policyTypeList) {
		this.policyTypeList = policyTypeList;
	}
	public List<Map<String, Object>> getVehicleTypeList() {
		return vehicleTypeList;
	}
	public void setVehicleTypeList(List<Map<String, Object>> vehicleTypeList) {
		this.vehicleTypeList = vehicleTypeList;
	}
	public List<Map<String, Object>> getBodyTypeList() {
		return bodyTypeList;
	}
	public void setBodyTypeList(List<Map<String, Object>> bodyTypeList) {
		this.bodyTypeList = bodyTypeList;
	}
	public List<Map<String, Object>> getTransList() {
		return transList;
	}
	public void setTransList(List<Map<String, Object>> transList) {
		this.transList = transList;
	}
	public List<Map<String, Object>> getApplicableCountryList() {
		return applicableCountryList;
	}
	public void setApplicableCountryList(
			List<Map<String, Object>> applicableCountryList) {
		this.applicableCountryList = applicableCountryList;
	}
	public List<Map<String, Object>> getApplicableCityList() {
		return applicableCityList;
	}
	public void setApplicableCityList(List<Map<String, Object>> applicableCityList) {
		this.applicableCityList = applicableCityList;
	}
	public List<Map<String, Object>> getSubPolicyList() {
		return subPolicyList;
	}
	public void setSubPolicyList(List<Map<String, Object>> subPolicyList) {
		this.subPolicyList = subPolicyList;
	}
	public List<Map<String, Object>> getComprehensiveRateList() {
		return comprehensiveRateList;
	}
	public void setComprehensiveRateList(
			List<Map<String, Object>> comprehensiveRateList) {
		this.comprehensiveRateList = comprehensiveRateList;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setValidationList(List<Map<String,Object>> validationList) {
		this.validationList = validationList;
	}
	public List<Map<String,Object>> getValidationList() {
		return validationList;
	}
	public void setVehicleStartAge(String vehicleStartAge) {
		this.vehicleStartAge = vehicleStartAge;
	}
	public String getVehicleStartAge() {
		return vehicleStartAge;
	}
	public void setVehicleEndAge(String vehicleEndAge) {
		this.vehicleEndAge = vehicleEndAge;
	}
	public String getVehicleEndAge() {
		return vehicleEndAge;
	}
	public void setModalHeaderList(List<Map<String,Object>> modalHeaderList) {
		this.modalHeaderList = modalHeaderList;
	}
	public List<Map<String,Object>> getModalHeaderList() {
		return modalHeaderList;
	}
	public void setApplicableCountry(String applicableCountry) {
		this.applicableCountry = applicableCountry;
	}
	public String getApplicableCountry() {
		return applicableCountry;
	}
	public void setApplicableCity(String applicableCity) {
		this.applicableCity = applicableCity;
	}
	public String getApplicableCity() {
		return applicableCity;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDescription() {
		return description;
	}
	public void setClaimStart(String claimStart) {
		this.claimStart = claimStart;
	}
	public String getClaimStart() {
		return claimStart;
	}
	public void setClaimEnd(String claimEnd) {
		this.claimEnd = claimEnd;
	}
	public String getClaimEnd() {
		return claimEnd;
	}
	public void setDeductionDesc(String deductionDesc) {
		this.deductionDesc = deductionDesc;
	}
	public String getDeductionDesc() {
		return deductionDesc;
	}
	public void setMakeList(List<Map<String,Object>> makeList) {
		this.makeList = makeList;
	}
	public List<Map<String,Object>> getMakeList() {
		return makeList;
	}
	public void setMakeId(String makeId) {
		this.makeId = makeId;
	}
	public String getMakeId() {
		return makeId;
	}
	public void setModelId(String modelId) {
		this.modelId = modelId;
	}
	public String getModelId() {
		return modelId;
	}
	
	
	public List<Map<String, Object>> getVehicleLinkforBodyTypeMaster() {
		return vehicleLinkforBodyTypeMaster;
	}
	public void setVehicleLinkforBodyTypeMaster(
			List<Map<String, Object>> vehicleLinkforBodyTypeMaster) {
		this.vehicleLinkforBodyTypeMaster = vehicleLinkforBodyTypeMaster;
	}
	public void setModelList(List<Map<String,Object>> modelList) {
		this.modelList = modelList;
	}
	public List<Map<String,Object>> getModelList() {
		return modelList;
	}
	public void setOpCoverList(List<Map<String,Object>> opCoverList) {
		this.opCoverList = opCoverList;
	}
	public List<Map<String,Object>> getOpCoverList() {
		return opCoverList;
	}
	public void setMakeName(String makeName) {
		this.makeName = makeName;
	}
	public String getMakeName() {
		return makeName;
	}
	public void setMakeNameArabic(String makeNameArabic) {
		this.makeNameArabic = makeNameArabic;
	}
	public String getMakeNameArabic() {
		return makeNameArabic;
	}
	public void setModelName(String modelName) {
		this.modelName = modelName;
	}
	public String getModelName() {
		return modelName;
	}
	public void setModelNameArabic(String modelNameArabic) {
		this.modelNameArabic = modelNameArabic;
	}
	public String getModelNameArabic() {
		return modelNameArabic;
	}
	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}
	public String getExpiryDate() {
		return expiryDate;
	}
	public void setReferralStatus(String referralStatus) {
		this.referralStatus = referralStatus;
	}
	public String getReferralStatus() {
		return referralStatus;
	}
	public void setDocumentList(List<Map<String,Object>> documentList) {
		this.documentList = documentList;
	}
	public List<Map<String,Object>> getDocumentList() {
		return documentList;
	}
	
	public void setDocumentId(String documentId) {
		this.documentId = documentId;
	}
	public String getDocumentId() {
		return documentId;
	}
	public void setDocumentDescription(String documentDescription) {
		this.documentDescription = documentDescription;
	}
	public String getDocumentDescription() {
		return documentDescription;
	}
	public void setPolicyTypeCheckBox(List<Map<String,Object>> policyTypeCheckBox) {
		this.policyTypeCheckBox = policyTypeCheckBox;
	}
	public List<Map<String,Object>> getPolicyTypeCheckBox() {
		return policyTypeCheckBox;
	}
	public void setBankMasterList(List<Map<String,Object>> bankMasterList) {
		this.bankMasterList = bankMasterList;
	}
	public List<Map<String,Object>> getBankMasterList() {
		return bankMasterList;
	}
	public void setMfgCountryList(List<Map<String,Object>> mfgCountryList) {
		this.mfgCountryList = mfgCountryList;
	}
	public List<Map<String,Object>> getMfgCountryList() {
		return mfgCountryList;
	}
	public String getMandatoryStatus() {
		return mandatoryStatus;
	}
	public void setMandatoryStatus(String mandatoryStatus) {
		this.mandatoryStatus = mandatoryStatus;
	}
	public String getOpCoverId() {
		return opCoverId;
	}
	public void setOpCoverId(String opCoverId) {
		this.opCoverId = opCoverId;
	}
	public String getOpCoverDesc() {
		return opCoverDesc;
	}
	public void setOpCoverDesc(String opCoverDesc) {
		this.opCoverDesc = opCoverDesc;
	}
	public void setThirdPartyList(List<Map<String,Object>> thirdPartyList) {
		this.thirdPartyList = thirdPartyList;
	}
	public List<Map<String,Object>> getThirdPartyList() {
		return thirdPartyList;
	}
	public void setThirdPartyId(String thirdPartyId) {
		this.thirdPartyId = thirdPartyId;
	}
	public String getThirdPartyId() {
		return thirdPartyId;
	}
	public void setThirdPartyRate(String thirdPartyRate) {
		this.thirdPartyRate = thirdPartyRate;
	}
	public String getThirdPartyRate() {
		return thirdPartyRate;
	}
	public void setCoreAppCode(String coreAppCode) {
		this.coreAppCode = coreAppCode;
	}
	public String getCoreAppCode() {
		return coreAppCode;
	}
	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}
	public String getLocationId() {
		return locationId;
	}
	public void setSeatingCylinderStart(String seatingCylinderStart) {
		this.seatingCylinderStart = seatingCylinderStart;
	}
	public String getSeatingCylinderStart() {
		return seatingCylinderStart;
	}
	public void setSeatingCylinderEnd(String seatingCylinderEnd) {
		this.seatingCylinderEnd = seatingCylinderEnd;
	}
	public String getSeatingCylinderEnd() {
		return seatingCylinderEnd;
	}
	public void setAreaCoverId(String areaCoverId) {
		this.areaCoverId = areaCoverId;
	}
	public String getAreaCoverId() {
		return areaCoverId;
	}
	public void setAreaCoverDescriptionEnglish(
			String areaCoverDescriptionEnglish) {
		this.areaCoverDescriptionEnglish = areaCoverDescriptionEnglish;
	}
	public String getAreaCoverDescriptionEnglish() {
		return areaCoverDescriptionEnglish;
	}
	public void setAreaCoverDescriptionArabic(String areaCoverDescriptionArabic) {
		this.areaCoverDescriptionArabic = areaCoverDescriptionArabic;
	}
	public String getAreaCoverDescriptionArabic() {
		return areaCoverDescriptionArabic;
	}
	public void setAreaCoverList(List<Map<String,Object>> areaCoverList) {
		this.areaCoverList = areaCoverList;
	}
	public List<Map<String,Object>> getAreaCoverList() {
		return areaCoverList;
	}
	public void setDeductibleId(String deductibleId) {
		this.deductibleId = deductibleId;
	}
	public String getDeductibleId() {
		return deductibleId;
	}
	public void setDeductibleStart(String deductibleStart) {
		this.deductibleStart = deductibleStart;
	}
	public String getDeductibleStart() {
		return deductibleStart;
	}
	public void setDeductibleEnd(String deductibleEnd) {
		this.deductibleEnd = deductibleEnd;
	}
	public String getDeductibleEnd() {
		return deductibleEnd;
	}
	public void setDiscountAmount(String discountAmount) {
		this.discountAmount = discountAmount;
	}
	public String getDiscountAmount() {
		return discountAmount;
	}
	public void setDeductibleList(List<Map<String,Object>> deductibleList) {
		this.deductibleList = deductibleList;
	}
	public List<Map<String,Object>> getDeductibleList() {
		return deductibleList;
	}
	public void setDeductibleRate(String deductibleRate) {
		this.deductibleRate = deductibleRate;
	}
	public String getDeductibleRate() {
		return deductibleRate;
	}
	public void setCoreappCode(String coreappCode) {
		this.coreappCode = coreappCode;
	}
	public String getCoreappCode() {
		return coreappCode;
	}
	public void setVehicletypeDesc(String vehicletypeDesc) {
		this.vehicletypeDesc = vehicletypeDesc;
	}
	public String getVehicletypeDesc() {
		return vehicletypeDesc;
	}
	public void setVtypeId(String vtypeId) {
		this.vtypeId = vtypeId;
	}
	public String getVtypeId() {
		return vtypeId;
	}
	public void setPolicyTyeId(String policyTyeId) {
		this.policyTyeId = policyTyeId;
	}
	public String getPolicyTyeId() {
		return policyTyeId;
	}
	public void setPolicyTypeDescEng(String policyTypeDescEng) {
		this.policyTypeDescEng = policyTypeDescEng;
	}
	public String getPolicyTypeDescEng() {
		return policyTypeDescEng;
	}
	public void setPolicyTypeDesArabic(String policyTypeDesArabic) {
		this.policyTypeDesArabic = policyTypeDesArabic;
	}
	public String getPolicyTypeDesArabic() {
		return policyTypeDesArabic;
	}
	public void setPolicySubTypeId(String policySubTypeId) {
		this.policySubTypeId = policySubTypeId;
	}
	public String getPolicySubTypeId() {
		return policySubTypeId;
	}
	public void setConstantList(List<Map<String,Object>> constantList) {
		this.constantList = constantList;
	}
	public List<Map<String,Object>> getConstantList() {
		return constantList;
	}
	public void setConstantMasterId(String constantMasterId) {
		this.constantMasterId = constantMasterId;
	}
	public String getConstantMasterId() {
		return constantMasterId;
	}
	public void setConstItemType(String constItemType) {
		this.constItemType = constItemType;
	}
	public String getConstItemType() {
		return constItemType;
	}
	public void setNcbYear(String ncbYear) {
		this.ncbYear = ncbYear;
	}
	public String getNcbYear() {
		return ncbYear;
	}
	public void setNcbRate(String ncbRate) {
		this.ncbRate = ncbRate;
	}
	public String getNcbRate() {
		return ncbRate;
	}
	public void setNcbId(String ncbId) {
		this.ncbId = ncbId;
	}
	public String getNcbId() {
		return ncbId;
	}
	public void setBankNameEnglish(String bankNameEnglish) {
		this.bankNameEnglish = bankNameEnglish;
	}
	public String getBankNameEnglish() {
		return bankNameEnglish;
	}
	public void setBankNameArabic(String bankNameArabic) {
		this.bankNameArabic = bankNameArabic;
	}
	public String getBankNameArabic() {
		return bankNameArabic;
	}
	public void setBankFinanceList(List<Map<String,Object>> bankFinanceList) {
		this.bankFinanceList = bankFinanceList;
	}
	public List<Map<String,Object>> getBankFinanceList() {
		return bankFinanceList;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	public String getGroupId() {
		return groupId;
	}
	public String getGroupDescEng() {
		return groupDescEng;
	}
	public void setGroupDescEng(String groupDescEng) {
		this.groupDescEng = groupDescEng;
	}
	public String getDisplayOrder() {
		return displayOrder;
	}
	public void setDisplayOrder(String displayOrder) {
		this.displayOrder = displayOrder;
	}
	public List<Map<String, Object>> getGroupList() {
		return groupList;
	}
	public void setGroupList(List<Map<String, Object>> groupList) {
		this.groupList = groupList;
	}
	public String getBankId() {
		return bankId;
	}
	public void setBankId(String bankId) {
		this.bankId = bankId;
	}
	public String getBankCode() {
		return bankCode;
	}
	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}
	public String getBankEngName() {
		return bankEngName;
	}
	public void setBankEngName(String bankEngName) {
		this.bankEngName = bankEngName;
	}
	public String getBankArbName() {
		return bankArbName;
	}
	public void setBankArbName(String bankArbName) {
		this.bankArbName = bankArbName;
	}
	public void setPolicyTypeDesc(String policyTypeDesc) {
		this.policyTypeDesc = policyTypeDesc;
	}
	public String getPolicyTypeDesc() {
		return policyTypeDesc;
	}
	public String getCountryId() {
		return countryId;
	}
	public void setCountryId(String countryId) {
		this.countryId = countryId;
	}
	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	public String getCountryName() {
		return countryName;
	}
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	public String getNationalityName() {
		return nationalityName;
	}
	public void setNationalityName(String nationalityName) {
		this.nationalityName = nationalityName;
	}
	public void setAreaCoverageId(String areaCoverageId) {
		this.areaCoverageId = areaCoverageId;
	}
	public String getAreaCoverageId() {
		return areaCoverageId;
	}
	public void setAreaCoverageCode(String areaCoverageCode) {
		this.areaCoverageCode = areaCoverageCode;
	}
	public String getAreaCoverageCode() {
		return areaCoverageCode;
	}
	public void setAreaCoverageDescriptionEnglish(
			String areaCoverageDescriptionEnglish) {
		this.areaCoverageDescriptionEnglish = areaCoverageDescriptionEnglish;
	}
	public String getAreaCoverageDescriptionEnglish() {
		return areaCoverageDescriptionEnglish;
	}
	public void setAreaCoverageDescriptionArabic(
			String areaCoverageDescriptionArabic) {
		this.areaCoverageDescriptionArabic = areaCoverageDescriptionArabic;
	}
	public String getAreaCoverageDescriptionArabic() {
		return areaCoverageDescriptionArabic;
	}
	public void setAreaCoverageList(List<Map<String,Object>> areaCoverageList) {
		this.areaCoverageList = areaCoverageList;
	}
	public List<Map<String,Object>> getAreaCoverageList() {
		return areaCoverageList;
	}
	public String getIsDeletable() {
		return isDeletable;
	}
	public void setIsDeletable(String isDeletable) {
		this.isDeletable = isDeletable;
	}
	public String getIsCalcType() {
		return isCalcType;
	}
	public void setIsCalcType(String isCalcType) {
		this.isCalcType = isCalcType;
	}
	public String getIsAddon() {
		return isAddon;
	}
	public void setIsAddon(String isAddon) {
		this.isAddon = isAddon;
	}
	public String getIsSelected() {
		return isSelected;
	}
	public void setIsSelected(String isSelected) {
		this.isSelected = isSelected;
	}
	public String getOpDesc() {
		return opDesc;
	}
	public void setOpDesc(String opDesc) {
		this.opDesc = opDesc;
	}
	public String getOpCoverRate() {
		return opCoverRate;
	}
	public void setOpCoverRate(String opCoverRate) {
		this.opCoverRate = opCoverRate;
	}
	public void setPaymentYN(String paymentYN) {
		this.paymentYN = paymentYN;
	}
	public String getPaymentYN() {
		return paymentYN;
	}
	public void setVehicleCount(String vehicleCount) {
		this.vehicleCount = vehicleCount;
	}
	public String getVehicleCount() {
		return vehicleCount;
	}
	public void setDescriptionList(List<String> descriptionList) {
		this.descriptionList = descriptionList;
	}
	public List<String> getDescriptionList() {
		return descriptionList;
	}
	public void setOpCoverDetailList(List<Map<String,Object>> opCoverDetailList) {
		this.opCoverDetailList = opCoverDetailList;
	}
	public List<Map<String,Object>> getOpCoverDetailList() {
		return opCoverDetailList;
	}
	public void setQuarterId(String quarterId) {
		this.quarterId = quarterId;
	}
	public String getQuarterId() {
		return quarterId;
	}

	public void setQuarterList(List<Map<String,Object>> quarterList) {
		this.quarterList=quarterList;
		
	}
	public  List<Map<String,Object>>getQuarterList() {
		return quarterList;
		
	}
	public String getPolicyTypeId() {
		return PolicyTypeId;
	}
	public void setPolicyTypeId(String policyTypeId) {
		PolicyTypeId = policyTypeId;
	}
	public String getStartRange() {
		return StartRange;
	}
	public void setStartRange(String startRange) {
		StartRange = startRange;
	}
	public String getDiscount() {
		return Discount;
	}
	public void setDiscount(String discount) {
		Discount = discount;
	}
	public void setEndRange(String endRange) {
		EndRange = endRange;
	}
	public String getEndRange() {
		return EndRange;
	}

	public String getSerialQuarter() {
		return SerialQuarter;
	}
	
	public void setSerialQuarter(String serialQuarter) {
		SerialQuarter = serialQuarter;
	}
	public void setDocumentdDrpdwn(List<Map<String,Object>> documentdDrpdwn) {
		this.documentdDrpdwn = documentdDrpdwn;
	}
	public List<Map<String,Object>> getDocumentdDrpdwn() {
		return documentdDrpdwn;
	}
	public void setDocumentApplicable(String documentApplicable) {
		this.documentApplicable = documentApplicable;
	}
	public String getDocumentApplicable() {
		return documentApplicable;
	}
	
	public void setItemTypeList(List<Map<String,Object>> itemTypeList) {
		this.itemTypeList = itemTypeList;
	}
	public List<Map<String,Object>> getItemTypeList() {
		return itemTypeList;
	}
	public String getItemId() {
		return itemId;
	}
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	public String getItemCode() {
		return itemCode;
	}
	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}
	public String getItemType() {
		return itemType;
	}
	public void setItemType(String itemType) {
		this.itemType = itemType;
	}
	public String getItemValue() {
		return itemValue;
	}
	public void setItemValue(String itemValue) {
		this.itemValue = itemValue;
	}
	public String getItemDesc() {
		return itemDesc;
	}
	public void setItemDesc(String itemDesc) {
		this.itemDesc = itemDesc;
	}
	public String getParam1() {
		return param1;
	}
	public void setParam1(String param1) {
		this.param1 = param1;
	}
	public String getParam2() {
		return param2;
	}
	public void setParam2(String param2) {
		this.param2 = param2;
	}
	public void setItem(String item) {
		this.item = item;
	}
	public String getItem() {
		return item;
	}
	
	public void setDocpolicyType(String[] docpolicyType) {
		this.docpolicyType = docpolicyType;
	}
	public String[] getDocpolicyType() {
		return docpolicyType;
	}
	public void setVehicleName(String vehicleName) {
		this.vehicleName = vehicleName;
	}
	public String getVehicleName() {
		return vehicleName;
	}
	public void setBodyName(String bodyName) {
		this.bodyName = bodyName;
	}
	public String getBodyName() {
		return bodyName;
	}
	public void setSeatingEnd(String seatingEnd) {
		this.seatingEnd = seatingEnd;
	}
	public String getSeatingEnd() {
		return seatingEnd;
	}
	public void setSeatingStart(String seatingStart) {
		this.seatingStart = seatingStart;
	}
	public String getSeatingStart() {
		return seatingStart;
	}
	public void setModelTypeId(String modelTypeId) {
		this.modelTypeId = modelTypeId;
	}
	public String getModelTypeId() {
		return modelTypeId;
	}
	public void setOpCoverAmount(String opCoverAmount) {
		this.opCoverAmount = opCoverAmount;
	}
	public String getOpCoverAmount() {
		return opCoverAmount;
	}
	public void setDataModified(String dataModified) {
		this.dataModified = dataModified;
	}
	public String getDataModified() {
		return dataModified;
	}
	public void setAddressA(String addressA) {
		this.addressA = addressA;
	}
	public String getAddressA() {
		return addressA;
	}
	public void setAddressB(String addressB) {
		this.addressB = addressB;
	}
	public String getAddressB() {
		return addressB;
	}
	public void setTelephoneNo(String telephoneNo) {
		this.telephoneNo = telephoneNo;
	}
	public String getTelephoneNo() {
		return telephoneNo;
	}
	public void setDocPolicy(String docPolicy) {
		this.docPolicy = docPolicy;
	}
	public String getDocPolicy() {
		return docPolicy;
	}
	public void setUserNameLogin(String userNameLogin) {
		this.userNameLogin = userNameLogin;
	}
	public String getUserNameLogin() {
		return userNameLogin;
	}
	public void setUserNameLoginList(List<Map<String,Object>> userNameLoginList) {
		this.userNameLoginList = userNameLoginList;
	}
	public List<Map<String,Object>> getUserNameLoginList() {
		return userNameLoginList;
	}
	public void setOpCoverSubId(String opCoverSubId) {
		this.opCoverSubId = opCoverSubId;
	}
	public String getOpCoverSubId() {
		return opCoverSubId;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getEndDate() {
		return endDate;
	}
	/**
	 * @param moUploadImgList the moUploadImgList to set
	 */
	public void setMoUploadImgList(List<Map<String,Object>> moUploadImgList) {
		this.moUploadImgList = moUploadImgList;
	}
	/**
	 * @return the moUploadImgList
	 */
	public List<Map<String,Object>> getMoUploadImgList() {
		return moUploadImgList;
	}
	/**
	 * @param refNo the refNo to set
	 */
	public void setRefNo(String refNo) {
		this.refNo = refNo;
	}
	/**
	 * @return the refNo
	 */
	public String getRefNo() {
		return refNo;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public String getPolicyNo() {
		return policyNo;
	}
	public void setPolicyNo(String policyNo) {
		this.policyNo = policyNo;
	}
	public String getDocType() {
		return docType;
	}
	public void setDocType(String docType) {
		this.docType = docType;
	}
	public String getDeviceManuf() {
		return deviceManuf;
	}
	public void setDeviceManuf(String deviceManuf) {
		this.deviceManuf = deviceManuf;
	}
	public String getUploadDate() {
		return uploadDate;
	}
	public void setUploadDate(String uploadDate) {
		this.uploadDate = uploadDate;
	}
	public void setCount1(String count1) {
		this.count1 = count1;
	}
	public String getCount1() {
		return count1;
	}
	public void setMoUploadImgListView(List<Map<String,Object>> moUploadImgListView) {
		this.moUploadImgListView = moUploadImgListView;
	}
	public List<Map<String,Object>> getMoUploadImgListView() {
		return moUploadImgListView;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setNotifyList(List<Map<String,Object>> notifyList) {
		this.notifyList = notifyList;
	}
	public List<Map<String,Object>> getNotifyList() {
		return notifyList;
	}
	public void setNotifyId(String notifyId) {
		this.notifyId = notifyId;
	}
	public String getNotifyId() {
		return notifyId;
	}
	public void setNotifyDesc(String notifyDesc) {
		this.notifyDesc = notifyDesc;
	}
	public String getNotifyDesc() {
		return notifyDesc;
	}
	/**
	 * @param paymentBankList the paymentBankList to set
	 */
	public void setPaymentBankList(List<Map<String,Object>> paymentBankList) {
		this.paymentBankList = paymentBankList;
	}
	/**
	 * @return the paymentBankList
	 */
	public List<Map<String,Object>> getPaymentBankList() {
		return paymentBankList;
	}
	public String getPaymentType() {
		return paymentType;
	}
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}
	public String getAccHolder() {
		return accHolder;
	}
	public void setAccHolder(String accHolder) {
		this.accHolder = accHolder;
	}
	public String getAccNumber() {
		return accNumber;
	}
	public void setAccNumber(String accNumber) {
		this.accNumber = accNumber;
	}
	public String getBankBranch() {
		return bankBranch;
	}
	public void setBankBranch(String bankBranch) {
		this.bankBranch = bankBranch;
	}
	public String getCurrencyType() {
		return currencyType;
	}
	public void setCurrencyType(String currencyType) {
		this.currencyType = currencyType;
	}
	public String getSwiftCode() {
		return swiftCode;
	}
	public void setSwiftCode(String swiftCode) {
		this.swiftCode = swiftCode;
	}
	public String getPaymentBankId() {
		return paymentBankId;
	}
	public void setPaymentBankId(String paymentBankId) {
		this.paymentBankId = paymentBankId;
	}
	/**
	 * @param paymentTypeList the paymentTypeList to set
	 */
	public void setPaymentTypeList(List<Map<String,Object>> paymentTypeList) {
		this.paymentTypeList = paymentTypeList;
	}
	/**
	 * @return the paymentTypeList
	 */
	public List<Map<String,Object>> getPaymentTypeList() {
		return paymentTypeList;
	}
	
	private String brokerId;
	private String branchCode;

	public String getBrokerId() {
		return brokerId;
	}
	public void setBrokerId(String brokerId) {
		this.brokerId = brokerId;
	}
	public String getBranchCode() {
		return branchCode;
	}
	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}
	
	private List<Map<String,Object>> motorRateList;
	private String factorRateId;
	private String agencyCode;
	private String factorId;
	private String rate;
	/*private String status;
	private String effectiveDate;
	private String entryDate;
	private String endDate;
	private String param1;
	private String param2;*/
	private String param3;
	private String param4;
	private String param5;
	private String param6;
	private String param7;
	private String param8;
	private String param9;
	private String param10;
	private String configType;
	//private String remarks;
	private String ratingType;
	private String divisionCode;
	private String customerCode;
	private String electrical;
	private String nonElectrical;
	private String vehusageId;
	private String bodyId;
	private List<Map<String,Object>> motorFactorList;
	
	public List<Map<String, Object>> getMotorRateList() {
		return motorRateList;
	}
	public void setMotorRateList(List<Map<String, Object>> motorRateList) {
		this.motorRateList = motorRateList;
	}
	public String getFactorRateId() {
		return factorRateId;
	}
	public void setFactorRateId(String factorRateId) {
		this.factorRateId = factorRateId;
	}
	public String getAgencyCode() {
		return agencyCode;
	}
	public void setAgencyCode(String agencyCode) {
		this.agencyCode = agencyCode;
	}
	public String getFactorId() {
		return factorId;
	}
	public void setFactorId(String factorId) {
		this.factorId = factorId;
	}
	public String getRate() {
		return rate;
	}
	public void setRate(String rate) {
		this.rate = rate;
	}
	public String getParam3() {
		return param3;
	}
	public void setParam3(String param3) {
		this.param3 = param3;
	}
	public String getParam4() {
		return param4;
	}
	public void setParam4(String param4) {
		this.param4 = param4;
	}
	public String getParam5() {
		return param5;
	}
	public void setParam5(String param5) {
		this.param5 = param5;
	}
	public String getParam6() {
		return param6;
	}
	public void setParam6(String param6) {
		this.param6 = param6;
	}
	public String getParam7() {
		return param7;
	}
	public void setParam7(String param7) {
		this.param7 = param7;
	}
	public String getParam8() {
		return param8;
	}
	public void setParam8(String param8) {
		this.param8 = param8;
	}
	public String getParam9() {
		return param9;
	}
	public void setParam9(String param9) {
		this.param9 = param9;
	}
	public String getParam10() {
		return param10;
	}
	public void setParam10(String param10) {
		this.param10 = param10;
	}
	public String getConfigType() {
		return configType;
	}
	public void setConfigType(String configType) {
		this.configType = configType;
	}
	public String getRatingType() {
		return ratingType;
	}
	public void setRatingType(String ratingType) {
		this.ratingType = ratingType;
	}
	public String getDivisionCode() {
		return divisionCode;
	}
	public void setDivisionCode(String divisionCode) {
		this.divisionCode = divisionCode;
	}
	public String getCustomerCode() {
		return customerCode;
	}
	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}
	public String getElectrical() {
		return electrical;
	}
	public void setElectrical(String electrical) {
		this.electrical = electrical;
	}
	public String getNonElectrical() {
		return nonElectrical;
	}
	public void setNonElectrical(String nonElectrical) {
		this.nonElectrical = nonElectrical;
	}
	public String getVehusageId() {
		return vehusageId;
	}
	public void setVehusageId(String vehusageId) {
		this.vehusageId = vehusageId;
	}
	public String getBodyId() {
		return bodyId;
	}
	public void setBodyId(String bodyId) {
		this.bodyId = bodyId;
	}
	public List<Map<String, Object>> getMotorFactorList() {
		return motorFactorList;
	}
	public void setMotorFactorList(List<Map<String, Object>> motorFactorList) {
		this.motorFactorList = motorFactorList;
	}
	
	private String rateEndDate;
	private String factorName;

	public String getRateEndDate() {
		return rateEndDate;
	}
	public void setRateEndDate(String rateEndDate) {
		this.rateEndDate = rateEndDate;
	}
	public String getFactorName() {
		return factorName;
	}
	public void setFactorName(String factorName) {
		this.factorName = factorName;
	}
	
	private String productId;
	//private String broker;
	private List<Map<String,Object>> productList;
	private List<Map<String,Object>> brokerList;
	private List<Map<String,Object>> factorDetailMasterList;
	private String tranID;
	private String noofRecords;
	private String validRecords;
	private String errorRecords;
	private String flag;
	private List<Map<String,Object>> factorRateList;
	private List<Map<String,Object>> factorRateMasterList;
	private String brokerNameDesc;
	private String progressStatus;
	private String progressMessage;
	private List<Map<String,Object>> factorDetails;
	private List<Map<String,Object>> vehicleUsageList;
	//private String vehUsage;
	private String vehUsageDesc;
	private String param11;
	private String param12;
	private String param13;
	private String param14;
	private String param15;
	private String downloadType;
	
	private String paramHead1;
	private String paramHead2;
	private String paramHead3;
	private String paramHead4;
	private String paramHead5;
	private String paramHead6;
	private String paramHead7;
	private String paramHead8;
	private String paramHead9;
	private String paramHead10;
	private String paramHead11;
	private String paramHead12;
	private String paramHead13;
	private String paramHead14;
	private String paramHead15;
	private String branchName;
	private List<Map<String,Object>> motorEditRateDtl;


	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	/*public String getBroker() {
		return broker;
	}
	public void setBroker(String broker) {
		this.broker = broker;
	}*/
	public List<Map<String, Object>> getProductList() {
		return productList;
	}
	public void setProductList(List<Map<String, Object>> productList) {
		this.productList = productList;
	}
	public List<Map<String, Object>> getBrokerList() {
		return brokerList;
	}
	public void setBrokerList(List<Map<String, Object>> brokerList) {
		this.brokerList = brokerList;
	}
	public List<Map<String, Object>> getFactorDetailMasterList() {
		return factorDetailMasterList;
	}
	public void setFactorDetailMasterList(List<Map<String, Object>> factorDetailMasterList) {
		this.factorDetailMasterList = factorDetailMasterList;
	}
	public String getTranID() {
		return tranID;
	}
	public void setTranID(String tranID) {
		this.tranID = tranID;
	}
	public String getNoofRecords() {
		return noofRecords;
	}
	public void setNoofRecords(String noofRecords) {
		this.noofRecords = noofRecords;
	}
	public String getValidRecords() {
		return validRecords;
	}
	public void setValidRecords(String validRecords) {
		this.validRecords = validRecords;
	}
	public String getErrorRecords() {
		return errorRecords;
	}
	public void setErrorRecords(String errorRecords) {
		this.errorRecords = errorRecords;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public List<Map<String, Object>> getFactorRateList() {
		return factorRateList;
	}
	public void setFactorRateList(List<Map<String, Object>> factorRateList) {
		this.factorRateList = factorRateList;
	}
	public List<Map<String, Object>> getFactorRateMasterList() {
		return factorRateMasterList;
	}
	public void setFactorRateMasterList(List<Map<String, Object>> factorRateMasterList) {
		this.factorRateMasterList = factorRateMasterList;
	}
	public String getBrokerNameDesc() {
		return brokerNameDesc;
	}
	public void setBrokerNameDesc(String brokerNameDesc) {
		this.brokerNameDesc = brokerNameDesc;
	}
	public String getProgressStatus() {
		return progressStatus;
	}
	public void setProgressStatus(String progressStatus) {
		this.progressStatus = progressStatus;
	}
	public String getProgressMessage() {
		return progressMessage;
	}
	public void setProgressMessage(String progressMessage) {
		this.progressMessage = progressMessage;
	}
	public List<Map<String, Object>> getFactorDetails() {
		return factorDetails;
	}
	public void setFactorDetails(List<Map<String, Object>> factorDetails) {
		this.factorDetails = factorDetails;
	}
	public List<Map<String, Object>> getVehicleUsageList() {
		return vehicleUsageList;
	}
	public void setVehicleUsageList(List<Map<String, Object>> vehicleUsageList) {
		this.vehicleUsageList = vehicleUsageList;
	}
	/*public String getVehUsage() {
		return vehUsage;
	}
	public void setVehUsage(String vehUsage) {
		this.vehUsage = vehUsage;
	}*/
	public String getVehUsageDesc() {
		return vehUsageDesc;
	}
	public void setVehUsageDesc(String vehUsageDesc) {
		this.vehUsageDesc = vehUsageDesc;
	}
	public String getParam11() {
		return param11;
	}
	public void setParam11(String param11) {
		this.param11 = param11;
	}
	public String getParam12() {
		return param12;
	}
	public void setParam12(String param12) {
		this.param12 = param12;
	}
	public String getParam13() {
		return param13;
	}
	public void setParam13(String param13) {
		this.param13 = param13;
	}
	public String getParam14() {
		return param14;
	}
	public void setParam14(String param14) {
		this.param14 = param14;
	}
	public String getParam15() {
		return param15;
	}
	public void setParam15(String param15) {
		this.param15 = param15;
	}
	public String getDownloadType() {
		return downloadType;
	}
	public void setDownloadType(String downloadType) {
		this.downloadType = downloadType;
	}
	public String getParamHead1() {
		return paramHead1;
	}
	public void setParamHead1(String paramHead1) {
		this.paramHead1 = paramHead1;
	}
	public String getParamHead2() {
		return paramHead2;
	}
	public void setParamHead2(String paramHead2) {
		this.paramHead2 = paramHead2;
	}
	public String getParamHead3() {
		return paramHead3;
	}
	public void setParamHead3(String paramHead3) {
		this.paramHead3 = paramHead3;
	}
	public String getParamHead4() {
		return paramHead4;
	}
	public void setParamHead4(String paramHead4) {
		this.paramHead4 = paramHead4;
	}
	public String getParamHead5() {
		return paramHead5;
	}
	public void setParamHead5(String paramHead5) {
		this.paramHead5 = paramHead5;
	}
	public String getParamHead6() {
		return paramHead6;
	}
	public void setParamHead6(String paramHead6) {
		this.paramHead6 = paramHead6;
	}
	public String getParamHead7() {
		return paramHead7;
	}
	public void setParamHead7(String paramHead7) {
		this.paramHead7 = paramHead7;
	}
	public String getParamHead8() {
		return paramHead8;
	}
	public void setParamHead8(String paramHead8) {
		this.paramHead8 = paramHead8;
	}
	public String getParamHead9() {
		return paramHead9;
	}
	public void setParamHead9(String paramHead9) {
		this.paramHead9 = paramHead9;
	}
	public String getParamHead10() {
		return paramHead10;
	}
	public void setParamHead10(String paramHead10) {
		this.paramHead10 = paramHead10;
	}
	public String getParamHead11() {
		return paramHead11;
	}
	public void setParamHead11(String paramHead11) {
		this.paramHead11 = paramHead11;
	}
	public String getParamHead12() {
		return paramHead12;
	}
	public void setParamHead12(String paramHead12) {
		this.paramHead12 = paramHead12;
	}
	public String getParamHead13() {
		return paramHead13;
	}
	public void setParamHead13(String paramHead13) {
		this.paramHead13 = paramHead13;
	}
	public String getParamHead14() {
		return paramHead14;
	}
	public void setParamHead14(String paramHead14) {
		this.paramHead14 = paramHead14;
	}
	public String getParamHead15() {
		return paramHead15;
	}
	public void setParamHead15(String paramHead15) {
		this.paramHead15 = paramHead15;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	
	public List<Map<String, Object>> getMotorEditRateDtl() {
		return motorEditRateDtl;
	}
	public void setMotorEditRateDtl(List<Map<String, Object>> motorEditRateDtl) {
		this.motorEditRateDtl = motorEditRateDtl;
	}

	private List<Map<String,Object>> brokerBranchList;
	private String brokerBranchName;
	private String brokerBranchId;
	private String brokerBranchCode;


	public List<Map<String, Object>> getBrokerBranchList() {
		return brokerBranchList;
	}
	public void setBrokerBranchList(List<Map<String, Object>> brokerBranchList) {
		this.brokerBranchList = brokerBranchList;
	}
	public String getBrokerBranchName() {
		return brokerBranchName;
	}
	public void setBrokerBranchName(String brokerBranchName) {
		this.brokerBranchName = brokerBranchName;
	}
	public String getBrokerBranchId() {
		return brokerBranchId;
	}
	public void setBrokerBranchId(String brokerBranchId) {
		this.brokerBranchId = brokerBranchId;
	}
	public String getBrokerBranchCode() {
		return brokerBranchCode;
	}
	public void setBrokerBranchCode(String brokerBranchCode) {
		this.brokerBranchCode = brokerBranchCode;
	}

	private List<Map<String,Object>> executiveList;
	private String executiveName;
	private String executiveCommission;
	private String executiveCoreCode;
	private String executiveID;


	public List<Map<String, Object>> getExecutiveList() {
		return executiveList;
	}
	public void setExecutiveList(List<Map<String, Object>> executiveList) {
		this.executiveList = executiveList;
	}
	public String getExecutiveName() {
		return executiveName;
	}
	public void setExecutiveName(String executiveName) {
		this.executiveName = executiveName;
	}
	public String getExecutiveCommission() {
		return executiveCommission;
	}
	public void setExecutiveCommission(String executiveCommission) {
		this.executiveCommission = executiveCommission;
	}
	public String getExecutiveCoreCode() {
		return executiveCoreCode;
	}
	public void setExecutiveCoreCode(String executiveCoreCode) {
		this.executiveCoreCode = executiveCoreCode;
	}
	public String getExecutiveID() {
		return executiveID;
	}
	public void setExecutiveID(String executiveID) {
		this.executiveID = executiveID;
	}
	/**
	 * @return the sno
	 */
	public String getSno() {
		return sno;
	}
	/**
	 * @param sno the sno to set
	 */
	public void setSno(String sno) {
		this.sno = sno;
	}
	/**
	 * @return the conditionDesc
	 */
	public String getConditionDesc() {
		return conditionDesc;
	}
	/**
	 * @param conditionDesc the conditionDesc to set
	 */
	public void setConditionDesc(String conditionDesc) {
		this.conditionDesc = conditionDesc;
	}
	/**
	 * @return the conditionType
	 */
	public String getConditionType() {
		return conditionType;
	}
	/**
	 * @param conditionType the conditionType to set
	 */
	public void setConditionType(String conditionType) {
		this.conditionType = conditionType;
	}
	
	private String deductibleAmountUSD;
	private String theftExcessZMW;
	private String theftExcessUSD;
	private String driverExcessZMW;
	private String driverExcessUSD;
	private String claimExcessZMW;
	private String claimExcessUSD;

	public String getDeductibleAmountUSD() {
		return deductibleAmountUSD;
	}
	public void setDeductibleAmountUSD(String deductibleAmountUSD) {
		this.deductibleAmountUSD = deductibleAmountUSD;
	}
	public String getTheftExcessZMW() {
		return theftExcessZMW;
	}
	public void setTheftExcessZMW(String theftExcessZMW) {
		this.theftExcessZMW = theftExcessZMW;
	}
	public String getTheftExcessUSD() {
		return theftExcessUSD;
	}
	public void setTheftExcessUSD(String theftExcessUSD) {
		this.theftExcessUSD = theftExcessUSD;
	}
	public String getDriverExcessZMW() {
		return driverExcessZMW;
	}
	public void setDriverExcessZMW(String driverExcessZMW) {
		this.driverExcessZMW = driverExcessZMW;
	}
	public String getDriverExcessUSD() {
		return driverExcessUSD;
	}
	public void setDriverExcessUSD(String driverExcessUSD) {
		this.driverExcessUSD = driverExcessUSD;
	}
	public String getClaimExcessZMW() {
		return claimExcessZMW;
	}
	public void setClaimExcessZMW(String claimExcessZMW) {
		this.claimExcessZMW = claimExcessZMW;
	}
	public String getClaimExcessUSD() {
		return claimExcessUSD;
	}
	public void setClaimExcessUSD(String claimExcessUSD) {
		this.claimExcessUSD = claimExcessUSD;
	}
	
	private List<Map<String,Object>> exchangeList;
	private String exchangeId;
	private String exchangeRate;
	private String currencyName;
	
	public List<Map<String, Object>> getExchangeList() {
		return exchangeList;
	}
	public void setExchangeList(List<Map<String, Object>> exchangeList) {
		this.exchangeList = exchangeList;
	}
	public String getExchangeId() {
		return exchangeId;
	}
	public void setExchangeId(String exchangeId) {
		this.exchangeId = exchangeId;
	}
	public String getExchangeRate() {
		return exchangeRate;
	}
	public void setExchangeRate(String exchangeRate) {
		this.exchangeRate = exchangeRate;
	}
	public String getCurrencyName() {
		return currencyName;
	}
	public void setCurrencyName(String currencyName) {
		this.currencyName = currencyName;
	}
	
	
}
