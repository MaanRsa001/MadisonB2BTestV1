//Note : The person  Who is really willing to change this code he want to add like this select eno from emp;that can be rewritten as select eno,ename from emp and not like this select ename,eno from this emp and this will affect the whole process flow.

package com.maan.Home.Premium;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.StringTokenizer;

import com.maan.common.LogManager;
import com.maan.common.error.ErrorInfo;
import com.maan.common.exception.BaseException;
import com.maan.common.util.OracleDateConversion;
//import com.maan.common.util.StringUtil;
import com.maan.services.util.runner;
import com.maan.services.util.ValidationFormat;

public class  PremiumDisplay  extends ErrorInfo
{
			private transient String queryClaimName;
			//private String queryBaseDriverName;
			//private	String queryBaseDriverType;
			//private transient String queryBaseDriverSubName;
			//private String queryRatingFactorSub;
			private transient String insuranceDate="";
			private transient String premium_name[]=new String[0];
			private transient String premium_value[]=new String[0];
			final ValidationFormat validObj = new ValidationFormat();
			private transient String query = "";

            public void  setInsuranceDate(final String insuranceDate){
				this.insuranceDate=insuranceDate;
			}
			public String getInsuranceDate(){
				return insuranceDate;
			}
			public void setPremiumName(final String[] premium_name){
				this.premium_name=(String[])premium_name.clone();
			}
			public String[] getPremiumName(){
					return (String[])premium_name.clone();
			}
			public void setPremiumValue(final String[] premium_value)
			{
				this.premium_value=(String[])premium_value.clone();
			}
			public String[] getPremiumValue()
			{
					return (String[])premium_value.clone();
			}

			 //Queries for displaying all the datas without data duplicate displaying from rating_factor_sub starts
			 public  String[][] allClaimDetails(final String claimId) throws BaseException
			{
				LogManager.push("allClaimDetails method()");
				LogManager.debug("- Enter");

			    query="select distinct(rfs.rating_factor_id),pfm.rating_factor_name from rating_factor_sub rfs,product_field_master_new pfm  where rfs.claim_type_id in '"+claimId+"' and rfs.dimension_id is not null and rfs.dimension_grid_id is not null and rfs.loading_discount_id is not null and rfs.status='Y' and rfs.rating_factor_id=pfm.rating_factor_id order by pfm.rating_factor_name";
				final String  claimResult[][] = runner.multipleSelection(query);

				LogManager.debug("- Exit");
				LogManager.popRemove();

				return claimResult;
			}

			//Queries for displaying all the datas without data duplicate displaying from rating_factor_sub ends
			//This is for gettting grid Id starts
			public String[][] getDimensionGridId(final String ratingFactorId,final String claimId) throws BaseException
			{
					LogManager.push("getDimensionGridId method()");
					LogManager.debug("- Enter");

					query="select rating_factor_sub_name,dimension_id,dimension_grid_id,loading_discount_id,rating_factor_id,rating_factor_sub_id from rating_factor_sub where rating_factor_id = ? and dimension_id is not null and dimension_grid_id is not null and loading_discount_id is not null  and status='Y' and claim_type_id in '"+claimId+"'";
                    String[] args = {ratingFactorId};
                    final String  claimResult[][] = runner.multipleSelection(query,args);

            		LogManager.debug("- Exit");
            		LogManager.popRemove();

					return claimResult;
			}

            //Used
			public String[][] getRemainingClaimDetails(final String dimensionId,final String ratingFactorId,final String ratingFactorSubId) throws BaseException
			{
				//String queryForGettingDimension="select rf.claim_type_id,rf.dimension_id,rf.dimension_grid_id,rf.loading_discount_id,rf.rating_factor_sub_name,rf.rating_factor_sub_id,rf.rating_factor_id  from rating_factor_sub rf where rf.claim_type_id ='"+claimId+"' and rf.dimension_id >0 and rf.dimension_grid_id>0 and rf.loading_discount_id > 0 and rf.status='Y'";

				LogManager.push("getRemainingClaimDetails method()");
				LogManager.debug("- Enter");

				final String[] args = {ratingFactorId,ratingFactorSubId};
				query="";
		        if("3".equalsIgnoreCase(dimensionId)){
		            query="select distinct(d2d.rating_factor_id),pf.rating_factor_name from two_dim_discrete_discrete d2d,product_field_master_new pf where d2d.rating_factor_id != ? and d2d.rating_factor_sub_id = ? and pf.rating_factor_id = d2d.rating_factor_id  and pf.status='Y'and d2d.status='Y'";
	            }
	            if("4".equalsIgnoreCase(dimensionId)){
			        query="select distinct(d2d.rating_factor_id),pf.rating_factor_name from two_dim_discrete_range d2d,product_field_master_new pf where d2d.rating_factor_id != ? and d2d.rating_factor_sub_id = ? and pf.rating_factor_id=d2d.rating_factor_id  and pf.status='Y'and d2d.status='Y'";
	            }
	            if("5".equalsIgnoreCase(dimensionId)){
	            	query="select distinct(d2d.rating_factor_id),pf.rating_factor_name from two_dim_range_discrete d2d,product_field_master_new pf where d2d.rating_factor_id != ? and d2d.rating_factor_sub_id = ? and pf.rating_factor_id=d2d.rating_factor_id  and pf.status='Y'and d2d.status='Y'";

	            }
	            if("6".equalsIgnoreCase(dimensionId)){
		            query="select distinct(d2d.rating_factor_id),pf.rating_factor_name from two_dim_range_range d2d,product_field_master_new pf where d2d.rating_factor_id != ? and d2d.rating_factor_sub_id = ? and pf.rating_factor_id=d2d.rating_factor_id  and pf.status='Y'and d2d.status='Y'";
	            }

	            final String[][] claimResult= runner.multipleSelection(query,args);

				LogManager.debug("- Exit");
        		LogManager.popRemove();

			    return claimResult;
			}

			//Queries for displaying all the datas with data duplicate displaying from rating_factor_sub starts
			public  String[][] allClaimWithDuplicateDetails(final String claimId) throws BaseException
			{
				LogManager.push("allClaimWithDuplicateDetails method()");
				LogManager.debug("- Enter");

				query ="select rf.claim_type_id,rf.dimension_id,rf.dimension_grid_id,rf.loading_discount_id,rf.rating_factor_sub_name,rf.rating_factor_sub_id,rf.rating_factor_id  from rating_factor_sub rf where rf.claim_type_id = ? and rf.dimension_id >0 and rf.dimension_grid_id>0 and rf.loading_discount_id > 0 and rf.status='Y'";
                final String[] args = {claimId};
				final String[][] claimResult= runner.multipleSelection(query,args);

                LogManager.debug("- Exit");
        		LogManager.popRemove();

				return claimResult;
			 }

			//query ForGetting SingleDimensionDiscrete values
			//Used
			public String[][] queryForGettingSingleDimensionDiscrete(final String ratingFactorSubId,final String premiumData,final String ratingFactorId,final String insuranceDate) throws BaseException
            {
				 LogManager.push("queryForGettingSingleDimensionDiscrete method()");
				 LogManager.debug("- Enter");
				 String temp = "sysdate";
				 temp = runner.getSysdate("01");		
					
                 final String[] args = {ratingFactorSubId,ratingFactorId,premiumData.toLowerCase(Locale.ENGLISH),ratingFactorSubId,ratingFactorId,premiumData.toLowerCase(Locale.ENGLISH)};
                 query = "select data_name,data_value,amend_id,relative_discrete_id from single_dimension_discrete where rating_factor_sub_id = ? and rating_factor_id = ? and  lower(data_name) = ? and status='Y' and effective_date < =(select "+temp+" from dual) and amend_id=(select max(amend_id) from single_dimension_discrete where rating_factor_sub_id = ?  and rating_factor_id = ? and  lower(data_name) = ? and status='Y'and effective_date <=(select "+temp+" from dual))";
			     final String[][] claimResult= runner.multipleSelection(query,args);

			     LogManager.debug("- Exit");
        		 LogManager.popRemove();

			     return claimResult;
    	 	}

			//query ForGetting SingleDimensionDiscrete values
		    //This is for single dimension discrete group starts
		    public String[][] getSingleDimensionDiscreteGroupInfoDetails(final String groupId,final String premiumData,final String ratingFactorId) throws BaseException
		    {
		    	LogManager.push("getSingleDimensionDiscreteGroupInfoDetails method()");
				LogManager.debug("- Enter");

                final String[] args = {premiumData.toLowerCase(Locale.ENGLISH),groupId,ratingFactorId};
			    query = "select data_name,data_from,data_to,data_value,new_table_id,rating_factor_id,dimension_grid_id,group_id from data_group_details where  lower(data_from) = ? and group_id  = ? and status='Y' and rating_factor_id = ?";
			    final String[][] claimResult= runner.multipleSelection(query,args);

			    LogManager.debug("- Exit");
        		LogManager.popRemove();

				return claimResult;
		    }

		    //for single column starts
		    public String[][] getSingleDimensionDiscreteGroupInfoDetails(final String groupId,final String tableId ,final String premiumData,final String ratingFactorId) throws BaseException
		    {
		    	LogManager.push("getSingleDimensionDiscreteGroupInfoDetails method()");
				LogManager.debug("- Enter");

                final String[] args = {premiumData.trim().toLowerCase(),groupId,ratingFactorId,tableId};
		        query = "select data_name,data_from,data_to,data_value,new_table_id,rating_factor_id,dimension_grid_id,group_id from data_group_details where  lower(data_from) = ? and group_id = ? and status='Y' and rating_factor_id = ? and new_table_id = ?";
		    	final String[][] claimResult= runner.multipleSelection(query,args);

		    	LogManager.debug("- Exit");
        		LogManager.popRemove();

				return claimResult;
		   }

		    //USed
            //This is for single dimension discrete group ends
            public String[][] getSingleDimensionDiscreteGroupInfoDetails(final String groupid,final String tableId ,final String premiumData,final String ratingFactorId,final String gridId) throws BaseException
		    {
            	LogManager.push("getSingleDimensionDiscreteGroupInfoDetails method()");
				LogManager.debug("- Enter");

        		query = "";
        		//String temp=premium_data;
                String  claimResult[][]=null;
               // String[] args = new String[0];

	            if("1".equalsIgnoreCase(gridId)){
		            final String [] args = {premiumData.toLowerCase(Locale.ENGLISH).trim(),groupid, ratingFactorId,tableId};
                    query="select 	data_name,data_from,data_to,data_value,new_table_id,rating_factor_id,dimension_grid_id,group_id from data_group_details where  lower(trim(data_from)) = ? and group_id = ? and status='Y' and rating_factor_id = ? and new_table_id = ?";
                    claimResult = runner.multipleSelection(query,args);
	            }
	            else if("2".equalsIgnoreCase(gridId))	{

			        if(validObj.isNumberValue(premiumData)){
			        	final String [] args = {groupid,ratingFactorId,tableId};
                        query="select 	data_name,data_from,data_to,data_value,new_table_id,rating_factor_id,dimension_grid_id,group_id from data_group_details where  group_id = ?   and  "+Double.parseDouble(premiumData)+"  between data_from and data_to and status='Y' and rating_factor_id = ? and new_table_id = ?";
                        claimResult = runner.multipleSelection(query,args);
			        }
			        else{
			        	final String [] args  = {groupid,ratingFactorId,tableId,premiumData.toLowerCase(Locale.ENGLISH),premiumData.toLowerCase(Locale.ENGLISH)};
                        query="select 	data_name,data_from,data_to,data_value,new_table_id,rating_factor_id,dimension_grid_id,group_id from data_group_details where  group_id = ? and status='Y' and rating_factor_id = ? and new_table_id = ? and lower(data_from ) = ? or lower(data_to) ?";
                        claimResult = runner.multipleSelection(query,args);
			        }
	            }

	            LogManager.debug("- Exit");
        		LogManager.popRemove();

				return claimResult;
		    }

		    //This is for getting the value starts
		    public String[][] getSingleDimensionDiscreteGroupValue(final String dataFrom,final String ratingFactorId,final String ratingFactorSubId) throws BaseException
		    {
		    	LogManager.push("getSingleDimensionDiscreteGroupValue method()");
				LogManager.debug("- Enter");

                final String[] args = {ratingFactorSubId,ratingFactorId,dataFrom};
				query = "select data_name,data_value from single_dimension_discrete where rating_factor_sub_id = ? and rating_factor_id = ? and  data_name= ? and status='Y'";
				final String[][]  claimResult= runner.multipleSelection(query,args);

				LogManager.debug("- Exit");
        		LogManager.popRemove();

				return claimResult;
        	}

		   //Used
		   public   String[][] queryForGettingSingleDimensionRange(final String ratingFactorSubId,String premiumData,final String ratingFactorId,String insuranceDate) throws BaseException
		   {
			    LogManager.push("queryForGettingSingleDimensionRange royals method()");
				LogManager.debug("- Enter");

				String  claimResult[][];
				if(premiumData.length()>0&&premiumData.indexOf(".")!=-1){
					premiumData = premiumData.substring(0,premiumData.indexOf("."));
                }
				LogManager.info("royal new test insuranceDate b4..."+insuranceDate);
				if(insuranceDate.length()>0&&insuranceDate.indexOf(":")!=-1){
					insuranceDate = insuranceDate.substring(0,insuranceDate.indexOf(" "));
				}
				LogManager.info("royal new test insuranceDate after..."+insuranceDate);
				if(validObj.isNumberValue(premiumData)){
						final String[] args = {ratingFactorId,ratingFactorSubId,insuranceDate,ratingFactorSubId,ratingFactorId,insuranceDate};
						query="select data_from,data_to,data_value,amend_id,relative_range_id from single_dimension_range where rating_factor_id = ? and  rating_factor_sub_id = ? and effective_date <= to_Date(?,'yyyy-mm-dd') and "+Long.parseLong(premiumData)+" between data_from and data_to and amend_id=(select max(amend_id) from single_dimension_range where rating_factor_sub_id = ?  and rating_factor_id = ? and effective_date <= to_Date(?,'yyyy-mm-dd') and "+Double.parseDouble(premiumData)+" between data_from and data_to)";
						claimResult= runner.multipleSelection(query,args);
				}
				else{
						final String[] args = {premiumData.toLowerCase(Locale.ENGLISH),premiumData.toLowerCase(Locale.ENGLISH),ratingFactorSubId,ratingFactorId};
                        query="select data_from,data_to,data_value from single_dimension_range where  lower(data_from) = ? or lower(data_to) = ? and rating_factor_sub_id = ? and rating_factor_id = ?";
                        claimResult= runner.multipleSelection(query,args);
				}

				LogManager.debug("- Exit");
        		LogManager.popRemove();

				return claimResult;
			}

			//Used
			public String[][] queryForGettingSingleDimensionGroupRange(final String ratingFactorSubId,final String premiumData,final String ratingFactorId,final String insuranceDate) throws BaseException
			{
				 	LogManager.push("queryForGettingSingleDimensionGroupRange method()");
					LogManager.debug("- Enter");

					final String  claimResult[][];
					if(validObj.isNumberValue(premiumData)){
						     final String[] args = {ratingFactorSubId,ratingFactorId,premiumData.toLowerCase(Locale.ENGLISH),OracleDateConversion.ConvertDate(insuranceDate),ratingFactorSubId,ratingFactorId,premiumData.toLowerCase(Locale.ENGLISH),OracleDateConversion.ConvertDate(insuranceDate)};
 							 query="select data_from,data_to,data_value,amend_id,relative_range_id from single_dimension_range where rating_factor_sub_id = ? and rating_factor_id = ? and lower(data_from) = ? and status='Y' and effective_date <= ? and amend_id=(select max(amend_id) from single_dimension_range where rating_factor_sub_id = ?  and rating_factor_id = ? and  lower(data_from)= ? and status='Y'and effective_date <= ?)";
							 claimResult= runner.multipleSelection(query,args);
					}
					else {
						final String[] args = {premiumData.toLowerCase(Locale.ENGLISH),premiumData.toLowerCase(Locale.ENGLISH),ratingFactorSubId,ratingFactorId,OracleDateConversion.ConvertDate(insuranceDate)};
                        query="select data_from,data_to,data_value from single_dimension_range where  lower(data_from) = ? or data_to = ? and rating_factor_sub_id = ? and rating_factor_id = ? and effective_date <= ?";
                        claimResult= runner.multipleSelection(query,args);
					}

					LogManager.debug("- Exit");
	        		LogManager.popRemove();

					return claimResult;
			}

         //Used
		//This is for Base Drive Screen starts
	    public String[][] getClaimNameInfo(final String claimTypeId, final String jsmode, final String jschannel, final String productId, String subProductId, String jscampaign,final String amendId,final String effectiveDate) throws BaseException
	    {
		    	LogManager.push("getClaimNameInfo method()");
				LogManager.debug("- Enter");

	            queryClaimName= "";
	            final String[][] getClaimTypeTbl;
	            jscampaign = (jscampaign==null || "null".equals(jscampaign) || "0".equals(jscampaign) || "".equals(jscampaign) || "0".equals(jscampaign))?"":jscampaign;
	            subProductId = (subProductId == null || "".equals(subProductId) || "null".equals(subProductId) || "0".equals(subProductId))?"":subProductId;


	            if ( jscampaign.length() == 0  && subProductId.length() == 0 ) {
                	final String[] args = {jsmode,jschannel,productId,claimTypeId};
                    queryClaimName = "select claim_type_name, claim_type_id, to_char(effective_date,'dd-mm-yyyy'), remarks, mode_id, channel_id, product_id  from claim_type where mode_id = ? and channel_id = ? and product_id = ? and claim_type_id = ? and (campaign_id is null or campaign_id=0) and (product_sub_id is null or product_sub_id=0) and status='Y'";
                    getClaimTypeTbl = runner.multipleSelection(queryClaimName,args);
                }
                else if ( jscampaign.length() == 0 ) {
            	       final String[] args = {jsmode,jschannel,productId,claimTypeId,subProductId};
                       queryClaimName = "select claim_type_name, claim_type_id, to_char(effective_date,'dd-mm-yyyy'), remarks, mode_id, channel_id, product_id, campaign_id, product_sub_id from claim_type where mode_id = ? and channel_id = ? and product_id = ? and claim_type_id = ? and (campaign_id is null or campaign_id=0) and product_sub_id = ? and status='Y'";
                       getClaimTypeTbl = runner.multipleSelection(queryClaimName,args);
                }
                else if ( subProductId.length() == 0 ) {
                		final String[] args = {jsmode,jschannel,productId,claimTypeId,jscampaign};
                        queryClaimName = "select claim_type_name, claim_type_id, to_char(effective_date,'dd-mm-yyyy'), remarks, mode_id, channel_id, product_id, campaign_id, product_sub_id from claim_type where mode_id = ? and channel_id = ? and product_id = ? and claim_type_id = ? and campaign_id = ? and (product_sub_id is null or product_sub_id=0) and status='Y'";
                        getClaimTypeTbl = runner.multipleSelection(queryClaimName,args);
                }
                else {
                	final String[] args = {jsmode,jschannel,productId,claimTypeId,jscampaign,subProductId};
                    queryClaimName = "select claim_type_name, claim_type_id, to_char(effective_date,'dd-mm-yyyy'), remarks, mode_id, channel_id, product_id, campaign_id, product_sub_id from claim_type where mode_id = ? and channel_id = ? and product_id = ? and claim_type_id = ? and campaign_id = ? and product_sub_id = ? and status='Y'";
                    getClaimTypeTbl = runner.multipleSelection(queryClaimName,args);
                }

                LogManager.debug("- Exit");
        		LogManager.popRemove();

	            return getClaimTypeTbl;
        } /* getClaimNameInfo(String claimTypeId, String amendId, String effectiveDate) */

        public String[][] getAllClaimNameInfo(final String claimTypeId, final String jsmode, final String jschannel,final String productId) throws BaseException
        {
        		LogManager.push("getAllClaimNameInfo method()");
        		LogManager.debug("- Enter");

	            queryClaimName= "";
                final String[] args = {jsmode,jschannel,productId,claimTypeId};
                queryClaimName = "select claim_type_name, claim_type_id, to_char(effective_date,'dd-mm-yyyy'), remarks, mode_id, channel_id, product_id  from claim_type where mode_id = ? and channel_id = ? and product_id = ? and claim_type_id = ?  and status='Y'";
                final String[][] getClaimTypeTbl = runner.multipleSelection(queryClaimName,args);

                LogManager.debug("- Exit");
        		LogManager.popRemove();

	            return getClaimTypeTbl;
        }

        //USed
        public String[][] getBaseDriverName(final String amendId, final String effectiveDate) throws BaseException
        {
	            LogManager.push("getBaseDriverName method()");
        		LogManager.debug("- Enter");

        		query  = "";
        		query = "select base_driver_name, base_driver_id from base_driver where status='Y'";
                final String[][] getBaseDriverTbl  = runner.multipleSelection(query);

                LogManager.debug("- Exit");
        		LogManager.popRemove();

	            return getBaseDriverTbl;
         }

        //Used
        public String[][] getBaseDriverType(final String claimTypeId,final String amendId,final String effectiveDate) throws BaseException
        {
        	 LogManager.push("getBaseDriverType method()");
     		 LogManager.debug("- Enter");

     		 query = "";
     		 final String[] args ={claimTypeId};
     		query = "select base_driver_type_id, attrition, largeloss, cat, net_premium, recovery, cost_of_capital, fixed_expenses, variable_expenses, commission, investment_return, loading_discount_id, loading_discount_sub_id, amend_id, inception_date, expiry_date, effective_date, entry_date, remarks from base_driver_type where claim_type_id = ? and status='Y'";
             final String[][] getBaseDriver = runner.multipleSelection(query,args);

             LogManager.debug("- Exit");
     		 LogManager.popRemove();

             return getBaseDriver;
        }

        //Used
        public String[][] getRatingFactorSub(final String baseDriverId,final String baseDriverSubId,final String claimTypeId, final String amendId, final String effectiveDate) throws BaseException
        {
        	LogManager.push("getRatingFactorSub method()");
    		LogManager.debug("- Enter");

            query = "";
            final String[] args = {baseDriverId,baseDriverSubId,claimTypeId};
            query = "select rating_factor_sub_id, rating_factor_sub_name, base_driver_id, base_driver_sub_id, claim_type_id, rating_factor_id, dimension_id, dimension_grid_id, x_name, y_name, amend_id, effective_date, entry_date, remarks, loading_discount_id from rating_factor_sub where base_driver_id = ? and base_driver_sub_id = ? and claim_type_id = ? and status='Y' and dimension_id is not null and dimension_grid_id is not null";
            final String[][] getRatFacSubTbl = runner.multipleSelection(query,args);

            LogManager.debug("- Exit");
    		LogManager.popRemove();

            return getRatFacSubTbl;
        }

        //Used
        public String[][] getBaseDriverSubName(final String amendId,final String effectiveDate,final  String baseDriverId) throws BaseException
        {
        	LogManager.push("getBaseDriverSubName method()");
    		LogManager.debug("- Enter");

            query = "";
            final String[] args = {baseDriverId};
            query = "select base_driver_sub_name, base_driver_sub_id, remarks from base_driver_sub where base_driver_id = ? and status='Y' order by base_driver_sub_id";
            final String[][] getBaseDriSubTbl = runner.multipleSelection(query,args);

            LogManager.debug("- Exit");
    		LogManager.popRemove();

            return getBaseDriSubTbl;
         }  /* getBaseDriverSubName(String amendId, String effectiveDate, String baseDriverId) */


        //This is for Group Checking Starts
        public String[][] getModifiedGroupName(final String ratingid) throws BaseException
        {
        	LogManager.push("getModifiedGroupName method()");
    		LogManager.debug("- Enter");

            final String[] args = {ratingid};
            final String[][] resGroupName;

		    final String groupIdSql="select group_id from product_field_master_new where rating_factor_id = ?";
		    final String grpid = runner.singleSelection(groupIdSql,args);
            if("0".equalsIgnoreCase(grpid)){
            		final String[] args1 =  {ratingid};
                    final String groupNameSql="select rating_factor_name from product_field_master_new where rating_factor_id = ?";
                    resGroupName = runner.multipleSelection(groupNameSql,args1);
            }
            else{
            		final String[] args2 = {grpid};
                    final String groupNameSql = "select group_name from group_master_new where group_id = ?";
                    resGroupName = runner.multipleSelection(groupNameSql,args2);
            }

            LogManager.debug("- Exit");
    		LogManager.popRemove();

            return resGroupName;
         }


         //Used
        public String[][] getModifiedGroup(final String ratingid) throws BaseException
        {
        		LogManager.push("getModifiedGroup method()");
        		LogManager.debug("- Enter");

                final String[] args = {ratingid};

	            //final  String groupIdSql="select group_id from product_field_master_new where rating_factor_id = ?";
	            //final  String grpid = runner.singleSelection(groupIdSql,args);

	            final String groupNameSql="select rating_factor_name from product_field_master_new where rating_factor_id = ?";
	            final String[][] resGroupName = runner.multipleSelection(groupNameSql,args);

	            LogManager.debug("- Exit");
	    		LogManager.popRemove();

                return resGroupName;
         }

        //Used
        public String[][] getGroupInfo(final String ratingId) throws BaseException
        {
        	  LogManager.push("getGroupInfo method()");
    		  LogManager.debug("- Enter");

              final String[] args = {ratingId};
	          query = "select pfm.group_id ,pfm.table_name,ctm.no_of_columns,ctm.new_table_id,gm.table_column_name from product_field_master_new pfm,created_table_master ctm, group_master_new gm where pfm.rating_factor_id = ? and pfm.table_name=ctm.new_table_name and gm.group_id=pfm.group_id and pfm.status='Y'";
	          final String[][] groupResult =runner.multipleSelection(query,args);

	          LogManager.debug("- Exit");
	    	  LogManager.popRemove();

	          return groupResult;
        }

        //mAKE TWO COLUMS AS one COLUMN
        public String[][]  structureMaintenancetSingleColumnFilterGroup(final String columnName,final String tableId) throws BaseException
        {
        	LogManager.push("structureMaintenancetSingleColumnFilterGroup method()");
   		  	LogManager.debug("- Enter");

            final String[] args = {columnName.toLowerCase(Locale.ENGLISH),tableId};
			query =  "select new_table_id, description_name from structure_maintenance where lower(description_name) != ?  and new_table_id = ?";
			final String[][]	groupResult = runner.multipleSelection(query,args);

			LogManager.debug("- Exit");
	    	LogManager.popRemove();

			return groupResult;
       }

        public String[][]  getDataValueSingleColumnFromTableName(final String tableName,final String colGet,final String premiumValue,final String columnName) throws BaseException
        {
        	LogManager.push("getDataValueSingleColumnFromTableName method()");
   		  	LogManager.debug("- Enter");

            query  ="select "+columnName+" from "+tableName+" where lower("+colGet+") = '"+premiumValue.toLowerCase(Locale.ENGLISH)+"'";
            final String[][] groupResult =runner.multipleSelection(query);

            LogManager.debug("- Exit");
	    	LogManager.popRemove();

	        return groupResult;
        }

        //mAKE TWO COLUMS AS one COLUMN
        public String[][]  structureMaintenanceFilterGroup(final String columnName,final String tableId) throws BaseException
        {
        	LogManager.push("structureMaintenanceFilterGroup method()");
   		  	LogManager.debug("- Enter");

            final String[] args = {columnName.toLowerCase(Locale.ENGLISH),tableId};
            query ="select new_table_id, description_name from structure_maintenance where lower(description_name) != ?  and new_table_id = ?";
           	final String[][] groupResult = runner.multipleSelection(query,args);

           	LogManager.debug("- Exit");
	    	LogManager.popRemove();

            return groupResult;
        }

        // aug 18 2006This is for getting the dimension whether grid_id falls to discrete or range starts
        //USed
        public String[][] getGridId(final String ratingid) throws BaseException
	    {
        	LogManager.push("getGridId method()");
   		  	LogManager.debug("- Enter");

   		  	final String[] args = {ratingid};
            query="select distinct(dimension_grid_id) from data_group_details where group_id =  ?";
            final String[][] groupResult = runner.multipleSelection(query,args);

            LogManager.debug("- Exit");
	    	LogManager.popRemove();

            return groupResult;
	   }

      //This is for getting the dimension whether grid_id falls to discrete or range starts
      //For Single Column starts
       public String[][]  getDataValueFromTableName(final String tableName,final String premiumValue,final String columnName) throws BaseException
       {
    	    LogManager.push("getDataValueFromTableName method()");
  		    LogManager.debug("- Enter");

	        query  ="select "+columnName+" from "+tableName+" where lower("+columnName+") = '"+premiumValue.toLowerCase(Locale.ENGLISH)+"'";
	        final String[][] groupResult = runner.multipleSelection(query);

	        LogManager.debug("- Exit");
	    	LogManager.popRemove();

	        return groupResult;
      }

        //For Single Column ends
        //For Three Column starts
        public String[][]  getDataValueFromTableName(final String tableName,final String colMin,final String colMax,final String premiumValue,final String columnName) throws BaseException
        {
		        //String premium_value_temp=premiumValue;
		        //long premium_values=0;

        	   LogManager.push("getDataValueFromTableName method()");
   		       LogManager.debug("- Enter");

		        final String[][] groupResult;
		        if(validObj.isNumberValue(premiumValue)){
		        	//<data value is nothing but a column name
		        	query= "select "+columnName+" from "+tableName+" where "+premiumValue+"  between "+colMin+" and  "+colMax;
		            groupResult = runner.multipleSelection(query);
		        }
		        else{
		        	query = "select "+columnName+" from "+tableName+" where lower("+colMin+") ='"+premiumValue.toLowerCase(Locale.ENGLISH)+"' or lower("+colMax+") ='"+premiumValue.toLowerCase(Locale.ENGLISH)+"'";
                    groupResult  = runner.multipleSelection(query);
                }

		        LogManager.debug("- Exit");
		    	LogManager.popRemove();

		        return groupResult;
        }

        //For Three Column starts
        //This is for Group Checking Ends
        //This is for two_dimension_discrete_discrete starts
        /*public synchronized  String[][] queryForGettingTwoDimensionDiscrete(String rating_factor_id,String another_rating_factor_id,String rating_factor_one,String rating_factor_another,String rating_factor_sub_id)
        {
        }*/
	    //This is for Discrete Range Starts
	    //This is for Discrete Range Ends

        /***Range Discrete Starts If its is plain then there is no need for x axis and y axis in order to get x id visit here****/
        public  String[][] queryForGettingTwoDimensionRangeGroupDiscrete(final String ratingFactorId,final String ratingFactorOne,final String ratingFactorSubId) throws BaseException
        {
        		LogManager.push("queryForGettingTwoDimensionRangeGroupDiscrete method()");
		        LogManager.debug("- Enter");

		        //String queryForGettingTwoDimensionRangeDiscrete="select x_data_name,y_data_name,ran_dis_id,x_id,y_id from two_dim_range_discrete where rating_factor_id='"+rating_factor_id+"' and  rating_factor_sub_id='"+rating_factor_sub_id+"' and lower(x_data_from)='"+rating_factor_one.toLowerCase()+"' and lower(x_data_to)='"++"'";
                final String[] args = {ratingFactorId,ratingFactorSubId,ratingFactorOne.toLowerCase(Locale.ENGLISH)};
	            query = "select x_data_name,y_data_name,ran_dis_id,x_id,y_id from two_dim_range_discrete where rating_factor_id = ? and  rating_factor_sub_id = ? and lower(x_data_name) = ?";
	            final String[][] claimResult= runner.multipleSelection(query,args);

	            LogManager.debug("- Exit");
		    	LogManager.popRemove();

				return claimResult;
        }

        //This is ffor y colum check starts
        public   String[][] queryForGettingTwoDimRanDisDetails(final String xId,final String yId,final String ranDisId,final String insuranceDate) throws BaseException
        {
        	//String queryForGettingTwoDimRanDisDetails="select x_id,y_id,ran_dis_id,data_value from two_dim_ran_dis_details where ran_dis_id='"+randisid+"' and x_id ='"+x_id+"' and y_id ='"+y_id+"' and effective_date <='"+OracleDateConversion.ConvertDate(insuranceDate)+"'";
	        //Modified By Brahmaiah 0n 24-jan-2007

        	LogManager.push("queryForGettingTwoDimRanDisDetails method()");
	        LogManager.debug("- Enter");

        	final String[] args = {ranDisId,xId,yId,OracleDateConversion.ConvertDate(insuranceDate),ranDisId,xId,yId,OracleDateConversion.ConvertDate(insuranceDate)};
	        query = "select x_id,y_id,ran_dis_id,data_value from two_dim_ran_dis_details where ran_dis_id = ? and x_id = ? and y_id = ? and effective_date <= ? and amend_id in (select max(amend_id) from two_dim_ran_dis_details where ran_dis_id = ? and x_id = ? and y_id =? and effective_date <= ?";
	        final String[][] claimResult= runner.multipleSelection(query,args);

	        LogManager.debug("- Exit");
	    	LogManager.popRemove();

	        return claimResult;
        }

        public  String[][] queryForGettingTwoDimDisRanDetails(final String xId,final String yId,final String disRanId,final String insuranceDate) throws BaseException
        {
        	LogManager.push("queryForGettingTwoDimDisRanDetails method()");
	        LogManager.debug("- Enter");

            final String[] args = {disRanId,xId,yId,OracleDateConversion.ConvertDate(insuranceDate),disRanId,xId,yId,OracleDateConversion.ConvertDate(insuranceDate)};
            query = "select x_id,y_id,dis_ran_id,data_value from two_dim_dis_ran_details where dis_ran_id = ? and x_id = ? and y_id = ? and effective_date <= ? and amend_id in (select max(amend_id) from two_dim_dis_ran_details where dis_ran_id = ? and x_id = ? and y_id = ? and effective_date <= ?";
			final String[][] claimResult= runner.multipleSelection(query,args);

			LogManager.debug("- Exit");
	    	LogManager.popRemove();

		    return claimResult;
        }

        public   String[][] queryForGettingTwoDimDisDisDetails(final String xId,final String yId,final String disdisid,final String insuranceDate) throws BaseException
        {
        	LogManager.push("queryForGettingTwoDimDisDisDetails method()");
	        LogManager.debug("- Enter");

            final String[] args = {disdisid,xId,yId,OracleDateConversion.ConvertDate(insuranceDate),disdisid,xId,yId,OracleDateConversion.ConvertDate(insuranceDate)};
            query="select x_id,y_id,dis_dis_id,data_value from two_dim_dis_dis_details where dis_dis_id = ? and x_id = ? and y_id = ? and effective_date <= ? and amend_id in (select max(amend_id) from two_dim_dis_dis_details where dis_dis_id  = ? and x_id = ? and y_id = ? and effective_date <= ?)";
	    	final String[][] claimResult= runner.multipleSelection(query,args);

	    	LogManager.debug("- Exit");
	    	LogManager.popRemove();

		    return claimResult;
     }

     //This is for two dimension range group discrete starts
    public   String[][] queryForGettingTwoDimensionRangeGroupPlainDiscrete(final String ratingFactorId,final String ratingFactorOne,final String ratingFactorSubId,final String axis) throws BaseException
    {
    	LogManager.push("queryForGettingTwoDimensionRangeGroupPlainDiscrete method()");
        LogManager.debug("- Enter");

        final String[] args = {ratingFactorId,ratingFactorSubId,ratingFactorOne.toLowerCase(Locale.ENGLISH)};
	    query = "select x_data_name,y_data_name,ran_dis_id,x_id,y_id from two_dim_range_discrete where rating_factor_id = ?  and rating_factor_sub_id = ? and lower(y_data_name) = ?";
        final  String[][] claimResult = runner.multipleSelection(query,args);

        LogManager.debug("- Exit");
    	LogManager.popRemove();

		return claimResult;
     }

     public  String[][] queryForGettingTwoDimensionDiscreteGroupPlainRange(final String ratingFactorId,final String ratingFactorOne,final String ratingFactorSubId,final String axis ) throws BaseException
     {
    	 LogManager.push("queryForGettingTwoDimensionDiscreteGroupPlainRange method()");
         LogManager.debug("- Enter");

         final String[] args = {ratingFactorId ,ratingFactorSubId ,ratingFactorOne.toLowerCase(Locale.ENGLISH)  };
		 query = "select x_data_name,y_data_name,dis_ran_id,x_id,y_id from two_dim_discrete_range where rating_factor_id = ?  and rating_factor_sub_id = ? and lower(y_data_from) = ?";
		 final String[][] claimResult = runner.multipleSelection(query,args);

		 LogManager.debug("- Exit");
		 LogManager.popRemove();

		 return claimResult;
    }

    public  String[][] queryForGettingTwoDimensionDiscreteGroupRange(final String ratingFactorId,final String ratingFactorOne,final String ratingFactorSubId,final String axis) throws BaseException
    {
    	LogManager.push("queryForGettingTwoDimensionDiscreteGroupRange method()");
        LogManager.debug("- Enter");

        final String[] args = {ratingFactorId,ratingFactorSubId,ratingFactorOne.toLowerCase(Locale.ENGLISH)};
		query = "select x_data_name,y_data_name,dis_ran_id,x_id,y_id from two_dim_discrete_range where rating_factor_id = ?  and rating_factor_sub_id = ? and lower(y_data_name) = ?";
		final String[][] claimResult = runner.multipleSelection(query,args);

		LogManager.debug("- Exit");
		LogManager.popRemove();

		return claimResult;
    }

    public  String[][] queryForGettingTwoDimensionDiscreteGroupRange(final String ratingFactorId,final String ratingFactorOne,final String ratingFactorSubId) throws BaseException
    {
    	LogManager.push("queryForGettingTwoDimensionDiscreteGroupRange method()");
        LogManager.debug("- Enter");

        final String[] args = {ratingFactorId,ratingFactorSubId,ratingFactorOne.toLowerCase(Locale.ENGLISH)};
        query = "select x_data_name,y_data_name,dis_ran_id,x_id,y_id from two_dim_discrete_range where rating_factor_id = ? and  rating_factor_sub_id = ? and lower(x_data_name) = ?";

        final String[][] claimResult = runner.multipleSelection(query,args);

        LogManager.debug("- Exit");
		LogManager.popRemove();

        return claimResult;
    }

    /***Range Discrete Ends If its is plain then there is no need for x axis and y axis in order to get yid visit here****/
    //Discrete Discrete Starts
    public String[][] queryForGettingTwoDimensionDiscreteGroupDiscrete(final String ratingFactorId,final String ratingFactorOne,final String ratingFactorSubId,final String axis) throws BaseException
    {
    	LogManager.push("queryForGettingTwoDimensionDiscreteGroupDiscrete method()");
        LogManager.debug("- Enter");

        final String[] args = {ratingFactorId,ratingFactorSubId,ratingFactorOne.toLowerCase(Locale.ENGLISH)};
		query = "select x_data_name,y_data_name,dis_dis_id,x_id,y_id from two_dim_discrete_discrete where rating_factor_id = ?  and rating_factor_sub_id = ? and lower(y_data_name) = ? and status='Y'";
		final String[][] claimResult = runner.multipleSelection(query,args);

		LogManager.debug("- Exit");
		LogManager.popRemove();

		return claimResult;
    }

    //This is for TwoDimensionRangeGroupRange
    //This is for TwoDimensionRangeGroupRange
    public  String[][] queryForGettingTwoDimensionDiscreteGroupDiscrete(final String ratingFactorId,final String ratingFactorOne,final String ratingFactorSubId) throws BaseException
    {
    	LogManager.push("queryForGettingTwoDimensionDiscreteGroupDiscrete method()");
        LogManager.debug("- Enter");

	    final String[] args  = {ratingFactorId,ratingFactorSubId,ratingFactorOne.toLowerCase(Locale.ENGLISH)};
		query = "select x_data_name,y_data_name,dis_dis_id,x_id,y_id from two_dim_discrete_discrete where rating_factor_id = ? and  rating_factor_sub_id = ? and lower(x_data_name) = ? and status='Y'";
		final String[][] claimResult = runner.multipleSelection(query,args);

		LogManager.debug("- Exit");
		LogManager.popRemove();

		return claimResult;
    }

    //Discrete Discrete Ends
    //Range Range starts
    public  String[][] queryForGettingTwoDimensionRangeGroupRange(final String ratingFactorId,final String ratingFactorOne,final String ratingFactorSubId) throws BaseException
    {
    	LogManager.push("queryForGettingTwoDimensionRangeGroupRange method()");
        LogManager.debug("- Enter");

        final  String[] args = {ratingFactorId,ratingFactorSubId,ratingFactorOne.toLowerCase(Locale.ENGLISH),ratingFactorOne.toLowerCase(Locale.ENGLISH)};
        query = "select '','',ran_ran_id,x_id,y_id from two_dim_range_range where rating_factor_id = ? and  rating_factor_sub_id = ? and (lower(x_data_from) = ? or lower(x_data_to) = ?)";
    	final String[][] claimResult = runner.multipleSelection(query,args);

    	LogManager.debug("- Exit");
		LogManager.popRemove();

		return claimResult;
    }

    public String[][] queryForGettingTwoDimensionRangeGroupRange(final String ratingFactorId,final String ratingFactorOne,final String ratingFactorSubId,final String  axis) throws BaseException
    {
    	LogManager.push("queryForGettingTwoDimensionRangeGroupRange method()");
        LogManager.debug("- Enter");

        final  String[] args = {ratingFactorId,ratingFactorSubId,ratingFactorOne.toLowerCase(Locale.ENGLISH),ratingFactorOne.toLowerCase(Locale.ENGLISH)};
        query = "select '','',ran_ran_id,x_id,y_id from two_dim_range_range where rating_factor_id = ? and  rating_factor_sub_id = ? and (lower(y_data_from) = ? or lower(y_data_to) = ?)";
        final String[][] claimResult = runner.multipleSelection(query,args);

    	LogManager.debug("- Exit");
		LogManager.popRemove();

		return claimResult;
    }
    //Range Range Ends

    //This is for two dimension range group discrete ends
    public   String[][] queryForGettingTwoDimensionRangeGroupDiscrete(final String ratingFactorId,final String ratingFactorOne,final String ratingFactorSubId,final String axis) throws BaseException
    {
    	LogManager.push("queryForGettingTwoDimensionRangeGroupDiscrete method()");
        LogManager.debug("- Enter");

        final String[] args = {ratingFactorId,ratingFactorSubId,ratingFactorOne.toLowerCase(Locale.ENGLISH)};
		query = "select x_data_name,y_data_name,ran_dis_id,x_id,y_id from two_dim_range_discrete where rating_factor_id = ?  and rating_factor_sub_id = ? and lower(y_data_name) =?";
		final String[][] claimResult =  runner.multipleSelection(query,args);

		LogManager.debug("- Exit");
		LogManager.popRemove();

		return claimResult;
    }

    public  String[][] queryForGettingTwoDimensionDiscreteRangePlainPlain(final String ratingFactorId,final String ratingFactorOne,final String ratingFactorSubId) throws BaseException
    {
    	LogManager.push("queryForGettingTwoDimensionDiscreteRangePlainPlain method()");
        LogManager.debug("- Enter");

        final String[][] claimResult;

        if(validObj.isNumberValue(ratingFactorOne)){
        	final String[] args = {ratingFactorId,ratingFactorSubId,ratingFactorOne};
        	query ="select x_data_name,y_data_name,dis_ran_id,x_id,y_id,y_data_from,y_data_to  from two_dim_discrete_range where rating_factor_id = ? and  rating_factor_sub_id = ? and  ? between y_data_from and y_data_to";
        	claimResult= runner.multipleSelection(query,args);
        }
        else{
        	   final String[] args = {ratingFactorId,ratingFactorSubId,ratingFactorOne,ratingFactorOne};
               query="select x_data_name,y_data_name,ran_dis_id,x_id,y_id,y_data_from,y_data_to from two_dim_discrete_range where rating_factor_id =? and  rating_factor_sub_id = ? and  y_data_from = ? or y_data_to = ?";
               claimResult= runner.multipleSelection(query,args);
        }

        LogManager.debug("- Exit");
		LogManager.popRemove();

        return claimResult;
    }

    public  String[][] queryForGettingTwoDimensionRangeDiscretePlainPlain(final String ratingFactorId,final String ratingFactorOne,final String ratingFactorSubId) throws BaseException
    {
    	LogManager.push("queryForGettingTwoDimensionRangeDiscretePlainPlain method()");
        LogManager.debug("- Enter");

        final String[] args = {ratingFactorId,ratingFactorSubId,ratingFactorOne};
        query = "select x_data_name,y_data_name,ran_dis_id,x_id,y_id,x_data_from,x_data_to from two_dim_range_discrete where rating_factor_id = ? and  rating_factor_sub_id = ? and  lower(y_data_name) = ?";
        final String[][] claimResult = runner.multipleSelection(query,args);

        LogManager.debug("- Exit");
		LogManager.popRemove();

		return claimResult;
    }

    public  String[][] queryForGettingTwoDimensionRangeDiscrete(final String ratingFactorId,final String ratingFactorOne,final String ratingFactorSubId) throws BaseException
    {
    	LogManager.push("queryForGettingTwoDimensionRangeDiscrete method()");
        LogManager.debug("- Enter");

        final String[][] claimResult;

        if(validObj.isNumberValue(ratingFactorOne)){
        	final String[] args = {ratingFactorId,ratingFactorSubId};
			query = "select x_data_name,y_data_name,ran_dis_id,x_id,y_id,x_data_from,x_data_to  from two_dim_range_discrete where rating_factor_id  = ? and  rating_factor_sub_id = ? and  '"+Long.parseLong(ratingFactorOne)+"' between x_data_from and x_data_to";
			claimResult= runner.multipleSelection(query,args);
        }
        else{
        	 final String[] args = {ratingFactorId ,ratingFactorSubId , ratingFactorOne ,ratingFactorOne };
        	 query = "select x_data_name,y_data_name,ran_dis_id,x_id,y_id,x_data_from,x_data_to from two_dim_range_discrete where rating_factor_id =? and  rating_factor_sub_id = ? and  x_data_from = ? or x_data_to = ?";
        	 claimResult= runner.multipleSelection(query,args);
        }

        LogManager.debug("- Exit");
		LogManager.popRemove();

		return claimResult;
    }
    /***Range Discrete Ends If its is plain then there is no need for x axis and y axis in order to get yid visit here****/


    //This is for x colum check starts
    //Previously it was queryForGettingTwoDimensionRange_Discrete -- Method name
    public   String[][] getTwoDimensionRangeDiscrete(final String ratingFactorId,final String ratingFactorOne,final  String ratingFactorSubId) throws BaseException
    {
    	LogManager.push("getTwoDimensionRangeDiscrete method()");
        LogManager.debug("- Enter");

        final String[][] claimResult;
        if(validObj.isNumberValue(ratingFactorOne)){
        	final String[] args = {ratingFactorId,ratingFactorSubId};
			query = "select x_data_name,y_data_name,ran_dis_id,x_id,y_id,x_data_from,x_data_to  from two_dim_range_discrete where rating_factor_id = ? and  rating_factor_sub_id = ? and  '"+Long.parseLong(ratingFactorOne)+"' between x_data_from and x_data_to";
			claimResult= runner.multipleSelection(query,args);
        }
        else{
        	 final String[] args =  {ratingFactorId,ratingFactorSubId,ratingFactorOne.toLowerCase(Locale.ENGLISH),ratingFactorOne.toLowerCase(Locale.ENGLISH)};
             query="select x_data_name,y_data_name,ran_dis_id,x_id,y_id,x_data_from,x_data_to from two_dim_range_discrete where rating_factor_id = ? and  rating_factor_sub_id = ? and  lower(x_data_from) =? or lower(x_data_to) = ?";
             claimResult= runner.multipleSelection(query,args);
        }

        LogManager.debug("- Exit");
		LogManager.popRemove();

		return claimResult;
    }

    //Previously below method name is queryForGettingTwoDimensionDiscrete_Range
    public   String[][] getTwoDimensionDiscreteRange(final String ratingFactorId,final String ratingFactorOne,final String ratingFactorSubId) throws BaseException
    {
    	LogManager.push("getTwoDimensionDiscreteRange method()");
        LogManager.debug("- Enter");

        final String[] args = {ratingFactorId,ratingFactorSubId, ratingFactorOne.toLowerCase(Locale.ENGLISH)};
		query = "select x_data_name,y_data_name,dis_ran_id,x_id,y_id from two_dim_discrete_range where rating_factor_id = ? and  rating_factor_sub_id = ? and lower(x_data_name) = ?";
		final String[][] claimResult = runner.multipleSelection(query,args);

		LogManager.debug("- Exit");
		LogManager.popRemove();

		return claimResult;
    }

    //Range Starts
     public   String[][] queryForGettingTwoDimensionRangeRange(final String ratingFactorId,final String ratingFactorOne,final String ratingFactorSubId,final String axis) throws BaseException
    {
    	 LogManager.push("queryForGettingTwoDimensionRangeRange method()");
         LogManager.debug("- Enter");

         final String[][] claimResult;

         if(validObj.isNumberValue(ratingFactorOne)){
        	 final String[] args = {ratingFactorId,ratingFactorSubId};
             query = "select '','',ran_ran_id,x_id,y_id,x_data_from,x_data_to  from two_dim_range_range where rating_factor_id = ? and  rating_factor_sub_id = ? and  '"+Long.parseLong(ratingFactorOne)+"' between y_data_from and y_data_to";
             claimResult= runner.multipleSelection(query,args);
         }
         else{
        	 final String[] args = {ratingFactorId,ratingFactorSubId,ratingFactorOne.toLowerCase(Locale.ENGLISH),ratingFactorOne.toLowerCase(Locale.ENGLISH)};
			 query = "select '','',ran_ran_id,x_id,y_id,x_data_from,x_data_to from two_dim_range_range where rating_factor_id = ? and  rating_factor_sub_id = ? and  lower(y_data_from) = ? or y_data_to =  ?";
			 claimResult= runner.multipleSelection(query,args);
         }

         LogManager.debug("- Exit");
 		 LogManager.popRemove();

		return claimResult;
    }

    public  String[][] queryForGettingTwoDimensionRangeRange(final String ratingFactorId,final String ratingFactorOne,final String ratingFactorSubId) throws BaseException
    {
    	LogManager.push("queryForGettingTwoDimensionRangeRange method()");
    	LogManager.debug("- Enter");

    	final String[][] claimResult;
    	if(validObj.isNumberValue(ratingFactorOne)){
     		  final String[] args = {ratingFactorId,ratingFactorSubId};
			  query = "select '','',ran_ran_id,x_id,y_id,x_data_from,x_data_to  from two_dim_range_range where rating_factor_id = ? and  rating_factor_sub_id = ? and  '"+Long.parseLong(ratingFactorOne)+"' between x_data_from and x_data_to";
			  claimResult= runner.multipleSelection(query,args);
    	}
    	else{
    		 final String[] args = {ratingFactorId,ratingFactorSubId,ratingFactorOne.toLowerCase(Locale.ENGLISH),ratingFactorOne.toLowerCase(Locale.ENGLISH)};
			 query = "select '','',ran_ran_id,x_id,y_id,x_data_from,x_data_to from two_dim_range_range where rating_factor_id = ? and  rating_factor_sub_id = ? and  lower(x_data_from) = ? or x_data_to = ?";
			 claimResult= runner.multipleSelection(query,args);
    	}

    	LogManager.debug("- Exit");
		LogManager.popRemove();

        return claimResult;
   }
   //Range Ends

    public   String[][] queryForGettingTwoDimensionDiscrete(final String ratingFactorId,final String ratingFactorOne,final String ratingFactorSubId) throws BaseException
    {
    	LogManager.push("queryForGettingTwoDimensionDiscrete method()");
    	LogManager.debug("- Enter");

    	final String[] args = {ratingFactorId,ratingFactorSubId,ratingFactorOne.toLowerCase(Locale.ENGLISH)};
		query = "select x_data_name,y_data_name,dis_dis_id,x_id,y_id from two_dim_discrete_discrete where rating_factor_id = ? and  rating_factor_sub_id = ? and lower(x_data_name) = ? and status='Y'";
		final  String[][] claimResult = runner.multipleSelection(query,args);

		LogManager.debug("- Exit");
		LogManager.popRemove();

		return claimResult;
    }

    //This is ffor x colum check ends
    //This is ffor y colum check starts
    public   String[][] queryForGettingTwoDimensionDiscrete(final String ratingFactorId,final String ratingFactorOne,final String ratingFactorSubId,final String axis) throws BaseException
    {
    	LogManager.push("queryForGettingTwoDimensionDiscrete method()");
    	LogManager.debug("- Enter");

        final String[] args = {ratingFactorId,ratingFactorSubId,ratingFactorOne.toLowerCase(Locale.ENGLISH)};
		query = "select x_data_name,y_data_name,dis_dis_id,x_id,y_id from two_dim_discrete_discrete where rating_factor_id = ?  and rating_factor_sub_id = ? and lower(y_data_name) =  ? and status='Y'";
		final  String[][] claimResult = runner.multipleSelection(query,args);

		LogManager.debug("- Exit");
		LogManager.popRemove();

		return claimResult;
    }

    public   String[][] queryForGettingTwoRanRanDetails(final String xId,final String yId,final String ranRanId,final String insuranceDate) throws BaseException
    {
    	LogManager.push("queryForGettingTwoRanRanDetails method()");
    	LogManager.debug("- Enter");

    	final String[] args = {ranRanId,xId,yId,OracleDateConversion.ConvertDate(insuranceDate),ranRanId,xId,yId,OracleDateConversion.ConvertDate(insuranceDate)};
	    query="select x_id,y_id,ran_ran_id,data_value from two_dim_ran_ran_details where ran_ran_id = ? and x_id = ? and y_id = ? and effective_date <=? and amend_id in (select max(amend_id) from two_dim_ran_ran_details where ran_ran_id = ? and x_id = ? and y_id = ? and effective_date <= ?)";
	    final String[][] claimResult = runner.multipleSelection(query,args);

	    LogManager.debug("- Exit");
		LogManager.popRemove();

	    return claimResult;
    }

    //queryForGettingTwoDimDisDetails starts
    public   String[][] queryForGettingTwoDimDisDetails(final String xId,final String yId,final String disDisId,final String insuranceDate) throws BaseException
    {
    	LogManager.push("queryForGettingTwoDimDisDetails method()");
    	LogManager.debug("- Enter");

    	final String[] args = {disDisId,xId,yId,OracleDateConversion.ConvertDate(insuranceDate)};
	    query = "select x_id,y_id,dis_dis_id,data_value from two_dim_dis_dis_details where dis_dis_id = ? and x_id = ? and y_id = ? and effective_date <=?";
	    final String[][] claimResult = runner.multipleSelection(query,args);

	    LogManager.debug("- Exit");
		LogManager.popRemove();

	    return claimResult;
    }

    public String[][] queryForGettingComboDisplay(final String claimId,final String ratingFactorName) throws BaseException
	{
    	LogManager.push("queryForGettingComboDisplay method()");
    	LogManager.debug("- Enter");

        final String[] args = {ratingFactorName.toLowerCase(Locale.ENGLISH)};
        query = "select distinct(rfs.rating_factor_id),pfm.rating_factor_name,pfm.group_id,pfm.table_name,ctm.no_of_columns,ctm.new_table_id,gm.table_column_name from rating_factor_sub rfs,product_field_master_new pfm,created_table_master ctm, group_master_new gm  where rfs.claim_type_id in  ("+claimId+") and rfs.dimension_id is not null and rfs.dimension_grid_id is not null and rfs.loading_discount_id is not null  and rfs.rating_factor_id=pfm.rating_factor_id and gm.group_id=pfm.group_id and pfm.table_name=ctm.new_table_name and rfs.status='Y' and lower(pfm.rating_factor_name) = ? ";
		String[][] claimResult = runner.multipleSelection(query,args);
		if(claimResult.length<=0)	{
			query="select '',pfm.rating_factor_name,pfm.group_id,pfm.table_name,ctm.no_of_columns,ctm.new_table_id,gm.table_column_name from product_field_master_new pfm,created_table_master ctm, group_master_new gm  where gm.group_id=pfm.group_id and pfm.table_name=ctm.new_table_name and pfm.status= 'Y'and lower(pfm.rating_factor_name) = ?";
			claimResult= runner.multipleSelection(query,args);
		}

		LogManager.debug("- Exit");
		LogManager.popRemove();

		return claimResult;
    }

    /*public StringBuffer displayCombo(final String tableName,final String columnName) throws BaseException
    {
    	LogManager.push("DisplayCombo method()");
    	LogManager.debug("- Enter");

	    query  = "select "+columnName+" from "+tableName;
        StringBuffer stb = new StringBuffer(1000);
	    String itemIdentifier = "";
		final String comboDetails[][] = runner.multipleSelection(query);
		stb.append("<select name='premium_value'><option value =''>premium_value</option>");
		//stb.append("<option value =''>premium_value</option>");
		for(int i = 0; i < comboDetails.length;i++)
		{
					stb.append(" <option value = '"+comboDetails[i][0]+"' "+itemIdentifier+">"+StringUtil.upperFirstChar(comboDetails[i][0])+"</option>");
		}
		stb.append("</select>");

		LogManager.debug("- Exit");
		LogManager.popRemove();

		return  stb;
      }*/

	 /*********************This is for combo box end************************/
	/***********************************This is for all Claims under a product*******************/

	public   String[][] allProductClaimDetails(final String claimId) throws BaseException
	{
		LogManager.push("allProductClaimDetails method()");
    	LogManager.debug("- Enter");

		query="select distinct(rfs.rating_factor_id),pfm.rating_factor_name from rating_factor_sub rfs,product_field_master_new pfm  where rfs.claim_type_id in ("+claimId+") and rfs.dimension_id is not null and rfs.dimension_grid_id is not null and rfs.loading_discount_id is not null and rfs.status='Y' and rfs.rating_factor_id=pfm.rating_factor_id and rfs.dimension_id != 3 ";
		final String  claimResult[][]= runner.multipleSelection(query);

		LogManager.debug("- Exit");
		LogManager.popRemove();

		return claimResult;
	}

	public String[][] getDimensionProductClaimGridId(final String ratingFactorId,final String claimId) throws BaseException
    {
		LogManager.push("allProductClaimDetails method()");
    	LogManager.debug("- Enter");

          final  String[] args = {ratingFactorId};
		  query="select rating_factor_sub_name,dimension_id,dimension_grid_id,loading_discount_id,rating_factor_id,rating_factor_sub_id from rating_factor_sub where rating_factor_id = ? and dimension_id is not null and dimension_grid_id is not null and loading_discount_id is not null  and status='Y' and claim_type_id in ("+claimId+")";
		  final String  claimResult[][] = runner.multipleSelection(query,args);

		  LogManager.debug("- Exit");
		  LogManager.popRemove();

		  return claimResult;
	}


	/**********************This is for all Claims under a product*************************/
	public String[][] getTotalCoeff(final String ratingFactorSubId) throws BaseException
	{
		LogManager.push("getTotalCoeff method()");
    	LogManager.debug("- Enter");

		final String[] args = {ratingFactorSubId};
        query="select total_coeff from interdetails where rating_factor_sub_id  = ?";
		final String  claimResult[][] =runner.multipleSelection(query,args);

		LogManager.debug("- Exit");
		LogManager.popRemove();

		return claimResult;
	}

    //Used
    public HashMap getClaimType(final String claimId) throws BaseException
	{
    	LogManager.push("getClaimType method()");
    	LogManager.debug("- Enter");

		HashMap claimType=new HashMap();
		query="select claim_type_id,mode_id,channel_id,product_id,amend_id,actuarial,suminsured_Y_N from claim_type where claim_type_id in ("+claimId+") and status='Y'";
		final String  claimResult[][]=runner.multipleSelection(query);
        claimResult[0][0]=claimResult[0][0]==null?"0":claimResult[0][0];
        claimResult[0][5]=claimResult[0][5]==null?"0":claimResult[0][5];
        claimResult[0][6]=claimResult[0][6]==null?"0":claimResult[0][6];
		claimType.put("claimId",claimResult[0][0]);
		claimType.put("actuarialType",claimResult[0][5]);
		claimType.put("sumYN",claimResult[0][6]);

		LogManager.debug("- Exit");
		LogManager.popRemove();

		return claimType;
	}

    //Used
    public HashMap getClaimTypeDetail(final String claimId) throws BaseException
	{
    	LogManager.push("getClaimTypeDetail method()");
    	LogManager.debug("- Enter");

    	Map claimType =  new HashMap();
		query="select rate,absolute_amount,dep_another_claim_y_n,dep_another_claim,premium_suminsured,rate_type from claim_type_detail where claim_type_id in ("+claimId+") and status='Y'";
        final String[][]  claimResult = runner.multipleSelection(query);

        claimResult[0][0]=claimResult[0][0]==null?"0":claimResult[0][0];
        claimResult[0][1]=claimResult[0][1]==null?"0":claimResult[0][1];
        claimResult[0][2]=claimResult[0][2]==null?"0":claimResult[0][2];
        claimResult[0][3]=claimResult[0][3]==null?"0":claimResult[0][3];
        claimResult[0][4]=claimResult[0][4]==null?"0":claimResult[0][4];
        claimResult[0][5]=claimResult[0][5]==null?"0":claimResult[0][5];
		claimType.put("rate",claimResult[0][0]);
		claimType.put("absolute",claimResult[0][1]);
		claimType.put("anotherClaimDep",claimResult[0][2]);
		claimType.put("anotherClaimDepIds",claimResult[0][3]);
		claimType.put("premiumSI",claimResult[0][4]);
		claimType.put("rateType",claimResult[0][5]);

		LogManager.debug("- Exit");
		LogManager.popRemove();

		return (HashMap)claimType;
	}

    public String[][] makeTwoDimArray(final String combined)  throws BaseException
	{
    	LogManager.push("makeTwoDimArray method()");
    	LogManager.debug("- Enter");

        int count=0;
       // int jcount=0;
        StringTokenizer st1=new StringTokenizer(combined,"~");
        String[][] convertedArray=new String[st1.countTokens()][1];
        while (st1.hasMoreTokens()){
                String w1 = st1.nextToken();
                convertedArray[count][0]=w1;
                //jcount=0;
                count=count+1;
        }

        LogManager.debug("- Exit");
		LogManager.popRemove();

		return convertedArray;
     }

   /* public String[][] getOrderedDisplay_one(final String claimIDS) throws BaseException
	{
		String[][] original=new String[0][0];
		String[][] orderedDis_D=new String[0][0];
		String[][] orderedDis_F=new String[0][0];
		HashMap order=new HashMap();
		String preSI="";
		String dep="";
		String depIds="";
		original=makeTwoDimArray_Comma(claimIDS);
		int decrement=original.length;
		for(int i=0;i<original.length;i++){
					order=getClaimTypeDetail(original[i][0]);
					preSI=(String)order.get("premiumSI");
					dep=(String)order.get("anotherClaimDep");
					depIds=(String)order.get("anotherClaimDepIds");
					orderedDis_D=makeTwoDimArray(depIds);
					if(dep.equalsIgnoreCase("Y") && preSI.equalsIgnoreCase("PREMIUM")){
								for(int ii=0;ii<orderedDis_D.length;i++){
								        orderedDis_F[decrement-1][0]=orderedDis_D[ii][0];
								}
					}
                    else{
								orderedDis_F[decrement-1][0]=original[i][0];
					}
		            decrement=decrement-1;
		}
        return orderedDis_F;
	}
*/
    public String[][] makeTwoDimArrayComma(final String combined) throws BaseException
	{
    	LogManager.push("makeTwoDimArrayComma method()");
    	LogManager.debug("- Enter");

        int count=0;
        //int jcount=0;
       final StringTokenizer st1=new StringTokenizer(combined,",");
        String[][] convertedArray=new String[st1.countTokens()][1];
        while (st1.hasMoreTokens()){
                convertedArray[count][0]=st1.nextToken();
                //jcount=0;
                count=count+1;
        }

        LogManager.debug("- Exit");
		LogManager.popRemove();

		return convertedArray;
    }

   /* public String[][] getClaimName(final String claimId) throws BaseException
	{
            String[] args = new String[1];
            args[0] = claimId;
			String query="select claim_type_name from claim_type where claim_type_id = ?";
			String  claimResult[][]=new String[0][0];
			try{
					claimResult= runner.multipleSelection(query,args);
			}
			catch(Exception e){
                e.printStackTrace();
			}
			return claimResult;
	}*/

	/*public String getOrderedDisplay(final String claimIDS) throws BaseException
	{
		String finalClaims="";
		String[][] initialClaimIds=new String[0][0];
		String[][] depClaimIds=new String[0][0];
		HashMap depH=new HashMap();
		Set finalSet=Collections.synchronizedSet(new HashSet());
		String preSI="";
		String dep="";
		String depIds="";
		initialClaimIds=makeTwoDimArrayComma(claimIDS);
		 for(int initial=0;initial<initialClaimIds.length;initial++)
        {
		        depH=getClaimTypeDetail(initialClaimIds[initial][0]);
				depIds=(String)depH.get("anotherClaimDepIds")==null?"0":(String)depH.get("anotherClaimDepIds");
				depClaimIds=makeTwoDimArray(depIds);
				for(int initial_d=0;initial_d<depClaimIds.length;initial_d++){
						if((depClaimIds[initial_d][0].trim()).equalsIgnoreCase("0")){
							finalSet.add(initialClaimIds[initial][0]);
						}
						else{
							depClaimIds[initial_d][0]=depClaimIds[initial_d][0].trim();
							finalSet.add(depClaimIds[initial_d][0]);
                        }
				}
		}
		for(int initial_F=0;initial_F<initialClaimIds.length;initial_F++) {
		            finalSet.add(initialClaimIds[initial_F][0]);
		}
		Iterator it=finalSet.iterator();
		while(it.hasNext()){
			finalClaims=finalClaims+it.next()+",";
		}
		finalClaims=finalClaims.substring(0,finalClaims.lastIndexOf(','));
		return finalClaims;
	}*/

    /*public String getOrderedDisplayArray(final String claimIDS) throws BaseException
	{
		String finalClaims="";
		String[][] initialClaimIds=new String[0][0];
		String[][] depClaimIds=new String[0][0];
		HashMap depH=new HashMap();
		ArrayList finalSet=new ArrayList();
		String preSI="";
		String dep="";
		String depIds="";
		initialClaimIds=makeTwoDimArrayComma(claimIDS);
		 for(int initial=0;initial<initialClaimIds.length;initial++)
        {
				depH=getClaimTypeDetail(initialClaimIds[initial][0]);
				depIds=(String)depH.get("anotherClaimDepIds")==null?"0":(String)depH.get("anotherClaimDepIds");
				depClaimIds=makeTwoDimArray(depIds);
				for(int initial_d=0;initial_d<depClaimIds.length;initial_d++)
				{
						if((depClaimIds[initial_d][0].trim()).equalsIgnoreCase("0")){
							        finalSet.add(initialClaimIds[initial][0]);
						}
						else{
							depClaimIds[initial_d][0]=depClaimIds[initial_d][0].trim();
							if(!finalSet.contains(depClaimIds[initial_d][0])){
							    finalSet.add(depClaimIds[initial_d][0]);
							}
						}
				}
		}
		for(int initial_F=0;initial_F<initialClaimIds.length;initial_F++) {
		        if(!finalSet.contains(initialClaimIds[initial_F][0])){
		            finalSet.add(initialClaimIds[initial_F][0]);
			    }
		}
		Iterator it=finalSet.iterator();
		while(it.hasNext()){
			finalClaims=finalClaims+it.next()+",";
		}
		finalClaims=finalClaims.substring(0,finalClaims.lastIndexOf(','));
		return finalClaims;
	}
*/
   /* public String[][] getContentDatas() throws BaseException
	{

			query="select data_from,data_to,data_value,amend_id,relative_range_id from single_dimension_range where rating_factor_id='267' and  rating_factor_sub_id ='606' and effective_date <= (select sysdate from dual) and data_value='1' and amend_id=(select max(amend_id) from single_dimension_range where rating_factor_sub_id='606'  and rating_factor_id ='267')";
			final String[][]  claimResult = runner.multipleSelection(query);

			return claimResult;
	}*/

    //Used
    public String[][] getPremiumClaimInfo(final String productId) throws BaseException
    {
    	LogManager.push("getPremiumClaimInfo method()");
    	LogManager.debug("- Enter");

    	queryClaimName= "";
        final  String[] args = {productId};
    	queryClaimName = "select claim_type_name, claim_type_id, to_char(effective_date,'dd-mm-yyyy'), remarks, mode_id, channel_id, product_id from claim_type where product_id = ? and claim_type_id in (select claim_type_id from claim_type where claim_type_name like 'HOME_COVER')  and status='Y'";

    	final String[][] getClaimTypeTbl =runner.multipleSelection(queryClaimName,args);


        LogManager.debug("- Exit");
		LogManager.popRemove();

        return getClaimTypeTbl;
   }

    //Used
	public String getInsuranceDate123(final String quoteNo) throws BaseException
	{
		LogManager.push("getInsuranceDate123 method()");
    	LogManager.debug("- Enter");
    	String temp = "sysdate";
		temp = runner.getSysdate(runner.getuserHomeBranch(quoteNo));		
		
		query = "select "+temp+" from dual";
		final String result = runner.singleSelection(query);

		LogManager.debug("- Exit");
		LogManager.popRemove();

		return result;
	}

	//Used // Previously method name was getRATING_FACTOR_SUB_NAME
	public String[][] getRatingFactorSubName(final String pid) throws BaseException
	{
		LogManager.push("getRatingFactorSubName method()");
    	LogManager.debug("- Enter");

        final String[] args = {pid};
        query = "select RATING_FACTOR_SUB_NAME,cover_id from HOME_COVER_MASTER where product_id=? order by cover_id";
        final String[][] result = runner.multipleSelection(query,args);

        LogManager.debug("- Exit");
		LogManager.popRemove();

		return result;
	}

	//used
	public String getFixedorNot(final String pid,final String coverId) throws BaseException
	{
		LogManager.push("getFixedorNot method()");
    	LogManager.debug("- Enter");

        final String[] args = {pid,coverId};
		query = "select DISPLAY_TYPE from HOME_COVER_MASTER where PRODUCT_ID = ? and COVER_ID = ?";
		final String result = runner.singleSelection(query,args);

	    LogManager.debug("- Exit");
		LogManager.popRemove();

		return result;
	}

	//Used
	public String[][] getSingleDimensionDiscreteGroupInfoDetails(final String groupid,final String table_id ,final String premium_data,final String rating_factor_id,final String gridId,final String dids) throws BaseException
	{
		LogManager.push("getSingleDimensionDiscreteGroupInfoDetails method()");
    	LogManager.debug("- Enter");

    	String[][] claimResult=null;

    	if("1".equalsIgnoreCase(gridId)){
    		final String[] args = {rating_factor_id,dids,groupid,rating_factor_id,table_id};
            query="select 	data_name,data_from,data_to,data_value,new_table_id,rating_factor_id,dimension_grid_id,group_id from data_group_details where  lower(trim(data_from))=(select data_name from single_dimension_discrete where  rating_factor_id = ? and status='Y' and DISCRETE_ID = ?) and group_id = ? and status='Y' and rating_factor_id = ? and new_table_id  = ?";
            claimResult= runner.multipleSelection(query,args);
    	}
    	else if("2".equalsIgnoreCase(gridId)){
           if(validObj.isNumberValue(premium_data)){
        	   final String[] args = {groupid,rating_factor_id,table_id};
               query="select 	data_name,data_from,data_to,data_value,new_table_id,rating_factor_id,dimension_grid_id,group_id from data_group_details where  group_id = ?   and  "+Long.parseLong(premium_data)+"  between data_from and data_to and status='Y' and rating_factor_id = ? and new_table_id = ?";
               claimResult= runner.multipleSelection(query,args);
           }
           else{
               final String[] args = {groupid,rating_factor_id,table_id, premium_data.toLowerCase(Locale.ENGLISH),premium_data.toLowerCase(Locale.ENGLISH)};
               query="select 	data_name,data_from,data_to,data_value,new_table_id,rating_factor_id,dimension_grid_id,group_id from data_group_details where  group_id = ? and status='Y' and rating_factor_id = ? and new_table_id = ? and lower(data_from) = ? or lower(data_to) = ?";
               claimResult= runner.multipleSelection(query,args);
           }
    	}
    	return claimResult;
		}
}
