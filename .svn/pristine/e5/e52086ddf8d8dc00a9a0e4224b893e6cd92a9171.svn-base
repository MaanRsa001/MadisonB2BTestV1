package com.maan.adminnew.report;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import com.maan.adminnew.common.CommonService;
import com.maan.common.DBConstants;
import com.maan.common.LogManager;
import com.maan.common.Validation;
import com.maan.report.JasperReports;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class ReportAction extends ActionSupport implements ModelDriven<ReportBean>
{
	private static final long serialVersionUID = 1L;
	ReportBean bean = new ReportBean();
	Validation validation=new Validation();
	ReportService service=new ReportService();
	final CommonService cservice=new CommonService();
	Map<String, Object> session=ActionContext.getContext().getSession();
	HttpServletRequest request= ServletActionContext.getRequest();
	HttpServletResponse responce= ServletActionContext.getResponse();
	List<Object> transDetList=null;
	ServletContext context=request.getSession().getServletContext();
	String branchCode=(String)session.get("BranchCode")==null?(String)session.get("BelongingBranch"):(String)session.get("BranchCode");
	String belongingBranch = (String)session.get("BelongingBranch");
	String login_id=(String)session.get("user");
	String userType=(String)session.get("usertype");
	String productId=(String)session.get("product_id");
	String appId=(String)session.get("AppId");
	List<Object> branchName=new ArrayList<Object>();
	List<Object> policyList=new ArrayList<Object>();
	List<Map<String,String>> productList=new ArrayList<Map<String,String>>();
	List<Object> uwList=new ArrayList<Object>();
	List<Object> brokerList=new ArrayList<Object>();
	List<Object> lcsmartList=new ArrayList<Object>();
	List<Object> coverList=new ArrayList<Object>();
	List<Object> smartList=new ArrayList<Object>();
	List<Object> branchList=new ArrayList<Object>();
	List<Object> exchangeList=new ArrayList<Object>();
	List<Object> consigneeList=new ArrayList<Object>();
	//for link in opencoverReport
	private List<Object> certificateList=new ArrayList<Object>();
	private InputStream inputStream;
	/*private String fileName;
	
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFileName() {
		return fileName;
	}*/
	public List<Object> getExchangeList() {
		return exchangeList;
	}
	public void setExchangeList(List<Object> exchangeList) {
		this.exchangeList = exchangeList;
	}
	public List<Object> getBranchName() {
		return branchName;
	}
	public void setBranchName(List<Object> branchName) {
		this.branchName = branchName;
	}
	public List<Object> getBranchList() {
		return branchList;
	}
	public void setBranchList(List<Object> branchList) {
		this.branchList = branchList;
	}
	public void setCoverList(List<Object> coverList) {
		this.coverList = coverList;
	}
	public List<Object> getPolicyList() {
		return policyList;
	}
	public void setPolicyList(List<Object> policyList) {
		this.policyList = policyList;
	}
	public List<Map<String,String>> getProductList() {
		return productList;
	}
	public void setProductList(List<Map<String,String>> productList) {
		this.productList = productList;
	}
	public List<Object> getConsigneeList() {
		return consigneeList;
	}
	public void setConsigneeList(List<Object> consigneeList) {
		this.consigneeList = consigneeList;
	}
	public List <Object> getCountryList(){
    	return cservice.getCountries(branchCode);
    }
	public List<Object> getUwList() {
		return uwList;
	}
	public void setUwList(List<Object> uwList) {
		this.uwList = uwList;
	}
	public List<Object> getBrokerList() {
		return brokerList;
	}
	public void setBrokerList(List<Object> brokerList) {
		this.brokerList = brokerList;
	}
	public List<Object> getCommodityList(){
		return service.getCommodityList(belongingBranch);
	}
	public List<Object> getCoverList(){
		return coverList;
	}
	public List<Object> getTransportModeList(){
		return service.getTransportModeList(belongingBranch);
	}

	/*public String schedule(){
    	LogManager.push("schedule()===>Enter");
		String returnResult="schedule";
		scheduleMap=service.schedule(bean.getBroker());
    	LogManager.push("schedule()===>Exit");
		return returnResult;
	}*/
	
	public List<Object> getLcsmartList() {
		return lcsmartList;
	}
	public void setLcsmartList(List<Object> lcsmartList) {
		this.lcsmartList = lcsmartList;
	}
	public List<Object> getSmartList() {
		return smartList;
	}
	public void setSmartList(List<Object> smartList) {
		this.smartList = smartList;
	}
	public String policy(){
		uwList=service.getUwList(branchCode);
		productList=cservice.getProductsDET(branchCode,"");
		brokerList=cservice.getAdminBrokerList(branchCode, appId);
		if(StringUtils.isNotBlank(bean.getStartDate()))
			bean.setStartDate(dateFormat(bean.getStartDate()));
		if(StringUtils.isNotBlank(bean.getEndDate()))
			bean.setEndDate(dateFormat(bean.getEndDate()));
		return "policy";
	}
	
	public String lcSmart(){
		String returnResult="lcsmart";
		brokerList=cservice.getAdminBrokerList(branchCode, appId);
		if(bean.getReqFrom()!=null && bean.getBroker()!=null){
			bean.setBcode(branchCode);
			lcsmartList=service.getLcSmartList(bean);
			if(bean.getReqFrom()!=null && !"".equals(bean.getReqFrom()))
				returnResult="ajax";
			if("ajax".equals(bean.getFrom1()))
				returnResult="lcsmart";
		}return returnResult;
	}
	
	public String lcSmartJasper() {
		try {
			bean.setFileName("LCSmartReport");
			JasperReports jr = new JasperReports();
			String type = "";
			if("ocm.MISSIPPI_OPENCOVER_NO".equalsIgnoreCase(bean.getSearchBy())) {
				type = "OPENCOVER";
			}
			else if("oclm.LC_NUMBER".equalsIgnoreCase(bean.getSearchBy())) {
				type = "LCNUMBER";
			}
			ByteArrayOutputStream bos=new ByteArrayOutputStream();
			jr.LCSmartReport(bean.getBroker(), branchCode, type, bean.getSearchValue(), bean.getDownloadType(), bos);
			inputStream=new ByteArrayInputStream(bos.toByteArray());
		}
		catch(Exception exception) {
			LogManager.debug("Exception @ lcSmartJasper { " + exception + " } ");
		}
		if("excel".equals(bean.getDownloadType()))
		{
			return "excel";
		}
		if("pdf".equals(bean.getDownloadType()))
		{
			return "pdf";
		}
		return "download";
	}
	
	public String smart(){
		String returnResult="smart";
		brokerList=service.getAdminBrokerList(branchCode, appId);
		productList=cservice.getProductsDET(branchCode,"");
		coverList=service.getCoverList(branchCode);
		bean.setCountrySmartList(service.getCountrySmartList());
		if(StringUtils.isNotBlank(bean.getStartDate()))
			bean.setStartDate(dateFormat(bean.getStartDate()));
		if(StringUtils.isNotBlank(bean.getEndDate()))
			bean.setEndDate(dateFormat(bean.getEndDate()));
		if(StringUtils.isNotBlank(bean.getBroker()))
			bean.setBrokers(bean.getBroker().split(","));
		if(StringUtils.isNotBlank(bean.getRags()))
			bean.setRag(bean.getRags().split(","));
		if(StringUtils.isNotBlank(bean.getOrginCountry())){
			bean.setOrginCountry(bean.getOrginCountry().replaceAll(", ", ","));
			bean.setOrginCountry(bean.getOrginCountry().replaceAll(" ,", ","));
			bean.setOrginCountries(bean.getOrginCountry().split(","));
		}if(StringUtils.isNotBlank(bean.getDestCountry())){
			bean.setDestCountry(bean.getDestCountry().replaceAll(", ", ","));
			bean.setDestCountry(bean.getDestCountry().replaceAll(" ,", ","));
			bean.setDestCountries(bean.getDestCountry().split(","));
		}
		return returnResult;
	}
	
	public String getRePolicy(){
    	LogManager.push("policy()===>Enter");
		String returnResult="ajax";
    	if("reportBR".equals(bean.getMode1())){
    		bean.setReqFrom("policylistBR");
    		bean.setIndex("1");
    	}else{
    		bean.setIndex("0");
    		bean.setReqFrom("policylistUW");
    	}
    	bean.setBranch(branchCode);
		validatepolicy();
		try{
			if(!hasActionErrors()){
				Object obj[]=new Object[]{bean.getProductID(),bean.getStartDate(), bean.getEndDate(), bean.getBroker(), bean.getMode1(),branchCode};
				policyList=service.getPolicyReport(obj);
				bean.setReqFrom("policylist");
				if("ajax".equals(bean.getFrom1())){
					uwList=service.getUwList(branchCode);
					productList=cservice.getProductsDET(branchCode,"");
					brokerList=cservice.getAdminBrokerList(branchCode, appId);
					returnResult="policy";
				}
			}else{
				uwList=service.getUwList(branchCode);
				productList=cservice.getProductsDET(branchCode,"");
				brokerList=cservice.getAdminBrokerList(branchCode, appId);
			}
			LogManager.push("policy()===>Exit");
		}catch(Exception e){
			e.printStackTrace();
		}
		return returnResult;
	}
	

	public String smartlist(){
		//String rags="";
		String returnResult="";
		if(bean.getOrginCountries().length<=0) {
			bean.setOrginCountries(new String[]{"0"}); 
		}
		validatesmart();
		if(!hasActionErrors()){
			if(Arrays.asList(bean.getBrokers()).contains("ALL"))
				bean.setBroker("ALL");
			else
				bean.setBroker(StringUtils.join(bean.getBrokers(), ","));
			bean.setRags(StringUtils.join(bean.getRag(),","));
			if(Arrays.asList(bean.getOrginCountries()).contains("0"))
				bean.setOrginCountry("ALL");
			else
				bean.setOrginCountry(StringUtils.join(bean.getOrginCountries(),","));
			if(Arrays.asList(bean.getDestCountries()).contains("0"))
				bean.setDestCountry("ALL");
			else
				bean.setDestCountry(StringUtils.join(bean.getDestCountries(),","));
			
			/*Object obj[]=new Object[]{bean.getBroker(),bean.getTransportId().replaceAll(" ", ""), StringUtils.isBlank(bean.getCoverId())?"":bean.getCoverId().replaceAll(" ", "") , bean.getBusType(), bean.getCommodity(), bean.getRags(), 
									bean.getStartDate(), bean.getEndDate(), bean.getProductID(), branchCode, bean.getOrginCountry(), bean.getDestCountry()};*/
			Object obj[]=new Object[]{bean.getBroker(),bean.getTransportId().replaceAll(" ", ""), StringUtils.isBlank(bean.getCoverId())?"":bean.getCoverId().replaceAll(" ", "") , bean.getCommodity(), 
					bean.getStartDate(), bean.getEndDate(), bean.getProductID(), branchCode, bean.getOrginCountry(), bean.getDestCountry()};
			
			/*
			if("all".equals(bean.getOrginCountry()))
				bean.setOrginCountry("select distinct country_id from country_master");
			if("all".equals(bean.getDestCountry()))
				bean.setDestCountry("select distinct country_id from country_master");
			if(bean.getRags()!=null && bean.getRags().length()>0)
				bean.setRags(" and mrd.rag in ('"+bean.getRags().replaceAll(",", "','")+"')");
			else bean.setRags("");
			bean.setTransportId(" and md.mode_of_transport in ('"+bean.getTransportId().replaceAll(",", "','")+"')");
			bean.setCoverId(" and md.cover_id in ('"+bean.getCoverId().replaceAll(",", "','")+"')");
			bean.setCommodity(" and mrd.commodity_id in ('"+bean.getCommodity().replaceAll(",", "','")+"')");
			if(!"all".equals(bean.getBroker())){
				bean.setBroker(bean.getBroker().replaceAll(" ,",","));
				bean.setBroker(bean.getBroker().replaceAll(", ",","));
				bean.setBroker(bean.getBroker().trim());
				bean.setBroker(" and log.LOGIN_ID in ('"+bean.getBroker().replaceAll(",","','").trim()+"')");
			}*/
			
			//Object obj[]=new Object[]{branchCode, branchCode, bean.getBroker(), bean.getProductID(), bean.getTransportId(), bean.getCoverId(), bean.getBusType(), bean.getOrginCountry(), bean.getDestCountry(), bean.getCommodity(), bean.getRags(), "'"+bean.getStartDate()+"'", "'"+bean.getEndDate()+"'"};
			
			smartList=service.getSmartList(obj);
			returnResult="smartList";
		}else 
			returnResult=smart();
		return returnResult;
	}
	
	public String branch(){
		String returnResult="branch";
		productList=cservice.getProductsDET(branchCode,"");
		branchName=service.getBranchName(branchCode,login_id);
		if(StringUtils.isNotBlank(bean.getStartDate()))
			bean.setStartDate(dateFormat(bean.getStartDate()));
		if(StringUtils.isNotBlank(bean.getEndDate()))
			bean.setEndDate(dateFormat(bean.getEndDate()));
		return returnResult;
	}
	public String branchResult(){
		String returnResult="branch";
		validbranch();
		if(!hasActionErrors()){
			//bean.setBranch(branchCode);
			//bean.setLoginBranch(branchCode);
			branchList=service.getBranchReport(bean);
			bean.setReqFrom("branchlist");
		}else{
			productList=cservice.getProductsDET(branchCode,"");
			branchName=service.getBranchName(branchCode,login_id);
			bean.setReqFrom(null);
		}
		return returnResult;
	}
	
	public String exchange(){
		String returnResult="exchange";
		return returnResult;
	}
	public String exchangeResult(){
		String returnResult="exchange";
		validateexchange();
		if(!hasActionErrors()){
			exchangeList=service.getExchangeReport(bean);
			bean.setReqFrom("exchangelist");
		}
		return returnResult;
	}
	public String branchReportJasper(){
		String result=StringUtils.isBlank(bean.getDownloadType())?"excel":bean.getDownloadType();
		try{
			bean.setFileName("BranchReports");
			if("reportBR".equals(bean.getMode1()) || "reportUW".equals(bean.getMode1())){
				uwbranchReportJasper();
			}else {
				ByteArrayOutputStream bos=new ByteArrayOutputStream();
				Object args[]=new Object[6];
				args[0]=bean.getStartDate();
				args[1]=bean.getEndDate();
				args[2]=bean.getProductID();
				args[3]=bean.getReportStatus();
				args[4]=StringUtils.isBlank(bean.getBranch())?branchCode:bean.getBranch();
				args[5]=("reportUW".equalsIgnoreCase(bean.getMode1()) || "reportBR".equalsIgnoreCase(bean.getMode1()))?bean.getLoginId():"ALL";
				new JasperReports().BranchReport(args,result,bos);
				inputStream=new ByteArrayInputStream(bos.toByteArray());
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	public String uwbranchReportJasper(){
		String result=StringUtils.isBlank(bean.getDownloadType())?"excel":bean.getDownloadType();
		try{
			bean.setFileName("BranchReports");
			ByteArrayOutputStream bos=new ByteArrayOutputStream();
			Object args[]=new Object[7];
			args[0]=bean.getStartDate();
			args[1]=bean.getEndDate();
			args[2]=bean.getProductID();
			args[3]=bean.getReportStatus();
			args[4]=branchCode;
			args[5]=("reportUW".equalsIgnoreCase(bean.getMode1()) || "reportBR".equalsIgnoreCase(bean.getMode1()))?bean.getLoginId():"ALL";			
			if(StringUtils.isNotBlank(login_id) && !"reportUW".equalsIgnoreCase(bean.getMode1())){
				args[6]=bean.getLoginId();
			}else if(StringUtils.isNotBlank(bean.getBroker()) && !"ALL".equals(bean.getBroker()) &&  "reportUW".equalsIgnoreCase(bean.getMode1())){
				args[6]=bean.getBroker();
			}
			new JasperReports().uwBranchReport(args,result,bos);
			inputStream=new ByteArrayInputStream(bos.toByteArray());
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	public String commoditySelect(){
		return "commoditySelect";
	}
	
	public ReportBean getModel() {
		if(StringUtils.isEmpty(bean.getBranch())){
			if(StringUtils.isNotBlank(branchCode)){
				bean.setBranch(branchCode);
			}else{
				bean.setBranch(belongingBranch);
			}
		}
		if(StringUtils.isEmpty(bean.getProductID())){
			bean.setProductID(productId);
		}
		return bean;
	}
	public void validatepolicy() {
		 if(StringUtils.isBlank(bean.getStartDate())){
    		addActionError(getText("error.policy.report.startdate.invalid"));
    	}if(StringUtils.isBlank(bean.getEndDate())){
    		addActionError(getText("error.policy.report.enddate.invalid"));
    	}if(StringUtils.isBlank(bean.getBroker())){
    		if("reportBR".equals(bean.getMode1()))
    			addActionError(getText("error.policy.report.broker.invalid"));
    		else addActionError(getText("error.policy.report.uw.invalid"));
    	}else
        	bean.setBroker(bean.getBroker().substring(bean.getBroker().charAt(0)==','?1:0));
    	if(StringUtils.isBlank(bean.getProductID())){
    		addActionError(getText("error.policy.report.product.invalid"));
    	}if(!hasActionErrors()){
			try{
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		    	Date sdate = sdf.parse(bean.getStartDate());
		    	Date edate = sdf.parse(bean.getEndDate());
		    	if(sdate.after(edate))
		    		addActionError("End date should be greater than or equal to Start Date");
			}catch(Exception e){
				e.printStackTrace();
			}
		} if(hasActionErrors()){
			if(StringUtils.isNotBlank(bean.getStartDate()))
				bean.setStartDate(dateFormat(bean.getStartDate()));
			if(StringUtils.isNotBlank(bean.getEndDate()))
				bean.setEndDate(dateFormat(bean.getEndDate()));
		}
    }
	
	public void validatesmart() {
		 if(StringUtils.isBlank(bean.getStartDate()))
			 addActionError(getText("error.policy.report.startdate.invalid"));
		 if(StringUtils.isBlank(bean.getEndDate()))
			 addActionError(getText("error.policy.report.enddate.invalid"));
		 if(StringUtils.isBlank(bean.getProductID()))
  			 addActionError(getText("error.policy.report.product.invalid"));
		 if(bean.getBrokers().length<=0)
  			 addActionError(getText("error.policy.report.broker.invalid"));
		 if(StringUtils.isBlank(bean.getCommodity()))
  			 addActionError(getText("error.policy.report.commodity.invalid"));
		 if(StringUtils.isBlank(bean.getBusType()))
  			 addActionError(getText("error.policy.report.bustype.invalid"));
		 if(bean.getOrginCountries().length<=0)
  			 addActionError(getText("error.policy.report.origincountry.invalid"));
		 if(bean.getDestCountries().length<=0)
  			 addActionError(getText("error.policy.report.destcountry.invalid"));
		 if(StringUtils.isBlank(bean.getTransportId()) && StringUtils.isBlank(bean.getCoverId()))
  			 addActionError(getText("error.policy.report.coverid.invalid"));
		 if(!hasActionErrors()){
			try{
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		    	Date sdate = sdf.parse(bean.getStartDate());
		    	Date edate = sdf.parse(bean.getEndDate());
		    	if(sdate.after(edate))
		    		addActionError("End date should be greater than or equal to Start Date");
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	public void validbranch() {
		if(StringUtils.isBlank(bean.getStartDate())){
			addActionError(getText("error.policy.report.startdate.invalid"));
		}if(StringUtils.isBlank(bean.getEndDate())){
			addActionError(getText("error.policy.report.enddate.invalid"));
		}if(!hasActionErrors()){
			try{
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		    	Date sdate = sdf.parse(bean.getStartDate());
		    	Date edate = sdf.parse(bean.getEndDate());
		    	if(sdate.after(edate))
		    		addActionError("End date should be greater than or equal to Start Date");
			}catch(Exception e){
				e.printStackTrace();
			}
		} if(hasActionErrors()){
			if(StringUtils.isNotBlank(bean.getStartDate()))
				bean.setStartDate(dateFormat(bean.getStartDate()));
			if(StringUtils.isNotBlank(bean.getEndDate()))
				bean.setEndDate(dateFormat(bean.getEndDate()));
		}if(StringUtils.isBlank(bean.getProductID())){
			addActionError(getText("error.policy.report.product.invalid"));
		}if(StringUtils.isBlank(bean.getBranch())){
			addActionError(getText("error.policy.report.branch.invalid"));
		}
	}
	
	public void validateexchange() {
	 if(StringUtils.isBlank(bean.getEffDate())){
    		addActionError(getText("error.policy.report.startdate.invalid"));
    	}
	}
	
	public String openCover(){
		String returnResult="opencover";
		try{
			if("result".equals(bean.getReqFrom())){
				validOpenCover("opencover");
				if(!hasActionErrors()){
					coverList=service.getOpenCoverList(bean, branchCode);
					
				}else{
					brokerList=cservice.getAdminBrokerList(branchCode, appId);
					bean.setReqFrom("");
					if(StringUtils.isNotBlank(bean.getStartDate()))
						bean.setStartDate(dateFormat(bean.getStartDate()));
					if(StringUtils.isNotBlank(bean.getEndDate()))
						bean.setEndDate(dateFormat(bean.getEndDate()));
				}
			}else{
				brokerList=cservice.getAdminBrokerList(branchCode, appId);
				bean.setReqFrom("");
				if(StringUtils.isNotBlank(bean.getStartDate()))
					bean.setStartDate(dateFormat(bean.getStartDate()));
				if(StringUtils.isNotBlank(bean.getEndDate()))
					bean.setEndDate(dateFormat(bean.getEndDate()));
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return returnResult;
	}
	
	public String openCoverJasper() {
		try {
			bean.setFileName("OpenCoverReport");
			JasperReports jr = new JasperReports();
			ByteArrayOutputStream bos=new ByteArrayOutputStream();
			jr.openCoverReport(bean.getStartDate(), bean.getEndDate(), bean.getBroker(), branchCode, bean.getDownloadType(), bos);
			inputStream=new ByteArrayInputStream(bos.toByteArray());
		}
		catch(Exception exception) {
			LogManager.debug("Exception @ openCoverJasper { " + exception + " } ");
		}
		if("excel".equals(bean.getDownloadType()))
		{
			return "excel";
		}
		if("pdf".equals(bean.getDownloadType()))
		{
			return "pdf";
		}
		return "download";
	}
	public String openCoverdetailJasper() {
		try {
			String brokerLoginId="";
			bean.setFileName("OpenCoverReport");
			JasperReports jr = new JasperReports();
			ByteArrayOutputStream bos=new ByteArrayOutputStream();
    		brokerLoginId=service.getBrokerLoginId(bean);
    		
			Object args[]=new Object[7];
			args[0]=branchCode;
			args[1]=bean.getStartDate();
			args[2]=bean.getEndDate();
			args[3]=brokerLoginId;
			args[4]="ALL";
			args[5]=(!StringUtils.isEmpty(bean.getProductID()))?bean.getProductID():"11";
			args[6]=bean.getOpenCoverNo();
			jr.openCoverdetailReport(args, bean.getDownloadType(), bos);
			inputStream=new ByteArrayInputStream(bos.toByteArray());
		}
		catch(Exception exception) {
			LogManager.debug("Exception @ openCoverJasper { " + exception + " } ");
		}
		if("excel".equals(bean.getDownloadType()))
		{
			return "excel";
		}
		if("pdf".equals(bean.getDownloadType()))
		{
			return "pdf";
		}
		return "download";
	}
	
	public void validOpenCover(String cond){
		if(StringUtils.isBlank(bean.getStartDate()))
			addActionError("Please select Start Date");
		if(StringUtils.isBlank(bean.getEndDate()))
			addActionError("Please select End Date");
		if(StringUtils.isBlank(bean.getBroker()) && "opencover".equals(cond))
			addActionError("Please select Broker");
		/*if(StringUtils.isBlank(bean.getReportStatus()) && "consignee".equals(cond))
			addActionError("Please select Report Type")*/;
		if(!hasActionErrors()){
			try{
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		    	Date sdate = sdf.parse(bean.getStartDate());
		    	Date edate = sdf.parse(bean.getEndDate());
		    	if(sdate.after(edate))
		    		addActionError("End date should be greater than or equal to Start Date");
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		if(hasActionErrors()  && !"opencover".equals(cond)){
			if(StringUtils.isNotBlank(bean.getStartDate()))
				bean.setStartDate(dateFormat(bean.getStartDate()));
			if(StringUtils.isNotBlank(bean.getEndDate()))
				bean.setEndDate(dateFormat(bean.getEndDate()));
		}
	}
	
	public String dateFormat(String name){
		String arr[]=name.split("/");
		return arr[1]+"/"+arr[0]+"/"+arr[2];
	}
	
	public String consignee(){
		String returnResult="consignee";
		try{
			if("result".equals(bean.getReqFrom())){
				validOpenCover("consignee");
				if(!hasActionErrors()){
					consigneeList=service.getConsigneeList(bean, branchCode);
				}else
					bean.setReqFrom("");
			}else if("result1".equals(bean.getReqFrom())){
				consigneeList=service.getConsigneeList(bean, branchCode);
			}else if("policyDetail".equals(bean.getReqFrom())){
				consigneeList=service.getConsigneeList(bean, branchCode);
				if(StringUtils.isNotBlank(bean.getStartDate()))
					bean.setStartDate(dateFormat(bean.getStartDate()));
				if(StringUtils.isNotBlank(bean.getEndDate()))
					bean.setEndDate(dateFormat(bean.getEndDate()));
			}else{
				if(StringUtils.isNotBlank(bean.getStartDate()))
					bean.setStartDate(dateFormat(bean.getStartDate()));
				if(StringUtils.isNotBlank(bean.getEndDate()))
					bean.setEndDate(dateFormat(bean.getEndDate()));
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return returnResult;
	}
	public InputStream getInputStream() {
		return inputStream;
	}
	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}
	public String openCoverCertificate(){
		try{
		  setCertificateList(service.getCertificatesList(bean,branchCode));
		}catch (Exception e) {
            e.printStackTrace();
		}
		return "opencover";
	}
	public void setCertificateList(List<Object> certificateList) {
		this.certificateList = certificateList;
	}
	public List<Object> getCertificateList() {
		return certificateList;
	}
	
	private HttpServletRequest getRequest() {
		return ServletActionContext.getRequest();
	}
	
	public String searchListAjax() {
		if("IR".equalsIgnoreCase(bean.getUtype()) || "AG".equalsIgnoreCase(bean.getUtype())){
			bean.setPaymentList(service.getDropDownList(bean,"paymentList"));
		}else if("CT".equalsIgnoreCase(bean.getSearchBy())){
			bean.setCoverTypeList(service.getDropDownList(bean,"coverTypeList"));
		}
		if("SS".equalsIgnoreCase(bean.getUtype())){
			bean.setApproverIdList(service.getDropDownList(bean,"surveyorList"));
		}else if("UW".equalsIgnoreCase(bean.getUtype())){
			bean.setApproverIdList(service.getDropDownList(bean,"underWriterList"));
		}else if("CC".equalsIgnoreCase(bean.getUtype())){
			bean.setApproverIdList(service.getDropDownList(bean,"creditControllerList"));
		}/*
		else if("ET".equalsIgnoreCase(bean.getSearchBy())){
			bean.setEndorsementTypeList(service.getDropDownList(bean,"endorsementTypeList"));
		}else if("VU".equalsIgnoreCase(bean.getSearchBy())){
			bean.setVehicleUsageList(service.getDropDownList(bean,"vehicleUsageList"));
		}else if("CI".equalsIgnoreCase(bean.getSearchBy())){
			bean.setCityList(service.getDropDownList(bean,"cityList"));
		}*/
		//'P':'Payment Method','CT':'Cover Type','SLI':'Approver Id','UWI':'Approver Id','ET':'Endorsement Type','VU':'Vehicle Usage','CI':'City'
		getRequest().setAttribute(DBConstants.FIELD, bean.getUtype() );
		return "dropdown";
	}
	
	public String getAllListAjax(){
		validateSearch();
			if(!hasActionErrors()){
			bean.setSmartReportList(service.getSmartReportList(branchCode,login_id,userType,bean));
			bean.setReqFrom("showList");
			}
			getRequest().setAttribute(DBConstants.FIELD, bean.getReportType());
		return "dropdown";
	}
	
	public String getAllList(){
		String result="";
		bean.setCoverTypeList(service.getDropDownList(bean,"coverTypeList"));
		bean.setCityList(service.getDropDownList(bean,"cityList"));
		bean.setEndorsementTypeList(service.getDropDownList(bean,"endorsementTypeList"));
		bean.setVehicleUsageList(service.getDropDownList(bean,"vehicleUsageList"));
		//bean.setPaymentList(service.getDropDownList(bean,"paymentList"));
		if("search".equalsIgnoreCase(bean.getMode())){
		validateSearch();
			if(!hasActionErrors()){
			bean.setSmartReportList(service.getSmartReportList(branchCode,login_id,userType,bean));
			bean.setReqFrom("showList");
			}
		}
		if("APRQUOTES".equalsIgnoreCase(bean.getReportType())){
			if("RSAIssuer".equalsIgnoreCase(userType)){
				result= "showPayPro";
			}else{
				result= "showPayProAdmin";
			}
		}else if("ENDORSEMENTREGISTER".equalsIgnoreCase(bean.getReportType()) || "ROADASSIT".equalsIgnoreCase(bean.getReportType())){
			if("RSAIssuer".equalsIgnoreCase(userType)){
				result= "showER";
			}else{
				result= "showERAdmin";
			}
		}else if("POLICYREGISTER".equalsIgnoreCase(bean.getReportType())){
			if("admin".equalsIgnoreCase(userType)){
				result= "showPolicyAdmin";
			}else{
				result= "showPolicy";
			}
		}else if("PREMIUMREGISTER".equalsIgnoreCase(bean.getReportType())){
			if("admin".equalsIgnoreCase(userType)){
				result= "showPremiumAdmin";
			}else{
				result= "showPremium";
			}
		}else if("CLAIMS".equalsIgnoreCase(bean.getReportType())){
			if("RSAIssuer".equalsIgnoreCase(userType)){
				result= "showClaim";
			}else{
				result= "showClaimAdmin";
			}
		}else if("INSTALMENTPAYMENT".equalsIgnoreCase(bean.getReportType()) || "AGINGANALYSIS".equalsIgnoreCase(bean.getReportType()) || (StringUtils.isEmpty(bean.getReportType()) )){
			if("admin".equalsIgnoreCase(userType)){
				result= "showInsAdmin";
			}else{
				result= "showIns";
			}
		}
		return result;
	}
	
	public void validateSearch() {
		 if(StringUtils.isBlank(bean.getStartDate())){
			 addActionError(getText("error.policy.report.startdate.invalid"));
	   	}if(StringUtils.isBlank(bean.getEndDate())){
	   		addActionError(getText("error.policy.report.enddate.invalid"));
	   	}if(!hasActionErrors()){
				try{
					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			    	Date sdate = sdf.parse(bean.getStartDate());
			    	Date edate = sdf.parse(bean.getEndDate());
			    	if(sdate.after(edate))
			    		addActionError("End date should be greater than or equal to Start Date");
				}catch(Exception e){
					e.printStackTrace();
				}
		} 
	   	if("APRQUOTES".equalsIgnoreCase(bean.getReportType())){
	   		if(StringUtils.isBlank(bean.getUtype())){
	   			addActionError("Choose the User Type");
	   		}
	   	}
		if("INSTALMENTPAYMENT".equalsIgnoreCase(bean.getReportType()) || "AGINGANALYSIS".equalsIgnoreCase(bean.getReportType()) || (StringUtils.isEmpty(bean.getReportType()))){
	   		if(StringUtils.isBlank(bean.getUtype())){
	   			addActionError("Choose the User Type");
	   		}
	   	}
   	}
	
	public String allRepJasper(){
		String result = null;
		try{
			String input = null;
			if("INSTALMENTPAYMENT".equalsIgnoreCase(bean.getReportType()) || "AGINGANALYSIS".equalsIgnoreCase(bean.getReportType())){
				input = (!StringUtils.isEmpty(bean.getPaymentVal()))?bean.getPaymentVal():"ALL";
			}else if("APRQUOTES".equalsIgnoreCase(bean.getReportType())){
				input = (!StringUtils.isEmpty(bean.getApproveVal()))?bean.getApproveVal():"ALL";
			}else if("ENDORSEMENTREGISTER".equalsIgnoreCase(bean.getReportType())){
				input = (!StringUtils.isEmpty(bean.getEndorsementTypeVal()))?bean.getEndorsementTypeVal():"ALL";
			}else if("POLICYREGISTER".equalsIgnoreCase(bean.getReportType())){
				input = (!StringUtils.isEmpty(bean.getVehicleUsageVal()))?bean.getVehicleUsageVal():"ALL";
			}else{
				input = "ALL";
			}
			bean.setFileName(bean.getReportType());		 
			JasperReports jr=new JasperReports(); 
			ByteArrayOutputStream report = (ByteArrayOutputStream) jr.commonAllReport(
			bean.getReportType(),bean.getStartDate(),bean.getEndDate(),
			(!StringUtils.isEmpty(bean.getCoverTypeVal()))?bean.getCoverTypeVal():"ALL",
			(!StringUtils.isEmpty(bean.getStatus()))?bean.getStatus():"ALL",
			input, (!StringUtils.isEmpty(bean.getCityVal()))?bean.getCityVal():"ALL",
			(!StringUtils.isEmpty(bean.getUtype()))?bean.getUtype():"ALL",
			bean.getProductID(),bean.getDownloadType()); 
			inputStream = new ByteArrayInputStream(report.toByteArray());
		}catch (Exception e) {
			LogManager.debug("Exception @ allRepJasper { " + e + " } ");
		}
		if("excel".equals(bean.getDownloadType()))
		{
			result= "excel";
		}
		if("pdf".equals(bean.getDownloadType()))
		{
			result= "pdf";
		}
		return result;
	}
}