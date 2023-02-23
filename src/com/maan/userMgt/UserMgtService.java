package com.maan.userMgt;

import java.util.List;

import com.maan.common.login.CommonBean;

public class UserMgtService {

	private UserMgtDAO dao = new UserMgtDAO();

	public void insertUserInfo(Object[] args1){
		dao.insertUserInfo( args1);
	}
	public List <Object> getCommisionData(String uagencyCode, String agencyCode, String branchCode){
		return dao.getCommisionData(uagencyCode, agencyCode, branchCode);
	}
	public void getUserDetails(final UserMgtBean ba, String uagencyCode){
		dao.getUserDetails(ba, uagencyCode);
	}
	public void updateUserQuote(String loginId, String applicationNo) {
		dao.updateUserQuote(loginId, applicationNo);
	}
	/*public String getOACode(String branchCode) {
		return dao.getOACode(branchCode);
	}*/
	public void updateUserInfo(Object[] args1){
		dao.updateUserInfo(args1);
	}
	public void updateSession(String userId, String sessionId) {
		dao.updateSession(userId,sessionId);
	}
	public String checkUserLoginId(UserMgtBean bean, String appId) {
		return dao.checkUserLoginId(bean,appId);
	}
	public void updateCustomerIdMotorDataDetail(String[] args2) {
		dao.updateCustomerIdMotorDataDetail(args2);
	}
	public int validateMobile(String mobileNo) {
		return dao.validateMobile(mobileNo);
	}
	public int validateMail(String email, String applicationNo) {
		return dao.validateMail(email,applicationNo);
	}
	public void getCustomerDetails(UserMgtBean bean) {
		dao.getCustomerDetails(bean);
	}
	/*public void updateCustomerDetail(UserMgtBean bean) {
		dao.updateCustomerDetail(bean);
	}*/
	public void updateCustomerPersonal(String userId, String applicationNo) {
		dao.updateCustomerPersonal(userId,applicationNo);
	}
	public String getEmailCount(UserMgtBean bean) {
		return dao.getEmailCount(bean);
	}
	public void addProducts(String loginId, String agencyCode, String b2cBrokerId) {
		dao.addProducts(loginId, agencyCode, b2cBrokerId);
	}
	public String getCustomerEmail(String userLoginId1, String appId) {
		return dao.getCustomerEmail(userLoginId1,appId);
	}
	public void updateLoginId(UserMgtBean bean) {
		dao.updateLoginId(bean);
	}
	public void insertUserInfo(Object[] args1,String type){
		dao.insertUserInfo(args1,type);
	}
	public String insertCustomerMob(UserMgtBean bean, String type,String oldCustomerNo) {
		return dao.insertCustomerMob(bean,type,oldCustomerNo);
	}
}
