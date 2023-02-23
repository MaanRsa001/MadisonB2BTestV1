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
import com.lowagie.text.Font; 
import java.awt.Color;
import com.lowagie.text.Element;

public class TableHeader extends PdfPageEventHelper
{
	public BaseFont helv;
    public PdfGState gstate;
	PdfPTable headTable = null;	
	PdfPTableCreation tableCreation;
	PdfPTable innertable1=null,innertable2=null,innertable3=null,innertable4=null,innertable5=null,innertable6=null,innertable7=null,innertable8=null,innertable9=null,htable=null;
	Document document;
	int page = 0;

	String urlPath="",customer_name="",pan_no="",total_records="",cycle_id="",commission="",total_premium="",cus_address="";
	String oa_code="",ag_code="",disText="";

	 
	
	public String getImagePath()
	{
	
		return urlPath;	
	}
	public void setImagePath(String urlPath)
	{
			
		this.urlPath= urlPath+"/logo.jpg";					
	}
	public String getCustomer_name()
	{
	
		return customer_name ;
	}

	public void setCustomer_name(String customer_name)
	{
			
	this.customer_name=customer_name;				
		
	}


	public String getPan_no()
	{
	
	return pan_no;
	
	}

	public void setPan_no(String pan_no)
	{
			
	this.pan_no=pan_no;					
		
	}


	public String getTotal_records()
	{
	
	return total_records;
	
	}

	public void setTotal_records(String total_records)
	{
			
	this.total_records=total_records;					
		
	}
	
	public String getCycle_id()
	{
	
		return cycle_id;
	}

	public void setCycle_id(String cycle_id)
	{
			
	this.cycle_id=cycle_id;					
		
	}


	public String getCommission()
	{
	
	return commission;
	
	}

	public void setCommission(String commission)
	{
			
	this.commission=commission;					
		
	}


	public String getTotal_premium()
	{
	
	return total_premium;
	
	}

	public void setTotal_premium(String total_premium)
	{
			
	this.total_premium=total_premium;					
		
	}

	public String getCus_address()
	{
	
	return cus_address;
	
	}

	public void setCus_address(String cus_address)
	{
			
	this.cus_address=cus_address;					
		
	}

  
	public void setOa_code(String oa_code)
	{
			
	this.oa_code=oa_code;					
		
	}

	public String getOa_code()
	{
	
	return oa_code;
	
	}

			
	public void setAg_code(String ag_code)
	{
			
	this.ag_code=ag_code;					
		
	}

	public String getAg_code()
	{
	
	return ag_code;
	
	}
 

   public String getDisText()
	{
	   return disText;
	}
	public void setDisText(String distext)
	{
		this.disText=distext;
	}


	public TableHeader(Document document)
	{
		this.document = document;
		tableCreation = new PdfPTableCreation();
	}
	
	/*public PdfPTable getHeaderTable()
	{
				
				//////////// Font Declaration ////////////
				Font font = new Font(Font.COURIER, 8);
				Font font1 = new Font(Font.HELVETICA, 8);
				Font font2 = new Font(Font.TIMES_ROMAN, 8, Font.BOLD);
				Font font3 = new Font(Font.TIMES_ROMAN, 10, Font.BOLD);
				//font3.setStyle(Font.UNDERLINE);
				Font font4 = new Font(Font.HELVETICA, 9);
				tableCreation.setTable(1);
				tableCreation.insertCell("Markers & Numbers", font2, Rectangle.BOX, 1, 0);
				tableCreation.insertCell("Packing Description\nPacking Type\nNo. of Packages\nPackage No.", font2, Rectangle.BOX, 1, 0);
				innertable1 = tableCreation.getTable();
				
				tableCreation.setTable(1);
				tableCreation.insertCell("Voyage", font2, Rectangle.BOX, 1, 0);
				tableCreation.insertCell("From\n\nTo\nTranshipment", font2, Rectangle.BOX, 1, 0);
				innertable2 = tableCreation.getTable();

				tableCreation.setTable(11);
				tableCreation.insertCell("", font2, Rectangle.NO_BORDER, 10, 0);
				tableCreation.insertCell("Page\b"+page, font2, Rectangle.NO_BORDER, 1, 1);
				tableCreation.insertCell("", font2, Rectangle.NO_BORDER, 3, 0);
				tableCreation.insertCell("Marine Declaration Under Open Cover No.", font2, Rectangle.NO_BORDER, 3, 0);
				tableCreation.insertCell("4/3/020/0000114", font2, Rectangle.NO_BORDER, 2, 0);
				tableCreation.insertCell("Currency :", font2, Rectangle.NO_BORDER, 1, 0);
				tableCreation.insertCell("(UAE DIRHAMS)", font1, Rectangle.NO_BORDER, 2, 0);
				tableCreation.insertCell("Bordereaux No :", font2, Rectangle.NO_BORDER, 1, 0);
				tableCreation.insertCell("4/1/020/0020272", font1, Rectangle.NO_BORDER, 2, 0);
				tableCreation.insertCell("", font1, Rectangle.NO_BORDER, 8, 0);
				tableCreation.insertCell("Client Name :", font2, Rectangle.NO_BORDER, 1, 0);
				tableCreation.insertCell("RNA RESOURCES GROUP LTD.", font1, Rectangle.NO_BORDER, 2, 0);
				tableCreation.insertCell("", font3, Rectangle.NO_BORDER, 8, 0);
				tableCreation.insertCell("Date of\nIssue", font2, Rectangle.BOX, 1, 0);
				tableCreation.insertCell("Declaration No.\nCertificate No.\n\nInvoice No.", font2, Rectangle.BOX, 1, 0);
				tableCreation.insertCell("BL/AWB/CN NO.\n&. Date\nL.C. No.\nBank Interest", font2, Rectangle.BOX, 1, 0);
				tableCreation.insertCell(innertable1, Rectangle.BOX, 1, 0);
				tableCreation.insertCell("Mode of Transit\nVessel Name\nSailing Date", font2, Rectangle.BOX, 1, 0);
				tableCreation.insertCell(innertable2, Rectangle.BOX, 1, 0);
				tableCreation.insertCell("Interests", font2, Rectangle.BOX, 1, 0);
				tableCreation.insertCell("Sum Insured\nValuation Basis\nLoading/Addl. (L)\nF.C. Sum Insured\nExchange Rate", font2, Rectangle.BOX, 1, 1);
				tableCreation.insertCell("", font2, Rectangle.BOX, 1, 0);
				tableCreation.insertCell("Rate(%)", font2, Rectangle.BOX, 1, 0);
				tableCreation.insertCell("Premium", font2, Rectangle.BOX, 1, 0);
				tableCreation.setTableSpacingBefore(10f);
				return tableCreation.getTable();
	}
*/

	public PdfPTable getHeaderTable()
	{
				
				//////////// Font Declaration ////////////
				Font font = new Font(Font.HELVETICA, 8);
				Font font1 = new Font(Font.HELVETICA, 8);
				Font font2 = new Font(Font.HELVETICA, 8, Font.BOLD);
				Font font3 = new Font(Font.HELVETICA, 10, Font.BOLD);
				Font font5 = new Font(Font.HELVETICA, 7,Font.BOLD);
				//font3.setStyle(Font.UNDERLINE);
				Font font4 = new Font(Font.HELVETICA, 9);
				tableCreation.setTable(2);
				tableCreation.insertCell("\n", font2, 0, 1, 1);
				tableCreation.insertCell("\n"+getCustomer_name(), font2, 0, 1, 0);
				innertable1 = tableCreation.getTable();

				tableCreation.setTable(2);
				tableCreation.insertCell("\n", font2, 0, 1, 1);
				tableCreation.insertCell("\n"+""+getCus_address(), font2, 0, 1, 0);
                innertable7 = tableCreation.getTable();
				
				tableCreation.setTable(2);
				tableCreation.insertCell("\n", font2, 0, 1, 1);
				tableCreation.insertCell("\n"+getPan_no(), font2, 0, 1, 0);
				innertable2 = tableCreation.getTable();

				tableCreation.setTable(2);
				tableCreation.insertCell("\n", font2, 0, 1, 1);
				tableCreation.insertCell("\n"+getCycle_id(), font2, 0, 1, 0);
				innertable3 = tableCreation.getTable();

				tableCreation.setTable(2);
				tableCreation.insertCell("\n", font2, 0, 1, 1);
				tableCreation.insertCell("\n"+getTotal_records(), font2, 0, 1, 0);
                innertable4 = tableCreation.getTable();

				tableCreation.setTable(2);
				tableCreation.insertCell("\n", font2, 0, 1, 1);
				tableCreation.insertCell("\n"+getTotal_premium(), font2, 0, 1, 0);
				innertable5 = tableCreation.getTable();

				tableCreation.setTable(2);
				tableCreation.insertCell("\n", font2, 0, 1, 1);
				tableCreation.insertCell("\n"+getCommission(), font2, 0, 1, 0);
				innertable6 = tableCreation.getTable();


				

				tableCreation.setTable(2);
				tableCreation.insertCell("\n", font2, 0, 1, 1);
				tableCreation.insertCell("\n"+getAg_code(), font2, 0, 1, 0);
				innertable8 = tableCreation.getTable();

				tableCreation.setTable(2);
				tableCreation.insertCell("\n", font2, 0, 1, 1);
				tableCreation.insertCell("\n"+getOa_code(), font2, 0, 1, 0);
				innertable9 = tableCreation.getTable();



				tableCreation.setTable(3);

				
				// image table
				try{
				Image jpg = Image.getInstance(getImagePath());
				tableCreation.insertCell(jpg, 0, 0, 0);
				}catch (Exception e){ 
					tableCreation.insertCell("", font2, 0, 1, 0);
					System.out.println("image not include"+e);}
					tableCreation.insertCell("\n\n\n\b\b\b\bOFFICE SHIELD INSURANCE \n\b\b\b\b\b\b\b\b\b\b\b\bPOLICY SCHEDULE\b\b\b", font3, 0, 1, 0);
					tableCreation.insertCell("\n\n\n\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\bXYZ INSURANCE\n\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\bCOMPANY LTD.\n\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b2nd Floor, Office Court, Oud Metha Road\n\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\bP.O. Box 28648, Dubai\n\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\bwww.XYZInsurance.com", font5, 0, 1, 0);
					

               // blank table
                for(int n=0;n<3;n++)
					tableCreation.insertCell("", font2, 0, 1, 0);

               // agent details
			   	tableCreation.insertCell(innertable1, 0, 1, 0);
				tableCreation.insertCell(innertable7, 0, 1, 0);
				tableCreation.insertCell(innertable2, 0, 1, 0);

				// blank table
               /* for(int n=0;n<3;n++)
					tableCreation.insertCell("\n", font2, 0, 1, 0);*/

                  	//cycle_id oa_code,ag_code
				tableCreation.insertCell(innertable3, 0, 1, 0);
				tableCreation.insertCell(innertable8, 0, 1, 0);
				tableCreation.insertCell(innertable9, 0, 1, 0);
				

                  // blank table
				  for(int n=0;n<3;n++)
					tableCreation.insertCell("", font2, 0, 1, 0);

			
				//total records,commission,premium header
				tableCreation.insertCell(innertable4, 0, 1, 0);
				tableCreation.insertCell(innertable5, 0, 1, 0);
				tableCreation.insertCell(innertable6, 0, 1, 0);

				tableCreation.setTableSpacingBefore(50f);
				return tableCreation.getTable();
	}

/*	public PdfPTable getNullTable()
	{
				
				//////////// Font Declaration ////////////
				Font font = new Font(Font.HELVETICA, 8);
				Font font1 = new Font(Font.HELVETICA, 8);
				Font font2 = new Font(Font.HELVETICA, 8, Font.BOLD);
				Font font3 = new Font(Font.HELVETICA, 10, Font.BOLD);
				//font3.setStyle(Font.UNDERLINE);
				Font font4 = new Font(Font.HELVETICA, 9);
				 
				tableCreation.setTable(11);
						
				
				tableCreation.insertCell("Date of\nIssue", font2, Rectangle.NO_BORDER, 1, 0);
				tableCreation.insertCell("Declaration No.\nCertificate No.\n\nInvoice No.", font2, Rectangle.NO_BORDER, 1, 0);
				tableCreation.insertCell("BL/AWB/CN NO.\n&. Date\nL.C. No.\nBank Interest", font2, Rectangle.NO_BORDER, 1, 0);
				tableCreation.insertCell("Mode of Transit\nVessel Name\nSailing Date", font2, Rectangle.NO_BORDER, 1, 0);
				tableCreation.insertCell("Interests", font2, Rectangle.NO_BORDER, 1, 0);
				tableCreation.insertCell("Sum Insured\nValuation Basis\nLoading/Addl. (L)\nF.C. Sum Insured\nExchange Rate", font2, Rectangle.NO_BORDER, 1, 1);
				tableCreation.insertCell("", font2, Rectangle.NO_BORDER, 1, 0);
				tableCreation.insertCell("Rate(%)", font2, Rectangle.NO_BORDER, 1, 0);
				tableCreation.insertCell("Premium", font2, Rectangle.NO_BORDER, 1, 0);
				tableCreation.insertCell("Premium", font2, Rectangle.NO_BORDER, 1, 0);
				tableCreation.insertCell("Premium", font2, Rectangle.NO_BORDER, 1, 0);
				tableCreation.setTableSpacingBefore(50f);
				return tableCreation.getTable();
	}*/

	public void onEndPage(PdfWriter writer, Document document)
	{
        try
		{
			//page++;
			page = writer.getPageNumber();
            Rectangle page = document.getPageSize();
            PdfPTable head = new PdfPTable(1);
			//
			/*Image jpg = Image.getInstance(getImagePath());
			PdfPCell cell = new PdfPCell(jpg);
			cell.setBorder(Rectangle.NO_BORDER);
			head.addCell(cell);
			head.setTotalWidth(page.width() - document.leftMargin() - document.rightMargin());
			head.writeSelectedRows(0, -1, document.leftMargin(), page.height() - document.topMargin() + head.getTotalHeight()-10, writer.getDirectContent());*/
			//
			PdfPCell cell1 = new PdfPCell(getHeaderTable());
			cell1.setBorder(Rectangle.NO_BORDER);
            head.addCell(cell1);
			head.setTotalWidth(page.getWidth() - document.leftMargin() - document.rightMargin());
			head.writeSelectedRows(0, -1, document.leftMargin(), page.getHeight() - document.topMargin() + head.getTotalHeight() - 90, writer.getDirectContent());

             System.out.println("Distext value.................................."+disText+"---"+getDisText());
             try 
             {
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
    catch (Exception de) 
    {}
	

			//
			/*PdfPCell cell2 = new PdfPCell(getNullTable());
			cell2.setBorder(Rectangle.NO_BORDER);
            head.addCell(cell2);
			head.setTotalWidth(page.width() - document.leftMargin() - document.rightMargin());
			head.writeSelectedRows(0, -1, document.leftMargin(), page.height() - document.topMargin() + head.getTotalHeight() - 54, writer.getDirectContent());*/
	

			
			/*PdfPTable foot = new PdfPTable(1);
			jpg.scaleAbsolute(500,60);
			jpg.setAbsolutePosition(document.leftMargin(), -15);
			jpg.scaleAbsoluteWidth(1050f);
			jpg.scaleAbsoluteHeight(90f);
			jpg.setDpi(300,300);
			foot.addCell(cell);
			foot.setTotalWidth(page.width() - document.leftMargin() - document.rightMargin() + 20);
			foot.writeSelectedRows(0, -1, 10, document.bottomMargin(), writer.getDirectContent());*/
        }
        catch (Exception e)
		{
            throw new ExceptionConverter(e);
        }
	}
	public void onStartPage(PdfWriter writer, Document document)
	{
		try
		{
			document.add(new Paragraph("\n\n\n"));
		}
		catch (Exception e)
		{
			throw new ExceptionConverter(e);
		}
	}
}