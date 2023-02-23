package com.maan.Office.DAO.pdfGenerate;


import java.io.FileOutputStream;

import com.lowagie.text.Document;
import com.lowagie.text.ExceptionConverter;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfPageEventHelper;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.Image;
import com.lowagie.text.pdf.*;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.Element;
import java.awt.Color;
import com.lowagie.text.Font; 
public class HeaderFooterImage extends PdfPageEventHelper
{
	public Image headerImage;
	public Image emptyImageBetter;
	public Image headerImageBetter;
	public Image footerImage;
  	public String urlPath = "";
	public String urlPathFooter = "";
	public String disText = "";
	public String policyNo = "";
	public String pname = "";
	public String option = "";
	public PdfGState gstate;
    /** A template that will hold the total number of pages. */
    public PdfTemplate tpl;
    /** The font that will be used. */
    public BaseFont helv;
	String betterImagePath="";
	String pid="";
	String emptyImage="";
	PdfPTableCreation tableCreation;
	public HeaderFooterImage()
	{
		tableCreation = new PdfPTableCreation();
		
	}
	public String getHeaderImagePath()
	{
		return urlPath;
	}
	public void setHeaderImagePath(String urlPath)
	{
		this.urlPath=urlPath;					
	}
	public String getFooterImagePath()
	{
		return urlPathFooter;
	}
	public void setFooterImagePath(String urlPathFooter)
	{
		this.urlPathFooter=urlPathFooter;					
	}
	public void setDisplayText(String disText)
	{
		this.disText=disText;					
	}
	public String getDisplayText()
	{
			return disText;
	}
	public void setPolicyNo(String policyNo)
	{
		this.policyNo=policyNo;					
	}
	public String getPolicyNo()
	{
			return policyNo;
	}
	public void setPname(String pname)
	{
		this.pname=pname;					
	}
	public String getPname()
	{
			return pname;
	}
	public void setOption(String option)
	{
		this.option=option;					
	}
	public String getOption()
	{
			return option;
	}
	public String getBetterImagePath()
	{
		return betterImagePath;
	}
	public void setBetterImagePath(String betterImagePath)
	{
		this.betterImagePath=betterImagePath;					
	}
	public void setPid(String pid)
	{
		this.pid = pid;
	}
	public String getPid()
	{
		return this.pid;
	}
	public void setEmptyImage(String emptyImage)
	{
		this.emptyImage = emptyImage;
	}
	public String getEmptyImage()
	{
		return this.emptyImage;
	}
	public PdfPTable getHeaderTable()
	{
				
				//////////// Font Declaration ////////////
				
				
				Font font1 = new Font(Font.HELVETICA, 8, Font.BOLD);
				Font font2 = new Font(Font.HELVETICA, 8);
				Font font3 = new Font(Font.HELVETICA, 10, Font.BOLD);
			
				tableCreation.setTable(8);
				
				tableCreation.insertCell("", font2, Rectangle.NO_BORDER, 5, 0);
				tableCreation.insertCell("XYZ Insurance Company Ltd.", font1, Rectangle.NO_BORDER, 3, 0);
				
				tableCreation.insertCell("", font2, Rectangle.NO_BORDER, 5, 0);
				tableCreation.insertCell("Office Court 2nd Floor Oud Metha Road", font2, Rectangle.NO_BORDER, 3, 0);

				tableCreation.insertCell("", font2, Rectangle.NO_BORDER, 5, 0);
				tableCreation.insertCell("P.O.Box 28648 Dubai - UAE", font2, Rectangle.NO_BORDER, 3, 0);

				tableCreation.insertCell("", font2, Rectangle.NO_BORDER, 5, 0);
				tableCreation.insertCell("Tel: +971 (04) 3029800", font2, Rectangle.NO_BORDER, 3, 0);

				tableCreation.insertCell("", font2, Rectangle.NO_BORDER, 5, 0);
				tableCreation.insertCell("Fax: +971 (04) 3029800", font2, Rectangle.NO_BORDER, 3, 0);

				tableCreation.insertCell("", font2, Rectangle.NO_BORDER, 5, 0);
				tableCreation.insertCell("Email: email@XYZInsurance.com", font2, Rectangle.NO_BORDER, 3, 0);

				tableCreation.insertCell("", font2, Rectangle.NO_BORDER, 5, 0);
				tableCreation.insertCell("www.XYZInsurance.com", font2, Rectangle.NO_BORDER, 3, 0);
			if(option.equalsIgnoreCase("PrintQuote"))
			{
				tableCreation.insertCell("", font2, Rectangle.NO_BORDER, 8, 2);
				tableCreation.insertCell("HOME INSURANCE QUOTATION", font3, Rectangle.NO_BORDER, 8, 2);
				tableCreation.insertCell("", font2, Rectangle.NO_BORDER, 8, 2);
				tableCreation.insertCell("", font2, Rectangle.NO_BORDER, 8, 2);
			}
				tableCreation.setTableSpacing(20f);
				return tableCreation.getTable();
	}
	public PdfPTable getHeaderImageTable()
	{
			try
			{
				headerImage  = Image.getInstance(getHeaderImagePath());
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
			
			Font font1 = new Font(Font.HELVETICA, 8, Font.BOLD);
			Font font2 = new Font(Font.HELVETICA, 11);
			//headerImage.setAbsolutePosition(document.leftMargin(), 760);
			
			
			if(option.equalsIgnoreCase("PrintQuote")&&(pid.equals("28") || pid.equals("34")))
			{
				try
				{
					headerImageBetter  = Image.getInstance(getBetterImagePath());
					emptyImageBetter  = Image.getInstance(getEmptyImage());
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
				tableCreation.setTable(2);
				headerImageBetter.scaleAbsolute(125,25);
				emptyImageBetter.scaleAbsolute(125,24);
				headerImageBetter.setDpi(300,100);
				emptyImageBetter.setDpi(300,100);
				tableCreation.insertCell(emptyImageBetter, Rectangle.NO_BORDER, 2, 0);
				tableCreation.insertCell(headerImageBetter, Rectangle.NO_BORDER, 2, 0);
				tableCreation.insertCell(emptyImageBetter, Rectangle.NO_BORDER, 2, 0);
				PdfPTable temp  = tableCreation.getTable();
				tableCreation.setTable(8);
				headerImage.scaleAbsolute(380,63);
				headerImage.setDpi(200,100);
				tableCreation.insertCell(temp, Rectangle.NO_BORDER, 2, 0);
				tableCreation.insertCell(headerImage, Rectangle.NO_BORDER, 6, 0);
				
				
			}
			else if(option.equalsIgnoreCase("BetterMail"))
			{
				
				
				try
				{
					headerImageBetter  = Image.getInstance(getBetterImagePath());
					emptyImageBetter  = Image.getInstance(getEmptyImage());
				}
				catch (Exception e)
				{
					System.out.println("royalbetermailtest....."+e.toString());
					e.printStackTrace();
				}
				tableCreation.setTable(2);
				headerImageBetter.scaleAbsolute(125,25);
				headerImageBetter.setDpi(300,100);
				tableCreation.insertCell(headerImageBetter, Rectangle.NO_BORDER, 2, 0);
				tableCreation.setTableSpacing(50f);
				PdfPTable temp  = tableCreation.getTable();

				tableCreation.setTable(8);
				headerImage.scaleAbsolute(130,90);
				headerImage.setDpi(200,100);
				tableCreation.insertCell(Rectangle.NO_BORDER, 6);
				tableCreation.insertCell(headerImage, Rectangle.NO_BORDER, 2, 0);
					tableCreation.insertCell(Rectangle.NO_BORDER, 8);
					tableCreation.insertCell(Rectangle.NO_BORDER, 8);
					tableCreation.insertCell(Rectangle.NO_BORDER, 8);
					tableCreation.insertCell(Rectangle.NO_BORDER, 8);
					tableCreation.insertCell(Rectangle.NO_BORDER, 8);
					tableCreation.insertCell(Rectangle.NO_BORDER, 8);
									
				tableCreation.insertCell(Rectangle.NO_BORDER, 1);
				tableCreation.insertCell("BETTER HOMES HOME CONTENTS INSURANCE ESTIMATE", font2,Rectangle.NO_BORDER, 5, 2);
				tableCreation.insertCell(temp, Rectangle.NO_BORDER, 2, 0);
				
				
			}
			else
			{
				tableCreation.setTable(8);
				headerImage.scaleAbsolute(500,70);
				headerImage.setDpi(200,100);
				tableCreation.insertCell(headerImage, Rectangle.NO_BORDER, 8, 2);
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
            PdfPTable head1 = new PdfPTable(1);
			
            PdfPCell cell = new PdfPCell(getHeaderImageTable());
			cell.setBorder(Rectangle.NO_BORDER);
            head.addCell(cell);
			head.setTotalWidth(page.getWidth() - document.leftMargin() - document.rightMargin());
            head.writeSelectedRows(0, -1, document.leftMargin(), page.getHeight() - document.topMargin() + head.getTotalHeight()-30,
                writer.getDirectContent());
			
			
			
            PdfPTable foot = new PdfPTable(1);
            footerImage = Image.getInstance(getFooterImagePath());
			footerImage.setAbsolutePosition(document.leftMargin(), -15);
			footerImage.scaleAbsolute(500,20);
			footerImage.setDpi(300,300);
			PdfPCell cell1 = new PdfPCell(footerImage);
			cell1.setBorder(Rectangle.NO_BORDER);
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
            foot.addCell(cell1);
			//foot.addCell(footerImage);
			foot.setTotalWidth(page.getWidth() - document.leftMargin() - document.rightMargin() );
            foot.writeSelectedRows(0, -1, document.leftMargin(), (document.bottomMargin()+1),
                writer.getDirectContent());
			PdfContentByte cb = writer.getDirectContent();
			cb.saveState();
			//System.out.println("Draft b4 new");
			gstate = new PdfGState();
            gstate.setFillOpacity(0.3f);
            gstate.setStrokeOpacity(0.3f);
			helv = BaseFont.createFont("Helvetica", BaseFont.WINANSI, false);
			//System.out.println("Draft b4 setting in Helper end");
			
			if (writer.getPageNumber() <= 50)
			{
				cb.setGState(gstate);
				cb.setColorFill(Color.pink);
				cb.beginText();
				cb.setFontAndSize(helv, 50);
				cb.showTextAligned(Element.ALIGN_CENTER, disText , document.getPageSize().getWidth() / 2, document.getPageSize().getHeight() / 2+20, 45);
				cb.endText();
				//System.out.println("Draft after setting in Helper end");
			}
			
        }
        catch (Exception e)
		{
            throw new ExceptionConverter(e);
        }
    }
	public void onStartPage(PdfWriter writer, Document document)
	{
		Font font1 = new Font(Font.HELVETICA, 12, Font.BOLD);
		font1.setStyle(Font.UNDERLINE);
		Font sfont1 = new Font(Font.HELVETICA, 9, Font.BOLD);
		try
		{
			
			//document.add(new Paragraph("<u>Home Insurance Policy</u>"));
			
			
			if(!option.equalsIgnoreCase("PrintQuote"))
				if(true)
			{
				document.add(new Paragraph("\n\n"));
				tableCreation.setTable(3);
				//tableCreation.insertCell(Rectangle.NO_BORDER, 1);
				//tableCreation.insertCell(pname+"\bPOLICY", font1, Rectangle.NO_BORDER, 3, 2);
				//tableCreation.insertCell(Rectangle.NO_BORDER, 3);
				//tableCreation.insertCell(Rectangle.NO_BORDER, 3);
				
				if(getDisplayText().equalsIgnoreCase("Draft"))
					tableCreation.insertCell("Attaching to and forming part of Office Shield Insurance Quote No:\b\b\b"+getPolicyNo(), sfont1, Rectangle.NO_BORDER, 3, 0);
				
				else 
					tableCreation.insertCell("Attaching to and forming part of Office Shield Insurance Policy No:\b\b\b"+getPolicyNo(), sfont1, Rectangle.NO_BORDER, 3, 0);
				
				
				tableCreation.setTableSpacing(5f);
				document.add(tableCreation.getTable());
				document.add(new Paragraph("\n"));
			}
			else
				document.add(new Paragraph("\n\n\n\n\n\n\n\n"));
				/*document.add(new Paragraph("\n"));
				document.add(new Paragraph("Schedule forming part of Policy No:"+getPolicyNo()));
				document.add(new Paragraph("\n\n"));*/
				
			
        }
		catch (Exception e)
		{
			throw new ExceptionConverter(e);
		}
	}
}
