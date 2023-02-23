package com.maan.Office.DAO.pdfGenerate;


import java.io.PrintWriter;
import java.io.IOException;
import java.io.File;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Hashtable;
import java.awt.Color;
import java.io.FileOutputStream;
import java.util.*;
import java.net.URL;
import java.io.*;

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
import com.lowagie.text.pdf.*;
import com.maan.Office.DAO.scheduleBean;

//import Com.Maan.Home.DataManipualtion.HomePdfDataSelect;
//import Com.Maan.Home.DBCon.DBConnection;
//import com.maan.home.services.util.ValidationFormat;
//import com.maan.home.services.util.runner;
public class PDFCreatorBean 
{
	 public BaseFont helv;
	 public PdfGState gstate;

 	String urlPath="",urlPathFooter="", policyDate = "";
 	String SignImg="",StampImg="";
	public String getSignImg() {
		return SignImg;
	}
	public void setSignImg(String signImg) {
		SignImg = signImg;
	}
	public String getStampImg() {
		return StampImg;
	}
	public void setStampImg(String stampImg) {
		StampImg = stampImg;
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
	public String getPolicyDate()
	{
		return policyDate;
	}
	public void setPolicyDate(String policyDate)
	{
		this.policyDate=policyDate;					
	}
	
//	Added by chinna
	private String incep_date="";
	private HashMap CoverDetails=new HashMap();
	private String[][] ContentMaster=new String[0][0];
	public String[][] getContentMaster() {
		return ContentMaster;
	}
	public void setContentMaster(String[][] contentMaster) {
		ContentMaster = contentMaster;
	}
	public HashMap getCoverageDetails() {
		return CoverDetails;
	}

	public void setCoverageDetails(HashMap coverageDetails) {
		CoverDetails = coverageDetails;
	}
	public String getIncep_date() {
		return incep_date;
	}
	public void setIncep_date(String incep_date) {
		this.incep_date = incep_date;
	}
//	End

	Font font, fontHead, fontHead1, fontHead2, fontHead3, fontHead4,fontBold, fontBold1,
			fontText, fontText1, fontBoldText, fontUnder;
scheduleBean sBean=new scheduleBean();
//String[][] records = new String[0][0];
public void createPDF(String ID,String path,String imagepath,String CoverageInfo[][],String ExtensionInfo[][],int TotnoQuotenos,Vector premium,Vector mainCover,Vector subCover,Vector CusDetails,String contents_office,String waterMark,String policy_number,String currencyType,String userLoginMode,Vector uploadedfiles,Vector uploadedfilescid)

	{
	Connection con=null;
	Hashtable CoverageDetails;
	Image jpg1;
	convtNumtoWord NumtoWord=new convtNumtoWord();
	String CusInfo[][] = new String[0][0];
	String[][] main_coverage = new String[0][0];
	String[][] sub_coverage =  new String[0][0];
	String[][] uploaded_files =  new String[0][0];
	String[][] uploaded_filescid =  new String[0][0];
	String values[][]=new String[0][0];	 
	String total_premium="",qnos="";
	String each_page_update="Attaching to and forming part of Office Shield Insurance ";
	double totpremium=0.0;
	boolean spCondsts=false;
	

	// if test mode setting  watermark as a invalid and schedule
    if(userLoginMode.equalsIgnoreCase("Test") && "schedule".equalsIgnoreCase(ID) )
    waterMark="Invalid Copy";

	
	//HomePdfDataSelect pdfDataSelect;
	HeaderFooterImage endPage1;
	//TableHeader endPage1;
	//PageNumbersWatermarkNew pageWater;
	PdfPTableCreation tableCreation,tableCreation1,tableCreation2,tableCreation3,tableCreation4,tableCreation5;
	PdfPTable innertable1=null,innertable2=null,htable=null,htable1=null,stable=null,tbl1=null,tbl2=null,tbl3=null,tbl4=null;
	
			//pdfDataSelect = new HomePdfDataSelect();
			tableCreation = new PdfPTableCreation();
			tableCreation1 = new PdfPTableCreation();
			tableCreation2 = new PdfPTableCreation();
			tableCreation3 = new PdfPTableCreation();
			tableCreation4 = new PdfPTableCreation();
			tableCreation5 = new PdfPTableCreation();
			//pdfDataSelect.setCon(Com.Maan.Home.DBCon.DBConnection.getDBConnection1());*/
	
	
		
		
		//String presentationtype = "application/pdf";
		// records=(String [][])request.getAttribute("rds")==null?new String[0][0]:(String [][])request.getAttribute("rds");
		
        // Calculating  total premium,commission
		                 java.text.NumberFormat nf = java.text.NumberFormat.getInstance();
            	         java.text.DecimalFormat df = (java.text.DecimalFormat)nf;
                     
                    
      //  Document document = new Document(PageSize.A4.rotate());
		Document document = new Document(PageSize.A4, 5, 5, 70, 25);


		try
		{

	        
			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(path));
		  
			Rectangle page = (document.getPageSize());

			document.open();
				
				//////////// Font Declaration ////////////
				//Font font = new Font(Font.TIMES_ROMAN, 8);
				Font font1 = new Font(Font.TIMES_ROMAN, 9);
				Font font2 = new Font(Font.TIMES_ROMAN, 8);
				Font font3 = new Font(Font.TIMES_ROMAN, 10);
				//font3.setStyle(Font.UNDERLINE);
				Font font4 = new Font(Font.TIMES_ROMAN, 10);

				fontHead = new Font(Font.HELVETICA, 8, Font.BOLD);
				fontHead2 = new Font(Font.HELVETICA, 9, Font.BOLD);
				fontHead1 = new Font(Font.HELVETICA, 9, Font.NORMAL);
				fontHead3 = new Font(Font.HELVETICA, 11, Font.NORMAL);
				fontHead4=new Font(Font.HELVETICA,13,Font.BOLD);
				font = new Font(Font.HELVETICA, 8, Font.NORMAL);
				fontBold = new Font(Font.HELVETICA, 8, Font.BOLD);
				fontBold1 = new Font(Font.HELVETICA, 11, Font.BOLD);
				fontText = new Font(Font.HELVETICA, 7, Font.NORMAL);
				fontText1 = new Font(Font.HELVETICA, 7, Font.BOLD);
				fontBoldText = new Font(Font.HELVETICA, 8, Font.BOLD);
				fontUnder = new Font(Font.HELVETICA, 10, Font.UNDERLINE);
			    tableCreation.setTable(3);
				tableCreation.insertCell("", font3, Rectangle.NO_BORDER, 3, 2);
				tableCreation.setTableSpacingBefore(80f);
				stable = tableCreation.getTable();
				document.add(stable);	
				
				try
				{

					//endPage1=new TableHeader(document);
					endPage1=new HeaderFooterImage();
					
					//endPage1.setImagePath(imagepath);
					endPage1.setHeaderImagePath(urlPath);
					endPage1.setFooterImagePath(urlPathFooter);
                     if("schedule".equalsIgnoreCase(ID))
					{
                    endPage1.setPolicyNo(policy_number); 
					}
					 if("draft".equalsIgnoreCase(ID))
						{
						      
							for(int x=0;x<TotnoQuotenos;x++)
							{
								 CusInfo=(String[][])CusDetails.get(x);
								 qnos+=((CusInfo[0][0]==null?"":CusInfo[0][0])+",");
								 total_premium=(String)premium.get(x);
								 // totpremium+=(Double.parseDouble((total_premium==null?"0":total_premium)));
								  
							}
						   qnos=qnos.substring(0,(qnos.length()-1));
						   endPage1.setPolicyNo(qnos); 
						   qnos="";
				
						}
						 if("certificate".equalsIgnoreCase(ID))
					     {
						     endPage1.setPolicyNo(policy_number); 
						 }
                   	endPage1.setDisplayText(waterMark);
					writer.setPageEvent(endPage1);

					/*pageWater = new PageNumbersWatermarkNew();
				    
				    pageWater.setImagePath(urlPath);
				    pageWater.setDisplayText(waterMark);
					writer.setPageEvent(pageWater);*/
				}
				catch(Exception exImageHeader)
				{}
				

			/////////Loop///////
			  
             
				
		if("schedule".equalsIgnoreCase(ID) || "draft".equalsIgnoreCase(ID))
			{
						
						tableCreation.setTable(1);
						tableCreation.insertCell(" ", font4, Rectangle.NO_BORDER, 1, 2);
						tableCreation.setTableSpacingBefore(50f);
						document.add(tableCreation.getTable());
// customer info updation

                     tableCreation.setTable(1);
					  tableCreation.insertCell("OFFICE SHIELD INSURANCE POLICY SCHEDULE", fontHead4, Rectangle.NO_BORDER, 1, 2);
					  tableCreation.setTableSpacingBefore(2f);
						document.add(tableCreation.getTable());

                      
                 for(int x=0;x<TotnoQuotenos;x++)
				{
					 CusInfo=(String[][])CusDetails.get(x);
                     qnos+=((CusInfo[0][0]==null?"":CusInfo[0][0])+",");
					 total_premium=(String)premium.get(x);
	                  totpremium+=(Double.parseDouble((total_premium==null?"0":total_premium)));
				}
               qnos=qnos.substring(0,(qnos.length()-1));
                  	CusInfo=(String[][])CusDetails.get(0);
                    if(CusInfo.length>0)
                    {    
						if("schedule".equalsIgnoreCase(ID))
						{
						//	policyDate = CusInfo[0][21];
                        tableCreation.setTable(3);
						tableCreation.insertCell("Policy Number :", fontHead2, Rectangle.NO_BORDER, 1, 0);
						tableCreation.insertCell(policy_number, fontHead1, Rectangle.NO_BORDER, 2, 0);
						tableCreation.insertCell("\b\b\b\b\b ", fontHead1, Rectangle.NO_BORDER, 2, 2);
						tableCreation.setTableSpacingBefore(10f);
						document.add(tableCreation.getTable());
						each_page_update+= "Policy Number :"+policy_number;
						}
                         
						if("draft".equalsIgnoreCase(ID))
						{
							policyDate = getSystemDate();
						tableCreation.setTable(3);
						tableCreation.insertCell("Quote No. :", fontHead2, Rectangle.NO_BORDER, 1, 0);
						tableCreation.insertCell(qnos, fontHead1, Rectangle.NO_BORDER, 2, 0);
						tableCreation.insertCell("\b\b\b\b\b ", fontHead1, Rectangle.NO_BORDER, 2, 2);
						tableCreation.setTableSpacingBefore(10f);
						document.add(tableCreation.getTable());
						each_page_update+= "Quote No. :"+qnos;
						}

						tableCreation.setTable(1);
						tableCreation.insertCell("  ", font4, Rectangle.NO_BORDER, 1, 2);
						document.add(tableCreation.getTable());
                        
						 tableCreation.setTable(1);
						tableCreation.insertCell("INSURED DETAILS", fontBoldText, Rectangle.BOX, 1, 2);
						document.add(tableCreation.getTable());

						 

						  tableCreation.setTable(3);
						 tableCreation.insertCell("Insured Name : ", fontHead2, Rectangle.NO_BORDER, 1, 0);			
						 tableCreation.insertCell(((CusInfo[0][1]==null?"":CusInfo[0][1])+"."+(CusInfo[0][2]==null?"":CusInfo[0][2])+(CusInfo[0][3]==null?"":CusInfo[0][3])), fontHead1, Rectangle.NO_BORDER, 2, 0);
						 tableCreation.insertCell("\b\b\b\b\b ", fontHead1, Rectangle.NO_BORDER, 2, 2);
						document.add(tableCreation.getTable());
    
		                tableCreation.setTable(3);
						 tableCreation.insertCell("Registered Address : ", fontHead2, Rectangle.NO_BORDER, 1, 0);
						tableCreation.insertCell(((CusInfo[0][4]==null?"":CusInfo[0][4])+(CusInfo[0][5]==null?"":CusInfo[0][5])+(CusInfo[0][18]==null?"":","+CusInfo[0][18])+(CusInfo[0][20]==null?"":","+CusInfo[0][20])+(CusInfo[0][19]==null?"":(CusInfo[0][19].equalsIgnoreCase("select")?"":","+CusInfo[0][19]))), fontHead1, Rectangle.NO_BORDER, 2, 0);
						tableCreation.insertCell("\b\b\b\b\b ", fontHead1, Rectangle.NO_BORDER, 2, 2);
						document.add(tableCreation.getTable());

						 tableCreation.setTable(3);
						 tableCreation.insertCell("Period of Insurance :  ", fontHead2, Rectangle.NO_BORDER, 1, 0);
						tableCreation.insertCell(((CusInfo[0][14]==null?"":CusInfo[0][14])+" to "+(CusInfo[0][15]==null?"":CusInfo[0][15])+" ( BOTH DAYS INCLUSIVE ) "), fontHead1, Rectangle.NO_BORDER, 2, 0);
						tableCreation.insertCell("\b\b\b\b\b ", fontHead1, Rectangle.NO_BORDER, 2, 2);
						document.add(tableCreation.getTable());

						 tableCreation.setTable(3);
						 tableCreation.insertCell("Broker Name : ", fontHead2, Rectangle.NO_BORDER, 1, 0);
						tableCreation.insertCell((CusInfo[0][16]==null?"":CusInfo[0][16]), fontHead1, Rectangle.NO_BORDER, 2, 0);
						tableCreation.insertCell("\b\b\b\b\b ", fontHead1, Rectangle.NO_BORDER, 2, 2);
						document.add(tableCreation.getTable());
				
               }// if(CusInfo.length>0)
				

				   createemptyColumn(document,stable,tableCreation);
                        tableCreation.setTable(1);
						tableCreation.insertCell("COVER SUMMARY", fontBoldText, Rectangle.BOX, 1, 2);
						document.add(tableCreation.getTable());
						 
                        tableCreation.setTable(1);
						tableCreation.insertCell((TotnoQuotenos==1?"Insured Location : ":"Insured Locations : "), fontHead2, Rectangle.NO_BORDER, 1, 0);
						document.add(tableCreation.getTable());
				   for(int x=0;x<TotnoQuotenos;x++)
                  {
                  	CusInfo=(String[][])CusDetails.get(x);
                    if(CusInfo.length>0)
                        {    
						 
						  tableCreation.setTable(5);
						 tableCreation.insertCell(("Premises "+(x+1)+" :"), fontHead2, Rectangle.NO_BORDER, 1, 0);		tableCreation.insertCell(((CusInfo[0][10]==null?"":CusInfo[0][10])+","+(CusInfo[0][13]==null?"":CusInfo[0][13])+"-"+(CusInfo[0][11]==null?"":CusInfo[0][11])+","+(CusInfo[0][12]==null?"":CusInfo[0][12])), fontHead1, Rectangle.NO_BORDER, 4, 0);
						 //tableCreation.insertCell("\b\b\b\b\b ", fontHead1, Rectangle.NO_BORDER, 2, 2);
						document.add(tableCreation.getTable());
                   
				       if( CusInfo[0][17]!=null && spCondsts==false)
                               spCondsts=true;
					
				
               }// if(CusInfo.length>0)
				  }// for x

                if(TotnoQuotenos==1)
				{  createemptyColumn(document,stable,tableCreation);
				   createemptyColumn(document,stable,tableCreation);
				   createemptyColumn(document,stable,tableCreation);
				}
				else
					createemptyColumn(document,stable,tableCreation);

   tableCreation.setTable(TotnoQuotenos);	
  
   for(int x=0;x<TotnoQuotenos;x++)
{
 tableCreation.insertCell(("Premises "+(x+1)), fontHead2, Rectangle.BOX, 1, 2);	

}
   tbl1=tableCreation.getTable();
  

    tableCreation.setTable(TotnoQuotenos);	
  
   for(int x=0;x<TotnoQuotenos;x++)
{
 tableCreation.insertCell("Covered ", fontHead1, Rectangle.BOX, 1, 2);	
}
    tbl2=tableCreation.getTable();
 

	 tableCreation.setTable(2);	 
	  tableCreation.insertCell(tbl1, Rectangle.BOX, 3, 0);
	   tableCreation.insertCell(tbl2, Rectangle.BOX, 3, 0);
	   tbl3=tableCreation.getTable();

	   tableCreation.setTable(TotnoQuotenos+1);	 
	  tableCreation.insertCell("\nPolicy section", fontHead2, Rectangle.BOX, 1, 2);
	   tableCreation.insertCell(tbl3, Rectangle.BOX,TotnoQuotenos , 0);
	  document.add(tableCreation.getTable());


for(int m=0;m<CoverageInfo.length;m++)
{
	 if((",5,").indexOf (","+CoverageInfo[m][0]+",") == -1)
	{
                        tableCreation.setTable(TotnoQuotenos+1);	   		  
						tableCreation.insertCell(("\b"+(CoverageInfo[m][1]==null?"":CoverageInfo[m][1])), fontHead1, Rectangle.NO_BORDER, 0, 0);
			            //System.out.println("CoverageInfo[m][1]"+CoverageInfo[m][1]); 				

for(int x=0;x<TotnoQuotenos;x++)
{
	main_coverage=(String[][])mainCover.get(x);
	
if(main_coverage.length>0)
	{         
	          boolean sts=true;
				for(int i=0;i<main_coverage.length;i++) { 
					//System.out.println("CoverageInfo[m][0]"+CoverageInfo[m][0]+"--"+"main_coverage[i][0]"+main_coverage[i][0]); 	  
					if(CoverageInfo[m][0].equals(main_coverage[i][0])) {
						 //System.out.println("main_coverage[i][10]"+main_coverage[i][10]); 	  
						 tableCreation.insertCell((main_coverage[i][10]==null?"No":(main_coverage[i][10].equalsIgnoreCase("Y")?"Yes":"No")), fontHead1, Rectangle.NO_BORDER, 0, 2);
						sts=false;
												
					}   } if(sts)
						tableCreation.insertCell("No", fontHead1, Rectangle.NO_BORDER, 0, 2);
					
		
			} 
} // for QuoteLength

 document.add(tableCreation.getTable());
	} // if((",2,").indexOf (","+main_coverage[i][0]+",") != -1)
		
}// for CoverageInfo%>
	
           

                         
                        // total premium 
                        /*tableCreation.setTable(2);
						tableCreation.insertCell("TOTAL PREMIUM : ", font3, Rectangle.BOX, 1, 2);
						tableCreation.insertCell((""+totpremium+" "+currencyType), font3, Rectangle.BOX, 2, 2);
						document.add(tableCreation.getTable());*/	

						 tableCreation.setTable(TotnoQuotenos+1);	
						 tableCreation.insertCell("Total Premium ("+currencyType+")", fontHead2, Rectangle.BOX, 0, 0);
						 for(int x=0;x<TotnoQuotenos;x++)
						{
							tableCreation.insertCell(df.format(Double.parseDouble(((String)premium.get(x)))), fontHead1, Rectangle.BOX, 1, 2);	
						}
                        tableCreation.setTableSpacingBefore(10f);
						document.add(tableCreation.getTable());

						//createemptyColumn(document,stable,tableCreation);
                        //updating special condition 
						 if(spCondsts)
				        {
						 tableCreation.setTable(1);
						 tableCreation.insertCell("SPECIAL CONDITION :", font3, Rectangle.NO_BORDER, 1, 0);		
						 document.add(tableCreation.getTable());
						for(int x=0;x<TotnoQuotenos;x++)
                        {
                  	       CusInfo=(String[][])CusDetails.get(x);
                            if(CusInfo.length>0 )
                                  {    
						   		  tableCreation.setTable(1);
						          tableCreation.insertCell(("Premises "+(x+1)+"\n"+(CusInfo[0][17]==null?"":CusInfo[0][17])), font3, Rectangle.BOX, 1, 0);		
						         document.add(tableCreation.getTable());
                                 }// if(CusInfo.length>0)
	      			  }// for x
						}//if spCondsts

						 createemptyColumn(document,stable,tableCreation);
                        // total premium 
                        tableCreation.setTable(1);
						tableCreation.insertCell("All Figures mentioned in the Schedule are in AED.", fontHead1, Rectangle.BOX, 1, 0);
						document.add(tableCreation.getTable());	

				         createemptyColumn(document,stable,tableCreation);
                        // total premium 
                        tableCreation.setTable(1);
						tableCreation.insertCell("IN CONSIDERATION OF THE ABOVE A PREMIUM OF AED. "+df.format(totpremium)+" IS HEREBY CHARGED TO AND DUE FROM THE INSURED SUBJECT OTHERWISE TO THE TERMS, CONDITIONS AND EXCEPTIONS OF THE POLICY. ", fontHead1, Rectangle.BOX, 1, 0);
						document.add(tableCreation.getTable());	

						createemptyColumn(document,stable,tableCreation);
						
                        // total premium 
                        tableCreation.setTable(1);
						//tableCreation.insertCell("THE POLICY BOOKLET FORMS A PART OF THIS POLICY.", fontHead1, Rectangle.BOX, 1, 0);
						tableCreation.insertCell("THE SCHEDULE FORMS PART OF THIS POLICY AND ATTACHED EXTENSIONS.", fontHead1, Rectangle.BOX, 1, 0);
						document.add(tableCreation.getTable());
						
                        createemptyColumn(document,stable,tableCreation);
						createemptyColumn(document,stable,tableCreation);
						tableCreation.setTable(2);
						tableCreation.insertCell("SIGNED ON : "+policyDate, fontHead1, Rectangle.NO_BORDER, 1, 0);
						//tableCreation.insertCell("SIGNED ON : "+getSystemDate(), fontHead1, Rectangle.NO_BORDER, 1, 0); //Jan-20-2009
						tableCreation.insertCell("XYZ Insurance Company Ltd.", fontBoldText, Rectangle.NO_BORDER, 1, 2);
						document.add(tableCreation.getTable());	
                        

						// if live mode adding signature image
						if(userLoginMode.equalsIgnoreCase("Live")||userLoginMode.equalsIgnoreCase("Test"))
						tableCreation.setTable(4);
						else
                         tableCreation.setTable(2);

						//tableCreation.insertCell("ISSUING OFFICE: Dubai Branch \n2nd Floor, Office Court,\nOud Metha Road,\nP.O. Box 28648, Dubai ", fontHead1, Rectangle.NO_BORDER, 1, 0);
						tableCreation.insertCell("\n\n\n", fontHead1, Rectangle.NO_BORDER, 1, 0);
                        if(userLoginMode.equalsIgnoreCase("Live")||userLoginMode.equalsIgnoreCase("Test"))
				         {
						tableCreation.insertCell("                   ", font3, Rectangle.NO_BORDER, 1, 2);
						Image jpg_new = Image.getInstance(imagepath+"/"+getSignImg());
						jpg_new.setAbsolutePosition(50, 250); 
						jpg_new.scaleAbsolute(70, 54); 
						tableCreation.insertCell(jpg_new, 0, 0, 1);
						}

						 Image jpg = Image.getInstance(imagepath+"/"+getStampImg());
						jpg.setAbsolutePosition(50, 250); 
						jpg.scaleAbsolute(60, 64); 
						if(userLoginMode.equalsIgnoreCase("Live") ||userLoginMode.equalsIgnoreCase("Test"))
						tableCreation.insertCell(jpg, 0, 0, 0);
						else
                        tableCreation.insertCell(jpg, 0, 0, 2);

						 document.add(tableCreation.getTable());
						
						tableCreation.setTable(2);
						tableCreation.insertCell(" ", font3, Rectangle.NO_BORDER, 1, 2);
						tableCreation.insertCell("AUTHORISED SIGNATORY", fontBoldText, Rectangle.NO_BORDER, 1, 2);
						document.add(tableCreation.getTable());	

                           createemptyColumn(document,stable,tableCreation);
							createemptyColumn(document,stable,tableCreation);

							 //document.clearTextWrap();
						 for(int x=0;x<TotnoQuotenos;x++)
						 {
                            		
							 
						  main_coverage=(String[][])mainCover.get(x);
							sub_coverage=(String[][])subCover.get(x);
							CusInfo=(String[][])CusDetails.get(x);
							uploaded_files=(String[][])uploadedfiles.get(x);
							uploaded_filescid=(String[][])uploadedfilescid.get(x);
							
							if(x!=0)
								document.newPage();
                        
						//attaching format line included on each page starting
							/* tableCreation.setTable(1);
							tableCreation.insertCell(each_page_update, fontHead2, Rectangle.NO_BORDER, 1, 0);
							document.add(tableCreation.getTable());
                             tableCreation.setTable(1);*/
							 
												
								//createemptyColumn(document,stable,tableCreation);
								document.newPage();
								tableCreation.setTable(1);
								tableCreation.insertCell("Cover Details :", fontHead2, Rectangle.NO_BORDER, 1, 0);
								document.add(tableCreation.getTable());
								//tableCreation.setTableSpacingBefore(2f);
								
								
								//Block Added by chinna for Home Coverage Details
								createemptyColumn_new(document,stable,tableCreation);
								int index=0;boolean flag=true;ArrayList coverNames=new ArrayList();String[][] columnNames=new String[0][0];
								if(ContentMaster !=null && ContentMaster.length<=0 && CoverDetails!=null && CoverDetails.size()>0)
								{
									Set list=CoverDetails.keySet();
									Iterator itr=list.iterator();
									while(itr.hasNext())
									{
										HashMap headers=(HashMap)itr.next();
										if(headers!=null && headers.size()>0)
										{
											if(flag)
											{
												Set list3=headers.keySet();
												Iterator i2=list3.iterator();
												while(i2.hasNext())
												{
													String coverName=(String)i2.next();
													coverNames.add(coverName);
												}
												
												flag=false;
											}
											
											tableCreation.setTable(1);
											tableCreation.insertCell(((String)(coverNames.get(index)==null?"":coverNames.get(index))), fontHead, Rectangle.BOX, 1, 0);
											document.add(tableCreation.getTable());
											
											columnNames=(String[][])headers.get(coverNames.get(index));
											tableCreation.setTable(columnNames.length);
											for(int i=0; i<columnNames.length; i++)
											{
												tableCreation.insertCell((columnNames[i][0]==null?"":columnNames[i][0]), fontBoldText, Rectangle.BOX, 1, 0);
											}
											HashMap coverValues=(HashMap)CoverDetails.get(headers);
											ArrayList cValues=(ArrayList)coverValues.get(coverNames.get(index));
											if(cValues.size()>0)
											{
												for(int v=0; v<cValues.size(); v++)
												{
													tableCreation.insertCell((cValues.get(v)==null?"":(String)cValues.get(v)), fontText, Rectangle.BOX, 1, 0);
												}
											}
										}
										document.add(tableCreation.getTable());
										createemptyColumn(document,stable,tableCreation);
										index++;
									}
								}
								document.add(tableCreation.getTable());
								//---End 
								
								
								 tableCreation.setTable(7);
								 tableCreation.insertCell(("Premises "+(x+1)+":"), fontHead2, Rectangle.NO_BORDER, 1, 0);		tableCreation.insertCell(((CusInfo[0][10]==null?"":CusInfo[0][10])+","+(CusInfo[0][13]==null?"":CusInfo[0][13])+"-"+(CusInfo[0][11]==null?"":CusInfo[0][11])+","+(CusInfo[0][12]==null?"":CusInfo[0][12])), fontHead1, Rectangle.NO_BORDER, 6, 0);
								 //tableCreation.insertCell("\b\b\b\b\b ", fontHead1, Rectangle.NO_BORDER, 1, 2);
								document.add(tableCreation.getTable());

								createemptyColumn_new(document,stable,tableCreation);
						         
								if(main_coverage.length>0)
							{         
							
                                    String NoofEmp="",GpaOpt="";TreeMap tm =null;boolean gpaheaderopt=true;
								
										for(int i=0;i<main_coverage.length;i++) { 
											   boolean sts=false;
												 tableCreation1.setTable(5);
												 tableCreation4.setTable(2);
											//if(main_coverage[i][10].equalsIgnoreCase("Y") && !main_coverage[i][0].equalsIgnoreCase("5"))
											if( !main_coverage[i][0].equalsIgnoreCase("5"))
											  {   
															
												 if(main_coverage[i][5].equalsIgnoreCase("Y") && !main_coverage[i][0].equalsIgnoreCase("9") )
												  {   
													  tm = new TreeMap(); 
													 
													 for(int j=0;j<sub_coverage.length;j++)
															  { 
																 if(main_coverage[i][0].equals(sub_coverage[j][1]) && !sub_coverage[j][0].equalsIgnoreCase("26"))
																  {
																	
																	  if(main_coverage[i][0].equalsIgnoreCase("10") )
																	  { // gpa option updated here 
                                                                         //GpaOpt+=sub_coverage[j][2]+"   -   "+(sub_coverage[j][9]==null?"N/A":(sub_coverage[j][9].equalsIgnoreCase("null")?"N/A":sub_coverage[j][9]))+"\n";
																		 if(gpaheaderopt)
																		  {
																		 tableCreation4.insertCell("No. of employees covered\n", font, Rectangle.BOX, 2, 2);
																		 gpaheaderopt=false;
																		  }
																		 tableCreation4.insertCell("\n "+(sub_coverage[j][9]==null?"N/A":(sub_coverage[j][9].equalsIgnoreCase("null")?"N/A":sub_coverage[j][9])), font, Rectangle.BOX, 2, 2);
																	  }else if(main_coverage[i][0].equalsIgnoreCase("8") )
																	  {
	                                                    			        if(sub_coverage[j][0].equalsIgnoreCase("18") )NoofEmp=(sub_coverage[j][9]==null?"N/A":(sub_coverage[j][9].equalsIgnoreCase("null")?"N/A":sub_coverage[j][9]));
																	  }else
																	  {
                                                                      
																	 if(main_coverage[i][10].equalsIgnoreCase("Y")) {
																	    if(sub_coverage[j][6].equalsIgnoreCase("Display") ) 
																	   tm.put(new Integer(sub_coverage[j][7]),(sub_coverage[j][2]+"~"+(sub_coverage[j][3]==null?"N/A":(sub_coverage[j][3].equalsIgnoreCase("null")?"N/A":sub_coverage[j][3])))); 	
																	 else
																		 tm.put(new Integer(sub_coverage[j][7]),(sub_coverage[j][2]+"~"+(sub_coverage[j][9]==null?"N/A":(sub_coverage[j][9].equalsIgnoreCase("null")?"N/A":sub_coverage[j][9])))); 
																	   }else
																		   tm.put(new Integer(sub_coverage[j][7]),("N/A~ ")); 	

																	  }

																	  
																  }//main_coverage[i][0].equals(sub_coverage[j][1])
																	
															  }// for int j

													  if(main_coverage[i][0].equalsIgnoreCase("8") )
													  {
														 tableCreation1.insertCell("Sum Insured", font, Rectangle.BOX, 2, 0);
														
														 tableCreation1.insertCell( main_coverage[i][10].equalsIgnoreCase("Y")?"10,000 in the annual aggregate":"N/A", font, Rectangle.BOX, 3, 0);
														 tableCreation1.insertCell("No. Of Employees ", font, Rectangle.BOX, 2, 0);
												         tableCreation1.insertCell((NoofEmp.length()<=0?"N/A":NoofEmp), font, Rectangle.BOX, 3, 0);
													  }

													   if(main_coverage[i][0].equalsIgnoreCase("10"))
												       {
														 tableCreation1.insertCell("Option \nChosen", font, Rectangle.BOX, 3, 2);
														 tableCreation1.insertCell("Accumulation \nLimit", font, Rectangle.BOX, 2, 2);
														 tableCreation1.insertCell("24 months salary upto 100,000/- per person & 1,000,000 in the aggregate", font, Rectangle.BOX, 3, 0);
														 //tableCreation1.insertCell("Dhs. 300,000", font, Rectangle.BOX, 2, 0);
														 tableCreation1.insertCell("300,000", font, Rectangle.BOX, 2, 0);
														 tableCreation1.insertCell("24 months salary upto 250,000/- per person & 2,500,000 per occurrence", font, Rectangle.BOX, 3, 0);
												         //tableCreation1.insertCell("Dhs. 500,000", font, Rectangle.BOX, 2, 0);
												         tableCreation1.insertCell("500,000", font, Rectangle.BOX, 2, 0);
														 tableCreation1.insertCell("24 months salary upto 500,000/- per person & 5,000,000 per occurrence", font, Rectangle.BOX, 3, 0);
												         //tableCreation1.insertCell("Dhs. 1,000,000", font, Rectangle.BOX, 2, 0);
												         tableCreation1.insertCell("1,000,000", font, Rectangle.BOX, 2, 0);
                                                        }

												    // Get a set of the entries 
													Set set = tm.entrySet(); 
													// Get an iterator 
													Iterator itr = set.iterator(); 
													// Display elements 
													System.out.print("Its start here...................................................................: "); 
													while(itr.hasNext()) { 
													Map.Entry me = (Map.Entry)itr.next(); 
													System.out.print(me.getKey() + ": "); 
													System.out.println(me.getValue());
													String temp_val=(String)me.getValue();
													StringTokenizer st=new StringTokenizer(temp_val,"~");
													while(st.hasMoreTokens())
														{
														
														tableCreation1.insertCell(st.nextToken(), font, Rectangle.BOX, 2, 0);
														tableCreation1.insertCell(st.nextToken(), font, Rectangle.BOX, 3, 0);

														}

													} 
                                                     System.out.print("Its end here...................................................................: "); 

												  innertable2 = tableCreation1.getTable();
												  tbl4=tableCreation4.getTable();
                      
												 tableCreation.setTable(7);
												 tableCreation.insertCell("\b\b\b\b\b"+(main_coverage[i][14]==null?"":main_coverage[i][14]), fontHead, Rectangle.BOX, 1, 0);
												tableCreation.insertCell(main_coverage[i][3], fontHead, Rectangle.BOX, 1, 0);
												if(main_coverage[i][0].equalsIgnoreCase("10"))
												{ tableCreation.insertCell(tbl4, Rectangle.BOX, 1, 0);
												tableCreation.insertCell(innertable2, Rectangle.BOX, 4, 0);}
												else
												     tableCreation.insertCell(innertable2, Rectangle.BOX, 5, 0);
												//tableCreation.setTableSpacingBefore(5f);
												document.add(tableCreation.getTable());

												  }  else
												  {
												tableCreation.setTable(7);
												tableCreation.insertCell("\b\b\b\b\b"+(main_coverage[i][14]==null?"":main_coverage[i][14]), fontHead, Rectangle.BOX, 1, 0);
													   tableCreation.insertCell(main_coverage[i][3], fontHead, Rectangle.BOX, 1, 0);

													    if(main_coverage[i][0].equalsIgnoreCase("3"))
														  tableCreation.insertCell("Limit of indemnity", font, Rectangle.BOX, 2, 0);
														else if (main_coverage[i][0].equalsIgnoreCase("4"))
														  tableCreation.insertCell("Estimated Annual Wages", font, Rectangle.BOX, 2, 0);
															else
													       tableCreation.insertCell("Sum Insured", font, Rectangle.BOX, 2, 0);

													  if( main_coverage[i][0].equalsIgnoreCase("9"))
     												    tableCreation.insertCell("10,000 in the annual aggregate", font, Rectangle.BOX, 3, 0);
														  else
												     tableCreation.insertCell(main_coverage[i][7]==null?"N/A": main_coverage[i][10].equalsIgnoreCase("N")?"N/A":(df.format(Double.parseDouble(main_coverage[i][7]))), font, Rectangle.BOX, 3, 0);
													// tableCreation.setTableSpacingBefore(5f);
														  document.add(tableCreation.getTable());
												  }
												  


												 											  

                                              // adding extension values in pdf
                                             
											/*   tableCreation1.setTable(5);
											   for(int mx=0;mx<ExtensionInfo.length;mx++)
															  { 
																 if(main_coverage[i][0].equals(ExtensionInfo[mx][0]))
																  {
																	  tableCreation1.insertCell(ExtensionInfo[mx][2], font, Rectangle.BOX, 5, 0);
																	  sts=true;
																  }//main_coverage[i][0].equals(ExtensionInfo[mx][0])
																																								
															  }// for mx
															  if(sts)
												              {
															   innertable1 = tableCreation1.getTable();
															   tableCreation.setTable(7);
															  tableCreation.insertCell(" ", font, Rectangle.BOX, 1, 0);
															 tableCreation.insertCell(" ", font, Rectangle.BOX, 1, 0);
															 tableCreation.insertCell("Extension ", font, Rectangle.BOX, 2, 0);
															 tableCreation.insertCell(innertable1, Rectangle.BOX, 3, 0);
															  document.add(tableCreation.getTable());
															  }
																	 
															   // updating excess details content of ur office,portable equipments for specfic coverages
															  if((",2,").indexOf (","+main_coverage[i][0]+",") != -1)
																  {
																     tableCreation.setTable(7);
																	 tableCreation.insertCell(" ", font, Rectangle.BOX, 1, 0);
																	 tableCreation.insertCell(" ", font, Rectangle.BOX, 1, 0);
																	 tableCreation.insertCell("Excess for "+main_coverage[i-1][3], font, Rectangle.BOX, 2, 0);
																	 tableCreation.insertCell(main_coverage[i-1][15], font, Rectangle.BOX, 3, 0);
																	  document.add(tableCreation.getTable());

																    tableCreation.setTable(7);
																	 tableCreation.insertCell(" ", font, Rectangle.BOX, 1, 0);
																	 tableCreation.insertCell(" ", font, Rectangle.BOX, 1, 0);
																	 tableCreation.insertCell("Excess for "+main_coverage[i][3], font, Rectangle.BOX, 2, 0);
																	 tableCreation.insertCell(main_coverage[i][15], font, Rectangle.BOX, 3, 0);
																	  document.add(tableCreation.getTable());

																  }

																   // updating excess details public liablity for specfic coverages
															  if((",3,").indexOf (","+main_coverage[i][0]+",") != -1)
																  {
																      tableCreation.setTable(7);
																	 tableCreation.insertCell(" ", font, Rectangle.BOX, 1, 0);
																	 tableCreation.insertCell(" ", font, Rectangle.BOX, 1, 0);
																	 tableCreation.insertCell("Excess for "+main_coverage[i][3], font, Rectangle.BOX, 2, 0);
																	 tableCreation.insertCell(main_coverage[i][15], font, Rectangle.BOX, 3, 0);
																	  document.add(tableCreation.getTable());

																  }
												                   
                                                       // updating warrented details for specfic coverages
                                                                  if((",2,,8,,9,,10,").indexOf (","+main_coverage[i][0]+",") != -1)
																  {
                                                               tableCreation.setTable(1);
															   tableCreation.insertCell(" Warranted that anything mentioned in the uploaded data will not supercede the coverage in the policy ", font1, Rectangle.BOX, 1, 2);
															    document.add(tableCreation.getTable());
																  } */
												     // adding excess values
													           if(main_coverage[i][15]!=null && main_coverage[i][10].equalsIgnoreCase("Y"))
																  {
																    tableCreation.setTable(7);
																	 tableCreation.insertCell(" ", font, Rectangle.BOX, 1, 0);
																	 tableCreation.insertCell(" ", font, Rectangle.BOX, 1, 0);
																	 tableCreation.insertCell("Excess for "+main_coverage[i][3], font, Rectangle.BOX, 2, 0);
																	 tableCreation.insertCell(main_coverage[i][15], font, Rectangle.BOX, 3, 0);
																	  document.add(tableCreation.getTable());

																  }
															 // adding extension values in pdf
                                             
											                tableCreation1.setTable(5);
											                 for(int mx=0;mx<ExtensionInfo.length;mx++)
															  { 
																 if(main_coverage[i][0].equals(ExtensionInfo[mx][0]))
																  {
																	  tableCreation1.insertCell(ExtensionInfo[mx][2], font, Rectangle.BOX, 5, 0);
																	  sts=true;
																  }//main_coverage[i][0].equals(ExtensionInfo[mx][0])
																																								
															  }// for mx
															  if(sts)
												              {
															   innertable1 = tableCreation1.getTable();
															   tableCreation.setTable(7);
															  tableCreation.insertCell(" ", font, Rectangle.BOX, 1, 0);
															 tableCreation.insertCell(" ", font, Rectangle.BOX, 1, 0);
															 tableCreation.insertCell("Extensions ", font, Rectangle.BOX, 2, 0);
															 tableCreation.insertCell(innertable1, Rectangle.BOX, 3, 0);
															  document.add(tableCreation.getTable());
															  }
																	 
																	           
											  }//if(main_coverage[i][10].Y      
											
											}//for main_coverage.length
                                                // addding warented details
												 tableCreation.setTable(1);
									             tableCreation.insertCell(" Warranted that anything mentioned in the uploaded data will not supercede the coverage in the policy ", font1, Rectangle.BOX, 1, 2);
												document.add(tableCreation.getTable());
												createemptyColumn_new(document,stable,tableCreation);
												
												tableCreation.setTable(2);
												tableCreation.insertCell("Premium for Premises "+(x+1)+" : ", fontHead2, Rectangle.BOX, 1, 2);
												tableCreation.insertCell((""+ df.format(Double.parseDouble(((String)premium.get(x))))), fontHead1, Rectangle.BOX, 2, 2);
												document.add(tableCreation.getTable());
												
                                                //Added by chinna
												createemptyColumn_new(document,stable,tableCreation);
												tableCreation.setTable(20);
												tableCreation.insertCell("\b\b\b\b\b", fontHead2, 0, 20, 0);
												tableCreation.insertCell("I/We declare that the foregoing statements and particulars are true and complete and that this Schedule shall form the basis of the contract with Qatar Insurance Company.  I/We agree to accept insurance subject to the terms and conditions of the Homecare Household Insurance policy document issued by the Company attached herewith. I/We note that the insurance will not be in force until the Schedule has been accepted by the Company and confirmation of such received by me/us in writing along with Endorsements if any.", font1, 0, 20, 0);
												tableCreation.insertCell("NB: ", fontHead2, Rectangle.NO_BORDER, 1, 0);
												tableCreation.insertCell("The value of the building should represent the construction cost at the time of insuring (depreciation value applicable for the rebuilding cost) excluding the land value. ", font1, 0, 19, 0);
												document.add(tableCreation.getTable());
												
												tableCreation.setTable(2);
												tableCreation.insertCell("\b\b\b\b\b", fontHead2, 0, 2, 0);
												tableCreation.insertCell("\b\b\b\b\b", fontHead2, 0, 2, 0);
												tableCreation.insertCell("\b\b\b\b\b", fontHead2, 0, 2, 0);
												tableCreation.insertCell("\b\b\b\b\b", fontHead2, 0, 2, 0);
												tableCreation.insertCell("\b\b\b\b\b", fontHead2, 0, 2, 0);
												tableCreation.insertCell("Signed for & on behalf of/Qatar Insurance Company/Authorised Signatory/Bank/Agent, ", fontHead2, 0, 2, 0);
												tableCreation.insertCell("As per Attached Terms and Conditions.", fontHead2, 0, 2, 0);
												tableCreation.insertCell("Date of Issue: "+(getIncep_date()==null?"":getIncep_date()), fontHead2, 0, 2, 0);
												tableCreation.insertCell("\b\b\b\b\b", fontHead2, 0, 2, 0);
												tableCreation.insertCell("\b\b\b\b\b", fontHead2, 0, 2, 0);
												tableCreation.insertCell("\b\b\b\b\b", fontHead2, 0, 2, 0);
												tableCreation.insertCell("\b\b\b\b\b", fontHead2, 0, 2, 0);
												tableCreation.insertCell("\b\b\b\b\b", fontHead2, 0, 2, 0);
												tableCreation.insertCell("Date of Proposal and Declaration, Signature of the Insured ", fontHead2, 0, 2, 0);
												document.add(tableCreation.getTable());

					  
						}//main_coverage


                                       // updating uploaded file list
						     if( uploaded_filescid.length>0)
							     {
                                               document.newPage();
						                     
												 tableCreation.setTable(1);
									             tableCreation.insertCell("Annexure for Premises "+(x+1)+":", fontHead2, Rectangle.NO_BORDER, 1, 0);
												 tableCreation.setTableSpacingBefore(5f);
												 document.add(tableCreation.getTable());
												
                                                  tableCreation.setTable(3);
												  tableCreation.insertCell(" Coverage Name", fontHead2, Rectangle.BOX, 1, 2);
												  tableCreation.insertCell(" File Name", fontHead2, Rectangle.BOX, 1, 2);
												  tableCreation.insertCell("", fontHead2, Rectangle.NO_BORDER, 1, 2);
												 tableCreation.setTableSpacingBefore(5f);
												 document.add(tableCreation.getTable());

										CusInfo=(String[][])CusDetails.get(x);
                                          
                                      for(int z=0;z<uploaded_filescid.length;z++)
									 {
										          tableCreation.setTable(3);
												  tableCreation.insertCell(uploaded_filescid[z][1], font, Rectangle.BOX, 1, 0);
												  
												 tableCreation1.setTable(1);
												 for(int i=0;i<uploaded_files.length;i++)
								                   {   
													 if(uploaded_filescid[z][0].equalsIgnoreCase(uploaded_files[i][0]))
													 {
											          tableCreation1.insertCell(uploaded_files[i][4], font, Rectangle.BOX, 1, 0);
													 }
													 tbl3=tableCreation1.getTable();
												   }//for uploaded_files.length
                                                   
												    
												   tableCreation.insertCell(tbl3, Rectangle.BOX, 1, 0);
												   tableCreation.insertCell("", font1, Rectangle.NO_BORDER, 1, 2);
												   document.add(tableCreation.getTable());

											 }//for uploaded_filescid.length
							 }//if uploaded_filescid.length

												   //getting updated values
												   String upload_cids[]=new String[4];
												   upload_cids[0]="2";
												   upload_cids[1]="10";
												   upload_cids[2]="9";
												   upload_cids[3]="8";
												   boolean headsts=true;

												  for(int z=0;z<upload_cids.length;z++)
									            {
												   values=sBean.getUpdatedValues(upload_cids[z],(CusInfo[0][0]==null?"":CusInfo[0][0]));
												    if( uploaded_filescid.length<=0 && values.length>0 && headsts )
							                        {
                                                       document.newPage();
						                     
												        tableCreation.setTable(1);
									                    tableCreation.insertCell("Annexure for Premises "+(x+1)+":", fontHead2, Rectangle.NO_BORDER, 1, 0);
												        tableCreation.setTableSpacingBefore(5f);
												        document.add(tableCreation.getTable());
														headsts=false;
													}
												   if( values.length>0 && upload_cids[z].equalsIgnoreCase("2"))
										           {
													   tableCreation.setTable(1);
									                   tableCreation.insertCell("Portable Equipments :", fontHead2, Rectangle.NO_BORDER, 1, 0);
												       tableCreation.setTableSpacingBefore(10f);
												       document.add(tableCreation.getTable()); 

													   tableCreation.setTable(6);
													   tableCreation.insertCell("S.No.", fontHead2, Rectangle.BOX, 1, 2);
												       tableCreation.insertCell("Make / Year", fontHead2, Rectangle.BOX, 1, 2);
													   tableCreation.insertCell("Equipment description", fontHead2, Rectangle.BOX, 2, 2);
													   tableCreation.insertCell("Replacement value", fontHead2, Rectangle.BOX, 2, 2);
													   tableCreation.setTableSpacingBefore(2f);
													    document.add(tableCreation.getTable());
													   for(int s=0;s< values.length;s++)
													   {
														    tableCreation.setTable(6);
														   tableCreation.insertCell(""+(s+1), font, Rectangle.BOX, 1, 2);
														   tableCreation.insertCell(values[s][0], font, Rectangle.BOX, 1, 0);
														   tableCreation.insertCell(values[s][1], font, Rectangle.BOX, 2, 0);
														   tableCreation.insertCell(values[s][2], font, Rectangle.BOX, 2, 0);
														    document.add(tableCreation.getTable());
													   }
												   }else  if( values.length>0 && upload_cids[z].equalsIgnoreCase("10"))
										           {
													   tableCreation.setTable(1);
									                   tableCreation.insertCell("Group Personal Accident :", fontHead2, Rectangle.NO_BORDER, 1, 0);
												       tableCreation.setTableSpacingBefore(10f);
												       document.add(tableCreation.getTable()); 

													   tableCreation.setTable(7);
													   tableCreation.insertCell("S.No.", fontHead2, Rectangle.BOX, 1, 2);
												       tableCreation.insertCell("Name", fontHead2, Rectangle.BOX, 1, 2);
													   tableCreation.insertCell("DOB", fontHead2, Rectangle.BOX, 1,2);
													   tableCreation.insertCell("Designation", fontHead2, Rectangle.BOX, 2, 2);
													   tableCreation.insertCell("Annual salary", fontHead2, Rectangle.BOX, 2, 2);
													   tableCreation.setTableSpacingBefore(2f);
													    document.add(tableCreation.getTable());
													   for(int s=0;s< values.length;s++)
													   {
														    tableCreation.setTable(7);
														   tableCreation.insertCell(""+(s+1), font, Rectangle.BOX, 1, 2);
														   tableCreation.insertCell(values[s][0], font, Rectangle.BOX, 1, 0);
														   tableCreation.insertCell(values[s][1], font, Rectangle.BOX, 1, 0);
														   tableCreation.insertCell(values[s][2], font, Rectangle.BOX, 2, 0);
														   tableCreation.insertCell(values[s][3], font, Rectangle.BOX, 2, 0);
														    document.add(tableCreation.getTable());
													   }
												   }else  if( values.length>0 && upload_cids[z].equalsIgnoreCase("9"))
										           {

													   tableCreation.setTable(1);
									                   tableCreation.insertCell("Travel Baggage :", fontHead2, Rectangle.NO_BORDER, 1, 0);
												       tableCreation.setTableSpacingBefore(10f);
												       document.add(tableCreation.getTable()); 

													   tableCreation.setTable(5);
													   tableCreation.insertCell("S.No.", fontHead2, Rectangle.BOX, 1, 2);
												       tableCreation.insertCell("Name", fontHead2, Rectangle.BOX, 2, 2);
													   tableCreation.insertCell("Designation", fontHead2, Rectangle.BOX, 2, 2);
													  tableCreation.setTableSpacingBefore(2f);
													    document.add(tableCreation.getTable());
													   for(int s=0;s< values.length;s++)
													   {
														    tableCreation.setTable(5);
															tableCreation.insertCell(""+(s+1), font, Rectangle.BOX, 1, 2);
														   tableCreation.insertCell(values[s][0], font, Rectangle.BOX, 2, 0);
														   tableCreation.insertCell(values[s][1], font, Rectangle.BOX, 2, 0);
														 
														    document.add(tableCreation.getTable());
													   }
												   }else  if( values.length>0 && upload_cids[z].equalsIgnoreCase("8"))
										           {
													   tableCreation.setTable(1);
									                   tableCreation.insertCell("Fidelity Guarantee :", fontHead2, Rectangle.NO_BORDER, 1, 0);
												       tableCreation.setTableSpacingBefore(10f);
												       document.add(tableCreation.getTable()); 

													   tableCreation.setTable(5);
													   tableCreation.insertCell("S.No.", fontHead2, Rectangle.BOX, 1, 2);
												        tableCreation.insertCell("Name", fontHead2, Rectangle.BOX, 2, 2);
													   tableCreation.insertCell("Designation", fontHead2, Rectangle.BOX, 2, 2);
													    tableCreation.setTableSpacingBefore(2f);
													    document.add(tableCreation.getTable());
													   for(int s=0;s< values.length;s++)
													   {
														    tableCreation.setTable(5);
															tableCreation.insertCell(""+(s+1), font, Rectangle.BOX, 1, 2);
														   tableCreation.insertCell(values[s][0], font, Rectangle.BOX, 2, 0);
														   tableCreation.insertCell(values[s][1], font, Rectangle.BOX, 2, 0);
														   document.add(tableCreation.getTable());
													   }
												   }

									     }//for uploaded_filescid.length
										 
								
												
						} // for QuoteLength%>      

						
						

                       

			}// id schedule draft

					
		    else if("certificate".equalsIgnoreCase(ID) )
			{
						
						tableCreation.setTable(1);
						tableCreation.insertCell(" ", font4, Rectangle.NO_BORDER, 1, 2);
						tableCreation.setTableSpacingBefore(50f);
						document.add(tableCreation.getTable());


						tableCreation.setTable(1);
						tableCreation.insertCell("CERTIFICATE OF INSURANCE ", fontHead4, Rectangle.NO_BORDER, 1, 2);
						document.add(tableCreation.getTable());

						tableCreation.setTable(1);
						tableCreation.insertCell("OFFICE SHIELD", fontHead4, Rectangle.NO_BORDER, 1, 2);
						document.add(tableCreation.getTable());

						// content of office started here 
                       
					   
					   // customer info updation


                      
                 for(int x=0;x<TotnoQuotenos;x++)
				{

					   tableCreation.setTable(3);
						tableCreation.insertCell("Date :", fontHead2, Rectangle.NO_BORDER, 1, 0);
						tableCreation.insertCell(getSystemDate(), fontHead1, Rectangle.NO_BORDER, 2, 0);
						tableCreation.insertCell("\b\b\b\b\b ", fontHead1, Rectangle.NO_BORDER, 2, 2);
						tableCreation.setTableSpacingBefore(10f);
						document.add(tableCreation.getTable());

					   
					 CusInfo=(String[][])CusDetails.get(x);
                     qnos+=((CusInfo[0][0]==null?"":CusInfo[0][0])+",");
					 total_premium=(String)premium.get(x);
	                  totpremium+=(Integer.parseInt((total_premium==null?"0":total_premium)));
					  if(CusInfo.length>0)
                        {    
						
						 tableCreation.setTable(3);
						tableCreation.insertCell("Address "+(x+1)+" :", fontHead2, Rectangle.NO_BORDER, 1, 0);
						tableCreation.insertCell(((CusInfo[0][8]==null?"":(CusInfo[0][8].equalsIgnoreCase("NOT A FREE ZONE")?(CusInfo[0][2]==null?"":CusInfo[0][2].trim()+","):CusInfo[0][8]+" Authority,"))+(CusInfo[0][13]==null?"":CusInfo[0][13].trim())+","+(CusInfo[0][12]==null?"":CusInfo[0][12].trim())), fontHead1, Rectangle.NO_BORDER, 2, 0);
						tableCreation.insertCell("\b\b\b\b\b ", fontHead1, Rectangle.NO_BORDER, 2, 2);
						document.add(tableCreation.getTable());
						}
			
                  qnos=qnos.substring(0,(qnos.length()-1));
                  	CusInfo=(String[][])CusDetails.get(0);
                    if(CusInfo.length>0)
                        {    
						
						 /*tableCreation.setTable(3);
						tableCreation.insertCell("Address :", fontHead2, Rectangle.NO_BORDER, 1, 0);
						tableCreation.insertCell(((CusInfo[0][8]==null?"":CusInfo[0][8])+" Authority,"+(CusInfo[0][13]==null?"":CusInfo[0][13])+","+(CusInfo[0][12]==null?"":CusInfo[0][12])), fontHead1, Rectangle.NO_BORDER, 2, 0);
						tableCreation.insertCell("\b\b\b\b\b ", fontHead1, Rectangle.NO_BORDER, 2, 2);
						document.add(tableCreation.getTable());*/
                          

    					
						tableCreation.setTable(1);
						tableCreation.insertCell("Dear Sir/ Madam,", fontHead1, Rectangle.NO_BORDER, 2, 0);
						tableCreation.setTableSpacingBefore(10f);
						document.add(tableCreation.getTable());
					
                        
						tableCreation.setTable(3);
						tableCreation.insertCell("Coverage :", fontHead2, Rectangle.NO_BORDER, 1, 0);
						tableCreation.insertCell("Section 1- Contents of your office", fontHead1, Rectangle.NO_BORDER, 3, 0);
						tableCreation.insertCell("\b\b\b\b\b ", fontHead1, Rectangle.NO_BORDER, 2, 2);
						tableCreation.setTableSpacingBefore(10f);
						document.add(tableCreation.getTable());
						
												
						tableCreation.setTable(1);
						tableCreation.insertCell("XYZ Insurance confirms that "+((CusInfo[0][1]==null?"":CusInfo[0][1])+"."+(CusInfo[0][2]==null?"":CusInfo[0][2])+(CusInfo[0][3]==null?"":CusInfo[0][3]))+" is insured with Us under OFFICE SHIELD INSURANCE  as per the following details:", fontHead1, Rectangle.NO_BORDER, 0, 0);
						tableCreation.setTableSpacingBefore(10f);
						document.add(tableCreation.getTable());
						
                        
                        tableCreation.setTable(3);
						tableCreation.insertCell("Policy Number :", fontHead2, Rectangle.NO_BORDER, 1, 0);
						tableCreation.insertCell(policy_number, fontHead1, Rectangle.NO_BORDER, 2, 0);
						tableCreation.insertCell("\b\b\b\b\b ", fontHead1, Rectangle.NO_BORDER, 2, 2);
						tableCreation.setTableSpacingBefore(10f);
						document.add(tableCreation.getTable());
						                        
						
						
						tableCreation.setTable(3); //certificate Insured Location
						 tableCreation.insertCell("Insured Location : ", fontHead2, Rectangle.NO_BORDER, 1, 0);
					   	tableCreation.insertCell(((CusInfo[0][4]==null?"":CusInfo[0][4])+(CusInfo[0][5]==null?"":","+CusInfo[0][5])+(CusInfo[0][18]==null?"":","+CusInfo[0][18])+(CusInfo[0][20]==null?"":","+CusInfo[0][20])+(CusInfo[0][19]==null?"":(CusInfo[0][19].equalsIgnoreCase("select")?"":","+CusInfo[0][19]))), fontHead1, Rectangle.NO_BORDER, 2, 0);
						//tableCreation.insertCell("\b\b\b\b\b ", fontHead1, Rectangle.NO_BORDER, 2, 2);
						document.add(tableCreation.getTable());
						
						tableCreation.setTable(3);
						 tableCreation.insertCell("Period of Insurance :  ", fontHead2, Rectangle.NO_BORDER, 1, 0);
						tableCreation.insertCell(((CusInfo[0][14]==null?"":CusInfo[0][14])+" to "+(CusInfo[0][15]==null?"":CusInfo[0][15])), fontHead1, Rectangle.NO_BORDER, 2, 0);
						tableCreation.insertCell("\b\b\b\b\b ", fontHead1, Rectangle.NO_BORDER, 2, 2);
						document.add(tableCreation.getTable());
                  }// if(CusInfo.length>0)
				

				    createemptyColumn(document,stable,tableCreation);   
				   
                  	CusInfo=(String[][])CusDetails.get(x);
					main_coverage=(String[][])mainCover.get(x);
					sub_coverage=(String[][])subCover.get(x);
                    if(CusInfo.length>0)
                        {    
						 
						  tableCreation.setTable(6);
						 tableCreation.insertCell(("Premises "+(x+1)+" :"), fontHead2, Rectangle.BOX, 1, 0);		tableCreation.insertCell(((CusInfo[0][10]==null?"":CusInfo[0][10])+","+(CusInfo[0][13]==null?"":CusInfo[0][13])+"-"+(CusInfo[0][11]==null?"":CusInfo[0][11])+","+(CusInfo[0][12]==null?"":CusInfo[0][12])), fontHead1, Rectangle.BOX, 3, 0);
						//tableCreation.insertCell("\b\b\b\b\b ", fontHead1, Rectangle.NO_BORDER, 2, 2);
						//document.add(tableCreation.getTable());
                   
				       
					     if(main_coverage.length>0)
							{          
											tableCreation4.setTable(2);		
										for(int i=0;i<main_coverage.length;i++) { 
											   boolean sts=true;
												 //tableCreation.setTable(3);
														
												 if(main_coverage[i][0].equalsIgnoreCase("1") || main_coverage[i][0].equalsIgnoreCase("2") )
												  {   
													  
													  //tableCreation.insertCell(main_coverage[i][1], fontHead, Rectangle.BOX, 0, 0);
													  tableCreation4.insertCell("Sum Insured for "+main_coverage[i][1], font, Rectangle.BOX, 1, 0);
													  tableCreation4.insertCell(main_coverage[i][10].equalsIgnoreCase("Y")?(main_coverage[i][7]==null?"N/A":df.format(Double.parseDouble(main_coverage[i][7]))):"N/A", font, Rectangle.BOX, 1, 0);
													
													  
													  
												  }
												
                                             
                                                     tbl1=tableCreation4.getTable();                         
											         //document.add(tableCreation4.getTable());
											}//for main_coverage.length
											  
											 tableCreation.insertCell(tbl1, Rectangle.BOX, 2, 0);     
											document.add(tableCreation.getTable());		   
                                     
															  
						}//main_coverage
						  
						 
				
                      }// if(CusInfo.length>0)
				 


				         createemptyColumn(document,stable,tableCreation);
                        // total premium 
                        tableCreation.setTable(1);
						tableCreation.insertCell("All Figures mentioned in the Certificate are in AED.", fontHead1, Rectangle.NO_BORDER, 1, 0);
						document.add(tableCreation.getTable());	


				         tableCreation.setTable(6);
						 tableCreation.insertCell("Jurisdiction:  ", fontHead2, Rectangle.NO_BORDER, 1, 0);
						tableCreation.insertCell("United Arab Emirates", fontHead1, Rectangle.NO_BORDER, 4, 0);
						tableCreation.insertCell("    ", fontHead1, Rectangle.NO_BORDER, 1, 0);
						tableCreation.setTableSpacingBefore(10f);
						document.add(tableCreation.getTable());

						tableCreation.setTable(3);
						 tableCreation.insertCell("Subject otherwise to the terms, conditions and limitations of the Policy.  ", fontHead1, Rectangle.NO_BORDER, 3, 0);
						 tableCreation.setTableSpacingBefore(10f);
						 document.add(tableCreation.getTable());

						 tableCreation.setTable(3);
						 tableCreation.insertCell("For XYZ Insurance Company Ltd.", fontHead2,Rectangle.NO_BORDER, 3, 0);
						 tableCreation.setTableSpacingBefore(10f);
						 document.add(tableCreation.getTable());

						 
					       createSymbol(imagepath,userLoginMode, document, stable, tableCreation);

						 	document.newPage();

                
                      // 	document.newPage();


						 // Public Liablity started here 
                       
					     tableCreation.setTable(3);
						tableCreation.insertCell("Date :", fontHead2, Rectangle.NO_BORDER, 1, 0);
						tableCreation.insertCell(getSystemDate(), fontHead1, Rectangle.NO_BORDER, 2, 0);
						tableCreation.insertCell("\b\b\b\b\b ", fontHead1, Rectangle.NO_BORDER, 2, 2);
						document.add(tableCreation.getTable());

					   
					   // customer info updation

                     
					 CusInfo=(String[][])CusDetails.get(x);
                     
					  if(CusInfo.length>0)
                        {    
						tableCreation.setTable(3);
						tableCreation.insertCell("Address "+(x+1)+" :", fontHead2, Rectangle.NO_BORDER, 1, 0);
						tableCreation.insertCell(((CusInfo[0][8]==null?"":(CusInfo[0][8].equalsIgnoreCase("NOT A FREE ZONE")?(CusInfo[0][2]==null?"":CusInfo[0][2].trim()+","):CusInfo[0][8]+" Authority,"))+(CusInfo[0][13]==null?"":CusInfo[0][13].trim())+","+(CusInfo[0][12]==null?"":CusInfo[0][12].trim())), fontHead1, Rectangle.NO_BORDER, 2, 0);
						tableCreation.insertCell("\b\b\b\b\b ", fontHead1, Rectangle.NO_BORDER, 2, 2);
						document.add(tableCreation.getTable());
						
						}
				
                      
                
                  	CusInfo=(String[][])CusDetails.get(x);
                    if(CusInfo.length>0)
                        {    
						
						/* tableCreation.setTable(3);
						tableCreation.insertCell("Address :", fontHead2, Rectangle.NO_BORDER, 1, 0);
						tableCreation.insertCell(((CusInfo[0][8]==null?"":CusInfo[0][8])+" Authority,"+(CusInfo[0][13]==null?"":CusInfo[0][13])+","+(CusInfo[0][12]==null?"":CusInfo[0][12])), fontHead1, Rectangle.NO_BORDER, 2, 0);
						tableCreation.insertCell("\b\b\b\b\b ", fontHead1, Rectangle.NO_BORDER, 2, 2);
						document.add(tableCreation.getTable());*/
                          

    					
						tableCreation.setTable(1);
						tableCreation.insertCell("Dear Sir/ Madam,", fontHead1, Rectangle.NO_BORDER, 2, 0);
						tableCreation.setTableSpacingBefore(10f);
						document.add(tableCreation.getTable());
					
                        tableCreation.setTable(3);
						tableCreation.insertCell("Coverage :", fontHead2, Rectangle.NO_BORDER, 1, 0);
						tableCreation.insertCell("Section 2- Public Liability ( Premises Risk only )   ", fontHead1, Rectangle.NO_BORDER, 3, 0);
						tableCreation.insertCell("\b\b\b\b\b ", fontHead1, Rectangle.NO_BORDER, 2, 2);
						tableCreation.setTableSpacingBefore(10f);
						document.add(tableCreation.getTable());
						
						tableCreation.setTable(1);
						tableCreation.insertCell("XYZ Insurance confirms that "+((CusInfo[0][1]==null?"":CusInfo[0][1])+"."+(CusInfo[0][2]==null?"":CusInfo[0][2])+(CusInfo[0][3]==null?"":CusInfo[0][3]))+" is insured with Us under OFFICE SHIELD INSURANCE  as per the following details:", fontHead1, Rectangle.NO_BORDER, 0, 0);
						tableCreation.setTableSpacingBefore(10f);
						document.add(tableCreation.getTable());
						
                        
                        tableCreation.setTable(3);
						tableCreation.insertCell("Policy Number :", fontHead2, Rectangle.NO_BORDER, 1, 0);
						tableCreation.insertCell(policy_number, fontHead1, Rectangle.NO_BORDER, 2, 0);
						tableCreation.insertCell("\b\b\b\b\b ", fontHead1, Rectangle.NO_BORDER, 2, 2);
						tableCreation.setTableSpacingBefore(10f);
						document.add(tableCreation.getTable());
						                        
						
						

						tableCreation.setTable(3);
						 tableCreation.insertCell("Insured Location : ", fontHead2, Rectangle.NO_BORDER, 1, 0);
						//tableCreation.insertCell(((CusInfo[0][4]==null?"":CusInfo[0][4])+(CusInfo[0][5]==null?"":CusInfo[0][5])), fontHead1, Rectangle.NO_BORDER, 2, 0);
						tableCreation.insertCell(((CusInfo[0][4]==null?"":CusInfo[0][4])+(CusInfo[0][5]==null?"":","+CusInfo[0][5])+(CusInfo[0][18]==null?"":","+CusInfo[0][18])+(CusInfo[0][20]==null?"":","+CusInfo[0][20])+(CusInfo[0][19]==null?"":(CusInfo[0][19].equalsIgnoreCase("select")?"":","+CusInfo[0][19]))), fontHead1, Rectangle.NO_BORDER, 2, 0);
						
						tableCreation.insertCell("\b\b\b\b\b ", fontHead1, Rectangle.NO_BORDER, 2, 2);
						document.add(tableCreation.getTable());
						
						tableCreation.setTable(3);
						 tableCreation.insertCell("Period of Insurance :  ", fontHead2, Rectangle.NO_BORDER, 1, 0);
						tableCreation.insertCell(((CusInfo[0][14]==null?"":CusInfo[0][14])+" to "+(CusInfo[0][15]==null?"":CusInfo[0][15])), fontHead1, Rectangle.NO_BORDER, 2, 0);
						tableCreation.insertCell("\b\b\b\b\b ", fontHead1, Rectangle.NO_BORDER, 2, 2);
						document.add(tableCreation.getTable());
                  }// if(CusInfo.length>0)
				

				    createemptyColumn(document,stable,tableCreation);   
				    
                  	CusInfo=(String[][])CusDetails.get(x);
					main_coverage=(String[][])mainCover.get(x);
					sub_coverage=(String[][])subCover.get(x);
                    if(CusInfo.length>0)
                        {    
						 
						  tableCreation.setTable(6);
						 tableCreation.insertCell(("Premises "+(x+1)+" :"), fontHead2, Rectangle.BOX, 1, 0);		tableCreation.insertCell(((CusInfo[0][10]==null?"":CusInfo[0][10])+","+(CusInfo[0][13]==null?"":CusInfo[0][13])+"-"+(CusInfo[0][11]==null?"":CusInfo[0][11])+","+(CusInfo[0][12]==null?"":CusInfo[0][12])), fontHead1, Rectangle.BOX, 3, 0);
						//tableCreation.insertCell("\b\b\b\b\b ", fontHead1, Rectangle.NO_BORDER, 2, 2);
						//document.add(tableCreation.getTable());
                   
				       
					     if(main_coverage.length>0)
							{         
														
										for(int i=0;i<main_coverage.length;i++) { 
											   boolean sts=true;
												// tableCreation.setTable(3);
															
												 if(main_coverage[i][0].equalsIgnoreCase("3"))
												  {   
													  
													  //tableCreation.insertCell(main_coverage[i][1], fontHead, Rectangle.BOX, 0, 0);
													  tableCreation.insertCell("Limit of Indemnity:", font, Rectangle.BOX, 1, 0);
													  tableCreation.insertCell((main_coverage[i][7]==null?"N/A":df.format(Double.parseDouble(main_coverage[i][7]))), font, Rectangle.BOX, 1, 0);
													   
												  }
												

                                                                                      
											
											}//for main_coverage.length

															  
						}//main_coverage
						  document.add(tableCreation.getTable());
				
                      }// if(CusInfo.length>0)
				 

				        createemptyColumn(document,stable,tableCreation);
                        
                        tableCreation.setTable(1);
						tableCreation.insertCell("All Figures mentioned in the Certificate are in AED.", fontHead1, Rectangle.NO_BORDER, 1, 0);
						document.add(tableCreation.getTable());	

				         tableCreation.setTable(6);
						 tableCreation.insertCell("Jurisdiction:  ", fontHead2, Rectangle.NO_BORDER, 1, 0);
						tableCreation.insertCell("United Arab Emirates", fontHead1, Rectangle.NO_BORDER, 4, 0);
						tableCreation.insertCell("    ", fontHead1, Rectangle.NO_BORDER, 1, 0);
						tableCreation.setTableSpacingBefore(10f);
						document.add(tableCreation.getTable());

						tableCreation.setTable(6);
						 tableCreation.insertCell("Territory:  ", fontHead2, Rectangle.NO_BORDER, 1, 0);
						tableCreation.insertCell("United Arab Emirates extended to World Wide cover in respect of non-manual Business trips", fontHead1, Rectangle.NO_BORDER, 5, 0);
						//tableCreation.insertCell("    ", fontHead1, Rectangle.NO_BORDER, 1, 0);
						tableCreation.setTableSpacingBefore(10f);
						document.add(tableCreation.getTable());

						tableCreation.setTable(3);
						 tableCreation.insertCell("Subject otherwise to the terms, conditions and limitations of the Policy.  ", fontHead1, Rectangle.NO_BORDER, 3, 0);
						 tableCreation.setTableSpacingBefore(10f);
						 document.add(tableCreation.getTable());

						 tableCreation.setTable(3);
						 tableCreation.insertCell("For XYZ Insurance Company Ltd.", fontHead2,Rectangle.NO_BORDER, 3, 0);
						 tableCreation.setTableSpacingBefore(10f);
						 document.add(tableCreation.getTable());


                          createSymbol(imagepath,userLoginMode, document, stable, tableCreation);
						 

						 	document.newPage();
 

						 //Workmen's Compensation Insurance
                       
					     tableCreation.setTable(3);
						tableCreation.insertCell("Date :", fontHead2, Rectangle.NO_BORDER, 1, 0);
						tableCreation.insertCell(getSystemDate(), fontHead1, Rectangle.NO_BORDER, 2, 0);
						tableCreation.insertCell("\b\b\b\b\b ", fontHead1, Rectangle.NO_BORDER, 2, 2);
						document.add(tableCreation.getTable());

					   
					   // customer info updation
                   
					 CusInfo=(String[][])CusDetails.get(x);
                     
					  if(CusInfo.length>0)
                        {    
						
						tableCreation.setTable(3);
						tableCreation.insertCell("Address "+(x+1)+" :", fontHead2, Rectangle.NO_BORDER, 1, 0);
						tableCreation.insertCell(((CusInfo[0][8]==null?"":(CusInfo[0][8].equalsIgnoreCase("NOT A FREE ZONE")?(CusInfo[0][2]==null?"":CusInfo[0][2].trim()+","):CusInfo[0][8]+" Authority,"))+(CusInfo[0][13]==null?"":CusInfo[0][13].trim())+","+(CusInfo[0][12]==null?"":CusInfo[0][12].trim())), fontHead1, Rectangle.NO_BORDER, 2, 0);
						tableCreation.insertCell("\b\b\b\b\b ", fontHead1, Rectangle.NO_BORDER, 2, 2);
						document.add(tableCreation.getTable());
						}
				

                      
                
                  	CusInfo=(String[][])CusDetails.get(x);
                    if(CusInfo.length>0)
                        {    
						
						/* tableCreation.setTable(3);
						tableCreation.insertCell("Address :", fontHead2, Rectangle.NO_BORDER, 1, 0);
						tableCreation.insertCell(((CusInfo[0][8]==null?"":CusInfo[0][8])+" Authority,"+(CusInfo[0][13]==null?"":CusInfo[0][13])+","+(CusInfo[0][12]==null?"":CusInfo[0][12])), fontHead1, Rectangle.NO_BORDER, 2, 0);
						tableCreation.insertCell("\b\b\b\b\b ", fontHead1, Rectangle.NO_BORDER, 2, 2);
						document.add(tableCreation.getTable());*/
                          

    					
						tableCreation.setTable(1);
						tableCreation.insertCell("Dear Sir/ Madam,", fontHead1, Rectangle.NO_BORDER, 2, 0);
						tableCreation.setTableSpacingBefore(10f);
						document.add(tableCreation.getTable());
					
                        tableCreation.setTable(3);
						tableCreation.insertCell("Coverage :", fontHead2, Rectangle.NO_BORDER, 1, 0);
						tableCreation.insertCell("Section 3- Workmen's Compensation Insurance", fontHead1, Rectangle.NO_BORDER, 3, 0);
						tableCreation.insertCell("\b\b\b\b\b ", fontHead1, Rectangle.NO_BORDER, 2, 2);
						tableCreation.setTableSpacingBefore(10f);
						document.add(tableCreation.getTable());
						
						tableCreation.setTable(1);
						tableCreation.insertCell("XYZ Insurance confirms that "+((CusInfo[0][1]==null?"":CusInfo[0][1])+"."+(CusInfo[0][2]==null?"":CusInfo[0][2])+(CusInfo[0][3]==null?"":CusInfo[0][3]))+" is insured with Us under OFFICE SHIELD INSURANCE  as per the following details:", fontHead1, Rectangle.NO_BORDER, 0, 0);
						tableCreation.setTableSpacingBefore(10f);
						document.add(tableCreation.getTable());
						
                        
                        tableCreation.setTable(3);
						tableCreation.insertCell("Policy Number :", fontHead2, Rectangle.NO_BORDER, 1, 0);
						tableCreation.insertCell(policy_number, fontHead1, Rectangle.NO_BORDER, 2, 0);
						tableCreation.insertCell("\b\b\b\b\b ", fontHead1, Rectangle.NO_BORDER, 2, 2);
						tableCreation.setTableSpacingBefore(10f);
						document.add(tableCreation.getTable());
						                        
						
						
						tableCreation.setTable(3);
						 tableCreation.insertCell("Insured Location : ", fontHead2, Rectangle.NO_BORDER, 1, 0);
						//tableCreation.insertCell(((CusInfo[0][4]==null?"":CusInfo[0][4])+(CusInfo[0][5]==null?"":CusInfo[0][5])), fontHead1, Rectangle.NO_BORDER, 2, 0);
						tableCreation.insertCell(((CusInfo[0][4]==null?"":CusInfo[0][4])+(CusInfo[0][5]==null?"":","+CusInfo[0][5])+(CusInfo[0][18]==null?"":","+CusInfo[0][18])+(CusInfo[0][20]==null?"":","+CusInfo[0][20])+(CusInfo[0][19]==null?"":(CusInfo[0][19].equalsIgnoreCase("select")?"":","+CusInfo[0][19]))), fontHead1, Rectangle.NO_BORDER, 2, 0);
						tableCreation.insertCell("\b\b\b\b\b ", fontHead1, Rectangle.NO_BORDER, 2, 2);
						document.add(tableCreation.getTable());
						
						tableCreation.setTable(3);
						 tableCreation.insertCell("Period of Insurance :  ", fontHead2, Rectangle.NO_BORDER, 1, 0);
						tableCreation.insertCell(((CusInfo[0][14]==null?"":CusInfo[0][14])+" to "+(CusInfo[0][15]==null?"":CusInfo[0][15])), fontHead1, Rectangle.NO_BORDER, 2, 0);
						tableCreation.insertCell("\b\b\b\b\b ", fontHead1, Rectangle.NO_BORDER, 2, 2);
						document.add(tableCreation.getTable());
                  }// if(CusInfo.length>0)
				

				    createemptyColumn(document,stable,tableCreation);   
				   
                  	CusInfo=(String[][])CusDetails.get(x);
					main_coverage=(String[][])mainCover.get(x);
					sub_coverage=(String[][])subCover.get(x);
                    if(CusInfo.length>0)
                        {    
						 
						  tableCreation.setTable(6);
						 tableCreation.insertCell(("Premises "+(x+1)+" :"), fontHead2, Rectangle.BOX, 1, 0);		tableCreation.insertCell(((CusInfo[0][10]==null?"":CusInfo[0][10])+","+(CusInfo[0][13]==null?"":CusInfo[0][13])+"-"+(CusInfo[0][11]==null?"":CusInfo[0][11])+","+(CusInfo[0][12]==null?"":CusInfo[0][12])), fontHead1, Rectangle.BOX, 2, 0);
						//tableCreation.insertCell("\b\b\b\b\b ", fontHead1, Rectangle.NO_BORDER, 2, 2);
						//document.add(tableCreation.getTable());
                   
				       
					     if(main_coverage.length>0)
							{         
														
										for(int i=0;i<main_coverage.length;i++) { 
											   boolean sts=true;
												// tableCreation.setTable(3);
															
												 if(main_coverage[i][0].equalsIgnoreCase("4"))
												  {   
													  
													  //tableCreation.insertCell(main_coverage[i][1], fontHead, Rectangle.BOX, 0, 0);
													  tableCreation.insertCell("Cover:"+(main_coverage[i][7]==null?"N/A":df.format(Double.parseDouble(main_coverage[i][7]))), font, Rectangle.BOX, 1, 0);
													  tableCreation.insertCell("As per UAE Federal Labour Law No. (8) of 1980, Chapter- 8; Common Law (Sharia) of UAE and subsequent amendments as at inception date of this Insurance.", font, Rectangle.BOX, 2, 0);
												  }
											}//for main_coverage.length
						}//main_coverage
						  document.add(tableCreation.getTable());
                      }// if(CusInfo.length>0)
				

				        createemptyColumn(document,stable,tableCreation);
                        
                        tableCreation.setTable(1);
						tableCreation.insertCell("All Figures mentioned in the Certificate are in AED.", fontHead1, Rectangle.NO_BORDER, 1, 0);
						document.add(tableCreation.getTable());	

				         tableCreation.setTable(6);
						 tableCreation.insertCell("Jurisdiction:  ", fontHead2, Rectangle.NO_BORDER, 1, 0);
						tableCreation.insertCell("United Arab Emirates", fontHead1, Rectangle.NO_BORDER, 4, 0);
						tableCreation.insertCell("    ", fontHead1, Rectangle.NO_BORDER, 1, 0);
						tableCreation.setTableSpacingBefore(10f);
						document.add(tableCreation.getTable());

						tableCreation.setTable(6);
						 tableCreation.insertCell("Territory:  ", fontHead2, Rectangle.NO_BORDER, 1, 0);
						tableCreation.insertCell("United Arab Emirates extended to World Wide cover in respect of non-manual Business trips", fontHead1, Rectangle.NO_BORDER, 5, 0);
						//tableCreation.insertCell("    ", fontHead1, Rectangle.NO_BORDER, 1, 0);
						tableCreation.setTableSpacingBefore(10f);
						document.add(tableCreation.getTable());

						tableCreation.setTable(3);
						 tableCreation.insertCell("Subject otherwise to the terms, conditions and limitations of the Policy.  ", fontHead1, Rectangle.NO_BORDER, 3, 0);
						 tableCreation.setTableSpacingBefore(10f);
						 document.add(tableCreation.getTable());

						 tableCreation.setTable(3);
						 tableCreation.insertCell("For XYZ Insurance Company Ltd.", fontHead2,Rectangle.NO_BORDER, 3, 0);
						 tableCreation.setTableSpacingBefore(10f);
						 document.add(tableCreation.getTable());

                          createSymbol(imagepath,userLoginMode, document, stable, tableCreation);
						 document.newPage();
  

						 //other Insurance
                       
					    tableCreation.setTable(3);
						tableCreation.insertCell("Date :", fontHead2, Rectangle.NO_BORDER, 1, 0);
						tableCreation.insertCell(getSystemDate(), fontHead1, Rectangle.NO_BORDER, 2, 0);
						tableCreation.insertCell("\b\b\b\b\b ", fontHead1, Rectangle.NO_BORDER, 2, 2);
						document.add(tableCreation.getTable());

					   
					   // customer info updation
                     
					 CusInfo=(String[][])CusDetails.get(x);
                     
					  if(CusInfo.length>0)
                        {    
						
						tableCreation.setTable(3);
						tableCreation.insertCell("Address "+(x+1)+" :", fontHead2, Rectangle.NO_BORDER, 1, 0);
						tableCreation.insertCell(((CusInfo[0][8]==null?"":(CusInfo[0][8].equalsIgnoreCase("NOT A FREE ZONE")?(CusInfo[0][2]==null?"":CusInfo[0][2].trim()+","):CusInfo[0][8]+" Authority,"))+(CusInfo[0][13]==null?"":CusInfo[0][13].trim())+","+(CusInfo[0][12]==null?"":CusInfo[0][12].trim())), fontHead1, Rectangle.NO_BORDER, 2, 0);
						tableCreation.insertCell("\b\b\b\b\b ", fontHead1, Rectangle.NO_BORDER, 2, 2);
						document.add(tableCreation.getTable());
						}
				

                      
                
                  	CusInfo=(String[][])CusDetails.get(x);
                    if(CusInfo.length>0)
                        {    
						
						 /*tableCreation.setTable(3);
						tableCreation.insertCell("Address :", fontHead2, Rectangle.NO_BORDER, 1, 0);
						tableCreation.insertCell(((CusInfo[0][8]==null?"":CusInfo[0][8])+" Authority,"+(CusInfo[0][13]==null?"":CusInfo[0][13])+","+(CusInfo[0][12]==null?"":CusInfo[0][12])), fontHead1, Rectangle.NO_BORDER, 2, 0);
						tableCreation.insertCell("\b\b\b\b\b ", fontHead1, Rectangle.NO_BORDER, 2, 2);
						document.add(tableCreation.getTable());*/
                          

    					
						tableCreation.setTable(1);
						tableCreation.insertCell("Dear Sir/ Madam,", fontHead1, Rectangle.NO_BORDER, 2, 0);
						tableCreation.setTableSpacingBefore(10f);
						document.add(tableCreation.getTable());
					
                        tableCreation.setTable(3);
						tableCreation.insertCell("Coverage :", fontHead2, Rectangle.NO_BORDER, 1, 0);
						tableCreation.insertCell("As mentioned below ", fontHead1, Rectangle.NO_BORDER, 2, 0);
						tableCreation.insertCell("\b\b\b\b\b ", fontHead1, Rectangle.NO_BORDER, 2, 2);
						tableCreation.setTableSpacingBefore(10f);
						document.add(tableCreation.getTable());
						
						tableCreation.setTable(1);
						tableCreation.insertCell("XYZ Insurance confirms that "+((CusInfo[0][1]==null?"":CusInfo[0][1])+"."+(CusInfo[0][2]==null?"":CusInfo[0][2])+(CusInfo[0][3]==null?"":CusInfo[0][3]))+" is insured with Us under OFFICE SHIELD INSURANCE  as per the following details:", fontHead1, Rectangle.NO_BORDER, 0, 0);
						tableCreation.setTableSpacingBefore(10f);
						document.add(tableCreation.getTable());
						
                        
                        tableCreation.setTable(3);
						tableCreation.insertCell("Policy Number :", fontHead2, Rectangle.NO_BORDER, 1, 0);
						tableCreation.insertCell(policy_number, fontHead1, Rectangle.NO_BORDER, 2, 0);
						tableCreation.insertCell("\b\b\b\b\b ", fontHead1, Rectangle.NO_BORDER, 2, 2);
						tableCreation.setTableSpacingBefore(10f);
						document.add(tableCreation.getTable());
						                        
						
						tableCreation.setTable(3);
						tableCreation.insertCell("Insured Location : ", fontHead2, Rectangle.NO_BORDER, 1, 0);
						//tableCreation.insertCell(((CusInfo[0][4]==null?"":CusInfo[0][4])+(CusInfo[0][5]==null?"":CusInfo[0][5])), fontHead1, Rectangle.NO_BORDER, 2, 0);
						tableCreation.insertCell(((CusInfo[0][4]==null?"":CusInfo[0][4])+(CusInfo[0][5]==null?"":","+CusInfo[0][5])+(CusInfo[0][18]==null?"":","+CusInfo[0][18])+(CusInfo[0][20]==null?"":","+CusInfo[0][20])+(CusInfo[0][19]==null?"":(CusInfo[0][19].equalsIgnoreCase("select")?"":","+CusInfo[0][19]))), fontHead1, Rectangle.NO_BORDER, 2, 0);
						tableCreation.insertCell("\b\b\b\b\b ", fontHead1, Rectangle.NO_BORDER, 2, 2);
						document.add(tableCreation.getTable());
						
						tableCreation.setTable(3);
						 tableCreation.insertCell("Period of Insurance :  ", fontHead2, Rectangle.NO_BORDER, 1, 0);
						tableCreation.insertCell(((CusInfo[0][14]==null?"":CusInfo[0][14])+" to "+(CusInfo[0][15]==null?"":CusInfo[0][15])), fontHead1, Rectangle.NO_BORDER, 2, 0);
						tableCreation.insertCell("\b\b\b\b\b ", fontHead1, Rectangle.NO_BORDER, 2, 2);
						document.add(tableCreation.getTable());
                  }// if(CusInfo.length>0)
				

				    createemptyColumn(document,stable,tableCreation);   
				   
                  	CusInfo=(String[][])CusDetails.get(x);
					main_coverage=(String[][])mainCover.get(x);
					sub_coverage=(String[][])subCover.get(x);
                    if(CusInfo.length>0)
                        {    
						 
						  tableCreation5.setTable(9);
						 tableCreation5.insertCell(("Premises "+(x+1)+" :"), fontHead2, Rectangle.BOX, 1, 0);		tableCreation5.insertCell(((CusInfo[0][10]==null?"":CusInfo[0][10])+","+(CusInfo[0][13]==null?"":CusInfo[0][13])+"-"+(CusInfo[0][11]==null?"":CusInfo[0][11])+","+(CusInfo[0][12]==null?"":CusInfo[0][12])), fontHead1, Rectangle.BOX, 1, 0);
						//tableCreation.insertCell("\b\b\b\b\b ", fontHead1, Rectangle.NO_BORDER, 2, 2);
						//document.add(tableCreation.getTable());
                         
				        createemptyColumn(document,stable,tableCreation); 


							if(main_coverage.length>0)
							{         
							
                                    String NoofEmp="",GpaOpt="";TreeMap tm =null;boolean gpaheaderopt=true;
								       
									   tableCreation3.setTable(4);
										for(int i=0;i<main_coverage.length;i++) { 
											tableCreation2.setTable(7);
											tableCreation4.setTable(2);
											   boolean sts=false;
												 tableCreation1.setTable(5);
										if(main_coverage[i][10].equalsIgnoreCase("Y") && (",5,,1,,2,,3,,4,").indexOf (","+main_coverage[i][0]+",") == -1)
											  {   
															
												 if(main_coverage[i][5].equalsIgnoreCase("Y")   && !main_coverage[i][0].equalsIgnoreCase("9"))
												  {   
													  tm = new TreeMap(); 
													 if(main_coverage[i][0].equalsIgnoreCase("8") )
													  {
														 tableCreation1.insertCell("Sum Insured", font, Rectangle.BOX, 2, 0);
														 tableCreation1.insertCell("10,000 in the annual aggregate", font, Rectangle.BOX, 3, 0);
													  }
													 for(int j=0;j<sub_coverage.length;j++)
															  { 
																 if(main_coverage[i][0].equals(sub_coverage[j][1])  && !sub_coverage[j][0].equalsIgnoreCase("26"))
																  {
																	
																	  if(main_coverage[i][0].equalsIgnoreCase("10") )
																	  {
                                                                         //GpaOpt+=sub_coverage[j][2]+"   -   "+(sub_coverage[j][9]==null?"N/A":(sub_coverage[j][9].equalsIgnoreCase("null")?"N/A":sub_coverage[j][9]))+"\n";
																		 if(gpaheaderopt)
																		  {
																		 tableCreation4.insertCell("No. of employees covered\n", font, Rectangle.BOX, 2, 2);
																		 gpaheaderopt=false;
																		  }
																		 tableCreation4.insertCell("\n "+(sub_coverage[j][9]==null?"N/A":(sub_coverage[j][9].equalsIgnoreCase("null")?"N/A":sub_coverage[j][9])), font, Rectangle.BOX, 2, 2);
																	  }else if(main_coverage[i][0].equalsIgnoreCase("8") )
																	  {
	                                                    			        if(sub_coverage[j][0].equalsIgnoreCase("18") )NoofEmp=(sub_coverage[j][9]==null?"N/A":(sub_coverage[j][9].equalsIgnoreCase("null")?"N/A":sub_coverage[j][9]));
																	  }else
																	  {
                                                                      
																	 
																	 if(sub_coverage[j][6].equalsIgnoreCase("Display")) 
																	   tm.put(new Integer(sub_coverage[j][7]),(sub_coverage[j][2]+"~"+(sub_coverage[j][3]==null?"N/A":(sub_coverage[j][3].equalsIgnoreCase("null")?"N/A":sub_coverage[j][3])))); 	
																	 else
																		 tm.put(new Integer(sub_coverage[j][7]),(sub_coverage[j][2]+"~"+(sub_coverage[j][9]==null?"N/A":(sub_coverage[j][9].equalsIgnoreCase("null")?"N/A":sub_coverage[j][9])))); 
																	  }

																	  
																  }//main_coverage[i][0].equals(sub_coverage[j][1])
																	
															  }// for int j



													 if(main_coverage[i][0].equalsIgnoreCase("10"))
												       {
														 tableCreation1.insertCell(" \n\nOption Chosen\n", font, Rectangle.BOX, 3, 2);
														 tableCreation1.insertCell(" \n\nAccumulation Limit\n", font, Rectangle.BOX, 2, 2);
														 tableCreation1.insertCell("24 months salary upto 100,000/- per person & 1,000,000 in the aggregate", font, Rectangle.BOX, 3, 0);
														 tableCreation1.insertCell("Dhs. 300,000", font, Rectangle.BOX, 2, 0);
														 tableCreation1.insertCell("24 months salary upto 250,000/- per person & 2,500,000 per occurrence", font, Rectangle.BOX, 3, 0);
												         tableCreation1.insertCell("Dhs. 500,000", font, Rectangle.BOX, 2, 0);
														 tableCreation1.insertCell("24 months salary upto 500,000/- per person & 5,000,000 per occurrence", font, Rectangle.BOX, 3, 0);
												         tableCreation1.insertCell("Dhs. 1,000,000", font, Rectangle.BOX, 2, 0);
                                                        }

												    // Get a set of the entries 
													Set set = tm.entrySet(); 
													// Get an iterator 
													Iterator itr = set.iterator(); 
													// Display elements 
													System.out.print("Its start here...................................................................: "); 
													while(itr.hasNext()) { 
													Map.Entry me = (Map.Entry)itr.next(); 
													System.out.print(me.getKey() + ": "); 
													System.out.println(me.getValue());
													String temp_val=(String)me.getValue();
													StringTokenizer st=new StringTokenizer(temp_val,"~");
													while(st.hasMoreTokens())
														{
														
														tableCreation1.insertCell(st.nextToken(), font, Rectangle.BOX, 2, 0);
														tableCreation1.insertCell(st.nextToken(), font, Rectangle.BOX, 3, 0);

														}

													} 
                                                     System.out.print("Its end here...................................................................: "); 

												  innertable2 = tableCreation1.getTable();
                                                   tbl4=tableCreation4.getTable();
												 
												 tableCreation2.insertCell("\b\b\b"+(main_coverage[i][14]==null?"":main_coverage[i][14]), fontHead, Rectangle.BOX, 1, 0);
												tableCreation2.insertCell(main_coverage[i][3], fontHead, Rectangle.BOX, 1, 0);

												if(main_coverage[i][0].equalsIgnoreCase("10"))
												{ tableCreation2.insertCell(tbl4, Rectangle.BOX, 1, 0);
												tableCreation2.insertCell(innertable2, Rectangle.BOX, 4, 0);}
												else
												    tableCreation2.insertCell(innertable2, Rectangle.BOX, 5, 0);
												//tbl1=tableCreation2.getTable();
												//document.add(tableCreation.getTable());

												  }  else
												  {
												
												tableCreation2.insertCell("\b\b\b"+(main_coverage[i][14]==null?"":main_coverage[i][14]), fontHead, Rectangle.BOX, 1, 0);
													   tableCreation2.insertCell(main_coverage[i][3], fontHead, Rectangle.BOX, 1, 0);

													    if(main_coverage[i][0].equalsIgnoreCase("3"))
														  tableCreation2.insertCell("Limit of indemnity", font, Rectangle.BOX, 2, 0);
														else if (main_coverage[i][0].equalsIgnoreCase("4"))
														  tableCreation2.insertCell("Estimated Wages", font, Rectangle.BOX, 2, 0);
															else
													       tableCreation2.insertCell("Sum Insured", font, Rectangle.BOX, 2, 0);

													  if( main_coverage[i][0].equalsIgnoreCase("9"))
     												    tableCreation2.insertCell("10,000 in the annual aggregate", font, Rectangle.BOX, 3, 0);
														  else
												     tableCreation2.insertCell((main_coverage[i][7]==null?"N/A":df.format(Double.parseDouble(main_coverage[i][7]))), font, Rectangle.BOX, 3, 0);
														 
														 // document.add(tableCreation.getTable());
												  }
												    tbl1=tableCreation2.getTable();
											
											  tableCreation3.insertCell(tbl1, Rectangle.BOX, 4, 0);
                                             
     
											  }//if(main_coverage[i][10].Y  
											 
											
											
											
											  
											}//for main_coverage.length
											
											 tableCreation5.insertCell(tableCreation3.getTable(), Rectangle.BOX, 7, 0);
											document.add(tableCreation5.getTable());
				  
						}//main_coverage
					    
						  
				        	               
                      }// if(CusInfo.length>0)
                       tableCreation.setTable(1);
					   createemptyColumn(document,stable,tableCreation);  
				 

				        createemptyColumn(document,stable,tableCreation);
                        
                        tableCreation.setTable(1);
						tableCreation.insertCell("All Figures mentioned in the Certificate are in AED.", fontHead1, Rectangle.NO_BORDER, 1, 0);
						document.add(tableCreation.getTable());	

				         tableCreation.setTable(6);
						 tableCreation.insertCell("Jurisdiction:  ", fontHead2, Rectangle.NO_BORDER, 1, 0);
						tableCreation.insertCell("United Arab Emirates", fontHead1, Rectangle.NO_BORDER, 4, 0);
						tableCreation.insertCell("    ", fontHead1, Rectangle.NO_BORDER, 1, 0);
						tableCreation.setTableSpacingBefore(10f);
						document.add(tableCreation.getTable());

						tableCreation.setTable(6);
						 tableCreation.insertCell("Territory:  ", fontHead2, Rectangle.NO_BORDER, 1, 0);
						tableCreation.insertCell("United Arab Emirates extended to World Wide cover in respect of non-manual Business trips", fontHead1, Rectangle.NO_BORDER, 5, 0);
						//tableCreation.insertCell("    ", fontHead1, Rectangle.NO_BORDER, 1, 0);
						tableCreation.setTableSpacingBefore(10f);
						document.add(tableCreation.getTable());

						tableCreation.setTable(3);
						 tableCreation.insertCell("Subject otherwise to the terms, conditions and limitations of the Policy.  ", fontHead1, Rectangle.NO_BORDER, 3, 0);
						 tableCreation.setTableSpacingBefore(10f);
						 document.add(tableCreation.getTable());

						 tableCreation.setTable(3);
						 tableCreation.insertCell("For XYZ Insurance Company Ltd.", fontHead2,Rectangle.NO_BORDER, 3, 0);
						 tableCreation.setTableSpacingBefore(10f);
						 document.add(tableCreation.getTable());

						  createSymbol(imagepath,userLoginMode, document, stable, tableCreation);

            if(x!=TotnoQuotenos-1)
            document.newPage();

        	} //contents for end

		}//if certificate

			 	document.close();
				
			}
			catch(DocumentException de) 
			{
				de.printStackTrace();
	            System.err.println("document: " + de.getMessage());
			}
			catch(Exception e) 
			{
				e.printStackTrace();
	            System.err.println("document: " + e.getMessage());
			}
		//	response.sendRedirect("../PDFFile/PremiumSummary.pdf");
	}


 // if live mode adding signature image
   public void createSymbol(String imagepath,String userLoginMode, Document document, PdfPTable stable,PdfPTableCreation tableCreation)
	{
						 try{
						tableCreation.setTable(8);
						Font font3 = new Font(Font.TIMES_ROMAN, 10, Font.BOLD);						
                        if(userLoginMode.equalsIgnoreCase("Live")||userLoginMode.equalsIgnoreCase("Test"))
				         {
						tableCreation.insertCell("                   ", font3, Rectangle.NO_BORDER, 1, 2);
						Image jpg_new = Image.getInstance(imagepath+"/"+SignImg);
						jpg_new.setAbsolutePosition(50, 250); 
						jpg_new.scaleAbsolute(70, 54); 
						tableCreation.insertCell(jpg_new, 0, 0, 1);
						}

						 Image jpg = Image.getInstance(imagepath+"/"+StampImg);
						jpg.setAbsolutePosition(50, 250); 
						jpg.scaleAbsolute(60, 64); 
						if(userLoginMode.equalsIgnoreCase("Live") ||userLoginMode.equalsIgnoreCase("Test"))
						tableCreation.insertCell(jpg, 0, 0, 0);
						else
                        tableCreation.insertCell(jpg, 0, 0, 2);

							tableCreation.insertCell(" ", fontHead1, Rectangle.NO_BORDER, 5, 0);
                          tableCreation.setTableSpacingBefore(10f);
						 document.add(tableCreation.getTable());

						 tableCreation.setTable(2);
						 tableCreation.insertCell("AUTHORISED SIGNATORY", fontBoldText, Rectangle.NO_BORDER, 1, 2);
						tableCreation.insertCell(" ", font3, Rectangle.NO_BORDER, 1, 2);
						tableCreation.setTableSpacingBefore(10f);
						document.add(tableCreation.getTable());	
						  }
	                         catch(Exception e) 
			              {
				
	                          System.err.println("document: " + e.getMessage());
			                  }
	}

   public void createemptyColumn(Document document, PdfPTable stable,PdfPTableCreation tableCreation)
	{
	   try{
	            Font font3 = new Font(Font.TIMES_ROMAN, 10, Font.BOLD);
	            tableCreation.setTable(3);
				tableCreation.insertCell("", font3, Rectangle.NO_BORDER, 3, 2);
				tableCreation.setTableSpacingBefore(15f);
				stable = tableCreation.getTable();
				document.add(stable);
	   }
	   catch(Exception e) 
			{
				
	            System.err.println("document: " + e.getMessage());
			}
	}

	 public void createemptyColumn_new(Document document, PdfPTable stable,PdfPTableCreation tableCreation)
	{
	   try{
	            Font font3 = new Font(Font.TIMES_ROMAN, 10, Font.BOLD);
	            tableCreation.setTable(3);
				tableCreation.insertCell("", font3, Rectangle.NO_BORDER, 3, 2);
				tableCreation.setTableSpacingBefore(2f);
				stable = tableCreation.getTable();
				document.add(stable);
	   }
	   catch(Exception e) 
			{
				
	            System.err.println("document: " + e.getMessage());
			}
	}

   public String getSystemDate()
	{
	int currentYear,currentMonth,currentDay=0;
	String start_date="",start_month="",start_datee="";
     Date sysDate = new Date();
	 currentYear		= sysDate.getYear();
	 currentMonth	= sysDate.getMonth();
	 currentDay		= sysDate.getDate();

	if(currentYear < 1900)
		currentYear = currentYear +1900;
  
	 if(currentDay<=9)
		start_date="0"+currentDay; 
     else
       start_date=""+currentDay; 

     if((currentMonth+1)<=9)
		 start_month="0"+(currentMonth+1);
     else
        start_month=""+(currentMonth+1);

	start_datee=start_date+"-"+start_month+"-"+currentYear;
	return start_datee;
	}



    
}