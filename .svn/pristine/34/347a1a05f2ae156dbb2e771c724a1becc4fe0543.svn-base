package com.maan.Health.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.maan.Health.controller.HealthBean;
import com.maan.Health.DAO.HealthDAO;
import com.maan.Health.controller.UploadBean;

public class HealthService {
	HealthDAO travelDAO=new HealthDAO();
	public String getPremium(HealthBean bean)
	{
		return travelDAO.getCalculatePremium(bean);
	}
	public String getUpdateInsurerInfo(HealthBean bean) {
		return travelDAO.getUpdateInsurerInfo(bean);
	}
	public void getSecondPageDts(HealthBean travelBean) {
		// TODO Auto-generated method stub
		travelDAO.getSecondPageDts(travelBean);
	}
	public List<Object> getDetailsView(HealthBean travelBean) {
		return travelDAO.getDetailsView(travelBean);
	}
	public List<Object> getPolicyView(HealthBean travelBean) {
		return travelDAO.getPolicyView(travelBean);
	}
	public int getEffectiveDate(HealthBean travelBean) {
		return travelDAO.getEffectiveDate(travelBean);
	}
	public String getGeratePolicy(HealthBean travelBean) {
		// TODO Auto-generated method stub
		return travelDAO.getGeratePolicy(travelBean);
	}
	public List<Object> getPolicyInformation(String quoteNo) {
		// TODO Auto-generated method stub
		return travelDAO.getPolicyInformation(quoteNo);
	}
	public void getBackShowQuote(HealthBean travelBean) {
		// TODO Auto-generated method stub
		travelDAO.getBackShowQuote(travelBean);
		travelBean.setDocumentList(getRequiredDocumentList(travelBean));
	}
	public List<Object> getCoverInfo(String productId,String schemecover,String travelcover,String branchCode){
		 return travelDAO.getCoverInfo(productId,schemecover,travelcover,branchCode);
	 }
	public String getAdminReferralUpdation(HealthBean travelBean) {
		// TODO Auto-generated method stub
		return travelDAO.getAdminReferralUpdation(travelBean);
	}
	public String getCancelReissue(HealthBean travelBean){
		 return travelDAO.getCancelReissue(travelBean);
	 }
	public String getConstanctDetials(String detialId,String branchCode) {
		// TODO Auto-generated method stub
		return travelDAO.getConstanctDetials(detialId,branchCode);
	}
	public void updateCorrections(HealthBean travelBean) {
		// TODO Auto-generated method stub
		travelDAO.updateCorrections(travelBean);
	}
	public List<UploadBean> getRequiredDocumentList(HealthBean bean){
    	List<UploadBean> list = travelDAO.getRequiredDocumentList(bean);
    /*	List<String> docIds = new ArrayList<String>();
    	List<String> docRemarks = new ArrayList<String>();*/
    	if(list!=null && list.size()>0){
    		for(int i=0;i<list.size();i++){
    			UploadBean updBean = list.get(i);
    			DocUploadService clmService = new DocUploadService();
    		//	docIds.add(updBean.getDocId());
    			updBean.setUpdDocumentList(clmService.getUploadedDocumentList(bean.getApplicationNo(), updBean.getDocId()));
    			//docRemarks.add(updBean.getDocDesc());
    			//docRemarks.add(updBean.getDocDesc());
    		}
    	}
    	/*bean.setDocCodes(docIds);
    	bean.setDocRemarks(docRemarks);*/
    	return list;
    }
}
