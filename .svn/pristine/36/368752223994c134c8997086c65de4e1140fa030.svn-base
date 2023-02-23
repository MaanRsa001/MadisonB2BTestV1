package com.maan.ClaimIntimation;

import java.io.FileInputStream;
import java.io.InputStream;

import org.apache.commons.lang.StringUtils;

import com.maan.ClaimIntimation.bean.ClaimIntimationBean;
import com.maan.ClaimIntimation.service.ClaimIntimationService;
import com.maan.common.LogManager;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class AdminClaimIntimationAction extends ActionSupport implements ModelDriven<ClaimIntimationBean>{
	private static final long serialVersionUID = 1L;
	ClaimIntimationBean bean=new ClaimIntimationBean();
	ClaimIntimationService service=new ClaimIntimationService();

	public ClaimIntimationBean getModel() {
		return bean;
	}
	
	private InputStream inputStream;
	
	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public String view(){
		bean.setClaimList(service.listClaim(bean));
		return "adminClaimList";
	}

	public String editClaim(){
	//	bean.setStatus(bean.getcStatus());
		service.getEditList(bean);
		return "updateClaim";
	}

	public String updateClaim(){
 		int count=0;
 		String ret="";
		try{
			validation();
			if(!hasActionErrors()){
				count = service.insertClaim(bean);
				if(count==0){
					addActionError("Update Failed");
				}
				else{
				//String success = "Claim Intimated Successfully for the Policy No : " +bean.getPolicyNo()+" and Your Claim Reference ID is"+bean.getClaimList(); 
					bean.setClaimList(service.listClaim(bean));
					ret="adminClaimList";
					addActionMessage("Claim Intimation Updated Successfully");
				}
			}
			else{
				service.getEditList(bean);
				ret="updateClaim";
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return ret;
	}

	private void validation() {
		if(StringUtils.isBlank(bean.getRemarks())){
			addActionError("Enter Remarks");
		}
	}
	
	public String docDld(){
		bean.setMode("download");
		bean.setClaimAttachedDocs(service.getDocDownloadList(bean));
		return "docDownload";
	}
	public String downloadDoc() {
		try {
			inputStream = new FileInputStream(bean.getFilePath());
			bean.setFileName(bean.getFileName());
		} catch(Exception exception) {
			LogManager.debug(exception);
		}
		return "stream";
	}	
}
