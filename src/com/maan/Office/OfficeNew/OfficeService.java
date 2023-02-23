package com.maan.Office.OfficeNew;

import java.util.List;
import java.util.Map;

public class OfficeService {
	OfficeDAO dao=new OfficeDAO();
	
	public List<Map<String, Object>> coverageList() {
		return dao.coverageList();
	}

	public void setCoverageDtls(OfficeBean bean) {
		dao.setCoverageDtls(bean);
	}

	public int updateCoverage(OfficeBean bean) {
		return dao.updateCoverage(bean);
	}

	public List<Map<String, Object>> schemeList() {
		return dao.schemeList();
	}

	public void setSchemeDtls(OfficeBean bean) {
		dao.setSchemeDtls(bean);
	}

	public int updateScheme(OfficeBean bean) {
		return dao.updateScheme(bean);
	}

	public List<Map<String, Object>> contentList(OfficeBean bean) {
		return dao.contentList(bean);
	}

	public void setContentDtls(OfficeBean bean) {
		dao.setContentDtls(bean);
	}

	public int updateContent(OfficeBean bean) {
		return dao.updateContent(bean);
	}

	public List<Map<String, Object>> contentDropDownList(OfficeBean bean) {
		return dao.contentDropDownList(bean);
	}

	public List<Map<String, Object>> coverageIncludedList(OfficeBean bean) {
		return dao.coverageIncludedList(bean);
	}

	public List<Map<String, Object>> coverageExcludedList(OfficeBean bean) {
		return dao.coverageExcludedList(bean);
	}

	public void setCoverageEditDtls(OfficeBean bean) {
		dao.setCoverageEditDtls(bean);
	}

	public List<Map<String, Object>> displayOrderList(OfficeBean bean) {
		return dao.displayOrderList(bean);
	}

	public int updateCoverageDtl(OfficeBean bean) {
		return dao.updateCoverageDtl(bean);
	}

	public boolean isGreaterEffectiveDate(OfficeBean bean, String type) {
		return dao.isGreaterEffectiveDate(bean,type);
	}

	public List<Map<String, Object>> getConfigDetails(OfficeBean bean) {
		return dao.getConfigDetails(bean);
	}

	public List<Map<String, Object>> getColumnNames(String table) {
		return dao.getColumnNames(table);
	}

	public List<Map<String, Object>> getTableNames() {
		return dao.getTableNames();
	}

	public String getMaxAmendId(OfficeBean bean) {
		return dao.getMaxAmendId(bean);
	}

	public String validString(String value,int format) {
		String returnval=null;
		try
		{
			value=value.trim();
			String validChar,validno,validextra=null;
			String string=new String("");
			if(value.length()>0)
			{
				validChar="abcdefghijklmnopqrstuvwxyz";
				validno="1234567890";
				validextra=".* %^&";
				value=value.toLowerCase();
				if(format==1)
				    string=new String(validChar);
				else if(format==2)
					string=new String(validno);
				else if(format==3)
					string=validChar+validno;
				else if(format==4)
					string=new String(validChar+validextra);
									
				for(int i=0;i<value.length();i++)
				{
					//char c=c.charAt(i);
					if(string.indexOf(value.charAt(i))== -1)
					returnval="Invalid";
				}
			}
			else
				returnval="Needed";
		}catch(Exception e)
		{
			return "Needed";
		}
		return returnval;
	}
	public String validInteger(String value)
	{
		String returnval=null;
		try
		{
			System.out.println("--"+Integer.parseInt(value));
		}catch(Exception e)
		{
			return "Invalid";
		}
		return returnval;
	}

	public int insertConfigDetails(OfficeBean bean, String values) {
		return dao.insertConfigDetails(bean,values);
	}

	public void getFormulaDetails(OfficeBean bean) {
		dao.getFormulaDetails(bean);
	}

	public int insertFormulaDetails(OfficeBean bean) {
		return dao.insertFormulaDetails(bean);
	}

	public List<Map<String, Object>> subCoverageList(OfficeBean bean) {
		return dao.subCoverageList(bean);
	}

	public List<Map<String, Object>> subCoverageIncludedList(OfficeBean bean) {
		return dao.subCoverageIncludedList(bean);
	}

	public List<Map<String, Object>> subCoverageExcludedList(OfficeBean bean) {
		return dao.subCoverageExcludedList(bean);
	}

	public List<Map<String, Object>> subDisplayOrderList(OfficeBean bean) {
		return dao.subDisplayOrderList(bean);
	}

	public void setSubCoverageEditDtls(OfficeBean bean) {
		dao.setSubCoverageEditDtls(bean);
	}

	public int updateSubCoverageDtl(OfficeBean bean) {
		return dao.updateSubCoverageDtl(bean);
	}

	public List<Map<String, Object>> fieldIncludedList(OfficeBean bean) {
		return dao.fieldIncludedList(bean);
	}

	public List<Map<String, Object>> getBrokerLinkedList() {
		return dao.getBrokerLinkedList();
	}

	public List<Map<String, Object>> fieldExcludedList(OfficeBean bean) {
		return dao.fieldExcludedList(bean);
	}

	public List<Map<String, Object>> displayOrderListNew(OfficeBean bean) {
		return dao.displayOrderListNew(bean);
	}

	public int insertFieldDetails(OfficeBean bean) {
		return dao.insertFieldDetails(bean);
	}

	public void setCoverageEditDtlsNew(OfficeBean bean) {
		dao.setCoverageEditDtlsNew(bean);
	}

	public List<Map<String, Object>> getBrokerLinkedFieldList(OfficeBean bean) {
		return dao.getBrokerLinkedFieldList(bean);
	}

	public void setCoverageEditDtlsBroker(OfficeBean bean) {
		dao.setCoverageEditDtlsBroker(bean);
	}

	public int updateFieldDetails(OfficeBean bean) {
		return dao.updateFieldDetails(bean);
	}

	public boolean isGreaterEffectiveDateNew(OfficeBean bean, String type) {
		return dao.isGreaterEffectiveDateNew(bean,type);
	}

	public List<Map<String, Object>> getGridDetails(OfficeBean bean) {
		return dao.getGridDetails(bean);
	}

	public String getMaxAmendIdGrid(OfficeBean bean) {
		return dao.getMaxAmendIdGrid(bean);
	}

	public int insertGridDetails(OfficeBean bean, String values) {
		return dao.insertGridDetails(bean,values);
	}

	public void setDefaultBrokerCoverageDtls(OfficeBean bean) {
		dao.setDefaultBrokerCoverageDtls(bean);
	}

}
