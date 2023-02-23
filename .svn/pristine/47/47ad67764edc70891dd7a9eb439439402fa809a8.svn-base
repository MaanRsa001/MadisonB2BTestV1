package com.maan.Health.Pdf;

import java.awt.Color;
import java.io.IOException;
import java.net.MalformedURLException;


import com.lowagie.text.BadElementException;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.ExceptionConverter;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.ColumnText;
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
public class HeaderFooterImage extends PdfPageEventHelper
{
	private transient Image headerImage;
	private transient Image footerImageRoyal;
	private transient String urlPath;
	private transient String urlPathFooter;
	private transient String disText;
	private transient String option;
	PdfPTableCreation tableCreation;
	private transient PdfTemplate total;
	private String formatpath="";
	public HeaderFooterImage(){
		tableCreation = new PdfPTableCreation();
	}
	public String getHeaderImagePath(){
		return urlPath;
	}
	public void setHeaderImagePath(final String urlPath){
		this.urlPath=urlPath;					
	}
	public String getFooterImagePath(){
		return urlPathFooter;
	}
	public void setFooterImagePath(final String urlPathFooter){
		this.urlPathFooter=urlPathFooter;					
	}
	public void setDisplayText(final String disText){
		this.disText=disText;					
	}
	public String getDisplayText(){
			return disText;
	}
	public void setOption(final String option){
		this.option=option;					
	}
	public String getOption(){
			return option;
	}
	public String getFormatpath() {
		return formatpath;
	}
	public void setFormatpath(String formatpath) {
		this.formatpath = formatpath;
	}
	
	public PdfPTable getHeaderTable()throws BaseException
	{
		BaseFont bf;
		try {
			bf = BaseFont.createFont(formatpath, BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
		
		Font font3 = new Font(bf, 10,Font.BOLD);
		//Font font3 = new Font(Font.HELVETICA, 10, Font.BOLD);
		tableCreation.setTable(8);
		if(option.equalsIgnoreCase("PrintQuote")){
			tableCreation.insertCell("HEALTH INSURANCE QUOTATION", font3, Rectangle.NO_BORDER, 8, 2);
		}
		else if(option.equalsIgnoreCase("Receipt")){
			tableCreation.insertCell("", font3, Rectangle.NO_BORDER, 8, 2);
		}
		else{
			PdfPTableCreation tab=new PdfPTableCreation();
			tab.setTable(10);
			tableCreation.insertCell("", font3, Rectangle.NO_BORDER, 1, 1);
			tab.insertCell("HEALTH INSURANCE POLICY SCHEDULE", font3, Rectangle.NO_BORDER, 6, 2);
			tab.insertCell("", font3, Rectangle.NO_BORDER, 4, 1,1);
			tableCreation.insertCell(tab.getTable(),Rectangle.NO_BORDER, 6, 2);
			tableCreation.insertCell("", font3, Rectangle.NO_BORDER, 1, 1);
			//tableCreation.insertCell("TRAVEL INSURANCE POLICY SCHEDULE", font3, Rectangle.NO_BORDER, 8, 2);
		}
		tableCreation.setTableSpacing(1f);
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return tableCreation.getTable();
	}
	
	public PdfPTable getHeaderImageTable()throws BaseException, BadElementException, MalformedURLException, IOException
	{
		headerImage  = Image.getInstance(getHeaderImagePath());
		final Font font1 = new Font(Font.HELVETICA, 8, Font.BOLD);
		headerImage.scaleToFit(500,150);
		headerImage.setDpi(300,300);
		tableCreation.setTable(8);
		tableCreation.insertCell("",font1,Rectangle.NO_BORDER, 8, 0);
		tableCreation.insertCell(headerImage, Rectangle.NO_BORDER, 8, 0);
		tableCreation.setTableSpacing(1f);
		return tableCreation.getTable();
	}
	
    public void onEndPage(final PdfWriter writer,final Document document)
	{
        try
		{
        	BaseFont arialFont=null;
			final Rectangle page = document.getPageSize();
            final PdfPTable head = new PdfPTable(1);
            final PdfPTable head1 = new PdfPTable(1);
            final PdfPCell cell = new PdfPCell(getHeaderImageTable());
			cell.setBorder(Rectangle.NO_BORDER);
            head.addCell(cell);
            final PdfPTable foot = new PdfPTable(1);
			final PdfPTable pageNo = new PdfPTable(2);
			arialFont = BaseFont.createFont(formatpath, BaseFont.IDENTITY_H,BaseFont.NOT_EMBEDDED);
			Font fontTextNormal = new Font(arialFont, 8, Font.NORMAL);
            head.setTotalWidth(page.getWidth() - document.leftMargin() - document.rightMargin());
            head.writeSelectedRows(0, -1, document.leftMargin(), page.getHeight() - document.topMargin() + head.getTotalHeight()-10,writer.getDirectContent());
			final PdfPCell cellHead = new PdfPCell(getHeaderTable());
			cellHead.setBorder(Rectangle.NO_BORDER);
            head1.addCell(cellHead);
			head1.setTotalWidth(page.getWidth() - document.leftMargin() - document.rightMargin());
			//head1.writeSelectedRows(0, -1, document.leftMargin(), page.height() - document.topMargin() + head1.getTotalHeight() - 85, writer.getDirectContent());
			head1.writeSelectedRows(0, -1, document.leftMargin(), page.getHeight() - document.topMargin() + head1.getTotalHeight() - 35, writer.getDirectContent());
			Paragraph para=new Paragraph(String.format("Page %d of ", writer.getPageNumber()), fontTextNormal);
			PdfPCell cell2 = new PdfPCell(para);
			cell2.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell2.setBorder(Rectangle.NO_BORDER);
            pageNo.addCell(cell2);
            PdfPCell cell3 = new PdfPCell(Image.getInstance(total));
            cell3.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell3.setBorder(Rectangle.NO_BORDER);
            pageNo.addCell(cell3);
            pageNo.setHorizontalAlignment(Element.ALIGN_RIGHT);
            pageNo.setTotalWidth(100);
            pageNo.writeSelectedRows(0, -1, page.getWidth()-document.rightMargin()-pageNo.getTotalWidth()+38, document.bottomMargin()+foot.getTotalHeight()+pageNo.getTotalHeight()-12,writer.getDirectContent());
			footerImageRoyal = Image.getInstance(getFooterImagePath());
			//footerImageRoyal.scaleAbsolute(500,60);
			footerImageRoyal.scaleToFit(500,60);
			//footerImageRoyal.scaleAbsolute(1032.75f,63f);
			footerImageRoyal.setDpi(300,300);
			final PdfPCell cell1 = new PdfPCell(footerImageRoyal);
			cell1.setBorder(Rectangle.NO_BORDER);
			foot.addCell(cell1);
			foot.setTotalWidth(page.getWidth() - document.leftMargin() - document.rightMargin());
			foot.writeSelectedRows(0, -1, document.leftMargin(), document.bottomMargin()-12,writer.getDirectContent());
			
			
			PdfContentByte pcb;
			pcb = writer.getDirectContent();
			pcb.saveState();
			PdfGState gstate;
			gstate = new PdfGState();
            gstate.setFillOpacity(0.3f);
            gstate.setStrokeOpacity(0.3f);
            
			BaseFont helv;
			helv = BaseFont.createFont("Helvetica", BaseFont.WINANSI, false);
			LogManager.info("royal travel pdf test disText..."+disText);
			disText = disText==null?"":disText;
			LogManager.info("royal travel pdf test disText..."+disText);
			if (writer.getPageNumber() <= 3)
			{
				pcb.setGState(gstate);
				pcb.setColorFill(Color.pink);
				pcb.beginText();
				pcb.setFontAndSize(helv, 50);
				pcb.showTextAligned(Element.ALIGN_CENTER, disText , document.getPageSize().getWidth() / 2, document.getPageSize().getHeight() / 2+20, 45);
				pcb.endText();
			}
        }
        catch (Exception e)
		{
            throw new ExceptionConverter(e);
        }
    }
    
	public void onStartPage(final PdfWriter writer,final Document document)
	{
		try
		{
			document.add(new Paragraph("\n\n\n\n\n\n\n\n\n"));
        }
		catch (Exception e)
		{
			throw new ExceptionConverter(e);
		}
	}
	public void onOpenDocument(PdfWriter writer, Document document) {
        total = writer.getDirectContent().createTemplate(30, 16);
    }
	 public void onCloseDocument(PdfWriter writer, Document document) {
		 BaseFont arialFont=null;
		 try {
			arialFont = BaseFont.createFont(formatpath, BaseFont.IDENTITY_H,BaseFont.EMBEDDED);
			Font fontTextNormal = new Font(arialFont, 8, Font.NORMAL);
	        ColumnText.showTextAligned(total, Element.ALIGN_LEFT, new Phrase(" "+String.valueOf(writer.getPageNumber() - 1),fontTextNormal),0, 6, 0);
		}catch (Exception e) {
			e.printStackTrace();
		}
     }
} // Class
