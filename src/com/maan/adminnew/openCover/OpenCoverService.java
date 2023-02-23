package com.maan.adminnew.openCover;

import java.util.List;

public class OpenCoverService
{	
	OpenCoverDAO dao=new OpenCoverDAO();
	
	public List <Object> getregQuote(String broker){
    	return dao.getregQuote(broker);
    }
	public List <Object> getPortfolio(String brokerLoginId){
    	return dao.getPortfolio(brokerLoginId);
    }
	public List <Object> getCustomerList(String broker, String appId){
    	return dao.getCustomerList(broker, appId);
    }
	public List<Object> getLcList(String branchCode, String policynumber) {
		return dao.getLcList(branchCode, policynumber);
	}
}