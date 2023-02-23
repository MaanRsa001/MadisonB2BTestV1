package com.maan.Office.DAO;

import com.maan.services.util.runner;

public class CoverageBean {
	
	private String productID = "";

	private String schemeID = "";

	private String contentTypeID = "";

	private String coverageType = "";

	private String coverageNameID = "";

	private String uploadOption = "";

	private String displayOrder = "";

	private String controlType = "";

	private String coverageLimit = "";

	private String sumControlType = "";

	private String sumCoverageLimit = "";
	
	private String minSumLimit = "";

	private String baseRate = "";

	private String helpContents = "";

	private String status = "";

	private String excess = "";

	private String calculationStatus = "";

	private String calculationType = "";

	private String error = "";

	private String effectiveDate = "";

	private String rsaCode = "";

	public String getBaseRate() {
		return baseRate;
	}

	public void setBaseRate(String baseRate) {
		this.baseRate = baseRate;
	}

	public String getCalculationStatus() {
		return calculationStatus;
	}

	public void setCalculationStatus(String calculationStatus) {
		this.calculationStatus = calculationStatus;
	}

	public String getCalculationType() {
		return calculationType;
	}

	public void setCalculationType(String calculationType) {
		this.calculationType = calculationType;
	}

	public String getContentTypeID() {
		return contentTypeID;
	}

	public void setContentTypeID(String contentTypeID) {
		this.contentTypeID = contentTypeID;
	}

	public String getControlType() {
		return controlType;
	}

	public void setControlType(String controlType) {
		this.controlType = controlType;
	}

	public String getCoverageLimit() {
		return coverageLimit;
	}

	public void setCoverageLimit(String coverageLimit) {
		this.coverageLimit = coverageLimit;
	}

	public String getCoverageNameID() {
		return coverageNameID;
	}

	public void setCoverageNameID(String coverageNameID) {
		this.coverageNameID = coverageNameID;
	}

	public String getCoverageType() {
		return coverageType;
	}

	public void setCoverageType(String coverageType) {
		this.coverageType = coverageType;
	}

	public String getDisplayOrder() {
		return displayOrder;
	}

	public void setDisplayOrder(String displayOrder) {
		this.displayOrder = displayOrder;
	}

	public String getExcess() {
		return excess;
	}

	public void setExcess(String excess) {
		this.excess = excess;
	}

	public String getHelpContents() {
		return helpContents;
	}

	public void setHelpContents(String helpContents) {
		this.helpContents = helpContents;
	}

	public String getProductID() {
		return productID;
	}

	public void setProductID(String productID) {
		this.productID = productID;
	}

	public String getSchemeID() {
		return schemeID;
	}

	public void setSchemeID(String schemeID) {
		this.schemeID = schemeID;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUploadOption() {
		return uploadOption;
	}

	public void setUploadOption(String uploadOption) {
		this.uploadOption = uploadOption;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(String effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public String getRsaCode() {
		return rsaCode;
	}

	public void setRsaCode(String rsaCode) {
		this.rsaCode = rsaCode;
	}

	public String getSumControlType() {
		return sumControlType;
	}

	public void setSumControlType(String sumControlType) {
		this.sumControlType = sumControlType;
	}

	public String getSumCoverageLimit() {
		return sumCoverageLimit;
	}

	public void setSumCoverageLimit(String sumCoverageLimit) {
		this.sumCoverageLimit = sumCoverageLimit;
	}

	public void validation() {
		if (schemeID.length() == 0) {
			error += "* Scheme Not valid<br>";
		}
		if (contentTypeID.length() == 0) {
			error += "* Content Type not valid<br>";
		}
		if (coverageNameID.length() == 0) {
			error += "* Coverage Name not valid<br>";
		}
		if (coverageType.length() == 0) {
			error += "* Select Coverage Type<br>";
		}
		if (uploadOption.length() == 0) {
			error += "* Select Upload Option<br>";
		}
		if (displayOrder.length() == 0) {
			error += "* Enter Display Order<br>";
		}
		if (controlType.length() == 0) {
			error += "* Select Control Type<br>";
		}
		if (coverageLimit.length() == 0) {
		//	error += "* Select Coverage Limit<br>";
		}
		if (sumCoverageLimit.length() == 0) {
			//error += "* insert Sum Insure Coverage Limit<br>";
		} else {
			try {
				Integer.parseInt(sumCoverageLimit);
			} catch (Exception e) {
				error += "* insert Valid Sum Insure Coverage Limit<br>";
			}

		}if (minSumLimit.length() == 0) {
			//error += "* insert Sum Insure Coverage Limit<br>";
		} else {
			try {
				Integer.parseInt(minSumLimit);
			} catch (Exception e) {
				error += "* insert Valid Minimum Sum Insure <br>";
			}

		}
		/*if (excess.length() == 0) {
			error += "* Enter Excess Value<br>";
		}*/
		
		//Calculation condition added to base rate validation for grid concept by chinna
		if(calculationType.length()>0 && !"G".equalsIgnoreCase(calculationType))
		{
			if (baseRate.length() == 0) {
				error += "* Enter Bass Rate<br>";
			} else {
				try {
					Double.parseDouble(baseRate);
				} catch (Exception e) {
					error += "* Enter proper Bass Rate<br>";
				}
			}
		}
		//End
		
		if (calculationStatus.length() == 0) {
			error += "* Select Calculation Status<br>";
		}
		if (calculationType.length() == 0) {
			error += "* Select Calculation Type<br>";
		}
		if (status.length() == 0) {
			error += "* Select Status<br>";
		}
		if (effectiveDate.length() == 0) {
			error += "* Select Effective Date Type<br>";
		} else {
			if (!isGreaterEffectiveDate()) {
				error += "* Select Greater Effective Date<br>";
			}
		}
		if (rsaCode.length() == 0) {
			error += "* Enter Core Application Code<br>";
		}
		if (helpContents.length() == 0) {
			error += "* Enter Help Content<br>";
		}
	}

	public String[][] selectValue(String pProductID, String pCoverageNameID,
			String pSchemeID, String pContentTypeID) {
		String[][] checkValue = new String[0][0];
		String[] values=new String[5];
		String amendID = new String();
		amendID = maxAmendID(pProductID, pCoverageNameID, pSchemeID,
				pContentTypeID);
		values[0]=pProductID;
		values[1]=pCoverageNameID;
		values[2]=pSchemeID;
		values[3]=pContentTypeID;
		values[4]=amendID;

		String checkQuery = "select COVERAGES_TYPE, UPLOAD_OPTION, DISPLAY_ORDER, CONTROL_TYPE,COVERAGES_LIMIT, BASE_RATE, HELP_CONTENTS_ID,STATUS,EXCESS,CALC_STATUS,CALC_TYPE,EFFECTIVE_DATE,RSACODE,SUM_INSURED_LIMIT,SUM_INSURED_CONTROL_TYPE,MIN_SUM_INSURED from OFS_COVERAGES_MASTER where PRODUCT_ID = ? and COVERAGES_ID = ? and SCHEME_ID = ? and CONTENT_TYPE_ID = ? and AMEND_ID = ?";
		checkValue = runner.multipleSelection(checkQuery,values);
		return checkValue;
	}

	public String maxAmendID(String pProductID, String pCoverageNameID,
			String pSchemeID, String pContentTypeID) {
		String checkValue = "";
		String[] values=new String[4];
		values[0]=pProductID;
		values[1]=pCoverageNameID;
		values[2]=pSchemeID;
		values[3]=pContentTypeID;
		String checkQuery = "select nvl(max(amend_id), -1) from OFS_COVERAGES_MASTER where PRODUCT_ID = ? and COVERAGES_ID =? and SCHEME_ID = ? and CONTENT_TYPE_ID = ?";
		checkValue = runner.singleSelection(checkQuery,values);
		return checkValue;
	}

	public void manipulationData() {

		String temp = "sysdate";
		temp = runner.getSysdate("01");		
		String amend = new String();
		amend = maxAmendID(productID, coverageNameID, schemeID, contentTypeID);

		String[][] help = new String[0][0];
		help = selectValue(productID, coverageNameID, schemeID, contentTypeID);

		String helpID = "";
		if (help.length > 0) {
			helpID = help[0][6];
			String updateQuery = "update OFS_HELP_MASTER set HELP_DESCRIPTION ='"
					+ helpContents + "' where HELP_CONTENTS_ID =" + helpID;
			runner.updation(updateQuery);
		} else {
			helpID = maxOfHelpID();
			String helpQuery = "insert into OFS_HELP_MASTER(HELP_CONTENTS_ID,HELP_DESCRIPTION,STATUS) values"
					+ "(" + helpID + ",'" + helpContents + "','Y' )";
			runner.inserion(helpQuery);
		}
		int amendID;

		try {
			amendID = Integer.parseInt(amend);
		} catch (Exception e) {
			amendID = -1;
		}

		if (amendID >= 0) {
			if (isGreaterEntryDate(amendID)) {
				String updateQuery = "update OFS_COVERAGES_MASTER set EXPIRY_DATE = "+temp+" where AMEND_ID='"
						+ amendID
						+ "' and PRODUCT_ID = "
						+ productID
						+ " and COVERAGES_ID = "
						+ coverageNameID
						+ " and SCHEME_ID = "
						+ schemeID
						+ " and CONTENT_TYPE_ID = " + contentTypeID;
				System.out.println("Update Query : " + updateQuery);
				runner.updation(updateQuery);
			} else {
				String updateQuery = "update OFS_COVERAGES_MASTER set EXPIRY_DATE =(select to_date('"
						+ effectiveDate
						+ "', 'dd-MM-YYYY')-1 from dual) where AMEND_ID='"
						+ amendID
						+ "' and PRODUCT_ID = "
						+ productID
						+ " and COVERAGES_ID = "
						+ coverageNameID
						+ " and SCHEME_ID = "
						+ schemeID
						+ " and CONTENT_TYPE_ID = " + contentTypeID;
				System.out.println("Update Query : " + updateQuery);
				runner.updation(updateQuery);
			}
		}
		amendID += 1;

			System.out.println("sumCoverageLimit contoller--------------------------"+sumCoverageLimit);
			System.out.println("coverageLimit contoller --------------------------"+coverageLimit);

		String result = new String("0");
		int countSts=0;
		result = runner.singleSelection("select count(*) from OFS_COVERAGES_SUB_MASTER WHERE PRODUCT_ID = "
						+ productID
						+ " and COVERAGES_ID = "
						+ coverageNameID
						+ " and SCHEME_ID = "
						+ schemeID
						+ " and CONTENT_TYPE_ID = " + contentTypeID );
		try{
		 if(result!=null)
		       countSts=Integer.parseInt(result);
			}catch(Exception e){ System.out.println("Status"+e);}

		String query = "insert into OFS_COVERAGES_MASTER(PRODUCT_ID, COVERAGES_ID, "
				+ "SCHEME_ID,CONTENT_TYPE_ID,COVERAGES_TYPE, UPLOAD_OPTION, DISPLAY_ORDER, "
				+ "CONTROL_TYPE,COVERAGES_LIMIT, BASE_RATE, HELP_CONTENTS_ID,"
				+ "STATUS,EXCESS,CALC_STATUS,CALC_TYPE,AMEND_ID,EFFECTIVE_DATE,ENTRY_DATE,RSACODE,"
				+ "SUB_COVERAGES,SUM_INSURED_LIMIT,SUM_INSURED_CONTROL_TYPE,MIN_SUM_INSURED,MULTIPLE_ROWS,EXPIRY_DATE) values("
				+ productID
				+ ","
				+ coverageNameID
				+ ","
				+ schemeID
				+ ","
				+ contentTypeID
				+ ",'"
				+ coverageType
				+ "','"
				+ uploadOption
				+ "',"
				+ displayOrder
				+ ",'"
				+ controlType
				+ "','"
				+ coverageLimit
				+ "',"
				+ baseRate
				+ ","
				+ helpID
				+ ",'"
				+ status
				+ "','"
				+ excess
				+ "','"
				+ calculationStatus
				+ "','"
				+ calculationType
				+ "',"
				+ amendID
				+ " ,to_date('"
				+ effectiveDate
				+ "','dd-MM-YYYY')"
				+ ", (select "+temp+" FROM dual),'"
				+ rsaCode
				+ (countSts>0?"','Y',":"','N',")
				+ (sumCoverageLimit.length()<=0?"0":sumCoverageLimit)
				+ ",'"
				+ sumControlType
				+ "','"
				+(minSumLimit.length()<=0?"0":minSumLimit) 
				+ "','"
				+ "M"
				+ "',to_date('"
				+ effectiveDate + "','dd-MM-YYYY')+1095 )";
		runner.inserion(query);
		
	}

	public String[][] getScheme(final String pProductID, final String branch) {
		String values[] = new String[1];
		values[0] = pProductID;
		String query = "select SCHEME_ID,SCHEME_NAME from OFS_SCHEME_MASTER where PRODUCT_ID=? and STATUS='Y' and branch_code='"
				+ branch + "'";
		String[][] result = new String[0][0];
		result = runner.multipleSelection(query, values);
		return result;
	}

	public String[][] getContentType(String pProductID, String pSchemeID) {
		String values[]=new String[2];
		values[0]=pProductID;
		values[1]=pSchemeID;
		String query = "select CONTENT_TYPE_ID,CONTENT_DESCRIPTION from OFS_CONTENT_MASTER where PRODUCT_ID=? and SCHEME_ID = ? and STATUS='Y'";
		String[][] result = new String[0][0];
		result = runner.multipleSelection(query,values);
		return result;
	}

	public String[][] getAllCoverage() {
		String query = "select COVERAGES_ID,COVERAGES_NAME from OFS_MASTER where STATUS='Y'";
		String[][] result = new String[0][0];
		result = runner.multipleSelection(query,new String[0]);
		return result;
	}

	public String maxOfHelpID() {
		String query = "select nvl(max(HELP_CONTENTS_ID)+1, -1) from OFS_HELP_MASTER";
		String result = new String();
		result = runner.singleSelection(query,new String[0]);
		return result;
	}

	public String getIDRelatedValue(String pFieldName, String pTableName,
			String pFieldID, String pIDValud) {
		String query = "select " + pFieldName + " from " + pTableName
				+ " where " + pFieldID + "=" + pIDValud;
		String result = "";
		result = runner.singleSelection(query,new String[0]);
		return result;
	}

	public String getCoverageList(String pProductID, String pContentTypeID,
			String pSchemeID) {
		String values[]=new String[3];
		values[0]=pProductID;
		values[1]=pContentTypeID;
		values[2]=pSchemeID;

		String[][] checkValue = new String[0][0];
		String selectQuery = "select distinct COVERAGES_ID from OFS_COVERAGES_MASTER where PRODUCT_ID = ? and CONTENT_TYPE_ID = ? and SCHEME_ID = ?" ;
		checkValue = runner.multipleSelection(selectQuery,values);
		String list = ",";
		for (int i = 0; i < checkValue.length; i++) {
			list += checkValue[i][0] + ",";
		}
		return list;
	}

	public String getCoverageDisplayOrderList(String pProductID,
			String pContentTypeID, String pSchemeID) {
		String[][] checkValue = new String[0][0];
		String values[]=new String[6];
		values[0]=pProductID;
		values[1]=pContentTypeID;
		values[2]=pSchemeID;
		values[3]=pProductID;
		values[4]=pContentTypeID;
		values[5]=pSchemeID;
		String selectQuery = "select display_order from OFS_COVERAGES_MASTER where amend_id||coverages_id in(select max(amend_id)||coverages_id from OFS_COVERAGES_MASTER where "
				+ "PRODUCT_ID = ? and CONTENT_TYPE_ID = ? and SCHEME_ID = ? group by coverages_id) "
				+ "and PRODUCT_ID = ? and CONTENT_TYPE_ID = ? and SCHEME_ID = ?";
		checkValue = runner.multipleSelection(selectQuery,values);
		String list = "";
		for (int i = 0; i < checkValue.length; i++) {
			list += checkValue[i][0] + ", ";
		}
		return list;
	}

	public boolean isGreaterEffectiveDate() {
		String amend = new String();
		
		
		amend = maxAmendID(productID, coverageNameID, schemeID, contentTypeID);
		int amendID;
		try {
			amendID = Integer.parseInt(amend);
		} catch (Exception e) {
			amendID = -1;
		}
		if (amendID < 0) {
			return true;
		} else {
			String values[]=new String[6];
			values[0]=effectiveDate;
			values[1]=""+amendID;
			values[2]=productID;
			values[3]=coverageNameID;
			values[4]=schemeID;
			values[5]=contentTypeID;

			String checkValue = "";
			String selectQuery = "select round(to_date(?,'dd-MM-YYYY') -(select EFFECTIVE_DATE from OFS_COVERAGES_MASTER where AMEND_ID=? and PRODUCT_ID = ? and COVERAGES_ID = ? and SCHEME_ID =? and CONTENT_TYPE_ID = ?)) from dual";
			//System.out.println(selectQuery);
			checkValue = runner.singleSelection(selectQuery,values);
			try {
				int day = Integer.parseInt(checkValue);
				if (day >= 0) {
					return true;
				} else {
					return false;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return false;
		}
	}

	public boolean isGreaterEntryDate(int amendID) {
		String checkValue = "";
		String values[]=new String[6];
			values[0]=effectiveDate;
			values[1]=""+amendID;
			values[2]=productID;
			values[3]=coverageNameID;
			values[4]=schemeID;
			values[5]=contentTypeID;
		String selectQuery = "select  to_date(?,'dd-MM-YYYY')-to_date(ENTRY_DATE) from OFS_COVERAGES_MASTER where AMEND_ID=? and PRODUCT_ID = ? and COVERAGES_ID = ? and SCHEME_ID = ? and CONTENT_TYPE_ID = ?" ;
		//System.out.println(selectQuery);
		checkValue = runner.singleSelection(selectQuery,values);
		try {
			int day = Integer.parseInt(checkValue);
			if (day == 0) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
//	Added by chinna
	
	public void insertGridDetails(String proId, String schemeId, String contId, String coverid, String subcoverid, String values)
	{
		System.out.println("insertCoverageDetails - Enter");
		String args[]=new String[6];
		String q="INSERT INTO OFS_GRID_MASTER VALUES(?,?, ?, ?, ? "+values+")";
		args=new String[5];
		args[0]=proId;
		args[1]=schemeId;
		args[2]=contId;
		args[3]=coverid;
		args[4]=subcoverid;
		String result=runner.multipleInsertion(q, args);
		System.out.println("Query: "+q);
		System.out.println(result);
		System.out.println("insertCoverageDetails - Exit");
	}
	
	public String[][] getGridDetails(String proId, String schemeId, String contentTypeID, String coverid, String subcoverid)
	{
		String q="select MINIMUM_PREMIUM, START_SUMINSURED, END_SUMINSURED, BASE_RATE,  CALC_TYPE, STATUS, to_char(ENTRY_DATE,'dd-MM-yyyy') from OFS_GRID_MASTER where product_id=? and scheme_id=? and content_type_id=? and coverages_id=? and coverages_sub_id=?" +
				"and coverages_id || coverages_sub_id || amend_id=(select coverages_id || coverages_sub_id || max(amend_id) from OFS_GRID_MASTER where product_id=? and scheme_id=? and content_type_id=? and coverages_id=? and coverages_sub_id=? group by coverages_id,coverages_sub_id)";
		String args[]=new String[10];
		args[0]=proId;
		args[1]=schemeId;
		args[2]=contentTypeID;
		args[3]=coverid;
		args[4]=subcoverid;
		args[5]=proId;
		args[6]=schemeId;
		args[7]=contentTypeID;
		args[8]=coverid;
		args[9]=subcoverid;
		String[][] gridDetails=runner.multipleSelection(q, args);
		return gridDetails;
	}
	public String getMaxAmendId(String proId, String schemeId, String contentTypeID, String coverid, String subcoverid)
	{
		String q="select (case when count(*)=0 then 0 else max(amend_id)+1 end) from OFS_GRID_MASTER where product_id=? and scheme_id=? and content_type_id=? and coverages_id=? and coverages_sub_id=?";
		String args[]=new String[5];
		args[0]=proId;
		args[1]=schemeId;
		args[2]=contentTypeID;
		args[3]=coverid;
		args[4]=subcoverid;
		String amendId=runner.singleSelection(q, args);
		return amendId;
	}
	//Methods for config Master
	public void insertConfigDetails(String proId, String schemeId, String contId, String coverid, String subcoverid, String values)
	{
		System.out.println("insertConfigDetails - Enter");
		String args[]=new String[5];
		String q="INSERT INTO OFS_CONFIG_MASTER(OFS_CONFIG_ID,PRODUCT_ID,SCHEME_ID,CONTENT_TYPE_ID,COVERAGES_ID,COVERAGES_SUB_ID,DISPLAY_NAME,DISPLAY_TYPE,DISPLAY_ORDER,DROPDOWN_KEY,DROPDOWN_VALUE,DROPDOWN_TABLE,VALIDATION_TYPE,DEST_TABLE,DEST_COLUMN,AMEND_ID,STATUS,MANDATORY,TOTAL_SUMINSURED_YN) VALUES((SELECT NVL(MAX(OFS_CONFIG_ID),0) FROM OFS_CONFIG_MASTER)+1,?,?, ?, ?, ? "+values+")";
		//args=new String[5];
		args[0]=proId;
		args[1]=schemeId;
		args[2]=contId;
		args[3]=coverid;
		args[4]=subcoverid;
		String result=runner.multipleInsertion(q, args);
		System.out.println("Query: "+q);
		System.out.println(result);
		System.out.println("insertConfigDetails - Exit");
	}
	
	public String[][] getConfigDetails(String proId, String schemeId, String contentTypeID, String coverid, String subcoverid)
	{
		String q="select DISPLAY_NAME, DISPLAY_TYPE, DISPLAY_ORDER, DROPDOWN_KEY, DROPDOWN_VALUE, DROPDOWN_TABLE, VALIDATION_TYPE, DEST_TABLE, DEST_COLUMN, STATUS, Mandatory,TOTAL_SUMINSURED_YN  from OFS_CONFIG_MASTER where product_id=? and scheme_id=? and content_type_id=? and coverages_id=? and coverages_sub_id=?" +
				"and coverages_id || coverages_sub_id || amend_id=(select coverages_id || coverages_sub_id || max(amend_id) from OFS_CONFIG_MASTER where product_id=? and scheme_id=? and content_type_id=? and coverages_id=? and coverages_sub_id=? group by coverages_id,coverages_sub_id)";
		String args[]=new String[10];
		args[0]=proId;
		args[1]=schemeId;
		args[2]=contentTypeID;
		args[3]=coverid;
		args[4]=subcoverid;
		args[5]=proId;
		args[6]=schemeId;
		args[7]=contentTypeID;
		args[8]=coverid;
		args[9]=subcoverid;
		String[][] configDetails=runner.multipleSelection(q, args);
		return configDetails;
	}
	public String getMaxAmendId1(String proId, String schemeId, String contentTypeID, String coverid, String subcoverid)
	{
		String q="select (case when count(*)=0 then 0 else max(amend_id)+1 end) from OFS_CONFIG_MASTER where product_id=? and scheme_id=? and content_type_id=? and coverages_id=? and coverages_sub_id=?";
		String args[]=new String[5];
		args[0]=proId;
		args[1]=schemeId;
		args[2]=contentTypeID;
		args[3]=coverid;
		args[4]=subcoverid;
		String amendId=runner.singleSelection(q, args);
		return amendId;
	}
	public String[][] getColumnNames(String tableName)
	{
		String q="SELECT DISTINCT COLUMN_NAME FROM ALL_TAB_COLS WHERE TABLE_NAME = '"+tableName+"' AND COLUMN_NAME LIKE 'FIELD%' ORDER BY COLUMN_NAME ASC";
		String columnNames[][]=runner.multipleSelection(q);
		return columnNames;
	}
	public String[][] getDropdownColumnNames(String tableName)
	{
		String q="SELECT column_name FROM all_tab_cols where table_name = '"+tableName+"'";
		String columnNames[][]=runner.multipleSelection(q);
		return columnNames;
	}
	public String[][] getTableNames()
	{
		String q="SELECT TNAME FROM TAB WHERE TABTYPE='TABLE'";
		String tableNames[][]=runner.multipleSelection(q);
		return tableNames;
	}
	
	//Methods for formula master
	public String[][] getFormulaDetails(String proId, String schemeId, String coverid, String branchCode)
	{
		String q="SELECT FORMULA, REMARKS, STATUS FROM FORMULA_MASTER  WHERE PRODUCT_ID=? AND SCHEME_ID=? AND COVERAGE_ID=? AND BRANCH_CODE=?";
		String args[] = new String[4];
		args[0]=proId;
		args[1]=schemeId;
		args[2]=coverid;
		args[3]=branchCode;
		String result[][]=runner.multipleSelection(q,args);
		return result;
	}
	public String insertFormulaDetails(String proId, String schemeId, String coverid, String branchCode, String formula, String remarks, String status)
	{
		String result="";
		try{
			String args[] = new String[4];
			args[0]=proId;
			args[1]=schemeId;
			args[2]=coverid;
			args[3]=branchCode;
			String q="SELECT COUNT(*) FROM FORMULA_MASTER  WHERE PRODUCT_ID=? AND SCHEME_ID=? AND COVERAGE_ID=? AND BRANCH_CODE=?";
			String count=runner.singleSelection(q, args);
			
			if(count!=null && count.equalsIgnoreCase("0"))
			{
				args = new String[7];
				args[0]=proId;
				args[1]=schemeId;
				args[2]=coverid;
				args[3]=formula;
				args[4]=branchCode;
				args[5]=remarks;
				args[6]=status;
				q="INSERT INTO FORMULA_MASTER VALUES(?,?,?,?,?,?,?)";
			}else
			{
				args = new String[7];
				args[0]=formula;
				args[1]=remarks;
				args[2]=status;
				args[3]=proId;
				args[4]=schemeId;
				args[5]=coverid;
				args[6]=branchCode;
				q="UPDATE FORMULA_MASTER SET FORMULA=?, REMARKS=?, STATUS=? WHERE PRODUCT_ID=? AND SCHEME_ID=? AND COVERAGE_ID=? AND BRANCH_CODE=?";
			}
			runner.multipleInsertion(q,args);
			result="Saved Successfully!!!";
		}catch(Exception e)
		{
			result="Problem in Inserting Data";
			e.printStackTrace();
		}
		return result;
	}

	public void setMinSumLimit(String minSumLimit) {
		this.minSumLimit = minSumLimit;
	}

	public String getMinSumLimit() {
		return minSumLimit;
	}
	
}

