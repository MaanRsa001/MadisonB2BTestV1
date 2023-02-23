package com.maan.report.dao;

import org.apache.commons.lang3.StringUtils;

import com.maan.common.LogManager;
import com.maan.common.MyJdbcTemplate;

public class PdfDAO extends MyJdbcTemplate {
	public String getPDFStatus(String userType, String productId, String policyNo) {
		String result = "";
		try {
			String query = null;
			if("3".equals(productId) || "11".equals(productId)) {
				if ("admin".equalsIgnoreCase(userType)){
					query = "select nvl(pdf_admin_status,0) from position_master where policy_no=? and status='P'";
				}else{
					query = "select nvl(pdf_broker_status,0) from position_master where policy_no=? and status='P'";
				}
			} else {
				if ("admin".equalsIgnoreCase(userType)){
					query = "select nvl(pdf_admin_status,0) from home_position_master where policy_no=? and status='P'";
				} else {
					query = "select nvl(pdf_broker_status,0) from home_position_master where policy_no=? and status='P'";
				}
			}
			Object[] args = new Object[]{policyNo};
			LogManager.info("Query==> " + query);
			LogManager.info("Args==> " + StringUtils.join(args, ", "));
			result = (String) this.mytemplate.queryForObject(query, args, String.class);
		} catch(Exception exception) {
			LogManager.debug(exception);
		}
		return result;
	}
	public void getUpdatePDFStatus(String productId, String userType, String policyNo, int clickCountStatus) {
		try {
			String query = null;
			if("3".equals(productId) || "11".equals(productId)) {
				if ("admin".equalsIgnoreCase(userType))	{
					query = "update position_master set pdf_admin_status=? where policy_no=? and status='P'";
				} else {
					query = "update position_master set pdf_broker_status=? where policy_no=? and status='P'";
				}
			} else {
				if ("admin".equalsIgnoreCase(userType))	{
					query = "update home_position_master set pdf_admin_status=? where policy_no=? and status='P'";
				} else {
					query = "update home_position_master set pdf_broker_status=? where policy_no=? and status='P'";
				}
			}
			Object[] args = new Object[]{clickCountStatus,policyNo};
			LogManager.info("Query==> " + query);
			LogManager.info("Args==> " + StringUtils.join(args, ", "));
			this.mytemplate.update(query, args);
		} catch(Exception exception) {
			LogManager.debug(exception);
		}
	}
}
