package com.maan.Health.DAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.core.RowMapper;

import com.maan.DBCon.DBConnection;
import com.maan.common.dao.CommonDAO;
import com.maan.common.LogManager;
import com.maan.common.MyJdbcTemplate;
import com.maan.common.exception.BaseException;
import com.maan.Health.controller.UploadBean;
import com.maan.Health.controller.HealthBean;

public class HealthDAO extends MyJdbcTemplate {
	public String getUpdateInsurerInfo(HealthBean bean) {
		LogManager.info("getUpdateInsurerInfo - Enter ");
		String result="";
		try
		{
			String sql="";
			int res=0;
			Object[] obj=null;
			boolean saveFlag=("getSave".equalsIgnoreCase(bean.getActionType())?true:false);
			if(StringUtils.isBlank(bean.getApplicationNo()))
			{  
				bean.setApplicationNo(new CommonDAO().getSequenceNo("Application",bean.getProductId(),bean.getBranchCode(),"",""));
				if(StringUtils.isBlank(bean.getQuoteNo())&&("getQuote".equalsIgnoreCase(bean.getActionType())||saveFlag))
					bean.setQuoteNo(new CommonDAO().getSequenceNo("Quote",bean.getProductId(),bean.getBranchCode(),"",""));
				sql=getQuery("INS_POL_DTL");
				LogManager.info("Query=>"+sql);
				obj=new Object[19];
				obj[0]=bean.getApplicationNo();
				obj[1]=StringUtils.isBlank(bean.getQuoteNo())?"":bean.getQuoteNo();
				obj[2]=bean.getApplicationNo();
				obj[3]=bean.getTitle();
				obj[4]=bean.getInsurerName();
				obj[5]=bean.getCusCivilId();
				obj[6]=StringUtils.isBlank(bean.getDob())?"":bean.getDob();
				obj[7]=new com.maan.common.dao.CommonDAO().getCalculatedAge(bean.getDob());
				obj[8]=bean.getGender();
				obj[9]=bean.getMaritalStatus();
				obj[10]=bean.getOccupation();
				obj[11]=bean.getRelation();
				obj[12]=bean.getNationality();
				obj[13]=bean.getSponsorId();
				obj[14]=bean.getSponsorName();
				obj[15]=bean.getSponsorCity();
				obj[16]=bean.getSponsorMobileNo();
				obj[17]="0";
				obj[18]="Y";
				LogManager.info("obj[] ==> "+StringUtils.join(obj, ","));
				res=this.mytemplate.update(sql,obj);
				LogManager.info("Result=>"+res);
				sql=getQuery("INS_POL_MSTR");
				obj=new Object[11];
				obj[0]=bean.getApplicationNo();
				obj[1]=StringUtils.isBlank(bean.getQuoteNo())?"":bean.getQuoteNo();
				obj[2]= bean.getLoginId();
				obj[3]= bean.getCustomerId();
				obj[4]= bean.getProductId();
				obj[5]=bean.getSchemeCover();
				obj[6]=bean.getCoverPeriod();
				obj[7]=StringUtils.isBlank(bean.getInceptionDt())?"":bean.getInceptionDt();
				obj[8]=StringUtils.isBlank(bean.getExpiryDt())?"":bean.getExpiryDt();
				obj[9]="0";
				obj[10]="Y";
				LogManager.info("Query=>"+sql);
				LogManager.info("obj[] ==> "+StringUtils.join(obj, ","));
				res=this.mytemplate.update(sql,obj);
				LogManager.info("Result=>"+res);
				obj=new Object[17];
				if(StringUtils.isNotBlank(bean.getReferralMsg())){
					sql=getQuery("INS_HOME_POS_MSTR_REF");
					obj[15]=bean.getReferralMsg();//Referal Msg
			    }
				else
				{
					sql=getQuery("INS_HOME_POS_MSTR");
					obj[15]="";//admin remarks
				}
				LogManager.info("Query=>"+sql);
				obj[0]=bean.getApplicationNo();
				obj[1]=StringUtils.isBlank(bean.getQuoteNo())?"":bean.getQuoteNo();
				obj[2]=bean.getCustomerId();
				obj[3]=bean.getLoginId();
				obj[4]=bean.getProductId();
				obj[5]=bean.getBranchCode();
				obj[6]=bean.getCoverPeriod();
				obj[7]="0";
				obj[8]=StringUtils.isBlank(bean.getExpiryDt())?"":bean.getExpiryDt();
				obj[9]=StringUtils.isBlank(bean.getInceptionDt())?"":bean.getInceptionDt();;
				if(saveFlag){
					obj[10]="S";
				}else
				{
					obj[10]="Y";
				}
				//if("Broker".equalsIgnoreCase(bean.getUserType())||"User".equalsIgnoreCase(bean.getUserType()))
				if(StringUtils.isBlank(bean.getIssuer()))
					obj[11]="1";//app id
				else
					obj[11]=bean.getIssuer();//app id	
				obj[12]="";//AGE_ABOVE_SIXTY_FIVE
				obj[13]="";//EXISTING_MEDICAL_CONDITION
				obj[14]=bean.getSchemeCover();
				obj[16]=bean.getProductId();
				
				LogManager.info("obj[] ==> "+StringUtils.join(obj, ","));
				res=this.mytemplate.update(sql,obj);
				LogManager.info("Result=>"+res);
				if(res>0){
					/*if("getQuote".equalsIgnoreCase(bean.getActionType()))
						getSecondPageDts(bean);*/
					result="SUCCESS";
				}
			}else
			{
				if(StringUtils.isBlank(bean.getQuoteNo())&&("getQuote".equalsIgnoreCase(bean.getActionType())||saveFlag))
					bean.setQuoteNo(new CommonDAO().getSequenceNo("Quote",bean.getProductId(),bean.getBranchCode(),"",""));
				sql=getQuery("UPD_POL_DTL");
				LogManager.info("Query=>"+sql);
				obj=new Object[16];
				obj[0]=StringUtils.isBlank(bean.getQuoteNo())?"":bean.getQuoteNo();
				obj[1]=bean.getTitle();
				obj[2]=bean.getInsurerName();
				obj[3]=bean.getCusCivilId();
				obj[4]=StringUtils.isBlank(bean.getDob())?"":bean.getDob();
				obj[5]=new com.maan.common.dao.CommonDAO().getCalculatedAge(bean.getDob());
				obj[6]=bean.getGender();
				obj[7]=bean.getMaritalStatus();
				obj[8]=bean.getOccupation();
				obj[9]=bean.getRelation();
				obj[10]=bean.getNationality();
				obj[11]=bean.getSponsorId();
				obj[12]=bean.getSponsorName();
				obj[13]=bean.getSponsorCity();
				obj[14]=bean.getSponsorMobileNo();
				obj[15]=bean.getApplicationNo();
				LogManager.info("obj[] ==> "+StringUtils.join(obj, ","));
				res=this.mytemplate.update(sql,obj);
				LogManager.info("Result=>"+res);
				sql=getQuery("UPD_POL_MSTR");
				LogManager.info("Query=>"+sql);
				obj=new Object[9];
				obj[0]=StringUtils.isBlank(bean.getQuoteNo())?"":bean.getQuoteNo();
				obj[1]= bean.getLoginId();
				obj[2]= bean.getCustomerId();
				obj[3]= bean.getProductId();
				obj[4]=bean.getSchemeCover();
				obj[5]=bean.getCoverPeriod();
				obj[6]=bean.getInceptionDt();
				obj[7]=bean.getExpiryDt();
				obj[8]=bean.getApplicationNo();
				LogManager.info("obj[] ==> "+StringUtils.join(obj, ","));
				res=this.mytemplate.update(sql,obj);
				LogManager.info("Result=>"+res);
				obj=new Object[12];
				if(StringUtils.isNotBlank(bean.getReferralMsg())){
					sql=getQuery("UPD_HOME_POS_MSTR_REF");
					obj[7]=bean.getReferralMsg();// memoranda;
					LogManager.info("Msg=>"+bean.getReferralMsg());
			    }
				else
				{
					sql=getQuery("UPD_HOME_POS_MSTR");
					obj[7]="";// memoranda;
				}
				if("admin".equals(bean.getUser())){
					sql=getQuery("UPD_HOME_POS_MSTR_ADMIN");
					obj[7]="";
				}
				LogManager.info("Query=>"+sql);
				obj[0]="";//AGE_ABOVE_SIXTY_FIVE
				obj[1]=bean.getInceptionDt();//bean.getCustomerId();
				obj[2]="";//treatment
				obj[3]=bean.getExpiryDt();
				obj[4]=bean.getSchemeCover();
				obj[5]=bean.getCoverPeriod();
				obj[6]=bean.getProductId();
				obj[8]=bean.getProductId();
				obj[9]=StringUtils.isBlank(bean.getQuoteNo())?"":bean.getQuoteNo();
				if(saveFlag){
					obj[10]="S";
				}else if(StringUtils.isBlank(bean.getAmendId())||"0".equals(bean.getAmendId()))
				{
					obj[10]="Y";
				}else if(!"0".equals(bean.getAmendId()))
				{
					obj[10]="E";
				}
				obj[11]=bean.getApplicationNo();
				LogManager.info("obj[] ==> "+StringUtils.join(obj, ","));
				res=this.mytemplate.update(sql,obj);
				LogManager.info("Result=>"+res);
				if(res>0){
					/*if("getQuote".equalsIgnoreCase(bean.getActionType()))
						getSecondPageDts(bean);*/
					result="SUCCESS";
				}
			}
		}catch(Exception e)
		{
			LogManager.debug(e);
		}
		LogManager.popRemove();
		LogManager.info("getUpdateInsurerInfo - Exit");
		return result;	
	}
	
	private void policyGeneration(HealthBean travelBean)throws BaseException {
		LogManager.info("policyGeneration method() Enter||");
		Object obj[] = new Object[]{travelBean.getQuoteNo()};
		String sql=getQuery("GET_POLICY_STATUS");
		LogManager.info("Query=>"+sql);
		LogManager.info("Obj[]=>"+StringUtils.join(obj,","));
		final Map fromPosition = this.mytemplate.queryForMap(sql,obj);
		LogManager.info("Map Size=>"+fromPosition.size());
		if ("Y".equalsIgnoreCase(fromPosition.get("STATUS").toString())) {
			/*//sql = getQuery("GET_POLICY_NO");
			obj = new Object[]{travelBean.getBranchCode(),travelBean.getProductId(),travelBean.getBranchCode(),travelBean.getProductId(),travelBean.getBranchCode(),travelBean.getProductId(),travelBean.getBranchCode(),travelBean.getProductId()};
			LogManager.info("Query=>"+sql);
			LogManager.info("Obj[]=>"+StringUtils.join(obj,","));
			Map brCodePrefix =  this.mytemplate.queryForMap(sql,obj);*/
			travelBean.setPolicyNo(new CommonDAO().getSequenceNo("Policy",travelBean.getProductId(),travelBean.getBranchCode(),travelBean.getQuoteNo(),""));
			travelBean.setReceiptNo(new CommonDAO().getSequenceNo("Receipt",travelBean.getProductId(),travelBean.getBranchCode(),"",""));
			travelBean.setDebitNo(new CommonDAO().getSequenceNo("Debit",travelBean.getProductId(),travelBean.getBranchCode(),"",""));
		}
		else{
			travelBean.setPolicyNo(fromPosition.get("POLICY_NO")==null?"":fromPosition.get("POLICY_NO").toString());
			travelBean.setReceiptNo(fromPosition.get("RECEIPT_NO")==null?"":fromPosition.get("RECEIPT_NO").toString());
			travelBean.setDebitNo(fromPosition.get("DEBIT_NOTE_NO")==null?"":fromPosition.get("DEBIT_NOTE_NO").toString());
		}
		LogManager.info("policyGeneration method() Exit||");
	}
	public void getSecondPageDts(HealthBean healBean) {
		// TODO Auto-generated method stub
		LogManager.push("getSecondPageDts method() Enter ||");
		try{
			String sql="";
			if(StringUtils.isNotBlank(healBean.getApplicationNo())){
				sql=getQuery("GET_POL_DTL");
				Object obj[] = new Object[]{healBean.getApplicationNo()};
				LogManager.info("Query=>"+sql);
				LogManager.info("Obj[0]=>"+healBean.getApplicationNo());
				List list=this.mytemplate.queryForList(sql,obj);
				if(list!=null &&list.size()>0)
				{
						Map map=(Map)list.get(0);
						healBean.setTitle(map.get("TITLE")==null?"":map.get("TITLE").toString());
						healBean.setInsurerName(map.get("CUST_NAME")==null?"":map.get("CUST_NAME").toString());
						healBean.setCusCivilId(map.get("CIVIL_ID")==null?"":map.get("CIVIL_ID").toString());
						healBean.setDob(map.get("DOB")==null?"":map.get("DOB").toString());
						healBean.setGender(map.get("GENDER")==null?"":map.get("GENDER").toString());
						healBean.setMaritalStatus(map.get("MARTIAL_STATUS")==null?"":map.get("MARTIAL_STATUS").toString());
						healBean.setOccupation(map.get("OCCUPATION")==null?"":map.get("OCCUPATION").toString());
						healBean.setRelation(map.get("RELATION")==null?"":map.get("RELATION").toString());
						healBean.setNationality(map.get("NATIONALITY")==null?"":map.get("NATIONALITY").toString());
						healBean.setSponsorId(map.get("SPONSOR_ID")==null?"":map.get("SPONSOR_ID").toString());
						healBean.setSponsorName(map.get("SPONSOR_NAME")==null?"":map.get("SPONSOR_NAME").toString());
						healBean.setSponsorCity(map.get("SPONSOR_CITY")==null?"":map.get("SPONSOR_CITY").toString());
						healBean.setSponsorMobileNo(map.get("SPONSOR_MOBILE")==null?"":map.get("SPONSOR_MOBILE").toString());
						healBean.setQuoteNo(map.get("QUOTE_NO")==null?"":map.get("QUOTE_NO").toString());
				}
				sql=getQuery("GET_POL_MSTR");
				obj = new Object[]{healBean.getApplicationNo()};
				LogManager.info("Query=>"+sql);
				LogManager.info("Obj[0]=>"+healBean.getApplicationNo());
				Map map=this.mytemplate.queryForMap(sql,obj);
				LogManager.info("Map=>"+map.size());
				if(map!=null&& map.size()>0)
				{
					healBean.setCoverPeriod(map.get("POLICY_TERM")==null?"":map.get("POLICY_TERM").toString());
					healBean.setInceptionDt(map.get("INSURANCE_START_DATE")==null?"":map.get("INSURANCE_START_DATE").toString());
					healBean.setExpiryDt(map.get("INSURANCE_END_DATE")==null?"":map.get("INSURANCE_END_DATE").toString());
					healBean.setFinalPremium(Double.parseDouble(map.get("TOTAL_PREMIUM")==null?"0.0":map.get("TOTAL_PREMIUM").toString()));
				    healBean.setDiscountAmt(Double.parseDouble(map.get("DISCOUNT_PREMIUM")==null?"0.0":map.get("DISCOUNT_PREMIUM").toString()));
					healBean.setSchemeCover(map.get("CLASS_ID")==null?"":map.get("CLASS_ID").toString());
					healBean.setScheme_Covers(map.get("CLASS_ID")==null?"":map.get("CLASS_ID").toString());
				}
				if(StringUtils.isNotBlank(healBean.getQuoteNo())){
					sql=getQuery("GET_HOME_POS_DET");
					obj = new Object[]{healBean.getQuoteNo()};
					LogManager.info("Query=>"+sql);
					LogManager.info("Obj[0]=>"+healBean.getQuoteNo());
					Map homeMap=this.mytemplate.queryForMap(sql,obj);
					if(homeMap!=null&& homeMap.size()>0)
					{
						LogManager.info("Size=>"+homeMap.size());
						healBean.setCustomerId(homeMap.get("CUSTOMER_ID")==null?"":homeMap.get("CUSTOMER_ID").toString());
						healBean.setPolicyNo(homeMap.get("POLICY_NO")==null?"":homeMap.get("POLICY_NO").toString());
						healBean.setFullName(homeMap.get("CUS_NAME")==null?"":homeMap.get("CUS_NAME").toString());
						healBean.setEmailId(homeMap.get("EMAIL")==null?"":homeMap.get("EMAIL").toString());
						healBean.setMobileNo(homeMap.get("MOBILE")==null?"":homeMap.get("MOBILE").toString());
						healBean.setReferralYN(homeMap.get("ADMIN_REFERRAL_YN")==null?"":homeMap.get("ADMIN_REFERRAL_YN").toString());
						healBean.setAdminRemarks(homeMap.get("ADMIN_REMARKS")==null?"":homeMap.get("ADMIN_REMARKS").toString());
						healBean.setModeOfPay(homeMap.get("PAYMENT_MODE")==null?"":homeMap.get("PAYMENT_MODE").toString());
						healBean.setFinalPremium(Double.parseDouble(homeMap.get("PREMIUM")==null?"0.0":homeMap.get("PREMIUM").toString()));
						healBean.setSign(homeMap.get("EXCESS_SIGN")==null?"+":homeMap.get("EXCESS_SIGN").toString());
						healBean.setLoadOrDiscPremium(Double.parseDouble(homeMap.get("EXCESS_PREMIUM")==null?"0.0":homeMap.get("EXCESS_PREMIUM").toString()));
						healBean.setEndtPremium(Double.parseDouble(homeMap.get("ENDT_PREMIUM")==null?"0.0":homeMap.get("ENDT_PREMIUM").toString())+Double.parseDouble(homeMap.get("POLICY_FEE")==null?"0.0":homeMap.get("POLICY_FEE").toString()));
						healBean.setPremiumPaid(Double.parseDouble(homeMap.get("PREMIUM")==null?"0.0":homeMap.get("PREMIUM").toString())-Double.parseDouble(homeMap.get("ENDT_PREMIUM")==null?"0.0":homeMap.get("ENDT_PREMIUM").toString())+("+".equals(healBean.getSign())?healBean.getLoadOrDiscPremium():healBean.getLoadOrDiscPremium()*(-1)));
						healBean.setPolicyFee(Double.parseDouble(homeMap.get("POLICY_FEE")==null?"0.0":homeMap.get("POLICY_FEE").toString()));
						healBean.setTotalPremium(Double.parseDouble(homeMap.get("OVERALL_PREMIUM")==null?"0.0":homeMap.get("OVERALL_PREMIUM").toString()));
						healBean.setAdminRefStatus(homeMap.get("ADMIN_REFERRAL_STATUS")==null?"":homeMap.get("ADMIN_REFERRAL_STATUS").toString());
						healBean.setReferralMsg(homeMap.get("REMARKS")==null?"":homeMap.get("REMARKS").toString());
						healBean.setAmendId(homeMap.get("AMEND_ID")==null?"0":homeMap.get("AMEND_ID").toString());
					}
					healBean.setCover(getCoverInfo(healBean.getProductId(),healBean.getSchemeCover(), healBean.getTravelCover(),healBean.getBranchCode()));
					if(!"admin".equals(healBean.getUser())){
						sql=getQuery("GET_REFERAL_YN");
						obj=new Object[1];
						obj[0]=healBean.getLoginId();
						LogManager.info("Query=>"+sql);
						LogManager.info("Obj[0]=>"+healBean.getLoginId());
						healBean.setShowReferralYN((String)this.mytemplate.queryForObject(sql, obj,String.class));
						LogManager.info("Result=>"+healBean.getShowReferralYN());
					}
				}
				
				
			}
				sql=getQuery("GET_SEARCH3");
				LogManager.info("Query=>"+sql);
				Object[] obj=new Object[1];
				obj[0]=healBean.getCustomerId();
				LogManager.info("obj[] ==> "+StringUtils.join(obj, ","));
				List list=this.mytemplate.queryForList(sql,obj);
				if(list!=null && list.size()>0)
				{
						Map map=(Map)list.get(0);
						healBean.setFullName(map.get("FIRST_NAME")!=null?map.get("FIRST_NAME").toString():map.get("COMPANY_NAME").toString());
						healBean.setMobileNo(map.get("MOBILE")==null?"":map.get("MOBILE").toString());
						healBean.setEmailId(map.get("EMAIL")==null?"":map.get("EMAIL").toString());
					if(StringUtils.isBlank(healBean.getApplicationNo())){
						healBean.setTitle(map.get("TITLE")==null?"":map.get("TITLE").toString());
						healBean.setCustomerId(map.get("CUSTOMER_ID")==null?"":map.get("CUSTOMER_ID").toString());
						healBean.setInsurerName(map.get("FIRST_NAME")!=null?map.get("FIRST_NAME").toString():map.get("COMPANY_NAME").toString());
						healBean.setNationality(map.get("NATIONALITY")==null?"":map.get("NATIONALITY").toString());
						healBean.setDob(map.get("DOB")==null?"":map.get("DOB").toString());
						healBean.setGender(map.get("GENDER")==null?"":map.get("GENDER").toString());
						healBean.setCusCivilId(map.get("CUSTOMER_SOURCE")==null?"":map.get("CUSTOMER_SOURCE").toString());
						healBean.setOccupation(map.get("OCCUPATION")==null?"":map.get("OCCUPATION").toString());
					}
				}
			
		}catch(Exception e)
		{
			LogManager.debug(e);
		}
		LogManager.push("getSecondPageDts method() Exit ||");
	}
	public String getGeratePolicy(HealthBean travelBean) {
		// TODO Auto-generated method stub
		LogManager.info("getGeratePolicy - Enter");
		String sql="";
		Object[] obj=new Object[0];
		int res=0;
		try
		{
			if("Y".equalsIgnoreCase(travelBean.getReferralYN())){
				sql=getQuery("UPD_REF_STATUS");
				obj=new Object[5];
				obj[0]=(!"getSave".equalsIgnoreCase(travelBean.getActionType())?"Referal":"");
				obj[1]=travelBean.getReferralComments();
				obj[2]=(!"getSave".equalsIgnoreCase(travelBean.getActionType())?travelBean.getReferralYN():"");
				obj[3]=(!"getSave".equalsIgnoreCase(travelBean.getActionType())?"Admin Referral":"");
				obj[4]=travelBean.getQuoteNo();
				LogManager.info("Query=>"+sql);
				LogManager.info("obj[] ==> "+StringUtils.join(obj, ","));
				res=this.mytemplate.update(sql,obj);
				LogManager.info("Result=>"+res);
			}else
			{
				sql=getQuery("UPD_HOME_POS_MASTER_MODEOFPAY");
				obj=new Object[6];
				obj[0]="";
				obj[1]="";
				obj[2]="";
				obj[3]=travelBean.getModeOfPay();
				obj[4]="";
				obj[5]=travelBean.getQuoteNo();
				LogManager.info("Query=>"+sql);
				LogManager.info("obj[] ==> "+StringUtils.join(obj, ","));
				res=this.mytemplate.update(sql,obj);
				LogManager.info("Result=>"+res);
				if("Y".equalsIgnoreCase(travelBean.getGeneratePolicyYN())&&!"getSave".equalsIgnoreCase(travelBean.getActionType())){
					policyGeneration(travelBean);
					sql=getQuery("UPD_POLICY_NO");
					obj=new Object[5];
					obj[0]=travelBean.getPolicyNo();
					obj[1]=travelBean.getReceiptNo();
					obj[2]=travelBean.getDebitNo();
					obj[3]=travelBean.getModeOfPay();
					obj[4]=travelBean.getQuoteNo();
					LogManager.info("Query=>"+sql);
					LogManager.info("obj[] ==> "+StringUtils.join(obj, ","));
					res=this.mytemplate.update(sql,obj);
					LogManager.info("Result=>"+res);
					new com.maan.common.dao.CommonDAO().closeTrnDateCalculation(travelBean.getQuoteNo(),travelBean.getBranchCode(),"HTOS");
				}
			}
		}catch(Exception e)
		{
			LogManager.debug(e);
		}
		LogManager.popRemove();
		LogManager.info("getGeratePolicy - Exit");
		return "SUCCESS";
	}
	public List<Object> getPolicyInformation(String quoteNo) {
		List<Object> policyInfo=null;		
		LogManager.push("getPolicyInformation - Enter");
		try{
			String sql=getQuery("GET_POLICYINFO");
			LogManager.info("Query=>" + sql);
			LogManager.info("QuoteNo=>" + quoteNo);
			policyInfo=this.mytemplate.queryForList(sql,new String[]{quoteNo});			
		   }
		catch (Exception e) {			
			LogManager.debug("EXCEPTION @ { " + e + " }");
		}		
		LogManager.info("getPolicyInformation - Exit || Result: " + policyInfo.size());
		return policyInfo;
	}
	public void getBackShowQuote(HealthBean travelBean) {
		// TODO Auto-generated method stub
		getSecondPageDts(travelBean);
	}
	public List<Object> getCoverInfo(String productId,String schemecover,String travelcover,String branchCode)
	{
		LogManager.info("getCoverInfo - Enter || schemecover: "+schemecover+travelcover);
		List<Object> list=null;		
		try{
			String sql=getQuery("GET_COVER_INFO");
			LogManager.info("Query=>"+sql);
			LogManager.info("Obj[0]=>"+productId);
			LogManager.info("Obj[1]=>"+schemecover);
			LogManager.info("Obj[2]=>"+travelcover);
			LogManager.info("Obj[3]=>"+branchCode);
			LogManager.info("Obj[4]=>"+branchCode);
			
			//list=this.mytemplate.queryForList(sql,new String[]{productId,schemecover,travelcover,branchCode,productId});
			//LogManager.info("List Size=>"+list.size());
		}catch(Exception e){
			LogManager.debug(e);
		}
		LogManager.info("getCoverInfo - Exit ");
		return 	list;
	}
	public String getCancelReissue(HealthBean travelBean)
	{
		LogManager.info("getCancelReissue - Enter"+travelBean.getCancelPolicy()+travelBean.getReissuePolicy());
		String result="";
		Object[] obj=new Object[0];
		try{
			if("YES".equalsIgnoreCase(travelBean.getCancelPolicy())&&"NO".equalsIgnoreCase(travelBean.getReissuePolicy())){
			LogManager.info("QuoteNo:"+travelBean.getQuoteNo());
			String sql=getQuery("GET_QUOTE_COUNT");
			obj=new Object[1];
			obj[0]=travelBean.getQuoteNo();
			int count=this.mytemplate.queryForInt(sql,obj);	
				if(count>0)
				{
					LogManager.info("Enter into CANCEL POLICY::");
					String sql2=getQuery("UPDATE_QUOTE_C");
					obj=new Object[4];
					obj[0]="C";
					obj[1]=travelBean.getReason();
					obj[2]=travelBean.getLoginId();
					obj[3]=travelBean.getPolicyNo();
					this.mytemplate.update(sql2,obj);   
					result="cancel";
				}else{
					LogManager.info("Enter into NO CANCEL::");
					result="nocancel";
				}
			}else if("NO".equalsIgnoreCase(travelBean.getCancelPolicy())&&"NO".equalsIgnoreCase(travelBean.getReissuePolicy())){
				LogManager.info("Enter into NOTHING::");
				result="nothing";
			}
			else{
				Map<String, String> map=new HashMap<String, String>();
				CallableStatement cstmt = null;
				Connection con = null;
				try {
					LogManager.info("Enter into TRAVEL_COPYQUOTE - Enter");
					con = DBConnection.getInstance().getDBConnection();
					cstmt = con.prepareCall("CALL TRAVEL_COPYQUOTE('R',?,?,?,?,?,?,?)");
					cstmt.setString(1, travelBean.getQuoteNo());
					cstmt.setString(2, travelBean.getLoginId());
					cstmt.setString(3, travelBean.getBranchCode());
					cstmt.setString(4, travelBean.getProductId());
					cstmt.registerOutParameter(5, java.sql.Types.VARCHAR);
					cstmt.registerOutParameter(6, java.sql.Types.VARCHAR);
					cstmt.registerOutParameter(7, java.sql.Types.VARCHAR);
					cstmt.execute();
					map.put("CUSTOMER_NAME", cstmt.getString(5));
					map.put("ERROR", cstmt.getString(6));
					map.put("QUOTE_NO", cstmt.getString(7));
					travelBean.setMap(map);
				}catch(Exception e){
					e.printStackTrace();
				}finally {
		          try {
		                if (cstmt != null)
			             cstmt.close();
		                 } catch (Exception e) { e.printStackTrace();} 
		           try {		
		               if (con != null && !con.isClosed())
		              con.close();
			                 } catch (Exception e) { e.printStackTrace(); }
		              }
				LogManager.info("Enter into TRAVEL_COPYQUOTE - Exit || STATUS: "+map.get("STATUS")+" || ERROR: "+map.get("ERROR")+" || QUOTE_NO: "+map.get("QUOTE_NO"));
			    result="reissue";
			}
		}catch(Exception e){
			LogManager.debug(e);
		}
		LogManager.info("getCancelReissue - Exit ");
		return result;
	}
	public String getAdminReferralUpdation(HealthBean travelBean) {
		// TODO Auto-generated method stub
		LogManager.info("getAdminReferralUpdation - Enter");
		String sql="",result="";
		Object[] obj=new Object[0];
		int res=0;
		try
		{
			sql=getQuery("UPD_POL_MSTR_EXCESS_PREMIUM");
			obj=new Object[3];
			obj[0]=travelBean.getDiscountAmt();
			obj[1]=travelBean.getFinalPremium();
			obj[2]=travelBean.getQuoteNo();
			LogManager.info("Query=>>"+sql);
			LogManager.info("Query=>>"+StringUtils.join(obj,","));
			res=this.mytemplate.update(sql,obj);              
			LogManager.info("Result=>>"+res);
			obj=new Object[13];
			obj[1]=travelBean.getAdminRemarks();
			obj[2]=travelBean.getLoadOrDiscPremium();
			obj[4]=travelBean.getSign();
			obj[5]=travelBean.getTotalPremium()+"";
			obj[6]=getCommision(travelBean.getFinalPremium(), travelBean.getApplicationNo());
			obj[7]=travelBean.getLoginId();
			obj[8]=travelBean.getFinalPremium();
			obj[9]=travelBean.getDiscountAmt();
			obj[10]=(travelBean.getEndtPremium()-travelBean.getPolicyFee());
			obj[11]=Double.parseDouble(getCommision((travelBean.getEndtPremium()-travelBean.getPolicyFee()), travelBean.getApplicationNo()));
			obj[12]=travelBean.getQuoteNo();
			if ("Y".equalsIgnoreCase(travelBean.getAdminRefStatus())){
				obj[0]="Admin";
				if(!"0".equals(travelBean.getAmendId()))
					obj[3]="E";
				else
					obj[3]="Y";
				travelBean.setReferralMsg(" Accepted.");
			}else if("N".equalsIgnoreCase(travelBean.getAdminRefStatus())){
				obj[0]="Referal";
				obj[3]="R";
				travelBean.setReferralMsg(" Rejected.");
			}else if ("A".equalsIgnoreCase(travelBean.getAdminRefStatus())){
				obj[0]="Referal";
				if(!"0".equals(travelBean.getAmendId()))
					obj[3]="E";
				else
					obj[3]="Y";
				travelBean.setReferralMsg(" Moved Pending.");
			}
			sql=getQuery("UPD_ADMIN_REFSTATUS");
			LogManager.info("Query=>>"+sql);
			LogManager.info("Query=>>"+StringUtils.join(obj,","));
			res=this.mytemplate.update(sql,obj);              
			LogManager.info("Result=>>"+res);			
			if(res>0)
				result="SUCCESS";
		}catch(Exception e)
		{
			LogManager.debug(e);
		}
		LogManager.popRemove();
		LogManager.info("getAdminReferralUpdation - Exit");
		return result;
	}
	public String getCommision(final double premium,final String appNo)throws BaseException {
		LogManager.push("getCommision method() Enter||");
		double commision=0; 
		try{
			String sql=getQuery("GET_LOGIN_PROD_ID");
			LogManager.info("Query=>"+sql);
			LogManager.info("Obj[0]=>"+appNo);
			Map map=this.mytemplate.queryForMap(sql,new Object[]{appNo});
			LogManager.info("Map Size=>"+map.size());
			sql=getQuery("GET_COMM");
			Object obj[] = {map.get("LOGIN_ID"),map.get("PROD_ID")};
			LogManager.info("Query=>"+sql);
			LogManager.info("Obj[0]=>"+map.get("LOGIN_ID"));
			LogManager.info("Obj[1]=>"+map.get("PROD_ID"));
			String comPer =(String)this.mytemplate.queryForObject(sql, obj,String.class);
			LogManager.info("Result=>"+comPer);
			if (0==Double.parseDouble(comPer)){
				commision = 0.0;
			}
			else{
				commision = premium * (Double.parseDouble(comPer) / 100.0);
			}
		}catch(Exception e)
		{
			LogManager.debug(e);
		}
		LogManager.info("getCommision method() Exit|| Commision=>"+commision);
		return commision+"";
	}
	public String getUpdateCalculatedPremium(HealthBean bean) {
		// TODO Auto-generated method stub
		LogManager.info("getUpdateCalculatedPremium method() Enter ||");
		//String result="";
		try{
			double minpremium=0.0;//,commPer=0.0,commission=0.0;
			int res=0;
			String value="";
			double policyFee=0.0;
			String sql="";
			Object[] obj=new Object[0];
			if(!"admin".equalsIgnoreCase(bean.getUser())){
				sql=getQuery("GET_MINIUM_PREMIUM");
				obj=new Object[2];
				obj[0]=bean.getApplicationNo();
				obj[1]=bean.getApplicationNo();
				LogManager.info("Query=>"+sql);
				LogManager.info("Obj[0-2]=>"+StringUtils.join(obj,","));
				value=(String)this.mytemplate.queryForObject(sql,obj,String.class);
				LogManager.info("Minimum Premium=>"+value);
				minpremium=Double.parseDouble(value);
				if(bean.getFinalPremium()<minpremium){
					bean.setFinalPremium(minpremium);
				}
			}
			
				obj=new Object[2];
				obj[0]=bean.getApplicationNo();
				obj[1]=bean.getBranchCode();
				sql=getQuery("GET_POLICY_FEE");
				LogManager.info("Query=>"+sql);
				LogManager.info("Obj[0-1]=>"+StringUtils.join(obj,","));
				List li=this.mytemplate.queryForList(sql,obj);
				if(li!=null && li.size()>0)
				{
					Map map=(Map)li.get(0);
					policyFee=Double.parseDouble(map.get("POLICY_FEE").toString());
				}
				sql=getQuery("UPD_HEALTH_MSTR_PREMIUM");
				obj=new Object[2];
				obj[0]=bean.getFinalPremium();
				obj[1]=bean.getApplicationNo();
				LogManager.info("Query=>"+sql);
				LogManager.info("Obj[0-1]=>"+StringUtils.join(obj,","));
				res=this.mytemplate.update(sql,obj);
				LogManager.info("Result=>"+res);
				bean.setTotalPremium(bean.getFinalPremium()+policyFee);
				sql=getQuery("UPD_HOME_POS_PREMIUM");
				obj=new Object[5];
				obj[0]=bean.getFinalPremium();
				obj[1]=bean.getTotalPremium();
				obj[2]=policyFee;
				obj[3]=getCommision(bean.getFinalPremium(),bean.getApplicationNo());//commission;
				obj[4]=bean.getApplicationNo();
				LogManager.info("Query=>"+sql);
				LogManager.info("Obj[0-3]=>"+StringUtils.join(obj,","));
				res=this.mytemplate.update(sql,obj);
				LogManager.info("Result=>"+res);
			//}
			/*if(res>0)
				result="SUCCESS";*/
		}catch(Exception e)
		{
			LogManager.debug(e);
		}
		LogManager.info("getUpdateCalculatedPremium method() Exit ||");
		return "";
	}
	public String getCalculatePremium(HealthBean bean){
		LogManager.info("getCalculatePremium method() Enter ||");
		CallableStatement cstmt = null;
		Connection con = null;
		try {
			String sql=getQuery("CAL_HEALTH_PREMIUM_CALC");
			LogManager.info("INOUT SP=>"+sql);
			LogManager.info("Arg In[0]=>"+bean.getProductId());
			LogManager.info("Arg In[1]=>"+bean.getUser());
			LogManager.info("Arg In[2]=>"+bean.getBranchCode());
			LogManager.info("Arg In[3]=>"+bean.getApplicationNo());
			con = DBConnection.getInstance().getDBConnection();
			cstmt = con.prepareCall(sql);
			cstmt.setString(1, bean.getProductId());
			cstmt.setString(2, bean.getUser());
			cstmt.setString(3, bean.getBranchCode());
			cstmt.setString(4, bean.getApplicationNo());
			cstmt.registerOutParameter(5, java.sql.Types.DOUBLE);
			cstmt.registerOutParameter(6, java.sql.Types.VARCHAR);
			cstmt.execute();
			LogManager.info("Arg Out[4]=>"+cstmt.getDouble(5));
			LogManager.info("Arg Out[5]=>"+cstmt.getString(6));
			bean.setTotalPremium(cstmt.getDouble(5));
			bean.setReferralMsg(cstmt.getString(6));
		}catch(Exception e){
			LogManager.debug(e);
		} finally {
			try {
				if (cstmt != null)
					cstmt.close();
			} catch (Exception e) {
				LogManager.debug(e);
			}
			try {
				if (con != null && !con.isClosed())
					con.close();
			} catch (Exception e) {
				LogManager.debug(e);
			}
		}
		/*if(StringUtils.isBlank(bean.getReferralMsg()))
			getUpdateCalculatedPremium(bean);*/
		LogManager.info("getCalculatePremium method() Exit Premium=>"+bean.getFinalPremium());
		return "SUCCESS";
	}
	public String getConstanctDetials(String detialId,String branchCode) {
		// TODO Auto-generated method stub
		LogManager.info("getConstanctDetials method() Enter");
		String constValue="0";
		try{
			String sql=getQuery("GET_CONSTRANCT_DTLS");
			Object[] obj=new Object[2];
			obj[0]=detialId;
			obj[1]=branchCode;
			LogManager.info("sql=>"+sql);
			LogManager.info("obj[]=>"+StringUtils.join(obj,","));
			constValue=(String)this.mytemplate.queryForObject(sql,obj,String.class);
		}catch(Exception e)
		{
			LogManager.debug(e);
		}
		LogManager.info("getConstanctDetials method() Exit Result=>"+constValue);
		return constValue;
	}
	public void updateCorrections(HealthBean bean)
	{
		String sql;
		Object[] obj;
		/*for (int i = 0; i < (bean.getSerialNos().size()); i++)
		{
			sql=getQuery("GET_TRAVEL_DTL_CNT");
			LogManager.info("Query=>"+sql);
			LogManager.info("Obj[]=>"+bean.getApplicationNo()+","+(bean.getSerialNos().get(i)));
			int count=this.mytemplate.queryForInt(sql,new Object[]{bean.getApplicationNo(),(bean.getSerialNos().get(i))});
			LogManager.info("Result=>"+count);
			sql=getQuery("travel.update.correction");
			LogManager.info("Query=>"+sql);
			obj=new Object[5];
			obj[0]=bean.getTravelNames().get(i);
			obj[1]=("-1".equals(bean.getGenders().get(i))?"":bean.getGenders().get(i));
			obj[2]=bean.getNationalitys().get(i);
			obj[3]=bean.getApplicationNo();
			obj[4]=bean.getSerialNos().get(i);
			LogManager.info("obj[] ==> "+StringUtils.join(obj, ","));
			this.mytemplate.update(sql,obj);
		}*/
	}
	
	public int getEffectiveDate(HealthBean bean)
	{
		String sql;
		int count=0;
		sql=getQuery("TRAVEL_CORRECTION_CHECK");
		try{
			count=this.mytemplate.queryForInt(sql,new Object[]{bean.getQuoteNo()});
		}
		catch(Exception e){
			LogManager.info("Exception=>"+e);
		}
		return count;
	}
	public List <Object> getDetailsView(HealthBean bean)
	{
		String sql=getQuery("TRAVEL_GET_CUSTOMER_DETAILS");
		String sql1=getQuery("TRAVEL_GET_PASSENGER_DETAILS");
		List<Object> trList=null;
		try{
			trList=this.mytemplate.queryForList(sql1,new Object[]{bean.getQuoteNo()});
			Map homeMap=this.mytemplate.queryForMap(sql,new Object[]{bean.getQuoteNo(),bean.getProductId()});
			if(homeMap!=null&& homeMap.size()>0)
			{	
				LogManager.info("Size=>"+homeMap.size());
				bean.setCustomerId(homeMap.get("CUSTOMER_ID")==null?"":homeMap.get("CUSTOMER_ID").toString());
				bean.setFullName(homeMap.get("CUS_NAME")==null?"":homeMap.get("CUS_NAME").toString());
				bean.setEmailId(homeMap.get("EMAIL")==null?"":homeMap.get("EMAIL").toString());
				bean.setAddress(homeMap.get("ADDRESS")==null?"":homeMap.get("ADDRESS").toString());
				bean.setFax(homeMap.get("FAX")==null?"":homeMap.get("FAX").toString());
				bean.setMobileNo(homeMap.get("MOBILE")==null?"":homeMap.get("MOBILE").toString());
				bean.setOccupation(homeMap.get("OCCUPATION")==null?"":homeMap.get("OCCUPATION").toString());
				bean.setPoBox(homeMap.get("POBOX")==null?"":homeMap.get("POBOX").toString());
				bean.setCountry(homeMap.get("COUNTRY")==null?"":homeMap.get("COUNTRY").toString());
			}
		}
		catch(Exception e){
			LogManager.info("Exception=>"+e);
		}
		return trList;
	}
	public List <Object> getPolicyView(HealthBean bean)
	{
		String sql=getQuery("TRAVEL_GET_CUSTOMER_POLICY_DETAILS");
		List<Object> trPolicyList=null;
		try{
			trPolicyList=this.mytemplate.queryForList(sql,new Object[]{bean.getQuoteNo()});
		}
		catch(Exception e){
			LogManager.info("Exception=>"+e);
		}
		return trPolicyList;
	}
	public List<UploadBean> getRequiredDocumentList(HealthBean bean){
		LogManager.info("getRequiredDocumentList method() Enter");
    	List<UploadBean> result = new ArrayList<UploadBean>();
    	final String query = getQuery("GET_REQUIRED_DOC_LIST");
    	final Object[] args = new Object[]{bean.getProductId(), bean.getSchemeCover()};
    	LogManager.info("GET_REQUIRED_DOC_LIST =>"+query);
    	LogManager.info("GET_REQUIRED_DOC_LIST Args => "+bean.getProductId() + " => "+bean.getSchemeCover());
    	try {
    		result = this.mytemplate.query(query, args, new RowMapper() {
    			public Object mapRow(final ResultSet rs, final int idVal) throws SQLException {
    				final UploadBean updBean = new UploadBean();
    				updBean.setDocId(rs.getString("DOC_ID"));
    				updBean.setDocName(rs.getString("DOC_NAME"));
    				return updBean;
    			}});
        } catch (Exception e) {
        	LogManager.debug(e);
        }
        LogManager.info("getRequiredDocumentList - Exit");
    	return result;
    }
	public Map getHealthPDFDetails(String quoteNo)
	{
		Map hMap=new HashMap();
		try{
			String sql=getQuery("GET_HEALTH_PDF_DTLS");
			final Object[] obj = new Object[]{quoteNo};
			LogManager.info("Sql=>"+sql);
			LogManager.info("Obj[]=>"+StringUtils.join(obj,","));
			hMap=this.mytemplate.queryForMap(sql,obj);
		  } catch (Exception e) {
        	LogManager.debug(e);
        }
		return hMap;
	}
}
