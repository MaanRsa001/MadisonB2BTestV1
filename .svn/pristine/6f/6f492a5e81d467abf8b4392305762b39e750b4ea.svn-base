package com.maan.Office.DAO;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;
import com.maan.DBCon.DBConnection;

import com.maan.services.util.runner;
//import com.maan.sql.runner;
public class scheduleBean {
	
	OfficeShieldBean OFB=new OfficeShieldBean();
	 private int scheme_id=0,quote_no=0,cont_type_id=0;
	 private String product_id="",activity_prof_exc="",refCover_ids="",branch_code="";

 
public int getCont_type_id() {
	return cont_type_id;
}

public void setCont_type_id(int cont_type_id) {
	this.cont_type_id = cont_type_id;
}

public String getProduct_id() {
	return product_id;
}

public void setProduct_id(String product_id) {
	this.product_id = product_id;
}

public int getScheme_id() {
	return scheme_id;
}

public void setScheme_id(int scheme_id) {
	this.scheme_id = scheme_id;
}

public int getQuote_no() {
	return quote_no;
}

public void setQuote_no(int quote_no) {
	this.quote_no = quote_no;
}

public String getActivity_prof_exc() {
	return activity_prof_exc;
}

public void setActivity_prof_exc(String activity_prof_exc) {
	this.activity_prof_exc = activity_prof_exc;
}
public String getRefCover_ids() {
	return refCover_ids;
}
public void setRefCover_ids(String refCover_ids) {
	this.refCover_ids = refCover_ids;
}

public String getBranch_code() {
	return branch_code;
}
public void setBranch_code(String branch_code) {
	this.branch_code = branch_code;
}

	public String[][] getQuoteInfo(String qno) {
		String[][] result = new String[0][0];
		String values[]=new String[2];
		values[0]=qno;
		values[1]=qno;
		String query="select product_id,scheme_id,content_type_id from home_position_master where quote_no in(?) and amend_id in (select max(amend_id) from home_position_master where quote_no in(?))";
		System.out.println("the query" + query);
		try {
			result = new runner().multipleSelection(query,values);
			} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;
}
	
	
	public String[][] getMainCoverageWithQuote() {
		String[][] result = new String[0][0];
		String[][] amendval = new String[0][0];
		String values[]=new String[4];
		String query = "",temp_qry="";
		int amend_id = 0;
		try {
			values[0]=""+quote_no;
            values[1]=product_id;
			values[2]=""+scheme_id;
			values[3]=""+cont_type_id;
			query = "select max(amend_id) from OFS_DATA_DETAILS where quote_no=? and product_id=? and scheme_id=? and CONTENT_TYPE_ID=?";
			//System.out.println("the query" + query);
			amendval = new runner().multipleSelection(query,values);
			
			if(amendval.length>0 && amendval[0][0]!=null)
			{
				amend_id=Integer.parseInt(amendval[0][0]);
			}
			else
				amend_id=0;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		try {
			if(activity_prof_exc.length()>0)
                temp_qry="and b.coverages_id not in ("+activity_prof_exc+")";

			query="select a.COVERAGES_ID,a.COVERAGES_NAME,b.COVERAGES_TYPE,a.COVERAGES_DISPLAY_NAME,b.DISPLAY_ORDER," +
					"b.SUB_COVERAGES,b.CONTROL_TYPE,c.COVERAGES_SUMINSURED,c.COVERAGES_BASE_RATE,b.HELP_CONTENTS_ID,C.COVERAGES_Y_N_OPTION," +
					"b.CALC_TYPE,b.SUM_INSURED_LIMIT,b.SUM_INSURED_CONTROL_TYPE,a.SECTION_DETAILS,b.EXCESS from  OFS_MASTER a,OFS_COVERAGES_MASTER b, OFS_DATA_DETAILS c " +
					//"where a.COVERAGES_ID=b.COVERAGES_ID and c.CONTENT_TYPE_ID="+cont_type_id+" and b.CONTENT_TYPE_ID=c.CONTENT_TYPE_ID " +
					"where a.COVERAGES_ID=b.COVERAGES_ID and b.CONTENT_TYPE_ID=c.CONTENT_TYPE_ID " +
					"and a.COVERAGES_ID=c.COVERAGES_ID and b.status='Y' and c.quote_no="+quote_no+" and c.amend_id in ("+amend_id+") and " +
					"c.product_id="+product_id+" and c.scheme_id="+scheme_id+" "+temp_qry+" and b.scheme_id=c.scheme_id and b.amend_id||b.coverages_id in " +
					//" (select max(amend_id)||coverages_id from OFS_COVERAGES_MASTER where CONTENT_TYPE_ID="+cont_type_id+" and product_id="+product_id+" and scheme_id="+scheme_id+" group by coverages_id) and ( to_date((select to_char(inception_date,'dd-mm-yyyy') from home_position_master where quote_no="+quote_no+"),'dd-mm-yyyy') between b.effective_date and b.expiry_date) order by b.display_order";
					" (select max(amend_id)||coverages_id from OFS_COVERAGES_MASTER b1 where b1.CONTENT_TYPE_ID=b.CONTENT_TYPE_ID and product_id="+product_id+" and scheme_id="+scheme_id+" group by coverages_id) and ( to_date((select to_char(inception_date,'dd-mm-yyyy') from home_position_master where quote_no="+quote_no+"),'dd-mm-yyyy') between b.effective_date and b.expiry_date) order by b.display_order";
			//System.out.println("the query" + query);
			result = new runner().multipleSelection(query,new String[0]);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return result;

	}
	
	public String[][] getSubCoverageWithQuote(){
		String[][] result = new String[0][0];
		String[][] amendval = new String[0][0];
		String query = "",temp_qry="";
		int amend_id=0;
		String values[]=new String[4];
		try {
			values[0]=""+quote_no;
            values[1]=product_id;
			values[2]=""+scheme_id;
			values[3]=""+cont_type_id;
			String qry = "select max(amend_id) from OFS_DATA_SUB_DETAILS where quote_no=? and product_id=? and scheme_id=? and CONTENT_TYPE_ID=?";
			//System.out.println("the query" + qry);
			amendval = new runner().multipleSelection(qry,values);
			
			
			if(amendval.length>0 && amendval[0][0]!=null)
			{
				amend_id = Integer.parseInt(amendval[0][0]);
			}
			else
				amend_id=0;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		try {
			if(activity_prof_exc.length()>0)
                temp_qry="and a.coverages_id not in ("+activity_prof_exc+")";

			query = "(select a.COVERAGES_SUB_ID,a.COVERAGES_ID,b.COVERAGES_DISPLAY_NAME,a.SUB_COVERAGES_LIMIT,c.COVERAGES_SUB_BASE_RATE," +
					"a.PRODUCT_ID,a.SUB_CONTROL_TYPE,a.SUB_DISPLAY_ORDER,a.CALC_TYPE,c.COVERAGES_COVERED_EMPLOYEES," +
					"a.SUM_INSURED_LIMIT,c.premium_amount from OFS_COVERAGES_SUB_MASTER a ,OFS_MASTER b,OFS_DATA_SUB_DETAILS c where " +
					"a.COVERAGES_SUB_ID=c.COVERAGES_SUB_ID and a.COVERAGES_SUB_ID=b.COVERAGES_ID and c.quote_no="+quote_no+" and c.amend_id " +
					"in("+amend_id+") and a.amend_id||a.coverages_id||a.coverages_sub_id in (select max(amend_id)||coverages_id||coverages_sub_id from OFS_COVERAGES_SUB_MASTER where CONTENT_TYPE_ID="+cont_type_id+" and product_id="+product_id+" and scheme_id="+scheme_id+" group by coverages_id,coverages_sub_id) and c.CONTENT_TYPE_ID=a.CONTENT_TYPE_ID and c.CONTENT_TYPE_ID="+cont_type_id+" and c.product_id="+product_id+" and c.scheme_id="+scheme_id+" and a.scheme_id=c.scheme_id)" +
					"union(select a.COVERAGES_SUB_ID,a.COVERAGES_ID,b.COVERAGES_DISPLAY_NAME,a.SUB_COVERAGES_LIMIT,a.SUB_RATE," +
					"a.PRODUCT_ID,a.SUB_CONTROL_TYPE,a.SUB_DISPLAY_ORDER,a.CALC_TYPE,a.REMARKS,a.SUM_INSURED_LIMIT,a.amend_id from " +
					"OFS_COVERAGES_SUB_MASTER a,OFS_MASTER b where a.CONTENT_TYPE_ID="+cont_type_id+" and a.product_id="+product_id+" and a.scheme_id="+scheme_id+"" +
					" and a.status='Y' and a.COVERAGES_SUB_ID=b.COVERAGES_ID "+temp_qry+" and a.amend_id||a.coverages_id||a.coverages_sub_id in (select max(amend_id)||coverages_id||coverages_sub_id from OFS_COVERAGES_SUB_MASTER where CONTENT_TYPE_ID="+cont_type_id+" and product_id="+product_id+" and scheme_id="+scheme_id+" group by coverages_id,coverages_sub_id) and ( to_date((select to_char(inception_date,'dd-mm-yyyy') from home_position_master where quote_no="+quote_no+"),'dd-mm-yyyy') between a.effective_date and a.expiry_date) and a.COVERAGES_SUB_ID in (select coverages_sub_id " +
					"from OFS_COVERAGES_SUB_MASTER where CONTENT_TYPE_ID="+cont_type_id+" and product_id="+product_id+" and scheme_id="+scheme_id+" and coverages_sub_id " +
					"not in(select coverages_sub_id from OFS_DATA_SUB_DETAILS where quote_no="+quote_no+" and CONTENT_TYPE_ID="+cont_type_id+" and " +
					"product_id="+product_id+" and scheme_id="+scheme_id+" and amend_id in("+amend_id+"))) ) ";
			//System.out.println("the query" + query);
			result = new runner().multipleSelection(query,new String[0]);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return result;

	}



	 public String[][] getCustomerInfo() {
		String result[][] =new String[0][0];
		String query = "";
		String values[]=new String[2];
		values[0]=""+quote_no;
        values[1]=""+quote_no;
		try {
			
			//query = "select a.quote_no,c.title,(c.first_name||c.company_name),c.last_name,a.address1,c.address2,d.content_description,(case when a.prof_others is not null then (f.profession||'-'||a.prof_others) else f.profession end),(case when a.freezone_others is not null then (g.freezone_description||'-'||a.freezone_others) else g.freezone_description end),a.INSURED_ADDRESS_DIFFERENT,a.ADDRESS1,a.POBOX,initcap(a.COUNTRY),initcap(a.EMIRATE),to_char(e.EFFECTIVE_DATE,'DD-MM-YYYY'),to_char(e.EXPIRY_DATE,'DD-MM-YYYY'),i.company_name,e.admin_remarks,c.emirate,c.country,c.pobox,to_char(e.policy_date,'DD-MM-YYYY') from OFS_DATA a,OFS_ACT_PROF_MASTER b,PERSONAL_INFO c,OFS_CONTENT_MASTER d,HOME_POSITION_MASTER e,OFS_ACT_PROF_MASTER f,OFS_FREEZONE_MASTER g,LOGIN_MASTER h,BROKER_COMPANY_MASTER i where a.quote_no=? and a.amend_id in (select max(amend_id) from OFS_DATA where quote_no=?) and a.activity_profession=b.activity_profession and a.customer_id=c.customer_id and a.content_value=d.content_type_id  and a.quote_no=e.quote_no and d.product_id=e.product_id and d.scheme_id=e.scheme_id and d.content_type_id=e.content_type_id and a.activity_profession=f.activity_profession and a.freezone=g.freezone and e.login_id=h.login_id and h.oa_code=i.agency_code";
			query ="select a.quote_no,c.title,(c.first_name||c.company_name),c.last_name,a.address1,c.address2,nvl((select d.content_description from OFS_CONTENT_MASTER d where a.content_value=d.content_type_id and d.product_id=e.product_id and D.SCHEME_ID = E.SCHEME_ID and d.content_type_id=e.content_type_id),' '),(case when a.prof_others is not null then (nvl((select f.profession from OFS_ACT_PROF_MASTER f where a.activity_profession=f.activity_profession and F.SCHEME_ID = e.SCHEME_ID),' ')||'-'||a.prof_others) else nvl((select f.profession from OFS_ACT_PROF_MASTER f where a.activity_profession=f.activity_profession and F.SCHEME_ID = e.SCHEME_ID),' ') end),(case when a.freezone_others is not null then (nvl((select g.freezone_description from OFS_FREEZONE_MASTER g where a.freezone=g.freezone),' ')||'-'||a.freezone_others) else nvl((select g.freezone_description from OFS_FREEZONE_MASTER g where a.freezone=g.freezone),' ') end),a.INSURED_ADDRESS_DIFFERENT,a.ADDRESS1,a.POBOX,initcap(a.COUNTRY),initcap(a.EMIRATE),to_char(e.EFFECTIVE_DATE,'DD-MM-YYYY'),to_char(e.EXPIRY_DATE,'DD-MM-YYYY'),i.company_name,e.admin_remarks,c.emirate,c.country,c.pobox,to_char(e.policy_date,'DD-MM-YYYY') from OFS_DATA a,PERSONAL_INFO c,HOME_POSITION_MASTER e,LOGIN_MASTER h,BROKER_COMPANY_MASTER i where a.quote_no=? and a.amend_id in (select max(amend_id) from OFS_DATA where quote_no=?) and a.customer_id=c.customer_id and a.quote_no=e.quote_no and e.login_id=h.login_id and h.oa_code=i.agency_code";
			//System.out.println("the query" + query);
			result = new runner().multipleSelection(query,values);
			
			} catch (Exception ex) {
			ex.printStackTrace();
		    }
			
			return result;
	 }

public String[][] getContentID(int quote_no,String linkfrom) {
		String result[][] =new String[0][0];
		String value[][] =new String[0][0];
		String query = "",refStatus=" ";
		String values[]=new String[2];
		values[0]=""+quote_no;
        values[1]=""+quote_no;
		try {
			
			query = "select a.content_value,a.activity_profession,b.referral_status,a.activity_status,b.COVERAGES_IDS from OFS_DATA a,OFS_ACT_PROF_MASTER b where a.quote_no=? and amend_id in (select max(amend_id) from OFS_DATA where quote_no=?) and a.activity_profession=b.activity_profession"; 
			//System.out.println("the query" + query);
			result = new runner().multipleSelection(query,values);
			
			} catch (Exception ex) {
			ex.printStackTrace();
		    }
			
			return result;
	 }


 public String[] getQuoteDataInfo(String activity_profession) {
		String[] result = new String[3];
		String val = "";
		String query = "";
		int amend_id = 0;
		String values[]=new String[4];
		try {
			values[0]=""+quote_no;
            values[1]=product_id;
			values[2]=""+scheme_id;
			values[3]=""+cont_type_id;
			query = "select max(amend_id) from OFS_DATA_DETAILS where quote_no=? and product_id=? and scheme_id=? and CONTENT_TYPE_ID=?";
			//System.out.println("the query" + query);
			val = new runner().singleSelection(query,values);
			
			if(val==null)
				result[0]="N";
			else
			     {    
			        result[0]=val;
					values=new String[3];
					values[0]=""+quote_no;
                    values[1]=product_id;
			        values[2]=""+scheme_id;
			         
					query = "select premium from HOME_POSITION_MASTER where quote_no=? and product_id=? and scheme_id=?";
			        //System.out.println("the query" + query);
			         val = new runner().singleSelection(query,values);
					 result[2]=val;

				 }

			//System.out.println("Valueeeeeeeeeeeeeeeeeeeeeeeeee"+result[0]);
			} catch (Exception ex) {
			ex.printStackTrace();		}
			try {
			values=new String[4];
			values[0]=""+cont_type_id;
            values[1]=product_id;
			values[2]=""+scheme_id;
			values[3]=activity_profession;
			query = "select coverages_id from OFS_ACT_PROF_EXCLUSION_MASTER where CONTENT_TYPE_ID=? and product_id=? and scheme_id=? and activity_profession=? and exclusion_status='Y'";
			//System.out.println("the query" + query);
			val = new runner().singleSelection(query,values);
			//System.out.println("Valueeeeeeeeeeeeeeeeeeeeeeeeee"+val);
			result[1]=val;
			} catch (Exception ex) {
			ex.printStackTrace();		}
			return result;
	   }


      public String[][] getCoverageInfo() {
		String[][] result = new String[0][0];
		String query = "",temp_qry="";
		String values[]=new String[2];
		
		try {
			values[0]=product_id;
            values[1]=""+scheme_id;
			//values[2]=""+quote_no;
			query="select distinct(a.coverages_id),b.coverages_name from OFS_COVERAGES_MASTER a ,OFS_MASTER b,OFS_DATA_DETAILS c where a.coverages_id=b.coverages_id and a.product_id=? and a.scheme_id=? and c.quote_no in("+quote_no+") and a.coverages_id=c.coverages_id order by a.coverages_id";
			//"select distinct(a.coverages_id),b.coverages_name from OFS_COVERAGES_MASTER a ,OFS_MASTER b where a.coverages_id=b.coverages_id and a.product_id=? and scheme_id=?";
			//System.out.println("the query" + query);
			result = new runner().multipleSelection(query,values);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return result;

	}


	 public String[][] getExtensionInfo() {
		String[][] result = new String[0][0];
		String query = "",temp_qry="";
		String values[]=new String[3];
		
		try {
			values[0]=product_id;
			values[1]=""+scheme_id;
			values[2]=""+cont_type_id;

			query="select coverages_id,EXTENSION_ID,EXTENSION_DESCRIPTION from OFS_EXTENSION_MASTER where product_id=? and scheme_id=? and CONTENT_TYPE_ID=? order by coverages_id,extension_id"; 
			//System.out.println("the query" + query);
			result = new runner().multipleSelection(query,values);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return result;

	}


	 public int getPdfBrokerStatus(String policy_number) {
		String result = new String();
		String query = "",temp_qry="";
		int sts=0;
		String values[]=new String[3];
		try {
			values[0]=product_id;
			values[1]=""+scheme_id;
			values[2]=policy_number;

			query="select distinct(nvl(PDF_BROKER_STATUS,0))  from home_position_master where product_id=? and scheme_id=? and policy_no=?";
			//System.out.println("the query" + query);
			result = new runner().singleSelection(query,values);
            sts=(Integer.parseInt(result));
			new runner().updation("update home_position_master set PDF_BROKER_STATUS="+(sts+1)+" where product_id="+product_id+" and scheme_id="+scheme_id+" and policy_no='"+policy_number+"'");
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return sts;

	}

	            
	public String getPolicyNumber(String quote_no,int Qnolength)
	{
		String temp = "sysdate";
		temp = runner.getSysdate(runner.getuserHomeBranch(quote_no));		
		String PolicyNo="";
		String values[]=new String[1];
		values[0]=product_id;
				 PolicyNo	=	new runner().singleSelection("select policy_no from home_position_master where quote_no in("+quote_no+") and PRODUCT_ID=? and policy_no is not null",values);
				if(PolicyNo.equalsIgnoreCase(""))
				{
					String remarksPol	= PolicyGeneration(quote_no);
					if(Qnolength==1)
						new runner().updation("update home_position_master set policy_no='"+remarksPol+"',status='P',PAYMENT_MODE=null,INCEPTION_DATE="+temp+",POLICY_DATE="+temp+"  where quote_no in("+quote_no+") and PRODUCT_ID='"+product_id+"'");
						else
         					new runner().updation("update home_position_master set policy_no='"+remarksPol+"',status='P',PAYMENT_MODE=null,declaration_status='LINKED',INCEPTION_DATE="+temp+",POLICY_DATE="+temp+" where quote_no in("+quote_no+") and PRODUCT_ID='"+product_id+"'");
					return remarksPol;
				}else
					return PolicyNo;
		
	}

	public String getPolicyDate(String policy_no,String product_id)
	{
		String PolicyDate[][]=new String[0][0];
		 PolicyDate	=	new runner().multipleSelection("select to_char(policy_date,'DD-MM-YYYY') from home_position_master where policy_no in('"+policy_no+"') and PRODUCT_ID='"+product_id+"' and policy_no is not null");
		
		if(PolicyDate.length>0)
		return PolicyDate[0][0];
		else
			return "date not updated";
	}
	

	public  synchronized String PolicyGeneration(String quote_no)
	{
		String current_no="";
		String referal="";
		String policyNo="";
		String sql	=	new String();
		String values[]=new String[0];
		
		try
		{
			    values = new String[2];
			     values[0]=product_id;
		         values[1]=""+scheme_id;
				sql="select status,policy_no from home_position_master where quote_no in("+quote_no+") and PRODUCT_ID=? and scheme_id=? ";
				String[][] fromPosition=new runner().multipleSelection(sql,values);
				fromPosition[0][0]=fromPosition[0][0]==null?"Y":fromPosition[0][0];
				String newSql = "";
				String newSql1 = "";
				if(fromPosition[0][0].equalsIgnoreCase("Y"))
				{
					String branchCode = "";
					String typeId = "";
					String branchCode2[][] = new String[0][0];
					
					try
					{
						values=new String[6];
						values[0]=branch_code;
						values[1]=product_id;
						values[2]=branch_code;
						values[3]=branch_code;
						values[4]=product_id;
						values[5]=branch_code;
						

						newSql = "select nvl(max(current_no)+1,max(start_no)) from policyno_generate where type_id=(select POLICY_TYPE_ID from BRANCH_DETAIL where BRANCH_CODE=? and PRODUCT_ID=?) and BRANCH_CODE=? and status='Y' and amend_id=(select max(amend_id) from policyno_generate where type_id=(select POLICY_TYPE_ID from BRANCH_DETAIL where BRANCH_CODE=? and PRODUCT_ID=?) and branch_code=?)";
							current_no=new runner().singleSelection(newSql,values);
					}
					catch(Exception e)
					{
						System.out.println("Exception in getting current no"+e.toString());
					}
					try 
					{
						values=new String[6];
						values[0]=branch_code;
						values[1]=branch_code;
						values[2]=product_id;
						values[3]=branch_code;
						values[4]=product_id;
						values[5]=branch_code;
						branchCode2 = new runner().multipleSelection("SELECT POLICY_PREFIX,BRANCH_CODE from POLICYNO_GENERATE where  branch_code=? and type_id=(select POLICY_TYPE_ID from BRANCH_DETAIL where BRANCH_CODE=? and PRODUCT_ID=?) and status='Y' and amend_id=(select max(amend_id)from policyno_generate where type_id=(select POLICY_TYPE_ID from BRANCH_DETAIL where BRANCH_CODE=? and PRODUCT_ID=?) and branch_code=?)",values);
						
					}
					catch(Exception e)
					{			
					}
					String remark = "";
					if(branchCode2.length>0)
						remark = branchCode2[0][0]+"/"+branchCode2[0][1]+"/"+current_no;
						newSql1 = "update policyno_generate set current_no='"+current_no+"',remarks='"+remark+"' where type_id=(select POLICY_TYPE_ID from BRANCH_DETAIL where BRANCH_CODE='"+branch_code+"' and PRODUCT_ID='"+product_id+"') and status='Y' and branch_code='"+branch_code+"' and amend_id=(select max(amend_id) from policyno_generate where type_id=(select POLICY_TYPE_ID from BRANCH_DETAIL where BRANCH_CODE='"+branch_code+"' and PRODUCT_ID='"+product_id+"') and branch_code='"+branch_code+"')";
					new runner().updation(newSql1);
					current_no=remark;

				}
				else
					current_no=fromPosition[0][1];
		}catch(Exception e)
		{
			System.out.println("ERROR in PolicyGeneration in DATACOLLECTION  "+e.toString());
		}
		//System.out.println("   DATACOLLECTION PolicyGeneration  "+current_no);

		return current_no;
	}

public String getDebitNumber(String quote_no)
	{
	String temp = "sysdate";
	temp = runner.getSysdate(runner.getuserHomeBranch(quote_no));		
	String debit_note_no = "";
			 String values[]=new String[0];
			 values = new String[2];
			 values[0]=product_id;
			 values[1]=""+scheme_id;
                debit_note_no	=	new runner().singleSelection("select debit_note_no from home_position_master where quote_no in("+quote_no+") and PRODUCT_ID=? and debit_note_no is not null and scheme_id=?",values);

				if(debit_note_no.equalsIgnoreCase(""))
				{
					debit_note_no	=	getMaxDebitNo();
					values[0] = debit_note_no;
					values[1] = product_id;
			        
					new runner().multipleUpdation("update home_position_master set debit_note_no=?,debit_note_date=(select "+temp+" from dual) where quote_no in("+quote_no+") and PRODUCT_ID=?",values);
				}
				return debit_note_no;
	}

	public synchronized String getMaxDebitNo()
	{
		String current_no=null;
		String debiSql="";
		String debiUpdateSql="";
		String branchCode="";
		String typeId = "";
		try
		{	
			    String values[]=new String[6];
				values[0]=branch_code;
				values[1]=product_id;
				values[2]=branch_code;
				values[3]=branch_code;
				values[4]=product_id;
				values[5]=branch_code;
			  
				debiSql = "select nvl(max(current_no)+1,max(start_no)) from policyno_generate where type_id=(select DEBIT_TYPE_ID from BRANCH_DETAIL where BRANCH_CODE=? and PRODUCT_ID=?) and status='Y' and branch_code=? and amend_id=(select max(amend_id) from policyno_generate where type_id=(select DEBIT_TYPE_ID from BRANCH_DETAIL where BRANCH_CODE=? and PRODUCT_ID=?) and branch_code=?)";
				current_no=new runner().singleSelection(debiSql,values);

				debiUpdateSql = "update policyno_generate set current_no='"+current_no+"',remarks='"+current_no+"' where type_id=(select DEBIT_TYPE_ID from BRANCH_DETAIL where BRANCH_CODE='"+branch_code+"' and PRODUCT_ID='"+product_id+"') and status='Y' and branch_code='"+branch_code+"' and amend_id=(select max(amend_id) from policyno_generate where type_id=(select DEBIT_TYPE_ID from BRANCH_DETAIL where BRANCH_CODE='"+branch_code+"' and PRODUCT_ID='"+product_id+"') and branch_code='"+branch_code+"')";
			new runner().updation(debiUpdateSql);
		}
		catch(Exception e)
		{
			System.out.println("ERROR in get MaxDebitNo in getMax DebitNo  "+e.toString());
		}
		return current_no;
	}


	public void updateInsSDateEDate(String Quotenos[],String sdate,String edate)
	{
	   String qnos="",debiUpdateSql="";
	   String values[]=new String[5];
		try
		{	
			if(sdate.length()>0 && edate.length()>0)
			{
			for(int i=0;i<Quotenos.length;i++)
               qnos+=(Quotenos[i]+",");
			 	qnos=qnos.substring(0,(qnos.length()-1));	
			values[0]=sdate;
			values[1]=edate;
			values[2]=qnos;
			values[3]=product_id;
			values[4]=""+scheme_id;

			debiUpdateSql = "update HOME_POSITION_MASTER set EFFECTIVE_DATE=to_date(?,'DD-MM-YYYY'),EXPIRY_DATE=to_date(?,'DD-MON-YYYY') where quote_no in(?) and product_id=? and scheme_id=?";
			
			new runner().multipleUpdation(debiUpdateSql,values);
			
			}
		}
		catch(Exception e)
		{
			System.out.println("ERROR in get MaxDebitNo in getMax DebitNo  "+e.toString());
		}
		
	}


public String[][] getUploadedFileList(String status) {
		String[][] result = new String[0][0];
		String[][] amendval = new String[0][0];
		String[] values=new String[4];
		String query = "",temp_qry="",temp_qry1="";
		int amend_id = 0;
		try {
			values[0]=""+quote_no;
			values[1]=product_id;
			values[2]=""+scheme_id;
			values[3]=""+cont_type_id;
			
			query = "select max(amend_id) from OFS_DATA_DETAILS where quote_no=? and product_id=? and scheme_id=? and CONTENT_TYPE_ID=?";
			//System.out.println("the query" + query);
			amendval = new runner().multipleSelection(query,values);
			
			if(amendval.length>0 && amendval[0][0]!=null)
			{
				amend_id=Integer.parseInt(amendval[0][0]);
			}
			else
				amend_id=0;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		try {
			if(activity_prof_exc.length()>0)
                temp_qry="and b.coverages_id not in ("+activity_prof_exc+")";
         

		  if(status.equalsIgnoreCase("main"))
			  temp_qry1="select a.COVERAGES_ID,a.COVERAGES_DISPLAY_NAME,b.DISPLAY_ORDER,b.upload_option,d.customer_file_name";
           else
               temp_qry1="select distinct(a.COVERAGES_ID),a.COVERAGES_DISPLAY_NAME,b.DISPLAY_ORDER,b.upload_option ";

			query=temp_qry1+" from  OFS_MASTER a,OFS_COVERAGES_MASTER b, OFS_DATA_DETAILS c,OFS_UPLOADED_DATA d " +
					"where a.COVERAGES_ID=b.COVERAGES_ID and c.CONTENT_TYPE_ID="+cont_type_id+" and b.CONTENT_TYPE_ID=c.CONTENT_TYPE_ID " +
					"and a.COVERAGES_ID=c.COVERAGES_ID and b.status='Y' and c.quote_no="+quote_no+" and c.amend_id in ("+amend_id+") and " +
					"c.product_id="+product_id+" and c.scheme_id="+scheme_id+" "+temp_qry+" and b.scheme_id=c.scheme_id and b.amend_id||b.coverages_id in (select max(amend_id)||coverages_id from OFS_COVERAGES_MASTER where CONTENT_TYPE_ID="+cont_type_id+" and product_id="+product_id+" and scheme_id="+scheme_id+" group by coverages_id) and ( to_date((select to_char(inception_date,'dd-mm-yyyy') from home_position_master where quote_no="+quote_no+"),'dd-mm-yyyy') between b.effective_date and b.expiry_date)  and d.quote_no="+quote_no+" and a.COVERAGES_ID=d.COVERAGES_ID and c.CONTENT_TYPE_ID=d.CONTENT_TYPE_ID and c.scheme_id=d.scheme_id and d.status='Y' order by b.display_order";
			//System.out.println("the query" + query);
			result = new runner().multipleSelection(query,new String[0]);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return result;

	}

    public String[][] getUpdatedValues(String status,String qno)
	{
		String qry="";
		String[][] result = new String[0][0];
		try {
//2-portable 8-fg 9-travel 10-gpa
			if(status.equalsIgnoreCase("2"))
			qry = "select MAKE_YEAR,EQUIPMENT_DESCRIPTION,REPLACEMENT_VALUE from OFS_PORTABLE_EQUIPMENT where quote_no="+qno+" and amend_id in(select max(amend_id) from OFS_PORTABLE_EQUIPMENT where quote_no="+qno+" and coverages_id="+status+")";
			else if(status.equalsIgnoreCase("10"))
			qry = "select NAME,to_char(dob,'dd-mm-yyyy'),DESIGNATION,ANNUAL_SALARY from OFS_PERSONAL_ACCIDENT  where quote_no="+qno+" and amend_id in(select max(amend_id) from OFS_PERSONAL_ACCIDENT where quote_no="+qno+" and coverages_id="+status+")";
			else if(status.equalsIgnoreCase("9"))
			qry = "select name,DESIGNATION from OFS_TRAVEL_BAGGAGE where quote_no="+qno+" and amend_id in(select max(amend_id) from OFS_TRAVEL_BAGGAGE where quote_no="+qno+" and coverages_id="+status+")";
			else if(status.equalsIgnoreCase("8"))
			qry = "select name,DESIGNATION from OFS_FIDELITY_GUARANTEE where quote_no="+qno+" and amend_id in(select max(amend_id) from OFS_FIDELITY_GUARANTEE where quote_no="+qno+" and coverages_id="+status+")";
			//System.out.println("the query" + qry);
			result = new runner().multipleSelection(qry,new String[0]);
			
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;
	  }

	  public void updateDebitCommission(String Quotenos[],String product_id,String scheme_id)
	{
		String temp = "sysdate";
		temp = runner.getSysdate(branch_code);		
		String qnos="",debiUpdateSql="",commis="",trckingsql="";
		try
		{	
			for(int i=0;i<Quotenos.length;i++)
			{
			   commis = getCommision(Quotenos[i], product_id,scheme_id);
			   if(commis.length() > 0 )
			   {
					debiUpdateSql = "update HOME_POSITION_MASTER set COMMISSION='"+commis+"' where quote_no ='"+Quotenos[i]+"' and policy_no is not null and product_id="+product_id+" and scheme_id="+scheme_id+" and status='P'";
				    new runner().updation(debiUpdateSql);
					System.out.println("updateDebitCommission "+debiUpdateSql);
					trckingsql = "update TRACKING_MASTER set END_TIME="+temp+",REMARKS='Policy',POLICY_NO=(select policy_no from home_position_master where quote_no='"+Quotenos[i]+"') where quote_no ='"+Quotenos[i]+"'";
					new runner().updation(trckingsql);
					System.out.println("trckingsql  "+debiUpdateSql);
			   }
			}
            //qnos+=(Quotenos[i]+",");
			//qnos=qnos.substring(0,(qnos.length()-1));	
		}
		catch(Exception e)
		{
			System.out.println("ERROR in updateDebitCommission in "+e.toString());
			e.printStackTrace();
		}
	}
	
	public String getCommision(String quoteNo, String pid,String schemeId)
	{
		String commision[][] = new String[0][0];
		String sql = "";
		double commis = 0.00;
		double premium = 0.00;
		double totcom = 0.00;
		String debitComis = "";
		String[] values=new String[4];
		try
		{
			values[0]=quoteNo;
			values[1]=pid;
			values[2]=schemeId;
			values[3]=quoteNo;

			sql = "select nvl(lud.commission,'0'),nvl(home.overall_premium,'0'),lud.scheme_id  from login_user_details lud,home_position_master home where lud.agency_code=(select oa_code from login_master where login_id=(select login_id from home_position_master where quote_no=?) and status='Y') and lud.status='Y' and lud.product_id=? and lud.scheme_id =? and home.quote_no=?";
			commision = runner.multipleSelection(sql,values);

			if(commision.length > 0)
			{
				commis = Double.parseDouble(commision[0][0]);
				premium = Double.parseDouble(commision[0][1]);
					if(commis != 0 && premium != 0 )
						totcom = premium*(commis/100);
			}
			System.out.println("Commision"+commis);
			System.out.println("premium"+premium);
			System.out.println("totcom"+totcom);
			System.out.println("totcom premium*(commis/100) "+premium*(commis/100));
		}
		catch(Exception e)
		{
			System.out.println("Exception in getting getCommision OSB"+e.toString());
			e.printStackTrace();
		}
		
		if(totcom != 0 )
			debitComis = ""+totcom;

		return debitComis;
	}
//	getting images from table - Karthick
	public String[][] getImages(){
		String[][] result = new String[0][0];
		String[] args = new String[]{branch_code,product_id,branch_code,product_id};
		if(branch_code!=null && !"".equalsIgnoreCase(branch_code)){
			result = runner.multipleSelection("select header_img,footer_img,sign_img,stamp_img from branch_detail where branch_code=? and product_id=? and amend_id=(select max(amend_id) from branch_detail where branch_code=? and product_id=?)",args);
		}
		return result;
	}
	//Added by chinna
	public HashMap getCoverageDetails(String quoteNo,String loginId, String proId, String shemeID)
	{
		OfficeShieldBean OSB=new OfficeShieldBean();
		runner run = new runner();
		String info[][] = new String[0][0];
		String result[][] = new String[0][0];
		HashMap list=new HashMap();
		HashMap coverValues=new HashMap();
		LinkedHashMap columnNames=new LinkedHashMap();
		ArrayList values=new ArrayList();
		String subcoverid="0";
		String contId="0";
		String q="";
		
		try
		{
			q="select ofm.coverages_display_name,odd.COVERAGES_id from ofs_master ofm,ofs_data_details odd where odd.coverages_id=ofm.coverages_id and ofm.coverages_id in (select COVERAGES_ID from OFS_DATA_DETAILS where quote_no=? and amend_id||PRODUCT_ID||SCHEME_ID||CONTENT_TYPE_ID in (select max(amend_id)||PRODUCT_ID||SCHEME_ID||CONTENT_TYPE_ID from OFS_DATA_DETAILS where quote_no= ? and PRODUCT_ID =? and SCHEME_ID =? and COVERAGES_Y_N_OPTION ='Y' group by PRODUCT_ID,SCHEME_ID,CONTENT_TYPE_ID ) and COVERAGES_Y_N_OPTION ='Y' group by PRODUCT_ID,SCHEME_ID,CONTENT_TYPE_ID,COVERAGES_ID ) and odd.status='Y' and odd.quote_no= ? and odd.PRODUCT_ID =? and odd.SCHEME_ID =? and odd.COVERAGES_Y_N_OPTION ='Y' and odd.content_type_id=? order by odd.COVERAGES_id";
			String args[]=new String[8];
			args[0]=quoteNo;
			args[1]=quoteNo;
			args[2]=proId;
			args[3]=shemeID;
			args[4]=quoteNo;
			args[5]=proId;
			args[6]=shemeID;
			args[7]=contId;
			info = runner.multipleSelection(q, args);
			System.out.print("Info======>>>"+info.length);	
			if(info!=null && info.length>0)
			{
				for(int i=0;i<info.length;i++)
				{
					String coverDetails[][]=OSB.getCoverDetails(info[i][1], subcoverid, proId, shemeID, contId);
					System.out.println("CoverDetails.length========in shedule=====: "+coverDetails.length);
					if(coverDetails!=null && coverDetails.length>0)
					{
						String ID=OSB.getMoreId(quoteNo, info[i][1], subcoverid, proId, shemeID, contId, "OFS_TRANSACTION_DETAILS");
						ID=ID==null?"0":ID;int moreID=Integer.parseInt(ID);
						for(int n=0; n<moreID; n++)
						{
							for(int j=0; j<coverDetails.length; j++)
							{
								String coverValue=OSB.getCoverValue(quoteNo, info[i][1], subcoverid, proId, shemeID, contId, coverDetails[j][4], "OFS_TRANSACTION_DETAILS", n+1);
								System.out.println("bean coverValue:=======>>>> "+coverValue);
								values.add(coverValue);
							}
							
							if(values.size()>0){System.out.println("info[i][0]: "+info[i][0]+" values.size: "+values.size());coverValues.put(info[i][0], values);}
							columnNames.put(info[i][0], coverDetails);
						}
						list.put(columnNames, coverValues);
						values=new ArrayList();
					}
				}
			}
			
			Set set=list.keySet();
			Iterator itr=set.iterator();
			while(itr.hasNext())
			{
				HashMap hm=(HashMap)itr.next();
				Set set2=hm.keySet();
				Iterator itr2=set2.iterator();
				while(itr2.hasNext())
				{
					System.out.println("Cover Names: ====="+itr2.next());
				}
			}
		}
		catch(Exception e)
		{
			System.out.println("uploadSummarySubmit2 ..."+e);
			e.printStackTrace();
		}
		return list;
	}
//	Added by chinna
	public String getInceptionDate(String quoteNo)
	{
		String date="";
		String q="select to_char(inception_date,'dd/mm/yyyy HH12:MI:SS AM') inception_date from home_position_master where quote_no="+quoteNo;
		date=runner.singleSelection(q);
		return date;
	}
}
