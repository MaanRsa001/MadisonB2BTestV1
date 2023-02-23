package com.maan.common;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CreationHelper;
public class ExcelDownload {	
	
	public static String writeExcel(String[] headersList, List recordsList, ByteArrayOutputStream bos) throws Exception
	{
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet("Sheet1");
		HSSFFont bold = wb.createFont();
		bold.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		HSSFCellStyle style = wb.createCellStyle();		  			
		style.setFont(bold);
		HSSFRow row = null;
		HSSFCell cell = null;
		String key="", value="";
		int c=0;
		row = sheet.createRow(0);	
		for(int i=0; i<headersList.length; i++)
		{
			cell = row.createCell(new Integer(c++).shortValue());
			value=(headersList[i]==null?"":headersList[i]);
			cell.setCellValue(new HSSFRichTextString(value));
			cell.setCellStyle(style);
		}
		
		if(recordsList!=null && recordsList.size()>0)
		{
			for(int i=0; i<recordsList.size(); i++)
			{
				c=0;
				row = sheet.createRow(i+1);
				Map map=(Map)recordsList.get(i);
				Set keySet=map.keySet();
				Iterator itr=keySet.iterator();
				while(itr.hasNext())
				{	
					cell = row.createCell(new Integer(c++).shortValue());
					key=(String)itr.next();
					value=(map.get(key)==null?"":map.get(key)).toString();
					cell.setCellValue(new HSSFRichTextString(value));
					cell.setCellStyle(style);
				}
			}
		}
		wb.write(bos);
		bos.close();
		return "";
	}

	public void writeExcelNew(List<String> fileHeader, List<Map<String,Object>> recordsList, ByteArrayOutputStream bos, String type) throws Exception{
		try {
			HSSFWorkbook wb = new HSSFWorkbook();
			HSSFSheet sheet = wb.createSheet("RatingReport");
			//HSSFFont bold = wb.createFont();
			//bold.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			HSSFCellStyle style = wb.createCellStyle();	   	
			//style.setFont(bold);
		
			// For date Format
			HSSFCellStyle dateStyle = wb.createCellStyle();
			CreationHelper dateHelper = wb.getCreationHelper();
			dateStyle.setDataFormat(dateHelper.createDataFormat().getFormat("DD-MMM-YYYY"));
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
		
			HSSFRow row = null;
			HSSFCell cell = null;
			String key="", value="";
			int c=0;
			row = sheet.createRow(0);
			while(fileHeader.remove("")){
				
			}
			for(int i=0; i<fileHeader.size(); i++){
				cell = row.createCell(c++);
				value=(fileHeader.get(i)==null?"":fileHeader.get(i));
				cell.setCellValue(new HSSFRichTextString(value));
				cell.setCellStyle(style);
			}
			if("report".equalsIgnoreCase(type)){
				if(recordsList!=null && recordsList.size()>0){
					for(int i=1; i<recordsList.size(); i++){
						c=0;
						row = sheet.createRow(i);
						Map<String,Object> map = recordsList.get(i);
						Map<String,Object> myMap = new LinkedHashMap<String, Object>();
							
						for(int j=0; j<fileHeader.size(); j++){
							String headKey=fileHeader.get(j);
							String val=map.get(headKey)==null?"":map.get(headKey).toString();
							myMap.put(headKey, val);
						}
						Set<String> keySet=myMap.keySet();
						//fileHeader.get(i-1)==keySet
						Iterator<String> itr=keySet.iterator();
						int headerCount = 0;
						while(itr.hasNext()){	
							cell = row.createCell(c++);
							key=(String)itr.next();
							value=(myMap.get(key)==null?"":myMap.get(key)).toString();
							if(fileHeader.get(headerCount).contains("DD-MMM-YYYY")){
								Date dateValue = formatter.parse(value);
								cell.setCellValue(dateValue);
								cell.setCellStyle(dateStyle);
							}else{
								cell.setCellValue(new HSSFRichTextString(value));
								cell.setCellStyle(style);
							}
							headerCount++;
						}
					}
				}
			}else if("otherReport".equalsIgnoreCase(type)){

				if(recordsList!=null && recordsList.size()>0){
					for(int i=1; i<=recordsList.size(); i++){
						c=0;
						row = sheet.createRow(i);
						Map<String,Object> map = recordsList.get(i-1);
						Map<String,Object> myMap = new LinkedHashMap<String, Object>();
							
						for(int j=0; j<fileHeader.size(); j++){
							String headKey=fileHeader.get(j);
							String val=map.get(headKey)==null?"":map.get(headKey).toString();
							myMap.put(headKey, val);
						}
						Set<String> keySet=myMap.keySet();
						//fileHeader.get(i-1)==keySet
						Iterator<String> itr=keySet.iterator();
						int headerCount = 0;
						while(itr.hasNext()){	
							cell = row.createCell(c++);
							key=(String)itr.next();
							value=(myMap.get(key)==null?"":myMap.get(key)).toString();
							if(fileHeader.get(headerCount).contains("DD-MMM-YYYY")){
								Date dateValue = formatter.parse(value);
								cell.setCellValue(dateValue);
								cell.setCellStyle(dateStyle);
							}else{
								cell.setCellValue(new HSSFRichTextString(value));
								cell.setCellStyle(style);
							}
							headerCount++;
						}
					}
				}
			
			}
			wb.write(bos);
			bos.close();
		} catch (Exception e) {
		e.printStackTrace();
		}
	}
}