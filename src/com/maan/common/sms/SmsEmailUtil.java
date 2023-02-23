package com.maan.common.sms;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.xml.bind.DatatypeConverter;

import org.apache.commons.lang3.StringUtils;

import com.maan.Mail.controller.mailController;
import com.maan.adminnew.common.CommonService;
import com.maan.common.LogManager;
import com.maan.report.service.PdfService;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;

/**
 * Created By ibrahim  on 24/03/2016
 * */
public class SmsEmailUtil {
	private final SmsEmailDAO dao=new SmsEmailDAO();
	
	public static final String GET_QUOTE = "GET_QUOTE";//1
	public static final String GET_QUOTE_OPUSER = "GET_QUOTE_OPUSER";//2
	public static final String BUY_POLICY = "BUY_POLICY";//3
	public static final String BUY_POLICY_OPUSER = "BUY_POLICY_OPUSER";//4
	public static final String PAYMENT_SUCESS_CUST = "PAYMENT_SUCESS_CUST";//5
	public static final String SURVEYOR_ACCEPT = "SURVEYOR_ACCEPT";//6
	public static final String SURVEYOR_ACCEPT_OPUSER = "SURVEYOR_ACCEPT_OPUSER";//7
	public static final String SURVEYOR_ACCEPT_SURVEYOR = "SURVEYOR_ACCEPT_SURVEYOR";//8
	public static final String SURVEYOR_REJECT = "SURVEYOR_REJECT";//9
	public static final String SURVEYOR_REJECT_SURVEYOR = "SURVEYOR_REJECT_SURVEYOR";//10
	public static final String SURVEYOR_REJECT_OPUSER = "SURVEYOR_REJECT_OPUSER";//11
	public static final String CLAIM_REG = "CLAIM_REG";//12
	public static final String CLAIM_REG_OPUSER = "CLAIM_REG_OPUSER";//13
	public static final String CLAIM_PROCESS = "CLAIM_PROCESS";//14
	public static final String CLAIM_PROCESS_OPUSER = "CLAIM_PROCESS_OPUSER";//15
	public static final String CLAIM_UPDATE = "CLAIM_UPDATE";//16
	public static final String CLAIM_UPDATE_OPUSER = "CLAIM_UPDATE_OPUSER";//17
	public static final String CLAIM_CLOSED = "CLAIM_CLOSED";//18
	public static final String CLAIM_CLOSED_OPUSER	= "CLAIM_CLOSED_OPUSER";//19
	public static final String CLAIM_REJECT = "CLAIM_REJECT";//20
	public static final String CLAIM_REJECT_OPUSER = "CLAIM_REJECT_OPUSER";//21
	public static final String REG_SUCCES = "REG_SUCCES";//22
	public static final String REG_SUCCESS_OPUSER = "REG_SUCCESS_OPUSER";//23
	public static final String GET_OTP = "GET_OTP";//24
	public static final String PAYMENT_SUCESS_SURVEYOR = "PAYMENT_SUCESS_SURVEYOR";//25
	public static final String ENDT_REQUEST_CUST = "ENDT_REQUEST_CUST";//26
	public static final String ENDT_REQUEST_OPUSER = "ENDT_REQUEST_OPUSER";//27
	public static final String ENDT_APPROVE_CUST = "ENDT_APPROVE_CUST";//28
	public static final String ENDT_APPROVE_OPUSER = "ENDT_APPROVE_OPUSER";//29
	public static final String CLAIM_INTIMATE_CUST = "CLAIM_INTIMATE_CUST";//30
	public static final String CLAIM_INTIMATE_OPUSER = "CLAIM_INTIMATE_OPUSER";//31
	public static final String ONLINE_PAYMENT_STATUS_UNDERWRITER = "ONLINE_PAYMENT_STATUS_UNDERWRITER";//32
	public static final String EMAIL_QUOTE="EMAIL_QUOTE";//33
	public static final String ENDT_DISAPPROVE_CUST = "ENDT_DISAPPROVE_CUST";//34
	public static final String ENDT_DISAPPROVE_OPUSER = "ENDT_DISAPPROVE_OPUSER";//35
	public static final String GET_ROADASSIST = "GET_ROADASSIST";//36
	public static final String GET_ROADASSIST_OPUSER = "GET_ROADASSIST_OPUSER";//37
	public static final String BUY_POLICY_RENEWAL = "BUY_POLICY_RENEWAL";//39
	public static final String BUY_POLICY_OPUSER_RENEWAL = "BUY_POLICY_OPUSER_RENEWAL";//40
	public static final String PAYMENT_SUCESS_CUST_RENEWAL = "PAYMENT_SUCESS_CUST_RENEWAL";//41
	public static final String PAYMENT_SUCESS_SURVEYOR_RENEWAL = "PAYMENT_SUCESS_SURVEYOR_RENEWAL";//42
	
	protected String reqFrom;
	private String quoteNo;
	private String pwd;
	private String otp;
	private String userLoginId;
	private String mobileNo;
	private String userName;
	private String subject;
	private String productId;
	private String adminEmailId;
	private String adminCCEmailId;
	private String adminMobileNo;
	/*private String refNo;
	private String custName;
	private String policyNo;
	private String assistantType;
	private String desc;
	private String latitude;
	private String longitude;*/
	
	private String expiryDate;
	private String mailOtp;
	private String customerMailId;
	
	private List<Map<String,Object>> mobileNoList;

	

	
	
	public SmsEmailUtil(String reqFrom,String quoteNo){
		this.reqFrom = reqFrom;
		this.quoteNo = quoteNo;
	}
	public SmsEmailUtil(String reqFrom,String quoteNo,String productId){
		this.reqFrom = reqFrom;
		this.quoteNo = quoteNo;
		this.productId=productId;
	}
	public SmsEmailUtil(String reqFrom,String pwd,String otp,String userLoginId, String userName) {
		this.reqFrom = reqFrom;
		this.pwd = pwd;
		this.otp = otp;
		this.userLoginId = userLoginId;
		this.userName = userName;
	}
	public SmsEmailUtil(String reqFrom, String otp, String mobileNo,String expiryDate,String mailOtp,String email) {
		this.reqFrom = reqFrom;
		this.otp = otp;
		this.mobileNo = mobileNo;
		this.expiryDate = expiryDate;
		this.mailOtp = mailOtp;
		this.customerMailId=email;
	}
	
	protected SmsEmailUtil(String reqFrom,String quoteNo,String pwd,String otp,String userLoginId, String userName,String productId,String mobileNo,String expiryDate, String mailOtp,String email) {
		this.quoteNo = quoteNo;
		this.reqFrom = reqFrom;
		this.pwd = pwd;
		this.otp = otp;
		this.userLoginId = userLoginId;
		this.userName = userName;
		this.productId=productId;
		this.mobileNo = mobileNo;
		this.expiryDate = expiryDate;
		this.mailOtp = mailOtp;
		this.customerMailId=email;
	}
	/*public SmsEmailUtil(String reqFrom, String refNo, String custName,
			String policyNo, String mobileNo, String assistantType,
			String desc, String latitude, String longitude) {
		this.reqFrom = reqFrom;
		this.refNo = refNo;
		this.custName = custName;
		this.policyNo = policyNo;
		this.mobileNo = mobileNo;
		this.assistantType = assistantType;
		this.desc = desc;
		this.latitude = latitude;
		this.longitude = longitude;
		
	}*/
	
	/*
	 *  This constructor is used for thread call
	 */
	protected SmsEmailUtil(String reqFrom,String quoteNo,String pwd,String otp,String userLoginId, String userName, String productId) {
		this.quoteNo = quoteNo;
		this.reqFrom = reqFrom;
		this.pwd = pwd;
		this.otp = otp;
		this.userLoginId = userLoginId;
		this.userName = userName;
		this.productId = productId;
	}
	public void send() {
		Runnable hello = new SmsEmailThread(reqFrom,quoteNo,pwd,otp,userLoginId,userName,productId,mobileNo,expiryDate,mailOtp,customerMailId);
	    Thread thread1 = new Thread(hello);
	    thread1.setDaemon(true);
	    thread1.setName("ReferralMail");
	    System.out.println("Started Mail Trigger...");
	    thread1.start();
	}
	
	protected void sendSMS() {
		try {
			String result="";
			LogManager.info("Enter Into Sending SMS");
			String content="";
			content = processSMS();
			
			String fromAddress = "Madison";
			if(PAYMENT_SUCESS_SURVEYOR.equals(reqFrom) ||PAYMENT_SUCESS_SURVEYOR_RENEWAL.equals(reqFrom)|| BUY_POLICY_OPUSER.equals(reqFrom) ||BUY_POLICY_OPUSER_RENEWAL.equals(reqFrom)){
				for(Map<String, Object> obj:mobileNoList){
					if(StringUtils.isNotBlank(obj.get("MOBILE_NO")==null?"":obj.get("MOBILE_NO").toString())){
						LogManager.info("HttpResponse<String> response = Unirest.post(\"https://v8vye.api.infobip.com/sms/1/text/single\").header(\"authorization\",\"Basic" + base64()+"\").header(\"content-type\", \"application/json\").header(\"accept\",\"application/json\").body(\"{\"from\":\""+fromAddress+"\",\"to\":\"+260"+obj.get("MOBILE_NO")+"\",\"text\":\""+content+"\"}\").asString();");
						HttpResponse<String> response = Unirest.post("https://v8vye.api.infobip.com/sms/1/text/single")
						.header("authorization", "Basic " + base64())
						.header("content-type", "application/json")
						.header("accept", "application/json")
						.body("{\"from\":\""+fromAddress+"\",\"to\":\"+26"+obj.get("MOBILE_NO")+"\",\"text\":\""+content+"\"}")
						//.body("{\"from\":\""+fromAddress+"\",\"to\":\"+91"+obj.get("MOBILE_NO")+"\",\"text\":\""+content+"\"}")
						.asString();
						result=response.getStatusText().toString();
						int res=response.getStatus();
						String re=response.getBody();
						LogManager.info("Respones => ResCode : "+res + ", Body : "+re+" , Result :"+result);
					}
				}
			} 
			/*else if(GET_ROADASSIST.equals(reqFrom)) {
				LogManager.info("HttpResponse<String> response = Unirest.post(\"https://api.infobip.com/sms/1/text/single\").header(\"authorization\",\"Basic" + base64()+"\").header(\"content-type\", \"application/json\").header(\"accept\",\"application/json\").body(\"{\"from\":\""+fromAddress+"\",\"to\":\"+260"+mobileNo+"\",\"text\":\""+content+"\"}\").asString();");
				HttpResponse<String> response = Unirest.post("https://api.infobip.com/sms/1/text/single")
				.header("authorization", "Basic " + base64())
				.header("content-type", "application/json")
				.header("accept", "application/json")
				.body("{\"from\":\""+fromAddress+"\",\"to\":\"+26"+mobileNo+"\",\"text\":\""+content+"\"}")
				//.body("{\"from\":\""+fromAddress+"\",\"to\":\"+91"+mobileNo+"\",\"text\":\""+content+"\"}")
				.asString();
				result=response.getStatusText().toString();
			}*/ else {
				LogManager.info("HttpResponse<String> response = Unirest.post(\"https://v8vye.api.infobip.com/sms/1/text/single\").header(\"authorization\",\"Basic" + base64()+"\").header(\"content-type\", \"application/json\").header(\"accept\",\"application/json\").body(\"{\"from\":\""+fromAddress+"\",\"to\":\"+26"+mobileNo+"\",\"text\":\""+content+"\"}\").asString();");
				HttpResponse<String> response = Unirest.post("https://v8vye.api.infobip.com/sms/1/text/single")
				.header("authorization", "Basic " + base64())
				.header("content-type", "application/json")
				.header("accept", "application/json")
				.body("{\"from\":\""+fromAddress+"\",\"to\":\"+26"+mobileNo+"\",\"text\":\""+content+"\"}")
				//.body("{\"from\":\""+fromAddress+"\",\"to\":\"+91"+mobileNo+"\",\"text\":\""+content+"\"}")
				.asString();
				result=response.getStatusText().toString();
				int res=response.getStatus();
				String re=response.getBody();
				LogManager.info("Respones => ResCode : "+res + ", Body : "+re+" , Result :"+result);
			}
			/*
			 * For Sending SMS to the Admin User
			 */
			if(adminMobileNo!=null && adminMobileNo.length()>0){
				String [] mobileNo =adminMobileNo.split(",");
				for (String mob : mobileNo) {
					LogManager.info("HttpResponse<String> response = Unirest.post(\"https://v8vye.api.infobip.com/sms/1/text/single\").header(\"authorization\",\"Basic" + base64()+"\").header(\"content-type\", \"application/json\").header(\"accept\",\"application/json\").body(\"{\"from\":\""+fromAddress+"\",\"to\":\"+260"+mobileNo+"\",\"text\":\""+content+"\"}\").asString();");
					HttpResponse<String> response = Unirest.post("https://v8vye.api.infobip.com/sms/1/text/single")
					.header("authorization", "Basic " + base64())
					.header("content-type", "application/json")
					.header("accept", "application/json")
					.body("{\"from\":\""+fromAddress+"\",\"to\":\"+26"+mob+"\",\"text\":\""+content+"\"}")
					//.body("{\"from\":\""+fromAddress+"\",\"to\":\"+91"+mob+"\",\"text\":\""+content+"\"}")
					.asString();
					result=response.getStatusText().toString();
					int res=response.getStatus();
					String re=response.getBody();
					LogManager.info("Respones => ResCode : "+res + ", Body : "+re+" , Result :"+result);	
				}
			}
			LogManager.info("Exit Into Sending SMS");
		} catch (Exception e) {
			e.printStackTrace();
		}
		//return result;
	}
	
	/*protected void sendSMS() {
		try {
			String result="";
			LogManager.info("Enter Into Sending SMS");
			String content="";
			content = processSMS();
			String fromAddress = "Madison";
			if(PAYMENT_SUCESS_SURVEYOR.equals(reqFrom)){
				for(Map<String, Object> obj:mobileNoList){
					if(StringUtils.isNotBlank(obj.get("MOBILE_NO")==null?"":obj.get("MOBILE_NO").toString())){
						LogManager.info("HttpResponse<String> response = Unirest.post(\"https://api.infobip.com/sms/1/text/single\").header(\"authorization\",\"Basic" + base64()+"\").header(\"content-type\", \"application/json\").header(\"accept\",\"application/json\").body(\"{\"from\":\""+fromAddress+"\",\"to\":\"+260"+obj.get("MOBILE_NO")+"\",\"text\":\""+content+"\"}\").asString();");
						HttpResponse<String> response = Unirest.post("https://api.infobip.com/sms/1/text/single")
						.header("authorization", "Basic " + base64())
						.header("content-type", "application/json")
						.header("accept", "application/json")
						.body("{\"from\":\""+fromAddress+"\",\"to\":\"+26"+obj.get("MOBILE_NO")+"\",\"text\":\""+content+"\"}")
						.asString();
						result=response.getStatusText().toString();
					}
				}
			} else {
				LogManager.info("HttpResponse<String> response = Unirest.post(\"https://api.infobip.com/sms/1/text/single\").header(\"authorization\",\"Basic" + base64()+"\").header(\"content-type\", \"application/json\").header(\"accept\",\"application/json\").body(\"{\"from\":\""+fromAddress+"\",\"to\":\"+260"+mobileNo+"\",\"text\":\""+content+"\"}\").asString();");
				HttpResponse<String> response = Unirest.post("https://api.infobip.com/sms/1/text/single")
				.header("authorization", "Basic " + base64())
				.header("content-type", "application/json")
				.header("accept", "application/json")
				.body("{\"from\":\""+fromAddress+"\",\"to\":\"+26"+mobileNo+"\",\"text\":\""+content+"\"}")
				.asString();
				result=response.getStatusText().toString();
			}
			LogManager.info("Respones => "+result);
			LogManager.info("Exit Into Sending SMS");
		} catch (Exception e) {
			e.printStackTrace();
		}
		//return result;
	}*/
	
	protected void sendEMail() {
		try {
			LogManager.info("Enter Into Sending Email");
			mailController mail = new mailController();
			
			if(GET_QUOTE_OPUSER.equals(reqFrom) || REG_SUCCESS_OPUSER.equals(reqFrom) 
					|| BUY_POLICY_OPUSER.equals(reqFrom) || ENDT_REQUEST_OPUSER.equals(reqFrom) 
					|| CLAIM_INTIMATE_OPUSER.equals(reqFrom) || CLAIM_REG.equals(reqFrom) 
					|| CLAIM_REG_OPUSER.equals(reqFrom) || ONLINE_PAYMENT_STATUS_UNDERWRITER.equals(reqFrom)
					|| BUY_POLICY_OPUSER_RENEWAL.equals(reqFrom)
					 ) {
				LogManager.info("ReqFrom =>"+reqFrom);
				List<Map<String,Object>> adminList = dao.getAdminDetails("RSAIssuer");
				for(Map<String,Object> adminMap : adminList) {
					String adminName = adminMap.get("USERNAME")==null?"":adminMap.get("USERNAME").toString();
					String toMailIds = adminMap.get("USER_MAIL")==null?"":adminMap.get("USER_MAIL").toString();
					Map<String,String> content = processEmail("", "", adminName);
					mail.postMail( toMailIds.split(","), content.get("EMAIL_SUBJECT"), content.get("EMAIL_CONTENT"), "", content.get("BRANCH_CODE"),adminCCEmailId.split(","));
				}
			} else if( ENDT_APPROVE_OPUSER.equals(reqFrom) 
					 || ENDT_DISAPPROVE_OPUSER.equals(reqFrom) 
					 || CLAIM_PROCESS_OPUSER.equals(reqFrom)
					|| CLAIM_UPDATE_OPUSER.equals(reqFrom) || CLAIM_CLOSED_OPUSER.equals(reqFrom)
					|| CLAIM_REJECT_OPUSER.equals(reqFrom)
					) {
				List<Map<String,Object>> adminList = dao.getAdminDetails("RSAIssuer");
				for(Map<String,Object> adminMap : adminList) {
					String adminName = adminMap.get("USERNAME")==null?"":adminMap.get("USERNAME").toString();
					String toMailIds = adminMap.get("USER_MAIL")==null?"":adminMap.get("USER_MAIL").toString();
					Map<String,String> content = processEmail("", "", adminName);
					mail.postMail( toMailIds.split(","), content.get("EMAIL_SUBJECT"), content.get("EMAIL_CONTENT"), "", content.get("BRANCH_CODE"),adminCCEmailId.split(","));
				}				
			} else if(PAYMENT_SUCESS_SURVEYOR.equals(reqFrom) || PAYMENT_SUCESS_SURVEYOR_RENEWAL.equals(reqFrom)) {
				List<Map<String,Object>> adminList = dao.getAdminDetails("surveyor");
				for(Map<String,Object> adminMap : adminList) {
					String adminName = adminMap.get("USERNAME")==null?"":adminMap.get("USERNAME").toString();
					String toMailIds = adminMap.get("USER_MAIL")==null?"":adminMap.get("USER_MAIL").toString();
					Map<String,String> content = processEmail("", "", adminName);
					mail.postMail( toMailIds.split(","), content.get("EMAIL_SUBJECT"), content.get("EMAIL_CONTENT"), "", content.get("BRANCH_CODE"),adminCCEmailId.split(","));
				}
				
			} else if( SURVEYOR_ACCEPT_SURVEYOR.equals(reqFrom) || SURVEYOR_REJECT_SURVEYOR.equals(reqFrom) ) {
				Map<String,Object> approverMap = dao.getApproverDetails(reqFrom, quoteNo);
				String surveyorName = approverMap.get("USERNAME")==null?"":approverMap.get("USERNAME").toString();
				String surveyorMobileNo = approverMap.get("MOBILE_NO")==null?"":approverMap.get("MOBILE_NO").toString();
				String toMailIds = approverMap.get("USER_MAIL")==null?"":approverMap.get("USER_MAIL").toString();
				Map<String,String> content = processEmail(surveyorName, surveyorMobileNo, "");
				mail.postMail( toMailIds.split(","), content.get("EMAIL_SUBJECT"), content.get("EMAIL_CONTENT"), "", content.get("BRANCH_CODE"),adminCCEmailId.split(","));
			} else if( SURVEYOR_ACCEPT_OPUSER.equals(reqFrom) || SURVEYOR_REJECT_OPUSER.equals(reqFrom) ) {
				Map<String,Object> approverMap = dao.getApproverDetails(reqFrom, quoteNo);
				String surveyorName = approverMap.get("USERNAME")==null?"":approverMap.get("USERNAME").toString();
				String surveyorMobileNo = approverMap.get("MOBILE_NO")==null?"":approverMap.get("MOBILE_NO").toString();
				List<Map<String,Object>> adminList = dao.getAdminDetails("RSAIssuer");
				for(Map<String,Object> adminMap : adminList) {
					String adminName = adminMap.get("USERNAME")==null?"":adminMap.get("USERNAME").toString();
					String toMailIds = adminMap.get("USER_MAIL")==null?"":adminMap.get("USER_MAIL").toString();
					Map<String,String> content = processEmail(surveyorName, surveyorMobileNo, adminName);
					mail.postMail( toMailIds.split(","), content.get("EMAIL_SUBJECT"), content.get("EMAIL_CONTENT"), "", content.get("BRANCH_CODE"),adminCCEmailId.split(","));
				}
			}else if(EMAIL_QUOTE.equals(reqFrom)){
				Map<String,String> content = processEmail("","","");
				String filePath=new PdfService().quotePrint(productId, "","", quoteNo, content.get("BRANCH_CODE"), "");
				final File attachment = new File(new CommonService().getApplicationPath()+filePath);
				mail.postMail1(content.get("CUST_MAILID").split(","),content.get("BRO_MAILID").split(","), content.get("EMAIL_SUBJECT"), content.get("EMAIL_CONTENT"), "", filePath, content.get("BRANCH_CODE"), attachment) ;
			}
			else if(GET_ROADASSIST_OPUSER.equals(reqFrom)){
				List<Map<String,Object>> adminList = dao.getAdminDetails("RSAIssuer");
				for(Map<String,Object> adminMap : adminList) {
					String adminName = adminMap.get("USERNAME")==null?"":adminMap.get("USERNAME").toString();
					String toMailIds = adminMap.get("USER_MAIL")==null?"":adminMap.get("USER_MAIL").toString();
					Map<String,String> content = processEmail("","",adminName);
					mail.postMail( toMailIds.split(","), content.get("EMAIL_SUBJECT"), content.get("EMAIL_CONTENT"), "", content.get("BRANCH_CODE"),adminCCEmailId.split(","));
				}
			}
			else {
				Map<String,String> content = processEmail("", "", "");
				mail.postMail( content.get("CUST_MAILID").split(","), content.get("EMAIL_SUBJECT"), content.get("EMAIL_CONTENT"), "", content.get("BRANCH_CODE"),adminCCEmailId.split(","));
			}
			/*
			 * Send Admin Email Details Using Template Master
			 */
			if(adminEmailId!=null && adminEmailId.length()>0){
				Map<String,String> content = processEmail("", "", "");
				mail.postMail( adminEmailId.split(","), content.get("EMAIL_SUBJECT"), content.get("EMAIL_CONTENT"), "", content.get("BRANCH_CODE"),adminCCEmailId.split(","));
			}
			LogManager.info("Exit Into Sending Email");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private String base64() throws Exception {
		String encodedString = DatatypeConverter.printBase64Binary("madisongi:m9enADpw0rd2!".getBytes("UTF-8"));
		System.out.println(encodedString);
		return encodedString;
	}
	
	/**
	 *  This Method for Return Email Content 
	 * */
	public Map<String,String> processEmail(String surveyourName, String surveyorMobileNo, String opuserName) {
		String cusName="";
		String mailId=""; 
		String amount="";
		String paymentMode="";
		String claimNo="";
		String product="";
		String policyNo = "";
		String endtTypeName = "";
		String branchCode = "";
		String surveyorMailId="";
		String paymentStatus="";
		String desc ="";
		String longi ="";
		String lat ="";
		String device ="";
		String loc = "";
		String assist ="";
		String brokermail="";
		Map<Object, Object> rec=null;
		if(GET_QUOTE.equals(reqFrom) || GET_QUOTE_OPUSER.equals(reqFrom) || BUY_POLICY.equals(reqFrom) || BUY_POLICY_OPUSER.equals(reqFrom)
				|| PAYMENT_SUCESS_CUST.equals(reqFrom) || PAYMENT_SUCESS_SURVEYOR.equals(reqFrom)
				|| ENDT_REQUEST_CUST.equals(reqFrom) || ENDT_REQUEST_OPUSER.equals(reqFrom)
				|| ENDT_REQUEST_OPUSER.equals(reqFrom) || ENDT_APPROVE_CUST.equals(reqFrom)
				|| ENDT_DISAPPROVE_CUST.equals(reqFrom)|| ENDT_DISAPPROVE_OPUSER.equals(reqFrom)
				|| ENDT_APPROVE_OPUSER.equals(reqFrom) || CLAIM_INTIMATE_CUST.equals(reqFrom) 
				|| CLAIM_INTIMATE_OPUSER.equals(reqFrom)|| SURVEYOR_ACCEPT.equals(reqFrom)
				|| SURVEYOR_REJECT.equals(reqFrom) ||ONLINE_PAYMENT_STATUS_UNDERWRITER.equals(reqFrom) 
				||EMAIL_QUOTE.equals(reqFrom)) {
			rec = dao.getCustInfo(quoteNo);
			cusName = rec.get("INSURED_NAME")==null?"":rec.get("INSURED_NAME").toString().trim();
			mobileNo = rec.get("MOBILE")==null?"":rec.get("MOBILE").toString();
			mailId = rec.get("EMAIL")==null?"":rec.get("EMAIL").toString();
			paymentMode = rec.get("PAYMENT_MODE")==null?"":rec.get("PAYMENT_MODE").toString();
			product = rec.get("PRODUCT")==null?"":rec.get("PRODUCT").toString();
			branchCode = rec.get("OVER")==null?"":rec.get("BRANCH_CODE").toString();
			amount = rec.get("OVERALL_PREMIUM")==null?"":rec.get("OVERALL_PREMIUM").toString();
			policyNo = rec.get("POLICY_NO")==null?"":rec.get("POLICY_NO").toString();
			endtTypeName = rec.get("ENDT_TYPE_NAME")==null?"":rec.get("ENDT_TYPE_NAME").toString();
			paymentStatus=rec.get("RES_DECISION")==null?"":rec.get("RES_DECISION").toString();
			brokermail=rec.get("BROKER_MAIL")==null?"":rec.get("BROKER_MAIL").toString();
		} else if( CLAIM_REG.equals(reqFrom) || CLAIM_REG_OPUSER.equals(reqFrom)
				|| CLAIM_PROCESS.equals(reqFrom) || CLAIM_PROCESS_OPUSER.equals(reqFrom)
				|| CLAIM_UPDATE.equals(reqFrom) || CLAIM_UPDATE_OPUSER.equals(reqFrom)
				|| CLAIM_CLOSED.equals(reqFrom) || CLAIM_CLOSED_OPUSER.equals(reqFrom)
				|| CLAIM_REJECT.equals(reqFrom) || CLAIM_REJECT_OPUSER.equals(reqFrom) ) {
			rec=dao.getClaimInfo( quoteNo);
			cusName=rec.get("INSURED_NAME")==null?"":rec.get("INSURED_NAME").toString().trim();
			mailId = rec.get("EMAIL")==null?"":rec.get("EMAIL").toString();
			mobileNo=rec.get("MOBILE")==null?"":rec.get("MOBILE").toString();
			claimNo=rec.get("CLAIM_NO")==null?"":rec.get("CLAIM_NO").toString();
			policyNo = rec.get("POLICY_NO")==null?"":rec.get("POLICY_NO").toString();
		} else if(REG_SUCCES.equals(reqFrom) || REG_SUCCESS_OPUSER.equals(reqFrom)) {
			rec=dao.getUserInfo(userLoginId);
			mailId=rec.get("USER_MAIL")==null?"":rec.get("USER_MAIL").toString();
			mobileNo =rec.get("MOBILE")==null?"":rec.get("MOBILE").toString();
			cusName = rec.get("CUST_NAME")==null?"":rec.get("CUST_NAME").toString();
		} else if(GET_ROADASSIST_OPUSER.equals(reqFrom)){
			rec=dao.getRoadAssistant(quoteNo);
			cusName=rec.get("CUSTOMER_NAME")==null?"":rec.get("CUSTOMER_NAME").toString().trim();
			mobileNo =rec.get("MOBILE_NO")==null?"":rec.get("MOBILE_NO").toString();
			assist =rec.get("ASSISTANT_TYPE")==null?"":rec.get("ASSISTANT_TYPE").toString();
			policyNo =rec.get("POLICY_NO")==null?"":rec.get("POLICY_NO").toString();
			desc = rec.get("DESCRIPTION")==null?"":rec.get("DESCRIPTION").toString();
			longi =rec.get("LONGITUDE")==null?"":rec.get("LONGITUDE").toString();
			lat =rec.get("LATITUDE")==null?"":rec.get("LATITUDE").toString();
			device =rec.get("DEVICE_ID")==null?"":rec.get("DEVICE_ID").toString();
			loc =rec.get("LOCATION")==null?"":rec.get("LOCATION").toString();
		}
		else if(GET_OTP.equals(reqFrom)){
			if(StringUtils.isBlank(customerMailId)){
				//String subMobile = mobileNo.substring(4,mobileNo.length());
				//mobileNo="0"+subMobile;
				rec = dao.getMailInfo(mobileNo);
				if(rec != null && rec.size()>0){
					mailId = rec.get("USER_MAIL")==null?"":rec.get("USER_MAIL").toString();
				}
			}else{
			mailId=customerMailId==null?"":customerMailId;
			}
		}else if(BUY_POLICY_RENEWAL.equals(reqFrom)||BUY_POLICY_OPUSER_RENEWAL.equals(reqFrom)||PAYMENT_SUCESS_CUST_RENEWAL.equals(reqFrom)||PAYMENT_SUCESS_SURVEYOR_RENEWAL.equals(reqFrom)){
			List<Map <Object,Object>> res = dao.getRenewalCustInfo(quoteNo);
			rec=res.get(0);
			cusName = rec.get("INSURED_NAME")==null?"":rec.get("INSURED_NAME").toString().trim();
			mobileNo = rec.get("MOBILE")==null?"":rec.get("MOBILE").toString();
			mailId = rec.get("EMAIL")==null?"":rec.get("EMAIL").toString();
			paymentMode = rec.get("PAYMENT_MODE")==null?"":rec.get("PAYMENT_MODE").toString();
			amount = rec.get("OVERALL_PREMIUM")==null?"":rec.get("OVERALL_PREMIUM").toString();
			policyNo = rec.get("POLICY_NO")==null?"":rec.get("POLICY_NO").toString();
			
		}
		Map<String,String> res = new HashMap();
		res.put("CUST_NAME", cusName==null?"":cusName);
		res.put("MOBILE_NO", mobileNo==null?"":mobileNo);
		res.put("MAIL_ID", mailId==null?"":mailId);
		res.put("QUOTE_NO", quoteNo==null?"":quoteNo);
		res.put("CUST_OTP", otp==null?"":otp);
		res.put("CUST_PWD", pwd==null?"":pwd);
		res.put("PAY_AMOUNT", amount==null?"":amount);
		res.put("PAYMENT_MODE", paymentMode==null?"":paymentMode);
		res.put("CLAIM_NO", claimNo==null?"":claimNo);
		res.put("PRODUCT_NAME", product==null?"":product);
		res.put("POLICY_NO", policyNo==null?"":policyNo);
		res.put("LONG", longi==null?"":longi);
		res.put("LAT", lat==null?"":lat);
		res.put("LOCATION", loc==null?"":loc);
		res.put("DEVICE", device==null?"":device);
		res.put("ASSISTANT_TYPE", assist==null?"":assist);
		res.put("DESC", desc==null?"":desc);
		res.put("SURVEYOUR_NAME", surveyourName==null?"":surveyourName);
		res.put("SURVEYOUR_MOBILE", surveyorMobileNo==null?"":surveyorMobileNo);
		res.put("ENDT_TYPE_NAME", endtTypeName==null?"":endtTypeName);
		res.put("OP_USER_NAME", opuserName==null?"":opuserName);
		res.put("SURVEYOUR_EMAIL_ID", opuserName==null?"":surveyorMailId);
		res.put("PAYMENT_STATUS", paymentStatus==null?"":paymentStatus);
		res.put("OTP_EXPIRY", expiryDate==null?"":expiryDate);
		res.put("MAIL_OTP", mailOtp==null?"":mailOtp);
		/*Map<String,String> content = emailContent(reqFrom, cusName, mobileNo,
				mailId, quoteNo, otp, pwd, amount, paymentMode, claimNo,
				product, policyNo, surveyourName, surveyorMobileNo,
				mailId, endtTypeName, opuserName);*/
		Map<String,String> content = emailContent(reqFrom, res);
		
		content.put("CUST_MAILID", mailId);
		content.put("BRO_MAILID", brokermail);
		content.put("BRANCH_CODE", StringUtils.isBlank(branchCode)?"01":branchCode);
		
		return content;
	}
	
	/**
	 *  This Method for Return SMS Content 
	 * */
	public String processSMS() {
		String cusName="";
		String mailId=""; 
		String amount="";
		String paymentMode="";
		String claimNo="";
		String content="";
		String product="";
		String paymentStatus="";
		String policyNo = "";
		String endtTypeName = "";
		String refNo = "";
		Map<Object, Object> rec=null;
		if(GET_QUOTE.equals(reqFrom)||BUY_POLICY.equals(reqFrom)||PAYMENT_SUCESS_CUST.equals(reqFrom)||SURVEYOR_ACCEPT.equals(reqFrom)||SURVEYOR_REJECT.equals(reqFrom) || ENDT_REQUEST_CUST.equals(reqFrom) || ENDT_REQUEST_OPUSER.equals(reqFrom) || ENDT_APPROVE_CUST.equals(reqFrom) || ENDT_DISAPPROVE_CUST.equals(reqFrom) /*||ONLINE_PAYMENT_STATUS_UNDERWRITER.equals(reqFrom)*/){
			rec=dao.getCustInfo(quoteNo);
			cusName=rec.get("INSURED_NAME")==null?"":rec.get("INSURED_NAME").toString().trim();
			mobileNo=rec.get("MOBILE")==null?"":rec.get("MOBILE").toString();
			mailId=rec.get("EMAIL")==null?"":rec.get("EMAIL").toString();
			paymentMode=rec.get("PAYMENT_MODE")==null?"":rec.get("PAYMENT_MODE").toString();
			policyNo = rec.get("POLICY_NO")==null?"":rec.get("POLICY_NO").toString();
			endtTypeName = rec.get("ENDT_TYPE_NAME")==null?"":rec.get("ENDT_TYPE_NAME").toString();
			product=rec.get("PRODUCT")==null?"":rec.get("PRODUCT").toString();
			paymentStatus=rec.get("RES_DECISION")==null?"":rec.get("RES_DECISION").toString();
		} else if(CLAIM_REG.equals(reqFrom)||CLAIM_PROCESS.equals(reqFrom)||CLAIM_UPDATE.equals(reqFrom)||CLAIM_CLOSED.equals(reqFrom)||CLAIM_REJECT.equals(reqFrom)){
			rec=dao.getClaimInfo( quoteNo);
			cusName=rec.get("INSURED_NAME")==null?"":rec.get("INSURED_NAME").toString().trim();
			mobileNo=rec.get("MOBILE")==null?"":rec.get("MOBILE").toString();
			claimNo=rec.get("CLAIM_NO")==null?"":rec.get("CLAIM_NO").toString();
		} else if(REG_SUCCES.equals(reqFrom)) {
			rec=dao.getUserInfo(userLoginId);
			mailId=rec.get("USER_MAIL")==null?"":rec.get("USER_MAIL").toString();
			mobileNo =rec.get("MOBILE")==null?"":rec.get("MOBILE").toString();
		} else if(PAYMENT_SUCESS_SURVEYOR.equals(reqFrom) || PAYMENT_SUCESS_SURVEYOR_RENEWAL.equals(reqFrom)){
			mobileNoList = dao.getAdminDetails("surveyor");
			List<Map<String,Object>> mobileNoList1 = dao.getAdminDetails("admin");
			mobileNoList.addAll(mobileNoList1);
			if(PAYMENT_SUCESS_SURVEYOR.equals(reqFrom)){
				rec=dao.getCustInfo(quoteNo);
				cusName=rec.get("INSURED_NAME")==null?"":rec.get("INSURED_NAME").toString().trim();
				mobileNo=rec.get("MOBILE")==null?"":rec.get("MOBILE").toString();
				mailId=rec.get("EMAIL")==null?"":rec.get("EMAIL").toString();
				paymentMode=rec.get("PAYMENT_MODE")==null?"":rec.get("PAYMENT_MODE").toString();
				policyNo = rec.get("POLICY_NO")==null?"":rec.get("POLICY_NO").toString();
				endtTypeName = rec.get("ENDT_TYPE_NAME")==null?"":rec.get("ENDT_TYPE_NAME").toString();
				product=rec.get("PRODUCT")==null?"":rec.get("PRODUCT").toString();
				paymentStatus=rec.get("RES_DECISION")==null?"":rec.get("RES_DECISION").toString();
			}
			if(PAYMENT_SUCESS_SURVEYOR_RENEWAL.equals(reqFrom)){
				List<Map <Object,Object>> res = dao.getRenewalCustInfo(quoteNo);
				rec=res.get(0);
				cusName = rec.get("INSURED_NAME")==null?"":rec.get("INSURED_NAME").toString().trim();
				mobileNo = rec.get("MOBILE")==null?"":rec.get("MOBILE").toString();
				mailId = rec.get("EMAIL")==null?"":rec.get("EMAIL").toString();
				paymentMode = rec.get("PAYMENT_MODE")==null?"":rec.get("PAYMENT_MODE").toString();
				amount = rec.get("OVERALL_PREMIUM")==null?"":rec.get("OVERALL_PREMIUM").toString();
				policyNo = rec.get("POLICY_NO")==null?"":rec.get("POLICY_NO").toString();
			}
			
		} else if(GET_ROADASSIST.equals(reqFrom)){
			rec=dao.getRoadAssistant(quoteNo);
			cusName=rec.get("CUSTOMER_NAME")==null?"":rec.get("CUSTOMER_NAME").toString().trim();
			mobileNo =rec.get("MOBILE_NO")==null?"":rec.get("MOBILE_NO").toString();
		}else if(BUY_POLICY_RENEWAL.equals(reqFrom)||PAYMENT_SUCESS_CUST_RENEWAL.equals(reqFrom)){
			List<Map <Object,Object>> res = dao.getRenewalCustInfo(quoteNo);
			rec=res.get(0);
			cusName = rec.get("INSURED_NAME")==null?"":rec.get("INSURED_NAME").toString().trim();
			mobileNo = rec.get("MOBILE")==null?"":rec.get("MOBILE").toString();
			mailId = rec.get("EMAIL")==null?"":rec.get("EMAIL").toString();
			paymentMode = rec.get("PAYMENT_MODE")==null?"":rec.get("PAYMENT_MODE").toString();
			amount = rec.get("OVERALL_PREMIUM")==null?"":rec.get("OVERALL_PREMIUM").toString();
			policyNo = rec.get("POLICY_NO")==null?"":rec.get("POLICY_NO").toString();
		}else if(BUY_POLICY_OPUSER.equals(reqFrom)){
			mobileNoList = dao.getAdminDetails("surveyor");
			List<Map<String,Object>> mobileNoList1 = dao.getAdminDetails("admin");
			mobileNoList.addAll(mobileNoList1);
			rec=dao.getCustInfo(quoteNo);
			cusName=rec.get("INSURED_NAME")==null?"":rec.get("INSURED_NAME").toString().trim();
			mobileNo=rec.get("MOBILE")==null?"":rec.get("MOBILE").toString();
			mailId=rec.get("EMAIL")==null?"":rec.get("EMAIL").toString();
			paymentMode=rec.get("PAYMENT_MODE")==null?"":rec.get("PAYMENT_MODE").toString();
			policyNo = rec.get("POLICY_NO")==null?"":rec.get("POLICY_NO").toString();
			endtTypeName = rec.get("ENDT_TYPE_NAME")==null?"":rec.get("ENDT_TYPE_NAME").toString();
			product=rec.get("PRODUCT")==null?"":rec.get("PRODUCT").toString();
			paymentStatus=rec.get("RES_DECISION")==null?"":rec.get("RES_DECISION").toString();
		}else if(BUY_POLICY_OPUSER_RENEWAL.equals(reqFrom)){
			mobileNoList = dao.getAdminDetails("surveyor");
			List<Map<String,Object>> mobileNoList1 = dao.getAdminDetails("admin");
			mobileNoList.addAll(mobileNoList1);
			List<Map <Object,Object>> res = dao.getRenewalCustInfo(quoteNo);
			rec=res.get(0);
			cusName = rec.get("INSURED_NAME")==null?"":rec.get("INSURED_NAME").toString().trim();
			mobileNo = rec.get("MOBILE")==null?"":rec.get("MOBILE").toString();
			mailId = rec.get("EMAIL")==null?"":rec.get("EMAIL").toString();
			paymentMode = rec.get("PAYMENT_MODE")==null?"":rec.get("PAYMENT_MODE").toString();
			amount = rec.get("OVERALL_PREMIUM")==null?"":rec.get("OVERALL_PREMIUM").toString();
			policyNo = rec.get("POLICY_NO")==null?"":rec.get("POLICY_NO").toString();
		}
		//content=smsContent(reqFrom, cusName, mobileNo, mailId, quoteNo, otp, pwd, amount, paymentMode, claimNo,product);
		
		Map<String,String> res = new HashMap();
		res.put("CUST_NAME", StringUtils.isBlank(cusName)?userName:cusName);
		res.put("MOBILE_NO", mobileNo==null?"":mobileNo);
		res.put("MAIL_ID", mailId==null?"":mailId);
		res.put("QUOTE_NO", quoteNo==null?"":quoteNo);
		res.put("REF_NO", quoteNo==null?"":quoteNo);
		res.put("CUST_OTP", otp==null?"":otp);
		res.put("CUST_PWD", pwd==null?"":pwd);
		res.put("PAY_AMOUNT", amount==null?"":amount);
		res.put("PAYMENT_MODE", paymentMode==null?"":paymentMode);
		res.put("CLAIM_NO", claimNo==null?"":claimNo);
		res.put("ENDT_TYPE_NAME", endtTypeName==null?"":endtTypeName);
		res.put("PRODUCT_NAME", product==null?"":product);
		res.put("PAYMENT_STATUS", paymentStatus==null?"":paymentStatus);
		res.put("POLICY_NO", policyNo==null?"":policyNo);
		res.put("OTP", otp==null?"":otp);
		res.put("OTP_EXPIRY", expiryDate==null?"":expiryDate);
		content=smsContent(reqFrom,res);
		return content;
	}
	
	private String smsContent(String reqFrom, Map<String, String> res) {
		String content="";
		String contentId="";
		if(GET_QUOTE.equals(reqFrom)){	//Quotation Stage – On Click of Get Quote Button
			contentId="1";
		} else if(GET_OTP.equals(reqFrom)) {//Registration With Quotation – On Click of Submit Button
			contentId="24";
		} else if(REG_SUCCES.equals(reqFrom)) {//Successful Registration message
			contentId="22";
		} else if(BUY_POLICY.equals(reqFrom)){//Exits customer clicks buy policy
			contentId="3";
		} else if(PAYMENT_SUCESS_CUST.equals(reqFrom)) {//When payment is received by Madison sms to customer 
			contentId="5";
		} else if(PAYMENT_SUCESS_SURVEYOR.equals(reqFrom)) {//When payment is received by Madison sms to surveyor
			contentId="25";
		} else if(SURVEYOR_ACCEPT.equals(reqFrom)){//When Surveyor Accecpet customer Req
			contentId="6";
		} else if(SURVEYOR_REJECT.equals(reqFrom)){//When Surveyor Reject customer Req
			contentId="9";
		} else if(CLAIM_REG.equals(reqFrom)){//On Claim Registration Stage
			contentId="12";
		} else if(CLAIM_PROCESS.equals(reqFrom)){//On Claim Status Update - PROCESSING
			contentId="14";
		} else if(CLAIM_UPDATE.equals(reqFrom)){//On Claims Status update - DISCHARGED
			contentId="16";
		} else if(CLAIM_CLOSED.equals(reqFrom)) {//On Claims Status update – CLOSED
			contentId="18";
		} else if(CLAIM_REJECT.equals(reqFrom)){	//On Claims Status update – REJECTED
			contentId="20";
		} else if(ENDT_REQUEST_CUST.equals(reqFrom)) { // On Endorsement Request
			contentId="26";
		} else if(ENDT_APPROVE_CUST.equals(reqFrom)) { // On Endorsement Approve
			contentId="28";
		} else if(ENDT_DISAPPROVE_CUST.equals(reqFrom)) { // On Endorsement Disapprove
			contentId="34";
		}else if(GET_ROADASSIST.equals(reqFrom)) { // On Endorsement Disapprove
			contentId="36";
		}else if(BUY_POLICY_RENEWAL.equals(reqFrom)){//Exits customer clicks buy policy
			contentId="39";
		} else if(PAYMENT_SUCESS_CUST_RENEWAL.equals(reqFrom)) {//When payment is received by Madison sms to customer 
			contentId="41";
		}else if(PAYMENT_SUCESS_SURVEYOR_RENEWAL.equals(reqFrom)) {//When payment is received by Madison sms to surveyor
			contentId="42";
		}else if (BUY_POLICY_OPUSER.equals(reqFrom)){
			contentId="4";
		}else if (BUY_POLICY_OPUSER_RENEWAL.equals(reqFrom)){
			contentId="40";
		}
		/*else if("ONLINE_PAYMENT_STATUS_UNDERWRITER".equals(reqFrom)) {
			contentId="32";
		}*/
		
		Map<String,Object> smsBody=dao.getSmsEmailContent(contentId);
		if(smsBody!=null && smsBody.size()>0){
			adminMobileNo = smsBody.get("SMS_TO")==null?"":smsBody.get("SMS_TO").toString();
			subject = smsBody.get("SMS_SUBJECT")==null?"":smsBody.get("SMS_SUBJECT").toString();
			content = smsBody.get("SMS_BODY")==null?"":smsBody.get("SMS_BODY").toString()+(smsBody.get("SMS_REGARDS")==null?"":smsBody.get("SMS_REGARDS").toString());
			content = content.replaceAll("\n", "");
		}
		for (Entry<String, String> entry : res.entrySet()) {
	         content = content.replace("{"+entry.getKey()+"}", res.get(entry.getKey())==null?"":res.get(entry.getKey()));
	    }
		return content;
	}
	private Map<String,String> emailContent(String reqFrom,Map<String, String> res) {
		Map<String,String> resultMap = new HashMap<String,String>();
		String contendId="";
		String filePath="";
	    StringBuffer content = new StringBuffer(10000);
		content.append("<!DOCTYPE HTML>");
		content.append("<HTML>");
		content.append("<HEAD>");
		content.append("</HEAD>");
		content.append("<BODY>");
		content.append("<div>");
		if(GET_QUOTE.equals(reqFrom)) {	//Quotation Stage – On Click of Get Quote Button
			contendId="1";
		}
		else if(GET_QUOTE_OPUSER.equals(reqFrom)) {	//Quotation Stage – On Click of Get Quote Button
			contendId="2";
		} else if(GET_OTP.equals(reqFrom)) {//Registration With Quotation – On Click of Submit Button
			contendId="24";
		} else if(REG_SUCCES.equals(reqFrom)) {//Successful Registration message
			contendId="22";
		} else if(REG_SUCCESS_OPUSER.equals(reqFrom)) { //Successful Registration message to Operational User
			contendId="23";
		} else if(BUY_POLICY.equals(reqFrom)){ //Exits customer clicks buy policy
			contendId="3";
		} else if(BUY_POLICY_OPUSER.equals(reqFrom)) { //Exits customer clicks buy policy To Operational User
			contendId="4";
		} else if(PAYMENT_SUCESS_CUST.equals(reqFrom)) {//When payment is received by Madison  sms to customer 
			contendId="5";
		} else if(PAYMENT_SUCESS_SURVEYOR.equals(reqFrom)) {//When payment is received by Madison  sms to surveyor
			contendId="25";
		} else if(ENDT_REQUEST_CUST.equals(reqFrom)) {
			contendId="26";
		} else if(ENDT_REQUEST_OPUSER.equals(reqFrom)) {
			contendId="27";
		} else if(ENDT_APPROVE_CUST.equals(reqFrom)) {
			contendId="28";
		} else if(ENDT_APPROVE_OPUSER.equals(reqFrom)) {
			contendId="29";
		} else if(CLAIM_INTIMATE_CUST.equals(reqFrom)) {
			contendId="30";
		} else if(CLAIM_INTIMATE_OPUSER.equals(reqFrom)) {
			contendId="31";
		} else if(SURVEYOR_ACCEPT.equals(reqFrom)){//When Surveyor Accecpet customer Req
			contendId="6";
		} else if(SURVEYOR_ACCEPT_SURVEYOR.equals(reqFrom)) {
			contendId="8";
		} else if(SURVEYOR_ACCEPT_OPUSER.equals(reqFrom)) {
			contendId="7";
		} else if(SURVEYOR_REJECT.equals(reqFrom)) {//When Surveyor Reject customer Req
			contendId="9";
		} else if(SURVEYOR_REJECT_SURVEYOR.equals(reqFrom)) { 
			contendId="10";
		} else if(SURVEYOR_REJECT_OPUSER.equals(reqFrom)) { 
			contendId="11";
		} else if(CLAIM_REG.equals(reqFrom)){//On Claim Registration Stage
			contendId="12";
		} else if(CLAIM_REG_OPUSER.equals(reqFrom)) {
			contendId="13";
		} else if(CLAIM_PROCESS.equals(reqFrom)) {//On Claim Status Update - PROCESSING
			contendId="14";
		} else if(CLAIM_PROCESS_OPUSER.equals(reqFrom)) {
			contendId="15";
		} else if(CLAIM_UPDATE.equals(reqFrom)){//On Claims Status update - DISCHARGED
			contendId="16";
		} else if(CLAIM_UPDATE_OPUSER.equals(reqFrom)) { 
			contendId="17";
		} else if(CLAIM_CLOSED.equals(reqFrom)) {//On Claims Status update – CLOSED
			contendId="18";
		} else if(CLAIM_CLOSED_OPUSER.equals(reqFrom)) {
			contendId="19";
		} else if(CLAIM_REJECT.equals(reqFrom)){	//On Claims Status update – REJECTED
			contendId="20";
		} else if(CLAIM_REJECT_OPUSER.equals(reqFrom)) {
			contendId="21";
		} else if("ONLINE_PAYMENT_STATUS_UNDERWRITER".equals(reqFrom)) {
			contendId="32";
		}else if(EMAIL_QUOTE.equals(reqFrom)) {
			contendId="33";
		} else if(ENDT_DISAPPROVE_CUST.equals(reqFrom)) {
			contendId="34";
		} else if(ENDT_DISAPPROVE_OPUSER.equals(reqFrom)) {
			contendId="35";
		}else if(GET_ROADASSIST.equals(reqFrom)) {
			contendId="36";
		} else if(GET_ROADASSIST_OPUSER.equals(reqFrom)) {
			if("HYBRID".equalsIgnoreCase(res.get("DEVICE"))){
			contendId="37";
			}
			else{
			contendId="38";
			}
		}else if(BUY_POLICY_RENEWAL.equals(reqFrom)){//Exits customer clicks buy policy
			contendId="39";
		} else if(PAYMENT_SUCESS_CUST_RENEWAL.equals(reqFrom)) {//When payment is received by Madison sms to customer 
			contendId="41";
		}else if(BUY_POLICY_OPUSER_RENEWAL.equals(reqFrom)) { //Exits customer clicks buy policy To Operational User
			contendId="40";
		}else if(PAYMENT_SUCESS_SURVEYOR_RENEWAL.equals(reqFrom)) {//When payment is received by Madison  sms to surveyor
			contendId="42";
		}
		String body="";
		Map<String,Object> smsBody=dao.getSmsEmailContent(contendId);
		if(smsBody!=null && smsBody.size()>0){
			adminEmailId = smsBody.get("EMAIL_TO")==null?"":smsBody.get("EMAIL_TO").toString();
			adminCCEmailId = smsBody.get("EMAIL_CC")==null?"":smsBody.get("EMAIL_CC").toString();
			subject = smsBody.get("MAIL_SUBJECT")==null?"":smsBody.get("MAIL_SUBJECT").toString();
			body = smsBody.get("MAIL_BODY")==null?"":smsBody.get("MAIL_BODY").toString()+"<br/><br/>"+(smsBody.get("MAIL_REGARDS")==null?"":smsBody.get("MAIL_REGARDS").toString());
		}
		for (Entry<String, String> entry : res.entrySet()) {
			subject = subject.replace("{"+entry.getKey()+"}", res.get(entry.getKey())==null?"":res.get(entry.getKey()));
			body = body.replace("{"+entry.getKey()+"}", res.get(entry.getKey())==null?"":res.get(entry.getKey()));
	    }
		content.append(body);
		content.append("</div>");
		content.append("</BODY>");
		content.append("</HTML>");
		
		resultMap.put("EMAIL_SUBJECT", subject);
		resultMap.put("EMAIL_CONTENT", content.toString());
		return resultMap;
	}
	/**
	 *  This Method for Manipulate SMS Content 
	 * *//*
	private String smsContent(String reqFrom,String  cusName ,String mobileNo,String mailId,String  quoteNo ,String otp,String pwd, String amount,String paymentMode,String   claimNo,String product)
	{
		String content="";
		
		
		
		if(GET_QUOTE.equals(reqFrom)){	//Quotation Stage – On Click of Get Quote Button
			content="Dear "+  cusName  +", thank you for your interest in insuring with us. " 
					+  "Your "+product+" quotation number is " +  quoteNo  + ". Keep it for your reference." 
					+ " Customer Care will be in touch shortly. Madison General, It's worth it!";
		} else if(GET_OTP.equals(reqFrom)) {//Registration With Quotation – On Click of Submit Button
			content="Your OTP is  . " + otp 
					+ "Please enter this in the Madison General portal to verify your account." 
					+ "Madison General, It's worth it!";
		} else if(REG_SUCCES.equals(reqFrom)) {//Successful Registration message
			content="Congratulations! You have successfully registered on the Madison General Portal." 
					+ " Your User ID is " + mailId + " and Password is "+ pwd 
					+ ". Use this to log into your account. Take note of your password and keep it safe." 
					+ " Madison General, It's worth it!";
		} else if(BUY_POLICY.equals(reqFrom)){//Exits customer clicks buy policy
			content="Dear " + cusName + ", you have chosen to pay "+ amount + " for quotation no. "
					+  quoteNo  +" using "+ paymentMode 
					+ ". Please proceed to complete payment process for policy to be approved. Regards, " 
					+ "Madison General";
		} else if(PAYMENT_SUCESS_CUST.equals(reqFrom)) {//When payment is received by Madison  sms to customer 
			content="Dear "+ cusName +", payment for quote no " + quoteNo  + " has been received. " 
					+ "A Madison General Inspector will visit you in the next 48 hours to inspect your property " 
					+ "and deliver your policy documents. Thank you for Insuring with us. It's worth it!";
		} else if(PAYMENT_SUCESS_SURVEYOR.equals(reqFrom)) {//When payment is received by Madison  sms to surveyor
			content="Dear Surveyor, New Inspection Request for quote no  " +  quoteNo  
					+ ". Please arrange to visit customer within 48hrs. Regards, B2C Portal Admin";
		} else if(SURVEYOR_ACCEPT.equals(reqFrom)){//When Surveyor Accecpet customer Req
			content="Dear "+ cusName + ", your property has been successfully inspected and " 
					+ "policy documents have been delivered. Thank you for choosing Madison General Insurance. " 
					+ "It's worth it!";
		} else if(SURVEYOR_REJECT.equals(reqFrom)){//When Surveyor Reject customer Req
			content="Dear "+ cusName +", we regret to inform you that we will not cover your property after an inspection. " 
					+ "Please visit any Madison General branch to have your payment refunded.";
		} else if(CLAIM_REG.equals(reqFrom)){//On Claim Registration Stage
			content="Dear "+ cusName +", your claim has been successfully registered. " 
					+ "Claim no  "+  claimNo +". Madison General, It's worth it!";
		} else if(CLAIM_PROCESS.equals(reqFrom)){//On Claim Status Update - PROCESSING
			content="Dear "+ cusName +", your claim "+   claimNo  +" is being processed. "
					+ "Madison General, It's worth it!";
		} else if(CLAIM_UPDATE.equals(reqFrom)){//On Claims Status update - DISCHARGED
			content="Dear "+  cusName + ", your claim "+  claimNo +"  has been discharged. " 
					+ "Please pass through your nearest Madison outlet to sign the discharge form." 
					+ " Madison General, It's worth it!";
		} else if(CLAIM_CLOSED.equals(reqFrom)) {//On Claims Status update – CLOSED
			content="Dear "+ cusName +", your claim "+  claimNo +" has been successfully settled. " 
					+ "Thank you for your cooperation and patience. Madison General, It's worth it!";
		} else if(CLAIM_REJECT.equals(reqFrom)){	//On Claims Status update – REJECTED
			content="Dear "+ cusName +", your claim "+  claimNo +" has been successfully settled. " 
					+ "Thank you for your cooperation and patience. Madison General, It's worth it!";
		}
		return content;
	}
	private Map<String,String> emailContent(String reqFrom,String cusName,String mobileNo,
			String mailId,String quoteNo,String otp,String pwd, String amount,
			String paymentMode,String claimNo,String product, String policyNo,
			String surveyorName, String surveyorMobileNo, String surveyorMailId, String endtTypeName,
			String opuserName) {
		Map<String,String> resultMap = new HashMap<String,String>();
		StringBuffer content = new StringBuffer(10000);
		
		content.append("<!DOCTYPE HTML>");
		content.append("<HTML>");
		content.append("<HEAD>");
		content.append("</HEAD>");
		content.append("<BODY>");
		content.append("<div>");
		
		if(GET_QUOTE_OPUSER.equals(reqFrom)) {	//Quotation Stage – On Click of Get Quote Button
			subject = "New Quotation Alert";
			content.append("<div>");
			content.append("A new quotation with quote number " + quoteNo + " under " + product
					+ " has been created by " + cusName + " ," + mobileNo + ", " + mailId + ". "
					+ "Please follow up with the customer.");
			content.append("</div>");
			content.append("<div>Regards,</div>");
			content.append("<div>B2C Admin</div>");
		} else if(GET_OTP.equals(reqFrom)) {//Registration With Quotation – On Click of Submit Button
			subject = "ACCOUNT VERIFICATION";
			content.append("<div>Dear " + userName + ",</div>");
			content.append("<div>");
			content.append("Your OTP is " + otp + ". Please enter this in the Madison General Insurance portal to verify your account and proceed to enjoy the full benefits of being a registered customer");
			content.append("</div>");
			content.append("<br/>");
			content.append("<div>Madison General, It's worth it!</div>");
			content.append("<div>Regards,</div>");
			content.append("<div>Madison General Insurance</div>");
			content.append("<div>online@madison.co.zm, 378700-5</div>");
		} else if(REG_SUCCES.equals(reqFrom)) {//Successful Registration message
			subject = "MADISON GENERAL PORTAL REGISTRATION";
			content.append("<div>Dear " + cusName + ",</div>");
			content.append("<div>");
			content.append("Congratulations! You have successfully registered on the Madison General Portal. "
					+ "Your User ID is " + mailId +" and Password is " + pwd + ". "
					+ "Use this to log into your account. Take note of your password and keep it safe. "
					+ "You can access the portal by following the URL: https://insure.madison.co.zm");
			content.append("</div>");
			content.append("<br/>");
			content.append("<div>Madison General, It's worth it!</div>");
			content.append("<div>Regards,</div>");
			content.append("<div>Madison General Insurance</div>");
			content.append("<div>online@madison.co.zm, 378700-5</div>");
		} else if(REG_SUCCESS_OPUSER.equals(reqFrom)) { //Successful Registration message to Operational User
			subject = "New User Alert";
			content.append("<div>Dear Operator,</div>");
			content.append("<div>A new user has been registered on the portal. Particulars of the user are as follows:</div>");
			content.append("<div>Customer Name: " + cusName + "</div>");
			content.append("<div>Phone Number: " + mobileNo + "</div>");
			content.append("<div>Email Address: " + mailId + "</div>");
			content.append("<div>Regards,</div>");
			content.append("<div>B2C Portal Admin</div>");
		} else if(BUY_POLICY.equals(reqFrom)){ //Exits customer clicks buy policy
			subject = "Payment Confirmation - " + quoteNo;
			content.append("<div>Dear " + cusName + ",</div>");
			content.append("<div>");
			content.append("You have chosen to pay " + amount + " for quote no. " + quoteNo + " using "+ paymentMode +". "
					+ "Please proceed to complete payment process for policy to be approved");
			content.append("</div>");
			content.append("<div><b>Note. Please provide the quotation number when making all your payments</b></div>");
			content.append("<div>Madison General Insurance</div>");
			content.append("<div>It's worth it!</div>");
			content.append("<div>online@madison.co.zm, 378700-5</div>");
		} else if(BUY_POLICY_OPUSER.equals(reqFrom)) { //Exits customer clicks buy policy To Operational User
			subject = "New Payment Alert - " + quoteNo;
			content.append("<div>Dear Operator,</div>");
			content.append("<div>");
			content.append("A new payment has been made by " + cusName + ". "
					+ "Customer phone number is " + mobileNo + " and Email is " + mailId + " for quote no. "
					+ quoteNo + " using " + paymentMode + ". "
					+ "Please proceed to assist the customer with completing the payment process.");
			content.append("</div>");
			content.append("<div>Regards,</div>");
			content.append("<div>B2C Portal Admin</div>");			
		} else if(PAYMENT_SUCESS_CUST.equals(reqFrom)) {//When payment is received by Madison  sms to customer 
			subject = "Property Inspection Request - " + quoteNo;
			content.append("<div>Dear " + cusName + ",</div>");
			content.append("<div>");
			content.append("Payment for quote no. " + quoteNo + " has been received. "
					+ "A Madison General Inspector will visit you in the next 48 hours to inspect your property and deliver the policy documents. "
					+ "Thank you for Insuring with us. It's worth it!");
			content.append("</div>");
			content.append("<br/>");
			content.append("<div>");
			content.append("Please inform Madison General immediately if your property will not be available for inspection in the next 48 hours and advise a suitable time.");
			content.append("</div>");
			content.append("<br/>");
			content.append("<div>");
			content.append("Disclaimer: Cover will only be applicable once inspection of vehicle/property is carried out by a Madison General Insurance surveyor and policy documents are delivered.");
			content.append("</div>");
			content.append("<br/><br/>");
			content.append("<div>Regards,</div>");
			content.append("<div>Madison General Insurance</div>");
			content.append("<div>online@madison.co.zm, 378700-5</div>");
			
		} else if(PAYMENT_SUCESS_SURVEYOR.equals(reqFrom)) {//When payment is received by Madison  sms to surveyor
			subject = "Inspection Request - " + quoteNo;
			content.append("<div>Dear Surveyor,</div>");
			content.append("<div>");
			content.append("A New Inspection Request for quote no. "
					+ quoteNo + ",Customer name.  " + cusName + ", "
					+ "Phone No. " + mobileNo + ", Email address. "
					+ mailId + " has been generated. "
					+ "Please arrange to visit the customer within 48hrs.");
			content.append("</div>");
			content.append("<div>Regards,</div>");
			content.append("<div>B2C Portal Admin</div>");
			content.append("<div>online@madison.co.zm, 378700-5</div>");
		} else if(ENDT_REQUEST_CUST.equals(reqFrom)) {
			subject = "Endorsement Request submitted";
			content.append("<div>Dear " + cusName + ",</div>");
			content.append("<div>");
			content.append("You have requested for an endorsement " + endtTypeName + " on policy no. " + policyNo + ". "
					+ "You can monitor the status of the endorsement in the portal under Open Endorsements.");
			content.append("</div>");
			content.append("<div>Regards,</div>");
			content.append("<div>Madison General Insurance</div>");
			content.append("<div>It's worth it!</div>");
			content.append("<div>online@madison.co.zm, 378700-5</div>");
			
		} else if(ENDT_REQUEST_OPUSER.equals(reqFrom)) {
			subject = "Endorsement Request submitted";
			content.append("<div>Dear Operator,</div>");
			content.append("<div>");
			content.append("A new endorsement request has been submitted on policy no. " + policyNo + ". "
					+ "Please proceed to process in the core system.");
			content.append("</div>");
			content.append("<div>Regards,</div>");
			content.append("<div>Madison General Insurance</div>");
			content.append("<div>It's worth it!</div>");
		} else if(ENDT_APPROVE_CUST.equals(reqFrom)) {
			subject = "Endorsement Request approved";
			content.append("<div>Dear " + cusName + ",</div>");
			content.append("<div>");
			content.append("Your endorsement " + endtTypeName + " on policy no. " + policyNo + ". "
					+ "Has been approved. "
					+ "Log into the portal to view the policies under your portfolio. "
					+ "Thank you for choosing Madison General Insurance.It's worth it!");
			content.append("</div>");
			content.append("<div>Regards,</div>");
			content.append("<div>Madison General Insurance</div>");
			content.append("<div>It's worth it!</div>");
			content.append("<div>online@madison.co.zm, 378700-5</div>");
		} else if(ENDT_APPROVE_OPUSER.equals(reqFrom)) {
			subject = "Endorsement Request approved";
			content.append("<div>Dear " + opuserName + ",</div>");
			content.append("<div>");
			content.append("You have approved Endorsement " + endtTypeName + " on policy " + policyNo + ".");
			content.append("</div>");
			content.append("<div>Regards,</div>");
			content.append("<div>B2C Portal Admin</div>");
		} else if(CLAIM_INTIMATE_CUST.equals(reqFrom)) {
			subject = "Claim Intimation Notice - " + policyNo;
			content.append("<div>Dear " + cusName + ",</div>");
			content.append("<div>");
			content.append("Your claim notification on policy no. " + policyNo + ". hasbeen acknowledged. You can monitor the status of your claim in the portal under Open Claims.");
			content.append("</div>");
			content.append("<div>");
			content.append("Madison General Insurance claims handler will be in touch to assist you to complete the claims process.");
			content.append("</div>");
			content.append("<div>Regards,</div>");
			content.append("<div>Madison General Insurance</div>");
			content.append("<div>It's worth it!</div>");
			content.append("<div>online@madison.co.zm, 378700-5</div>");
		} else if(CLAIM_INTIMATE_OPUSER.equals(reqFrom)) {
			subject = "Claim Intimation Notice";
			content.append("<div>Dear Operator,</div>");
			content.append("<div>");
			content.append("A new claim on. " + policyNo + " has been registered. Please proceed to process in the core system.");
			content.append("</div>");
			content.append("<div>Regards,</div>");
			content.append("<div>Madison General Insurance</div>");
			content.append("<div>It's worth it!</div>");
		} else if(SURVEYOR_ACCEPT.equals(reqFrom)){//When Surveyor Accecpet customer Req
			subject = "Cover confirmation - " + quoteNo;
			content.append("<div>");
			content.append("Dear " + cusName + " Your property has been successfully inspected and policy documents have been delivered. "
					+ "This email is confirmation that cover of your property has since commenced.");
			content.append("</div>");
			content.append("<div>");
			content.append("You can access all your policy documents and manage your policies portfolio on the Madison Portal.");
			content.append("</div>");
			content.append("<div>Thank you for choosing Madison General Insurance. It's worth it!</div>");
			content.append("<div>Regards,</div>");
			content.append("<div>Madison General Insurance</div>");
			content.append("<div>online@madison.co.zm, 378700-5</div>");
		} else if(SURVEYOR_ACCEPT_SURVEYOR.equals(reqFrom)) {
			subject = "Policy Confirmation - " + quoteNo;
			content.append("<div>");
			content.append("Dear " + surveyorName + " you have successfully approved quote no. " + quoteNo + ".");
			content.append("</div>");
			content.append("<div>Regards,</div>");
			content.append("<div>B2C Portal Admin</div>");
			content.append("<div>online@madison.co.zm, 378700-5</div>");
		} else if(SURVEYOR_ACCEPT_OPUSER.equals(reqFrom)) {
			subject = "Policy Confirmation - " + quoteNo;
			content.append("<div>Dear Operator,</div>");
			content.append("<div>");
			content.append("Quote no. " + quoteNo + " has been successfully approved by surveyor " + surveyorName + ". Please proceed to check, validate and approve.");
			content.append("</div>");
			content.append("<div>Regards,</div>");
			content.append("<div>B2C Portal Admin</div>");
		} else if(SURVEYOR_REJECT.equals(reqFrom)) {//When Surveyor Reject customer Req
			subject = "Cover Request Declined - " + quoteNo;
			content.append("<div>");
			content.append("Dear " + cusName + ", "
					+ "We regret to inform you that your request for cover on property stated on quote " + quoteNo + " has been declined. "
					+ "Kindly visit any Madison General branch to discuss any other cover options that can be taken out for your property or to claim a refund.");
			content.append("</div>");
			content.append("<div>Thank you for choosing Madison General Insurance. It's worth it!</div>");
			content.append("<div>Regards,</div>");
			content.append("<div>Madison General Insurance</div>");
			content.append("<div>online@madison.co.zm, 378700-5</div>");
		} else if(SURVEYOR_REJECT_SURVEYOR.equals(reqFrom)) { 
			subject = "Policy Declined - " + quoteNo;
			content.append("<div>Dear " + surveyorName + ", </div>");
			content.append("<div>");
			content.append("You have declined request for cover on quote no. " + quoteNo + ".");
			content.append("</div>");
			content.append("<div>Regards,</div>");
			content.append("<div>B2C Portal Admin</div>");
			content.append("<div>online@madison.co.zm, 378700-5</div>");
		} else if(SURVEYOR_REJECT_OPUSER.equals(reqFrom)) { 
			subject = "Policy Declined - " + quoteNo;
			content.append("<div>Dear Operator,</div>");
			content.append("<div>");
			content.append("Quote no. " + quoteNo + " has been declined by surveyor " + surveyorName + ", " + surveyorMobileNo + ", " + surveyorMailId + ". "
					+ "Please proceed to reject the quote on the portal and issue a cancellation endorsement in core system.");
			content.append("</div>");
			content.append("<div>Regards,</div>");
			content.append("<div>B2C Portal Admin</div>");
			content.append("<div>online@madison.co.zm, 378700-5</div>");
		} else if(CLAIM_REG.equals(reqFrom)){//On Claim Registration Stage
			subject = "Claim Registered";
			content.append("<div>Dear " + cusName + ",</div>");
			content.append("<div>");
			content.append("Your claim on policy no. " + policyNo + ". has been successfully registered. "
					+ "You can monitor the status of your claim in the portal under Open Claims.");
			content.append("</div>");
			content.append("<div>");
			content.append("Madison General Insurance claims handler will be in touch to assist you to complete the claims process.");
			content.append("</div>");
			content.append("<div>Regards,</div>");
			content.append("<div>Madison General Insurance</div>");
			content.append("<div>It's worth it!</div>");
			content.append("<div>online@madison.co.zm, 378700-5</div>");
		} else if(CLAIM_REG_OPUSER.equals(reqFrom)) {
			subject = "Claim Registration Notice";
			content.append("<div>Dear Operator,</div>");
			content.append("<div>");
			content.append("A new claim " + claimNo + ", on. " + policyNo + " has been registered. Please proceed to process in the core system.");
			content.append("</div>");
			content.append("<div>Regards,</div>");
			content.append("<div>Madison General Insurance</div>");
			content.append("<div>It's worth it!</div>");
		} else if(CLAIM_PROCESS.equals(reqFrom)) {//On Claim Status Update - PROCESSING
			subject = "When operational user updates claim status to PROCESSING";
			content.append("<div>Dear " + cusName + ",</div>");
			content.append("<div>");
			content.append("Your claim " + claimNo + " on policy no. " + policyNo + " is being processed.");
			content.append("</div>");
			content.append("<div>You can monitor the status of your claim in the portal under Open Claims.</div>");
			content.append("<div>Madison General Insurance claims handler will be in touch to assist you to complete the claims process.</div>");
			content.append("<div>Regards,</div>");
			content.append("<div>Madison General Insurance</div>");
			content.append("<div>It's worth it!</div>");
			content.append("<div>online@madison.co.zm, 378700-5</div>");
			
		} else if(CLAIM_PROCESS_OPUSER.equals(reqFrom)) {
			subject = "Claim status Update";
			content.append("<div>Dear Operator,</div>");
			content.append("<div>");
			content.append("Claim number " + claimNo + ", is being processed.");
			content.append("</div>");
			content.append("<div>Regards,</div>");
			content.append("<div>Madison General Insurance</div>");
			content.append("<div>It's worth it!</div>");
			
		} else if(CLAIM_UPDATE.equals(reqFrom)){//On Claims Status update - DISCHARGED
			subject = "CLAIM DISCHARGE NOTIFICATION";
			content.append("<div>Dear " + cusName + ",</div>");
			content.append("<div>");
			content.append("Your claim " + claimNo + " on policy no. " + policyNo + " is been discharged.");
			content.append("</div>");
			content.append("<div>Please pass through your nearest Madison outlet to sign the discharge form.</div>");
			content.append("<div>Regards,</div>");
			content.append("<div>Madison General Insurance</div>");
			content.append("<div>It's worth it!</div>");
			content.append("<div>online@madison.co.zm, 378700-5</div>");
		} else if(CLAIM_UPDATE_OPUSER.equals(reqFrom)) { 
			subject = "Claim status Update";
			content.append("<div>Dear Operator,</div>");
			content.append("<div>");
			content.append("Claim number " + claimNo + ", is being processed.");
			content.append("</div>");
			content.append("<div>Regards,</div>");
			content.append("<div>Madison General Insurance</div>");
			content.append("<div>It's worth it!</div>");			
		} else if(CLAIM_CLOSED.equals(reqFrom)) {//On Claims Status update – CLOSED
			subject = "CLAIM SETTLEMENT NOTIFICATION";
			content.append("<div>Dear " + cusName + ",</div>");
			content.append("<div>");
			content.append("Your claim " + claimNo + " on policy no. " + policyNo + " has been successfully settled.");
			content.append("</div>");
			content.append("<div>Thank you for your cooperation and patience during the claim processing.</div>");
			content.append("<div>Regards,</div>");
			content.append("<div>Madison General Insurance</div>");
			content.append("<div>It's worth it!</div>");
			content.append("<div>online@madison.co.zm, 378700-5</div>");
		} else if(CLAIM_CLOSED_OPUSER.equals(reqFrom)) {
			subject = "CLAIM SETTLEMENT NOTIFICATION";
			content.append("<div>Dear Operator,</div>");
			content.append("<div>");
			content.append("Claim number {CLAIM_NUMBER} on policy no. " + policyNo + " has been successfully settled.");
			content.append("</div>");
			content.append("<div>Regards,</div>");
			content.append("<div>Madison General Insurance</div>");
			content.append("<div>It's worth it!</div>");
			
		} else if(CLAIM_REJECT.equals(reqFrom)){	//On Claims Status update – REJECTED
			subject = "CLAIM SETTLEMENT NOTIFICATION";
			content.append("<div>Dear " + cusName + ",</div>");
			content.append("<div>");
			content.append("Your claim " + claimNo + " on policy no. " + policyNo + " has been rejected due to some irregularities.");
			content.append("</div>");
			content.append("<div>Please contact Madison General to rectify.</div>");
			content.append("<div>Regards,</div>");
			content.append("<div>Madison General Insurance</div>");
			content.append("<div>It's worth it!</div>");
			content.append("<div>online@madison.co.zm, 378700-5</div>");
		} else if(CLAIM_REJECT_OPUSER.equals(reqFrom)) {
			subject = "CLAIM REJECTION NOTIFICATION";
			content.append("<div>Dear Operator,</div>");
			content.append("<div>");
			content.append("Claim number " + claimNo + " on policy no. " + policyNo + " has been rejected.");
			content.append("</div>");
			content.append("<div>Regards,</div>");
			content.append("<div>Madison General Insurance</div>");
			content.append("<div>It's worth it!</div>");
		}
		
		content.append("</div>");
		content.append("</BODY>");
		content.append("</HTML>");
		
		resultMap.put("EMAIL_SUBJECT", subject);
		resultMap.put("EMAIL_CONTENT", content.toString());
		return resultMap;
	}*/
	
	/*
	 * This Mehtod For Sending SMS
	 */
	
	/*private String getStringFromInputStream(InputStream is) {
		BufferedReader br = null;
		StringBuilder sb = new StringBuilder();
		String line;
		try {
			br = new BufferedReader(new InputStreamReader(is));
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return sb.toString();
	}*/
}	


