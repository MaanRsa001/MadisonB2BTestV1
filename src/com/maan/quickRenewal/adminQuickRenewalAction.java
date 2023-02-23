package com.maan.quickRenewal;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.maan.common.LogManager;
import com.maan.common.login.CommonBean;
import com.maan.quickRenewal.bean.quickRenewalBean;
import com.maan.quickRenewal.service.quickRenewalService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;


public class adminQuickRenewalAction extends ActionSupport implements ModelDriven<quickRenewalBean> {
	private static final long serialVersionUID = 1L;
	quickRenewalBean bean= new quickRenewalBean();
	quickRenewalService service= new quickRenewalService();
	private com.maan.common.dao.CommonDAO commonDAO = new com.maan.common.dao.CommonDAO();
	private CommonBean cdao = new CommonBean();
	
	public List<Map<String, Object>> getVehList() {
		return service.getVehicleList(bean);
	}

	public quickRenewalBean getModel() {
		return bean;
	}
	
	public List<Object> getBankNamelist(){
		return commonDAO.getOptionsList("BankList", "65", getParams("BankList"));
	}
	private Object[] getParams(String type) {
		Object[] objects = null;
			objects=new String[]{"","65","01","","","","",""};
		return objects;
	}
	
	public String view(){
		bean.setAdminRenewalList(service.getAdminRenewalList(bean));
		return "adminRenewal";
	}
	
	public String cashList(){
		bean.setCashPaymentList(service.getCashPaymentList(bean));
		return "cashPending";
	}
	
	public String getList(){
		service.getCashListDetails(bean);
		bean.setVehList(service.getVehicleList(bean));	
		return "getList";
	}
	
	public String updateCash(){
		validation(bean);
		String pvOut="";
		System.out.println(bean.getMerchant_reference());
		try {
			if(!hasActionErrors()){
				int status = service.updateCashStatus(bean);
				if(status>1){
					if("Y".equalsIgnoreCase(bean.getStatus())){
						LogManager.info("Renewal Integration Calling starts.. Offline");
						Map <String ,Object> map=commonDAO.renewalIntgProcess(bean.getPolicyNo(),bean.getMerchant_reference());
						pvOut=(String)map.get("PVOUT")==null?"":(String)map.get("PVOUT");
						LogManager.info("Renewal Integration pvOut => "+pvOut);
						if(!"Success".equalsIgnoreCase(pvOut))
							addActionError("Integration Process Error");
					}
					addActionMessage("Updated Successfully");
					
					return cashList();
					
				}else {
					addActionError("Update Failed");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return getList();
	}
	private void validation(quickRenewalBean bean) {
		if(StringUtils.isBlank(bean.getStatus())){
			addActionError("Please Select Any Status");
		}
		if("R".equalsIgnoreCase(bean.getStatus())){
			if(StringUtils.isBlank(bean.getRemarks())){
				addActionError("Please Enter Remarks For the Rejection");
			}
		}
		
	}

	public List<Map<String,Object>> getApproverLoginList(){
		return service.getApproverLoginList("surveyor");
	}
	
}
