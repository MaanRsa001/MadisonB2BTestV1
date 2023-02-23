package com.maan.Office.DAO;

import com.maan.services.util.runner;

public class SubCoverageBean {
	private String productID = "";

	private String schemeID = "";

	private String contentTypeID = "";

	private String coverageID = "";

	private String subCoverageID = "";

	private String displayOrder = "";

	private String controlType = "";

	private String coverageLimit = "";

	private String subCoverageLimit = "";

	private String subRate = "";

	private String status = "";

	private String calculationType = "";

	private String error = "";

	private String effectiveDate = "";

	private String rsaCode = "";

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

	public String getCoverageID() {
		return coverageID;
	}

	public void setCoverageID(String coverageID) {
		this.coverageID = coverageID;
	}

	public String getCoverageLimit() {
		return coverageLimit;
	}

	public void setCoverageLimit(String coverageLimit) {
		this.coverageLimit = coverageLimit;
	}

	public String getDisplayOrder() {
		return displayOrder;
	}

	public void setDisplayOrder(String displayOrder) {
		this.displayOrder = displayOrder;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
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

	public String getSubCoverageID() {
		return subCoverageID;
	}

	public void setSubCoverageID(String subCoverageID) {
		this.subCoverageID = subCoverageID;
	}

	public String getSubRate() {
		return subRate;
	}

	public void setSubRate(String subRate) {
		this.subRate = subRate;
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

	public String getSubCoverageLimit() {
		return subCoverageLimit;
	}


	public void setSubCoverageLimit(String subCoverageLimit) {
		this.subCoverageLimit = subCoverageLimit;
	}

	public void validation() {
		if (productID.length() == 0) {
			error += "* product ID Not valid<br>";
		}
		if (schemeID.length() == 0) {
			error += "* Scheme ID Not valid<br>";
		}
		if (contentTypeID.length() == 0) {
			error += "* Content Type not valid<br>";
		}
		if (coverageID.length() == 0) {
			error += "* Coverage ID is Not Valid<br>";
		}
		if (subCoverageID.length() == 0) {
			error += "* Sub Coverage ID is Not Valid<br>";
		}
		if (displayOrder.length() == 0) {
			error += "* Enter Display Order<br>";
		}
		if (controlType.length() == 0) {
			error += "* Select Control Type<br>";
		}
		if (coverageLimit.length() == 0) {
			//error += "* Select Coverage Limit<br>";
		}
		if (subRate.length() == 0) {
			error += "* Insert Sub Rate<br>";
		} else {
			try {
				Double.parseDouble(subRate);
			} catch (Exception e) {
				error += "* Enter proper Sub Rate<br>";
			}
		}
		if (calculationType.length() == 0) {
			error += "* Select Calculation Type<br>";
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
		if (status.length() == 0) {
			error += "* Select Status<br>";
		}
	}

	public String[][] selectValue(String pProductID, String pCoverageNameID,
			String pSchemeID, String pContentTypeID, String pSubCoverageNameID) {
		String[][] checkValue = new String[0][0];
		String amendID = new String();
		String[] values=new String[6];
		amendID = maxAmendID(pProductID, pCoverageNameID, pSchemeID,
				pContentTypeID, pSubCoverageNameID);
		values[0]=pProductID;
		values[1]=pCoverageNameID;
		values[2]=pSchemeID;
		values[3]=pContentTypeID;
		values[4]=amendID;
		values[5]=pSubCoverageNameID;
		String checkQuery = "select SUB_COVERAGES_LIMIT,SUB_RATE,SUB_CONTROL_TYPE,"
				+ "SUB_DISPLAY_ORDER,STATUS,CALC_TYPE,EFFECTIVE_DATE,RSACODE,SUM_INSURED_LIMIT from OFS_COVERAGES_SUB_MASTER where PRODUCT_ID = ? and COVERAGES_ID = ? and SCHEME_ID = ? and CONTENT_TYPE_ID =? and AMEND_ID = ? and COVERAGES_SUB_ID = ?";
		checkValue = runner.multipleSelection(checkQuery,values);
		//System.out.println(checkQuery);
		return checkValue;
	}

	public String maxAmendID(String pProductID, String pCoverageNameID,
			String pSchemeID, String pContentTypeID, String pSubCoverageNameID) {
		String checkValue = "";
		String[] values=new String[5];
		values[0]=pProductID;
		values[1]=pCoverageNameID;
		values[2]=pSchemeID;
		values[3]=pContentTypeID;
		values[4]=pSubCoverageNameID;
		String checkQuery = "select nvl(max(amend_id), -1) from OFS_COVERAGES_SUB_MASTER where PRODUCT_ID = ? and COVERAGES_ID = ? and SCHEME_ID = ? and CONTENT_TYPE_ID = ? and COVERAGES_SUB_ID =? ";
		checkValue = runner.singleSelection(checkQuery,values);
		return checkValue;
	}

	public void manipulationData() {

		String temp = "sysdate";
		temp = runner.getSysdate("01");		
		String amend = new String();
		amend = maxAmendID(productID, coverageID, schemeID, contentTypeID,
				subCoverageID);
		int amendID;

		try {
			amendID = Integer.parseInt(amend);
		} catch (Exception e) {
			amendID = 0;
		}

		if (amendID >= 0) {
			if (isGreaterEntryDate(amendID)) {
				String updateQuery = "update OFS_COVERAGES_SUB_MASTER set EXPIRY_DATE = "+temp+" where AMEND_ID='"
						+ amendID
						+ "' and PRODUCT_ID = "
						+ productID
						+ " and COVERAGES_ID = "
						+ coverageID
						+ " and SCHEME_ID = "
						+ schemeID
						+ " and CONTENT_TYPE_ID = "
						+ contentTypeID
						+ " and COVERAGES_SUB_ID = " + subCoverageID;
				runner.updation(updateQuery);
			} else {
				String updateQuery = "update OFS_COVERAGES_SUB_MASTER set EXPIRY_DATE =(select to_date('"
						+ effectiveDate
						+ "', 'dd-MM-YYYY')-1 from dual) where AMEND_ID='"
						+ amendID
						+ "' and PRODUCT_ID = "
						+ productID
						+ " and COVERAGES_ID = "
						+ coverageID
						+ " and SCHEME_ID = "
						+ schemeID
						+ " and CONTENT_TYPE_ID = "
						+ contentTypeID
						+ " and COVERAGES_SUB_ID = " + subCoverageID;
				runner.updation(updateQuery);
			}

		}

		amendID += 1;

		String query = "insert into OFS_COVERAGES_SUB_MASTER(COVERAGES_SUB_ID, COVERAGES_ID,"
				+ "PRODUCT_ID, SCHEME_ID, CONTENT_TYPE_ID, SUB_COVERAGES_LIMIT,SUB_RATE,"
				+ "SUB_CONTROL_TYPE,SUB_DISPLAY_ORDER,STATUS,CALC_TYPE, AMEND_ID, EFFECTIVE_DATE, ENTRY_DATE,RSACODE,EXPIRY_DATE) values("
				+ subCoverageID
				+ ","
				+ coverageID
				+ ","
				+ productID
				+ ","
				+ schemeID
				+ ","
				+ contentTypeID
				+ ",'"
				+ coverageLimit
				+ "',"
				+ subRate
				+ ",'"
				+ controlType
				+ "',"
				+ displayOrder
				+ ",'"
				+ status
				+ "','"
				+ calculationType
				+ "',"
				+ amendID
				+ " ,to_date('"
				+ effectiveDate
				+ "','dd-MM-YYYY')"
				+ ", (select "+temp+" FROM dual),'" + rsaCode + "',to_date('"
				+ effectiveDate
				+ "','dd-MM-YYYY')+1095 )";
		runner.inserion(query);

		String updateQuery = "";
        String values[]=new String[4];
		
		if (status.equalsIgnoreCase("Y")) {
			values[0]=productID;
		    values[1]=coverageID;
		    values[2]=schemeID;
		    values[3]=contentTypeID;
			updateQuery = "update OFS_COVERAGES_MASTER set SUB_COVERAGES = 'Y' where "
					+ " PRODUCT_ID =? and COVERAGES_ID=? and SCHEME_ID =? and CONTENT_TYPE_ID = ?";
			runner.multipleUpdation(updateQuery,values);
		} else {
			values[0]=productID;
		    values[1]=contentTypeID;
		    values[2]=schemeID;
		    values[3]=coverageID;
			String[][] list = new String[0][0];
			String selectQuery = "select COVERAGES_SUB_ID from OFS_COVERAGES_SUB_MASTER where PRODUCT_ID =? and CONTENT_TYPE_ID = ? and SCHEME_ID = ? and COVERAGES_ID = ? and status = 'Y'";
			list = runner.multipleSelection(selectQuery,values);
			if (list.length == 0) {
				values[0]=productID;
		        values[1]=coverageID;
		        values[2]=schemeID;
		        values[3]=contentTypeID;
				updateQuery = "update OFS_COVERAGES_MASTER set SUB_COVERAGES = 'N' where "
						+ " PRODUCT_ID =? and COVERAGES_ID=? and SCHEME_ID=? and CONTENT_TYPE_ID = ?";
				runner.multipleUpdation(updateQuery,values);
			}
		}
	}

	public String getSubCoverageList(String pProductID, String pContentTypeID,
			String pSchemeID, String pCoverageID) {
		String[][] checkValue = new String[0][0];
		String values[]=new String[4];
		values[0]=pProductID;
		values[1]=pContentTypeID;
		values[2]=pSchemeID;
		values[3]=pCoverageID;
		String selectQuery = "select distinct COVERAGES_SUB_ID from OFS_COVERAGES_SUB_MASTER where PRODUCT_ID = ? and CONTENT_TYPE_ID =? and SCHEME_ID =? and COVERAGES_ID = ?";
		checkValue = runner.multipleSelection(selectQuery,values);
		String list = ",";
		for (int i = 0; i < checkValue.length; i++) {
			list += checkValue[i][0] + ",";
		}
		return list;
	}

	public String getSubCoverageDisplayOrderList(String pProductID,
			String pContentTypeID, String pSchemeID, String pCoverageID) {
		String[][] checkValue = new String[0][0];
		String values[]=new String[8];
		values[0]=pProductID;
		values[1]=pContentTypeID;
		values[2]=pSchemeID;
		values[3]=pCoverageID;
		values[4]=pProductID;
		values[5]=pContentTypeID;
		values[6]=pSchemeID;
		values[7]=pCoverageID;
		String selectQuery = "select SUB_DISPLAY_ORDER from OFS_COVERAGES_SUB_MASTER where amend_id||COVERAGES_SUB_ID in(select max(amend_id)||COVERAGES_SUB_ID from OFS_COVERAGES_SUB_MASTER where PRODUCT_ID = ? and CONTENT_TYPE_ID = ? and SCHEME_ID = ? and COVERAGES_ID = ? group by COVERAGES_SUB_ID) and PRODUCT_ID = ? and CONTENT_TYPE_ID = ?  and SCHEME_ID = ? and COVERAGES_ID =?";
		checkValue = runner.multipleSelection(selectQuery,values);
		String list = "";
		for (int i = 0; i < checkValue.length; i++) {
			list += checkValue[i][0] + ", ";
		}
		return list;
	}

	public boolean isGreaterEffectiveDate() {
		String amend = new String();
		amend = maxAmendID(productID, coverageID, schemeID, contentTypeID,
				subCoverageID);
		String values[]=new String[7];
		int amendID;
		try {
			amendID = Integer.parseInt(amend);
		} catch (Exception e) {
			amendID = -1;
		}
		if (amendID < 0) {
			return true;
		} else {
			String checkValue = "";
			values[0]=effectiveDate;
			values[1]=""+amendID;
			values[2]=productID;
			values[3]=coverageID;
			values[4]=schemeID;
			values[5]=contentTypeID;
			values[6]=subCoverageID;
			String selectQuery = "select ROUND(to_date(?,'dd-MM-YYYY') -(select EFFECTIVE_DATE from OFS_COVERAGES_SUB_MASTER where AMEND_ID=? and PRODUCT_ID = ? and COVERAGES_ID = ? and SCHEME_ID = ? and CONTENT_TYPE_ID =? and COVERAGES_SUB_ID = ?)) from dual";
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
		String temp = "sysdate";
		temp = runner.getSysdate("01");		
		String checkValue = "";
		String values[]=new String[6];
		values[0]=""+amendID;
		values[1]=productID;
		values[2]=coverageID;
		values[3]=schemeID;
		values[4]=contentTypeID;
		values[5]=subCoverageID;
		String selectQuery = "select to_date("+temp+")-to_date(ENTRY_DATE) from OFS_COVERAGES_SUB_MASTER where AMEND_ID=? and PRODUCT_ID =? and COVERAGES_ID = ? and SCHEME_ID = ? and CONTENT_TYPE_ID = ? and COVERAGES_SUB_ID = ?";
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

}
