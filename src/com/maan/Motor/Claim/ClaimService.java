package com.maan.Motor.Claim;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.opensymphony.xwork2.ActionSupport;

public class ClaimService extends ActionSupport{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ClaimDao dao = new ClaimDao();

	public String saveClaimIntimationDetails(ClaimBean bean) {
		return dao.saveClaimIntimationDetails(bean);

	}

	public void getClaimDetails(ClaimBean bean, String productId,
			String branchCode) {
		dao.getClaimDetails(bean, productId, branchCode);

	}

	public int getClaimStatus(String policyNo) {
		return dao.getClaimStatus(policyNo);
	}

	public List<Map<Object, Object>> getClaimAdminDetail(ClaimBean bean) {
		return dao.getClaimAdminDetail(bean);
	}

	public void getClaimCloseDetails(ClaimBean bean) {
		dao.getClaimCloseDetails(bean);

	}

	public String getApplicationNo(String policyNo) {
		return dao.getApplicationNo(policyNo);
	}

	public void updateCloseDetail1s(ClaimBean bean) {
		dao.updateCloseDetails(bean);
	}

	public List<Map<String, Object>> getIntimatePolicy (ClaimBean bean){
		return dao.getIntimatePolicy(bean);
	}
	
	public List<Map<String, Object>> getIntimateStatus (ClaimBean bean){
		return dao.getIntimateStatus(bean);
	}

	public List<Map<String, Object>> getIntimateVehicle (ClaimBean bean){
		return dao.getIntimateVehicle(bean);
	}
	
	public int getCheckIntimatePolicy (ClaimBean bean){
		return dao.getCheckIntimatePolicy(bean);
	}
	
	public  void getIntimateInsert (ClaimBean bean){
		 dao.getIntimateInsert(bean);
	}
	
	public List<Map<String, Object>> getIntimateView (ClaimBean bean){
		return dao.getIntimateView(bean);
	}
	
	public List<Map<String, Object>> getIntimateEdit (ClaimBean bean){
		return dao.getIntimateEdit(bean);
	}
	
	public  void getIntimateThirdPartyInsert (ClaimBean bean){
		 dao.getIntimateThirdPartyInsert(bean);
	}
	
	public List<Map<String, Object>> getIntimateThirdPartyView (ClaimBean bean){
		return dao.getIntimateThirdPartyView(bean);
	}
	
	public List<Map<String, Object>> getIntimateThirdPartyEdit (ClaimBean bean){
		return dao.getIntimateThirdPartyEdit(bean);
	}
	
	public int checkIntimateComp (ClaimBean bean){
		return dao.checkIntimateComp(bean);
	}
	
	public int checkIntimateTpa (ClaimBean bean){
		return dao.checkIntimateTpa(bean);
	}
	
	public List<Map<String, Object>> getAssitHomeInfo(ClaimBean bean){
		return dao.getAssitHomeInfo(bean);
	}
	
	public List<Object> getHomeInfo(ClaimBean bean){
		return dao.getHomeInfo(bean);
	}

	public List<Map<String, Object>> getEndorsamentPolicy(ClaimBean bean) {
		return dao.getEndorsamentPolicy(bean);
	}
	public List<Map<String, Object>> getEndorsamentVehicle(ClaimBean bean) {
		return dao.getEndorsamentVehicle(bean);
	}


	public List<Map<String, Object>> getEndorsementDropdown(ClaimBean bean) {
		return dao.getEndorsementDropdown(bean);
	}

	public List<Map<String, Object>> getEndtReqList(ClaimBean bean) {
		return dao.getEndtReqList(bean);
	}

	public String insertEndtRequest(ClaimBean bean) {
		return dao.insertEndtRequest(bean);
		
	}

	public List<Object> getHomeList(ClaimBean bean) {
		dao.getAssitHomeInfo(bean);
		return dao.getHomeInfo(bean);
	}
	public List<Map<String,Object>> claimIntimateReportList(ClaimBean bean) {
		return dao.claimIntimateReportList(bean);
     }


	public List<Map<String,Object>> claimIntimateReportTpaList(ClaimBean bean) {
		return dao.claimIntimateReportTpaList(bean);
	}

	public List<Map<String, Object>> getClaimReqList(ClaimBean bean) {
		return dao.getClaimReqList(bean);
	}
	public void getIntimationInsert(ClaimBean bean){
		dao.getIntimationInsert(bean);
	}

	public void getIntimationTpaInsert(ClaimBean bean){
		dao.getIntimationTpaInsert(bean);
	}

	public void getEndorsementUpdate(ClaimBean bean) {
		dao.getEndorsementUpdate(bean);		
	}
	public List<Map<String, Object>> geteEndorsementList(ClaimBean bean) {
		return dao.getEndorsementList(bean);
	}
	public List<Map<String, Object>> getEndorsementListView(ClaimBean bean) {

		return dao.getEndorsementListView(bean);
	}
	public List<Map<String, Object>> getIntimateDistinctVehicleList(ClaimBean bean){
		return dao.getIntimateDistinctVehicleList(bean);
	}
	public List<Map<String, Object>> getIntimateSameVehicleList(ClaimBean bean){
		return dao.getIntimateSameVehicleList(bean);
	}
	public List<Map<String, Object>> getIntimateSameTpaVehicleList(ClaimBean bean){
		return dao.getIntimateSameTpaVehicleList(bean);
	}

	public List<Map<String, Object>> getEndtNewReqList(ClaimBean bean) {
		return dao.getEndtNewReqList(bean);	
		}

	public String getUpdateStatus(ClaimBean bean) {
		return dao.getUpdateStatus(bean);
	}

	public void insertClaimApprovedDetails(ClaimBean bean) {
		dao.insertClaimApprovedDetails(bean);
	}

	public LinkedList<String> getValidate(ClaimBean bean, String type, String motorTp) {
		LinkedList<String> list=new LinkedList<String>();
		if("claim".equalsIgnoreCase(type)){
		if("65".equalsIgnoreCase(bean.getProductId())){
			if(motorTp.equalsIgnoreCase(bean.getPolicyType())) {
				if(StringUtils.isBlank(bean.getHospitalName())) {
					list.add("error.claim.hospitalName");
				}
				if(StringUtils.isBlank(bean.getNameOfDoctor())) {
					list.add("error.claim.nameOfDoctor");
				}
				if(StringUtils.isBlank(bean.getDoctorTelephoneNumber())) {
					list.add("error.claim.doctorTelephoneNumber");
				}
				if(StringUtils.isBlank(bean.getThirdPartyName())) {
					list.add("error.claim.thirdPartyName");
				}
				if(StringUtils.isBlank(bean.getThirdPartyPropertyDetail())) {
					list.add("error.claim.thirdPartyPropertyDetail");
				}
				if(StringUtils.isBlank(bean.getThirdPartyOwnerName())) {
					list.add("error.claim.thirdPartyOwnerName");
				}
				if(StringUtils.isBlank(bean.getThirdPartyOwnerTelephoneNumber())) {
					list.add("error.claim.tpOwnerTelephoneNo");
				}
				if(StringUtils.isBlank(bean.getDriverName())) {
					list.add("error.claim.driverName");
				}
				if(StringUtils.isBlank(bean.getPhysicalAddress())) {
					list.add("error.claim.physicalAddress");
				}
				if(StringUtils.isBlank(bean.getRegisterNumber())) {
					list.add("error.claim.registerNo");
				}
				if(StringUtils.isBlank(bean.getMake())) {
					list.add("error.claim.make");
				}
				if(StringUtils.isBlank(bean.getNameOfInsurer())) {
					list.add("error.claim.nameOfInsurer");
				}
			} else {
				if(StringUtils.isBlank(bean.getSoleOwnerYN())) {
					list.add("error.claim.soleOwnerYN");
				} else if("N".equals(bean.getSoleOwnerYN()) && StringUtils.isBlank(bean.getFinanciers())) {
					list.add("error.claim.financier");
				}
				if(StringUtils.isBlank(bean.getNameOfDriver())) {
					list.add("error.claim.nameOfDriver");
				}
				if(StringUtils.isBlank(bean.getDriverDob())) {
					list.add("error.claim.dob");
				}
				if(StringUtils.isBlank(bean.getDriverDob())) {
					list.add("error.claim.dob");
				}
				if(StringUtils.isBlank(bean.getLicenseNumber())) {
					list.add("error.claim.licenceNo");
				}
				if(StringUtils.isBlank(bean.getDateObtained())) {
					list.add("error.claim.dateObtained");
				}
				if(StringUtils.isBlank(bean.getLossDate())) {
					list.add("error.claim.lossDate");
				}
				if(StringUtils.isBlank(bean.getLossTime())) {
					list.add("error.claim.lossTime");
				}
				if(StringUtils.isBlank(bean.getLossSpeed())) {
					list.add("error.claim.lossSpeed");
				}
				if(StringUtils.isBlank(bean.getLossPlace())) {
					list.add("error.claim.lossPlace");
				}
				if(StringUtils.isBlank(bean.getLossPurposeDescription())) {
					list.add("error.claim.lossPurposeDesc");
				}
				/*if(StringUtils.isBlank(bean.getLossDetailDescription())) {
					addActionError(getText("error.claim.lossDetailDesc"));
				}*/
				if(StringUtils.isBlank(bean.getLossReportYN())){
					list.add("error.loss.report.yn");
				}
				if("Y".equalsIgnoreCase(bean.getLossReportYN())&&StringUtils.isBlank(bean.getLossDateReported())) {
					list.add("error.claim.lossDateReported");
				}
				if("Y".equalsIgnoreCase(bean.getLossReportYN())&&StringUtils.isBlank(bean.getLossTimeReported())) {
					list.add("error.claim.lossTimeReported");
				}
				if(StringUtils.isBlank(bean.getLossPoliceVisitYN())){
					list.add("error.loss.police.report.yn");
				}
				if("Y".equalsIgnoreCase(bean.getLossPoliceVisitYN())&&StringUtils.isBlank(bean.getPoliceOfficerName())) {
					list.add("error.claim.policeOfficerName");
				}
				/*if("Y".equalsIgnoreCase(bean.getLossPoliceVisitYN())&&StringUtils.isBlank(bean.getIdentityNumber())) {
					addActionError(getText("error.claim.identityNo"));
				}*/
				if("Y".equalsIgnoreCase(bean.getLossPoliceVisitYN())&&StringUtils.isBlank(bean.getPoliceStationName())) {
					list.add("error.claim.policeStationName");
				}
				if(StringUtils.isBlank(bean.getLossLocation())) {
					list.add("error.claim.lossLocation");
				}
				if(StringUtils.isBlank(bean.getDriverDescription())) {
					list.add("error.claim.driverDescription");
				}
			}
		}else if("30".equalsIgnoreCase(bean.getProductId())){
			if(StringUtils.isBlank(bean.getLossStatus()))
				list.add("error.choose.loss.status");
			if(StringUtils.isBlank(bean.getLossDescription()))
				list.add("error.enter.loss.description");
		}
		if(StringUtils.isBlank(bean.getDeclaration())) {
				list.add("error.claim.declaration");
		}
		}else if("endorsement".equalsIgnoreCase(type)){
			if(StringUtils.isBlank(bean.getEndTypeId())) {
				list.add("error.endtType");
				addActionError(getText("error.endtType"));
			}
			if(StringUtils.isBlank(bean.getAgentRemarks())) {
				list.add("error.agent.remarks");
			}
		}
		
		return list;
	}
}
