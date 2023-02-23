package com.maan.adminnew.MotorNew;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.maan.common.DBConstants;
import com.maan.common.ExcelDownload;
import com.maan.common.LogManager;
import com.maan.common.Validation;
import com.maan.upload.UploadAction;
import com.maan.upload.service.UploadService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class MotorAdminActionNew extends ActionSupport implements ModelDriven<MotorAdminBeanNew>{

	private static final long serialVersionUID = 1L;
	private InputStream inputStream;
	
	MotorAdminBeanNew bean =new MotorAdminBeanNew();
	MotorAdminServiceNew service = new MotorAdminServiceNew();
	Map<String, Object> session = ActionContext.getContext().getSession();
	String branchCode = (String) session.get("BranchCode");
	String productId = "65";
	String userType = (String) session.get("usertype");
	DateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy");
	Date date = new Date(0);
	Validation validation = new Validation();
	
	private File upload;
	private String uploadFileName;
	private String uploadContentType;
	private String downloadFileName;

	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public String getUploadContentType() {
		return uploadContentType;
	}

	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}
	
	public String getDownloadFileName() {
		return downloadFileName;
	}

	public void setDownloadFileName(String downloadFileName) {
		this.downloadFileName = downloadFileName;
	}
	
	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}
	
	public MotorAdminBeanNew getModel() {
		return bean;
	}
	
	public List<Object> getUserSelection() {
		return service.getUserList((String)session.get("user"), productId);
	}
	
	public List<Map<String,Object>> getBranchList(){
		return service.getBranchList();
	}
	
	public String subPolicy(){
		bean.setPolicyTypeList(service.getDropDown(bean,"policyType"));
		bean.setVehicleTypeList(service.getDropDown(bean,"vehicleType"));
		if("subPolicyList".equalsIgnoreCase(bean.getMode())){
			if(StringUtils.isBlank(bean.getBodyType()))
				addActionError(getText("error.choose.body.type"));
			if(StringUtils.isBlank(bean.getVehicleType()))
				addActionError(getText("error.enter.vehicle.type"));
			if(StringUtils.isBlank(bean.getPolicyType()))
				addActionError(getText("error.enter.policy.type"));
			if(!hasActionErrors())
				bean.setSubPolicyList(service.getSubPolicyList(bean));
		}
		else if(!"subPolicyList".equalsIgnoreCase(bean.getMode()) && ((StringUtils.isNotBlank(bean.getBodyType())) && (StringUtils.isNotBlank(bean.getVehicleType())) && (StringUtils.isNotBlank(bean.getPolicyType())))){
			bean.setSubPolicyList(service.getSubPolicyList(bean));
		}
		return "subPolicy";
	}
	public String editSubPolicy(){
		if("editSubPolicy".equalsIgnoreCase(bean.getMode())){
			service.editSubPolicyDetails(bean);
		}
		/*bean.setApplicableCountryList(service.getDropDown(bean,"country"));
		bean.setApplicableCityList(service.getDropDown(bean,"city"));*/
		bean.setModalHeaderList(service.getHeaderDetails(bean,"subPolicyList"));
		bean.setSubPolicyList(service.getSubPolicyList(bean));
		return "editSubPolicy";
	}
	public String insSubPolicyType(){
		validateSubPolicyType(bean);
		String result="";
		if(!hasActionErrors()){
			String status = service.insertSubPolicyType(bean);
			if(StringUtils.isNotBlank(status)){
				addActionError("Unique Constraints Combination Already Exist");
			}else{
				if("editSubPolicy".equalsIgnoreCase(bean.getMode()))
					addActionMessage("Update Successfully");
				else if("addSubPolicy".equalsIgnoreCase(bean.getMode()))
					addActionMessage("Inserted Successfully");
				bean.setMode("");
				bean.setPolicyTypeList(service.getDropDown(bean,"policyType"));
				bean.setVehicleTypeList(service.getDropDown(bean,"vehicleType"));
				bean.setSubPolicyList(service.getSubPolicyList(bean));			
				result =  "subPolicy";
			}
		}
		if(hasActionErrors()){
			/*bean.setApplicableCountryList(service.getDropDown(bean,"country"));
			bean.setApplicableCityList(service.getDropDown(bean,"city"));*/
			bean.setModalHeaderList(service.getHeaderDetails(bean,"subPolicyList"));
			bean.setSubPolicyList(service.getSubPolicyList(bean));
			result = "editSubPolicy";
		}
		return result;
	}
	private void validateSubPolicyType(MotorAdminBeanNew bean2) {
		try{
			if(bean.getDescriptionList()!=null && bean.getDescriptionList().size()>0){
				String desc = "";
				List<String> descList=new ArrayList<String>();
				for(int i=0;i<bean.getDescriptionList().size();i++){
					if(StringUtils.isNotBlank(bean.getDescriptionList().get(i))){
						desc += bean.getDescriptionList().get(i);
						descList.add(bean.getDescriptionList().get(i));
						if( i < bean.getDescriptionList().size()-1){
							desc +="~";
						}
					}
				}
				bean.setDescription(desc);
				bean.setDescriptionList(descList);
			}
			
			if(StringUtils.isBlank(bean.getVehicleStartAge()))
				addActionError(getText("error.enter.vehicle.start.age"));
			if(StringUtils.isBlank(bean.getVehicleEndAge()))
				addActionError(getText("error.enter.vehicle.end.age"));
			if(StringUtils.isNotBlank(bean.getVehicleStartAge()) && StringUtils.isNotBlank(bean.getVehicleEndAge())){
				if(Integer.parseInt(bean.getVehicleStartAge()) >= Integer.parseInt(bean.getVehicleEndAge()))
					addActionError(getText("error.vehicleEndAge.must.be.greater.than.vehicleStartAge"));
			}
			if(StringUtils.isBlank(bean.getEffectiveDate()))
				addActionError(getText("error.enter.effective.date"));
			else if(service.diffInDays(bean.getEffectiveDate(),"") >= 0)
				addActionError(getText("error.enter.effective.date.greater.sysdate"));
			if(StringUtils.isBlank(bean.getVehicleCount()))
				addActionError(getText("error.enter.vehicle.count"));
			else if(!StringUtils.isNumeric(bean.getVehicleCount()))
				addActionError(getText("error.enter.vehicle.count.must.be.numeric"));
			if(StringUtils.isBlank(bean.getStatus()))
				addActionError(getText("error.choose.status"));
			/*if(StringUtils.isBlank(bean.getApplicableCountry()))
				addActionError(getText("error.enter.vehicle.applicable.country"));
			if(StringUtils.isBlank(bean.getApplicableCity()))
				addActionError(getText("error.enter.vehicle.applicable.city"));
			if(StringUtils.isBlank(bean.getClaimStart()))
				addActionError(getText("error.enter.claim.start"));
			if(StringUtils.isBlank(bean.getClaimEnd()))
				addActionError(getText("error.enter.claim.end"));*/
			/*if(service.checkVehicleAge(bean) > 0)
				addActionError(getText("error.vehicleStarAge.endAge.exist"));*/
			if(!hasActionErrors()){
				if(!("editSubPolicy".equalsIgnoreCase(bean.getMode()) && "N".equalsIgnoreCase(bean.getStatus()))){
					if(service.checkCombinationOfSubPolicy(bean) > 0)
						addActionError(getText("These Combination Already Exist"));
				}
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public String comprehensive(){
		bean.setComprehensiveRateList(service.getComprehensiveRateList(bean));
		return "comprehensive";
	}

	public String editComprehensiveRate(){
		if(!"editCompreRate".equalsIgnoreCase(bean.getMode())){
			bean.setUserNameLoginList(service.getDropDown(bean, "usernamelogin"));
		}
		service.getComprehensiveRateDropDown(bean,branchCode);
		if("editCompreRate".equalsIgnoreCase(bean.getMode())){
			bean.setUserNameLoginList(service.getDropDown(bean, "usernamelogin1"));
			service.getEditCompreRateDetails(bean);
		}
		return "editCompreRate";
	}
	public String insCompreRate(){
		String result="";
		validateCompreRate();
		if(!hasActionErrors()){
			String status = service.insertCompreRate(bean);
			if(StringUtils.isNotBlank(status)){
				addActionError("Unique Constraints Combination Already Exist");
			}else{
				if("editCompreRate".equalsIgnoreCase(bean.getMode()))
					addActionMessage("Update Successfully");
				else if("addCompreRate".equalsIgnoreCase(bean.getMode()))
					addActionMessage("Inserted Successfully");
				bean.setMode("");
				bean.setPolicyTypeList(service.getDropDown(bean,"policyType"));
				bean.setVehicleTypeList(service.getDropDown(bean,"vehicleType"));
				bean.setSubPolicyList(service.getSubPolicyList(bean));
				bean.setComprehensiveRateList(service.getComprehensiveRateList(bean));
				result =  "comprehensive";
			}
		}if(hasActionErrors()){
			service.getComprehensiveRateDropDown(bean,branchCode);
			bean.setUserNameLoginList(service.getDropDown(bean, "usernamelogin1"));
			result ="editCompreRate";
		}
		return result;
	}
	private void validateCompreRate() {
		try{
			if(StringUtils.isBlank(bean.getSeatingTonStart()))
				addActionError(getText("error.enter.seating.ton.start"));
			else if(StringUtils.isAlphaSpace(bean.getSeatingTonStart()))
				addActionError(getText("error.enter.numeric.seating.ton.start"));
			if(StringUtils.isBlank(getText(bean.getUserNameLogin())))
				addActionError(getText("error.enter.username.login"));
			if(StringUtils.isBlank(bean.getSeatingTonEnd()))
				addActionError(getText("error.enter.seating.ton.end"));
			else if(StringUtils.isAlphaSpace(bean.getSeatingTonEnd()))
				addActionError(getText("error.enter.numeric.seating.ton.end"));
			if(StringUtils.isNotBlank(bean.getSeatingTonStart()) && StringUtils.isNotBlank(bean.getSeatingTonEnd())){
				if(Integer.parseInt(bean.getSeatingTonStart()) >= Integer.parseInt(bean.getSeatingTonEnd()))
					addActionError(getText("error.enter.seating.ton.start.less.than.seating.ton.end"));
			}
			if(StringUtils.isBlank(bean.getCCGVWStartRange()))
				addActionError(getText("error.enter.cc.gvw.start"));
			else if(StringUtils.isAlphaSpace(bean.getCCGVWStartRange()))
				addActionError(getText("error.enter.numeric.cc.gvw.start"));
			if(StringUtils.isBlank(bean.getCCGVWEndRange()))
				addActionError(getText("error.enter.cc.gvw.end"));
			else if(StringUtils.isAlphaSpace(bean.getCCGVWEndRange()))
				addActionError(getText("error.enter.numeric.cc.gvw.end"));
			if(StringUtils.isNotBlank(bean.getCCGVWStartRange()) && StringUtils.isNotBlank(bean.getCCGVWEndRange())){
				if(Integer.parseInt(bean.getCCGVWStartRange()) >= Integer.parseInt(bean.getCCGVWEndRange()))
					addActionError(getText("error.enter.cc.gvw.start.range.less.than.cc.gvw.end.range"));
			}
			if(StringUtils.isBlank(bean.getUAELiscenceStart()))
				addActionError(getText("error.enter.uae.license.start"));
			else if(StringUtils.isAlphaSpace(bean.getUAELiscenceStart()))
				addActionError(getText("error.enter.numeric.uae.license.start"));
			if(StringUtils.isBlank(bean.getUAELiscenceEnd()))
				addActionError(getText("error.enter.uae.license.end"));
			else if(StringUtils.isAlphaSpace(bean.getUAELiscenceEnd()))
				addActionError(getText("error.enter.numeric.uae.license.end"));
			if(StringUtils.isNotBlank(bean.getUAELiscenceStart()) && StringUtils.isNotBlank(bean.getUAELiscenceEnd())){
				if(Integer.parseInt(bean.getUAELiscenceStart()) >= Integer.parseInt(bean.getUAELiscenceEnd()))
					addActionError(getText("error.enter.uae.license.start.less.than.uae.license.end"));
			}
			if(StringUtils.isBlank(bean.getDriverAgeFrom()))
				addActionError(getText("error.enter.driver.age.from"));
			else if(StringUtils.isAlphaSpace(bean.getDriverAgeFrom()))
				addActionError(getText("error.enter.numeric.driver.age.from"));
			if(StringUtils.isBlank(bean.getDriverAgeTo()))
				addActionError(getText("error.enter.driver.age.to"));
			else if(StringUtils.isAlphaSpace(bean.getDriverAgeTo()))
				addActionError(getText("error.enter.numeric.driver.age.to"));
			if(StringUtils.isNotBlank(bean.getDriverAgeFrom()) && StringUtils.isNotBlank(bean.getDriverAgeTo())){
				if(Integer.parseInt(bean.getDriverAgeFrom()) >= Integer.parseInt(bean.getDriverAgeTo()))
					addActionError(getText("error.enter.driver.age.from.less.than.driver.age.to"));	
			}
			if(StringUtils.isBlank(bean.getSuminsuredStart()))
				addActionError(getText("error.enter.sum.insured.start"));
			else if(StringUtils.isAlphaSpace(bean.getSuminsuredStart()))
				addActionError(getText("error.enter.numeric.sum.insured.start"));
			if(StringUtils.isBlank(bean.getSuminsuredEnd()))
				addActionError(getText("error.enter.sum.insured.end"));
			else if(StringUtils.isAlphaSpace(bean.getSuminsuredEnd()))
				addActionError(getText("error.enter.numeric.sum.insured.end"));
			if(StringUtils.isNotBlank(bean.getSuminsuredStart()) && StringUtils.isNotBlank(bean.getSuminsuredEnd())){
				if(Integer.parseInt(bean.getSuminsuredStart()) >= Integer.parseInt(bean.getSuminsuredEnd()))
					addActionError(getText("error.enter.suminsured.start.less.than.suminsured.end"));	
			}
			if(StringUtils.isBlank(bean.getPolicyStartDate()))
				addActionError(getText("error.select.policy.start.date"));
			if(StringUtils.isBlank(bean.getPolicyEndDate()))
				addActionError(getText("error.select.policy.end.date"));
			if(StringUtils.isNotBlank(bean.getPolicyStartDate())&&StringUtils.isNotBlank(bean.getPolicyEndDate())){
				if(service.diffInDays(bean.getPolicyStartDate(),bean.getPolicyEndDate()) < 0)
					addActionError(getText("error.policy.start.date.must.be.less.than.policy.end.date"));
			}
			if("editCompreRate".equalsIgnoreCase(bean.getMode())){
				if(service.diffInDays(bean.getPolicyStartDate(),"") >= 0)
				addActionError(getText("Policy Start Date Must Be Greater than Today Date"));
			}
			/*if(!("editCompreRate".equalsIgnoreCase(bean.getMode()) && "N".equalsIgnoreCase(bean.getStatus()))){
				if(service.checkPolicyDate(bean) > 0){
					addActionError(getText("Policy Start Date And Policy End Date Combination Already Exist"));
				}
			}*/
			if(StringUtils.isBlank(bean.getMotorRate()))
				addActionError(getText("error.enter.motor.rate"));
			else if(StringUtils.isAlpha(bean.getMotorRate()))
				addActionError(getText("error.motor.rate.must.be.floating.point"));
			if(StringUtils.isBlank(bean.getMinimumPremium()))
				addActionError(getText("error.enter.minimum.premium"));
			else if(StringUtils.isAlpha(bean.getMinimumPremium()))
				addActionError(getText("error.minimum.premium.must.be.floating.point"));
			if(StringUtils.isBlank(bean.getOnlinePercent()))
				addActionError(getText("error.enter.online.percent"));
			else if(StringUtils.isAlpha(bean.getOnlinePercent()))
				addActionError(getText("error.online.percent.must.be.floatint.point"));
			if(StringUtils.isBlank(bean.getDeductionPercent()))
				addActionError(getText("error.enter.deduction.percent"));
			else if(StringUtils.isAlpha(bean.getDeductionPercent()))
				addActionError(getText("error.deduction.percent.must.be.floatint.point"));
			if(StringUtils.isBlank(bean.getDeductionAmount()))
				addActionError(getText("error.enter.deduction.amount"));
			if(StringUtils.isBlank(bean.getEffectiveDate()))
				addActionError(getText("error.enter.effective.date"));
			else if(service.diffInDays(bean.getEffectiveDate(),"") >= 0)
				addActionError(getText("error.enter.effective.date.greater.sysdate"));
			if(StringUtils.isBlank(bean.getStatus()))
				addActionError(getText("error.choose.status"));
		}catch(Exception e){
			LogManager.debug("Exception Occured @ validateCompreRate => "+e);
			e.printStackTrace();
		}
	}
	public String thirdParty(){
		bean.setVehicleTypeList(service.getDropDown(bean,"vehicleType"));
		if("thirdPartyList".equalsIgnoreCase(bean.getMode())){
			if(StringUtils.isBlank(bean.getVehicleType()))
				addActionError(getText("error.enter.vehicle.type"));
			if(StringUtils.isBlank(bean.getBodyType()))
				addActionError(getText("error.choose.body.type"));
			if(!hasActionErrors())
				bean.setThirdPartyList(service.getThirdPartList(bean));
		}
		if(StringUtils.isNotBlank(bean.getVehicleType()) && StringUtils.isNotBlank(bean.getBodyType()))
		bean.setThirdPartyList(service.getThirdPartList(bean));
		return "thirdParty";
		
	}
	public String editThirdParty(){
		bean.setModalHeaderList(service.getHeaderDetails(bean,"thirdParty"));
		bean.setThirdPartyList(service.getThirdPartModelList(bean));
		bean.setVehicleTypeList(service.getDropDown(bean,"vehicleType"));
		if(!"edit".equalsIgnoreCase(bean.getMode())){
			bean.setUserNameLoginList(service.getDropDown(bean, "usernameloginn"));
		}
		if("edit".equalsIgnoreCase(bean.getMode())){
			bean.setUserNameLoginList(service.getDropDown(bean, "usernamelogin1"));
			service.getEditThirdParty(bean);
		}
		return "editThirdParty";
	}
	public String insthirdParty(){
		String result="";
		validateThirdParty();
		if(!hasActionErrors()){
			String status = service.insertThirdPartyMaster(bean);
			if(StringUtils.isNotBlank(status)){
				addActionError("Unique Constraints Combination Already Exist");
			}else{
				if("edit".equalsIgnoreCase(bean.getMode()))
					addActionMessage("Update Successfully");
				else if("add".equalsIgnoreCase(bean.getMode()))
					addActionMessage("Inserted Successfully");
				bean.setMode("");
				bean.setVehicleTypeList(service.getDropDown(bean,"vehicleType"));
				bean.setUserNameLoginList(service.getDropDown(bean, "usernameloginn"));
				bean.setThirdPartyList(service.getThirdPartList(bean));
				result= "thirdParty";
			}
		}
		if(hasActionErrors()){
			bean.setModalHeaderList(service.getHeaderDetails(bean,"thirdParty"));
			bean.setThirdPartyList(service.getThirdPartModelList(bean));
			bean.setUserNameLoginList(service.getDropDown(bean, "usernamelogin1"));
			result = "editThirdParty";
		}
		return result;
	}
	private void validateThirdParty() {
		try{
			if(StringUtils.isBlank(bean.getSeatingCylinderStart()))
				addActionError(getText("error.enter.seating.cylinder.start"));
			if(StringUtils.isBlank(bean.getSeatingCylinderEnd()))
				addActionError(getText("error.enter.seating.cylinder.end"));
			if(StringUtils.isBlank(getText(bean.getUserNameLogin())))
				addActionError(getText("error.enter.username.login"));
			if(!("edit".equalsIgnoreCase(bean.getMode()) && "N".equalsIgnoreCase(bean.getStatus())) && (StringUtils.isNotBlank(bean.getSeatingCylinderStart()) && StringUtils.isNotBlank(bean.getSeatingCylinderEnd()))){
				if(Integer.parseInt(bean.getSeatingCylinderStart()) >= Integer.parseInt(bean.getSeatingCylinderEnd())){
					addActionError(getText("error.enter.seating.cylinder.start.must.be.less.than.seating.cylinder.end"));
				}else{
					int count = service.checkThirdPartySeating(bean);
					if(count > 0)
						addActionError(getText("error.enter.cylinder.combination.already.exit"));
				}
			}
			/*if(StringUtils.isBlank(bean.getVehicleType()))
				addActionError(getText("error.enter.vehicle.type"));
			else if(!("edit".equalsIgnoreCase(bean.getMode()) && "N".equalsIgnoreCase(bean.getStatus()))){
				if(service.checkThirdPartyVehicleUsage(bean) > 0)
					addActionError(getText("error.vehicle.usage.already.exist"));
			}*/
			if(StringUtils.isBlank(bean.getThirdPartyRate()))
				addActionError(getText("error.enter.third.party.rate"));
			else if(!StringUtils.isNumeric(bean.getThirdPartyRate().replace(".","")))
				addActionError(getText("error.enter.third.party.rate.must.be.numeric"));
			if(StringUtils.isBlank(bean.getCoreAppCode()))
				addActionError(getText("error.enter.core.app.code"));
			else if(!("edit".equalsIgnoreCase(bean.getMode()) && "N".equalsIgnoreCase(bean.getStatus()))){
				if(service.checkThirdPartyCoreCode(bean) > 0)
					addActionError(getText("error.core.app.code.already.exist"));
			}
			if(StringUtils.isNumeric(bean.getCoreAppCode()) && StringUtils.isNotBlank(bean.getCoreAppCode())){
				if(Integer.parseInt(bean.getCoreAppCode()) == 0){
					addActionError(getText("error.enter.valid.core.app.code"));
				}
			}
			if(StringUtils.isBlank(bean.getEffectiveDate()))
				addActionError(getText("error.enter.effective.date"));
			else if(service.diffInDays(bean.getEffectiveDate(),"") >= 0)
				addActionError(getText("error.enter.effective.date.greater.sysdate"));
			/*if(StringUtils.isBlank(bean.getLocationId()))
				addActionError(getText("error.select.vehicle.country"));*/
			if(StringUtils.isBlank(bean.getStatus()))
				addActionError(getText("error.select.status"));
		}catch(Exception e){
			LogManager.debug("Exception Occured @ "+e);
			e.printStackTrace();
		}
	}
	public String make(){
		/*bean.setVehicleTypeList(service.getDropDown(bean,"vehicleType"));
		if("makeList".equalsIgnoreCase(bean.getMode())){
			if(StringUtils.isBlank(bean.getBodyType()))
				addActionError(getText("error.choose.body.type"));
			if(StringUtils.isBlank(bean.getVehicleType()))
				addActionError(getText("error.enter.vehicle.type"));
			if(!hasActionErrors())
				bean.setMakeList(service.getMakeList(bean));
		}
		else if(StringUtils.isNotBlank(bean.getBodyType()) && StringUtils.isNotBlank(bean.getVehicleType()))*/
		bean.setMakeList(service.getMakeList(bean));
		return "makeMaster";
	}
	public String editMake(){
		if("editMake".equalsIgnoreCase(bean.getMode()))
			service.getEditMakeDetails(bean);
		return "makeMaster";
	}
	public String insMakeMaster(){
		validateMakeMaster();
		if(!hasActionErrors()){
			String status = service.insertMakeMaster(bean);
			if(StringUtils.isNotBlank(status)){
				addActionError("Unique Constraints Combination Already Exist");
			}else{
				if("editMake".equalsIgnoreCase(bean.getMode()))
					addActionMessage("Update Successfully");
				else if("addMake".equalsIgnoreCase(bean.getMode()))
					addActionMessage("Inserted Successfully");
				bean.setMode("");
				bean.setVehicleTypeList(service.getDropDown(bean,"vehicleType"));
				bean.setMakeList(service.getMakeList(bean));
			}
		}
		if(hasActionErrors()){
			/*bean.setModalHeaderList(service.getHeaderDetails(bean,"make"));*/
		}
		return "makeMaster";
	}
	private void validateMakeMaster() {
		try{
			if(StringUtils.isBlank(bean.getMakeName()))
				addActionError(getText("error.master.make.name"));
			if(StringUtils.isBlank(bean.getApplicableCountry()))
				addActionError(getText("error.master.make.choose.country"));
			if(StringUtils.isBlank(bean.getCoreAppCode()))
				addActionError(getText("error.enter.core.app.code"));
			else if(!("editMake".equalsIgnoreCase(bean.getMode()) && "N".equalsIgnoreCase(bean.getStatus()))){
				if(service.checkMakeCoreCode(bean) > 0)
					addActionError(getText("error.core.app.code.already.exist"));
			}
			if(StringUtils.isNumeric(bean.getCoreAppCode()) && StringUtils.isNotBlank(bean.getCoreAppCode())){
				if(Integer.parseInt(bean.getCoreAppCode()) == 0){
					addActionError(getText("error.enter.valid.core.app.code"));
				}
			}	
			if(StringUtils.isBlank(bean.getEffectiveDate()))
				addActionError(getText("error.enter.effective.date"));
			else if(service.diffInDays(bean.getEffectiveDate(),"") >= 0)
				addActionError(getText("error.enter.effective.date.greater.sysdate"));
			if(StringUtils.isBlank(bean.getExpiryDate()))
				addActionError(getText("error.enter.expiry.date"));
			if(StringUtils.isNotBlank(bean.getEffectiveDate()) && StringUtils.isNotBlank(bean.getExpiryDate())){
				if(service.diffInDays(bean.getEffectiveDate(), bean.getExpiryDate()) < 0)
					addActionError(getText("error.effective.date.must.be.less.than.expiry.date"));
			}
			if(StringUtils.isBlank(bean.getReferralStatus()))
				addActionError(getText("error.choose.referral.status"));
			if(StringUtils.isBlank(bean.getStatus()))
				addActionError(getText("error.choose.status"));
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public String model(){
		bean.setModelList(service.getModelList(bean));
		bean.setModalHeaderList(service.getHeaderDetails(bean,"model"));
		return "modelMaster";
	}
	public String editModel(){
		bean.setModalHeaderList(service.getHeaderDetails(bean,"model"));
		/*bean.setBodyTypeList(service.getDropDown(bean,"bodyTypeWithOutVehicleType"));*/
		if("editModel".equalsIgnoreCase(bean.getMode()))
			service.getEditModelDetails(bean);
		return "modelMaster";
	}
	public String insModelMaster(){
		validateModelMaster();
		if(!hasActionErrors()){
			String status = service.insertModelMaster(bean);;
			if(StringUtils.isNotBlank(status)){
				addActionError("Unique Constraints Combination Already Exist");
			}else{
				if("editModel".equalsIgnoreCase(bean.getMode()))
					addActionMessage("Update Successfully");
				else if("addModel".equalsIgnoreCase(bean.getMode()))
					addActionMessage("Inserted Successfully");
				bean.setMode("");
				bean.setModelList(service.getModelList(bean));
				bean.setModalHeaderList(service.getHeaderDetails(bean,"model"));
			}
		}if(hasActionErrors()){
			bean.setModalHeaderList(service.getHeaderDetails(bean,"model"));
			/*bean.setBodyTypeList(service.getDropDown(bean,"bodyTypeWithOutVehicleType"));*/
		}
		return "modelMaster";
	}
	private void validateModelMaster() {
		try{
			if(StringUtils.isBlank(bean.getModelName()))
				addActionError(getText("error.enter.model.name"));
/*			if(StringUtils.isBlank(bean.getBodyType()))
				addActionError(getText("error.choose.body.type"));
		if(StringUtils.isNotBlank(bean.getModelName()) && StringUtils.isNotBlank(bean.getBodyType()) ){
				if(!("editModel".equalsIgnoreCase(bean.getMode()) && "N".equalsIgnoreCase(bean.getStatus()))){
					if(service.checkModelBody(bean) > 0){
						addActionError(getText("error.model.name.and.body.combination.already.exist"));
					}
				}
			}*/
			if(StringUtils.isBlank(bean.getCoreAppCode()))
				addActionError(getText("error.enter.core.app.code"));
			else if(!("editModel".equalsIgnoreCase(bean.getMode()) && "N".equalsIgnoreCase(bean.getStatus()))){
				if(service.checkModelCoreCode(bean) > 0)
					addActionError(getText("error.core.app.code.already.exist"));
			}
				
			if(StringUtils.isNumeric(bean.getCoreAppCode()) && StringUtils.isNotBlank(bean.getCoreAppCode())){
				if(Integer.parseInt(bean.getCoreAppCode()) == 0){
					addActionError(getText("error.enter.valid.core.app.code"));
				}
			}
			if(StringUtils.isBlank(bean.getEffectiveDate()))
				addActionError(getText("error.enter.effective.date"));
			else if(service.diffInDays(bean.getEffectiveDate(),"") >= 0)
				addActionError(getText("error.enter.effective.date.greater.sysdate"));
			if(StringUtils.isBlank(bean.getExpiryDate()))
				addActionError(getText("error.enter.expiry.date"));
			if(StringUtils.isNotBlank(bean.getEffectiveDate()) && StringUtils.isNotBlank(bean.getExpiryDate())){
				if(service.diffInDays(bean.getEffectiveDate(), bean.getExpiryDate()) < 0)
					addActionError(getText("error.effective.date.must.be.less.than.expiry.date"));
			}
			if(StringUtils.isBlank(bean.getReferralStatus()))
				addActionError(getText("error.choose.referral.status"));
			if(StringUtils.isBlank(bean.getStatus()))
				addActionError(getText("error.choose.status"));
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public String modelDetail(){
		bean.setModelList(service.getModelDetail(bean));
		bean.setModalHeaderList(service.getHeaderDetails(bean,"model"));
		return "modelDetail";
	}
	
	public String editModelDetail(){
		bean.setModalHeaderList(service.getHeaderDetails(bean,"model"));
		if("edit".equalsIgnoreCase(bean.getMode()))
			service.getEditModelDetail(bean);
		bean.setBodyTypeList(service.getDropDown(bean,"bodyTypeWithOutVehicleType"));
		return "modelDetail";
	}
	
	public String insModelDetail(){
		validateModelDetail();
		if(!hasActionErrors()){
			String status = service.insertModelDetail(bean);;
			if(StringUtils.isNotBlank(status)){
				addActionError("Unique Constraints Combination Already Exist");
			}else{
				if("edit".equalsIgnoreCase(bean.getMode()))
					addActionMessage("Update Successfully");
				else if("add".equalsIgnoreCase(bean.getMode()))
					addActionMessage("Inserted Successfully");
				bean.setMode("");
				bean.setModelList(service.getModelDetail(bean));
				bean.setModalHeaderList(service.getHeaderDetails(bean,"model"));
			}
		}if(hasActionErrors()){
			bean.setModalHeaderList(service.getHeaderDetails(bean,"model"));
			bean.setBodyTypeList(service.getDropDown(bean,"bodyTypeWithOutVehicleType"));
		}
		return "modelDetail";
	}
	
	private void validateModelDetail() {
		try{
		if(StringUtils.isBlank(bean.getBodyType()))
				addActionError(getText("error.choose.body.type"));
			else if(!("edit".equalsIgnoreCase(bean.getMode()) && "N".equalsIgnoreCase(bean.getStatus()))){
						if(service.checkModelBodyType(bean) > 0)
						addActionError(getText("error.model.name.and.body.combination.already.exist"));
						}
		if(StringUtils.isBlank(bean.getStatus()))
			addActionError(getText("error.choose.status"));
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	//op Cover
	public String opCover() {
		bean.setMode("list");
		bean.setOpCoverList(service.getOpCoverList(bean));
		return "opCoverMaster";
	}
	public String editOpCover(){
		if("edit".equalsIgnoreCase(bean.getMode())){
			service.getEditOpCover(bean);
		}
		return "opCoverMaster";
	}
	public String insOpCover(){
		validateOpCover();
		if(!hasActionErrors()){
			service.insertinsOpCover(bean);
			opCover();
		}else{
			bean.setPolicyTypeList(service.getDropDown(bean, "policyType"));
		}
		return "opCoverMaster";
	}
	private void validateOpCover() {
		if(StringUtils.isBlank(bean.getOpCoverDesc()))
			addActionError(getText("error.enter.opcoverdesc"));
		if(StringUtils.isBlank(bean.getCoreAppCode()))
			addActionError(getText("error.enter.coreappcode"));
		else if(service.checkCoreAppCodeOpCoverExists(bean) >0 )
			addActionError(getText("error.enter.coreappcode.exists"));
		if(StringUtils.isBlank(bean.getEffectiveDate()))
			addActionError(getText("error.enter.effective.date"));
		else if(service.diffInDays(bean.getEffectiveDate(),"") >= 0)
			addActionError(getText("error.enter.effective.date.greater.sysdate"));
		if(StringUtils.isBlank(bean.getStatus()))
			addActionError(getText("error.choose.status"));
	}
	public String opCoverDetail() {
		bean.setMode("list");
		bean.setOpCoverDetailList(service.getOpCoverDetailList(bean));
		return "opCoverDetail";
	}
	public String editOpCoverDetail(){
		bean.setPolicyTypeList(service.getDropDown(bean, "policyType"));
		if("edit".equalsIgnoreCase(bean.getMode())){
			service.getEditOpCoverDetail(bean);
		}
		return "opCoverDetail";
	}
	public String insOpCoverDetail(){
		validateOpCoverDetail();
		if(!hasActionErrors()){
			service.insertinsOpCoverDetail(bean);
			opCoverDetail();
		}else{
			bean.setPolicyTypeList(service.getDropDown(bean, "policyType"));
		}
		return "opCoverDetail";
	}
	private void validateOpCoverDetail() {
		if(StringUtils.isBlank(bean.getGroupId()))
			addActionError(getText("error.groupIdOpCover"));
		if(StringUtils.isBlank(bean.getPolicySubTypeId()))
			addActionError(getText("error.enter.policy.subtype"));
		else if(service.checkOpPolicyExists(bean)) {
			addActionError(getText("error.enter.policy.subtype.exists"));
		}
		if(StringUtils.isBlank(bean.getCoreAppCode()))
			addActionError(getText("error.enter.coreappcode"));
		else if(service.checkCoreAppCodeOpCoverDetExists(bean) >0 )
			addActionError(getText("error.enter.coreappcode.exists"));
		if(StringUtils.isBlank(bean.getIsSelected()))
			addActionError(getText("error.enter.opIsSelected"));
		if(StringUtils.isBlank(bean.getIsAddon()))
			addActionError(getText("error.enter.opIsAddon"));
		if(StringUtils.isBlank(bean.getIsDeletable()))
			addActionError(getText("error.enter.opIsDeletable"));
		if(StringUtils.isBlank(bean.getIsCalcType()))
			addActionError(getText("error.enter.opIsCalcType"));
		if(StringUtils.isBlank(bean.getOpCoverRate()) && "Y".equalsIgnoreCase(bean.getIsCalcType()))
			addActionError(getText("error.enter.opRate"));
		else if(validation.validDouble(bean.getOpCoverRate())!= "" && "Y".equalsIgnoreCase(bean.getIsCalcType()))
			addActionError(getText("error.enter.opRate.num"));
		if(StringUtils.isBlank(bean.getOpCoverAmount()) && "N".equalsIgnoreCase(bean.getIsCalcType()))
			addActionError(getText("error.enter.opCoverAmount"));
		else if(!StringUtils.isNumeric(bean.getOpCoverAmount()) && "N".equalsIgnoreCase(bean.getIsCalcType()))
			addActionError(getText("error.enter.opRate.Amount"));
		/*if(StringUtils.isBlank(bean.getOpDesc()))
			addActionError(getText("error.enter.opDesc"));
		if(StringUtils.isBlank(bean.getEffectiveDate()))
			addActionError(getText("error.enter.effective.date"));
		else if(service.diffInDays(bean.getEffectiveDate(),"") >= 0)
			addActionError(getText("error.enter.effective.date.greater.sysdate"));*/
		if(StringUtils.isBlank(bean.getStatus()))
			addActionError(getText("error.choose.status"));
		if(StringUtils.isBlank(bean.getStartDate()))
			addActionError(getText("error.choose.startDate"));
		if(StringUtils.isBlank(bean.getEndDate()))
			addActionError(getText("error.choose.endDate"));
	}
	public String mfgCountry() {
		bean.setMode("list");
		bean.setMfgCountryList(service.getMfgCountryMasterList(bean));
		return "mfgCountryMaster";
	}
	public String editMfgCountry(){
		if("edit".equalsIgnoreCase(bean.getMode())){
			service.getEditMfgCountryMaster(bean);
		}
		return "mfgCountryMaster";
	}
	public String insMfgCountry(){
		validateMfgCountry();
		if(!hasActionErrors()){
			service.insertinsMfgCountryMaster(bean);
			mfgCountry();
		}
		return "mfgCountryMaster";
	}

	private void validateMfgCountry() {
		if(StringUtils.isBlank(bean.getCountryCode()))
			addActionError(getText("error.enter.countryCode"));
		if(!StringUtils.isNumeric(bean.getCountryCode()))
			addActionError(getText("error.enter.countryCode.numeric"));
		if(StringUtils.isBlank(bean.getCountryName()))
			addActionError(getText("error.enter.countryName"));
		if(StringUtils.isBlank(bean.getNationalityName()))
			addActionError(getText("error.enter.nationalityName"));
		if(StringUtils.isBlank(bean.getCoreAppCode()))
			addActionError(getText("error.enter.coreappcode"));
		else if(service.checkCoreAppCodeCountryExists(bean) >0 )
			addActionError(getText("error.enter.coreappcode.exists"));
		if(StringUtils.isBlank(bean.getEffectiveDate()))
			addActionError(getText("error.enter.effective.date"));
		else if(service.diffInDays(bean.getEffectiveDate(),"") >= 0)
			addActionError(getText("error.enter.effective.date.greater.sysdate"));
		if(StringUtils.isBlank(bean.getStatus()))
			addActionError(getText("error.choose.status"));
	}

	public String deductible() {
		bean.setDeductibleList(service.getdeductible(bean));
		return "deductibleMaster";
	}
	
	public String getDeductibleEdit(){
		if("Edit".equalsIgnoreCase(bean.getMode()))
			service.getDeductibleEdit(bean);
		bean.setPolicyTypeList(service.getDropDown(bean,"policyType"));
		bean.setVehicleTypeList(service.getDropDown(bean,"vehicleType"));
		return "deductibleMaster";
	}
	
	public String getDeductibleInsert(){
		validateDeductible();
		if(!hasActionErrors()){
			service.getDeductibleInsert(bean);
			if("Edit".equalsIgnoreCase(bean.getMode())){
				this.addActionMessage(getText("update.success"));				
			}else{
				this.addActionMessage(getText("insert.success"));
			}
			bean.setMode("");
			bean.setDeductibleList(service.getdeductible(bean));
		}
		else{
			bean.setPolicyTypeList(service.getDropDown(bean,"policyType"));
			bean.setVehicleTypeList(service.getDropDown(bean,"vehicleType"));
		}
		return "deductibleMaster";
	}
	
	private void validateDeductible(){
		if(StringUtils.isEmpty(bean.getBodyType()))
			addActionError(getText("error.bodyType"));
		if(StringUtils.isEmpty(bean.getDeductibleStart()))
			addActionError(getText("error.deductibleStart"));
		if(StringUtils.isEmpty(bean.getDeductibleEnd()))
			addActionError(getText("error.deductibleEnd"));
		if(StringUtils.isEmpty(bean.getDeductibleRate()))
		    addActionError(getText("error.deductibleRate"));
		if(StringUtils.isEmpty(bean.getDiscountAmount()))
		    addActionError(getText("error.discountAmount"));
		else {
			try {
				Double.parseDouble(bean.getDiscountAmount());
			} catch (Exception e) {
				addActionError("Please Enter Valid Deductible Amount (ZMW)");
				e.printStackTrace();
			}
		}
		if(StringUtils.isEmpty(bean.getDeductibleAmountUSD()))
		    addActionError("Please Enter Deductible Amount (USD)");
		else {
			try {
				Double.parseDouble(bean.getDeductibleAmountUSD());
			} catch (Exception e) {
				addActionError("Please Enter Valid Deductible Amount (USD)");
				e.printStackTrace();
			}
		}
		if(StringUtils.isEmpty(bean.getTheftExcessZMW()))
			addActionError("Please Enter Theft Excess");
		else {
			try {
				Double.parseDouble(bean.getTheftExcessZMW());
			} catch (Exception e) {
				addActionError("Please Enter Valid Theft Excess");
				e.printStackTrace();
			}
		}
		/*if(StringUtils.isEmpty(bean.getTheftExcessUSD()))
			addActionError("Please Enter Theft Excess (USD)");
		else {
			try {
				Double.parseDouble(bean.getTheftExcessUSD());
			} catch (Exception e) {
				addActionError("Please Enter Valid Theft Excess (USD)");
				e.printStackTrace();
			}
		}*/
		if(StringUtils.isEmpty(bean.getDriverExcessZMW()))
			addActionError("Please Enter Driver Excess");
		else {
			try {
				Double.parseDouble(bean.getDriverExcessZMW());
			} catch (Exception e) {
				addActionError("Please Enter Valid Driver Excess");
				e.printStackTrace();
			}
		}
		/*if(StringUtils.isEmpty(bean.getDriverExcessUSD()))
			addActionError("Please Enter Driver Excess (USD)");
		else {
			try {
				Double.parseDouble(bean.getDriverExcessUSD());
			} catch (Exception e) {
				addActionError("Please Enter Valid Driver Excess (USD)");
				e.printStackTrace();
			}
		}*/
		if(StringUtils.isEmpty(bean.getClaimExcessZMW()))
			addActionError("Please Enter Claim Excess");
		else {
			try {
				Double.parseDouble(bean.getClaimExcessZMW());
			} catch (Exception e) {
				addActionError("Please Enter Valid Claim Excess");
				e.printStackTrace();
			}
		}
		/*if(StringUtils.isEmpty(bean.getClaimExcessUSD()))
			addActionError("Please Enter Claim Excess (USD)");
		else {
			try {
				Double.parseDouble(bean.getClaimExcessUSD());
			} catch (Exception e) {
				addActionError("Please Enter Valid Claim Excess (USD)");
				e.printStackTrace();
			}
		}*/
		if(StringUtils.isEmpty(bean.getStatus()))
		    addActionError(getText("error.status"));
		if(StringUtils.isEmpty(bean.getEffectiveDate()))
		    addActionError(getText("error.effectiveDate"));
		else if(service.diffInDays(bean.getEffectiveDate(),"") >= 0)
			addActionError(getText("error.enter.effective.date.greater.sysdate"));
		if(StringUtils.isEmpty(bean.getDataModified()))
		    addActionError(getText("error.dataModified"));
		
	}
	
	

//Area Coverage Master
	public String areaCoverage() {
		bean.setAreaCoverageList(service.getAreaCoverage(bean));
		return "areaCoverageMaster";
	}
	
	public String getAreaCoverageEdit(){
		if("Edit".equalsIgnoreCase(bean.getMode()))
			service.getAreaCoverageEdit(bean);
		return "areaCoverageMaster";
	}
	
	public String getAreaCoverageInsert(){
		validateAreaCoverage();
		if(!hasActionErrors()){
			String coverage =service.getAreaCoverageInsert(bean);
			if(StringUtils.isNotBlank(coverage))
				addActionError(getText("error.unique.constraint.combination.already.exist"));
			else{
			if("Edit".equalsIgnoreCase(bean.getMode())){
				this.addActionMessage(getText("update.success"));				
			}else{
				this.addActionMessage(getText("insert.success"));
			}
			bean.setMode("");
			bean.setAreaCoverageList(service.getAreaCoverage(bean));
		}
		}
		return "areaCoverageMaster";
	}
	
	private void validateAreaCoverage(){
		if(StringUtils.isEmpty(bean.getAreaCoverageDescriptionEnglish()))
			addActionError(getText("error.areaCoverageDescriptionEnglish"));
		if(StringUtils.isEmpty(bean.getAreaCoverageCode()))
			addActionError(getText("error.coreAppcCode"));
		 else if(StringUtils.isNumeric(bean.getAreaCoverageCode())){
				if(Integer.parseInt(bean.getAreaCoverageCode()) == 0)
				addActionError(getText("error.enter.valid.core.app.code"));
			}
		if(!("Edit".equalsIgnoreCase(bean.getMode())&& "N".equalsIgnoreCase(bean.getStatus())))
		    if(service.checkAreaCoverageCode(bean) >0 ){
			   addActionError(getText("error.coreAppCode1"));
		       }	
		if(StringUtils.isEmpty(bean.getEffectiveDate()))
		    addActionError(getText("error.effectiveDate"));
		else if(service.diffInDays(bean.getEffectiveDate(),"") >= 0)
			addActionError(getText("error.enter.effective.date.greater.sysdate"));
		if(StringUtils.isEmpty(bean.getStatus()))
		    addActionError(getText("error.status"));
	} 
	
	public String bodyRate() {
		return "bodyRateMaster";
	}
	public String bank() {
		bean.setMode("list");
		bean.setBankMasterList(service.getBankMasterList(bean));
		return "bankMaster";
	}
	public String editBank(){
		if("edit".equalsIgnoreCase(bean.getMode())){
			service.getEditBankMaster(bean);
		}
		return "bankMaster";
	}
	
	public String insBank(){
		validateBankMaster();
		if(!hasActionErrors()){
			String status =  service.insertinsBankMaster(bean);
			if(StringUtils.isNotBlank(status)){
				addActionError("Unique Constraints Combination Already Exist");
			}else{
				if("edit".equalsIgnoreCase(bean.getMode())){
					this.addActionMessage(getText("update.success"));				
				}else{
					this.addActionMessage(getText("insert.success"));
				}
			bank();
		}
		}
		return "bankMaster";
	}

	private void validateBankMaster() {
		if(StringUtils.isBlank(bean.getBankCode()))
			addActionError(getText("error.enter.bankcode"));
		if(StringUtils.isBlank(bean.getAddressA()))
			addActionError(getText("error.enter.address1"));
		if(StringUtils.isBlank(bean.getAddressB()))
			addActionError(getText("error.enter.address2"));
		if(StringUtils.isBlank(bean.getTelephoneNo()))
			addActionError(getText("error.enter.telephone"));
		else if(StringUtils.isNumeric(bean.getTelephoneNo()))
			addActionError(getText("error.enter.telephone1"));
		if(StringUtils.isBlank(bean.getBankCode()))
			addActionError(getText("error.bankcode"));
		else if(!("edit".equalsIgnoreCase(bean.getMode()) && "N".equalsIgnoreCase(bean.getStatus()))){
			if(service.checkBMBankCode(bean)>0 )
				addActionError(getText("error.bankcode.ispresent"));
		else if(StringUtils.isNumeric(bean.getBankCode())){
						if(Long.parseLong(bean.getBankCode()) == 0){
						addActionError(getText("error.invalid.bankcode"));
						}
			}
		}
		if(StringUtils.isBlank(bean.getBankEngName()))
			addActionError(getText("error.enter.bankname"));
		/*if(StringUtils.isBlank(bean.getBankArbName()))
			addActionError(getText("error.enter.bankarbname"));*/
		if(StringUtils.isBlank(bean.getEffectiveDate()))
			addActionError(getText("error.enter.effective.date"));
		else if(service.diffInDays(bean.getEffectiveDate(),"") >= 0)
			addActionError(getText("error.enter.effective.date.greater.sysdate"));
		if(StringUtils.isBlank(bean.getStatus()))
			addActionError(getText("error.choose.status"));
	}

	public String policyType() {
		bean.setTransList(service.getPolicyTypeMasterList(bean));
		return "policyTypeMaster";
	}
	public String editPolicyType(){
		bean.setPolicyTypeList(service.getDropDown(bean, "mainPolicyType"));
		if("edit".equalsIgnoreCase(bean.getMode()))
			service.getEditPolicyTypeMaster(bean);
		return "policyTypeMaster";
	}
	public String insPolicyType(){
		validatePolicyType();
		if(!hasActionErrors()){
			String status = service.insertPolicyTypeMaster(bean);
			if(StringUtils.isNotBlank(status)){
				addActionError("Unique Constraints Combination Already Exist");
			}else{
				if("Edit".equalsIgnoreCase(bean.getMode())){
					this.addActionMessage(getText("update.success"));				
				}else{
					this.addActionMessage(getText("insert.success"));
				}
				bean.setMode("");
				bean.setTransList(service.getPolicyTypeMasterList(bean));
			}
		}if(hasActionErrors()){
			bean.setPolicyTypeList(service.getDropDown(bean, "mainPolicyType"));
		}
		return "policyTypeMaster";
	}
	private void validatePolicyType() {
		try{
			/*if(StringUtils.isBlank(bean.getPolicyTyeId()))
				addActionError(getText("error.choose.policy.type"));*/
			if(StringUtils.isBlank(bean.getCoreAppCode()))
				addActionError(getText("error.enter.core.app.code"));
			else if(!("edit".equalsIgnoreCase(bean.getMode()) && "N".equalsIgnoreCase(bean.getStatus()))){
				if(service.checkPolicyTypeCoreCode(bean) > 0)
					addActionError(getText("error.core.app.code.already.exist"));
			}
			if(StringUtils.isBlank(bean.getEffectiveDate()))
				addActionError(getText("error.enter.effective.date"));
			else if(service.diffInDays(bean.getEffectiveDate(),"") >= 0)
				addActionError(getText("error.enter.effective.date.greater.sysdate"));
			if(StringUtils.isBlank(bean.getStatus()))
				addActionError(getText("error.choose.status"));
			if(StringUtils.isBlank(bean.getPolicyTypeDescEng()))
				addActionError(getText("error.enter.policy.type.desc.eng"));
			if(StringUtils.isNumeric(bean.getCoreAppCode()) && StringUtils.isNotBlank(bean.getCoreAppCode())){
				if(Integer.parseInt(bean.getCoreAppCode()) == 0){
					addActionError(getText("error.enter.valid.core.app.code"));
				}
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public String color() {
		return "colorMaster";
	}
	public String mafCountry() {
		return "mafCountryMaster";
	}
	public String cyclinder() {
		return "cyclinderMaster";
	}
	public String ncb() {
		bean.setTransList(service.getNCBList(bean));
		return "ncbMaster";
	}
	public String editNCB(){
		bean.setPolicyTypeList(service.getDropDown(bean,"policyType"));
		bean.setVehicleTypeList(service.getDropDown(bean, "vehicleType"));
		if("edit".equalsIgnoreCase(bean.getMode()))
			service.getEditNCBMaster(bean);
		return "ncbMaster";
	}
	public String insNCBMaster(){
		validateNCB();
		if(!hasActionErrors()){
			String status = service.insertNCBMaster(bean);
			if(StringUtils.isNotBlank(status)){
				addActionError("Unique Constraints Combination Already Exist");
			}else{
				if("edit".equalsIgnoreCase(bean.getMode())){
					this.addActionMessage(getText("update.success"));				
				}else{
					this.addActionMessage(getText("insert.success"));
				}
				bean.setMode("");
				bean.setTransList(service.getNCBList(bean));
			}
		}if(hasActionErrors()){
			bean.setPolicyTypeList(service.getDropDown(bean,"policyType"));
			bean.setVehicleTypeList(service.getDropDown(bean, "vehicleType"));
		}
		return "ncbMaster";
	}
	private void validateNCB() {
		try{
			if(StringUtils.isBlank(bean.getNcbYear()))
				addActionError(getText("error.enter.ncb.year"));
			/*else if(service.checkNCBYear(bean) > 0)
				addActionError(getText("error.ncb.year.already.exist"));*/
			if(StringUtils.isBlank(bean.getVehicleType()))
				addActionError(getText("error.enter.vehicle.type")); 
			if(StringUtils.isBlank(bean.getPolicyType()))
				addActionError(getText("error.enter.policy.type"));
			/*if(!("edit".equalsIgnoreCase(bean.getMode())& "N".equalsIgnoreCase(bean.getStatus()))){
				if(StringUtils.isNotBlank(bean.getNcbYear()) && StringUtils.isNotBlank(bean.getVehicleType())){
					if(service.checkNCBVehicleUsage(bean) > 0){
						addActionError(getText("error.vehicle.usage.and.ncb.year.combination.already.exist"));
					}
				}
			}*/
			if(StringUtils.isBlank(bean.getSuminsuredStart()))
				addActionError(getText("error.enter.ins.value.start"));
			else if(StringUtils.isAlphaSpace(bean.getSuminsuredStart()))
				addActionError(getText("error.enter.numeric.ins.value.start"));
			if(StringUtils.isBlank(bean.getSuminsuredEnd()))
				addActionError(getText("error.enter.ins.value.end"));
			else if(StringUtils.isAlphaSpace(bean.getSuminsuredEnd()))
				addActionError(getText("error.enter.numeric.ins.value.end"));
			if(StringUtils.isNotBlank(bean.getSuminsuredStart()) && StringUtils.isNotBlank(bean.getSuminsuredEnd())){
				if(Double.parseDouble(bean.getSuminsuredStart()) >= Double.parseDouble(bean.getSuminsuredEnd()))
					addActionError(getText("error.enter.ins.value.start.must.be.less.than.ins.value.end"));	
			}
			if(StringUtils.isBlank(bean.getNcbRate()))
				addActionError(getText("error.enter.ncb.rate"));
			if(StringUtils.isBlank(bean.getCoreAppCode()))
				addActionError(getText("error.enter.core.app.code"));
			else if(!("edit".equalsIgnoreCase(bean.getMode())& "N".equalsIgnoreCase(bean.getStatus()))){
				if(service.checkNCBCoreCode(bean) > 0)
					addActionError(getText("error.core.app.code.already.exist"));
			}
			if(StringUtils.isNumeric(bean.getCoreAppCode()) && StringUtils.isNotBlank(bean.getCoreAppCode())){
				if(Integer.parseInt(bean.getCoreAppCode()) == 0){
					addActionError(getText("error.enter.valid.core.app.code"));
				}
			}
			if(StringUtils.isBlank(bean.getEffectiveDate()))
				addActionError(getText("error.enter.effective.date"));
			else if(service.diffInDays(bean.getEffectiveDate(),"") >= 0)
				addActionError(getText("error.enter.effective.date.greater.sysdate"));
			if(StringUtils.isBlank(bean.getStatus()))	
				addActionError(getText("error.choose.status"));
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
//BODY TYPE MASTER
	public String bodyType() {
		if("list".equalsIgnoreCase(bean.getMode())){
				bean.setBodyTypeMasterList(service.BodyTypeMasterList(bean,branchCode));
		}
		if("edit".equalsIgnoreCase(bean.getMode())){
				service.editbodyTypeMaster(bean);
		}
		if("update".equalsIgnoreCase(bean.getMode()) || "addNew".equalsIgnoreCase(bean.getMode())){	
			validateBodyTypeMaster();
			if(!hasActionErrors()){
				String status=service.updatebodyTypeMaster(bean);
				if(StringUtils.isNotBlank(status)){
					addActionError(getText("error.comm.exist"));
				}else{
					if("update".equalsIgnoreCase(bean.getMode()))
						addActionMessage("Update Successfully");
					else if("addNew".equalsIgnoreCase(bean.getMode()))
						addActionMessage("Inserted Successfully");
				bean.setBodyTypeMasterList(service.BodyTypeMasterList(bean,branchCode));
				bean.setMode("list");
				}
			}
			else{
				if("update".equalsIgnoreCase(bean.getMode()) ){
					bean.setMode("edit");
				}
				if("addNew".equalsIgnoreCase(bean.getMode())){
					bean.setMode("add");
				}
				}
		}
		if("vehicleedit".equalsIgnoreCase(bean.getMode())){
				bean.setVehicleListforBodyTypeMaster(service.vehicleList(bean));
				service.editvehiclebodytypeMaster(bean);
		}
		if("vehicleadd".equalsIgnoreCase(bean.getMode())){
			validatevehicleforBodyMaster();
			if(!hasActionErrors()){
				service.vehicleInsert(bean);
				addActionMessage("Inserted Successfully");
				bean.setMode("vehicle");
				bean.setVehicleLinkforBodyTypeMaster(service.vehiclelinkforBodyTypeMaster(bean));
			}
			else{
				bean.setMode("vehicleedit");
				bean.setVehicleListforBodyTypeMaster(service.vehicleList(bean));
			}
		}
		return "bodyTypeMaster";
		
	}
	public String modify(){
		if("vehicleadd".equalsIgnoreCase(bean.getMode())){
			validatevehicleforBodyMaster();
				if(!hasActionErrors()){
					String status=service.vehicleInsertBodyTyeId(bean);
					if(StringUtils.isBlank(status)){
						addActionMessage("Vehicle Details Added Successfully");
					}
					bean.setMode("vehicle");
					bean.setVehicleLinkforBodyTypeMaster(service.vehiclelinkforBodyTypeMaster(bean));	
					}
				else{
					bean.setMode("vehicleedit");
					bean.setVehicleListforBodyTypeMaster(service.vehicleList(bean));
				}
			}
		else{
				bean.setVehicleListforBodyTypeMaster(service.vehicleList(bean));
				service.editvehiclebodytypeMaster(bean);
			}
		return "bodyTypeMaster";
	}

	public String vehicleLinkBodyType(){
		bean.setCount(service.count(bean));
		if(bean.getCount()==0){
			bean.setVehicleListforBodyTypeMaster(service.vehicleList(bean));
			bean.setReqFrom("addBodyVehicleLink");
			/*if(StringUtils.isEmpty(bean.getVehiclebodyTypeId())){
				service.getMaxofTypeBodyId(bean);
			}*/
			service.bodyName(bean);
			bean.setMode("vehicleedit");
		}
		else{
			bean.setMode("vehicle");
			bean.setVehicleLinkforBodyTypeMaster(service.vehiclelinkforBodyTypeMaster(bean));
		}
		return "bodyTypeMaster";
	}
	
	public String addVehicleLinkBodyType(){
		bean.setVehicleListforBodyTypeMaster(service.vehicleList(bean));
		service.bodyName(bean);
		bean.setMode("vehicleedit");
		return "bodyTypeMaster";
	}

	private void validatevehicleforBodyMaster() {
		try{
			if(StringUtils.isBlank(bean.getBodyTypeName()))
				addActionError(getText("error.enter.bodytype.name"));
			if(StringUtils.isBlank(bean.getVehicleIdforBodyType()))
				addActionError(getText("error.choose.vehicyle"));
			if(StringUtils.isBlank(bean.getStatus()))
				addActionError(getText("error.choose.status"));
			if ("Y".equalsIgnoreCase(bean.getStatus())){
				if( service.count(bean) != 0 ){
					addActionError(getText("error.vehiclelink.exits"));
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	private void validateBodyTypeMaster() {
		try{
			if(StringUtils.isBlank(bean.getBodyTypeName()))
				addActionError(getText("error.enter.bodytype.name"));
			if(StringUtils.isBlank(bean.getCoreAppCode())){
				addActionError(getText("error.choose.coreAppCode"));
			}else{
				bean.setCoreappcodecount(service.coreappcodecount(bean));
				if("update".equalsIgnoreCase(bean.getMode())){
					if(bean.getCoreappcodecount()>0 && "Y".equalsIgnoreCase(bean.getStatus())){
						addActionError(getText("error.choose.coreAppCodeCount"));
					}
				}
				else{
					if(0!=bean.getCoreappcodecount()){
						addActionError(getText("error.choose.coreAppCodeCount"));
					}
				}
			}
			if(StringUtils.isBlank(bean.getEffectiveDate()))
				addActionError(getText("error.enter.effective.date"));
			else if(service.diffInDays(bean.getEffectiveDate(),"") >= 0)
				addActionError(getText("error.enter.effective.date.greater.sysdate"));
			if(StringUtils.isBlank(bean.getStatus()))
				addActionError(getText("error.choose.status"));
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public String ajax(){
		List<Map<String,Object>> validationList =new ArrayList<Map<String,Object>>();
		if("bodyType".equalsIgnoreCase(bean.getReqFrom())){
			bean.setBodyTypeList(service.getDropDown(bean,"bodyType"));
		}
		else if("checkVehicleAge".equalsIgnoreCase(bean.getReqFrom())){
			if(Integer.parseInt(bean.getVehicleStartAge()) >= Integer.parseInt(bean.getVehicleEndAge())){
				addActionError(getText("error.enter.vehicle.start.less.than.end.age"));
			}else{
				validationList = service.getAjaxValidationList(bean);
			}
		}
		else if("checkClaim".equalsIgnoreCase(bean.getReqFrom())){
			if(Integer.parseInt(bean.getClaimStart()) > Integer.parseInt(bean.getClaimEnd())){
				addActionError(getText("error.enter.claim.start.less.than.end.age"));
			}else{
				validationList = service.getAjaxValidationList(bean);
			}
		}
		else if("checkSeatingTon".equalsIgnoreCase(bean.getReqFrom())){
			if(Integer.parseInt(bean.getSeatingTonStart()) >= Integer.parseInt(bean.getSeatingTonEnd())){
				addActionError(getText("error.enter.seating.ton.start.less.than.seating.ton.end"));
			}else{
				validationList = service.getAjaxValidationList(bean);
			}
		}
		else if ("checkCCGVWRange".equalsIgnoreCase(bean.getReqFrom())){
			if(Integer.parseInt(bean.getCCGVWStartRange()) >= Integer.parseInt(bean.getCCGVWEndRange())){
				addActionError(getText("error.enter.cc.gvw.start.range.less.than.cc.gvw.end.range"));
			}else{
				validationList = service.getAjaxValidationList(bean);
			}
		}
		else if ("checkUAELiscence".equalsIgnoreCase(bean.getReqFrom())){
			if(Integer.parseInt(bean.getUAELiscenceStart()) >= Integer.parseInt(bean.getUAELiscenceEnd())){
				addActionError(getText("error.enter.uae.license.start.less.than.uae.license.end"));
			}else{
				validationList = service.getAjaxValidationList(bean);
			}
		}
		else if ("checkDriverAge".equalsIgnoreCase(bean.getReqFrom())){
			if(Integer.parseInt(bean.getDriverAgeFrom()) >= Integer.parseInt(bean.getDriverAgeTo())){
				addActionError(getText("error.enter.driver.age.from.less.than.driver.age.to"));
			}else{
				validationList = service.getAjaxValidationList(bean);
			}
		}
		else if ("checkSumInsureSd".equalsIgnoreCase(bean.getReqFrom())){
			if(Integer.parseInt(bean.getSuminsuredStart()) >= Integer.parseInt(bean.getSuminsuredEnd())){
				addActionError(getText("error.enter.suminsured.start.less.than.suminsured.end"));
			}else{
				validationList = service.getAjaxValidationList(bean);
			}
		}
		else if("checkSeatingCylinder".equalsIgnoreCase(bean.getReqFrom())){
			if(Integer.parseInt(bean.getSeatingCylinderStart()) >= Integer.parseInt(bean.getSeatingCylinderEnd())){
				addActionError(getText("error.enter.seating.cylinder.start.must.be.less.than.seating.cylinder.end"));
			}else{
				validationList = service.getAjaxValidationList(bean);
			}
		}
		if(validationList!=null && validationList.size()>0){
			bean.setValidationList(validationList);
		}
		return "ajax";
	}
	public List<Map<String,Object>> getCountryList() {
		return service.getDropDown(bean,"country");
	}
	
	//Documnet Master
	public String getDocument(){
		bean.setDocumentList(service.getDocument(bean));
		return "document";
	}
		
	public String getDocumentEdit(){
		if(bean.getMode()!= null && "Edit".equalsIgnoreCase(bean.getMode())){
			service.getDocumentEdit(bean);
			}
		else if(bean.getMode() != null && "Add".equalsIgnoreCase(bean.getMode())){
			service.getMaxDocumentId(bean);
		}
		bean.setPolicyTypeCheckBox(service.getDropDown(bean,"policyType"));
		bean.setDocumentdDrpdwn(service.getDropDown(bean, "DocApp"));
		return "document";
	}
	
	public String getDocumentInsert(){
		String doc = "";
		if(bean.getDocpolicyType() != null && bean.getDocpolicyType().length>0){
			for(String str: bean.getDocpolicyType())
				doc = doc+","+str; 
			doc = doc.substring(1);
		}
		bean.setDocPolicy(doc);
		validateDocument();
		if(!hasActionErrors()){
			if("Edit".equalsIgnoreCase(bean.getMode())){
				service.getDocumentUpdate(bean);
				this.addActionMessage(getText("update.success"));
				bean.setMode("");
				bean.setDocumentList(service.getDocument(bean));
				}
			else {
				if("Add".equalsIgnoreCase(bean.getMode())){
					service.getDocumentUpdate(bean);
					this.addActionMessage(getText("insert.success"));
					bean.setMode("");
					bean.setDocumentList(service.getDocument(bean));
				}
				}
				return "document";
			}
			else{
				  bean.setPolicyTypeCheckBox(service.getDropDown(bean,"policyType"));
				  bean.setDocumentdDrpdwn(service.getDropDown(bean, "DocApp"));
			}
			return "document";
		}
	
	private void validateDocument(){
		try{
			/*if(bean.getDocpolicyType().length > 0){
				bean.setPolicyType(StringUtils.join(bean.getDocpolicyType(),","));
			}*/
			if(ArrayUtils.isEmpty(bean.getDocpolicyType()))
			addActionError(getText("error.policyType"));
			if(StringUtils.isEmpty(bean.getDocumentDescription()))
			addActionError(getText("error.documentDescription"));
			if(StringUtils.isEmpty(bean.getDocumentApplicable()))
			addActionError(getText("error.documentApplicable"));
			if(StringUtils.isEmpty(bean.getDisplayOrder()))
			addActionError(getText("error.DocumentDisplayOrder"));
			else if(service.checkDocumentDisplayOrder(bean)>0)
			    addActionError(getText("error.DocumentDisplayOrder1"));
			else if(StringUtils.isNumeric(bean.getDisplayOrder())){
			if(Integer.parseInt(bean.getDisplayOrder()) == 0){
			addActionError(getText("error.DisplayOrder2"));
			}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		}
	
	//Vehicle Type Master
	public String vehicleType() {
		bean.setVehicleTypeList(service.getVehicleType(bean));
		return "vehicleTypeMaster";
	}
	
	public String getVehicleTypeEdit(){
		if( bean.getMode()!= null && "edit".equalsIgnoreCase(bean.getMode()))
			service.getEditVehicleType(bean);
		return "vehicleTypeMaster";
		
	}
		
	public String vehicleTypeInsert(){
		validateVehicleType();
		if(!hasActionErrors()){
			if(!hasActionErrors()){
				String status = service.insertVehicleTypeMaster(bean);
				if(StringUtils.isNotBlank(status)){
					addActionError("Unique Constraints Combination Already Exist");
				}else
				if("edit".equalsIgnoreCase(bean.getMode())){
					this.addActionMessage(getText("update.success"));				
				}else{
					this.addActionMessage(getText("insert.success"));
				}
				bean.setMode("");
				bean.setVehicleTypeList(service.getVehicleType(bean));
				}
			}
	return "vehicleTypeMaster";
	}

	private void validateVehicleType() {
		
		if(StringUtils.isEmpty(bean.getVehicletypeDesc()))
			addActionError(getText("error.vtype.desc"));
		
		if(StringUtils.isBlank(bean.getCoreappCode()))
			addActionError(getText("error.coreappcode"));
		else if(!("edit".equalsIgnoreCase(bean.getMode()) && "N".equalsIgnoreCase(bean.getStatus()))){
			if(service.checkVTCoreAppCode(bean)>0 )
				addActionError(getText("error.coreappcode.ispresent"));
		else if(StringUtils.isNumeric(bean.getCoreappCode())){
						if(Integer.parseInt(bean.getCoreappCode()) == 0){
						addActionError(getText("error.enter.valid.core.app.code"));
						}
			}
		}
		if(StringUtils.isEmpty(bean.getEffectiveDate()))
			addActionError(getText("error.effectiveDate"));
		else if(service.diffInDays(bean.getEffectiveDate(),"") >= 0)
			addActionError(getText("error.enter.effective.date.greater.sysdate"));
		if(StringUtils.isEmpty(bean.getStatus()))
			addActionError(getText("error.status"));
		if(StringUtils.isEmpty(bean.getReferralStatus()))
			addActionError(getText("error.refferal.status"));
	}
	
	//Bank Finance Master
	public String bankFinance() {
		bean.setBankFinanceList(service.getbankFinance(bean));
		return "bankFinanceMaster";
	}
	
	public String getBankFinanceEdit(){
		if( bean.getMode()!=null && "edit".equalsIgnoreCase(bean.getMode()))
			service.getEditBankFinance(bean);
		return "bankFinanceMaster";
		
	}
		
	public String bankFinanceInsert(){
		validateBankFinance();
			if(!hasActionErrors()){
				String status = service.insertBankFinanceMaster(bean);
				if(StringUtils.isNotBlank(status)){
					addActionError("Unique Constraints Combination Already Exist");
				}else{
					if("edit".equalsIgnoreCase(bean.getMode())){
						this.addActionMessage(getText("update.success"));				
					}else{
						this.addActionMessage(getText("insert.success"));
					}
					bean.setMode("");
					bean.setBankFinanceList(service.getbankFinance(bean));
					}
				}
	return "bankFinanceMaster";
	}

	private void validateBankFinance() {
		
		if(StringUtils.isBlank(bean.getBankCode())){
			addActionError(getText("error.bankcode"));
		}else if(!("edit".equalsIgnoreCase(bean.getMode()) && "N".equalsIgnoreCase(bean.getStatus()))){
				if(service.checkBFMBankCode(bean)>0 )
					addActionError(getText("error.bankcode.ispresent"));
				}
		else if(StringUtils.isNumeric(bean.getBankCode())){
				if(Integer.parseInt(bean.getBankCode()) == 0)
						addActionError(getText("error.invalid.bankcode"));
				}
		if(StringUtils.isBlank(bean.getAddressA()))
			addActionError(getText("error.enter.address1"));
		if(StringUtils.isBlank(bean.getAddressB()))
			addActionError(getText("error.enter.address2"));
		if(StringUtils.isBlank(bean.getTelephoneNo()))
			addActionError(getText("error.enter.telephone"));
		else if(StringUtils.isNumeric(bean.getTelephoneNo()))
			addActionError(getText("error.enter.telephone1"));
		if(StringUtils.isEmpty(bean.getBankNameEnglish()))
			addActionError(getText("error.bankname"));
		if(StringUtils.isEmpty(bean.getEffectiveDate()))
			addActionError(getText("error.effectiveDate"));
		else if(service.diffInDays(bean.getEffectiveDate(),"") >= 0)
			addActionError(getText("error.enter.effective.date.greater.sysdate"));
		if(StringUtils.isEmpty(bean.getStatus()))
			addActionError(getText("error.status"));
		
	}	
	
	//Group Master
	public String group() {
		bean.setGroupList(service.getGroup(bean));
		return "groupMaster";
	}
	
	public String groupEdit(){
		if("edit".equalsIgnoreCase(bean.getMode()))
			service.getEditGroup(bean);
		return "groupMaster";
		
	}
	
	public String insertGroup(){
		validateGroup();
		if(!hasActionErrors()){
			String status =service.getInsertGroup(bean);
			if(StringUtils.isNotBlank(status)){
				addActionError("Unique Constraints Combination Already Exist");
			}else if("edit".equalsIgnoreCase(bean.getMode())){
				this.addActionMessage(getText("update.success"));				
			}else{
				this.addActionMessage(getText("insert.success"));
			}
			bean.setMode("");
			bean.setGroupList(service.getGroup(bean));
			}
		return "groupMaster";
	}
	
	public List<Map<String, Object>> getGroupOpCoverList(){
		return service.getDropDown(bean,"groupOpcover");
	}
	
	private void validateGroup() {
		if(StringUtils.isEmpty(bean.getGroupDescEng()))
			addActionError(getText("error.groupDescEng"));
		if(StringUtils.isEmpty(bean.getEffectiveDate()))
			addActionError(getText("error.effectiveDate"));
		else if(service.diffInDays(bean.getEffectiveDate(),"") >= 0)
			addActionError(getText("error.enter.effective.date.greater.sysdate"));
		if(StringUtils.isEmpty(bean.getStatus()))
			addActionError(getText("error.status"));
		if(StringUtils.isEmpty(bean.getDisplayOrder()))
			addActionError(getText("error.displayOrder"));
		else if(!("edit".equalsIgnoreCase(bean.getMode()) && "N".equalsIgnoreCase(bean.getStatus()))){
				if(service.checkDisplayOrder(bean)>0 )
			addActionError(getText("error.displayorder.ispresent"));
		}else if(StringUtils.isNumeric(bean.getDisplayOrder())){
			if(Integer.parseInt(bean.getDisplayOrder()) == 0){
				addActionError(getText("error.invalid.displayorder"));
				}
			}
	}
	//Quart master
	public String quarter(){
		bean.setQuarterList(service.getQuarter(bean));
		return "quarterMaster";
	}
	public String  editQuarter(){
		bean.setPolicyTypeList(service.getDropDown(bean,"policyType"));
		if("edit".equalsIgnoreCase(bean.getMode()))
			service.getQuarterEdit(bean);
		//bean.setMode("");
		return "quarterMaster";
	}
	public String insertQuarter()
	{
		validatQuarter();
		if(!hasActionErrors())
		{
			String status=service.getInsertQuarter(bean);
			if(StringUtils.isNotBlank(status))
				{
				addActionError("Unique Constraints Combination Already Exist");
				}
				if("edit".equalsIgnoreCase(bean.getMode()))
				
				this.addActionMessage(getText("update.success"));				
			else
				this.addActionMessage(getText("insert.success"));
			bean.setMode("");
			bean.setQuarterList(service.getQuarter(bean));
			}
		else{
			bean.setPolicyTypeList(service.getDropDown(bean,"policyType"));
		}
		return "quarterMaster";	
	}
	public void validatQuarter()
	{
		if(StringUtils.isEmpty(bean.getPolicyType()))
			addActionError(getText("error.policytype"));
		if(StringUtils.isEmpty(bean.getStartRange()))
			addActionError(getText("error.startrange"));
		if(StringUtils.isEmpty(bean.getStatus()))
			addActionError(getText("error.status"));
		if(StringUtils.isEmpty(bean.getQuarterId()))
			addActionError(getText("error.quater"));
		if(StringUtils.isEmpty(bean.getEffectiveDate()))
			addActionError(getText("error.effectiveDate"));
		else if(service.diffInDays(bean.getEffectiveDate(),"") >= 0)
			addActionError(getText("error.enter.effective.date.greater.sysdate"));
		if(StringUtils.isEmpty(bean.getEndRange()))
			addActionError(getText("error.endrange"));
		else if(Integer.parseInt(bean.getStartRange())>=Integer.parseInt(bean.getEndRange())&&StringUtils.isNotEmpty(bean.getStartRange()))
		{
			addActionError(getText("error.low.startrange"));
		}
		if(!("edit".equalsIgnoreCase(bean.getMode()) && "N".equalsIgnoreCase(bean.getStatus()))){
			if(service.getQuarterExists(bean)>0)
			{
					addActionError(getText("error.quater.exist"));	
			}
		}
	}
	
	
	//Constatnt Master
	public String constant(){
		bean.setConstantList(service.getConstantDetailsList(bean));
		return "constantMaster";
	}
	
	public String constantEdit(){
		if("edit".equalsIgnoreCase(bean.getMode()))
			service.getEditConstant(bean);
		else{
			bean.setItemTypeList(service.getDropDown(bean,"itemType"));
		}
		return "constantMaster";
		
	}
	
	public String insertConstant(){
		validateConstant();
		if(!hasActionErrors()){
			service.getInsertConstant(bean);
				if("edit".equalsIgnoreCase(bean.getMode())){
					this.addActionMessage(getText("update.success"));				
				}else{
					this.addActionMessage(getText("insert.success"));
				}
			bean.setMode("");
			bean.setConstantList(service.getConstantDetailsList(bean));
		}
		else{
		bean.setItemTypeList(service.getDropDown(bean,"itemType"));
		}
		return "constantMaster";
	}

	private void validateConstant() {
			if(StringUtils.isEmpty(bean.getItemType()))
			addActionError(getText("error.choose.itemType"));
			if("***ADD NEW***".equalsIgnoreCase(bean.getItemType())){
				if(StringUtils.isEmpty(bean.getItem()))
					addActionError(getText("error.enter.itemType"));
			}
			if(StringUtils.isEmpty(bean.getItemValue()))
			addActionError(getText("error.itemValue"));
			if(StringUtils.isEmpty(bean.getItemDesc()))
			addActionError(getText("error.itemDesc"));
			if(StringUtils.isEmpty(bean.getStatus()))
			addActionError(getText("error.status"));
	}
	
	// Mobile Upload Images
	public String mUploadImg(){
		if("RSAIssuer".equalsIgnoreCase(userType)){
			return "mupoladimgOP";
		}else{
			return "mupoladimg";
		}
	}
	
	public String getMobileUploadImg(){
		validateDate();
		if(!hasActionErrors()){
			bean.setMoUploadImgList(service.getMoUploadImg(bean));
			bean.setMode("list");
		}
		if("RSAIssuer".equalsIgnoreCase(userType)){
			return "mupoladimgOP";
		}else{
			return "mupoladimg";
		}
	}
	
	public long diffInDays(String startDate,String endDate){
		long result=0;
		if(!StringUtils.isEmpty(startDate)&& !StringUtils.isEmpty(endDate)){
		try{
			Date date = new Date();
	        Calendar cal1 = Calendar.getInstance();
	        Calendar cal2 = Calendar.getInstance();
	        SimpleDateFormat sfd = new SimpleDateFormat("dd/MM/yyyy");
	        cal1.setTime(sfd.parse(startDate));
	        if(StringUtils.isBlank(endDate))
	        	cal2.setTime(sfd.parse(sfd.format(date)));
	        else
	        cal2.setTime(sfd.parse(endDate));
	        long milis1 = cal1.getTimeInMillis();
	        long milis2 = cal2.getTimeInMillis();
	        long diff = milis2 - milis1;
	        result = diff / (24 * 60 * 60 * 1000);
		}catch (Exception e) {
			LogManager.debug("Exception Occured @ "+e);
			e.printStackTrace();
		}
	}
	
	return result;
}
	
	private void validateDate(){
		long date = diffInDays(bean.getEndDate(),bean.getStartDate());
			if (StringUtils.isEmpty(bean.getStartDate())){
				addActionError(getText("error.choose.startDate"));
			}else if (StringUtils.isEmpty(bean.getEndDate())){
				addActionError(getText("error.choose.endDate"));
			}else if (date > 0){
				 addActionError(getText("End date must be greater than Start Date"));
			}
	}
	
	/*private void validateMobileUploadImg() {
	if(StringUtils.isBlank(bean.getStartDate()))
		addActionError(getText("error.choose.startDate"));
	if(StringUtils.isBlank(bean.getEndDate()))
		addActionError(getText("error.choose.endDate"));
	}*/
	
	public String getMobileUploadImgView(){
		service.getMoUploadImgView(bean);
		bean.setMoUploadImgListView(service.getMoUploadImgViewList(bean));
		bean.setMode("viewlist");
		if("RSAIssuer".equalsIgnoreCase(userType)){
			return "mupoladimgOP";
		}else{
			return "mupoladimg";
		}
	}
	public String downloadAttachment() {
		try {
			service.getFilePath(bean);
			//bean.setFilePath(getApplicationPath()+"MobileUploadedImages/" + bean.getFileName());
			inputStream = new FileInputStream(bean.getFilePath());
			bean.setFileName(bean.getFileName());
		} catch(Exception exception) {
			LogManager.debug(exception);
		}
		return "stream";
	}
	
	/*private String getApplicationPath(){
		String[] resource = this.getClass().getResource("").toString().replace("file:/", "").split("WEB-INF/");
		if(SystemUtils.IS_OS_LINUX) {
			return "/"+resource[0].replaceAll("%20", " ");
		}else{
			return resource[0].replaceAll("%20", " ");
		}
	}*/
	
	//Notification Master
	public String getNotify(){
		bean.setNotifyList(service.getNotifyList(bean));
		return "moNotifyMaster";
	}
	
	public String notifyEdit(){
		if("edit".equalsIgnoreCase(bean.getMode())){
			service.getEditNotify(bean);
		}
		return "moNotifyMaster";
	}
	
	public String insertNotify(){
		validateNotify();
		validateDate();
		if(!hasActionErrors()){
			service.getInsertNotify(bean);
				if("edit".equalsIgnoreCase(bean.getMode())){
					this.addActionMessage(getText("update.success"));				
				}else{
					this.addActionMessage(getText("insert.success"));
				}
			bean.setMode("");
			bean.setNotifyList(service.getNotifyList(bean));
		}
		return "moNotifyMaster";
	}

	private void validateNotify() {
			if(StringUtils.isEmpty(bean.getNotifyDesc()))
			addActionError(getText("Enter Msge Description"));
			if(StringUtils.isEmpty(bean.getStatus()))
			addActionError(getText("error.status"));
	}
	
	//Payment Bank Master
	public String getPaymentBank(){
		bean.setPaymentBankList(service.getPaymentBankList(bean));
		return "paymentBankMaster";
	}
	
	public String paymentBankEdit(){
		bean.setPaymentTypeList(service.getDropDown(bean,"paymentType"));
		if("edit".equalsIgnoreCase(bean.getMode())){
			service.getEditPaymentBank(bean);
		}
		return "paymentBankMaster";
	}
	
	public String insertPaymentBank(){
		String result="";
		validatePaymentBank();
		if(!hasActionErrors()){
			result=service.getInsertPaymentBank(bean);
			if(StringUtils.isBlank(result)){
				if("edit".equalsIgnoreCase(bean.getMode())){
					this.addActionMessage(getText("update.success"));				
				}else{
					this.addActionMessage(getText("insert.success"));
				}
			bean.setMode("");
			bean.setPaymentBankList(service.getPaymentBankList(bean));
			}else{
				addActionError("Unique Constraints Combination Already Exist");
				bean.setPaymentTypeList(service.getDropDown(bean,"paymentType"));
			}
		}else{
			bean.setPaymentTypeList(service.getDropDown(bean,"paymentType"));
		}
		return "paymentBankMaster";
	}

	private void validatePaymentBank() {
		if(StringUtils.isEmpty(bean.getPaymentType()))
			addActionError(getText("error.payment.type"));
		if(StringUtils.isEmpty(bean.getCurrencyType()))
			addActionError(getText("error.currencytype"));
		if(StringUtils.isEmpty(bean.getAccHolder()))
			addActionError(getText("error.acc.holder"));
		if(StringUtils.isEmpty(bean.getAccNumber()))
			addActionError(getText("error.acc.number"));
		if(StringUtils.isEmpty(bean.getBankEngName()))
			addActionError(getText("error.bank.name"));
		if(StringUtils.isEmpty(bean.getBankCode()))
			addActionError(getText("error.bank.code"));
		if(StringUtils.isEmpty(bean.getBankBranch()))
			addActionError(getText("error.bank.branch"));
		if(StringUtils.isEmpty(bean.getSwiftCode()))
			addActionError(getText("error.swift.code"));
		if(StringUtils.isBlank(bean.getEffectiveDate()))
			addActionError(getText("error.enter.effective.date"));
		else if(service.diffInDays(bean.getEffectiveDate(),"") >= 0)
			addActionError(getText("error.enter.effective.date.greater.sysdate"));
		if(StringUtils.isEmpty(bean.getStatus()))
			addActionError(getText("error.status"));
		if(!hasActionErrors()){
			if(service.checkPBExit(bean) && "add".equalsIgnoreCase(bean.getMode())){
				addActionError(getText("error.currency.payment"));
			}
		}
	}

	// Rating Upload Start //	
	public String init(){
		try {
			bean.setMode("factList");
			bean.setPolicyTypeList(service.getDropDown(bean,"policyType"));
			bean.setVehicleTypeList(service.getDropDown(bean,"vehicleType"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "rateConfig";
	}
	
	public String factorList(){
		try {
			bean.setPolicyTypeList(service.getDropDown(bean,"policyType"));
			bean.setVehicleTypeList(service.getDropDown(bean,"vehicleType"));
			if(StringUtils.isBlank(bean.getBrokerId()))
				addActionError(getText("Please Choose Broker"));
			if(StringUtils.isBlank(bean.getBranchCode()))
				addActionError(getText("Please Choose Branch Name"));
			if(StringUtils.isBlank(bean.getPolicyType()))
				addActionError(getText("error.enter.policy.type"));
			if(StringUtils.isBlank(bean.getVehicleType()))
				addActionError(getText("error.enter.vehicle.type"));
			/*if(StringUtils.isBlank(bean.getBodyType()))
				addActionError(getText("error.choose.body.type"));*/
			
			if(!hasActionErrors())
				//bean.setMotorRateList(service.getMotorRateList(bean));
				bean.setMotorFactorList(service.getMotorFactorList(bean));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "rateConfig";
	}
	
	public String rateList(){
		try {
			service.setLabelValues(bean);
			bean.setMotorRateList(service.getMotorRateList(bean));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "rateConfig";
	}
	private HttpServletRequest getRequest() {
		return ServletActionContext.getRequest();
	}
	
	public String editRate(){
		try {
			service.editRateDetails(bean);
			getRequest().setAttribute(DBConstants.FIELD, "editRate");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return DBConstants.DROPDOWN;
	}
	public String updateRate(){
		try {
			if(StringUtils.isBlank(bean.getRate()))
				addActionError("Please Enter Rate");
			else
				try{
					Double.parseDouble(bean.getRate());
				}catch(Exception e){
					addActionError("Please Enter Valid Rate");
				}
				
			if(!hasActionErrors()){
				int res=service.updateRateModified(bean);
				if(res>0)
					addActionMessage("Updated Successfully");
				
				else
					addActionError("Update Failed");
			}
			getRequest().setAttribute(DBConstants.FIELD, "editRate");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return DBConstants.DROPDOWN;
	}
	public String factorUpload(){
		try {
			bean.setMode("rateUpload");
			//bean.setFactorRateMasterList(service.getFactorRateMasterList(bean));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "rateConfig";
	}
	
	public String upldFile(){
		String result="rateConfig";
		validateUploadXls();
		bean.setMode("rateUpload");
		if(!hasActionErrors()){
			UploadAction upload = new UploadAction();
			UploadService updservice=new UploadService();
			String tranId=updservice.getTranactionId();
			bean.setTranID(tranId);
			if(StringUtils.isNotBlank(tranId)){
				updservice.insertTransactionDtl(tranId,bean.getPolicyType(),bean.getFactorId(),"P");
				String res = upload.factorUpNew(tranId,uploadFileName,this.upload,bean.getPolicyType(),bean.getFactorId(),bean.getBrokerId(),bean.getEffectiveDate(),bean.getVehicleType(),bean.getBranchCode());
				//service.gettransactionDtls(bean);
				if(StringUtils.isBlank(res)){
					bean.setMode("tran");
					result="uploadResult";
				}else
					addActionError(result);
			}else
				addActionError("Error in Creating Transaction ID");
		}
		return result;
	}
	
	public String result(){
		LogManager.info("Enter into result");
		bean.setMode("tran");
		service.gettransactionDtls(bean);
		LogManager.info("ProgressStatus =>"+bean.getProgressStatus());
		LogManager.info("ProgressMessage =>"+bean.getProgressMessage());
		if("S".equalsIgnoreCase(bean.getProgressStatus()))
			addActionMessage("File Uploaded Successfully");
		else if ("E".equalsIgnoreCase(bean.getProgressStatus()))
			addActionError(bean.getProgressMessage());
		return "rateConfig";
	}
	
	private void validateUploadXls() {
		if(StringUtils.isEmpty(uploadFileName)){
			addActionError(getText("Please choose the File to Upload"));
		}if(StringUtils.isEmpty(bean.getEffectiveDate())){
			addActionError(getText("Please choose Effective Date"));
		}
	}
	
	public String factorRateTranDetails(){
		try {
			bean.setMode("view");
			bean.setFactorRateList(service.getfactortrandetails());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "rateConfig";
	}
	
	public String factorDownload(){
		String res="rateConfig";
		try {
			List<Map<String, Object>> rateList = service.rateDetailsList(bean);
			if("report".equalsIgnoreCase(bean.getDownloadType()))
				downloadFileName="RatingReport.xls";
			else if("template".equalsIgnoreCase(bean.getDownloadType()))
				downloadFileName="RatingUploadTemplate.xls";
			ExcelDownload fileWrite=new ExcelDownload();
			List<String> fileHeader = new ArrayList<String>();
			if(rateList!=null && rateList.size()>0){
				Map<String, Object> headerMap = rateList.get(0);
				int headerIndex = 0;
				for(Map.Entry<String, Object> entry : headerMap.entrySet()){
					fileHeader.add(headerIndex, entry.getValue()==null?"":entry.getValue().toString());
					headerIndex++;
				}
				ByteArrayOutputStream bos = new ByteArrayOutputStream();
				fileWrite.writeExcelNew(fileHeader, rateList, bos, bean.getDownloadType());
				inputStream=new ByteArrayInputStream(bos.toByteArray());
				res="stream";
			}else{
				addActionError("Something Went Wrong in Download Process");
				bean.setMode("factList");
				//bean.setFactorDetailMasterList(service.getFactorDetailList(bean));
				bean.setMotorFactorList(service.getMotorFactorList(bean));
				bean.setPolicyTypeList(service.getDropDown(bean,"policyType"));
				bean.setVehicleTypeList(service.getDropDown(bean,"vehicleType"));
			}
		}
		catch(Exception exception) {
			LogManager.info("Exception @ RatingReport { " + exception + " } ");
		}
		return res;
	}
	
	public String modalAjax(){
		bean.setFactorDetails(service.getFactorDetails(bean));
		return "ajax";
	}
	
	// Rating Upload End //
	
	// Broker Branch Master Start //
	public String branchList(){
		try {
			bean.setMode("list");
			bean.setBrokerBranchList(service.getBrokerBranchList());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "brokerBranch";
	}
	
	public String editBrokerBranch(){
		try {
			if("edit".equalsIgnoreCase(bean.getMode()))
				service.getEditBrokerbranch(bean);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "brokerBranch";
	}
	
	public String addBrokerBranch(){
		try {
			if(StringUtils.isBlank(bean.getBrokerId()))
				addActionError("please Select Broker");
			if(StringUtils.isBlank(bean.getBranchCode()))
				addActionError("please Select MGEN Branch");
			if(StringUtils.isBlank(bean.getBrokerBranchCode()))
				addActionError("please Enter Broker Branch Code");
			if(StringUtils.isBlank(bean.getBrokerBranchName()))
				addActionError("please Enter Broker Branch Name");
			if(StringUtils.isBlank(bean.getStatus()))
				addActionError("please Select Status");
			if(service.branchExist(bean)>0 && "add".equalsIgnoreCase(bean.getMode())){
				addActionError("This Branch Setup is Already Exist for this Selected Broker");
			}
			
			if(!hasActionErrors()){
				int res=service.updatebrokerBranch(bean);
				if(res>0 && res!=99999){
					addActionMessage("Updated Successfully");
					branchList();
				}else if(res==99999)
					addActionError("This Branch Setup is Already Exist ");
				else{
					addActionError("Update Failed, Please try again");
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "brokerBranch";
	}
	// Broker Branch Master End //
	
	// Executive BDM Master Start //
	
		public String executiveList(){
			bean.setMode("list");
			bean.setExecutiveList(service.getExecutiveList());
			return "executive";
		}
		
		public String editExecutive(){
			try {
				if("edit".equalsIgnoreCase(bean.getMode()))
					service.getEditExecutive(bean);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "executive";
		}
		
		public String addExecutive(){
			try {
				if(StringUtils.isBlank(bean.getExecutiveName()))
					addActionError("please Enter Executive Name");
				if(StringUtils.isBlank(bean.getExecutiveCommission()))
					addActionError("please Enter Executive Commission");
				else {
					try {
						Double.parseDouble(bean.getExecutiveCommission());
					} catch (Exception e) {
						addActionError("please Enter Valid Executive Commission");
						e.printStackTrace();
					}
				}
				if(StringUtils.isBlank(bean.getExecutiveCoreCode()))
					addActionError("please Enter Core App Code");
				if(!hasActionErrors()){
					int res=service.updateExecutive(bean);
					if(res>0){
						addActionMessage("Updated Successfully");
						executiveList();
					}else{
						addActionError("Update Failed, Please try again");
					}
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "executive";
		}
		
		// Executive BDM Master End //
		
		public String condList() {
			return "conditionsList";
		}
		
		public List<Map<String, Object>> getConditionsList(){
			return service.conditionsList();
		}
		
		public String editCond() {
			if("edit".equalsIgnoreCase(bean.getMode())) {
				service.editConditions(bean);
			}
			return "conditionsEdit";
		}
		
		public List<Map<String,Object>> getMotorPolicyTypeList(){
			return service.getDropDown(bean,"policyType");
		}
		
		public String insCond() {
			String returnz = "conditionsEdit";
			validateConditions();
			if(!hasActionErrors()) {
				if(service.insUpdCondition(bean)) {
					addActionMessage("Conditions "+("edit".equalsIgnoreCase(bean.getMode())?"Updated":"Inserted")+" Successfully");
					returnz = "conditionsList";
				}else {
					addActionError("Something Went Wrong. Please Try Again Later");
				}
			}
			return returnz;
		}

		private void validateConditions() {
			try {
				if(StringUtils.isBlank(bean.getPolicyType())) {
					addActionError("Please Select Policy Type");
				}
				if(StringUtils.isBlank(bean.getConditionType())) {
					addActionError("Please Select Condition Type");
				}
				if(StringUtils.isBlank(bean.getConditionDesc())) {
					addActionError("Please Enter Condition Description");
				}
				if(StringUtils.isBlank(bean.getCoreappCode())) {
					addActionError("Please Enter Core App Code");
				}
				if(StringUtils.isBlank(bean.getStatus())) {
					addActionError("Please Choose Status");
				}
			}catch(Exception e) {
				addActionError("Something Went Wrong. Please try Again Later");
				e.printStackTrace();
			}
			
		}
		
		// Exchange Master Start //
		public String exchangeList(){
			try {
				bean.setMode("list");
				bean.setExchangeList(service.getExchangeList());
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "exchangeMaster";
		}
		
		public List<Map<String,Object>> getCurrencyList(){
			return service.getCurrencyList();
		}
		public List<Map<String,Object>> getExchangeCountryList(){
			return service.getExchangeCountryList();
		}
		
		public String editExchange(){
			try {
				if("edit".equalsIgnoreCase(bean.getMode()))
					service.editExchangeData(bean);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "exchangeMaster";
		}
		
		public String addExchange(){
			try {
				if(StringUtils.isBlank(bean.getExchangeRate()))
					addActionError("please Enter Exchange Rate");
				if(StringUtils.isBlank(bean.getCurrencyName()))
					addActionError("please Select Currency Name");
				if(StringUtils.isBlank(bean.getCountryName()))
					addActionError("please Select Country Name");
				if(StringUtils.isBlank(bean.getStartDate()))
					addActionError("please Choose Start Date");
				else if(service.diffInDays(bean.getStartDate(),"") > 0)
					addActionError(getText("Back Date is not Allowed for Start Date"));
				if(StringUtils.isBlank(bean.getEndDate()))
					addActionError("please Choose End Date");
				else if(service.diffInDays(bean.getEndDate(),"") > 0)
					addActionError(getText("Back Date is not Allowed for End Date"));
				if(StringUtils.isNotBlank(bean.getStartDate()) && StringUtils.isNotBlank(bean.getEndDate())) {
					if(service.diffInDays(bean.getStartDate(),bean.getEndDate()) <= 0)
						addActionError(getText("End Date Should be Greater than Start Date"));
				}
				if(StringUtils.isBlank(bean.getEffectiveDate()))
					addActionError("please Choose Effective Date");
				else if(service.diffInDays(bean.getEffectiveDate(),"") >= 0)
					addActionError(getText("Please Choose Effective Date Greater than Today Date"));
				if(StringUtils.isNotBlank(bean.getStartDate()) && StringUtils.isNotBlank(bean.getEffectiveDate())) {
					if(service.diffInDays(bean.getEffectiveDate(),bean.getStartDate()) > 0)
						addActionError(getText("Effective Date Should Between Start Date and End Date"));
				}
				if(StringUtils.isNotBlank(bean.getEndDate()) && StringUtils.isNotBlank(bean.getEffectiveDate())) {
					if(service.diffInDays(bean.getEndDate(),bean.getEffectiveDate()) >= 0)
						addActionError(getText("Effective Date Should Between Start Date and End Date"));
				}
				if(StringUtils.isBlank(bean.getCoreAppCode()))
					addActionError("please Enter CoreApp Code");
				if(StringUtils.isBlank(bean.getStatus()))
					addActionError("please Choose Exchange Status");
				if("add".equalsIgnoreCase(bean.getMode())) {
					int cnt=service.checkExist(bean);
					if(cnt>0)
						addActionError("Setup Already Exist for this Currency");
				}
				
				if(!hasActionErrors()){
					int res=service.updateExchangeData(bean);
					if(res>0 && "add".equalsIgnoreCase(bean.getMode()))
						addActionMessage("Added Successfully");
					else if(res>0 && "edit".equalsIgnoreCase(bean.getMode()))
						addActionMessage("Updated Successfully");
					else
						addActionMessage("Insert / Update Failed");
					
					exchangeList();
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "exchangeMaster";
		}
		// Exchange Master End //
}


    
	
	
