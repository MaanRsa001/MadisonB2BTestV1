package com.maan.Home.displayBean;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import com.maan.common.LogManager;
import com.maan.common.exception.BaseException;

public class HomeDisplayBean {
	public List getExistingCustomerList(final String[][] value,final String identifyCus)
			throws BaseException {// vinoth exisiting
		// customer string to list
		LogManager.push("getExistingCustomerList method()");
		LogManager.debug("- Enter");

		List list = new ArrayList();
		String custName;
		for (int i = 0; i < value.length; i++) {

			if (value[i][6]==null||value[i][6].equals("") || value[i][6].length() <= 0
					|| value[i][6].equalsIgnoreCase("null")) {
				custName = (value[i][1] == null ? "" : value[i][1]) + " "
						+ (value[i][2] == null ? "" : value[i][2]);
			} else {
				custName = value[i][6];
			}
			final CustomerListBean bean = new CustomerListBean();
			
			/*if(value[i][0].equalsIgnoreCase(identifyCus)){
				bean.setCustomerOpt("checked");
			}*/
			
			bean.setCustomerID(value[i][0] == null ? "" : value[i][0]);
			bean.setCustomerName(custName);
			bean.setEmailID(value[i][3] == null ? "" : value[i][3]);
			bean.setContactNO(value[i][4] == null ? "" : value[i][4]);
			list.add(bean);
		}

		LogManager.debug("- Exit");
		LogManager.popRemove();

		return list;
	}

	public List getHomePortFolioList(final String[][] value)
			throws BaseException {// vinoth Portfolio
		// string to list
		LogManager.push("getHomePortFolioList method()");
		LogManager.debug("- Enter");
		
		final List list = new ArrayList();
		final NumberFormat fmt = new DecimalFormat("##,##0");
		for (int i = 0; i < value.length; i++) {
			final PortfolioListBean bean = new PortfolioListBean();
			bean.setSno(i + 1);
			bean.setPolicyNo(value[i][0] == null ? "" : value[i][0]);
			bean.setQuoteNo(value[i][9] == null ? "" : value[i][9]);
			bean.setCustomerName(value[i][6] == null ? "" : value[i][6]);
			bean.setStartDate(value[i][3] + "/" + value[i][4] + "/"
					+ value[i][5]);
			bean.setPremium(value[i][2] == null ? "0" : fmt.format(Double
					.parseDouble(value[i][2])));
			bean.setCustomerID(value[i][1] == null ? "" : value[i][1]);
			list.add(bean);
		}

		LogManager.debug("- Exit");
		LogManager.popRemove();

		return list;
	}

	public List getHomeMergeLocationList(final String[][] value)
			throws BaseException {// vinoth
		// Declaration
		// string to list

		LogManager.push("getHomeMergeLocationList method()");
		LogManager.debug("- Enter");

		final List list = new ArrayList();
		final NumberFormat fmt = new DecimalFormat("##,##0");
		for (int i = 0; i < value.length; i++) {
			final DeclarationListBean bean = new DeclarationListBean();
			bean.setSno(i + 1);
			bean.setPolicyNo(value[i][0] == null ? "" : value[i][0]);
			bean.setMississippiNo(value[i][2] == null ? "" : value[i][2]);

			bean.setOpenCoverCustomerName(value[i][3] == null ? ""
					: value[i][3]);
			bean.setNoofCertificates(value[i][6] == null ? "" : value[i][6]);
			bean.setPremium(value[i][2] == null ? "" : fmt.format(Double
					.parseDouble(value[i][2])));
			list.add(bean);
		}

		LogManager.debug("- Exit");
		LogManager.popRemove();

		return list;
	}

	public List getHomeReferralPolicyList(final String[][] value,
			final String type) throws BaseException {// vinoth
		// Portfolio
		// string to list

		LogManager.push("getHomeReferralPolicyList method()");
		LogManager.debug("- Enter");

		final List list = new ArrayList();
		String cName;
		for (int i = 0; i < value.length; i++) {
			final ReferralPolicyDisplayBean bean = new ReferralPolicyDisplayBean();
			bean.setSno(i + 1);
			bean.setQuoteNo(value[i][0] == null ? "" : value[i][0]);
			bean.setQuoteDate(value[i][2] + "/" + value[i][3] + "/"
					+ value[i][4]);
			bean.setCustomerNo(value[i][1] == null ? "" : value[i][1]);
			bean.setLoginID(value[i][11] == null ? "" : value[i][11]);
			if (value[i][11] == null || value[i][11].equals("")
					|| value[i][11].equalsIgnoreCase("null")) {
				cName = (value[i][8] == null ? "" : value[i][8]) + " "
						+ (value[i][9] == null ? "" : value[i][9]);
			} else {
				cName = value[i][11];
			}
			bean.setCustomerName(cName);
			if ("app".equals(type)) {
				bean.setApprovedDate(value[i][5] + "/" + value[i][6] + "/"
						+ value[i][7]);
			} else if ("rej".equals(type)) {
				bean.setRejectedDate(value[i][5] + "/" + value[i][6] + "/"
						+ value[i][7]);
			}
			bean.setApplicationNo(value[i][13]==null?"0":value[i][13]);
			list.add(bean);
		}

		LogManager.debug("- Exit");
		LogManager.popRemove();

		return list;
	}

	public List getHomeQuoteRegisterList(final String[][] value,
			final String type) throws BaseException {// vinoth
		// Declaration
		// string to list
		LogManager.push("getHomeQuoteRegisterList method()");
		LogManager.debug("- Enter");

		final List list = new ArrayList();
		String str;
		for (int i = 0; i < value.length; i++) {
			QuoteRegisterBean bean = new QuoteRegisterBean();
			bean.setSno(i + 1);
			bean.setQuoteNo(value[i][0] == null ? "" : value[i][0]);
			bean.setCustomerID(value[i][1] == null ? "" : value[i][1]);
			if ("PN".equals(type) || "PC".equals(type)) {
				bean.setPremiumAmount(value[i][14] == null ? "" : value[i][14]);
				bean.setApplicationNo(value[i][15]);
			}

			bean.setQuoteDate(value[i][2] + "/" + value[i][3] + "/"
					+ value[i][4]);
			bean.setValidityPeriod(value[i][5] + "/" + value[i][6] + "/"
					+ value[i][7]);
			if ("E".equals(type)) {
				if (value[i][11]==null||value[i][11].length() <= 0 || value[i][11].equals("")
						|| value[i][11].equalsIgnoreCase("null")) {
					str = (value[i][8] == null ? "" : value[i][8]) + " "
							+ (value[i][9] == null ? "" : value[i][9]);
				} else {
					str = value[i][11];
				}
				bean.setCustomerName(str);
			} else {
				bean.setCustomerName(value[i][8] == null ? "" : value[i][8]);
			}

			if ("PN".equals(type)) {
				String payStatus;
				if (value[i][13].equalsIgnoreCase("F")) {
					payStatus = "Failure";
				} else {
					payStatus = "Unknown";
				}
				bean.setStatus(payStatus);
			}
			
			if("E".equalsIgnoreCase(type)){
				bean.setApplicationNo(value[i][13]);
			}
			else if("L".equalsIgnoreCase(type)){
				bean.setApplicationNo(value[i][9]);
			}			
			list.add(bean);
		}

		LogManager.debug("- Exit");
		LogManager.popRemove();

		return list;
	}
}
