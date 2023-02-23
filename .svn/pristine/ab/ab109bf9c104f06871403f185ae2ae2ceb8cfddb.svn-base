package com.maan.adminnew.branchMgt;

import java.io.File;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.omg.PortableServer.Servant;

import sun.tools.tree.AddExpression;

import com.maan.adminnew.common.CommonService;
import com.maan.common.Validation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;



@SuppressWarnings("serial")
public class BranchAction extends ActionSupport implements ModelDriven<BranchBean>  {
	BranchBean bean = new BranchBean();
	BranchService service=new BranchService();
	Validation validation=new Validation();
	private final String FILE_PATH=ServletActionContext.getRequest().getSession().getServletContext().getRealPath(getText("IMAGE_PATH"));
	private String branchCode;
	private String countryAjax;
	 
	public String list() {
		service.loadList(bean);
		bean.setDisplay("list");
		return "branch";
	}
	
	public String edit() {
		if("edit".equals(bean.getMode())){
			service.getSelectedBroker(bean);
			loadImages(bean);
		}
		service.setDropDown(bean);
		bean.setDisplay("edit");
		return "branch";
	}
	
	public String save() {	
		validateSaveBranch();
		if(!hasActionErrors()){
			if(service.saveBranch(bean,FILE_PATH)){
				addActionMessage("Inserted Successfully");
				 list();
			}else {
				service.setDropDown(bean);
				addActionError("Error in Insertion");
				bean.setDisplay("edit");
			}
		}else{
			service.setDropDown(bean);
			bean.setDisplay("edit");
		}
		return "branch";
	}

	private void validateSaveBranch() {
		if(StringUtils.isBlank(bean.getBranchName()))
    		addActionError(getText("branchError.enterBranchName"));
		if(StringUtils.isBlank(bean.getBranchPrefix()))
    		addActionError(getText("branchError.enterBranchPrefix"));
		if(StringUtils.isEmpty(bean.getBelongingBranch()))
			addActionError(getText("branchError.belongingBranch"));
		if(StringUtils.isBlank(bean.getAddress1()))
    		addActionError(getText("branchError.enterAddress1"));
		if(StringUtils.isBlank(bean.getAddress2()))
    		addActionError(getText("branchError.enterAddress2"));
		if(StringUtils.isBlank(bean.getAddress3()))
    		addActionError(getText("branchError.enterAddress3"));
		if(StringUtils.isBlank(bean.getCity()))
    		addActionError(getText("branchError.selectCity"));		
		if(StringUtils.isBlank(bean.getCountry()))
    		addActionError(getText("branchError.selectCountry"));
		if(StringUtils.isBlank(bean.getCurrency()))
    		addActionError(getText("branchError.selectCurrency"));
		if(StringUtils.isBlank(bean.getPhone()))
    		addActionError(getText("branchError.enterPhone"));		
		if(StringUtils.isBlank(bean.getFax()))
    		addActionError(getText("branchError.enterFax"));
		if(StringUtils.isBlank(bean.getEmail()))
    		addActionError(getText("branchError.enterEmail"));
		if(StringUtils.isBlank(bean.getStatus()))
    		addActionError(getText("branchError.selectStatus"));	
		if(StringUtils.isEmpty(bean.getTax()))
			addActionError(getText("branchError.selectTax"));
		if(StringUtils.isBlank(bean.getEffectiveDate()))
    		addActionError(getText("branchError.selectEffectiveDate"));
		if(StringUtils.isBlank(bean.getOriginCountry()))
    		addActionError(getText("branchError.selectOriginCountry"));
		if(StringUtils.isBlank(bean.getDestiCountry()))
    		addActionError(getText("branchError.selectdestiCountry"));
		if(StringUtils.isBlank(bean.getDecimalPlaces()))
    		addActionError(getText("branchError.enterDecimalPlaces"));
		if(StringUtils.isBlank(bean.getLang()))
    		addActionError(getText("branchError.enterLang"));
		if("new".equals(bean.getMode())){
		if(StringUtils.isBlank(bean.getHeaderImageFileName()))
    		addActionError(getText("branchError.selectHeaderImage"));
		if(StringUtils.isBlank(bean.getSignImageFileName()))
    		addActionError(getText("branchError.selectSignImage"));
		if(StringUtils.isBlank(bean.getStampImageFileName()))
    		addActionError(getText("branchError.selectStampImage"));
		if(StringUtils.isBlank(bean.getFooterImageFileName()))
    		addActionError(getText("branchError.selectFooterImage"));
		}
		
		
	}
	private void loadImages(BranchBean bean)
	{
		bean.setHeaderImage(new File(bean.getHeaderName()));
		bean.setFooterImage(new File(bean.getFooterName()));
		bean.setStampImage(new File(bean.getStampImageName()));
		bean.setSignImage(new File(bean.getSignImageName()));
	}

	public BranchBean getModel() {		
		return bean;
	}
	public String getCity(){
		bean.setCityList(service.getCities(countryAjax));
		return "branchAjax";
	}
	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}
	public String getBranchCode() {
		return branchCode;
	}
	public void setCountryAjax(String countryAjax) {
		this.countryAjax = countryAjax;
	}

	public String getCountryAjax() {
		return countryAjax;
	}
	
	// Added New Methods for Branch Master
	public String branchList() {
		service.loadListNew(bean);
		bean.setMode("list");
		return "branchMaster";
	}
	
	public String editBranch() {
		if("edit".equals(bean.getMode())){
			service.getSelectedBranch(bean);
			loadImages(bean);
		}
		service.setDropDownNew(bean);
		bean.setDisplay("edit");
		return "branchMaster";
	}
	
	public String saveBranch() {	
		validateSaveBranchNew();
		if(!hasActionErrors()){
			/*if(StringUtils.isNotBlank(bean.getHeaderImageFileName()))
	    		bean.setHeaderName(bean.getHeaderImageFileName());
			if(StringUtils.isNotBlank(bean.getSignImageFileName()))
				bean.setSignImageName(bean.getSignImageFileName());
			if(StringUtils.isNotBlank(bean.getStampImageFileName()))
				bean.setStampImageName(bean.getStampImageFileName());
			if(StringUtils.isNotBlank(bean.getFooterImageFileName()))
				bean.setFooterName(bean.getFooterImageFileName());*/
			
			if(service.saveBranchNew(bean,FILE_PATH)){
				addActionMessage("Inserted Successfully");
				branchList();
			}else {
				service.setDropDownNew(bean);
				addActionError("Error in Insertion");
				bean.setDisplay("edit");
			}
		}else{
			service.setDropDownNew(bean);
			bean.setDisplay("edit");
		}
		return "branchMaster";
	}
	
	private void validateSaveBranchNew() {
		if(StringUtils.isBlank(bean.getBranchName()))
    		addActionError(getText("branchError.enterBranchName"));
		if(StringUtils.isBlank(bean.getBranchPrefix()))
    		addActionError(getText("branchError.enterBranchPrefix"));
		/*if(StringUtils.isEmpty(bean.getBelongingBranch()))
			addActionError(getText("branchError.belongingBranch"));*/
		if(StringUtils.isBlank(bean.getAddress1()))
    		addActionError(getText("branchError.enterAddress1"));
		if(StringUtils.isBlank(bean.getAddress2()))
    		addActionError(getText("branchError.enterAddress2"));
		if(StringUtils.isBlank(bean.getAddress3()))
    		addActionError(getText("branchError.enterAddress3"));
		if(StringUtils.isBlank(bean.getCity()))
    		addActionError(getText("branchError.selectCity"));		
		if(StringUtils.isBlank(bean.getCountry()))
    		addActionError(getText("branchError.selectCountry"));
		if(StringUtils.isBlank(bean.getCurrency()))
    		addActionError(getText("branchError.selectCurrency"));
		if(StringUtils.isBlank(bean.getPolicyFee()))
    		addActionError("Enter Policy Fees (%) ");
		else if("invalid".equalsIgnoreCase(validation.validDouble(bean.getPolicyFee())))
			addActionError("Enter Valid Policy Fees (%) ");
		if(StringUtils.isBlank(bean.getPhone()))
    		addActionError(getText("branchError.enterPhone"));		
		if(StringUtils.isBlank(bean.getFax()))
    		addActionError(getText("branchError.enterFax"));
		if(StringUtils.isBlank(bean.getEmail()))
    		addActionError(getText("branchError.enterEmail"));
		else if(StringUtils.contains(bean.getEmail(), " "))
	 		addActionError("Email Id should not contain white spaces");
		else if("invalid".equalsIgnoreCase(validation.emailValidate(bean.getEmail())))
			addActionError(getText("error.admin.email.invalid"));
		if(StringUtils.isBlank(bean.getStatus()))
    		addActionError(getText("branchError.selectStatus"));	
		if(StringUtils.isEmpty(bean.getTax()))
			addActionError(getText("branchError.selectTax"));
		if(StringUtils.isBlank(bean.getEffectiveDate()))
    		addActionError(getText("branchError.selectEffectiveDate"));
		if(StringUtils.isBlank(bean.getOriginCountry()))
    		addActionError(getText("branchError.selectOriginCountry"));
		if(StringUtils.isBlank(bean.getDestiCountry()))
    		addActionError(getText("branchError.selectdestiCountry"));
		if(StringUtils.isBlank(bean.getDecimalPlaces()))
    		addActionError(getText("branchError.enterDecimalPlaces"));
		if(StringUtils.isBlank(bean.getLang()))
    		addActionError(getText("branchError.enterLang"));
		if("add".equals(bean.getMode()) || "Y".equalsIgnoreCase(bean.getImgMode())){
			if(StringUtils.isBlank(bean.getHeaderImageFileName()))
	    		addActionError(getText("branchError.selectHeaderImage"));
			if(StringUtils.isBlank(bean.getSignImageFileName()))
	    		addActionError(getText("branchError.selectSignImage"));
			if(StringUtils.isBlank(bean.getStampImageFileName()))
	    		addActionError(getText("branchError.selectStampImage"));
			if(StringUtils.isBlank(bean.getFooterImageFileName()))
	    		addActionError(getText("branchError.selectFooterImage"));
		}
		
	}
	public String ajaxValidation(){

		if(StringUtils.isBlank(bean.getBranchName()))
    		addActionError(getText("branchError.enterBranchName"));
		if(StringUtils.isBlank(bean.getBranchPrefix()))
    		addActionError(getText("branchError.enterBranchPrefix")); 
		/*if(StringUtils.isEmpty(bean.getBelongingBranch()))
			addActionError(getText("branchError.belongingBranch"));*/
		if(StringUtils.isBlank(bean.getAddress1()))
    		addActionError(getText("branchError.enterAddress1"));
		if(StringUtils.isBlank(bean.getAddress2()))
    		addActionError(getText("branchError.enterAddress2"));
		if(StringUtils.isBlank(bean.getAddress3()))
    		addActionError(getText("branchError.enterAddress3"));
		if(StringUtils.isBlank(bean.getCity()))
    		addActionError(getText("branchError.selectCity"));		
		if(StringUtils.isBlank(bean.getCountry()))
    		addActionError(getText("branchError.selectCountry"));
		if(StringUtils.isBlank(bean.getCurrency()))
    		addActionError(getText("branchError.selectCurrency"));
		if(StringUtils.isBlank(bean.getPolicyFee()))
    		addActionError("Enter Policy Fees (%) ");
		else if("invalid".equalsIgnoreCase(validation.validDouble(bean.getPolicyFee())))
			addActionError("Enter Valid Policy Fees (%) ");
		if(StringUtils.isBlank(bean.getPhone()))
    		addActionError(getText("branchError.enterPhone"));		
		if(StringUtils.isBlank(bean.getFax()))
    		addActionError(getText("branchError.enterFax"));
		if(StringUtils.isBlank(bean.getEmail()))
    		addActionError(getText("branchError.enterEmail"));
		else if(StringUtils.contains(bean.getEmail(), " "))
	 		addActionError("Email Id should not contain white spaces");
		else if("invalid".equalsIgnoreCase(validation.emailValidate(bean.getEmail())))
			addActionError(getText("error.admin.email.invalid"));
		if(StringUtils.isBlank(bean.getStatus()))
    		addActionError(getText("branchError.selectStatus"));	
		if(StringUtils.isEmpty(bean.getTax()))
			addActionError(getText("branchError.selectTax"));
		if(StringUtils.isBlank(bean.getEffectiveDate()))
    		addActionError(getText("branchError.selectEffectiveDate"));
		if(StringUtils.isBlank(bean.getOriginCountry()))
    		addActionError(getText("branchError.selectOriginCountry"));
		if(StringUtils.isBlank(bean.getDestiCountry()))
    		addActionError(getText("branchError.selectdestiCountry"));
		if(StringUtils.isBlank(bean.getDecimalPlaces()))
    		addActionError(getText("branchError.enterDecimalPlaces"));
		if(StringUtils.isBlank(bean.getLang()))
    		addActionError(getText("branchError.enterLang"));
		/*if("add".equals(bean.getMode()) || "Y".equalsIgnoreCase(bean.getImgMode())){
			if(StringUtils.isBlank(bean.getHeaderImageFileName()))
	    		addActionError(getText("branchError.selectHeaderImage"));
			if(StringUtils.isBlank(bean.getFooterImageFileName()))
	    		addActionError(getText("branchError.selectFooterImage"));
			if(StringUtils.isBlank(bean.getSignImageFileName()))
	    		addActionError(getText("branchError.selectSignImage"));
			if(StringUtils.isBlank(bean.getStampImageFileName()))
	    		addActionError(getText("branchError.selectStampImage"));
			
		}*/
		if(!hasActionErrors()){
			addActionMessage("Success");
		}
		
	return "branchAjax";
	}

}
