package com.maan.adminnew.userManagement;

import java.util.List;

public class UserMgtService
{	
	UserMgtDAO dao=new UserMgtDAO();
	
    public List <Object> getAdminUserList(final UserMgtBean ba, String agencyCode, String mode1, String branchCode){
    	return dao.getAdminUserList(ba,agencyCode, mode1, branchCode);
    }
    public List <Object> getUserDetails(final UserMgtBean ba, String uagencyCode){
    	return dao.getUserDetails(ba, uagencyCode);
    }
    public List <Object> getCommisionData(String uagencyCode, String agencyCode, String branchCode){
    	return dao.getCommisionData(uagencyCode, agencyCode, branchCode);
    }
    public List<Object> getUserListAjax(String agencyCode, String searchBy, String searchValue, String mode1){
    	  return dao.getUserListAjax(agencyCode, searchBy, searchValue, mode1);
    }
    public List <Object> getOCCertificate(String agencyCode){
    	return dao.getOCCertificate(agencyCode);
    }
    /*public List <Object> getProducts(final UserMgtBean ba){
    	return dao.getProducts(ba);
    }*/
    public void insertUserInfo(Object[]args, Object[] args1){
   	 dao.insertUserInfo(args, args1);
   }
    public void updateUserInfo(Object[]args, Object[] args1){
   	 dao.updateUserInfo(args, args1);
   }
	public void updateLoginUserDtl(UserMgtBean bean) {
		dao.updateLoginUserDtl(bean);
	}
	public void updateShortUrl(UserMgtBean bean, String shorternURL) {
		dao.updateShortUrl(bean,shorternURL);
	}
	public boolean checkShortUrlNotExist(UserMgtBean bean) {
		return dao.checkShortUrlNotExist(bean);
	}
}