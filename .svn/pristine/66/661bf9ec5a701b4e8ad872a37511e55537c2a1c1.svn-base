package com.maan.Health.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

import com.maan.Health.controller.UploadBean;
import com.maan.common.LogManager;
import com.maan.common.MyJdbcTemplate;

public class DocUploadDAO extends MyJdbcTemplate{
	
	public String insertUploadDetails(List<Object[]> details, final String applicationNo, final String docId){
    	String result = null;
    	try{
    		LogManager.info("DOCUMENT_UPLOAD_MAX_ID => "+getQuery("DOCUMENT_UPLOAD_MAX_ID"));
    		LogManager.info("DOCUMENT_UPLOAD_MAX_ID PARAMS => "+applicationNo+" : "+docId);
    		int maxId = this.mytemplate.queryForInt(getQuery("DOCUMENT_UPLOAD_MAX_ID"), new Object[]{applicationNo, docId});
    		LogManager.info("DOCUMENT_UPLOAD_MAX_ID RESULT => "+maxId);
    		
    		LogManager.info("INS_DOCUMENT_UPLOAD => "+getQuery("INS_DOCUMENT_UPLOAD"));
    		for(int i=0;i<details.size();i++){
    			Object[] args = details.get(i);
    			if(!"0".equals(args[2])){
    				args[2] = maxId;
    			}
    			LogManager.info("INS_DOCUMENT_UPLOAD PARAM => "+args);
	    		int count = this.mytemplate.update(getQuery("INS_DOCUMENT_UPLOAD"), args);
	    		LogManager.info("INS_DOCUMENT_UPLOAD RESULT FOR ROW:"+(i+1)+" => "+count);
    		}
		}catch(Exception e){
			LogManager.debug(e);
			result = e.getMessage();
		}		
		
    	return result;
    }
	public List<UploadBean> getUploadedDocumentList(final String applicationNo, final String docId){
    	return getUploadedDocuments(applicationNo, docId, "");
    }
    
    public UploadBean getUploadedDocumentDetails(final String applicationNo, final String docId, final String docSNo){
    	UploadBean bean = new UploadBean();
    	final String condt = " AND HDD.DOC_SNO= '"+docSNo+"'";
    	List<UploadBean> list = getUploadedDocuments(applicationNo, docId, condt);
    	if(list!=null && list.size()>0){
    		bean = list.get(0);
    	}
    	return bean;
    }
	public List<UploadBean> getUploadedDocuments(final String applicationNo, final String docId,final String condt){
		LogManager.info("getUploadedDocuments() || Enter  applicationNo Ref:"+applicationNo+" docId:"+docId+" - Enter");
    	List<UploadBean> result = new ArrayList<UploadBean>();
    	final String query = getQuery("GET_UPLOADED_DOC_LIST")+condt;
    	final Object[] args = new Object[]{applicationNo, docId};
    	LogManager.info("GET_UPLOADED_DOC_LIST =>"+query);
    	LogManager.info("GET_UPLOADED_DOC_LIST PARAMS => "+applicationNo + " => "+docId);
    	try {
    		result = this.mytemplate.query(query, args, new RowMapper() {
    			public Object mapRow(final ResultSet rs, final int idVal) throws SQLException {
    				final UploadBean updBean = new UploadBean();
    				updBean.setDocId(rs.getString("DOC_ID"));
    				updBean.setDocSNo(rs.getString("DOC_SNO"));
    				updBean.setDocName(rs.getString("ORG_FILE_NAME"));
    				updBean.setDocOurName(rs.getString("OUR_FILE_NAME"));
    				updBean.setDocPath(rs.getString("FILE_LOCATION"));
    				updBean.setDocUploadBy(rs.getString("UPLOAD_BY"));
    				updBean.setDocUploadDate(rs.getString("EFF_DATE"));
    				updBean.setDocDesc(rs.getString("DOC_DESC"));
    				updBean.setStatus(rs.getString("STATUS"));
    				return updBean;
    			}});
    		
        } catch (Exception e) {
        	LogManager.debug(e);
        }
        LogManager.info("getUploadedDocuments() || Exit");
    	return result;
	}
	public String deleteUpdateFileDetails(final String applicationNo, final String docId, final String docSNo){
		LogManager.info("Delete Uploaded Document Details for applicationNo:"+applicationNo+" docId:"+docId+" DOC:"+docId+" - Exit");
    	String result = null;
    	String query = getQuery("DEL_UPLOADED_DOC_DETAILS");
    	Object[] args = new Object[]{applicationNo, docId, docSNo};
    	LogManager.info("DEL_UPLOADED_DOC_DETAILS =>"+query);
    	LogManager.info("DEL_UPLOADED_DOC_DETAILS PARAMS => "+applicationNo + " => "+docId + " => "+docSNo);
    	try{
    		int count = this.mytemplate.update(query, args);
    		LogManager.info("DEL_UPLOADED_DOC_DETAILS Records Deleted =>"+count);
    		query = getQuery("ARRANGE_UPLOADED_DOC_LIST");
        	args = new Object[]{applicationNo, docId,docSNo};
        	count = this.mytemplate.update(query, args);
        	LogManager.info("ARRANGE_UPLOADED_DOC_LIST =>"+query);
        	LogManager.info("ARRANGE_UPLOADED_DOC_LIST Records =>"+count);
    	}catch(Exception e){
    		LogManager.debug(e);
    		result = "Error Deletion of File Records => "+e.getMessage();
    	}
    	LogManager.info("Delete Uploaded Document Details for Claim Ref:"+applicationNo+" DRM:"+docId+" DOC:"+docSNo+" - Exit");
    	return result;
    }

}
