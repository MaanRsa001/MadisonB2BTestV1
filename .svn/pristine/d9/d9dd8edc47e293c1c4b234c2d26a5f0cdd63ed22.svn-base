package com.maan.adminnew.AdminMgt;

import java.util.List;
import java.util.Map;

import com.maan.common.util.Encrypter;

public class AdminMgtService
{	
	AdminMgtDAO dao=new AdminMgtDAO();
	
	public List<Object>UtypeList(String branchCode,String appId){
		return dao.UtypeList(branchCode, appId);
	}
	public List<Object>getMenuList(final AdminMgtBean bean,String branchCode){
		return dao.getMenuList(bean, branchCode);
	}
	public List<Object>adminList(final AdminMgtBean bean,String branchCode,String appId){
		return dao.adminList(bean, branchCode, appId);
	}
	public void getAdminInfo(final AdminMgtBean bean,String branchCode, String appId){
		dao.getAdminInfo(bean, branchCode, appId);
	}
	public void getMenuInfo(final AdminMgtBean bean,String branchCode){
		dao.getMenuInfo(bean, branchCode);
	}
	public int insNewMenu(final AdminMgtBean bean,String branchCode){
		return dao.insNewMenu(bean, branchCode);
	}
	public int updateMenu(final AdminMgtBean bean,String branchCode){
		return dao.updateMenu(bean, branchCode);
	}
	public int insNewAdmin(final AdminMgtBean bean,String branchCode, String appId){
		return dao.insNewAdmin(bean, branchCode, appId);
	}
	public int updateAdmin(final AdminMgtBean bean,String branchCode, String appId){
		return dao.updateAdmin(bean, branchCode, appId);
	}
	public String getBranch(String branchCode){
		return dao.getBranch(branchCode);
	}
	public List<Object> getDashBoard(String loginId,AdminMgtBean bean, String productId,String branchCode) {
		return dao.getDashBoard(loginId,bean,productId,branchCode);
	}
	public void getPopUpList(AdminMgtBean bean,String branchCode) {
		dao.getPopUpList(bean,branchCode);		
	}
	public int saveOptionalCovers(AdminMgtBean bean) {
		return dao.saveOptionalCovers(bean);
	}
	public void getOptionalCoverList(String branchCode, AdminMgtBean bean) {
		dao.getOptionalCoverList(branchCode, bean);
	}
	public List<Map<String,String>> getUwGradeList() {
		 return dao.getUwGradeList();
	}
	public Map<String,List<Object>>  getProductList(AdminMgtBean bean, String branchCode) {
		return dao.getProductList(bean,branchCode);
	}
	
	public List<Map<String,Object>> getPaymentMas(AdminMgtBean bean) {
		return dao.getPaymentMas(bean);
		
	}
	public List<Map<String, Object>> getEditPaymentMas(AdminMgtBean bean) {
		return dao.getEditPaymentMas(bean);
		
	}
	public String paymentEncrypt(String data) throws Exception{
		String enc = new Encrypter().encrypt(data);
		 return enc;
	}
	 
	public String paymentDecrypt(String encryptedData) throws Exception{
		String enc = new Encrypter().decrypt(encryptedData);
		return enc;
	 }
	 
	public int getUpdatePayMas(AdminMgtBean bean) {
		return dao.getUpdatePayMas(bean);
	}
	public long diffInDays(String policyStartDate,String policyEndDate) {
		return dao.diffInDays(policyStartDate, policyEndDate);
	}
	public int checkCurrencyType(AdminMgtBean bean) {
		return dao.checkCurrencyType(bean);
	}
	public List<Object> getdefaultMenus(String branchCode) {
	 	return dao.getdefaultMenus(branchCode);
	}
	public void changePassword(AdminMgtBean bean) {
		dao.changePassword(bean);
	}
	public boolean checkEmailAvailability(String email) {
		return dao.checkEmailAvailability(email);
	}
	public boolean checkMobileNoAvailability(String mobile) {
		return dao.checkMobileNoAvailability(mobile);
	}
}