package com.maan.Office.OfficeNew;

import java.util.List;
import java.util.Map;

import nl.captcha.Captcha;

import org.apache.commons.lang3.StringUtils;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class OfficeAction extends ActionSupport implements ModelDriven<OfficeBean>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	OfficeBean bean=new OfficeBean();
	OfficeService service = new OfficeService();
	
	public OfficeBean getModel() {
		String branchCode=(String)getSession().get("LoginBranchCode");
		String productId=(String) getSession().get("product_id");
		String loginId=(String)getSession().get("user");
		String userType=(String)getSession().get("usertype");
		String user=(String)getSession().get("user1");
		
		bean.setBranchCode(branchCode);
		bean.setProductId(productId);
		bean.setLoginId(loginId);
		bean.setUserType(userType);
		bean.setUser(user);
		
		return bean;
	}
	
	private final Map<String, Object> getSession() {
		return com.opensymphony.xwork2.ActionContext.getContext().getSession();
	}

	public String coverageList(){
		try {
			bean.setCoverageList(service.coverageList());
			bean.setMode("");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "ofsCoverageMaster";
	}
	public String editCoverage(){
		try {
			if("edit".equalsIgnoreCase(bean.getMode())){
				service.setCoverageDtls(bean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "ofsCoverageMaster";
	}
	public String updateCoverage(){
		try {
			validation("coverage");
			if(!hasActionErrors()){
				int ins=service.updateCoverage(bean);
				if("add".equalsIgnoreCase(bean.getMode()) && ins>0)
					addActionMessage("Added Successfully");
				else if("edit".equalsIgnoreCase(bean.getMode()) && ins>0)
					addActionMessage("Updated Successfully");
				else 
					addActionError("Insert/Update Failed");
				
				coverageList();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "ofsCoverageMaster";
	}
	
	public String schemeList(){
		try {
			bean.setSchemeList(service.schemeList());
			bean.setMode("");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "ofsSchemeMaster";
	}
	public String editScheme(){
		try {
			if("edit".equalsIgnoreCase(bean.getMode())){
				service.setSchemeDtls(bean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "ofsSchemeMaster";
	}
	public String updateScheme(){
		try {
			validation("scheme");
			if(!hasActionErrors()){
				int ins=service.updateScheme(bean);
				if("add".equalsIgnoreCase(bean.getMode()) && ins>0)
					addActionMessage("Added Successfully");
				else if("edit".equalsIgnoreCase(bean.getMode()) && ins>0)
					addActionMessage("Updated Successfully");
				else 
					addActionError("Insert/Update Failed");
				
				schemeList();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "ofsSchemeMaster";
	}
	
	public String contentList(){
		try {
			bean.setContentList(service.contentList(bean));
			bean.setMode("");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "ofsContentMaster";
	}
	public String editContent(){
		try {
			if("edit".equalsIgnoreCase(bean.getMode())){
				service.setContentDtls(bean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "ofsContentMaster";
	}
	public String updateContent(){
		try {
			validation("content");
			if(!hasActionErrors()){
				int ins=service.updateContent(bean);
				if("add".equalsIgnoreCase(bean.getMode()) && ins>0)
					addActionMessage("Added Successfully");
				else if("edit".equalsIgnoreCase(bean.getMode()) && ins>0)
					addActionMessage("Updated Successfully");
				else 
					addActionError("Insert/Update Failed");
				
				contentList();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "ofsContentMaster";
	}
	
	public String coverageDtlList(){
		try {
			bean.setContentDropDownList(service.contentDropDownList(bean));
			if("list".equalsIgnoreCase(bean.getMode())){
				if(StringUtils.isBlank(bean.getContentId()))
					addActionError("Please Select Content Name");
				else{
					bean.setCoverageIncludedList(service.coverageIncludedList(bean));
					bean.setCoverageExcludedList(service.coverageExcludedList(bean));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "ofsCoverageDtl";
	}
	
	public String editCoverageDtl(){
		try {
			bean.setDisplayOrderList(service.displayOrderList(bean));
			if("edit".equalsIgnoreCase(bean.getMode())){
				service.setCoverageEditDtls(bean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "ofsCoverageDtl";
	}
	
	public String updateCoverageDtl(){
		try {
			validation("coverageDtl");
			if(!hasActionErrors()){
				int ins=service.updateCoverageDtl(bean);
				/*if("add".equalsIgnoreCase(bean.getMode()) && ins>0)
					addActionMessage("Added Successfully");
				else if("edit".equalsIgnoreCase(bean.getMode()) && ins>0)
					addActionMessage("Updated Successfully");*/
				if(ins>0)
					addActionMessage("Insert/Update Success");
				else 
					addActionError("Insert/Update Failed");
				
				bean.setMode("list");
				coverageDtlList();
			}
			else{
				bean.setMode("edit");
				bean.setDisplayOrderList(service.displayOrderList(bean));
				//editCoverageDtl();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "ofsCoverageDtl";
	}
	

	public String editConfig(){
		try {
			bean.setConfigType("config");
			if(!"addMore".equalsIgnoreCase(bean.getMode()) && !"insert".equalsIgnoreCase(bean.getMode()))
				bean.setConfigDetails(service.getConfigDetails(bean));
			int add=Integer.parseInt(StringUtils.isBlank(bean.getAdd())?"0":bean.getAdd());
			if(StringUtils.isBlank(bean.getAdd()))
				add=bean.getConfigDetails().size()==0?3:bean.getConfigDetails().size();
			if("addMore".equalsIgnoreCase(bean.getMode()))
				add++;
			bean.setAdd(String.valueOf(add));
			bean.setColumnNames(service.getColumnNames("OFS_TRANSACTION_DETAILS"));
			bean.setTableNames(service.getTableNames());
			bean.setDisplayOrderList(service.displayOrderList(bean));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "ofsConfigDtl";
	}
	
	public String updConfig(){
		
		String forward="ofsConfigDtl";
		int add=Integer.parseInt(StringUtils.isBlank(bean.getAdd())?"0":bean.getAdd());
		String amendId=service.getMaxAmendId(bean);
		String values="";
		String err="";
		java.util.ArrayList val=new java.util.ArrayList();
		String[][] values1=new String[add][12];
		for(int k=0; k<add; k++){
			values+=",'"+(bean.getDisplayNamesConfig().get(k)==null?"":bean.getDisplayNamesConfig().get(k))+"'";
			values+=",'"+(bean.getSumControlTypeConfig().get(k)==null?"":bean.getSumControlTypeConfig().get(k))+"'";
			values+=",'"+(bean.getDisplayOrderConfig().get(k)==null?"":bean.getDisplayOrderConfig().get(k))+"'";
		 /* values+=",'"+(request.getParameter("dropdownKey"+k)==null?"":request.getParameter("dropdownKey"+k))+"'";
			values+=",'"+(request.getParameter("dropdownValue"+k)==null?"":request.getParameter("dropdownValue"+k))+"'"; */
			values+=",'"+""+"'";
			values+=",'"+""+"'";
			values+=",'"+(bean.getDropdownTableConfig().get(k)==null?"":bean.getDropdownTableConfig().get(k))+"'";
			values+=",'"+(bean.getValidationTypeConfig().get(k)==null?"":bean.getValidationTypeConfig().get(k))+"'";
			values+=",'"+(bean.getDestTableConfig().get(k)==null?"":bean.getDestTableConfig().get(k))+"'";
			values+=",'"+(bean.getDestColumnConfig().get(k)==null?"":bean.getDestColumnConfig().get(k))+"'";
			values+=",'"+amendId+"'";
			
			try {
				values+=",'"+(bean.getStatusConfig().get(k)==null?"":bean.getStatusConfig().get(k))+"'";
			} catch (Exception e) {
				values+=",'"+""+"'";
			}
			try {
				values+=",'"+(bean.getMandatoryConfig().get(k)==null?"":bean.getMandatoryConfig().get(k))+"'";
			} catch (Exception e) {
				values+=",'"+""+"'";
			}
			try {
				values+=",'"+(bean.getTotalSumInsuredConfig().get(k)==null?"":bean.getTotalSumInsuredConfig().get(k))+"'";
			} catch (Exception e) {
				values+=",'"+""+"'";
			}
			
			System.out.println("Values: "+values);
			
			values1[k][0]=bean.getDisplayNamesConfig().get(k)==null?"":bean.getDisplayNamesConfig().get(k);
			values1[k][1]=bean.getSumControlTypeConfig().get(k)==null?"":bean.getSumControlTypeConfig().get(k);
		    values1[k][2]=bean.getDisplayOrderConfig().get(k)==null?"":bean.getDisplayOrderConfig().get(k);
		 /* values1[k][3]=request.getParameter("dropdownKey"+k)==null?"":request.getParameter("dropdownKey"+k); 
			values1[k][4]=request.getParameter("dropdownValue"+k)==null?"":request.getParameter("dropdownValue"+k); */
		    values1[k][3]="";
		    values1[k][4]="";
			values1[k][5]=bean.getDropdownTableConfig().get(k)==null?"":bean.getDropdownTableConfig().get(k);
			values1[k][6]=bean.getValidationTypeConfig().get(k)==null?"":bean.getValidationTypeConfig().get(k);
			values1[k][7]=bean.getDestTableConfig().get(k)==null?"":bean.getDestTableConfig().get(k);
			values1[k][8]=bean.getDestColumnConfig().get(k)==null?"":bean.getDestColumnConfig().get(k);
			
			try {
				values1[k][9]=bean.getStatusConfig().get(k)==null?"":bean.getStatusConfig().get(k);
			} catch (Exception e) {
				values1[k][9]="";
			}
			try {
				values1[k][10]=bean.getMandatoryConfig().get(k)==null?"":bean.getMandatoryConfig().get(k);
			} catch (Exception e) {
				values1[k][10]="";
			}
			try {
				values1[k][11]=bean.getTotalSumInsuredConfig().get(k)==null?"":bean.getTotalSumInsuredConfig().get(k);
			} catch (Exception e) {
				values1[k][11]="";
			}
			
			System.out.println("Values[][]: "+values1.length);
			
			if(!(values1[k][0].equals("")&&values1[k][1].equals("")&&values1[k][2].equals("")
					&&values1[k][3].equals("")&&values1[k][4].equals("")&&values1[k][5].equals("")&&values1[k][6].equals("")
					&&values1[k][8].equals("") /* &&values1[k][9].equals("")&&values1[k][10].equals("") */ ))
			{
				val.add(values);
				err=service.validString(values1[k][0],4);
				//addActionError(err);
				if((err!=null && err.length()>0)||values1[k][0].equals(""))
				{
					addActionError(err+" Display Name in Row "+(k+1));
				}
				err=service.validString(values1[k][1],4);
				if((err!=null && err.length()>0)||values1[k][1].equals(""))
				{
					addActionError(err+" Control Type in Row "+(k+1));
				}
				err=service.validInteger(values1[k][2]);
				if((err!=null && err.length()>0)||values1[k][2].equals(""))
				{
					addActionError(err+" Display Order in Row "+(k+1));
				}
			 /* err=v.validString(values1[k][3],4); */
					
				if("dropdown".equalsIgnoreCase(values1[k][1]))
				{
					/*if(values1[k][3].equals(""))
					{
						error+=err+" Dropdown Key in Row "+(k+1)+"</br>";
					}
					if(values1[k][4].equals(""))
					{
						error+=err+" Dropdown Value in Row "+(k+1)+"</br>";
					}*/
					if(values1[k][5].equals(""))
					{
						//error+=err+" Dropdown Table in Row "+(k+1)+"</br>";
						addActionError("Needed Dropdown Table in Row "+(k+1));
					}
					
				}
				err=service.validString(values1[k][6],4);
				if((err!=null && err.length()>0)||values1[k][6].equals(""))
				{
					addActionError(err+" Validation Type in Row "+(k+1));
				}
				if(values1[k][7].equalsIgnoreCase(""))
				{
					addActionError("Select Destination Table in Row "+(k+1));
				}
				if(values1[k][8].equalsIgnoreCase(""))
				{
					addActionError("Select Destination Column in Row "+(k+1));
				}
				if(values1[k][9].equalsIgnoreCase(""))
				{
					addActionError("Select Status in Row "+(k+1));
				}
				if(values1[k][10].equalsIgnoreCase(""))
				{
					addActionError("Select Mandatory in Row "+(k+1));
				}
				if(values1[k][11].equalsIgnoreCase(""))
				{
					addActionError("Select Mandatory in Row "+(k+1));
				}
			}
			values="";
			
		}
		
		if(!hasActionErrors()){
			int ins=0;
			for(int k=0; k<val.size(); k++)
			{
				ins=service.insertConfigDetails(bean,(String)val.get(k));
			}
			if(ins>0)
				addActionMessage("Inserted Successfully");
			else
				addActionError("Insertion Failed");
			//bean.setMode("list");
			//forward=coverageDtlList();
			bean.setMode("insert");
			forward=editConfig();
		}
		else{
			bean.setMode("insert");
			forward=editConfig();
		}
		
		return forward;
	}

	public String editFormula(){
		try {
			bean.setConfigType("formula");
			service.getFormulaDetails(bean);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "ofsConfigDtl";
	}
	
	public String updFormula(){
		try {
			bean.setConfigType("formula");
			validation("formula");
			int ins=0;
			if(!hasActionErrors()){
				ins=service.insertFormulaDetails(bean);
				if(ins>0)
					addActionMessage("Inserted Successfully");
				else
					addActionError("Insertion Failed");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "ofsConfigDtl";
	}                        
	
	public String subCoverage(){
		try {
			bean.setContentDropDownList(service.contentDropDownList(bean));
			if("list".equalsIgnoreCase(bean.getMode())){
				if(StringUtils.isBlank(bean.getContentId()))
					addActionError("Please Select Content Name");
				else{
					bean.setSubCoverageList(service.subCoverageList(bean));
					//bean.setCoverageIncludedList(service.coverageIncludedList(bean));
					//bean.setCoverageExcludedList(service.coverageExcludedList(bean));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "ofsSubCoverageDtl";
	}
	
	public String subCoverageDtl(){
		try {
			bean.setSubCoverageIncludedList(service.subCoverageIncludedList(bean));
			bean.setSubCoverageExcludedList(service.subCoverageExcludedList(bean));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "ofsSubCoverageDtl";
	}
	
	public String editSubCoverageDtl(){			
		try {
			bean.setDisplayOrderList(service.subDisplayOrderList(bean));
			if("edit".equalsIgnoreCase(bean.getMode())){
				service.setSubCoverageEditDtls(bean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "ofsSubConfigDtl";
	}
	
	public String updSubCoverageDtl(){
		validation("subCoverageDtl");
		String forward="ofsSubConfigDtl";
		bean.setDisplayOrderList(service.subDisplayOrderList(bean));
		if(!hasActionErrors()){
			int ins=service.updateSubCoverageDtl(bean);
			if(ins>0)
				addActionMessage("Inserted Successfully");
			else
				addActionError("Insertion Failed");
			bean.setMode("add");
			forward=subCoverageDtl();
		}
		return forward;
	}
	
	public String editSubConfig(){
		try {
			bean.setConfigType("config");
			if(!"addMore".equalsIgnoreCase(bean.getMode()) && !"insert".equalsIgnoreCase(bean.getMode()))
				bean.setConfigDetails(service.getConfigDetails(bean));
			int add=Integer.parseInt(StringUtils.isBlank(bean.getAdd())?"0":bean.getAdd());
			if(StringUtils.isBlank(bean.getAdd()))
				add=bean.getConfigDetails().size()==0?3:bean.getConfigDetails().size();
			if("addMore".equalsIgnoreCase(bean.getMode()))
				add++;
			bean.setAdd(String.valueOf(add));
			bean.setColumnNames(service.getColumnNames("OFS_TRANSACTION_DETAILS"));
			bean.setTableNames(service.getTableNames());
			bean.setDisplayOrderList(service.subDisplayOrderList(bean));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "ofsSubConfigDtl";
	}
	
	public String updSubConfig(){
		
		String forward="ofsSubConfigDtl";
		int add=Integer.parseInt(StringUtils.isBlank(bean.getAdd())?"0":bean.getAdd());
		String amendId=service.getMaxAmendId(bean);
		String values="";
		String err="";
		java.util.ArrayList val=new java.util.ArrayList();
		String[][] values1=new String[add][12];
		for(int k=0; k<add; k++){
			values+=",'"+(bean.getDisplayNamesConfig().get(k)==null?"":bean.getDisplayNamesConfig().get(k))+"'";
			values+=",'"+(bean.getSumControlTypeConfig().get(k)==null?"":bean.getSumControlTypeConfig().get(k))+"'";
			values+=",'"+(bean.getDisplayOrderConfig().get(k)==null?"":bean.getDisplayOrderConfig().get(k))+"'";
		 /* values+=",'"+(request.getParameter("dropdownKey"+k)==null?"":request.getParameter("dropdownKey"+k))+"'";
			values+=",'"+(request.getParameter("dropdownValue"+k)==null?"":request.getParameter("dropdownValue"+k))+"'"; */
			values+=",'"+""+"'";
			values+=",'"+""+"'";
			values+=",'"+(bean.getDropdownTableConfig().get(k)==null?"":bean.getDropdownTableConfig().get(k))+"'";
			values+=",'"+(bean.getValidationTypeConfig().get(k)==null?"":bean.getValidationTypeConfig().get(k))+"'";
			values+=",'"+(bean.getDestTableConfig().get(k)==null?"":bean.getDestTableConfig().get(k))+"'";
			values+=",'"+(bean.getDestColumnConfig().get(k)==null?"":bean.getDestColumnConfig().get(k))+"'";
			values+=",'"+amendId+"'";
			
			try {
				values+=",'"+(bean.getStatusConfig().get(k)==null?"":bean.getStatusConfig().get(k))+"'";
			} catch (Exception e) {
				values+=",'"+""+"'";
			}
			try {
				values+=",'"+(bean.getMandatoryConfig().get(k)==null?"":bean.getMandatoryConfig().get(k))+"'";
			} catch (Exception e) {
				values+=",'"+""+"'";
			}
			try {
				values+=",'"+(bean.getTotalSumInsuredConfig().get(k)==null?"":bean.getTotalSumInsuredConfig().get(k))+"'";
			} catch (Exception e) {
				values+=",'"+""+"'";
			}
			
			System.out.println("Values: "+values);
			
			values1[k][0]=bean.getDisplayNamesConfig().get(k)==null?"":bean.getDisplayNamesConfig().get(k);
			values1[k][1]=bean.getSumControlTypeConfig().get(k)==null?"":bean.getSumControlTypeConfig().get(k);
		    values1[k][2]=bean.getDisplayOrderConfig().get(k)==null?"":bean.getDisplayOrderConfig().get(k);
		 /* values1[k][3]=request.getParameter("dropdownKey"+k)==null?"":request.getParameter("dropdownKey"+k); 
			values1[k][4]=request.getParameter("dropdownValue"+k)==null?"":request.getParameter("dropdownValue"+k); */
		    values1[k][3]="";
		    values1[k][4]="";
			values1[k][5]=bean.getDropdownTableConfig().get(k)==null?"":bean.getDropdownTableConfig().get(k);
			values1[k][6]=bean.getValidationTypeConfig().get(k)==null?"":bean.getValidationTypeConfig().get(k);
			values1[k][7]=bean.getDestTableConfig().get(k)==null?"":bean.getDestTableConfig().get(k);
			values1[k][8]=bean.getDestColumnConfig().get(k)==null?"":bean.getDestColumnConfig().get(k);
			
			try {
				values1[k][9]=bean.getStatusConfig().get(k)==null?"":bean.getStatusConfig().get(k);
			} catch (Exception e) {
				values1[k][9]="";
			}
			try {
				values1[k][10]=bean.getMandatoryConfig().get(k)==null?"":bean.getMandatoryConfig().get(k);
			} catch (Exception e) {
				values1[k][10]="";
			}
			try {
				values1[k][11]=bean.getTotalSumInsuredConfig().get(k)==null?"":bean.getTotalSumInsuredConfig().get(k);
			} catch (Exception e) {
				values1[k][11]="";
			}
			
			System.out.println("Values[][]: "+values1.length);
			
			if(!(values1[k][0].equals("")&&values1[k][1].equals("")&&values1[k][2].equals("")
					&&values1[k][3].equals("")&&values1[k][4].equals("")&&values1[k][5].equals("")&&values1[k][6].equals("")
					&&values1[k][8].equals("") /* &&values1[k][9].equals("")&&values1[k][10].equals("") */ ))
			{
				val.add(values);
				err=service.validString(values1[k][0],4);
				//addActionError(err);
				if((err!=null && err.length()>0)||values1[k][0].equals(""))
				{
					addActionError(err+" Display Name in Row "+(k+1));
				}
				err=service.validString(values1[k][1],4);
				if((err!=null && err.length()>0)||values1[k][1].equals(""))
				{
					addActionError(err+" Control Type in Row "+(k+1));
				}
				err=service.validInteger(values1[k][2]);
				if((err!=null && err.length()>0)||values1[k][2].equals(""))
				{
					addActionError(err+" Display Order in Row "+(k+1));
				}
			 /* err=v.validString(values1[k][3],4); */
					
				if("dropdown".equalsIgnoreCase(values1[k][1]))
				{
					/*if(values1[k][3].equals(""))
					{
						error+=err+" Dropdown Key in Row "+(k+1)+"</br>";
					}
					if(values1[k][4].equals(""))
					{
						error+=err+" Dropdown Value in Row "+(k+1)+"</br>";
					}*/
					if(values1[k][5].equals(""))
					{
						//error+=err+" Dropdown Table in Row "+(k+1)+"</br>";
						addActionError("Needed Dropdown Table in Row "+(k+1));
					}
					
				}
				err=service.validString(values1[k][6],4);
				if((err!=null && err.length()>0)||values1[k][6].equals(""))
				{
					addActionError(err+" Validation Type in Row "+(k+1));
				}
				if(values1[k][7].equalsIgnoreCase(""))
				{
					addActionError("Select Destination Table in Row "+(k+1));
				}
				if(values1[k][8].equalsIgnoreCase(""))
				{
					addActionError("Select Destination Column in Row "+(k+1));
				}
				if(values1[k][9].equalsIgnoreCase(""))
				{
					addActionError("Select Status in Row "+(k+1));
				}
				if(values1[k][10].equalsIgnoreCase(""))
				{
					addActionError("Select Mandatory in Row "+(k+1));
				}
				if(values1[k][11].equalsIgnoreCase(""))
				{
					addActionError("Select Mandatory in Row "+(k+1));
				}
			}
			values="";
			
		}
		
		if(!hasActionErrors()){
			int ins=0;
			for(int k=0; k<val.size(); k++)
			{
				ins=service.insertConfigDetails(bean,(String)val.get(k));
			}
			if(ins>0)
				addActionMessage("Inserted Successfully");
			else
				addActionError("Insertion Failed");
			//bean.setMode("list");
			//forward=coverageDtlList();
			bean.setMode("insert");
			forward=editSubConfig();
		}
		else{
			bean.setMode("insert");
			forward=editSubConfig();
		}
		
		return forward;
	}
	
	private void validation(String type) {
		if("coverage".equalsIgnoreCase(type)){
			if(StringUtils.isBlank(bean.getCoverageName()))
				addActionError("Please Enter Coverage Name");
			if(StringUtils.isBlank(bean.getCoverageDisplayName()))
				addActionError("Please Enter Coverage Display Name");
			if(StringUtils.isBlank(bean.getCoverageCoreCode()))
				addActionError("Please Enter Core Code");
		}
		else if("scheme".equalsIgnoreCase(type)){
			if(StringUtils.isBlank(bean.getSchemeName()))
				addActionError("Please Enter Scheme Name");
			if(StringUtils.isBlank(bean.getSchemeCoreCode()))
				addActionError("Please Enter Core Code");
		}
		else if("content".equalsIgnoreCase(type)){
			if(StringUtils.isBlank(bean.getContentDesc()))
				addActionError("Please Enter Content Description");
			if(StringUtils.isBlank(bean.getMinimumPremium()))
				addActionError("Please Enter Minimum Premium");
			if(StringUtils.isBlank(bean.getContentCoreCode()))
				addActionError("Please Enter Core Code");
		}
		else if("coverageDtl".equalsIgnoreCase(type)){
			if (StringUtils.isBlank(bean.getSchemeId())) {
				addActionError("Scheme Not valid");
			}
			if (StringUtils.isBlank(bean.getContentId())) {
				addActionError("Content Type not valid");
			}
			if (StringUtils.isBlank(bean.getCoverageId())) {
				addActionError("Coverage Name not valid");
			}
			if (StringUtils.isBlank(bean.getCoverageType())) {
				addActionError("Select Coverage Type");
			}
			if (StringUtils.isBlank(bean.getUploadOption())) {
				addActionError("Select Upload Option");
			}
			if (StringUtils.isBlank(bean.getDisplayOrder())) {
				addActionError("Enter Display Order");
			}
			if (StringUtils.isBlank(bean.getControlType())) {
				addActionError("Select Control Type");
			}
			if (bean.getCoverageLimit().length() == 0) {
			//	error += "* Select Coverage Limit<br>";
			}
			if (StringUtils.isBlank(bean.getSumCoverageLimit())) {
				//error += "* insert Sum Insure Coverage Limit<br>";
			} else {
				try {
					Integer.parseInt(bean.getSumCoverageLimit());
				} catch (Exception e) {
					addActionError("insert Valid Sum Insure Coverage Limit");
				}

			}if (StringUtils.isBlank(bean.getMinSumLimit())) {
				//error += "* insert Sum Insure Coverage Limit<br>";
			} else {
				try {
					Integer.parseInt(bean.getMinSumLimit());
				} catch (Exception e) {
					addActionError("insert Valid Minimum Sum Insure ");
				}

			}
			/*if (excess.length() == 0) {
				error += "* Enter Excess Value<br>";
			}*/
			
			//Calculation condition added to base rate validation for grid concept by chinna
			if(StringUtils.isNotBlank(bean.getCalculationType())&& !"G".equalsIgnoreCase(bean.getCalculationType()))
			{
				if (StringUtils.isBlank(bean.getBaseRate())) {
					addActionError("Enter Bass Rate");
				} else {
					try {
						Double.parseDouble(bean.getBaseRate());
					} catch (Exception e) {
						addActionError("Enter proper Bass Rate");
					}
				}
			}
			//End
			
			if (StringUtils.isBlank(bean.getCalculationStatus())) {
				addActionError("Select Calculation Status");
			}
			if (StringUtils.isBlank(bean.getCalculationType())) {
				addActionError("Select Calculation Type");
			}
			if (StringUtils.isBlank(bean.getStatus())) {
				addActionError("Select Status");
			}
			if (StringUtils.isBlank(bean.getEffectiveDate())) {
				addActionError("Select Effective Date Type");
			} else {
				if (!service.isGreaterEffectiveDate(bean,"cover")) {
					addActionError("Select Greater Effective Date");
				}
			}
			if (StringUtils.isBlank(bean.getRsaCode())) {
				addActionError("Enter Core Application Code");
			}
			if (StringUtils.isBlank(bean.getHelpContent())) {
				addActionError("Enter Help Content");
			}
		}
		else if("formula".equalsIgnoreCase(type)){
			if (StringUtils.isBlank(bean.getFormula())) {
				addActionError("Enter Formula");
			}
			if (StringUtils.isBlank(bean.getStatus())) {
				addActionError("Select Status");
			}
		}
		else if("subCoverageDtl".equalsIgnoreCase(type)){
			if (StringUtils.isBlank(bean.getDisplayOrder())) {
				addActionError("Enter Display Order");
			}
			if (StringUtils.isBlank(bean.getControlType())) {
				addActionError("Select Control Type");
			}
			if (StringUtils.isBlank(bean.getCoverageLimit())) {
				//error += "* Select Coverage Limit<br>";
			}
			if (StringUtils.isBlank(bean.getSubRate())) {
				addActionError("Enter Sub Rate");
			} else {
				try {
					Double.parseDouble(bean.getSubRate());
				} catch (Exception e) {
					addActionError("Enter valid Sub Rate");
				}
			}
			if (StringUtils.isBlank(bean.getCalculationType())) {
				addActionError("Select Calculation Type");
			}
			if (StringUtils.isBlank(bean.getEffectiveDate())) {
				addActionError("Select Effective Date Type");
			} else {
				if (!service.isGreaterEffectiveDate(bean,"subCover")) {
					addActionError(" Select Greater Effective Date");
				}
			}
			if (StringUtils.isBlank(bean.getRsaCode())) {
				addActionError("Enter Core Application Code");
			}
			if (StringUtils.isBlank(bean.getStatus())) {
				addActionError("Select Status");
			}
		}
	}
	public String fieldDetail(){
		try {
			bean.setContentDropDownList(service.contentDropDownList(bean));
			if("list".equalsIgnoreCase(bean.getMode())){
				if(StringUtils.isBlank(bean.getContentId()))
					addActionError("Please Select Content Name");
				else{
					bean.setCoverageIncludedList(service.fieldIncludedList(bean));
					//bean.setCoverageExcludedList(service.coverageExcludedList(bean));
				}
			}
			else if("edit".equalsIgnoreCase(bean.getMode())){
				bean.setDisplayOrderList(service.displayOrderListNew(bean));
				service.setCoverageEditDtls(bean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "ofsFieldDtl";
	}
	
	public String editFieldDtl(){
		try {
			bean.setDisplayOrderList(service.displayOrderListNew(bean));
			bean.setCoverageExcludedList(service.fieldExcludedList(bean));
			service.setCoverageEditDtlsNew(bean);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "ofsFieldDtl";
	}
	
	public String updateField(){
		validationNew("updateField");
		if(!hasActionErrors()){
			int ins=service.insertFieldDetails(bean);
			if(ins>0)
				addActionMessage("Field Details Inserted Successfully");
			else
				addActionError("Field Details Insertion Failed");
			
			bean.setMode("list");
			fieldDetail();
		}else{
			editFieldDtl();
		}
		
		return "ofsFieldDtl";
	}
	
	public String linkedBroker(){
		try {
			bean.setBrokerLinkedList(service.getBrokerLinkedList());
			if(StringUtils.isNotBlank(bean.getBrokerLinked()))
				bean.setBrokerLinkedField(service.getBrokerLinkedFieldList(bean));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "linkedBrokerDtl";
	}
	public String editBrokerField(){
		try {
			bean.setContentId("0");
			bean.setBrokerLinkedList(service.getBrokerLinkedList());
			bean.setCoverageIncludedList(service.fieldIncludedList(bean));
			if("edit".equalsIgnoreCase(bean.getMode())){
				service.setCoverageEditDtlsBroker(bean);
			}else if("add".equalsIgnoreCase(bean.getMode())){
				service.setDefaultBrokerCoverageDtls(bean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "linkedBrokerDtl";
	}
	
	public String updateFieldDtl(){
		String forward="linkedBrokerDtl";
		validationNew("updateFieldDtl");
		if(!hasActionErrors()){
			int ins=service.updateFieldDetails(bean);
			if(ins>0){
				addActionMessage("Field Details Inserted Successfully");
				if("G".equalsIgnoreCase(bean.getCalculationType())){
					forward=gridEdit();
				}
			}
			else
				addActionError("Field Details Insertion Failed");
			
			bean.setMode("list");
			linkedBroker();
			
		}else{
			bean.setBrokerLinkedList(service.getBrokerLinkedList());
			bean.setCoverageIncludedList(service.fieldIncludedList(bean));
		}
		return forward;
	}
	
	public String gridEdit(){
		try {
			int add=Integer.parseInt(StringUtils.isBlank(bean.getAdd())?"0":bean.getAdd());
			if(!"addMore".equalsIgnoreCase(bean.getMode()) && !"insert".equalsIgnoreCase(bean.getMode()))
				bean.setGridDetails(service.getGridDetails(bean));
			if(StringUtils.isBlank(bean.getAdd()))
				add=bean.getGridDetails().size()==0?3:bean.getGridDetails().size();
			if("addMore".equalsIgnoreCase(bean.getMode()))
				add++;
			bean.setAdd(String.valueOf(add));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return "gridDetail";
	}
	
	public String gridUpdate(){
		String forward="gridDetail";
		int add=Integer.parseInt(StringUtils.isBlank(bean.getAdd())?"0":bean.getAdd());
		String amendId=service.getMaxAmendIdGrid(bean);
		String values="";
		String err="";
		java.util.ArrayList val=new java.util.ArrayList();
		String[][] values1=new String[add][7];
		for(int k=0; k<add; k++){
			values+=",'"+(bean.getStartSum().get(k)==null?"":bean.getStartSum().get(k))+"'";
			values+=",'"+(bean.getEndSum().get(k)==null?"":bean.getEndSum().get(k))+"'";
			values+=",'"+(bean.getBaseRateGrid().get(k)==null?"":bean.getBaseRateGrid().get(k))+"'";
			values+=",'"+""+"'";
			values+=",'"+amendId+"'";
			try {
				values+=",'"+(bean.getStatusGrid().get(k)==null?"":bean.getStatusGrid().get(k))+"'";
			} catch (Exception e) {
				values+=",'"+""+"'";
			}
			values+=",'"+""+"'";
			values+=",to_date('"+(bean.getEffectiveDateGrid().get(k)==null?"":bean.getEffectiveDateGrid().get(k))+"', 'dd-mm-yy')";
			values+=",to_date('"+(bean.getEffectiveDateGrid().get(k)==null?"":bean.getEffectiveDateGrid().get(k))+"', 'dd-mm-yy')+365";
			try {
				values+=",'"+(bean.getCalculationTypeGrid().get(k)==null?"":bean.getCalculationTypeGrid().get(k))+"'";
			} catch (Exception e) {
				values+=",'"+""+"'";
			}
			
			
			System.out.println("Values: "+values);
			
			values1[k][0]="";
			values1[k][1]=bean.getStartSum().get(k)==null?"":bean.getStartSum().get(k);
		    values1[k][2]=bean.getEndSum().get(k)==null?"":bean.getEndSum().get(k);
			values1[k][3]=bean.getBaseRateGrid().get(k)==null?"":bean.getBaseRateGrid().get(k);
			try {
				values1[k][4]=bean.getCalculationTypeGrid().get(k)==null?"":bean.getCalculationTypeGrid().get(k);
			} catch (Exception e) {
				values1[k][4]="";
			}
			try {
				values1[k][5]=bean.getStatusGrid().get(k)==null?"":bean.getStatusGrid().get(k);
			} catch (Exception e) {
				values1[k][5]="";
			}
			values1[k][6]=bean.getEffectiveDateGrid().get(k)==null?"":bean.getEffectiveDateGrid().get(k);
			
			//Validation goes here
			if(!(values1[k][0].equals("")&&values1[k][1].equals("")&&values1[k][2].equals("")
					&&values1[k][3].equals("")&&values1[k][4].equals("")&&values1[k][5].equals("")&&values1[k][6].equals("")))
			{
				val.add(values);
				/*err=v.validInteger(values1[k][0]);
				if((err!=null && err.length()>0)||values1[k][0].equals(""))
				{
					error+=err+" Minimum Premim in Row "+(k+1)+"</br>";
				}*/
				err=service.validInteger(values1[k][1]);
				if((err!=null && err.length()>0)||values1[k][1].equals(""))
				{
					addActionError(err+" Start Sum Insured in Row "+(k+1));
				}
				err=service.validInteger(values1[k][2]);
				if((err!=null && err.length()>0)||values1[k][2].equals(""))
				{
					addActionError(err+" End Sum Insured in Row "+(k+1));
				}
				if(values1[k][1].length()>0 && values1[k][2].length()>0)
				{
					try{
					int val1=Integer.parseInt(values1[k][1]);
					int val2=Integer.parseInt(values1[k][2]);
						if(val2<val1)
						{
							addActionError(" End Sum Insured is less than Start Sum Insured in Row "+(k+1));
						}
					}catch(Exception e){
						addActionError("Invalid End Sum Insured in Row "+(k+1));
					}
				}
				if (values1[k][3].length() == 0) {
					addActionError("Invalid Bass Rate in Row "+(k+1));
				} else {
					try {
						Double.parseDouble(values1[k][3]);
					} catch (Exception e) {
						addActionError("Invalid Bass Rate in Row "+(k+1));
					}
				}
				
				if(values1[k][4].equalsIgnoreCase(""))
				{
					addActionError("Select Calculation Type in Row "+(k+1));
				}
				if(values1[k][5].equalsIgnoreCase(""))
				{
					addActionError("Select Status in Row "+(k+1));
				}
				if(values1[k][6].equalsIgnoreCase(""))
				{
					addActionError("Select Effective Date in Row "+(k+1));
				}
			}
			values="";
		}
		
		if(!hasActionErrors()){
			int ins=0;
			for(int k=0; k<val.size(); k++)
			{
				ins=service.insertGridDetails(bean,(String)val.get(k));
			}
			if(ins>0)
				addActionMessage("Grid Details Inserted Successfully");
			else
				addActionError("Grid Details Insertion Failed");
			//bean.setMode("list");
			//forward=coverageDtlList();
			bean.setMode("insert");
			forward=gridEdit();
		}
		else{
			bean.setMode("insert");
			forward=gridEdit();
		}
		
		return forward;
	}

	private void validationNew(String type) {
		if("updateField".equalsIgnoreCase(type)){
			if(StringUtils.isBlank(bean.getFieldName()) && "add".equalsIgnoreCase(bean.getMode()))
				addActionError("Please Choose Field Name");
			if(StringUtils.isBlank(bean.getDisplayOrder()))
				addActionError("Please Choose Display Order");
			if(StringUtils.isBlank(bean.getSumControlType()))
				addActionError("Please Choose Coverage Display Type");
			if(StringUtils.isBlank(bean.getControlType()))
				addActionError("Please Choose Option Display Type");
			
			if (StringUtils.isBlank(bean.getCoverageType())) {
				addActionError("Select Coverage Type");
			}
			
			if (StringUtils.isBlank(bean.getSumCoverageLimit())) {
				
			} else {
				try {
					Integer.parseInt(bean.getSumCoverageLimit());
				} catch (Exception e) {
					addActionError("insert Valid Sum Insure Coverage Limit");
				}

			}
			
			if (StringUtils.isBlank(bean.getMinSumLimit())) {
			} else {
				try {
					Integer.parseInt(bean.getMinSumLimit());
				} catch (Exception e) {
					addActionError("insert Valid Minimum Sum Insure ");
				}

			}
			
			if(StringUtils.isNotBlank(bean.getCalculationType())&& !"G".equalsIgnoreCase(bean.getCalculationType())){
				if (StringUtils.isBlank(bean.getBaseRate())) {
					addActionError("Enter Bass Rate");
				} else {
					try {
						Double.parseDouble(bean.getBaseRate());
					} catch (Exception e) {
						addActionError("Enter proper Bass Rate");
					}
				}
			}
			
			if (StringUtils.isBlank(bean.getCalculationStatus())) {
				addActionError("Select Calculation Status");
			}
			if (StringUtils.isBlank(bean.getCalculationType())) {
				addActionError("Select Calculation Type");
			}
			

			if (StringUtils.isBlank(bean.getStatus())) {
				addActionError("Select Status");
			}
			
		}
		else if("updateFieldDtl".equalsIgnoreCase(type)){
			/*if (StringUtils.isBlank(bean.getSchemeId())) {
				addActionError("Scheme Not valid");
			}
			if (StringUtils.isBlank(bean.getContentId())) {
				addActionError("Content Type not valid");
			}
			if (StringUtils.isBlank(bean.getCoverageId())) {
				addActionError("Coverage Name not valid");
			}*/
			if (StringUtils.isBlank(bean.getCoverageType())) {
				addActionError("Select Coverage Type");
			}
			if (StringUtils.isBlank(bean.getUploadOption())) {
				addActionError("Select Upload Option");
			}
			/*if (StringUtils.isBlank(bean.getDisplayOrder())) {
				addActionError("Enter Display Order");
			}
			if (StringUtils.isBlank(bean.getControlType())) {
				addActionError("Select Control Type");
			}*/
			if (bean.getCoverageLimit().length() == 0) {
			//	error += "* Select Coverage Limit<br>";
			}
			if (StringUtils.isBlank(bean.getSumCoverageLimit())) {
				//error += "* insert Sum Insure Coverage Limit<br>";
			} else {
				try {
					Integer.parseInt(bean.getSumCoverageLimit());
				} catch (Exception e) {
					addActionError("insert Valid Sum Insure Coverage Limit");
				}

			}if (StringUtils.isBlank(bean.getMinSumLimit())) {
				//error += "* insert Sum Insure Coverage Limit<br>";
			} else {
				try {
					Integer.parseInt(bean.getMinSumLimit());
				} catch (Exception e) {
					addActionError("insert Valid Minimum Sum Insure ");
				}

			}
			/*if (excess.length() == 0) {
				error += "* Enter Excess Value<br>";
			}*/
			
			//Calculation condition added to base rate validation for grid concept by chinna
			if(StringUtils.isNotBlank(bean.getCalculationType())&& !"G".equalsIgnoreCase(bean.getCalculationType()))
			{
				if (StringUtils.isBlank(bean.getBaseRate())) {
					addActionError("Enter Bass Rate");
				} else {
					try {
						Double.parseDouble(bean.getBaseRate());
					} catch (Exception e) {
						addActionError("Enter proper Bass Rate");
					}
				}
			}
			//End
			
			if (StringUtils.isBlank(bean.getCalculationStatus())) {
				addActionError("Select Calculation Status");
			}
			if (StringUtils.isBlank(bean.getCalculationType())) {
				addActionError("Select Calculation Type");
			}
			if (StringUtils.isBlank(bean.getStatus())) {
				addActionError("Select Status");
			}
			if (StringUtils.isBlank(bean.getEffectiveDate())) {
				addActionError("Select Effective Date Type");
			} else {
				if (!service.isGreaterEffectiveDateNew(bean,"cover")) {
					addActionError("Select Greater Effective Date");
				}
			}
			if (StringUtils.isBlank(bean.getRsaCode())) {
				addActionError("Enter Core Application Code");
			}
			if (StringUtils.isBlank(bean.getHelpContent())) {
				addActionError("Enter Help Content");
			}
		}
		
	}

}
