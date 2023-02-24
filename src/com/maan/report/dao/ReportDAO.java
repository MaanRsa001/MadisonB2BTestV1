package com.maan.report.dao;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;

import com.maan.common.DBConstants;
import com.maan.common.LogManager;
import com.maan.common.MyJdbcTemplate;
import com.maan.dao.ApiCaller.ApiForReport;
import com.maan.report.ReportAction;
import com.maan.webservice.dao.PolicyGenerationDAO;

public class ReportDAO extends MyJdbcTemplate {
	ApiForReport reportApi = new ApiForReport();

	public List<Map<String,Object>> getReportList(String loginId,String productId,String issuer, final String menuType,String openCoverNo,String startDate,String endDate, String quoteNo, String policyNo,String searchBy,String searchValue, String searchField,String searchString,String searchOper,String branchCode, String userLogin,String schemeId,String vehicleId,String loginBranch)
	{
		String sql = "";
		List<Map<String,Object>> reportList=new ArrayList<Map<String,Object>>();
		StringUtils.defaultIfEmpty(openCoverNo,"");
		LogManager.info("args===>" + loginId+","+ productId+","+ issuer+","+ menuType+","+openCoverNo);	
		try {
			
			String homeSQLCondition = "";
			final String LOGINID_SQL_CONDITION = StringUtils.isBlank(issuer)?"?":" SELECT LOGIN_ID FROM LOGIN_MASTER LM1 WHERE LM1.OA_CODE IN (SELECT OA_CODE FROM LOGIN_MASTER LM2 WHERE LM2.LOGIN_ID=?) ";
			final String ISSUER_SQL_CONDITION = StringUtils.isBlank(issuer)?" AND a.APPLICATION_ID IN ('1') ":" AND a.APPLICATION_ID IN ('" + issuer + "' )";
			
			if("30".equals(productId)) {
				//homeSQLCondition = " and  a.scheme_id = '" + schemeId + "'";
			}
			String args[]=null;
			args=new String[]{loginId,productId,StringUtils.isBlank(issuer)?"":"gotherPolicyNo".equalsIgnoreCase(searchField)?"1":issuer,StringUtils.isBlank(openCoverNo)?"":openCoverNo};
			if("P".equalsIgnoreCase(menuType)){
				if("11".equals(productId)){
					sql = getQuery("GET_PORTFOLIO_OC", new Object[]{"gotherPolicyNo".equalsIgnoreCase(searchField)?"":"A.LOGIN_ID='"+loginId+"' AND "});
					openCoverNo="%"+getOriginalPolicyNo(openCoverNo)+"%";
					//args=new String[]{productId,StringUtils.isBlank(issuer)?"":"gotherPolicyNo".equalsIgnoreCase(searchField)?"":issuer,StringUtils.isBlank(openCoverNo)?"":openCoverNo};
					String applicationId = StringUtils.isBlank(issuer)?"1":"gotherPolicyNo".equalsIgnoreCase(searchField)?"1":issuer;
					args=new String[]{productId,applicationId,applicationId,StringUtils.isBlank(openCoverNo)?"":openCoverNo};
				} else if("3".equals(productId)) {
					sql=getQuery(DBConstants.REPORT_PORTFOLIO, new Object[]{"gotherPolicyNo".equalsIgnoreCase(searchField)?"":"A.LOGIN_ID='"+loginId+"' AND "});
					//args=new String[]{productId,StringUtils.isBlank(issuer)?"":"gotherPolicyNo".equalsIgnoreCase(searchField)?"":issuer,StringUtils.isBlank(openCoverNo)?"":openCoverNo};
					String applicationId = StringUtils.isBlank(issuer)?"1":"gotherPolicyNo".equalsIgnoreCase(searchField)?"":issuer;
					args=new String[]{productId,applicationId,applicationId,StringUtils.isBlank(openCoverNo)?"":openCoverNo};
				}
				
				else if("33".equalsIgnoreCase(productId)){
					sql=getQuery(DBConstants.TRAVEL_REPORT_PORTFOLIO_HOME, new Object[]{(StringUtils.isBlank(issuer)?"":"gotherPolicyNo".equalsIgnoreCase(searchField)?"":" AND HPM.APPLICATION_ID IN ('"+issuer+"') ")});
					args=new String[]{loginId,productId};
				}else if("30".equalsIgnoreCase(productId)){
					sql=getQuery("home.report.portfolio", new Object[]{(StringUtils.isBlank(issuer)?"":"gotherPolicyNo".equalsIgnoreCase(searchField)?"":" AND HPM.APPLICATION_ID IN ('"+issuer+"') ")});
					args=new String[]{loginId,productId,loginBranch};
				}else if("65".equalsIgnoreCase(productId)){
					sql=getQuery("motor.report.portfolio", new Object[]{(StringUtils.isBlank(issuer)?"":"gotherPolicyNo".equalsIgnoreCase(searchField)?"":" AND HPM.APPLICATION_ID IN ('"+issuer+"') "),LOGINID_SQL_CONDITION});
					args=new String[]{loginId,productId,StringUtils.isBlank(schemeId)?"1":schemeId,loginBranch};
				}
				else { //"30".equals(productId)
					sql=getQuery(DBConstants.TRAVEL_REPORT_PORTFOLIO, new Object[]{(StringUtils.isBlank(issuer)?"":"gotherPolicyNo".equalsIgnoreCase(searchField)?"":" AND HPM.APPLICATION_ID IN ('"+issuer+"') "),LOGINID_SQL_CONDITION});
					args=new String[]{loginId,productId,schemeId};
				}
			}else if("RP".equalsIgnoreCase(menuType) || "RC".equalsIgnoreCase(menuType) || "RE".equalsIgnoreCase(menuType)){
				if("RP".equalsIgnoreCase(menuType)) {
					sql=getQuery("GET_MOTOR_RENEWAL_DUE_LIST");
					args=new String[]{loginId};
				}else if("RC".equalsIgnoreCase(menuType)) {
					sql=getQuery("GET_MOTOR_RENEWAL_COMPLETED_LIST");
					args=new String[]{loginId};
				}else if("RE".equalsIgnoreCase(menuType)) {
					sql=getQuery("GET_MOTOR_RENEWAL_EXPIRY_LIST");
					args=new String[]{loginId};
				}
			} else if("PC".equalsIgnoreCase(menuType)){
				if("11".equals(productId)){
					sql=getQuery("GET_PORTFOLIO_CANCELLED_OC", new Object[]{"gotherPolicyNo".equalsIgnoreCase(searchField)?"":"A.LOGIN_ID='"+loginId+"' AND ","PC".equalsIgnoreCase(menuType)?"D":"P"});
					openCoverNo="%"+getOriginalPolicyNo(openCoverNo)+"%";
					args=new String[]{productId,StringUtils.isBlank(issuer)?"1":issuer,StringUtils.isBlank(issuer)?"1":"gotherPolicyNo".equalsIgnoreCase(searchField)?"":issuer,StringUtils.isBlank(openCoverNo)?"":openCoverNo};					
				} else if("3".equals(productId)) {
					sql=getQuery("GET_PORTFOLIO_CANCELLED", new Object[]{"gotherPolicyNo".equalsIgnoreCase(searchField)?"":"A.LOGIN_ID='"+loginId+"' AND ","PC".equalsIgnoreCase(menuType)?"D":"P"});
					args=new String[]{productId,StringUtils.isBlank(issuer)?"1":issuer,StringUtils.isBlank(issuer)?"":"gotherPolicyNo".equalsIgnoreCase(searchField)?"":issuer,StringUtils.isBlank(openCoverNo)?"":openCoverNo};
				}
				else if("65".equals(productId)) {
					sql=getQuery("motor.report.portfolio.pending", new Object[]{(StringUtils.isBlank(issuer)?"":"gotherPolicyNo".equalsIgnoreCase(searchField)?"":" AND HPM.APPLICATION_ID IN ('"+issuer+"') "),LOGINID_SQL_CONDITION});
					args=new String[]{loginId,productId,schemeId,loginBranch};
				}
				else if("30".equals(productId)) {
					sql=getQuery("home.report.portfolio.pending", new Object[]{(StringUtils.isBlank(issuer)?"":"gotherPolicyNo".equalsIgnoreCase(searchField)?"":" AND HPM.APPLICATION_ID IN ('"+issuer+"') "),LOGINID_SQL_CONDITION});
					args=new String[]{loginId,productId,loginBranch};
				}
				
			} else if("BP".equalsIgnoreCase(menuType)) {
				if("3".equals(productId) || "11".equals(productId)) {
					
				} else { //"30".equals(productId)
					sql=getQuery("GET_MOTOR_ISSUERPORTFOLIO", new Object[]{(StringUtils.isBlank(issuer)?"":"gotherPolicyNo".equalsIgnoreCase(searchField)?"":" AND HPM.APPLICATION_ID NOT IN ('"+issuer+"') ")});
					if("30".equals(productId)) {
						sql+=" and hpm.scheme_id='" + schemeId + "'";
					}
					args=new String[]{loginId,productId};
				}
			} else if ("QL".equalsIgnoreCase(menuType)) {
				if("3".equals(productId) || "11".equals(productId)) {
					sql=getQuery(DBConstants.REPORT_QUOTEREG_LAPSED);
				} else if("65".equals(productId)) {
					sql = getQuery("motor.report.quoteregister.lapsed", new Object[]{ISSUER_SQL_CONDITION, homeSQLCondition,LOGINID_SQL_CONDITION});
					args=new String[]{loginId,productId,loginBranch};
				}
				else if("30".equals(productId)) {
					sql = getQuery("home.report.quoteregister.lapsed", new Object[]{ISSUER_SQL_CONDITION, homeSQLCondition,LOGINID_SQL_CONDITION});
					args=new String[]{loginId,productId,loginBranch};
				}
				else { //"30".equals(productId)
					sql = getQuery(DBConstants.TRAVEL_REPORT_QUOTEREG_LAPSED, new Object[]{ISSUER_SQL_CONDITION, homeSQLCondition,LOGINID_SQL_CONDITION});
					args=new String[]{loginId,productId};
				}
			} else if ("QE".equalsIgnoreCase(menuType)) {
				if("3".equals(productId) || "11".equals(productId)) {
					sql=getQuery(DBConstants.REPORT_QUOTEREG_EXISTING);
				} else if ("65".equals(productId)) {
					sql=getQuery("motor.quoteregister.existing", new Object[]{ISSUER_SQL_CONDITION, homeSQLCondition,LOGINID_SQL_CONDITION});
					args=new String[]{loginId,productId,loginBranch};
				}else if ("30".equals(productId)) {
					sql=getQuery("home.quoteregister.existing", new Object[]{ISSUER_SQL_CONDITION, homeSQLCondition,LOGINID_SQL_CONDITION});
					args=new String[]{loginId,productId,loginBranch};
				}
				else { //"30".equals(productId)
					sql=getQuery(DBConstants.TRAVEL_QUOTEREG_EXISTING, new Object[]{ISSUER_SQL_CONDITION, homeSQLCondition,LOGINID_SQL_CONDITION});
					args=new String[]{loginId,productId};
				}
			} else if("BQE".equalsIgnoreCase(menuType)) {
				if("3".equals(productId) || "11".equals(productId)) {
					
				} else { //"30".equals(productId)
					sql=getQuery("GET_B2C_EXISTINGQUOTE_LIST", new Object[]{(StringUtils.isBlank(issuer)?"1":issuer)});
					if("30".equals(productId)) {
						sql+=" and a.scheme_id='" + schemeId + "'";
					}
					args=new String[]{loginId,productId};
				}
			} else if ("RP".equalsIgnoreCase(menuType)) {
				if("3".equals(productId) || "11".equals(productId)) {
					
				}else if("65".equals(productId)){
					sql=getQuery("GET_MOTOR_RENEWALPENDING_LIST", new Object[]{ISSUER_SQL_CONDITION, homeSQLCondition,LOGINID_SQL_CONDITION});
					args=new String[]{loginId,productId,loginBranch};
				} else { //"30".equals(productId)
					sql=getQuery("GET_HOME_RENEWALPENDING_LIST", new Object[]{ISSUER_SQL_CONDITION, homeSQLCondition,LOGINID_SQL_CONDITION});
					args=new String[]{loginId,productId};
				}
			} else if ("BRP".equalsIgnoreCase(menuType)) {
				if("3".equals(productId) || "11".equals(productId)) {
					
				} else { //"30".equals(productId)
					sql=getQuery("GET_HOME_RENEWALPENDING_LIST", new Object[]{"", homeSQLCondition,LOGINID_SQL_CONDITION});
					args=new String[]{loginId,productId};
				}
			} else if ("RU".equalsIgnoreCase(menuType)) {
				if("3".equals(productId) || "11".equals(productId)) {
					sql=getQuery(DBConstants.REPORT_REFERRAL_UNAPPROVED);
				}else if("65".equals(productId)) { 
					sql=getQuery("motor.report.referral.unapproved", new Object[]{ISSUER_SQL_CONDITION,homeSQLCondition,LOGINID_SQL_CONDITION});
					args=new String[]{loginId,productId,loginBranch};
				}else if("30".equals(productId)) { 
					sql=getQuery("home.report.referral.unapproved", new Object[]{homeSQLCondition,LOGINID_SQL_CONDITION});
					args=new String[]{loginId,productId,loginBranch};
				}
				else { //"30".equals(productId)
				
					sql=getQuery(DBConstants.TRAVEL_REPORT_REFERRAL_UNAPPROVED, new Object[]{ISSUER_SQL_CONDITION,homeSQLCondition,LOGINID_SQL_CONDITION});
					args=new String[]{loginId,productId};
					/*if(StringUtils.isNotBlank(issuer)) {
						sql=getQuery("GET_MOTOR_ISSUER_RUQUOTE_LIST", Locale.ENGLISH, new String[]{(StringUtils.isBlank(issuer)?" AND A.APPLICATION_ID IN ('1') ":" AND A.APPLICATION_ID IN ('"+issuer+"') ")});
					} else {
						sql=getQuery("GET_MOTOR_RUQUOTE_LIST", Locale.ENGLISH, new String[]{(StringUtils.isBlank(issuer)?"":"")});
						if("30".equals(productId)) {
							sql+=" and a.scheme_id='" + schemeId + "'";
						}
					}
					sql=getQuery("GET_MOTOR_RUQUOTE_LIST", Locale.ENGLISH, new String[]{(StringUtils.isBlank(issuer)?"":"")});
					if("30".equals(productId)) {
						sql+=" and a.scheme_id='" + schemeId + "'";
					}*/
					args=new String[]{loginId,productId};
				}
			} else if("BRU".equalsIgnoreCase(menuType) && StringUtils.isNotBlank(issuer)) {
				if("3".equals(productId) || "11".equals(productId)) {
					
				} else { //"30".equals(productId)
					sql=getQuery("GET_MOTOR_ISSUER_RUQUOTE_LIST", new Object[]{(StringUtils.isBlank(issuer)?" AND A.APPLICATION_ID NOT IN ('1') ":" AND A.APPLICATION_ID NOT IN ('"+issuer+"') ")});
					if("30".equals(productId)) {
						sql+=" and a.scheme_id='" + schemeId + "'";
					}
					args=new String[]{loginId,productId};
				}
			} else if ("RA".equalsIgnoreCase(menuType)) {
				if("3".equals(productId) || "11".equals(productId)) {
					sql=getQuery(DBConstants.REPORT_REFERRAL_APPROVED);
				} else if("65".equals(productId)){
					sql=getQuery("motor.report.referral.approved", new Object[]{ISSUER_SQL_CONDITION,homeSQLCondition,LOGINID_SQL_CONDITION});
					args=new String[]{loginId,productId,loginBranch};
				}else if("30".equals(productId)){
					sql=getQuery("home.report.referral.approved", new Object[]{ISSUER_SQL_CONDITION,homeSQLCondition,LOGINID_SQL_CONDITION});
					args=new String[]{loginId,productId,loginBranch};
				}
				else { //"30".equals(productId)
					sql=getQuery(DBConstants.TRAVEL_REPORT_REFERRAL_APPROVED, new Object[]{ISSUER_SQL_CONDITION,homeSQLCondition,LOGINID_SQL_CONDITION});
					args=new String[]{loginId,productId};
				}
			}else if ("RR".equalsIgnoreCase(menuType)) {
				if("3".equals(productId) || "11".equals(productId)) {
					sql=getQuery(DBConstants.REPORT_REFERRAL_REJECT);
				} else if("65".equals(productId)){
					sql=getQuery("motor.report.referral.reject", new Object[]{ISSUER_SQL_CONDITION,homeSQLCondition,LOGINID_SQL_CONDITION});
					args=new String[]{loginId,productId,loginBranch};
				}else if("30".equals(productId)){
					sql=getQuery("home.report.referral.reject", new Object[]{ISSUER_SQL_CONDITION,homeSQLCondition,LOGINID_SQL_CONDITION});
					args=new String[]{loginId,productId,loginBranch};
				}
				else { //"30".equals(productId)
					sql=getQuery(DBConstants.TRAVEL_REPORT_REFERRAL_REJECT, new Object[]{ISSUER_SQL_CONDITION,homeSQLCondition,LOGINID_SQL_CONDITION});
					args=new String[]{loginId,productId};
				}
			}else if ("L".equalsIgnoreCase(menuType)) {
				if("3".equals(productId) || "11".equals(productId)) {
					sql=getQuery(DBConstants.REPORT_LAPSEDQUOTE);
				} else if("65".equals(productId)) {
					sql=getQuery("motor.report.lapsedquote", new Object[]{ISSUER_SQL_CONDITION,homeSQLCondition,LOGINID_SQL_CONDITION});
					args=new String[]{loginId,productId,loginBranch};
				}else if("30".equals(productId)) {
					sql=getQuery("home.report.lapsedquote", new Object[]{ISSUER_SQL_CONDITION,homeSQLCondition,LOGINID_SQL_CONDITION});
					args=new String[]{loginId,productId,loginBranch};
				}
				else { //"30".equals(productId)
					sql=getQuery(DBConstants.TRAVEL_REPORT_LAPSEDQUOTE, new Object[]{ISSUER_SQL_CONDITION,homeSQLCondition,LOGINID_SQL_CONDITION});
					args=new String[]{loginId,productId};
				}
			}else if("T".equalsIgnoreCase(menuType)){
				sql=getQuery(DBConstants.REPORT_TRANSACTION);
				args=new String[]{openCoverNo};
			}else if("PD".equalsIgnoreCase(menuType)){
				sql=getQuery(DBConstants.REPORT_DECLARATION_POLICY);
				args=new String[]{loginId,productId,issuer==null?"":issuer,openCoverNo,openCoverNo};
			}else if("C".equalsIgnoreCase(menuType)){
				if(StringUtils.isNotEmpty(openCoverNo)){
					sql=getQuery(DBConstants.OPENCOVER_CUSTOMER_LIST);
					args=new String[]{openCoverNo};
				}else{
					sql=getQuery(DBConstants.CUSTOMER_SELECTION);
					args=new String[]{loginId};
				}
			}else if("R".equalsIgnoreCase(menuType)){
				if(StringUtils.isEmpty(issuer)){
					sql=getQuery(DBConstants.REPORT_REPORT);
					args=new String[]{loginId,loginId}; 
				}else{
					sql=getQuery(DBConstants.REPORT_USERLIST_ISSUER);
					args=new String[]{issuer,productId};					
				}
			}else if("PR".equalsIgnoreCase(menuType)){
				if("3".equals(productId) || "11".equals(productId)) {
					if(StringUtils.isEmpty(issuer)){
						sql=getQuery(DBConstants.REPORT_POLICY);
						args=new String[]{branchCode,startDate,endDate,userLogin,loginId,productId};
					}else{
						sql=getQuery(DBConstants.REPORT_POLICY_ISSUER);
						args=new String[]{branchCode,startDate,endDate,userLogin,loginId,productId};
					}
					/*sql = "Select * from table(broker_reports(?,?,?,?,?,?))";
						args=new String[]{loginId,branchCode,startDate,endDate,"ALL",productId};*/
				} else if("65".equals(productId)){
					sql=getQuery("motor.report.policy");
					args=new String[]{startDate,endDate,loginId,loginId,productId, schemeId,loginBranch};
				}else if("30".equals(productId)){
					sql=getQuery("home.report.policy");
					args=new String[]{startDate,endDate,loginId,productId,loginBranch};
				}
				else { //"30".equals(productId)
					sql=getQuery(DBConstants.TRAVEL_REPORT_POLICY);
					args=new String[]{startDate,endDate,loginId,productId, schemeId};
				}
			}else if("E".equalsIgnoreCase(menuType)){
				if("3".equals(productId) || "11".equals(productId)) {
					sql=getQuery("GET_END_LIST");
					args=new String[]{loginId, productId,StringUtils.isBlank(policyNo)?"":policyNo};
				} else if("65".equals(productId)) {
					sql=getQuery("GET_MOTOR_END_LIST");
					args=new String[]{policyNo, productId,vehicleId};
				}else { //"30".equals(productId)
					sql=getQuery("GET_TRAVEL_END_LIST");
					args=new String[]{policyNo, productId};
				}
			}else if("PE".equalsIgnoreCase(menuType)){
				sql=getQuery("GET_ENDT_LIST_ISSUER");
				if("11".equals(productId)){
					openCoverNo=getOriginalPolicyNo(openCoverNo);
				}
				args=new String[]{loginId, productId,issuer,openCoverNo};
			}
			//}
			if(!"R".equalsIgnoreCase(menuType)&&!"PR".equalsIgnoreCase(menuType)){
				String str="like '%'||upper(?)||'%'";
				if(searchField!=null && searchString!=null && searchOper!=null){
					if("nc".equalsIgnoreCase(searchOper))
						str="not like '%'||upper(?)||'%'";
					else if("cn".equalsIgnoreCase(searchOper))
						str="like '%'||upper(?)||'%'";
					else if("eq".equalsIgnoreCase(searchOper))
						str=" = upper(?)?";
					else if("nq".equalsIgnoreCase(searchOper))
						str=" != upper(?)";

					if("gquoteNo".equalsIgnoreCase(searchField))
						sql+=" and upper(A.QUOTE_NO) "+str;
					else if("gcustName".equalsIgnoreCase(searchField)){
						if("P".equals(menuType) || "QL".equals(menuType) || "PE".equals(menuType))
							sql+=" and upper(NVL (b.COMPANY_NAME, b.FIRST_NAME)) "+str;
						else if("QE".equals(menuType))
							sql+=" and upper(NVL (b.COMPANY_NAME, b.FIRST_NAME)) "+str;
						else if("L".equals(menuType) || "RU".equals(menuType) || "RR".equals(menuType))
							sql+=" and upper(B.FIRST_NAME) "+str;
					}
					else if("gpolicyNo".equalsIgnoreCase(searchField)||"gotherPolicyNo".equalsIgnoreCase(searchField))
						sql+=" and upper(POLICY_NO) "+str;
					else if("gocCustName".equalsIgnoreCase(searchField))
						sql+=" and upper(OPENCOVER_CUST_NAME) "+str;
					else if("gfirstName".equalsIgnoreCase(searchField))
						sql+=" and upper(FIRST_NAME) "+str;
					else if("gcustomerCivilId".equalsIgnoreCase(searchField))
						sql+=" and upper(CUSTOMER_SOURCE) "+str;
					//sql+=" and upper(CUSTOMER_SOURCE) "+str;
					String[] newArray = new String[args.length+1];  
					for(int cnt=0;cnt<args.length;cnt++){  
						newArray[cnt] = args[cnt];  
					}  
					newArray[args.length]=searchString;
					args=newArray;
				}
				if("RP".equalsIgnoreCase(menuType) || "BRP".equalsIgnoreCase(menuType) || "RE".equalsIgnoreCase(menuType) || "RC".equalsIgnoreCase(menuType)) {
					if("3".equals(productId) || "11".equals(productId)) {
						
					} else { //"30".equals(productId)
						sql += " ORDER BY POLICY_END_DATE ASC";
					}
					
				} else if(!"PE".equalsIgnoreCase(menuType) && !"E".equalsIgnoreCase(menuType) && !"PD".equalsIgnoreCase(menuType) && !"T".equalsIgnoreCase(menuType)){
					if("C".equalsIgnoreCase(menuType))
						sql+=" ORDER BY FIRST_NAME";
					else if("P".equalsIgnoreCase(menuType) || "PC".equalsIgnoreCase(menuType) || "BP".equals(menuType)  )
						if("3".equals(productId) || "11".equals(productId)) {
							sql+=" ORDER BY INCEPTION_DATE DESC";
						} else { //"30".equals(productId)
							sql += " ORDER BY HPM.INCEPTION_DATE DESC";
						}
					else
						sql+=" ORDER BY QUOTE_NO DESC";
				} else if(!"T".equalsIgnoreCase(menuType) && !"PD".equalsIgnoreCase(menuType) && !"E".equalsIgnoreCase(menuType))
					sql+=" ORDER BY A.QUOTE_NO DESC";

				/*	if(!"PE".equalsIgnoreCase(menuType) && !"E".equalsIgnoreCase(menuType) && !"PD".equalsIgnoreCase(menuType)){
					if("C".equalsIgnoreCase(menuType))
						sql+=" ORDER BY FIRST_NAME";
					else
						sql+=" ORDER BY QUOTE_NO DESC";
				}else if(!"P".equals(menuType) && !"E".equalsIgnoreCase(menuType) && !"PE".equalsIgnoreCase(menuType))
					sql+=" ORDER BY QUOTE_NO DESC";*/
			}
			LogManager.info("args[] ==> "+StringUtils.join(args, ","));

			removeNull(args);
			if(StringUtils.isNotEmpty(sql)){
				reportList=this.mytemplate.queryForList(sql,args);
			}
		}
		catch (Exception e)
		{			
			LogManager.debug("EXCEPTION @ { " + e + " }");
		}		
		LogManager.info("Query===>" + sql);		
		LogManager.info("getReportList() - Exit || Result: " + reportList.size());
		LogManager.popRemove();		
		return reportList;
	}


	public List<Object> getUserList(String loginId,String productId,String issuer, String searchBy, String branchCode) {
		String sql = "";
		List<Object> userList=null;	
		LogManager.info("args===>" + loginId+","+ productId+","+ issuer);
		try {
			if("3".equalsIgnoreCase(productId)||"11".equalsIgnoreCase(productId)){
				if("".equalsIgnoreCase(issuer)) {
					sql=getQuery(DBConstants.REPORT_USERLIST);
					userList=this.mytemplate.queryForList(sql,new String[]{loginId,productId});	
				} else if("OtherUsers".equalsIgnoreCase(searchBy)){
					sql=getQuery(DBConstants.ISSUER_BROKER_LIST);
					userList=this.mytemplate.queryForList(sql,new String[]{productId,issuer,branchCode});	
				} else {					
					sql=getQuery(DBConstants.REPORT_USERLIST_ISSUER);
					userList=this.mytemplate.queryForList(sql,new String[]{issuer,productId});	
				}
			}else if("65".equalsIgnoreCase(productId)){

				if("".equalsIgnoreCase(issuer)) {
					sql=getQuery(DBConstants.TRAVEL_REPORT_USERLIST);
					userList=this.mytemplate.queryForList(sql,new String[]{loginId,productId});	
				} else if("OtherUsers".equalsIgnoreCase(searchBy)){
					sql=getQuery("travel.issuer.brokerList");
					userList=this.mytemplate.queryForList(sql,new String[]{productId,issuer,branchCode});	
				}
				else {					
					sql=getQuery("motor.userlist.issuer");
					userList=this.mytemplate.queryForList(sql,new String[]{productId});
				}
			
			}
			else {
				if("".equalsIgnoreCase(issuer)) {
					sql=getQuery(DBConstants.TRAVEL_REPORT_USERLIST);
					userList=this.mytemplate.queryForList(sql,new String[]{loginId,productId});	
				} else if("OtherUsers".equalsIgnoreCase(searchBy)){
					sql=getQuery("travel.issuer.brokerList");
					userList=this.mytemplate.queryForList(sql,new String[]{productId,issuer,branchCode});	
				}
				else {					
					sql=getQuery("travel.userlist.issuer");
					userList=this.mytemplate.queryForList(sql,new String[]{issuer,productId});
				}
			}
		}
		catch (Exception e) {			
			LogManager.debug("EXCEPTION @ { " + e + " }");
		}		
		LogManager.info("Query===>" + sql);		
		LogManager.info("getUserList() - Exit || Result: " + userList.size());
		LogManager.popRemove();		
		return userList;
	}
	public List<Object> getDeclarationPolicyList(String policyNo)	
	{
		String sql = "";
		List<Object> declarationList=null;	
		LogManager.info("args===>" + policyNo);
		try
		{
			sql=getQuery(DBConstants.DECLARATION_POLICY_INDIVIDUAL);
			declarationList=this.mytemplate.queryForList(sql,new String[]{policyNo,policyNo});				

		}
		catch (Exception e) 
		{			
			LogManager.debug("EXCEPTION @ { " + e + " }");
		}		
		LogManager.info("Query===>" + sql);		
		LogManager.info("getDeclarationPolicyList() - Exit || Result: " + declarationList.size());
		LogManager.popRemove();		
		return declarationList;
	}
	public List<Object> getLapsedReason(String branchCode)	
	{
		String sql = "";
		List<Object> lapsedReason=null;			
		try
		{
			sql=getQuery(DBConstants.LAPSED_REASON);
			lapsedReason=this.mytemplate.queryForList(sql,new String[]{branchCode,branchCode});				

		}
		catch (Exception e) 
		{			
			LogManager.debug("EXCEPTION @ { " + e + " }");
		}		
		LogManager.info("Query===>" + sql);
		LogManager.info("args===>" + branchCode);
		LogManager.info("getLapsedReason() - Exit || Result: " + lapsedReason.size());
		LogManager.popRemove();		
		return lapsedReason;
	}
	public List<Object> getLapsedQuote(String quoteNo,String productId) {
		String sql = "";
		List<Object> lapsedQuote=null;			
		try {
			if("3".equals(productId) || "11".equals(productId)) {
				sql=getQuery(DBConstants.QUOTE_DETAIL);
			} else { //"30".equals(productId)
				sql=getQuery(DBConstants.TRAVEL_QUOTE_DETAIL);
			}
			lapsedQuote=this.mytemplate.queryForList(sql,new String[]{quoteNo});				

		} catch (Exception e) {		
			LogManager.debug("EXCEPTION @ { " + e + " }");
		}		
		LogManager.info("Query===>" + sql);	
		LogManager.info("args===>" + quoteNo);
		LogManager.info("getLapsedQuote() - Exit || Result: " + lapsedQuote.size());
		LogManager.popRemove();		
		return lapsedQuote;
	}
	public int updateLabsed(String quoteNo,String remarks,String loginId,String productId)	
	{
		String sql = "";
		int result=0;			
		try
		{
			if("3".equals(productId) || "11".equals(productId)) {
				sql=getQuery(DBConstants.LAPSED_UPDATE);
			} else { //"30".equals(productId)
				sql=getQuery(DBConstants.TRAVEL_LAPSED_UPDATE);
			}
			result=this.mytemplate.update(sql,new String[]{remarks,loginId,quoteNo});					
		}
		catch (Exception e) 
		{			
			LogManager.debug("EXCEPTION @ { " + e + " }");
		}		
		LogManager.info("Query===>" + sql);	
		LogManager.info("args===>" + quoteNo);
		LogManager.info("getLapsedQuote() - Exit || Result: " + result);
		LogManager.popRemove();		
		return result;
	}
	public int activeLapsed(String quoteNo, String productId)	
	{
		String sql = "";
		int result=0;			
		try {
			if("3".equals(productId) || "11".equals(productId)) {
				sql=getQuery(DBConstants.ACTIVE_LAPSED);
			} else { //"30".equals(productId)
				sql=getQuery(DBConstants.TRAVEL_ACTIVE_LAPSED);
			}
			result=this.mytemplate.update(sql,new String[]{quoteNo});					
		}
		catch (Exception e) 
		{			
			LogManager.debug("EXCEPTION @ { " + e + " }");
		}		
		LogManager.info("Query===>" + sql);	
		LogManager.info("args===>" + quoteNo);
		LogManager.info("getLapsedQuote() - Exit || Result: " + result);
		LogManager.popRemove();		
		return result;
	}
	public List<Map<String,Object>> getSearchResult(ReportAction action, String userType, String productId) {
		LogManager.info("getSearchResult() - Enter");
		List<Map<String,Object>> result=new ArrayList<Map<String,Object>>();			
		try {
			String sql = "";
			Object[] args = null;
			if("3".equals(productId) || "11".equals(productId)) {
				args = new String[]{action.getLoginId(),action.getProductId(),action.getIssuer(),action.getOpenCoverNo(), "%"+action.getSearchValue()+"%"};

				if("quoteNo".equalsIgnoreCase(action.getSearchBy())){
					sql=getQuery(DBConstants.SEARCH_QUOTE);
					if("QE".equalsIgnoreCase(action.getMenuType()))
						sql=getQuery(DBConstants.SEARCH_QUOTE_EXISTING);
					else if("QL".equalsIgnoreCase(action.getMenuType()))
						sql=getQuery(DBConstants.SEARCH_QUOTE_LAPSED);
					else if("L".equalsIgnoreCase(action.getMenuType()))
						sql=getQuery(DBConstants.SEARCH_QUOTE_REJECT);
					else if("RA".equalsIgnoreCase(action.getMenuType()))
						sql=getQuery(DBConstants.SEARCH_REFERRAL_APPROVED);
					else if("RU".equalsIgnoreCase(action.getMenuType()))
						sql=getQuery(DBConstants.SEARCH_REFERRAL_UNAPPROVED);
					else if("RR".equalsIgnoreCase(action.getMenuType()))
						sql=getQuery(DBConstants.SEARCH_REFERRAL_REJECT);
					else if("P".equalsIgnoreCase(action.getMenuType())){
						sql=getQuery(DBConstants.SEARCH_PORTFOLIO);
						args=new String[]{action.getLoginId(),action.getProductId(),action.getIssuer(),action.getOpenCoverNo(),action.getSearchBy(), "%"+action.getSearchValue()+"%"};
					}else
						args=new String[]{action.getSearchValue(), action.getLoginId(), action.getProductId(),action.getOpenCoverNo(),"",StringUtils.isBlank(action.getIssuer())?"":action.getIssuer()};

				}else if("policyNo".equalsIgnoreCase(action.getSearchBy())){
					if(StringUtils.isEmpty(action.getIssuer()) && StringUtils.isEmpty(action.getLoginId())){
						sql=getQuery(DBConstants.SEARCH_POLICY);
					}else{
						sql=getQuery("GET_PORTFOLIO_ISSUER");
						if(!"BS".equalsIgnoreCase(action.getSearchFrom()) && !"S".equalsIgnoreCase(action.getSearchFrom()) && "11".equals(action.getProductId())){
							sql=getQuery("GET_PORTFOLIO_SEARCH_ISSUER");
							args=new String[]{action.getBranchCode(),action.getProductId(),getOriginalPolicyNo(action.getOpenCoverNo()),"%"+action.getSearchValue()+"%"};
						}else{
							if(!"RSAIssuer".equals(userType))
								sql+=" AND A.application_id='1'";
							if("User".equals(userType))
								sql+=" AND A.LOGIN_ID='"+action.getLoginId()+"'";
							args=new String[]{action.getBranchCode(),action.getProductId(),"%"+action.getSearchValue()+"%"};
						}
						if(StringUtils.isNotBlank(sql)){
							sql=sql+" ORDER BY SUBSTR (A.POLICY_NO, 9, 16) DESC";
						}
					}
				}else if("custName".equalsIgnoreCase(action.getSearchBy())){
					sql=getQuery(DBConstants.SEARCH_CUSTOMER);
					args=new String[]{action.getLoginId(),action.getProductId(),action.getIssuer(),action.getOpenCoverNo(), "%"+action.getSearchValue()+"%","%"+action.getSearchValue()+"%"};
					if("QE".equalsIgnoreCase(action.getMenuType()))
						sql=getQuery(DBConstants.SEARCH_CUSTOMER_QUOTE_EXISTING);
					else if("QL".equalsIgnoreCase(action.getMenuType()))
						sql=getQuery(DBConstants.SEARCH_CUSTOMER_QUOTE_LAPSED);
					else if("L".equalsIgnoreCase(action.getMenuType()))
						sql=getQuery(DBConstants.SEARCH_CUSTOMER_QUOTE_REJECT);
					else if("RA".equalsIgnoreCase(action.getMenuType()))
						sql=getQuery(DBConstants.SEARCH_CUSTOMER_REFERRAL_APPROVED);
					else if("RU".equalsIgnoreCase(action.getMenuType()))
						sql=getQuery(DBConstants.SEARCH_CUSTOMER_REFERRAL_UNAPPROVED);
					else if("RR".equalsIgnoreCase(action.getMenuType()))
						sql=getQuery(DBConstants.SEARCH_CUSTOMER_REFERRAL_REJECT);
					else if("P".equalsIgnoreCase(action.getMenuType())){
						sql=getQuery(DBConstants.SEARCH_PORTFOLIO);
						args=new String[]{action.getLoginId(),action.getProductId(),action.getIssuer(),action.getOpenCoverNo(),action.getSearchBy(), "%"+action.getSearchValue()+"%"};
					}else
						args=new String[]{action.getSearchValue(), action.getLoginId(), action.getProductId(),action.getOpenCoverNo(),"customer",StringUtils.isBlank(action.getIssuer())?"":action.getIssuer()};
				}else if("otherUsers".equalsIgnoreCase(action.getSearchBy())){
					sql=getQuery(DBConstants.SEARCH_QUOTE_OTHERS);
				}
			} 
			else if("65".equals(productId)) {
				args=new String[]{action.getLoginId(),action.getProductId(),action.getIssuer(),action.getOpenCoverNo(), "%"+action.getSearchValue()+"%"};

				if("quoteNo".equalsIgnoreCase(action.getSearchBy())) {
					sql=getQuery("motor.report.searchByQuote");
					args=new String[]{action.getLoginId(),action.getProductId(),action.getLoginBranch(),action.getIssuer(), "%"+action.getSearchValue()+"%"};
				} else if("policyNo".equalsIgnoreCase(action.getSearchBy())) {
					if(StringUtils.isEmpty(action.getIssuer())){
						sql=getQuery("motor.report.searchByPolicy");
						args=new String[]{action.getLoginId(),action.getProductId(),action.getLoginBranch(),action.getIssuer(), "%"+action.getSearchValue()+"%"};
					} else {
						sql=getQuery("GET_PORTFOLIO_ISSUER");
						if(!"BS".equalsIgnoreCase(action.getSearchFrom()) && !"S".equalsIgnoreCase(action.getSearchFrom()) && "11".equals(action.getProductId())){
							args=new String[]{action.getLoginBranch(),action.getProductId(),"%"+getOriginalPolicyNo(action.getOpenCoverNo())+"-"+action.getSearchValue()+"%"};
						}else{
							args=new String[]{action.getLoginBranch(),action.getProductId(),"%"+action.getSearchValue()+"%"};
						}
					}
				} else if("custName".equalsIgnoreCase(action.getSearchBy())) {
					sql=getQuery("motor.report.searchByCustomer");
					args=new String[]{action.getLoginId(),action.getProductId(),action.getLoginBranch(),action.getIssuer(),"%"+action.getSearchValue()+"%","%"+action.getSearchValue()+"%"};
				} else if("otherUsers".equalsIgnoreCase(action.getSearchBy())) {
					sql=getQuery(DBConstants.TRAVEL_SEARCH_QUOTE_OTHERS);
				}
				else if("nrc".equalsIgnoreCase(action.getSearchBy())) {
					sql=getQuery("motor.report.searchByNrc");
					args=new String[]{action.getLoginId(),action.getProductId(),action.getLoginBranch(),action.getIssuer(), "%"+action.getSearchValue()+"%","%"+action.getSearchValue()+"%"};
				}
				else if("comRegNo".equalsIgnoreCase(action.getSearchBy())) {
					sql=getQuery("motor.report.searchByCompRegNo");
					args=new String[]{action.getLoginId(),action.getProductId(),action.getLoginBranch(),action.getIssuer(), "%"+action.getSearchValue()+"%"};
				}
			}
			else if("30".equals(productId)) {
				args=new String[]{action.getLoginId(),action.getProductId(),action.getIssuer(),action.getOpenCoverNo(), "%"+action.getSearchValue()+"%"};

				if("quoteNo".equalsIgnoreCase(action.getSearchBy())) {
					sql=getQuery("home.report.searchByQuote");
					args=new String[]{action.getLoginId(),action.getProductId(),action.getLoginBranch(),action.getIssuer(), "%"+action.getSearchValue()+"%"};
				} else if("policyNo".equalsIgnoreCase(action.getSearchBy())) {
					if(StringUtils.isEmpty(action.getIssuer())){
						sql=getQuery("home.report.searchByPolicy");
						args=new String[]{action.getLoginId(),action.getProductId(),action.getLoginBranch(),action.getIssuer(), "%"+action.getSearchValue()+"%"};
					} else {
						sql=getQuery("GET_PORTFOLIO_ISSUER");
						if(!"BS".equalsIgnoreCase(action.getSearchFrom()) && !"S".equalsIgnoreCase(action.getSearchFrom()) && "11".equals(action.getProductId())){
							args=new String[]{action.getLoginBranch(),action.getProductId(),"%"+getOriginalPolicyNo(action.getOpenCoverNo())+"-"+action.getSearchValue()+"%"};
						}else{
							args=new String[]{action.getLoginBranch(),action.getProductId(),"%"+action.getSearchValue()+"%"};
						}
					}
				} else if("custName".equalsIgnoreCase(action.getSearchBy())) {
					sql=getQuery("home.report.searchByCustomer");
					args=new String[]{action.getLoginId(),action.getProductId(),action.getLoginBranch(),action.getIssuer(),"%"+action.getSearchValue()+"%","%"+action.getSearchValue()+"%"};
				} else if("otherUsers".equalsIgnoreCase(action.getSearchBy())) {
					sql=getQuery(DBConstants.TRAVEL_SEARCH_QUOTE_OTHERS);
				}
			}
			else { //"30".equals(productId)
				args=new String[]{action.getLoginId(),action.getProductId(),action.getIssuer(),action.getOpenCoverNo(), "%"+action.getSearchValue()+"%"};

				if("quoteNo".equalsIgnoreCase(action.getSearchBy())) {
					sql=getQuery(DBConstants.TRAVEL_SEARCH_QUOTE);
					args=new String[]{action.getLoginId(),action.getProductId(),action.getIssuer(), "%"+action.getSearchValue()+"%"};
				} else if("policyNo".equalsIgnoreCase(action.getSearchBy())) {
					if(StringUtils.isEmpty(action.getIssuer())){
						sql=getQuery(DBConstants.TRAVEL_SEARCH_POLICY);
						args=new String[]{action.getLoginId(),action.getProductId(),action.getIssuer(), "%"+action.getSearchValue()+"%"};
					} else {
						sql=getQuery("GET_PORTFOLIO_ISSUER");
						if(!"BS".equalsIgnoreCase(action.getSearchFrom()) && !"S".equalsIgnoreCase(action.getSearchFrom()) && "11".equals(action.getProductId())){
							args=new String[]{action.getBranchCode(),action.getProductId(),"%"+getOriginalPolicyNo(action.getOpenCoverNo())+"-"+action.getSearchValue()+"%"};
						}else{
							args=new String[]{action.getBranchCode(),action.getProductId(),"%"+action.getSearchValue()+"%"};
						}
					}
				} else if("custName".equalsIgnoreCase(action.getSearchBy())) {
					sql=getQuery(DBConstants.TRAVEL_SEARCH_CUSTOMER);
					args=new String[]{action.getLoginId(),action.getProductId(),action.getIssuer(),"%"+action.getSearchValue()+"%","%"+action.getSearchValue()+"%"};
				} else if("otherUsers".equalsIgnoreCase(action.getSearchBy())) {
					sql=getQuery(DBConstants.TRAVEL_SEARCH_QUOTE_OTHERS);
				}
			}
			removeNull(args);
			LogManager.info("Query=>"+sql);
			LogManager.info("args=>"+StringUtils.join(args,","));
			result=this.mytemplate.queryForList(sql,args);
		}
		catch (Exception e) 
		{			
			LogManager.debug("EXCEPTION @ { " + e + " }");
		}		
		LogManager.info("getSearchResult() - Exit || Result: " + result.size());
		LogManager.popRemove();		
		return result;
	}
	public List<Object> getEndTypeList(String productId)	
	{
		String sql = "";
		LogManager.info("getEndTypeList() - Enter");
		List<Object> list=null;			
		try{
			sql=getQuery("GET_ENDT_TYPE");
			list=this.mytemplate.queryForList(sql, new Object[]{productId});
			if(list==null || list.size()<=0){
				list=this.mytemplate.queryForList(sql, new Object[]{"03"});
			}
		}
		catch (Exception e){			
			LogManager.debug("EXCEPTION @ { " + e + " }");
		}		
		LogManager.info("Query===>" + sql);
		LogManager.info("getEndTypeList() - Exit");
		LogManager.popRemove();		
		return list;
	}
	public List<Object> getConstantList(String categoryId, String branchCode)	
	{
		String sql = "";
		LogManager.info("getConstantList() - Enter || categoryId: " + categoryId+" branchCode: "+branchCode);
		List<Object> list=null;			
		try{
			sql=getQuery("GET_CONSTANT");
			list=this.mytemplate.queryForList(sql,new String[]{branchCode,categoryId});				
		}
		catch (Exception e){			
			LogManager.debug("EXCEPTION @ { " + e + " }");
		}		
		LogManager.info("Query===>" + sql);
		LogManager.info("getConstantList() - Exit || Result: " + list);
		LogManager.popRemove();		
		return list;
	}
	public List<Object> getSearchResult(String policyNo, String branchCode, String productId)
	{
		String sql = "";
		LogManager.info("getSearchResult() - Enter");
		List<Object> result=new ArrayList<Object>();			
		try
		{
			sql=getQuery("GET_PORTFOLIO_ISSUER") +" ORDER BY SUBSTR (A.POLICY_NO, 9, 16) DESC";
			String[] args=new String[]{branchCode,productId,"%"+policyNo+"%"};
			LogManager.info("args=>"+StringUtils.join(args,","));
			for (int i = 0; i < args.length; i++) {
				if(args[i]==null){
					args[i]="";
				}
			}
			result=this.mytemplate.queryForList(sql,args);					
		}
		catch (Exception e) 
		{			
			LogManager.debug("EXCEPTION @ { " + e + " }");
		}		
		LogManager.info("getSearchResult() - Exit || Result: " + result.size());
		LogManager.popRemove();		
		return result;
	}
	public String getOriginalPolicyNo(String openCoverNo){
		LogManager.info("getOriginalPolicyNo() - Enter || openCoverNo: "+openCoverNo);
		PolicyGenerationDAO policyDAO=new PolicyGenerationDAO();
		openCoverNo=policyDAO.getValue("GET_ORIGINAL_POLNO", new String[]{openCoverNo});
		LogManager.info("getOriginalPolicyNo() - Exit || Result: "+openCoverNo);
		return openCoverNo;
	}
	public List<Map<String, Object>> getEndtPolicyInfo(String branchCode, String policyNo)	
	{
		String sql = "";
		LogManager.info("getEndtPolicyInfo() - Enter || branchCode: "+branchCode+" policyNo: "+policyNo);
		List<Map<String, Object>> info=new ArrayList<Map<String, Object>>();;			
		try{
			sql=getQuery("GET_ENDT_POL");
			info=this.mytemplate.queryForList(sql,new String[]{branchCode, policyNo});				
		}catch (Exception e){
			LogManager.debug("EXCEPTION @ { " + e + " }");
		}		
		LogManager.info("Query===>" + sql);	
		LogManager.info("getEndtPolicyInfo() - Exit || Result: " + info.size());
		LogManager.popRemove();		
		return info;
	}
	public List<Object> getPolicyEndtTypeList(String endtType)	
	{
		String sql = "";
		LogManager.info("getPolicyEndtTypeList() - Enter || endtType: "+endtType);
		List<Object> info=new ArrayList<Object>();;			
		try{
			info=(List<Object>)this.mytemplate.queryForList(getQuery("GET_POL_ENDT_LIST").replace("?",endtType));
		}catch (Exception e){			
			LogManager.debug("EXCEPTION @ { " + e + " }");
		}		
		LogManager.info("Query===>" + sql);	
		LogManager.info("getPolicyEndtTypeList() - Exit || Result: " + info.size());
		LogManager.popRemove();		
		return info;
	}
	public int[] getTRPortfolio(String loginId,String productId,String issuer,String openCoverNo, String schemeId,String loginBranch)
	{
		String sql = "";
		LogManager.info("getTRPortfolio() - Enter");
		int portfolio=0;
		int portfolioAmount=0;
		Object[] obj=null;
		try{
			if("3".equals(productId) || "11".equals(productId)){
				sql=getQuery(DBConstants.REPORT_CHART_PORTFOLIO, new Object[]{(StringUtils.isBlank(issuer)?" A.LOGIN_ID ='"+loginId+"' AND":"")});
				obj=new Object[]{productId,(StringUtils.isBlank(issuer)?"1":issuer),openCoverNo};
				LogManager.info("SQL=>"+sql);
				LogManager.info("obj=>"+StringUtils.join(obj,","));
				portfolio=this.mytemplate.queryForInt(sql,obj);
				LogManager.info("Result=>"+portfolio);
				sql=getQuery(DBConstants.REPORT_CHART_PORTFOLIO_AMOUNT, new Object[]{(StringUtils.isBlank(issuer)?" A.LOGIN_ID ='"+loginId+"' AND":"")});
				LogManager.info("SQL=>"+sql);
				LogManager.info("obj=>"+StringUtils.join(obj,","));
				portfolioAmount=this.mytemplate.queryForInt(sql,obj);
				LogManager.info("Result=>"+portfolioAmount);
			} else if("65".equals(productId)){
				sql=getQuery("motor.report.chart.portfolio", new Object[]{(StringUtils.isBlank(issuer)?" A.LOGIN_ID ='"+loginId+"' AND":"")});
				obj=new Object[]{productId,schemeId,(StringUtils.isBlank(issuer)?"1":issuer),loginBranch};
				LogManager.info("SQL=>"+sql);
				LogManager.info("obj=>"+StringUtils.join(obj,","));
				portfolio=this.mytemplate.queryForInt(sql,obj);
				LogManager.info("Result=>"+portfolio);
				sql=getQuery("motor.report.chart.portfolio.amount", new Object[]{(StringUtils.isBlank(issuer)?" A.LOGIN_ID ='"+loginId+"' AND":"")});
				LogManager.info("SQL=>"+sql);
				LogManager.info("obj=>"+StringUtils.join(obj,","));
				portfolioAmount=this.mytemplate.queryForInt(sql,obj);
				LogManager.info("Result=>"+portfolioAmount);
			}
			else { //"30".equals(productId)
				if("30".equals(productId)){
					sql=getQuery("home.report.chart.portfolio", new Object[]{(StringUtils.isBlank(issuer)?" A.LOGIN_ID ='"+loginId+"' AND":"")});
					obj=new Object[]{productId,(StringUtils.isBlank(issuer)?"1":issuer),loginBranch};
				}
				else{
					sql=getQuery(DBConstants.TRAVEL_REPORT_CHART_PORTFOLIO, new Object[]{(StringUtils.isBlank(issuer)?" A.LOGIN_ID ='"+loginId+"' AND":"")});
					obj=new Object[]{productId,schemeId,(StringUtils.isBlank(issuer)?"1":issuer)};
				}
				LogManager.info("SQL=>"+sql);
				LogManager.info("obj=>"+StringUtils.join(obj,","));
				portfolio=this.mytemplate.queryForInt(sql,obj);
				LogManager.info("Result=>"+portfolio);
				if("30".equals(productId))
					sql=getQuery("home.report.chart.portfolio.amount", new Object[]{(StringUtils.isBlank(issuer)?" A.LOGIN_ID ='"+loginId+"' AND":"")});
				else
					sql=getQuery(DBConstants.TRAVEL_REPORT_CHART_PORTFOLIO_AMOUNT, new Object[]{(StringUtils.isBlank(issuer)?" A.LOGIN_ID ='"+loginId+"' AND":"")});
				LogManager.info("SQL=>"+sql);
				LogManager.info("obj=>"+StringUtils.join(obj,","));
				portfolioAmount=this.mytemplate.queryForInt(sql,obj);
				LogManager.info("Result=>"+portfolioAmount);
			}
		}catch (Exception e){			
			LogManager.debug("EXCEPTION @ { " + e + " }");
		}
		LogManager.info("getTRPortfolio() - Exit");
		LogManager.popRemove();		
		return new int[]{portfolio,portfolioAmount};
	}
	public int[] getTRExisting(String loginId,String productId,String issuer,String openCoverNo,String schemeId,String loginBranch)
	{
		
		LogManager.info("getTRExisting() - Enter");
		int existing =0;
		int existingAmpont =0;
		Object[] obj=null;
		try{
			String sql = "";
			if("3".equals(productId) || "11".equals(productId)){
				sql=getQuery(DBConstants.REPORT_QUOTEREGISTER_CHART_EXISTING, new Object[]{(StringUtils.isBlank(issuer)?" A.LOGIN_ID ='"+loginId+"' AND":"")});
				LogManager.info("Query=>" + sql);
				obj=new Object[]{productId,(StringUtils.isBlank(issuer)?"1":issuer),openCoverNo};
				LogManager.info("obj[]=>"+StringUtils.join(obj,","));
				existing=this.mytemplate.queryForInt(sql,obj);
				LogManager.info("Result=>" + existing);
				sql=getQuery(DBConstants.REPORT_CHART_EXISTING_AMOUNT, new Object[]{(StringUtils.isBlank(issuer)?" A.LOGIN_ID ='"+loginId+"' AND":"")});
				LogManager.info("Query=>" + sql);
				LogManager.info("obj[]=>"+StringUtils.join(obj,","));
				existingAmpont=this.mytemplate.queryForInt(sql,obj);
				LogManager.info("Result=>" + existingAmpont);
			}else if("65".equals(productId)){
				sql=getQuery("motor.quoteregister.chart.existing", new Object[]{(StringUtils.isBlank(issuer)?" A.LOGIN_ID ='"+loginId+"' AND":"")});
				LogManager.info("Query=>" + sql);
				obj=new Object[]{productId,schemeId,(StringUtils.isBlank(issuer)?"1":issuer),loginBranch};
				LogManager.info("obj[]=>"+StringUtils.join(obj,","));
				existing=this.mytemplate.queryForInt(sql,obj);
				LogManager.info("Result=>" + existing);
				sql=getQuery("motor.quoteregister.chart.existing.amount", new Object[]{(StringUtils.isBlank(issuer)?" A.LOGIN_ID ='"+loginId+"' AND":"")});
				LogManager.info("Query=>" + sql);
				LogManager.info("obj[]=>"+StringUtils.join(obj,","));
				existingAmpont=this.mytemplate.queryForInt(sql,obj);
				LogManager.info("Result=>" + existingAmpont);
			}
			else
			{
				if("30".equals(productId)){
					sql=getQuery("home.quoteregister.chart.existing", new Object[]{(StringUtils.isBlank(issuer)?" A.LOGIN_ID ='"+loginId+"' AND":"")});
					obj=new Object[]{productId,(StringUtils.isBlank(issuer)?"1":issuer),loginBranch};
				}
				else{
					sql=getQuery(DBConstants.TRAVEL_QUOTEREGISTER_CHART_EXISTING, new Object[]{(StringUtils.isBlank(issuer)?" A.LOGIN_ID ='"+loginId+"' AND":"")});
					obj=new Object[]{productId,schemeId,(StringUtils.isBlank(issuer)?"1":issuer)};
				}
				LogManager.info("Query=>" + sql);
				LogManager.info("obj[]=>"+StringUtils.join(obj,","));
				existing=this.mytemplate.queryForInt(sql,obj);
				LogManager.info("Result=>" + existing);
				
				if("30".equals(productId))
					sql=getQuery("home.quoteregister.chart.existing.amount", new Object[]{(StringUtils.isBlank(issuer)?" A.LOGIN_ID ='"+loginId+"' AND":"")});
				else
					sql=getQuery(DBConstants.TRAVEL_QUOTEREGISTER_CHART_EXISTING_AMOUNT, new Object[]{(StringUtils.isBlank(issuer)?" A.LOGIN_ID ='"+loginId+"' AND":"")});
				
				LogManager.info("Query=>" + sql);
				LogManager.info("obj[]=>"+StringUtils.join(obj,","));
				existingAmpont=this.mytemplate.queryForInt(sql,obj);
				LogManager.info("Result=>" + existingAmpont);
			}

		}catch (Exception e){			
			LogManager.debug("EXCEPTION @ { " + e + " }");
		}
		LogManager.info("getTRExisting() - Exit");
		LogManager.popRemove();		
		return new int[]{existing,existingAmpont};
	}
	public int[] getTRUnapproved(String loginId,String productId,String issuer,String openCoverNo,String schemeId,String loginBranch)
	{
		String sql = "";
		LogManager.info("getTRUnapproved() - Enter");
		int unapproved =0;
		int unapprovedAmount =0;
		String[] obj=null;
		try{
			if("3".equals(productId) || "11".equals(productId)){
				sql=getQuery(DBConstants.REPORT_REFERRAL_CHART_UNAPPROVED, new Object[]{(StringUtils.isBlank(issuer)?" A.LOGIN_ID ='"+loginId+"' AND":"")});
				obj=new String[]{productId,(StringUtils.isBlank(issuer)?"1":issuer),openCoverNo};
				LogManager.info("Query=>" + sql);
				LogManager.info("Args[]=>" + StringUtils.join(obj,","));
				unapproved=this.mytemplate.queryForInt(sql,obj);
				LogManager.info("Result=>" + unapproved);
				sql=getQuery(DBConstants.REPORT_REFERRAL_CHART_UNAPPROVED_AMOUNT, new Object[]{(StringUtils.isBlank(issuer)?" A.LOGIN_ID ='"+loginId+"' AND":"")});
				LogManager.info("Query=>" + sql);
				LogManager.info("Args[]=>" + StringUtils.join(obj,","));
				unapprovedAmount=this.mytemplate.queryForInt(sql,obj);
				LogManager.info("Result=>" + unapprovedAmount);
			}else if("65".equals(productId)){
				sql=getQuery("motor.report.referral.chart.unapproved", new Object[]{(StringUtils.isBlank(issuer)?" A.LOGIN_ID ='"+loginId+"' AND":"")});
				LogManager.info("Query=>" + sql);
				obj=new String[]{productId,schemeId,(StringUtils.isBlank(issuer)?"1":issuer),loginBranch};
				LogManager.info("Args[]=>" + StringUtils.join(obj,","));
				unapproved=this.mytemplate.queryForInt(sql,obj);
				LogManager.info("Result=>" + unapproved);
				sql=getQuery("motor.report.referral.chart.unapproved.amount", new Object[]{(StringUtils.isBlank(issuer)?" A.LOGIN_ID ='"+loginId+"' AND":"")});
				LogManager.info("Query=>" + sql);
				LogManager.info("Args[]=>" + StringUtils.join(obj,","));
				unapprovedAmount=this.mytemplate.queryForInt(sql,obj);
				LogManager.info("Result=>" + unapprovedAmount);
			}
			else
			{
				if("30".equals(productId)){
					sql=getQuery("home.report.referral.chart.unapproved", new Object[]{(StringUtils.isBlank(issuer)?" A.LOGIN_ID ='"+loginId+"' AND":"")});
					obj=new String[]{productId,(StringUtils.isBlank(issuer)?"1":issuer),loginBranch};
				}
				else{
					sql=getQuery(DBConstants.TRAVEL_REPORT_REFERRAL_CHART_UNAPPROVED, new Object[]{(StringUtils.isBlank(issuer)?" A.LOGIN_ID ='"+loginId+"' AND":"")});
					obj=new String[]{productId,schemeId,(StringUtils.isBlank(issuer)?"1":issuer)};
				}
				LogManager.info("Query=>" + sql);
				LogManager.info("Args[]=>" + StringUtils.join(obj,","));
				unapproved=this.mytemplate.queryForInt(sql,obj);
				LogManager.info("Result=>" + unapproved);
				if("30".equals(productId))
					sql=getQuery("home.report.referral.chart.unapproved.amount", new Object[]{(StringUtils.isBlank(issuer)?" A.LOGIN_ID ='"+loginId+"' AND":"")});
				else
					sql=getQuery(DBConstants.TRAVEL_REPORT_REFERRAL_CHART_UNAPPROVED_AMOUNT, new Object[]{(StringUtils.isBlank(issuer)?" A.LOGIN_ID ='"+loginId+"' AND":"")});
				LogManager.info("Query=>" + sql);
				LogManager.info("Args[]=>" + StringUtils.join(obj,","));
				unapprovedAmount=this.mytemplate.queryForInt(sql,obj);
				LogManager.info("Result=>" + unapprovedAmount);
			}
		}catch (Exception e){			
			LogManager.debug("EXCEPTION @ { " + e + " }");
		}
		int[] trUnapproved={unapproved,unapprovedAmount};
		LogManager.info("Query===>" + sql);	
		LogManager.info("getTRUnapproved() - Exit");
		LogManager.popRemove();		
		return trUnapproved;
	}
	public int[] getTRApproved(String loginId,String productId,String issuer,String openCoverNo,String schemeId,String loginBranch)
	{
		LogManager.info("getTRApproved() - Enter");
		int approved =0;
		int approvedAmount =0;
		String[] obj=null;
		try{
			String sql = "";
			if("3".equals(productId) || "11".equals(productId)){
				sql=getQuery(DBConstants.REPORT_REFERRAL_CHART_APPROVED, new Object[]{(StringUtils.isBlank(issuer)?" A.LOGIN_ID ='"+loginId+"' AND":"")});
				obj=new String[]{productId,(StringUtils.isBlank(issuer)?"1":issuer),openCoverNo};
				LogManager.info("Query=>" + sql);
				LogManager.info("Args[]=>" + StringUtils.join(obj,","));
				approved=this.mytemplate.queryForInt(sql,obj);
				LogManager.info("Result=>" + approved);
				sql=getQuery(DBConstants.REPORT_REFERRAL_CHART_APPROVED_AMOUNT, new Object[]{(StringUtils.isBlank(issuer)?" A.LOGIN_ID ='"+loginId+"' AND":"")});
				LogManager.info("Query=>" + sql);
				LogManager.info("Args[]=>" + StringUtils.join(obj,","));
				approvedAmount=this.mytemplate.queryForInt(sql,obj);
				LogManager.info("Result=>" + approvedAmount);
			}else if("65".equals(productId)){
				sql=getQuery("motor.report.referral.chart.approved", new Object[]{(StringUtils.isBlank(issuer)?" A.LOGIN_ID ='"+loginId+"' AND":"")});
				obj=new String[]{productId,schemeId,(StringUtils.isBlank(issuer)?"1":issuer),loginBranch};
				LogManager.info("Query=>" + sql);
				approved=this.mytemplate.queryForInt(sql,obj);
				LogManager.info("Result=>" + approved);
				sql=getQuery("motor.report.referral.chart.approved.amount", new Object[]{(StringUtils.isBlank(issuer)?" A.LOGIN_ID ='"+loginId+"' AND":"")});
				LogManager.info("Query=>" + sql);
				LogManager.info("Args[]=>" + StringUtils.join(obj,","));
				approvedAmount=this.mytemplate.queryForInt(sql,obj);
				LogManager.info("Result=>" + approvedAmount);
			}
			else
			{
				if("30".equals(productId)){
					sql=getQuery("home.report.referral.chart.approved", new Object[]{(StringUtils.isBlank(issuer)?" A.LOGIN_ID ='"+loginId+"' AND":"")});
					obj=new String[]{productId,(StringUtils.isBlank(issuer)?"1":issuer),loginBranch};
				}
				else{
					sql=getQuery(DBConstants.TRAVEL_REPORT_REFERRAL_CHART_APPROVED, new Object[]{(StringUtils.isBlank(issuer)?" A.LOGIN_ID ='"+loginId+"' AND":"")});
					obj=new String[]{productId,schemeId,(StringUtils.isBlank(issuer)?"1":issuer)};
				}
				LogManager.info("Query=>" + sql);
				approved=this.mytemplate.queryForInt(sql,obj);
				LogManager.info("Result=>" + approved);
				if("30".equals(productId))
					sql=getQuery("home.report.referral.chart.approved.amount", new Object[]{(StringUtils.isBlank(issuer)?" A.LOGIN_ID ='"+loginId+"' AND":"")});
				else
					sql=getQuery(DBConstants.TRAVEL_REPORT_REFERRAL_CHART_APPROVED_AMOUNT, new Object[]{(StringUtils.isBlank(issuer)?" A.LOGIN_ID ='"+loginId+"' AND":"")});
				LogManager.info("Query=>" + sql);
				LogManager.info("Args[]=>" + StringUtils.join(obj,","));
				approvedAmount=this.mytemplate.queryForInt(sql,obj);
				LogManager.info("Result=>" + approvedAmount);
			}
		}catch (Exception e){			
			LogManager.debug("EXCEPTION @ { " + e + " }");
		}
		int[] trApproved={approved,approvedAmount};
		LogManager.info("getTRApproved() - Exit");
		LogManager.popRemove();		
		return trApproved;
	}
	public int[] getTRReject(String loginId,String productId,String issuer,String openCoverNo,String schemeId,String loginBranch)
	{
		LogManager.info("getTRReject() - Enter");
		int reject =0;
		int rejectAmount =0;
		String[] obj=null;
		try{
			String sql = "";
			if("3".equals(productId) || "11".equals(productId)){
				obj=new String[]{productId,(StringUtils.isBlank(issuer)?"1":issuer),openCoverNo};
				sql=getQuery(DBConstants.REPORT_REFERRAL_CHART_REJECT, new Object[]{(StringUtils.isBlank(issuer)?" A.LOGIN_ID ='"+loginId+"' AND":"")});
				LogManager.info("Query=>" + sql);
				LogManager.info("Args[]=>" + StringUtils.join(obj,","));
				reject=this.mytemplate.queryForInt(sql,obj);
				LogManager.info("Result=>" + reject);
				sql=getQuery(DBConstants.REPORT_REFERRAL_CHART_REJECT_AMOUNT, new Object[]{(StringUtils.isBlank(issuer)?" A.LOGIN_ID ='"+loginId+"' AND":"")});
				LogManager.info("Query=>" + sql);
				LogManager.info("Args[]=>" + StringUtils.join(obj,","));
				rejectAmount=this.mytemplate.queryForInt(sql,obj);
				LogManager.info("Result=>" + rejectAmount);
			}else if ("65".equals(productId)){
				sql=getQuery("motor.report.referral.chart.reject", new Object[]{(StringUtils.isBlank(issuer)?" A.LOGIN_ID ='"+loginId+"' AND":"")});
				obj=new String[]{productId,schemeId,(StringUtils.isBlank(issuer)?"1":issuer),loginBranch};
				LogManager.info("Query=>" + sql);
				LogManager.info("Args[]=>" + StringUtils.join(obj,","));
				reject=this.mytemplate.queryForInt(sql,obj);
				LogManager.info("Result=>" + reject);
				sql=getQuery("motor.report.referral.chart.reject.amount", new Object[]{(StringUtils.isBlank(issuer)?" A.LOGIN_ID ='"+loginId+"' AND":"")});
				LogManager.info("Query=>" + sql);
				LogManager.info("Args[]=>" + StringUtils.join(obj,","));
				rejectAmount=this.mytemplate.queryForInt(sql,obj);
				LogManager.info("Result=>" + rejectAmount);
			}
			else{
				if("30".equals(productId)){
					sql=getQuery("home.report.referral.chart.reject", new Object[]{(StringUtils.isBlank(issuer)?" A.LOGIN_ID ='"+loginId+"' AND":"")});
					obj=new String[]{productId,(StringUtils.isBlank(issuer)?"1":issuer),loginBranch};
				}
				else{
					sql=getQuery(DBConstants.TRAVEL_REPORT_REFERRAL_CHART_REJECT, new Object[]{(StringUtils.isBlank(issuer)?" A.LOGIN_ID ='"+loginId+"' AND":"")});
					obj=new String[]{productId,schemeId,(StringUtils.isBlank(issuer)?"1":issuer)};
				}
				LogManager.info("Query=>" + sql);
				LogManager.info("Args[]=>" + StringUtils.join(obj,","));
				reject=this.mytemplate.queryForInt(sql,obj);
				LogManager.info("Result=>" + reject);
				if("30".equals(productId))
					sql=getQuery("home.report.referral.chart.reject.amount", new Object[]{(StringUtils.isBlank(issuer)?" A.LOGIN_ID ='"+loginId+"' AND":"")});
				else
					sql=getQuery(DBConstants.TRAVEL_REPORT_REFERRAL_CHART_REJECT_AMOUNT, new Object[]{(StringUtils.isBlank(issuer)?" A.LOGIN_ID ='"+loginId+"' AND":"")});
				LogManager.info("Query=>" + sql);
				LogManager.info("Args[]=>" + StringUtils.join(obj,","));
				rejectAmount=this.mytemplate.queryForInt(sql,obj);
				LogManager.info("Result=>" + rejectAmount);
			}
		}catch (Exception e){			
			LogManager.debug("EXCEPTION @ { " + e + " }");
		}
		int[] trReject={reject, rejectAmount};
		LogManager.info("getTRReject() - Exit");
		LogManager.popRemove();		
		return trReject;
	}
	public int[] getTRLapsed(String loginId,String productId,String issuer,String openCoverNo,String schemeId,String loginBranch)
	{
		LogManager.info("getTRLapsed() - Enter");
		int lapsed =0;
		int lapsedAmount =0;
		Object[] obj=null;
		try{
			String sql = "";
			if("3".equals(productId) || "11".equals(productId)){
				sql=getQuery(DBConstants.REPORT_CHART_QUOTEREGISTER_LAPSED, new Object[]{(StringUtils.isBlank(issuer)?" A.LOGIN_ID ='"+loginId+"' AND":"")});
				LogManager.info("Query=>" + sql);
				obj=new Object[]{productId,(StringUtils.isBlank(issuer)?"1":issuer),openCoverNo};
				LogManager.info("obj=>" + StringUtils.join(obj,","));
				lapsed=this.mytemplate.queryForInt(sql,obj);
				LogManager.info("Result=>" + lapsed);
				sql=getQuery(DBConstants.REPORT_CHART_QUOTEREGISTER_LAPSED_AMOUNT, new Object[]{(StringUtils.isBlank(issuer)?" A.LOGIN_ID ='"+loginId+"' AND":"")});
				LogManager.info("Query=>" + sql);
				LogManager.info("obj=>" + StringUtils.join(obj,","));
				lapsedAmount=this.mytemplate.queryForInt(sql,obj);
				LogManager.info("Result=>" + lapsedAmount);
			}else if("65".equals(productId)){
				sql=getQuery("motor.report.chart.quoteregister.lapsed", new Object[]{(StringUtils.isBlank(issuer)?" A.LOGIN_ID ='"+loginId+"' AND":"")});
				obj=new Object[]{productId,schemeId,(StringUtils.isBlank(issuer)?"1":issuer),loginBranch};
				LogManager.info("Query=>" + sql);
				LogManager.info("obj=>" + StringUtils.join(obj,","));
				lapsed=this.mytemplate.queryForInt(sql,obj);
				LogManager.info("Result=>" + lapsed);
				sql=getQuery("motor.report.chart.quoteregister.lapsed.amount", new Object[]{(StringUtils.isBlank(issuer)?" A.LOGIN_ID ='"+loginId+"' AND":"")});
				LogManager.info("Query=>" + sql);
				LogManager.info("obj=>" + StringUtils.join(obj,","));
				lapsedAmount=this.mytemplate.queryForInt(sql,obj);
				LogManager.info("Result=>" + lapsedAmount);
			}
			else
			{
				if("30".equals(productId)){
					sql=getQuery("home.report.chart.quoteregister.lapsed", new Object[]{(StringUtils.isBlank(issuer)?" A.LOGIN_ID ='"+loginId+"' AND":"")});
					obj=new Object[]{productId,(StringUtils.isBlank(issuer)?"1":issuer),loginBranch};
				}
				else{
					sql=getQuery(DBConstants.TRAVEL_REPORT_CHART_QUOTEREGISTER_LAPSED, new Object[]{(StringUtils.isBlank(issuer)?" A.LOGIN_ID ='"+loginId+"' AND":"")});
					obj=new Object[]{productId,schemeId,(StringUtils.isBlank(issuer)?"1":issuer)};
				}
				LogManager.info("Query=>" + sql);
				LogManager.info("obj=>" + StringUtils.join(obj,","));
				lapsed=this.mytemplate.queryForInt(sql,obj);
				LogManager.info("Result=>" + lapsed);
				if("30".equals(productId))
					sql=getQuery("home.report.chart.quoteregister.lapsed.amount", new Object[]{(StringUtils.isBlank(issuer)?" A.LOGIN_ID ='"+loginId+"' AND":"")});
				else
					sql=getQuery(DBConstants.TRAVEL_REPORT_CHART_QUOTEREGISTER_LAPSED_AMOUNT, new Object[]{(StringUtils.isBlank(issuer)?" A.LOGIN_ID ='"+loginId+"' AND":"")});
				LogManager.info("Query=>" + sql);
				LogManager.info("obj=>" + StringUtils.join(obj,","));
				lapsedAmount=this.mytemplate.queryForInt(sql,obj);
				LogManager.info("Result=>" + lapsedAmount);
			}
		}catch (Exception e){
			LogManager.debug("EXCEPTION @ { " + e + " }");
		}
		LogManager.info("getTRLapsed() - Exit");
		LogManager.popRemove();
		return new int[]{lapsed,lapsedAmount};
	}
	public List getSingleInfo(String option, String[] args) {
		LogManager.info("getSingleInfo - Enter || "+option+" args: "+ StringUtils.join(args, ","));
		String sql = "";
		List result=null;					
		try{
			sql=getQuery(option);
			result=this.mytemplate.queryForList(sql,args);			
		}
		catch (Exception e) {			
			LogManager.debug("EXCEPTION @ { " + e + " }");
		}		
		LogManager.info("Query==>" + sql);
		LogManager.info("getSingleInfo() - Exit || Result: "+result.size() );
		LogManager.popRemove();		
		return result;
	}

	public String getEndtQuoteNo(String quoteNo) {
		LogManager.info("getEndtQuoteNo - Enter || "+quoteNo);
		String endtQuoteNo="";					
		try{
			String sql = "";
			sql=getQuery("GET_ENDT_QUOTENO");
			LogManager.info("Query==>" + sql);
			List list= this.mytemplate.queryForList(sql,new Object[]{quoteNo});
			if(list!=null && list.size()>0){
				Map map=(Map)list.get(0);
				endtQuoteNo=map.get("QUOTE_NO")==null?"":map.get("QUOTE_NO").toString();
			}
			if (StringUtils.isBlank(endtQuoteNo))
				endtQuoteNo=quoteNo;
		}
		catch (Exception e) {			
			LogManager.debug("EXCEPTION @ { " + e + " }");
		}	
		return endtQuoteNo;
	}

	public Map<String ,Object> getQuoteInfo(String quoteNo) {
		LogManager.info("getQuoteInfo - Enter || "+quoteNo);
		Map<String ,Object> quoteInfo=null;
		try{
			String sql = "";
			sql="select REFERENCE_NUMBER, application_no from webservice_marine_quote where quote_no=?";
			LogManager.info("Query==>" + sql);
			quoteInfo= this.mytemplate.queryForMap(sql,new Object[]{quoteNo});
		}
		catch (Exception e) {			
			LogManager.debug("EXCEPTION @ { " + e + " }");
		}	
		return quoteInfo;
	}

	public void getUpdateEndtStatus(String quoteNo , String cancelRemarks) {
		LogManager.info("getUpdateEndtStatus - Enter || "+quoteNo);
		try{
			String sql="UPDATE POSITION_MASTER SET STATUS='D', PDF_PRE_SHOW_STATUS='YES',CANCEL_REMARKS=?  WHERE QUOTE_NO=?";
			LogManager.info("Query==>" + sql);
			this.mytemplate.update(sql,new Object[]{cancelRemarks, quoteNo});
			sql = "UPDATE  POSITION_MASTER SET STATUS='D', PDF_BROKER_STATUS='999' WHERE POLICY_NO LIKE (SELECT SUBSTR(POLICY_NO,1,instr(POLICY_NO,'-',-1)-1) FROM POSITION_MASTER WHERE QUOTE_NO=?) ||'%'";
			LogManager.info("Query==>" + sql);
			this.mytemplate.update(sql,new Object[]{quoteNo});
		}
		catch (Exception e) {
			LogManager.debug("EXCEPTION @ { " + e + " }");
		}
	}
	public List getLcUploadDetails(String policyNo) {
		LogManager.info("getLcUploadDetails - Enter");
		String sql = "";
		List result=null;					
		try{
			sql=getQuery("GET_LC_UPLOAD_DETAILS");
			result=this.mytemplate.queryForList(sql,new Object[]{policyNo});			
		}
		catch (Exception e) {			
			LogManager.debug("EXCEPTION @ { " + e + " }");
		}		
		LogManager.info("Query==>" + sql);
		LogManager.info("getLcUploadDetails() - Exit || Result: "+result.size() );
		LogManager.popRemove();		
		return result;
	}
	public List getLcUploadPolicy(String policyNo) {
		LogManager.info("getLcUploadPolicy - Enter");
		String sql = "";
		List result=null;					
		try{
			sql=getQuery("GET_LC_UPLOAD_POLICY_DTLS");
			result=this.mytemplate.queryForList(sql,new Object[]{policyNo});			
		}
		catch (Exception e) {			
			LogManager.debug("EXCEPTION @ { " + e + " }");
		}		
		LogManager.info("Query==>" + sql);
		LogManager.info("getLcUploadPolicy() - Exit || Result: "+result.size() );
		LogManager.popRemove();		
		return result;
	}
	public List getLcUploadPloDtls(String policyNo) {
		LogManager.info("getLcUpoladPolDtls - Enter");
		String sql = "";
		List result=null;					
		try{
			sql=getQuery("GET_LC_UPLOAD_POL_DTLS");
			result=this.mytemplate.queryForList(sql,new Object[]{policyNo});			
		}
		catch (Exception e) {			
			LogManager.debug("EXCEPTION @ { " + e + " }");
		}		
		LogManager.info("Query==>" + sql);
		LogManager.info("getLcUploadPolDtls() - Exit || Result: "+result.size() );
		LogManager.popRemove();		
		return result;
	}
	public void insertLcFileDtls(String policyNo,String loginId,List<String> uploadFileName,File fileToCreate,List<String> lcdocremarks,String lcFilePath,List<File> upload){
		LogManager.info("getUpdateEndtStatus - Enter || ");
		try{
			String sql = "";
			sql=getQuery("LC_FILE_INSERT");
			LogManager.info("Query==>" + sql);
			for(int i=0;i<uploadFileName.size();i++){
				Map uploadId = this.mytemplate.queryForMap("select LC_FILE_UPLOAD_SEQ.nextval uploadid from dual");
				String lcUploadId = uploadId.get("uploadid")==null?"":uploadId.get("uploadid").toString();
				fileToCreate = new File(lcFilePath+(lcUploadId)+"."+FilenameUtils.getExtension(uploadFileName.get(i)));
				FileUtils.copyFile(upload.get(i), fileToCreate);
				this.mytemplate.update(sql,new Object[]{policyNo,fileToCreate.getName(),loginId,uploadFileName.get(i),lcdocremarks.get(i),"Y",lcUploadId});
			}

		}
		catch (Exception e) {
			LogManager.debug("EXCEPTION @ { " + e + " }");
		}
	}
	public List<Object> getLcFileList(String policyNo)	
	{
		LogManager.info("getLcFileList() - Enter || endtType: ");
		String sql = "";
		List<Object> info=new ArrayList<Object>();;			
		try{
			//policyNo = "01/3112/327/2015/2231";
			sql=getQuery("GET_LC_FILE_LIST");
			info=(List<Object>)this.mytemplate.queryForList(sql,new Object[]{policyNo});
		}catch (Exception e){			
			LogManager.debug("EXCEPTION @ { " + e + " }");
		}		
		LogManager.info("Query===>" + sql);	
		LogManager.info("getLcFileList() - Exit || Result: " + info.size());
		LogManager.popRemove();		
		return info;
	}
	public void deleteLcFile(String policyNo,String uploadId){
		LogManager.info("getUpdateEndtStatus - Enter || ");
		try{
			String sql = "";
			sql=getQuery("LC_FILE_DELETE");
			LogManager.info("Query==>" + sql);
			this.mytemplate.update(sql,new Object[]{uploadId.trim()});
		}
		catch (Exception e) {
			LogManager.debug("EXCEPTION @ { " + e + " }");
		}
	}
	public List<Object> getAllDocuments(String policyNo) {
		List<Object> list=null;
		try{
			String[] policyNumber = policyNo.split("-");
			policyNo = policyNumber[0];
			String query=getQuery("GET_DOCUMENTS_LIST");
			list=this.mytemplate.queryForList(query,new Object[]{policyNo});
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
	public List<Map<String,Object>> getHomeApproverPendingList(String loginId,String branchCode,String productId,String Status) {
		List<Map<String,Object>> list=null;
		try{
			String query=getQuery("GET_HOME_APPROVERPENDING_LIST");
			Object[] args = new Object[]{loginId,productId,branchCode,Status,""};
			LogManager.info("Query==>" + query);
			LogManager.info("Args==>" + StringUtils.join(args, ", "));
			list=this.mytemplate.queryForList(query,args);
		} catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
	public List<Map<String,Object>> getHomeRenewalPendingList(String loginId,String branchCode,String productId) {
		List<Map<String,Object>> list=null;
		try{
			String query=getQuery("GET_HOME_RENEWALPENDING_LIST");
			Object[] args = new Object[]{loginId,productId,branchCode};
			LogManager.info("Query==>" + query);
			LogManager.info("Args==>" + StringUtils.join(args, ", "));
			list=this.mytemplate.queryForList(query,args);
		} catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}


	public List<Map<String, Object>> getPolicyNoList(String productId, String loginId) {
		List<Map<String, Object>> list=null;
		try{
			String query=getQuery("GET_POLICY_NO_LIST");
			Object[] args = new Object[]{productId,loginId};
			LogManager.info("Query==>" + query);
			LogManager.info("Args==>" + StringUtils.join(args, ", "));
			list=this.mytemplate.queryForList(query,args);
		} catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}

	public List<Map<String,Object>> getb2cSearchResult(ReportAction action, String userType, String productId) {
		LogManager.info("getb2cSearchResult() - Enter");
		List<Map<String,Object>> result=new ArrayList<Map<String,Object>>();			
		try {
			String sql = "";
			Object[] args = null;
			if("65".equals(productId)) {
				//args=new String[]{action.getLoginId(),action.getProductId(),action.getIssuer(),action.getOpenCoverNo(), "%"+action.getSearchValue()+"%"};

				//if("quoteNo".equalsIgnoreCase(action.getSearchBy())) {
					sql=getQuery("motor.report.b2csearch");
					args=new String[]{action.getLoginId(),action.getProductId(),action.getLoginBranch(),action.getIssuer(), "%"+action.getQuoteNo()+"%","%"+action.getMobileNo()+"%"};
				//} 
			}
			removeNull(args);
			LogManager.info("getb2cSearchResult Query=>"+queryFrammer(sql, args));
			result=this.mytemplate.queryForList(sql,args);
		}
		catch (Exception e) 
		{			
			LogManager.debug("EXCEPTION @ { " + e + " }");
		}		
		LogManager.info("getSearchResult() - Exit || Result: " + result.size());
		LogManager.popRemove();		
		return result;
	}


	public String getCertificate(String quoteNo,String vehicleId) {
		String result=null;
		try {
			result = reportApi.getCertificate(quoteNo,vehicleId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}