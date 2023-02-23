package com.maan.Home.Service;

import java.util.List;
import com.maan.Home.Controller.AdminHomeBean;
import com.maan.Home.DAO.AdminHomeDAO;

public class AdminHomeService{
	final AdminHomeDAO dao=new AdminHomeDAO();
	
	public List<AdminHomeBean> getSchemeList(final AdminHomeBean bean, String searchField, String searchString, String searchOper) {
		return dao.getSchemeList(bean, searchField, searchString, searchOper);
	}
	
	public List<AdminHomeBean> getCoveragesList(final AdminHomeBean bean, String searchField, String searchString, String searchOper) {
		return dao.getCoveragesList(bean, searchField, searchString, searchOper);
	}
	
	public List<Object> getProductList(String branchCode){
		return dao.getProductList(branchCode);
	}
	
	public List<Object> getBranchList(){
		return dao.getBranchList();
	}
	
	public int[] validateScheme(final AdminHomeBean bean, String operation){
		return dao.validateScheme(bean, operation);
	}
	
	public int schemeModify(final AdminHomeBean bean, String operation, String id){
		return dao.schemeModify(bean, operation, id);
	}
}