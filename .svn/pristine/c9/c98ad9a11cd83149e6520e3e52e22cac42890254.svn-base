package com.maan.Home.DAO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;

import com.maan.Home.Controller.HomeBean;
import com.maan.Motor.controller.MotorBean;
import com.maan.common.LogManager;
import com.maan.common.MyJdbcTemplate;
import com.maan.common.dao.CommonDAO;
import com.maan.common.exception.BaseException;
import com.maan.common.util.Formatter;

public class HomeDAO extends MyJdbcTemplate {
	CommonDAO commonDao = new CommonDAO();
	public String getQuoteInfo(HomeBean bean) {
		LogManager.info("getQuote - Enter ");
		String result="SUCCESS";
		String sql="";
		Object[] obj=null;
		int res=0;
		try{
			if(StringUtils.isBlank(bean.getQuoteNo())){
				bean.setApplicationNo(new CommonDAO().getSequenceNo("Application",bean.getProductId(),bean.getBranchCode(),"",""));
				bean.setQuoteNo(new CommonDAO().getSequenceNo("Quote",bean.getProductId(),bean.getBranchCode(),"",""));
			}
			String query = getQuery("CNT_OFS_DATA");
			Object[] args = new Object[]{bean.getQuoteNo()};
			int count = this.mytemplate.queryForInt(query, args);
			if(count==0) {
				sql=getQuery("INS_OFS_DATA");
				obj=new Object[34];
				obj[0] = bean.getQuoteNo()==null?"":bean.getQuoteNo();
				obj[1] = "0";
				obj[2] = bean.getCustomerId()==null?"":bean.getCustomerId();
				obj[3] = "";
				obj[4] = "";
				obj[5] = "";
				obj[6] = "";
				obj[7] = "";
				obj[8] = "0";
				obj[9] = "0";
				obj[10] = "0";
				obj[11] = "";
				obj[12] = "0";
				obj[13] = "0";
				obj[14] = "0";
				obj[15] = "0";
				obj[16] = "0";
				obj[17] = "0";
				obj[18] = bean.getInceptionDt();
				obj[19] = bean.getApplicationNo();
				obj[20] = "Y";
				obj[21] = "";
				obj[22] = "N";
				obj[23] = bean.getExpiryDt();
				obj[24] = "";
				obj[25] = "";
				obj[26] = "N";
				obj[27] = "0";
				obj[28] = "0";
				obj[29] = "0";
				obj[30] = "";
				obj[31] = "";
				obj[32] = bean.getPaCoverYN();
				obj[33] = bean.getTractorYN();

			} else {
				sql=getQuery("UPD_OFS_DATA");
				obj=new Object[34];
				obj[0] = "0";
				obj[1] = bean.getCustomerId()==null?"":bean.getCustomerId();
				obj[2] = "";
				obj[3] = "";
				obj[4] = "";
				obj[5] = "";
				obj[6] = "";
				obj[7] = "0";
				obj[8] = "0";
				obj[9] = "0";
				obj[10] = "";
				obj[11] = "0";
				obj[12] = "0";
				obj[13] = "0";
				obj[14] = "0";
				obj[15] = "0";
				obj[16] = "0";
				obj[17] = bean.getInceptionDt();
				obj[18] = bean.getApplicationNo();
				obj[19] = "Y";
				obj[20] = "";
				obj[21] = "N";
				obj[22] = bean.getExpiryDt();
				obj[23] = "";
				obj[24] = "";
				obj[25] = "N";
				obj[26] = "0";
				obj[27] = "0";
				obj[28] = "0";
				obj[29] = "";
				obj[30] = "";
				obj[31] = bean.getPaCoverYN();
				obj[32] = bean.getTractorYN();
				obj[33] = bean.getQuoteNo();
			}
			removeNull(obj);
			LogManager.info("Query=>"+sql);
			LogManager.info("obj[] ==> "+StringUtils.join(obj, ","));
			res=this.mytemplate.update(sql,obj);
			LogManager.info("Result=>"+res);

			//updateHomeDtls(bean);
		}catch(Exception e){
			LogManager.debug(e);
			result="FAILED";
		}
		LogManager.popRemove();
		LogManager.info("getQuote - Exit");
		return result;
	}

	/*private void updateHomeDtls(HomeBean bean) throws Exception {

		String query = getQuery("CNT_HOME_DTL");
		Object[] args = new Object[]{bean.getQuoteNo()};
		int count = this.mytemplate.queryForInt(query, args);

		String sql = "";
		Object[] obj = null;
		int res = 0;
		if (count == 0) {
			sql = getQuery("INS_HOME_DTL");
			obj = new Object[16];
			obj[0] = bean.getQuoteNo() == null ? "" : bean.getQuoteNo();
			obj[1] = bean.getCustomerId() == null ? "" : bean.getCustomerId();
			obj[2] = bean.getLoginId();
			obj[3] = bean.getProductId();
			obj[4] = "";
			obj[5] = "0";
			obj[6] = bean.getInceptionDt();
			obj[7] = bean.getExpiryDt();
			obj[8] = bean.getApplicationNo();
			obj[9] = "Y";
			obj[10] = bean.getSchemeId();
			obj[11] = bean.getContentTypeId();
			obj[12] = (StringUtils.isBlank(bean.getIssuer()) ? "1" : bean.getIssuer());
			obj[13] = bean.getBrokerCode();
			obj[14] = bean.getExecutive();
			obj[15] = bean.getBranchCode();
		} else {
			sql = getQuery("UPD_HOME_DTL");
			obj = new Object[16];
			obj[0] = bean.getCustomerId() == null ? "" : bean.getCustomerId();
			obj[1] = bean.getLoginId();
			obj[2] = bean.getProductId();
			obj[3] = "";
			obj[4] = "0";
			obj[5] = bean.getInceptionDt();
			obj[6] = bean.getExpiryDt();
			obj[7] = bean.getApplicationNo();
			obj[8] = "Y";
			obj[9] = bean.getSchemeId();
			obj[10] = bean.getContentTypeId();
			obj[11] = (StringUtils.isBlank(bean.getIssuer()) ? "1" : bean.getIssuer());
			obj[12] = bean.getBrokerCode();
			obj[13] = bean.getExecutive();
			obj[14] = bean.getBranchCode(); 
			obj[15] = bean.getQuoteNo();
		}
		removeNull(obj);
		LogManager.info("Query=>"+sql);
		LogManager.info("obj[] ==> "+StringUtils.join(obj, ","));
		res=this.mytemplate.update(sql,obj);
		LogManager.info("Result=>"+res);
	}*/
	
	public void getOFSData(HomeBean bean) {
		try {
			if(StringUtils.isNotBlank(bean.getQuoteNo())) {
				String query = getQuery("GET_OFS_DATA");
				Object[] args = new Object[]{bean.getQuoteNo()};
				Map<String,Object> resultMap = this.mytemplate.queryForMap(query, args);
				bean.setCustomerId(resultMap.get("CUSTOMER_ID")==null?"":resultMap.get("CUSTOMER_ID").toString());
				bean.setPaCoverYN(resultMap.get("PACOVER_YN")==null?"":resultMap.get("PACOVER_YN").toString());
				bean.setTractorYN(resultMap.get("TRACTER_YN")==null?"":resultMap.get("TRACTER_YN").toString());
				bean.setInceptionDt(resultMap.get("INCEPTION_DATE")==null?"":resultMap.get("INCEPTION_DATE").toString());
				bean.setExpiryDt(resultMap.get("EXPIRY_DATE")==null?"":resultMap.get("EXPIRY_DATE").toString());
			}
		}
		catch(Exception exception) {
			LogManager.debug(exception);
		}
	}

	public List<Map<String,Object>> getHomeInfo(HomeBean bean) {
		LogManager.info("getHomeInfo - Enter");
		List<Map<String, Object>> list=null;		
		List<Map<String,Object>> li=new ArrayList<Map<String,Object>>();
		try{
			/*String sql=getQuery("GET_HOME_INFO");
			LogManager.info("Query=>"+sql);
			Object obj[]=new Object[]{StringUtils.isBlank(bean.getContentTypeId())?"0":bean.getContentTypeId(), StringUtils.isBlank(bean.getQuoteNo())?"":bean.getQuoteNo(),StringUtils.isBlank(bean.getContentTypeId())?"0":bean.getContentTypeId(), bean.getProductId(), bean.getSchemeId(), bean.getProductId(), bean.getSchemeId(),  StringUtils.isBlank(bean.getContentTypeId())?"0":bean.getContentTypeId(), bean.getProductId(), bean.getSchemeId(),  StringUtils.isBlank(bean.getContentTypeId())?"0":bean.getContentTypeId()};
			*/
			/*if("1".equalsIgnoreCase(bean.getLocationSize()) || "insert".equalsIgnoreCase(bean.getCallFrom()))
				list = getCoveragesList(bean.getQuoteNo(), bean.getProductId(), bean.getSchemeId(), bean.getContentTypeId(), "coverage", " WHERE LOCATION_ID='"+bean.getLocationId()+"'",bean.getLocationId());
			else	*/
				list = getCoveragesList(bean.getQuoteNo(), bean.getProductId(), bean.getSchemeId(), bean.getContentTypeId(), "coverage", bean.getCallFrom(),bean.getLocationId(),bean.getAgencyCode());
			
			String dropdownQuery = getQuery("GET_HOMECOVER_DROPDOWN_LIST");
			Object[] args = new Object[2];
			args[1] = bean.getBranchCode();
			for(int i=0;i<list.size();i++){
				Map<String,Object> map = list.get(i);
				String controlType = map.get("SUM_INSURED_CONTROL_TYPE").toString();
				if("dropdown".equalsIgnoreCase(controlType) || "Radio".equalsIgnoreCase(controlType)){
					args[0] = map.get("DROPDOWNID")==null?"":map.get("DROPDOWNID").toString();
					List<Map<String,Object>> dropdownList = this.mytemplate.queryForList(dropdownQuery, args);
					map.put("DropDown", dropdownList);
				}
				li.add(map);
			}
			LogManager.info("List Size=>"+li.size());
		}catch(Exception e){
			LogManager.debug(e);
		}
		LogManager.info("getHomeInfo - Exit ");
		return 	li;
	}
	
	public List<Map<String,Object>> getHomeInfoDesc(HomeBean bean) {
		LogManager.info("getHomeInfo - Enter");
		List<Map<String, Object>> list=null;		
		List<Map<String,Object>> li=new ArrayList<Map<String,Object>>();
		try{
			list = getCoveragesList(bean.getQuoteNo(), bean.getProductId(), bean.getSchemeId(), bean.getContentTypeId(), "description", "",bean.getLocationId(),bean.getAgencyCode());
			String dropdownQuery = getQuery("GET_HOMECOVER_DROPDOWN_LIST");
			Object[] args = new Object[2];
			args[1] = bean.getBranchCode();
			for(int i=0;i<list.size();i++){
				Map<String,Object> map = list.get(i);
				String controlType = map.get("SUM_INSURED_CONTROL_TYPE").toString();
				if("dropdown".equalsIgnoreCase(controlType) || "Radio".equalsIgnoreCase(controlType)){
					args[0] = map.get("DROPDOWNID")==null?"":map.get("DROPDOWNID").toString();
					List<Map<String,Object>> dropdownList = this.mytemplate.queryForList(dropdownQuery, args);
					map.put("DropDown", dropdownList);
				}
				li.add(map);
			}
			LogManager.info("List Size=>"+li.size());
		}catch(Exception e){
			LogManager.debug(e);
		}
		LogManager.info("getHomeInfo - Exit ");
		return 	li;
	}
	
	/*public List<Map<String,Object>> getHomePremiumInfo(HomeBean bean) {
		List<Map<String, Object>> resultList = new ArrayList<Map<String,Object>>();
		try {
			String condition = " where PREMIUM_AMOUNT!=0 ";
			resultList = getCoveragesList(bean.getQuoteNo(), bean.getProductId(), bean.getSchemeId(), bean.getContentTypeId(), "coverage", condition);
		} catch(Exception e){
			LogManager.debug(e);
		}
		return resultList;
	}*/
	
	public List<Map<String,Object>> getHomePremiumInfo(HomeBean bean) {
		List<Map<String, Object>> resultList = new ArrayList<Map<String,Object>>();
		try {
			String condition = " where PREMIUM_AMOUNT!=0 ";
			resultList = getCoveragesList(bean,"coverage",condition);
		} catch(Exception e){
			LogManager.debug(e);
		}
		return resultList;
	}
	
	public List<Map<String,Object>> getUploadCoverList(HomeBean bean)
	{
		LogManager.info("getUploadCoverList - Enter");
		List<Map<String, String>> list=null;		
		List<Map<String,Object>> li=new ArrayList<Map<String,Object>>();
		try{
			String sql=getQuery("GET_UPLOAD_LIST");
			LogManager.info("Query=>"+sql);
			list=this.mytemplate.queryForList(sql,new Object[]{StringUtils.isBlank(bean.getContentTypeId())?"0":bean.getContentTypeId(), StringUtils.isBlank(bean.getQuoteNo())?"":bean.getQuoteNo(),StringUtils.isBlank(bean.getContentTypeId())?"0":bean.getContentTypeId(), bean.getProductId(), bean.getSchemeId(), bean.getProductId(), bean.getSchemeId(),  StringUtils.isBlank(bean.getContentTypeId())?"0":bean.getContentTypeId(), bean.getProductId(), bean.getSchemeId(),  StringUtils.isBlank(bean.getContentTypeId())?"0":bean.getContentTypeId()});
			for(int i=0;i<list.size();i++){
				Map map=(Map)list.get(i);
				String controlType = map.get("SUM_INSURED_CONTROL_TYPE").toString();
				if("dropdown".equalsIgnoreCase(controlType) || "Radio".equalsIgnoreCase(controlType)){
					String arr[]=(map.get("COVERAGES_LIMIT").toString().split(","));
					List<Map<String, String>> l=new ArrayList<Map<String, String>>();
					for(int j=0;j<arr.length;j++){
						Map<String, String>  m=new HashMap<String, String>();
						String[] arrVal = arr[j].split("~");
						m.put("Key", arrVal[0]);
						m.put("Value", arrVal[1]);
						l.add(m);
					}
					map.put("DropDown", l);
				}
				li.add(map);
			}
			LogManager.info("List Size=>"+li.size());
		}catch(Exception e){
			LogManager.debug(e);
		}
		LogManager.info("getUploadCoverList - Exit ");
		return 	li;
	}
	
	public List<Map<String,Object>> subUploadCoverList(final HomeBean bean){
		LogManager.push("subUploadCoverList method() Enter");
		List<Map<String,Object>> gmDetails=null;
		try{
			String sql=getQuery("GET_SUB_UPLOADCOVER_LIST");
			//Object obj[]=new Object[]{bean.getQuoteNo(), bean.getQuoteNo(), bean.getProductId(), bean.getSchemeId(), bean.getContentTypeId(), bean.getProductId(), bean.getSchemeId(), bean.getQuoteNo()};
			Object obj[]=new Object[]{StringUtils.isBlank(bean.getQuoteNo())?"":bean.getQuoteNo()};//, bean.getContentTypeId(), bean.getProductId(), bean.getSchemeId()};
			for(Object k:obj)
				LogManager.info("args=>"+k);
			LogManager.info("Query=>"+sql);
			gmDetails=this.mytemplate.queryForList(sql,obj);
		}catch(Exception e){
			LogManager.debug(e);
		}
		LogManager.info("subUploadCoverList method() Exit");
		return gmDetails;
	}
	
	public List<Map<String,Object>> subPremium(final HomeBean bean){
		LogManager.push("subPremium method() Enter");
		List<Map<String,Object>> gmDetails=null;
		try{
			String sql=getQuery("GET_HOME_SUBPREMIUM");
			//Object obj[]=new Object[]{bean.getQuoteNo(), bean.getQuoteNo(), bean.getProductId(), bean.getSchemeId(), bean.getContentTypeId(), bean.getProductId(), bean.getSchemeId(), bean.getQuoteNo()};
			Object obj[]=new Object[]{StringUtils.isBlank(bean.getQuoteNo())?"":bean.getQuoteNo()};//, bean.getContentTypeId(), bean.getProductId(), bean.getSchemeId()};
			for(Object k:obj)
				LogManager.info("args=>"+k);
			LogManager.info("Query=>"+sql);
			gmDetails=this.mytemplate.queryForList(sql,obj);
		}catch(Exception e){
			LogManager.debug(e);
		}
		LogManager.info("subPremium method() Exit");
		return gmDetails;
	}
	
	public List<Map<String,Object>> getSubHomeInfo(HomeBean bean)
	{
		LogManager.info("getSubHomeInfo - Enter");
		List<Map<String,Object>> list=null;		
		List<Map<String,Object>> li=new ArrayList<Map<String,Object>>();
		try{
			/*String sql=getQuery("GET_SUB_HOME_INFO");
			LogManager.info("Query=>"+sql);
			list=this.mytemplate.queryForList(sql,new Object[]{StringUtils.isBlank(bean.getContentTypeId())?"0":bean.getContentTypeId(), StringUtils.isBlank(bean.getQuoteNo())?"":bean.getQuoteNo(), StringUtils.isBlank(bean.getContentTypeId())?"0":bean.getContentTypeId(), bean.getProductId(), bean.getSchemeId(), bean.getProductId(), bean.getSchemeId(),  StringUtils.isBlank(bean.getContentTypeId())?"0":bean.getContentTypeId(), bean.getProductId(), bean.getSchemeId(),  StringUtils.isBlank(bean.getContentTypeId())?"0":bean.getContentTypeId()});*/
			list = getCoveragesList(bean.getQuoteNo(), bean.getProductId(), bean.getSchemeId(), bean.getContentTypeId(), "subcoverage", "",bean.getLocationId(),bean.getAgencyCode());
			String dropdownQuery = getQuery("GET_HOMECOVER_DROPDOWN_LIST");
			Object[] args = new Object[2];
			args[1] = bean.getBranchCode();
			for(int i=0;i<list.size();i++){
				Map<String,Object> map = list.get(i);
				String controlType = map.get("SUB_CONTROL_TYPE").toString();
				if("dropdown".equalsIgnoreCase(controlType) || "Radio".equalsIgnoreCase(controlType)){
					args[0] = map.get("DROPDOWNID")==null?"":map.get("DROPDOWNID").toString();
					List<Map<String,Object>> dropdownList = this.mytemplate.queryForList(dropdownQuery, args);
					map.put("DropDown", dropdownList);
				}
				li.add(map);
			}
		}catch(Exception e){
			LogManager.debug(e);
		}
		LogManager.info("getSubHomeInfo - Exit ");
		return 	li;
	}
	public List<Object> getPremiumInfo(HomeBean bean)
	{
		LogManager.info("getPremiumInfo - Enter");
		List<Object> list=null;	
		String sql=null;
		Object[] obj=new Object[0];

		try{
			sql=getQuery("GET_PREMIUM_DTL");
			obj=new Object[4];
			obj[0]=bean.getQuoteNo();
			obj[1]=bean.getQuoteNo();
			obj[2]=bean.getQuoteNo();
			obj[3]=bean.getLoginId();
			list=this.mytemplate.queryForList(sql,obj);
			if(list!=null && list.size()>0)
			{
				Map map=(Map)list.get(0);
				bean.setCustomerId(map.get("CUSTOMER_ID")==null?"":map.get("CUSTOMER_ID").toString());
				bean.setCustomerName(map.get("CUSTOMER_NAME")==null?"":map.get("CUSTOMER_NAME").toString());
				bean.setInsuredLocation(map.get("ADDRESS1")==null?"":map.get("ADDRESS1").toString());
				bean.setActualPremium(map.get("ACTUAL_PREMIUM")==null?"":map.get("ACTUAL_PREMIUM").toString());
				bean.setDiscountPremium(map.get("DISCOUNT_PREMIUM")==null?"":map.get("DISCOUNT_PREMIUM").toString());
				bean.setTotalPremium(map.get("PREMIUM")==null?"":map.get("PREMIUM").toString());
				bean.setQuoteNo(map.get("QUOTE_NO")==null?"":map.get("QUOTE_NO").toString());
				bean.setEffectiveDate(map.get("EFFECTIVE_DATE")==null?"":map.get("EFFECTIVE_DATE").toString());
				bean.setExpiryDate(map.get("EXPIRY_DATE")==null?"":map.get("EXPIRY_DATE").toString());
				bean.setEmail(map.get("EMAIL")==null?"":map.get("EMAIL").toString());
			}
			sql=getQuery("GET_PREMIUM_INFO");
			obj=new Object[8];
			obj[0]=bean.getQuoteNo();
			obj[1]=bean.getQuoteNo();
			obj[2]=bean.getProductId();
			obj[3]=bean.getSchemeId();
			obj[4]=bean.getQuoteNo();
			obj[5]=bean.getProductId();
			obj[6]=bean.getSchemeId();
			obj[7]=bean.getContentTypeId();
			LogManager.info("Query=>"+sql);
			list=this.mytemplate.queryForList(sql, obj);
		}catch(Exception e){
			LogManager.debug(e);
		}
		LogManager.info("getPremiumInfo - Exit ");
		return 	list;
	}
	
	public double getPremium(String baserateSts,double baserateVal,String sum_insured,String value)
	{
		System.out.println("baserateSts: "+baserateSts+"===baserateVal: "+baserateVal+"=== sum_insured: "+sum_insured+" value: "+value);
		double premium=0.00,sum_ins=0.00;
		int val=0;
		try{
			if(sum_insured!=null && sum_insured.length()>0){
				sum_ins=Double.parseDouble(sum_insured==null?"0":sum_insured); 
			}
		}catch(Exception e){e.printStackTrace();}
		if("P".equalsIgnoreCase(baserateSts))
		{
			premium=(baserateVal*sum_ins)/100;
		}else if("M".equalsIgnoreCase(baserateSts))
			{
				premium=(baserateVal*sum_ins)/1000;
			}
		else if("A".equalsIgnoreCase(baserateSts))
		{
			try{
				if(value!=null && value.length()>0)
			          val=Integer.parseInt(value);
			}catch (Exception e) {
				e.printStackTrace();
			}
			premium=(baserateVal*val);
			System.out.println("premium: "+premium);
		}else if("G".equalsIgnoreCase(baserateSts))
		{
			String rate=baserateVal+"";
			if(rate.indexOf(".")!=-1)
			{
				premium=(baserateVal*sum_ins)/100;	
			}else
			{
				try
				{
					if(value!=null && value.length()>0)
						val=Integer.parseInt(value);
				}catch (Exception e) {
					e.printStackTrace();
				}
				premium=(baserateVal*val);
			}
		}
		
		System.out.println("premium: for P"+premium);
		return Formatter.roundAmount(premium);
		
	}
	
	public double insertMasterPremiumInfo(HomeBean bean, List<Object> list){
		double baserateVal=0.00,premium=0.00,total_premium=0.00;
		int amend_id=0;
		int res=0;
		String refRemarks="";
		String locationId="";
		
		if(list!=null && list.size()>0){
			Map map=(Map)list.get(0);
			locationId=map.get("LOCATION_ID").toString();
		}
		
		try {
			String sql=getQuery("GET_HOME_EXIST");
			Object[] obj=new Object[4];
			obj[0]=bean.getQuoteNo();
			obj[1]=bean.getProductId();
			obj[2]=bean.getSchemeId();
			obj[3]=locationId;
			LogManager.info("Query=>"+sql);
			int count =this.mytemplate.queryForInt(sql, obj);
			//if(count>0){
				if("RSAIssuer".equalsIgnoreCase(bean.getUserType()) || "Admin".equalsIgnoreCase(bean.getUserType())){
					amend_id=count;
				}else{
					
					try {
						String refQry="SELECT REFERRAL_REMARKS FROM OFS_DATA_DETAILS WHERE QUOTE_NO=? AND SCHEME_ID=? AND ROWNUM='1'";
						Object[] refArg=new Object[2];
						refArg[0]=bean.getQuoteNo();
						refArg[1]=bean.getSchemeId();
						/*String refQry="SELECT REFERRAL_REMARKS FROM OFS_DATA_DETAILS WHERE QUOTE_NO=? AND ROWNUM='1'";
						Object[] refArg=new Object[1];
						refArg[0]=bean.getQuoteNo();*/
						
						refRemarks=(String) this.mytemplate.queryForObject(refQry, refArg, String.class);
					} catch (Exception e) {
						refRemarks="";
					}
					
					
					amend_id=count;
					/*LogManager.info("ENTER DELETE::");
					sql=getQuery("DELETE_HOME_EXIST");
					Object[] obj1=new Object[5];
					obj1[0]=bean.getProductId();
					obj1[1]=bean.getSchemeId();
					obj1[2]=bean.getQuoteNo();
					obj1[3]=amend_id;
					obj1[4]=locationId;
					LogManager.info("DELETE_HOME_EXIST Query::"+sql);
					res=this.mytemplate.update(sql,obj1);
					LogManager.info("Result=>"+res);*/
					
					for(int n=0;n<bean.getSchemeList().size();n++){
						String sqlD="SELECT REMARKS FROM OFS_DATA_DETAILS WHERE PRODUCT_ID=? AND SCHEME_ID=? AND QUOTE_NO=?  AND AMEND_ID=?";
						Object[] arg=new Object[4];
						arg[0]=bean.getProductId();
						arg[1]=bean.getSchemeList().get(n);
						arg[2]=bean.getQuoteNo();
						arg[3]=amend_id;
						List<Map<String,Object>> remList=this.mytemplate.queryForList(sqlD, arg);
						
						if(remList.size()>0 && remList!=null){
							String remarks=remList.get(0).get("REMARKS")==null?"":remList.get(0).get("REMARKS").toString();
							if(StringUtils.isNotBlank(remarks)){
								String sqlQry="UPDATE OFS_DATA_DETAILS SET PREMIUM_AMOUNT=REMARKS WHERE PRODUCT_ID=? AND SCHEME_ID=? AND QUOTE_NO=?  AND AMEND_ID=?";
								this.mytemplate.update(sqlQry,arg);
							}
						}
						
					}
					
					amend_id=0;
				}
			//} 
			for(int i=0; i<list.size(); i++){
				Map map=(Map)list.get(i);
				String coverid=map.get("COVERAGES_ID").toString();
				//String cover_sts=map.get("SUM_INSURED")==null?"N":"Y";
				String cover_sts = "Y".equals(map.get("CALC_STATUS").toString()) && map.get("SUM_INSURED")!=null?"Y":"N";
				String baserateSts=map.get("MAIN_CALC_TYPE")==null?map.get("CALC_TYPE").toString():map.get("MAIN_CALC_TYPE").toString();
				String sum_insured=map.get("SUM_INSURED").toString()==null?"":map.get("SUM_INSURED").toString();
				double minPremium=Double.parseDouble(map.get("MINIMUM_PREMIUM")==null?"0":map.get("MINIMUM_PREMIUM").toString());
				Double baseRate=0.0;
				if(!StringUtils.isBlank(sum_insured)){
					try{
//						if("HomeAdmin".equalsIgnoreCase(bean.getUserType()) || "Admin".equalsIgnoreCase(bean.getUserType())){
//							for(int j=0; j<bean.getCOVER_ID().size(); j++){
//								if(bean.getCOVER_ID().get(j) != null){
//								if(Integer.parseInt(coverid) == Integer.parseInt(bean.getCOVER_ID().get(j))){
//									baseRate=Double.parseDouble(bean.getCOVERAGES_BASE_RATE().get(j));
//									baserateVal=Double.parseDouble(bean.getCOVERAGES_BASE_RATE().get(j));
//									break;
//								}
//								}
//							}
//							//baseRate=Double.parseDouble(bean.getCOVERAGES_BASE_RATE().get(Integer.parseInt(coverid.trim())));
//							//baserateVal=Double.parseDouble(bean.getCOVERAGES_BASE_RATE().get(Integer.parseInt(coverid.trim())));
//						} else {
							baseRate=Double.parseDouble(map.get("NEW_BASE_RATE")==null?"0":map.get("NEW_BASE_RATE").toString());
							baserateVal=Double.parseDouble(map.get("BASE_RATE")==null?"0":map.get("BASE_RATE").toString());
							//baserateVal=getBussinesRate(bean.getBusinessType(),coverid,"0",baserateVal);
//						}
						//baserateVal=Double.parseDouble(map.get("BASE_RATE")==null?"0":map.get("BASE_RATE").toString());
					}catch(Exception e){
						System.out.println("BASE RATE CONVERTION ERROR::"+e);
					}
					//if("G".equalsIgnoreCase(baserateSts) && ("HomeAdmin".equalsIgnoreCase(bean.getUserType()) || "Admin".equalsIgnoreCase(bean.getUserType()))){
					if("G".equals(map.get("CALC_TYPE"))) {
						if("A".equals(map.get("MAIN_CALC_TYPE"))) {
							premium=baseRate;
						}
						else {
							premium=getPremium(map.get("MAIN_CALC_TYPE").toString(),baseRate,sum_insured,"1");
						}
						
						total_premium+=premium<minPremium?minPremium:premium;
						
					}else if("Y".equalsIgnoreCase(cover_sts)){
						try{
							premium=getPremium(baserateSts,baserateVal,sum_insured,"1");
							if("A".equals(baserateSts))
								baseRate=baserateVal;
							}catch(Exception e){
								System.out.println("NUMBER FORMAT CONVERSION ERROR::"+e);
								e.printStackTrace();
							}
						total_premium+=premium<minPremium?minPremium:premium;
					}
					/*above of same
					 * if(sum_insured !=null && sum_insured.length()>0){
						  sum_insured = (sum_insured == null ? "": sum_insured);
						  premium=getPremium(baserateSts,baserateVal,sum_insured,"1");
					}*/
					if("G".equals(map.get("CALC_TYPE")) && "A".equals(map.get("MAIN_CALC_TYPE")))
						baserateVal=baseRate;
					java.text.NumberFormat fmt = new java.text.DecimalFormat("0.00");
					String coveragePremium=fmt.format(premium<minPremium?minPremium:premium);
					
//					if("HomeAdmin".equalsIgnoreCase(bean.getUserType()) || "Admin".equalsIgnoreCase(bean.getUserType())){
//						sql=getQuery("GET_COVERAGE_EXIST");
//						obj=new Object[]{bean.getProductId(), bean.getSchemeId(), bean.getContentTypeId(), coverid, bean.getQuoteNo()};
//						LogManager.info("GET_COVERAGE_EXIST Query::"+sql);
//						int countCoverage=this.mytemplate.queryForInt(sql,obj);
//						if(countCoverage>0){
//							LogManager.info("ENTER UPDATE FOR REFERRAL APPROVED::");
//							sql=getQuery("UPDATE_HOME_DATA");
//							obj=new Object[12];
//							obj[0]=amend_id;
//							obj[1]=sum_insured;
//							obj[2]=coveragePremium;
//							obj[3]=StringUtils.defaultIfEmpty(cover_sts,"");
//							obj[4]=baserateVal;
//							obj[5]="Y";
//							obj[6]=bean.getQuoteNo();
//							obj[7]=coverid;
//							obj[8]=bean.getSchemeId();
//							obj[9]=bean.getContentTypeId();
//							obj[10]=bean.getProductId();
//							obj[11]=locationId;
//							LogManager.info("UPDATE_HOME_DATA Query::"+sql);
//							res=this.mytemplate.update(sql,obj);
//						}else{
//							LogManager.info("ENTER INSERT FOR ADMIN INSERT::");
//							sql=getQuery("INSERT_HOME_DATA");
//							obj=new Object[12];					  
//							obj[0]=bean.getQuoteNo();
//							obj[1]=amend_id;
//							obj[2]=coverid;//map.get("COVERAGES_ID")
//							obj[3]=sum_insured;//map.get("SUM_INSURED")
//							obj[4]=coveragePremium;//map.get("BASE_RATE");
//							obj[5]=StringUtils.defaultIfEmpty(cover_sts,"");//map.get("SUM_INSURED")==null?"N":"Y"
//							obj[6]=baserateVal;//map.get("COVERAGES_BASE_RATE")
//							obj[7]="Y";
//							obj[8]=bean.getProductId();
//							obj[9]=bean.getSchemeId();
//							obj[10]=bean.getContentTypeId();
//							obj[11]=locationId;
//							LogManager.info("INSERT_HOME_DATA Query::"+sql);
//							res=this.mytemplate.update(sql,obj);
//						}
//					}else{
						LogManager.info("ENTER INSERT FOR EDIT::");
						sql=getQuery("INSERT_HOME_DATA");
						obj=new Object[12];					  
						obj[0]=bean.getQuoteNo();
						obj[1]=amend_id;
						obj[2]=coverid;//map.get("COVERAGES_ID")
						obj[3]=sum_insured;//map.get("SUM_INSURED")
						obj[4]=coveragePremium;//map.get("BASE_RATE");
						obj[5]=StringUtils.defaultIfEmpty(cover_sts,"");//map.get("SUM_INSURED")==null?"N":"Y"
						obj[6]=baserateVal;//map.get("COVERAGES_BASE_RATE")
						obj[7]="Y";
						obj[8]=bean.getProductId();
						obj[9]=bean.getSchemeId();
						obj[10]=bean.getContentTypeId();
						obj[11]=locationId;
						LogManager.info("INSERT_HOME_DATA Query::"+sql);
						res=this.mytemplate.update(sql,obj);
//				   }
					LogManager.info("PREMIUM FOR COVERAGE of"+coverid+" is===>"+premium);
				}
			}
			//updateReferralSumInsRemarks(bean,refRemarks);
			String query = getQuery("DEL_OFSTRANSACTIONDLS_NOTIN");
			Object[] args = new Object[1];
			args[0] = bean.getQuoteNo();
			LogManager.info("Query==> " + query);
			LogManager.info("Args==> " + StringUtils.join(args, ", "));
			this.mytemplate.update(query, args);
		}catch (Exception e){
			System.out.println("Exception::" + e.toString());
		}
		return total_premium;
	}
	
	public double insertOptionalPremiumInfo(HomeBean bean, List<Object> list)
	{	
		double premium=0.00,total_premium=0.00;
		double baserateVal=0.00;
		int amend_id=0;
		int res=0;
		
		try {
			String sql=getQuery("GET_HOME_EXIST_OPTIONAL");
			Object[] obj=new Object[3];
			obj[0]=bean.getQuoteNo();
			obj[1]=bean.getProductId();
			obj[2]=bean.getSchemeId();
			LogManager.info("Query=>"+sql);
			int count =this.mytemplate.queryForInt(sql, obj);
			//if(count>0){
				if("RSAIssuer".equalsIgnoreCase(bean.getUserType()) || "Admin".equalsIgnoreCase(bean.getUserType())){
					amend_id=count;
				}else{
					amend_id=count;
					LogManager.info("ENTER DELETE::");
					sql=getQuery("DELETE_HOME_EXIST_OPTIONAL");
					Object[] obj1=new Object[4];
					obj1[0]=bean.getProductId();
					obj1[1]=bean.getSchemeId();
					obj1[2]=bean.getQuoteNo();
					obj1[3]=amend_id;
					LogManager.info("DELETE_HOME_EXIST Query::"+sql);
					res=this.mytemplate.update(sql,obj1);
					LogManager.info("Result=>"+res);
					amend_id=0;
				}
			//} 
				for(int i=0; i<list.size(); i++){
					Map map=(Map)list.get(i);
					String basecoverid=map.get("COVERAGES_ID").toString();
					String subcoverid=map.get("COVERAGES_SUB_ID").toString();
					//String cover_sts=map.get("SUM_INSURED")==null?"N":"Y";
					String cover_sts = "Y".equals(map.get("CALC_STATUS").toString()) && map.get("SUM_INSURED")!=null?"Y":"N";
					String baserateSts=map.get("MAIN_CALC_TYPE")==null?map.get("CALC_TYPE").toString():map.get("MAIN_CALC_TYPE").toString();
					String sum_insured=map.get("SUM_INSURED")==null?"":map.get("SUM_INSURED").toString();
					double minPremium=Double.parseDouble(map.get("MINIMUM_PREMIUM")==null?"0":map.get("MINIMUM_PREMIUM").toString());
					Double baseRate=0.0;
					if(!StringUtils.isBlank(sum_insured)){
						try{
//							if("HomeAdmin".equals(bean.getUserType()) || "Admin".equalsIgnoreCase(bean.getUserType())){
//								baseRate=Double.parseDouble(bean.getCOVERAGES_SUB_BASE_RATE().get(Integer.parseInt(subcoverid)));
//								baserateVal=Double.parseDouble(bean.getCOVERAGES_SUB_BASE_RATE().get(Integer.parseInt(subcoverid)));
//							}
//							else{
								baseRate=Double.parseDouble(map.get("NEW_BASE_RATE")==null?"0":map.get("NEW_BASE_RATE").toString());
								baserateVal=Double.parseDouble(map.get("SUB_RATE")==null?"0":map.get("SUB_RATE").toString());
								//baserateVal=getBussinesRate(bean.getBusinessType(),basecoverid,subcoverid,baserateVal);
//							}
						}catch(Exception e){
							LogManager.debug("BASE RATE CONVERTION ERROR::"+e);
						}
						//if("G".equalsIgnoreCase(baserateSts) && ("HomeAdmin".equals(bean.getUserType()) || "Admin".equalsIgnoreCase(bean.getUserType()))){
						if("G".equals(map.get("CALC_TYPE"))) {
							if("A".equals(map.get("MAIN_CALC_TYPE"))) {
								premium=baseRate;
							}
							else {
								premium=getPremium(map.get("MAIN_CALC_TYPE").toString(),baseRate,sum_insured,"1");
							}
							total_premium+=premium<minPremium?minPremium:premium;
							//total_premium+=premium;
							
						}else if("Y".equalsIgnoreCase(cover_sts)){
							try{
								premium=getPremium(baserateSts,baserateVal,sum_insured,"1");
								}catch(Exception e){
									System.out.println("NUMBER FORMAT CONVERSION ERROR::"+e);
									e.printStackTrace();
								}
								total_premium+=premium<minPremium?minPremium:premium;
								//total_premium+=premium;
						}
						/*java.text.NumberFormat fmt = new java.text.DecimalFormat("0.00");
						String coveragePremium=fmt.format(premium<minPremium?minPremium:premium);*/
						String coveragePremium= String.valueOf(premium<minPremium?minPremium:premium);
						/*above of same
						 * if(sum_insured !=null && sum_insured.length()>0){
							  sum_insured = (sum_insured == null ? "": sum_insured);
							  premium=getPremium(baserateSts,baserateVal,sum_insured,"1");
						}*/
						if("G".equals(map.get("CALC_TYPE")) && "A".equals(map.get("MAIN_CALC_TYPE")))
							baserateVal=baseRate;
//						if("HomeAdmin".equalsIgnoreCase(bean.getUserType()) || "Admin".equalsIgnoreCase(bean.getUserType())){
//							sql=getQuery("GET_SUB_COVERAGE_EXIST");
//							obj=new Object[]{bean.getProductId(), bean.getSchemeId(), bean.getContentTypeId(), basecoverid, subcoverid, bean.getQuoteNo()};
//							LogManager.info("GET_COVERAGE_EXIST Query::"+sql);
//							int countCoverage=this.mytemplate.queryForInt(sql,obj);
//							if(countCoverage>0){
//								LogManager.info("ENTER UPDATE FOR REFERRAL APPROVED::");
//								sql=getQuery("UPDATE_HOME_DATA_OPTIONAL");
//								obj=new Object[11];
//								obj[0]=amend_id;
//								obj[1]=sum_insured;
//								obj[2]=coveragePremium;
//								//obj[2]=premium;
//								obj[3]=baserateVal;
//								obj[4]="Y";
//								obj[5]=bean.getQuoteNo();
//								obj[6]=basecoverid;
//								obj[7]=subcoverid;
//								obj[8]=bean.getSchemeId();
//								obj[9]=bean.getContentTypeId();
//								obj[10]=bean.getProductId();
//								LogManager.info("UPDATE_HOME_DATA Query::"+sql);
//								res=this.mytemplate.update(sql,obj);
//							}else{
//								LogManager.info("ENTER INSERT FOR ADMIN INSERT::");
//								sql=getQuery("INSERT_HOME_DATA_OPTIONAL");
//								obj=new Object[12];					  
//								obj[0]=bean.getQuoteNo();
//								obj[1]=amend_id;
//								obj[2]=basecoverid;//map.get("COVERAGES_ID")
//								obj[3]=subcoverid;//map.get("COVERAGES_SUB_ID")
//								//obj[4]="";
//								obj[4]=sum_insured;//map.get("BASE_RATE");
//								obj[5]=coveragePremium;//map.get("SUB_RATE");
//								obj[6]=sum_insured;
//								obj[7]=baserateVal;//map.get("SUB_RATE")
//								obj[8]="Y";
//								obj[9]=bean.getProductId();
//								obj[10]=bean.getSchemeId();
//								obj[11]=bean.getContentTypeId();
//								LogManager.info("INSERT_HOME_DATA Query::"+sql);
//								res=this.mytemplate.update(sql,obj);
//							}
//						}else{
							LogManager.info("ENTER INSERT FOR EDIT::");
							sql=getQuery("INSERT_HOME_DATA_OPTIONAL");
							obj=new Object[12];					  
							obj[0]=bean.getQuoteNo();
							obj[1]=amend_id;
							obj[2]=basecoverid;//map.get("COVERAGES_ID")
							obj[3]=subcoverid;//map.get("COVERAGES_SUB_ID")
							//obj[4]="";
							obj[4]=sum_insured;//map.get("BASE_RATE");
							obj[5]=coveragePremium;//map.get("SUB_RATE");
							obj[6]=sum_insured;
							obj[7]=baserateVal;//map.get("SUB_RATE")
							obj[8]="Y";
							obj[9]=bean.getProductId();
							obj[10]=bean.getSchemeId();
							obj[11]=bean.getContentTypeId();
							LogManager.info("INSERT_HOME_DATA Query::"+sql);
							res=this.mytemplate.update(sql,obj);
//					   }
						LogManager.info("PREMIUM FOR COVERAGE of"+basecoverid+" is===>"+premium);
					}
				}
				String query = getQuery("DEL_OFSTRANSACTIONDLS_SUB_NOTIN");
				Object[] args = new Object[1];
				args[0] = bean.getQuoteNo();
				LogManager.info("Query==> " + query);
				LogManager.info("Args==> " + StringUtils.join(args, ", "));
				this.mytemplate.update(query, args);
			}catch (Exception e) {
			System.out.println("  runner insertion   " + e.toString());
			}
			return total_premium;
          }
	
	public List<Map<String,Object>> getBaseRate(String mainCover, String subCover, String productid, String schemeid, String contentTypeId, String sum){
		System.out.println("getBaseRate - Enter");
		String[] args=new String[9];
		List<Map<String,Object>> list=null;
		try{
			String q=getQuery("GET_HOME_BASERATE");
			args[0]=sum;
			args[1]=productid;
			args[2]=schemeid;
			args[3]=contentTypeId;
			args[4]=mainCover;
			args[5]=subCover;
			args[6]=productid;
			args[7]=schemeid;
			args[8]=contentTypeId;
			LogManager.info("Query=>"+q);
			for(String k:args)
				LogManager.info("args=>"+k);
			list=this.mytemplate.queryForList(q, args);
			if(list!=null && list.size()>0){
				LogManager.info("BASE RATE FOR COVERAGE of "+mainCover+"====>"+((Map)list.get(0)).get("BASE_RATE")==null?"0":((Map)list.get(0)).get("BASE_RATE").toString());
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		System.out.println("getBaseRate - Exit");
		return list;
	}
	
	public String getFormulaSum(String proid, String schemeId, String coverid, String quoteno){
		String result="";
		try{
			String q=getQuery("GET_HOME_FORMULA_SUM");
			LogManager.info("Query=>"+q);
			String[] args=new String [3];
			args[0]=proid;
			args[1]=schemeId;
			args[2]=coverid;
			for(String k:args)
				LogManager.info("args=>"+k);
			q=this.mytemplate.queryForObject(q, args, String.class).toString();
			LogManager.info("Query=>"+q);
			args=new String[2];
			args[0]=quoteno;
			args[1]=quoteno;
			for(String k:args)
				LogManager.info("args=>"+k);
			List list=this.mytemplate.queryForList(q, args);
			if(list!=null && list.size()>0){
				Map map=(Map)list.get(0);
				Object obj[]=map.values().toArray();
				result=obj[0].toString();
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return result;
	}
	
	public boolean getFormulaCount(String proid, String schemeId, String coverid)
	{
		boolean result=false;
		String q=getQuery("GET_HOME_FORMULA_COUNT");
		LogManager.info("Query=>"+q);
		String[] args=new String [3];
		args[0]=proid;
		args[1]=schemeId;
		args[2]=coverid;
		for(String k:args)
			LogManager.info("args=>"+k);
		if(!this.mytemplate.queryForObject(q, args, String.class).toString().equalsIgnoreCase("0")){
			result = true;
		}
		return result;
	}
	
	
	public List<Object> getSubCoverageDetails(final HomeBean bean) {
		List subcoverList=null;
		String query = "";
		try {
			query = getQuery("GET_HOME_SUBCOVERAGES");
			LogManager.info("Query=>"+query);
			Object obj[]=new Object[]{ bean.getContentTypeId(), bean.getProductId(), bean.getSchemeId(), bean.getContentTypeId(), bean.getProductId(), bean.getSchemeId(), bean.getQuoteNo()};
			for(Object k:obj)
				LogManager.info("args=>"+k);
			subcoverList = this.mytemplate.queryForList(query,obj);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return subcoverList;

	}
	
	 public List<Map<String,Object>> getReferalInfo(String quoteNo, String productId, String schemeId, String contentTypeId){
		 List<Map<String,Object>>  list=null;
		try{
			Object obj[] =new Object[]{quoteNo, productId, schemeId};
			String query = getQuery("GET_HOME_REFERRAL_INFO");
			LogManager.info("Query=>"+query);
			for(Object k:obj)
				LogManager.info("args=>"+k);
			list =this.mytemplate.queryForList(query,obj);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return list;
	}
	
	 public double updatePremium(double total_premium,String referral,String userType,String quoteNo,String productId,String schemeId,String contentTypeId,String menuType,String branchCode) {
		 System.out.println("total_premium:=====>>> "+total_premium);
		 String val = "";
		String query = "",temp_qry="",refStatus="";
		double min_premium = 0.00;
		Object values[]=null;

		 List<Map<String,Object>> value = getReferalInfo(quoteNo, productId, schemeId, contentTypeId);
		 if(value.size()>0){
			 Map<String,Object> map= value.get(0);
			 if(map.get("REFERRAL_DESCRIPTION")!=null )
				 refStatus = map.get("REFERRAL_DESCRIPTION").toString().trim();
		 }
		 // Minimum Premium premium from LOGIN_USER_DETAILS TABLE for the attached Broker
		 try{
			 String minPremiumQuery =getQuery("GET_HOME_MIN_PREMIUM_BROKER");
			 Object[] minPremiumObj = new Object[]{quoteNo, productId, schemeId};
			 LogManager.info("HomeDAO.updatePremium() Minimum Premium Query: "+queryFrammer(minPremiumQuery, minPremiumObj));
			 List<Map<String,Object>> list=this.mytemplate.queryForList(minPremiumQuery,minPremiumObj);
			 if(list != null && list.size()>0){
				 Map<String,Object> map = list.get(0);
				 min_premium=Double.parseDouble(map.get("min_premium_amount").toString());
			 }
			 if(min_premium>total_premium){
				 total_premium=min_premium;
			 }
		 }catch(Exception e) {
			 LogManager.info("Exception @ HomeDao.updatePremium()"
			 		+ " getting minimum Premium Amount QuoteNo: "+quoteNo+" SchemeId: "+schemeId+" ProductId: "+productId+" Error: "+e); 
			 e.printStackTrace();
		 }
		 if(referral.equalsIgnoreCase("CusInfo")){
			 return total_premium;
		 }

		 try {
			 //double policyFee = Double.parseDouble(getPolicyFees(quoteNo, userType, menuType, branchCode));
			 com.maan.common.dao.CommonDAO commonDAO = new com.maan.common.dao.CommonDAO();
			 double policyFee = Double.parseDouble(commonDAO.calculatePolicyFee(String.valueOf(total_premium), branchCode));
			 double finalPremium = total_premium + policyFee;
			 
			 //if("RSAIssuer".equals(userType)){
				 String qry="SELECT DISTINCT SCHEME_ID FROM OFS_DATA_DETAILS WHERE QUOTE_NO=? ORDER BY SCHEME_ID";
				 List<Map<String,Object>> list=this.mytemplate.queryForList(qry,new Object[]{quoteNo});
				 if(list.size()>1){
					 for(int j=0;j<list.size();j++){
						 if(!(list.get(j).get("SCHEME_ID").toString()).equalsIgnoreCase(schemeId)){
							 String myScheme=list.get(j).get("SCHEME_ID").toString();
							 String sqll="SELECT SUM(PREMIUM_AMOUNT) from OFS_DATA_DETAILS WHERE QUOTE_NO=? AND SCHEME_ID=?";
							 double myTot=this.mytemplate.queryForInt(sqll, new Object[]{quoteNo,myScheme});
							 List<Double> newPremList=updatePremiumNew(myTot,referral,userType,quoteNo,productId,myScheme,contentTypeId,menuType,branchCode);
							 finalPremium=finalPremium+newPremList.get(0);
							 total_premium=total_premium+newPremList.get(1);
							 policyFee=policyFee+newPremList.get(2);
						 }
					 }
				 }
			// }
			 
			 if(referral.equalsIgnoreCase("NR")&& !userType.equalsIgnoreCase("admin") && refStatus.indexOf("Admin Referral") == -1) {
				 temp_qry=" ,REMARKS=?, ADMIN_REFERRAL_STATUS=?,REFERRAL_DESCRIPTION =? ";
				 values=new Object[]{finalPremium, total_premium,policyFee,"","","", quoteNo, productId, schemeId};	
			 } else {
				 values=new Object[]{finalPremium, total_premium, policyFee, quoteNo, productId, schemeId};
			 }
			 query = getQuery("UPD_HOME_PREMIUM_DETAILS", new Object[]{temp_qry});
			 LogManager.info("Query=>"+query);
			 LogManager.info("Args==> " + StringUtils.join(values, ", "));
			 System.out.println("the query" + query);
			 this.mytemplate.update(query, values);

		 }catch (Exception ex) {
			 ex.printStackTrace();
		 }
		 return total_premium;
	 }
	
	public void getClaimExperience(String linkfrom, final HomeBean bean) {
		List result=null;
		List value;
		String query = "",refStatus=" ";
		String param[]=new String[2];
		boolean claim=false;
		
		try {
			param[0]=bean.getQuoteNo();
			param[1]=bean.getQuoteNo();
			query = "select last_years_1,last_years_2,last_years_3,amount_1,amount_2,amount_3,no_of_claim1 from OFS_DATA  where quote_no=? and amend_id in (select max(amend_id) from OFS_DATA where quote_no=?) "; 
			System.out.println("the query" + query);
			result =this.mytemplate.queryForList(query,param);
			
		}catch (Exception ex) {
			ex.printStackTrace();
		}
		if(result.size()>0  &&  !"adminProceedInfo".equalsIgnoreCase(linkfrom) &&  !"adminaddPremium".equalsIgnoreCase(linkfrom)){
			Map map1=(Map)result.get(0);
			Object result1[]=map1.values().toArray();
			value=getReferalInfo(bean.getQuoteNo(), bean.getProductId(), bean.getSchemeId(), bean.getContentTypeId());
			if(value.size()>0 ){
				Map map=(Map)value.get(0);
				if(map.get("REFERRAL_DESCRIPTION")!=null )
			       refStatus= map.get("REFERRAL_DESCRIPTION").toString();
			}
            try {
            	// if Anything greater than 2 is referral
				if (result1[6].toString()!=null&&result1[6].toString().length()>0){    
					if (Integer.parseInt(result1[6].toString()) >2)
						claim=true;
				} 
				if (result1[5].toString()!=null&&result1[5].toString().length()>0){    
					if (Integer.parseInt(result1[5].toString()) >5000)
						claim=true;
			    } 
				if (result1[4]!=null&&result1[4].toString().length()>0){    
					if (Integer.parseInt(result1[4].toString()) >10000)
						claim=true;
			    } 
				if (result1[3]!=null&&result1[3].toString().length()>0){    
					if (Integer.parseInt(result1[3].toString()) >10000)
						claim=true;
			    } 
			}catch (Exception ex){	
				ex.printStackTrace();		
			}
            if(claim){
				if(refStatus!=null){
					if (refStatus.indexOf(",34,") == -1) 
		                 refStatus+=",34,";
					}else 
		                 refStatus+=",34,";
            	}else{
					  if(refStatus!=null){
							if (refStatus.indexOf(",34,") != -1) 
		                          refStatus=refStatus.replaceAll(",34,","");
					  }
				}
				try{
					if(refStatus.trim().length()>0){
						System.out.println("refStatus updation-----------------------------------------------------------"+refStatus);
					    query = "update HOME_POSITION_MASTER set REMARKS='Referal',REFERRAL_DESCRIPTION='"+refStatus+"' where quote_no="+bean.getQuoteNo()+" and product_id="+bean.getProductId()+" and scheme_id="+bean.getSchemeId();
			        }else{
			        	System.out.println("refStatus updation-----------------------------------------------------------"+refStatus);
					    query = "update HOME_POSITION_MASTER set REMARKS=null,REFERRAL_DESCRIPTION=null where quote_no="+bean.getQuoteNo()+" and product_id="+bean.getProductId()+" and scheme_id="+bean.getProductId();
					}
					this.mytemplate.update(query);
				}catch (Exception ex){	
					ex.printStackTrace();
				}
		   }
	 }
	
	public String[] getReferalDetailInfo(String linkfrom, final HomeBean bean){
		String result[] =new String[3];
		List value;
		String qryValues[]=new String[1];
		String query = "",refStatus="",referral="",Allreferral="",tempRefStatus="";
		result[1]="NotAvailable";
		  value=getReferalInfo(bean.getQuoteNo(), bean.getProductId(), bean.getSchemeId(), bean.getContentTypeId());
		   if(value.size()>0 ){
			   Map map=(Map)value.get(0);
				if(map.get("REFERRAL_DESCRIPTION")!=null )
			       refStatus= map.get("REFERRAL_DESCRIPTION").toString().trim();
					//System.out.println("refStatus.........................................."+refStatus+"refStatus.length"+refStatus.length());
					if(refStatus!=null && refStatus.length()>0 ){
                        try{
							if (refStatus.indexOf("Admin Referral") != -1){
								  tempRefStatus=refStatus;
		    	                  refStatus=refStatus.replaceAll("Admin Referral","");
								
								  if(refStatus.length()<=0 )
									  query = "update HOME_POSITION_MASTER set ADMIN_SUMMARY_STATUS='N',REMARKS=null,REFERRAL_DESCRIPTION=null where quote_no="+bean.getQuoteNo()+" and product_id="+bean.getProductId()+" and scheme_id="+bean.getSchemeId();
                                  else
                                	  query = "update HOME_POSITION_MASTER set ADMIN_SUMMARY_STATUS='N',REMARKS='Referal', REFERRAL_DESCRIPTION='"+refStatus+"'  where quote_no="+bean.getQuoteNo()+" and product_id="+bean.getProductId()+" and scheme_id="+bean.getSchemeId();
                                   
								  if( !tempRefStatus.equalsIgnoreCase("Admin Referral"))
                                    this.mytemplate.update(query);
             				 }
							
							}
				              catch(Exception e) { System.out.println("errror"+e);}
                        }// refstatus

						if(tempRefStatus.indexOf("Admin Referral") != -1  &&("adminProceedInfo".equalsIgnoreCase(linkfrom) ||  "adminaddPremium".equalsIgnoreCase(linkfrom) ||  "admin".equalsIgnoreCase(linkfrom) || "adminCalculate".equalsIgnoreCase(linkfrom) )){
							 Allreferral+="Admin Referral";
						     result[1]="Available";
							 result[2]=Allreferral;
						   }
			            
						if(refStatus!=null && refStatus.length()>0 ){
							result[1]="Available";
							refStatus=refStatus.replaceAll(",,",",");
							//refStatus=refStatus.substring(1,(refStatus.length()-1));
					    try{
					    	//qryValues[0]=refStatus;
					    	query = "select referal_name,referal_id from REFERAL_MASTER where REFERAL_NAME in('"+refStatus+"') and branch_code='"+bean.getBranchCode()+"' ";
			                  
					    	// value = new runner().multipleSelection(query,qryValues);
					    	value =this.mytemplate.queryForList(query);
					    	if(value.size()>0 && value!=null){
							 
							 for(int i=0;i<value.size();i++){
								 Map map1=(Map)value.get(i);
								 Allreferral+=((map1.get("referal_name")==null?"":map1.get("referal_name"))+", ");
								 if( !(map1.get("referal_id").toString().equalsIgnoreCase("26")) && !(map1.get("referal_id").toString().equalsIgnoreCase("34")) )
									 referral+=((map1.get("referal_name")==null?"":map1.get("referal_name"))+"<br>");
								}
							}

                        } catch (Exception ex) {	ex.printStackTrace();		}

						  result[0]=referral;
			              if(Allreferral != null && Allreferral.length() > 1) 
			            	  result[2]=Allreferral.substring(0, Allreferral.length()-2);
			              else
			            	  result[2]="test";
				    }// refstatus 2nd
			  }
			 /* if("Admin Referral".equalsIgnoreCase(refStatus))
		        {
			 result[1]="";
			  result[0]=refStatus;
			  result[2]=refStatus;
				}
               else
		      {
			  result[0]=referral;
			  result[2]=Allreferral;
			  }*/
		return result; 
	}
	
	public List<Map<String,Object>> getMainCoverageWithQuote(final HomeBean bean,String linkfrom) {
		List<Map<String,Object>> result =null;
		List amendval =null;
		String values[]=new String[4];
		String query = "",temp_qry="", temp_qry_date = "";
		int amend_id = 0;
		try {
			values[0]=bean.getQuoteNo();
			values[1]=bean.getProductId();
			values[2]=bean.getSchemeId();
			values[3]=bean.getContentTypeId();

			query = "select max(amend_id) amend_id from OFS_DATA_DETAILS where quote_no=? and product_id=? and scheme_id=? and CONTENT_TYPE_ID=?";
			//System.out.println("the query" + query);
			amendval = this.mytemplate.queryForList(query,values);
			
			if(amendval.size()>0 && amendval!=null){
				Map map=(Map)amendval.get(amend_id);
				amend_id=Integer.parseInt(map.get("amend_id")==null?"0":map.get("amend_id").toString());
			}
			else
				amend_id=0;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		try {
			
			if (true)
				temp_qry_date = " and ( to_date((select to_char(inception_date,'dd-mm-yyyy') from home_position_master where quote_no="
						+ bean.getQuoteNo()
						+ "),'dd-mm-yyyy') between b.effective_date and b.expiry_date) ";
			
			query="SELECT * FROM ( select a.COVERAGES_ID,a.COVERAGES_NAME,b.COVERAGES_TYPE,a.COVERAGES_DISPLAY_NAME,b.DISPLAY_ORDER,b.CALC_STATUS," +
			"b.SUB_COVERAGES,b.CONTROL_TYPE,c.COVERAGES_SUMINSURED,c.COVERAGES_BASE_RATE,b.HELP_CONTENTS_ID,C.COVERAGES_Y_N_OPTION," +
			"b.CALC_TYPE,b.SUM_INSURED_LIMIT,b.SUM_INSURED_CONTROL_TYPE,b.COVERAGES_LIMIT from  OFS_MASTER a,OFS_COVERAGES_MASTER b, OFS_DATA_DETAILS c " +
			"where a.COVERAGES_ID=b.COVERAGES_ID and c.CONTENT_TYPE_ID="+bean.getContentTypeId()+" and b.CONTENT_TYPE_ID=c.CONTENT_TYPE_ID " +
			"and a.COVERAGES_ID=c.COVERAGES_ID and b.status='Y' and c.quote_no="+bean.getQuoteNo()+" and c.amend_id in ("+amend_id+") and " +
			"c.product_id="+bean.getProductId()+" and c.scheme_id="+bean.getSchemeId()+" "+temp_qry+" and b.scheme_id=c.scheme_id and b.amend_id||b.coverages_id in (select max(amend_id)||coverages_id from OFS_COVERAGES_MASTER where CONTENT_TYPE_ID="+bean.getContentTypeId()+" and product_id="+bean.getProductId()+" and scheme_id="+bean.getSchemeId()+" group by coverages_id) "
			+ temp_qry_date
			+" UNION ALL "
			+"select a.COVERAGES_ID,a.COVERAGES_NAME,b.COVERAGES_TYPE,a.COVERAGES_DISPLAY_NAME,b.DISPLAY_ORDER, b.CALC_STATUS,"
			+ "b.SUB_COVERAGES,b.CONTROL_TYPE,(case when b.CONTENT_TYPE_ID=4 and b.COVERAGES_ID=1 then TO_CHAR(c.CONTENT_VALUE_OTHERS) else b.COVERAGES_LIMIT end),b.BASE_RATE,b.HELP_CONTENTS_ID,b.CALC_STATUS,"
			+ "b.CALC_TYPE,b.SUM_INSURED_LIMIT,b.SUM_INSURED_CONTROL_TYPE,b.COVERAGES_LIMIT from OFS_MASTER a,OFS_COVERAGES_MASTER b,OFS_DATA c  where a.COVERAGES_ID=b.COVERAGES_ID "
			+"AND a.COVERAGES_ID NOT IN (SELECT COVERAGES_ID FROM OFS_DATA_DETAILS WHERE CONTENT_TYPE_ID = "+bean.getContentTypeId()+" AND quote_no= "+bean.getQuoteNo()+" AND amend_id IN ("+amend_id+") AND product_id = "+bean.getProductId()+" AND scheme_id = "+bean.getSchemeId()+") and b.CONTENT_TYPE_ID="+bean.getContentTypeId()+" and b.product_id="+bean.getProductId()+" and b.scheme_id="+bean.getSchemeId()
			+ " and b.CONTENT_TYPE_ID="
			+ bean.getContentTypeId()
			+ " and b.product_id="
			+ bean.getProductId()
			+ " and b.scheme_id="
			+ bean.getSchemeId()
			+ "    and b.status='Y' and c.quote_no="
			+ bean.getQuoteNo()
			+ " "
			+ temp_qry
			+ " and b.amend_id||b.coverages_id in (select max(amend_id)||coverages_id from OFS_COVERAGES_MASTER where CONTENT_TYPE_ID="
			+ bean.getContentTypeId()
			+ " and product_id="
			+ bean.getProductId()
			+ " and scheme_id="
			+ bean.getSchemeId()
			+ " group by coverages_id)  "
			+ temp_qry_date
			+ ") order by display_order";
			LogManager.info("Error====>"+query);
			result =this.mytemplate.queryForList(query,new String[0]);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return result;

	}
	
	public List<Map<String,Object>> getSubCoverageWithQuote(final HomeBean bean, String linkfrom){
		List<Map<String,Object>> result =null;
		List amendval =null;
		Object values[]=new Object[4];
		String query = "",temp_qry="", temp_qry_date = "";
		int amend_id=0;
		try {
			values[0]=bean.getQuoteNo();
			values[1]=bean.getProductId();
			values[2]=bean.getSchemeId();
			values[3]=bean.getContentTypeId();
			String qry = "select max(amend_id) from OFS_DATA_SUB_DETAILS where quote_no=? and product_id=? and scheme_id=? and CONTENT_TYPE_ID=?";
			
			amendval = this.mytemplate.queryForList(qry,values);
			
			if(amendval.size()>0 && amendval!=null){
				Map map=(Map)amendval.get(amend_id);
				amend_id=Integer.parseInt(map.get("amend_id")==null?"0":map.get("amend_id").toString());
			}else
				amend_id=0;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		try {
			if (!"admin".equalsIgnoreCase(linkfrom)
					&& !"adminProceedInfo".equalsIgnoreCase(linkfrom)
					&& !"adminCalculate".equalsIgnoreCase(linkfrom))
				temp_qry_date = " and ( to_date((select to_char(inception_date,'dd-mm-yyyy') from home_position_master where quote_no="
						+ bean.getQuoteNo()
						+ "),'dd-mm-yyyy') between a.effective_date and a.expiry_date) ";

			query = "(select a.COVERAGES_SUB_ID,a.COVERAGES_ID,b.COVERAGES_DISPLAY_NAME,a.SUB_COVERAGES_LIMIT,c.COVERAGES_SUB_BASE_RATE," +
					"a.PRODUCT_ID,a.SUB_CONTROL_TYPE,a.SUB_DISPLAY_ORDER,a.CALC_TYPE,c.COVERAGES_COVERED_EMPLOYEES," +
					"a.SUM_INSURED_LIMIT from OFS_COVERAGES_SUB_MASTER a ,OFS_MASTER b,OFS_DATA_SUB_DETAILS c where " +
					"a.status='Y'  and a.COVERAGES_SUB_ID=c.COVERAGES_SUB_ID and a.COVERAGES_SUB_ID=b.COVERAGES_ID and c.quote_no="+bean.getQuoteNo()+" and c.amend_id " +
					"in("+amend_id+") and a.amend_id||a.coverages_id||a.coverages_sub_id in (select max(amend_id)||coverages_id||coverages_sub_id from OFS_COVERAGES_SUB_MASTER where CONTENT_TYPE_ID="+bean.getContentTypeId()+" and product_id="+bean.getProductId()+" and scheme_id="+bean.getSchemeId()+" group by coverages_id,coverages_sub_id) and c.CONTENT_TYPE_ID=a.CONTENT_TYPE_ID and c.CONTENT_TYPE_ID="+bean.getContentTypeId()+" and c.product_id="+bean.getProductId()+" and c.scheme_id="+bean.getSchemeId()+" and a.scheme_id=c.scheme_id)" +
					"union(select a.COVERAGES_SUB_ID,a.COVERAGES_ID,b.COVERAGES_DISPLAY_NAME,a.SUB_COVERAGES_LIMIT,a.SUB_RATE," +
					"a.PRODUCT_ID,a.SUB_CONTROL_TYPE,a.SUB_DISPLAY_ORDER,a.CALC_TYPE,a.REMARKS,a.SUM_INSURED_LIMIT from " +
					"OFS_COVERAGES_SUB_MASTER a,OFS_MASTER b where a.CONTENT_TYPE_ID="+bean.getContentTypeId()+" and a.product_id="+bean.getProductId()+" and a.scheme_id="+bean.getSchemeId()+"" +
					" and a.status='Y' and a.COVERAGES_SUB_ID=b.COVERAGES_ID "+temp_qry+" and a.amend_id||a.coverages_id||a.coverages_sub_id in (select max(amend_id)||coverages_id||coverages_sub_id from OFS_COVERAGES_SUB_MASTER where CONTENT_TYPE_ID="+bean.getContentTypeId()+" and product_id="+bean.getProductId()+" and scheme_id="+bean.getSchemeId()+" group by coverages_id,coverages_sub_id) "
					+ temp_qry_date
					+ " and a.COVERAGES_SUB_ID in (select coverages_sub_id " +
					"from OFS_COVERAGES_SUB_MASTER where CONTENT_TYPE_ID="+bean.getContentTypeId()+" and product_id="+bean.getProductId()+" and scheme_id="+bean.getSchemeId()+" and coverages_sub_id " +
					"not in(select coverages_sub_id from OFS_DATA_SUB_DETAILS where quote_no="+bean.getQuoteNo()+" and CONTENT_TYPE_ID="+bean.getContentTypeId()+" and " +
					"product_id="+bean.getProductId()+" and scheme_id="+bean.getSchemeId()+" and amend_id in("+amend_id+"))) ) ";
			//System.out.println("the query" + query);
			result = this.mytemplate.queryForList(query,new String[0]);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return result;

	}
	
	
	/*public double updatePremium(HomeBean bean,double total_premium,String referral,String userType) {
		System.out.println("total_premium:=====>>> "+total_premium);
		String temp_qry="",refStatus="";
		double min_premium = 0.00;
		List<Object> val;
		Map map=null;

		try {
			String sql=getQuery("GET_HOME_MIN_PREMIUM");
			Object[] obj=new Object[3];
			obj[0]=bean.getProductId();
			obj[1]=bean.getSchemeId();
			obj[2]=bean.getContentTypeId();
			LogManager.info("Query=>"+sql);
			
			val =this.mytemplate.queryForList(sql, obj);
			
			if(val != null && val.size()>0){
				map = (Map) val.get(0);
				min_premium=Double.parseDouble(map.get("MINIMUM_PREMIUM")==null?"0.00":map.get("MINIMUM_PREMIUM").toString());
			}
			if(min_premium>total_premium)
				total_premium=min_premium;
			} catch (Exception ex) {
				ex.printStackTrace();		
			}
			try {
				String sql=getQuery("GET_HOME_MIN_PREMIUM_BROKER");
				Object[] obj=new Object[3];
				obj[0]=bean.getQuoteNo();
				obj[1]=bean.getProductId();
				obj[2]=bean.getSchemeId();
				LogManager.info("Query=>"+sql);
				val =this.mytemplate.queryForList(sql, obj);
				
				if(val != null && val.size()>0){
					map=(Map) val.get(0);
					min_premium=Double.parseDouble(map.get("min_premium_amount")==null?"0.00":map.get("min_premium_amount").toString());
				}
				if(min_premium>total_premium)
					total_premium=min_premium;
			}catch (Exception ex) {
				ex.printStackTrace();		
			}

			if(referral.equalsIgnoreCase("CusInfo"))
				return total_premium;
			Object[] obj=null;
			if(referral.equalsIgnoreCase("NR")&& !(userType.equalsIgnoreCase("HomeAdmin") || "Admin".equalsIgnoreCase(bean.getUserType())) && refStatus.indexOf("Admin Referral") == -1){
				temp_qry=" ,REMARKS= ?, ADMIN_REFERRAL_STATUS= ?,REFERRAL_DESCRIPTION= ?";
				obj=new Object[]{bean.getInceptionDt(), bean.getExpiryDt(), total_premium, total_premium, "", "", "", bean.getQuoteNo(), bean.getProductId(), bean.getSchemeId()};
			}else
				obj=new Object[]{bean.getInceptionDt(), bean.getExpiryDt(), total_premium, total_premium, bean.getQuoteNo(), bean.getProductId(), bean.getSchemeId()};
			for(Object k: obj)
				LogManager.info("args==>: " + k);
			try {
				String sql= getQuery("UPDATE_HOME_PREMIUM", new Object[]{temp_qry});
				this.mytemplate.update(sql,obj);
			}catch (Exception ex) {
				ex.printStackTrace();
			}
			return total_premium;
	   }*/
	/*public List<Map<String, Object>> premiumSummary(final HomeBean bean){
		LogManager.info("ENTER==>premiumSummary()");
		List<Map<String, Object>> premiumList=null;
		try{
			Object[] obj=new Object[]{bean.getQuoteNo(), bean.getQuoteNo(), bean.getProductId(), bean.getSchemeId(), bean.getQuoteNo(), bean.getProductId(), bean.getSchemeId(), bean.getContentTypeId()};
			String query=getQuery("GET_HOME_PREMIUM_DETAILS");
			LogManager.info("Query: " + query);
			LogManager.info("args==> " + StringUtils.join(obj,","));
			premiumList=this.mytemplate.queryForList(query, obj);
		}catch(Exception e){
			e.printStackTrace();
		}
		return premiumList;
	}*/
	
	public List<Map<String, Object>> personalInfo(final HomeBean bean){
		LogManager.info("ENTER===>personalInfo()");
		List<Map<String, Object>> premiumList=null;
		try{
			Object[] obj=new Object[]{StringUtils.isBlank(bean.getQuoteNo())?"":bean.getQuoteNo()};
			String query=getQuery("GET_FARMHOME_QUOTE_PERSONAL_INFO");
			LogManager.info("Query: " + query);
			LogManager.info("args==> " + StringUtils.join(obj,","));
			premiumList=this.mytemplate.queryForList(query, obj);
		}catch(Exception e){
			e.printStackTrace();
		}
		return premiumList;
	}
	
	/*public List<Map<String, Object>> customerPersonalInfo(final HomeBean bean){
		LogManager.info("ENTER===>customerPersonalInfo()");
		List<Map<String, Object>> premiumList=null;
		try{
			Object[] obj=new Object[]{bean.getCustomerId()};
			String query=getQuery("GET_HOME_PERSONAL_INFO");
			LogManager.info("Query: " + query);
			for(Object k: obj)
				LogManager.info("args==>: " + k);
			premiumList=this.mytemplate.queryForList(query, obj);
		}catch(Exception e){
			e.printStackTrace();
		}
		return premiumList;
	}*/
	
	public List<Map<String, Object>> getListValue(String lkey, String lvalue, String tname){
		LogManager.info("ENTER===>getListValue()");
		List<Map<String, Object>> list=null;
		try{
			String query="SELECT DISTINCT(TRIM("+lkey+")) DROPDOWN_KEY, TRIM("+lvalue+") DROPDOWN_VALUE FROM "+tname;
			LogManager.info("Query: " + query);
			list=this.mytemplate.queryForList(query);
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}

	public List<Map<String, Object>> getSubList(final HomeBean bean){
		LogManager.info("ENTER===>getSubList()");
		List<Map<String, Object>> list=null;
		Object[] obj=null;
		try{
			bean.setSubCoverId(StringUtils.isBlank(bean.getSubCoverId())?"0":bean.getSubCoverId());
			/*if(StringUtils.isBlank(bean.getSubCoverId().trim()))
				bean.setSubCoverId("0");*/
			obj=new Object[]{bean.getCoverId(), bean.getSubCoverId(), bean.getProductId(), bean.getSchemeId(), bean.getContentTypeId(), bean.getCoverId(),  bean.getSubCoverId(), bean.getProductId(), bean.getSchemeId(), bean.getContentTypeId()};
			String query=getQuery("GET_HOME_SUB_LIST");
			LogManager.info("Query: " + query);
			for(Object k: obj)
				LogManager.info("args==>: " + k);
			list=this.mytemplate.queryForList(query, obj);
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
	
	public List<Map<String, Object>> getCoverageList(final HomeBean bean){
		LogManager.info("ENTER===>getCoverageList()");
		List<Map<String, Object>> list=null;
		Object[] obj=null;
		try{
			bean.setSubCoverId(StringUtils.isBlank(bean.getSubCoverId())?"0":bean.getSubCoverId());
			/*if(StringUtils.isBlank(bean.getSubCoverId().trim()))
				bean.setSubCoverId("0");*/
			obj=new Object[]{bean.getQuoteNo(), bean.getCoverId(), bean.getSubCoverId(), bean.getProductId(), bean.getSchemeId(), bean.getContentTypeId(), bean.getQuoteNo(), bean.getDropDownLocation()};
			String query=getQuery("GET_HOME_COVERAGES_DETAILS");
			LogManager.info("Query==> " + query);
			LogManager.info("Args==> " + StringUtils.join(obj, ", "));
			list=this.mytemplate.queryForList(query, obj);
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
	public int saveCoverages(final HomeBean bean){
		int count=0;
		LogManager.info("ENTER===>getCoverageList()");
		Object[] obj=null;
		try{
			String query=getQuery("GET_HOME_MAX_AMENDID_COVERAGE");
			int amendId=this.mytemplate.queryForInt(query, new Object[]{bean.getQuoteNo()});
			LogManager.info("amendId===> " + amendId);
			bean.setSubCoverId(StringUtils.isBlank(bean.getSubCoverId())?"0":bean.getSubCoverId());
			/*if(StringUtils.isBlank(bean.getSubCoverId().trim()))
				bean.setSubCoverId("0");*/
			obj=new Object[]{bean.getQuoteNo(), bean.getProductId(), bean.getSchemeId(), bean.getContentTypeId(), bean.getCoverId(), bean.getSubCoverId(),bean.getDropDownLocation()};
			query=getQuery("HOME_DEL_COVERAGE_DETAILS");
			int del=this.mytemplate.update(query, obj);
			LogManager.info("HOME_DEL_COVERAGE_DETAILS: " + query);
			for(Object k: obj)
				LogManager.info("args==>: " + k);
			if(bean.getCoverageList()!=null && bean.getCoverageList().size()>0){
				for(int i=0; i<bean.getCoverageList().size(); i++){
					Map map=bean.getCoverageList().get(i);
					String dynamicQuery="";
					String values="";
					if(bean.getSubList().size()>=1){
						dynamicQuery+=", "+((Map<String, Object>)bean.getSubList().get(0)).get("DEST_COLUMN").toString();
						values+=",'"+map.get(((Map<String, Object>)bean.getSubList().get(0)).get("DEST_COLUMN").toString())+"'";
					}if(bean.getSubList().size()>=2){
						dynamicQuery+=", "+((Map<String, Object>)bean.getSubList().get(1)).get("DEST_COLUMN").toString();
						values+=",'"+map.get(((Map<String, Object>)bean.getSubList().get(1)).get("DEST_COLUMN").toString())+"'";
					}if(bean.getSubList().size()>=3){
						dynamicQuery+=", "+((Map<String, Object>)bean.getSubList().get(2)).get("DEST_COLUMN").toString();
						values+=",'"+map.get(((Map<String, Object>)bean.getSubList().get(2)).get("DEST_COLUMN").toString())+"'";
					}if(bean.getSubList().size()>=4){
						dynamicQuery+=", "+((Map<String, Object>)bean.getSubList().get(3)).get("DEST_COLUMN").toString();
						values+=",'"+map.get(((Map<String, Object>)bean.getSubList().get(3)).get("DEST_COLUMN").toString())+"'";
					}if(bean.getSubList().size()>=5){
						dynamicQuery+=", "+((Map<String, Object>)bean.getSubList().get(4)).get("DEST_COLUMN").toString();
						values+=",'"+map.get(((Map<String, Object>)bean.getSubList().get(4)).get("DEST_COLUMN").toString())+"'";
					}if(bean.getSubList().size()>=6){
						dynamicQuery+=", "+((Map<String, Object>)bean.getSubList().get(5)).get("DEST_COLUMN").toString();
						values+=",'"+map.get(((Map<String, Object>)bean.getSubList().get(5)).get("DEST_COLUMN").toString())+"'";
					}if(bean.getSubList().size()>=7){
						dynamicQuery+=", "+((Map<String, Object>)bean.getSubList().get(6)).get("DEST_COLUMN").toString();
						values+=",'"+map.get(((Map<String, Object>)bean.getSubList().get(6)).get("DEST_COLUMN").toString())+"'";
					}if(bean.getSubList().size()>=8){
						dynamicQuery+=", "+((Map<String, Object>)bean.getSubList().get(7)).get("DEST_COLUMN").toString();
						values+=",'"+map.get(((Map<String, Object>)bean.getSubList().get(7)).get("DEST_COLUMN").toString())+"'";
					}if(bean.getSubList().size()>=9){
						dynamicQuery+=", "+((Map<String, Object>)bean.getSubList().get(8)).get("DEST_COLUMN").toString();
						values+=",'"+map.get(((Map<String, Object>)bean.getSubList().get(8)).get("DEST_COLUMN").toString())+"'";
					}if(bean.getSubList().size()>=10){
						dynamicQuery+=","+((Map<String, Object>)bean.getSubList().get(9)).get("DEST_COLUMN").toString();
						values+=",'"+map.get(((Map<String, Object>)bean.getSubList().get(9)).get("DEST_COLUMN").toString())+"'";
					}
					obj=new Object[]{bean.getQuoteNo(), bean.getProductId(), bean.getSchemeId(), bean.getContentTypeId(), bean.getCoverId(), bean.getSubCoverId(), i, amendId,"Y",bean.getDropDownLocation()};
					query = getQuery("HOME_INS_COVERAGE_DETAILS", new Object[]{dynamicQuery, values});
					LogManager.info("Query: " + query);
					LogManager.info("Args: " + StringUtils.join(obj, ", "));
					count+=this.mytemplate.update(query, obj);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return count;
	}

	public List<Object> getUWMenu(final HomeBean bean){
		LogManager.info("ENTER===>getCoverageList()");
		List<Object> list=null;
		Object[] obj=null;
		try{
			obj=new Object[]{bean.getProductId(), bean.getSchemeId()};
			String query=getQuery("GET_HOME_UW_MENU");
			LogManager.info("Query: " + query);
			for(Object k: obj)
				LogManager.info("args==>: " + k);
			list=this.mytemplate.queryForList(query, obj);
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
	
	public List<Map<String,Object>> getBusinessTypeList(String SchemeAppCode) {
		List<Map<String,Object>> resultList = null;
		try {
			String query = getQuery("GET_BUSINESSTYPE_LIST");
			Object[] args = new Object[]{SchemeAppCode};
			LogManager.info("query==> " + query);
			LogManager.info("Args==> " + StringUtils.join(args,","));
			resultList = this.mytemplate.queryForList(query,args);
		}
		catch(Exception exception) {
			LogManager.debug("Exception @ getBusinessTypeList() " + exception);
		}
		return resultList;
	}
	
	public List<Object> getTypeClaimList(final HomeBean bean){
		LogManager.info("ENTER===>getTypeClaimList()");
		List<Object> list=null;
		Object[] obj=null;
		try{
			obj=new Object[]{bean.getProductId()};
			String query=getQuery("GET_HOME_COVER_ID");
			LogManager.info("Query: " + query);
			for(Object k: obj)
				LogManager.info("args==>: " + k);
			list=this.mytemplate.queryForList(query, obj);
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
	
	public List<Map<String, Object>> getUWDetails(final HomeBean bean){
		LogManager.info("ENTER===>getUWDetails()");
		List<Map<String, Object>> list=null;
		Object[] obj=null;
		try{
			obj=new Object[]{bean.getQuoteNo()};
			String query=getQuery("GET_HOME_UW_DETAILS");
			LogManager.info("Query: " + query);
			for(Object k: obj)
				LogManager.info("args==>: " + k);
			list=this.mytemplate.queryForList(query, obj);
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
	
	public void saveUWDetails(final HomeBean bean){
		LogManager.info("ENTER===>saveUWDetails()");
		Object[] obj=null;
		String query="";
		try{
			List<Map<String, Object>> list=getUWDetails(bean);
			if(list==null || list.size()<=0){
				obj=new Object[]{bean.getQuoteNo(), bean.getBuildCon(), bean.getLiveAccom(), bean.getReclaimLand(), bean.getConsDays(), bean.getDecRej(),
						bean.getPastThr(), bean.getNoClaim(), bean.getTypeClaim(), bean.getClaimAmt(), bean.getTypeProperty()};
				query=getQuery("INS_HOME_UW_DETAILS");
			}else{
				obj=new Object[]{bean.getBuildCon(), bean.getLiveAccom(), bean.getReclaimLand(), bean.getConsDays(), bean.getDecRej(),
						bean.getPastThr(), bean.getNoClaim(), bean.getTypeClaim(), bean.getClaimAmt(), bean.getTypeProperty(), bean.getQuoteNo()};
				query=getQuery("UPD_HOME_UW_DETAILS");
			}
			removeNull(obj);
			LogManager.info("Query: " + query);
			LogManager.info("Args==> " + StringUtils.join(obj, ", "));
			
			this.mytemplate.update(query, obj);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public String getReferalYNStatus(final HomeBean bean){
		LogManager.info("ENTER===>getReferalYNStatus()");
		String str="";
		Object[] obj=null;
		try{
			obj=new Object[]{bean.getLoginId()};
			String query=getQuery("GET_HOME_REFERAL_STATUS");
			LogManager.info("Query: " + query);
			for(Object k: obj)
				LogManager.info("args==>: " + k);
			str=this.mytemplate.queryForObject(query, obj, String.class).toString();
		}catch(Exception e){
			e.printStackTrace();
		}
		return str;
	}
	
	/*public String getGeneratePolicy(final HomeBean bean) {
		LogManager.info("ENTER===>getGeneratePolicy()");
		String sql="";
		Object[] obj=new Object[0];
		int res=0;
		try{
			bean.setMerchant_reference(new PaymentDAO()
			.updatePaymentDetails(bean.getModeOfPayment(), bean.getChequeNo(), bean.getChequeDate(), bean.getChequeAmount(),
					bean.getTotalPremium(),bean.getBankName(), bean.getMicrCode(), bean.getCashDepositBank(),
					bean.getCashAmount(), bean.getCashChellanNo(), bean.getCashInstrumentDate(), bean.getApplicationNo(), bean.getQuoteNo(),
					bean.getProductId(), bean.getTotalPremium(), bean.getMerchant_reference(), bean.getEmail(), bean.getCustomerName()));
			sql=getQuery("UPD_HOME_MODEOFPAY");
			obj=new Object[]{"", "", "", bean.getModeOfPayment(), "",StringUtils.isBlank(bean.getIssuer()) ? "1" : bean.getIssuer(), bean.getQuoteNo()};
			LogManager.info("Query=>"+sql);
			LogManager.info("obj[] ==> "+StringUtils.join(obj, ","));
			res=this.mytemplate.update(sql,obj);
			LogManager.info("Result=>"+res);
			if("Y".equalsIgnoreCase(bean.getGeneratePolicyYN())&&!"getSave".equalsIgnoreCase(bean.getActionType())){
				commonDao.updateHomeQuoteStatus(bean.getApplicationNo(),bean.getMerchant_reference());
			}
		}catch(Exception e){
			LogManager.debug(e);
		}
		LogManager.popRemove();
		LogManager.info("Exit===>getGeneratePolicy()");
		return "SUCCESS";
	}*/
	
	private void policyGeneration(HomeBean bean)throws BaseException {
		LogManager.info("ENTER===>policyGeneration()");
		Object obj[] = new Object[]{bean.getQuoteNo(),bean.getProductId(),bean.getProductId()};
		String sql=getQuery("GET_HOME_POLICY_STATUS");
		LogManager.info("Query=>"+sql);
		LogManager.info("Obj[]=>"+StringUtils.join(obj,","));
		final Map fromPosition = this.mytemplate.queryForMap(sql,obj);
		LogManager.info("Map Size=>"+fromPosition.size());
		if ("Y".equalsIgnoreCase(fromPosition.get("STATUS").toString())) {
			bean.setPolicyNo(new CommonDAO().getSequenceNo("Policy",bean.getProductId(),bean.getBranchCode(),bean.getQuoteNo(),""));
			bean.setReceiptNo(new CommonDAO().getSequenceNo("Receipt",bean.getProductId(),bean.getBranchCode(),"",""));
			bean.setDebitNo(new CommonDAO().getSequenceNo("Debit",bean.getProductId(),bean.getBranchCode(),"",""));
		}else{
			bean.setPolicyNo(fromPosition.get("POLICY_NO")==null?"":fromPosition.get("POLICY_NO").toString());
			bean.setReceiptNo(fromPosition.get("RECEIPT_NO")==null?"":fromPosition.get("RECEIPT_NO").toString());
			bean.setDebitNo(fromPosition.get("DEBIT_NOTE_NO")==null?"":fromPosition.get("DEBIT_NOTE_NO").toString());
		}
		LogManager.info("Exit===>policyGeneration()");
	}
	
	public List<Object> getPolicyInformation(String quoteNo) {
		List<Object> policyInfo=null;		
		LogManager.push("getPolicyInformation - Enter");
		try{
			String sql=getQuery("GET_POLICYINFO");
			LogManager.info("Query=>" + sql);
			LogManager.info("QuoteNo=>" + quoteNo);
			policyInfo=this.mytemplate.queryForList(sql,new String[]{quoteNo});			
		}catch (Exception e) {			
			LogManager.debug("EXCEPTION @ { " + e + " }");
		}		
		LogManager.info("getPolicyInformation - Exit || Result: " + policyInfo.size());
		return policyInfo;
	}
	
	/*private String getPolicyFees(String quoteNo, String userType, String menuType, String branchCode){
		String policyFee="0";
		String sql;
		LogManager.push("getPolicyFees - Enter");
		try{
			if(!"RA".equals(menuType) || "admin".equalsIgnoreCase(userType)){
				//sql=getQuery("GET_HOME_BROKER_POLICYFEES");
				//LogManager.info("Query=>" + sql);
				//LogManager.info("QuoteNo=>" + quoteNo);
				//double minpolicyFee=Double.parseDouble(this.mytemplate.queryForObject(sql,new Object[]{quoteNo, quoteNo}, String.class).toString());
				
				sql=getQuery("GET_HOME_POLICYFEE_CALCULATION");
				LogManager.info("Query=>" + sql);
				LogManager.info("QuoteNo=>" + quoteNo);
				List<Map<String,Object>> list=this.mytemplate.queryForList(sql,new Object[]{quoteNo});
				if(list!=null && list.size()>0){
					Map<String,Object> map = list.get(0);
					double total=0;
					if(!("HomeAdmin".equals(userType) || "Admin".equalsIgnoreCase(userType))){
						Double premium=Double.parseDouble(map.get("PREMIUM")==null?"0":map.get("PREMIUM").toString());
						Double loading=Double.parseDouble(map.get("EXCESS_PREMIUM")==null?"0":map.get("EXCESS_PREMIUM").toString());
						if("+".equals(map.get("EXCESS_SIGN"))){
							total=premium+loading;
						}else
							total=premium-loading;
					} else {
						total=Double.parseDouble(map.get("PREMIUM")==null?"0":map.get("PREMIUM").toString());
					}
					com.maan.common.dao.CommonDAO commonDAO = new com.maan.common.dao.CommonDAO();
					policyFee = commonDAO.calculatePolicyFee(String.valueOf(total), branchCode);
				}
			} else{
				sql=getQuery("GET_HOME_POSITION_MASTER_POLICYFEES");
				policyFee=this.mytemplate.queryForObject(sql,new Object[]{quoteNo}, String.class).toString();
			}
		}catch (Exception e) {
			LogManager.debug("EXCEPTION @ { " + e + " }");
		}		
		LogManager.info("getPolicyFees - Exit");
		return policyFee;
	}*/
	public List<Object> getHelpInfo(final HomeBean bean){
		List<Object> helpInfo=null;	
		LogManager.info("getHelpInfo - Enter");
		try{
			String sql=getQuery("GET_HELP_INFO");
			LogManager.info("Query=>" + sql);
			LogManager.info("ContentId=>" + bean.getHelpInfo());
			helpInfo=this.mytemplate.queryForList(sql,new Object[]{bean.getHelpInfo(), bean.getCoverId()});			
		}catch (Exception e) {
			LogManager.debug("EXCEPTION @ { " + e + " }");
		}		
		LogManager.info("getHelpInfo - Exit");
		return helpInfo;
	}
	
	public String adminRefstage(final HomeBean bean){
		String status="";	
		LogManager.push("adminRefstage - Enter");
		try{
			String sql=getQuery("UPD_HOME_REFERRAL_STATUS");
			LogManager.info("Query=>" + sql);
			LogManager.info("QuoteNo=>" + bean.getQuoteNo());
			if(StringUtils.isBlank(bean.getTotalPremium())){
				Double tp=0.0;
				tp=Double.parseDouble(bean.getFinalPremium())+Double.parseDouble(bean.getPolicyFee());
				bean.setTotalPremium(Double.toString(tp));
			}
			Object[] obj=new Object[10];
			obj[1]=bean.getAdminRemarks()==null?"":bean.getAdminRemarks();
			obj[2]=bean.getLoadOrDiscPremium()==null?"0":bean.getLoadOrDiscPremium();
			obj[4]=bean.getSign();
			obj[5]=bean.getTotalPremium();
			obj[6]=bean.getLoginId();
			obj[7]=bean.getFinalPremium();
			obj[8]=bean.getPolicyFee()==null?"0":bean.getPolicyFee();
			obj[9]=bean.getQuoteNo();
			if ("Y".equalsIgnoreCase(bean.getAdminRefStatus())){
				obj[0]="Admin";
				if(!"0".equals(bean.getAmendId()))
					obj[3]="E";
				else
					obj[3]="Y";
				bean.setReferralMsg(" Accepted.");
			}else if("N".equalsIgnoreCase(bean.getAdminRefStatus())){
				obj[0]="Referal";
				obj[3]="R";
				bean.setReferralMsg(" Rejected.");
			}else if ("A".equalsIgnoreCase(bean.getAdminRefStatus())){
				obj[0]="Referal";
				if(!"0".equals(bean.getAmendId()))
					obj[3]="E";
				else
					obj[3]="Y";
				bean.setReferralMsg(" Moved Pending.");
			}
			int res=this.mytemplate.update(sql,obj);              
			LogManager.info("Result=>>"+res);			
			if(res>0)
				status="SUCCESS";
		}catch (Exception e) {			
			LogManager.debug("EXCEPTION @ { " + e + " }");
		}
		LogManager.info("adminRefstage - Exit");
		return status;
	}
	
	public String getHomeCommision(final double premium,final String appNo, final HomeBean bean){
		LogManager.push("getHomeCommision method() Enter||");
		double commision=0; 
		try{
			String sql=getQuery("GET_LOGIN_PROD_ID");
			LogManager.info("Query=>"+sql);
			LogManager.info("Obj[0]=>"+appNo);
			Map map=this.mytemplate.queryForMap(sql,new Object[]{appNo});
			LogManager.info("Map Size=>"+map.size());
			sql=getQuery("GET_HOME_COMM");
			Object obj[] = {map.get("LOGIN_ID"),map.get("PROD_ID"), bean.getSchemeId()};
			LogManager.info("Query=>"+sql);
			LogManager.info("Obj[0]=>"+map.get("LOGIN_ID"));
			LogManager.info("Obj[1]=>"+map.get("PROD_ID"));
			String comPer =(String)this.mytemplate.queryForObject(sql, obj,String.class);
			LogManager.info("Result=>"+comPer);
			if (0==Double.parseDouble(comPer)){
				commision = 0.0;
			}
			else{
				commision = premium * (Double.parseDouble(comPer) / 100.0);
			}
		}catch(Exception e)
		{
			LogManager.debug(e);
		}
		LogManager.info("getHomeCommision method() Exit|| Commision=>"+commision);
		return commision+"";
	}
	
	public void savePremiumInfo(final HomeBean bean){
		LogManager.push("getHomeCommision method() Enter||");
		try{
			String sql=getQuery("UPD_HOME_PREMIUM_INFO");
			Object[] args = new Object[]{bean.getPolicyFee(), StringUtils.isBlank(bean.getTotalPremium())?"":bean.getTotalPremium(), bean.getQuoteNo()};
			LogManager.info("Query=>"+sql);
			LogManager.info("Query=>" + StringUtils.join(args, ", "));
			this.mytemplate.update(sql,args);
		} catch(Exception e) {
			LogManager.debug(e);
		}
		LogManager.info("getHomeCommision method() Exit");
	}
	
	public List<Object> gmDetails(final HomeBean bean){
		LogManager.push("gmDetails method() Enter");
		List gmDetails=null;
		try{
			String sql=getQuery("GET_GRID_MASTER_DETAILS");
			LogManager.info("Query=>"+sql);
			gmDetails=this.mytemplate.queryForList(sql,new Object[]{bean.getProductId(), bean.getSchemeId(), bean.getContentTypeId()});
		}catch(Exception e)
		{
			LogManager.debug(e);
		}
		LogManager.info("gmDetails method() Exit");
		return gmDetails;
	}
	
	public List<Map<String,Object>> getUWMaster(final HomeBean bean){
		LogManager.push("getUWMaster method() Enter");
		List<Map<String,Object>> uwMasterList=null;
		try{
			String sql=getQuery("GET_UW_MASTER_MENU");
			LogManager.info("Query=>"+sql);
			uwMasterList=this.mytemplate.queryForList(sql);
		}catch(Exception e){
			LogManager.debug(e);
		}
		LogManager.info("getUWMaster method() Exit");
		return uwMasterList;
	}
		
	public void adminScheme(final HomeBean bean){
		LogManager.push("adminScheme method() Enter");
		List schemeContent=null;
		try{
			String sql=getQuery("GET_HOME_ADMIN_SCHEMEID");
			LogManager.info("Query=>"+sql);
			schemeContent=this.mytemplate.queryForList(sql,new Object[]{bean.getQuoteNo()});
			if(schemeContent!=null){
				Map map=(Map)schemeContent.get(0);
				bean.setSchemeId(map.get("scheme_id").toString());
				bean.setContentTypeId(map.get("CONTENT_TYPE_ID").toString());
			}
		}catch(Exception e){
			LogManager.debug(e);
		}
		LogManager.info("adminScheme method() Exit");
	}
	
	public String updReferralRemarks(String referralRemarks, String quoteNo, String issuer, String type){
		LogManager.push("updRemarks method() Enter");
		try{
			String sql=getQuery("UPD_HOME_REF_STATUS");
			Object[] obj=new Object[6];
			obj[0] = StringUtils.isBlank(referralRemarks)?"":"Referal";
			obj[1] = referralRemarks;
			obj[2] = StringUtils.isBlank(referralRemarks)?"":"Y";
			obj[3] = StringUtils.isBlank(referralRemarks)?"":type;
			obj[4] = StringUtils.isBlank(issuer) ? "1" : issuer;
			obj[5] = quoteNo;
			
			LogManager.info("Query=>"+sql);
			LogManager.info("obj[] ==> "+StringUtils.join(obj, ","));
			this.mytemplate.update(sql,obj);
		}catch(Exception e){
			LogManager.debug(e);
		}
		LogManager.info("updRemarks method() Exit");
		return "SUCCESS";
	}
	
	public void updOfsReferralRemarks(final String quoteNo, final String referralComments){
		LogManager.push("updReferralRemarks method() Enter");
		try{
			String sql="UPDATE OFS_DATA SET REFERRAL_REMARKS=? WHERE QUOTE_NO=?";
			LogManager.info("Query=>"+sql);
			Object[] args = new Object[]{referralComments, quoteNo};
			removeNull(args);
			this.mytemplate.update(sql, args);
		}catch(Exception e){
			LogManager.debug(e);
		}
		LogManager.info("updReferralRemarks method() Exit");
	}
	
	public void updateReferralRemarks(final String quoteNo, final String referralComments){
		LogManager.push("updReferralRemarks method() Enter");
		try{
			String sql="update home_position_master set summary_remarks=? where quote_no=?";
			LogManager.info("Query=>"+sql);
			Object[] args = new Object[]{referralComments, quoteNo};
			removeNull(args);
			this.mytemplate.update(sql, args);
		}catch(Exception e){
			LogManager.debug(e);
		}
		LogManager.info("updReferralRemarks method() Exit");
	}
	/*private Double getBussinesRate(String BusinessType,String coverId,String subCoverId,double baseRate) {
		String query = "";
		Object[] args = null;
		try {
			query = getQuery("GET_BUSINESS_TYPE_RATE");
			args = new Object[]{BusinessType,coverId,subCoverId};
			LogManager.info("Query==> " + query);
			LogManager.info("Args==> " + StringUtils.join(args,","));
			Double percent = (Double) this.mytemplate.queryForObject(query, args, Double.class);
			baseRate +=(baseRate*percent/100);
			baseRate = round(baseRate,4);
		}
		catch(Exception exception) {
			//LogManager.debug("Exception @ getBussinesRate() " + exception);
		}
		return baseRate;
	}*/
	
	public List<Map<String,Object>> getContentList(String productId, String schemeId) {
		List<Map<String,Object>> result = new ArrayList<Map<String,Object>>();
		try {
			String query = getQuery("GET_CONTENT_TYPE_LIST");
			result = this.mytemplate.queryForList(query, new Object[]{productId,schemeId});
		}
		catch(Exception exception) {
			LogManager.debug("Exception @ getContentList { " + exception);
		}
		return result;
	}
	public String getContentName(String productId, String schemeId, String contentTypeId) {
		String contentTypeName = "";
		try {
			String query = getQuery("GET_OFS_CONTENT_TYPE_NAME");
			Object[] args = new Object[3];
			args[0] = productId;
			args[1] = schemeId;
			args[2] = contentTypeId;
			LogManager.info("Query==> " + query);
			LogManager.info("Args==> " + StringUtils.join(args,","));
			contentTypeName = (String) this.mytemplate.queryForObject(query, args, String.class);
		}
		catch(Exception exception) {
			LogManager.debug("Exception @ getContentName { " + exception);
		}
		return contentTypeName;
	}
	public List<Map<String,String>> getContentCover(HomeBean bean, String productId, String schemeId) {
		List<Map<String,String>> contentCoverList = new ArrayList<Map<String,String>>();
		try {
			String sql = "select ocm.content_type_id,om.COVERAGES_DISPLAY_NAME,ocm.SUM_INSURED_LIMIT,SUB_COVERAGES from ofs_coverages_master ocm, ofs_master om where OCM.PRODUCT_ID=? and ocm.scheme_id=? and ocm.content_type_id=? and ocm.coverages_id = om.coverages_id and ocm.amend_id = (select max(amend_id) from ofs_coverages_master ocm1 where ocm1.product_id=ocm.product_id and ocm1.scheme_id = ocm.scheme_id and ocm1.content_type_id = OCM.CONTENT_TYPE_ID and ocm1.coverages_id = ocm.coverages_id) and ocm.status=? and om.status=? order by COVERAGES_TYPE,display_order";
			Object[] args = new Object[5];
			args[0] = productId;
			args[1] = schemeId;
			args[3] = "Y";
			args[4] = "Y";
			LogManager.info("Query==> " + sql);
			for(int i=0; i<bean.getContentTypeList().size() ; i++) {
				Map<String,Object> contentTypeMap = bean.getContentTypeList().get(i);
				args[2] = contentTypeMap.get("CONTENT_TYPE_ID")==null?"":contentTypeMap.get("CONTENT_TYPE_ID").toString();
				LogManager.info("Args==> " + StringUtils.join(args,","));
				List<Map<String,Object>> list = this.mytemplate.queryForList(sql,args);
			}
		}
		catch(Exception exception) {
			LogManager.debug("Exception @ getContentCover { " + exception);
		}
		return contentCoverList;
	}
	public Map<String,Object> getCalculatePremium(String quoteNo,String schemeId,String location) {
		Map<String,Object> outputValues = null;
		try {
			SimpleJdbcCall procedure= mySimpleJdbcCall.withProcedureName("HOME_PREMIUM_CALC_B2B");
	     	Map<String,Object> inputValues=new java.util.TreeMap<String, Object>(String.CASE_INSENSITIVE_ORDER);
	     	inputValues.put("pvquote",quoteNo);
	     	inputValues.put("pvschemeid",schemeId);
	     	//inputValues.put("pvlocation",location);
	     	outputValues = procedure.execute(new MapSqlParameterSource(inputValues)); //Executes the procedure.
		}
		catch(Exception exception) {
			LogManager.debug("Exception @ getCalculatePremium { " + exception);
		}
		return outputValues;
	}
	
	public List<Map<String,Object>> getDropDownList(String type) {
		List<Map<String,Object>> resultList = new ArrayList<Map<String,Object>>();
		try {
			String query = "";
			Object[] args = null;
			if("AVERAGE_AGE_BROILER".equals(type)) {
				query = "SELECT ITEM_CODE, ITEM_VALUE FROM HOME_LIST_ITEM_VALUE WHERE ITEM_TYPE=? AND PARAM1=? AND STATUS='Y' ORDER BY ITEM_CODE ASC";
				args = new Object[]{"AVERAGE_AGE","BROILER"};
			} else if("AVERAGE_AGE_LAYER".equals(type)) {
				query = "SELECT ITEM_CODE, ITEM_VALUE FROM HOME_LIST_ITEM_VALUE WHERE ITEM_TYPE=? AND PARAM1=? AND STATUS='Y' ORDER BY ITEM_CODE ASC";
				args = new Object[]{"AVERAGE_AGE","LAYER"};
			} else {
				query = "SELECT ITEM_CODE, ITEM_VALUE FROM HOME_LIST_ITEM_VALUE WHERE ITEM_TYPE=? AND STATUS='Y' ORDER BY ITEM_CODE ASC";
				args = new Object[]{type};
			}
			resultList = this.mytemplate.queryForList(query, args);
		}
		catch(Exception exception) {
			LogManager.debug("Exception @ getDropDownList { " + exception);
		}
		return resultList;
	}
	public String getCoverStatus(String quoteNo, String type) {
		String result = "";
		try{
			String query = null;
			if("paCover".equalsIgnoreCase(type)) {
				query = getQuery("GET_FARM_PATCOVER_STATUS", new String[]{"PACOVER_YN"});
			}
			else if("motor".equalsIgnoreCase(type)) {
				query = getQuery("GET_FARM_PATCOVER_STATUS", new String[]{"TRACTER_YN"});
			}
			Object[] args = new Object[]{quoteNo};
			LogManager.info("query==> "+query);
			LogManager.info("args[]==> "+StringUtils.join(args,","));
			result = (String) this.mytemplate.queryForObject(query, args, String.class);
		}
		catch(Exception exception) {
			LogManager.debug("Exception @ getCoverStatus { " + exception);
		}
		return result;
	}
	public String getPaTractorOption(String schemeId, String branchCode) {
		String result = "";
		try {
			String query = getQuery("GET_CONTANT_PATRACTOR_OPTION");
			Object[] args = new Object[]{schemeId,branchCode};
			result = (String) this.mytemplate.queryForObject(query, args, String.class);
		} catch(Exception exception) {
			//LogManager.debug("Exception @ getCoverStatus { " + exception);
		}
		return result;
	}
	public List<Map<String,Object>> getExtraBenefitsList() {
		List<Map<String,Object>> resultList = null;
		try {
			String query = getQuery("GET_HOME_EXTRABENEFITS");
			resultList = this.mytemplate.queryForList(query);
		} catch(Exception exception) {
			LogManager.debug("Exception @ getExtraBenefitsList { " + exception);
		}
		return resultList;
	}
	public List<Map<String,Object>> getExcessList() {
		List<Map<String,Object>> resultList = null;
		try {
			String query = getQuery("GET_HOME_EXCESS");
			resultList = this.mytemplate.queryForList(query);
		} catch(Exception exception) {
			LogManager.debug("Exception @ getExcessList { " + exception);
		}
		return resultList;
	}
	public List<Map<String,Object>> getSubDetailsCount(String quoteNo, String locationId, String schemeId) {
		List<Map<String,Object>> resultMap = null;
		try {
			//String query = "select (select count(distinct COVERAGES_ID) from ofs_transaction_details where quote_no=?) COUNT1,(select count(distinct ODD.COVERAGES_ID ) from ofs_data_Details odd,ofs_coverages_master ocm where quote_no=? and ODD.PRODUCT_ID=ocm.PRODUCT_ID and ODD.SCHEME_ID=ocm.SCHEME_ID and ODD.CONTENT_TYPE_ID=ocm.CONTENT_TYPE_ID and ODD.COVERAGES_ID=ocm.COVERAGES_ID and ocm.status='Y' and ocm.UPLOAD_OPTION='Y') COUNT2 from dual";
			String query = "SELECT OM.COVERAGES_DISPLAY_NAME, OSM.SCHEME_NAME"
					+ " FROM OFS_DATA_DETAILS ODD, OFS_COVERAGES_MASTER OCM, OFS_MASTER OM, OFS_SCHEME_MASTER OSM"
					+ " WHERE ODD.QUOTE_NO = ? AND OSM.SCHEME_ID = ODD.SCHEME_ID AND ODD.PRODUCT_ID = OCM.PRODUCT_ID"
					+ " AND ODD.SCHEME_ID = OCM.SCHEME_ID AND ODD.CONTENT_TYPE_ID = OCM.CONTENT_TYPE_ID"
					+ " AND ODD.COVERAGES_ID = OCM.COVERAGES_ID AND OCM.COVERAGES_ID = OM.COVERAGES_ID"
					+ " AND OCM.AMEND_ID=(SELECT MAX(AMEND_ID) FROM OFS_COVERAGES_MASTER WHERE COVERAGES_ID= OCM.COVERAGES_ID)"
					+ " AND OCM.STATUS = 'Y' AND OCM.UPLOAD_OPTION = 'Y' AND ODD.COVERAGES_ID"
					+ " NOT IN (SELECT   DISTINCT COVERAGES_ID FROM   OFS_TRANSACTION_DETAILS"
					+ " WHERE QUOTE_NO = ODD.QUOTE_NO AND LOCATION_ID = ODD.LOCATION_ID"
					+ " AND ODD.SCHEME_ID = SCHEME_ID) AND ODD.LOCATION_ID = ? AND ODD.SCHEME_ID = ? AND TO_NUMBER(ODD.COVERAGES_SUMINSURED) >0";
			Object[] args = new Object[]{quoteNo, locationId, schemeId};
			LogManager.info("HomeDAO.getSubDetailsCount() Query: "+queryFrammer(query, args));
			resultMap = this.mytemplate.queryForList(query, args);
		} catch(Exception exception) {
			LogManager.debug("Exception @ HomeDAO.getSubDetailsCount() error: "+exception);
		}
		return resultMap;
	}
	
	public List<Map<String,Object>> getSubDetailsValidateAmount(String productId, String schemeId,
			String contentTypeId, String quoteNo, String locId) {
		List<Map<String,Object>> finalList = new ArrayList<Map<String,Object>>();
		try {
			Object[] args = null;
			String query = getQuery("GET_HOME_DESTSICOLMNS_COVER_LIST");
			args = new Object[3];
			args[0] = productId;
			args[1] = schemeId;
			args[2] = contentTypeId;
			LogManager.info("HomeDAO.getSubDetailsValidateAmount() Query: "+queryFrammer(query, args));
			List<Map<String,Object>> destColList = this.mytemplate.queryForList(query, args);
			
			
			Object[] params = new Object[1];
			args = new Object[4];
			args[0] = quoteNo;
			args[2] = locId;
			args[3] = schemeId;
			for ( int i=0 ; i<destColList.size() ; i++ ) {
				Map<String,Object> destColMap = destColList.get(i);
				params[0] = destColMap.get("DEST_COLUMN");
				query = getQuery("GET_HOME_SUBTOTALSI_VALIDATE", params);
				args[1] = destColMap.get("COVERAGES_ID");
				LogManager.info("HomeDAO.getSubDetailsValidateAmount() Query: "+queryFrammer(query, args));
				List<Map<String,Object>> resultList = this.mytemplate.queryForList(query, args);
				finalList.addAll(resultList);
			}
			
		} catch(Exception exception) {
			LogManager.info("Exception @ HomeDAO.getSubDetailsValidateAmount() Error: " + exception);
		}
		return finalList;
	}
	
	public List<Map<String,Object>> getCoveragesList(String quoteNo, String productId,
			String schemeId, String contentTypeId, String type, String condition,String locationId,String agencyCode) throws Exception {
		Object[] args=null;
		String query ="";
		
		if("description".equalsIgnoreCase(type)){
			String query1 = "SELECT COUNT(*) FROM OFS_COVERAGES_MASTER WHERE AGENCY_CODE = ? AND PRODUCT_ID = ? AND SCHEME_ID IS NOT NULL";
			int count = this.mytemplate.queryForInt(query1, new Object[] {agencyCode, productId});
			 if(count==0) {
				 query= getQuery("GET_HOME_COVERAGES_LIST_NEW_DEFAULT");
					args = new Object[12];
					args[0] = StringUtils.isBlank(contentTypeId)?"0":contentTypeId;
					args[1] = quoteNo;
					args[2] = locationId;
					args[3] = StringUtils.isBlank(contentTypeId)?"0":contentTypeId;
					args[4] = productId;
					args[5] = schemeId;
					args[6] = productId;
					args[7] = schemeId;
					args[8] = StringUtils.isBlank(contentTypeId)?"0":contentTypeId;
					args[9] = productId;
					args[10] = schemeId;
					args[11] = StringUtils.isBlank(contentTypeId)?"0":contentTypeId;
			 }else {
			
				query= getQuery("GET_HOME_COVERAGES_LIST_NEW");
				args = new Object[13];
				args[0] = StringUtils.isBlank(contentTypeId)?"0":contentTypeId;
				args[1] = agencyCode;
				args[2] = quoteNo;
				args[3] = locationId;
				args[4] = StringUtils.isBlank(contentTypeId)?"0":contentTypeId;
				args[5] = productId;
				args[6] = schemeId;
				args[7] = productId;
				args[8] = schemeId;
				args[9] = StringUtils.isBlank(contentTypeId)?"0":contentTypeId;
				args[10] = productId;
				args[11] = schemeId;
				args[12] = StringUtils.isBlank(contentTypeId)?"0":contentTypeId;
			 }
		}else{
			if("insert".equalsIgnoreCase(condition==null?"":condition)){
				query = "SELECT distinct(d.CALC_TYPE) GRID_CALC_TYPE, b.CALC_TYPE , B.DROPDOWNID , a.coverages_id, a.coverages_name,"
						+ " b.coverages_type, a.coverages_display_name, b.display_order, b.MULTIPLE_ROWS, b.sub_coverages, b.control_type,"
						+ " b.CALC_STATUS, c.coverages_suminsured SUM_INSURED,( CASE WHEN DROPDOWNID IS NOT NULL THEN"
						+ " ( SELECT DETAIL_NAME FROM CONSTANT_DETAIL CD WHERE CD.CATEGORY_ID=DROPDOWNID"
						+ " AND CATEGORY_DETAIL_ID=c.coverages_suminsured) ELSE"
						+ " TRIM(TO_CHAR (NVL (c.coverages_suminsured, 0), '999G999G999G999G999G999G999G999G999G990')) END) COVERAGES_SUMINSURED,"
						+ " NVL(c.coverages_base_rate,b.BASE_RATE) coverages_base_rate, b.BASE_RATE, b.help_contents_id, c.coverages_y_n_option,"
						+ " b.sum_insured_limit, b.sum_insured_control_type, b.coverages_limit, c.PREMIUM_AMOUNT, d.amend_id , b.UPLOAD_OPTION,"
						+ " a.REMARKS, c.CALCULATED_SI, b.MIN_SUM_INSURED,C.LOCATION_ID FROM OFS_MASTER a JOIN OFS_COVERAGES_MASTER b"
						+ " ON a.COVERAGES_ID = b.COVERAGES_ID AND b.CONTENT_TYPE_ID = ? and b.agency_code = ? LEFT OUTER JOIN OFS_DATA_DETAILS c ON"
						+ " b. content_type_id = c.content_type_id AND a.COVERAGES_ID = c.COVERAGES_ID"
						+ " AND b.scheme_id = c.scheme_id AND b.product_id = c.product_id AND c.quote_no = NVL (?, 0) AND C.LOCATION_ID = ?"
						+ " LEFT OUTER JOIN OFS_GRID_MASTER d ON b. content_type_id = d.content_type_id AND b.COVERAGES_ID = d.COVERAGES_ID"
						+ " AND b.scheme_id = d.scheme_id AND coverages_sub_id='0' and d.status='Y' AND b.product_id = d.product_id"
						+ " AND d.amend_id || d.coverages_id || d.coverages_sub_id IN ( SELECT MAX (amend_id) || coverages_id || coverages_sub_id"
						+ " FROM OFS_GRID_MASTER WHERE CONTENT_TYPE_ID = ? AND product_id = ? AND scheme_id = ? AND coverages_sub_id='0'"
						+ " and status='Y' GROUP BY coverages_id, coverages_sub_id) WHERE b.product_id =? AND b.scheme_id = ?"
						+ " AND b.status = 'Y' AND B.CONTENT_TYPE_ID = ? AND b.amend_id = ( SELECT MAX (amend_id) FROM OFS_COVERAGES_MASTER"
						+ " WHERE product_id = ? AND scheme_id = ? AND CONTENT_TYPE_ID =? AND coverages_id = b.coverages_id and agency_code = b.agency_code)"
						+ " order by display_order ASC,COVERAGES_ID ASC";
				args = new Object[13];
				args[0] = StringUtils.isBlank(contentTypeId)?"0":contentTypeId;
				args[1] = agencyCode;
				args[2] = quoteNo;
				args[3] = locationId;
				args[4] = StringUtils.isBlank(contentTypeId)?"0":contentTypeId;
				args[5] = productId;
				args[6] = schemeId;
				args[7] = productId;
				args[8] = schemeId;
				args[9] = StringUtils.isBlank(contentTypeId)?"0":contentTypeId;
				args[10] = productId;
				args[11] = schemeId;
				args[12] = StringUtils.isBlank(contentTypeId)?"0":contentTypeId;
			}else{
				query = getQuery("GET_HOME_COVERAGES_LIST");
				args= new Object[6];
				args[0] = quoteNo;
				args[1] = productId;
				args[2] = StringUtils.isBlank(contentTypeId)?"0":contentTypeId;
				args[3] = schemeId;
				args[4] = type;
				args[5] = agencyCode;
			}
		}
		
		removeNull(args);
		LogManager.info("HomeDao.getCoveragesList() Query: "+queryFrammer(query, args));
		List<Map<String,Object>> resultList = this.mytemplate.queryForList(query, args);
		return resultList;
	}
	
	public List<Map<String,Object>> getCoveragesList(HomeBean bean, String type, String condition) throws Exception {
		String query="";
		List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
		List<Map<String,Object>> resultList=new ArrayList<Map<String,Object>>();
		List<Map<String,Object>>[] resultListNew=new ArrayList[bean.getSchemeList().size()];
		for(int i=0;i<bean.getSchemeList().size();i++){
			query = getQuery("GET_HOME_COVERAGES_LIST") + condition;
			Object[] args = new Object[6];
			args[0] = bean.getQuoteNo();
			args[1] = bean.getProductId();
			args[2] = StringUtils.isBlank(bean.getContentTypeId())?"0":bean.getContentTypeId();
			args[3] = bean.getSchemeList().get(i);
			args[4] = type;
			args[5] = bean.getAgencyCode();
			removeNull(args);
			LogManager.info("Query==> " + query);
			LogManager.info("Args==> " + StringUtils.join(args, ", "));
			list= this.mytemplate.queryForList(query, args);
			resultList.addAll(list);
			resultListNew[i]=list;
		}
		bean.setPremiumListNew(resultListNew);
		query="SELECT SCHEME_ID,SCHEME_NAME FROM OFS_SCHEME_MASTER WHERE SCHEME_ID IN (SELECT DISTINCT SCHEME_ID FROM OFS_DATA_DETAILS WHERE QUOTE_NO=?) ORDER BY SCHEME_ID";
		
		LogManager.info("Query==> " + query);
		LogManager.info("Args==> " + StringUtils.join(bean.getQuoteNo(), ", "));
		bean.setSchemeListNew(this.mytemplate.queryForList(query, new Object[]{bean.getQuoteNo()}));
		return resultList;
	}
	public String getSubDetailReferralRemarks(String quoteNo) {
		String result = "";
		try {
			String query = getQuery("GET_HOME_SUBDETAIL_REFERRAL");
			Object[] args = new Object[1];
			args[0] = quoteNo;
			LogManager.info("Query==> " + query);
			LogManager.info("Args==> " + StringUtils.join(args, ", "));
			result = (String) this.mytemplate.queryForObject(query, args, String.class);
		} catch(Exception exception) {
			LogManager.debug(exception);
		} finally {
			result = StringUtils.isBlank(result)?"": (result + " Detail to Cover Referral");
		}
		return result;
	}
	public List<Map<String,Object>> getBuildingAddressList(String quoteNo) {
		List<Map<String,Object>> resultList = new ArrayList<Map<String,Object>>();
		try {
			String query = getQuery("GET_BUILDINGADDRESS_LIST");
			Object[] args = new Object[1];
			args[0] = quoteNo;
			LogManager.info("Query==> " + query);
			LogManager.info("Args==> " + StringUtils.join(args, ", "));
			resultList = this.mytemplate.queryForList(query, args);
		} catch(Exception exception) {
			LogManager.debug(exception);
		}
		return resultList;
	}
	public int getBaseCoverCnt(String quoteNo) {
		int resultCount = 0;
		try {
			String query = getQuery("GET_HOME_BASECOVER_CNT");
			Object[] args = new Object[1];
			args[0] = quoteNo;
			LogManager.info("Query==> " + query);
			LogManager.info("Args==> " + StringUtils.join(args, ", "));
			resultCount = this.mytemplate.queryForInt(query, args);
		} catch(Exception exception) {
			LogManager.debug(exception);
		}
		return resultCount; 
	}
	/*************** Farmer Insurance | PA Cover - Start *******************/
	public void getPaCoverDetails(HomeBean bean) {
		try {
			Map<String,Object> resultMap = getPaDetailsList(bean.getApplicationNo());
			bean.setPaPolicyType(resultMap.get("POLICY_TYPE")==null?"":resultMap.get("POLICY_TYPE").toString());
			bean.setPaIsRollPolicyYN(resultMap.get("ISROLLPOLICYYN")==null?"":resultMap.get("ISROLLPOLICYYN").toString());
			bean.setPaPrevPolicyNo(resultMap.get("PREVPOLICYNO")==null?"":resultMap.get("PREVPOLICYNO").toString());
			bean.setPaPrevExpiryDate(resultMap.get("PREVEXPIRYDATE")==null?"":resultMap.get("PREVEXPIRYDATE").toString());
			bean.setPaPrevInsCompany(resultMap.get("PREVINSURANCE")==null?"":resultMap.get("PREVINSURANCE").toString());
			bean.setPaPrevClaimAmount(resultMap.get("PREVCLAIMAMT")==null?"":resultMap.get("PREVCLAIMAMT").toString());
			
		} catch(Exception exception) {
			LogManager.debug(exception);
		}
	}
	public void updatePaCoverDetails(HomeBean bean) {
		try {
			String query = "";
			Object[] args = null;
			
			query = getQuery("CNT_FARMPA_DETAILS");
			args = new Object[]{bean.getApplicationNo()};
			LogManager.info("Query==> " + query);
			LogManager.info("Args==> " + StringUtils.join(args, ", "));
			int count = this.mytemplate.queryForInt(query, args);
			if(count==0) {
				query = getQuery("INS_FARMPA_DETAILS");
				args = new Object[9];
				args[0] = bean.getApplicationNo();
				args[1] = bean.getQuoteNo();
				args[2] = "0";
				args[3] = bean.getPaPolicyType();
				args[4] = bean.getPaIsRollPolicyYN();
				args[5] = bean.getPaPrevPolicyNo();
				args[6] = bean.getPaPrevExpiryDate();
				args[7] = bean.getPaPrevInsCompany();
				args[8] = bean.getPaPrevClaimAmount();
			} else {
				query = getQuery("UPD_FARMPA_DETAILS");
				args = new Object[9];
				args[0] = bean.getQuoteNo();
				args[1] = "0";
				args[2] = bean.getPaPolicyType();
				args[3] = bean.getPaIsRollPolicyYN();
				args[4] = bean.getPaPrevPolicyNo();
				args[5] = bean.getPaPrevExpiryDate();
				args[6] = bean.getPaPrevInsCompany();
				args[7] = bean.getPaPrevClaimAmount();
				args[8] = bean.getApplicationNo();
			}
			
			LogManager.info("Query==> " + query);
			LogManager.info("Args==> " + StringUtils.join(args, ", "));
			this.mytemplate.update(query, args);
			
		} catch(Exception exception) {
			LogManager.debug(exception);
		}
	}
	public void editpaInsuredDetails(HomeBean bean) {
		try {
			String query = getQuery("GET_FARMPA_INSURED_DETAILS");
			Object[] args = new Object[]{bean.getApplicationNo(), bean.getPaInsuredId()};
			LogManager.info("Query==> " + query);
			LogManager.info("Args==> " + StringUtils.join(args, ", "));
			Map<String,Object> resultMap = this.mytemplate.queryForMap(query, args);
			bean.setPaName(resultMap.get("INSURED_NAME")==null?"":resultMap.get("INSURED_NAME").toString());
			bean.setPaDob(resultMap.get("DOB")==null?"":resultMap.get("DOB").toString());
			bean.setPaIndurer(resultMap.get("INSURER_NAME")==null?"":resultMap.get("INSURER_NAME").toString());
			bean.setPaRelationship(resultMap.get("RELATIONSHIP")==null?"":resultMap.get("RELATIONSHIP").toString());
			bean.setPaOccupation(resultMap.get("OCCUPATION")==null?"":resultMap.get("OCCUPATION").toString());
			bean.setPaTableOfBenifits(resultMap.get("TABLEOFBENEFITS")==null?"":resultMap.get("TABLEOFBENEFITS").toString());
			bean.setPaSumInsured(resultMap.get("SUMINSURED")==null?"":resultMap.get("SUMINSURED").toString());
			bean.setPaAnnualIncome(resultMap.get("ANNUALINCOME")==null?"":resultMap.get("ANNUALINCOME").toString());
			bean.setPaFatherFirstName(resultMap.get("FATHERFIRSTNAME")==null?"":resultMap.get("FATHERFIRSTNAME").toString());
			bean.setPaFatherMiddleName(resultMap.get("FATHERMIDDLENAME")==null?"":resultMap.get("FATHERMIDDLENAME").toString());
			bean.setPaFatherLastName(resultMap.get("FATHERLASTNAME")==null?"":resultMap.get("FATHERLASTNAME").toString());
			bean.setPaExitingDisability(resultMap.get("PREEXISTING_YN")==null?"":resultMap.get("PREEXISTING_YN").toString());
			bean.setPaDisabilityDesc(resultMap.get("DISABILITY_DESC")==null?"":resultMap.get("DISABILITY_DESC").toString());
			bean.setPaOptionType(resultMap.get("OPTIONTYPE")==null?"":resultMap.get("OPTIONTYPE").toString());
			bean.setPaMedicalExtn(resultMap.get("MEDICAL_EXTENSION")==null?"":resultMap.get("MEDICAL_EXTENSION").toString());
			bean.setPaCostOfTravel(resultMap.get("TRAVEL_COST_YN")==null?"":resultMap.get("TRAVEL_COST_YN").toString());
			bean.setPaSupportingItem(resultMap.get("SUPPORTITEM_EXTENSION_YN")==null?"":resultMap.get("SUPPORTITEM_EXTENSION_YN").toString());
		} catch(Exception exception) {
			LogManager.debug(exception);
		}
	}
	public void addpaInsuredDetails(HomeBean bean) {
		try {
			if(StringUtils.isNotBlank(bean.getPaName())) {
				String query = "";
				Object[] args = null;
				if(StringUtils.isBlank(bean.getPaInsuredId())) {
					query = getQuery("GET_FARMPA_MAXINSURED_ID");
					args = new Object[]{bean.getApplicationNo()};
					LogManager.info("Query==> " + query);
					LogManager.info("Args==> " + StringUtils.join(args, ", "));
					bean.setPaInsuredId((String) this.mytemplate.queryForObject(query, args, String.class));
				}
				
				delpaInsuredDetails(bean.getApplicationNo(), bean.getPaInsuredId());
				
				query = getQuery("INS_FARMPA_INSURED_DETAILS");
				args = new Object[21];
				args[0] = bean.getApplicationNo();
				args[1] = bean.getQuoteNo();
				args[2] = "0";
				args[3] = bean.getPaInsuredId();
				args[4] = bean.getPaName();
				args[5] = bean.getPaDob();
				args[6] = bean.getPaIndurer();
				args[7] = bean.getPaRelationship();
				args[8] = bean.getPaFatherFirstName();
				args[9] = bean.getPaFatherMiddleName();
				args[10] = bean.getPaFatherLastName();
				args[11] = bean.getPaOccupation();
				args[12] = bean.getPaTableOfBenifits();
				args[13] = bean.getPaSumInsured();
				args[14] = bean.getPaAnnualIncome();
				args[15] = bean.getPaExitingDisability();
				args[16] = bean.getPaDisabilityDesc();
				args[17] = bean.getPaOptionType();
				args[18] = bean.getPaMedicalExtn();
				args[19] = bean.getPaCostOfTravel();
				args[20] = bean.getPaSupportingItem();
				LogManager.info("Query==> " + query);
				LogManager.info("Args==> " + StringUtils.join(args, ", "));
				this.mytemplate.update(query, args);
			}
		} catch(Exception exception) {
			LogManager.debug(exception);
		}
	}
	public void delpaInsuredDetails(String applicationNo, String insuredId) {
		try {
			String query = getQuery("DEL_FARMPA_INSURED_DETAILS");
			Object[] args = new Object[]{applicationNo, insuredId};
			LogManager.info("Query==> " + query);
			LogManager.info("Args==> " + StringUtils.join(args, ", "));
			this.mytemplate.update(query, args);
		} catch(Exception exception) {
			LogManager.debug(exception);
		}
	}
	public List<Map<String,Object>> getPaInsuredDetailsList(String applicationNo) {
		List<Map<String,Object>> resultList = null;
		try {
			String query = getQuery("GET_FARMPA_INSURED_DETAILS_LIST");
			Object[] args = new Object[]{applicationNo};
			LogManager.info("Query==> " + query);
			LogManager.info("Args==> " + StringUtils.join(args, ", "));
			resultList = this.mytemplate.queryForList(query, args);
		} catch(Exception exception) {
			LogManager.debug(exception);
		}
		return resultList;
	}
	public Map<String,Object> getPaDetailsList(String applicationNo) {
		Map<String,Object> resultMap = new HashMap<String, Object>();
		try {
			String query = getQuery("GET_FARMPA_DETAILS");
			Object[] args = new Object[]{applicationNo};
			LogManager.info("Query==> " + query);
			LogManager.info("Args==> " + StringUtils.join(args, ", "));
			resultMap = this.mytemplate.queryForMap(query, args);
		} catch(Exception exception) {
			LogManager.debug("Exception @getPaDetailsList()" + exception);
		}
		return resultMap;
	}
	public void paPremiumCalc(String applicationNo, String branchCode, String type) {
		try {
			LogManager.info("paPremiumCalc - Enter");
			SimpleJdbcCall procedure= mySimpleJdbcCall.withProcedureName("PA_PREMIUM_CALC");
	     	Map<String,Object> inputValues=new java.util.TreeMap<String, Object>(String.CASE_INSENSITIVE_ORDER);
	     	inputValues.put("PVAPPNO", applicationNo);
	     	inputValues.put("PVBRANCH", branchCode);
	     	inputValues.put("PVTYPE",type);
	     	procedure.execute(new MapSqlParameterSource(inputValues)); //Executes the procedure.
	     	LogManager.info("paPremiumCalc - Exit");
		} catch(Exception exception) {
			LogManager.debug(exception);
		}
	}
	public String updatepaPremiuminfo(HomeBean bean) {
		String result = "";
		try {
			if(bean.getPaInsuredIdList()!=null) {
				String query = "UPDATE PA_INSURED_DETAILS SET RATE=?,MEDICAL_EXTENSION_RATE=?,TRAVEL_COST_RATE=?,SUPPORTITEM_EXTENSION_RATE=? WHERE APPLICATION_NO=? AND INSURED_ID=?";
				Object[] args = new Object[6];
				for(int i=0 ; i<bean.getPaInsuredIdList().size() ; i++) {
					args[0] = bean.getPaInsuredRateList().get(i);
					args[1] = bean.getPaMedExtnRateList().get(i);
					args[2] = bean.getPaTravelCostRateList().get(i);
					args[3] = bean.getPaSupportRateList().get(i);
					args[4] = bean.getApplicationNo();
					args[5] = bean.getPaInsuredIdList().get(i);
					LogManager.info("Query==> " + query);
					LogManager.info("Args==> " + StringUtils.join(args, ", "));
					this.mytemplate.update(query, args);
				}
			}
			paPremiumCalc(bean.getApplicationNo(), bean.getBranchCode(), "U");
			
			result = "SUCCESS";
		}
		catch(Exception exception) {
			result = "FAILURE";
			LogManager.debug(exception);
		}
		return result;
	}
	/*************** Farmer Insurance | PA Cover - End *******************/
	/***************************** Farmer Insurance | Motor - Start ***************************************/
	public void getFirstPageDtls(HomeBean bean) {
		LogManager.info("getComparisionDetails - Enter");
		try{
			String sql=getQuery("GET_FARMMOTOR_DATA_DTLS");
			Object obj[] = new Object[]{bean.getApplicationNo(),bean.getProductId()};
			LogManager.info("Query=>"+sql);
			LogManager.info("Obj[]=>"+StringUtils.join(obj,","));
			Map<String,Object> map=this.mytemplate.queryForMap(sql,obj);
			bean.setQuoteNo(map.get("QUOTE_NO")==null?"":map.get("QUOTE_NO").toString());
			bean.setCustomerId(map.get("CUSTOMER_ID")==null?"":map.get("CUSTOMER_ID").toString());
			/*bean.setPolicyStartDate(map.get("INCEPTION_DATE")==null?"":map.get("INCEPTION_DATE").toString());
			bean.setPolicyEndDate(map.get("EXPIRY_DATE")==null?"":map.get("EXPIRY_DATE").toString());*/
			bean.setBrokerCode(map.get("BROKER_CODE")==null?"":map.get("BROKER_CODE").toString());
			bean.setExecutive(map.get("AC_EXECUTIVE_ID")==null?"":map.get("AC_EXECUTIVE_ID").toString());
			bean.setLoginId(map.get("LOGIN_ID")==null?"":map.get("LOGIN_ID").toString());
			
			if(StringUtils.isBlank(bean.getBrokerCode())) {
				try {
					String query = getQuery("broker");
					Object[] args = new Object[]{bean.getLoginId()};
					String brokerCode = (String) this.mytemplate.queryForObject(query, args, String.class);
					bean.setBrokerCode(brokerCode);
				}
				catch(Exception ex) {
					ex.printStackTrace();
				}
			}
			if(StringUtils.isBlank(bean.getExecutive())) {
				try {
					String query = getQuery("executive");
					Object[] args = new Object[]{bean.getBranchCode(),bean.getBrokerCode()};
					String executive = (String) this.mytemplate.queryForObject(query, args, String.class);
					bean.setExecutive(executive);
				}
				catch(Exception ex) {
					ex.printStackTrace();
				}
			}
		}catch(Exception e)
		{
			LogManager.debug(e);
		}
		LogManager.info("getComparisionDetails - Exit");
	}
	public String getQuoteMotor(HomeBean bean) {
		LogManager.info("getQuote - Enter ");
		/*if(!"getSave".equalsIgnoreCase(bean.getActionType()))
			getReferralCheck(bean);*/
		String result="SUCCESS";
		String sql="";
		Object[] obj=null;
		int res=0;
		try
		{
			boolean saveFlag=("getSave".equalsIgnoreCase(bean.getActionType())?true:false);
			if(StringUtils.isBlank(bean.getApplicationNo())) {
				bean.setApplicationNo(new CommonDAO().getSequenceNo("Application",bean.getProductId(),bean.getBranchCode(),"",""));
			}
			if(StringUtils.isBlank(bean.getQuoteNo())&&("getQuote".equalsIgnoreCase(bean.getActionType())||saveFlag)) {
				bean.setQuoteNo(new CommonDAO().getSequenceNo("Quote",bean.getProductId(),bean.getBranchCode(),"",""));
			}
			
			updateVehicleDetails(bean);
			updateMotorHomePosDetails(bean, saveFlag);
			updateAddVehicleDetails(bean);
			updateCoveragesDetails(bean);
			tractorCalcPremium(bean.getApplicationNo(), bean.getBranchCode(), "C");
		}catch(Exception e)
		{
			LogManager.debug(e);
			result="FAILED";
		}
		LogManager.popRemove();
		LogManager.info("getQuote - Exit");
		return result;
	}
	private void updateVehicleDetails(HomeBean bean) throws Exception {
		String sql="";
		Object[] obj = null;
		
		String vehicleId = "0";
		String query = getQuery("DEL_FARMMOTOR_VEHICLE_DETAILS");
		Object[] args = new Object[]{bean.getApplicationNo(), vehicleId};
		this.mytemplate.update(query, args);
		
		
		/*String dtlCntQuery = getQuery("GET_FARMMOTOR_DTL_CNT");
		Object[] dtlCntObj = new Object[]{bean.getApplicationNo()};
		LogManager.info("dtlCntQuery==> " + dtlCntQuery);
		LogManager.info("dtlCntObj==> " + StringUtils.join(dtlCntObj, "~~"));
		int dtlCount = this.mytemplate.queryForInt(dtlCntQuery, dtlCntObj);*/
		
		int dtlCount = 0;
		
		if(dtlCount==0) {
			sql=this.getQuery("INS_FARMMOTOR_DATA_DTLS");
            obj=new Object[35];
			obj[0] = bean.getQuoteNo();
			obj[1] = bean.getCustomerId();
			obj[2] = bean.getProductId();
			obj[3] = "0";
			obj[4] = bean.getApplicationNo();
			obj[5] = "Y";
			obj[6] = bean.getMake();
			obj[7] = bean.getModel();
			obj[8] = bean.getTypeBody();
			obj[9] = bean.getMfgYr();
			obj[10] = bean.getCubicCapacity();
			obj[11] = bean.getSeatingCapacity();
			obj[12] = bean.getSumInsured();
			obj[13] = "N"/*bean.getAgencyRepairYNIdList().get(i)*/;
	        obj[14] = bean.getNoOfCylinder();
	        obj[15] = bean.getVehicleUsage();
	        obj[16] = "";
	        obj[17] = bean.getClaimYN();
	        obj[18] = bean.getClaimAmount();
	        obj[19] = bean.getNoClaimBonus();
	        obj[20] = bean.getAreaCoverage();
	        obj[21] = bean.getDriverDOB();
	        obj[22] = ""/*bean.getPolicyStartDate()*/;
	        obj[23] = ""/*bean.getPolicyStartDate()*/;
	        obj[24] = "";// bean.getTpl()==null?"0":bean.getTpl();
	        obj[25] = "";//bean.getExcess();
	        obj[26] = "";
			obj[27] = "";
			obj[28] = bean.getVoluntaryDeductible();
			obj[29] = bean.getElectricalAcc();
			obj[30] = bean.getNonElectricalAcc();
			obj[31] = bean.getBifuelKit();
			obj[32] = bean.getRestrictedTPPDYN();
			obj[33] = bean.getNoOfTrailer();
			obj[34] = vehicleId;
			removeNull(obj);
	        LogManager.info("Sql=>=>"+sql);
			LogManager.info("Obj[]=>"+StringUtils.join(obj,","));
			int res=this.mytemplate.update(sql,obj);
			LogManager.info("Result=>"+res);
		}
		/*else {
			sql=this.getQuery("UPD_FARMMOTOR_DATA_DTLS");
			obj=new Object[25];
			obj[0] = bean.getCustomerId();
			obj[1] = bean.getMake();
			obj[2] = bean.getModel();
			obj[3] = bean.getTypeBody();
			obj[4] = bean.getMfgYr();
			obj[5] = bean.getCubicCapacity();
			obj[6] = bean.getSeatingCapacity();
			obj[7] = bean.getSumInsured();
			obj[8] = bean.getAgencyRepairYN();
	        obj[9] = bean.getNoOfCylinder();
	        obj[10] = bean.getVehicleUsage();
	        obj[11] = "";
	        obj[12] = bean.getClaimYN();
	        obj[13] = bean.getClaimAmount();
	        obj[14] = bean.getNoClaimBonus();
	        obj[15] = bean.getAreaCoverage();
	        obj[16] = bean.getDriverDOB();
	        obj[17] = bean.getPolicyStartDate();
	        obj[18] = bean.getPolicyStartDate();
	        obj[19] = "";// bean.getTpl()==null?"0":bean.getTpl();
	        obj[20] = "";//bean.getExcess();
	        obj[21] = "";//bean.getDriverNationalityList().get(i);
			obj[22] = "";//bean.getUaeLicExpDTIdList().get(i);
	        obj[23] = bean.getApplicationNo();
	        obj[24] = bean.getApplicationNo();
	        removeNull(obj);
	        LogManager.info("Sql=>=>"+sql);
			LogManager.info("Obj[]=>"+StringUtils.join(obj,","));
			int res=this.mytemplate.update(sql,obj);
			LogManager.info("Result=>"+res);
		}*/
		updateAddVehicleDetails(bean);
	}
	
	private void updateAddVehicleDetails(HomeBean bean) {
		try {
			String query = getQuery("UPD_FARMMOTOR_ADDVEHICLE_DTLS");
			Object[] args = new Object[33];
			args[0] = bean.getVehicleColour();
			args[1] = bean.getLeasedYN();
			args[2] = StringUtils.isBlank(bean.getBankOfFinance())?"":bean.getBankOfFinance();
			args[3] = bean.getRegNo();
			args[4] = bean.getChassisNo();
			args[5] = bean.getEngineNo();
			args[6] = bean.getInsNameArabic();
			args[7] = bean.getInsAddressArabic();
			args[8] = bean.getVehicleRegLoc();
			args[9] = bean.getPlateCharacter();
			args[10] = "";
			
			args[11] = bean.getManufactureDate();
			args[12] = bean.getMotorLampsYN();
			args[13] = bean.getTrailerIdv1();
			args[14] = bean.getTrailer1MfrDate();
			args[15] = bean.getMotorPolicyType();
			args[16] = bean.getTrailerIdv2();
			args[17] = bean.getTrailer2MfrDate();
			args[18] = bean.getOwnerDriver();
			args[19] = bean.getUnNamedPassengersNos();
			args[20] = bean.getUnNamedPassengersSi();
			args[21] = bean.getPaidDriversNos();
			args[22] = bean.getPaidDriversSi();
			args[23] = bean.getLegalLibCover();
			args[24] = bean.getDriverCondCleaner();
			args[25] = bean.getNonFarePassenger();
			args[26] = bean.getEmployeesInOperation();
			args[27] = bean.getLtFinanceYN();
			args[28] = bean.getTrailer1RegNo();
			args[29] = bean.getTrailer2RegNo();
			
			args[30] = bean.getApplicationNo();
			args[31] = "0";
			args[32] = bean.getApplicationNo();
			removeNull(args);
			LogManager.info("Sql=>=>"+args);
			LogManager.info("Obj[]=>"+StringUtils.join(args,","));
			this.mytemplate.update(query, args);
		}
		catch(Exception e) {
			LogManager.debug(e);
		}
	}
	
	public List<Map<String,Object>> getMultiVehicleDetails(String applicationNo, String productId, String branchCode) {
		List<Map<String,Object>> resultList = null;
		try {
			String query = getQuery("GET_FARMMOTOR_MULTI_DATA_DETAILS");
			Object[] args = new Object[]{applicationNo, productId, branchCode};
			LogManager.info("Query==>" + query);
			LogManager.info("Args==>" + StringUtils.join(args,", "));
			resultList = this.mytemplate.queryForList(query, args);
		}
		catch (Exception e) {
			LogManager.debug("Exception occured @ getMultiVehicleDetails{"+e+"}");
		}
		return resultList;
	}
	
	public List<Map<String,Object>> getFarmCoverDetailsList(String applicationNo) {
		List<Map<String,Object>> resultList = null;
		try {
			String query = getQuery("GET_FARMMOTOR_COVER_DETAILS");
			Object[] args = new Object[]{applicationNo};
			removeNull(args);
			LogManager.info("Query==>" + query);
			LogManager.info("Args==>" + StringUtils.join(args,", "));
			resultList = this.mytemplate.queryForList(query, args);
		}
		catch (Exception e) {
			LogManager.debug("Exception occured @ getFarmCoverDetailsList{"+e+"}");
		}
		return resultList;
	}
	
	private void updateMotorHomePosDetails(HomeBean bean, boolean saveFlag) throws Exception {
		String posCntQuery = getQuery("GET_HOM_POS_CNT");
		Object[] posCntObj = new Object[]{bean.getApplicationNo()};
		LogManager.info("posCntQuery==> " + posCntQuery);
		LogManager.info("posCntObj==> " + StringUtils.join(posCntObj, "~~"));
		int posCount = this.mytemplate.queryForInt(posCntQuery, posCntObj);
		
		String sql = "";
		Object[] obj = null;
		
		if(posCount==0) {
			obj=new Object[16];
			if(StringUtils.isNotBlank(bean.getReferralMsg())){
				sql=getQuery("INS_FARMMOTOR_HOME_POS_DTLS_REF");
				obj[0] = bean.getQuoteNo();
				obj[1] = bean.getCustomerId();
				obj[2] = bean.getLoginId();
				obj[3] = bean.getProductId();
				obj[4] = "0";
				obj[5] = bean.getApplicationNo();
				obj[6] = saveFlag?"S":"Y";
				obj[7] = "0";
				obj[8] = bean.getFleetNo();
				obj[9] = bean.getBranchCode();
				obj[10] = StringUtils.isBlank(bean.getIssuer())?"1":bean.getIssuer();
				obj[11] = bean.getReferralMsg();//Referal Msg
				obj[12] = bean.getBrokerCode();
				obj[13] = bean.getExecutive();
				obj[14] = bean.getSchemeId();
				obj[15] = bean.getContentTypeId();
			}
			else {
				sql=getQuery("INS_FARMMOTOR_HOME_POS_DTLS");
				obj[0] = bean.getQuoteNo();
				obj[1] = bean.getCustomerId();
				obj[2] = bean.getLoginId();
				obj[3] = bean.getProductId();
				obj[4] = "0";
				obj[5] = bean.getApplicationNo();
				obj[6] = saveFlag?"S":"Y";
				obj[7] = "0";
				obj[8] = bean.getFleetNo();
				obj[9] = bean.getBranchCode();
				obj[10]=StringUtils.isBlank(bean.getIssuer())?"1":bean.getIssuer();
				obj[11]="";//admin remarks
				obj[12]=bean.getBrokerCode();
				obj[13]=bean.getExecutive();
				obj[14] = bean.getSchemeId();
				obj[15] = bean.getContentTypeId();
			}
			removeNull(obj);
			LogManager.info("Sql=>=>"+sql);
			LogManager.info("Obj[]=>"+StringUtils.join(obj,","));
			int res=this.mytemplate.update(sql,obj);
			LogManager.info("Result=>"+res);
		}
		else {
			String status = "";
			if(saveFlag)
				status="S";
			else if(StringUtils.isBlank(bean.getAmendId())||"0".equals(bean.getAmendId()))
			
				status="Y";
			else if(!"0".equals(bean.getAmendId()))
				status="E";
			
			/*if("admin".equals(bean.getUser())||"RA".equalsIgnoreCase(bean.getMenuType())) {
				sql=getQuery("UPD_FARMMOTOR_HOME_POS_DTLS_ADMIN");
				obj=new Object[5];
				obj[0] = "";
				obj[1] = status;
				obj[2] = bean.getBrokerCode()==null?"":bean.getBrokerCode();
				obj[3] = bean.getExecutive()==null?"":bean.getExecutive();
				obj[4] = bean.getApplicationNo();
			}
			else {*/
				obj=new Object[6];
				if(StringUtils.isNotBlank(bean.getReferralMsg())){
					sql=this.getQuery("UPD_FARMMOTOR_HOME_POS_REF");
				}
				else {
					sql=this.getQuery("UPD_FARMMOTOR_HOME_POS_DTLS");
				}
				obj[0] = StringUtils.isNotBlank(bean.getReferralMsg())?bean.getReferralMsg():"";
				obj[1] = status;
				obj[2] = bean.getBrokerCode()==null?"":bean.getBrokerCode();
				obj[3] = bean.getExecutive()==null?"":bean.getExecutive();
				obj[4] = StringUtils.isBlank(bean.getIssuer())?"1":bean.getIssuer();
				obj[5] = bean.getApplicationNo();
			/*}*/
			
			LogManager.info("Sql=>=>"+sql);
			LogManager.info("Obj[]=>"+StringUtils.join(obj,","));
			int res=this.mytemplate.update(sql,obj);
			LogManager.info("Result=>"+res);
		}
	}
	private void updateCoveragesDetails(HomeBean bean) throws Exception {
		String query = getQuery("DEL_FARMMOTOR_COVER_DETAIL");
		Object[] args = new Object[]{bean.getApplicationNo()};
		LogManager.info("query=>=>"+query);
		LogManager.info("args[]=>"+StringUtils.join(args,","));
		this.mytemplate.update(query, args);
		query = getQuery("INS_FARMMOTOR_COVER_DETAIL");
		LogManager.info("query=>=>"+query);
		for(int i=0 ; i<bean.getCoverageIdList().size() ; i++) {
			args = new Object[6];
			args[0] = bean.getApplicationNo();
			args[1] = bean.getQuoteNo();
			args[2] = bean.getCoverageIdList().get(i);
			args[3] = bean.getCoverageNameList().get(i);
			args[4] = "";
			args[5] = bean.getCoverageValueList().get(i);
			LogManager.info("args[]=>"+StringUtils.join(args,","));
			this.mytemplate.update(query, args);
		}
		args[0] = bean.getApplicationNo();
		args[1] = bean.getQuoteNo();
		args[2] = "101";
		args[3] = "No Claim Bonus";
		args[4] = bean.getNoClaimBonusVal();
		args[5] = "";
		LogManager.info("args[]=>"+StringUtils.join(args,","));
		this.mytemplate.update(query, args);
		args[0] = bean.getApplicationNo();
		args[1] = bean.getQuoteNo();
		args[2] = "102";
		args[3] = "OD";
		args[4] = bean.getLoadingOd();
		args[5] = "";
		LogManager.info("args[]=>"+StringUtils.join(args,","));
		this.mytemplate.update(query, args);
		args[0] = bean.getApplicationNo();
		args[1] = bean.getQuoteNo();
		args[2] = "103";
		args[3] = "TP";
		args[4] = bean.getLoadingTp();
		args[5] = "";
		LogManager.info("args[]=>"+StringUtils.join(args,","));
		this.mytemplate.update(query, args);
	}
	private void tractorCalcPremium(String applicationNo, String branchCode, String type) throws Exception {
		LogManager.info("tractorCalcPremium - Enter");
		SimpleJdbcCall procedure= mySimpleJdbcCall.withProcedureName("MOTOR_TRACTOR_PREM_CALC");
     	Map<String,Object> inputValues=new java.util.TreeMap<String, Object>(String.CASE_INSENSITIVE_ORDER);
     	inputValues.put("PVAPPNO",applicationNo);
     	inputValues.put("PVBRANCH",branchCode);
     	inputValues.put("PVTYPE",type);
     	procedure.execute(new MapSqlParameterSource(inputValues)); //Executes the procedure.
     	LogManager.info("tractorCalcPremium - Exit");
	}
	
	public String getCoverPremium(String quoteNo, String type) {
		String result = "";
		try {
			String query = null;
			Object[] args = null;
			if("paCover".equalsIgnoreCase(type)) {
				query = getQuery("GET_FARMPA_PREMIUM");
				args = new Object[]{quoteNo};
			}
			else if("motor".equalsIgnoreCase(type)) {
				query = getQuery("GET_FARMMOTOR_PREMIUM");
				args = new Object[]{quoteNo};
			}
			else if("home".equalsIgnoreCase(type)) {
				query = getQuery("GET_FARMHOME_PREMIUM");
				args = new Object[]{quoteNo, quoteNo};
			}
			result = (String) this.mytemplate.queryForObject(query, args, String.class);
		}
		catch(Exception exception) {
			LogManager.debug("Exception @getCoverPremium() " + exception);
		}
		return StringUtils.isBlank(result)?"0":result;
	}
	
	public String getCoverReferralRemarks(String quoteNo, String type) {
		String result = "";
		try {
			String query = null;
			Object[] args = null;
			if("paCover".equalsIgnoreCase(type)) {
				query = getQuery("GET_FARMPA_REFERRALREMARKS");
				args = new Object[]{quoteNo};
			}
			else if("motor".equalsIgnoreCase(type)) {
				query = getQuery("GET_FARMMOTOR_REFERRALREMARKS");
				args = new Object[]{quoteNo};
			}
			else if("home".equalsIgnoreCase(type)) {
				query = getQuery("GET_FARMHOME_REFERRALREMARKS");
				args = new Object[]{quoteNo};
			}
			else {
				query = getQuery("GET_HOME_REFERRALREMARKS");
				args = new Object[]{quoteNo};
			}
			result = (String) this.mytemplate.queryForObject(query, args, String.class);
		}
		catch(Exception exception) {
			//LogManager.debug("Exception @ getCoverReferralRemarks(){ " + exception + " } ");
		}
		return StringUtils.isBlank(result)?"":result;
	}
	
	public List<Map<String,Object>> getTractorCoverList(String applicationNo) {
		List<Map<String,Object>> resultList = null;
		try {
			String query = getQuery("GET_FARMMOTOR_PREMIUM_INFO");
			Object[] args = new Object[]{applicationNo};
			removeNull(args);
			LogManager.info("Query==>" + query);
			LogManager.info("Args==>" + StringUtils.join(args,", "));
			resultList = this.mytemplate.queryForList(query, args);
		}
		catch (Exception e) {
			LogManager.debug("Exception occured @ getTracterCoverList{"+e+"}");
		}
		return resultList;
	}
	public String updatetractorPremiuminfo(HomeBean bean) {
		String result = "";
		try {
			tractorCalcPremium(bean.getApplicationNo(), bean.getBranchCode(), "C");
			String query = getQuery("UPD_FARMMOTOR_COVER_RATE");
			LogManager.info("Query==>" + query);
			Object[] args = new Object[3];
			if(bean.getTractorSubCoverList()!=null) {
				for(int i=0 ; i<bean.getTractorSubCoverList().size() ; i++) {
					args[0] = StringUtils.isBlank(bean.getTractorRateList().get(i))?"":bean.getTractorRateList().get(i);
					args[1] = bean.getApplicationNo();
					args[2] = bean.getTractorSubCoverList().get(i);
					LogManager.info("Args==>" + StringUtils.join(args,", "));
					this.mytemplate.update(query, args);
				}
			}
			tractorCalcPremium(bean.getApplicationNo(), bean.getBranchCode(), "U");
			
			result = "SUCCESS";
		}
		catch(Exception exception) {
			result = "FAILURE";
			LogManager.debug(exception);
		}
		return result;
	}
	/***************************** Farmer Insurance | Motor - End ***************************************/

	public List<Map<String, Object>> getHomePolicyDetails(String policyNo,String productId, String branchCode) {
		List<Map<String, Object>> result=null;
		try{
			String query = getQuery("GET_HOME_POLICY_DETAILS");
			LogManager.info("Query => "+query);
			Object args[]={policyNo,productId};
			LogManager.info("Arguments => "+StringUtils.join(args,","));
			result=this.mytemplate.queryForList(query,args);
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

	public String getEmailCount(HomeBean bean) {
		String result="";
		try{
			String query=getQuery("GET_USER_EMAIL_COUNT");
			LogManager.info("Query => "+query);
			Object[] args;
			args=new Object[]{bean.getApplicationNo()};
			LogManager.info("Arguments => "+StringUtils.join(args,","));
			result = (String)this.mytemplate.queryForObject(query,args,String.class);
			
		}catch(Exception e){
			LogManager.info("Exception Occured @ getemailCount"+e);
			e.printStackTrace();
		}
		return result;
	}

	public List<Map<String, Object>> getSumInsuredList(HomeBean bean) {
		List<Map<String, Object>> result=null;
		try{
			//String query = getQuery("GET_HOME_POLICY_DETAILS");SELECT * FROM OFS_DATA_DETAILS WHERE AMEND_ID=(SELECT MAX(AMEND_ID) FROM OFS_DATA_DETAILS WHERE QUOTE_NO=? ) AND QUOTE_NO=? ORDER BY COVERAGES_ID

			String query ="SELECT * FROM OFS_DATA_DETAILS WHERE AMEND_ID=(SELECT MAX(AMEND_ID) FROM OFS_DATA_DETAILS WHERE QUOTE_NO='"+bean.getQuoteNo()+"' ) AND QUOTE_NO='"+bean.getQuoteNo()+"' ORDER BY COVERAGES_ID";
			LogManager.info("Query => "+query);
			Object args[]={};
			LogManager.info("Arguments => "+StringUtils.join(args,","));
			//result=this.mytemplate.queryForList(query,args);
			result=this.mytemplate.queryForList(query);
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

	public void ofsDataDetailTempInsert(HomeBean bean) {
		String query="";
		try{
			for(int i=1;i<=bean.getSUM_INSURED().size();i++){
				String sumIns=bean.getSUM_INSURED().get(i).toString();
				String covId=bean.getCOVER_ID().get(i).toString();
				query ="insert into OFS_DATA_DETAILS(quote_no,amend_id,coverages_id,coverages_suminsured,status,product_id,scheme_id,content_type_id) values ('"+bean.getQuoteNo()+"','9999','"+covId+"','"+sumIns+"','T','"+bean.getProductId()+"','"+bean.getSchemeId()+"','"+bean.getContentTypeId()+"')";
				LogManager.info("Query => "+query);
				Object args[]={};
				LogManager.info("Arguments => "+StringUtils.join(args,","));
				this.mytemplate.update(query);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public void deleteOfsDataDtlTempIns(HomeBean bean) {
		try {
			String query ="DELETE FROM OFS_DATA_DETAILS WHERE quote_no='"+bean.getQuoteNo()+"' and status='T' and amend_id='9999' ";
			LogManager.info("Query => "+query);
			Object args[]={};
			LogManager.info("Arguments => "+StringUtils.join(args,","));
			this.mytemplate.update(query);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
	}

	public void setQuoteSchemeDtls(HomeBean bean) {
		try {
			List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
			//List<String> reslist=new ArrayList<String>();
			String sql=getQuery("GET_QUOTE_SCHEME_ID");
			Object args[]={bean.getQuoteNo()};
			list=this.mytemplate.queryForList(sql, args);
			String schemeSelected="";
			for (int i=0;i<list.size();i++){
				//reslist.add(list.get(i).get("SCHEME_ID").toString());
				schemeSelected+=list.get(i).get("SCHEME_ID").toString()+",";
			}
			//bean.setSchemeList(reslist);
			schemeSelected=schemeSelected.replaceAll(",$","");
			if(list!=null && list.size()>0)
				bean.setSchemeSelected(schemeSelected);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<Map<String, Object>> extendedCoverList(HomeBean bean) {
		List<Map<String, Object>> list= new ArrayList<Map<String,Object>>();
		try {
			String sql=getQuery("GET_EXTENDED_COVER_LIST");
			Object args[]={bean.getProductId(),bean.getSchemeId(),quoteBelongingAgencyCode(bean),"01"};
			LogManager.info("HomeDao.extendedCoverList() Query: "+queryFrammer(sql, args)); 
			list=this.mytemplate.queryForList(sql, args);
		} catch (Exception e) {
			LogManager.info("Exception @ HomeDao.extendedCoverList() Error : "+e); 
			e.printStackTrace();
		}
		return list;
	}

	public int insExtendedCoverage(HomeBean bean) {
		int res=0;
		try {
			String sql=getQuery("GET_DELETE_EXIST_EXTENDED_COVER");
			Object obj[]={bean.getQuoteNo(),bean.getSchemeId()};
			LogManager.info("HomeDao.insExtendedCoverage() Query: "+queryFrammer(sql, obj));
			res=this.mytemplate.update(sql, obj);
			String cover=bean.getExtendedCover();
			if(StringUtils.isNotBlank(cover)){
				String[] covers=cover.split(",");
				if(covers!= null && covers.length>0){
					for(int i=0;i<covers.length;i++){
						String query=getQuery("GET_INSERT_EXTENDED_COVER");
						Object args[]={bean.getProductId(),bean.getQuoteNo(),bean.getSchemeId(),covers[i],
								bean.getProductId(),bean.getSchemeId(),covers[i],quoteBelongingAgencyCode(bean)};
						LogManager.info("HomeDao.insExtendedCoverage() Query: "+queryFrammer(query, args));
						res=this.mytemplate.update(query, args);
					}
				}
			}
		} catch (Exception e) {
			LogManager.info("Exception @ HomeDao.insExtendedCoverage() Error: "+e);
			e.printStackTrace();
		}
		return res;
	}

	public List<Map<String, Object>> warrantiesList(HomeBean bean) {
		List<Map<String, Object>> list= new ArrayList<Map<String,Object>>();
		try {
			String query = "SELECT WARRENTY_ID, WARRENTY_DESCRIPTION"
					+ " FROM OFS_WARRENTY_DETAILS WHERE QUOTE_NO = ? AND SCHEME_ID = ? AND PRODUCT_ID = ? ORDER BY SNO ASC";
			Object args[] = new Object[] {bean.getQuoteNo(), bean.getSchemeId(), bean.getProductId()};
			LogManager.info("Home.warrantiesList() Query: "+queryFrammer(query, args));
			list = this.mytemplate.queryForList(query, args);
			if(list == null || list.size()<=0){
				String query1=getQuery("GET_HOME_WARRANTIES_LIST");
				Object args1[]={bean.getProductId(),bean.getSchemeId()};
				LogManager.info("Home.warrantiesList() Query: "+queryFrammer(query1, args1));
				list=this.mytemplate.queryForList(query1, args1);
			}
		} catch (Exception e) {
			LogManager.info("Exception @ HomeDao.warrantiesList() Error: "+e);
			e.printStackTrace();
		}
		return list;
	}

	public List<Map<String, Object>> exclusionsList(HomeBean bean) {
		List<Map<String, Object>> list= new ArrayList<Map<String,Object>>();
		try {
			String query = "SELECT EXCLUSION_ID, EXCLUSION_DESCRIPTION"
					+ " FROM OFS_EXCLUSION_DETAILS WHERE QUOTE_NO = ? AND SCHEME_ID = ? AND PRODUCT_ID = ? ORDER BY SNO ASC";
			Object args[] = new Object[] {bean.getQuoteNo(), bean.getSchemeId(), bean.getProductId()};
			LogManager.info("Home.exclusionsList() Query: "+queryFrammer(query, args));
			list = this.mytemplate.queryForList(query, args);
			if(list == null || list.size()<=0){
				String query1=getQuery("GET_HOME_EXCLUSION_LIST");
				Object args1[]={bean.getProductId(),bean.getSchemeId()};
				LogManager.info("Home.exclusionsList() Query: "+queryFrammer(query1, args1));
				list=this.mytemplate.queryForList(query1, args1);
			}
		} catch (Exception e) {
			LogManager.info("Exception @ HomeDao.exclusionsList() Error: "+e);
			e.printStackTrace();
		}
		return list;
	}

	public List<Map<String, Object>> excessList(HomeBean bean) {
		List<Map<String, Object>> list= new ArrayList<Map<String,Object>>();
		try {
			String query = "SELECT EXCESS_ID, EXCESS_PERCENT, EXCESS_AMOUNT, EXCESS_DESCRIPTION"
					+ " FROM OFS_EXCESS_RATE_DETAILS WHERE QUOTE_NO = ? AND SCHEME_ID = ? AND PRODUCT_ID = ? ORDER BY SNO ASC";
			Object args[] = new Object[] {bean.getQuoteNo(), bean.getSchemeId(), bean.getProductId()};
			LogManager.info("Home.excessList() Query: "+queryFrammer(query, args));
			list = this.mytemplate.queryForList(query, args);
			if(list == null || list.size()<=0){
				String query1 = getQuery("GET_HOME_EXCESS_LIST");
				Object args1[]={bean.getProductId(),bean.getSchemeId()};
				LogManager.info("Home.excessList() Query: "+queryFrammer(query1, args1));
				list=this.mytemplate.queryForList(query1, args1);
			}
		} catch (Exception e) {
			LogManager.info("Exception @ Home.excessList() Error: "+e);
			e.printStackTrace();
		}
		return list;
	}

	public void setSelectedCover(HomeBean bean) {
		List<Map<String, Object>> list= new ArrayList<Map<String,Object>>();
		try {
			String covers="";
			String sql=getQuery("GET_SELECTED_ADDON_COVER");
			Object args[]={bean.getProductId(),bean.getSchemeId(),bean.getQuoteNo()};
			LogManager.info("HomeDao.setSelectedCover() Query: "+queryFrammer(sql, args));
			list=this.mytemplate.queryForList(sql, args);
			for(int i=0;i<list.size();i++){
				covers+=list.get(i).get("OPCOVEREXT_ID").toString()+",";
			}
			covers=covers.replaceAll(",$","");
			bean.setCoverSelected(covers);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int checkSchemeExist(HomeBean bean, String schemeId) {
		int res=0;
		try {
			String sql=getQuery("GET_CHECK_EXIST_SCHEME");
			Object args[]={bean.getQuoteNo(),schemeId};
			LogManager.info("checkSchemeExist Query => "+sql);
			LogManager.info("checkSchemeExist Arguments => "+StringUtils.join(args,","));
			res=this.mytemplate.queryForInt(sql, args);
			if(res>0){
				String qry=getQuery("GET_DELETE_EXIST_SCHEME");
				Object obj[]={bean.getQuoteNo(),schemeId};
				LogManager.info("checkSchemeExist Query => "+qry);
				LogManager.info("checkSchemeExist Arguments => "+StringUtils.join(obj,","));
				res=this.mytemplate.update(qry, obj);
				
				String query=getQuery("GET_DELETE_EXIST_ADDON_DTL");
				Object objNew[]={bean.getQuoteNo(),schemeId};
				LogManager.info("insExtendedCoverage Query 1=> "+query);
				LogManager.info("insExtendedCoverage Arguments 1=> "+StringUtils.join(objNew,","));
				res=this.mytemplate.update(query, objNew);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public void setExistSchemeDtls(HomeBean bean) {
		try {
			List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
			//List<String> reslist=new ArrayList<String>();
			String sql=getQuery("GET_EXIST_SCHEME_DETAIL");
			Object args[]={bean.getQuoteNo()};
			list=this.mytemplate.queryForList(sql, args);
			String schemeSelected="";
			for (int i=0;i<list.size();i++){
				//reslist.add(list.get(i).get("SCHEME_ID").toString());
				schemeSelected+=list.get(i).get("SCHEME_ID").toString()+",";
			}
			//bean.setSchemeList(reslist);
			schemeSelected=schemeSelected.replaceAll(",$","");
			bean.setExistSchemeSelected(schemeSelected);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void updateCalculatedSumIns(HomeBean bean) {
		try{
			for(int i=1;i<=bean.getCOVER_ID().size();i++){
				String sql=getQuery("GET_UPDATE_SI_DETAIL");
				Object args[]={bean.getQuoteNo(),bean.getCOVER_ID().get(i),bean.getSchemeId(),bean.getLocationId()};
				LogManager.info("HomeDao.updateCalculatedSumIns() Query: "+queryFrammer(sql, args));
				this.mytemplate.update(sql, args);
			}
		}catch(Exception e){
			LogManager.info("Exception @ HomeDao.updateCalculatedSumIns() Error: "+e);
			e.printStackTrace();
		}
		
	}

	public int updateExcess(HomeBean bean) {
		int res=0;
		try{
			String sql=getQuery("GET_UPDATE_EXCESS_DETAIL");
			Object args[]={bean.getExcessPercent(),bean.getExcessAmount(),bean.getExcessDesc(),bean.getProductId(),bean.getSchemeId()};
			res=this.mytemplate.update(sql, args);
			LogManager.info("updateExcess Query => "+sql);
			LogManager.info("updateExcess Arguments => "+StringUtils.join(args,","));
		}catch(Exception e){
			e.printStackTrace();
		}
		return res;
	}
	
	public List<Double> updatePremiumNew(double total_premium,String referral,String userType,String quoteNo,String productId,String schemeId,String contentTypeId,String menuType,String branchCode) {
		List<Double> myPremiumList=new ArrayList<Double>();
		double myPremium=0.00;
		 //String val = "";
		 String query = "",temp_qry="",refStatus="";
		 double min_premium = 0.00;
		 Object values[]=null;

		 List<Map<String,Object>> value = getReferalInfo(quoteNo, productId, schemeId, contentTypeId);
		 if(value.size()>0){
			 Map<String,Object> map= value.get(0);
			 if(map.get("REFERRAL_DESCRIPTION")!=null )
				 refStatus = map.get("REFERRAL_DESCRIPTION").toString().trim();
		 }
		 // scheme master premium
		 try {
			 query = getQuery("GET_HOME_MINIMUM_PREMIUM");
			 values=new Object[]{productId, schemeId, contentTypeId};
			 LogManager.info("Query=>"+query);
			 for(Object k:values)
				 LogManager.info("args=>"+k);
			 List<Map<String,Object>> list=this.mytemplate.queryForList(query,values);
			 if(list != null && list.size()>0) {
				 Map<String,Object> map = list.get(0);
				 min_premium=Double.parseDouble(map.get("minimum_premium")==null?"0":map.get("minimum_premium").toString());
			 }
			 if(min_premium>total_premium)
				 total_premium=min_premium;

		 }
		 catch (Exception ex) {
			 ex.printStackTrace();
		 }
		 // min premium from broker 
		 try{
			 values[0]=quoteNo;
			 values[1]=productId;
			 values[2]=schemeId;

			 query =getQuery("GET_HOME_MIN_PREMIUM_BROKER");
			 LogManager.info("Query=>"+query);
			 LogManager.info("Args==> " + StringUtils.join(values, ", "));
			 List<Map<String,Object>> list=this.mytemplate.queryForList(query,values);
			 if(list != null && list.size()>0){
				 Map<String,Object> map = list.get(0);
				 min_premium=Double.parseDouble(map.get("min_premium_amount").toString());
			 }
			 if(min_premium>total_premium)
				 total_premium=min_premium;
		 }
		 catch (Exception ex) {
			 ex.printStackTrace();
		 }
		 //if(referral.equalsIgnoreCase("CusInfo"))
			// return total_premium;

		 try {
			 //double policyFee = Double.parseDouble(getPolicyFees(quoteNo, userType, menuType, branchCode));
			 com.maan.common.dao.CommonDAO commonDAO = new com.maan.common.dao.CommonDAO();
			 double policyFee = Double.parseDouble(commonDAO.calculatePolicyFee(String.valueOf(total_premium), branchCode));
			 myPremium = total_premium + policyFee;
			
			 myPremiumList.add(0, myPremium);
			 myPremiumList.add(1, total_premium);
			 myPremiumList.add(2, policyFee);

		 }catch (Exception ex) {
			 ex.printStackTrace();
		 }
		 return myPremiumList;
	 }

	public String[] getExtendedCoverageCount(HomeBean bean, List<String> schemelist) {
		String[] res=new String[2];
		List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
		String schemeName="";
		String status="";
		String err="";
		String ref="";
		try{
			for(int i=0;i<bean.getSchemeList().size();i++){
				String sql="SELECT OAM.OPCOVEREXT_ID,OAM.OPCOVEREXT_DESC,OAM.STATUS FROM OFS_ADDON_EXTENSION_MASTER OAM, OFS_ADDON_EXTENSION_DETAILS OAD WHERE OAM.SCHEME_ID=OAD.SCHEME_ID AND OAM.OPCOVEREXT_ID=OAD.OPCOVEREXT_ID AND OAD.QUOTE_NO=? AND OAM.SCHEME_ID=? ORDER BY OPCOVEREXT_ID";//getQuery("GET_EXTENDED_COVER_COUNT");
				Object args[]={bean.getQuoteNo(),schemelist.get(i)};
				list=this.mytemplate.queryForList(sql, args);
				
				if(list.size()<=0){
					String sqlQry="SELECT COUNT(*) FROM OFS_ADDON_EXTENSION_MASTER WHERE PRODUCT_ID=? AND SCHEME_ID=?";
					Object args1[]={bean.getProductId(),schemelist.get(i)};
					int cnt=this.mytemplate.queryForInt(sqlQry, args1);
					if(cnt>0){
						String query="SELECT SCHEME_NAME FROM OFS_SCHEME_MASTER WHERE SCHEME_ID=?";
						Object obj[]={schemelist.get(i)};
						schemeName=(String)this.mytemplate.queryForObject(query,obj, String.class);
						err+="& "+schemeName+" ";
						res[0]="0";
					}
				}
				else{
					for(int j=0;j<list.size();j++){
						if("R".equalsIgnoreCase(list.get(j).get("STATUS").toString())){
							ref+=", "+list.get(j).get("OPCOVEREXT_DESC").toString();
						}
					}
					//res=new String[2];
					//res[0]="1";
					//res[1]=StringUtils.isBlank(ref)?"":"Extended Coverage - "+ref+" are in Referral";
				}
			}
			res[0]=StringUtils.isBlank(res[0])?"":res[0];
			res[1]=StringUtils.isBlank(err)?"":"Please Select Extended Coverage in Extra Covers For  - "+err.substring(1);
			ref=StringUtils.isBlank(ref)?"":"Extended Coverage - "+ref.substring(1)+" are in Referral";
			if(StringUtils.isNotBlank(ref)){
				String sql="UPDATE OFS_ADDON_EXTENSION_DETAILS SET REFERRAL_REMARKS=? WHERE QUOTE_NO=?";
				Object args[]={ref,bean.getQuoteNo()};
				this.mytemplate.update(sql,args);
			}else{
				String sql="UPDATE OFS_ADDON_EXTENSION_DETAILS SET REFERRAL_REMARKS='' WHERE QUOTE_NO=?";
				Object args[]={bean.getQuoteNo()};
				this.mytemplate.update(sql,args);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return res;
	}

	public String getExtendedReferralRemarks(String quoteNo, String type) {
		String result="";
		try {
			String query = "SELECT REFERRAL_REMARKS FROM OFS_ADDON_EXTENSION_DETAILS WHERE QUOTE_NO=? AND ROWNUM='1'";
			Object[] args = new Object[]{quoteNo};
			result = (String) this.mytemplate.queryForObject(query, args, String.class);
		} catch (Exception e) {
			result="";
		}
		return StringUtils.isBlank(result)?"":result;
	}

	public List<Map<String,Object>> setLossLimbsList(HomeBean bean) {
		List<Map<String,Object>> result=new ArrayList<Map<String,Object>>();
		String lossLimbs="";
		try {
			String query = "SELECT CATEGORY_DETAIL_ID LISTKEY, DETAIL_NAME LISTVALUE FROM CONSTANT_DETAIL WHERE CATEGORY_ID='208' AND CATEGORY_DETAIL_ID=?";
			Object[] args = new Object[]{bean.getLossOfLimbs()};
			result = this.mytemplate.queryForList(query, args);
			//if(result.size()>0)
				//bean.setLossOfLimbs(result.get(0).get("DETAIL_NAME")==null?"":result.get(0).get("DETAIL_NAME").toString());
				
			
		} catch (Exception e) {
			lossLimbs="";
		}
		return result;
	}
	
	public void updateReferralSumInsRemarks( HomeBean bean, String referralComments){
		LogManager.push("updateReferralSumInsRemarks method() Enter");
		try{
			String sql="update OFS_DATA_DETAILS set REFERRAL_REMARKS=? where quote_no=? and SCHEME_ID=?";
			//String sql="update OFS_DATA_DETAILS set REFERRAL_REMARKS=? where quote_no=?";
			LogManager.info("Query=>"+sql);
			Object[] args = new Object[]{referralComments, bean.getQuoteNo(),bean.getSchemeId()};
			//Object[] args = new Object[]{referralComments, bean.getQuoteNo()};
			removeNull(args);
			this.mytemplate.update(sql, args);
		}catch(Exception e){
			LogManager.debug(e);
		}
		LogManager.info("updateReferralSumInsRemarks method() Exit");
	}

	public List<Map<String, Object>> getReferralList(HomeBean bean) {
		LogManager.push("getReferralList method() Enter");
		List<Map<String, Object>> res=new ArrayList<Map<String,Object>>();
		try {
			String sql="SELECT DISTINCT SCHEME_ID, REFERRAL_REMARKS FROM OFS_DATA_DETAILS WHERE QUOTE_NO=? ORDER BY SCHEME_ID";
			Object[] args = new Object[]{bean.getQuoteNo()};
			res=this.mytemplate.queryForList(sql,args);
			LogManager.info("getReferralList method() Exit");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	/*public int getInsertLocationDetails(HomeBean bean) {
		LogManager.push("getInsertLocationDetails method() Enter");
		int res=0;
		try{
			//bean.setLocationIds(locationIds);
			String delQry="DELETE FROM OFS_LOCATION_DETAILS WHERE QUOTE_NO=?";
			Object args1[]={bean.getQuoteNo()};
			this.mytemplate.update(delQry,args1);
			for(int i=0;i<bean.getLocation().size();i++){
				String sql="INSERT INTO OFS_LOCATION_DETAILS (QUOTE_NO, APPLICATION_NO,PRODUCT_ID, SCHEME_ID, CONTENT_TYPE_ID, LOCATION_ID, LOCATION_NAME, AGENCY_CODE, CUST_NAME, AMEND_ID, STATUS, REMARKS,INCEPTION_DATE,EXPIRY_DATE,ENTRY_DATE,CUSTOMER_ID) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,TO_DATE(?,'DD/MM/YYYY'),TO_DATE(?,'DD/MM/YYYY'),SYSDATE,?)";
				LogManager.info("Query=>"+sql);
				Object[] args = new Object[]{bean.getQuoteNo(),bean.getApplicationNo(),bean.getProductId(),"",bean.getContentTypeId(),(i+1),bean.getLocation().get(i),bean.getAgencyCode(),bean.getCustomerName()+" "+bean.getCustLastName(),"0",bean.getLocationStatus().get(i),"",bean.getInceptionDt(),bean.getExpiryDt(),bean.getCustomerId()};
				LogManager.info(" Arguments => "+StringUtils.join(args,","));
				removeNull(args);
				res=this.mytemplate.update(sql, args);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		LogManager.info("getInsertLocationDetails method() Exit");
		return res;
	}*/
	public int getInsertLocationDetails(HomeBean bean) {
		LogManager.push("getInsertLocationDetails method() Enter");
		int res=0;
		String sql="";
		Object[] args =null;
		try{
			
			if("save".equalsIgnoreCase(bean.getMode())){
				sql="INSERT INTO OFS_LOCATION_DETAILS (QUOTE_NO, APPLICATION_NO, PRODUCT_ID, SCHEME_ID, CONTENT_TYPE_ID, LOCATION_ID, LOCATION_NAME, AGENCY_CODE, CUST_NAME, AMEND_ID, STATUS, REMARKS,INCEPTION_DATE,EXPIRY_DATE,ENTRY_DATE,CUSTOMER_ID) VALUES (?,?,?,?,?,(SELECT NVL(MAX(LOCATION_ID)+1,1) FROM OFS_LOCATION_DETAILS WHERE QUOTE_NO=?),?,?,?,?,?,?,TO_DATE(?,'DD/MM/YYYY'),TO_DATE(?,'DD/MM/YYYY'),SYSDATE,?)";
				args = new Object[]{bean.getQuoteNo(),bean.getApplicationNo(),bean.getProductId(),"",bean.getContentTypeId(),bean.getQuoteNo(),bean.getLocationDesc(),bean.getAgencyCode(),bean.getCustomerName()+" "+bean.getCustLastName(),"0",bean.getLocStatus(),"",bean.getInceptionDt(),bean.getExpiryDt(),bean.getCustomerId()};
			}
			else if("update".equalsIgnoreCase(bean.getMode())){
				sql="UPDATE OFS_LOCATION_DETAILS SET LOCATION_NAME=?,STATUS=? WHERE QUOTE_NO=? AND LOCATION_ID=?";
				args = new Object[]{bean.getLocationDesc(),bean.getLocStatus(),bean.getQuoteNo(),bean.getEditLocId()};
			}
			LogManager.info("Query=>"+sql);
			LogManager.info(" Arguments => "+StringUtils.join(args,","));
			removeNull(args);
			res=this.mytemplate.update(sql, args);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		LogManager.info("getInsertLocationDetails method() Exit");
		return res;
	}

	public List<Map<String, Object>> setCustomerAndLocDetail(HomeBean bean) {

		List<Map<String, Object>> list= new ArrayList<Map<String,Object>>();
		List<Map<String, Object>> custList= new ArrayList<Map<String,Object>>();
		//List<String> location=new ArrayList<String>();
		//List<String> locationStatus=new ArrayList<String>();
		String customerId="";
		try {
			String sql="SELECT QUOTE_NO, APPLICATION_NO, PRODUCT_ID, SCHEME_ID, CONTENT_TYPE_ID, LOCATION_ID, LOCATION_NAME, AGENCY_CODE, CUST_NAME, TO_CHAR(INCEPTION_DATE,'DD/MM/YYYY') INCEPTION_DATE, TO_CHAR(EXPIRY_DATE,'DD/MM/YYYY') EXPIRY_DATE, ENTRY_DATE, AMEND_ID, STATUS, DECODE(STATUS,'Y','Active','N','DeActive') STATUS_DES, REMARKS, CUSTOMER_ID FROM OFS_LOCATION_DETAILS WHERE QUOTE_NO=? ORDER BY LOCATION_ID";
			Object args[]={bean.getQuoteNo()};
			LogManager.info("setCustomerAndLocDetail Query => "+sql);
			LogManager.info("setCustomerAndLocDetail Arguments => "+StringUtils.join(args,","));
			list=this.mytemplate.queryForList(sql, args);
			
			if(list!=null && list.size()>0){
				bean.setLocationSize(String.valueOf(list.size()));
				String incepDt=list.get(0).get("INCEPTION_DATE")==null?"":list.get(0).get("INCEPTION_DATE").toString();
				String expDt=list.get(0).get("EXPIRY_DATE")==null?"":list.get(0).get("EXPIRY_DATE").toString();
				customerId=list.get(0).get("CUSTOMER_ID")==null?"":list.get(0).get("CUSTOMER_ID").toString();
				bean.setCustomerId(customerId);
				/*for(int i=0;i<list.size();i++){
					location.add(list.get(i).get("LOCATION_NAME")==null?"":list.get(i).get("LOCATION_NAME").toString());
					locationStatus.add(list.get(i).get("STATUS")==null?"":list.get(i).get("STATUS").toString());
				}*/
				
				//bean.setLocation(location);
				//bean.setLocationStatus(locationStatus);
				bean.setInceptionDt(incepDt);
				bean.setExpiryDt(expDt);
			}
			String query="SELECT TITLE, FIRST_NAME, LAST_NAME, MOBILE, EMAIL, CUSTOMER_TYPE FROM PERSONAL_INFO WHERE CUSTOMER_ID=?";
			Object args1[]={customerId};
			custList=this.mytemplate.queryForList(query, args1);
			if(custList!=null && custList.size()>0){
				bean.setTitle(custList.get(0).get("TITLE")==null?"":custList.get(0).get("TITLE").toString());
				bean.setCustomerName(custList.get(0).get("FIRST_NAME")==null?"":custList.get(0).get("FIRST_NAME").toString());
				bean.setCustLastName(custList.get(0).get("LAST_NAME")==null?"":custList.get(0).get("LAST_NAME").toString());
				bean.setEmail(custList.get(0).get("EMAIL")==null?"":custList.get(0).get("EMAIL").toString());
				bean.setMobileNo(custList.get(0).get("MOBILE")==null?"":custList.get(0).get("MOBILE").toString());
				bean.setCustomerType(custList.get(0).get("CUSTOMER_TYPE")==null?"":custList.get(0).get("CUSTOMER_TYPE").toString());
			}
			if(StringUtils.isNotBlank(bean.getIssuer()))
			{
				List<Map<String, Object>> brokerDtl= new ArrayList<Map<String,Object>>();
				String qry="SELECT BROKER_CODE,AC_EXECUTIVE_ID FROM HOME_POSITION_MASTER WHERE QUOTE_NO=?";
				Object obj[]={bean.getQuoteNo()};
				brokerDtl=this.mytemplate.queryForList(qry, obj);
				if(brokerDtl!=null && brokerDtl.size()>0){
					bean.setBrokerCode(brokerDtl.get(0).get("BROKER_CODE")==null?"":brokerDtl.get(0).get("BROKER_CODE").toString());
					bean.setExecutive(brokerDtl.get(0).get("AC_EXECUTIVE_ID")==null?"":brokerDtl.get(0).get("AC_EXECUTIVE_ID").toString());
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public int updateLocationSchemeEmpty(HomeBean bean) {
		LogManager.push("updateLocationScheme method() Enter");
		int res=0;
		try{
			String sql="UPDATE OFS_LOCATION_DETAILS SET SCHEME_ID=? WHERE QUOTE_NO=? AND PRODUCT_ID=?";
			LogManager.info("updateLocationScheme Query=>"+sql);
			Object[] args = new Object[]{"",bean.getQuoteNo(),bean.getProductId()};
			LogManager.info("updateLocationScheme Arguments => "+StringUtils.join(args,","));
			removeNull(args);
			res=this.mytemplate.update(sql, args);
		}catch(Exception e){
			e.printStackTrace();
		}
		LogManager.info("updateLocationScheme method() Exit");
		return res;
	}

	public int updateLocationScheme(HomeBean bean, String location,List<String> sList) {
		LogManager.push("updateLocationScheme method() Enter");
		int res=0;
		try{
			String schemes="";
			for(int i=0;i<sList.size();i++){
				schemes=schemes+","+sList.get(i);
			}
			if(StringUtils.isNotBlank(schemes)) {
				schemes=schemes.substring(1);
				
				String sql="UPDATE OFS_LOCATION_DETAILS SET SCHEME_ID=? WHERE QUOTE_NO=? AND PRODUCT_ID=? AND LOCATION_ID=?";
				LogManager.info("updateLocationScheme Query=>"+sql);
				Object[] args = new Object[]{schemes,bean.getQuoteNo(),bean.getProductId(),location};
				LogManager.info("updateLocationScheme Arguments => "+StringUtils.join(args,","));
				removeNull(args);
				res=this.mytemplate.update(sql, args);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		LogManager.info("updateLocationScheme method() Exit");
		return res;
	}

	public List<String> getLocationDtls(HomeBean bean) {
		List<Map<String,Object>> res=new ArrayList<Map<String,Object>>();
		List<String> result=new ArrayList<String>();
		try {
			String sql="SELECT LOCATION_NAME FROM OFS_LOCATION_DETAILS WHERE QUOTE_NO=? ORDER BY LOCATION_ID";
			Object[] args = new Object[]{bean.getQuoteNo()};
			res=this.mytemplate.queryForList(sql,args);
			for(int i=0;i<res.size();i++){
				result.add(res.get(i).get("LOCATION_NAME")==null?"":res.get(i).get("LOCATION_NAME").toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	/*public String getLocationSizeDetails(HomeBean bean) {
		List<Map<String,Object>> res=new ArrayList<Map<String,Object>>();
		String size="0";
		try {
			String sql="SELECT LOCATION_ID,LOCATION_NAME FROM OFS_LOCATION_DETAILS WHERE QUOTE_NO=? AND SCHEME_ID NOT IN ('99999') ORDER BY LOCATION_ID";
			Object[] args = new Object[]{bean.getQuoteNo()};
			res=this.mytemplate.queryForList(sql,args);
			if(res!=null && res.size()>0)
				size=String.valueOf(res.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return size;
	}*/
	
	public String getLocationSizeDetails(HomeBean bean, String type) {
		List<Map<String,Object>> res=new ArrayList<Map<String,Object>>();
		String result="0";
		int size=0;
		try {
			if("scheme".equalsIgnoreCase(type)){
				String sql=getQuery("GET_SCHEME_LOCATION_SIZE");
				//Object[] args = new Object[]{bean.getQuoteNo(),bean.getSchemeId()};
				Object[] args = new Object[]{bean.getQuoteNo(),bean.getSchemeId(),bean.getQuoteNo(), bean.getQuoteNo()};
				res=this.mytemplate.queryForList(sql,args);
				/*if(res!=null && res.size()>0){
					for(int i=0;i<res.size();i++){
						String schemeId=res.get(i).get("SCHEME_ID")==null?"":res.get(i).get("SCHEME_ID").toString();
						if(schemeId.equalsIgnoreCase(bean.getSchemeId())){
							size+=1;
							bean.setLocationId(res.get(i).get("LOCATION_ID")==null?"1":res.get(i).get("LOCATION_ID").toString());
						}
					}
				}*/
				if(res!=null && res.size()>0)
					size=res.size();
			}else{
				String sql="SELECT LOCATION_ID,LOCATION_NAME FROM OFS_LOCATION_DETAILS WHERE QUOTE_NO=? AND NVL(SCHEME_ID,'0') NOT IN ('99999') ORDER BY LOCATION_ID";
				Object[] args = new Object[]{bean.getQuoteNo()};
				res=this.mytemplate.queryForList(sql,args);
				if(res!=null && res.size()>0)
					size=res.size();
			}
			result=String.valueOf(size);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	public void setSumInsuredDetails(HomeBean bean) {
		List<Map<String,Object>> res=new ArrayList<Map<String,Object>>();
		List<Map<String,Object>> res1=new ArrayList<Map<String,Object>>();
		List<String> locationName=new ArrayList<String>();
		String locationId="";
		String selLocation="";
		int loc=0;
		String[][] sumInsured=new  String[30][30];
		String[][] premium=new  String[30][30];
		try{
			String contentTypeId=StringUtils.isBlank(bean.getContentTypeId())?"0":bean.getContentTypeId();
			/*String sql="SELECT DISTINCT LOCATION_ID FROM OFS_DATA_DETAILS WHERE QUOTE_NO=?";
			Object[] args1 = new Object[]{bean.getQuoteNo()};
			res=this.mytemplate.queryForList(sql,args1);*/
			
			String sql1=getQuery("GET_SCHEME_LOCATION_SIZE");
			//Object[] args2 = new Object[]{bean.getQuoteNo(),bean.getSchemeId()};
			Object[] args2 = new Object[]{bean.getQuoteNo(),bean.getSchemeId(),bean.getQuoteNo(),bean.getQuoteNo()};
			res1=this.mytemplate.queryForList(sql1,args2);
			if(res1!=null && res1.size()>0){
				for(int k=0;k<res1.size();k++){
					//String schemeId=res1.get(k).get("SCHEME_ID")==null?"":res1.get(k).get("SCHEME_ID").toString();
					//if(schemeId.equalsIgnoreCase(bean.getSchemeId())){
						selLocation=res1.get(k).get("LOCATION_ID")==null?"":res1.get(k).get("LOCATION_ID").toString();
						locationName.add(res1.get(k).get("LOCATION_NAME")==null?"":res1.get(k).get("LOCATION_NAME").toString());
						//for(int i=0;i<res.size();i++){
							//locationId=res.get(i).get("LOCATION_ID")==null?"":res.get(i).get("LOCATION_ID").toString();
						locationId=selLocation;
						loc=Integer.parseInt(locationId)-1;
						
						String query= getQuery("GET_HOME_COVERAGES_LIST_EDIT");
						Object [] args = new Object[12];
						args[0] = contentTypeId;
						args[1] = bean.getQuoteNo();
						args[2] = locationId;
						args[3] = contentTypeId;
						args[4] = bean.getProductId();
						args[5] = bean.getSchemeId();
						args[6] = bean.getProductId();
						args[7] = bean.getSchemeId();
						args[8] = contentTypeId;
						args[9] = bean.getProductId();
						args[10] = bean.getSchemeId();
						args[11] = contentTypeId;
						
						removeNull(args);
						LogManager.info("Query==> " + query);
						LogManager.info("Args==> " + StringUtils.join(args, ", "));
						List<Map<String,Object>> resultList = this.mytemplate.queryForList(query, args);
						
						for(int j=0;j<resultList.size();j++){
							sumInsured[k][j]=resultList.get(j).get("SUM_INSURED")==null?"":resultList.get(j).get("SUM_INSURED").toString();
							premium[k][j]=resultList.get(j).get("PREMIUM_AMOUNT")==null?"":resultList.get(j).get("PREMIUM_AMOUNT").toString();
						}
					//}
				}
				bean.setSumInsuredNM(sumInsured);
				bean.setPremiumNM(premium);
				bean.setLocationName(locationName);
			}
			
			/*for(int i=0;i<res.size();i++){
				locationId=res.get(i).get("LOCATION_ID")==null?"":res.get(i).get("LOCATION_ID").toString();
				
				String query= getQuery("GET_HOME_COVERAGES_LIST_EDIT");
				Object [] args = new Object[12];
				args[0] = contentTypeId;
				args[1] = bean.getQuoteNo();
				args[2] = locationId;
				args[3] = contentTypeId;
				args[4] = bean.getProductId();
				args[5] = bean.getSchemeId();
				args[6] = bean.getProductId();
				args[7] = bean.getSchemeId();
				args[8] = contentTypeId;
				args[9] = bean.getProductId();
				args[10] = bean.getSchemeId();
				args[11] = contentTypeId;
				
				removeNull(args);
				LogManager.info("Query==> " + query);
				LogManager.info("Args==> " + StringUtils.join(args, ", "));
				List<Map<String,Object>> resultList = this.mytemplate.queryForList(query, args);
				
				for(int j=0;j<resultList.size();j++){
					sumInsured[i][j]=resultList.get(j).get("SUM_INSURED")==null?"":resultList.get(j).get("SUM_INSURED").toString();
				}
				
			}
			bean.setSumInsuredNM(sumInsured);*/
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setQuoteDetails(HomeBean bean) {
		List<Map<String,Object>> res=new ArrayList<Map<String,Object>>();
		String locationIds="";
		try {
			String sql="SELECT LISTAGG(SCHEME_ID,',') WITHIN GROUP(ORDER BY SCHEME_ID) AS SCHEME_ID FROM ( SELECT DISTINCT REGEXP_SUBSTR(SCHEME_ID,'[^,]+',1,LEVEL) SCHEME_ID FROM OFS_LOCATION_DETAILS WHERE QUOTE_NO=? CONNECT BY LEVEL <= REGEXP_COUNT(SCHEME_ID,',')+1)";
			Object[] args = new Object[]{bean.getQuoteNo()};
			res=this.mytemplate.queryForList(sql,args);
			bean.setSchemeSelected(res.get(0).get("SCHEME_ID")==null?"":res.get(0).get("SCHEME_ID").toString());
			
			String sql1=getQuery("GET_LOCATION_IDS");
			Object[] args1 = new Object[]{bean.getQuoteNo()};
			res=this.mytemplate.queryForList(sql1,args1);
			
			for(int i=0;i<res.size();i++){
				String loc=",".concat(res.get(i).get("LOCATION_IDS")==null?"":res.get(i).get("LOCATION_IDS").toString());
				locationIds=locationIds+loc;
				
			}
			if(StringUtils.isNotBlank(locationIds) && ",".equalsIgnoreCase(locationIds.substring(0,1))) {
				locationIds=locationIds.substring(1);
			}
			
			bean.setLocationIds(locationIds);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public int deleteExist(HomeBean bean){
		int amend_id=0;
		int res=0;
		try {
			String sql="select max(amend_id) from OFS_DATA_DETAILS where quote_no=? and product_id=? and scheme_id=?";
			Object[] obj=new Object[3];
			obj[0]=bean.getQuoteNo();
			obj[1]=bean.getProductId();
			obj[2]=bean.getSchemeId();
			LogManager.info("HomeDao.deleteExist() Query: "+queryFrammer(sql, obj));
			int count =this.mytemplate.queryForInt(sql, obj);
			LogManager.info("HomeDao.deleteExist() Args: "+StringUtils.join(obj,",")+" AmendId: "+queryFrammer(sql, obj));
			amend_id=count;
			String sql1=getQuery("DELETE_HOME_EXIST");
			Object[] obj1=new Object[4];
			obj1[0]=bean.getProductId();
			obj1[1]=bean.getSchemeId();
			obj1[2]=bean.getQuoteNo();
			obj1[3]=amend_id;
			LogManager.info("HomeDao.deleteExist() Query: "+queryFrammer(sql, obj));
			res=this.mytemplate.update(sql1,obj1);
			LogManager.info("HomeDao.deleteExist() Args: "+StringUtils.join(obj,",")+" Deleted: "+res);
		} catch (Exception e) {
			LogManager.info("Exception @ HomeDao.deleteExist() Error: "+e);
			e.printStackTrace();
		}
		return res;
	}

	public List<Map<String, Object>> getSelectedSchemeLocation(HomeBean bean) {
		List<Map<String, Object>> location=new ArrayList<Map<String, Object>>();
		
		try {
			String sql=getQuery("GET_SCHEME_LOCATION_SIZE");
			Object[] args = new Object[]{bean.getQuoteNo(),bean.getSchemeId(),bean.getQuoteNo(),bean.getQuoteNo()};
			LogManager.info("HomeDao.getSelectedSchemeLocation() Query: "+queryFrammer(sql, args));
			location=this.mytemplate.queryForList(sql,args);
		} catch (Exception e) {
			LogManager.info("Exception @ HomeDao.getSelectedSchemeLocation() Error: "+e);
			e.printStackTrace();
		}
		return location;
	}

	public void setLocationDetails(HomeBean bean) {
		List<Map<String, Object>> list=new ArrayList<Map<String, Object>>();
		
		try {
			String sql="SELECT QUOTE_NO, APPLICATION_NO, PRODUCT_ID, SCHEME_ID, CONTENT_TYPE_ID, LOCATION_ID, LOCATION_NAME, AGENCY_CODE, CUST_NAME, TO_CHAR(INCEPTION_DATE, 'DD/MM/YYYY') INCEPTION_DATE, TO_CHAR (EXPIRY_DATE, 'DD/MM/YYYY') EXPIRY_DATE, ENTRY_DATE, AMEND_ID, STATUS, DECODE(STATUS,'Y','ACTIVE','N','DeActive') STATUS_DES, REMARKS, CUSTOMER_ID FROM OFS_LOCATION_DETAILS WHERE QUOTE_NO = ? AND LOCATION_ID=? ORDER BY LOCATION_ID";
			//Object[] args = new Object[]{bean.getQuoteNo(),bean.getSchemeId()};
			Object[] args = new Object[]{bean.getQuoteNo(),bean.getEditLocId()};
			list=this.mytemplate.queryForList(sql,args);
			if(list!=null && list.size()>0){
				bean.setLocationDesc(list.get(0).get("LOCATION_NAME")==null?"":list.get(0).get("LOCATION_NAME").toString());
				bean.setLocStatus(list.get(0).get("STATUS")==null?"":list.get(0).get("STATUS").toString());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public int getDeleteLocationDetails(HomeBean bean) {
		int res=0;
		try {
			String sql="DELETE FROM OFS_LOCATION_DETAILS WHERE QUOTE_NO=? AND LOCATION_ID=?";
			Object[] args = new Object[]{bean.getQuoteNo(),bean.getEditLocId()};
			res=this.mytemplate.update(sql,args);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public int getUpdateLocationDetails(HomeBean bean) {
		LogManager.push("getUpdateLocationDetails method() Enter");
		int res=0;
		String sql="";
		Object[] args =null;
		try{
			sql="UPDATE OFS_LOCATION_DETAILS SET CUST_NAME=?,INCEPTION_DATE=TO_DATE(?,'DD/MM/YYYY'),EXPIRY_DATE=TO_DATE(?,'DD/MM/YYYY') WHERE QUOTE_NO=?";
			args = new Object[]{bean.getCustomerName()+" "+bean.getCustLastName(),bean.getInceptionDt(),bean.getExpiryDt(),bean.getQuoteNo()};
			LogManager.info("Query=>"+sql);
			LogManager.info(" Arguments => "+StringUtils.join(args,","));
			removeNull(args);
			res=this.mytemplate.update(sql, args);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		LogManager.info("getUpdateLocationDetails method() Exit");
		return res;
	}

	public List<Map<String, Object>> getSchemeLocationList(HomeBean bean) {
		List<Map<String, Object>> list=new ArrayList<Map<String, Object>>();
		try {
			String sql="SELECT OLD.LOCATION_ID CODE, INITCAP(OLD.LOCATION_NAME) CODEDESC FROM OFS_LOCATION_DETAILS OLD WHERE OLD.QUOTE_NO = ? AND OLD.LOCATION_ID IN(SELECT DISTINCT LOCATION_ID FROM OFS_DATA_DETAILS ODD WHERE ODD.QUOTE_NO = OLD.QUOTE_NO AND SCHEME_ID = ?) ORDER BY LOCATION_ID ASC";
			Object[] args = new Object[]{bean.getQuoteNo(),bean.getDropDownScheme()==null?"":bean.getDropDownScheme()};
			list=this.mytemplate.queryForList(sql,args);
			if(list!=null && list.size()>0){
				bean.setDropDownLocation(list.get(0).get("CODE")==null?"":list.get(0).get("CODE").toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public List<Map<String, Object>> getDropDownSchemeList(HomeBean bean) {
		List<Map<String, Object>> list=new ArrayList<Map<String, Object>>();
		try {
			String sql="SELECT SCHEME_ID CODE, INITCAP(SCHEME_NAME) CODEDESC FROM OFS_SCHEME_MASTER WHERE SCHEME_ID IN(SELECT DISTINCT SCHEME_ID FROM OFS_DATA_DETAILS WHERE QUOTE_NO = ?) ORDER BY SCHEME_ID";
			Object[] args = new Object[]{bean.getQuoteNo()};
			list=this.mytemplate.queryForList(sql,args);
			if(list!=null && list.size()>0){
				bean.setDropDownScheme(list.get(0).get("CODE")==null?"":list.get(0).get("CODE").toString());
				bean.setDropDownSchemeName(list.get(0).get("CODEDESC")==null?"":list.get(0).get("CODEDESC").toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public List<Map<String,Object>> getHomePremiumInfoNew(HomeBean bean) {
		List<Map<String, Object>> resultList = new ArrayList<Map<String,Object>>();
		try {
			String condition = " where PREMIUM_AMOUNT!=0 AND LOCATION_ID='"+bean.getDropDownLocation()+"' ORDER BY COVERAGES_ID,LOCATION_ID ASC";
			resultList = getCoveragesListNew(bean,"coverage",condition);
		} catch(Exception e){
			LogManager.debug(e);
		}
		return resultList;
	}
	
	public List<Map<String,Object>> getCoveragesListNew(HomeBean bean, String type, String condition) throws Exception {
		String query="";
		List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
		List<Map<String,Object>> resultList=new ArrayList<Map<String,Object>>();
		//List<Map<String,Object>>[] resultListNew=new ArrayList[bean.getSchemeList().size()];
		//for(int i=0;i<bean.getSchemeList().size();i++){
			query = getQuery("GET_HOME_COVERAGES_LIST") + condition;
			Object[] args = new Object[6];
			args[0] = bean.getQuoteNo();
			args[1] = bean.getProductId();
			args[2] = StringUtils.isBlank(bean.getContentTypeId())?"0":bean.getContentTypeId();
			//args[3] = bean.getSchemeList().get(i);
			args[3] = StringUtils.isBlank(bean.getDropDownScheme())?bean.getSchemeId():bean.getDropDownScheme();
			args[4] = type;
			args[5] = bean.getAgencyCode();
			removeNull(args);
			LogManager.info("Query==> " + query);
			LogManager.info("Args==> " + StringUtils.join(args, ", "));
			list= this.mytemplate.queryForList(query, args);
			resultList.addAll(list);
			//resultListNew[i]=list;
		//}
		//bean.setPremiumListNew(resultListNew);
		//query="SELECT SCHEME_ID,SCHEME_NAME FROM OFS_SCHEME_MASTER WHERE SCHEME_ID IN (SELECT DISTINCT SCHEME_ID FROM OFS_DATA_DETAILS WHERE QUOTE_NO=?) ORDER BY SCHEME_ID";
			//query="SELECT SCHEME_ID,SCHEME_NAME FROM OFS_SCHEME_MASTER WHERE SCHEME_ID IN (?) ORDER BY SCHEME_ID";
			query="SELECT OSM.SCHEME_ID,SCHEME_NAME, CASE WHEN OAE.SCHEME_ID IS NOT NULL THEN 'Y' ELSE 'N' END EXTENDED_YN FROM OFS_SCHEME_MASTER OSM INNER JOIN(SELECT DISTINCT SCHEME_ID FROM OFS_DATA_DETAILS WHERE QUOTE_NO=? AND SCHEME_ID IN (?)) ODD ON OSM.SCHEME_ID=ODD.SCHEME_ID LEFT OUTER JOIN (SELECT DISTINCT SCHEME_ID FROM OFS_ADDON_EXTENSION_MASTER WHERE PRODUCT_ID=? AND STATUS IN ('Y','R') ) OAE ON OSM.SCHEME_ID = OAE.SCHEME_ID ORDER BY OSM.SCHEME_ID ";
		
		LogManager.info("Query==> " + query);
		LogManager.info("Args==> " + StringUtils.join(StringUtils.isBlank(bean.getDropDownScheme())?bean.getSchemeId():bean.getDropDownScheme(), ", "));
		bean.setSchemeListNew(this.mytemplate.queryForList(query, new Object[]{bean.getQuoteNo(),StringUtils.isBlank(bean.getDropDownScheme())?bean.getSchemeId():bean.getDropDownScheme(),bean.getProductId()}));
		return resultList;
	}

	public List<Map<String, Object>> getSchemeName(HomeBean bean) {
		List<Map<String, Object>> schemeName=new ArrayList<Map<String,Object>>();
		try {
			String query = "SELECT SCHEME_ID,SCHEME_NAME FROM OFS_SCHEME_MASTER WHERE SCHEME_ID =?";
			Object[] args = new Object[]{bean.getSchemeId()};
			LogManager.info("Query==> " + query);
			LogManager.info("Args==> " + StringUtils.join(args, ", "));
			schemeName=this.mytemplate.queryForList(query, args);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return schemeName;
	}

	public List<Map<String, Object>> getLocationNames(HomeBean bean) {
		List<Map<String, Object>> locationName=new ArrayList<Map<String,Object>>();
		try {
			String query = getQuery("GET_SCHEME_LOCATION_SIZE");
			Object[] args = new Object[]{bean.getQuoteNo(),bean.getSchemeId(),bean.getQuoteNo(),bean.getQuoteNo()};
			LogManager.info("Query==> " + query);
			LogManager.info("Args==> " + StringUtils.join(args, ", "));
			locationName=this.mytemplate.queryForList(query, args);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return locationName;
	}

	public void setlocationPremiumValues(HomeBean bean) {
		List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
		try {
			bean.setAjActualPremium("0");
			bean.setAjActualOptionalPremium("0");
			bean.setAjTtotalPremium("0");
			
			String schLocTotalPremQuery = "SELECT MINIMUM_PREMIUM_YN, BASE_PREMIUM, OPTIONAL_PREMIUM, PREMIUM_APPLIED, REMARKS,"
					+ " VOLUME_DISCOUNT_PERCENT, CORPORATE_DISCOUNT_PERCENT, SPECIAL_DISCOUNT_PERCENT, VOLUME_DISCOUNT_AMOUNT,"
					+ " CORPORATE_DISCOUNT_AMOUNT, SPECIAL_DISCOUNT_AMOUNT"
					+ " FROM OFS_SCHEME_DETAILS WHERE QUOTE_NO = ? AND PRODUCT_ID = ? AND SCHEME_ID = ?"
					+ " AND LOCATION_ID = ? AND STATUS = 'Y'";
			Object[] schLocTotalPremArgs = new Object[]{bean.getQuoteNo(), bean.getProductId(),
					StringUtils.isBlank(bean.getDropDownScheme())?bean.getSchemeId():bean.getDropDownScheme(),
							StringUtils.isBlank(bean.getDropDownLocation())?bean.getLocationId():bean.getDropDownLocation()};
			LogManager.info("Home.DAO.setlocationPremiumValues Query: "+queryFrammer(schLocTotalPremQuery, schLocTotalPremArgs));
			List<Map<String, Object>> schLocPremList = this.mytemplate.queryForList(schLocTotalPremQuery, schLocTotalPremArgs);
			if(schLocPremList!= null && schLocPremList.size()>0){
				Map<String, Object> schLocPremMap = schLocPremList.get(0);
				if(schLocPremMap!= null && schLocPremMap.size()>0){
					bean.setAjMinimumPremiumYn(schLocPremMap.get("MINIMUM_PREMIUM_YN")==null?"N":schLocPremMap.get("MINIMUM_PREMIUM_YN").toString());
					bean.setAjActualPremium(schLocPremMap.get("BASE_PREMIUM")==null?"0":schLocPremMap.get("BASE_PREMIUM").toString());
					bean.setAjActualOptionalPremium(schLocPremMap.get("OPTIONAL_PREMIUM")==null?"0":schLocPremMap.get("OPTIONAL_PREMIUM").toString());
					bean.setAjVolDiscountPercent(schLocPremMap.get("VOLUME_DISCOUNT_PERCENT")==null?0:Double.valueOf(schLocPremMap.get("VOLUME_DISCOUNT_PERCENT").toString()));
					bean.setAjVolDiscountAmount(schLocPremMap.get("VOLUME_DISCOUNT_AMOUNT")==null?0:Double.valueOf(schLocPremMap.get("VOLUME_DISCOUNT_AMOUNT").toString()));
					bean.setAjCorpDiscountPercent(schLocPremMap.get("CORPORATE_DISCOUNT_PERCENT")==null?0:Double.valueOf(schLocPremMap.get("CORPORATE_DISCOUNT_PERCENT").toString()));
					bean.setAjCorpDiscountAmount(schLocPremMap.get("CORPORATE_DISCOUNT_AMOUNT")==null?0:Double.valueOf(schLocPremMap.get("CORPORATE_DISCOUNT_AMOUNT").toString()));
					bean.setAjSplDiscountPercent(schLocPremMap.get("SPECIAL_DISCOUNT_PERCENT")==null?0:Double.valueOf(schLocPremMap.get("SPECIAL_DISCOUNT_PERCENT").toString()));
					bean.setAjSplDiscountAmount(schLocPremMap.get("SPECIAL_DISCOUNT_AMOUNT")==null?0:Double.valueOf(schLocPremMap.get("SPECIAL_DISCOUNT_AMOUNT").toString()));
					bean.setAjTtotalPremium(schLocPremMap.get("PREMIUM_APPLIED")==null?"0":schLocPremMap.get("PREMIUM_APPLIED").toString());
					bean.setAjRemarks(schLocPremMap.get("REMARKS")==null?"":schLocPremMap.get("REMARKS").toString());
				}
			}
		}catch(Exception e){
			LogManager.info("Exception @ HomeDAO.setlocationPremiumValues() Error: "+e);
			e.printStackTrace();
		}
	}

	
	
	
	
	public List<Map<String, Object>> locationDtlsList(HomeBean bean) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		try{
			if(StringUtils.isNotBlank(bean.getApplicationNo())){
				String query ="SELECT APPLICATION_NO, PRODUCT_ID, SCHEME_ID, CONTENT_TYPE_ID,"
						+ " LOCATION_ID, LOCATION_NAME, AGENCY_CODE, CUST_NAME,"
						+ " TO_CHAR(INCEPTION_DATE,'DD/MM/YYYY') INCEPTION_DATE,"
						+ " TO_CHAR(EXPIRY_DATE,'DD/MM/YYYY') EXPIRY_DATE, ENTRY_DATE, AMEND_ID, STATUS,"
						+ " DECODE(STATUS,'Y','Active','N','DeActive') STATUS_DES, REMARKS, CUSTOMER_ID"
						+ " FROM OFS_LOCATION_DETAILS A WHERE APPLICATION_NO = ?"
						+ " AND AMEND_ID = (SELECT MAX(AMEND_ID) FROM OFS_LOCATION_DETAILS B"
						+ " WHERE A.APPLICATION_NO = B.APPLICATION_NO AND A.LOCATION_ID = B.LOCATION_ID)"
						+ " ORDER BY LOCATION_ID";
				Object[] args = new Object[]{bean.getApplicationNo()};
				LogManager.info("HomeDAO.locationDtlsList() Query(): "+queryFrammer(query, args));
				list = this.mytemplate.queryForList(query, args);
			}
		}catch(Exception e){
			LogManager.info("Exception @ HomeDAO.locationDtlsList(): "+e);
			e.printStackTrace();
		}
		return list;
	}

	public boolean editLocationDetails(HomeBean bean) {
		try{
			if(StringUtils.isNotBlank(bean.getApplicationNo()) && StringUtils.isNotBlank(bean.getEditLocId())){
				String query = "SELECT APPLICATION_NO,LOCATION_ID,LOCATION_NAME,STATUS"
						+ " FROM OFS_LOCATION_DETAILS A WHERE APPLICATION_NO = ? AND LOCATION_ID = ?"
						+ " AND AMEND_ID = (SELECT MAX(AMEND_ID) FROM OFS_LOCATION_DETAILS B"
						+ " WHERE A.APPLICATION_NO = B.APPLICATION_NO AND A.LOCATION_ID = B.LOCATION_ID)";
				Object[] args = new Object[]{bean.getApplicationNo(), bean.getEditLocId()};
				LogManager.info("HomeDAO.editLocationDetails() Query(): "+queryFrammer(query, args));
				Map<String, Object> map = this.mytemplate.queryForMap(query, args);
				if(map!=null && map.size()>0){
					bean.setApplicationNo(map.get("APPLICATION_NO")==null?"":map.get("APPLICATION_NO").toString());
					bean.setEditLocId(map.get("LOCATION_ID")==null?"":map.get("LOCATION_ID").toString());
					bean.setLocationDesc(map.get("LOCATION_NAME")==null?"":map.get("LOCATION_NAME").toString());
					bean.setLocStatus(map.get("STATUS")==null?"":map.get("STATUS").toString());
					return true;
				}
			}
		}catch(Exception e){
			LogManager.info("Exception @ HomeDAO.editLocationDetails(): "+e);
			e.printStackTrace();
		}
		return false;
	}

	public boolean manipulateLocationDetails(HomeBean bean) {
		try{
			if(StringUtils.isNotBlank(bean.getApplicationNo())){
				String query = "";
				Object[] args = null;
				if("add".equalsIgnoreCase(bean.getMode())){
					query = "INSERT INTO OFS_LOCATION_DETAILS(APPLICATION_NO,PRODUCT_ID,"
							+ " CONTENT_TYPE_ID,LOCATION_ID,LOCATION_NAME,AGENCY_CODE,AMEND_ID,STATUS,ENTRY_DATE)"
							+ " VALUES (?,?,?,(SELECT NVL(MAX(LOCATION_ID),0)+1 FROM OFS_LOCATION_DETAILS A"
							+ " WHERE APPLICATION_NO = ? AND AMEND_ID = (SELECT MAX(AMEND_ID) FROM OFS_LOCATION_DETAILS B"
							+ " WHERE A.APPLICATION_NO = B.APPLICATION_NO AND A.LOCATION_ID = B.LOCATION_ID)),?,?,?,?,SYSDATE)";
					args = new Object[]{bean.getApplicationNo(), bean.getProductId(), "0",
							bean.getApplicationNo(), bean.getLocationDesc(),
							bean.getAgencyCode(), "0", bean.getLocStatus()};
				}else if("edit".equalsIgnoreCase(bean.getMode())){
					query = "UPDATE OFS_LOCATION_DETAILS SET LOCATION_NAME = ?, STATUS = ?"
							+ " WHERE APPLICATION_NO = ? AND LOCATION_ID = ?";
					args = new Object[]{bean.getLocationDesc(), bean.getLocStatus(),
							bean.getApplicationNo(), bean.getEditLocId()};
				}else if("delete".equalsIgnoreCase(bean.getMode())){
					query = "DELETE FROM OFS_LOCATION_DETAILS"
							+ " WHERE APPLICATION_NO = ? AND LOCATION_ID = ?";
					args = new Object[]{bean.getApplicationNo(), bean.getEditLocId()};
				}
				LogManager.info("HomeDAO.manipulateLocationDetails() "+bean.getMode()+" Query(): "+queryFrammer(query, args));
				int count = this.mytemplate.update(query, args);
				if(count>0){
					return true;
				}
			}
		}catch(Exception e){
			LogManager.info("Exception @ HomeDAO.manipulateLocationDetails(): "+e);
			e.printStackTrace();
		}
		return false;
	}

	public void editQuoteDetails(HomeBean bean) {
		try{
			if(StringUtils.isNotBlank(bean.getQuoteNo())){
				String query="SELECT QUOTE_NO, APPLICATION_NO, CUSTOMER_ID, TO_CHAR(INCEPTION_DATE,'DD/MM/YYYY') INCEPTION_DATE,"
						+ " TO_CHAR(EXPIRY_DATE,'DD/MM/YYYY') EXPIRY_DATE, BROKER_CODE, AC_EXECUTIVE_ID FROM HOME_POSITION_MASTER A WHERE QUOTE_NO = ? "
						+ " AND AMEND_ID = (SELECT MAX(AMEND_ID) FROM HOME_POSITION_MASTER B WHERE A.QUOTE_NO = B.QUOTE_NO)";
				Object args[]={bean.getQuoteNo()};
				LogManager.info("HomeDAO.editQuoteDetails() Query: "+queryFrammer(query, args));
				Map<String, Object> hpMap = this.mytemplate.queryForMap(query, args);
				if(hpMap!=null && hpMap.size()>0){
					String policyNo = hpMap.get("POLICY_NO")==null?"":hpMap.get("POLICY_NO").toString();
					if(StringUtils.isNotBlank(policyNo)){
						bean.setPolicyNo(policyNo);
					}
					bean.setQuoteNo(hpMap.get("QUOTE_NO")==null?"":hpMap.get("QUOTE_NO").toString());
					bean.setApplicationNo(hpMap.get("APPLICATION_NO")==null?"":hpMap.get("APPLICATION_NO").toString());
					bean.setCustomerId(hpMap.get("CUSTOMER_ID")==null?"":hpMap.get("CUSTOMER_ID").toString());
					bean.setInceptionDt(hpMap.get("INCEPTION_DATE")==null?"":hpMap.get("INCEPTION_DATE").toString());
					bean.setExpiryDt(hpMap.get("EXPIRY_DATE")==null?"":hpMap.get("EXPIRY_DATE").toString());
					bean.setBrokerCode(hpMap.get("BROKER_CODE")==null?"":hpMap.get("BROKER_CODE").toString());
					bean.setExecutive(hpMap.get("AC_EXECUTIVE_ID")==null?"":hpMap.get("AC_EXECUTIVE_ID").toString());
					
					query = "SELECT TITLE, FIRST_NAME, LAST_NAME, MOBILE, EMAIL, CUSTOMER_TYPE"
							+ " FROM PERSONAL_INFO A WHERE CUSTOMER_ID=?"
							+ " AND AMEND_ID = (SELECT MAX(AMEND_ID) FROM PERSONAL_INFO B WHERE A.CUSTOMER_ID = B.CUSTOMER_ID)";
					args = new Object[]{bean.getCustomerId()};
					LogManager.info("HomeDAO.editQuoteDetails() Query: "+queryFrammer(query, args));
					Map<String, Object> piMap = this.mytemplate.queryForMap(query, args);
					if(piMap!=null && piMap.size()>0){
						bean.setTitle(piMap.get("TITLE")==null?"":piMap.get("TITLE").toString());
						bean.setCustomerName(piMap.get("FIRST_NAME")==null?"":piMap.get("FIRST_NAME").toString());
						bean.setCustLastName(piMap.get("LAST_NAME")==null?"":piMap.get("LAST_NAME").toString());
						bean.setCustomerFullName(bean.getTitle()+"."+bean.getCustomerName()+" "+bean.getCustLastName());
						bean.setMobileNo(piMap.get("MOBILE")==null?"":piMap.get("MOBILE").toString());
						bean.setEmail(piMap.get("EMAIL")==null?"":piMap.get("EMAIL").toString());
						bean.setCustomerType(piMap.get("CUSTOMER_TYPE")==null?"":piMap.get("CUSTOMER_TYPE").toString());
					}
				}
			}
		}catch(Exception e){
			LogManager.info("Exception @ HomeDAO.editQuoteDetails(): "+e);
			e.printStackTrace();
		}
	}

	public int locationDtlsCount(HomeBean bean, String str) {
		int count = 0;
		try{
			if(StringUtils.isNotBlank(bean.getApplicationNo())){
				String query = "SELECT COUNT(*) FROM OFS_LOCATION_DETAILS A WHERE APPLICATION_NO = ?"
						+ " AND AMEND_ID = (SELECT MAX(AMEND_ID) FROM OFS_LOCATION_DETAILS B"
						+ " WHERE A.APPLICATION_NO = B.APPLICATION_NO AND A.LOCATION_ID = B.LOCATION_ID)";
				if("active".equalsIgnoreCase(str)){
					query += " AND STATUS = 'Y'";
				}
				Object[] args = new Object[]{bean.getApplicationNo()};
				LogManager.info("HomeDAO.locationDtlsCount() Query: "+queryFrammer(query, args));
				count = this.mytemplate.queryForInt(query,args);
			}
		}catch(Exception e){
			LogManager.info("Exception @ HomeDAO.locationDtlsCount(): "+e);
			e.printStackTrace();
		}
		return count;
	}

	public boolean manipulateHomePosition(HomeBean bean) {
		boolean status = false;
		try{
			if(StringUtils.isNotBlank(bean.getQuoteNo())){
				String query = getQuery("CNT_HOME_DTL");
				Object[] args = new Object[]{bean.getQuoteNo()};
				LogManager.info("HomeDAO.manipulateHomePosition() Query: "+queryFrammer(query, args));
				int hpCount = this.mytemplate.queryForInt(query, args);

				String sql = "";
				Object[] obj = null;
				int count = 0;
				if(hpCount<= 0){
					sql = getQuery("INS_HOME_DTL");
					obj = new Object[16];
					obj[0] = bean.getQuoteNo() == null ? "" : bean.getQuoteNo();
					obj[1] = bean.getCustomerId() == null ? "" : bean.getCustomerId();
					obj[2] = (StringUtils.isBlank(bean.getIssuer())?bean.getLoginId():brokerLoginId(bean.getBrokerCode()));
					obj[3] = bean.getProductId();
					obj[4] = "";
					obj[5] = "0";
					obj[6] = bean.getInceptionDt();
					obj[7] = bean.getExpiryDt();
					obj[8] = bean.getApplicationNo();
					obj[9] = "Y";
					obj[10] = bean.getSchemeId();
					obj[11] = bean.getContentTypeId();
					obj[12] = (StringUtils.isBlank(bean.getIssuer()) ? "1" : bean.getIssuer());
					obj[13] = bean.getBrokerCode();
					obj[14] = bean.getExecutive();
					obj[15] = bean.getBranchCode();
				}else if(hpCount>0){
					String rLoginId = "";
					String rApplicationId = "";
					String rBrokerCode = "";
					String rExecutiveId = "";
					String rBranchCode = "";
					if(("RU".equalsIgnoreCase(bean.getQuoteStatus()) || "RA".equalsIgnoreCase(bean.getQuoteStatus()) || "RA".equalsIgnoreCase(bean.getQuoteStatus()))
							&& ("RSAIssuer".equalsIgnoreCase(bean.getUserType()) || "admin".equalsIgnoreCase(bean.getUserType()))){
						String rquery = "SELECT LOGIN_ID, APPLICATION_ID, BROKER_CODE, AC_EXECUTIVE_ID, BRANCH_CODE"
								+ " FROM HOME_POSITION_MASTER WHERE QUOTE_NO=?";
						Object[] rargs = new Object[]{bean.getQuoteNo()};
						LogManager.info("HomeDAO.manipulateHomePosition() Query: "+queryFrammer(rquery, rargs));
						Map<String, Object> rMap = this.mytemplate.queryForMap(rquery, rargs);
						if(rMap!=null && rMap.size()>0){
							rLoginId = rMap.get("LOGIN_ID")==null?"":rMap.get("LOGIN_ID").toString();
							rApplicationId = rMap.get("APPLICATION_ID")==null?"":rMap.get("APPLICATION_ID").toString();
							rBrokerCode = rMap.get("BROKER_CODE")==null?"":rMap.get("BROKER_CODE").toString();
							rExecutiveId = rMap.get("AC_EXECUTIVE_ID")==null?"":rMap.get("AC_EXECUTIVE_ID").toString();
							rBranchCode = rMap.get("BRANCH_CODE")==null?"":rMap.get("BRANCH_CODE").toString();
						}
					}
					sql = getQuery("UPD_HOME_DTL");
					obj = new Object[16];
					obj[0] = bean.getCustomerId() == null ? "" : bean.getCustomerId();
					if(("RU".equalsIgnoreCase(bean.getQuoteStatus()) || "RA".equalsIgnoreCase(bean.getQuoteStatus()) || "RA".equalsIgnoreCase(bean.getQuoteStatus()))
							&& ("RSAIssuer".equalsIgnoreCase(bean.getUserType()) || "admin".equalsIgnoreCase(bean.getUserType()))){
						obj[1] = rLoginId;
					}else{
						obj[1] = (StringUtils.isBlank(bean.getIssuer())?bean.getLoginId():brokerLoginId(bean.getBrokerCode()));
					}
					obj[2] = bean.getProductId();
					obj[3] = "";
					obj[4] = "0";
					obj[5] = bean.getInceptionDt();
					obj[6] = bean.getExpiryDt();
					obj[7] = bean.getApplicationNo();
					obj[8] = "Y";
					obj[9] = bean.getSchemeId();
					obj[10] = bean.getContentTypeId();
					if(("RU".equalsIgnoreCase(bean.getQuoteStatus()) || "RA".equalsIgnoreCase(bean.getQuoteStatus()) || "RA".equalsIgnoreCase(bean.getQuoteStatus()))
							&& ("RSAIssuer".equalsIgnoreCase(bean.getUserType()) || "admin".equalsIgnoreCase(bean.getUserType()))){
						obj[11] = rApplicationId;
						obj[12] = rBrokerCode;
						obj[13] = rExecutiveId;
						obj[14] = rBranchCode; 
					}else{
						obj[11] = (StringUtils.isBlank(bean.getIssuer()) ? "1" : bean.getIssuer());
						obj[12] = bean.getBrokerCode();
						obj[13] = bean.getExecutive();
						obj[14] = bean.getBranchCode();
					}
					obj[15] = bean.getQuoteNo();
				}
				removeNull(obj);
				LogManager.info("HomeDAO.manipulateHomePosition() Query: "+queryFrammer(sql, obj));
				count = this.mytemplate.update(sql,obj);
				status = (count>0?true:false);
			}
		}catch(Exception e){
			LogManager.info("Exception @ HomeDAO.manipulateHomePosition(): "+e);
			e.printStackTrace();
		}
		return status;
	}
	
	public String brokerLoginId(String brokerCode){
		String loginId = "";
		try{
			String query = "SELECT LOGIN_ID FROM LOGIN_MASTER WHERE AGENCY_CODE = ? AND UPPER(USERTYPE) = 'BROKER'";
			Object[] args = new Object[]{brokerCode};
			LogManager.info("HomeDAO.brokerLoginId() Query: "+queryFrammer(query, args));
			loginId = String.valueOf(this.mytemplate.queryForObject(query, args, String.class));
		}catch(Exception e){
			LogManager.info("Exception @ HomeDAO.brokerLoginId(): "+e);
			e.printStackTrace();
		}
		return loginId;
	}

	public boolean updateLocationDtls(HomeBean bean) {
		boolean status = false;
		try{
			if(StringUtils.isNotBlank(bean.getApplicationNo())){
				String query = "UPDATE OFS_LOCATION_DETAILS A SET QUOTE_NO=?, INCEPTION_DATE = TO_DATE(?,'DD/MM/YYYY'),"
						+ " EXPIRY_DATE = TO_DATE(?,'DD/MM/YYYY'), CUSTOMER_ID = ?, CUST_NAME = ?"
						+ " WHERE APPLICATION_NO = ? AND AMEND_ID = (SELECT MAX(AMEND_ID) FROM OFS_LOCATION_DETAILS B"
						+ " WHERE A.APPLICATION_NO = B.APPLICATION_NO AND A.LOCATION_ID = B.LOCATION_ID)";
				Object[] args = new Object[]{bean.getQuoteNo(), bean.getInceptionDt(), bean.getExpiryDt(),
						bean.getCustomerId(), bean.getCustomerName(), bean.getApplicationNo()};
				LogManager.info("HomeDAO.updateLocationDtls() Query: "+queryFrammer(query, args));
				int count = this.mytemplate.update(query,args);
				status = (count>0?true:false);
			}
		}catch(Exception e){
			LogManager.info("Exception @ HomeDAO.updateLocationDtls(): "+e);
			e.printStackTrace();
		}
		return status;
	}

	public List<Map<String, Object>> activeSchemeList(HomeBean bean) {
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		try {
			String query="SELECT SCHEME_ID, INITCAP(SCHEME_NAME) SCHEME_NAME FROM OFS_SCHEME_MASTER"
					+ " WHERE STATUS='Y' AND BRANCH_CODE = ? ORDER BY DISPLAY_ORDER ASC";
			Object[] args = new Object[]{bean.getBranchCode()};
			LogManager.info("HomeDAO.activeSchemeList() Query: "+queryFrammer(query, args));
			list = this.mytemplate.queryForList(query, args);
		} catch (Exception e) {
			LogManager.info("Exception @ HomeDAO.activeSchemeList(): "+e);
			e.printStackTrace();
		}
		return list;
	}

	public List<Map<String, Object>> activeLocationDtls(HomeBean bean) {
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		try {
			String query="SELECT LOCATION_ID, INITCAP(LOCATION_NAME) LOCATION_NAME, SCHEME_ID FROM OFS_LOCATION_DETAILS A"
					+ " WHERE QUOTE_NO = ? AND APPLICATION_NO = ? AND STATUS = 'Y'"
					+ " AND AMEND_ID = (SELECT MAX(AMEND_ID) FROM OFS_LOCATION_DETAILS B"
					+ " WHERE A.APPLICATION_NO = B.APPLICATION_NO) ORDER BY LOCATION_NAME ASC";
			Object[] args = new Object[]{bean.getQuoteNo(), bean.getApplicationNo()};
			LogManager.info("HomeDAO.activeLocationDtls() Query: "+queryFrammer(query, args));
			list = this.mytemplate.queryForList(query, args);
		} catch (Exception e) {
			LogManager.info("Exception @ HomeDAO.activeLocationDtls(): "+e);
			e.printStackTrace();
		}
		return list;
	}

	public boolean updateLocationScheme(String locationSchemes, String locationId, String quoteNo,
			String applicationNo) {
		boolean status = false;
		try{
			if(StringUtils.isNotBlank(quoteNo) && StringUtils.isNotBlank(applicationNo) && StringUtils.isNotBlank(locationId)){
				String query = "UPDATE OFS_LOCATION_DETAILS SET SCHEME_ID = ?"
						+ " WHERE QUOTE_NO = ? AND APPLICATION_NO = ? AND LOCATION_ID = ? AND STATUS = 'Y'";
				Object[] args = new Object[]{locationSchemes, quoteNo, applicationNo, locationId};
				LogManager.info("HomeDAO.updateLocationScheme() Query: "+queryFrammer(query, args));
				int count = this.mytemplate.update(query,args);
				status = (count>0?true:false);
			}
		}catch(Exception e){
			LogManager.info("Exception @ HomeDAO.updateLocationScheme(): "+e);
			e.printStackTrace();
		}
		return status;
	}
	
	 public boolean updatePremiumHpm(HomeBean bean){
		 boolean status = false;
		 try{
			 String queryTp = "SELECT SUM(BASE_PREMIUM) BASE_PREMIUM, SUM(OPTIONAL_PREMIUM) OPTIONAL_PREMIUM,"
			 		+ " SUM(PREMIUM_APPLIED) TOTAL_PREMIUM, SUM(VOLUME_DISCOUNT_AMOUNT) VOLUME_DISCOUNT_AMOUNT,"
			 		+ " SUM(CORPORATE_DISCOUNT_AMOUNT) CORPORATE_DISCOUNT_AMOUNT,"
			 		+ " SUM(SPECIAL_DISCOUNT_AMOUNT) SPECIAL_DISCOUNT_AMOUNT"
			 		+ "  FROM OFS_SCHEME_DETAILS WHERE QUOTE_NO = ? AND STATUS = 'Y'";
			 Object[] argsTp = new Object[]{bean.getQuoteNo()};
			 LogManager.info("HomeDAO.updatePremiumHpm() Query1: "+queryFrammer(queryTp, argsTp));
			 Map<String, Object> mapTp = this.mytemplate.queryForMap(queryTp,argsTp);
			 double basePremium = 0;
			 double optionalPremium = 0;
			 double volumeDiscount = 0;
			 double corporateDiscount = 0;
			 double specialDiscount = 0;
			 double totalPremium = 0;
			 if(mapTp!=null && mapTp.size()>0){
				 basePremium = mapTp.get("BASE_PREMIUM")==null?0:Double.valueOf(mapTp.get("BASE_PREMIUM").toString());
				 optionalPremium = mapTp.get("OPTIONAL_PREMIUM")==null?0:Double.valueOf(mapTp.get("OPTIONAL_PREMIUM").toString());
				 volumeDiscount = mapTp.get("VOLUME_DISCOUNT_AMOUNT")==null?0:Double.valueOf(mapTp.get("VOLUME_DISCOUNT_AMOUNT").toString());
				 corporateDiscount = mapTp.get("CORPORATE_DISCOUNT_AMOUNT")==null?0:Double.valueOf(mapTp.get("CORPORATE_DISCOUNT_AMOUNT").toString());
				 specialDiscount = mapTp.get("SPECIAL_DISCOUNT_AMOUNT")==null?0:Double.valueOf(mapTp.get("SPECIAL_DISCOUNT_AMOUNT").toString());
				 totalPremium = mapTp.get("TOTAL_PREMIUM")==null?0:Double.valueOf(mapTp.get("TOTAL_PREMIUM").toString());
			 }
			 
			 String queryLd = "SELECT EXCESS_SIGN, EXCESS_PREMIUM FROM HOME_POSITION_MASTER WHERE QUOTE_NO = ?";
			 Object[] argsLd = new Object[]{bean.getQuoteNo()};
			 LogManager.info("HomeDAO.updatePremiumHpm() Query2: "+queryFrammer(queryLd, argsLd));
			 Map<String, Object> mapLd = this.mytemplate.queryForMap(queryLd,argsLd);
			 String excessSign = "+";
			 double excessPremium = 0;
			 if(mapLd!=null && mapLd.size()>0){
				 excessSign = mapLd.get("EXCESS_SIGN")==null?"+":mapLd.get("EXCESS_SIGN").toString();
				 excessPremium = mapLd.get("EXCESS_PREMIUM")==null?0:Double.valueOf(mapLd.get("EXCESS_PREMIUM").toString());
			 }
			 
			 double totalPremiumLd = "+".equalsIgnoreCase(excessSign)?(totalPremium+excessPremium):(totalPremium-excessPremium);
			 double policyFeePercent = nonMotorPolicyFeePercent(bean.getBranchCode());
			 double policyFee = (totalPremiumLd*(policyFeePercent/100)); ;
			 
			 double overallPremium = (totalPremiumLd+policyFee);
			 
			 String queryHpm = "UPDATE HOME_POSITION_MASTER SET ACTUAL_PREMIUM = ?, ACTUAL_OPPREMIUM = ?,"
			 		+ " VOLUME_DISCOUNT_AMOUNT = ?, CORPORATE_DISCOUNT_AMOUNT = ?, SPECIAL_DISCOUNT_AMOUNT = ?,"
			 		+ " PREMIUM = ?, POLICY_FEE = ?, OVERALL_PREMIUM = ?, POLICY_FEE_PERCENT = ?"
			 		+ " WHERE QUOTE_NO = ?";
			 Object[] argsHpm = new Object[]{basePremium, optionalPremium,
					 volumeDiscount, corporateDiscount, specialDiscount,
					 totalPremium, policyFee, overallPremium, policyFeePercent,
					 bean.getQuoteNo()};
			 LogManager.info("HomeDAO.updatePremiumHpm() Query3: "+queryFrammer(queryHpm, argsHpm));
			 int count = this.mytemplate.update(queryHpm,argsHpm);
			 status = (count>0?true:false);
		 }catch(Exception e){
			LogManager.info("Exception @ HomeDAO.updatePremiumHpm(): "+e);
			 e.printStackTrace();
		 }
		 return status;
	 }
	 
	 public Map<String, Object> consolidatePremiumDetails(HomeBean bean){
		 Map<String, Object> map = new HashMap<String, Object>();
		 try{
			 String query = "SELECT PREMIUM, POLICY_FEE, EXCESS_SIGN, EXCESS_PREMIUM,"
			 		+ " VOLUME_DISCOUNT_AMOUNT, CORPORATE_DISCOUNT_AMOUNT, SPECIAL_DISCOUNT_AMOUNT,"
			 		+ " OVERALL_PREMIUM, POLICY_FEE_PERCENT,"
				 	+ " ACTUAL_PREMIUM, ACTUAL_OPPREMIUM"
				 	+ " FROM HOME_POSITION_MASTER WHERE QUOTE_NO = ?";
			 Object[] args = new Object[]{bean.getQuoteNo()};
			 LogManager.info("HomeDAO.consolidatePremiumDetails() Query: "+queryFrammer(query, args));
			 map = this.mytemplate.queryForMap(query, args);
		 }catch(Exception e){
			LogManager.info("Exception @ HomeDAO.consolidatePremiumDetails(): "+e);
			e.printStackTrace();
		 }
		 return map;
	 }
	 
	 public double nonMotorPolicyFeePercent(String branchCode){
		 double policyFeePercent = 0;
		 try{
			 String query = "SELECT PERCENT FROM CONSTANT_DETAIL WHERE"
			 		+ " DETAIL_NAME = 'NON_MOTOR_POLICY_FEE_PERCENT'"
			 		+ " AND CATEGORY_ID = '215' AND CATEGORY_DETAIL_ID = '1'"
			 		+ " AND STATUS = 'Y' AND BRANCH_CODE = ?";
			 Object[] args = new Object[]{branchCode};
			 LogManager.info("HomeDAO.nonMotorPolicyFee() Query: "+queryFrammer(query, args));
			 Map<String, Object> map = this.mytemplate.queryForMap(query, args);
			 if(map!=null && map.size()>0){
				 policyFeePercent = map.get("PERCENT")==null?0:Double.valueOf(map.get("PERCENT").toString());
			 }
		 }catch(Exception e){
			 LogManager.info("Exception @ HomeDAO.nonMotorPolicyFee(): "+e);
			 e.printStackTrace();
		 }
		 return policyFeePercent;
	 }

	public void editReferralRemarks(HomeBean bean) {
		try{
			String query = "SELECT SUMMARY_REMARKS, REFERRAL_DESCRIPTION FROM HOME_POSITION_MASTER WHERE QUOTE_NO = ?";
			Object[] args = new Object[]{bean.getQuoteNo()};
			LogManager.info("HomeDAO.editReferralRemarks() Query: "+queryFrammer(query, args));
			Map<String, Object> map = this.mytemplate.queryForMap(query, args);
			if(map!= null && map.size()>0){
				String refRemarks = map.get("SUMMARY_REMARKS")==null?"":map.get("SUMMARY_REMARKS").toString();
				String refType = map.get("REFERRAL_DESCRIPTION")==null?"":map.get("REFERRAL_DESCRIPTION").toString();
				if("manual".equalsIgnoreCase(refType) && StringUtils.isNotBlank(refRemarks)){
					bean.setReferralYN("Y");
					bean.setReferralComments(refRemarks);
				}
			}
		}catch(Exception e){
			LogManager.info("Exception @ HomeDAO.editReferralRemarks(): "+e);
			e.printStackTrace();
		}
	}

	public boolean updateLoadingDiscountHpm(HomeBean bean) {
		boolean status = false;
		try{
			String query = "UPDATE HOME_POSITION_MASTER SET EXCESS_SIGN = ?, EXCESS_PREMIUM = ? WHERE QUOTE_NO = ?";
			Object[] args = new Object[]{(StringUtils.isBlank(bean.getExcessSign())?"+":bean.getExcessSign()), (StringUtils.isBlank(bean.getExcessPremium())?"0":bean.getExcessPremium()), bean.getQuoteNo()};
			LogManager.info("HomeDAO.updateLoadingDiscountHpm() Query: "+queryFrammer(query, args));
			int count = this.mytemplate.update(query, args);
			status = (count>0?true:false);
		}catch(Exception e){
			LogManager.info("Exception @ HomeDAO.updateLoadingDiscountHpm(): "+e);
			e.printStackTrace();
		}
		return status;
	}
	
	public boolean adminReferralUpdateHpm(HomeBean bean){
		boolean status = false;
		try{
			String query = "UPDATE HOME_POSITION_MASTER SET REMARKS=?, ADMIN_REMARKS=?, ADMIN_REFERRAL_STATUS='N', STATUS=?, "
					+ "EFFECTIVE_DATE=SYSDATE+30,APPROVED_BY=(SELECT NVL(USERNAME,' ') FROM LOGIN_MASTER WHERE LOGIN_ID=?) WHERE QUOTE_NO=?";
			Object[] args = new Object[5];
			if("Y".equalsIgnoreCase(bean.getAdminRefStatus())){
				args[0] = "Admin";
				args[2] = "Y";
			}else if("N".equalsIgnoreCase(bean.getAdminRefStatus())){
				args[0] = "Referal";
				args[2] = "R";
			}else if("A".equalsIgnoreCase(bean.getAdminRefStatus())){
				args[0] = "Referal";
				args[2] = "Y";
			}
			args[1] = bean.getAdminRemarks();
			args[3] = bean.getLoginId();
			args[4] = bean.getQuoteNo();
			LogManager.info("HomeDAO.adminReferralUpdateHpm() Query: "+queryFrammer(query, args));
			int count = this.mytemplate.update(query, args);
			status = (count>0?true:false);
		}catch(Exception e){
			LogManager.info("Exception @ HomeDAO.adminReferralUpdateHpm(): "+e);
			e.printStackTrace();
		}
		return status;
	}

	public void adminReferralStatus(HomeBean bean) {
		try{
			String query = "SELECT REMARKS, STATUS, ADMIN_REMARKS FROM HOME_POSITION_MASTER WHERE QUOTE_NO = ?";
			Object[] args = new Object[]{bean.getQuoteNo()};
			LogManager.info("HomeDAO.adminReferralStatus() Query: "+queryFrammer(query, args));
			Map<String, Object> map = this.mytemplate.queryForMap(query, args);
			if(map!=null && map.size()>0){
				String remarks = map.get("REMARKS")==null?"":map.get("REMARKS").toString();
				String status = map.get("STATUS")==null?"":map.get("STATUS").toString();
				String adminRemarks = map.get("ADMIN_REMARKS")==null?"":map.get("ADMIN_REMARKS").toString();
				if("Referal".equalsIgnoreCase(remarks) && "Y".equalsIgnoreCase(status)){
					bean.setAdminRefStatus("A");
				}else if("Referal".equalsIgnoreCase(remarks) && "R".equalsIgnoreCase(status)){
					bean.setAdminRefStatus("N");
				}else if("Admin".equalsIgnoreCase(remarks) && "Y".equalsIgnoreCase(status)){
					bean.setAdminRefStatus("Y");
				}
				bean.setAdminRemarks(adminRemarks);
			}
		}catch(Exception e){
			LogManager.info("Exception @ HomeDAO.adminReferralStatus(): "+e);
			e.printStackTrace();
		}
	}
	
	public int installmentcount(HomeBean bean) {
		int count=0;
		try {
			String query = getQuery("GET_INSTALLMENT_DETAIL_COUNT");
			Object[] args = new Object[]{bean.getQuoteNo()};
			LogManager.info("HomeDAO.installmentcount() Query: "+queryFrammer(query, args));
			count=this.mytemplate.queryForInt(query, args);
		}
		catch (Exception e) {
			LogManager.info("Exception @ HomeDAO.installmentcount Error: "+e);
			e.printStackTrace();
		}
		return count;
	}

	public void deleteinstallment(HomeBean bean) {
		try {
			String query = getQuery("DEL_INSTALLMENT_DETAIL");
			Object[] args = new Object[]{bean.getQuoteNo()};
			LogManager.info("HomeDAO.deleteinstallment() Query: "+queryFrammer(query, args));
			this.mytemplate.update(query, args);
		}catch (Exception e) {
			LogManager.info("Exception @ HomeDAO.deleteinstallment() Error: "+e);
			e.printStackTrace();
		}
	}

	public boolean updateInceptionDate(HomeBean bean) {
		boolean status = false;
		try{
			String query1 = "UPDATE OFS_DATA SET INCEPTION_DATE = TO_DATE(?,'DD/MM/YYYY') WHERE QUOTE_NO = ?";
			Object[] args1 = new Object[]{bean.getInceptionDt(), bean.getQuoteNo()};
			LogManager.info("HomeDAO.updateInceptionDate() Query: "+queryFrammer(query1, args1));
			int count1 = this.mytemplate.update(query1, args1);
			status = (count1>0?true:false);
			
			String query2 = "UPDATE HOME_POSITION_MASTER SET INCEPTION_DATE = TO_DATE(?,'DD/MM/YYYY') WHERE QUOTE_NO = ?";
			Object[] args2 = new Object[]{bean.getInceptionDt(), bean.getQuoteNo()};
			LogManager.info("HomeDAO.updateInceptionDate() Query: "+queryFrammer(query2, args2));
			int count2 = this.mytemplate.update(query2, args2);
			status = (count2>0?true:false);
			
		}catch(Exception e){
			LogManager.info("Exception @ HomeDAO.updateInceptionDate() Error: "+e);
			e.printStackTrace();
		}
		return status;
	}

	public Map checkPolicy(HomeBean bean) {
		Map fromPosition = new HashMap();
		try {
			String sql=getQuery("GET_POLICY_STATUS");
			Object obj[] = new Object[]{bean.getQuoteNo()};
			LogManager.info("Query=>"+sql);
			LogManager.info("Obj[]=>"+StringUtils.join(obj,","));
			fromPosition = this.mytemplate.queryForMap(sql,obj);
			LogManager.info("Map Size=>"+fromPosition.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fromPosition;
	}

	public String quoteBelongingLoginid(HomeBean bean) {
		try{
			String query = "SELECT LOGIN_ID FROM HOME_POSITION_MASTER WHERE QUOTE_NO = ?";
			Object[] args = new Object[]{bean.getQuoteNo()};
			LogManager.info("HomeDAO.quoteBelongingLoginid() Query: "+queryFrammer(query, args));
			String loginId = String.valueOf(this.mytemplate.queryForObject(query, args, String.class));
			return loginId;
		}catch(Exception e){
			LogManager.info("Exception @ HomeDao.quoteBelongingLoginid() Error: "+e);
			e.printStackTrace();
		}
		return "";
	}

	public String quoteBelongingAgencyCode(HomeBean bean) {
		try{
			String query = "SELECT B.OA_CODE FROM HOME_POSITION_MASTER A, LOGIN_MASTER B"
					+ " WHERE A.LOGIN_ID = B.LOGIN_ID AND A.QUOTE_NO = ?";
			Object[] args = new Object[]{bean.getQuoteNo()};
			LogManager.info("HomeDAO.quoteBelongingAgencyCode() Query: "+queryFrammer(query, args));
			String loginId = String.valueOf(this.mytemplate.queryForObject(query, args, String.class));
			return loginId;
		}catch(Exception e){
			LogManager.info("Exception @ HomeDao.quoteBelongingAgencyCode() Error: "+e);
			e.printStackTrace();
		}
		return "";
	}

	public Map<String, Object> loadDiscValPremiumDtls(String loginId, String productId) {
		Map<String, Object> map = new HashMap<String, Object>();
		try{
			String query = "SELECT B.MIN_LOADING, B.MAX_LOADING, B.MIN_DISCOUNT, B.MAX_DISCOUNT"
					+ " FROM LOGIN_MASTER A, LOGIN_USER_DETAILS B  WHERE A.OA_CODE = B.AGENCY_CODE AND"
					+ " A.LOGIN_ID = ? AND B.PRODUCT_ID = ?";
			Object[] args = new Object[]{loginId, productId};
			LogManager.info("HomeDAO.loadDiscValPremiumDtls() Query: "+queryFrammer(query, args));
			List<Map<String, Object>> list = this.mytemplate.queryForList(query, args);
			if(list!=null && list.size()>0){
				map = list.get(0);
			}
		}catch(Exception e){
			LogManager.info("Exception @ HomeDao.loadDiscValPremiumDtls() Error: "+e);
			e.printStackTrace();
		}
		return map;
	}

	public double loadDiscPercent(HomeBean bean) {
		double d=0;
		try{
			String query = "SELECT (?/PREMIUM)*100 FROM HOME_POSITION_MASTER WHERE QUOTE_NO = ?";
			Object[] args = new Object[]{bean.getExcessPremium(),bean.getQuoteNo()};
			LogManager.info("HomeDAO.loadDiscPercent() Query: "+queryFrammer(query, args));
			d = Double.valueOf(String.valueOf(this.mytemplate.queryForObject(query, args, Double.class)));
		}catch(Exception e){
			LogManager.info("Exception @ HomeDao.loadDiscPercent() Error: "+e);
			e.printStackTrace();
		}
		return d;
	}
	
	public String schemeNameById(String schemeId, String productId){
		try{
			String query = "SELECT SCHEME_NAME FROM OFS_SCHEME_MASTER WHERE SCHEME_ID = ? AND PRODUCT_ID = ?";
			Object[] args = new Object[]{schemeId, productId};
			LogManager.info("HomeDAO.schemeNameById() Query: "+queryFrammer(query, args));
			String loginId = String.valueOf(this.mytemplate.queryForObject(query, args, String.class));
			return loginId;
		}catch(Exception e){
			LogManager.info("Exception @ HomeDao.schemeNameById() Error: "+e);
			e.printStackTrace();
		}
		return "";
	}

	public int deleteExcessDetails(HomeBean bean) {
		int del = 0;
		try{
			String query = "DELETE FROM OFS_EXCESS_RATE_DETAILS WHERE QUOTE_NO = ? AND SCHEME_ID = ? AND PRODUCT_ID = ?";
			Object[] args = new Object[] {bean.getQuoteNo(), bean.getSchemeId(), bean.getProductId()};
			LogManager.info("HomeDao.deleteExcessDetails() Query: "+queryFrammer(query, args));
			del = this.mytemplate.update(query, args);
		}catch(Exception e){
			LogManager.info("Exception @ HomeDao.deleteExcessDetails() Error: "+e);
			e.printStackTrace();
		}
		return del;
	}

	public int insertExcessDetails(String quoteNo, String schemeId, String productId, String excessId, String excessPer,
			String excessAmt, String excessDesc) {
		int ins = 0;
		try{
			String query = "INSERT INTO OFS_EXCESS_RATE_DETAILS(SNO, QUOTE_NO, SCHEME_ID, PRODUCT_ID, EXCESS_ID, EXCESS_PERCENT,"
					+ " EXCESS_AMOUNT, EXCESS_DESCRIPTION, ENTRY_DATE, STATUS) VALUES (OFS_EXCESS_RATE_SNO.NEXTVAL,"
					+ " ?,?,?,?,?,?,?,SYSDATE,'Y')";
			Object[] args = new Object[] {quoteNo, schemeId, productId, excessId, excessPer, excessAmt, excessDesc};
			LogManager.info("HomeDao.insertExcessDetails() Query: "+queryFrammer(query, args));
			ins = this.mytemplate.update(query, args);
		}catch(Exception e){
			LogManager.info("Exception @ HomeDao.insertExcessDetails() Error: "+e);
			e.printStackTrace();
		}
		return ins;
	}

	public int deleteWarrantyDetails(HomeBean bean) {
		int del = 0;
		try{
			String query = "DELETE FROM OFS_WARRENTY_DETAILS WHERE QUOTE_NO = ? AND SCHEME_ID = ? AND PRODUCT_ID = ?";
			Object[] args = new Object[] {bean.getQuoteNo(), bean.getSchemeId(), bean.getProductId()};
			LogManager.info("HomeDao.deleteWarrantyDetails() Query: "+queryFrammer(query, args));
			del = this.mytemplate.update(query, args);
		}catch(Exception e){
			LogManager.info("Exception @ HomeDao.deleteWarrantyDetails() Error: "+e);
			e.printStackTrace();
		}
		return del;
	}

	public int deleteExclusionDetails(HomeBean bean) {
		int del = 0;
		try{
			String query = "DELETE FROM OFS_EXCLUSION_DETAILS WHERE QUOTE_NO = ? AND SCHEME_ID = ? AND PRODUCT_ID = ?";
			Object[] args = new Object[] {bean.getQuoteNo(), bean.getSchemeId(), bean.getProductId()};
			LogManager.info("HomeDao.deleteExclusionDetails() Query: "+queryFrammer(query, args));
			del = this.mytemplate.update(query, args);
		}catch(Exception e){
			LogManager.info("Exception @ HomeDao.deleteExclusionDetails() Error: "+e);
			e.printStackTrace();
		}
		return del;
	}

	public int insertWarrantyDetails(String quoteNo, String schemeId, String productId, String warId, String warDesc) {
		int ins = 0;
		try{
			String query = "INSERT INTO OFS_WARRENTY_DETAILS(SNO, QUOTE_NO, SCHEME_ID, PRODUCT_ID, WARRENTY_ID,"
					+ " WARRENTY_DESCRIPTION, ENTRY_DATE, STATUS) VALUES (OFS_WARRENTY_SNO.NEXTVAL,"
					+ " ?,?,?,?,?,SYSDATE,'Y')";
			Object[] args = new Object[] {quoteNo, schemeId, productId, warId, warDesc};
			LogManager.info("HomeDao.insertWarrantyDetails() Query: "+queryFrammer(query, args));
			ins = this.mytemplate.update(query, args);
		}catch(Exception e){
			LogManager.info("Exception @ HomeDao.insertWarrantyDetails() Error: "+e);
			e.printStackTrace();
		}
		return ins;
	}

	public int insertExclusionDetails(String quoteNo, String schemeId, String productId, String exlId, String exlDesc) {
		int ins = 0;
		try{
			String query = "INSERT INTO OFS_EXCLUSION_DETAILS(SNO, QUOTE_NO, SCHEME_ID, PRODUCT_ID, EXCLUSION_ID,"
					+ " EXCLUSION_DESCRIPTION, ENTRY_DATE, STATUS) VALUES (OFS_EXCLUSION_SNO.NEXTVAL,"
					+ " ?,?,?,?,?,SYSDATE,'Y')";
			Object[] args = new Object[] {quoteNo, schemeId, productId, exlId, exlDesc};
			LogManager.info("HomeDao.insertExclusionDetails() Query: "+queryFrammer(query, args));
			ins = this.mytemplate.update(query, args);
		}catch(Exception e){
			LogManager.info("Exception @ HomeDao.insertExclusionDetails() Error: "+e);
			e.printStackTrace();
		}
		return ins;
	}

	public List<Map<String, Object>> brokerMinimumPremium(HomeBean bean) {
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		try{
			 String minPremiumQuery =getQuery("GET_HOME_MIN_PREMIUM_BROKER");
			 Object[] minPremiumObj = new Object[]{bean.getQuoteNo(), bean.getProductId(), bean.getSchemeId()};
			 LogManager.info("HomeDAO.brokerMinimumPremium() Query: "+queryFrammer(minPremiumQuery, minPremiumObj));
			 list = this.mytemplate.queryForList(minPremiumQuery,minPremiumObj);
		}catch(Exception e){
			 LogManager.info("Exception @ HomeDao.brokerMinimumPremium()"
			 		+ " QuoteNo: "+bean.getQuoteNo()+" SchemeId: "+bean.getSchemeId()+" ProductId: "+bean.getProductId()+" Error: "+e); 
			 e.printStackTrace();
		}
		return list;
	}

	public List<Map<String, Object>> schemeAvailLocPremList(HomeBean bean) {
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		try{
			 String query = "SELECT LOCATION_ID, SUM(PREMIUM_AMOUNT) PREMIUM_AMOUNT FROM OFS_DATA_DETAILS"
			 		+ " WHERE QUOTE_NO = ? AND AMEND_ID = 0 AND PRODUCT_ID = ? AND SCHEME_ID = ?"
			 		+ " AND CONTENT_TYPE_ID = 0 AND COVERAGES_ID NOT IN ('99999') AND STATUS = 'Y' GROUP BY LOCATION_ID";
			 Object[] args = new Object[]{bean.getQuoteNo(), bean.getProductId(), bean.getSchemeId()};
			 LogManager.info("HomeDAO.schemeAvailLocList() Query: "+queryFrammer(query, args));
			 list = this.mytemplate.queryForList(query,args);
		}catch(Exception e){
			 LogManager.info("Exception @ HomeDao.schemeAvailLocList()"
			 		+ " QuoteNo: "+bean.getQuoteNo()+" SchemeId: "+bean.getSchemeId()+" ProductId: "+bean.getProductId()+" Error: "+e); 
			 e.printStackTrace();
		}
		return list;
	}

	public List<Map<String, Object>> schemeAvailLocParPremList(HomeBean bean) {
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		try{
			 String query = "SELECT LOCATION_ID, SUM(PREMIUM_AMOUNT) PREMIUM_AMOUNT FROM OFS_DATA_DETAILS"
			 		+ " WHERE QUOTE_NO = ? AND AMEND_ID = 0 AND PRODUCT_ID = ? AND SCHEME_ID = ? AND LOCATION_ID = ?"
			 		+ " AND CONTENT_TYPE_ID = 0 AND COVERAGES_ID NOT IN ('99999') AND STATUS = 'Y' GROUP BY LOCATION_ID";
			 Object[] args = new Object[]{bean.getQuoteNo(), bean.getProductId(), bean.getSchemeId(), bean.getLocationId()};
			 LogManager.info("HomeDAO.schemeAvailLocParPremList() Query: "+queryFrammer(query, args));
			 list = this.mytemplate.queryForList(query,args);
		}catch(Exception e){
			 LogManager.info("Exception @ HomeDao.schemeAvailLocParPremList()"
			 		+ " QuoteNo: "+bean.getQuoteNo()+" SchemeId: "+bean.getSchemeId()+" LocationId: "+bean.getLocationId()+" ProductId: "+bean.getProductId()+" Error: "+e); 
			 e.printStackTrace();
		}
		return list;
	}
	
	public double schemeLocPremium(String quoteNo, String productId, String schemeId, String locationId, String premiumType){
		try{
			String query = "SELECT SUM(A.PREMIUM_AMOUNT) PREMIUM_AMOUNT FROM OFS_DATA_DETAILS A, OFS_COVERAGES_MASTER B"
					+ " WHERE A.PRODUCT_ID = B.PRODUCT_ID AND A.SCHEME_ID = B.SCHEME_ID AND A.COVERAGES_ID = B.COVERAGES_ID"
					+ " AND A.CONTENT_TYPE_ID = B.CONTENT_TYPE_ID AND A.AMEND_ID = B.AMEND_ID AND A.STATUS = B.STATUS"
					+ " AND A.QUOTE_NO = ? AND A.PRODUCT_ID = ? AND A.SCHEME_ID = ? AND A.LOCATION_ID = ? AND B.COVERAGES_TYPE = ?"
					+ " AND A.COVERAGES_ID NOT IN ('99999') AND A.CONTENT_TYPE_ID = 0 AND A.STATUS = 'Y' ";
			Object[] args = new Object[]{quoteNo, productId, schemeId, locationId, premiumType};
			 LogManager.info("HomeDAO.schemeLocPremium() Query: "+queryFrammer(query, args));
			 List<Map<String, Object>> list = this.mytemplate.queryForList(query,args);
			 if(list!=null && list.size()>0){
				 Map<String, Object> map = list.get(0);
				 if(map!=null && map.size()>0){
					 return map.get("PREMIUM_AMOUNT")==null?0:Double.valueOf(map.get("PREMIUM_AMOUNT").toString());
				 }
			 }
		}catch(Exception e){
		     LogManager.info("Exception @ HomeDao.schemeLocPremium()"
			 		+ " QuoteNo: "+quoteNo+" SchemeId: "+schemeId+" ProductId: "+productId+""
			 				+ " Locationid: "+locationId+" CoverageType: "+premiumType+" Error: "+e); 
			 e.printStackTrace();
		}
		return 0;
	}

	public boolean deleteSchemePremDtls(String quoteNo, String productId, String schemeId) {
		try{
			String query = "DELETE FROM OFS_SCHEME_DETAILS WHERE QUOTE_NO = ? AND PRODUCT_ID = ? AND SCHEME_ID = ?";
			Object[] args = new Object[]{quoteNo, productId, schemeId};
			LogManager.info("HomeDAO.deleteSchemePremDtls() Query: "+queryFrammer(query, args));
			int count = this.mytemplate.update(query, args);
			LogManager.info("HomeDAO.deleteSchemePremDtls() Count: "+count);
			return true;
		}catch(Exception e){
			 LogManager.info("Exception @ HomeDao.deleteSchemePremDtls()"
				 		+ " QuoteNo: "+quoteNo+" SchemeId: "+schemeId+" ProductId: "+productId+" Error: "+e);
			e.printStackTrace();
		}
		return false;
	}

	public boolean deleteSchemeLocPremDtls(String quoteNo, String productId, String schemeId, String locationId) {
		try{
			String query = "DELETE FROM OFS_SCHEME_DETAILS WHERE QUOTE_NO = ? AND PRODUCT_ID = ? AND SCHEME_ID = ? AND LOCATION_ID = ?";
			Object[] args = new Object[]{quoteNo, productId, schemeId, locationId};
			LogManager.info("HomeDAO.deleteSchemeLocPremDtls() Query: "+queryFrammer(query, args));
			int count = this.mytemplate.update(query, args);
			LogManager.info("HomeDAO.deleteSchemeLocPremDtls() Count: "+count);
			return true;
		}catch(Exception e){
			 LogManager.info("Exception @ HomeDao.deleteSchemeLocPremDtls()"
				 		+ " QuoteNo: "+quoteNo+" SchemeId: "+schemeId+" LocationId: "+locationId+" ProductId: "+productId+" Error: "+e);
			e.printStackTrace();
		}
		return false;
	}

	public boolean insertScheemLocPremDtls(String quoteNo, String applicationNo, String productId, String schemeId,
			String locationId, double basePremium, double optionalPremium, double totalPremium,
			String minimumPremiumFlag, double appliedPremium, String remarks) {
		try{
			String query = "INSERT INTO OFS_SCHEME_DETAILS(QUOTE_NO, APPLICATION_NO, PRODUCT_ID, SCHEME_ID, LOCATION_ID,"
					+ " BASE_PREMIUM, OPTIONAL_PREMIUM, PREMIUM_CALCULATED, MINIMUM_PREMIUM_YN,"
					+ " PREMIUM_APPLIED, ENTRY_DATE, STATUS, REMARKS,VOLUME_DISCOUNT_PERCENT, CORPORATE_DISCOUNT_PERCENT,"
					+ " SPECIAL_DISCOUNT_PERCENT, VOLUME_DISCOUNT_AMOUNT, CORPORATE_DISCOUNT_AMOUNT, SPECIAL_DISCOUNT_AMOUNT)"
					+ " VALUES (?,?,?,?,?,?,?,?,?,?,SYSDATE,'Y',?,0,0,0,0,0,0)";
			Object[] args = new Object[]{quoteNo, applicationNo, productId, schemeId, locationId,
					basePremium, optionalPremium, totalPremium, minimumPremiumFlag, appliedPremium, remarks};
			LogManager.info("HomeDAO.insertScheemLocPremDtls() Query: "+queryFrammer(query, args));
			int count = this.mytemplate.update(query, args);
			LogManager.info("HomeDAO.insertScheemLocPremDtls() Count: "+count);
			return (count>0)?true:false;
		}catch(Exception e){
			 LogManager.info("Exception @ HomeDao.insertScheemLocPremDtls()"
				 		+ " QuoteNo: "+quoteNo+" SchemeId: "+schemeId+" ProductId: "+productId+" LocationId: "+locationId+" Error: "+e);
			e.printStackTrace();
		}
		return false;
	}

	public boolean deleteUnAvailableSchemes(HomeBean bean) {
		try{
			String query = "DELETE FROM OFS_SCHEME_DETAILS WHERE QUOTE_NO = ? AND PRODUCT_ID = ?"
					+ " AND SCHEME_ID NOT IN (SELECT DISTINCT SCHEME_ID FROM OFS_DATA_DETAILS WHERE QUOTE_NO = ? AND PRODUCT_ID = ?)";
			Object[] args = new Object[]{bean.getQuoteNo(), bean.getProductId(), bean.getQuoteNo(), bean.getProductId()};
			LogManager.info("HomeDAO.deleteUnAvailableSchemes() Query: "+queryFrammer(query, args));
			int count = this.mytemplate.update(query, args);
			LogManager.info("HomeDAO.deleteUnAvailableSchemes() Count: "+count);
			return true;
		}catch(Exception e){
			 LogManager.info("Exception @ HomeDao.deleteUnAvailableSchemes()"
				 		+ " QuoteNo: "+bean.getQuoteNo()+" ProductId: "+bean.getProductId()+" Error: "+e);
			e.printStackTrace();
		}
		return false;
	}

	public List<Map<String, Object>> parSchemeActiveCoveragesDetail(HomeBean bean) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		try{
			String query = "SELECT A.COVERAGES_ID, A.COVERAGES_TYPE, A.CONTROL_TYPE, A.COVERAGES_LIMIT, A.BASE_RATE,"
					+ " A.SUM_INSURED_LIMIT, A.MIN_SUM_INSURED, C.COVERAGES_NAME, A.SUM_INSURED_CONTROL_TYPE"
					+ " FROM OFS_COVERAGES_MASTER A, OFS_MASTER C WHERE A.PRODUCT_ID = ?"
					+ " AND A.CONTENT_TYPE_ID = 0 AND A.SCHEME_ID = ? AND A.AMEND_ID = (SELECT MAX(AMEND_ID)"
					+ " FROM OFS_COVERAGES_MASTER B WHERE A.PRODUCT_ID = B.PRODUCT_ID AND A.CONTENT_TYPE_ID = B.CONTENT_TYPE_ID"
					+ " AND A.SCHEME_ID = B.SCHEME_ID AND A.COVERAGES_ID = B.COVERAGES_ID)"
					+ " AND A.STATUS = 'Y' AND A.COVERAGES_ID = C.COVERAGES_ID AND A.STATUS = C.STATUS"
					+ " ORDER BY DISPLAY_ORDER ASC";
			Object[] args = new Object[]{bean.getProductId(), bean.getSchemeId()};
			LogManager.info("HomeDAO.parSchemeActiveCoveragesDetail() Query: "+queryFrammer(query, args));
			list = this.mytemplate.queryForList(query, args);
		}catch(Exception e){
			 LogManager.info("Exception @ HomeDao.parSchemeActiveCoveragesDetail()"
				 		+ " QuoteNo: "+bean.getQuoteNo()+" ProductId: "+bean.getProductId()
				 		+" SchemeId: "+bean.getSchemeId()+" Error: "+e);
			e.printStackTrace();
		}
		return list;
	}

	public List<Map<String, Object>> parSchemeActiveLocationsDetail(HomeBean bean) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		try{
			String query = getQuery("GET_SCHEME_LOCATION_SIZE");
			Object[] args = new Object[]{bean.getQuoteNo(), bean.getSchemeId(), bean.getQuoteNo(),bean.getQuoteNo()};
			LogManager.info("HomeDAO.parSchemeActiveLocationsDetail() Query: "+queryFrammer(query, args));
			list = this.mytemplate.queryForList(query, args);
		}catch(Exception e){
			 LogManager.info("Exception @ HomeDao.parSchemeActiveLocationsDetail()"
				 		+ " QuoteNo: "+bean.getQuoteNo()+" ProductId: "+bean.getProductId()
				 		+" SchemeId: "+bean.getSchemeId()+" Error: "+e);
			e.printStackTrace();
		}
		return list;
	}

	public Map<String, Object> validationSchLocDiscCalcDtls(HomeBean bean) {
		Map<String, Object> map = new HashMap<String, Object>();
		try{
			String query = "SELECT B.VOLUME_DISCOUNT_LIMIT, B.CORPORATE_DISCOUNT_LIMIT, B.SPECIAL_DISCOUNT_LIMIT, C.MINIMUM_PREMIUM_YN,"
					+ " B.MIN_PREMIUM_AMOUNT, C.PREMIUM_APPLIED  FROM HOME_POSITION_MASTER A, LOGIN_USER_DETAILS B, OFS_SCHEME_DETAILS C"
					+ " WHERE A.LOGIN_ID = B.LOGIN_ID AND A.QUOTE_NO = C.QUOTE_NO AND A.AMEND_ID = '0' AND A.QUOTE_NO = ?"
					+ " AND B.SCHEME_ID = C.SCHEME_ID AND B.SCHEME_ID = ? AND C.LOCATION_ID = ? AND C.STATUS = 'Y' AND B.STATUS = 'Y'"
					+ " AND C.PRODUCT_ID = B.PRODUCT_ID AND A.PRODUCT_ID = B.PRODUCT_ID AND A.PRODUCT_ID = ?";
			Object[] args = new Object[]{bean.getQuoteNo(), bean.getDropDownScheme(), bean.getDropDownLocation(), bean.getProductId()};
			LogManager.info("HomeDAO.validationSchLocDiscCalcDtls() Query: "+queryFrammer(query, args));
			List<Map<String, Object>> list = this.mytemplate.queryForList(query, args);
			if(list!=null && list.size()>0){
				map = list.get(0);
			}
			
		}catch(Exception e){
			LogManager.info("Exception @ HomeDao.validationSchLocDiscCalcDtls()"
					+ " QuoteNo: "+bean.getQuoteNo()+" ApplicationNo: "+bean.getApplicationNo()+" ProductId: "+bean.getProductId()+""
					+ " SchemeId: "+bean.getDropDownScheme()+" Location: "+bean.getDropDownLocation()+" Error: "+e);
			e.printStackTrace();
		}
		return map;
	}

	public boolean updateSchLocPremCalc(double volDiscPercIns, double volDiscAmtIns, double corpDiscPercIns,
			double corpDiscAmtIns, double splDiscPercIns, double splDiscAmtIns, double premiumAppliedIns,
			String minPremiumYnIns, String productId, String quoteNo, String dropDownScheme, String dropDownLocation) {
		try{
			String query = "UPDATE OFS_SCHEME_DETAILS SET VOLUME_DISCOUNT_PERCENT = ?, VOLUME_DISCOUNT_AMOUNT = ?,"
					+ " CORPORATE_DISCOUNT_PERCENT = ?, CORPORATE_DISCOUNT_AMOUNT = ?, SPECIAL_DISCOUNT_PERCENT = ?,"
					+ " SPECIAL_DISCOUNT_AMOUNT = ?, PREMIUM_APPLIED = ?, MINIMUM_PREMIUM_YN = ?"
					+ " WHERE PRODUCT_ID = ? AND QUOTE_NO = ? AND SCHEME_ID = ? AND LOCATION_ID = ?";
			Object[] args = new Object[]{volDiscPercIns, volDiscAmtIns, corpDiscPercIns, corpDiscAmtIns, splDiscPercIns, splDiscAmtIns,
					premiumAppliedIns, minPremiumYnIns, productId, quoteNo, dropDownScheme, dropDownLocation};
			LogManager.info("HomeDAO.updateSchLocPremCalc() Query: "+queryFrammer(query, args));
			int count = this.mytemplate.update(query, args);
			return (count>0)?true:false;
		}catch(Exception e){
			LogManager.info("Exception @ HomeDao.updateSchLocPremCalc()"
					+ " QuoteNo: "+quoteNo+" ProductId: "+productId
					+ " SchemeId: "+dropDownScheme+" Location: "+dropDownLocation+" Error: "+e);
			e.printStackTrace();
		}
		return false;
	}

	public void getQuoteInfoDtl(HomeBean bean) {
		LogManager.info("getQuoteInfo - Enter ");
		String result="";
		try {
			String sql=this.getQuery("GET_PAYMENT_QUOTE_INFO");
			//obj=new Object[]{bean.getApplicationNo(),bean.getProductId(),bean.getBranchCode(),bean.getBranchCode(),bean.getBranchCode(),bean.getBranchCode(),bean.getBranchCode(),bean.getBranchCode()};
			Object[] obj=new Object[]{StringUtils.isBlank(bean.getQuoteNo())?"":bean.getQuoteNo(),bean.getProductId(),bean.getBranchCode()};
			LogManager.info("Sql=>=>"+queryFrammer(sql, obj));
			List list=this.mytemplate.queryForList(sql,obj);
			if(list!=null&&list.size()>0)
			{
				Map map=(Map)list.get(0);
				bean.setProduct(map.get("PRODUCT_NAME")==null?"":map.get("PRODUCT_NAME").toString());
				bean.setModeOfPay(map.get("PAYMENT_MODE")==null?"":map.get("PAYMENT_MODE").toString());
				bean.setCurrencyType(map.get("CURRENCY_TYPE")==null? "ZMW":map.get("CURRENCY_TYPE").toString());
				bean.setMobileNo(map.get("MOBILE")==null?"":map.get("MOBILE").toString());
				bean.setEmail(map.get("EMAIL")==null?"":map.get("EMAIL").toString());
				bean.setTotalPremium(map.get("OVERALL_PREMIUM")==null?"":map.get("OVERALL_PREMIUM").toString());
				//bean.setInsIntialAmount(map.get("OVERALL_PREMIUM")==null?"":map.get("OVERALL_PREMIUM").toString());
				
				String policyNo = map.get("POLICY_NO")==null?"":map.get("POLICY_NO").toString();
				if(StringUtils.isNotBlank(policyNo)){
					bean.setPolicyNo(policyNo);
				}
				
				bean.setQuoteNo(map.get("QUOTE_NO")==null?"":map.get("QUOTE_NO").toString());
				bean.setApplicationNo(map.get("APPLICATION_NO")==null?"":map.get("APPLICATION_NO").toString());
				bean.setCustomerId(map.get("CUSTOMER_ID")==null?"":map.get("CUSTOMER_ID").toString());
				bean.setBrokerCode(map.get("BROKER_CODE")==null?"":map.get("BROKER_CODE").toString());
				bean.setExecutive(map.get("AC_EXECUTIVE_ID")==null?"":map.get("AC_EXECUTIVE_ID").toString());
				
				bean.setTitle(map.get("TITLE")==null?"":map.get("TITLE").toString());
				bean.setCustomerName(map.get("FIRST_NAME")==null?"":map.get("FIRST_NAME").toString());
				bean.setCustLastName(map.get("LAST_NAME")==null?"":map.get("LAST_NAME").toString());
				bean.setCustomerType(map.get("CUSTOMER_TYPE")==null?"":map.get("CUSTOMER_TYPE").toString());
				bean.setQuotationStatus(map.get("STATUS")==null?"":map.get("STATUS").toString());
			}
			
		}catch(Exception e)
		{
			LogManager.debug(e);
		}
		LogManager.popRemove();
		LogManager.info("getQuoteInfo - Exit");}

}