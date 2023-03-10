package com.maan.adminnew.customerManagement;

import java.util.List;
import java.util.Map;
import com.maan.common.LogManager;
import com.maan.common.Validation;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class CustomerMgtAction extends ActionSupport implements ModelDriven<CustomerMgtBean>
{
	private static final long serialVersionUID = 1L;
	private CustomerMgtBean bean = new CustomerMgtBean();
	Validation validation=new Validation();
	CustomerMgtService service=new CustomerMgtService();
	Map<String, Object> session=ActionContext.getContext().getSession();
	String branchCode=(String)session.get("BranchCode");
	String login_id=(String)session.get("user");
	private List <Object> customerList;
	private List <Object> customerInfo;
	private List <Object> commisionDetails;
	private List <Object> openCover;
	
	public List<Object> getCustomerList() {
		return customerList;
	}

	public void setCustomerList(List<Object> customerList) {
		this.customerList = customerList;
	}

	public List<Object> getCustomerInfo() {
		return customerInfo;
	}

	public void setCustomerInfo(List<Object> customerInfo) {
		this.customerInfo = customerInfo;
	}

	public List<Object> getCommisionDetails() {
		return commisionDetails;
	}

	public void setCommisionDetails(List<Object> commisionDetails) {
		this.commisionDetails = commisionDetails;
	}

	public List<Object> getOpenCover() {
		return openCover;
	}

	public void setOpenCover(List<Object> openCover) {
		this.openCover = openCover;
	}

	public CustomerMgtBean getModel() {
		return bean;
	}
	
	public String getABList(){
    	LogManager.push("ENTER-->Method to getABList");
    	customerList=service.getAdminCustomerList(bean.getAgencyCode());
		LogManager.info("getABList() - Exit");
		/*if("mainTab".equals(bean.getMode()))
			return "userListAjax";
		else */
		return "customerList";
    }
	
	public String view(){
    	LogManager.push("Method to view");
    	customerInfo=service.getCustomerDetails(bean);
    	//commisionDetails=service.getCommisionData(bean.getCagencyCode());
    	openCover=service.getOpenCoverList(bean);
    	if("opencover".equals(bean.getReqFrom()))
    		bean.setIndex("1");
		LogManager.info("view() - Exit");
    	return "view";
    }
	
	public String getCustomerAjax(){
    	if("opencover".equals(bean.getReqFrom())){
    		openCover=service.getOpenCoverList(bean);
    	}else if("customerLists".equals(bean.getReqFrom())){
        	customerList=service.getCustomerListAjax(bean.getAgencyCode(), bean.getSearchBy(), bean.getSearchValue());
    	}
    	return "adminAjax";
    }
	
	public String edit(){
		LogManager.push("Method to edit");
		try{
			if(!"new".equals(bean.getMode())){
		    	customerInfo=service.getCustomerDetails(bean);
		    	commisionDetails=service.getCommisionData(bean.getUagencyCode());
			}
			LogManager.info("edit() - Exit");
		}catch(Exception e){
			LogManager.info(e);
			e.printStackTrace();
		}
		return "edit";
	}
	public String coreCustomer(){
		return "coreCustomer";
	}
	public List<Object> getCoreCustomerList(){
		return service.getCoreCustomerList(branchCode);
	}
	public String editCoreCustomer(){
		boolean result=false;
		if("update".equalsIgnoreCase(bean.getMode())){
			result=service.updateCoreCustomer(bean);
		}
		service.editCoreCustomer(bean);
		return result?"coreCustomer":"editCoreCustomer";
	}
}