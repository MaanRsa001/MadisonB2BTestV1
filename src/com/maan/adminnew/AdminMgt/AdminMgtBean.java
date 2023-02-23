package com.maan.adminnew.AdminMgt;

import java.util.List;
import java.util.Map;

public class AdminMgtBean {
	private String uid;
	private String utype;
	private String uname;
	private String loginID;
	private String pwd;
	private String rpassword;
	private String branch;
	private String product;
	private String mname;
	private String urlPath;
	private String parent;
	private String status;
	private String from;
	private String from1;
	private String fromm1;
	private String fromm2;
	private String reqFrom;
	private String mode;
	private String mode1;
	private String index="0";
	private String mid;
	private String broker;
	private String[] productID;
	private String[] uwgrade; 
	private String searchBy;
	private String searchValue;
	private String email;
	private String mobileNo;
	private String display;
	
	private String oneOffPortFolio;
	private String openCoverPortFolio;
	private String oneOffPremium;
	private String openCoverPremium;
	private String oneOffPending;
	private String oneOffAccepted;
	private String oneOffRejected;
	private String openCoverPending;
	private String openCoverAccepted;
	private String openCoverRejected;
	
	private String noOfQuote;
	private String customerLinkedQuote;
	private String policyAccept;
	private String policyReject;
	private String ccPending;
	private String ssPending;
	private String uwPending;
	
	private String accessKey;
	private String secretKey;
	private String profileId;
	private String expiryDate;
	private String currencyType;
	private String intiExpiryDate;
	private String intiMobileNo;
	private String intiEMailId;
	private String remarks;
	private String paymentId;
	
	private List<Map<String,Object>> paymentMasterList;
	 
	private String clausesType;
	private String clauseId;
	private String branchId;
	private String selProducts; 
	private String menuId[];
	private String onlineYN;
	 
	  private List<Object> optionalCoverList;
	    private List<String> optionalId;
	    private List<String> optionalDesc;
	    private String effectDate;
	    private String coverNo;
	    

	    private String firstName;
	    private String middleName;
	    private String lastName;
	    
	    public List<Object> getOptionalCoverList() {
			return optionalCoverList;
		}
		public void setOptionalCoverList(List<Object> optionalCoverList) {
			this.optionalCoverList = optionalCoverList;
		}
		public List<String> getOptionalId() {
			return optionalId;
		}
		public void setOptionalId(List<String> optionalId) {
			this.optionalId = optionalId;
		}
		public List<String> getOptionalDesc() {
			return optionalDesc;
		}
		public void setOptionalDesc(List<String> optionalDesc) {
			this.optionalDesc = optionalDesc;
		}
		public String getEffectDate() {
			return effectDate;
		}
		public void setEffectDate(String effectDate) {
			this.effectDate = effectDate;
		}
		public List<Object> getSelectedCovers() {
			return selectedCovers;
		}
		public void setSelectedCovers(List<Object> selectedCovers) {
			this.selectedCovers = selectedCovers;
		}
		private String proposalNo;
	    private List<Object> selectedCovers;
	    private String openCoverNo;
	private List<Map<String,Object>> commodityList;
	public String getClausesType() {
		return clausesType;
	}
	public void setClausesType(String clausesType) {
		this.clausesType = clausesType;
	}
	public String getClauseId() {
		return clauseId;
	}
	public void setClauseId(String clauseId) {
		this.clauseId = clauseId;
	}
	public List<Map<String, Object>> getCommodityList() {
		return commodityList;
	}
	public void setCommodityList(List<Map<String, Object>> commodityList) {
		this.commodityList = commodityList;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getUtype() {
		return utype;
	}
	public void setUtype(String utype) {
		this.utype = utype;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getLoginID() {
		return loginID;
	}
	public void setLoginID(String loginID) {
		this.loginID = loginID;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	public String getProduct() {
		return product;
	}
	public void setProduct(String product) {
		this.product = product;
	}
	public String getMname() {
		return mname;
	}
	public void setMname(String mname) {
		this.mname = mname;
	}
	public String getUrlPath() {
		return urlPath;
	}
	public void setUrlPath(String urlPath) {
		this.urlPath = urlPath;
	}
	public String getParent() {
		return parent;
	}
	public void setParent(String parent) {
		this.parent = parent;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getFrom1() {
		return from1;
	}
	public void setFrom1(String from1) {
		this.from1 = from1;
	}
	public String getFromm1() {
		return fromm1;
	}
	public void setFromm1(String fromm1) {
		this.fromm1 = fromm1;
	}
	public String getFromm2() {
		return fromm2;
	}
	public void setFromm2(String fromm2) {
		this.fromm2 = fromm2;
	}
	public String getReqFrom() {
		return reqFrom;
	}
	public void setReqFrom(String reqFrom) {
		this.reqFrom = reqFrom;
	}
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	public String getMode1() {
		return mode1;
	}
	public void setMode1(String mode1) {
		this.mode1 = mode1;
	}
	public String getIndex() {
		return index;
	}
	public void setIndex(String index) {
		this.index = index;
	}
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	public String getBroker() {
		return broker;
	}
	public void setBroker(String broker) {
		this.broker = broker;
	}
	public String[] getProductID() {
		return productID;
	}
	public void setProductID(String[] productID) {
		this.productID = productID;
	}
	public String getSearchBy() {
		return searchBy;
	}
	public void setSearchBy(String searchBy) {
		this.searchBy = searchBy;
	}
	public String getSearchValue() {
		return searchValue;
	}
	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getOneOffPortFolio() {
		return oneOffPortFolio;
	}
	public void setOneOffPortFolio(String oneOffPortFolio) {
		this.oneOffPortFolio = oneOffPortFolio;
	}
	public String getOpenCoverPortFolio() {
		return openCoverPortFolio;
	}
	public void setOpenCoverPortFolio(String openCoverPortFolio) {
		this.openCoverPortFolio = openCoverPortFolio;
	}
	public String getOneOffPremium() {
		return oneOffPremium;
	}
	public void setOneOffPremium(String oneOffPremium) {
		this.oneOffPremium = oneOffPremium;
	}
	public String getOpenCoverPremium() {
		return openCoverPremium;
	}
	public void setOpenCoverPremium(String openCoverPremium) {
		this.openCoverPremium = openCoverPremium;
	}
	public String getOneOffPending() {
		return oneOffPending;
	}
	public void setOneOffPending(String oneOffPending) {
		this.oneOffPending = oneOffPending;
	}
	public String getOneOffAccepted() {
		return oneOffAccepted;
	}
	public void setOneOffAccepted(String oneOffAccepted) {
		this.oneOffAccepted = oneOffAccepted;
	}
	public String getOneOffRejected() {
		return oneOffRejected;
	}
	public void setOneOffRejected(String oneOffRejected) {
		this.oneOffRejected = oneOffRejected;
	}
	public String getOpenCoverPending() {
		return openCoverPending;
	}
	public void setOpenCoverPending(String openCoverPending) {
		this.openCoverPending = openCoverPending;
	}
	public String getOpenCoverAccepted() {
		return openCoverAccepted;
	}
	public void setOpenCoverAccepted(String openCoverAccepted) {
		this.openCoverAccepted = openCoverAccepted;
	}
	public String getOpenCoverRejected() {
		return openCoverRejected;
	}
	public void setOpenCoverRejected(String openCoverRejected) {
		this.openCoverRejected = openCoverRejected;
	}
	public void setProposalNo(String proposalNo) {
		this.proposalNo = proposalNo;
	}
	public String getProposalNo() {
		return proposalNo;
	}
	public void setOpenCoverNo(String openCoverNo) {
		this.openCoverNo = openCoverNo;
	}
	public String getOpenCoverNo() {
		return openCoverNo;
	}
	public void setCoverNo(String coverNo) {
		this.coverNo = coverNo;
	}
	public String getCoverNo() {
		return coverNo;
	}
	public void setUwgrade(String[] uwgrade) {
		this.uwgrade = uwgrade;
	}
	public String[] getUwgrade() {
		return uwgrade;
	}
	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}
	public String getBranchId() {
		return branchId;
	}
	public void setSelProducts(String selProducts) {
		this.selProducts = selProducts;
	}
	public String getSelProducts() {
		return selProducts;
	}
	public void setMenuId(String menuId[]) {
		this.menuId = menuId;
	}
	public String[] getMenuId() {
		return menuId;
	}
	public String getNoOfQuote() {
		return noOfQuote;
	}
	public void setNoOfQuote(String noOfQuote) {
		this.noOfQuote = noOfQuote;
	}
	public String getCustomerLinkedQuote() {
		return customerLinkedQuote;
	}
	public void setCustomerLinkedQuote(String customerLinkedQuote) {
		this.customerLinkedQuote = customerLinkedQuote;
	}
	public String getPolicyAccept() {
		return policyAccept;
	}
	public void setPolicyAccept(String policyAccept) {
		this.policyAccept = policyAccept;
	}
	public String getPolicyReject() {
		return policyReject;
	}
	public void setPolicyReject(String policyReject) {
		this.policyReject = policyReject;
	}
	public String getCcPending() {
		return ccPending;
	}
	public void setCcPending(String ccPending) {
		this.ccPending = ccPending;
	}
	public String getSsPending() {
		return ssPending;
	}
	public void setSsPending(String ssPending) {
		this.ssPending = ssPending;
	}
	public String getUwPending() {
		return uwPending;
	}
	public void setUwPending(String uwPending) {
		this.uwPending = uwPending;
	}
	public String getAccessKey() {
		return accessKey;
	}
	public void setAccessKey(String accessKey) {
		this.accessKey = accessKey;
	}
	public String getSecretKey() {
		return secretKey;
	}
	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}
	public String getProfileId() {
		return profileId;
	}
	public void setProfileId(String profileId) {
		this.profileId = profileId;
	}
	public String getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}
	public String getCurrencyType() {
		return currencyType;
	}
	public void setCurrencyType(String currencyType) {
		this.currencyType = currencyType;
	}
	public String getIntiExpiryDate() {
		return intiExpiryDate;
	}
	public void setIntiExpiryDate(String intiExpiryDate) {
		this.intiExpiryDate = intiExpiryDate;
	}
	public String getIntiMobileNo() {
		return intiMobileNo;
	}
	public void setIntiMobileNo(String intiMobileNo) {
		this.intiMobileNo = intiMobileNo;
	}
	public String getIntiEMailId() {
		return intiEMailId;
	}
	public void setIntiEMailId(String intiEMailId) {
		this.intiEMailId = intiEMailId;
	}
	public void setPaymentMasterList(List<Map<String,Object>> paymentMasterList) {
		this.paymentMasterList = paymentMasterList;
	}
	public List<Map<String,Object>> getPaymentMasterList() {
		return paymentMasterList;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}
	public String getPaymentId() {
		return paymentId;
	}
	public void setDisplay(String display) {
		this.display = display;
	}
	public String getDisplay() {
		return display;
	}
	public void setRpassword(String rpassword) {
		this.rpassword = rpassword;
	}
	public String getRpassword() {
		return rpassword;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setOnlineYN(String onlineYN) {
		this.onlineYN = onlineYN;
	}
	public String getOnlineYN() {
		return onlineYN;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
 
  
}
