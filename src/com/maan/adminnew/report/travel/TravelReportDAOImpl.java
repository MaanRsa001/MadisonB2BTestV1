package com.maan.adminnew.report.travel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.maan.common.LogManager;
import com.maan.common.MyJdbcTemplate;
import com.opensymphony.xwork2.ActionContext;

public class TravelReportDAOImpl extends MyJdbcTemplate implements TravelReportDAO {
	Map<String, Object> session = ActionContext.getContext().getSession();
	String branchCode = (String) session.get("BranchCode");
	String productId = (String) session.get("product_id");
	private String query="";
	private Object[] args = null;
	
	public List<Map<String,Object>> travelReport(TravelReportBean bean){
		LogManager.info("Enter into the travelReport()");
		List<Map<String,Object>> drpdwn=new ArrayList<Map<String,Object>>();
		try{
			query = getQuery("GET_TRAVEL_REPORT");
			Object args[]={bean.getFromDate(),bean.getToDate(),productId,branchCode};
			LogManager.info("Query==>"+query);
			LogManager.info("Arguments => "+StringUtils.join(args,","));
			drpdwn=this.mytemplate.queryForList(query,args);
		}
		catch(Exception e){
			e.printStackTrace();
			LogManager.debug("Exception Occured @ travelReport()"+e);
		}
		LogManager.info("Exit from the travelReport()");
		return drpdwn;
		
	}
}
