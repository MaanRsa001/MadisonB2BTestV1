package rsa.pdf;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

import com.maan.common.LogManager;
import com.maan.common.exception.BaseException;
import com.maan.report.service.PdfService;

public class print4Debit extends HttpServlet {

	final static transient private String ENTER = "- Enter";
	final static transient private String EXIT = "- Exit";
	private static final long serialVersionUID = 1030498627115204350L;
	final static transient private String DEBIT = "Debit";
	/*final static transient private String IMG = "/images/";
	final static transient private String POLICYNO = "POLICYNO";
	final static transient private String HUND = "100";
	final static transient private String THOU = "1000";
	final static transient private String TAB = "\t\t";*/

	protected void processRequest(final HttpServletRequest request,final HttpServletResponse response) throws ServletException, IOException,BaseException 
	{
		LogManager.push("One Off print4Debit processRequest method()");
		LogManager.debug(ENTER);
		finalprint finalBean = new finalprint();
		try {
			//PDFDisplay pdis = new PDFDisplay();
			HttpSession session;
			
			String displayText = "";
			String usrModeController = "";

			String brokerBra;
			
			session = request.getSession(false);
			if (session.getAttribute("ses") == null) 
			{
				response.sendRedirect("login/error_messg_pdf.jsp");
				return;
			}
			usrModeController = (String) session.getAttribute("userLoginMode") == null ? ""	: (String) session.getAttribute("userLoginMode");
			if ("".equalsIgnoreCase(usrModeController)	|| " ".equalsIgnoreCase(usrModeController)) {
				response.sendRedirect("login/error_messg_pdf.jsp");
				return;
			}
			com.maan.DBCon.DBConnectionStatus.statusStatic = usrModeController;
			LogManager.info("the user Mode is" + usrModeController);
			displayText = request.getParameter("displayText") == null ? "": request.getParameter("displayText");
			if ("Test".equalsIgnoreCase(usrModeController)&&"".equalsIgnoreCase(displayText)) {
				displayText = "INVALID DEBIT";
			}

			String PolicyNo;
			String loginId;
			PolicyNo = finalBean.isNull(request.getParameter("policynumber"),"0");
			loginId = finalBean.isNull(request.getParameter("loginid"),"");
			String currencyOption = finalBean.isNull(request.getParameter("currencyOption"),"");
			LogManager.info("========loginId is " + loginId);
			LogManager.info("========policynumber   is " + PolicyNo);
			LogManager.info("========currencyOption   is " + currencyOption);

			brokerBra = (String)session.getAttribute("LoginBranchCode");
			/*HashMap brokerDetails;
			double taxPercent = 0;
			String cid="";
			int decimalDigit = 0;
			brokerDetails = (HashMap)session.getAttribute("BrokerDetails");
			if(!brokerDetails.isEmpty())
			{
				cid = (String)brokerDetails.get("Orgination");
				decimalDigit = Integer.parseInt((String)brokerDetails.get("CurrencyDecimal"));
				taxPercent = Double.parseDouble((String)brokerDetails.get("Tax"));
				//currencyType = (String)brokerDetails.get("CurrencyAbb");
			}
			String placeCode[][];
				String headImage;
				String footImage;
				String signImage;
				String currencyType;
				String currencyType1;
				String fillsDigit;
				String fills;
				String braAddress;
				String cols="";
				placeCode = finalBean.getPlaceCodes(PolicyNo,DEBIT,"3",POLICYNO);
				headImage  = placeCode[0][1] == null ? "" : placeCode[0][1];
				footImage  = placeCode[0][2] == null ? "" : placeCode[0][2];
				signImage  = placeCode[0][3] == null ? "" : placeCode[0][3];
				currencyType  = placeCode[0][5] == null ? "" : placeCode[0][5];
				currencyType1  = placeCode[0][6] == null ? "" : placeCode[0][6];
				fills  = placeCode[0][8] == null ? "" : placeCode[0][8];
				fillsDigit  = placeCode[0][9] == null ? "" : placeCode[0][9];
				braAddress = placeCode[0][10] == null ? "" : placeCode[0][10];
				cols = placeCode[0][11] == null ? "" : placeCode[0][11];

				String fontPath;
				fontPath = request.getSession().getServletContext().getRealPath("/" + "ScheduleFont/arial.ttf");
				String urlPath;
				String urlPathFooter;
				urlPath = request.getSession().getServletContext().getRealPath("/" + IMG+headImage);
				urlPathFooter = request.getSession().getServletContext().getRealPath("/" + IMG+footImage);
				String freightUser;
				String freightBroker;
				freightUser = finalBean.isNull((String)session.getAttribute("freight"),"");
				freightBroker = finalBean.isNull((String)session.getAttribute("freightBroker"),"");*/
			response.setContentType("application/pdf");
			String PolicyNoFour = PolicyNo.replaceAll("/", "-");
			String extension = ".pdf";
			String fileName,fileName1;
			if ("ORIGINAL COPY".equalsIgnoreCase(displayText)) {
				fileName = request.getSession().getServletContext().getRealPath("/" + "/OriginalCopyPdf/"  + PolicyNoFour + DEBIT + extension);
				fileName1="/OriginalCopyPdf/"  + PolicyNoFour + DEBIT + extension;
			} else if ("COPY".equalsIgnoreCase(displayText)) {
				fileName = request.getSession().getServletContext().getRealPath("/" + "/duplicatecopypdf/" + PolicyNoFour + DEBIT + extension);
				fileName1="/duplicatecopypdf/" + PolicyNoFour + DEBIT + extension;
			} else if ("INVALID DEBIT".equalsIgnoreCase(displayText)) {
				fileName = request.getSession().getServletContext().getRealPath("/" + "/testpolicypdf/"  + PolicyNoFour + DEBIT + extension);
				fileName1="/testpolicypdf/"  + PolicyNoFour + DEBIT + extension;
			} else {
				fileName = request.getSession().getServletContext().getRealPath("/" + "/debitpdf/"  + PolicyNoFour + DEBIT + extension);
				fileName1="/debitpdf/"  + PolicyNoFour + DEBIT + extension;
			}
			LogManager.info("the X FILE NAME IS " + fileName);
			/*File pdfFile;
				pdfFile = new File(fileName);
				String url=request.getSession().getServletContext().getRealPath("/"+ "/images/");
				PDFCreatorBean creatorBean = new PDFCreatorBean();
				creatorBean.setCurrencyOption(currencyOption);
				creatorBean.setUsrModeController(usrModeController);

				double netPremium=0.0;
				netPremium=finalBean.getnetPremium(PolicyNo);
				if(netPremium>=0){
				creatorBean.writeDebitPDF(urlPath,urlPathFooter,fontPath,cid,decimalDigit,taxPercent,brokerBra,freightBroker,freightUser,loginId,PolicyNo,fileName,url);
				}else{
				creatorBean.writeNewDebitPDF(urlPath,urlPathFooter,fontPath,cid,decimalDigit,taxPercent,brokerBra,freightBroker,freightUser,loginId,PolicyNo,fileName,url);	 
				}*/
			String productId = session.getAttribute("product_id")==null?"":session.getAttribute("product_id").toString();
			com.maan.report.JasperReports jr= new com.maan.report.JasperReports() ;
			if("3".equals(productId) || "11".equals(productId)) {
				jr.debitNote(PolicyNo, brokerBra, fileName, "Certificate","","","Y");
			} else if("30".equals(productId) || "33".equals(productId) || "65".equals(productId)) {
				String quoteNo = finalBean.getHomeQuoteNo(PolicyNo);
				if(StringUtils.isBlank(quoteNo)) {
					quoteNo = PolicyNo;
				}
				jr.getDebitMotor(quoteNo, productId, brokerBra, fileName, displayText);
			}

			//response.sendRedirect(request.getContextPath()+fileName1);
			session.setAttribute("pdfFilePath", fileName1);
			response.sendRedirect(request.getContextPath()+"/pdfReport.action");

		} catch (Exception e) {
			throw new BaseException(e, "Error");
		}
		LogManager.debug(EXIT);
		LogManager.popRemove();

	}

	protected void doGet(final HttpServletRequest request,
			final HttpServletResponse response) throws ServletException, IOException {
		try{
			processRequestNew(request,response);
		}catch(Exception e){
			LogManager.debug(e);
		}
	}
	protected void doPost(final HttpServletRequest request,
			final HttpServletResponse response) throws ServletException, IOException {
		try{
			processRequestNew(request,response);
		}catch(Exception e){
			LogManager.debug(e);
		}
	}
	
	protected void processRequestNew(final HttpServletRequest request, final HttpServletResponse response) 
	throws ServletException, IOException,BaseException {
		HttpSession session = request.getSession(false);
		String usrModeController = session.getAttribute("userLoginMode") == null ? ""	: (String) session.getAttribute("userLoginMode");
		String productId = session.getAttribute("product_id")==null?"":session.getAttribute("product_id").toString();
		String branchCode = (String)session.getAttribute("LoginBranchCode");
		
		String policyNo = StringUtils.isEmpty(request.getParameter("policynumber"))?"0":request.getParameter("policynumber");
		
		String displayText = StringUtils.isEmpty(request.getParameter("displayText"))? "" : request.getParameter("displayText");
		if ("Test".equalsIgnoreCase(usrModeController) && "".equalsIgnoreCase(displayText)) {
			displayText = "INVALID DEBIT";
		}
		
		PdfService pdfService = new PdfService();
		String filePath = pdfService.debit(policyNo, displayText, productId, branchCode);
		session.setAttribute("pdfFilePath", filePath);
		response.sendRedirect(request.getContextPath()+"/pdfReport.action");
	}
	
	public String getServletInfo() {
		return "Short description";
	}
}