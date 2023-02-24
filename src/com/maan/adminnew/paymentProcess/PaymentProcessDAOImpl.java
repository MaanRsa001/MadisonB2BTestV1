package com.maan.adminnew.paymentProcess;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.maan.Motor.DAO.MotorDAO;
import com.maan.Motor.controller.MotorBean;
import com.maan.common.LogManager;
import com.maan.common.MyJdbcTemplate;
import com.maan.common.dao.CommonDAO;

public class PaymentProcessDAOImpl extends MyJdbcTemplate implements PaymentProcessDAO {
	String query;
	
	/*
	 * Getting Credit Controller,Surveyour and Under Writer Pending list
	 */
	public List<Map<String,Object>> getTransactionList(PaymentProcessBean bean){
		List<Map<String,Object>> result=null;
		try{
			query = getQuery("GET_PAYMENT_PROCESS_DETAILS");
			Object[] args=null;
			if("ccPending".equalsIgnoreCase(bean.getReqForm()))
				args = new Object[]{bean.getReqForm(),bean.getProductId(),bean.getBranchCode(),"CCP",bean.getLoginId()};
			else if("ccNStatus".equalsIgnoreCase(bean.getReqForm())) // Credit Controller Rejected
				args = new Object[]{bean.getReqForm(),bean.getProductId(),bean.getBranchCode(),"CCR",bean.getLoginId()};
			else if("ssPending".equalsIgnoreCase(bean.getReqForm()) || "ccYStatus".equalsIgnoreCase(bean.getReqForm())  )
				args = new Object[]{bean.getReqForm(),bean.getProductId(),bean.getBranchCode(),"SP",bean.getLoginId()};
			else if("ssNStatus".equalsIgnoreCase(bean.getReqForm()))
				args = new Object[]{bean.getReqForm(),bean.getProductId(),bean.getBranchCode(),"UWP",bean.getLoginId()};
			else if("uwPending".equalsIgnoreCase(bean.getReqForm()) || "ssYStatus".equalsIgnoreCase(bean.getReqForm()) )
				args = new Object[]{bean.getReqForm(),bean.getProductId(),bean.getBranchCode(),"UWP",bean.getLoginId()};
			else if("uwYStatus".equalsIgnoreCase(bean.getReqForm()))
				args = new Object[]{bean.getReqForm(),bean.getProductId(),bean.getBranchCode(),"P",bean.getLoginId()};
			else if("uwNStatus".equalsIgnoreCase(bean.getReqForm()))
				args = new Object[]{bean.getReqForm(),bean.getProductId(),bean.getBranchCode(),"D",bean.getLoginId()};
			LogManager.info("Query==>"+query);
			LogManager.info("Arguments = > " + StringUtils.join(args, ","));
			result=this.mytemplate.queryForList(query,args);
		}catch(Exception e){
			e.printStackTrace();
			LogManager.debug("Exception Occured @ creditControl"+e);
		}
		LogManager.info("Exit from the creditControl");
		return result;
	}
	
	public String insPaymentProcessTrac(String policyNo,String quoteNo,String reqfrom,String status,String remarks,String loginId,String applicableLoginId,String productId,String branchCode) {
		String result="";
		try{
			LogManager.info("Enter into the getInsertCC ");
			String payStatus="";
			String query=getQuery("INS_PAYMENT_PROCESS_DETAILS");
			Object[] args = new Object[7];
			args[0] = policyNo;
			args[1] = quoteNo;
			if("ccPending".equalsIgnoreCase(reqfrom)){
				args[2] = "CC";
				if("Y".equalsIgnoreCase(status)){
					payStatus="SP";
					new CommonDAO().homePolicyGeneration(quoteNo, productId,branchCode);
				}
				else
					payStatus="CCR";
			}
			else if("ssPending".equalsIgnoreCase(reqfrom)){
				String paymentType=new CommonDAO().getPaymentType(quoteNo);
				
				if("6".equalsIgnoreCase(paymentType)){
					LogManager.info("Enter into the getInsertCC paymentmode 6... status => "+status);
					if("Y".equalsIgnoreCase(status)){
						args[2] = "SS";
						payStatus="P";
					}/*else if("H".equalsIgnoreCase(status)){
						payStatus="SP";
						args[2] = "CC";
					}*/
					else{
						args[2] = "CC";
						payStatus="SP";
					}
				}
				else{
					LogManager.info("Enter into the getInsertCC offline paymentmode... status => "+status);
					if("Y".equalsIgnoreCase(status)){
						payStatus="UWP";
						args[2] = "SS";
					}else if("H".equalsIgnoreCase(status)){
						payStatus="SP";
						args[2] = "CC";
					}
					else{
						args[2] = "SS";
						payStatus="UWP";
					}
				}
			}
			else if("uwPending".equalsIgnoreCase(reqfrom)){
				if("Y".equalsIgnoreCase(status)){
					args[2] = "UW";
					payStatus="P";
				}else if("H".equalsIgnoreCase(status)){
					args[2] = "SS";
				}
				else{
					args[2] = "UW";
					payStatus="D";
				}
			}
			args[3] = status;
			args[4] = remarks;
			args[5] = loginId;
			args[6] = applicableLoginId==null?"":applicableLoginId;
			LogManager.info("Query => "+query);
			LogManager.info("Arguments==>"+StringUtils.join(args,','));
			this.mytemplate.update(query,args);
			if(!"H".equalsIgnoreCase(status))
				new CommonDAO().updatePolicyStatus(quoteNo,payStatus);
			/*if("H".equalsIgnoreCase(status) && "ssPending".equalsIgnoreCase(reqfrom))
				new CommonDAO().updatePolicyStatus(quoteNo,payStatus);*/
			if("uwPending".equalsIgnoreCase(reqfrom) && "65".equalsIgnoreCase(productId)){
				String sql = getQuery("UPD_POLICY_NO_VEHICLE_ID");
				LogManager.info("Query => "+sql);
				int value =this.mytemplate.update(sql,new Object[]{policyNo,quoteNo});
				System.out.println(value);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
		
	}
	public List<Map<String, Object>> getPaymentDetails(PaymentProcessBean bean) {
		LogManager.info("Enter into getPaymentDetails()");
		List<Map<String, Object>> result = null;
		try {
			String query = "";
			query =getQuery("GET_PREMIUM_DETAILS_PAY_PROCESS");
			LogManager.info("query =>" + query);
			Object args[] = { bean.getQuoteNo(),bean.getBranchCode() };
			LogManager.info("Arguments =>" + StringUtils.join(args, ","));
			result = this.mytemplate.queryForList(query, args);
			if (result.size() > 0) {
				Map map = (Map) result.get(0);
				//Premium Details
				bean.setCustomerId(map.get("CUSTOMER_ID") == null ? "": map.get("CUSTOMER_ID").toString());
				bean.setPolicyNo(map.get("POLICY_NO") == null ? "": map.get("POLICY_NO").toString());
				bean.setPremium(map.get("PREMIUM") == null ? "": map.get("PREMIUM").toString());
				bean.setVat(map.get("POLICY_FEE") == null ? "": map.get("POLICY_FEE").toString());
				bean.setTotPremium(map.get("OVERALL_PREMIUM") == null ? "": map.get("OVERALL_PREMIUM").toString());
				bean.setFromDate(map.get("INCEPTION_DATE") == null ? "": map.get("INCEPTION_DATE").toString());
				bean.setToDate(map.get("EXPIRY_DATE") == null ? "": map.get("EXPIRY_DATE").toString());
				bean.setBranchCode(map.get("BRANCH_CODE") == null ? "": map.get("BRANCH_CODE").toString());
				bean.setApplicationNo(map.get("APPLICATION_NO") == null ? "": map.get("APPLICATION_NO").toString());
				bean.setCurrencyType(map.get("CURRENCY") == null ? "": map.get("CURRENCY").toString());
				
				//Payment Details
				bean.setChequeNo(map.get("CHEQUE_NO") == null ? "": map.get("CHEQUE_NO").toString());
				bean.setBankName(map.get("BANK_NAME") == null ? "": map.get("BANK_NAME").toString());
				bean.setMicrCode(map.get("MICR_CODE") == null ? "": map.get("MICR_CODE").toString());
				bean.setChequeAmount(map.get("CHEQUE_AMOUNT") == null ? "": map.get("CHEQUE_AMOUNT").toString());
				bean.setChequeDate(map.get("CHEQUE_DATE") == null ? "": map.get("CHEQUE_DATE").toString());
				bean.setCashDepositBank(map.get("BANK_NAME") == null ? "": map.get("BANK_NAME").toString());
				bean.setCashChellanNo(map.get("CHEQUE_NO") == null ? "": map.get("CHEQUE_NO").toString());
				bean.setCashAmount(map.get("CHEQUE_AMOUNT") == null ? "": map.get("CHEQUE_AMOUNT").toString());
				bean.setCashInstrumentDate(map.get("CHEQUE_DATE") == null ? "": map.get("CHEQUE_DATE").toString());
				bean.setReqTime(map.get("REQUEST_TIME") == null ? "": map.get("REQUEST_TIME").toString());
				bean.setResTime(map.get("RESPONSE_TIME") == null ? "": map.get("RESPONSE_TIME").toString());
				bean.setResTranNo(map.get("RESPONSE_TRAN_NO") == null ? "": map.get("RESPONSE_TRAN_NO").toString());
				bean.setResStatus(map.get("RESPONSE_STATUS") == null ? "": map.get("RESPONSE_STATUS").toString());
				bean.setResMsge(map.get("RESPONSE_MESSAGE") == null ? "": map.get("RESPONSE_MESSAGE").toString());
				bean.setMaskedCard(map.get("CARD_NUMBER_MASKED") == null ? "": map.get("CARD_NUMBER_MASKED").toString());
				bean.setResCode(map.get("RESPONSE_CODE") == null ? "": map.get("RESPONSE_CODE").toString());
				bean.setModeOfPayment(map.get("PAYMENT_TYPE") == null ? "": map.get("PAYMENT_TYPE").toString());
				bean.setMerchant_reference(map.get("MERCHANT_REFERENCE") == null ? "": map.get("MERCHANT_REFERENCE").toString());
				bean.setPaymentType(map.get("MODE_OF_PAYMENT") == null ? "": map.get("MODE_OF_PAYMENT").toString());
				bean.setResDecision(map.get("RES_DECISION") == null ? "": map.get("RES_DECISION").toString());
			}	
		} catch (Exception e) {
			LogManager.error("Exception Occured @ getPaymentDetails()" + e);
			e.printStackTrace();
		}
		LogManager.info("Exit from getPaymentDetails() ");
		return result;
	}

	public List<Object> getBankNameList(PaymentProcessBean bean) {
		List<Object> result=null;
		try{
			String sql= getQuery("GET_BANK_NAME_LIST_ADMIN");
			LogManager.info("Query => "+sql);
			result = this.mytemplate.queryForList(sql,new Object[]{bean.getBranchCode()}); 
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

	public List<Object> getModeOfPaymentPaymentList(PaymentProcessBean bean) {
		List<Object> result=null;
		try{
			String sql= getQuery("GET_MODE_OF_PAYMENT");
			LogManager.info("Query => "+sql);
			result = this.mytemplate.queryForList(sql); 
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	/*private void updatePaymentProcess(PaymentProcessBean bean,String status){
		try{
			String sql= getQuery("UPD_PAYMENT_PROCESS_DETAILS");
			Object args[]={status,bean.getQuoteNo()};
			LogManager.info("Query => "+sql);
			LogManager.info("Arguments  => "+StringUtils.join(args,","));
			this.mytemplate.update(sql,args); 
		}catch(Exception e){
			e.printStackTrace();
		}
	}*/
	public List<Map<String,Object>> travellerDetailsList(PaymentProcessBean bean){
		List<Map<String,Object>> result=null;
		try{
			String sql= getQuery("GET_TRAVELLER_DETAILS");
			Object args[]={bean.getQuoteNo()};
			LogManager.info("Query => "+sql);
			LogManager.info("Arguments  => "+StringUtils.join(args,","));
			result = this.mytemplate.queryForList(sql,args); 
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}


	public List<Map<String, Object>> getHomeInfo(PaymentProcessBean bean) {
		List<Map<String,Object>> result=null;
		try{
			String sql= getQuery("GET_PAYMENT_PROCESS_HOME_DETAILS");
			Object args[]={bean.getQuoteNo()};
			LogManager.info("Query => "+sql);
			LogManager.info("Arguments  => "+StringUtils.join(args,","));
			result = this.mytemplate.queryForList(sql,args); 
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public List<Map<String, Object>> getTransactionTrackingList(PaymentProcessBean bean) {
		List<Map<String,Object>> result=null;
		try{
			query = getQuery("GET_PAYMENT_PROCESS_TRACK_DETAILS");
			Object[] args=new Object[]{bean.getQuoteNo()};
			LogManager.info("Query==>"+query);
			LogManager.info("Arguments = > " + StringUtils.join(args, ","));
			result=this.mytemplate.queryForList(query,args);
		}catch(Exception e){
			e.printStackTrace();
			LogManager.debug("Exception Occured @ creditControl"+e);
		}
		LogManager.info("Exit from the creditControl");
		return result;
	}

	public List<Map<String, Object>> getvehicleDetails(PaymentProcessBean bean) {
		List<Map<String,Object>> result=null;
		MotorBean bean1 = new MotorBean();
		bean1.setApplicationNo(bean.getApplicationNo());
		try{
			  result = new MotorDAO().getMultiVehicleDetails(bean1);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public List<Map<String, Object>> getApproverLoginList(String reqForm,String productId) {
		List<Map<String,Object>> result=null;
		try{
			String query = getQuery("GET_PAYMENT_LOGIN_LIST");
			LogManager.info("Query => "+query);
			Object args[]=null;
			if("ccPending".equalsIgnoreCase(reqForm)){
				args=new Object[]{"surveyor"};
			}else if("ssPending".equalsIgnoreCase(reqForm)){
				args=new Object[]{"RSAIssuer"};
			}
			result = this.mytemplate.queryForList(query, args);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	public void setPaymentProcessDetails(PaymentProcessBean bean){
		try{
			String query = getQuery("GET_PAYMENT_PROCESS_INFO");
			LogManager.info("Query => "+query);
			Object args[]=new Object[2];
			args[0]=bean.getQuoteNo();
			
			if("ssPending".equalsIgnoreCase(bean.getReqForm()))
				args[1]="CC";
			else if("uwPending".equalsIgnoreCase(bean.getReqForm()))
				args[1]="SS";
			else
				args[1]="";
			LogManager.info("Arguments => "+StringUtils.join(args,","));
			List<Map<String,Object>> result = this.mytemplate.queryForList(query,args);
			if(result!=null && result.size()>0){
				bean.setStatus(result.get(0).get("STATUS")==null?"":result.get(0).get("STATUS").toString());
				bean.setRemarks(result.get(0).get("REMARKS")==null?"":result.get(0).get("REMARKS").toString());	
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

	public List<Map<String, Object>> getCustAttachedDocs(String productId,String quoteNo, String reqFrom) {
		List<Map<String,Object>> resultList = new ArrayList<Map<String,Object>>();
		try {
			if("30".equalsIgnoreCase(productId)){
				Object[] args={quoteNo,productId};
				String query = getQuery("GET_ATTACHED_DOCUMENTS_LIST_HOME");
				LogManager.info("Query==>" + query);
				LogManager.info("Args==>" + StringUtils.join(args,", "));
				resultList=this.mytemplate.queryForList(query, args);
			}else if("65".equalsIgnoreCase(productId)){
				Object[] args={quoteNo,productId,};
				String query = getQuery("GET_ATTACHED_DOCUMENTS_LIST_MOTOR");
				LogManager.info("Query==>" + query);
				LogManager.info("Args==>" + StringUtils.join(args,", "));
				resultList=this.mytemplate.queryForList(query, args);
			}
		} catch(Exception exception) {
			LogManager.debug(exception);
		}

		return resultList;
	}

	public String getPremiumInfo(PaymentProcessBean bean) {
		LogManager.info("getPremiumInfo - Enter ");
		String result="";
		String sql="";
		Object[] obj=null;
		List<Map<String,Object>> premiumInfo=new ArrayList<Map<String,Object>>();
		try {
			sql=this.getQuery("GET_MOTOR_PREMIUM_INFO");
			obj=new Object[]{bean.getApplicationNo()};
			LogManager.info("Sql=>=>"+sql);
			LogManager.info("Obj[]=>"+StringUtils.join(obj,","));
			premiumInfo=this.mytemplate.queryForList(sql,obj);
			bean.setPremiumInfo(premiumInfo);
			bean.setPremiumInfoList(premiumInfo);
			bean.setPolicyFee(new MotorDAO().getPolicyFees(bean.getApplicationNo(),bean.getBranchCode()));
		} catch(Exception e) {
			LogManager.debug(e);
		}
		LogManager.popRemove();
		LogManager.info("getPremiumInfo - Exit");
		return result;
	}
	
	public List<Map<String, Object>> getSurveyorAttachedDocs(String productId,String quoteNo, String reqFrom) {
		List<Map<String,Object>> resultList = new ArrayList<Map<String,Object>>();
		try {
			if("30".equalsIgnoreCase(productId)){
				Object[] args={quoteNo,productId};
				String query = getQuery("GET_ATTACHED_DOCUMENTS_LIST_HOME");
				LogManager.info("Query==>" + query);
				LogManager.info("Args==>" + StringUtils.join(args,", "));
				resultList=this.mytemplate.queryForList(query, args);
			}else if("65".equalsIgnoreCase(productId)){
				Object[] args={quoteNo,productId,};
				String query = getQuery("GET_ATTACHED_DOCUMENTS_LIST_MOTOR");
				LogManager.info("Query==>" + query);
				LogManager.info("Args==>" + StringUtils.join(args,", "));
				resultList=this.mytemplate.queryForList(query, args);
			}
		} catch(Exception exception) {
			LogManager.debug(exception);
		}

		return resultList;
	}

	
	/*public void updateInstallmentDetail(String quoteNo,String type) {
		String query="";
		try {
			if("ISS".equalsIgnoreCase(type)){
				query = getQuery("UPDATE_INSTALLMENT");
			}else if("ISP".equalsIgnoreCase(type)){
				query = getQuery("UPDATE_INSTALLMENT_PAYSTATUS");
			}else if("HSS".equalsIgnoreCase(type)){
				query = getQuery("UPDATE_INSTALLMENT_HOME_STATUS");
			}
				LogManager.info("Query => " + query);
				LogManager.info("Arguments => " + quoteNo);
				this.mytemplate.update(query, new Object[] {quoteNo});
				
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String getInstallmentStatus(String quoteNo) {
		String emiStatus="";
		try {
				String query = getQuery("GET_INSTALLMENT_DETAIL");
				LogManager.info("Query => " + query);
				LogManager.info("Arguments => " + quoteNo);
				emiStatus=(String)this.mytemplate.queryForObject(query,new Object[] {quoteNo}, String.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return emiStatus;
	}*/
	
	public List<Map<String, Object>> getQuoteDetails(PaymentProcessBean bean) {
		List<Map<String,Object>> resultList = new ArrayList<Map<String,Object>>();
		try {
				Object[] args={bean.getQuoteNo()};
				String query = getQuery("GET_CONVERTION_QUOTE_DTL");
				LogManager.info("Query==>" + query);
				LogManager.info("Args==>" + StringUtils.join(args,", "));
				resultList=this.mytemplate.queryForList(query, args);
				if(resultList !=null && resultList.size()>0){
					bean.setInstallmentYN(resultList.get(0).get("INSTALLMENT_YN")==null?"":resultList.get(0).get("INSTALLMENT_YN").toString());
					bean.setPaymentType(resultList.get(0).get("PAYMENT_TYPE")==null?"":resultList.get(0).get("PAYMENT_TYPE").toString());
					bean.setResDecision(resultList.get(0).get("RES_DECISION")==null?"":resultList.get(0).get("RES_DECISION").toString());
					bean.setProductId(resultList.get(0).get("PRODUCT_ID")==null?"":resultList.get(0).get("PRODUCT_ID").toString());
					bean.setDeviceType(resultList.get(0).get("DEVICE_TYPE")==null?"":resultList.get(0).get("DEVICE_TYPE").toString());
					bean.setMerchant_reference(resultList.get(0).get("MERCHANT_REFERENCE")==null?"":resultList.get(0).get("MERCHANT_REFERENCE").toString());
					bean.setApplicationNo(resultList.get(0).get("APPLICATION_NO")==null?"":resultList.get(0).get("APPLICATION_NO").toString());
				}
			
		} catch(Exception e) {
			e.printStackTrace();
		}

		return resultList;
	}

	public int updatePaymentdetail(PaymentProcessBean bean) {
		String paymentType="";
		int value=0;
		try {
			String sql = getQuery("UPDATE_PAYMENT_DETAIL_CONVERT");
			LogManager.info("Query => "+sql);
			if("Y".equalsIgnoreCase(bean.getInstallmentYN()))
				paymentType="sale,create_payment_token";
			else
				paymentType="sale";
				
			Object[] args={
							bean.getBillRefNum()==null?"":bean.getBillRefNum(),
							bean.getQuoteNo(),
							bean.getQuoteNo(),
							bean.getQuoteNo(),
							paymentType,
							bean.getQuoteNo()
							};
			LogManager.info("Args==>" + StringUtils.join(args,", "));
			value=this.mytemplate.update(sql,args);
			System.out.println(value);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return value;
	}
}
