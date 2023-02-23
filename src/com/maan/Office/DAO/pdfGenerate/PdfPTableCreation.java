package com.maan.Office.DAO.pdfGenerate;

import com.lowagie.text.html.HtmlWriter;
import com.lowagie.text.Chapter;
import com.lowagie.text.Font; 
import com.lowagie.text.List;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Cell;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Image;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.Element;
import com.lowagie.text.Chunk;
import com.lowagie.text.Rectangle;
import com.lowagie.text.HeaderFooter;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.*;

public class PdfPTableCreation
{
	public PdfPTable pdfpTable;
	public PdfPCell pdfpCell;
	public PdfPTableCreation()
	{
			pdfpTable = null;
			pdfpCell = null;
	}
	public void setTable(int cols) 
	{
		this.pdfpTable = new PdfPTable(cols);
		this.pdfpTable.setWidthPercentage(90);
		this.pdfpTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
	}
	public PdfPTable getTable()
	{
			return this.pdfpTable;
	}
	public void setTableSpacingBefore(float sapce)
	{
		this.pdfpTable.setSpacingBefore(sapce);
	}
	public void setTableSpacing(float sapce)
	{
		this.pdfpTable.setSpacingBefore(sapce);
	}
	public void insertCell(String data, Font font, int border, int colspan, int align)
	{	
		pdfpCell = new PdfPCell(new Paragraph(data,font));
		pdfpCell.setBorder(border);
		pdfpCell.setColspan(colspan);
		if(align==0)
			pdfpCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		else if(align==1)
			pdfpCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		else 
			pdfpCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		this.pdfpTable.addCell(pdfpCell);
	}
	public void insertCell(Image image, int border, int colspan, int align)
	{	
		pdfpCell = new PdfPCell(image);
		pdfpCell.setBorder(border);
		pdfpCell.setColspan(colspan);
		if(align==0)
			pdfpCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		else if(align==1)
			pdfpCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		else 
			pdfpCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		this.pdfpTable.addCell(pdfpCell);
	}
	public void insertCell(PdfPTable cellTable, int border, int colspan, int align)
	{	
		pdfpCell = new PdfPCell(cellTable);
		pdfpCell.setBorder(border);
		pdfpCell.setColspan(colspan);
		if(align==0)
			pdfpCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		else if(align==1)
			pdfpCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		else 
			pdfpCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		this.pdfpTable.addCell(pdfpCell);
	}
	public void insertCell(int border, int colspan)
	{	
		pdfpCell = new PdfPCell(new Paragraph(""));
		pdfpCell.setBorder(border);
		pdfpCell.setColspan(colspan);
		this.pdfpTable.addCell(pdfpCell);
	}
	public void arabicInsertCell(String data, Font font, int border, int colspan, int align) throws DocumentException
	{	
		final PdfPCell pdfpCell = new PdfPCell(new Paragraph(data,font));
		pdfpCell.setRunDirection(PdfWriter.RUN_DIRECTION_RTL); 
		pdfpCell.setBorder(border);
		pdfpCell.setColspan(colspan);
		if(align==0)
			pdfpCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		else if(align==1)
			pdfpCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		else 
			pdfpCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		
		//pdfpTable.setRunDirection(PdfWriter.RUN_DIRECTION_RTL);
		this.pdfpTable.addCell(pdfpCell);
	}
}