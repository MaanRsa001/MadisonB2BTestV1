package com.maan.Health.Pdf;

import java.awt.Color;
import java.io.FileOutputStream;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.StringTokenizer;

import org.apache.commons.lang3.StringUtils;

import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfWriter;
import com.maan.Home.DataManipualtion.HomeAdminReferralBean;
import com.maan.Home.MasterController.NumberToWordBean;
import com.maan.Travel.DAO.TravelBean;
import com.maan.common.LogManager;
import com.maan.common.exception.BaseException;
import com.maan.pdf.PdfPTableCreation;

public class HealthPDFCreatorBean  implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2772262767253918993L;
	final static transient private String ENTER = "- Enter";
	final static transient private String EXIT = "- Exit";
	final static transient private String COPY = "Copy";
	final static transient private String TEST= "Test";
	final static transient private String SCHEDULE  = "Schedule";
	final static transient private String SELECT = "Select";
	final static transient private String BLANKSPACE = "\b\b\b\b\b";
	final static transient private String NULL = "null";
	final static transient private String SMALLSEL = "select";
	final HomeAdminReferralBean adminRef = new HomeAdminReferralBean();
	
	private String QuoteNo	  =	"";
	private String loginId 	  = "";
	//private String approved = "";
	private transient Image jpg1;
	private String headerImagePath = "";
	private String footerImagePath = "";
	private String signedImagePath = "";
	private String filePath     = "";
	private String pdfDisplay   = "";
	private String all			= "";
	private String pid          = "";
	private transient  String pids         = "";
	private String     receiptNo    = "";
	private transient  String receiptDate  = "";
	private transient  String userModeSC   = "";
	private String travelProductId = "";
	private String modeOfPurchase = "";
	private transient String branch = "";
	private transient String currencyType = "";
	private transient final PdfPTableCreation tableCreation;
	private transient final PdfPTableCreation tab;
	private transient PdfWriter writer;
	private transient final TravelBean travelBean;
	private transient final NumberToWordBean numword;
	private String place = "";
	private String branchName = "";
	private String branchAddress = "";
	private String branchAddress1 = "";
	private String currencyName = "";
	private String cid = "";
	private String formatpath="";
	
	public HealthPDFCreatorBean()
	{
		tableCreation = new PdfPTableCreation();
		tab = new PdfPTableCreation();
		travelBean = new TravelBean();
		numword = new NumberToWordBean();
	}
	public void setLoginId(final String loginId){
		this.loginId = loginId;
	}
	public String getLoginId(){
		return this.loginId;
	}
	public void setPid(final String pid){
		this.pid = pid;
	}
	public String getPid(){
		return this.pid;
	}
	public void setQuoteNo(final String QuoteNo){
		this.QuoteNo = QuoteNo;
	}
	public String getQuoteNo(){
		return this.QuoteNo;
	}
	public void setHeaderImagePath(final String headerImagePath){
		this.headerImagePath=headerImagePath;
	}
	public String getHeaderImagePath(){
		return headerImagePath;
	}
	public void setFooterImagePath(final String footerImagePath){
		this.footerImagePath=footerImagePath;
	}
	public void setPdfDisplay(final String pdfDisplay){
		this.pdfDisplay = pdfDisplay;
	}
	public String getPdfDisplay(){
		return this.pdfDisplay;
	}
	public String getFooterImagePath(){
		return footerImagePath;
	}
	public void setFilePath(final String filePath){
		this.filePath = filePath;
	}
	public String getFilePath(){
		return filePath;
	}
	public void setSignedImagePath(final String signedImagePath){
		this.signedImagePath = signedImagePath;
	}
	public String getSignedImagePath(){
		return this.signedImagePath;
	}
	public void setAll(final String all){
		this.all = all;
	}
	public String getAll(){
		return this.all;
	}
	public void setUserModeSC(final String usrModeSC)	{
		this.userModeSC = usrModeSC;
	}
	public String getUserModeSC(){
		return this.userModeSC;
	}
    private      String userType = "";
    public void setUserType(final String userType){
         this.userType = userType;
    }
    public String getUserType(){
         return this.userType;
    }
	public void setReceiptNo(final String receiptNo){
         this.receiptNo = receiptNo;
    }
    public String getReceiptNo(){
         return this.receiptNo;
    }
	public void setTravelProductId(final String travelProductId){
         this.travelProductId = travelProductId;
    }
    public String getTravelProductId(){
         return this.travelProductId;
    }
	public void setModeOfPurchase(final String modeOfPurchase){
         this.modeOfPurchase = modeOfPurchase;
    }
    public String getModeOfPurchase(){
         return this.modeOfPurchase;
    }
	public String getBranch(){
		return this.branch;
	}
	public void setBranch(final String branch){
		this.branch = branch;
	}
	public void setCurrencyType(final String currencyType){
		this.currencyType = currencyType;
	}
	public String getCurrencyType(){
		return this.currencyType;
	}
	public String getFormatpath() {
		return formatpath;
	}
	public void setFormatpath(String formatpath) {
		this.formatpath = formatpath;
	}

	public void writePDF(final String option) throws BaseException,Exception
	{
		LogManager.push("writePDF ");
		LogManager.debug(ENTER);
		try
		{
			NumberFormat fmt   = new DecimalFormat("##,##0");
			String personal[][]          = new String[0][0];
			String insured[][]           = new String[0][0];
			String policyDebit[][]       = new String[0][0];
			String headresDetails[][]    = new String[0][0];
			String name                  = "";
			String policyNo              = "";
			String quote                 = "";
			String debitNo               = "";
			String debitDate             = "";
			double premium               = 0.00;
			String premiums              = "0";
			String actualPid             = "";
			String incepDate             = "";
			String expDate               = "";
			String genDate               = "";
			String incDate				 ="";
			String duration              = "";
			String pname                 = "";
			String covertype			 = "";
			String adminRemarks          = "";
			String schemeId 			 = "";
			//Current Date//
			final Date sysDate           = new Date();
			final SimpleDateFormat year  = new SimpleDateFormat("yyyy",Locale.ENGLISH);
			final SimpleDateFormat month = new SimpleDateFormat("MM",Locale.ENGLISH);
			final SimpleDateFormat day   = new SimpleDateFormat("dd",Locale.ENGLISH);
			final String sysDay          = day.format(sysDate);
			final String sysMonth        = month.format(sysDate);
			final String sysYear         = year.format(sysDate);
			//String sealDate ="00-00-0000";
			String sealDate="";
			//sealDate = sysDay+"-"+sysMonth+"-"+sysYear;
			String broker[][] = new String[0][0];
			String broAdd = "";
			String broComp = "";
			String broCompany = "";
			String travelArea = "";
			String loginusertype = "";
			String userName="";
			String issuers="";
			boolean win=false;
			boolean golf=false;
			boolean medi=false;
			boolean bag=false;
			String traOptStatus="";
			String loginBranch="";
			
			String countryOrigin = "";
			String countryDestn = "";
			String policyFee="";
			
			personal =	travelBean.travelPersonalInfoSelect(getQuoteNo());
			if(personal.length>0)
			{
				name = (personal[0][0]==null?"":(SELECT.equalsIgnoreCase(personal[0][0])?"":personal[0][0]+" ")) + (personal[0][1]==null?"":personal[0][1] + " ") + (personal[0][2]==null?"":personal[0][2]);
				//address = (personal[0][9]==null?"":personal[0][9] + ", ") + (personal[0][10]!=null?personal[0][10]+",\n":"") + (personal[0][13]!=null?((personal[0][13]).equalsIgnoreCase(SELECT)?"":(personal[0][13]+",\n")):"")+"P O Box\b"+(personal[0][12]!=null?personal[0][12]+".":"");
			}
			policyDebit = travelBean.travelPositionMasterSelect(getQuoteNo());
			if(policyDebit.length>0)
			{
				int amendHead = travelBean.getMaxAmendId("TRAVEL_HEADER",getQuoteNo());
				amendHead = amendHead - 1;
				  if(amendHead>0){
				  	policyNo = isNull(policyDebit[0][0],"")+"/"+amendHead;
				  }
				  else{
					policyNo = isNull(policyDebit[0][0]);
				  }
				quote = getQuoteNo();
				premiums 	= isNull(policyDebit[0][1],"0");
				premium 	= Double.parseDouble(isNull(policyDebit[0][1],"0"));
				debitNo 	= isNull(policyDebit[0][2]);
				debitDate 	= isNull(policyDebit[0][3]);
				sealDate= isNull(policyDebit[0][4]);
				incDate 	= isNull(policyDebit[0][5]);
				genDate 	= isNull(policyDebit[0][6]);
				duration 	= isNull(policyDebit[0][7]);
				pids 		= isNull(policyDebit[0][17]);
				String sign = isNull(policyDebit[0][9],"+");
				double excess_premium = Double.parseDouble(isNull(policyDebit[0][10],"0"));
				//schemeId    = isNull(policyDebit[0][17]);
				
				if("-".equalsIgnoreCase(sign)){
					premium = premium - excess_premium;
				}
				else if("+".equalsIgnoreCase(sign)){
					premium = premium + excess_premium;
				}

				premiums 	   = fmt.format(Math.round(premium));
				loginId        = isNull(policyDebit[0][11],loginId);
				//approved       = isNull(policyDebit[0][12],"Nil");
				actualPid      = isNull(policyDebit[0][13],"Nil");
				receiptNo      = isNull(policyDebit[0][14],"");
				receiptDate    = isNull(policyDebit[0][15],"");
				modeOfPurchase = isNull(policyDebit[0][16],"Q");
				policyFee = isNull(policyDebit[0][18],"0");
			}
            pname =	travelBean.getProductNamePDF(pids);
			broker = travelBean.brokerCompanyMasterSelect(loginId);
			covertype = travelBean.getSchemeCoverId(quote,"nameonly");
			adminRemarks = travelBean.getAdminRemarks(getQuoteNo());
			loginusertype = travelBean.getLoginUsertype(loginId);
			traOptStatus = travelBean.getTravelOptionStatus(pids);
			if("User".equalsIgnoreCase(loginusertype)){
				userName = travelBean.getLoginUserNames(loginId);
			}
			if(broker.length>0)
			{
				broAdd = "P O Box\b"+(broker[0][6]==null?"":broker[0][6]+".")+"\n"+(broker[0][1]==null?"":broker[0][1]+", ")+(broker[0][2]==null?"":broker[0][2]+", ")+(broker[0][3]==null?"":broker[0][3]+",\n") + (broker[0][4]==null?"":((SELECT).equalsIgnoreCase(broker[0][4])?"":(broker[0][4]+",\n")))+(broker[0][5]==null?"":((broker[0][5]).equalsIgnoreCase(SELECT)?"":(broker[0][5]+",\n")));
				broCompany = (broker[0][0]==null?"":broker[0][0]+"\n");
				if("User".equalsIgnoreCase(loginusertype) && userName.length() >0 )
				{
					broComp	= userName== null ? "":userName+"\n";
					issuers = isNull(broker[0][0]);
				}
				else
				{
					broComp = (broker[0][7] == null?"":broker[0][7]+"\n");
					issuers = isNull(broker[0][0]);
				}
				loginBranch = broker[0][9]!=null?broker[0][9]:"";
			}
			headresDetails = travelBean.getHeadresDetails(getQuoteNo());
			if(headresDetails.length>0)
			{
				incepDate = isNull(headresDetails[0][4]);
				expDate = isNull(headresDetails[0][3]);
				if("Y".equalsIgnoreCase(headresDetails[0][1])){
					travelArea = "Worldwide Excluding USA & Canada";
				}
				else{
					travelArea = "Worldwide";
				}
				if("Y".equalsIgnoreCase(headresDetails[0][5])){
					win = true;
				}
				if("Y".equalsIgnoreCase(headresDetails[0][6])){
					golf = true;
				}
				if("Y".equalsIgnoreCase(headresDetails[0][7])){
					medi = true;
				}
				if("Y".equalsIgnoreCase(headresDetails[0][8])){
					bag = true;
				}
				
				countryOrigin=headresDetails[0][11]==null?"":headresDetails[0][11];
				countryDestn=headresDetails[0][12]==null?"":headresDetails[0][12];
				
			}
		if(!"Debit".equalsIgnoreCase(option)&&!"PrintQuote".equalsIgnoreCase(option)&&!"Receipt".equalsIgnoreCase(option))
		{
			Document document = new Document(PageSize.A4, 50, 50, 60, 60);
			BaseFont bf = BaseFont.createFont(formatpath, BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
			Font sfont = new Font(bf, 8);
			Font bsfont = new Font(bf, 8, Font.BOLD);
			Font Cfont = new Font(bf, 9, Font.BOLD);
			Font bufont = new Font(bf, 9, Font.UNDERLINE);
			bufont.setStyle(Font.UNDERLINE);
			writer = PdfWriter.getInstance(document, new FileOutputStream(getFilePath()));
			document.open();
			HeaderFooterImage endPage;
			endPage=new HeaderFooterImage();
			endPage.onOpenDocument(writer, document);
			endPage.setFormatpath(formatpath);
			endPage.setOption(option);
			writer.setPageEvent(endPage);
			if("Draft".equalsIgnoreCase(option)){
				endPage.setDisplayText("Draft");
			}
			else if(SCHEDULE.equalsIgnoreCase(option) && !"All".equalsIgnoreCase(getAll()))
			{
				if(TEST.equalsIgnoreCase(userModeSC))	{
					endPage.setDisplayText("Invalid Copy");
				}
				else
				{
					if("".equals(getAll())){
						endPage.setDisplayText(getPdfDisplay());
					}
					else if(getAll().length()>0){
						if("Original".equalsIgnoreCase(getAll())){
							endPage.setDisplayText("");
						}
						if("Original Copy".equalsIgnoreCase(getAll())){
							endPage.setDisplayText("Original Copy");
						}
						if(COPY.equalsIgnoreCase(getAll())){
							endPage.setDisplayText(COPY);
						}
					}
				}
			}
			else if(SCHEDULE.equalsIgnoreCase(option) && "All".equalsIgnoreCase(getAll()))
			{
				if(TEST.equalsIgnoreCase(userModeSC)){
					endPage.setDisplayText("Invalid Copy");
				}
			}
		endPage.setHeaderImagePath(getHeaderImagePath());
		endPage.setFooterImagePath(getFooterImagePath());
		String firstname="",date="",add="",telephone="",mobile="",email="",fax="",civil_id="",occupation="";
		HashMap hast = travelBean.getDetailsForPremiumCalculation(getQuoteNo());
		
		Map hmap=new com.maan.Health.DAO.HealthDAO().getHealthPDFDetails(getQuoteNo());
		
		
        if(hast != null && !hast.isEmpty()){
             date          = (String)hast.get("InceptionDate");
             email         = (String)hast.get("email");
             telephone     = (String)hast.get("telephone");
             mobile        = (String)hast.get("mobile");
             fax           = (String)hast.get("fax");
             firstname     = (String)hast.get("first_name");
             civil_id      =(String)hast.get("civil_id");
             occupation    =(String)hast.get("occupation");
        }
        telephone     = isNull(telephone);
        mobile        = isNull(mobile);
        fax           = isNull(fax);
        firstname     = isNull(firstname);
		
		insured = travelBean.travelInsuredPersonsInfoSelect(QuoteNo,branch);
		tableCreation.setTable(8);
		tableCreation.insertCell(Rectangle.NO_BORDER, 8);
		tableCreation.insertCell(Rectangle.NO_BORDER, 8);
		tableCreation.insertCell(Rectangle.NO_BORDER, 8);
		tableCreation.insertCell(Rectangle.NO_BORDER, 8);
		tableCreation.insertCell(Rectangle.NO_BORDER, 8);
		tableCreation.insertCell(Rectangle.NO_BORDER, 8);
		tableCreation.insertCell(Rectangle.NO_BORDER, 8);
		tableCreation.insertCell(Rectangle.NO_BORDER, 8);
		tableCreation.insertCell(Rectangle.NO_BORDER, 8);
		tableCreation.insertCell(Rectangle.NO_BORDER, 8);
		tableCreation.insertCell(Rectangle.NO_BORDER, 8);
		tableCreation.insertCell(Rectangle.NO_BORDER, 8);
		tableCreation.setTableSpacing(1000f);
		document.add(tableCreation.getTable());
		tableCreation.setTable(10);		
		if(SCHEDULE.equalsIgnoreCase(option)&& !TEST.equalsIgnoreCase(userModeSC))
		{
				tab.setTable(7);
				tab.insertCell("Policy Number /", sfont, Rectangle.NO_BORDER, 3, 0);
				tab.insertCell("عدد سياسة", sfont, Rectangle.NO_BORDER, 3, 1,1);
				tab.insertCell("", sfont, Rectangle.NO_BORDER, 1, 0);
				tableCreation.insertCell(tab.getTable(), Rectangle.BOX, 3, 2);
		        tableCreation.insertCell((hmap.get("POLICY_NO")==null?"":hmap.get("POLICY_NO").toString()),sfont, Rectangle.BOX, 2, 0);
		}
		else if(SCHEDULE.equalsIgnoreCase(option)&& TEST.equalsIgnoreCase(userModeSC))
		{
				tab.setTable(7);
				tab.insertCell("Quote Number /", sfont, Rectangle.NO_BORDER, 3, 0);
				tab.insertCell("عدد اقتباس", sfont, Rectangle.NO_BORDER, 3, 1,1);
				tab.insertCell("", sfont, Rectangle.NO_BORDER, 1, 0);
				tableCreation.insertCell(tab.getTable(), Rectangle.BOX, 3, 2);
		        tableCreation.insertCell((hmap.get("QUOTE_NO")==null?"":hmap.get("QUOTE_NO").toString()),sfont, Rectangle.BOX, 2, 0);
		}
		else if("Draft".equalsIgnoreCase(option))
		{
				tab.setTable(7);
				tab.insertCell("Quote Number /", sfont, Rectangle.NO_BORDER, 3, 0);
				tab.insertCell("عدد اقتباس", sfont, Rectangle.NO_BORDER, 3, 1,1);
				tab.insertCell("", sfont, Rectangle.NO_BORDER, 1, 0);
				tableCreation.insertCell(tab.getTable(), Rectangle.BOX, 3, 2);
		        tableCreation.insertCell((hmap.get("QUOTE_NO")==null?"":hmap.get("QUOTE_NO").toString()),sfont, Rectangle.BOX, 2, 0);
		}
		tab.setTable(8);
		tab.insertCell("Issue Date /", sfont, Rectangle.NO_BORDER, 3, 0);
		tab.insertCell("تاريخ الإصدار", sfont, Rectangle.NO_BORDER, 3, 1,1);
		tab.insertCell("", sfont, Rectangle.NO_BORDER, 2, 0);
		tableCreation.insertCell(tab.getTable(), Rectangle.BOX, 3, 2);
		tableCreation.insertCell((hmap.get("EFFECTIVE_DT")==null?"":hmap.get("EFFECTIVE_DT").toString()),sfont, Rectangle.BOX, 2, 0);
		document.add(tableCreation.getTable());
		tableCreation.setTable(10);		
		tab.setTable(12);
		tab.insertCell("Proposer Details /", Cfont, Rectangle.NO_BORDER, 2, 0);
		tab.insertCell("تفاصيل الاقتراح", Cfont, Rectangle.NO_BORDER, 2, 1,1);
		tab.insertCell("", sfont, Rectangle.NO_BORDER, 8, 0);
		tableCreation.insertCell(tab.getTable(), Rectangle.BOX, 10, 2);
		tab.setTable(7);
		tab.insertCell("Proposer Name /", sfont, Rectangle.NO_BORDER, 2, 0);
		tab.insertCell("الاقتراح اسم", sfont, Rectangle.NO_BORDER, 2, 1,1);
		tab.insertCell("", sfont, Rectangle.NO_BORDER, 3, 0);
		tableCreation.insertCell(tab.getTable(), Rectangle.BOX, 3, 2);
        tableCreation.insertCell((hmap.get("PROP_NAME")==null?"":hmap.get("PROP_NAME").toString()),sfont, Rectangle.BOX, 7, 0);
        /*tab.setTable(7);
		tab.insertCell("Insured / ", sfont, Rectangle.NO_BORDER, 2, 0);
		tab.insertCell("المؤمن له", sfont, Rectangle.NO_BORDER, 2, 1,1);
		tab.insertCell("", sfont, Rectangle.NO_BORDER, 3, 0);
		tableCreation.insertCell(tab.getTable(), Rectangle.BOX, 3, 2);
        tableCreation.insertCell(hb.getInsurerName(),sfont, Rectangle.BOX, 2, 0);*/
		/*tab.setTable(8);
		tab.insertCell("Issue Date /", sfont, Rectangle.NO_BORDER, 3, 0);
		tab.insertCell("تاريخ الإصدار", sfont, Rectangle.NO_BORDER, 3, 1,1);
		tab.insertCell("", sfont, Rectangle.NO_BORDER, 2, 0);
		tableCreation.insertCell(tab.getTable(), Rectangle.BOX, 3, 2);
		tableCreation.insertCell((hmap.get("EFFECTIVE_DT")==null?"":hmap.get("EFFECTIVE_DT").toString()),sfont, Rectangle.BOX, 2, 0);*/
        tab.setTable(9);
		tab.insertCell("Phone Number /", sfont, Rectangle.NO_BORDER, 4, 0);
		tab.insertCell("رقم العاتف", sfont, Rectangle.NO_BORDER, 3, 1,1);
		tab.insertCell("", sfont, Rectangle.NO_BORDER, 2, 0);
		tableCreation.insertCell(tab.getTable(), Rectangle.BOX, 3, 2);
        tableCreation.insertCell((hmap.get("TELEPHONE")==null?"":hmap.get("TELEPHONE").toString()),sfont, Rectangle.BOX, 2, 0);
        tab.setTable(3);
		tab.insertCell("ID/C.R. No /", sfont, Rectangle.NO_BORDER, 1, 0);
		tab.insertCell("رقم الهوية أوالسجل التجاري", sfont, Rectangle.NO_BORDER, 2, 1,1);
		tableCreation.insertCell(tab.getTable(), Rectangle.BOX, 3, 2);
        tableCreation.insertCell((hmap.get("CUSTOMER_SOURCE")==null?"":hmap.get("CUSTOMER_SOURCE").toString()),sfont, Rectangle.BOX, 2, 0);
        tab.setTable(9);
		tab.insertCell("Mobile Number /", sfont, Rectangle.NO_BORDER, 4, 0);
		tab.insertCell("رقم الجوال", sfont, Rectangle.NO_BORDER, 3, 1,1);
		tab.insertCell("", sfont, Rectangle.NO_BORDER, 2, 0);
		tableCreation.insertCell(tab.getTable(), Rectangle.BOX, 3, 2);
        tableCreation.insertCell((hmap.get("MOBILE")==null?"":hmap.get("MOBILE").toString()),sfont, Rectangle.BOX, 2, 0);
        tab.setTable(8);
		tab.insertCell("Fax No /", sfont, Rectangle.NO_BORDER, 2, 0);
		tab.insertCell("رقم الفاكس", sfont, Rectangle.NO_BORDER, 3, 1,1);
		tab.insertCell("", sfont, Rectangle.NO_BORDER, 3, 0);
		tableCreation.insertCell(tab.getTable(), Rectangle.BOX, 3, 2);
        tableCreation.insertCell((hmap.get("FAX")==null?"":hmap.get("FAX").toString()),sfont, Rectangle.BOX, 2, 0);
        tab.setTable(8);
		tab.insertCell("Type of business /", sfont, Rectangle.NO_BORDER, 4, 0);
		tab.insertCell("نوع العمل", sfont, Rectangle.NO_BORDER, 3, 1,1);
		tab.insertCell("", sfont, Rectangle.NO_BORDER, 1, 0);
		tableCreation.insertCell(tab.getTable(), Rectangle.BOX, 3, 2);
        tableCreation.insertCell((hmap.get("OCCUPATION_NAME")==null?"":hmap.get("OCCUPATION_NAME").toString()),sfont, Rectangle.BOX, 2, 0);
        tab.setTable(8);
		tab.insertCell("E-mail /", sfont, Rectangle.NO_BORDER, 2, 0);
		tab.insertCell("البريد الإليكنروني", sfont, Rectangle.NO_BORDER, 3, 1,1);
		tab.insertCell("", sfont, Rectangle.NO_BORDER, 3, 0);
		tableCreation.insertCell(tab.getTable(), Rectangle.BOX,3, 2);
		tab.insertCell("", sfont, Rectangle.NO_BORDER, 1, 0);
        tableCreation.insertCell((hmap.get("EMAIL")==null?"":hmap.get("EMAIL").toString()),sfont, Rectangle.BOX, 2, 0);
        tableCreation.setTableSpacing(25f);
		document.add(tableCreation.getTable());
		tableCreation.setTable(10);		
		tab.setTable(12);
		tab.insertCell("Insured Details /", Cfont, Rectangle.NO_BORDER, 2, 0);
		tab.insertCell("تفاصيل المؤمن", Cfont, Rectangle.NO_BORDER, 2, 1,1);
		tab.insertCell("", sfont, Rectangle.NO_BORDER, 8, 0);
		tableCreation.insertCell(tab.getTable(), Rectangle.BOX, 10, 2);
		tab.setTable(7);
		tab.insertCell("Insured /", sfont, Rectangle.NO_BORDER, 2, 0);
		tab.insertCell("المؤمن له", sfont, Rectangle.NO_BORDER, 2, 1,1);
		tab.insertCell("", sfont, Rectangle.NO_BORDER, 3, 0);
		tableCreation.insertCell(tab.getTable(), Rectangle.BOX, 3, 2);
        tableCreation.insertCell((hmap.get("TITLE")==null?"":hmap.get("TITLE").toString())+" "+(hmap.get("INS_NAME")==null?"":hmap.get("INS_NAME").toString()),sfont, Rectangle.BOX, 2, 0);
        tab.setTable(3);
		tab.insertCell("ID/C.R. No /", sfont, Rectangle.NO_BORDER, 1, 0);
		tab.insertCell("رقم الهوية أوالسجل التجاري", sfont, Rectangle.NO_BORDER, 2, 1,1);
		tableCreation.insertCell(tab.getTable(), Rectangle.BOX, 3, 2);
        tableCreation.insertCell((hmap.get("CIVIL_ID")==null?"":hmap.get("CIVIL_ID").toString()),sfont, Rectangle.BOX, 2, 0);
        tab.setTable(3);
		tab.insertCell("DOB/Age /", sfont, Rectangle.NO_BORDER, 1, 0);
		tab.insertCell("تاريخ الميلاد/ العمر", sfont, Rectangle.NO_BORDER, 2, 1,1);
		tableCreation.insertCell(tab.getTable(), Rectangle.BOX, 3, 2);
        tableCreation.insertCell((hmap.get("DOB")==null?"":hmap.get("DOB").toString())+" ("+(hmap.get("AGE")==null?"":hmap.get("AGE").toString())+")",sfont, Rectangle.BOX, 2, 0);
        
        tab.setTable(3);
		tab.insertCell("Gender /", sfont, Rectangle.NO_BORDER, 1, 0);
		tab.insertCell("جنس", sfont, Rectangle.NO_BORDER, 2, 1,1);
		tableCreation.insertCell(tab.getTable(), Rectangle.BOX, 3, 2);
        tableCreation.insertCell((hmap.get("GENDER")==null?"":hmap.get("GENDER").toString()),sfont, Rectangle.BOX, 2, 0);
        tab.setTable(3);
		tab.insertCell("Marital Status /", sfont, Rectangle.NO_BORDER, 1, 0);
		tab.insertCell("الحالة الاجتماعية", sfont, Rectangle.NO_BORDER, 2, 1,1);
		tableCreation.insertCell(tab.getTable(), Rectangle.BOX, 3, 2);
        tableCreation.insertCell((hmap.get("MARTIAL_STATUS")==null?"":hmap.get("MARTIAL_STATUS").toString()),sfont, Rectangle.BOX, 2, 0);

        tab.setTable(3);
		tab.insertCell("Occupation /", sfont, Rectangle.NO_BORDER, 1, 0);
		tab.insertCell("احتلال", sfont, Rectangle.NO_BORDER, 2, 1,1);
		tableCreation.insertCell(tab.getTable(), Rectangle.BOX, 3, 2);
        tableCreation.insertCell((hmap.get("OCCUPATION")==null?"":hmap.get("OCCUPATION").toString()),sfont, Rectangle.BOX, 2, 0);
        tab.setTable(3);
		tab.insertCell("Relation /", sfont, Rectangle.NO_BORDER, 1, 0);
		tab.insertCell("علاقة", sfont, Rectangle.NO_BORDER, 2, 1,1);
		tableCreation.insertCell(tab.getTable(), Rectangle.BOX, 3, 2);
        tableCreation.insertCell((hmap.get("RELATION")==null?"":hmap.get("RELATION").toString()),sfont, Rectangle.BOX, 2, 0);
        tab.setTable(3);
		tab.insertCell("Nationality /", sfont, Rectangle.NO_BORDER, 1, 0);
		tab.insertCell("جنسية", sfont, Rectangle.NO_BORDER, 2, 1,1);
		tableCreation.insertCell(tab.getTable(), Rectangle.BOX, 3, 2);
        tableCreation.insertCell((hmap.get("NATIONALITY")==null?"":hmap.get("NATIONALITY").toString()),sfont, Rectangle.BOX, 2, 0);
        tab.setTable(3);
        tab.insertCell("Policy Period /", sfont, Rectangle.NO_BORDER, 3, 0);
		tab.insertCell("فترة الوثيقة", sfont, Rectangle.NO_BORDER, 3, 1,1);
		tab.insertCell("", sfont, Rectangle.NO_BORDER, 2, 0);
		tableCreation.insertCell(tab.getTable(), Rectangle.BOX, 3, 2);
		tab.setTable(7);
		tab.insertCell("From /", sfont, Rectangle.NO_BORDER, 4, 0);
		tab.insertCell("من", sfont, Rectangle.NO_BORDER, 2, 1,1);
		tab.insertCell("", sfont, Rectangle.NO_BORDER, 1, 0);
		tableCreation.insertCell(tab.getTable(), Rectangle.BOX, 1, 2);
		tableCreation.insertCell((hmap.get("INCEPTION_DT")==null?"":hmap.get("INCEPTION_DT").toString()),sfont, Rectangle.BOX, 1, 0);
		tab.setTable(10);
		tab.insertCell("To /", sfont, Rectangle.NO_BORDER, 2, 0);
		tab.insertCell("الى", sfont, Rectangle.NO_BORDER, 2, 1,1);
		tab.insertCell("", sfont, Rectangle.NO_BORDER, 6, 0);
		tableCreation.insertCell(tab.getTable(), Rectangle.BOX, 2, 2);
        tableCreation.insertCell((hmap.get("EXPIRY_DT")==null?"":hmap.get("EXPIRY_DT").toString()),sfont, Rectangle.BOX, 1, 0);
        tab.setTable(4);
		tab.insertCell("Both Dates Inclusive /", sfont, Rectangle.NO_BORDER, 4, 8);
		tab.insertCell("شامل كلا التارخين", sfont, Rectangle.NO_BORDER, 4, 8,1);
		tableCreation.insertCell(tab.getTable(), Rectangle.BOX, 2, 2);
		tab.setTable(5);
		tab.insertCell("Type of Plan /", sfont, Rectangle.NO_BORDER, 2, 0);
		tab.insertCell("نوع التغطية", sfont, Rectangle.NO_BORDER, 2, 1,1);
		tab.insertCell("", sfont, Rectangle.NO_BORDER, 1, 0);
		tableCreation.insertCell(tab.getTable(), Rectangle.BOX, 3, 2);
        tableCreation.insertCell((hmap.get("CLASS_ID")==null?"":hmap.get("CLASS_ID").toString()),sfont, Rectangle.BOX, 7, 0);
        tab.setTable(9);
		tab.insertCell("Geographical Limit /", sfont, Rectangle.NO_BORDER, 5, 0);
		tab.insertCell("الحدود الجغرافية", sfont, Rectangle.NO_BORDER, 4, 1,1);
		tableCreation.insertCell(tab.getTable(), Rectangle.BOX, 3, 2);
        tableCreation.insertCell("",sfont, Rectangle.BOX, 7, 0);
        tab.setTable(5);
		tab.insertCell("Type of Policy /", sfont, Rectangle.NO_BORDER, 2, 0);
		tab.insertCell("نوع الوثيقة", sfont, Rectangle.NO_BORDER, 2, 1,1);
		tab.insertCell("", sfont, Rectangle.NO_BORDER, 1, 0);
		tableCreation.insertCell(tab.getTable(), Rectangle.BOX, 3, 2);
        tableCreation.insertCell((hmap.get("CLASS_ID")==null?"":hmap.get("CLASS_ID").toString()),sfont, Rectangle.BOX, 7, 0);
        tableCreation.setTableSpacing(25f);
		document.add(tableCreation.getTable());
        Font sbfont = new Font(bf, 9);
		tableCreation.setTable(12);
		tab.setTable(12);
		tab.insertCell("Sponsor Details /", sfont, Rectangle.NO_BORDER, 2, 0);
		tab.insertCell("تفاصيل الراعي", sfont, Rectangle.NO_BORDER, 2, 1,1);
		tab.insertCell("", sfont, Rectangle.NO_BORDER, 8, 0);
		tableCreation.insertCell(tab.getTable(), Rectangle.BOX, 12, 2);
		String status = "Y,";
		if(bag){
			status = status+"PS,";
		}
		else{
			status = status+"PD,";
		}
		if(medi){
			status = status+"ES,";
		}
		else
		{
			if(insured.length > 10){
				status = status+"G,";
			}
			else{
				status = status+"ED,";
			}
		}

		if(golf){
			status = status+"GS,";
		}
		else{
			status = status+"GD,";
		}
		if(win){
			status = status+"WS,";
		}
		else{
			status = status+"WD,";
		}
		status = status.substring(0,(status.length()-1));
		status = "'"+status.replaceAll(",","','")+"'";
		tableCreation.setTable(8);
		tableCreation.insertCell("SUMMARY OF POLICY BENEFITS", Cfont, Rectangle.BOX, 8, 0);
		String policyClauses[][] = new String[0][0];
		schemeId = travelBean.getSchemeCoverId(quote,"");
		policyClauses = travelBean.getPolicyClauses(pids,status,schemeId);
		for(int i=0;i<policyClauses.length;i++)
		{
			PdfPTableCreation tableCreation1 = new PdfPTableCreation();
			tableCreation1.setTable(5);
			tableCreation1.insertCell(policyClauses[i][0], bsfont, Rectangle.NO_BORDER, 1, 0);
			if(policyClauses[i][2]==null || NULL.equals(policyClauses[i][2]))
			{
				tableCreation1.insertCell(policyClauses[i][1], sfont, Rectangle.NO_BORDER, 2, 0);
				tableCreation1.insertCell(policyClauses[i][3], sfont, Rectangle.NO_BORDER, 2, 0);
			}
			else if((policyClauses[i][2]).length()>5)
			{
				tableCreation1.insertCell(policyClauses[i][1], sfont, Rectangle.NO_BORDER, 1, 0);
				tableCreation1.insertCell(policyClauses[i][2], sfont, Rectangle.NO_BORDER, 2, 1);
				tableCreation1.insertCell(policyClauses[i][3], sfont, Rectangle.NO_BORDER, 1, 1);
			}
			else if(policyClauses[i][2]!=null && !NULL.equals(policyClauses[i][2]))
			{
				tableCreation1.insertCell(policyClauses[i][1], sfont, Rectangle.NO_BORDER, 2, 0);
				tableCreation1.insertCell(policyClauses[i][2], sfont, Rectangle.NO_BORDER, 1, 1);
				tableCreation1.insertCell(policyClauses[i][3], sfont, Rectangle.NO_BORDER, 1, 1);
			}
			tableCreation.insertCell(tableCreation1.getTable(), Rectangle.BOX, 4, 0);
		}
		tableCreation.insertCell("", bsfont, Rectangle.BOX, 4, 0);
		tableCreation.setTableSpacing(10f);
		document.add(tableCreation.getTable());
		tableCreation.setTable(8);
		tableCreation.insertCell("", bsfont, Rectangle.NO_BORDER, 8, 0);
		tab.setTable(21);
		tab.insertCell("The above limits are on per event &/or aggregate basis / ", sfont, Rectangle.NO_BORDER, 9, 0);
		tab.insertCell("الحالات المذكورة في الأعلى تنطبق على كل حالة واحدة و /أو في المجموع", sfont, Rectangle.NO_BORDER, 10, 1,1);
		tab.insertCell("", sfont, Rectangle.NO_BORDER, 2, 0);
		tableCreation.insertCell(tab.getTable(), Rectangle.NO_BORDER, 8, 2);
		document.add(tableCreation.getTable());
		tableCreation.setTable(8);
		tableCreation.insertCell("SPECIAL CONDITIONS", Cfont, Rectangle.NO_BORDER, 8, 0);
		tableCreation.insertCell(adminRemarks, sfont, Rectangle.NO_BORDER, 8, 0);
		LogManager.info("royal travel pdf test adminRemarks..."+adminRemarks);
		tableCreation.setTableSpacing(5f);
		if(!"".equalsIgnoreCase(adminRemarks.trim())){
			document.add(tableCreation.getTable());
		}
		//String newPremium[][] = travelBean.getNewPremiumDetails(getQuoteNo());
		 if(!"0".equals(Math.round(Double.parseDouble(hmap.get("EXCESS_PREMIUM").toString()))+"")&&!"0".equals(Math.round(Double.parseDouble(hmap.get("POLICY_FEE").toString()))+""))
		 {
			 tableCreation.setTable(12);
		 }else if(!"0".equals(Math.round(Double.parseDouble(hmap.get("EXCESS_PREMIUM").toString()))+"")||!"0".equals(Math.round(Double.parseDouble(hmap.get("POLICY_FEE").toString()))+""))
		 {
			 tableCreation.setTable(9);
		 }else
		 {
			 tableCreation.setTable(6);
		 }
		tab.setTable(4);
		tab.insertCell("Premium", sfont, Rectangle.NO_BORDER, 4, 8);
		tab.insertCell("(contribution)", sfont, Rectangle.NO_BORDER, 4, 8);
		tab.insertCell("القسط ( الإشتراك)", sfont, Rectangle.NO_BORDER, 4, 8,1);
		tableCreation.insertCell(tab.getTable(), Rectangle.BOX, 2, 2);
	    tableCreation.insertCell(""+fmt.format(Math.round(Double.parseDouble(hmap.get("PREMIUM").toString()))),sfont, Rectangle.BOX, 1, 5);
	    if(!"0".equals(Math.round(Double.parseDouble(hmap.get("EXCESS_PREMIUM").toString()))+""))
	    {
		    tab.setTable(4);
		    if("+".equals(hmap.get("EXCESS_SIGN").toString()))
		    {
		    	tab.insertCell("Loading", sfont, Rectangle.NO_BORDER, 4, 8);
		    }else
		    {
		    	tab.insertCell("Discount", sfont, Rectangle.NO_BORDER, 4, 8);
		    }
			tab.insertCell("Premium(SAR)", sfont, Rectangle.NO_BORDER, 4, 8,1);
			tableCreation.insertCell(tab.getTable(), Rectangle.BOX, 2, 2);
     		tableCreation.insertCell(""+fmt.format(Math.round(Double.parseDouble(hmap.get("EXCESS_PREMIUM").toString()))),sfont, Rectangle.BOX, 1, 5);
	    }
	    if(!"0".equals(Math.round(Double.parseDouble(hmap.get("POLICY_FEE").toString()))+""))
	    {
		    tab.setTable(4);
			tab.insertCell("Admin fees (SAR)", sfont, Rectangle.NO_BORDER, 4, 8);
			tab.insertCell("مصاريف الإدارية  بالريال السعودي", sfont, Rectangle.NO_BORDER, 4, 8,1);
			tableCreation.insertCell(tab.getTable(), Rectangle.BOX, 2, 2);
		    tableCreation.insertCell(""+fmt.format(Math.round(Double.parseDouble(hmap.get("POLICY_FEE").toString()))),sfont, Rectangle.BOX, 1, 5);
	    }
	    tab.setTable(4);
		tab.insertCell("Total  (SAR) / ", sfont, Rectangle.NO_BORDER, 4, 8);
		tab.insertCell("المجموع  بالريال السعودي", sfont, Rectangle.NO_BORDER, 4, 8,1);
		tableCreation.insertCell(tab.getTable(), Rectangle.BOX, 2, 2);
	    tableCreation.insertCell(""+fmt.format(Math.round(Double.parseDouble(hmap.get("OVERALL_PREMIUM").toString()))),sfont, Rectangle.BOX, 1, 5);
	    tableCreation.setTableSpacing(20f);
		document.add(tableCreation.getTable());
		tableCreation.setTable(8);
		tableCreation.insertCell("", sfont, Rectangle.NO_BORDER, 8, 0);
		tableCreation.insertCell("Clauses/ Endorsements/ Special Conditions/ Warranties ( as per attached)", sfont, Rectangle.NO_BORDER, 8, 0);
		document.add(tableCreation.getTable());
		jpg1 = Image.getInstance(getSignedImagePath());
		jpg1.scalePercent(30,30);
		LogManager.info("royal travel pdf test broComp..."+broComp);
		String enteredApproved = broComp;
		tableCreation.setTable(8);
		tableCreation.insertCell("Date:\b\b"+(StringUtils.isBlank(sealDate)?incDate:sealDate), sfont, Rectangle.NO_BORDER, 4, 0);
		tableCreation.insertCell("For the Company ", sfont, Rectangle.NO_BORDER, 4, 0);
		tableCreation.insertCell("Place:\b"+place, sfont, Rectangle.NO_BORDER, 4, 0);
		tableCreation.insertCell(jpg1, Rectangle.NO_BORDER, 4, 0);
		tableCreation.insertCell("User:\b\b"+enteredApproved, sfont, Rectangle.NO_BORDER, 4, 0);
		tableCreation.insertCell(branchName, sfont, Rectangle.NO_BORDER, 4, 0);
		tableCreation.insertCell("", sfont, Rectangle.NO_BORDER, 4, 0);
		tableCreation.insertCell(branchAddress, sfont, Rectangle.NO_BORDER, 4, 0);
		tableCreation.insertCell("", sfont, Rectangle.NO_BORDER, 4, 0);
		tableCreation.insertCell(branchAddress1, sfont, Rectangle.NO_BORDER, 4, 0);
		tableCreation.setTableSpacing(20f);
		document.add(tableCreation.getTable());
		Paragraph tespara1 =  new Paragraph();
		tespara1.add(new Phrase("This insurance is issued by "+issuers+" and underwritten by Madison Insurance Company. All claims under the policy should be directed to and will be handled by Saudi United Cooperative Insurance Company.", sfont));//by "+("153".equals(cid)?"Al Alamiya":"XYZ")+" Insurance."
		tespara1.setAlignment(Element.ALIGN_JUSTIFIED);
		document.add(tespara1);
		document.close();
	}
	if("Debit".equalsIgnoreCase(option)&&!"Receipt".equalsIgnoreCase(option)&&!"PrintQuote".equalsIgnoreCase(option))
	{
			//Document document = new Document(PageSize.A4, 50, 50, 120, 60);
			Document document = new Document(PageSize.A4, 50, 50, 60, 60);
			double commision = 0.00;
			double com = 0.00;
			writer = PdfWriter.getInstance(document, new FileOutputStream(getFilePath()));
			document.open();
			HeaderFooterImage endPage;
			endPage=new HeaderFooterImage();
			endPage.onOpenDocument(writer, document);
			writer.setPageEvent(endPage);
			endPage.setFormatpath(formatpath);
			endPage.setHeaderImagePath(getHeaderImagePath());
			endPage.setFooterImagePath(getFooterImagePath());
			endPage.setOption(option);
			endPage.setDisplayText("");
			com = Double.parseDouble(travelBean.getCommision(loginId,actualPid));
			if(com != 0 && premium != 0)
			{
				commision = premium*com/100;
			}
			BaseFont bf = BaseFont.createFont(formatpath, BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
				Font sfont = new Font(bf, 8);
				Font bfont = new Font(Font.HELVETICA, 8, Font.BOLD);
				Font font1 = new Font(Font.HELVETICA, 9, Font.BOLD);
				font1.setStyle(Font.UNDERLINE);
				tableCreation.setTable(8);
				tableCreation.insertCell(Rectangle.NO_BORDER, 8);
				tableCreation.insertCell(Rectangle.NO_BORDER, 8);
				tableCreation.insertCell(Rectangle.NO_BORDER, 8);
				tableCreation.insertCell(Rectangle.NO_BORDER, 8);
				/*tableCreation.insertCell(Rectangle.NO_BORDER, 8);
				tableCreation.insertCell(Rectangle.NO_BORDER, 8);
				tableCreation.insertCell(Rectangle.NO_BORDER, 8);
				tableCreation.insertCell(Rectangle.NO_BORDER, 8);
				tableCreation.insertCell(Rectangle.NO_BORDER, 8);
				tableCreation.insertCell(Rectangle.NO_BORDER, 8);
				tableCreation.insertCell(Rectangle.NO_BORDER, 8);
				tableCreation.insertCell(Rectangle.NO_BORDER, 8);
				tableCreation.insertCell(Rectangle.NO_BORDER, 8);
				tableCreation.insertCell(Rectangle.NO_BORDER, 8);
				tableCreation.insertCell(Rectangle.NO_BORDER, 8);
				tableCreation.insertCell(Rectangle.NO_BORDER, 8);
				tableCreation.insertCell(Rectangle.NO_BORDER, 8);
				tableCreation.insertCell(Rectangle.NO_BORDER, 8);
				tableCreation.insertCell(Rectangle.NO_BORDER, 8);
				tableCreation.insertCell(Rectangle.NO_BORDER, 8);
				tableCreation.insertCell(Rectangle.NO_BORDER, 8);
				tableCreation.insertCell(Rectangle.NO_BORDER, 8);
				tableCreation.insertCell(Rectangle.NO_BORDER, 8);
				tableCreation.insertCell(Rectangle.NO_BORDER, 8);
				tableCreation.insertCell(Rectangle.NO_BORDER, 8);
				tableCreation.insertCell(Rectangle.NO_BORDER, 8);
				tableCreation.insertCell(Rectangle.NO_BORDER, 8);
				tableCreation.insertCell(Rectangle.NO_BORDER, 8);
				tableCreation.insertCell(Rectangle.NO_BORDER, 8);
				tableCreation.insertCell(Rectangle.NO_BORDER, 8);*/
					tableCreation.setTableSpacing(1000f);
					document.add(tableCreation.getTable());
					tableCreation.setTable(7);
					tableCreation.insertCell(Rectangle.TOP, 7);
					tableCreation.insertCell(Rectangle.NO_BORDER, 3);
					tableCreation.insertCell("DEBIT NOTE", font1, Rectangle.NO_BORDER, 1, 0);
					tableCreation.insertCell(Rectangle.NO_BORDER, 3);
					tableCreation.insertCell(Rectangle.BOTTOM, 7);
					tableCreation.setTableSpacing(30f);
					document.add(tableCreation.getTable());
					tableCreation.setTable(7);
					tableCreation.insertCell("To", sfont, Rectangle.NO_BORDER, 5, 0);
					tableCreation.insertCell(Rectangle.NO_BORDER, 2);
					tableCreation.insertCell(Rectangle.NO_BORDER, 5);
					tableCreation.insertCell("Debit Note No :\b"+debitNo, sfont, Rectangle.NO_BORDER, 2, 0);
					tableCreation.insertCell(Rectangle.NO_BORDER, 5);
					tableCreation.insertCell("Debit Note Date :\b"+debitDate, sfont, Rectangle.NO_BORDER, 2, 0);
					tableCreation.insertCell(broCompany+broAdd, sfont, Rectangle.NO_BORDER, 5, 0);
					tableCreation.insertCell(Rectangle.NO_BORDER, 2);
					tableCreation.insertCell("", sfont, Rectangle.NO_BORDER, 5, 0);
					tableCreation.insertCell(Rectangle.NO_BORDER, 2);
					tableCreation.insertCell("", sfont, Rectangle.NO_BORDER, 5, 0);
					tableCreation.insertCell(Rectangle.NO_BORDER, 2);
					tableCreation.setTableSpacing(10f);
					document.add(tableCreation.getTable());
					tableCreation.setTable(7);
					tableCreation.insertCell("INSURED :", sfont, Rectangle.NO_BORDER, 1, 0);
					tableCreation.insertCell(name, sfont, Rectangle.NO_BORDER, 6, 0);
					tableCreation.insertCell("In accordance with your instruction we have issued the attached documentation and debited your account as per details\nshown hereunder :", sfont, Rectangle.NO_BORDER, 7, 0);
					tableCreation.setTableSpacing(50f);
					document.add(tableCreation.getTable());
					tableCreation.setTable(7);
					tableCreation.insertCell("DESCRIPTION", bfont, Rectangle.TOP, 6, 0);
					tableCreation.insertCell("AMOUNT In "+currencyType, bfont, Rectangle.TOP, 1, 0);
					if(TEST.equalsIgnoreCase(userModeSC)){
						tableCreation.insertCell("BEING THE PREMIUM DUE ON TRAVEL INSURANCE QUOTE NO:\b"+quote, sfont, Rectangle.TOP, 6, 0);
					}
					else{
						tableCreation.insertCell("BEING THE PREMIUM DUE ON TRAVEL INSURANCE POLICY NO:\b"+policyNo, sfont, Rectangle.TOP, 6, 0);
					}
					tableCreation.insertCell(fmt.format(premium), sfont, Rectangle.TOP, 1, 1);
					tableCreation.insertCell(Rectangle.NO_BORDER, 7);
					if(com==0){
						tableCreation.insertCell("", sfont, Rectangle.NO_BORDER, 6, 0);
						tableCreation.insertCell("", sfont, Rectangle.NO_BORDER, 1, 1);
					}
					else{
						tableCreation.insertCell("Less Commision @ "+fmt.format(com)+"%", sfont, Rectangle.NO_BORDER, 6, 0);
						tableCreation.insertCell(fmt.format(commision), sfont, Rectangle.NO_BORDER, 1, 1);
					}
					if(!"0".equals(policyFee)){
						tableCreation.insertCell("Admin Fees", sfont, Rectangle.BOTTOM, 6, 0);
						tableCreation.insertCell(fmt.format(Double.parseDouble(policyFee)), sfont, Rectangle.BOTTOM, 1, 1);
					}
					tableCreation.insertCell(Rectangle.NO_BORDER, 7);
					String wordPre="";
					String wordPreStr = Double.toString(Math.round(premium-commision+Double.parseDouble(policyFee)));
					StringTokenizer str = new StringTokenizer(wordPreStr,".");
					if(str.hasMoreTokens())	{
						wordPre = numword.convertNumToWord(Integer.parseInt(str.nextToken()));
					}
					tableCreation.insertCell(currencyName+"\b"+wordPre+"\bOnly", sfont, Rectangle.NO_BORDER, 5, 0);
					tableCreation.insertCell("Total:", sfont, Rectangle.NO_BORDER, 1, 0);
					tableCreation.insertCell(fmt.format((premium-commision+Double.parseDouble(policyFee))), sfont, Rectangle.NO_BORDER, 1, 1);
					tableCreation.insertCell(Rectangle.BOTTOM, 7);
					tableCreation.setTableSpacing(10f);
					document.add(tableCreation.getTable());
					tableCreation.setTable(7);
					tableCreation.insertCell("PLEASE NOTE :", sfont, Rectangle.NO_BORDER, 2, 0);
					tableCreation.insertCell(Rectangle.NO_BORDER, 5);
					tableCreation.insertCell("Your remittance in respect of the above transaction should be forwarded to us in order to ensure continutity of cover", sfont, Rectangle.NO_BORDER, 7, 0);
					tableCreation.insertCell("We would appreciate you contacting us immediately if you have any queries relating to the above details\nor the attached documents", sfont, Rectangle.NO_BORDER, 7, 0);
					tableCreation.setTableSpacing(10f);
					document.add(tableCreation.getTable());
					tableCreation.setTable(7);
					tableCreation.insertCell("For : "+branchName, sfont, Rectangle.NO_BORDER, 4, 0);
					tableCreation.insertCell(Rectangle.NO_BORDER, 3);
					jpg1 = Image.getInstance(getSignedImagePath());
					jpg1.scalePercent(30,30);
					int align=2;
					if(cid.equals("153")||cid.equals("14")){
						align = 0;
					}
					tableCreation.insertCell(jpg1, Rectangle.NO_BORDER, 4, align);
					tableCreation.insertCell(Rectangle.NO_BORDER, 3);
					tableCreation.insertCell("Authorised Signatory", sfont, Rectangle.NO_BORDER, 4, align);
					tableCreation.insertCell(Rectangle.NO_BORDER, 3);
					tableCreation.setTableSpacing(55f);
					document.add(tableCreation.getTable());
			document.close();
		}
	if("Receipt".equalsIgnoreCase(option)&&!"PrintQuote".equalsIgnoreCase(option))
	{
			//Document document = new Document(PageSize.A4, 50, 50, 120, 60);
			Document document = new Document(PageSize.A4, 50, 50, 60, 60);
			writer = PdfWriter.getInstance(document, new FileOutputStream(getFilePath()));
			document.open();
			HeaderFooterImage endPage;
			endPage=new HeaderFooterImage();
			endPage.onOpenDocument(writer, document);
			writer.setPageEvent(endPage);
			endPage.setFormatpath(formatpath);
			endPage.setHeaderImagePath(getHeaderImagePath());
			endPage.setFooterImagePath(getFooterImagePath());
			endPage.setOption(option);
			endPage.setDisplayText("");
			Font sfont = new Font(Font.HELVETICA, 8);
			Font bfont = new Font(Font.HELVETICA, 8, Font.BOLD);
			Font font1 = new Font(Font.HELVETICA, 9, Font.BOLD);
				tableCreation.setTable(8);
				tableCreation.insertCell(Rectangle.NO_BORDER, 8);
				tableCreation.insertCell(Rectangle.NO_BORDER, 8);
				tableCreation.insertCell(Rectangle.NO_BORDER, 8);
				tableCreation.insertCell(Rectangle.NO_BORDER, 8);
			/*	tableCreation.insertCell(Rectangle.NO_BORDER, 8);
				tableCreation.insertCell(Rectangle.NO_BORDER, 8);
				tableCreation.insertCell(Rectangle.NO_BORDER, 8);
				tableCreation.insertCell(Rectangle.NO_BORDER, 8);
				tableCreation.insertCell(Rectangle.NO_BORDER, 8);
				tableCreation.insertCell(Rectangle.NO_BORDER, 8);
				tableCreation.insertCell(Rectangle.NO_BORDER, 8);
				tableCreation.insertCell(Rectangle.NO_BORDER, 8);
				tableCreation.insertCell(Rectangle.NO_BORDER, 8);
				tableCreation.insertCell(Rectangle.NO_BORDER, 8);
				tableCreation.insertCell(Rectangle.NO_BORDER, 8);
				tableCreation.insertCell(Rectangle.NO_BORDER, 8);
				tableCreation.insertCell(Rectangle.NO_BORDER, 8);
				tableCreation.insertCell(Rectangle.NO_BORDER, 8);
				tableCreation.insertCell(Rectangle.NO_BORDER, 8);
				tableCreation.insertCell(Rectangle.NO_BORDER, 8);*/
				tableCreation.setTableSpacing(1000f);
					document.add(tableCreation.getTable());
					tableCreation.setTable(7);
					tableCreation.insertCell(Rectangle.TOP, 7);
					tableCreation.insertCell(Rectangle.NO_BORDER, 3);
					tableCreation.insertCell("RECEIPT", font1, Rectangle.NO_BORDER, 1, 0);
					tableCreation.insertCell(Rectangle.NO_BORDER, 3);
					tableCreation.insertCell(Rectangle.NO_BORDER, 7);
					tableCreation.insertCell(Rectangle.TOP, 7);
					tableCreation.setTableSpacing(30f);
					document.add(tableCreation.getTable());
					tableCreation.setTable(7);
					tableCreation.insertCell("RECEIVED FROM", font1, Rectangle.NO_BORDER, 2, 0);
					tableCreation.insertCell(name, font1, Rectangle.NO_BORDER, 3, 0);
					tableCreation.insertCell("Receipt No :", sfont, Rectangle.NO_BORDER, 1, 0);
					tableCreation.insertCell(receiptNo, font1, Rectangle.NO_BORDER, 1, 0);
					tableCreation.insertCell(Rectangle.NO_BORDER, 5);
					tableCreation.insertCell("Date :", sfont, Rectangle.NO_BORDER, 1, 0);
					tableCreation.insertCell(receiptDate, font1, Rectangle.NO_BORDER, 1, 0);
					tableCreation.insertCell("", sfont, Rectangle.NO_BORDER, 5, 0);
					tableCreation.insertCell(Rectangle.NO_BORDER, 2);
					tableCreation.insertCell("", sfont, Rectangle.NO_BORDER, 5, 0);
					tableCreation.insertCell(Rectangle.NO_BORDER, 2);
					tableCreation.setTableSpacing(10f);
					document.add(tableCreation.getTable());
					String wordPre="";
					String wordPreStr = Long.toString(Math.round(premium+Double.parseDouble(policyFee)));
					StringTokenizer str = new StringTokenizer(wordPreStr,".");
					if(str.hasMoreTokens())
					{
						wordPre = numword.convertNumToWord(Integer.parseInt(str.nextToken()));
					}
					tableCreation.setTable(7);
					tableCreation.insertCell("Amount :", sfont, Rectangle.NO_BORDER, 1, 0);
					tableCreation.insertCell(currencyType+"\b"+fmt.format(premium+Double.parseDouble(policyFee)), font1, Rectangle.NO_BORDER, 6, 0);
					tableCreation.insertCell("", sfont, Rectangle.NO_BORDER, 1, 0);
					tableCreation.insertCell(currencyName+"\b"+wordPre+"\bOnly", sfont, Rectangle.NO_BORDER, 6, 0);
					tableCreation.setTableSpacing(50f);
					document.add(tableCreation.getTable());
					if("C".equalsIgnoreCase(modeOfPurchase)){
						modeOfPurchase = "Cash";
					}
					else if("D".equalsIgnoreCase(modeOfPurchase)){
						modeOfPurchase = "Credit Card";
					}
					else if("Q".equalsIgnoreCase(modeOfPurchase)){
						modeOfPurchase = "Cheque";
					}
					tableCreation.setTable(7);
					tableCreation.insertCell("PARTICULARS OF RECEIPTS:", font1, Rectangle.NO_BORDER, 2, 0);
					tableCreation.insertCell(modeOfPurchase, font1, Rectangle.NO_BORDER, 2, 0);
					tableCreation.insertCell("", sfont, Rectangle.NO_BORDER, 2, 0);
					tableCreation.insertCell(debitDate, font1, Rectangle.NO_BORDER, 1, 0);
					tableCreation.setTableSpacing(50f);
					document.add(tableCreation.getTable());
					tableCreation.setTable(7);
					tableCreation.insertCell("DESCRIPTION", bfont, Rectangle.TOP, 5, 0);
					tableCreation.insertCell("AMOUNT In "+currencyType, bfont, Rectangle.TOP, 2, 1);
					if(TEST.equalsIgnoreCase(userModeSC)){
						tableCreation.insertCell("BEING THE PREMIUM COLLECTED ON "+adminRef.getProductName(pid, branch)+" QUOTE NO:\b"+quote, sfont, Rectangle.TOP, 5, 0);
					}
					else{
						tableCreation.insertCell("BEING THE PREMIUM COLLECTED ON "+adminRef.getProductName(pid, branch)+" POLICY NO:\b"+policyNo, sfont, Rectangle.TOP, 5, 0);
					}
					tableCreation.insertCell(fmt.format(premium), sfont, Rectangle.TOP, 2, 1);
					if(!"0".equals(policyFee)){
						tableCreation.insertCell(Rectangle.NO_BORDER, 7);
						tableCreation.insertCell("ADMIN FEES", sfont, Rectangle.NO_BORDER, 5, 0);
						tableCreation.insertCell(fmt.format(Double.parseDouble(policyFee)), sfont, Rectangle.NO_BORDER, 2, 1);
					}
					tableCreation.insertCell(Rectangle.NO_BORDER, 7);
					tableCreation.insertCell(Rectangle.BOTTOM, 7);
					tableCreation.insertCell("", sfont, Rectangle.NO_BORDER, 5, 0);
					tableCreation.insertCell("Total :", sfont, Rectangle.NO_BORDER, 1, 1);
					tableCreation.insertCell(fmt.format(premium+Double.parseDouble(policyFee)), font1, Rectangle.NO_BORDER, 1, 1);
					tableCreation.insertCell(Rectangle.BOTTOM, 7);
					tableCreation.setTableSpacing(50f);
					document.add(tableCreation.getTable());
					tableCreation.setTable(7);
					tableCreation.insertCell("For : "+branchName, font1, Rectangle.NO_BORDER, 4, 0);
					tableCreation.insertCell(Rectangle.NO_BORDER, 3);
						jpg1 = Image.getInstance(getSignedImagePath());
						jpg1.scalePercent(30,30);
						int align=2;
						if(cid.equals("153")||cid.equals("14")){
							align = 0;
						}
					tableCreation.insertCell(jpg1, Rectangle.NO_BORDER, 4, align);
					tableCreation.insertCell(Rectangle.NO_BORDER, 3);
					tableCreation.insertCell("Authorised Signatory", sfont, Rectangle.NO_BORDER, 4, align);
					tableCreation.insertCell(Rectangle.NO_BORDER, 3);
					tableCreation.setTableSpacing(55f);
					document.add(tableCreation.getTable());
					broComp = broComp.trim();
					tableCreation.setTable(7);
					tableCreation.insertCell("Printed on:", sfont, Rectangle.NO_BORDER, 1, 0);
					tableCreation.insertCell(sealDate, font1, Rectangle.NO_BORDER, 6, 0);
					tableCreation.insertCell("Printed By:", sfont, Rectangle.NO_BORDER, 1, 0);
					tableCreation.insertCell("( "+broComp+" - "+("134".equals(cid)?"Wala'a":"XYZ Insurance")+" "+place+" )", sfont, Rectangle.NO_BORDER, 6, 0);
					tableCreation.setTableSpacing(50f);
					document.add(tableCreation.getTable());
					document.close();
		}
       if("PrintQuote".equalsIgnoreCase(option))
	   {
            String[] names         = new String[0];
	        String[] rel           = new String[0];
            String[] nationality   = new String[0];
	        String[] genders       = new String[0];
            String[] dob         = new String[0];
            int[] age          	   = new int[0];
            String covperiod       = "";
            String effdate       = "";
            String usaext        = "";
            String winext        = "";
            String winext1        = "";
            String golfext       = "";
            String golfext1       = "";
            String claim         = "";
            String date          = "";
            String email         = "";
            String efectdate     = "";
            String address1      = "";
            String address2      = "";
            String pobox         = "";
            String emirate       = "";
            String telephone     = "";
            String mobile        = "";
            String fax           = "";
            String firstname     = "";
            String companyname   = "";
            String expdate       = "";
            String medicalexl    = "";
            String bagagexl      = "";
			String medicalexl1    = "";
            String bagagexl1     = "";
            String productId     = "";
            String travelOption = "";
            String terrorism    = "";
            String terrorism1    = "";
            String oldage        = "";
		    String treatment     = "";
		    String medical       = "";
            String customer_nation = "";
            String memoranda = "";
            String referalChecking = "";
            //Document document = new Document(PageSize.A4, 50, 50, 120, 60);
            Document document = new Document(PageSize.A4, 50, 50, 60, 60);
            HashMap hast = new HashMap();

			HashMap travelCovers = new HashMap();  //Visa Travel Covers
			String mediCover ="";
			String BaggCover ="";
			String winterCover ="";
			String GolfCover ="";
			String terrorCover ="";
			travelCovers = travelBean.getTravelCovers(getQuoteNo());
			if(travelCovers != null && !travelCovers.isEmpty())
			{
				 mediCover =(String)travelCovers.get("TC"+1);
				 BaggCover =(String)travelCovers.get("TC"+2);
				 winterCover =(String)travelCovers.get("TC"+3);
				 GolfCover =(String)travelCovers.get("TC"+4);
				 terrorCover =(String)travelCovers.get("TC"+5);
			}


			hast = travelBean.getDetailsForPremiumCalculation(getQuoteNo());
            if(hast != null && !hast.isEmpty()){
                 names         = (String[])hast.get("names");
	             rel           = (String[])hast.get("rel");
	             nationality   = (String[])hast.get("nationalities");
	             genders       = (String[])hast.get("genders");
                 age          	= (int[])hast.get("age");
                 covperiod     = (String)hast.get("covperiod");
                 effdate       = (String)hast.get("effdate");
                 usaext        = (String)hast.get("usaext");
                 winext        = (String)hast.get("winext");
                 golfext       = (String)hast.get("golfext");
                 claim         = (String)hast.get("claim");
                 date          = (String)hast.get("InceptionDate");
                 email         = (String)hast.get("email");
                 efectdate     = (String)hast.get("efectdate");
                 address1      = (String)hast.get("address1");
                 address2      = (String)hast.get("address2");
                 pobox         = "P.O.Box "+(String)hast.get("pobox");
                 emirate       = (String)hast.get("emirate");
                 telephone     = (String)hast.get("telephone");
                 mobile        = (String)hast.get("mobile");
                 fax           = (String)hast.get("fax");
                 dob           = (String[])hast.get("dob");
                 firstname     = (String)hast.get("first_name");
                 companyname   = (String)hast.get("company_name");
                 expdate       = (String)hast.get("expDATE");
                 medicalexl    = (String)hast.get("medicalexl");
                 bagagexl      = (String)hast.get("bagagexl");
                 productId     = (String)hast.get("ProductId");
                 terrorism     = (String)hast.get("TerrorismExtension");
                 oldage        = (String)hast.get("oldage");
		         treatment     = (String)hast.get("treatment");
		         medical       = (String)hast.get("medical");
                 customer_nation= (String)hast.get("nationality");
                  memoranda      = (String)hast.get("memoranda");
                 referalChecking = (String)hast.get("ReferalChecking");
            }
            names         = names == null?new String[0]:names;
            dob           = dob == null?new String[0]:dob;
	        rel           = rel == null?new String[0]:rel;
	        nationality   = nationality == null?new String[0]:nationality;
	        genders       = genders == null?new String[0]:genders;
            covperiod     = isNull(covperiod);
            effdate       = isNull(effdate);
            usaext        = isNull(usaext);
            winext        = isNull(winext);
            golfext       = isNull(golfext);
            claim         = isNull(claim);
            date          = isNull(date);
            email         = isNull(email);
            efectdate     = isNull(efectdate);
            if(address1==null || NULL.equalsIgnoreCase(address1) || "".equals(address1)){
            	address1      = "";
            }
            else{
            	address1      = address1 + ",";
            }
            if(address2==null || NULL.equalsIgnoreCase(address2) || "".equals(address2)){
            	address2 = "";
            }
            else{
            	address2 = address2 + ",";
            }
            if(emirate==null || NULL.equalsIgnoreCase(emirate) || SMALLSEL.equalsIgnoreCase(emirate)){
            	emirate="";
            }
            else{
            	emirate=emirate+".";
            }
            pobox         = isNull(pobox);
            telephone     = isNull(telephone);
            mobile        = isNull(mobile);
            fax           = isNull(fax);
            firstname     = isNull(firstname);
            companyname   = isNull(companyname);
            expdate       = isNull(expdate);
            medicalexl    = isNull(medicalexl);
            bagagexl      = isNull(bagagexl);
            productId     = isNull(productId);
            oldage        = isNull(oldage);
		    treatment     = isNull(treatment);
		    medical       = isNull(medical);
            terrorism     = isNull(terrorism);
            if(memoranda==null || NULL.equalsIgnoreCase(memoranda) || SMALLSEL.equalsIgnoreCase(memoranda)){
            	memoranda="";
            }
            if(customer_nation==null || NULL.equalsIgnoreCase(customer_nation) || "".equals(customer_nation) || SMALLSEL.equalsIgnoreCase(customer_nation)){
            	customer_nation ="";
            }
            referalChecking = isNull(referalChecking);

            if(!"Annual Cover".equalsIgnoreCase(covperiod)){
                 covperiod = covperiod + "  days";
            }
            String add = pobox +"\n\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b" + address1 +"\n\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b"+ emirate;
						writer = PdfWriter.getInstance(document, new FileOutputStream(getFilePath()));
                Font sfont  = new Font(Font.HELVETICA, 9);
			    Font Cfont  = new Font(Font.HELVETICA, 9, Font.BOLD,Color.WHITE);
				document.open();
				HeaderFooterImage endPage;
				endPage=new HeaderFooterImage();
				endPage.onOpenDocument(writer, document);
				endPage.setOption(option);
				endPage.setFormatpath(formatpath);
				writer.setPageEvent(endPage);
				endPage.setDisplayText("");
				endPage.setHeaderImagePath(getHeaderImagePath());
				endPage.setFooterImagePath(getFooterImagePath());
                tableCreation.setTable(8);
				tableCreation.insertCell(Rectangle.NO_BORDER, 8);
				tableCreation.insertCell(Rectangle.NO_BORDER, 8);
				tableCreation.insertCell(Rectangle.NO_BORDER, 8);
				tableCreation.insertCell(Rectangle.NO_BORDER, 8);
			/*	tableCreation.insertCell(Rectangle.NO_BORDER, 8);
				tableCreation.insertCell(Rectangle.NO_BORDER, 8);
				tableCreation.insertCell(Rectangle.NO_BORDER, 8);
				tableCreation.insertCell(Rectangle.NO_BORDER, 8);
				tableCreation.insertCell(Rectangle.NO_BORDER, 8);
				tableCreation.insertCell(Rectangle.NO_BORDER, 8);
				tableCreation.insertCell(Rectangle.NO_BORDER, 8);
				tableCreation.insertCell(Rectangle.NO_BORDER, 8);
				tableCreation.insertCell(Rectangle.NO_BORDER, 8);
				tableCreation.insertCell(Rectangle.NO_BORDER, 8);
				tableCreation.insertCell(Rectangle.NO_BORDER, 8);
				tableCreation.insertCell(Rectangle.NO_BORDER, 8);
				tableCreation.insertCell(Rectangle.NO_BORDER, 8);
				tableCreation.insertCell(Rectangle.NO_BORDER, 8);
				tableCreation.insertCell(Rectangle.NO_BORDER, 8);
				tableCreation.insertCell(Rectangle.NO_BORDER, 8);
				tableCreation.insertCell(Rectangle.NO_BORDER, 8);*/
				tableCreation.setTableSpacing(500f);
				document.add(tableCreation.getTable());
                tableCreation.setTable(8);
                tableCreation.insertCell("CUSTOMER INFORMATION", Cfont, Rectangle.BOX, 4, 0,"BG");
                tableCreation.insertCell("\b\b\b\bCUSTOMER NAME\b\b\b"+firstname,Cfont, Rectangle.BOX, 4, 0,"BG");
                tableCreation.insertCell("Quote Number:\b\b\b\b\b\b"+getQuoteNo(), sfont, Rectangle.BOX, 4, 0);
				tableCreation.insertCell("Date:\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b"+date, sfont, Rectangle.BOX, 4, 0);
				tableCreation.insertCell("Address:\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b"+add, sfont, Rectangle.BOX, 4, 0);
                tableCreation.insertCell("Telephone:\b\b\b\b\b\b\b\b\b"+telephone, sfont, Rectangle.BOX, 4, 0);
                tableCreation.insertCell("Mobile:\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b"+mobile, sfont, Rectangle.BOX, 4, 0);
                tableCreation.insertCell("Email:\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b"+email, sfont, Rectangle.BOX, 4, 0);
                tableCreation.insertCell("Fax:\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b"+fax, sfont, Rectangle.BOX, 4, 0);
                tableCreation.insertCell("", sfont, Rectangle.BOX, 4, 0);
                tableCreation.setTableSpacing(25f);
				document.add(tableCreation.getTable());
                Font sbfont = new Font(Font.HELVETICA, 9);//Font sfont = new Font(Font.HELVETICA, 8);
				tableCreation.setTable(8);
				tableCreation.insertCell("\n", sbfont, Rectangle.NO_BORDER, 8, 0);
				tableCreation.insertCell("INSURED PERSONS", Cfont, Rectangle.BOX, 8, 0,"BG");
				tableCreation.insertCell("NAME", sbfont, Rectangle.BOX, 2, 2);
                tableCreation.insertCell("DOB", sbfont, Rectangle.BOX, 1, 2);
                tableCreation.insertCell("AGE", sbfont, Rectangle.BOX, 1, 2);
				tableCreation.insertCell("GENDER", sbfont, Rectangle.BOX, 1, 2);
				tableCreation.insertCell("RELATION", sbfont, Rectangle.BOX, 1, 2);
                tableCreation.insertCell("NATIONALITY", sbfont, Rectangle.BOX, 2, 2);
                int leg = 0;
                if(names.length > 5){
                    leg =  names.length;
                }
                else{
                     leg = 5;
                }

				for(int i=0;i<leg;i++)
				{
					String relation="";
                    if(i < names.length){
                        if("M".equalsIgnoreCase(genders[i])){
                            genders[i] = "Male";
                        }
                        else if("F".equalsIgnoreCase(genders[i])){
                            genders[i] = "Female";
                        }
                        if("1".equalsIgnoreCase(rel[i].trim())){
                            relation = "Self";
                        }
                        else if("2".equalsIgnoreCase(rel[i].trim())){
                            relation = "Spouse";
                        }
                        else if("3".equalsIgnoreCase(rel[i].trim())){
                            relation = "Child";
                        }
                        else if("4".equalsIgnoreCase(rel[i].trim())){
                            relation = "Father";
                        }
                        else if("5".equalsIgnoreCase(rel[i].trim())){
                            relation = "Mother";
                        }
                        else if("6".equalsIgnoreCase(rel[i].trim())){
                            relation = "Other";
                        }
                        tableCreation.insertCell(names[i], sfont, Rectangle.BOX, 2, 0);
                        tableCreation.insertCell(dob[i], sfont, Rectangle.BOX, 1, 0);
                        tableCreation.insertCell(Integer.toString(age[i]), sfont, Rectangle.BOX, 1, 0);
                        tableCreation.insertCell(genders[i], sfont, Rectangle.BOX, 1, 0);
                        tableCreation.insertCell(relation, sfont, Rectangle.BOX, 1, 0);
                        tableCreation.insertCell(nationality[i], sfont, Rectangle.BOX, 2, 0);
                    }
                    else{
                        tableCreation.insertCell(BLANKSPACE, sbfont, Rectangle.BOX, 2, 0);
                        tableCreation.insertCell(BLANKSPACE, sbfont, Rectangle.BOX, 1, 0);
                        tableCreation.insertCell(BLANKSPACE, sbfont, Rectangle.BOX, 1, 0);
					    tableCreation.insertCell(BLANKSPACE, sbfont, Rectangle.BOX, 1, 0);
                        tableCreation.insertCell(BLANKSPACE, sbfont, Rectangle.BOX, 1, 0);
                        tableCreation.insertCell(BLANKSPACE, sbfont, Rectangle.BOX, 2, 0);
                    }
				}
                tableCreation.setTableSpacing(15f);
				document.add(tableCreation.getTable());
                sfont = new Font(Font.HELVETICA, 8);
				tableCreation.setTable(8);
				tableCreation.insertCell("\n", sbfont, Rectangle.NO_BORDER, 8, 0);
				tableCreation.insertCell("POLICY DETAILS", Cfont, Rectangle.BOX, 8, 0,"BG");
				tableCreation.insertCell("Policy Term:\b\b\b\b\b"+covperiod, sfont, Rectangle.BOX, 2, 0);
                tableCreation.insertCell("Effective Policy Period:\b\b\b\b\b"+effdate, sfont, Rectangle.BOX, 3, 0);
                tableCreation.insertCell("Expiry Date:\b\b\b\b\b"+expdate, sfont, Rectangle.BOX, 3, 0);

				tableCreation.setTableSpacing(15f);
				document.add(tableCreation.getTable());
                if("Y".equalsIgnoreCase(medical)) {
                	medical = "Yes";
                }
                else{
                	medical = "No";
                }
                if("Y".equalsIgnoreCase(treatment)){
                	treatment = "Yes";
                }
                else{
                	treatment = "No";
                }
                if("Y".equalsIgnoreCase(oldage)){
                	oldage = "Yes";
                }
                else{
                	oldage = "No";
                }
                tableCreation.setTable(8);
				tableCreation.insertCell("\n", sbfont, Rectangle.NO_BORDER, 8, 0);
				tableCreation.insertCell("UNDER WRITING QUESTIONS", Cfont, Rectangle.BOX, 8, 0,"BG");
				tableCreation.insertCell("Is there anyone above the age of 75 travelling in your group?", sfont, Rectangle.BOX, 6, 0);
                tableCreation.insertCell(oldage, sfont, Rectangle.BOX, 2, 2);
                tableCreation.insertCell("Does anyone in your group have an existing medical condition? Or has been hospitalised in the last 12 months? Or anticipate any treatment during travel?", sfont, Rectangle.BOX, 6, 0);
				tableCreation.insertCell(treatment, sfont, Rectangle.BOX, 2, 2);
				tableCreation.insertCell("Has anyone in your group made any medical claim or a travel related claim in the last 3 years?", sfont, Rectangle.BOX, 6, 0);
                tableCreation.insertCell(medical, sfont, Rectangle.BOX, 2, 2);
                tableCreation.setTableSpacing(15f);
				document.add(tableCreation.getTable());
                String newPremium[][] = travelBean.getNewPremiumDetails(getQuoteNo());
                double pre  = 0.00;
                double add1 = 0.00;
                double over = 0.00;
                if(newPremium.length>0)
                {
                    pre  = Double.parseDouble(isNull(newPremium[0][0],"0"));
                    add1 = Double.parseDouble(isNull(newPremium[0][1],"0"));
                    over = Double.parseDouble(isNull(newPremium[0][2],"0"));
                    pre  = Math.round(pre);
                    add1 = Math.round(add1);
                    over = Math.round(over);
                }
                tableCreation.setTable(8);
				tableCreation.insertCell("\n", sbfont, Rectangle.NO_BORDER, 8, 0);
                tableCreation.insertCell("PREMIUM", Cfont, Rectangle.BOX, 8, 0,"BG");
                tableCreation.insertCell("Total Premium ["+currencyType+"] ", sfont, Rectangle.BOX, 6, 2);
                tableCreation.insertCell(fmt.format(Math.round(over)), sfont, Rectangle.BOX, 2, 2);
                tableCreation.setTableSpacing(15f);
				document.add(tableCreation.getTable());
                if("s".equalsIgnoreCase(getUserType()) && memoranda.length() > 0){
                        tableCreation.setTable(8);
						tableCreation.insertCell("\n", sbfont, Rectangle.NO_BORDER, 8, 0);
                        tableCreation.insertCell("Special Condition", Cfont, Rectangle.BOX, 8, 0,"BG");
                        tableCreation.insertCell(memoranda,sfont, Rectangle.BOX, 8, 0);
                        tableCreation.setTableSpacing(15f);
                        document.add(tableCreation.getTable());
                }
                else if("admin".equalsIgnoreCase(referalChecking) &&  memoranda.length() > 0){
                        tableCreation.setTable(8);
						tableCreation.insertCell("\n", sbfont, Rectangle.NO_BORDER, 8, 0);
                        tableCreation.insertCell("Special Condition", Cfont, Rectangle.BOX, 8, 0,"BG");
                        tableCreation.insertCell(memoranda,sfont, Rectangle.BOX, 8, 0);
                        tableCreation.setTableSpacing(15f);
                        document.add(tableCreation.getTable());
                }
                String dec_txt = "In addition to any other details supplied to the Insurers, I, the undersigned, declare that to the best of my knowledge and belief the information given by me is true and complete and that all material information has been disclosed and I agree that this application shall be the basis of the contract between me and the insurance company. I further declare that the payment of my premium is made from my own source. I understand and accept that the insurers reserve the right to accept or reject a proposal at their discretion. I will give notice to the company of any change in the information relating to the insured, as stated above. I agree to accept a policy in the Company's usual form for this class of insurance.";
                tableCreation.setTable(8);
				tableCreation.insertCell("\n", sbfont, Rectangle.NO_BORDER, 8, 0);
                tableCreation.insertCell("DECLARATION", Cfont, Rectangle.BOX, 8, 0,"BG");
                tableCreation.insertCell(dec_txt, sfont, Rectangle.BOX, 8, 0);
                tableCreation.insertCell("Date:", sfont, Rectangle.BOX, 1, 2);
                tableCreation.insertCell("\b\b\b", sfont, Rectangle.BOX, 3, 2);
                tableCreation.insertCell("Signature:", sfont, Rectangle.BOX, 1, 2);
                tableCreation.insertCell("\b\b\b\b\b\b", sfont, Rectangle.BOX, 3, 2);
                tableCreation.insertCell("Location:", sfont, Rectangle.BOX, 1, 2);
                tableCreation.insertCell("\b\b\b\b\b\b", sfont, Rectangle.BOX, 7, 2);
                tableCreation.setTableSpacing(15f);
				document.add(tableCreation.getTable());
				document.close();
	   		}

       		LogManager.debug(EXIT);
       		LogManager.popRemove();
		}
		catch(BaseException e){
        	throw new BaseException(e,"Error");
        }

		LogManager.debug(EXIT);
		LogManager.popRemove();
	}

	public String getPDFStatus(final String quoteNo)throws BaseException
	{
		LogManager.push("getPDFStatus ");
		LogManager.debug(ENTER);

		int pdfStatus = travelBean.updateBrokerStatus(quoteNo);
		String resStatus="";
		if(pdfStatus == 0){
			resStatus="";
		}
		else if(pdfStatus == 1){
			resStatus="Original Copy";
		}
		else if(pdfStatus == 2){
			resStatus=COPY;
		}
		else{
			resStatus=COPY;
		}

		LogManager.debug(EXIT);
		LogManager.popRemove();

		return resStatus;
	}

	public String getBranchAddress() {
		return branchAddress;
	}
	public void setBranchAddress(final String branchAddress) {
		this.branchAddress = branchAddress;
	}
	public String getBranchAddress1() {
		return branchAddress1;
	}
	public void setBranchAddress1(final String branchAddress1) {
		this.branchAddress1 = branchAddress1;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(final String branchName) {
		this.branchName = branchName;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(final String place) {
		this.place = place;
	}
	public String getCurrencyName() {
		return currencyName;
	}
	public void setCurrencyName(final String currencyName) {
		this.currencyName = currencyName;
	}
	public String isNull(final String content)throws BaseException{
		final StringBuffer contents = new StringBuffer();
		if(content==null){
			contents.append("");
		}
		else{
			contents.append(content);
		}
		return contents.toString();
	}
	public String isNull(final String content,final String value)throws BaseException{
		final StringBuffer contents = new StringBuffer();
		if(content==null||content.length()<=0){
			contents.append(value);
		}
		else{
			contents.append(content);
		}
		return contents.toString();
	}
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
}
