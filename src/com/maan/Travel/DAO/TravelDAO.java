package com.maan.Travel.DAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.core.RowMapper;
import com.maan.DBCon.DBConnection;
import com.maan.Home.Controller.HomeBean;
import com.maan.Travel.controller.CoverageBean;
import com.maan.Travel.controller.TravelBean;
import com.maan.common.LogManager;
import com.maan.common.MyJdbcTemplate;
import com.maan.common.dao.CommonDAO;
import com.maan.common.exception.BaseException;
import com.maan.common.util.ResourceBundleUtil;
import com.maan.copyquote.service.CopyQuoteService;
import com.maan.customer.dao.CustomerDAO;
import com.maan.payment.PaymentDAO;
import com.opensymphony.xwork2.ActionContext;


public class TravelDAO extends MyJdbcTemplate {
	Map<String, Object> session=ActionContext.getContext().getSession();
	CommonDAO commonDAO = new CommonDAO();
	public String getUpdateTravellersInfo(TravelBean bean) {
		LogManager.info("getUpdateTravellersInfo - Enter ");
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
					bean.setQuoteNo(new CommonDAO().getSequenceNo("Quote",bean.getProductId(),bean.getBranchCode(),bean.getApplicationNo(),""));
				sql=getQuery("INS_TRAVEL_DTL");
				List slList=new ArrayList();
				List trList=new ArrayList();
				String slNos="";
				for (int i = 0; i < (saveFlag==true?bean.getSerialNos().size():bean.getAges().size()); i++)
				{
					int slNo=this.mytemplate.queryForInt(getQuery("GET_SLNO_TRAVEL_DTL"),new Object[]{bean.getApplicationNo()});
					slList.add(slNo);
					trList.add(i+1);
					LogManager.info("Query=>"+sql);
					obj=new Object[17];
					obj[0]=bean.getApplicationNo();
					obj[1]=bean.getQuoteNo();
					obj[2]=slNo;
					obj[3]=bean.getTravelNames().get(i);
					obj[4]=StringUtils.isBlank(bean.getDobs().get(i))?"":bean.getDobs().get(i);
					obj[5]="0";
					obj[6]="Y";
					obj[7]=bean.getRelations().get(i);
					obj[8]=("-1".equals(bean.getGenders().get(i))?"":bean.getGenders().get(i));
					obj[9]=bean.getNationalitys().get(i);
					obj[10]=(saveFlag==true?"":bean.getAges().get(i));
					obj[11]=(saveFlag==true?"":bean.getAges().get(i));
					obj[12]=(saveFlag==true?"":bean.getAges().get(i));
					obj[13]=(saveFlag==true?"":bean.getAges().get(i));
					obj[14]=bean.getPassportNo().get(i);
					obj[15]=bean.getPassportExpDate().get(i);
					obj[16] = bean.getTravelLastNames().get(i);
					LogManager.info("obj[] ==> "+StringUtils.join(obj, ","));
					res=this.mytemplate.update(sql,obj);
					LogManager.info("Result=>"+res);
					if(!saveFlag){
						if(i==0)
							slNos+=slNo;
		  				else 
		  					slNos+="','"+slNo;
				    }
				 }
				if(!saveFlag){
					sql=getQuery("DEL_TRAVEL_DTL_ALL")+"'"+slNos+"')";
					obj=new Object[1];
					obj[0]=bean.getApplicationNo();
					LogManager.info("Query=>"+sql);
					LogManager.info("obj[0]=>"+obj[0]);
					LogManager.info("Result=>"+this.mytemplate.update(sql,obj));
			    }
				bean.setSerialNos(slList);
				bean.setTravelList(trList);
				sql=getQuery("INS_TRAVEL_HEADER");
				obj=new Object[30];
				obj[0]=bean.getApplicationNo();
				obj[1]=bean.getQuoteNo();
				obj[2]= bean.getApplicationNo();
				obj[3]= bean.getApplicationNo();
				obj[4]= bean.getApplicationNo();
				obj[5]=bean.getCoverPeriod();
				obj[6]="";//claim
				obj[7]="";//claim Details
				obj[8]="0";//sum insured
				obj[9]=StringUtils.isBlank(bean.getInceptionDt())?"":bean.getInceptionDt();
				obj[10]=StringUtils.isBlank(bean.getExpiryDt())?"":bean.getExpiryDt();
				obj[11]="0";
				obj[12]=bean.getCustomerId();
				obj[13]=bean.getLoginId();
				obj[14] = "Y";
				obj[15] = bean.getProductId();			
				/*if(!"None".equals(bean.getTravel_Covers()))
					obj[16] = bean.getScheme_Covers()+"_"+bean.getTravel_Covers();//schemeName; //NEW COLUMN ADDED IN TRAVEL_HEADER SCHEME_COVER
				else
					obj[16] = bean.getScheme_Covers();*/
				obj[16] = "";
				obj[17] = "";//countryOrigin;
				obj[18] = "";//countryDestn;
				obj[19] = StringUtils.isBlank(bean.getSchemeCover())?"":bean.getSchemeCover();
				obj[20] = bean.getTravelCover();
				obj[21] = "4";//StringUtils.isBlank(bean.getSchemeCover())?"":bean.getSchemeCover();
				obj[22]=bean.getApplicationNo();
				obj[23]=bean.getApplicationNo();
				obj[24]=bean.getApplicationNo();
				obj[25]=bean.getApplicationNo();
				obj[26]=bean.getApplicationNo();
				obj[27]=bean.getApplicationNo();
				obj[28]=bean.getApplicationNo();
				obj[29]=bean.getApplicationNo();
				LogManager.info("Query=>"+sql);
				LogManager.info("obj[] ==> "+StringUtils.join(obj, ","));
				res=this.mytemplate.update(sql,obj);
				LogManager.info("Result=>"+res);
				obj=new Object[19];
				if(StringUtils.isNotBlank(bean.getReferralMsg())){
					sql=getQuery("INS_HOME_POS_MASTER_REF");
					obj[15]=bean.getReferralMsg();//Referal Msg
			    }
				else
				{
					sql=getQuery("INS_HOME_POS_MASTER");
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
				obj[9]=StringUtils.isBlank(bean.getInceptionDt())?"":bean.getInceptionDt();
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
				obj[14]=StringUtils.isBlank(bean.getSchemeCover())?"":bean.getSchemeCover();
				obj[16]=bean.getProductId();
				obj[17] = bean.getBrokerCode();
				obj[18] = bean.getExecutive();
				LogManager.info("obj[] ==> "+StringUtils.join(obj, ","));
				res=this.mytemplate.update(sql,obj);
				LogManager.info("Result=>"+res);
				//getInsertOrUpdateTravelCoverDtls(bean);
				if(res>0){
					/*if("getQuote".equalsIgnoreCase(bean.getActionType()))
						getSecondPageDts(bean);*/
					result="SUCCESS";
				}
			}else
			{
				if(StringUtils.isBlank(bean.getQuoteNo())&&("getQuote".equalsIgnoreCase(bean.getActionType())||saveFlag))
					bean.setQuoteNo(new CommonDAO().getSequenceNo("Quote",bean.getProductId(),bean.getBranchCode(),bean.getApplicationNo(),""));
				List slList=new ArrayList();
				List trList=new ArrayList();
				String slNos="";
				for (int i = 0; i < (saveFlag==true?bean.getSerialNos().size():bean.getAges().size()); i++)
				{
					trList.add(i+1);
					sql=getQuery("GET_TRAVEL_DTL_CNT");
					LogManager.info("Query=>"+sql);
					LogManager.info("Obj[]=>"+bean.getApplicationNo()+","+(bean.getSerialNos().get(i)));
					int count=this.mytemplate.queryForInt(sql,new Object[]{bean.getApplicationNo(),(bean.getSerialNos().get(i))});
					LogManager.info("Result=>"+count);
					if(count==0){
						sql=getQuery("INS_TRAVEL_DTL");
						int slNo=this.mytemplate.queryForInt(getQuery("GET_SLNO_TRAVEL_DTL"),new Object[]{bean.getApplicationNo()});
						slList.add(slNo);
						LogManager.info("Query=>"+sql);
						obj=new Object[17];
						obj[0]=bean.getApplicationNo();
						obj[1]=bean.getQuoteNo();
						obj[2]=slNo;
						obj[3]=bean.getTravelNames().get(i);
						obj[4]=StringUtils.isBlank(bean.getDobs().get(i))?"":bean.getDobs().get(i);
						obj[5]="0";
						obj[6]="Y";
						obj[7]=bean.getRelations().get(i);
						obj[8]=("-1".equals(bean.getGenders().get(i))?"":bean.getGenders().get(i));
						obj[9]=bean.getNationalitys().get(i);
						obj[10]=(saveFlag==true?"":bean.getAges().get(i));
						obj[11]=(saveFlag==true?"":bean.getAges().get(i));
						obj[12]=(saveFlag==true?"":bean.getAges().get(i));
						obj[13]=(saveFlag==true?"":bean.getAges().get(i));
						obj[14]=bean.getPassportNo().get(i);
						obj[15]=bean.getPassportExpDate().get(i);
						obj[16]=bean.getTravelLastNames().get(i);
						LogManager.info("obj[] ==> "+StringUtils.join(obj, ","));
						res=this.mytemplate.update(sql,obj);
						LogManager.info("Result=>"+res);
						if(!saveFlag){
							if(i==0)
								slNos+=slNo;
			  				else 
			  					slNos+="','"+slNo;
					    }
					}else 
					{
						sql=getQuery("UPD_TRAVEL_DTL");
						LogManager.info("Query=>"+sql);
						obj=new Object[17];
						obj[0]=bean.getTravelNames().get(i);
						obj[1]=StringUtils.isBlank(bean.getDobs().get(i))?"":bean.getDobs().get(i);
						obj[2]="0";
						obj[3]="Y";
						obj[4]=bean.getRelations().get(i);
						obj[5]=("-1".equals(bean.getGenders().get(i))?"":bean.getGenders().get(i));
						obj[6]=bean.getNationalitys().get(i);
						obj[7]=(saveFlag==true?"":bean.getAges().get(i));
						obj[8]=bean.getQuoteNo();
						obj[9]=(saveFlag==true?"":bean.getAges().get(i));
						obj[10]=(saveFlag==true?"":bean.getAges().get(i));
						obj[11]=(saveFlag==true?"":bean.getAges().get(i));
						obj[12]=bean.getPassportNo().get(i);
						obj[13] = bean.getPassportExpDate().get(i);
						obj[14] = bean.getTravelLastNames().get(i);
						obj[15]=bean.getApplicationNo();
						obj[16]=bean.getSerialNos().get(i);
						removeNull(obj);
						LogManager.info("obj[] ==> "+StringUtils.join(obj, ","));
						res=this.mytemplate.update(sql,obj);
						LogManager.info("Result=>"+res);
						slList.add(bean.getSerialNos().get(i));
						if(!saveFlag){
							if(i==0)
								slNos+=bean.getSerialNos().get(i);
			  				else 
			  					slNos+="','"+bean.getSerialNos().get(i);
					    }
					}
				}
				if(!saveFlag){
					sql=getQuery("DEL_TRAVEL_DTL_ALL")+"'"+slNos+"')";
					obj=new Object[1];
					obj[0]=bean.getApplicationNo();
					LogManager.info("Query=>"+sql);
					LogManager.info("obj[0]=>"+obj[0]);
					LogManager.info("Result=>"+this.mytemplate.update(sql,obj));
			    }
				bean.setSerialNos(slList);
				bean.setTravelList(trList);
				sql=getQuery("UPD_TRAVEL_HEADER");
				LogManager.info("Query=>"+sql);
				obj=new Object[26];
				obj[0]=bean.getApplicationNo();
				obj[1]=bean.getApplicationNo();
				obj[2]=bean.getApplicationNo();
				obj[3]=bean.getCoverPeriod();
				obj[4]="";//claim
				obj[5]="";//claim Details
				obj[6]="0";//sum insured
				obj[7]=StringUtils.isBlank(bean.getInceptionDt())?"":bean.getInceptionDt();
				obj[8]=StringUtils.isBlank(bean.getExpiryDt())?"":bean.getExpiryDt();
				obj[9] = bean.getProductId();		
/*				if(!"None".equals(bean.getTravel_Covers()))
					obj[10] = bean.getScheme_Covers()+"_"+bean.getTravel_Covers();//schemeName; //NEW COLUMN ADDED IN TRAVEL_HEADER SCHEME_COVER
				else
					obj[10] = bean.getScheme_Covers();*/
				obj[10] = "";
				obj[11] = "";//countryOrigin;
				obj[12] = "";//countryDestn;
				obj[13] = bean.getQuoteNo();
				obj[14] = StringUtils.isBlank(bean.getSchemeCover())?"":bean.getSchemeCover();
				obj[15] = bean.getTravelCover();
				obj[16] = "4";//StringUtils.isBlank(bean.getSchemeCover())?"":bean.getSchemeCover();
				obj[17] = bean.getApplicationNo();
				obj[18] = bean.getApplicationNo();
				obj[19] = bean.getApplicationNo();
				obj[20] = bean.getApplicationNo();
				obj[21] = bean.getApplicationNo();
				obj[22] = bean.getApplicationNo();
				obj[23] = bean.getApplicationNo();
				obj[24] = bean.getApplicationNo();
				obj[25] = bean.getApplicationNo();
				removeNull(obj);
				LogManager.info("obj[] ==> "+StringUtils.join(obj, ","));
				res=this.mytemplate.update(sql,obj);
				LogManager.info("Result=>"+res);
				obj=new Object[14];
				if(StringUtils.isNotBlank(bean.getReferralMsg())){
					sql=getQuery("UPD_HOME_POS_MASTER_REF");
					obj[7]=bean.getReferralMsg();// memoranda;
					LogManager.info("Msg=>"+bean.getReferralMsg());
			    }
				else
				{
					sql=getQuery("UPD_HOME_POS_MASTER");
					obj[7]="";// memoranda;
				}
				if("admin".equals(bean.getUser())){
					sql=getQuery("UPD_HOME_POS_MASTER_ADMIN");
					obj[7]="";
				}
				LogManager.info("Query=>"+sql);
				obj[0]="";//AGE_ABOVE_SIXTY_FIVE
				obj[1]=StringUtils.isBlank(bean.getInceptionDt())?"":bean.getInceptionDt();//bean.getCustomerId();
				obj[2]="";//treatment
				obj[3]=StringUtils.isBlank(bean.getExpiryDt())?"":bean.getExpiryDt();
				obj[4]=StringUtils.isBlank(bean.getSchemeCover())?"":bean.getSchemeCover();
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
				obj[11] = bean.getBrokerCode();
				obj[12] = bean.getExecutive();
				obj[13]=bean.getApplicationNo();
				LogManager.info("obj[] ==> "+StringUtils.join(obj, ","));
				res=this.mytemplate.update(sql,obj);
				LogManager.info("Result=>"+res);
				//getInsertOrUpdateTravelCoverDtls(bean);
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
		LogManager.info("getUpdateTravellersInfo - Exit");
		return result;	
	}
	
	public void getInsertOrUpdateTravelCoverDtls(TravelBean bean) 
	{
		LogManager.info("getInsertOrUpdateTravelCoverDtls - Enter");
		try{
			String sql;
			Object[] obj=null;
			int res=0;
			List coverages =getTravelCoverages(bean.getSchemeCover(),bean.getTravelCover(), bean.getBranchCode(),bean.getProductId());
	        if(coverages != null &&coverages.size()>0){
	        		String coverIds="";
	            	for(int i=0;i<coverages.size();i++){
	            		Map map1=(Map)coverages.get(i);
						sql=getQuery("GET_TRAVEL_COVER_CNT");
						obj=new Object[2];
						obj[0]=bean.getApplicationNo();
						obj[1]=map1.get("COVERAGES_ID");
						LogManager.info("Query=>"+sql);
						LogManager.info("obj[0]=>"+obj[0]);
		  				LogManager.info("obj[1]=>"+obj[1]);
						res=this.mytemplate.queryForInt(sql,obj);
						LogManager.info("Result=>"+res);
						if(res==0)
						{
							sql=getQuery("INS_TRAVEL_COVER_DTLS");
							obj=new Object[6];					  
							obj[0]=bean.getApplicationNo();
							obj[1]=bean.getQuoteNo();
							obj[2]=bean.getProductId();
							obj[3]=map1.get("COVERAGES_ID");
							obj[4]=map1.get("COVERAGES_VALUE");
							obj[5]=bean.getCoverages().get(i)==null?"":bean.getCoverages().get(i);
							LogManager.info("Query=>"+sql);
							LogManager.info("obj[]=>"+StringUtils.join(obj,","));
							res=this.mytemplate.update(sql,obj);
			  				LogManager.info("Result=>"+res);
			  				if(i==0)
			  					coverIds+=map1.get("COVERAGES_ID");
			  				else
		  						coverIds+="','"+map1.get("COVERAGES_ID");
			  				
						}else
						{
							sql=getQuery("UPD_TRAVEL_COVER_DTLS");
							obj=new Object[6];
							obj[0]=bean.getQuoteNo();
							obj[1]=bean.getProductId();
							obj[2]=map1.get("COVERAGES_VALUE");
							obj[3]=bean.getCoverages().get(i)==null?"":bean.getCoverages().get(i);
							obj[4]=bean.getApplicationNo();
							obj[5]=map1.get("COVERAGES_ID");
							LogManager.info("Query=>"+sql);
							LogManager.info("obj[]=>"+StringUtils.join(obj,","));
							res=this.mytemplate.update(sql,obj);
			  				LogManager.info("Result=>"+res);
			  				if(i==0)
			  					coverIds+=map1.get("COVERAGES_ID");
			  				else 
			  					coverIds+="','"+map1.get("COVERAGES_ID");
						}
	            	}
	            	sql=getQuery("DEL_TRAVEL_COVER_DTLS")+"'"+coverIds+"')";
					obj=new Object[1];
					obj[0]=bean.getApplicationNo();
					LogManager.info("Query=>"+sql);
					LogManager.info("obj[0]=>"+obj[0]);
					LogManager.info("Result=>"+this.mytemplate.update(sql,obj));
	        }else
	        {
	        	sql=getQuery("DEL_ALL_TRAVEL_COVER_DTLS");
	        	obj=new Object[1];
				obj[0]=bean.getApplicationNo();
				LogManager.info("Query=>"+sql);
				LogManager.info("obj[0]=>"+obj[0]);
				LogManager.info("Result=>"+this.mytemplate.update(sql,obj));
	        }
		}catch(Exception e)
		{
			LogManager.debug(e);
		}
		LogManager.popRemove();
		LogManager.info("getInsertOrUpdateTravelCoverDtls - Exit");
	}
	/*public synchronized String getMaxId(String loginBra,String pids,String type,String remarks) throws BaseException 
	{
		LogManager.push("getMaxId method() Enter");
		String maxId = null;
		try{
			Object obj[] = new Object[]{loginBra, pids, loginBra, pids};
			String sql=getQuery("GET_MAX_ID1")+" " +type+" "+getQuery("GET_MAX_ID2");
			LogManager.info("Query=>"+sql);
			LogManager.info("obj[] ==> "+StringUtils.join(obj, ","));
			maxId =(String)this.mytemplate.queryForObject(sql, obj,String.class); 
			LogManager.info("Result=>"+maxId);
			sql=getQuery("UPD_MAX_ID1")+" " +type+" "+getQuery("UPD_MAX_ID2");
			LogManager.info("Query=>"+sql);
			obj = new Object[]{maxId,maxId,loginBra, pids, loginBra, pids};
			LogManager.info("obj[] ==> "+StringUtils.join(obj, ","));
			int res=this.mytemplate.update(sql,obj);
			LogManager.info("Result=>"+res);
		}catch(Exception e)
		{
			LogManager.debug(e);
		}
		LogManager.popRemove();
		LogManager.push("getMaxId method() Exit || maxId=>"+maxId);
		return maxId;
	}*/
	private void policyGeneration(TravelBean bean)throws BaseException {
		LogManager.info("policyGeneration method() Enter||");
		Object obj[] = new Object[]{bean.getQuoteNo(),bean.getProductId(),bean.getProductId()};
		String sql=getQuery("GET_POLICY_STATUS");
		LogManager.info("Query=>"+sql);
		LogManager.info("Obj[]=>"+StringUtils.join(obj,","));
		final Map fromPosition = this.mytemplate.queryForMap(sql,obj);
		LogManager.info("Map Size=>"+fromPosition.size());
		if ("Y".equalsIgnoreCase(fromPosition.get("STATUS").toString())) {
			/*//sql = getQuery("GET_POLICY_NO");
			obj = new Object[]{bean.getBranchCode(),bean.getProductId(),bean.getBranchCode(),bean.getProductId(),bean.getBranchCode(),bean.getProductId(),bean.getBranchCode(),bean.getProductId()};
			LogManager.info("Query=>"+sql);
			LogManager.info("Obj[]=>"+StringUtils.join(obj,","));
			Map brCodePrefix =  this.mytemplate.queryForMap(sql,obj);
			bean.setPolicyNo(new CommonDAO().getPolicyNo(bean.getBranchCode(), bean.getProductId(),"POLICY_TYPE_ID"));
			bean.setReceiptNo(new CommonDAO().getPolicyNo(bean.getBranchCode(),  bean.getProductId(),"RECEIPT_TYPE_ID"));
			bean.setDebitNo(new CommonDAO().getPolicyNo(bean.getBranchCode(),  bean.getProductId(),"DEBIT_TYPE_ID"));*/
			bean.setPolicyNo(new CommonDAO().getSequenceNo("Policy",bean.getProductId(),bean.getBranchCode(),bean.getQuoteNo(),""));
			bean.setReceiptNo(new CommonDAO().getSequenceNo("Receipt",bean.getProductId(),bean.getBranchCode(),"",""));
			bean.setDebitNo(new CommonDAO().getSequenceNo("Debit",bean.getProductId(),bean.getBranchCode(),"",""));
		}
		else{
			bean.setPolicyNo(fromPosition.get("POLICY_NO")==null?"":fromPosition.get("POLICY_NO").toString());
			bean.setReceiptNo(fromPosition.get("RECEIPT_NO")==null?"":fromPosition.get("RECEIPT_NO").toString());
			bean.setDebitNo(fromPosition.get("DEBIT_NOTE_NO")==null?"":fromPosition.get("DEBIT_NOTE_NO").toString());
		}
		LogManager.info("policyGeneration method() Exit||");
	}
	public void getSecondPageDts(TravelBean bean) {
		// TODO Auto-generated method stub
		LogManager.push("getSecondPageDts method() Enter ||");
		try{
			String sql="";
			Object obj[] = new Object[]{bean.getApplicationNo()};
			if(StringUtils.isNotBlank(bean.getApplicationNo())){
				List<Map<String,Object>> res=getTravelCoverDetails(bean.getApplicationNo());
				if(res.size()>0){
					List<String>trList=new ArrayList<String>();
					List<Integer>sNo=new ArrayList<Integer>();
					List<String>name=new ArrayList<String>();
					List<String>dob=new ArrayList<String>();
					List<String>gender=new ArrayList<String>();
					List<String>relation=new ArrayList<String>();
					List<String>nationality=new ArrayList<String>();
					List<Double>premium=new ArrayList<Double>();
					List<String> passportNo = new ArrayList<String>();
					List<String> passportExpDate = new ArrayList<String>();
					List<String> lastName = new ArrayList<String>();
					for(int i=0;i<res.size();i++)
					{
						trList.add(""+(i+1));
						sNo.add(Integer.parseInt(res.get(i).get("SERIAL_NO").toString()));
						name.add(res.get(i).get("PASSENGER_NAME")==null?"":res.get(i).get("PASSENGER_NAME").toString());
						dob.add(res.get(i).get("DOB")==null?"":res.get(i).get("DOB").toString());
						gender.add(res.get(i).get("GENDER")==null?"":res.get(i).get("GENDER").toString());
						relation.add(res.get(i).get("RELATION")==null?"":res.get(i).get("RELATION").toString());
						nationality.add(res.get(i).get("NATIONALITY")==null?"":res.get(i).get("NATIONALITY").toString());
						premium.add(Double.parseDouble(res.get(i).get("PREMIUM")==null?"0.0":res.get(i).get("PREMIUM").toString()));
						passportNo.add(res.get(i).get("PASSPORT_NO")==null?"":res.get(i).get("PASSPORT_NO").toString());
						passportExpDate.add(res.get(i).get("PASSPORT_EXP_DATE")==null?"":res.get(i).get("PASSPORT_EXP_DATE").toString());
						lastName.add(res.get(i).get("PASSPANGER_LAST_NAME")==null?"":res.get(i).get("PASSPANGER_LAST_NAME").toString());
						bean.setQuoteNo(res.get(i).get("QUOTE_NO")==null?"":res.get(i).get("QUOTE_NO").toString());
						
					}
					bean.setSerialNos(sNo);
					bean.setTravelNames(name);
					bean.setDobs(dob);
					bean.setGenders(gender);
					bean.setRelations(relation);
					bean.setNationalitys(nationality);
					bean.setPassportNo(passportNo);
					bean.setPassportExpDate(passportExpDate);
					bean.setTravelPremium(premium);
					bean.setTravelList(trList);
					bean.setTravelLastNames(lastName);
				}
				
				/*sql=getQuery("GET_TRAVEL_DTL");
				Object obj[] = new Object[]{bean.getApplicationNo()};
				LogManager.info("Query=>"+sql);
				LogManager.info("Obj[0]=>"+bean.getApplicationNo());
				List list=this.mytemplate.queryForList(sql,obj);
				if(list!=null &&list.size()>0)
				{
					List<String>trList=new ArrayList<String>();
					List<Integer>sNo=new ArrayList<Integer>();
					List<String>name=new ArrayList<String>();
					List<String>dob=new ArrayList<String>();
					List<String>gender=new ArrayList<String>();
					List<String>relation=new ArrayList<String>();
					List<String>nationality=new ArrayList<String>();
					List<Double>premium=new ArrayList<Double>();
					List<String> passportNo = new ArrayList<String>();
					List<String> passportExpDate = new ArrayList<String>();
					List<String> lastName = new ArrayList<String>();
					for(int i=0;i<list.size();i++)
					{
						Map map=(Map)list.get(i);
						trList.add(""+(i+1));
						sNo.add(Integer.parseInt(map.get("SERIAL_NO").toString()));
						name.add(map.get("PASSENGER_NAME")==null?"":map.get("PASSENGER_NAME").toString());
						dob.add(map.get("DOB")==null?"":map.get("DOB").toString());
						gender.add(map.get("GENDER")==null?"":map.get("GENDER").toString());
						relation.add(map.get("RELATION")==null?"":map.get("RELATION").toString());
						nationality.add(map.get("NATIONALITY")==null?"":map.get("NATIONALITY").toString());
						premium.add(Double.parseDouble(map.get("PREMIUM")==null?"0.0":map.get("PREMIUM").toString()));
						passportNo.add(map.get("PASSPORT_NO")==null?"":map.get("PASSPORT_NO").toString());
						passportExpDate.add(map.get("PASSPORT_EXP_DATE")==null?"":map.get("PASSPORT_EXP_DATE").toString());
						lastName.add(map.get("PASSPANGER_LAST_NAME")==null?"":map.get("PASSPANGER_LAST_NAME").toString());
						bean.setQuoteNo(map.get("QUOTE_NO")==null?"":map.get("QUOTE_NO").toString());
						
					}
					bean.setSerialNos(sNo);
					bean.setTravelNames(name);
					bean.setDobs(dob);
					bean.setGenders(gender);
					bean.setRelations(relation);
					bean.setNationalitys(nationality);
					bean.setPassportNo(passportNo);
					bean.setPassportExpDate(passportExpDate);
					bean.setTravelPremium(premium);
					bean.setTravelList(trList);
					bean.setTravelLastNames(lastName);
				}*/
				
				sql=getQuery("GET_TRAVEL_HEADER");
				obj = new Object[]{bean.getApplicationNo()};
				LogManager.info("Query=>"+sql);
				LogManager.info("Obj[0]=>"+bean.getApplicationNo());
				Map map=this.mytemplate.queryForMap(sql,obj);
				LogManager.info("Map=>"+map.size());
				if(map!=null&& map.size()>0)
				{
					bean.setCoverPeriod(map.get("POLICY_TERM")==null?"":map.get("POLICY_TERM").toString());
					bean.setInceptionDt(map.get("INSURANCE_START_DATE")==null?"":map.get("INSURANCE_START_DATE").toString());
					bean.setExpiryDt(map.get("INSURANCE_END_DATE")==null?"":map.get("INSURANCE_END_DATE").toString());
					bean.setFinalPremium(map.get("TOTAL_PREMIUM")==null?"0.0":map.get("TOTAL_PREMIUM").toString());
					bean.setDiscountAmt(Double.parseDouble(map.get("DISCOUNTED_VALUE")==null?"0.0":map.get("DISCOUNTED_VALUE").toString()));
					bean.setSchemeCover(map.get("SCHEME_ID")==null?"":map.get("SCHEME_ID").toString());
					bean.setTravelCover(map.get("OPTION_ID")==null?"":map.get("OPTION_ID").toString());
					bean.setDiscountType(map.get("DISCOUNT_TYPE")==null?"None":map.get("DISCOUNT_TYPE").toString());
					bean.setOptCovers(map.get("OPTIONAL_COVER")==null?"":map.get("OPTIONAL_COVER").toString());
					Map coverMap=getCoveragesName(bean.getSchemeCover(), bean.getTravelCover(),bean.getBranchCode(),bean.getProductId());
					String schemeCoverage=coverMap.get("SCHEME_NAME")==null?"":coverMap.get("SCHEME_NAME").toString();
					String travelCoverage=coverMap.get("OPTION_NAME")==null?"":coverMap.get("OPTION_NAME").toString();
					if(!"None".equalsIgnoreCase(travelCoverage))
					{
						travelCoverage=travelCoverage.trim();
						//travelCoverage=travelCoverage.replaceAll(" ", "_");
						bean.setScheme_Covers(schemeCoverage);
						bean.setTravel_Covers(travelCoverage);
					}
					else
					{
						bean.setScheme_Covers(schemeCoverage);
						bean.setTravel_Covers("None");
					}
				}
				if(StringUtils.isNotBlank(bean.getQuoteNo())){
					sql=getQuery("GET_HOME_POS_DET");
					obj = new Object[]{bean.getQuoteNo()};
					LogManager.info("Query=>"+sql);
					LogManager.info("Obj[0]=>"+bean.getQuoteNo());
					Map homeMap=this.mytemplate.queryForMap(sql,obj);
					if(homeMap!=null&& homeMap.size()>0)
					{	
						LogManager.info("Size=>"+homeMap.size());
						bean.setCustomerId(homeMap.get("CUSTOMER_ID")==null?"":homeMap.get("CUSTOMER_ID").toString());
						bean.setPolicyNo(homeMap.get("POLICY_NO")==null?"":homeMap.get("POLICY_NO").toString());
						bean.setCustomerName(homeMap.get("CUS_NAME")==null?"":homeMap.get("CUS_NAME").toString());
						bean.setEmail(homeMap.get("EMAIL")==null?"":homeMap.get("EMAIL").toString());
						bean.setMobileNo(homeMap.get("MOBILE")==null?"":homeMap.get("MOBILE").toString());
						bean.setReferralYN(homeMap.get("ADMIN_REFERRAL_YN")==null?"":homeMap.get("ADMIN_REFERRAL_YN").toString());
						bean.setAdminRemarks(homeMap.get("ADMIN_REMARKS")==null?"":homeMap.get("ADMIN_REMARKS").toString());
						bean.setModeOfPay(homeMap.get("PAYMENT_MODE")==null?"":homeMap.get("PAYMENT_MODE").toString());
						bean.setFinalPremium(homeMap.get("PREMIUM")==null?"0.0":homeMap.get("PREMIUM").toString());
						bean.setSign(homeMap.get("EXCESS_SIGN")==null?"+":homeMap.get("EXCESS_SIGN").toString());
						bean.setLoadOrDiscPremium(Double.parseDouble(homeMap.get("EXCESS_PREMIUM")==null?"0.0":homeMap.get("EXCESS_PREMIUM").toString()));
						bean.setEndtPremium(Double.parseDouble(homeMap.get("ENDT_PREMIUM")==null?"0.0":homeMap.get("ENDT_PREMIUM").toString())+Double.parseDouble(homeMap.get("POLICY_FEE")==null?"0.0":homeMap.get("POLICY_FEE").toString()));
						bean.setPremiumPaid(Double.parseDouble(homeMap.get("PREMIUM")==null?"0.0":homeMap.get("PREMIUM").toString())-Double.parseDouble(homeMap.get("ENDT_PREMIUM")==null?"0.0":homeMap.get("ENDT_PREMIUM").toString())+("+".equals(bean.getSign())?bean.getLoadOrDiscPremium():bean.getLoadOrDiscPremium()*(-1)));
						bean.setPolicyFee(Double.parseDouble(homeMap.get("POLICY_FEE")==null?"0.0":homeMap.get("POLICY_FEE").toString()));
						bean.setTotalPremium(Double.parseDouble(homeMap.get("OVERALL_PREMIUM")==null?"0.0":homeMap.get("OVERALL_PREMIUM").toString()));
						bean.setAdminRefStatus(homeMap.get("ADMIN_REFERRAL_STATUS")==null?"":homeMap.get("ADMIN_REFERRAL_STATUS").toString());
						bean.setReferralMsg(homeMap.get("REFERRAL_DESCRIPTION")==null?"":homeMap.get("REFERRAL_DESCRIPTION").toString());
						bean.setAmendId(homeMap.get("AMEND_ID")==null?"0":homeMap.get("AMEND_ID").toString());
					}
					sql=getQuery("GET_TRAVEL_COVER_DTLS");
					obj = new Object[]{bean.getApplicationNo()};
					LogManager.info("Query=>"+sql);
					LogManager.info("Obj[0]=>"+bean.getApplicationNo());
					List covList=this.mytemplate.queryForList(sql,obj);
					if(covList!=null && covList.size()>0)
					{ 
						LogManager.info("Size=>"+covList.size());
						List cov=new ArrayList();
						List<Double> premium=new ArrayList<Double>();
						for(int i=0;i<covList.size();i++){
							cov.add(((Map)(covList.get(i))).get("STATUS"));
							premium.add(Double.parseDouble((((Map)(covList.get(i))).get("PREMIUM"))==null?"0.0":(((Map)(covList.get(i))).get("PREMIUM")).toString()));
						}
						bean.setCoverages(cov);
						bean.setCoveragePremium(premium);
					}
				}				
					/*sql=getQuery("TRAVEL_GET_CUSTOMER_DETAILS");
					LogManager.info("Query=>"+sql);
					LogManager.info("Obj[0]=>"+bean.getQuoteNo());
					LogManager.info("Obj[1]"+bean.getProductId());
					Map homeMap=this.mytemplate.queryForMap(sql,new Object[]{bean.getQuoteNo(),bean.getProductId()});
					if(homeMap!=null&& homeMap.size()>0)
					{	
						LogManager.info("Size=>"+homeMap.size());
						bean.setCustomerId(homeMap.get("CUSTOMER_ID")==null?"":homeMap.get("CUSTOMER_ID").toString());
						bean.setTitle(homeMap.get("TITLE")==null?"":homeMap.get("TITLE").toString());
						bean.setCustomerName(homeMap.get("CUS_NAME")==null?"":homeMap.get("CUS_NAME").toString());
						bean.setEmail(homeMap.get("EMAIL")==null?"":homeMap.get("EMAIL").toString());
						bean.setAddress1(homeMap.get("ADDRESS1")==null?"":homeMap.get("ADDRESS1").toString());
						bean.setAddress2(homeMap.get("ADDRESS2")==null?"":homeMap.get("ADDRESS2").toString());
						bean.setFax(homeMap.get("FAX")==null?"":homeMap.get("FAX").toString());
						bean.setMobileNo(homeMap.get("MOBILE")==null?"":homeMap.get("MOBILE").toString());
						bean.setOccupation(homeMap.get("OCCUPATION")==null?"":homeMap.get("OCCUPATION").toString());
						bean.setPoBox(homeMap.get("POBOX")==null?"":homeMap.get("POBOX").toString());
						bean.setCountry(homeMap.get("COUNTRY")==null?"":homeMap.get("COUNTRY").toString());
						bean.setCity(homeMap.get("EMIRATE")==null?"":homeMap.get("EMIRATE").toString());
						bean.setTelephoneNo(homeMap.get("TELEPHONE")==null?"":homeMap.get("TELEPHONE").toString());
						bean.setCusCivilId(homeMap.get("CUSTOMER_SOURCE")==null?"":homeMap.get("CUSTOMER_SOURCE").toString());
						bean.setNationality(homeMap.get("NATIONALITY")==null?"":homeMap.get("NATIONALITY").toString());
						bean.setBrokerCode(homeMap.get("AGENCY_CODE")==null?"":homeMap.get("AGENCY_CODE").toString());
						bean.setExecutive(homeMap.get("AC_EXECUTIVE_ID")==null?"":homeMap.get("AC_EXECUTIVE_ID").toString());
					}*/
					new CustomerDAO().setCustomerDetails(bean);
					
					if(StringUtils.isBlank(bean.getIssuer()))
						bean.setBackDt(new com.maan.common.dao.CommonDAO().getBackDatesAllowed((String)bean.getLoginId(),(String)bean.getUserType(),bean.getProductId(),bean.getBranchCode(),""));
					else
						bean.setLoginId(commonDAO.getSingleInfo("loginId", new String[]{bean.getBrokerCode()}));
					//bean.setCover(getCoverInfo(bean.getProductId(),bean.getSchemeCover(), bean.getTravelCover(),bean.getBranchCode()));
					if(!"admin".equals(bean.getUser())){
						/*sql=getQuery("GET_REFERAL_YN");
						obj=new Object[1];
						obj[0]=bean.getLoginId();
						LogManager.info("Query=>"+sql);
						LogManager.info("Obj[0]=>"+bean.getLoginId());
						bean.setShowReferralYN((String)this.mytemplate.queryForObject(sql, obj,String.class));*/
						bean.setShowReferralYN(new com.maan.common.dao.CommonDAO().getReferralYN(bean.getLoginId()));
					}
			}else
			{
				
					List<Integer>sNo=new ArrayList<Integer>();
					List<String>trList=new ArrayList<String>();
					sNo.add(1);
					trList.add("1");
					bean.setSerialNos(sNo);
					bean.setTravelList(trList);
			}
		}catch(Exception e)
		{
			LogManager.debug(e);
		}
		LogManager.push("getSecondPageDts method() Exit ||");
	}
	public List<Map<String,Object>> getTravelCoverDetails(String applicationNo){
		List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
		try{
			String sql="";
			sql=getQuery("GET_TRAVEL_DTL");
			Object obj[] = new Object[]{applicationNo};
			LogManager.info("Query=>"+sql);
			LogManager.info("Obj[0]=>"+applicationNo);
			list=this.mytemplate.queryForList(sql,obj);
		}catch (Exception e) {
			
		}
		return list;
	}
	public Map getCoveragesName(String schemeCover, String travelCover,String branchCode,String productCode) {
		// TODO Auto-generated method stub
		LogManager.push("getCoveragesName method() Enter ||");
		Map map=new HashMap();
		try{
			if(StringUtils.isNotBlank(schemeCover)&&StringUtils.isNotBlank(travelCover)){
				String sql=getQuery("GET_COVER_NAME_TRAVEL");
				Object obj[] = new Object[]{schemeCover,travelCover,branchCode,productCode,branchCode};
				LogManager.info("Query=>"+sql);
				LogManager.info("Obj[0]=>"+schemeCover);
				LogManager.info("Obj[1]=>"+travelCover);
				LogManager.info("Obj[2]=>"+branchCode);
				LogManager.info("Obj[3]=>"+productCode);
				LogManager.info("Obj[4]=>"+branchCode);
				map=this.mytemplate.queryForMap(sql,obj);
			}
		}catch(Exception e)
		{
			LogManager.debug(e);
		}
		LogManager.push("getCoveragesName method() Exit || Map Size=>"+map.size());
		return map;
	}
	 public List getTravelCoverages(final String schemeId,final String optionId,final String branchCode,String productCode)
	 {
		 LogManager.push("getTravelCoverages method() Enter ||");
		 List list=null;
		 try{
			String sql=getQuery("GET_TRAVELCOVERAGES");
			Object obj[]=new Object[7];
			obj[0]=schemeId;
			obj[1]=optionId;
			obj[2]=productCode;
			obj[3]=productCode;
			obj[4]=branchCode;
			obj[5]=branchCode;
			obj[6]=branchCode;
			LogManager.info("Sql=>"+sql);
			LogManager.info("Sql=>"+StringUtils.join(obj,","));
			list =this.mytemplate.queryForList(sql,obj);
		 }catch(Exception e)
		 {
				LogManager.debug(e);
		 }	
		LogManager.push("getTravelCoverages method() Exit || List Size=>"+list.size());
		return list;
	 }

	 public String updReferralStatus(String actionType, String referralRemarks, String quoteNo, String referralYN) {
		 String result="SUCCESS";
		 try {
			 String sql=getQuery("UPD_REF_STATUS");
			 Object[] obj = new Object[5];
			 obj[0]=(!"getSave".equalsIgnoreCase(actionType)?"Referal":"");
			 obj[1] = referralRemarks;
			 obj[2]=(!"getSave".equalsIgnoreCase(actionType)?referralYN:"");
			 obj[3]=(!"getSave".equalsIgnoreCase(actionType)?"Admin Referral":"");
			 obj[4] = quoteNo;
			 LogManager.info("Query=>"+sql);
			 LogManager.info("obj[] ==> "+StringUtils.join(obj, ","));
			 int res=this.mytemplate.update(sql,obj);
			 LogManager.info("Result=>"+res);
		 }catch(Exception e) {
			 LogManager.debug(e);
			 result="FAILED";
		 }
		 return result;
	 }

	public String getGeratePolicy(TravelBean bean) {
		// TODO Auto-generated method stub
		LogManager.info("getGeratePolicy - Enter");
		String sql="";
		Object[] obj=new Object[0];
		int res=0;
		String result="SUCCESS";
		try
		{
			if("Y".equalsIgnoreCase(bean.getReferralYN())){
				sql=getQuery("UPD_REF_STATUS");
				obj=new Object[5];
				obj[0]=(!"getSave".equalsIgnoreCase(bean.getActionType())?"Referal":"");
				obj[1]=bean.getReferralComments();
				obj[2]=(!"getSave".equalsIgnoreCase(bean.getActionType())?bean.getReferralYN():"");
				obj[3]=(!"getSave".equalsIgnoreCase(bean.getActionType())?"Admin Referral":"");
				obj[4]=bean.getQuoteNo();
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
				obj[3]=bean.getModeOfPayment();
				obj[4]="";
				obj[5]=bean.getQuoteNo();
				LogManager.info("Query=>"+sql);
				LogManager.info("obj[] ==> "+StringUtils.join(obj, ","));
				res=this.mytemplate.update(sql,obj);
				LogManager.info("Result=>"+res);
				/*if("Y".equalsIgnoreCase(bean.getGeneratePolicyYN())&&!"getSave".equalsIgnoreCase(bean.getActionType())){
					bean.setMerchant_reference(
						new PaymentDAO().updatePaymentDetails(
							bean.getModeOfPayment(), bean.getChequeNo(), bean.getChequeDate(), bean.getChequeAmount(),
							Double.toString(bean.getTotalPremium()),bean.getBankName(), bean.getMicrCode(), bean.getCashDepositBank(),
							bean.getCashAmount(), bean.getCashChellanNo(), bean.getCashInstrumentDate(), bean.getApplicationNo(), bean.getQuoteNo(),
							bean.getProductId(), Double.toString(bean.getTotalPremium()), bean.getMerchant_reference(), bean.getEmail(), bean.getCustomerName(),
							bean.getBranchCode()
						)
					);
					result = commonDAO.updateHomeQuoteStatus(bean.getApplicationNo(),bean.getMerchant_reference());
					commonDAO.closeTrnDateCalculation(bean.getQuoteNo(),bean.getBranchCode(),"HTOS");
				}*/
			}
		}catch(Exception e)
		{
			LogManager.debug(e);
			result="FAILED";
		}
		LogManager.popRemove();
		LogManager.info("getGeratePolicy - Exit");
		return result;
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
	public void getBackShowQuote(TravelBean bean) {
		// TODO Auto-generated method stub
		getSecondPageDts(bean);
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
			LogManager.info("Obj[4]=>"+productId);
			
			list=this.mytemplate.queryForList(sql,new String[]{productId,schemecover,travelcover,branchCode,productId});
			LogManager.info("List Size=>"+list.size());
		}catch(Exception e){
			LogManager.debug(e);
		}
		LogManager.info("getCoverInfo - Exit ");
		return 	list;
	}
	public String getCancelReissue(TravelBean bean)
	{
		LogManager.info("getCancelReissue - Enter"+bean.getCancelPolicy()+bean.getReissuePolicy());
		String result="";
		Object[] obj=new Object[0];
		try{
			if("YES".equalsIgnoreCase(bean.getCancelPolicy())&&"NO".equalsIgnoreCase(bean.getReissuePolicy())){
			LogManager.info("QuoteNo:"+bean.getQuoteNo());
			String sql=getQuery("GET_QUOTE_COUNT");
			obj=new Object[1];
			obj[0]=bean.getQuoteNo();
			int count=this.mytemplate.queryForInt(sql,obj);	
				if(count>0)
				{
					LogManager.info("Enter into CANCEL POLICY::");
					String sql2=getQuery("UPDATE_QUOTE_C");
					obj=new Object[4];
					obj[0]="C";
					obj[1]=bean.getReason();
					obj[2]=bean.getLoginId();
					obj[3]=bean.getPolicyNo();
					this.mytemplate.update(sql2,obj);   
					result="cancel";
				}else{
					LogManager.info("Enter into NO CANCEL::");
					result="nocancel";
				}
			}else if("NO".equalsIgnoreCase(bean.getCancelPolicy())&&"NO".equalsIgnoreCase(bean.getReissuePolicy())){
				LogManager.info("Enter into NOTHING::");
				result="nothing";
			}
			else{
				CopyQuoteService csService = new CopyQuoteService();
				bean.setMap(csService.travelcopyQuote(bean.getLoginId(), bean.getQuoteNo(), bean.getProductId(), bean.getBranchCode(),"",null,"Normal"));
				/*Map<String, String> map=new HashMap<String, String>();
				CallableStatement cstmt = null;
				Connection con = null;
				try {
					LogManager.info("Enter into TRAVEL_COPYQUOTE - Enter");
					con = DBConnection.getInstance().getDBConnection();
					cstmt = con.prepareCall("{CALL TRAVEL_COPYQUOTE_ENDT(?,?,?,?,?,?,?,?)}");
					cstmt.setString(1, "R");
					cstmt.setString(2, bean.getQuoteNo());
					cstmt.setString(3, "");
					cstmt.setString(4, bean.getLoginId());
					cstmt.setString(5, bean.getBranchCode());
					cstmt.setString(6, bean.getProductId());
					cstmt.registerOutParameter(6, java.sql.Types.VARCHAR);
					cstmt.registerOutParameter(7, java.sql.Types.VARCHAR);
					cstmt.registerOutParameter(8, java.sql.Types.VARCHAR);
					cstmt.execute();
					map.put("CUSTOMER_NAME", cstmt.getString(6));
					map.put("ERROR", cstmt.getString(7));
					map.put("QUOTE_NO", cstmt.getString(8));
					bean.setMap(map);
				}catch(Exception e){
					e.printStackTrace();
				}finally {
		          try {
		                if (cstmt != null)
			             cstmt.close();
		                 } catch (Exception e) { LogManager.debug(e);} 
		           try {		
		               if (con != null && !con.isClosed())
		                 con.close();
			           } catch (Exception e) { LogManager.debug(e); }
		              }
				LogManager.info("Enter into TRAVEL_COPYQUOTE - Exit || STATUS: "+map.get("STATUS")+" || ERROR: "+map.get("ERROR")+" || QUOTE_NO: "+map.get("QUOTE_NO"));
				*/
			    result="reissue";
			}
		}catch(Exception e){
			LogManager.debug(e);
		}
		LogManager.info("getCancelReissue - Exit ");
		return result;
	}
	public String getAdminReferralUpdation(TravelBean bean) {
		// TODO Auto-generated method stub
		LogManager.info("getAdminReferralUpdation - Enter");
		String sql="",result="";
		Object[] obj=new Object[0];
		int res=0;
		List tages = new ArrayList<Integer>();
		try
		{			
			
			for(int i=0;i<bean.getTravelList().size();i++){
				sql=getQuery("UPD_TRAVEL_DTL_ADMIN");
				obj=new Object[14];
				obj[0]=bean.getTravelPremium().get(i);
				obj[1]=bean.getTravelNames().get(i);
				obj[2]=bean.getDobs().get(i);
				obj[3]=bean.getRelations().get(i);
				obj[4]=bean.getGenders().get(i);
				obj[5]=bean.getNationalitys().get(i);
				obj[6]=new com.maan.common.dao.CommonDAO().getCalculatedAge(bean.getDobs().get(i));
				obj[7]=obj[6];
				obj[8]=obj[6];
				obj[9]=obj[6];
				obj[10] = bean.getPassportNo().get(i);
				obj[11] = bean.getPassportExpDate().get(i);
				obj[12]=bean.getApplicationNo();
				obj[13]=bean.getSerialNos().get(i);
				LogManager.info("Query=>>"+sql);
				LogManager.info("Query=>>"+StringUtils.join(obj,","));
				res=this.mytemplate.update(sql,obj);              
				LogManager.info("Result=>>"+res);
			}
			List coverages =getTravelCoverages(bean.getSchemeCover(),bean.getTravelCover(), bean.getBranchCode(),bean.getProductId());
	        if(coverages != null &&coverages.size()>0){
				for(int i=0;i<coverages.size();i++){
	        		Map map1=(Map)coverages.get(i);
	        		sql=getQuery("UPD_TRAVEL_COVER_DTLS_ADMIN");
					obj=new Object[3];
					obj[0]=bean.getCoveragePremium().get(i);
					obj[1]=bean.getApplicationNo();
					obj[2]=map1.get("COVERAGES_ID");
					LogManager.info("Query=>>"+sql);
					LogManager.info("Query=>>"+StringUtils.join(obj,","));
					removeNull(obj);
					res=this.mytemplate.update(sql,obj);              
					LogManager.info("Result=>>"+res);
	    		}
	        }
			sql=getQuery("UPD_EXCESS_PREMIUM");
			obj=new Object[3];
			obj[0]=bean.getDiscountAmt();
			obj[1]=bean.getFinalPremium();
			obj[2]=bean.getQuoteNo();
			LogManager.info("Query=>>"+sql);
			LogManager.info("Query=>>"+StringUtils.join(obj,","));
			res=this.mytemplate.update(sql,obj);              
			LogManager.info("Result=>>"+res);
			obj=new Object[13];
			obj[1]=bean.getAdminRemarks();
			obj[2]=bean.getLoadOrDiscPremium();
			obj[4]=bean.getSign();
			obj[5]=bean.getTotalPremium()+"";
			obj[6]=new com.maan.common.dao.CommonDAO().getCommision(Double.parseDouble(bean.getFinalPremium()), bean.getApplicationNo());
			obj[7]=bean.getLoginId();
			obj[8]=bean.getFinalPremium();
			obj[9]=bean.getDiscountAmt();
			obj[10]=(bean.getEndtPremium()-bean.getPolicyFee());
			obj[11]=Double.parseDouble(new com.maan.common.dao.CommonDAO().getCommision((bean.getEndtPremium()-bean.getPolicyFee()), bean.getApplicationNo()));
			obj[12]=bean.getQuoteNo();
			if ("Y".equalsIgnoreCase(bean.getAdminRefStatus())){
				obj[0]="Admin";
				if(!"0".equals(bean.getAmendId()))
					obj[3]="E";
				else
					obj[3]="Y";
				bean.setReferralMsg(" Accepted.");
			}else if("N".equalsIgnoreCase(bean.getAdminRefStatus())){
				obj[0]="Referal";
				obj[3]="R";
				bean.setReferralMsg(" Rejected.");
			}else if ("A".equalsIgnoreCase(bean.getAdminRefStatus())){
				obj[0]="Referal";
				if(!"0".equals(bean.getAmendId()))
					obj[3]="E";
				else
					obj[3]="Y";
				bean.setReferralMsg(" Moved Pending.");
			}
			sql=getQuery("UPD_ADMIN_REFSTATUS");
			LogManager.info("Query=>>"+sql);
			LogManager.info("Query=>>"+StringUtils.join(obj,","));
			removeNull(obj);
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
	public String getUpdateCalculatedPremium(TravelBean bean) {
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
				/*sql=getQuery("GET_MINIUM_PREMIUM");
				obj=new Object[2];
				obj[0]=bean.getApplicationNo();
				obj[1]=bean.getApplicationNo();
				LogManager.info("Query=>"+sql);
				LogManager.info("Obj[0-2]=>"+StringUtils.join(obj,","));
				value=(String)this.mytemplate.queryForObject(sql,obj,String.class);
				LogManager.info("Minimum Premium=>"+value);*/
				minpremium=Double.parseDouble(new com.maan.common.dao.CommonDAO().getMinimumPremium(bean.getApplicationNo()));
				if(Double.parseDouble(bean.getFinalPremium())<minpremium){
					bean.setFinalPremium(Double.toString(minpremium));
				}
			}
			
				/*obj=new Object[2];
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
				}*/
				policyFee=new com.maan.common.dao.CommonDAO().getPolicyFee(bean.getApplicationNo(),bean.getBranchCode());
				sql=getQuery("UPD_TRAVEL_HEADER_PREMIUM");
				obj=new Object[2];
				obj[0]=bean.getFinalPremium();
				obj[1]=bean.getApplicationNo();
				LogManager.info("Query=>"+sql);
				LogManager.info("Obj[0-1]=>"+StringUtils.join(obj,","));
				res=this.mytemplate.update(sql,obj);
				LogManager.info("Result=>"+res);
				bean.setTotalPremium(Double.parseDouble(bean.getFinalPremium())+policyFee);
				sql=getQuery("UPD_HOME_POS_PREMIUM");
				obj=new Object[5];
				obj[0]=bean.getFinalPremium();
				obj[1]=bean.getTotalPremium();
				obj[2]=policyFee;
				obj[3]=new com.maan.common.dao.CommonDAO().getCommision(Double.parseDouble(bean.getFinalPremium()),bean.getApplicationNo());//commission;
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
	public String getCalculatePremium(TravelBean bean,String type){
		LogManager.info("getCalculatePremium method() Enter ||");
		CallableStatement cstmt=null;
		Connection con=null;
		try{
			String sql=getQuery("CAL_TRAVEL_PREMIUM_CALC");
			LogManager.info("IN OUT SP=>"+sql);
			LogManager.info(" IN Args[0]>"+bean.getApplicationNo()+" IN Args[1]>"+bean.getProductId()+" IN Args[2]>"+bean.getBranchCode()+" IN Args[3]>"+bean.getUser()+" IN Args[4]>"+type);
			con = DBConnection.getInstance().getDBConnection();
			cstmt =  con.prepareCall(sql);
			cstmt.setString(1, bean.getApplicationNo());
			cstmt.setString(2, bean.getProductId());
			cstmt.setString(3, bean.getBranchCode());
			cstmt.setString(4, bean.getUser());
			cstmt.setString(5, type);
			cstmt.registerOutParameter(6, java.sql.Types.DOUBLE);
			cstmt.execute();
			LogManager.info("SP OUT SP=>"+cstmt.getDouble(6));
			bean.setTotalPremium(cstmt.getDouble(6));
			//getUpdateCalculatedPremium(bean);
		}catch(Exception e)
		{
			LogManager.debug(e);
		}finally {
	          try {
	                if (cstmt != null)
		             cstmt.close();
	                 } catch (Exception e) { LogManager.debug(e);} 
	           try {		
	               if (con != null && !con.isClosed())
	            	   con.close();
	           		} catch (Exception e) { LogManager.debug(e); }
	              }
		LogManager.info("getCalculatePremium method() Exit Premium=>"+bean.getTotalPremium());
		return "SUCCESS";
	}
	public String getDeleteTraveller(TravelBean bean) {
		// TODO Auto-generated method stub
		LogManager.info("getDeleteTraveller method() Enter");
		try{
			String sql=getQuery("DEL_TRAVEL_DTL");
			Object[] obj=new Object[2];
			obj[0]=bean.getApplicationNo();
			obj[1]=bean.getSerialNo();
			LogManager.info("sql=>"+sql);
			LogManager.info("obj[]=>"+StringUtils.join(obj,","));
			int res=this.mytemplate.update(sql,obj);
			LogManager.info("Delete Result=>"+res);
		}catch(Exception e)
		{
			LogManager.debug(e);
		}
		LogManager.info("getDeleteTraveller method() Exit");
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
	
	public void updateCorrections(TravelBean bean)
	{
		String sql;
		Object[] obj;
		for (int i = 0; i < (bean.getSerialNos().size()); i++)
		{
			sql=getQuery("GET_TRAVEL_DTL_CNT");
			LogManager.info("Query=>"+sql);
			LogManager.info("Obj[]=>"+bean.getApplicationNo()+","+(bean.getSerialNos().get(i)));
			int count=this.mytemplate.queryForInt(sql,new Object[]{bean.getApplicationNo(),(bean.getSerialNos().get(i))});
			LogManager.info("Result=>"+count);
			sql=getQuery("travel.update.correction");
			LogManager.info("Query=>"+sql);
			obj=new Object[8];
			obj[0]=bean.getTravelNames().get(i);
			obj[1]=("-1".equals(bean.getGenders().get(i))?"":bean.getGenders().get(i));
			obj[2]=bean.getNationalitys().get(i);
			obj[3] = bean.getTravelLastNames().get(i);
			obj[4] = bean.getPassportNo().get(i);
			obj[5] = bean.getPassportExpDate().get(i);
			obj[6]=bean.getApplicationNo();
			obj[7]=bean.getSerialNos().get(i);
			LogManager.info("obj[] ==> "+StringUtils.join(obj, ","));
			this.mytemplate.update(sql,obj);
		}
	}
	
	public int getEffectiveDate(TravelBean bean)
	{
		String sql;
		int count=0;
		sql=getQuery("TRAVEL_CORRECTION_CHECK");
		LogManager.info("Query => "+sql);
		LogManager.info("Arguments => "+bean.getQuoteNo());
		try{
			count=this.mytemplate.queryForInt(sql,new Object[]{bean.getQuoteNo()});
		}
		catch(Exception e){
			LogManager.info("Exception=>"+e);
		}
		return count;
	}
	public List <Object> getDetailsView(TravelBean bean)
	{
		String sql=getQuery("TRAVEL_GET_PASSENGER_DETAILS");
		List<Object> trList=null;
		try{
			LogManager.info("Query=>"+sql);
			LogManager.info("Obj[0]=>"+bean.getBranchCode());
			LogManager.info("Obj[1]"+bean.getQuoteNo());
			trList=this.mytemplate.queryForList(sql,new Object[]{bean.getBranchCode(),bean.getQuoteNo()});			
		}
		catch(Exception e){
			LogManager.info("Exception=>"+e);
		}
		return trList;
	}
	public List <Object> getPolicyView(TravelBean bean)
	{
		String sql=getQuery("TRAVEL_GET_CUSTOMER_POLICY_DETAILS");
		LogManager.info("Query => "+sql);
		LogManager.info("Arguments => "+bean.getQuoteNo());
		List<Object> trPolicyList=null;
		try{
			trPolicyList=this.mytemplate.queryForList(sql,new Object[]{bean.getQuoteNo()});
		}
		catch(Exception e){
			LogManager.info("Exception=>"+e);
		}
		return trPolicyList;
	}
	@SuppressWarnings("unchecked")
	public List<CoverageBean> getCoveragesNames(final String applicationNo,final String travelCover){
		LogManager.info("getCoveragesNames method() Enter");
    	List<CoverageBean> result = new ArrayList<CoverageBean>();
    	final String sql = getQuery("GET_COVERAGES_NAMES");
    	LogManager.info("sql=>"+sql);
    	final Object[] obj = new Object[]{applicationNo, travelCover};
    	LogManager.info("obj[]=>"+StringUtils.join(obj,","));
    	try {
    		if(applicationNo!=null){ 
    		result = this.mytemplate.query(sql, obj, new RowMapper() {
    			public Object mapRow(final ResultSet rs, final int idVal) throws SQLException {
    				final CoverageBean covBean = new CoverageBean();
    				covBean.setOptionId(travelCover);
    				covBean.setSchemeId(rs.getString("SCHEME_ID"));
    				covBean.setSchemeName(rs.getString("SCHEME_NAME"));
    				covBean.setPremium(Double.parseDouble(rs.getString("PREMIUM")==null?"0.0":rs.getString("PREMIUM")));
    				covBean.setReferalYN(rs.getString("REFYN"));
    				return covBean;
    			}});
    		}
			
        } catch (Exception e) {
        	LogManager.debug(e);
        }
        LogManager.info("getCoveragesNames - Exit");
    	return result;
    }
	public  List<CoverageBean> getCoverages(String applicaitonNo,String schemeId,String optionId) {
		// TODO Auto-generated method stub
		List<CoverageBean> result = new ArrayList<CoverageBean>();
		LogManager.info("getCoverages method() Enter");
		try {
			String sql=getQuery("GET_COVERAGES_DTLS");
			Object[] obj=new Object[3];
			obj[0]=applicaitonNo;
			obj[1]=schemeId;
			obj[2]=optionId;
			LogManager.info("sql=>"+sql);
			LogManager.info("obj[]=>"+StringUtils.join(obj,","));
    		result = this.mytemplate.query(sql, obj, new RowMapper() {
    			public Object mapRow(final ResultSet rs, final int idVal) throws SQLException {
    				final CoverageBean covBean = new CoverageBean();
    				covBean.setGroupId(rs.getString("GROUP_ID"));
    				covBean.setCoverId(rs.getString("Y_ID"));
    				covBean.setCoverName(rs.getString("Y_DATA_NAME"));
    				covBean.setReferalYN(rs.getString("REFYN"));
    				covBean.setPremium(Double.parseDouble(rs.getString("PREMIUM")==null?"0.0":rs.getString("PREMIUM")));
    				covBean.setIsSelected(rs.getString("IS_SELECTED"));
    				covBean.setIsAddon(rs.getString("IS_ADDON"));
    				covBean.setIsDeletable(rs.getString("DELETABLE"));
    				return covBean;
    			}});
        } catch (Exception e) {
        	LogManager.debug(e);
        }
		LogManager.info("getCoverages method() Exit");
		return result;
	}

	public String getUpdateOptionCovers(TravelBean bean) {
		LogManager.info("getUpdateOptionCovers method() Enter");
		String result="SUCCESS";
		try{
			String sql=getQuery("UPD_TRAVEL_HEADER_OPTCOVER");
			Object[] obj=new Object[3];
			obj[0]=bean.getSchemeCover();
			obj[1]=StringUtils.join(bean.getOptionalCovers(),"~");
			obj[2]=bean.getApplicationNo();
			LogManager.info("sql=>"+sql);
			LogManager.info("obj[]=>"+StringUtils.join(obj,","));
			int res=this.mytemplate.update(sql,obj);
			LogManager.info("Result=>"+res);
		}catch(Exception e)
		{
			result="FAILED";
			LogManager.debug(e);
		}
		LogManager.info("getUpdateOptionCovers method() Exit");
		return result;
	}
	/*private String dateFormat(String name){
		String arr[]=name.split("/");
		return arr[1]+"/"+arr[0]+"/"+arr[2];
	}*/
	/*public void insertPersonal(TravelBean bean) {

		LogManager.info("getUpdate - Enter ");		
		try
		{
			if(StringUtils.isBlank(bean.getCustomerId()))
			{
				bean.setCustomerId(new CommonDAO().getPolicyNo(bean.getBranchCode(), bean.getProductId(),"CUSTOMER_TYPE_ID"));
				String sql=getQuery("INS_PERSONAL_INFO");
				LogManager.info("Query=>"+sql);				
				Object[] obj=new Object[4];
				obj[0]=bean.getCustomerId();			
				obj[1]=bean.getFullName();
				obj[2]=bean.getEmailId();				
				obj[3]=bean.getLoginId();						
				this.mytemplate.update(sql,obj);				
			}
			else
			{
				String sql=getQuery("UPD_CUSTOMER_INFO");
				LogManager.info("Query=>"+sql);				
				Object[] obj=new Object[3];
				obj[0]=bean.getFullName();
				obj[1]=bean.getEmailId();		
				obj[2]=bean.getCustomerId();								
				this.mytemplate.update(sql,obj);
				
			}
		}catch(Exception e)
		{
			LogManager.debug(e);
		}
		LogManager.popRemove();
		LogManager.info("getUpdate - Exit");		
	}*/
	/*public void updatePersonalInfo(TravelBean bean) {
	LogManager.info("getUpdate - Enter ");		
	try
	{
		
			String sql=getQuery("UPDATE_PERSONAL_INFO");
			LogManager.info("Query=>"+sql);				
			Object[] obj=new Object[8];
			obj[0]=bean.getAddress();			
			obj[1]=bean.getPoBox();
			obj[2]=bean.getCity();			
			obj[3]=bean.getTelephoneNo();	
			obj[4]=bean.getMobileNo();	
			obj[5]=bean.getOccupation();	
			obj[6]=bean.getCusCivilId();	
			obj[7]=bean.getCustomerId();				
			this.mytemplate.update(sql,obj);				
		
	}catch(Exception e)
	{
		LogManager.debug(e);
	}
	LogManager.popRemove();
	LogManager.info("getUpdate - Exit");		
}*/
	/*public String insertOrUpdateCustomerInfo(TravelBean bean,String issuer) 
	{
		LogManager.info("insertOrUpdateCustomerInfo - Enter ");
		String customerId=bean.getCustomerId();
		String[] args=null;
		int count=0;
		try{
			String sql=getQuery(DBConstants.MARINE_CUSTOMER_COUNT);
			if(StringUtils.isNotEmpty(customerId)){
				count=this.mytemplate.queryForInt(sql, new String[]{bean.getCustomerId()});
			}
			if (count<=0)
			{
				//customerId = getValue("GET_CUST_ID_MAX");
				customerId = new CommonDAO().getSequenceNo("CUSTOMER_ID",bean.getProductId(),bean.getBranchCode(),"","");
				args = new String[18];
				args[0] = customerId;
				args[1] = "1";
				args[2] = bean.getTitle();
				args[3] = bean.getCustomerName();
				args[4] = "";
				args[5] = "0";
				args[6] = bean.getMobileNo();
				args[7] = bean.getEmailId();
				args[8] = StringUtils.isEmpty(bean.getAddress1())?"":bean.getAddress1();
				args[9] = StringUtils.isEmpty(bean.getAddress2())?"":bean.getAddress2();
				args[10] = StringUtils.isEmpty(bean.getPoBox())?"":bean.getPoBox();
				args[11] = StringUtils.isEmpty(bean.getCity())?"":bean.getCity();
				args[12] = "Y";
				args[13] = bean.getLoginId();
				args[14] = "";
				args[15] = "";
				args[16] = "";
				args[17] = bean.getCustomerName();
				sql=getQuery(DBConstants.MARINE_CUSTOMER_INSERT);
				LogManager.info("Customer insert args=>"+StringUtils.join(args,","));
				this.mytemplate.update(sql, args);
				bean.setCustomerId(customerId);
			}
			else 
			{
				args = new String[16];
				args[0] = "1";
				args[1] = bean.getTitle();
				args[2] = bean.getCustomerName();
				args[3] = "";
				args[4] = bean.getMobileNo();
				args[5] = bean.getEmailId();
				args[6] = StringUtils.isEmpty(bean.getAddress1())?"":bean.getAddress1();
				args[7] = StringUtils.isEmpty(bean.getAddress2())?"":bean.getAddress2();
				args[8] = StringUtils.isEmpty(bean.getPoBox())?"":bean.getPoBox();
				args[9] = StringUtils.isEmpty(bean.getCity())?"":bean.getCity();
				args[10] = "Y";
				args[11] = "";
				args[12] = "";
				args[13] = "";
				args[14] = bean.getCustomerName();
				args[15] = bean.getCustomerId();
				sql=getQuery(DBConstants.MARINE_CUSTOMER_UPDATE);
				LogManager.info("Customer update args=>"+StringUtils.join(args,","));
				this.mytemplate.update(sql, args);
			}
			if(StringUtils.isNotEmpty(issuer))
			{
				sql=getQuery("update.travel.broker");
				this.mytemplate.update(sql, new Object[]{bean.getBrokerCode(),bean.getExecutive(),bean.getCustomerId()});
			}
		}catch(Exception e){
			LogManager.debug("Exception @{"+e+"}");
			e.printStackTrace();
		}
		LogManager.info("insertOrUpdateCustomerInfo - Exit || Result: "+customerId);
		return customerId;
	}*/
	public String getValue(String option)
	{
		LogManager.info("getValue - Enter || "+option);
		String result="";					
		try{
			String sql=getQuery(option);
			result=(String)this.mytemplate.queryForObject(sql,String.class);			
		}
		catch (Exception e) {			
			LogManager.debug("EXCEPTION @ { " + e + " }");
		}		
		LogManager.info("getValue() - Exit || Result: "+result );
		LogManager.popRemove();		
		return result;
	}
	public int getPasswordExpiryValidationMonth() {
		int result=0;
		try{
			String sql = getQuery("GET_MONTH_COUNT_PASSPORTEXPDATE");
			LogManager.info("Query =>"+sql);
			String month = this.mytemplate.queryForObject(sql,String.class).toString();
			result = Integer.parseInt(month==null?"0":month);
		}catch (Exception e) {
			LogManager.debug("Exception Occured @ getPasswordExpiryValidationMonth  "+e);
		}
		return result;
	}

	public String getEmailCount(TravelBean bean) {
		String result="";
		try{
			String query=getQuery("GET_USER_EMAIL_COUNT");
			LogManager.info("Query => "+query);
			Object[] args;
			args=new Object[]{bean.getApplicationNo()};
			LogManager.info("Arguments => "+StringUtils.join(args,","));
			result = (String)this.mytemplate.queryForObject(query,args,String.class);
			
		}catch(Exception e){
			LogManager.info("Exception Occured @ getemailCount"+e);
			e.printStackTrace();
		}
		return result;
	}

	public void setCustomerDetails(TravelBean bean) {
		String query = "";
		query = getQuery("GET_CUSTOMER_DETAILS_lOGIN_ID");
		Object args[]=new Object[]{bean.getLoginId(),"2"};
		Map<String,Object> res = this.mytemplate.queryForMap(query, args);
		if(res!=null && res.size() > 0){
			bean.setCustomerId(res.get("CUSTOMER_ID")==null?"":res.get("CUSTOMER_ID").toString());
			bean.setTitle(res.get("TITLE")==null?"":res.get("TITLE").toString());
			bean.setCustomerName(res.get("NAME")==null?"":res.get("NAME").toString());
			bean.setMobileNo(res.get("MOBILE")==null?"":res.get("MOBILE").toString());
			bean.setEmail(res.get("EMAIL")==null?"":res.get("EMAIL").toString());
			bean.setAddress1(res.get("ADDRESS1")==null?"":res.get("ADDRESS1").toString());
			bean.setAddress2(res.get("ADDRESS2")==null?"":res.get("ADDRESS2").toString());
			bean.setPoBox(res.get("POBOX")==null?"":res.get("POBOX").toString());
			bean.setCity(res.get("EMIRATE")==null?"":res.get("EMIRATE").toString());
			bean.setCity(StringUtils.isBlank(bean.getCity())?ResourceBundleUtil.findDefaultText("MOTOR_DEFAULT_CITY"):bean.getCity());
			bean.setCustCoreCode(res.get("MISSIPPI_CUSTOMER_CODE")==null?"":res.get("MISSIPPI_CUSTOMER_CODE").toString());
			bean.setCoreAppCode(res.get("MISSIPPI_CUSTOMER_CODE")==null?"":res.get("MISSIPPI_CUSTOMER_CODE").toString());
			bean.setClientCustomerId(res.get("CLIENT_CUSTOMER_ID")==null?"":res.get("CLIENT_CUSTOMER_ID").toString());
			bean.setCustArNo(res.get("CUST_AR_NO")==null?"":res.get("CUST_AR_NO").toString());
			bean.setCustLastName(res.get("LAST_NAME")==null?"":res.get("LAST_NAME").toString());
			bean.setCustNameArabic(res.get("CUST_NAME_ARABIC")==null?"":res.get("CUST_NAME_ARABIC").toString());
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
			bean.setCustomerType(res.get("CUSTOMER_TYPE")==null?"":res.get("CUSTOMER_TYPE").toString());
			bean.setCompanyRegNo(res.get("COMPANY_REG_NO")==null?"":res.get("COMPANY_REG_NO").toString());
			bean.setGender(res.get("GENDER")==null?"":res.get("GENDER").toString());
			bean.setOccupation(res.get("OCCUPATION")==null?"":res.get("OCCUPATION").toString());
			bean.setBrokerCode(res.get("BROKER_CODE")==null?"":res.get("BROKER_CODE").toString());
			bean.setExecutive(res.get("AC_EXECUTIVE_ID")==null?"":res.get("AC_EXECUTIVE_ID").toString());
		}
	}
}

