package com.maan.quickRenewal;
	
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.maan.adminnew.common.CommonService;
import com.maan.common.LogManager;
import com.maan.common.StringHelper;
import com.maan.common.Validation;
import com.maan.common.dao.CommonDAO;
import com.maan.common.login.CommonBean;
import com.maan.common.otp.OTPGenerator;
import com.maan.common.sms.SmsEmailUtil;
import com.maan.payment.airtel.AirtelService;
import com.maan.payment.airtel.model.ReqToPayIpModelAirtel;
import com.maan.payment.airtel.model.Subscriber;
import com.maan.payment.airtel.model.Transaction;
import com.maan.payment.mtn.MtnService;
import com.maan.payment.mtn.model.Payer;
import com.maan.payment.mtn.model.ReqToPayIpModel;
import com.maan.quickRenewal.bean.quickRenewalBean;
import com.maan.quickRenewal.service.quickRenewalService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import nl.captcha.Captcha;

public class quickRenewalAction extends ActionSupport implements ModelDriven<quickRenewalBean>{

	private static final long serialVersionUID = 1L;
	quickRenewalBean bean= new quickRenewalBean();
	quickRenewalService service= new quickRenewalService();
	Map<String, Object> session=ActionContext.getContext().getSession();
	private com.maan.common.dao.CommonDAO commonDAO = new com.maan.common.dao.CommonDAO();
	private CommonBean cdao = new CommonBean();
	Validation validations=new Validation();
	private static final String applicationPath = CommonService.getApplicationPath();
	private static final String QR_CODE_LOCATION = applicationPath + "QRImages/";
	
	private File upload;
	private String uploadFileName;
	private String uploadContentType;
	private InputStream inputStream;

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}
	
	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public String getUploadContentType() {
		return uploadContentType;
	}

	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}
	
	public quickRenewalBean getModel() {
		bean.setIsRenewal("Y");
		return bean;
	}

	//entry 1st page
	public String page(){
		LogManager.info("Enter into Quick Renewal Page()");
		bean.setQrMode("qrRead");
		session.put("product_id", "65");
		String set = validateUserId((String)session.get("user"));
		bean.setMobileNo(set);
		return "quickPage";
	}

	public List<Object> getModeOfPaymentList() {
		//return commonDAO.getOptionsList("modeOfPayment","65", getParams("modeOfPayment"));
		List<Object> list = new ArrayList<Object>();
		List<Object> list1 = commonDAO.getOptionsList("modeOfPayment","65", getParams("modeOfPayment"));
		List<Object> list2 = commonDAO.getUserBasedModeOfPayment("USER");
		try{
			if(list1!=null && list1.size()>0){
				list.addAll(list1);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		try{
			if(list2!=null && list2.size()>0){
				list.addAll(list2);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
	public List<Object> getBankNamelist(){
		return commonDAO.getOptionsList("BankList", "65", getParams("BankList"));
	}
	
	private Object[] getParams(String type) {
		Object[] objects = null;
			objects=new String[]{"","65","01","","","","",""};
		return objects;
	}

//	getRenewal 2nd page
	public String getRenewal(){
		LogManager.info("Enter into Quick Renewal getRenewal()");
		String forward ="quickPage";
		List<Map<String, Object>> result = null;
		try{
			validation();
			if(!hasActionErrors()){
				result=service.getPolicyDetails(bean);
				if(result.size()>0){
					int otp=new OTPGenerator().getOTP();
					int mailOtp=new OTPGenerator().getOTP();
					bean.setOtpId(new OTPGenerator().insertOTP(new String[]{bean.getOtpId(),"QUICK_RENEWAL",
							Integer.toString(otp),bean.getMobileNo(),bean.getEmail(),"","N",Integer.toString(mailOtp)}));
					new SmsEmailUtil("GET_OTP",Integer.toString(otp),bean.getMobileNo(),new OTPGenerator().getOtpExpiry(bean.getOtpId()),Integer.toString(mailOtp),bean.getEmail()).send();
					bean.setOtpStatus("N");
					bean.setOtp("");
					bean.setMailOtp("");
					bean.setOtpMessage("OTP has been sent to the mobile number ("+bean.getMobileNo()+" )");
					bean.setRenewalList(service.getPolicyDetails(bean));
					forward="otppage";
					}
				else{
					addActionError("There is no Renewal available for given details");
				}
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return forward;
	}

	//3rd page
	public String verifyOtp(){
		LogManager.info("Enter into Quick Renewal verifyOtp()");
		String forward ="otppage";
		bean.setRenewalList(service.getRenewList(bean));
		if(!"back".equalsIgnoreCase(bean.getMode())){
			int count =service.getValidateOtpCount(bean);
			if(count>0){
				forward="vehicleRenewalInfo";
				bean.setTempMobileNo(bean.getMobileNo());
				bean.setTempEmailId(bean.getEmail());
				bean.setTempPassportNo(bean.getPassportNo());
				bean.setMyTotal("0.00");
				bean.setPremTax("0.00");
			}
			else {
				addActionError("Invalid OTP");
			}
			if(count>0){
				int expiry=service.getValidateOtpExpiry(bean);
				if(!(expiry>0)){
					addActionError("OTP Expired");
					forward="otppage";
				}
				else{
					bean.setRenewalList(service.getRenewList(bean));
				    bean.setPaidList(service.getpaidDetails(bean));
				    bean.setPaymentList(service.getPaymentList(bean));
				}
			}
		}
		else{
			service.getCheckBoxValue(bean);
			bean.setRenewalList(service.getRenewList(bean));
		    bean.setPaidList(service.getpaidDetails(bean));
		    bean.setPaymentList(service.getPaymentList(bean));
			forward="vehicleRenewalInfo";
			//bean.setMyTotal("0.0");
		}
		return forward;
	}

	public String renewPolicy() {
		List<Map<String, Object>> result = null;
		try {
			result=service.getPolicyDetails(bean);
			if(result.size()>0){
				bean.setRenewalList(service.getRenewList(bean));
			    bean.setPaidList(service.getpaidDetails(bean));
			    bean.setPaymentList(service.getPaymentList(bean));
			}else {
				addActionError("There is no Renewal available for given details");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "vehicleRenewalInfo";
		
	}
	//vehicle detail page via portfolio
	public String vehDetail(){
		LogManager.info("Enter into Quick Renewal vehDetail()");
		if(!"back".equalsIgnoreCase(bean.getMode())){
			String mblNo=(String) session.get("user");
			bean.setMobileNo(mblNo);
		}
		service.getPolicyDetails(bean);
		if(StringUtils.isBlank(bean.getTempEmailId())){
			bean.setTempMobileNo(bean.getMobileNo());
			bean.setTempEmailId(bean.getEmail());
			bean.setTempPassportNo(bean.getPassportNo());
		}
		bean.setMyTotal("0.00");
		bean.setPremTax("0.00");
		bean.setRenewalList(service.getRenewList(bean));
	    bean.setPaidList(service.getpaidDetails(bean));
	    bean.setPaymentList(service.getPaymentList(bean));
		return "vehicleRenewalInfo";
	}
	
	//payment confirmation page
	public String payment(){
		LogManager.info("Enter into Quick Renewal payment()");
		String forward="vehicleRenewalInfo";
		service.checkbox(bean);
		String veh = StringUtils.chop(bean.getVehicleList());
		vehicleValidation(veh);
		if(!hasActionErrors()){
			
			if(StringUtils.isNotBlank(bean.getRenewRefNo())){
				service.deletePrevRecord(bean);
			}
			else{
				service.checkForQuote(bean);
			}
			bean.setVehicles(veh);
			service.getTotalPrem(bean);
			service.getPolicyPayment(bean);
			forward="paymentRenewal";
		}
		else{
			bean.setRenewalList(service.getRenewList(bean));
		    bean.setPaidList(service.getpaidDetails(bean));
		    bean.setPaymentList(service.getPaymentList(bean));
			bean.getVehicleSelect();
		}
		return forward;
	}
	
	public int paymentDetailInsert(String modeOfPay){
		LogManager.info("Enter into paymentDetailInsert()");
		int res=0;
		try {
			if("1".equalsIgnoreCase(modeOfPay)||"2".equalsIgnoreCase(modeOfPay)){
				res=service.getPaymentInsertCashCheque(bean);
				if(res==1){
					new SmsEmailUtil(SmsEmailUtil.PAYMENT_SUCESS_CUST_RENEWAL,bean.getRenewRefNo()).send();
					new SmsEmailUtil(SmsEmailUtil.PAYMENT_SUCESS_SURVEYOR_RENEWAL,bean.getRenewRefNo()).send();
				}
			}else{
				res=service.getPaymentInsert(bean);
				if(res==1){
					new SmsEmailUtil(SmsEmailUtil.BUY_POLICY_RENEWAL,bean.getRenewRefNo()).send();
					new SmsEmailUtil(SmsEmailUtil.BUY_POLICY_OPUSER_RENEWAL,bean.getRenewRefNo()).send();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
			
		return res;
	}
	
	//payment page
	public String genPayment(){
		LogManager.info("Enter into Quick Renewal genPayment()");
		if(!StringUtils.isBlank(bean.getModeOfPayment())){
			try {
				if("6".equalsIgnoreCase(bean.getModeOfPayment())){
					//int stat=service.getPaymentInsert(bean);
					int stat=paymentDetailInsert(bean.getModeOfPayment());
					if(stat==1){
						//new SmsEmailUtil(SmsEmailUtil.BUY_POLICY_RENEWAL,bean.getRenewRefNo()).send();
						//new SmsEmailUtil(SmsEmailUtil.BUY_POLICY_OPUSER_RENEWAL,bean.getRenewRefNo()).send();
						return "onlinePaymentReq";
					}
					else
						return payment();
				}else if("1".equalsIgnoreCase(bean.getModeOfPayment())||"2".equalsIgnoreCase(bean.getModeOfPayment())){
					validatePayment();
					if(!hasActionErrors()){
						//int stat=service.getPaymentInsertCashCheque(bean);
						int stat=paymentDetailInsert(bean.getModeOfPayment());
						if(stat==1){
							CommonDAO commonDAO = new CommonDAO();
							commonDAO.updateRenewalRiskDtl(bean.getRenewRefNo(),bean.getMerchant_reference(),bean.getPolicyNo(),bean.getModeOfPayment());
							//new SmsEmailUtil(SmsEmailUtil.PAYMENT_SUCESS_CUST_RENEWAL,bean.getRenewRefNo()).send();
							//new SmsEmailUtil(SmsEmailUtil.PAYMENT_SUCESS_SURVEYOR_RENEWAL,bean.getRenewRefNo()).send();
							return updateRenewalInfo();
						}else{
							return cashResponse();
						}
					}else{
						return "paymentRenewal";
					}
					
				}else if("101".equalsIgnoreCase(bean.getModeOfPayment())){
					String forward="paymentRenewal";
					validatePayment();
					if(!hasActionErrors()){
						//int stat=service.getPaymentInsert(bean);
						int stat=paymentDetailInsert(bean.getModeOfPayment());
						if(stat==1){
							try{
								if(StringUtils.isNotBlank(bean.getMerchant_reference())){
									MtnService ms = new MtnService(bean.getRenewRefNo(),"",bean.getMerchant_reference(),"65");
									Map<String, Object> mpd = ms.getPaymentDetails();
									if(mpd!=null && mpd.size()>0){
										String quoteNo = mpd.get("QUOTE_NO")==null?"":mpd.get("QUOTE_NO").toString();
										String productId = mpd.get("PRODUCT_ID")==null?"":mpd.get("PRODUCT_ID").toString();
										String premium = mpd.get("PREMIUM")==null?"":mpd.get("PREMIUM").toString();
										String merchantRefNo = mpd.get("MERCHANT_REFERENCE")==null?"":mpd.get("MERCHANT_REFERENCE").toString();
										String currencyType = mpd.get("CURRENCY_TYPE")==null?"":mpd.get("CURRENCY_TYPE").toString();
										String referenceNo = mpd.get("REFERENCE_NO")==null?"":mpd.get("REFERENCE_NO").toString();
										String mobileNo = mpd.get("MTN_MOBILE_NO")==null?"":mpd.get("MTN_MOBILE_NO").toString();
										
										
										if(StringUtils.isNotBlank(quoteNo) && StringUtils.isNotBlank(premium)
												&& StringUtils.isNotBlank(merchantRefNo) && StringUtils.isNotBlank(currencyType)
												&& StringUtils.isNotBlank(referenceNo) && StringUtils.isNotBlank(mobileNo)){
											ReqToPayIpModel rtp = new ReqToPayIpModel();
											rtp.setExternalId(quoteNo);
											rtp.setCurrency(currencyType);
											rtp.setAmount(premium);
											rtp.setPayeeNote("Payment for Madison");
											rtp.setPayerMessage("Pay for Insurance");
											Payer p = new Payer();
											p.setPartyIdType("MSISDN");
											if(!mobileNo.startsWith("26")){
												mobileNo = "26"+mobileNo;
											}
											p.setPartyId(mobileNo);
											rtp.setPayer(p);
											
											MtnService ms1 = new MtnService(quoteNo, referenceNo,
													merchantRefNo, productId);
											if(ms1.requestToPay(rtp)){
												try{
													Thread.sleep(5000);
												}catch(Exception e){
													e.printStackTrace();
												}
												MtnService ms2 = new MtnService(quoteNo, referenceNo,
														merchantRefNo, productId);
												String payStatus = ms2.paymentStatus();
												 if("pending".equalsIgnoreCase(payStatus)
														|| "success".equalsIgnoreCase(payStatus)
														|| "successful".equalsIgnoreCase(payStatus)){
													 forward= "mtnWaitingPage";
												}else{
													addActionError("MTN Payment Failed. Please try again later");
													forward= "paymentRenewal";
												}
											}else{
												throw new Exception(merchantRefNo+" Request Sending Failed");
											}
										}else{
											throw new Exception(merchantRefNo+" Mtn Payment Details Not Found");
										}
									}
								}else{
									throw new Exception(bean.getMerchant_reference()+" Merchant Reference No is Empty");
								}
							}catch(Exception e){
								addActionError("Something went wrong. please try again with other payment method.");
								e.printStackTrace();
								forward= "paymentRenewal";
							}
								
						}
						else{
							addActionError("Payment Detail Insert Error");
							forward= "paymentRenewal";
						}
					}else{
						forward= "paymentRenewal";
					}
					return forward;
				}
				else if("102".equalsIgnoreCase(bean.getModeOfPayment())){
					String forward="paymentRenewal";
					validatePayment();
					if(!hasActionErrors()){
						//int stat=service.getPaymentInsert(bean);
						int stat=paymentDetailInsert(bean.getModeOfPayment());
						if(stat==1){
							try{
								if(StringUtils.isNotBlank(bean.getMerchant_reference())){
									AirtelService as = new AirtelService(bean.getRenewRefNo(),"",bean.getMerchant_reference(),"65");
									Map<String, Object> mpd = as.getPaymentDetails();
									if(mpd!=null && mpd.size()>0){
										String quoteNo = mpd.get("QUOTE_NO")==null?"":mpd.get("QUOTE_NO").toString();
										String productId = mpd.get("PRODUCT_ID")==null?"":mpd.get("PRODUCT_ID").toString();
										String premium = mpd.get("PREMIUM")==null?"":mpd.get("PREMIUM").toString();
										String merchantRefNo = mpd.get("MERCHANT_REFERENCE")==null?"":mpd.get("MERCHANT_REFERENCE").toString();
										String currencyType = mpd.get("CURRENCY_TYPE")==null?"":mpd.get("CURRENCY_TYPE").toString();
										String referenceNo = mpd.get("REFERENCE_NO")==null?"":mpd.get("REFERENCE_NO").toString();
										String mobileNo = mpd.get("MTN_MOBILE_NO")==null?"":mpd.get("MTN_MOBILE_NO").toString();
										
										if(StringUtils.isNotBlank(quoteNo) && StringUtils.isNotBlank(premium)
												&& StringUtils.isNotBlank(merchantRefNo) && StringUtils.isNotBlank(currencyType)
												&& StringUtils.isNotBlank(mobileNo)){
											ReqToPayIpModelAirtel rtp = new ReqToPayIpModelAirtel();
											rtp.setReference("Madison Gen");
											
											Subscriber sub = new Subscriber();
											sub.setCountry("ZM");
											sub.setCurrency(currencyType);
											sub.setMsisdn(Integer.parseInt(mobileNo));
											
											Transaction txn = new Transaction();
											txn.setAmount(premium);
											txn.setCountry("ZM");
											txn.setCurrency(currencyType);
											txn.setId(merchantRefNo);
											
											rtp.setSubscriber(sub);
											rtp.setTransaction(txn);
											
											AirtelService as1 = new AirtelService(quoteNo, referenceNo,
													merchantRefNo, productId);
											if(as1.requestToPay(rtp)){
												try{
													Thread.sleep(5000);
												}catch(Exception e){
													e.printStackTrace();
												}
												AirtelService ms2 = new AirtelService(quoteNo, referenceNo,
														merchantRefNo, productId);
												String payStatus = ms2.paymentStatus(rtp);
												 if("pending".equalsIgnoreCase(payStatus)
														|| "success".equalsIgnoreCase(payStatus)
														|| "successful".equalsIgnoreCase(payStatus)
														|| "TIP".equalsIgnoreCase(payStatus)
														|| "TS".equalsIgnoreCase(payStatus)){
													 forward= "airtelWaitingPage";
												}else{
													addActionError("Airtel Payment Failed. Please try again later");
												}
											}else{
												throw new Exception(merchantRefNo+" Request Sending Failed");
											}
										}else{
											throw new Exception(merchantRefNo+" Airtel Payment Details Not Found");
										}
									}
								}else{
									throw new Exception(bean.getMerchant_reference()+" Merchant Reference No is Empty");
								}
							}catch(Exception e){
								addActionError("Something went wrong. please try again with other payment method.");
								e.printStackTrace();
							}
						}
						else{
							addActionError("Payment Detail Insert Error");
							forward= "paymentRenewal";
						}
					}
					else{
						forward= "paymentRenewal";
					}
					return forward;
				}
				else {
					addActionError("Something went wrong. please try again with other payment method.");
					return "paymentRenewal";
				}
					
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		else{
			addActionError("Please Select Payment Method");
			return "paymentRenewal";
		}
			return null;
		}
	
	public String cashResponse(){
		LogManager.info("Enter into Quick Renewal cashResponse()");
		return "cashResponse";
	}
	
	
	public String portfolio(){
		LogManager.info("Enter into Quick Renewal portfolio()");
		String forward="vehicleRenewalInfo";
		String mblNo=(String) session.get("user");
		bean.setpMode("Y");
		bean.setPolicyList(service.getPolicyInfo(mblNo));
		return forward;
	}
	
	
	public void validation(){
		if(StringUtils.isBlank(bean.getPolicyNo()) & StringUtils.isBlank(bean.getVehPlateNo())){
			addActionError("Please Enter any one, Policy Number or Vehicle Plate Number");
		}
		if(StringUtils.isBlank(bean.getMobileNo())){
			addActionError("Please Enter Your Registered Mobile Number");
		}
		//else if(!Pattern.matches("^(09)([0-9]{8})$", bean.getMobileNo())){
		else if(Validation.INVALID.equals(validations.validateMobile(bean.getMobileNo()))){
			addActionError("Mobile Number should begin with 09 or 07 and must contain 10 Digits (eg: 0977777777)");
		}
		Captcha capta=(Captcha) session.get("simpleCaptcha");
		validateCaptcha(bean.getCaptchavalue(),capta);
	}

	public void vehicleValidation(String vehicle){
		if(StringUtils.isBlank(vehicle)){
			addActionError("Please Select Any Vehicle");
		}
		if(StringUtils.isBlank(bean.getTempMobileNo())){
			addActionError("Please Enter Your Mobile Number");
		}
		//else if(!Pattern.matches("^(09)([0-9]{8})$", bean.getTempMobileNo())){
		else if(Validation.INVALID.equals(validations.validateMobile(bean.getTempMobileNo()))){
			addActionError("Mobile Number should begin with 09 or 07 and must contain 10 Digits (eg: 0977777777)");
		}
		if(StringUtils.isBlank(bean.getTempPassportNo())){
			addActionError("Please Your Enter NRC/Passport Number");
		}
		mailvalidation(bean.getTempEmailId());
	}

	public void mailvalidation(String name){
	    	Pattern pattern = Pattern.compile("^[A-Za-z0-9_\\+-]+(\\.[A-Za-z0-9_\\+-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*\\.([A-Za-z]{2,4})$");
	        		Matcher matcher = pattern.matcher(name);
	        		if(!matcher.matches()){
	        			addActionError("Please Enter Valid E-Mail ID");
	        		}
	    }

	public void validateCaptcha(String captchavalue, Captcha captcha) {
		try{
			if(!captcha.isCorrect(captchavalue)) 
				addActionError("Invalid Captcha. Enter Again");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
		public String adminRenewal(){
			List<Map<String, Object>> result = null;
			 bean.setAdminRenewalList(service.getAdminRenewal(bean));
			return "adminRenewal";
		}
		
	private String validateUserId(String string) {
		String user="";
		if(StringUtils.isNumeric(string)){
			if(string.length()==10){
				user=string;
			}
		}
		return user;
	}
	
	public String updateRenewalInfo(){
		String forward="vehicleRenewalInfo";
		LogManager.info("Enter into updateRenewalInfo()");
		try {
			 if("1".equalsIgnoreCase(bean.getModeOfPayment())||"2".equalsIgnoreCase(bean.getModeOfPayment())){
				 forward="cashResponse";
			 }
			bean.setRenewalList(service.getRenewList(bean));
			bean.setPaidList(service.getpaidDetails(bean));
			bean.setPaymentList(service.getPaymentList(bean));
			bean.setMyTotal("0.00");
			bean.setPremTax("0.00");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return forward;
	}
	
	public void validatePayment(){
		if("1".equalsIgnoreCase(bean.getModeOfPayment())){
			//if(StringUtils.isBlank(bean.getCashDepositBank()))
				//addActionError("Please Select Deposit Bank");
			if(StringUtils.isBlank(bean.getCashAmount()))
				addActionError("Please Enter Premium Amount");
			if(StringUtils.isBlank(bean.getCashInstrumentDate()))
				addActionError("Please Select Deposit Date");
		}
		else if("2".equalsIgnoreCase(bean.getModeOfPayment())){
			if(StringUtils.isBlank(bean.getChequeNo()))
				addActionError("Please Enter Cheque Number");
			if(StringUtils.isBlank(bean.getChequeDate()))
				addActionError("Please Select Cheque Date");
			if(StringUtils.isBlank(bean.getChequeAmount()))
				addActionError("Please Enter Cheque Amount");
			if(StringUtils.isBlank(bean.getBankName()))
				addActionError("Please Select Cheque Bank");
			if(StringUtils.isBlank(bean.getMicrCode()))
				addActionError("Please Enter MICR Code");
		}
		else if("101".equalsIgnoreCase(bean.getModeOfPayment())){
			if(StringUtils.isBlank(bean.getMtnMobileNo())){
				addActionError("Please Enter MTN Mobile Number");
			}else if(!StringUtils.isNumeric(bean.getMtnMobileNo())){
				addActionError("MTN Mobile Number Should be in Number Format");
			}else if(!bean.getMtnMobileNo().startsWith("09") && !bean.getMtnMobileNo().startsWith("07")){
				addActionError("MTN Mobile Number Should Starts with '09' or '07'");
			}else if(bean.getMtnMobileNo().length()!=10){
				addActionError("MTN Mobile Number Should be in 10 digit");
			}
		}
		else if("102".equalsIgnoreCase(bean.getModeOfPayment())){
			if(StringUtils.isBlank(bean.getAirtelMoneyNumber())){
				addActionError("Please Enter Airtel Money Number");
			}else if(!StringUtils.isNumeric(bean.getAirtelMoneyNumber())){
				addActionError("Airtel Money Number Should be in Number Format");
			}else{
				AirtelService ms2 = new AirtelService();
				boolean userStatus = ms2.userEnquiry(bean.getAirtelMoneyNumber());
				if(userStatus==false)
					addActionError("Please Enter Valid Airtel Money Number");
			}
		}
	}
	
	public String qrUpload(){
		LogManager.info("Enter into Quick Renewal qrUpload()");
		try {
			bean.setQrMode("qrUpload");
			session.put("product_id", "65");
			String set = validateUserId((String)session.get("user"));
			bean.setMobileNo(set);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "quickPage";
	}
	public String qrCodeUpload(){
		LogManager.info("Enter into qrCodeUpload()");
		String forward ="quickPage";
		try {
			if(upload==null){
				addActionError("Please Choose File to Upload");
			}
			else{
				String fileLoc="";
				uploadFileName=StringHelper.getFileNameFormat(uploadFileName, "");
				System.out.println("uploadFileName:"+uploadFileName);
				File fileToCreate = new File(QR_CODE_LOCATION, this.uploadFileName);
				FileUtils.copyFile(this.upload, fileToCreate);
				fileLoc=QR_CODE_LOCATION+uploadFileName;
				File qrFile = new File(fileLoc);
				boolean exists = qrFile.exists();
				LogManager.push("File Exist : "+exists);
				String charset = "UTF-8"; // or "ISO-8859-1"
				Map hintMap = new HashMap();
				hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
				if(exists){
					String qrCodeResult=readQRCode(fileLoc, charset, hintMap);
					bean.setQrCodeResult(qrCodeResult);
					forward=processQRCode();
				}
			}
		} catch (Exception e) {
			addActionError("Please Choose Valid QR Code file");
			e.printStackTrace();
		}
		return forward;
	}
	
	public static String readQRCode(String filePath, String charset, Map hintMap)throws FileNotFoundException, IOException, NotFoundException {
		BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(
				new BufferedImageLuminanceSource(
						ImageIO.read(new FileInputStream(filePath)))));
		Result qrCodeResult = new MultiFormatReader().decode(binaryBitmap,
				hintMap);
		return qrCodeResult.getText();
	}
	
	public String processQRCode(){
		LogManager.info("Enter into processQRCode()");
		String forward ="quickPage";
		try {
			String qrResult=bean.getQrCodeResult();
			String newQrResult=qrResult.replaceAll("\r", "").replaceAll("\n", ",");
			System.out.println("Data read from QR Code: "+ newQrResult);
			String arr[] = StringUtils.isNotBlank(newQrResult)?newQrResult.split(","):new String[0];
			String polNo=arr[0];
			String vehRegNo=arr[1];
			/*String polStDate=arr[2].toString().replaceAll("\\s.*", "");
			String polEndDate=arr[3].toString().replaceAll("\\s.*", "");
			String certNo=arr[4];
			String title=arr[5];*/
			System.out.println("Size of Data read from QR Code: "+ arr.length);
			if(arr.length>0 && StringUtils.isNotBlank(polNo)){
				/*String strArray[] = new String[6];
				strArray[0]=polNo;
				strArray[1]=vehRegNo;
				strArray[2]=polStDate;
				strArray[3]=polEndDate;
				strArray[4]=certNo;
				strArray[5]=title;*/
				int count=service.getPolicyExist(polNo);
				if(count>0){
					service.settingDetail(polNo,vehRegNo,bean);
					bean.setPolicyNo(polNo);
					bean.setVehPlateNo(vehRegNo);
					//if(StringUtils.isNotBlank(bean.getMobileNo())){
						forward=getRenewalNew();
					//}
					//else{
					//	addActionError("Renewal Details is not Available");
					//}
				}else
					addActionError("There is no Renewal Details for this Policy Number");
			}else {
				if("qrRead".equalsIgnoreCase(bean.getQrMode()))
					addActionError("Scanned File not having Valid Details");
				else if("qrUpload".equalsIgnoreCase(bean.getQrMode()))
					addActionError("Selected File not having Valid Details");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return forward;
	}
	
	public String getRenewalNew(){
		LogManager.info("Enter into Quick Renewal getRenewalNew()");
		String forward ="quickPage";
		/*List<Map<String, Object>> result = null;
		try{
			result=service.getPolicyDetails(bean);
			if(result.size()>0){
				int otp=new OTPGenerator().getOTP();
				int mailOtp=new OTPGenerator().getOTP();
				bean.setOtpId(new OTPGenerator().insertOTP(new String[]{bean.getOtpId(),"QUICK_RENEWAL",
						Integer.toString(otp),bean.getMobileNo(),bean.getEmail(),"","N",Integer.toString(mailOtp)}));
				new SmsEmailUtil("GET_OTP",Integer.toString(otp),bean.getMobileNo(),new OTPGenerator().getOtpExpiry(bean.getOtpId()),Integer.toString(mailOtp),bean.getEmail()).send();
				bean.setOtpStatus("N");
				bean.setOtp("");
				bean.setMailOtp("");
				bean.setOtpMessage("OTP has been sent to the mobile number ("+bean.getMobileNo()+" )");
				bean.setRenewalList(service.getPolicyDetails(bean));
				forward="otppage";
				}
			else{
				addActionError("There is no Renewal available for given details");
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}*/
		
		try {
			bean.setRenewalList(service.getRenewList(bean));
			forward="vehicleRenewalInfo";
			bean.setTempMobileNo(bean.getMobileNo());
			bean.setTempEmailId(bean.getEmail());
			bean.setTempPassportNo(bean.getPassportNo());
			bean.setMyTotal("0.00");
			bean.setPremTax("0.00");
			//bean.setRenewalList(service.getRenewList(bean));
			bean.setPaidList(service.getpaidDetails(bean));
			bean.setPaymentList(service.getPaymentList(bean));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return forward;
	}
	
}