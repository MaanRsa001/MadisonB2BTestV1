package com.maan.adminnew.Travel.DAO;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.maan.adminnew.Travel.DAO.TravelBean;
import com.maan.common.LogManager;
import com.maan.common.MyJdbcTemplate;

public class TravelDAO extends MyJdbcTemplate {
	public List<Object> getSchemeValue(TravelBean travelBean) {
		List<Object> schemeValue=null;		
		LogManager.push("getSchemeValue - Enter");
		try{
			String sql=getQuery("GET_SCHEME_VALUE");
			LogManager.info("SQL=>"+sql);
			schemeValue=this.mytemplate.queryForList(sql,new String[]{travelBean.getBranchCode()});			
		}
		catch (Exception e) {			
			LogManager.debug("EXCEPTION @ { " + e + " }");
		}		
		LogManager.info("getSchemeValue - Exit || Result: " + schemeValue.size());
		return schemeValue;
	}
	public List<Object> getOptionValue(TravelBean travelBean) {
		List<Object> optionvalue=null;		
		LogManager.push("getOptionValue - Enter");
		try{
			String sql=getQuery("GET_OPTION_VALUE");
			LogManager.info("SQL=>"+sql);
			optionvalue=this.mytemplate.queryForList(sql,new String[]{travelBean.getBranchCode()});			
		}
		catch (Exception e) {			
			LogManager.debug("EXCEPTION @ { " + e + " }");
		}		
		LogManager.info("getOptionValue - Exit || Result: " + optionvalue.size());
		return optionvalue;
	}
	public List<Object> getCoverValue(TravelBean travelBean) {
		List<Object> coverValue=null;		
		LogManager.push("getCoverValue - Enter");
		try{
			String sql=getQuery("GET_COVER_VALUE");
			LogManager.info("SQL=>"+sql);
			coverValue=this.mytemplate.queryForList(sql,new String[]{travelBean.getBranchCode(),travelBean.getSchemeId(),travelBean.getProductId(),travelBean.getBranchCode()});			
		}
		catch (Exception e) {			
			LogManager.debug("EXCEPTION @ { " + e + " }");
		}		
		LogManager.info("getCoverValue - Exit || Result: " + coverValue.size());
		return coverValue;
	}
	public List<Object> getScheme(TravelBean travelBean) {
		List<Object> scheme=null;		
		LogManager.push("getScheme - Enter");
		try{
			String sql=getQuery("GET_SCHEME");
			LogManager.info("SQL=>"+sql);
			scheme=this.mytemplate.queryForList(sql,new String[]{travelBean.getBranchCode()});			
		}
		catch (Exception e) {			
			LogManager.debug("EXCEPTION @ { " + e + " }");
		}		
		LogManager.info("getScheme - Exit || Result: " + scheme.size());
		return scheme;
	}
	public List<Object> getSchemeHistory(TravelBean travelBean) {
		List<Object> scheme=null;		
		LogManager.push("getSchemeHistory - Enter");
		try{
			String sql=getQuery("GET_SCHEME_HISTORY");
			LogManager.info("SQL=>"+sql);
			scheme=this.mytemplate.queryForList(sql,new String[]{travelBean.getSchemeId(),travelBean.getBranchCode()});			
		}
		catch (Exception e) {			
			LogManager.debug("EXCEPTION @ { " + e + " }");
		}		
		LogManager.info("getSchemeHistory - Exit || Result: " + scheme.size());
		return scheme;
	}
	public String editScheme(TravelBean travelBean) {
		LogManager.push("editScheme - Enter");
		String result="";
		
		try{
			String sql=getQuery("GET_SCHEME_DATA");
			LogManager.info("SQL=>"+sql);
			Object obj[] = new Object[]{travelBean.getSchemeId(),travelBean.getBranchCode()};
			Map map=this.mytemplate.queryForMap(sql,obj);
			if(map!=null&& map.size()>0)
			{	
				travelBean.setSchemeId(map.get("SCHEME_ID")==null?"":map.get("SCHEME_ID").toString());
				travelBean.setSchemeName(map.get("SCHEME_NAME")==null?"":map.get("SCHEME_NAME").toString());
				travelBean.setSchemeCode(map.get("COREAPP_CODE")==null?"":map.get("COREAPP_CODE").toString());
				travelBean.setSchemeDate(map.get("EFFECTIVE_DATE")==null?"":map.get("EFFECTIVE_DATE").toString());
				//travelBean.setSchemeProduct(map.get("PRODUCT_CODE")==null?"":map.get("PRODUCT_CODE").toString());
				travelBean.setOptionProduct(map.get("PRODUCT_CODE")==null?"":map.get("PRODUCT_CODE").toString());
				travelBean.setSchemeStatus(map.get("STATUS")==null?"":map.get("STATUS").toString());
			}
		}
		catch (Exception e) {			
			LogManager.debug("EXCEPTION @ { " + e + " }");
		}		
		LogManager.info("editScheme - Exit || Result: ");
		return result;
	}
	public String insertScheme(TravelBean travelBean) {
		LogManager.push("insertScheme - Enter");
		String sql,result="false";
		int res=0,amendId=0,schemeId=0;
		List<Map<String,Object>> amendid=null;
		Object[] obj=null;
		try{
			/*if("update".equalsIgnoreCase(travelBean.getDisplay())){
				diffInDate = diffInDays(bean.getEffectiveDate(),"");
			}*/
			/*if("update".equalsIgnoreCase(travelBean.getDisplay())){
				sql=getQuery("GET_SCHEME_CORE_CODE1");
				obj=new Object[2];					  
				obj[0]=travelBean.getSchemeCode();
				obj[1]=travelBean.getSchemeId();
				res=this.mytemplate.queryForInt(sql,obj);	
			}
			else{
			sql=getQuery("GET_SCHEME_CORE_CODE");
			obj=new Object[1];					  
			obj[0]=travelBean.getSchemeCode();
			res=this.mytemplate.queryForInt(sql,obj);
			}
			if(res==0){*/
			if("update".equalsIgnoreCase(travelBean.getDisplay())){
				sql=getQuery("GET_SCHEME_AMEND_ID");
				obj=new Object[2];
				obj[0]=travelBean.getSchemeId();
				obj[1]=travelBean.getBranchCode();
				LogManager.info("Query=>"+sql);
				amendid=this.mytemplate.queryForList(sql,obj);
				if(amendid != null){
					for(int i=0;i<amendid.size();i++){
					Map map = (Map)amendid.get(i);
					travelBean.setAmendId(map.get("AMEND_ID")== null?"":map.get("AMEND_ID").toString());
					}
				}
				LogManager.info("AMENDID=>"+amendId);
				if(travelBean.getAmendId()!=null){
					sql=getQuery("UPDATE_SCHEME_DATA");
					this.mytemplate.update(sql,new Object[]{travelBean.getSchemeName(),travelBean.getSchemeStatus(),travelBean.getSchemeCode(),travelBean.getProductId(),travelBean.getSchemeDate(),travelBean.getAmendId(),travelBean.getSchemeId(),travelBean.getBranchCode()});
				}
				else{
					sql=getQuery("GET_SCHEME_MAX_AMEND_ID");
					obj=new Object[2];
					obj[0]=travelBean.getSchemeId();
					obj[1]=travelBean.getBranchCode();
					amendId=this.mytemplate.queryForInt(sql,obj);
					/*sql=getQuery("UPDATE_SCHEME_DATA");
					this.mytemplate.update(sql,new Object[]{travelBean.getSchemeName(),travelBean.getBranchCode(),travelBean.getSchemeStatus(),travelBean.getSchemeCode(),travelBean.getProductId(),travelBean.getSchemeDate(),amendId,travelBean.getSchemeId()});
						travelBean.setAmendId(travelBean.getAmendId()+1);*/
					sql=getQuery("INS_SCHEME_DTLS");
					this.mytemplate.update(sql,new Object[]{travelBean.getSchemeId(),travelBean.getSchemeName(),travelBean.getBranchCode(),travelBean.getSchemeStatus(),travelBean.getSchemeCode(),travelBean.getProductId(),travelBean.getSchemeDate(),amendId});
				}
				LogManager.info("args> "+StringUtils.join(new Object[]{travelBean.getSchemeId(),travelBean.getSchemeName(),travelBean.getBranchCode(),travelBean.getSchemeStatus(),travelBean.getSchemeCode(),travelBean.getProductId(),travelBean.getSchemeDate(),amendId}));
				travelBean.setDisplay("edit");
				result="success";
			}else{
				sql=getQuery("GET_SCHEME_ID");
				obj=new Object[1];
				obj[0]=travelBean.getBranchCode();
				schemeId=this.mytemplate.queryForInt(sql,obj);
				LogManager.info("SCHEME_ID=>"+schemeId);
				sql=getQuery("INS_SCHEME_DTLS");
				LogManager.info("args> "+StringUtils.join(new Object[]{schemeId,travelBean.getSchemeName(),travelBean.getBranchCode(),travelBean.getSchemeStatus(),travelBean.getSchemeCode(),travelBean.getProductId(),travelBean.getSchemeDate(),amendId},","));
				this.mytemplate.update(sql,new Object[]{schemeId,travelBean.getSchemeName(),travelBean.getBranchCode(),travelBean.getSchemeStatus(),travelBean.getSchemeCode(),travelBean.getProductId(),travelBean.getSchemeDate(),amendId});
				travelBean.setDisplay("new");
				result="success";
			}
			//}
		}
		catch (Exception e) {			
			LogManager.debug("EXCEPTION @ { " + e + " }");
		}		
		LogManager.info("insertScheme - Exit || Result: ");
		return result;
	}

	public List<Object> getOption(TravelBean travelBean) {
		List<Object> option=null;		
		LogManager.push("getOption - Enter");
		try{
			String sql=getQuery("GET_OPTION");
			LogManager.info("SQL=>"+sql);
			option=this.mytemplate.queryForList(sql,new String[]{travelBean.getBranchCode()});			
		}
		catch (Exception e) {			
			LogManager.debug("EXCEPTION @ { " + e + " }");
		}		
		LogManager.info("getOption - Exit || Result: " + option.size());
		return option;
	}
	public List<Object> getOptionHistory(TravelBean travelBean) {
		List<Object> option=null;		
		LogManager.push("getOptionHistory - Enter");
		try{
			String sql=getQuery("GET_OPTION_HISTORY");
			LogManager.info("SQL=>"+sql);
			option=this.mytemplate.queryForList(sql,new String[]{travelBean.getOptionId(),travelBean.getBranchCode()});			
		}
		catch (Exception e) {			
			LogManager.debug("EXCEPTION @ { " + e + " }");
		}		
		LogManager.info("getOptionHistory - Exit || Result: " + option.size());
		return option;
	}
	
	public String editOption(TravelBean travelBean) {
		LogManager.push("editOption - Enter");
		String result="";
		try{
			String sql=getQuery("GET_OPTION_DATA");
			LogManager.info("SQL=>"+sql);
			Object obj[] = new Object[]{travelBean.getOptionId(),travelBean.getBranchCode()};
			Map map=this.mytemplate.queryForMap(sql,obj);
			if(map!=null&& map.size()>0)
			{	
				travelBean.setOptionId(map.get("OPTION_ID")==null?"":map.get("OPTION_ID").toString());
				travelBean.setOptionName(map.get("OPTION_NAME")==null?"":map.get("OPTION_NAME").toString());
				travelBean.setOptionCode(map.get("COREAPP_CODE")==null?"":map.get("COREAPP_CODE").toString());
				travelBean.setOptionDate(map.get("EFFECTIVE_DATE")==null?"":map.get("EFFECTIVE_DATE").toString());
				travelBean.setOptionProduct(map.get("PRODUCT_CODE")==null?"":map.get("PRODUCT_CODE").toString());
				travelBean.setOptionStatus(map.get("STATUS")==null?"":map.get("STATUS").toString());
			}
		}
		catch (Exception e) {			
			LogManager.debug("EXCEPTION @ { " + e + " }");
		}		
		LogManager.info("editOption - Exit || Result: ");
		return result;
	}
	public String insertOption(TravelBean travelBean) {
		LogManager.push("insertOption - Enter");
		String sql,result="false";
		int res=0,amendId=0,optionId=0;
		List<Map<String,Object>> amendid=null;
		Object[] obj=null;
		try{
			/*if("update".equalsIgnoreCase(travelBean.getDisplay())){
				sql=getQuery("GET_OPTION_CORE_CODE1");
				obj=new Object[2];					  
				obj[0]=travelBean.getOptionCode();
				obj[1]=travelBean.getOptionId();
				res=this.mytemplate.queryForInt(sql,obj);	
			}
			else{
				sql=getQuery("GET_OPTION_CORE_CODE");
				obj=new Object[1];					  
				obj[0]=travelBean.getOptionCode();
				res=this.mytemplate.queryForInt(sql,obj);
			}
				if(res==0){*/
				if("update".equalsIgnoreCase(travelBean.getDisplay())){
					/*sql=getQuery("GET_OPTION_AMEND_ID");
					obj=new Object[2];
					obj[0]=travelBean.getOptionId();
					obj[1]=travelBean.getBranchCode();
					LogManager.info("Query=>"+sql);
					amendId=this.mytemplate.queryForInt(sql,obj);
					LogManager.info("AMENDID=>"+amendId);
					amendId=amendId+1;*/
					sql=getQuery("GET_OPTION_AMEND_ID1");
					obj=new Object[2];
					obj[0]=travelBean.getOptionId();
					obj[1]=travelBean.getBranchCode();
					amendid=this.mytemplate.queryForList(sql,obj);
					if(amendid != null){
						for(int i=0;i<amendid.size();i++){
						Map map = (Map)amendid.get(i);
						travelBean.setAmendId(map.get("AMEND_ID")== null?"":map.get("AMEND_ID").toString());
						}
					}
					LogManager.info("AMENDID=>"+amendId);
					if(travelBean.getAmendId()!=null){
						sql=getQuery("UPDATE_OPTION_DTLS");
						this.mytemplate.update(sql,new Object[]{travelBean.getOptionName(),travelBean.getOptionStatus(),travelBean.getOptionCode(),travelBean.getProductId(),travelBean.getOptionDate(),travelBean.getAmendId(),travelBean.getOptionId(),travelBean.getBranchCode()});
					}
					else{
						sql=getQuery("GET_OPTION_AMEND_ID");
						obj=new Object[2];
						obj[0]=travelBean.getOptionId();
						obj[1]=travelBean.getBranchCode();
						amendId=this.mytemplate.queryForInt(sql,obj);
					sql=getQuery("INS_OPTION_DTLS");
					this.mytemplate.update(sql,new Object[]{travelBean.getOptionId(),travelBean.getOptionName(),travelBean.getBranchCode(),travelBean.getOptionStatus(),travelBean.getOptionCode(),travelBean.getProductId(),travelBean.getOptionDate(),amendId});
					}
					result="success";
					travelBean.setDisplay("edit");
				}else{
					sql=getQuery("GET_OPTION_ID");
					obj=new Object[1];
					obj[0]=travelBean.getBranchCode();
					optionId=this.mytemplate.queryForInt(sql,obj);
					LogManager.info("OPTION_ID=>"+optionId);
					sql=getQuery("INS_OPTION_DTLS");
					this.mytemplate.update(sql,new Object[]{optionId,travelBean.getOptionName(),travelBean.getBranchCode(),travelBean.getOptionStatus(),travelBean.getOptionCode(),travelBean.getProductId(),travelBean.getOptionDate(),amendId});
					result="success";
					travelBean.setDisplay("new");
				}
				//}
		}
		catch (Exception e) {			
			LogManager.debug("EXCEPTION @ { " + e + " }");
		}		
		LogManager.info("insertOption - Exit || Result: ");
		return result;
	}
	
	public List<Object> getCoverage(TravelBean travelBean) {
		List<Object> cover=null;		
		LogManager.push("getCoverage - Enter");
		try{
			String sql=getQuery("GET_MASTER_COVERAGE");
			LogManager.info("SQL=>"+sql);
			cover=this.mytemplate.queryForList(sql,new String[]{travelBean.getBranchCode()});			
		   }
		catch (Exception e) {			
			LogManager.debug("EXCEPTION @ { " + e + " }");
		}		
		LogManager.info("getCoverage - Exit || Result: " + cover.size());
		return cover;
	}
	public List<Object> getCoverageHistory(TravelBean travelBean) {
		List<Object> cover=null;		
		LogManager.push("getCoverageHistory - Enter");
		try{
			String sql=getQuery("GET_COVERAGE_HISTORY");
			LogManager.info("SQL=>"+sql);
			cover=this.mytemplate.queryForList(sql,new String[]{travelBean.getCoverageId(),travelBean.getBranchCode()});			
		}
		catch (Exception e) {			
			LogManager.debug("EXCEPTION @ { " + e + " }");
		}		
		LogManager.info("getCoverageHistory - Exit || Result: " + cover.size());
		return cover;
	}
	public String editCoverage(TravelBean travelBean) {
		LogManager.push("editCoverage - Enter");
		String result="";
		try{
			String sql=getQuery("GET_COVERAGE_DATA");
			LogManager.info("SQL=>"+sql);
			Object obj[] = new Object[]{travelBean.getCoverageId(),travelBean.getBranchCode()};
			Map map=this.mytemplate.queryForMap(sql,obj);
			if(map!=null&& map.size()>0)
			{	
				travelBean.setCoverageId(map.get("COVERAGES_ID")==null?"":map.get("COVERAGES_ID").toString());
				travelBean.setCoverageName(map.get("COVERAGES_NAME")==null?"":map.get("COVERAGES_NAME").toString());
				travelBean.setCoverageValue(map.get("COVERAGES_VALUE")==null?"":map.get("COVERAGES_VALUE").toString());
				travelBean.setCoverageCode(map.get("COREAPP_CODE")==null?"":map.get("COREAPP_CODE").toString());
				travelBean.setCoverageDate(map.get("EFFECTIVE_DATE")==null?"":map.get("EFFECTIVE_DATE").toString());
				travelBean.setCoverageProduct(map.get("PRODUCT_CODE")==null?"":map.get("PRODUCT_CODE").toString());
				travelBean.setCoverageStatus(map.get("STATUS")==null?"":map.get("STATUS").toString());
			}
		}
		catch (Exception e) {			
			LogManager.debug("EXCEPTION @ { " + e + " }");
		}		
		LogManager.info("editCoverage - Exit || Result: ");
		return result;
	}
	public String insertCoverage(TravelBean travelBean) {
		LogManager.push("insertCoverage - Enter");
		String sql,result="false";
		int res=0,amendId=0,coverageId=0;
		Object[] obj=null;
		List<Map<String,Object>> amendid=null;
		try{
			/*if("update".equalsIgnoreCase(travelBean.getDisplay())){
				sql=getQuery("GET_COVERAGE_CORE_CODE1");
				obj=new Object[2];					  
				obj[0]=travelBean.getCoverageCode();
				obj[1]=travelBean.getCoverageId();
				res=this.mytemplate.queryForInt(sql,obj);	
			}
			else{
				sql=getQuery("GET_COVERAGE_CORE_CODE");
				obj=new Object[1];					  
				obj[0]=travelBean.getCoverageCode();
				res=this.mytemplate.queryForInt(sql,obj);
			}
			if(res==0){*/
				if("update".equalsIgnoreCase(travelBean.getDisplay())){
					/*sql=getQuery("GET_COVERAGE_AMEND_ID");
					obj=new Object[2];
					obj[0]=travelBean.getCoverageId();
					obj[1]=travelBean.getBranchCode();
					LogManager.info("Query=>"+sql);
					amendId=this.mytemplate.queryForInt(sql,obj);
					LogManager.info("AMENDID=>"+amendId);
					amendId=amendId+1;*/
					sql=getQuery("GET_COVERAGE_AMEND_ID1");
					obj=new Object[2];
					obj[0]=travelBean.getCoverageId();
					obj[1]=travelBean.getBranchCode();
					LogManager.info("Query=>"+sql);
					amendid=this.mytemplate.queryForList(sql,obj);
					if(amendid != null){
						for(int i=0;i<amendid.size();i++){
						Map map = (Map)amendid.get(i);
						travelBean.setAmendId(map.get("AMEND_ID")== null?"":map.get("AMEND_ID").toString());
						}
					}
					LogManager.info("AMENDID=>"+amendId);
					if(travelBean.getAmendId()!=null){
						sql=getQuery("UPDATE_COVERAGE_DTLS");
						this.mytemplate.update(sql,new Object[]{travelBean.getCoverageName(),travelBean.getCoverageValue(),travelBean.getCoverageStatus(),travelBean.getCoverageCode(),travelBean.getProductId(),travelBean.getCoverageDate(),travelBean.getAmendId(),travelBean.getCoverageId(),travelBean.getBranchCode()});
					}
					else{
						sql=getQuery("GET_COVERAGE_AMEND_ID");
						obj=new Object[2];
						obj[0]=travelBean.getCoverageId();
						obj[1]=travelBean.getBranchCode();
						LogManager.info("Query=>"+sql);
						amendId=this.mytemplate.queryForInt(sql,obj);
						LogManager.info("AMENDID=>"+amendId);
					sql=getQuery("INS_COVERAGE_DTLS");
					this.mytemplate.update(sql,new Object[]{travelBean.getCoverageId(),travelBean.getCoverageName(),travelBean.getCoverageValue(),travelBean.getBranchCode(),travelBean.getCoverageStatus(),travelBean.getCoverageCode(),travelBean.getProductId(),travelBean.getCoverageDate(),amendId});
					}
					result="success";
					travelBean.setDisplay("edit");
				}else{
					sql=getQuery("GET_COVERAGE_ID");
					obj=new Object[1];
					obj[0]=travelBean.getBranchCode();
					coverageId=this.mytemplate.queryForInt(sql,obj);
					LogManager.info("COVERAGE_ID=>"+coverageId);
					sql=getQuery("INS_COVERAGE_DTLS");
					this.mytemplate.update(sql,new Object[]{coverageId,travelBean.getCoverageName(),travelBean.getCoverageValue(),travelBean.getBranchCode(),travelBean.getCoverageStatus(),travelBean.getCoverageCode(),travelBean.getProductId(),travelBean.getCoverageDate(),amendId});
					result="success";
					travelBean.setDisplay("new");
				}
				//}
		}
		catch (Exception e) {			
			LogManager.debug("EXCEPTION @ { " + e + " }");
		}		
		LogManager.info("insertCoverage - Exit || Result: ");
		return result;
	}
	
	//DISCOUNT RATE BLOCK
	
	public List<Object> getDiscountRate(TravelBean travelBean) {
		List<Object> discountrate=null;		
		LogManager.push("getDiscountRate - Enter");
		try{
			String sql=getQuery("GET_DISCOUNT_RATE");
			LogManager.info("SQL=>"+sql);
			discountrate=this.mytemplate.queryForList(sql,new String[]{travelBean.getProductId(),travelBean.getBranchCode(),travelBean.getProductId(),travelBean.getBranchCode()});			
		}
		catch (Exception e) {			
			LogManager.debug("EXCEPTION @ { " + e + " }");
		}		
		LogManager.info("getDiscountRate - Exit || Result: " + discountrate.size());
		return discountrate;
	}
	public List<Object> getDiscountRateHistory(TravelBean travelBean) {
		List<Object> discountrate=null;		
		LogManager.push("getDiscountRateHistory - Enter");
		try{
			String sql=getQuery("GET_DISCOUNT_RATE_HISTORY");
			LogManager.info("SQL=>"+sql);
			discountrate=this.mytemplate.queryForList(sql,new String[]{travelBean.getProductId(), travelBean.getDrateId(),travelBean.getBranchCode()});			
		}
		catch (Exception e) {			
			LogManager.debug("EXCEPTION @ { " + e + " }");
		}		
		LogManager.info("getDiscountRateHistory - Exit || Result: " + discountrate.size());
		return discountrate;
	}
	public String editDiscountRate(TravelBean travelBean) {
		LogManager.push("editDiscountRate - Enter");
		String result="";
		try{
			String sql=getQuery("GET_DISCOUNT_RATE_DATA");
			LogManager.info("SQL=>"+sql);
			//Object obj[] = new Object[]{travelBean.getProductId(),travelBean.getDrateId(),travelBean.getBranchCode()};
			Object obj[] = new Object[]{travelBean.getProductId(),travelBean.getDisId(),travelBean.getBranchCode()};
			Map map=this.mytemplate.queryForMap(sql,obj);
			if(map!=null&& map.size()>0)
			{	 
				/*travelBean.setDrateId(map.get("RATE_ID")==null?"":map.get("RATE_ID").toString());
				travelBean.setTypeList(map.get("TYPE")==null?"":map.get("TYPE").toString());
				travelBean.setSageList(map.get("AGE_START")==null?"":map.get("AGE_START").toString());
				travelBean.setEageList(map.get("AGE_END")==null?"":map.get("AGE_END").toString());
				travelBean.setRateValue(map.get("RATE_VALUE")==null?"":map.get("RATE_VALUE").toString());
				travelBean.setDrateDate(map.get("EFFECTIVE_DATE")==null?"":map.get("EFFECTIVE_DATE").toString());
				travelBean.setDrateCode(map.get("COREAPP_CODE")==null?"":map.get("COREAPP_CODE").toString());
				travelBean.setDrateStatus(map.get("STATUS")==null?"":map.get("STATUS").toString());*/
				travelBean.setDisId(map.get("RATE_ID")==null?"":map.get("RATE_ID").toString());
				travelBean.setDisType(map.get("TYPE")==null?"":map.get("TYPE").toString());
				travelBean.setDisStart(map.get("AGE_START")==null?"":map.get("AGE_START").toString());
				travelBean.setDisEnd(map.get("AGE_END")==null?"":map.get("AGE_END").toString());
				travelBean.setDisRateValue(map.get("RATE_VALUE")==null?"":map.get("RATE_VALUE").toString());
				travelBean.setDisDate(map.get("EFFECTIVE_DATE")==null?"":map.get("EFFECTIVE_DATE").toString());
				travelBean.setDisCode(map.get("COREAPP_CODE")==null?"":map.get("COREAPP_CODE").toString());
				travelBean.setDisStatus(map.get("STATUS")==null?"":map.get("STATUS").toString());
			}
		}
		catch (Exception e) {			
			LogManager.debug("EXCEPTION @ { " + e + " }");
		}		
		LogManager.info("editDiscountRate - Exit || Result: ");
		return result;
	}
	public String insertDiscountRate(TravelBean travelBean) {
		LogManager.push("insertDiscountRate - Enter");
		String sql,result="false";
		int res=0,amendId=0,drateId=0;
		List<Map<String,Object>> amend=null;
		Object[] obj=null;
		try{
			/*if("update".equalsIgnoreCase(travelBean.getDisplay())){
				sql=getQuery("GET_DISCOUNT_RATE_CORE_CODE1");
				obj=new Object[2];					  
				obj[0]=travelBean.getDisCode();
				obj[1]=travelBean.getDisId();
				res=this.mytemplate.queryForInt(sql,obj);	
			}
			else{
				sql=getQuery("GET_DISCOUNT_RATE_CORE_CODE");
				obj=new Object[1];					  
				obj[0]=travelBean.getDisCode();
				res=this.mytemplate.queryForInt(sql,obj);
			}
			
			if(res==0){*/
			if("update".equalsIgnoreCase(travelBean.getDisplay())){
				sql=getQuery("GET_DISCOUNT_RATE_AMEND_ID");
				obj=new Object[2];
				obj[0]=travelBean.getDisId();
				obj[1]=travelBean.getBranchCode();
				LogManager.info("Query=>"+sql);
				amend=this.mytemplate.queryForList(sql,obj);
				if(amend != null){
					for(int i=0;i<amend.size();i++){
					Map map = (Map)amend.get(i);
					travelBean.setAmendId(map.get("AMEND_ID")== null?"":map.get("AMEND_ID").toString());
					}
				}
				LogManager.info("AMENDID=>"+amendId);
				//amendId=amendId+1;
				if(travelBean.getAmendId()!=null){
					sql=getQuery("UPDATE_NEW_DISCOUNT_RATE");
					this.mytemplate.update(sql,new Object[]{travelBean.getDisType(),travelBean.getDisStart(),travelBean.getDisEnd(),travelBean.getDisRateValue(),travelBean.getDisDate(),travelBean.getAmendId(),travelBean.getDisStatus(),travelBean.getDisCode(),travelBean.getProductId(),travelBean.getDisId(),travelBean.getBranchCode()});	
				}
				else{
					sql=getQuery("GET_DISCOUNT_RATE_MAX_AMEND_ID");
					obj=new Object[2];
					obj[0]=travelBean.getDisId();
					obj[1]=travelBean.getBranchCode();
					LogManager.info("Query=>"+sql);
					amendId=this.mytemplate.queryForInt(sql,obj);
					sql=getQuery("INS_DISCOUNT_RATE_DTLS");
					this.mytemplate.update(sql,new Object[]{travelBean.getDisId(),travelBean.getDisType(),travelBean.getDisStart(),travelBean.getDisEnd(),travelBean.getDisRateValue(),travelBean.getBranchCode(),travelBean.getDisDate(),amendId,travelBean.getDisStatus(),travelBean.getDisCode(),travelBean.getProductId()});	
				}
				travelBean.setDisplay("edit");
				result="success";
			}else{
				sql=getQuery("GET_DISCOUNT_RATE_ID");
				obj=new Object[1];
				obj[0]=travelBean.getBranchCode();
				drateId=this.mytemplate.queryForInt(sql,obj);
				LogManager.info("RATE_ID=>"+drateId);
				sql=getQuery("INS_DISCOUNT_RATE_DTLS");
				this.mytemplate.update(sql,new Object[]{drateId,travelBean.getDisType(),travelBean.getDisStart(),travelBean.getDisEnd(),travelBean.getDisRateValue(),travelBean.getBranchCode(),travelBean.getDisDate(),amendId,travelBean.getDisStatus(),travelBean.getDisCode(),travelBean.getProductId()});	
				travelBean.setDisplay("new");
				result="success";
			}
			//}
		}
		catch (Exception e) {			
			LogManager.debug("EXCEPTION @ { " + e + " }");
		}		
		LogManager.info("insertDiscountRate - Exit || Result: ");
		return result;
	}
	
	//RELATION DISCOUNT
	
	public List<Object> getRelationDiscount(TravelBean travelBean) {
		List<Object> relationdis=null;		
		LogManager.push("RelationDiscount - Enter");
		try{
			String sql=getQuery("GET_RELATION_DISCOUNT");
			LogManager.info("SQL=>"+sql);
			relationdis=this.mytemplate.queryForList(sql,new String[]{travelBean.getProductId(),travelBean.getBranchCode(),travelBean.getProductId(),travelBean.getBranchCode()});			
		}
		catch (Exception e) {			
			LogManager.debug("EXCEPTION @ { " + e + " }");
		}		
		LogManager.info("RelationDiscount - Exit || Result: " + relationdis.size());
		return relationdis;
	}
	public List<Object> getRelationDiscountHistory(TravelBean travelBean) {
		List<Object> relationdis=null;		
		LogManager.push("getRelationDiscountHistory - Enter");
		try{
			String sql=getQuery("GET_RELATION_DISCOUNT_HISTORY");
			LogManager.info("SQL=>"+sql);
			relationdis=this.mytemplate.queryForList(sql,new String[]{travelBean.getProductId(),travelBean.getDrateId(),travelBean.getBranchCode()});			
		}
		catch (Exception e) {			
			LogManager.debug("EXCEPTION @ { " + e + " }");
		}		
		LogManager.info("getRelationDiscountHistory - Exit || Result: " + relationdis.size());
		return relationdis;
	}
	public String editRelationDiscount(TravelBean travelBean) {
		LogManager.push("editRelationDiscount - Enter");
		String result="";
		try{
			String sql=getQuery("GET_RELATION_DISCOUNT_DATA");
			LogManager.info("SQL=>"+sql);
			Object obj[] = new Object[]{travelBean.getProductId(),travelBean.getDrateId(),travelBean.getBranchCode()};
			Map map=this.mytemplate.queryForMap(sql,obj);
			if(map!=null&& map.size()>0)
			{	 
				travelBean.setDrateId(map.get("DISCOUNT_ID")==null?"":map.get("DISCOUNT_ID").toString());
				travelBean.setSageList(map.get("DISCOUNT_START")==null?"":map.get("DISCOUNT_START").toString());
				travelBean.setEageList(map.get("DISCOUNT_END")==null?"":map.get("DISCOUNT_END").toString());
				travelBean.setRateValue(map.get("RATE_VALUE")==null?"":map.get("RATE_VALUE").toString());
				travelBean.setDisType(map.get("RELATION_TYPE")==null?"":map.get("RELATION_TYPE").toString());
				travelBean.setDrateDate(map.get("EFFECTIVE_DATE")==null?"":map.get("EFFECTIVE_DATE").toString());
				travelBean.setDrateCode(map.get("COREAPP_CODE")==null?"":map.get("COREAPP_CODE").toString());
				travelBean.setDrateStatus(map.get("STATUS")==null?"":map.get("STATUS").toString());
			}
		}
		catch (Exception e) {			
			LogManager.debug("EXCEPTION @ { " + e + " }");
		}		
		LogManager.info("editRelationDiscount - Exit || Result: ");
		return result;
	}
	public String insertRelationDiscount(TravelBean travelBean) {
		LogManager.push("insertRelationDiscount - Enter");
		String sql,result="false";
		int res=0,amendId=0,disId=0;
		List<Map<String,Object>> amend=null;
		Object[] obj=null;
		try{
			/*if("update".equalsIgnoreCase(travelBean.getDisplay())){
				sql=getQuery("GET_RELATION_DISCOUNT_CORE_CODE1");
				obj=new Object[2];					  
				obj[0]=travelBean.getDrateCode();
				obj[1]=travelBean.getDrateId();
				res=this.mytemplate.queryForInt(sql,obj);
			}
			else{
				sql=getQuery("GET_RELATION_DISCOUNT_CORE_CODE");
				obj=new Object[1];					  
				obj[0]=travelBean.getDrateCode();
				res=this.mytemplate.queryForInt(sql,obj);
			}
			
			
			if(res==0){*/
			if("update".equalsIgnoreCase(travelBean.getDisplay())){
				sql=getQuery("GET_RELATION_DISCOUNT_AMEND_ID");
				obj=new Object[2];
				obj[0]=travelBean.getDrateId();
				obj[1]=travelBean.getBranchCode();
				amend=this.mytemplate.queryForList(sql,obj);
				if(amend != null){
					for(int i=0;i<amend.size();i++){
					Map map = (Map)amend.get(i);
					travelBean.setAmendId(map.get("AMEND_ID")== null?"":map.get("AMEND_ID").toString());
					}
				}
				LogManager.info("AMENDID=>"+amendId);
				//amendId=amendId+1;
				if(travelBean.getAmendId()!=null){
					sql=getQuery("UPDATE_DISCOUNT_RELATION");
					this.mytemplate.update(sql,new Object[]{travelBean.getSageList(),travelBean.getEageList(),travelBean.getRateValue(),travelBean.getDisType(),travelBean.getDrateDate(),travelBean.getDrateCode(),travelBean.getAmendId(),travelBean.getDrateStatus(),travelBean.getProductId(),travelBean.getDrateId(),travelBean.getBranchCode()});
				}
				else{
				sql=getQuery("GET_RELATION_DISCOUNT_MAX_AMEND_ID");
				obj=new Object[2];
				obj[0]=travelBean.getDrateId();
				obj[1]=travelBean.getBranchCode();
				amendId=this.mytemplate.queryForInt(sql,obj);
				LogManager.info("AMENDID=>"+amendId);
				//amendId=amendId+1;
				sql=getQuery("INS_RELATION_DISCOUNT_DTLS");
				this.mytemplate.update(sql,new Object[]{travelBean.getDrateId(),travelBean.getSageList(),travelBean.getEageList(),travelBean.getRateValue(),travelBean.getDisType(),travelBean.getDrateDate(),travelBean.getDrateCode(),amendId,travelBean.getDrateStatus(),travelBean.getBranchCode(),travelBean.getProductId(),"0"});
				}
				travelBean.setDisplay("edit");
				result="success";
			}else{
				sql=getQuery("GET_RELATION_DISCOUNT_ID");
				obj=new Object[1];
				obj[0]=travelBean.getBranchCode();
				disId=this.mytemplate.queryForInt(sql,obj);
				LogManager.info("DISCOUNT_ID=>"+disId);
				sql=getQuery("INS_RELATION_DISCOUNT_DTLS");
				this.mytemplate.update(sql,new Object[]{disId,travelBean.getSageList(),travelBean.getEageList(),travelBean.getRateValue(),travelBean.getDisType(),travelBean.getDrateDate(),travelBean.getDrateCode(),amendId,travelBean.getDrateStatus(),travelBean.getBranchCode(),travelBean.getProductId(),"0"});
				travelBean.setDisplay("new");
				result="success";
			}
			//}
		}
		catch (Exception e) {			
			LogManager.debug("EXCEPTION @ { " + e + " }");
		}		
		LogManager.info("insertRelationDiscount - Exit || Result: ");
		return result;
	}
	
	//PREMIUM RATE
	public List<Object> getPremiumRate(TravelBean travelBean) {
		List<Object> premiumrate=null;		
		LogManager.push("getPremiumRate - Enter");
		try{
			String sql=getQuery("GET_PREMIUM_RATE");
			LogManager.info("SQL=>"+sql);
			premiumrate=this.mytemplate.queryForList(sql,new String[]{travelBean.getProductId(),travelBean.getBranchCode()});			
		}
		catch (Exception e) {			
			LogManager.debug("EXCEPTION @ { " + e + " }");
		}		
		LogManager.info("getPremiumRate - Exit || Result: " + premiumrate.size());
		return premiumrate;
	}
	public List<Object> getPremiumRateHistory(TravelBean travelBean) {
		List<Object> relationdis=null;		
		LogManager.push("getPremiumRateHistory - Enter");
		try{
			String sql=getQuery("GET_PREMIUM_RATE_HISTORY");
			LogManager.info("SQL=>"+sql);
			relationdis=this.mytemplate.queryForList(sql,new String[]{travelBean.getProductId(),travelBean.getDisId(),travelBean.getBranchCode()});			
		}
		catch (Exception e) {			
			LogManager.debug("EXCEPTION @ { " + e + " }");
		}		
		LogManager.info("getPremiumRateHistory - Exit || Result: " + relationdis.size());
		return relationdis;
	}
	public String editPremiumRate(TravelBean travelBean) {
		LogManager.push("editPremiumRate - Enter");
		String result="";
		try{
			String sql=getQuery("GET_PREMIUM_RATE_DATA");
			LogManager.info("SQL=>"+sql);
			Object obj[] = new Object[]{travelBean.getProductId(),travelBean.getDisId(),travelBean.getBranchCode()};
			Map map=this.mytemplate.queryForMap(sql,obj);
			if(map!=null&& map.size()>0)
			{	 
				travelBean.setSchemeName(map.get("SCHEME_ID")==null?"":map.get("SCHEME_ID").toString());
				travelBean.setOptionName(map.get("OPTION_ID")==null?"":map.get("OPTION_ID").toString());
				travelBean.setNoofDays(map.get("NO_OF_DAYS")==null?"":map.get("NO_OF_DAYS").toString());
				travelBean.setDisRateValue(map.get("PREMIUM")==null?"":map.get("PREMIUM").toString());
				travelBean.setDisDate(map.get("EFFECTIVE_DATE")==null?"":map.get("EFFECTIVE_DATE").toString());
				travelBean.setDisCode(map.get("COREAPPCODE")==null?"":map.get("COREAPPCODE").toString());
				travelBean.setDisStatus(map.get("STATUS")==null?"":map.get("STATUS").toString());
				travelBean.setDisId(map.get("TRAVEL_PREMIUM_ID")==null?"":map.get("TRAVEL_PREMIUM_ID").toString());
				/*travelBean.setDisId(map.get("DISCOUNT_ID")==null?"":map.get("DISCOUNT_ID").toString());
				travelBean.setDisStart(map.get("DISCOUNT_START")==null?"":map.get("DISCOUNT_START").toString());
				travelBean.setDisEnd(map.get("DISCOUNT_END")==null?"":map.get("DISCOUNT_END").toString());
				travelBean.setDisRateValue(map.get("RATE_VALUE")==null?"":map.get("RATE_VALUE").toString());
				travelBean.setDisType(map.get("RELATION_TYPE")==null?"":map.get("RELATION_TYPE").toString());
				travelBean.setDisDate(map.get("EFFECTIVE_DATE")==null?"":map.get("EFFECTIVE_DATE").toString());
				travelBean.setDisCode(map.get("COREAPP_CODE")==null?"":map.get("COREAPP_CODE").toString());
				travelBean.setDisStatus(map.get("STATUS")==null?"":map.get("STATUS").toString());*/
			
			}
		}
		catch (Exception e) {			
			LogManager.debug("EXCEPTION @ { " + e + " }");
		}		
		LogManager.info("editPremiumRate - Exit || Result: ");
		return result;
	}
	public String insertPremiumRate(TravelBean travelBean) {
		LogManager.push("insertPremiumRate - Enter");
		String sql,result="false";
		int res=0,amendId=0,disId=0;
		List<Map<String,Object>> amend=null;
		
		Object[] obj=null;
		try{
			/*if("update".equalsIgnoreCase(travelBean.getDisplay())){
				sql=getQuery("GET_PREMIUM_RATE_CORE_CODE1");
				obj=new Object[2];					  
				obj[0]=travelBean.getDisCode();
				obj[1]=travelBean.getDisId();
				res=this.mytemplate.queryForInt(sql,obj);
			}
			else{
				sql=getQuery("GET_PREMIUM_RATE_CORE_CODE");
				obj=new Object[1];					  
				obj[0]=travelBean.getDisCode();
				res=this.mytemplate.queryForInt(sql,obj);
			}			
			if(res==0){*/
			if("update".equalsIgnoreCase(travelBean.getDisplay())){
				/*sql=getQuery("GET_PREMIUM_RATE_AMEND_ID");
				obj=new Object[2];
				obj[0]=travelBean.getDisId();
				obj[1]=travelBean.getBranchCode();
				LogManager.info("Query=>"+sql);
				amendId=this.mytemplate.queryForInt(sql,obj);
				LogManager.info("AMENDID=>"+amendId);
				amendId=amendId+1;*/
				sql=getQuery("GET_PREMIUM_RATE_AMEND_ID1");
				obj=new Object[2];
				obj[0]=travelBean.getDisId();
				obj[1]=travelBean.getBranchCode();
				amend=this.mytemplate.queryForList(sql,obj);
				if(amend != null){
					for(int i=0;i<amend.size();i++){
					Map map = (Map)amend.get(i);
					travelBean.setAmendId(map.get("AMEND_ID")== null?"":map.get("AMEND_ID").toString());
					}
				}
				LogManager.info("AMENDID=>"+amendId);
				//amendId=amendId+1;
				if(travelBean.getAmendId()!=null){
					sql=getQuery("UPDATE_DISCOUNT_RATE");
					this.mytemplate.update(sql,new Object[]{travelBean.getNoofDays(),travelBean.getSchemeName(),travelBean.getOptionName(),travelBean.getDisDate(),travelBean.getDisRateValue(),travelBean.getProductId(),travelBean.getDisCode(),travelBean.getDisStatus(),travelBean.getDisId()});
				}
				else{
					sql=getQuery("GET_PREMIUM_RATE_AMEND_ID");
					obj=new Object[2];
					obj[0]=travelBean.getDisId();
					obj[1]=travelBean.getBranchCode();
					LogManager.info("Query=>"+sql);
					amendId=this.mytemplate.queryForInt(sql,obj);
					LogManager.info("AMENDID=>"+amendId);
					sql=getQuery("INS_PREMIUM_RATE_DTLS");
					this.mytemplate.update(sql,new Object[]{travelBean.getDisId(),travelBean.getNoofDays(),travelBean.getSchemeName(),travelBean.getOptionName(),travelBean.getDisDate(),travelBean.getDisRateValue(),travelBean.getBranchCode(),travelBean.getProductId(),amendId,travelBean.getDisStatus(),travelBean.getDisCode()});
				}
				travelBean.setDisplay("edit");
				result="success";
			}else{
				sql=getQuery("GET_PREMIUM_RATE_ID");
				obj=new Object[1];
				obj[0]=travelBean.getBranchCode();
				disId=this.mytemplate.queryForInt(sql,obj);
				LogManager.info("DISCOUNT_ID=>"+disId);
				sql=getQuery("INS_PREMIUM_RATE_DTLS");
				this.mytemplate.update(sql,new Object[]{disId,travelBean.getNoofDays(),travelBean.getSchemeName(),travelBean.getOptionName(),travelBean.getDisDate(),travelBean.getDisRateValue(),travelBean.getBranchCode(),travelBean.getProductId(),amendId,travelBean.getDisStatus(),travelBean.getDisCode()});
				travelBean.setDisplay("new");
				result="success";
			}
			//}
		}
		catch (Exception e) {			
			LogManager.debug("EXCEPTION @ { " + e + " }");
		}		
		LogManager.info("insertPremiumRate - Exit || Result: ");
		return result;
	}
	//LINK OPTION
	public List<Object> getLinkOption(TravelBean travelBean) {
		List<Object> linkoption=null;		
		LogManager.push("getLinkOption - Enter");
		try{
			String sql=getQuery("GET_LINK_OPTION");
			LogManager.info("SQL=>"+sql);
			linkoption=this.mytemplate.queryForList(sql,new String[]{travelBean.getProductId(),travelBean.getBranchCode()});			
		}
		catch (Exception e) {			
			LogManager.debug("EXCEPTION @ { " + e + " }");
		}		
		LogManager.info("getLinkOption - Exit || Result: " + linkoption.size());
		return linkoption;
	}
	public List<Object> getLinkOptionHistory(TravelBean travelBean) {
		List<Object> linkoption=null;		
		LogManager.push("getLinkOptionHistory - Enter");
		try{
			String sql=getQuery("GET_LINK_OPTION_HISTORY");
			LogManager.info("SQL=>"+sql);
			linkoption=this.mytemplate.queryForList(sql,new String[]{travelBean.getSchemeId(),travelBean.getBranchCode()});			
		}
		catch (Exception e) {			
			LogManager.debug("EXCEPTION @ { " + e + " }");
		}		
		LogManager.info("getLinkOptionHistory - Exit || Result: " + linkoption.size());
		return linkoption;
	}
	public String editLinkOption(TravelBean travelBean) {
		LogManager.push("editLinkOption - Enter");
		String result="";
		try{
			String sql=getQuery("GET_LINK_OPTION_DATA");
			LogManager.info("SQL=>"+sql);
			Object obj[] = new Object[]{travelBean.getSchemeId(),travelBean.getProductId(),travelBean.getBranchCode()};
			List list=this.mytemplate.queryForList(sql,obj);
			if(list!=null && list.size()>0)
			{
				Map map=(Map)list.get(0);
				travelBean.setSchemeId(map.get("SCHEME_ID")==null?"":map.get("SCHEME_ID").toString());
				travelBean.setOptionId(map.get("OPTION_ID")==null?"":map.get("OPTION_ID").toString());
				travelBean.setLinkOptionDate(map.get("EFFECTIVE_DATE")==null?"":map.get("EFFECTIVE_DATE").toString());
				travelBean.setLinkOptionCode(map.get("COREAPP_CODE")==null?"":map.get("COREAPP_CODE").toString());
				travelBean.setLinkOptionStatus(map.get("STATUS")==null?"":map.get("STATUS").toString());
			}
		}
		catch (Exception e) {			
			LogManager.debug("EXCEPTION @ { " + e + " }");
		}		
		LogManager.info("editLinkOption - Exit || Result: ");
		return result;
	}
	public String insertLinkOption(TravelBean travelBean) {
		LogManager.push("insertLinkOption - Enter");
		String sql,result="false";
		int res=0,amendId=0;
		Object[] obj=null;
		try{
			sql=getQuery("GET_LINK_OPTION_CORE_CODE");
			obj=new Object[1];					  
			obj[0]=travelBean.getLinkOptionCode();
			res=this.mytemplate.queryForInt(sql,obj);
			
			if(res==0){
				sql=getQuery("GET_LINK_OPTION_AMEND_ID");
				obj=new Object[2];
				obj[0]=travelBean.getSchemeId();
				obj[1]=travelBean.getBranchCode();
				LogManager.info("Query=>"+sql);
				amendId=this.mytemplate.queryForInt(sql,obj);
				LogManager.info("AMENDID=>"+amendId);
				amendId=amendId+1;
				sql=getQuery("INS_LINK_OPTION_DTLS");
				for(int i=0;i<travelBean.getLinkOption().length;i++)
				{
					this.mytemplate.update(sql,new Object[]{travelBean.getSchemeId(),travelBean.getLinkOption()[i],travelBean.getLinkOptionStatus(),travelBean.getBranchCode(),travelBean.getLinkOptionCode(),travelBean.getProductId(),travelBean.getLinkOptionDate(),amendId});
				}
				travelBean.setDisplay("");
				result="success";
			}
		}
		catch (Exception e) {			
			LogManager.debug("EXCEPTION @ { " + e + " }");
		}		
		LogManager.info("insertLinkOption - Exit || Result: ");
		return result;
	}
	//LINK COVERAGE
	public List<Object> getLinkCoverage(TravelBean travelBean) {
		List<Object> linkcoverage=null;		
		LogManager.push("getLinkCoverage - Enter");
		try{
			String sql=getQuery("GET_LINK_COVERAGE");
			LogManager.info("SQL=>"+sql);
			linkcoverage=this.mytemplate.queryForList(sql,new String[]{travelBean.getBranchCode(),travelBean.getProductId(),travelBean.getBranchCode()});			
		}
		catch (Exception e) {			
			LogManager.debug("EXCEPTION @ { " + e + " }");
		}		
		LogManager.info("getLinkCoverage - Exit || Result: " + linkcoverage.size());
		return linkcoverage;
	}
	public List<Object> getLinkCoverageHistory(TravelBean travelBean) {
		List<Object> linkcoverage=null;		
		LogManager.push("getLinkCoverageHistory - Enter");
		try{
			String sql=getQuery("GET_PREMIUM_RATE_HISTORY");
			LogManager.info("SQL=>"+sql);
			linkcoverage=this.mytemplate.queryForList(sql,new String[]{travelBean.getSchemeId(),travelBean.getBranchCode()});			
		}
		catch (Exception e) {			
			LogManager.debug("EXCEPTION @ { " + e + " }");
		}		
		LogManager.info("getLinkCoverageHistory - Exit || Result: " + linkcoverage.size());
		return linkcoverage;
	}
	public String editLinkCoverage(TravelBean travelBean) {
		LogManager.push("editLinkOption - Enter");
		String result="";
		try{
			String sql=getQuery("GET_LINK_COVERAGE_DATA");
			LogManager.info("SQL=>"+sql);
			Object obj[] = new Object[]{travelBean.getSchemeId(),travelBean.getOptionId(),travelBean.getProductId(),travelBean.getBranchCode(),travelBean.getBranchCode(),travelBean.getProductId()};
			List list=this.mytemplate.queryForList(sql,obj);
			if(list!=null && list.size()>0)
			{
				Map map=(Map)list.get(0);
				travelBean.setLinkCoverageId(map.get("COVERAGES_ID")==null?"":map.get("COVERAGES_ID").toString());
				travelBean.setLinkCoverageRate(map.get("RATE")==null?"":map.get("RATE").toString());
				travelBean.setLinkCoverageDate(map.get("EFF_DATE")==null?"":map.get("EFF_DATE").toString());
				travelBean.setLinkCoverageCode(map.get("CORE_APP_CODE")==null?"":map.get("CORE_APP_CODE").toString());
				travelBean.setLinkCoverageStatus(map.get("STATUS")==null?"":map.get("STATUS").toString());
			}
		}
		catch (Exception e) {			
			LogManager.debug("EXCEPTION @ { " + e + " }");
		}		
		LogManager.info("editLinkOption - Exit || Result: ");
		return result;
	}
	public String insertLinkCoverage(TravelBean travelBean) {
		LogManager.push("insertLinkCoverage - Enter");
		String sql,result="false";
		int res=0,amendId=0;
		Object[] obj=null;
		try{
			sql=getQuery("GET_LINK_COVERAGE_CORE_CODE");
			obj=new Object[1];					  
			obj[0]=travelBean.getLinkOptionCode();
			res=this.mytemplate.queryForInt(sql,obj);
			
			if(res==0){
				sql=getQuery("GET_LINK_COVERAGE_AMEND_ID");
				obj=new Object[2];
				obj[0]=travelBean.getSchemeId();
				obj[1]=travelBean.getBranchCode();
				LogManager.info("Query=>"+sql);
				amendId=this.mytemplate.queryForInt(sql,obj);
				LogManager.info("AMENDID=>"+amendId);
				amendId=amendId+1;
				sql=getQuery("INS_LINK_COVERAGE_DTLS");
				for(int i=0;i<travelBean.getLinkOption().length;i++)
				{
					this.mytemplate.update(sql,new Object[]{travelBean.getSchemeId(),travelBean.getLinkOption()[i],travelBean.getLinkOptionStatus(),travelBean.getBranchCode(),travelBean.getLinkOptionCode(),travelBean.getProductId(),travelBean.getLinkOptionDate(),amendId});
				}
				travelBean.setDisplay("");
				result="success";
			}
		}
		catch (Exception e) {			
			LogManager.debug("EXCEPTION @ { " + e + " }");
		}		
		LogManager.info("insertLinkCoverage - Exit || Result: ");
		return result;
	}
	public List<Map<String,Object>> getschemename(TravelBean travelBean) {
		List<Map<String,Object>> schemeValue=null;		
		LogManager.push("getSchemeValue - Enter");
		try{
			String sql=getQuery("GET_SCHEME_VALUE");
			LogManager.info("SQL=>"+sql);
			schemeValue=this.mytemplate.queryForList(sql,new String[]{travelBean.getProductId()});			
		}
		catch (Exception e) {			
			LogManager.debug("EXCEPTION @ { " + e + " }");
		}		
		LogManager.info("getSchemeValue - Exit || Result: " + schemeValue.size());
		return schemeValue;
	}
	public List<Map<String, Object>> getOptionName(TravelBean travelBean) {
		List<Map<String,Object>> optionvalue=null;		
		LogManager.push("getOptionValue - Enter");
		try{
			String sql=getQuery("GET_OPTION_VALUE");
			LogManager.info("SQL=>"+sql);
			optionvalue=this.mytemplate.queryForList(sql,new String[]{travelBean.getSchemeName(),travelBean.getProductId()});			
		}
		catch (Exception e) {			
			LogManager.debug("EXCEPTION @ { " + e + " }");
		}		
		LogManager.info("getOptionValue - Exit || Result: " + optionvalue.size());
		return optionvalue;
	}
	public List<Map<String, Object>> TravelTypeList(TravelBean travelBean) {
		List<Map<String,Object>> Typevalue=null;		
		LogManager.push("TravelTypeList - Enter");
		try{
			String sql=getQuery("GET_TYPE_VALUE");
			LogManager.info("SQL=>"+sql);
			Typevalue=this.mytemplate.queryForList(sql,new String[]{travelBean.getBranchCode()});			
		}
		catch (Exception e) {			
			LogManager.debug("EXCEPTION @ { " + e + " }");
		}		
		LogManager.info("TravelTypeList - Exit || Result: " + Typevalue.size());
		return Typevalue;
	}
	public List<Map<String, Object>> TypeList(TravelBean travelBean) {
		List<Map<String,Object>> Typevalue=null;		
		LogManager.push("TypeList - Enter");
		try{
			String sql=getQuery("GET_RELATION_TYPE_VALUE");
			LogManager.info("SQL=>"+sql);
			Typevalue=this.mytemplate.queryForList(sql,new String[]{travelBean.getBranchCode()});			
		}
		catch (Exception e) {			
			LogManager.debug("EXCEPTION @ { " + e + " }");
		}		
		LogManager.info("TypeList - Exit || Result: " + Typevalue.size());
		return Typevalue;
	}
	public int coreappcodetest(String code, TravelBean travelBean) {
		LogManager.push("Scheme core app code check - Enter");
		String sql;
		int res=0;
		Object[] obj=null;
		try{
		if("update".equalsIgnoreCase(travelBean.getDisplay())){
			sql=getQuery("GET_SCHEME_CORE_CODE1");
			obj=new Object[2];					  
			obj[0]=travelBean.getSchemeCode();
			obj[1]=travelBean.getSchemeId();
			res=this.mytemplate.queryForInt(sql,obj);	
		}
		else{
		sql=getQuery("GET_SCHEME_CORE_CODE");
		obj=new Object[1];					  
		obj[0]=travelBean.getSchemeCode();
		res=this.mytemplate.queryForInt(sql,obj);
		}	
		}
		catch (Exception e) {			
			LogManager.debug("EXCEPTION @ { " + e + " }");
		}	
		LogManager.push("Scheme core app code check - Exit");
		return res;
	}
	public int coreappcodetestoption(TravelBean travelBean) {
		LogManager.push(" core app code check - Enter");
		String sql;
		int res=0;
		Object[] obj=null;
		try{
		if("update".equalsIgnoreCase(travelBean.getDisplay())){
			sql=getQuery("GET_OPTION_CORE_CODE1");
			obj=new Object[2];					  
			obj[0]=travelBean.getOptionCode();
			obj[1]=travelBean.getOptionId();
			res=this.mytemplate.queryForInt(sql,obj);	
		}
		else{
			sql=getQuery("GET_OPTION_CORE_CODE");
			obj=new Object[1];					  
			obj[0]=travelBean.getOptionCode();
			res=this.mytemplate.queryForInt(sql,obj);
		}
		}
		catch (Exception e) {			
			LogManager.debug("EXCEPTION @ { " + e + " }");
		}	
		return res;
	}
	public int coreappcodetestcoverage(TravelBean travelBean) {
		LogManager.push("Coverage core app code check - Enter");
		String sql;
		int res=0;
		Object[] obj=null;
		try{
		if("update".equalsIgnoreCase(travelBean.getDisplay())){
			sql=getQuery("GET_COVERAGE_CORE_CODE1");
			obj=new Object[2];					  
			obj[0]=travelBean.getCoverageCode();
			obj[1]=travelBean.getCoverageId();
			res=this.mytemplate.queryForInt(sql,obj);	
		}
		else{
			sql=getQuery("GET_COVERAGE_CORE_CODE");
			obj=new Object[1];					  
			obj[0]=travelBean.getCoverageCode();
			res=this.mytemplate.queryForInt(sql,obj);
		}
		}
		catch (Exception e) {			
			LogManager.debug("EXCEPTION @ { " + e + " }");
		}	
		return res;
	}
	public int coreappcodetestdiscount(TravelBean travelBean) {
		LogManager.push("Discount core app code check - Enter");
		String sql;
		int res=0;
		Object[] obj=null;
		try{
			if("update".equalsIgnoreCase(travelBean.getDisplay())){
				sql=getQuery("GET_DISCOUNT_RATE_CORE_CODE1");
				obj=new Object[2];					  
				obj[0]=travelBean.getDisCode();
				obj[1]=travelBean.getDisId();
				res=this.mytemplate.queryForInt(sql,obj);	
			}
			else{
				sql=getQuery("GET_DISCOUNT_RATE_CORE_CODE");
				obj=new Object[1];					  
				obj[0]=travelBean.getDisCode();
				res=this.mytemplate.queryForInt(sql,obj);
			}
		}
		catch (Exception e) {			
			LogManager.debug("EXCEPTION @ { " + e + " }");
		}	
		return res;
	}
	public int coreappcodetestrelation(TravelBean travelBean) {
		LogManager.push("Relation core app code check - Enter");
		String sql;
		int res=0;
		Object[] obj=null;
		try{
			if("update".equalsIgnoreCase(travelBean.getDisplay())){
				sql=getQuery("GET_RELATION_DISCOUNT_CORE_CODE1");
				obj=new Object[2];					  
				obj[0]=travelBean.getDrateCode();
				obj[1]=travelBean.getDrateId();
				res=this.mytemplate.queryForInt(sql,obj);
			}
			else{
				sql=getQuery("GET_RELATION_DISCOUNT_CORE_CODE");
				obj=new Object[1];					  
				obj[0]=travelBean.getDrateCode();
				res=this.mytemplate.queryForInt(sql,obj);
			}
		}
		catch (Exception e) {			
			LogManager.debug("EXCEPTION @ { " + e + " }");
		}	
		return res;
	}
	public int coreappcodetestpremium(TravelBean travelBean) {
		LogManager.push(" Premium core app code check - Enter");
		String sql;
		int res=0;
		Object[] obj=null;
		try{
			if("update".equalsIgnoreCase(travelBean.getDisplay())){
				sql=getQuery("GET_PREMIUM_RATE_CORE_CODE1");
				obj=new Object[2];					  
				obj[0]=travelBean.getDisCode();
				obj[1]=travelBean.getDisId();
				res=this.mytemplate.queryForInt(sql,obj);
			}
			else{
				sql=getQuery("GET_PREMIUM_RATE_CORE_CODE");
				obj=new Object[1];					  
				obj[0]=travelBean.getDisCode();
				res=this.mytemplate.queryForInt(sql,obj);
			}	
		}
		catch (Exception e) {			
			LogManager.debug("EXCEPTION @ { " + e + " }");
		}	
		return res;
	}
	
	
}
