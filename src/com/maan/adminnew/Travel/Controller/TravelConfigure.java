package com.maan.adminnew.Travel.Controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.maan.adminnew.Travel.DAO.TravelBean;
import com.maan.adminnew.Travel.Service.TravelService;
import com.maan.common.LogManager;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class TravelConfigure extends ActionSupport implements ModelDriven<TravelBean>{
	HttpServletRequest request=ServletActionContext.getRequest();
	HttpServletResponse response=ServletActionContext.getResponse();
	Map<String, Object> session=ActionContext.getContext().getSession();
	private static final long serialVersionUID = 1L;
	TravelService service=new TravelService();
	private TravelBean travelBean=new TravelBean(); 
	private String branchCode=(String)session.get("LoginBranchCode");
	private String productId=(String) session.get("product_id");
	private List<Object> list=null;
	private List<Object> link=null;
	private List<Object> scheme=null;
	private List<Object> option=null;
	private List<Object> cover=null;

	public List<Object> getScheme() {
		return service.getSchemeValue(travelBean);
	}
	public List<Object> getOption() {
		return service.getOptionValue(travelBean);
	}
	public List<Object> getCover() {
		return service.getCoverValue(travelBean);
	}
	public List<Object> getLink() {
		return link;
	}
	public void setLink(List<Object> link) {
		this.link = link;
	}
	public List getSage(){
		List sage=new ArrayList<Object>();
		for(int i=0;i<=100;i++)
			sage.add(i, i);
		return sage;
	}
	public List getEage(){
		List eage=new ArrayList<Object>();
		for(int i=0;i<=100;i++)
			eage.add(i, i);
		return eage;
	}

	public List<Object> getList() {
		return list;
	}

	public void setList(List<Object> list) {
		this.list = list;
	}
	
    public String engine()
	{
		list=service.getScheme(travelBean);
		return "scheme";	
	}
	
	public String scheme() {
		String result="scheme",res="";
		if("edit".equalsIgnoreCase(travelBean.getDisplay())){
			service.editScheme(travelBean);
		}else if("history".equalsIgnoreCase(travelBean.getDisplay())){
			list=service.getSchemeHistory(travelBean);
			travelBean.setDisplay("history");
			result="schemehistory";
		}else if("new".equalsIgnoreCase(travelBean.getDisplay())){
			travelBean.setDisplay("new");
		}else if("insert".equalsIgnoreCase(travelBean.getDisplay())){
			getValidate("Scheme");
			if(getActionErrors().isEmpty()){
				res=service.insertScheme(travelBean);
				if("success".equalsIgnoreCase(res)){
					addActionError(getText("error.travel.insert.sucess"));
					travelBean.setDisplay("");
					list=service.getScheme(travelBean);
				}else{
					travelBean.setDisplay("new");
					//addActionError(getText("error.travel.code"));
				}
			}
			else{
				travelBean.setDisplay("new");
			}
		}else if("update".equalsIgnoreCase(travelBean.getDisplay())){
			getValidate("Scheme");
			if(getActionErrors().isEmpty()){
				res=service.insertScheme(travelBean);
				if("success".equalsIgnoreCase(res)){
					addActionError(getText("error.travel.update.sucess"));
					travelBean.setDisplay("");
					list=service.getScheme(travelBean);
				}else{
					travelBean.setDisplay("edit");
					//addActionError(getText("error.travel.code"));
				}
			}
			else{
				travelBean.setDisplay("edit");
			}
		}else{
			travelBean.setDisplay("");
			list=service.getScheme(travelBean);
		}
		return result;
	}
	public String option() {
		String result="option",res="";
		if("edit".equalsIgnoreCase(travelBean.getDisplay())){
			service.editOption(travelBean);
		}else if("history".equalsIgnoreCase(travelBean.getDisplay())){
			list=service.getOptionHistory(travelBean);
			travelBean.setDisplay("history");
			result="optionhistory";
		}else if("new".equalsIgnoreCase(travelBean.getDisplay())){
			travelBean.setDisplay("new");
		}else if("insert".equalsIgnoreCase(travelBean.getDisplay())){
			//travelBean.setDisplay("new");
			getValidate("Option");
			if(getActionErrors().isEmpty()){
				res=service.insertOption(travelBean);
				if("success".equalsIgnoreCase(res)){
					addActionError(getText("error.travel.insert.sucess"));
					travelBean.setDisplay("");
					list=service.getOption(travelBean);
				}else{
					travelBean.setDisplay("new");
					//addActionError(getText("error.travel.code"));
				}
			}
			else{
				travelBean.setDisplay("new");
			}
		}else if("update".equalsIgnoreCase(travelBean.getDisplay())){
			//travelBean.setDisplay("edit");
			getValidate("Option");
			if(getActionErrors().isEmpty()){
				res=service.insertOption(travelBean);
				if("success".equalsIgnoreCase(res)){
					addActionError(getText("error.travel.update.sucess"));
					travelBean.setDisplay("");
					list=service.getOption(travelBean);
				}else{
					travelBean.setDisplay("edit");
					//addActionError(getText("error.travel.code"));
				}
			}
			else{
				travelBean.setDisplay("edit");
			}
		}else{
			travelBean.setDisplay("");
			list=service.getOption(travelBean);
		}
		return result;
	}
	
	public String linkoption() {
		String result="linkoption",res="";
		if("scheme".equals(travelBean.getReqFrom())){
			result="dropdown";
		}else if("option".equals(travelBean.getReqFrom())){
			link=service.getLinkOption(travelBean);
			String[] str=new String[link.size()];
			if(link != null &&link.size()>0){
              	for(int i=0;i<link.size();i++){
              		Map map1=(Map)link.get(i);
              		str[i]=(map1.get("OPTION_ID")==null?"":map1.get("OPTION_ID")).toString();
				}
			}
			travelBean.setLinkOption(str);
			service.editLinkOption(travelBean);
			result="dropdown";
		}else if("history".equalsIgnoreCase(travelBean.getDisplay())){
			list=service.getLinkOptionHistory(travelBean);
			travelBean.setDisplay("history");
			result="linkoptionhistory";
		}else if("insert".equalsIgnoreCase(travelBean.getDisplay())){
			getValidate("linkoption");
			if(getActionErrors().isEmpty()){
				res=service.insertLinkOption(travelBean);
				if("success".equalsIgnoreCase(res)){
					addActionError(getText("error.travel.insert.sucess"));
				}else{
					travelBean.setDisplay("new");
					addActionError(getText("error.travel.code"));
				}
			}
		}else{
			travelBean.setDisplay("");
			list=service.getLinkOption(travelBean);
		}
		return result;
	}
	
	public String coverage() {
		String result="coverage",res="";
		if("edit".equalsIgnoreCase(travelBean.getDisplay())){
			service.editCoverage(travelBean);
		}else if("history".equalsIgnoreCase(travelBean.getDisplay())){
			list=service.getCoverageHistory(travelBean);
			travelBean.setDisplay("history");
			result="coveragehistory";
		}else if("new".equalsIgnoreCase(travelBean.getDisplay())){
			travelBean.setDisplay("new");
		}else if("insert".equalsIgnoreCase(travelBean.getDisplay())){
			//travelBean.setDisplay("new");
			getValidate("coverage");
			if(getActionErrors().isEmpty()){
				res=service.insertCoverage(travelBean);
				if("success".equalsIgnoreCase(res)){
					addActionError(getText("error.travel.insert.sucess"));
					travelBean.setDisplay("");
					list=service.getCoverage(travelBean);
				}else{
					travelBean.setDisplay("new");
					//addActionError(getText("error.travel.code"));
				}
			}
			else{
				travelBean.setDisplay("NEW");
			}
		}else if("update".equalsIgnoreCase(travelBean.getDisplay())){
			//travelBean.setDisplay("edit");
			getValidate("coverage");
			if(getActionErrors().isEmpty()){
				res=service.insertCoverage(travelBean);
				if("success".equalsIgnoreCase(res)){
					addActionError(getText("error.travel.update.sucess"));
					travelBean.setDisplay("");
					list=service.getCoverage(travelBean);
				}else{
					travelBean.setDisplay("edit");
					//addActionError(getText("error.travel.code"));
				}
			}
			else{
				travelBean.setDisplay("edit");
			}
		}else{
			travelBean.setDisplay("");
			list=service.getCoverage(travelBean);
		}
		return result;
	}
	
	public String linkcoverage() {
		String result="linkcoverage",res="";
		if("coverage".equals(travelBean.getReqFrom())){
			result="dropdown";
		}else if("cover".equals(travelBean.getReqFrom())){
			service.editLinkCoverage(travelBean);
			result="dropdown";
		}else if("edit".equalsIgnoreCase(travelBean.getDisplay())){
			service.editLinkCoverage(travelBean);
		}else if("history".equalsIgnoreCase(travelBean.getDisplay())){
			list=service.getLinkCoverageHistory(travelBean);
			travelBean.setDisplay("history");
			result="linkcoveragehistory";
		}else if("insert".equalsIgnoreCase(travelBean.getDisplay())){
			getValidate("linkcoverage");
			if(getActionErrors().isEmpty()){
				res=service.insertLinkCoverage(travelBean);
				if("success".equalsIgnoreCase(res)){
					addActionError(getText("error.travel.insert.sucess"));
				}else{
					travelBean.setDisplay("new");
					addActionError(getText("error.travel.code"));
				}
			}
		}else{
			travelBean.setDisplay("");
			list=service.getLinkCoverage(travelBean);
		}
		return result;
	}
	
	public String discountrate() {
		String result="discountrate",res="";
		travelBean.setTravelRelationList(service.TypeList(travelBean));
		if("edit".equalsIgnoreCase(travelBean.getDisplay())){
			service.editDiscountRate(travelBean);
		}else if("history".equalsIgnoreCase(travelBean.getDisplay())){
			list=service.getDiscountRateHistory(travelBean);
			travelBean.setDisplay("history");
			result="discountratehistory";
		}else if("new".equalsIgnoreCase(travelBean.getDisplay())){
			travelBean.setDisplay("new");
		}else if("insert".equalsIgnoreCase(travelBean.getDisplay())){
			getValidate("discountrate");
			if(getActionErrors().isEmpty()){
				res=service.insertDiscountRate(travelBean);
				if("success".equalsIgnoreCase(res)){
					addActionError(getText("error.travel.insert.sucess"));
					travelBean.setDisplay("");
					list=service.getDiscountRate(travelBean);
				}else{
					travelBean.setDisplay("new");
					//addActionError(getText("error.travel.code"));
				}
			}
			else{
				travelBean.setDisplay("new");
			}
		}else if("update".equalsIgnoreCase(travelBean.getDisplay())){
			getValidate("discountrate");
			if(getActionErrors().isEmpty()){
				res=service.insertDiscountRate(travelBean);
				if("success".equalsIgnoreCase(res)){
					addActionError(getText("error.travel.update.sucess"));
					travelBean.setDisplay("");
					list=service.getDiscountRate(travelBean);
				}else{
					travelBean.setDisplay("edit");
					//addActionError(getText("error.travel.code"));
				}
			}
			else{
				travelBean.setDisplay("edit");
			}
		}else{
			travelBean.setDisplay("");
			list=service.getDiscountRate(travelBean);
		}
		return result;
	}
	
	public String relationdiscount() {
		String result="relationdiscount",res="";
		travelBean.setTravelTypeList(service.TravelTypeList(travelBean));
		if("edit".equalsIgnoreCase(travelBean.getDisplay())){
			service.editRelationDiscount(travelBean);
		}else if("history".equalsIgnoreCase(travelBean.getDisplay())){
			list=service.getRelationDiscountHistory(travelBean);
			travelBean.setDisplay("history");
			result="relationdiscounthistory";
		}else if("new".equalsIgnoreCase(travelBean.getDisplay())){
			travelBean.setDisplay("new");
		}else if("insert".equalsIgnoreCase(travelBean.getDisplay())){
			getValidate("relationdiscount");
			if(getActionErrors().isEmpty()){
				res=service.insertRelationDiscount(travelBean);
				if("success".equalsIgnoreCase(res)){
					addActionError(getText("error.travel.insert.sucess"));
					travelBean.setDisplay("");
					list=service.getRelationDiscount(travelBean);
				}else{
					travelBean.setDisplay("new");
					//addActionError(getText("error.travel.code"));
				}
			}
			else{
				travelBean.setDisplay("new");
			}
		}else if("update".equalsIgnoreCase(travelBean.getDisplay())){
			getValidate("relationdiscount");
			if(getActionErrors().isEmpty()){
				res=service.insertRelationDiscount(travelBean);
				if("success".equalsIgnoreCase(res)){
					addActionError(getText("error.travel.update.sucess"));
					travelBean.setDisplay("");
					list=service.getRelationDiscount(travelBean);
				}else{
					travelBean.setDisplay("edit");
					//addActionError(getText("error.travel.code"));
				}
			}
			else{
				travelBean.setDisplay("edit");
			}
		}else{
			travelBean.setDisplay("");
			list=service.getRelationDiscount(travelBean);
		}
		return result;
	}
	public String premiumrate() {
		String result="premiumrate",res="";
		travelBean.setTravelSchemeList(service.getschemename(travelBean));
		if("edit".equalsIgnoreCase(travelBean.getDisplay())){
			service.editPremiumRate(travelBean);
		}else if("history".equalsIgnoreCase(travelBean.getDisplay())){
			list=service.getPremiumRateHistory(travelBean);
			travelBean.setDisplay("history");
			result="premiumratehistory";
		}else if("new".equalsIgnoreCase(travelBean.getDisplay())){
			travelBean.setDisplay("new");
		}else if("insert".equalsIgnoreCase(travelBean.getDisplay())){
			//getValidate("relationdiscount");
			getValidate("premiumrate");
			if(getActionErrors().isEmpty()){
				res=service.insertPremiumRate(travelBean);
				if("success".equalsIgnoreCase(res)){
					addActionError(getText("error.travel.insert.sucess"));
					travelBean.setDisplay("");
					list=service.getPremiumRate(travelBean);
				}else{
					travelBean.setDisplay("new");
					//addActionError(getText("error.travel.code"));
				}
			}
			else{
				travelBean.setDisplay("new");
			}
		}else if("update".equalsIgnoreCase(travelBean.getDisplay())){
			//getValidate("relationdiscount");
			getValidate("premiumrate");
			if(getActionErrors().isEmpty()){
				res=service.insertPremiumRate(travelBean);
				if("success".equalsIgnoreCase(res)){
					addActionError(getText("error.travel.update.sucess"));
					travelBean.setDisplay("");
					list=service.getPremiumRate(travelBean);
				}else{
					travelBean.setDisplay("edit");
					//addActionError(getText("error.travel.code"));
				}
			}
			else{
				//travelBean.setTravelOptionList(travelOptionList)
				travelBean.setDisplay("edit");
			}
		}else{
			travelBean.setDisplay("");
			list=service.getPremiumRate(travelBean);
		}
		return result;
	}
	
	public String getAjaxVal(){
		travelBean.setTravelOptionList(service.getOptionName(travelBean));
		return "dropdown";
	}
	
	public void getValidate(String type)
	{
		int diffInDate=0;
		try{
		 if("scheme".equalsIgnoreCase(type)){
			 if(StringUtils.isEmpty(travelBean.getSchemeName())){
					addActionError(getText("error.travel.scheme.name"));
				}if(StringUtils.isEmpty(travelBean.getSchemeCode())){
					addActionError(getText("error.travel.scheme.code"));
				//}if(StringUtils.isEmpty(travelBean.getSchemeProduct())){
				}else{
					int code=service.coreappcodetest(travelBean.getSchemeCode(),travelBean);
					if(code!=0){
						addActionError(getText("error.travel.code"));
					}
				}if(StringUtils.isEmpty(travelBean.getOptionProduct())){
					addActionError(getText("error.travel.scheme.product"));
				}if(StringUtils.isEmpty(travelBean.getSchemeDate())){
					addActionError(getText("error.travel.scheme.date"));
				}else{
						diffInDate = (int) diffInDays(travelBean.getSchemeDate(),"");
						if(diffInDate>0){
							addActionError(getText("error.travel.scheme.date1"));
						}
				}
				if(StringUtils.isEmpty(travelBean.getSchemeStatus())){
					addActionError(getText("error.travel.scheme.status"));
			    }
		 }else if("option".equalsIgnoreCase(type)){
			 if(StringUtils.isEmpty(travelBean.getOptionName())){
					addActionError(getText("error.travel.option.name"));
				}if(StringUtils.isEmpty(travelBean.getOptionCode())){
					addActionError(getText("error.travel.option.code"));
				}else{
						int code=service.coreappcodetestoption(travelBean);
						if(code!=0){
							addActionError(getText("error.travel.code"));
				}
				}if(StringUtils.isEmpty(travelBean.getOptionProduct())){
					addActionError(getText("error.travel.option.product"));
				}if(StringUtils.isEmpty(travelBean.getOptionDate())){
					addActionError(getText("error.travel.option.date"));
				}else{
					diffInDate = (int) diffInDays(travelBean.getOptionDate(),"");
					if(diffInDate>0){
						addActionError(getText("error.travel.option.date1"));
					}
				}
				if(StringUtils.isEmpty(travelBean.getOptionStatus())){
					addActionError(getText("error.travel.option.status"));
			    }
		 }else if("coverage".equalsIgnoreCase(type)){
			 if(StringUtils.isEmpty(travelBean.getCoverageName())){
					addActionError(getText("error.travel.coverage.name"));
				}if(StringUtils.isEmpty(travelBean.getCoverageValue())){
					addActionError(getText("error.travel.coverage.value"));
				}if(StringUtils.isEmpty(travelBean.getCoverageCode())){
					addActionError(getText("error.travel.coverage.code"));
				}else{
					int code=service.coreappcodetestcoverage(travelBean);
					if(code!=0){
						addActionError(getText("error.travel.code"));
				}
				}if(StringUtils.isEmpty(travelBean.getCoverageProduct())){
					addActionError(getText("error.travel.coverage.product"));
				}if(StringUtils.isEmpty(travelBean.getCoverageDate())){
					addActionError(getText("error.travel.option.date"));
				}else{
						diffInDate = (int) diffInDays(travelBean.getCoverageDate(),"");
						if(diffInDate>0){
							addActionError(getText("error.travel.option.date1"));
						}
					}
				if(StringUtils.isEmpty(travelBean.getCoverageStatus())){
					addActionError(getText("error.travel.coverage.status"));
			    }
		 	}
			else if("discountrate".equalsIgnoreCase(type)){	
			 if(StringUtils.isEmpty(travelBean.getDisType())){
					addActionError(getText("error.travel.type.list"));
				}if(StringUtils.isEmpty(travelBean.getDisStart())){
					addActionError(getText("error.travel.start.age"));
				}if(StringUtils.isEmpty(travelBean.getDisEnd())){
					addActionError(getText("error.travel.end.age"));
				}
				if(StringUtils.isNotEmpty(travelBean.getDisStart()) && StringUtils.isNotEmpty(travelBean.getDisEnd())){
				int test=travelBean.getDisStart().compareTo(travelBean.getDisEnd());
				if(test>0){
					addActionError(getText("error.travel.start.end.date"));	
				}
				}
				if(StringUtils.isEmpty(travelBean.getDisRateValue())){
					addActionError(getText("error.travel.rate.value"));
				}if(StringUtils.isEmpty(travelBean.getDisCode())){
					addActionError(getText("error.travel.drate.code"));
				}else{
					int code=service.coreappcodetestdiscount(travelBean);
					if(code!=0){
						addActionError(getText("error.travel.code"));
				}
				}if(StringUtils.isEmpty(travelBean.getDisDate())){
					addActionError(getText("error.travel.drate.date"));
				}else{
					diffInDate = (int) diffInDays(travelBean.getDisDate(),"");
					if(diffInDate>0){
					addActionError(getText("error.travel.scheme.date1"));
						}
			    }if(StringUtils.isEmpty(travelBean.getDisStatus())){
					addActionError(getText("error.travel.drate.status"));
			    }
		 }else if("relationdiscount".equalsIgnoreCase(type)){
			 if(StringUtils.isEmpty(travelBean.getDisType())){
					addActionError(getText("error.travel.relation.type"));
				}if(StringUtils.isEmpty(travelBean.getSageList())){
					addActionError(getText("error.travel.relation.start"));
				}if(StringUtils.isEmpty(travelBean.getEageList())){
					addActionError(getText("error.travel.relation.end"));
				}if(StringUtils.isNotEmpty(travelBean.getSageList()) && StringUtils.isNotEmpty(travelBean.getEageList())){
				int test=travelBean.getSageList().compareTo(travelBean.getEageList());
				if(test>0){
					addActionError(getText("error.travel.start.end.date1"));
				}
				}if(StringUtils.isEmpty(travelBean.getRateValue())){
					addActionError(getText("error.travel.relation.value"));
				}if(StringUtils.isEmpty(travelBean.getDrateCode())){
					addActionError(getText("error.travel.relation.code"));
				}else{
					int code=service.coreappcodetestrelation(travelBean);
					if(code!=0){
						addActionError(getText("error.travel.code"));
				}
				}if(StringUtils.isEmpty(travelBean.getDrateDate())){
					addActionError(getText("error.travel.relation.date"));
				}else{
						diffInDate = (int) diffInDays(travelBean.getDrateDate(),"");
						if(diffInDate>0){
							addActionError(getText("error.travel.option.date1"));
						}
			    }if(StringUtils.isEmpty(travelBean.getDrateStatus())){
					addActionError(getText("error.travel.relation.status"));
			    }
		 }else if("premiumrate".equalsIgnoreCase(type)){
			 if(StringUtils.isEmpty(travelBean.getSchemeName())){
					addActionError(getText("error.travel.premiumrate.schemename"));
			 }if(StringUtils.isEmpty(travelBean.getOptionName())){
					addActionError(getText("error.travel.premiumrate.option"));
				}if(StringUtils.isEmpty(travelBean.getNoofDays())){
					addActionError(getText("error.travel.premiumrate.noofdays"));
				}if(StringUtils.isEmpty(travelBean.getDisRateValue())){
					addActionError(getText("error.travel.relation.value"));
				}if(StringUtils.isEmpty(travelBean.getDisCode())){
					addActionError(getText("error.travel.relation.code"));
				}else{
					int code=service.coreappcodetestpremium(travelBean);
					if(code!=0){
						addActionError(getText("error.travel.code"));
				}
				}if(StringUtils.isEmpty(travelBean.getDisDate())){
					addActionError(getText("error.travel.relation.date"));
				}else{
					diffInDate = (int) diffInDays(travelBean.getDisDate(),"");
					if(diffInDate>0){
							addActionError(getText("error.travel.option.date1"));
					}
			    }if(StringUtils.isEmpty(travelBean.getDisStatus())){
					addActionError(getText("error.travel.relation.status"));
			    }
		 		}else if("linkoption".equalsIgnoreCase(type)){
		 		}else if("linkcoverage".equalsIgnoreCase(type)){
		 			if(StringUtils.isEmpty(travelBean.getDisType())){
					addActionError(getText("error.travel.relation.type"));
				}
		 }
		}catch(Exception e)
		{
			LogManager.debug(e);
		}
	}
	public TravelBean getModel() {
		travelBean.setBranchCode(branchCode);
		travelBean.setProductId("33");
		return travelBean;
	}
	public long diffInDays(String startDate,String endDate){
		long result=0;
		try{
			Date date = new Date();
	        Calendar cal1 = Calendar.getInstance();
	        Calendar cal2 = Calendar.getInstance();
	        SimpleDateFormat sfd = new SimpleDateFormat("dd/MM/yyyy");
	        cal1.setTime(sfd.parse(startDate));
	        if(StringUtils.isBlank(endDate))
	        	cal2.setTime(sfd.parse(sfd.format(date)));
	        else
	        	cal2.setTime(sfd.parse(endDate));
	        long milis1 = cal1.getTimeInMillis();
	        long milis2 = cal2.getTimeInMillis();
	        long diff = milis2 - milis1;
	        result = diff / (24 * 60 * 60 * 1000);
		}catch (Exception e) {
			LogManager.debug("Exception Occured @ "+e);
			e.printStackTrace();
		}
		return result;
	}
}
