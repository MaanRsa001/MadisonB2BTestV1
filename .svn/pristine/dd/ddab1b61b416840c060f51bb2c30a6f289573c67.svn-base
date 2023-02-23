package com.maan.ClaimIntimation;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import nl.captcha.Captcha;
import proj.date.DateFunction;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.json.annotations.JSON;

import com.maan.ClaimIntimation.bean.ClaimIntimationBean;
import com.maan.ClaimIntimation.service.ClaimIntimationService;
import com.maan.common.LogManager;
import com.maan.services.util.ValidationFormat;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class ClaimIntimationAction extends ActionSupport implements ModelDriven<ClaimIntimationBean>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ClaimIntimationBean bean=new ClaimIntimationBean();
	ClaimIntimationService service=new ClaimIntimationService();
	ValidationFormat validationFormat = new ValidationFormat();
	Map<String, Object> session=ActionContext.getContext().getSession();
	public ClaimIntimationBean getModel() {
		session.put("product_id", "65");
		return bean;
	}
	private InputStream inputStream;
	
	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	@JSON(serialize=false)
	public List<Map<String,Object>> getClaimIntimUploadList() {
		return service.getClaimIntimUploadList(bean);
	}
	
	public String addClaim(){
		String set = validateUserId((String)session.get("user"));
		bean.setPhone(set);
		return "searchlist";
	}
	
	private String validateUserId(String string) {
		String user="";
		if(StringUtils.isNumeric(string)){
			if(string.length()==10){
				user=string;
			}
		}
		return user;
	}

	public String search(){
		
		List<Map<String, Object>> result =null;
		searchValidation();
		if(!hasActionErrors()){
			result =service.getClaimStatus(bean);
			if(result.size()>0){
				bean.setsStatus("available");
			}
			else{
				addActionError("Claim Intimation for this Claim Reference Number :"+bean.getClaimRefNo()+" is not found");
			}
		}
		return "searchlist";
	}
	
	public String insertNew(){
		bean.setMode("add");
		return "claimList";
	}
	
	public String insertB2B(){
		bean.setMode("add");
		return "claimListB2B";
	}

	//insert/edit/update
	/*public String insertClaim(){
 		int count=0;
		if(bean.getMode().equalsIgnoreCase("insert")||bean.getMode().equalsIgnoreCase("update")){
		try{
			validation();
			if(!hasActionErrors()){
				bean.setClaimRefNo(service.getClaimRef(bean));
				count =service.insertClaim(bean);
				if(count==0){
					addActionError(bean.getMode()+"Failed");
				}
				else{
					String success = "Claim Intimated Successfully for the Policy No : " +bean.getPolicyNo()+" and Your Claim Reference ID is "+bean.getClaimRefNo(); 
					addActionMessage("insert".equalsIgnoreCase(bean.getMode())?success:"Updated Successfully");
				}
			}
			else{
			bean.setMode("add");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
		return "claimList";
	}*/
	
	public String insertClaim(){
 		int count=0;
 		String forward="";
		if(bean.getMode().equalsIgnoreCase("insert")||bean.getMode().equalsIgnoreCase("update")){
		try{
			validation();
			if(!hasActionErrors()){
				bean.setPolicyNo(bean.getPolicyNo1()+"/"+bean.getPolicyNo2()+"/"+bean.getPolicyNo3()+"/"+bean.getPolicyNo4());
				String claimRefNum=service.getClaimExistRef(bean);
				if(StringUtils.isNotBlank(claimRefNum)){
					bean.setClaimRefNo(claimRefNum);
					addActionMessage("Claim Already Intimated for the Policy No : " +bean.getPolicyNo()+" with Claim Reference ID is "+bean.getClaimRefNo()); 
					//forward="claimList";
					forward="claimUpload";
				}
				else{
					bean.setClaimRefNo(service.getClaimRef(bean));
					count =service.insertClaim(bean);
					if(count==0){
						addActionError(bean.getMode()+"Failed");
						forward="claimList";
					}
					else{
						forward="claimUpload";
					}
				}
			}
			else{
				bean.setMode("add");
				forward="claimList";
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
		return forward;
	}
	
	public String insertB2BClaim(){
 		int count=0;
 		String forward="";
		if(bean.getMode().equalsIgnoreCase("insert")||bean.getMode().equalsIgnoreCase("update")){
		try{
			validationB2B();
			if(!hasActionErrors()){
				if(StringUtils.isNotBlank(bean.getPolicyNo1()) && StringUtils.isNotBlank(bean.getPolicyNo2()) && StringUtils.isNotBlank(bean.getPolicyNo3()) && StringUtils.isNotBlank(bean.getPolicyNo4())){
					bean.setPolicyNo(bean.getPolicyNo1()+"/"+bean.getPolicyNo2()+"/"+bean.getPolicyNo3()+"/"+bean.getPolicyNo4());
				}
				String claimRefNum=service.getClaimExistRef(bean);
				if(StringUtils.isNotBlank(claimRefNum)){
					bean.setClaimRefNo(claimRefNum);
					addActionMessage("Claim Already Intimated for the Policy No : " +bean.getPolicyNo()+" with Claim Reference ID is "+bean.getClaimRefNo()); 
					//forward="claimList";
					forward="claimUploadB2B";
				}
				else{
					bean.setClaimRefNo(service.getClaimRef(bean));
					count =service.insertClaim(bean);
					if(count==0){
						addActionError(bean.getMode()+"Failed");
						forward="claimListB2B";
					}
					else{
						forward="claimUploadB2B";
					}
				}
			}
			else{
				bean.setMode("add");
				forward="claimListB2B";
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
		return forward;
	}
	
	public String submitdocument() {
		try {
			List<String> errorList = service.insertDocumentDetails(bean.getDocumentIdList(),bean.getDocDescription(),bean.getUploadFileName(),bean.getUpload(),bean.getClaimRefNo(),"65","","");
			for(String errorDesc : errorList) {
				addActionError(errorDesc);
			}
			if(!hasActionErrors()&&bean.getDocumentIdList()!=null) {
				addActionMessage("Added Successfully");
			}
		} catch(Exception exception) {
			LogManager.debug(exception);
		}
		return documentUpload();
	}
	
	public String submitdocumentB2B() {
		try {
			List<String> errorList = service.insertDocumentDetails(bean.getDocumentIdList(),bean.getDocDescription(),bean.getUploadFileName(),bean.getUpload(),bean.getClaimRefNo(),"65","","");
			for(String errorDesc : errorList) {
				addActionError(errorDesc);
			}
			if(!hasActionErrors()&&bean.getDocumentIdList()!=null) {
				addActionMessage("Added Successfully");
			}
		} catch(Exception exception) {
			LogManager.debug(exception);
		}
		return documentUploadB2B();
	}
	
	public String documentUpload() {
		return "claimUpload";
	}
	
	public String documentUploadB2B() {
		return "claimUploadB2B";
	}
	
	public String downloaddocument() {
		try {
			inputStream = new FileInputStream(bean.getFilePath());
			bean.setFileName(bean.getFileName());
		} catch(Exception exception) {
			LogManager.debug(exception);
		}
		return "stream";
	}	
	
	public String deletedocument() {
		try {
			service.deleteDocument(bean.getFilePath());
		} catch(Exception exception) {
			LogManager.debug(exception);
		}
		return documentUpload();
	}
	
	public String deletedocumentB2B() {
		try {
			service.deleteDocument(bean.getFilePath());
		} catch(Exception exception) {
			LogManager.debug(exception);
		}
		return documentUploadB2B();
	}
	
	public String clmComplete(){
		bean.setMode("success");
		addActionMessage("Claim Intimated Completed for the Policy No : " +bean.getPolicyNo()+" and Your Claim Reference ID is "+bean.getClaimRefNo());
		return "claimList";
	}
	
	public String clmCompleteB2B(){
		bean.setMode("success");
		addActionMessage("Claim Intimated Completed for the Policy No : " +bean.getPolicyNo()+" and Your Claim Reference ID is "+bean.getClaimRefNo());
		return "claimListB2B";
	}
	
	public void searchValidation(){
		if(StringUtils.isBlank(bean.getClaimRefNo())){
			addActionError("Enter the Claim Reference Number");
		}
		if(StringUtils.isBlank(bean.getPhone())){
			addActionError("Enter the Phone Number");
		}
		Captcha capta=(Captcha) session.get("simpleCaptcha");
		validateCaptcha(bean.getCaptchavalue(),capta);
		
	}
	
	public void validation() {
		if(StringUtils.isBlank(bean.getName())){
			addActionError("Enter the Name");
		}
		if(StringUtils.isBlank(bean.getPhone())){
			addActionError("Enter the Mobile Number");
		}
		else if(!Pattern.matches("^(09)([0-9]{8})$", bean.getPhone())){
			addActionError("Mobile Number should begin with 09 and must contain 10 Digits (eg: 0977777777)");
		}
		if(StringUtils.isBlank(bean.getPassport())){
			addActionError("Enter the NRC/Passport number");
		}
		if(StringUtils.isBlank(bean.getVehicleRegNo()) ){
			addActionError("Enter the Correct Vehicle Registration Number");
		}
		else if(!StringUtils.isAlphanumeric(bean.getVehicleRegNo())){
			addActionError("Vehicle Registration Number should contain AlphaNumeric Combination");
		}
		/*if(StringUtils.isBlank(bean.getPolicyNo1())|| (bean.getPolicyNo1().length()<1) || !StringUtils.isAlpha(bean.getPolicyNo1())){
			addActionError("Enter the Correct Policy Number eg A/12/1234567890/1234");
		}
		else if(StringUtils.isBlank(bean.getPolicyNo2())|| bean.getPolicyNo2().length()<4){
			addActionError("Enter the Correct Policy Number eg A/12/1234567890/1234");
		}
		else if(StringUtils.isBlank(bean.getPolicyNo3())|| bean.getPolicyNo3().length()<10){
			addActionError("Enter the Correct Policy Number eg A/12/1234567890/1234");
		}
		else if(StringUtils.isBlank(bean.getPolicyNo4())|| bean.getPolicyNo4().length()<4){
			addActionError("Enter the Correct Policy Number eg A/12/1234567890/1234");
		}*/
		if(StringUtils.isBlank(bean.getDateOfAccident())){
			addActionError("Select the Date Of Accident");
		}
		Captcha capta=(Captcha) session.get("simpleCaptcha");
		validateCaptcha(bean.getCaptchavalue(),capta);
		
	}
	
	public void validationB2B() {
		if(StringUtils.isBlank(bean.getName())){
			addActionError("Enter the Name");
		}
		if(StringUtils.isBlank(bean.getPassport())){
			addActionError("Enter the NRC/Passport number");
		}
		if(StringUtils.isBlank(bean.getPhone())){
			addActionError("Enter the Mobile Number");
		}
		else if((!StringUtils.isNumeric(bean.getPhone())) || (bean.getPhone().length()!=10)){
			addActionError("Please Enter Valid Mobile Number");
		}else if(!bean.getPhone().startsWith("09") && !bean.getPhone().startsWith("07")){
			addActionError("Mobile Number should begin with 09 or 07 and must contain 10 Digits (eg: 0977777777)");
		}
		if(StringUtils.isBlank(bean.getVehicleRegNo()) ){
			addActionError("Enter the Correct Vehicle Registration Number");
		}
		else if(!StringUtils.isAlphanumeric(bean.getVehicleRegNo())){
			addActionError("Vehicle Registration Number should contain AlphaNumeric Combination");
		}
		if(StringUtils.isBlank(bean.getPolicyNo1()) || StringUtils.isBlank(bean.getPolicyNo2()) || StringUtils.isBlank(bean.getPolicyNo3()) || StringUtils.isBlank(bean.getPolicyNo4())){
			addActionError("Please Enter Policy Number");
		}
		if(StringUtils.isBlank(bean.getDateOfAccident())){
			addActionError("Select the Date Of Accident");
		}else if(!validationFormat.IsDateValidationFormat(bean.getDateOfAccident())) {
			addActionError("Please Select Valid Format Date of Accident");
		} else{
			final DateFunction datf = new DateFunction();
			final Calendar cal1 = Calendar.getInstance();
			String[] dateOfCheque=bean.getDateOfAccident().split("/");
			cal1.set(Integer.parseInt(dateOfCheque[2]),Integer.parseInt(dateOfCheque[1]),Integer.parseInt(dateOfCheque[0]));
			final Calendar cal2 = Calendar.getInstance();
			final Map todayDate =  new com.maan.common.dao.CommonDAO().getTodaysDate();
			cal2.set(Integer.parseInt(todayDate.get("YEAR").toString()),Integer.parseInt(todayDate.get("MONTH").toString()),Integer.parseInt(todayDate.get("DAY").toString()));
			long diff = 0;
			try{
				diff = datf.getDayDifference(cal1,cal2);
			}
			catch(Exception e){
				LogManager.debug("Exception @ Claim IntimationB2B Validation"+e);
			}
			if(diff < 0 || diff > 365){
				addActionError("Please Select Valid Date of Accident");
			}
		}
		
	}
		public void validateCaptcha(String captchavalue, Captcha captcha) {
			try{
				if(!captcha.isCorrect(captchavalue)) 
					addActionError("Invalid Captcha. Enter Again");
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		public String initSearch(){
			LogManager.info("Enter into initSearch()");
			return "searchClaim";
		}
		public String clmPolSearch(){
			LogManager.info("Enter into clmPolSearch()");
			try {
				if(StringUtils.isBlank(bean.getClaimNo()) && StringUtils.isBlank(bean.getPolicyNo()))
					addActionError("Please Enter Policy Number or Claim Number to Search");
				else if(StringUtils.isNotBlank(bean.getClaimNo()) && StringUtils.isNotBlank(bean.getPolicyNo()))
					addActionError("Please Enter Any One Value");
				else{
					bean.setMode("claimSearchList");
					bean.setClaimDetails(service.setClaimDetails(bean));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "searchClaim";
		}
}
