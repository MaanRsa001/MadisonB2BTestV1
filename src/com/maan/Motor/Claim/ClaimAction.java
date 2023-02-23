package com.maan.Motor.Claim;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.maan.Motor.Services.MotorService;
import com.maan.Motor.controller.MotorBean;
import com.maan.common.DBConstants;
import com.maan.common.LogManager;
import com.maan.common.dao.CommonDAO;
import com.maan.common.sms.SmsEmailUtil;
import com.maan.common.util.ResourceBundleUtil;
import com.maan.report.service.ReportService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class ClaimAction extends ActionSupport implements ModelDriven<ClaimBean>{
	
	private static final long serialVersionUID = 1L;
	private ClaimBean bean=new ClaimBean(); 
	private ClaimService service = new ClaimService();
	private ReportService rservice = new ReportService();
	private MotorService mservice = new MotorService();
	LinkedList<String> list=new LinkedList<String>();
	
	
	
	public ClaimBean getModel() {
		Map<String, Object> session=ActionContext.getContext().getSession();
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpServletResponse response=ServletActionContext.getResponse();
		String branchCode=(String)session.get("LoginBranchCode");
		String productId=(String) session.get("product_id");
		//private String brokerCode=(String) session.get("brokerCode");
		String loginId=(String)session.get("user");
		String issuer=(String)session.get("RSAISSUER");
		String userType=(String)session.get("usertype");
		String user=(String)session.get("user1");
		String actualBranch=(String)session.get("adminBranch");
		bean.setIssuer(issuer);
		bean.setBranchCode(branchCode);
		if(request.getParameter("productId")!=null && !"".equals(request.getParameter("productId")))
		{
			session.put("product_id", request.getParameter("productId"));
			productId=(String) session.get("product_id");
		}
		
		bean.setLoginId(loginId);
		bean.setProductId(productId);
		bean.setDestCountry("1");
		bean.setOriginCountry("1");
		bean.setUserType(userType);
		bean.setUser(user);
		bean.setLoginType(userType);
		return bean;
	}
	
	public List<Map<String, Object>> getIntimatePolicyList() {
		return service.getIntimatePolicy(bean);
	}
	public List<Map<String, Object>> getIntimateStatusList() {
		return service.getIntimateStatus(bean);
	}
	public List<Map<String, Object>> getIntimateVehicleList() {
		return service.getIntimateVehicle(bean);
	}
	public List<Object> getHomeList() {
		return service.getHomeList(bean);
	}
	public List<Map<String, Object>> getEndorsementDropdown() {
		return service.getEndorsementDropdown(bean);
	}
	public String getUpdateStatus(){
		return service.getUpdateStatus(bean);
	}
	
	public String intimate() {
		String forward="claimIntimation";
		try{
			if(!"admin".equalsIgnoreCase(bean.getLoginType()) || ("admin".equalsIgnoreCase(bean.getLoginType()) && (StringUtils.isNotBlank(bean.getPolicyNo()) || StringUtils.isNotBlank(bean.getVehicleId()) ))){
				if("claim".equalsIgnoreCase(bean.getRequireType())){
					if( "65".equalsIgnoreCase(bean.getProductId())){
						int policyType = service.getCheckIntimatePolicy(bean);
						if("individual".equalsIgnoreCase(bean.getMode())){
							bean.setClaimDistinctVehicle(service.getIntimateDistinctVehicleList(bean));
							if(policyType <= 2){
								bean.setClaimSameVehicleList(service.getIntimateSameVehicleList(bean));
							}
							else if(policyType >= 3){
								bean.setClaimSameTpaVehicleList(service.getIntimateSameTpaVehicleList(bean));
							}
						}
						if(!"individual".equalsIgnoreCase(bean.getMode()) && !"Add".equals(bean.getMode())){
						if(!"edit".equalsIgnoreCase(bean.getMode())){
							if(policyType <= 2){ // if policy type not third party
								if("admin".equalsIgnoreCase(bean.getLoginType())){
									service.getIntimateEdit(bean);
								}
								bean.setIntimateList(service.getIntimateView(bean));
								forward="claimIntimationAdminView";
								}
							else if(policyType >= 3){
								if("admin".equalsIgnoreCase(bean.getLoginType())){
									service.getIntimateThirdPartyEdit(bean);
								}
								bean.setIntimateThirdPartyList(service.getIntimateThirdPartyView(bean));
								forward="claimIntimationView";
							}
							}else{
								if(policyType <= 2){ // if policy type not third party
									service.getIntimateEdit(bean);
									bean.setMode("Update");
								}
								else if(policyType >= 3){
									service.getIntimateThirdPartyEdit(bean);
									bean.setMode("Update");
								}
							}
						}
					}
					if( "30".equalsIgnoreCase(bean.getProductId())){
						if("individual".equalsIgnoreCase(bean.getMode())){
							bean.setClaimDistinctVehicle(service.getIntimateDistinctVehicleList(bean));
							bean.setClaimSameVehicleList(service.getIntimateSameVehicleList(bean));
							}	
							if(!"individual".equalsIgnoreCase(bean.getMode()) && !"Add".equals(bean.getMode())){
								if(!"edit".equalsIgnoreCase(bean.getMode())){
									if("admin".equalsIgnoreCase(bean.getLoginType())){
										service.getIntimateEdit(bean);
									}
									bean.setIntimateList(service.getIntimateView(bean));
									forward="claimIntimationAdminView";
								}else{
									service.getIntimateEdit(bean);
									bean.setMode("Update");
								}
							}
					}
					if((bean.getIntimateList()!=null && bean.getIntimateList().size()>0) ||(bean.getIntimateThirdPartyList()!=null && bean.getIntimateThirdPartyList().size()>0) ){
						if("admin".equals(bean.getLoginType()))
							forward="claimIntimationAdminView";
						else
							forward="claimIntimationView";
					}else{
						
					}
				}
			}else{
				forward="claimIntimationAdmin";
				if("calimReportList".equalsIgnoreCase(bean.getMode())){
					validateDate();
					if(!hasActionErrors()){
						if( "65".equalsIgnoreCase(bean.getProductId())){
							bean.setIntimateList(service.claimIntimateReportList(bean));
							bean.setIntimateThirdPartyList(service.claimIntimateReportTpaList(bean));
						}
						if( "30".equalsIgnoreCase(bean.getProductId())){
							bean.setIntimateList(service.claimIntimateReportList(bean));
						}
					}
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return forward;
	}
	public String intimateInsert(){
		String forward = "claimIntimation";
		try{
			if("admin".equalsIgnoreCase(bean.getLoginType())){
				forward="claimIntimationAdmin";
				if (StringUtils.isEmpty(bean.getApproverStatus()))
					addActionError(getText("error.admin.status"));
				else if("S".equalsIgnoreCase(bean.getApproverStatus())){
					if (StringUtils.isEmpty(bean.getSettleAmt()))
						addActionError(getText("Enter the Settlement Amount"));
				}
				if(!hasActionErrors()){
					if( "65".equalsIgnoreCase(bean.getProductId())){
						int policyType = service.getCheckIntimatePolicy(bean);
						if(!(getText("MOTOR_TP_ID")).equalsIgnoreCase(Integer.toString(policyType))){
							service.getIntimationInsert(bean);
							addActionMessage(getText("message.sucessfully"));
						}
						else if((getText("MOTOR_TP_ID")).equalsIgnoreCase(Integer.toString(policyType))){
							service.getIntimationTpaInsert(bean);
							addActionMessage(getText("message.sucessfully"));
						}
						bean.setIntimateList(service.claimIntimateReportList(bean));
						bean.setIntimateThirdPartyList(service.claimIntimateReportTpaList(bean));
					}
					if( "30".equalsIgnoreCase(bean.getProductId())){
						service.getIntimationInsert(bean);
						addActionMessage(getText("message.sucessfully"));
						bean.setIntimateList(service.claimIntimateReportList(bean));
					}
					service.insertClaimApprovedDetails(bean);
					String tquoteNo = new com.maan.common.dao.CommonDAO().getHomeApplicationNo(bean.getPolicyNo());
					if("S".equalsIgnoreCase(bean.getApproverStatus())) {
						new SmsEmailUtil(SmsEmailUtil.CLAIM_CLOSED,bean.getClaimNo()).send();
						new SmsEmailUtil(SmsEmailUtil.CLAIM_CLOSED_OPUSER,bean.getClaimNo()).send();
					} else if("R".equalsIgnoreCase(bean.getApproverStatus())) {
						new SmsEmailUtil(SmsEmailUtil.CLAIM_REJECT,bean.getClaimNo()).send();
						new SmsEmailUtil(SmsEmailUtil.CLAIM_REJECT_OPUSER,bean.getClaimNo()).send();
					} else if("P".equalsIgnoreCase(bean.getApproverStatus())) {
						new SmsEmailUtil(SmsEmailUtil.CLAIM_PROCESS,bean.getClaimNo()).send();
						new SmsEmailUtil(SmsEmailUtil.CLAIM_PROCESS_OPUSER,bean.getClaimNo()).send();
					} else if("D".equalsIgnoreCase(bean.getApproverStatus())) {
						new SmsEmailUtil(SmsEmailUtil.CLAIM_UPDATE,bean.getClaimNo()).send();
						new SmsEmailUtil(SmsEmailUtil.CLAIM_UPDATE,bean.getClaimNo()).send();
					}
				}else{
					forward = intimate();
				}
			}else{
				getValidate("claim");
				forward = "claimIntimation";
				if(getActionErrors().isEmpty() && (bean.getActionErrorsBean() == null || bean.getActionErrorsBean().isEmpty())) {
					if("65".equalsIgnoreCase(bean.getProductId())){
						int policyType = service.getCheckIntimatePolicy(bean);
						if(policyType <= 2){
							service.getIntimateInsert(bean);
							if(DBConstants.DEVICETYPE_ANDROID.equals(bean.getDeviceType()) || DBConstants.DEVICETYPE_HYBRID.equals(bean.getDeviceType())) {
								if(!"Update".equalsIgnoreCase(bean.getMode())){
									bean.setActionMsg("Claim Request has Made successfully and Claim Number  "+bean.getClaimNo());
								}
								if("Update".equalsIgnoreCase(bean.getMode())){
									bean.setActionMsg("Claim Request has Updated successfully and Claim Number  "+bean.getClaimNo());
								}
							}else{
								if(!"Update".equalsIgnoreCase(bean.getMode())){
									addActionMessage(getText("message.sucessfully"));
								}
								if("Update".equalsIgnoreCase(bean.getMode())){
									addActionMessage(getText("updated.sucessfully"));
								}
							}
							bean.setClaimDistinctVehicle(service.getIntimateDistinctVehicleList(bean));
							bean.setClaimSameVehicleList(service.getIntimateSameVehicleList(bean));
						}
						if(policyType >= 3){
							service.getIntimateThirdPartyInsert(bean);
							if(DBConstants.DEVICETYPE_ANDROID.equals(bean.getDeviceType()) || DBConstants.DEVICETYPE_HYBRID.equals(bean.getDeviceType())) {
								if(!"Update".equalsIgnoreCase(bean.getMode())){
									bean.setActionMsg("Claim Request has Made successfully and Claim Number  "+bean.getClaimNo());
								}
								if("Update".equalsIgnoreCase(bean.getMode())){
									bean.setActionMsg("Claim Request has Updated successfully and Claim Number  "+bean.getClaimNo());
								}
							}else{
								if(!"Update".equalsIgnoreCase(bean.getMode())){
									addActionMessage(getText("message.sucessfully"));
								}
								if("Update".equalsIgnoreCase(bean.getMode())){
									addActionMessage(getText("updated.sucessfully"));
								}
							}
							bean.setClaimDistinctVehicle(service.getIntimateDistinctVehicleList(bean));
							bean.setClaimSameTpaVehicleList(service.getIntimateSameTpaVehicleList(bean));
						}
						
					}else if("30".equalsIgnoreCase(bean.getProductId())){
						service.getIntimateInsert(bean);
						if(DBConstants.DEVICETYPE_ANDROID.equals(bean.getDeviceType()) || DBConstants.DEVICETYPE_HYBRID.equals(bean.getDeviceType())) {
							if(!"Update".equalsIgnoreCase(bean.getMode())){
								bean.setActionMsg("Claim Request has Made successfully and Claim Number  "+bean.getClaimNo());
							}
							if("Update".equalsIgnoreCase(bean.getMode())){
								bean.setActionMsg("Claim Request has Updated successfully and Claim Number  "+bean.getClaimNo());
							}
						}else{
							if(!"Update".equalsIgnoreCase(bean.getMode())){
								addActionMessage(getText("message.sucessfully"));
							}
							if("Update".equalsIgnoreCase(bean.getMode())){
								addActionMessage(getText("updated.sucessfully"));
							}
						}
						bean.setClaimDistinctVehicle(service.getIntimateDistinctVehicleList(bean));
						bean.setClaimSameVehicleList(service.getIntimateSameVehicleList(bean));
					}
					String tquoteNo = new com.maan.common.dao.CommonDAO().getHomeQuoteNo(bean.getPolicyNo());
					if(!"Update".equalsIgnoreCase(bean.getMode())){
						new SmsEmailUtil(SmsEmailUtil.CLAIM_REG,bean.getClaimNo()).send();
						new SmsEmailUtil(SmsEmailUtil.CLAIM_REG_OPUSER,bean.getClaimNo()).send();
					}
					bean.setMode("individual");
					forward = "claimIntimation";
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return forward;
	}
	
	public String endorsement() {
		String forward="claimIntimation";
		try{
			if(!"admin".equalsIgnoreCase(bean.getLoginType())){
				if("showlist".equalsIgnoreCase(bean.getMode())){
					bean.setEndtReqList(service.getEndtReqList(bean));
					forward="claimStatus";
				}
				else if("showlistClaim".equalsIgnoreCase(bean.getMode())){
					bean.setEndtReqList(service.getClaimReqList(bean));
					forward="claimStatus";
				}
				else if("newReq".equalsIgnoreCase(bean.getMode())){
					bean.setEndtReqList(service.getEndtNewReqList(bean));
					forward="claimIntimation";
				}
				
			}else{
				forward = "claimIntimationAdmin";
				
				if("endorsementList".equalsIgnoreCase(bean.getMode())){
					validateDate();
					if(!hasActionErrors())
						bean.setEndorsementList(service.geteEndorsementList(bean));	
				} 
				if("viewEndorsement".equalsIgnoreCase(bean.getMode())){
					bean.setEndorsementListView(service.getEndorsementListView(bean));
					forward="claimIntimationAdminView";
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return forward;
	}
	public String endorsementInsert() {
		String forward="";
		try{
			if("admin".equalsIgnoreCase(bean.getLoginType())){
				if (StringUtils.isEmpty(bean.getApproverStatus()))
					addActionError(getText("error.admin.status"));	
				if(!hasActionErrors()){
					service.getEndorsementUpdate(bean);	
					bean.setEndorsementList(service.geteEndorsementList(bean));	
					addActionMessage(getText("Endorsement Updated Successfully."));
					if("Y".equalsIgnoreCase(bean.getApproverStatus())){
						new SmsEmailUtil(SmsEmailUtil.ENDT_APPROVE_CUST, new CommonDAO().getHomeQuoteNo(bean.getPolicyNo())).send();
						new SmsEmailUtil(SmsEmailUtil.ENDT_APPROVE_OPUSER,new CommonDAO().getHomeQuoteNo(bean.getPolicyNo())).send();
					}
					else if("N".equalsIgnoreCase(bean.getApproverStatus())){
						new SmsEmailUtil(SmsEmailUtil.ENDT_DISAPPROVE_CUST, new CommonDAO().getHomeQuoteNo(bean.getPolicyNo())).send();
						new SmsEmailUtil(SmsEmailUtil.ENDT_DISAPPROVE_OPUSER,new CommonDAO().getHomeQuoteNo(bean.getPolicyNo())).send();
					}
					forward="claimIntimationAdmin";
				}
				else{
					bean.setEndorsementListView(service.getEndorsementListView(bean));
					forward="claimIntimationAdminView";	
				}
			}else{
				if("homePage".equalsIgnoreCase(bean.getMode())){
					forward="initReport";
				}
				else{
					getValidate("Endorsement");
					if(getActionErrors().isEmpty() && (bean.getActionErrorsBean() == null || bean.getActionErrorsBean().isEmpty())) {
						String seq=service.insertEndtRequest(bean);
						if("Update".equalsIgnoreCase(bean.getMode())){
							bean.setActionMsg("Endorsement Request has Updated successfully and Refrence Number  "+bean.getRefNo());
						}else{
							bean.setActionMsg("Endorsement Request has made successfully and Refrence Number  "+seq);
						}
						if(StringUtils.isNotBlank(seq) && !("update".equalsIgnoreCase(bean.getMode()))){
							new SmsEmailUtil(SmsEmailUtil.ENDT_REQUEST_CUST, new CommonDAO().getHomeQuoteNo(bean.getPolicyNo())).send();
							new SmsEmailUtil(SmsEmailUtil.ENDT_REQUEST_OPUSER,new CommonDAO().getHomeQuoteNo(bean.getPolicyNo())).send();
						}
						forward="initReport";	
					}
			
					else{
						forward=endorsement();
					}
				}
			}
			}catch (Exception e) {
			e.printStackTrace();
		}
			return forward;
	}
	public String endorsementDetails()
	{
		if("update".equalsIgnoreCase(bean.getMode())){
			if (StringUtils.isEmpty(bean.getStatus()))
				addActionError(getText("error.admin.status"));	
			if(!hasActionErrors())
			{
			service.getEndorsementUpdate(bean);	
			bean.setMode("showlist");
			addActionMessage(getText("endorsement.req.succses"));
			
			}
			else{
				bean.setMode("view");	
			}
		}
		
		if("view".equalsIgnoreCase(bean.getMode()))
		{
			bean.setEndorsementListView(service.getEndorsementListView(bean));	
		}
		
		
		return "endorsementDetails";
	}
	
	public String claimDetails(){
		try{
			LogManager.info("Enter Into claim()");
			service.getClaimDetails(bean,bean.getProductId(),bean.getBranchCode());
			mservice.getPremiumInfo(bean);
			bean.setStatus("claimDetails");
			LogManager.info("Exit Into claim()");
		}catch (Exception e) {
			LogManager.debug("Exception at claim() "+e);
		}
		return "viewClaim";
		
	}
	public String saveClaim(){
		try{
			LogManager.info("Enter Into saveClaim()");
			if(StringUtils.isEmpty(bean.getLossDate())){
				addActionError(getText("error.motor.claim.loss.date"));
			}
			if(StringUtils.isEmpty(bean.getLossDescription())){
				addActionError(getText("error.motor.claim.loss.description"));
			}
			if(!hasActionErrors()){
				String status = service.saveClaimIntimationDetails(bean);
				if(!status.equalsIgnoreCase("0")){
					bean.setStatus("intimated");
				}
			}
			else{
				claimDetails();
			}
				LogManager.info("Exit Into saveClaim()");
		}catch (Exception e) {
			LogManager.debug("Exception at saveClaim() "+e);
		}
		return "viewClaim";
	}
	public String adminClaim(){
		try{
			LogManager.info("Enter Into adminClaim()");
			List<Map<Object, Object>> claimList;
			claimList = service.getClaimAdminDetail(bean);
			bean.setClaimList(claimList);
			bean.setStatus("");
			LogManager.info("Exit Into adminClaim()");
		}catch (Exception e) {
			LogManager.debug("Exception at adminClaim() {" + e + "}");
		}
		return "adminClaimDetail";
	}
	public String closeClaimDet(){
		try{
			LogManager.info("Enter Into closeClaimDet()");
			bean.setApplicationNo(service.getApplicationNo(bean.getPolicyNo()));
			service.getClaimDetails(bean,bean.getProductId(), bean.getBranchCode());
			mservice.getPremiumInfo(bean);
			service.getClaimCloseDetails(bean);
			bean.setStatus("claimDetails");
			LogManager.info("Exit Into closeClaimDet()");
		}catch (Exception e) {
			LogManager.info("Exception @ closeClaimDet() {"+e+"}");
		}
		return "adminClaimDetail";
	}
	public String closeClaim(){
		try{
			LogManager.info("Enter Into closeClaim()");
			if(StringUtils.isEmpty(bean.getRemarks())){
				addActionError(getText("error.claim.motor.remarks"));
			}
			if(!hasActionErrors()){
				service.updateCloseDetail1s(bean);
				addActionMessage(getText("claim.closed.successfully"));
				bean.setStatus("");
				adminClaim();
			}
			else{
				closeClaimDet();
			}
			LogManager.info("Exit Into closeClaim()");
		}catch (Exception e) {
			LogManager.info("Exception @ closeClaim() {"+e+"}");
		}
		return "adminClaimDetail";
	}
		
	/*public String intimate() {
		int selecta=2;
		int selectb=3;
		bean.setIntimatePolicyList(service.getIntimatePolicy(bean));
		bean.setIntimateVehicleList(service.getIntimateVehicle(bean));
		if(!"edit".equalsIgnoreCase(bean.getMode()) && service.getCheckIntimatePolicy(bean) <= selecta && service.checkIntimateComp(bean) > 0){
			bean.setIntimateList(service.getIntimateView(bean));
			return "claimIntimationView";
		}
		if(!"edit".equalsIgnoreCase(bean.getMode()) &&service.getCheckIntimatePolicy(bean) >= selectb && service.checkIntimateTpa(bean) > 0){
			bean.setIntimateThirdPartyList(service.getIntimateThirdPartyView(bean));
			return "claimIntimationView";
		}
        if("edit".equalsIgnoreCase(bean.getMode())){
        	if(service.getCheckIntimatePolicy(bean) <= selecta)
			service.getIntimateEdit(bean);
			if(service.getCheckIntimatePolicy(bean) >= selectb)
			service.getIntimateThirdPartyEdit(bean);
		}
		return "claimIntimation";
	}
	*/
	/*public String intimateInsert(){
		int selecta=2;
		int selectb=3;
		String forward = "claimIntimation";
		//bean.setIntimatePolicyList(service.getIntimatePolicy(bean));
		//bean.setIntimateVehicleList(service.getIntimateVehicle(bean));
		validateintimateInsert();
		if(!hasActionErrors()) {
			if(service.getCheckIntimatePolicy(bean) <= selecta){
			service.getIntimateInsert(bean);
			bean.setIntimateList(service.getIntimateView(bean));
			}
			if(service.getCheckIntimatePolicy(bean) >= selectb){
			service.getIntimateThirdPartyInsert(bean);
			bean.setIntimateThirdPartyList(service.getIntimateThirdPartyView(bean));
			}
			forward = "claimIntimationView";
		}
		return forward;
	}*/
	//endorsement details endorsement
	
	/*public String intimatedClaimStatus(){
		if("showlist".equalsIgnoreCase(bean.getMode())){
			bean.setEndtReqList(service.getClaimReqList(bean));
		}
		return "endorsement";
	}*/
	/*public String endorsementInsert() {
		if(StringUtils.isBlank(bean.getEndTypeId())) {
			addActionError(getText("error.endtType"));
		}
		if(StringUtils.isBlank(bean.getAgentRemarks())) {
			addActionError(getText("error.agent.remarks"));
		}
		if(!hasActionErrors()) {
			//bean.setProductId(bean.getProductId());
			String seq=service.insertEndtRequest(bean);
		//	addActionMessage(getText("message.endtRequest"));
			bean.setActionMsg("Endorsement Request has made successfully and Refrence Number  "+seq);
		}else{
			return endorsement();
		}
		return "initReport";
	}*/
	
	private void getValidate(String type) {
		String Motor_tp = "3";
		LinkedList<String> list=service.getValidate(bean, type,Motor_tp);
		setActionErrorList(list);
	}
	
	public void mapRestBean(ClaimBean bean) {
		bean.setActionErrorsBean(new ArrayList<String>());
		this.bean = bean;
	}
	
	private void setActionErrorList(List<String> list) {
		if(DBConstants.DEVICETYPE_ANDROID.equals(bean.getDeviceType()) || DBConstants.DEVICETYPE_HYBRID.equals(bean.getDeviceType())) {
			List<String> errorBean = new ArrayList<String>();
			for (String st : list) {
				if(st.indexOf("#")!=-1) {
					Object args[]=(st.indexOf("~")!=-1?(st.substring(st.indexOf('#')+1, st.lastIndexOf('#'))).split("~"):new String[]{st.substring(st.indexOf('#')+1)});
					bean.getActionErrorsBean().add(ResourceBundleUtil.findText(DBConstants.CLAIM_PROPERTY_FILE,st.substring(0,st.indexOf('#')),args));
				} else {
					bean.getActionErrorsBean().add(ResourceBundleUtil.findText(DBConstants.CLAIM_PROPERTY_FILE,st));
				}
			}
			bean.getActionErrorsBean().addAll(errorBean);
		} else {
			for (String st : list) {
				if(st.indexOf("#")!=-1) {
					String args[]=(st.indexOf("~")!=-1?(st.substring(st.indexOf('#')+1, st.lastIndexOf('#'))).split("~"):new String[]{st.substring(st.indexOf('#')+1)});
					this.addActionError(getText(st.substring(0,st.indexOf('#')),args));
				} else {
					this.addActionError(getText(st));	
				}
			}
		}
	}
	/*private void validateintimateInsert() {
		if("65".equalsIgnoreCase(bean.getProductId())){
			if((getText("MOTOR_TP_ID")).equalsIgnoreCase(bean.getPolicyType())) {
				if(StringUtils.isBlank(bean.getHospitalName())) {
					addActionError(getText("error.claim.hospitalName"));
					list.add("error.claim.hospitalName");
				}
				if(StringUtils.isBlank(bean.getNameOfDoctor())) {
					addActionError(getText("error.claim.nameOfDoctor"));
					list.add("error.claim.nameOfDoctor");
				}
				if(StringUtils.isBlank(bean.getDoctorTelephoneNumber())) {
					addActionError(getText("error.claim.doctorTelephoneNumber"));
					list.add("error.claim.doctorTelephoneNumber");
				}
				if(StringUtils.isBlank(bean.getThirdPartyName())) {
					addActionError(getText("error.claim.thirdPartyName"));
					list.add("error.claim.thirdPartyName");
				}
				if(StringUtils.isBlank(bean.getThirdPartyPropertyDetail())) {
					addActionError(getText("error.claim.thirdPartyPropertyDetail"));
					list.add("error.claim.thirdPartyPropertyDetail");
				}
				if(StringUtils.isBlank(bean.getThirdPartyOwnerName())) {
					addActionError(getText("error.claim.thirdPartyOwnerName"));
					list.add("error.claim.thirdPartyOwnerName");
				}
				if(StringUtils.isBlank(bean.getThirdPartyOwnerTelephoneNumber())) {
					addActionError(getText("error.claim.tpOwnerTelephoneNo"));
					list.add("error.claim.tpOwnerTelephoneNo");
				}
				if(StringUtils.isBlank(bean.getDriverName())) {
					addActionError(getText("error.claim.driverName"));
					list.add("error.claim.driverName");
				}
				if(StringUtils.isBlank(bean.getPhysicalAddress())) {
					addActionError(getText("error.claim.physicalAddress"));
					list.add("error.claim.physicalAddress");
				}
				if(StringUtils.isBlank(bean.getRegisterNumber())) {
					addActionError(getText("error.claim.registerNo"));
					list.add("error.claim.registerNo");
				}
				if(StringUtils.isBlank(bean.getMake())) {
					addActionError(getText("error.claim.make"));
					list.add("error.claim.make");
				}
				if(StringUtils.isBlank(bean.getNameOfInsurer())) {
					addActionError(getText("error.claim.nameOfInsurer"));
					list.add("error.claim.nameOfInsurer");
				}
			} else {
				if(StringUtils.isBlank(bean.getSoleOwnerYN())) {
					addActionError(getText("error.claim.soleOwnerYN"));
					list.add("error.claim.soleOwnerYN");
				} else if("N".equals(bean.getSoleOwnerYN()) && StringUtils.isBlank(bean.getFinanciers())) {
					addActionError(getText("error.claim.financier"));
					list.add("error.claim.financier");
				}
				if(StringUtils.isBlank(bean.getNameOfDriver())) {
					addActionError(getText("error.claim.nameOfDriver"));
					list.add("error.claim.nameOfDriver");
				}
				if(StringUtils.isBlank(bean.getDriverDob())) {
					addActionError(getText("error.claim.dob"));
					list.add("error.claim.dob");
				}
				if(StringUtils.isBlank(bean.getDriverDob())) {
					addActionError(getText("error.claim.dob"));
					list.add("error.claim.dob");
				}
				if(StringUtils.isBlank(bean.getLicenseNumber())) {
					addActionError(getText("error.claim.licenceNo"));
					list.add("error.claim.licenceNo");
				}
				if(StringUtils.isBlank(bean.getDateObtained())) {
					addActionError(getText("error.claim.dateObtained"));
					list.add("error.claim.dateObtained");
				}
				if(StringUtils.isBlank(bean.getLossDate())) {
					addActionError(getText("error.claim.lossDate"));
					list.add("error.claim.lossDate");
				}
				if(StringUtils.isBlank(bean.getLossTime())) {
					addActionError(getText("error.claim.lossTime"));
					list.add("error.claim.lossTime");
				}
				if(StringUtils.isBlank(bean.getLossSpeed())) {
					addActionError(getText("error.claim.lossSpeed"));
					list.add("error.claim.lossSpeed");
				}
				if(StringUtils.isBlank(bean.getLossPlace())) {
					addActionError(getText("error.claim.lossPlace"));
					list.add("error.claim.lossPlace");
				}
				if(StringUtils.isBlank(bean.getLossPurposeDescription())) {
					addActionError(getText("error.claim.lossPurposeDesc"));
					list.add("error.claim.lossPurposeDesc");
				}
				if(StringUtils.isBlank(bean.getLossDetailDescription())) {
					addActionError(getText("error.claim.lossDetailDesc"));
				}
				if(StringUtils.isBlank(bean.getLossReportYN())){
					addActionError(getText("error.loss.report.yn"));
					list.add("error.loss.report.yn");
				}
				if("Y".equalsIgnoreCase(bean.getLossReportYN())&&StringUtils.isBlank(bean.getLossDateReported())) {
					addActionError(getText("error.claim.lossDateReported"));
					list.add("error.claim.lossDateReported");
				}
				if("Y".equalsIgnoreCase(bean.getLossReportYN())&&StringUtils.isBlank(bean.getLossTimeReported())) {
					addActionError(getText("error.claim.lossTimeReported"));
					list.add("error.claim.lossTimeReported");
				}
				if(StringUtils.isBlank(bean.getLossPoliceVisitYN())){
					addActionError(getText("error.loss.police.report.yn"));
					list.add("error.loss.police.report.yn");
				}
				if("Y".equalsIgnoreCase(bean.getLossPoliceVisitYN())&&StringUtils.isBlank(bean.getPoliceOfficerName())) {
					addActionError(getText("error.claim.policeOfficerName"));
					list.add("error.claim.policeOfficerName");
				}
				if("Y".equalsIgnoreCase(bean.getLossPoliceVisitYN())&&StringUtils.isBlank(bean.getIdentityNumber())) {
					addActionError(getText("error.claim.identityNo"));
				}
				if("Y".equalsIgnoreCase(bean.getLossPoliceVisitYN())&&StringUtils.isBlank(bean.getPoliceStationName())) {
					addActionError(getText("error.claim.policeStationName"));
					list.add("error.claim.policeStationName");
				}
				if(StringUtils.isBlank(bean.getLossLocation())) {
					addActionError(getText("error.claim.lossLocation"));
					list.add("error.claim.lossLocation");
				}
				if(StringUtils.isBlank(bean.getDriverDescription())) {
					addActionError(getText("error.claim.driverDescription"));
					list.add("error.claim.driverDescription");
				}
			}
		}else if("30".equalsIgnoreCase(bean.getProductId())){
			if(StringUtils.isBlank(bean.getLossStatus()))
				addActionError(getText("error.choose.loss.status"));
			list.add("error.choose.loss.status");
			if(StringUtils.isBlank(bean.getLossDescription()))
				addActionError(getText("error.enter.loss.description"));
			list.add("error.enter.loss.description");
		}
		if(StringUtils.isBlank(bean.getDeclaration())) {
			addActionError(getText("error.claim.declaration"));
			list.add("error.claim.declaration");
		}
	}*/
	private void validateDate(){
		long date = diffInDays(bean.getToDate(),bean.getFromDate());
			if (StringUtils.isEmpty(bean.getFromDate())){
				addActionError(getText("error.fromdate"));
				bean.setMode("");
			}else if (StringUtils.isEmpty(bean.getToDate())){
				addActionError(getText("error.todate"));
				bean.setMode("");
			}else if (date > 0){
				 addActionError(getText("error.diff.date"));
				 bean.setMode("");
			 }
	}

	public long diffInDays(String startDate, String endDate) {
		long result = 0;
		if (!StringUtils.isEmpty(startDate) && !StringUtils.isEmpty(endDate)) {
			try {
				Date date = new Date();
				Calendar cal1 = Calendar.getInstance();
				Calendar cal2 = Calendar.getInstance();
				SimpleDateFormat sfd = new SimpleDateFormat("dd/MM/yyyy");
				cal1.setTime(sfd.parse(startDate));
				if (StringUtils.isBlank(endDate))
					cal2.setTime(sfd.parse(sfd.format(date)));
				else
					cal2.setTime(sfd.parse(endDate));
				long milis1 = cal1.getTimeInMillis();
				long milis2 = cal2.getTimeInMillis();
				long diff = milis2 - milis1;
				result = diff / (24 * 60 * 60 * 1000);
			} catch (Exception e) {
				LogManager.debug("Exception Occured @ " + e);
				e.printStackTrace();
			}
		}
		return result;
	}
}
