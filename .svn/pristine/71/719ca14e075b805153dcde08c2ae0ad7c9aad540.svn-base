package com.maan.adminnew.AdminMgt;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.ListUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import com.maan.common.LogManager;
import com.maan.common.MyJdbcTemplate;
import com.maan.common.password.passwordEnc;

public class AdminMgtDAO extends MyJdbcTemplate {
	String query="";
	/*public List <Object> getMenuList(final AdminMgtBean bean, String branchCode){
		List <Object> menuList=null;
		try{

			Object[] obj=new Object[]{branchCode};
			if("".equals(bean.getSearchBy()) || bean.getSearchBy()==null || StringUtils.isNotBlank(bean.getUname()))
				query=getQuery("GET_MENU_LIST")+" order by A.DETAIL_NAME";
			else if((!"".equals(bean.getSearchBy())) && (!"".equals(bean.getSearchValue())) )
				query=getQuery("GET_MENU_LIST")+" and lower("+bean.getSearchBy()+") like '%'||lower('"+bean.getSearchValue()+"')||'%' order by A.DETAIL_NAME";
			LogManager.info("Query===>" + query);
			LogManager.info("Args===> " + StringUtils.join(obj,","));
			menuList=this.mytemplate.queryForList(query,obj);
		}catch (Exception e) {
			LogManager.debug("EXCEPTION @ { " + e + " }");
		}
    	return menuList;
	}*/
	
	public List <Object> getMenuList(final AdminMgtBean bean, String branchCode){
		List <Object> menuList=null;
		try{

			Object[] obj=new Object[]{branchCode};
			if("newadmin".equalsIgnoreCase(bean.getReqFrom())){
				query=getQuery("GET_MENU_LIST")+" and status='Y'";
			}
			else if("".equals(bean.getSearchBy()) || bean.getSearchBy()==null || StringUtils.isNotBlank(bean.getUname()))
				query=getQuery("GET_MENU_LIST")+" order by A.DETAIL_NAME";
			else if((!"".equals(bean.getSearchBy())) && (!"".equals(bean.getSearchValue())) )
				query=getQuery("GET_MENU_LIST")+" and lower("+bean.getSearchBy()+") like '%'||lower('"+bean.getSearchValue()+"')||'%' order by A.DETAIL_NAME";
			LogManager.info("Query===>" + query);
			LogManager.info("Args===> " + StringUtils.join(obj,","));
			menuList=this.mytemplate.queryForList(query,obj);
		}catch (Exception e) {
			LogManager.debug("EXCEPTION @ { " + e + " }");
		}
		return menuList;
	}
	
	public List<Object>UtypeList(String branchCode,String appId){
		List <Object> utypeList=null;;
		try{
			query=getQuery("GET_UTYPE_LIST");
			LogManager.info("Query===>" + query);
			Object[] obj=new Object[]{branchCode, "2".equalsIgnoreCase(appId)?"Marine":appId};
			for(Object k:obj)
				LogManager.info("Args===>" + k);
			utypeList=this.mytemplate.queryForList(query,obj);
		}catch (Exception e) {
			LogManager.debug("EXCEPTION @ { " + e + " }");
		}
    	return utypeList;
	}
	
	public List<Object>adminList(final AdminMgtBean bean, String branchCode,String appId){
		List <Object> adminList=null;;
		try{
			if("".equals(bean.getSearchBy()) || bean.getSearchBy()==null)
				query=getQuery("GET_ADMIN_LIST");
			else if((!"".equals(bean.getSearchBy())) && (!"".equals(bean.getSearchValue())))
				query=getQuery("GET_ADMIN_LIST")+" and lower("+bean.getSearchBy()+") like '%'||lower('"+bean.getSearchValue()+"')||'%'";
			LogManager.info("Query===>" + query);
			Object[] obj=new Object[]{branchCode};
			for(Object k:obj)
				LogManager.info("Args===>" + k);
			adminList=this.mytemplate.queryForList(query,obj);
		}catch (Exception e) {
			LogManager.debug("EXCEPTION @ { " + e + " }");
		}
    	return adminList;
	}
	
	public void getAdminInfo(final AdminMgtBean bean,String branchCode, String appId){
		List <Object> adminList=null;;
		try{
			query=getQuery("GET_ADMIN_LIST");
			query+=" and a.login_id=?";
			LogManager.info("Query===>" + query);
			//Object[] obj=new Object[]{appId=="2"?"Marine":appId, branchCode, bean.getLoginID()};
			Object[] obj=new Object[]{ branchCode, bean.getLoginID()};
			for(Object k:obj)
				LogManager.info("Args===>" + k);
			adminList=this.mytemplate.queryForList(query,obj);
			if(adminList!=null && adminList.size()>0){
				Map map=(Map) adminList.get(0);
				bean.setUname(map.get("USERNAME")==null?"":map.get("USERNAME").toString());
				bean.setLoginID(map.get("LOGIN_ID")==null?"":map.get("LOGIN_ID").toString());
				bean.setUtype(map.get("userType")==null?"":map.get("userType").toString());
				bean.setBranch(map.get("BRANCH_NAME")==null?"":map.get("BRANCH_NAME").toString());
				bean.setStatus(map.get("STATUS")==null?"":map.get("STATUS").toString());
				bean.setBroker(map.get("BROKER_CODES")==null?"":map.get("BROKER_CODES").toString());
				bean.setMid(map.get("MENU_ID")==null?"":map.get("MENU_ID").toString());
				bean.setEmail(map.get("USER_MAIL")==null?"":map.get("USER_MAIL").toString());
				bean.setMobileNo(map.get("MOBILE_NO")==null?"":map.get("MOBILE_NO").toString());
				bean.setProductID(map.get("PRODUCT_ID").toString().trim().split(","));
				String attacheduw=map.get("ATTACHED_UW").toString().replace(" ", "");
				bean.setOnlineYN(map.get("ONLINE_YN")==null?"":map.get("ONLINE_YN").toString());
				bean.setFirstName(map.get("FIRST_NAME")==null?"":map.get("FIRST_NAME").toString());
				bean.setMiddleName(map.get("MIDDLE_NAME")==null?"":map.get("MIDDLE_NAME").toString());
				bean.setLastName(map.get("LAST_NAME")==null?"":map.get("LAST_NAME").toString());
				String uwg[]=attacheduw.trim().split(",");
				uwg=(String [])ArrayUtils.remove(uwg, 0);
				//uwg=(String [])ArrayUtils.remove(uwg, uwg.length-1);
				
				bean.setUwgrade(uwg);
				bean.setBranchId(map.get("ATTACHED_BRANCH")==null?"":map.get("ATTACHED_BRANCH").toString());
				//bean.setProduct("Marine");
			}
		}catch (Exception e) {
			LogManager.debug("EXCEPTION @ { " + e + " }");
		}
	}
	
	public void getMenuInfo(final AdminMgtBean bean,String branchCode){
		List <Object> adminList=null;;
		try{
			query=getQuery("GET_MENU_LIST");
			query+=" and A.CATEGORY_DETAIL_ID=?";
			LogManager.info("Query===>" + query);
			Object[] obj=new Object[]{branchCode, bean.getMid()};
			for(Object k:obj)
				LogManager.info("Args===>" + k);
			adminList=this.mytemplate.queryForList(query,obj);
			if(adminList!=null && adminList.size()>0){
				Map map=(Map) adminList.get(0);
				bean.setMid(map.get("CATEGORY_DETAIL_ID")==null?"":map.get("CATEGORY_DETAIL_ID").toString());
				bean.setMname(map.get("DETAIL_NAME")==null?"":map.get("DETAIL_NAME").toString());
				bean.setUrlPath(map.get("REMARKS")==null?"":map.get("REMARKS").toString());
				bean.setStatus(map.get("STATUS")==null?"":map.get("STATUS").toString());
				bean.setParent(map.get("PARENT_MENU")==null?"":map.get("PARENT_MENU").toString());
			}
		}catch (Exception e) {
			LogManager.debug("EXCEPTION @ { " + e + " }");
		}
	}
	
	public int insNewMenu(final AdminMgtBean bean,String branchCode){
		int affRows=0;
		try{
			query=getQuery("INS_NEW_MENU");
			LogManager.info("Query===>" + query);
			Object[] obj=new Object[]{branchCode, bean.getMname(), bean.getUrlPath(), bean.getStatus(),branchCode, branchCode, bean.getParent()};
			for(Object k:obj)
				LogManager.info("Args===>" + k);
			affRows=this.mytemplate.update(query,obj);
		}catch (Exception e) {
			LogManager.debug("EXCEPTION @ { " + e + " }");
		}
		return affRows;
	}
	
	public int updateMenu(final AdminMgtBean bean,String branchCode){
		int affRows=0;
		try{
			query=getQuery("UPD_EXIST_MENU");
			LogManager.info("Query===>" + query);
			Object[] obj=new Object[]{bean.getMname(), bean.getUrlPath(), bean.getStatus(), bean.getParent(), bean.getMid(), branchCode};
			for(Object k:obj)
				LogManager.info("Args===>" + k);
			affRows=this.mytemplate.update(query,obj);
		}catch (Exception e) {
			LogManager.debug("EXCEPTION @ { " + e + " }");
		}
		return affRows;
	}
	
	public int insNewAdmin(final AdminMgtBean bean,String branchCode, String appId){
		int affRows=0;
		try{
			passwordEnc pass = new passwordEnc();
			query=getQuery("INS_NEW_ADMIN_USER");
			LogManager.info("Query===>" + query);
			bean.setUname(bean.getFirstName()+(StringUtils.isBlank(bean.getMiddleName())?"":(" "+bean.getMiddleName()))+" "+bean.getLastName());
			Object[] obj=new Object[]{bean.getLoginID(), pass.crypt(bean.getPwd()), bean.getUtype(), bean.getUname(), bean.getUtype(),
					 bean.getLoginID(),"1", "admin", "Y", "Y", "Y", "BOTH", branchCode, branchCode, bean.getMid(), bean.getProduct(), "", bean.getEmail(),","+StringUtils.join(bean.getUwgrade(),",")+",",bean.getBranchId(),bean.getMobileNo(),bean.getOnlineYN()==null?"N":bean.getOnlineYN(),
							 bean.getFirstName(), bean.getMiddleName(), bean.getLastName()};
			for(Object k:obj)
				LogManager.info("Args===>" + k);
			affRows=this.mytemplate.update(query,obj);
		}catch (Exception e) {
			LogManager.debug("EXCEPTION @ { " + e + " }");
		}
		return affRows;
	}
	
	public int updateAdmin(final AdminMgtBean bean, String branchCode, String appId){
		int affRows=0;
		Object[] obj=null;
		try{
			LogManager.info("Query===>" + query);
			bean.setUname(bean.getFirstName()+(StringUtils.isBlank(bean.getMiddleName())?"":(" "+bean.getMiddleName()))+" "+bean.getLastName());
			if("".equals(bean.getPwd()) || bean.getPwd()==null){
				query=getQuery("UPD_EXIST_ADMIN_USER");
				obj=new Object[]{bean.getUname(),bean.getUtype(), appId, bean.getStatus(), bean.getMid(), bean.getProduct(), bean.getEmail(), bean.getBroker(),","+StringUtils.join(bean.getUwgrade(),",")+",",bean.getBranchId(),bean.getMobileNo(),bean.getOnlineYN()==null?"N":bean.getOnlineYN(),
						bean.getFirstName(), bean.getMiddleName(), bean.getLastName(), bean.getLoginID()};
			}
			else{
				passwordEnc pass = new passwordEnc();
				query=getQuery("UPD_EXIST_ADMIN_USER_PWD");
				obj=new Object[]{bean.getUname(),pass.crypt(bean.getPwd()),bean.getUtype(), appId, bean.getStatus(), bean.getMid(), bean.getProduct(), bean.getEmail(), bean.getBroker(),","+StringUtils.join(bean.getUwgrade(),",")+",",bean.getBranchId(),
						bean.getFirstName(), bean.getMiddleName(), bean.getLastName(), bean.getLoginID()};
				
			}
			for(Object k:obj)
				LogManager.info("Args===>" + k);
			affRows=this.mytemplate.update(query,obj);
		}catch (Exception e) {
			LogManager.debug("EXCEPTION @ { " + e + " }");
		}
		return affRows;
	}
	public String getBranch(String branchCode){
		String branch="";
		try{
			query=getQuery("GET_BRANCH_NAME");
			LogManager.info("Query===>" + query);
			Object[] obj=new Object[]{branchCode};
			for(Object k:obj)
				LogManager.info("Args===>" + k);
			branch=this.mytemplate.queryForObject(query,obj, String.class).toString();
		}catch (Exception e) {
			LogManager.debug("EXCEPTION @ { " + e + " }");
		}
		return branch;
	}
	public List<Object> getDashBoard(String loginId,AdminMgtBean bean, String productId,String branchCode) {
		List<Object> result=null;
		try{
			String query=getQuery("GET_DASHBOARD_ADMIN");
			Object obj[]=new Object[]{branchCode,bean.getProduct()};
			LogManager.info("getDashBoard Query ==>"+query);
			LogManager.info("getDashBoard Args ==>"+StringUtils.join(obj,", "));
			result=this.mytemplate.queryForList(query,obj);
			for(int i=0;i<result.size();i++){
			Map<String,Object> temp=(Map<String,Object>)result.get(i);
				bean.setNoOfQuote(temp.get("GIVEN_QUOTE").toString());
				bean.setCustomerLinkedQuote(temp.get("CUSTOMER_LINKED_QUOTE").toString());
				bean.setPolicyAccept(temp.get("APPROVED_POLICIES").toString());
				bean.setPolicyReject(temp.get("REJECTED_POLICES").toString());
				bean.setCcPending(temp.get("CCPPENDING_POLICES").toString());
				bean.setSsPending(temp.get("SSPPENDING_POLICES").toString());
				bean.setUwPending(temp.get("UWPPENDING_POLICES").toString());
			}
			
				/*for(int i=0;i<result.size();i++){
				Map<String,Object> temp=(Map<String,Object>)result.get(i);
				if("PORTFOLIO".equalsIgnoreCase(temp.get("TYPE")==null?"":temp.get("TYPE").toString())){
					bean.setOneOffPortFolio(temp.get("QUOTE_NO").toString());
					bean.setOneOffPremium(temp.get("PREMIUM").toString());
				}
		        if("UNAPPROVED".equalsIgnoreCase(temp.get("TYPE")==null?"":temp.get("TYPE").toString())){
		        	bean.setOneOffPending(temp.get("QUOTE_NO").toString());
		        }
		        if("APPROVED".equalsIgnoreCase(temp.get("TYPE")==null?"":temp.get("TYPE").toString())){
		        	bean.setOneOffAccepted(temp.get("QUOTE_NO").toString());
		        }
		        if("REJECT".equalsIgnoreCase(temp.get("TYPE")==null?"":temp.get("TYPE").toString())){
		        	bean.setOneOffRejected(temp.get("QUOTE_NO").toString());
		        }
			}
			obj=new Object[]{bean.getProduct(),loginId};
			LogManager.info("getDashBoard Args ==>"+StringUtils.join(obj,", "));
			result=this.mytemplate.queryForList(query,obj);
			for(int i=0;i<result.size();i++){
				Map<String,Object> temp=(Map<String,Object>)result.get(i);
				if("PORTFOLIO".equalsIgnoreCase(temp.get("TYPE")==null?"":temp.get("TYPE").toString())){
					bean.setOpenCoverPortFolio(temp.get("QUOTE_NO").toString());
					bean.setOpenCoverPremium(temp.get("PREMIUM").toString());
				}
		        if("UNAPPROVED".equalsIgnoreCase(temp.get("TYPE")==null?"":temp.get("TYPE").toString())){
		        	bean.setOpenCoverPending(temp.get("QUOTE_NO").toString());
		        }
		        if("APPROVED".equalsIgnoreCase(temp.get("TYPE")==null?"":temp.get("TYPE").toString())){
		        	bean.setOpenCoverAccepted(temp.get("QUOTE_NO").toString());
		        }
		        if("REJECT".equalsIgnoreCase(temp.get("TYPE")==null?"":temp.get("TYPE").toString())){
		        	bean.setOpenCoverRejected(temp.get("QUOTE_NO").toString());
		        }
			}*/
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	public void getPopUpList(AdminMgtBean bean,String branchCode) {
		List<Map<String,Object>> popupList=null;
		try{
			String query="";
			Object[] args=null;
			if("commodities".equalsIgnoreCase(bean.getClausesType())){
				query=getQuery("GET_COMMODITY_LIST_CLAUSES");
				args=new Object[]{bean.getProposalNo()};
			}else{
				query=getQuery("GET_COVER_LIST_CLAUSES");
				args=new Object[]{bean.getProposalNo(),branchCode};
			}
			popupList=this.mytemplate.queryForList(query,args);
			bean.setCommodityList(popupList);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public int saveOptionalCovers(AdminMgtBean bean) {
		 int result=1;
		try{
			String query=getQuery("OPTIONAL_COVER_INSERT");
			LogManager.info("OptionalCovers Insert Query "+query);
			this.mytemplate.update("delete from OPEN_COVER_FREE_TEXT where PROPOSAL_NO=? AND TYPE = 'O' and cover_id=? ",new Object[]{bean.getProposalNo(),bean.getCoverNo()});
			LogManager.info("delete from OPEN_COVER_FREE_TEXT where PROPOSAL_NO=? AND TYPE = 'O' and cover_id=?");
			String amndId=(String)this.mytemplate.queryForObject("select nvl(max(amend_id)+1,0) from OPEN_COVER_FREE_TEXT where PROPOSAL_NO=? and cover_id=?", new Object[]{bean.getProposalNo(),bean.getCoverNo()},String.class);
			if(null!=bean.getOptionalId() && null!=bean.getOptionalDesc()){
				for(int i=0;i<bean.getOptionalId().size();i++){
					if(!"false".equalsIgnoreCase(bean.getOptionalId().get(i))){
					  LogManager.info("Args "+StringUtils.join(new Object[]{bean.getProposalNo(),amndId,"0",bean.getCoverNo(),"0",bean.getOptionalId().get(i),bean.getOptionalDesc().get(i),bean.getEffectDate(),"Y","01","O"},","));	
					  this.mytemplate.update(query,new Object[]{bean.getProposalNo(),amndId,"0",bean.getCoverNo(),"0",bean.getOptionalId().get(i),bean.getOptionalDesc().get(i),bean.getEffectDate(),"Y","01","O"});
					}
				}
			}
		   }catch (Exception e) {
			result=0;
			e.printStackTrace();
		}
		return result;
	}
	public void getOptionalCoverList(String branchCode, AdminMgtBean bean) {
		try{
		 String coverQuery=getQuery("GET_OPTIONAL_COVER_NOT_SELECTED");	
		 LogManager.push("Non Selected Optional Covers List "+coverQuery+"\n args "+StringUtils.join(new Object[]{bean.getCoverNo(),bean.getProposalNo(),bean.getProposalNo(),bean.getCoverNo()}));
		 List coverList=this.mytemplate.queryForList(coverQuery,new Object[]{branchCode,bean.getProposalNo(),bean.getProposalNo(),bean.getCoverNo(),bean.getCoverNo()});
		 String selectedQuery=getQuery("GET_OPTIONAL_COVER_SELECTED");
		 LogManager.push("Selected Optional Covers List "+selectedQuery+"\n args "+new Object[]{bean.getProposalNo(),bean.getProposalNo(),bean.getCoverNo(),bean.getCoverNo()});
		 List selectedList=this.mytemplate.queryForList(selectedQuery,new Object[]{bean.getProposalNo(),bean.getProposalNo(),bean.getCoverNo(),bean.getCoverNo()});
		 bean.setOptionalCoverList(ListUtils.union(selectedList, coverList));
		 bean.setSelectedCovers(selectedList);
		 if(null!=selectedList){
		   if(selectedList.size()>0){
			   Map temp=(Map)selectedList.get(0);
			   bean.setEffectDate((String)temp.get("EFFECTIVE_DATE"));
		   }else{
			     Calendar cal=Calendar.getInstance();
		    	 int date=cal.get(Calendar.DAY_OF_MONTH);
		    	 int month=cal.get(Calendar.MONTH)+1;
		    	 int year=cal.get(Calendar.YEAR);
		    	 bean.setEffectDate(date+"-"+month+"-"+year);
		   }
		 }
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public List<Map<String,String>> getUwGradeList() {
		List<Map<String,String>> result=null;
		try{
			String query=getQuery("GET_RIMASTER");
			result=this.mytemplate.queryForList(query);
		}catch (Exception e) {
			e.printStackTrace();
		}
	 	return result;
	}

	public Map<String,List<Object>> getProductList(AdminMgtBean bean, String branchCode) {
		
		 Map<String,List<Object>> result=null;
		 try{			  
			 List<Map<String,Object>> key=null;
			 String query=getQuery("GET_PRODUCT_LIST");
			 String arg="'"+StringUtils.join(bean.getSelProducts().split(","),"','")+"'";
			 LogManager.info("query "+query.replace("?", arg));			 
			 key=this.mytemplate.queryForList(query.replace("?", arg));
			 query="SELECT MENU_ID , MENU_NAME FROM MENU_MASTER WHERE STATUS='Y' AND BRANCH_CODE=(SELECT BELONGING_BRANCH FROM BRANCH_MASTER WHERE BRANCH_CODE=?) AND  PRODUCT_ID = ? and USERTYPE='admin'";
			 String []product=bean.getSelProducts().split(",");
			 result=new HashMap<String, List<Object>>();
			 for (int i = 0; i < product.length; i++) {
				 List<Object> value=this.mytemplate.queryForList(query,new Object[]{branchCode,key.get(i).get("PRODUCT_ID").toString()});
				 result.put(key.get(i).get("PRODUCT_NAME").toString(), value);	
			 } 
		 }catch (Exception e) {
			 e.printStackTrace();
		}
		 return result;
	}
	
	public List<Map<String,Object>> getPaymentMas(AdminMgtBean bean){
		AdminMgtService service= new AdminMgtService();
		List<Map<String,Object>> drpdwn= new ArrayList<Map<String,Object>>();
		try{
			LogManager.info("Enter into the getPaymentMas");
			String query = getQuery("GET_PAYMENT_MAS_LIST");
			LogManager.info("Query===>" + query);
			drpdwn=this.mytemplate.queryForList(query);
			for(int i=0;i<drpdwn.size();i++){
				Map<String,Object> temp=(Map<String,Object>)drpdwn.get(i);
					bean.setProfileId(service.paymentDecrypt(temp.get("PROFILE_ID").toString()));
					bean.setAccessKey(service.paymentDecrypt(temp.get("ACCESS_KEY").toString()));
					bean.setSecretKey(service.paymentDecrypt(temp.get("SECRET_KEY").toString()));
					Map<String,Object> rec=new HashMap<String, Object>();
					for(Map.Entry m:temp.entrySet())
					{
					rec.put((String) m.getKey(),m.getValue());
					}
					rec.put("PROFILE_ID",bean.getProfileId() );
					rec.put("ACCESS_KEY",bean.getAccessKey() );
					rec.put("SECRET_KEY", bean.getSecretKey());
					drpdwn.set(i,rec) ;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		LogManager.info("Exit from the getPaymentMas");
		return drpdwn;
		
	}

	public List<Map<String,Object>> getEditPaymentMas(AdminMgtBean bean) {
		AdminMgtService service= new AdminMgtService();
		List<Map<String,Object>> drpdwn = null;
		try{
			LogManager.info("Enter into the getEditPaymentMas");
			query = getQuery("Get_EDIT_PAYMENT_PROCESS");
			LogManager.info("Query===>" + query);
			Object args[]=new Object[]{bean.getPaymentId()};
			drpdwn = this.mytemplate.queryForList(query,args);
			LogManager.info("Arqumentw==>"+StringUtils.join(args,","));
			if(drpdwn.size()>0){
				Map map=(Map)drpdwn.get(0);
				bean.setPaymentId(map.get("PAYMENT_ID") == null ? "": map.get("PAYMENT_ID").toString());
				bean.setProfileId(service.paymentDecrypt(map.get("PROFILE_ID") == null ? "": map.get("PROFILE_ID").toString()));
				bean.setAccessKey(service.paymentDecrypt(map.get("ACCESS_KEY") == null ? "": map.get("ACCESS_KEY").toString()));
				bean.setSecretKey(service.paymentDecrypt(map.get("SECRET_KEY") == null ? "": map.get("SECRET_KEY").toString()));
				bean.setExpiryDate(map.get("EXPIRY_DATE") == null ? "": map.get("EXPIRY_DATE").toString());
				bean.setCurrencyType(map.get("CURRENCY_TYPE") == null ? "": map.get("CURRENCY_TYPE").toString());
				bean.setIntiExpiryDate(map.get("INTIMATION_EXPIRY_DATE") == null ? "": map.get("INTIMATION_EXPIRY_DATE").toString());
				bean.setIntiMobileNo(map.get("INTIMATION_MOBILE_NO") == null ? "": map.get("INTIMATION_MOBILE_NO").toString());
				bean.setIntiEMailId(map.get("INTIMATION_MAIL_ID") == null ? "": map.get("INTIMATION_MAIL_ID").toString());
				bean.setStatus(map.get("STATUS") == null ? "": map.get("STATUS").toString());
				bean.setRemarks(map.get("REMARKS") == null ? "": map.get("REMARKS").toString());
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		LogManager.info("Exit from the getEditPaymentMas");
		return drpdwn;
	}

	public int getUpdatePayMas(AdminMgtBean bean) {
		AdminMgtService service= new AdminMgtService();
		int result=0;
		try{
			LogManager.info("Enter into the getUpdatePayMas");
			if("add".equalsIgnoreCase(bean.getMode())){
				query = getQuery("INSERT_PAY_MAS");
				LogManager.info("Query===>" + query);
				Object args[] = new Object[10];
				args[0]=service.paymentEncrypt(bean.getAccessKey());
				args[1]=service.paymentEncrypt(bean.getSecretKey());
				args[2]=bean.getExpiryDate();
				args[3]=bean.getCurrencyType();
				args[4]=bean.getIntiExpiryDate();
				args[5]=bean.getIntiMobileNo();
				args[6]=bean.getIntiEMailId();
				args[7]=bean.getStatus();
				args[8]=bean.getRemarks();
				args[9]=service.paymentEncrypt(bean.getProfileId());
				LogManager.info("Arqumentw==>"+StringUtils.join(args,","));
				result=this.mytemplate.update(query,args);
			}
			if("edit".equalsIgnoreCase(bean.getMode())){
			query = getQuery("UPDATE_PAY_MAS");
			LogManager.info("Query===>" + query);
			Object args[] = new Object[11];
			args[0]=service.paymentEncrypt(bean.getAccessKey());
			args[1]=service.paymentEncrypt(bean.getSecretKey());
			args[2]=bean.getExpiryDate();
			args[3]=bean.getCurrencyType();
			args[4]=bean.getIntiExpiryDate();
			args[5]=bean.getIntiMobileNo();
			args[6]=bean.getIntiEMailId();
			args[7]=bean.getStatus();
			args[8]=bean.getRemarks();
			args[9]=service.paymentEncrypt(bean.getProfileId());
			args[10]=bean.getPaymentId();
			LogManager.info("Arqumentw==>"+StringUtils.join(args,","));
			result=this.mytemplate.update(query,args);
			}
			
		}catch(Exception e){
		e.printStackTrace();
		}
		LogManager.info("Enter into the getUpdatePayMas");
		return result;
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
	
	public int checkCurrencyType(AdminMgtBean bean){
		int result=0;
		try{
			query =getQuery("CHECK_CURRENCY_TYPE");
			Object[] arg= new Object[]{bean.getCurrencyType()};
			result=this.mytemplate.queryForInt(query,arg);
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
		
	}

	public List<Object> getdefaultMenus(String branchCode) {
		List<Object> result=null;
		 try{
			 result=this.mytemplate.queryForList("select DETAIL_NAME,REMARKS from  constant_detail where BRANCH_CODE=? and  CATEGORY_ID = 204 and  CATEGORY_DETAIL_ID='1'",new Object[]{branchCode});
		 }catch (Exception e) {
			 e.printStackTrace();
		}
		 return result;
	}

	public void changePassword(AdminMgtBean bean) {
		try
		   { 
			String query="update login_master set password=?,STATUS='Y',PWD_COUNT=0 where login_id=?";
			LogManager.info("Query===>" + query);
			this.mytemplate.update(query,new Object[]{bean.getPwd(),bean.getLoginID()});
	   }
	   catch (Exception e) 
		{
			LogManager.debug("EXCEPTION @ { " + e + " }");
		}
	}

	public boolean checkEmailAvailability(String email) {
		try {
			String query = "SELECT COUNT(*) FROM LOGIN_MASTER WHERE UPPER(USER_MAIL) = UPPER(?) AND UPPER(USERTYPE) = 'ADMIN'";
			Object[] args = new Object[] {email};
			LogManager.info("AdminMgtDao.checkEmailAvailability() Query: "+queryFrammer(query, args));
			int count = this.mytemplate.queryForInt(query, args);
			if(count>0) {
				return true;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean checkMobileNoAvailability(String mobile) {
		try {
			String query = "SELECT COUNT(*) FROM LOGIN_MASTER WHERE MOBILE_NO = ? AND UPPER(USERTYPE) = 'ADMIN'";
			Object[] args = new Object[] {mobile};
			LogManager.info("AdminMgtDao.checkMobileNoAvailability() Query: "+queryFrammer(query, args));
			int count = this.mytemplate.queryForInt(query, args);
			if(count>0) {
				return true;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}