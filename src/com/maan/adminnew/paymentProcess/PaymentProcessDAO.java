package com.maan.adminnew.paymentProcess;

import java.util.List;
import java.util.Map;

interface PaymentProcessDAO {

	public List<Map<String, Object>> getTransactionList(PaymentProcessBean bean);

	/*public List<Map<String, Object>> getViewCC(PaymentProcessBean bean);*/
	
	public List<Map<String, Object>> getPaymentDetails(PaymentProcessBean bean);

	public String insPaymentProcessTrac(String policyNo,String quoteNo,String reqfrom,String status,String remarks,String loginId,String applicableLoginId,String productId,String branchCode);

	public List<Object> getBankNameList(PaymentProcessBean bean);

	public List<Object> getModeOfPaymentPaymentList(PaymentProcessBean bean);

	public List<Map<String, Object>> travellerDetailsList(PaymentProcessBean bean);

	public List<Map<String, Object>> getHomeInfo(PaymentProcessBean bean);

	public List<Map<String, Object>> getTransactionTrackingList(PaymentProcessBean bean);

	public List<Map<String, Object>> getvehicleDetails(PaymentProcessBean bean);

	public List<Map<String, Object>> getApproverLoginList(String reqForm,String productId);

	public void setPaymentProcessDetails(PaymentProcessBean bean);

	public List<Map<String, Object>> getCustAttachedDocs(String productId,String quoteNo, String reqFrom);

	public String getPremiumInfo(PaymentProcessBean bean);

	/*public void updateInstallmentDetail(String quoteNo, String type);

	public String getInstallmentStatus(String quoteNo);*/
	
	public List<Map<String, Object>> getQuoteDetails(PaymentProcessBean bean);

	public int updatePaymentdetail(PaymentProcessBean bean);

}
