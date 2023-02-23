package com.maan.Motor.Claim;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.maan.adminnew.report.motor.MotorReportBean;
import com.maan.common.LogManager;
import com.maan.common.MyJdbcTemplate;

public class ClaimDao extends MyJdbcTemplate {

	public void getClaimDetails(ClaimBean bean, String productId, String branchCode) {
		try{
			LogManager.info("Enter Into getClaimDetails()");
			String query=getQuery("GET_MOTOR_CLAIM_DETAILS");
			Map<Object, Object> value=null;
			Object[] args={bean.getApplicationNo(),productId,branchCode};
			LogManager.info("Query => "+query);
			LogManager.info("Arguments => "+StringUtils.join(args, ","));
			value=this.mytemplate.queryForMap(query,args);
			String title = value.get("TITLE")==null?"":value.get("TITLE").toString();
			String name = value.get("NAME")==null?"":value.get("NAME").toString();
			String customerName = title +". "+ name;
			bean.setCustomerName(customerName);
			bean.setPolicyStartDate(value.get("INCEPTION_DATE")==null?"":value.get("INCEPTION_DATE").toString());
			bean.setQuoteNo(value.get("QUOTE_NO")==null?"":value.get("QUOTE_NO").toString());
			//bean.setQuoteDate(value.get("")==null?"":value.get("").toString());
			bean.setProduct(value.get("PRODUCT_NAME")==null?"":value.get("PRODUCT_NAME").toString());
			bean.setPremium(value.get("PREMIUM")==null?"":value.get("PREMIUM").toString());
			bean.setCoreAppCode(value.get("COREAPP_CODE")==null?"":value.get("COREAPP_CODE").toString());
			String address1 = value.get("ADDRESS1")==null?"":value.get("ADDRESS1").toString();
			String address2 = value.get("ADDRESS2")==null?" ":value.get("ADDRESS2").toString();
			String address = address1 +address2; 
			bean.setAddress1(address);
			bean.setCity(value.get("EMIRATE")==null?"":value.get("EMIRATE").toString());
			bean.setPoBox(value.get("POBOX")==null?"":value.get("POBOX").toString());
			bean.setMobileNo(value.get("MOBILE")==null?"":value.get("MOBILE").toString());
			bean.setEmail(value.get("EMAIL")==null?"":value.get("EMAIL").toString());
			bean.setMakeName(value.get("MAKE_NAME")==null?"":value.get("MAKE_NAME").toString());
			bean.setModelName(value.get("MODEL_NAME")==null?"":value.get("MODEL_NAME").toString());
			bean.setTypeBodyName(value.get("TYPE_OF_BODY_NAME")==null?"":value.get("TYPE_OF_BODY_NAME").toString());
			/*bean.setMfgYr(value.get("MANUFACTURE_YEAR")==null?"":value.get("MANUFACTURE_YEAR").toString());
			bean.setSumInsured(value.get("SUMINSURED_VALUE_LOCAL")==null?"":value.get("SUMINSURED_VALUE_LOCAL").toString());
			bean.setCubicCapacity(value.get("CUBIC_CAPACITY")==null?"":value.get("CUBIC_CAPACITY").toString());
			bean.setSeatingCapacity(value.get("SEATING_CAPACITY")==null?"":value.get("SEATING_CAPACITY").toString());
			bean.setNoOfCylinder(value.get("NO_OF_CYLINDER")==null?"":value.get("NO_OF_CYLINDER").toString());
			bean.setVehicleUsageName(value.get("VEHICLETYPE_DESC_ENGLISH")==null?"":value.get("VEHICLETYPE_DESC_ENGLISH").toString());
			bean.setAreaCoverage(value.get("AREA_COVERAGE")==null?"":value.get("AREA_COVERAGE").toString());
			bean.setDriverDOB(value.get("DRIVER_DOB")==null?"":value.get("DRIVER_DOB").toString());
			bean.setDriverNationality(value.get("DRIVER_NATIONALITY")==null?"":value.get("DRIVER_NATIONALITY").toString());
			bean.setUaeLicenceNo(value.get("DRIVER_UAE_LICENCE")==null?"":value.get("DRIVER_UAE_LICENCE").toString());
			bean.setUaeLicExpDT(value.get("DRIVER_UAE_LIC_EXP_DT")==null?"":value.get("DRIVER_UAE_LIC_EXP_DT").toString());
			bean.setClaimAmount(value.get("CLAIM_AMOUNT")==null?"":value.get("CLAIM_AMOUNT").toString());*/
			bean.setAgencyRepairName(value.get("AGENCY_REPAIR")==null?"":value.get("AGENCY_REPAIR").toString());
			/*bean.setRegNo(value.get("REGISTRATION_NO")==null?"":value.get("REGISTRATION_NO").toString());
			bean.setChassisNo(value.get("CHASSIS_NO")==null?"":value.get("CHASSIS_NO").toString());
			bean.setEngineNo(value.get("ENGINE_NUMBER")==null?"":value.get("ENGINE_NUMBER").toString());
			bean.setVehicleColour(value.get("VEHICLE_COLOR")==null?"":value.get("VEHICLE_COLOR").toString());
			bean.setVehicleRegLoc(value.get("REGISTER_LOCATION")==null?"":value.get("REGISTER_LOCATION").toString());
			bean.setPlateCharacter(value.get("PLATE_COLOR")==null?"":value.get("PLATE_COLOR").toString());
			bean.setLeasedYN(value.get("LEASED")==null?"":value.get("LEASED").toString());*/
			//bean.setBankOfFinance(value.get("")==null?"":value.get("").toString());
			bean.setTotalPremium(value.get("TOTAL_PREMIUM")==null?"":value.get("TOTAL_PREMIUM").toString());
			Date date= new Date();
			SimpleDateFormat sfd=new SimpleDateFormat("dd/MM/yyyy");
			String intimationDate = sfd.format(date);
			bean.setClaimIntimationDate(intimationDate);			
			
			LogManager.info("Exit Into getClaimDetails()");
		}catch (Exception e) {
			LogManager.debug("Exception occured @ getClaimDetails" +e);
		}
	}
	public String saveClaimIntimationDetails(ClaimBean bean) {
		String status="";
		try{
			LogManager.info("Enter Into saveClaimIntimationDetails()");
			String query = getQuery("MOTOR_CLAIM_SEQ");
			Map<Object,Object> seq = this.mytemplate.queryForMap(query);
			String motorClaimSeq = seq.get("CLAIM_SEQ")==null?"":seq.get("CLAIM_SEQ").toString();
			query=getQuery("INS_MOTOR_CLAIM_INTIMATION");
			Object[] args={motorClaimSeq,bean.getProductId(),bean.getPolicyNo(),bean.getLossDate(),bean.getLossDescription(),bean.getClaimIntimationDate(),"Pending",bean.getLoginId()};
			LogManager.info("Query => "+query);
			LogManager.info("Arguments => "+StringUtils.join(args, ","));
			status = Integer.toString(this.mytemplate.update(query,args));
			bean.setClaimNo(motorClaimSeq);
			LogManager.info("Exit Into saveClaimIntimationDetails()");
		}catch (Exception e) {
			LogManager.debug("Exception occured @ saveClaimIntimationDetails" +e);
		}
		return status;
	}
	public int getClaimStatus(String policyNo) {
		int count=0;
		try{
			LogManager.info("Enter Into getClaimStatus()");
			String query=getQuery("MOTOR_CLAIM_STATUS");
			Object[] args={policyNo};
			LogManager.info("Query => "+query);
			LogManager.info("Arguments => "+StringUtils.join(args, ","));
			count = this.mytemplate.update(query,args);
			LogManager.info("Exit Into getClaimStatus()");
		}catch (Exception e) {
			LogManager.debug("Exception occured @ getClaimStatus" +e);
		}
		return count;
	}
	public List<Map<Object, Object>> getClaimAdminDetail(ClaimBean bean) {
		List<Map<Object,Object>> result = null;
		try{
			LogManager.info("Enter into getClaimAdminDetail()");
			String query = getQuery("GET_MOTOR_ADMIN_CLAIM");
			LogManager.info("Query =>"+query);
			
			result = this.mytemplate.queryForList(query);
			
			LogManager.info("Exit Into getClaimAdminDetail()");
		}catch (Exception e) {
			LogManager.debug("Exception occured @ getClaimAdminDetail{"+e+"}");
		}
		return result;
	}
	public String getApplicationNo(String policyNo) {
		String applicationNo="";
		try{
			LogManager.info("Enter Into getClaimDetails()");
			String query=getQuery("GET_APPLICATION_NO");
			Map<Object, Object> value=null;
			Object[] args={policyNo};
			LogManager.info("Query => "+query);
			LogManager.info("Arguments => "+StringUtils.join(args, ","));
			value=this.mytemplate.queryForMap(query,args);
			applicationNo = value.get("APPLICATION_NO")==null?"":value.get("APPLICATION_NO").toString().trim();
		}catch (Exception e) {
			LogManager.debug("Exception occured @ getClaimAdminDetail{"+e+"}");
		}
		return applicationNo;
	}
	
	public void getClaimCloseDetails(ClaimBean bean) {
		try{
			LogManager.info("Enter Into getClaimCloseDetails()");
			String query=getQuery("GET_MOTOR_CLAIM_CLOSE_DETAILS");
			Map<Object, Object> value=null;
			Object[] args={bean.getClaimNo()};
			LogManager.info("Query => "+query);
			LogManager.info("Arguments => "+StringUtils.join(args, ","));
			value=this.mytemplate.queryForMap(query,args);
			bean.setLossDate(value.get("LOSS_DATE")==null?"":value.get("LOSS_DATE").toString());
			bean.setLossDescription(value.get("LOSS_DESCRIPTION")==null?"":value.get("LOSS_DESCRIPTION").toString());
			bean.setClaimIntimationDate(value.get("INTIMATION_DATE")==null?"":value.get("INTIMATION_DATE").toString());
			LogManager.info("Exit Into getClaimCloseDetails()");
		}catch (Exception e) {
			LogManager.debug("Exception occured @ getClaimCloseDetails{"+e+"}");
		}
	}
	public void updateCloseDetails(ClaimBean bean) {
		try{
			LogManager.info("Enter Into updateCloseDetails()");
			String query = getQuery("UPD_MOTOR_CLAIM_CLOSE_DETAIL");
			Object[] args ={bean.getRemarks(),bean.getLoginId(),bean.getClaimNo()};
			LogManager.info("Query ==> "+query);
			LogManager.info("Arguments ==>" +StringUtils.join(args,","));
			this.mytemplate.update(query,args);
			LogManager.info("Exit Into updateCloseDetails()");
		}catch (Exception e) {
			LogManager.debug("Exception occured @ updateCloseDetails{"+e+"}");
		}
	}
	
	public List<Map<String, Object>> getIntimateDistinctVehicleList(ClaimBean bean){
		LogManager.info("Enter into getIntimateDistinctVehicleList()");
		List<Map<String, Object>> result = null;
		String query = null;
		Object[] args = null;
		try{
			if( "65".equalsIgnoreCase(bean.getProductId())){
				query = getQuery("GET_LIST_FOR_INTIMATE_DISTINCT_VEHICLE");
			}
			if( "30".equalsIgnoreCase(bean.getProductId())){
				query = getQuery("GET_LIST_FOR_INTIMATE_DISTINCT_VEHICLE_HOME");
			}
			LogManager.info("query =>" +query);
			if( "65".equalsIgnoreCase(bean.getProductId())){
				args = new Object []{bean.getPolicyNo(), bean.getVehicleId()};
			}
			if( "30".equalsIgnoreCase(bean.getProductId())){
				args = new Object []{bean.getPolicyNo()};
			}
			removeNull(args);
			LogManager.info("Argument =>"+StringUtils.join(args,","));
			result = this.mytemplate.queryForList(query,args);
			LogManager.info("Exit From getIntimateDistinctVehicleList()");
			}catch(Exception e){
				LogManager.error("Exception Occured @ getIntimateDistinctVehicleList()" +e);
				e.printStackTrace();
			}
			return result;
	}
	
	public List<Map<String, Object>> getIntimateSameVehicleList(ClaimBean bean){
		LogManager.info("Enter into getIntimateSameVehicleList()");
		List<Map<String, Object>> result = null;
		String query = null;
		Object[] args = null;
		try{
			if( "65".equalsIgnoreCase(bean.getProductId())){
				 query = getQuery("GET_LIST_FOR_INTIMATE_SAME_VEHICLE");
			}
			if( "30".equalsIgnoreCase(bean.getProductId())){
				 query = getQuery("GET_LIST_FOR_INTIMATE_SAME_VEHICLE_HOME");
			}
			LogManager.info("query =>" +query);
			if( "65".equalsIgnoreCase(bean.getProductId())){
				args = new Object []{bean.getPolicyNo(), bean.getVehicleId()};
			}
			if( "30".equalsIgnoreCase(bean.getProductId())){
				args = new Object []{bean.getPolicyNo()};
			}
			removeNull(args);
			LogManager.info("Argument =>"+StringUtils.join(args,","));
			result = this.mytemplate.queryForList(query,args);
			LogManager.info("Exit From getIntimateSameVehicleList()");
			}catch(Exception e){
				LogManager.error("Exception Occured @ getIntimateSameVehicleList()" +e);
				e.printStackTrace();
			}
			return result;
	}
	
	public List<Map<String, Object>> getIntimateSameTpaVehicleList(ClaimBean bean){
		LogManager.info("Enter into getIntimateSameTpaVehicleList()");
		List<Map<String, Object>> result = null;
		try{
			String query = getQuery("GET_LIST_FOR_INTIMATE_SAME_TPA_VEHICLE");
			LogManager.info("query =>" +query);
			Object[] args = new Object []{bean.getPolicyNo(), bean.getVehicleId()};
			removeNull(args);
			LogManager.info("Argument =>"+StringUtils.join(args,","));
			result = this.mytemplate.queryForList(query,args);
			LogManager.info("Exit From getIntimateSameTpaVehicleList()");
			}catch(Exception e){
				LogManager.error("Exception Occured @ getIntimateSameTpaVehicleList()" +e);
				e.printStackTrace();
			}
			return result;
	}
	
	public List<Map<String, Object>> getIntimatePolicy(ClaimBean bean){
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
	
	public List<Map<String, Object>> getIntimateStatus(ClaimBean bean){
		LogManager.info("Enter into getIntimateStatus()");
		List<Map<String, Object>> result = null;
		String query = null;
		try {
				query = getQuery("GET_LIST_FOR_CLAIM_HISTORY");
			LogManager.info("query =>" +query);
			Object[] args = new Object []{bean.getClaimNo()};
			removeNull(args);
			LogManager.info("Argument =>"+StringUtils.join(args,","));
			result = this.mytemplate.queryForList(query,args);
			LogManager.info("Exit From getIntimateStatus()");
		} catch(Exception e) {
			LogManager.error("Exception Occured @ getIntimateStatus()" +e);
			e.printStackTrace();
		}
		return result;
	}
	
	public List<Map<String, Object>> getIntimateVehicle(ClaimBean bean){
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
	
	public void getIntimateInsert(ClaimBean bean){
		LogManager.info("Enter into getIntimateInsert()");
		String query = null;
		Object args[] = null;
		String sequence = null;
		try{
			if( "65".equalsIgnoreCase(bean.getProductId())){
				if("Insert".equalsIgnoreCase(bean.getMode())){
					query = getQuery("GET_INTIMATE_FOR_INSERT");
					 sequence = (String)this.mytemplate.queryForObject("SELECT CLAIM_INTIMATION_SEQ.NEXTVAL FROM DUAL", String.class);
					 bean.setClaimNo(sequence);
					}
					if("Update".equalsIgnoreCase(bean.getMode())){
							query = getQuery("GET_INTIMATE_FOR_UPDATE");
							}
			}
			if( "30".equalsIgnoreCase(bean.getProductId())){
				if("Insert".equalsIgnoreCase(bean.getMode())){
				       query = getQuery("GET_INTIMATE_FOR_INSERT_HOME");
				       sequence = (String)this.mytemplate.queryForObject("SELECT CLAIM_INTIMATION_SEQ.NEXTVAL FROM DUAL", String.class);
				       bean.setClaimNo(sequence);
					}
					if("Update".equalsIgnoreCase(bean.getMode())){
					   query = getQuery("GET_INTIMATE_FOR_UPDATE_HOME");
					}
			}
			LogManager.info("query=>" +query);
			if( "65".equalsIgnoreCase(bean.getProductId()) && "Insert".equalsIgnoreCase(bean.getMode())){
		    args = new Object[26];
			args[0] = bean.getSoleOwnerYN();
			args[1] = bean.getFinanciers();
			args[2] = bean.getNameOfDriver();
			args[3] = bean.getDriverDob();
			args[4] = bean.getLicenseNumber();
			args[5] = bean.getDateObtained();
			args[6] = bean.getLossDate();
			args[7] = bean.getLossTime();
			args[8] = bean.getLossPlace();
			args[9] = bean.getLossSpeed();
			args[10] = bean.getLossPurposeDescription();
			args[11] = bean.getLossDetailDescription();
			args[12] = bean.getLossReportYN();
			args[13] = bean.getLossDateReported();
			args[14] = bean.getLossTimeReported();
			args[15] = bean.getLossPoliceVisitYN();
			args[16] = bean.getPoliceOfficerName();
			args[17] = bean.getIdentityNumber();
			args[18] = bean.getPoliceStationName();
			args[19] = bean.getLossLocation();
			args[20] = bean.getDriverDescription();
			args[21] = bean.getDeclaration();
			args[22] = sequence;
			args[23] = bean.getPolicyNo();
			args[24] = bean.getVehicleId();
			args[25] = "O";
			}
			if( "65".equalsIgnoreCase(bean.getProductId()) && "Update".equalsIgnoreCase(bean.getMode())){
				args = new Object[25];
				args[0] = bean.getSoleOwnerYN();
				args[1] = bean.getFinanciers();
				args[2] = bean.getNameOfDriver();
				args[3] = bean.getDriverDob();
				args[4] = bean.getLicenseNumber();
				args[5] = bean.getDateObtained();
				args[6] = bean.getLossDate();
				args[7] = bean.getLossTime();
				args[8] = bean.getLossPlace();
				args[9] = bean.getLossSpeed();
				args[10] = bean.getLossPurposeDescription();
				args[11] = bean.getLossDetailDescription();
				args[12] = bean.getLossReportYN();
				args[13] = bean.getLossDateReported();
				args[14] = bean.getLossTimeReported();
				args[15] = bean.getLossPoliceVisitYN();
				args[16] = bean.getPoliceOfficerName();
				args[17] = bean.getIdentityNumber();
				args[18] = bean.getPoliceStationName();
				args[19] = bean.getLossLocation();
				args[20] = bean.getDriverDescription();
				args[21] = bean.getDeclaration();
				args[22] = bean.getPolicyNo();
				args[23] = bean.getVehicleId();
				args[24] = bean.getClaimNo();
			}
			if( "30".equalsIgnoreCase(bean.getProductId()) && "Insert".equalsIgnoreCase(bean.getMode())){
				args = new Object[6];
				args [0] = bean.getLossStatus();
				args [1] = bean.getLossDescription();
				args [2] = sequence;
				args [3] = bean.getPolicyNo();
				args [4] = bean.getProductId();
				args [5] = "O";
				}
			if( "30".equalsIgnoreCase(bean.getProductId()) && "Update".equalsIgnoreCase(bean.getMode())){
				args = new Object[5];
				args [0] = bean.getLossStatus();
				args [1] = bean.getLossDescription();
				args [2] = bean.getPolicyNo();
				args [3] = bean.getProductId();
				args [4] = bean.getClaimNo();
				}
			removeNull(args);
			LogManager.info("Argument =>" +StringUtils.join(args,","));
			this.mytemplate.update(query,args);
			LogManager.info("Exit from getIntimateInsert()");
		}catch(Exception e){
			LogManager.error("Exception Occured @ getIntimateInsert()" +e);
			e.printStackTrace();
		}
	}
	
	public List<Map<String, Object>> getIntimateView(ClaimBean bean){
		LogManager.info("Enter into getIntimateView()");
		String query = null;
		Object args[] = null;
		List<Map<String, Object>> result = null;
		try{
			if( "65".equalsIgnoreCase(bean.getProductId())){
				query = getQuery("GET_LIST_FOR_INTIMATE_VIEW");
		    	args = new Object[]{bean.getPolicyNo(),bean.getVehicleId(),bean.getClaimNo()};
			}
			if( "30".equalsIgnoreCase(bean.getProductId())){
			    query = getQuery("GET_LIST_FOR_INTIMATE_VIEW_HOME");
			    args = new Object[]{bean.getPolicyNo(),bean.getProductId(),bean.getClaimNo()};
			}
			removeNull(args);
			LogManager.info("query =>" +query);
			LogManager.info("Argument =>"+StringUtils.join(args,","));
			result = this.mytemplate.queryForList(query,args);
			LogManager.info("Exit From getIntimateView()");
		}catch(Exception e){
			LogManager.error("Exception Occured @ getIntimateView()" +e);
			e.printStackTrace();
		}
		return result;
	}
	
	public List<Map<String, Object>> getIntimateThirdPartyView(ClaimBean bean){
		LogManager.info("Enter into getIntimateThirdPartyView()");
		List<Map<String, Object>> result = null;
		try{
			String query = getQuery("GET_LIST_FOR_INTIMATE_THIRD_PARTY_VIEW");
			LogManager.info("query =>" +query);
			Object[] args = new Object [3];
			args[0] = bean.getPolicyNo();
			args[1] = bean.getVehicleId();
			args[2] = bean.getClaimNo();
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
	
	public int getCheckIntimatePolicy(ClaimBean bean){
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
	
	public List<Map<String, Object>> getIntimateEdit(ClaimBean bean){
		LogManager.info("Enter into getIntimateEdit()");
		List<Map<String,Object>> result = null;
		String query = null;
		Object args[] = null;
		try{
			if(!"admin".equalsIgnoreCase(bean.getLoginType())){
				if( "65".equalsIgnoreCase(bean.getProductId())){
					query = getQuery("GET_INTIMATE_FOR_EDIT");
					args = new Object [3];
					args[0] = bean.getPolicyNo();
					args[1] = bean.getVehicleId();
					args[2] = bean.getClaimNo();
				}
				if( "30".equalsIgnoreCase(bean.getProductId())){
					query = getQuery("GET_INTIMATE_FOR_EDIT_HOME");
					args = new Object [3];
					args[0] = bean.getPolicyNo();
					args[1] = bean.getProductId();
					args[2] = bean.getClaimNo();
				}
				LogManager.info("query =>" +query);
				LogManager.info("Arguments =>" +StringUtils.join(args,","));
				result = this.mytemplate.queryForList(query,args);
				if(result !=null && result.size() > 0){
					Map map = (Map) result.get(0);
					if( "65".equalsIgnoreCase(bean.getProductId())){
					bean.setSoleOwnerYN(map.get("SOLE_OWNER_YN")==null?"":map.get("SOLE_OWNER_YN").toString());
					bean.setFinanciers(map.get("FINANCIERS")==null?"":map.get("FINANCIERS").toString());
					bean.setNameOfDriver(map.get("NAME_OF_DRIVER")==null?"":map.get("NAME_OF_DRIVER").toString());
					bean.setDriverDob(map.get("DRIVER_DOB")==null?"":map.get("DRIVER_DOB").toString());
					bean.setLicenseNumber(map.get("LICENSE_NO")==null?"":map.get("LICENSE_NO").toString());
					bean.setDateObtained(map.get("DATE_OBTAINED")==null?"":map.get("DATE_OBTAINED").toString());
					bean.setLossDate(map.get("LOSS_DATE")==null?"":map.get("LOSS_DATE").toString());
					bean.setLossTime(map.get("LOSS_TIME")==null?"":map.get("LOSS_TIME").toString());
					bean.setLossPlace(map.get("LOSS_PLACE")==null?"":map.get("LOSS_PLACE").toString());
					bean.setLossSpeed(map.get("LOSS_SPEED")==null?"":map.get("LOSS_SPEED").toString());
					bean.setLossPurposeDescription(map.get("LOSS_PURPOSE_DESC")==null?"":map.get("LOSS_PURPOSE_DESC").toString());
					bean.setLossDetailDescription(map.get("LOSS_DETAILS_DESC")==null?"":map.get("LOSS_DETAILS_DESC").toString());
					bean.setLossReportYN(map.get("LOSS_REPORT_YN")==null?"":map.get("LOSS_REPORT_YN").toString());
					bean.setLossDateReported(map.get("LOSS_DATE_REPORTED")==null?"":map.get("LOSS_DATE_REPORTED").toString());
					bean.setLossTimeReported(map.get("LOSS_TIME_REPORTED")==null?"":map.get("LOSS_TIME_REPORTED").toString());
					bean.setLossPoliceVisitYN(map.get("LOSS_POLICE_VISIT_YN")==null?"":map.get("LOSS_POLICE_VISIT_YN").toString());
					bean.setPoliceOfficerName(map.get("POLICE_OFFICER_NAME")==null?"":map.get("POLICE_OFFICER_NAME").toString());
					bean.setIdentityNumber(map.get("IDENTITY_NO")==null?"":map.get("IDENTITY_NO").toString());
					bean.setPoliceStationName(map.get("POLICE_STATION_NAME")==null?"":map.get("POLICE_STATION_NAME").toString());
					bean.setLossLocation(map.get("LOSS_LOCATION")==null?"":map.get("LOSS_LOCATION").toString());
					bean.setDriverDescription(map.get("DRIVER_DESC")==null?"":map.get("DRIVER_DESC").toString());
					}
					if( "30".equalsIgnoreCase(bean.getProductId())){
						bean.setLossStatus(map.get("LOSS_STATUS")==null?"":map.get("LOSS_STATUS").toString());
						bean.setLossDescription(map.get("LOSS_DESCRIPTION")==null?"":map.get("LOSS_DESCRIPTION").toString());
					}
				}
			}else{
				if( "65".equalsIgnoreCase(bean.getProductId())){
				    query = getQuery("GET_INTIMATE_REPORT_FOR_EDIT");
				    args = new Object [3];
					args[0] = bean.getPolicyNo();
					args[1] = bean.getVehicleId();
					args[2] = bean.getClaimNo();
				}
				if( "30".equalsIgnoreCase(bean.getProductId())){
				    query = getQuery("GET_INTIMATE_REPORT_FOR_EDIT_HOME");
				    args = new Object [3];
					args[0] = bean.getPolicyNo();
					args[1] = bean.getProductId();
					args[2] = bean.getClaimNo();
				}
				LogManager.info("query =>" +query);
				LogManager.info("Arguments =>" +StringUtils.join(args,","));
				result = this.mytemplate.queryForList(query,args);
				if(result !=null){
					Map map = (Map) result.get(0);
					bean.setApproverStatus(map.get("APPROVER_STATUS")==null?"":map.get("APPROVER_STATUS").toString());
					bean.setRemarks(map.get("APPROVER_REMARKS")==null?"":map.get("APPROVER_REMARKS").toString());
				}
			}
		}catch(Exception e){
			LogManager.error("Exception occured @ getIntimateEdit()");
			e.printStackTrace();
		}
		LogManager.info("Exit from getIntimateEdit()");
		return result;
	}
	
	public void getIntimateThirdPartyInsert(ClaimBean bean){
		LogManager.info("Enter into getIntimateThirdPartyInsert()");
		String query = null;
		String sequence = null;
		Object[] args = null;
		try{
			if("Insert".equalsIgnoreCase(bean.getMode())){
		       query = getQuery("GET_INTIMATE_THIRD_PARTY_FOR_INSERT");
		       sequence = (String)this.mytemplate.queryForObject("SELECT CLAIM_INTIMATION_SEQ.NEXTVAL FROM DUAL", String.class);
		       bean.setClaimNo(sequence);
			}
			if("Update".equalsIgnoreCase(bean.getMode())){
			   query = getQuery("GET_INTIMATE_THIRD_PARTY_FOR_UPDATE");
			}LogManager.info("query=>" +query);
			if("Insert".equalsIgnoreCase(bean.getMode())){
			args = new Object[24];
			args[0] = bean.getPassengerNameA();
			args[1] = bean.getPassengerNameB();
			args[2] = bean.getPassengerPhoneNoA();
			args[3] = bean.getPassengerPhoneNoB();
			args[4] = bean.getPassengerAddressA();
			args[5] = bean.getPassengerAddressB();
			args[6] = bean.getPassengerInjuryA();
			args[7] = bean.getPassengerInjuryB();
			args[8] = bean.getHospitalName();
			args[9] = bean.getNameOfDoctor();
			args[10] = bean.getDoctorTelephoneNumber();
			args[11] = bean.getThirdPartyName();
			args[12] = bean.getThirdPartyPropertyDetail();
			args[13] = bean.getThirdPartyOwnerName();
			args[14] = bean.getThirdPartyOwnerTelephoneNumber();
			args[15] = bean.getDriverName();
			args[16] = bean.getPhysicalAddress();
			args[17] = bean.getRegisterNumber();
			args[18] = bean.getMake();
			args[19] = bean.getNameOfInsurer();
			args[20] = sequence;
			args[21] = bean.getPolicyNo();
			args[22] = bean.getVehicleId();
			args[23] = "O";
			}
			if("Update".equalsIgnoreCase(bean.getMode())){
				args = new Object[23];
				args[0] = bean.getPassengerNameA();
				args[1] = bean.getPassengerNameB();
				args[2] = bean.getPassengerPhoneNoA();
				args[3] = bean.getPassengerPhoneNoB();
				args[4] = bean.getPassengerAddressA();
				args[5] = bean.getPassengerAddressB();
				args[6] = bean.getPassengerInjuryA();
				args[7] = bean.getPassengerInjuryB();
				args[8] = bean.getHospitalName();
				args[9] = bean.getNameOfDoctor();
				args[10] = bean.getDoctorTelephoneNumber();
				args[11] = bean.getThirdPartyName();
				args[12] = bean.getThirdPartyPropertyDetail();
				args[13] = bean.getThirdPartyOwnerName();
				args[14] = bean.getThirdPartyOwnerTelephoneNumber();
				args[15] = bean.getDriverName();
				args[16] = bean.getPhysicalAddress();
				args[17] = bean.getRegisterNumber();
				args[18] = bean.getMake();
				args[19] = bean.getNameOfInsurer();
				args[20] = bean.getPolicyNo();
				args[21] = bean.getVehicleId();
				args[22] = bean.getClaimNo();
				}
			removeNull(args);
			LogManager.info("Argument =>" +StringUtils.join(args,","));
			this.mytemplate.update(query,args);
			LogManager.info("Exit from getIntimateThirdPartyInsert()");
		}catch(Exception e){
			LogManager.error("Exception Occured @ getIntimateThirdPartyInsert()" +e);
			e.printStackTrace();
		}
	}
	
	public List<Map<String, Object>> getIntimateThirdPartyEdit(ClaimBean bean){
		LogManager.info("Enter into getIntimateThirdPartyEdit()");
		List<Map<String,Object>> result = null;
		try{
			if(!"admin".equalsIgnoreCase(bean.getLoginType())){
				String query = getQuery("GET_INTIMATE_THIRD_PARTY_FOR_EDIT");
				LogManager.info("query =>" +query);
				Object[] args = new Object [3];
				args[0] = bean.getPolicyNo();
				args[1] = bean.getVehicleId();
				args[2] = bean.getClaimNo();
				LogManager.info("Arguments =>" +StringUtils.join(args,","));
				result = this.mytemplate.queryForList(query,args);
				if(result !=null){
					Map map = (Map) result.get(0);
					bean.setPassengerNameA(map.get("PASSENGER_NAME_A")==null?"":map.get("PASSENGER_NAME_A").toString());
					bean.setPassengerNameB(map.get("PASSENGER_NAME_B")==null?"":map.get("PASSENGER_NAME_B").toString());
					bean.setPassengerPhoneNoA(map.get("PASSENGER_PHONENO_A")==null?"":map.get("PASSENGER_PHONENO_A").toString());
					bean.setPassengerPhoneNoB(map.get("PASSENGER_PHONENO_B")==null?"":map.get("PASSENGER_PHONENO_B").toString());
					bean.setPassengerAddressA(map.get("PASSENGER_ADDRESS_A")==null?"":map.get("PASSENGER_ADDRESS_A").toString());
					bean.setPassengerAddressB(map.get("PASSENGER_ADDRESS_B")==null?"":map.get("PASSENGER_ADDRESS_B").toString());
					bean.setPassengerInjuryA(map.get("PASSENGER_INJURY_A")==null?"":map.get("PASSENGER_INJURY_A").toString());
					bean.setPassengerInjuryB(map.get("PASSENGER_INJURY_B")==null?"":map.get("PASSENGER_INJURY_B").toString());
					bean.setHospitalName(map.get("HOSPITAL_NAME")==null?"":map.get("HOSPITAL_NAME").toString());
					bean.setNameOfDoctor(map.get("NAME_OF_DOCTOR")==null?"":map.get("NAME_OF_DOCTOR").toString());
					bean.setDoctorTelephoneNumber(map.get("DOC_TELEPHONE_NO")==null?"":map.get("DOC_TELEPHONE_NO").toString());
					bean.setThirdPartyName(map.get("THIRD_PARTY_NAME")==null?"":map.get("THIRD_PARTY_NAME").toString());
					bean.setThirdPartyPropertyDetail(map.get("THIRD_PARTY_PROPERTY_DETAIL")==null?"":map.get("THIRD_PARTY_PROPERTY_DETAIL").toString());
					bean.setThirdPartyOwnerName(map.get("THIRD_PARTY_OWNER_NAME")==null?"":map.get("THIRD_PARTY_OWNER_NAME").toString());
					bean.setThirdPartyOwnerTelephoneNumber(map.get("THIRD_PARTY_OWNER_TELEPHONE_NO")==null?"":map.get("THIRD_PARTY_OWNER_TELEPHONE_NO").toString());
					bean.setDriverName(map.get("DRIVER_NAME")==null?"":map.get("DRIVER_NAME").toString());
					bean.setPhysicalAddress(map.get("PHYSICAL_ADDRESS")==null?"":map.get("PHYSICAL_ADDRESS").toString());
					bean.setRegisterNumber(map.get("REGISTER_NO")==null?"":map.get("REGISTER_NO").toString());
					bean.setMake(map.get("MAKE")==null?"":map.get("MAKE").toString());
					bean.setNameOfInsurer(map.get("NAME_OF_INSURER")==null?"":map.get("NAME_OF_INSURER").toString());
				}
			}else{
				String query = getQuery("GET_INTIMATE_REPORT_THIRD_PARTY_FOR_EDIT");
				LogManager.info("query =>" +query);
				Object[] args = new Object [3];
				args[0] = bean.getPolicyNo();
				args[1] = bean.getVehicleId();
				args[2] = bean.getClaimNo();
				LogManager.info("Arguments =>" +StringUtils.join(args,","));
				result = this.mytemplate.queryForList(query,args);
				if(result !=null){
					Map map = (Map) result.get(0);
					bean.setApproverStatus(map.get("APPROVER_STATUS")==null?"":map.get("APPROVER_STATUS").toString());
					bean.setRemarks(map.get("APPROVER_REMARKS")==null?"":map.get("APPROVER_REMARKS").toString());
				}
			}
		}catch(Exception e){
			LogManager.error("Exception occured @ getIntimateThirdPartyEdit()");
			e.printStackTrace();
		}
		LogManager.info("Exit from getIntimateThirdPartyEdit()");
		return result;
	}
	
	public int checkIntimateComp(ClaimBean bean){
		LogManager.info("Enter into checkIntimateComp()");
		int result = 0;
		String query = null;
		Object [] args = null;
		try{
			if( "65".equalsIgnoreCase(bean.getProductId())){
		    query = getQuery("CHECK_INTIMATE_COMP");
			}
			if( "30".equalsIgnoreCase(bean.getProductId())){
			    query = getQuery("CHECK_INTIMATE_COMP_HOME");
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
			result = this.mytemplate.queryForInt(query,args);
			LogManager.info("Exit From checkIntimateComp()");
			}catch(Exception e){
				LogManager.error("Exception Occured @ checkIntimateComp()" +e);
				e.printStackTrace();
			}
			return result;
	}
	
	public int checkIntimateTpa(ClaimBean bean){
		LogManager.info("Enter into checkIntimateTpa()");
		int result = 0;
		try{
			String query = getQuery("CHECK_INTIMATE_TPA");
			LogManager.info("query =>" +query);
			Object[] args = new Object [2];
			args[0] = bean.getPolicyNo();
			args[1] = bean.getVehicleId();
			removeNull(args);
			LogManager.info("Argument =>"+StringUtils.join(args,","));
			result = this.mytemplate.queryForInt(query,args);
			LogManager.info("Exit From checkIntimateTpa()");
			}catch(Exception e){
				LogManager.error("Exception Occured @ checkIntimateTpa()" +e);
				e.printStackTrace();
			}
			return result;
	}
	
	public List<Map<String, Object>> getAssitHomeInfo(ClaimBean bean){
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
	
	public List<Object> getHomeInfo(ClaimBean bean){
		LogManager.info("getHomeInfo - Enter");
		List<Map<String, String>> list=null;	
		List<Object> li=new ArrayList<Object>();
			try{
				String sql=getQuery("GET_HOME_INFO");
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
								m.put("Value", arrVal[0]);
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
	
	public List<Map<String, Object>> getEndorsement(ClaimBean bean){
		List<Map<String, Object>> result = null;
		try{
			LogManager.info("Enter into getEndorsement()");
			String query = getQuery("GET_LIST_FOR_INTIMATE");
			LogManager.info("query =>" +query);
			Object[] args = new Object []{bean.getPolicyNo()};
			removeNull(args);
			LogManager.info("Argument =>"+StringUtils.join(args,","));
			result = this.mytemplate.queryForList(query,args);
			LogManager.info("Exit From getEndorsamentPolicy()");
		}catch(Exception e){
			LogManager.error("Exception Occured @ getEndorsamentPolicy()" +e);
			e.printStackTrace();
		}
		return result;
	} 
	
	public List<Map<String, Object>> getEndorsamentPolicy(ClaimBean bean)
	{
		LogManager.info("Enter into getEndorsamentPolicy()");
		List<Map<String, Object>> result = null;
		try{
			String query = getQuery("GET_LIST_FOR_INTIMATE_POLICY");
			LogManager.info("query =>" +query);
			Object[] args = new Object []{bean.getPolicyNo()};
			LogManager.info("Argument =>"+StringUtils.join(args,","));
			result = this.mytemplate.queryForList(query,args);
			LogManager.info("Exit From getEndorsamentPolicy()");
		}catch(Exception e){
			LogManager.error("Exception Occured @ getEndorsamentPolicy()" +e);
			e.printStackTrace();
		}
		return result;
	}
	public List<Map<String, Object>> getEndorsamentVehicle(ClaimBean bean){
		LogManager.info("Enter into getEndorsamentVehicle()");
		List<Map<String, Object>> result = null;
		try{
			String query = getQuery("GET_LIST_FOR_INTIMATE_VEHICLE");
			LogManager.info("query =>" +query);
			Object[] args = new Object [2];
			args[0] = bean.getPolicyNo();
			args[1] = bean.getVehicleId();
			LogManager.info("Argument =>"+StringUtils.join(args,","));
			result = this.mytemplate.queryForList(query,args);
			LogManager.info("Exit From getEndorsamentVehicle()");
		}catch(Exception e){
			LogManager.error("Exception Occured @ getEndorsamentVehicle()" +e);
			e.printStackTrace();
		}
		return result;
	}

	public List<Map<String, Object>> getEndorsementDropdown(ClaimBean bean) {
		List<Map<String, Object>> result = null;
		try{
			LogManager.info("Enter into getEndorsementDropdown()");
			String query = getQuery("GET_ENDORSEMENT_DROPDOWN");
			LogManager.info("query =>" +query);
			Object args[]={bean.getProductId()};
			result = this.mytemplate.queryForList(query,args);
			LogManager.info("Exit From getEndorsementDropdown()");
			
		}
		catch(Exception e){
			LogManager.error("Exception Occured @ getEndorsementDropdown()" +e);
			e.printStackTrace();
		}
		return result;
	}
	public String insertEndtRequest(ClaimBean bean) {
		String seq=null;
		try {
			LogManager.info("Enter into insertEndtRequest()");
			String query="";Object[] args=null;
			if("Update".equalsIgnoreCase(bean.getMode())){
				query = getQuery("UPD_ENDORSMENT_DETAILS");
				args=new Object[4];
				args[0]=bean.getEndTypeId();
				args[1]=bean.getAgentRemarks();
				args[2]=bean.getRefNo();
				args[3]=bean.getProductId();
			}else{
				query = getQuery("INS_MOTOR_OTHERENDORSEMENT");
				seq=(String) this.mytemplate.queryForObject("select endt_reference_seq.nextval from dual", String.class);
				args=new Object[7];
				args[0]=bean.getPolicyNo();
				args[1]=bean.getVehicleId();
				args[2]=bean.getEndTypeId();
				args[3]=bean.getAgentRemarks();
				args[4]=seq;
				args[5]=bean.getProductId();
				args[6]="H";
			}
			LogManager.info("query =>" +query);
			LogManager.info("Args==> " + StringUtils.join(args, ", "));
			this.mytemplate.update(query,args);
			LogManager.info("Exit From insertEndtRequest()");
			
		} catch(Exception e){
			LogManager.error("Exception Occured @ getEndorsementInsert()" +e);
			e.printStackTrace();
		}
		return seq;
	}
	public List<Map<String, Object>> getEndtReqList(ClaimBean bean) {
		List<Map<String, Object>> result = null;
		try{
			LogManager.info("Enter into getEndtReqList()");
			String query = "";
			if( "65".equalsIgnoreCase(bean.getProductId())){
				query = getQuery("GET_ENDT_REQ_LIST_MOTOR");

			} else if( "30".equalsIgnoreCase(bean.getProductId())){
				query = getQuery("GET_ENDT_REQ_LIST_HOME");
			}
			Object[] args={bean.getProductId(),bean.getLoginId()};
			LogManager.info("query =>" +query);
			LogManager.info("Argument =>" +StringUtils.join(args,","));
			result = this.mytemplate.queryForList(query,args);
			LogManager.info("Exit From getEndtReqList()");
		}catch(Exception e){
			LogManager.error("Exception Occured @ getEndtReqList()" +e);
			e.printStackTrace();
		}
		return result;	
		}
	public List<Map<String, Object>> getEndtNewReqList(ClaimBean bean) {
		List<Map<String, Object>> result = null;
		try{
			Object[] args=null;
			LogManager.info("Enter into getEndtReqList()");
			String query = "";
			if( "65".equalsIgnoreCase(bean.getProductId())){
				args=new Object[3];
				args[0]=bean.getProductId();
				args[1]=bean.getPolicyNo();
				args[2]=bean.getVehicleId();
				query = getQuery("GET_ENDT_REQ_NEW_MOTOR");

			} else if( "30".equalsIgnoreCase(bean.getProductId())){
				args=new Object[2];
				args[0]=bean.getProductId();
				args[1]=bean.getPolicyNo();
				query = getQuery("GET_ENDT_REQ_NEW_HOME");
			}
			
			LogManager.info("query =>" +query);
			LogManager.info("Argument =>" +StringUtils.join(args,","));
			result = this.mytemplate.queryForList(query,args);
			LogManager.info("Exit From getEndtReqList()");
		}catch(Exception e){
			LogManager.error("Exception Occured @ getEndtReqList()" +e);
			e.printStackTrace();
		}
		return result;	
		}
	
	
	/**
	 * Admin
	 */
	public List<Map<String, Object>> claimIntimateReportList(ClaimBean bean){
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
	public List<Map<String, Object>> claimIntimateReportTpaList(ClaimBean bean){
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
	public List<Map<String, Object>> getClaimReqList(ClaimBean bean) {
		List<Map<String, Object>> result = null;
		try{
			LogManager.info("Enter into getClaimReqList()");
			String query ="";
			Object[] args=null;
			if("30".equalsIgnoreCase(bean.getProductId())){
				query = getQuery("GET_CLAIM_INTIMATION_STATUS_HOME");
				args=new Object[]{bean.getLoginId(),bean.getProductId()};
			}else if("65".equalsIgnoreCase(bean.getProductId())){
				query = getQuery("GET_CLAIM_INTIMATION_STATUS");
				args=new Object[]{bean.getProductId(),bean.getLoginId(),bean.getProductId(),bean.getLoginId()};
			}
			LogManager.info("query =>" +query);
			result = this.mytemplate.queryForList(query,args);
			LogManager.info("Exit From getClaimReqList()");
		}catch(Exception e){
			LogManager.error("Exception Occured @ getClaimReqList()" +e);
			e.printStackTrace();
		}
		return result;	
	}
	public void getIntimationInsert(ClaimBean bean){
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
				args = new Object[8];
				args[0] = bean.getApproverStatus();
				args[1] = bean.getSettleAmt();
				args[2] = bean.getEstimateAmt();
				args[3] = bean.getRemarks();
				args[4] = bean.getLoginId();
				args[5] = bean.getPolicyNo();
				args[6] = bean.getVehicleId();
				args[7] = bean.getClaimNo();
				}
			if( "30".equalsIgnoreCase(bean.getProductId())){
				args = new Object[8];
				args[0] = bean.getApproverStatus();
				args[1] = bean.getSettleAmt();
				args[2] = bean.getEstimateAmt();
				args[3] = bean.getRemarks();
				args[4] = bean.getLoginId();
				args[5] = bean.getPolicyNo();
				args[6] = bean.getProductId();
				args[7] = bean.getClaimNo();
			}
			LogManager.info("Argument =>" +StringUtils.join(args,","));
			this.mytemplate.update(query,args);
			LogManager.info("Exit from getIntimationInsert()");
		}catch(Exception e){
			LogManager.error("Exception Occured @ getIntimationInsert()" +e);
			e.printStackTrace();
		}
	}

	public void getIntimationTpaInsert(ClaimBean bean){
		LogManager.info("Enter into getIntimationTpaInsert()");
		String query = null;
		try{
		    query = getQuery("GET_INTIMATE_REPORT_TPA_FOR_UPDATE");
			LogManager.info("query=>" +query);
			Object[] args = new Object[8];
			args[0] = bean.getApproverStatus();
			args[1] = bean.getSettleAmt();
			args[2] = bean.getEstimateAmt();
			args[3] = bean.getRemarks();
			args[4] = bean.getLoginId();
			args[5] = bean.getPolicyNo();
			args[6] = bean.getVehicleId();
			args[7] = bean.getClaimNo();
			LogManager.info("Argument =>" +StringUtils.join(args,","));
			this.mytemplate.update(query,args);
			LogManager.info("Exit from getIntimationTpaInsert()");
		}catch(Exception e){
			LogManager.error("Exception Occured @ getIntimationTpaInsert()" +e);
			e.printStackTrace();
		}
	}
	public void getEndorsementUpdate(ClaimBean bean) {
		try
		{
			String query = getQuery("GET_ENDORSEMENT_ADMIN_UPDATE");
			Object[] args = new Object[4];
			args[0]=bean.getLoginId();
			args[1]=bean.getRemarks();
			args[2]=bean.getApproverStatus();
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
	public List<Map<String, Object>> getEndorsementList(ClaimBean bean) {
		List<Map<String, Object>> endorsementList=null;
		try
		{
			String query = "";
			
			if("65".equalsIgnoreCase(bean.getProductId()))
				query = getQuery("GET_ENDORSEMENT_DETAILS_MOTOR");
			else if("30".equalsIgnoreCase(bean.getProductId()))
				query = getQuery("GET_ENDORSEMENT_DETAILS_HOME");
			Object[] args = new Object[3];
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
	public List<Map<String, Object>> getEndorsementListView(ClaimBean bean) {
		List<Map<String, Object>> endtList=null;
		try
		{
			String query = "";
			if("65".equalsIgnoreCase(bean.getProductId()))
			{
				query = getQuery("GET_ENDT_MOTOR_ADMIN_VIEW");
			}else if("30".equalsIgnoreCase(bean.getProductId())){
				query = getQuery("GET_ENDT_HOME_ADMIN_VIEW");			
				}
				
			
			Object[] args = new Object[1];
			args[0]=bean.getRefNo();
			LogManager.info("Query===>" + query);
			LogManager.info("args"+StringUtils.join(args,","));
			endtList=this.mytemplate.queryForList(query,args);
			 if( endtList!=null && endtList.size()>0){
				 Map<String, Object> record = endtList.get(0);
				 bean.setRemarks(record.get("ADMIN_REMARKS")==null?"":record.get("ADMIN_REMARKS").toString());
				 bean.setApproverStatus(record.get("STATUS1")==null?"":record.get("STATUS1").toString());
			 }  	
		}
		catch (Exception e) {			
			LogManager.debug("EXCEPTION @ { " + e + " }");
			e.printStackTrace();
		}
		
		return endtList;
	}
	public String getUpdateStatus(ClaimBean bean) {
		String result="";
		try{
			if(StringUtils.isNotBlank(bean.getRefNo()) || StringUtils.isNotBlank(bean.getClaimNo())){
				String query = "";
				Object[] args=null;
				if("endorsement".equalsIgnoreCase(bean.getRequireType())){
					query =getQuery("CHECK_ENDT_EDIT_OPTIONS");
					args = new Object[]{bean.getRefNo(),bean.getProductId()};
				}else {
					if("65".equalsIgnoreCase(bean.getProductId())){
						query =getQuery("CHECK_CLAIM_EDIT_OPTIONS");
						args = new Object[]{bean.getClaimNo(),bean.getClaimNo()};
					}else{
						query =getQuery("CHECK_HOME_CLAIM_EDIT_OPTIONS");
						args = new Object[]{bean.getClaimNo()};
					}
				}
				LogManager.info("Query===>" + query);
				LogManager.info("Arguments===>" + StringUtils.join(args,","));
				result=(String) this.mytemplate.queryForObject(query,args,String.class);
				if(Integer.parseInt(StringUtils.isBlank(result)?"0":result) <=0){
					result="N";
				}else{
					result="Y";
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	public void insertClaimApprovedDetails(ClaimBean bean) {
		try{
			String query=getQuery("INS_CLAIM_APPROVED_DETAILS");
			Object[] args=new Object[]{bean.getClaimNo(),bean.getApproverStatus(),bean.getRemarks(),bean.getLoginId()};
			LogManager.info("Query===>" + query);
			LogManager.info("Arguments===>" + StringUtils.join(args,","));
			this.mytemplate.update(query,args);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
