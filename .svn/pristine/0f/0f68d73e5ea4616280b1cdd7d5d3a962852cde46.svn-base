package com.maan.adminnew.underwriterManagement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.maan.adminnew.userManagement.UserMgtBean;
import com.maan.common.LogManager;
import com.maan.common.MyJdbcTemplate;
import com.maan.common.password.passwordEnc;
import com.opensymphony.xwork2.ActionContext;

public class UnderwriterMgtDAO extends MyJdbcTemplate{
	String query="";
	passwordEnc pass = new passwordEnc();
	Map<String, Object> session=ActionContext.getContext().getSession();
	public List <Object> getAdminUnderwriterList(final UnderwriterMgtBean ba, String agencyCode, String mode1, String branchCode){
    	List <Object> underwriterList=null;
    	Object[] obj=null;
    	try{
    		if(StringUtils.isNotBlank(ba.getSearchValue())){
    			query=getQuery("GET_ISSUER_LIST")+" and upper(username) like '%'||upper(?)||'%'";
    			obj=new Object[]{branchCode,ba.getSearchValue()};
    		}
    		else{
    			query=getQuery("GET_ISSUER_LIST");
    			obj=new Object[]{branchCode};
    		}
			LogManager.info("Query===>" + query);
			underwriterList=this.mytemplate.queryForList(query,obj);		
		   }
		catch (Exception e) {			
			LogManager.debug("EXCEPTION @ { " + e + " }");
		}
    	return underwriterList;
    }
	
	
	public List <Object> getUnderwriterDetails(final UnderwriterMgtBean ba, String branchCode, String issurName){
		List <Object> underwriterInfo=null;
		Object[] obj=null;
		try{
			query="select username,user_mail,login_id,CORE_LOGIN_ID,product_id,status,ATTACHED_BRANCH,REFERAL from login_master where branch_code=? and usertype='RSAIssuer' and login_id=?";
			LogManager.info("Query===>" + query);
			LogManager.info("IssuerName===>" + ba.getIssurName());
			obj=new Object[]{session.get("BranchCode"),ba.getLoginId()};
			underwriterInfo=this.mytemplate.queryForList(query,obj);
			String products="";
			for(int i=0;i<underwriterInfo.size();i++){
				Map temp=(Map)underwriterInfo.get(i);
				ba.setIssurName(temp.get("USERNAME")==null?"":temp.get("USERNAME").toString());
				ba.setCoreLoginId(temp.get("CORE_LOGIN_ID")==null?"":temp.get("CORE_LOGIN_ID").toString());
				ba.setEmailId(temp.get("USER_MAIL")==null?"":temp.get("USER_MAIL").toString());
				products=temp.get("PRODUCT_ID")==null?"":temp.get("PRODUCT_ID").toString();
				ba.setUstatus(temp.get("STATUS")==null?"":temp.get("STATUS").toString());
				ba.setBranchId(temp.get("ATTACHED_BRANCH")==null?"":temp.get("ATTACHED_BRANCH").toString());
				ba.setReferralYN(temp.get("REFERAL")==null?"":temp.get("REFERAL").toString());
			}
			String[] productIds=products.split(",");
			List<String> product=new ArrayList<String>();
			for(int i=0;i<productIds.length;i++){
				product.add(productIds[i].trim());
			}
			ba.setProducts(product);			
			 /*List branchList=this.mytemplate.queryForList(getQuery("issuer.branch"),new Object[]{branchCode, ba.getLoginId()});
			 String branchNames="";
			 String branchCodes="";
			 for(int i=0;i<branchList.size();i++){
				 Map temp=(Map)branchList.get(i);
				 branchNames=branchNames+","+(temp.get("CODEDESC")==null?"":temp.get("CODEDESC").toString());
				 branchCodes=branchCodes+","+(temp.get("CODE")==null?"":temp.get("CODE").toString());
			  }
		 ba.setBranchNames(branchNames);
		 ba.setBranchSelected(branchCodes);
*/		 }catch (Exception e) {			
			LogManager.debug("EXCEPTION @ { " + e + " }");
		}
    	return underwriterInfo;
	}
	
	/*public void updatebrokerDetails(final UnderwriterMgtBean ba, String issurName){
		try{
			query=getQuery("UPDATE_INCLUDE_BROKER");
			if(ba.getBrokerCode()!=null && ba.getBrokerCode().size()>0){
				for(int i=0;i<ba.getBrokerCode().size();i++){
					Object args[]=new Object[4];
					args[0]=ba.getType().get(i+1)==null?"exclude":"include";
					args[1]=ba.getStatus().get(i).toString();
					args[2]=ba.getBrokerCode().get(i).toString();
					args[3]=ba.getProductId().get(i).toString();
				  LogManager.push("Query "+query+" args"+StringUtils.join(args,",")+"IssuerName "+ba.getIssurName());	
			      this.mytemplate.update(query,args);
				}
		     }
		}
		catch (Exception e) {			
			LogManager.debug("EXCEPTION @ { " + e + " }");
		}
	}*/
	
	private int size(List<String> type) {
		
		return 0;
	}

	public List <Map<String, Object>> includeissuerDetails(final UnderwriterMgtBean ba, String type1){
		List <Map<String, Object>> includeIssuer=null;
		Object[] obj=null;
		try{
			//query=getQuery("GET_INCLUDE_BROKER");
			//query="select  AGENCY_CODE, COMPANY_NAME, CONTACT_PERSON, ADDRESS1, ADDRESS2, ADDRESS3,CITY, COUNTRY, PHONE,POBOX, FAX, EMIRATE,REMARKS, STATUS, CUSTOMER_ID,BRANCH_CODE, MISSIPPI_ID, APPROVED_PREPARED_BY,RSA_BROKER_CODE, AC_EXECUTIVE_ID from broker_company_master bcm where agency_code in (select agency_code from login_master where branch_code=?) and agency_code not in (select broker_code  from login_rsauser_details where login_id=? and branch_code=? )";
			//For Issuer need to show all Brokers with different Branch
			//query="select  AGENCY_CODE, COMPANY_NAME, CONTACT_PERSON, ADDRESS1, ADDRESS2, ADDRESS3,CITY, COUNTRY, PHONE,POBOX, FAX, EMIRATE,REMARKS, STATUS, CUSTOMER_ID,bcm.BRANCH_CODE, MISSIPPI_ID, APPROVED_PREPARED_BY,RSA_BROKER_CODE, AC_EXECUTIVE_ID,(SELECT BRANCH_NAME FROM BRANCH_MASTER bm WHERE bm.BRANCH_CODE=bcm.BRANCH_CODE) BRANCH_NAME from broker_company_master bcm where agency_code not in (select broker_code  from login_rsauser_details where login_id=?)";
			query = "SELECT AGENCY_CODE, COMPANY_NAME, CONTACT_PERSON, ADDRESS1, ADDRESS2, ADDRESS3,CITY, COUNTRY, PHONE,POBOX, FAX, EMIRATE,REMARKS, STATUS, CUSTOMER_ID,BCM.BRANCH_CODE, MISSIPPI_ID, APPROVED_PREPARED_BY,RSA_BROKER_CODE, AC_EXECUTIVE_ID,(SELECT BRANCH_NAME FROM BRANCH_MASTER BM WHERE BM.BRANCH_CODE=BCM.BRANCH_CODE) BRANCH_NAME,(SELECT LISTAGG(PRODUCT_ID,',') WITHIN GROUP(ORDER BY PRODUCT_ID) PRODUCT_IDS FROM PRODUCT_MASTER WHERE STATUS='Y' AND BRANCH_CODE =( SELECT BELONGING_BRANCH FROM BRANCH_MASTER BM WHERE BRANCH_CODE='01' AND STATUS='Y' AND AMEND_ID=(SELECT MAX(AMEND_ID) FROM BRANCH_MASTER BM1 WHERE BM1.BRANCH_CODE=BM.BRANCH_CODE AND BM1.STATUS=BM.STATUS)) AND PRODUCT_ID NOT IN (SELECT DISTINCT PRODUCT_ID FROM LOGIN_RSAUSER_DETAILS F WHERE F.BROKER_CODE = BCM.AGENCY_CODE AND LOGIN_ID = ?)) PRODUCT_IDS FROM BROKER_COMPANY_MASTER BCM";
			LogManager.info("Query===>" + queryFrammer(query, new Object[]{ba.getLoginId()}));
			//LogManager.info("Type===>" + ba.getType());
			//obj=new Object[]{type1};
			includeIssuer=this.mytemplate.queryForList(query,new Object[]{ba.getLoginId()});
		}
		catch (Exception e) {			
			LogManager.debug("EXCEPTION @ { " + e + " }");
		}
		return includeIssuer;
	}
	
	public List <Map<String, Object>> excludeissuerDetails(final UnderwriterMgtBean ba, String type1){
		List <Map<String, Object>> excludeIssuer=null;
		Object[] obj=null;
		try{
			//query=getQuery("GET_EXCLUDE_BROKER");
			//String query="select distinct(BROKER_CODE) agency_code,COMPANY_NAME,bcm.BRANCH_CODE,(SELECT BRANCH_NAME FROM BRANCH_MASTER bm WHERE bm.BRANCH_CODE=bcm.BRANCH_CODE) BRANCH_NAME from login_rsauser_details lrd,broker_company_master bcm where lrd.broker_code=bcm.agency_code and login_id=?";
			query = " SELECT AGENCY_CODE, COMPANY_NAME, CONTACT_PERSON, ADDRESS1, ADDRESS2, ADDRESS3,CITY, COUNTRY, PHONE,POBOX, FAX, EMIRATE,REMARKS, STATUS, CUSTOMER_ID,BCM.BRANCH_CODE, MISSIPPI_ID, APPROVED_PREPARED_BY,RSA_BROKER_CODE, AC_EXECUTIVE_ID,(SELECT BRANCH_NAME FROM BRANCH_MASTER BM WHERE BM.BRANCH_CODE=BCM.BRANCH_CODE) BRANCH_NAME,(SELECT LISTAGG(PRODUCT_ID,',') WITHIN GROUP(ORDER BY PRODUCT_ID) PRODUCT_IDS FROM PRODUCT_MASTER WHERE STATUS='Y' AND BRANCH_CODE =( SELECT BELONGING_BRANCH FROM BRANCH_MASTER BM WHERE BRANCH_CODE='01' AND STATUS='Y' AND AMEND_ID=(SELECT MAX(AMEND_ID) FROM BRANCH_MASTER BM1 WHERE BM1.BRANCH_CODE=BM.BRANCH_CODE AND BM1.STATUS=BM.STATUS)) AND PRODUCT_ID IN (SELECT DISTINCT PRODUCT_ID FROM LOGIN_RSAUSER_DETAILS F WHERE F.BROKER_CODE = BCM.AGENCY_CODE AND LOGIN_ID = ?)) PRODUCT_IDS FROM BROKER_COMPANY_MASTER BCM";
			LogManager.info("Query===>" + queryFrammer(query, new Object[]{ba.getLoginId()}));
			//LogManager.info("Type===>" + ba.getType());
			//obj=new Object[]{type1};
			excludeIssuer=this.mytemplate.queryForList(query,new Object[]{ba.getLoginId()});
		}
		catch (Exception e) {			
			LogManager.debug("EXCEPTION @ { " + e + " }");
		}
		return excludeIssuer;
	}
  public void getRSABranches(UnderwriterMgtBean bean) {
	    try{
		String query ="select branch_code,branch_name from rsa_branch_details where branch_code in ( SELECT distinct(REGEXP_SUBSTR(rsa_branch,'[^,]+',1,LEVEL)) lc_login FROM (select Rsa_branch from branch_master where branch_code=?) B  CONNECT BY LEVEL <= LENGTH(rsa_branch) - LENGTH(REPLACE(rsa_branch,',',''))+1 and rsa_branch is not null)";
			//getQuery("GET_RSA_BRANCHES");
		if(StringUtils.isNotEmpty(bean.getBranchSelected())){
			String right=bean.getBranchSelected().replaceAll(",", "','");
			String rightCodes="'"+right+"'";
		    bean.setLeftBranchList(this.mytemplate.queryForList(query+" and branch_code not in("+rightCodes+")",new Object[]{bean.getMainBranchCode()}));
		    bean.setRightBranchList(this.mytemplate.queryForList(query+" and branch_code in("+rightCodes+")",new Object[]{bean.getMainBranchCode()}));
		}else{
			bean.setLeftBranchList(this.mytemplate.queryForList(query,new Object[]{bean.getMainBranchCode()}));
			bean.setRightBranchList(new ArrayList<Map<String,Object>>());
		}
	    }catch(Exception e){
	    	LogManager.debug("EXCEPTION @ { " + e + " }");
	    }
	}


	public int insertUnderwriter(UnderwriterMgtBean bean) {
		//insert into login_master(LOGIN_ID,PASSWORD,USERTYPE,USERNAME,USERID,AGENCY_CODE,OA_CODE,COMPANY_ID,CREATED_BY,STATUS,USER_ID_CREATION,AC_EXECUTIVE_CREATION,ACCESSTYPE,PASSDATE,BRANCH_CODE,COUNTRY_ID,CORE_LOGIN_ID, APP_ID, PWD_COUNT, USER_MAIL) values(?,?,?,?,?,?,?,?,?,?,?,?,?,(select sysdate from dual),?,?,?, '16', '0', ?)
		try{
			List<String> products=bean.getProducts();
			String productIds="";
			if(products!=null){
				for(int i=0;i<products.size();i++){
					productIds=productIds+","+products.get(i);
				}
			}	
		Object args[]=new Object[20];
		args[0]=bean.getLoginId();
		args[1]=pass.crypt(bean.getPassword());
		args[2]="RSAIssuer";
		args[3]=bean.getIssurName();
		args[4]="1";
		args[5]=bean.getLoginId()+1;
		args[6]=bean.getLoginId();
		args[7]="1";
		args[8]=session.get("user");
		args[9]="Y";
		args[10]="";
		args[11]="";
		args[12]="BOTH";
		args[13]=session.get("BranchCode");
		args[14]="";
		args[15]=bean.getCoreLoginId();
		args[16]=bean.getEmailId();
		args[17]=productIds;
		args[18]=bean.getBranchId();
		args[19]=bean.getReferralYN();
		this.mytemplate.update(getQuery("INS_ISSUER_LOGIN_DET"),args);
		//this.mytemplate.update("update login_master set product_id=? , RSA_BRANCH_CODE =?,entry_date=sysdate where login_id=?" ,new Object[]{productIds.replaceFirst(",", ""),bean.getBranchSelected(),bean.getLoginId()});
		//Insert for product details
		String query="insert into login_user_details(user_id,user_name,login_id,AGENCY_CODE,OA_CODE,PRODUCT_ID,COMPANY_ID,AMEND_ID,STATUS,SCHEME_ID,ISSUERTYPE,IS_B2C) values(?,?,?,?,?,?,?,?,?,?,'77777','N')";
		Object product[]=new Object[10];
		if(bean.getProducts()!=null){
			for(int i=0;i<bean.getProducts().size();i++){
				product[0]=(String)this.mytemplate.queryForObject("select max(user_id)+1 from login_user_details", String.class); 
				product[1]=bean.getIssurName();
				product[2]=bean.getLoginId();
				product[3]=bean.getLoginId()+1;
				product[4]=bean.getLoginId();
				product[5]=bean.getProducts().get(i);
				product[6]="1";
				product[7]="0";
				product[8]="Y";
				product[9]="30".equalsIgnoreCase(bean.getProducts().get(i))?"3":"";
				this.mytemplate.update(query,product);
			}
		}
		}catch(Exception e){
			e.printStackTrace();
		}
		return 0;
	}
	public int updateUnderwriter(UnderwriterMgtBean bean){
		try{
		String query="update login_master set username=?,user_mail=?,product_id=?,CORE_LOGIN_ID=?,status=?, BRANCH_CODE=?,ATTACHED_BRANCH=?,REFERAL=? where login_id=?";
		List<String> products=bean.getProducts();
		String product="";
		for(int i=0;i<products.size();i++){
			product=product+","+products.get(i);
		}
		this.mytemplate.update(query,new Object[]{bean.getIssurName(),bean.getEmailId(),product,bean.getCoreLoginId(),bean.getUstatus(),session.get("BranchCode"),bean.getBranchId(),bean.getReferralYN(),bean.getLoginId()});
		
		String amendId=(String)this.mytemplate.queryForObject("select nvl(max(amend_id)+1,0) from login_user_details where LOGIN_ID=?", new Object[]{bean.getLoginId()}, String.class);
		
		query="insert into login_user_details(user_id,user_name,login_id,AGENCY_CODE,OA_CODE,PRODUCT_ID,COMPANY_ID,AMEND_ID,STATUS) values(?,?,?,?,?,?,?,?,?)";
		Object product1[]=new Object[9];
		if(bean.getProducts()!=null){
			for(int i=0;i<bean.getProducts().size();i++){
				product1[0]=(String)this.mytemplate.queryForObject("select max(user_id)+1 from login_user_details", String.class); 
				product1[1]=bean.getIssurName();
				product1[2]=bean.getLoginId();
				product1[3]=bean.getLoginId()+1;
				product1[4]=bean.getLoginId();
				product1[5]=bean.getProducts().get(i);
				product1[6]="1";
				product1[7]=amendId;
				product1[8]="Y";
				this.mytemplate.update(query,product1);
			}
		}
		
		}catch(Exception e){
			e.printStackTrace();
		}
       return 0;
		
	}
	public void changePassword(UnderwriterMgtBean bean) {
		   try
		   { 
				String query="update login_master set password=?,STATUS='Y',PWD_COUNT=0 where login_id=?";
				LogManager.info("Query===>" + query);
				this.mytemplate.update(query,new Object[]{bean.getPassword(),bean.getLoginId()});
		   }
		   catch (Exception e) 
			{
				LogManager.debug("EXCEPTION @ { " + e + " }");
			}
		}
   public void updateExcludedBrokers(UnderwriterMgtBean bean) {
	   try{
		   List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
		   String query="SELECT PRODUCT_ID,ATTACHED_BRANCH FROM LOGIN_MASTER WHERE LOGIN_ID=?";
		   LogManager.info("updateExcludedBrokers Query===>" + queryFrammer(query, new Object[]{bean.getLoginId()}));
		   list=this.mytemplate.queryForList(query,new Object[]{bean.getLoginId()});
		   String branchList=list.get(0).get("ATTACHED_BRANCH")==null?"":list.get(0).get("ATTACHED_BRANCH").toString();
		   Object [] attachedBranch=branchList.split(",");
		   if(bean.getBrokerCode()!=null && bean.getStatusChk()!=null){
			   for(int i=0;i<bean.getStatusChk().size();i++){
				   if(bean.getStatusChk().get(i)){
					   String prodList=bean.getProductId().get(i);
					   	Object [] productIds=prodList.split(",");//new String[]{"3","11"};
					   	for(int k=0;k<attachedBranch.length;k++){
						   for(int j=0;j<productIds.length;j++){
							   if(productIds[j]!=null && StringUtils.isNotBlank(productIds[j].toString())){
								   Object args[]=new Object[7];
								   args[0]=bean.getLoginId();
								   args[1]=bean.getBrokerCode().get(i);
								   args[2]=productIds[j];
								   args[3]=attachedBranch[k];//args[3]=session.get("BranchCode");
								   args[4]="0";
								   args[5]="0";
								   args[6]="Y";
							   	   this.mytemplate.update("insert into LOGIN_RSAUSER_DETAILS (LOGIN_ID, BROKER_CODE, PRODUCT_ID,BRANCH_CODE, AMEND_ID, "
							   	   		+ "COMMISSION,ENTRY_DATE, START_DATE,END_DATE, STATUS) values (?,?,?,?,?,?,sysdate,sysdate,sysdate+365,?)",args);
							   }
						   }
					   	}
					   }
				   }
			   }
		   }catch (Exception e) {
         e.printStackTrace();
	}
  }
  public void updateIncludeBrokers(UnderwriterMgtBean bean){
	try{
		if(bean.getBrokerCode()!=null && bean.getStatusChk()!=null){
			   for(int i=0;i<bean.getStatusChk().size();i++){
				   if(bean.getStatusChk().get(i)){
					   String productList = bean.getProductId().get(i);
					   if(StringUtils.isNotBlank(productList)) {
						   String[] prodArr = productList.split(",");
						   if(prodArr!=null && prodArr.length>0) {
							   this.mytemplate.update("delete from LOGIN_RSAUSER_DETAILS where broker_code=? and login_id=? and product_id in ('"+(StringUtils.join(prodArr,"','"))+"')",new Object[]{bean.getBrokerCode().get(i),bean.getLoginId()});
						   }
					   }
				   }
			   }
		}
	}catch(Exception e){
		e.printStackTrace();
	}
  }
}