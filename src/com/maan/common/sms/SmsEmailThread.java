package com.maan.common.sms;

public class SmsEmailThread extends SmsEmailUtil implements Runnable {
	
	public SmsEmailThread(String reqFrom,String quoteNo,String pwd,String otp,String userLoginId,String userName,String productId) {
		super(reqFrom, quoteNo, pwd, otp, userLoginId, userName,productId);
	}
	
	public SmsEmailThread(String reqFrom,String quoteNo,String pwd,String otp,String userLoginId,String userName,String productId,String mobileNo,String expiryDate, String mailOtp, String customerMailId) {
		super(reqFrom, quoteNo, pwd, otp, userLoginId, userName,productId,mobileNo,expiryDate,mailOtp,customerMailId);
	}

	public void run() {
		if(GET_QUOTE.equals(reqFrom) || GET_OTP.equals(reqFrom)
				|| REG_SUCCES.equals(reqFrom) || BUY_POLICY.equals(reqFrom)
				|| PAYMENT_SUCESS_CUST.equals(reqFrom) || PAYMENT_SUCESS_SURVEYOR.equals(reqFrom)
				|| SURVEYOR_ACCEPT.equals(reqFrom) || SURVEYOR_REJECT.equals(reqFrom)
				|| CLAIM_REG.equals(reqFrom) || CLAIM_PROCESS.equals(reqFrom)
				|| CLAIM_UPDATE.equals(reqFrom) || CLAIM_CLOSED.equals(reqFrom)
				|| CLAIM_REJECT.equals(reqFrom) || ENDT_APPROVE_CUST.equals(reqFrom) || GET_ROADASSIST.equals(reqFrom)
				|| ENDT_DISAPPROVE_CUST.equals(reqFrom) || ENDT_REQUEST_CUST.equals(reqFrom) || BUY_POLICY_RENEWAL.equals(reqFrom)
				|| PAYMENT_SUCESS_CUST_RENEWAL.equals(reqFrom) || PAYMENT_SUCESS_SURVEYOR_RENEWAL.equals(reqFrom)|| BUY_POLICY_OPUSER.equals(reqFrom)|| BUY_POLICY_OPUSER_RENEWAL.equals(reqFrom)) {
			sendSMS();
		}
		if(GET_QUOTE.equals(reqFrom) || GET_QUOTE_OPUSER.equals(reqFrom) || GET_OTP.equals(reqFrom)
				|| REG_SUCCES.equals(reqFrom) || REG_SUCCESS_OPUSER.equals(reqFrom)
				|| BUY_POLICY.equals(reqFrom) || BUY_POLICY_OPUSER.equals(reqFrom)
				|| PAYMENT_SUCESS_CUST.equals(reqFrom) || PAYMENT_SUCESS_SURVEYOR.equals(reqFrom)
				|| ENDT_REQUEST_CUST.equals(reqFrom) || ENDT_REQUEST_OPUSER.equals(reqFrom)
				|| ENDT_APPROVE_CUST.equals(reqFrom) || ENDT_APPROVE_OPUSER.equals(reqFrom)
				|| ENDT_DISAPPROVE_CUST.equals(reqFrom) || ENDT_DISAPPROVE_OPUSER.equals(reqFrom)
				|| CLAIM_INTIMATE_CUST.equals(reqFrom) || CLAIM_INTIMATE_OPUSER.equals(reqFrom)
				|| SURVEYOR_ACCEPT.equals(reqFrom) || SURVEYOR_ACCEPT_SURVEYOR.equals(reqFrom)
				|| SURVEYOR_ACCEPT_OPUSER.equals(reqFrom) || SURVEYOR_REJECT.equals(reqFrom)
				|| SURVEYOR_REJECT_SURVEYOR.equals(reqFrom) || SURVEYOR_REJECT_OPUSER.equals(reqFrom)
				|| CLAIM_REG.equals(reqFrom) || CLAIM_REG_OPUSER.equals(reqFrom)
				|| CLAIM_PROCESS.equals(reqFrom) || CLAIM_PROCESS_OPUSER.equals(reqFrom)
				|| CLAIM_UPDATE.equals(reqFrom) || CLAIM_UPDATE_OPUSER.equals(reqFrom)
				|| CLAIM_CLOSED.equals(reqFrom) || CLAIM_CLOSED_OPUSER.equals(reqFrom) || GET_ROADASSIST_OPUSER.equals(reqFrom)
				|| CLAIM_REJECT.equals(reqFrom) || CLAIM_REJECT_OPUSER.equals(reqFrom)|| ONLINE_PAYMENT_STATUS_UNDERWRITER.equals(reqFrom) ||EMAIL_QUOTE.equals(reqFrom)
				|| BUY_POLICY_RENEWAL.equals(reqFrom)|| BUY_POLICY_OPUSER_RENEWAL.equals(reqFrom)|| PAYMENT_SUCESS_CUST_RENEWAL.equals(reqFrom)|| PAYMENT_SUCESS_SURVEYOR_RENEWAL.equals(reqFrom)) {
			sendEMail();
		}
	}
}
