package com.maan.adminnew.report.motor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import com.maan.adminnew.report.ReportBean;
import com.maan.Motor.Claim.ClaimBean;
import com.maan.common.LogManager;
import com.maan.common.MyJdbcTemplate;
import com.maan.common.util.StringUtil;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.util.LocalizedTextUtil;

public class MotorReportDao extends MyJdbcTemplate {
	private String query="";
	private Object[] args = null;
	
public List<Map<String,Object>> getMotorReport(MotorReportBean bean){
	LogManager.info("Enter into the getMotorReport()");
	List<Map<String,Object>> drpdwn=new ArrayList<Map<String,Object>>();
	try{
		if("list".equalsIgnoreCase(bean.getMode())){
		args = new Object[2];
		args[0]=bean.getFromDate();
		args[1]=bean.getToDate();
		query = getQuery("GET_MOTOR_REPORT");
		}
		else if("showlist".equalsIgnoreCase(bean.getMode())){
			args = new Object[4];
			args[0]=bean.getFromDate();
			args[1]=bean.getToDate();
			args[2]=bean.getPolicyType();
			args[3]=bean.getBranchCode();
			query = getQuery("GET_MOTOR_REPORT_DETAIL");	
		}
		LogManager.info("Query==>"+query);
		LogManager.info("Arguments => "+StringUtils.join(args,","));
		drpdwn=this.mytemplate.queryForList(query,args);
	}catch(Exception e){
		LogManager.debug("Exception Occured @ getMotorReport()"+e);
		e.printStackTrace();
	}
	LogManager.info("Exit from getMotorReport()");
	return drpdwn;
}

public List<Map<String,Object>> getRosdAssistReport(MotorReportBean bean,String Type){
	LogManager.info("Enter into the getRosdAssistReport()");
	List<Map<String,Object>> drpdwn=new ArrayList<Map<String,Object>>();
	try{
		if("list".equalsIgnoreCase(Type)){
		args = new Object[2];
		args[0]=bean.getFromDate();
		args[1]=bean.getToDate();
		query = getQuery("GET_ROAD_ASSISTANT_LIST");
		}else if("view".equalsIgnoreCase(Type)) {
			args = new Object[1];
			args[0]=bean.getRefNo();
			query = getQuery("GET_ROAD_ASSISTANT_VIEW");
		}
		LogManager.info("Query==>"+query);
		LogManager.info("Arguments => "+StringUtils.join(args,","));
		drpdwn=this.mytemplate.queryForList(query,args);
	}catch(Exception e){
		LogManager.debug("Exception Occured @ getRosdAssistReport()"+e);
		e.printStackTrace();
	}
	LogManager.info("Exit from getRosdAssistReport()");
	return drpdwn;
}
public List<Map<String, Object>> getEndorsementList(MotorReportBean bean) {
	List<Map<String, Object>> endorsementList=null;
	try
	{
		String query = getQuery("GET_ENDORSEMENT_DETAILS");
		args = new Object[3];
		args[0]=bean.getFromDate();
		args[1]=bean.getToDate();
		args[2]=bean.getProductId();
	
		endorsementList=this.mytemplate.queryForList(query,args);
		LogManager.info("Query===>" + query);
	}
	catch (Exception e) {			
		LogManager.debug("EXCEPTION @ { " + e + " }");
		e.printStackTrace();
	}
	return endorsementList;
}

public List<Map<String, Object>> claimIntimateReportList(MotorReportBean bean){
	LogManager.info("Enter into claimIntimateReportList()");
	List<Map<String, Object>> result = null;
	String query = null;
	try{
		if( "65".equalsIgnoreCase(bean.getProductId())){
			query = getQuery("GET_CLAIM_INTIMATE_REPORT_LIST");
		}
		if( "30".equalsIgnoreCase(bean.getProductId())){
			query = getQuery("GET_CLAIM_INTIMATE_REPORT_LIST_HOME");
		}
		LogManager.info("query =>" +query);
		Object[] args = new Object [2];
		args[0] = bean.getFromDate();
		args[1] = bean.getToDate();
		LogManager.info("Argument =>"+StringUtils.join(args,","));
		result = this.mytemplate.queryForList(query,args);
		LogManager.info("Exit From claimIntimateReportList()");
		}catch(Exception e){
			LogManager.error("Exception Occured @ claimIntimateReportList()" +e);
			e.printStackTrace();
		}
		return result;

}

public List<Map<String, Object>> getEndorsementListView(MotorReportBean bean) {
	List<Map<String, Object>> endtList=null;
	try
	{
		String query = getQuery("GET_ENDORSEMENT_ADMIN_VIEW");
		args = new Object[1];
		args[0]=bean.getRefNo();
		LogManager.info("Query===>" + query);
		LogManager.info("args"+StringUtils.join(args,","));
		endtList=this.mytemplate.queryForList(query,args);
		 if( endtList!=null && endtList.size()>0){
			 Map<String, Object> record = endtList.get(0);
			 bean.setAdminRemarks(record.get("ADMIN_REMARKS")==null?"":record.get("ADMIN_REMARKS").toString());
			 bean.setStatus(record.get("STATUS1")==null?"":record.get("STATUS1").toString());
		 }  	
	}
	catch (Exception e) {			
		LogManager.debug("EXCEPTION @ { " + e + " }");
		e.printStackTrace();
	}
	
	return endtList;
}
public void getEndorsementUpdate(MotorReportBean bean) {
	try
	{
		String query = getQuery("GET_ENDORSEMENT_ADMIN_UPDATE");
		args = new Object[4];
		args[0]=bean.getLoginId();
		args[1]=bean.getAdminRemarks();
		args[2]=bean.getStatus();
		args[3]=bean.getRefNo();
		LogManager.info("Argument =>"+StringUtils.join(args,","));
		LogManager.info("Query===>" + query);
		this.mytemplate.update(query,args);
	}
	catch (Exception e) {			
		LogManager.debug("EXCEPTION @ { " + e + " }");
		e.printStackTrace();
	}

	
}


public List<Map<String, Object>> claimIntimateReportTpaList(MotorReportBean bean){
	LogManager.info("Enter into claimIntimateReportTpaList()");
	List<Map<String, Object>> result = null;
	try{
		String query = getQuery("GET_CLAIM_INTIMATE_TPA_REPORT_LIST");
		LogManager.info("query =>" +query);
		Object[] args = new Object [2];
		args[0] = bean.getFromDate();
		args[1] = bean.getToDate();
		LogManager.info("Argument =>"+StringUtils.join(args,","));
		result = this.mytemplate.queryForList(query,args);
		LogManager.info("Exit From claimIntimateReportTpaList()");
		}catch(Exception e){
			LogManager.error("Exception Occured @ claimIntimateReportTpaList()" +e);
			e.printStackTrace();
		}
		return result;
}

public List<Map<String, Object>> getIntimatePolicy(MotorReportBean bean){
	LogManager.info("Enter into getIntimatePolicy()");
	List<Map<String, Object>> result = null;
	String query = null;
	try {
		if( "65".equalsIgnoreCase(bean.getProductId())){
			query = getQuery("GET_LIST_FOR_INTIMATE_POLICY");
		}
		if( "30".equalsIgnoreCase(bean.getProductId())){
			query = getQuery("GET_LIST_FOR_INTIMATE_POLICY_HOME");	
		}
		LogManager.info("query =>" +query);
		Object[] args = new Object []{bean.getPolicyNo()};
		removeNull(args);
		LogManager.info("Argument =>"+StringUtils.join(args,","));
		result = this.mytemplate.queryForList(query,args);
		LogManager.info("Exit From getIntimatePolicy()");
	} catch(Exception e) {
		LogManager.error("Exception Occured @ getIntimatePolicy()" +e);
		e.printStackTrace();
	}
	return result;
}

public List<Map<String, Object>> getIntimateVehicle(MotorReportBean bean){
	LogManager.info("Enter into getIntimateVehicle()");
	List<Map<String, Object>> result = null;
	try{
		String query = getQuery("GET_LIST_FOR_INTIMATE_VEHICLE");
		LogManager.info("query =>" +query);
		Object[] args = new Object []{bean.getPolicyNo(), bean.getVehicleId()};
		removeNull(args);
		LogManager.info("Argument =>"+StringUtils.join(args,","));
		result = this.mytemplate.queryForList(query,args);
		LogManager.info("Exit From getIntimateVehicle()");
		}catch(Exception e){
			LogManager.error("Exception Occured @ getIntimateVehicle()" +e);
			e.printStackTrace();
		}
		return result;
}

public List<Map<String, Object>> getAssitHomeInfo(MotorReportBean bean){
	LogManager.info("Enter into getAssitHomeInfo()");
	List<Map<String,Object>> result = null;
	try{
		String query = getQuery("GET_ASSIT_FOR_HOME_INFO");
		LogManager.info("query =>" +query);
		Object[] args = new Object [1];
		args[0] = bean.getPolicyNo();
		LogManager.info("Arguments =>" +StringUtils.join(args,","));
		result = this.mytemplate.queryForList(query,args);
		if(result !=null){
			Map map = (Map) result.get(0);
			bean.setContentTypeId(map.get("CONTENT_TYPE_ID")==null?"":map.get("CONTENT_TYPE_ID").toString());
			bean.setSchemeId(map.get("SCHEME_ID")==null?"":map.get("SCHEME_ID").toString());
			bean.setQuoteNo(map.get("QUOTE_NO")==null?"":map.get("QUOTE_NO").toString());
		}
	}catch(Exception e){
		LogManager.error("Exception occured @ getAssitHomeInfo()");
		e.printStackTrace();
	}
	LogManager.info("Exit from getAssitHomeInfo()");
	return result;
}

public List<Object> getHomeInfo(MotorReportBean bean){
	LogManager.info("getHomeInfo - Enter");
	List<Map<String, String>> list=null;	
	List<Object> li=new ArrayList<Object>();
		try{
			String sql=getQuery("GET_HOME_INFO");
			LogManager.info("Query=>"+sql);
			list=this.mytemplate.queryForList(sql,new Object[]{StringUtils.isBlank(bean.getContentTypeId())?"0":bean.getContentTypeId(), StringUtils.isBlank(bean.getQuoteNo())?"":bean.getQuoteNo(),StringUtils.isBlank(bean.getContentTypeId())?"0":bean.getContentTypeId(),bean.getProductId(), bean.getSchemeId(),bean.getProductId(), bean.getSchemeId(),  StringUtils.isBlank(bean.getContentTypeId())?"0":bean.getContentTypeId(), bean.getProductId(), bean.getSchemeId(),  StringUtils.isBlank(bean.getContentTypeId())?"0":bean.getContentTypeId()});
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
		LogManager.info("getHomeInfo - Exit ");
	return 	li;
}

public List<Map<String, Object>> getIntimateView(MotorReportBean bean){
	LogManager.info("Enter into getIntimateView()");
	String query = null;
	Object args[] = null;
	List<Map<String, Object>> result = null;
	try{
		if( "65".equalsIgnoreCase(bean.getProductId())){
	    query = getQuery("GET_LIST_FOR_INTIMATE_VIEW");
		}
		if( "30".equalsIgnoreCase(bean.getProductId())){
		    query = getQuery("GET_LIST_FOR_INTIMATE_VIEW_HOME");
			}
		LogManager.info("query =>" +query);
		if( "65".equalsIgnoreCase(bean.getProductId())){
			args = new Object [2];
			args[0] = bean.getPolicyNo();
			args[1] = bean.getVehicleId();
		}
		if( "30".equalsIgnoreCase(bean.getProductId())){
		    args = new Object [2];
			args[0] = bean.getPolicyNo();
			args[1] = bean.getProductId();
			}
		removeNull(args);
		LogManager.info("Argument =>"+StringUtils.join(args,","));
		result = this.mytemplate.queryForList(query,args);
		LogManager.info("Exit From getIntimateView()");
		}catch(Exception e){
			LogManager.error("Exception Occured @ getIntimateView()" +e);
			e.printStackTrace();
		}
		return result;
}	

public List<Map<String, Object>> getIntimateThirdPartyView(MotorReportBean bean){
	LogManager.info("Enter into getIntimateThirdPartyView()");
	List<Map<String, Object>> result = null;
	try{
		String query = getQuery("GET_LIST_FOR_INTIMATE_THIRD_PARTY_VIEW");
		LogManager.info("query =>" +query);
		Object[] args = new Object [2];
		args[0] = bean.getPolicyNo();
		args[1] = bean.getVehicleId();
		removeNull(args);
		LogManager.info("Argument =>"+StringUtils.join(args,","));
		result = this.mytemplate.queryForList(query,args);
		LogManager.info("Exit From getIntimateThirdPartyView()");
		}catch(Exception e){
			LogManager.error("Exception Occured @ getIntimateThirdPartyView()" +e);
			e.printStackTrace();
		}
		return result;
}

public void getIntimationInsert(MotorReportBean bean){
	LogManager.info("Enter into getIntimationInsert()");
	String query = null;
	Object[] args = null;
	try{
		if( "65".equalsIgnoreCase(bean.getProductId())){
			query = getQuery("GET_INTIMATE_REPORT_FOR_UPDATE");
		}
		if( "30".equalsIgnoreCase(bean.getProductId())){
			query = getQuery("GET_INTIMATE_REPORT_FOR_UPDATE_HOME");
		}
		LogManager.info("query=>" +query);
		if( "65".equalsIgnoreCase(bean.getProductId())){
			args = new Object[5];
			args[0] = bean.getApproverStatus();
			args[1] = bean.getRemarks();
			args[2] = bean.getLoginId();
			args[3] = bean.getPolicyNo();
			args[4] = bean.getVehicleId();
			}
		if( "30".equalsIgnoreCase(bean.getProductId())){
			args = new Object[5];
			args[0] = bean.getApproverStatus();
			args[1] = bean.getRemarks();
			args[2] = bean.getLoginId();
			args[3] = bean.getPolicyNo();
			args[4] = bean.getProductId();
		}
		LogManager.info("Argument =>" +StringUtils.join(args,","));
		this.mytemplate.update(query,args);
		LogManager.info("Exit from getIntimationInsert()");
	}catch(Exception e){
		LogManager.error("Exception Occured @ getIntimationInsert()" +e);
		e.printStackTrace();
	}
}

public void getIntimationTpaInsert(MotorReportBean bean){
	LogManager.info("Enter into getIntimationTpaInsert()");
	String query = null;
	try{
	    query = getQuery("GET_INTIMATE_REPORT_TPA_FOR_UPDATE");
		LogManager.info("query=>" +query);
		Object[] args = new Object[5];
		args[0] = bean.getApproverStatus();
		args[1] = bean.getRemarks();
		args[2] = bean.getLoginId();
		args[3] = bean.getPolicyNo();
		args[4] = bean.getVehicleId();
		LogManager.info("Argument =>" +StringUtils.join(args,","));
		this.mytemplate.update(query,args);
		LogManager.info("Exit from getIntimationTpaInsert()");
	}catch(Exception e){
		LogManager.error("Exception Occured @ getIntimationTpaInsert()" +e);
		e.printStackTrace();
	}
}

public List<Map<String, Object>> getIntimateThirdPartyEdit(MotorReportBean bean){
	LogManager.info("Enter into getIntimateThirdPartyEdit()");
	List<Map<String,Object>> result = null;
	try{
		String query = getQuery("GET_INTIMATE_REPORT_THIRD_PARTY_FOR_EDIT");
		LogManager.info("query =>" +query);
		Object[] args = new Object [2];
		args[0] = bean.getPolicyNo();
		args[1] = bean.getVehicleId();
		LogManager.info("Arguments =>" +StringUtils.join(args,","));
		result = this.mytemplate.queryForList(query,args);
		if(result !=null){
			Map map = (Map) result.get(0);
			bean.setApproverStatus(map.get("APPROVER_STATUS")==null?"":map.get("APPROVER_STATUS").toString());
			bean.setRemarks(map.get("APPROVER_REMARKS")==null?"":map.get("APPROVER_REMARKS").toString());
		}
	}catch(Exception e){
		LogManager.error("Exception occured @ getIntimateThirdPartyEdit()");
		e.printStackTrace();
	}
	LogManager.info("Exit from getIntimateThirdPartyEdit()");
	return result;
}

public List<Map<String, Object>> getIntimateEdit(MotorReportBean bean){
	LogManager.info("Enter into getIntimateEdit()");
	List<Map<String,Object>> result = null;
	String query = null;
	Object[] args = null;
	try{
		if( "65".equalsIgnoreCase(bean.getProductId())){
	    query = getQuery("GET_INTIMATE_REPORT_FOR_EDIT");
		}
		if( "30".equalsIgnoreCase(bean.getProductId())){
		    query = getQuery("GET_INTIMATE_REPORT_FOR_EDIT_HOME");
			}
		LogManager.info("query =>" +query);
		if( "65".equalsIgnoreCase(bean.getProductId())){
		args = new Object [2];
		args[0] = bean.getPolicyNo();
		args[1] = bean.getVehicleId();
		}
		if( "30".equalsIgnoreCase(bean.getProductId())){
			args = new Object [2];
			args[0] = bean.getPolicyNo();
			args[1] = bean.getProductId();
			}
		LogManager.info("Arguments =>" +StringUtils.join(args,","));
		result = this.mytemplate.queryForList(query,args);
		if(result !=null){
			Map map = (Map) result.get(0);
			bean.setApproverStatus(map.get("APPROVER_STATUS")==null?"":map.get("APPROVER_STATUS").toString());
			bean.setRemarks(map.get("APPROVER_REMARKS")==null?"":map.get("APPROVER_REMARKS").toString());
		}
	}catch(Exception e){
		LogManager.error("Exception occured @ getIntimateEdit()");
		e.printStackTrace();
	}
	LogManager.info("Exit from getIntimateEdit()");
	return result;
}

public int getCheckIntimatePolicy(MotorReportBean bean){
	LogManager.info("Enter into getCheckIntimatePolicy()");
	int result = 0;
	try {
		String query = getQuery("GET_CHECK_FOR_INTIMATE_POLICY");
		LogManager.info("query =>" +query);
		Object[] args = new Object []{bean.getPolicyNo()};
		LogManager.info("Argument =>"+StringUtils.join(args,","));
		result = this.mytemplate.queryForInt(query,args);
		LogManager.info("Exit From getCheckIntimatePolicy()");
	} catch(Exception e) {
		LogManager.error("Exception Occured @ getCheckIntimatePolicy()" +e);
		e.printStackTrace();
	}
	return result;
}

public void updRoadAssist(MotorReportBean bean) {
	LogManager.info("Enter into updRoadAssist()");
	try
	{
		String query = getQuery("GET_ROAD_ASSISTANT_UPDATE");
		args = new Object[4];
		args[0]=bean.getAdminRemarks();
		args[1]=bean.getStatus();
		args[2]=bean.getRefNo();
		args[3]=bean.getLoginId();
		LogManager.info("Argument =>"+StringUtils.join(args,","));
		LogManager.info("Query===>" + query);
		this.mytemplate.update(query,args);
	}
	catch (Exception e) {			
		LogManager.debug("EXCEPTION @ { " + e + " }");
		e.printStackTrace();
	}
	LogManager.info("Exit From updRoadAssist()");
}

}

