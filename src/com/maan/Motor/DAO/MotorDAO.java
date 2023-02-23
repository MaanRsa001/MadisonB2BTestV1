package com.maan.Motor.DAO;


import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;

import com.maan.Motor.controller.MotorBean;
import com.maan.common.DBConstants;
import com.maan.common.LogManager;
import com.maan.common.MyJdbcTemplate;
import com.maan.common.dao.CommonDAO;
import com.maan.common.util.ResourceBundleUtil;
import com.maan.customer.dao.CustomerDAO;
import com.maan.dao.ApiCaller.ApiForMotor;
import com.maan.services.util.runner;

public class MotorDAO extends MyJdbcTemplate {
	ApiForMotor motorApi = new ApiForMotor();
	
	/*public void getReferralCheck(MotorBean bean) {
		LogManager.info("getReferralCheck - Enter ");
			String sql="";
			int vehicleAge=0;
			Object[] obj=new Object[0];
			try{
				List<String> referralValidation = new ArrayList<String>();
				List<String> referralMsg = new ArrayList<String>();
				for(int i=0; i<bean.getMakeIdList().size() ; i++) {
					if(StringUtils.isNotBlank(bean.getMakeIdList().get(i))) {
						sql=this.getQuery("GET_MOTOR_YEAR_CNT");
						obj=new Object[]{bean.getMfgYrIdList().get(i)};
						LogManager.info("Sql=>"+sql);
						LogManager.info("Obj[]=>"+bean.getMfgYrIdList().get(i));
						final String vehicleyear=this.mytemplate.queryForInt(sql,obj)+"";
						LogManager.info("Result=>"+vehicleyear);
						sql = this.getQuery("GET_MOTOR_VEHICLE_AGE");
						obj=new Object[4];
						obj[0]=bean.getBranchCode();
						obj[1]=bean.getProductId();
						obj[2]=bean.getTypeBodyIdList().get(i);
						obj[3]=vehicleyear;
						LogManager.info("Sql=>"+sql);
						LogManager.info("Obj[]=>"+StringUtils.join(obj,","));
						vehicleAge=this.mytemplate.queryForInt(sql,obj);
						if(vehicleAge<=0)
						{
							referralValidation.add("Vehicle Age Referral");
						}
						sql=this.getQuery("GET_MOTOR_MAKE_REF_STATUS");
						obj[0]=bean.getMakeIdList().get(i);
						obj[1]=bean.getBranchCode();
						obj[2]=bean.getMakeIdList().get(i);
						obj[3]=bean.getBranchCode();
						LogManager.info("Sql=>"+sql);
						LogManager.info("Obj[]=>"+StringUtils.join(obj,","));
						String refStatus=(String)this.mytemplate.queryForObject(sql, obj,  String.class);
						LogManager.info("Make Ref Status=>"+refStatus);
						if("Y".equalsIgnoreCase(refStatus)){
							referralValidation.add("Make Referral");
						}
						sql=this.getQuery("GET_MOTOR_MODEL_REF_STATUS");
						obj=new Object[6];
						obj[0]=bean.getModelIdList().get(i);
						obj[1]=bean.getMakeIdList().get(i);
						obj[2]=bean.getBranchCode();
						obj[3]=bean.getModelIdList().get(i);
						obj[4]=bean.getMakeIdList().get(i);
						obj[5]=bean.getBranchCode();
						LogManager.info("Sql=>"+sql);
						LogManager.info("Obj[]=>"+StringUtils.join(obj,","));
						refStatus=(String)this.mytemplate.queryForObject(sql, obj,  String.class);
						LogManager.info("Model Ref Status=>"+refStatus);
						if("Y".equalsIgnoreCase(refStatus)){
							referralValidation.add("Model Referral");
						}
						if(StringUtils.isBlank(bean.getApplicationNo()))
						{
							sql=this.getQuery("GET_MOTOR_AG_CODE");
							obj=new Object[]{bean.getLoginId()};
						}else
						{
							sql=this.getQuery("GET_MOTOR_AGENCY_CODE");
							obj=new Object[]{bean.getApplicationNo()};
						}
						LogManager.info("Sql=>"+sql);
						LogManager.info("Obj[]=>"+StringUtils.join(obj,","));
						final String brokerCode = (String)this.mytemplate.queryForObject(sql, obj,String.class);
						LogManager.info("Result=>"+brokerCode);
						refStatus=getTypeInfo(bean.getTypeBodyIdList().get(i), bean.getModelIdList().get(i), bean.getVehicleUsageIdList().get(i), bean.getDriverDOBList().get(i), bean.getBranchCode(), bean.getProductId(), false, false, false, brokerCode);
						if(refStatus.length()<=0)
						{
							sql=LocalizedTextUtil.findDefaultText("GET_MOTOR_TYPE_BODY_CNT", Locale.ENGLISH, new String[]{brokerCode});
							obj=new Object[3];
							obj[0]=bean.getTypeBodyIdList().get(i);
							obj[1]=bean.getBranchCode();
							obj[2]=bean.getProductId();
							LogManager.info("Sql=>"+sql);
							LogManager.info("Obj[]=>"+StringUtils.join(obj,","));
							int typeBodyCnt=this.mytemplate.queryForInt(sql,obj);
							LogManager.info("TypeBodyCnt=>"+typeBodyCnt);
							if(typeBodyCnt>0) {
								if(getDriverAgeCount(bean.getTypeBodyIdList().get(i), bean.getDriverDOBList().get(i), bean.getBranchCode(), bean.getProductId(), brokerCode)<=0) {
									referralValidation.add("Driver Age Referral");
								}
								if(getUaeLicenceCount(bean.getTypeBodyIdList().get(i), bean.getBranchCode(), bean.getProductId(), brokerCode)<=0) {
									referralValidation.add("UAE Licence Referral");
								}
								if(getVehicleTypeCount(bean.getTypeBodyIdList().get(i), bean.getVehicleUsageIdList().get(i), bean.getBranchCode(), bean.getProductId(), brokerCode)<=0) {
									referralValidation.add("Vehicle Usage Referral");
								}
								
							} else {
								referralValidation.add("Type of Body Referral");
							}
						}
						double sILimit=Double.parseDouble(new CommonDAO().getSumInsuredLimit(bean.getApplicationNo(),bean.getLoginId(),bean.getProductId()));
						LogManager.info("sILimit=>"+sILimit);
						if(sILimit<Double.parseDouble(bean.getSumInsuredList().get(i))) {
							//bean.setReferralMsg(bean.getReferralMsg()+"<br>Sum Insured Referral");
							referralMsg.add("Sum Insured Referral");
						}
						//bean.setReferralValidation(StringUtils.join(referralValidation,", "));
						bean.setReferralMsg(StringUtils.join(referralMsg,", "));
					}
				}
			}catch(Exception e)
			{
				e.printStackTrace();
			}
		LogManager.info("getReferralCheck - Exit ");
	}*/

	public String addVehicle(MotorBean bean) {
		LogManager.info("addVehicle - Enter ");
		/*if(!"getSave".equalsIgnoreCase(bean.getActionType()))
			getReferralCheck(bean);*/
		String result="SUCCESS";
		String sql="";
		Object[] obj=null;
		int res=0;
		try {
			boolean saveFlag=("getSave".equalsIgnoreCase(bean.getActionType())?true:false);
			if(StringUtils.isBlank(bean.getApplicationNo())) {
				bean.setApplicationNo(new CommonDAO().getSequenceNo("Application",bean.getProductId(),bean.getBranchCode(),"",""));
			}
			if(StringUtils.isBlank(bean.getQuoteNo())&&("getQuote".equalsIgnoreCase(bean.getActionType())||saveFlag)) {
				bean.setQuoteNo(new CommonDAO().getSequenceNo("Quote",bean.getProductId(),bean.getBranchCode(),bean.getApplicationNo(),""));
			}
			
			updateVehicleDetails(bean);
			updateQuoteDetails(bean);
			updateCoveragesInfo(bean.getApplicationNo(),bean.getLoginBranch(),bean.getProductId(),bean.getEndTypeId(),bean.getLoginId(),"C",bean.getUserType(),"",bean.getReferal());
			//bean.setReferralMsg(getReferralMsgs(bean.getApplicationNo()));
			
			String posCntQuery = getQuery("GET_HOM_POS_CNT");
			Object[] posCntObj = new Object[]{bean.getApplicationNo()};
			LogManager.info("posCntQuery==> " + posCntQuery);
			LogManager.info("posCntObj==> " + StringUtils.join(posCntObj, "~~"));
			int posCount = this.mytemplate.queryForInt(posCntQuery, posCntObj);
			
			if(posCount==0) {
				obj=new Object[16];
				sql=getQuery("INS_MOTOR_HOME_POS_DTLS");
				obj[0] = bean.getQuoteNo();
				obj[1] = bean.getCustomerId();
				obj[2] = bean.getLoginId();
				obj[3] = bean.getProductId();
				obj[4] = "0";
				obj[5] = bean.getPolicyStartDate();
				obj[6] = bean.getPolicyEndDate();
				obj[7] = bean.getApplicationNo();
				obj[8] = saveFlag?"S":"Y";
				obj[9] = "0";
				obj[10] = bean.getFleetNo();
				obj[11] = bean.getLoginBranch();//bean.getBranchCode();
				obj[12]=StringUtils.isBlank(bean.getIssuer())?"1":bean.getIssuer();
				obj[13]="";//admin remarks
				obj[14]=bean.getBrokerCode();
				obj[15]=bean.getExecutive();
				removeNull(obj);
				LogManager.info("Sql=>=>"+sql);
				LogManager.info("Obj[]=>"+StringUtils.join(obj,","));
				res=this.mytemplate.update(sql,obj);
				LogManager.info("Result=>"+res);
			}
			else {
				if("admin".equals(bean.getUser())||"RA".equalsIgnoreCase(bean.getQuoteStatus())){
					sql=getQuery("UPD_MOTOR_HOME_POS_DTLS_ADMIN");
					obj=new Object[7];
					obj[0] = bean.getPolicyStartDate();
					obj[1] = bean.getPolicyEndDate();
					obj[2] = "";
					if(saveFlag){
						obj[3]="S";
					} else if(StringUtils.isNotBlank(bean.getEndTypeId())) {
						obj[3]="E";
					} else {
						obj[3]="Y";
					}
					obj[4] = bean.getBrokerCode()==null?"":bean.getBrokerCode();
					obj[5] = bean.getExecutive()==null?"":bean.getExecutive();
					obj[6] = bean.getApplicationNo();
				} else {
					sql=this.getQuery("UPD_MOTOR_HOME_POS_DTLS");
					obj=new Object[8];
					obj[0] = bean.getPolicyStartDate();
					obj[1] = bean.getPolicyEndDate();
					obj[2] = "";
					if(saveFlag){
						obj[3]="S";
					} else if(StringUtils.isNotBlank(bean.getEndTypeId())) {
						obj[3]="E";
					} else {
						obj[3]="Y";
					}
					obj[4] = bean.getBrokerCode()==null?"":bean.getBrokerCode();
					obj[5] = bean.getExecutive()==null?"":bean.getExecutive();
					obj[6] = StringUtils.isBlank(bean.getIssuer())?"1":bean.getIssuer();
					obj[7] = bean.getApplicationNo();
				}
				LogManager.info("Sql=>=>"+sql);
				LogManager.info("Obj[]=>"+StringUtils.join(obj,","));
				res=this.mytemplate.update(sql,obj);
				LogManager.info("Result=>"+res);
			}
			
			/*if(!"getSave".equalsIgnoreCase(bean.getActionType())) {
				getCoverageInfo(bean);
			}*/
		}catch(Exception e)
		{
			e.printStackTrace();
			result="FAILED";
		}
		LogManager.popRemove();
		LogManager.info("addVehicle - Exit");
		return result;
	}
	
	public String addVehicleNew(MotorBean bean) {
		String result="SUCCESS";
		try {
			updateVehicleDetailsNew(bean);
			//updateCoveragesInfo(bean.getApplicationNo(),bean.getLoginBranch(),bean.getProductId(),bean.getEndTypeId(),bean.getLoginId(),"C",bean.getUserType(),"",bean.getReferal());
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public String addCustomerPolicy(MotorBean bean) {
		LogManager.info("addVehicle - Enter ");
		/*if(!"getSave".equalsIgnoreCase(bean.getActionType()))
			getReferralCheck(bean);*/
		String result="SUCCESS";
		String sql="";
		Object[] obj=null;
		int res=0;
		try {
			boolean saveFlag=("getSave".equalsIgnoreCase(bean.getActionType())?true:false);
			if(StringUtils.isBlank(bean.getApplicationNo())) {
				bean.setApplicationNo(new CommonDAO().getSequenceNo("Application",bean.getProductId(),bean.getBranchCode(),"",""));
			}
//			if(StringUtils.isBlank(bean.getQuoteNo())||saveFlag) {
//				bean.setQuoteNo(new CommonDAO().getSequenceNo("Quote",bean.getProductId(),bean.getBranchCode(),bean.getApplicationNo(),""));
//			}
			
			//updateVehicleDetails(bean);
			updateQuoteDetailsNew(bean);
			
//			String cntQuery="SELECT COUNT(*) FROM MOTOR_DATA_DETAIL WHERE APPLICATION_NO=? AND PRODUCT_ID=?";
//			Object[] cntObj = new Object[]{bean.getApplicationNo(),bean.getProductId()};
//			int count=this.mytemplate.queryForInt(cntQuery,cntObj);
//			if(count>0){
//				updateVehiclePolicyDtl(bean);
//			}
//			
//			//updateCoveragesInfo(bean.getApplicationNo(),bean.getLoginBranch(),bean.getProductId(),bean.getEndTypeId(),bean.getLoginId(),"C",bean.getUserType(),"");
//			//bean.setReferralMsg(getReferralMsgs(bean.getApplicationNo()));
//			
//			String posCntQuery = getQuery("GET_HOM_POS_CNT");
//			Object[] posCntObj = new Object[]{bean.getApplicationNo()};
//			LogManager.info("posCntQuery==> " + posCntQuery);
//			LogManager.info("posCntObj==> " + StringUtils.join(posCntObj, "~~"));
//			int posCount = this.mytemplate.queryForInt(posCntQuery, posCntObj);
//			
//			if(posCount==0) {
//				obj=new Object[16];
//				sql=getQuery("INS_MOTOR_HOME_POS_DTLS");
//				obj[0] = bean.getQuoteNo();
//				obj[1] = bean.getCustomerId();
//				obj[2] = bean.getLoginId();
//				obj[3] = bean.getProductId();
//				obj[4] = "0";
//				obj[5] = bean.getPolicyStartDate();
//				obj[6] = bean.getPolicyEndDate();
//				obj[7] = bean.getApplicationNo();
//				obj[8] = saveFlag?"S":"Y";
//				obj[9] = "0";
//				obj[10] = bean.getFleetNo();
//				obj[11] = bean.getLoginBranch();//bean.getBranchCode();
//				obj[12]=StringUtils.isBlank(bean.getIssuer())?"1":bean.getIssuer();
//				obj[13]="";//admin remarks
//				obj[14]=bean.getBrokerCode();
//				obj[15]=bean.getExecutive();
//				removeNull(obj);
//				LogManager.info("Sql=>=>"+sql);
//				LogManager.info("Obj[]=>"+StringUtils.join(obj,","));
//				res=this.mytemplate.update(sql,obj);
//				LogManager.info("Result=>"+res);
//			}
//			else {
//				if("admin".equals(bean.getUser())||"RA".equalsIgnoreCase(bean.getQuoteStatus())){
//					sql=getQuery("UPD_MOTOR_HOME_POS_DTLS_ADMIN");
//					obj=new Object[7];
//					obj[0] = bean.getPolicyStartDate();
//					obj[1] = bean.getPolicyEndDate();
//					obj[2] = "";
//					if(saveFlag){
//						obj[3]="S";
//					} else if(StringUtils.isNotBlank(bean.getEndTypeId())) {
//						obj[3]="E";
//					} else {
//						obj[3]="Y";
//					}
//					obj[4] = bean.getBrokerCode()==null?"":bean.getBrokerCode();
//					obj[5] = bean.getExecutive()==null?"":bean.getExecutive();
//					obj[6] = bean.getApplicationNo();
//				} else {
//					sql=this.getQuery("UPD_MOTOR_HOME_POS_DTLS");
//					obj=new Object[8];
//					obj[0] = bean.getPolicyStartDate();
//					obj[1] = bean.getPolicyEndDate();
//					obj[2] = "";
//					if(saveFlag){
//						obj[3]="S";
//					} else if(StringUtils.isNotBlank(bean.getEndTypeId())) {
//						obj[3]="E";
//					} else {
//						obj[3]="Y";
//					}
//					obj[4] = bean.getBrokerCode()==null?"":bean.getBrokerCode();
//					obj[5] = bean.getExecutive()==null?"":bean.getExecutive();
//					obj[6] = StringUtils.isBlank(bean.getIssuer())?"1":bean.getIssuer();
//					obj[7] = bean.getApplicationNo();
//				}
//				LogManager.info("Sql=>=>"+sql);
//				LogManager.info("Obj[]=>"+StringUtils.join(obj,","));
//				res=this.mytemplate.update(sql,obj);
//				LogManager.info("Result=>"+res);
//			}
			
			/*if(!"getSave".equalsIgnoreCase(bean.getActionType())) {
				getCoverageInfo(bean);
			}*/
		}catch(Exception e)
		{
			e.printStackTrace();
			result="FAILED";
		}
		LogManager.popRemove();
		LogManager.info("addVehicle - Exit");
		return result;
	}
	
	
	private void updateVehicleDetails(MotorBean bean) throws Exception {
		String sql="";
		Object[] obj = null;
		if(DBConstants.DEVICETYPE_ANDROID.equalsIgnoreCase(bean.getDeviceType()) || DBConstants.DEVICETYPE_HYBRID.equalsIgnoreCase(bean.getDeviceType())) {
			List<String> vehicleIdList = new ArrayList<String>(bean.getVehicleIdList());
			vehicleIdList.removeAll(Collections.singleton(""));;
			String vehicleIds = "'" + StringUtils.join(vehicleIdList,"','") + "'";
			String query = getQuery("DEL_MOTOR_VEHICLE_DETAILS_ALL", new Object[]{vehicleIds} );
			Object[] args = new Object[]{bean.getApplicationNo()};
			LogManager.info("Query==> " + query);
			LogManager.info("Args==> " + StringUtils.join(args, "~~"));
			int delCnt = this.mytemplate.update(query, args);
			LogManager.info("Result Count==> " + delCnt);
		}
		for(int i=0 ; i<bean.getVehicleIdList().size() ; i++) {
			if(StringUtils.isNotBlank(bean.getMakeIdList().get(i))) {
				String vehicleId = bean.getVehicleIdList().get(i);
				if(StringUtils.isBlank(vehicleId)) {
					String query = getQuery("GET_MOTOR_DTL_VEHICLEID");
					Object[] args = new Object[]{bean.getApplicationNo()};
					vehicleId = (String) this.mytemplate.queryForObject(query, args, String.class);
				}
				/*else {
					String query = getQuery("DEL_MOTOR_VEHICLE_DETAILS");
					Object[] args = new Object[]{bean.getApplicationNo(), vehicleId};
					this.mytemplate.update(query, args);
				}*/
				String dtlCntQuery = getQuery("GET_MOTOR_DTL_CNT");
				Object[] dtlCntObj = new Object[]{bean.getApplicationNo(),vehicleId};
				LogManager.info("dtlCntQuery==> " + dtlCntQuery);
				LogManager.info("dtlCntObj==> " + StringUtils.join(dtlCntObj, "~~"));
				int dtlCount = this.mytemplate.queryForInt(dtlCntQuery, dtlCntObj);
				
				String selectedExcess="";
				String excessQry=getQuery("GET_MOTOR_EXCESS_AMOUNT");
				Object[] arg = new Object[]{bean.getBranchCode(),bean.getSeatingCapacityList().get(i),bean.getVehicleUsageIdList().get(i),
						bean.getTypeBodyIdList().get(i),bean.getBranchCode(),bean.getSeatingCapacityList().get(i),bean.getVehicleUsageIdList().get(i),
						bean.getTypeBodyIdList().get(i),bean.getDeductibleIdList().get(i)};
				List<Map<String,Object>> excessRes=this.mytemplate.queryForList(excessQry,arg);
				if(excessRes!=null && excessRes.size()>0){
					selectedExcess=excessRes.get(0).get("DEDUCT_END")==null?"":excessRes.get(0).get("DEDUCT_END").toString();
				}
				
				
				if(dtlCount==0) {
					sql=this.getQuery("INS_MOTOR_DATA_DTLS_B2B");
		            obj=new Object[49];
					obj[0] = bean.getQuoteNo();
					obj[1] = bean.getCustomerId();
					obj[2] = bean.getProductId();
					obj[3] = "0";
					obj[4] = bean.getApplicationNo();
					obj[5] = "Y";
					obj[6] = bean.getMakeIdList().get(i);
					obj[7] = bean.getModelIdList().get(i);
					obj[8] = bean.getTypeBodyIdList().get(i);
					obj[9] = bean.getMfgYrIdList().get(i);
					obj[10] = bean.getOwnerDriverYNList().get(i);
					obj[11] = bean.getSeatingCapacityList().get(i);
					obj[12] = bean.getSumInsuredList().get(i);
					obj[13] = "N"/*bean.getAgencyRepairYNIdList().get(i)*/;
			        obj[14] = ""/*bean.getNoOfCylinderIdList().get(i)*/;
			        obj[15] = bean.getVehicleUsageIdList().get(i);
			        obj[16] = "";
			        obj[17] = bean.getClaimYNIdList().get(i);
			        obj[18] = bean.getClaimAmountList().get(i);
			        obj[19] = bean.getNoClaimBonusIdList().get(i);
			        obj[20] = ""/*bean.getAreaCoverageIdList().get(i)*/;
			        obj[21] = bean.getDriverDOBList().get(i);
			        obj[22] = bean.getPolicyStartDate();
			        obj[23] = bean.getPolicyEndDate();
			        obj[24] = "";// bean.getTpl()==null?"0":bean.getTpl();
			        obj[25] = "";//bean.getExcess();
			        obj[26] = "";
					obj[27] = "";
					obj[28] = ""/*bean.getReferralMsg()*/;
					obj[29] = bean.getDeductibleIdList().get(i);
					obj[30] = bean.getDriverIdList().get(i);
					obj[31] = bean.getElectricalAccList().get(i);
					obj[32] = bean.getNonElectricalAccList().get(i);
					obj[33] = vehicleId;
					
					obj[34] = bean.getVehicleColourIdList().get(i);
					obj[35] = bean.getLeasedYNIdList().get(i);
					obj[36] = StringUtils.isBlank(bean.getBankOfFinanceIdList().get(i))?"":bean.getBankOfFinanceIdList().get(i);
					obj[37] = bean.getRegNoList().get(i);
					obj[38] = bean.getChassisNoList().get(i);
					obj[39] = bean.getEngineNoList().get(i);
					obj[40] = "";
					obj[41] = "";
					obj[42] = "";
					obj[43] = "";
					obj[44] = bean.getCubicCapacityList().get(i);
					obj[45] = bean.getPrevPolicyNoList().get(i);
					obj[46] = bean.getPrevExpiryDateList().get(i);
					obj[47] = bean.getPrevInsCompanyIdList().get(i);
					obj[48] = selectedExcess;
					
					removeNull(obj);
			        LogManager.info("Sql=>=>"+sql);
					LogManager.info("Obj[]=>"+StringUtils.join(obj,","));
					int res=this.mytemplate.update(sql,obj);
					LogManager.info("Result=>"+res);
				}
				else {
					if(!"quoteView".equalsIgnoreCase(bean.getReqFrom())){
						sql=this.getQuery("UPD_MOTOR_DATA_DTLS_B2B");
						obj=new Object[45];
						obj[0] = bean.getCustomerId();
						obj[1] = bean.getMakeIdList().get(i);
						obj[2] = bean.getModelIdList().get(i);
						obj[3] = bean.getTypeBodyIdList().get(i);
						obj[4] = bean.getMfgYrIdList().get(i);
						obj[5] = bean.getOwnerDriverYNList().get(i);
						obj[6] = bean.getSeatingCapacityList().get(i);
						obj[7] = bean.getSumInsuredList().get(i);
						obj[8] = "N"/*bean.getAgencyRepairYNIdList().get(i)*/;
				        obj[9] = ""/*bean.getNoOfCylinderIdList().get(i)*/;
				        obj[10] = bean.getVehicleUsageIdList().get(i);
				        obj[11] = "";
				        obj[12] = bean.getClaimYNIdList().get(i);
				        obj[13] = bean.getClaimAmountList().get(i);
				        obj[14] = bean.getNoClaimBonusIdList().get(i);
				        obj[15] = ""/*bean.getAreaCoverageIdList().get(i)*/;
				        obj[16] = bean.getDriverDOBList().get(i);
				        obj[17] = bean.getPolicyStartDate();
				        obj[18] = bean.getPolicyEndDate();
				        obj[19] = "";// bean.getTpl()==null?"0":bean.getTpl();
				        obj[20] = "";//bean.getExcess();
				        obj[21] = ""/*bean.getDriverNationalityList().get(i)*/;
						obj[22] = ""/*bean.getUaeLicExpDTIdList().get(i)*/;
						obj[23] = ""/*bean.getReferralMsg()*/;
						obj[24] = bean.getDeductibleIdList().get(i);
						obj[25] = bean.getDriverIdList().get(i);
						obj[26] = bean.getElectricalAccList().get(i);
						obj[27] = bean.getNonElectricalAccList().get(i);
						
						obj[28] = bean.getVehicleColourIdList().get(i);
						obj[29] = bean.getLeasedYNIdList().get(i);
						obj[30] = StringUtils.isBlank(bean.getBankOfFinanceIdList().get(i))?"":bean.getBankOfFinanceIdList().get(i);
						obj[31] = bean.getRegNoList().get(i);
						obj[32] = bean.getChassisNoList().get(i);
						obj[33] = bean.getEngineNoList().get(i);
						obj[34] = "";
						obj[35] = "";
						obj[36] = "";
						obj[37] = "";
						obj[38] = bean.getCubicCapacityList().get(i);
						obj[39] = bean.getPrevPolicyNoList().get(i);
						obj[40] = bean.getPrevExpiryDateList().get(i);
						obj[41] = bean.getPrevInsCompanyIdList().get(i);
						obj[42] = selectedExcess;
						
				        obj[43] = bean.getApplicationNo();
				        obj[44] = vehicleId;
					}else{
						sql=this.getQuery("UPD_MOTOR_DATA_DTLS_B2BQUOTE");
						obj=new Object[38];
						obj[0] = bean.getCustomerId();
						obj[1] = bean.getMakeIdList().get(i);
						obj[2] = bean.getModelIdList().get(i);
						obj[3] = bean.getTypeBodyIdList().get(i);
						obj[4] = bean.getMfgYrIdList().get(i);
						obj[5] = bean.getOwnerDriverYNList().get(i);
						obj[6] = bean.getSeatingCapacityList().get(i);
						obj[7] = bean.getSumInsuredList().get(i);
						obj[8] = "N"/*bean.getAgencyRepairYNIdList().get(i)*/;
				        obj[9] = ""/*bean.getNoOfCylinderIdList().get(i)*/;
				        obj[10] = bean.getVehicleUsageIdList().get(i);
				        obj[11] = "";
				        obj[12] = bean.getClaimYNIdList().get(i);
				        obj[13] = bean.getClaimAmountList().get(i);
				        obj[14] = bean.getNoClaimBonusIdList().get(i);
				        obj[15] = ""/*bean.getAreaCoverageIdList().get(i)*/;
				        obj[16] = bean.getDriverDOBList().get(i);
				        obj[17] = bean.getPolicyStartDate();
				        obj[18] = bean.getPolicyEndDate();
				        obj[19] = "";// bean.getTpl()==null?"0":bean.getTpl();
				        obj[20] = "";//bean.getExcess();
				        obj[21] = ""/*bean.getDriverNationalityList().get(i)*/;
						obj[22] = ""/*bean.getUaeLicExpDTIdList().get(i)*/;
						obj[23] = ""/*bean.getReferralMsg()*/;
						obj[24] = bean.getDeductibleIdList().get(i);
						obj[25] = bean.getDriverIdList().get(i);
						obj[26] = bean.getElectricalAccList().get(i);
						obj[27] = bean.getNonElectricalAccList().get(i);
						
						/*obj[28] = bean.getVehicleColourIdList().get(i);
						obj[29] = bean.getLeasedYNIdList().get(i);
						obj[30] = StringUtils.isBlank(bean.getBankOfFinanceIdList().get(i))?"":bean.getBankOfFinanceIdList().get(i);
						obj[31] = bean.getRegNoList().get(i);
						obj[32] = bean.getChassisNoList().get(i);
						obj[33] = bean.getEngineNoList().get(i);*/
						obj[28] = "";
						obj[29] = "";
						obj[30] = "";
						obj[31] = "";
						//obj[32] = bean.getCubicCapacityList().get(i);
						obj[32] = bean.getPrevPolicyNoList().get(i);
						obj[33] = bean.getPrevExpiryDateList().get(i);
						obj[34] = bean.getPrevInsCompanyIdList().get(i);
						obj[35] = selectedExcess;
						
				        obj[36] = bean.getApplicationNo();
				        obj[37] = vehicleId;
					}
			        removeNull(obj);
			        LogManager.info("Sql=>=>"+sql);
					LogManager.info("Obj[]=>"+StringUtils.join(obj,","));
					int res=this.mytemplate.update(sql,obj);
					LogManager.info("Result=>"+res);
				}
			}
		}
	}

	private String updateVehicleDetailsNew(MotorBean bean) throws Exception {
		String result=null;
		try {
			result = motorApi.updateVehicleDetailsNew(bean);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
		
		
		
//		String sql="";
//		Object[] obj = null;
//		if(DBConstants.DEVICETYPE_ANDROID.equalsIgnoreCase(bean.getDeviceType()) || DBConstants.DEVICETYPE_HYBRID.equalsIgnoreCase(bean.getDeviceType())) {
//			List<String> vehicleIdList = new ArrayList<String>(bean.getVehicleIdList());
//			vehicleIdList.removeAll(Collections.singleton(""));;
//			String vehicleIds = "'" + StringUtils.join(vehicleIdList,"','") + "'";
//			String query = getQuery("DEL_MOTOR_VEHICLE_DETAILS_ALL", new Object[]{vehicleIds} );
//			Object[] args = new Object[]{bean.getApplicationNo()};
//			LogManager.info("Query==> " + query);
//			LogManager.info("Args==> " + StringUtils.join(args, "~~"));
//			int delCnt = this.mytemplate.update(query, args);
//			LogManager.info("Result Count==> " + delCnt);
//		}
//		for(int i=0 ; i<bean.getVehicleIdList().size() ; i++) {
//			if(StringUtils.isNotBlank(bean.getMakeIdList().get(i))) {
//				String vehicleId = bean.getVehicleIdList().get(i);
//				if(StringUtils.isBlank(vehicleId)) {
//					String query = getQuery("GET_MOTOR_DTL_VEHICLEID");
//					Object[] args = new Object[]{bean.getApplicationNo()};
//					vehicleId = (String) this.mytemplate.queryForObject(query, args, String.class);
//				}
//				/*else {
//					String query = getQuery("DEL_MOTOR_VEHICLE_DETAILS");
//					Object[] args = new Object[]{bean.getApplicationNo(), vehicleId};
//					this.mytemplate.update(query, args);
//				}*/
//				bean.setDeleteVehicleId(vehicleId);
//				String dtlCntQuery = getQuery("GET_MOTOR_DTL_CNT");
//				Object[] dtlCntObj = new Object[]{bean.getApplicationNo(),vehicleId};
//				LogManager.info("dtlCntQuery==> " + dtlCntQuery);
//				LogManager.info("dtlCntObj==> " + StringUtils.join(dtlCntObj, "~~"));
//				int dtlCount = this.mytemplate.queryForInt(dtlCntQuery, dtlCntObj);
//				
//				String selectedExcess="";
//				String excessQry=getQuery("GET_MOTOR_EXCESS_AMOUNT");
//				Object[] arg = new Object[]{bean.getBranchCode(),bean.getSeatingCapacityList().get(i),bean.getVehicleUsageIdList().get(i),
//						bean.getTypeBodyIdList().get(i),bean.getBranchCode(),bean.getSeatingCapacityList().get(i),bean.getVehicleUsageIdList().get(i),
//						bean.getTypeBodyIdList().get(i),bean.getDeductibleIdList().get(i)};
//				List<Map<String,Object>> excessRes=this.mytemplate.queryForList(excessQry,arg);
//				if(excessRes!=null && excessRes.size()>0){
//					selectedExcess=excessRes.get(0).get("DEDUCT_END")==null?"":excessRes.get(0).get("DEDUCT_END").toString();
//				}
//				/*String polStratDate="";
//				String polExpiryDate="";
//				String dateQurery="SELECT TO_CHAR(POLICYSTARTDATE,'DD/MM/YYYY') POLICYSTARTDATE, TO_CHAR(POLICYENDDATE,'DD/MM/YYYY') POLICYENDDATE FROM MOTOR_POLICY_DETAILS WHERE QUOTENO=?";
//				Object[] dtarg = new Object[]{bean.getQuoteNo()};
//				List<Map<String,Object>> dateRes=this.mytemplate.queryForList(dateQurery,dtarg);
//				if(dateRes!=null && dateRes.size()>0){
//					polStratDate=dateRes.get(0).get("POLICYSTARTDATE")==null?"":dateRes.get(0).get("POLICYSTARTDATE").toString();
//					polExpiryDate=dateRes.get(0).get("POLICYENDDATE")==null?"":dateRes.get(0).get("POLICYENDDATE").toString();
//				}
//				Date date = new Date();  
//			    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");  
//			    String strDate = formatter.format(date);*/
//			    
//				if(dtlCount==0) {
//					if(StringUtils.isNotBlank(bean.getQuoteNo())){
//						sql=this.getQuery("INS_MOTOR_DATA_DTLS_B2B_NEW_QUOTE");
//						obj=new Object[45];
//						
//						obj[0] = bean.getCustomerId();
//						obj[1] = bean.getProductId();
//						obj[2] = "0";
//						obj[3] = bean.getApplicationNo();
//						obj[4] = "Y";
//						obj[5] = bean.getMakeIdList().get(i);
//						obj[6] = bean.getModelIdList().get(i);
//						obj[7] = bean.getTypeBodyIdList().get(i);
//						obj[8] = bean.getMfgYrIdList().get(i);
//						//obj[10] = bean.getOwnerDriverYNList().get(i);
//						obj[9] = bean.getSeatingCapacityList().get(i);
//						obj[10] = bean.getSumInsuredList().get(i);
//						obj[11] = "N"/*bean.getAgencyRepairYNIdList().get(i)*/;
//				        obj[12] = ""/*bean.getNoOfCylinderIdList().get(i)*/;
//				        obj[13] = bean.getVehicleUsageIdList().get(i);
//				        obj[14] = "";
//				        //obj[17] = bean.getClaimYNIdList().get(i);
//				        //obj[18] = bean.getClaimAmountList().get(i);
//				       // obj[19] = bean.getNoClaimBonusIdList().get(i);
//				        obj[15] = ""/*bean.getAreaCoverageIdList().get(i)*/;
//				        //obj[21] = bean.getDriverDOBList().get(i);
//				        //obj[17] = polStratDate;
//				        //obj[18] = polExpiryDate;
//				        obj[16] = bean.getPolicyStartDate();
//				        obj[17] = bean.getPolicyEndDate();
//				        obj[18] = "";// bean.getTpl()==null?"0":bean.getTpl();
//				        obj[19] = "";//bean.getExcess();
//				        obj[20] = "";
//						obj[21] = "";
//						obj[22] = ""/*bean.getReferralMsg()*/;
//						obj[23] = bean.getDeductibleIdList().get(i);
//						//obj[30] = bean.getDriverIdList().get(i);
//						obj[24] = bean.getElectricalAccList().get(i);
//						obj[25] = bean.getNonElectricalAccList().get(i);
//						obj[26] = vehicleId;
//						
//						obj[27] = bean.getVehicleColourIdList().get(i);
//						obj[28] = bean.getLeasedYNIdList().get(i);
//						obj[29] = StringUtils.isBlank(bean.getBankOfFinanceIdList().get(i))?"":bean.getBankOfFinanceIdList().get(i);
//						obj[30] = bean.getRegNoList().get(i);
//						obj[31] = bean.getChassisNoList().get(i);
//						obj[32] = bean.getEngineNoList().get(i);
//						obj[33] = "";
//						obj[34] = "";
//						obj[35] = "";
//						obj[36] = "";
//						obj[37] = bean.getCubicCapacityList().get(i);
//						//obj[45] = bean.getPrevPolicyNoList().get(i);
//						//obj[46] = bean.getPrevExpiryDateList().get(i);
//						//obj[47] = bean.getPrevInsCompanyIdList().get(i);
//						obj[38] = selectedExcess;
//						obj[39] = bean.getPolicyType();
//						obj[40] = bean.getCurrencyType();
//						obj[41] = bean.getCustomerType();
//						obj[42] = bean.getQuoteNo();
//						obj[43] = bean.getPrevClaimYn().get(i);
//						obj[44] = StringUtils.isBlank(bean.getNoOfClaims().get(i))?"0":bean.getNoOfClaims().get(i);
//						
//						removeNull(obj);
//				        LogManager.info("updateVehicleDetailsNew Sql=>=>"+queryFrammer(sql, obj));
//						//LogManager.info("Obj[]=>"+StringUtils.join(obj,","));
//						int res=this.mytemplate.update(sql,obj);
//						LogManager.info("updateVehicleDetailsNew Result=>"+res);
//					}
//					else{
//						sql=this.getQuery("INS_MOTOR_DATA_DTLS_B2B_NEW");
//						obj=new Object[44];
//						
//						obj[0] = bean.getCustomerId();
//						obj[1] = bean.getProductId();
//						obj[2] = "0";
//						obj[3] = bean.getApplicationNo();
//						obj[4] = "Y";
//						obj[5] = bean.getMakeIdList().get(i);
//						obj[6] = bean.getModelIdList().get(i);
//						obj[7] = bean.getTypeBodyIdList().get(i);
//						obj[8] = bean.getMfgYrIdList().get(i);
//						//obj[10] = bean.getOwnerDriverYNList().get(i);
//						obj[9] = bean.getSeatingCapacityList().get(i);
//						obj[10] = bean.getSumInsuredList().get(i);
//						obj[11] = "N"/*bean.getAgencyRepairYNIdList().get(i)*/;
//				        obj[12] = ""/*bean.getNoOfCylinderIdList().get(i)*/;
//				        obj[13] = bean.getVehicleUsageIdList().get(i);
//				        obj[14] = "";
//				        //obj[17] = bean.getClaimYNIdList().get(i);
//				        //obj[18] = bean.getClaimAmountList().get(i);
//				       // obj[19] = bean.getNoClaimBonusIdList().get(i);
//				        obj[15] = ""/*bean.getAreaCoverageIdList().get(i)*/;
//				        //obj[21] = bean.getDriverDOBList().get(i);
//				        //obj[17] = polStratDate;
//				        //obj[18] = polExpiryDate;
//				        obj[16] = bean.getPolicyStartDate();
//				        obj[17] = bean.getPolicyEndDate();
//				        obj[18] = "";// bean.getTpl()==null?"0":bean.getTpl();
//				        obj[19] = "";//bean.getExcess();
//				        obj[20] = "";
//						obj[21] = "";
//						obj[22] = ""/*bean.getReferralMsg()*/;
//						obj[23] = bean.getDeductibleIdList().get(i);
//						//obj[30] = bean.getDriverIdList().get(i);
//						obj[24] = bean.getElectricalAccList().get(i);
//						obj[25] = bean.getNonElectricalAccList().get(i);
//						obj[26] = vehicleId;
//						
//						obj[27] = bean.getVehicleColourIdList().get(i);
//						obj[28] = bean.getLeasedYNIdList().get(i);
//						obj[29] = StringUtils.isBlank(bean.getBankOfFinanceIdList().get(i))?"":bean.getBankOfFinanceIdList().get(i);
//						obj[30] = bean.getRegNoList().get(i);
//						obj[31] = bean.getChassisNoList().get(i);
//						obj[32] = bean.getEngineNoList().get(i);
//						obj[33] = "";
//						obj[34] = "";
//						obj[35] = "";
//						obj[36] = "";
//						obj[37] = bean.getCubicCapacityList().get(i);
//						//obj[45] = bean.getPrevPolicyNoList().get(i);
//						//obj[46] = bean.getPrevExpiryDateList().get(i);
//						//obj[47] = bean.getPrevInsCompanyIdList().get(i);
//						obj[38] = selectedExcess;
//						obj[39] = bean.getPolicyType();
//						obj[40] = bean.getCurrencyType();
//						obj[41] = bean.getCustomerType();
//						obj[42] = bean.getPrevClaimYn().get(i);
//						obj[43] = StringUtils.isBlank(bean.getNoOfClaims().get(i))?"0":bean.getNoOfClaims().get(i);
//						
//						removeNull(obj);
//				        LogManager.info("updateVehicleDetailsNew Sql=>=>"+queryFrammer(sql, obj));
//						//LogManager.info("Obj[]=>"+StringUtils.join(obj,","));
//						int res=this.mytemplate.update(sql,obj);
//						LogManager.info("updateVehicleDetailsNew Result=>"+res);
//					}
//				}
//				else {
//						sql=this.getQuery("UPD_MOTOR_DATA_DTLS_B2B_NEW");
//						obj=new Object[41];
//						obj[0] = bean.getCustomerId();
//						obj[1] = bean.getMakeIdList().get(i);
//						obj[2] = bean.getModelIdList().get(i);
//						obj[3] = bean.getTypeBodyIdList().get(i);
//						obj[4] = bean.getMfgYrIdList().get(i);
//						//obj[5] = bean.getOwnerDriverYNList().get(i);
//						obj[5] = bean.getSeatingCapacityList().get(i);
//						obj[6] = bean.getSumInsuredList().get(i);
//						obj[7] = "N"/*bean.getAgencyRepairYNIdList().get(i)*/;
//				        obj[8] = ""/*bean.getNoOfCylinderIdList().get(i)*/;
//				        obj[9] = bean.getVehicleUsageIdList().get(i);
//				        obj[10] = "";
//				        //obj[12] = bean.getClaimYNIdList().get(i);
//				        //obj[13] = bean.getClaimAmountList().get(i);
//				        //obj[14] = bean.getNoClaimBonusIdList().get(i);
//				        obj[11] = ""/*bean.getAreaCoverageIdList().get(i)*/;
//				        //obj[16] = bean.getDriverDOBList().get(i);
//				        //obj[12] = polStratDate;
//				        //obj[13] = polExpiryDate;
//				        obj[12] = bean.getPolicyStartDate();
//				        obj[13] = bean.getPolicyEndDate();
//				        obj[14] = "";// bean.getTpl()==null?"0":bean.getTpl();
//				        obj[15] = "";//bean.getExcess();
//				        obj[16] = ""/*bean.getDriverNationalityList().get(i)*/;
//						obj[17] = ""/*bean.getUaeLicExpDTIdList().get(i)*/;
//						obj[18] = ""/*bean.getReferralMsg()*/;
//						obj[19] = bean.getDeductibleIdList().get(i);
//						//obj[25] = bean.getDriverIdList().get(i);
//						obj[20] = bean.getElectricalAccList().get(i);
//						obj[21] = bean.getNonElectricalAccList().get(i);
//						
//						obj[22] = bean.getVehicleColourIdList().get(i);
//						obj[23] = bean.getLeasedYNIdList().get(i);
//						obj[24] = StringUtils.isBlank(bean.getBankOfFinanceIdList().get(i))?"":bean.getBankOfFinanceIdList().get(i);
//						obj[25] = bean.getRegNoList().get(i);
//						obj[26] = bean.getChassisNoList().get(i);
//						obj[27] = bean.getEngineNoList().get(i);
//						obj[28] = "";
//						obj[29] = "";
//						obj[30] = "";
//						obj[31] = "";
//						obj[32] = bean.getCubicCapacityList().get(i);
//						//obj[39] = bean.getPrevPolicyNoList().get(i);
//						//obj[40] = bean.getPrevExpiryDateList().get(i);
//						//obj[41] = bean.getPrevInsCompanyIdList().get(i);
//						obj[33] = selectedExcess;
//						obj[34] = bean.getPolicyType();
//						obj[35] = bean.getCurrencyType();
//						obj[36] = bean.getCustomerType();
//						obj[37] = bean.getPrevClaimYn().get(i);
//						obj[38] = StringUtils.isBlank(bean.getNoOfClaims().get(i))?"0":bean.getNoOfClaims().get(i);
//				        obj[39] = bean.getApplicationNo();
//				        obj[40] = vehicleId;
//					
//			        removeNull(obj);
//			        LogManager.info("updateVehicleDetailsNew Sql=>=>"+queryFrammer(sql, obj));
//					//LogManager.info("Obj[]=>"+StringUtils.join(obj,","));
//					int res=this.mytemplate.update(sql,obj);
//					LogManager.info("updateVehicleDetailsNew Result=>"+res);
//				}
//			}
//		}
//	}
	
	private void updateQuoteDetails(MotorBean bean) throws Exception {
		String query = "";
		Object[] args = null;
		query = getQuery("CNT_MOTOR_POLICY_DETAILS");
		args = new Object[]{bean.getApplicationNo()};
		removeNull(args);
		LogManager.info("query==>"+queryFrammer(query, args));
		//LogManager.info("args[]==>"+StringUtils.join(args,", "));
		int count = this.mytemplate.queryForInt(query, args);
		
		if(count==0) {
			query = getQuery("INS_MOTOR_POLICY_DETAILS");
			args = new Object[6];
			args[0] = bean.getApplicationNo();
			args[1] = bean.getQuoteNo();
			args[2] = bean.getPremiumType();
			args[3] = bean.getPolicyStartDate();
			args[4] = bean.getPolicyEndDate();
			args[5] = bean.getCurrencyType();
		} else {
			query = getQuery("UPD_MOTOR_POLICY_DETAILS");
			args = new Object[5];
			args[0] = bean.getPolicyStartDate();
			args[1] = bean.getPolicyEndDate();
			args[2] = bean.getPremiumType();
			args[3] = bean.getCurrencyType();
			args[4] = bean.getApplicationNo();
		}
		removeNull(args);
		LogManager.info("query==>"+queryFrammer(query, args));
		//LogManager.info("args[]==>"+StringUtils.join(args,", "));
		int res=this.mytemplate.update(query,args);
		LogManager.info("Result=>"+res);
		if(DBConstants.DEVICETYPE_HYBRID.equals(bean.getDeviceType())) {
			updateTPLiablity(bean);
		}
	}
	
	@SuppressWarnings("unchecked")
	public void updateQuoteDetailsNew(MotorBean bean){
		List<String> DriverDOBList = new ArrayList<String>();
		List<Double> BasePrem = new ArrayList<Double>();
		List<String> ElectricalAccList = new ArrayList<String>();
		List<String> NonElectricalAccList = new ArrayList<String>();
		List<String> Deductible = new ArrayList<String>();
		List<Double> BaseRate = new ArrayList<Double>();
		
		try {
		Map<String,Object>	resMap = motorApi.updateQuoteDetailsNew(bean);
		if(resMap!=null) {
			Map<String,Object> quoteinfo = (Map<String,Object>)resMap.get("QuoteInfo");
			if(quoteinfo!=null) {
				bean.setQuoteNo(quoteinfo.get("QuoteNo")==null?"":quoteinfo.get("QuoteNo").toString());
				bean.setCustomerName(quoteinfo.get("CustomerName")==null?"":quoteinfo.get("CustomerName").toString());
				bean.setPolicyType(quoteinfo.get("PolicyType")==null?"":quoteinfo.get("PolicyType").toString());
				bean.setPolicyName(quoteinfo.get("PolicyName")==null?"":quoteinfo.get("PolicyName").toString());
				bean.setQuoteDate(quoteinfo.get("QuoteDate")==null?"":quoteinfo.get("QuoteDate").toString());
				bean.setProduct(quoteinfo.get("ProductName")==null?"":quoteinfo.get("ProductName").toString());
				bean.setEmail(quoteinfo.get("Email")==null?"":quoteinfo.get("Email").toString());
				bean.setCurrencyType(quoteinfo.get("Currency")==null?"":quoteinfo.get("Currency").toString());
			}
			List<Map<String,Object>> vehiclinfoList = (List<Map<String,Object>>)resMap.get("VehicleInfo");
			if(vehiclinfoList!=null) {
				bean.setVehiclinfoList(vehiclinfoList);
			}
			
			Map<String,Object> customerinfo = (Map<String,Object>)resMap.get("CustomerInfo");
			if(customerinfo!=null) {
				bean.setTitle(customerinfo.get("Title")==null?"":customerinfo.get("Title").toString());
				bean.setTitle(customerinfo.get("Title")==null?"":customerinfo.get("Title").toString());
				bean.setCustName(customerinfo.get("FirstName")==null?"":customerinfo.get("FirstName").toString());
				bean.setCustLastName(customerinfo.get("LastName")==null?"":customerinfo.get("LastName").toString());
				DriverDOBList.add(customerinfo.get("DateOfBirth")==null?"":customerinfo.get("DateOfBirth").toString());
				bean.setGender(customerinfo.get("Gender")==null?"":customerinfo.get("Gender").toString());
				bean.setOccupation(customerinfo.get("Occupation")==null?"":customerinfo.get("Occupation").toString());
				bean.setAddress1(customerinfo.get("Address")==null?"":customerinfo.get("Address").toString());
				bean.setCity(customerinfo.get("City")==null?"":customerinfo.get("City").toString());
				bean.setPoBox(customerinfo.get("PoBox")==null?"":customerinfo.get("PoBox").toString());
				bean.setMobileNo(customerinfo.get("MobileNo")==null?"":customerinfo.get("MobileNo").toString());
				bean.setCustAlterMobileNo(customerinfo.get("AlternatMobileNo")==null?"":customerinfo.get("AlternatMobileNo").toString());
				bean.setEmail(customerinfo.get("Email")==null?"":customerinfo.get("Email").toString());
				
			if(StringUtils.isNotBlank(customerinfo.get("Nrc").toString())) {
				String [] nrc = customerinfo.get("Nrc").toString().split("/");
				if(nrc!=null) {
					bean.setCustnrc1(nrc[0].toString());
					bean.setCustnrc2(nrc[1].toString());
					bean.setCustnrc3(nrc[2].toString());
				}
			}else {
				bean.setCustnrc1(null);
				bean.setCustnrc2(null);
				bean.setCustnrc3(null);
			}
				bean.setCustPassportNo(customerinfo.get("PassportNo")==null?"":customerinfo.get("PassportNo").toString());
				bean.setCustomerType(customerinfo.get("CustomerType")==null?"":customerinfo.get("CustomerType").toString());
				bean.setCompanyRegNo(customerinfo.get("CompanyRegNo")==null?"":customerinfo.get("CompanyRegNo").toString());
				bean.setBrokerCode(customerinfo.get("BrokerCode")==null?"":customerinfo.get("BrokerCode").toString());
				bean.setCustomerId(customerinfo.get("CustomerId")==null?"":customerinfo.get("CustomerId").toString());
			}
			bean.setDriverDOBList(DriverDOBList);
			
			Map<String,Object> premium = (Map<String,Object>)resMap.get("Premium");
			if(premium!=null) {
				BasePrem.add(Double.parseDouble(premium.get("BasePremium")==null?"":premium.get("BasePremium").toString()));
				ElectricalAccList.add(premium.get("ElectricalAccesPremium")==null?"":premium.get("ElectricalAccesPremium").toString());
				NonElectricalAccList.add(premium.get("NonElectricalAccesPremium")==null?"":premium.get("NonElectricalAccesPremium").toString());
				bean.setTotalCoverPremium(premium.get("PremiumBeforTax")==null?"":premium.get("PremiumBeforTax").toString());
				bean.setPolicyFee(premium.get("PolicyFees")==null?"":premium.get("PolicyFees").toString());
				bean.setTotalPremium(premium.get("OverAllPremium")==null?"":premium.get("OverAllPremium").toString());
				Deductible.add(premium.get("Deductibles")==null?"":premium.get("Deductibles").toString());
				BaseRate.add(Double.parseDouble(premium.get("BasePremiumRate")==null?"":premium.get("BasePremiumRate").toString()));
			}
			bean.setBasePrem(BasePrem);
			bean.setElectricalAccList(ElectricalAccList);
			bean.setNonElectricalAccList(NonElectricalAccList);
			bean.setDeductible(Deductible);
			List<Map<String,Object>> premiumInfo = new ArrayList<Map<String,Object>>();
			premiumInfo.add(premium);
			bean.setPremiumInfo(premiumInfo);
			bean.setBaseRate(BaseRate);
		}
		
		bean.setReferal(resMap.get("ReferalStatus")==null?"":resMap.get("ReferalStatus").toString());
		bean.setApplicationNo(resMap.get("ApplicationNo")==null?"":resMap.get("ApplicationNo").toString());
		
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
		
//		String query = "";
//		Object[] args = null;
//		String polStDate="";
//		String polEndDate="";
//		String polType="";
//		String currencyType="";
//		
//		query = getQuery("CNT_MOTOR_POLICY_DETAILS");
//		args = new Object[]{bean.getApplicationNo()};
//		removeNull(args);
//		LogManager.info("query==>"+queryFrammer(query, args));
//		//LogManager.info("args[]==>"+StringUtils.join(args,", "));
//		int count = this.mytemplate.queryForInt(query, args);
//		if(count>0){
//			List<Map<String,Object>> res=new ArrayList<Map<String,Object>>();
//			
//			String qry="SELECT TO_CHAR(POLICYSTARTDATE,'DD/MM/YYYY') POLICYSTARTDATE,TO_CHAR(POLICYENDDATE,'DD/MM/YYYY') POLICYENDDATE,ISSELECTCOVER,CURRENCY_TYPE FROM MOTOR_POLICY_DETAILS WHERE APPLICATIONNO=?";
//			args = new Object[1];
//			args[0] = bean.getApplicationNo();
//			LogManager.info("query==>"+queryFrammer(qry, args));
//			res=this.mytemplate.queryForList(qry, args);
//			if(res!=null && res.size()>0){
//				polStDate=res.get(0).get("POLICYSTARTDATE")==null?"":res.get(0).get("POLICYSTARTDATE").toString();
//				polEndDate=res.get(0).get("POLICYENDDATE")==null?"":res.get(0).get("POLICYENDDATE").toString();
//				polType=res.get(0).get("ISSELECTCOVER")==null?"":res.get(0).get("ISSELECTCOVER").toString();
//				currencyType=res.get(0).get("CURRENCY_TYPE")==null?"":res.get(0).get("CURRENCY_TYPE").toString();
//			}
//		}
//		if(count==0) {
//			query = getQuery("INS_MOTOR_POLICY_DETAILS_NEW");
//			args = new Object[7];
//			args[0] = bean.getApplicationNo();
//			args[1] = bean.getQuoteNo();
//			args[2] = bean.getPremiumType();
//			args[3] = bean.getPolicyStartDate();
//			args[4] = bean.getPolicyEndDate();
//			args[5] = bean.getCurrencyType();
//			args[6] = bean.getPolicyType();
//		} else {
//			query = getQuery("UPD_MOTOR_POLICY_DETAILS_NEW");
//			args = new Object[6];
//			args[0] = StringUtils.isBlank(bean.getPolicyStartDate())?polStDate:bean.getPolicyStartDate();
//			args[1] = StringUtils.isBlank(bean.getPolicyEndDate())?polEndDate:bean.getPolicyEndDate();
//			args[2] = bean.getPremiumType();
//			args[3] = StringUtils.isBlank(bean.getCurrencyType())?currencyType:bean.getCurrencyType();
//			args[4] = StringUtils.isBlank(bean.getPolicyType())?polType:bean.getPolicyType();
//			args[5] = bean.getApplicationNo();
//		}
//		removeNull(args);
//		LogManager.info("query==>"+queryFrammer(query, args));
//		//LogManager.info("args[]==>"+StringUtils.join(args,", "));
//		int res=this.mytemplate.update(query,args);
//		LogManager.info("Result=>"+res);
//		
//		query="UPDATE MOTOR_DATA_DETAIL SET QUOTE_NO=? WHERE APPLICATION_NO=?";
//		args = new Object[2];
//		args[0] = bean.getQuoteNo();
//		args[1] = bean.getApplicationNo();
//		LogManager.info("quote update query==>"+queryFrammer(query, args));
//		int res1=this.mytemplate.update(query,args);
//		LogManager.info("Result=>"+res1);
//		
//		if(DBConstants.DEVICETYPE_HYBRID.equals(bean.getDeviceType())) {
//			updateTPLiablity(bean);
//		}
//	}
//	
//	/*public void getCoverageInfo(MotorBean bean) {
//		LogManager.info("getCoverageInfo - Enter");
//		try{
//			List policyTypes=collectPolicytypeInfo(bean.getBranchCode(), bean.getLoginId(),bean.getProductId());
//			List optionalCovers;
//			String sql="";
//			Object []obj=null;
//			int count;		
//			String q;
//			String policyTypeIds = "0";
//			final int val=Integer.parseInt(bean.getSeatingCapacity())-1;
//			for(int i=0; i<policyTypes.size(); i++){
//				Map map=(Map)policyTypes.get(i);
//				policyTypeIds = policyTypeIds+"','"+((Map)policyTypes.get(i)).get("POLICYTYPE_ID");
//			}
//			sql=LocalizedTextUtil.findDefaultText("DEL_MOTOR_POL_COVERAGE", Locale.ENGLISH, new String[]{policyTypeIds});
//			obj=new Object[1];
//			obj[0]=bean.getApplicationNo();
//			LogManager.info("Sql=>"+sql);
//			LogManager.info("Obj[]=>"+StringUtils.join(obj,","));
//			int res=0;
//			this.mytemplate.update(sql,obj);
//			for(int i=0; i<policyTypes.size(); i++)
//			{
//				Map map=(Map)policyTypes.get(i);
//				insertPolicyCover(bean, map.get("POLICYTYPE_ID").toString(), map.get("POLICYTYPE_DESC_ENGLISH").toString());
//				sql=this.getQuery("GET_MOTOR_POL_COV_COUNT");
//				obj=new Object[]{bean.getApplicationNo(),map.get("POLICYTYPE_ID").toString()};
//				LogManager.info("Sql=>"+sql);
//				LogManager.info("Obj[]=>"+StringUtils.join(obj,","));
//				int count1 = this.mytemplate.queryForInt(sql, obj);
//				LogManager.info("Result=>"+count1);
//				if(count1!=0){
//					optionalCovers=collectOpencoverNewInfo2(bean.getBranchCode(),map.get("POLICYTYPE_ID").toString(),bean.getTypeBody());
//					for(int j=0; j<optionalCovers.size(); j++)
//					{
//						Map optCovMap=(Map)optionalCovers.get(j);
//						sql=this.getQuery("GET_MOTOR_POL_COV_CNT");
//						obj=new Object[3];
//						obj[0]=bean.getApplicationNo();
//						obj[1]= map.get("POLICYTYPE_ID");
//						obj[2]=optCovMap.get("POLICYTYPE_COVERID");
//						count=this.mytemplate.queryForInt(sql,obj);
//						String premiumAndRate=",RATE,RATE";
//						String rate="RATE";
//						if(optCovMap.get("CALC_TYPE")!=null && "G".equalsIgnoreCase(optCovMap.get("CALC_TYPE").toString()))
//						{
//							String[][] premiumDetails=getOpCoverPremiumWithBaseRate(optCovMap.get("POLICYCOVER_ID").toString(), bean.getProductId(), map.get("POLICYTYPE_ID").toString(), bean.getTypeBody(), bean.getSumInsured());
//							if(premiumDetails!=null && premiumDetails.length>0)
//							{
//								premiumAndRate=","+premiumDetails[0][1]+","+premiumDetails[0][0];
//								rate=premiumDetails[0][1];
//							}
//						}
//						
//						if(count==0)
//						{
//							sql=LocalizedTextUtil.findDefaultText("INS_MOTOR_POL_COV_DTL", Locale.ENGLISH, new String[]{val+"",rate,premiumAndRate});
//							obj=new Object[9];
//							obj[0]=bean.getApplicationNo();
//							obj[1]=map.get("POLICYTYPE_ID");
//							obj[2]=map.get("POLICYTYPE_DESC_ENGLISH");
//							obj[3]=optCovMap.get("POLICYTYPE_COVERID");
//							obj[4]=optCovMap.get("OPCOVER_DESC_ENGLISH");
//							obj[5]=optCovMap.get("POLICYTYPE_COVERID");
//							obj[6]=bean.getBranchCode();
//							obj[7]=optCovMap.get("POLICYTYPE_COVERID");
//							obj[8]=bean.getBranchCode();
//							LogManager.info("Sql=>=>"+sql);
//							LogManager.info("Obj[]=>"+StringUtils.join(obj,","));
//							res=this.mytemplate.update(sql,obj);
//							LogManager.info("Result=>"+res);
//						}else{
//							sql=LocalizedTextUtil.findDefaultText("UPD_MOTOR_POL_COV_DTL", Locale.ENGLISH, new String[]{val+"",rate,premiumAndRate});
//							obj=new Object[6];
//							obj[0]=bean.getBranchCode();
//							obj[1]=optCovMap.get("POLICYTYPE_COVERID");
//							obj[2]=bean.getBranchCode();
//							obj[3]=bean.getApplicationNo();
//							obj[4]=map.get("POLICYTYPE_ID");
//							obj[5]=optCovMap.get("POLICYTYPE_COVERID");
//							LogManager.info("Sql=>=>"+sql);
//							LogManager.info("Obj[]=>"+StringUtils.join(obj,","));
//							res=this.mytemplate.update(sql,obj);
//							LogManager.info("Result=>"+res);
//						}
//					}
//				}
//			}
//		}catch(Exception e)
//		{
//			e.printStackTrace();
//		}
//		LogManager.popRemove();
//		LogManager.info("getCoverageInfo - Exit");
//	}*/
	
	public List collectPolicytypeInfo(final String branchid, final String loginID, final String productId) {
		LogManager.info("collectPolicytypeInfo-Enter");
		List policytype=null;
		try{
			Object[] obj=new Object[3];
			String sql =this.getQuery("GET_MOTOR_POL_TYPE_IDS"); 
			obj[0]=loginID;
			obj[1]=branchid;
			obj[2]=productId;	
			LogManager.info("Sql=>=>"+sql);
			LogManager.info("Obj[]=>"+StringUtils.join(obj,","));
			List polTypeList=this.mytemplate.queryForList(sql,obj);
			LogManager.info("Result=>=>"+polTypeList.size());
			String st="";
			if(polTypeList.size()>0){
				st="AND POLICYTYPE_ID in("+((Map)polTypeList.get(0)).get("POLICYTYPE_IDS").toString()+",2)";
			}
			sql=getQuery("GET_MOTOR_POL_MSTR", new Object[]{st});
			obj=new Object[2];
			obj[0]=branchid;
			obj[1]=branchid;
			LogManager.info("Sql=>=>"+sql);
			LogManager.info("Obj[]=>"+StringUtils.join(obj,","));
			policytype = this.mytemplate.queryForList(sql,obj);
			LogManager.info("Result=>=>"+policytype);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		LogManager.popRemove();
		LogManager.info("collectPolicytypeInfo - Exit");
		return policytype;
	}
	
	/*public void insertPolicyCover(final MotorBean bean, final String policyType, final String policyName) {//insertDataValue MotorQuoteData line NO 1101
		try{
			boolean flag=false;
			boolean driveAge = false;
			boolean uaeLicence = false;
			boolean vehicleType = false;
			String query="";
			List rateInfo = new ArrayList();
			final List ID=getPolicyTypeCoverID2(bean, policyType);
			String sql=this.getQuery("GET_MOTOR_AGENCY_CODE");
			Object[] obj=new Object[]{bean.getApplicationNo()};
			LogManager.info("Sql=>"+sql);
			LogManager.info("Obj[]=>"+StringUtils.join(obj,","));
			final String brokerCode = (String)this.mytemplate.queryForObject(sql, obj,String.class);
			LogManager.info("Result=>"+brokerCode);
			final int driverAge = getDriverAgeCount(bean.getTypeBody(), bean.getDriverDOB(), bean.getBranchCode(), bean.getProductId(),brokerCode);
			if(driverAge<=0){
				driveAge = true;
			}
			final int uaeLicense = getUaeLicenceCount(bean.getTypeBody(), bean.getBranchCode(), bean.getProductId(),brokerCode);		
			if(uaeLicense<=0){
				uaeLicence = true;
			}
			final int vehicleTypes = getVehicleTypeCount(bean,brokerCode);
			if(vehicleTypes<=0){
				vehicleType = true;
			}		
			
			final String typeInfo = getTypeInfo(bean.getTypeBody(), bean.getModel(), bean.getVehicleUsage(), bean.getDriverDOB(), bean.getBranchCode(), bean.getProductId(),driveAge,uaeLicence,vehicleType,brokerCode);
			
			if(typeInfo!=null&&typeInfo.length()>0 && ID!=null&&ID.size()>0){	
				Map idMap=(Map)ID.get(0);
				sql=this.getQuery("GET_MOTOR_YEAR_CNT");
				obj=new Object[]{bean.getMfgYr()};
				LogManager.info("Sql=>"+sql);
				LogManager.info("Obj[]=>"+bean.getMfgYr());
				final String vehicleyear=this.mytemplate.queryForInt(sql,obj)+"";
				LogManager.info("Result=>"+vehicleyear);
				sql = this.getQuery("GET_MOTOR_VEHICLE_AGE");
				obj=new Object[4];
				obj[0]=bean.getBranchCode();
				obj[1]=bean.getProductId();
				obj[2]=bean.getTypeBody();
				obj[3]=vehicleyear;
				LogManager.info("Sql=>"+sql);
				LogManager.info("Obj[]=>"+StringUtils.join(obj,","));
				final int vehicleAge=this.mytemplate.queryForInt(sql,obj);
				if(vehicleAge>0){
					if(!"2".equalsIgnoreCase(policyType)){
						LogManager.info("Comprehensive Premium || Enter");
						sql=this.getQuery("GET_MOTOR_COMPREHENSIVE_RATE");
						obj=new Object[20];
						obj[0]=bean.getLoginId();
						obj[1]=bean.getProductId();
						obj[2]=bean.getLoginId();
						obj[3]=bean.getProductId();
						obj[4]=bean.getLoginId();
						obj[5]=bean.getProductId();
						obj[6]=bean.getLoginId();
						obj[7]=bean.getProductId();
						obj[8]="Y".equalsIgnoreCase(bean.getAgencyRepairYN())?vehicleyear:"99999";
						obj[8]="0";
						obj[9]=bean.getSeatingCapacity();
						obj[10]=bean.getSumInsured();
						obj[11]=typeInfo;
						obj[12]=bean.getBranchCode();
						obj[13]=bean.getProductId();
						obj[14]=policyType;
						obj[15]="Y".equalsIgnoreCase(bean.getAgencyRepairYN())?vehicleyear:"99999";
						obj[15]="0";
						obj[16]=typeInfo;
						obj[17]=bean.getBranchCode();
						obj[18]=bean.getProductId();
						obj[19]=policyType;		
						
					}else{
						LogManager.info("Third Party Premium || Enter");
						sql=this.getQuery("GET_MOTOR_THIRD_PARTY_RATE");
						obj = new Object[8];
						obj[0]=typeInfo;
						obj[1]=bean.getNoOfCylinder();
						obj[2]=bean.getBranchCode();
						obj[3]=bean.getProductId();
						obj[4]=typeInfo;
						obj[5]=bean.getNoOfCylinder();
						obj[6]=bean.getBranchCode();
						obj[7]=bean.getProductId();
					}
					LogManager.info("Sql=>"+sql);
					LogManager.info("Obj[]=>"+StringUtils.join(obj,","));
					rateInfo = this.mytemplate.queryForList(sql,obj);
					sql=this.getQuery("GET_MOTOR_POL_COVER_DATA_RATE");
					obj=new Object[]{bean.getApplicationNo(),idMap.get("POLICYTYPE_COVERID")};
					LogManager.info("Sql=>"+sql);
					LogManager.info("Obj[]=>"+StringUtils.join(obj,","));
					List rateList=this.mytemplate.queryForList(sql,obj);
					String previousRate=""; 
					if(rateList==null || rateList.size()<=0){
						sql=this.getQuery("GET_MOTOR_BRPOLTYPEMSTR_RATE");
						obj=new Object[]{bean.getProductId(),bean.getLoginId(),bean.getBranchCode(),policyType};
						List rates = this.mytemplate.queryForList(sql,obj);
						if(rates!=null && rates.size()>0){
							Map rateMap=(Map)rates.get(0);
							double value = 0.0;
							final List tempList = new ArrayList();
							if(rateMap.get("RATE_ONE")!=null){
									tempList.add(rateMap.get("RATE_ONE").toString());
							}
							if(rateMap.get("RATE_TWO")!=null){
								tempList.add(rateMap.get("RATE_TWO").toString());
							}
							if(rateMap.get("RATE_THREE")!=null){
								tempList.add(rateMap.get("RATE_THREE").toString());
							}
							if(rateMap.get("RATE_FOUR")!=null){
								tempList.add(rateMap.get("RATE_FOUR").toString());
							}
							if(tempList.size()>0){
								value = Double.parseDouble((String)tempList.get(0));
								for(int i=0;i<tempList.size();i++){
									String tempVar = (String)tempList.get(i);
									value = Double.parseDouble(tempVar)<value?Double.parseDouble(tempVar):value;
								}
							}
							previousRate = value+"";
						}else{
							previousRate = "0";
						}
					}
					double rate=0.0,baserate=0.0,minpremium=0.0,suminsured=0.0,actualpremium=0.0;
					if(bean.getReferralMsg()!=null && bean.getReferralMsg().length()>0 && rateInfo.size()<=0) {
						Map<String,String> rateMap = new HashMap<String,String>();
						List<Map<String,String>> trateInfo = new ArrayList<Map<String,String>>();
						rateMap.put("RATE","0");
						rateMap.put("PREMIUM","0");
						trateInfo.add(rateMap);
						rateInfo = trateInfo;
					}
					if(rateInfo.size()>0){
						Map rateMap=(Map)rateInfo.get(0);
						if(policyType!=null && !("2".equalsIgnoreCase(policyType))){
							rate=Double.parseDouble(rateMap.get("RATE").toString());
							baserate = rate;
							if(previousRate!=null && previousRate.length()>0){
								try{
									rate = Double.parseDouble(previousRate)>rate?Double.parseDouble(previousRate):rate;
								}catch(Exception e){
									LogManager.info("Previous Rate was not available");
								}
							}
							minpremium=Double.parseDouble(rateMap.get("PREMIUM").toString());
							suminsured=Double.parseDouble(bean.getSumInsured());
							actualpremium=(rate*suminsured);
							actualpremium=actualpremium<minpremium?minpremium:actualpremium;
							LogManager.info("rate"+rate);
							LogManager.info("suminsured"+suminsured);					
						}else{
							actualpremium = Double.parseDouble(rateMap.get("RATE").toString());
							baserate = actualpremium;
							if(previousRate!=null && previousRate.length()>0){
								try{
									actualpremium = Double.parseDouble(previousRate)>actualpremium?Double.parseDouble(previousRate):actualpremium;
								}catch(Exception e){
									LogManager.info("Previous Rate was not available");
								}
							}
							minpremium=actualpremium;
							rate = actualpremium;
						}
						LogManager.info("Actual Premium==>"+actualpremium);
						
						sql=this.getQuery("DEL_MOTOR_POL_COV1");
						obj=new Object[]{bean.getApplicationNo(),policyType,idMap.get("POLICYTYPE_COVERID")};
						LogManager.info("Sql=>"+sql);
						LogManager.info("Obj[]=>"+StringUtils.join(obj,","));
						int res=this.mytemplate.update(sql,obj);
						LogManager.info("Result=>"+res);
						sql=this.getQuery("GET_MOTOR_POL_COV_CNT");
						obj=new Object[]{bean.getApplicationNo(),policyType,idMap.get("POLICYTYPE_COVERID")};
						LogManager.info("Sql=>"+sql);
						LogManager.info("Obj[]=>"+StringUtils.join(obj,","));
						int count=this.mytemplate.queryForInt(sql,obj);
						if(count==0){
							sql=this.getQuery("INS_MOTOR_POL_COV");
							obj=new Object[14];
							obj[0]=bean.getApplicationNo();
							obj[1]=policyType;
							obj[2]=policyName;
							obj[3]=idMap.get("POLICYTYPE_COVERID");
							obj[4]=bean.getBranchCode();
							obj[5]=actualpremium;
							obj[6]=idMap.get("GROUP_ID");
							obj[7]=idMap.get("DESCRIPTION");
							obj[8]=idMap.get("IS_SELECTED");
							obj[9]=idMap.get("IS_ADDON");
							obj[10]=idMap.get("DELETABLE");
							obj[11]=rate;
							obj[12]=baserate;
							obj[13]=minpremium;
							LogManager.info("Sql=>"+sql);
							LogManager.info("Obj[]=>"+StringUtils.join(obj,","));
							res=this.mytemplate.update(sql,obj);
							LogManager.info("Result=>"+res);
						}else{
							sql=this.getQuery("UPD_MOTOR_POL_COV");
							obj=new Object[12];
							obj[0]=actualpremium;
							obj[1]=idMap.get("GROUP_ID");
							obj[2]=idMap.get("DESCRIPTION");
							obj[3]=idMap.get("IS_SELECTED");
							obj[4]=idMap.get("IS_ADDON");
							obj[5]=idMap.get("DELETABLE");
							obj[6]=rate;
							obj[7]=baserate;
							obj[8]=minpremium;
							obj[9]=bean.getApplicationNo();
							obj[10]=policyType;
							obj[11]=idMap.get("POLICYTYPE_COVERID");
							LogManager.info("Sql=>"+sql);
							LogManager.info("Obj[]=>"+StringUtils.join(obj,","));
							res=this.mytemplate.update(sql,obj);
							LogManager.info("Result=>"+res);
						}
					}else{
						flag = true;
					}
				}else{
					flag=true;
				}
			}else{
				flag=true;
			}
			
			if(flag){
				sql=this.getQuery("DEL_MOTOR_POL_COV2");
				obj=new Object[]{bean.getApplicationNo(),policyType};
				LogManager.info("Sql=>"+sql);
				LogManager.info("Obj[]=>"+StringUtils.join(obj,","));
				int res=this.mytemplate.update(sql,obj);
				LogManager.info("Result=>"+res);
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		LogManager.popRemove();
		LogManager.info("getQuoteInfo - Exit");
	}*/
	/*public List getPolicyTypeCoverID2(final MotorBean bean, final String policyType) {
		LogManager.info("getPolicyTypeCoverID-Enter");
		List id=null;
		try{
			final Object[] obj=new Object[3];
			final String sql =this.getQuery("GET_MOTOR_COVERAGE_DTLS");
			obj[0] = policyType;
			obj[1]=bean.getBranchCode();
			obj[2]=bean.getTypeBody();
			LogManager.info("Sql=>"+sql);
			LogManager.info("Obj[]=>"+StringUtils.join(obj,","));
			id=this.mytemplate.queryForList(sql,obj);
			LogManager.info("Result=>"+id.size());
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		LogManager.popRemove();
		LogManager.info("getQuoteInfo - Exit");
		return id;
	}*/
	public int getDriverAgeCount(String typeBody, String driverDOB, String branchCode, String productId,final String brokerCode){
		LogManager.info("getDriverAgeCount - Enter");
		int result = 0;
		try{
		    Object []obj = new Object[9];
			obj[0]=typeBody;
			obj[1]=driverDOB;
			obj[2]=branchCode;
			obj[3]=productId;
			obj[4]="Y";
			obj[5]=typeBody;
			obj[6]=branchCode;
			obj[7]=productId;
			obj[8]="Y";
			String sql=getQuery("GET_MOTOR_DRIVER_AGE_CNT", new Object[]{brokerCode+"','None"});
			LogManager.info("Sql=>"+sql);
			LogManager.info("Obj[]=>"+StringUtils.join(obj,","));
			result = this.mytemplate.queryForInt(sql,obj);
			LogManager.info("Result=>"+result);
		}catch(Exception e){
			e.printStackTrace();
		}
		LogManager.info("getDriverAgeCount - Exit");
		return result;
	}
	
	public int getUaeLicenceCount(String typeBody, String branchCode, String productId, final String brokerCode){
		LogManager.info("getUaeLicenceCount - Enter");
		int result = 0;
		try{
			final String sql = getQuery("GET_MOTOR_UAE_LICENCE_CNT", new Object[]{brokerCode+"','None"});
			final Object[] obj = new Object[9];
			obj[0]=typeBody;
			obj[1]="0";//bean.getUaeLicenceNo();
			obj[2]=branchCode;
			obj[3]=productId;
			obj[4]="Y";
			obj[5]=typeBody;
			obj[6]=branchCode;
			obj[7]=productId;
			obj[8]="Y";
			LogManager.info("Sql=>"+sql);
			LogManager.info("Obj[]=>"+StringUtils.join(obj,","));
			result = this.mytemplate.queryForInt(sql,obj);
			LogManager.info("Result=>"+result);
		}catch(Exception e){
			e.printStackTrace();
		}
		LogManager.info("getUaeLicenceCount - Exit");
		return result;
	}
	
	public int getVehicleTypeCount(String typeBody, String vehicleUsage, String branchCode, String productId,final String brokerCode){
		LogManager.info("getVehicleTypeCount - Enter");
		int result = 0;
		try{
			final String sql = getQuery("GET_MOTOR_VEHICLE_TYPE_CNT", new Object[]{brokerCode+"','None"});
			final String[] obj = new String[9];
			obj[0]=typeBody;
			obj[1]=vehicleUsage;
			obj[2]=branchCode;
			obj[3]=productId;
			obj[4]="Y";
			obj[5]=typeBody;
			obj[6]=branchCode;
			obj[7]=productId;
			obj[8]="Y";
			LogManager.info("Sql=>"+sql);
			LogManager.info("Obj[]=>"+StringUtils.join(obj,","));
			result = this.mytemplate.queryForInt(sql,obj);
			LogManager.info("Result=>"+result);
		}catch(Exception e){
			LogManager.fatal(e);
		}
		LogManager.info("getVehicleTypeCount - Exit");
		return result;
	}
	
	public String getTypeInfo(String typeBody, String modelId, String vehicleUsage, String driverDOB, String branchCode,String productId, final boolean driverAge, final boolean uaeLicence,final boolean vehicleType,String brokerCode) {
		LogManager.info("getTypeInfo - Enter");
		String typeid="";
		try{
			String sql =this.getQuery("GET_MOTOR_TYPE_INFO1");
			if(!driverAge){
				sql = sql +" "+ getQuery("GET_MOTOR_TYPE_INFO2", new Object[]{driverDOB});
			}
			if(!uaeLicence){
				sql = sql + " "+ getQuery("GET_MOTOR_TYPE_INFO3", new Object[]{"0"});//bean.getUaeLicenceNo()
			}
			if(!vehicleType){
				sql = sql +" "+ getQuery("GET_MOTOR_TYPE_INFO4",  new Object[]{vehicleUsage});
			}
			sql  = sql +" "+ getQuery("GET_MOTOR_TYPE_INFO5", new Object[]{brokerCode+"','None"});
			final String[] obj=new String[8];
			obj[0]=typeBody;
			obj[1]=branchCode;
			obj[2]=productId;
			obj[3]="Y";
			obj[4]=typeBody;
			obj[5]=branchCode;
			obj[6]=productId;
			obj[7]="Y";
			LogManager.info("Sql=>"+sql);
			LogManager.info("Obj[]=>"+StringUtils.join(obj,","));
			final List typeInfo = this.mytemplate.queryForList(sql,obj);
			boolean flag = false;
			if(typeInfo!=null && typeInfo.size()>0){
				for(int i=0;i<typeInfo.size();i++){
					Map typeMap=(Map)typeInfo.get(i);
					if(("-"+typeMap.get("MODEL_ID")+"-").indexOf("-"+modelId+"-")!=-1 && brokerCode.equalsIgnoreCase(typeMap.get("AG_CODE").toString())){
						typeid=typeMap.get("TYPEID").toString();
						flag = true;
					}
					if(!flag){
						if("Nil".equalsIgnoreCase(typeMap.get("MODEL_ID").toString()) && brokerCode.equalsIgnoreCase(typeMap.get("AG_CODE").toString())){
							typeid=typeMap.get("TYPEID").toString();
							flag = true;
						}
						if(!flag){
							if(("-"+typeMap.get("MODEL_ID").toString()+"-").indexOf("-"+modelId+"-")!=-1 && "None".equalsIgnoreCase(typeMap.get("AG_CODE").toString())){
								typeid=typeMap.get("TYPEID").toString();
								flag = true;
							}						
							if(!flag){
								if("Nil".equalsIgnoreCase(typeMap.get("MODEL_ID").toString()) && "None".equalsIgnoreCase(typeMap.get("AG_CODE").toString())){
									typeid=typeMap.get("TYPEID").toString();
								}
							}
						}
					}
				}
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		LogManager.info("getTypeInfo - Exit");
		LogManager.popRemove();
		return typeid;
	}
	public List collectOpencoverNewInfo2(String branchid,String policytypeid,String typeofbody) {
		List list=null;
		try{
			LogManager.info("collectOpencoverInfo-Enter");
			Object[] obj=new Object[3];
			String sql = this.getQuery("GET_MOTOR_POL_COV_DTLS");
			obj[0]=policytypeid;
			obj[1]=typeofbody;
			obj[2]=branchid;
			LogManager.info("Sql=>"+sql);
			LogManager.info("Obj[]=>"+StringUtils.join(obj,","));
			list = this.mytemplate.queryForList(sql,obj);
			LogManager.info("Result=>"+list.size());
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		LogManager.info("getTypeInfo - Exit");
		return list;
	}
	/*public String[][] getOpCoverPremiumWithBaseRate(String mainCover, String productid, String schemeid, String contentTypeId, String sum)
	{
		System.out.println("getOpCoverPremiumWithBaseRate - Enter");
		String [][] result=new String[0][0];
		try{
			double rate=0.0;
			int contId=Integer.parseInt(contentTypeId==null?"0":contentTypeId);
			result=new getCoverageInfo().getBaseRate(mainCover, "0", productid, schemeid, contId, sum);
			if(result!=null && result.length>0)
			{
				rate=Double.parseDouble(result[0][0]==null?"0.0":result[0][0]);
				if("P".equalsIgnoreCase(result[0][1])){
					result[0][0]=(rate/100)+"";
				}else if("M".equalsIgnoreCase(result[0][1])){
					result[0][0]=(rate/1000)+"";
				}
				result[0][1]=new getCoverageInfo().getPremium(result[0][1], rate, sum, "1")+"";				
			}
			
		}catch(Exception e)
		{
			e.printStackTrace();LogManager.info(e);
		}
		System.out.println("getOpCoverPremiumWithBaseRate - Exit || Result Length: "+result.length);
		return result;
	}*/
	public String getQuoteInfo(MotorBean bean, boolean errorStatus) {
		LogManager.info("getQuoteInfo - Enter ");
		String result="";
		try {
			String sql=this.getQuery("GET_MOTOR_QUOTE_INFO");
			//obj=new Object[]{bean.getApplicationNo(),bean.getProductId(),bean.getBranchCode(),bean.getBranchCode(),bean.getBranchCode(),bean.getBranchCode(),bean.getBranchCode(),bean.getBranchCode()};
			//Object[] obj=new Object[]{StringUtils.isBlank(bean.getApplicationNo())?"":bean.getApplicationNo(),bean.getProductId(),bean.getLoginBranch()};
			Object[] obj=new Object[]{StringUtils.isBlank(bean.getApplicationNo())?"":bean.getApplicationNo(),bean.getProductId()};
			LogManager.info("Sql=>=>"+sql);
			LogManager.info("Obj[]=>"+StringUtils.join(obj,","));
			List list=this.mytemplate.queryForList(sql,obj);
			if(list!=null&&list.size()>0)
			{
				Map map=(Map)list.get(0);
				bean.setQuoteDate(map.get("ENTRY_DATE")==null?"":map.get("ENTRY_DATE").toString());
				bean.setProduct(map.get("PRODUCT_NAME")==null?"":map.get("PRODUCT_NAME").toString());
				bean.setMakeName(map.get("MAKE_NAME")==null?"":map.get("MAKE_NAME").toString());
				bean.setModelName(map.get("MODEL_NAME")==null?"":map.get("MODEL_NAME").toString());
				/*bean.setSeatingCapacity(map.get("SEATING_CAPACITY")==null?"":map.get("SEATING_CAPACITY").toString());
				bean.setTypeBody(map.get("BODY")==null?"":map.get("BODY").toString());
				bean.setTypeBodyName(map.get("TYPE_OF_BODY_NAME")==null?"":map.get("TYPE_OF_BODY_NAME").toString());
				bean.setSumInsured(map.get("SUMINSURED_VALUE_LOCAL")==null?"":map.get("SUMINSURED_VALUE_LOCAL").toString());
				bean.setDriverDOB(map.get("DRIVER_DOB")==null?"":map.get("DRIVER_DOB").toString());
				bean.setNoOfCylinder(map.get("NO_OF_CYLINDER")==null?"":map.get("NO_OF_CYLINDER").toString());*/
				bean.setVehicleUsageName(map.get("VEHICLETYPE_DESC_ENGLISH")==null?"":map.get("VEHICLETYPE_DESC_ENGLISH").toString());
				bean.setAgencyRepairName(map.get("AGENCY_REP")==null?"":map.get("AGENCY_REP").toString());
				String polStDate="";
				if(StringUtils.isNotBlank(bean.getPolicyStartDate()) && "getSaveDriver".equalsIgnoreCase(bean.getActionType()))
					polStDate=bean.getPolicyStartDate();
				else
					polStDate=map.get("POLICYSTARTDATE")==null?"":map.get("POLICYSTARTDATE").toString();	
				bean.setPolicyStartDate(polStDate);
				String polEndDate="";
				if(StringUtils.isNotBlank(bean.getPolicyEndDate()) && "getSaveDriver".equalsIgnoreCase(bean.getActionType()))
					polEndDate=bean.getPolicyEndDate();
				else
					polEndDate=map.get("POLICYENDDATE")==null?"":map.get("POLICYENDDATE").toString();
				
				bean.setPolicyEndDate(polEndDate);
				bean.setPolicyEndDatePeriod(map.get("POLICYENDDATE_PERIOD")==null?"":map.get("POLICYENDDATE_PERIOD").toString());
				bean.setPremiumType(map.get("POLICYTYPE")==null?"":map.get("POLICYTYPE").toString());
				if(StringUtils.isBlank(bean.getModeOfPay()))
					bean.setModeOfPay(map.get("PAYMENT_MODE")==null?"":map.get("PAYMENT_MODE").toString());
				if(StringUtils.isBlank(bean.getReferralYN()))
					bean.setReferralYN(map.get("ADMIN_REFERRAL_YN")==null?"":map.get("ADMIN_REFERRAL_YN").toString());
				if("Y".equalsIgnoreCase(bean.getReferralYN()))
					bean.setReferralComments(map.get("SUMMARY_REMARKS")==null?"":map.get("SUMMARY_REMARKS").toString());
				else
					bean.setReferralComments("");
				if(StringUtils.isBlank(bean.getLoadOrDiscPremium()))
						bean.setLoadOrDiscPremium(map.get("EXCESS_PREMIUM")==null?"0.0":Double.parseDouble(map.get("EXCESS_PREMIUM").toString())+"");
				if(StringUtils.isBlank(bean.getSign()))
						bean.setSign(map.get("EXCESS_SIGN")==null?"+":map.get("EXCESS_SIGN").toString());
				bean.setAdminRefStatus(map.get("ADMIN_REFERRAL_STATUS")==null?"":map.get("ADMIN_REFERRAL_STATUS").toString());
				bean.setAdminRemarks(map.get("ADMIN_REMARKS")==null?"":map.get("ADMIN_REMARKS").toString());
				bean.setChqInvNo(map.get("CHQ_INV_NO")==null?"":map.get("CHQ_INV_NO").toString());
				bean.setOptCovers(map.get("OPTIONAL_COVER")==null?"":map.get("OPTIONAL_COVER").toString());
				bean.setLoginId(map.get("LOGIN_ID")==null?"":map.get("LOGIN_ID").toString());
				
				//Motor Policy Details
				String currType="";
				if(StringUtils.isNotBlank(bean.getCurrencyType()) && "getSaveDriver".equalsIgnoreCase(bean.getActionType()))
					currType=bean.getCurrencyType();
				else
					currType=map.get("CURRENCY_TYPE")==null? ResourceBundleUtil.findDefaultText("MOTOR_CURRENCY_LOCAL"):map.get("CURRENCY_TYPE").toString();
				bean.setCurrencyType(currType);
				bean.setTpLiablityYN(map.get("LIABILITY_YN")==null?"":map.get("LIABILITY_YN").toString());
				bean.setTpLiablityAmount(map.get("LIABILITY_SI")==null?"":map.get("LIABILITY_SI").toString());
				String policyTypeDesc="";
				String polType="";
				if(StringUtils.isNotBlank(bean.getPolicyType()) && "getSaveDriver".equalsIgnoreCase(bean.getActionType()))
					polType=bean.getPolicyType();
				else
					polType=map.get("ISSELECTCOVER")==null?"":map.get("ISSELECTCOVER").toString();
				bean.setPolicyType(polType);
				bean.setBrokerBranch(map.get("BROKER_BRANCH")==null?"":map.get("BROKER_BRANCH").toString());
				try{
					String query="SELECT POLICYTYPE_ID,POLICYTYPE_DESC_ENGLISH FROM MOTOR_POLICYTYPE_MASTER WHERE POLICYTYPE_ID=?";
					List listNew=this.mytemplate.queryForList(query,new Object[]{polType});
					if(listNew!=null&&listNew.size()>0)
					{
						Map mapnew=(Map)listNew.get(0);
						policyTypeDesc=(mapnew.get("POLICYTYPE_DESC_ENGLISH")==null?"":mapnew.get("POLICYTYPE_DESC_ENGLISH").toString());
					}
				}catch(Exception e){
					
				}
				bean.setPolicyTypeDesc(policyTypeDesc);
				
				if(errorStatus==false) {
					//Customer Details - Start
					/*bean.setCustomerId(map.get("CUSTOMER_ID")==null?"":map.get("CUSTOMER_ID").toString());
					bean.setTitle(map.get("TITLE")==null?"":map.get("TITLE").toString());
					bean.setCustomerName(map.get("NAME")==null?"":map.get("NAME").toString());
					bean.setMobileNo(map.get("MOBILE")==null?"":map.get("MOBILE").toString());
					bean.setEmail(map.get("EMAIL")==null?"":map.get("EMAIL").toString());
					bean.setAddress1(map.get("ADDRESS1")==null?"":map.get("ADDRESS1").toString());
					bean.setAddress2(map.get("ADDRESS2")==null?"":map.get("ADDRESS2").toString());
					bean.setPoBox(map.get("POBOX")==null?"":map.get("POBOX").toString());
					bean.setCity(map.get("EMIRATE")==null?"":map.get("EMIRATE").toString());
					bean.setCity(StringUtils.isBlank(bean.getCity())?ResourceBundleUtil.findDefaultText("MOTOR_DEFAULT_CITY"):bean.getCity());
					bean.setCustCoreCode(map.get("MISSIPPI_CUSTOMER_CODE")==null?"":map.get("MISSIPPI_CUSTOMER_CODE").toString());
					bean.setCoreAppCode(map.get("MISSIPPI_CUSTOMER_CODE")==null?"":map.get("MISSIPPI_CUSTOMER_CODE").toString());
					bean.setClientCustomerId(map.get("CLIENT_CUSTOMER_ID")==null?"":map.get("CLIENT_CUSTOMER_ID").toString());
					bean.setCustArNo(map.get("CUST_AR_NO")==null?"":map.get("CUST_AR_NO").toString());
					
					bean.setCustLastName(map.get("LAST_NAME")==null?"":map.get("LAST_NAME").toString());
					bean.setCustNameArabic(map.get("CUST_NAME_ARABIC")==null?"":map.get("CUST_NAME_ARABIC").toString());
					bean.setCustnrc(map.get("NRC")==null?"":map.get("NRC").toString());
					if(StringUtils.isNotBlank(bean.getCustnrc())) {
						String[] nrc = bean.getCustnrc().split("/");
						if(nrc.length > 2){
							bean.setCustnrc1(nrc[0]);
							bean.setCustnrc2(nrc[1]);
							bean.setCustnrc3(nrc[2]);
						}
					}
					bean.setCustPassportNo(map.get("PASSPORT_NUMBER")==null?"":map.get("PASSPORT_NUMBER").toString());
					bean.setCustdob(map.get("CUST_DOB")==null?"":map.get("CUST_DOB").toString());
					if(StringUtils.isBlank(bean.getCustdob())) {
						String query = getQuery("GET_DOB_BYDRIVERDOB");
						Object[] args = new Object[]{bean.getApplicationNo()};
						List<Map<String,Object>> tempResult = this.mytemplate.queryForList(query,args);
						if(tempResult.size()>0) {
							Map<String,Object> res = tempResult.get(0);
							bean.setCustdob(res.get("DRIVER_DOB")==null?"":res.get("DRIVER_DOB").toString());
						}
					}
					bean.setCustdobar(map.get("CUST_DOB_AR")==null?"":map.get("CUST_DOB_AR").toString());
					bean.setCustAlterMobileNo(map.get("ALTERNATE_MOBILE")==null?"":map.get("ALTERNATE_MOBILE").toString());
					bean.setCustLandLineNo(map.get("TELEPHONE")==null?"":map.get("TELEPHONE").toString());
					bean.setCustomerType(map.get("CUSTOMER_TYPE")==null?"":map.get("CUSTOMER_TYPE").toString());
					bean.setCompanyRegNo(map.get("COMPANY_REG_NO")==null?"":map.get("COMPANY_REG_NO").toString());
					bean.setGender(map.get("GENDER")==null?"":map.get("GENDER").toString());
					bean.setOccupation(map.get("OCCUPATION")==null?"":map.get("OCCUPATION").toString());
					
					bean.setBrokerCode(map.get("BROKER_CODE")==null?"":map.get("BROKER_CODE").toString());
					bean.setExecutive(map.get("AC_EXECUTIVE_ID")==null?"":map.get("AC_EXECUTIVE_ID").toString());*/
					new CustomerDAO().setCustomerDetails(bean);
					if(StringUtils.isBlank(bean.getCustdob())) {
						String query = getQuery("GET_DOB_BYDRIVERDOB");
						Object[] args = new Object[]{bean.getApplicationNo()};
						List<Map<String,Object>> tempResult = this.mytemplate.queryForList(query,args);
						if(tempResult.size()>0) {
							Map<String,Object> res = tempResult.get(0);
							bean.setCustdob(res.get("DRIVER_DOB")==null?"":res.get("DRIVER_DOB").toString());
						}
					}
					//Customer Details - End
				}
			}
			if(!"admin".equals(bean.getUser()) && !"User".equalsIgnoreCase(bean.getUserType())){
				bean.setShowReferralYN(new com.maan.common.dao.CommonDAO().getReferralYN(bean.getLoginId()));
			}
			/*if(!"Y".equals(bean.getShowReferralYN())) {
				bean.setReferralYN("N");
				bean.setReferralComments("");
			}*/
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		LogManager.popRemove();
		LogManager.info("getQuoteInfo - Exit");
		return result;
	}
	
	public String getVehiclePremiumInfo(MotorBean bean) {
		LogManager.info("getVehiclePremiumInfo - Enter ");
		String result="";
		String sql="";
		Object[] obj=null;
		List<Map<String,Object>> premiumInfo=new ArrayList<Map<String,Object>>();
		try {
			com.maan.common.dao.CommonDAO commonDAO = new com.maan.common.dao.CommonDAO();
			sql=this.getQuery("GET_MOTOR_VEHICLE_PREMIUM_INFO");
			//obj=new Object[]{bean.getApplicationNo(),bean.getBranchCode(),bean.getRateVehicleId()};
			obj=new Object[]{bean.getApplicationNo(),bean.getRateVehicleId()};
			removeNull(obj);
			LogManager.info("Sql=>=>"+queryFrammer(sql, obj));
			premiumInfo=this.mytemplate.queryForList(sql,obj);
			bean.setPremiumInfo(premiumInfo);
			bean.setPremiumInfoList(premiumInfo);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		LogManager.popRemove();
		LogManager.info("getVehiclePremiumInfo - Exit");
		return result;
	}
	public String getApplicationBranchCode(String applicationNo){
		LogManager.info("getApplicationBranchCode - Enter ");
		String branchCode="";
		Object[] obj=null;
		try {
			String sql="SELECT BRANCH_CODE FROM HOME_POSITION_MASTER WHERE APPLICATION_NO=?";
			obj=new Object[]{applicationNo};
			LogManager.info("getApplicationBranchCode Sql=>=>"+queryFrammer(sql, obj));
			branchCode=String.valueOf(this.mytemplate.queryForObject(sql, obj, String.class));
			LogManager.info("getApplicationBranchCode Result=>=>"+branchCode);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return branchCode;
	}
	public String getPremiumInfo(MotorBean bean) {
		LogManager.info("getPremiumInfo - Enter ");
		String result="";
		String sql="";
		Object[] obj=null;
		List<Map<String,Object>> premiumInfo=new ArrayList<Map<String,Object>>();
		try {
			sql=this.getQuery("GET_MOTOR_PREMIUM_INFO");
			//obj=new Object[]{bean.getApplicationNo(),bean.getBranchCode()};
			obj=new Object[]{bean.getApplicationNo()};
			LogManager.info("Sql=>=>"+sql);
			LogManager.info("Obj[]=>"+StringUtils.join(obj,","));
			premiumInfo=this.mytemplate.queryForList(sql,obj);
			bean.setPremiumInfo(premiumInfo);
			bean.setPremiumInfoList(premiumInfo);
			if(StringUtils.isBlank(bean.getLoadOrDiscPremium())){
				bean.setPolicyFee(getPolicyFees(bean.getApplicationNo(),getApplicationBranchCode(bean.getApplicationNo())));
			}else{
				String excessSign=bean.getSign();
				String excessAmount=bean.getLoadOrDiscPremium();
				sql=this.getQuery("GET_MOTOR_COV_PRE_SUM");
				double pre=(Double)this.mytemplate.queryForObject(sql,new Object[]{bean.getApplicationNo()},Double.class);
				double prewithloading=0.0;

				if("-".equalsIgnoreCase(excessSign))
					prewithloading=pre - Double.parseDouble(StringUtils.isBlank(excessAmount)?"0":excessAmount);
				else
					prewithloading=pre + Double.parseDouble(StringUtils.isBlank(excessAmount)?"0":excessAmount);
				
				com.maan.common.dao.CommonDAO commonDAO = new com.maan.common.dao.CommonDAO();
				bean.setPolicyFee(commonDAO.calculatePolicyFee(String.valueOf(prewithloading), getApplicationBranchCode(bean.getApplicationNo())));	
				
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		LogManager.popRemove();
		LogManager.info("getPremiumInfo - Exit");
		return result;
	}
	
	public String getPolicyFees(String applicationNo, String branchCode) throws Exception {
		com.maan.common.dao.CommonDAO commonDAO = new com.maan.common.dao.CommonDAO();
		String query = "SELECT SUM(PREMIUM) FROM MOTOR_DATA_DETAIL WHERE APPLICATION_NO=?";
		String tempPremium = (String) this.mytemplate.queryForObject(query, new Object[]{applicationNo}, String.class);
		/*if(Float.parseFloat(miniPre) > Float.parseFloat(tempPremium)){
			tempPremium = miniPre;
		}*/
		return commonDAO.calculatePolicyFee(tempPremium, branchCode); 
	}
	
	/*public Map getComparisionDetails(MotorBean bean){
		LogManager.info("getComparisionDetails - Enter");
		final Map result = new HashMap();
		List policyType = new ArrayList();
		List policyGroup = new ArrayList();
		List rates = new ArrayList();
		try{
			String sql="";
			Object []obj=null;
			sql=this.getQuery("GET_MOTOR_POL_TYPE_ID");
			obj=new Object[]{bean.getApplicationNo(),bean.getBranchCode()};
			LogManager.info("Sql=>=>"+sql);
			LogManager.info("Obj[]=>"+StringUtils.join(obj,","));
			List typeList=this.mytemplate.queryForList(sql,obj);
			String selectedType="";
			if(typeList!=null &&typeList.size()>0)
				//selectedType=((Map)typeList.get(0)).get("POLICYTYPE_ID").toString();
				selectedType=getSelectedPolicyType(bean.getApplicationNo());;
			LogManager.info("Result=>"+selectedType);
			result.put("selectedPolicyType", selectedType);
			sql=this.getQuery("GET_MOTOR_POL_TYPE");
			obj=new Object[]{bean.getApplicationNo()};
			LogManager.info("Sql=>=>"+sql);
			LogManager.info("Obj[]=>"+StringUtils.join(obj,","));
			policyType = this.mytemplate.queryForList(sql,obj);
			LogManager.info("Result=>"+policyType.size());
			for(Map<String,Object> tempMap : (List<Map<String,Object>>)policyType) {
				String query = getQuery("GET_MOTOR_POLGROUP_REF_CNT");
				Object[] args = new Object[]{bean.getApplicationNo(),tempMap.get("X_ID")};
				LogManager.info("query==>" + query);
				LogManager.info("args[]==>"+StringUtils.join(args,", "));
				int refCount = this.mytemplate.queryForInt(query, args);
				tempMap.put("REFERAL_STATUS", refCount>0?"Y":"N");
			}
			result.put("policyType", policyType);
			sql=this.getQuery("GET_MOTOR_POL_GROUP");
			obj=new Object[]{bean.getApplicationNo(),bean.getBranchCode()};
			LogManager.info("Sql=>=>"+sql);
			LogManager.info("Obj[]=>"+StringUtils.join(obj,","));
			policyGroup = this.mytemplate.queryForList(sql,obj);
			LogManager.info("Result=>"+policyGroup.size());
			result.put("policyGroup", policyGroup);
			if((policyGroup!=null && policyGroup.size()>0) && (policyType!=null && policyType.size()>0)){
				for(int group = 0; group < policyGroup.size(); group++){
					Map groupMap=(Map)policyGroup.get(group);
					String groupId=groupMap.get("GROUP_ID").toString();
					//if(!"1".equalsIgnoreCase(groupId)){
					sql=this.getQuery("GET_MOTOR_POL_COVERAGE");
					obj=new Object[]{bean.getApplicationNo(),groupId};
					}else{
						sql=this.getQuery("GET_MOTOR_POL_COVERAGE_OP");
						obj=new Object[]{bean.getApplicationNo(),groupId};
					}
					LogManager.info("Sql=>=>"+sql);
					LogManager.info("Obj[]=>"+StringUtils.join(obj,","));
					List covers = this.mytemplate.queryForList(sql,obj);
					LogManager.info("Result=>"+covers.size());
					result.put("policyCovers"+groupId, covers);
					if(!"1".equalsIgnoreCase(groupId)){
						if(covers!=null && covers.size()>0){
							for(int cover = 0; cover < covers.size(); cover++){
								Map coverMap=(Map)covers.get(cover);
								String coverId=coverMap.get("Y_DATA_NAME").toString();
								for(int type = 0; type < policyType.size(); type++){
									Map typeMap=(Map)policyType.get(type);
									String typeId=typeMap.get("X_ID").toString();	
									sql=this.getQuery("GET_MOTOR_POL_COVERAGE_DTLS");
									obj=new Object[]{bean.getApplicationNo(),groupId,typeId,coverId};
									LogManager.info("Sql=>=>"+sql);
									LogManager.info("Obj[]=>"+StringUtils.join(obj,","));
									List coverDetails = this.mytemplate.queryForList(sql,obj);
									LogManager.info("Result=>"+coverDetails.size());
									result.put("policyCoverDetails"+groupId+typeId+coverId, coverDetails);
								}
							}
						}
					}else{
						for(int type = 0; type < policyType.size(); type++){
							Map typeMap=(Map)policyType.get(type);
							String typeId=typeMap.get("X_ID").toString();	
							sql=this.getQuery("GET_MOTOR_POL_COVERAGE_ALL_DTLS");
							obj=new Object[]{bean.getApplicationNo(),groupId,typeId};
							LogManager.info("Sql=>=>"+sql);
							LogManager.info("Obj[]=>"+StringUtils.join(obj,","));
							List coverOpDetails = this.mytemplate.queryForList(sql,obj);
							LogManager.info("Result=>"+coverOpDetails.size());
							result.put("policyOpCoverDetails"+groupId+typeId, coverOpDetails);
						}
					}
				}
			}
			sql=this.getQuery("GET_MOTOR_RATE_MODYN");
			obj=new Object[]{bean.getLoginId(),bean.getProductId()};
			LogManager.info("Sql=>=>"+sql);
			LogManager.info("Obj[]=>"+StringUtils.join(obj,","));
			String rateModYN=(String)this.mytemplate.queryForObject(sql,obj, String.class);
			LogManager.info("Result=>"+rateModYN);
			result.put("rateModification", rateModYN);
		}catch(Exception e){
			e.printStackTrace();
		}
		LogManager.popRemove();
		LogManager.info("getComparisionDetails - Exit");
		return result;
	}*/
	
	public Map getComparisionDetails(MotorBean bean){
		LogManager.info("getComparisionDetails - Enter");
		final Map result = new HashMap();
		List policyType = new ArrayList();
		List policyGroup = new ArrayList();
		List rates = new ArrayList();
		try{
			String sql="";
			Object []obj=null;
			sql=this.getQuery("GET_MOTOR_POL_TYPE_ID");
			obj=new Object[]{bean.getApplicationNo(),bean.getBranchCode()};
			LogManager.info("Sql=>=>"+sql);
			LogManager.info("Obj[]=>"+StringUtils.join(obj,","));
			List typeList=this.mytemplate.queryForList(sql,obj);
			String selectedType="";
			if(typeList!=null &&typeList.size()>0)
				selectedType=((Map)typeList.get(0)).get("POLICYTYPE_ID").toString();
			LogManager.info("Result=>"+selectedType);
			result.put("selectedPolicyType", selectedType);
			sql=this.getQuery("GET_MOTOR_POL_TYPE");
			obj=new Object[]{bean.getApplicationNo()};
			LogManager.info("Sql=>=>"+sql);
			LogManager.info("Obj[]=>"+StringUtils.join(obj,","));
			policyType = this.mytemplate.queryForList(sql,obj);
			LogManager.info("Result=>"+policyType.size());
			for(Map<String,Object> tempMap : (List<Map<String,Object>>)policyType) {
				String query = getQuery("GET_MOTOR_POLGROUP_REF_CNT");
				Object[] args = new Object[]{bean.getApplicationNo(),tempMap.get("X_ID")};
				LogManager.info("query==>" + query);
				LogManager.info("args[]==>"+StringUtils.join(args,", "));
				int refCount = this.mytemplate.queryForInt(query, args);
				tempMap.put("REFERAL_STATUS", refCount>0?"Y":"N");
			}
			
			result.put("policyType", policyType);
			sql=this.getQuery("GET_MOTOR_POL_GROUP");
			obj=new Object[]{bean.getApplicationNo(),bean.getBranchCode()};
			LogManager.info("Sql=>=>"+sql);
			LogManager.info("Obj[]=>"+StringUtils.join(obj,","));
			policyGroup = this.mytemplate.queryForList(sql,obj);
			LogManager.info("Result=>"+policyGroup.size());
			result.put("policyGroup", policyGroup);
			if((policyGroup!=null && policyGroup.size()>0) && (policyType!=null && policyType.size()>0)){
				for(int group = 0; group < policyGroup.size(); group++){
					Map groupMap=(Map)policyGroup.get(group);
					String groupId=groupMap.get("GROUP_ID").toString();
					sql=this.getQuery("GET_MOTOR_POL_COVERAGE");
					obj=new Object[]{bean.getApplicationNo(),groupId};
					LogManager.info("Sql=>=>"+sql);
					LogManager.info("Obj[]=>"+StringUtils.join(obj,","));
					List covers = this.mytemplate.queryForList(sql,obj);
					LogManager.info("Result=>"+covers.size());
					result.put("policyCovers"+groupId, covers);
					if(covers!=null && covers.size()>0){
						for(int cover = 0; cover < covers.size(); cover++){
							Map coverMap=(Map)covers.get(cover);
							String coverId=coverMap.get("Y_DATA_NAME").toString();
							for(int type = 0; type < policyType.size(); type++){
								Map typeMap=(Map)policyType.get(type);
								String typeId=typeMap.get("X_ID").toString();	
								sql=this.getQuery("GET_MOTOR_POL_COVERAGE_DTLS");
								obj=new Object[]{bean.getApplicationNo(),groupId,typeId,coverId};
								LogManager.info("Sql=>=>"+sql);
								LogManager.info("Obj[]=>"+StringUtils.join(obj,","));
								List coverDetails = this.mytemplate.queryForList(sql,obj);
								LogManager.info("Result=>"+coverDetails.size());
								result.put("policyCoverDetails"+groupId+typeId+coverId, coverDetails);
								sql=this.getQuery("GET_MOTOR_RATES");
								obj=new Object[]{bean.getProductId(),bean.getLoginId(),bean.getBranchCode(),typeId};
								LogManager.info("Sql=>=>"+sql);
								LogManager.info("Obj[]=>"+StringUtils.join(obj,","));
								rates = this.mytemplate.queryForList(sql,obj);
								LogManager.info("Result=>"+rates.size());
								result.put("brokerRates"+groupId+typeId+coverId, rates);
							}
						}
					}
				}
			}
			sql=this.getQuery("GET_MOTOR_RATE_MODYN");
			obj=new Object[]{bean.getLoginId(),bean.getProductId()};
			LogManager.info("Sql=>=>"+sql);
			LogManager.info("Obj[]=>"+StringUtils.join(obj,","));
			String rateModYN=(String)this.mytemplate.queryForObject(sql,obj, String.class);
			LogManager.info("Result=>"+rateModYN);
			result.put("rateModification", rateModYN);
		}catch(Exception e){
			e.printStackTrace();
		}
		LogManager.popRemove();
		LogManager.info("getComparisionDetails - Exit");
		return result;
	}

	/*public String insertOptionCover(MotorBean bean) {
		LogManager.info("getComparisionDetails - Enter");
		String result="";
    	try{
    		Object[] obj = new Object[4];
    		String sql=this.getQuery("UPD_MOTOR_DATA_DTLS_OPT_COVPOLTYPE");
    		obj[0] = bean.getPolicyType();
            String[] opcovers=bean.getPolicyCover();
            if(opcovers.length>0)
            {
            	StringBuffer combine=new StringBuffer();
            	for(int i=0;i<opcovers.length;i++)
            	{
                     combine.append(opcovers[i]+"~");
            	}
            	obj[1]=combine.substring(0, combine.length());
            }else
            {
            	obj[1]="N";
            }
            obj[2]=bean.getApplicationNo();
            obj[3]=bean.getApplicationNo();
            LogManager.info("Sql=>"+sql);
            LogManager.info("Obj[]=>"+StringUtils.join(obj,","));
            int res=this.mytemplate.update(sql,obj);
            LogManager.info("Result=>"+res);
     		sql=this.getQuery("DEL_MOTOR_POL_COV_DATA");
     		obj = new Object[2];
            obj[0]=bean.getApplicationNo();
            obj[1]=bean.getApplicationNo();
            LogManager.info("Sql=>"+sql);
            LogManager.info("Obj[]=>"+StringUtils.join(obj,","));
            res=this.mytemplate.update(sql,obj);
            LogManager.info("Result=>"+res);
            if(opcovers.length>0 || !"75".equals(bean.getProductId()));
            {
            	int val=1;
        		final List basic_cover=getPolicyTypeCoverID2(bean,bean.getPolicyType());
        		LogManager.info("Optional Covers Length=>"+opcovers.length);
        		for(int i=0;i<opcovers.length;i++){
        			LogManager.info("Optional Covers Value=>"+opcovers[i]);
        			val=1;
    				String premium="RATE";
    				String rate="RATE";
    				sql=this.getQuery("GET_MOTOR_POL_COV_DTL_POLCOVID_CLCTYPE");
    				obj=new Object[4];
    				obj[0]=opcovers[i];
    				obj[1]=bean.getBranchCode();
    				obj[2]=opcovers[i];
    				obj[3]=bean.getBranchCode();
    				LogManager.info("Sql=>"+sql);
    	            LogManager.info("Obj[]=>"+StringUtils.join(obj,","));
    				List opCoverInfo=this.mytemplate.queryForList(sql,obj);
            		if(opCoverInfo!=null && opCoverInfo.size()>0){
            			Map opCovMap=(Map)opCoverInfo.get(0);
            			String op = opCovMap.get("POLICYCOVER_ID").toString();
            			if("2".equalsIgnoreCase(op) || "14".equalsIgnoreCase(op) || "16".equalsIgnoreCase(op)){
                			val=Integer.parseInt(bean.getSeatingCapacity())-1;
                		}
        				premium = val + "*" + premium;
                		if(opCovMap.get("CALC_TYPE")!=null && "G".equalsIgnoreCase(opCovMap.get("CALC_TYPE").toString())){
            				String[][] premiumDetails=getOpCoverPremiumWithBaseRate(op, bean.getProductId(), bean.getPolicyType(), bean.getTypeBody(), bean.getSumInsured());
        					if(premiumDetails!=null && premiumDetails.length>0){
        						val=Integer.parseInt(bean.getSumInsured());
        						premium=premiumDetails[0][1];
        						rate=premiumDetails[0][0];
        					}
        				}
            		}
            		if(!opcovers[i].equalsIgnoreCase(((Map)basic_cover.get(0)).get("POLICYTYPE_COVERID").toString())){
            			sql=LocalizedTextUtil.findDefaultText("INS_AMD_MOTOR_POL_COV_DATA", Locale.ENGLISH, new String[]{rate,premium});
    	        		obj = new Object[9];
    	        		obj[0] = bean.getApplicationNo();
    	        		obj[1] = (bean.getQuoteNo().length()>0?bean.getQuoteNo():"0");
    	        		obj[2] = opcovers[i];
    	        		obj[3] = val+"";
    	        		obj[4] = bean.getBranchCode();
    	        		obj[5] = opcovers[i];
    	        		obj[6] = bean.getBranchCode();
    	        		obj[7] = opcovers[i];
    	        		obj[8] = bean.getBranchCode();
    	        		LogManager.info("Sql=>"+sql);
    	                LogManager.info("Obj[]=>"+StringUtils.join(obj,","));
    	        		res=this.mytemplate.update(sql,obj);
    	        		LogManager.info("Result=>"+res);
            		}
            	}
            }
            bean.setPolicyCover(opcovers);
		}catch(Exception e){
			e.printStackTrace();
		}
		LogManager.info("getComparisionDetails - Exit");
		
		return result;
	}*/
	/*public boolean comprehensivePremium(MotorBean bean) {
		LogManager.info("comprehensivePremium-Enter-PremiumData");
		String[] args=new String[10];
		boolean status=false;	
		boolean driveAge = false;
		boolean uaeLicence = false;
		boolean vehicleType = false;
		String sql=this.getQuery("GET_MOTOR_AGENCY_CODE");
		Object[] obj=new Object[]{bean.getApplicationNo()};
		LogManager.info("Sql=>"+sql);
		LogManager.info("Obj[]=>"+StringUtils.join(obj,","));
		final String brokerCode = (String)this.mytemplate.queryForObject(sql, obj,String.class);
		LogManager.info("Result=>"+brokerCode);
		List rateInfo = new ArrayList();
		final int driverAge = getDriverAgeCount(bean.getTypeBody(), bean.getDriverDOB(), bean.getBranchCode(), bean.getProductId(),brokerCode);
		if(driverAge<=0){
			driveAge = true;
		}
		final int uaeLicense = getUaeLicenceCount(bean.getTypeBody(), bean.getBranchCode(), bean.getProductId(),brokerCode);		
		if(uaeLicense<=0){
			uaeLicence = true;
		}
		final int vehicleTypes = getVehicleTypeCount(bean,brokerCode);
		if(vehicleTypes<=0){
			vehicleType = true;
		}		
		
		final String typeInfo = getTypeInfo(bean.getTypeBody(), bean.getModel(), bean.getVehicleUsage(), bean.getDriverDOB(), bean.getBranchCode(), bean.getProductId(),driveAge,uaeLicence,vehicleType,brokerCode);
		if(typeInfo!=null && typeInfo.length()>0){
			sql=this.getQuery("GET_MOTOR_YEAR_CNT");
			obj=new Object[]{bean.getMfgYr()};
			LogManager.info("Sql=>"+sql);
			LogManager.info("Obj[]=>"+bean.getMfgYr());
			final String vehicleyear=this.mytemplate.queryForInt(sql,obj)+"";
			LogManager.info("Result=>"+vehicleyear);
			sql=this.getQuery("GET_MOTOR_COMPREHENSIVE_RATE");
			obj=new Object[20];
			obj[0]=bean.getLoginId();
			obj[1]=bean.getProductId();
			obj[2]=bean.getLoginId();
			obj[3]=bean.getProductId();
			obj[4]=bean.getLoginId();
			obj[5]=bean.getProductId();
			obj[6]=bean.getLoginId();
			obj[7]=bean.getProductId();
			obj[8]="Y".equalsIgnoreCase(bean.getAgencyRepairYN())?vehicleyear:"99999";
			obj[8]="0";
			obj[9]=bean.getSeatingCapacity();
			obj[10]=bean.getSumInsured();
			obj[11]=typeInfo;
			obj[12]=bean.getBranchCode();
			obj[13]=bean.getProductId();
			obj[14]=bean.getPolicyType();
			obj[15]="Y".equalsIgnoreCase(bean.getAgencyRepairYN())?vehicleyear:"99999";
			obj[15]="0";
			obj[16]=typeInfo;
			obj[17]=bean.getBranchCode();
			obj[18]=bean.getProductId();
			obj[19]=bean.getPolicyType();
			LogManager.info("Sql=>"+sql);
			LogManager.info("Obj[]=>"+StringUtils.join(obj,","));
			rateInfo = this.mytemplate.queryForList(sql,obj);
			
			//select rate for motor insurance purpose
			//if(bean.getPolicyCoverId()!=null){
				//sql = "select rate from motor_policy_coverage where y_id=? and application_no=?";
				//Map rateSam = this.mytemplate.queryForMap(sql,new Object[]{bean.getPolicyCoverId(),bean.getApplicationNo()});
				bean.setRate(((Map)rateInfo.get(0)).get("RATE")==null?"":((Map)rateInfo.get(0)).get("RATE").toString());
			//}
			double rate=0.0,minpremium=0.0,suminsured=0.0,actualpremium=0.0;
			String sts="Y";
			if(rateInfo.size()>0){
				rate=Double.parseDouble(bean.getRate());
				minpremium=Double.parseDouble(((Map)rateInfo.get(0)).get("PREMIUM").toString());
				suminsured=Double.parseDouble(bean.getSumInsured());
				actualpremium=(rate*suminsured);
				if(actualpremium<minpremium){
					actualpremium=minpremium;
				}
			}
			final String ID[][]=getPolicyTypeCoverID(bean);
			if(ID.length>0){
				sql=this.getQuery("INS_MOTOR_POL_COV_DATA");
				args = new String[9];
				args[0] = bean.getApplicationNo();
				args[1] = (bean.getQuoteNo().length()>0?bean.getQuoteNo():"0");
				args[2] = ID[0][0];
				args[3] = bean.getSumInsured();
				args[4] = ""+rate;
				args[5] = ""+actualpremium;
				args[6] =sts;
				args[7] ="0";
				args[8] =bean.getBranchCode();
				runner.multipleInsertion(sql,args);
				updatePolicyTerm(bean.getApplicationNo(), bean.getPolicyType(), bean.getBranchCode(), bean.getProductId());
				runner.updation("update motor_policy_coverage set rate='"+rate+"',data_value='"+actualpremium+"' where application_no='"+bean.getApplicationNo()+"' and y_id='"+ID[0][0]+"'");
			}
			if(actualpremium>0){ 
				status=true; 
				System.out.println("Inside Motor premium status==="+status);
			}		
		}else{
			final String ID[][]=getPolicyTypeCoverID(bean);
			if(ID.length>0){
				sql=this.getQuery("INS_MOTOR_POL_COV_DATA");
				args = new String[9];
				args[0] = bean.getApplicationNo();
				args[1] = (bean.getQuoteNo().length()>0?bean.getQuoteNo():"0");
				args[2] = ID[0][0];
				args[3] = bean.getSumInsured();
				args[4] = "0";
				args[5] = "0";
				args[6] ="Y";
				args[7] ="0";
				args[8] =bean.getBranchCode();
				runner.multipleInsertion(sql,args);
				updatePolicyTerm(bean.getApplicationNo(), bean.getPolicyType(), bean.getBranchCode(), bean.getProductId());
			}
			status=true;
		}
		LogManager.popRemove();
		return status;
	}*/
	/*public String[][] getPolicyTypeCoverID(final MotorBean bean) {
		LogManager.info("getPolicyTypeCoverID-Enter");
		final String[] args=new String[6];
		final String query ="select POLICYTYPE_COVERID from MOTOR_POLICY_COVER_DETAIL where policytype_id=? and " +
				"POLICYCOVER_ID=0 and branch_code=? and TYPE_OF_BODY=? and amend_id in(select max(amend_id) from " +
				"MOTOR_POLICY_COVER_DETAIL where policytype_id=? and POLICYCOVER_ID=0 and branch_code=? AND TYPE_OF_BODY=?)";
		args[0]=bean.getPolicyType()==null?bean.getPolicyType():bean.getPolicyType();
		args[1]=bean.getBranchCode();
		args[2]=bean.getTypeBody();
		args[3]=bean.getPolicyType()==null?bean.getPolicyType():bean.getPolicyType();
		args[4]=bean.getBranchCode();
		args[5]=bean.getTypeBody();
		final String id[][] = runner.multipleSelection(query,args);	
		LogManager.popRemove();
		return id;
	}*/
	public String updatePolicyTerm(final String applicationNo, final String policyType, final String branchCode, final String productId){
		String result = "";
		String policyTerm = "0";
		try{
			String[] cols = new String[2];
			cols[0] = branchCode;
			cols[1] = policyType;
			//String query = "SELECT (CASE WHEN LENGTH(TRIM(TRANSLATE(POLICY_TERM, ' 0123456789',' ')))=NULL THEN POLICY_TERM ELSE 12 END) FROM MOTOR_POLICYTYPE_MASTER MP WHERE BRANCH_CODE=? AND POLICYTYPE_ID=? AND AMEND_ID=(SELECT MAX(AMEND_ID) FROM MOTOR_POLICYTYPE_MASTER WHERE POLICYTYPE_ID=MP.POLICYTYPE_ID AND BRANCH_CODE=MP.BRANCH_CODE)";
			String query = "SELECT POLICY_TERM FROM MOTOR_POLICYTYPE_MASTER MP WHERE BRANCH_CODE=? AND POLICYTYPE_ID=? AND AMEND_ID=(SELECT MAX(AMEND_ID) FROM MOTOR_POLICYTYPE_MASTER WHERE POLICYTYPE_ID=MP.POLICYTYPE_ID AND BRANCH_CODE=MP.BRANCH_CODE)";
			policyTerm = runner.singleSelection(query,cols);
			query = "UPDATE HOME_POSITION_MASTER SET POLICY_TERM=?,EXPIRY_DATE=TO_DATE(TO_CHAR(add_months(INCEPTION_DATE,?)-1,'DD-MM-YYYY')||' 23:59:59','DD-MM-YYYY HH24:MI:SS') WHERE PRODUCT_ID=? AND APPLICATION_NO=?";
			cols = new String[4];
			cols[0] = policyTerm;
			cols[1] = policyTerm;
			cols[2] = productId;
			cols[3] = applicationNo;
			runner.multipleUpdation(query, cols);
			query = "UPDATE MOTOR_DATA_DETAIL SET EXPIRY_DATE=TO_DATE(TO_CHAR(add_months(INCEPTION_DATE,?)-1,'DD-MM-YYYY')||' 23:59:59','DD-MM-YYYY HH24:MI:SS') WHERE PRODUCT_ID=? AND APPLICATION_NO=?";
			cols = new String[3];
			cols[0] = policyTerm;
			cols[1] = productId;
			cols[2] = applicationNo;
			runner.multipleUpdation(query, cols);
			result = "Inserted";
		}catch(Exception e){
			LogManager.fatal(e);
			result="Not Inserted";
		}
		return result;
	}
	/*public boolean thirdPartyPremium(final MotorBean bean) {
		LogManager.info("thirdPartyPremium-Enter");
		boolean driveAge = false;
		boolean uaeLicence = false;
		boolean status = false;
		boolean vehicleType = false;
		String sql=this.getQuery("GET_MOTOR_AGENCY_CODE");
		Object[] obj=new Object[]{bean.getApplicationNo()};
		LogManager.info("Sql=>"+sql);
		LogManager.info("Obj[]=>"+StringUtils.join(obj,","));
		final String brokerCode = (String)this.mytemplate.queryForObject(sql, obj,String.class);
		LogManager.info("Result=>"+brokerCode);
		String[] args=new String[8];	
		List rate=new ArrayList();
		final int driverAge = getDriverAgeCount(bean.getTypeBody(), bean.getDriverDOB(), bean.getBranchCode(), bean.getProductId(), brokerCode);
		if(driverAge<=0){
			driveAge = true;
		}
		final int uaeLicense = getUaeLicenceCount(bean.getTypeBody(), bean.getBranchCode(), bean.getProductId(), brokerCode);		
		if(uaeLicense<=0){
			uaeLicence = true;
		}
		final int vehicleTypes = getVehicleTypeCount(bean, brokerCode);
		if(vehicleTypes<=0){
			vehicleType = true;
		}		
		
		final String typeInfo = getTypeInfo(bean.getTypeBody(), bean.getModel(), bean.getVehicleUsage(), bean.getDriverDOB(), bean.getBranchCode(), bean.getProductId(),driveAge,uaeLicence,vehicleType,brokerCode);
		if(typeInfo!=null && typeInfo.length()>0){
			LogManager.info("Third Party Premium || Enter");
			sql=this.getQuery("GET_MOTOR_THIRD_PARTY_RATE");
			obj = new Object[8];
			obj[0]=typeInfo;
			obj[1]=bean.getNoOfCylinder();
			obj[2]=bean.getBranchCode();
			obj[3]=bean.getProductId();
			obj[4]=typeInfo;
			obj[5]=bean.getNoOfCylinder();
			obj[6]=bean.getBranchCode();
			obj[7]=bean.getProductId();
			LogManager.info("Obj[]=>"+StringUtils.join(obj,","));
			rate = this.mytemplate.queryForList(sql,obj);
			if(rate.size()>0){
				String ID[][]=getPolicyTypeCoverID(bean);
				if(ID.length>0){
					sql=this.getQuery("INS_MOTOR_POL_COV_DATA");
					args = new String[9];
					args[0] = bean.getApplicationNo();
					args[1] = (bean.getQuoteNo().length()>0?bean.getQuoteNo():"0");
					args[2] = ID[0][0];
					args[3] = "1";
					args[4] = bean.getRate();
					args[5] = bean.getRate();
					args[6] ="Y";
					args[7] ="0";
					args[8] =bean.getBranchCode();
					runner.multipleInsertion(sql,args);
					updatePolicyTerm(bean.getApplicationNo(), bean.getPolicyType(), bean.getBranchCode(), bean.getProductId());
					runner.updation("update motor_policy_coverage set rate='"+bean.getRate()+"',data_value='"+bean.getRate()+"' where application_no='"+bean.getApplicationNo()+"' and y_id='"+ID[0][0]+"'");
				}
				if(!"0".equalsIgnoreCase(((Map)rate.get(0)).get("RATE").toString())){
					status=true;		
				}
			}
		}else{
			final String ID[][]=getPolicyTypeCoverID(bean);
			if(ID.length>0){
				sql=this.getQuery("INS_MOTOR_POL_COV_DATA");
				args = new String[9];
				args[0] = bean.getApplicationNo();
				args[1] = (bean.getQuoteNo().length()>0?bean.getQuoteNo():"0");
				args[2] = ID[0][0];
				args[3] = "1";
				args[4] = "0";
				args[5] = "0";
				args[6] = "Y";
				args[7] = "0";
				args[8] =bean.getBranchCode();
				runner.multipleInsertion(sql,args);
				updatePolicyTerm(bean.getApplicationNo(), bean.getPolicyType(), bean.getBranchCode(), bean.getProductId());
			}
			status=true;		
		}
		LogManager.popRemove();
		return status;
	}*/

	public void getFirstPageDtls(MotorBean bean) {
		LogManager.info("getFirstPageDtls - Enter");
		try{
			String sql=getQuery("GET_MOTOR_DATA_DTLS");
			Object obj[] = new Object[]{bean.getApplicationNo(),bean.getProductId()};
			LogManager.info("Query=>"+sql);
			LogManager.info("Obj[]=>"+StringUtils.join(obj,","));
			Map<String,Object> map=this.mytemplate.queryForMap(sql,obj);
			bean.setQuoteNo(map.get("QUOTE_NO")==null?"":map.get("QUOTE_NO").toString());
			bean.setCustomerId(map.get("CUSTOMER_ID")==null?"":map.get("CUSTOMER_ID").toString());
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
					Object[] args = new Object[]{bean.getBrokerCode()};
					String executive = (String) this.mytemplate.queryForObject(query, args, String.class);
					bean.setExecutive(executive);
				}
				catch(Exception ex) {
					ex.printStackTrace();
				}
			}
			if(StringUtils.isNotBlank(bean.getApplicationNo())) {
				map = getMotorDataDetails(bean.getApplicationNo());
				bean.setPolicyStartDate(map.get("POLICYSTARTDATE")==null?"":map.get("POLICYSTARTDATE").toString());
				bean.setPolicyEndDate(map.get("POLICYENDDATE")==null?"":map.get("POLICYENDDATE").toString());
				bean.setPremiumType(map.get("POLICYTYPE")==null?"":map.get("POLICYTYPE").toString());
				bean.setCurrencyType(map.get("CURRENCY_TYPE")==null?"":map.get("CURRENCY_TYPE").toString());
				bean.setPolicyType(map.get("ISSELECTCOVER")==null?"":map.get("ISSELECTCOVER").toString());
			}
			if(StringUtils.isNotBlank(bean.getApplicationNo()) || StringUtils.isNotBlank(bean.getCustomerId())) {
				/*sql=getQuery("GET_MOTOR_CUST_INFO");
				obj = new Object[]{bean.getCustomerId()};
				LogManager.info("Query=>"+sql);
				LogManager.info("Obj[]=>"+StringUtils.join(obj,","));
				map=this.mytemplate.queryForMap(sql,obj);
				bean.setTitle(map.get("TITLE")==null?"":map.get("TITLE").toString());
				bean.setCustomerName(map.get("NAME")==null?"":map.get("NAME").toString());
				bean.setCustNameArabic(map.get("CUST_NAME_ARABIC")==null?"":map.get("CUST_NAME_ARABIC").toString());
				bean.setEmail(map.get("EMAIL")==null?"":map.get("EMAIL").toString());
				bean.setMobileNo(map.get("MOBILE")==null?"":map.get("MOBILE").toString());
				bean.setAddress1(map.get("ADDRESS1")==null?"":map.get("ADDRESS1").toString());
				bean.setAddress2(map.get("ADDRESS2")==null?"":map.get("ADDRESS2").toString());
				bean.setPoBox(map.get("POBOX")==null?"":map.get("POBOX").toString());
				bean.setCity(map.get("EMIRATE")==null?"":map.get("EMIRATE").toString());
				bean.setCustCoreCode(map.get("MISSIPPI_CUSTOMER_CODE")==null?"":map.get("MISSIPPI_CUSTOMER_CODE").toString());
				bean.setCoreAppCode(map.get("MISSIPPI_CUSTOMER_CODE")==null?"":map.get("MISSIPPI_CUSTOMER_CODE").toString());
				bean.setClientCustomerId(map.get("CLIENT_CUSTOMER_ID")==null?"":map.get("CLIENT_CUSTOMER_ID").toString());
				bean.setCustArNo(map.get("CUST_AR_NO")==null?"":map.get("CUST_AR_NO").toString());
				bean.setCustomerType(map.get("CUSTOMER_TYPE")==null?"":map.get("CUSTOMER_TYPE").toString());
				bean.setCompanyRegNo(map.get("COMPANY_REG_NO")==null?"":map.get("COMPANY_REG_NO").toString());*/
				new CustomerDAO().setCustomerDetails(bean);
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		LogManager.info("getFirstPageDtls - Exit");
	}
	
	public void getFirstPageDtlsNew(MotorBean bean) {
		LogManager.info("getFirstPageDtlsNew - Enter");
		try{
			bean.setCustomerId("");
			String sql="SELECT CUSTOMER_ID FROM MOTOR_DATA_DETAIL WHERE APPLICATION_NO=?";
			Object obj[] = new Object[]{bean.getApplicationNo()};
			LogManager.info("Query=>"+queryFrammer(sql, obj));
			List<Map<String,Object>> map=this.mytemplate.queryForList(sql,obj);
			if(map!=null && map.size()>0){
				bean.setCustomerId(map.get(0).get("CUSTOMER_ID")==null?"":map.get(0).get("CUSTOMER_ID").toString());
			}else{
				sql="SELECT CUSTOMER_ID FROM HOME_POSITION_MASTER WHERE APPLICATION_NO=?";
				Object args[] = new Object[]{bean.getApplicationNo()};
				LogManager.info("Query=>"+queryFrammer(sql, args));
				List<Map<String,Object>> list=this.mytemplate.queryForList(sql,args);
				if(list!=null && list.size()>0){
					bean.setCustomerId(list.get(0).get("CUSTOMER_ID")==null?"":list.get(0).get("CUSTOMER_ID").toString());
				}
				
			}
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
					Object[] args = new Object[]{bean.getBrokerCode()};
					String executive = (String) this.mytemplate.queryForObject(query, args, String.class);
					bean.setExecutive(executive);
				}
				catch(Exception ex) {
					ex.printStackTrace();
				}
			}
			
			if(StringUtils.isNotBlank(bean.getApplicationNo()) || StringUtils.isNotBlank(bean.getCustomerId())) {
				setCustomerDetailsB2B(bean);
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		LogManager.info("getFirstPageDtls - Exit");
	}
	
	public void setCustomerDetailsB2B(MotorBean bean) {
		LogManager.info("Enter into setCustomerDetailsB2B");
		try{
			Map<String,Object> res = getCustomerInfoQuote(bean.getCustomerId());
			if(res!=null && res.size() > 0){
				bean.setCustomerId(res.get("CUSTOMER_ID")==null?"":res.get("CUSTOMER_ID").toString());
				if(!"getVehicle".equalsIgnoreCase(bean.getActionType())){
					bean.setTitle(res.get("TITLE")==null?"":res.get("TITLE").toString());
					bean.setCustomerName(res.get("NAME")==null?"":res.get("NAME").toString());
					bean.setMobileNo(res.get("MOBILE")==null?"":res.get("MOBILE").toString());
					bean.setEmail(res.get("EMAIL")==null?"":res.get("EMAIL").toString());
					bean.setCustLastName(res.get("LAST_NAME")==null?"":res.get("LAST_NAME").toString());
					bean.setCustNameArabic(res.get("CUST_NAME_ARABIC")==null?"":res.get("CUST_NAME_ARABIC").toString());
					bean.setCustomerType(res.get("CUSTOMER_TYPE")==null?"":res.get("CUSTOMER_TYPE").toString());
				}
				bean.setAddress1(res.get("ADDRESS1")==null?"":res.get("ADDRESS1").toString());
				bean.setAddress2(res.get("ADDRESS2")==null?"":res.get("ADDRESS2").toString());
				bean.setPoBox(res.get("POBOX")==null?"":res.get("POBOX").toString());
				bean.setCity(res.get("EMIRATE")==null?"":res.get("EMIRATE").toString());
				bean.setCity(StringUtils.isBlank(bean.getCity())?ResourceBundleUtil.findDefaultText("MOTOR_DEFAULT_CITY"):bean.getCity());
				bean.setCustCoreCode(res.get("MISSIPPI_CUSTOMER_CODE")==null?"":res.get("MISSIPPI_CUSTOMER_CODE").toString());
				bean.setCoreAppCode(res.get("MISSIPPI_CUSTOMER_CODE")==null?"":res.get("MISSIPPI_CUSTOMER_CODE").toString());
				bean.setClientCustomerId(res.get("CLIENT_CUSTOMER_ID")==null?"":res.get("CLIENT_CUSTOMER_ID").toString());
				bean.setCustArNo(res.get("CUST_AR_NO")==null?"":res.get("CUST_AR_NO").toString());
				
				/*bean.setCustnrc(res.get("NRC")==null?"":res.get("NRC").toString());
				if(StringUtils.isNotBlank(bean.getCustnrc())) {
					String[] nrc = bean.getCustnrc().split("/");
					if(nrc.length > 2){
						bean.setCustnrc1(nrc[0]);
						bean.setCustnrc2(nrc[1]);
						bean.setCustnrc3(nrc[2]);
					}
				}*/
				String nrc = res.get("NRC")==null?"":res.get("NRC").toString();
				if(StringUtils.isNotBlank(nrc)) {
					String[] nrcarr = nrc.split("/");
					if(nrcarr.length > 2){
						bean.setCustnrc1(nrcarr[0]);
						bean.setCustnrc2(nrcarr[1]);
						bean.setCustnrc3(nrcarr[2]);
					}
				}
				bean.setCustPassportNo(res.get("PASSPORT_NUMBER")==null?"":res.get("PASSPORT_NUMBER").toString());
				/*if(bean.getCustdob()== null && bean.getCustdob() == ""){
				bean.setCustdob(res.get("CUST_DOB")==null?"":res.get("CUST_DOB").toString());
				}*/
				bean.setCustdob(res.get("CUST_DOB")==null?"":res.get("CUST_DOB").toString());
				bean.setCustAlterMobileNo(res.get("ALTERNATE_MOBILE")==null?"":res.get("ALTERNATE_MOBILE").toString());
				bean.setCustLandLineNo(res.get("TELEPHONE")==null?"":res.get("TELEPHONE").toString());
				bean.setCompanyRegNo(res.get("COMPANY_REG_NO")==null?"":res.get("COMPANY_REG_NO").toString());
				bean.setGender(res.get("GENDER")==null?"":res.get("GENDER").toString());
				bean.setOccupation(res.get("OCCUPATION")==null?"":res.get("OCCUPATION").toString());
				LogManager.info("Exit from setCustomerDetailsB2B");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	private Map<String,Object> getCustomerInfoQuote(String customerId){
		Map<String, Object> result = null;
		try {
			String query = "SELECT PI.CUSTOMER_ID, PI.TITLE, NVL(PI.FIRST_NAME, PI.COMPANY_NAME) NAME, PI.MOBILE, PI.EMAIL, "
					+ "PI.ADDRESS1, PI.ADDRESS2, PI.POBOX, PI.EMIRATE, PI.MISSIPPI_CUSTOMER_CODE, PI.CLIENT_CUSTOMER_ID, "
					+ "PI.CUST_AR_NO, PI.LAST_NAME, PI.NRC, PI.PASSPORT_NUMBER, TO_CHAR (PI.DOB, 'DD/MM/YYYY') CUST_DOB, "
					+ "PI.DOB_AR CUST_DOB_AR, PI.ALTERNATE_MOBILE, PI.TELEPHONE, PI.CUSTOMER_TYPE, PI.COMPANY_REG_NO, "
					+ "PI.CUST_NAME_ARABIC, PI.GENDER, PI.OCCUPATION FROM PERSONAL_INFO PI "
					+ "WHERE PI.CUSTOMER_ID=?";
			Object args[]=new Object[]{customerId};
			LogManager.info("query =>" +queryFrammer(query, args));
			result = this.mytemplate.queryForMap(query, args);
		}catch (Exception e) {
			//e.printStackTrace();
		}
		return result;
	}
	/*public void getMotorCustomerDetails(MotorBean bean,String loginId){
		try{
			String sql=getQuery("GET_USER_MOTOR_CUSTOMER_DETAILS");
			Object obj[] = new Object[]{loginId};
			LogManager.info("Query=>"+sql);
			LogManager.info("Obj[]=>"+StringUtils.join(obj,","));
			List<Map<String,Object>> map=this.mytemplate.queryForList(sql,obj);
			if(map.size()>0){
				for(int i=0;i<map.size();i++){
					bean.setTitle(map.get(i).get("TITLE")==null?"":map.get(i).get("TITLE").toString());
					bean.setCustomerName(map.get(i).get("NAME")==null?"":map.get(i).get("NAME").toString());
					bean.setEmail(map.get(i).get("EMAIL")==null?"":map.get(i).get("EMAIL").toString());
					bean.setMobileNo(map.get(i).get("MOBILE")==null?"":map.get(i).get("MOBILE").toString());
					bean.setAddress1(map.get(i).get("ADDRESS1")==null?"":map.get(i).get("ADDRESS1").toString());
					bean.setAddress2(map.get(i).get("ADDRESS2")==null?"":map.get(i).get("ADDRESS2").toString());
					bean.setPoBox(map.get(i).get("POBOX")==null?"":map.get(i).get("POBOX").toString());
					bean.setCity(map.get(i).get("EMIRATE")==null?"":map.get(i).get("EMIRATE").toString());
					bean.setCustCoreCode(map.get(i).get("MISSIPPI_CUSTOMER_CODE")==null?"":map.get(i).get("MISSIPPI_CUSTOMER_CODE").toString());
					bean.setCoreAppCode(map.get(i).get("MISSIPPI_CUSTOMER_CODE")==null?"":map.get(i).get("MISSIPPI_CUSTOMER_CODE").toString());
					bean.setClientCustomerId(map.get(i).get("CLIENT_CUSTOMER_ID")==null?"":map.get(i).get("CLIENT_CUSTOMER_ID").toString());
					bean.setCustArNo(map.get(i).get("CUST_AR_NO")==null?"":map.get(i).get("CUST_AR_NO").toString());
					bean.setCustomerType(map.get(i).get("CUSTOMER_TYPE")==null?"":map.get(i).get("CUSTOMER_TYPE").toString());
					bean.setCompanyRegNo(map.get(i).get("COMPANY_REG_NO")==null?"":map.get(i).get("COMPANY_REG_NO").toString());
					bean.setDriverDOBList(Arrays.asList(new String[]{map.get(i).get("DOB")==null?"":map.get(i).get("DOB").toString()}));
					bean.setOwnerDriverYNList(Arrays.asList(new String[]{"Y"}));
				}
			}
			
		}catch (Exception e) {
			LogManager.info("Exception Occured @ getMotorCustomerDetails "+e);
			e.printStackTrace();LogManager.info(e);
		}
	}*/
	
	private Map<String,Object> getMotorDataDetails(String applicationNo) {
		Map<String,Object> resultMap = new HashMap<String, Object>();
		try {
			String query = getQuery("GET_MOTOR_POLICY_DETAILS");
			Object[] args = new Object[]{applicationNo};
			removeNull(args);
			LogManager.info("query==>"+query);
			LogManager.info("args[]==>"+StringUtils.join(args,", "));
			resultMap = this.mytemplate.queryForMap(query, args);
		} catch(Exception exception) {
			resultMap = new HashMap<String, Object>();
		}
		return resultMap;
	}

	public String getGeratePolicy(MotorBean bean) {
		LogManager.info("getGeratePolicy - Enter");
		String sql="";
		Object[] obj=new Object[0];
		int res=0;
		String result="SUCCESS";
		try
		{
		try{
				sql=this.getQuery("GET_MOTOR_COV_PRE_SUM");
				LogManager.info("Query=>>"+sql);
				LogManager.info("Obj[]=>>"+bean.getApplicationNo());
				Double pre=(Double)this.mytemplate.queryForObject(sql,new Object[]{bean.getApplicationNo()},Double.class); 
				LogManager.info("Result=>>"+pre);
				 if(StringUtils.isNotBlank(bean.getEndTypeId())){
						bean.setPolicyFee("0.00");
				}
				String policyfee = StringUtils.isBlank(bean.getEndTypeId())?StringUtils.isBlank(bean.getPolicyFee())?"0.0":bean.getPolicyFee():"0";
				Double totPre=pre+Double.parseDouble(policyfee);
				final java.text.NumberFormat fmt1 = new java.text.DecimalFormat("0.00");
				bean.setTotalPremium(fmt1.format(totPre));
				
			}catch (Exception e) {
				e.printStackTrace();
			}
			
			if("Y".equalsIgnoreCase(bean.getReferralYN())){
				sql=getQuery("UPD_MOTOR_REF_STATUS");
				obj=new Object[9];
				obj[0]=(!"getSave".equalsIgnoreCase(bean.getActionType())?"Referal":"");
				obj[1]=bean.getReferralComments();
				obj[2]=(!"getSave".equalsIgnoreCase(bean.getActionType())?bean.getReferralYN():"");
				obj[3]=(!"getSave".equalsIgnoreCase(bean.getActionType())?"Admin Referral":"");
				obj[4]=bean.getPremium();
				obj[5]=bean.getPolicyFee();
				obj[6]=bean.getTotalPremium();
				obj[7]=new com.maan.common.dao.CommonDAO().getCommision(Double.parseDouble(bean.getPremium()),bean.getApplicationNo());//commission;
				obj[8]=bean.getQuoteNo();
				LogManager.info("Query=>"+sql);
				LogManager.info("obj[] ==> "+StringUtils.join(obj, ","));
				res=this.mytemplate.update(sql,obj);
				LogManager.info("Result=>"+res);
			} else {
				sql=getQuery("UPD_MOTOR_HOME_POS_MASTER_MODEOFPAY");
				obj=new Object[12];
				obj[0]="";
				obj[1]="";
				obj[2]="";
				//obj[3]=bean.getModeOfPay();
				obj[3]=bean.getModeOfPayment()==null?"":bean.getModeOfPayment();
				obj[4]="";
				obj[5]=bean.getPremium();
				obj[6]=bean.getPolicyFee();
				obj[7]=bean.getTotalPremium();
				obj[8]=new com.maan.common.dao.CommonDAO().getCommision(Double.parseDouble(bean.getPremium()),bean.getApplicationNo());//commission;
				if("getSave".equalsIgnoreCase(bean.getActionType())){
					obj[9]="S";
				} else if(StringUtils.isNotBlank(bean.getEndTypeId())) {
					obj[9]="E";
				} else {
					obj[9]="Y";
				}
				obj[10]=bean.getChqInvNo();
				obj[11]=bean.getQuoteNo();
				removeNull(obj);
				LogManager.info("Query=>"+sql);
				LogManager.info("obj[] ==> "+StringUtils.join(obj, ","));
				res=this.mytemplate.update(sql,obj);
				LogManager.info("Result=>"+res);
			}
		}catch(Exception e)
		{
			e.printStackTrace();
			result="FAILED";
		}
		LogManager.info("getGeratePolicy - Exit");
		return result;
	}
	
	public String getGeratePolicyNew(MotorBean bean) {
		LogManager.info("getGeratePolicyNew - Enter");
		String sql="";
		Object[] obj=new Object[0];
		int res=0;
		String result="SUCCESS";
		double prewithloading=0.0;
		Double totPre;
		String policyFee= "";
		String excessAmount="";
		try
		{
		try{
				sql=this.getQuery("GET_MOTOR_COV_PRE_SUM");
				LogManager.info("Query=>>"+sql);
				LogManager.info("Obj[]=>>"+bean.getApplicationNo());
				Double pre=(Double)this.mytemplate.queryForObject(sql,new Object[]{bean.getApplicationNo()},Double.class); 
				try {
					bean.setPremium(String.valueOf(pre));
				} catch (Exception e) {
					
					e.printStackTrace();
				}
				LogManager.info("Result=>>"+pre);
				 if(StringUtils.isNotBlank(bean.getEndTypeId())){
						bean.setPolicyFee("0.00");
				}
				 String query="SELECT PREMIUM,POLICY_FEE,OVERALL_PREMIUM,EXCESS_SIGN,EXCESS_PREMIUM FROM HOME_POSITION_MASTER WHERE APPLICATION_NO=?";
				 List<Map<String,Object>> list=this.mytemplate.queryForList(query,new Object[]{bean.getApplicationNo()});
				 
				 if(list!=null && list.size()>0){
					 if(StringUtils.isBlank(bean.getPremium())){
						 pre=(Double)(list.get(0).get("PREMIUM")==null?"":list.get(0).get("PREMIUM"));
						 bean.setPremium(String.valueOf(pre));
					 }
					 //bean.setSign(list.get(0).get("EXCESS_SIGN")==null?"":list.get(0).get("EXCESS_SIGN").toString());
					 String excessSign=list.get(0).get("EXCESS_SIGN")==null?"":list.get(0).get("EXCESS_SIGN").toString();
					 bean.setSign(excessSign);
					 excessAmount=list.get(0).get("EXCESS_PREMIUM")==null?"":list.get(0).get("EXCESS_PREMIUM").toString();
					 bean.setLoadOrDiscPremium(excessAmount);
					 if("-".equalsIgnoreCase(excessSign))
						prewithloading=pre - Double.parseDouble(StringUtils.isBlank(excessAmount)?"0":excessAmount);
					else
						prewithloading=pre + Double.parseDouble(StringUtils.isBlank(excessAmount)?"0":excessAmount);
						
						com.maan.common.dao.CommonDAO commonDAO = new com.maan.common.dao.CommonDAO();
						policyFee=commonDAO.calculatePolicyFee(String.valueOf(prewithloading), getApplicationBranchCode(bean.getApplicationNo()));
					 //bean.setPolicyFee(list.get(0).get("POLICY_FEE")==null?"":list.get(0).get("POLICY_FEE").toString());
					 //bean.setTotalPremium(list.get(0).get("OVERALL_PREMIUM")==null?"":list.get(0).get("OVERALL_PREMIUM").toString());
				 }else{
						policyFee=getPolicyFees(bean.getApplicationNo(),getApplicationBranchCode(bean.getApplicationNo()));
					}
				 	
				bean.setPolicyFee(policyFee);
				String loadOrDiscPremium=StringUtils.isBlank(bean.getLoadOrDiscPremium())?excessAmount:bean.getLoadOrDiscPremium();
				//String policyfee = StringUtils.isBlank(bean.getEndTypeId())?StringUtils.isBlank(bean.getPolicyFee())?"0.0":bean.getPolicyFee():"0";
				if("-".equalsIgnoreCase(bean.getSign()))
					totPre=pre+Double.parseDouble(policyFee)-Double.parseDouble(loadOrDiscPremium);
				else
					totPre=pre+Double.parseDouble(policyFee)+Double.parseDouble(loadOrDiscPremium);
				
				//totPre=pre+Double.parseDouble(policyfee);
				
				final java.text.NumberFormat fmt1 = new java.text.DecimalFormat("0.00");
				bean.setTotalPremium(fmt1.format(totPre));
				
			}catch (Exception e) {
				e.printStackTrace();
			}
			
			if("Y".equalsIgnoreCase(bean.getReferralYN())){
				sql=getQuery("UPD_MOTOR_REF_STATUS");
				obj=new Object[9];
				obj[0]=(!"getSave".equalsIgnoreCase(bean.getActionType())?"Referal":"");
				obj[1]=bean.getReferralComments();
				obj[2]=(!"getSave".equalsIgnoreCase(bean.getActionType())?bean.getReferralYN():"");
				obj[3]=(!"getSave".equalsIgnoreCase(bean.getActionType())?"Admin Referral":"");
				obj[4]=bean.getPremium();
				obj[5]=bean.getPolicyFee();
				obj[6]=bean.getTotalPremium();
				obj[7]=new com.maan.common.dao.CommonDAO().getCommision(Double.parseDouble(bean.getPremium()),bean.getApplicationNo());//commission;
				obj[8]=bean.getQuoteNo();
				LogManager.info("Query=>"+sql);
				LogManager.info("obj[] ==> "+StringUtils.join(obj, ","));
				res=this.mytemplate.update(sql,obj);
				LogManager.info("Result=>"+res);
			} else {
				sql=getQuery("UPD_MOTOR_HOME_POS_MASTER_MODEOFPAY");
				obj=new Object[12];
				obj[0]="";
				obj[1]="";
				obj[2]="";
				//obj[3]=bean.getModeOfPay();
				obj[3]=bean.getModeOfPayment()==null?"":bean.getModeOfPayment();
				obj[4]="";
				obj[5]=bean.getPremium();
				obj[6]=bean.getPolicyFee();
				obj[7]=bean.getTotalPremium();
				obj[8]=new com.maan.common.dao.CommonDAO().getCommision(Double.parseDouble(bean.getPremium()),bean.getApplicationNo());//commission;
				if("getSave".equalsIgnoreCase(bean.getActionType())){
					obj[9]="S";
				} else if(StringUtils.isNotBlank(bean.getEndTypeId())) {
					obj[9]="E";
				} else {
					obj[9]="Y";
				}
				obj[10]=bean.getChqInvNo();
				obj[11]=bean.getQuoteNo();
				removeNull(obj);
				LogManager.info("Query=>"+sql);
				LogManager.info("obj[] ==> "+StringUtils.join(obj, ","));
				res=this.mytemplate.update(sql,obj);
				LogManager.info("Result=>"+res);
			}
		}catch(Exception e)
		{
			e.printStackTrace();
			result="FAILED";
		}
		LogManager.info("getGeratePolicyNew - Exit");
		return result;
	}
	
	/*public String policyGeneration(MotorBean bean) {
		String result="SUCCESS";
		try {
			LogManager.info("policyGeneration method() Enter||");
			Object obj[] = new Object[]{bean.getQuoteNo(),bean.getProductId(),bean.getProductId()};
			String sql=getQuery("GET_POLICY_STATUS");
			LogManager.info("Query=>"+sql);
			LogManager.info("Obj[]=>"+StringUtils.join(obj,","));
			final Map fromPosition = this.mytemplate.queryForMap(sql,obj);
			LogManager.info("Map Size=>"+fromPosition.size());
			if ("Y".equalsIgnoreCase(fromPosition.get("STATUS").toString())) {
				//sql = getQuery("GET_POLICY_NO");
				obj = new Object[]{travelBean.getBranchCode(),travelBean.getProductId(),travelBean.getBranchCode(),travelBean.getProductId(),travelBean.getBranchCode(),travelBean.getProductId(),travelBean.getBranchCode(),travelBean.getProductId()};
				LogManager.info("Query=>"+sql);
				LogManager.info("Obj[]=>"+StringUtils.join(obj,","));
				Map brCodePrefix =  this.mytemplate.queryForMap(sql,obj);
				bean.setPolicyNo(new CommonDAO().getSequenceNo("Policy",bean.getProductId(),bean.getBranchCode(),bean.getQuoteNo(),""));
				bean.setReceiptNo(new CommonDAO().getSequenceNo("Receipt",bean.getProductId(),bean.getBranchCode(),"",""));
				bean.setDebitNo(new CommonDAO().getSequenceNo("Debit",bean.getProductId(),bean.getBranchCode(),"",""));
			}
			else {
				bean.setPolicyNo(fromPosition.get("POLICY_NO").toString());
				bean.setReceiptNo(fromPosition.get("RECEIPT_NO").toString());
				bean.setDebitNo(fromPosition.get("DEBIT_NOTE_NO").toString());
			}
			sql=getQuery("UPD_POLICY_NO");
			obj=new Object[5];
			obj[0]=bean.getPolicyNo();
			obj[1]=bean.getReceiptNo();
			obj[2]=bean.getDebitNo();
			obj[3]="";
			obj[4]=bean.getQuoteNo();
			LogManager.info("Query=>"+sql);
			LogManager.info("obj[] ==> "+StringUtils.join(obj, ","));
			int res=this.mytemplate.update(sql,obj);
			LogManager.info("Result=>"+res);
			new com.maan.common.dao.CommonDAO().closeTrnDateCalculation(bean.getQuoteNo(),bean.getBranchCode(),"HTOS");
			LogManager.info("policyGeneration method() Exit||");
		} catch(Exception e) {
			e.printStackTrace();
			result="FAILED";
		}
		LogManager.info("getGeratePolicy - Exit");
		return result;
	}*/
	
	public List<Map<String,Object>> getPolicyInformation(String quoteNo) {
		List<Map<String,Object>> policyInfo=null;		
		LogManager.push("getPolicyInformation - Enter");
		try{
			String sql=getQuery("GET_POLICYINFO_MOTOR");
			LogManager.info("Query=>" + sql);
			LogManager.info("QuoteNo=>" + quoteNo);
			policyInfo=this.mytemplate.queryForList(sql,new String[]{quoteNo});			
		   }
		catch (Exception e) {			
			e.printStackTrace();
		}		
		LogManager.info("getPolicyInformation - Exit || Result: " + policyInfo.size());
		return policyInfo;
	}

	public String getAdminReferralUpdation(MotorBean bean) {
		LogManager.info("getAdminReferralUpdation - Enter");
		String sql="",result="";
		Object[] obj=new Object[0];
		int res=0;
		double pre=0.0,totPre;
		try
		{
			double prewithloading=0.0;
			if("-".equalsIgnoreCase(bean.getSign()))
				prewithloading=Double.parseDouble(StringUtils.isBlank(bean.getPremium())?"0":bean.getPremium()) - Double.parseDouble(StringUtils.isBlank(bean.getLoadOrDiscPremium())?"0":bean.getLoadOrDiscPremium());
			else
				prewithloading=Double.parseDouble(StringUtils.isBlank(bean.getPremium())?"0":bean.getPremium()) + Double.parseDouble(StringUtils.isBlank(bean.getLoadOrDiscPremium())?"0":bean.getLoadOrDiscPremium());
			
			com.maan.common.dao.CommonDAO commonDAO = new com.maan.common.dao.CommonDAO();
			String fee=commonDAO.calculatePolicyFee(String.valueOf(prewithloading), getApplicationBranchCode(bean.getApplicationNo()));
			
			if("-".equalsIgnoreCase(bean.getSign()))
				totPre=Double.parseDouble(bean.getPremium())+Double.parseDouble(fee)-Double.parseDouble(StringUtils.isBlank(bean.getLoadOrDiscPremium())?"0":bean.getLoadOrDiscPremium());
			else
				totPre=Double.parseDouble(bean.getPremium())+Double.parseDouble(fee)+Double.parseDouble(StringUtils.isBlank(bean.getLoadOrDiscPremium())?"0":bean.getLoadOrDiscPremium());
			bean.setTotalPremium(String.valueOf(totPre));
			obj=new Object[11];
			obj[1]=bean.getAdminRemarks();
			obj[2]=bean.getLoadOrDiscPremium();
			obj[4]=bean.getSign();
			obj[5]=bean.getTotalPremium()+"";
			obj[6]=new com.maan.common.dao.CommonDAO().getCommision(Double.parseDouble(StringUtils.isBlank(bean.getPremium())?"0":bean.getPremium()), bean.getApplicationNo());
			obj[7]=bean.getLoginId();
			obj[8]=bean.getPremium();
			
			obj[9]=fee;
			obj[10]=bean.getQuoteNo();
			if ("Y".equalsIgnoreCase(bean.getAdminRefStatus())){
				obj[0]="Admin";
				if(StringUtils.isNotBlank(bean.getEndTypeId())) {
					obj[3]="E";
				} else {
					obj[3]="Y";
				}
				bean.setReferralMsg(" Accepted.");
			}else if("N".equalsIgnoreCase(bean.getAdminRefStatus())){
				obj[0]="Referal";
				obj[3]="R";
				bean.setReferralMsg(" Rejected.");
			}else if ("A".equalsIgnoreCase(bean.getAdminRefStatus())){
				obj[0]="Referal";
				if(StringUtils.isNotBlank(bean.getEndTypeId())) {
					obj[3]="E";
				} else {
					obj[3]="Y";
				}
				bean.setReferralMsg(" Moved Pending.");
			}
			sql=getQuery("UPD_MOTOR_ADMIN_REFSTATUS");
			LogManager.info("Query=>>"+sql);
			LogManager.info("Obj[]=>>"+StringUtils.join(obj,","));
			res=this.mytemplate.update(sql,obj);              
			LogManager.info("Result=>>"+res);			
			if(res>0)
				result="SUCCESS";
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		LogManager.popRemove();
		LogManager.info("getAdminReferralUpdation - Exit");
		return result;
	}

	public String updateCovRate(MotorBean bean) {
		LogManager.info("updateCovRate - Enter");
		String result="";
		String sql ="";
		String query="";
		try {
			if("ZMW".equalsIgnoreCase(bean.getCurrencyType()))
				sql = this.getQuery("UPD_MOTOR_POLICY_COVER_RATEPR");
				
			else
				sql = this.getQuery("UPD_MOTOR_POLICY_COVER_RATEPR_USD");
				
			Object[] obj=new Object[5];
			//Object[] obj=new Object[6];
			for(int i=0;i<bean.getCoverId().size();i++) {
				/*obj[0] = StringUtils.isEmpty(bean.getBaseRate().get(i)==null?"":bean.getBaseRate().get(i).toString())?"0":bean.getBaseRate().get(i);
				obj[1] = bean.getApplicationNo();
				obj[2] = StringUtils.isEmpty(bean.getCoverId().get(i)==null?"":bean.getCoverId().get(i).toString())?"0":bean.getCoverId().get(i);
				obj[3] = bean.getBranchCode();
				obj[4] = bean.getRateVehicleId();*/
				String covId=bean.getCoverId().get(i)==null?"":bean.getCoverId().get(i).toString();
				if(StringUtils.isNotBlank(covId)){
					double rate=StringUtils.isEmpty(bean.getBaseRate().get(i)==null?"":bean.getBaseRate().get(i).toString())?0:bean.getBaseRate().get(i);
					
					obj[0] = StringUtils.isEmpty(bean.getBasePrem().get(i)==null?"":bean.getBasePrem().get(i).toString())?"0":bean.getBasePrem().get(i);
					if("103".equalsIgnoreCase(covId)){
						obj[1] = rate/100;
					}else{
						obj[1] = rate;
					}
					obj[2] = bean.getApplicationNo();
					obj[3] = StringUtils.isEmpty(bean.getCoverId().get(i)==null?"":bean.getCoverId().get(i).toString())?"0":bean.getCoverId().get(i);
					//obj[4] = bean.getBranchCode();
					obj[4] = bean.getRateVehicleId();
					removeNull(obj);
					LogManager.info("Query=>>"+sql);
					LogManager.info("Obj[]=>>"+StringUtils.join(obj,","));
					int res=this.mytemplate.update(sql,obj);              
					LogManager.info("Result=>>"+res);	
				}
			}
			try {
				query=getQuery("GET_MOTOR_PREMIUM_INFO_REF_UPDATE");
				List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
				list=this.mytemplate.queryForList(query,new Object[]{bean.getApplicationNo(),bean.getRateVehicleId()});
				double prem=0.0;
				double electrical=0.0;
				double nonelectrical=0.0;
				for(int j=0;j<list.size();j++) {
					if("0".equalsIgnoreCase(list.get(j).get("POLICYTYPE_COVERID").toString()))
						prem=Double.parseDouble(list.get(j).get("SUM_INSURED").toString());
					if("101".equalsIgnoreCase(list.get(j).get("POLICYTYPE_COVERID").toString()))
						electrical=Double.parseDouble(list.get(j).get("SUM_INSURED").toString());
					if("102".equalsIgnoreCase(list.get(j).get("POLICYTYPE_COVERID").toString()))
						nonelectrical=Double.parseDouble(list.get(j).get("SUM_INSURED").toString());
				}
				if("ZMW".equalsIgnoreCase(bean.getCurrencyType()))
					query=getQuery("UPDATE_MOTOR_DATA_DETAIL_REFERRAL_PREM_ZMW");
				else
					query=getQuery("UPDATE_MOTOR_DATA_DETAIL_REFERRAL_PREM_USD");
				
				this.mytemplate.update(query,new Object[]{prem,electrical,nonelectrical,bean.getRateVehicleId(),bean.getApplicationNo()});
				
			} catch (Exception e) {
				LogManager.info("Error in Updating Motor Data Detail Referral Prem Update");
				e.printStackTrace();
			}
			
			updateCoveragesInfo(bean.getApplicationNo(),bean.getLoginBranch(),bean.getProductId(),bean.getEndTypeId(),bean.getLoginId(),"B",bean.getUserType(),bean.getRateVehicleId(),bean.getReferal());
			result = "SUCCESS";
		} catch(Exception e) {
			e.printStackTrace();
			result="FAILED";
		}
		LogManager.popRemove();
		LogManager.info("updateCovRate - Exit");
		return result;
	}

	public String getUpdatePremiumInfo(MotorBean bean) {
		LogManager.info("getUpdatePremiumInfo - Enter");
		String sql="",result="";
		int res=0;
		double pre=0.0,totPre;
		Object[] obj=new Object[7];
		sql=this.getQuery("GET_MOTOR_COV_PRE_SUM");
		List<Map<String,Object>> excess=new ArrayList<Map<String,Object>>();
		double prewithloading=0.0;
		String policyFee= "";
		String excessAmount="";
		try{
				LogManager.info("Query=>>"+sql);
				LogManager.info("Obj[]=>>"+bean.getApplicationNo());
				pre=(Double)this.mytemplate.queryForObject(sql,new Object[]{bean.getApplicationNo()},Double.class);
				/*if(pre < Double.parseDouble(bean.getMiniPre())){
					pre=Double.parseDouble(bean.getMiniPre());
				}*/
				LogManager.info("Result=>>"+pre);
				
				String qry="SELECT EXCESS_SIGN,EXCESS_PREMIUM FROM HOME_POSITION_MASTER WHERE APPLICATION_NO=?";
				excess=this.mytemplate.queryForList(qry, new Object[]{bean.getApplicationNo()});
				if(excess!=null && excess.size()>0){
					
					String excessSign=excess.get(0).get("EXCESS_SIGN")==null?"":excess.get(0).get("EXCESS_SIGN").toString();
					excessAmount=excess.get(0).get("EXCESS_PREMIUM")==null?"0":excess.get(0).get("EXCESS_PREMIUM").toString();
					bean.setSign(excessSign);
					if("-".equalsIgnoreCase(excessSign))
						prewithloading=pre - Double.parseDouble(StringUtils.isBlank(excessAmount)?"0":excessAmount);
					else
						prewithloading=pre + Double.parseDouble(StringUtils.isBlank(excessAmount)?"0":excessAmount);
					
					com.maan.common.dao.CommonDAO commonDAO = new com.maan.common.dao.CommonDAO();
					String branchcode="";
					if("admin".equalsIgnoreCase(bean.getUserType())){
						branchcode=getApplicationBranchCode(bean.getApplicationNo());
					}else
						branchcode=bean.getLoginBranch();
					policyFee=commonDAO.calculatePolicyFee(String.valueOf(prewithloading), branchcode);	
				}
				else{
					policyFee=getPolicyFees(bean.getApplicationNo(),getApplicationBranchCode(bean.getApplicationNo()));
				}
				bean.setPolicyFee(policyFee);
				
				String loadOrDiscPremium=StringUtils.isBlank(bean.getLoadOrDiscPremium())?excessAmount:bean.getLoadOrDiscPremium();
				sql=this.getQuery("UPD_MOTOR_PRE");
				obj[0]=pre;
				obj[1]=policyFee;
				obj[2]=bean.getSign();
				obj[3]=loadOrDiscPremium;
				if("-".equalsIgnoreCase(bean.getSign()))
					totPre=pre+Double.parseDouble(policyFee)-Double.parseDouble(loadOrDiscPremium);
				else
					totPre=pre+Double.parseDouble(policyFee)+Double.parseDouble(loadOrDiscPremium);
				obj[4]=totPre;
				//updated transaction id for motor insurance
				obj[5]= bean.getTransId()==null?"":bean.getTransId();
				obj[6]=bean.getApplicationNo();
				removeNull(obj);
				LogManager.info("Query=>>"+sql);
				LogManager.info("Obj[]=>>"+StringUtils.join(obj,","));
				res=this.mytemplate.update(sql,obj);
				if(res>0)
					result="SUCCESS";
		}catch(Exception e)
		{
			e.printStackTrace();
			result="FAILED";
		}
		LogManager.popRemove();
		LogManager.info("getUpdatePremiumInfo - Exit");
		return result;
	}
	
	public List<Map<String,Object>> getHelpInfoList(String helpType, String branchCode) {
		List<Map<String,Object>> resultList = null;
		try {
			String query = getQuery("GET_MOTOR_HELP_LIST");
			Object[] args = new Object[]{helpType, branchCode};
			LogManager.info("Query==>" + query);
			LogManager.info("Args==>" + StringUtils.join(args, ","));
			resultList = this.mytemplate.queryForList(query, args);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return resultList;
	}

	
	public String updateAddVehicleDetails(MotorBean bean, int index) {
		String result="";
		LogManager.info("Enter Into updateAddVehicleDetails()");
		try {
			String query = getQuery("UPD_MOTOR_ADDVEHICLE_DTLS");
			Object[] args = new Object[18];
			if(bean.getVehicleIdList().get(index) != null && bean.getVehicleIdList().get(index) != "" ){			
			//for(int i=0 ; i<bean.getVehicleIdList().size() ; i++) {
				args[0] = bean.getVehicleColourIdList().get(index);
				args[1] = bean.getLeasedYNIdList().get(index);
				args[2] = StringUtils.isBlank(bean.getBankOfFinanceIdList().get(index))?"":bean.getBankOfFinanceIdList().get(index);
				args[3] = bean.getRegNoList().get(index);
				args[4] = bean.getChassisNoList().get(index);
				args[5] = bean.getEngineNoList().get(index);
				args[6] = ""/*bean.getInsNameArabicList().get(index)*/;
				args[7] = ""/*bean.getInsAddressArabicList().get(index)*/;
				args[8] = ""/*bean.getVehicleRegLocIdList().get(index)*/;
				args[9] = ""/*bean.getPlateCharacterIdList().get(index)*/;
				args[10] = bean.getDriverIdList().get(index);
				args[11] = bean.getCubicCapacityList().get(index);
				args[12] = bean.getPrevPolicyNoList().get(index);
				args[13] = bean.getPrevExpiryDateList().get(index);
				args[14] = bean.getPrevInsCompanyIdList().get(index);
				args[15] = bean.getApplicationNo();
				args[16] = bean.getVehicleIdList().get(index);
				args[17] = bean.getApplicationNo();
				removeNull(args);
				LogManager.info("Query==>" + query);
				LogManager.info("Args==>" + StringUtils.join(args, ","));
				int res=this.mytemplate.update(query, args);
				if(res>0)
					result="SUCCESS";
			}else{
				result="FAILED";
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			result="FAILED";
		}
		LogManager.info("Enter Into updateAddVehicleDetails()");
		return result;
		

	}

	public String saveClaimIntimationDetails(MotorBean bean) {
		String status="";
		try{
			LogManager.info("Enter Into saveClaimIntimationDetails()");
			String query=getQuery("");
			Object[] args={bean.getPolicyNo()};
			
			LogManager.info("Query => "+query);
			LogManager.info("Arguments => "+StringUtils.join(args, ","));
			status = Integer.toString(this.mytemplate.update(query,args));
			
			LogManager.info("Exit Into saveClaimIntimationDetails()");
		}catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}

	/*public void updatePaymentDetails(MotorBean bean) {
		try{
			LogManager.info("Enter Into saveClaimIntimationDetails()");
			String query = getQuery("GET_PAYMENT_TRANID_SEQ");
			bean.setMerchant_reference((String) this.mytemplate.queryForObject(query, String.class));
			query=getQuery("INS_MOTOR_PAY_DETAILS");
			Object[] args=new Object[13];
			args[0]=bean.getApplicationNo();
			args[1]=bean.getQuoteNo();
			args[2]=bean.getProductId();
			args[3]=bean.getModeOfPayment();
			args[4]= Double.valueOf(bean.getPremium())*100;
			args[5]=bean.getChequeNo();
			args[6]=bean.getBankName();
			args[7]=bean.getMicrCode();
			args[8]=bean.getChequeAmount();
			args[9]=bean.getChequeDate();
			args[10] = bean.getMerchant_reference();
			args[11] = bean.getEmail();
			args[12] = bean.getCustomerName();
			removeNull(args);
			LogManager.info("Query => "+query);
			LogManager.info("Arguments => "+StringUtils.join(args, ","));
			this.mytemplate.update(query,args);
			LogManager.info("Exit Into saveClaimIntimationDetails()");
		}catch (Exception e) {
			LogManager.debug("Exception occured @ updatePaymentDetails{"+e+"}");
		}
	}*/
	
	public List<Map<String,Object>> getMultiVehicleDetails(MotorBean bean) {
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		try {
			list= motorApi.getMultiVehicleDetails(bean);
		} catch (Exception e) {
			e.printStackTrace();
		}
			return list;
	}
		
		
		
//		List<Map<String,Object>> resultList = new ArrayList<Map<String,Object>>();
//		try {
//			if(StringUtils.isNotBlank(applicationNo)) {
//				String query = getQuery("GET_MOTOR_MULTI_DATA_DETAILS");
//				Object[] args = new Object[]{applicationNo, productId, branchCode};
//				LogManager.info("Query==>" + queryFrammer(query, args));
//				resultList = this.mytemplate.queryForList(query, args);
//			}
//			if(resultList==null || resultList.size()==0){
//				String query=getQuery("GET_MOTOR_MULTI_DATA_DETAILS_NEW") + " ORDER BY VEHICLE_ID ASC";
//				Object[] args = new Object[]{applicationNo,productId,branchCode,branchCode,branchCode};
//				LogManager.info("Query==>" + queryFrammer(query, args));
//				resultList = this.mytemplate.queryForList(query, args);
//			}
//		}
//		catch (Exception e) {
//			e.printStackTrace();
//		}
//		return resultList;
//	}
	
	public void deleteVehicleIdDetails(MotorBean bean) {
		
		try {
			motorApi.deleteVehicleIdDetails(bean);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		
//		try {
//			String query = getQuery("DEL_MOTOR_VEHICLE_DETAILS");
//			Object[] args = new Object[]{bean.getApplicationNo(),bean.getDeleteVehicleId()};
//			LogManager.info("Query==>" + query);
//			LogManager.info("Args==>" + StringUtils.join(args,", "));
//			this.mytemplate.update(query, args);
//			updateCoveragesInfo(bean.getApplicationNo(),bean.getLoginBranch(),bean.getProductId(),bean.getEndTypeId(),bean.getLoginId(), "C",bean.getUserType(),"",bean.getReferal());
//		}
//		catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
	
	public void editVehicleIdDetails(MotorBean bean) {
		List<String> makeIdList = new ArrayList<String>();
		List<String> modelIdList = new ArrayList<String>();
		List<String> typeBodyIdList = new ArrayList<String>();
		List<String> mfgYrIdList = new ArrayList<String>();
		List<String> sumInsuredList = new ArrayList<String>();
		List<String> seatingCapacityList = new ArrayList<String>();
		List<String> vehicleUsageIdList = new ArrayList<String>();
		List<String> vehicleIdList = new ArrayList<String>();
		List<String> driverDOBList = new ArrayList<String>();
		List<String> noClaimBonusIdList = new ArrayList<String>();
		List<String> claimYNIdList = new ArrayList<String>();
		List<String> claimAmountList = new ArrayList<String>();
		List<String> deductibleIdList = new ArrayList<String>();
		List<String> electricalAccList = new ArrayList<String>();
		List<String> nonElectricalAccList = new ArrayList<String>();
		List<String> vehicleUsageNameList = new ArrayList<String>();
		List<String> typeBodyNameList = new ArrayList<String>();
		List<String> vehicleColourIdList = new ArrayList<String>();
		List<String> leasedYNIdList = new ArrayList<String>();
		List<String> bankOfFinanceIdList = new ArrayList<String>();
		List<String> prevClaimYn = new ArrayList<String>();
		List<String> noOfClaims = new ArrayList<String>();
		List<String> regNoList = new ArrayList<String>();
		List<String> chassisNoList = new ArrayList<String>();
		List<String> engineNoList = new ArrayList<String>();
		List<String> cubicCapacityList = new ArrayList<String>();
		List<String> prevPolicyNoList = new ArrayList<String>();
		
		
		
		
		try {
			Map<String,Object> resMap = motorApi.editVehicleIdDetails(bean);
			if(resMap!=null) {
				
				bean.setMake(resMap.get("MakeId")==null?"":resMap.get("MakeId").toString());
				makeIdList.add(bean.getMake());
				bean.setModel(resMap.get("ModelId")==null?"":resMap.get("ModelId").toString());
				modelIdList.add(bean.getModel());
				bean.setVehicleUsage(resMap.get("VehicleUsage")==null?"":resMap.get("VehicleUsage").toString());
				vehicleUsageIdList.add(bean.getVehicleUsage());
				bean.setSeatingCapacity(resMap.get("SeatingCapacity")==null?"":resMap.get("SeatingCapacity").toString());
				seatingCapacityList.add(bean.getSeatingCapacity());
				bean.setTypeBody(resMap.get("BodyTypeId")==null?"":resMap.get("BodyTypeId").toString());
				typeBodyIdList.add(bean.getTypeBody());
				mfgYrIdList.add(resMap.get("ManufacureYear")==null?"":resMap.get("ManufacureYear").toString());
				sumInsuredList.add(resMap.get("SumInsured")==null?"":resMap.get("SumInsured").toString());
				driverDOBList.add(resMap.get("DriverDateOfBirth")==null?"":resMap.get("DriverDateOfBirth").toString());
				noClaimBonusIdList.add(resMap.get("NoOfClaims")==null?"":resMap.get("NoOfClaims").toString());
				claimAmountList.add(resMap.get("CLAIM_AMOUNT")==null?"":resMap.get("CLAIM_AMOUNT").toString());
				deductibleIdList.add(resMap.get("ExcessLimit")==null?"":resMap.get("ExcessLimit").toString());
				electricalAccList.add(resMap.get("ElectricalAccesAmt")==null?"":resMap.get("ElectricalAccesAmt").toString());
				nonElectricalAccList.add(resMap.get("NonElectricalAccesAmt")==null?"":resMap.get("NonElectricalAccesAmt").toString());
				vehicleIdList.add(resMap.get("VehicleId")==null?"":resMap.get("VehicleId").toString());
				vehicleUsageNameList.add(resMap.get("VehicleUsage")==null?"":resMap.get("VehicleUsage").toString());
				typeBodyNameList.add(resMap.get("BodyTypeId")==null?"":resMap.get("BodyTypeId").toString());
				vehicleColourIdList.add(resMap.get("VehicleColor")==null?"":resMap.get("VehicleColor").toString());
				leasedYNIdList.add(resMap.get("LeasedYn")==null?"":resMap.get("LeasedYn").toString());
				bankOfFinanceIdList.add(resMap.get("BankOfFinance")==null?"":resMap.get("BankOfFinance").toString());
				prevClaimYn.add(resMap.get("PreviousClaimYN")==null?"":resMap.get("PreviousClaimYN").toString());
				noOfClaims.add(resMap.get("NoOfClaims")==null?"":resMap.get("NoOfClaims").toString());
				regNoList.add(resMap.get("RegistrationNo")==null?"":resMap.get("RegistrationNo").toString());
				chassisNoList.add(resMap.get("ChassisNo")==null?"":resMap.get("ChassisNo").toString());
				engineNoList.add(resMap.get("EngineNo")==null?"":resMap.get("EngineNo").toString());
				cubicCapacityList.add(resMap.get("EngineCapacity")==null?"":resMap.get("EngineCapacity").toString());
				prevPolicyNoList.add(resMap.get("PolicyType")==null?"":resMap.get("PolicyType").toString());
				
				bean.setMakeIdList(makeIdList);
				bean.setModelIdList(modelIdList);
				bean.setTypeBodyIdList(typeBodyIdList);
				bean.setMfgYrIdList(mfgYrIdList);
				bean.setSumInsuredList(sumInsuredList);
				bean.setSeatingCapacityList(seatingCapacityList);
				bean.setVehicleUsageIdList(vehicleUsageIdList);
				bean.setDriverDOBList(driverDOBList);
				bean.setNoClaimBonusIdList(noClaimBonusIdList);
				bean.setClaimYNIdList(claimYNIdList);
				bean.setClaimAmountList(claimAmountList);
				bean.setDeductibleIdList(deductibleIdList);
				bean.setElectricalAccList(electricalAccList);
				bean.setNonElectricalAccList(nonElectricalAccList);
				bean.setVehicleIdList(vehicleIdList);
				bean.setVehicleUsageNameList(vehicleUsageNameList);
				bean.setTypeBodyNameList(typeBodyNameList);
				bean.setVehicleColourIdList(vehicleColourIdList);
				bean.setLeasedYNIdList(leasedYNIdList);
				bean.setBankOfFinanceIdList(bankOfFinanceIdList);
				bean.setPrevClaimYn(prevClaimYn);
				bean.setNoOfClaims(noOfClaims);
				bean.setRegNoList(regNoList);
				bean.setChassisNoList(chassisNoList);
				bean.setEngineNoList(engineNoList);
				bean.setCubicCapacityList(cubicCapacityList);
				bean.setPrevPolicyNoList(prevPolicyNoList);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
//		try {
//			Map<String,Object> resultMap = getVehicleDetailsById(bean.getApplicationNo(), bean.getProductId(), bean.getBranchCode(), bean.getDeleteVehicleId());
//			
//			List<String> makeIdList = new ArrayList<String>();
//			List<String> modelIdList = new ArrayList<String>();
//			List<String> typeBodyIdList = new ArrayList<String>();
//			List<String> mfgYrIdList = new ArrayList<String>();
//			List<String> sumInsuredList = new ArrayList<String>();
//			/*List<String> noOfCylinderIdList = new ArrayList<String>();*/
//			List<String> seatingCapacityList = new ArrayList<String>();
//			List<String> vehicleUsageIdList = new ArrayList<String>();
//			/*List<String> areaCoverageIdList = new ArrayList<String>();*/
//			List<String> vehicleIdList = new ArrayList<String>();
//			List<String> driverDOBList = new ArrayList<String>();
//			List<String> noClaimBonusIdList = new ArrayList<String>();
//			List<String> claimYNIdList = new ArrayList<String>();
//			List<String> claimAmountList = new ArrayList<String>();
//			List<String> deductibleIdList = new ArrayList<String>();
//			List<String> electricalAccList = new ArrayList<String>();
//			List<String> nonElectricalAccList = new ArrayList<String>();
//			List<String> driverIdList = new ArrayList<String>();
//			List<String> vehicleUsageNameList = new ArrayList<String>();
//			List<String> typeBodyNameList = new ArrayList<String>();
//			List<String> ownerDriverYNList = new ArrayList<String>();
//			
//			List<String> vehicleColourIdList = new ArrayList<String>();
//			List<String> leasedYNIdList = new ArrayList<String>();
//			List<String> bankOfFinanceIdList = new ArrayList<String>();
//			List<String> prevClaimYn = new ArrayList<String>();
//			List<String> noOfClaims = new ArrayList<String>();
//			List<String> regNoList = new ArrayList<String>();
//			List<String> chassisNoList = new ArrayList<String>();
//			List<String> engineNoList = new ArrayList<String>();
//			/*List<String> insNameArabicList = new ArrayList<String>();
//			List<String> insAddressArabicList = new ArrayList<String>();
//			List<String> vehicleRegLocIdList = new ArrayList<String>();
//			List<String> plateCharacterIdList = new ArrayList<String>();*/
//			List<String> cubicCapacityList = new ArrayList<String>();
//			List<String> prevPolicyNoList = new ArrayList<String>();
//			List<String> prevExpiryDateList = new ArrayList<String>();
//			List<String> prevInsCompanyIdList = new ArrayList<String>();
//			
//			
//			bean.setMake(resultMap.get("MAKE_ID")==null?"":resultMap.get("MAKE_ID").toString());
//			makeIdList.add(bean.getMake());
//			bean.setModel(resultMap.get("MODEL_ID")==null?"":resultMap.get("MODEL_ID").toString());
//			modelIdList.add(bean.getModel());
//			bean.setVehicleUsage(resultMap.get("VEHICLE_TYPE")==null?"":resultMap.get("VEHICLE_TYPE").toString());
//			vehicleUsageIdList.add(bean.getVehicleUsage());
//			bean.setSeatingCapacity(resultMap.get("SEATING_CAPACITY")==null?"":resultMap.get("SEATING_CAPACITY").toString());
//			seatingCapacityList.add(bean.getSeatingCapacity());
//			bean.setTypeBody(resultMap.get("BODY")==null?"":resultMap.get("BODY").toString());
//			typeBodyIdList.add(bean.getTypeBody());
//			
//			
//			mfgYrIdList.add(resultMap.get("MANUFACTURE_YEAR")==null?"":resultMap.get("MANUFACTURE_YEAR").toString());
//			sumInsuredList.add(resultMap.get("SUMINSURED_VALUE_LOCAL")==null?"":resultMap.get("SUMINSURED_VALUE_LOCAL").toString());
//			/*noOfCylinderIdList.add(resultMap.get("NO_OF_CYLINDER_ID")==null?"":resultMap.get("NO_OF_CYLINDER_ID").toString());*/
//			/*areaCoverageIdList.add(resultMap.get("AREA_COVERAGE_ID")==null?"":resultMap.get("AREA_COVERAGE_ID").toString());*/
//			driverDOBList.add(resultMap.get("DRIVER_DOB")==null?"":resultMap.get("DRIVER_DOB").toString());
//			noClaimBonusIdList.add(resultMap.get("NO_CLAIM_BONUS")==null?"":resultMap.get("NO_CLAIM_BONUS").toString());
//			claimYNIdList.add(resultMap.get("CLAIMYN")==null?"":resultMap.get("CLAIMYN").toString());
//			claimAmountList.add(resultMap.get("CLAIM_AMOUNT")==null?"":resultMap.get("CLAIM_AMOUNT").toString());
//			deductibleIdList.add(resultMap.get("DEDUCTIBLE_ID")==null?"":resultMap.get("DEDUCTIBLE_ID").toString());
//			electricalAccList.add(resultMap.get("ELECTRICAL_SI")==null?"":resultMap.get("ELECTRICAL_SI").toString());
//			nonElectricalAccList.add(resultMap.get("NONELECTRICAL_SI")==null?"":resultMap.get("NONELECTRICAL_SI").toString());
//			driverIdList.add(resultMap.get("DRIVER_ID")==null?"":resultMap.get("DRIVER_ID").toString());
//			vehicleIdList.add(resultMap.get("VEHICLE_ID")==null?"":resultMap.get("VEHICLE_ID").toString());
//			vehicleUsageNameList.add(resultMap.get("VEHICLETYPE_DESC")==null?"":resultMap.get("VEHICLETYPE_DESC").toString());
//			typeBodyNameList.add(resultMap.get("TYPE_OF_BODY_NAME")==null?"":resultMap.get("TYPE_OF_BODY_NAME").toString());
//			ownerDriverYNList.add(resultMap.get("OWNNERDRIVER_YN")==null?"":resultMap.get("OWNNERDRIVER_YN").toString());
//			
//			vehicleColourIdList.add(resultMap.get("VEHICLE_COLOR_ID")==null?"":resultMap.get("VEHICLE_COLOR_ID").toString());
//			leasedYNIdList.add(resultMap.get("LEASED")==null?"":resultMap.get("LEASED").toString());
//			bankOfFinanceIdList.add(resultMap.get("BANK_ID")==null?"":resultMap.get("BANK_ID").toString());
//			prevClaimYn.add(resultMap.get("CLAIMYN")==null?"":resultMap.get("CLAIMYN").toString());
//			noOfClaims.add(resultMap.get("NO_OF_CLAIMS")==null?"":resultMap.get("NO_OF_CLAIMS").toString());
//			regNoList.add(resultMap.get("REGISTRATION_NO")==null?"":resultMap.get("REGISTRATION_NO").toString());
//			chassisNoList.add(resultMap.get("CHASSIS_NO")==null?"":resultMap.get("CHASSIS_NO").toString());
//			engineNoList.add(resultMap.get("ENGINE_NUMBER")==null?"":resultMap.get("ENGINE_NUMBER").toString());
//			cubicCapacityList.add(resultMap.get("CUBIC_CAPACITY")==null?"":resultMap.get("CUBIC_CAPACITY").toString());
//			prevPolicyNoList.add(resultMap.get("PREV_POLICYNO")==null?"":resultMap.get("PREV_POLICYNO").toString());
//			prevExpiryDateList.add(resultMap.get("PREV_POLICYEXPIRYDATE")==null?"":resultMap.get("PREV_POLICYEXPIRYDATE").toString());
//			prevInsCompanyIdList.add(resultMap.get("INS_COMPANY")==null?"":resultMap.get("INS_COMPANY").toString());
//			
//			bean.setMakeIdList(makeIdList);
//			bean.setModelIdList(modelIdList);
//			bean.setTypeBodyIdList(typeBodyIdList);
//			bean.setMfgYrIdList(mfgYrIdList);
//			bean.setSumInsuredList(sumInsuredList);
//			/*bean.setNoOfCylinderIdList(noOfCylinderIdList);*/
//			bean.setSeatingCapacityList(seatingCapacityList);
//			bean.setVehicleUsageIdList(vehicleUsageIdList);
//			/*bean.setAreaCoverageIdList(areaCoverageIdList);*/
//			bean.setDriverDOBList(driverDOBList);
//			bean.setNoClaimBonusIdList(noClaimBonusIdList);
//			bean.setClaimYNIdList(claimYNIdList);
//			bean.setClaimAmountList(claimAmountList);
//			bean.setDeductibleIdList(deductibleIdList);
//			bean.setElectricalAccList(electricalAccList);
//			bean.setNonElectricalAccList(nonElectricalAccList);
//			bean.setDriverIdList(driverIdList);
//			bean.setVehicleIdList(vehicleIdList);
//			bean.setVehicleUsageNameList(vehicleUsageNameList);
//			bean.setTypeBodyNameList(typeBodyNameList);
//			bean.setOwnerDriverYNList(ownerDriverYNList);
//			
//			bean.setVehicleColourIdList(vehicleColourIdList);
//			bean.setLeasedYNIdList(leasedYNIdList);
//			bean.setBankOfFinanceIdList(bankOfFinanceIdList);
//			bean.setPrevClaimYn(prevClaimYn);
//			bean.setNoOfClaims(noOfClaims);
//			bean.setRegNoList(regNoList);
//			bean.setChassisNoList(chassisNoList);
//			bean.setEngineNoList(engineNoList);
//			bean.setCubicCapacityList(cubicCapacityList);
//			bean.setPrevPolicyNoList(prevPolicyNoList);
//			bean.setPrevExpiryDateList(prevExpiryDateList);
//			bean.setPrevInsCompanyIdList(prevInsCompanyIdList);
//			//bean.setPolicyType(resultMap.get("POLICYTYPE")==null?"":resultMap.get("POLICYTYPE").toString());
//		}
//		catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
	
	public void updateCoveragesInfo(String applicationNo, String branchCode, String productId, String endtTypeId, String loginId, String type, String userType, String vehicleId, String referal) {
		try {
			String query = "";
			Object[] args = null;
			String endtStatus = "";
			if("admin".equals(userType) ||"Y".equals(referal)) {
				endtStatus = "Referal";
			} else if(StringUtils.isNotBlank(endtTypeId)) {
				endtStatus = "Endt";
			} else {
				endtStatus = "Normal";
			}
			query = getQuery("CAL_MOTOR_PREMIUM_CALC");
			args = new Object[]{type, applicationNo, branchCode, productId, endtStatus, vehicleId};
			LogManager.info("updateCoveragesInfo Query==>" +queryFrammer(query, args));
			this.mytemplate.update(query, args);
			if("C".equals(type) && !"admin".equals(userType) && !"Y".equals(referal)) {
				if(vehCount(applicationNo)>0){
					String cur="";
					try {
						String curQry="SELECT DISTINCT CURRENCY_TYPE FROM MOTOR_DATA_DETAIL WHERE APPLICATION_NO=?";
						cur=this.mytemplate.queryForObject(curQry,new Object[]{applicationNo}, String.class).toString();
					} catch (Exception e) {
						e.printStackTrace();
					}
					if("USD".equalsIgnoreCase(cur)){
						query = getQuery("UPD_MOTOR_SIREFERRAL_DTLS_USD");
					}
					else{
						query = getQuery("UPD_MOTOR_SIREFERRAL_DTLS");
					}
					args = new Object[]{loginId,productId,applicationNo};
					LogManager.info("Query==>" + queryFrammer(query, args));
					this.mytemplate.update(query, args);
				}
			}
		}
		catch(Exception exception) {
			exception.printStackTrace();
		}
	}

	public void updSelectedPolicyDtls(MotorBean bean) {
		try {
			String TP_ID = ResourceBundleUtil.findDefaultText("MOTOR_TP_ID");
			Object[] obj = new Object[4];
			String sql=this.getQuery("UPD_MOTOR_DATA_DTLS_OPT_COVPOLTYPE");
			obj[0] = bean.getPolicyType();
			obj[1] = bean.getPolicyCover().length>0?StringUtils.join(bean.getPolicyCover(),"~"):"N";
			obj[2]=bean.getApplicationNo();
			obj[3]=bean.getApplicationNo();
			LogManager.info("Sql=>"+sql);
			LogManager.info("Obj[]=>"+StringUtils.join(obj,","));
			this.mytemplate.update(sql,obj);
			
			sql = getQuery("UPD_MOTOR_POLICY_DTLS_OPT_COVPOLTYPE");
			obj = new Object[4];
			obj[0] = bean.getPolicyType();
			obj[1] = bean.getPolicyCover().length>0?StringUtils.join(bean.getPolicyCover(),"~"):"N";
			obj[2] = (!"RA".equalsIgnoreCase(bean.getQuoteStatus()) && StringUtils.isBlank(bean.getEndTypeId()) && !"admin".equals(bean.getUser()) && !TP_ID.equals(bean.getPolicyType()))?getReferralMsgs(bean.getApplicationNo()):"";
			obj[3] = bean.getApplicationNo();
			LogManager.info("Sql=>"+sql);
			LogManager.info("Obj[]=>"+StringUtils.join(obj,","));
			removeNull(obj);
			this.mytemplate.update(sql,obj);
			
			if(!"RA".equalsIgnoreCase(bean.getQuoteStatus()) && StringUtils.isBlank(bean.getEndTypeId()) && !"admin".equals(bean.getUser())) {
				updateCoveragesInfo(bean.getApplicationNo(),bean.getLoginBranch(),bean.getProductId(),bean.getEndTypeId(),bean.getLoginId(),"C",bean.getUserType(),"",bean.getReferal());
				if(TP_ID.equals(bean.getPolicyType())) {
					updateReferralRemarks(bean.getApplicationNo(),"");
				} else {
					updateReferralRemarks(bean.getApplicationNo(),getReferralMsgs(bean.getApplicationNo()));
				}
			}
			
			sql = getQuery("UPD_MOTOR_POLICY_DTLS_OPT_COVPOLTYPE");
			obj = new Object[4];
			obj[0] = bean.getPolicyType();
			obj[1] = bean.getPolicyCover().length>0?StringUtils.join(bean.getPolicyCover(),"~"):"N";
			obj[2] = (!"RA".equalsIgnoreCase(bean.getQuoteStatus()) && StringUtils.isBlank(bean.getEndTypeId()) && !"admin".equals(bean.getUser()) && !TP_ID.equals(bean.getPolicyType()))?getReferralMsgs(bean.getApplicationNo()):"";
			obj[3] = bean.getApplicationNo();
			LogManager.info("Sql=>"+sql);
			LogManager.info("Obj[]=>"+StringUtils.join(obj,","));
			removeNull(obj);
			this.mytemplate.update(sql,obj);
			
			updateCoveragesInfo(bean.getApplicationNo(),bean.getLoginBranch(),bean.getProductId(),bean.getEndTypeId(),bean.getLoginId(),"B",bean.getUserType(),"",bean.getReferal());
		}
		catch(Exception exception) {
			exception.printStackTrace();
		}
	}
	
	public Map<String,Object> getThirdPartyPremiumInfo(String applicationNo) {
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try {
			String TP_ID = ResourceBundleUtil.findDefaultText("MOTOR_TP_ID");
			String query = getQuery("GET_MOTOR_THIRDPARTY_PREMIUMINFO");
			Object[] args = new Object[]{applicationNo,TP_ID};
			LogManager.info("query==>" + query);
			LogManager.info("args==>" + StringUtils.join(args,","));
			resultMap = this.mytemplate.queryForMap(query, args);
		} catch(Exception exception) {
			//exception.printStackTrace();
		}
		return resultMap;
	}
	
	public Map<String,Object> getVehicleDetailsById(String applicationNo, String productId, String branchCode, String vehicleId) {
		Map<String,Object> resultMap = new HashMap<String, Object>();
		try {
			String query = getQuery("GET_MOTOR_MULTI_DATA_DETAILS") + " WHERE VEHICLE_ID=?";
			Object[] args = new Object[]{applicationNo,productId,branchCode,vehicleId};
			LogManager.info("Query==>" + queryFrammer(query, args));
			resultMap = this.mytemplate.queryForMap(query, args);
		} catch(Exception exception) {
			String query=getQuery("GET_MOTOR_MULTI_DATA_DETAILS_NEW") + " AND VEHICLE_ID=? ORDER BY VEHICLE_ID ASC";
			Object[] args = new Object[]{applicationNo,productId,branchCode,branchCode,branchCode,vehicleId};
			LogManager.info("Query==>" + queryFrammer(query, args));
			resultMap = this.mytemplate.queryForMap(query, args);
		}
		return resultMap;
	}
	
	public void insertDocumentDetails(String applicationNo, String quoteNo, String vehicleId, String documentId, String documentPath) {
		try {
			String query = "";Object[] args = null;
			query = getQuery("DEL_MOTOR_DOCUMENTS");
			args = new Object[]{applicationNo, vehicleId, documentId};
			LogManager.info("Query==>" + query);
			LogManager.info("Args==>" + StringUtils.join(args,", "));
			this.mytemplate.update(query,args);
			
			query = getQuery("INS_MOTOR_DOCUMENTS");
			args = new Object[5];
			args[0] = applicationNo;
			args[1] = quoteNo;
			args[2] = vehicleId;
			args[3] = documentId;
			args[4] = documentPath;
			LogManager.info("Query==>" + query);
			LogManager.info("Args==>" + StringUtils.join(args,", "));
			this.mytemplate.update(query,args);
		} catch(Exception exception) {
			exception.printStackTrace();
		}
	}
	
	public Map<String,String> getDocumentDetails(String applicationNo, String vehicleId) {
		Map<String,String> finalMap = new HashMap<String, String>();
		try {
			String query = getQuery("GET_MOTOR_DOCUMENTS");
			Object[] args = new Object[]{applicationNo, vehicleId};
			LogManager.info("Query==>" + query);
			LogManager.info("Args==>" + StringUtils.join(args,", "));
			List<Map<String,Object>> resultList = this.mytemplate.queryForList(query, args);
			for(Map<String,Object> resultMap : resultList) {
				finalMap.put(resultMap.get("DOCUMENT_ID").toString(), resultMap.get("DOCUMENT_PATH").toString());
			}
		} catch(Exception exception) {
			exception.printStackTrace();
		}
		return finalMap;
	}
	
	public Map<String,Object> getVehicleDocumentCntVal(String applicationNo, String vehicleId) {
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try {
			String query = getQuery("GET_MOTOR_VEHICLEDOCUMENTS_VAL");
			Object[] args = new Object[]{applicationNo, applicationNo, vehicleId, applicationNo};
			LogManager.info("Query==>" + query);
			LogManager.info("Args==>" + StringUtils.join(args,", "));
			resultMap = this.mytemplate.queryForMap(query, args);
		} catch(Exception e) {
			e.printStackTrace();LogManager.info(e);
		}
		return resultMap;
	}
	
	public List<Map<String,Object>> getDocumentList(String applicationNo) {
		List<Map<String,Object>> resultList = new ArrayList<Map<String,Object>>();
		try {
			String query = getQuery("GET_MOTOR_DOCUMENTS_LIST");
			Object[] args = new Object[]{applicationNo};
			resultList = this.mytemplate.queryForList(query, args);
		} catch(Exception exception) {
			exception.printStackTrace();
		}
		return resultList;
	}
	
	public String getReferralMsgs(String applicationNo) {
		String result = "";
		try {
			if(StringUtils.isNotBlank(applicationNo)){
			String query = getQuery("GET_MOTOR_REFERRALMSGS");
			Object[] args = new Object[]{applicationNo};
			result = (String) this.mytemplate.queryForObject(query, args, String.class);
			}
		} catch(Exception e) {
			e.printStackTrace();LogManager.info(e);
		}
		return StringUtils.isBlank(result)?"":result.replaceAll("~~", ",");
	}
	
	public String getPolicyReferralMsgs(String applicationNo) {
		String result = "";
		try {
			String query = getQuery("GET_MOTOR_POLICY_REFERRALMSGS");
			Object[] args = new Object[]{applicationNo};
			result = (String) this.mytemplate.queryForObject(query, args, String.class);
		} catch(Exception e) {
			e.printStackTrace();LogManager.info(e);
		}
		return StringUtils.isBlank(result)?"":result;
	}
	
	public void updateReferralRemarks(String applicationNo, String referralRemarks) throws Exception {
		String query = getQuery("UPD_MOTOR_REFSTATUS");
		Object[] args = new Object[4];
		args[0] = referralRemarks;
		args[1] = StringUtils.isBlank(referralRemarks)?"":"Referal";
		args[2] = StringUtils.isBlank(referralRemarks)?"":"Y";
		args[3] = applicationNo;
		LogManager.info("Query==>" + query);
		LogManager.info("Args==>" + StringUtils.join(args,", "));
		int count = this.mytemplate.update(query,args);
		LogManager.info("Result==>" + count);
	}
	
	public void updateReferralRemarksNew(String applicationNo, String referralRemarks){
		try {
			String query = getQuery("UPD_MOTOR_REFSTATUS_NEW");
			Object[] args = new Object[5];
			args[0] = referralRemarks;
			args[1] = StringUtils.isBlank(referralRemarks)?"":"Referal";
			args[2] = StringUtils.isBlank(referralRemarks)?"":"Y";
			args[3] = StringUtils.isBlank(referralRemarks)?"":"Y";
			args[4] = applicationNo;
			LogManager.info("Query==>" + query);
			LogManager.info("Args==>" + StringUtils.join(args,", "));
			int count = this.mytemplate.update(query,args);
			LogManager.info("Result==>" + count);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String getSelectedPolicyType(String applicationNo) {
		String result = "";
		try {
			String query = getQuery("GET_MOTOR_SELECTED_POLICY");
			Object[] args = new Object[]{applicationNo};
			result = (String) this.mytemplate.queryForObject(query, args, String.class);
		} catch(Exception e) {
			e.printStackTrace();LogManager.info(e);
		}
		return StringUtils.isBlank(result)?"":result;
	}
	
	public String getPaymentYN(String applicationNo) {
		String result = "";
		try {
			String query = getQuery("GET_MOTOR_POLICYTYPE_PAYMENTYN");
			Object[] args = new Object[]{applicationNo};
			result = (String) this.mytemplate.queryForObject(query, args, String.class);
		} catch(Exception e) {
			e.printStackTrace();LogManager.info(e);
		}
		return result;
	}
	public void insertElectric(final MotorBean bean) {
		try {
			String query="DELETE FROM MOTOR_ELECTRICAL_DETAILS WHERE APPLICATION_NO=?";
			this.mytemplate.update(query,new Object[]{bean.getApplicationNo()});
			query ="Insert into MOTOR_ELECTRICAL_DETAILS (SNO,APPLICATION_NO, DESCRIPTION, AMOUNT) Values (?, ?, ?,?)";
			Object[] args = new Object[]{bean.getApplicationNo()};
			final int batchSize=bean.getElecSno().size();
			this.mytemplate.batchUpdate(query, new BatchPreparedStatementSetter() { 
				public int getBatchSize() {					 
					return batchSize;
				}

				public void setValues(PreparedStatement ps, int i)
						throws SQLException {
					  ps.setString(1, String.valueOf(i+1));   
					  ps.setString(2, bean.getApplicationNo());  
					  ps.setString(3, bean.getElecDescrip().get(i));
					  ps.setString(4, bean.getElecAmount().get(i));
				} 
			});
						 
		} catch(Exception e) {
			e.printStackTrace();LogManager.info(e);
		}
	}

	public void insertNonElectric(final MotorBean bean) {
		try {
			String query="DELETE FROM MOTOR_NONELECTRICAL_DETAILS WHERE APPLICATION_NO=?";
			this.mytemplate.update(query,new Object[]{bean.getApplicationNo()});
			query ="Insert into MOTOR_NONELECTRICAL_DETAILS (SNO,APPLICATION_NO, DESCRIPTION, AMOUNT) Values (?, ?, ?,?)";
			Object[] args = new Object[]{bean.getApplicationNo()};
			final int batchSize=bean.getNonelesno().size();
			this.mytemplate.batchUpdate(query, new BatchPreparedStatementSetter() { 
				public int getBatchSize() {					 
					return batchSize;
				}
	
				public void setValues(PreparedStatement ps, int i)
						throws SQLException {
					  ps.setString(1, String.valueOf(i+1));   
					  ps.setString(2, bean.getApplicationNo());  
					  ps.setString(3, bean.getNoneleDescrip().get(i));
					  ps.setString(4, bean.getNonelecAmont().get(i));
				} 
			});
						 
		} catch(Exception e) {
			e.printStackTrace();LogManager.info(e);
		}
	}

	public void getElectrical(MotorBean bean) {
		 try{
			 String query="select * from MOTOR_ELECTRICAL_DETAILS where application_no=?";
			 List<String > sno=new ArrayList<String>();
			 List<String > desc=new ArrayList<String>();
			 List<String > amount=new ArrayList<String>();
			 List<Map<String,Object>> list = this.mytemplate.queryForList(query,new Object[]{bean.getApplicationNo()});
			 if(list!=null && list.size()>0){
				 for (int i = 0; i < list.size(); i++) {
					 Map<String,Object> rec=list.get(i);
					 	sno.add(rec.get("SNO")==null?"":rec.get("SNO").toString());
					 	desc.add(rec.get("DESCRIPTION")==null?"":rec.get("DESCRIPTION").toString());
					 	amount.add(rec.get("AMOUNT")==null?"":rec.get("AMOUNT").toString()); 	 
				}
			 }
			 bean.setElecSno(sno);
			 bean.setElecDescrip(desc);
			 bean.setElecAmount(amount);			 
		 }catch (Exception e) {
			 e.printStackTrace();LogManager.info(e);
		}
	}

	public void getnelectricalPopup(MotorBean bean) {
		try{
			 String query="select * from MOTOR_NONELECTRICAL_DETAILS where application_no=?";
			 List<String > sno=new ArrayList<String>();
			 List<String > desc=new ArrayList<String>();
			 List<String > amount=new ArrayList<String>();
			 List<Map<String,Object>> list = this.mytemplate.queryForList(query,new Object[]{bean.getApplicationNo()});
			 if(list!=null && list.size()>0){
				 for (int i = 0; i < list.size(); i++) {
					 Map<String,Object> rec=list.get(i);
					 	sno.add(rec.get("SNO")==null?"":rec.get("SNO").toString());
					 	desc.add(rec.get("DESCRIPTION")==null?"":rec.get("DESCRIPTION").toString());
					 	amount.add(rec.get("AMOUNT")==null?"":rec.get("AMOUNT").toString()); 	 
				}
			 }
			 bean.setNonelesno(sno);
			 bean.setNoneleDescrip(desc);
			 bean.setNonelecAmont(amount);			 
		 }catch (Exception e) {
			 e.printStackTrace();LogManager.info(e);
		}
	}
	
	public String validateVehicleUsage(String applicationNo, String vehicleId, String vehicleType) {
		String result = "";
		try {
			String query = getQuery("VALIDATE_MOTOR_VEHICLE_TYPE");
			Object[] args = new Object[]{applicationNo,StringUtils.isBlank(vehicleId)?"99999":vehicleId,vehicleType};
			result = (String) this.mytemplate.queryForObject(query, args, String.class);
		} catch (Exception e) {
			 e.printStackTrace();LogManager.info(e);
		}
		return result;
	}
	
	public List<Map<String,Object>> getVehicleTypeDetails(String make, String model, String branchCode, String applicationNo,String vehicleId) {
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		
		try {
			list = motorApi.getVehicleTypeDetails(make,model,branchCode,applicationNo,vehicleId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
		
//		List<Map<String,Object>> resultList = new ArrayList<Map<String,Object>>();
//		try {
//			String query = getQuery("GET_MOTOR_VEHICLETYPE_APPLICATIONNO");
//			Object[] args = new Object[]{applicationNo,StringUtils.isBlank(vehicleId)?"99999":vehicleId,"99999"};
//			LogManager.info("getVehicleTypeDetails Query1==> " + queryFrammer(query, args));
//			String vehicleType = (String) this.mytemplate.queryForObject(query, args, String.class);
//			
//			
//			query = getQuery("GET_MOTOR_VEHICLETYPE_DETAILS");
//			args = new Object[]{make,model,branchCode,vehicleType,vehicleType,vehicleType};
//			LogManager.info("getVehicleTypeDetails Query2==> " + queryFrammer(query, args));
//			resultList = this.mytemplate.queryForList(query,args);
//		} catch (Exception e) {
//			 e.printStackTrace();LogManager.info(e);
//		}
//		return resultList;
//	}
	
	public void updateTPLiablity(MotorBean bean) {
		try {
			String query = getQuery("UPD_MOTOR_TPLIABITY");
			Object[] args = new Object[3];
			args[0] = bean.getTpLiablityYN();
			args[1] = bean.getTpLiablityAmount();
			args[2] = bean.getApplicationNo();
			LogManager.info("Query==> " + query);
			LogManager.info("Args==> " + StringUtils.join(args, ", "));
			this.mytemplate.update(query, args);
		} catch(Exception exception) {
			exception.printStackTrace();
		}
	}

	public String getDocumenCount(String quoteNo, String vtypeId) {
		String result="";
		try {
			String query = getQuery("GET_DOCUMENTS_COUNT_MOTOR");
			Object[] args = {quoteNo,vtypeId,"1"};

			LogManager.info("Query==> " + query);
			LogManager.info("Args==> " + StringUtils.join(args, ", "));
			 result=(String) this.mytemplate.queryForObject(query, args,String.class);
		} catch(Exception exception) {
			exception.printStackTrace();
		}
	
		return result;
	}
	
	public List<Map<String,Object>> getConditionClausesList(String quoteNo, String productId, String branchCode) {
		List<Map<String,Object>> resultList = null;
		try {
			/*String query = getQuery("GET_MOTOR_CODITIONCLAUSES_LIST");
			Object[] args = new Object[9];
			args[0] = quoteNo;
			args[1] = branchCode;
			args[2] = productId;
			args[3] = quoteNo;
			args[4] = branchCode;
			args[5] = productId;
			args[6] = quoteNo;
			args[7] = branchCode;
			args[8] = productId;
			resultList = this.mytemplate.queryForList(query, args);*/
			String query = getQuery("GET_MOTOR_CODITIONCLAUSES_LIST");
			LogManager.info("Query==> " + query);
			resultList = this.mytemplate.queryForList(query);
		} catch(Exception exception) {
			exception.printStackTrace();
		}
		return resultList;
	}

	public List<Map<String, Object>> getMototVehicleDetails(String quoteNo) {
		List<Map<String,Object>> resultList = null;
		try {
			String query = getQuery("GET_MOTOR_VHEICLE_DETAILS");
			LogManager.info("Query==> " + query);
			LogManager.info("Arguments ==> " + quoteNo);
			resultList = this.mytemplate.queryForList(query,new Object[]{quoteNo});
		} catch(Exception exception) {
			exception.printStackTrace();
		}
		return resultList;
	}
	
	
	public void insUploadImgDetail(MotorBean bean) {
		try {
			String query = "";
			Object[] args = null;
			query = getQuery("INS_MOBILE_UPLOAD_DETAIL");
			args = new Object[8];
			args[0] = bean.getCustName();
			args[1] = bean.getPolicyNo();
			args[2] = bean.getDocType();
			args[3] = bean.getDeviceModel();
			args[4] = bean.getDeviceManuf();
			args[5] = bean.getDeviceBrand();
			args[6] = bean.getDeviceProduct();
			args[7] = getSeqRefNo();
			LogManager.info("Query==>" + query);
			LogManager.info("Args==>" + StringUtils.join(args,", "));
			this.mytemplate.update(query,args);
			bean.setRefNo(getRefNo());
		} catch(Exception exception) {
			exception.printStackTrace();
		}
	}
	
	public void insUploadImgDocument(MotorBean bean) {
		try {
			String query = "";
			Object[] args = null;
			query = getQuery("INS_MOBILE_UPLOAD_DOCUMENT");
			//Insert into MOTOR_MOBILE_UPLOAD_DOCUMENT (DOC_ID, FILE_PATH, REF_NO, FILE_NAME, DETAIL_ID,UPLOAD_DATE) 
			//Values ((SELECT NVL(MAX(DOC_ID),0)+1 FROM MOTOR_MOBILE_UPLOAD_DOCUMENT),?,?,?,(SELECT DETAIL_ID FROM MOTOR_MOBILE_UPLOAD_DETAILS REF_NO = ?),SYSTIMESTAMP)
			args = new Object[4];
			args[0] = bean.getFilePath();
			args[1] = bean.getRefNo();
			args[2] = bean.getFileName();
			args[3] = bean.getRefNo();
			LogManager.info("Query==>" + query);
			LogManager.info("Args==>" + StringUtils.join(args,", "));
			this.mytemplate.update(query,args);
		} catch(Exception exception) {
			exception.printStackTrace();
		}
	}

	public String getRefNo() {
		String value="";
		try {
			String query = "";
			query = getQuery("GET_REF_NO");
			LogManager.info("Query==>" + query);
			value = (String) this.mytemplate.queryForObject(query,String.class);
		} catch(Exception exception) {
			exception.printStackTrace();
		}
		return value;
	}
	
	public String getSeqRefNo() {
		String value="";
		try {
			String query = "";
			query = getQuery("GET_MOBILE_SEQ_NO");
			LogManager.info("Query==>" + query);
			value = (String) this.mytemplate.queryForObject(query,String.class);
		} catch(Exception exception) {
			exception.printStackTrace();
		}
		return value;
	}

	public void deleteinstallment(MotorBean bean) {
		try {
			String query = getQuery("DEL_INSTALLMENT_DETAIL");
			Object[] args = new Object[]{bean.getQuoteNo()};
			LogManager.info("Query==>" + query);
			LogManager.info("Args==>" + StringUtils.join(args,", "));
			this.mytemplate.update(query, args);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public int installmentcount(MotorBean bean) {
		int count=0;
		try {
			String query = getQuery("GET_INSTALLMENT_DETAIL_COUNT");
			Object[] args = new Object[]{bean.getQuoteNo()};
			LogManager.info("Query==>" + query);
			LogManager.info("Args==>" + StringUtils.join(args,", "));
			count=this.mytemplate.queryForInt(query, args);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}

	public List<Object> getNotifyList() {
			List<Object> resultList = null;
			try {
				String query = getQuery("GET_NOTIFY_LIST_MOBILE");
				LogManager.info("Query==> " + query);
				resultList = this.mytemplate.queryForList(query);
			} catch(Exception exception) {
				exception.printStackTrace();
			}
			return resultList;
		}
	
	public List<Map<String,Object>> getTypeofAssistantList() {
		List<Map<String,Object>> resultList = null;
		try {
			String query = getQuery("GET_TYPE_ASSISTANT_LIST_MOBILE");
			LogManager.info("Query==> " + query);
			resultList = this.mytemplate.queryForList(query);
		} catch(Exception exception) {
			exception.printStackTrace();
		}
		return resultList;
	}
	public List<Map<String,Object>> getRAList(MotorBean bean) {
		List<Map<String,Object>> resultList = null;
		try {
			String query = getQuery("GET_ROAD_REF_NO_LIST");
			LogManager.info("Query==> " + query);
			resultList = this.mytemplate.queryForList(query,new Object[]{bean.getLoginId()});
		} catch(Exception exception) {
			exception.printStackTrace();
		}
		return resultList;
	}
	/*public List<Object> getPolicyNoList() {
		List<Object> resultList = null;
		try {
			String query = getQuery("GET_POLICY_NO_LIST_MOBILE");
			LogManager.info("Query==> " + query);
			resultList = this.mytemplate.queryForList(query);
		} catch(Exception exception) {
			exception.printStackTrace();
		}
		return resultList;
	}*/
	public String getRoadSeqRefNo() {
		String value="";
		try {
			String query = "";
			query = getQuery("GET_ROAD_ASSISTANT_SEQ_NO");
			LogManager.info("Query==>" + query);
			value = (String) this.mytemplate.queryForObject(query,String.class);
		} catch(Exception exception) {
			exception.printStackTrace();
		}
		return value;
	}
	
	public String getRoadRefNo() {
		String value="";
		try {
			String query = "";
			query = getQuery("GET_ROAD_REF_NO");
			LogManager.info("Query==>" + query);
			value = (String) this.mytemplate.queryForObject(query,String.class);
		} catch(Exception exception) {
			exception.printStackTrace();
		}
		return value;
	}
	
	public void insRoadAssistantDetail(MotorBean bean) {
		try {
			String query = "";
			Object[] args = null;
			//MOBILE_NO,POLICY_NO,CUSTOMER_NAME,ASSISTANT_TYPE,DESCRIPTION,LONGITUDE,LATITUDE,DEVICE_ID,REF_NO
			query = getQuery("INS_ROAD_ASSISTANT_DETAIL");
			args = new Object[12];
			args[0] = bean.getMobileNo();
			args[1] = bean.getPolicyNo();
			args[2] = bean.getCustName();
			args[3] = bean.getAssistantType();
			args[4] = bean.getDesc();
			args[5] = bean.getLongitude();
			args[6] = bean.getLatitude();
			args[7] = bean.getDeviceType();
			args[8] = bean.getLoc();
			if(!StringUtils.isEmpty(bean.getEmail())){
				args[9] = bean.getEmail();
			}else{
				args[9] = bean.getLoginId();
			}
			args[10] = bean.getLoginId();
 			args[11] = getRoadSeqRefNo();
 			removeNull(args);
			LogManager.info("Query==>" + query);
			LogManager.info("Args==>" + StringUtils.join(args,", "));
			this.mytemplate.update(query,args);
			bean.setRefNo(getRoadRefNo());
		} catch(Exception exception) {
			exception.printStackTrace();
		}
	}

	public void getInstallmentDetail(MotorBean bean) {
			LogManager.info("getInstallmentDetail - Enter ");
			String result="";
			try {
				String sql=this.getQuery("GET_INS_INTIAL_AMOUNT");
				Object[] obj=new Object[]{bean.getQuoteNo()};
				LogManager.info("Sql=>=>"+sql);
				LogManager.info("Obj[]=>"+StringUtils.join(obj,","));
				List list=this.mytemplate.queryForList(sql,obj);
				if(list!=null&&list.size()>0)
				{
					Map map=(Map)list.get(0);
					bean.setInsIntialAmount(map.get("PREMIUM_AMOUNT")==null?"":map.get("PREMIUM_AMOUNT").toString());
					bean.setNoOfIns(map.get("NO_OF_EMI")==null?"":map.get("NO_OF_EMI").toString());
				}
			}catch(Exception e) {
				e.printStackTrace();
				}
			LogManager.popRemove();
			LogManager.info("getInstallmentDetail - Exit");
		}

	public void updRAFeedBack(MotorBean bean) {
		LogManager.info("updRAFeedBack - Enter");
		try {
			String query = "";
			Object[] args = null;
			query = getQuery("UPD_ROAD_ASSISTANT_FEEDBACK");
			args = new Object[2];
			args[0]= bean.getFeedBack();
 			args[1] = bean.getRefNo();;
 			removeNull(args);
			LogManager.info("Query==>" + query);
			LogManager.info("Args==>" + StringUtils.join(args,", "));
			this.mytemplate.update(query,args);
			LogManager.info("updRAFeedBack - Exit");
		} catch(Exception exception) {
			exception.printStackTrace();
		}
	}
	public String getEmailCount(MotorBean bean) {
		String result="";
		try{
			String query=getQuery("GET_USER_EMAIL_COUNT");
			LogManager.info("Query => "+query);
			Object[] args;
			args=new Object[]{bean.getApplicationNo()};
			LogManager.info("Arguments => "+StringUtils.join(args,","));
			result = (String)this.mytemplate.queryForObject(query,args,String.class);
			/*if(Integer.parseInt(result) >0){
				query = getQuery("GET_CUSTOMER_DETAILS_ON_EMAIL");
				LogManager.info("Query => "+query);
				args=new Object[] {bean.getApplicationNo()};
				LogManager.info("Arguments => "+StringUtils.join(args,","));
				List<Map<String,Object>> res = this.mytemplate.queryForList(query,args);
				if(res.size() > 0){
					bean.setTitle(res.get(0).get("TITLE")==null?"":res.get(0).get("TITLE").toString());
					bean.setCustomerName(res.get(0).get("FIRST_NAME")==null?"":res.get(0).get("FIRST_NAME").toString());
					bean.setCustLastName(res.get(0).get("LAST_NAME")==null?"":res.get(0).get("LAST_NAME").toString());
					bean.setCustdob(res.get(0).get("DOB")==null?"":res.get(0).get("DOB").toString());
					bean.setOccupation(res.get(0).get("OCCUPATION")==null?"":res.get(0).get("OCCUPATION").toString());
					bean.setAddress1(res.get(0).get("ADDres.get(0)S1")==null?"":res.get(0).get("ADDres.get(0)S1").toString());
					bean.setAddress2(res.get(0).get("ADDres.get(0)S2")==null?"":res.get(0).get("ADDres.get(0)S2").toString());
					bean.setPoBox(res.get(0).get("POBOX")==null?"":res.get(0).get("POBOX").toString());
					bean.setCity(res.get(0).get("CITY")==null?"":res.get(0).get("CITY").toString());
					bean.setMobileNo(res.get(0).get("MOBILE")==null?"":res.get(0).get("MOBILE").toString());
					bean.setUemail(res.get(0).get("EMAIL")==null?"":res.get(0).get("EMAIL").toString());
					bean.setEmail(res.get(0).get("EMAIL")==null?"":res.get(0).get("EMAIL").toString());
				}
			}*/
		}catch(Exception e){
			LogManager.info("Exception Occured @ getemailCount"+e);
			e.printStackTrace();
		}
		return result;
	}

	public String getLoginCount(MotorBean bean, String type) {
		LogManager.info("getLoginCount -Enter");
		String result="";
		try{
			String query=getQuery("GET_USER_EXIST_COUNT");
			LogManager.info("Query => "+query);
			Object[] args = null;
			
			if("Reg".equalsIgnoreCase(type))
				args=new Object[]{bean.getMobileNum()};
			else if("New".equalsIgnoreCase(type))
				args=new Object[]{bean.getMobileNo()};
			
			LogManager.info("Arguments => "+StringUtils.join(args,","));
			result = (String)this.mytemplate.queryForObject(query,args,String.class);
			
		}catch(Exception e){
			LogManager.info("Exception Occured @ getLoginCount"+e);
			e.printStackTrace();
		}
		LogManager.info("getLoginCount -Exit");
		return result;
	}

	public int getUpdateCustDtl(MotorBean bean, String type) {
		LogManager.info("getUpdateCustDtl ("+type+") - Enter ");
		String query="";
		int res=0;
		try {
			Object[] args = null;
			if("loginNewDtl".equalsIgnoreCase(type)){
				query = getQuery("UPDATE_CUST_DETAIL");
				args = new Object[8];
				args[0] = bean.getTitle();
				args[1] = bean.getCustomerName();
				args[2] = bean.getCustLastName();
				args[3] = bean.getEmail();
				args[4] = bean.getMobileNo();
				args[5] = bean.getCustomerType();
				args[6] = bean.getCustomerId();
				args[7] = bean.getBrokerCode();
				LogManager.info("Query==>" + query);
				LogManager.info("Args==>" + StringUtils.join(args,", "));
				res=this.mytemplate.update(query,args);
			}
			if("loginRegDtl".equalsIgnoreCase(type)){
				query = getQuery("UPDATE_REGCUST_DETAIL");
				args = new Object[19];
				args[0] = bean.getTitle();
				args[1] = bean.getCustomerName();
				args[2] = bean.getCustLastName();
				args[3] = bean.getGender();
				args[4] = bean.getOccupation();
				args[5] = bean.getCustdob();
				args[6] = bean.getAddress1();
				args[7] = bean.getAddress2();
				args[8] = bean.getCity();
				args[9] = bean.getPoBox();
				args[10] = bean.getMobileNo();
				args[11] = bean.getCustAlterMobileNo();
				args[12] = bean.getEmail();
				args[13] = (bean.getCustnrc1() + "/" + bean.getCustnrc2() + "/" + bean.getCustnrc3());
				args[14] = bean.getCustPassportNo();
				args[15] = bean.getCustomerType();
				args[16] = bean.getCompanyRegNo();
				args[17] = bean.getCustomerId();
				args[18] = bean.getBrokerCode();
				LogManager.info("Query==>" + query);
				LogManager.info("Args==>" + StringUtils.join(args,", "));
				res=this.mytemplate.update(query,args);
				}
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		LogManager.info("getUpdateCustDtl ("+type+") - Exit ");
		return res;
	}

	public void setCustDetail(MotorBean bean, String type) {
		
		try {
			Map<String,Object> resMap = motorApi.setCustDetail(bean,type);
			if(resMap!=null) {
				bean.setTitle(resMap.get("Title")==null?"":resMap.get("Title").toString());
				bean.setCustomerName(resMap.get("FirstName")==null?"":resMap.get("FirstName").toString());
				bean.setCustLastName(resMap.get("LastName")==null?"":resMap.get("LastName").toString());
				bean.setCustdob(resMap.get("DateOfBirth")==null?"":resMap.get("DateOfBirth").toString());
				bean.setGender(resMap.get("Gender")==null?"":resMap.get("Gender").toString());
				bean.setOccupation(resMap.get("Occupation")==null?"":resMap.get("Occupation").toString());
				bean.setAddress1(resMap.get("Address")==null?"":resMap.get("Address").toString());
				bean.setCity(resMap.get("City")==null?"":resMap.get("City").toString());
				bean.setPoBox(resMap.get("PoBox")==null?"":resMap.get("PoBox").toString());
				bean.setMobileNo(resMap.get("MobileNo")==null?"":resMap.get("MobileNo").toString());
				bean.setCustAlterMobileNo(resMap.get("AlternatMobileNo")==null?"":resMap.get("AlternatMobileNo").toString());
				bean.setEmail(resMap.get("Email")==null?"":resMap.get("Email").toString());
				
				if(StringUtils.isNotBlank(resMap.get("Nrc").toString())) {
					String [] nrc = resMap.get("Nrc").toString().split("/");
					if(nrc!=null) {
						bean.setCustnrc1(nrc[0].toString());
						bean.setCustnrc2(nrc[1].toString());
						bean.setCustnrc3(nrc[2].toString());
					}
				}else {
					bean.setCustnrc1(null);
					bean.setCustnrc2(null);
					bean.setCustnrc3(null);
				}
				
				
				bean.setCustPassportNo(resMap.get("PassportNo")==null?"":resMap.get("PassportNo").toString());
				bean.setCustomerType(resMap.get("CustomerType")==null?"":resMap.get("CustomerType").toString());
				bean.setCompanyRegNo(resMap.get("CompanyRegNo")==null?"":resMap.get("CompanyRegNo").toString());
				bean.setBrokerCode(resMap.get("BrokerCode")==null?"":resMap.get("BrokerCode").toString());
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		
		
//		try {
//			LogManager.info("setCustDetail ("+type+") - Enter ");
//			if(StringUtils.isNotBlank(bean.getCustomerId())){
//				if("label".equalsIgnoreCase(type)){
//					String sql=this.getQuery("GET_MOTOR_QUOTE_CUST_INFO");
//					Object[] obj=new Object[]{bean.getCustomerId()};
//					LogManager.info("Sql=>=>"+sql);
//					LogManager.info("Obj[]=>"+StringUtils.join(obj,","));
//					List list=this.mytemplate.queryForList(sql,obj);
//					if(list!=null&&list.size()>0)
//					{
//						Map map=(Map)list.get(0);
//						/*bean.setTitle(map.get("TITLE")==null?"":map.get("TITLE").toString());
//						bean.setCustomerName(map.get("FIRST_NAME")==null?"":map.get("FIRST_NAME").toString());
//						bean.setCustLastName(map.get("LAST_NAME")==null?"":map.get("LAST_NAME").toString());
//						bean.setEmail(map.get("EMAIL")==null?"":map.get("EMAIL").toString());
//						bean.setCustomerType(map.get("CUSTOMER_TYPE")==null?"":map.get("CUSTOMER_TYPE").toString());
//						bean.setMobileNo(map.get("MOBILE")==null?"":map.get("MOBILE").toString());*/
//						bean.setCustNameLabel(map.get("FIRST_NAME")==null?"":map.get("FIRST_NAME").toString());
//						bean.setCustLastNameLabel(map.get("LAST_NAME")==null?"":map.get("LAST_NAME").toString());
//						bean.setCustEmailLabel(map.get("EMAIL")==null?"":map.get("EMAIL").toString());
//					}
//				}
//				else{
//					String sql=this.getQuery("GET_MOTOR_QUOTE_CUST_INFO");
//					Object[] obj=new Object[]{bean.getCustomerId()};
//					LogManager.info("Sql=>=>"+sql);
//					LogManager.info("Obj[]=>"+StringUtils.join(obj,","));
//					List list=this.mytemplate.queryForList(sql,obj);
//					if(list!=null&&list.size()>0)
//					{
//						Map map=(Map)list.get(0);
//						if(!"getVehicle".equalsIgnoreCase(type)){
//							bean.setTitle(map.get("TITLE")==null?"":map.get("TITLE").toString());
//							bean.setCustomerName(map.get("FIRST_NAME")==null?"":map.get("FIRST_NAME").toString());
//							bean.setCustLastName(map.get("LAST_NAME")==null?"":map.get("LAST_NAME").toString());
//							bean.setMobileNo(map.get("MOBILE")==null?"":map.get("MOBILE").toString());
//							bean.setEmail(map.get("EMAIL")==null?"":map.get("EMAIL").toString());
//							bean.setCustomerType(map.get("CUSTOMER_TYPE")==null?"":map.get("CUSTOMER_TYPE").toString());
//						}
//						bean.setGender(map.get("GENDER")==null?"":map.get("GENDER").toString());
//						bean.setOccupation(map.get("OCCUPATION")==null?"":map.get("OCCUPATION").toString());
//						bean.setCustdob(map.get("DOB")==null?"":map.get("DOB").toString());
//						bean.setAddress1(map.get("ADDRESS1")==null?"":map.get("ADDRESS1").toString());
//						bean.setAddress2(map.get("ADDRESS2")==null?"":map.get("ADDRESS2").toString());
//						bean.setCity(map.get("EMIRATE")==null?"":map.get("EMIRATE").toString());
//						bean.setPoBox(map.get("POBOX")==null?"":map.get("POBOX").toString());
//						bean.setCustAlterMobileNo(map.get("ALTERNATE_MOBILE")==null?"":map.get("ALTERNATE_MOBILE").toString());
//						bean.setCustnrc1(map.get("NRC1")==null?"":map.get("NRC1").toString());
//						bean.setCustnrc2(map.get("NRC2")==null?"":map.get("NRC2").toString());
//						bean.setCustnrc3(map.get("NRC3")==null?"":map.get("NRC3").toString());
//						bean.setCustPassportNo(map.get("PASSPORT_NUMBER")==null?"":map.get("PASSPORT_NUMBER").toString());
//						bean.setCompanyRegNo(map.get("COMPANY_REG_NO")==null?"":map.get("COMPANY_REG_NO").toString());
//						bean.setCustNameLabel(bean.getCustomerName());
//						bean.setCustLastNameLabel(bean.getCustLastName());
//						bean.setCustEmailLabel(bean.getEmail());
//					}
//				}
//			}
//			
//				/*else if("Registered".equalsIgnoreCase(type)){
//				String sql=this.getQuery("GET_MOTOR_QUOTE_REGCUST_INFO");
//				Object[] obj=new Object[]{bean.getLoginId()};
//				LogManager.info("Sql=>=>"+sql);
//				LogManager.info("Obj[]=>"+StringUtils.join(obj,","));
//				List list=this.mytemplate.queryForList(sql,obj);
//				if(list!=null&&list.size()>0)
//				{
//					Map map=(Map)list.get(0);
//					bean.setTitle(map.get("TITLE")==null?"":map.get("TITLE").toString());
//					bean.setCustomerName(map.get("FIRST_NAME")==null?"":map.get("FIRST_NAME").toString());
//					bean.setCustLastName(map.get("LAST_NAME")==null?"":map.get("LAST_NAME").toString());
//					bean.setGender(map.get("GENDER")==null?"":map.get("GENDER").toString());
//					bean.setOccupation(map.get("OCCUPATION")==null?"":map.get("OCCUPATION").toString());
//					bean.setCustdob(map.get("DOB")==null?"":map.get("DOB").toString());
//					bean.setAddress1(map.get("ADDRESS1")==null?"":map.get("ADDRESS1").toString());
//					bean.setAddress2(map.get("ADDRESS2")==null?"":map.get("ADDRESS2").toString());
//					bean.setCity(map.get("EMIRATE")==null?"":map.get("EMIRATE").toString());
//					bean.setPoBox(map.get("POBOX")==null?"":map.get("POBOX").toString());
//					bean.setMobileNo(map.get("MOBILE")==null?"":map.get("MOBILE").toString());
//					bean.setCustAlterMobileNo(map.get("ALTERNATE_MOBILE")==null?"":map.get("ALTERNATE_MOBILE").toString());
//					bean.setEmail(map.get("EMAIL")==null?"":map.get("EMAIL").toString());
//					bean.setCustnrc1(map.get("NRC1")==null?"":map.get("NRC1").toString());
//					bean.setCustnrc2(map.get("NRC2")==null?"":map.get("NRC2").toString());
//					bean.setCustnrc3(map.get("NRC3")==null?"":map.get("NRC3").toString());
//					bean.setCustPassportNo(map.get("PASSPORT_NUMBER")==null?"":map.get("PASSPORT_NUMBER").toString());
//					bean.setCustomerType(map.get("CUSTOMER_TYPE")==null?"":map.get("CUSTOMER_TYPE").toString());
//					bean.setCompanyRegNo(map.get("COMPANY_REG_NO")==null?"":map.get("COMPANY_REG_NO").toString());
//				}
//			}else if("RegNewQuote".equalsIgnoreCase(type)){
//				String sql=this.getQuery("GET_MOTOR_QUOTE_REGCUSTNEW_INFO");
//				Object[] obj=new Object[]{bean.getLoginId()};
//				LogManager.info("Sql=>=>"+sql);
//				LogManager.info("Obj[]=>"+StringUtils.join(obj,","));
//				List list=this.mytemplate.queryForList(sql,obj);
//				if(list!=null&&list.size()>0)
//				{
//					Map map=(Map)list.get(0);
//					bean.setTitle(map.get("TITLE")==null?"":map.get("TITLE").toString());
//					bean.setCustomerName(map.get("FIRST_NAME")==null?"":map.get("FIRST_NAME").toString());
//					bean.setCustLastName(map.get("LAST_NAME")==null?"":map.get("LAST_NAME").toString());
//					bean.setGender(map.get("GENDER")==null?"":map.get("GENDER").toString());
//					bean.setOccupation(map.get("OCCUPATION")==null?"":map.get("OCCUPATION").toString());
//					bean.setCustdob(map.get("DOB")==null?"":map.get("DOB").toString());
//					bean.setAddress1(map.get("ADDRESS1")==null?"":map.get("ADDRESS1").toString());
//					bean.setAddress2(map.get("ADDRESS2")==null?"":map.get("ADDRESS2").toString());
//					bean.setCity(map.get("EMIRATE")==null?"":map.get("EMIRATE").toString());
//					bean.setPoBox(map.get("POBOX")==null?"":map.get("POBOX").toString());
//					bean.setMobileNo(map.get("MOBILE")==null?"":map.get("MOBILE").toString());
//					bean.setCustAlterMobileNo(map.get("ALTERNATE_MOBILE")==null?"":map.get("ALTERNATE_MOBILE").toString());
//					bean.setEmail(map.get("EMAIL")==null?"":map.get("EMAIL").toString());
//					bean.setCustnrc1(map.get("NRC1")==null?"":map.get("NRC1").toString());
//					bean.setCustnrc2(map.get("NRC2")==null?"":map.get("NRC2").toString());
//					bean.setCustnrc3(map.get("NRC3")==null?"":map.get("NRC3").toString());
//					bean.setCustPassportNo(map.get("PASSPORT_NUMBER")==null?"":map.get("PASSPORT_NUMBER").toString());
//					bean.setCustomerType(map.get("CUSTOMER_TYPE")==null?"":map.get("CUSTOMER_TYPE").toString());
//					bean.setCompanyRegNo(map.get("COMPANY_REG_NO")==null?"":map.get("COMPANY_REG_NO").toString());
//				}
//			}else if("NewQuote".equalsIgnoreCase(type)){
//				String sql=this.getQuery("GET_MOTOR_QUOTE_REGCUSTNEW_INFO");
//				Object[] obj=new Object[]{bean.getLoginId()};
//				LogManager.info("Sql=>=>"+sql);
//				LogManager.info("Obj[]=>"+StringUtils.join(obj,","));
//				List list=this.mytemplate.queryForList(sql,obj);
//				if(list!=null&&list.size()>0)
//				{
//					Map map=(Map)list.get(0);
//					bean.setTitle(map.get("TITLE")==null?"":map.get("TITLE").toString());
//					bean.setCustomerName(map.get("FIRST_NAME")==null?"":map.get("FIRST_NAME").toString());
//					bean.setCustLastName(map.get("LAST_NAME")==null?"":map.get("LAST_NAME").toString());
//					bean.setGender(map.get("GENDER")==null?"":map.get("GENDER").toString());
//					bean.setOccupation(map.get("OCCUPATION")==null?"":map.get("OCCUPATION").toString());
//					bean.setCustdob(map.get("DOB")==null?"":map.get("DOB").toString());
//					bean.setAddress1(map.get("ADDRESS1")==null?"":map.get("ADDRESS1").toString());
//					bean.setAddress2(map.get("ADDRESS2")==null?"":map.get("ADDRESS2").toString());
//					bean.setCity(map.get("EMIRATE")==null?"":map.get("EMIRATE").toString());
//					bean.setPoBox(map.get("POBOX")==null?"":map.get("POBOX").toString());
//					bean.setMobileNo(map.get("MOBILE")==null?"":map.get("MOBILE").toString());
//					bean.setCustAlterMobileNo(map.get("ALTERNATE_MOBILE")==null?"":map.get("ALTERNATE_MOBILE").toString());
//					bean.setEmail(map.get("EMAIL")==null?"":map.get("EMAIL").toString());
//					bean.setCustnrc1(map.get("NRC1")==null?"":map.get("NRC1").toString());
//					bean.setCustnrc2(map.get("NRC2")==null?"":map.get("NRC2").toString());
//					bean.setCustnrc3(map.get("NRC3")==null?"":map.get("NRC3").toString());
//					bean.setCustPassportNo(map.get("PASSPORT_NUMBER")==null?"":map.get("PASSPORT_NUMBER").toString());
//					bean.setCustomerType(map.get("CUSTOMER_TYPE")==null?"":map.get("CUSTOMER_TYPE").toString());
//					bean.setCompanyRegNo(map.get("COMPANY_REG_NO")==null?"":map.get("COMPANY_REG_NO").toString());
//				}
//			}*/
//			
//		} catch (DataAccessException e) {
//			e.printStackTrace();
//		}
//		LogManager.info("setCustDetail ("+type+") - Exit ");
//	}

	public int isExistInRenewal(String policyNo) {
		LogManager.info("ENTER-->Method to isExistInRenewal");
		int res=0;
		try {
			String sql="SELECT COUNT(*) FROM XX_B2C_RENEWAL WHERE POL_NO=?";
			Object[] args=new Object[]{policyNo};
			LogManager.info("isExistInRenewal Query===>" + queryFrammer(sql, args));
			res=this.mytemplate.queryForInt(sql, args);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public String getCurrencyType(MotorBean bean) {
		String currency="";
		try {
			String sql="SELECT NVL(CURRENCY,'ZMW') CURRENCY FROM HOME_POSITION_MASTER WHERE QUOTE_NO=?";
			Object[] args=new Object[]{bean.getQuoteNo()};
			currency=(String) this.mytemplate.queryForObject(sql,args, String.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return currency;
	}

	public List<Map<String, Object>> getPolicyTypeList(MotorBean bean) {
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		
		try {
			list = motorApi.getPolicyTypeList(bean);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
		
		
//		LogManager.push("ENTER-->Method to getPolicyTypeList");
//		List<Map<String, Object>> result=new ArrayList<Map<String,Object>>();
//		 try{
//			  String query=getQuery("GET_BROKER_POLICYTYPE_LIST"); 
//			  Object[] args = new Object[]{bean.getLoginId()};
//			  LogManager.info("Query===>" + query+", Args => "+StringUtils.join(args, ","));
//			  //result= this.mytemplate.queryForList(query,args); 
//			  result= this.mytemplate.queryForList(query);
//		 }catch (Exception e) {
//			e.printStackTrace(); 
//		}
//		 return result;
//	}

	public List<Map<String, Object>> getOptionalCovers(MotorBean bean) {
		LogManager.push("ENTER-->Method to getOptionalCovers");
		List<Map<String, Object>> result=new ArrayList<Map<String,Object>>();
		String condition="";
		 try{
			 /*if("1".equalsIgnoreCase(bean.getPolicyType())){
				 condition=" AND Y_ID NOT IN ('11') ";
			 }else if("2".equalsIgnoreCase(bean.getPolicyType())){
				 condition="";
			 }else if("3".equalsIgnoreCase(bean.getPolicyType())){
				 condition=" AND Y_ID IN ('0') ";
			 }*/
			 
			  String query="SELECT DISTINCT Y_ID,DISPLAY_ORDER FROM MOTOR_POLICY_COVERAGE WHERE APPLICATION_NO=? AND BRANCH_CODE=? AND X_ID=? AND STATUS='Y' AND IS_SELECTED='Y' ORDER BY DISPLAY_ORDER"; 
			  Object[] args = new Object[]{bean.getApplicationNo(),bean.getBranchCode(),bean.getPolicyType()};
			  LogManager.info("Query===>" + query+", Args => "+StringUtils.join(args, ","));
			  result= this.mytemplate.queryForList(query,args); 
		 }catch (Exception e) {
			e.printStackTrace(); 
		}
		 return result;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getCustomerList(MotorBean bean, String type, String userId) {
		LogManager.push("ENTER-->Method to getOptionalCovers");
		List<Map<String, Object>> result=null;
		String query="";
		try {
			if("custList".equalsIgnoreCase(bean.getMode()))
				query = " SELECT CUSTOMER_TYPE,UPPER(NVL(COMPANY_NAME,FIRST_NAME)) FIRST_NAME,LAST_NAME, EMAIL, POBOX, MOBILE, EMIRATE CITY_NAME,EMIRATE, CUSTOMER_ID, MISSIPPI_CUSTOMER_CODE, TITLE, ADDRESS1, ADDRESS2,CUST_AR_NO, REPLACE( 'P.O.BOX.' || NVL(POBOX, ' ') || NVL(ADDRESS1, ' ') || NVL(ADDRESS2, ' ') || NVL (EMIRATE , ' '), 'select', '') ADDRESS,cust_name FROM PERSONAL_INFO WHERE LOGIN_ID IN (SELECT LOGIN_ID FROM LOGIN_MASTER WHERE OA_CODE = (?)) AND APPLICATION_ID = '1' and customer_type = ? ORDER BY FIRST_NAME ";
			else	
				query = " SELECT CUSTOMER_TYPE,UPPER(NVL(COMPANY_NAME,FIRST_NAME)) FIRST_NAME,LAST_NAME, EMAIL, POBOX, MOBILE, EMIRATE CITY_NAME,EMIRATE, CUSTOMER_ID, MISSIPPI_CUSTOMER_CODE, TITLE, ADDRESS1, ADDRESS2,CUST_AR_NO, REPLACE( 'P.O.BOX.' || NVL(POBOX, ' ') || NVL(ADDRESS1, ' ') || NVL(ADDRESS2, ' ') || NVL (EMIRATE , ' '), 'select', '') ADDRESS,cust_name FROM PERSONAL_INFO WHERE LOGIN_ID IN (SELECT LOGIN_ID FROM LOGIN_MASTER WHERE OA_CODE = (SELECT OA_CODE FROM LOGIN_MASTER WHERE LOGIN_ID = ?)) AND APPLICATION_ID = '1' and customer_type = ? ORDER BY FIRST_NAME ";
			Object[] args = new Object[]{userId,type};
			LogManager.info("Query===>" + query+", Args => "+StringUtils.join(args, ","));
			result = this.mytemplate.queryForList(query , args);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int updateDriverDetails(MotorBean bean) {
		int result = 0;
		
		try {
			result = motorApi.updateDriverDetails(bean);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
//		
//		LogManager.push("ENTER-->Method to updateDriverDetails");
//		int res=0;
//		try {
//			String qry="UPDATE MOTOR_DATA_DETAIL SET DRIVER_ID=?,DRIVER_DOB=TO_DATE(?,'DD/MM/YYYY'),OWNNERDRIVER_YN=?,NO_CLAIM_BONUS=?,CLAIMYN=?,CLAIM_AMOUNT=?,PREV_POLICYNO=?,PREV_POLICYEXPIRYDATE=TO_DATE(?,'DD/MM/YYYY'),INS_COMPANY=? WHERE APPLICATION_NO=? AND VEHICLE_ID=?";
//			Object arg[]=new Object[]{bean.getDriverIdList().get(0),bean.getDriverDOBList().get(0),bean.getOwnerDriverYNList().get(0),bean.getNoClaimBonusIdList().get(0),bean.getClaimYNIdList().get(0),bean.getClaimAmountList().get(0), bean.getPrevPolicyNoList().get(0),bean.getPrevExpiryDateList().get(0),bean.getPrevInsCompanyIdList().get(0),bean.getApplicationNo(),bean.getDeleteVehicleId()};
//			LogManager.info("Query===>" + queryFrammer(qry, arg));
//			res=this.mytemplate.update(qry,arg);
//			updateCoveragesInfo(bean.getApplicationNo(),bean.getLoginBranch(),bean.getProductId(),bean.getEndTypeId(),bean.getLoginId(),"C",bean.getUserType(),"",bean.getReferal());
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return res;
//	}
	
	public int updateVehiclePolicyDtl(MotorBean bean){
		LogManager.push("ENTER-->Method to updateVehiclePolicyDtl");
		int res=0;
		try {
			Map<String,Object> map = getMotorDataDetails(bean.getApplicationNo());
			bean.setPolicyStartDate(map.get("POLICYSTARTDATE")==null?"":map.get("POLICYSTARTDATE").toString());
			bean.setPolicyEndDate(map.get("POLICYENDDATE")==null?"":map.get("POLICYENDDATE").toString());
			bean.setPremiumType(map.get("POLICYTYPE")==null?"":map.get("POLICYTYPE").toString());
			bean.setCurrencyType(map.get("CURRENCY_TYPE")==null?"":map.get("CURRENCY_TYPE").toString());
			bean.setPolicyType(map.get("ISSELECTCOVER")==null?"":map.get("ISSELECTCOVER").toString());
			
			String qry="UPDATE MOTOR_DATA_DETAIL SET POLICYTYPE=?,CURRENCY_TYPE=?,INCEPTION_DATE=TO_DATE(?,'DD/MM/YYYY'),EXPIRY_DATE=TO_DATE(?,'DD/MM/YYYY'),DRIVER_DOB=TO_DATE(sysdate-1,'DD/MM/YYYY') WHERE APPLICATION_NO=? AND PRODUCT_ID=?"; // Update Driver Age as 25 Default on 14/01/2023
			Object arg[]=new Object[]{bean.getPolicyType(),bean.getCurrencyType(),bean.getPolicyStartDate(),bean.getPolicyEndDate(),bean.getApplicationNo(),bean.getProductId()};
			LogManager.info("Query===>" + qry+", Args => "+StringUtils.join(arg, ","));
			res=this.mytemplate.update(qry,arg);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public LinkedList<String> getVehicleDetailsValidation(MotorBean bean, LinkedList<String> list) {
		int driverCount=0;
		String vehId="";
		//LinkedList<String> list=new LinkedList<String>();
		List<Map<String,Object>> vehIds=new ArrayList<Map<String,Object>>();
		try{
			String vehQry="SELECT VEHICLE_ID,CURRENCY_TYPE,SUMINSURED_VALUE_LOCAL,SUMINSURED_VALUE_FOREIGN FROM MOTOR_DATA_DETAIL WHERE APPLICATION_NO=? AND PRODUCT_ID=? ORDER BY VEHICLE_ID";
			Object[] vehObj = new Object[]{bean.getApplicationNo(),bean.getProductId()};
			vehIds=this.mytemplate.queryForList(vehQry,vehObj);
			String customertype=getCustomerType(bean);
			if(vehIds!=null && vehIds.size()>0){
				for(int i=0;i<vehIds.size();i++){
					vehId=vehIds.get(i).get("VEHICLE_ID")==null?"":vehIds.get(i).get("VEHICLE_ID").toString();
					/** Commented on 14/01/2023 on request from mapande mail start**/
					/*String driverQry="SELECT COUNT(*) FROM MOTOR_DATA_DETAIL WHERE APPLICATION_NO=? AND PRODUCT_ID=? AND VEHICLE_ID=? AND DRIVER_DOB IS NOT NULL AND DRIVER_ID IS NOT NULL";
					Object[] driverObj = new Object[]{bean.getApplicationNo(),bean.getProductId(),vehId};
					driverCount=this.mytemplate.queryForInt(driverQry,driverObj);
					if(driverCount<=0 && "INDIVIDUAL".equalsIgnoreCase(customertype)){
						list.add("Please Add Driver Details For Vehicle " +(i+1) );
					}*/
					/** Commented on 14/01/2023 on request from mapande mail End**/
					if(!"3".equalsIgnoreCase(bean.getPolicyType())){
						if("ZMW".equalsIgnoreCase(vehIds.get(i).get("CURRENCY_TYPE")==null?"":vehIds.get(i).get("CURRENCY_TYPE").toString())){
							if(StringUtils.isBlank(vehIds.get(i).get("SUMINSURED_VALUE_LOCAL")==null?"":vehIds.get(i).get("SUMINSURED_VALUE_LOCAL").toString())){
								list.add("Please Enter Vehicle Value For Vehicle " +(i+1) );
							}
						}if("USD".equalsIgnoreCase(vehIds.get(i).get("CURRENCY_TYPE")==null?"":vehIds.get(i).get("CURRENCY_TYPE").toString())){
							if(StringUtils.isBlank(vehIds.get(i).get("SUMINSURED_VALUE_FOREIGN")==null?"":vehIds.get(i).get("SUMINSURED_VALUE_FOREIGN").toString())){
								list.add("Please Enter Vehicle Value For Vehicle " +(i+1) );
							}
						}
					}
					
				}
			}else{
				list.add("Please Add Vehicle Details");
			}
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return list;
	}
	public List<Map<String,Object>> getVehicleDetailsByIdNew(String applicationNo, String productId, String branchCode, String vehicleId) {
		List<Map<String,Object>> resultMap = new ArrayList<Map<String,Object>>();
		try {
			String query = getQuery("GET_MOTOR_MULTI_DATA_DETAILS") + " WHERE VEHICLE_ID=?";
			Object[] args = new Object[]{applicationNo,productId,branchCode,vehicleId};
			LogManager.info("Query==>" + query);
			LogManager.info("Args==>" + StringUtils.join(args,", "));
			resultMap = this.mytemplate.queryForList(query, args);
			if(resultMap==null || resultMap.size()==0){
				query=getQuery("GET_MOTOR_MULTI_DATA_DETAILS_NEW") + " AND VEHICLE_ID=? ORDER BY VEHICLE_ID ASC";
				args = new Object[]{applicationNo,productId,branchCode,branchCode,branchCode,vehicleId};
				LogManager.info("Query==>" + queryFrammer(query, args));
				resultMap = this.mytemplate.queryForList(query, args);
			}
		} catch(Exception exception) {
			String query=getQuery("GET_MOTOR_MULTI_DATA_DETAILS_NEW") + " AND VEHICLE_ID=? ORDER BY VEHICLE_ID ASC";
			Object[] args = new Object[]{applicationNo,productId,branchCode,branchCode,branchCode,vehicleId};
			LogManager.info("Query==>" + queryFrammer(query, args));
			resultMap = this.mytemplate.queryForList(query, args);
		}
		return resultMap;
	}

	public Map checkPolicy(MotorBean bean) {
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

	public String getLabelInfo(MotorBean bean, boolean errorStatus) {
		LogManager.info("getQuoteInfo - Enter ");
		String result="";
		try {
			String sql=this.getQuery("GET_MOTOR_QUOTE_INFO");
			//obj=new Object[]{bean.getApplicationNo(),bean.getProductId(),bean.getBranchCode(),bean.getBranchCode(),bean.getBranchCode(),bean.getBranchCode(),bean.getBranchCode(),bean.getBranchCode()};
			//Object[] obj=new Object[]{bean.getApplicationNo(),bean.getProductId(),bean.getLoginBranch()};
			Object[] obj=new Object[]{bean.getApplicationNo(),bean.getProductId()};
			LogManager.info("Sql=>=>"+sql);
			LogManager.info("Obj[]=>"+StringUtils.join(obj,","));
			List list=this.mytemplate.queryForList(sql,obj);
			if(list!=null&&list.size()>0)
			{
				Map map=(Map)list.get(0);
				bean.setQuoteDate(map.get("ENTRY_DATE")==null?"":map.get("ENTRY_DATE").toString());
				bean.setProduct(map.get("PRODUCT_NAME")==null?"":map.get("PRODUCT_NAME").toString());
				//bean.setPolicyStartDate(map.get("POLICYSTARTDATE")==null?"":map.get("POLICYSTARTDATE").toString());
				bean.setPolicyEndDate(map.get("POLICYENDDATE")==null?"":map.get("POLICYENDDATE").toString());
				bean.setPolicyEndDatePeriod(map.get("POLICYENDDATE_PERIOD")==null?"":map.get("POLICYENDDATE_PERIOD").toString());
				bean.setPremiumType(map.get("POLICYTYPE")==null?"":map.get("POLICYTYPE").toString());
			
				bean.setLoginId(map.get("LOGIN_ID")==null?"":map.get("LOGIN_ID").toString());
				
				//Motor Policy Details
				bean.setCurrencyType(map.get("CURRENCY_TYPE")==null? ResourceBundleUtil.findDefaultText("MOTOR_CURRENCY_LOCAL"):map.get("CURRENCY_TYPE").toString());
				
				String policyTypeDesc="";
				String polType=map.get("ISSELECTCOVER")==null?"":map.get("ISSELECTCOVER").toString();
				try{
					String query="SELECT POLICYTYPE_ID,POLICYTYPE_DESC_ENGLISH FROM MOTOR_POLICYTYPE_MASTER WHERE POLICYTYPE_ID=?";
					List listNew=this.mytemplate.queryForList(query,new Object[]{polType});
					if(listNew!=null&&listNew.size()>0)
					{
						Map mapnew=(Map)listNew.get(0);
						policyTypeDesc=(mapnew.get("POLICYTYPE_DESC_ENGLISH")==null?"":mapnew.get("POLICYTYPE_DESC_ENGLISH").toString());
					}
				}catch(Exception e){
					
				}
				bean.setPolicyTypeDesc(policyTypeDesc);
				
				if(errorStatus==false) {
					new CustomerDAO().setCustomerDetails(bean);
					if(StringUtils.isBlank(bean.getCustdob())) {
						String query = getQuery("GET_DOB_BYDRIVERDOB");
						Object[] args = new Object[]{bean.getApplicationNo()};
						List<Map<String,Object>> tempResult = this.mytemplate.queryForList(query,args);
						if(tempResult.size()>0) {
							Map<String,Object> res = tempResult.get(0);
							bean.setCustdob(res.get("DRIVER_DOB")==null?"":res.get("DRIVER_DOB").toString());
						}
					}
				}
			}
			if(!"admin".equals(bean.getUser()) && !"User".equalsIgnoreCase(bean.getUserType())){
				bean.setShowReferralYN(new com.maan.common.dao.CommonDAO().getReferralYN(bean.getLoginId()));
			}
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		LogManager.popRemove();
		LogManager.info("getQuoteInfo - Exit");
		return result;
	}

	public String updatePolDate(MotorBean bean) {
		LogManager.info("updatePolDate - Enter ");
		String qry="";
		try {

			qry="UPDATE MOTOR_POLICY_DETAILS SET POLICYSTARTDATE=TO_DATE (?,'DD/MM/YYYY') WHERE APPLICATIONNO=?";
			Object[] obj = new Object[]{bean.getPolicyStartDate(),bean.getApplicationNo()};
			this.mytemplate.update(qry,obj);
			
			qry="UPDATE HOME_POSITION_MASTER SET INCEPTION_DATE=TO_DATE (?,'DD/MM/YYYY'),POLICY_TERM=TRUNC(EXPIRY_DATE)-TRUNC(TO_DATE(?,'DD/MM/YYYY')) WHERE APPLICATION_NO=?";
			Object[] args = new Object[]{bean.getPolicyStartDate(),bean.getPolicyStartDate(),bean.getApplicationNo()};
			this.mytemplate.update(qry,args);
			
			qry="UPDATE MOTOR_DATA_DETAIL SET INCEPTION_DATE=TO_DATE (?,'DD/MM/YYYY') WHERE APPLICATION_NO=?";
			Object[] arg1 = new Object[]{bean.getPolicyStartDate(),bean.getApplicationNo()};
			this.mytemplate.update(qry,arg1);
			
			
			//qry="UPDATE HOME_POSITION_MASTER SET POLICY_TERM=TRUNC(EXPIRY_DATE)-TRUNC(INCEPTION_DATE) WHERE APPLICATION_NO=?";
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "";
	}
	
	public int vehCount(String appNo){
		int count=0;
		try {
			String qry="SELECT COUNT(*) FROM MOTOR_POLICY_COVER_DATA MPD, MOTOR_DATA_DETAIL MDD WHERE MPD.APPLICATION_NO=MDD.APPLICATION_NO AND MPD.APPLICATION_NO=?";
			Object[] obj = new Object[]{appNo};
			count=this.mytemplate.queryForInt(qry,obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}

	public String getModelName(MotorBean bean) {
		String modelName="";
		try {
			String qry="SELECT UPPER(MODEL_NAME) MODEL_NAME FROM MOTOR_MODEL_MASTER WHERE MODEL_ID=? AND BRANCH_CODE=?";
			Object[] obj = new Object[]{bean.getModelIdList().get(0).toString(),bean.getBranchCode()};
			modelName=(String) this.mytemplate.queryForObject(qry,obj, String.class);
		} catch (Exception e) {
			modelName="";
			e.printStackTrace();
		}
		return modelName;
	}
	
	public String updateCovRateVeh (MotorBean bean) {
		LogManager.info("updateCovRateVeh - Enter");
		String result="";
		String sql ="";
		String query="";
		int id=Integer.parseInt(bean.getRateVehicleId());
		try {
			if("ZMW".equalsIgnoreCase(bean.getCurrencyType()))
				sql = this.getQuery("UPD_MOTOR_POLICY_COVER_RATEPR_NEW");
				
			else
				sql = this.getQuery("UPD_MOTOR_POLICY_COVER_RATEPR_USD_NEW");
				
			Object[] obj=new Object[5];
			//Object[] obj=new Object[6];
			//for(int i=0;i<bean.getCoverId().size();i++) {
				obj[0] = StringUtils.isEmpty(bean.getBaseRateVeh().get(id)==null?"":bean.getBaseRateVeh().get(id).toString())?"0":bean.getBaseRateVeh().get(id);
				obj[1] = bean.getApplicationNo();
				obj[2] = "0";
				obj[3] = bean.getBranchCode();
				obj[4] = bean.getRateVehicleId();
				/*obj[0] = StringUtils.isEmpty(bean.getBasePrem().get(i)==null?"":bean.getBasePrem().get(i).toString())?"0":bean.getBasePrem().get(i);
				obj[1] = StringUtils.isEmpty(bean.getBaseRate().get(i)==null?"":bean.getBaseRate().get(i).toString())?"0":bean.getBaseRate().get(i);
				obj[2] = bean.getApplicationNo();
				obj[3] = StringUtils.isEmpty(bean.getCoverId().get(i)==null?"":bean.getCoverId().get(i).toString())?"0":bean.getCoverId().get(i);
				obj[4] = bean.getBranchCode();
				obj[5] = bean.getRateVehicleId();*/
				removeNull(obj);
				LogManager.info("Query=>>"+sql);
				LogManager.info("Obj[]=>>"+StringUtils.join(obj,","));
				int res=this.mytemplate.update(sql,obj);              
				LogManager.info("Result=>>"+res);	
			//}
			
			updateCoveragesInfoIssuer(bean.getApplicationNo(),bean.getLoginBranch(),bean.getProductId(),bean.getEndTypeId(),bean.getLoginId(),"B",bean.getUserType(),bean.getRateVehicleId());
			result = "SUCCESS";
		} catch(Exception e) {
			e.printStackTrace();
			result="FAILED";
		}
		LogManager.popRemove();
		LogManager.info("updateCovRateVeh - Exit");
		return result;
	}
	
	public void updateCoveragesInfoIssuer(String applicationNo, String branchCode, String productId, String endtTypeId, String loginId, String type, String userType, String vehicleId) {
		try {
			String query = "";
			Object[] args = null;
			String endtStatus = "";
			endtStatus = "Referal";
			
			query = getQuery("CAL_MOTOR_PREMIUM_CALC");
			args = new Object[]{type, applicationNo, branchCode, productId, endtStatus, vehicleId};
			LogManager.info("updateCoveragesInfoIssuer Query==>" +queryFrammer(query, args));
			this.mytemplate.update(query, args);
			if("C".equals(type) && !"admin".equals(userType)) {
				if(vehCount(applicationNo)>0){
					String cur="";
					try {
						String curQry="SELECT DISTINCT CURRENCY_TYPE FROM MOTOR_DATA_DETAIL WHERE APPLICATION_NO=?";
						cur=this.mytemplate.queryForObject(curQry,new Object[]{applicationNo}, String.class).toString();
					} catch (Exception e) {
						e.printStackTrace();
					}
					if("USD".equalsIgnoreCase(cur)){
						query = getQuery("UPD_MOTOR_SIREFERRAL_DTLS_USD");
					}
					else{
						query = getQuery("UPD_MOTOR_SIREFERRAL_DTLS");
					}
					args = new Object[]{loginId,productId,applicationNo};
					LogManager.info("Query==>" + queryFrammer(query, args));
					this.mytemplate.update(query, args);
				}
			}
		}
		catch(Exception exception) {
			exception.printStackTrace();
		}
	}

	public List<Map<String, Object>> getConditionClausesList(MotorBean bean) {
		List<Map<String, Object>> list= new ArrayList<Map<String, Object>>();
		List<String> condition=new ArrayList<String>();
		String qry="";
		Object[] args = null;
		try {
			qry="SELECT CODE,CODE_DESC FROM MOTOR_CLAUSES_INFO WHERE QUOTE_NO=? AND CON_TYPE=? ORDER BY CODE";
			args = new Object[]{bean.getQuoteNo(),"1"};
			LogManager.info("Query==>" + qry);
			LogManager.info("Args==>" + StringUtils.join(args,", "));
			list=this.mytemplate.queryForList(qry, args);
			/*if(list==null || list.size()==0){
				qry="SELECT CONDITION_ID,COREAPPCODE CODE,CONDITION_DESC CODE_DESC FROM MOTOR_CONDITION_MASTER WHERE POLICY_TYPE_ID = ? AND CONDITION_TYPE = ? ORDER BY CONDITION_ID";
				args = new Object[]{bean.getPolicyType(),"CONDITION"};
				LogManager.info("Query==>" + qry);
				LogManager.info("Args==>" + StringUtils.join(args,", "));
				list=this.mytemplate.queryForList(qry, args);
			}*/
			/*if("list".equalsIgnoreCase(bean.getMode())){
				if(list!=null || list.size()>0){
					for(int i=0;i<list.size();i++){
						condition.add(list.get(i).get("CODE").toString());
					}
				}
				bean.setCondition(condition);
			}*/
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public int addConditionDetail(MotorBean bean) {
		int res=0;
		String qry="";
		Object[] args = null;
		try {
			qry="INSERT INTO MOTOR_CLAUSES_INFO (SNO,QUOTE_NO,CON_TYPE,CON_TYPE_DESC,CODE,CODE_DESC,ENTRY_DATE,STATUS,REMARKS,COREAPPCODE) VALUES ((SELECT NVL(MAX(SNO )+1,1) FROM MOTOR_CLAUSES_INFO),?,?,?,(SELECT NVL(MAX(CODE )+1,1) FROM MOTOR_CLAUSES_INFO WHERE CON_TYPE=? AND QUOTE_NO=?),?,SYSDATE,?,?,?)";
			args = new Object[]{bean.getQuoteNo(),"1","CONDITION","1",bean.getQuoteNo(),bean.getConditionDesc(),"Y","","99999"};
			LogManager.info("Query==>" + qry);
			LogManager.info("Args==>" + StringUtils.join(args,", "));
			res=this.mytemplate.update(qry, args);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public int addChooseConditionDetail(MotorBean bean) {
		int res=0;
		String qry="";
		Object[] args = null;
		String conDesc="";
		try {
			List<Map<String,Object>> list=bean.getConditionList();
			//if(bean.getCondition().size()>0 && list.size()>0){
				//qry="DELETE FROM MOTOR_CLAUSES_INFO WHERE QUOTE_NO=? AND ";
				//args = new Object[]{bean.getQuoteNo()};
				//this.mytemplate.update(qry,args);
			//}
			for(int i=0;i<bean.getCondition().size();i++){
				String id=bean.getCondition().get(i).toString();
				
				qry="DELETE FROM MOTOR_CLAUSES_INFO WHERE QUOTE_NO=? AND COREAPPCODE=?";
				args = new Object[]{bean.getQuoteNo(),id};
				this.mytemplate.update(qry,args);
				
				conDesc=list.stream().filter(a -> id.equalsIgnoreCase(a.get("CODE")==null?"":a.get("CODE").toString())).collect(Collectors.toList()).get(0).get("CODE_DESC").toString();
				qry="INSERT INTO MOTOR_CLAUSES_INFO (SNO,QUOTE_NO,CON_TYPE,CON_TYPE_DESC,CODE,CODE_DESC,ENTRY_DATE,STATUS,REMARKS,COREAPPCODE) VALUES ((SELECT NVL(MAX(SNO )+1,1) FROM MOTOR_CLAUSES_INFO),?,?,?,(SELECT NVL(MAX(CODE )+1,1) FROM MOTOR_CLAUSES_INFO WHERE CON_TYPE=? AND QUOTE_NO=?),?,SYSDATE,?,?,?)";
				args = new Object[]{bean.getQuoteNo(),"1","CONDITION","1",bean.getQuoteNo(),conDesc,"Y","",id};
				LogManager.info("Query==>" + qry);
				LogManager.info("Args==>" + StringUtils.join(args,", "));
				res=this.mytemplate.update(qry, args);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public List<Map<String, Object>> getConditionClausesListRemain(MotorBean bean) {
		List<Map<String, Object>> list= new ArrayList<Map<String, Object>>();
		List<String> condition=new ArrayList<String>();
		String qry="";
		Object[] args = null;
		try {
			
			qry=" SELECT SNO CODE, CONDITION_ID, COREAPPCODE, CONDITION_DESC CODE_DESC FROM MOTOR_CONDITION_MASTER WHERE POLICY_TYPE_ID = ? AND CONDITION_TYPE = ? AND SNO NOT IN (SELECT COREAPPCODE FROM MOTOR_CLAUSES_INFO WHERE QUOTE_NO=? AND COREAPPCODE IS NOT NULL) AND STATUS='Y' ORDER BY SNO";
			args = new Object[]{bean.getPolicyType(),"CONDITION",bean.getQuoteNo()};
			LogManager.info("Query==>" + qry);
			LogManager.info("Args==>" + StringUtils.join(args,", "));
			list=this.mytemplate.queryForList(qry, args);
			
			/*if("list".equalsIgnoreCase(bean.getMode())){
				if(list!=null || list.size()>0){
					for(int i=0;i<list.size();i++){
						condition.add(list.get(i).get("CODE").toString());
					}
				}
				bean.setCondition(condition);
			}*/
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public int insertConditionDetail(MotorBean bean) {
		int res=0;
		String qry="";
		Object[] args = null;
		String conDesc="";
		String coreCode="";
		try {
			//List<Map<String,Object>> list=bean.getConditionList();
			//if(bean.getCondition().size()>0 && list.size()>0){
				qry="DELETE FROM MOTOR_CLAUSES_INFO WHERE QUOTE_NO=? AND CON_TYPE=?";
				args = new Object[]{bean.getQuoteNo(),"1"};
				this.mytemplate.update(qry,args);
			//}
			for(int i=0;i<bean.getCondition().size();i++){
				String id=bean.getCondition().get(i).toString();
				int val=Integer.parseInt(id);
				//conDesc=list.stream().filter(a -> id.equalsIgnoreCase(a.get("CODE")==null?"":a.get("CODE").toString())).collect(Collectors.toList()).get(0).get("CODE_DESC").toString();
				conDesc=bean.getConditionText().get(val)==null?"":bean.getConditionText().get(val).toString();
				coreCode=bean.getConditionEditList().get(val).get("COREAPPCODE")==null?"":bean.getConditionEditList().get(val).get("COREAPPCODE").toString();
				qry="INSERT INTO MOTOR_CLAUSES_INFO (SNO,QUOTE_NO,CON_TYPE,CON_TYPE_DESC,CODE,CODE_DESC,ENTRY_DATE,STATUS,REMARKS,COREAPPCODE) VALUES ((SELECT NVL(MAX(SNO )+1,1) FROM MOTOR_CLAUSES_INFO),?,?,?,(SELECT NVL(MAX(CODE )+1,1) FROM MOTOR_CLAUSES_INFO WHERE CON_TYPE=? AND QUOTE_NO=?),?,SYSDATE,?,?,?)";
				args = new Object[]{bean.getQuoteNo(),"1","CONDITION","1",bean.getQuoteNo(),conDesc,"Y","",coreCode};
				LogManager.info("Query==>" + qry);
				LogManager.info("Args==>" + StringUtils.join(args,", "));
				res=this.mytemplate.update(qry, args);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public List<Map<String, Object>> getConditionClausesEdit(MotorBean bean) {
		List<Map<String, Object>> list= new ArrayList<Map<String, Object>>();
		List<String> condition=new ArrayList<String>();
		String qry="";
		Object[] args = null;
		try {
			qry="SELECT CODE,CODE_DESC,COREAPPCODE FROM MOTOR_CLAUSES_INFO WHERE QUOTE_NO=? AND CON_TYPE=? ORDER BY CODE";
			args = new Object[]{bean.getQuoteNo(),"1"};
			LogManager.info("Query==>" + qry);
			LogManager.info("Args==>" + StringUtils.join(args,", "));
			list=this.mytemplate.queryForList(qry, args);
			
			/*if("list".equalsIgnoreCase(bean.getMode())){
				if(list!=null || list.size()>0){
					for(int i=0;i<list.size();i++){
						condition.add(list.get(i).get("CODE").toString());
					}
				}
				bean.setCondition(condition);
			}*/
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public List<Map<String, Object>> getDeductibleClausesListRemain(MotorBean bean) {
		List<Map<String, Object>> list= new ArrayList<Map<String, Object>>();
		List<String> deductible=new ArrayList<String>();
		String qry="";
		Object[] args = null;
		try {
			
			qry=" SELECT SNO CODE, CONDITION_ID, COREAPPCODE, CONDITION_DESC CODE_DESC FROM MOTOR_CONDITION_MASTER WHERE POLICY_TYPE_ID = ? AND CONDITION_TYPE = ? AND SNO NOT IN (SELECT COREAPPCODE FROM MOTOR_CLAUSES_INFO WHERE QUOTE_NO=? AND COREAPPCODE IS NOT NULL) AND STATUS='Y' ORDER BY SNO";
			args = new Object[]{bean.getPolicyType(),"FIRSTAMOUNTPAYABLE",bean.getQuoteNo()};
			LogManager.info("Query==>" + qry);
			LogManager.info("Args==>" + StringUtils.join(args,", "));
			list=this.mytemplate.queryForList(qry, args);
			
			/*if("list".equalsIgnoreCase(bean.getMode())){
				if(list!=null || list.size()>0){
					for(int i=0;i<list.size();i++){
						deductible.add(list.get(i).get("CODE").toString());
					}
				}
				bean.setDeductible(deductible);
			}*/
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public List<Map<String, Object>> getDeductibleClausesEdit(MotorBean bean) {
		List<Map<String, Object>> list= new ArrayList<Map<String, Object>>();
		List<String> deductible=new ArrayList<String>();
		String qry="";
		Object[] args = null;
		try {
			qry="SELECT CODE,CODE_DESC,COREAPPCODE FROM MOTOR_CLAUSES_INFO WHERE QUOTE_NO=? AND CON_TYPE=? ORDER BY CODE";
			args = new Object[]{bean.getQuoteNo(),"2"};
			LogManager.info("Query==>" + qry);
			LogManager.info("Args==>" + StringUtils.join(args,", "));
			list=this.mytemplate.queryForList(qry, args);
			
			/*if("list".equalsIgnoreCase(bean.getMode())){
				if(list!=null || list.size()>0){
					for(int i=0;i<list.size();i++){
						deductible.add(list.get(i).get("CODE").toString());
					}
				}
				bean.setDeductible(deductible);
			}*/
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public int addDeductibleDetail(MotorBean bean) {
		int res=0;
		String qry="";
		Object[] args = null;
		try {
			qry="INSERT INTO MOTOR_CLAUSES_INFO (SNO,QUOTE_NO,CON_TYPE,CON_TYPE_DESC,CODE,CODE_DESC,ENTRY_DATE,STATUS,REMARKS,COREAPPCODE) VALUES ((SELECT NVL(MAX(SNO )+1,1) FROM MOTOR_CLAUSES_INFO),?,?,?,(SELECT NVL(MAX(CODE )+1,1) FROM MOTOR_CLAUSES_INFO WHERE CON_TYPE=? AND QUOTE_NO=?),?,SYSDATE,?,?,?)";
			args = new Object[]{bean.getQuoteNo(),"2","FIRSTAMOUNTPAYABLE","2",bean.getQuoteNo(),bean.getDeductibleDesc(),"Y","","99999"};
			LogManager.info("Query==>" + qry);
			LogManager.info("Args==>" + StringUtils.join(args,", "));
			res=this.mytemplate.update(qry, args);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public int addChooseDeductibleDetail(MotorBean bean) {
		int res=0;
		String qry="";
		Object[] args = null;
		String conDesc="";
		try {
			List<Map<String,Object>> list=bean.getDeductibleAddList();
			
			for(int i=0;i<bean.getDeductible().size();i++){
				String id=bean.getDeductible().get(i).toString();
				
				qry="DELETE FROM MOTOR_CLAUSES_INFO WHERE QUOTE_NO=? AND COREAPPCODE=?";
				args = new Object[]{bean.getQuoteNo(),id};
				this.mytemplate.update(qry,args);
				
				conDesc=list.stream().filter(a -> id.equalsIgnoreCase(a.get("CODE")==null?"":a.get("CODE").toString())).collect(Collectors.toList()).get(0).get("CODE_DESC").toString();
				qry="INSERT INTO MOTOR_CLAUSES_INFO (SNO,QUOTE_NO,CON_TYPE,CON_TYPE_DESC,CODE,CODE_DESC,ENTRY_DATE,STATUS,REMARKS,COREAPPCODE) VALUES ((SELECT NVL(MAX(SNO )+1,1) FROM MOTOR_CLAUSES_INFO),?,?,?,(SELECT NVL(MAX(CODE )+1,1) FROM MOTOR_CLAUSES_INFO WHERE CON_TYPE=? AND QUOTE_NO=?),?,SYSDATE,?,?,?)";
				args = new Object[]{bean.getQuoteNo(),"2","FIRSTAMOUNTPAYABLE","2",bean.getQuoteNo(),conDesc,"Y","",id};
				LogManager.info("Query==>" + qry);
				LogManager.info("Args==>" + StringUtils.join(args,", "));
				res=this.mytemplate.update(qry, args);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	
	}

	public int insertDeductibleDetail(MotorBean bean) {
		int res=0;
		String qry="";
		Object[] args = null;
		String conDesc="";
		String coreCode="";
		try {
			qry="DELETE FROM MOTOR_CLAUSES_INFO WHERE QUOTE_NO=? AND CON_TYPE=?";
			args = new Object[]{bean.getQuoteNo(),"2"};
			this.mytemplate.update(qry,args);
			for(int i=0;i<bean.getDeductible().size();i++){
				String id=bean.getDeductible().get(i).toString();
				int val=Integer.parseInt(id);
				//conDesc=list.stream().filter(a -> id.equalsIgnoreCase(a.get("CODE")==null?"":a.get("CODE").toString())).collect(Collectors.toList()).get(0).get("CODE_DESC").toString();
				conDesc=bean.getDeductibleText().get(val)==null?"":bean.getDeductibleText().get(val).toString();
				coreCode=bean.getDeductibleEditList().get(val).get("COREAPPCODE")==null?"":bean.getDeductibleEditList().get(val).get("COREAPPCODE").toString();
				qry="INSERT INTO MOTOR_CLAUSES_INFO (SNO,QUOTE_NO,CON_TYPE,CON_TYPE_DESC,CODE,CODE_DESC,ENTRY_DATE,STATUS,REMARKS,COREAPPCODE) VALUES ((SELECT NVL(MAX(SNO )+1,1) FROM MOTOR_CLAUSES_INFO),?,?,?,(SELECT NVL(MAX(CODE )+1,1) FROM MOTOR_CLAUSES_INFO WHERE CON_TYPE=? AND QUOTE_NO=?),?,SYSDATE,?,?,?)";
				args = new Object[]{bean.getQuoteNo(),"2","FIRSTAMOUNTPAYABLE","2",bean.getQuoteNo(),conDesc,"Y","",coreCode};
				LogManager.info("Query==>" + qry);
				LogManager.info("Args==>" + StringUtils.join(args,", "));
				res=this.mytemplate.update(qry, args);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	
	}

	public List<Map<String, Object>> getDeductibleClausesList(MotorBean bean) {
		List<Map<String, Object>> list= new ArrayList<Map<String, Object>>();
		List<String> deductible=new ArrayList<String>();
		String qry="";
		Object[] args = null;
		try {
			qry="SELECT CODE,CODE_DESC FROM MOTOR_CLAUSES_INFO WHERE QUOTE_NO=? AND CON_TYPE=? ORDER BY CODE";
			args = new Object[]{bean.getQuoteNo(),"2"};
			LogManager.info("Query==>" + qry);
			LogManager.info("Args==>" + StringUtils.join(args,", "));
			list=this.mytemplate.queryForList(qry, args);
			
			/*if("list".equalsIgnoreCase(bean.getMode())){
				if(list!=null || list.size()>0){
					for(int i=0;i<list.size();i++){
						deductible.add(list.get(i).get("CODE").toString());
					}
				}
				bean.setDeductible(deductible);
			}*/
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public void getCustomerId(MotorBean bean) {
			try {
				String sql=getQuery("GET_MOTOR_DATA_DTLS");
				Object obj[] = new Object[]{bean.getApplicationNo(),bean.getProductId()};
				LogManager.info("Query=>"+sql);
				LogManager.info("Obj[]=>"+StringUtils.join(obj,","));
				Map<String,Object> map=this.mytemplate.queryForMap(sql,obj);
				bean.setCustomerId(map.get("CUSTOMER_ID")==null?"":map.get("CUSTOMER_ID").toString());
			} catch (Exception e) {
				e.printStackTrace();
			}
	}

	public int updateCustomerType(MotorBean bean) {
		int res=0;
		try {
			String sql="SELECT COUNT(*) FROM MOTOR_DATA_DETAIL WHERE APPLICATION_NO=?";
			Object obj[] = new Object[]{bean.getApplicationNo()};
			LogManager.info("updateCustomerType Count Query=> "+queryFrammer(sql, obj));
			int count=this.mytemplate.queryForInt(sql,obj);
			LogManager.info("updateCustomerType Count=> "+count);
			if(count>0){
				String query="UPDATE MOTOR_DATA_DETAIL SET CUSTOMER_TYPE=? WHERE APPLICATION_NO=?";
				Object arg[] = new Object[]{bean.getCustomerType(),bean.getApplicationNo()};
				LogManager.info("updateCustomerType Update Query=> "+queryFrammer(query, arg));
				res=this.mytemplate.update(query, arg);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public boolean checkCustomerType(MotorBean bean) {
		String personalCustType="";
		String motorCustType="";
		boolean res=false;
		try {
			String sql="SELECT CUSTOMER_ID,CUSTOMER_TYPE FROM PERSONAL_INFO WHERE CUSTOMER_ID=(SELECT CUSTOMER_ID FROM HOME_POSITION_MASTER WHERE APPLICATION_NO=?)";
			Object obj[] = new Object[]{bean.getApplicationNo()};
			//personalCustType=String.valueOf(this.mytemplate.queryForObject(sql, obj ,String.class));
			List<Map<String,Object>> personalInfo=this.mytemplate.queryForList(sql,obj);
			if(personalInfo!=null && personalInfo.size()>0){
				bean.setCustomerId(personalInfo.get(0).get("CUSTOMER_ID")==null?"":personalInfo.get(0).get("CUSTOMER_ID").toString());
				personalCustType=personalInfo.get(0).get("CUSTOMER_TYPE")==null?"":personalInfo.get(0).get("CUSTOMER_TYPE").toString();
			}
			String query="SELECT CUSTOMER_TYPE FROM MOTOR_DATA_DETAIL WHERE APPLICATION_NO=?";
			List<Map<String,Object>> custType=this.mytemplate.queryForList(query,obj);
			if(custType!=null && custType.size()>0){
				for(int i=0;i<custType.size();i++){
					motorCustType=custType.get(i).get("CUSTOMER_TYPE")==null?"":custType.get(i).get("CUSTOMER_TYPE").toString();
					res=personalCustType.equalsIgnoreCase(motorCustType)?true:false;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public void premiumInfoNew(MotorBean bean) {
		LogManager.info("premiumInfoNew - Enter ");
		String result="";
		String sql="";
		Object[] obj=null;
		List<Map<String,Object>> premiumInfo=new ArrayList<Map<String,Object>>();
		List<Map<String,Object>> vehicleIdList=new ArrayList<Map<String,Object>>();
			try {
				sql=this.getQuery("GET_MOTOR_PREMIUM_INFO_NEW");
				obj=new Object[]{bean.getApplicationNo()};
				LogManager.info("premiumInfoNew Sql=>=>"+queryFrammer(sql, obj));
				premiumInfo=this.mytemplate.queryForList(sql,obj);
				bean.setPremiumInfoNew(premiumInfo);
				
				sql=this.getQuery("GET_MOTOR_MULTI_DATA_DETAILS");
				obj=new Object[]{bean.getApplicationNo(),bean.getProductId(),bean.getLoginBranch()};
				LogManager.info("premiumInfoNew vehicleDtlList Sql=>=>"+queryFrammer(sql, obj));
				vehicleIdList=this.mytemplate.queryForList(sql,obj);
				bean.setVehicleDetailsList(vehicleIdList);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
	}
	
	public void getQuoteInfo(MotorBean bean) {
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
		LogManager.info("getQuoteInfo - Exit");
	
}

	public String getCustomerType(MotorBean bean) {
		String personalCustType="";
		String motorCustType="";
		boolean res=false;
		try {
			String sql="SELECT CUSTOMER_TYPE FROM PERSONAL_INFO WHERE CUSTOMER_ID=?";
			Object obj[] = new Object[]{bean.getCustomerId()};
			//personalCustType=String.valueOf(this.mytemplate.queryForObject(sql, obj ,String.class));
			List<Map<String,Object>> personalInfo=this.mytemplate.queryForList(sql,obj);
			if(personalInfo!=null && personalInfo.size()>0){
				personalCustType=personalInfo.get(0).get("CUSTOMER_TYPE")==null?"":personalInfo.get(0).get("CUSTOMER_TYPE").toString();
			}
			if(!"INDIVIDUAL".equalsIgnoreCase(personalCustType)) {
				String qry="UPDATE MOTOR_DATA_DETAIL SET DRIVER_DOB=TO_DATE(sysdate-1,'DD/MM/YYYY') WHERE APPLICATION_NO=? AND VEHICLE_ID=?";
				Object arg[]=new Object[] {bean.getApplicationNo(),bean.getDeleteVehicleId()};
				this.mytemplate.update(qry,arg);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return personalCustType;
	}
	
	public List<Map<String,Object>> getDriverDetails(MotorBean bean) {
		List<String> driverIdList = new ArrayList<String>();
		List<String> driverDOBList = new ArrayList<String>();
		List<String> claimYNIdList = new ArrayList<String>();
		List<String> prevExpiryDateList = new ArrayList<String>();
		List<String> noClaimBonusIdList = new ArrayList<String>();
		List<String> claimAmountList = new ArrayList<String>();
		List<String> prevInsCompanyIdList = new ArrayList<String>();
		List<String> ownerDriverYNList = new ArrayList<String>();
		List<String> prevPolicyNoList = new ArrayList<String>();
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		
		try {
			Map<String,Object> resMap = motorApi.getDriverDetails(bean);
			if(resMap!=null) {
				driverIdList.add(resMap.get("DriverId")==null?"":resMap.get("DriverId").toString());
				driverDOBList.add(resMap.get("DriverDob")==null?"":resMap.get("DriverDob").toString());
				claimYNIdList.add(resMap.get("Claimyn")==null?"":resMap.get("Claimyn").toString());
				prevExpiryDateList.add(resMap.get("PrePolicyExpDate")==null?"":resMap.get("PrePolicyExpDate").toString());
				noClaimBonusIdList.add(resMap.get("NoOfClaimBonus")==null?"":resMap.get("NoOfClaimBonus").toString());
				claimAmountList.add(resMap.get("ClaimAmt")==null?"":resMap.get("ClaimAmt").toString());
				prevInsCompanyIdList.add(resMap.get("InsCompany")==null?"":resMap.get("InsCompany").toString());
				ownerDriverYNList.add(resMap.get("OwnnerdriverYn")==null?"":resMap.get("OwnnerdriverYn").toString());
				prevPolicyNoList.add(resMap.get("PolicyNo")==null?"":resMap.get("PolicyNo").toString());
				bean.setIsClaimDtl(resMap.get("IsClaimDtl")==null?"":resMap.get("IsClaimDtl").toString());
			}
			bean.setDriverIdList(driverIdList);
			bean.setDriverDOBList(driverDOBList);
			bean.setClaimYNIdList(claimYNIdList);
			bean.setPrevExpiryDateList(prevExpiryDateList);
			bean.setNoClaimBonusIdList(noClaimBonusIdList);
			bean.setClaimAmountList(claimAmountList);
			bean.setPrevInsCompanyIdList(prevInsCompanyIdList);
			bean.setOwnerDriverYNList(ownerDriverYNList);
			bean.setPrevPolicyNoList(prevPolicyNoList);
			list.add(resMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public List<Map<String, Object>> getVehicleDetailsByIdNew(MotorBean bean) {
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		
		try {
			Map<String,Object> resMap = motorApi.getVehicleDetailsByIdNew(bean);
			if(resMap!=null) {
				list.add(resMap);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}

	public String getBuypolicy(MotorBean bean) {
		String result = null;
		try {
			result = motorApi.getBuypolicy(bean);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public void validatePolicy(MotorBean bean) {
		
		try {
			motorApi.validatePolicy(bean);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public List<Map<String, Object>> getPaymentList(MotorBean bean) {
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		
		try {
			list = motorApi.getPaymentList(bean);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public List<Map<String, Object>> makepay(MotorBean bean) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		List<String> sumInsuredList = new ArrayList<String>();
		
		try {
			Map<String,Object> resMap = motorApi.makepay(bean);
			if(resMap!=null) {
				bean.setPolicyNo(resMap.get("PolicyNo")==null?"":resMap.get("PolicyNo").toString());
				bean.setPremium(resMap.get("Premium")==null?"":resMap.get("Premium").toString());
				bean.setDebitNo(resMap.get("DebitNo")==null?"":resMap.get("DebitNo").toString());
				bean.setReceiptNo(resMap.get("ReceiptNo")==null?"":resMap.get("ReceiptNo").toString());
				
				List<Map<String,Object>> documentinfo = (List<Map<String,Object>>)resMap.get("DocumentInfo");
				if(documentinfo!=null) {
					bean.setDocumentinfo(documentinfo);
				}
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}


	
}
