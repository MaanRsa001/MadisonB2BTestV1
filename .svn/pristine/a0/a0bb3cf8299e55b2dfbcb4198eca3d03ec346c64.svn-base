package com.maan.Office.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

import com.maan.services.util.runner;

public class  officeadminBean{
     private String branch_code="";

	 public String getBranch_code() {
    	return branch_code;
     }
    public void setBranch_code(String branch_code) {
	     this.branch_code = branch_code;
     }

	public String validateSchemeInfo(String scheme,String rsacode) 
	{
		String error="";
		if(scheme.trim().length()<=0)
		    error+="Please enter the scheme name<br>";
		if(rsacode.trim().length()<=0)
		    error+="Please enter the Core Application code<br>";

		return error;
 	}
	
	public void insertSchemeInfo(String pid,String scheme,String remarks,String rsacode,String status) 
	{
		String values[]=new String[6];
		values[0]=scheme;
		values[1]=remarks;
		values[2]=status;
		values[3]=rsacode;
		values[4]=pid;
		values[5]=branch_code;
		String qry="insert into OFS_SCHEME_MASTER(SCHEME_ID,SCHEME_NAME,REMARKS,STATUS,RSACODE,product_id,display_order,branch_code) values ((select NVL(max(scheme_id),0)+1 from OFS_SCHEME_MASTER),?,?,?,?,?,(select NVL(max(scheme_id),0)+1 from OFS_SCHEME_MASTER),?)";
		runner.multipleInsertion(qry,values);
	}

	public String validateContentInfo(String scheme,String cont_desc,String  min_prem,String rsacode)
	{
		String error="";
		if(scheme.equalsIgnoreCase("select"))
		    error+="Please select the scheme <br>";
		if(rsacode.trim().length()<=0)
		    error+="Please enter the Core Application code<br>";
		if(cont_desc.trim().length()<=0)
		    error+="Please enter the content description<br>";
		if(min_prem.trim().length()<=0)
		    error+="Please enter the minimum premium<br>";

		return error;
 	}
	


	public void insertContentInfo(String pid,String scheme,String cont_desc,String cont_value,String  min_prem,String remarks,String rsacode,String status) {
		
		String values[]=new String[10];
		values[0]=pid;
		values[1]=scheme;
		values[2]=cont_desc;
		values[3]=remarks;
		values[4]=status;
		values[5]=pid;
		values[6]=scheme;
		values[7]=cont_value;
		values[8]=min_prem;
		values[9]=rsacode;
		
		String qry="insert into OFS_CONTENT_MASTER(CONTENT_TYPE_ID,CONTENT_DESCRIPTION,REMARKS,STATUS,PRODUCT_ID,SCHEME_ID,CONTENT_VALUE,MINIMUM_PREMIUM,RSACODE) values((select nvl(max(content_type_id)+1,1) from OFS_CONTENT_MASTER where product_id=? and scheme_id=?),?,?,?,?,?,?,?,?)";
		runner.multipleInsertion(qry,values);


	}


	public String validateCoverageInfo(String Cname,String Cdisname,String rsacode) 
	{
		String error="";
		if(Cname.trim().length()<=0)
		    error+="Please enter the coverage name<br>";
		if(Cdisname.trim().length()<=0)
		    error+="Please enter the coverage display name<br>";
		if(rsacode.trim().length()<=0)
		    error+="Please enter the Core Application code<br>";

		return error;
 	}



	public void insertCoverageInfo(String Cname,String Cdisname,String sce_details,String remarks,String rsacode,String status) {
	    String values[]=new String[6];
		values[0]=Cname;
		values[1]=Cdisname;
		values[2]=remarks;
		values[3]=status;
		values[4]=rsacode;
		values[5]=sce_details;
	
		
	   String qry="insert into OFS_MASTER(COVERAGES_ID,COVERAGES_NAME,COVERAGES_DISPLAY_NAME,REMARKS,STATUS,RSACODE,SECTION_DETAILS) values((select nvl(max(COVERAGES_ID)+1,1) from OFS_MASTER),?,?,?,?,?,?)";	

	   runner.multipleInsertion(qry,values);

	}

	public void updateCoverageInfo(String Cname,String Cdisname,String sce_details,String remarks,String rsacode,String coverage_id,String status) {
	    String values[]=new String[8];
		values[0]=coverage_id;
		values[1]=Cname;
		values[2]=Cdisname;
		values[3]=remarks;
		values[4]=status;
		values[5]=rsacode;
		values[6]=sce_details;
		values[7]=coverage_id;
		
	   String qry="update OFS_MASTER set COVERAGES_ID=?,COVERAGES_NAME=?,COVERAGES_DISPLAY_NAME=?,REMARKS=?,STATUS=?,RSACODE=?,SECTION_DETAILS=? where coverages_id=?";

	   runner.multipleInsertion(qry,values);

	}

	public String[][] getSchemeInfo(String prod_id,String scheme)
	{
         String result[][]=new String[0][0];
		 String values[]=new String[2];
		 values[0]=prod_id;
		 values[1]=scheme;
		 String qry="select SCHEME_ID,SCHEME_NAME,REMARKS,RSACODE,STATUS from OFS_SCHEME_MASTER where product_id=? and scheme_id=?";
		 result=runner.multipleSelection(qry,values);
		 return result;


	}

	public void updateSchemeInfo(String pid,String scheme,String remarks,String rsacode,String scheme_id,String status) 
	{
		String values[]=new String[7];
		values[0]=scheme;
		values[1]=remarks;
		values[2]=status;
		values[3]=rsacode;
		values[4]=pid;
		values[5]=branch_code;
		values[6]=scheme_id;
		String qry="update OFS_SCHEME_MASTER set SCHEME_NAME=?,REMARKS=?,STATUS=?,RSACODE=? where product_id=? and branch_code=? and scheme_id=?"; 
		runner.multipleUpdation(qry,values);
	}

	public String[][] getContentInfo(String prod_id,String scheme,String contentType)
   {
         String result[][]=new String[0][0];
		  String values[]=new String[3];
		 values[0]=prod_id;
		 values[1]=scheme;
		 values[2]=contentType;
		  String qry="select CONTENT_TYPE_ID,CONTENT_DESCRIPTION,SCHEME_ID,REMARKS,RSACODE,STATUS,CONTENT_VALUE,MINIMUM_PREMIUM from OFS_CONTENT_MASTER where product_id=? and scheme_id=? and CONTENT_TYPE_ID=?";
		 result=runner.multipleSelection(qry,values);
		 return result;
	}


	public String[][] getScheme(String pProductID) {
		 String values[]=new String[1];
		 values[0]=pProductID;
		String query = "select SCHEME_ID,SCHEME_NAME from OFS_SCHEME_MASTER where PRODUCT_ID=? order by SCHEME_ID";
		String[][] result = new String[0][0];
		result = runner.multipleSelection(query,values);
		return result;
	}

	public String[][] getCoverages() {
		String query = "select COVERAGES_ID,COVERAGES_NAME from OFS_MASTER order by COVERAGES_ID";
		String[][] result = new String[0][0];
		result = runner.multipleSelection(query);
		return result;
	}

	public void updateContentInfo(String pid,String scheme,String cont_desc,String cont_value,String  min_prem,String remarks,String rsacode,String cont_id,String status) {
		
		String values[]=new String[10];
		values[0]=cont_desc;
		values[1]=remarks;
		values[2]=status;
		values[3]=scheme;
		values[4]=cont_value;
		values[5]=min_prem;
		values[6]=rsacode;
		values[7]=pid;
		values[8]=scheme;
		values[9]=cont_id;
		
		String qry="update OFS_CONTENT_MASTER  set CONTENT_DESCRIPTION=?,REMARKS=?,STATUS=?,SCHEME_ID=?,CONTENT_VALUE=?,MINIMUM_PREMIUM=?,RSACODE=? where product_id=? and scheme_id=? and content_type_id=?";
		runner.multipleInsertion(qry,values);


	}
	public String[][] getCoverageInfo(String coverage)
   {
         String result[][]=new String[0][0];
		 String values[]=new String[1];
		 values[0]=coverage;
		  String qry="select COVERAGES_ID,COVERAGES_NAME,COVERAGES_DISPLAY_NAME,REMARKS,RSACODE,STATUS,SECTION_DETAILS from OFS_MASTER where COVERAGES_ID=?";
		 result=runner.multipleSelection(qry,values);
		 return result;
	}

	public void insertExtendsInfo(final String prod_id,final String scheme,final String cont_type,final String extn_desc,final String cover,final String remarks,final String rsacode,final String status,final String effectiveDate) 
	{
		int ext_id=0;
		String max_ext_id=runner.singleSelection("select max(extension_id)+1 from OFS_EXTENSION_MASTER where product_id="+prod_id+" and scheme_id="+scheme+" and content_type_id="+cont_type);
		if(max_ext_id!=null)
			ext_id=Integer.parseInt(max_ext_id);
		
		String values[]=new String[11];
		values[0]=prod_id;
		values[1]=scheme;
		values[2]=cover;
		values[3]=cont_type;
		values[4]=String.valueOf(ext_id);
		values[5]=extn_desc;
		values[6]=remarks;
		values[7]=status;
		values[8]=rsacode;
		values[9]=String.valueOf(ext_id);
		values[10]="0";
	
		
		String qry="insert into OFS_EXTENSION_MASTER(PRODUCT_ID,SCHEME_ID,COVERAGES_ID,CONTENT_TYPE_ID,EXTENSION_ID,EXTENSION_DESCRIPTION,REMARKS,STATUS,RSACODE,DISPLAY_ORDER,amend_id,EFFECTIVE_DATE) values(?,?,?,?,?,?,?,?,?,?,?,to_date('"+effectiveDate+"','dd-mm-yyyy'))";
		runner.multipleInsertion(qry,values);
	}

	public String[][] getExtensionInfo(final String prod_id,final String scheme,final String contentType)
	   {
	         String result[][]=new String[0][0];
			 String values[]=new String[3];
			 values[0]=prod_id;
			 values[1]=scheme;
			 values[2]=contentType;
			 String qry="select b.coverages_name,a.extension_description,a.status,a.display_order,a.coverages_id," +
			 		"a.extension_id,a.scheme_id,to_char(a.effective_date,'dd-mm-yyyy'),a.RSACODE from OFS_EXTENSION_MASTER a,OFS_MASTER b where a.product_id=? and " +
			 		"a.scheme_id=? and a.content_type_id=? and a.coverages_id=b.coverages_id and (a.amend_id||a.product_id||a.scheme_id||" +
			 		"a.content_type_id||a.coverages_id||a.extension_id) in(select max(amend_id)||product_id||scheme_id||content_type_id||coverages_id||extension_id  " +
			 		"from OFS_EXTENSION_MASTER group by product_id,scheme_id,content_type_id,coverages_id,extension_id)order by a.coverages_id,"
			 		+"a.display_order";
			 result=runner.multipleSelection(qry,values);
			 return result;
		}
	   
	public void updateExtensionInfo(final String prod_id,final String scheme,final String content_id,final String status,final String cover_id,final String ext_id,final String ext_name,final String dis_order,final String effectiveDate,final String rsa_code) 
	{
		
		String amend = new String();
		amend = maxAmendID(prod_id, cover_id, scheme, content_id,ext_id);
		int amendID;

		try {
			amendID = Integer.parseInt(amend);
		} catch (Exception e) {
			amendID = 0;
		}
		
		amendID += 1;
		
		/*String values[]=new String[8];
		values[0]=ext_name;
		values[1]=dis_order;
		values[2]=status;
		values[3]=prod_id;
		values[4]=scheme;
		values[5]=content_id;
		values[6]=ext_id;
		values[7]=cover_id;
			
		String qry="update OFS_extension_MASTER  set EXTENSION_DESCRIPTION=?,DISPLAY_ORDER=?,STATUS=?,effective_date=to_date('"+effectiveDate.trim()+"','dd-mm-yyyy') where product_id=? and scheme_id=? and content_type_id=? and extension_id=? and coverages_id=?";
		runner.multipleInsertion(qry,values);*/
		
		String values[]=new String[10];
		values[0]=prod_id;
		values[1]=scheme;
		values[2]=cover_id;
		values[3]=content_id;
		values[4]=ext_id;
		values[5]=status;
		values[6]=rsa_code;
		values[7]=dis_order;
		values[8]=Integer.toString(amendID);
		values[9]=ext_name;
	
		
		String qry="insert into OFS_EXTENSION_MASTER(PRODUCT_ID,SCHEME_ID,COVERAGES_ID,CONTENT_TYPE_ID,EXTENSION_ID,STATUS,RSACODE,DISPLAY_ORDER,amend_id,EXTENSION_DESCRIPTION,EFFECTIVE_DATE) values(?,?,?,?,?,?,?,?,?,?,to_date('"+effectiveDate+"','dd-mm-yyyy'))";
		runner.multipleInsertion(qry,values);
	}
   
	public String maxAmendID(final String pProductID,final String pCoverageNameID,final String pSchemeID,final String pContentTypeID,final String pExtensionID) 
	{
		String checkValue = "";
		String[] values = new String[5];
		values[0] = pProductID;
		values[1] = pCoverageNameID;
		values[2] = pSchemeID;
		values[3] = pContentTypeID;
		values[4] = pExtensionID;
		String checkQuery = "select nvl(max(amend_id), -1) from OFS_EXTENSION_MASTER where PRODUCT_ID = ? and COVERAGES_ID = ? and SCHEME_ID = ? and CONTENT_TYPE_ID = ? and EXTENSION_ID =? ";
		checkValue = runner.singleSelection(checkQuery, values);
		return checkValue;
	}
	
	public String[][] getCoverInfo()
	   {
	         String result[][]=new String[0][0];
	         String values[]=new String[0];
			 String qry="select coverages_id,coverages_name from OFS_MASTER";
			 result=runner.multipleSelection(qry,values);
			 return result;
		}


	
}
