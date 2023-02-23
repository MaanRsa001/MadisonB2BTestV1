package com.maan.quickRenewal.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.maan.quickRenewal.bean.quickRenewalBean;
import com.maan.quickRenewal.dao.quickRenewalDAO;

public class quickRenewalService {
	quickRenewalBean bean= new quickRenewalBean();
	quickRenewalDAO dao=new quickRenewalDAO();

	public List<Map<String, Object>> getPolicyDetails(quickRenewalBean bean) {
		return dao.getPolicyDetails(bean);
	}
	
	public List<Map<String, Object>> getRenewList(quickRenewalBean bean) {
		return dao.getRenewList(bean);
	}

	public int getValidateOtpCount(quickRenewalBean bean) {
		return dao.getValidateOtp(bean.getOtp(),bean.getOtpId(),bean.getMailOtp());
	}

	public int getValidateOtpExpiry(quickRenewalBean bean) {
		return dao.getValidateOtpExpiry(bean.getOtp(),bean.getOtpId(),bean.getMailOtp());
		
	}

	public String getDate() {
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
		String dateString = format.format( new Date());
		return dateString;
	}

	public void getPolicyPayment(quickRenewalBean bean) {
		dao.getPolicyPayment(bean);
	}

	public int getPaymentInsert(quickRenewalBean bean) {
		return dao.getPaymentInsert(bean);
	}

	public List<Map<String, Object>>  getAdminRenewalList(quickRenewalBean bean) {
		return dao.getAdminRenewal(bean);
	}

	public List<Map<String, Object>> getAdminRenewal(quickRenewalBean bean) {
		return dao.getAdminRenewal(bean);
	}

//	public String getVehicleList(quickRenewalBean bean) {
//		String selectVeh="";
//		for(int i=0;i<bean.getVehicleSelect().size();i++){
//			if("true".equalsIgnoreCase(bean.getVehicleSelect().get(i))){
//				selectVeh += String.valueOf(i+1)+"," ;
//			}
//		}
//		return selectVeh;
//	}

	public void getTotalPrem(quickRenewalBean bean) {
		dao.getTotalPrem(bean);
	}

	public List<Map<String, Object>> getpaidDetails(quickRenewalBean bean) {
		return dao.getPaidDetails(bean);
	}

	public List<Map<String, Object>> getPolicyInfo(String mblNo) {
		return dao.getPolicyInfo(mblNo);
	}

	public void checkbox(quickRenewalBean bean) {
		List<String> veh=bean.getVehicleSelect();
		String vehicle="";
		if(veh!=null){
			for(String test : veh){
				vehicle+=test+"-";
			}
			bean.setCheckboxValue(StringUtils.chop(vehicle));
		}
	}

	public void getCheckBoxValue(quickRenewalBean bean) {
		String check[]= bean.getCheckboxValue().split("-");
		List<String> chk = new ArrayList<String>();
		for(String ch: check){
			chk.add(ch);
		}
		bean.setVehicleSelect(chk);
	}

	public void deletePrevRecord(quickRenewalBean bean) {
		int count =dao.deletePrevRecord(bean);
	}

	public void checkForQuote(quickRenewalBean bean) {
		//check for last quote no success count
		int i= dao.checkSuccessQuote(bean);
		if(i>0){
			dao.getQuoteNo(bean);
		}else{
			//check for last failure quote and get that quote
			String quote = dao.getLastQuote(bean);
			if(StringUtils.isNotBlank(quote)){
				bean.setRenewRefNo(quote);
				int count =dao.deletePrevRecord(bean);
				if(count==0){
					dao.getQuoteNo(bean);
				}
			}else{
				dao.getQuoteNo(bean);
			}
		}
	}

	public List<Map<String, Object>> getPaymentList(quickRenewalBean bean) {
		return dao.getPaymentList(bean);
	}

	public int getPaymentInsertCashCheque(quickRenewalBean bean) {
		return dao.getPaymentInsertCashCheque(bean);
	}

	public List<Map<String, Object>> getCashPaymentList(quickRenewalBean bean) {
		return dao.getCashPaymentList(bean);
	}

	public void getCashListDetails(quickRenewalBean bean) {
		dao.getCashListDetails(bean);
	}

	public int updateCashStatus(quickRenewalBean bean) {
		return dao.updateCashStatus(bean);
	}

	public List<Map<String, Object>> getVehicleList(quickRenewalBean bean) {
		return dao.getVehicleList(bean);
	}
	public List<Map<String, Object>> getApproverLoginList(String reqForm) {
		return dao.getApproverLoginList(reqForm);
	}

	public void settingDetail(String polNo, String vehRegNo, quickRenewalBean bean) {
		dao.settingDetail(polNo,vehRegNo,bean);
	}

	public int getPolicyExist(String polNo) {
		return dao.getPolicyExist(polNo);
	}

}
