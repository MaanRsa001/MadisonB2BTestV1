package com.maan.Home.MasterController;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import proj.date.DateFunction;

import com.maan.DBCon.DBConnectionStatus;
import com.maan.Home.DataManipualtion.HomeQuoteRegisterBean;
import com.maan.common.LogManager;
import com.maan.common.exception.BaseException;
import com.maan.services.util.ValidationFormat;
import com.maan.services.util.runner;
public class MasterController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	java.util.Date dd = new java.util.Date();
	final static transient private String PDFFPATHORI="pdfFilePathOriginal";
	final static transient private String PDFFPATHORlCOPY="pdfFilePathOriginalCopy";
	final static transient private String PDFFPATHCOPY="pdfFilePathCopy";
	final static transient private String PDFFPATHDEBIT="pdfFilePathDebit";
	final static transient private String FAIL="Failed to Delete-";
	final static transient private String DEL="Delete: write protected: ";
	final static transient private String PDF=".pdf";
	final static transient private String TYPE="Type";
	final static transient private String HOME="Home";
	final static transient private String TRAVEL="Travel";
	final static transient private String PRODUCTID= "product_id";
	final static transient private String DOBDAY = "dobDay";
	final static transient private String DOBMONTH = "dobMonth";
	final static transient private String DOBYEAR =  "dobYear";
	final static transient private String QUOTENUM = "quoteNo";
	final static transient private String ERRORMSG = "ErrorMsg";
	final static transient private String ERRORDETAILS = "errorDetail";
	final static transient private String CUSTOMERS = "customers";
	final static transient private String USERS = "user";
	final static transient private String PROID = "proId";
	final static transient private String OPTION = "Option";
	final static transient private String PDFQUOTE = "PDFQuoteNo";
	final static transient private String REMARKS= "remarks";
	final static transient private String CLAUSES = "clauses";
	final static transient private String QUOTENO = "QuoteNo";
	final static transient private String ADDSIGN = "addSign";
	final static transient private String ADDPRE = "addPremium";
	final static transient private String BROKER = "Broker";
	final static transient private String DAY = "DD";
	final static transient private String MONTH = "MM";
	final static transient private String YEAR = "YYYY";
	final static transient private String EXPDATE = "ExpDate";
	final static transient private String EFFECTDATE = "EffectiveDate";
	final static transient private String QUOTENOS = "quoteno";
	final static transient private String BRANCH = "branch";
	final static transient private String PDFCREATOR = "*.PDFCreator";
	final static transient private String SELECT = "select";
	final static transient private String NULL = "null";
	final static transient private String SPACE= "&nbsp;";
	final static transient private String SPACES = "&nbsp;,";
	final static transient private String SNO = "SNo";
	final static transient private String INCREMENT =  "Incrementvalue";
	final static transient private String REM = "RemValue";
	final static transient private String ADDITEM = "AddItem";
	final static transient private String ADDVAL = "AddedValues";
	final static transient private String PROIDS = "ProId";
	final static transient private String INCREMENTVAL = "Incrementvalues";
	final static transient private String COVERAGES = "CoverageDetails";
	final static transient private String EXCESS = "excess";
	final static transient private String CLAIM = "clm";
	final static transient private String BUILDING = "Building";
	final static transient private String CONTENT = "Contents";
	final static transient private String PERSONAL = "Personal";
	final static transient private String COVERID = "CoverId";
	final static transient private String CLAIMAMOUNT = "CLAIMAMOUNT";
	final static transient private String CLAIMDETAILS = "CLAIMDETAILS";
	final static transient private String DROPDOWN = "DROP_DOWN";
	final static transient private String CHECK = "Check";
	final static transient private String DOMESTIC = "empDomestic";
	final static transient private String BED = "bed";
	final static transient private String NOOFROOM = "noroom";
	final static transient private String ROOMSUM = "roomSum";
	final static transient private String BETCONTENT = "bedContent";
	final static transient private String FOR = "For ";
	final static transient private String INFO = "../HomeInsurance/information.jsp";
	final static transient private String MAILMSG = "../HomeInsurance/Messagenotsending.jsp";
	final static transient private String PRESUM = "../HomeInsurance/PremiumSummary.jsp";
	final static transient private String LOYALTY = "Loyalty";
	final static transient private String MEMBERNO = "memberNo";
	
	public void doPost(final HttpServletRequest request, final HttpServletResponse response)
			throws ServletException, IOException {
		try{
			processResult(request, response);
		} catch(BaseException e) {
			LogManager.debug(e);
		}
	}
	public void doGet(final HttpServletRequest request, final HttpServletResponse response)
			throws ServletException, IOException {
		try{
			processResult(request, response);
		} catch(BaseException e) {
			LogManager.debug(e);
		}
	}
	public void processResult(final HttpServletRequest request,final HttpServletResponse response) throws ServletException, IOException,BaseException {
		try {
			System.out.println("Inside processResult===");
			response.setContentType("text/html");
			final HttpSession session = request.getSession(false);
			final String pathh = request.getContextPath();
			DBConnectionStatus.statusStatic = isNull((String) session.getAttribute("userLoginMode"));
			final String paths = request.getRequestURI();
			if (session.getAttribute("ses") == null) {
				response.sendRedirect(pathh + "/login/error_messg.jsp");
				return;
			}
			final String path = paths.substring(paths.lastIndexOf('/') + 1, paths.lastIndexOf('.'));
			System.out.println("==> Path: "+path);
			//final String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+pathh+"/";
			final ValidationFormat validationFormat = new ValidationFormat();
			final com.maan.Home.DataManipualtion.DataManipualtion DataManip = new com.maan.Home.DataManipualtion.DataManipualtion();
			final com.maan.Home.DataManipualtion.DataSelect dataSelect = new com.maan.Home.DataManipualtion.DataSelect();
			final com.maan.Home.DataManipualtion.DataSelectCustomer dataCus = new com.maan.Home.DataManipualtion.DataSelectCustomer();
			final String branch = (String) session.getAttribute("LoginBranchCode");
			String pids = (String) session.getAttribute(PRODUCTID);
			final String schemeId = (String) session.getAttribute("scheme_id");
			HashMap brokerDetails = (HashMap)session.getAttribute("BrokerDetails");
	  		String cid = "";
	  		if(brokerDetails.size()>0){
	  			cid = (String)brokerDetails.get("Orgination");
	  		}
	  		
			String prodDesc = "";
			if ("newHomeCustomer".equalsIgnoreCase(path)) {
				final String popup = isNull(request.getParameter("popup"));
				final String mode = request.getParameter("copymode"); 
				String linkFrom = request.getParameter("linkFrom");
				String applicationNo = request.getParameter("applicationNo");
				String referral = request.getParameter("referral");
				linkFrom = linkFrom==null?"":linkFrom;
				applicationNo = applicationNo==null?"":applicationNo;
				referral=referral==null?"":referral;
				final com.maan.Home.DataManipualtion.DataSelect cusSelect = new com.maan.Home.DataManipualtion.DataSelect();
				cusSelect.setTitle(isNull(request.getParameter("title")));
				cusSelect.setFirstName(isNull(request.getParameter("firstName")));
				cusSelect.setNationality(isNull(request.getParameter("nationality")));
				cusSelect.setDobDay(isNull(request.getParameter(DOBDAY)));
				cusSelect.setDobMonth(isNull(request.getParameter(DOBMONTH)));
				cusSelect.setDobYear(isNull(request.getParameter(DOBYEAR)));
				cusSelect.setTelephone(isNull(request.getParameter("telephone")));
				cusSelect.setMobile(isNull(request.getParameter("mobile")));
				cusSelect.setFax(isNull(request.getParameter("fax")));
				cusSelect.setEmail(isNull(request.getParameter("email")));
				cusSelect.setAddress1(isNull(request.getParameter("address1")));
				cusSelect.setOccupation(isNull(request.getParameter("occupation")));
				cusSelect.setOtherSource(isNull(request.getParameter("otherSource")));
				cusSelect.setSource(isNull(request.getParameter("source")));
				cusSelect.setEmirate(isNull(request.getParameter("emirate")));
				cusSelect.setCountry(isNull(request.getParameter("country")));
				cusSelect.setPoBox(isNull(request.getParameter("poBox")));
				cusSelect.setOrgName(isNull(request.getParameter("orgName")));
				cusSelect.setInsuredlocation(isNull(request.getParameter("insuredLocation")));
				cusSelect.setCid(cid);
				if(pids.equalsIgnoreCase("30")){
					prodDesc ="Office";
				}else{
					prodDesc = "Home";
				}
				StringBuffer ErrorMsg = cusSelect.validateFields(pids,prodDesc);
				final String CheckCustomerWay = isNull(request.getParameter("CheckCustomerWay"));

				RequestDispatcher customerRequest = null;
				if (ErrorMsg.length() > 0) {
					final String quoteno = request.getParameter(QUOTENUM);
					request.setAttribute(ERRORMSG, ErrorMsg);
					request.setAttribute(QUOTENUM, quoteno);
					customerRequest = request.getRequestDispatcher("/CustomerInfo/CustomerInfoHome.jsp");
					if (CheckCustomerWay.equalsIgnoreCase("EditSaveCus")) {
						//session.setAttribute(QUOTENUM, quoteno);
						request.setAttribute("copy", mode);
						customerRequest = request.getRequestDispatcher("/HomeInsurance/Customers.jsp");
						request.setAttribute(ERRORDETAILS, ErrorMsg);

					}
					if (customerRequest != null){
						customerRequest.forward(request, response);
					}
				} else {
					boolean flag = false;
					String CustomerId = isNull(request.getParameter(CUSTOMERS));
					if (CustomerId.equalsIgnoreCase("")){
						CustomerId = isNull(request.getParameter("customer"));
					}
					final String cusVal = cusSelect.storedValues(CustomerId, (String) session.getAttribute(USERS), branch, pids);
					session.setAttribute("customer_id", isNull(cusVal));
					if("AllocateCustomer".equalsIgnoreCase(linkFrom)){
						customerRequest = request.getRequestDispatcher("/servlet/motorQuote?linkFrom=customers&customers="+isNull(cusVal)+"&applicationNo="+applicationNo+"&referral="+referral);
						flag = false;
					}else if("sendmail".equalsIgnoreCase(linkFrom)){
						customerRequest = request.getRequestDispatcher("/servlet/motorQuote?linkFrom=customers&DisplayValue=mail&customers="+isNull(cusVal)+"&applicationNo="+applicationNo+"&referral="+referral);
						flag = false;
					}else{
						if (CheckCustomerWay.equalsIgnoreCase("SaveAndContinue")) {
							customerRequest = request.getRequestDispatcher("/HomeInsurance/CoverInformationA.jsp");
						} else if (CheckCustomerWay.equalsIgnoreCase("SaveAndExit")) {
							ErrorMsg.append(runner.getErrormsg("191"));
							request.setAttribute(ERRORMSG, ErrorMsg);
							customerRequest = request.getRequestDispatcher("/CustomerInfo/CustomerInfoHome.jsp");
						} else if (CheckCustomerWay.equalsIgnoreCase("SaveOnly")) {
							customerRequest = request.getRequestDispatcher("/HomeInsurance/generalMsg.jsp");
						} else if (CheckCustomerWay.equalsIgnoreCase("EditSaveCus")) {
							String rFrom=request.getParameter("emails")==null?"":request.getParameter("emails");
							System.out.println("rFrom: "+rFrom);
							if("yes".equalsIgnoreCase(rFrom))
							{
								String QuoteNum=request.getParameter("quoteNo")==null?"":request.getParameter("quoteNo");
								System.out.println("QuoteNum: "+QuoteNum);
								customerRequest = request.getRequestDispatcher("/HomeMailController?reqFrom=Customer&productTypes=Travel&QuoteNo"+QuoteNum);
							}
							else
							{
							final String QuoteNoss = isNull(request.getParameter("quoteNos"));
							if (QuoteNoss.length() > 0&& !NULL.equalsIgnoreCase(QuoteNoss)) {
								customerRequest = request.getRequestDispatcher("/HomeDisplayController?DisplayValue=portfolio");
							} else {
								flag = true;
							}
							}
						}
					}
					if (flag) {
						if ("true".equalsIgnoreCase(mode)) {
							final String quoteno = request.getParameter(QUOTENUM);
							session.setAttribute(QUOTENUM, quoteno);
							request.setAttribute(QUOTENUM, quoteno);
							customerRequest = request.getRequestDispatcher("../CopyQuote/copyQuoteCustomers_B2B1.jsp");
							customerRequest.forward(request, response);
						} else {
							response.sendRedirect(pathh+"/HomeDisplayController?DisplayValue=customer&popup="+ popup);
						}
					} else {
						if (customerRequest != null){
							customerRequest.forward(request, response);
						}
					}
				}
			}
			if (path.equalsIgnoreCase("PdfSummary_Schedule")) {
				String proId = isNull(request.getParameter(PROID));
				proId = getSessionPid(session,proId);
				request.setAttribute(OPTION, "Schedule");
				
				final String quoteno = (String) session.getAttribute(PDFQUOTE);
				final String remarks = isNull(request.getParameter(REMARKS));
				final String clauses = isNull(request.getParameter(CLAUSES));
				final String hour = dataSelect.getSysdateTime(quoteno, QUOTENO);
				final String loginId = (String) session.getAttribute(USERS);
				final String addSign = isNull(request.getParameter(ADDSIGN));
				final String addPremium = isNull(request.getParameter(ADDPRE));
				final String modeOfPurchase = isNull(request.getParameter("modeOfPurchase"));
				final StringBuffer paymentMode = new StringBuffer();
				if (modeOfPurchase.equalsIgnoreCase("Y")){
					paymentMode.append("Credit Card");
				}
				else if (modeOfPurchase.equalsIgnoreCase("N")){
					paymentMode.append("Cheque");
				}
				else{
					paymentMode.append("Cash");
				}
				String memberNo;memberNo = isNull(request.getParameter(MEMBERNO));
				String loyalty;loyalty = isNull(request.getParameter(LOYALTY));
				if(loyalty.equals("0")){
					memberNo = "";
				}
				
				final boolean loadDisStatus = dataSelect.getLoadDisStatus(loginId, proId);
				if (loadDisStatus){
					final HomeQuoteRegisterBean quoteReg = new HomeQuoteRegisterBean();
					quoteReg.getAdminReferralUpdation(quoteno, addSign,addPremium, loginId, proId, BROKER);
				}
				// Insurence Date update
				final String day = isNull(request.getParameter(DAY));
				final String mon = isNull(request.getParameter(MONTH));
				final String year = isNull(request.getParameter(YEAR));
				final String expDate = isNull(request.getParameter(EXPDATE));

				final Map datesVal = (HashMap)validateInsureDate(day,mon,year);
				StringBuffer ErrorMsg = (StringBuffer)datesVal.get(ERRORMSG);
				final String effectiveDate = (String)datesVal.get(EFFECTDATE);
				if (ErrorMsg.length() > 0) {
					request.setAttribute(ERRORMSG, ErrorMsg);
					final RequestDispatcher dispatcher = request.getRequestDispatcher(INFO);
					dispatcher.forward(request, response);
				} else {
					ErrorMsg = validateBackDates(day,mon,year,loginId,proId);
					if (ErrorMsg.length() > 0) {
						request.setAttribute(ERRORMSG, ErrorMsg);
						final RequestDispatcher dispatcher = request.getRequestDispatcher(INFO);
						dispatcher.forward(request, response);
					}else{
						final Map policyCollection = new HashMap();
						policyCollection.put(EFFECTDATE, effectiveDate);
						policyCollection.put(EXPDATE, expDate);
						policyCollection.put(CLAUSES, clauses);
						policyCollection.put(REMARKS, remarks);
						policyCollection.put(LOYALTY, loyalty);
						policyCollection.put(MEMBERNO, memberNo);
						policyCollection.put(QUOTENOS, quoteno);
						policyCollection.put(PROID, proId);
						policyCollection.put(BRANCH, branch);
						policyCollection.put("paymentMode", paymentMode.toString());
						dataSelect.updateDateDetails(policyCollection,hour);
						dataSelect.updatePolicyDetails(policyCollection,hour,branch);
						final RequestDispatcher dispatcher = request.getRequestDispatcher(PDFCREATOR);
						dispatcher.forward(request, response);
					}
				}
			}
			if (path.equalsIgnoreCase("showPolicys")) {
				String proId = isNull(request.getParameter(PROID));
				proId = getSessionPid(session,proId);
				String adminStatus = isNull(request.getParameter("adminStatus"));
				String quoteno = (String) session.getAttribute(PDFQUOTE);
				final String remarks = isNull(request.getParameter(REMARKS));
				final String clauses = isNull(request.getParameter(CLAUSES));
				final String hour = dataSelect.getSysdateTime(quoteno, QUOTENO);
				final String loginId = (String) session.getAttribute(USERS);
				final String addSign = isNull(request.getParameter(ADDSIGN));
				final String addPremium = isNull(request.getParameter(ADDPRE));
				final String modeOfPurchase = isNull(request.getParameter("modeOfPurchase"));
				final StringBuffer paymentMode = new StringBuffer();
				if (modeOfPurchase.equalsIgnoreCase("Y")){
					paymentMode.append("Credit Card");}
				else if (modeOfPurchase.equalsIgnoreCase("N")){
					paymentMode.append("Cheque");
				}else{
					paymentMode.append("Cash");
				}
				
				String memberNo;memberNo = isNull(request.getParameter(MEMBERNO));
				String loyalty;loyalty = isNull(request.getParameter(LOYALTY));
				if(loyalty.equals("0")){
					memberNo = "";
				}
				
				final boolean loadDisStatus = dataSelect.getLoadDisStatus(loginId, proId);
				if (loadDisStatus){
					final HomeQuoteRegisterBean quoteReg = new HomeQuoteRegisterBean();
					quoteReg.getAdminReferralUpdation(quoteno, addSign,addPremium, loginId, proId, BROKER);
				}
				if (adminStatus.equalsIgnoreCase("Y")) {
					final String adminRemarks = isNull(request.getParameter("adminRemarks"));
					if ("".equals(quoteno) || quoteno.length() <= 0){
						quoteno = (String) session.getAttribute(QUOTENO) == null ? "": (String) session.getAttribute(QUOTENO);
					}
					request.setAttribute(QUOTENO, quoteno);
					session.setAttribute(QUOTENO, quoteno);
					request.setAttribute(PROIDS, proId);
					session.setAttribute("referralName", "Admin Referral");
					dataSelect.updateReferalStatus(quoteno, proId,"Admin Referral", adminRemarks, adminStatus);
					final RequestDispatcher mailDispatch = request.getRequestDispatcher("/HomeMailController?reqFrom=Referral");
					if (mailDispatch != null){
						mailDispatch.include(request, response);
					}
					final RequestDispatcher dispatcher = request.getRequestDispatcher("/HomeInsurance/Home_Referral_ShowQuote.jsp");
					dispatcher.forward(request, response);
					
				} else  {
					final String day = isNull(request.getParameter(DAY));
					final String mon = isNull(request.getParameter(MONTH));
					final String year = isNull(request.getParameter(YEAR));
					final String expDate = isNull(request.getParameter(EXPDATE));
	
					final Map datesVal = (HashMap)validateInsureDate(day,mon,year);
					StringBuffer ErrorMsg = (StringBuffer)datesVal.get(ERRORMSG);
					final String effectiveDate = (String)datesVal.get(EFFECTDATE);
					if (ErrorMsg.length() > 0) {
						request.setAttribute(ERRORMSG, ErrorMsg);
						final RequestDispatcher dispatcher = request.getRequestDispatcher(MAILMSG);
						dispatcher.forward(request, response);
					}else {
						ErrorMsg = validateBackDates(day,mon,year,loginId,proId);
						if (ErrorMsg.length() > 0) {
							request.setAttribute(ERRORMSG, ErrorMsg);
							final RequestDispatcher dispatcher = request.getRequestDispatcher(MAILMSG);
							dispatcher.forward(request, response);
						}else{
							final Map policyCollection = new HashMap();
							policyCollection.put(EFFECTDATE, effectiveDate);
							policyCollection.put(EXPDATE, expDate);
							policyCollection.put(CLAUSES, clauses);
							policyCollection.put(REMARKS, remarks);
							policyCollection.put(LOYALTY, loyalty);
							policyCollection.put(MEMBERNO, memberNo);
							policyCollection.put(QUOTENOS, quoteno);
							policyCollection.put(PROID, proId);
							policyCollection.put(BRANCH, branch);
							policyCollection.put("paymentMode", paymentMode.toString());
							dataSelect.updateDateDetails(policyCollection,hour);
							dataSelect.updatePolicyDetails(policyCollection,hour,branch);
							adminStatus = "N";
							if (adminStatus.equalsIgnoreCase("N")) {
								dataSelect.updatePolicyDetails(policyCollection,hour,branch);
								dataSelect.updateDebitDetails(policyCollection,hour,branch);
								request.setAttribute(OPTION, "All");
								final RequestDispatcher dispatcher1 = request.getRequestDispatcher(PDFCREATOR);
								dispatcher1.include(request, response);
								final RequestDispatcher dispatcher = request.getRequestDispatcher("/HomeInsurance/showPolicys.jsp");
								dispatcher.forward(request, response);
							}
						}
					}
				}
			}
			if (path.equalsIgnoreCase("PdfSummary_Debit")) {
				request.setAttribute(OPTION, "Debit");
				String proId = isNull(request.getParameter(PROID));
				proId = getSessionPid(session,proId);
				final String quoteno = (String) session.getAttribute(PDFQUOTE);
				final String hour = dataSelect.getSysdateTime(quoteno, QUOTENO);
				final Map policyCollection = new HashMap();
				policyCollection.put(QUOTENOS, quoteno);
				policyCollection.put(PROID, proId);
				policyCollection.put(BRANCH, branch);
				dataSelect.updateDebitDetails(policyCollection,hour,branch);	
				final RequestDispatcher dispatcher = request.getRequestDispatcher(PDFCREATOR);
				dispatcher.forward(request, response);
			}
			// Receipt No
			if (path.equalsIgnoreCase("PdfSummary_Receipt")) {
				request.setAttribute(OPTION, "Receipt");
				String proId = isNull(request.getParameter(PROID));
				proId = getSessionPid(session,proId);
				com.maan.Home.DataManipualtion.HomePdfDataSelect data = new com.maan.Home.DataManipualtion.HomePdfDataSelect();
				data.getReceiptNoNew((String)session.getAttribute(PDFQUOTE),proId,branch,request.getParameter("modeOfPurchase"));
				final RequestDispatcher dispatcher = request.getRequestDispatcher(PDFCREATOR);
				dispatcher.forward(request, response);
			}
			// Office Shield - Debit Multiple Start
			if (path.equalsIgnoreCase("PdfSummary_DebitMultiple")) {
				request.setAttribute(OPTION, "DebitMultiple");
				String proId = "";
				proId = isNull(request.getParameter(PROID));
				proId = getSessionPid(session,proId);
				final String quoteno = (String) session.getAttribute(PDFQUOTE);
				final String hour = dataSelect.getSysdateTime(branch, BRANCH);
				final Map policyCollection = new HashMap();
				policyCollection.put(QUOTENOS, quoteno);
				policyCollection.put(PROID, proId);
				policyCollection.put(BRANCH, branch);
				dataSelect.updateDebitDetails(policyCollection,hour,branch);
				final RequestDispatcher dispatcher = request.getRequestDispatcher(PDFCREATOR);
				dispatcher.forward(request, response);
			}
			// Admin Side Customer Creation Start - E-Office
			if ("newOfficeCustomer".equalsIgnoreCase(path)) {
				String CustomerId = "";
				final com.maan.Home.DataManipualtion.DataSelect cusSelect = new com.maan.Home.DataManipualtion.DataSelect();
				final String brokerId = isNull(request.getParameter("brokerId"));
				final String officeProductId = isNull(request.getParameter("officeProductId"));
				cusSelect.setFirstName(isNull(request.getParameter("custName")));
				cusSelect.setAddress1(isNull(request.getParameter("custAddress")));
				cusSelect.setEmirate(isNull(request.getParameter("custCity")));
				cusSelect.setCountry(isNull(request.getParameter("custCountry")));
				cusSelect.setPoBox(isNull(request.getParameter("custPOBox")));
				cusSelect.setTelephone(isNull(request.getParameter("custPhone")));
				cusSelect.setEmail(isNull(request.getParameter("custMailId")));
				/* Office Shield Admin Side Customer Creation */
				cusSelect.setTitle(isNull(request.getParameter("title")));
				cusSelect.setNationality(isNull(request.getParameter("nationality")));
				cusSelect.setDobDay(isNull(request.getParameter(DOBDAY)));
				cusSelect.setDobMonth(isNull(request.getParameter(DOBMONTH)));
				cusSelect.setDobYear(isNull(request.getParameter(DOBYEAR)));
				cusSelect.setMobile(isNull(request.getParameter("mobile")));
				cusSelect.setFax(isNull(request.getParameter("fax")));
				cusSelect.setOccupation(isNull(request.getParameter("occupation")));
				cusSelect.setOtherSource(isNull(request.getParameter("otherSource")));
				cusSelect.setSource(isNull(request.getParameter("source")));
				cusSelect.setOrgName(isNull(request.getParameter("orgName")));
				cusSelect.setInsuredlocation(isNull(request.getParameter("insuredLocation")));
				/* Office Shield Admin Side Customer Creation */
				final StringBuffer ErrorMsg = cusSelect.validateFields(officeProductId,"Office");
				if (brokerId.length() == 0	|| brokerId.equalsIgnoreCase(SELECT)) {
					ErrorMsg.append("Plese select broker,");
				}
				if (ErrorMsg.length() > 0) {
					request.setAttribute(ERRORMSG, ErrorMsg);
					final RequestDispatcher cusDis = request.getRequestDispatcher("/OfficeConfig/addCustomer.jsp");
					cusDis.forward(request, response);
				} else {
					CustomerId = isNull(request.getParameter("customerId"));
					cusSelect.storedValues(CustomerId, brokerId, branch,officeProductId);
					response.sendRedirect("../OfficeConfig/adminCustomerConfirmation.jsp");
				}
			}
			if (path.equalsIgnoreCase("PdfSummary_Draft")) {
				final String remarks = isNull(request.getParameter(REMARKS));
				final String clauses = isNull(request.getParameter(CLAUSES));
				final String proId = (String) session.getAttribute(PRODUCTID);
				final String quoteno = (String) session.getAttribute(PDFQUOTE);
				final String loginId = (String) session.getAttribute(USERS);
				final String addSign = isNull(request.getParameter(ADDSIGN));
				final String addPremium = isNull(request.getParameter(ADDPRE));
				
				String memberNo;memberNo = isNull(request.getParameter(MEMBERNO));
				String loyalty;loyalty = isNull(request.getParameter(LOYALTY));
				if(loyalty.equals("0")){
					memberNo = "";
				}
				
				final boolean loadDisStatus = dataSelect.getLoadDisStatus(loginId, proId);
				if (loadDisStatus){
					final HomeQuoteRegisterBean quoteReg = new HomeQuoteRegisterBean();
					quoteReg.getAdminReferralUpdation(quoteno, addSign,addPremium, loginId, proId, BROKER);
				}
				// Insurence Date update
				final String day = isNull(request.getParameter(DAY));
				final String mon = isNull(request.getParameter(MONTH));
				final String year = isNull(request.getParameter(YEAR));
				final String expDate = isNull(request.getParameter(EXPDATE));
				final Map datesVal = (HashMap)validateInsureDate(day,mon,year);
				StringBuffer ErrorMsg = (StringBuffer)datesVal.get(ERRORMSG);
				final String effectiveDate = (String)datesVal.get(EFFECTDATE);
				if (ErrorMsg.length() > 0) {
					request.setAttribute(ERRORMSG, ErrorMsg);
					final RequestDispatcher dispatcher = request.getRequestDispatcher(INFO);
					dispatcher.forward(request, response);
				} else {
					ErrorMsg = validateBackDates(day,mon,year,loginId,proId);
					if (ErrorMsg.length() > 0) {
						request.setAttribute(ERRORMSG, ErrorMsg);
						final RequestDispatcher dispatcher = request.getRequestDispatcher(INFO);
						dispatcher.forward(request, response);
					}
					else{
						final String hour = dataSelect.getSysdateTime(quoteno, QUOTENO);
						final Map policyCollection = new HashMap();
						policyCollection.put(EFFECTDATE, effectiveDate);
						policyCollection.put(EXPDATE, expDate);
						policyCollection.put(CLAUSES, clauses);
						policyCollection.put(REMARKS, remarks);
						policyCollection.put(LOYALTY, loyalty);
						policyCollection.put(MEMBERNO, memberNo);
						policyCollection.put(QUOTENOS, quoteno);
						policyCollection.put(PROID, proId);
						dataSelect.updateDateDetails(policyCollection,hour);
						request.setAttribute(OPTION, "Draft");
						final RequestDispatcher dispatcher = request.getRequestDispatcher(PDFCREATOR);
						dispatcher.forward(request, response);
					}
				}
			}
			if (path.equalsIgnoreCase("PdfSummary_PrintQuote")) {
				final String remarks = isNull(request.getParameter(REMARKS));
				final String clauses = isNull(request.getParameter(CLAUSES));
				final String proId = (String) session.getAttribute(PRODUCTID);
				final String quoteno = (String) session.getAttribute(PDFQUOTE);
				final String loginId = (String) session.getAttribute(USERS);
				final String addSign = isNull(request.getParameter(ADDSIGN));
				final String addPremium = isNull(request.getParameter(ADDPRE));
				final boolean loadDisStatus = dataSelect.getLoadDisStatus(loginId, proId);
				if (loadDisStatus){
					final HomeQuoteRegisterBean quoteReg = new HomeQuoteRegisterBean();
					quoteReg.getAdminReferralUpdation(quoteno, addSign,addPremium, loginId, proId, BROKER);
				}
				// Insurence Date update
				final String day = isNull(request.getParameter(DAY));
				final String mon = isNull(request.getParameter(MONTH));
				final String year = isNull(request.getParameter(YEAR));
				final String expDate = isNull(request.getParameter(EXPDATE));
				final Map datesVal = (HashMap)validateInsureDate(day,mon,year);
				StringBuffer ErrorMsg = (StringBuffer)datesVal.get(ERRORMSG);
				final String effectiveDate = (String)datesVal.get(EFFECTDATE);
				if (ErrorMsg.length() > 0) {
					request.setAttribute(ERRORMSG, ErrorMsg);
					RequestDispatcher dispatcher = request.getRequestDispatcher(INFO);
					dispatcher.forward(request, response);
				} else {
					ErrorMsg = validateBackDates(day,mon,year,loginId,proId);
					if (ErrorMsg.length() > 0) {
						request.setAttribute(ERRORMSG, ErrorMsg);
						final RequestDispatcher dispatcher = request.getRequestDispatcher(INFO);
						dispatcher.forward(request, response);
					}
					else{
						final String hour = dataSelect.getSysdateTime(quoteno, QUOTENO);
						final Map policyCollection = new HashMap();
						policyCollection.put(EFFECTDATE, effectiveDate);
						policyCollection.put(EXPDATE, expDate);
						policyCollection.put(CLAUSES, clauses);
						policyCollection.put(REMARKS, remarks);
						policyCollection.put(QUOTENOS, quoteno);
						policyCollection.put(PROID, proId);
						dataSelect.updateDateDetails(policyCollection,hour);
						request.setAttribute(OPTION, "PrintQuote");
						final RequestDispatcher dispatcher = request.getRequestDispatcher(PDFCREATOR);
						dispatcher.forward(request, response);
					}
				}
			}
			if (path.equalsIgnoreCase("CustomMail")) {
				final String remarks = isNull(request.getParameter(REMARKS));
				final String clauses = isNull(request.getParameter(CLAUSES));
				final String proId = (String) session.getAttribute(PRODUCTID);
				final String quoteno = (String) session.getAttribute(PDFQUOTE);
				final String loginId = (String) session.getAttribute(USERS);
				final String addSign = isNull(request.getParameter(ADDSIGN));
				final String addPremium = isNull(request.getParameter(ADDPRE));
				final boolean loadDisStatus = dataSelect.getLoadDisStatus(loginId, proId);
				if (loadDisStatus){
					final HomeQuoteRegisterBean quoteReg = new HomeQuoteRegisterBean();
					quoteReg.getAdminReferralUpdation(quoteno, addSign,addPremium, loginId, proId, BROKER);
				}
				// Insurence Date update
				final String day = isNull(request.getParameter(DAY));
				final String mon = isNull(request.getParameter(MONTH));
				final String year = isNull(request.getParameter(YEAR));
				final String expDate = isNull(request.getParameter(EXPDATE));
				final Map datesVal = (HashMap)validateInsureDate(day,mon,year);
				StringBuffer ErrorMsg = (StringBuffer)datesVal.get(ERRORMSG);
				final String effectiveDate = (String)datesVal.get(EFFECTDATE);
				if (ErrorMsg.length() > 0) {
					request.setAttribute(ERRORMSG, ErrorMsg);
					final RequestDispatcher dispatcher = request.getRequestDispatcher(MAILMSG);
					dispatcher.forward(request, response);
				} else {
					ErrorMsg = validateBackDates(day,mon,year,loginId,proId);
				}
				if (ErrorMsg.length() > 0) {
					request.setAttribute(ERRORMSG, ErrorMsg);
					final RequestDispatcher dispatcher = request.getRequestDispatcher(MAILMSG);
					dispatcher.forward(request, response);
				}
				final String hour = dataSelect.getSysdateTime(quoteno, QUOTENO);
				final Map policyCollection = new HashMap();
				policyCollection.put(EFFECTDATE, effectiveDate);
				policyCollection.put(EXPDATE, expDate);
				policyCollection.put(CLAUSES, clauses);
				policyCollection.put(REMARKS, remarks);
				policyCollection.put(QUOTENOS, quoteno);
				policyCollection.put(PROID, proId);
				dataSelect.updateDateDetails(policyCollection,hour);
				final RequestDispatcher dispatcher = request.getRequestDispatcher("/HomeMailController?reqFrom=Customer");
				dispatcher.forward(request, response);
			}
			if (path.equalsIgnoreCase("PdfSummary_PolicyWording")) {
				request.setAttribute(OPTION, "PolicyWording");
				final RequestDispatcher dispatcher = request.getRequestDispatcher(PDFCREATOR);
				dispatcher.forward(request, response);
			}
			if (path.equalsIgnoreCase("deleteInfo")) {
				final String lapsedStatus = isNull(request.getParameter("LapsedStatus"));
				final StringBuffer ErrorMsg = new StringBuffer();
				if (lapsedStatus.equalsIgnoreCase(SELECT)) {
					ErrorMsg.append(runner.getErrormsg("192"));
					ErrorMsg.append(',');
					request.setAttribute(ERRORMSG, ErrorMsg);
					final RequestDispatcher dispatcher = request.getRequestDispatcher("/HomeInsurance/LapsedQuote1.jsp");
					dispatcher.forward(request, response);
				} else {
					final String quoteNo = request.getParameter("Quote_No");
					final String userName = (String)session.getAttribute("userName");
					final String quote = dataSelect.deleteTable(quoteNo, lapsedStatus,userName);
					session.setAttribute("quote_no", quote);
					final RequestDispatcher dispatcher = request.getRequestDispatcher("/HomeInsurance/Quotedelete.jsp");
					dispatcher.forward(request, response);
				}
			}
			if ("existingCustomer".equalsIgnoreCase(path)) {
				final String customer = isNull(request.getParameter(CUSTOMERS));
				if (customer.equalsIgnoreCase("")|| customer.equalsIgnoreCase(NULL)) {
					request.setAttribute(ERRORDETAILS, runner.getErrormsg("46"));
					final RequestDispatcher dispatcher = request.getRequestDispatcher("HomeDisplayController?DisplayValue=customer");
					dispatcher.forward(request, response);
				} else {
					request.setAttribute("cusid", customer);
					final RequestDispatcher dispatcher = request.getRequestDispatcher("/CustomerInfo/CustomerInfoHome.jsp");
					dispatcher.forward(request, response);
				}
			}
			if ("existingCustomerPOPUP".equalsIgnoreCase(path)) // OfficeSchield
			{
				final String customer = isNull(request.getParameter(CUSTOMERS));
				if (customer.equalsIgnoreCase("")|| customer.equalsIgnoreCase(NULL)) {
					request.setAttribute(ERRORDETAILS, runner.getErrormsg("46"));
					//final RequestDispatcher dispatcher = request.getRequestDispatcher("/HomeInsurance/ExistingCustomers_B2B.jsp?popup=popup");
					final RequestDispatcher dispatcher = request.getRequestDispatcher("HomeDisplayController?DisplayValue=customer&popup=popup");
					dispatcher.forward(request, response);
				}
			}
			if ("existingCustomerFlow".equalsIgnoreCase(path))// OfficeSchield
			{
				final String customer = isNull(request.getParameter(CUSTOMERS));
				if (customer.equalsIgnoreCase("")|| customer.equalsIgnoreCase(NULL)) {
					request.setAttribute(ERRORDETAILS, runner.getErrormsg("46"));
					final RequestDispatcher dispatcher = request.getRequestDispatcher("HomeDisplayController?DisplayValue=customer");
					dispatcher.forward(request, response);
				} else {
					request.setAttribute("existingCustomerFlow","existingCustomerFlow"); // Sep30
					final RequestDispatcher dispatcher = request.getRequestDispatcher("/CustomerInfo/CustomerInfoOShield.jsp?customer="
									+ customer);
					dispatcher.forward(request, response);
				}
			}
			if ("existingCustomerFire".equalsIgnoreCase(path))// FireInsurance
			{
				final String customer = isNull(request.getParameter(CUSTOMERS));
				if (customer.equalsIgnoreCase("")|| customer.equalsIgnoreCase(NULL)) {
					request.setAttribute(ERRORDETAILS, runner.getErrormsg("46"));
					final RequestDispatcher dispatcher = request.getRequestDispatcher("HomeDisplayController?DisplayValue=customer");
					dispatcher.forward(request, response);
				} else {
					final RequestDispatcher dispatcher = request.getRequestDispatcher("/customerandCovers.fire?customer="+ customer+"&method=Products");
					dispatcher.forward(request, response);
				}
			}
			if (path.equalsIgnoreCase("Summary")) {
				final RequestDispatcher dispatcher = request.getRequestDispatcher("/HomeInsurance/summary.jsp");
				dispatcher.forward(request, response);
			}
			if (path.equalsIgnoreCase("ListItemChoose")) {
				final StringBuffer ErrorMsg = new StringBuffer();
				final StringBuffer RemValue = new StringBuffer();
				final String AddedValue = isNull(request.getParameter("AddedValue"));
				final String BAmount = isNull(request.getParameter("BAmount"));
				final String ListString = (String) session.getAttribute("ListString");
				final StringBuffer SNoEmpty = new StringBuffer();
				String SNoValid = "";
				final StringBuffer ItemEmpty = new StringBuffer();
				final StringBuffer SumInsuredEmpty = new StringBuffer();
				String SumInsuredValid = "";
				int loopJ;
				final int CountCollection = Integer.parseInt(AddedValue);
				double TotalSumInsured = 0.0;
				double AfterSumInsured;
				String Incrementvalue;

				for (int i = 0; i < CountCollection; i++) {
					final String SNo = isNull(request.getParameter(SNO + ListString + i));
					final String Item = isNull(request.getParameter("Description"+ ListString + i));
					final String SumInsured = isNull(request.getParameter("InsurxedAmount"+ ListString + i));
					loopJ = i + 1;
					if (SNo.equalsIgnoreCase("")) {
						SNoEmpty.append(SPACE + loopJ);
					}
					if ("".equalsIgnoreCase(Item.trim())) {
						ItemEmpty.append(SPACE + loopJ);
					}
					if (SumInsured.equalsIgnoreCase("")) {
						SumInsuredEmpty.append(SPACE + loopJ);
					} else if (!validationFormat.isDigitValidationFormat(SumInsured)) {
						SumInsuredValid = SumInsuredValid + SPACE + loopJ;
					}

					if (!SumInsured.equalsIgnoreCase("")&& validationFormat.isDigitValidationFormat(SumInsured)) {
						final double sumval = Double.parseDouble(SumInsured);
						TotalSumInsured = TotalSumInsured + sumval;
					}
				}
				final double contentValue = Double.parseDouble(BAmount);
				if (!SNoValid.equalsIgnoreCase("")) {
					ErrorMsg.append(runner.getErrormsg("193"));
					ErrorMsg.append(SNoValid);
					ErrorMsg.append(',');
				}
				if (!SumInsuredValid.equalsIgnoreCase("")) {
					ErrorMsg.append(runner.getErrormsg("194"));
					ErrorMsg.append(SumInsuredValid);
					ErrorMsg.append(',');
				}
				if (SNoEmpty.length()>0) {
					ErrorMsg.append(runner.getErrormsg("195"));
					ErrorMsg.append(SNoEmpty.toString());
					ErrorMsg.append(',');
				}
				if (ItemEmpty.length()>0) {
					ErrorMsg.append(runner.getErrormsg("196"));
					ErrorMsg.append(ItemEmpty.toString());
					ErrorMsg.append(',');
				}
				if (SumInsuredEmpty.length()>0) {
					ErrorMsg.append(runner.getErrormsg("197"));
					ErrorMsg.append(SumInsuredEmpty.toString());
					ErrorMsg.append(',');
				}
				if (TotalSumInsured > contentValue) {
					AfterSumInsured = TotalSumInsured - contentValue;
					RemValue.append(runner.getErrormsg("199"));
					RemValue.append(AfterSumInsured);
					ErrorMsg.append(runner.getErrormsg("200"));
					ErrorMsg.append(contentValue);
					ErrorMsg.append(',');
				} else if (TotalSumInsured < contentValue) {
					AfterSumInsured = contentValue - TotalSumInsured;
					RemValue.append(runner.getErrormsg("201"));
					RemValue.append(AfterSumInsured);
				} else {
					RemValue.append(runner.getErrormsg("202"));
				}
				int countAdd = 0;
				if (session.getAttribute(INCREMENT) != null) {
					Incrementvalue = (String) session.getAttribute(INCREMENT);
					countAdd = Integer.parseInt(Incrementvalue);
					countAdd = countAdd + 1;
				}
				session.setAttribute(INCREMENT, Integer.toString(countAdd));
				if (ErrorMsg.length() > 0) {
					request.setAttribute(ERRORMSG, ErrorMsg);
					request.setAttribute(REM, RemValue);
					final RequestDispatcher dispatcher = request.getRequestDispatcher(PRESUM);
					dispatcher.forward(request, response);
				} else {
					request.setAttribute(REM, RemValue);
					final RequestDispatcher dispatcher = request.getRequestDispatcher(PRESUM);
					dispatcher.forward(request, response);
				}
			}
			if(path.equalsIgnoreCase("listeditemFinish")) {
				final String pid = (String) session.getAttribute(PRODUCTID);
				String AddItem = isNull(request.getParameter(ADDITEM));
				final String QuoteNo = isNull(request.getParameter(QUOTENO));
				request.setAttribute(ADDITEM, AddItem);
				final String[][] result = dataSelect.getCoverPremiumStatus(pid, "2");
				String excessPremium = "";
				if (result.length > 0) {
					excessPremium = isNull(result[0][0]);
				}

				if (AddItem.equalsIgnoreCase("1")) {
					final String AddedValue = isNull(request.getParameter(ADDVAL));
					final int CountCollection = Integer.parseInt(AddedValue);
					final Map financeDetails = getFinanceDetails(request,"Yes");
					final StringBuffer ErrorMsg = (StringBuffer)financeDetails.get(ERRORMSG);
					if (ErrorMsg.length() > 0) {
						request.setAttribute(INCREMENTVAL, Integer.toString(CountCollection));
						session.setAttribute(ADDITEM, AddItem);
						request.setAttribute(ERRORMSG, ErrorMsg);
						final RequestDispatcher dispatcher = request.getRequestDispatcher(PRESUM);
						dispatcher.forward(request, response);
					} else {
						request.setAttribute(INCREMENTVAL, Integer.toString(CountCollection+1));
						HashMap CoverageDetails = new HashMap();
						if (session.getAttribute(COVERAGES) != null) {
							CoverageDetails = (java.util.HashMap) session.getAttribute(COVERAGES);
							session.setAttribute(COVERAGES,CoverageDetails);
						}

						if (CoverageDetails.size() == 3) {
							AddItem = "7";
						} else {
							final int AddItemInt = Integer.parseInt(AddItem) + 2;
							AddItem = Integer.toString(AddItemInt);
							AddItem = (String) CoverageDetails.get(COVERAGES + AddItem);
						}
						financeDetails.put("totalFinance", Integer.toString(CountCollection));
						if("continue".equalsIgnoreCase((String)financeDetails.get("insertOption"))){
							DataManip.homeCoverageBuildingTransaction(QuoteNo,(HashMap)financeDetails);
						}
						request.setAttribute(ADDITEM, AddItem);
						final RequestDispatcher dispatcher = request.getRequestDispatcher(PRESUM);
						dispatcher.forward(request, response);
					}
				} else if (AddItem.equalsIgnoreCase("2") || AddItem.equalsIgnoreCase("3")
						|| AddItem.equalsIgnoreCase("3")|| AddItem.equalsIgnoreCase("4")
						|| AddItem.equalsIgnoreCase("5")|| AddItem.equalsIgnoreCase("6"))//need
				{

					final StringBuffer ErrorMsg = new StringBuffer();
					final StringBuffer RemValue = new StringBuffer();
					final String AddedValue = isNull(request.getParameter("AddedValue"));
					final String BAmount = isNull(request.getParameter("BAmount"));
					final String ListString = (String) session.getAttribute("ListString");
					String SumInsuredValid = "";
					double AfterSumInsured;

					final HashMap SumDetails = new HashMap();

					// For Nakheel Shecme
					final HashMap hashPre = new HashMap();
					boolean IndPreStatus = false;
					int indPreLoop = 0;

					final String increasee = isNull(request.getParameter("increase"));

					int loopJ;
					final int CountCollection = Integer.parseInt(AddedValue);
					double TotalSumInsured = 0.0;
					final double contentValue = Double.parseDouble(BAmount);

					for (int i = 0; i < CountCollection; i++) {
						String SNo = isNull(request.getParameter(SNO + ListString+ i));
						String Item = isNull(request.getParameter("Description"	+ ListString + i));
						final String SumInsured = isNull(request.getParameter("InsurxedAmount" + ListString + i));
						SNo = SNo.replaceAll("'", "''");
						Item = Item.replaceAll("'", "''");

						loopJ = i + 1;
						if ((SNo.equalsIgnoreCase("") || SNo.equalsIgnoreCase(SELECT))
								&& (!"".equalsIgnoreCase(Item.trim()) || !SumInsured.equalsIgnoreCase(""))) {
							ErrorMsg.append(runner.getErrormsg("250") + " "	+ (i + 1));
							ErrorMsg.append(',');
						} else {
							SumDetails.put(SNO + i, SNo);

						}
						if ("".equalsIgnoreCase(Item.trim())
								&& (!SNo.equalsIgnoreCase(SELECT) || !SumInsured.equalsIgnoreCase(""))) {
							ErrorMsg.append(runner.getErrormsg("251") + " "+ (i + 1));
							ErrorMsg.append(',');
						} else {
							SumDetails.put("Item" + i, Item);
						}
						if (SumInsured.equalsIgnoreCase("0")&& (!SNo.equalsIgnoreCase(SELECT) || !""
										.equalsIgnoreCase(Item.trim()))) {
							ErrorMsg.append(runner.getErrormsg("252") + " "	+ (i + 1));
							ErrorMsg.append(',');
						} else if (validationFormat.isDigitValidationFormat(SumInsured)) {
							SumDetails.put("SumInsured" + i, SumInsured);
						} else {
							SumInsuredValid = SumInsuredValid + SPACE + loopJ;
						}

						if (!SumInsured.equalsIgnoreCase("")&&validationFormat.isDigitValidationFormat(SumInsured)) {
							final double sumval = Double.parseDouble(SumInsured);
							TotalSumInsured = TotalSumInsured + sumval;
							if ("Y".equalsIgnoreCase(excessPremium)) {
								if (sumval > (contentValue / 3)) {
									ErrorMsg.append("The Total SumInsured Should 1/3 of Content Value "
													+ contentValue
													+ " Sum Insured " + (i + 1));
									ErrorMsg.append(',');
								} else {
									final double tempInd = dataSelect.getAdditionPremium(SumInsured,pid, AddItem);
									hashPre.put(SNO + indPreLoop, Integer.toString(i));
									hashPre.put("IndPre" + indPreLoop, Double.toString(tempInd));
									IndPreStatus = true;
									indPreLoop++;
								}

							}
						}
					}
					hashPre.put("IndPreSize", Integer.toString(indPreLoop));
					if (!SumInsuredValid.equalsIgnoreCase("")) {
						ErrorMsg.append(runner.getErrormsg("208"));
						ErrorMsg.append(SumInsuredValid);
						ErrorMsg.append(',');
					}
					if (TotalSumInsured > contentValue) {
						AfterSumInsured = TotalSumInsured - contentValue;
						RemValue.append(runner.getErrormsg("210"));
						RemValue.append(AfterSumInsured);
						ErrorMsg.append(runner.getErrormsg("211"));
						ErrorMsg.append(contentValue);
						ErrorMsg.append(',');
					}
					if (TotalSumInsured < contentValue) {
						AfterSumInsured = contentValue - TotalSumInsured;
						RemValue.append(runner.getErrormsg("212"));
						RemValue.append(AfterSumInsured);
					}
					String excess1 = "";
					excess1 = isNull(request.getParameter(EXCESS));
					if (ErrorMsg.length() > 0 && !excess1.equalsIgnoreCase("N")) {
						if (excess1.equalsIgnoreCase("Y")) {
							request.setAttribute("addCons", "b2c");
						}
						int countAdd = 0;
						if (session.getAttribute(INCREMENT) != null) {
							final String Incrementvalue = (String) session.getAttribute(INCREMENT);
							countAdd = Integer.parseInt(Incrementvalue);
							countAdd = countAdd + 1;
						}
						session.setAttribute(INCREMENT, Integer.toString(countAdd));
						request.setAttribute("increasee", increasee);
						request.setAttribute(ERRORMSG, ErrorMsg);
						request.setAttribute(REM, RemValue);
						final RequestDispatcher dispatcher = request.getRequestDispatcher(PRESUM);
						dispatcher.forward(request, response);
					} else {
						HashMap CoverageDetails = new HashMap();
						if (session.getAttribute(COVERAGES) != null) {
							CoverageDetails = (java.util.HashMap) session.getAttribute(COVERAGES);
							session.setAttribute(COVERAGES,CoverageDetails);
						}
						// /////////////////Passing The Cover Details End
						String Temp = "";
						String SelectChoice = "";

						for (int i = 0; i < CoverageDetails.size(); i = i + 3) {
							Temp = (String) CoverageDetails.get(COVERAGES + i);
							if (AddItem.equalsIgnoreCase(Temp)) {
								if (AddItem.equalsIgnoreCase("3")){
									SelectChoice = (String) CoverageDetails.get(COVERAGES + (i + 6));}
								else{
									SelectChoice = (String) CoverageDetails.get(COVERAGES + (i + 3));}
							}
						}

						if (SelectChoice == null) {
							String excess = "";
							excess = isNull(request.getParameter(EXCESS));
							request.setAttribute(ADDITEM, "7");
							if ("N".equalsIgnoreCase(excess)) {
								DataManip.homeCoverageTotalTransactionDelete(QuoteNo, AddItem);
							} else {
								DataManip.homeCoverageTotalTransaction(QuoteNo,AddItem, SumDetails);
								if (IndPreStatus) {
									DataManip.IndividualPremiumUpdate(QuoteNo,AddItem, hashPre);
								} else if ("Y".equalsIgnoreCase(excessPremium)) {
									DataManip.IndividualPremiumUpdate(QuoteNo,AddItem);
								}
							}
							
						} else {
							String excess = "";
							excess = isNull(request.getParameter(EXCESS));

							if ("N".equalsIgnoreCase(excess)) {
								DataManip.homeCoverageTotalTransactionDelete(QuoteNo, AddItem);
							} else {
								DataManip.homeCoverageTotalTransaction(QuoteNo,AddItem, SumDetails);
								if (IndPreStatus) {
									DataManip.IndividualPremiumUpdate(QuoteNo,AddItem, hashPre);
								} else if ("Y".equalsIgnoreCase(excessPremium))
									DataManip.IndividualPremiumUpdate(QuoteNo,AddItem);
							}
							request.setAttribute(ADDITEM, SelectChoice);
							/*final RequestDispatcher dispatcher = request.getRequestDispatcher(PRESUM);
							dispatcher.forward(request, response);*/
						}
						//single limit validation 
						Map refStatus;refStatus = dataSelect.isSingleArtReferral(QuoteNo,AddItem);
						if(!refStatus.isEmpty()&&((String)refStatus.get("Status")).equalsIgnoreCase("Yes")){
							session.setAttribute("Single"+QuoteNo+"Cover"+AddItem,(String)refStatus.get("Limit"));
							dataSelect.updateReferalStatus(QuoteNo,pid,"Single Article Suminsured Referral");
							
						}else if(session.getAttribute("Single"+QuoteNo+"Cover"+AddItem)!=null){
							session.removeAttribute("Single"+QuoteNo+"Cover"+AddItem);
						}
						//
						final RequestDispatcher dispatcher = request.getRequestDispatcher(PRESUM);
						dispatcher.forward(request, response);

					}
				} else if (AddItem.equalsIgnoreCase("7")) {
					dataSelect.setQuoteNo(QuoteNo);
					final String homebuilt = isNull(request.getParameter("r1"));
					final String pvtacmation = isNull(request.getParameter("r2"));
					final String rclimeland = isNull(request.getParameter("r3"));
					final String consdays = isNull(request.getParameter("r4"));
					final String conditions = isNull(request.getParameter("r5"));
					final String pstthryrs = isNull(request.getParameter("r6"));
					final String claim = isNull(request.getParameter("t1"));
					final String claimamt = isNull(request.getParameter("t2"));
					final String property = isNull(request.getParameter("property"));

					String proId = isNull(request.getParameter(PROIDS));
					if ("".equals(proId) || proId.length() <= 0){
						proId = (String) session.getAttribute(PRODUCTID);}
					if ("".equals(proId) || proId.length() <= 0){
						proId = (String) session.getAttribute("productTypeId");}

					final int domestic_staf_no = Integer.parseInt(isNull(request.getParameter("domesticStaffNo"),"0"));
					final String mode = isNull(request.getParameter("s1"));
					String name[] = new String[domestic_staf_no];
					String dob[] = new String[domestic_staf_no];
					String mon[] = new String[domestic_staf_no];
					String year[] = new String[domestic_staf_no];
					if ("1".equalsIgnoreCase(mode)) {
						for (int d = 0; d < domestic_staf_no; d++) {
							name[d] = isNull(request.getParameter("name" + d));
							dob[d] = isNull(request.getParameter(DOBDAY + d));
							mon[d] = isNull(request.getParameter(DOBMONTH + d));
							year[d] = isNull(request.getParameter(DOBYEAR + d));
						}
					}
					final String[][] clm123 = dataSelect.getClaimData(proId);
					String claimiss = "";
					for (int i = 0; i < clm123.length; i++) {
						if (isNull(request.getParameter(CLAIM + i)).length() > 0){
							claimiss = claimiss+ ","+ isNull(request.getParameter(CLAIM + i));}
					}
					if (claimiss.length() > 0) {
						claimiss = claimiss.substring(1, claimiss.length());
					}
					dataSelect.setHomebuilt(homebuilt);
					dataSelect.setPvtacmation(pvtacmation);
					dataSelect.setRclimeland(rclimeland);
					dataSelect.setConsdays(consdays);
					dataSelect.setConditions(conditions);
					dataSelect.setPastthryrs(pstthryrs);
					dataSelect.setClaim(claim);
					dataSelect.setClaimtype(claimiss);
					dataSelect.setClaimamt(claimamt);
					dataSelect.setProperty(property);

					if ("1".equalsIgnoreCase(mode)) {
						dataSelect.setStaffSize(domestic_staf_no);
						dataSelect.setName(name);
						dataSelect.setDob(dob);
						dataSelect.setMon(mon);
						dataSelect.setYear(year);
					}
					int dob1, mon1, yea1;
					RequestDispatcher buildFwd = null;
					final StringBuffer errmsg = (StringBuffer) dataSelect.doValidations();
					String errmsg1 = "";
					if ("1".equalsIgnoreCase(mode)&&errmsg.length() == 0) {
							for (int d = 0; d < domestic_staf_no; d++) {
								dob1 = Integer.parseInt(dob[d]);
								mon1 = Integer.parseInt(mon[d]);
								yea1 = Integer.parseInt(year[d]);
								errmsg1 = dataCus.validateDateOfBirth(yea1,mon1, dob1);
								if (errmsg1.length() > 0) {
									errmsg.append(errmsg1);
									errmsg.append(" in row ");errmsg.append(Integer.toString((d+1)));
									errmsg.append(',');
								}
							}
					}
					if (errmsg.length() > 0) {
						request.setAttribute(ERRORMSG, errmsg);
						request.setAttribute(ADDITEM, "7");
						request.setAttribute("claimiss", claimiss);
						buildFwd = request.getRequestDispatcher(PRESUM);
						buildFwd.forward(request, response);
					} else {
						dataSelect.getUWData();
						final StringBuffer refMsgBuf = new StringBuffer();
						final StringBuffer referralName = new StringBuffer(1000);
						if (homebuilt.equalsIgnoreCase("N")	&& pvtacmation.equalsIgnoreCase("N")
								&& rclimeland.equalsIgnoreCase("Y")	&& consdays.equalsIgnoreCase("Y")
								&& conditions.equalsIgnoreCase("Y")&& pstthryrs.equalsIgnoreCase("Y")) {
							refMsgBuf.append("*1*2*3*4*5*6");
						} else {
							if (homebuilt.equalsIgnoreCase("N")) {
								refMsgBuf.append("*1");
							}
							if (pvtacmation.equalsIgnoreCase("N")) {
								refMsgBuf.append("*2");
							}
							if (rclimeland.equalsIgnoreCase("Y")) {
								refMsgBuf.append("*3");
							}
							if (consdays.equalsIgnoreCase("Y")) {
								refMsgBuf.append("*4");
							}
							if (conditions.equalsIgnoreCase("Y")) {
								refMsgBuf.append("*5");
							}
							if (pstthryrs.equalsIgnoreCase("Y")) {
								refMsgBuf.append("*6");
							}
						}
						StringTokenizer stRef = null;
						final String refMsg = refMsgBuf.toString();
						final String refMaster[][] = dataSelect.getRefrralDetails(proId, branch);
						for (int r = 0; r < refMaster.length; r++) {
							stRef = new StringTokenizer(refMsg, "*");
							while (stRef.hasMoreTokens()) {
								final String temp = stRef.nextToken();
								if (refMaster[r][0].equalsIgnoreCase(temp)) {
									referralName.append("* "+ refMaster[r][1]);
								}
							}
						}
						if (session.getAttribute(BUILDING + QuoteNo) != null){
							referralName.append("* Building SumInsured Referral");}
						if (session.getAttribute(CONTENT + QuoteNo) != null){
							referralName.append("* Contents SumInsured Referral");}
						if (session.getAttribute(PERSONAL + QuoteNo) != null){
							referralName.append("* Personal Belongings SumInsured Referral");}
						if(session.getAttribute("Single"+QuoteNo+"Cover2")!=null||session.getAttribute("Single"+QuoteNo+"Cover3")!=null){
							referralName.append("* Single Article Suminsured Referral");
						}
						if ("Y".equalsIgnoreCase(excessPremium)){
							DataManip.IndividualPremiumUpdatePos(QuoteNo);}
						if (referralName.length() > 0) {
							request.setAttribute(QUOTENO, QuoteNo);
							request.setAttribute(PROIDS, proId);
							session.setAttribute("referralName", referralName.toString());
							dataSelect.updateReferalStatus(QuoteNo, proId,referralName.toString());
							final RequestDispatcher mailDispatch = request.getRequestDispatcher("/HomeMailController?reqFrom=Referral");
							if (mailDispatch != null){
								mailDispatch.include(request, response);}
							response.sendRedirect("../HomeInsurance/Home_Referral_ShowQuote.jsp");
						} else {
							dataSelect.updateReferalStatusIfNot(QuoteNo, proId);
							buildFwd = request.getRequestDispatcher("../HomeInsurance/summary.jsp");
						}
						if (buildFwd != null){
							buildFwd.forward(request, response);}
					}

				}
				else if (AddItem.equalsIgnoreCase("8")) {
					final StringBuffer ErrorMsg = new StringBuffer();
					final HashMap CollectionValue = new HashMap();
					final String BuildingsCheck = isNull(request.getParameter("BuildingsCheck"));
					final String ContentsCheck = isNull(request.getParameter("ContentsCheck"));
					final String SpecifiedCheck = isNull(request.getParameter("SpecifiedCheck"));
					final String SportEquiCheck = isNull(request.getParameter("SportsEquipmentsCheck"));
					final String PedalCyclesCheck = isNull(request.getParameter("PedalCyclesCheck"));
					final String LaptopsCheck = isNull(request.getParameter("LaptopsCheck"));

					int count = 0;
					if (BuildingsCheck.equalsIgnoreCase("Y")) {
						final String Building = isNull(request.getParameter(BUILDING));
						final String BuildingsArea = isNull(request.getParameter("BuildingsArea"));
						CollectionValue.put(COVERID + count, "1");
						if (Building.equalsIgnoreCase("")) {
							ErrorMsg.append(runner.getErrormsg("173"));
							ErrorMsg.append(',');
						} else if (validationFormat.isDigitValidationFormat(Building)) {
							CollectionValue.put(CLAIMAMOUNT + count, Building);
						} else {
							ErrorMsg.append(runner.getErrormsg("174"));
							ErrorMsg.append(',');
						}
						if (BuildingsArea.equalsIgnoreCase("")) {
							ErrorMsg.append(runner.getErrormsg("175"));
							ErrorMsg.append(',');
						} else {
							CollectionValue.put(CLAIMDETAILS + count,BuildingsArea);
						}
						count = count + 1;
					}
					if (ContentsCheck.equalsIgnoreCase("Y")) {
						final String Contents = isNull(request.getParameter(CONTENT));
						final String ContentsArea = isNull(request.getParameter("ContentsArea"));
						CollectionValue.put(COVERID + count, "2");
						if (Contents.equalsIgnoreCase("")) {
							ErrorMsg.append(runner.getErrormsg("176"));
							ErrorMsg.append(',');
						} else if (validationFormat.isDigitValidationFormat(Contents)) {
							CollectionValue.put(CLAIMAMOUNT + count, Contents);
						} else {
							ErrorMsg.append(runner.getErrormsg("177"));
							ErrorMsg.append(',');
						}
						if (ContentsArea.equalsIgnoreCase("")) {
							ErrorMsg.append(runner.getErrormsg("178"));
							ErrorMsg.append(',');
						} else {
							CollectionValue.put(CLAIMDETAILS + count,ContentsArea);

						}
						count = count + 1;
					}
					if (SpecifiedCheck.equalsIgnoreCase("Y")) {
						final String Specified = isNull(request.getParameter("Specified"));
						final String SpecifiedArea = isNull(request.getParameter("SpecifiedArea"));
						CollectionValue.put(COVERID + count, "3");
						if (Specified.equalsIgnoreCase("")) {
							ErrorMsg.append(runner.getErrormsg("179"));
							ErrorMsg.append(',');
						} else if (validationFormat.isDigitValidationFormat(Specified)) {
							CollectionValue.put(CLAIMAMOUNT + count,Specified);
						} else {
							ErrorMsg.append(runner.getErrormsg("180"));
							ErrorMsg.append(',');
						}
						if (SpecifiedArea.equalsIgnoreCase("")) {
							ErrorMsg.append(runner.getErrormsg("181"));
							ErrorMsg.append(',');
						} else {
							CollectionValue.put(CLAIMDETAILS + count,	SpecifiedArea);
						}
						count = count + 1;
					}
					if (SportEquiCheck.equalsIgnoreCase("Y")) {
						final String SportsEquipments = isNull(request.getParameter("SportsEquipments"));
						final String SportsEquiArea = isNull(request.getParameter("SportsEquipmentsArea"));
						CollectionValue.put(COVERID + count, "5");
						if (SportsEquipments.equalsIgnoreCase("")) {
							ErrorMsg.append(runner.getErrormsg("182"));
							ErrorMsg.append(',');
						} else if (validationFormat.isDigitValidationFormat(SportsEquipments)) {
							CollectionValue.put(CLAIMAMOUNT + count,SportsEquipments);
						} else {
							ErrorMsg.append(runner.getErrormsg("183"));
							ErrorMsg.append(',');
						}
						if (SportsEquiArea.equalsIgnoreCase("")) {
							ErrorMsg.append(runner.getErrormsg("184"));
							ErrorMsg.append(',');
						} else {
							CollectionValue.put(CLAIMDETAILS + count,	SportsEquiArea);
						}
						count = count + 1;
					}

					if (PedalCyclesCheck.equalsIgnoreCase("Y")) {
						final String PedalCycles = isNull(request.getParameter("PedalCycles"));
						final String PedalCyclesArea = isNull(request.getParameter("PedalCyclesArea"));
						CollectionValue.put(COVERID + count, "6");
						if (PedalCycles.equalsIgnoreCase("")) {
							ErrorMsg.append(runner.getErrormsg("185"));
							ErrorMsg.append(',');
						} else if (validationFormat.isDigitValidationFormat(PedalCycles)) {
							CollectionValue.put(CLAIMAMOUNT + count,PedalCycles);
						} else {
							ErrorMsg.append(runner.getErrormsg("186"));
							ErrorMsg.append(',');
						}
						if (PedalCyclesArea.equalsIgnoreCase("")) {
							ErrorMsg.append(runner.getErrormsg("187"));
							ErrorMsg.append(',');
						} else {
							CollectionValue.put(CLAIMDETAILS + count,	PedalCyclesArea);
						}
						count = count + 1;
					}

					if (LaptopsCheck.equalsIgnoreCase("Y")) {
						final String Laptops = isNull(request.getParameter("Laptops"));
						final String LaptopsArea = isNull(request.getParameter("LaptopsArea"));
						CollectionValue.put(COVERID + count, "7");
						if (Laptops.equalsIgnoreCase("")) {
							ErrorMsg.append(runner.getErrormsg("188"));
							ErrorMsg.append(',');
						} else if (validationFormat.isDigitValidationFormat(Laptops)) {
							CollectionValue.put(CLAIMAMOUNT + count, Laptops);
						} else {
							ErrorMsg.append(runner.getErrormsg("189"));
							ErrorMsg.append(',');
						}
						if (LaptopsArea.equalsIgnoreCase("")) {
							ErrorMsg.append(runner.getErrormsg("190"));
							ErrorMsg.append(',');
						} else {
							CollectionValue.put(CLAIMDETAILS + count,	LaptopsArea);
						}
						count = count + 1;

					}
					if (ErrorMsg.length() > 0) {
						AddItem = "8";
						request.setAttribute(ADDITEM, AddItem);
						request.setAttribute(ERRORMSG, ErrorMsg);
						final RequestDispatcher dispatcher = request.getRequestDispatcher("/HomeInsurance/listeditem.jsp");
						dispatcher.forward(request, response);
					} else {
						final RequestDispatcher dispatcher = request.getRequestDispatcher("/HomeInsurance/summary.jsp");
						dispatcher.forward(request, response);
					}

				}
			}
			if (path.equalsIgnoreCase("listeditemSave")) {
				final String pid = (String) session.getAttribute(PRODUCTID);
				String AddItem = isNull(request.getParameter(ADDITEM));
				final String QuoteNo = isNull(request.getParameter(QUOTENO));
				if (AddItem.equalsIgnoreCase("1")) {
					final String AddedValue = isNull(request.getParameter(ADDVAL));
					final int CountCollection = Integer.parseInt(AddedValue);
					final Map financeDetails = getFinanceDetails(request,"No");
					final StringBuffer ErrorMsg =  (StringBuffer)financeDetails.get(ERRORMSG);
					if (ErrorMsg.length() > 0) {
						request.setAttribute(INCREMENTVAL, Integer.toString(CountCollection));
						session.setAttribute(ADDITEM, AddItem);
						request.setAttribute(ERRORMSG, ErrorMsg);
						final RequestDispatcher dispatcher = request.getRequestDispatcher(PRESUM);
						dispatcher.forward(request, response);
					} else {
						ErrorMsg.append(runner.getErrormsg("217"));
						ErrorMsg.append(',');
						request.setAttribute(ERRORMSG, ErrorMsg);
						request.setAttribute(INCREMENTVAL, Integer.toString(CountCollection));
						financeDetails.put("totalFinance", Integer.toString(CountCollection));
						if("continue".equalsIgnoreCase((String)financeDetails.get("insertOption"))){
							DataManip.homeCoverageBuildingTransaction(QuoteNo,(HashMap)financeDetails);
						}
						session.setAttribute(ADDITEM, AddItem);
						final RequestDispatcher dispatcher = request.getRequestDispatcher(PRESUM);
						dispatcher.forward(request, response);

					}
				} else if (AddItem.equalsIgnoreCase("2")|| AddItem.equalsIgnoreCase("3")|| AddItem.equalsIgnoreCase("3")
						|| AddItem.equalsIgnoreCase("4")|| AddItem.equalsIgnoreCase("5")|| AddItem.equalsIgnoreCase("6")) {
					final String[][] result = dataSelect.getCoverPremiumStatus(pid,AddItem);
					String excessPremium = "";
					if (result.length > 0) {
						excessPremium = isNull(result[0][0]);
					}

					final StringBuffer ErrorMsg = new StringBuffer();
					final StringBuffer RemValue = new StringBuffer();
					final String AddedValue = isNull(request.getParameter("AddedValue"));
					final String BAmount = isNull(request.getParameter("BAmount"));
					final String ListString = (String) session.getAttribute("ListString");
					final StringBuffer ItemEmpty = new StringBuffer();
					final StringBuffer SumInsuredEmpty = new StringBuffer();
					String SumInsuredValid = "";
					double AfterSumInsured = 0.0;

					final HashMap SumDetails = new HashMap();

					// For Nakheel Shecme
					final HashMap hashPre = new HashMap();
					boolean IndPreStatus = false;
					int indPreLoop = 0;

					int loopJ;
					final int CountCollection = Integer.parseInt(AddedValue);
					double TotalSumInsured = 0.0;
					final double contentValue = Double.parseDouble(BAmount);

					for (int i = 0; i < CountCollection; i++) {
						final String SNo = isNull(request.getParameter(SNO + ListString	+ i));
						final String Item = isNull(request.getParameter("Description"+ ListString + i));
						final String SumInsured = isNull(request.getParameter("InsurxedAmount" + ListString + i));
						loopJ = i + 1;
						if ((SNo.equalsIgnoreCase("") || SNo.equalsIgnoreCase(SELECT))
								&& !"".equalsIgnoreCase(Item.trim())&& !SumInsured.equalsIgnoreCase("")) {
							ErrorMsg.append(runner.getErrormsg("250") + (i + 1));
							ErrorMsg.append(',');
						}
						else {
							SumDetails.put(SNO + i, SNo);
						}
						if ("".equalsIgnoreCase(Item.trim())) {
							ItemEmpty.append(SPACE + loopJ);
						} else {
							SumDetails.put("Item" + i, Item);

						}
						if (SumInsured.equalsIgnoreCase("")) {
							SumInsuredEmpty.append(SPACE+ loopJ);
						} else if (validationFormat.isDigitValidationFormat(SumInsured)) {
							SumDetails.put("SumInsured" + i, SumInsured);
						} else {
							SumInsuredValid = SumInsuredValid + SPACE + loopJ;
						}
						if (!SumInsured.equalsIgnoreCase("")&&validationFormat.isDigitValidationFormat(SumInsured)) {
							final double sumval = Double.parseDouble(SumInsured);
							TotalSumInsured = TotalSumInsured + sumval;
							// if(pid.equalsIgnoreCase("60")||pid.equalsIgnoreCase("59"))
							if ("Y".equalsIgnoreCase(excessPremium)) {
								if (sumval > (contentValue / 3)) {
									ErrorMsg.append("The Total SumInsured Should 1/3 of Content Value "+ contentValue+ " Sum Insured " + (i + 1));
									ErrorMsg.append(',');
								} else {
									final double tempInd = dataSelect.getAdditionPremium(SumInsured,pid, AddItem);
									hashPre.put(SNO + indPreLoop, Integer.toString(i));
									hashPre.put("IndPre" + indPreLoop, Double.toString(tempInd));
									IndPreStatus = true;
									indPreLoop++;
								}
							}
						}
					}
					hashPre.put("IndPreSize", Integer.toString(indPreLoop));
					if (!SumInsuredValid.equalsIgnoreCase("")) {
						ErrorMsg.append(runner.getErrormsg("218"));
						ErrorMsg.append(SumInsuredValid);
						ErrorMsg.append(',');
					}
					if (TotalSumInsured > contentValue) {
						AfterSumInsured = TotalSumInsured - contentValue;
						RemValue.append(runner.getErrormsg("210"));
						RemValue.append(AfterSumInsured);
						ErrorMsg.append(runner.getErrormsg("211"));
						ErrorMsg.append(contentValue);
						ErrorMsg.append(',');
					}
					if (ErrorMsg.length() > 0) {
						request.setAttribute("saveFrom", "2");
						request.setAttribute(ERRORMSG, ErrorMsg);
						request.setAttribute(REM, RemValue);
						final RequestDispatcher dispatcher = request.getRequestDispatcher(PRESUM);
						dispatcher.forward(request, response);
					} else {
						ErrorMsg.append(runner.getErrormsg("217"));
						ErrorMsg.append(',');
						request.setAttribute("saveFrom", "2");
						request.setAttribute(ERRORMSG, ErrorMsg);
						request.setAttribute(REM, RemValue);
						DataManip.homeCoverageTotalTransaction(QuoteNo,	AddItem, SumDetails);
						// for Nakheel Scheme...
						if (IndPreStatus) {
							DataManip.IndividualPremiumUpdate(QuoteNo, AddItem,	hashPre);
						} else if ("Y".equalsIgnoreCase(excessPremium))
							DataManip.IndividualPremiumUpdate(QuoteNo, AddItem);
							final RequestDispatcher dispatcher = request.getRequestDispatcher(PRESUM);
							dispatcher.forward(request, response);
						}
				} else if (AddItem.equalsIgnoreCase("7")) {

					String homebuilt = "";
					String pvtacmation = "";
					String rclimeland = "";
					String consdays = "";
					String conditions = "";
					String pstthryrs = "";
					String claim = "";
					String claimamt = "";
					String property = "";
					String proId = "";
					dataSelect.setQuoteNo(QuoteNo);
					homebuilt = isNull(request.getParameter("r1"));
					pvtacmation = isNull(request.getParameter("r2"));
					rclimeland = isNull(request.getParameter("r3"));
					consdays = isNull(request.getParameter("r4"));
					conditions = isNull(request.getParameter("r5"));
					pstthryrs = isNull(request.getParameter("r6"));
					claim = isNull(request.getParameter("t1"));
					claimamt = isNull(request.getParameter("t2"));
					property = isNull(request.getParameter("property"));
					proId = isNull(request.getParameter(PROIDS));
					if ("".equals(proId) || proId.length() <= 0){
						proId = (String) session.getAttribute(PRODUCTID);}
					if ("".equals(proId) || proId.length() <= 0){
						proId = (String) session.getAttribute("productTypeId");}
					final String[][] clm123 = dataSelect.getClaimData(proId);
					String claimiss = "";
					for (int i = 0; i < clm123.length; i++) {
						if (isNull(request.getParameter(CLAIM + i)).length() > 0){
							claimiss = claimiss	+ "," + isNull(request.getParameter(CLAIM + i));}

					}
					if (claimiss.length() > 0) {
						claimiss = claimiss.substring(1, claimiss.length());
					}
					dataSelect.setHomebuilt(homebuilt);
					dataSelect.setPvtacmation(pvtacmation);
					dataSelect.setRclimeland(rclimeland);
					dataSelect.setConsdays(consdays);
					dataSelect.setConditions(conditions);
					dataSelect.setPastthryrs(pstthryrs);
					dataSelect.setClaim(claim);
					dataSelect.setClaimtype(claimiss);
					dataSelect.setClaimamt(claimamt);
					dataSelect.setProperty(property);

					StringBuffer errmsg = new StringBuffer();

					final int domestic_staf_no = Integer.parseInt(isNull(request.getParameter("domesticStaffNo"),"0"));
					final String mode = isNull(request.getParameter("s1"));
					String name[] = new String[domestic_staf_no];
					String dob[] = new String[domestic_staf_no];
					String mon[] = new String[domestic_staf_no];
					String year[] = new String[domestic_staf_no];
					if ("1".equalsIgnoreCase(mode)) {
						for (int d = 0; d < domestic_staf_no; d++) {
							name[d] = isNull(request.getParameter("name" + d));
							dob[d] = isNull(request.getParameter(DOBDAY + d));
							mon[d] = isNull(request.getParameter(DOBMONTH + d));
							year[d] = isNull(request.getParameter(DOBYEAR + d));
						}
					}
					if ("1".equalsIgnoreCase(mode)) {
						dataSelect.setStaffSize(domestic_staf_no);
						dataSelect.setName(name);
						dataSelect.setDob(dob);
						dataSelect.setMon(mon);
						dataSelect.setYear(year);
					}
					int dob1, mon1, yea1;
					RequestDispatcher underFwd = null;
					if ("1".equalsIgnoreCase(mode)) {
						errmsg = (StringBuffer) dataSelect.doValidationsForSave();
					}
					String errmsg1 = "";
					if ("1".equalsIgnoreCase(mode)&&errmsg.length() == 0) {
							for (int d = 0; d < domestic_staf_no; d++) {
								dob1 = Integer.parseInt(dob[d]);
								mon1 = Integer.parseInt(mon[d]);
								yea1 = Integer.parseInt(year[d]);
								errmsg1 = dataCus.validateDateOfBirth(yea1,mon1, dob1);
								if (errmsg1.length() > 0) {
									errmsg.append(errmsg1);
									errmsg.append(',');
								}
							}
					}
					if (errmsg.length() > 0) {
						request.setAttribute(ERRORMSG, errmsg);
						request.setAttribute(ADDITEM, "7");
						request.setAttribute("claimiss", claimiss);
						underFwd = request.getRequestDispatcher(PRESUM);
						underFwd.forward(request, response);
					} else {
						final int resStatus = (int) dataSelect.getUWData();
						if (resStatus > 0) {
							final StringBuffer resBuf = new StringBuffer(1000);
							resBuf.append(runner.getErrormsg("217"));
							request.setAttribute(ERRORMSG, resBuf);
							underFwd = request.getRequestDispatcher(PRESUM);
							underFwd.forward(request, response);
						} else {
							final StringBuffer resBuf = new StringBuffer(1000);
							resBuf.append("Record Not Inserted ");
							request.setAttribute(ERRORMSG, resBuf);
							underFwd = request.getRequestDispatcher(PRESUM);
							underFwd.forward(request, response);
						}
					}

				}

				else if (AddItem.equalsIgnoreCase("8")) {
					final StringBuffer ErrorMsg = new StringBuffer();
					final HashMap CollectionValue = new HashMap();
					final String BuildingsCheck = isNull(request.getParameter("BuildingsCheck"));
					final String ContentsCheck = isNull(request.getParameter("ContentsCheck"));
					final String SpecifiedCheck = isNull(request.getParameter("SpecifiedCheck"));
					final String SportEquiCheck = isNull(request.getParameter("SportsEquipmentsCheck"));
					final String PedalCyclesCheck = isNull(request.getParameter("PedalCyclesCheck"));
					final String LaptopsCheck = isNull(request.getParameter("LaptopsCheck"));

					int count = 0;
					if (BuildingsCheck.equalsIgnoreCase("Y")) {
						final String Building = isNull(request.getParameter(BUILDING));
						final String BuildingsArea = isNull(request.getParameter("BuildingsArea"));
						CollectionValue.put(COVERID + count, "1");
						if (Building.equalsIgnoreCase("")) {
							ErrorMsg.append(runner.getErrormsg("219"));
							ErrorMsg.append(',');
						} else if (validationFormat.isDigitValidationFormat(Building)) {
							CollectionValue.put(CLAIMAMOUNT + count, Building);
						} else {
							ErrorMsg.append(runner.getErrormsg("220"));
							ErrorMsg.append(',');
						}
						if (BuildingsArea.equalsIgnoreCase("")) {
							ErrorMsg.append(runner.getErrormsg("221"));
							ErrorMsg.append(',');
						} else {
							CollectionValue.put(CLAIMDETAILS + count,BuildingsArea);
						}
						count = count + 1;
					}

					if (ContentsCheck.equalsIgnoreCase("Y")) {
						final String Contents = isNull(request.getParameter(CONTENT));
						final String ContentsArea = isNull(request.getParameter("ContentsArea"));
						CollectionValue.put(COVERID + count, "2");
						if (Contents.equalsIgnoreCase("")) {
							ErrorMsg.append(runner.getErrormsg("222"));
							ErrorMsg.append(',');
						} else if (validationFormat.isDigitValidationFormat(Contents)) {
							CollectionValue.put(CLAIMAMOUNT + count, Contents);
						} else {
							ErrorMsg.append(runner.getErrormsg("223"));
							ErrorMsg.append(',');
						}
						if (ContentsArea.equalsIgnoreCase("")) {
							ErrorMsg.append(runner.getErrormsg("224"));
							ErrorMsg.append(',');
						} else {
							CollectionValue.put(CLAIMDETAILS + count,	ContentsArea);
						}
						count = count + 1;
					}

					if (SpecifiedCheck.equalsIgnoreCase("Y")) {
						final String Specified = isNull(request.getParameter("Specified"));
						final String SpecifiedArea = isNull(request.getParameter("SpecifiedArea"));
						CollectionValue.put(COVERID + count, "3");
						if (Specified.equalsIgnoreCase("")) {
							ErrorMsg.append(runner.getErrormsg("225"));
							ErrorMsg.append(',');
						} else if (validationFormat.isDigitValidationFormat(Specified)) {
							CollectionValue.put(CLAIMAMOUNT + count,Specified);
						} else {
							ErrorMsg.append(runner.getErrormsg("226"));
							ErrorMsg.append(',');
						}
						if (SpecifiedArea.equalsIgnoreCase("")) {
							ErrorMsg.append(runner.getErrormsg("181"));
							ErrorMsg.append(',');
						} else {
							CollectionValue.put(CLAIMDETAILS + count,SpecifiedArea);
						}
						count = count + 1;

					}

					if (SportEquiCheck.equalsIgnoreCase("Y")) {
						final String SportsEquipments = isNull(request.getParameter("SportsEquipments"));
						final String SportsEquiArea = isNull(request.getParameter("SportsEquipmentsArea"));
						CollectionValue.put(COVERID + count, "5");
						if (SportsEquipments.equalsIgnoreCase("")) {
							ErrorMsg.append(runner.getErrormsg("182"));
							ErrorMsg.append(',');
						} else if (validationFormat.isDigitValidationFormat(SportsEquipments)) {
							CollectionValue.put(CLAIMAMOUNT + count,SportsEquipments);
						} else {
							ErrorMsg.append(runner.getErrormsg("183"));
							ErrorMsg.append(',');
						}
						if (SportsEquiArea.equalsIgnoreCase("")) {
							ErrorMsg.append(runner.getErrormsg("184"));
							ErrorMsg.append(',');
						} else {
							CollectionValue.put(CLAIMDETAILS + count,SportsEquiArea);
						}
						count = count + 1;
					}

					if (PedalCyclesCheck.equalsIgnoreCase("Y")) {
						final String PedalCycles = isNull(request.getParameter("PedalCycles"));
						final String PedalCyclesArea = isNull(request.getParameter("PedalCyclesArea"));
						CollectionValue.put(COVERID + count, "6");
						if (PedalCycles.equalsIgnoreCase("")) {
							ErrorMsg.append(runner.getErrormsg("185"));
							ErrorMsg.append(',');
						} else if (validationFormat.isDigitValidationFormat(PedalCycles)) {
							CollectionValue.put(CLAIMAMOUNT + count,PedalCycles);
						} else {
							ErrorMsg.append(runner.getErrormsg("186"));
							ErrorMsg.append(',');
						}
						if (PedalCyclesArea.equalsIgnoreCase("")) {
							ErrorMsg.append(runner.getErrormsg("187"));
							ErrorMsg.append(',');
						} else {
							CollectionValue.put(CLAIMDETAILS + count,	PedalCyclesArea);
						}
						count = count + 1;
					}

					if (LaptopsCheck.equalsIgnoreCase("Y")) {
						final String Laptops = isNull(request.getParameter("Laptops"));
						final String LaptopsArea = isNull(request.getParameter("LaptopsArea"));
						CollectionValue.put(COVERID + count, "7");
						if (Laptops.equalsIgnoreCase("")) {
							ErrorMsg.append(runner.getErrormsg("188"));
							ErrorMsg.append(',');
						} else if (validationFormat.isDigitValidationFormat(Laptops)) {
							CollectionValue.put(CLAIMAMOUNT + count, Laptops);
						} else {
							ErrorMsg.append(runner.getErrormsg("189"));
							ErrorMsg.append(',');
						}
						if (LaptopsArea.equalsIgnoreCase("")) {
							ErrorMsg.append(runner.getErrormsg("190"));
							ErrorMsg.append(',');
						} else {
							CollectionValue.put(CLAIMDETAILS + count,LaptopsArea);
						}
						count = count + 1;
					}

					if (ErrorMsg.length() > 0) {
						AddItem = "8";
						request.setAttribute(ADDITEM, AddItem);
						request.setAttribute(ERRORMSG, ErrorMsg);
						final RequestDispatcher dispatcher = request.getRequestDispatcher(PRESUM);
						dispatcher.forward(request, response);
					} else {
						final RequestDispatcher dispatcher = request.getRequestDispatcher("/HomeInsurance/summary.jsp");
						dispatcher.forward(request, response);
					}
				}
			}
			if (path.equalsIgnoreCase("CoverCalculation")) {
				final com.maan.Home.DataManipualtion.DataSelect cover = new com.maan.Home.DataManipualtion.DataSelect();
				final StringBuffer ErrorMsg = new StringBuffer();
				final HashMap CoverageDetails = new HashMap();
				int CoverCount = 0;
				String GettingContentValues = "";
				String empDomestic = "";
				String optDomestic = "";
				final String ProId = isNull(request.getParameter(PROIDS));

				final String CoverSelect[][] = cover.coverSelectController(ProId);
				final String CoverSelectValue[][] = new String[CoverSelect.length][3];
				String typeModel = "";
				for (int i = 0; i < CoverSelectValue.length; i++) {
					final String GettingValues = isNull(request.getParameter(CoverSelect[i][2]));
					if ("".equalsIgnoreCase(GettingValues)||SELECT.equalsIgnoreCase(GettingValues)||"0".equals(GettingValues)) {
						if (CoverSelect[i][1].equals("2")){
							typeModel = cover.getFixedorNot(ProId,CoverSelect[i][1]);}
						if (typeModel.equalsIgnoreCase(DROPDOWN)) {
							if (GettingValues.equalsIgnoreCase(SELECT)){
								GettingContentValues = GettingValues;}
						} else {
							if ((GettingValues.equalsIgnoreCase("") || GettingValues.equalsIgnoreCase("0"))
									&& CoverSelect[i][1] != null&& Integer.parseInt(CoverSelect[i][1]) == 2) {
								GettingContentValues = CHECK;
							}
						}
						if (CoverSelect[i][1].equals("7")&&GettingValues.equalsIgnoreCase("")) {
								ErrorMsg.append("Please select Types of Contents,");
						}
						
					} else {
						CoverSelectValue[i][0] = CoverSelect[i][1];
						CoverSelectValue[i][1] = CoverSelect[i][2];
						CoverSelectValue[i][2] = GettingValues;
						if (CoverSelect[i][1].equals("4")){
							optDomestic = GettingValues;}
					}
				}
				empDomestic = isNull(request.getParameter(DOMESTIC),"0");
				String bed = isNull(request.getParameter(BED));
				String did = "";
				if (bed.equalsIgnoreCase(BED)	&& (ProId.equalsIgnoreCase("28") || ProId.equalsIgnoreCase("34"))) {
					did = isNull(request.getParameter("royalValId"));
				}
				int flag = 0;
				int flag1 = 0;
				int validFlag = 0;
				for (int i = 0; i < CoverSelectValue.length; i++) {
					if (CoverSelectValue[i][0] != null&& Integer.parseInt(CoverSelectValue[i][0]) <= 3) {
						flag = 1;
						if (validationFormat.isDigitValidationFormat(CoverSelectValue[i][2])) {
							if (Long.parseLong(CoverSelectValue[i][2]) <= 0) {
								validFlag++;
							}
							
						} else {
							ErrorMsg.append(CoverSelectValue[i][1]+ runner.getErrormsg("253"));
							ErrorMsg.append(',');
						}
					}
					if (CoverSelect[i][1].equals("2")){
						typeModel = cover.getFixedorNot(ProId,CoverSelect[i][1]);}
					if (typeModel.equalsIgnoreCase(DROPDOWN)) {
						if (GettingContentValues.equalsIgnoreCase(SELECT)	&& CoverSelectValue[i][0] != null
								&& Integer.parseInt(CoverSelectValue[i][0]) == 3){
							flag1 = 1;}
					} else {
						if (GettingContentValues.equalsIgnoreCase(CHECK)&& (CoverSelectValue[i][0] != null && Integer
										.parseInt(CoverSelectValue[i][0]) == 3)){
							flag1 = 1;}
					}

					if (CoverSelectValue[i][0] != null) {
						final String norooms = isNull(request.getParameter(NOOFROOM));
						request.setAttribute("norooms", norooms);

						if (CoverSelectValue[i][0].equalsIgnoreCase(Integer.toString((i + 1)))) {
							final String roomsSum = isNull(request.getParameter(ROOMSUM));
							CoverageDetails.put(COVERAGES + CoverCount,	CoverSelectValue[i][0]);
							CoverCount = CoverCount + 1;
							CoverageDetails.put(COVERAGES + CoverCount,CoverSelectValue[i][1]);
							CoverCount = CoverCount + 1;
							if (CoverSelectValue[i][0].equalsIgnoreCase("2")&& roomsSum.length() > 0) {
								CoverageDetails.put(COVERAGES+ CoverCount, roomsSum);
								request.setAttribute("roomsSum", roomsSum);

							} else{
								CoverageDetails.put(COVERAGES+ CoverCount, CoverSelectValue[i][2]);}
							CoverCount = CoverCount + 1;
						}
					}
				}
				if (flag == 0) {
					ErrorMsg.append(runner.getErrormsg("254"));
					ErrorMsg.append(',');
				}
				if (validFlag == 3) {
					ErrorMsg.append("Please give suminsured for atleast one Coverage,");
				}
				if (flag1 == 1) {
					if (!bed.equalsIgnoreCase(BED)&& (ProId.equalsIgnoreCase("28") || ProId.equalsIgnoreCase("34"))) {
						ErrorMsg.append(runner.getErrormsg("255"));
						ErrorMsg.append(',');
					}
					if (bed.equalsIgnoreCase(BED)&& (ProId.equalsIgnoreCase("28") || ProId.equalsIgnoreCase("34"))) {
						ErrorMsg.append(runner.getErrormsg("291"));
						ErrorMsg.append(',');
					}
				}
				ValidationFormat validObj = new ValidationFormat();
				if ("Y".equalsIgnoreCase(optDomestic)&& (empDomestic.length() == 0 ||
						!validObj.isNumberValue(empDomestic) || empDomestic.equals("0"))) {
					ErrorMsg.append(runner.getErrormsg("257"));
					ErrorMsg.append(',');
				}
				session.setAttribute(COVERAGES, CoverageDetails);

				// start//////////////////////////////
				final String BUILDINGS = isNull(request.getParameter("BUILDINGS"));
				String CONTENTS = isNull(request.getParameter("CONTENTS"));
				final String VALUABLES = isNull(request.getParameter("PERSONAL BELONGINGS"));

				//final com.maan.Home.Premium.Premium premium = new com.maan.Home.Premium.Premium();
				// String[][] claims = premium.getClaimsUnderProduct();
				String[][] groupedFactorss = new String[0][0];

				double PremiumCal2 = 0.0;
				double buildingval = 0.0;
				// For getting range of Personal Belongins - Validation
				final String ValidationSubIds[][] = cover.getValidationSubIds(ProId);
				if (ValidationSubIds.length > 0){
					groupedFactorss =null;// premium.getCalculatedValue1234(isNull(ValidationSubIds[2][2]));
					}
				double Data_Value = 0.0;
				if (groupedFactorss.length > 0){
					Data_Value = Double.parseDouble(groupedFactorss[0][0]);}

				groupedFactorss = null;//premium.getPropertyFactorsUnderClaimForValidation123(ProId);
				
				String buildSubId = "";
				String conSubId = "";
				String perSubId = "";
				if (ValidationSubIds.length > 0) {
					buildSubId = isNull(ValidationSubIds[0][1]);
					conSubId = isNull(ValidationSubIds[1][1]);
					perSubId = isNull(ValidationSubIds[2][1]);
				}
				int noofRoom;
				for (int i = 0; i < groupedFactorss.length; i++) {

					final String[][] getRatingFactor =null; //premium.getCalculatedValue125(groupedFactorss[i][3]);
					if (groupedFactorss[i][3].equalsIgnoreCase(buildSubId)) {
						if (getRatingFactor.length > 0) {
							PremiumCal2 = Double.parseDouble(getRatingFactor[0][3] == null ? "0":getRatingFactor[0][3]);
						}
						if (validationFormat.isDigitValidationFormat(BUILDINGS) && !BUILDINGS.equalsIgnoreCase("")) {
							buildingval = Double.parseDouble(BUILDINGS);
						}
						if (PremiumCal2 < buildingval) {
							ErrorMsg.append(runner.getErrormsg("258"));
							ErrorMsg.append(PremiumCal2);
							ErrorMsg.append(',');
						}
					}
					if (groupedFactorss[i][3].equalsIgnoreCase(conSubId)) {
						if (getRatingFactor.length > 0) {
							PremiumCal2 = Double.parseDouble(getRatingFactor[0][3] == null ? "0":getRatingFactor[0][3]);
						}
						if (validationFormat.isDigitValidationFormat(CONTENTS) 	&& !CONTENTS.equalsIgnoreCase(SELECT)
								&& !CONTENTS.equalsIgnoreCase("")) {
							final double contentval = Double.parseDouble(CONTENTS);
							if (PremiumCal2 < contentval) {
								ErrorMsg.append(runner.getErrormsg("259"));
								ErrorMsg.append(PremiumCal2);
								ErrorMsg.append(',');
							}
						}
						if (bed.equalsIgnoreCase(BED)	&& (ProId.equalsIgnoreCase("28") || ProId.equalsIgnoreCase("34"))) {
							final String noroomVal = isNull(request.getParameter(NOOFROOM),"0");
							final String roomSums = isNull(request.getParameter(ROOMSUM),"0");
							String rooms[][] = new String[0][0];
							if (session.getAttribute(BETCONTENT) != null){
								rooms = (String[][]) session.getAttribute(BETCONTENT);}
							noofRoom = Integer.parseInt(noroomVal);
							CONTENTS = roomSums;
							final int CurrentroomSum = Integer.parseInt(roomSums);
							if (noofRoom >= 2) {
								final int Originalsum = Integer.parseInt(rooms[(noofRoom - 2)][0]);
								final int Originalsum1 = Integer.parseInt(rooms[(noofRoom - 1)][0]);
								if (CurrentroomSum < Originalsum|| CurrentroomSum > Originalsum1) {
									ErrorMsg.append(FOR + noofRoom
													+ " Bed Room Sum Insured value should be between  "
													+ Originalsum + " and "
													+ Originalsum1 + ".,");
								}
							} else if (noofRoom == 1) {
								final int Originalsum = Integer.parseInt(rooms[0][0]);
								if (CurrentroomSum > Originalsum) {
									ErrorMsg.append(FOR
													+ noofRoom
													+ " Bed Room Sum Insured value should be less than or equal to  "
													+ Originalsum + ".,");
								}

							}
						}
					}
					if (groupedFactorss[i][3].equalsIgnoreCase(perSubId)) {
						if (getRatingFactor.length > 0) {
							PremiumCal2 = Double.parseDouble(getRatingFactor[0][3] == null ? "0":getRatingFactor[0][3]);
						}
						if (validationFormat.isDigitValidationFormat(VALUABLES) && !VALUABLES.equalsIgnoreCase("")
								&& validationFormat.isDigitValidationFormat(CONTENTS)&& !CONTENTS.equalsIgnoreCase("0")) {
							final double valuableval = Double.parseDouble(VALUABLES);
							if (validationFormat.isDigitValidationFormat(CONTENTS)
									&& !CONTENTS.equalsIgnoreCase(SELECT)
									&& !CONTENTS.equalsIgnoreCase("")) {
								if (valuableval > ((Double.parseDouble(CONTENTS)) * Data_Value)) {
									if (bed.equalsIgnoreCase(BED)
											&& (ProId.equalsIgnoreCase("28") || ProId.equalsIgnoreCase("34"))) {
										ErrorMsg.append(runner.getErrormsg("290"));
										ErrorMsg.append(',');
									} 
									else {
										ErrorMsg.append("The Personal Belongings value should be less than or equal to "+Math.round((Data_Value*100))+"% of the Contents value. If not please refer to XYZ Insurance.");
										ErrorMsg.append(',');
									}
								}
							}
						}
					}

				}
				if (ErrorMsg.length() > 0) {
					request.setAttribute(ERRORMSG, ErrorMsg);
					final RequestDispatcher dispatcher = request.getRequestDispatcher("/HomeInsurance/CoverInformationA.jsp");
					dispatcher.forward(request, response);
				} else {
					if (optDomestic.equalsIgnoreCase("Y")&& empDomestic.length() > 0) {
							request.setAttribute(DOMESTIC, empDomestic);
					}
					if (bed.equalsIgnoreCase(BED)	&& (ProId.equalsIgnoreCase("28") || ProId.equalsIgnoreCase("34"))) {
						request.setAttribute(BED, BED);
						request.setAttribute("did", did);
					}
					final RequestDispatcher dispatcher1 = request.getRequestDispatcher("/HomePremiumCalculateController");
					if (dispatcher1 != null){
						dispatcher1.include(request, response);}

					request.setAttribute("buildings_premium", (String) session.getAttribute("buildings_premium"));
					request.setAttribute("contents_premium", (String) session.getAttribute("contents_premium"));
					request.setAttribute("pbelongings_premium",(String) session.getAttribute("pbelongings_premium"));
					request.setAttribute("liability_premium", (String) session.getAttribute("liability_premium"));
					request.setAttribute("lossof_doc_premium", (String) session.getAttribute("lossof_doc_premium"));
					request.setAttribute("subsidence_premium", (String) session.getAttribute("subsidence_premium"));
					request.setAttribute("total premium", (String) session.getAttribute("total_premium"));

					request.setAttribute("TotalPremium", (String) session.getAttribute("total_premium"));

					final RequestDispatcher dispatcher123 = request.getRequestDispatcher("../HomeInsurance/CoverInformationA.jsp");
					dispatcher123.forward(request, response);
				}
			}
			if (path.equalsIgnoreCase("CoverCollection")) {
				final com.maan.Home.DataManipualtion.DataSelect cover = new com.maan.Home.DataManipualtion.DataSelect();
				
				String btoc = isNull((String) session.getAttribute("b2c"));
				if (btoc.equalsIgnoreCase("b2c")){
					btoc = "BTOC";}
				final String ProId = isNull(request.getParameter(PROIDS));
				if (ProId.equalsIgnoreCase("60")|| ProId.equalsIgnoreCase("59")){
					btoc = "BTOC";}
				StringBuffer ErrorMsg = new StringBuffer();
				final HashMap CoverageDetails = new HashMap();
				int CoverCount = 0;
				final String  Save  = isNull(request.getParameter("Save"));
				final String  bed = isNull(request.getParameter(BED));
				String did = "";
				if (bed.equalsIgnoreCase(BED)&& (ProId.equalsIgnoreCase("28") || ProId.equalsIgnoreCase("34"))) {
					did = isNull(request.getParameter("royalValId"));
				}
				// Insured Location Details
				final String buildingName = isNull(request.getParameter("buildingName"));
				String buildingStreet = isNull(request.getParameter("buildingStreet"));
				final String otherArea = isNull(request.getParameter("otherArea"));
				final String buildingFlat = isNull(request.getParameter("buildingFlat"));
				final String buildingEmirate = isNull(request.getParameter("buildingEmirate"));
				
				if("Others".equalsIgnoreCase(buildingStreet)){
					
					if("".equalsIgnoreCase(otherArea)){
						ErrorMsg.append("specify other area");
						ErrorMsg.append(',');						
					}else{
						buildingStreet=otherArea;
					}
						
				}

				if (!Save.equalsIgnoreCase("Save")) {
					if (buildingName.equalsIgnoreCase("")) {
						ErrorMsg.append(runner.getErrormsg("261"));
						ErrorMsg.append(',');
					}

					if ("".equalsIgnoreCase(buildingStreet)) {
						ErrorMsg.append(runner.getErrormsg("262"));
						ErrorMsg.append(',');
					} else if (SELECT.equalsIgnoreCase(buildingStreet)) {
						ErrorMsg.append("Please select any one of Residing property,");
					} else if ("None".equalsIgnoreCase(buildingStreet)) {
						ErrorMsg.append("Please select any one of Residing property,");
					}
					if ("".equalsIgnoreCase(buildingFlat)) {
						ErrorMsg.append(runner.getErrormsg("263"));
						ErrorMsg.append(',');
					}
					if ("Select Emirate".equalsIgnoreCase(buildingEmirate)|| "".equalsIgnoreCase(buildingEmirate)) {
						ErrorMsg.append(runner.getErrormsg("25"));
						ErrorMsg.append(',');
					}
				}
				//
				String GettingContentValues = "";
				String empDomestic = "";
				String optDomestic = "";
				String typeModel = "";

				final String CoverSelect[][] = cover.coverSelectController(ProId);
				String CoverSelectValue[][] = new String[CoverSelect.length][3];

				for (int i = 0; i < CoverSelectValue.length; i++) {
					final String GettingValues = isNull(request.getParameter(CoverSelect[i][2]));
					if ("".equals(GettingValues)|| SELECT.equalsIgnoreCase(GettingValues)||"0".equals(GettingValues)) {
						if (CoverSelect[i][1].equals("2")){
							typeModel = cover.getFixedorNot(ProId,CoverSelect[i][1]);}
						if (DROPDOWN.equalsIgnoreCase(typeModel)&&SELECT.equalsIgnoreCase(GettingValues)) {
								GettingContentValues = GettingValues;
						} else {
							if (("".equalsIgnoreCase(GettingValues) || "0".equalsIgnoreCase(GettingValues))
									&& CoverSelect[i][1] != null&& Integer.parseInt(CoverSelect[i][1]) == 2) {
								GettingContentValues = CHECK;
							}
						}
						if (CoverSelect[i][1].equals("7")&&GettingValues.equalsIgnoreCase("")) {
								ErrorMsg.append("Please select Types of Contents,");
						}
						
					} else {
						CoverSelectValue[i][0] = CoverSelect[i][1];
						CoverSelectValue[i][1] = CoverSelect[i][2];
						CoverSelectValue[i][2] = GettingValues;
						if (CoverSelect[i][1].equals("4")){
							optDomestic = GettingValues;}
					}
				}
				empDomestic = isNull(request.getParameter(DOMESTIC));
				// ////////////////////////////////////////////////
				String quoteNo = "";
				final String Login_id = isNull((String) session.getAttribute(USERS));
				final String product_id = ProId;
				final String DD1 = isNull(request.getParameter(DAY));
				final String MM1 = isNull(request.getParameter(MONTH));
				final String YYYY1 = isNull(request.getParameter(YEAR));
				final String ExpDate = isNull(request.getParameter(EXPDATE));
				final String customerid = isNull(request.getParameter("customer"));

				final String[] Mon = { "JAN", "FEB", "MAR", "APR", "MAY", "JUN","JUL", "AUG", "SEP", "OCT", "NOV", "DEC" };
				String EffectiveDate = "";
				if (SELECT.equalsIgnoreCase(DD1) || SELECT.equalsIgnoreCase(MM1)|| SELECT.equalsIgnoreCase(YYYY1)) {
					ErrorMsg.append("Invalid Date ");
				} else {
					final int temp = Integer.parseInt(MM1);
					for (int i = 0; i <= Mon.length; i++) {
						if (i == temp) {
							EffectiveDate = DD1 + "-" + Mon[--i] + "-"+ YYYY1;
							break;
						}
					}
					final String resMsg = dataCus.validateDateOfBirth(Integer.parseInt(YYYY1), Integer.parseInt(MM1), Integer.parseInt(DD1));
					if (resMsg.length() > 0 || !"".equalsIgnoreCase(resMsg)) {
						ErrorMsg.append(resMsg);
						ErrorMsg.append(',');
					}

				}
				// /////////////////////////////////////////////////////////////////////
				int flag = 0;
				int flag1 = 0;
				int buildFlag = 0;
				int conFlag = 0;
				int perFlag = 0;
				int validFlag = 0;
				long totalSum = 0;

				for (int i = 0; i < CoverSelectValue.length; i++) {
					if (CoverSelectValue[i][0] != null
							&& Integer.parseInt(CoverSelectValue[i][0]) <= 3) {
						flag = 1;
						if (validationFormat.isDigitValidationFormat(CoverSelectValue[i][2])) {
							if (Long.parseLong(CoverSelectValue[i][2]) <= 0) {
								validFlag++;
							}
							totalSum = totalSum	+ Long.parseLong(CoverSelectValue[i][2]);
						} else  {
							ErrorMsg.append(CoverSelectValue[i][1]+ runner.getErrormsg("253"));
							ErrorMsg.append(',');
						}
						if (Integer.parseInt(CoverSelectValue[i][0]) == 1){
							buildFlag = 1;}
						else if (Integer.parseInt(CoverSelectValue[i][0]) == 2){
							conFlag = 1;}
						else if (Integer.parseInt(CoverSelectValue[i][0]) == 3){
							perFlag = 1;}
					}
					if (CoverSelect[i][1].equals("2")){
						typeModel = cover.getFixedorNot(ProId,CoverSelect[i][1]);}
					if (typeModel.equalsIgnoreCase(DROPDOWN)) {
						if (GettingContentValues.equalsIgnoreCase(SELECT)
								&& CoverSelectValue[i][0] != null&& Integer.parseInt(CoverSelectValue[i][0]) == 3){
							flag1 = 1;}
					} else {
						if (GettingContentValues.equalsIgnoreCase(CHECK)
								&& (CoverSelectValue[i][0] != null && Integer.parseInt(CoverSelectValue[i][0]) == 3)){
							flag1 = 1;}
					}

					if (CoverSelectValue[i][0] != null&& !CoverSelectValue[i][2].equalsIgnoreCase("0")) {
						final String norooms = isNull(request.getParameter(NOOFROOM));
						request.setAttribute("norooms", norooms);
						if (CoverSelectValue[i][0].equalsIgnoreCase(Integer.toString((i + 1)))) {
							final String roomsSum = isNull(request.getParameter(ROOMSUM));
							CoverageDetails.put(COVERAGES + CoverCount,	CoverSelectValue[i][0]);
							CoverCount = CoverCount + 1;
							CoverageDetails.put(COVERAGES + CoverCount,	CoverSelectValue[i][1]);
							CoverCount = CoverCount + 1;
							if (CoverSelectValue[i][0].equalsIgnoreCase("2")&& roomsSum.length() > 0) {
								CoverageDetails.put(COVERAGES+ CoverCount, roomsSum);
								request.setAttribute("roomsSum", roomsSum);
							} else{
								CoverageDetails.put(COVERAGES+ CoverCount, CoverSelectValue[i][2]);}
							CoverCount = CoverCount + 1;
						}
					}
				}
				ValidationFormat validObj = new ValidationFormat();
				if (flag == 0) {
					ErrorMsg.append(runner.getErrormsg("254"));
					ErrorMsg.append(',');
				}
				if (flag1 == 1) {
					ErrorMsg.append(runner.getErrormsg("255"));
					ErrorMsg.append(',');
				}
				if (validFlag == 3 || totalSum == 0) {
					ErrorMsg.append("Please give suminsured for atleast one Coverage,");
				}
				if ("Y".equalsIgnoreCase(optDomestic)&& ( empDomestic.length() ==  0 || !validObj.isNumberValue(empDomestic) || empDomestic.equals("0"))) {
					ErrorMsg.append(runner.getErrormsg("257"));
					ErrorMsg.append(',');
				}
				// Insurance START DATE VALIDATION
				if (SELECT.equalsIgnoreCase(DD1) || SELECT.equalsIgnoreCase(MM1)|| SELECT.equalsIgnoreCase(YYYY1)) {
					ErrorMsg.append(runner.getErrormsg("264"));
					ErrorMsg.append(',');
				} else {
					ErrorMsg.append(validateBackDates(DD1,MM1,YYYY1,Login_id,ProId));
				}
				// /////////////////////////////////////////////////////////////////////////
				final String BUILDINGS = isNull(request.getParameter("BUILDINGS"));
				String CONTENTS = isNull(request.getParameter("CONTENTS"));
				final String VALUABLES = isNull(request.getParameter("PERSONAL BELONGINGS"));
				//final com.maan.Home.Premium.Premium premium = new com.maan.Home.Premium.Premium();

				double PremiumCal2 = 0.0;
				double buildingval = 0.0;
				String[][] ValidationSubIds = new String[0][0];
				ValidationSubIds = cover.getValidationSubIds(ProId);
				String[][] groupedFactorss = null;//premium.getCalculatedValue1234(isNull(ValidationSubIds[2][2]));
				double Data_Value = 0.0;
				if (groupedFactorss.length > 0){
					Data_Value = Double.parseDouble(groupedFactorss[0][0]);}
				String[][] getRatingFactor = new String[0][0];
				groupedFactorss =null;// premium.getPropertyFactorsUnderClaimForValidation123(ProId);
				String buildSubId = "";
				String conSubId = "";
				String perSubId = "";
				if (ValidationSubIds.length > 0) {
					buildSubId = isNull(ValidationSubIds[0][1]);
					conSubId = isNull(ValidationSubIds[1][1]);
					perSubId = isNull(ValidationSubIds[2][1]);
				}
				double bills = 0, cons = 0, pers = 0, bedRef = 0;
				int noofRoom = 0;
				for (int i = 0; i < groupedFactorss.length; i++) {
					getRatingFactor =null; //premium.getCalculatedValue125(groupedFactorss[i][3]);
					if (groupedFactorss[i][3].equalsIgnoreCase(buildSubId)) {
						if (getRatingFactor.length > 0) {
							PremiumCal2 = Double.parseDouble(getRatingFactor[0][3] == null ? "0":getRatingFactor[0][3]);
						}

						if (validationFormat.isDigitValidationFormat(BUILDINGS)&& !BUILDINGS.equalsIgnoreCase("")) {
							buildingval = Double.parseDouble(BUILDINGS);
						}
						if (PremiumCal2 < buildingval) {
							bills = PremiumCal2;
						}
					}
					if (groupedFactorss[i][3].equalsIgnoreCase(conSubId)) {
						if (getRatingFactor.length > 0) {
							PremiumCal2 = Double.parseDouble(getRatingFactor[0][3] == null ? "0":getRatingFactor[0][3]);
						}


						if (validationFormat.isDigitValidationFormat(CONTENTS)&& !CONTENTS.equalsIgnoreCase(SELECT)
								&& !CONTENTS.equalsIgnoreCase("")) {
							final double contentval = Double.parseDouble(CONTENTS);
							if (PremiumCal2 < contentval) {
								cons = PremiumCal2;
							}
						}
						if (bed.equalsIgnoreCase(BED)	&& (ProId.equalsIgnoreCase("28") || ProId
										.equalsIgnoreCase("34"))) {

							final String noroomVal = isNull(request.getParameter(NOOFROOM),"0");
							final String roomSums = isNull(request.getParameter(ROOMSUM),"0");
							String rooms[][] = new String[0][0];
							if (session.getAttribute(BETCONTENT) != null){
								rooms = (String[][]) session.getAttribute(BETCONTENT);}
							noofRoom = Integer.parseInt(noroomVal);
							CONTENTS = roomSums;
							final int CurrentroomSum = Integer.parseInt(roomSums);
							if (noofRoom >= 2) {
								final int Originalsum = Integer.parseInt(rooms[(noofRoom - 2)][0]);
								final int Originalsum1 = Integer.parseInt(rooms[(noofRoom - 1)][0]);
								if (CurrentroomSum < Originalsum
										|| CurrentroomSum > Originalsum1) {
									if (noofRoom == 4&& CurrentroomSum > Originalsum1) {
										bedRef = Originalsum1;
									} else {
										ErrorMsg.append(FOR
														+ noofRoom
														+ " Bed Room Sum Insured value should be between  "
														+ Originalsum + " and "
														+ Originalsum1 + ".,");
									}
								}

							} else if (noofRoom == 1) {
								final int Originalsum = Integer.parseInt(rooms[0][0]);
								if (CurrentroomSum > Originalsum) {
									ErrorMsg.append(FOR
													+ noofRoom
													+ " Bed Room Sum Insured value should be less than or equal to  "
													+ Originalsum + ".,");
								}

							}
						}
					}
					if (groupedFactorss[i][3].equalsIgnoreCase(perSubId)) {
						if (getRatingFactor.length > 0) {
							PremiumCal2 = Double.parseDouble(getRatingFactor[0][3] == null ? "0":getRatingFactor[0][3]);
						}

						if (validationFormat.isDigitValidationFormat(VALUABLES)&& !VALUABLES.equalsIgnoreCase("")&& validationFormat
										.isDigitValidationFormat(CONTENTS)&& !CONTENTS.equalsIgnoreCase("0")) {
							final double valuableval = Double.parseDouble(VALUABLES);
							if (validationFormat.isDigitValidationFormat(CONTENTS)&& !SELECT.equalsIgnoreCase(CONTENTS)
									&& !"".equalsIgnoreCase(CONTENTS)&&valuableval > (Double.parseDouble(CONTENTS) * Data_Value)) {
									pers = 1;
							}
						}
					}

				}
				if (ErrorMsg.length() > 0) {
					request.setAttribute(ERRORMSG, ErrorMsg);
					final RequestDispatcher dispatcher = request.getRequestDispatcher("/HomeInsurance/CoverInformationA.jsp");
					dispatcher.forward(request, response);
				} else {
					if (session.getAttribute(QUOTENO) == null	|| "".equalsIgnoreCase((String)session.getAttribute(QUOTENO)))
					{	
						quoteNo = dataSelect.getMaxQuote(ProId, branch); LogManager.info("Quote_no...............Senthil......"+quoteNo);
					}
					else {
						quoteNo = (String) session.getAttribute(QUOTENO);
					}
					// Find No of Bed Rooms
					int roomNo = 0;
					if (bed.equalsIgnoreCase(BED)&& (ProId.equalsIgnoreCase("28") || ProId.equalsIgnoreCase("34"))) {
						roomNo = noofRoom;
					}
					// Sum Insured Block
					
						String temps = "";
						final StringBuffer referralName = new StringBuffer(1000);
						if (bills > 0) {
							temps = Double.toString(bills);
							if (temps.indexOf('.') != -1){
								temps = temps.substring(0, temps.indexOf('.'));}
							session.setAttribute(BUILDING + quoteNo, temps);
							referralName.append("* Buildings SumInsured Referral");
						} else if (session.getAttribute(BUILDING + quoteNo) != null){
							session.removeAttribute(BUILDING + quoteNo);}
						if (cons > 0 || bedRef > 0) {
							if (cons > 0) {
								temps = Double.toString(cons);
								if (temps.indexOf('.') != -1){
									temps = temps.substring(0, temps.indexOf('.'));}
								session.setAttribute(CONTENT + quoteNo, temps);
							} else if (bedRef > 0) {
								temps = Double.toString(bedRef);
								if (temps.indexOf('.') != -1){
									temps = temps.substring(0, temps.indexOf('.'));}
								session.setAttribute(CONTENT + quoteNo, temps);
							}
							referralName.append("* Contents SumInsured Referral");
						} else if (session.getAttribute(CONTENT + quoteNo) != null){
							session.removeAttribute(CONTENT + quoteNo);}
						if(session.getAttribute("Single"+QUOTENO+"Cover2")!=null){
							session.removeAttribute("Single"+QUOTENO+"Cover2");
						}if(session.getAttribute("Single"+QUOTENO+"Cover3")!=null){
							session.removeAttribute("Single"+QUOTENO+"Cover3");
						}
						if (pers == 1) {
							session.setAttribute(PERSONAL + quoteNo,"Personal Belongings SumInsured Referral");
							referralName.append("* Personal Belongings SumInsured Referral");
						} else if (session.getAttribute(PERSONAL + quoteNo) != null){
							session.removeAttribute(PERSONAL + quoteNo);}
						if (bills > 0 || cons > 0 || pers == 1) {
							session.setAttribute("Referral" + quoteNo,"SumInsured Referral");
						} else if (session.getAttribute("Referral" + quoteNo) != null){
							session.removeAttribute("Referral" + quoteNo);}
						
					// Sum Insured Block
					final Map quoteDetails = new HashMap();
					quoteDetails.put(QUOTENUM, quoteNo);
					quoteDetails.put(QUOTENUM, quoteNo);
					quoteDetails.put(BRANCH, branch);
					quoteDetails.put("customerid", customerid);
					quoteDetails.put("Login_id", Login_id);
					quoteDetails.put(PRODUCTID, product_id);
					quoteDetails.put(EFFECTDATE, EffectiveDate);
					quoteDetails.put(EXPDATE, ExpDate);
					quoteDetails.put("roomNo", Integer.toString(roomNo));
					quoteDetails.put("btoc", btoc);
					
					dataSelect.quoteGeneration(quoteDetails);
					if(referralName.length()>0){
						dataSelect.updateSumInsuredReferralNames(quoteNo,ProId, referralName.toString());
					}
					if (buildFlag == 0){
						DataManip.homeCoverageBuildingDetletion(quoteNo);
					}
					if (conFlag == 0){
						DataManip.homeCoverageTransactionDetletion(quoteNo, "2");
					}
					if (perFlag == 0){
						DataManip.homeCoverageTransactionDetletion(quoteNo, "3");
					}
					DataManip.homeCoverageBuildingInitial(quoteNo, "0",CoverageDetails);
					DataManip.homeCoverageBuildingTransaction(quoteNo,buildingName, buildingStreet, buildingFlat,buildingEmirate);

					session.setAttribute(QUOTENO, quoteNo);
					session.setAttribute(COVERAGES, CoverageDetails);
					if ("Y".equalsIgnoreCase(optDomestic)&& empDomestic.length() > 0) {
							request.setAttribute(DOMESTIC, empDomestic);
							dataSelect.addDomesticStaffNo(quoteNo, empDomestic);
					}else{
						dataSelect.removeDemestic(quoteNo);
					}
					if (BED.equalsIgnoreCase(bed)	&& ("28".equalsIgnoreCase(ProId) || "34".equalsIgnoreCase(ProId))) {
						request.setAttribute(BED, BED);
						request.setAttribute("did", did);
					}
					final RequestDispatcher dispatcher1 = request.getRequestDispatcher("/HomePremiumCalculateController");
					if (dispatcher1 != null){
						dispatcher1.include(request, response);}

					final String preCheck = (String) session.getAttribute("total_premium");
					if (preCheck == null||"0".equals(preCheck)||"".equals(preCheck)||preCheck.length()<= 0||NULL.equalsIgnoreCase(preCheck)) {
						ErrorMsg.append("Please verify the premium,");
						request.setAttribute(ERRORMSG, ErrorMsg);
						final RequestDispatcher dispatcherfinal1 = request.getRequestDispatcher("../HomeInsurance/CoverInformationA.jsp");
						if (dispatcherfinal1 != null){
							dispatcherfinal1.forward(request, response);}
					}

					if (Save.equalsIgnoreCase("Save")) {
						session.setAttribute("HomeQuoteNumber", quoteNo);
						response.sendRedirect("../HomeInsurance/showQuote.jsp");
					} else {
						response.sendRedirect(PRESUM);
					}

				}
			}
			if (path.equalsIgnoreCase("Home_Admin_ShowQuote")) {
				final StringBuffer ErrorMsg = new StringBuffer();
				final String addSign = isNull(request.getParameter(ADDSIGN));
				final String Remarks = isNull(request.getParameter("Remarks"));
				if (addSign.equalsIgnoreCase("sign")|| addSign.equalsIgnoreCase("")) {
					ErrorMsg.append(runner.getErrormsg("267"));
					ErrorMsg.append(',');
				}
				if (Remarks.equalsIgnoreCase("") || Remarks.length() <= 0) {
					ErrorMsg.append(runner.getErrormsg("268"));
					ErrorMsg.append(',');
				} else {
					if (Remarks.equalsIgnoreCase("A")) {
						request.setAttribute("ReferalCase", "AASuccess");
						/*final RequestDispatcher dispatcher = request.getRequestDispatcher("../HomeInsurance/Home_Admin_ShowQuote.jsp");
						if (dispatcher != null){
							dispatcher.forward(request, response);}*/
					}
				}
				System.out.println("ErrorMsg: "+ErrorMsg.length());
				
				if (ErrorMsg.length() > 0) {
					request.setAttribute(ERRORMSG, ErrorMsg);
					final RequestDispatcher dispatcher = request.getRequestDispatcher("../HomeInsurance/AdminHomeSummary.jsp");
					if (dispatcher != null){
						dispatcher.forward(request, response);}
				} else {
					final RequestDispatcher dispatcher = request.getRequestDispatcher("../HomeInsurance/Home_Admin_ShowQuote.jsp");
					if (dispatcher != null){
						dispatcher.forward(request, response);}
				}
			}
			if (path.equalsIgnoreCase("AddItemChooseFinance")) {
				final String AddedValue = isNull(request.getParameter(ADDVAL));
				final int CountCollection = Integer.parseInt(AddedValue);
				final Map financeDetails = getFinanceDetails(request,"Add");
				final StringBuffer ErrorMsg = (StringBuffer)financeDetails.get(ERRORMSG);
				if (ErrorMsg.length() > 0) {
					request.setAttribute(INCREMENTVAL, Integer.toString(CountCollection));
					request.setAttribute(ERRORMSG, ErrorMsg);
					final RequestDispatcher dispatcher = request.getRequestDispatcher(PRESUM);
					dispatcher.forward(request, response);
				} else {
					request.setAttribute(INCREMENTVAL, Integer.toString(CountCollection+1));
					final RequestDispatcher dispatcher = request.getRequestDispatcher(PRESUM);
					dispatcher.forward(request, response);
				}
			}
			if (path.equalsIgnoreCase("adminDelete")) {
				final String quoteNo = isNull(request.getParameter(QUOTENUM));
				final String type = request.getParameter(TYPE) == null ?HOME:request.getParameter(TYPE);
				if (HOME.equalsIgnoreCase(type)) {
					final String pdfFPathOri = request.getSession().getServletContext().getRealPath("/PDFFile/Schedule/PremiumSummary_Schedule_"+ quoteNo + PDF);
					final String pdfFPathOriCopy = request.getSession().getServletContext().getRealPath("/PDFFile/Schedule/Original_Copy/PremiumSummary_Schedule_"+ quoteNo + PDF);
					final String pdfFilePathCopy = request.getSession().getServletContext().getRealPath("/PDFFile/Schedule/Copy/PremiumSummary_Schedule_"+ quoteNo + PDF);
					final String pdfFilePathDebit = request.getSession().getServletContext().getRealPath("/PDFFile/Debit/PremiumSummary_Debit_"+ quoteNo + PDF);
					if (new File(pdfFPathOri).exists()){
						request.setAttribute(PDFFPATHORI,pdfFPathOri);}
					if (new File(pdfFPathOriCopy).exists()){
						request.setAttribute(PDFFPATHORlCOPY,pdfFPathOriCopy);}
					if (new File(pdfFilePathCopy).exists()){
						request.setAttribute(PDFFPATHCOPY,pdfFilePathCopy);}
					if (new File(pdfFilePathDebit).exists()){
						request.setAttribute(PDFFPATHDEBIT,pdfFilePathDebit);}
					request.setAttribute(QUOTENUM, quoteNo);
					request.setAttribute(TYPE, type);
				} else if (type.equalsIgnoreCase(TRAVEL)) {
					final String pdfFPathOri = request.getSession().getServletContext().getRealPath("/TravelPDFFile/Schedule/PremiumSummary_Schedule_"+ quoteNo + PDF);
					final String pdfFPathOriCopy = request.getSession().getServletContext().getRealPath("/TravelPDFFile/Schedule/Original_Copy/PremiumSummary_Schedule_"+ quoteNo + PDF);
					final String pdfFilePathCopy = request.getSession().getServletContext().getRealPath("/TravelPDFFile/Schedule/Copy/PremiumSummary_Schedule_"+ quoteNo + PDF);
					final String pdfFilePathDebit = request.getSession().getServletContext().getRealPath("/TravelPDFFile/Debit/PremiumSummary_Debit_"+ quoteNo + PDF);
					if (new File(pdfFPathOri).exists()){
						request.setAttribute(PDFFPATHORI,pdfFPathOri);}
					if (new File(pdfFPathOriCopy).exists()){
						request.setAttribute(PDFFPATHORlCOPY,pdfFPathOriCopy);}
					if (new File(pdfFilePathCopy).exists()){
						request.setAttribute(PDFFPATHCOPY,pdfFilePathCopy);}
					if (new File(pdfFilePathDebit).exists()){
						request.setAttribute(PDFFPATHDEBIT,pdfFilePathDebit);}
					request.setAttribute(QUOTENUM, quoteNo);
					request.setAttribute(TYPE, type);
				} else if (type.equalsIgnoreCase("Office")) // Office Delete
				{
					final String pdfFPathOri = request.getSession().getServletContext().getRealPath("/PDFFile/Office/Schedule/PremiumSummary_Schedule_"
									+ quoteNo + PDF);
					final String pdfFPathOriCopy = request.getSession().getServletContext().getRealPath("/PDFFile/Office/Schedule/Original_Copy/PremiumSummary_Schedule_"
									+ quoteNo + PDF);
					final String pdfFilePathCopy = request.getSession().getServletContext().getRealPath("/PDFFile/Office/Schedule/Copy/PremiumSummary_Schedule_"
									+ quoteNo + PDF);
					final String pdfFilePathDebit = request.getSession().getServletContext().getRealPath("/PDFFile/Office/Debit/PremiumSummary_Debit_"
									+ quoteNo + PDF);
					if (new File(pdfFPathOri).exists()){
						request.setAttribute(PDFFPATHORI,pdfFPathOri);}
					if (new File(pdfFPathOriCopy).exists()){
						request.setAttribute(PDFFPATHORlCOPY,pdfFPathOriCopy);}
					if (new File(pdfFilePathCopy).exists()){
						request.setAttribute(PDFFPATHCOPY,pdfFilePathCopy);}
					if (new File(pdfFilePathDebit).exists()){
						request.setAttribute(PDFFPATHDEBIT,pdfFilePathDebit);}
					request.setAttribute(QUOTENUM, quoteNo);
					request.setAttribute(TYPE, type);
				} else if (type.equalsIgnoreCase("Motor")) // Office Delete
				{
					final String pdfFPathOri = request.getSession().getServletContext().getRealPath("/PDFFile/Motor/Schedule/PremiumSummary_Schedule_"
									+ quoteNo + PDF);
					final String pdfFPathOriCopy = request.getSession().getServletContext().getRealPath("/PDFFile/Motor/Schedule/Original_Copy/PremiumSummary_Schedule_"
									+ quoteNo + PDF);
					final String pdfFilePathCopy = request.getSession().getServletContext().getRealPath("/PDFFile/Motor/Schedule/Copy/PremiumSummary_Schedule_"
									+ quoteNo + PDF);
					final String pdfFilePathDebit = request.getSession().getServletContext().getRealPath("/PDFFile/Motor/Debit/PremiumSummary_Debit_"
									+ quoteNo + PDF);
					if (new File(pdfFPathOri).exists()){
						request.setAttribute(PDFFPATHORI,pdfFPathOri);}
					if (new File(pdfFPathOriCopy).exists()){
						request.setAttribute(PDFFPATHORlCOPY,pdfFPathOriCopy);}
					if (new File(pdfFilePathCopy).exists()){
						request.setAttribute(PDFFPATHCOPY,pdfFilePathCopy);}
					if (new File(pdfFilePathDebit).exists()){
						request.setAttribute(PDFFPATHDEBIT,pdfFilePathDebit);}
					request.setAttribute(QUOTENUM, quoteNo);
					request.setAttribute(TYPE, type);
				}
				final RequestDispatcher dispatcher = request.getRequestDispatcher("../admin/HomePdfDeleteInfo.jsp");
				dispatcher.forward(request, response);
			}
			if (path.equalsIgnoreCase("DeletePdf")) {

				final StringBuffer error = new StringBuffer();
				final String quoteNo = isNull(request.getParameter(QUOTENUM));
				final String type = request.getParameter(TYPE) == null ? HOME:request.getParameter(TYPE);
				final String[] res = request.getParameterValues("del") == null ? new String[0]: request.getParameterValues("del");
				if (HOME.equalsIgnoreCase(type)) {
					if (res.length > 0) {
						for (int i = 0; i < res.length; i++) {
							if (PDFFPATHORI.equalsIgnoreCase(res[i])) {
								final String pdfFPathOri = request.getSession().getServletContext().getRealPath("/PDFFile/Schedule/PremiumSummary_Schedule_"+ quoteNo + PDF);
								final File file1 = new File(pdfFPathOri);
								if (file1.exists()) {
									if (file1.canWrite()){
										file1.delete();
									}
									else{
										error.append(DEL);
										error.append(pdfFPathOri);
									}
										
								} else {
									error.append("Failed to Delete");error.append(pdfFPathOri);
								}
							}
							if (PDFFPATHORlCOPY.equalsIgnoreCase(res[i])) {
								final String pdfFPathOriCopy = request.getSession().getServletContext().getRealPath("/PDFFile/Schedule/Original_Copy/PremiumSummary_Schedule_"+ quoteNo + PDF);
								final File file2 = new File(pdfFPathOriCopy);
								if (file2.exists()) {
									if (file2.canWrite()){
										file2.delete();
									}
									else{
										error.append(DEL);
										error.append(pdfFPathOriCopy);
									}
										
								} else{
									error.append(FAIL);error.append(pdfFPathOriCopy);}
							}
							if (PDFFPATHCOPY.equalsIgnoreCase(res[i])) {
								final String pdfFilePathCopy = request.getSession().getServletContext().getRealPath("/PDFFile/Schedule/Copy/PremiumSummary_Schedule_"+ quoteNo + PDF);
								final File file3 = new File(pdfFilePathCopy);
								if (file3.exists()) {
									if (file3.canWrite()){
										file3.delete();
									}
									else{
										error.append(DEL);
										error.append(pdfFilePathCopy);
									}
								} else{
									error.append(FAIL);error.append(pdfFilePathCopy);}
							}
							if (PDFFPATHDEBIT.equalsIgnoreCase(res[i])) {
								final String pdfFilePathDebit = request.getSession().getServletContext().getRealPath("/PDFFile/Debit/PremiumSummary_Debit_"
												+ quoteNo + PDF);
								final File file4 = new File(pdfFilePathDebit);
								if (file4.exists()) {
									if (file4.canWrite()){
										file4.delete();
									}
									else{
										error.append(DEL);
										error.append(pdfFilePathDebit);
									}
								} else{
									error.append(FAIL);error.append(pdfFilePathDebit);
									}
							}
						}
					}
					final String pdfFPathOri = request.getSession().getServletContext().getRealPath("/PDFFile/Schedule/PremiumSummary_Schedule_"+ quoteNo + PDF);
					final String pdfFPathOriCopy = request.getSession().getServletContext().getRealPath("/PDFFile/Schedule/Original_Copy/PremiumSummary_Schedule_"+ quoteNo + PDF);
					final String pdfFilePathCopy = request.getSession().getServletContext().getRealPath("/PDFFile/Schedule/Copy/PremiumSummary_Schedule_"+ quoteNo + PDF);
					final String pdfFilePathDebit = request.getSession().getServletContext().getRealPath("/PDFFile/Debit/PremiumSummary_Debit_"+ quoteNo + PDF);
					if (new File(pdfFPathOri).exists()){
						request.setAttribute(PDFFPATHORI,	pdfFPathOri);}
					if (new File(pdfFPathOriCopy).exists()){
						request.setAttribute(PDFFPATHORlCOPY,pdfFPathOriCopy);}
					if (new File(pdfFilePathCopy).exists()){
						request.setAttribute(PDFFPATHCOPY,	pdfFilePathCopy);}
					if (new File(pdfFilePathDebit).exists()){
						request.setAttribute(PDFFPATHDEBIT,pdfFilePathDebit);}
				} else if (type.equalsIgnoreCase(TRAVEL)) {
					if (res.length > 0) {
						for (int i = 0; i < res.length; i++) {
							if (PDFFPATHORI.equalsIgnoreCase(res[i])) {
								final String pdfFPathOri = request.getSession().getServletContext().getRealPath("/TravelPDFFile/Schedule/PremiumSummary_Schedule_"	+ quoteNo + PDF);
								final File file1 = new File(pdfFPathOri);
								if (file1.exists()) {
									if (file1.canWrite()){
										file1.delete();
									}
									else{
										error.append(DEL);
										error.append(pdfFPathOri);
									}
								} else {
									error.append("Failed to Delete");error.append(pdfFPathOri);
								}
							}
							if (PDFFPATHORlCOPY.equalsIgnoreCase(res[i])) {
								final String pdfFPathOriCopy = request.getSession().getServletContext().getRealPath("/TravelPDFFile/Schedule/Original_Copy/PremiumSummary_Schedule_"+ quoteNo + PDF);
								final File file2 = new File(pdfFPathOriCopy);
								if (file2.exists()) {
									if (file2.canWrite()){
										file2.delete();
									}
									else{
										error.append(DEL);
										error.append(pdfFPathOriCopy);
									}
								} else{
									error.append(FAIL);error.append(pdfFPathOriCopy);}
							}
							if (PDFFPATHCOPY.equalsIgnoreCase(res[i])) {
								final String pdfFilePathCopy = request.getSession().getServletContext().getRealPath("/TravelPDFFile/Schedule/Copy/PremiumSummary_Schedule_"+ quoteNo + PDF);
								final File file3 = new File(pdfFilePathCopy);
								if (file3.exists()) {
									if (file3.canWrite()){
										file3.delete();
									}
									else{
										error.append(DEL);
										error.append(pdfFilePathCopy);
									}
								} else{
									error.append(FAIL);error.append(pdfFilePathCopy);}
							}
							if (PDFFPATHDEBIT.equalsIgnoreCase(res[i])) {
								final String pdfFilePathDebit = request.getSession().getServletContext().getRealPath("/TravelPDFFile/Debit/PremiumSummary_Debit_"+ quoteNo + PDF);
								final File file4 = new File(pdfFilePathDebit);
								if (file4.exists()) {
									if (file4.canWrite()){
										file4.delete();
									}
									else{
										error.append(DEL);
										error.append(pdfFilePathDebit);
									}
								} else{
									error.append(FAIL);error.append(pdfFilePathDebit);}
							}
						}
					}
					final String pdfFPathOri = request.getSession().getServletContext().getRealPath("/TravelPDFFile/Schedule/PremiumSummary_Schedule_"+ quoteNo + PDF);
					final 	String pdfFPathOriCopy = request.getSession().getServletContext().getRealPath("/TravelPDFFile/Schedule/Original_Copy/PremiumSummary_Schedule_"+ quoteNo + PDF);
					final 	String pdfFilePathCopy = request.getSession().getServletContext().getRealPath("/TravelPDFFile/Schedule/Copy/PremiumSummary_Schedule_"+ quoteNo + PDF);
					final 	String pdfFilePathDebit = request.getSession().getServletContext().getRealPath("/TravelPDFFile/Debit/PremiumSummary_Debit_"+ quoteNo + PDF);
					if (new File(pdfFPathOri).exists()){
						request.setAttribute(PDFFPATHORI,pdfFPathOri);}
					if (new File(pdfFPathOriCopy).exists()){
						request.setAttribute(PDFFPATHORlCOPY,pdfFPathOriCopy);}
					if (new File(pdfFilePathCopy).exists()){
						request.setAttribute(PDFFPATHCOPY,	pdfFilePathCopy);}
					if (new File(pdfFilePathDebit).exists()){
						request.setAttribute(PDFFPATHDEBIT,pdfFilePathDebit);}
				}
				else if (type.equalsIgnoreCase("Office")) {
					if (res.length > 0) {
						for (int i = 0; i < res.length; i++) {
							if (PDFFPATHORI.equalsIgnoreCase(res[i])) {
								final String pdfFPathOri = request.getSession().getServletContext().getRealPath("/PDFFile/Office/Schedule/PremiumSummary_Schedule_"+ quoteNo + PDF);
								final File file1 = new File(pdfFPathOri);
								if (file1.exists()) {
									if (file1.canWrite()){
										file1.delete();
									}
									else{
										error.append(DEL);
										error.append(pdfFPathOri);
									}
								} else {
									error.append("Failed to Delete");error.append(pdfFPathOri);
									
								}
							}
							if (PDFFPATHORlCOPY.equalsIgnoreCase(res[i])) {
								final String pdfFPathOriCopy = request.getSession().getServletContext().getRealPath("/PDFFile/Office/Schedule/Original_Copy/PremiumSummary_Schedule_"+ quoteNo + PDF);
								final File file2 = new File(pdfFPathOriCopy);
								if (file2.exists()) {
									if (file2.canWrite()){
										file2.delete();
									}
									else{
										error.append(DEL);
										error.append(pdfFPathOriCopy);
									}
								} else{
									error.append(FAIL);
									error.append(pdfFPathOriCopy);
									}
							}
							if (PDFFPATHCOPY.equalsIgnoreCase(res[i])) {
								final String pdfFilePathCopy = request.getSession().getServletContext().getRealPath("/PDFFile/Office/Schedule/Copy/PremiumSummary_Schedule_"
												+ quoteNo + PDF);
								final File file3 = new File(pdfFilePathCopy);
								if (file3.exists()) {
									if (file3.canWrite()){
										file3.delete();
									}
									else{
										error.append(DEL);
										error.append(pdfFilePathCopy);
									}
								} else{
									error.append(FAIL);
									error.append(pdfFilePathCopy);
									}
							}
							if (PDFFPATHDEBIT.equalsIgnoreCase(res[i])) {
								final String pdfFilePathDebit = request.getSession().getServletContext().getRealPath("/PDFFile/Office/Debit/PremiumSummary_Debit_"
												+ quoteNo + PDF);
								final File file4 = new File(pdfFilePathDebit);
								if (file4.exists()) {
									if (file4.canWrite()){
										file4.delete();}
									else{
										error.append(DEL);
										error.append(pdfFilePathDebit);
									}
										
								} else{
									error.append(FAIL);
									error.append(pdfFilePathDebit);
									}
							}
						}
					}
					final String pdfFPathOriginal = request.getSession().getServletContext().getRealPath("/PDFFile/Office/Schedule/PremiumSummary_Schedule_"+ quoteNo + PDF);
					final String pdfFPathOriCopy = request.getSession().getServletContext().getRealPath("/PDFFile/Office/Schedule/Original_Copy/PremiumSummary_Schedule_"+quoteNo + PDF);
					final String pdfFilePathCopy = request.getSession().getServletContext().getRealPath("/PDFFile/Office/Schedule/Copy/PremiumSummary_Schedule_"	+ quoteNo + PDF);
					final String pdfFilePathDebit = request.getSession().getServletContext().getRealPath("/PDFFile/Office/Debit/PremiumSummary_Debit_"+ quoteNo + PDF);
					if (new File(pdfFPathOriginal).exists()){
						request.setAttribute(PDFFPATHORI,pdfFPathOriginal);}
					if (new File(pdfFPathOriCopy).exists()){
						request.setAttribute(PDFFPATHORlCOPY,pdfFPathOriCopy);}
					if (new File(pdfFilePathCopy).exists()){
						request.setAttribute(PDFFPATHCOPY,	pdfFilePathCopy);}
					if (new File(pdfFilePathDebit).exists()){
						request.setAttribute(PDFFPATHDEBIT,pdfFilePathDebit);}
				}else if(type.equalsIgnoreCase("Motor"))
				{

					if (res.length > 0) {
						for (int i = 0; i < res.length; i++) {
							if (PDFFPATHORI.equalsIgnoreCase(res[i])) {
								final String pdfFPathOri = request.getSession().getServletContext().getRealPath("/PDFFile/Motor/Schedule/PremiumSummary_Schedule_"+ quoteNo + PDF);
								final File file1 = new File(pdfFPathOri);
								if (file1.exists()) {
									if (file1.canWrite()){
										file1.delete();
									}
									else{
										error.append(DEL);
										error.append(pdfFPathOri);
									}
								} else {
									error.append("Failed to Delete");error.append(pdfFPathOri);
									
								}
							}
							if (PDFFPATHORlCOPY.equalsIgnoreCase(res[i])) {
								final String pdfFPathOriCopy = request.getSession().getServletContext().getRealPath("/PDFFile/Motor/Schedule/Original_Copy/PremiumSummary_Schedule_"+ quoteNo + PDF);
								final File file2 = new File(pdfFPathOriCopy);
								if (file2.exists()) {
									if (file2.canWrite()){
										file2.delete();
									}
									else{
										error.append(DEL);
										error.append(pdfFPathOriCopy);
									}
								} else{
									error.append(FAIL);
									error.append(pdfFPathOriCopy);
									}
							}
							if (PDFFPATHCOPY.equalsIgnoreCase(res[i])) {
								final String pdfFilePathCopy = request.getSession().getServletContext().getRealPath("/PDFFile/Motor/Schedule/Copy/PremiumSummary_Schedule_"
												+ quoteNo + PDF);
								final File file3 = new File(pdfFilePathCopy);
								if (file3.exists()) {
									if (file3.canWrite()){
										file3.delete();
									}
									else{
										error.append(DEL);
										error.append(pdfFilePathCopy);
									}
								} else{
									error.append(FAIL);
									error.append(pdfFilePathCopy);
									}
							}
							if (PDFFPATHDEBIT.equalsIgnoreCase(res[i])) {
								final String pdfFilePathDebit = request.getSession().getServletContext().getRealPath("/PDFFile/Motor/Debit/PremiumSummary_Debit_"
												+ quoteNo + PDF);
								final File file4 = new File(pdfFilePathDebit);
								if (file4.exists()) {
									if (file4.canWrite()){
										file4.delete();}
									else{
										error.append(DEL);
										error.append(pdfFilePathDebit);
									}
										
								} else{
									error.append(FAIL);
									error.append(pdfFilePathDebit);
									}
							}
						}
					}
					final String pdfFPathOriginal = request.getSession().getServletContext().getRealPath("/PDFFile/Motor/Schedule/PremiumSummary_Schedule_"+ quoteNo + PDF);
					final String pdfFPathOriCopy = request.getSession().getServletContext().getRealPath("/PDFFile/Motor/Schedule/Original_Copy/PremiumSummary_Schedule_"+quoteNo + PDF);
					final String pdfFilePathCopy = request.getSession().getServletContext().getRealPath("/PDFFile/Motor/Schedule/Copy/PremiumSummary_Schedule_"	+ quoteNo + PDF);
					final String pdfFilePathDebit = request.getSession().getServletContext().getRealPath("/PDFFile/Motor/Debit/PremiumSummary_Debit_"+ quoteNo + PDF);
					if (new File(pdfFPathOriginal).exists()){
						request.setAttribute(PDFFPATHORI,pdfFPathOriginal);}
					if (new File(pdfFPathOriCopy).exists()){
						request.setAttribute(PDFFPATHORlCOPY,pdfFPathOriCopy);}
					if (new File(pdfFilePathCopy).exists()){
						request.setAttribute(PDFFPATHCOPY,	pdfFilePathCopy);}
					if (new File(pdfFilePathDebit).exists()){
						request.setAttribute(PDFFPATHDEBIT,pdfFilePathDebit);}
				
				}
				request.setAttribute(QUOTENUM, quoteNo);
				if (error.length()<=0 && (res.length > 0)){
					request.setAttribute("msg", "Succesfully Deleted");}
				else{
					request.setAttribute("error", error.toString());}
				final RequestDispatcher dispatcher = request.getRequestDispatcher("../admin/HomePdfDeleteInfo.jsp");
				dispatcher.forward(request, response);
			}			

		} catch (BaseException e) {
			System.out.println("Exception: "+e);
			throw new BaseException(e, "Error");
		}		
		System.out.println("Inside processResult---------");
		
	}
	
	public String isNull(final String content)throws BaseException{
		final StringBuffer contents = new StringBuffer();
		if(content==null){
			contents.append("");
		}
		else{
			contents.append(content);
		}
		return contents.toString();
	}
	public String isNull(final String content,final String value)throws BaseException{
		final StringBuffer contents = new StringBuffer();
		if(content==null||content.length()<=0){
			contents.append(value);
		}
		else{
			contents.append(content);
		}
		return contents.toString();
	}
	public Map validateInsureDate(final String day,final String months,final String years)throws BaseException{
		LogManager.info("Master validateInsureDate method()");
		LogManager.info("ENTER");
		final String[] Mon = { "JAN", "FEB", "MAR", "APR", "MAY", "JUN","JUL", "AUG", "SEP", "OCT", "NOV", "DEC" };
		final com.maan.Home.DataManipualtion.DataSelectCustomer dataSelect = new com.maan.Home.DataManipualtion.DataSelectCustomer();
		String EffectiveDate="";
		String resMsg;
		final Map datesValidate = new HashMap();
		final StringBuffer ErrorMsg = new StringBuffer();
		if (SELECT.equalsIgnoreCase(day) || SELECT.equalsIgnoreCase(months)|| SELECT.equalsIgnoreCase(years)) {
			ErrorMsg.append("Invalid Date");
		} else {
			final int temp  = Integer.parseInt(months);
			for (int i = 0; i <= Mon.length; i++) {
				if (i == temp) {
					EffectiveDate = day + "-" + Mon[--i] + "-"	+ years;
					break;
				}
			}
			resMsg = dataSelect.validateDateOfBirth(Integer.parseInt(years), Integer.parseInt(months), Integer.parseInt(day));
			if (resMsg.length() > 0) {
				ErrorMsg.append(resMsg);
				ErrorMsg.append(',');
			}
		}
		datesValidate.put(ERRORMSG,ErrorMsg);
		datesValidate.put(EFFECTDATE,EffectiveDate);
		return datesValidate;
	}
	public StringBuffer validateBackDates(final String day,final String months,final String years,final String loginId,final String proId)throws BaseException{
		final com.maan.Home.DataManipualtion.DataSelect dataSelect = new com.maan.Home.DataManipualtion.DataSelect();
		final StringBuffer ErrorMsg = new StringBuffer();
		final DateFunction dateFun = new DateFunction();
		final Calendar cal1 = Calendar.getInstance();
		cal1.set(Integer.parseInt(years), Integer.parseInt(months),Integer.parseInt(day));
		final Calendar cal2 = Calendar.getInstance();
		final String[][] serverDate = dataSelect.getTodaysDate();
		cal2.set(Integer.parseInt(serverDate[0][0]), Integer.parseInt(serverDate[0][1]), Integer.parseInt(serverDate[0][2]));
		long diff = 0;
		try{
			diff = dateFun.getDayDifference(cal1, cal2);
		}
		catch(Exception e){
			throw new BaseException(e,"Error");
		}
		int backDates = 0;
		final String backD = dataSelect.getBackDatesAllowed(loginId, proId);
		if (backD != null && !backD.equals("")){
			backDates = Integer.parseInt(backD);}
		if (diff > backDates) {
			if (backDates == 0) {
				ErrorMsg.append(runner.getErrormsg("265"));
				ErrorMsg.append(',');
			} else if (backDates > 0) {
				ErrorMsg.append(runner.getErrormsg("266"));
				ErrorMsg.append(Integer.toString(backDates));
				ErrorMsg.append(" days only,");
			}
		}
		return ErrorMsg;
	}
	public String getSessionPid(final HttpSession session, final String proId)throws BaseException{
		final StringBuffer proIdBuf = new StringBuffer();
		if (("".equals(proId) || proId.length() <= 0)&&session.getAttribute(PRODUCTID) != null) {
			proIdBuf.append((String) session.getAttribute(PRODUCTID));
			session.setAttribute(PRODUCTID, proIdBuf.toString());
		}else{
			proIdBuf.append(proId);
			session.setAttribute(PRODUCTID, proId);
		}
		return proIdBuf.toString();
	}
	public Map getFinanceDetails(final HttpServletRequest request,final String option)throws BaseException{
		final ValidationFormat validationFormat = new ValidationFormat();
		final Map financeDetails = new HashMap();
		final StringBuffer ErrorMsg = new StringBuffer(1000);
		final StringBuffer financeEmpty = new StringBuffer();
		final StringBuffer tphoneEmpty = new StringBuffer();
		final StringBuffer lamtEmpty=new StringBuffer();
		final StringBuffer poEmpty=new StringBuffer();
		String loanAmount = "";
		int loopJ;
		double loanAmounts = 0;
		final String addedValue = isNull(request.getParameter(ADDVAL));
		final int CountCollection = Integer.parseInt(addedValue);
		final String AAmount = isNull(request.getParameter("AAmount"));
		for (int i = 0; i < CountCollection; i++) {
			final String financerName = isNull(request.getParameter("FinancerName" + i));
			final String telePhone = isNull(request.getParameter("TelePhone" + i));
			loanAmount = isNull(request.getParameter("LoanAmount" + i));
			final String pobox = isNull(request.getParameter("pobox" + i));
			loopJ = i + 1;
			if("".equalsIgnoreCase(financerName) && "".equalsIgnoreCase(telePhone) 
					&& "".equalsIgnoreCase(loanAmount) && "".equalsIgnoreCase(pobox) && !"Add".equalsIgnoreCase(option)){
				financeDetails.put("insertOption", "don't continue");
			}else{
				if (financerName.equalsIgnoreCase("")) {
					financeEmpty.append(SPACE);
					financeEmpty.append(loopJ);
				}
				else{
					financeDetails.put("FinancerName" + i, financerName);
				}
				if (telePhone.equalsIgnoreCase("")){
					tphoneEmpty.append(SPACE);
					tphoneEmpty.append(loopJ);
				} else if (validationFormat.isDigitValidationFormat(telePhone)) {
					financeDetails.put("TelePhone" + i, telePhone);
				}
				else{
					ErrorMsg.append(runner.getErrormsg("203") + SPACE + loopJ+ SPACES);
				}
				if (loanAmount.equalsIgnoreCase("")) {
					lamtEmpty.append(SPACE);
					lamtEmpty.append(loopJ);
				} else if (validationFormat.isDigitValidationFormat(loanAmount)) {
					loanAmounts = loanAmounts+ Double.parseDouble(loanAmount);
					financeDetails.put("LoanAmount" + i, loanAmount);
				}else {
					ErrorMsg.append(runner.getErrormsg("204"));
					ErrorMsg.append(SPACE);
					ErrorMsg.append(Integer.toString(loopJ));
					ErrorMsg.append(',');
				}
				if (pobox.equalsIgnoreCase("")) {
					poEmpty.append(SPACE);
					poEmpty.append(loopJ);
				} else if (validationFormat.isDigitValidationFormat(pobox)) {
					financeDetails.put("pobox" + i, pobox);
				}
				else {
					ErrorMsg.append(runner.getErrormsg("248") + SPACE + loopJ+ SPACES);
				}
			}
			financeDetails.put("insertOption", "continue");
		}
		if (financeEmpty.length()>0) {
			ErrorMsg.append(runner.getErrormsg("269") + SPACE+ financeEmpty.toString()+ SPACES);
		}
		if (tphoneEmpty.length()>0) {
			ErrorMsg.append(runner.getErrormsg("270") + SPACE+ tphoneEmpty.toString()+ SPACES);
		}
		if (lamtEmpty.length()>0) {
			ErrorMsg.append(runner.getErrormsg("271") + SPACE+ lamtEmpty.toString()+ SPACES);
		}
		if (poEmpty.length()>0) {
			ErrorMsg.append(runner.getErrormsg("272") + SPACE+ poEmpty.toString()+ SPACES);
		}
		if (ErrorMsg.length() <= 0&&option.equalsIgnoreCase("Yes")&&loanAmount.length()>0) {
			final double loanAmounts1 = loanAmounts;
			final double aAmounts = Double.parseDouble(AAmount);
			if (loanAmounts1 > aAmounts) {
				ErrorMsg.append(runner.getErrormsg("249") + SPACE+ aAmounts+ SPACES);
			}
		}	
		financeDetails.put(ERRORMSG, ErrorMsg);
		return financeDetails;
	}
	
} // Class