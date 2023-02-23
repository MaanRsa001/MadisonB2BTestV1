package com.maan.Home.Premium;

import java.util.Locale;

import com.maan.common.LogManager;
import com.maan.common.exception.BaseException;
import com.maan.services.util.runner;

public class Premium
{
	public String[][] getClaimsUnderProduct() throws BaseException
	{
		LogManager.push("getClaimsUnderProduct method()");
		LogManager.debug("- Enter");

		String[][] claimsUnderPro = new String[0][0];
		final String sql = "select claim_type_id,CLAIM_TYPE_NAME from claim_type where mode_id='2' and channel_id='2' and product_id='21' and claim_type_id not in ('2') and status='Y'";
		claimsUnderPro = runner.multipleSelection(sql);

		LogManager.debug("- Exit");
		LogManager.popRemove();

		return claimsUnderPro;
	}

	public String[][] getFactorsUnderClaim(final String claim ) throws BaseException{

		LogManager.push("getFactorsUnderClaim method()");
		LogManager.debug("- Enter");

		String[][] claimsUnderPro = new String[0][0];
		final String sql = "select rating_factor_id,RATING_FACTOR_SUB_NAME from rating_factor_sub where claim_type_id in ('"+ claim + "') and status='Y' order by RATING_FACTOR_SUB_NAME";
		claimsUnderPro = runner.multipleSelection(sql);

		LogManager.debug("- Exit");
		LogManager.popRemove();

		return claimsUnderPro;
	}

	public String[][] getGroupedFactorsUnderClaim(final String claim) throws BaseException {

		LogManager.push("getGroupedFactorsUnderClaim method()");
		LogManager.debug("- Enter");

		String[][] claimsUnderPro = new String[0][0];
		final String sql = "select a.group_id,a.RATING_FACTOR_NAME,a.RATING_FACTOR_ID,b.RATING_FACTOR_sub_id from product_field_master_new a,rating_factor_sub b where a.rating_Factor_id in  (select rating_factor_id from rating_factor_sub where claim_type_id='"
				+ claim+ "' and status='Y') and a.rating_factor_id=b.rating_factor_id and b.claim_type_id='"
				+ claim+ "' and  a.group_id='8' and b.status='Y'  order by(a.RATING_FACTOR_NAME) ";
		claimsUnderPro = runner.multipleSelection(sql);

		LogManager.debug("- Exit");
		LogManager.popRemove();

		return claimsUnderPro;
	}

	public String[][] getPropertyFactorsUnderClaim(final String claim) throws BaseException  {

		LogManager.push("getPropertyFactorsUnderClaim method()");
		LogManager.debug("- Enter");

		String[][] claimsUnderPro = new String[0][0];
		final String sql = "select a.group_id,a.RATING_FACTOR_NAME,a.RATING_FACTOR_ID,b.RATING_FACTOR_sub_id from product_field_master_new a,rating_factor_sub b where a.rating_Factor_id in  (select rating_factor_id from rating_factor_sub where claim_type_id='"
				+ claim+ "' and status='Y') and a.rating_factor_id=b.rating_factor_id and b.claim_type_id='"
				+ claim+ "' and  a.group_id='0' and a.status='Y'  order by(a.RATING_FACTOR_NAME)";
		claimsUnderPro = runner.multipleSelection(sql);

		LogManager.debug("- Exit");
		LogManager.popRemove();

		return claimsUnderPro;
	}

	public String[][] getDatasByGroupId(final String groupid)  throws BaseException {

		LogManager.push("getDatasByGroupId method()");
		LogManager.debug("- Enter");

		String[][] claimsUnderPro = new String[0][0];
		final String sql = "select DATA_GROUP_ID,data_name from data_group_details where group_id in('"
				+ groupid+ "') and status='Y' order by cast(data_name as integer)";
		claimsUnderPro = runner.multipleSelection(sql);

		LogManager.debug("- Exit");
		LogManager.popRemove();

		return claimsUnderPro;
	}

	public String[][] getCalculatedValue125(final String RatingFactorSubId) throws BaseException{

		LogManager.push("getCalculatedValue125 method()");
		LogManager.debug("- Enter");

		String[][] claimsUnderPro = new String[0][0];
		final String sql = "select range_id,rating_factor_id,data_from,data_to,data_value, to_char(effective_date,'DD-MM-YYYY') from single_dimension_range where rating_factor_sub_id='"
				+ RatingFactorSubId+ "' and range_id in (select  max(range_id) from single_dimension_range  where status='Y' and rating_factor_sub_id='"
				+ RatingFactorSubId + "'  group by relative_range_id) ";
		claimsUnderPro = runner.multipleSelection(sql);

		LogManager.debug("- Exit");
		LogManager.popRemove();

		return claimsUnderPro;
	}

	public String[][] getCalculatedValue123(final String RatingId,final String RatingSubId ,final String CoverValue) throws BaseException{

		LogManager.push("getCalculatedValue123 method()");
		LogManager.debug("- Enter");

		String[][] claimsUnderPro = new String[0][0];
		final String sql = "select data_value from single_dimension_discrete where rating_factor_sub_id='"
				+ RatingSubId+ "'  and discrete_id in (select  max(discrete_id) from single_dimension_discrete  where status='Y' and rating_factor_sub_id='"
				+ RatingSubId + "' group by relative_discrete_id) ";
		claimsUnderPro = runner.multipleSelection(sql);

		LogManager.debug("- Exit");
		LogManager.popRemove();

		return claimsUnderPro;
	}

	public String[][] getCalculatedValue124(final String RatingId,final String RatingSubId,final String CoverValue) throws BaseException{

		LogManager.push("getCalculatedValue124 method()");
		LogManager.debug("- Enter");

		String[][] claimsUnderPro = new String[0][0];
		final String sql = "select data_value from single_dimension_discrete where rating_factor_sub_id='"
				+ RatingSubId+ "' and rating_factor_id ='"+ RatingId+ "' and data_name='"
				+ CoverValue+ "'  and discrete_id in (select  max(discrete_id) from single_dimension_discrete  where status='Y' and rating_factor_sub_id='"
				+ RatingSubId+ "' and rating_factor_id ='"+ RatingId+ "' and data_name='"+ CoverValue
				+ "'  group by relative_discrete_id) ";
		claimsUnderPro = runner.multipleSelection(sql);

		LogManager.debug("- Exit");
		LogManager.popRemove();

		return claimsUnderPro;
	}

	public String[][] getPropertyFactorsUnderClaimForValidation()  throws BaseException {

		LogManager.push("getPropertyFactorsUnderClaimForValidation method()");
		LogManager.debug("- Enter");

		final String claim = "2";
		String[][] claimsUnderPro = new String[0][0];
		final String sql = "select a.group_id,a.RATING_FACTOR_NAME,a.RATING_FACTOR_ID,b.RATING_FACTOR_sub_id from product_field_master_new a,rating_factor_sub b where a.rating_Factor_id in  (select rating_factor_id from rating_factor_sub where claim_type_id='"
				+ claim+ "' and status='Y') and a.rating_factor_id=b.rating_factor_id and b.claim_type_id='"
				+ claim+ "' and  a.group_id='0' and a.status='Y'  order by(a.RATING_FACTOR_NAME)";
		claimsUnderPro = runner.multipleSelection(sql);

		LogManager.debug("- Exit");
		LogManager.popRemove();

		return claimsUnderPro;
	}

	public String[][] getBrokerExPremium(final String LoginId)   throws BaseException {

		LogManager.push("getBrokerExPremium method()");
		LogManager.debug("- Enter");

		String agencyCode = "";
		final String sql = "select agency_code from login_master where agency_code=(select oa_code from login_master where login_id = '"+ LoginId + "') ";
		agencyCode = runner.singleSelection(sql);

		String[][] commodity = new String[0][0];
		String sql1 = "select  discrete_id,data_name,data_value from single_dimension_discrete where rating_factor_sub_id='16'  and discrete_id in (select  max(discrete_id) from single_dimension_discrete  where status='Y' and rating_factor_sub_id='16' and data_name='"
				+ agencyCode.toUpperCase(Locale.ENGLISH)+ "'  group by relative_discrete_id)";
		commodity = runner.multipleSelection(sql1);

		LogManager.debug("- Exit");
		LogManager.popRemove();

		return commodity;
	}

	public void updateAdditionalCommision(final String commission,final String quoteNo) throws BaseException {
		LogManager.push("UpdateAdditionalCommision method()");
		LogManager.debug("- Enter");

		String qry = "update home_position_master set BROKER_ADDITIONAL_COMMISSION = '"+commission +"'  where QUOTE_NO = '" + quoteNo + "'";
		qry = runner.updation(qry);

		LogManager.debug("- Exit");
		LogManager.popRemove();
	}

	public String[][] selectPremiium(final String loginid)  throws BaseException{

		LogManager.push("selectPremiium method()");
		LogManager.debug("- Enter");

		String[][] claimsUnderPro = new String[0][0];
		String sql = "select claim_type_id,CLAIM_TYPE_NAME from claim_type where mode_id='2' and channel_id='2' and product_id='21' and claim_type_id not in ('2') and status='Y'";
		claimsUnderPro = runner.multipleSelection(sql);

		LogManager.debug("- Exit");
		LogManager.popRemove();

		return claimsUnderPro;
	}

	public String homeCoverageSelect125(final String Quote_No) throws BaseException {

		LogManager.push("homeCoverageSelect125 method()");
		LogManager.debug("- Enter");

		String result[][] = new String[0][0];
		String result1 = "";
		final String sql = "select premium from home_position_master where quote_no='"+ Quote_No + "'";
		result = runner.multipleSelection(sql);
		if(result.length>0){
			result1 = result[0][0];
		}

		LogManager.debug("- Exit");
		LogManager.popRemove();

		return result1;
	}

	public void updateNCD(final String ncd,final String ncdvalue,final String quoteNo) throws BaseException{
		LogManager.push("UpdateNCD method()");
		LogManager.debug("- Enter");

		String qry = "update home_position_master set NO_CLAIM_DISCOUNT = '"+ncd+"',NO_CLAIM_DISCOUNT_VALUE='" + ncdvalue+ "'  where QUOTE_NO='" + quoteNo + "'";
		qry = runner.updation(qry);

		LogManager.debug("- Exit");
		LogManager.popRemove();
	}

	public String[][] gettingRatingDataNameAndValue(final String PId,final String ratingFactorId,final  String ratingFactorSubId) throws BaseException {

		LogManager.push("gettingRatingDataNameAndValue method()");
		LogManager.debug("- Enter");

		String result[][] = new String[0][0];
		String sql = "select data_name,data_value,DISCRETE_ID from single_dimension_discrete where rating_factor_sub_id='"
						 + ratingFactorSubId+ "' and rating_factor_id='"+ ratingFactorId+ "' and status='Y' order by cast(DATA_NAME as INTEGER)";
		result = runner.multipleSelection(sql);

		LogManager.debug("- Exit");
		LogManager.popRemove();
		return result;
	}

	// Added for Nakheel Scheme
	public String[][] gettingAreaB2C(final String ratingFactorId,final String ratingFactorSubId) throws BaseException{

		LogManager.push("gettingAreaB2C method()");
		LogManager.debug("- Enter");

		final String sql = "select data_name,data_value from single_dimension_discrete where rating_factor_sub_id='"
				+ ratingFactorSubId+ "' and rating_factor_id = '"+ ratingFactorId + "' and status='Y' order by data_name";
		String res[][] = new String[0][0];
		res = runner.multipleSelection(sql);

		LogManager.debug("- Exit");
		LogManager.popRemove();

		return res;
	}

	public String[][] gettingRatingDataNameAndValueB2C(final String PId,final String ratingFactorId,final String ratingFactorSubId) throws BaseException {

		LogManager.push("gettingRatingDataNameAndValueB2C method()");
		LogManager.debug("- Enter");

		String result[][] = new String[0][0];
		final String sql = "select data_name,data_value,DISCRETE_ID from single_dimension_discrete where rating_factor_sub_id='"
				+ ratingFactorSubId+ "' and rating_factor_id='"+ ratingFactorId+ "' and status='Y' order by cast(data_value as INTEGER)";
		result = runner.multipleSelection(sql);

		LogManager.debug("- Exit");
		LogManager.popRemove();
		return result;
	}

	public String[][] gettingRatingDataNameAndValueBed(final String PId,final String ratingFactorId, final String ratingFactorSubId) throws BaseException {

		LogManager.push("gettingRatingDataNameAndValueBed method()");
		LogManager.debug("- Enter");

		String result[][] = new String[0][0];
		final String sql = "select data_name,data_value,DISCRETE_ID,rownum from single_dimension_discrete where rating_factor_sub_id='"
				+ ratingFactorSubId+ "' and rating_factor_id='"+ratingFactorId+"' and status='Y' order by cast(DATA_NAME as INTEGER)";
		result = runner.multipleSelection(sql);

		LogManager.debug("- Exit");
		LogManager.popRemove();
		return result;
	}
/*	public String[][] getPropertyFactorsUnderClaimForValidation123(String pid) throws BaseException {

		LogManager.push("getPropertyFactorsUnderClaimForValidation123 method()");
		LogManager.debug("- Enter");
		com.maan.Travel.DAO.PremiumDisplay preCal = new com.maan.Travel.DAO.PremiumDisplay();
		pid = preCal.getRatingProductId(pid);
		String[][] claimsUnderPro = new String[0][0];
		final String sql = "select a.group_id,a.RATING_FACTOR_NAME,a.RATING_FACTOR_ID,b.RATING_FACTOR_sub_id from product_field_master_new a,rating_factor_sub b where a.rating_Factor_id in  (select rating_factor_id from rating_factor_sub where claim_type_id in (select claim_type_id from claim_type where claim_type_name like 'HOME_VALIDATION' and PRODUCT_ID='"
				+ pid+ "') and status='Y') and a.rating_factor_id=b.rating_factor_id and b.claim_type_id in (select claim_type_id from claim_type where claim_type_name like 'HOME_VALIDATION' and PRODUCT_ID='"
				+ pid+ "') and  a.group_id='0' and a.status='Y'  and a.product_id='"+ pid + "' order by(a.RATING_FACTOR_NAME)";
		claimsUnderPro = runner.multipleSelection(sql);

		LogManager.debug("- Exit");
		LogManager.popRemove();

		return claimsUnderPro;
	}*/

	public String[][] getCalculatedValue1234(final String ratingSubId) throws BaseException {
		String[][] claimsUnderPro = new String[0][0];

		LogManager.push("getCalculatedValue1234 method()");
		LogManager.debug("- Enter");

		final String sql = "select data_value from single_dimension_discrete where rating_factor_sub_id='"+ ratingSubId
				+ "' and discrete_id in (select  max(discrete_id) from single_dimension_discrete where status='Y' and rating_factor_sub_id='"
				+ ratingSubId + "' group by relative_discrete_id)";

		claimsUnderPro = runner.multipleSelection(sql);

		LogManager.debug("- Exit");
		LogManager.popRemove();
		return claimsUnderPro;
	}
}
