package com.maan.adminnew.MotorNew;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.dao.DataAccessException;

import com.maan.common.LogManager;
import com.maan.common.MyJdbcTemplate;
import com.opensymphony.xwork2.ActionContext;

public class MotorAdminDaoNew extends MyJdbcTemplate {
	Map<String, Object> session = ActionContext.getContext().getSession();
	String branchCode = (String) session.get("BranchCode");
	private final String groupId = "151";
	private String loginId = (String) session.get("user");
	String productId = "65";
	String query;
	/*
	 * (Getting Sub policy type based on policy type and body type)
	 */
	public List<Map<String, Object>> getSubPolicyList(MotorAdminBeanNew bean) {
		List<Map<String, Object>> result = null;
		try {
			String query = getQuery("GET_SUB_POLICY_TYPE_LIST");
			Object args[] = null;

			if ("editSubPolicy".equalsIgnoreCase(bean.getMode())) {
				query += "AND MSM.SUB_RATE_ID ! = ?";
				args = new Object[] { bean.getPolicyType(), bean.getBodyType(),bean.getSubRateId() };
			} else {
				args = new Object[] { bean.getPolicyType(), bean.getBodyType() };
			}

			LogManager.info("Query => " + query);
			LogManager.info("Arguments = > " + StringUtils.join(args, ","));
			result = this.mytemplate.queryForList(query, args);
		} catch (Exception e) {
			LogManager.info("Exception Occured @ getSubPolicyList ");
			e.printStackTrace();
		}
		return result;
	}

	/*
	 * (Getting Comprehensive Rate List From MOTOR_TYPE_RATE_MASTER )
	 */
	public List<Map<String, Object>> getComprehensiveRateList(
			MotorAdminBeanNew bean) {
		List<Map<String, Object>> result = null;
		try {
			String query = getQuery("GET_COMPREHENSIVE_RATE_LIST");
			Object[] args;
			if ("editCompreRate".equalsIgnoreCase(bean.getMode())) {
				query += " AND RATE_ID!=?";
				args = new Object[] { bean.getSubRateId(), bean.getRateId() };
			} else {
				args = new Object[] { bean.getSubRateId() };
			}

			LogManager.info("Query => " + query);
			LogManager.info("Arguments = > " + StringUtils.join(args, ","));
			result = this.mytemplate.queryForList(query, args);
		} catch (Exception e) {
			LogManager.info("Exception Occured @ getComprehensiveRateList ");
			e.printStackTrace();
		}
		return result;
	}

	/*
	 * (Ajax Validation Can be handle this method)
	 */
	public List<Map<String, Object>> getAjaxValidationList(
			MotorAdminBeanNew bean) {
		List<Map<String, Object>> result = null;
		try {
			String query = "";
			Object args[] = null;
			if ("checkVehicleAge".equalsIgnoreCase(bean.getReqFrom())) {
				query = getQuery("CHECK_VEHICLE_AGE_MODAL");
				if ("editSubPolicy".equalsIgnoreCase(bean.getMode())) {
					query += " AND SUB_RATE_ID != ?";
					args = new Object[] { bean.getPolicyType(),
							bean.getBodyType(), bean.getVehicleStartAge(),
							bean.getVehicleEndAge(), bean.getVehicleCount(),
							bean.getSubRateId() };
				} else {
					query += "";
					args = new Object[] { bean.getPolicyType(),
							bean.getBodyType(), bean.getVehicleStartAge(),
							bean.getVehicleEndAge(), bean.getVehicleCount() };				}
			} else if ("checkClaim".equalsIgnoreCase(bean.getReqFrom())) {
				query = getQuery("CHECK_VEHICLE_CLAIM_MODAL");
				if ("editSubPolicy".equalsIgnoreCase(bean.getMode())) {
					query += " AND SUB_RATE_ID != ?";
					args = new Object[] { bean.getPolicyType(),
							bean.getBodyType(), bean.getClaimStart(),
							bean.getClaimEnd(), bean.getSubRateId() };
				} else {
					query += "";
					args = new Object[] { bean.getPolicyType(),
							bean.getBodyType(), bean.getClaimStart(),
							bean.getClaimEnd() };
				}
			} else if ("checkSeatingTon".equalsIgnoreCase(bean.getReqFrom())) {
				query = getQuery("CHECK_SEATING_TON");
				if ("editCompreRate".equalsIgnoreCase(bean.getMode())) {
					query += " AND RATE_ID != ?";
					args = new Object[] { bean.getSubRateId(),
							bean.getSeatingTonStart(), bean.getSeatingTonEnd(),
							bean.getRateId() };
				} else {
					query += "";
					args = new Object[] { bean.getSubRateId(),
							bean.getSeatingTonStart(), bean.getSeatingTonEnd() };
				}

			} else if ("checkCCGVWRange".equalsIgnoreCase(bean.getReqFrom())) {
				query = getQuery("CHECK_CCGVWRANGE");
				if ("editCompreRate".equalsIgnoreCase(bean.getMode())) {
					query += " AND RATE_ID != ?";
					args = new Object[] { bean.getSubRateId(),
							bean.getCCGVWStartRange(), bean.getCCGVWEndRange(),
							bean.getRateId() };
				} else {
					query += "";
					args = new Object[] { bean.getSubRateId(),
							bean.getCCGVWStartRange(), bean.getCCGVWEndRange() };
				}
			} else if ("checkUAELiscence".equalsIgnoreCase(bean.getReqFrom())) {
				query = getQuery("CHECK_UAE_LISCENCE");
				if ("editCompreRate".equalsIgnoreCase(bean.getMode())) {
					query += " AND RATE_ID != ?";
					args = new Object[] { bean.getSubRateId(),
							bean.getUAELiscenceStart(),
							bean.getUAELiscenceEnd(), bean.getRateId() };
				} else {
					query += "";
					args = new Object[] { bean.getSubRateId(),
							bean.getUAELiscenceStart(),
							bean.getUAELiscenceEnd() };
				}
			} else if ("checkDriverAge".equalsIgnoreCase(bean.getReqFrom())) {
				query = getQuery("CHECK_DRIVER_AGE");
				if ("editCompreRate".equalsIgnoreCase(bean.getMode())) {
					query += " AND RATE_ID != ?";
					args = new Object[] { bean.getSubRateId(),
							bean.getDriverAgeFrom(), bean.getDriverAgeTo(),
							bean.getRateId() };
				} else {
					query += "";
					args = new Object[] { bean.getSubRateId(),
							bean.getDriverAgeFrom(), bean.getDriverAgeTo() };
				}
			} else if ("checkSumInsureSd".equalsIgnoreCase(bean.getReqFrom())) {
				query = getQuery("CHECK_SUM_INSURED");
				if ("editCompreRate".equalsIgnoreCase(bean.getMode())) {
					query += " AND RATE_ID != ?";
					args = new Object[] { bean.getSubRateId(),
							bean.getSuminsuredStart(), bean.getSuminsuredEnd(),
							bean.getRateId() };
				} else {
					query += "";
					args = new Object[] { bean.getSubRateId(),
							bean.getSuminsuredStart(), bean.getSuminsuredEnd() };
				}
			} else if ("checkSeatingCylinder".equalsIgnoreCase(bean
					.getReqFrom())) {
				query = getQuery("GET_THIRD_PARTY_SEATING_CYL_MODEL");
				if ("edit".equalsIgnoreCase(bean.getMode())) {
					query += " AND THIRD_PARTY_ID !=? ";
					args = new Object[] { bean.getBodyType(),
							bean.getSeatingCylinderStart(),
							bean.getSeatingCylinderEnd(),
							bean.getThirdPartyId() };
				} else {
					args = new Object[] { bean.getVehicleType(),
							bean.getSeatingCylinderStart(),
							bean.getSeatingCylinderEnd() };
				}
			}
			LogManager.info("Query => " + query);
			LogManager.info("Arguments = > " + StringUtils.join(args, ","));
			result = this.mytemplate.queryForList(query, args);
		} catch (Exception e) {
			LogManager.info("Exception Occured @ getAjaxValidationList ");
			e.printStackTrace();
		}
		return result;
	}

	public int checkVehicleAge(MotorAdminBeanNew bean) {
		int result = 0;
		try {
			String query = getQuery("CHECK_VEHICLE_AGE");
			Object[] args = null;
			if ("editSubPolicy".equalsIgnoreCase(bean.getMode())) {
				query += "AND MSM.SUB_RATE_ID ! = ?";
				args = new Object[] { bean.getPolicyType(), bean.getBodyType(),
						bean.getVehicleStartAge(), bean.getVehicleEndAge(),
						bean.getSubRateId() };
			} else {
				args = new Object[] { bean.getPolicyType(), bean.getBodyType(),
						bean.getVehicleStartAge(), bean.getVehicleEndAge() };
			}
			LogManager.info("Query => " + query);
			LogManager.info("Arguments => " + StringUtils.join(args, ","));
			result = this.mytemplate.queryForInt(query, args);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public String insertSubPolicyType(MotorAdminBeanNew bean) {
		String result = "";
		try {

			String query = "";
			String amendId = "";
			Object[] args = null;
			if ("editSubPolicy".equalsIgnoreCase(bean.getMode())) {
				query = getQuery("GET_EDIT_SUBPOLICY_AMEND");
				args = new Object[] { bean.getSubRateId() };
				LogManager.info("Query => " + query);
				LogManager.info("Arguments => " + StringUtils.join(args, ","));
				List<Map<String, Object>> res = this.mytemplate.queryForList(query, args);
				if (res.size() > 0)
					amendId = res.get(0).get("AMEND_ID") == null ? "" : res
							.get(0).get("AMEND_ID").toString();
			}
			if ("addSubPolicy".equalsIgnoreCase(bean.getMode())
					|| ("editSubPolicy".equalsIgnoreCase(bean.getMode()) && StringUtils.isBlank(amendId))) {
				query = getQuery("INS_MOTOR_SUBPOLICY");
				args = new Object[15];
				args[0] = bean.getPolicyType();
				args[1] = bean.getBodyType();
				if ("addSubPolicy".equalsIgnoreCase(bean.getMode())) {
					String sql = getQuery("GET_MAX_SUBPOLICY_ID");
					int subPolicyId = this.mytemplate.queryForInt(sql);
					args[2] = subPolicyId;
					args[3] = subPolicyId;
				} else {
					args[2] = bean.getSubRateId();
					args[3] = bean.getSubRateId();
				}
				args[4] = bean.getVehicleStartAge();
				args[5] = bean.getVehicleEndAge();
				args[6] = "999";
				args[7] = "9999";
				args[8] = "0";
				args[9] = "5";
				args[10] = bean.getStatus();
				args[11] = bean.getEffectiveDate();
				args[12] = bean.getRemarks();
				args[13] = bean.getDescription();
				args[14] = bean.getVehicleCount();
			} else if ("editSubPolicy".equalsIgnoreCase(bean.getMode()) && StringUtils.isNotBlank(amendId)) {
				query = getQuery("UPD_MOTOR_SUBPOLICY");
				args = new Object[15];
				args[0] = bean.getPolicyType();
				args[1] = bean.getBodyType();
				args[2] = bean.getVehicleStartAge();
				args[3] = bean.getVehicleEndAge();
				args[4] = "999";
				args[5] = "9999";
				args[6] = "0";
				args[7] = "5";
				args[8] = bean.getStatus();
				args[9] = bean.getEffectiveDate();
				args[10] = bean.getRemarks();
				args[11] = bean.getDescription();
				args[12] = bean.getVehicleCount();
				args[13] = bean.getSubRateId();
				args[14] = amendId;
			}
			LogManager.info("Query => " + query);
			LogManager.info("Arguments => " + StringUtils.join(args, ","));
			this.mytemplate.update(query, args);
		} catch (Exception e) {
			e.printStackTrace();
			result = e.toString();
		}
		return result;
	}

	public void editSubPolicyDetails(MotorAdminBeanNew bean) {
		try {
			String query = getQuery("GET_MOTOR_SUBPOLICY");
			LogManager.info("Query => " + query);
			Object args[] = { bean.getSubRateId() };
			LogManager.info("Arguments => " + StringUtils.join(args, ","));
			Map<String, Object> res = this.mytemplate.queryForMap(query, args);
			bean.setVehicleStartAge(res.get("AGE_OF_VEHICLE_START") == null ? "": res.get("AGE_OF_VEHICLE_START").toString());
			bean.setVehicleEndAge(res.get("AGE_OF_VEHICLE_END") == null ? "": res.get("AGE_OF_VEHICLE_END").toString());
			bean.setClaimStart(res.get("CLAIMS_START") == null ? "" : res.get("CLAIMS_START").toString());
			bean.setClaimEnd(res.get("CLAIMS_END") == null ? "" : res.get("CLAIMS_END").toString());
			bean.setApplicableCountry(res.get("COUNTRY_APPLICABLE") == null ? "": res.get("COUNTRY_APPLICABLE").toString());
			bean.setApplicableCity(res.get("CITY_APPLICABLE") == null ? "": res.get("CITY_APPLICABLE").toString());
			bean.setEffectiveDate(res.get("EFFECTIVE_DATE") == null ? "" : res.get("EFFECTIVE_DATE").toString());
			bean.setRemarks(res.get("REMARKS") == null ? "" : res.get("REMARKS").toString());
			bean.setDescription(res.get("DESCRIPTION") == null ? "" : res.get("DESCRIPTION").toString());
			bean.setStatus(res.get("STATUS") == null ? "" : res.get("STATUS").toString());
			bean.setVehicleCount(res.get("VEHICLE_COUNT") == null ? "" : res.get("VEHICLE_COUNT").toString());
			if (StringUtils.isNotBlank(bean.getDescription())) {
				String[] subStr = bean.getDescription().split("~");
				List<String> desc = new ArrayList();
				for (int i = 0; i < subStr.length; i++) {
					desc.add(subStr[i]);
				}
				bean.setDescriptionList(desc);
			}
		} catch (Exception e) {
			LogManager.debug("Exception Occured @ editSubPolicyDetails " + e);
			e.printStackTrace();
		}
	}

	public long diffInDays(String startDate, String endDate) {
		long result = 0;
		try {
			Date date = new Date();
			Calendar cal1 = Calendar.getInstance();
			Calendar cal2 = Calendar.getInstance();
			SimpleDateFormat sfd = new SimpleDateFormat("dd/MM/yyyy");
			cal1.setTime(sfd.parse(startDate));
			if (StringUtils.isBlank(endDate))
				cal2.setTime(sfd.parse(sfd.format(date)));
			else
				cal2.setTime(sfd.parse(endDate));
			long milis1 = cal1.getTimeInMillis();
			long milis2 = cal2.getTimeInMillis();
			long diff = milis2 - milis1;
			result = diff / (24 * 60 * 60 * 1000);
		} catch (Exception e) {
			LogManager.debug("Exception Occured @ " + e);
			e.printStackTrace();
		}
		return result;
	}

	public void getComprehensiveRateDropDown(MotorAdminBeanNew bean,
			String branchCode) {
		try {
			bean.setComprehensiveRateList(getComprehensiveRateList(bean));
			String query = getQuery("GET_SUBPOLICY_HEADER_DETAILS");
			LogManager.info("Query => " + query);
			Object[] args = new Object[] { bean.getPolicyType(),
					bean.getVehicleType(), bean.getBodyType() };
			LogManager.info("Arguments => " + StringUtils.join(args, ","));
			bean.setModalHeaderList(this.mytemplate.queryForList(query, args));
		} catch (Exception e) {
			LogManager.debug("Exception Occured @ getComprehensiveRateDropDown"
					+ e);
			e.printStackTrace();
		}
	}

	public String insertinsCompreRate(MotorAdminBeanNew bean) {
		String result = "";
		String fromReq="";
		try {
			String query = "";
			String amendId = "";
			Object[] args = null;
			if ("editCompreRate".equalsIgnoreCase(bean.getMode())) {
				query = getQuery("GET_EDIT_COMPRERATE_AMEND");
				args = new Object[] { bean.getRateId(), bean.getSubRateId() };
				LogManager.info("Query => " + query);
				LogManager.info("Arguments => " + StringUtils.join(args, ","));
				List<Map<String, Object>> res = this.mytemplate.queryForList(query, args);
				if (res.size() > 0)
					amendId = res.get(0).get("AMEND_ID") == null ? "" : res
							.get(0).get("AMEND_ID").toString();
			}
			if ("addCompreRate".equalsIgnoreCase(bean.getMode())|| ("editCompreRate".equalsIgnoreCase(bean.getMode()) && StringUtils.isBlank(amendId))) {
				query = getQuery("INS_COMPRE_RATE");
				args = new Object[25];
				args[0] = bean.getSubRateId();
				if ("addCompreRate".equalsIgnoreCase(bean.getMode())) {
					String sql = getQuery("GET_MAX_MOTOR_COMPRE_RATE_ID");
					int rateId = this.mytemplate.queryForInt(sql);
					args[1] = rateId;
					args[2] = rateId;
				} else {
					fromReq="insert";
					args[1] = bean.getRateId();
					args[2] = bean.getRateId();
				}
				args[3] = bean.getSeatingTonStart();
				args[4] = bean.getSeatingTonEnd();
				args[5] = bean.getCCGVWStartRange();
				args[6] = bean.getCCGVWEndRange();
				args[7] = bean.getUAELiscenceStart();
				args[8] = bean.getUAELiscenceEnd();
				args[9] = bean.getDriverAgeFrom();
				args[10] = bean.getDriverAgeTo();
				args[11] = bean.getSuminsuredStart();
				args[12] = bean.getSuminsuredEnd();
				args[13] = bean.getPolicyStartDate();
				args[14] = bean.getPolicyEndDate();
				args[15] = bean.getMotorRate();
				args[16] = bean.getMinimumPremium();
				args[17] = bean.getOnlinePercent();
				args[18] = bean.getDeductionPercent();
				args[19] = bean.getDeductionAmount();
				args[20] = bean.getDeductionDesc();
				args[21] = bean.getStatus();
				args[22] = bean.getEffectiveDate();
				args[23] = bean.getRemarks();
				args[24] = bean.getUserNameLogin();
			} else if ("editCompreRate".equalsIgnoreCase(bean.getMode())&& StringUtils.isNotBlank(amendId)) {
				query = getQuery("UPD_COMPRE_RATE");
				args = new Object[25];
				args[0] = bean.getSeatingTonStart();
				args[1] = bean.getSeatingTonEnd();
				args[2] = bean.getCCGVWStartRange();
				args[3] = bean.getCCGVWEndRange();
				args[4] = bean.getUAELiscenceStart();
				args[5] = bean.getUAELiscenceEnd();
				args[6] = bean.getDriverAgeFrom();
				args[7] = bean.getDriverAgeTo();
				args[8] = bean.getSuminsuredStart();
				args[9] = bean.getSuminsuredEnd();
				args[10] = bean.getPolicyStartDate();
				args[11] = bean.getPolicyEndDate();
				args[12] = bean.getMotorRate();
				args[13] = bean.getMinimumPremium();
				args[14] = bean.getOnlinePercent();
				args[15] = bean.getDeductionPercent();
				args[16] = bean.getDeductionAmount();
				args[17] = bean.getDeductionDesc();
				args[18] = bean.getStatus();
				args[19] = bean.getEffectiveDate();
				args[20] = bean.getRemarks();
				args[21] = bean.getUserNameLogin();
				args[22] = bean.getSubRateId();
				args[23] = bean.getRateId();
				args[24] = amendId;
			}
			LogManager.info("Query => " + query);
			LogManager.info("Arguments => " + StringUtils.join(args, ","));
			this.mytemplate.update(query, args);
			if("insert".equalsIgnoreCase(fromReq)){
				String query12="";
				query12 = getQuery("UPD_COMPRE_ENDDATE");
				this.mytemplate.update(query12, new Object[]{bean.getRateId(),bean.getRateId(),bean.getRateId(),bean.getRateId()});
			}
		} catch (Exception e) {
			LogManager.debug("Exception Occured @ insertinsCompreRate " + e);
			e.printStackTrace();
			result = e.getMessage();
		}
		return result;
	}

	public int checkCombinationOfSubPolicy(MotorAdminBeanNew bean) {
		int result = 0;
		try {
			String query = getQuery("CHECK_SUB_POLICY_COMBINATION");
			Object[] args = null;
			if ("editSubPolicy".equalsIgnoreCase(bean.getMode())) {
				query += "AND MSM.SUB_RATE_ID ! = ?";
				args = new Object[] { bean.getPolicyType(), bean.getBodyType(),
						bean.getVehicleStartAge(), bean.getVehicleEndAge(),
						bean.getVehicleCount(), bean.getSubRateId() };
			} else {
				args = new Object[] { bean.getPolicyType(), bean.getBodyType(),
						bean.getVehicleStartAge(), bean.getVehicleEndAge(),
						bean.getVehicleCount() };
			}
			LogManager.info("Query => " + query);
			LogManager.info("Arguments => " + StringUtils.join(args, ","));
			result = this.mytemplate.queryForInt(query, args);
		} catch (Exception e) {
			LogManager.debug("Exception Occured @ checkCombinationOfSubPolicy "
					+ e);
		}
		return result;
	}

	public void getEditCompreRateDetails(MotorAdminBeanNew bean) {
		try {
			String query = getQuery("EDIT_COMPRE_RATE");
			Object args[] = { bean.getRateId(), bean.getSubRateId() };
			LogManager.info("Query => " + query);
			LogManager.info("Arguments => " + StringUtils.join(args, ","));
			Map<String, Object> res = this.mytemplate.queryForMap(query, args);
			if (res.size() > 0) {
				bean.setSeatingTonStart(res.get("SEATING_TON_START") == null ? "": res.get("SEATING_TON_START").toString());
				bean.setSeatingTonEnd(res.get("SEATING_TON_END") == null ? "": res.get("SEATING_TON_END").toString());
				bean.setCCGVWStartRange(res.get("CC_GVW_START_RANGE") == null ? "": res.get("CC_GVW_START_RANGE").toString());
				bean.setCCGVWEndRange(res.get("CC_GVW_END_RANGE") == null ? "": res.get("CC_GVW_END_RANGE").toString());
				bean.setUAELiscenceStart(res.get("UAE_LISCENCE_START") == null ? "": res.get("UAE_LISCENCE_START").toString());
				bean.setUAELiscenceEnd(res.get("UAE_LISCENCE_END") == null ? "": res.get("UAE_LISCENCE_END").toString());
				bean.setDriverAgeFrom(res.get("DRIVER_AGE_FROM") == null ? "": res.get("DRIVER_AGE_FROM").toString());
				bean.setDriverAgeTo(res.get("DRIVER_AGE_TO") == null ? "" : res.get("DRIVER_AGE_TO").toString());
				bean.setSuminsuredStart(res.get("SUMINSURED_START") == null ? "": res.get("SUMINSURED_START").toString());
				bean.setSuminsuredEnd(res.get("SUMINSURED_END") == null ? "": res.get("SUMINSURED_END").toString());
				bean.setPolicyStartDate(res.get("POLICY_START_DATE") == null ? "": res.get("POLICY_START_DATE").toString());
				bean.setPolicyEndDate(res.get("POLICY_END_DATE") == null ? "": res.get("POLICY_END_DATE").toString());
				bean.setMotorRate(res.get("MOTOR_RATE") == null ? "" : res.get("MOTOR_RATE").toString());
				bean.setMinimumPremium(res.get("MINIMUM_PREMIUM") == null ? "": res.get("MINIMUM_PREMIUM").toString());
				bean.setOnlinePercent(res.get("ONLINE_PERCENT") == null ? "": res.get("ONLINE_PERCENT").toString());
				bean.setDeductionPercent(res.get("DEDUCTION_PERCENT") == null ? "": res.get("DEDUCTION_PERCENT").toString());
				bean.setDeductionAmount(res.get("DEDUCTION_AMOUNT") == null ? "": res.get("DEDUCTION_AMOUNT").toString());
				bean.setDeductionDesc(res.get("DEDUCTION_DESC") == null ? "": res.get("DEDUCTION_DESC").toString());
				bean.setStatus(res.get("STATUS") == null ? "" : res.get("STATUS").toString());
				bean.setEffectiveDate(res.get("EFFECTIVE_DATE") == null ? "": res.get("EFFECTIVE_DATE").toString());
				bean.setRemarks(res.get("REMARKS") == null ? "" : res.get("REMARKS").toString());
				bean.setUserNameLogin(res.get("AGENCY_CODE")==null?"":res.get("AGENCY_CODE").toString());
			}

		} catch (Exception e) {
			LogManager.debug("Exception Occured @ getEditCompreRateDetails "
					+ e);
			e.printStackTrace();
		}
	}

	public List<Map<String, Object>> getMakeList(MotorAdminBeanNew bean) {
		List<Map<String, Object>> result = null;
		try {
			String query = getQuery("GET_MAKE_MASTER_LIST");
			Object args[] = { branchCode };
			LogManager.info("Query => " + query);
			LogManager.info("Arguments = > " + StringUtils.join(args, ","));
			result = this.mytemplate.queryForList(query, args);
		} catch (Exception e) {
			LogManager.info("Exception Occured @ getMakeList ");
			e.printStackTrace();
		}
		return result;
	}

	public List<Map<String, Object>> getModelList(MotorAdminBeanNew bean) {
		List<Map<String, Object>> result = null;
		try {
			String query = getQuery("GET_MODEL_MASTER_LIST");
			Object args[] = { branchCode, bean.getMakeId() };
			LogManager.info("Query => " + query);
			LogManager.info("Arguments = > " + StringUtils.join(args, ","));
			result = this.mytemplate.queryForList(query, args);
		} catch (Exception e) {
			LogManager.info("Exception Occured @ getMakeList ");
			e.printStackTrace();
		}
		return result;
	}

	public List<Map<String, Object>> getDropDown(MotorAdminBeanNew bean,
			String type) {
		List<Map<String, Object>> result = null;
		try {
			String query = "";
			Object[] args = null;
			if ("vehicleType".equalsIgnoreCase(type)) {
				query = getQuery("GET_VEHICLE_TYPE_DROP_DOWN");
				args = new Object[] { branchCode };
			} else if ("policyType".equalsIgnoreCase(type)) {
				query = getQuery("GET_POLICY_TYPE_LIST_ADMIN");
				args = new Object[] { branchCode };
			} else if ("bodyType".equalsIgnoreCase(type)) {
				query = getQuery("GET_MOTOR_BODY_TYPE_ADMIN");
				args = new Object[] { bean.getVehicleType() };
			} else if ("make".equalsIgnoreCase(type)) {
				query = getQuery("GET_MAKE_DROP_DOWN");
				args = new Object[] { branchCode };
			} else if ("country".equalsIgnoreCase(type)) {
				query = getQuery("GET_COUNTRY_DROP_DOWN_LIST");
				args = new Object[] { branchCode };
			} else if ("city".equalsIgnoreCase(type)) {
				query = getQuery("GET_VEHICLE_APPLICABLE_CITY_LIST");
				args = new Object[] { branchCode };
			} else if ("mainPolicyType".equalsIgnoreCase(type)) {
				query = getQuery("GET_MAIN_POLICY_TYPE_LIST");
				args = new Object[] {};
			} else if ("itemType".equalsIgnoreCase(type)) {
				query = getQuery("GET_ITEM_TYPE");
				args = new Object[] {};
			} else if ("bodyTypeWithOutVehicleType".equalsIgnoreCase(type)) {
				query = getQuery("GET_MOTOR_BODY_TYPE_WTVT");
				args = new Object[] {};
			}else if("NCBVehicleUsage".equalsIgnoreCase(type)){
				query=getQuery("GET_NCB_VEHICLE_USAGE");
				args = new Object[]{};
			}else if("DocApp".equalsIgnoreCase(type)){
				query=getQuery("GET_DOC_APP_DRPDWN");
				args = new Object[]{};
			}else if("usernamelogin".equalsIgnoreCase(type)){
				query=getQuery("GET_USERNAME_LOGIN_DRPDWN");
				args = new Object[]{};
			}else if("usernameloginn".equalsIgnoreCase(type)){
				query=getQuery("GET_USERNAME_LOGIN_DRPDWNN");
				args = new Object[]{};
			}else if("usernamelogin1".equalsIgnoreCase(type)){
				query=getQuery("GET_USERNAME_LOGIN_DRPDWNN_EDIT");
				args = new Object[]{};
			}else if("paymentType".equalsIgnoreCase(type)){
				query=getQuery("GET_PAYMENT_TYPE");
				args = new Object[]{};
			}else if("groupOpcover".equalsIgnoreCase(type)){
				query=getQuery("GET_GROUP_OPCOVER_DROP_DOWNN");
				args = new Object[]{branchCode};
			}
			LogManager.info("Query => " + query);
			LogManager.info("Arguments = > " + StringUtils.join(args, ","));
			result = this.mytemplate.queryForList(query, args);
		} catch (Exception e) {
			LogManager.debug("Exception Occured @ getDropDown " + e);
			e.printStackTrace();
		}
		return result;
	}

	public List<Map<String, Object>> getHeaderDetails(MotorAdminBeanNew bean,
			String type) {
		List<Map<String, Object>> result = null;
		try {
			String query = "";
			Object[] args = null;
			if ("subPolicyList".equalsIgnoreCase(type)) {
				query = getQuery("GET_SUBPOLICY_HEADER_DETAILS");
				args = new Object[] { bean.getPolicyType(),
						bean.getVehicleType(), bean.getBodyType() };
			} else if ("model".equalsIgnoreCase(type)) {
				query = getQuery("GET_MODEL_HEADER_LIST");
				args = new Object[] { bean.getMakeId() };
			} else if ("thirdParty".equalsIgnoreCase(type)) {
				query = getQuery("GET_THIRD_PARTY_HEADER_LIST");
				args = new Object[] { bean.getVehicleType(), bean.getBodyType() };
			}
			LogManager.info("Query => " + query);
			LogManager.info("Arguments = > " + StringUtils.join(args, ","));
			result = this.mytemplate.queryForList(query, args);
		} catch (Exception e) {
			LogManager.debug("Exception Occured @ getDropDown " + e);
			e.printStackTrace();
		}
		return result;
	}
	public String insertMakeMaster(MotorAdminBeanNew bean) {
		String result="";
		try {
			String query = "";
			String amendId = "";
			Object[] args = null;
			if ("editMake".equalsIgnoreCase(bean.getMode())) {
				query = getQuery("GET_EDIT_MAKE_AMEND");
				args = new Object[] { bean.getMakeId(), branchCode };
				LogManager.info("Query => " + query);
				LogManager.info("Arguments => " + StringUtils.join(args, ","));
				List<Map<String, Object>> res = this.mytemplate.queryForList(query, args);
				if (res.size() > 0)
					amendId = res.get(0).get("AMEND_ID") == null ? "" : res.get(0).get("AMEND_ID").toString();
			}
			if ("addMake".equalsIgnoreCase(bean.getMode())|| ("editMake".equalsIgnoreCase(bean.getMode()) && StringUtils.isBlank(amendId))) {
				query = getQuery("INS_MAKE_MASTER");
				args = new Object[12];
				if ("addMake".equalsIgnoreCase(bean.getMode())) {
					String sql = getQuery("GET_MAX_MAKE_ID");
					String makeId = (String) this.mytemplate.queryForObject(
							sql, String.class);
					args[0] = makeId;
					args[6] = makeId;
				} else {
					args[0] = bean.getMakeId();
					args[6] = bean.getMakeId();
				}
				args[1] = bean.getMakeName();
				args[2] = bean.getStatus();
				args[3] = bean.getEffectiveDate();
				args[4] = bean.getExpiryDate();
				args[5] = bean.getRemarks();
				args[7] = branchCode;
				args[8] = bean.getMakeNameArabic()==null?"":bean.getMakeNameArabic();
				args[9] = bean.getReferralStatus();
				args[10] = bean.getApplicableCountry();
				args[11] = bean.getCoreAppCode();
			} else if ("editMake".equalsIgnoreCase(bean.getMode())&& StringUtils.isNotBlank(amendId)) {
				query = getQuery("UPD_MAKE_MASTER");
				args = new Object[12];
				args[0] = bean.getMakeName();
				args[1] = bean.getStatus();
				args[2] = bean.getEffectiveDate();
				args[3] = bean.getExpiryDate();
				args[4] = bean.getRemarks();
				args[5] = branchCode;
				args[6] = bean.getMakeNameArabic()==null?"":bean.getMakeNameArabic();
				args[7] = bean.getReferralStatus();
				args[8] = bean.getApplicableCountry();
				args[9] = bean.getCoreAppCode();
				args[10] = bean.getMakeId();
				args[11] = amendId;
			}
			LogManager.info("Query => " + query);
			LogManager.info("Arguments => " + StringUtils.join(args, ","));
			this.mytemplate.update(query, args);

		} catch (Exception e) {
			LogManager.debug("Exception Occured @ insertMakeMaster " + e);
			e.printStackTrace();
			result = e.toString();
		}
		return result;
	}

	public void getEditMakeDetails(MotorAdminBeanNew bean) {
		try {
			String query = getQuery("EDIT_MAKE_MASTER");
			Object args[] = { bean.getMakeId(), branchCode };
			LogManager.info("Query => " + query);
			LogManager.info("Arguments => " + StringUtils.join(args, ","));
			Map<String, Object> res = this.mytemplate.queryForMap(query, args);
			if (res.size() > 0) {
				bean.setMakeName(res.get("MAKE_NAME") == null ? "" : res.get("MAKE_NAME").toString());
				bean.setMakeNameArabic(res.get("MAKE_NAME_ARABIC") == null ? "": res.get("MAKE_NAME_ARABIC").toString());
				bean.setApplicableCountry(res.get("COUNTRY_ID") == null ? "": res.get("COUNTRY_ID").toString());
				bean.setEffectiveDate(res.get("EFFECTIVE_DATE") == null ? "": res.get("EFFECTIVE_DATE").toString());
				bean.setExpiryDate(res.get("EXPIRY_DATE") == null ? "" : res.get("EXPIRY_DATE").toString());
				bean.setRemarks(res.get("REMARKS") == null ? "" : res.get("REMARKS").toString());
				bean.setReferralStatus(res.get("REFERRAL_STATUS") == null ? "": res.get("REFERRAL_STATUS").toString());
				bean.setStatus(res.get("STATUS") == null ? "" : res.get("STATUS").toString());
				bean.setCoreAppCode(res.get("CORE_CODE") == null ? "" : res.get("CORE_CODE").toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public String insertModelMaster(MotorAdminBeanNew bean) {
		String result="";
		try {
			String query = "";
			String amendId = "";
			Object[] args = null;
			if ("editModel".equalsIgnoreCase(bean.getMode())) {
				query = getQuery("GET_EDIT_MODEL_AMEND_ID");
				args = new Object[] { bean.getModelId(), branchCode };
				LogManager.info("Query => " + query);
				LogManager.info("Arguments => " + StringUtils.join(args, ","));
				List<Map<String, Object>> res = this.mytemplate.queryForList(query, args);
				if (res.size() > 0)
					amendId = res.get(0).get("AMEND_ID") == null ? "" : res.get(0).get("AMEND_ID").toString();
			}
			if ("addModel".equalsIgnoreCase(bean.getMode()) || ("editModel".equalsIgnoreCase(bean.getMode()) && StringUtils.isBlank(amendId))) {
				query = getQuery("INS_MODEL_MASTER");
				args = new Object[12];
				if ("addModel".equalsIgnoreCase(bean.getMode())) {
					String sql = getQuery("GET_MAX_MODEL_ID");
					String modelId = (String) this.mytemplate.queryForObject(sql, String.class);
					args[0] = modelId;
					args[7] = modelId;
				} else {
					args[0] = bean.getModelId();
					args[8] = bean.getModelId();
				}
				args[1] = bean.getMakeId();
				args[2] = bean.getModelName();
				args[3] = bean.getStatus();
				args[4] = bean.getEffectiveDate();
				args[5] = bean.getExpiryDate();
				args[6] = bean.getRemarks();
				args[8] = branchCode;
				args[9] = bean.getModelNameArabic()==null?"":bean.getModelNameArabic();
				args[10] = bean.getReferralStatus();
				args[11] = bean.getCoreAppCode();
			} else if ("editModel".equalsIgnoreCase(bean.getMode())&& StringUtils.isNotBlank(amendId)) {
				query = getQuery("UPD_MODEL_MASTER");
				args = new Object[12];
				args[0] = bean.getMakeId();
				args[1] = bean.getModelName();
				args[2] = bean.getStatus();
				args[3] = bean.getEffectiveDate();
				args[4] = bean.getExpiryDate();
				args[5] = bean.getRemarks();
				args[6] = branchCode;
				args[7] = bean.getModelNameArabic()==null?"":bean.getModelNameArabic();
				args[8] = bean.getReferralStatus();
				args[9] = bean.getCoreAppCode();
				args[10] = bean.getModelId();
				args[11] = amendId;
			}
			LogManager.info("Query => " + query);
			LogManager.info("Arguments => " + StringUtils.join(args, ","));
			this.mytemplate.update(query, args);
		} catch (Exception e) {
			LogManager.debug("Exception Occured @ insertModelMaster " + e);
			e.printStackTrace();
			result=e.toString();
		}
		return result;
	}

	public void getEditModelDetails(MotorAdminBeanNew bean) {
		try {
			String query = getQuery("EDIT_MODEL_MASTER");
			Object args[] = { bean.getModelId(),branchCode };
			LogManager.info("Query => " + query);
			LogManager.info("Arguments => " + StringUtils.join(args, ","));
			Map<String, Object> res = this.mytemplate.queryForMap(query, args);
			if (res.size() > 0) {
				bean.setModelName(res.get("MODEL_NAME") == null ? "" : res.get("MODEL_NAME").toString());
				bean.setModelNameArabic(res.get("MODEL_NAME_ARABIC") == null ? "": res.get("MODEL_NAME_ARABIC").toString());
				bean.setEffectiveDate(res.get("EFFECTIVE_DATE") == null ? "": res.get("EFFECTIVE_DATE").toString());
				bean.setExpiryDate(res.get("EXPIRY_DATE") == null ? "" : res.get("EXPIRY_DATE").toString());
				bean.setRemarks(res.get("REMARKS") == null ? "" : res.get("REMARKS").toString());
				bean.setReferralStatus(res.get("REFERRAL_STATUS") == null ? "": res.get("REFERRAL_STATUS").toString());
				bean.setStatus(res.get("STATUS") == null ? "" : res.get("STATUS").toString());
				bean.setCoreAppCode(res.get("CORE_CODE") == null ? "" : res.get("CORE_CODE").toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//Model Body Type Master
	public List<Map<String, Object>> getModelDetail(MotorAdminBeanNew bean) {
		List<Map<String, Object>> result = null;
		try {
			String query = getQuery("GET_MODEL_DETAIL_LIST");
			Object args[] = { branchCode,bean.getMakeId(),bean.getModelName()};
			LogManager.info("Query => " + query);
			LogManager.info("Arguments = > " + StringUtils.join(args, ","));
			result = this.mytemplate.queryForList(query, args);
		} catch (Exception e) {
			LogManager.info("Exception Occured @ getMakeList ");
			e.printStackTrace();
		}
		return result;
	}
	
	public void getEditModelDetail(MotorAdminBeanNew bean) {
		try {
			String query = getQuery("EDIT_MODEL_DETAIL");
			Object args[] = { branchCode,bean.getModelTypeId() };
			LogManager.info("Query => " + query);
			LogManager.info("Arguments => " + StringUtils.join(args, ","));
			Map<String, Object> res = this.mytemplate.queryForMap(query,args);
			if (res.size() > 0) {
				bean.setModelId(res.get("MODEL_ID") == null ? "" : res.get("MODEL_ID").toString());
				bean.setModelName(res.get("MODEL_NAME") == null ? "" : res.get("MODEL_NAME").toString());
				bean.setBodyType(res.get("BODY_ID") == null ? "" : res.get("BODY_ID").toString());
				bean.setRemarks(res.get("REMARKS") == null ? "" : res.get("REMARKS").toString());
				bean.setStatus(res.get("STATUS") == null ? "" : res.get("STATUS").toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String insertModelDetail(MotorAdminBeanNew bean) {
		String result="";
		try {
			Object[] args = null;
			if("add".equalsIgnoreCase(bean.getMode())) {
				query = getQuery("INS_MODEL_DETAIL");
				args = new Object[7];
				args[0] = bean.getModelId();
				args[1] = bean.getMakeId();
				args[2] = bean.getModelName();
				args[3] = bean.getBodyType();
				args[4] = bean.getStatus();
				args[5] = branchCode;
				args[6] = bean.getRemarks();
			} else if("edit".equalsIgnoreCase(bean.getMode())) {
				query = getQuery("UPD_MODEL_DETAIL");
				args = new Object[8];
				args[0] = bean.getMakeId();
				args[1] = bean.getModelName();
				args[2] = bean.getBodyType();
				args[3] = bean.getStatus();
				args[4] = branchCode;
				args[5] = bean.getRemarks();
				args[6] = bean.getModelId();
				args[7] = bean.getModelTypeId();
			}
			LogManager.info("Query => " + query);
			LogManager.info("Arguments => " + StringUtils.join(args, ","));
			this.mytemplate.update(query, args);
		} catch (Exception e) {
			LogManager.debug("Exception Occured @ insertModelDetail " + e);
			e.printStackTrace();
			result=e.toString();
		}
		return result;
	}
	
	public int checkModelBodyType(MotorAdminBeanNew bean) {
		int result=0;
		try{
			query=getQuery("CHECK_MODEL_DETAIL_MASTER");
			Object args[] = null;
			if("edit".equalsIgnoreCase(bean.getMode())){
				query += " AND MODEL_TYPE_ID !=?";
				args= new Object[]{bean.getModelId(),bean.getMakeId(),bean.getBodyType(),bean.getModelTypeId()};
			}else{
				args= new Object[]{bean.getModelId(),bean.getMakeId(),bean.getBodyType()};	
			}
			LogManager.info("query =>"+ query);
			LogManager.info("args===>"+StringUtils.join(args,","));
			result = this.mytemplate.queryForInt(query,args);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
/*	public List<Map<String, Object>> MaxofTypeBodyId(MotorAdminBeanNew bean) {
		LogManager.info("Enter into MaxofTypeBodyId()");
		List<Map<String, Object>> result = null;
		try {
			String query = getQuery("GET_MAX_TYPE_BODY_ID");
			LogManager.info("query =>" + query);
			result = this.mytemplate.queryForList(query);
			if (result != null) {
				Map map = (Map) result.get(0);
				bean.setVehiclebodyTypeId(map.get("TYPE_OF_BODY_ID") == null ? "" : map.get("TYPE_OF_BODY_ID").toString());
			}
		} catch (Exception e) {
			LogManager.error("Exception occured @ MaxofTypeBodyId()" + e);
			e.printStackTrace();
		}
		LogManager.info("Exit from  MaxofTypeBodyId()");
		return result;
}*/
	
	//Document Master
	public List<Map<String, Object>> getDocument(MotorAdminBeanNew bean) {
		LogManager.info("Enter into getDocument()");
		List<Map<String, Object>> result = null;
		try {
			String query = getQuery("GET_DOCUMENT_MASTER");
			LogManager.info("query=>" + query);
			result = this.mytemplate.queryForList(query);
		} catch (Exception e) {
			LogManager.info("Exception Occured @ getDocument()");
			e.printStackTrace();
		}
		LogManager.info("Exit From getDocument()");
		return result;
	}
	
	/*public List<Map<String, Object>> getDocumentEdit(MotorAdminBeanNew bean) {
		LogManager.info("Enter into getDocumentEdit()");
		List<Map<String, Object>> result = null;
		try {
			String query = getQuery("GET_DOCUMENT_FOR_EDIT");
			LogManager.info("query=>" + query);
			Object args[] = new Object[1];
			args[0] = bean.getDocumentId();
			result = this.mytemplate.queryForList(query, args);
			if (result != null) {Map map = (Map) result.get(0);
				bean.setDocumentId(map.get("DOCUMENT_ID") == null ? "" : map.get("DOCUMENT_ID").toString());
				bean.setDocumentDescription(map.get("DOCUMENT_DESC") == null ? "": map.get("DOCUMENT_DESC").toString());
				bean.setPolicyType(map.get("POLICY_TYPE") == null ? "" : map.get("POLICY_TYPE").toString());
				bean.setMandatoryStatus(map.get("MANDATORY_STATUS") == null ? "": map.get("MANDATORY_STATUS").toString());
				bean.setStatus(map.get("STATUS") == null ? "" : map.get("STATUS").toString());
				bean.setRemarks(map.get("REMARKS") == null ? "" : map.get("REMARKS").toString());
				bean.setDisplayOrder(map.get("DISPLAY_ORDER") == null ? "": map.get("DISPLAY_ORDER").toString());
				} catch (Exception e) {
			LogManager.info("Exception Occured @ getDocumentEdit()" + e);
			e.printStackTrace();
		}
		LogManager.info("Exit from getDocumentEdit()");
		return result;
	}*/
	
		public List<Map<String,Object>> getDocumentEdit(MotorAdminBeanNew bean){
			LogManager.info("Enter into getDocumentEdit()");
			List<Map<String,Object>> result = null;
				try{
				String query = getQuery("GET_DOCUMENT_FOR_EDIT");
				LogManager.info("query=>" +query);
				Object args[] = new Object[1];
				args[0] = bean.getDocumentId();
				result = this.mytemplate.queryForList(query,args);
				if(result != null){
				Map map = (Map)result.get(0);
				bean.setDocumentId(map.get("DOCUMENT_ID")== null?"":map.get("DOCUMENT_ID").toString());
				bean.setDocumentDescription(map.get("DOCUMENT_DESC")== null?"":map.get("DOCUMENT_DESC").toString());
				/*bean.setDocpolicyType(map.get("POLICY_TYPE")== null?"":map.get("POLICY_TYPE").toString());*/
				bean.setDocpolicyType(map.get("POLICY_TYPE").toString().trim().replaceAll(" ", "").split(","));
				bean.setMandatoryStatus(map.get("MANDATORY_STATUS")==null?"":map.get("MANDATORY_STATUS").toString());
				bean.setStatus(map.get("STATUS")== null?"":map.get("STATUS").toString());
				bean.setRemarks(map.get("REMARKS")== null?"":map.get("REMARKS").toString());
				bean.setDisplayOrder(map.get("DISPLAY_ORDER")== null?"":map.get("DISPLAY_ORDER").toString());
				bean.setDocumentApplicable(map.get("DOC_APPLICABLE")==null?"":map.get("DOC_APPLICABLE").toString());
				}
					}catch(Exception e){
				LogManager.info("Exception Occured @ getDocumentEdit()" +e);
				e.printStackTrace();
				}
			LogManager.info("Exit from getDocumentEdit()");
			return result;
			}

	public List<Map<String, Object>> getMaxDocumentId(MotorAdminBeanNew bean) {
		LogManager.info("Enter into getMaxDocumentId()");
		List<Map<String, Object>> result = null;
		try {
			String query = getQuery("GET_MAX_DOCUMENT_ID");
			LogManager.info("query =>" + query);
			result = this.mytemplate.queryForList(query);
			if (result != null) {
				Map map = (Map) result.get(0);
				bean.setDocumentId(map.get("DOCUMENT_ID") == null ? "" : map
						.get("DOCUMENT_ID").toString());
			}
		} catch (Exception e) {
			LogManager.error("Exception occured @ getMaxDocumentId()" + e);
			e.printStackTrace();
		}
		LogManager.info("Exit from  getMaxDocumentId()");
		return result;
	}

	public boolean getDocumentUpdate(MotorAdminBeanNew bean) {
		LogManager.info("Enter into getDocumentUpdate()");
		boolean result = false;
		try {
			if ("edit".equalsIgnoreCase(bean.getMode())) {
				String query = getQuery("DOCUMENT_UPDATE");
				LogManager.info("query =>" + query);
				Object args[] = new Object[8];
				args[0] = bean.getDocumentDescription();
				args[1] = bean.getDocPolicy();
				args[2] = bean.getMandatoryStatus();
				args[3] = bean.getStatus();
				args[4] = bean.getRemarks();
				args[5] = bean.getDisplayOrder();
				args[6] = bean.getDocumentApplicable();
				args[7] = bean.getDocumentId();
				LogManager.info("Arguments =>" + StringUtils.join(args, ","));
				this.mytemplate.update(query, args);
			} else if ("add".equalsIgnoreCase(bean.getMode())) {
				String query = getQuery("DOCUMENT_INSERT");
				LogManager.info("query =>" + query);
				Object args[] = new Object[8];
				args[0] = bean.getDocumentId();
				args[1] = bean.getDocumentDescription();
				args[2] = bean.getDocPolicy();
				args[3] = bean.getMandatoryStatus();
				args[4] = bean.getStatus();
				args[5] = bean.getRemarks();
				args[6] = bean.getDisplayOrder();
				args[7] = bean.getDocumentApplicable();
				LogManager.info("Arguments =>" +StringUtils.join(args,","));
				this.mytemplate.update(query,args);
			}
		} catch (Exception e) {
			LogManager.error("Exception occured @ getDocumentUpdate()");
			e.printStackTrace();
		}
		LogManager.info("Exit from getDocumentUpdate()");
		return result;
	}

	public List<Map<String, Object>> getOpCoverList(MotorAdminBeanNew bean) {
		List<Map<String, Object>> result = null;
		try {
			String query = getQuery("GET_OPCOVER_MASTER_LIST");
			Object args[] = { branchCode };
			LogManager.info("Query => " + query);
			LogManager.info("Arguments = > " + StringUtils.join(args, ","));
			result = this.mytemplate.queryForList(query, args);
		} catch (Exception e) {
			LogManager.info("Exception Occured @ getOpCoverList ");
			e.printStackTrace();
		}
		return result;
	}

	public void getEditOpCover(MotorAdminBeanNew bean) {
		try {
			String query = getQuery("EDIT_OPCOVER_MASTER");
			Object args[] = { branchCode, bean.getOpCoverId() };
			LogManager.info("Query => " + query);
			LogManager.info("Arguments => " + StringUtils.join(args, ","));
			Map<String, Object> res = this.mytemplate.queryForMap(query, args);
			if (res.size() > 0) {
				bean.setOpCoverDesc(res.get("OPCOVER_DESC") == null ? "" : res.get("OPCOVER_DESC").toString());
				bean.setCoreAppCode(res.get("COREAPP_CODE") == null ? "" : res.get("COREAPP_CODE").toString());
				bean.setEffectiveDate(res.get("EFFECTIVE_DATE") == null ? "": res.get("EFFECTIVE_DATE").toString());
				bean.setPolicySubTypeId(res.get("POLICY_SUBTYPE_ID") == null ? "": res.get("POLICY_SUBTYPE_ID").toString());
				bean.setRemarks(res.get("REMARKS") == null ? "" : res.get("REMARKS").toString());
				bean.setStatus(res.get("STATUS") == null ? "" : res.get("STATUS").toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void insertinsOpCover(MotorAdminBeanNew bean) {
		try {
			String query = "";
			String amendId = "";
			Object[] args = null;
			if ("edit".equalsIgnoreCase(bean.getMode())) {
				query = getQuery("GET_EDIT_AMENDID_OPCOVER");
				args = new Object[] { bean.getOpCoverId(), branchCode };
				LogManager.info("Query => " + query);
				LogManager.info("Arguments => " + StringUtils.join(args, ","));
				List<Map<String, Object>> res = this.mytemplate.queryForList(query, args);
				if (res.size() > 0)
					amendId = res.get(0).get("AMEND_ID") == null ? "" : res.get(0).get("AMEND_ID").toString();
			}
			if ("add".equalsIgnoreCase(bean.getMode())|| ("edit".equalsIgnoreCase(bean.getMode()) && StringUtils.isBlank(amendId))) {
				query = getQuery("INSERT_OPCOVER_MASTER");
				args = new Object[10];
				if ("add".equalsIgnoreCase(bean.getMode())) {
					String sql = getQuery("GET_MAX_OPCOVERID");
					String opcoverId = (String) this.mytemplate.queryForObject(sql, String.class);
					args[0] = opcoverId;
					args[1] = opcoverId;
				} else {
					args[0] = bean.getOpCoverId();
					args[1] = bean.getOpCoverId();
				}
				args[2] = bean.getOpCoverDesc();
				args[3] = bean.getCoreAppCode();
				args[4] = bean.getStatus();
				args[5] = bean.getEffectiveDate();
				// args[6] = bean.getEntryDate();
				args[6] = bean.getRemarks();
				args[7] = productId;
				args[8] = branchCode;
				args[9] = bean.getPolicySubTypeId()==null?"":bean.getPolicySubTypeId();
			} else if ("edit".equalsIgnoreCase(bean.getMode())&& StringUtils.isNotBlank(amendId)) {
				query = getQuery("UPDATE_OPCOVER_MASTER");
				args = new Object[8];
				args[0] = bean.getOpCoverDesc();
				args[1] = bean.getCoreAppCode();
				args[2] = bean.getEffectiveDate();
				args[3] = bean.getRemarks();
				args[4] = bean.getStatus();
				args[5] = bean.getPolicySubTypeId()==null?"":bean.getPolicySubTypeId();
				args[6] = bean.getOpCoverId();
				args[7] = amendId;
			}
			LogManager.info("Query => " + query);
			LogManager.info("Arguments => " + StringUtils.join(args, ","));
			this.mytemplate.update(query, args);
		} catch (Exception e) {
			LogManager.debug("Exception Occured @ insertinsOpCover " + e);
			e.printStackTrace();
		}
	}

	public List<Map<String, Object>> getOpCoverDetailList(MotorAdminBeanNew bean) {
		List<Map<String, Object>> result = null;
		try {
			String query = getQuery("GET_OPCOVER_DETAIL_LIST");
			Object args[] = { bean.getOpCoverId() };
			LogManager.info("Query => " + query);
			LogManager.info("Arguments = > " + StringUtils.join(args, ","));
			result = this.mytemplate.queryForList(query, args);
		} catch (Exception e) {
			LogManager.info("Exception Occured @ getOpCoverDetailList ");
			e.printStackTrace();
		}
		return result;
	}

	public void getEditOpCoverDetail(MotorAdminBeanNew bean) {
		try {
			query = getQuery("EDIT_OPCOVER_DETAIL");
			Object args1[] = { bean.getOpCoverId(),bean.getOpCoverSubId() };
			LogManager.info("Query => " + query);
			LogManager.info("Arguments => " + StringUtils.join(args1, ","));
			Map<String, Object> res1 = this.mytemplate.queryForMap(query, args1);
			if (res1.size() > 0) {
				bean.setOpCoverAmount(res1.get("AMOUNT") == null ? "" : res1.get("AMOUNT").toString());
				bean.setStatus(res1.get("STATUS") == null ? "" : res1.get("STATUS").toString());
				bean.setGroupId(res1.get("GROUP_ID") == null?"":res1.get("GROUP_ID").toString());
				bean.setPolicySubTypeId(res1.get("POLICY_SUBTYPE_ID") == null?"":res1.get("POLICY_SUBTYPE_ID").toString());
				bean.setIsSelected(res1.get("IS_SELECTED") == null ? "" : res1.get("IS_SELECTED").toString());
				bean.setIsAddon(res1.get("IS_ADDON") == null ? "" : res1.get("IS_ADDON").toString());
				bean.setIsDeletable(res1.get("DELETABLE") == null ? "" : res1.get("DELETABLE").toString());
				bean.setIsCalcType(res1.get("CALC_TYPE") == null ? "" : res1.get("CALC_TYPE").toString());
				bean.setOpDesc(res1.get("DESCRIPTION") == null ? "" : res1.get("DESCRIPTION").toString());
				bean.setOpCoverRate(res1.get("RATE") == null ? "" : res1.get("RATE").toString());
				bean.setCoreAppCode(res1.get("COREAPP_CODE") == null ? "": res1.get("COREAPP_CODE").toString());
				bean.setStartDate(res1.get("STARTDATE") == null?"":res1.get("STARTDATE").toString());
				bean.setEndDate(res1.get("ENDDATE") == null?"":res1.get("ENDDATE").toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void insertinsOpCoverDetail(MotorAdminBeanNew bean) {
		try {
			String query1 = "";
			Object[] args1 = null;
			if ("edit".equalsIgnoreCase(bean.getMode())) {
				String id = "";
				String amend = "0";
				query1 = getQuery("UPDATE_OPCOVER_DETAIL_INSERT");
				args1 = new Object[16];
				args1[0] = bean.getOpCoverId();
				String sql = getQuery("GET_DETAILS_OPCOVERSUBID");
				Map<String, Object> res1 = this.mytemplate.queryForMap(sql,new Object[] { bean.getOpCoverSubId()});
				if (res1.size() > 0) {
					amend = res1.get("AMEND_ID") == null ? "0" : res1.get("AMEND_ID").toString();
				}
				args1[1] = bean.getOpCoverSubId();
				args1[2] = amend;
				args1[3] = bean.getStatus();
				if("Y".equalsIgnoreCase(bean.getIsCalcType())){
					args1[4] = bean.getOpCoverRate();
					args1[13] = "0";
				}
				if("N".equalsIgnoreCase(bean.getIsCalcType())){
					args1[4] = "1";
					args1[13] = bean.getOpCoverAmount();
				}
				args1[5] = bean.getGroupId();
				args1[6] = bean.getIsSelected();
				args1[7] = bean.getIsAddon();
				args1[8] = bean.getIsDeletable();
				args1[9] = bean.getIsCalcType();
				args1[10] = bean.getOpDesc();
				args1[11] = bean.getPolicySubTypeId();
				args1[12] = bean.getCoreAppCode();
				args1[14] = bean.getStartDate();
				args1[15] = bean.getEndDate();
			} else {
				query1 = getQuery("INSERT_OPCOVER_DETAIL");
				args1 = new Object[16];
				args1[0] = bean.getOpCoverId();
				String sql = getQuery("GET_MAX_OPCOVERSUBID");
				String id = (String) this.mytemplate.queryForObject(sql,String.class);
				args1[1] = id;
				args1[2] = "0";
				args1[3] = bean.getStatus();
				if("Y".equalsIgnoreCase(bean.getIsCalcType())){
					args1[4] = bean.getOpCoverRate();
					args1[13] = "0";
				}
				if("N".equalsIgnoreCase(bean.getIsCalcType())){
					args1[4] = "1";
					args1[13] = bean.getOpCoverAmount();
				}
				args1[5] = bean.getGroupId();
				args1[6] = bean.getIsSelected();
				args1[7] = bean.getIsAddon();
				args1[8] = bean.getIsDeletable();
				args1[9] = bean.getIsCalcType();
				args1[10] = bean.getOpDesc();
				args1[11] = bean.getPolicySubTypeId();
				args1[12] = bean.getCoreAppCode();
				args1[14] = bean.getStartDate();
				args1[15] = bean.getEndDate();
			}
			LogManager.info("Query => " + query1);
			LogManager.info("Arguments => " + StringUtils.join(args1, ","));
			this.mytemplate.update(query1, args1);
		} catch (Exception e) {
			LogManager.debug("Exception Occured @ insertinsOpCoverDetail " + e);
			e.printStackTrace();
		}
	}

	// BODY TYPE MASTER
	public List<Map<String, Object>> BodyTypeMasterList(MotorAdminBeanNew bean,
			String branchCode2) {
		List<Map<String, Object>> result = null;
		try {
			String query = getQuery("GET_BODYTYPE_MASTER_LIST");
			Object args[] = new Object[1];
			args[0] = branchCode;

			LogManager.info("Query => " + query);
			LogManager.info("Arguments = > " + StringUtils.join(args, ","));
			result = this.mytemplate.queryForList(query, args);
		} catch (Exception e) {
			LogManager.info("Exception Occured @ getMakeList ");
			e.printStackTrace();
		}
		return result;
	}

	public void editbodyTypeMaster(MotorAdminBeanNew bean) {
		try {
			String query = getQuery("EDIT_BODYTYPE_MASTER");
			Object args[] = new Object[2];
			args[0] = bean.getBodyTypeId();
			args[1] = bean.getBodyTypeId();
			LogManager.info("Query => " + query);
			LogManager.info("Arguments => " + StringUtils.join(args, ","));
			Map<String, Object> res = this.mytemplate.queryForMap(query, args);
			if (res.size() > 0) {
				bean.setBodyTypeName(res.get("BODY_NAME") == null ? "" : res.get("BODY_NAME").toString());
				bean.setCoreAppCode(res.get("COREAPP_CODE") == null ? "" : res.get("COREAPP_CODE").toString());
				bean.setEffectiveDate(res.get("EFFECTIVE_DATE") == null ? "": res.get("EFFECTIVE_DATE").toString());
				bean.setExpiryDate(res.get("EXPIRY_DATE") == null ? "" : res.get("EXPIRY_DATE").toString());
				bean.setRemarks(res.get("REMARKS") == null ? "" : res.get("REMARKS").toString());
				bean.setStatus(res.get("STATUS") == null ? "" : res.get("STATUS").toString());
				bean.setReferralStatus(res.get("REFERRAL_STATUS") == null ? "": res.get("REFERRAL_STATUS").toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String updatebodyTypeMaster(MotorAdminBeanNew bean) {
		String result = "";
		try {
			String query = "";
			String amendId = "";
			Object[] args = null;
			if ("update".equalsIgnoreCase(bean.getMode())) {
				query = getQuery("GET_EDIT_BODYTYPE_AMEND_ID");
				args = new Object[] { bean.getBodyTypeId() };
				LogManager.info("Query => " + query);
				LogManager.info("Arguments => " + StringUtils.join(args, ","));
				List<Map<String, Object>> res = this.mytemplate.queryForList(query, args);
				if (res.size() > 0)
					amendId = res.get(0).get("AMEND_ID") == null ? "" : res.get(0).get("AMEND_ID").toString();
			}
			if (("addnew".equalsIgnoreCase(bean.getMode()))) {
				query = getQuery("BODY_TYPE_INSERT");
				args = new Object[8];
				args[0] = "0";
				args[1] = bean.getBodyTypeName();
				args[2] = bean.getCoreAppCode();
				args[3] = bean.getStatus();
				args[4] = bean.getEffectiveDate();
				args[5] = bean.getRemarks();
				args[6] = branchCode;
				args[7] = bean.getReferralStatus();
				// args[7]=bean.getBodyTypeId();
			} else if ("update".equalsIgnoreCase(bean.getMode())&& StringUtils.isBlank(amendId)) {
				query = getQuery("BODY_TYPE_UPDATE_INSERT");
				args = new Object[9];
				args[0] = bean.getBodyTypeId();
				args[1] = bean.getBodyTypeName();
				args[2] = bean.getCoreAppCode();
				args[3] = bean.getStatus();
				args[4] = bean.getEffectiveDate();
				args[5] = bean.getRemarks();
				args[6] = branchCode;
				args[7] = bean.getBodyTypeId();
				args[8] = bean.getReferralStatus();
			} else if ("update".equalsIgnoreCase(bean.getMode())&& StringUtils.isNotBlank(amendId)) {
				query = getQuery("BODY_TYPE_UPDATE");
				args = new Object[9];
				/*
				 * if(diffInDate >= 0){ String sql
				 * =getQuery("GET_MAX_AMEND_ID_BODY_TYPE"); int amendId =
				 * this.mytemplate.queryForInt(sql,new
				 * Object[]{bean.getBodyTypeId()}); args[0] = amendId; }else{
				 * args[0] = "0"; }
				 */
				args[0] = bean.getBodyTypeName();
				args[1] = bean.getCoreAppCode();
				args[2] = bean.getStatus();
				args[3] = bean.getEffectiveDate();
				args[4] = bean.getRemarks();
				args[5] = branchCode;
				args[6] = bean.getReferralStatus();
				args[7] = bean.getBodyTypeId();
				args[8] = amendId;
			}
			LogManager.info("Query => " + query);
			LogManager.info("Arguments => " + StringUtils.join(args, ","));
			this.mytemplate.update(query, args);
		} catch (Exception e) {
			e.printStackTrace();
			result = e.toString();
		}
		return result;
	}

	public List<Map<String, Object>> vehiclelinkforBodyTypeMaster(
			MotorAdminBeanNew bean) {
		List<Map<String, Object>> result = null;
		try {
			String query = getQuery("VEHICLE_LINK_LIST_BODY_TYPE_MASTER");
			Object args[] = new Object[1];
			if(bean.getBodyTypeId() != null){
				args[0] = bean.getBodyTypeId();
			}
			else{
				args[0] = bean.getVehiclebodyTypeId();
			}
			LogManager.info("Query => " + query);
			LogManager.info("Arguments = > " + StringUtils.join(args, ","));
			result = this.mytemplate.queryForList(query, args);
		} catch (Exception e) {
			LogManager.info("Exception Occured @ getMakeList ");
			e.printStackTrace();
		}
		return result;
	}

	public void editvehiclebodytypeMaster(MotorAdminBeanNew bean) {
		try {
			String query = getQuery("VEHICLE_LINK_EDIT");
			Object args[] = new Object[1];
			if (bean.getVehiclebodyTypeId() != null) {
				args[0] = bean.getVehiclebodyTypeId();
			} else {
				args[0] = bean.getBodyTypeId();
			}
			LogManager.info("Query => " + query);
			LogManager.info("Arguments => " + StringUtils.join(args, ","));
			Map<String, Object> res = this.mytemplate.queryForMap(query, args);
			if (res.size() > 0) {
				bean.setVehicleIdforBodyType(res.get("VTYPE_ID") == null ? "": res.get("VTYPE_ID").toString());
				bean.setBodyTypeName(res.get("TYPE_OF_BODY_NAME") == null ? "": res.get("TYPE_OF_BODY_NAME").toString());
				// bean.setCoreAppCode(res.get("COREAPP_CODE")==null?"":res.get("COREAPP_CODE").toString());
				// bean.setEffectiveDate(res.get("EFFECTIVE_DATE")==null?"":res.get("EFFECTIVE_DATE").toString());
				// bean.setRemarks(res.get("REMARKS")==null?"":res.get("REMARKS").toString());
				bean.setStatus(res.get("STATUS") == null ? "" : res.get("STATUS").toString());
				bean.setBodyTypeId(res.get("BODY_ID") == null ? "": res.get("BODY_ID").toString());
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<Map<String, Object>> vehicleList(MotorAdminBeanNew bean) {
		List<Map<String, Object>> result = null;
		try {
			String query = getQuery("VEHICLE_LIST");
			LogManager.info("Query => " + query);
			result = this.mytemplate.queryForList(query);
		} catch (Exception e) {
			LogManager.info("Exception Occured @ getMakeList ");
			e.printStackTrace();
		}
		return result;
	}

	public int count(MotorAdminBeanNew bean) {
		int result = 0;
		String query = "";
		Object[] args=null;
		try {
			if(!"vehicleadd".equalsIgnoreCase(bean.getMode())){
				args= new Object[1];
				if (bean.getVehiclebodyTypeId() == null) {
					query = getQuery("VEHICLE_COUNT");
					args[0] = bean.getBodyTypeId();
					} 
				else {
					query = getQuery("VEHICLE_COUNT1");
					args[0] = bean.getVehiclebodyTypeId();
					}
				}
			else{
				args= new Object[2];
				query = getQuery("VEHICLE_COUNT2");
				
				if (bean.getVehiclebodyTypeId() == null) {
					args[0] = bean.getBodyTypeId();
					} 
				else {
					args[0] = bean.getVehiclebodyTypeId();
					}
				args[1] = bean.getVehicleIdforBodyType();
				
			}
			LogManager.info("Query => " + query);
			result = this.mytemplate.queryForInt(query, args);
		} catch (Exception e) {
			LogManager.info("Exception Occured @ getMakeList ");
			e.printStackTrace();
		}
		return result;
	}

	public void bodyName(MotorAdminBeanNew bean) {
		try {
			String query = getQuery("BODY_ADDED_VEHYCLE");
			Object args[] = new Object[1];
			args[0] = bean.getBodyTypeId();
			LogManager.info("Query => " + query);
			LogManager.info("Arguments => " + StringUtils.join(args, ","));
			Map<String, Object> res = this.mytemplate.queryForMap(query, args);
			if (res.size() > 0) {
				bean.setBodyTypeName(res.get("BODY_NAME") == null ? "" : res.get("BODY_NAME").toString());
				bean.setStatus(res.get("STATUS") == null ? "" : res.get("STATUS").toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void vehicleInsert(MotorAdminBeanNew bean) {
		try {
			// int count=count(bean);
			if (bean.getVehiclebodyTypeId() == null) {
				String query = getQuery("VEHICLE_INSERT");
				Object args[] = new Object[5];
				args[0] = bean.getVehicleIdforBodyType();
				args[1] = bean.getBodyTypeId();
				args[2] = "0";
				args[3] = bean.getStatus();
				args[4] = bean.getBodyTypeName();
				LogManager.info("Query => " + query);
				LogManager.info("Arguments => " + StringUtils.join(args, ","));
				this.mytemplate.update(query, args);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String vehicleInsertBodyTyeId(MotorAdminBeanNew bean) {
		String result = "";
		try {

			String query = getQuery("VEHICLE_BODY_TYPE_ID_INSERT");
			Object args[] = new Object[6];
			args[0] = bean.getVehicleIdforBodyType();
			args[1] = bean.getVehiclebodyTypeId();
			args[2] = bean.getVehiclebodyTypeId();
			args[3] = bean.getStatus();
			args[4] = bean.getBodyTypeName();
			args[5] = bean.getVehiclebodyTypeId();
			LogManager.info("Query => " + query);
			LogManager.info("Arguments => " + StringUtils.join(args, ","));
			this.mytemplate.update(query, args);
		} catch (Exception e) {
			e.printStackTrace();
		result = e.toString();
		}
		return result;
	}

	public int coreappcodecount(MotorAdminBeanNew bean) {
		int result = 0;
		String query = "";
		try {

			if (bean.getBodyTypeId().equalsIgnoreCase("")) {
				query = getQuery("CORE_APP_CODE_COUNT1");
				Object args[] = new Object[2];
				args[0] = branchCode;
				args[1] = bean.getCoreAppCode();
				LogManager.info("Query => " + query);
				result = this.mytemplate.queryForInt(query, args);
			} else {
				query = getQuery("CORE_APP_CODE_COUNT");
				Object args[] = new Object[3];
				args[0] = branchCode;
				args[1] = bean.getCoreAppCode();
				args[2] = bean.getBodyTypeId();
				LogManager.info("Query => " + query);
				result = this.mytemplate.queryForInt(query, args);
			}

		} catch (Exception e) {
			LogManager.info("Exception Occured @ getMakeList ");
			e.printStackTrace();
		}
		return result;
	}
	/*
	 * Getting Third Party List Based on Vehicle Body Type
	 */
	public List<Map<String, Object>> getThirdPartyList(MotorAdminBeanNew bean) {
		List<Map<String, Object>> result = null;
		try {
			String query = getQuery("GET_THIRD_PARTY_LIST");
			query += " ORDER BY THIRD_PARTY_ID DESC";
			Object[] args = new Object[] { branchCode, productId,bean.getBodyType()};
			LogManager.info("Query => " + query);
			LogManager.info("Arguments => " + StringUtils.join(args, ","));
			result = this.mytemplate.queryForList(query, args);
		} catch (Exception e) {
			LogManager.debug("Exception Occured @ getThirdPartyList => " + e);
			e.printStackTrace();
		}
		return result;
	}

	public int checkThirdPartyCoreCode(MotorAdminBeanNew bean) {
		int result = 0;
		try {
			String query = getQuery("CHECK_THIRD_PARTY_CORE_CODE");
			Object[] args;
			if ("edit".equalsIgnoreCase(bean.getMode())) {
				query += " AND THIRD_PARTY_ID !=?";
				args = new Object[] { bean.getCoreAppCode(), branchCode,bean.getThirdPartyId() };
			} else {
				args = new Object[] { bean.getCoreAppCode(), branchCode };
			}
			LogManager.info("Query => " + query);
			LogManager.info("Arguments => " + StringUtils.join(args, ","));
			result = this.mytemplate.queryForInt(query, args);
		} catch (Exception e) {
			LogManager.debug("Exception Occured @ checkCoreCode " + e);
			e.printStackTrace();
		}
		return result;
	}

	public int checkThirdPartyVehicleUsage(MotorAdminBeanNew bean) {
		int result = 0;
		try {
			String query = getQuery("CHECK_THIRD_PARTY_VEHICLE_USAGE");
			Object[] args;
			if ("edit".equalsIgnoreCase(bean.getMode())) {
				query += " AND THIRD_PARTY_ID !=?";
				args = new Object[] { bean.getVehicleType(), branchCode,
						bean.getThirdPartyId() };
			} else {
				args = new Object[] { bean.getVehicleType(), branchCode };
			}
			LogManager.info("Query => " + query);
			LogManager.info("Arguments => " + StringUtils.join(args, ","));
			result = this.mytemplate.queryForInt(query, args);
		} catch (Exception e) {
			LogManager.debug("Exception Occured @ checkCoreCode " + e);
			e.printStackTrace();
		}
		return result;
	}

	public int checkThirdPartySeating(MotorAdminBeanNew bean) {
		int result = 0;
		try {
			String query = getQuery("CHECK_THIRD_PARTY_SEATING_CYL");
			Object[] args;
			if ("edit".equalsIgnoreCase(bean.getMode())) {
				query += " AND THIRD_PARTY_ID !=? ";
				args = new Object[] { bean.getBodyType(),bean.getSeatingCylinderStart(),bean.getSeatingCylinderEnd(),branchCode, bean.getThirdPartyId() };
			} else {
				args = new Object[] { bean.getBodyType(),bean.getSeatingCylinderStart(),bean.getSeatingCylinderEnd(),branchCode };
			}
			LogManager.info("Query => " + query);
			LogManager.info("Arguments => " + StringUtils.join(args, ","));
			result = this.mytemplate.queryForInt(query, args);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public String insertThirdPartyMaster(MotorAdminBeanNew bean) {
		String result = "";
		try {
			String query = "";
			String amendId = "";
			Object[] args = null;
			if ("edit".equalsIgnoreCase(bean.getMode())) {
				query = getQuery("GET_EDIT_THIRD_PARTY_AMEND_ID");
				args = new Object[] { bean.getThirdPartyId() };
				LogManager.info("Query => " + query);
				LogManager.info("Arguments => " + StringUtils.join(args, ","));
				List<Map<String, Object>> res = this.mytemplate.queryForList(query, args);
				if (res.size() > 0)
					amendId = res.get(0).get("AMEND_ID") == null ? "" : res.get(0).get("AMEND_ID").toString();
			}
			if ("add".equalsIgnoreCase(bean.getMode()) || ("edit".equalsIgnoreCase(bean.getMode()) && StringUtils.isBlank(amendId))) {
				query = getQuery("INS_THIRD_PARTY_MASTER");
				args = new Object[13];
				args[0] = branchCode;
				args[1] = productId;
				if ("add".equalsIgnoreCase(bean.getMode())) {
					String sql = getQuery("GET_MAX_THIRD_PARTY_ID");
					String thirdPartyId = (String) this.mytemplate.queryForObject(sql, String.class);
					args[2] = thirdPartyId;
					args[3] = thirdPartyId;
				} else {
					args[2] = bean.getThirdPartyId();
					args[3] = bean.getThirdPartyId();
				}
				args[4] = bean.getBodyType();
				args[5] = bean.getSeatingCylinderStart();
				args[6] = bean.getThirdPartyRate();
				args[7] = bean.getEffectiveDate();
				args[8] = bean.getCoreAppCode();
				args[9] = bean.getStatus();
				args[10] = bean.getSeatingCylinderEnd();
				args[11] = "1";
				args[12] = bean.getUserNameLogin();
			} else if ("edit".equalsIgnoreCase(bean.getMode()) && StringUtils.isNotBlank(amendId)) {
				query = getQuery("UPD_THIRD_PARTY_MASTER");
				args = new Object[13];
				args[0] = branchCode;
				args[1] = productId;
				args[2] = bean.getBodyType();
				args[3] = bean.getSeatingCylinderStart();
				args[4] = bean.getThirdPartyRate();
				args[5] = bean.getEffectiveDate();
				args[6] = bean.getCoreAppCode();
				args[7] = bean.getStatus();
				args[8] = bean.getSeatingCylinderEnd();
				args[9] = bean.getUserNameLogin();
				args[10] = "1";
				args[11] = bean.getThirdPartyId();
				args[12] = amendId;
			}
			LogManager.info("Query => " + query);
			LogManager.info("Arguments => " + StringUtils.join(args, ","));
			this.mytemplate.update(query, args);

		} catch (Exception e) {
			LogManager.debug("Exception Occured @ insertThirdPartyMaster " + e);
			e.printStackTrace();
			result = e.toString();
		}
		return result;
	}

	public void getEditThirdParty(MotorAdminBeanNew bean) {
		try {
			String query = getQuery("GET_EDIT_THIRD_PARTY");
			Object args[] = { bean.getThirdPartyId(), productId, branchCode };
			LogManager.info("Query => " + query);
			LogManager.info("Arguments => " + StringUtils.join(args, ","));
			Map<String, Object> res = this.mytemplate.queryForMap(query, args);
			bean.setSeatingCylinderStart(res.get("SEATING_CYLINDER_START") == null ? "": res.get("SEATING_CYLINDER_START").toString());
			bean.setSeatingCylinderEnd(res.get("SEATING_CYLINDER_END") == null ? "": res.get("SEATING_CYLINDER_END").toString());
			bean.setThirdPartyRate(res.get("THIRD_PARTY_RATE") == null ? "": res.get("THIRD_PARTY_RATE").toString());
			bean.setEffectiveDate(res.get("EFFECTIVE_DATE") == null ? "" : res.get("EFFECTIVE_DATE").toString());
			bean.setCoreAppCode(res.get("COREAPP_CODE") == null ? "" : res.get("COREAPP_CODE").toString());
			bean.setStatus(res.get("STATUS") == null ? "" : res.get("STATUS").toString());
			bean.setUserNameLogin(res.get("AGENCY_CODE")==null?"":res.get("AGENCY_CODE").toString());
			/*bean.setVehicleType(res.get("TYPE_OF_BODY_ID") == null ? "" : res.get("TYPE_OF_BODY_ID").toString());*/
			/*
			 * bean.setLocationId(res.get("LOCATIONID")==null?"":res.get("LOCATIONID"
			 * ).toString());
			 */
		} catch (Exception e) {
			LogManager.debug("Exception Occured getEditThirdParty => " + e);
			e.printStackTrace();
		}
	}

	public List<Map<String, Object>> getThirdPartyModelList(
			MotorAdminBeanNew bean) {
		List<Map<String, Object>> result = null;
		try {
			String query = getQuery("GET_THIRD_PARTY_LIST");
			Object[] args;
			if ("edit".equalsIgnoreCase(bean.getMode())) {
				query += " AND THIRD_PARTY_ID !=? ORDER BY THIRD_PARTY_ID DESC";
				args = new Object[] { branchCode, productId,bean.getBodyType(), bean.getThirdPartyId() };
			} else {
				query += " ORDER BY THIRD_PARTY_ID DESC";
				args = new Object[] { branchCode, productId,bean.getBodyType() };
			}
			LogManager.info("Query => " + query);
			LogManager.info("Arguments => " + StringUtils.join(args, ","));
			result = this.mytemplate.queryForList(query, args);
		} catch (Exception e) {
			LogManager.debug("Exception Occured @ getThirdPartyModelList +> "
					+ e);
			e.printStackTrace();
		}
		return result;
	}

	public int checkMakeCoreCode(MotorAdminBeanNew bean) {
		int result = 0;
		try {
			String query = getQuery("CHECK_MAKE_CORE_CODE");
			Object[] args;
			if ("editMake".equalsIgnoreCase(bean.getMode())) {
				query += " AND MAKE_ID !=? ";
				args = new Object[] { bean.getCoreAppCode(), branchCode,bean.getMakeId() };
			} else {
				args = new Object[] { bean.getCoreAppCode(), branchCode };
			}
			LogManager.info("Query => " + query);
			LogManager.info("Arguments => " + StringUtils.join(args, ","));
			result = this.mytemplate.queryForInt(query, args);
		} catch (Exception e) {
			LogManager.debug("Exception Occured @ checkMakeCoreCode " + e);
			e.printStackTrace();
		}
		return result;
	}

	public int checkModelCoreCode(MotorAdminBeanNew bean) {
		int result = 0;
		try {
			String query = getQuery("CHECK_MODEL_CORE_CODE");
			Object[] args;
			if ("editModel".equalsIgnoreCase(bean.getMode())) {
				query += " AND MODEL_ID !=? ";
				args = new Object[] { bean.getCoreAppCode(), branchCode,bean.getModelId() };
			} else {
				args = new Object[] { bean.getCoreAppCode(), branchCode };
			}
			LogManager.info("Query => " + query);
			LogManager.info("Arguments => " + StringUtils.join(args, ","));
			result = this.mytemplate.queryForInt(query, args);
		} catch (Exception e) {
			LogManager.debug("Exception Occured @ checkMakeCoreCode " + e);
			e.printStackTrace();
		}
		return result;
	}

	public List<Map<String, Object>> getDeductible(MotorAdminBeanNew bean) {
		LogManager.info("Enter into getDeductible()");
		List<Map<String, Object>> result = null;
		try {
			String query = getQuery("GET_DEDUCTIBLE_MASTER");
			LogManager.info("query =>" + query);
			Object args[] = new Object[1];
			args[0] = branchCode;
			LogManager.info("Argument =>" + args[0]);
			result = this.mytemplate.queryForList(query, args);
		} catch (Exception e) {
			LogManager.error("Exception occured @ getDeductible()" + e);
			e.printStackTrace();
		}
		LogManager.info("Exit from getDeductible()");
		return result;
	}

	public List<Map<String, Object>> getDeductibleEdit(MotorAdminBeanNew bean) {
		LogManager.info("Enter into getDeductibleEdit()");
		List<Map<String, Object>> result = null;
		try {
			String query = getQuery("GET_DEDUCTIBLE_FOR_EDIT");
			LogManager.info("query =>" + query);
			Object args[] = { bean.getDeductibleId(), branchCode };
			LogManager.info("Arguments =>" + StringUtils.join(args, ","));
			result = this.mytemplate.queryForList(query, args);
			if (result.size() > 0) {
				Map map = (Map) result.get(0);
				bean.setDeductibleId(map.get("DEDUCT_ID") == null ? "" : map.get("DEDUCT_ID").toString());
				bean.setDeductibleStart(map.get("DEDUCT_START") == null ? "": map.get("DEDUCT_START").toString());
				bean.setDeductibleEnd(map.get("DEDUCT_END") == null ? "" : map.get("DEDUCT_END").toString());
				bean.setDeductibleRate(map.get("RATE") == null ? "" : map.get("RATE").toString());
				bean.setDiscountAmount(map.get("DISC_AMT") == null ? "" : map.get("DISC_AMT").toString());
				bean.setVehicleType(map.get("VTYPE_ID") == null ? "" : map.get("VTYPE_ID").toString());
				bean.setBodyType(map.get("TYPE_OF_BODY_ID") == null ? "" : map.get("TYPE_OF_BODY_ID").toString());
				bean.setSeatingStart(map.get("SEATING_START") == null ? "" : map.get("SEATING_START").toString());
				bean.setSeatingEnd(map.get("SEATING_END") == null ? "" : map.get("SEATING_END").toString());
				bean.setStatus(map.get("STATUS") == null ? "" : map.get("STATUS").toString());
				bean.setEffectiveDate(map.get("EFFECTIVE_DATE") == null ? "": map.get("EFFECTIVE_DATE").toString());
				bean.setRemarks(map.get("REMARKS") == null ? "" : map.get("REMARKS").toString());
				bean.setDataModified(map.get("DEFAULT_YN") == null ? "" : map.get("DEFAULT_YN").toString());
				bean.setDeductibleAmountUSD(map.get("DEDUCT_AMT_USD") == null ? "" : map.get("DEDUCT_AMT_USD").toString());
				bean.setTheftExcessZMW(map.get("THEFT_EXCESS") == null ? "" : map.get("THEFT_EXCESS").toString());
				bean.setDriverExcessZMW(map.get("DRIVER_EXCESS") == null ? "" : map.get("DRIVER_EXCESS").toString());
				bean.setClaimExcessZMW(map.get("CLAIM_EXCESS") == null ? "" : map.get("CLAIM_EXCESS").toString());
				
			}
		} catch (Exception e) {
			LogManager.error("Exception Occured @ getDeductibleEdit()" + e);
			e.printStackTrace();
		}
		LogManager.info("Exit from getDeductibleEdit() ");
		return result;
	}

	public void getDeductibleInsert(MotorAdminBeanNew bean) {
		try {
			String query = "";
			String amendId = "";
			Object[] args = null;
			if ("Edit".equalsIgnoreCase(bean.getMode())) {
				query = getQuery("GET_EDIT_DEDUCTIBLE_AMEND_ID");
				args = new Object[] { bean.getDeductibleId() };
				LogManager.info("Query => " + query);
				LogManager.info("Arguments => " + StringUtils.join(args, ","));
				List<Map<String, Object>> res = this.mytemplate.queryForList(query, args);
				if (res.size() > 0)
					amendId = res.get(0).get("AMEND_ID") == null ? "" : res.get(0).get("AMEND_ID").toString();
			}

			if ("Add".equalsIgnoreCase(bean.getMode()) || ("Edit".equalsIgnoreCase(bean.getMode()) && StringUtils.isBlank(amendId))) {
				LogManager.info("Enter into getDeductibleInsert()");
				query = getQuery("GET_DEDUCTIBLE_FOR_INSERT");

				args = new Object[18];
				if ("Add".equalsIgnoreCase(bean.getMode())) {
					String sql = getQuery("GET_MAX_DEDUCTIBLE_ID");
					String deductibleId = (String) this.mytemplate.queryForObject(sql, String.class);
					args[0] = deductibleId;
					args[11] = deductibleId;
				} else {
					args[0] = bean.getDeductibleId();
					args[11] = bean.getDeductibleId();
				}
				args[1] = bean.getDeductibleStart();
				args[2] = bean.getDeductibleEnd();
				args[3] = bean.getDeductibleRate();
				args[4] = bean.getDiscountAmount();
				args[5] = bean.getBodyType();
				args[6] = bean.getSeatingStart();
				args[7] = bean.getSeatingEnd();
				args[8] = bean.getStatus();
				args[9] = bean.getEffectiveDate();
				args[10] = bean.getRemarks();
				args[12] = branchCode;
				args[13] = bean.getDataModified();
				args[14] = bean.getDeductibleAmountUSD();
				args[15] = bean.getTheftExcessZMW();
				args[16] = bean.getDriverExcessZMW();
				args[17] = bean.getClaimExcessZMW();
				

			} else if ("Edit".equalsIgnoreCase(bean.getMode())&& StringUtils.isNotBlank(amendId)) {
				query = getQuery("GET_DEDUCTIBLE_FOR_UPDATE");
				args = new Object[18];
				args[0] = bean.getDeductibleStart();
				args[1] = bean.getDeductibleEnd();
				args[2] = bean.getDeductibleRate();
				args[3] = bean.getDiscountAmount();
				args[4] = bean.getBodyType();
				args[5] = bean.getSeatingStart();
				args[6] = bean.getSeatingEnd();
				args[7] = bean.getStatus();
				args[8] = bean.getEffectiveDate();
				args[9] = bean.getRemarks();
				args[10] = branchCode;
				args[11] = bean.getDataModified();
				args[12] = bean.getDeductibleAmountUSD();
				args[13] = bean.getTheftExcessZMW();
				args[14] = bean.getDriverExcessZMW();
				args[15] = bean.getClaimExcessZMW();
				args[16] = bean.getDeductibleId();
				args[17] = amendId;
			}
			LogManager.info("Query => " + query);
			LogManager.info("Arguments => " + StringUtils.join(args, ","));
			this.mytemplate.update(query, args);

		} catch (Exception e) {
			LogManager.debug("Exception Occured @ getDeductibleInsert() " + e);
			e.printStackTrace();
		}
		LogManager.info("Exit from getDeductibleInsert()");
	}

	// Vehicle Type Master List
	public List<Map<String, Object>> getVehicleType(MotorAdminBeanNew bean) {
		List<Map<String, Object>> drpdown = new ArrayList<Map<String, Object>>();
		try {
			LogManager.info("Enter into the getVehicleType()");
			Object args[] = { branchCode };
			String query = getQuery("GET_VEHICLE_TYPE_MASTER");
			LogManager.info("Query==>" + query);
			LogManager.info("Arguments =>" + StringUtils.join(args, ","));
			drpdown = this.mytemplate.queryForList(query, args);

		} catch (Exception e) {
			LogManager.info("Enter into the Exception");
			e.printStackTrace();
		}
		LogManager.info("Exit from getVehicleType()");
		return drpdown;
	}

	// Vehicle Type Master Edit

	public void getEditVehicleType(MotorAdminBeanNew bean) {
		try {
			LogManager.info("Enter into the getEditVehicleType()");

			query = getQuery("GET_EDIT_VEHICLE_TYPE");
			Object args[] = { bean.getVtypeId(), branchCode };
			LogManager.info("Query => " + query);
			LogManager.info("Arguments => " + StringUtils.join(args, ","));
			Map<String, Object> res = this.mytemplate.queryForMap(query, args);
			if (res.size() > 0) {
				bean.setVtypeId(res.get("VTYPE_ID") == null ? "" : res.get("VTYPE_ID").toString());
				bean.setVehicletypeDesc(res.get("VEHICLETYPE_DESC") == null ? "": res.get("VEHICLETYPE_DESC").toString());
				bean.setCoreappCode(res.get("CORE_APP_CODE") == null ? "" : res.get("CORE_APP_CODE").toString());
				bean.setEffectiveDate(res.get("EFFECTIVE_DATE") == null ? "": res.get("EFFECTIVE_DATE").toString());
				bean.setRemarks(res.get("REMARKS") == null ? "" : res.get("REMARKS").toString());
				bean.setReferralStatus(res.get("REFERRAL_STATUS") == null ? "": res.get("REFERRAL_STATUS").toString());
				bean.setStatus(res.get("STATUS") == null ? "" : res.get("STATUS").toString());

			}
		} catch (Exception e) {
			LogManager.info("Enter into the Exception");
			e.printStackTrace();
		}
	}

	// Vehicle Type Master Insert 0r update
 String insertVehicleTypeMaster(MotorAdminBeanNew bean) {
		String result = "";
		try {
			String query = "";
			String amendId = "";
			Object[] args = null;
			if ("edit".equalsIgnoreCase(bean.getMode())) {
				query = getQuery("GET_EDIT_VTYPE_AMEND_ID");

				args = new Object[] { bean.getVtypeId() };
				LogManager.info("Query => " + query);
				LogManager.info("Arguments => " + StringUtils.join(args, ","));
				List<Map<String, Object>> res = this.mytemplate.queryForList(query, args);
				if (res.size() > 0)
					amendId = res.get(0).get("AMEND_ID") == null ? "" : res.get(0).get("AMEND_ID").toString();
			}
			if ("add".equalsIgnoreCase(bean.getMode())|| ("edit".equalsIgnoreCase(bean.getMode()) && StringUtils.isBlank(amendId))) {
				query = getQuery("GET_VEHICLETYPE_INSERT");
				args = new Object[10];
				if ("add".equalsIgnoreCase(bean.getMode())) {
					String sql = getQuery("GET_MAX_VTYPE_ID");
					LogManager.info("Query==>" + sql);
					String vtypeId = (String) this.mytemplate.queryForObject(sql, String.class);
					args[0] = vtypeId;
					args[5] = vtypeId;
				} else {
					args[0] = bean.getVtypeId();
					args[5] = bean.getVtypeId();
				}
				args[1] = bean.getVehicletypeDesc();
				args[2] = bean.getStatus();
				args[3] = bean.getEffectiveDate();
				args[4] = bean.getRemarks();
				args[6] = branchCode;
				args[7] = bean.getCoreappCode();
				args[8] = bean.getReferralStatus();
				args[9] = productId;
			} else if ("edit".equalsIgnoreCase(bean.getMode())&& StringUtils.isNotBlank(amendId)) {
				query = getQuery("UPDATE_VEHICLETYPE_MASTER");
				args = new Object[10];
				args[0] = bean.getVehicletypeDesc();
				args[1] = bean.getStatus();
				args[2] = bean.getEffectiveDate();
				args[3] = bean.getRemarks();
				args[4] = branchCode;
				args[5] = bean.getCoreappCode();
				args[6] = bean.getReferralStatus();
				args[7] = productId;
				args[8] = bean.getVtypeId();
				args[9] = amendId;

			}
			LogManager.info("Query => " + query);
			LogManager.info("Arguments => " + StringUtils.join(args, ","));
			this.mytemplate.update(query, args);

		} catch (Exception e) {
			LogManager.debug("Exception Occured @ insertVehicleTypeMaster " + e);
			e.printStackTrace();
			result = e.toString();
		}
		return result;
	}

	public int checkVTCoreAppCode(MotorAdminBeanNew bean) {
		LogManager.info("Enter into checkVTCoreAppCode()");
		int i = 0;
		try {
			query = getQuery("CHECK_CORE_APP_CODE");
			Object args[];
			if ("edit".equalsIgnoreCase(bean.getMode())) {
				query += " AND VTYPE_ID !=? ";
				args = new Object[] { bean.getCoreappCode(),branchCode, bean.getVtypeId() };
			} else {
				args = new Object[] { bean.getCoreappCode(),branchCode};
			}
			LogManager.info("query =>" + query);
			LogManager.info("args===>" + StringUtils.join(args, ","));
			i = this.mytemplate.queryForInt(query, args);

		} catch (Exception e) {
			e.printStackTrace();
			LogManager.info("Error in checkVTCoreAppCode()");
		}

		LogManager.info("Exit from checkVTCoreAppCode()");
		return i;
	}

	public List<Map<String, Object>> getPolicyTypeMasterList(
			MotorAdminBeanNew bean) {
		List<Map<String, Object>> result = null;
		try {
			String query = getQuery("GET_POLICY_TYPE_MASTER_LIST");
			Object args[] = { branchCode };
			LogManager.info("query =>" + query);
			LogManager.info("args===>" + StringUtils.join(args, ","));
			result = this.mytemplate.queryForList(query, args);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	public String insertPolicyTypeMaster(MotorAdminBeanNew bean) {
		String result="";
		try {
			String query = "";
			String amendId = "";
			Object[] args = null;
			if ("edit".equalsIgnoreCase(bean.getMode())) {
				query = getQuery("GET_EDIT_POLICY_TYPE_AMEND_ID");
				args = new Object[] { bean.getPolicySubTypeId() };
				LogManager.info("Query => " + query);
				LogManager.info("Arguments => " + StringUtils.join(args, ","));
				List<Map<String, Object>> res = this.mytemplate.queryForList(query, args);
				if (res.size() > 0)
					amendId = res.get(0).get("AMEND_ID") == null ? "" : res.get(0).get("AMEND_ID").toString();
			}
			if ("add".equalsIgnoreCase(bean.getMode()) || ("edit".equalsIgnoreCase(bean.getMode()) && StringUtils.isBlank(amendId))) {
				query = getQuery("INS_POLICY_TYPE_MASTER");
				args = new Object[11];
				args[1] = bean.getCoreAppCode();
				args[2] = bean.getPolicyTypeDescEng();
				args[3] = bean.getPolicyTypeDesArabic()==null?"":bean.getPolicyTypeDesArabic();
				args[4] = bean.getStatus();
				args[5] = bean.getEffectiveDate();
				args[6] = bean.getRemarks();
				args[7] = branchCode;
				if ("add".equalsIgnoreCase(bean.getMode())) {
					String sql = getQuery("GET_MAX_POLICY_SUB_ID");
					String policySubId = (String) this.mytemplate
							.queryForObject(sql, String.class);
					args[0] = policySubId;
					args[8] = policySubId;
					args[9] = policySubId;
				} else {
					args[0] = bean.getPolicySubTypeId();
					args[8] = bean.getPolicySubTypeId();
					args[9] = bean.getPolicySubTypeId();
				}
				args[10] = bean.getPaymentYN();
			} else if ("edit".equalsIgnoreCase(bean.getMode())&& StringUtils.isNotBlank(amendId)) {
				query = getQuery("UPD_POLICY_TYPE_MASTER");
				args = new Object[10];
				args[0] = bean.getPolicySubTypeId();
				args[1] = bean.getCoreAppCode();
				args[2] = bean.getPolicyTypeDescEng();
				args[3] = bean.getPolicyTypeDesArabic()==null?"":bean.getPolicyTypeDesArabic();
				args[4] = bean.getStatus();
				args[5] = bean.getEffectiveDate();
				args[6] = bean.getRemarks();
				args[7] = bean.getPaymentYN();
				args[8] = bean.getPolicySubTypeId();
				args[9] = amendId;
			}
			LogManager.info("Query => " + query);
			LogManager.info("Arguments => " + StringUtils.join(args, ","));
			this.mytemplate.update(query, args);

		} catch (Exception e) {
			LogManager.debug("Exception Occured @ insertPolicyTypeMaster " + e);
			e.printStackTrace();
			result=e.toString();
		}
		return result;
	}

	public void getEditPolicyTypeMaster(MotorAdminBeanNew bean) {
		try {
			String query = getQuery("GET_EDIT_POLICY_TYPE_MASTER");
			Object args[] = { bean.getPolicySubTypeId() };
			LogManager.info("query =>" + query);
			LogManager.info("args===>" + StringUtils.join(args, ","));
			Map<String, Object> res = this.mytemplate.queryForMap(query, args);
			bean.setPolicyTyeId(res.get("POLICYTYPE_ID") == null ? "" : res.get("POLICYTYPE_ID").toString());
			bean.setCoreAppCode(res.get("COREAPP_CODE") == null ? "" : res.get("COREAPP_CODE").toString());
			bean.setPolicyTypeDescEng(res.get("POLICYTYPE_DESC_ENGLISH") == null ? "": res.get("POLICYTYPE_DESC_ENGLISH").toString());
			bean.setPolicyTypeDesArabic(res.get("POLICYTYPE_DESC_ARABIC") == null ? "": res.get("POLICYTYPE_DESC_ARABIC").toString());
			bean.setStatus(res.get("STATUS") == null ? "" : res.get("STATUS").toString());
			bean.setEffectiveDate(res.get("EFFECTIVE_DATE") == null ? "" : res.get("EFFECTIVE_DATE").toString());
			bean.setRemarks(res.get("REMARKS") == null ? "" : res.get("REMARKS").toString());
			bean.setPaymentYN(res.get("PAYMENT_YN") == null ? "" : res.get("PAYMENT_YN").toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int checkPolicyTypeCoreCode(MotorAdminBeanNew bean) {
		int result = 0;
		try {
			String query = getQuery("CHECK_POLICYTYPE_CORE_CODE");
			Object[] args;
			if ("edit".equalsIgnoreCase(bean.getMode())) {
				query += " AND POLICY_SUBTYPE_ID != ?";
				args = new Object[] { bean.getCoreAppCode(), branchCode,bean.getPolicySubTypeId() };
			} else {
				args = new Object[] { bean.getCoreAppCode(), branchCode };
			}
			LogManager.info("Query => " + query);
			LogManager.info("Arguments => " + StringUtils.join(args, ","));
			result = this.mytemplate.queryForInt(query, args);
		} catch (Exception e) {
			LogManager.debug("Exception Occured @ checkMakeCoreCode " + e);
			e.printStackTrace();
		}
		return result;
	}

	public List<Map<String, Object>> getNCBList(MotorAdminBeanNew bean) {
		List<Map<String, Object>> result = null;
		try {
			String query = getQuery("GET_NCB_MASTER_DATA_LIST");
			Object args[] = { branchCode };
			LogManager.info("query =>" + query);
			LogManager.info("args===>" + StringUtils.join(args, ","));
			result = this.mytemplate.queryForList(query, args);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public void getEditNCBMaster(MotorAdminBeanNew bean) {
		try {
			String query = getQuery("GET_EDIT_NCB_MASTER");
			Object args[] ={bean.getNcbId(),branchCode};
			LogManager.info("query =>"+ query);
			LogManager.info("args===>"+StringUtils.join(args,","));
			Map<String,Object> res = this.mytemplate.queryForMap(query,args);
			bean.setNcbRate(res.get("RATE")==null?"":res.get("RATE").toString());
			bean.setNcbYear(res.get("NCBYEAR")==null?"":res.get("NCBYEAR").toString());
			bean.setSuminsuredStart(res.get("INSUREDVALUE_START")==null?"":res.get("INSUREDVALUE_START").toString());
			bean.setSuminsuredEnd(res.get("INSUREDVALUE_END")==null?"":res.get("INSUREDVALUE_END").toString());
			bean.setCoreAppCode(res.get("CORE_CODE")==null?"":res.get("CORE_CODE").toString());
			bean.setStatus(res.get("STATUS")==null?"":res.get("STATUS").toString());
			bean.setEffectiveDate(res.get("EFFECTIVE_DATE")==null?"":res.get("EFFECTIVE_DATE").toString());
			bean.setRemarks(res.get("REMARKS")==null?"":res.get("REMARKS").toString());
			bean.setVehicleType(res.get("VTYPE_ID")==null?"":res.get("VTYPE_ID").toString());
			bean.setPolicyType(res.get("POLICYTYPE")==null?"":res.get("POLICYTYPE").toString());
		}catch(Exception e){
			e.printStackTrace();	
		}
	}

	public int checkNCBYear(MotorAdminBeanNew bean) {
		int result = 0;
		try {
			String query = getQuery("GET_COUNT_NCB");
			Object[] args;
			if ("edit".equalsIgnoreCase(bean.getMode())) {
				query += " AND S_NO != ? ";
				args = new Object[] { bean.getNcbYear(), branchCode,
						bean.getNcbId() };
			} else {
				args = new Object[] { bean.getNcbYear(), branchCode };
			}

			LogManager.info("Query => " + query);
			LogManager.info("Arguments => " + StringUtils.join(args, ","));
			result = this.mytemplate.queryForInt(query, args);
		} catch (Exception e) {
			LogManager.debug("Exception Occured @ checkNCBYear " + e);
			e.printStackTrace();
		}
		return result;
	}

	public int checkNCBCoreCode(MotorAdminBeanNew bean) {
		int result = 0;
		try {
			String query = getQuery("CHECK_NCB_CORE_CODE");
			Object[] args;
			if ("edit".equalsIgnoreCase(bean.getMode())) {
				query += "  AND S_NO != ? ";
				args = new Object[] { bean.getCoreAppCode(), branchCode,
						bean.getNcbId() };
			} else {
				args = new Object[] { bean.getCoreAppCode(), branchCode };
			}
			LogManager.info("Query => " + query);
			LogManager.info("Arguments => " + StringUtils.join(args, ","));
			result = this.mytemplate.queryForInt(query, args);
		} catch (Exception e) {
			LogManager.debug("Exception Occured @ checkNCBCoreCode " + e);
			e.printStackTrace();
		}
		return result;
	}
	public String insertNCBMaster(MotorAdminBeanNew bean) {
		String result="";
		try {
			String query = "";
			Object[] args = null;
			String amendId = "";
			if ("edit".equalsIgnoreCase(bean.getMode())) {
				query = getQuery("GET_EDIT_NCB_AMEND_ID");
				args = new Object[] { bean.getNcbId() };
				List<Map<String, Object>> res = this.mytemplate.queryForList(query, args);
				if (res.size() > 0)
					amendId = res.get(0).get("AMEND_ID") == null ? "" : res.get(0).get("AMEND_ID").toString();
			}
			if ("add".equalsIgnoreCase(bean.getMode()) || ("edit".equalsIgnoreCase(bean.getMode()) && StringUtils.isBlank(amendId))) {
				query = getQuery("INS_NCB_MASTER");
				args = new Object[13];
				args[0] = productId;
				args[1] = bean.getPolicyType();
				args[2] = bean.getNcbYear();
				args[3] = bean.getSuminsuredStart();
				args[4] = bean.getSuminsuredEnd();
				args[5] = bean.getNcbRate();
				args[6] = bean.getEffectiveDate();
				if ("add".equalsIgnoreCase(bean.getMode())) {
					String sql = getQuery("GET_MAX_NCB_ID");
					String ncbId = (String) this.mytemplate.queryForObject(sql,
							String.class);
					args[7] = ncbId;
					args[8] = ncbId;
				} else {
					args[7] = bean.getNcbId();
					args[8] = bean.getNcbId();
				}
				args[9] = branchCode;
				args[10] = bean.getStatus();
				args[11] = bean.getCoreAppCode();
				args[12] = bean.getVehicleType();
			}else if("edit".equalsIgnoreCase(bean.getMode()) &&  StringUtils.isNotBlank(amendId)){
				query=getQuery("UPD_NCB_MASTER");
				args=new Object[13];
				args[0] =productId;
				args[1] = bean.getNcbYear();
				args[2] = bean.getSuminsuredStart();
				args[3] = bean.getSuminsuredEnd();
				args[4] = bean.getNcbRate();
				args[5] = bean.getEffectiveDate();
				args[6] = branchCode;
				args[7] = bean.getStatus();
				args[8] = bean.getCoreAppCode();
				args[9] = bean.getVehicleType();
				args[10] = bean.getPolicyType();
				args[11] = bean.getNcbId();
				args[12] = amendId;
				
			}
			LogManager.info("Query => " + query);
			LogManager.info("Arguments => " + StringUtils.join(args, ","));
			this.mytemplate.update(query, args);

		} catch (Exception e) {
			LogManager.debug("Exception Occured @ insertPolicyTypeMaster " + e);
			e.printStackTrace();
			result = e.toString();
		}
		return result;
	}

	public List<Map<String, Object>> getbankFinance(MotorAdminBeanNew bean) {
		List<Map<String, Object>> drpdown = new ArrayList<Map<String, Object>>();
		try {
			LogManager.info("Enter into the getbankFinance()");
			Object args[] = { branchCode };
			String query = getQuery("GET_BANK_FINANCE_MASTER");
			LogManager.info("Query==>" + query);
			LogManager.info("Arguments =>" + StringUtils.join(args, ","));
			drpdown = this.mytemplate.queryForList(query, args);

		} catch (Exception e) {
			LogManager.info("Enter into the Exception");
			e.printStackTrace();
		}
		LogManager.info("Exit from getbankFinance()");
		return drpdown;
	}

	public void getEditBankFinance(MotorAdminBeanNew bean) {
		try {
			LogManager.info("Enter into the getEditBankFinance()");
			query = getQuery("GET_EDIT_BANK_FINANCE");
			Object args[] = { bean.getBankId(), branchCode };
			LogManager.info("Query => " + query);
			LogManager.info("Arguments => " + StringUtils.join(args, ","));
			Map<String, Object> res = this.mytemplate.queryForMap(query, args);
			if (res.size() > 0) {
				bean.setBankId(res.get("BANK_ID") == null ? "" : res.get("BANK_ID").toString());
				bean.setBankCode(res.get("BANK_CODE") == null ? "" : res.get("BANK_CODE").toString());
				bean.setBankNameEnglish(res.get("BANK_NAME_ENGLISH") == null ? "": res.get("BANK_NAME_ENGLISH").toString());
				bean.setBankNameArabic(res.get("BANK_NAME_ARABIC") == null ? "": res.get("BANK_NAME_ARABIC").toString());
				bean.setEffectiveDate(res.get("EFFECTIVE_DATE") == null ? "": res.get("EFFECTIVE_DATE").toString());
				bean.setRemarks(res.get("REMARKS") == null ? "" : res.get("REMARKS").toString());
				bean.setStatus(res.get("STATUS") == null ? "" : res.get("STATUS").toString());
				bean.setAddressA(res.get("ADDRESS1")==null?"":res.get("ADDRESS1").toString());
				bean.setAddressB(res.get("ADDRESS2")==null?"":res.get("ADDRESS2").toString());
				bean.setTelephoneNo(res.get("TELEPHONE")==null?"":res.get("TELEPHONE").toString());
			}
		} catch (Exception e) {
			LogManager.info("Enter into the Exception");
			e.printStackTrace();
		}
		LogManager.info("Exit from getEditBankFinance()");

	}

	public String insertBankFinanceMaster(MotorAdminBeanNew bean) {
		String result = "";
		try {
			LogManager.info("Enter into the insertBankFinanceMaster()");
			String query = "";
			Object[] args = null;
			String amendId = "";
			if ("edit".equalsIgnoreCase(bean.getMode())) {
				query = getQuery("GET_EDIT_FBANK_AMEND_ID");
				args = new Object[] { bean.getBankId() };
				List<Map<String, Object>> res = this.mytemplate.queryForList(query, args);
				if (res.size() > 0)
					amendId = res.get(0).get("AMEND_ID") == null ? "" : res.get(0).get("AMEND_ID").toString();
			}
			if ("add".equalsIgnoreCase(bean.getMode())|| ("edit".equalsIgnoreCase(bean.getMode()) && StringUtils.isBlank(amendId))) {
				query = getQuery("GET_BANK_FINANCE_INSERT");
				args = new Object[12];
				if ("add".equalsIgnoreCase(bean.getMode())) {
					String sql = getQuery("GET_MAX_BANK_ID");
					LogManager.info("Query==>" + sql);
					String bankId = (String) this.mytemplate.queryForObject(sql, String.class);
					args[0] = bankId;
					args[7] = bankId;
				} else {
					args[0] = bean.getBankId();
					args[7] = bean.getBankId();
				}
				args[1] = bean.getBankCode();
				args[2] = bean.getBankNameEnglish();
				args[3] = bean.getBankNameArabic() == null ? "" : bean.getBankNameArabic();
				args[4] = bean.getStatus();
				args[5] = bean.getEffectiveDate();
				args[6] = bean.getRemarks();
				args[8] = branchCode;
				args[9] = bean.getAddressA();
				args[10] = bean.getAddressB();
				args[11] = bean.getTelephoneNo();

			} else if ("edit".equalsIgnoreCase(bean.getMode())&& StringUtils.isNotBlank(amendId)) {
				query = getQuery("UPDATE_BANK_FINANCE_MASTER");
				args = new Object[12];
				args[0] = bean.getBankCode();
				args[1] = bean.getBankNameEnglish();
				args[2] = bean.getBankNameArabic() == null ? "" : bean.getBankNameArabic();
				args[3] = bean.getStatus();
				args[4] = bean.getEffectiveDate();
				args[5] = bean.getRemarks();
				args[6] = bean.getAddressA();
				args[7] = bean.getAddressB();
				args[8] = bean.getTelephoneNo();
				args[9] = branchCode;
				args[10] = bean.getBankId();
				args[11] = amendId;
			}
			LogManager.info("Query => " + query);
			LogManager.info("Arguments => " + StringUtils.join(args, ","));
			this.mytemplate.update(query, args);

		} catch (Exception e) {
			LogManager.debug("Exception Occured @ insertBankFinanceMaster " + e);
			e.printStackTrace();
			result = e.toString();
		}
		LogManager.info("Exit from insertBankFinanceMaster()");
		return result;
	}

	public int checkBFMBankCode(MotorAdminBeanNew bean) {
		LogManager.info("Enter into checkBFMBankCode()");
		int i = 0;
		try {
			String query = "";
			query = getQuery("CHECK_BANK_CODE");
			Object args[] = new Object[1];
			if ("edit".equalsIgnoreCase(bean.getMode())) {
				query += " AND BANK_ID !=? ";
				args = new Object[] { bean.getBankCode(), branchCode,bean.getBankId() };
			} else {
				args = new Object[] { bean.getBankCode(), branchCode };
			}
			LogManager.info("query =>" + query);
			LogManager.info("args===>" + StringUtils.join(args, ","));
			i = this.mytemplate.queryForInt(query, args);

		} catch (Exception e) {
			e.printStackTrace();
			LogManager.info("Error in checkBFMBankCode()");
		}

		LogManager.info("Exit from checkBFMBankCode()");
		return i;
	}

	// Group Master
	public List<Map<String, Object>> getGroup(MotorAdminBeanNew bean) {
		LogManager.info("Enter into the getGroup()");
		List<Map<String, Object>> drpdwn = new ArrayList<Map<String, Object>>();
		try {
			Object args[] = { branchCode };
			query = getQuery("GET_GROUP_MASTER");
			LogManager.info("Query==>" + query);
			LogManager.info("Arguments =>" + StringUtils.join(args, ","));
			drpdwn = this.mytemplate.queryForList(query, args);

		} catch (Exception e) {
			LogManager.debug("Exception Occured @ getGroup " + e);
			e.printStackTrace();
		}
		LogManager.info("Exit from the getGroup()");
		return drpdwn;
	}

	public void getEditGroup(MotorAdminBeanNew bean) {
		LogManager.info("Enter into the getEditGroup()");
		try {
			query = getQuery("GET_EDIT_GROUP_MASTER");
			Object args[] = { bean.getGroupId(), branchCode };
			LogManager.info("Query==>" + query);
			LogManager.info("Arguments==>" + StringUtils.join(args, ","));
			Map<String, Object> res = this.mytemplate.queryForMap(query, args);
			if (res.size() > 0) {
				bean.setGroupId(res.get("GROUP_ID") == null ? "" : res.get("GROUP_ID").toString());
				bean.setGroupDescEng(res.get("GROUP_DESC_ENGLISH") == null ? "": res.get("GROUP_DESC_ENGLISH").toString());
				bean.setDisplayOrder(res.get("DISPLAY_ORDER") == null ? "": res.get("DISPLAY_ORDER").toString());
				bean.setEffectiveDate(res.get("EFFECTIVE_DATE") == null ? "": res.get("EFFECTIVE_DATE").toString());
				bean.setRemarks(res.get("REMARKS") == null ? "" : res.get("REMARKS").toString());
				bean.setStatus(res.get("STATUS") == null ? "" : res.get("STATUS").toString());
			}
		} catch (Exception e) {
			LogManager.debug("Exception Occured @ getEditGroup()" + e);
			e.printStackTrace();
		}
		LogManager.info("Exit from getEditGroup()");
	}

	public String getInsertGroup(MotorAdminBeanNew bean) {
		String result = "";
		try {
			LogManager.info("Enter into the getInsertGroup()");
			String query = "";
			Object[] args = null;
			String amendId = "";
			if ("edit".equalsIgnoreCase(bean.getMode())) {
				query = getQuery("GET_EDIT_GROUP_AMEND_ID");
				args = new Object[] { bean.getGroupId() };
				List<Map<String, Object>> res = this.mytemplate.queryForList(query, args);
				if (res.size() > 0)
					amendId = res.get(0).get("AMEND_ID") == null ? "" : res.get(0).get("AMEND_ID").toString();
			}
			if ("add".equalsIgnoreCase(bean.getMode())|| "edit".equalsIgnoreCase(bean.getMode())&& StringUtils.isBlank(amendId)) {
				query = getQuery("GET_GROUP_INSERT");
				args = new Object[8];
				if ("add".equalsIgnoreCase(bean.getMode())) {
					String sql = getQuery("GET_MAX_GROUP_ID");
					LogManager.info("Query==>" + sql);
					String groupId = (String) this.mytemplate.queryForObject(sql, String.class);
					args[0] = groupId;
					args[5] = groupId;
				} else {
					args[0] = bean.getGroupId();
					args[5] = bean.getGroupId();
				}

				args[1] = bean.getGroupDescEng();
				args[2] = bean.getStatus();
				args[3] = bean.getEffectiveDate();
				args[4] = bean.getRemarks();
				args[6] = branchCode;
				args[7] = bean.getDisplayOrder();
			} else if ("edit".equalsIgnoreCase(bean.getMode())&& StringUtils.isNotBlank(amendId)) {
				query = getQuery("GET_GROUP_UPDATE");
				args = new Object[8];
				args[0] = bean.getGroupDescEng();
				args[1] = bean.getStatus();
				args[2] = bean.getEffectiveDate();
				args[3] = bean.getRemarks();
				args[4] = branchCode;
				args[5] = bean.getDisplayOrder();
				args[6] = bean.getGroupId();
				args[7] = amendId;
			}
			LogManager.info("Query => " + query);
			LogManager.info("Arguments => " + StringUtils.join(args, ","));
			this.mytemplate.update(query, args);
		} catch (Exception e) {
			e.printStackTrace();
			LogManager.debug("Exception Occured @ getInsertGroup()");
			result = e.toString();
		}
		return result;
	}

	public int checkDisplayOrder(MotorAdminBeanNew bean) {
		LogManager.info("Enter into the checkDisplayOrder()");
		int i = 0;
		try {
			query = getQuery("CHECK_DISPLAY_ORDER");
			Object args[] = new Object[1];
			if ("edit".equalsIgnoreCase(bean.getMode())) {
				query += " AND GROUP_ID !=?";
				args = new Object[] { bean.getDisplayOrder(), branchCode,bean.getGroupId() };
			} else {
				args = new Object[] { bean.getDisplayOrder(), branchCode };
			}
			LogManager.info("query =>" + query);
			LogManager.info("args===>" + StringUtils.join(args, ","));
			i = this.mytemplate.queryForInt(query, args);

		} catch (Exception e) {
			e.printStackTrace();
			LogManager.debug("Exception Occured @ checkDisplayOrder" + e);
		}
		LogManager.info("Exit from checkDisplayOrder()");
		return i;
	}

	public List<Map<String, Object>> getBankMasterList(MotorAdminBeanNew bean) {
		List<Map<String, Object>> result = null;
		try {
			String query = getQuery("GET_BANK_MASTER_LIST");
			Object args[] = { branchCode };
			LogManager.info("Query => " + query);
			LogManager.info("Arguments = > " + StringUtils.join(args, ","));
			result = this.mytemplate.queryForList(query, args);
		} catch (Exception e) {
			LogManager.info("Exception Occured @ getBankMasterList ");
			e.printStackTrace();
		}
		return result;
	}

	public void getEditBankMaster(MotorAdminBeanNew bean) {
		try {
			String query = getQuery("EDIT_BANK_MASTER_ADMIN");
			Object args[] = { branchCode, bean.getBankId() };
			LogManager.info("Query => " + query);
			LogManager.info("Arguments => " + StringUtils.join(args, ","));
			Map<String, Object> res = this.mytemplate.queryForMap(query, args);
			if (res.size() > 0) {
				bean.setBankCode(res.get("BANK_CODE") == null ? "" : res.get("BANK_CODE").toString());
				bean.setBankEngName(res.get("BANK_NAME_ENGLISH") == null ? "": res.get("BANK_NAME_ENGLISH").toString());
				bean.setEffectiveDate(res.get("EFFECTIVE_DATE") == null ? "": res.get("EFFECTIVE_DATE").toString());
				bean.setBankArbName(res.get("BANK_NAME_ARABIC") == null ? "": res.get("BANK_NAME_ARABIC").toString());
				bean.setRemarks(res.get("REMARKS") == null ? "" : res.get("REMARKS").toString());
				bean.setStatus(res.get("STATUS") == null ? "" : res.get("STATUS").toString());
				bean.setAddressA(res.get("ADDRESS1")==null?"":res.get("ADDRESS1").toString());
				bean.setAddressB(res.get("ADDRESS2")==null?"":res.get("ADDRESS2").toString());
				bean.setTelephoneNo(res.get("TELEPHONE")==null?"":res.get("TELEPHONE").toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String insertinsBankMaster(MotorAdminBeanNew bean) {
		String result = "";
		try {
			String query = "";
			Object[] args = null;
			String amendId = "";
			if ("edit".equalsIgnoreCase(bean.getMode())) {
				query = getQuery("GET_EDIT_BANK_AMEND_ID");
				args = new Object[] { bean.getBankId() };
				List<Map<String, Object>> res = this.mytemplate.queryForList(query, args);
				if (res.size() > 0)
					amendId = res.get(0).get("AMEND_ID") == null ? "" : res.get(0).get("AMEND_ID").toString();
			}
			if ("add".equalsIgnoreCase(bean.getMode())|| ("edit".equalsIgnoreCase(bean.getMode()) && StringUtils.isBlank(amendId))) {
				query = getQuery("INSERT_BANK_MASTER");
				args = new Object[12];
				if ("add".equalsIgnoreCase(bean.getMode())) {
					String sql = getQuery("GET_MAX_BANKID");
					String id = (String) this.mytemplate.queryForObject(sql,String.class);
					args[0] = id;
					args[1] = id;
				} else {
					args[0] = bean.getBankId();
					args[1] = bean.getBankId();
				}
				args[2] = bean.getBankCode();
				args[3] = bean.getBankEngName();
				args[4] = bean.getBankArbName() == null ? "" : bean.getBankArbName();
				args[5] = bean.getStatus();
				args[6] = bean.getEffectiveDate();
				args[7] = bean.getRemarks();
				args[8] = branchCode;
				args[9] = bean.getAddressA();
				args[10] = bean.getAddressB();
				args[11] = bean.getTelephoneNo();
			} else if ("edit".equalsIgnoreCase(bean.getMode())&& StringUtils.isNotBlank(amendId)) {
				query = getQuery("UPDATE_BANK_MASTER_ADMIN");
				args = new Object[11];
				args[0] = bean.getBankCode();
				args[1] = bean.getBankEngName();
				args[2] = bean.getBankArbName() == null ? "" : bean.getBankArbName();
				args[3] = bean.getStatus();
				args[4] = bean.getEffectiveDate();
				args[5] = bean.getRemarks();
				args[6] = bean.getAddressA();
				args[7] = bean.getAddressB();
				args[8] = bean.getTelephoneNo();
				args[9] = bean.getBankId();
				args[10] = amendId;
			}
			LogManager.info("Query => " + query);
			LogManager.info("Arguments => " + StringUtils.join(args, ","));
			this.mytemplate.update(query, args);
		} catch (Exception e) {
			LogManager.debug("Exception Occured @ insertinsBankMaster " + e);
			e.printStackTrace();
			result = e.toString();
		}
		return result;
	}

	
	public int checkBMBankCode(MotorAdminBeanNew bean) {
		LogManager.info("Enter into checkBMBankCode()");
		int i = 0;
		try {
			String query = "";
			query = getQuery("CHECK_BANK_CODE");
			Object args[] = new Object[1];
			if ("edit".equalsIgnoreCase(bean.getMode())) {
				query += " AND BANK_ID !=? ";
				args = new Object[] { bean.getBankCode(), branchCode,bean.getBankId() };
			} else {
				args = new Object[] { bean.getBankCode(), branchCode };
			}
			LogManager.info("query =>" + query);
			LogManager.info("args===>" + StringUtils.join(args, ","));
			i = this.mytemplate.queryForInt(query, args);

		} catch (Exception e) {
			e.printStackTrace();
			LogManager.info("Error in checkBMBankCode()");
		}

		LogManager.info("Exit from checkBFMBankCode()");
		return i;
	}

	public List<Map<String, Object>> getMfgCountryMasterList(
			MotorAdminBeanNew bean) {
		List<Map<String, Object>> result = null;
		try {
			String query = getQuery("GET_MFGCOUNTRY_MASTER_LIST");
			Object args[] = { branchCode };
			LogManager.info("Query => " + query);
			LogManager.info("Arguments = > " + StringUtils.join(args, ","));
			result = this.mytemplate.queryForList(query, args);
		} catch (Exception e) {
			LogManager.info("Exception Occured @ getMfgCountryMasterList ");
			e.printStackTrace();
		}
		return result;
	}

	public void getEditMfgCountryMaster(MotorAdminBeanNew bean) {
		try {
			String query = getQuery("EDIT_MFGCOUNTRY_MASTER"); // COUNTRY_ID,
																// COUNTRY_CODE,
																// COUNTRY_NAME,
																// DECODE
																// (STATUS,'Y','Active','N','De
																// Active')
																// STATUS,
																// TO_CHAR
																// (EFFECTIVE_DATE,
																// 'DD/MM/YYYY')
																// EFFECTIVE_DATE,
																// COREAPPCODE,
																// NATIONALITY_NAME
			Object args[] = { branchCode, bean.getCountryId() };
			LogManager.info("Query => " + query);
			LogManager.info("Arguments => " + StringUtils.join(args, ","));
			Map<String, Object> res = this.mytemplate.queryForMap(query, args);
			if (res.size() > 0) {
				bean.setCountryCode(res.get("COUNTRY_CODE") == null ? "" : res.get("COUNTRY_CODE").toString());
				bean.setCountryName(res.get("COUNTRY_NAME") == null ? "" : res.get("COUNTRY_NAME").toString());
				bean.setEffectiveDate(res.get("EFFECTIVE_DATE") == null ? "": res.get("EFFECTIVE_DATE").toString());
				bean.setCoreAppCode(res.get("COREAPPCODE") == null ? "" : res.get("COREAPPCODE").toString());
				bean.setNationalityName(res.get("NATIONALITY_NAME") == null ? "": res.get("NATIONALITY_NAME").toString());
				bean.setRemarks(res.get("REMARKS") == null ? "" : res.get("REMARKS").toString());
				bean.setStatus(res.get("STATUS") == null ? "" : res.get("STATUS").toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void insertinsMfgCountryMaster(MotorAdminBeanNew bean) {
		try {
			String query = "";
			Object[] args = null;
			if ("add".equalsIgnoreCase(bean.getMode())) {
				query = getQuery("INSERT_MFGCOUNTRY_MASTER");// COUNTRY_ID,
																// COUNTRY_CODE,
																// COUNTRY_NAME,
																// STATUS,
																// ENTRY_DATE,
																// COREAPPCODE,
																// REMARKS,
																// BRANCH_CODE,
																// NATIONALITY_NAME,
																// EFFECTIVE_DATE
				args = new Object[9];
				String sql = getQuery("GET_MAX_MFGCOUNTRYID");
				String id = (String) this.mytemplate.queryForObject(sql,
						String.class);
				args[0] = id;
				args[1] = bean.getCountryCode();
				args[2] = bean.getCountryName();
				args[3] = bean.getStatus();
				args[4] = bean.getCoreAppCode();
				args[5] = bean.getRemarks();
				args[6] = branchCode;
				args[7] = bean.getNationalityName();
				args[8] = bean.getEffectiveDate();

			} else {
				query = getQuery("UPDATE_MFGCOUNTRY_MASTER"); // COUNTRY_CODE =
																// ?,
																// COUNTRY_NAME
																// = ?, STATUS
																// =?,
																// COREAPPCODE
																// =?, REMARKS
																// =?,
																// NATIONALITY_NAME
																// =?,
																// EFFECTIVE_DATE
																// =? WHERE
																// COUNTRY_ID= ?
				args = new Object[8];
				args[0] = bean.getCountryCode();
				args[1] = bean.getCountryName();
				args[2] = bean.getStatus();
				args[3] = bean.getCoreAppCode();
				args[4] = bean.getRemarks();
				args[5] = bean.getNationalityName();
				args[6] = bean.getEffectiveDate();
				args[7] = bean.getCountryId();
			}
			LogManager.info("Query => " + query);
			LogManager.info("Arguments => " + StringUtils.join(args, ","));
			this.mytemplate.update(query, args);
		} catch (Exception e) {
			LogManager.debug("Exception Occured @ insertinsMfgCountryMaster "
					+ e);
			e.printStackTrace();
		}
	}

	public int checkCoreAppCodeCountryExists(MotorAdminBeanNew bean) {
		LogManager.info("Enter into checkCoreAppCodeCountryExists()");
		int i = 0;
		try {
			String query = getQuery("CHECK_CORE_APP_CODE_COUNTRY");
			Object[] args;
			if ("edit".equalsIgnoreCase(bean.getMode())) {
				query += "  AND COUNTRY_ID != ? ";
				args = new Object[] { bean.getCoreAppCode(), branchCode,bean.getCountryId() };
			} else {
				args = new Object[] { bean.getCoreAppCode(), branchCode };
			}
			LogManager.info("query =>" + query);
			LogManager.info("args===>" + StringUtils.join(args, ","));
			i = this.mytemplate.queryForInt(query, args);

		} catch (Exception e) {
			e.printStackTrace();
			LogManager.info("Error in checkCoreAppCodeCountryExists()");
		}

		LogManager.info("Exit from checkCoreAppCodeCountryExists()");
		return i;
	}

	public int checkCoreAppCodeOpCoverExists(MotorAdminBeanNew bean) {
		LogManager.info("Enter into checkCoreAppCodeOpCoverExists()");
		int i = 0;
		try {
			String query = getQuery("CHECK_CORE_APP_CODE_OPCOVER");
			Object[] args;
			if ("edit".equalsIgnoreCase(bean.getMode())) {
				query += "  AND OPCOVER_ID != ? ";
				args = new Object[] { bean.getCoreAppCode(), branchCode,
						bean.getOpCoverId() };
			} else {
				args = new Object[] { bean.getCoreAppCode(), branchCode };
			}
			LogManager.info("query =>" + query);
			LogManager.info("args===>" + StringUtils.join(args, ","));
			i = this.mytemplate.queryForInt(query, args);

		} catch (Exception e) {
			e.printStackTrace();
			LogManager.info("Error in checkCoreAppCodeOpCoverExists()");
		}

		LogManager.info("Exit from checkCoreAppCodeOpCoverExists()");
		return i;
	}


	public int checkCoreAppCodeOpCoverDetExists(MotorAdminBeanNew bean) {
		LogManager.info("Enter into checkCoreAppCodeOpCoverDetExists()");
		int i = 0;
		try {
			String query = getQuery("CHECK_CORE_APP_CODE_OPCOVER_DETAIL");
			Object[] args;
			if ("edit".equalsIgnoreCase(bean.getMode())) {
				query += "  AND OPCOVER_ID != ? AND POLICY_SUBTYPE_ID != ?";
				args = new Object[] { bean.getCoreAppCode(),groupId,
						bean.getOpCoverId(), bean.getPolicySubTypeId() };
			} else {
				args = new Object[] { bean.getCoreAppCode(),groupId };
			}
			LogManager.info("query =>" + query);
			LogManager.info("args===>" + StringUtils.join(args, ","));
			i = this.mytemplate.queryForInt(query, args);

		} catch (Exception e) {
			e.printStackTrace();
			LogManager.info("Error in checkCoreAppCodeOpCoverDetExists()");
		}

		LogManager.info("Exit from checkCoreAppCodeOpCoverDetExists()");
		return i;
	}

	public boolean checkOpPolicyExists(MotorAdminBeanNew bean) {
		LogManager.info("Enter into checkOpPolicyExists()");
		boolean i = false;
		try {
			if (bean.getMode() != null && "add".equalsIgnoreCase(bean.getMode())) {
				String query = getQuery("CHECK_OP_POLICY_EXISTS");
				Object[] args = new Object[] { bean.getOpCoverId(),bean.getPolicySubTypeId()};
				LogManager.info("query =>" + query);
				LogManager.info("args===>" + StringUtils.join(args, ","));
				int count = this.mytemplate.queryForInt(query, args);
				if (count > 0)
					i = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			LogManager.info("Error in checkOpPolicyExists()");
		}

		LogManager.info("Exit from checkOpPolicyExists()");
		return i;
	}

	// Area Coverage Master

	public List<Map<String, Object>> getAreaCoverage(MotorAdminBeanNew bean) {
		LogManager.info("Enter into getAreaCoverage()");
		List<Map<String, Object>> result = null;
		try {
			String query = getQuery("GET_AREACOVERAGE_MASTER");
			LogManager.info("query =>" + query);
			Object args[] = new Object[1];
			args[0] = branchCode;
			LogManager.info("Argument =>" + args[0]);
			result = this.mytemplate.queryForList(query, args);
		} catch (Exception e) {
			LogManager.error("Exception occured @ getAreaCoverage()" + e);
			e.printStackTrace();
		}
		LogManager.info("Exit from getAreaCoverage()");
		return result;
	}

	public List<Map<String, Object>> getAreaCoverageEdit(MotorAdminBeanNew bean) {
		LogManager.info("Enter into getAreaCoverageEdit()");
		List<Map<String, Object>> result = null;
		try {
			String query = getQuery("GET_AREACOVERAGE_FOR_EDIT");
			LogManager.info("query =>" + query);
			Object args[] = { bean.getAreaCoverageId(), branchCode };
			LogManager.info("Arguments =>" + StringUtils.join(args, ","));
			result = this.mytemplate.queryForList(query, args);
			if (result.size() > 0) {
				Map map = (Map) result.get(0);
				bean.setAreaCoverageId(map.get("AREACOVER_ID") == null ? "": map.get("AREACOVER_ID").toString());
				bean.setAreaCoverageCode(map.get("AREACOVER_CODE") == null ? "": map.get("AREACOVER_CODE").toString());
				bean.setAreaCoverageDescriptionEnglish(map.get("AREACOVER_DESC_ENGLISH") == null ? "" : map.get("AREACOVER_DESC_ENGLISH").toString());
				bean.setAreaCoverageDescriptionArabic(map.get("AREACOVER_DESC_ARABIC") == null ? "" : map.get("AREACOVER_DESC_ARABIC").toString());
				bean.setStatus(map.get("STATUS") == null ? "" : map.get("STATUS").toString());
				bean.setEffectiveDate(map.get("EFFECTIVE_DATE") == null ? "": map.get("EFFECTIVE_DATE").toString());
				bean.setRemarks(map.get("REMARKS") == null ? "" : map.get("REMARKS").toString());
			}
		} catch (Exception e) {
			LogManager.error("Exception Occured @ getAreaCoverageEdit()" + e);
			e.printStackTrace();
		}
		LogManager.info("Exit from getAreaCoverageEdit() ");
		return result;
	}

	public String getAreaCoverageInsert(MotorAdminBeanNew bean) {
		   String result = "";
		try {
			String query = "";
			Object[] args = null;
			String amendId = "";
			if ("Edit".equalsIgnoreCase(bean.getMode())) {
				query = getQuery("GET_EDIT_AREA_AMEND_ID");
				args = new Object[] { bean.getAreaCoverageId() };
				List<Map<String, Object>> res = this.mytemplate.queryForList(query, args);
				if (res.size() > 0)
					amendId = res.get(0).get("AMEND_ID") == null ? "" : res.get(0).get("AMEND_ID").toString();
			}

			if ("Add".equalsIgnoreCase(bean.getMode())|| ("Edit".equalsIgnoreCase(bean.getMode()) && StringUtils.isBlank(amendId))) {
				LogManager.info("Enter into getAreaCoverageInsert()");
				query = getQuery("GET_AREACOVERAGE_FOR_INSERT");
				args = new Object[9];
				if ("Add".equalsIgnoreCase(bean.getMode())) {
					String sql = getQuery("GET_MAX_AREACOVERAGE_ID");
					String areaCoverageId = (String) this.mytemplate.queryForObject(sql, String.class);

					args[0] = areaCoverageId;
					args[7] = areaCoverageId;
				} else {
					args[0] = bean.getAreaCoverageId();
					args[7] = bean.getAreaCoverageId();
				}
				args[1] = bean.getAreaCoverageCode();
				args[2] = bean.getAreaCoverageDescriptionEnglish();
				args[3] = bean.getAreaCoverageDescriptionArabic() == null ? "": bean.getAreaCoverageDescriptionArabic();
				args[4] = bean.getStatus();
				args[5] = bean.getEffectiveDate();
				args[6] = bean.getRemarks();
				args[8] = branchCode;
			} else if ("Edit".equalsIgnoreCase(bean.getMode())&& StringUtils.isNotBlank(amendId)) {
				query = getQuery("GET_AREACOVERAGE_FOR_UPDATE");
				args = new Object[9];
				args[0] = bean.getAreaCoverageCode();
				args[1] = bean.getAreaCoverageDescriptionEnglish();
				args[2] = bean.getAreaCoverageDescriptionArabic() == null ? "": bean.getAreaCoverageDescriptionArabic();
				args[3] = bean.getStatus();
				args[4] = bean.getEffectiveDate();
				args[5] = bean.getRemarks();
				args[6] = branchCode;
				args[7] = bean.getAreaCoverageId();
				args[8] = amendId;
			}
			LogManager.info("Query => " + query);
			LogManager.info("Arguments => " + StringUtils.join(args, ","));
			this.mytemplate.update(query, args);

		} catch (Exception e) {
			LogManager.debug("Exception Occured @ getAreaCoverageInsert() " + e);
			e.printStackTrace();
			result = e.toString();
		}
		LogManager.info("Exit from getAreaCoverageInsert()");
		return result;
	}

	public int checkAreaCoverageCode(MotorAdminBeanNew bean) {
		LogManager.info("Enter into checAreaCoverageCode()");
		int d = 0;
		try {
			Object[] args;
			query = getQuery("CHECK_AREACOVERAGE_CODE");
			if ("Edit".equalsIgnoreCase(bean.getMode())) {
				query += " AND AREACOVER_ID !=? ";
				args = new Object[] { bean.getAreaCoverageCode(), branchCode,
						bean.getAreaCoverageId() };
			} else {
				args = new Object[] { bean.getAreaCoverageCode(), branchCode };
			}
			LogManager.info("query =>" + query);
			LogManager.info("args=>" + StringUtils.join(args, ","));
			d = this.mytemplate.queryForInt(query, args);
		} catch (Exception e) {
			e.printStackTrace();
			LogManager.info("Error in checAreaCoverageCode()");
		}
		LogManager.info("Exit from checAreaCoverageCode()");
		return d;
	}

	public int checkDocumentDisplayOrder(MotorAdminBeanNew bean) {
		LogManager.info("Enter into the checkDocumentDisplayOrder()");
		int i = 0;
		try {
			query = getQuery("CHECK_DOCUMENTDISPLAY_ORDER");
			Object args[] = new Object[1];
			if ("Edit".equalsIgnoreCase(bean.getMode())) {
				query += " AND DOCUMENT_ID !=?";
				args = new Object[] { bean.getDisplayOrder(),
						bean.getDocumentId() };
			} else {
				args = new Object[] { bean.getDisplayOrder() };
			}
			LogManager.info("query =>" + query);
			LogManager.info("args===>" + StringUtils.join(args, ","));
			i = this.mytemplate.queryForInt(query, args);

		} catch (Exception e) {
			e.printStackTrace();
			LogManager.debug("Exception Occured @ checkDocumentDisplayOrder"
					+ e);
		}
		LogManager.info("Exit from checkDocumentDisplayOrder()");
		return i;
	}

	
	public int checkPolicyDate(MotorAdminBeanNew bean) {
		LogManager.info("Enter into the checkPolicyDate()");
		int i = 0;
		try {
			query = getQuery("CHECK_POLICY_DATE");
			Object args[] = new Object[1];
			if ("editCompreRate".equalsIgnoreCase(bean.getMode())) {
				query += " AND RATE_ID!=?";
				args = new Object[] { bean.getSubRateId(),
						bean.getPolicyStartDate(), bean.getPolicyEndDate(),bean.getRateId() };
			} else {
				args = new Object[] { bean.getSubRateId(),
						bean.getPolicyStartDate(), bean.getPolicyEndDate() };
			}
			LogManager.info("query =>" + query);
			LogManager.info("args===>" + StringUtils.join(args, ","));
			i = this.mytemplate.queryForInt(query, args);
		} catch (Exception e) {
			e.printStackTrace();
			LogManager.debug("Exception Occured @ checkPolicyDate" + e);
		}
		LogManager.info("Exit from checkPolicyDate()");
		return i;
	}

	public List<Map<String, Object>> getQuarter(MotorAdminBeanNew bean) {
		LogManager.info("Enter into getQuarter()");
		List<Map<String, Object>> result = null;
		try {
			String query = getQuery("GET_QUATER_MASTER");
			LogManager.info("query =>" + query);
			result = this.mytemplate.queryForList(query);
		} catch (Exception e) {
			LogManager.error("Exception occured @ getQuarter()" + e);
			e.printStackTrace();
		}
		LogManager.info("Exit from getQuarter()");
		return result;

	}

	public List<Map<String, Object>> getQuarterEdit(MotorAdminBeanNew bean) {
		LogManager.info("Enter into getQuarterCEdit()");
		List<Map<String, Object>> result = null;
		try {
			String query = getQuery("GET_QUARTER_FOR_EDIT");
			LogManager.info("query =>" + query);
			Object args[] = { bean.getSerialQuarter() };
			LogManager.info("Arguments =>" + StringUtils.join(args, ","));
			result = this.mytemplate.queryForList(query, args);
			if (result.size() > 0) {
				Map map = (Map) result.get(0);
				bean.setPolicyType(map.get("POLICYTYPE_ID") == null ? "" : map.get("POLICYTYPE_ID").toString());
				bean.setQuarterId(map.get("QUATERID") == null ? "" : map.get("QUATERID").toString());
				bean.setStartRange(map.get("STARTRANGE") == null ? "" : map.get("STARTRANGE").toString());
				bean.setEndRange(map.get("ENDRANGE") == null ? "" : map.get("ENDRANGE").toString());
				bean.setDiscount(map.get("DISCOUNT_PERC") == null ? "" : map.get("DISCOUNT_PERC").toString());
				bean.setStatus(map.get("STATUS") == null ? "" : map.get("STATUS").toString());
				bean.setEffectiveDate(map.get("EFFECTIVE_DATE") == null ? "": map.get("EFFECTIVE_DATE").toString());
				bean.setRemarks(map.get("REMARKS") == null ? "" : map.get("REMARKS").toString());
			}
		} catch (Exception e) {
			LogManager.error("Exception Occured @ getQuarterEdit()" + e);
			e.printStackTrace();
		}
		LogManager.info("Exit from getQuarterEdit() ");

		return result;
	}

	/*public String getQuarterInsert(MotorAdminBeanNew bean) {
		String amendId = "",result="";
		LogManager.info("Enter into  getQuarterInsert() ");
		try {
			String query = "";
			Object[] args = null;
			//String amendId = "";
			if ("add".equalsIgnoreCase(bean.getMode())|| ("edit".equalsIgnoreCase(bean.getMode()) && StringUtils.isBlank(amendId)))
			{
				query = getQuery("GET_EDIT_QUARTER_AMEND_ID");
				args = new Object[] { bean.getSerialQuarter() };
				List<Map<String, Object>> res = this.mytemplate.queryForList(query, args);
				LogManager.info("Query"+query);
				LogManager.info("args"+args);
				if (res.size() > 0)
					amendId = res.get(0).get("AMEND_ID") == null ? "" : res.get(0).get("AMEND_ID").toString();
				else
					amendId ="0";
				}
			if ("Add".equalsIgnoreCase(bean.getMode()))
			{
			
				query = getQuery("GET_QUARTER_FOR_INSERT");
				args = new Object[10];
				String sql = getQuery("GET_MAX_QUARTER_SERIAL_ID");
				String SId = (String) this.mytemplate.queryForObject(sql,
						String.class);
				// args[0] = areaCoverageId;
				args[8] = amendId;
				args[9] = SId;

				args[0] = bean.getPolicyType();
				args[1] = bean.getQuarterId();
				args[2] = bean.getStartRange();
				args[3] = bean.getEndRange();
				args[4] = bean.getDiscount();
				args[5] = bean.getStatus();
				args[6] = bean.getEffectiveDate();
				args[7] = bean.getRemarks();
				// Object sno = null;
				// args[8] =bean.getSerialQuarter();
			} else if ("Edit".equalsIgnoreCase(bean.getMode())
					&& StringUtils.isNotBlank(amendId)) {
				query = getQuery("GET_QUARTER_FOR_UPDATE");
				args = new Object[10];
				args[0] = bean.getPolicyType();
				args[1] = bean.getQuarterId();
				args[2] = bean.getStartRange();
				args[3] = bean.getEndRange();
				args[4] = bean.getDiscount();
				args[5] = bean.getStatus();
				args[6] = bean.getEffectiveDate();
				args[7] = bean.getRemarks();
				args[8] = bean.getSerialQuarter();
				args[9] = amendId;
			}
			LogManager.info("Query => " + query);
			LogManager.info("Arguments => " + StringUtils.join(args, ","));
			this.mytemplate.update(query, args);

		} catch (Exception e) {
			LogManager.debug("Exception Occured @ getQuarterInsert() " + e);
			e.printStackTrace();
			result=e.getMessage();
		}
		LogManager.info("Exit from getQuartInsert()");
		return result;
	}*/
	
	public String getQuarterInsert(MotorAdminBeanNew bean) {
		String amendId = "",result="";
		LogManager.info("Enter into  getQuarterInsert() ");
		try {
			String query = "";
			Object[] args = null;
			//String amendId = "";
			if ("add".equalsIgnoreCase(bean.getMode())|| ("edit".equalsIgnoreCase(bean.getMode()) && StringUtils.isBlank(amendId)))
			{
				query = getQuery("GET_EDIT_QUARTER_AMEND_ID");
				args = new Object[] { bean.getSerialQuarter() };
				List<Map<String, Object>> res = this.mytemplate.queryForList(query, args);
				LogManager.info("Query"+query);
				LogManager.info("args"+args);
				if (res.size() > 0)
					amendId = res.get(0).get("AMEND_ID") == null ? "" : res.get(0).get("AMEND_ID").toString();
				}
			if ("Add".equalsIgnoreCase(bean.getMode()) || ("edit".equalsIgnoreCase(bean.getMode()) && StringUtils.isBlank(amendId)))
			{
			
				query = getQuery("GET_QUARTER_FOR_INSERT");
				args = new Object[11];
				if ("Add".equalsIgnoreCase(bean.getMode())){
				String sql = getQuery("GET_MAX_QUARTER_SERIAL_ID");
				String SId = (String) this.mytemplate.queryForObject(sql,
						String.class);
				// args[0] = areaCoverageId;
				args[8] = SId;
				args[9] = SId;
				}else{
					args[8] = bean.getSerialQuarter();
					args[9] = bean.getSerialQuarter();
				}
				args[0] = bean.getPolicyType();
				args[1] = bean.getQuarterId();
				args[2] = bean.getStartRange();
				args[3] = bean.getEndRange();
				args[4] = bean.getDiscount();
				args[5] = bean.getStatus();
				args[6] = bean.getEffectiveDate();
				args[7] = bean.getRemarks();
				args[10] = productId;
				// Object sno = null;
				// args[8] =bean.getSerialQuarter();
			} else if ("Edit".equalsIgnoreCase(bean.getMode())
					&& StringUtils.isNotBlank(amendId)) {
				query = getQuery("GET_QUARTER_FOR_UPDATE");
				args = new Object[10];
				args[0] = bean.getPolicyType();
				args[1] = bean.getQuarterId();
				args[2] = bean.getStartRange();
				args[3] = bean.getEndRange();
				args[4] = bean.getDiscount();
				args[5] = bean.getStatus();
				args[6] = bean.getEffectiveDate();
				args[7] = bean.getRemarks();
				args[8] = bean.getSerialQuarter();
				args[9] = amendId;
			}
			LogManager.info("Query => " + query);
			LogManager.info("Arguments => " + StringUtils.join(args, ","));
			this.mytemplate.update(query, args);

		} catch (Exception e) {
			LogManager.debug("Exception Occured @ getQuarterInsert() " + e);
			e.printStackTrace();
			result=e.getMessage();
		}
		LogManager.info("Exit from getQuartInsert()");
		return result;
	}
	
	public int getQuarterExists(MotorAdminBeanNew bean)
	{
		LogManager.info("Enter into getQuarterExists()");
		int i = 0;
		try {
			String query = getQuery("GET_QUARTER_EXIST");
			Object[] args;
			if ("edit".equalsIgnoreCase(bean.getMode())) {
				query += "AND SNO!=?";
				args = new Object[] {bean.getPolicyType(),bean.getQuarterId(),bean.getSerialQuarter()};
			} else {
				args = new Object[] {bean.getPolicyType(),bean.getQuarterId()};
			}
			LogManager.info("query =>" + query);
			LogManager.info("args===>" + StringUtils.join(args, ","));
			i = this.mytemplate.queryForInt(query, args);

		} catch (Exception e) {
			e.printStackTrace();
			LogManager.info("Error in getQuarterExists()");
		}

		LogManager.info("Exit from getQuarterExists()");
		return i;
	}

		public int checkModelBody(MotorAdminBeanNew bean) {
			int result=0;
			try{
				query=getQuery("CHECK_MODEL_BODY_COMBINATION");
				Object args[] = null;
				if("editModel".equalsIgnoreCase(bean.getMode())){
					query += " AND MODEL_ID !=? ";
					args =new Object[]{bean.getBodyType(),bean.getModelName(),branchCode,bean.getModelId()};
				}else{
					args =new Object[]{bean.getBodyType(),bean.getModelName(),branchCode};	
				}
				LogManager.info("query =>"+ query);
				LogManager.info("args===>"+StringUtils.join(args,","));
				result = this.mytemplate.queryForInt(query,args);
			}catch (Exception e) {
				e.printStackTrace();
			}
			return result;
		}
		public int checkNCBVehicleUsage(MotorAdminBeanNew bean) {
			int result=0;
			try{
				query=getQuery("CHECK_NCB_MASTER");
				Object args[] = null;
				if("editCompreRate".equalsIgnoreCase(bean.getMode())){
					query += " AND S_NO !=?";
					args= new Object[]{bean.getNcbYear(),bean.getVehicleType(),bean.getNcbId()};
				}else{
					args= new Object[]{bean.getNcbYear(),bean.getVehicleType()};	
				}
				LogManager.info("query =>"+ query);
				LogManager.info("args===>"+StringUtils.join(args,","));
				result = this.mytemplate.queryForInt(query,args);
			}catch (Exception e) {
				e.printStackTrace();
			}
			return result;
		}
		//Constant Master
		public List<Map<String,Object>> getConstatntDetailList(MotorAdminBeanNew bean){
			List<Map<String,Object>> drpdwn=new ArrayList<Map<String,Object>>();
			try{
				LogManager.info("Enter into the getConstatntDetailList   ()");
				query =getQuery("GET_ITEM_LIST");
				LogManager.info("Query==>"+query);
				drpdwn=this.mytemplate.queryForList(query);
				
			}catch(Exception e){
				e.printStackTrace();
				LogManager.info("Exception Occured @ getConstatntDetailList()");
			}
			LogManager.info("Exit from the getConstatntDetailList()");
			return drpdwn;
			
		}
		
		public List<Map<String,Object>> getItemDropDown(MotorAdminBeanNew bean){
			List<Map<String,Object>> drpdwn=new ArrayList<Map<String,Object>>();
			try{
				LogManager.info("Enter into the getConstatntDetailList   ()");
				query =getQuery("GET_CONSTANT_LIST");
				LogManager.info("Query==>"+query);
				drpdwn=this.mytemplate.queryForList(query);
				
			}catch(Exception e){
				e.printStackTrace();
				LogManager.info("Exception Occured @ getConstatntDetailList()");
			}
			LogManager.info("Exit from the getConstatntDetailList()");
			return drpdwn;
			
		}

		public void getInsertConstant(MotorAdminBeanNew bean) {
				try {
					Object[] args = null;
			/*		if ("edit".equalsIgnoreCase(bean.getMode())) {
						query = getQuery("GET_EDIT_VTYPE_AMEND_ID");
						args = new Object[] { bean.getVtypeId() };
						LogManager.info("Query => " + query);
						LogManager.info("Arguments => " + StringUtils.join(args, ","));
						List<Map<String, Object>> res = this.mytemplate.queryForList(query, args);
						if (res.size() > 0)
							amendId = res.get(0).get("AMEND_ID") == null ? "" : res.get(0).get("AMEND_ID").toString();
					}*/
					if ("add".equalsIgnoreCase(bean.getMode())) {
						query = getQuery("INSERT_ITEM_VALUE");
						args = new Object[7];
						if("***ADD NEW***".equalsIgnoreCase(bean.getItemType())){
						args[0] = bean.getItem();
						args[1] = bean.getItem();
						}else{
							args[0] = bean.getItemType();
							args[1] = bean.getItemType();
						}
						args[2] = bean.getItemValue();
						args[3] = bean.getItemDesc();
						args[4] = bean.getParam1();
						args[5] = bean.getParam1();
						args[6] = bean.getStatus();
					} else if ("edit".equalsIgnoreCase(bean.getMode())) {
						query = getQuery("UPDATE_ITEM_VALUE");
						args = new Object[8];
						args[0] = bean.getItemType();
						args[1] = bean.getItemCode();
						args[2] = bean.getItemValue();
						args[3] = bean.getItemDesc();
						args[4] = bean.getParam1();
						args[5] = bean.getParam1();
						args[6] = bean.getStatus();
						args[7] = bean.getItemId();
					}
					LogManager.info("Query => " + query);
					LogManager.info("Arguments => " + StringUtils.join(args, ","));
					this.mytemplate.update(query, args);

				} catch (Exception e) {
					LogManager.debug("Exception Occured @ getInsertConstant " + e);
					e.printStackTrace();
				}
		}
		
		public List<Map<String, Object>> getEditConstant(MotorAdminBeanNew bean) {
			LogManager.info("Enter into getEditConstant()");
			List<Map<String, Object>> result = null;
			try {
				String query = getQuery("GET_ITEM_FOR_EDIT");
				LogManager.info("query =>" + query);
				Object args[] = {bean.getItemId()};
				LogManager.info("Arguments =>" + StringUtils.join(args, ","));
				result = this.mytemplate.queryForList(query,args);
				if (result.size() > 0) {
					Map map = (Map) result.get(0);
						bean.setItemId(map.get("ITEM_ID") == null ? "": map.get("ITEM_ID").toString());
						bean.setItemType(map.get("ITEM_TYPE") == null ? "": map.get("ITEM_TYPE").toString());
						bean.setItemCode(map.get("ITEM_CODE") == null ? "": map.get("ITEM_CODE").toString());
						bean.setItemValue(map.get("ITEM_VALUE") == null ? "": map.get("ITEM_VALUE").toString());
						bean.setItemDesc(map.get("ITEM_DESC") == null ? "": map.get("ITEM_DESC").toString());
						bean.setParam1(map.get("PARAM1") == null ? "": map.get("PARAM1").toString());
						bean.setParam2(map.get("PARAM2") == null ? "": map.get("PARAM2").toString());
						bean.setStatus(map.get("STATUS") == null ? "": map.get("STATUS").toString());		
				}
			} catch (Exception e) {
				LogManager.error("Exception Occured @ getEditConstant()" + e);
				e.printStackTrace();
			}
			LogManager.info("Exit from getEditConstant() ");
			return result;
		}

		public List<Map<String, Object>> getMoUploadImg(MotorAdminBeanNew bean) {
			LogManager.info("Enter into getMoUploadImg()");
			List<Map<String,Object>> result=new ArrayList<Map<String,Object>>();
			try{
				query =getQuery("GET_MOBILE_UPLOAD_DETAIL");
				LogManager.info("query =>" + query);
				Object args[] = {bean.getStartDate(),bean.getEndDate()};
				LogManager.info("Arguments =>" + StringUtils.join(args, ","));
				result = this.mytemplate.queryForList(query,args);
				
			}catch (Exception e) {
				e.printStackTrace();
			}
			LogManager.info("Exit from getMoUploadImg() ");
			return result;
		}
		
		public List<Map<String, Object>> getMoUploadImgView(MotorAdminBeanNew bean) {
			LogManager.info("Enter into getMoUploadImg()");
			List<Map<String,Object>> result=new ArrayList<Map<String,Object>>();
			try{
				query =getQuery("GET_MOBILE_UPLOAD_DOCUMENT");
				LogManager.info("query =>" + query);
				Object args[] = {bean.getRefNo()};
				LogManager.info("Arguments =>" + StringUtils.join(args, ","));
				result = this.mytemplate.queryForList(query,args);
				if (result.size() > 0) {
					Map map = (Map) result.get(0);
						bean.setCustName(map.get("CUSTOMER_NAME") == null ? "": map.get("CUSTOMER_NAME").toString());
						bean.setPolicyNo(map.get("POLICY_NO") == null ? "": map.get("POLICY_NO").toString());
						bean.setDocType(map.get("DOCUMENT_TYPE") == null ? "": map.get("DOCUMENT_TYPE").toString());
						bean.setDeviceManuf(map.get("DEVICE_MANUF") == null ? "": map.get("DEVICE_MANUF").toString());
						bean.setUploadDate(map.get("UPLOADED_DATE") == null ? "": map.get("UPLOADED_DATE").toString());
						bean.setCount1(map.get("COUNT") == null ? "": map.get("COUNT").toString());
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
			LogManager.info("Exit from getMoUploadImg() ");
			return result;
		}
		
		public List<Map<String, Object>> getMoUploadImgViewList(MotorAdminBeanNew bean) {
			LogManager.info("Enter into getMoUploadImgViewList()");
			List<Map<String,Object>> result=new ArrayList<Map<String,Object>>();
			try{
				query =getQuery("GET_MOBILE_UPLOAD_IMAGE");
				LogManager.info("query =>" + query);
				Object args[] = {bean.getRefNo()};
				LogManager.info("Arguments =>" + StringUtils.join(args, ","));
				result = this.mytemplate.queryForList(query,args);
				
			}catch (Exception e) {
				e.printStackTrace();
			}
			LogManager.info("Exit from getMoUploadImgViewList() ");
			return result;
		}

		public String getFilePath(MotorAdminBeanNew bean) {
			LogManager.info("Enter into getFilePath()");
			List<Map<String,Object>> result=new ArrayList<Map<String,Object>>();
			try{
				query =getQuery("GET_MOBILE_UPLOAD_FILEPATH");
				LogManager.info("query =>" + query);
				Object args[] = {bean.getDocumentId()};
				LogManager.info("Arguments =>" + StringUtils.join(args, ","));
				result = this.mytemplate.queryForList(query,args);
				if (result.size() > 0) {
					Map map = (Map) result.get(0);
						bean.setFilePath(map.get("FILE_PATH") == null ? "": map.get("FILE_PATH").toString());
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
			LogManager.info("Exit from getFilePath() ");
			return null;
		}

		//Notify Master
		public List<Map<String, Object>> getNotifyList() {
			LogManager.info("Enter into the @getNotifyList");
			List<Map<String,Object>> result=new ArrayList<Map<String,Object>>();
			try{
				query = getQuery("GET_NOTIFY_LIST");
				result=this.mytemplate.queryForList(query);
				LogManager.info("Query++>"+query);
			}catch (Exception e) {
				e.printStackTrace();
			}
			LogManager.info("Exit from the @getNotifyList");
			return result;
		}

		public void getEditNotify(MotorAdminBeanNew bean) {
			LogManager.info("Enter into the @getEditNotify");
			List<Map<String,Object>> result=new ArrayList<Map<String,Object>>();
			try{
			query = getQuery("GET_NOTIFY_FOR_EDIT");
			LogManager.info("query =>" + query);
			Object args[] = {bean.getNotifyId()};
			LogManager.info("Arguments =>" + StringUtils.join(args, ","));
			result = this.mytemplate.queryForList(query,args);
			if (result.size() > 0) {
				Map map = (Map) result.get(0);
					bean.setNotifyDesc(map.get("MSG_DESCRIPTION") == null ? "": map.get("MSG_DESCRIPTION").toString());
					bean.setStartDate(map.get("START_DATE") == null ? "" : map.get("START_DATE").toString());
					bean.setEndDate(map.get("END_DATE") == null ? "" : map.get("END_DATE").toString());
					bean.setRemarks(map.get("REMARKS") == null ? "" : map.get("REMARKS").toString());
					bean.setStatus(map.get("STATUS") == null ? "" : map.get("STATUS").toString());
					//START_DATE,END_DATE,ENTRY_DATE,STATUS:
				}
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			LogManager.info("Exit from the @getEditNotify");
		}

		public void getInsertNotify(MotorAdminBeanNew bean) {
			LogManager.info("Enter into the @getInsertNotify");
			try {
				Object[] args = null;
				if ("add".equalsIgnoreCase(bean.getMode())) {
					query = getQuery("INSERT_NOTIFY_DETAIL");
					args = new Object[5];
					args[0] = bean.getNotifyDesc();
					args[1] = bean.getStartDate();
					args[2] = bean.getEndDate();
					args[3] = bean.getRemarks();
					args[4] = bean.getStatus();
				} else if ("edit".equalsIgnoreCase(bean.getMode())) {
					query = getQuery("UPDATE_NOTIFY_DETAIL");
					args = new Object[6];
					args[0] = bean.getNotifyDesc();
					args[1] = bean.getStartDate();
					args[2] = bean.getEndDate();
					args[3] = bean.getRemarks();
					args[4] = bean.getStatus();
					args[5] = bean.getNotifyId();
				}
				LogManager.info("Query => " + query);
				LogManager.info("Arguments => " + StringUtils.join(args, ","));
				this.mytemplate.update(query, args);

			} catch (Exception e) {
				LogManager.debug("Exception Occured @ getInsertNotify " + e);
				e.printStackTrace();
			}
			LogManager.info("Exit from the @getInsertNotify");
		}
		
		//Notify Master
		public List<Map<String, Object>> getPaymentBankList() {
			LogManager.info("Enter into the @getPaymentBankList");
			List<Map<String,Object>> result=new ArrayList<Map<String,Object>>();
			try{
				query = getQuery("GET_PAYMENTBANK_LIST");
				result=this.mytemplate.queryForList(query);
				LogManager.info("Query++>"+query);
			}catch (Exception e) {
				e.printStackTrace();
			}
			LogManager.info("Exit from the @getPaymentBankList");
			return result;
		}

		public void getEditPaymentBank(MotorAdminBeanNew bean) {
			LogManager.info("Enter into the @getEditPaymentBank");
			List<Map<String,Object>> result=new ArrayList<Map<String,Object>>();
			try{
			query = getQuery("GET_PAYMENTBANK_EDIT");
			LogManager.info("query =>" + query);
			Object args[] = {bean.getPaymentBankId()};
			LogManager.info("Arguments =>" + StringUtils.join(args, ","));
			result = this.mytemplate.queryForList(query,args);
			if (result.size() > 0) {
				Map map = (Map) result.get(0);
					bean.setPaymentBankId(map.get("PAYMENTBANK_ID") == null ? "": map.get("PAYMENTBANK_ID").toString());
					bean.setPaymentType(map.get("PAYMENT_TYPE") == null ? "" : map.get("PAYMENT_TYPE").toString());
					bean.setAccHolder(map.get("ACCOUNT_HOLDER") == null ? "" : map.get("ACCOUNT_HOLDER").toString());
					bean.setAccNumber(map.get("ACCOUNT_NUMBER") == null ? "" : map.get("ACCOUNT_NUMBER").toString());
					bean.setBankEngName(map.get("BANK_NAME") == null ? "" : map.get("BANK_NAME").toString());
					bean.setBankBranch(map.get("BRANCH_NAME") == null ? "": map.get("BRANCH_NAME").toString());
					bean.setBankCode(map.get("BRANCH_CODE") == null ? "" : map.get("BRANCH_CODE").toString());
					bean.setCurrencyType(map.get("CURRENCY_TYPE") == null ? "" : map.get("CURRENCY_TYPE").toString());
					bean.setSwiftCode(map.get("SWIFT_CODE") == null ? "" : map.get("SWIFT_CODE").toString());
					bean.setEffectiveDate(map.get("EFFECTIVE_DATE") == null ? "" : map.get("EFFECTIVE_DATE").toString());
					bean.setRemarks(map.get("REMARKS") == null ? "" : map.get("REMARKS").toString());
					bean.setStatus(map.get("STATUS") == null ? "" : map.get("STATUS").toString());
				}
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			LogManager.info("Exit from the @getEditPaymentBank");
		}

		public String getInsertPaymentBank(MotorAdminBeanNew bean) {
			String result = "";
			try {

				String query = "";
				String amendId = "";
				Object[] args = null;
				if ("edit".equalsIgnoreCase(bean.getMode())) {
					query = getQuery("GET_EDIT_PAYMENTBANK_AMEND");
					args = new Object[] { bean.getPaymentBankId() };
					LogManager.info("Query => " + query);
					LogManager.info("Arguments => " + StringUtils.join(args, ","));
					List<Map<String, Object>> res = this.mytemplate.queryForList(query, args);
					if (res.size() > 0)
						amendId = res.get(0).get("AMEND_ID") == null ? "" : res
								.get(0).get("AMEND_ID").toString();
				}
				if ("add".equalsIgnoreCase(bean.getMode())
						|| ("edit".equalsIgnoreCase(bean.getMode()) && StringUtils.isBlank(amendId))) {
					query = getQuery("INSERT_PAYMENTBANK_DETAIL");
					args = new Object[13];
					if ("add".equalsIgnoreCase(bean.getMode())) {
						String sql = getQuery("GET_MAX_PAYMENT_BANK_ID");
						int paymentBankId = this.mytemplate.queryForInt(sql);
						args[0] = paymentBankId;
						args[1] = paymentBankId;
					} else {
						args[0] = bean.getPaymentBankId();
						args[1] = bean.getPaymentBankId();
					}
					args[2] = bean.getPaymentType();
					args[3] = bean.getAccHolder();
					args[4] = bean.getEffectiveDate();
					args[5] = bean.getStatus();
					args[6] = bean.getBankEngName();
					args[7] = bean.getAccNumber();
					args[8] = bean.getBankBranch();
					args[9] = bean.getBankCode();
					args[10] = bean.getCurrencyType();
					args[11] = bean.getSwiftCode();
					args[12] = bean.getRemarks();
				} else if ("edit".equalsIgnoreCase(bean.getMode()) && StringUtils.isNotBlank(amendId)) {
					query = getQuery("UPDATE_PAYMENTBANK_DETAIL");
					args = new Object[13];
					args[0] = bean.getPaymentType();
					args[1] = bean.getAccHolder();
					args[2] = bean.getEffectiveDate();
					args[3] = bean.getStatus();
					args[4] = bean.getBankEngName();
					args[5] = bean.getAccNumber();
					args[6] = bean.getBankBranch();
					args[7] = bean.getBankCode();
					args[8] = bean.getCurrencyType();
					args[9] = bean.getSwiftCode();
					args[10] = bean.getRemarks();
					args[11] = bean.getPaymentBankId();
					args[12] = amendId;
				}
				LogManager.info("Query => " + query);
				LogManager.info("Arguments => " + StringUtils.join(args, ","));
				this.mytemplate.update(query, args);
			} catch (Exception e) {
				e.printStackTrace();
				LogManager.info("Excwption Occured @getInsertPaymentBank"+e);
				result = e.toString();
			}
			return result;
		}
		public boolean checkPBExit(MotorAdminBeanNew bean) {
			LogManager.info("Enter into checkPBExit()");
			boolean i = false;
			try {
					String query = getQuery("COUNT_PAYMENTBANK");
					Object[] args = new Object[] { bean.getCurrencyType(),bean.getPaymentType()};
					LogManager.info("query =>" + query);
					LogManager.info("args===>" + StringUtils.join(args, ","));
					int count = this.mytemplate.queryForInt(query, args);
					if (count > 0)
						i = true;
			} catch (Exception e) {
				e.printStackTrace();
				LogManager.info("Error in checkPBExit()");
			}

			LogManager.info("Exit from checkPBExit()");
			return i;
		}

		public List<Object> getUserList(String user, String productId) {
			LogManager.info("Enter into getUserList()");
			List<Object> userList=null;	
			String sql="";
			try {
				sql="SELECT DISTINCT LM.LOGIN_ID LOGIN_ID,LM.AGENCY_CODE,BCM.COMPANY_NAME USERNAME FROM LOGIN_MASTER LM, BROKER_COMPANY_MASTER BCM WHERE LM.AGENCY_CODE = BCM.AGENCY_CODE";
				LogManager.info("query =>" + sql);
				userList=this.mytemplate.queryForList(sql);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return userList;
		}

		public List<Map<String, Object>> getBranchList() {
			LogManager.info("Enter into getUserList()");
			List<Map<String, Object>> result=null;
			 try{
				  String query=getQuery("GET_LOGIN_BRANCH"); 
				  LogManager.info("query =>" + query);
				  result= this.mytemplate.queryForList(query); 
				 
			 }catch (Exception e) {
				e.printStackTrace(); 
			}
			 return result;
		}

		public List<Map<String, Object>> getMotorRateList(MotorAdminBeanNew bean) {
			List<Map<String, Object>> result = new ArrayList<Map<String,Object>>();
			List<Map<String,Object>> resNew=new ArrayList<Map<String,Object>>();
			String factorId="";
			try {
				String type="VIEW";
				query = "SELECT TO_CHAR (SYSDATE+1,'DD/MM/YYYY') TODAYDATE FROM DUAL";
				String todayDate=(String) this.mytemplate.queryForObject(query, String.class);
				String rateEndDate=bean.getRateEndDate()==null||"".equalsIgnoreCase(bean.getRateEndDate())?todayDate:bean.getRateEndDate().toString();
				bean.setRateEndDate(rateEndDate);
				String query="SELECT * FROM TABLE(MASTER_PKG.DYNAMIC_MASTER_RATE(?,?,?,?,?,?,?,?,?,?,?,?,?))";//"SELECT * FROM TABLE(MASTER_PKG.DYNAMIC_MASTER_RATE('1','1','1','01','','','','','','','90031','26/03/2021','VIEW') )";
				Object args[] =  new Object[] {bean.getPolicyType(),bean.getFactorId(),bean.getVehicleType(),bean.getBranchCode(),"","","","","","", bean.getBrokerId(), rateEndDate,type };

				LogManager.info("Query => " + queryFrammer(query, args));
				result = this.mytemplate.queryForList(query, args);
				
				Object[] obj = new Object[]{bean.getFactorId()};
				query="SELECT FACTOR_DESC,PARAM1,PARAM2,PARAM3,PARAM4,PARAM5,PARAM6,PARAM7,PARAM8,PARAM9,PARAM10,"
						//+ "PARAM11,PARAM12,PARAM13,PARAM14,PARAM15,CONFIG_TYPE,"
						+ "RATING_TYPE FROM MOTOR_FACTOR_REFERENCE WHERE POLICYTYPE_ID LIKE '%"+bean.getPolicyType()+"%' AND FACTOR_ID=?";
				LogManager.info("Query==>"+queryFrammer(query, obj));
				resNew=this.mytemplate.queryForList(query,obj);
				if(resNew!=null && resNew.size()>0){
					bean.setParamHead1(resNew.get(0).get("PARAM1")==null?"PARAM1":resNew.get(0).get("PARAM1").toString());
					bean.setParamHead2(resNew.get(0).get("PARAM2")==null?"PARAM2":resNew.get(0).get("PARAM2").toString());
					bean.setParamHead3(resNew.get(0).get("PARAM3")==null?"PARAM3":resNew.get(0).get("PARAM3").toString());
					bean.setParamHead4(resNew.get(0).get("PARAM4")==null?"PARAM4":resNew.get(0).get("PARAM4").toString());
					bean.setParamHead5(resNew.get(0).get("PARAM5")==null?"PARAM5":resNew.get(0).get("PARAM5").toString());
					bean.setParamHead6(resNew.get(0).get("PARAM6")==null?"PARAM6":resNew.get(0).get("PARAM6").toString());
					bean.setParamHead7(resNew.get(0).get("PARAM7")==null?"PARAM7":resNew.get(0).get("PARAM7").toString());
					bean.setParamHead8(resNew.get(0).get("PARAM8")==null?"PARAM8":resNew.get(0).get("PARAM8").toString());
					bean.setParamHead9(resNew.get(0).get("PARAM9")==null?"PARAM9":resNew.get(0).get("PARAM9").toString());
					bean.setParamHead10(resNew.get(0).get("PARAM10")==null?"PARAM10":resNew.get(0).get("PARAM10").toString());
//					bean.setParamHead11(resNew.get(0).get("PARAM11")==null?"PARAM11":resNew.get(0).get("PARAM11").toString());
//					bean.setParamHead12(resNew.get(0).get("PARAM12")==null?"PARAM12":resNew.get(0).get("PARAM12").toString());
//					bean.setParamHead13(resNew.get(0).get("PARAM13")==null?"PARAM13":resNew.get(0).get("PARAM13").toString());
//					bean.setParamHead14(resNew.get(0).get("PARAM14")==null?"PARAM14":resNew.get(0).get("PARAM14").toString());
//					bean.setParamHead15(resNew.get(0).get("PARAM15")==null?"PARAM15":resNew.get(0).get("PARAM15").toString());
				}else{
					bean.setParamHead1("PARAM1");
					bean.setParamHead2("PARAM2");
					bean.setParamHead3("PARAM3");
					bean.setParamHead4("PARAM4");
					bean.setParamHead5("PARAM5");
					bean.setParamHead6("PARAM6");
					bean.setParamHead7("PARAM7");
					bean.setParamHead8("PARAM8");
					bean.setParamHead9("PARAM9");
					bean.setParamHead10("PARAM10");
//					bean.setParamHead11("PARAM11");
//					bean.setParamHead12("PARAM12");
//					bean.setParamHead13("PARAM13");
//					bean.setParamHead14("PARAM14");
//					bean.setParamHead15("PARAM15");
				}
			} catch (Exception e) {
				LogManager.info("Exception Occured @ getMotorRateList ");
				e.printStackTrace();
			}
			return result;
		}

		public void setRateConfigDetails(MotorAdminBeanNew bean) {
			List<Map<String, Object>> result = null;
			String factorId="";
			try {
				//String query = getQuery("GET_MOTOR_POLICY_TYPE_RATE_LIST");
				factorId="1".equalsIgnoreCase(bean.getPolicyType())?"1":"2";
				String query = "SELECT FACTOR_RATE_ID, POLICYTYPE_ID, AGENCY_CODE, FACTOR_ID, AMEND_ID, RATE, STATUS, TO_CHAR(EFFECTIVE_DATE,'DD/MM/YYYY') EFFECTIVE_DATE, TO_CHAR(ENTRY_DATE,'DD/MM/YYYY') ENTRY_DATE, TO_CHAR(END_DATE,'DD/MM/YYYY') END_DATE, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, PARAM6, PARAM7, PARAM8, PARAM9, PARAM10, CONFIG_TYPE, REMARKS, RATING_TYPE, DIVISION_CODE, CUSTOMERCODE, ELECTRICAL, NONELECTRICAL, VEHUSAGEID, BODYID FROM MOTOR_FACTOR_RATE_MASTER MFR WHERE FACTOR_RATE_ID=? AND AMEND_ID=(SELECT MAX(AMEND_ID) FROM MOTOR_FACTOR_RATE_MASTER MFRM WHERE MFR.AGENCY_CODE=MFRM.AGENCY_CODE AND MFR.POLICYTYPE_ID=MFRM.POLICYTYPE_ID AND MFR.DIVISION_CODE=MFRM.DIVISION_CODE AND MFR.FACTOR_ID=MFRM.FACTOR_ID AND MFR.VEHUSAGEID=MFRM.VEHUSAGEID AND MFR.BODYID=MFRM.BODYID)";
				Object args[] =  new Object[] { bean.getFactorRateId()};

				LogManager.info("Query => " + queryFrammer(query, args));
				result = this.mytemplate.queryForList(query, args);
				if(result!=null && result.size()>0){
					bean.setPolicyTypeId(result.get(0).get("POLICYTYPE_ID")==null?"":result.get(0).get("POLICYTYPE_ID").toString());
					bean.setAgencyCode(result.get(0).get("AGENCY_CODE")==null?"":result.get(0).get("AGENCY_CODE").toString());
					bean.setFactorId(result.get(0).get("FACTOR_ID")==null?"":result.get(0).get("FACTOR_ID").toString());
					bean.setRate(result.get(0).get("RATE")==null?"":result.get(0).get("RATE").toString());
					bean.setStatus(result.get(0).get("STATUS")==null?"":result.get(0).get("STATUS").toString());
					bean.setEffectiveDate(result.get(0).get("EFFECTIVE_DATE")==null?"":result.get(0).get("EFFECTIVE_DATE").toString());
					bean.setEntryDate(result.get(0).get("ENTRY_DATE")==null?"":result.get(0).get("ENTRY_DATE").toString());
					bean.setEndDate(result.get(0).get("END_DATE")==null?"":result.get(0).get("END_DATE").toString());
					bean.setParam1(result.get(0).get("PARAM1")==null?"":result.get(0).get("PARAM1").toString());
					bean.setParam2(result.get(0).get("PARAM2")==null?"":result.get(0).get("PARAM2").toString());
					bean.setParam3(result.get(0).get("PARAM3")==null?"":result.get(0).get("PARAM3").toString());
					bean.setParam4(result.get(0).get("PARAM4")==null?"":result.get(0).get("PARAM4").toString());
					bean.setParam5(result.get(0).get("PARAM5")==null?"":result.get(0).get("PARAM5").toString());
					bean.setParam6(result.get(0).get("PARAM6")==null?"":result.get(0).get("PARAM6").toString());
					bean.setParam7(result.get(0).get("PARAM7")==null?"":result.get(0).get("PARAM7").toString());
					bean.setParam8(result.get(0).get("PARAM8")==null?"":result.get(0).get("PARAM8").toString());
					bean.setParam9(result.get(0).get("PARAM9")==null?"":result.get(0).get("PARAM9").toString());
					bean.setParam10(result.get(0).get("PARAM10")==null?"":result.get(0).get("PARAM10").toString());
					bean.setConfigType(result.get(0).get("CONFIG_TYPE")==null?"":result.get(0).get("CONFIG_TYPE").toString());
					bean.setRemarks(result.get(0).get("REMARKS")==null?"":result.get(0).get("REMARKS").toString());
					bean.setRatingType(result.get(0).get("RATING_TYPE")==null?"":result.get(0).get("RATING_TYPE").toString());
					bean.setDivisionCode(result.get(0).get("DIVISION_CODE")==null?"":result.get(0).get("DIVISION_CODE").toString());
					bean.setCustomerCode(result.get(0).get("CUSTOMERCODE")==null?"":result.get(0).get("CUSTOMERCODE").toString());
					bean.setElectrical(result.get(0).get("ELECTRICAL")==null?"":result.get(0).get("ELECTRICAL").toString());
					bean.setNonElectrical(result.get(0).get("NONELECTRICAL")==null?"":result.get(0).get("NONELECTRICAL").toString());
					bean.setVehusageId(result.get(0).get("VEHUSAGEID")==null?"":result.get(0).get("VEHUSAGEID").toString());
					bean.setBodyId(result.get(0).get("BODYID")==null?"":result.get(0).get("BODYID").toString());
				}
			} catch (Exception e) {
				LogManager.info("Exception Occured @ getMotorRateList ");
				e.printStackTrace();
			}
			
		}

		public List<Map<String, Object>> getMotorFactorList(MotorAdminBeanNew bean) {List<Map<String, Object>> result = null;
		String factorId="";
		try {
			//factorId="1".equalsIgnoreCase(bean.getPolicyType())?"1":"2";
			//String query = "SELECT FACTOR_DETAIL_ID,FACTOR_ID,POLICYTYPE_ID,TO_CHAR(ENTRY_DATE,'DD/MM/YYYY') ENTRY_DATE,TO_CHAR(EFFECTIVE_DATE,'DD/MM/YYYY') EFFECTIVE_DATE,TO_CHAR(END_DATE,'DD/MM/YYYY') END_DATE,DECODE(STATUS,'Y','Active','N','DeActive' ) STATUS,REMARKS FROM MOTOR_FACTOR_RATE_DETAILS WHERE POLICYTYPE_ID=? AND STATUS ='Y' ORDER BY  FACTOR_ID";
			String query = "SELECT FACTOR_DETAIL_ID,FACTOR_ID,POLICYTYPE_ID,TO_CHAR(ENTRY_DATE,'DD/MM/YYYY') ENTRY_DATE,"
					+ "(SELECT TO_CHAR(MAX(TRUNC(EFFECTIVE_DATE)),'DD/MM/YYYY') FROM MOTOR_FACTOR_RATE_MASTER B WHERE A.FACTOR_ID = B.FACTOR_ID "
					+ "AND A.POLICYTYPE_ID = B.POLICYTYPE_ID AND B.AGENCY_CODE = ? AND B.DIVISION_CODE = ? AND STATUS IN ('Y','N')) EFFECTIVE_DATE,"
					+ "TO_CHAR(END_DATE,'DD/MM/YYYY') END_DATE,DECODE(STATUS,'Y','Active','N','DeActive' ) STATUS,REMARKS FROM MOTOR_FACTOR_RATE_DETAILS A"
					+ " WHERE POLICYTYPE_ID=? AND STATUS ='Y' ORDER BY  FACTOR_ID";
			Object args[] =  new Object[] {("all".equalsIgnoreCase(bean.getBrokerId())?"99999":bean.getBrokerId()),
					("all".equalsIgnoreCase(bean.getBranchCode())?"99999":bean.getBranchCode()),
					bean.getPolicyType()};

			LogManager.info("Query => " + queryFrammer(query, args));
			result = this.mytemplate.queryForList(query, args);
		} catch (Exception e) {
			LogManager.info("Exception Occured @ getMotorFactorList ");
			e.printStackTrace();
		}
		return result;}

		public void gettransactionDtls(MotorAdminBeanNew bean) {
			try{
			  //query=getQuery("GET_RATE_TRANSACTION_DETAILS_RA");
			  query="select * from RATE_TRANSACTION_DETAILS where TRAN_ID=?";
			  Object[] args = new Object[]{bean.getTranID()};
			  LogManager.info("Query => " + queryFrammer(query, args));
			  Map<String, Object> res = this.mytemplate.queryForMap(query, args);
			  bean.setTranID(res.get("TRAN_ID") == null ? "": res.get("TRAN_ID").toString());
			  bean.setNoofRecords(res.get("NO_OF_RECORDS") == null ? "": res.get("NO_OF_RECORDS").toString());
			  bean.setValidRecords(res.get("VALID_RECORDS") == null ? "": res.get("VALID_RECORDS").toString());
			  bean.setErrorRecords(res.get("ERROR_RECORDS") == null ? "": res.get("ERROR_RECORDS").toString());
			  bean.setProgressStatus(res.get("STATUS") == null ? "": res.get("STATUS").toString());
			  bean.setProgressMessage(res.get("TRANSACTION_DESC") == null ? "": res.get("TRANSACTION_DESC").toString());
			}catch (Exception e) {
				e.printStackTrace();
			}
		}

		public List<Map<String, Object>> getfactortrandetails() {
			List<Map<String,Object>> result=null;
			try{
				//String query=getQuery("GET_FACTOR_RATE_TRAN_DETAILS_RA");
				String query="SELECT TRAN_ID,FACTOR_ID,TO_CHAR(TRANSACTION_DATE,'dd/mm/yyyy') TRANSACTION_DATE,NO_OF_RECORDS,VALID_RECORDS,ERROR_RECORDS FROM RATE_TRANSACTION_DETAILS ORDER BY TRAN_ID DESC";
				LogManager.info("Query => "+query);
				result = this.mytemplate.queryForList(query);
			}catch(Exception e){
				LogManager.info("Exception Occured @ getfactortrandetails ");
				e.printStackTrace();
			}
			return result;
		}

		public List<Map<String, Object>> rateDetailsList(MotorAdminBeanNew bean) {
			LogManager.info("Enter into rateDetailsList()");
			List<Map<String,Object>> res=new ArrayList<Map<String,Object>>();
			try{
				String type="DOWNLOAD";
				String brokerName=bean.getBrokerId()==null?"":bean.getBrokerId().toString();
				query = "SELECT TO_CHAR (SYSDATE+1,'DD/MM/YYYY') TODAYDATE FROM DUAL";
				String todayDate=(String) this.mytemplate.queryForObject(query, String.class);
				String rateEndDate=bean.getRateEndDate()==null||"".equalsIgnoreCase(bean.getRateEndDate())?todayDate:bean.getRateEndDate().toString();
				bean.setRateEndDate(rateEndDate);
				query="SELECT  BROKERNAME AGENCY_CODE,"
//						+ " CUSTOMERCODE,"
						+ " DIVISIONCODE, BRANCH_NAME, CUSTOMER_TYPE, VEHUSAGEID, VEHUSAGE_DESC,"
						+ " BODYID, BODYTYPE_DESC,   PARAM1 DESC_PARAM1, PARAM2 DESC_PARAM2, PARAM3 DESC_PARAM3,"
						+ " PARAM4 DESC_PARAM4, PARAM5 DESC_PARAM5, PARAM6 DESC_PARAM6,"
						+ " PARAM7 DESC_PARAM7,PARAM8 DESC_PARAM8, PARAM9 DESC_PARAM9, PARAM10 DESC_PARAM10,STATUS,EFFECTIVE_DATE,"
						+ " END_DATE, RATE, RATING_TYPE,"
//						+ " PARAM1CODE CODE_PARAM1, PARAM2CODE CODE_PARAM2, PARAM3CODE CODE_PARAM3,"
//						+ " PARAM4CODE CODE_PARAM4,PARAM5CODE CODE_PARAM5, PARAM6CODE CODE_PARAM6,"
//						+ " PARAM7CODE CODE_PARAM7, PARAM8CODE CODE_PARAM8, PARAM9CODE CODE_PARAM9, PARAM10CODE CODE_PARAM10,"
//						+ " CONFIG_TYPE, AMENDS AMEND_ID, FACTOR_RATE_ID,"
						+ " REPLACE(REMARKS,'\\n','') REMARKS"
						+ " FROM TABLE(MASTER_PKG.DYNAMIC_MASTER_RATE(?,?,?,?,?,?,?,?,?,?,?,?,?))";
				//String query="SELECT * FROM TABLE(MASTER_PKG.DYNAMIC_MASTER_RATE(?,?,?,?,?,?,?,?,?,?,?,?,?))";//"SELECT * FROM TABLE(MASTER_PKG.DYNAMIC_MASTER_RATE('1','1','1','01','','','','','','','90031','26/03/2021','VIEW') )";
				Object args[] =  new Object[] {bean.getPolicyType(),bean.getFactorId(),bean.getVehicleType(),bean.getBranchCode(),"","","","","","", bean.getBrokerId(), rateEndDate,type };

				//query=getQuery("GET_FACTOR_RATE_DOWNLOAD_QUERY");
				//query=query+"BROKERNAME AGENCY_CODE FROM TABLE(MASTER_PKG.DYNAMIC_MASTER_RATE(?,?,?,?,?,?,?,?,?,?,?,?))";
				LogManager.info("Query => " + queryFrammer(query, args));
				res=this.mytemplate.queryForList(query,new Object[] {bean.getPolicyType(),bean.getFactorId(),bean.getVehicleType(),bean.getBranchCode(),"","","","","","", bean.getBrokerId(), rateEndDate,type });
			}catch(Exception e){
				e.printStackTrace();
				LogManager.info("Exception Occured @ rateDetailsList()");
			}
			LogManager.info("Exit from the rateDetailsList()");
			return res;
		}

		public List<Map<String, Object>> getFactorDetailList(MotorAdminBeanNew bean) {
			LogManager.info("Enter into the getFactorDetailList()");
			List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
			Object[] args=null;
			try{
				//query =getQuery("GET_FACTOR_DETAILS_LIST_RA");
				query="SELECT FACTOR_ID, FACTOR_NAME, STATUS, EFFECTIVE_DATE, POLICYTYPE_ID FROM( SELECT DISTINCT MFM.FACTOR_ID FACTOR_ID, FACTOR_NAME, CASE WHEN MFM.STATUS = 'Y' THEN 'Active' ELSE 'Deactive' END STATUS, (CASE WHEN ?=3 AND MFM.FACTOR_ID IN (1,2) AND PARAM12 IS NOT NULL THEN TO_CHAR(MFRM.EFFECTIVE_DATE, 'DD/MM/YYYY') WHEN (?=2 OR ?=1) AND MFM.FACTOR_ID IN (1,2) AND PARAM12 IS NULL THEN TO_CHAR(MFRM.EFFECTIVE_DATE, 'DD/MM/YYYY') WHEN ?=3 AND MFM.FACTOR_ID IN (16,17,29) AND PARAM7 IS NOT NULL THEN TO_CHAR(MFRM.EFFECTIVE_DATE, 'DD/MM/YYYY') WHEN (?=2 OR ?=1) AND MFM.FACTOR_ID IN (16,17,29) AND PARAM7 IS NULL THEN TO_CHAR(MFRM.EFFECTIVE_DATE, 'DD/MM/YYYY') WHEN MFM.FACTOR_ID NOT IN (1,2,16,17,29) THEN TO_CHAR(MFRM.EFFECTIVE_DATE, 'DD/MM/YYYY') END) EFFECTIVE_DATE, POLICYTYPE_ID FROM MOTOR_FACTOR_MASTER MFM, MOTOR_FACTOR_RATE_MASTER MFRM WHERE POLICYTYPE_ID = ? AND MFM.FACTOR_ID = MFRM.FACTOR_ID AND MFM.STATUS = 'Y' AND MFRM.STATUS!= 'N' AND AMEND_ID = (SELECT MAX(AMEND_ID) FROM MOTOR_FACTOR_RATE_MASTER WHERE POLICYTYPE_ID = ? AND MFRM.FACTOR_ID=FACTOR_ID AND STATUS !='N' AND STATUS !='N' AND TO_CHAR(EFFECTIVE_DATE, 'DD/MM/YYYY') = CASE WHEN ?=3 AND FACTOR_ID IN (1,2) AND PARAM12 IS NOT NULL THEN TO_CHAR(EFFECTIVE_DATE, 'DD/MM/YYYY') WHEN (?=2 OR ?=1) AND FACTOR_ID IN (1,2) AND PARAM12 IS NULL THEN TO_CHAR(EFFECTIVE_DATE, 'DD/MM/YYYY') WHEN ?=3 AND FACTOR_ID IN (16,17,29) AND PARAM7 IS NOT NULL THEN TO_CHAR(EFFECTIVE_DATE, 'DD/MM/YYYY') WHEN (?=2 OR ?=1) AND FACTOR_ID IN (16,17,29) AND PARAM7 IS NULL THEN TO_CHAR(EFFECTIVE_DATE, 'DD/MM/YYYY') WHEN FACTOR_ID NOT IN (1,2,16,17,29) THEN TO_CHAR(EFFECTIVE_DATE, 'DD/MM/YYYY') END) ) WHERE EFFECTIVE_DATE IS NOT NULL ORDER BY FACTOR_ID";
				args = new Object[] {bean.getVehicleType(),bean.getVehicleType(),bean.getVehicleType(),bean.getVehicleType(),bean.getVehicleType(),bean.getVehicleType(),bean.getPolicyType(),bean.getPolicyType(),bean.getVehicleType(),bean.getVehicleType(),bean.getVehicleType(),bean.getVehicleType(),bean.getVehicleType(),bean.getVehicleType()};
				
				LogManager.info("getFactorDetailList Query==>"+queryFrammer(query, args) );
				list=this.mytemplate.queryForList(query,args);
			}catch(Exception e){
				e.printStackTrace();
				LogManager.info("Exception Occured @ getFactorDetailList()");
			}
			LogManager.info("Exit from getFactorDetailList()");
			return list;
		}

		public void setLabelValues(MotorAdminBeanNew bean) {
			LogManager.info("Enter into the setLabelValues()");
			List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
			Object[] args=null;
			try{
				query="SELECT POLICYTYPE_DESC_ENGLISH, POLICY_SUBTYPE_ID FROM MOTOR_POLICYTYPE_MASTER MPM WHERE STATUS = 'Y' AND AMEND_ID =(SELECT MAX (AMEND_ID) FROM MOTOR_POLICYTYPE_MASTER WHERE POLICY_SUBTYPE_ID = MPM.POLICY_SUBTYPE_ID) AND BRANCH_CODE = ? AND POLICY_SUBTYPE_ID=? ORDER BY POLICYTYPE_DESC_ENGLISH";
				args = new Object[] {branchCode,bean.getPolicyType()};
				LogManager.info("setLabelValues Query==>"+queryFrammer(query, args));
				list=this.mytemplate.queryForList(query,args);
				if(list!=null && list.size()>0)
					bean.setPolicyTypeDescEng(list.get(0).get("POLICYTYPE_DESC_ENGLISH")==null?"":list.get(0).get("POLICYTYPE_DESC_ENGLISH").toString());
				
				if(!"ALL".equalsIgnoreCase(bean.getVehicleType())) {
					query="SELECT VTYPE_ID CODE,UPPER(VEHICLETYPE_DESC) CODEDESC FROM MOTOR_VEHICLETYPE_MASTER MVM WHERE STATUS='Y' and BRANCH_CODE= ? AND AMEND_ID =( SELECT MAX(AMEND_ID) FROM MOTOR_VEHICLETYPE_MASTER MV WHERE MVM.VTYPE_ID=MV.VTYPE_ID) AND VTYPE_ID=? ORDER BY VEHICLETYPE_DESC";
					args = new Object[] {branchCode,bean.getVehicleType()};
					LogManager.info("setLabelValues Query==>"+queryFrammer(query, args) );
					list=this.mytemplate.queryForList(query,args);
					if(list!=null && list.size()>0)
						bean.setVehUsageDesc(list.get(0).get("CODEDESC")==null?"":list.get(0).get("CODEDESC").toString());
				}else {
					bean.setVehUsageDesc("ALL");
				}
				
				if(!"ALL".equalsIgnoreCase(bean.getBrokerId())) {
					query="SELECT DISTINCT LM.LOGIN_ID LOGIN_ID,LM.AGENCY_CODE,BCM.COMPANY_NAME USERNAME FROM LOGIN_MASTER LM, BROKER_COMPANY_MASTER BCM WHERE LM.AGENCY_CODE = BCM.AGENCY_CODE AND LM.AGENCY_CODE=?";
					args = new Object[] {bean.getBrokerId()};
					LogManager.info("setLabelValues Query==>"+queryFrammer(query, args));
					list=this.mytemplate.queryForList(query,args);
					if(list!=null && list.size()>0)
						bean.setBrokerNameDesc(list.get(0).get("USERNAME")==null?"":list.get(0).get("USERNAME").toString());
				}else {
					bean.setBrokerNameDesc("ALL");
				}
				
				if(!"ALL".equalsIgnoreCase(bean.getBranchCode())) {
					query="SELECT BRANCH_CODE , BRANCH_NAME FROM BRANCH_MASTER WHERE STATUS='Y' AND BRANCH_CODE=?";
					args = new Object[] {bean.getBranchCode()};
					LogManager.info("setLabelValues Query==>"+queryFrammer(query, args));
					list=this.mytemplate.queryForList(query,args);
					if(list!=null && list.size()>0)
						bean.setBranchName(list.get(0).get("BRANCH_NAME")==null?"":list.get(0).get("BRANCH_NAME").toString());
				}else {
					bean.setBranchName("ALL");
				}
				
				query="SELECT FACTOR_DETAIL_ID,FACTOR_ID,POLICYTYPE_ID,REMARKS FROM MOTOR_FACTOR_RATE_DETAILS WHERE POLICYTYPE_ID=? AND STATUS ='Y' AND FACTOR_ID=? ORDER BY  FACTOR_ID ";
				args = new Object[] {bean.getPolicyType(),bean.getFactorId()};
				LogManager.info("setLabelValues Query==>"+queryFrammer(query, args));
				list=this.mytemplate.queryForList(query,args);
				if(list!=null && list.size()>0)
					bean.setFactorName(list.get(0).get("REMARKS")==null?"":list.get(0).get("REMARKS").toString());
			}catch(Exception e){
				e.printStackTrace();
				LogManager.info("Exception Occured @ setLabelValues()");
			}
		}

		public List<Map<String, Object>> getBrokerBranchList() {
			List<Map<String, Object>> res = new ArrayList<Map<String, Object>>();
			try {
				String query="SELECT BBM.SNO, BBM.BROKER_ID, BBM.BRANCH_ID, BBM.BRANCH_CODE,BBM.BRANCH_NAME BROKER_BRANCH_NAME, BBM.MGEN_BRANCH_ID,(SELECT BRANCH_NAME FROM BRANCH_MASTER WHERE BRANCH_CODE =BBM.MGEN_BRANCH_ID) MGEN_BRANCH_NAME, DECODE(BBM.STATUS, 'Y', 'Active', 'N', 'DeActive') STATUS, BBM.REMARKS,(SELECT LOGIN_ID FROM LOGIN_MASTER WHERE AGENCY_CODE =BBM.BROKER_ID) LOGIN_ID,(SELECT COMPANY_NAME FROM BROKER_COMPANY_MASTER WHERE AGENCY_CODE=BBM.BROKER_ID) BROKER_NAME FROM BROKER_BRANCH_MASTER BBM ORDER BY SNO ";
				LogManager.info("getBrokerBranchList Query==>"+query);
				res=this.mytemplate.queryForList(query);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return res;
		}

		public void getEditBrokerbranch(MotorAdminBeanNew bean) {
			List<Map<String, Object>> res = new ArrayList<Map<String, Object>>();
			try {
				String query="SELECT BBM.SNO, BBM.BROKER_ID, BBM.BRANCH_ID,BBM.BRANCH_CODE, BBM.BRANCH_NAME, BBM.MGEN_BRANCH_ID,BBM.STATUS, BBM.REMARKS FROM BROKER_BRANCH_MASTER BBM WHERE BRANCH_ID=?";
				Object[] args=new Object[]{bean.getBrokerBranchId()};
				LogManager.info("getEditBrokerbranch Query==>"+queryFrammer(query, args));
				res=this.mytemplate.queryForList(query,args);
				if(res!=null && res.size()>0){
					bean.setBrokerId(res.get(0).get("BROKER_ID")==null?"":res.get(0).get("BROKER_ID").toString());
					bean.setBranchCode(res.get(0).get("MGEN_BRANCH_ID")==null?"":res.get(0).get("MGEN_BRANCH_ID").toString());
					bean.setBrokerBranchCode(res.get(0).get("BRANCH_CODE")==null?"":res.get(0).get("BRANCH_CODE").toString());
					bean.setBrokerBranchName(res.get(0).get("BRANCH_NAME")==null?"":res.get(0).get("BRANCH_NAME").toString());
					bean.setStatus(res.get(0).get("STATUS")==null?"":res.get(0).get("STATUS").toString());
					bean.setRemarks(res.get(0).get("REMARKS")==null?"":res.get(0).get("REMARKS").toString());
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}

		public int updatebrokerBranch(MotorAdminBeanNew bean) {
			int res=0;
			Object[] args=null;
			try {
				if("add".equalsIgnoreCase(bean.getMode())){
					query="INSERT INTO BROKER_BRANCH_MASTER (SNO, BROKER_ID, BRANCH_ID,BRANCH_CODE, BRANCH_NAME, MGEN_BRANCH_ID, STATUS, REMARKS) VALUES ((SELECT NVL(MAX(SNO)+1,1) FROM BROKER_BRANCH_MASTER),?,(SELECT NVL(MAX(BRANCH_ID)+1,1) FROM BROKER_BRANCH_MASTER),?,?,?,?,?)";
					args=new Object[]{bean.getBrokerId(),bean.getBrokerBranchCode(),bean.getBrokerBranchName(),bean.getBranchCode(),bean.getStatus(),bean.getRemarks()};
				}
				else if("edit".equalsIgnoreCase(bean.getMode())){
					query="UPDATE BROKER_BRANCH_MASTER SET BROKER_ID=?,BRANCH_CODE=?,BRANCH_NAME=?,MGEN_BRANCH_ID=?,STATUS=?,REMARKS=? WHERE BRANCH_ID=?";
					args=new Object[]{bean.getBrokerId(),bean.getBrokerBranchCode(),bean.getBrokerBranchName(),bean.getBranchCode(),bean.getStatus(),bean.getRemarks(),bean.getBrokerBranchId()};
				}
				LogManager.info("updatebrokerBranch Query==>"+queryFrammer(query, args));
				res=this.mytemplate.update(query,args);
			} catch (Exception e) {
				e.printStackTrace();
				String result = e.toString();
				if(result.contains("unique"))
					res=99999;
			}
			return res;
		}

		public void editRateDetails(MotorAdminBeanNew bean) {
			Object[] args=null;
			try {
				String query="SELECT MFRM.RATE FROM MOTOR_FACTOR_RATE_MASTER MFRM WHERE MFRM.FACTOR_RATE_ID=? AND MFRM.AMEND_ID=(SELECT NVL(MAX(AMEND_ID),0) FROM MOTOR_FACTOR_RATE_MASTER WHERE FACTOR_RATE_ID=MFRM.FACTOR_RATE_ID)";
				args=new Object[]{bean.getFactorRateId()};
				LogManager.info("editRateDetails Query==>"+queryFrammer(query, args));
				List<Map<String,Object>> res=this.mytemplate.queryForList(query,args);
				bean.setMotorEditRateDtl(res);
				if(res!=null && res.size()>0){
					bean.setRate(res.get(0).get("RATE")==null?"":res.get(0).get("RATE").toString());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}

		public int updateRateModified(MotorAdminBeanNew bean) {
			int res=0;
			Object[] args=null;
			try {
				String query="UPDATE MOTOR_FACTOR_RATE_MASTER MFRM SET MFRM.RATE=? WHERE MFRM.FACTOR_RATE_ID=?  AND MFRM.AMEND_ID=(SELECT NVL(MAX(AMEND_ID),0) FROM MOTOR_FACTOR_RATE_MASTER WHERE FACTOR_RATE_ID=MFRM.FACTOR_RATE_ID)";
				args=new Object[]{bean.getRate(),bean.getFactorRateId()};
				LogManager.info("updateRateModified Query==>"+queryFrammer(query, args));
				res=this.mytemplate.update(query,args);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return res;
		}
		
		public List<Map<String, Object>> getExecutiveList() {
			List<Map<String, Object>> res=new ArrayList<Map<String, Object>>();
			try {
				query="SELECT AC_EXECUTIVE_ID, AC_EXECUTIVE_NAME, LOGIN_ID, AGENCY_CODE, OA_CODE, COMPANY_ID, RELATIVE_AC_EXECUTIVE_ID, AMEND_ID, INCEPTION_DATE, EXPIRY_DATE, EFFECTIVE_DATE, TO_CHAR(ENTRY_DATE,'DD/MM/YYYY') ENTRY_DATE, REMARKS, DECODE(STATUS,'Y','ACTIVE','N','DEACTIVE') STATUS, PRODUCT_ID, COMMISSION, INSURANCE_START_LIMIT, INSURANCE_END_LIMIT, SPECIAL_DISCOUNT, CUSTOMER_ID, RSACODE, OPENCOVER_COMMISSION FROM LOGIN_EXECUTIVE_DETAILS ORDER BY AC_EXECUTIVE_ID";
				LogManager.info("getExecutiveList Query==>"+query);
				res=this.mytemplate.queryForList(query);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return res;
		}

		public void getEditExecutive(MotorAdminBeanNew bean) {
			List<Map<String, Object>> res=new ArrayList<Map<String, Object>>();
			Object[] args=null;
			try {
				query="SELECT AC_EXECUTIVE_ID, AC_EXECUTIVE_NAME,STATUS,REMARKS,RSACODE,COMMISSION FROM LOGIN_EXECUTIVE_DETAILS WHERE AC_EXECUTIVE_ID=?";
				args=new Object[]{bean.getExecutiveID()};
				LogManager.info("getEditExecutive Query==>"+queryFrammer(query, args));
				res=this.mytemplate.queryForList(query,args);
				if(res!=null && res.size()>0){
					bean.setExecutiveName(res.get(0).get("AC_EXECUTIVE_NAME")==null?"":res.get(0).get("AC_EXECUTIVE_NAME").toString());
					bean.setExecutiveCommission(res.get(0).get("COMMISSION")==null?"":res.get(0).get("COMMISSION").toString());
					bean.setExecutiveCoreCode(res.get(0).get("RSACODE")==null?"":res.get(0).get("RSACODE").toString());
					bean.setStatus(res.get(0).get("STATUS")==null?"":res.get(0).get("STATUS").toString());
					bean.setRemarks(res.get(0).get("REMARKS")==null?"":res.get(0).get("REMARKS").toString());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		public int updateExecutive(MotorAdminBeanNew bean) {
			int res=0;
			Object[] args=null;
			try {
				if("add".equalsIgnoreCase(bean.getMode())){
					query="INSERT INTO LOGIN_EXECUTIVE_DETAILS (AC_EXECUTIVE_ID, AC_EXECUTIVE_NAME,COMMISSION,RSACODE,ENTRY_DATE,STATUS,REMARKS,COMPANY_ID,AMEND_ID,PRODUCT_ID) VALUES ((SELECT NVL(MAX(AC_EXECUTIVE_ID)+1,1) FROM LOGIN_EXECUTIVE_DETAILS),?,?,?,SYSDATE,?,?,'213','0',?)";
					args=new Object[]{bean.getExecutiveName(),bean.getExecutiveCommission(),bean.getExecutiveCoreCode(),bean.getStatus(),bean.getRemarks(),bean.getProductId()};
				}
				else if("edit".equalsIgnoreCase(bean.getMode())){
					query="UPDATE LOGIN_EXECUTIVE_DETAILS SET AC_EXECUTIVE_NAME=?,COMMISSION=?,RSACODE=?,STATUS=?,REMARKS=? WHERE AC_EXECUTIVE_ID=?";
					args=new Object[]{bean.getExecutiveName(),bean.getExecutiveCommission(),bean.getExecutiveCoreCode(),bean.getStatus(),bean.getRemarks(),bean.getExecutiveID()};
				}
				LogManager.info("updateExecutive Query==>"+queryFrammer(query, args));
				res=this.mytemplate.update(query,args);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return res;
		}

		public int branchExist(MotorAdminBeanNew bean) {
			int res=0;
			Object[] args=null;
			try {
				query="SELECT COUNT(*) FROM BROKER_BRANCH_MASTER WHERE BROKER_ID=? AND MGEN_BRANCH_ID=? AND (UPPER(BRANCH_NAME)=UPPER(?) OR BRANCH_CODE=?)";
				args=new Object[]{bean.getBrokerId(),bean.getBranchCode(),bean.getBrokerBranchName(),bean.getBrokerBranchCode()};
				LogManager.info("branchExist Query==>"+queryFrammer(query, args));
				res=this.mytemplate.queryForInt(query,args);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return res;
		}

		public List<Map<String, Object>> conditionsList() {
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			try {
				String query = "SELECT SNO, POLICY_TYPE_ID, (SELECT POLICYTYPE_DESC_ENGLISH FROM MOTOR_POLICYTYPE_MASTER B"
						+ " WHERE POLICY_SUBTYPE_ID = A.POLICY_TYPE_ID AND AMEND_ID = (SELECT MAX(AMEND_ID) FROM MOTOR_POLICYTYPE_MASTER C"
						+ " WHERE B.POLICY_SUBTYPE_ID = C.POLICY_SUBTYPE_ID)) POLICY_TYPE,  CONDITION_ID, CONDITION_DESC, COREAPPCODE, PRODUCT_ID,"
						+ " (CASE STATUS WHEN 'Y' THEN 'Active' else 'De-Active' END) STATUS, (CASE CONDITION_TYPE WHEN 'CONDITION' THEN 'Condition' else 'Deductible' END) CONDITION_TYPE FROM MOTOR_CONDITION_MASTER A"
						+ " WHERE CONDITION_TYPE IN ('CONDITION','FIRSTAMOUNTPAYABLE') AND PRODUCT_ID = '65'";
				LogManager.info("MotorAdmiDaoNew.conditionsList() Query: "+query);
				list = this.mytemplate.queryForList(query);
			}catch(Exception e) {
				LogManager.error("Exception @ MotorAdmiDaoNew.conditionsList() "+e);
				e.printStackTrace();
			}
			return list;
		}

		public List<Map<String, Object>> specificCondition(MotorAdminBeanNew bean) {
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			try {
				String query = "SELECT SNO, POLICY_TYPE_ID,  CONDITION_ID, CONDITION_DESC, COREAPPCODE, PRODUCT_ID, STATUS, CONDITION_TYPE FROM MOTOR_CONDITION_MASTER"
						+ " WHERE CONDITION_TYPE IN ('CONDITION','FIRSTAMOUNTPAYABLE') AND PRODUCT_ID = '65' AND SNO = ?";
				Object[] args = new Object[] {bean.getSno()};
				LogManager.info("MotorAdmiDaoNew.specificCondition() Query: "+queryFrammer(query, args));
				list = this.mytemplate.queryForList(query, args);
			}catch(Exception e) {
				LogManager.error("Exception @ MotorAdminDaoNew.specificCondition() "+e);
				e.printStackTrace();
			}
			return list;
		}

		public boolean insUpdCondition(MotorAdminBeanNew bean) {
			try {
				String query = "";
				Object[] args = null;
				if("add".equalsIgnoreCase(bean.getMode())) {
					query = "INSERT INTO MOTOR_CONDITION_MASTER (SNO, POLICY_TYPE_ID, CONDITION_ID, CONDITION_DESC,"
							+ " COREAPPCODE, CONDITION_TYPE, PRODUCT_ID, STATUS) VALUES ((SELECT MAX(SNO)+1 FROM MOTOR_CONDITION_MASTER), ?,"
							+ "(SELECT MAX(CONDITION_ID) FROM MOTOR_CONDITION_MASTER WHERE CONDITION_TYPE IN ('CONDITION','FIRSTAMOUNTPAYABLE') AND PRODUCT_ID = '65')"
							+ ",?,?,?,'65',?)";
					args = new Object[] {bean.getPolicyType(), bean.getConditionDesc(), bean.getCoreappCode(), bean.getConditionType(), bean.getStatus()};
				}else if("edit".equalsIgnoreCase(bean.getMode())) {
					query = "UPDATE MOTOR_CONDITION_MASTER SET POLICY_TYPE_ID = ?, CONDITION_DESC = ?, COREAPPCODE = ?, STATUS = ?, CONDITION_TYPE = ?"
							+ " WHERE CONDITION_TYPE IN ('CONDITION','FIRSTAMOUNTPAYABLE') AND PRODUCT_ID = '65' AND SNO = ?";
					args = new Object[] {bean.getPolicyType(), bean.getConditionDesc(), bean.getCoreappCode(), bean.getStatus(), bean.getConditionType(), bean.getSno()};
				}
				LogManager.info("MotorAdminServiceNew.insUpdCondition() Query: "+queryFrammer(query, args));
				int count = this.mytemplate.update(query, args);
				if(count>0) {
					return true;
				}
			}catch(Exception e) {
				LogManager.error("Exception @ MotorAdminServiceNew.insUpdCondition() "+e);
				e.printStackTrace();
			}
			return false;
		}

		public List<Map<String, Object>> getFactorDetails(MotorAdminBeanNew bean) {
			LogManager.info("Enter into getFactorDetails()");
			List<Map<String,Object>> res=new ArrayList<Map<String,Object>>();
			try {
				Object[] args = new Object[]{bean.getFactorId()};
				query="SELECT FACTOR_DESC,PARAM1,PARAM2,PARAM3,PARAM4,PARAM5,PARAM6,PARAM7,PARAM8,PARAM9,PARAM10,"
						//+ "PARAM11,PARAM12,PARAM13,PARAM14,PARAM15,CONFIG_TYPE,"
						+ "RATING_TYPE, DIVISION_CODE, VEHUSAGE, BODYTYPE, CUSTOMER_TYPE, AGENCY_CODE FROM MOTOR_FACTOR_REFERENCE WHERE POLICYTYPE_ID LIKE '%"+bean.getPolicyType()+"%' AND FACTOR_ID=?";
				LogManager.info("getFactorDetails Query==>"+queryFrammer(query, args));
				res=this.mytemplate.queryForList(query,args);
			} catch (DataAccessException e) {
				e.printStackTrace();
			}
			LogManager.info("Exit from the getFactorDetails()");
			return res;
		}

		public List<Map<String, Object>> getExchangeList() {
			List<Map<String, Object>> res = new ArrayList<Map<String, Object>>();
			try {
				String query="SELECT EXCHANGE_ID, EXCHANGE_RATE, CURRENCY_ID,(SELECT CURRENCY_NAME FROM CURRENCY_MASTER CM WHERE "
						+ "CM.CURRENCY_ID=EMM.CURRENCY_ID) CURRENCY_NAME,AMEND_ID, TO_CHAR(INCEPTION_DATE,'DD/MM/YYYY') INCEPTION_DATE, "
						+ "TO_CHAR(EXPIRY_DATE,'DD/MM/YYYY') EXPIRY_DATE, TO_CHAR(EFFECTIVE_DATE,'DD/MM/YYYY') EFFECTIVE_DATE, "
						+ "TO_CHAR(ENTRY_DATE,'DD/MM/YYYY') ENTRY_DATE, REMARKS, DECODE(STATUS,'Y','Active','N','DeActive') STATUS_DESC, RSACODE, SNO__ SNO, COUNTRY_ID,"
						+ "(SELECT COUNTRY_NAME FROM COUNTRY_MASTER CMM WHERE CMM.COUNTRY_ID=EMM.COUNTRY_ID) COUNTRY_NAME, DISPLAY_ORDER "
						+ "FROM EXCHANGE_MASTER EMM WHERE AMEND_ID=(SELECT MAX(AMEND_ID) FROM EXCHANGE_MASTER EM WHERE "
						+ "EM.EXCHANGE_ID=EMM.EXCHANGE_ID) ORDER BY SNO__";
				LogManager.info("getExchangeList() Query==> "+query);
				res=this.mytemplate.queryForList(query);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return res;
		}

		public List<Map<String, Object>> getCurrencyList() {
			List<Map<String, Object>> res = new ArrayList<Map<String, Object>>();
			try {
				String query="SELECT CURRENCY_ID CODE,CURRENCY_NAME CODE_DESC FROM CURRENCY_MASTER CMM WHERE STATUS='Y' AND "
						+ "AMEND_ID=(SELECT MAX(AMEND_ID) FROM CURRENCY_MASTER WHERE CURRENCY_ID=CMM.CURRENCY_ID) ORDER BY CURRENCY_ID";
				LogManager.info("getCurrencyList() Query==> "+query);
				res=this.mytemplate.queryForList(query);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return res;
		}

		public List<Map<String, Object>> getExchangeCountryList() {
			List<Map<String, Object>> res = new ArrayList<Map<String, Object>>();
			try {
				String query="SELECT COUNTRY_ID CODE,COUNTRY_NAME CODE_DESC FROM COUNTRY_MASTER CMM WHERE STATUS='Y' AND "
						+ "AMEND_ID=(SELECT MAX(AMEND_ID) FROM COUNTRY_MASTER WHERE COUNTRY_ID=CMM.COUNTRY_ID) ORDER BY COUNTRY_ID";
				LogManager.info("getExchangeCountryList() Query==> "+query);
				res=this.mytemplate.queryForList(query);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return res;
		}

		public List<Map<String, Object>> editExchangeData(MotorAdminBeanNew bean) {
			List<Map<String, Object>> res = new ArrayList<Map<String, Object>>();
			try {
				String query="SELECT EXCHANGE_ID, EXCHANGE_RATE, CURRENCY_ID,(SELECT CURRENCY_NAME FROM CURRENCY_MASTER CM WHERE "
						+ "CM.CURRENCY_ID=EMM.CURRENCY_ID) CURRENCY_NAME,AMEND_ID, TO_CHAR(INCEPTION_DATE,'DD/MM/YYYY') INCEPTION_DATE, "
						+ "TO_CHAR(EXPIRY_DATE,'DD/MM/YYYY') EXPIRY_DATE, TO_CHAR(EFFECTIVE_DATE,'DD/MM/YYYY') EFFECTIVE_DATE, "
						+ "TO_CHAR(ENTRY_DATE,'DD/MM/YYYY') ENTRY_DATE, REMARKS, STATUS, RSACODE, SNO__ SNO, COUNTRY_ID,"
						+ "(SELECT COUNTRY_NAME FROM COUNTRY_MASTER CMM WHERE CMM.COUNTRY_ID=EMM.COUNTRY_ID) COUNTRY_NAME, DISPLAY_ORDER "
						+ "FROM EXCHANGE_MASTER EMM WHERE AMEND_ID=(SELECT MAX(AMEND_ID) FROM EXCHANGE_MASTER EM WHERE "
						+ "EM.EXCHANGE_ID=EMM.EXCHANGE_ID) AND EXCHANGE_ID=?";
				Object[] args = new Object[]{bean.getExchangeId()};
				LogManager.info("getExchangeList() Query==> "+queryFrammer(query, args));
				res=this.mytemplate.queryForList(query,args);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return res;
		}

		public int updateExchangeData(MotorAdminBeanNew bean) {
			int result=0;
			try {
				String query = "";
				String amendId = "";
				String curAmendId="";
				Object[] args = null;
				
				query = "SELECT AMEND_ID FROM EXCHANGE_MASTER EMM WHERE AMEND_ID=(SELECT MAX (AMEND_ID) FROM EXCHANGE_MASTER WHERE "
						+ "EXCHANGE_ID = EMM.EXCHANGE_ID) AND EXCHANGE_ID=?";
				args = new Object[] { bean.getExchangeId() };
				LogManager.info("updateExchangeData() Current AmendId Query => " + queryFrammer(query, args));
				List<Map<String, Object>> resultList = this.mytemplate.queryForList(query, args);
				if (resultList.size() > 0)
					curAmendId = resultList.get(0).get("AMEND_ID") == null ? "" : resultList.get(0).get("AMEND_ID").toString();
				
				if ("edit".equalsIgnoreCase(bean.getMode())) {
					query = "SELECT AMEND_ID FROM EXCHANGE_MASTER EMM WHERE AMEND_ID=(SELECT MAX (AMEND_ID) FROM EXCHANGE_MASTER WHERE "
							+ "EXCHANGE_ID = EMM.EXCHANGE_ID) AND EXCHANGE_ID=? AND EFFECTIVE_DATE > SYSDATE";
					args = new Object[] { bean.getExchangeId() };
					LogManager.info("updateExchangeData() AmendId Query => " + queryFrammer(query, args));
					List<Map<String, Object>> res = this.mytemplate.queryForList(query, args);
					if (res.size() > 0)
						amendId = res.get(0).get("AMEND_ID") == null ? "" : res
								.get(0).get("AMEND_ID").toString();
				}
				if ("add".equalsIgnoreCase(bean.getMode())
						|| ("edit".equalsIgnoreCase(bean.getMode()) && StringUtils.isBlank(amendId))) {
					query = " INSERT INTO EXCHANGE_MASTER(EXCHANGE_ID,EXCHANGE_RATE,CURRENCY_ID,AMEND_ID,INCEPTION_DATE,EXPIRY_DATE,EFFECTIVE_DATE,"
							+ "ENTRY_DATE,REMARKS, STATUS,RSACODE,SNO__,COUNTRY_ID,CURRENCY_TYPE) VALUES(?,?,?,( SELECT NVL(MAX(AMEND_ID)+1,0) FROM EXCHANGE_MASTER "
							+ "WHERE EXCHANGE_ID=?),TO_TIMESTAMP(?,'DD/MM/YYYY HH:MI:SS'),TO_DATE(?,'DD/MM/YYYY'),TO_DATE(?,'DD/MM/YYYY'),"
							+ "SYSDATE,?,?,?,(SELECT NVL(MAX(SNO__)+1,1) FROM EXCHANGE_MASTER),?,(SELECT SHORT_NAME FROM CURRENCY_MASTER CM "
							+ "WHERE CM.CURRENCY_ID=? AND AMEND_ID=(SELECT MAX(AMEND_ID) FROM CURRENCY_MASTER WHERE CM.CURRENCY_ID=CURRENCY_ID))) ";
					args = new Object[12];
					if ("add".equalsIgnoreCase(bean.getMode())) {
						String sql = "SELECT NVL(MAX(EXCHANGE_ID)+1,1) FROM EXCHANGE_MASTER";
						int exchangeId = this.mytemplate.queryForInt(sql);
						args[0] = exchangeId;
						args[3] = exchangeId;
						args[11]= exchangeId;
					} else {
						args[0] = bean.getExchangeId();
						args[3] = bean.getExchangeId();
						args[11]= bean.getExchangeId();
					}
					args[1] = bean.getExchangeRate();
					args[2] = bean.getCurrencyName();
					
					args[4] = bean.getStartDate();
					args[5] = bean.getEndDate();
					args[6] = bean.getEffectiveDate();
					args[7] = bean.getRemarks();
					args[8] = bean.getStatus();
					args[9] = bean.getCoreAppCode();
					args[10] = bean.getCountryName();
					
				} else if ("edit".equalsIgnoreCase(bean.getMode()) && StringUtils.isNotBlank(amendId)) {
					query = "UPDATE EXCHANGE_MASTER SET EXCHANGE_RATE=?,CURRENCY_ID=?,COUNTRY_ID=?,INCEPTION_DATE=TO_TIMESTAMP(?,'DD/MM/YYYY HH:MI:SS'),EXPIRY_DATE=TO_DATE(?,'DD/MM/YYYY'),EFFECTIVE_DATE=TO_DATE(?,'DD/MM/YYYY'),"
							+ "RSACODE=?,STATUS=?,REMARKS=? WHERE EXCHANGE_ID=? AND AMEND_ID=?";
					args = new Object[11];
					args[0] = bean.getExchangeRate();
					args[1] = bean.getCurrencyName();
					args[2] = bean.getCountryName();
					args[3] = bean.getStartDate();
					args[4] = bean.getEndDate();
					args[5] = bean.getEffectiveDate();
					args[6] = bean.getCoreAppCode();
					args[7] = bean.getStatus();
					args[8] = bean.getRemarks();
					args[9] = bean.getExchangeId();
					args[10] = amendId;
				}
				LogManager.info("updateExchangeData() Insert/Update Query => " + queryFrammer(query, args));
				result=this.mytemplate.update(query, args);
				if (result>0 && ("edit".equalsIgnoreCase(bean.getMode()) && StringUtils.isBlank(amendId))) {
					query="UPDATE EXCHANGE_MASTER SET EXPIRY_DATE=TO_DATE(?,'DD/MM/YYYY')-1 WHERE EXCHANGE_ID=? AND AMEND_ID=?";
					args = new Object[3];
					args[0] = bean.getEffectiveDate();
					args[1] = bean.getExchangeId();
					args[2] = curAmendId;
					LogManager.info("updateExpiry Date Query => " + queryFrammer(query, args));
					this.mytemplate.update(query, args);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return result;
		}

		public int checkExist(MotorAdminBeanNew bean) {
			int res=0;
			try {
				String query="SELECT COUNT(*) FROM EXCHANGE_MASTER EMM WHERE CURRENCY_ID=? AND AMEND_ID=(SELECT MAX(AMEND_ID) FROM EXCHANGE_MASTER WHERE "
						+ "CURRENCY_ID=EMM.CURRENCY_ID)";
				Object[] args = new Object[]{bean.getCurrencyName()};
				LogManager.info("checkExist() Query==> "+queryFrammer(query, args));
				res=this.mytemplate.queryForInt(query,args);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return res;
		}

}
