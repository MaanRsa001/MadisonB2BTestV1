package com.maan.Home.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.core.RowMapper;
import com.maan.Home.Controller.AdminHomeBean;
import com.maan.common.LogManager;
import com.maan.common.MyJdbcTemplate;

public class AdminHomeDAO extends MyJdbcTemplate {
	private String query="";
	private Object obj[]=null;
	
	public List<Object> getProductList(String branchCode){
		LogManager.info("getProductList - Enter ");
		List<Object> productList=null;
		try{
			query=getQuery("GET_PRODUCT_LIST");
			LogManager.info("Query=>"+query);
			obj=new Object[]{branchCode};
			LogManager.info("obj[] ==> "+StringUtils.join(obj, ","));
			productList=this.mytemplate.queryForList(query,obj);
		}catch(Exception e){
			LogManager.debug(e);
		}
		LogManager.popRemove();
		LogManager.info("getProductList - Exit");
		return productList;
	}
	
	public List<Object> getBranchList(){
		LogManager.info("getProductList - Enter ");
		List<Object> productList=null;
		try{
			query=getQuery("GET_HOME_BRANCH_LIST");
			LogManager.info("Query=>"+query);
			productList=this.mytemplate.queryForList(query);
		}catch(Exception e){
			LogManager.debug(e);
		}
		LogManager.popRemove();
		LogManager.info("getProductList - Exit");
		return productList;
	}
	
	public List<AdminHomeBean> getSchemeList(final AdminHomeBean bean, String searchField, String searchString, String searchOper) {
		LogManager.info("getSchemeList - Enter ");
		List schemeList=null;
		String str="like '%'||upper(?)||'%'";
		try{
			query=getQuery("GET_HOME_SCHEME_LIST");
			if(searchField!=null && searchString!=null && searchOper!=null){
				if("nc".equalsIgnoreCase(searchOper))
					str="not like '%'||upper(?)||'%'";
				else if("cn".equalsIgnoreCase(searchOper))
					str="like '%'||upper(?)||'%'";
				else if("eq".equalsIgnoreCase(searchOper))
					str=" = upper(?)?";
				else if("nq".equalsIgnoreCase(searchOper))
					str=" != upper(?)";
				
				if("schemeId".equalsIgnoreCase(searchField))
					query+=" and upper(sm.SCHEME_ID) "+str;
				else if("schemeName".equalsIgnoreCase(searchField))
					query+=" and upper(sm.SCHEME_NAME) "+str;
				else if("productId1".equalsIgnoreCase(searchField))
					query+=" and upper(sm.product_id) "+str;
				else if("productName".equalsIgnoreCase(searchField))
					query+=" and upper(pm.product_name) "+str;
				else if("displayOrder".equalsIgnoreCase(searchField))
					query+=" and upper(sm.DISPLAY_ORDER) "+str;
				else if("remarks".equalsIgnoreCase(searchField))
					query+=" and upper(sm.REMARKS) "+str;
				else if("rsaCode".equalsIgnoreCase(searchField))
					query+=" and upper(sm.RSACODE) "+str;
				else if("branchCode".equalsIgnoreCase(searchField))
					query+=" and upper(sm.BRANCH_CODE) "+str;
				else if("branchName".equalsIgnoreCase(searchField))
					query+=" and upper(bm.branch_name) "+str;
				else if("status1".equalsIgnoreCase(searchField)){
					query+=" and upper(sm.status) "+str;
					if(searchString.contains("n") || searchString.contains("i") || searchString.contains("N") || searchString.contains("I"))
						searchString="N";
					else
						searchString="Y";
				}
				obj=new Object[]{bean.getBranchCode(), searchString};
			}else
				obj=new Object[]{bean.getBranchCode()};
			LogManager.info("Query=>"+query);
			schemeList = this.mytemplate.query(query,obj,new RowMapper() {
    			public Object mapRow(final ResultSet rset,final int idVal) throws SQLException {
    				AdminHomeBean bean1=new AdminHomeBean();
    				bean1.setSchemeId(rset.getString("SCHEME_ID")==null?"":rset.getString("SCHEME_ID"));
    			    bean1.setSchemeName(rset.getString("SCHEME_NAME")==null?"":rset.getString("SCHEME_NAME"));
    			    bean1.setProductId1(rset.getString("product_id")==null?"":rset.getString("product_id"));
    			    bean1.setProductName(rset.getString("product_name")==null?"":rset.getString("product_name"));
    			    bean1.setRemarks(rset.getString("REMARKS")==null?"":rset.getString("REMARKS"));
    			    bean1.setRsaCode(rset.getString("RSACODE")==null?"":rset.getString("RSACODE"));
    			    bean1.setDisplayOrder(rset.getString("DISPLAY_ORDER")==null?"":rset.getString("DISPLAY_ORDER"));
    			    bean1.setBranchCode(rset.getString("BRANCH_CODE")==null?"":rset.getString("BRANCH_CODE"));
    			    bean1.setBranchName(rset.getString("branch_name")==null?"":rset.getString("branch_name"));
    			    bean1.setStatus1(rset.getString("status1")==null?"":rset.getString("status1"));
    			    bean1.setStatus(rset.getString("STATUS")==null?"":rset.getString("STATUS"));
    				return bean1;
    			}});
		}catch(Exception e){
			LogManager.debug(e);
		}
		LogManager.info("getSchemeList - Exit");
		return schemeList;
	}
	
	public List<AdminHomeBean> getCoveragesList(final AdminHomeBean bean, String searchField, String searchString, String searchOper) {
		LogManager.info("getCoveragesList - Enter ");
		List coveragesList=null;
		try{
			query=getQuery("GET_HOME_ADMIN_COVERAGES_LIST");
			if(searchField!=null && searchString!=null && searchOper!=null){
				String str="like '%'||upper(?)||'%'";
				if("nc".equalsIgnoreCase(searchOper))
					str="not like '%'||upper(?)||'%'";
				else if("cn".equalsIgnoreCase(searchOper))
					str="like '%'||upper(?)||'%'";
				else if("eq".equalsIgnoreCase(searchOper))
					str=" = upper(?)?";
				else if("nq".equalsIgnoreCase(searchOper))
					str=" != upper(?)";
				
				if("schemeId".equalsIgnoreCase(searchField))
					query+=" and upper(sm.SCHEME_ID) "+str;
				else if("schemeName".equalsIgnoreCase(searchField))
					query+=" and upper(sm.SCHEME_NAME) "+str;
				else if("productId1".equalsIgnoreCase(searchField))
					query+=" and upper(sm.product_id) "+str;
				else if("productName".equalsIgnoreCase(searchField))
					query+=" and upper(pm.product_name) "+str;
				else if("displayOrder".equalsIgnoreCase(searchField))
					query+=" and upper(sm.DISPLAY_ORDER) "+str;
				else if("remarks".equalsIgnoreCase(searchField))
					query+=" and upper(sm.REMARKS) "+str;
				else if("rsaCode".equalsIgnoreCase(searchField))
					query+=" and upper(sm.RSACODE) "+str;
				else if("branchCode".equalsIgnoreCase(searchField))
					query+=" and upper(sm.BRANCH_CODE) "+str;
				else if("branchName".equalsIgnoreCase(searchField))
					query+=" and upper(bm.branch_name) "+str;
				else if("status1".equalsIgnoreCase(searchField)){
					query+=" and upper(sm.status) "+str;
					if(searchString.contains("n") || searchString.contains("i") || searchString.contains("N") || searchString.contains("I"))
						searchString="N";
					else
						searchString="Y";
				}
				obj=new Object[]{bean.getBranchCode(),searchString};
			}else
				obj=new Object[]{bean.getBranchCode()};
			LogManager.info("Query=>"+query);
			coveragesList = this.mytemplate.query(query,obj,new RowMapper() {
    			public Object mapRow(final ResultSet rset,final int idVal) throws SQLException {
    				AdminHomeBean bean1=new AdminHomeBean();
    				bean1.setCoverId(rset.getString("COVERAGES_ID")==null?"":rset.getString("COVERAGES_ID"));
    			    bean1.setCoverName(rset.getString("COVERAGES_NAME")==null?"":rset.getString("COVERAGES_NAME"));
    			    bean1.setCoverDisName(rset.getString("COVERAGES_DISPLAY_NAME")==null?"":rset.getString("COVERAGES_DISPLAY_NAME"));
    			    bean1.setProductName(rset.getString("PRODUCT_NAME")==null?"":rset.getString("PRODUCT_NAME"));
    			    bean1.setSchemeName(rset.getString("SCHEME_NAME")==null?"":rset.getString("SCHEME_NAME"));
    			    bean1.setProductId1(rset.getString("PRODUCT_ID")==null?"":rset.getString("PRODUCT_ID"));
    			    bean1.setSchemeId(rset.getString("SCHEME_ID")==null?"":rset.getString("SCHEME_ID"));
    			    bean1.setContentTypeId(rset.getString("CONTENT_TYPE_ID")==null?"":rset.getString("CONTENT_TYPE_ID"));
    			    bean1.setContentTypeName(rset.getString("CONTENT_DESCRIPTION")==null?"":rset.getString("CONTENT_DESCRIPTION"));
    				return bean1;
    			}});
		}catch(Exception e){
			LogManager.debug(e);
		}
		LogManager.info("getCoveragesList - Exit");
		return coveragesList;
	}
	
	public int[] validateScheme(final AdminHomeBean bean, String option){
		LogManager.info("validateSchemeId - Enter ");
		int countval[]=new int[3];
		String query1="";
		String cond="";
		try{
			query=getQuery("GET_VALID_SCHEME_MASTER");
			if("edit".equalsIgnoreCase(option))
				cond=" and scheme_id!='"+bean.getSchemeId()+"'";
			
			query1=query+" where scheme_id=? and product_id=?"+cond;
			obj=new Object[]{bean.getSchemeId(), bean.getProductName()};
			LogManager.info("Query=>"+query1);
			countval[0]=this.mytemplate.queryForInt(query1, obj);
			
			query1=query+"  where product_id=? and RSACODE=?"+cond;
			obj=new Object[]{bean.getProductName(), bean.getRsaCode()};
			LogManager.info("Query=>"+query1);
			countval[1]=this.mytemplate.queryForInt(query1, obj);
			
			query1=query+" where product_id=? and DISPLAY_ORDER=?"+cond;
			obj=new Object[]{bean.getProductName(), bean.getDisplayOrder()};
			LogManager.info("Query=>"+query1);
			countval[2]=this.mytemplate.queryForInt(query1, obj);
			
		}catch(Exception e){
			LogManager.debug(e);
		}
		LogManager.info("validateSchemeId - Exit");
		return countval;
	}
	
	public int schemeModify(final AdminHomeBean bean, String operation, String id){
		LogManager.info("validateSchemeId - Enter ");
		int countval=0;
		try{
			if("add".equalsIgnoreCase(operation)){
				query=getQuery("INS_HOME_SCHEME_MASTER");
				obj=new Object[]{bean.getSchemeId(), bean.getSchemeName(), bean.getDisplayOrder(), bean.getProductName(), bean.getRemarks(),
									bean.getStatus1(), bean.getRsaCode(), bean.getBranchName()};
			} else if("edit".equalsIgnoreCase(operation)){
				query=getQuery("UPD_HOME_SCHEME_MASTER");
				obj=new Object[]{bean.getSchemeName(), bean.getDisplayOrder(), bean.getProductName(), bean.getRemarks(), bean.getStatus1(), 
									bean.getRsaCode(), bean.getBranchName(), bean.getSchemeId()};
			} else if("del".equalsIgnoreCase(operation)){
				query=getQuery("DEL_HOME_SCHEME_MASTER");
				obj=new Object[]{id};
			}
			LogManager.info("Query==>"+query);
			countval=this.mytemplate.update(query, obj);
		}catch(Exception e){
			LogManager.debug(e);
		}
		LogManager.info("validateSchemeId - Exit");
		return countval;
	}
}