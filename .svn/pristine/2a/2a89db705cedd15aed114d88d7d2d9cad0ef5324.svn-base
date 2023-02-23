package com.maan.adminnew.paymentProcess;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import com.maan.Home.Controller.HomeBean;
import com.maan.Home.Service.HomeService;
import com.maan.Motor.DAO.MotorDAO;
import com.maan.Motor.controller.MotorBean;
import com.maan.payment.PaymentService;

public class PaymentProcessService {
	PaymentProcessDAO dao=new PaymentProcessDAOImpl();

	public List<Map<String, Object>> getTransactionList(PaymentProcessBean bean) {
		return dao.getTransactionList(bean);
	}

	/*public List<Map<String, Object>> getViewCC(PaymentProcessBean bean) {
		return dao.getViewCC(bean);
	}*/
	public List<Map<String, Object>> getPaymentDetails(PaymentProcessBean bean) {
		return dao.getPaymentDetails(bean);
	}
	public String getPremiumInfo(PaymentProcessBean bean){
		return dao.getPremiumInfo(bean);
	}
	public List<Map<String, Object>> getvehicleDetails(PaymentProcessBean bean) {
		// TODO Auto-generated method stub
		return dao.getvehicleDetails(bean);
	}

	public LinkedList<String> getValidate(PaymentProcessBean bean) {
		LinkedList<String> list = new PaymentService().validatePayment(bean.getModeOfPayment(),bean.getChequeNo(), bean.getChequeDate(), bean.getChequeAmount(), bean.getTotPremium(), bean.getBankName(),bean.getMicrCode(),bean.getCashDepositBank(),bean.getCashAmount(),bean.getCashChellanNo(),bean.getCashInstrumentDate(),bean.getInstallmentYN(),bean.getInsIntialAmount(),bean.getMtnMobileNo());
		return list;
	}

	public List<Object> getBankNameList(PaymentProcessBean bean) {
		return dao.getBankNameList(bean);
	}

	public List<Object> getModeOfPaymentList(PaymentProcessBean bean) {
		return dao.getModeOfPaymentPaymentList(bean);
	}

	public List<Map<String, Object>> travellerDetailsList(PaymentProcessBean bean) {
		return dao.travellerDetailsList(bean);
	}

	public List<Map<String,Object>> homeInfo(PaymentProcessBean bean) {
		HomeBean homeBean = new HomeBean();
		List<Map<String,Object>> res = dao.getHomeInfo(bean);
		homeBean.setContentTypeId(res.get(0).get("CONTENT_TYPE_ID")==null?"0":res.get(0).get("CONTENT_TYPE_ID").toString());
		homeBean.setQuoteNo(bean.getQuoteNo());
		homeBean.setProductId(bean.getProductId());
		homeBean.setSchemeId(res.get(0).get("SCHEME_ID")==null?"0":res.get(0).get("SCHEME_ID").toString());
		return new HomeService().getHomeInfo(homeBean);
	}
	public List<Map<String,Object>> subhomeInfo(PaymentProcessBean bean) {
		HomeBean homeBean = new HomeBean();
		List<Map<String,Object>> res = dao.getHomeInfo(bean);
		homeBean.setContentTypeId(res.get(0).get("CONTENT_TYPE_ID")==null?"0":res.get(0).get("CONTENT_TYPE_ID").toString());
		homeBean.setQuoteNo(bean.getQuoteNo());
		homeBean.setProductId(bean.getProductId());
		homeBean.setSchemeId(res.get(0).get("SCHEME_ID")==null?"0":res.get(0).get("SCHEME_ID").toString());
		return new HomeService().getSubHomeInfo(homeBean);
	}

	public String insPaymentProcessTrac(String policyNo,String quoteNo,String reqfrom,String status,String remarks,String loginId,String applicableLoginId,String productId,String branchCode) {
		return dao.insPaymentProcessTrac(policyNo,quoteNo,reqfrom,status,remarks,loginId,applicableLoginId,productId,branchCode);	
	}

	public List<Map<String, Object>> getTransactionTrackingList(PaymentProcessBean bean) {
		return dao.getTransactionTrackingList(bean);
	}

	public List<Map<String, Object>> getApproverLoginList(String reqForm, String produtId) {
		return dao.getApproverLoginList(reqForm,produtId);
	}

	public void setPaymentProcessDetails(PaymentProcessBean bean) {
		dao.setPaymentProcessDetails(bean);
	}

	public List<Map<String, Object>> getCustAttachedDocs(String productId,String quoteNo, String reqFrom) {
		return dao.getCustAttachedDocs(productId,quoteNo,reqFrom);
		
	}
	
	/*public void updateInstallmentDetail(String quoteNo,String Type) {
		dao.updateInstallmentDetail(quoteNo,Type);
		
	}

	public String getInstallmentStatus(String quoteNo) {
		return dao.getInstallmentStatus(quoteNo);
	}*/
	
	public List<Map<String, Object>> getQuoteDetails(PaymentProcessBean bean) {
		return dao.getQuoteDetails(bean);
	}

	public int updatePaymentdetail(PaymentProcessBean bean) {
		return dao.updatePaymentdetail(bean);
	}

}
