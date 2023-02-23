package com.maan.adminnew.lcMaster;

import java.util.List;

public class LCMasterService {
	LCMasterDAO dao=new LCMasterDAO();
	
	public List<Object>getLcList(String branchCode){
		return dao.getLcList(branchCode);
	}
	public List<Object>getLcDetail(String broker, String branchCode){
		return dao.getLcDetail(broker, branchCode);
	}
	public List<Object>getOcDetail(String broker, String branchCode){
		return dao.getOcDetail(broker, branchCode);
	}
	public List<Object>getLCOCDetail(String branchCode, final LCMasterBean bean, String from){
		return dao.getLCOCDetail(branchCode, bean, from);
	}
	public List<Object> getSaleList(String branchCode){
		return dao.getSaleList(branchCode);
	}
	public List<Object> getCurrencyList(String branchCode){
		return dao.getCurrencyList(branchCode);
	}
	public List<Object> getBankList(String branchCode){
		return dao.getBankList(branchCode);
	}
	public int getLCSave(final LCMasterBean bean, String branchCode){
		return dao.getLCSave(bean, branchCode);
	}
	public int getLCUpdate(final LCMasterBean bean, String branchCode){
		return dao.getLCUpdate(bean, branchCode);
	}
	public void getlcInfo(final LCMasterBean bean, String branchCode){
		 dao.getlcInfo(bean, branchCode);
	}
	public void lcDelete(final LCMasterBean bean){
		 dao.lcDelete(bean);
	}
	public List<Object> getLcAmtDetails(LCMasterBean bean) {
			return dao.getLcAmtDetails(bean);
	}
	public String getLcAmt(LCMasterBean bean) {
		 return dao.getLcAmt(bean); 
		
	}
}
