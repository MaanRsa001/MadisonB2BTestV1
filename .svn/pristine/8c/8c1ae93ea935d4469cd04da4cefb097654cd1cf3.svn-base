package com.maan.ClaimIntimation.dao;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.json.simple.JSONObject;

import com.maan.ClaimIntimation.bean.ClaimIntimationBean;
import com.maan.common.LogManager;
import com.maan.common.MyJdbcTemplate;

public class ClaimIntimationDAO extends MyJdbcTemplate {
	private final String properties="webservice"; 
	private ResourceBundle bundle=ResourceBundle.getBundle("webservice");

	public List<Map<String, Object>> listClaim(ClaimIntimationBean bean) {
		LogManager.info("Enter into listClaim()");
		List<Map<String, Object>> list=null;
		String querystatus="";
		try{
			if("R".equalsIgnoreCase(bean.getcStatus())){
				querystatus = "GET_REJ_CLAIM_LIST";
			}
			else if("A".equalsIgnoreCase(bean.getcStatus())){
				querystatus ="GET_ACC_CLAIM_LIST";
			}
			else if("P".equalsIgnoreCase(bean.getcStatus())){
				querystatus ="GET_PEND_CLAIM_LIST";
			}
		String query = getQuery(querystatus);
		LogManager.info("query =>" + query);
		list=this.mytemplate.queryForList(query);
		}
		catch(Exception e){
			LogManager.error("Exception occured @ listClaim()" + e);
		}
		LogManager.info("Exit from listClaim()");
		return list;
	}
	public int insertClaim(ClaimIntimationBean bean) {
		int count =0;
		try{
			if("insert".equalsIgnoreCase(bean.getMode())){
				String query=getQuery("INSERT_CLAIM_LIST");
				Object[] obj=new Object[]{bean.getName(),bean.getPassport(),bean.getPhone(),bean.getPolicyNo(),bean.getVehicleRegNo(),bean.getDateOfAccident(),bean.getClaimRefNo()};
				LogManager.info("query =>" + query);
				LogManager.info("Obj =>" + StringUtils.join(obj, ", "));
				count=this.mytemplate.update(query, obj);
			}
			if("update".equalsIgnoreCase(bean.getMode())){
				String query=getQuery("UPDATE_CLAIM_LIST");
				Object[] obj=new Object[]{bean.getName(),bean.getPassport(),bean.getPhone(),bean.getPolicyNo(),bean.getVehicleRegNo(),bean.getDateOfAccident(),bean.getStatus(),bean.getRemarks(),bean.getPolicyNo(),bean.getClaimRefNo()};
				LogManager.info("query =>" + query);
				LogManager.info("Obj =>" + StringUtils.join(obj, ", "));
				count=this.mytemplate.update(query, obj);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return count;
	}

	public List<Map<String, Object>> getEditList(ClaimIntimationBean bean) {
		LogManager.info("Enter into getEditList()");
		List<Map<String, Object>> result = null;
		try {
			String query = getQuery("EDIT_CLAIM_LIST");		
			LogManager.info("query =>" + query);
			Object args[] = {bean.getPolicyNo(),bean.getClaimRefNo()};
			LogManager.info("Arguments =>" + StringUtils.join(args, ","));
			result = this.mytemplate.queryForList(query, args);
			if (result.size() > 0) {
				Map map = (Map) result.get(0);
				bean.setName(map.get("NAME") == null ? "" : map.get("NAME").toString());
				bean.setPassport(map.get("NRC_PASSPORT_NO") == null ? "" : map.get("NRC_PASSPORT_NO").toString());
				bean.setPhone(map.get("PHONE_NO") == null ? "" : map.get("PHONE_NO").toString());
				bean.setPolicyNo(map.get("POLICY_NUMBER") == null ? "" : map.get("POLICY_NUMBER").toString());
				bean.setVehicleRegNo((map.get("VEHICLE_REG_NO") == null ? "" : map.get("VEHICLE_REG_NO")).toString());
				bean.setDateOfAccident(map.get("DATE_OF_ACCIDENT") == null ? "" : map.get("DATE_OF_ACCIDENT").toString());
				bean.setClaimId(map.get("CLAIM_ID") == null ? "": map.get("CLAIM_ID").toString());
				bean.setStatus(map.get("STATUS") == null ? "": map.get("STATUS").toString());
				bean.setRemarks(map.get("REMARKS") == null ? "": map.get("REMARKS").toString());
				bean.setClaimRefNo(map.get("CLAIM_REF") == null ? "": map.get("CLAIM_REF").toString());
				String[] obj =bean.getPolicyNo().toString().split("/");
				bean.setPolicyNo1(obj[0]);
				bean.setPolicyNo2(obj[1]);
				bean.setPolicyNo3(obj[2]);
				bean.setPolicyNo4(obj[3]);
			}
		} catch (Exception e) {
			LogManager.error("Exception Occured @ geteditClaim()" + e);
			e.printStackTrace();
		}
		LogManager.info("Exit from getEditList() ");

		return result;
	}

	public String getClaimRef(ClaimIntimationBean bean) {
		String value="";
		try {
			String query = "";
			query = getQuery("GET_CLAIM_REF_SEQ_NO");
			LogManager.info("Query==>" + query);
			value = (String) this.mytemplate.queryForObject(query,String.class);
		} catch(Exception exception) {
			LogManager.debug(exception);
		}
		return value;
	}

	public List<Map<String, Object>> getClaimStatus(ClaimIntimationBean bean) {
		List<Map<String, Object>> result = null;
		try{
			String query= getQuery("GET_CLAIM_STATUS");
			LogManager.info("Query==>" + query);
			Object args[] = {bean.getPhone(),bean.getClaimRefNo()};
			result = this.mytemplate.queryForList(query, args);
			if (result.size() > 0) {
				Map map = (Map) result.get(0);
				bean.setName(map.get("NAME") == null ? "" : map.get("NAME").toString());
				bean.setPassport(map.get("NRC_PASSPORT_NO") == null ? "" : map.get("NRC_PASSPORT_NO").toString());
				bean.setPhone(map.get("PHONE_NO") == null ? "" : map.get("PHONE_NO").toString());
				bean.setPolicyNo(map.get("POLICY_NUMBER") == null ? "" : map.get("POLICY_NUMBER").toString());
				bean.setVehicleRegNo((map.get("VEHICLE_REG_NO") == null ? "" : map.get("VEHICLE_REG_NO")).toString());
				bean.setDateOfAccident(map.get("DATE_OF_ACCIDENT") == null ? "" : map.get("DATE_OF_ACCIDENT").toString());
				bean.setClaimId(map.get("CLAIM_ID") == null ? "": map.get("CLAIM_ID").toString());
				bean.setStatus(map.get("STATUS") == null ? "": map.get("STATUS").toString());
				bean.setRemarks(map.get("REMARKS") == null ? "": map.get("REMARKS").toString());
				bean.setClaimRefNo(map.get("CLAIM_REF") == null ? "": map.get("CLAIM_REF").toString());
				bean.setUpdateDate(map.get("STATUS_UPDATE") == null ? "": map.get("STATUS_UPDATE").toString());
			}
		}
		catch(Exception e){
			LogManager.debug(e);
		}
		return result;
	}
	public List<Map<String, Object>> getClaimIntimUploadList(ClaimIntimationBean bean) {
		List<Map<String,Object>> resultList = new ArrayList<Map<String,Object>>();
		try {
			String query = "";
			Object[] args=null;
					args=new Object[]{bean.getClaimRefNo(),"65"};
					query = getQuery("GET_CLAIMINTIMATION_UPLOAD_LIST_MOTOR");
				
			LogManager.info("Query==>" + query);
			LogManager.info("Args==>" + StringUtils.join(args,", "));
			resultList=this.mytemplate.queryForList(query, args);
		} catch(Exception exception) {
			LogManager.debug(exception);
		}

		return resultList;
	}
	
	public void insertDocumentDetails(String claimRefNo,String documentId, String documentPath, String docDesc, String fileName, String productId) {
		try {
			String query = "";
			Object[] args = null;
			query = getQuery("INS_CLAIMINTIMATION_UPLOAD");
			args = new Object[8];
			args[0] = claimRefNo;
			args[1] = documentId;
			args[2] = documentPath;
			args[3] = docDesc==null?"":docDesc;
			args[4] = "Y";
			args[5] = fileName;
			args[6]=productId;
			args[7]="";
			
			LogManager.info("Query==>" + query);
			LogManager.info("Args==>" + StringUtils.join(args,", "));
			this.mytemplate.update(query,args);
		} catch(Exception exception) {
			LogManager.debug(exception);
		}
	}
	public void deleteDocument(String filePath) {
		Object[] args={filePath};
		try {
			String query =getQuery("DEL_DOCUMENTS");
			this.mytemplate.update(query, args);
		}catch(Exception exception) {
			LogManager.debug(exception);
		}		
	}
	
	public String getClaimExistRef(ClaimIntimationBean bean) {
		String claimRefNo="";
		try {
			Object[] args = null;
			String query = "";
			query = getQuery("GET_CLAIM_EXIST_REFNUM");
			LogManager.info("Query==>" + query);
			claimRefNo =(String) this.mytemplate.queryForObject(query, new Object[]{bean.getPhone(),bean.getPolicyNo()}, String.class);
		} catch(Exception exception) {
			 claimRefNo="";
		}
		return claimRefNo;
	}
	public List<Map<String, Object>> getDocDownloadList(ClaimIntimationBean bean) {
		List<Map<String,Object>> resultList = new ArrayList<Map<String,Object>>();
		try {
			String query = "";
			Object[] args=null;
					args=new Object[]{bean.getClaimRefNo(),"65"};
					query = getQuery("GET_CLAIMINTIMATION_UPLOAD_LIST_MOTOR");
				
			LogManager.info("Query==>" + query);
			LogManager.info("Args==>" + StringUtils.join(args,", "));
			resultList=this.mytemplate.queryForList(query, args);
		} catch(Exception exception) {
			LogManager.debug(exception);
		}

		return resultList;
	}
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> setClaimDetails(ClaimIntimationBean bean) {
		LogManager.info("Enter into  setClaimDetals()");
		List<Map<String, Object>> result = new ArrayList<Map<String,Object>>();
		try{
			String link=bundle.getString("CLAIM_SEARCH_LINK");
			String auth=bundle.getString("CLAIM_SEARCH_AUTH");
			JSONObject reqObj = new JSONObject();
			reqObj.put("PolicyNumber",bean.getPolicyNo()==null?"":bean.getPolicyNo());
			reqObj.put("ClaimNumber",bean.getClaimNo()==null?"":bean.getClaimNo());
			LogManager.info("Raw Details Json setClaimDetals ===> " + reqObj.toString().replaceAll("\"\"", "null"));
			String jsonList = callAPI(link,auth, reqObj.toString().replaceAll("\"\"", "null"),bean);
			if(StringUtils.isNotBlank(jsonList)){
				ObjectMapper mapper = new ObjectMapper();
				result = mapper.readValue(jsonList, new TypeReference<List<Map<String, Object>>>(){});
			}
			//String qry="SELECT BCS_POL_NO,BCS_CLM_NO,BCS_STATUS,TO_CHAR(BCS_UPD_DT,'DD/MM/YYYY HH:MI:SS AM') BCS_UPD_DT,TO_CHAR(BCS_CST_DT,'DD/MM/YYYY HH:MI:SS AM') BCS_CST_DT,BCS_REMARKS FROM XXT_B2C_CLM_STS WHERE BCS_POL_NO IN (?) OR BCS_CLM_NO IN (?) ORDER BY BCS_CST_DT DESC";
			//result=this.mytemplate.queryForList(qry,new Object[]{bean.getPolicyNo(),bean.getClaimNo()});
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	public String callAPI(String Link,String authorization,String request, ClaimIntimationBean bean){
		try{
			LogManager.info("Request JSON===>" + request);
			StringEntity entity = new StringEntity(request,"UTF-8");
			CloseableHttpClient httpclient = HttpClients.createDefault();			 
			HttpPost httpPost = new HttpPost(Link); 
			httpPost.setHeader("Content-type", "application/json; charset=utf-8"); 
			httpPost.setHeader(HttpHeaders.AUTHORIZATION,authorization); 
			httpPost.setEntity(entity);	
			CloseableHttpResponse response = httpclient.execute(httpPost); 
			//bean.setStatusCode(response.getStatusLine().getStatusCode());
			if(response.getStatusLine().getStatusCode()<=400){
				BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
				StringBuffer responseAsString = new StringBuffer();
				String line = "";				
				while ((line = rd.readLine()) != null) {
					responseAsString.append(line);					
				}					
				 LogManager.info("Response JSON===>" + responseAsString.toString());				 
				return responseAsString.toString();
				
			}

		}catch (Exception e) {
			LogManager.info("Exception @ API Configuration===>" +e);
			e.printStackTrace();
		}
		return null;
	}
}