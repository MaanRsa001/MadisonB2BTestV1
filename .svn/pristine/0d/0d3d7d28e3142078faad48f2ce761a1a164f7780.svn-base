package com.maan.Home.MasterController;

import java.awt.Color;
import java.io.IOException;
import java.net.MalformedURLException;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.ExceptionConverter;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfGState;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfPageEventHelper;
import com.lowagie.text.pdf.PdfTemplate;
import com.lowagie.text.pdf.PdfWriter;
import com.maan.common.LogManager;
import com.maan.common.exception.BaseException;
import com.maan.pdf.PdfPTableCreation;
public class HeaderFooterImage extends PdfPageEventHelper {
	public transient Image headerImage;

	public transient Image emptyImageBetter;

	public transient Image headerImageBetter;

	public transient Image footerImage;

	public transient String urlPath = "";

	public transient String urlPathFooter = "";

	public transient String disText = "";

	public transient String policyNo = "";

	public transient String pname = "";

	public transient String option = "";

	public transient PdfGState gstate;

	public transient BaseFont helv;

	private String betterImagePath = "";

	private String pid = "";
	
	final private transient String PRINTQUOTE; 

	private String emptyImage = "";

	final private transient PdfPTableCreation tableCreation;

	/** A template that will hold the total number of pages. */
	public transient PdfTemplate tpl;

	/** The font that will be used. */

	public HeaderFooterImage() {
		super();
		tableCreation = new PdfPTableCreation();
		PRINTQUOTE = "PrintQuote";

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

	public void setDisplayText(final String disText) {
		this.disText = disText;
	}

	public String getDisplayText() {
		return disText;
	}

	public void setPolicyNo(final String policyNo) {
		this.policyNo = policyNo;
	}

	public String getPolicyNo() {
		return policyNo;
	}

	public void setPname(final String pname) {
		this.pname = pname;
	}

	public String getPname() {
		return pname;
	}

	public void setOption(final String option) {
		this.option = option;
	}

	public String getOption() {
		return option;
	}

	public String getBetterImagePath() {
		return betterImagePath;
	}

	public void setBetterImagePath(final String betterImagePath) {
		this.betterImagePath = betterImagePath;
	}

	public void setPid(final String pid) {
		this.pid = pid;
	}

	public String getPid() {
		return this.pid;
	}

	public void setEmptyImage(final String emptyImage) {
		this.emptyImage = emptyImage;
	}

	public String getEmptyImage() {
		return this.emptyImage;
	}


	public PdfPTable getHeaderImageTable() throws BaseException,
			BadElementException, MalformedURLException, IOException {

		headerImage = Image.getInstance(getHeaderImagePath());
		LogManager.info("royal home header test.."+getHeaderImagePath());
		headerImage.scaleToFit(500,150);
		headerImage.setDpi(300,300);
		tableCreation.setTable(8);
		tableCreation.insertCell(Rectangle.NO_BORDER, 8);
		tableCreation.insertCell(Rectangle.NO_BORDER, 8);
		tableCreation.insertCell(Rectangle.NO_BORDER, 8);
		tableCreation.insertCell(headerImage, Rectangle.NO_BORDER, 8, 0);
		tableCreation.setTableSpacing(1f);
		return tableCreation.getTable();
		//
	}

	public void onEndPage(final PdfWriter writer, final Document document) {
		try {

			Rectangle page = document.getPageSize();
			PdfPTable head = new PdfPTable(1);
			PdfPTable head1 = new PdfPTable(1);

			PdfPCell cell = new PdfPCell(getHeaderImageTable());
			cell.setBorder(Rectangle.NO_BORDER);
			head.addCell(cell);
			head.setTotalWidth(page.getWidth() - document.leftMargin()- document.rightMargin());
			LogManager.info("royal home header writing test..");
			head.writeSelectedRows(0, -1, document.leftMargin(), page.getHeight() - document.topMargin() + head.getTotalHeight()-10,writer.getDirectContent());
			LogManager.info("royal home after header writing test..");

			PdfContentByte contentByte = writer.getDirectContent();
			LogManager.info("royal home after header writing test0..");
			contentByte.saveState();
			LogManager.info("royal home after header writing test1..");
			// System.out.println("Draft b4 new");
			gstate = new PdfGState();
			gstate.setFillOpacity(0.3f);
			gstate.setStrokeOpacity(0.3f);
			helv = BaseFont.createFont("Helvetica", BaseFont.WINANSI, false);
			// System.out.println("Draft b4 setting in Helper end");
			LogManager.info("royal home after header writing test2..");
			if (writer.getPageNumber() <= 3) {
				contentByte.setGState(gstate);
				contentByte.setColorFill(Color.pink);
				contentByte.beginText();
				contentByte.setFontAndSize(helv, 50);
				contentByte.showTextAligned(Element.ALIGN_CENTER, disText,
						document.getPageSize().getWidth() / 2, document
								.getPageSize().getHeight() / 2 + 20, 45);
				contentByte.endText();
				// System.out.println("Draft after setting in Helper end");
			}
			LogManager.info("royal home after header writing test3..");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void onStartPage(final PdfWriter writer, final Document document) {
		
		
		try {

			if (PRINTQUOTE.equalsIgnoreCase(option)) {
				document.add(new Paragraph("\n\n\n\n\n\n\n\n"));
			} else {
				document.add(new Paragraph("\n\n\n\n\n\n"));
				tableCreation.setTable(3);
				// tableCreation.insertCell(Rectangle.NO_BORDER, 1);
				
				final Font font1 = new Font(Font.HELVETICA, 12, Font.BOLD);
				font1.setStyle(Font.UNDERLINE);
				final Font sfont1 = new Font(Font.HELVETICA, 9, Font.BOLD);
				
				tableCreation.insertCell(pname + "\bPOLICY", font1,
						Rectangle.NO_BORDER, 3, 2);
				tableCreation.insertCell(Rectangle.NO_BORDER, 3);
				tableCreation.insertCell(Rectangle.NO_BORDER, 3);
				
				if (getDisplayText().equalsIgnoreCase("Draft")) {
					
					tableCreation
							.insertCell(
									"\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\bSchedule forming part of Quote No:\b\b\b"
											+ getPolicyNo(), sfont1,
									Rectangle.NO_BORDER, 2, 0);

				} else {
					tableCreation
							.insertCell(
									"\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\bSchedule forming part of Policy No:\b\b\b"
											+ getPolicyNo(), sfont1,
									Rectangle.NO_BORDER, 2, 0);
				}
				tableCreation.insertCell(Rectangle.NO_BORDER, 1);
				tableCreation.setTableSpacing(5f);
				document.add(tableCreation.getTable());
				document.add(new Paragraph("\n\n"));
			}
		} catch (Exception e) {
			throw new ExceptionConverter(e);
		}
	}
}
