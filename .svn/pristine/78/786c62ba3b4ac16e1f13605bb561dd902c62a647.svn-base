package com.maan.report;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import net.sf.jasperreports.engine.export.JRHtmlExporterParameter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRXlsAbstractExporterParameter;
import net.sf.jasperreports.engine.export.JRXlsExporter;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.maan.DBCon.DBConnection;
import com.maan.adminnew.common.CommonService;
import com.maan.common.LogManager;
import com.maan.common.dao.CommonDAO;
import com.opensymphony.xwork2.ActionContext;

public class JasperReports {
	private static final String applicationPath = CommonService.getApplicationPath();
	private final String jasperBasePath = applicationPath + "/Jasper/";
	private final String motorJasperPath = jasperBasePath + "/motor/";
	private final String homeJasperPath = jasperBasePath + "/home/";
	private final String travelJasperPath = jasperBasePath + "/travel/";
	private final String hospitalJasperPath = jasperBasePath + "/hospital/";
	private static final String imageURL = applicationPath + "/images/";
	private static final String QR_CODE_LOCATION = applicationPath + "QRImages/";
	
	private void init() {
		try {
			Map<String, Object> session=ActionContext.getContext().getSession();
			if(session!=null) {
				session.remove("pdfFilePath");
			}
		} catch(Exception exception) {
			//LogManager.debug("Exception @ JasperReports init() { " + exception);
		}
	}
	
	public void export(String startDate,String endDate, String loginId ,String jasperPath,OutputStream os,String fileName,String downloadType, String loginId2, String type){
		init();
		try{
			Map<String, Object> session=ActionContext.getContext().getSession();					
			String actualBranch=(String)session.get("adminBranch");
			String productId=(String)session.get("product_id");		
			final org.apache.log4j.Logger jasperLogger = org.apache.log4j.Logger.getLogger("net.sf.jasperreports");	
			jasperLogger.setLevel(Level.ERROR);

			HashMap<String,Object> jasperParameter = new HashMap<String,Object>();    		
			jasperParameter.put("startDate", startDate); 
			jasperParameter.put("endDate", endDate);
			jasperParameter.put("loginid", loginId2); 
			jasperParameter.put("product", productId);	
			jasperParameter.put("branch", actualBranch); 
			jasperParameter.put("opencover", "ALL");
			jasperParameter.put("type", type);
			jasperParameter.put("userLoginId", loginId);
			jasperParameter.put("imagePath", imageURL);		
			System.out.println(jasperParameter);
			JasperPrint jasperPrint = fillReport(jasperPath,jasperParameter);
			exporter(downloadType, jasperPrint, os);
		}
		catch(Exception e){
			LogManager.debug(e);
			e.printStackTrace();
		}

		LogManager.push("Filling report finished.........");	
	}

	public void opencoverSchedule(String proposalNo, String openCoverNo, String endtstatus, String branchcode, String filePath) {
		init();
		try{
			final Logger jasperLogger = Logger.getLogger("net.sf.jasperreports");	
			jasperLogger.setLevel(Level.ERROR);
			HashMap<String, Object> jasperParameter = new HashMap<String, Object>();

			jasperParameter.put("proposalNo",proposalNo);
			jasperParameter.put("openCoverNo",openCoverNo);
			jasperParameter.put("endtStatus",endtstatus);
			jasperParameter.put("branchCode", branchcode);	
			jasperParameter.put("imagePath", imageURL);
			
			String jasperPath = jasperBasePath + "/CargoOpenCover.jasper";
			
			JasperPrint jasperPrint = fillReport(jasperPath,jasperParameter);
			JasperExportManager.exportReportToPdfFile(jasperPrint,filePath);
		}
		catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	public void certificateSchedule(String applicationNo, String branchCode, String belongingBranch, String filePath,String displayText, String startDate, String endDate, String imageStatus) {
		init();
		try{
			final Logger jasperLogger = Logger.getLogger("net.sf.jasperreports");	
			jasperLogger.setLevel(Level.ERROR);
			HashMap<String, Object> jasperParameter = new HashMap<String, Object>();
			jasperParameter.put("Application_No",applicationNo);
			jasperParameter.put("branchCode", branchCode);
			jasperParameter.put("belongingBranchCode", belongingBranch);
			jasperParameter.put("imagePath", imageURL);
			jasperParameter.put("status", displayText);
			jasperParameter.put("Pvstartdate", startDate);
			jasperParameter.put("pvenddate", endDate);
			jasperParameter.put("ImageStatus", imageStatus);
			
			String jasperPath="";
			String jasperFileName = "";
			String productId = "";
			if(StringUtils.isNotBlank(applicationNo)) {
				productId = new com.maan.common.dao.CommonDAO().getProductIdByAppNo(applicationNo);
			}
			if("3".equals(productId)) {
				jasperFileName = "CargoCertificate.jasper";
			} else if("11".equals(productId)) {
				jasperFileName = "OpenCoverCertificate.jasper";
			} else {
				jasperFileName = "OpenCoverCertificate.jasper";
			}
			jasperPath = jasperBasePath + jasperFileName;

			JasperPrint jasperPrint = fillReport(jasperPath,jasperParameter);
			JasperExportManager.exportReportToPdfFile(jasperPrint,filePath);
		} 
		catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	
	public void certificatePrint(String applicationNo, String branchCode, String belongingBranch, String filePath, String productId,String displayText) {
		init();
		if("11".equals(productId)) {
			certificateSchedule(applicationNo, branchCode, belongingBranch, filePath,displayText,"","","Y");
		}
		else {
			try{
				final Logger jasperLogger = Logger.getLogger("net.sf.jasperreports");	
				jasperLogger.setLevel(Level.ERROR);
				HashMap<String, Object> jasperParameter = new HashMap<String, Object>();
				jasperParameter.put("applicationNo",applicationNo);
				jasperParameter.put("branchCode", branchCode);
				jasperParameter.put("belongingBranch", belongingBranch);
				jasperParameter.put("imagePath", imageURL);
				jasperParameter.put("brokerImagePath", applicationPath);
				jasperParameter.put("status", displayText);
				String jasperPath="";
				jasperPath = jasperBasePath + "/CargoPrint.jasper";
	
				JasperPrint jasperPrint = fillReport(jasperPath,jasperParameter);
				JasperExportManager.exportReportToPdfFile(jasperPrint,filePath);
			} 
			catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}

	public void certificateEndt(String policyNo, String applicationNo, String branchCode, String belongingBranch, String filePath) {
		init();
		try{
			final Logger jasperLogger = Logger.getLogger("net.sf.jasperreports");	
			jasperLogger.setLevel(Level.ERROR);
			HashMap<String, Object> jasperParameter = new HashMap<String, Object>();
			jasperParameter.put("policyNo",policyNo);
			jasperParameter.put("applicationNo",applicationNo);
			jasperParameter.put("branchCode", branchCode);
			jasperParameter.put("imagePath", imageURL);
			jasperParameter.put("belongingBranch", belongingBranch);
			String jasperPath="";
			jasperPath = jasperBasePath + "/CargoEndtCertificate.jasper";

			JasperPrint jasperPrint = fillReport(jasperPath,jasperParameter);
			JasperExportManager.exportReportToPdfFile(jasperPrint,filePath);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}


	public void debitNote(String PolicyNo, String branchCode, String filePath, String pvType, String startDate, String endDate, String imageStatus) {
		init();
		try{
			final Logger jasperLogger = Logger.getLogger("net.sf.jasperreports");	
			jasperLogger.setLevel(Level.ERROR);
			HashMap<String, Object> jasperParameter = new HashMap<String, Object>();
			/*String applicationNo = finalBean.getApplicationNo(PolicyNo);
			if(StringUtils.isBlank(applicationNo)){
				applicationNo = finalBean.getApplicationNo1(PolicyNo);
			}*/
			jasperParameter.put("policyNumber",PolicyNo);
			jasperParameter.put("branchCode", branchCode);	
			jasperParameter.put("imagePath", imageURL);
			jasperParameter.put("pvType", pvType);
			jasperParameter.put("Pvstartdate", startDate);
			jasperParameter.put("pvenddate", endDate);
			jasperParameter.put("ImageStatus", imageStatus);
			
			String jasperPath="";
			jasperPath = jasperBasePath + "/CargoDebit.jasper";

			JasperPrint jasperPrint = fillReport(jasperPath,jasperParameter);
			JasperExportManager.exportReportToPdfFile(jasperPrint,filePath);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	public void creditNote(String PolicyNo, String branchCode, String filePath, String pvType, String startDate, String endDate, String imageStatus) {
		init();
		try{
			final Logger jasperLogger = Logger.getLogger("net.sf.jasperreports");	
			jasperLogger.setLevel(Level.ERROR);
			HashMap<String, Object> jasperParameter = new HashMap<String, Object>();
			jasperParameter.put("policyNumber",PolicyNo);
			jasperParameter.put("branchCode", branchCode);	
			jasperParameter.put("imagePath", imageURL);
			jasperParameter.put("pvType", pvType);
			jasperParameter.put("Pvstartdate", startDate);
			jasperParameter.put("pvenddate", endDate);
			jasperParameter.put("ImageStatus", imageStatus);
			
			String jasperPath="";
			jasperPath = jasperBasePath + "/CargoCredit.jasper";

			JasperPrint jasperPrint = fillReport(jasperPath,jasperParameter);
			JasperExportManager.exportReportToPdfFile(jasperPrint,filePath);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	
	public void LCSmartReport(String loginId,String branchCode,String type,String value,String downloadType, OutputStream os) {
		init();
		try{
			final org.apache.log4j.Logger jasperLogger = org.apache.log4j.Logger.getLogger("net.sf.jasperreports");	
			jasperLogger.setLevel(Level.ERROR);
			String jasperPath = jasperBasePath + "/LCSmartReport.jasper";
			HashMap<String,Object> jasperParameter = new HashMap<String,Object>();    		

			jasperParameter.put("loginId", loginId); 
			jasperParameter.put("branchCode", branchCode);
			jasperParameter.put("type", type); 
			jasperParameter.put("value", value);	

			JasperPrint jasperPrint = fillReport(jasperPath,jasperParameter);
			exporter(downloadType, jasperPrint, os);
		}
		catch(Exception e){
			LogManager.debug(e);
			e.printStackTrace();
		}

		LogManager.push("Filling report finished.........");
	}
	
	public void openCoverReport(String startDate, String endDate, String loginId, String branchCode, String downloadType, OutputStream os) {
		init();
		try{
			final org.apache.log4j.Logger jasperLogger = org.apache.log4j.Logger.getLogger("net.sf.jasperreports");	
			jasperLogger.setLevel(Level.ERROR);
			String jasperPath = jasperBasePath + "/OpenCoverReport.jasper";
			HashMap<String,Object> jasperParameter = new HashMap<String,Object>();    		

			jasperParameter.put("startDate", startDate); 
			jasperParameter.put("endDate", endDate);
			jasperParameter.put("loginId", loginId); 
			jasperParameter.put("branchCode", branchCode);	

			JasperPrint jasperPrint = fillReport(jasperPath,jasperParameter);
			exporter(downloadType, jasperPrint, os);

		}
		catch(Exception e){
			LogManager.debug(e);
			e.printStackTrace();
		}

		LogManager.push("Filling report finished.........");
	}
	public void openCoverdetailReport(Object[] arg, String downloadType, OutputStream os) {
		init();
		try{
			final org.apache.log4j.Logger jasperLogger = org.apache.log4j.Logger.getLogger("net.sf.jasperreports");	
			jasperLogger.setLevel(Level.ERROR);
			String jasperPath = jasperBasePath + "/OpenCoverDetailReport.jasper";
			HashMap<String,Object> jasperParameter = new HashMap<String,Object>();    		

			jasperParameter.put("branchCode",arg[0]); 
			jasperParameter.put("startDate",arg[1]);
			jasperParameter.put("endDate",arg[2]);
			jasperParameter.put("brokerLogin",arg[3]);
			jasperParameter.put("loginId",arg[4]);
			jasperParameter.put("productId",arg[5]);
			jasperParameter.put("openCover",arg[6]);

			JasperPrint jasperPrint = fillReport(jasperPath,jasperParameter);
			exporter(downloadType, jasperPrint, os);

		}
		catch(Exception e){
			LogManager.debug(e);
			e.printStackTrace();
		}

		LogManager.push("Filling report finished.........");
	}
	
	public void branchReports(String startDate,String endDate,String status,String productId,String branchCode, String filePath) {
		try{
			final Logger jasperLogger = Logger.getLogger("net.sf.jasperreports");	
			jasperLogger.setLevel(Level.ERROR);
			HashMap<String, Object> jasperParameter = new HashMap<String, Object>();
			jasperParameter.put("startDate", startDate);
			jasperParameter.put("endDate", endDate);	
			jasperParameter.put("status", status);
			jasperParameter.put("productId", productId);
			jasperParameter.put("branchCode", branchCode);
			String jasperPath="";
			jasperPath = jasperBasePath + "/BranchReports.jasper";

			JasperPrint jasperPrint = fillReport(jasperPath,jasperParameter);
			JasperExportManager.exportReportToPdfFile(jasperPrint,filePath);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	
	public void dayEndBranchReports(String startDate,String endDate,String status,String productId,String branchCode, String filePath) {
		try{
			final Logger jasperLogger = Logger.getLogger("net.sf.jasperreports");	
			jasperLogger.setLevel(Level.ERROR);
			HashMap<String, Object> jasperParameter = new HashMap<String, Object>();
			jasperParameter.put("startDate", startDate);
			jasperParameter.put("endDate", endDate);	
			jasperParameter.put("status", status);
			jasperParameter.put("branchCode", branchCode);
			String jasperPath="";
			jasperPath = jasperBasePath + "/DayEndBranchReports.jasper";

			JasperPrint jasperPrint = fillReport(jasperPath,jasperParameter);
			
			ByteArrayOutputStream baos=new ByteArrayOutputStream();
			exporter("excel",jasperPrint,baos);
			StreamToFile(filePath, baos);
			LogManager.push("Filling report finished.........");
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	public void BranchReport(Object[] arg ,String downloadType, OutputStream os) {
		init();
		/*Connection connection=null;
		try {
			connection=DBConnection.getInstance().getDBConnection();
		} 

		catch (Exception e1) {
			e1.printStackTrace();
		} */
		try{
			final org.apache.log4j.Logger jasperLogger = org.apache.log4j.Logger.getLogger("net.sf.jasperreports");	
			jasperLogger.setLevel(Level.ERROR);
			String jasperPath = jasperBasePath + "/BranchReports.jasper";
			//JasperReport jasperReport;
			JasperPrint jasperPrint;
			HashMap<String,Object> jasperParameter = new HashMap<String,Object>();    		

			jasperParameter.put("startDate",arg[0]); 
			jasperParameter.put("endDate",arg[1]);
			jasperParameter.put("productId",arg[2]);
			jasperParameter.put("status",arg[3]);
			jasperParameter.put("branchCode",arg[4]);
			jasperParameter.put("application",arg[5]);
			System.out.println(jasperParameter);
			/*if(new File(jasperPath).exists())
				jasperReport = (JasperReport)JRLoader.loadObjectFromLocation(jasperPath);
			else
			{
				String path=jasperPath.replace(".jasper", ".jrxml");
				String temp = JasperCompileManager.compileReportToFile(path);
				LogManager.push("File "+temp);			    
				jasperReport = (JasperReport)JRLoader.loadObjectFromLocation(jasperPath);
			}
			LogManager.push("Filling report started.........");			 
			jasperPrint = JasperFillManager.fillReport(jasperReport,jasperParameter, connection); */
			jasperPrint = fillReport(jasperPath,jasperParameter);
			exporter(downloadType, jasperPrint, os);
		}
		catch(Exception e){
			LogManager.debug(e);
			e.printStackTrace();
		}

		LogManager.push("Filling report finished.........");
	}
	/**
	 * This Method for Bind all Schedules of Specified Brokers/User
	 * */
	public void policyCertificate(String branchCode, String belongingBranch, String filePath,String displayText, String startDate, String imageStatus,String openCoverNo,String userType,String productId){
		init();
		try{
			final Logger jasperLogger = Logger.getLogger("net.sf.jasperreports");	
			jasperLogger.setLevel(Level.ERROR);
			HashMap<String, Object> jasperParameter = new HashMap<String, Object>();
			 
			jasperParameter.put("branchCode", branchCode);
			jasperParameter.put("belongingBranchCode", belongingBranch);
			jasperParameter.put("imagePath", imageURL);
			jasperParameter.put("status", displayText);
			jasperParameter.put("Pvstartdate", startDate);
			jasperParameter.put("Pvusertype", userType);
			jasperParameter.put("OpencoverNo", openCoverNo);
			jasperParameter.put("ImageStatus", imageStatus);
			
			String jasperPath="";
			String jasperFileName = "";
			if("3".equals(productId)){
				jasperFileName="PolicyCertificate.jasper";
			}else{
				jasperFileName="PolicyOpenCoverCertificate.jasper";
			}	
						 
			jasperPath = jasperBasePath + jasperFileName;

			JasperPrint jasperPrint = fillReport(jasperPath,jasperParameter);
			JasperExportManager.exportReportToPdfFile(jasperPrint,filePath);
		} 
		catch (Exception e1) {
			e1.printStackTrace();
		}
	}	
	public void uwBranchReport(Object[] arg ,String downloadType, OutputStream os) {
		init();
		try{
			final org.apache.log4j.Logger jasperLogger = org.apache.log4j.Logger.getLogger("net.sf.jasperreports");	
			jasperLogger.setLevel(Level.ERROR);
			String jasperPath = jasperBasePath + "/UWBrokerReport.jasper";
			JasperPrint jasperPrint;
			HashMap<String,Object> jasperParameter = new HashMap<String,Object>();    		

			jasperParameter.put("startDate",arg[0]); 
			jasperParameter.put("endDate",arg[1]);
			jasperParameter.put("productId",arg[2]);
			jasperParameter.put("status",arg[3]);
			jasperParameter.put("branchCode",arg[4]);
			jasperParameter.put("application",arg[5]);
			jasperParameter.put("loginId",arg[6]);
			System.out.println(jasperParameter);
			jasperPrint = fillReport(jasperPath,jasperParameter);
			exporter(downloadType, jasperPrint, os);
		}
		catch(Exception e){
			LogManager.debug(e);
			e.printStackTrace();
		}

		LogManager.push("Filling report finished.........");
	}
	
	/************* Motor Start *****************/
	public void motorSchedule(String quoteNo, String branchCode, String filePath, String displayText, String productId) {
		try{
			final Logger jasperLogger = Logger.getLogger("net.sf.jasperreports");	
			jasperLogger.setLevel(Level.ERROR);
			HashMap<String, Object> jasperParameter = new HashMap<String, Object>();
			jasperParameter.put("Quoteno", quoteNo);
			jasperParameter.put("Pvbranch", branchCode);
			jasperParameter.put("imagePath", imageURL);
			jasperParameter.put("Status", displayText);
			jasperParameter.put("SUBREPORT_DIR", motorJasperPath);
			
			
			String jasperPath =  motorJasperPath + "/Schedule.jasper";
			List<String> subJasperPath = new ArrayList<String>();
			subJasperPath.add(motorJasperPath + "/Schedule_subreport1.jasper");
			subJasperPath.add(motorJasperPath + "/Deductible.jasper");
			subJasperPath.add(motorJasperPath + "/VehicleDetails.jasper");
			JasperPrint jasperPrint = fillReport(jasperPath,subJasperPath, jasperParameter);
			JasperExportManager.exportReportToPdfFile(jasperPrint,filePath);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	
	public void motorQuotation(String quoteNo, String branchCode, String filePath, String displayText, String productId) {
		try{
			final Logger jasperLogger = Logger.getLogger("net.sf.jasperreports");	
			jasperLogger.setLevel(Level.ERROR);
			HashMap<String, Object> jasperParameter = new HashMap<String, Object>();
			jasperParameter.put("Quoteno", quoteNo);
			jasperParameter.put("Pvbranch", branchCode);
			jasperParameter.put("imagePath", imageURL);
			//jasperParameter.put("Status", displayText);
			jasperParameter.put("SUBREPORT_DIR", motorJasperPath);
			
			
			String jasperPath =  motorJasperPath + "/Quotation.jasper";
			List<String> subJasperPath = new ArrayList<String>();
			subJasperPath.add(motorJasperPath + "/Quotation_subreport1.jasper");
			subJasperPath.add(motorJasperPath + "/Deductible.jasper");
			JasperPrint jasperPrint = fillReport(jasperPath,subJasperPath, jasperParameter);
			JasperExportManager.exportReportToPdfFile(jasperPrint,filePath);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	
	public void getReceipt(String quoteNo, String productId, String branchCode, String filePath) {
		try{
			final Logger jasperLogger = Logger.getLogger("net.sf.jasperreports");	
			jasperLogger.setLevel(Level.ERROR);
			HashMap<String, Object> jasperParameter = new HashMap<String, Object>();
			jasperParameter.put("Quoteno", quoteNo);
			jasperParameter.put("Pvproduct", productId);
			jasperParameter.put("Pvbranch", branchCode);
			jasperParameter.put("imagePath", imageURL);
			String jasperPath = motorJasperPath + "/Receipt.jasper";
			JasperPrint jasperPrint = fillReport(jasperPath,jasperParameter);
			JasperExportManager.exportReportToPdfFile(jasperPrint,filePath);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	
	public void getDebitMotor(String quoteNo, String productId, String branchCode, String filePath, String displayText) {
		try{
			final Logger jasperLogger = Logger.getLogger("net.sf.jasperreports");	
			jasperLogger.setLevel(Level.ERROR);
			HashMap<String, Object> jasperParameter = new HashMap<String, Object>();
			jasperParameter.put("Quoteno", quoteNo);
			jasperParameter.put("Pvproduct", productId);
			jasperParameter.put("Pvbranch", branchCode);
			jasperParameter.put("Status", displayText);
			jasperParameter.put("imagePath", imageURL);
			
			String jasperPath = motorJasperPath + "/Debit.jasper";
			JasperPrint jasperPrint = fillReport(jasperPath,jasperParameter);
			JasperExportManager.exportReportToPdfFile(jasperPrint,filePath);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	
	public void getMotorFleetSchedule(String quoteNo, String branchCode, String displayText, String vehicleId, String filePath, String userType, String loginId) {
		LogManager.push("Enter getMotorFleetSchedule");
		String fileLoc="";
		try{
			final Logger jasperLogger = Logger.getLogger("net.sf.jasperreports");	
			jasperLogger.setLevel(Level.ERROR);
			String userName= new CommonDAO().getSingleInfo("GET_USER_NAME", new String[]{loginId});
				List<Map<String,Object>> qrData= new CommonDAO().getQrcodeInfo(quoteNo,branchCode,vehicleId);
				String polNo=qrData.get(0).get("POLICY_NO")==null?"":qrData.get(0).get("POLICY_NO").toString();
				String vehRegNo=qrData.get(0).get("VECHICLE_REG_NO")==null?"":qrData.get(0).get("VECHICLE_REG_NO").toString();
				String issueDate=qrData.get(0).get("ISSUE_DATE")==null?"":qrData.get(0).get("ISSUE_DATE").toString();
				String expDate=qrData.get(0).get("EXPIRY_DATE")==null?"":qrData.get(0).get("EXPIRY_DATE").toString();
				String certNo=qrData.get(0).get("CERTIFICATE_NO")==null?"":qrData.get(0).get("CERTIFICATE_NO").toString();
				String tag="MGen ZM";
				String msg=polNo+"\r\n"+vehRegNo+"\r\n"+issueDate+"\r\n"+expDate+"\r\n"+certNo+"\r\n"+tag;
				LogManager.push("QR Data --->"+msg);
				try {
					fileLoc=QR_CODE_LOCATION+quoteNo+".JPG";
					generateQRCode(msg, 200, 200, fileLoc);
					File qrFile = new File(fileLoc);
					boolean exists = qrFile.exists();
					LogManager.push("File Exist : "+exists);
				} catch (Exception e) {
					e.printStackTrace();
				} 
			
			HashMap<String, Object> jasperParameter = new HashMap<String, Object>();
			jasperParameter.put("Quoteno", quoteNo);
			jasperParameter.put("Pvbranch", branchCode);
			jasperParameter.put("Status", displayText);
			jasperParameter.put("PvVechicle", vehicleId);
			jasperParameter.put("imagePath", imageURL);
			jasperParameter.put("Pvusername",userName==null?"":userName);
			jasperParameter.put("SUBREPORT_DIR", motorJasperPath);
			jasperParameter.put("QRPath", QR_CODE_LOCATION);
			String jasperPath ="";
			List<String> subJasperPath = new ArrayList<String>();
			//if("user".equalsIgnoreCase(userType)){
				LogManager.push("Enter User Side Report");
				//jasperPath = motorJasperPath + "/PolicyCertificate.jasper";
				jasperPath = motorJasperPath + "/PolicyCertificateNew.jasper";
				/*jasperPath = motorJasperPath + "/PolicyCertificateNew.jasper";
				subJasperPath.add(motorJasperPath + "/PolicyCertificateNew_subreport1.jasper");*/
			/*}else{
				LogManager.push("Enter Admin Side Report");
				jasperPath = motorJasperPath + "/AdminPolicyCertificate.jasper";
				subJasperPath.add(motorJasperPath + "/AdminPolicyCertificate_subreport1.jasper");
			}*/
			JasperPrint jasperPrint;
			if("user".equalsIgnoreCase(userType))
				jasperPrint = fillReport(jasperPath,jasperParameter);
			else
				jasperPrint = fillReport(jasperPath,subJasperPath,jasperParameter);
			
			//JasperPrint jasperPrint = fillReport(jasperPath,subJasperPath,jasperParameter);
			JasperExportManager.exportReportToPdfFile(jasperPrint,filePath);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	/************* Motor End *****************/
	
	/************* Travel Start **************/
	public void travelSchedule(String quoteNo, String branchCode, String filePath, String displayText){
		try{
			final Logger jasperLogger = Logger.getLogger("net.sf.jasperreports");	
			jasperLogger.setLevel(Level.ERROR);
			HashMap<String, Object> jasperParameter = new HashMap<String, Object>();
			jasperParameter.put("quoteno", quoteNo);
			jasperParameter.put("branchCode", branchCode);
			jasperParameter.put("imagePath", imageURL);
			jasperParameter.put("status", displayText);
			String jasperPath = travelJasperPath+ "PolicySchedule.jasper";
			JasperPrint jasperPrint = fillReport(jasperPath,jasperParameter);
			JasperExportManager.  exportReportToPdfFile(jasperPrint,filePath);
		}catch (Exception e) {
			LogManager.info("Exception Occured @ Travel Schedule"+e);
			e.printStackTrace();
		}
	}
	/************* Travel End **************/
	
	/************* Home Start **************/
	public void homeSchedule(String quoteNo, String schemeId, String contentTypeId, String branchCode,String displayText, String filePath) {
		try{
			final Logger jasperLogger = Logger.getLogger("net.sf.jasperreports");	
			jasperLogger.setLevel(Level.ERROR);
			HashMap<String, Object> jasperParameter = new HashMap<String, Object>();
			jasperParameter.put("Quoteno", quoteNo);
			jasperParameter.put("scheme_id", schemeId);
			jasperParameter.put("coverage_id", contentTypeId);
			jasperParameter.put("Pvbranch", branchCode);
			jasperParameter.put("status", displayText);
			jasperParameter.put("imagePath", imageURL);
			jasperParameter.put("SUBREPORT_DIR", homeJasperPath);
			
			String jasperPath = homeJasperPath + "/HomeSchedule.jasper";
			List<String> subJasperPath = new ArrayList<String>();
			subJasperPath.add(homeJasperPath + "/HomeSchedule_subreport1.jasper");
			subJasperPath.add(homeJasperPath + "/HomeSchedule_subreport2.jasper");
			JasperPrint jasperPrint = fillReport(jasperPath,subJasperPath,jasperParameter);
			JasperExportManager.exportReportToPdfFile(jasperPrint,filePath);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	/************* Home End **************/
	
	
	
	
	
	/****** Document Writing *******/
	private JasperPrint fillReport(String jasperPath, Map<String,Object> jasperParameter) throws Exception {
		compileReportToFile(jasperPath);
		Connection connection=DBConnection.getInstance().getDBConnection();
		LogManager.push("Filling report started.........");
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperPath,jasperParameter, connection);
		LogManager.push("Filling report ended.........");
		return jasperPrint;
	}
	private JasperPrint fillReport(String jasperPath, List<String> subJasperPath, Map<String,Object> jasperParameter) throws Exception {
		compileReportToFile(jasperPath);
		for(int i=0 ; i< subJasperPath.size() ; i++) {
			compileReportToFile(subJasperPath.get(i));
		}
		Connection connection=DBConnection.getInstance().getDBConnection();
		LogManager.push("Filling report started.........");
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperPath,jasperParameter, connection);
		LogManager.push("Filling report ended.........");
		return jasperPrint;
	}
	private void compileReportToFile(String jasperPath) throws Exception {
		File jasperFile = new File(jasperPath);
		if(!jasperFile.exists()){
			String path=jasperFile.getAbsolutePath().replace(".jasper", ".jrxml");
			String temp = JasperCompileManager.compileReportToFile(path);
			LogManager.push("Compiled File "+temp);
		}
	}
	private void exporter(String downloadType, JasperPrint jasperPrint, OutputStream os) throws Exception {
		if("excel".equals(downloadType))
		{
			JRXlsExporter exporter = new JRXlsExporter();
			exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM,os);
			exporter.setParameter(JRXlsAbstractExporterParameter.IS_COLLAPSE_ROW_SPAN, Boolean.TRUE);
			exporter.setParameter(JRXlsAbstractExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_COLUMNS, Boolean.TRUE);
			exporter.setParameter(JRXlsAbstractExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);
			exporter.setParameter(JRXlsAbstractExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE);
			exporter.setParameter(JRXlsAbstractExporterParameter.IS_DETECT_CELL_TYPE, Boolean.TRUE);
			exporter.setParameter(JRXlsAbstractExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);
			//exporter.setParameter(JRXlsAbstractExporterParameter.IS_IGNORE_GRAPHICS, Boolean.TRUE);
			//exporter.setParameter(JRXlsAbstractExporterParameter.IS_IGNORE_GRAPHICS, Boolean.TRUE);
			exporter.exportReport();
		}			
		else if("pdf".equals(downloadType))
		{
			JRPdfExporter exporter = new JRPdfExporter();
			exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);					
			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM,os);							
			exporter.exportReport();				
		}			
		else
		{

			JRHtmlExporter exporter = new JRHtmlExporter();
			exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM,os);	

			exporter.setParameter(JRHtmlExporterParameter.IS_WHITE_PAGE_BACKGROUND, false);
			exporter.setParameter(JRHtmlExporterParameter.IGNORE_PAGE_MARGINS, true);
			exporter.setParameter(JRHtmlExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, true);
			exporter.setParameter(JRHtmlExporterParameter.ZOOM_RATIO, 1.3F);
			exporter.setParameter(JRHtmlExporterParameter.BETWEEN_PAGES_HTML, "");
			exporter.setParameter(JRHtmlExporterParameter.FRAMES_AS_NESTED_TABLES,true);	
			exporter.exportReport();			
		}
	}
	private void StreamToFile(String filePath, ByteArrayOutputStream baos) throws IOException {
		FileOutputStream fos = null;
		try {
		    fos = new FileOutputStream (filePath);
		    baos.writeTo(fos);
		} catch(IOException ioe) {
		    // Handle exception here
		    ioe.printStackTrace();
		} finally {
		    fos.close();
		}
	}
	
	public OutputStream travelAdminReport(String startdate,String enddate){
		OutputStream baos=new ByteArrayOutputStream();
		try{
			Map<String, Object> session=ActionContext.getContext().getSession();					
			String branch=(String)session.get("adminBranch");
			String product=(String)session.get("product_id");		
			final org.apache.log4j.Logger jasperLogger = org.apache.log4j.Logger.getLogger("net.sf.jasperreports");	
			jasperLogger.setLevel(Level.ERROR);
			HashMap<String,Object> jasperParameter = new HashMap<String,Object>();    		
			jasperParameter.put("StartDate", startdate); 
			jasperParameter.put("EndDate", enddate);
			jasperParameter.put("Pvproduct", product); 
			jasperParameter.put("BranchCode", branch);	
			System.out.println(jasperParameter);
			String jasperpath = travelJasperPath+"TravelReport.jasper";
			JasperPrint jasperPrint = fillReport(jasperpath,jasperParameter);
			exporter("excel", jasperPrint, baos);
		}
		catch(Exception e){
			LogManager.debug(e);
			e.printStackTrace();
		}
		return baos;
	}
	
	public OutputStream motorAdminReport(String startdate,String enddate,String policytype){
		OutputStream baos=new ByteArrayOutputStream();
		try{
			Map<String, Object> session=ActionContext.getContext().getSession();					
			String branch=(String)session.get("adminBranch");
			String productId=(String)session.get("product_id");		
			final org.apache.log4j.Logger jasperLogger = org.apache.log4j.Logger.getLogger("net.sf.jasperreports");	
			jasperLogger.setLevel(Level.ERROR);
			String jasperPath =motorJasperPath+"MotorReport.jasper";
			Map<String,Object> jasperParameter = new TreeMap<String,Object>(String.CASE_INSENSITIVE_ORDER);    		
			jasperParameter.put("StartDate", startdate); 
			jasperParameter.put("EndDate", enddate);
			jasperParameter.put("Pvpolicytype", policytype);
			jasperParameter.put("BranchCode", branch); 
			System.out.println(jasperParameter);
			JasperPrint jasperPrint = fillReport(jasperPath,jasperParameter);			 
			exporter("excel",jasperPrint,baos);			 
		}
		catch(Exception e){
			LogManager.debug(e);
			e.printStackTrace();
		}
		return baos;
	}
	public OutputStream commonAllReport(String type,String startdate,String enddate,String cover,
			String status,String input1,String input2,String user,String product,String downloadType){
		OutputStream baos=new ByteArrayOutputStream();
		try{
			String jasperPath="";
			Map<String, Object> session=ActionContext.getContext().getSession();					
			final org.apache.log4j.Logger jasperLogger = org.apache.log4j.Logger.getLogger("net.sf.jasperreports");	
			jasperLogger.setLevel(Level.ERROR);
			if("APRQUOTES".equalsIgnoreCase(type)){
				jasperPath =jasperBasePath+"commonInsReport.jasper";
			}else if("ENDORSEMENTREGISTER".equalsIgnoreCase(type) || "ROADASSIT".equalsIgnoreCase(type)){
				jasperPath =jasperBasePath+"commonInsReport.jasper";
			}else if("POLICYREGISTER".equalsIgnoreCase(type)){
				jasperPath =motorJasperPath+"commonPolicyReport.jasper";
			}else if("PREMIUMREGISTER".equalsIgnoreCase(type)){
				jasperPath =jasperBasePath+"commonInsReport.jasper";
			}else if("CLAIMS".equalsIgnoreCase(type)){
				jasperPath =jasperBasePath+"commonInsReport.jasper";	
			}else if("INSTALMENTPAYMENT".equalsIgnoreCase(type) || "AGINGANALYSIS".equalsIgnoreCase(type)){
				jasperPath =jasperBasePath+"commonInsReport.jasper";
			}
			Map<String,Object> jasperParameter = new TreeMap<String,Object>(String.CASE_INSENSITIVE_ORDER);    		
			jasperParameter.put("Pvtype", type); 
			jasperParameter.put("pvstartdate", startdate);
			jasperParameter.put("pvenddate", enddate);
			jasperParameter.put("pvstatus", status);
			jasperParameter.put("pvcover", cover); 
			jasperParameter.put("pvinput1", input1);
			jasperParameter.put("pvinput2", input2);
			jasperParameter.put("pvuser", user);
			jasperParameter.put("PVPRODUCT", product);
			System.out.println(jasperParameter);
			JasperPrint jasperPrint = fillReport(jasperPath,jasperParameter);			 
			exporter(downloadType,jasperPrint,baos);			 
		}
		catch(Exception e){
			LogManager.debug(e);
			e.printStackTrace();
		}
		return baos;
	}
	
	@SuppressWarnings("deprecation")
	public void generateQRCode(String text, int width, int height, String filePath) {
		LogManager.push("--- Enter generateQRCode ---");
		try {
			QRCodeWriter writer = new QRCodeWriter();
			BitMatrix matrix = writer.encode(text, BarcodeFormat.QR_CODE, width, height);
			MatrixToImageWriter.writeToFile(matrix, filePath.substring(filePath
			        .lastIndexOf('.') + 1), new File(filePath));
			LogManager.push("QR Code image created successfully!");
		} catch (Exception e) {
			LogManager.debug("EXCEPTION in generateQRCode @ { " + e + " }");
			e.printStackTrace();
		}
		LogManager.push("--- Exit generateQRCode ---");
		}
	
	public void hospitalSchedule(String quoteNo, String branchCode,String realFilePath, String string, String productId) {
		try{
			final Logger jasperLogger = Logger.getLogger("net.sf.jasperreports");	
			jasperLogger.setLevel(Level.ERROR);
			HashMap<String, Object> jasperParameter = new HashMap<String, Object>();
			jasperParameter.put("quoteno", quoteNo);
			jasperParameter.put("branchCode", branchCode);
			jasperParameter.put("imagePath", imageURL);
			String jasperPath = hospitalJasperPath+ "HospitalCoverNote.jasper";
			JasperPrint jasperPrint = fillReport(jasperPath,jasperParameter);
			JasperExportManager.  exportReportToPdfFile(jasperPrint,realFilePath);
		}catch (Exception e) {
			LogManager.info("Exception Occured @ Travel Schedule"+e);
			e.printStackTrace();
		}
		
	}

	public void hospitalLetterSchedule(String quoteNo, String schemeId,String contentTypeId, String branchCode, String displayText,String realFilePath) {
		try{
			final Logger jasperLogger = Logger.getLogger("net.sf.jasperreports");	
			jasperLogger.setLevel(Level.ERROR);
			HashMap<String, Object> jasperParameter = new HashMap<String, Object>();
			jasperParameter.put("quoteno", quoteNo);
			jasperParameter.put("branchCode", branchCode);
			jasperParameter.put("imagePath", imageURL);
			String jasperPath = hospitalJasperPath+ "HospitalCoverLetter.jasper";
			JasperPrint jasperPrint = fillReport(jasperPath,jasperParameter);
			JasperExportManager.  exportReportToPdfFile(jasperPrint,realFilePath);
		}catch (Exception e) {
			LogManager.info("Exception Occured @ Travel Schedule"+e);
			e.printStackTrace();
		}
		
	}
}
