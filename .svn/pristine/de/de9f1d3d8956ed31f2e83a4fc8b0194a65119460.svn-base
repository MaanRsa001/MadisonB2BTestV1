package com.maan.quickRenewal.dao;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.maan.common.LogManager;
import com.maan.common.MyJdbcTemplate;
import com.maan.payment.mtn.MtnService;
import com.maan.quickRenewal.bean.quickRenewalBean;
public class quickRenewalDAO extends MyJdbcTemplate {
	
	public List<Map<String, Object>> getPolicyDetails(quickRenewalBean bean) {
		String query="";
		Object args[]=null;
		List<Map<String, Object>> result = null;
		try{
			query= getQuery("GET_VEHICLE_COUNT");
			if(StringUtils.isNotBlank(bean.getPolicyNo())&&StringUtils.isNotBlank(bean.getVehPlateNo())){
				query+="AND POL_NO=? AND REG_NO=?";
				args = new Object[]{bean.getMobileNo(),bean.getPolicyNo(),bean.getVehPlateNo()};
			}
			else if(StringUtils.isNotBlank(bean.getVehPlateNo())){
				query+="AND REG_NO=? ORDER BY RISK_ID";
				args = new Object[]{bean.getMobileNo(),bean.getVehPlateNo()};
			}
			else{
				query+="AND POL_NO=? ORDER BY RISK_ID";
				args= new Object[]{bean.getMobileNo(),bean.getPolicyNo()};
			}
			LogManager.info("Query==>" + query);
			LogManager.info("Args==>" + StringUtils.join(args,", "));
			result=this.mytemplate.queryForList(query, args);
			if(result.size()>0){
				Map map = (Map) result.get(0);
				bean.setMobileNo(map.get("MOBILE_NO") == null ? "" : map.get("MOBILE_NO").toString());
				bean.setCustBranch(map.get("DIVN_NAME") == null ? "" : map.get("DIVN_NAME").toString());
				bean.setCustInsuredName(map.get("ASSR_NAME") == null ? "" : map.get("ASSR_NAME").toString());
				bean.setCustProduct(map.get("PROD_NAME") == null ? "" : map.get("PROD_NAME").toString());
				//bean.setEmail(map.get("EMAIL_ID") == null ? "" : map.get("EMAIL_ID").toString());
				//bean.setPassportNo(map.get("NRC_PASSPORT_NO") == null ? "" : map.get("NRC_PASSPORT_NO").toString());
				bean.setPolicyNo(map.get("POL_NO") == null ? "" : map.get("POL_NO").toString());
				bean.setCurrency(map.get("PREM_CURR_CODE") == null ? "" : map.get("PREM_CURR_CODE").toString());
				bean.setPrevInceptionDate(map.get("PREV_FROM_DT") == null ? "" : map.get("PREV_FROM_DT").toString());
				bean.setPrevExpiryDate(map.get("PREV_TO_DT") == null ? "" : map.get("PREV_TO_DT").toString());
				bean.setPeriodOfInsurance(map.get("POL_PERIOD") == null ? "" : map.get("POL_PERIOD").toString());
				bean.setRenewIncepDate(map.get("CUR_FROM_DT") == null ? "" : map.get("CUR_FROM_DT").toString());
				bean.setRenewExpiryDate(map.get("CUR_TO_DT") == null ? "" : map.get("CUR_TO_DT").toString());
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	public List<Map<String, Object>> getRenewList(quickRenewalBean bean){
		LogManager.info("Enter into getRenewList()");
		String query="";
		Object args[]=null;
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> EmailPasport = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> result1=new ArrayList<Map<String, Object>>();
		try{
			if("qrRead".equalsIgnoreCase(bean.getQrMode()) || "qrUpload".equalsIgnoreCase(bean.getQrMode())){
				query= getQuery("GET_RENEW_COUNT_QR");
				args = new Object[]{bean.getPolicyNo(),bean.getPolicyNo()};
				LogManager.info("Query==>" + query);
				LogManager.info("Args==>" + StringUtils.join(args,", "));
				result=this.mytemplate.queryForList(query, args);
				
				String sql=getQuery("GET_RENEW_CUST_DTL_QR");
				args = new Object[]{bean.getPolicyNo()};
				LogManager.info("Query==>" + sql);
				LogManager.info("Args==>" + StringUtils.join(args,", "));
				result1=this.mytemplate.queryForList(sql, args);
			}
			else{
				query= getQuery("GET_RENEW_COUNT");
				query+=" AND POL_NO=? ORDER BY RISK_ID";
				args = new Object[]{bean.getPolicyNo(),bean.getMobileNo(),bean.getPolicyNo()};
				LogManager.info("Query==>" + query);
				LogManager.info("Args==>" + StringUtils.join(args,", "));
				result=this.mytemplate.queryForList(query, args);
				
				String sql=getQuery("GET_RENEW_CUST_DTL");
				args = new Object[]{bean.getMobileNo(),bean.getPolicyNo()};
				LogManager.info("Query==>" + sql);
				LogManager.info("Args==>" + StringUtils.join(args,", "));
				result1=this.mytemplate.queryForList(sql, args);
			}
			if(result1.size()>0){
				Map map = (Map) result1.get(0);
				bean.setMobileNo(map.get("MOBILE_NO") == null ? "" : map.get("MOBILE_NO").toString());
				bean.setCustBranch(map.get("DIVN_NAME") == null ? "" : map.get("DIVN_NAME").toString());
				bean.setCustInsuredName(map.get("ASSR_NAME") == null ? "" : map.get("ASSR_NAME").toString());
				bean.setCustProduct(map.get("PROD_NAME") == null ? "" : map.get("PROD_NAME").toString());
				bean.setPolicyNo(map.get("POL_NO") == null ? "" : map.get("POL_NO").toString());
				bean.setCurrency(map.get("PREM_CURR_CODE") == null ? "" : map.get("PREM_CURR_CODE").toString());
				bean.setPrevInceptionDate(map.get("PREV_FROM_DT") == null ? "" : map.get("PREV_FROM_DT").toString());
				bean.setPrevExpiryDate(map.get("PREV_TO_DT") == null ? "" : map.get("PREV_TO_DT").toString());
				bean.setPeriodOfInsurance(map.get("POL_PERIOD") == null ? "" : map.get("POL_PERIOD").toString());
				bean.setRenewIncepDate(map.get("CUR_FROM_DT") == null ? "" : map.get("CUR_FROM_DT").toString());
				bean.setRenewExpiryDate(map.get("CUR_TO_DT") == null ? "" : map.get("CUR_TO_DT").toString());
			}
			if("qrRead".equalsIgnoreCase(bean.getQrMode()) || "qrUpload".equalsIgnoreCase(bean.getQrMode())){
				query=getQuery("GET_EMAIL_PASSPORT_QR");
				LogManager.info("Query==>" + query);
				EmailPasport =this.mytemplate.queryForList(query,args);
			}
			else{
				query=getQuery("GET_EMAIL_PASSPORT");
				LogManager.info("Query==>" + query);
				EmailPasport =this.mytemplate.queryForList(query,args);
			}
			if(EmailPasport.size()>0){
				Map map = (Map) EmailPasport.get(0);
				bean.setEmail(map.get("EMAIL_ID") == null ? "" : map.get("EMAIL_ID").toString());
				bean.setPassportNo(map.get("NEW_NRC_PASSPORT_NO") == null ? "" : map.get("NEW_NRC_PASSPORT_NO").toString());
			}
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
			LogManager.info("Exit from getRenewList()");
		return result;
	}

	public int getValidateOtp(String otp, String otpId, String mailOtp) {
		int count = 0;
		String query="";
		try{
			query = getQuery("CHECK_OTP_COUNT");
			LogManager.info("Query==>" + query);
			LogManager.info("Args==>" + StringUtils.join(new Object[]{otp,otpId},", "));
			count = this.mytemplate.queryForInt(query,new Object[]{otp,otpId});
		}catch(Exception e){
			e.printStackTrace();
		}
		return count;
	}

	public int getValidateOtpExpiry(String otp, String otpId, String mailOtp) {
		int expiry=0;
		String query="";
		try{
			query = getQuery("CHECK_OTP_EXPIRY");
			LogManager.info("Query==>" + query);
			LogManager.info("Args==>" + StringUtils.join(new Object[]{otp,otpId},", "));
			expiry = this.mytemplate.queryForInt(query,new Object[]{otpId,otp});
		}catch(Exception e){
			e.printStackTrace();
		}
		return expiry;
	}
	
	public void getTotalPrem(quickRenewalBean bean) {
		List<Map<String, Object>> result = null;
		String[] vehdetails = bean.getVehicles().split(",");
		String query="";
		float prem=0;
		Object args[]=null;
		try{
			query=getQuery("GET_RENEWAL_DETAILS");
			for(int i=0;i<vehdetails.length;i++){
				args = new Object[]{bean.getPolicyNo(),vehdetails[i]};
				LogManager.info("Query==>" + query);
				LogManager.info("Args==>" + StringUtils.join(args,", "));
				result=this.mytemplate.queryForList(query, args);
				if(result.size()>0){
					Map map = (Map) result.get(0);
					bean.setTempCustPrem(map.get("CUST_PREM") == null ? "" : map.get("CUST_PREM").toString());
					prem+= Float.parseFloat(bean.getTempCustPrem());
				}
			}
			DecimalFormat df = new DecimalFormat("0.00");
			
			//String premAmounnt =Float.toString(Math.round(prem));
			bean.setMyTotal(df.format(prem));
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

	public void getPolicyPayment(quickRenewalBean bean) {
		List<Map<String, Object>> result = null;
		String[] vehdetails = bean.getVehicles().split(",");
		String query="";
		Object args[]=null;
		query=getQuery("GET_RENEWAL_DETAILS");
		float prem=0;
		String refNo="";
		double premium=0.0;
		double tax=0.0;
		double totalPrem=0.0;
		double tempPrem=0.0;
		double tempTax=0.0;
		try{
			bean.setCurrency("ZMW");
			for(int i=0;i<vehdetails.length;i++){
				args = new Object[]{bean.getPolicyNo(),vehdetails[i]};
				LogManager.info("Query==>" + query);
				LogManager.info("Args==>" + StringUtils.join(args,", "));
				result=this.mytemplate.queryForList(query, args);
				if(result.size()>0){
					Map map = (Map) result.get(0);
					//bean.setTempCustPrem(map.get("CUST_PREM") == null ? "" : map.get("CUST_PREM").toString());
					bean.setTempVehiclePlateNo(map.get("REG_NO") == null ? "" : map.get("REG_NO").toString());
					bean.setTempCustSI(map.get("CUST_SI") == null ? "" : map.get("CUST_SI").toString());
					bean.setCustBranch(map.get("DIVN_NAME") == null ? "" : map.get("DIVN_NAME").toString());
					bean.setCustInsuredName(map.get("ASSR_NAME") == null ? "" : map.get("ASSR_NAME").toString());
					bean.setCustProduct(map.get("PROD_NAME") == null ? "" : map.get("PROD_NAME").toString());
					bean.setCurrency(map.get("PREM_CURR_CODE") == null ? "" : map.get("PREM_CURR_CODE").toString());
					bean.setPrevInceptionDate(map.get("PREV_FROM_DT") == null ? "" : map.get("PREV_FROM_DT").toString());
					bean.setPrevExpiryDate(map.get("PREV_TO_DT") == null ? "" : map.get("PREV_TO_DT").toString());
					bean.setPeriodOfInsurance(map.get("POL_PERIOD") == null ? "" : map.get("POL_PERIOD").toString());
					bean.setRenewIncepDate(map.get("CUR_FROM_DT") == null ? "" : map.get("CUR_FROM_DT").toString());
					bean.setRenewExpiryDate(map.get("CUR_TO_DT") == null ? "" : map.get("CUR_TO_DT").toString());
					bean.setTempRiskId(map.get("RISK_ID") == null ? "" : map.get("RISK_ID").toString());
					bean.setTempChassisNo(map.get("CHASSIS_NO") == null ? "" : map.get("CHASSIS_NO").toString());
					bean.setTempEngineNo(map.get("ENGINE_NO") == null ? "" : map.get("ENGINE_NO").toString());
					//bean.setTempRegNo(map.get("REG_NO") == null ? "" : map.get("REG_NO").toString());
					//prem+= Float.parseFloat(bean.getTempCustPrem());
					tempPrem=Double.parseDouble(map.get("CUST_PREM") == null ? "0.0" : map.get("CUST_PREM").toString());
					tempTax=Double.parseDouble(map.get("PREM_TAX") == null ? "0.0" : map.get("PREM_TAX").toString());
					bean.setTempCustPrem(String.valueOf(tempPrem+tempTax));
					premium+=Double.parseDouble(map.get("CUST_PREM") == null ? "0.0" : map.get("CUST_PREM").toString());
					tax+=Double.parseDouble(map.get("PREM_TAX") == null ? "0.0" : map.get("PREM_TAX").toString());
					result=null;
					int rencount,paycount=0;
					rencount=setRenewalDetails(bean);
					paycount=setRenewalPaymentDetails(bean);
					if(rencount==0)
					{
						break;
					}
				}
			}
			totalPrem=premium+tax;
			bean.setMyTotal(String.valueOf(premium));
			bean.setPremTax(String.valueOf(tax));
			bean.setPremPay(String.valueOf(totalPrem));
			updateRenewalAmount(bean);
			//String premAmounnt =Float.toString(prem);
		//	bean.setMyTotal(premAmounnt);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public int updateRenewalAmount(quickRenewalBean bean){
		LogManager.info("Enter into updateRenewalAmount()");
		int res=0;
		String query="";
		try{
			query=getQuery("UPDATE_RENEWAL_AMOUNT");
			Object args[]=new Object[]{bean.getPremPay(),bean.getRenewRefNo(),bean.getPolicyNo()};
			LogManager.info("Query==>" + query);
			LogManager.info("Args==>" + StringUtils.join(args,", "));
			res = this.mytemplate.update(query, args);
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return res;
	}

	private int setRenewalPaymentDetails(quickRenewalBean bean) {
		LogManager.info("Enter into setRenewalPaymentDetails()");
		String query="";
		int count=0;
		try{
			query=getQuery("INSERT_PAYMENT_DETAILS");
			Object args[]=new Object[]{bean.getRenewRefNo(),bean.getPolicyNo(),bean.getTempRiskId(),bean.getTempCustPrem()};
			LogManager.info("Query==>" + query);
			LogManager.info("Args==>" + StringUtils.join(args,", "));
			count = this.mytemplate.update(query, args);
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		LogManager.info("Exit from setRenewalPaymentDetails()");
		return count;
	}

	private int setRenewalDetails(quickRenewalBean bean) {
		LogManager.info("Enter into setRenewalDetails()");
		String query="";
		int count=0;
		try{
			query=getQuery("INSERT_RENEWAL_DETAILS");
			Object args[]=new Object[]{
					bean.getCustInsuredName(),bean.getTempCustPrem(),
					bean.getTempCustSI(),bean.getCustBranch(),
					bean.getMobileNo(),bean.getPolicyNo(),
					bean.getTempPassportNo(),bean.getMyTotal(),
					bean.getTempEmailId(),bean.getTempVehiclePlateNo(),
					bean.getRenewRefNo(),bean.getCurrency(),
					bean.getTempRiskId(),bean.getTempEngineNo(),
					bean.getTempChassisNo(),bean.getTempMobileNo()};
			LogManager.info("Query==>" + query);
			LogManager.info("Args==>" + StringUtils.join(args,", "));
			count = this.mytemplate.update(query, args);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		LogManager.info("Exit from setRenewalDetails()");
		return count;
	}

	public int getPaymentInsert(quickRenewalBean bean) {
		int result=0;
		LogManager.info("Enter into getPaymentInsert()");
		String query="";
		String refNo="";
		int count=0;
		Object args[]=null;
		try{
			String qry = getQuery("GET_RENEWAL_PAYMENT_TRANID_SEQ");
			refNo = (String) this.mytemplate.queryForObject(qry, String.class);
			refNo+="R";
			bean.setMerchant_reference(refNo);

			String premQry=getQuery("GET_PREMIUM_AMOUNT");
			Object arg[]=new Object[]{bean.getRenewRefNo()};
			String prem=(String) this.mytemplate.queryForObject(premQry, arg, String.class);

			query=getQuery("INSERT_RENEWALPAYMENT_DETAILS");
			
			if("101".equalsIgnoreCase(bean.getModeOfPayment())) {
				MtnService ms = new MtnService(bean.getRenewRefNo(), "", refNo, "65");
				String refNum = ms.getUuidNo();
				args=new Object[]{bean.getRenewRefNo(),"65",bean.getModeOfPayment(),prem,bean.getMerchant_reference(),bean.getTempEmailId(),bean.getCustInsuredName(),"01",bean.getCustInsuredName(),bean.getCustInsuredName(),bean.getTempEmailId(),bean.getMobileNo(),bean.getCurrency(),bean.getVehicles(),bean.getPolicyNo(),"Y",refNum,bean.getMtnMobileNo()};
			}
			else if("102".equalsIgnoreCase(bean.getModeOfPayment())){
				args=new Object[]{bean.getRenewRefNo(),"65",bean.getModeOfPayment(),prem,bean.getMerchant_reference(),bean.getTempEmailId(),bean.getCustInsuredName(),"01",bean.getCustInsuredName(),bean.getCustInsuredName(),bean.getTempEmailId(),bean.getMobileNo(),bean.getCurrency(),bean.getVehicles(),bean.getPolicyNo(),"Y","",bean.getAirtelMoneyNumber()};
			}
			else{
				args=new Object[]{bean.getRenewRefNo(),"65",bean.getModeOfPayment(),prem,bean.getMerchant_reference(),bean.getTempEmailId(),bean.getCustInsuredName(),"01",bean.getCustInsuredName(),bean.getCustInsuredName(),bean.getTempEmailId(),bean.getMobileNo(),bean.getCurrency(),bean.getVehicles(),bean.getPolicyNo(),"Y","",""};
			}
			LogManager.info("Query==>" + query);
			LogManager.info("Args==>" + StringUtils.join(args,", "));
			result = this.mytemplate.update(query, args);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		LogManager.info("Exit from getPaymentInsert()");
		return result;
	}

	public List<Map<String, Object>> getPaidDetails(quickRenewalBean bean) {
		LogManager.info("Enter into getPaidDetails()");
		String query="";
		Object args[]=null;
		List<Map<String, Object>> result = null;
		try{
			query=getQuery("GET_PAID_LIST");
			args=new Object[]{bean.getPolicyNo()};
			result=this.mytemplate.queryForList(query, args);
			LogManager.info("Query==>" + query);
			LogManager.info("Args==>" + StringUtils.join(args,", "));
		}
		catch(Exception e){
			e.printStackTrace();
		}
		LogManager.info("Exit from getPaidDetails()");
		return result;
	}

	public List<Map<String, Object>> getAdminRenewal(quickRenewalBean bean){
		String query="";
		Object args[]=null;
		List<Map<String, Object>> result = null;
		if("ACCEPT".equalsIgnoreCase(bean.getRenewalStatus()))
			query=getQuery("GET_ADMIN_RENEWAL_SUCCESS_LIST");
		else if("CANCEL".equalsIgnoreCase(bean.getRenewalStatus()))
			query=getQuery("GET_ADMIN_RENEWAL_FAILED_LIST");
		//args =new Object[]{bean.getRenewalStatus()};
		LogManager.info("Query==>" + query);
		//LogManager.info("Args==>" + StringUtils.join(args,", "));
		//result=this.mytemplate.queryForList(query, args);
		result=this.mytemplate.queryForList(query);
		return result;
	}

	public List<Map<String, Object>> getPolicyInfo(String mblNo) {
		String query="";
		List<Map<String, Object>> result = null;
		query=getQuery("GET_POLICY_INFO_LIST");
		String args[]={mblNo};
		LogManager.info("Query==>" + query);
		LogManager.info("Args==>" + StringUtils.join(args,", "));
		result=this.mytemplate.queryForList(query, args);
		return result;
	}

	public int deletePrevRecord(quickRenewalBean bean) {
		String query="";
		int count=0;
		try{
		query=getQuery("DELETE_MOTOR_PREV_RECORD");
		String args[]={bean.getPolicyNo(),bean.getRenewRefNo()};
		LogManager.info("Query==>" + query);
		LogManager.info("Args==>" + StringUtils.join(args,", "));
		this.mytemplate.update(query, args);
		
		query = getQuery("DELETE_PAYRISK_PREV_RECORD");
		LogManager.info("Query==>" + query);
		LogManager.info("Args==>" + StringUtils.join(args,", "));
		count = this.mytemplate.update(query, args);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		return count;
	}

	public int checkSuccessQuote(quickRenewalBean bean) {
		String query="";
		int check=0;
		try{
			String args[]=null;
			if("qrRead".equalsIgnoreCase(bean.getQrMode()) || "qrUpload".equalsIgnoreCase(bean.getQrMode())){
				query=getQuery("CHECK_LAST_SUCCESS_QUOTE_QR");
				args= new String[]{bean.getPolicyNo()};
			}
			else{
				query=getQuery("CHECK_LAST_SUCCESS_QUOTE");
				args= new String[]{bean.getPolicyNo(),bean.getMobileNo()};
			}
			LogManager.info("Query==>" + query);
			LogManager.info("Args==>" + StringUtils.join(args,", "));
			check=this.mytemplate.queryForInt(query, args);
			
		}catch(Exception e){
			System.out.println(check);
			//e.printStackTrace();
		}
		return check;
	}

	public void getQuoteNo(quickRenewalBean bean) {
		String refNo="";
		try{
			String qry = getQuery("GET_RENEWAL_REFERENCE_TRANID_SEQ");
			LogManager.info("Query==>" + qry);
			refNo = (String) this.mytemplate.queryForObject(qry, String.class);
			bean.setRenewRefNo(refNo);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

	public String getLastQuote(quickRenewalBean bean) {
		String query="";
		String quote="";
		try{
			String args[]=null;
			if("qrRead".equalsIgnoreCase(bean.getQrMode()) || "qrUpload".equalsIgnoreCase(bean.getQrMode())){
				query=getQuery("GET_LAST_QUOTE_QR");
				args=new String[]{bean.getPolicyNo()};
			}else{
				query=getQuery("GET_LAST_QUOTE");
				args=new String[]{bean.getPolicyNo(),bean.getMobileNo()};
			}
		
		LogManager.info("Query==>" + query);
		LogManager.info("Args==>" + StringUtils.join(args,", "));
		quote=(String) this.mytemplate.queryForObject(query, args, String.class);
		}
		catch(Exception e){
			//e.printStackTrace();
		}
		LogManager.info("Quote no==>" + quote);
		return quote;
	}

	public List<Map<String, Object>> getPaymentList(quickRenewalBean bean) {
		LogManager.info("Enter into getPaymentList()");
		String query="";
		Object args[]=null;
		List<Map<String, Object>> result = null;
		try{
			query=getQuery("GET_RENEWAL_PAYMENT_DETAIL");
			args=new Object[]{bean.getPolicyNo()};
			result=this.mytemplate.queryForList(query, args);
			LogManager.info("Query==>" + query);
			LogManager.info("Args==>" + StringUtils.join(args,", "));
		}
		catch(Exception e){
			e.printStackTrace();
		}
		LogManager.info("Exit from getPaymentList()");
		return result;
	}

	public int getPaymentInsertCashCheque(quickRenewalBean bean) {
		int result=0;
		LogManager.info("Enter into getPaymentInsert()");
		String query="";
		String refNo="";
		int count=0;
		try{
			String qry = getQuery("GET_RENEWAL_PAYMENT_TRANID_SEQ");
			refNo = (String) this.mytemplate.queryForObject(qry, String.class);
			refNo+="R";
			bean.setMerchant_reference(refNo);

			String premQry=getQuery("GET_PREMIUM_AMOUNT");
			Object arg[]=new Object[]{bean.getRenewRefNo()};
			String prem=(String) this.mytemplate.queryForObject(premQry, arg, String.class);

			query=getQuery("INSERT_RENEWALPAYMENT_DETAILS_CASH_CHEQUE");
			Object[] args=new Object[22];
			
			args[0]=bean.getRenewRefNo();
			args[1]="65";
			args[2]=bean.getModeOfPayment();
			if("1".equalsIgnoreCase(bean.getModeOfPayment())) {
				args[3]=bean.getCashChellanNo();
				args[4]=bean.getCashDepositBank();
				args[5]="";
				args[6]=bean.getCashAmount();
				args[7]=bean.getCashInstrumentDate();
			}
			else if("2".equalsIgnoreCase(bean.getModeOfPayment())) {
				args[3]=bean.getChequeNo();
				args[4]=bean.getBankName();
				args[5]=bean.getMicrCode();
				args[6]=bean.getChequeAmount();
				args[7]=bean.getChequeDate();
			}
			args[8]=bean.getMerchant_reference();
			args[9]=bean.getTempEmailId();
			args[10]=bean.getCustInsuredName();
			args[11]="01";
			args[12]=bean.getCustInsuredName();
			args[13]=bean.getCustInsuredName();
			args[14]=bean.getTempEmailId();
			args[15]=bean.getCurrency();
			args[16]=prem;
			args[17]="N";
			args[18]=bean.getMobileNo();
			args[19]=bean.getVehicles();
			args[20]=bean.getPolicyNo();
			args[21]="Y";
			removeNull(args);
			
			LogManager.info("Query==>" + query);
			LogManager.info("Args==>" + StringUtils.join(args,", "));
			result = this.mytemplate.update(query, args);
			
			///query=getQuery("UPD_QUICK_RENEWAL_PAYMENT_INFO");
			//args =new Object[1];
			//args[0] = bean.getRenewRefNo();
			
			//LogManager.info("Update Query => "+query);
			//LogManager.info("Update Arguments => "+StringUtils.join(args, ","));
			//this.mytemplate.update(query,args);
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		LogManager.info("Exit from getPaymentInsert()");
		return result;
	}

	public List<Map<String, Object>> getCashPaymentList(quickRenewalBean bean) {
		String query="";
		List<Map<String ,Object>> result = null;
		try{
			query=getQuery("GET_PENDING_CASH_PAYMENT");
			LogManager.info("Query => "+query);
			result=this.mytemplate.queryForList(query);
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

	public void getCashListDetails(quickRenewalBean bean) {
		String query="";
		List<Map<String ,Object>> result = null;
		Object[] args= null;
		try{
			query = getQuery("GET_CASH_DETAILS");
			args= new Object[]{bean.getMerchant_reference()};
			LogManager.info("Query => "+query);
			LogManager.info("args => "+args);
			result= this.mytemplate.queryForList(query, args);
			
			if(result.size()>0){
				Map map = (Map) result.get(0);
				bean.setCustInsuredName(map.get("CUSTOMER_NAME") == null ? "" : map.get("CUSTOMER_NAME").toString());
				bean.setMobileNo(map.get("MOBILE_NO") == null ? "" : map.get("MOBILE_NO").toString());
				bean.setEmail(map.get("CUSTOMER_EMAIL") == null ? "" : map.get("CUSTOMER_EMAIL").toString());
				bean.setPolicyNo(map.get("POLICY_NO") == null ? "" : map.get("POLICY_NO").toString());
				bean.setPremium(map.get("PREMIUM") == null ? "" : map.get("PREMIUM").toString());
				bean.setMerchant_reference(map.get("QUOTE_NO") == null ? "" : map.get("QUOTE_NO").toString());
				bean.setRiskId(map.get("RISK_ID") == null ? "" : map.get("RISK_ID").toString());
				bean.setChequeNo(map.get("CHEQUE_NO") == null ? "" : map.get("CHEQUE_NO").toString());
				bean.setChequeDate(map.get("CHEQUE_DATE") == null ? "" : map.get("CHEQUE_DATE").toString());
				bean.setChequeAmount(map.get("CHEQUE_AMOUNT") == null ? "" : map.get("CHEQUE_AMOUNT").toString());
				bean.setBankName(map.get("BANK_NAME") == null ? "" : map.get("BANK_NAME").toString());
				bean.setMicrCode(map.get("MICR_CODE") == null ? "" : map.get("MICR_CODE").toString());
				bean.setModeOfPayment(map.get("PAYMENT_TYPE") == null ? "" : map.get("PAYMENT_TYPE").toString());
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public int updateCashStatus(quickRenewalBean bean) {
		String query="";
		Object[] args= null;
		int count = 0;
		String status ="";
		String decision ="";
		try{
			if("Y".equalsIgnoreCase(bean.getStatus())){
				status="Y";
				decision="ACCEPT";
			} else if ("R".equalsIgnoreCase(bean.getStatus())){
				status="";
				decision="CANCEL";
			}
			query=getQuery("UPDATE_MOTOR_CASH_STATUS");
			args = new Object[]{status,bean.getRemarks(),bean.getMerchant_reference()};
			LogManager.info("Query => "+query);
			LogManager.info("args => "+StringUtils.join(args, ", "));
			count = this.mytemplate.update(query, args);
			
			query=getQuery("UPDATE_RISK_CASH_STATUS");
			args = new Object[]{status,bean.getMerchant_reference()};
			LogManager.info("Query => "+query);
			LogManager.info("args => "+StringUtils.join(args, ", "));
			count+= this.mytemplate.update(query, args);
			
			query=getQuery("UPDATE_PAYMENT_DETAIL");
			LogManager.info("Query => "+query);
			LogManager.info("args => "+decision+" , "+bean.getMerchant_reference());
			count+= this.mytemplate.update(query , new Object[]{decision,bean.getMerchant_reference()});
			
		} catch(Exception e){
			e.printStackTrace();
		}
		return count;
	}

	public List<Map<String, Object>> getVehicleList(quickRenewalBean bean) {
		String query="";
		List<Map<String,Object>> result=null;
		Object[] args= null;
		try{
			query="SELECT RISK_ID,CUST_PREM,TO_CHAR(CUR_TO_DT,'DD/MM/YYYY')CUR_TO_DT,TO_CHAR(CUR_FROM_DT,'DD/MM/YYYY')CUR_FROM_DT,REG_NO FROM XX_B2C_RENEWAL WHERE RISK_ID IN ("+bean.getRiskId()+") AND POL_NO ='"+bean.getPolicyNo()+"'";
			LogManager.info("Query => "+query);
			result = this.mytemplate.queryForList(query);
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

	public List<Map<String, Object>> getApproverLoginList(String reqForm) {
		List<Map<String,Object>> result=null;
		try{
			String query = getQuery("GET_PAYMENT_LOGIN_LIST");
			LogManager.info("Query => "+query);
			Object args[]=null;
				args=new Object[]{"surveyor"};
			result = this.mytemplate.queryForList(query, args);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public void settingDetail(String polNo, String vehRegNo, quickRenewalBean bean) {
		List<Map<String,Object>> result=null;
		try{
			String query = getQuery("GET_MOBILENUMBER_QRREADER");
			Object args[]=null;
				args=new Object[]{polNo,vehRegNo};
				LogManager.info("Query => "+query);
				LogManager.info("Args => "+StringUtils.join(args, " ,"));
			result = this.mytemplate.queryForList(query, args);
			LogManager.info("result => "+result);
			if(result.size()>0){
				bean.setMobileNo(result.get(0).get("MOBILE_NO")==null?"":result.get(0).get("MOBILE_NO").toString());
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int getPolicyExist(String polNo) {
		int res=0;
		try{
			String query=getQuery("GET_RENEWAL_POLICY_EXIST");
			Object args[]=new Object[]{polNo};
			res = this.mytemplate.queryForInt(query, args);
			LogManager.info("result => "+res);
		}catch(Exception e){
			e.printStackTrace();
		}
		return res;
	}

}
