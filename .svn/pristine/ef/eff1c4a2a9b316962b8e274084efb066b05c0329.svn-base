package com.maan.Home.MasterController;

import java.awt.Color;
import java.io.FileOutputStream;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.StringTokenizer;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.maan.Home.DataManipualtion.DataSelect;
import com.maan.Home.DataManipualtion.HomeAdminReferralBean;
import com.maan.Home.DataManipualtion.HomePdfDataSelect;
import com.maan.common.LogManager;
import com.maan.common.exception.BaseException;
import com.maan.pdf.PdfPTableCreation;
public class PDFCreatorBean {

	private transient Map CoverageDetails;

	final static transient private String PER_FIRST_NAME = "per.first_name";

	final static transient private String PER_COMPANYNAME = "per.companyName";

	final static transient private String PER_LAST_NAME = "per.last_name";

	final static transient private String PER_TITLE = "per.title";

	final static transient private String SELECT = "Select";

	final static transient private String COVERAGE_DETAILS = "CoverageDetails";

	final static transient private String COVTRA_COVER_ID = "covtra.cover_id";

	final static transient private String COVTRA_PRO_SUM = "covtra.product_suminsured";

	final static transient private String POS_POLICY_NO = "pos.POLICY_NO";

	final static transient private String PER_ADDRESS1 = "per.address1";

	final static transient private String PER_POBOX = "per.pobox";

	final static transient private String PER_EMIRATE = "per.emirate";

	final static transient private String POS_BEDROOMS = "pos.bedrooms";

	final static transient private String DRAFT = "Draft";

	final static transient private String TEST = "Test";

	final static transient private String POLICY_NO = "Policy No: ";

	final static transient private String COVTRA_PREMIUM = "covtra.premium";

	final static transient private String BRO_COMPANY_NAME = "bro.COMPANY_NAME";

	final static transient private String POS_EFF_DATE = "pos.EFFECTIVE_DATE";

	final static transient private String POS_EXPIRY_DATE = "pos.EXPIRY_DATE";

	final static transient private String POS_PREMIUM = "pos.PREMIUM";

	final static transient private String POS_LOGINID = "pos.loginId";

	final static transient private String POS_DEBIT_DATE = "pos.DEBIT_NOTE_DATE";

	final static transient private String YES = "Yes";

	final static transient private String SCHEDULE = "Schedule";

	final static transient private String SPECIAL = "Special";

	private String Data1 = "";

	private String QuoteNo;

	private transient String urlPath = "";

	private transient String urlPathFooter = "";

	private transient String betterImagePath = "";

	private String waterImagePath = "";

	private String loginId = "";

	private String option;

	private transient Image jpg1;

	private String filePath = "";

	private String all = "";

	private String pid = "";

	private String signedImagePath = "";

	private String userModeSC = "";

	private String pdfDisplay = "";

	private String clauses = "";

	private transient Map homePdfDetails;

	private String branch = "";

	private String currencyType = "";

	private String betterRoom = "";

	private String betterContents = "";

	private String betterPremium = "";

	private String betterName = "";

	// Receipt
	private String receiptNo = "";

	private String modeOfPurchase = "";

	private int pdfStatus = 0;

	private transient PdfWriter writer;

	final private transient HomePdfDataSelect pdfDataSelect;

	final private transient DataSelect cover;

	private transient HeaderFooterImage endPage;

	final private transient PdfPTableCreation tableCreation;

	private transient PdfPTableCreation tableCreation1;

	final private transient NumberToWordBean numword;

	private String emptyImage = "";

	private String place = "";

	private String branchName = "";

	private String branchAddress = "";

	private String currencyName = "";

	public PDFCreatorBean() {
		CoverageDetails = new HashMap();
		QuoteNo = "";
		homePdfDetails = new HashMap();
		pdfDataSelect = new HomePdfDataSelect();
		cover = new DataSelect();
		tableCreation = new PdfPTableCreation();
		tableCreation1 = new PdfPTableCreation();
		numword = new NumberToWordBean();
	}

	// Receipt No Start
	public void setReceiptNo(final String receiptNo) {
		this.receiptNo = receiptNo;
	}

	public String getReceiptNo() {
		return this.receiptNo;
	}

	public void setModeOfPurchase(final String modeOfPurchase) {
		this.modeOfPurchase = modeOfPurchase;
	}

	public String getModeOfPurchase() {
		return this.modeOfPurchase;
	}

	// Receipt No End
	public void setEmptyImage(final String emptyImage) {
		this.emptyImage = emptyImage;
	}

	public String getEmptyImage() {
		return this.emptyImage;
	}

	public void setLoginId(final String loginId) {
		this.loginId = loginId;
	}

	public String getLoginId() {
		return this.loginId;
	}

	public void setPdfDisplay(final String pdfDisplay) {
		this.pdfDisplay = pdfDisplay;
	}

	public String getPdfDisplay() {
		return this.pdfDisplay;
	}

	public void setAll(final String all) {
		this.all = all;
	}

	public String getAll() {
		return this.all;
	}

	public void setCoverageDetails(final Map CoverageDetails) {
		this.CoverageDetails = CoverageDetails;
	}

	public Map getCoverageDetails() {
		return this.CoverageDetails;
	}

	public void setQuoteNo(final String QuoteNo) {
		this.QuoteNo = QuoteNo;
	}

	public String getQuoteNo() {
		return this.QuoteNo;
	}

	public void setUserModeSC(final String usrModeSC) {
		this.userModeSC = usrModeSC;
	}

	public String getUserModeSC() {
		return this.userModeSC;
	}

	public String getHeaderImagePath() {
		return urlPath;
	}

	public void setHeaderImagePath(final String urlPath) {
		this.urlPath = urlPath;
	}

	public String getFooterImagePath() {
		return urlPathFooter;
	}

	public void setFooterImagePath(final String urlPathFooter) {
		this.urlPathFooter = urlPathFooter;
	}

	public String getBetterImagePath() {
		return betterImagePath;
	}

	public void setBetterImagePath(final String betterImagePath) {
		this.betterImagePath = betterImagePath;
	}

	public void setFilePath(final String filePath) {
		this.filePath = filePath;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setSignedImagePath(final String signedImagePath) {
		this.signedImagePath = signedImagePath;
	}

	public String getSignedImagePath() {
		return this.signedImagePath;
	}

	public void setWaterImagePath(final String waterImagePath) {
		this.waterImagePath = waterImagePath;
	}

	public String getWaterImagePath() {
		return this.waterImagePath;
	}

	public void setPid(final String pid) {
		this.pid = pid;
	}

	public String getPid() {
		return this.pid;
	}

	public void setClauses(final String clauses) {
		this.clauses = clauses;
	}

	public String getClauses() {
		return this.clauses;
	}

	public void setBranch(final String branch) {
		this.branch = branch;
	}

	public String getBranch() {
		return this.branch;
	}

	public void setCurrencyType(final String currencyType) {
		this.currencyType = currencyType;
	}

	public String getCurrencyType() {
		return this.currencyType;
	}

	public void setBetterRoom(final String betterRoom) {
		this.betterRoom = betterRoom;
	}

	public String getBetterRoom() {
		return this.betterRoom;
	}

	public void setBetterContents(final String betterContents) {
		this.betterContents = betterContents;
	}

	public String getBetterContents() {
		return this.betterContents;
	}

	public void setBetterPremium(final String betterPremium) {
		this.betterPremium = betterPremium;
	}

	public String getBetterPremium() {
		return this.betterPremium;
	}

	public void setBetterName(final String betterName) {
		this.betterName = betterName;
	}

	public String getBetterName() {
		return this.betterName;
	}

	public void writePDF(final String StrOption) throws BaseException {
		LogManager.push("writePDF method()");
		LogManager.debug("- Enter");

		option = StrOption;
		String fullName = "";
		String leinName = "";
		String ncdval = "";
		String polno = "";
		String sdate = "";
		String newAdd = "";

		// Receipt No
		final Date receiptDate = new Date();
		final SimpleDateFormat Ryear = new SimpleDateFormat("yyyy", Locale.US);
		final SimpleDateFormat Rmonth = new SimpleDateFormat("MM", Locale.US);
		final SimpleDateFormat Rday = new SimpleDateFormat("dd", Locale.US);
		final String receiptDay = Rday.format(receiptDate);
		final String receiptMonth = Rmonth.format(receiptDate);
		final String receiptYear = Ryear.format(receiptDate);
		final String sealDate = receiptDay + "-" + receiptMonth + "-"
		+ receiptYear;
		// Receipt No

		int bill = 0;
		int cons = 0;
		int pers = 0;
		String pname;
		final NumberFormat fmt = new DecimalFormat("##,##,##0");
		final NumberFormat fmt2 = new DecimalFormat("##,##,##0.0");

		int size = 0;
		String bedrooms;
		final String qno = getQuoteNo();
		final String logId = getLoginId();
		if (option.equalsIgnoreCase("DebitMultiple")) {
			homePdfDetails = pdfDataSelect.getOfficePdfDetails(qno, logId);
			option = "Debit";
		} else {
			homePdfDetails = pdfDataSelect.getHomePdfDetails(qno, logId);
		}

		// pname = cover.getProductName(getPid());
		pname = "HOME INSURANCE";
		if (pname == null || pname.equals("null")) {
			pname = "";
		}
		String bname = "";
		String banks[][];
		banks = pdfDataSelect.getFinanceBankName(getQuoteNo());
		for (int b = 0; b < banks.length; b++) {
			bname = bname + banks[b][0] + ",";
		}
		if (bname.length() > 0) {
			bname = bname.substring(0, bname.length() - 1);
		}
		if (homePdfDetails.size() > 0) {
			String firstName = (homePdfDetails.get(PER_FIRST_NAME) == null ? ""
					: (String) homePdfDetails.get(PER_FIRST_NAME) + " ").trim();
			final String lastName = (homePdfDetails.get(PER_LAST_NAME) == null ? ""
					: (String) homePdfDetails.get(PER_LAST_NAME));
			final String companyName = (homePdfDetails.get(PER_COMPANYNAME) == null ? ""
					: (String) homePdfDetails.get(PER_COMPANYNAME)).trim();

			if (firstName.length() > 0) {
				fullName = firstName;
			} else if (lastName.length() > 0) {
				fullName = lastName;
			} else if (companyName.length() > 0) {
				fullName = companyName;
			}
			//
			fullName = (homePdfDetails.get(PER_TITLE) == null ? ""
					: (((String) homePdfDetails.get(PER_TITLE))
							.equalsIgnoreCase(SELECT) ? ""
									: (String) homePdfDetails.get(PER_TITLE) + " "))
									+ "\b" + fullName;
			String covlen = (String) homePdfDetails.get("covtra.length");
			covlen = covlen == null ? "0" : covlen;
			leinName = fullName;
			if (bname.length() > 0 && bname != null && !bname.equals("")) {
				fullName = bname + "\b&/OR\b" + fullName;
			}
			if (covlen.length() > 0) {
				size = Integer.parseInt(covlen);
			} else {
				size = 0;
			}
			CoverageDetails.clear();
			if (CoverageDetails.size() <= 0) {
				int jIndex = 0;
				for (int iIndex = 0; iIndex < size; iIndex++) {
					CoverageDetails.put(COVERAGE_DETAILS + (jIndex++),
							homePdfDetails.get(COVTRA_COVER_ID + iIndex));
					CoverageDetails.put(COVERAGE_DETAILS + (jIndex++),
							homePdfDetails.get("covtra.Product_description"
									+ iIndex));
					CoverageDetails.put(COVERAGE_DETAILS + (jIndex++),
							homePdfDetails.get(COVTRA_PRO_SUM + iIndex));
				}
			}
			polno = (homePdfDetails.get(POS_POLICY_NO) == null ? ""
					: (String) homePdfDetails.get(POS_POLICY_NO));
			newAdd = ((homePdfDetails.get(PER_ADDRESS1).equals("")) ? ""
					: ((String) homePdfDetails.get(PER_ADDRESS1) + ",\b")
					+ "PO Box number\b"
					+ ((homePdfDetails.get(PER_POBOX).equals("")) ? ""
							: ((String) homePdfDetails.get(PER_POBOX) + ",\b"))
							+ ((homePdfDetails.get(PER_EMIRATE).equals("")) ? ""
									: ((String) homePdfDetails.get(PER_EMIRATE) + ", U A E.")));
			newAdd = newAdd.trim();
			bedrooms = homePdfDetails.get(POS_BEDROOMS) == null ? ""
					: (String) homePdfDetails.get(POS_BEDROOMS);
		}
		if (!option.equalsIgnoreCase("Debit")
				&& !option.equalsIgnoreCase("PrintQuote")
				&& !option.equalsIgnoreCase("BetterMail")
				&& !option.equalsIgnoreCase("Receipt")) {

			Document document = new Document(PageSize.A4, 50, 50, 120, 60);
			try {
				try {
					writer = PdfWriter.getInstance(document,
							new FileOutputStream(getFilePath()));
				} catch (Exception e) {
					LogManager.debug(e);
				}

				document.open();
				// ///////Header and Footer/////////
				try {
					endPage = new HeaderFooterImage();
					writer.setPageEvent(endPage);
					endPage.setOption(option);
					if (option.equalsIgnoreCase(DRAFT)) {
						endPage.setDisplayText(DRAFT);
						endPage.setPolicyNo(getQuoteNo());
					} else if (option.equalsIgnoreCase(SCHEDULE)
							&& !getAll().equalsIgnoreCase("All")) {
						if (userModeSC.equalsIgnoreCase(TEST)) {
							endPage.setDisplayText("Invalid Copy");
							endPage.setPolicyNo(getQuoteNo());
						} else {
							endPage.setPolicyNo(polno);
							if (getAll().equals("") || getAll().length() <= 0) {
								endPage.setDisplayText(getPdfDisplay());
							} else if (getAll().length() > 0) {

								if (getAll().equalsIgnoreCase("Original")) {
									endPage.setDisplayText("");
								}
								if (getAll().equalsIgnoreCase("Original Copy")) {
									endPage.setDisplayText("Original Copy");
								}
								if (getAll().equalsIgnoreCase("Copy")) {
									endPage.setDisplayText("Copy");
								}
							}
						}
					} else if (option.equalsIgnoreCase(SCHEDULE)
							&& getAll().equalsIgnoreCase("All")
							&& userModeSC.equalsIgnoreCase(TEST)) {
						endPage.setDisplayText("Invalid Copy");
						endPage.setPolicyNo(getQuoteNo());

					}
					endPage.setHeaderImagePath(getHeaderImagePath());
					endPage.setPname(pname);

					endPage.setFooterImagePath(getFooterImagePath());

				} catch (Exception exImageHeader) {
					LogManager.info(exImageHeader);
				}

				// ////////// Font Declaration ////////////
				Font sfont = new Font(Font.HELVETICA, 8);
				Font spfont = new Font(Font.HELVETICA, 9);

				Font sfont1 = new Font(Font.HELVETICA, 9, Font.BOLD);
				Font sfont111 = new Font(Font.HELVETICA, 9);
				Font sfontb1 = new Font(Font.HELVETICA, 9, Font.BOLD);
				sfontb1.setStyle(Font.UNDERLINE);
				Font sfont22 = new Font(Font.HELVETICA, 8, Font.BOLD);

				Font font1 = new Font(Font.HELVETICA, 12, Font.BOLD);
				font1.setStyle(Font.UNDERLINE);
				Font sfont2 = new Font(Font.HELVETICA, 8, Font.UNDERLINE);
				// /// Table 1 /////////
				tableCreation.setTable(8);
				tableCreation.insertCell(Rectangle.NO_BORDER, 8);
				tableCreation.insertCell(Rectangle.NO_BORDER, 8);
				tableCreation.insertCell(Rectangle.NO_BORDER, 8);
				tableCreation.insertCell(Rectangle.NO_BORDER, 8);
				tableCreation.insertCell(Rectangle.NO_BORDER, 8);
				tableCreation.setTableSpacing(100f);
				document.add(tableCreation.getTable());
				// /// Table 2 /////////
				tableCreation.setTable(3);
				// tableCreation.insertCell(Rectangle.NO_BORDER, 1);
				tableCreation.insertCell(pname + "\bPOLICY", font1,
						Rectangle.NO_BORDER, 3, 2);
				tableCreation.insertCell(Rectangle.NO_BORDER, 3);
				tableCreation.insertCell(Rectangle.NO_BORDER, 3);
				tableCreation.insertCell(Rectangle.NO_BORDER, 1);
				tableCreation.insertCell(Rectangle.NO_BORDER, 1);
				if (option.equalsIgnoreCase(SCHEDULE)
						&& !userModeSC.equalsIgnoreCase(TEST)) {
					if (homePdfDetails.isEmpty()) {
						tableCreation.insertCell(POLICY_NO, sfont1,
								Rectangle.NO_BORDER, 1, 1);
					} else {
						tableCreation
						.insertCell(
								POLICY_NO
								+ (homePdfDetails
										.get(POS_POLICY_NO) == null ? ""
												: (String) homePdfDetails
												.get(POS_POLICY_NO)),
												sfont1, Rectangle.NO_BORDER, 1, 1);
					}
				} else if (option.equalsIgnoreCase(DRAFT)) {
					if (getQuoteNo().length() > 0) {
						tableCreation.insertCell("Quote No: " + getQuoteNo(),
								sfont1, Rectangle.NO_BORDER, 1, 1);
					} else {
						tableCreation.insertCell("Quote No: ", sfont1,
								Rectangle.NO_BORDER, 1, 1);
					}
				} else {
					if (getQuoteNo().length() > 0) {
						tableCreation.insertCell(POLICY_NO + getQuoteNo(),
								sfont1, Rectangle.NO_BORDER, 1, 1);
					} else {
						tableCreation.insertCell(POLICY_NO, sfont1,
								Rectangle.NO_BORDER, 1, 1);
					}
				}
				// For Sum Insured only//
				double sum = 0.0;
				double pre = 0.0;
				int noc = 0;
				for (int i = 0; i < size; i++) {
					if (!(((String) homePdfDetails.get(COVTRA_PRO_SUM + i))
							.equalsIgnoreCase("1"))
							&& !(((String) homePdfDetails.get(COVTRA_PRO_SUM
									+ i)).equalsIgnoreCase("0"))) {
						sum += Double.parseDouble(homePdfDetails
								.get(COVTRA_PRO_SUM + i) == "" ? "0.0"
										: (String) homePdfDetails.get(COVTRA_PRO_SUM
												+ i));
					}
					pre += Double.parseDouble(homePdfDetails.get(COVTRA_PREMIUM
							+ i) == "" ? "0.0" : (String) homePdfDetails
									.get(COVTRA_PREMIUM + i));
				}
				// End
				tableCreation.setTableSpacing(20f);
				document.add(tableCreation.getTable());
				// ////// Table 3/////////////
				PdfPTable datatable21;
				PdfPTable datatable22;
				tableCreation.setTable(1);
				tableCreation.insertCell(Rectangle.NO_BORDER, 1);
				tableCreation.insertCell(Rectangle.NO_BORDER, 1);
				tableCreation.insertCell(Rectangle.NO_BORDER, 1);
				tableCreation.insertCell(Rectangle.NO_BORDER, 1);
				tableCreation.insertCell("The Company :", sfont1,
						Rectangle.NO_BORDER, 1, 0);
				tableCreation.insertCell("Address :", sfont1,
						Rectangle.NO_BORDER, 1, 0);
				tableCreation.insertCell("Broker :", sfont1,
						Rectangle.NO_BORDER, 1, 0);
				tableCreation.insertCell("The Insured :", sfont1,
						Rectangle.NO_BORDER, 1, 0);
				tableCreation.insertCell("Address :", sfont1,
						Rectangle.NO_BORDER, 1, 0);
				tableCreation.insertCell("Insured Location :", sfont1,
						Rectangle.NO_BORDER, 1, 0);
				tableCreation.insertCell("Period of Insurance :", sfont1,
						Rectangle.NO_BORDER, 1, 0);
				tableCreation.insertCell(Rectangle.NO_BORDER, 1);
				// tableCreation.insertCell("Sum Insured("+currencyType+") :",
				// sfont1, Rectangle.NO_BORDER, 1, 0);
				tableCreation.insertCell("Premium(" + currencyType + ") :",
						sfont1, Rectangle.NO_BORDER, 1, 0);
				datatable21 = tableCreation.getTable();
				tableCreation.setTable(5);
				tableCreation.insertCell("THE SCHEDULE", new Font(
						Font.HELVETICA, 10, Font.BOLD), Rectangle.NO_BORDER, 5,
						2);
				tableCreation.insertCell(Rectangle.NO_BORDER, 5);
				tableCreation.insertCell(branchName, sfont111,
						Rectangle.NO_BORDER, 5, 0);
				tableCreation.insertCell(branchAddress, sfont111,
						Rectangle.NO_BORDER, 5, 0);
				if (homePdfDetails.isEmpty()) {
					tableCreation.insertCell("", sfont111, Rectangle.NO_BORDER,
							5, 0);

				} else {
					tableCreation.insertCell((homePdfDetails
							.get(BRO_COMPANY_NAME) == null ? ""
									: ((String) homePdfDetails.get(BRO_COMPANY_NAME))),
									sfont111, Rectangle.NO_BORDER, 5, 0);
				}

				if (homePdfDetails.isEmpty()) {
					tableCreation.insertCell("", sfont, Rectangle.NO_BORDER, 5,
							0);
					tableCreation.insertCell("", sfont, Rectangle.NO_BORDER, 5,
							0);
					tableCreation.insertCell("", sfont, Rectangle.NO_BORDER, 5,
							0);
				} else {

					String buildLoc = "";
					tableCreation.insertCell(fullName, sfont111,
							Rectangle.NO_BORDER, 5, 0);
					buildLoc = pdfDataSelect.getBuildingLocation(getQuoteNo());
					if (newAdd.length() > 0) {
						tableCreation.insertCell(newAdd, sfont111,
								Rectangle.NO_BORDER, 5, 0);
					}

					if (buildLoc.length() > 0) {
						tableCreation.insertCell(buildLoc, sfont111,
								Rectangle.NO_BORDER, 5, 0);
					} else {
						tableCreation.insertCell("", sfont111,
								Rectangle.NO_BORDER, 5, 0);
					}
					// else if(!buildLoc.equals("")&&buildLoc.length()>0)
					// tableCreation.insertCell(buildLoc, sfont,
					// Rectangle.NO_BORDER, 5, 0);
				}
				tableCreation.insertCell("", sfont111, Rectangle.NO_BORDER, 5,
						0);
				if (homePdfDetails.size() > 0) {
					sdate = (homePdfDetails.get(POS_EFF_DATE) == null ? ""
							: (String) homePdfDetails.get(POS_EFF_DATE));
					tableCreation.insertCell("From "
							+ sdate
							+ " To "
							+ (homePdfDetails.get(POS_EXPIRY_DATE) == null ? ""
									: (String) homePdfDetails
									.get(POS_EXPIRY_DATE))
									+ " both days inclusive.", sfont111,
									Rectangle.NO_BORDER, 5, 0);
				} else {
					tableCreation
					.insertCell(
							"From 00/00/0000 To 00/00/0000 both days inclusive.",
							sfont111, Rectangle.NO_BORDER, 5, 0);
					tableCreation.insertCell("", sfont111, Rectangle.NO_BORDER,
							5, 0);
				}
				if (homePdfDetails.isEmpty()) {
					tableCreation.insertCell("", sfont, Rectangle.NO_BORDER, 3,
							0);
				} else {
					String excessandSign[][] = new String[0][0];
					double totPre = 0.0;
					String exPre = "";
					String sign = "";
					String preOriginal = (homePdfDetails.get(POS_PREMIUM) == null ? ""
							: (String) homePdfDetails.get(POS_PREMIUM));
					excessandSign = pdfDataSelect.getExcessandSign(QuoteNo);
					if (excessandSign.length > 0) {
						exPre = excessandSign[0][0] == null ? ""
								: excessandSign[0][0];
						sign = excessandSign[0][1] == null ? ""
								: excessandSign[0][1];
					}
					if ((preOriginal.length() > 0) && (exPre.length() > 0)) {
						if (sign.equalsIgnoreCase("+")) {
							totPre = Double.parseDouble(preOriginal)
							+ Double.parseDouble(exPre);
						} else if (sign.equalsIgnoreCase("-")) {
							totPre = Double.parseDouble(preOriginal)
							- Double.parseDouble(exPre);
						} else {
							totPre = Double.parseDouble(preOriginal)
							+ Double.parseDouble(exPre);
						}
					} else if (preOriginal.length() > 0 && totPre == 0) {
						totPre = Double.parseDouble(preOriginal);
					}

					tableCreation.insertCell(fmt.format(totPre), sfont111,
							Rectangle.NO_BORDER, 5, 0);

				}
				datatable22 = tableCreation.getTable();
				tableCreation.setTable(6);
				tableCreation.insertCell(datatable21, Rectangle.BOX, 1, 0);
				tableCreation.insertCell(datatable22, Rectangle.BOX, 5, 0);
				tableCreation.setTableSpacing(15f);
				document.add(tableCreation.getTable());
				// // New Table From Summary Jsp //
				tableCreation.setTable(4);
				tableCreation.insertCell("SNO", sfont1, Rectangle.BOX, 1, 2);
				tableCreation.insertCell("COVERAGE", sfont1, Rectangle.BOX, 1,
						2);
				tableCreation.insertCell("SUM INSURED [" + currencyType + "]",
						sfont1, Rectangle.BOX, 1, 2);
				tableCreation.insertCell("PREMIUM [" + currencyType + "]",
						sfont1, Rectangle.BOX, 1, 2);
				tableCreation.insertCell("", sfont1, Rectangle.NO_BORDER, 4, 0);
				// / Next Table ///

				// ncdval = (String)homePdfDetails.get("pos.ncdval");
				ncdval = "0";
				pre = java.lang.Math.round(pre) + Double.parseDouble(ncdval);
				if (!"0".equals(ncdval)) {
					tableCreation.insertCell(Integer.toString(noc + 2), sfont,
							Rectangle.NO_BORDER, 1, 2);
					tableCreation.insertCell("NO CLAIM DISCOUNT", sfont,
							Rectangle.NO_BORDER, 1, 0);
					tableCreation.insertCell("", sfont, Rectangle.NO_BORDER, 1,
							1);
					tableCreation.insertCell(fmt.format(Double
							.parseDouble(ncdval)), sfont, Rectangle.NO_BORDER,
							1, 1);
				}

				tableCreation.insertCell("", sfont1, Rectangle.NO_BORDER, 4, 0);
				tableCreation.insertCell("", sfont1, Rectangle.BOX, 1, 2);
				tableCreation.insertCell("TOTAL", sfont1, Rectangle.BOX, 1, 2);
				tableCreation.insertCell(fmt.format(sum), sfont1,
						Rectangle.BOX, 1, 1);
				tableCreation.insertCell(fmt.format(java.lang.Math.round(pre)),
						sfont1, Rectangle.BOX, 1, 1);
				tableCreation.setTableSpacing(20f);
				// document.add(tableCreation.getTable());
				// /////// Table 4 ///////////
				tableCreation.setTable(6);
				tableCreation.insertCell("Cover", sfont1, Rectangle.BOX, 1, 0);
				tableCreation.insertCell("Property Description", sfont1,
						Rectangle.BOX, 3, 0);
				tableCreation.insertCell("\b\b\bSum Insured (" + currencyType
						+ ")", sfont1, Rectangle.BOX, 2, 2);
				tableCreation.setTableSpacing(20f);
				document.add(tableCreation.getTable());
				// ///// Table 5 ///////////// Trying to get Data from
				// Database///////////
				tableCreation.setTable(6);
				int index = 0;
				double total_count = 0.0;
				double temp_count = 0.0;
				int flag = 0;
				int flag1 = 0;
				int flagc = 0;
				int financeFalg = 0;

				String disStrB = "";
				String disStrC = "";
				String disStrP = "";
				String disStrE = "";
				if (pid.equalsIgnoreCase("26") || pid.equalsIgnoreCase("33")) {
					disStrB = "(Section 4 & 5)";
					disStrC = "(Section 1 & 2)";
					disStrP = "(Section 3)";
					disStrE = "(Section 6)";
				}
				while (index < (CoverageDetails.size())) {
					String Sno = (String) CoverageDetails.get(COVERAGE_DETAILS
							+ index);
					String covDesc = (String) CoverageDetails
					.get(COVERAGE_DETAILS + (index + 1));
					String sumIns = (String) CoverageDetails
					.get(COVERAGE_DETAILS + (index + 2));

					index = index + 3;

					if (Sno.equalsIgnoreCase("1")) {
						String building[][] = new String[0][0];
						building = pdfDataSelect
						.homeCoverageBuildingTransactionSelect(getQuoteNo());
						double doubleValue = 0.0;
						financeFalg = 1;
						if (building.length > 0) {
							doubleValue = Double
							.parseDouble(building[0][3] == null ? "0"
									: building[0][3]);
						} else {
							doubleValue = 0.0;
						}

						tableCreation.insertCell("1", sfont1,
								Rectangle.NO_BORDER, 1, 0);
						tableCreation.insertCell(covDesc + "\b" + disStrB,
								sfont1, Rectangle.NO_BORDER, 3, 0);

						for (int m = 0; m < size; m++) {
							if (((String) homePdfDetails.get(COVTRA_COVER_ID
									+ m)).equalsIgnoreCase("1")) {
								tableCreation
										.insertCell(
												fmt
														.format(Double
																.parseDouble((homePdfDetails
																		.get(COVTRA_PRO_SUM
																				+ m) == "" ? "0"
																		: (String) homePdfDetails
																				.get(COVTRA_PRO_SUM
																						+ m)))),
												sfont1, Rectangle.NO_BORDER, 2,
												1);
							}
						}
						if (doubleValue != 0) {
							total_count = total_count + doubleValue;
						}
						flag = 1;
						bill = 1;
					}
					if (Sno.equalsIgnoreCase("2")) {

						String contents[][] = new String[0][0];
						contents = pdfDataSelect
						.homeCoverageTotalTransactionSelect(
								getQuoteNo(), "2");
						String desc = "";
						double siTemp = 0.0;
						flagc = 1;
						if (flag == 1) {
							Data1 = "B";
						} else {
							Data1 = "A";
						}
						bedrooms = "0";
						if (bedrooms.length() > 0 && !"0".equals(bedrooms)) {
							tableCreation.insertCell("", sfont1,
									Rectangle.NO_BORDER, 6, 0);
							tableCreation.insertCell("", sfont1,
									Rectangle.NO_BORDER, 1, 2);
							tableCreation.insertCell("TOTAL NO OF BEDROOMS",
									sfont1, Rectangle.NO_BORDER, 3, 0);
							tableCreation.insertCell("", sfont1,
									Rectangle.NO_BORDER, 1, 1);
							tableCreation.insertCell(bedrooms, sfont1,
									Rectangle.NO_BORDER, 1, 1);
						}
						tableCreation.insertCell("2", sfont1,
								Rectangle.NO_BORDER, 1, 0);
						tableCreation.insertCell("TOTAL\b" + covDesc + "\b"
								+ disStrC, sfont1, Rectangle.NO_BORDER, 3, 0);

						for (int m1 = 0; m1 < size; m1++) {
							if (((String) homePdfDetails.get(COVTRA_COVER_ID
									+ m1)).equalsIgnoreCase("2")) {
								tableCreation
										.insertCell(
												fmt
														.format(Double
																.parseDouble((homePdfDetails
																		.get(COVTRA_PRO_SUM
																				+ m1) == "" ? "0"
																		: (String) homePdfDetails
																				.get(COVTRA_PRO_SUM
																						+ m1)))),
												sfont1, Rectangle.NO_BORDER, 2,
												1);
							}
						}
						tableCreation.insertCell(Rectangle.NO_BORDER, 6);
						for (int c = 0; c < contents.length; c++) {
							// total_count = total_count +
							// Double.parseDouble(contents[c][5]==null?"0":contents[c][5]);
							temp_count = temp_count
							+ Double
							.parseDouble(contents[c][5] == null ? "0"
									: contents[c][5]);

						}
						if (temp_count > 0) {
							tableCreation.insertCell(Rectangle.NO_BORDER, 1);
							tableCreation.insertCell("Specified Items",
									sfont22, Rectangle.NO_BORDER, 3, 0);
							// tableCreation.insertCell(""+fmt.format(temp_count),
							// sfont22, Rectangle.NO_BORDER, 1, 1);
							tableCreation.insertCell(Rectangle.NO_BORDER, 2);
						}
						tableCreation.insertCell(Rectangle.NO_BORDER, 6);
						for (int c = 0; c < contents.length; c++) {
							if (contents.length > 0) {
								desc = contents[c][4] == null ? ""
										: contents[c][4];
								siTemp = Double
								.parseDouble(contents[c][5] == null ? "0"
										: contents[c][5]);
							} else {
								desc = "";
								siTemp = 0.0;
							}

							if (siTemp > 0) {
								tableCreation
								.insertCell(Rectangle.NO_BORDER, 0);
								tableCreation.insertCell(
										(c + 1) + ")\b" + desc, sfont,
										Rectangle.NO_BORDER, 2, 0);
								tableCreation.insertCell(fmt.format(siTemp),
										sfont, Rectangle.NO_BORDER, 1, 1);
								tableCreation
								.insertCell(Rectangle.NO_BORDER, 1);
								tableCreation
								.insertCell(Rectangle.NO_BORDER, 2);
								tableCreation
								.insertCell(Rectangle.NO_BORDER, 0);
								tableCreation.insertCell("", sfont,
										Rectangle.NO_BORDER, 2, 0);
								tableCreation.insertCell("", sfont,
										Rectangle.NO_BORDER, 2, 1);

								tableCreation
								.insertCell(Rectangle.NO_BORDER, 2);
							}
						}
						if (temp_count != 0) {
							tableCreation.insertCell(Rectangle.NO_BORDER, 0);
							tableCreation.insertCell("", sfont,
									Rectangle.NO_BORDER, 2, 0);
							tableCreation.insertCell(fmt.format(temp_count),
									sfont, Rectangle.TOP, 1, 1);
							tableCreation.insertCell(Rectangle.NO_BORDER, 1);
							tableCreation.insertCell(Rectangle.NO_BORDER, 2);
							tableCreation.insertCell(Rectangle.NO_BORDER, 0);
							tableCreation.insertCell("", sfont,
									Rectangle.NO_BORDER, 2, 0);
							tableCreation.insertCell("", sfont, Rectangle.TOP,
									1, 1);
							tableCreation.insertCell(Rectangle.NO_BORDER, 1);

							tableCreation.insertCell(Rectangle.NO_BORDER, 2);

						}
						cons = 1;
					}
					if (Sno.equalsIgnoreCase("3")) {
						String valuables[][];
						temp_count = 0.0;
						valuables = pdfDataSelect
						.homeCoverageTotalTransactionSelect(
								getQuoteNo(), "3");
						String desc1 = "";
						double si1 = 0.0;
						flag1 = 2;
						if (flag == 1 && flagc == 1) {
							Data1 = "C";
						} else {
							Data1 = "B";
						}

						tableCreation.insertCell("3", sfont1,
								Rectangle.NO_BORDER, 1, 0);
						tableCreation.insertCell("TOTAL\b" + covDesc + "\b"
								+ disStrP, sfont1, Rectangle.NO_BORDER, 3, 0);

						for (int m2 = 0; m2 < size; m2++) {
							if (((String) homePdfDetails.get(COVTRA_COVER_ID
									+ m2)).equalsIgnoreCase("3")) {
								tableCreation
										.insertCell(
												fmt
														.format(Double
																.parseDouble((homePdfDetails
																		.get(COVTRA_PRO_SUM
																				+ m2) == "" ? "0"
																		: (String) homePdfDetails
																				.get(COVTRA_PRO_SUM
																						+ m2)))),
												sfont1, Rectangle.NO_BORDER, 2,
												1);
							}
						}
						tableCreation.insertCell(Rectangle.NO_BORDER, 6);
						for (int v = 0; v < valuables.length; v++) {
							temp_count = temp_count
							+ Double
							.parseDouble(valuables[v][5] == null ? "0"
									: valuables[v][5]);
						}
						if (temp_count > 0) {
							tableCreation.insertCell(Rectangle.NO_BORDER, 1);
							tableCreation.insertCell("Specified Items",
									sfont22, Rectangle.NO_BORDER, 3, 0);
							// tableCreation.insertCell(""+fmt.format(temp_count),
							// sfont22, Rectangle.NO_BORDER, 1, 1);
							tableCreation.insertCell(Rectangle.NO_BORDER, 2);
						}
						tableCreation.insertCell(Rectangle.NO_BORDER, 6);
						for (int v = 0; v < valuables.length; v++) {
							if (valuables.length > 0) {
								desc1 = valuables[v][4] == null ? ""
										: valuables[v][4];
								si1 = Double
								.parseDouble(valuables[v][5] == null ? "0"
										: valuables[v][5]);
							} else {
								desc1 = "";
								si1 = 0.0;
							}

							if (si1 > 0) {
								tableCreation
								.insertCell(Rectangle.NO_BORDER, 0);
								tableCreation.insertCell((v + 1) + ")\b"
										+ desc1, sfont, Rectangle.NO_BORDER, 2,
										0);
								if (v + 1 == valuables.length) {
									tableCreation.insertCell(fmt.format(si1),
											sfont, Rectangle.NO_BORDER, 1, 1);
								} else {
									tableCreation.insertCell(fmt.format(si1),
											sfont, Rectangle.NO_BORDER, 1, 1);
								}
								tableCreation
								.insertCell(Rectangle.NO_BORDER, 1);
								tableCreation.insertCell("", sfont,
										Rectangle.NO_BORDER, 1, 0);
							}
						}
						if (temp_count != 0) {
							tableCreation.insertCell(Rectangle.NO_BORDER, 0);
							tableCreation.insertCell("", sfont,
									Rectangle.NO_BORDER, 2, 0);
							tableCreation.insertCell(fmt.format(temp_count),
									sfont, Rectangle.TOP, 1, 1);
							tableCreation.insertCell(Rectangle.NO_BORDER, 1);
							tableCreation.insertCell(Rectangle.NO_BORDER, 2);
							tableCreation.insertCell(Rectangle.NO_BORDER, 0);
							tableCreation.insertCell("", sfont,
									Rectangle.NO_BORDER, 2, 0);
							tableCreation.insertCell("", sfont, Rectangle.TOP,
									1, 1);
							tableCreation.insertCell(Rectangle.NO_BORDER, 1);

							tableCreation.insertCell(Rectangle.NO_BORDER, 2);

						}
						pers = 1;
					}

					// document.add(tableCreation.getTable());
					if (Sno.equalsIgnoreCase("4")
							&& !sumIns.equalsIgnoreCase("0")) {

						String[][] domestinInfo;
						if (flag == 1 && flag1 == 2 && flagc == 1) {
							Data1 = "D";
						} else if (flag1 != 2 && flagc != 1) {
							Data1 = "B";
						}
						// tableCreation1.setTable(6);
						tableCreation.insertCell("", sfont1,
								Rectangle.NO_BORDER, 1, 0);
						tableCreation.insertCell(covDesc + "\b" + disStrE,
								sfont1, Rectangle.NO_BORDER, 5, 0);
						domestinInfo = pdfDataSelect
						.getDomesticInfo(getQuoteNo());
						tableCreation.insertCell("", sfont,
								Rectangle.NO_BORDER, 6, 0);
						for (int d = 0; d < domestinInfo.length; d++) {
							tableCreation.insertCell("", sfont,
									Rectangle.NO_BORDER, 1, 0);
							tableCreation.insertCell(Rectangle.NO_BORDER, 1);
							tableCreation.insertCell("Name\b:", sfont22,
									Rectangle.NO_BORDER, 1, 2);
							tableCreation.insertCell(
									(domestinInfo[d][0] == null ? ""
											: domestinInfo[d][0]), sfont,
											Rectangle.NO_BORDER, 1, 1);
							tableCreation.insertCell(Rectangle.NO_BORDER, 1);
							tableCreation.insertCell("", sfont,
									Rectangle.NO_BORDER, 1, 0);
							tableCreation.insertCell("", sfont,
									Rectangle.NO_BORDER, 1, 0);
							tableCreation.insertCell(Rectangle.NO_BORDER, 1);
							tableCreation.insertCell("DOB\b:", sfont22,
									Rectangle.NO_BORDER, 1, 2);
							tableCreation.insertCell(
									(domestinInfo[d][1] == null ? ""
											: domestinInfo[d][1]), sfont,
											Rectangle.NO_BORDER, 1, 1);
							tableCreation.insertCell(Rectangle.NO_BORDER, 1);
							tableCreation.insertCell("", sfont,
									Rectangle.NO_BORDER, 1, 0);
							tableCreation.insertCell("", sfont,
									Rectangle.NO_BORDER, 6, 0);
						}
						// datatableNew = tableCreation1.getTable();
					}
					if (Sno.equalsIgnoreCase("5")
							&& !sumIns.equalsIgnoreCase("0")) {
						if (flag == 1 && flag1 == 2 && flagc == 1) {
							Data1 = "E";
						} else {
							Data1 = "D";
						}
						tableCreation.insertCell("", sfont1,
								Rectangle.NO_BORDER, 1, 0);
						tableCreation.insertCell(covDesc, sfont1,
								Rectangle.NO_BORDER, 2, 0);
						tableCreation.insertCell(YES, sfont,
								Rectangle.NO_BORDER, 1, 1);
						tableCreation.insertCell(Rectangle.NO_BORDER, 1);
						tableCreation.insertCell("", sfont,
								Rectangle.NO_BORDER, 1, 0);

					}
					if (Sno.equalsIgnoreCase("6")
							&& !sumIns.equalsIgnoreCase("0")) {

						if (flag == 1 && flag1 == 2 && flagc == 1) {
							Data1 = "F";
						} else {
							Data1 = "E";
						}
						tableCreation.insertCell("", sfont1,
								Rectangle.NO_BORDER, 1, 0);
						tableCreation.insertCell(covDesc, sfont1,
								Rectangle.NO_BORDER, 2, 0);
						tableCreation.insertCell(YES, sfont,
								Rectangle.NO_BORDER, 1, 1);
						tableCreation.insertCell(Rectangle.NO_BORDER, 1);
						tableCreation.insertCell("", sfont,
								Rectangle.NO_BORDER, 1, 0);

					}
				}
				document.add(tableCreation.getTable());
				// //////// Table 6 -- Second Page -- //////////
				// document.newPage();
				tableCreation.setTable(3);
				tableCreation.insertCell(Rectangle.NO_BORDER, 3);
				document.add(tableCreation.getTable());
				// ///////// Table 7 /////////
				tableCreation.setTable(3);
				// tableCreation.insertCell(Rectangle.NO_BORDER, 1);
				tableCreation.insertCell(pname + "\bPOLICY", font1,
						Rectangle.NO_BORDER, 3, 2);
				tableCreation.insertCell(Rectangle.NO_BORDER, 3);
				tableCreation.insertCell(Rectangle.NO_BORDER, 3);
				tableCreation.insertCell(Rectangle.NO_BORDER, 1);
				tableCreation.insertCell("Schedule forming part of", sfont1,
						Rectangle.NO_BORDER, 1, 2);
				if (option.equalsIgnoreCase(SCHEDULE)
						&& !userModeSC.equalsIgnoreCase(TEST)) {
					if (homePdfDetails.isEmpty()) {
						tableCreation.insertCell("Policy No:", sfont1,
								Rectangle.NO_BORDER, 1, 1);
					} else {
						tableCreation
						.insertCell(
								POLICY_NO
								+ (homePdfDetails
										.get(POS_POLICY_NO) == null ? ""
												: (String) homePdfDetails
												.get(POS_POLICY_NO)),
												sfont1, Rectangle.NO_BORDER, 1, 1);
					}
				} else if (option.equalsIgnoreCase(DRAFT)) {
					if (getQuoteNo().length() > 0) {
						tableCreation.insertCell("Quote No: " + getQuoteNo(),
								sfont1, Rectangle.NO_BORDER, 1, 1);
					} else {
						tableCreation.insertCell("Quote No:", sfont1,
								Rectangle.NO_BORDER, 1, 1);
					}
				} else {
					if (getQuoteNo().length() > 0) {
						tableCreation.insertCell(POLICY_NO + getQuoteNo(),
								sfont1, Rectangle.NO_BORDER, 1, 1);
					} else {
						tableCreation.insertCell(POLICY_NO, sfont1,
								Rectangle.NO_BORDER, 1, 1);
					}
				}

				tableCreation.setTableSpacing(30f);
				// document.add(tableCreation.getTable());
				// /////////// Table 8 ////////
				tableCreation.setTable(5);
				tableCreation.insertCell(
						"This policy is subject to the following:", sfont,
						Rectangle.NO_BORDER, 5, 0);
				if (option.equalsIgnoreCase(SCHEDULE)
						&& !userModeSC.equalsIgnoreCase(TEST)) {
					if (homePdfDetails.isEmpty()) {
						tableCreation
						.insertCell(
								"At the request of the Insured Policy No. 00 has been renewed for a period of 12 \nmonths from 00/00/0000 to 00/00/0000.",
								sfont, Rectangle.NO_BORDER, 5, 0);

					} else {
						tableCreation
						.insertCell(
								"At the request of the Insured Policy No. "
								+ (homePdfDetails
										.get(POS_POLICY_NO) == null ? ""
												: (String) homePdfDetails
												.get(POS_POLICY_NO))
												+ " has been renewed for a further period of 12 \nmonths from "
												+ (homePdfDetails
														.get(POS_EFF_DATE) == null ? ""
																: (String) homePdfDetails
																.get(POS_EFF_DATE))
																+ " to "
																+ (homePdfDetails
																		.get(POS_EXPIRY_DATE) == null ? ""
																				: (String) homePdfDetails
																				.get(POS_EXPIRY_DATE)
																				+ "."), sfont,
																				Rectangle.NO_BORDER, 5, 0);
					}
				} else if (option.equalsIgnoreCase(DRAFT)) {
					if (getQuoteNo().length() > 0) {
						tableCreation
						.insertCell(
								"At the request of the Insured Quote No. "
								+ getQuoteNo()
								+ " has been renewed for a period of 12 \nmonths from "
								+ (homePdfDetails
										.get(POS_EFF_DATE) == null ? ""
												: (String) homePdfDetails
												.get(POS_EFF_DATE))
												+ " to "
												+ (homePdfDetails
														.get(POS_EXPIRY_DATE) == null ? ""
																: (String) homePdfDetails
																.get(POS_EXPIRY_DATE)
																+ "."), sfont,
																Rectangle.NO_BORDER, 5, 0);
					} else {
						tableCreation
						.insertCell(
								"At the request of the Insured Quote No. 00 has been renewed for a period of 12 \nmonths from 00/00/0000 to 00/00/0000.",
								sfont, Rectangle.NO_BORDER, 5, 0);
					}
				} else {
					if (getQuoteNo().length() > 0) {
						tableCreation
						.insertCell(
								"At the request of the Insured Quote No. "
								+ getQuoteNo()
								+ " has been renewed for a period of 12 \nmonths from "
								+ (homePdfDetails
										.get(POS_EFF_DATE) == null ? ""
												: (String) homePdfDetails
												.get(POS_EFF_DATE))
												+ " to "
												+ (homePdfDetails
														.get(POS_EXPIRY_DATE) == null ? ""
																: (String) homePdfDetails
																.get(POS_EXPIRY_DATE)
																+ "."), sfont,
																Rectangle.NO_BORDER, 5, 0);
					} else {
						tableCreation
						.insertCell(
								"At the request of the Insured Quote No. 00 has been renewed for a period of 12 \nmonths from 00/00/0000 to 00/00/0000.",
								sfont, Rectangle.NO_BORDER, 5, 0);
					}
				}

				tableCreation.insertCell(Rectangle.NO_BORDER, 5);
				tableCreation.insertCell(Rectangle.NO_BORDER, 5);
				tableCreation.insertCell("Safe Warranty:", sfont2,
						Rectangle.NO_BORDER, 5, 0);
				tableCreation
				.insertCell(
						"It is warranted that the item is always kept locked under the key within the premises when not worn by the \ninsured",
						sfont, Rectangle.NO_BORDER, 5, 0);
				tableCreation.insertCell(Rectangle.NO_BORDER, 5);
				tableCreation
				.insertCell(
						"In consideration of the above a renewal premium as shown above is hereby charged to and due from the\ninsured",
						sfont, Rectangle.NO_BORDER, 5, 0);
				tableCreation.insertCell(Rectangle.NO_BORDER, 5);
				// tableCreation.insertCell("Subject otherwise to the terms,
				// conditions and exceptions of the Policy.", sfont,
				// Rectangle.NO_BORDER, 5, 0);
				tableCreation
				.insertCell(
						"Please refer to your policy booklet for full terms, conditions and exclusions of the policy.",
						sfont, Rectangle.NO_BORDER, 5, 0);
				tableCreation.setTableSpacing(15f);
				// document.add(tableCreation.getTable());
				//
				tableCreation.setTable(5);
				tableCreation.insertCell("Excess\b:", sfont1,
						Rectangle.NO_BORDER, 5, 0);
				String exIds = "";
				if (cons == 1 || pers == 1) {
					exIds = exIds + "2,";
				}
				if (bill == 1) {
					exIds = exIds + "1,";
				}
				String policyExcess[][];
				String policyConSp[][];
				String policyPerSp[][];
				if (exIds.length() > 0) {
					exIds = exIds.substring(0, (exIds.length() - 1));
				}
				policyExcess = cover.getPolicyClauses(pid, exIds, "Excess");
				for (int p = 0; p < policyExcess.length; p++) {
					tableCreation.insertCell(policyExcess[p][0], sfont,
							Rectangle.NO_BORDER, 5, 0);
				}

				tableCreation.insertCell("Special Terms & Conditions\b:",
						sfont1, Rectangle.NO_BORDER, 5, 0);

				// tableCreation.insertCell("\b\bMinimum Excess on all Claims is
				// 250 "+currencyType+"", sfont, Rectangle.NO_BORDER, 5, 0);
				/*
				 * if(bill==1) { tableCreation.insertCell("\b\bMinimum Excess on
				 * Cover 1 Building is 1000 "+currencyType+"", sfont,
				 * Rectangle.NO_BORDER, 5, 0); }
				 */
				if (cons == 1 || pers == 1) {
					tableCreation.insertCell("", sfont1, Rectangle.NO_BORDER,
							5, 0);
					if (cons == 1) {

						tableCreation.insertCell("Contents\b:", sfont22,
								Rectangle.NO_BORDER, 5, 0);
						policyConSp = cover.getPolicyClauses(pid, "2", SPECIAL);
						for (int p = 0; p < policyConSp.length; p++) {
							tableCreation.insertCell(policyConSp[p][0], sfont,
									Rectangle.NO_BORDER, 5, 0);
						}
					}
					if (pers == 1) {
						tableCreation.insertCell("Personal Belongings\b:",
								sfont22, Rectangle.NO_BORDER, 5, 0);
						policyPerSp = cover.getPolicyClauses(pid, "3", SPECIAL);
						for (int p = 0; p < policyPerSp.length; p++) {
							tableCreation.insertCell(policyPerSp[p][0], sfont,
									Rectangle.NO_BORDER, 5, 0);
						}

					}
					if (cons == 1 || pers == 1) {
						tableCreation
						.insertCell(
								"You will be required to provide a valuation or proof of purchase for any specified item when submitting a claim.",
								sfont, Rectangle.NO_BORDER, 5, 0);
					}
					tableCreation.insertCell("", sfont, Rectangle.NO_BORDER, 5,
							0);
				}

				String adminRemarks = pdfDataSelect.getClauses(getQuoteNo());
				String adminRemarkss = pdfDataSelect.getRemarkss(getQuoteNo());
				if (adminRemarks.length() > 0
						&& !adminRemarks.equalsIgnoreCase("null")) {
					tableCreation.insertCell("Special Condition\b:", sfont1,
							Rectangle.NO_BORDER, 5, 0);
					tableCreation.insertCell("", sfont1, Rectangle.NO_BORDER,
							5, 0);
					tableCreation.insertCell(adminRemarks, sfont,
							Rectangle.NO_BORDER, 5, 0);
				}
				if (adminRemarkss.length() > 0
						&& !adminRemarkss.equalsIgnoreCase("null")) {
					tableCreation.insertCell("REMARKS\b:", sfont1,
							Rectangle.NO_BORDER, 5, 0);
					tableCreation.insertCell("", sfont1, Rectangle.NO_BORDER,
							5, 0);
					tableCreation.insertCell(adminRemarkss, sfont,
							Rectangle.NO_BORDER, 5, 0);
				}
				tableCreation.insertCell("", sfont, Rectangle.NO_BORDER, 5, 0);
				tableCreation.insertCell("", sfont, Rectangle.NO_BORDER, 5, 0);
				// tableCreation.insertCell("Subject otherwise to the terms,
				// conditions and exceptions of the Policy.", sfont,
				// Rectangle.NO_BORDER, 5, 0);
				tableCreation
				.insertCell(
						"Please refer to your policy booklet for full terms, conditions and exclusions of the policy.",
						sfont, Rectangle.NO_BORDER, 5, 0);
				tableCreation.setTableSpacing(10f);
				document.add(tableCreation.getTable());

				// ///////////// Table 9 ////////////
				Date sysDate = new Date();
				SimpleDateFormat year = new SimpleDateFormat("yyyy", Locale.US);
				SimpleDateFormat month = new SimpleDateFormat("MMMMMM",
						Locale.US);
				SimpleDateFormat day = new SimpleDateFormat("dd", Locale.US);
				String sysDay = day.format(sysDate);
				String sysMonth = month.format(sysDate);
				String sysYear = year.format(sysDate);
				if (sysDay.equalsIgnoreCase("01")) {
					sysDay = "01st";
				} else if (sysDay.equalsIgnoreCase("02")) {
					sysDay = "2nd";
				} else if (sysDay.equalsIgnoreCase("03")) {
					sysDay = "3rd";
				} else {
					sysDay = sysDay + "th";
				}
				String signDate = "Signed on the\b" + sysDay + "\bday of\b"
				+ sysMonth + "\b" + sysYear;
				tableCreation.setTable(7);
				tableCreation.insertCell(signDate, sfont, Rectangle.NO_BORDER,
						3, 0);
				tableCreation.insertCell(branchName, sfont1,
						Rectangle.NO_BORDER, 4, 0);
				try {
					jpg1 = Image.getInstance(getSignedImagePath());
				} catch (Exception de) {
					LogManager.debug(de);

				}
				jpg1.scalePercent(30, 30);
				// String palce =
				// pdfDataSelect.getBrokerBranchName(getQuoteNo());
				tableCreation.insertCell("Signed at\b" + place, sfont,
						Rectangle.NO_BORDER, 3, 0);
				tableCreation.insertCell(jpg1, Rectangle.NO_BORDER, 4, 2);
				// tableCreation.insertCell("",sfont1,Rectangle.NO_BORDER, 4,
				// 2);
				String broker = "";
				String entered = "";
				if (homePdfDetails.size() > 0) {
					broker = homePdfDetails.get(BRO_COMPANY_NAME) == null ? ""
							: (String) homePdfDetails.get(BRO_COMPANY_NAME);
					String quoteLogin = homePdfDetails.get(POS_LOGINID) == null ? ""
							: (String) homePdfDetails.get(POS_LOGINID);
					if (quoteLogin.length() > 0) {
						entered = pdfDataSelect.getQuotedName(quoteLogin);
					}
					if (entered.length() <= 0) {
						entered = broker;
					}

				}
				tableCreation.insertCell("Entered / Approved By: " + entered,
						sfont, Rectangle.NO_BORDER, 3, 0);
				tableCreation.insertCell("Authorised Signatory", sfont1,
						Rectangle.NO_BORDER, 4, 2);
				tableCreation.setTableSpacing(25f);
				document.add(tableCreation.getTable());
				// New PDF page Lein Clauses
				String finance[][] = new String[0][0];
				if (financeFalg == 1) {
					finance = pdfDataSelect.getFinanceDetails(getQuoteNo());
					if (finance.length > 0) {
						String str1 = "";
						if (userModeSC.equalsIgnoreCase(TEST)
								&& option.equalsIgnoreCase(DRAFT)) {
							str1 = "Quote No " + getQuoteNo();
						} else if (userModeSC.equalsIgnoreCase(TEST)
								&& !option.equalsIgnoreCase(DRAFT)) {
							str1 = "Policy No " + getQuoteNo();
						} else if (option.equalsIgnoreCase(DRAFT)) {
							str1 = "Quote No " + getQuoteNo();
						} else {
							str1 = "Policy No " + polno;
						}
						String financeName = "";
						String financePhone = "";
						String financeAmt = "";
						String financePOBOX = "";
						document.newPage();
						tableCreation.setTable(5);
						tableCreation.insertCell(
								"Clause attached to & forming Part of " + str1,
								sfont1, Rectangle.NO_BORDER, 5, 0);
						// tableCreation.insertCell("Endorsement No.1 -",
						// sfont1, Rectangle.NO_BORDER, 5, 0);
						tableCreation.setTableSpacing(5f);
						document.add(tableCreation.getTable());

						if (finance.length == 1) {
							financeName = finance[0][0] == null ? ""
									: finance[0][0];
							financePhone = finance[0][2] == null ? ""
									: finance[0][2];
							financeAmt = finance[0][1] == null ? ""
									: finance[0][1];
							financePOBOX = finance[0][3] == null ? ""
									: finance[0][3];

							financeName = financeName + "\b&\\OR\b" + leinName;

							if (financePhone.length() > 0) {
								financePhone = ", Ph. " + financePhone;
							} else {
								financePhone = "";
							}
							if (financePOBOX.length() > 0) {
								financePOBOX = ", PO Box Number "
									+ financePOBOX;
							} else {
								financePOBOX = "";
							}
							tableCreation.setTable(5);
							tableCreation.insertCell("LIEN CLAUSE", sfontb1,
									Rectangle.NO_BORDER, 5, 0);
							tableCreation
							.insertCell(
									"Loss, if any, payable under this endorsement, "
									+ currencyType
									+ ".\b"
									+ financeAmt
									+ "\bshall be paid to "
									+ financeName
									+ " "
									+ financePhone
									+ " "
									+ financePOBOX
									+ ", as their interest may appear and whose receipt will be a valid discharge.",
									spfont, Rectangle.NO_BORDER, 5, 0);
							tableCreation
							.insertCell(
									"\n\nSubject otherwise to the terms, conditions & limitations of the Policy",
									spfont, Rectangle.NO_BORDER, 5, 0);
							tableCreation.setTableSpacing(5f);
							document.add(tableCreation.getTable());
						} else if (finance.length > 1) {
							double totalLoansum = 0;
							String msg1 = "";
							for (int f = 0; f < finance.length; f++) {
								financeName = finance[f][0] == null ? ""
										: finance[f][0];
								financePhone = finance[f][2] == null ? ""
										: finance[f][2];
								financeAmt = finance[f][1] == null ? ""
										: finance[f][1];
								financePOBOX = finance[f][3] == null ? ""
										: finance[f][3];
								if (financeAmt.length() > 0) {
									msg1 = msg1 + "upto " + currencyType + ". "
									+ financeAmt;
									totalLoansum = totalLoansum
									+ Double.parseDouble(financeAmt);
								}
								if (financeName.length() > 0) {
									msg1 = msg1 + " shall be paid to "
									+ financeName + "\b&\\OR\b"
									+ leinName + ", Ph. "
									+ financePhone;
								}
								if (financePOBOX.length() > 0) {
									msg1 = msg1 + ", PO Box Number "
									+ financePOBOX;
								}
								if ((f + 2) == finance.length) {
									msg1 = msg1 + " and ";
								} else {
									msg1 = msg1 + ", ";
								}

							}
							tableCreation.setTable(5);
							tableCreation.insertCell("LIEN CLAUSE", sfontb1,
									Rectangle.NO_BORDER, 5, 0);
							tableCreation
							.insertCell(
									"Loss, if any, payable under this Policy "
									+ msg1
									+ " the same being part of Total Sum Insured "
									+ currencyType
									+ ". "
									+ totalLoansum
									+ ", and such part of any monies so paid as may relate to the interests of other parties insured hereunder shall be received by the Bank as Agents for such other parties and that the receipt of the Bank shall be a complete discharge of the Company therefore and shall be binding on all parties insured hereunder.",
									spfont, Rectangle.NO_BORDER, 5, 0);
							tableCreation
							.insertCell(
									"\n\nSubject otherwise to the terms, conditions & limitations of the Policy",
									spfont, Rectangle.NO_BORDER, 5, 0);
							tableCreation.setTableSpacing(5f);
							document.add(tableCreation.getTable());
						}
						tableCreation.setTable(5);
						tableCreation.insertCell("", sfont1,
								Rectangle.NO_BORDER, 2, 0);
						tableCreation.insertCell(jpg1, Rectangle.NO_BORDER, 3,
								1);
						tableCreation.insertCell("Date : " + sdate, sfont1,
								Rectangle.NO_BORDER, 2, 0);
						// tableCreation.insertCell(, sfont,
						// Rectangle.NO_BORDER, 1, 0);
						tableCreation.insertCell("For the Company", sfont1,
								Rectangle.NO_BORDER, 3, 1);
						tableCreation.setTableSpacing(5f);
						document.add(tableCreation.getTable());
					}
				}
			} catch (DocumentException de) {
				LogManager.debug(de);
			}
			document.close();
		} else if (option.equalsIgnoreCase("Debit")) // Office
		{
			// java.text.NumberFormat fmt=new
			// java.text.DecimalFormat("##,##0.00");
			Document document = new Document(PageSize.A4, 50, 50, 120, 60);
			// float premium = 0.00f;
			double commision = 0.0f;
			double com = 0.0f;
			String excessandSign[][] = new String[0][0];
			double totPre = 0.0;
			String preOriginal = "";
			String exPre = "";
			String sign = "";
			String wordPre = "";
			double finalTot=0.0;
			HomeAdminReferralBean adminRef = new HomeAdminReferralBean();
			try {
				try {
					writer = PdfWriter.getInstance(document,
							new FileOutputStream(getFilePath()));
				} catch (Exception e) {
					LogManager.debug(e);
				}
				document.open();
				// ///////Header and Footer/////////
				try {
					endPage = new HeaderFooterImage();
					writer.setPageEvent(endPage);
					endPage.setHeaderImagePath(getHeaderImagePath());
					endPage.setFooterImagePath(getFooterImagePath());
					endPage.setOption(option);
				} catch (Exception exImageHeader) {
					LogManager.debug(exImageHeader);
				}
				try {
					com = Double.parseDouble(pdfDataSelect.getCommision(
							getLoginId(), getPid()));

					if (homePdfDetails.size() > 0) {
						//
						preOriginal = (homePdfDetails.get(POS_PREMIUM) == null ? ""
								: (String) homePdfDetails.get(POS_PREMIUM));
						excessandSign = pdfDataSelect.getExcessandSign(QuoteNo);
						if (excessandSign.length > 0) {
							exPre = excessandSign[0][0] == null ? ""
									: excessandSign[0][0];
							sign = excessandSign[0][1] == null ? ""
									: excessandSign[0][1];
						}
						if (preOriginal.length() > 0 && exPre.length() > 0) {
							if (sign.equalsIgnoreCase("+")) {
								totPre = Double.parseDouble(preOriginal)
								+ Double.parseDouble(exPre);
							} else if (sign.equalsIgnoreCase("-")) {
								totPre = Double.parseDouble(preOriginal)
								- Double.parseDouble(exPre);
							} else {
								totPre = Double.parseDouble(preOriginal)
								+ Double.parseDouble(exPre);
							}
						} else if (preOriginal.length() > 0 && totPre == 0) {
							totPre = Double.parseDouble(preOriginal);
						}
						if (totPre != 0) {
							commision = totPre * com / 100;

						}
						finalTot = java.lang.Math.round(totPre)
						- java.lang.Math.round(commision);

						String wordPreStr = Long.toString(Math.round(finalTot));
						StringTokenizer str = new StringTokenizer(wordPreStr,
						".");
						if (str.hasMoreTokens()) {
							wordPre = numword.convertNumToWord(Integer
									.parseInt(str.nextToken()));
						}
					}
				} catch (Exception e) {
					LogManager.info(e);
				}
				// ////////// Font Declaration ////////////
				Font sfont = new Font(Font.HELVETICA, 8);
				Font sfont1 = new Font(Font.HELVETICA, 9, Font.BOLD);
				Font bfont = new Font(Font.HELVETICA, 8, Font.BOLD);
				Font font1 = new Font(Font.HELVETICA, 12, Font.BOLD);
				font1.setStyle(Font.UNDERLINE);
				Font sfont2 = new Font(Font.HELVETICA, 8, Font.UNDERLINE);
				// //////// Table 10 --Third Page-- //////
				// document.newPage();
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
				// /////// Table 11 ///////
				tableCreation.setTable(7);
				tableCreation.insertCell(Rectangle.TOP, 7);
				tableCreation.insertCell(Rectangle.NO_BORDER, 3);
				tableCreation.insertCell("DEBIT NOTE", sfont2,
						Rectangle.NO_BORDER, 1, 0);
				tableCreation.insertCell(Rectangle.NO_BORDER, 3);
				tableCreation.insertCell(Rectangle.BOTTOM, 7);
				tableCreation.setTableSpacing(30f);
				document.add(tableCreation.getTable());
				// //////// Table 12 ////////
				tableCreation.setTable(7);
				tableCreation
				.insertCell("To", sfont, Rectangle.NO_BORDER, 5, 0);
				tableCreation.insertCell(Rectangle.NO_BORDER, 2);
				tableCreation.insertCell(Rectangle.NO_BORDER, 5);
				if (homePdfDetails.isEmpty()) {
					tableCreation.insertCell("Debit Note No   : ", sfont,
							Rectangle.NO_BORDER, 2, 0);
				} else {
					tableCreation
					.insertCell(
							"Debit Note No    : "
							+ (homePdfDetails
									.get("pos.DEBIT_NOTE_NO") == null ? ""
											: (String) homePdfDetails
											.get("pos.DEBIT_NOTE_NO")),
											sfont, Rectangle.NO_BORDER, 2, 0);
				}
				tableCreation.insertCell(Rectangle.NO_BORDER, 5);
				if (homePdfDetails.isEmpty()) {
					tableCreation.insertCell("Debit Note Date : ", sfont,
							Rectangle.NO_BORDER, 2, 0);
				} else {
					tableCreation.insertCell("Debit Note Date : "
							+ (homePdfDetails.get(POS_DEBIT_DATE) == null ? ""
									: (String) homePdfDetails
									.get(POS_DEBIT_DATE)), sfont,
									Rectangle.NO_BORDER, 2, 0);

				}
				if (homePdfDetails.isEmpty()) {
					tableCreation.insertCell("", sfont1, Rectangle.NO_BORDER,
							5, 0);
					tableCreation.insertCell(Rectangle.NO_BORDER, 2);
					tableCreation.insertCell("", sfont, Rectangle.NO_BORDER, 5,
							0);
					tableCreation.insertCell(Rectangle.NO_BORDER, 2);
					tableCreation.insertCell("", sfont, Rectangle.NO_BORDER, 5,
							0);
					tableCreation.insertCell(Rectangle.NO_BORDER, 2);

				} else {
					String broAdd = (homePdfDetails.get("bro.ADDRESS1").equals(
					"") ? "" : ((String) homePdfDetails
							.get("bro.ADDRESS1") + " "))
							+ (homePdfDetails.get("bro.ADDRESS2").equals("") ? ""
									: ((String) homePdfDetails
											.get("bro.ADDRESS2") + " "))
											+ (homePdfDetails.get("bro.ADDRESS3").equals("") ? ""
													: ((String) homePdfDetails
															.get("bro.ADDRESS3")))
															+ (homePdfDetails.get("bro.CITY").equals("") ? ""
																	: ("\n" + (String) homePdfDetails
																			.get("bro.CITY")))
																			+ (homePdfDetails.get("bro.POBOX").equals("") ? ""
																					: ("\n"
																							+ (String) homePdfDetails
																							.get("bro.POBOX") + ",")
																							+ (homePdfDetails
																									.get("bro.COUNTRY").equals(
																									"") ? ""
																											: ("\n" + (String) homePdfDetails
																													.get("bro.COUNTRY")))
																													+ "\nU A E.");

					tableCreation.insertCell((homePdfDetails
							.get(BRO_COMPANY_NAME) == null ? ""
									: (String) homePdfDetails.get(BRO_COMPANY_NAME)),
									sfont1, Rectangle.NO_BORDER, 5, 0);
					tableCreation.insertCell(Rectangle.NO_BORDER, 2);
					tableCreation.insertCell(broAdd, sfont,
							Rectangle.NO_BORDER, 5, 0);
					tableCreation.insertCell(Rectangle.NO_BORDER, 2);
				}
				tableCreation.setTableSpacing(10f);
				document.add(tableCreation.getTable());
				// ///////// Table 13 //////////
				tableCreation.setTable(7);
				tableCreation.insertCell("INSURED :", sfont1,
						Rectangle.NO_BORDER, 1, 0);
				if (fullName.length() > 0) {
					tableCreation.insertCell(fullName, sfont,
							Rectangle.NO_BORDER, 6, 0);
				} else {
					tableCreation.insertCell("", sfont, Rectangle.NO_BORDER, 6,
							0);
				}
				tableCreation
				.insertCell(
						"In accordance with your instructions we have issued the attached documentation and debited your account as per details\nshown hereunder :",
						sfont, Rectangle.NO_BORDER, 7, 0);
				tableCreation.setTableSpacing(50f);
				document.add(tableCreation.getTable());
				// ///////// Table 14 /////////
				tableCreation.setTable(7);
				tableCreation.insertCell("DESCRIPTION", bfont, Rectangle.TOP,
						6, 0);
				tableCreation.insertCell("AMOUNT In " + currencyType, bfont,
						Rectangle.TOP, 1, 0);
				pname = adminRef.getProductName(getPid(), getBranch());
				if (homePdfDetails.size() > 0
						&& !userModeSC.equalsIgnoreCase(TEST)) {
					tableCreation.insertCell("BEING THE PREMIUM DUE ON\b"
							+ pname
							+ "\bPOLICY NO : "
							+ (homePdfDetails.get(POS_POLICY_NO) == null ? ""
									: (String) homePdfDetails
									.get(POS_POLICY_NO)), sfont,
									Rectangle.TOP, 6, 0);
				} else {
					tableCreation.insertCell("BEING THE PREMIUM DUE ON\b"
							+ pname + "\bQUOTE NO : " + getQuoteNo(), sfont,
							Rectangle.TOP, 6, 0);
				}
				pname = "";
				tableCreation.insertCell(fmt.format(totPre), sfont,
						Rectangle.TOP, 1, 1);
				tableCreation.insertCell(Rectangle.NO_BORDER, 7);
				if (com > 0) {
					tableCreation.insertCell("Less Commission @ "
							+ fmt2.format(com) + "%", sfont, Rectangle.BOTTOM,
							6, 0);
					tableCreation.insertCell(fmt.format(java.lang.Math.round(commision)), sfont,
							Rectangle.BOTTOM, 1, 1);
					tableCreation.insertCell(Rectangle.NO_BORDER, 7);
				} else {
					tableCreation.insertCell(Rectangle.BOTTOM, 7);
				}
				tableCreation.insertCell(currencyName + "\b" + wordPre
						+ "\bOnly", sfont, Rectangle.NO_BORDER, 5, 0);
				tableCreation.insertCell("Total:", sfont, Rectangle.NO_BORDER,
						1, 0);
				tableCreation.insertCell(fmt.format(finalTot),
						sfont, Rectangle.NO_BORDER, 1, 1);
				tableCreation.insertCell(Rectangle.BOTTOM, 7);
				tableCreation.setTableSpacing(10f);
				document.add(tableCreation.getTable());
				// ///// Table 15 ////////
				tableCreation.setTable(7);
				tableCreation.insertCell("PLEASE NOTE :", sfont1,
						Rectangle.NO_BORDER, 2, 0);
				tableCreation.insertCell(Rectangle.NO_BORDER, 5);
				tableCreation
				.insertCell(
						"Your remittance in respect of the above transaction should be forwarded to us in order to ensure continuity of cover",
						sfont, Rectangle.NO_BORDER, 7, 0);
				tableCreation
				.insertCell(
						"We would appreciate you contacting us immediately if you have any queries relating to the above details\nor the attached documents",
						sfont, Rectangle.NO_BORDER, 7, 0);
				tableCreation.setTableSpacing(10f);
				document.add(tableCreation.getTable());
				// /////// Table 16 ////////////
				tableCreation.setTable(7);
				tableCreation.insertCell("For : " + branchName, sfont1,
						Rectangle.NO_BORDER, 4, 0);
				tableCreation.insertCell(Rectangle.NO_BORDER, 3);
				try {
					jpg1 = Image.getInstance(getSignedImagePath());
					// jpg1.scaleAbsoluteWidth(30f);
					// jpg1.scaleAbsoluteHeight(30f);
					jpg1.scalePercent(30, 30);
				} catch (Exception de) {
					LogManager.debug(de);
				}
				tableCreation.insertCell(jpg1, Rectangle.NO_BORDER, 4, 2);
				tableCreation.insertCell(Rectangle.NO_BORDER, 3);
				tableCreation.insertCell("Authorised Signatory", sfont1,
						Rectangle.NO_BORDER, 4, 2);
				tableCreation.insertCell(Rectangle.NO_BORDER, 3);
				tableCreation.setTableSpacing(55f);
				document.add(tableCreation.getTable());

			} catch (DocumentException de) {
				LogManager.debug(de);
			}
			document.close();
		} else if (option.equalsIgnoreCase("PrintQuote")) {

			Document document = new Document(PageSize.A4, 50, 50, 120, 60);
			try {
				try {
					writer = PdfWriter.getInstance(document,
							new FileOutputStream(getFilePath()));
				} catch (Exception e) {
					LogManager.debug(e);
				}
				document.open();
				// ///////Header and Footer/////////
				try {
					endPage = new HeaderFooterImage();
					writer.setPageEvent(endPage);
					endPage.setHeaderImagePath(getHeaderImagePath());
					endPage.setFooterImagePath(getFooterImagePath());
					endPage.setBetterImagePath(getBetterImagePath());
					endPage.setEmptyImage(getEmptyImage());
					endPage.setPid(getPid());
					endPage.setOption(option);
				} catch (Exception exImageHeader) {
					LogManager.debug(exImageHeader);
				}
				fullName = "";
				String occupation = "";
				String gender = "";
				String nationality = "";
				String dob = "";
				String pobox = "";
				String emirate = "";
				String address = "";
				String insureLoc = "";
				String telephone = "";
				String mobile = "";
				String email = "";
				String fax = "";
				String broker = "";
				bedrooms = "";
				java.util.Calendar calendar = java.util.Calendar.getInstance();
				String sysDay = Integer.toString(calendar
						.get(java.util.Calendar.DATE));
				String sysMonth = Integer.toString(calendar
						.get(java.util.Calendar.MONTH) + 1);
				String sysYear = Integer.toString(calendar
						.get(java.util.Calendar.YEAR));
				if (homePdfDetails.size() > 0) {

					String lastName = (homePdfDetails.get(PER_FIRST_NAME) == null ? ""
							: (String) homePdfDetails.get(PER_FIRST_NAME))
							.trim();
					String companyName = (homePdfDetails.get(PER_COMPANYNAME) == null ? ""
							: (String) homePdfDetails.get(PER_COMPANYNAME))
							.trim();
					broker = (homePdfDetails.get(BRO_COMPANY_NAME) == null ? ""
							: ((String) homePdfDetails.get(BRO_COMPANY_NAME)))
							.trim();
					companyName = companyName.trim();
					if (!"".equals(lastName) || lastName.length() > 0) {
						fullName = lastName;
					} else if (!"".equals(companyName)
							|| companyName.length() > 0) {
						fullName = companyName;
					}
					//

					fullName = (homePdfDetails.get(PER_TITLE) == null ? ""
							: (((String) homePdfDetails.get(PER_TITLE))
									.equalsIgnoreCase(SELECT) ? ""
											: (String) homePdfDetails.get(PER_TITLE)
											+ " "))
											+ "\b" + fullName;
					if (gender == null) {
						gender = "";
					}
					nationality = homePdfDetails.get("per.nationality") == null ? ""
							: (((String) homePdfDetails.get("per.nationality"))
									.equalsIgnoreCase(SELECT) ? ""
											: (String) homePdfDetails
											.get("per.nationality"));
					dob = homePdfDetails.get("per.dob") == null ? ""
							: (((String) homePdfDetails.get("per.dob"))
									.equalsIgnoreCase(SELECT) ? ""
											: (String) homePdfDetails.get("per.dob"));
					pobox = homePdfDetails.get(PER_POBOX) == null ? ""
							: (String) homePdfDetails.get(PER_POBOX);
					emirate = homePdfDetails.get(PER_EMIRATE) == null ? ""
							: (((String) homePdfDetails.get(PER_EMIRATE))
									.equalsIgnoreCase(SELECT) ? ""
											: (String) homePdfDetails.get(PER_EMIRATE));
					address = (homePdfDetails.get(PER_ADDRESS1) == null ? ""
							: (String) homePdfDetails.get(PER_ADDRESS1));
					// insureLoc =
					// (homePdfDetails.get("per.address2")!=null?(String)homePdfDetails.get("per.address2"):"");
					telephone = homePdfDetails.get("per.telephone") == null ? ""
							: (String) homePdfDetails.get("per.telephone");
					mobile = homePdfDetails.get("per.mobile") == null ? ""
							: (String) homePdfDetails.get("per.mobile");
					email = homePdfDetails.get("per.email") == null ? ""
							: (String) homePdfDetails.get("per.email");
					fax = homePdfDetails.get("per.fax") == null ? ""
							: (String) homePdfDetails.get("per.fax");

					occupation = homePdfDetails.get("per.occupation") == null ? ""
							: ((String) homePdfDetails.get("per.occupation"));
					bedrooms = homePdfDetails.get(POS_BEDROOMS) == null ? ""
							: (String) homePdfDetails.get(POS_BEDROOMS);
				}
				insureLoc = pdfDataSelect.getBuildingLocation(QuoteNo);
				if (clauses.equals("") || clauses == null
						|| clauses.length() <= 0) {
					clauses = pdfDataSelect.getClauses(QuoteNo);
				}
				Font psfont = new Font(Font.HELVETICA, 8);
				Font pbfont = new Font(Font.HELVETICA, 8, Font.BOLD);
				Font font2 = new Font(Font.HELVETICA, 10, Font.BOLD);
				Font pbbfont = new Font(Font.HELVETICA, 9, Font.BOLD,
						Color.WHITE);
				// document.newPage();
				tableCreation.setTable(8);
				tableCreation.insertCell(Rectangle.NO_BORDER, 8);
				tableCreation.insertCell(Rectangle.NO_BORDER, 8);
				tableCreation.insertCell(Rectangle.NO_BORDER, 8);
				tableCreation.insertCell(Rectangle.NO_BORDER, 8);
				tableCreation.insertCell(Rectangle.NO_BORDER, 8);
				tableCreation.insertCell(Rectangle.NO_BORDER, 8);
				tableCreation.insertCell("", psfont, Rectangle.NO_BORDER, 8, 2);
				tableCreation.insertCell("", psfont, Rectangle.NO_BORDER, 8, 2);
				tableCreation.insertCell("", psfont, Rectangle.NO_BORDER, 8, 2);
				tableCreation.insertCell("HOME INSURANCE QUOTATION", font2,
						Rectangle.NO_BORDER, 8, 2);
				tableCreation.insertCell(Rectangle.NO_BORDER, 8);
				tableCreation.insertCell(Rectangle.NO_BORDER, 8);
				tableCreation.insertCell(Rectangle.NO_BORDER, 8);
				tableCreation.setTableSpacing(100f);
				document.add(tableCreation.getTable());
				tableCreation.setTable(6);
				tableCreation.insertCell("Proposer's Information", pbbfont,
						Rectangle.BOX, 6, 0, "BG");
				tableCreation.insertCell("Proposer Name", pbfont,
						Rectangle.BOX, 1, 0);
				tableCreation.insertCell((fullName.length() <= 0 ? "\b"
						: fullName), psfont, Rectangle.BOX, 1, 0);
				tableCreation.insertCell("DOB", pbfont, Rectangle.BOX, 1, 0);
				tableCreation.insertCell(dob, psfont, Rectangle.BOX, 1, 0);
				tableCreation.insertCell("Occupation", pbfont, Rectangle.BOX,
						1, 0);
				tableCreation.insertCell((occupation.length() <= 0 ? "\b"
						: occupation), psfont, Rectangle.BOX, 1, 0);
				tableCreation.insertCell("Nationality", pbfont, Rectangle.BOX,
						1, 0);
				tableCreation.insertCell((nationality.length() <= 0 ? "\b"
						: nationality), psfont, Rectangle.BOX, 1, 0);
				tableCreation
				.insertCell("Emirate", pbfont, Rectangle.BOX, 1, 0);
				tableCreation.insertCell((emirate.length() <= 0 ? "\b"
						: emirate), psfont, Rectangle.BOX, 1, 0);
				tableCreation
				.insertCell("Address", pbfont, Rectangle.BOX, 1, 0);
				tableCreation.insertCell((address.length() <= 0 ? "\b"
						: address), psfont, Rectangle.BOX, 1, 0);
				tableCreation.insertCell("P.O. Box", pbfont, Rectangle.BOX, 1,
						0);
				tableCreation.insertCell((pobox.length() <= 0 ? "\b" : pobox),
						psfont, Rectangle.BOX, 1, 0);
				tableCreation.insertCell("Telephone", pbfont, Rectangle.BOX, 1,
						0);
				tableCreation.insertCell((telephone.length() <= 0 ? "\b"
						: telephone), psfont, Rectangle.BOX, 1, 0);
				tableCreation.insertCell("Mobile", pbfont, Rectangle.BOX, 1, 0);
				tableCreation.insertCell(
						(mobile.length() <= 0 ? "\b" : mobile), psfont,
						Rectangle.BOX, 1, 0);
				tableCreation.insertCell("Fax", pbfont, Rectangle.BOX, 1, 0);
				tableCreation.insertCell((fax.length() <= 0 ? "\b" : fax),
						psfont, Rectangle.BOX, 1, 0);
				tableCreation.insertCell("E-Mail", pbfont, Rectangle.BOX, 1, 0);
				tableCreation.insertCell((email.length() <= 0 ? "\b" : email),
						psfont, Rectangle.BOX, 1, 0);
				tableCreation.insertCell("Insured Location", pbfont,
						Rectangle.BOX, 1, 0);
				tableCreation.insertCell((insureLoc.length() <= 0 ? "\b"
						: insureLoc), psfont, Rectangle.BOX, 1, 0);
				tableCreation.setTableSpacing(7f);
				document.add(tableCreation.getTable());
				tableCreation.setTable(6);
				tableCreation.insertCell("Policy Information", pbbfont,
						Rectangle.BOX, 6, 0, "BG");
				tableCreation.insertCell("Quote No", pbfont, Rectangle.BOX, 1,
						0);
				tableCreation.insertCell(QuoteNo, psfont, Rectangle.BOX, 1, 0);
				tableCreation.insertCell("Date", pbfont, Rectangle.BOX, 1, 0);
				tableCreation.insertCell(
						(sysDay + "-" + sysMonth + "-" + sysYear), psfont,
						Rectangle.BOX, 1, 0);
				double totPre = 0.0;
				/*	String excessandSign[][] = new String[0][0];
				String exPre = "";
				String sign = "";*/

				bname = "";
				banks = pdfDataSelect.getFinanceBankName(QuoteNo);
				for (int b = 0; b < banks.length; b++) {
					bname = bname + banks[b][0] + ",";
				}
				if (bname.length() > 0) {
					bname = bname.substring(0, bname.length() - 1);
				}
				if (homePdfDetails.size() > 0) {
					final String preOriginal = (homePdfDetails.get("pos.OVERALL_PREMIUM") == null ? "": (String) homePdfDetails.get("pos.OVERALL_PREMIUM"));
					/*excessandSign = cover.getExcessandSign(QuoteNo);
					if (excessandSign.length > 0) {
						exPre = excessandSign[0][0] == null ? ""
								: excessandSign[0][0];
						sign = excessandSign[0][1] == null ? ""
								: excessandSign[0][1];
					}*/
					if(!preOriginal.equals("") && preOriginal.length()>0 && totPre==0){
						totPre = Double.parseDouble(preOriginal);
					}
					/*if (preOriginal.length() > 0 && exPre.length() > 0) {
						if ("+".equalsIgnoreCase(sign)) {
							totPre = Double.parseDouble(preOriginal)
									+ Double.parseDouble(exPre);
						} else if ("-".equalsIgnoreCase(sign)) {
							totPre = Double.parseDouble(preOriginal)
									- Double.parseDouble(exPre);
						} else {
							totPre = Double.parseDouble(preOriginal)
									+ Double.parseDouble(exPre);
						}
					} else if (!"".equals(preOriginal)
							&& preOriginal.length() > 0 && totPre == 0) {
						totPre = Double.parseDouble(preOriginal);
					}*/
				}
				tableCreation
				.insertCell("Premium", pbfont, Rectangle.BOX, 1, 0);
				tableCreation.insertCell(fmt.format(totPre), psfont,
						Rectangle.BOX, 1, 0);
				tableCreation
				.insertCell("Product", pbfont, Rectangle.BOX, 1, 0);
				tableCreation.insertCell("Home Insurance", psfont,
						Rectangle.BOX, 1, 0);
				if (loginId.equalsIgnoreCase("bhomes")) {
					tableCreation.insertCell("", pbfont, Rectangle.BOX, 1, 0);
					tableCreation.insertCell("", psfont, Rectangle.BOX, 1, 0);
				} else {
					tableCreation.insertCell("Broker", pbfont, Rectangle.BOX,
							1, 0);
					tableCreation.insertCell(broker, psfont, Rectangle.BOX, 1,
							0);
				}
				tableCreation.insertCell("Named Interest", pbfont,
						Rectangle.BOX, 1, 0);
				tableCreation.insertCell((bname.length() <= 0 ? "\b" : bname),
						psfont, Rectangle.BOX, 1, 0);
				tableCreation.setTableSpacing(7f);
				document.add(tableCreation.getTable());
				// Sum Insured and Premium //
				tableCreation.setTable(6);
				tableCreation.insertCell("Sum Insured and Premium", pbbfont,
						Rectangle.BOX, 6, 0, "BG");

				tableCreation.insertCell("SNO", pbbfont, Rectangle.BOX, 1, 0,
				"BG");
				tableCreation.insertCell("COVERAGE", pbbfont, Rectangle.BOX, 3,
						0, "BG");
				tableCreation.insertCell("SUM INSURED [" + currencyType + "]",
						pbbfont, Rectangle.BOX, 2, 2, "BG");
				//tableCreation.insertCell("PREMIUM [" + currencyType + "]",	pbbfont, Rectangle.BOX, 1, 0, "BG");
				double pre = 0.0;
				double sum = 0.0;
				//String excesPremium = "";
				if (homePdfDetails.size() > 0) {

					size = Integer.parseInt((String) homePdfDetails.get("covtra.length"));
					String Sno = "";
					for (int i = 0; i < size; i++) {
						Sno = (String) homePdfDetails.get(COVTRA_COVER_ID + i);

						/*DataSelect dataSelect = new DataSelect();
						String[][] result = dataSelect.getCoverPremiumStatus(pid, Sno);
						excesPremium = result[0][0] == null ? "" : result[0][0];
						 */
						if (!(((String) homePdfDetails.get(COVTRA_PRO_SUM + i))
								.equalsIgnoreCase("1"))
								&& !(((String) homePdfDetails
										.get(COVTRA_PRO_SUM + i))
										.equalsIgnoreCase("0"))) {

							sum += Double.parseDouble(homePdfDetails
									.get(COVTRA_PRO_SUM + i) == "" ? "0"
											: (String) homePdfDetails
											.get(COVTRA_PRO_SUM + i));
						}
						pre += Double.parseDouble(homePdfDetails
								.get(COVTRA_PREMIUM + i) == "" ? "0"
										: (String) homePdfDetails.get(COVTRA_PREMIUM
												+ i));
						tableCreation.insertCell(Integer.toString(i + 1),
								psfont, Rectangle.NO_BORDER, 1, 0);
						tableCreation.insertCell((String) homePdfDetails
								.get("covtra.Product_description" + i), psfont,
								Rectangle.NO_BORDER, 3, 0);

						if ((((String) homePdfDetails.get(COVTRA_PRO_SUM + i))
								.equalsIgnoreCase("1"))
								&& !(((String) homePdfDetails
										.get(COVTRA_PRO_SUM + i))
										.equalsIgnoreCase("0"))) {
							tableCreation.insertCell("", psfont,
									Rectangle.NO_BORDER, 2, 0);
						} else {
							tableCreation
							.insertCell(
									"\b\b\b\b\b"
									+ fmt
									.format(Double
											.parseDouble(homePdfDetails
													.get(COVTRA_PRO_SUM
															+ i) == "" ? "0"
																	: (String) homePdfDetails
																	.get(COVTRA_PRO_SUM
																			+ i))),
																			psfont, Rectangle.NO_BORDER, 1, 1);
							tableCreation.insertCell("", psfont,
									Rectangle.NO_BORDER, 1, 0);
						}

						//tableCreation.insertCell(fmt.format(Double.parseDouble(homePdfDetails.get(COVTRA_PREMIUM+ i) == "" ? "0": (String) homePdfDetails.get(COVTRA_PREMIUM + i))),psfont, Rectangle.NO_BORDER, 1, 1);

						if (Sno.equalsIgnoreCase("1")) {
							bill = 1;
						}
						if (Sno.equalsIgnoreCase("2")) {
							cons = 1;
						}
						if (Sno.equalsIgnoreCase("3")) {
							pers = 1;
						}
						if (Sno.equalsIgnoreCase("2")|| Sno.equalsIgnoreCase("3")) {
							String source;
							String desc;
							String contents[][] = new String[0][0];
							contents = pdfDataSelect.homeCoverageTotalTransactionSelect(QuoteNo, Sno);
							double temp_count = 0;
							for (int c = 0; c < contents.length; c++) {
								desc = contents[c][4] == null ? ""
										: contents[c][4];
								source = contents[c][5] == null ? "0"
										: contents[c][5];
								temp_count = temp_count
								+ Double
								.parseDouble(contents[c][5] == null ? "0"
										: contents[c][5]);
								tableCreation.insertCell("", psfont,
										Rectangle.NO_BORDER, 1, 0);
								tableCreation.insertCell("\b\b\b\b\b\b("
										+ (c + 1) + ")\b\b" + desc, psfont,
										Rectangle.NO_BORDER, 2, 0);
								tableCreation.insertCell(fmt.format(Double
										.parseDouble(source)), psfont,
										Rectangle.NO_BORDER, 1, 1);
								tableCreation.insertCell("", psfont,
										Rectangle.NO_BORDER, 2, 0);

							}
							if (temp_count > 0) {

								tableCreation.insertCell("", psfont,
										Rectangle.NO_BORDER, 1, 0);
								tableCreation.insertCell("", psfont,
										Rectangle.NO_BORDER, 2, 0);
								tableCreation.insertCell("", psfont,
										Rectangle.TOP, 1, 1);
								tableCreation.insertCell("", psfont,
										Rectangle.NO_BORDER, 2, 0);

								tableCreation.insertCell("", psfont,
										Rectangle.NO_BORDER, 1, 0);
								tableCreation.insertCell("", psfont,
										Rectangle.NO_BORDER, 2, 0);
								tableCreation.insertCell(
										fmt.format(temp_count), psfont,
										Rectangle.BOTTOM, 1, 1);
								tableCreation.insertCell("", psfont,
										Rectangle.NO_BORDER, 2, 0);
							}
						}
					}
				}
				tableCreation.insertCell("", psfont, Rectangle.BOTTOM, 6, 0);

				/*String preStatus = "";
				if (exPre.length() > 0 && !"0".equals(exPre)) {

					if ("+".equalsIgnoreCase(sign)) {
						exPre = "+" + exPre;
						preStatus = "Additional";
					} else if ("-".equalsIgnoreCase(sign)) {
						exPre = "-" + exPre;
						preStatus = "Discount";
					}
					if (excesPremium.equalsIgnoreCase("Y")) {
						String excessPremium = cover
								.getContentExcessPremium(getQuoteNo());
						if (excessPremium.length() > 0
								&& !"0".equals(excessPremium)) {
							tableCreation.insertCell("", psfont,
									Rectangle.NO_BORDER, 1, 0);
							tableCreation.insertCell("", psfont,
									Rectangle.NO_BORDER, 2, 0);
							tableCreation.insertCell("Content Excess Premium ["
									+ currencyType + "]", psfont,
									Rectangle.NO_BORDER, 2, 0);
							tableCreation.insertCell(fmt.format(Double
									.parseDouble(excessPremium)), psfont,
									Rectangle.NO_BORDER, 1, 1);
						}
					}

					tableCreation.insertCell("", psfont, Rectangle.NO_BORDER,
							1, 0);
					tableCreation.insertCell("", psfont, Rectangle.NO_BORDER,
							2, 0);
					tableCreation.insertCell("Premium [" + currencyType + "]",
							psfont, Rectangle.NO_BORDER, 2, 0);
					tableCreation.insertCell(fmt.format(Double
							.parseDouble(preOriginal)), psfont,
							Rectangle.NO_BORDER, 1, 1);

					tableCreation.insertCell("", psfont, Rectangle.NO_BORDER,
							1, 0);
					tableCreation.insertCell("", psfont, Rectangle.NO_BORDER,
							2, 0);
					tableCreation.insertCell(preStatus + "\bPremium ["
							+ currencyType + "]", psfont, Rectangle.NO_BORDER,
							2, 0);
					tableCreation.insertCell(fmt.format(Double
							.parseDouble(exPre)), psfont, Rectangle.NO_BORDER,
							1, 1);

					tableCreation.insertCell("", psfont, Rectangle.NO_BORDER,
							1, 0);
					tableCreation.insertCell("", psfont, Rectangle.NO_BORDER,
							2, 0);
					tableCreation.insertCell("Total Premium Payable ["
							+ currencyType + "]", psfont, Rectangle.NO_BORDER,
							2, 0);
					tableCreation.insertCell(fmt.format(totPre), psfont,
							Rectangle.NO_BORDER, 1, 1);
				} else {
					if (excesPremium.equalsIgnoreCase("Y")) {
						String excessPremium = cover
								.getContentExcessPremium(getQuoteNo());
						if (excessPremium.length() > 0
								&& !"0".equals(excessPremium)) {
							tableCreation.insertCell("", psfont,
									Rectangle.NO_BORDER, 1, 0);
							tableCreation.insertCell("", psfont,
									Rectangle.NO_BORDER, 2, 0);
							tableCreation.insertCell("Content Excess Premium ["
									+ currencyType + "]", psfont,
									Rectangle.NO_BORDER, 2, 0);
							tableCreation.insertCell(fmt.format(Double
									.parseDouble(excessPremium)), psfont,
									Rectangle.NO_BORDER, 1, 1);
						}
					}
					tableCreation.insertCell("", psfont, Rectangle.NO_BORDER,
							1, 0);
					tableCreation.insertCell("", psfont, Rectangle.NO_BORDER,
							2, 0);
					tableCreation.insertCell("Total Premium [" + currencyType
							+ "]", psfont, Rectangle.NO_BORDER, 2, 0);
					tableCreation.insertCell(fmt.format(totPre), psfont,
							Rectangle.NO_BORDER, 1, 1);
				}*/
				tableCreation.insertCell("", psfont, Rectangle.NO_BORDER, 1, 0);
				tableCreation.insertCell("", psfont, Rectangle.NO_BORDER, 2, 0);  
				tableCreation.insertCell("Total Premium ["+currencyType+"]", psfont, Rectangle.NO_BORDER, 2, 0); 
				tableCreation.insertCell(fmt.format(totPre), psfont, Rectangle.NO_BORDER, 1, 1); 

				tableCreation.insertCell("Policy Start Date", pbfont,
						Rectangle.BOX, 2, 0);
				tableCreation.insertCell(
						(homePdfDetails.get(POS_EFF_DATE) == null ? ""
								: (String) homePdfDetails.get(POS_EFF_DATE)),
								psfont, Rectangle.BOX, 1, 0);
				tableCreation.insertCell("Policy End Date", pbfont,
						Rectangle.BOX, 2, 0);
				tableCreation
				.insertCell(
						(homePdfDetails.get(POS_EXPIRY_DATE) == null ? ""
								: (String) homePdfDetails
								.get(POS_EXPIRY_DATE)), psfont,
								Rectangle.BOX, 1, 0);
				tableCreation.setTableSpacing(5f);
				document.add(tableCreation.getTable());
				// Under writing //
				String[][] claimDetails = new String[0][0];
				claimDetails = cover.getClaimDetails(QuoteNo);
				for (int i = 0; i < claimDetails.length; i++) {
					tableCreation.setTable(6);
					tableCreation.insertCell("Underwriting Questions", pbbfont,
							Rectangle.BOX, 6, 0, "BG");
					tableCreation.insertCell("Is your home built of concrete",
							psfont, Rectangle.NO_BORDER, 5, 0);
					tableCreation.insertCell(((claimDetails[i][0] == null ? ""
							: claimDetails[i][0]).equalsIgnoreCase("Y") ? YES
									: "No"), psfont, Rectangle.NO_BORDER, 1, 0);
					tableCreation
					.insertCell(
							"Is your Home Used Solely as a private living accommodation for your  household",
							psfont, Rectangle.NO_BORDER, 5, 0);
					tableCreation.insertCell(((claimDetails[i][1] == null ? ""
							: claimDetails[i][1]).equalsIgnoreCase("Y") ? YES
									: "No"), psfont, Rectangle.NO_BORDER, 1, 0);
					tableCreation.insertCell(
							"Has your home been built on reclaimed land",
							psfont, Rectangle.NO_BORDER, 5, 0);
					tableCreation.insertCell(((claimDetails[i][2] == null ? ""
							: claimDetails[i][2]).equalsIgnoreCase("Y") ? YES
									: "No"), psfont, Rectangle.NO_BORDER, 1, 0);
					tableCreation
					.insertCell(
							"Do you ever leave your home unattended for more than 60 consecutive days",
							psfont, Rectangle.NO_BORDER, 5, 0);
					tableCreation.insertCell(((claimDetails[i][3] == null ? ""
							: claimDetails[i][3]).equalsIgnoreCase("Y") ? YES
									: "No"), psfont, Rectangle.NO_BORDER, 1, 0);
					tableCreation
					.insertCell(
							"Has any insurer declined/cancelled/imposed special terms or conditions",
							psfont, Rectangle.NO_BORDER, 5, 0);
					tableCreation.insertCell(((claimDetails[i][4] == null ? ""
							: claimDetails[i][4]).equalsIgnoreCase("Y") ? YES
									: "No"), psfont, Rectangle.NO_BORDER, 1, 0);
					if ((claimDetails[i][5] == null ? "" : claimDetails[i][5])
							.equalsIgnoreCase("Y")) {
						tableCreation.insertCell(
								"Have you made any claim in the past 3 years",
								psfont, Rectangle.NO_BORDER, 5, 0);
						tableCreation.insertCell(YES, psfont,
								Rectangle.NO_BORDER, 1, 0);
						tableCreation.insertCell("Number of Claims", pbfont,
								Rectangle.BOX, 1, 0);
						tableCreation.insertCell(
								(claimDetails[i][6] == null ? ""
										: claimDetails[i][6]), psfont,
										Rectangle.BOX, 2, 0);
						tableCreation.insertCell("Claim Amount", pbfont,
								Rectangle.BOX, 1, 0);
						tableCreation.insertCell(
								(claimDetails[i][8] == null ? ""
										: claimDetails[i][8]), psfont,
										Rectangle.BOX, 2, 0);
						tableCreation.insertCell("Type of Claims", pbfont,
								Rectangle.BOX, 1, 0);

						String claimTypeName[][] = new String[0][0];
						String tempType = "";
						claimTypeName = cover.getClaimTypeNames(
								(claimDetails[i][7] == null ? ""
										: claimDetails[i][7]), pid);
						for (int c = 0; c < claimTypeName.length; c++) {
							tempType = tempType
							+ ((claimTypeName[c][0] == null ? ""
									: claimTypeName[c][0] + ","));
						}
						if (tempType.length() > 0) {
							tempType = tempType.substring(0,
									tempType.length() - 1);
						}
						tableCreation.insertCell(tempType, psfont,
								Rectangle.BOX, 5, 0);

					} else {
						tableCreation.insertCell(
								"Have you made any claim in the past 3 years",
								psfont, Rectangle.NO_BORDER, 5, 0);
						tableCreation.insertCell("No", psfont,
								Rectangle.NO_BORDER, 1, 0);
					}
					tableCreation.setTableSpacing(7f);
					document.add(tableCreation.getTable());
				}
				tableCreation.setTable(6);
				tableCreation.insertCell("Excess", pbbfont, Rectangle.BOX, 6,
						0, "BG");
				String exIds = "";
				if (cons == 1 || pers == 1) {
					exIds = exIds + "2,";
				}
				if (bill == 1) {
					exIds = exIds + "1,";
				}
				String policyExcess[][] = new String[0][0];
				String policyConSp[][] = new String[0][0];
				String policyPerSp[][] = new String[0][0];
				if (exIds.length() > 0) {
					exIds = exIds.substring(0, (exIds.length() - 1));
				}
				policyExcess = cover.getPolicyClauses(pid, exIds, "Excess");
				for (int p = 0; p < policyExcess.length; p++) {
					tableCreation.insertCell(policyExcess[p][0], psfont,
							Rectangle.NO_BORDER, 6, 0);
				}
				tableCreation.setTableSpacing(7f);
				document.add(tableCreation.getTable());
				tableCreation.setTable(6);
				tableCreation.insertCell("Special Terms & Conditions", pbbfont,
						Rectangle.BOX, 6, 0, "BG");
				if (cons == 1 || pers == 1) {

					if (cons == 1) {

						tableCreation.insertCell("Contents", pbfont,
								Rectangle.NO_BORDER, 6, 0);
						policyConSp = cover.getPolicyClauses(pid, "2", SPECIAL);
						for (int p = 0; p < policyConSp.length; p++) {
							tableCreation.insertCell(policyConSp[p][0], psfont,
									Rectangle.NO_BORDER, 6, 0);
						}
					}
					if (pers == 1) {
						tableCreation.insertCell("Personal Belongings", pbfont,
								Rectangle.NO_BORDER, 6, 0);
						policyPerSp = cover.getPolicyClauses(pid, "3", SPECIAL);
						for (int p = 0; p < policyPerSp.length; p++) {
							tableCreation.insertCell(policyPerSp[p][0], psfont,
									Rectangle.NO_BORDER, 6, 0);
						}

					}
					if (cons == 1 || pers == 1) {
						tableCreation
						.insertCell(
								"You will be required to provide a valuation or proof of purchase for any specified item when submitting a claim.",
								psfont, Rectangle.NO_BORDER, 6, 0);
					}
				}

				tableCreation
				.insertCell(
						"Please refer to your policy booklet for full terms, conditions and exclusions of the policy.",
						psfont, Rectangle.NO_BORDER, 6, 0);
				tableCreation.setTableSpacing(5f);
				document.add(tableCreation.getTable());
				String clauses1 = pdfDataSelect.getClauses(getQuoteNo());
				clauses1 = clauses1.trim();

				if (clauses1.length() > 0) {
					tableCreation.setTable(6);
					tableCreation.insertCell("Special Condition", pbbfont,
							Rectangle.BOX, 6, 0, "BG");
					tableCreation.insertCell(clauses1, psfont,
							Rectangle.NO_BORDER, 6, 0);
					tableCreation.setTableSpacing(5f);
					document.add(tableCreation.getTable());
				}

				tableCreation.setTable(6);
				tableCreation.insertCell("Declaration", pbbfont, Rectangle.BOX,
						6, 0, "BG");
				tableCreation
				.insertCell(
						"In addition to any other details supplied to the Insurers, I, the undersigned, declare that to the best of my knowledge and belief the information given by me is true and complete and that all material information has been disclosed and I agree that this application shall be the basis of the contract between me and the insurance company. I further declare that the payment of my premium is made from my own source. I understand and accept that the insurers reserve the right to accept or reject a proposal at their discretion. I will give notice to the company of any change in the information relating to the insured, as stated above. I agree to accept a policy in the Company's usual form for this class of insurance. ",
						psfont, Rectangle.NO_BORDER, 6, 0);
				tableCreation.insertCell("Date\b\b\b\b\b\b\b\b:", pbfont,
						Rectangle.NO_BORDER, 1, 0);
				tableCreation.insertCell("", psfont, Rectangle.NO_BORDER, 2, 0);
				tableCreation.insertCell("Signature\b:", pbfont,
						Rectangle.NO_BORDER, 1, 1);
				tableCreation.insertCell("", psfont, Rectangle.NO_BORDER, 2, 0);
				tableCreation.insertCell("Location\b:", pbfont,
						Rectangle.NO_BORDER, 1, 0);
				tableCreation.insertCell("", psfont, Rectangle.NO_BORDER, 5, 0);
				tableCreation.setTableSpacing(5f);
				document.add(tableCreation.getTable());
			} catch (Exception e) {
				LogManager.debug(e);
			}
			document.close();
		}

		// Receipt No

		else if (option.equalsIgnoreCase("Receipt")) {
			Document document = new Document(PageSize.A4, 50, 50, 120, 60);
			double totPre = 0.0;
			String wordPre = "";
			try {
				try {
					writer = PdfWriter.getInstance(document,
							new FileOutputStream(getFilePath()));
				} catch (Exception e) {
					LogManager.debug(e);
				}
				document.open();
				// ///////Header and Footer/////////
				try {
					endPage = new HeaderFooterImage();

					writer.setPageEvent(endPage);
					endPage.setHeaderImagePath(getHeaderImagePath());
					endPage.setFooterImagePath(getFooterImagePath());
					endPage.setOption(option);
				} catch (Exception exImageHeader) {
					LogManager.debug(exImageHeader);
				}
				String receiptNo = "";
				String receiptDates = "";
				try
				{
					String receiptDetails[][] = new String[0][0];
					receiptDetails = pdfDataSelect.getReceiptDetails(getQuoteNo());
					if(receiptDetails.length > 0){
						receiptNo = receiptDetails[0][0];
						receiptDates = receiptDetails[0][1];
						modeOfPurchase = receiptDetails[0][2];
						totPre = Double.parseDouble(receiptDetails[0][3]);
					}
					String wordPreStr = ""+java.lang.Math.round(totPre);
					StringTokenizer str = new StringTokenizer(wordPreStr,".");
					if(str.hasMoreTokens())
					{
						wordPre = numword.convertNumToWord(Integer.parseInt(str.nextToken()));
					}
				}
				catch(Exception e)
				{
					LogManager.info("Exception in getting data from databse in Debit option"+e.toString());
					//e.printStackTrace();
				}

				// ////////// Font Declaration ////////////
				// Font sfont = new Font(Font.HELVETICA, 8);
				Font sfont = new Font(Font.HELVETICA, 8);
				Font bfont = new Font(Font.HELVETICA, 8, Font.BOLD);
				Font font1 = new Font(Font.HELVETICA, 9, Font.BOLD);

				// Font sfont = new Font(Font.HELVETICA, 8, Font.UNDERLINE);
				// //////// Table 10 --Third Page-- //////
				// document.newPage();
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
				// /////// Table 11 ///////
				tableCreation.setTable(7);
				tableCreation.insertCell(Rectangle.TOP, 7);
				tableCreation.insertCell(Rectangle.NO_BORDER, 3);
				tableCreation.insertCell("RECEIPT", font1, Rectangle.NO_BORDER,
						1, 0);
				tableCreation.insertCell(Rectangle.NO_BORDER, 3);
				tableCreation.insertCell(Rectangle.NO_BORDER, 7);
				tableCreation.insertCell(Rectangle.TOP, 7);
				tableCreation.setTableSpacing(30f);
				document.add(tableCreation.getTable());
				// //////// Table 12 ////////
				tableCreation.setTable(7);
				tableCreation.insertCell("RECEIVED FROM", font1,
						Rectangle.NO_BORDER, 2, 0);
				tableCreation.insertCell(fullName, font1, Rectangle.NO_BORDER,
						3, 0);
				tableCreation.insertCell("Receipt No :", sfont,
						Rectangle.NO_BORDER, 1, 0);
				tableCreation.insertCell(receiptNo, font1,
						Rectangle.NO_BORDER, 1, 0);
				tableCreation.insertCell(Rectangle.NO_BORDER, 5);
				tableCreation.insertCell("Date :", sfont, Rectangle.NO_BORDER,
						1, 0);
				tableCreation.insertCell(receiptDates, font1,Rectangle.NO_BORDER, 1, 0);
				// tableCreation.insertCell(broComp+broAdd, sfont,
				// Rectangle.NO_BORDER, 5, 0);
				// tableCreation.insertCell(Rectangle.NO_BORDER, 2);
				tableCreation.insertCell("", sfont, Rectangle.NO_BORDER, 5, 0);
				tableCreation.insertCell(Rectangle.NO_BORDER, 2);
				tableCreation.insertCell("", sfont, Rectangle.NO_BORDER, 5, 0);
				tableCreation.insertCell(Rectangle.NO_BORDER, 2);
				tableCreation.setTableSpacing(10f);
				document.add(tableCreation.getTable());

				tableCreation.setTable(7);
				tableCreation.insertCell("Amount :", sfont,
						Rectangle.NO_BORDER, 1, 0);
				tableCreation.insertCell(currencyType + "\b"
						+ fmt.format(totPre), font1, Rectangle.NO_BORDER, 6, 0);
				tableCreation.insertCell("", sfont, Rectangle.NO_BORDER, 1, 0);
				tableCreation.insertCell(currencyName + "\b" + wordPre
						+ "\bOnly", sfont, Rectangle.NO_BORDER, 6, 0);
				tableCreation.setTableSpacing(50f);
				document.add(tableCreation.getTable());
				// /////////
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
				tableCreation.insertCell("PARTICULARS OF RECEIPTS:", font1,
						Rectangle.NO_BORDER, 2, 0);
				tableCreation.insertCell(modeOfPurchase, font1,
						Rectangle.NO_BORDER, 2, 0);
				tableCreation.insertCell("", sfont, Rectangle.NO_BORDER, 2, 0);
				tableCreation.insertCell(sealDate, font1, Rectangle.NO_BORDER,
						1, 0);
				tableCreation.setTableSpacing(50f);
				document.add(tableCreation.getTable());
				// ///////// Table 14 /////////
				tableCreation.setTable(7);
				// tableCreation.insertCell("GL Code", bfont, Rectangle.TOP, 2,
				// 0);
				tableCreation.insertCell("DESCRIPTION", bfont, Rectangle.TOP,
						5, 0);
				tableCreation.insertCell("AMOUNT In " + currencyType, bfont,
						Rectangle.TOP, 2, 1);
				// tableCreation.insertCell("1-2-20-11-1410-1115", sfont,
				// Rectangle.TOP, 2, 0);
				final HomeAdminReferralBean adminRef = new HomeAdminReferralBean();
				pname = adminRef.getProductName(getPid(), getBranch());
				if (homePdfDetails.isEmpty()
						&& userModeSC.equalsIgnoreCase(TEST)) {
					tableCreation.insertCell("BEING THE PREMIUM COLLECTED\b"
							+ pname + "\bQUOTE NO : " + getQuoteNo(), sfont,
							Rectangle.TOP, 5, 0);
				} else {
					tableCreation.insertCell("BEING THE PREMIUM COLLECTED\b"
							+ pname
							+ "\bPOLICY NO : "
							+ (homePdfDetails.get(POS_POLICY_NO) == null ? ""
									: (String) homePdfDetails
									.get(POS_POLICY_NO)), sfont,
									Rectangle.TOP, 5, 0);
				}
				pname = "";

				tableCreation.insertCell(fmt.format(totPre), sfont,
						Rectangle.TOP, 2, 1);
				tableCreation.insertCell(Rectangle.NO_BORDER, 7);

				tableCreation.insertCell(Rectangle.BOTTOM, 7);

				tableCreation.insertCell(new String(), sfont,
						Rectangle.NO_BORDER, 5, 0);
				tableCreation.insertCell("Total :", sfont, Rectangle.NO_BORDER,
						1, 1);
				tableCreation.insertCell(fmt.format(totPre), font1,
						Rectangle.NO_BORDER, 1, 1);
				tableCreation.insertCell(Rectangle.BOTTOM, 7);
				tableCreation.setTableSpacing(50f);
				document.add(tableCreation.getTable());
				// ///// Table 15 ////////

				// /////// Table 16 ////////////
				tableCreation.setTable(7);
				tableCreation.insertCell("For : " + branchName, font1,
						Rectangle.NO_BORDER, 4, 0);
				tableCreation.insertCell(Rectangle.NO_BORDER, 3);
				try {
					jpg1 = Image.getInstance(getSignedImagePath());
				} catch (Exception de) {
					LogManager.debug(de);
				}
				jpg1.scalePercent(30, 30);
				tableCreation.insertCell(jpg1, Rectangle.NO_BORDER, 4, 2);
				tableCreation.insertCell(Rectangle.NO_BORDER, 3);
				tableCreation.insertCell("Authorised Signatory", sfont,
						Rectangle.NO_BORDER, 4, 2);
				tableCreation.insertCell(Rectangle.NO_BORDER, 3);
				tableCreation.setTableSpacing(55f);
				document.add(tableCreation.getTable());
				// //////
				String broComp = "";
				String entered = "";
				// String place =
				// pdfDataSelect.getBrokerBranchName(getQuoteNo());
				if (homePdfDetails.size() > 0) {
					broComp = homePdfDetails.get(BRO_COMPANY_NAME) == null ? ""
							: (String) homePdfDetails.get(BRO_COMPANY_NAME);
					broComp = broComp.trim();
					String quoteLogin = homePdfDetails.get(POS_LOGINID) == null ? ""
							: (String) homePdfDetails.get(POS_LOGINID);
					if (quoteLogin.length() > 0) {
						entered = pdfDataSelect.getQuotedName(quoteLogin);
					}
					if (entered.length() <= 0) {
						entered = broComp;
					}
				}
				tableCreation.setTable(7);
				tableCreation.insertCell("Printed on:", sfont,
						Rectangle.NO_BORDER, 1, 0);
				tableCreation.insertCell(sealDate, font1, Rectangle.NO_BORDER,
						2, 0);
				tableCreation.insertCell("( " + broComp + " - XYZ Insurance " + place
						+ " )", sfont, Rectangle.NO_BORDER, 4, 0);
				tableCreation.setTableSpacing(50f);
				document.add(tableCreation.getTable());
			} catch (DocumentException de) {
				LogManager.debug(de);
			}
			document.close();
		}
		// Receipt No

		if (option.equalsIgnoreCase("BetterMail")) {
			final Document document = new Document(PageSize.A4, 50, 50, 120, 60);
			try {
				try {
					writer = PdfWriter.getInstance(document,
							new FileOutputStream(getFilePath()));
				} catch (Exception e) {
					LogManager.debug(e);
				}
				final Font sfont = new Font(Font.HELVETICA, 11);
				final Font sbfont = new Font(Font.HELVETICA, 13);
				final Font bsfont = new Font(Font.HELVETICA, 11, Font.BOLD);
				final Font font1 = new Font(Font.HELVETICA, 16, Font.BOLD);

				document.open();
				// ///////Header and Footer/////////
				try {

					endPage = new HeaderFooterImage();
					endPage.setOption(option);
					endPage.setBetterImagePath(getBetterImagePath());
					endPage.setEmptyImage(getEmptyImage());
					endPage.setPid(getPid());
					endPage.setHeaderImagePath(getHeaderImagePath());
					endPage.setFooterImagePath(getFooterImagePath());
					writer.setPageEvent(endPage);

				} catch (Exception exImageHeader) {
					LogManager.debug(exImageHeader);
				}
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
				tableCreation.insertCell(Rectangle.NO_BORDER, 8);
				tableCreation.insertCell(Rectangle.NO_BORDER, 8);
				tableCreation.insertCell(Rectangle.NO_BORDER, 8);
				tableCreation.insertCell(Rectangle.NO_BORDER, 8);
				tableCreation.insertCell(Rectangle.NO_BORDER, 8);
				tableCreation.insertCell(Rectangle.NO_BORDER, 8);
				tableCreation.insertCell(Rectangle.NO_BORDER, 8);
				tableCreation.insertCell(Rectangle.NO_BORDER, 8);
				tableCreation.insertCell(Rectangle.NO_BORDER, 8);
				tableCreation.setTableSpacing(500f);
				document.add(tableCreation.getTable());

				tableCreation.setTable(8);
				tableCreation.insertCell("A Safer Home, A Better Home", font1,
						Rectangle.NO_BORDER, 8, 2);
				tableCreation.setTableSpacing(50f);
				document.add(tableCreation.getTable());

				tableCreation.setTable(8);
				tableCreation.insertCell("Dear " + betterName + ",", sfont,
						Rectangle.NO_BORDER, 8, 0);
				tableCreation.insertCell("", font1, Rectangle.NO_BORDER, 8, 0);
				tableCreation.insertCell("", font1, Rectangle.NO_BORDER, 8, 0);
				tableCreation
				.insertCell(
						"As a client of Better Homes you are entitled to preferential terms from XYZ Insurance, one of the World's leading insurance groups. If you would like to take advantage of this offer, please call us on 9714 303 9500 within 7 days to validate the offer quoting reference number "
						+ getQuoteNo()
						+ ".  The process is simple and takes only a few minutes to confirm full cover via E-mail. We strongly recommend that your Home contents are adequately insured by a reputable insurance company.",
						sfont, Rectangle.NO_BORDER, 8, 0);
				tableCreation.insertCell("", font1, Rectangle.NO_BORDER, 8, 0);
				tableCreation.insertCell("", font1, Rectangle.NO_BORDER, 8, 0);
				tableCreation.insertCell("", font1, Rectangle.NO_BORDER, 8, 0);
				tableCreation.insertCell("", font1, Rectangle.NO_BORDER, 8, 0);
				tableCreation.insertCell("", font1, Rectangle.NO_BORDER, 8, 0);
				tableCreation.insertCell("Your Premium", bsfont,
						Rectangle.NO_BORDER, 8, 0);
				tableCreation.insertCell("", font1, Rectangle.NO_BORDER, 8, 0);
				tableCreation.insertCell("", font1, Rectangle.NO_BORDER, 8, 0);
				tableCreation.insertCell("", font1, Rectangle.NO_BORDER, 8, 0);
				tableCreation.insertCell(
						"Based on the information below, your premium will be "
						+ currencyType + "\b" + betterPremium, sfont,
						Rectangle.NO_BORDER, 8, 0);
				tableCreation.insertCell("", font1, Rectangle.NO_BORDER, 8, 0);
				tableCreation.insertCell("", font1, Rectangle.NO_BORDER, 8, 0);
				tableCreation.insertCell("", font1, Rectangle.NO_BORDER, 8, 0);
				tableCreation.insertCell("", font1, Rectangle.NO_BORDER, 8, 0);
				tableCreation.insertCell("", font1, Rectangle.NO_BORDER, 8, 0);
				tableCreation.insertCell("Your Information", bsfont,
						Rectangle.NO_BORDER, 8, 0);
				tableCreation.insertCell("", font1, Rectangle.NO_BORDER, 8, 0);
				tableCreation.insertCell("", font1, Rectangle.NO_BORDER, 8, 0);
				tableCreation.insertCell("", font1, Rectangle.NO_BORDER, 8, 0);
				tableCreation.insertCell("", font1, Rectangle.NO_BORDER, 8, 0);
				tableCreation.insertCell("No of Bedrooms in your Home:\b"
						+ betterRoom, sfont, Rectangle.NO_BORDER, 8, 0);
				tableCreation.insertCell("", font1, Rectangle.NO_BORDER, 8, 0);
				tableCreation.insertCell("", font1, Rectangle.NO_BORDER, 8, 0);
				tableCreation.insertCell("", font1, Rectangle.NO_BORDER, 8, 0);
				tableCreation.insertCell("Value of your Contents:\b"
						+ currencyType + "\b" + betterContents, sfont,
						Rectangle.NO_BORDER, 8, 0);
				tableCreation.insertCell("", font1, Rectangle.NO_BORDER, 8, 0);
				tableCreation.insertCell("", font1, Rectangle.NO_BORDER, 8, 0);
				tableCreation.insertCell("", font1, Rectangle.NO_BORDER, 8, 0);
				tableCreation.insertCell("", font1, Rectangle.NO_BORDER, 8, 0);
				tableCreation.insertCell("Your Cover", bsfont,
						Rectangle.NO_BORDER, 8, 0);
				tableCreation.insertCell("", font1, Rectangle.NO_BORDER, 8, 0);
				tableCreation.insertCell("", font1, Rectangle.NO_BORDER, 8, 0);
				tableCreation
				.insertCell(
						"1) Home contents protection against fire, theft, natural calamities and accidental loss or damage.",
						sfont, Rectangle.NO_BORDER, 8, 0);
				tableCreation.insertCell("2) 'New for Old' cover.", sfont,
						Rectangle.NO_BORDER, 8, 0);
				tableCreation.insertCell("3) Cover upto " + currencyType
						+ " 1,000 for your money whilst you are at home.",
						sfont, Rectangle.NO_BORDER, 8, 0);
				tableCreation
				.insertCell(
						"4) Cover for breakage of mirrors, glass tops and fixed glass in furniture.",
						sfont, Rectangle.NO_BORDER, 8, 0);
				tableCreation
				.insertCell(
						"5) Cover upto "
						+ currencyType
						+ " 2,500  for deterioration of food in your freezer due to breakdown.",
						sfont, Rectangle.NO_BORDER, 8, 0);
				tableCreation.insertCell("", font1, Rectangle.NO_BORDER, 8, 0);
				tableCreation.insertCell("", font1, Rectangle.NO_BORDER, 8, 0);
				tableCreation.insertCell("", font1, Rectangle.NO_BORDER, 8, 0);
				tableCreation.insertCell("", font1, Rectangle.NO_BORDER, 8, 0);
				tableCreation.insertCell("", font1, Rectangle.NO_BORDER, 8, 0);
				tableCreation.insertCell("", font1, Rectangle.NO_BORDER, 8, 0);
				tableCreation.insertCell("", font1, Rectangle.NO_BORDER, 8, 0);
				tableCreation
				.insertCell(
						"The above estimate is subject to XYZ Insurance underwriting terms and conditions.  Please call us on 9714 303 9500 to validate your quotation.",
						bsfont, Rectangle.NO_BORDER, 8, 0);
				tableCreation.insertCell("", font1, Rectangle.NO_BORDER, 8, 0);
				tableCreation.insertCell("", font1, Rectangle.NO_BORDER, 8, 0);
				tableCreation.insertCell("", font1, Rectangle.NO_BORDER, 8, 0);
				tableCreation.insertCell("", font1, Rectangle.NO_BORDER, 8, 0);
				tableCreation.insertCell("", font1, Rectangle.NO_BORDER, 8, 0);
				tableCreation.insertCell("", font1, Rectangle.NO_BORDER, 8, 0);
				tableCreation.insertCell("", font1, Rectangle.NO_BORDER, 8, 0);
				tableCreation.insertCell("", font1, Rectangle.NO_BORDER, 8, 0);
				tableCreation.insertCell("Kind regards,", sfont,
						Rectangle.NO_BORDER, 8, 0);
				tableCreation.insertCell("BETTER HOMES PLUS", sfont,
						Rectangle.NO_BORDER, 8, 0);
				tableCreation.insertCell("Tel: 9714 303 9500", sfont,
						Rectangle.NO_BORDER, 8, 0);
				tableCreation.insertCell("bhplus@bhomes.com", sfont,
						Rectangle.NO_BORDER, 8, 0);
				tableCreation.insertCell("", font1, Rectangle.NO_BORDER, 8, 0);
				tableCreation.insertCell("", font1, Rectangle.NO_BORDER, 8, 0);
				tableCreation.insertCell("", font1, Rectangle.NO_BORDER, 8, 0);
				tableCreation
				.insertCell(
						"Kindly contact us for any queries, or to purchase your policy",
						sbfont, Rectangle.NO_BORDER, 8, 0);
				tableCreation.setTableSpacing(40f);
				document.add(tableCreation.getTable());
			} catch (Exception ex) {
				LogManager.debug(ex);
			}
			document.close();

			LogManager.debug("- Exit");
			LogManager.popRemove();
		}
	}

	public String getBranchAddress() {
		return branchAddress;
	}

	public void setBranchAddress(final String branchAddress) {
		this.branchAddress = branchAddress;
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

	public String getData1() {
		return Data1;
	}

	public void setData1(final String data1) {
		Data1 = data1;
	}

	public String getOption() {
		return option;
	}

	public void setOption(final String option) {
		this.option = option;
	}

	public PdfPTableCreation getTableCreation1() {
		return tableCreation1;
	}

	public void setTableCreation1(final PdfPTableCreation tableCreation1) {
		this.tableCreation1 = tableCreation1;
	}

	public int getPdfStatus() {
		return pdfStatus;
	}

	public void setPdfStatus(final int pdfStatus) {
		this.pdfStatus = pdfStatus;
	}
}