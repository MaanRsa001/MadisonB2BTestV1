
package com.maan.Office.DAO.pdfGenerate;

import java.awt.Color;
import java.io.FileOutputStream;

import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.ExceptionConverter;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfGState;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfPageEventHelper;
import com.lowagie.text.pdf.PdfTemplate;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.pdf.*;

public class PageNumbersWatermarkNew extends PdfPageEventHelper {
	public Image headerImage;

	public Image footerImage;

	public PdfPTable table;

	public PdfGState gstate;

	public PdfTemplate tpl;

	public BaseFont helv;

	public String urlPath = "";
	public String cols = "";

		public String urlPathFooter = "";

	public String disText = "";
	PdfPTableCreation tableCreation;
	public Image headerImageRoyal;
	public Image footerImageRoyal;
	public String getImagePath() {

		return urlPath;

	}

	public void setImagePath(String urlPath) {

		this.urlPath = urlPath;

	}
	public String getCols() {

		return this.cols;

	}

	public void setCols(String cols) {

		this.cols = cols;

	}

	public String getFooterImagePath() {

		return urlPathFooter;

	}

	public void setFooterImagePath(String urlPathFooter) {

		this.urlPathFooter = urlPathFooter;

	}

	public String getDisplayText() {

		return disText;

	}

	public void setDisplayText(String disText) {

		this.disText = disText;

	}

	public PdfPTable getHeaderImageTable()
	{
		tableCreation = new PdfPTableCreation();
			try
			{
				
				headerImageRoyal  = Image.getInstance(getImagePath());
			}
			catch (Exception e)
			{
				System.out.println("Exception in getHeaderImageTable hedear footer.."+e.toString());
				e.printStackTrace();
			}
			
			Font font1 = new Font(Font.HELVETICA, 8, Font.BOLD);
			//headerImage.setAbsolutePosition(document.leftMargin(), 760);
			if(cols.equalsIgnoreCase("UAE"))
				headerImageRoyal.scaleAbsolute(500,70);
			else if(cols.equalsIgnoreCase("KSA"))
				headerImageRoyal.scaleAbsolute(130,90);
			headerImageRoyal.setDpi(200,100);
			tableCreation.setTable(8);

			
			if(cols.equalsIgnoreCase("UAE"))
				tableCreation.insertCell(headerImageRoyal, Rectangle.NO_BORDER, 8, 0);
			else if(cols.equalsIgnoreCase("KSA"))
			{
				tableCreation.insertCell("",font1,Rectangle.NO_BORDER, 6, 0);
				tableCreation.insertCell(headerImageRoyal, Rectangle.NO_BORDER, 2, 0);
			}
			tableCreation.setTableSpacing(1f);
			return tableCreation.getTable();
	}
	public void onEndPage(PdfWriter writer, Document document)
	{
        try
		{
			
			Rectangle page = document.getPageSize();
            PdfPTable head = new PdfPTable(1);
           
			
            PdfPCell cell = new PdfPCell(getHeaderImageTable());
			cell.setBorder(Rectangle.NO_BORDER);
            head.addCell(cell);
			//head.addCell(headerImage);
            head.setTotalWidth(page.getWidth() - document.leftMargin() - document.rightMargin());
            head.writeSelectedRows(0, -1, document.leftMargin(), page.getHeight() - document.topMargin() + head.getTotalHeight()-30,
                writer.getDirectContent());
			//Footer
			
            PdfPTable foot = new PdfPTable(1);
            footerImageRoyal = Image.getInstance(getFooterImagePath());
			
			footerImageRoyal.scaleAbsolute(500,60);
			footerImageRoyal.setDpi(300,300);
			PdfPCell cell1 = new PdfPCell(footerImageRoyal);
			cell1.setBorder(Rectangle.NO_BORDER);
            foot.addCell(cell1);
			
			foot.setTotalWidth(page.getWidth() - document.leftMargin() - document.rightMargin());
            foot.writeSelectedRows(0, -1, document.leftMargin(), document.bottomMargin(),
                writer.getDirectContent());

			PdfContentByte cb = writer.getDirectContent();
			cb.saveState();
			//System.out.println("Draft b4 new");
			gstate = new PdfGState();
            gstate.setFillOpacity(0.3f);
            gstate.setStrokeOpacity(0.3f);
			helv = BaseFont.createFont("Helvetica", BaseFont.WINANSI, false);
			
			
			if (writer.getPageNumber() <= 3)
			{
				cb.setGState(gstate);
				cb.setColorFill(Color.pink);
				cb.beginText();
				cb.setFontAndSize(helv, 50);
				cb.showTextAligned(Element.ALIGN_CENTER, disText , document.getPageSize().getWidth() / 2, document.getPageSize().getHeight() / 2+20, 45);
				cb.endText();
				
			}
        }
        catch (Exception e)
		{
           //throw new ExceptionConverter(e);
			System.out.println("Exception in hedear footer.."+e.toString());
			e.printStackTrace();
        }
    }
     
  
    public void onStartPage(PdfWriter writer1, Document document) 
	{
			try
			{
				document.add(new Paragraph("\n\n"));
			}
			catch (Exception e)
			{
				System.out.println("Exception in onStartPage hedear footer.."+e.toString());
				e.printStackTrace();
			} 
    }
      
   
}

