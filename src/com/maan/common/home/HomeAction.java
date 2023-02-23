package com.maan.common.home;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.json.annotations.JSON;

import com.maan.common.LogManager;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class HomeAction extends ActionSupport implements ModelDriven<HomeBean> {

	private static final long serialVersionUID = 1L;

	private final Map<String, Object> session=ActionContext.getContext().getSession();
	private final String loginId = (String)session.get("user");
	private final String LoginIdType = (String)session.get("LoginIdType");
	private final String userType = (String)session.get("usertype");
	private final String selectedBranch = (String)session.get("selectedBranch");
	
	private HomeBean bean = new HomeBean();
	private HomeService service = new HomeService();
	
	HttpServletRequest request = ServletActionContext.getRequest();
	ServletContext context = request.getSession().getServletContext();
	private String APK_FILE_LOCATION =context.getRealPath("/apr_24_late.apk");
	
	private String fileName;
	private InputStream inputStream;
	
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}
	
	public HomeBean getModel() {
		return bean;
	}
	
	/*public String User() {
		return "productSelection";
	}*/
	
	public String User() {
		String forward="productSelectionNew";
		try {
			removeSessionVariables();
			if("RSAIssuer".equalsIgnoreCase(userType) || "Broker".equalsIgnoreCase(userType) || "User".equalsIgnoreCase(userType)) {
				String actualBranch = service.getActualBranch(loginId);
				String branchCode = service.getDefaultBranch(LoginIdType,selectedBranch,actualBranch);//Default Branch Code
				String agencyCode = service.getAgencyCode(loginId);
				Map<String,String> brokerUserDetails = service.getBrokerUserDetails(branchCode);
				
				session.put("LoginBranchCode", branchCode);//Default Branch Code
				session.put("adminBranch", actualBranch);//Actual Branch Code
				session.put("defaultBranch", branchCode);
				session.put("BrokerDetails", brokerUserDetails);
				session.put("LANG", brokerUserDetails.get("LANG"));
				session.put("AgencyCode", agencyCode);
				//For Issuer
				if(service.isIssuer(userType)) {
					session.put("RSAISSUER", (String) session.get("user"));
				}
				if(service.getIsBrokerHasDC(loginId)) {
					session.put("CDMenu","Yes");
				}
			}
			if("Broker".equalsIgnoreCase(userType) || "RSAIssuer".equalsIgnoreCase(userType)){
				forward="productSelectionNew";
			}
			session.put("ProductDetails", service.getProductDetails(loginId, userType, (String)session.get("LoginBranchCode")));
		} catch(Exception exception) {
			LogManager.debug(exception);
		}
		return forward;
	}
	
	@JSON(serialize=false)
	public List<Map<String,Object>> getOfficeProductScheme() {
		return service.getOfficeProductScheme(loginId, (String)session.get("LoginBranchCode"));
	}
	
	private void removeSessionVariables() throws Exception {
		if(session.get("language") != null) {
	        session.remove("language");
		} if(session.get("freight")!=null) {
			session.remove("freight");
		} if(session.get("freightBroker")!=null) {
			session.remove("freightBroker");
		} if(session.get("product_id")!=null) {
			session.remove("product_id");
		} if(session.get("typeOfProduct")!=null) {
			session.remove("typeOfProduct");
		} if(session.get("quoteNo")!=null) {
			session.remove("quoteNo");
		} if(session.get("openCoverNo") !=null) {
			session.remove("openCoverNo");
		} if(session.get("brokerCode") !=null) {
			session.remove("brokerCode");
		}
	}
	public String FAQ(){
		return SUCCESS;
	}
	
	public String apkDownload(){
		System.out.println("Enter into apkDownlload()");
		String res="";
		try {
			FileInputStream fis = null;
			BufferedInputStream buff = null;
			fileName="MGen.apk";
			String filePath=APK_FILE_LOCATION;
			fis = new FileInputStream(filePath);
			           buff=new BufferedInputStream(fis);
			           setInputStream(buff);
			           res="stream";
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println("Exit from apkDownlload()");
		return res;
	}
}
