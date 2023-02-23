package com.maan.Home.Controller;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.maan.Home.Service.AdminHomeService;
import com.maan.common.LogManager;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class AdminHomeAction extends ActionSupport implements ModelDriven<AdminHomeBean>{
	private static final long serialVersionUID = 1987654L;
	private AdminHomeBean bean=new AdminHomeBean();
	HttpServletRequest request=ServletActionContext.getRequest();
	HttpServletResponse response=ServletActionContext.getResponse();
	private Map<String, Object> session=ActionContext.getContext().getSession();
	private String branchCode=(String)session.get("LoginBranchCode");
	private String productId=(String) session.get("product_id");
	private String loginId=(String)session.get("user");
	private String userType=(String)session.get("usertype");
	private String user=(String)session.get("user1");
	private AdminHomeService service=new AdminHomeService();
	private Integer page= 0;
	private int id;
	private Integer rows=0;
	private String sord;
	private String sidx;
	private String searchField;
	private String searchString;
	private String searchOper;
	private Integer total= 0;
	private Integer records= 0;
	private String option;
	private String oper= "edit";

	public void setOper(String oper){
	    this.oper = oper;
	  }
	public String getOption() {
		return option;
	}
	public void setOption(String option) {
		this.option = option;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public AdminHomeBean getModel() {
		bean.setLoginId(loginId);
		bean.setBranchCode(branchCode);
		bean.setProductId(productId);
		bean.setUserType(userType);
		bean.setUser(user);
		return bean;
	}
	
	public Integer getRows() {
		return rows;
	}
	public void setRows(Integer rows) {
		this.rows = rows;
	}
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public String getSord() {
		return sord;
	}
	public void setSord(String sord) {
		this.sord = sord;
	}
	public String getSidx() {
		return sidx;
	}
	public void setSidx(String sidx) {
		this.sidx = sidx;
	}
	public String getSearchField() {
		return searchField;
	}
	public void setSearchField(String searchField) {
		this.searchField = searchField;
	}
	public String getSearchString() {
		return searchString;
	}
	public void setSearchString(String searchString) {
		this.searchString = searchString;
	}
	public String getSearchOper() {
		return searchOper;
	}
	public void setSearchOper(String searchOper) {
		this.searchOper = searchOper;
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public Integer getRecords() {
		return records;
	}
	public void setRecords(Integer records) {
		this.records = records;
	}
	
	public String getListSelect() {
		List listselect=service.getProductList(branchCode);
		StringBuffer sb= new StringBuffer();
		for(int i=0; i<listselect.size(); i++){
			Map listEditOptions=(Map)listselect.get(i);
			sb.append(""+listEditOptions.get("product_id")+":"+listEditOptions.get("product_name")).append(";");
		}
		return "{value:':-Select-;"+sb.toString().substring(0,sb.toString().length()-1)+"'}";
	}

	public String getListSelect1() {
		List listselect=service.getBranchList();
		StringBuffer sb= new StringBuffer();
		for(int i=0; i<listselect.size(); i++){
			Map listEditOptions=(Map)listselect.get(i);
			sb.append(""+listEditOptions.get("branch_code")+":"+listEditOptions.get("branch_name")).append(";");
		}
		return "{value:':-Select-;"+sb.toString().substring(0,sb.toString().length()-1)+"'}";
	}
	
	public List<AdminHomeBean> gridRaja(List<AdminHomeBean> list){
		List<AdminHomeBean>selectedList =  new ArrayList<AdminHomeBean>();
		try{
		    int to = (rows * page);
		    int from = to - rows;
		    records = list.size();
	        if(to > records)
	            to =records;
	       
	        for(int i=from;i<to;i++){
	        	selectedList.add(list.get(i));
	        }
	       /* if (selectedList != null && sord!= null && sord.equalsIgnoreCase("asc")) {
		            Collections.sort(selectedList, null);
		    }
		    if (sord!= null && sord.equalsIgnoreCase("desc")) {
		            Collections.sort(selectedList, null);
		            Collections.reverse(selectedList);
		    }*/
		    total =(int) Math.ceil((double)records / (double)rows);
		}catch(Exception e){
			e.printStackTrace();
		}
		return selectedList;
	}
	  
	public String getMenu(){
		return "hadminMenu";
	}
	public String scheme(){
		return "schemeMaster";
	}
	
	public String coverage(){
		return "coverageList";
	}
	
	public String editScheme(){
		try{
			if(!"del".equalsIgnoreCase(oper))
				editValid();
			else
				bean.setSchemeId((service.getSchemeList(bean, searchField, searchString, searchOper).get(id-1)).getSchemeId());
			if(!hasActionErrors()){
				LogManager.info("Add Scheme");
				int valid=service.schemeModify(bean, oper, bean.getSchemeId());
				if(valid<=0){
					addActionError("Error in Updation");
					return "error1";
				}
			}else
				return "error1";
		}catch(Exception e){
			e.printStackTrace();
			return "error1";
	    }
	    return NONE;
	}
	
	public List<AdminHomeBean> getGridList(){
		List<AdminHomeBean> list=new ArrayList<AdminHomeBean>();
		if("coverage".equalsIgnoreCase(option))
			list=gridRaja(service.getCoveragesList(bean, searchField, searchString, searchOper));
		else if("scheme".equalsIgnoreCase(option))
			list=gridRaja(service.getSchemeList(bean, searchField, searchString, searchOper));
		return list;
	}
	
	public void editValid(){
		int[] countVal=service.validateScheme(bean, oper);
		if(countVal[0]!=0)
			addActionError(getText("error.scheme.product.invalid"));
		if(countVal[1]!=0)
			addActionError(getText("error.scheme.product.rsacode.invalid"));
		if(countVal[2]!=0)
			addActionError(getText("error.scheme.product.displayorder.invalid"));
	}
}
