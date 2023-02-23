package com.maan.adminnew.underwriterManagement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.maan.adminnew.common.CommonService;
import com.maan.adminnew.userManagement.UserMgtBean;

public class UnderwriterMgtService{
	
	UnderwriterMgtDAO dao=new UnderwriterMgtDAO();
	
	public List<Object> getAdminUnderwriterList(UnderwriterMgtBean ba,String agencyCode, String mode1, String branchCode) {
		return dao.getAdminUnderwriterList(ba,agencyCode, mode1, branchCode);
	}
	
	public List <Object> getUnderwriterDetails(final UnderwriterMgtBean ba, String branchCode, String issurName){
    	return dao.getUnderwriterDetails(ba, branchCode, issurName);
    }
	
	/*public void updatebrokerDetails(final UnderwriterMgtBean ba, String issurName){
		dao.updatebrokerDetails(ba, issurName);
    }*/
	
	public List <Map<String, Object>> includeissuerDetails(final UnderwriterMgtBean ba, String type1, String belongBranch){
		List<Map<String, Object>> returnList = new ArrayList<Map<String, Object>>();
		try {
			List<Map<String, Object>> list = dao.includeissuerDetails(ba, type1);
			CommonService cservice = new CommonService();
			List<Map<String, String>> prodList = cservice.getProductsDET(belongBranch, "");
			if(list!=null && list.size()>0 && prodList!=null && prodList.size()>0) {
				for(Map<String, Object> map : list) {
						if(map!=null && map.size()>0) {
							String availProds = map.get("PRODUCT_IDS")==null?"":map.get("PRODUCT_IDS").toString();
							if(StringUtils.isNotBlank(availProds)) {
								String[] prodArr = availProds.split(",");
								if(prodArr != null && prodArr.length>0) {
									List<Map<String, String>> tempProdList = new ArrayList<Map<String, String>>();
									for(Map<String, String> map1: prodList) {
										String tempProdId = map1.get("PRODUCT_ID")==null?"":map1.get("PRODUCT_ID").toString();
										if(Arrays.asList(prodArr).contains(tempProdId)) {
											tempProdList.add(map1);
										}
									}
									if(tempProdList!=null && tempProdList.size()>0) {
										map.put("PRODUCT_LIST", tempProdList);
										returnList.add(map);
									}
								}
							}
						}
				}	
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
    	return returnList;
    }
	
	public List <Map<String, Object>> excludeissuerDetails(final UnderwriterMgtBean ba, String type1, String belongBranch){
		List<Map<String, Object>> returnList = new ArrayList<Map<String, Object>>();
		try {
			List<Map<String, Object>> list = dao.excludeissuerDetails(ba, type1);
			CommonService cservice = new CommonService();
			List<Map<String, String>> prodList = cservice.getProductsDET(belongBranch, "");
			if(list!=null && list.size()>0 && prodList!=null && prodList.size()>0) {
				for(Map<String, Object> map : list) {
						if(map!=null && map.size()>0) {
							String availProds = map.get("PRODUCT_IDS")==null?"":map.get("PRODUCT_IDS").toString();
							if(StringUtils.isNotBlank(availProds)) {
								String[] prodArr = availProds.split(",");
								if(prodArr != null && prodArr.length>0) {
									List<Map<String, String>> tempProdList = new ArrayList<Map<String, String>>();
									for(Map<String, String> map1: prodList) {
										String tempProdId = map1.get("PRODUCT_ID")==null?"":map1.get("PRODUCT_ID").toString();
										if(Arrays.asList(prodArr).contains(tempProdId)) {
											tempProdList.add(map1);
										}
									}
									if(tempProdList!=null && tempProdList.size()>0) {
										map.put("PRODUCT_LIST", tempProdList);
										returnList.add(map);
									}
								}
							}
						}
				}	
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
    	return returnList;
    }

	public void getRSABranches(UnderwriterMgtBean bean) {
		dao.getRSABranches(bean);		
	}

	public int insertUnderwriter(UnderwriterMgtBean bean) {
		return dao.insertUnderwriter(bean);		
	}

	public void updateExcludedBrokers(UnderwriterMgtBean bean) {
		dao.updateExcludedBrokers(bean);
		
	}

	public void updateIncludeBrokers(UnderwriterMgtBean bean) {
		dao.updateIncludeBrokers(bean);
		
	}
	public int updateUnderwriter(UnderwriterMgtBean bean){
		return dao.updateUnderwriter(bean);
	}
	public void changePassword(UnderwriterMgtBean bean){
		dao.changePassword(bean);
	}
	
	public boolean isNotBlank(Object [] args) {
		try {
			if(args!=null && args.length>0) {
				for(Object arg: args) {
					if(StringUtils.isNotBlank(arg.toString())) {
						return true;
					}
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean isNotBlank(List<Boolean> args) {
		try {
			if(args!=null && args.size()>0) {
				for(Boolean arg: args) {
					if(arg) {
						return true;
					}
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}