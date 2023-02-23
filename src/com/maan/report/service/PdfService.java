package com.maan.report.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.lowagie.text.Document;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfImportedPage;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfWriter;
import com.maan.adminnew.common.CommonService;
import com.maan.common.LogManager;
import com.maan.report.JasperReports;
import com.maan.report.dao.PdfDAO;

import rsa.pdf.finalprint;

public class PdfService {
	JasperReports jasperReport = new JasperReports();
	PdfDAO dao = new PdfDAO();
	
	private String getRealPdfPath(String filPath) {
		return CommonService.getApplicationPath() + filPath;
	}
	private String getpdfPath(String reqFrom, String sequenceNo, String displayText) {
		String filePath = "";
		sequenceNo = sequenceNo.replaceAll("/", "_");
		if("QUOTE_PRINT".equals(reqFrom)) {
			filePath = "/quotepdf/" + sequenceNo + ".pdf";
		} else if ("DRAFT".equalsIgnoreCase(reqFrom)) {
			filePath = "/quotepdf/" + sequenceNo + ".pdf";
		} else if("SCHEDULE".equals(reqFrom)) {
			if ("ORIGINAL COPY".equalsIgnoreCase(displayText)) {
				filePath = "/OriginalCopyPdf/" + sequenceNo + ".pdf";
			} else if ((displayText.trim()).length()<=0) {
				filePath = "/OriginalPdf/" + sequenceNo + ".pdf";
			} else if ("COPY".equalsIgnoreCase(displayText)) {
				filePath = "/duplicatecopypdf/" + sequenceNo + ".pdf";
			} else if ("DRAFT".equalsIgnoreCase(displayText)) {
				filePath = "/quotepdf/" + sequenceNo + ".pdf";
			} else if ("INVALID POLICY".equalsIgnoreCase(displayText)) {
				filePath = "/testpolicypdf/" + sequenceNo + ".pdf";
			} else if ("INVALID DRAFT".equalsIgnoreCase(displayText)) {
				filePath = "/testquotepdf/" + sequenceNo + ".pdf";
			} else {
				filePath = "/debitpdf/" + sequenceNo + ".pdf";
			}
		} else if("RECEIPT".equals(reqFrom)) {
			filePath = "/receiptPdf/" + sequenceNo + ".pdf";
		} else if("MOTOR_FLEET_SCHEDULE".equals(reqFrom)) {
			filePath = "/fleetSchedule/" + sequenceNo + ".pdf";
		} else if("DEBIT".equals(reqFrom)) {
			if ("ORIGINAL COPY".equalsIgnoreCase(displayText)) {
				filePath = "/OriginalCopyPdf/"  + sequenceNo + ".pdf";
			} else if ("COPY".equalsIgnoreCase(displayText)) {
				filePath = "/duplicatecopypdf/" + sequenceNo + ".pdf";
			} else if ("INVALID DEBIT".equalsIgnoreCase(displayText)) {
				filePath = "/testpolicypdf/"  + sequenceNo + ".pdf";
			} else {
				filePath = "/debitpdf/"  + sequenceNo + ".pdf";
			}
		}else if("PolicyWording".equals(reqFrom)) {
			filePath = "/policyWording/" + sequenceNo + ".pdf";
		}
		return filePath;
	}
	
	public String quotePrint(String productId, String schemeId, String contentTypeId, 
			String quoteNo, String branchCode, String belongingBranch) {
		String _filePath = getpdfPath("QUOTE_PRINT", quoteNo, "");
		String realFilePath = getRealPdfPath(_filePath);
		if("3".equals(productId) || "11".equals(productId)) {
			String applicationNo = new finalprint().getApplicationNo1(quoteNo);
			jasperReport.certificatePrint(applicationNo, branchCode, branchCode, realFilePath, productId, "PRINT");
		} else if("65".equals(productId)) {
			jasperReport.motorQuotation(quoteNo, branchCode, realFilePath, "PRINT", productId);
		} else if("30".equals(productId)) {
			jasperReport.homeSchedule(quoteNo, schemeId, contentTypeId, branchCode, "PRINT", realFilePath);
		}
		return _filePath;
	}
	public String schedule(String productId, String schemeId, String contentTypeId, 
			String branchCode, String belongingBranch, String policyNo,
			String displayText, String displayMode ) {
		String _filePath = null;
		if("draftMode".equalsIgnoreCase(displayMode)) {
			_filePath = getpdfPath("DRAFT", policyNo, "");
		} else {
			_filePath = getpdfPath("SCHEDULE", policyNo, displayText);
		}
		String realFilePath = getRealPdfPath(_filePath);
		if("draftMode".equalsIgnoreCase(displayMode) || !new File(realFilePath).exists()) {
			finalprint tempFinalPrint = new finalprint();
			if("3".equals(productId) || "11".equals(productId)) {
				String applicationNo = tempFinalPrint.getApplicationNo(policyNo);
				if(StringUtils.isBlank(applicationNo)){
					applicationNo = new finalprint().getApplicationNo1(policyNo);
				}
				
				jasperReport.certificateSchedule(applicationNo, branchCode, belongingBranch, realFilePath,displayText,"","","Y");
			} else if("65".equals(productId)) {
				String quoteNo = tempFinalPrint.getHomeQuoteNo(policyNo);
				if(StringUtils.isBlank(quoteNo)) {
					quoteNo = policyNo;
				}
				jasperReport.motorSchedule(quoteNo, branchCode, realFilePath, displayText, productId);
			} else if("30".equals(productId)) {
				String quoteNo = tempFinalPrint.getHomeQuoteNo(policyNo);
				if(StringUtils.isBlank(quoteNo)) {
					quoteNo = policyNo;
				}
				jasperReport.homeSchedule(quoteNo, schemeId, contentTypeId, branchCode, displayText, realFilePath);
			}
		}
		return _filePath;
	}
	
	public String receipt(String quoteNo, String productId, String branchCode) {
		String _filePath = getpdfPath("RECEIPT", quoteNo, "");
		String realFilePath = getRealPdfPath(_filePath);
		if(!new File(realFilePath).exists()) {
			jasperReport.getReceipt(quoteNo, productId, branchCode, realFilePath);
		}
		return _filePath;
	}
	
	public String motorFleetSchedule(String quoteNo, String vehicleId, String branchCode, String userType, String loginId) {
		String _filePath = getpdfPath("MOTOR_FLEET_SCHEDULE", (quoteNo + "_" + vehicleId) , "");
		String realFilePath = getRealPdfPath(_filePath);
		/*if(!new File(realFilePath).exists()) {*/
			jasperReport.getMotorFleetSchedule(quoteNo, branchCode, "", vehicleId, realFilePath,userType,loginId);
		/*}*/
		return _filePath;
	}
	public String debit(String policyNo, String displayText, String productId, String branchCode) {
		String _filePath = getpdfPath("DEBIT", (policyNo + "Debit") , displayText);
		String realFilePath = getRealPdfPath(_filePath);
		if(!new File(realFilePath).exists()) {
			if("3".equals(productId) || "11".equals(productId)) {
				jasperReport.debitNote(policyNo, branchCode, realFilePath, "Certificate","","","Y");
			} else if("30".equals(productId) || "33".equals(productId) || "65".equals(productId)) {
				finalprint tempFinalPrint = new finalprint();
				String quoteNo = tempFinalPrint.getHomeQuoteNo(policyNo);
				if(StringUtils.isBlank(quoteNo)) {
					quoteNo = policyNo;
				}
				jasperReport.getDebitMotor(quoteNo, productId, branchCode, realFilePath, displayText);
			}
		}
		return _filePath;
	}
	public String getDisplayText(String reqFrom, String userType, String productId, String policyNo) {
		String displayText = "";
		if("Test".equalsIgnoreCase(CommonService.getAppMode())) {
			if("DEBIT".equals(reqFrom)) {
				displayText = "INVALID DEBIT";
			} else if("SCHEDULE".equals(reqFrom)) {
				displayText = "INVALID POLICY";
			} else if("DRAFT".equals(reqFrom)) {
				displayText = "INVALID DRAFT";
			}
		} else {
			if("SCHEDULE".equals(reqFrom)) {
				String clickCountStatus = "";
				if("admin".equalsIgnoreCase(userType)) {
					if("ORIGINAL COPY".equalsIgnoreCase(displayText)) {
						clickCountStatus = "1";
					} else if("COPY".equalsIgnoreCase(displayText)) {
						clickCountStatus = "2";
					} else if("INVALID POLICY".equalsIgnoreCase(displayText)) {
						clickCountStatus = "0";
					} else if("INVALID DEBIT".equalsIgnoreCase(displayText)) {
						clickCountStatus = "0";
					} else if("DEBIT".equalsIgnoreCase(displayText)) {
						clickCountStatus = "0";
					} else {
						clickCountStatus = "0";
					}
				} else {
					clickCountStatus = dao.getPDFStatus(userType, productId, policyNo);
					dao.getUpdatePDFStatus(productId, userType, policyNo, (Integer.valueOf(clickCountStatus)+1) );
				}
				if("0".equals(clickCountStatus)) {
					displayText = "";
				} else if("1".equals(clickCountStatus)) {
					displayText = "ORIGINAL COPY";
				} else {
					displayText = "COPY";
				}
			} else if("DRAFT".equals(reqFrom)) {
				displayText = "DRAFT";
			} else if("QUOTE_PRINT".equals(reqFrom)) {
				displayText = "PRINT";
			}
		}
		return displayText;
	}
	 public InputStream concatPDFs(List<InputStream> streamOfPDFFiles,boolean paginate, String PolicyNo) {
		
		InputStream input=null;
		try{
			List<InputStream> pdfs = streamOfPDFFiles;
			List<PdfReader> readers = new ArrayList<PdfReader>();
			int totalPages = 0;
			Iterator<InputStream> iteratorPDFs = pdfs.iterator();
			while (iteratorPDFs.hasNext()) {
				InputStream pdf = iteratorPDFs.next();
	            PdfReader pdfReader = new PdfReader(pdf);
	            readers.add(pdfReader);
	            totalPages += pdfReader.getNumberOfPages();
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return input;
		
		/*String result="";
		
		Document document = new Document();
		OutputStream output=null;
		try{
			output = new FileOutputStream(result);
            List<InputStream> pdfs = streamOfPDFFiles;
            List<PdfReader> readers = new ArrayList<PdfReader>();
            int totalPages = 0;
            Iterator<InputStream> iteratorPDFs = pdfs.iterator();
            // Create Readers for the pdfs.
            while (iteratorPDFs.hasNext()) {
                InputStream pdf = iteratorPDFs.next();
                PdfReader pdfReader = new PdfReader(pdf);
                readers.add(pdfReader);
                totalPages += pdfReader.getNumberOfPages();
            }
            // Create a writer for the outputstream
            PdfWriter writer = PdfWriter.getInstance(document, output);
            document.open();
            BaseFont bf = BaseFont.createFont(BaseFont.HELVETICA,BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
            PdfContentByte cb = writer.getDirectContent(); // Holds the PDF
            // data
            PdfImportedPage page;
            int currentPageNumber = 0;
            int pageOfCurrentReaderPDF = 0;
            Iterator<PdfReader> iteratorPDFReader = readers.iterator();
            // Loop through the PDF files and add to the output.
            while (iteratorPDFReader.hasNext()) {
                PdfReader pdfReader = iteratorPDFReader.next();
                // Create a new page in the target for each source page.
                while (pageOfCurrentReaderPDF < pdfReader.getNumberOfPages()) {
                    document.newPage();
                    pageOfCurrentReaderPDF++;
                    currentPageNumber++;
                    page = writer.getImportedPage(pdfReader,
                            pageOfCurrentReaderPDF);
                    cb.addTemplate(page, 0, 0);
 
                    // Code for pagination.
                    if (paginate) {
                        cb.beginText();
                        cb.setFontAndSize(bf, 9);
                        cb.showTextAligned(PdfContentByte.ALIGN_CENTER, "Policy No:"+PolicyNo+"  "
                                + currentPageNumber + " of " + totalPages, 500520,
                                5, 0);
                        cb.endText();
                    }
                }
                pageOfCurrentReaderPDF = 0;
            }
            output.flush();
            document.close();
            output.close();
        
		}catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            if (document.isOpen())
	                document.close();
	            try {
	                if (output != null)
	                	output.close();
	            } catch (IOException ioe) {
	                ioe.printStackTrace();
	            }
	        }
	        return output;*/
	    }
	 
}
