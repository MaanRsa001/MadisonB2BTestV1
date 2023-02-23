package com.maan.Office.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;
import com.maan.DBCon.DBConnection;
import com.maan.services.util.runner;
//import com.maan.sql.runner;
public class getCoverageInfo {
     
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

        public String[][] getQuoteInfo() {
		String[][] result = new String[0][0];
		String values[]=new String[2];
		values[0]=""+quote_no;
		values[1]=""+quote_no;
		String query="select product_id,scheme_id,content_type_id from home_position_master where quote_no=? and amend_id in (select max(amend_id) from home_position_master where quote_no=?)";
		System.out.println("the query" + query);
		try {
			result = new runner().multipleSelection(query,values);
			} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;
     }
        public String[][] getMainCoverageDetails(String linkfrom) {
    		String[][] result = new String[0][0];
    		String query = "", temp_qry = "", temp_qry_date = "";
    		try {
    			if (activity_prof_exc.length() > 0)
    				temp_qry = "and b.coverages_id not in (" + activity_prof_exc
    						+ ")";

    			if (!"admin".equalsIgnoreCase(linkfrom)
    					&& !"adminProceedInfo".equalsIgnoreCase(linkfrom)
    					&& !"adminCalculate".equalsIgnoreCase(linkfrom))
    				temp_qry_date = " and ( to_date((select to_char(inception_date,'dd-mm-yyyy') from home_position_master where quote_no="
    						+ quote_no
    						+ "),'dd-mm-yyyy') between b.effective_date and b.expiry_date) ";
    			
    			//Query Modified: ",b.COVERAGES_LIMIT" is added by chinna
    			query = "select a.COVERAGES_ID,a.COVERAGES_NAME,b.COVERAGES_TYPE,a.COVERAGES_DISPLAY_NAME,b.DISPLAY_ORDER,"
    					+ "b.SUB_COVERAGES,b.CONTROL_TYPE,(case when b.CONTENT_TYPE_ID=4 and b.COVERAGES_ID=1 then TO_CHAR(c.CONTENT_VALUE_OTHERS) else b.COVERAGES_LIMIT end),b.BASE_RATE,b.HELP_CONTENTS_ID,b.CALC_STATUS,"
    					+ "b.CALC_TYPE,b.SUM_INSURED_LIMIT,b.SUM_INSURED_CONTROL_TYPE,b.COVERAGES_LIMIT from OFS_MASTER a,OFS_COVERAGES_MASTER b,OFS_DATA c  where a.COVERAGES_ID=b.COVERAGES_ID "
    					+ "and b.CONTENT_TYPE_ID="
    					+ cont_type_id
    					+ " and b.product_id="
    					+ product_id
    					+ " and b.scheme_id="
    					+ scheme_id
    					+ "    and b.status='Y' and c.quote_no="
    					+ quote_no
    					+ " "
    					+ temp_qry
    					+ " and b.amend_id||b.coverages_id in (select max(amend_id)||coverages_id from OFS_COVERAGES_MASTER where CONTENT_TYPE_ID="
    					+ cont_type_id
    					+ " and product_id="
    					+ product_id
    					+ " and scheme_id="
    					+ scheme_id
    					+ " group by coverages_id)  "
    					+ temp_qry_date
    					+ " order by b.display_order";
    			System.out.println("the query" + query);
    			result = new runner().multipleSelection(query);
    		} catch (Exception ex) {
    			ex.printStackTrace();
    		}

    		return result;

    	}
	
	public String[][] getSubCoverageDetails(String linkfrom) {
		String[][] result = new String[0][0];
		String query = "",temp_qry="", temp_qry_date = "";
		try {
			if(activity_prof_exc.length()>0)
                temp_qry="and a.coverages_id not in ("+activity_prof_exc+")";

			if (!"admin".equalsIgnoreCase(linkfrom)
					&& !"adminProceedInfo".equalsIgnoreCase(linkfrom)
					&& !"adminCalculate".equalsIgnoreCase(linkfrom))
				temp_qry_date = " and ( to_date((select to_char(inception_date,'dd-mm-yyyy') from home_position_master where quote_no="
						+ quote_no
						+ "),'dd-mm-yyyy') between a.effective_date and a.expiry_date) ";
			
			query = "select a.COVERAGES_SUB_ID,a.COVERAGES_ID,b.COVERAGES_DISPLAY_NAME,a.SUB_COVERAGES_LIMIT,a.SUB_RATE," +
					"a.PRODUCT_ID,a.SUB_CONTROL_TYPE,a.SUB_DISPLAY_ORDER,a.CALC_TYPE,a.REMARKS,a.SUM_INSURED_LIMIT from " +
					"OFS_COVERAGES_SUB_MASTER a,OFS_MASTER b where a.COVERAGES_SUB_ID=b.COVERAGES_ID and a.CONTENT_TYPE_ID="+cont_type_id+" " +
					"and a.product_id="+product_id+" and a.scheme_id="+scheme_id+" and a.status='Y' "+temp_qry+" and a.amend_id||a.coverages_id||a.coverages_sub_id in (select max(amend_id)||coverages_id||coverages_sub_id from OFS_COVERAGES_SUB_MASTER where CONTENT_TYPE_ID="+cont_type_id+" and product_id="+product_id+" and scheme_id="+scheme_id+" group by coverages_id,coverages_sub_id) "+ temp_qry_date + "order by a.sub_display_order";
			//System.out.println("the query" + query);
			result = runner.multipleSelection(query,new String[0]);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return result;

	}
	
	public boolean insertPremiumInfo(Hashtable Cover_Values)
	{
		//method not used
		String result = null;
		Enumeration en=Cover_Values.keys();
		String qry="";
		String values[]=new String[2];
		try {
			
			while(en.hasMoreElements())
			{
				values[0]=(String) en.nextElement();
				values[1]=(String) Cover_Values.get(values[0]);
				qry="insert into OFFICE_SHIELD_DATA_DETAILS(quote_no,amend_id,coverages_id,premium_amount) " +
						"values(1,1,?,?)";
				runner.multipleUpdation(qry,values);
			}
			
			
		} catch (Exception e) {
			System.out.println("  runner insertion   " + e.toString());
			result = "DIDN'T INSERTED";
		} 
		return true;
	}
	
	
	public double insertMasterPremiumInfo(HashMap baseSts,HashMap  baseratests,HashMap  baserate, HashMap sum_ins,HashMap textsumins,HashMap cidnamests)
	{
		String[][] result = null;
		Statement stmt = null;
		Connection con = null;
		Set HostKeys=baseSts.keySet();
		Iterator en = HostKeys.iterator();
		String coverid="",cover_sts="",qry="",baserateSts="",query="",sum_insured="",insertStatus="",text_sum_ins="",text_sum_sts="";
		double baserateVal=0.00,premium=0.00,total_premium=0.00;
		int amend_id=0;
		boolean inssts=true;
		
		try {
			con = DBConnection.getInstance().getDBConnection();
			stmt = con.createStatement();
			try {
				query = "select max(amend_id) from OFS_DATA_DETAILS where quote_no="+quote_no+" and product_id="+product_id+" and scheme_id="+scheme_id;
				//System.out.println("the query" + query);
				result = new runner().multipleSelection(query,new String[0]);
				if(result.length>0 && result[0][0]!=null)
				{
					amend_id=Integer.parseInt(result[0][0]);
                    insertStatus="exist";
				}
				else
				{
					amend_id=0;
					insertStatus="insert";
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}

			if (insertStatus.equalsIgnoreCase("exist"))
				{
				 try{
					qry="delete from OFS_DATA_DETAILS where  product_id="+product_id+" and scheme_id="+scheme_id+" and quote_no="+quote_no+"  and amend_id="+amend_id;
					//System.out.println("qry is"+qry);
				     stmt.executeUpdate(qry);
				     }
					 catch (Exception e) {
			                     System.out.println("  Problem to delete the old records   " + e.toString());
								 inssts=false;
								 con.rollback();}
				}
			while(en.hasNext() )
			{
				coverid=(String) en.next();
				cover_sts=(String) baseSts.get(coverid);
				baserateSts=(String)baseratests.get(coverid);
				sum_insured=(String) sum_ins.get(coverid);
                 try{
				baserateVal=Double.parseDouble((String)baserate.get(coverid)==null?"0":(String)baserate.get(coverid));
				}catch(Exception e){System.out.println("baserate converstion error"+e);
				}
				if("Y".equalsIgnoreCase(cover_sts))
				{
				try{
				premium=getPremium(baserateSts,baserateVal,sum_insured,"1");
				}catch(Exception e)
				{
					System.out.println("number format covertion error"+e);
					e.printStackTrace();
				}
				
				total_premium+=premium;
				}

				if(cidnamests.size()>0)
				{     text_sum_sts=(String)  cidnamests.get(coverid);
                   text_sum_ins=(String) textsumins.get(coverid);
				   if(text_sum_sts !=null && text_sum_sts.length()>0 )
				   {
					   sum_insured = (text_sum_ins == null ? "": text_sum_ins);
					   premium=getPremium(baserateSts,baserateVal,sum_insured,"1");//Added by chinna
				   }
				}
				if(inssts)
				{
				qry="	insert into OFS_DATA_DETAILS(quote_no,amend_id,coverages_id,coverages_suminsured,premium_amount,COVERAGES_Y_N_OPTION,COVERAGES_BASE_RATE,status,product_id,scheme_id,content_type_id) " +
						"values("+quote_no+","+amend_id+","+coverid+",'"+sum_insured+"',"+premium+",'"+cover_sts+"',"+baserateVal+",'Y',"+product_id+","+scheme_id+","+cont_type_id+")";
				 
						
				System.out.println("qry is"+qry);
				stmt.executeUpdate(qry);

				if (cont_type_id == 4 && coverid.equalsIgnoreCase("1")) {
						qry = "update ofs_data set CONTENT_VALUE_OTHERS="
								+ sum_insured + " where quote_no=" + quote_no;
						System.out.println("new qry1 is" + qry);
						stmt.executeUpdate(qry); }
				}

				
				
				
			}
			
			
		} catch (Exception e) {
			System.out.println("  runner insertion   " + e.toString());
			
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				if (con != null)
					con.close();
			} catch (Exception e) {
				System.out.println("ERROR " + e.toString());
			}
		}
		return total_premium;
	}
	
	
	public double insertOptionalPremiumInfo(HashMap baseSts,HashMap subbaseratests,HashMap subbaserate,HashMap subbaseids,HashMap cValues,HashMap sum_insured)
	{	
		String[][] result = null;
		Statement stmt = null;
		Connection con = null;
		Set hotkeys=subbaseids.keySet();
		Iterator en=hotkeys.iterator();
		String subcoverid="",basecoverid="",cover_sts="",qry="",subbaserateSts="",cvalue="",sum_ins="",insertStatus="";
		double subbaserateVal=0.00,premium=0.00,total_premium=0.00;
		int amend_id=0;
		boolean inssts=true;
		
		try {
			con = DBConnection.getInstance().getDBConnection();
			stmt = con.createStatement();
			try {
				String query = "select max(amend_id) from OFS_DATA_SUB_DETAILS where quote_no="+quote_no+" and product_id="+product_id+" and scheme_id="+scheme_id;
				//System.out.println("the query" + query);
				result = new runner().multipleSelection(query,new String[0]);
				
				if(result.length>0 && result[0][0]!=null)
				{
					amend_id = Integer.parseInt(result[0][0]);
					insertStatus="exist";
				}
				else
				{
					amend_id=0;
					insertStatus="insert";
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}

				if (insertStatus.equalsIgnoreCase("exist"))
				{
				 try{
					qry="delete from OFS_DATA_SUB_DETAILS where  product_id="+product_id+" and scheme_id="+scheme_id+" and quote_no="+quote_no+"  and amend_id="+amend_id;
					//System.out.println("qry is"+qry);
				     stmt.executeUpdate(qry);
				     }
					 catch (Exception e) {
			                     System.out.println("  Problem to delete the old records   " + e.toString());
								 inssts=false;
								 con.rollback();}
				}
			while(en.hasNext())
				
			{
				subcoverid=(String) en.next();
				basecoverid=(String) subbaseids.get(subcoverid);
				cover_sts=(String)baseSts.get(basecoverid);
				
				if("Y".equalsIgnoreCase(cover_sts))
				{
					cvalue=(String) cValues.get(subcoverid);
					subbaserateSts=(String)subbaseratests.get(subcoverid);
					subbaserateVal=Double.parseDouble((String)subbaserate.get(subcoverid));
					sum_ins=(String) sum_insured.get(subcoverid);
					//System.out.println("subcoverid--"+subcoverid+"--subbaserateVal"+subbaserateVal+"--sum_ins"+sum_ins);
					if(sum_ins!=null && sum_ins.length()>0)
					{
				   if(cvalue !=null && cvalue.length()>0 )
					      sum_ins=cvalue;
					}
				    
					premium=getPremium(subbaserateSts,subbaserateVal,sum_ins,cvalue);
					total_premium+=premium;

					if(inssts)
				     {
					qry="insert into OFS_DATA_SUB_DETAILS(quote_no,amend_id,coverages_id,COVERAGES_SUB_ID,coverages_covered_amount,premium_amount,coverages_covered_employees,COVERAGES_SUB_BASE_RATE,status,product_id,scheme_id,content_type_id) " +
					"values("+quote_no+","+amend_id+","+basecoverid+","+subcoverid+",'"+sum_ins+"',"+premium+",'"+cvalue+"',"+subbaserateVal+",'Y',"+product_id+","+scheme_id+","+cont_type_id+")";
					 //System.out.println("qry is"+qry);
			        stmt.executeUpdate(qry);
					 } 
			       
					
				}
				
				
			}
			
			
		} catch (Exception e) {
			System.out.println("  runner insertion   " + e.toString());
			
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				if (con != null)
					con.close();
			} catch (Exception e) {
				System.out.println("ERROR " + e.toString());
			}
		}
		System.out.println("optional total_premium:  " + total_premium);
		return total_premium;
	}

	
	public double getPremium(String baserateSts,double baserateVal,String sum_insured,String value)
	{
		System.out.println("baserateSts: "+baserateSts+"===baserateVal: "+baserateVal+"=== sum_insured: "+sum_insured+" value: "+value);
		double premium=0.00,sum_ins=0.00;
		int val=0;
		try{
			if(sum_insured!=null && sum_insured.length()>0){
		sum_ins=Double.parseDouble(sum_insured); }
		}catch(Exception e){e.printStackTrace();}
		if("P".equalsIgnoreCase(baserateSts))
		{
			premium=(baserateVal*sum_ins)/100;
		}else if("M".equalsIgnoreCase(baserateSts))
			{
				premium=(baserateVal*sum_ins)/1000;
			}
		else if("A".equalsIgnoreCase(baserateSts))
		{
			try{
				if(value!=null && value.length()>0)
			          val=Integer.parseInt(value);
			}catch (Exception e) {
				e.printStackTrace();
			}
			premium=(baserateVal*val);
			System.out.println("premium: "+premium);
		}else if("G".equalsIgnoreCase(baserateSts))
		{
			String rate=baserateVal+"";
			if(rate.indexOf(".")!=-1)
			{
				premium=(baserateVal*sum_ins)/100;	
			}else
			{
				try
				{
					if(value!=null && value.length()>0)
						val=Integer.parseInt(value);
				}catch (Exception e) {
					e.printStackTrace();
				}
				premium=(baserateVal*val);
			}
		}
		
		System.out.println("premium: for P"+premium);
		return premium;
		
	}
	
	public String[][] getMainCoverageWithQuote(String linkfrom) {
		String[][] result = new String[0][0];
		String[][] amendval = new String[0][0];
		String values[]=new String[4];
		String query = "",temp_qry="", temp_qry_date = "";
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

				// if(!"admin".equalsIgnoreCase(linkfrom) &&
			// !"adminProceedInfo".equalsIgnoreCase(linkfrom)
			// &&!"adminCalculate".equalsIgnoreCase(linkfrom))
			if (true)
				temp_qry_date = " and ( to_date((select to_char(inception_date,'dd-mm-yyyy') from home_position_master where quote_no="
						+ quote_no
						+ "),'dd-mm-yyyy') between b.effective_date and b.expiry_date) ";


			
			/*query="select a.COVERAGES_ID,a.COVERAGES_NAME,b.COVERAGES_TYPE,a.COVERAGES_DISPLAY_NAME,b.DISPLAY_ORDER," +
					"b.SUB_COVERAGES,b.CONTROL_TYPE,c.COVERAGES_SUMINSURED,c.COVERAGES_BASE_RATE,b.HELP_CONTENTS_ID,C.COVERAGES_Y_N_OPTION," +
					"b.CALC_TYPE,b.SUM_INSURED_LIMIT,b.SUM_INSURED_CONTROL_TYPE from  OFS_MASTER a,OFS_COVERAGES_MASTER b, OFS_DATA_DETAILS c " +
					"where a.COVERAGES_ID=b.COVERAGES_ID and c.CONTENT_TYPE_ID="+cont_type_id+" and b.CONTENT_TYPE_ID=c.CONTENT_TYPE_ID " +
					"and a.COVERAGES_ID=c.COVERAGES_ID and b.status='Y' and c.quote_no="+quote_no+" and c.amend_id in ("+amend_id+") and " +
					"c.product_id="+product_id+" and c.scheme_id="+scheme_id+" "+temp_qry+" and b.scheme_id=c.scheme_id and b.amend_id||b.coverages_id in (select max(amend_id)||coverages_id from OFS_COVERAGES_MASTER where CONTENT_TYPE_ID="+cont_type_id+" and product_id="+product_id+" and scheme_id="+scheme_id+" group by coverages_id) "
					+ temp_qry_date
					+ " order by b.display_order";*/
//			Modified by chinna
			/*query="select a.COVERAGES_ID,a.COVERAGES_NAME,b.COVERAGES_TYPE,a.COVERAGES_DISPLAY_NAME,b.DISPLAY_ORDER," +
			"b.SUB_COVERAGES,b.CONTROL_TYPE,c.COVERAGES_SUMINSURED,c.COVERAGES_BASE_RATE,b.HELP_CONTENTS_ID,C.COVERAGES_Y_N_OPTION," +
			"b.CALC_TYPE,b.SUM_INSURED_LIMIT,b.SUM_INSURED_CONTROL_TYPE,b.COVERAGES_LIMIT from  OFS_MASTER a,OFS_COVERAGES_MASTER b, OFS_DATA_DETAILS c " +
			"where a.COVERAGES_ID=b.COVERAGES_ID and c.CONTENT_TYPE_ID="+cont_type_id+" and b.CONTENT_TYPE_ID=c.CONTENT_TYPE_ID " +
			"and a.COVERAGES_ID=c.COVERAGES_ID and b.status='Y' and c.quote_no="+quote_no+" and c.amend_id in ("+amend_id+") and " +
			"c.product_id="+product_id+" and c.scheme_id="+scheme_id+" "+temp_qry+" and b.scheme_id=c.scheme_id and b.amend_id||b.coverages_id in (select max(amend_id)||coverages_id from OFS_COVERAGES_MASTER where CONTENT_TYPE_ID="+cont_type_id+" and product_id="+product_id+" and scheme_id="+scheme_id+" group by coverages_id) "
			+ temp_qry_date
			+ " order by b.display_order";*/
			//End
			/*query="select a.COVERAGES_ID,a.COVERAGES_NAME,b.COVERAGES_TYPE,a.COVERAGES_DISPLAY_NAME,b.DISPLAY_ORDER," +
			"b.SUB_COVERAGES,b.CONTROL_TYPE,c.COVERAGES_SUMINSURED,b.BASE_RATE,b.HELP_CONTENTS_ID,C.COVERAGES_Y_N_OPTION," +
			"b.CALC_TYPE,b.SUM_INSURED_LIMIT,b.SUM_INSURED_CONTROL_TYPE,b.COVERAGES_LIMIT from  OFS_MASTER a,OFS_COVERAGES_MASTER b, OFS_DATA_DETAILS c " +
			"where a.COVERAGES_ID=b.COVERAGES_ID and c.CONTENT_TYPE_ID="+cont_type_id+" and b.CONTENT_TYPE_ID=c.CONTENT_TYPE_ID " +
			"and a.COVERAGES_ID=c.COVERAGES_ID and b.status='Y' and c.quote_no="+quote_no+" and c.amend_id in ("+amend_id+") and " +
			"c.product_id="+product_id+" and c.scheme_id="+scheme_id+" "+temp_qry+" and b.scheme_id=c.scheme_id and b.amend_id||b.coverages_id in (select max(amend_id)||coverages_id from OFS_COVERAGES_MASTER where CONTENT_TYPE_ID="+cont_type_id+" and product_id="+product_id+" and scheme_id="+scheme_id+" group by coverages_id) "
			+ temp_qry_date
			+ " order by b.display_order";*/
			//System.out.println("the query" + query);
			
			
			query="SELECT * FROM ( select a.COVERAGES_ID,a.COVERAGES_NAME,b.COVERAGES_TYPE,a.COVERAGES_DISPLAY_NAME,b.DISPLAY_ORDER," +
			"b.SUB_COVERAGES,b.CONTROL_TYPE,c.COVERAGES_SUMINSURED,c.COVERAGES_BASE_RATE,b.HELP_CONTENTS_ID,C.COVERAGES_Y_N_OPTION," +
			"b.CALC_TYPE,b.SUM_INSURED_LIMIT,b.SUM_INSURED_CONTROL_TYPE,b.COVERAGES_LIMIT from  OFS_MASTER a,OFS_COVERAGES_MASTER b, OFS_DATA_DETAILS c " +
			"where a.COVERAGES_ID=b.COVERAGES_ID and c.CONTENT_TYPE_ID="+cont_type_id+" and b.CONTENT_TYPE_ID=c.CONTENT_TYPE_ID " +
			"and a.COVERAGES_ID=c.COVERAGES_ID and b.status='Y' and c.quote_no="+quote_no+" and c.amend_id in ("+amend_id+") and " +
			"c.product_id="+product_id+" and c.scheme_id="+scheme_id+" "+temp_qry+" and b.scheme_id=c.scheme_id and b.amend_id||b.coverages_id in (select max(amend_id)||coverages_id from OFS_COVERAGES_MASTER where CONTENT_TYPE_ID="+cont_type_id+" and product_id="+product_id+" and scheme_id="+scheme_id+" group by coverages_id) "
			+ temp_qry_date
			+" UNION ALL "
			+"select a.COVERAGES_ID,a.COVERAGES_NAME,b.COVERAGES_TYPE,a.COVERAGES_DISPLAY_NAME,b.DISPLAY_ORDER,"
			+ "b.SUB_COVERAGES,b.CONTROL_TYPE,(case when b.CONTENT_TYPE_ID=4 and b.COVERAGES_ID=1 then TO_CHAR(c.CONTENT_VALUE_OTHERS) else b.COVERAGES_LIMIT end),b.BASE_RATE,b.HELP_CONTENTS_ID,b.CALC_STATUS,"
			+ "b.CALC_TYPE,b.SUM_INSURED_LIMIT,b.SUM_INSURED_CONTROL_TYPE,b.COVERAGES_LIMIT from OFS_MASTER a,OFS_COVERAGES_MASTER b,OFS_DATA c  where a.COVERAGES_ID=b.COVERAGES_ID "
			+"AND a.COVERAGES_ID NOT IN (SELECT COVERAGES_ID FROM OFS_DATA_DETAILS WHERE CONTENT_TYPE_ID = "+cont_type_id+" AND quote_no = "+quote_no+" AND amend_id IN ("+amend_id+") AND product_id = "+product_id+" AND scheme_id = "+scheme_id+") and b.CONTENT_TYPE_ID="+cont_type_id+" and b.product_id="+product_id+" and b.scheme_id="+scheme_id
			+ " and b.CONTENT_TYPE_ID="
			+ cont_type_id
			+ " and b.product_id="
			+ product_id
			+ " and b.scheme_id="
			+ scheme_id
			+ "    and b.status='Y' and c.quote_no="
			+ quote_no
			+ " "
			+ temp_qry
			+ " and b.amend_id||b.coverages_id in (select max(amend_id)||coverages_id from OFS_COVERAGES_MASTER where CONTENT_TYPE_ID="
			+ cont_type_id
			+ " and product_id="
			+ product_id
			+ " and scheme_id="
			+ scheme_id
			+ " group by coverages_id)  "
			+ temp_qry_date
			+ ") order by display_order";
			
			result = new runner().multipleSelection(query,new String[0]);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return result;

	}
	
	public String[][] getSubCoverageWithQuote(String linkfrom){
		String[][] result = new String[0][0];
		String[][] amendval = new String[0][0];
		String values[]=new String[4];
		String query = "",temp_qry="", temp_qry_date = "";
		int amend_id=0;
		try {
			values[0]=""+quote_no;
			values[1]=product_id;
			values[2]=""+scheme_id;
			values[3]=""+cont_type_id;
			String qry = "select max(amend_id) from OFS_DATA_SUB_DETAILS where quote_no=? and product_id=? and scheme_id=? and CONTENT_TYPE_ID=?";
			//System.out.println("the query" + qry);
			amendval = runner.multipleSelection(qry,values);
			
			
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

			if (!"admin".equalsIgnoreCase(linkfrom)
					&& !"adminProceedInfo".equalsIgnoreCase(linkfrom)
					&& !"adminCalculate".equalsIgnoreCase(linkfrom))
				temp_qry_date = " and ( to_date((select to_char(inception_date,'dd-mm-yyyy') from home_position_master where quote_no="
						+ quote_no
						+ "),'dd-mm-yyyy') between a.effective_date and a.expiry_date) ";

			query = "(select a.COVERAGES_SUB_ID,a.COVERAGES_ID,b.COVERAGES_DISPLAY_NAME,a.SUB_COVERAGES_LIMIT,c.COVERAGES_SUB_BASE_RATE," +
					"a.PRODUCT_ID,a.SUB_CONTROL_TYPE,a.SUB_DISPLAY_ORDER,a.CALC_TYPE,c.COVERAGES_COVERED_EMPLOYEES," +
					"a.SUM_INSURED_LIMIT from OFS_COVERAGES_SUB_MASTER a ,OFS_MASTER b,OFS_DATA_SUB_DETAILS c where " +
					"a.status='Y'  and a.COVERAGES_SUB_ID=c.COVERAGES_SUB_ID and a.COVERAGES_SUB_ID=b.COVERAGES_ID and c.quote_no="+quote_no+" and c.amend_id " +
					"in("+amend_id+") and a.amend_id||a.coverages_id||a.coverages_sub_id in (select max(amend_id)||coverages_id||coverages_sub_id from OFS_COVERAGES_SUB_MASTER where CONTENT_TYPE_ID="+cont_type_id+" and product_id="+product_id+" and scheme_id="+scheme_id+" group by coverages_id,coverages_sub_id) and c.CONTENT_TYPE_ID=a.CONTENT_TYPE_ID and c.CONTENT_TYPE_ID="+cont_type_id+" and c.product_id="+product_id+" and c.scheme_id="+scheme_id+" and a.scheme_id=c.scheme_id)" +
					"union(select a.COVERAGES_SUB_ID,a.COVERAGES_ID,b.COVERAGES_DISPLAY_NAME,a.SUB_COVERAGES_LIMIT,a.SUB_RATE," +
					"a.PRODUCT_ID,a.SUB_CONTROL_TYPE,a.SUB_DISPLAY_ORDER,a.CALC_TYPE,a.REMARKS,a.SUM_INSURED_LIMIT from " +
					"OFS_COVERAGES_SUB_MASTER a,OFS_MASTER b where a.CONTENT_TYPE_ID="+cont_type_id+" and a.product_id="+product_id+" and a.scheme_id="+scheme_id+"" +
					" and a.status='Y' and a.COVERAGES_SUB_ID=b.COVERAGES_ID "+temp_qry+" and a.amend_id||a.coverages_id||a.coverages_sub_id in (select max(amend_id)||coverages_id||coverages_sub_id from OFS_COVERAGES_SUB_MASTER where CONTENT_TYPE_ID="+cont_type_id+" and product_id="+product_id+" and scheme_id="+scheme_id+" group by coverages_id,coverages_sub_id) "
					+ temp_qry_date
					+ " and a.COVERAGES_SUB_ID in (select coverages_sub_id " +
					"from OFS_COVERAGES_SUB_MASTER where CONTENT_TYPE_ID="+cont_type_id+" and product_id="+product_id+" and scheme_id="+scheme_id+" and coverages_sub_id " +
					"not in(select coverages_sub_id from OFS_DATA_SUB_DETAILS where quote_no="+quote_no+" and CONTENT_TYPE_ID="+cont_type_id+" and " +
					"product_id="+product_id+" and scheme_id="+scheme_id+" and amend_id in("+amend_id+"))) ) ";
			//System.out.println("the query" + query);
			result = runner.multipleSelection(query,new String[0]);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return result;

	}

	public String[][] getContentID(String quote_no,String linkfrom) {
		String result[][] =new String[0][0];
		String value[][] =new String[0][0];
		String query = "",refStatus=" ";
		String qryValues[]=new String[3];

		try {
			qryValues=new String[4];
			qryValues[0]=""+scheme_id;
			qryValues[1]=""+scheme_id;
			qryValues[2]=""+quote_no;
            qryValues[3]=""+quote_no;
			//Modified by chinna
//			query = "select a.content_value,a.activity_profession,b.referral_status,a.activity_status,b.COVERAGES_IDS,a.content_status from OFS_DATA a,OFS_ACT_PROF_MASTER b where a.quote_no=? and amend_id in (select max(amend_id) from OFS_DATA where quote_no=?) and a.activity_profession=b.activity_profession and b.scheme_id=?"; 
			query = "SELECT   a.content_value, a.activity_profession, nvl((SELECT   b.referral_status FROM   OFS_ACT_PROF_MASTER b WHERE   a.activity_profession = b.activity_profession AND b.scheme_id = ?),' '), a.activity_status, nvl((SELECT   b.COVERAGES_IDS FROM   OFS_ACT_PROF_MASTER b WHERE   a.activity_profession = b.activity_profession AND b.scheme_id = ?),' '), a.content_status FROM   OFS_DATA a WHERE   a.quote_no = ? AND amend_id IN (SELECT   MAX (amend_id) FROM OFS_DATA WHERE quote_no = ?)";
			//System.out.println("the query" + query);
			result = new runner().multipleSelection(query,qryValues);
			qryValues=new String[3];
			
			} catch (Exception ex) {
			ex.printStackTrace();
		    }
			if(result.length>0  &&  !"adminProceedInfo".equalsIgnoreCase(linkfrom) &&  !"adminaddPremium".equalsIgnoreCase(linkfrom) && !"adminCalculate".equalsIgnoreCase(linkfrom))
		   {
				  value=getReferalInfo();
							          
		                           
							   if(value.length>0 )
						      { 
								    if(value[0][1]!=null )
			                        refStatus= value[0][1];
									
							  }
                   if( result[0][2].equalsIgnoreCase("Y") && result[0][4]==null)
			      {
					   try {

						      // if he changed the activity status then clear the referralstatus
							   if (result[0][3]!=null)
			                    {         if(result[0][3].equalsIgnoreCase("Y") )
					                      refStatus="";
			                       } 

							   if(refStatus!=null)
		                    	{
							    if (refStatus.indexOf(",26,") == -1) 
		                             refStatus+=",26,";
								}else 
		                             refStatus+=",26,";


			                   
			                
			                  } catch (Exception ex) {	ex.printStackTrace();		}
				//System.out.println("refStatus 1-----------------------------------------------------------"+refStatus);
				  }
				 else
			      {
					  //System.out.println("refStatus 2-----------------------------------------------------------"+refStatus);
					 
					                     if(refStatus!=null)
		                    	            {
							                     if (refStatus.indexOf(",26,") != -1) 
		                                	           refStatus=refStatus.replaceAll(",26,","");
												 
								            }
											if (result[0][3]!=null)
			                                       {         if(result[0][3].equalsIgnoreCase("Y") )
					                                  refStatus="";
			                                  } 
			        }

					if (result[0][5]!=null)
			         {         if(result[0][5].equalsIgnoreCase("Y") &&  !"admin".equalsIgnoreCase(linkfrom) )
					                      refStatus="";
                       } 
				

				
                         try{
							 if(refStatus.trim().length()>0)
							 {
								 //System.out.println("refStatus updation-----------------------------------------------------------"+refStatus);
					              query = "update HOME_POSITION_MASTER set REMARKS='Referal',REFERRAL_DESCRIPTION='"+refStatus+"' where quote_no="+quote_no+" and product_id="+product_id+" and scheme_id="+scheme_id;
			                       
							 }else{
								  //System.out.println("refStatus updation-----------------------------------------------------------"+refStatus);
					              query = "update HOME_POSITION_MASTER set REMARKS=null,REFERRAL_DESCRIPTION=null where quote_no="+quote_no+" and product_id="+product_id+" and scheme_id="+scheme_id;
							 }
							  if( !"admin".equalsIgnoreCase(linkfrom))
							 runner.updation(query);
								 } catch (Exception ex) {	ex.printStackTrace();		}
					
		   }
			return result;
	 }


	 public String[][] getCustomerInfo() {
		String result[][] =new String[0][0];
		String query = "";
		String values[]=new String[2];
		
		try {
			values[0]=""+quote_no;
			values[1]=""+quote_no;
			query = "select a.quote_no,c.title,(c.first_name||c.company_name),c.last_name,a.address1,c.address2,d.content_description,(case when a.prof_others is not null then (f.profession||'-'||a.prof_others) else f.profession end),(case when a.freezone_others is not null then (g.freezone_description||'-'||a.freezone_others) else g.freezone_description end) from OFS_DATA a,OFS_ACT_PROF_MASTER b,PERSONAL_INFO c,OFS_CONTENT_MASTER d,HOME_POSITION_MASTER e,OFS_ACT_PROF_MASTER f,OFS_FREEZONE_MASTER g where a.quote_no=? and a.amend_id in (select max(amend_id) from OFS_DATA where quote_no=?) and a.activity_profession=b.activity_profession and a.customer_id=c.customer_id and a.content_value=d.content_type_id  and a.quote_no=e.quote_no and d.product_id=e.product_id and d.scheme_id=e.scheme_id and d.content_type_id=e.content_type_id and a.activity_profession=f.activity_profession and a.freezone=g.freezone  and f.scheme_id=e.scheme_id"; 
			//System.out.println("the query" + query);
			result = new runner().multipleSelection(query,values);
			
			} catch (Exception ex) {
			ex.printStackTrace();
		    }
			
			return result;
	 }



     public String[][] getReferalInfo()
	{
		 String[][] val=new String[0][0];
		 String[] values=new String[3];
		try{
	  values[0]=""+quote_no;
	  values[1]=product_id;
	  values[2]=""+scheme_id;
      String query = "select ADMIN_REFERRAL_STATUS,REFERRAL_DESCRIPTION from HOME_POSITION_MASTER where quote_no=? and product_id=? and scheme_id=?";
	  val = new runner().multipleSelection(query,values);
			 } catch (Exception ex) {	ex.printStackTrace();		}
			 return val;
	}

	public String[] getReferalDetailInfo(String linkfrom)
	{
		String result[] =new String[3];
		String value[][] =new String[0][0];
		String qryValues[]=new String[1];
		String query = "",refStatus="",referral="",Allreferral="",tempRefStatus="";
		result[1]="NotAvailable";
		  value=getReferalInfo();
		   if(value.length>0 )
		      { 
				    if(value[0][1]!=null )
		                refStatus= value[0][1].trim();

					//System.out.println("refStatus.........................................."+refStatus+"refStatus.length"+refStatus.length());
					if(refStatus!=null && refStatus.length()>0 )
		           {
                        try{
							if (refStatus.indexOf("Admin Referral") != -1) 
					        {
								   tempRefStatus=refStatus;
		    	                   refStatus=refStatus.replaceAll("Admin Referral","");
								
								  if(refStatus.length()<=0 )
					              query = "update HOME_POSITION_MASTER set ADMIN_SUMMARY_STATUS='N',REMARKS=null,REFERRAL_DESCRIPTION=null where quote_no="+quote_no+" and product_id="+product_id+" and scheme_id="+scheme_id;
                                    else
								   query = "update HOME_POSITION_MASTER set ADMIN_SUMMARY_STATUS='N',REMARKS='Referal', REFERRAL_DESCRIPTION='"+refStatus+"'  where quote_no="+quote_no+" and product_id="+product_id+" and scheme_id="+scheme_id;
                                   
								   if( !tempRefStatus.equalsIgnoreCase("Admin Referral"))
                                    runner.updation(query);
             				 }
							
							}
				              catch(Exception e) { System.out.println("errror"+e);}
                        }// refstatus

						if( tempRefStatus.indexOf("Admin Referral") != -1  &&("adminProceedInfo".equalsIgnoreCase(linkfrom) ||  "adminaddPremium".equalsIgnoreCase(linkfrom) ||  "admin".equalsIgnoreCase(linkfrom) || "adminCalculate".equalsIgnoreCase(linkfrom) ))
				           { Allreferral+="Admin Referral";
						     result[1]="Available";
							 result[2]=Allreferral;
						   }
			            
						if(refStatus!=null && refStatus.length()>0 )
		           {
						result[1]="Available";
                       refStatus=refStatus.replaceAll(",,",",");
					   refStatus=refStatus.substring(1,(refStatus.length()-1));
					    try
				        {
						//qryValues[0]=refStatus;
					   query = "select referal_name,referal_id from REFERAL_MASTER where referal_id in("+refStatus+") and branch_code='"+branch_code+"' ";
			                  
			        	// value = new runner().multipleSelection(query,qryValues);
			        	 value = new runner().multipleSelection(query);
						 if(value.length>0 && value[0][0]!=null)
							{
							 
							 for(int i=0;i<value.length;i++)
								{
								 
								 Allreferral+=((value[i][0]==null?"":value[i][0])+", ");
								  if( !(value[i][1].equalsIgnoreCase("26")) && !(value[i][1].equalsIgnoreCase("34")) )
                                  referral+=((value[i][0]==null?"":value[i][0])+"<br>");
								}
							}

                        } catch (Exception ex) {	ex.printStackTrace();		}

						  result[0]=referral;
			              if(Allreferral != null && Allreferral.length() > 1) 
			            	  result[2]=Allreferral.substring(0, Allreferral.length()-2);
			              else
			            	  result[2]="test";
				    }// refstatus 2nd
				  
		 
			  }
			 /* if("Admin Referral".equalsIgnoreCase(refStatus))
		        {
			 result[1]="";
			  result[0]=refStatus;
			  result[2]=refStatus;
				}
               else
		      {
			  result[0]=referral;
			  result[2]=Allreferral;
			  }*/
			  return result;
				   
	}



	 public String[] validateOptionalPremiumInfo(String linkFrom,HashMap baseSts,HashMap subbaseratests,HashMap subbaserate,HashMap subbaseids,HashMap cValues,HashMap sum_insured,HashMap cidnames)
	{	
		String[] result = new String[4];
		String[][] value = new String[0][0];
		Set hotkeys=subbaseids.keySet();
		Iterator en=hotkeys.iterator();
		TreeMap tm = new TreeMap();
		String subcoverid="",basecoverid="",cover_sts="",qry="",subbaserateSts="",cvalue="",sum_ins="",error="",refStatus=" ";
		double subbaserateVal=0.00,premium=0.00,total_premium=0.00,si=0.00,t_si=0.00;;
		boolean referal=false,errSts=true,FDrefstatus=false,Buildrefsts=false,GPAsts1=false,GPAsts2=false,GPAsts3=false,SIrefstatus=false;
		int amend_id=0;
		int totalFields = 0; // vinoth
		
		try {
					
			while(en.hasNext())
				
			{
				errSts=true;
				subcoverid=(String) en.next();
				basecoverid=(String) subbaseids.get(subcoverid);
				cover_sts=(String)baseSts.get(basecoverid);
				

				if("Y".equalsIgnoreCase(cover_sts))
				{
					cvalue=(String) cValues.get(subcoverid);
					subbaserateSts=(String)subbaseratests.get(subcoverid);
					subbaserateVal=Double.parseDouble((String)subbaserate.get(subcoverid));
                    
                    sum_ins=(String) sum_insured.get(subcoverid);
					 System.out.println("----subcoverid--"+subcoverid+"--basecoverid"+basecoverid+"--cvalue"+cvalue+"------sum_ins"+sum_ins);
					 //validating Fidelity Guarantee as a reference
					if("8".equalsIgnoreCase(basecoverid) )
				   {      
						if ("ProceedInfo".equalsIgnoreCase(linkFrom)
								|| "Calculate".equalsIgnoreCase(linkFrom)) {
						 if(cvalue!=null && cvalue.equalsIgnoreCase("N")&& !"24".equalsIgnoreCase(subcoverid))
					      FDrefstatus=true;
						 else if(cvalue!=null && cvalue.equalsIgnoreCase("Y")&& "24".equalsIgnoreCase(subcoverid))
					      FDrefstatus=true;
						}
                     if("ProceedInfo".equalsIgnoreCase(linkFrom) || "Calculate".equalsIgnoreCase(linkFrom) || "adminProceedInfo"
										.equalsIgnoreCase(linkFrom)
								|| "adminCalculate".equalsIgnoreCase(linkFrom))
					 {
                          if("18".equalsIgnoreCase(subcoverid))//FG
					   {
                         if(cvalue.equalsIgnoreCase("select") )
					       {  //error+=(runner.getErrormsg("301")+"<br>"); 
						      tm.put(new Integer(subcoverid), (runner
											.getErrormsg("301") + "<br>"));
						    errSts=false;} else { // senthil
									// String qyery = "delete from
									// OFS_FIDELITY_GUARANTEE where
									// quote_no='"+quote_no+"' and amend_id in
									// (select max(amend_id) from
									// OFS_FIDELITY_GUARANTEE where
									// quote_no='1000000871' group by quote_no)
									// and (select count(max(amend_id)) from
									// OFS_FIDELITY_GUARANTEE where
									// quote_no='1000000871' group by
									// quote_no)<>5";
									// by vinoth
									// String qyery = "delete from
									// OFS_FIDELITY_GUARANTEE a,
									// OFS_DATA_SUB_DETAILS b where
									// a.quote_no='"+quote_no+"' and
									// a.quote_no=b.quote_no and
									// b.COVERAGES_COVERED_EMPLOYEES
									// >'"+cvalue+"'";
								/*	String query = "DELETE FROM OFS_FIDELITY_GUARANTEE WHERE EXISTS (select a.quote_no from OFS_FIDELITY_GUARANTEE a, OFS_DATA_SUB_DETAILS b where a.quote_no='"
											+ quote_no
											+ "' and b.COVERAGES_SUB_ID='18' and to_number(b.COVERAGES_COVERED_EMPLOYEES) >"
											+ cvalue + ")";
									System.out.println("Delete Query \t\t\t ::"
											+ query);*/
									//runner.updation(query);
								}
						 }else if("19".equalsIgnoreCase(subcoverid))
					   {
                         if(cvalue==null || cvalue.length()<=0 )
					       { // error+=(runner.getErrormsg("302")+"<br>");  
						 tm.put(new Integer(subcoverid), (runner
											.getErrormsg("302") + "<br>"));
						    errSts=false;}
						 }
						 else if("20".equalsIgnoreCase(subcoverid))
					   {
                         if(cvalue==null || cvalue.length()<=0 )
					       {  //error+=(runner.getErrormsg("303")+"<br>"); 
						 tm.put(new Integer(subcoverid), (runner
											.getErrormsg("303") + "<br>"));
						    errSts=false;}
						 }else if("21".equalsIgnoreCase(subcoverid))
					   {
                         if(cvalue==null || cvalue.length()<=0 )
					       { // error+=(runner.getErrormsg("304")+"<br>"); 
						 tm.put(new Integer(subcoverid), (runner
											.getErrormsg("304") + "<br>"));
						    errSts=false;}
						 }else if("22".equalsIgnoreCase(subcoverid))
					   {
                         if(cvalue==null || cvalue.length()<=0 )
					       { // error+=(runner.getErrormsg("305")+"<br>"); 
						 tm.put(new Integer(subcoverid), (runner
											.getErrormsg("305") + "<br>"));
						    errSts=false;}
						 }else if("23".equalsIgnoreCase(subcoverid))
					   {
                         if(cvalue==null || cvalue.length()<=0 )
					       {  //error+=(runner.getErrormsg("306")+"<br>"); 
						 tm.put(new Integer(subcoverid), (runner
											.getErrormsg("306") + "<br>"));
						    errSts=false;}
						 }else if("24".equalsIgnoreCase(subcoverid))
					   {
                         if(cvalue==null || cvalue.length()<=0 )
					       {  //error+=(runner.getErrormsg("307")+"<br>"); 
						 tm.put(new Integer(subcoverid), (runner
											.getErrormsg("307") + "<br>"));
						    errSts=false;}
						 }else if("25".equalsIgnoreCase(subcoverid))
					   {
                         if(cvalue==null || cvalue.length()<=0 )
					       {  //error+=(runner.getErrormsg("308")+"<br>"); 
						 tm.put(new Integer(subcoverid), (runner
											.getErrormsg("308") + "<br>"));
						    errSts=false;}
						 }

					 }//proceedinfo
				    }

					//Group Personal Accident
					if("10".equalsIgnoreCase(basecoverid) )
				   {   
						/*  if("26".equalsIgnoreCase(subcoverid))
					   {
                         if(cvalue !=null && cvalue.length()>0 )
					       {  
							  if(!OFB.validAmount(cvalue))
							   { error+=(runner.getErrormsg("324")+"<br>"); 
						         errSts=false; }
							} else
									  { error+=(runner.getErrormsg("323")+"<br>"); 
						         errSts=false; }


					   } else*/ 
						if("27".equalsIgnoreCase(subcoverid))
					   {
                         if(cvalue !=null && cvalue.length()>0 )
					       {  
							  if(!OFB.validAmount(cvalue) || cvalue.equalsIgnoreCase("0"))
							   { //error+=(runner.getErrormsg("309")+"<br>"); 
							  tm.put(new Integer(subcoverid), (runner
											.getErrormsg("309") + "<br>"));
						         errSts=false; }
								 // senthil
								else {
									try {
										totalFields += Integer.parseInt(cvalue);
									} catch (Exception e) {
										totalFields += 0;
										e.printStackTrace();
									}

									// String qyery = "delete from
									// OFS_FIDELITY_GUARANTEE a,
									// OFS_DATA_SUB_DETAILS b where
									// a.quote_no='"+quote_no+"' and
									// a.quote_no=b.quote_no and
									// b.COVERAGES_COVERED_EMPLOYEES<>'"+cvalue+"'";
									// runner.updation(qyery);
								}
							} else
									 GPAsts1=true;
							
					   }else if("28".equalsIgnoreCase(subcoverid))
					   {
                         if(cvalue !=null && cvalue.length()>0 )
					       {  if(!OFB.validAmount(cvalue) || cvalue.equalsIgnoreCase("0"))
							   {//error+=(runner.getErrormsg("310")+"<br>");
						 tm.put(new Integer(subcoverid), (runner
											.getErrormsg("310") + "<br>"));
						       errSts=false;}
							   else {
									try {
										totalFields += Integer.parseInt(cvalue);
									} catch (Exception e) {
										totalFields += 0;
										e.printStackTrace();
									}
								}
							 }
							 else
							   GPAsts2=true;
							
					   } else if("29".equalsIgnoreCase(subcoverid))
					   {
                         if(cvalue !=null && cvalue.length()>0)
						   {  if(!OFB.validAmount(cvalue) || cvalue.equalsIgnoreCase("0"))
							   {//error+=(runner.getErrormsg("311")+"<br>");
						 tm.put(new Integer(subcoverid), (runner
											.getErrormsg("311") + "<br>"));
						    errSts=false;}
							else {
									try {
										totalFields += Integer.parseInt(cvalue);
									} catch (Exception e) {
										totalFields += 0;
										e.printStackTrace();
									}
								}
							}
							else
								GPAsts3=true;
						  }

						  if(GPAsts1 && GPAsts2 && GPAsts3 && ("ProceedInfo".equalsIgnoreCase(linkFrom) || "Calculate".equalsIgnoreCase(linkFrom) || "adminProceedInfo"
												.equalsIgnoreCase(linkFrom) || "adminCalculate"
										.equalsIgnoreCase(linkFrom))
								&& !"26".equalsIgnoreCase(subcoverid) )
					 		  error+=(runner.getErrormsg("312")+"<br>");

							
				    }
                    // vinoth start
					/*System.out.println("Vinoth  \t" + check1 + "\t" + check2
							+ "\t" + check3);
					if (check1 && check2 && check3) {
						try {
							String query = "delete FROM OFS_PERSONAL_ACCIDENT WHERE EXISTS (select a.quote_no from "
									+ "OFS_PERSONAL_ACCIDENT a, OFS_DATA_SUB_DETAILS b where b.quote_no = a.quote_no and "
									+ "(select sum(to_number(COVERAGES_COVERED_EMPLOYEES)) from OFS_DATA_SUB_DETAILS where "
									+ "COVERAGES_SUB_ID in (27,28,29) and quote_no='"
									+ quote_no + "') > " + totalFields + ")";

							System.out.println("Delete Query vinoth \t\t\t ::"
									+ query);
							runner.updation(query);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}*/
					// end vinoth
					//Building Cover validation
					if("11".equalsIgnoreCase(basecoverid) )
				   {     
						 // if its opt building cover is referral
					    Buildrefsts=true;
						if("30".equalsIgnoreCase(subcoverid))
					   {
                         if(cvalue==null || cvalue.length()<=0 )
					       {       if( "ProceedInfo".equalsIgnoreCase(linkFrom) || "Calculate".equalsIgnoreCase(linkFrom) || "adminProceedInfo"
												.equalsIgnoreCase(linkFrom)
										|| "adminCalculate"
												   .equalsIgnoreCase(linkFrom))
							        { //error+=(runner.getErrormsg("313")+"<br>");
								tm.put(new Integer(subcoverid), (runner
											.getErrormsg("313") + "<br>"));
												   errSts=false;}
						   }
							else
						   {
								 if(!OFB.validAmount(cvalue) || cvalue.equalsIgnoreCase("0"))
								//error+=(runner.getErrormsg("314")+"<br>");
								 tm.put(new Integer(subcoverid), (runner
											.getErrormsg("314") + "<br>"));
						   }
					   }else if("31".equalsIgnoreCase(subcoverid))
					   {
                         if(cvalue==null || cvalue.length()<=0 )
					       {  if( "ProceedInfo".equalsIgnoreCase(linkFrom) || "Calculate".equalsIgnoreCase(linkFrom)|| "adminProceedInfo"
												.equalsIgnoreCase(linkFrom)
										|| "adminCalculate"
												.equalsIgnoreCase(linkFrom))
							   {//error+=(runner.getErrormsg("315")+"<br>"); 
												tm.put(new Integer(subcoverid), (runner
											.getErrormsg("315") + "<br>"));
												errSts=false;}
						   }

						 if("N".equalsIgnoreCase(cvalue))
						   { 
							      
                                    if(((String) cValues.get("32"))==null || ((String) cValues.get("32")).length()<=0 )
							         {  if( "ProceedInfo".equalsIgnoreCase(linkFrom) || "Calculate".equalsIgnoreCase(linkFrom) || "adminProceedInfo"
													.equalsIgnoreCase(linkFrom)
											|| "adminCalculate"
													.equalsIgnoreCase(linkFrom))
										 { //error+=(runner.getErrormsg("316")+"<br>");
													tm.put(new Integer(subcoverid), (runner
												.getErrormsg("316") + "<br>"));
													errSts=false;}
									 }
									else if( ((String) cValues.get("32")).length()>50 )
										{ //error+=(runner.getErrormsg("320")+"<br>");
									tm.put(new Integer(subcoverid), (runner
											.getErrormsg("320") + "<br>"));
									      errSts=false;}
					             
						   }
					   }
							
				    }

					if( sum_ins!=null && sum_ins.length()>0)
				     {
                   
				    si=Double.parseDouble(sum_ins);
				   if(cvalue !=null && cvalue.length()>0 )
					       {  
					           
							  if(!OFB.validAmount(cvalue))
							   { error+=(runner.getErrormsg("318")+"-"+((String)cidnames.get(subcoverid))+"<br>"); 
						         errSts=false; }
								 else
							     {
									 t_si=Double.parseDouble(cvalue);
									 if(t_si>si)
									    {  error+=(runner.getErrormsg("319")+"-"+((String)cidnames.get(subcoverid))+"<br>");  errSts=false; 
										//SIrefstatus=true;
										}
									 else
                                       sum_ins=cvalue;
							       }
							} 
							else  {  //System.out.println("Its coming to add money");
							if(basecoverid.equalsIgnoreCase("6"))
								                {  if("ProceedInfo".equalsIgnoreCase(linkFrom) || "Calculate".equalsIgnoreCase(linkFrom))
													{  
								                       error+=(runner.getErrormsg("317")+"-"+((String)cidnames.get(subcoverid))+"<br>");  errSts=false; }}
							          }

				        }

					if(errSts)
					{
					//sum_ins=(String) sum_insured.get(subcoverid);
					System.out.println("subcoverid--"+subcoverid+"--subbaserateVal"+subbaserateVal+"--sum_ins"+sum_ins);
					premium=getPremium(subbaserateSts,subbaserateVal,sum_ins,cvalue);
					total_premium+=premium;
					}
						
				}
				
				
			}
	    // Get a set of the entries
			Set set = tm.entrySet();
			// Get an iterator
			Iterator i = set.iterator();
			// Display elements
			while (i.hasNext()) {
				Map.Entry me = (Map.Entry) i.next();
				System.out.print(me.getKey() + ": ");
				System.out.println(me.getValue());
				error += me.getValue();
			}		
			
		} catch (Exception e) {
			System.out.println("  runner insertion   " + e.toString());
			
		} 
		
		//adding error values
		result[0]=error;
    // adding referal as 'R' and 'NR'-non referal
	   
        if ("ProceedInfo".equalsIgnoreCase(linkFrom)
				|| "Calculate".equalsIgnoreCase(linkFrom)) {
		   //System.out.println("Its Referal");
		   try {
			                  value=getReferalInfo();
							   if(value.length>0 )
						      { 
								    if(value[0][1]!=null )
			                        {
										refStatus= value[0][1];
									}
						       }
				} catch (Exception ex) {
			ex.printStackTrace();		}

      if(FDrefstatus)
		{
		  //System.out.println("coming to add ..................................................fD");
		  if(refStatus!=null)
			{
		         if (refStatus.indexOf(",24,") == -1) 
		        refStatus+=",24,";
			}else
                refStatus+=",24,";
		}else
		{
			 //System.out.println("coming to remove ..................................................fD");
			  if(refStatus!=null)
			{
			 if (refStatus.indexOf(",24,") != -1) 
		    	refStatus=refStatus.replaceAll(",24,","");
			}
		}

		 if(Buildrefsts)
		{
			  //System.out.println("coming to add ..................................................building");
			   if(refStatus!=null)
			{
			  if (refStatus.indexOf(",25,") == -1) 
		       refStatus+=",25,";
			}else
               refStatus+=",25,";
		}
		else
		{
			 //System.out.println("coming to remove ..................................................buildig");
             if(refStatus!=null)
			{
			 if (refStatus.indexOf(",25,") != -1) 
		    	refStatus=refStatus.replaceAll(",25,","");
			}
		}

		 if(SIrefstatus)
		{
			  //System.out.println("coming to add ..................................................building");
			   if(refStatus!=null)
			{
			  if (refStatus.indexOf(",28,") == -1) 
		       refStatus+=",28,";
			}else
               refStatus+=",28,";
		}
		else
		{
			 //System.out.println("coming to remove ..................................................suminsured");
             if(refStatus!=null)
			{
			 if (refStatus.indexOf(",28,") != -1) 
		    	refStatus=refStatus.replaceAll(",28,","");
			}
		}
       
	   try{
			// if( error.length()<=0)
			 //{
			     String query = "update HOME_POSITION_MASTER set REMARKS='Referal', REFERRAL_DESCRIPTION='"+refStatus+"' where quote_no="+quote_no+" and product_id="+product_id;
			     query+=" and scheme_id="+scheme_id;
			     //System.out.println("the query" + query);
			     runner.updation(query);
			 //}
								 } catch (Exception ex) {	ex.printStackTrace();		}
					
			

		}// if proceed info referal checking

		//adding total premium 
		 result[2]=Double.toString(total_premium);

		return result;
	}

public String[] validateMasterPremiumInfo(String linkFrom, HashMap baseSts,
			HashMap baseratests, HashMap baserate, HashMap sum_ins,
			HashMap textsumins, HashMap cidnames, HashMap cidnamests) {
		String[] result = new String[2];
		String[][] value = new String[0][0];
		Set HostKeys = baseSts.keySet();
		Iterator en = HostKeys.iterator();
		String coverid = "", cover_sts = "", qry = "", baserateSts = "", query = "", sum_insured = "", text_sum_ins = "", error = "", refStatus = "", cont_value = "";
		double baserateVal = 0.00, premium = 0.00, total_premium = 0.00, si = 0.00, t_si = 0.00;
		int amend_id = 0;
		boolean errSts = true, SIrefstatus = false;

		value = getReferalInfo();
		if (value.length > 0) {
			if (value[0][1] != null)
				refStatus = value[0][1];
		}

		try {

			while (en.hasNext()) {
				coverid = (String) en.next();
				cover_sts = (String) baseSts.get(coverid);
				baserateSts = (String) baseratests.get(coverid);
				sum_insured = (String) sum_ins.get(coverid);
				System.out.print("coverid---------------------------" + coverid
						+ "cover_sts----------------" + cover_sts);

				if ("Y".equalsIgnoreCase(cover_sts)) {
					// verifying the coverids in referral part

					if (refCover_ids.length() > 0
							&& refCover_ids.indexOf(("," + coverid + ",")) != -1) {
						if (refStatus != null) {
							if (refStatus.indexOf(",26,") == -1)
								refStatus += ",26,";
						} else
							refStatus += ",26,";

						System.out.println("ref statusss123333345----"
								+ coverid + "refStatus" + refStatus);

					}// refcover_idslength

					try {
						System.out.print("coverid" + coverid
								+ "(String)baserate.get(coverid)"
								+ (String) baserate.get(coverid));

						if (!OFB.validAmount((String) baserate.get(coverid))) {
							error += "please verify the base rate--"
									+ cidnames.get(coverid);
							errSts = false;
						} else {
							baserateVal = Double.parseDouble((String) baserate
									.get(coverid));
						}
						System.out.println("textsumins: "+textsumins.size());
						System.out.println("sum_insured: "+sum_insured.length());
						if (textsumins.size() > 0 && sum_insured != null
								&& sum_insured.length() > 0) {
							text_sum_ins = (String) textsumins.get(coverid);
							
							System.out.println("text_sum_ins: "+text_sum_ins);
							si = Double.parseDouble(sum_insured);

							if (text_sum_ins != null
									&& text_sum_ins.length() > 0) {

								if (!OFB.validAmount(text_sum_ins)
										|| text_sum_ins.equalsIgnoreCase("0")) {
									error += (runner.getErrormsg("318") + "-"
											+ ((String) cidnames.get(coverid)) + "<br>");
									errSts = false;
								} else {
									t_si = Double.parseDouble(text_sum_ins);
									System.out
											.println("text_sum_ins1234565688--"
													+ t_si + "si--" + si
													+ "coverid--" + coverid);

									if (coverid.equalsIgnoreCase("2")) {
										si = (Double
												.parseDouble((String) textsumins
														.get("1")) * 0.1);

									}

									if (t_si > si) { // error+=(runner.getErrormsg("319")+"-"+((String)cidnames.get(coverid))+"<br>");
														// errSts=false;
										String refid = "";
										if (coverid.equalsIgnoreCase("1"))
											refid = ",28,";
										else if (coverid.equalsIgnoreCase("2"))
											refid = ",29,";
										else if (coverid.equalsIgnoreCase("3"))
											refid = ",30,";
										else if (coverid.equalsIgnoreCase("4"))
											refid = ",31,";
										else if (coverid.equalsIgnoreCase("5"))
											refid = ",32,";
										else if (coverid.equalsIgnoreCase("7"))
											refid = ",33,";
										//Added by chinna
										else if (coverid.equalsIgnoreCase("11"))
											refid = ",43,";
										else if (coverid.equalsIgnoreCase("38"))
											refid = ",44,";
										else if (coverid.equalsIgnoreCase("43"))
											refid = ",48,";
										else if (coverid.equalsIgnoreCase("44"))
											refid = ",49,";
										else if (coverid.equalsIgnoreCase("45"))
											refid = ",50,";
										//End
										System.out
												.println("coming to add ..........suminsured");
										if (refStatus != null
												&& refid.length() > 0) {
											if (refStatus.indexOf(refid) == -1)
												refStatus += refid;
										} else
											refStatus += refid;

										 SIrefstatus=true;
									} else {

										if (cont_type_id == 4
												& coverid.equalsIgnoreCase("1")) {
											query = "select content_value from OFS_CONTENT_MASTER where product_id="
													+ product_id
													+ " and scheme_id="
													+ scheme_id
													+ " and content_type_id="
													+ cont_type_id;
											cont_value = new runner()
													.singleSelection(query);
											if (t_si <= Double
													.parseDouble(cont_value)) // Jan17
											{
												error += (runner
														.getErrormsg("318")
														+ "-"
														+ ((String) cidnames
																.get(coverid)) + "<br>");
												errSts = false;
											}
										}

										sum_insured = text_sum_ins;
										String refid = "";
										if (coverid.equalsIgnoreCase("1"))
											refid = ",28,";
										else if (coverid.equalsIgnoreCase("2"))
											refid = ",29,";
										else if (coverid.equalsIgnoreCase("3"))
											refid = ",30,";
										else if (coverid.equalsIgnoreCase("4"))
											refid = ",31,";
										else if (coverid.equalsIgnoreCase("5"))
											refid = ",32,";
										else if (coverid.equalsIgnoreCase("7"))
											refid = ",33,";
//										Added by chinna
										else if (coverid.equalsIgnoreCase("11"))
											refid = ",43,";
										else if (coverid.equalsIgnoreCase("38"))
											refid = ",44,";
										else if (coverid.equalsIgnoreCase("43"))
											refid = ",48,";
										else if (coverid.equalsIgnoreCase("44"))
											refid = ",49,";
										else if (coverid.equalsIgnoreCase("45"))
											refid = ",50,";
										//End
										System.out
												.println("coming to remove ..................................................suminsured values123"
														+ refStatus);

										if (refStatus.indexOf(refid) != -1)
											refStatus = refStatus.replaceAll(
													refid, "");

									}
								}
							} else {
								if ("ProceedInfo".equalsIgnoreCase(linkFrom)
										|| "Calculate"
												.equalsIgnoreCase(linkFrom)
										|| "adminProceedInfo"
												.equalsIgnoreCase(linkFrom)
										|| "admin".equalsIgnoreCase(linkFrom)
										|| "adminCalculate"
												.equalsIgnoreCase(linkFrom)) {
									error += (runner.getErrormsg("317") + "-"
											+ ((String) cidnames.get(coverid)) + "<br>");
									errSts = false;
								}
							}

						}

						System.out.println("baserateSts: "+baserateSts+"------baserateVal: "+baserateVal+"---sum_insured: "+sum_insured);
						if (errSts)
							premium = getPremium(baserateSts, baserateVal,
									sum_insured, "1");
						System.out.println("premium: "+premium);
						
						  if(SIrefstatus) { System.out.println("coming to add..................................................suminsured");
						  if(refStatus!=null) { /*if (refStatus.indexOf(",28,") ==-1) refStatus+=",28,";*/ }/*else refStatus+=",28,";*/ }
						  else { System.out.println("coming to remove ..................................................suminsured");
						  if(refStatus!=null) { if (refStatus.indexOf(",28,") !=-1) refStatus=refStatus.replaceAll(",28,",""); } }
						 

						try {
							/*
							 * if(!userType.equalsIgnoreCase("admin")) {
							 */
							if ("ProceedInfo".equalsIgnoreCase(linkFrom)
									|| "Calculate".equalsIgnoreCase(linkFrom)) {
								if (refStatus.trim().length() > 0
										&& error.length() <= 0) {
									query = "update HOME_POSITION_MASTER set REMARKS='Referal', REFERRAL_DESCRIPTION='"
											+ refStatus
											+ "' where quote_no="
											+ quote_no
											+ " and product_id="
											+ product_id;
									query += " and scheme_id=" + scheme_id;
									System.out.println("query is" + query);
									runner.updation(query);
								}
								if (refStatus.trim().length() <= 0
										&& error.length() <= 0) {
									query = "update HOME_POSITION_MASTER set ADMIN_SUMMARY_STATUS='N',REMARKS=null,REFERRAL_DESCRIPTION=null,SUMMARY_REMARKS=null where quote_no="
											+ quote_no
											+ " and product_id="
											+ product_id
											+ " and scheme_id="
											+ scheme_id;
									System.out.println("query is" + query);
									runner.updation(query);
								}
								// }
							}
						} catch (Exception ex) {
							ex.printStackTrace();
						}

					} catch (Exception e) {
						System.out.println("number format covertion error" + e);
						e.printStackTrace();
					}

					total_premium += premium;
				} else { // senthil kumar
					if (coverid.equals("2")) {
						query = "delete from OFS_PORTABLE_EQUIPMENT where quote_no="
								+ quote_no;
						runner.updation(query);
						query = "delete from OFS_UPLOADED_DATA where quote_no="
								+ quote_no + " and product_id=" + product_id
								+ " and scheme_id=" + scheme_id
								+ " and CONTENT_TYPE_ID=" + cont_type_id
								+ " and coverages_id=" + coverid;
						runner.updation(query);
					}
					if (coverid.equals("8")) {
						query = "delete from OFS_FIDELITY_GUARANTEE where quote_no="
								+ quote_no;
						runner.updation(query);
						query = "delete from OFS_UPLOADED_DATA where quote_no="
								+ quote_no + " and product_id=" + product_id
								+ " and scheme_id=" + scheme_id
								+ " and CONTENT_TYPE_ID=" + cont_type_id
								+ " and coverages_id=" + coverid;
						runner.updation(query);
					}
					if (coverid.equals("10")) {
						query = "delete from OFS_PERSONAL_ACCIDENT where quote_no="
								+ quote_no;
						runner.updation(query);
						query = "delete from OFS_UPLOADED_DATA where quote_no="
								+ quote_no + " and product_id=" + product_id
								+ " and scheme_id=" + scheme_id
								+ " and CONTENT_TYPE_ID=" + cont_type_id
								+ " and coverages_id=" + coverid;
						runner.updation(query);
					}
				}
			}

		} catch (Exception e) {
			System.out.println("  runner insertion   " + e.toString());

		}
		result[0] = error;
		result[1] = Double.toString(total_premium);
		return result;
	}
	public String[] validateMasterbaserate(String linkFrom,HashMap baseSts,HashMap  baseratests,HashMap  baserate, HashMap sum_ins,HashMap textsumins,HashMap cidnames)
	{
		String[] result = new String[2];
		String[][] value = new String[0][0];
		Set HostKeys=baseSts.keySet();
		Iterator en = HostKeys.iterator();
		String coverid="",cover_sts="",qry="",baserateSts="",query="",sum_insured="",text_sum_ins="",error="",refStatus="";
		double baserateVal=0.00,premium=0.00,total_premium=0.00,si=0.00,t_si=0.00;
		int amend_id=0;boolean errSts=true;
		
		try {
			
			while(en.hasNext())
			{
				coverid=(String) en.next();
				cover_sts=(String) baseSts.get(coverid);
				baserateSts=(String)baseratests.get(coverid);
				sum_insured=(String) sum_ins.get(coverid);
                //System.out.print("coverid---------------------------"+coverid+"cover_sts----------------"+cover_sts);
				if("Y".equalsIgnoreCase(cover_sts))
				{
				try{
					//System.out.print("coverid-------"+coverid+"(String)baserate.get(coverid)"+(String)baserate.get(coverid));
				    
					//System.out.print("coverid------------------"+coverid+"baserateVal----------------------------------------"+baserateVal);
					if(!OFB.validAmount((String)baserate.get(coverid)))
					    {    error+="please verify the base rate--"+cidnames.get(coverid); errSts=false; }
					else
					     { 
						    baserateVal=Double.parseDouble((String)baserate.get(coverid));
						 }
				       
					   if(errSts)
				           premium=getPremium(baserateSts,baserateVal,sum_insured,"1");

					}catch(Exception e)
				{
					System.out.println("number format covertion error"+e);
					e.printStackTrace();
				}
				total_premium+=premium;
				
				}
			}
			
		} catch (Exception e) {
			System.out.println("  runner insertion   " + e.toString());
			
		}
		result[0]=error;
		result[1]=Double.toString(total_premium);
		return result;
	}
    public String[] getQuoteDataInfo(String activity_profession) {
		String[] result = new String[3];
        String[] values = new String[4];
		String val = "";
		String query = "";
		int amend_id = 0;
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


	   public double updatePremium(double total_premium,String referral,String userType) {
		System.out.println("total_premium:=====>>> "+total_premium);
		String[] result = new String[2];
		String[][] value = new String[0][0];
		String val = "";
		String query = "",temp_qry="",refStatus="";
		double min_premium = 0.00;
		String values[]=new String[3];
			
        value=getReferalInfo();
		   if(value.length>0 )
		      { 
				    if(value[0][1]!=null )
		                refStatus= value[0][1].trim();
			  }

		if(referral.equalsIgnoreCase("NR")&& !userType.equalsIgnoreCase("admin") && refStatus.indexOf("Admin Referral") == -1)
			temp_qry=" ,REMARKS=null, ADMIN_REFERRAL_STATUS=null,REFERRAL_DESCRIPTION =null ";
		// scheme master premium
		try {
			values[0]=product_id;
			values[1]=""+scheme_id;
			values[2]=""+cont_type_id;
			query = "select minimum_premium from OFS_CONTENT_MASTER where  product_id=? and scheme_id=? and CONTENT_TYPE_ID=?";
			//System.out.println("the query" + query);
			val = new runner().singleSelection(query,values);
			
			if(val != null && val.length()>0)
				min_premium=Double.parseDouble(val);
			
			if(min_premium>total_premium)
				total_premium=min_premium;
		
			} catch (Exception ex) {
			ex.printStackTrace();		}
           // min premium from broker 
			try {
			values[0]=""+quote_no;
			values[1]=product_id;
			values[2]=""+scheme_id;
			
			query = "select c.min_premium_amount from home_position_master a,login_master b,LOGIN_USER_DETAILS c where a.quote_no=? and a.product_id=? and a.scheme_id=? and a.login_id=b.login_id and b.oa_code=c.agency_code and c.product_id=a.product_id ";
			//System.out.println("the query" + query);
			val = new runner().singleSelection(query,values);
			
			if(val != null && val.length()>0)
				min_premium=Double.parseDouble(val);
			
			if(min_premium>total_premium)
				total_premium=min_premium;
		
			} catch (Exception ex) {
			ex.printStackTrace();		}

			if(referral.equalsIgnoreCase("CusInfo"))
            return total_premium;
			
			try {
			
			query = "update HOME_POSITION_MASTER set overall_premium="+total_premium+",premium="+total_premium+" "+temp_qry+" where quote_no="+quote_no+" and product_id="+product_id;
			query+=" and scheme_id="+scheme_id;
			System.out.println("the query" + query);
			runner.updation(query);
					
			} catch (Exception ex) {
			ex.printStackTrace();		}
			return total_premium;
	   }

	  public String getHelpContentDetails(String help_content_id)
	{
		  String val="",qry="";
		  String values[]=new String[1];
		  values[0]=help_content_id;
		  qry="select help_description from OFS_HELP_MASTER where help_contents_id=?";
		  try
		{
		  val=runner.singleSelection(qry,values);
			  } catch (Exception ex) {
			ex.printStackTrace();		}
			return val;
	}


	public void updateReferralStatus(String adminReferalRemarks,String Remarks)
	{
		String qry="";
		if("Y".equalsIgnoreCase(Remarks))
			qry = "update HOME_POSITION_MASTER set REMARKS='Admin',ADMIN_REFERRAL_STATUS='N' ,status='Y',admin_remarks=' "+adminReferalRemarks+"' where quote_no="+quote_no+" and product_id="+product_id +" and scheme_id="+scheme_id;
		else if("N".equalsIgnoreCase(Remarks))
			qry = "update HOME_POSITION_MASTER set REMARKS='Referal',ADMIN_REFERRAL_STATUS='N' ,status='R',admin_remarks=' "+adminReferalRemarks+"' where quote_no="+quote_no+" and product_id="+product_id +" and scheme_id="+scheme_id;
		else if("A".equalsIgnoreCase(Remarks))
			qry = "update HOME_POSITION_MASTER set REMARKS='Referal',ADMIN_REFERRAL_STATUS='N' ,status='Y',admin_remarks=' "+adminReferalRemarks+"' where quote_no="+quote_no+" and product_id="+product_id +" and scheme_id="+scheme_id;

       try {
		   //System.out.println("the query" + qry);
			runner.updation(qry);
					
			} catch (Exception ex) {
			ex.printStackTrace();		}
	}

	public void updatefinalPremium(String addSign,String addPremium,String payablePremium)
	{
		String qry="",sign="";
		if("Plus".equalsIgnoreCase(addSign))
           sign="+";
			else if ("Minus".equalsIgnoreCase(addSign))
				 sign="-";

				
			qry = "update HOME_POSITION_MASTER set excess_sign='"+sign+"',excess_premium='"+addPremium+"' ,premium="+payablePremium+",overall_premium="+payablePremium+" where quote_no="+quote_no+" and product_id="+product_id +" and scheme_id="+scheme_id;
		

       try {
		   //System.out.println("the query" + qry);
			runner.updation(qry);
					
			} catch (Exception ex) {
			ex.printStackTrace();		}
	}



	public HashMap getReferalMaster(String branch_id)
	{
		String values[]=new String[1];
		String result[][]=new String[0][0];
		String query="",refid="",refval="";
		HashMap HM = new HashMap(); 

		   try
		      {
				values[0] = branch_id;
		   	   query = "select referal_id,referal_name from REFERAL_MASTER where  branch_code=? order by referal_id";
			   result=runner.multipleSelection(query,values);
			   if(result.length>0)
			   for(int i=0;i<result.length;i++)
			   {
				   System.out.println(",,,,,,,,"+result[i][0]);
				   refid=result[i][0];
				   refval=result[i][1];
				   HM.put(result[i][0],refval); 
			   }
			     } catch (Exception ex) {	ex.printStackTrace();		}
					
			     System.out.println("Hm......"+HM.size());
		  return HM;
	 }

     public String getProductID(String quote_no)
	{
		  String prod_id="";
		   String values[]=new String[1];
		   values[0]=quote_no;
		  prod_id=runner.singleSelection("select product_id from home_position_master where quote_no=?",values);
		  return prod_id;
	}
	  public String[][] getReferalExcessInfo()
	{
		 String[][] val=new String[0][0];
		 String values[]=new String[3];
		try{
			values[0]=""+quote_no;
			values[1]=product_id;
			values[2]=""+scheme_id;
      String query = "select premium,overall_premium,excess_sign,excess_premium,admin_remarks from home_position_master where quote_no=? and product_id=? and scheme_id=?";
	  val = new runner().multipleSelection(query,values);
			 } catch (Exception ex) {	ex.printStackTrace();		}
			 return val;
	}

	public void updateContent4RefStatus(double limit,double evalue)
	{
		String[][] value = new String[0][0];
		String refStatus="",query="";
		   value=getReferalInfo();
		   if(value.length>0 )
		      { 
				    if(value[0][1]!=null )
		                refStatus= value[0][1].trim();
			  }


		if(limit>evalue)
		{
			
			   System.out.println("coming to remove .................................................sum insured");
			  if(refStatus!=null)
			{
			 if (refStatus.indexOf(",28,") != -1) 
		    	refStatus=refStatus.replaceAll(",28,","");
			}
		}

		 try{
							 if(refStatus.trim().length()>0)
							 {
								 System.out.println("refStatus updation-----------------------------------------------------------"+refStatus);
					              query = "update HOME_POSITION_MASTER set REMARKS='Referal',REFERRAL_DESCRIPTION='"+refStatus+"' where quote_no="+quote_no+" and product_id="+product_id+" and scheme_id="+scheme_id;
			                       
							 }else{
								  System.out.println("refStatus updation-----------------------------------------------------------"+refStatus);
					              query = "update HOME_POSITION_MASTER set ADMIN_SUMMARY_STATUS='N', REMARKS=null,REFERRAL_DESCRIPTION=null where quote_no="+quote_no+" and product_id="+product_id+" and scheme_id="+scheme_id;
							 }
							 runner.updation(query);
								 } catch (Exception ex) {	ex.printStackTrace();		}
	}

	public void getClaimExperience(String quote_no,String linkfrom) {
		String result[][] =new String[0][0];
		String value[][] =new String[0][0];
		String query = "",refStatus=" ";
		String param[]=new String[2];
		boolean claim=false;
		
		try {
			param[0]=quote_no;
			param[1]=quote_no;
			query = "select last_years_1,last_years_2,last_years_3,amount_1,amount_2,amount_3,no_of_claim1 from OFS_DATA  where quote_no=? and amend_id in (select max(amend_id) from OFS_DATA where quote_no=?) "; 
			System.out.println("the query" + query);
			result = new runner().multipleSelection(query,param);
			
			} catch (Exception ex) {
			ex.printStackTrace();
		    }
			if(result.length>0  &&  !"adminProceedInfo".equalsIgnoreCase(linkfrom) &&  !"adminaddPremium".equalsIgnoreCase(linkfrom) )
		   {
				  value=getReferalInfo();
							          
		                           
							   if(value.length>0 )
						      { 
								    if(value[0][1]!=null )
			                        refStatus= value[0][1];
									
							  }
                 
					   try {

						      // if Anything greater than 2 is referral
							   if (result[0][6]!=null&&result[0][6].length()>0)
			                    {    
								   if (Integer.parseInt(result[0][6]) >2)
									   claim=true;
			                       } 

							  if (result[0][5]!=null&&result[0][5].length()>0)
			                    {    
								   if (Integer.parseInt(result[0][5]) >5000)
									   claim=true;
			                       } 

								if (result[0][4]!=null&&result[0][4].length()>0)
			                    {    
								   if (Integer.parseInt(result[0][4]) >10000)
									   claim=true;
			                       } 

								   if (result[0][3]!=null&&result[0][3].length()>0)
			                    {    
								   if (Integer.parseInt(result[0][3]) >10000)
									   claim=true;
			                       } 
							  } catch (Exception ex) {	ex.printStackTrace();		}
               if(claim)
			      {
							   if(refStatus!=null)
		                    	{
							    if (refStatus.indexOf(",34,") == -1) 
		                             refStatus+=",34,";
								}else 
		                             refStatus+=",34,";
              
			 				  }
				 else
			      {
					                 if(refStatus!=null)
		                    	            {
							                     if (refStatus.indexOf(",34,") != -1) 
		                                	           refStatus=refStatus.replaceAll(",34,","");
												 
								            }
					 }

					
				
                         try{
							 if(refStatus.trim().length()>0)
							 {
								 System.out.println("refStatus updation-----------------------------------------------------------"+refStatus);
					              query = "update HOME_POSITION_MASTER set REMARKS='Referal',REFERRAL_DESCRIPTION='"+refStatus+"' where quote_no="+quote_no+" and product_id="+product_id+" and scheme_id="+scheme_id;
			                       
							 }else{
								  System.out.println("refStatus updation-----------------------------------------------------------"+refStatus);
					              query = "update HOME_POSITION_MASTER set REMARKS=null,REFERRAL_DESCRIPTION=null where quote_no="+quote_no+" and product_id="+product_id+" and scheme_id="+scheme_id;
							 }
							 runner.updation(query);
								 } catch (Exception ex) {	ex.printStackTrace();		}
					
		   }
			
	 }
	//New Method added by chinna
	public String[][] getBaseRate(String mainCover, String subCover, String productid, String schemeid, int contentTypeId, String sum)
	{
		System.out.println("getBaseRate - Enter");
		String[] args=new String[9];
		//String q="select BASE_RATE,CALC_TYPE from OFS_GRID_MASTER where ? between START_SUMINSURED and END_SUMINSURED AND ( PRODUCT_ID=? and SCHEME_ID=? and CONTENT_TYPE_ID="+contentTypeId+" and COVERAGES_ID=? and COVERAGES_SUB_ID=?)";
		String q="select NVL((BASE_RATE),'0') BASE_RATE,CALC_TYPE from OFS_GRID_MASTER where ? between START_SUMINSURED and END_SUMINSURED and STATUS='Y' AND ( PRODUCT_ID=? and SCHEME_ID=? and CONTENT_TYPE_ID=? and COVERAGES_ID=? and COVERAGES_SUB_ID=?) and AMEND_ID||COVERAGES_ID||COVERAGES_SUB_ID IN (select MAX(AMEND_ID)||COVERAGES_ID||COVERAGES_SUB_ID FROM OFS_GRID_MASTER WHERE PRODUCT_ID=? and SCHEME_ID=? and CONTENT_TYPE_ID=? GROUP BY COVERAGES_ID,COVERAGES_SUB_ID)";
		args[0]=sum;
		args[1]=productid;
		args[2]=schemeid;
		args[3]=contentTypeId+"";
		args[4]=mainCover;
		args[5]=subCover;
		args[6]=productid;
		args[7]=schemeid;
		args[8]=contentTypeId+"";
		String result[][]=runner.multipleSelection(q, args);
		System.out.println("getBaseRate - Exit");
		return result;
	}
	public boolean getFormulaCount(String proid, String schemeId, String coverid)
	{
		boolean result=false;
		String q="select count(*) from formula_master where product_id=? and scheme_id=? and coverage_id=? and status='Y'";
		String[] args=new String [3];
		args[0]=proid;
		args[1]=schemeId;
		args[2]=coverid;
		if(!runner.singleSelection(q, args).equalsIgnoreCase("0"))
		{
			result = true;
		}
		return result;
	}
	public String getFormulaSum(String proid, String schemeId, String coverid, String quoteno)
	{
		String result="";
		try{
			String q="select formula from formula_master where product_id=? and scheme_id=? and coverage_id=? and status='Y'";
			String[] args=new String [3];
			args[0]=proid;
			args[1]=schemeId;
			args[2]=coverid;
			q=runner.singleSelection(q, args);
			args=new String[2];
			args[0]=quoteno;
			args[1]=quoteno;
			result=runner.singleSelection(q, args);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return result;
	}
	
	public String checkMailIds(String quoteNo)
	{
		System.out.println("checkMailIds - Enter");
		String sql = "";
        String args[] = new String[1];
        String from = "";
        String to = "";
        String result="";
		try
		{
			args[0] = quoteNo;
			sql = "select EMAIL from personal_info where AGENCY_CODE in(select oa_code from LOGIN_MASTER where login_id=(select login_id from home_position_master where quote_no=?))";
			from = runner.singleSelection(sql,args);
			sql = "select EMAIL from personal_info where customer_id =(select customer_id from home_position_master where quote_no=?)";
			to = runner.singleSelection(sql,args);
			if(from==null || from.length()<=0 || to==null || to.length()<=0)
			result="Email ID is Not Available";
		}
		catch(Exception e)
		{
			System.out.println("Exception is"+e);
            e.printStackTrace();
		}
		System.out.println("checkMailIds - Exit || Result: "+result);
		return result;
	}
}
