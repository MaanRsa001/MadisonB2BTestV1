package com.maan.adminnew.report;

import java.util.List;
import java.util.Map;

public class ReportService
{	
	ReportDAO dao=new ReportDAO();
	
	public Map<Object, Object> schedule(final String broker){
    	return dao.schedule(broker);
    }
	public List<Object> getPolicyReport(final Object[] obj){
    	return dao.getPolicyReport(obj);
    }
	public List<Object> getUwList(String branchCode){
		return dao.getUwList(branchCode);
	}
	public List<Object> getLcSmartList(final ReportBean bean){
		return dao.getLcSmartList(bean);
	}
	public List<Object> getCommodityList(final String branchCode){
		return dao.getCommodityList(branchCode);
	}
	public List<Object> getCoverList(final String branchCode){
		return dao.getCoverList(branchCode);
	}
	public List<Object> getTransportModeList(final String branchCode){
		return dao.getTransportModeList(branchCode);
	}
	public List<Object> getSmartList(final Object[] obj){
		return dao.getSmartList(obj);
	}
	public List<Object> getBranchReport(ReportBean bean){
		return dao.getBranchReport(bean);
	}
	public List<Object> getExchangeReport(ReportBean bean){
    	return dao.getExchangeReport(bean);
    }
	public List<Object> getBranchName(String branchCode,String loginId){
    	return dao.getBranchName(branchCode,loginId);
    }
	public List<Object> getOpenCoverList(ReportBean bean, String branchCode){
    	return dao.getOpenCoverList(bean, branchCode);
    }
	public List<Object> getConsigneeList(ReportBean bean, String branchCode){
    	return dao.getConsigneeList(bean, branchCode);
    }
	public List <Object> getAdminBrokerList(String branchCode, String appId){
    	return dao.getAdminBrokerList(branchCode, appId);
    }
	public List <Object> getCountrySmartList(){
    	return dao.getCountrySmartList();
    }
	public List<Object> getCertificatesList(ReportBean bean, String branchCode) {
		return dao.getCertificatesList(bean,branchCode);
	}
	public String getBrokerLoginId(ReportBean bean) {
		return dao.getBrokerLoginId(bean);
	}
	public List<Map<String, Object>> getSmartReportList(String branchCode,
			String loginId, String userType, ReportBean bean ) {
		String condition="";
		/*if("CCAPRQ".equalsIgnoreCase(bean.getReportType()) || "SAPRQM".equalsIgnoreCase(bean.getReportType()) || "UWAPRQM".equalsIgnoreCase(bean.getReportType()) ){
			condition = " where status='"+bean.getStatus()+"'";
			if(!"admin".equalsIgnoreCase(userType)){
				condition = " and login_id='"+loginId+"'";
			}
		}*/
		return dao.getSmartReportList(branchCode,loginId,condition,bean);
	}
	public List<Map<String, Object>> getDropDownList(ReportBean bean,
			String string) {
		return dao.getDropDownList(bean,string);
	}
}