package com.maan.adminnew.AdminMgt;

import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import com.maan.adminnew.common.CommonService;
import com.maan.common.LogManager;
import com.maan.common.Validation;
import com.maan.common.password.passwordEnc;
import com.maan.common.util.StringUtil;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class AdminMgtAction extends ActionSupport implements ModelDriven<AdminMgtBean>
{
	private static final long serialVersionUID = 1589654L;
	private AdminMgtBean bean = new AdminMgtBean();
	Validation validation=new Validation();
	AdminMgtService service=new AdminMgtService();
	CommonService cservice=new CommonService();
	Map<String, Object> session=ActionContext.getContext().getSession();
	String branchCode=(String)session.get("BranchCode");
	private String belongingBranch=(String)session.get("BelongingBranch");
	String appId=(String)session.get("AppId");
	String login_id=(String)session.get("user");
	String productId = (String) session.get("product_id");
	passwordEnc pass = new passwordEnc();
	
	public AdminMgtBean getModel() {
		return bean;
	}
	public List<Object>getUtypeList(){
		return service.UtypeList(branchCode, appId);
	}
	public List<Object> getMenuList(){
		return service.getMenuList(bean,branchCode);
	}
	public Map<String,List<Object>>  getProductsWiseMenu(){
		return service.getProductList(bean,branchCode);
	}
	public List<Object>getAdminList(){
		return service.adminList(bean, branchCode, appId);
	}
	public List<Map<String,String>>getProductList(){
		return cservice.getProductsDET(branchCode, "");
	}
	public List<Object>getBrokerList(){
		return cservice.getAdminBrokerList(branchCode, appId);
	}
	public List<Object>getBranchList(){
		return cservice.getAdminBranchList(branchCode, appId);
	}	
	public List<Object> getDashBoard(){
		return service.getDashBoard(login_id,bean,productId,branchCode);
	}
	public List<Map<String,String>> getUwGradeList(){
		return service.getUwGradeList();
	}
	public String home(){
		session.put("product_id",bean.getProduct());
		session.put("MenuList", cservice.getMenuList( (String)session.get("MENU_ID"), belongingBranch,bean.getProduct()));
		return "adminHome";
	}
	
	public String adminMgt(){
		bean.setFrom("alist");
		bean.setFrom1("mlist");
		return "adminMgt";
	}
	
	public String editadmin(){
		bean.setFrom("aform");
		bean.setFrom1("mlist");
		bean.setIndex("0");
		if("edit".equals(bean.getMode()))
			service.getAdminInfo(bean, branchCode, appId);
		bean.setBranch(service.getBranch(branchCode));
		return "adminMgt";
	}
	
	public String editMenu(){
		bean.setFrom("alist");
		bean.setFrom1("mform");
		bean.setIndex("1");
		if("edit".equals(bean.getMode1()))
			service.getMenuInfo(bean, branchCode);
		return "adminMgt";
	}
	
	public String menuSave(){
		bean.setFrom("alist");
		bean.setFrom1("mform");
		bean.setIndex("1");
		try{
			validatemenu();
			if(!hasActionErrors()){
				if("new".equals(bean.getMode1())){
					service.insNewMenu(bean, branchCode);
					bean.setFrom1("addSuccess");
				}else if("edit".equals(bean.getMode1())){
					service.updateMenu(bean, branchCode);
					bean.setFrom1("updateSuccess");
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return "adminMgt";
	}
	public String adminSave(){
		bean.setFrom("aform");
		bean.setFrom1("mlist");
		bean.setIndex("0");
		String pid="";
		if(bean.getProductID()!=null && bean.getProductID().length>0){
			for(String str: bean.getProductID())
				pid=pid+","+str;
			pid=pid.substring(1);
		}
		
		bean.setProduct(pid);
		try{
			validateadmin();
			if(!hasActionErrors()){
				if("new".equals(bean.getMode())){
					service.insNewAdmin(bean, branchCode, appId);
					bean.setFrom("addSuccess");
				}else if("edit".equals(bean.getMode())){
					service.updateAdmin(bean, branchCode, appId);
					bean.setFrom("updateSuccess");
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return "adminMgt";
	}
	
	public String menuSelection(){
		return "menuSelect";
	}
	
	public String brokerSelection(){
		return "brokerSelect";
	}
	public String branchSelection(){
		return "branchSelect";
	}
	public String ajaxList(){
		return "ajax";
	}
	
    public void validatemenu(){
    	if(StringUtils.isBlank(bean.getMname()))
    		addActionError(getText("error.menu.name.required"));
    	if(StringUtils.isBlank(bean.getUrlPath()))
    		addActionError(getText("error.url.path.required"));
    	if(StringUtils.isBlank(bean.getParent()))
    		addActionError(getText("error.parent.required"));
    	if(StringUtils.isBlank(bean.getStatus()))
    		addActionError(getText("error.status.required"));
    }
    
    public void validateadmin(){
    	if(StringUtils.isBlank(bean.getUtype()))
    		addActionError(getText("error.admin.utype.required"));
    	/*if(StringUtils.isBlank(bean.getUname()))
    		addActionError(getText("error.admin.uname.required"));*/
    	if(StringUtils.isBlank(bean.getFirstName())) {
    		addActionError(getText("Please Enter First Name"));
    	}
    	if(StringUtils.isBlank(bean.getLastName())) {
    		addActionError(getText("Please Enter Last Name"));
    	}
    	if(StringUtils.isBlank(bean.getLoginID()))
    		addActionError(getText("error.admin.loginid.required"));
    	else if(StringUtils.contains(bean.getLoginID(), " "))
	 		addActionError("Login Id should not contain white spaces");
    	if("new".equals(bean.getMode())){
    		if(cservice.getAdminInfo(bean.getLoginID()).size()>0)
        		addActionError(getText("error.loginid.notavailable"));
	    	if(StringUtils.isBlank(bean.getPwd()))
	    		addActionError(getText("error.admin.pwd.required"));
	    	else if(StringUtils.contains(bean.getPwd(), " "))
		 		addActionError("Password should not contain white spaces");
    	}if(StringUtils.isBlank(bean.getProduct()))
    		addActionError(getText("error.admin.product.required"));
    	if(!"admin".equalsIgnoreCase(bean.getUtype())){
	    	if(StringUtils.isBlank(bean.getMid()))
	    		addActionError(getText("error.admin.mid.required"));
    	}
    	//if(StringUtils.isBlank(bean.getBroker()))
    		//addActionError(getText("error.admin.broker.required"));
    	if(StringUtils.isBlank(bean.getEmail()))
    		addActionError(getText("error.admin.email.required"));
    	else if(StringUtils.contains(bean.getEmail(), " "))
	 		addActionError("Email Id should not contain white spaces");
    	else if("invalid".equalsIgnoreCase(validation.emailValidate(bean.getEmail())))
    		addActionError(getText("error.admin.email.invalid"));
    	else if(!"edit".equals(bean.getMode()) && service.checkEmailAvailability(bean.getEmail())) {
    		addActionError("Email Id already Available. Please Try with a New One.");
    	}
    	if(StringUtils.isBlank(bean.getMobileNo()))
    		addActionError(getText("error.admin.mobile.required"));
    	else if(!StringUtils.isNumeric(bean.getMobileNo()))
    		addActionError(getText("error.admin.mobile.must.be.numeric"));
    	else if(!"edit".equals(bean.getMode()) && service.checkMobileNoAvailability(bean.getMobileNo())) {
    		addActionError("Mobile No. already Available. Please Try with a New One.");
    	}
    	if(StringUtils.isBlank(bean.getStatus()))
    		addActionError(getText("error.admin.status.required"));
    }
    public String getPopUpList(){
		String proposalNo=String.valueOf(session.get("proposalNo")==null?"":session.get("proposalNo"));
		
		bean.setProposalNo(proposalNo);
		service.getPopUpList(bean,branchCode);
		return "clauses";
	} 
    public String getOthers(){
    	try{
    		  String proposalNo=String.valueOf(session.get("proposalNo")==null?"":session.get("proposalNo"));
    		  String openCoverNo=String.valueOf(session.get("openCoverNo")==null?"":session.get("openCoverNo"));
	    	  bean.setProposalNo(proposalNo);
	    	  bean.setOpenCoverNo(openCoverNo);
	    	  if("proceed".equalsIgnoreCase(bean.getFrom())){
	    		    boolean covers = validateOptionalCovers();
	    		    if(!hasActionErrors()){
	    		    	int i=0;
	    		    	   i=service.saveOptionalCovers(bean);
		    	        if(i>0){
		    	        	service.getOptionalCoverList(branchCode, bean);
		    	        	bean.setFrom1("second");
		    	        }
	    		    }else{
	    		    	bean.setFrom1("first"); 
	   		            service.getOptionalCoverList(branchCode, bean);
	    		    }
		      }else{
		    	 bean.setFrom1("first"); 
		    	 service.getOptionalCoverList(branchCode, bean);
		    	 if(StringUtils.isBlank(bean.getEffectDate())){
			    	 Calendar cal=Calendar.getInstance();
			    	 int date=cal.get(Calendar.DAY_OF_MONTH);
			    	 int month=cal.get(Calendar.MONTH)+1;
			    	 int year=cal.get(Calendar.YEAR);
			    	 bean.setEffectDate(date+"-"+month+"-"+year);
		    	 }
		      }
    	}catch(Exception e){
    		e.printStackTrace();
    	}
      return "others";    	
    }
    private boolean validateOptionalCovers(){
    	boolean result=true;
    	try{
    		if(StringUtils.isBlank(bean.getEffectDate())){
    			addActionError(getText("optional.effective.date.empty"));
    	    }
    		if(null!=bean.getOptionalId()){
    			int i=0;
    			Iterator<String> iterator=bean.getOptionalId().iterator();
    			while(iterator.hasNext()){
    			   if(!"false".equalsIgnoreCase(iterator.next())){
    				  i=i+1; 
    			   }
    			}
    			if(i==0){
    				result=false;
    			}
    		}else{
    			addActionError(getText("optional.covers.empty"));
    		}
    		
    	}catch (Exception e) {
			e.printStackTrace();
		}
    	return result;
    }
    
    // Payment Master
    public String paymentMas(){
    	bean.setPaymentMasterList(service.getPaymentMas(bean));
    	return "paymentmaster";
    }
    
    public String editPaymentMas() throws Exception{
    	if("edit".equalsIgnoreCase(bean.getMode())){
    	service.getEditPaymentMas(bean);
    	}
    	/*bean.setProfileId(service.paymentDecrypt(bean.getProfileId()));
    	bean.setAccessKey(service.paymentDecrypt(bean.getAccessKey()));
    	bean.setSecretKey(service.paymentDecrypt(bean.getSecretKey()));*/
    	return "paymentmaster";
    }
    
    public String updatePaymentMas() throws Exception{
    	validatePayMas();
    	if(!hasActionErrors()){
    		//bean.setProfileId(service.paymentEncrypt(bean.getProfileId()));
        	//bean.setAccessKey(service.paymentEncrypt(bean.getAccessKey()));
        	//bean.setSecretKey(service.paymentEncrypt(bean.getSecretKey()));
    		service.getUpdatePayMas(bean);
    		if("edit".equalsIgnoreCase(bean.getMode())){
    			addActionMessage(getText("update.success"));
    		}
    		if("add".equalsIgnoreCase(bean.getMode())){
    			addActionMessage(getText("insert.success"));
    		}
    		bean.setPaymentMasterList(service.getPaymentMas(bean));
    		bean.setMode("");
    	}
    	return "paymentmaster";
    }
	private void validatePayMas() {
		if(StringUtils.isBlank(bean.getProfileId()))
    		addActionError(getText("error.profile.id"));
		if(StringUtils.isBlank(bean.getAccessKey()))
    		addActionError(getText("error.access.key"));
		if(StringUtils.isBlank(bean.getSecretKey()))
    		addActionError(getText("error.secret.key"));
		if(StringUtils.isBlank(bean.getExpiryDate()))
    		addActionError(getText("error.expiry.date"));
		else if("invalid".equalsIgnoreCase(validation.checkDate(bean.getExpiryDate())))
    		addActionError(getText("error.expiry.date.invalid"));
    	else if(service.diffInDays(bean.getExpiryDate(),"") >= 0)
			addActionError(getText("error.enter.expiry.date.greater.sysdate"));
		if(StringUtils.isBlank(bean.getCurrencyType()))
    		addActionError(getText("error.currency.type"));
		else if("add".equalsIgnoreCase(bean.getMode())){
			if((service.checkCurrencyType(bean)) > 0)
				addActionError(getText("error.currency.combination"));
		}
		if(StringUtils.isBlank(bean.getIntiExpiryDate()))
    		addActionError(getText("error.inti.expiry.date"));
		else if("invalid".equalsIgnoreCase(validation.checkDate(bean.getIntiExpiryDate())))
    		addActionError(getText("error.inti.expiry.date.invalid"));
    	else if(service.diffInDays(bean.getIntiExpiryDate(),"") >= 0)
			addActionError(getText("error.enter.inti.expiry.date.greater.sysdate"));
    	else if(!StringUtils.isBlank(bean.getExpiryDate())){
    	 if(service.diffInDays(bean.getExpiryDate(),bean.getIntiExpiryDate()) >= 0)
			addActionError(getText("error.enter.inti.expiry.date.lessthen.expiry.date"));
    	}
		if(StringUtils.isBlank(bean.getIntiMobileNo()))
    		addActionError(getText("error.inti.mobile.no"));
    	else if(!StringUtils.isNumeric(bean.getIntiMobileNo()) && StringUtils.length(bean.getIntiMobileNo()) < 10)
	 		addActionError("error.invalid.inti.mob.no");
		if(StringUtils.isBlank(bean.getIntiEMailId()))
    		addActionError(getText("error.email"));
    	else if(StringUtils.contains(bean.getIntiEMailId(), " "))
	 		addActionError("error.email.space");
    	else if("invalid".equalsIgnoreCase(validation.emailValidate(bean.getIntiEMailId())))
    		addActionError(getText("error.email.invalid"));
    	if(StringUtils.isBlank(bean.getStatus()))
    		addActionError(getText("error.status"));
    	
    	
		
	}
	
	public List<Object> getDefaultMenus(){
		return service.getdefaultMenus(branchCode);
	}
	
	public String changePass(){
		return "changePassAdmin";
	}
	
	public String updatePass(){
    	LogManager.push("Method to checkPwd()");
    	validatePassword();
    	try{
	    	if(getActionErrors().isEmpty()){
	    		bean.setPwd(pass.crypt(bean.getPwd()));
				service.changePassword(bean);
				bean.setDisplay("passwordsuccess");
	    	}
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	return "changePassAdmin";
    }
	public void validatePassword()
    {
    	if(StringUtils.isEmpty(bean.getPwd())){
    		addActionError(getText("error.broker.newpassword"));
    	}else if(StringUtils.contains(bean.getPwd(), " "))
	 		addActionError("Password should not contain white spaces");
    	if(StringUtils.isEmpty(bean.getRpassword())){
    		addActionError(getText("error.broker.repassword"));
    	}if(!bean.getRpassword().equals(bean.getPwd())){
    		addActionError(getText("error.different"));
    	}
    }
	
}