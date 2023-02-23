package com.maan.adminnew.referal;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.maan.adminnew.common.CommonService;
import com.maan.common.LogManager;
import com.maan.common.Validation;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class ReferalAction extends ActionSupport implements ModelDriven<ReferalBean>
{
	private static final long serialVersionUID = 1L;
	private ReferalBean bean = new ReferalBean();
	private CommonService cservice=new CommonService();
	Validation validation=new Validation();
	ReferalService service=new ReferalService();
	Map<String, Object> session=ActionContext.getContext().getSession();
	String branchCode=(String)session.get("BranchCode");
	String login_id=(String)session.get("user");
	String attched_Branch=(String)session.get("Attached_Branch");
	String accesstype=(String)session.get("accesstype");
	String productId=(String)session.get("product_id");
	String usertype=(String)session.get("usertype");
	private List <Object> occList;
	private List <Map<String,String>> productDet;
	private List <Object> occListP;
	private List <Object> occListA;
	private List <Object> occListR;
	private List <Object> policyList;
	private List <Map<String,String>> branchDet;
	private String region;
	public String getRegion() {
		return StringUtils.isBlank(region)?branchCode:region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public List<Object> getPolicyList() {
		return policyList;
	}
	public void setPolicyList(List<Object> policyList) {
		this.policyList = policyList;
	}
	public List<Object> getOccListP() {
		return occListP;
	}
	public void setOccListP(List<Object> occListP) {
		this.occListP = occListP;
	}
	public List<Object> getOccListA() {
		return occListA;
	}
	public void setOccListA(List<Object> occListA) {
		this.occListA = occListA;
	}
	public List<Object> getOccListR() {
		return occListR;
	}
	public void setOccListR(List<Object> occListR) {
		this.occListR = occListR;
	}
	public List<Object> getOccList() {
		return occList;
	}
	public void setOccList(List<Object> occList) {
		this.occList = occList;
	}
	public List<Map<String,String>> getProductDet() {
		return productDet;
	}
	public void setProductDet(List<Map<String,String>> productDet) {
		this.productDet = productDet;
	}
	public ReferalBean getModel() {
		return bean;
	}
	public List<Object> getRegionList(){
		return service.getRegionList(login_id,accesstype);
	}
	public String getOCList() {
		LogManager.info("ENTER==>getOCList()");
		productDet=cservice.getReferralProducts(branchCode, productId);
		branchDet=cservice.getBranchDetails(login_id);
		
		/*if(bean.getProductID()==null){
			bean.setProductID("3");
		}
		occListP=service.getOCList("pending",bean.getProductID(), branchCode);
		occListA=service.getOCList("approved",bean.getProductID(), branchCode);
		occListR=service.getOCList("rejected",bean.getProductID(), branchCode);*/
		LogManager.info("EXIST==>getOCList()");
		return "referalList";
	}
	
	public String getOCAjax() {
		String returnResult="referalList";
		if("RSAIssuer".equals(usertype)) {
			session.put("Referal", "Y");
			returnResult="issuerreferalList";
		}
		productDet=cservice.getReferralProducts(branchCode, productId);
		branchDet=cservice.getBranchDetails(login_id);
		if(StringUtils.isNotBlank(bean.getReqFrom()))
			occList=service.getOCList(bean.getReqFrom(),bean.getProductID(), bean.getBranchId(), bean.getAgencyCode(),login_id,attched_Branch);
		if(bean.getRdate()!=null && !"".equals(bean.getRdate())){
			if("".equals(bean.getFrom1()) || "ajax".equalsIgnoreCase(bean.getFrom1()))
				returnResult="adminAjax";
			policyList=service.getPolicyList(bean.getBranchId(), bean.getRdate(), bean.getProductID(), bean.getReqFrom(), bean.getAgencyCode(),login_id,attched_Branch);
		}else if("".equals(bean.getFrom1()) || "ajax".equalsIgnoreCase(bean.getFrom1())){
			returnResult="adminAjax";
    	}else{
    		if("pending".equals(bean.getReqFrom())){
    			occListP=occList;
    			occListA=service.getOCList("approved",bean.getProductID(), bean.getBranchId(), bean.getAgencyCode(),login_id,attched_Branch);
    			occListR=service.getOCList("rejected",bean.getProductID(), bean.getBranchId(), bean.getAgencyCode(),login_id,attched_Branch);
    		}else if("approved".equals(bean.getReqFrom())){
    			occListA=occList;
    			occListP=service.getOCList("pending",bean.getProductID(), bean.getBranchId(), bean.getAgencyCode(),login_id,attched_Branch);
    			occListR=service.getOCList("rejected",bean.getProductID(), bean.getBranchId(), bean.getAgencyCode(),login_id,attched_Branch);
    		}else if("rejected".equals(bean.getReqFrom())){
    			occListR=occList;
    			occListP=service.getOCList("pending",bean.getProductID(), bean.getBranchId(), bean.getAgencyCode(),login_id,attched_Branch);
    			occListA=service.getOCList("approved",bean.getProductID(), bean.getBranchId(), bean.getAgencyCode(),login_id,attched_Branch);
    		}
    	}
		return returnResult;
	}
	public void setBranchDet(List <Map<String,String>> branchDet) {
		this.branchDet = branchDet;
	}
	public List <Map<String,String>> getBranchDet() {
		return branchDet;
	}
}