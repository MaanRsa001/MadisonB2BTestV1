package com.maan.adminnew.status;

import java.util.List;
import java.util.Map;

import com.maan.adminnew.paymentProcess.PaymentProcessBean;

public class MotorPaymentService {

	MotorPaymentDao dao= new MotorPaymentDao();

	public List<Map<String,Object>> getMotorPayment(MotorPaymentBean bean) {
		return dao.getMotorPayment(bean);
	}	
	public List<Map<String,Object>> getCreditPayment(MotorPaymentBean bean) {
		return dao.getCreditPayment(bean);
	}
	public List<Map<String,Object>> getMotorProduct(MotorPaymentBean bean) {
		return dao.getMotorProduct(bean);
	}
	
	public List<Map<String,Object>> getMotorPaymentType(MotorPaymentBean bean) {
		return dao.getMotorPaymentType(bean);
	}

	public void viewPayment(MotorPaymentBean bean) {
		dao.viewPayment(bean);
	}
	public String updateCCP(MotorPaymentBean bean) {
		return dao.updateCCP(bean);
	}
	public List<Map<String,Object>> getResCodeDescription(MotorPaymentBean bean) {
		return dao.getResCodeDescription(bean);
	}
	public List<Map<String, Object>> getResCodeDecision(MotorPaymentBean bean) {
		return dao.getResCodeDecision(bean);
	}
	
	public List<Map<String,Object>> getInstallmentReport(MotorPaymentBean bean) {
		return dao.getInstallmentReport(bean);
	}	
	public List<Map<String,Object>> getNoInstallment(String quoteNo) {
		return dao.getNoInstallment(quoteNo);
	}
	public void viewInstallmentPayment(MotorPaymentBean bean) {
		dao.viewInstallmentPayment(bean);
	}
	public void viewInstallment(MotorPaymentBean bean) {
		dao.viewInstallment(bean);
	}
	public List<Map<String,Object>> getDueCount(MotorPaymentBean bean) {
		return dao.getDueCount(bean);
	}
	public List<Map<String,Object>> getPaymentDue(MotorPaymentBean bean) {
		return dao.getPaymentDue(bean);
	}
	public List<Object> getBankNameList(MotorPaymentBean bean) {
		return dao.getBankNameList(bean);
	}
	public String insPaymentInsert(MotorPaymentBean bean) {
		return dao.insPaymentInsert(bean);
	}
	public void updateInsPay(MotorPaymentBean bean) {
		dao.updateInsPay(bean);
		
	}
}