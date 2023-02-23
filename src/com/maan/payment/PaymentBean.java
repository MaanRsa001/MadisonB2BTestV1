package com.maan.payment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;

import com.maan.customer.CustBean;

public class PaymentBean extends CustBean{
	private String applicationNo;
	private String productId;
	private String branchCode;
	private String installmentErrorStatus;
	//private String deviceType;
	
	private String currency;
	private String merchant_reference;
	private String amount;
	private String signature;
	
	private String profile_id;
	private String access_key;
	private String secret_key;
	
	private String reference_number;
	private String signed_field_names;
	private String signed_date_time;
	private String transaction_type;
	private String locale;
	private String transaction_uuid;
	private String unsigned_field_names;
	private String recurring_automatic_renew;
	
	private String bill_to_forename;
	private String bill_to_surname;
	private String bill_to_address_line1;
	private String bill_to_address_city;
	private String bill_to_address_country;
	private String bill_to_address_postal_code;
	private String bill_to_email;
	
	/*private String chequeNo;
	private String paymentDate;
	private String paymentAmount;
	private String micrCode;
	private String paymentBank;
	private String modeOfPayment;*/
	
	private String auth_trans_ref_no;
	private String payer_authentication_enroll_veres_enrolled;
	private String req_bill_to_surname;
	private String req_bill_to_address_city;
	private String req_card_expiry_date;
	private String req_bill_to_address_postal_code;
	private String reason_code;
	private String auth_amount;
	private String auth_response;
	private String bill_trans_ref_no;
	private String req_bill_to_forename;
	private String req_payment_method;
	private String request_token;
	private String auth_time;
	private String req_amount;
	private String req_bill_to_email;
	private String payer_authentication_reason_code;
	private String auth_avs_code_raw;
	private String transaction_id;
	private String req_currency;
	private String req_card_type;
	private String payer_authentication_pares_status;
	private String decision;
	private String payer_authentication_cavv;
	private String message;
	
	private String req_transaction_type;
	private String auth_code;
	private String req_recurring_frequency;
	private String req_recurring_number_of_installments;
	private String req_recurring_amount;
	private String payment_token;
	
	
	private String recurring_amount;
	private String recurring_frequency;
	private String recurring_start_date;
	private String recurring_number_of_installments;
	
	private String req_reference_number;
	private List<Map<String, Object>> insList;
	private List<HashMap<String,Object>> errors = new ArrayList<HashMap<String,Object>>();
	
	private String payStatus;
	
	
	public List<HashMap<String, Object>> getErrors() {
		return errors;
	}
	public void setErrors(List<HashMap<String, Object>> errors) {
		this.errors = errors;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getMerchant_reference() {
		return merchant_reference;
	}
	public void setMerchant_reference(String merchantReference) {
		merchant_reference = merchantReference;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
	
	public String getApplicationNo() {
		return applicationNo;
	}
	public void setApplicationNo(String applicationNo) {
		this.applicationNo = applicationNo;
	}
	public String getReference_number() {
		return reference_number;
	}
	public void setReference_number(String referenceNumber) {
		reference_number = referenceNumber;
	}
	public String getSigned_field_names() {
		return signed_field_names;
	}
	public void setSigned_field_names(String signedFieldNames) {
		signed_field_names = signedFieldNames;
	}
	public String getProfile_id() {
		return profile_id;
	}
	public void setProfile_id(String profileId) {
		profile_id = profileId;
	}
	public String getSigned_date_time() {
		return signed_date_time;
	}
	public void setSigned_date_time(String signedDateTime) {
		signed_date_time = signedDateTime;
	}
	public String getTransaction_type() {
		return transaction_type;
	}
	public void setTransaction_type(String transactionType) {
		transaction_type = transactionType;
	}
	public String getLocale() {
		return locale;
	}
	public void setLocale(String locale) {
		this.locale = locale;
	}
	public String getTransaction_uuid() {
		return transaction_uuid;
	}
	public void setTransaction_uuid(String transactionUuid) {
		transaction_uuid = transactionUuid;
	}
	public String getAccess_key() {
		return access_key;
	}
	public void setAccess_key(String accessKey) {
		access_key = accessKey;
	}
	public String getUnsigned_field_names() {
		return unsigned_field_names;
	}
	public void setUnsigned_field_names(String unsignedFieldNames) {
		unsigned_field_names = unsignedFieldNames;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getAuth_trans_ref_no() {
		return auth_trans_ref_no;
	}
	public void setAuth_trans_ref_no(String authTransRefNo) {
		auth_trans_ref_no = authTransRefNo;
	}
	public String getPayer_authentication_enroll_veres_enrolled() {
		return payer_authentication_enroll_veres_enrolled;
	}
	public void setPayer_authentication_enroll_veres_enrolled(
			String payerAuthenticationEnrollVeresEnrolled) {
		payer_authentication_enroll_veres_enrolled = payerAuthenticationEnrollVeresEnrolled;
	}
	public String getReq_bill_to_surname() {
		return req_bill_to_surname;
	}
	public void setReq_bill_to_surname(String reqBillToSurname) {
		req_bill_to_surname = reqBillToSurname;
	}
	public String getReq_bill_to_address_city() {
		return req_bill_to_address_city;
	}
	public void setReq_bill_to_address_city(String reqBillToAddressCity) {
		req_bill_to_address_city = reqBillToAddressCity;
	}
	public String getReq_card_expiry_date() {
		return req_card_expiry_date;
	}
	public void setReq_card_expiry_date(String reqCardExpiryDate) {
		req_card_expiry_date = reqCardExpiryDate;
	}
	public String getReq_bill_to_address_postal_code() {
		return req_bill_to_address_postal_code;
	}
	public void setReq_bill_to_address_postal_code(String reqBillToAddressPostalCode) {
		req_bill_to_address_postal_code = reqBillToAddressPostalCode;
	}
	public String getReason_code() {
		return reason_code;
	}
	public void setReason_code(String reasonCode) {
		reason_code = reasonCode;
	}
	public String getAuth_amount() {
		return auth_amount;
	}
	public void setAuth_amount(String authAmount) {
		auth_amount = authAmount;
	}
	public String getAuth_response() {
		return auth_response;
	}
	public void setAuth_response(String authResponse) {
		auth_response = authResponse;
	}
	public String getBill_trans_ref_no() {
		return bill_trans_ref_no;
	}
	public void setBill_trans_ref_no(String billTransRefNo) {
		bill_trans_ref_no = billTransRefNo;
	}
	public String getReq_bill_to_forename() {
		return req_bill_to_forename;
	}
	public void setReq_bill_to_forename(String reqBillToForename) {
		req_bill_to_forename = reqBillToForename;
	}
	public String getReq_payment_method() {
		return req_payment_method;
	}
	public void setReq_payment_method(String reqPaymentMethod) {
		req_payment_method = reqPaymentMethod;
	}
	public String getRequest_token() {
		return request_token;
	}
	public void setRequest_token(String requestToken) {
		request_token = requestToken;
	}
	public String getAuth_time() {
		return auth_time;
	}
	public void setAuth_time(String authTime) {
		auth_time = authTime;
	}
	public String getReq_amount() {
		return req_amount;
	}
	public void setReq_amount(String reqAmount) {
		req_amount = reqAmount;
	}
	public String getReq_bill_to_email() {
		return req_bill_to_email;
	}
	public void setReq_bill_to_email(String reqBillToEmail) {
		req_bill_to_email = reqBillToEmail;
	}
	public String getPayer_authentication_reason_code() {
		return payer_authentication_reason_code;
	}
	public void setPayer_authentication_reason_code(
			String payerAuthenticationReasonCode) {
		payer_authentication_reason_code = payerAuthenticationReasonCode;
	}
	public String getAuth_avs_code_raw() {
		return auth_avs_code_raw;
	}
	public void setAuth_avs_code_raw(String authAvsCodeRaw) {
		auth_avs_code_raw = authAvsCodeRaw;
	}
	public String getTransaction_id() {
		return transaction_id;
	}
	public void setTransaction_id(String transactionId) {
		transaction_id = transactionId;
	}
	public String getReq_currency() {
		return req_currency;
	}
	public void setReq_currency(String reqCurrency) {
		req_currency = reqCurrency;
	}
	public String getReq_card_type() {
		return req_card_type;
	}
	public void setReq_card_type(String reqCardType) {
		req_card_type = reqCardType;
	}
	public String getPayer_authentication_pares_status() {
		return payer_authentication_pares_status;
	}
	public void setPayer_authentication_pares_status(
			String payerAuthenticationParesStatus) {
		payer_authentication_pares_status = payerAuthenticationParesStatus;
	}
	public String getDecision() {
		return decision;
	}
	public void setDecision(String decision) {
		this.decision = decision;
	}
	public String getPayer_authentication_cavv() {
		return payer_authentication_cavv;
	}
	public void setPayer_authentication_cavv(String payerAuthenticationCavv) {
		payer_authentication_cavv = payerAuthenticationCavv;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getReq_reference_number() {
		return req_reference_number;
	}
	public void setReq_reference_number(String reqReferenceNumber) {
		req_reference_number = reqReferenceNumber;
	}
	public String getBranchCode() {
		return branchCode;
	}
	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}
	public String getBill_to_email() {
		return bill_to_email;
	}
	public void setBill_to_email(String billToEmail) {
		bill_to_email = billToEmail;
	}
	public String getBill_to_forename() {
		return bill_to_forename;
	}
	public void setBill_to_forename(String billToForename) {
		bill_to_forename = billToForename;
	}
	public String getBill_to_surname() {
		return bill_to_surname;
	}
	public void setBill_to_surname(String billToSurname) {
		bill_to_surname = billToSurname;
	}
	public String getBill_to_address_line1() {
		return bill_to_address_line1;
	}
	public void setBill_to_address_line1(String billToAddressLine1) {
		bill_to_address_line1 = billToAddressLine1;
	}
	public String getBill_to_address_city() {
		return bill_to_address_city;
	}
	public void setBill_to_address_city(String billToAddressCity) {
		bill_to_address_city = billToAddressCity;
	}
	public String getBill_to_address_country() {
		return bill_to_address_country;
	}
	public void setBill_to_address_country(String billToAddressCountry) {
		bill_to_address_country = billToAddressCountry;
	}
	public String getBill_to_address_postal_code() {
		return bill_to_address_postal_code;
	}
	public void setBill_to_address_postal_code(String billToAddressPostalCode) {
		bill_to_address_postal_code = billToAddressPostalCode;
	}
	public String getRecurring_amount() {
		return recurring_amount;
	}
	public void setRecurring_amount(String recurringAmount) {
		recurring_amount = recurringAmount;
	}
	public String getRecurring_frequency() {
		return recurring_frequency;
	}
	public void setRecurring_frequency(String recurringFrequency) {
		recurring_frequency = recurringFrequency;
	}
	public String getRecurring_start_date() {
		return recurring_start_date;
	}
	public void setRecurring_start_date(String recurringStartDate) {
		recurring_start_date = recurringStartDate;
	}
	public String getRecurring_number_of_installments() {
		return recurring_number_of_installments;
	}
	public void setRecurring_number_of_installments(
			String recurringNumberOfInstallments) {
		recurring_number_of_installments = recurringNumberOfInstallments;
	}
	public String getInstallmentErrorStatus() {
		return installmentErrorStatus;
	}
	public void setInstallmentErrorStatus(String installmentErrorStatus) {
		this.installmentErrorStatus = installmentErrorStatus;
	}
	public String getSecret_key() {
		return secret_key;
	}
	public void setSecret_key(String secretKey) {
		secret_key = secretKey;
	}
	public String getReq_transaction_type() {
		return req_transaction_type;
	}
	public void setReq_transaction_type(String reqTransactionType) {
		req_transaction_type = reqTransactionType;
	}
	public String getAuth_code() {
		return auth_code;
	}
	public void setAuth_code(String authCode) {
		auth_code = authCode;
	}
	public String getReq_recurring_frequency() {
		return req_recurring_frequency;
	}
	public void setReq_recurring_frequency(String reqRecurringFrequency) {
		req_recurring_frequency = reqRecurringFrequency;
	}
	public String getReq_recurring_number_of_installments() {
		return req_recurring_number_of_installments;
	}
	public void setReq_recurring_number_of_installments(
			String reqRecurringNumberOfInstallments) {
		req_recurring_number_of_installments = reqRecurringNumberOfInstallments;
	}
	public String getReq_recurring_amount() {
		return req_recurring_amount;
	}
	public void setReq_recurring_amount(String reqRecurringAmount) {
		req_recurring_amount = reqRecurringAmount;
	}
	public String getPayment_token() {
		return payment_token;
	}
	public void setPayment_token(String paymentToken) {
		payment_token = paymentToken;
	}
	/*public String getDeviceType() {
		return deviceType;
	}
	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}*/
	/**
	 * @param recurring_automatic_renew the recurring_automatic_renew to set
	 */
	public void setRecurring_automatic_renew(String recurring_automatic_renew) {
		this.recurring_automatic_renew = recurring_automatic_renew;
	}
	/**
	 * @return the recurring_automatic_renew
	 */
	public String getRecurring_automatic_renew() {
		return recurring_automatic_renew;
	}
	/**
	 * @param insList the insList to set
	 */
	public void setInsList(List<Map<String, Object>> insList) {
		this.insList = insList;
	}
	/**
	 * @return the insList
	 */
	public List<Map<String, Object>> getInsList() {
		return insList;
	}
	/**
	 * @return the payStatus
	 */
	public String getPayStatus() {
		return payStatus;
	}
	/**
	 * @param payStatus the payStatus to set
	 */
	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}
	
}
