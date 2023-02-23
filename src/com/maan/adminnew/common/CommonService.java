package com.maan.adminnew.common;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.SystemUtils;

import com.maan.common.LogManager;

public class CommonService {
	CommonDAO dao=new CommonDAO();
	 public List<Object> getAdminInfo(final String user){
    	return dao.getAdminInfo(user);
    }
    public List<Object> getMenuList(final String menuIds, final String bCode, final String productId){
        return dao.getMenuList(menuIds, bCode, productId);
    }
	public List <Object> getCountries(String branchCode){
    	return dao.getCountries(branchCode);
    }
	public int getExist(Object[] val,String str)
	{
		return dao.getExist(val,str);
	}
    public List <Object> getEmirates(String branchCode){
    	return dao.getEmirates(branchCode);
    }
    public List <Object> getNationalities(){
    	return dao.getNationalities();
    }
    public List <Object> getTitles(String branchCode){
    	return dao.getTitles(branchCode);
    }
    public List <Map<String,String>> getProductsDET(String branchCode, String agencyCode){
   	 return dao.getProductsDET(branchCode, agencyCode);
   }
   public List<Map<String,String>> getReferralProducts(String branchCode,String productId){
	   return dao.getReferralProducts(branchCode, productId);
   }
    /*public int getCustomerId(String branchCode){
    	return dao.getCustomerId(branchCode);
    }*/
    public String getUserCode(String branchCode){
    	return dao.getUserCode(branchCode);
    }
    public int getMaxUserId(){
    	return dao.getMaxUserId();
   }
    public void checkPassword(String[] args){
		dao.checkPassword(args);
	}
    public void updateCommission(Object[] info, Object[] obj1){
        dao.updateCommission(info, obj1);
    }
    public void insertCommission(Object[] info, Object[] obj1){
        dao.insertCommission(info, obj1);
    }
    public int checkExistProduct(String productId, String agencyCode){
        return dao.checkExistProduct(productId, agencyCode);
    }
    public List <Object> getAdminBrokerList(String branchCode, String appId){
    	return dao.getAdminBrokerList(branchCode, appId);
    }
    public List<Object> getCurrencyList() {
		return dao.getCurrencyList();
    }
    public List<Object> getBusinessTypeList(final String branchCode) {
		return dao.getBusinessTypeList(branchCode);
    }
    public HashMap getBrokerUserDetails(String branch){
    	return dao.getBrokerUserDetails(branch);
    }
    public List<Object> isBetweenTwo(Object[] val)
    {
    	return dao.isBetweenTwo(val);
    }
    
    
    public static String getApplicationPath() {
		String applicationPath = "";
		try {
			final String path=(CommonService.class).getProtectionDomain().getCodeSource().getLocation().getPath();
			if(SystemUtils.IS_OS_LINUX) {
				applicationPath = path.substring(0, path.indexOf("WEB-INF"));
			}
			else if(SystemUtils.IS_OS_WINDOWS) {
				applicationPath = path.substring(1, path.indexOf("WEB-INF"));
			}
			applicationPath = applicationPath.replaceAll("%20", " ");
			LogManager.info("Application Path==> " + applicationPath);
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		return applicationPath;
	}
    
    public static String getAppMode() {
		String appMode = "";
		try {
			final String applicationPath = getApplicationPath();
			appMode = (applicationPath.indexOf("test")!=-1||applicationPath.indexOf("Test")!=-1)?"Test":"Live";
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		return appMode;
	}
    
    public static String getAppEnvironment() {
		String appEnvironment = "";
		try {
			final String applicationPath = getApplicationPath();
			appEnvironment = (applicationPath.indexOf("test")!=-1||applicationPath.indexOf("Test")!=-1)?" - FROM TEST ENVIRONMENT":"";
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		return appEnvironment;
	}
    
    public static String getMailServerInfo() {
    	String result = "";
		try {
			final String basePath = getApplicationPath();
			result = basePath + "MailServerInfo/MailServerInfo.xml";
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		return result;
    }
    
	public List<Object> getAdminBranchList(String branchCode, String appId) {		
		return dao.getAdminBranchList(branchCode,appId);
	}
	public List<Map<String, String>> getBranchDetails(String loginId) {
		return dao.getBranchDetails(loginId);
	}
	public List<Object> getRegionList(String loginId, String accesstype) {
		return dao.getRegionList(loginId, accesstype);
	}
	public String getB2CGuestLoginId(String branchCode) {
    	return dao.getB2CGuestLoginId(branchCode);
    }
    public String getAgencyCode(String loginId) {
    	return dao.getAgencyCode(loginId);
    }
    public void updateCommissionMotor(Object[] info, Object[] obj1){
        dao.updateCommissionMotor(info, obj1);
    }
	public List<Map<String, Object>> getBranchList() {
		return dao.getBranchList();
	}
	public List<Map<String, Object>> getUSerBranchList(String agCode) {
		return dao.getUSerBranchList(agCode);
	}
	public List<Map<String, Object>> getSubBranchList(String[] branchId) {
		return dao.getSubBranchList(branchId);
	}
}
