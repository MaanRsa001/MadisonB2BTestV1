package com.maan.Office.OfficeNew;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.dao.DataAccessException;

import com.maan.common.LogManager;
import com.maan.common.MyJdbcTemplate;
import com.maan.services.util.runner;

public class OfficeDAO extends MyJdbcTemplate{
	
	String sql="";
	Object[] args=null;

	public List<Map<String, Object>> coverageList() {
		List<Map<String, Object>> coverageList=new ArrayList<Map<String, Object>>();
		try {
			sql=getQuery("GET_COVERAGE_LIST");
			LogManager.info("coverageList Query=>"+sql);
			coverageList=this.mytemplate.queryForList(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return coverageList;
	}

	public void setCoverageDtls(OfficeBean bean) {
		List<Map<String, Object>> coverageList=new ArrayList<Map<String, Object>>();
		try {
			sql=getQuery("GET_COVERAGE_LIST_EDIT");
			args=new Object[]{bean.getCoverageId()};
			LogManager.info("setCoverageDtls Query=>"+sql);
			LogManager.info("setCoverageDtls obj[] ==> "+StringUtils.join(args, ","));
			coverageList=this.mytemplate.queryForList(sql,args);
			if(coverageList!=null && coverageList.size()>0){
				bean.setCoverageName(coverageList.get(0).get("COVERAGES_NAME")==null?"":coverageList.get(0).get("COVERAGES_NAME").toString());
				bean.setCoverageDisplayName(coverageList.get(0).get("COVERAGES_DISPLAY_NAME")==null?"":coverageList.get(0).get("COVERAGES_DISPLAY_NAME").toString());
				bean.setSectionDetail(coverageList.get(0).get("SECTION_DETAILS")==null?"":coverageList.get(0).get("SECTION_DETAILS").toString());
				bean.setCoverageCoreCode(coverageList.get(0).get("RSACODE")==null?"":coverageList.get(0).get("RSACODE").toString());
				bean.setCoverageStatus(coverageList.get(0).get("STATUS")==null?"":coverageList.get(0).get("STATUS").toString());
				bean.setCoverageRemarks(coverageList.get(0).get("REMARKS")==null?"":coverageList.get(0).get("REMARKS").toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public int updateCoverage(OfficeBean bean) {
		int res=0;
		try {
			if("add".equalsIgnoreCase(bean.getMode())){
				sql=getQuery("GET_INSERT_COVERAGE_DTL");
				args=new Object[]{bean.getCoverageName(),bean.getCoverageDisplayName(),bean.getSectionDetail(),bean.getCoverageCoreCode(),bean.getCoverageRemarks(),bean.getCoverageStatus()};
			}
			else if("edit".equalsIgnoreCase(bean.getMode())){
				sql=getQuery("GET_UPDATE_COVERAGE_DTL");
				args=new Object[]{bean.getCoverageName(),bean.getCoverageDisplayName(),bean.getSectionDetail(),bean.getCoverageCoreCode(),bean.getCoverageRemarks(),bean.getCoverageStatus(),bean.getCoverageId()};
			}
			LogManager.info("updateCoverage Query=>"+sql);
			LogManager.info("updateCoverage obj[] ==> "+StringUtils.join(args, ","));
			res=this.mytemplate.update(sql,args);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public List<Map<String, Object>> schemeList() {
		List<Map<String, Object>> schemeList=new ArrayList<Map<String, Object>>();
		try {
			sql=getQuery("GET_SCHEME_MASTER_LIST");
			LogManager.info("schemeList Query=>"+sql);
			schemeList=this.mytemplate.queryForList(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return schemeList;
	}

	public void setSchemeDtls(OfficeBean bean) {
		List<Map<String, Object>> schemeList=new ArrayList<Map<String, Object>>();
		try {
			sql=getQuery("GET_SCHEME_MASTER_EDIT");
			args=new Object[]{bean.getSchemeId()};
			LogManager.info("setSchemeDtls Query=>"+sql);
			LogManager.info("setSchemeDtls obj[] ==> "+StringUtils.join(args, ","));
			schemeList=this.mytemplate.queryForList(sql,args);
			if(schemeList!=null && schemeList.size()>0){
				bean.setSchemeName(schemeList.get(0).get("SCHEME_NAME")==null?"":schemeList.get(0).get("SCHEME_NAME").toString());
				bean.setSchemeCoreCode(schemeList.get(0).get("RSACODE")==null?"":schemeList.get(0).get("RSACODE").toString());
				bean.setSchemeStatus(schemeList.get(0).get("STATUS")==null?"":schemeList.get(0).get("STATUS").toString());
				bean.setSchemeRemarks(schemeList.get(0).get("REMARKS")==null?"":schemeList.get(0).get("REMARKS").toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int updateScheme(OfficeBean bean) {
		int res=0;
		try {
			if("add".equalsIgnoreCase(bean.getMode())){
				sql=getQuery("GET_SCHEME_MASTER_INSERT");
				args=new Object[]{bean.getSchemeName(),bean.getSchemeRemarks(),bean.getSchemeStatus(),bean.getSchemeCoreCode()};
			}
			else if("edit".equalsIgnoreCase(bean.getMode())){
				sql=getQuery("GET_SCHEME_MASTER_UPDATE");
				args=new Object[]{bean.getSchemeName(),bean.getSchemeCoreCode(),bean.getSchemeRemarks(),bean.getSchemeStatus(),bean.getSchemeId()};
			}
			LogManager.info("updateScheme Query=>"+sql);
			LogManager.info("updateScheme obj[] ==> "+StringUtils.join(args, ","));
			res=this.mytemplate.update(sql,args);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public List<Map<String, Object>> contentList(OfficeBean bean) {
		List<Map<String, Object>> list=new ArrayList<Map<String, Object>>();
		try {
			sql=getQuery("GET_CONTENT_MASTER_LIST");
			args=new Object[]{bean.getSchemeId()};
			LogManager.info("contentList Query=>"+sql);
			LogManager.info("contentList obj[]=>"+StringUtils.join(args, ", "));
			list=this.mytemplate.queryForList(sql,args);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public void setContentDtls(OfficeBean bean) {
		List<Map<String, Object>> list=new ArrayList<Map<String, Object>>();
		try {
			sql=getQuery("GET_CONTENT_MASTER_EDIT");
			args=new Object[]{bean.getSchemeId(),bean.getContentId()};
			LogManager.info("setContentDtls Query=>"+sql);
			LogManager.info("setContentDtls obj[] ==> "+StringUtils.join(args, ","));
			list=this.mytemplate.queryForList(sql,args);
			if(list!=null && list.size()>0){
				bean.setContentDesc(list.get(0).get("CONTENT_DESCRIPTION")==null?"":list.get(0).get("CONTENT_DESCRIPTION").toString());
				bean.setContentValue(list.get(0).get("CONTENT_VALUE")==null?"":list.get(0).get("CONTENT_VALUE").toString());
				bean.setMinimumPremium(list.get(0).get("MINIMUM_PREMIUM")==null?"":list.get(0).get("MINIMUM_PREMIUM").toString());
				bean.setContentCoreCode(list.get(0).get("RSACODE")==null?"":list.get(0).get("RSACODE").toString());
				bean.setContentStatus(list.get(0).get("STATUS")==null?"":list.get(0).get("STATUS").toString());
				bean.setContentRemarks(list.get(0).get("REMARKS")==null?"":list.get(0).get("REMARKS").toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int updateContent(OfficeBean bean) {
		int res=0;
		try {
			if("add".equalsIgnoreCase(bean.getMode())){
				sql=getQuery("GET_CONTENT_MASTER_INSERT");
				args=new Object[]{bean.getSchemeId(),bean.getContentDesc(),bean.getContentRemarks(),bean.getContentStatus(),bean.getSchemeId(),bean.getContentValue(),bean.getMinimumPremium(),bean.getContentCoreCode()};
			}
			else if("edit".equalsIgnoreCase(bean.getMode())){
				sql=getQuery("GET_CONTENT_MASTER_UPDATE");
				args=new Object[]{bean.getContentDesc(),bean.getContentRemarks(),bean.getContentStatus(),bean.getContentValue(),bean.getMinimumPremium(),bean.getContentCoreCode(),bean.getSchemeId(),bean.getContentId()};
			}
			LogManager.info("updateContent Query=>"+sql);
			LogManager.info("updateContent obj[] ==> "+StringUtils.join(args, ","));
			res=this.mytemplate.update(sql,args);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public List<Map<String, Object>> contentDropDownList(OfficeBean bean) {
		List<Map<String, Object>> list=new ArrayList<Map<String, Object>>();
		try {
			sql=getQuery("GET_CONTENT_DROPDOWN_LIST");
			args=new Object[]{bean.getSchemeId()};
			LogManager.info("contentDropDownList Query=>"+sql);
			LogManager.info("contentDropDownList obj[]=>"+StringUtils.join(args, ", "));
			list=this.mytemplate.queryForList(sql,args);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public List<Map<String, Object>> coverageIncludedList(OfficeBean bean) {
		List<Map<String, Object>> list=new ArrayList<Map<String, Object>>();
		try {
			sql=getQuery("GET_COVERAGE_INCLUDED_LIST");
			args=new Object[]{bean.getContentId(),bean.getSchemeId()};
			LogManager.info("coverageIncludedList Query=>"+sql);
			LogManager.info("coverageIncludedList obj[]=>"+StringUtils.join(args, ", "));
			list=this.mytemplate.queryForList(sql,args);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public List<Map<String, Object>> coverageExcludedList(OfficeBean bean) {
		List<Map<String, Object>> list=new ArrayList<Map<String, Object>>();
		try {
			sql=getQuery("GET_COVERAGE_EXCLUDED_LIST");
			args=new Object[]{bean.getContentId(),bean.getSchemeId()};
			LogManager.info("coverageExcludedList Query=>"+sql);
			LogManager.info("coverageExcludedList obj[]=>"+StringUtils.join(args, ", "));
			list=this.mytemplate.queryForList(sql,args);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public void setCoverageEditDtls(OfficeBean bean) {
		List<Map<String, Object>> list=new ArrayList<Map<String, Object>>();
		try {
			sql=getQuery("GET_COVERAGE_EDIT_DETAILS");
			args=new Object[]{bean.getProductId(),bean.getCoverageId(),bean.getSchemeId(),bean.getContentId()};
			LogManager.info("setCoverageEditDtls Query=>"+sql);
			LogManager.info("setCoverageEditDtls obj[] ==> "+StringUtils.join(args, ","));
			list=this.mytemplate.queryForList(sql,args);
			if(list!=null && list.size()>0){
				bean.setCoverageType(list.get(0).get("COVERAGES_TYPE")==null?"":list.get(0).get("COVERAGES_TYPE").toString());
				bean.setUploadOption(list.get(0).get("UPLOAD_OPTION")==null?"":list.get(0).get("UPLOAD_OPTION").toString());
				bean.setDisplayOrder(list.get(0).get("DISPLAY_ORDER")==null?"":list.get(0).get("DISPLAY_ORDER").toString());
				bean.setSumControlType(list.get(0).get("SUM_INSURED_CONTROL_TYPE")==null?"":list.get(0).get("SUM_INSURED_CONTROL_TYPE").toString());
				bean.setSumCoverageLimit(list.get(0).get("SUM_INSURED_LIMIT")==null?"":list.get(0).get("SUM_INSURED_LIMIT").toString());
				bean.setMinSumLimit(list.get(0).get("MIN_SUM_INSURED")==null?"":list.get(0).get("MIN_SUM_INSURED").toString());
				bean.setControlType(list.get(0).get("CONTROL_TYPE")==null?"":list.get(0).get("CONTROL_TYPE").toString());
				bean.setCoverageLimit(list.get(0).get("COVERAGES_LIMIT")==null?"":list.get(0).get("COVERAGES_LIMIT").toString());
				bean.setExcess(list.get(0).get("EXCESS")==null?"":list.get(0).get("EXCESS").toString());
				bean.setBaseRate(list.get(0).get("BASE_RATE")==null?"":list.get(0).get("BASE_RATE").toString());
				bean.setCalculationStatus(list.get(0).get("CALC_STATUS")==null?"":list.get(0).get("CALC_STATUS").toString());
				bean.setCalculationType(list.get(0).get("CALC_TYPE")==null?"":list.get(0).get("CALC_TYPE").toString());
				bean.setStatus(list.get(0).get("STATUS")==null?"":list.get(0).get("STATUS").toString());
				bean.setEffectiveDate(list.get(0).get("EFFECTIVE_DATE")==null?"":list.get(0).get("EFFECTIVE_DATE").toString());
				bean.setRsaCode(list.get(0).get("RSACODE")==null?"":list.get(0).get("RSACODE").toString());
				bean.setHelpContent(list.get(0).get("HELP_DESCRIPTION")==null?"":list.get(0).get("HELP_DESCRIPTION").toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<Map<String, Object>> displayOrderList(OfficeBean bean) {
		List<Map<String, Object>> list=new ArrayList<Map<String, Object>>();
		Map<String, Object> listAdd=new HashMap<String, Object>();
		try {
			sql=getQuery("GET_DISPLAY_ORDER_LIST");
			args=new Object[]{bean.getProductId(),bean.getContentId(),bean.getSchemeId(),bean.getProductId(),bean.getContentId(),bean.getSchemeId()};
			LogManager.info("displayOrderList Query=>"+sql);
			LogManager.info("displayOrderList obj[]=>"+StringUtils.join(args, ", "));
			list=this.mytemplate.queryForList(sql,args);
			if(list.size()<=0)
				listAdd.put("DISPLAY_ORDER","1");
			else
				listAdd.put("DISPLAY_ORDER",list.size()+1);
			
			list.add(listAdd);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public int updateCoverageDtl(OfficeBean bean) {
		int res=0;
		try {
			String temp = "sysdate";
			String amend = new String();
			amend = maxAmendID(bean.getProductId(), bean.getCoverageId(), bean.getSchemeId(), bean.getContentId());

			String[][] help = new String[0][0];
			help = selectValue(bean.getProductId(), bean.getCoverageId(), bean.getSchemeId(), bean.getContentId());

			String helpID = "";
			if (help.length > 0) {
				helpID = help[0][6];
				String updateQuery = "update OFS_HELP_MASTER set HELP_DESCRIPTION ='"
						+ bean.getHelpContent() + "' where HELP_CONTENTS_ID =" + helpID;
				this.mytemplate.update(updateQuery);
			} else {
				helpID = maxOfHelpID();
				String helpQuery = "insert into OFS_HELP_MASTER(HELP_CONTENTS_ID,HELP_DESCRIPTION,STATUS) values"
						+ "(" + helpID + ",'" + bean.getHelpContent() + "','Y' )";
				this.mytemplate.update(helpQuery);
			}
			int amendID;

			try {
				amendID = Integer.parseInt(amend);
			} catch (Exception e) {
				amendID = -1;
			}

			if (amendID >= 0) {
				if (isGreaterEntryDate(bean,amendID,"cover")) {
					String updateQuery = "update OFS_COVERAGES_MASTER set EXPIRY_DATE = "+temp+" where AMEND_ID='"
							+ amendID
							+ "' and PRODUCT_ID = "
							+ bean.getProductId()
							+ " and COVERAGES_ID = "
							+ bean.getCoverageId()
							+ " and SCHEME_ID = "
							+ bean.getSchemeId()
							+ " and CONTENT_TYPE_ID = " + bean.getContentId();
					System.out.println("Update Query : " + updateQuery);
					res=this.mytemplate.update(updateQuery);
				} else {
					String updateQuery = "update OFS_COVERAGES_MASTER set EXPIRY_DATE =(select to_date('"
							+ bean.getEffectiveDate()
							+ "', 'dd-MM-YYYY')-1 from dual) where AMEND_ID='"
							+ amendID
							+ "' and PRODUCT_ID = "
							+ bean.getProductId()
							+ " and COVERAGES_ID = "
							+ bean.getCoverageId()
							+ " and SCHEME_ID = "
							+ bean.getSchemeId()
							+ " and CONTENT_TYPE_ID = " + bean.getContentId();
					System.out.println("Update Query : " + updateQuery);
					res=this.mytemplate.update(updateQuery);
				}
			}
			amendID += 1;

			String result = new String("0");
			int countSts=0;
			result = runner.singleSelection("select count(*) from OFS_COVERAGES_SUB_MASTER WHERE PRODUCT_ID = "
							+ bean.getProductId()
							+ " and COVERAGES_ID = "
							+ bean.getCoverageId()
							+ " and SCHEME_ID = "
							+ bean.getSchemeId()
							+ " and CONTENT_TYPE_ID = " + bean.getContentId() );
			try{
			 if(result!=null)
			       countSts=Integer.parseInt(result);
				}catch(Exception e){ System.out.println("Status"+e);}

			String query = "insert into OFS_COVERAGES_MASTER(PRODUCT_ID, COVERAGES_ID, "
					+ "SCHEME_ID,CONTENT_TYPE_ID,COVERAGES_TYPE, UPLOAD_OPTION, DISPLAY_ORDER, "
					+ "CONTROL_TYPE,COVERAGES_LIMIT, BASE_RATE, HELP_CONTENTS_ID,"
					+ "STATUS,EXCESS,CALC_STATUS,CALC_TYPE,AMEND_ID,EFFECTIVE_DATE,ENTRY_DATE,RSACODE,"
					+ "SUB_COVERAGES,SUM_INSURED_LIMIT,SUM_INSURED_CONTROL_TYPE,MIN_SUM_INSURED,MULTIPLE_ROWS,EXPIRY_DATE) values("
					+ bean.getProductId()
					+ ","
					+ bean.getCoverageId()
					+ ","
					+ bean.getSchemeId()
					+ ","
					+ bean.getContentId()
					+ ",'"
					+ bean.getCoverageType()
					+ "','"
					+ bean.getUploadOption()
					+ "',"
					+ bean.getDisplayOrder()
					+ ",'"
					+ bean.getControlType()
					+ "','"
					+ bean.getCoverageLimit()
					+ "',"
					+ bean.getBaseRate()
					+ ","
					+ helpID
					+ ",'"
					+ bean.getStatus()
					+ "','"
					+ bean.getExcess()
					+ "','"
					+ bean.getCalculationStatus()
					+ "','"
					+ bean.getCalculationType()
					+ "',"
					+ amendID
					+ " ,to_date('"
					+ bean.getEffectiveDate()
					+ "','dd-MM-YYYY')"
					+ ", (select "+temp+" FROM dual),'"
					+ bean.getRsaCode()
					+ (countSts>0?"','Y',":"','N',")
					+ (bean.getSumCoverageLimit().length()<=0?"0":bean.getSumCoverageLimit())
					+ ",'"
					+ bean.getSumControlType()
					+ "','"
					+(bean.getMinSumLimit().length()<=0?"0":bean.getMinSumLimit()) 
					+ "','"
					+ "M"
					+ "',to_date('"
					+ bean.getEffectiveDate() + "','dd-MM-YYYY')+1095 )";
			res=this.mytemplate.update(query);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
	
	public String maxAmendID(String pProductID, String pCoverageNameID,String pSchemeID, String pContentTypeID) {
		String checkValue = "";
		String[] values=new String[4];
		values[0]=pProductID;
		values[1]=pCoverageNameID;
		values[2]=pSchemeID;
		values[3]=pContentTypeID;
		String checkQuery = "select nvl(max(amend_id), -1) from OFS_COVERAGES_MASTER where PRODUCT_ID = ? and COVERAGES_ID =? and SCHEME_ID = ? and CONTENT_TYPE_ID = ?";
		checkValue = runner.singleSelection(checkQuery,values);
		return checkValue;
	}
	public String maxAmendID(String pProductID, String pCoverageNameID,String pSchemeID, String pContentTypeID, String subCoverId) {
		String checkValue = "";
		String[] values=new String[5];
		values[0]=pProductID;
		values[1]=pCoverageNameID;
		values[2]=pSchemeID;
		values[3]=pContentTypeID;
		values[4]=subCoverId;
		String checkQuery = "select nvl(max(amend_id), -1) from OFS_COVERAGES_SUB_MASTER where PRODUCT_ID = ? and COVERAGES_ID = ? and SCHEME_ID = ? and CONTENT_TYPE_ID = ? and COVERAGES_SUB_ID =? ";
		checkValue = runner.singleSelection(checkQuery,values);
		return checkValue;
	}
	
	public String[][] selectValue(String pProductID, String pCoverageNameID,String pSchemeID, String pContentTypeID) {
		String[][] checkValue = new String[0][0];
		String[] values=new String[5];
		String amendID = new String();
		amendID = maxAmendID(pProductID, pCoverageNameID, pSchemeID,
				pContentTypeID);
		values[0]=pProductID;
		values[1]=pCoverageNameID;
		values[2]=pSchemeID;
		values[3]=pContentTypeID;
		values[4]=amendID;

		String checkQuery = "select COVERAGES_TYPE, UPLOAD_OPTION, DISPLAY_ORDER, CONTROL_TYPE,COVERAGES_LIMIT, BASE_RATE, HELP_CONTENTS_ID,STATUS,EXCESS,CALC_STATUS,CALC_TYPE,EFFECTIVE_DATE,RSACODE,SUM_INSURED_LIMIT,SUM_INSURED_CONTROL_TYPE,MIN_SUM_INSURED from OFS_COVERAGES_MASTER where PRODUCT_ID = ? and COVERAGES_ID = ? and SCHEME_ID = ? and CONTENT_TYPE_ID = ? and AMEND_ID = ?";
		checkValue = runner.multipleSelection(checkQuery,values);
		for (int i = 0; i < checkValue.length; i++) 
			  
            // Loop through all elements of current row 
            for (int j = 0; j < checkValue[i].length; j++) 
                System.out.print(checkValue[i][j] + " "); 
		return checkValue;
	}
	
	public String maxOfHelpID() {
		String query = "select nvl(max(HELP_CONTENTS_ID)+1, -1) from OFS_HELP_MASTER";
		String result = new String();
		result = runner.singleSelection(query,new String[0]);
		return result;
	}
	
	public boolean isGreaterEntryDate(OfficeBean bean, int amendID,String type) {
		String checkValue = "";
		if("cover".equalsIgnoreCase(type)){
			String values[]=new String[6];
				values[0]=bean.getEffectiveDate();
				values[1]=""+amendID;
				values[2]=bean.getProductId();
				values[3]=bean.getCoverageId();
				values[4]=bean.getSchemeId();
				values[5]=bean.getContentId();
			String selectQuery = "select  to_date(?,'dd-MM-YYYY')-to_date(ENTRY_DATE) from OFS_COVERAGES_MASTER where AMEND_ID=? and PRODUCT_ID = ? and COVERAGES_ID = ? and SCHEME_ID = ? and CONTENT_TYPE_ID = ?" ;
			//System.out.println(selectQuery);
			checkValue = runner.singleSelection(selectQuery,values);
		}
		else{
			String temp = "sysdate";
			temp = runner.getSysdate("01");		
			String values[]=new String[6];
			values[0]=""+amendID;
			values[1]=bean.getProductId();
			values[2]=bean.getCoverageId();
			values[3]=bean.getSchemeId();
			values[4]=bean.getContentId();
			values[5]=bean.getSubCoverageId();
			String selectQuery = "select to_date("+temp+")-to_date(ENTRY_DATE) from OFS_COVERAGES_SUB_MASTER where AMEND_ID=? and PRODUCT_ID =? and COVERAGES_ID = ? and SCHEME_ID = ? and CONTENT_TYPE_ID = ? and COVERAGES_SUB_ID = ?";
			//System.out.println(selectQuery);
			checkValue = runner.singleSelection(selectQuery,values);
		}
		try {
			int day = Integer.parseInt(checkValue);
			if (day == 0) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	public boolean isGreaterEffectiveDate(OfficeBean bean, String type) {
		String amend = new String();
		String checkValue = "";
		if("cover".equalsIgnoreCase(type))
			amend = maxAmendID(bean.getProductId(), bean.getCoverageId(), bean.getSchemeId(), bean.getContentId());
		else
			amend = maxAmendID(bean.getProductId(), bean.getCoverageId(), bean.getSchemeId(), bean.getContentId(),bean.getSubCoverageId());
		
		int amendID;
		try {
			amendID = Integer.parseInt(amend);
		} catch (Exception e) {
			amendID = -1;
		}
		if (amendID < 0) {
			return true;
		} else {
			if("cover".equalsIgnoreCase(type)){
				String values[]=new String[6];
				values[0]=bean.getEffectiveDate();
				values[1]=""+amendID;
				values[2]=bean.getProductId();
				values[3]=bean.getCoverageId();
				values[4]=bean.getSchemeId();
				values[5]=bean.getContentId();
	
				String selectQuery = "select round(to_date(?,'dd-MM-YYYY') -(select EFFECTIVE_DATE from OFS_COVERAGES_MASTER where AMEND_ID=? and PRODUCT_ID = ? and COVERAGES_ID = ? and SCHEME_ID =? and CONTENT_TYPE_ID = ?)) from dual";
				checkValue = runner.singleSelection(selectQuery,values);
			}else{
				String values[]=new String[7];
				values[0]=bean.getEffectiveDate();
				values[1]=""+amendID;
				values[2]=bean.getProductId();
				values[3]=bean.getCoverageId();
				values[4]=bean.getSchemeId();
				values[5]=bean.getContentId();
				values[6]=bean.getSubCoverageId();
				String selectQuery = "select ROUND(to_date(?,'dd-MM-YYYY') -(select EFFECTIVE_DATE from OFS_COVERAGES_SUB_MASTER where AMEND_ID=? and PRODUCT_ID = ? and COVERAGES_ID = ? and SCHEME_ID = ? and CONTENT_TYPE_ID =? and COVERAGES_SUB_ID = ?)) from dual";
				checkValue = runner.singleSelection(selectQuery,values);
			}
			try {
				int day = Integer.parseInt(checkValue);
				if (day >= 0) {
					return true;
				} else {
					return false;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return false;
		}
	}

	public List<Map<String, Object>> getConfigDetails(OfficeBean bean) {
		List<Map<String, Object>> res=new ArrayList<Map<String,Object>>();
		try{
			String sql="SELECT DISPLAY_NAME, DISPLAY_TYPE, DISPLAY_ORDER, DROPDOWN_KEY, DROPDOWN_VALUE, DROPDOWN_TABLE, VALIDATION_TYPE, DEST_TABLE, DEST_COLUMN, STATUS, Mandatory, TOTAL_SUMINSURED_YN FROM OFS_CONFIG_MASTER WHERE product_id = ? AND scheme_id = ? AND content_type_id = ? AND coverages_id = ? AND coverages_sub_id = ? AND coverages_id || coverages_sub_id || amend_id =( SELECT coverages_id || coverages_sub_id || MAX (amend_id) FROM OFS_CONFIG_MASTER WHERE product_id = ? AND scheme_id = ? AND content_type_id = ? AND coverages_id = ? AND coverages_sub_id = ? GROUP BY coverages_id, coverages_sub_id)";
			String subcovId=StringUtils.isBlank(bean.getSubCoverageId())?"0":bean.getSubCoverageId();
			Object[] args=new Object[]{bean.getProductId(),bean.getSchemeId(),bean.getContentId(),bean.getCoverageId(),subcovId,bean.getProductId(),bean.getSchemeId(),bean.getContentId(),bean.getCoverageId(),subcovId};
			res=this.mytemplate.queryForList(sql,args);
			if(res!=null && res.size()>0){
				List<String> displayNamesConfig=new ArrayList<String>();
				List<String> sumControlTypeConfig=new ArrayList<String>();
				List<String> displayOrderConfig=new ArrayList<String>();
				List<String> dropdownTableConfig=new ArrayList<String>();
				List<String> validationTypeConfig=new ArrayList<String>();
				List<String> destTableConfig=new ArrayList<String>();
				List<String> destColumnConfig=new ArrayList<String>();
				List<String> mandatoryConfig=new ArrayList<String>();
				List<String> totalSumInsuredConfig=new ArrayList<String>();
				List<String> statusConfig=new ArrayList<String>();
				for(int i=0;i<res.size();i++){
					displayNamesConfig.add(res.get(i).get("DISPLAY_NAME")==null?"":res.get(i).get("DISPLAY_NAME").toString());
					sumControlTypeConfig.add(res.get(i).get("DISPLAY_TYPE")==null?"":res.get(i).get("DISPLAY_TYPE").toString());
					displayOrderConfig.add(res.get(i).get("DISPLAY_ORDER")==null?"":res.get(i).get("DISPLAY_ORDER").toString());
					dropdownTableConfig.add(res.get(i).get("DROPDOWN_TABLE")==null?"":res.get(i).get("DROPDOWN_TABLE").toString());
					validationTypeConfig.add(res.get(i).get("VALIDATION_TYPE")==null?"":res.get(i).get("VALIDATION_TYPE").toString());
					destTableConfig.add(res.get(i).get("DEST_TABLE")==null?"":res.get(i).get("DEST_TABLE").toString());
					destColumnConfig.add(res.get(i).get("DEST_COLUMN")==null?"":res.get(i).get("DEST_COLUMN").toString());
					mandatoryConfig.add(res.get(i).get("MANDATORY")==null?"":res.get(i).get("MANDATORY").toString());
					totalSumInsuredConfig.add(res.get(i).get("TOTAL_SUMINSURED_YN")==null?"":res.get(i).get("TOTAL_SUMINSURED_YN").toString());
					statusConfig.add(res.get(i).get("STATUS")==null?"":res.get(i).get("STATUS").toString());
				}
				bean.setDisplayNamesConfig(displayNamesConfig);
				bean.setSumControlTypeConfig(sumControlTypeConfig);
				bean.setDisplayOrderConfig(displayOrderConfig);
				bean.setDropdownTableConfig(dropdownTableConfig);
				bean.setValidationTypeConfig(validationTypeConfig);
				bean.setDestTableConfig(destTableConfig);
				bean.setDestColumnConfig(destColumnConfig);
				bean.setMandatoryConfig(mandatoryConfig);
				bean.setTotalSumInsuredConfig(totalSumInsuredConfig);
				bean.setStatusConfig(statusConfig);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return res;
	}

	public List<Map<String, Object>> getColumnNames(String table) {
		List<Map<String, Object>> res=new ArrayList<Map<String,Object>>();
		try{
			String sql="SELECT DISTINCT COLUMN_NAME FROM ALL_TAB_COLS WHERE TABLE_NAME = ? AND COLUMN_NAME LIKE 'FIELD%' ORDER BY COLUMN_NAME ASC";
			Object[] args=new Object[]{table};
			res=this.mytemplate.queryForList(sql,args);
		}catch(Exception e){
			e.printStackTrace();
		}
		return res;
	}

	public List<Map<String, Object>> getTableNames() {
		List<Map<String, Object>> res=new ArrayList<Map<String,Object>>();
		try{
			String sql="SELECT TNAME FROM TAB WHERE TABTYPE='TABLE'";
			res=this.mytemplate.queryForList(sql);
		}catch(Exception e){
			e.printStackTrace();
		}
		return res;
	}

	public String getMaxAmendId(OfficeBean bean) {
		String amendId="0";
		try {
			String sql="select (case when count(*)=0 then 0 else max(amend_id)+1 end) from OFS_CONFIG_MASTER where product_id=? and scheme_id=? and content_type_id=? and coverages_id=? and coverages_sub_id=?";
			String args[]=new String[5];
			args[0]=bean.getProductId();
			args[1]=bean.getSchemeId();
			args[2]=bean.getContentId();
			args[3]=bean.getCoverageId();
			args[4]=StringUtils.isBlank(bean.getSubCoverageId())?"0":bean.getSubCoverageId();
			LogManager.info("getMaxAmendId Query=>"+sql);
			LogManager.info("getMaxAmendId obj[] ==> "+StringUtils.join(args, ","));
			amendId = (String) this.mytemplate.queryForObject(sql, args,String.class);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return amendId;
	}

	public int insertConfigDetails(OfficeBean bean, String values) {
		LogManager.info("insertConfigDetails - Enter");
		int result = 0;
		try {
			String args[]=new String[5];
			String sql="INSERT INTO OFS_CONFIG_MASTER(OFS_CONFIG_ID,PRODUCT_ID,SCHEME_ID,CONTENT_TYPE_ID,COVERAGES_ID,COVERAGES_SUB_ID,DISPLAY_NAME,DISPLAY_TYPE,DISPLAY_ORDER,DROPDOWN_KEY,DROPDOWN_VALUE,DROPDOWN_TABLE,VALIDATION_TYPE,DEST_TABLE,DEST_COLUMN,AMEND_ID,STATUS,MANDATORY,TOTAL_SUMINSURED_YN) VALUES((SELECT NVL(MAX(OFS_CONFIG_ID),0) FROM OFS_CONFIG_MASTER)+1,?,?, ?, ?, ? "+values+")";
			//args=new String[5];
			args[0]=bean.getProductId();
			args[1]=bean.getSchemeId();
			args[2]=bean.getContentId();
			args[3]=bean.getCoverageId();
			args[4]=StringUtils.isBlank(bean.getSubCoverageId())?"0":bean.getSubCoverageId();
			result=this.mytemplate.update(sql, args);
			LogManager.info("insertConfigDetails Query: "+sql);
			LogManager.info("insertConfigDetails obj[] ==> "+StringUtils.join(args, ","));
		} catch (Exception e) {
			e.printStackTrace();
		}
		LogManager.info("insertConfigDetails - Exit");
		return result;
	}

	public void getFormulaDetails(OfficeBean bean) {
		List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
		try{
			String sql="SELECT FORMULA, REMARKS, STATUS FROM FORMULA_MASTER  WHERE PRODUCT_ID=? AND SCHEME_ID=? AND COVERAGE_ID=? AND BRANCH_CODE=?";
			String args[] = new String[4];
			args[0]=bean.getProductId();
			args[1]=bean.getSchemeId();
			args[2]=bean.getCoverageId();
			args[3]=bean.getBranchCode();
			LogManager.info("getFormulaDetails Query: "+sql);
			LogManager.info("getFormulaDetails obj[] ==> "+StringUtils.join(args, ","));
			list=this.mytemplate.queryForList(sql,args);
			if(list!=null && list.size()>0){
				bean.setFormula(list.get(0).get("FORMULA")==null?"":list.get(0).get("FORMULA").toString());
				bean.setRemarks(list.get(0).get("REMARKS")==null?"":list.get(0).get("REMARKS").toString());
				bean.setStatus(list.get(0).get("STATUS")==null?"":list.get(0).get("STATUS").toString());
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public int insertFormulaDetails(OfficeBean bean) {
		int result=0;
		try{
			String args[] = new String[4];
			args[0]=bean.getProductId();
			args[1]=bean.getSchemeId();
			args[2]=bean.getCoverageId();
			args[3]=bean.getBranchCode();
			String q="SELECT COUNT(*) FROM FORMULA_MASTER  WHERE PRODUCT_ID=? AND SCHEME_ID=? AND COVERAGE_ID=? AND BRANCH_CODE=?";
			int count=this.mytemplate.queryForInt(q, args);
			
			if(count<=0)
			{
				args = new String[7];
				args[0]=bean.getProductId();
				args[1]=bean.getSchemeId();
				args[2]=bean.getCoverageId();
				args[3]=bean.getFormula();
				args[4]=bean.getBranchCode();
				args[5]=bean.getRemarks();
				args[6]=bean.getStatus();
				q="INSERT INTO FORMULA_MASTER VALUES(?,?,?,?,?,?,?)";
			}else
			{
				args = new String[7];
				args[0]=bean.getFormula();
				args[1]=bean.getRemarks();
				args[2]=bean.getStatus();
				args[3]=bean.getProductId();
				args[4]=bean.getSchemeId();
				args[5]=bean.getCoverageId();
				args[6]=bean.getBranchCode();
				q="UPDATE FORMULA_MASTER SET FORMULA=?, REMARKS=?, STATUS=? WHERE PRODUCT_ID=? AND SCHEME_ID=? AND COVERAGE_ID=? AND BRANCH_CODE=?";
			}
			LogManager.info("insertFormulaDetails Query: "+q);
			LogManager.info("insertFormulaDetails obj[] ==> "+StringUtils.join(args, ","));
			result=this.mytemplate.update(q,args);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return result; 
	}

	public List<Map<String, Object>> subCoverageList(OfficeBean bean) {
		List<Map<String,Object>> res=new ArrayList<Map<String,Object>>();
		try {
			String sql="SELECT DISTINCT OCM.COVERAGES_ID, OM.COVERAGES_NAME FROM OFS_COVERAGES_MASTER OCM, OFS_MASTER OM WHERE OCM.COVERAGES_ID = OM.COVERAGES_ID AND PRODUCT_ID = ? AND CONTENT_TYPE_ID = ? AND SCHEME_ID = ? AND OCM.COVERAGES_ID NOT IN('99999')";
			Object[] args=new Object[]{bean.getProductId(),bean.getContentId(),bean.getSchemeId()};
			LogManager.info("subCoverageList Query: "+sql);
			LogManager.info("subCoverageList obj[] ==> "+StringUtils.join(args, ","));
			res=this.mytemplate.queryForList(sql,args);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public List<Map<String, Object>> subCoverageIncludedList(OfficeBean bean) {
		List<Map<String,Object>> res=new ArrayList<Map<String,Object>>();
		try {
			String sql="SELECT COVERAGES_ID,COVERAGES_NAME FROM OFS_MASTER WHERE STATUS='Y' AND COVERAGES_ID IN (SELECT DISTINCT COVERAGES_SUB_ID FROM OFS_COVERAGES_SUB_MASTER WHERE PRODUCT_ID = ? AND CONTENT_TYPE_ID = ? AND SCHEME_ID = ? AND COVERAGES_ID = ?)";
			Object[] args=new Object[]{bean.getProductId(),bean.getContentId(),bean.getSchemeId(),bean.getCoverageId()};
			LogManager.info("subCoverageIncludedList Query: "+sql);
			LogManager.info("subCoverageIncludedList obj[] ==> "+StringUtils.join(args, ","));
			res=this.mytemplate.queryForList(sql,args);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public List<Map<String, Object>> subCoverageExcludedList(OfficeBean bean) {
		List<Map<String,Object>> res=new ArrayList<Map<String,Object>>();
		try {
			String sql="SELECT COVERAGES_ID,COVERAGES_NAME FROM OFS_MASTER WHERE STATUS='Y' AND COVERAGES_ID NOT IN(SELECT DISTINCT COVERAGES_ID FROM OFS_COVERAGES_MASTER WHERE PRODUCT_ID = ? AND CONTENT_TYPE_ID = ? AND SCHEME_ID = ? UNION ALL SELECT DISTINCT COVERAGES_SUB_ID FROM OFS_COVERAGES_SUB_MASTER WHERE PRODUCT_ID = ? AND CONTENT_TYPE_ID =? AND SCHEME_ID =? AND COVERAGES_ID = ? UNION ALL SELECT 99999 AS COVERAGES_ID FROM DUAL) ORDER BY COVERAGES_ID";
			Object[] args=new Object[]{bean.getProductId(),bean.getContentId(),bean.getSchemeId(),bean.getProductId(),bean.getContentId(),bean.getSchemeId(),bean.getCoverageId()};
			LogManager.info("subCoverageExcludedList Query: "+sql);
			LogManager.info("subCoverageExcludedList obj[] ==> "+StringUtils.join(args, ","));
			res=this.mytemplate.queryForList(sql,args);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public List<Map<String, Object>> subDisplayOrderList(OfficeBean bean) {List<Map<String, Object>> list=new ArrayList<Map<String, Object>>();
	Map<String, Object> listAdd=new HashMap<String, Object>();
		try {
			sql=getQuery("GET_SUB_DISPLAY_ORDER_LIST");
			args=new Object[]{bean.getProductId(),bean.getContentId(),bean.getSchemeId(),bean.getCoverageId(),bean.getProductId(),bean.getContentId(),bean.getSchemeId(),bean.getCoverageId()};
			LogManager.info("subDisplayOrderList Query=>"+sql);
			LogManager.info("subDisplayOrderList obj[]=>"+StringUtils.join(args, ", "));
			list=this.mytemplate.queryForList(sql,args);
			if(list.size()<=0)
				listAdd.put("SUB_DISPLAY_ORDER","1");
			else
				listAdd.put("SUB_DISPLAY_ORDER",list.size()+1);
			
			list.add(listAdd);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public void setSubCoverageEditDtls(OfficeBean bean) {
		List<Map<String, Object>> list=new ArrayList<Map<String, Object>>();
		try {
			sql=getQuery("GET_SUB_COVERAGE_EDIT_DETAILS");
			args=new Object[]{bean.getProductId(),bean.getCoverageId(),bean.getSchemeId(),bean.getContentId(),bean.getSubCoverageId()};
			LogManager.info("setSubCoverageEditDtls Query=>"+sql);
			LogManager.info("setSubCoverageEditDtls obj[] ==> "+StringUtils.join(args, ","));
			list=this.mytemplate.queryForList(sql,args);
			if(list!=null && list.size()>0){
				bean.setCoverageLimit(list.get(0).get("SUB_COVERAGES_LIMIT")==null?"":list.get(0).get("SUB_COVERAGES_LIMIT").toString());
				bean.setSubRate(list.get(0).get("SUB_RATE")==null?"":list.get(0).get("SUB_RATE").toString());
				bean.setControlType(list.get(0).get("SUB_CONTROL_TYPE")==null?"":list.get(0).get("SUB_CONTROL_TYPE").toString());
				bean.setDisplayOrder(list.get(0).get("SUB_DISPLAY_ORDER")==null?"":list.get(0).get("SUB_DISPLAY_ORDER").toString());
				bean.setStatus(list.get(0).get("STATUS")==null?"":list.get(0).get("STATUS").toString());
				bean.setCalculationType(list.get(0).get("CALC_TYPE")==null?"":list.get(0).get("CALC_TYPE").toString());
				bean.setEffectiveDate(list.get(0).get("EFFECTIVE_DATE")==null?"":list.get(0).get("EFFECTIVE_DATE").toString());
				bean.setRsaCode(list.get(0).get("RSACODE")==null?"":list.get(0).get("RSACODE").toString());
				bean.setSubCoverageLimit(list.get(0).get("SUM_INSURED_LIMIT")==null?"":list.get(0).get("SUM_INSURED_LIMIT").toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int updateSubCoverageDtl(OfficeBean bean) {
		int result=0;
		String temp = "sysdate";
		temp = runner.getSysdate("01");		
		String amend = new String();
		amend = maxAmendID(bean.getProductId(), bean.getCoverageId(), bean.getSchemeId(), bean.getContentId(),bean.getSubCoverageId());
		int amendID;

		try {
			amendID = Integer.parseInt(amend);
		} catch (Exception e) {
			amendID = 0;
		}

		if (amendID >= 0) {
			if (isGreaterEntryDate(bean,amendID,"subCover")) {
				String updateQuery = "update OFS_COVERAGES_SUB_MASTER set EXPIRY_DATE = "+temp+" where AMEND_ID='"
						+ amendID
						+ "' and PRODUCT_ID = "
						+ bean.getProductId()
						+ " and COVERAGES_ID = "
						+ bean.getCoverageId()
						+ " and SCHEME_ID = "
						+ bean.getSchemeId()
						+ " and CONTENT_TYPE_ID = "
						+ bean.getContentId()
						+ " and COVERAGES_SUB_ID = " + bean.getSubCoverageId();
				this.mytemplate.update(updateQuery);
			} else {
				String updateQuery = "update OFS_COVERAGES_SUB_MASTER set EXPIRY_DATE =(select to_date('"
						+ bean.getEffectiveDate()
						+ "', 'dd-MM-YYYY')-1 from dual) where AMEND_ID='"
						+ amendID
						+ "' and PRODUCT_ID = "
						+ bean.getProductId()
						+ " and COVERAGES_ID = "
						+ bean.getCoverageId()
						+ " and SCHEME_ID = "
						+ bean.getSchemeId()
						+ " and CONTENT_TYPE_ID = "
						+ bean.getContentId()
						+ " and COVERAGES_SUB_ID = " + bean.getSubCoverageId();
				this.mytemplate.update(updateQuery);
			}

		}

		amendID += 1;

		String query = "insert into OFS_COVERAGES_SUB_MASTER(COVERAGES_SUB_ID, COVERAGES_ID,"
				+ "PRODUCT_ID, SCHEME_ID, CONTENT_TYPE_ID, SUB_COVERAGES_LIMIT,SUB_RATE,"
				+ "SUB_CONTROL_TYPE,SUB_DISPLAY_ORDER,STATUS,CALC_TYPE, AMEND_ID, EFFECTIVE_DATE, ENTRY_DATE,RSACODE,EXPIRY_DATE) values("
				+ bean.getSubCoverageId()
				+ ","
				+ bean.getCoverageId()
				+ ","
				+ bean.getProductId()
				+ ","
				+ bean.getSchemeId()
				+ ","
				+ bean.getContentId()
				+ ",'"
				+ bean.getCoverageLimit()
				+ "',"
				+ bean.getSubRate()
				+ ",'"
				+ bean.getControlType()
				+ "',"
				+ bean.getDisplayOrder()
				+ ",'"
				+ bean.getStatus()
				+ "','"
				+ bean.getCalculationType()
				+ "',"
				+ amendID
				+ " ,to_date('"
				+ bean.getEffectiveDate()
				+ "','dd-MM-YYYY')"
				+ ", (select "+temp+" FROM dual),'" + bean.getRsaCode() + "',to_date('"
				+ bean.getEffectiveDate()
				+ "','dd-MM-YYYY')+1095 )";
		result=this.mytemplate.update(query);

		String updateQuery = "";
        String values[]=new String[4];
		
		if (bean.getStatus().equalsIgnoreCase("Y")) {
			values[0]=bean.getProductId();
		    values[1]=bean.getCoverageId();
		    values[2]=bean.getSchemeId();
		    values[3]=bean.getContentId();
			updateQuery = "update OFS_COVERAGES_MASTER set SUB_COVERAGES = 'Y' where "
					+ " PRODUCT_ID =? and COVERAGES_ID=? and SCHEME_ID =? and CONTENT_TYPE_ID = ?";
			this.mytemplate.update(updateQuery,values);
		} else {
			values[0]=bean.getProductId();
		    values[1]=bean.getContentId();
		    values[2]=bean.getSchemeId();
		    values[3]=bean.getCoverageId();
			String[][] list = new String[0][0];
			String selectQuery = "select COVERAGES_SUB_ID from OFS_COVERAGES_SUB_MASTER where PRODUCT_ID =? and CONTENT_TYPE_ID = ? and SCHEME_ID = ? and COVERAGES_ID = ? and status = 'Y'";
			list = runner.multipleSelection(selectQuery,values);
			if (list.length == 0) {
				values[0]=bean.getProductId();
		        values[1]=bean.getCoverageId();
		        values[2]=bean.getSchemeId();
		        values[3]=bean.getContentId();
				updateQuery = "update OFS_COVERAGES_MASTER set SUB_COVERAGES = 'N' where "
						+ " PRODUCT_ID =? and COVERAGES_ID=? and SCHEME_ID=? and CONTENT_TYPE_ID = ?";
				this.mytemplate.update(updateQuery,values);
			}
		}
		return result;
	}

	public List<Map<String, Object>> fieldIncludedList(OfficeBean bean) {
		List<Map<String,Object>> res=new ArrayList<Map<String,Object>>();
		try {
			String sql="SELECT OM.COVERAGES_ID,OM.COVERAGES_NAME,OCM.DISPLAY_ORDER,OCM.SUM_INSURED_CONTROL_TYPE,OCM.CONTROL_TYPE FROM OFS_MASTER OM, OFS_COVERAGES_COMMON_DTL OCM WHERE OM.STATUS='Y' AND OM.COVERAGES_ID=OCM.COVERAGES_ID AND OM.COVERAGES_ID IN (SELECT DISTINCT COVERAGES_ID FROM OFS_COVERAGES_COMMON_DTL WHERE PRODUCT_ID = ? AND CONTENT_TYPE_ID = ? AND SCHEME_ID = ? AND COVERAGES_ID NOT IN ('99999')) ORDER BY OCM.DISPLAY_ORDER";
			Object[] args=new Object[]{bean.getProductId(),bean.getContentId(),bean.getSchemeId()};
			LogManager.info("fieldIncludedList Query: "+sql);
			LogManager.info("fieldIncludedList obj[] ==> "+StringUtils.join(args, ","));
			res=this.mytemplate.queryForList(sql,args);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public List<Map<String, Object>> getBrokerLinkedList() {
		List<Map<String,Object>> res=new ArrayList<Map<String,Object>>();
		try {
			String sql="SELECT AGENCY_CODE,COMPANY_NAME FROM BROKER_COMPANY_MASTER WHERE STATUS='Y'";
			LogManager.info("getBrokerLinkedList Query: "+sql);
			res=this.mytemplate.queryForList(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public List<Map<String, Object>> fieldExcludedList(OfficeBean bean) {
		List<Map<String,Object>> res=new ArrayList<Map<String,Object>>();
		try {
			String sql="SELECT COVERAGES_ID,COVERAGES_NAME FROM OFS_MASTER WHERE STATUS='Y' AND COVERAGES_ID NOT IN (SELECT DISTINCT COVERAGES_ID FROM OFS_COVERAGES_COMMON_DTL WHERE PRODUCT_ID = ? AND CONTENT_TYPE_ID = ? AND SCHEME_ID = ?  UNION ALL SELECT 99999 AS COVERAGES_ID FROM DUAL) ORDER BY COVERAGES_ID";
			Object[] args=new Object[]{bean.getProductId(),bean.getContentId(),bean.getSchemeId()};
			LogManager.info("fieldExcludedList Query: "+sql);
			LogManager.info("fieldExcludedList obj[] ==> "+StringUtils.join(args, ","));
			res=this.mytemplate.queryForList(sql,args);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public List<Map<String, Object>> displayOrderListNew(OfficeBean bean) {
		List<Map<String, Object>> list=new ArrayList<Map<String, Object>>();
		Map<String, Object> listAdd=new HashMap<String, Object>();
		try {
			//sql=getQuery("GET_DISPLAY_ORDER_LIST");
			sql="SELECT DISTINCT DISPLAY_ORDER FROM OFS_COVERAGES_COMMON_DTL WHERE AMEND_ID||COVERAGES_ID IN(SELECT MAX(AMEND_ID)||COVERAGES_ID FROM OFS_COVERAGES_COMMON_DTL WHERE PRODUCT_ID = ? AND CONTENT_TYPE_ID = ? AND SCHEME_ID = ? GROUP BY COVERAGES_ID) AND PRODUCT_ID = ? AND CONTENT_TYPE_ID = ? AND SCHEME_ID = ? ORDER BY DISPLAY_ORDER";
			args=new Object[]{bean.getProductId(),bean.getContentId(),bean.getSchemeId(),bean.getProductId(),bean.getContentId(),bean.getSchemeId()};
			LogManager.info("displayOrderListNew Query=>"+sql);
			LogManager.info("displayOrderListNew obj[]=>"+StringUtils.join(args, ", "));
			list=this.mytemplate.queryForList(sql,args);
			if(list.size()<=0)
				listAdd.put("DISPLAY_ORDER","1");
			else
				listAdd.put("DISPLAY_ORDER",list.size()+1);
			
			list.add(listAdd);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public int insertFieldDetails(OfficeBean bean) {
		int res=0;
		try {
			
			String helpID = "";
			if("add".equalsIgnoreCase(bean.getMode())){
				helpID = maxOfHelpID();
				String helpQuery = "insert into OFS_HELP_MASTER(HELP_CONTENTS_ID,HELP_DESCRIPTION,STATUS) values"
						+ "(" + helpID + ",'" + bean.getHelpContent() + "','Y' )";
				this.mytemplate.update(helpQuery);
				
				//sql=getQuery("GET_INSERT_COVERAGE_DTL");
				sql="INSERT INTO OFS_COVERAGES_COMMON_DTL (PRODUCT_ID, COVERAGES_ID, SCHEME_ID, CONTENT_TYPE_ID,DISPLAY_ORDER,"
						+ " CONTROL_TYPE, SUM_INSURED_CONTROL_TYPE, HELP_CONTENTS_ID, UPLOAD_OPTION,RSACODE,ENTRY_DATE, AMEND_ID,"
						+ " COVERAGES_TYPE, SUM_INSURED_LIMIT, MIN_SUM_INSURED, COVERAGES_LIMIT, EXCESS, BASE_RATE, CALC_STATUS, CALC_TYPE, STATUS)"
						+ " VALUES (?,?,?,?,?,?,?,?,?,?,SYSDATE,'0',"
						+ "?,?,?,?,?,?,?,?,?)";
				args=new Object[]{bean.getProductId(), bean.getFieldName(), bean.getSchemeId(),
						bean.getContentId(),bean.getDisplayOrder(),bean.getControlType(),
						bean.getSumControlType(),helpID,bean.getUploadOption(),bean.getRsaCode(),
						bean.getCoverageType(), bean.getSumCoverageLimit(), bean.getMinSumLimit(), bean.getCoverageLimit(),
						bean.getExcess(), bean.getBaseRate(), bean.getCalculationStatus(), bean.getCalculationType(), bean.getStatus()};
				LogManager.info("insertFieldDetails Query=>"+sql);
				LogManager.info("insertFieldDetails obj[] ==> "+StringUtils.join(args, ","));
				res=this.mytemplate.update(sql,args);
			}
			else if("edit".equalsIgnoreCase(bean.getMode())){
				sql="select HELP_CONTENTS_ID from OFS_COVERAGES_COMMON_DTL where PRODUCT_ID = ? and COVERAGES_ID = ? and SCHEME_ID = ? and CONTENT_TYPE_ID = ?";
				args=new Object[]{bean.getProductId(), bean.getCoverageId(), bean.getSchemeId(), bean.getContentId()};
				List<Map<String,Object>> list=this.mytemplate.queryForList(sql,args);
				if (list!=null && list.size()>0) {
					helpID = list.get(0).get("HELP_CONTENTS_ID")==null?"":list.get(0).get("HELP_CONTENTS_ID").toString();
					
					String updateQuery = "update OFS_HELP_MASTER set HELP_DESCRIPTION ='"
						+ bean.getHelpContent() + "' where HELP_CONTENTS_ID =" +helpID ;
				this.mytemplate.update(updateQuery);
				}
				
				
				//sql=getQuery("GET_UPDATE_COVERAGE_DTL");
				sql="UPDATE OFS_COVERAGES_COMMON_DTL SET DISPLAY_ORDER=?,CONTROL_TYPE=?, SUM_INSURED_CONTROL_TYPE=?,"
						+ " HELP_CONTENTS_ID=?,UPLOAD_OPTION=?,RSACODE=?,"
						+ " COVERAGES_TYPE = ?, SUM_INSURED_LIMIT = ?, MIN_SUM_INSURED = ?, COVERAGES_LIMIT = ?, EXCESS = ?,"
						+ " BASE_RATE = ?, CALC_STATUS = ?, CALC_TYPE = ?, STATUS = ?"
						+ " WHERE PRODUCT_ID=? AND COVERAGES_ID=?"
						+ " AND SCHEME_ID=? AND CONTENT_TYPE_ID=?";
				args=new Object[]{bean.getDisplayOrder(),bean.getControlType(),bean.getSumControlType(),helpID,bean.getUploadOption(),
						bean.getRsaCode(),bean.getCoverageType(), bean.getSumCoverageLimit(), bean.getMinSumLimit(), bean.getCoverageLimit(),
						bean.getExcess(), bean.getBaseRate(), bean.getCalculationStatus(), bean.getCalculationType(),bean.getStatus(), bean.getProductId(),
						bean.getCoverageId(), bean.getSchemeId(), bean.getContentId()};
				
				/*String qry="UPDATE OFS_COVERAGES_MASTER OCM SET DISPLAY_ORDER = ?, CONTROL_TYPE = ?, SUM_INSURED_CONTROL_TYPE = ?, HELP_CONTENTS_ID = ? ,UPLOAD_OPTION=?,RSACODE=? WHERE PRODUCT_ID = ? AND COVERAGES_ID = ? AND SCHEME_ID = ? AND CONTENT_TYPE_ID = ? AND AMEND_ID=(SELECT MAX(AMEND_ID) FROM OFS_COVERAGES_MASTER WHERE COVERAGES_ID=OCM.COVERAGES_ID AND SCHEME_ID=OCM.SCHEME_ID AND CONTENT_TYPE_ID=OCM.CONTENT_TYPE_ID AND AGENCY_CODE=OCM.AGENCY_CODE)";
				Object[] argsNew=new Object[]{bean.getDisplayOrder(),bean.getControlType(),bean.getSumControlType(),helpID,bean.getUploadOption(),bean.getRsaCode(),bean.getProductId(), bean.getCoverageId(), bean.getSchemeId(), bean.getContentId()};
				this.mytemplate.update(qry,argsNew);*/
			
				LogManager.info("insertFieldDetails Query=>"+sql);
				LogManager.info("insertFieldDetails obj[] ==> "+StringUtils.join(args, ","));
				res=this.mytemplate.update(sql,args);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	
	}

	public void setCoverageEditDtlsNew(OfficeBean bean) {
		List<Map<String, Object>> list=new ArrayList<Map<String, Object>>();
		try {
			//sql=getQuery("GET_COVERAGE_EDIT_DETAILS_NEW");
			sql="SELECT DISPLAY_ORDER, CONTROL_TYPE, HELP_CONTENTS_ID,"
					+ "(SELECT HELP_DESCRIPTION FROM OFS_HELP_MASTER WHERE HELP_CONTENTS_ID = OCM.HELP_CONTENTS_ID) HELP_DESCRIPTION, SUM_INSURED_CONTROL_TYPE,"
					+ " COVERAGES_TYPE, SUM_INSURED_LIMIT, MIN_SUM_INSURED, COVERAGES_LIMIT, EXCESS, BASE_RATE, CALC_STATUS, CALC_TYPE,STATUS,"
					+ " UPLOAD_OPTION,RSACODE FROM OFS_COVERAGES_COMMON_DTL OCM WHERE PRODUCT_ID = ? AND COVERAGES_ID = ? AND SCHEME_ID = ? AND CONTENT_TYPE_ID = ?"
					+ " AND AMEND_ID = (SELECT NVL (MAX (AMEND_ID), 0) FROM OFS_COVERAGES_COMMON_DTL WHERE PRODUCT_ID = OCM.PRODUCT_ID"
					+ " AND COVERAGES_ID = OCM.COVERAGES_ID AND SCHEME_ID = OCM.SCHEME_ID AND CONTENT_TYPE_ID = OCM.CONTENT_TYPE_ID)";
			args=new Object[]{bean.getProductId(),bean.getCoverageId(),bean.getSchemeId(),bean.getContentId()};
			LogManager.info("setCoverageEditDtlsNew Query=>"+sql);
			LogManager.info("setCoverageEditDtlsNew obj[] ==> "+StringUtils.join(args, ","));
			list=this.mytemplate.queryForList(sql,args);
			if(list!=null && list.size()>0){
				bean.setDisplayOrder(list.get(0).get("DISPLAY_ORDER")==null?"":list.get(0).get("DISPLAY_ORDER").toString());
				bean.setSumControlType(list.get(0).get("SUM_INSURED_CONTROL_TYPE")==null?"":list.get(0).get("SUM_INSURED_CONTROL_TYPE").toString());
				bean.setControlType(list.get(0).get("CONTROL_TYPE")==null?"":list.get(0).get("CONTROL_TYPE").toString());
				bean.setHelpContent(list.get(0).get("HELP_DESCRIPTION")==null?"":list.get(0).get("HELP_DESCRIPTION").toString());
				bean.setUploadOption(list.get(0).get("UPLOAD_OPTION")==null?"":list.get(0).get("UPLOAD_OPTION").toString());
				bean.setRsaCode(list.get(0).get("RSACODE")==null?"":list.get(0).get("RSACODE").toString());
				
				bean.setCoverageType(list.get(0).get("COVERAGES_TYPE")==null?"":list.get(0).get("COVERAGES_TYPE").toString());
				bean.setSumCoverageLimit(list.get(0).get("SUM_INSURED_LIMIT")==null?"":list.get(0).get("SUM_INSURED_LIMIT").toString());
				bean.setMinSumLimit(list.get(0).get("MIN_SUM_INSURED")==null?"":list.get(0).get("MIN_SUM_INSURED").toString());
				bean.setCoverageLimit(list.get(0).get("COVERAGES_LIMIT")==null?"":list.get(0).get("COVERAGES_LIMIT").toString());
				bean.setExcess(list.get(0).get("EXCESS")==null?"":list.get(0).get("EXCESS").toString());
				bean.setBaseRate(list.get(0).get("BASE_RATE")==null?"":list.get(0).get("BASE_RATE").toString());
				bean.setCalculationStatus(list.get(0).get("CALC_STATUS")==null?"":list.get(0).get("CALC_STATUS").toString());
				bean.setCalculationType(list.get(0).get("CALC_TYPE")==null?"":list.get(0).get("CALC_TYPE").toString());
				bean.setStatus(list.get(0).get("STATUS")==null?"":list.get(0).get("STATUS").toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public List<Map<String, Object>> getBrokerLinkedFieldList(OfficeBean bean) {
		List<Map<String,Object>> res=new ArrayList<Map<String,Object>>();
		try {
			String sql="SELECT DISTINCT OCC.COVERAGES_ID, OM.COVERAGES_NAME, OCC.DISPLAY_ORDER,OCC.SUM_INSURED_CONTROL_TYPE,OCC.CONTROL_TYPE,OCC.HELP_CONTENTS_ID,DECODE(OCC.UPLOAD_OPTION,'Y','Yes','N','No') UPLOAD_OPTION_YN,OCC.UPLOAD_OPTION,OCC.RSACODE, CASE WHEN OCM.COVERAGES_ID IS NOT NULL THEN 'Y' ELSE 'N' END AS STATUS FROM OFS_MASTER OM INNER JOIN OFS_COVERAGES_COMMON_DTL OCC ON OM.COVERAGES_ID=OCC.COVERAGES_ID LEFT OUTER JOIN(SELECT DISTINCT COVERAGES_ID FROM OFS_COVERAGES_MASTER WHERE AGENCY_CODE=? AND PRODUCT_ID = ? AND CONTENT_TYPE_ID = ? AND SCHEME_ID=? AND COVERAGES_ID NOT IN ('99999')) OCM ON OCC.COVERAGES_ID = OCM.COVERAGES_ID WHERE PRODUCT_ID = ? AND CONTENT_TYPE_ID = ? AND SCHEME_ID=? ORDER BY OCC.COVERAGES_ID";
			Object[] args=new Object[]{bean.getBrokerLinked(),bean.getProductId(),bean.getContentId(),bean.getSchemeId(),bean.getProductId(),bean.getContentId(),bean.getSchemeId()};
			LogManager.info("getBrokerLinkedFieldList Query: "+sql);
			LogManager.info("getBrokerLinkedFieldList obj[] ==> "+StringUtils.join(args, ","));
			res=this.mytemplate.queryForList(sql,args);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
	
	public void setCoverageEditDtlsBroker(OfficeBean bean) {
		List<Map<String, Object>> list=new ArrayList<Map<String, Object>>();
		try {
			//sql=getQuery("GET_COVERAGE_EDIT_DETAILS");
			sql="SELECT COVERAGES_TYPE, UPLOAD_OPTION, DISPLAY_ORDER, CONTROL_TYPE, COVERAGES_LIMIT, BASE_RATE, HELP_CONTENTS_ID,(SELECT HELP_DESCRIPTION FROM OFS_HELP_MASTER WHERE HELP_CONTENTS_ID=OCM.HELP_CONTENTS_ID) HELP_DESCRIPTION, STATUS, EXCESS, CALC_STATUS, CALC_TYPE, TO_CHAR(EFFECTIVE_DATE,'DD/MM/YYYY') EFFECTIVE_DATE, RSACODE, SUM_INSURED_LIMIT, SUM_INSURED_CONTROL_TYPE, MIN_SUM_INSURED FROM OFS_COVERAGES_MASTER OCM WHERE PRODUCT_ID = ? AND COVERAGES_ID = ? AND SCHEME_ID = ? AND CONTENT_TYPE_ID = ? AND AGENCY_CODE=? AND AMEND_ID = (SELECT NVL(MAX(AMEND_ID), -1) FROM OFS_COVERAGES_MASTER WHERE PRODUCT_ID = OCM.PRODUCT_ID AND COVERAGES_ID =OCM.COVERAGES_ID AND SCHEME_ID = OCM.SCHEME_ID AND CONTENT_TYPE_ID = OCM.CONTENT_TYPE_ID AND AGENCY_CODE=OCM.AGENCY_CODE)";
			args=new Object[]{bean.getProductId(),bean.getCoverageId(),bean.getSchemeId(),bean.getContentId(),bean.getBrokerLinked()};
			LogManager.info("setCoverageEditDtlsBroker Query=>"+sql);
			LogManager.info("setCoverageEditDtlsBroker obj[] ==> "+StringUtils.join(args, ","));
			list=this.mytemplate.queryForList(sql,args);
			if(list!=null && list.size()>0){
				bean.setCoverageType(list.get(0).get("COVERAGES_TYPE")==null?"":list.get(0).get("COVERAGES_TYPE").toString());
				//bean.setUploadOption(list.get(0).get("UPLOAD_OPTION")==null?"":list.get(0).get("UPLOAD_OPTION").toString());
				//bean.setDisplayOrder(list.get(0).get("DISPLAY_ORDER")==null?"":list.get(0).get("DISPLAY_ORDER").toString());
				//bean.setSumControlType(list.get(0).get("SUM_INSURED_CONTROL_TYPE")==null?"":list.get(0).get("SUM_INSURED_CONTROL_TYPE").toString());
				bean.setSumCoverageLimit(list.get(0).get("SUM_INSURED_LIMIT")==null?"":list.get(0).get("SUM_INSURED_LIMIT").toString());
				bean.setMinSumLimit(list.get(0).get("MIN_SUM_INSURED")==null?"":list.get(0).get("MIN_SUM_INSURED").toString());
				//bean.setControlType(list.get(0).get("CONTROL_TYPE")==null?"":list.get(0).get("CONTROL_TYPE").toString());
				bean.setCoverageLimit(list.get(0).get("COVERAGES_LIMIT")==null?"":list.get(0).get("COVERAGES_LIMIT").toString());
				bean.setExcess(list.get(0).get("EXCESS")==null?"":list.get(0).get("EXCESS").toString());
				bean.setBaseRate(list.get(0).get("BASE_RATE")==null?"":list.get(0).get("BASE_RATE").toString());
				bean.setCalculationStatus(list.get(0).get("CALC_STATUS")==null?"":list.get(0).get("CALC_STATUS").toString());
				bean.setCalculationType(list.get(0).get("CALC_TYPE")==null?"":list.get(0).get("CALC_TYPE").toString());
				bean.setStatus(list.get(0).get("STATUS")==null?"":list.get(0).get("STATUS").toString());
				bean.setEffectiveDate(list.get(0).get("EFFECTIVE_DATE")==null?"":list.get(0).get("EFFECTIVE_DATE").toString());
				//bean.setRsaCode(list.get(0).get("RSACODE")==null?"":list.get(0).get("RSACODE").toString());
				//bean.setHelpContent(list.get(0).get("HELP_DESCRIPTION")==null?"":list.get(0).get("HELP_DESCRIPTION").toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public int updateFieldDetails(OfficeBean bean) {
		int res=0;
		try {
			String temp = "sysdate";
			String amend = new String();
			amend = maxAmendIDNew(bean.getProductId(), bean.getCoverageId(), bean.getSchemeId(), bean.getContentId(),bean.getBrokerLinked());

			//String[][] help = new String[0][0];
			//help = selectValue(bean.getProductId(), bean.getCoverageId(), bean.getSchemeId(), bean.getContentId());

			/*String helpID = "";
			if (help.length > 0) {
				helpID = help[0][6];
				String updateQuery = "update OFS_HELP_MASTER set HELP_DESCRIPTION ='"
						+ bean.getHelpContent() + "' where HELP_CONTENTS_ID =" + helpID;
				this.mytemplate.update(updateQuery);
			} else {
				helpID = maxOfHelpID();
				String helpQuery = "insert into OFS_HELP_MASTER(HELP_CONTENTS_ID,HELP_DESCRIPTION,STATUS) values"
						+ "(" + helpID + ",'" + bean.getHelpContent() + "','Y' )";
				this.mytemplate.update(helpQuery);
			}*/
			int amendID;

			try {
				amendID = Integer.parseInt(amend);
			} catch (Exception e) {
				amendID = -1;
			}

			if (amendID >= 0) {
				if (isGreaterEntryDateNew(bean,amendID,"cover")) {
					String updateQuery = "update OFS_COVERAGES_MASTER set EXPIRY_DATE = "+temp+" where AMEND_ID='"
							+ amendID
							+ "' and PRODUCT_ID = "
							+ bean.getProductId()
							+ " and COVERAGES_ID = "
							+ bean.getCoverageId()
							+ " and SCHEME_ID = "
							+ bean.getSchemeId()
							+ " and CONTENT_TYPE_ID = " + bean.getContentId()
							+ " and AGENCY_CODE = " + bean.getBrokerLinked();
					System.out.println("Update Query : " + updateQuery);
					res=this.mytemplate.update(updateQuery);
				} else {
					String updateQuery = "update OFS_COVERAGES_MASTER set EXPIRY_DATE =(select to_date('"
							+ bean.getEffectiveDate()
							+ "', 'dd-MM-YYYY')-1 from dual) where AMEND_ID='"
							+ amendID
							+ "' and PRODUCT_ID = "
							+ bean.getProductId()
							+ " and COVERAGES_ID = "
							+ bean.getCoverageId()
							+ " and SCHEME_ID = "
							+ bean.getSchemeId()
							+ " and CONTENT_TYPE_ID = " + bean.getContentId()
							+ " and AGENCY_CODE = " + bean.getBrokerLinked();
					System.out.println("Update Query : " + updateQuery);
					res=this.mytemplate.update(updateQuery);
				}
			}
			amendID += 1;

			String result = new String("0");
			int countSts=0;
			result = runner.singleSelection("select count(*) from OFS_COVERAGES_SUB_MASTER WHERE PRODUCT_ID = "
							+ bean.getProductId()
							+ " and COVERAGES_ID = "
							+ bean.getCoverageId()
							+ " and SCHEME_ID = "
							+ bean.getSchemeId()
							+ " and CONTENT_TYPE_ID = " + bean.getContentId() );
			try{
			 if(result!=null)
			       countSts=Integer.parseInt(result);
				}catch(Exception e){ System.out.println("Status"+e);}

			String query = "insert into OFS_COVERAGES_MASTER(PRODUCT_ID, COVERAGES_ID, "
					+ "SCHEME_ID,CONTENT_TYPE_ID,COVERAGES_TYPE, UPLOAD_OPTION, DISPLAY_ORDER, "
					+ "CONTROL_TYPE,COVERAGES_LIMIT, BASE_RATE, HELP_CONTENTS_ID,"
					+ "STATUS,EXCESS,CALC_STATUS,CALC_TYPE,AMEND_ID,EFFECTIVE_DATE,ENTRY_DATE,RSACODE,"
					+ "SUB_COVERAGES,SUM_INSURED_LIMIT,SUM_INSURED_CONTROL_TYPE,MIN_SUM_INSURED,MULTIPLE_ROWS,EXPIRY_DATE,AGENCY_CODE) values("
					+ bean.getProductId()
					+ ","
					+ bean.getCoverageId()
					+ ","
					+ bean.getSchemeId()
					+ ","
					+ bean.getContentId()
					+ ",'"
					+ bean.getCoverageType()
					+ "','"
					+ bean.getUploadOption()
					+ "',"
					+ bean.getDisplayOrder()
					+ ",'"
					+ bean.getControlType()
					+ "','"
					+ bean.getCoverageLimit()
					+ "',"
					+ bean.getBaseRate()
					+ ","
					+ bean.getHelpContent()//helpID
					+ ",'"
					+ bean.getStatus()
					+ "','"
					+ bean.getExcess()
					+ "','"
					+ bean.getCalculationStatus()
					+ "','"
					+ bean.getCalculationType()
					+ "',"
					+ amendID
					+ " ,to_date('"
					+ bean.getEffectiveDate()
					+ "','dd-MM-YYYY')"
					+ ", (select "+temp+" FROM dual),'"
					+ bean.getRsaCode()
					+ (countSts>0?"','Y',":"','N',")
					+ (bean.getSumCoverageLimit().length()<=0?"0":bean.getSumCoverageLimit())
					+ ",'"
					+ bean.getSumControlType()
					+ "','"
					+(bean.getMinSumLimit().length()<=0?"0":bean.getMinSumLimit()) 
					+ "','"
					+ "M"
					+ "',to_date('"
					+ bean.getEffectiveDate() + "','dd-MM-YYYY')+1095 ,'"
					+ bean.getBrokerLinked()
					+ "')";
			res=this.mytemplate.update(query);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
	
	public boolean isGreaterEntryDateNew (OfficeBean bean, int amendID,String type) {
		String checkValue = "";
		if("cover".equalsIgnoreCase(type)){
			String values[]=new String[7];
				values[0]=bean.getEffectiveDate();
				values[1]=""+amendID;
				values[2]=bean.getProductId();
				values[3]=bean.getCoverageId();
				values[4]=bean.getSchemeId();
				values[5]=bean.getContentId();
				values[6]=bean.getBrokerLinked();
			String selectQuery = "select  to_date(?,'dd-MM-YYYY')-to_date(ENTRY_DATE) from OFS_COVERAGES_MASTER where AMEND_ID=? and PRODUCT_ID = ? and COVERAGES_ID = ? and SCHEME_ID = ? and CONTENT_TYPE_ID = ? and AGENCY_CODE=?" ;
			//System.out.println(selectQuery);
			checkValue = runner.singleSelection(selectQuery,values);
		}
		else{
			String temp = "sysdate";
			temp = runner.getSysdate("01");		
			String values[]=new String[6];
			values[0]=""+amendID;
			values[1]=bean.getProductId();
			values[2]=bean.getCoverageId();
			values[3]=bean.getSchemeId();
			values[4]=bean.getContentId();
			values[5]=bean.getSubCoverageId();
			String selectQuery = "select to_date("+temp+")-to_date(ENTRY_DATE) from OFS_COVERAGES_SUB_MASTER where AMEND_ID=? and PRODUCT_ID =? and COVERAGES_ID = ? and SCHEME_ID = ? and CONTENT_TYPE_ID = ? and COVERAGES_SUB_ID = ?";
			//System.out.println(selectQuery);
			checkValue = runner.singleSelection(selectQuery,values);
		}
		try {
			int day = Integer.parseInt(checkValue);
			if (day == 0) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public String maxAmendIDNew (String pProductID, String pCoverageNameID,String pSchemeID, String pContentTypeID, String pBrokerLinked) {
		String checkValue = "";
		String[] values=new String[5];
		values[0]=pProductID;
		values[1]=pCoverageNameID;
		values[2]=pSchemeID;
		values[3]=pContentTypeID;
		values[4]=pBrokerLinked;
		String checkQuery = "select nvl(max(amend_id), -1) from OFS_COVERAGES_MASTER where PRODUCT_ID = ? and COVERAGES_ID =? and SCHEME_ID = ? and CONTENT_TYPE_ID = ? and AGENCY_CODE=?";
		checkValue = runner.singleSelection(checkQuery,values);
		return checkValue;
	}
	
	public boolean isGreaterEffectiveDateNew(OfficeBean bean, String type) {
		String amend = new String();
		String checkValue = "";
		if("cover".equalsIgnoreCase(type)){
			//amend = maxAmendID(bean.getProductId(), bean.getCoverageId(), bean.getSchemeId(), bean.getContentId());
			amend = maxAmendIDNew(bean.getProductId(), bean.getCoverageId(), bean.getSchemeId(), bean.getContentId(),bean.getBrokerLinked());
			}
		else
			amend = maxAmendID(bean.getProductId(), bean.getCoverageId(), bean.getSchemeId(), bean.getContentId(),bean.getSubCoverageId());
		
		int amendID;
		try {
			amendID = Integer.parseInt(amend);
		} catch (Exception e) {
			amendID = -1;
		}
		if (amendID < 0) {
			return true;
		} else {
			if("cover".equalsIgnoreCase(type)){
				String values[]=new String[7];
				values[0]=bean.getEffectiveDate();
				values[1]=""+amendID;
				values[2]=bean.getProductId();
				values[3]=bean.getCoverageId();
				values[4]=bean.getSchemeId();
				values[5]=bean.getContentId();
				values[6]=bean.getBrokerLinked();
	
				String selectQuery = "select round(to_date(?,'dd-MM-YYYY') -(select EFFECTIVE_DATE from OFS_COVERAGES_MASTER where AMEND_ID=? and PRODUCT_ID = ? and COVERAGES_ID = ? and SCHEME_ID =? and CONTENT_TYPE_ID = ? and AGENCY_CODE=?)) from dual";
				checkValue = runner.singleSelection(selectQuery,values);
			}else{
				String values[]=new String[7];
				values[0]=bean.getEffectiveDate();
				values[1]=""+amendID;
				values[2]=bean.getProductId();
				values[3]=bean.getCoverageId();
				values[4]=bean.getSchemeId();
				values[5]=bean.getContentId();
				values[6]=bean.getSubCoverageId();
				String selectQuery = "select ROUND(to_date(?,'dd-MM-YYYY') -(select EFFECTIVE_DATE from OFS_COVERAGES_SUB_MASTER where AMEND_ID=? and PRODUCT_ID = ? and COVERAGES_ID = ? and SCHEME_ID = ? and CONTENT_TYPE_ID =? and COVERAGES_SUB_ID = ?)) from dual";
				checkValue = runner.singleSelection(selectQuery,values);
			}
			try {
				int day = Integer.parseInt(checkValue);
				if (day >= 0) {
					return true;
				} else {
					return false;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return false;
		}
	}

	public List<Map<String, Object>> getGridDetails(OfficeBean bean) {

		List<Map<String, Object>> res=new ArrayList<Map<String, Object>>();
		try {
			//sql=getQuery("GET_COVERAGE_EDIT_DETAILS_NEW");
			sql="SELECT MINIMUM_PREMIUM, START_SUMINSURED, END_SUMINSURED, BASE_RATE, CALC_TYPE, STATUS, TO_CHAR(ENTRY_DATE,'DD/MM/YYYY') ENTRY_DATE FROM OFS_GRID_MASTER WHERE PRODUCT_ID=? AND SCHEME_ID=? AND CONTENT_TYPE_ID=? AND COVERAGES_ID=? AND COVERAGES_SUB_ID=? AND AGENCY_CODE=? AND COVERAGES_ID || COVERAGES_SUB_ID || AMEND_ID=(SELECT COVERAGES_ID || COVERAGES_SUB_ID || MAX(AMEND_ID) FROM OFS_GRID_MASTER WHERE PRODUCT_ID=? AND SCHEME_ID=? AND CONTENT_TYPE_ID=? AND COVERAGES_ID=? AND COVERAGES_SUB_ID=? AND AGENCY_CODE=? GROUP BY COVERAGES_ID,COVERAGES_SUB_ID)";
			String subcovId=StringUtils.isBlank(bean.getSubCoverageId())?"0":bean.getSubCoverageId();
			args=new Object[]{bean.getProductId(),bean.getSchemeId(),bean.getContentId(),bean.getCoverageId(),subcovId,bean.getBrokerLinked(), bean.getProductId(),bean.getSchemeId(),bean.getContentId(),bean.getCoverageId(),subcovId, bean.getBrokerLinked()};
			LogManager.info("getGridDetails Query=>"+sql);
			LogManager.info("getGridDetails obj[] ==> "+StringUtils.join(args, ","));
			res=this.mytemplate.queryForList(sql,args);
			if(res!=null && res.size()>0){
				
				List<String> startSum=new ArrayList<String>();
				List<String> endSum=new ArrayList<String>();
				List<String> baseRateGrid=new ArrayList<String>();
				List<String> calculationTypeGrid=new ArrayList<String>();
				List<String> statusGrid=new ArrayList<String>();
				List<String> effectiveDateGrid=new ArrayList<String>();
				
				for(int i=0;i<res.size();i++){
					startSum.add(res.get(i).get("START_SUMINSURED")==null?"":res.get(i).get("START_SUMINSURED").toString());
					endSum.add(res.get(i).get("END_SUMINSURED")==null?"":res.get(i).get("END_SUMINSURED").toString());
					baseRateGrid.add(res.get(i).get("BASE_RATE")==null?"":res.get(i).get("BASE_RATE").toString());
					calculationTypeGrid.add(res.get(i).get("CALC_TYPE")==null?"":res.get(i).get("CALC_TYPE").toString());
					statusGrid.add(res.get(i).get("STATUS")==null?"":res.get(i).get("STATUS").toString());
					effectiveDateGrid.add(res.get(i).get("ENTRY_DATE")==null?"":res.get(i).get("ENTRY_DATE").toString());
				}
				bean.setStartSum(startSum);
				bean.setEndSum(endSum);
				bean.setBaseRateGrid(baseRateGrid);
				bean.setCalculationTypeGrid(calculationTypeGrid);
				bean.setStatusGrid(statusGrid);
				bean.setEffectiveDateGrid(effectiveDateGrid);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	
	}
	
	public String getMaxAmendIdGrid(OfficeBean bean)
	{
		String q="select (case when count(*)=0 then 0 else max(amend_id)+1 end) from OFS_GRID_MASTER where product_id=? and scheme_id=? and content_type_id=? and coverages_id=? and coverages_sub_id=?";
		String subcovId=StringUtils.isBlank(bean.getSubCoverageId())?"0":bean.getSubCoverageId();
		String args1[]=new String[5];
		args1[0]=bean.getProductId();
		args1[1]=bean.getSchemeId();
		args1[2]=bean.getContentId();
		args1[3]=bean.getCoverageId();
		args1[4]=subcovId;
		String amendId=runner.singleSelection(q, args1);
		return amendId;
	}

	public int insertGridDetails(OfficeBean bean, String values) {
		LogManager.info("insertGridDetails - Enter");
		int result = 0;
		try {
			String args[]=new String[6];
			String sql="INSERT INTO OFS_GRID_MASTER VALUES(?,?, ?, ?, ? "+values+",?)";
			//args=new String[5];
			args[0]=bean.getProductId();
			args[1]=bean.getSchemeId();
			args[2]=bean.getContentId();
			args[3]=bean.getCoverageId();
			args[4]=StringUtils.isBlank(bean.getSubCoverageId())?"0":bean.getSubCoverageId();
			args[5]=bean.getBrokerLinked();
			LogManager.info("insertGridDetails Query: "+sql);
			LogManager.info("insertGridDetails obj[] ==> "+StringUtils.join(args, ","));
			result=this.mytemplate.update(sql, args);
		} catch (Exception e) {
			e.printStackTrace();
		}
		LogManager.info("insertGridDetails - Exit");
		return result;
	}

	public void setDefaultBrokerCoverageDtls(OfficeBean bean) {
		try {
			String query = "SELECT COVERAGES_TYPE, SUM_INSURED_LIMIT, MIN_SUM_INSURED, COVERAGES_LIMIT,"
					+ " EXCESS, BASE_RATE, CALC_STATUS, CALC_TYPE, TO_CHAR(SYSDATE,'DD/MM/YYYY') EFFECTIVE_DATE, 'Y' STATUS"
					+ " FROM OFS_COVERAGES_COMMON_DTL OCM WHERE PRODUCT_ID = ? AND COVERAGES_ID = ? AND SCHEME_ID = ? AND CONTENT_TYPE_ID = ?"
					+ "AND AMEND_ID = (SELECT NVL(MAX(AMEND_ID), 0) FROM OFS_COVERAGES_COMMON_DTL WHERE PRODUCT_ID = OCM.PRODUCT_ID "
					+ "AND COVERAGES_ID =OCM.COVERAGES_ID AND SCHEME_ID = OCM.SCHEME_ID AND CONTENT_TYPE_ID = OCM.CONTENT_TYPE_ID)";
			args = new Object[] {bean.getProductId(),bean.getCoverageId(),bean.getSchemeId(),bean.getContentId()};
			LogManager.info("Defaul Broker Config Query: "+queryFrammer(query, args));
			List<Map<String, Object>> list = this.mytemplate.queryForList(query, args);
			if(list != null && list.size()>0) {
				Map<String, Object> map = list.get(0);
				if(map != null && map.size()>0) {
					bean.setCoverageType(map.get("COVERAGES_TYPE")==null?"":map.get("COVERAGES_TYPE").toString());
					bean.setSumCoverageLimit(map.get("SUM_INSURED_LIMIT")==null?"":map.get("SUM_INSURED_LIMIT").toString());
					bean.setMinSumLimit(map.get("MIN_SUM_INSURED")==null?"":map.get("MIN_SUM_INSURED").toString());
					bean.setCoverageLimit(map.get("COVERAGES_LIMIT")==null?"":map.get("COVERAGES_LIMIT").toString());
					bean.setExcess(map.get("EXCESS")==null?"":map.get("EXCESS").toString());
					bean.setBaseRate(map.get("BASE_RATE")==null?"":map.get("BASE_RATE").toString());
					bean.setCalculationStatus(map.get("CALC_STATUS")==null?"":map.get("CALC_STATUS").toString());
					bean.setCalculationType(map.get("CALC_TYPE")==null?"":map.get("CALC_TYPE").toString());
					bean.setStatus(map.get("STATUS")==null?"":map.get("STATUS").toString());
					bean.setEffectiveDate(map.get("EFFECTIVE_DATE")==null?"":map.get("EFFECTIVE_DATE").toString());
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	


}
