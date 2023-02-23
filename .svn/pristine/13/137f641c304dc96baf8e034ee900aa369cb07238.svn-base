package com.maan.admin.controller;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.maan.admin.DAO.MotorConfigBean;
import com.maan.common.LogManager;
import com.maan.common.exception.BaseException;
import com.maan.services.util.ValidationFormat;
public class MotorConfigController extends HttpServlet 
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String FORMPROCESS = "**********Here we populating the bean and displays the values in form*********";
	private static final String AREACOVERID = "areaCoverId";
	private static final String EFFECTIVEDATE="effDay";
	private static final String EFFECTIVEMONTH="effMonth";
	private static final String EFFECTIVEYEAR="effYear";
	private static final String STATUS="status";
	private static final String REMARKS ="remarks";
	private static final String VALIDCODE="Enter the Valid Code <br>";
	private static final String VALIDSTATUS="Select the Status <br>";
	private static final String DATEVALIDATION="Effective date should be greater or equal to previous effective date <br>";
	private static final String ERROR="error";
	private static final String BRANCHCODE="Branch Code::";
	private static final String POLICYTYPEID="policyTypeId";
	private static final String OPCOVERID="opCoverId";
	private static final String BANKID="bankId";
	private static final String BANKCODE="bankCOde";
	private static final String RESULTSIZE="Result size:";
	private static final String MOTORCONFIGBEAN="MotorConfigBean";
	private static final String CORECODE="coreAppCode";
	private static final String BANKENG="bankNameEng";
	private static final String BANKARABIC="bankNameArabic";
	private static final String COLORID="colorId";
	private static final String VTYPEID="vtypeId";
	private static final String TYPEOFBODYID="typeOfBodyId";
	private static final String CYLINDERID="cylId";
	private static final String FILEID="fileId";
	private static final String PRODUCTID="productId";
	private static final String GROUPID="groupId";
	private static final String VOLUNTARYID="voluntaryId";
	private static final String MODE="mode";
	
	public void doGet(final HttpServletRequest request, final HttpServletResponse response)throws ServletException, IOException 
	{	
		try{
			processResult(request, response);
		}catch(BaseException e){
			LogManager.debug(e);
		}
	}
	
	public void doPost(final HttpServletRequest request, final HttpServletResponse response)throws ServletException, IOException 
	{
		try{
			processResult(request, response);
		}catch(BaseException e){
			LogManager.debug(e);
		}
	}
	
	public void processResult(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException, BaseException 
	{
		try{
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");
		}catch(Exception e){
			LogManager.debug(e);
		}
		final HttpSession session = request.getSession(true);
		final MotorConfigBean bean=new MotorConfigBean();
		RequestDispatcher dispatcher;
		
		final String adminPid=session.getAttribute("AdminPid")==null?"":(String)session.getAttribute("AdminPid");
		final String branchCode= session.getAttribute("LoginBranchCode")==null?"":(String)session.getAttribute("LoginBranchCode");
		final String[][] pids=bean.getProductIds(adminPid, branchCode);
		final ValidationFormat validate=new ValidationFormat();
		final StringBuffer errorMessage=new StringBuffer("");
		final boolean flag;
		final String mode;
		final String areaCoverId;
		
		
		request.setAttribute("pids", pids);
		
		if(session.getAttribute("ses")==null)
		{
		response.sendRedirect("../login/error_messg.jsp");
		
		}
		
		final String requestFrom = request.getParameter("requestFrom")==null?"":request.getParameter("requestFrom");
	if("displayeconfig".equalsIgnoreCase(requestFrom))
	{
		LogManager.push("enter to make config");
		//final String branchCode= (String)session.getAttribute("LoginBranchCode");
		String productId=(String)session.getAttribute(PRODUCTID);
		if(productId==null)
		{
			productId="65";
		}
		final String[][] result=bean.getConfig(branchCode);
		
		LogManager.push(RESULTSIZE+result.length);
		ArrayList configList=null;
		try
		{
		 configList= bean.getConfigAreaList(result);
		}  
		catch(Exception e)
		{
			LogManager.debug(e);
		}
		LogManager.push("++++++++Here Is configList Size++++++++"+configList.size()); 
	    request.setAttribute("motorConfigList",configList);
		  dispatcher = request.getRequestDispatcher("../admin/MotorAreaCoverage.jsp");
		dispatcher.forward(request,response);
				
	}
	
	else if("editMotorConfig".equalsIgnoreCase(requestFrom))
	{
		LogManager.push(FORMPROCESS);
		
		
		  areaCoverId=request.getParameter(AREACOVERID);
		LogManager.push("areaCoverId value"+areaCoverId);
	
		
		final String[][] result=bean.getAreaCongigById(branchCode,areaCoverId);
		
		if(result.length>0)
		{
		bean.setAreaCoverId(result[0][0]);
		bean.setAreaCoverCode(result[0][1]);
		bean.setAreaEnglish(result[0][2]);
		bean.setAreaArabic(result[0][3]);
		bean.setEffYear(result[0][4]);
		LogManager.push("Hear is Year++++="+bean.getEffYear());
		bean.setEffMonth(result[0][5]);
		bean.setEffDay(result[0][6]);
		bean.setStatus(result[0][7]);
		bean.setRemarks(result[0][8]);
		}
    	
    	request.setAttribute(MOTORCONFIGBEAN,bean);
		LogManager.push("bean AreaCoverId====>"+bean.getAreaCoverId());
		LogManager.push("In OCntroler date is====>"+bean.getEffDay());
		LogManager.push("In OCntroler mon is====>"+bean.getEffMonth());
		LogManager.push("In OCntroler year is====>"+bean.getEffYear());
		  dispatcher=request.getRequestDispatcher("../admin/MoterAreaEdit.jsp");
		
		dispatcher.forward(request,response);
		LogManager.push("------------exit from editAreaconfig--------");
	}
	
	
	else if("uplodeMotorConfig".equalsIgnoreCase(requestFrom))
	{

		  mode=request.getParameter(MODE)==null?"":request.getParameter(MODE);
		LogManager.push("Mode In areaCover Controller: "+mode);
		 
		
		  areaCoverId=request.getParameter(AREACOVERID)==null?"":request.getParameter(AREACOVERID);
		LogManager.push("In COntroler Request CID IS====>>"+areaCoverId);
	
		
		bean.setAreaCoverId(request.getParameter(AREACOVERID)==null?"":request.getParameter(AREACOVERID));
		bean.setAreaEnglish(request.getParameter("areaEnglish")==null?"":request.getParameter("areaEnglish"));
		bean.setAreaArabic(request.getParameter("areaArabic")==null?"":request.getParameter("areaArabic"));
		bean.setAreaCoverCode(request.getParameter("areaCoverCode")==null?"":request.getParameter("areaCoverCode"));
		bean.setEffDay(request.getParameter(EFFECTIVEDATE)==null?"":request.getParameter(EFFECTIVEDATE));
		bean.setEffMonth(request.getParameter(EFFECTIVEMONTH)==null?"":request.getParameter(EFFECTIVEMONTH));
		bean.setEffYear(request.getParameter(EFFECTIVEYEAR)==null?"":request.getParameter(EFFECTIVEYEAR));
		bean.setStatus(request.getParameter(STATUS)==null?"":request.getParameter(STATUS));
		bean.setRemarks(request.getParameter(REMARKS)==null?"":request.getParameter(REMARKS));
		
		
		final String areaCoverId1=bean.getAreaCoverId();
		LogManager.push("In COntroler  Bean CID IS====>>"+areaCoverId1);
		
		
		if(bean.getAreaEnglish().equalsIgnoreCase(""))
		{
			errorMessage.append("Enter the Area Name(English) <br>");
		}	
		
		if(bean.getAreaCoverCode().equalsIgnoreCase(""))
		{
			errorMessage.append("Enter the Area Code <br>");
		}
		else
		{
			
			flag=validate.IsLetterOrDigitWithSpaceValidationFormat(bean.getAreaCoverCode());
			if(!flag)
			{
				errorMessage.append(VALIDCODE);
			}
		}
		if(bean.getStatus().equalsIgnoreCase(""))
		{
			errorMessage.append(VALIDSTATUS);
		}
		LogManager.push("-getEffDay----FDG------->"+bean.getEffDay());
		LogManager.push("-getEffMonth--DF--------->"+bean.getEffMonth());
		LogManager.push("-getEffYear---FG-------->"+bean.getEffYear());
		
		
		if((bean.getEffDay()+"-"+bean.getEffMonth()+"-"+bean.getEffYear())!=null ||(bean.getEffDay()+"-"+bean.getEffMonth()+"-"+bean.getEffYear())!="" )
		{
			final String message = bean.isValidDate(bean.getEffDay()+"-"+bean.getEffMonth()+"-"+bean.getEffYear());
			if(message!=null && message.trim().length()<=0)
				{
			
			final String areaCoverId2=bean.getAreaCoverId()==null?"":bean.getAreaCoverId();
			final String result=bean.isVAlidAreaDate((bean.getEffDay()+"-"+bean.getEffMonth()+"-"+bean.getEffYear()),areaCoverId2,branchCode);
			if(result.equalsIgnoreCase("no"))
			{
				errorMessage.append(DATEVALIDATION);
			}


				}
			else if(message!=null)
				{
				errorMessage.append(message);
				}
		}
		if(errorMessage.length()>0)
		 {
			LogManager.push("hi Ram I am In Area If Part");
			request.setAttribute(ERROR, errorMessage);	 
			dispatcher = request.getRequestDispatcher("../admin/MoterAreaEdit.jsp?mode="+mode);
		 }
		 else
		 { 
			 String msg="";
			 if(session.getAttribute("doProcess")!=null){
				 msg= bean.insertAreaConfig(branchCode, areaCoverId,bean,mode);
					session.removeAttribute("doProcess");
				}
			 
			
			 LogManager.push(BRANCHCODE+branchCode+"AreaID::"+ areaCoverId+"Mode Is=>"+mode);
			
			 LogManager.push("*****After Area Edit Insertion*********"+msg);
			 dispatcher = request.getRequestDispatcher("/servlet/MotorConfigController?requestFrom=displayeconfig");
	      }
		 
		 dispatcher.forward(request, response);
		

		 
	}
	
	
	else if("displaymotortype".equalsIgnoreCase(requestFrom))
	{

		LogManager.push("enter to Motor Type");
	
		
		final String[][] result=bean.getMotorPolicy(branchCode);
		ArrayList motorPolicyTypeList=null;
		try
		{
		 
		 motorPolicyTypeList= bean.getMotorPolicyTypeList(result);
		 
		}  
		catch(Exception e)
		{
			LogManager.debug(e);
		}
		LogManager.push("++++++++Here Is motorPolicyTypeList Size++++++++"+motorPolicyTypeList.size()); 
	    request.setAttribute("motorPolicyTypeList",motorPolicyTypeList);
		  dispatcher = request.getRequestDispatcher("../admin/MotorPolicyType.jsp");
		dispatcher.forward(request,response);
		 					
		
	}
	
	else if("editMotorPolicyType".equalsIgnoreCase(requestFrom))
	{
		LogManager.push(FORMPROCESS);
		mode=request.getParameter(MODE);
		
		final String policyTypeId=request.getParameter(POLICYTYPEID);
		LogManager.push("policyTypeId VALUE==>"+policyTypeId);
	
		
		final String[][] result=bean.getMotorPolicyById(branchCode,policyTypeId);
		
		if(result.length>0)
		{
		bean.setPolicyTypeId(result[0][0]==null?"":result[0][0]);
		bean.setCoreAppCode(result[0][1]==null?"":result[0][1]);
		bean.setPolyTypeEng(result[0][2]==null?"":result[0][2]);
		bean.setPolyTypeArabic(result[0][3]==null?"":result[0][3]);
		bean.setStatus(result[0][4]==null?"":result[0][4]);
		bean.setEffYear(result[0][5]==null?"":result[0][5]);
		bean.setEffMonth(result[0][6]==null?"":result[0][6]);
		bean.setEffDay(result[0][7]==null?"":result[0][7]);
		bean.setRemarks(result[0][8]==null?"":result[0][8]);
		bean.setPolicyTerm(result[0][9]==null?"":result[0][9]);
		bean.setPolicytypeName(result[0][10]==null?"":result[0][10]);
		}
    	request.setAttribute(MOTORCONFIGBEAN,bean);
    	LogManager.push("AND REMARKS IS==============================================>>>>>>>>>>>>>>>"+bean.getRemarks());
		LogManager.push("bean PolicyTypeId====>"+bean.getPolicyTypeId());
		dispatcher=request.getRequestDispatcher("../admin/MotorPolicyEdit.jsp");
		dispatcher.forward(request,response);
		LogManager.push("------------exit from editPolicyType--------");
	}
	
	else if("uplodePolicyType".equalsIgnoreCase(requestFrom))
	{

		mode=request.getParameter(MODE)==null?"":request.getParameter(MODE);
		LogManager.push("Mode In Policy Controller: "+mode);
		  dispatcher=null;
		
		final String policyTypeId=request.getParameter(POLICYTYPEID)==null?"":request.getParameter(POLICYTYPEID);
		LogManager.push("In COntroler Request Policy Id IS====>>"+policyTypeId);
	
		bean.setPolicytypeName(request.getParameter("policyTypeName")==null?"":request.getParameter("policyTypeName"));
		bean.setPolicyTypeId(request.getParameter(POLICYTYPEID)==null?"":request.getParameter(POLICYTYPEID));
		bean.setPolyTypeEng(request.getParameter("polyTypeEng")==null?"":request.getParameter("polyTypeEng"));
		bean.setPolyTypeArabic(request.getParameter("polyTypeArabic")==null?"":request.getParameter("polyTypeArabic"));
		bean.setCoreAppCode(request.getParameter(CORECODE)==null?"":request.getParameter(CORECODE));
		bean.setEffDay(request.getParameter(EFFECTIVEDATE)==null?"":request.getParameter(EFFECTIVEDATE));
		bean.setEffMonth(request.getParameter(EFFECTIVEMONTH)==null?"":request.getParameter(EFFECTIVEMONTH));
		bean.setEffYear(request.getParameter(EFFECTIVEYEAR)==null?"":request.getParameter(EFFECTIVEYEAR));
		bean.setStatus(request.getParameter(STATUS)==null?"":request.getParameter(STATUS));
		bean.setRemarks(request.getParameter(REMARKS)==null?"":request.getParameter(REMARKS));
		bean.setPolicyTerm(request.getParameter("policyTerm")==null?"":request.getParameter("policyTerm"));
		
		final String policyTypeId1=bean.getPolicyTypeId();
		LogManager.push("In COntroler  Bean CID IS====>>"+policyTypeId1);
		
		if(bean.getPolicytypeName().equalsIgnoreCase("")){
			errorMessage.append("Enter the Policy Type Name <br>");
		}	
		if(bean.getPolyTypeEng().equalsIgnoreCase("")){
			errorMessage.append("Enter the Policy Type Desc(English) <br>");
		}	
		
		if(bean.getCoreAppCode().equalsIgnoreCase("")){
			errorMessage.append("Enter the Core App Code <br>");
		}else{
			flag=validate.IsLetterOrDigitWithSpaceValidationFormat(bean.getCoreAppCode());
			if(!flag){
				errorMessage.append(VALIDCODE);
			}
		}
		
		if(bean.getStatus().equalsIgnoreCase("")){
			errorMessage.append(VALIDSTATUS);
		}
		LogManager.push("-getEffDay----GFH------->"+bean.getEffDay());
		LogManager.push("-getEffMonth--GH--------->"+bean.getEffMonth());
		LogManager.push("-getEffYear---GHD-------->"+bean.getEffYear());
		
		if((bean.getEffDay()+"-"+bean.getEffMonth()+"-"+bean.getEffYear())!=null ||(bean.getEffDay()+"-"+bean.getEffMonth()+"-"+bean.getEffYear())!="" )
		{
			final String message = bean.isValidDate(bean.getEffDay()+"-"+bean.getEffMonth()+"-"+bean.getEffYear());
			if(message!=null && message.trim().length()<=0){
			
			final String policyId=bean.getPolicyTypeId()==null?"":bean.getPolicyTypeId();
			final String result=bean.isVAlidEffDate((bean.getEffDay()+"-"+bean.getEffMonth()+"-"+bean.getEffYear()),policyId,branchCode);
			if(result.equalsIgnoreCase("no"))
			{
				errorMessage.append(DATEVALIDATION);
			}


				}
			else if(message!=null)
				{
				errorMessage.append(message);
				}
		}
		
		LogManager.push("&&&&&&&&&&&&&&&&&&&&Here Is Date&&&&&&&&&&&&&&&&"+errorMessage);
		 
		if(errorMessage.length()>0)
		 {
			LogManager.push("hi Ram I am In policy If Part");
			request.setAttribute(ERROR, errorMessage);	 
			dispatcher = request.getRequestDispatcher("../admin/MotorPolicyEdit.jsp?mode="+mode);
		 }
		 else
		 { 
			 String msg="";
			 
			 if(session.getAttribute("doProcess")!=null){
				 msg= bean.insertMotorPolicy(branchCode, policyTypeId,bean,mode);
					session.removeAttribute("doProcess");
				}
			 
			 LogManager.push(BRANCHCODE+branchCode+"AreaID::"+ policyTypeId+"Mode Is===>"+mode);
			 LogManager.push("hi Ram I am In Edit Part ANd going to execute policyType Edit Query");
			
			 LogManager.push("*****After Policy Insertion*********"+msg);
			 dispatcher = request.getRequestDispatcher("/servlet/MotorConfigController?requestFrom=displaymotortype");
	      }
		 
		 dispatcher.forward(request, response);
		  
	
	}
	
	
	
	else if("displayopcover".equalsIgnoreCase(requestFrom))
	{

		LogManager.push("enter to OPCover");
		
		
		final String[][] result=bean.getOPCover(branchCode);
		LogManager.push(RESULTSIZE+result.length);
		ArrayList motorOPCoverList=null;
		try
		{
		 
		 motorOPCoverList= bean.getMotorOPCoverList(result);
		
		 
		}  
		catch(Exception e)
		{
			LogManager.debug(e);
		}
		LogManager.push("++++++++Here Is motorOPCoverList Size++++++++"+motorOPCoverList.size()); 
	    request.setAttribute("motorOPCoverList",motorOPCoverList);
		  dispatcher = request.getRequestDispatcher("../admin/MotorOPCover.jsp");
		dispatcher.forward(request,response);
		 					
		
	}
	
	
	else if("editMotorOPCover".equalsIgnoreCase(requestFrom))
	{
		LogManager.push(FORMPROCESS);
		
		
		final String opCoverId=request.getParameter(OPCOVERID);
		LogManager.push("opCoverId VALUE==>"+opCoverId);
		 
		final String[][] result=bean.getMotorOPCoverById(branchCode,opCoverId);
		
		if(result.length>0)
		{
		bean.setOpCoverId(result[0][0]);
		bean.setCoreAppCode(result[0][1]);
		bean.setOpCoverEnglish(result[0][2]);
		bean.setOpCoverArabic(result[0][3]);
		bean.setStatus(result[0][4]);
		bean.setEffYear(result[0][5]);
		bean.setEffMonth(result[0][6]);
		bean.setEffDay(result[0][7]);
		bean.setRemarks(result[0][8]);
		bean.setProductId(pids[0][0]);
		}
    	request.setAttribute(MOTORCONFIGBEAN,bean);
		LogManager.push("bean OPCoverID====>"+bean.getOpCoverId());
		  dispatcher=request.getRequestDispatcher("../admin/MotorOPCoverEdit.jsp");
		 // dispatcher=request.getRequestDispatcher("../admin/MotorPolicyEdit.jsp");
		 
		dispatcher.forward(request,response);
		LogManager.push("------------exit from editOPCover--------");
	}
	
	
	else if("uplodeOPCover".equalsIgnoreCase(requestFrom))
	{

		mode=request.getParameter(MODE)==null?"":request.getParameter(MODE);
		LogManager.push("Mode In OPCover Controller: "+mode);
		  dispatcher=null;
		
		final String opCoverId=request.getParameter(OPCOVERID)==null?"":request.getParameter(OPCOVERID);
		LogManager.push("In COntroler Request Cover ID  IS====>>"+opCoverId);
	 
		
		bean.setOpCoverId(request.getParameter(OPCOVERID)==null?"":request.getParameter(OPCOVERID));
		bean.setOpCoverEnglish(request.getParameter("opCoverEnglish")==null?"":request.getParameter("opCoverEnglish"));
		bean.setOpCoverArabic(request.getParameter("opCoverArabic")==null?"":request.getParameter("opCoverArabic"));
		bean.setCoreAppCode(request.getParameter(CORECODE)==null?"":request.getParameter(CORECODE));
		bean.setEffDay(request.getParameter(EFFECTIVEDATE)==null?"":request.getParameter(EFFECTIVEDATE));
		bean.setEffMonth(request.getParameter(EFFECTIVEMONTH)==null?"":request.getParameter(EFFECTIVEMONTH));
		bean.setEffYear(request.getParameter(EFFECTIVEYEAR)==null?"":request.getParameter(EFFECTIVEYEAR));
		bean.setStatus(request.getParameter(STATUS)==null?"":request.getParameter(STATUS));
		bean.setRemarks(request.getParameter(REMARKS)==null?"":request.getParameter(REMARKS));
		
		
		final String opCoverId1=bean.getOpCoverId();
		LogManager.push("In COntroler  Bean CID IS====>>"+opCoverId1);
		
		
		if(bean.getOpCoverEnglish().equalsIgnoreCase(""))
		{
			errorMessage.append("Enter the OPCover Name(English) <br>");
		}	
		
		if(bean.getCoreAppCode().equalsIgnoreCase(""))
		{
			errorMessage.append("Enter the Core App Code <br>");
		}
		else
		{
			 
			flag=validate.IsLetterOrDigitWithSpaceValidationFormat(bean.getCoreAppCode());
			if(!flag)
			{
				errorMessage.append(VALIDCODE);
			}
		}
		if(bean.getStatus().equalsIgnoreCase(""))
		{
			errorMessage.append(VALIDSTATUS);
		}
		LogManager.push("-getEffDay----HG------->"+bean.getEffDay());
		LogManager.push("-getEffMonth---FBGF-------->"+bean.getEffMonth());
		LogManager.push("-getEffYear----HFGH------->"+bean.getEffYear());
		
		
		if((bean.getEffDay()+"-"+bean.getEffMonth()+"-"+bean.getEffYear())!=null ||(bean.getEffDay()+"-"+bean.getEffMonth()+"-"+bean.getEffYear())!="" )
		{
			final String message = bean.isValidDate(bean.getEffDay()+"-"+bean.getEffMonth()+"-"+bean.getEffYear());
			if(message!=null && message.trim().length()<=0)
				{
			
			final String opCoverId2=bean.getOpCoverId()==null?"":bean.getOpCoverId();
			final String result=bean.isVAlidOPDate((bean.getEffDay()+"-"+bean.getEffMonth()+"-"+bean.getEffYear()),opCoverId2,branchCode);
			if(result.equalsIgnoreCase("no"))
			{
				errorMessage.append(DATEVALIDATION);
			}


				}
			else if(message!=null)
				{
				errorMessage.append(message);
				}
		}
		
		LogManager.push("&&&&&&&&&&&&&&&&&&&&Here Is Date&&&&&&&&&&&&&&&&"+errorMessage);
		 
		if(errorMessage.length()>0)
		 {
			LogManager.push("hi Ram I am In OPCover If Part");
			request.setAttribute(ERROR, errorMessage);	 
			dispatcher = request.getRequestDispatcher("../admin/MotorOPCoverEdit.jsp?mode="+mode);
		 }
		 else
		 { 
			 String msg="";
			 if(session.getAttribute("doProcess")!=null){
				 msg= bean.insertOPCover(branchCode, opCoverId,bean,mode);
					session.removeAttribute("doProcess");
				}
			 LogManager.push(BRANCHCODE+branchCode+"OPCover ID::"+ opCoverId+"Mode >"+mode);
			 LogManager.push("hi Ram I am In Edit Part ANd going to execute opCover Edit Query");
			
			 LogManager.push("*****After Opcover Insertion*********"+msg);
			 dispatcher = request.getRequestDispatcher("/servlet/MotorConfigController?requestFrom=displayopcover");
	      }
		 
		 dispatcher.forward(request, response);
		  
	
	}
	
	
	else if("displaybank".equalsIgnoreCase(requestFrom))
	{

		LogManager.push("enter to Bank");
	 
		
		final String[][] result=bean.getBank(branchCode);
		LogManager.push(RESULTSIZE+result.length);
		ArrayList motorBankList=null;
		try
		{
		 
		 motorBankList= bean.getMotorBankList(result);
		 
		}  
		catch(Exception e)
		{
			LogManager.debug(e);
		}
		LogManager.push("++++++++Here Is motorBankList Size++++++++"+motorBankList.size()); 
	    request.setAttribute("motorBankList",motorBankList);
		  dispatcher = request.getRequestDispatcher("../admin/MotorBank.jsp");
		dispatcher.forward(request,response);
		 					
		
	}
	
	
	else if("editMotorBank".equalsIgnoreCase(requestFrom))
	{
		
		LogManager.push(FORMPROCESS);
		mode=request.getParameter(MODE);
		
		final String bankId=request.getParameter(BANKID);
		LogManager.push("ID VALUE==>"+bankId);
	 
		
		final String[][] result=bean.getMotorBankById(branchCode,bankId);
		
		if(result.length>0)
		{
		bean.setBankId(result[0][0]);
		bean.setBankCOde(result[0][1]);
		bean.setBankNameEng(result[0][2]);
		bean.setBankNameArabic(result[0][3]);
		bean.setStatus(result[0][4]);
		bean.setEffYear(result[0][5]);
		bean.setEffMonth(result[0][6]);
		bean.setEffDay(result[0][7]);
		bean.setRemarks(result[0][8]);
		}
    	request.setAttribute(MOTORCONFIGBEAN,bean);
		LogManager.push("bean bankId====>"+bean.getBankId());
		  dispatcher=request.getRequestDispatcher("../admin/MotorBankEdit.jsp");
		 
		dispatcher.forward(request,response);
		LogManager.push("------------exit from editMotorBank-------");
	}
	
	
	else if("uplodeMotorBank".equalsIgnoreCase(requestFrom))
	{
		LogManager.push("Hiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii");

		mode=request.getParameter(MODE)==null?"":request.getParameter(MODE);
		LogManager.push("Mode In Bank Controller: "+mode);
		  dispatcher=null;
		
		  final String bankId=request.getParameter(BANKID)==null?"":request.getParameter(BANKID);
		LogManager.push("In COntroler Request Bank Id IS====>>"+bankId);
	 
		
		bean.setBankId(request.getParameter(BANKID)==null?"":request.getParameter(BANKID));
		bean.setBankNameEng(request.getParameter(BANKENG)==null?"":request.getParameter(BANKENG));
		bean.setBankNameArabic(request.getParameter(BANKARABIC)==null?"":request.getParameter(BANKARABIC));
		bean.setBankCOde(request.getParameter(BANKCODE)==null?"":request.getParameter(BANKCODE));
		bean.setEffDay(request.getParameter(EFFECTIVEDATE)==null?"":request.getParameter(EFFECTIVEDATE));
		bean.setEffMonth(request.getParameter(EFFECTIVEMONTH)==null?"":request.getParameter(EFFECTIVEMONTH));
		bean.setEffYear(request.getParameter(EFFECTIVEYEAR)==null?"":request.getParameter(EFFECTIVEYEAR));
		bean.setStatus(request.getParameter(STATUS)==null?"":request.getParameter(STATUS));
		bean.setRemarks(request.getParameter(REMARKS)==null?"":request.getParameter(REMARKS));
		 
		
		final String bankId1=bean.getBankId();
		LogManager.push("In COntroler  Bean Bank Id  IS====>>"+bankId1);
		LogManager.push("In COntroler  Bean Bank Cod4e IS====>>"+bean.getBankCOde());
		LogManager.push("In COntroler  Bean Bank Status  IS====>>"+bean.getStatus());
		LogManager.push("In COntroler  Bean Bank Remarks  IS====>>"+bean.getRemarks());
		
		if(bean.getBankNameEng().equalsIgnoreCase(""))
		{
			errorMessage.append("Enter the Bank Name(English) <br>");
		}	
		
		if(bean.getBankCOde().equalsIgnoreCase(""))
		{
			errorMessage.append("Enter the Bank Code <br>");
		}
		else
		{
			 
			flag=validate.IsLetterOrDigitWithSpaceValidationFormat(bean.getBankCOde());
			if(!flag)
			{
				errorMessage.append(VALIDCODE);
			}
		}
		if(bean.getStatus().equalsIgnoreCase(""))
		{
			errorMessage.append(VALIDSTATUS);
		}
		LogManager.push("-------day----->"+bean.getEffDay());
		LogManager.push("------month------>"+bean.getEffMonth());
		LogManager.push(""+bean.getEffYear());
		
		
		if((bean.getEffDay()+"-"+bean.getEffMonth()+"-"+bean.getEffYear())!=null ||(bean.getEffDay()+"-"+bean.getEffMonth()+"-"+bean.getEffYear())!="" )
		{
			final String message = bean.isValidDate(bean.getEffDay()+"-"+bean.getEffMonth()+"-"+bean.getEffYear());
			if(message!=null && message.trim().length()<=0)
				{
			
			final String bankId2=bean.getBankId()==null?"":bean.getBankId();
			final String result=bean.isVAlidBankDate((bean.getEffDay()+"-"+bean.getEffMonth()+"-"+bean.getEffYear()),bankId2,branchCode);
			if(result.equalsIgnoreCase("no"))
			{
				errorMessage.append(DATEVALIDATION);
			}


				}
			else if(message!=null)
				{
				errorMessage.append(message);
				}
		}
		//LogManager.push("&&&&&&&&&&&&&&&&&&&&Here Is Date&&&&&&&&&&&&&&&&"+errorMessage);
		 
		if(errorMessage.length()>0)
		 {
			LogManager.push("hi Ram I am In Bank If Part");
			request.setAttribute(ERROR, errorMessage);	 
			dispatcher = request.getRequestDispatcher("../admin/MotorBankEdit.jsp?mode="+mode);
		 }
		 else
		 { 
			 System.out.println("Hiiii Again1");
			 String msg="";
			 if(session.getAttribute("doProcess")!=null){
				 System.out.println("Hiiii Again2");
				 msg= bean.insertMotorBank(branchCode, bankId,bean,mode);
					session.removeAttribute("doProcess");
				}
			 System.out.println("Hiiii Again3");
			 LogManager.push(BRANCHCODE+branchCode+"Bank ID::"+ bankId+"Mode Is==>>"+mode);
			 LogManager.push("hi Ram I am In Edit Part ANd going to execute ank Edit Query");
			
			 LogManager.push("*****After insertMotorBank Insertion*********"+msg);
			 dispatcher = request.getRequestDispatcher("/servlet/MotorConfigController?requestFrom=displaybank");
	      }
		 
		 dispatcher.forward(request, response);
		  
	
	}
	
	
	else if("displaymotorcolor".equalsIgnoreCase(requestFrom))
	{

		LogManager.push("enter to Motor Color");
	 
		
		final String[][] result=bean.getMotorColor(branchCode);
		LogManager.push(RESULTSIZE+result.length);
		ArrayList motorColorList=null;
		try
		{
		 
		 motorColorList= bean.getMotorColorList(result);
		 
		}  
		catch(Exception e)
		{
			LogManager.debug(e);
		}
		LogManager.push("++++++++Here Is motorColorList Size++++++++"+motorColorList.size()); 
	    request.setAttribute("motorColorList",motorColorList);
		  dispatcher = request.getRequestDispatcher("../admin/MotorColor.jsp");
		dispatcher.forward(request,response);
		 					
		
	}
	
	
	else if("editMotorColorType".equalsIgnoreCase(requestFrom))
	{
		
		LogManager.push(FORMPROCESS);
		mode=request.getParameter(MODE);
		
		final String colorId=request.getParameter(COLORID);
		LogManager.push("ID VALUE==>"+colorId);
	 
		
		final String[][] result=bean.getMotorColorById(branchCode,colorId);
		
		if(result.length>0)
		{
		bean.setColorId(result[0][0]);
		bean.setColorCode(result[0][1]);
		bean.setColorNameEng(result[0][2]);
		bean.setColorNameArabic(result[0][3]);
		bean.setStatus(result[0][4]);
		bean.setEffYear(result[0][5]);
		bean.setEffMonth(result[0][6]);
		bean.setEffDay(result[0][7]);
		bean.setRemarks(result[0][8]);
		}
    	request.setAttribute(MOTORCONFIGBEAN,bean);
		LogManager.push("bean colorId====>"+bean.getColorId());
		  dispatcher=request.getRequestDispatcher("../admin/MotorColorEdit.jsp");
		 
		dispatcher.forward(request,response);
		LogManager.push("------------exit from editColorType-------");
	}
	
	else if("uplodeColorType".equalsIgnoreCase(requestFrom))
	{
		LogManager.push("Hiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii");

		mode=request.getParameter(MODE)==null?"":request.getParameter(MODE);
		LogManager.push("Mode In Color Controller: "+mode);
		  dispatcher=null;
		 String colorId=request.getParameter(COLORID)==null?"":request.getParameter(COLORID);
		LogManager.push("In COntroler Request Color Id IS====>>"+colorId);
		bean.setColorId(request.getParameter(COLORID)==null?"":request.getParameter(COLORID));
		bean.setColorNameEng(request.getParameter("colorNameEng")==null?"":request.getParameter("colorNameEng"));
		bean.setColorNameArabic(request.getParameter("colorNameArabic")==null?"":request.getParameter("colorNameArabic"));
		bean.setColorCode(request.getParameter("colorCode")==null?"":request.getParameter("colorCode"));
		bean.setEffDay(request.getParameter(EFFECTIVEDATE)==null?"":request.getParameter(EFFECTIVEDATE));
		bean.setEffMonth(request.getParameter(EFFECTIVEMONTH)==null?"":request.getParameter(EFFECTIVEMONTH));
		bean.setEffYear(request.getParameter(EFFECTIVEYEAR)==null?"":request.getParameter(EFFECTIVEYEAR));
		bean.setStatus(request.getParameter(STATUS)==null?"":request.getParameter(STATUS));
		bean.setRemarks(request.getParameter(REMARKS)==null?"":request.getParameter(REMARKS));
		 
		final String colorId1=bean.getColorId();
		LogManager.push("In COntroler  Bean colorId1 Id  IS====>>"+colorId1);
		LogManager.push("In COntroler  Bean colorId1 Cod4e IS====>>"+bean.getColorCode());
		LogManager.push("In COntroler  Bean colorId1 Status  IS====>>"+bean.getStatus());
		LogManager.push("In COntroler  Bean colorId1 Remarks  IS====>>"+bean.getRemarks());
		
		if(bean.getColorNameEng().equalsIgnoreCase(""))
		{
			errorMessage.append("Enter the Color Name(English) <br>");
		}	
		
		if(bean.getColorCode().equalsIgnoreCase(""))
		{
			
			errorMessage.append("Enter the Color Code <br>");
		}
		else  if(session.getAttribute("doProcess")!=null)
		{
			colorId=(colorId==null||colorId==""||colorId.trim().length()<=0)?"0":colorId;
			final int count=bean.getColorCount(branchCode,colorId,bean.getColorCode());
			if (count>0)
			{
				errorMessage.append("Entered Color Code Is Already Exist <br>");
			}
			 
			flag=validate.IsLetterOrDigitWithSpaceValidationFormat(bean.getColorCode());
			if(!flag)
			{
				errorMessage.append(VALIDCODE);
			}
			 
		}
		
		
		if(bean.getStatus().equalsIgnoreCase(""))
		{
			errorMessage.append(VALIDSTATUS);
		}
		
		if((bean.getEffDay()+"-"+bean.getEffMonth()+"-"+bean.getEffYear())!=null ||(bean.getEffDay()+"-"+bean.getEffMonth()+"-"+bean.getEffYear())!="" )
		{
			final String message = bean.isValidDate(bean.getEffDay()+"-"+bean.getEffMonth()+"-"+bean.getEffYear());
			if(message!=null && message.trim().length()<=0){
				final String colorId2=bean.getColorId()==null?"":bean.getColorId();
				final String result=bean.isVAlidColorDate((bean.getEffDay()+"-"+bean.getEffMonth()+"-"+bean.getEffYear()),colorId2,branchCode);
				if(result.equalsIgnoreCase("no")){
					errorMessage.append(DATEVALIDATION);
				}
			}else if(message!=null){
				errorMessage.append(message);
			}
		}
		
		if(errorMessage.length()>0)
		 {
			LogManager.push("hi Ram I am In color If Part");
			request.setAttribute(ERROR, errorMessage);	 
			dispatcher = request.getRequestDispatcher("../admin/MotorColorEdit.jsp?mode="+mode);
		 }
		 else
		 { 
			 String msg="";
			 System.out.println("Hiiii Again1");
			 if(session.getAttribute("doProcess")!=null){
				 System.out.println("Hiiii Again2");
				 msg= bean.insertMotorColor(branchCode, colorId,bean,mode);
				 session.removeAttribute("doProcess");
				}
			 System.out.println("Hiiii Again3");
			 LogManager.push(BRANCHCODE+branchCode+"colorId ID::"+ colorId+"Mode Is=--=>"+mode);
			 LogManager.push("hi Ram I am In Edit Part ANd going to execute color Edit Query");
			 
			 LogManager.push("*****After insertMotorColor*********"+msg);
			 dispatcher = request.getRequestDispatcher("/servlet/MotorConfigController?requestFrom=displaymotorcolor");
	      }
		 
		 dispatcher.forward(request, response);
		  
	
	}
	
	
	else if("displayfinancebank".equalsIgnoreCase(requestFrom))
	{

		LogManager.push("enter to FinanceBank");
		 
		
		final String[][] result=bean.getFinanceBank(branchCode);
		LogManager.push(RESULTSIZE+result.length);
		ArrayList motorFinanceBankList=null;
		try
		{
		 
		 motorFinanceBankList= bean.getMotorFinanceBankList(result);
		 
		}  
		catch(Exception e)
		{
			LogManager.debug(e);
		}
		LogManager.push("++++++++Here Is motorFinanceBankList Size++++++++"+motorFinanceBankList.size()); 
	    request.setAttribute("motorFinanceBankList",motorFinanceBankList);
		  dispatcher = request.getRequestDispatcher("../admin/MotorFinanceBank.jsp");
		dispatcher.forward(request,response);
		 					
		
	}
	
	
	else if("editMotorFinanceBank".equalsIgnoreCase(requestFrom))
	{
		
		LogManager.push(FORMPROCESS);
		mode=request.getParameter(MODE);
		
		final String bankId=request.getParameter(BANKID);
		LogManager.push("bankId VALUE==>"+bankId);
		final String[][] result=bean.getMotorFinanceBankById(branchCode,bankId);
		
		if(result.length>0)
		{
		bean.setBankId(result[0][0]);
		bean.setBankCOde(result[0][1]);
		bean.setBankNameEng(result[0][2]);
		bean.setBankNameArabic(result[0][3]);
		bean.setStatus(result[0][4]);
		bean.setEffYear(result[0][5]);
		bean.setEffMonth(result[0][6]);
		bean.setEffDay(result[0][7]);
		bean.setRemarks(result[0][8]);
		}
    	request.setAttribute(MOTORCONFIGBEAN,bean);
		LogManager.push("bean bankId====>"+bean.getBankId());
		  dispatcher=request.getRequestDispatcher("../admin/MotorFinanceBankEdit.jsp");
		 
		dispatcher.forward(request,response);
		LogManager.push("------------exit from editMotorFinanceBank--------");
	}
	
	
	else if("uplodeMotorFinanceBank".equalsIgnoreCase(requestFrom))
	{
		 

		mode=request.getParameter(MODE)==null?"":request.getParameter(MODE);
		LogManager.push("Mode In FinanceBank Controller: "+mode);
		  dispatcher=null;
		
		  final String bankId=request.getParameter(BANKID)==null?"":request.getParameter(BANKID);
		LogManager.push("In COntroler Request Bank ID  IS====>>"+bankId);
	 
		
		bean.setBankId(request.getParameter(BANKID)==null?"":request.getParameter(BANKID));
		bean.setBankNameEng(request.getParameter(BANKENG)==null?"":request.getParameter(BANKENG));
		bean.setBankNameArabic(request.getParameter(BANKARABIC)==null?"":request.getParameter(BANKARABIC));
		bean.setBankCOde(request.getParameter(BANKCODE)==null?"":request.getParameter(BANKCODE));
		bean.setEffDay(request.getParameter(EFFECTIVEDATE)==null?"":request.getParameter(EFFECTIVEDATE));
		bean.setEffMonth(request.getParameter(EFFECTIVEMONTH)==null?"":request.getParameter(EFFECTIVEMONTH));
		bean.setEffYear(request.getParameter(EFFECTIVEYEAR)==null?"":request.getParameter(EFFECTIVEYEAR));
		bean.setStatus(request.getParameter(STATUS)==null?"":request.getParameter(STATUS));
		bean.setRemarks(request.getParameter(REMARKS)==null?"":request.getParameter(REMARKS));
		 
		
		final String bankId1=bean.getBankId();
		LogManager.push("In COntroler  Bean Bank Id  IS====>>"+bankId1);
		LogManager.push("In COntroler  Bean Bank Cod4e IS====>>"+bean.getBankCOde());
		LogManager.push("In COntroler  Bean Bank Status  IS====>>"+bean.getStatus());
		LogManager.push("In COntroler  Bean Bank Remarks  IS====>>"+bean.getRemarks());
		
		if(bean.getBankNameEng().equalsIgnoreCase(""))
		{
			errorMessage.append("Enter the Bank Name(English) <br>");
		}	
		
		if(bean.getBankCOde().equalsIgnoreCase(""))
		{
			errorMessage.append("Enter the Bank Code <br>");
		}
		else
		{
			 
			flag=validate.IsLetterOrDigitWithSpaceValidationFormat(bean.getBankCOde());
			if(!flag)
			{
				errorMessage.append(VALIDCODE);
			}
		}
		
		if(bean.getStatus().equalsIgnoreCase(""))
		{
			errorMessage.append(VALIDSTATUS);
		}
		LogManager.push("-getEffDay-----HGFH------>"+bean.getEffDay());
		LogManager.push("-getEffMonth----BVC------->"+bean.getEffMonth());
		LogManager.push("-getEffYear-----BC------>"+bean.getEffYear());
		
		
		if((bean.getEffDay()+"-"+bean.getEffMonth()+"-"+bean.getEffYear())!=null ||(bean.getEffDay()+"-"+bean.getEffMonth()+"-"+bean.getEffYear())!="" )
		{
			final String message = bean.isValidDate(bean.getEffDay()+"-"+bean.getEffMonth()+"-"+bean.getEffYear());
			if(message!=null && message.trim().length()<=0)
				{
			
			final String bankId2=bean.getBankId()==null?"":bean.getBankId();
			final String result=bean.isVAlidFinanceDate((bean.getEffDay()+"-"+bean.getEffMonth()+"-"+bean.getEffYear()),bankId2,branchCode);
			if(result.equalsIgnoreCase("no")){
			
				errorMessage.append(DATEVALIDATION);
			 }


				}
			else if(message!=null)
				{
				errorMessage.append(message);
				}
		}
		
		
		 
		if(errorMessage.length()>0)
		 {
			LogManager.push("hi Ram I am In Bank If Part");
			request.setAttribute(ERROR, errorMessage);	 
			dispatcher = request.getRequestDispatcher("../admin/MotorFinanceBankEdit.jsp?mode="+mode);
		 }
		 else
		 { 
			 String msg="";
			 if(session.getAttribute("doProcess")!=null){
				 msg= bean.insertMotorFinanceBank(branchCode, bankId,bean,mode);
					session.removeAttribute("doProcess");
				}
			 LogManager.push(BRANCHCODE+branchCode+"Bank ID::"+ bankId+"Mode >"+mode);
			 LogManager.push("hi Ram I am In Edit Part ANd going to execute Bank Edit Query");
			 LogManager.push("Finance Bank Arabic ==============>>>>>>>??"+bean.getBankNameArabic());
			
			 LogManager.push("*****After insertMotorFinanceBank*********"+msg);
			 dispatcher = request.getRequestDispatcher("/servlet/MotorConfigController?requestFrom=displayfinancebank");
	      }
		 
		 dispatcher.forward(request, response);
		  
	
	}
	
	else if("displayMotorCylinder".equalsIgnoreCase(requestFrom))
	{
		LogManager.push("enter to displayMotorCylinder config");
		final String value=request.getParameter("val")==null?"":request.getParameter("val");
		LogManager.push("......Seeeeee Problem MAy here........");
		
		
		 
		if(value.equalsIgnoreCase("displayBodyList"))
		{
			final com.maan.admin.DAO.MotorBodyCreation bodybean = new com.maan.admin.DAO.MotorBodyCreation();
			LogManager.push("Inside displayBodyList--- displayMotorCylinder");
		 
			String productId=(String)session.getAttribute(PRODUCTID);
			if(productId==null)
			{
				productId="65";
			}
			final String[][] result=bodybean.getMotorBodyTypeId(branchCode,productId);
			request.setAttribute("bodyList",result);
			LogManager.push("t1");
			  dispatcher = request.getRequestDispatcher("../admin/MotorCylinder.jsp");
			LogManager.push("t1");
			dispatcher.forward(request,response);
			
		}
		else if(value.equalsIgnoreCase("displayCylinderList"))
		{
			LogManager.push(".............Hello Ram Now I am  In Model Config Part............");
			LogManager.push("Entry to display config");
		 
			LogManager.push("........Hai Ram Branch Code Is::"+branchCode);
			String typeOfBodyId=request.getParameter(TYPEOFBODYID);
			String cylTypeOfBodyName=bean.getCylinderTypeOfBody(branchCode, typeOfBodyId);
			request.setAttribute("cylTypeOfBodyName", cylTypeOfBodyName);
			
			String bodyName=request.getParameter("bodyId")==null?"":request.getParameter("bodyId");
			if(bodyName==null || bodyName=="")
			{
				bodyName=request.getParameter("bodyName");
				
			}
			if(typeOfBodyId==null || typeOfBodyId.equalsIgnoreCase(""))
			{
				typeOfBodyId=session.getAttribute(TYPEOFBODYID)==null?"":(String)session.getAttribute(TYPEOFBODYID);
			}
			LogManager.push(".......Hai Ram  Body Name Is ::::"+typeOfBodyId);
			request.setAttribute("typeOfBodyId",typeOfBodyId);
			final String [][]result=bean.getCylinderDisplay(branchCode, typeOfBodyId);
			
			ArrayList motorCylinderList=null;
			try{
			 LogManager.push("Model:1"); 
			 motorCylinderList=bean.getMotorCylinderList(result);
			 LogManager.push("Model:2");
			}  
			catch(Exception e)
			{
				LogManager.debug(e);
			}
			 
		    request.setAttribute("MotorCylinderList",motorCylinderList);
		    session.setAttribute(TYPEOFBODYID, typeOfBodyId);
		    LogManager.push("typeOfBodyId: "+typeOfBodyId);
		      dispatcher = request.getRequestDispatcher("../admin/MotorCylinder.jsp?display=cylinderList&body="+bodyName);
			dispatcher.forward(request,response);
			LogManager.push("-----------------Exit from display model config-----------");
			
		}
	}
	
	
	
	else if("editMotorCylinder".equalsIgnoreCase(requestFrom))
	{
		
		LogManager.push(FORMPROCESS);
		mode=request.getParameter(MODE);
		
		final String cylId=request.getParameter(CYLINDERID);
		LogManager.push("cylId VALUE==>"+cylId);
		String typeOfBodyId=request.getParameter(TYPEOFBODYID);
		String cylTypeOfBodyName=bean.getCylinderTypeOfBody(branchCode, typeOfBodyId);
		request.setAttribute("cylTypeOfBodyName", cylTypeOfBodyName);
	
		
		final String[][] result=bean.getMotorCylinderById(branchCode,cylId);
		
		if(result.length>0)
		{
		bean.setCylId(result[0][0]);
		bean.setCylCode(result[0][1]);
		bean.setCylNameEng(result[0][2]);
		bean.setCylNameArabic(result[0][3]);
		bean.setStatus(result[0][4]);
		bean.setEffYear(result[0][5]);
		bean.setEffMonth(result[0][6]);
		bean.setEffDay(result[0][7]);
		bean.setRemarks(result[0][8]);
		bean.setTypeOfBodyId(result[0][9]);
		}
    	request.setAttribute(MOTORCONFIGBEAN,bean);
		LogManager.push("bean Cylinder Id====>"+bean.getCylId());
		  dispatcher=request.getRequestDispatcher("../admin/MotorCylinderEdit.jsp");
		 
		dispatcher.forward(request,response);
		LogManager.push("------------exit from editCylinderType---");
	}
	
	
	else if("uplodeCylinderType".equalsIgnoreCase(requestFrom))
	{
		LogManager.push("Hiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii");

		mode=request.getParameter(MODE)==null?"":request.getParameter(MODE);
		LogManager.push("Mode In Cylinder Controller: "+mode);
		  dispatcher=null;
		
		  final String cylId=request.getParameter(CYLINDERID)==null?"":request.getParameter(CYLINDERID);
		LogManager.push("In COntroler Request Cylinder Id  IS====>>"+cylId);
	 
		
		bean.setCylId(request.getParameter(CYLINDERID)==null?"":request.getParameter(CYLINDERID));
		bean.setCylNameEng(request.getParameter("cylNameEng")==null?"":request.getParameter("cylNameEng"));
		bean.setCylNameArabic(request.getParameter("cylNameArabic")==null?"":request.getParameter("cylNameArabic"));
		bean.setCylCode(request.getParameter("cylCode")==null?"":request.getParameter("cylCode"));
		bean.setEffDay(request.getParameter(EFFECTIVEDATE)==null?"":request.getParameter(EFFECTIVEDATE));
		bean.setEffMonth(request.getParameter(EFFECTIVEMONTH)==null?"":request.getParameter(EFFECTIVEMONTH));
		bean.setEffYear(request.getParameter(EFFECTIVEYEAR)==null?"":request.getParameter(EFFECTIVEYEAR));
		bean.setStatus(request.getParameter(STATUS)==null?"":request.getParameter(STATUS));
		bean.setRemarks(request.getParameter(REMARKS)==null?"":request.getParameter(REMARKS));
		bean.setTypeOfBodyId(request.getParameter(TYPEOFBODYID)==null?(String)session.getAttribute(TYPEOFBODYID):request.getParameter(TYPEOFBODYID));
		final String typeOfBodyId=request.getParameter(TYPEOFBODYID);
		final String cylTypeOfBodyName=bean.getCylinderTypeOfBody(branchCode, typeOfBodyId);
		request.setAttribute("cylTypeOfBodyName", cylTypeOfBodyName);
	
		
		final String cylId1=bean.getCylId();
		LogManager.push("In COntroler  Bean Cylinder Id  IS====>>"+cylId1);
		LogManager.push("In COntroler  Bean Cylinder Cod4e IS====>>"+bean.getCylCode());
		LogManager.push("In COntroler  Bean Cylinder Status  IS====>>"+bean.getStatus());
		LogManager.push("In COntroler  Bean Cylinder Remarks  IS====>>"+bean.getRemarks());
		LogManager.push("In COntroler  Bean Cylinder body id  IS====>>"+bean.getTypeOfBodyId());
		if(bean.getCylNameEng().equalsIgnoreCase(""))
		{
			errorMessage.append("Enter the Cylinder Name(English) <br>");
		}	
		
		if(bean.getCylCode().equalsIgnoreCase(""))
		{
			errorMessage.append("Enter the Cylinder Code <br>");
		}
		else
		{
			 
			flag=validate.IsLetterOrDigitWithSpaceValidationFormat(bean.getCylCode());
			if(!flag)
			{
				errorMessage.append(VALIDCODE);
			}
		}
		
		if(bean.getStatus().equalsIgnoreCase(""))
		{
			errorMessage.append(VALIDSTATUS);
		}
		LogManager.push("---------day--->"+bean.getEffDay());
		LogManager.push("-------mon----->"+bean.getEffMonth());
		LogManager.push("-------year----->"+bean.getEffYear());
		
		
		
		if((bean.getEffDay()+"-"+bean.getEffMonth()+"-"+bean.getEffYear())!=null ||(bean.getEffDay()+"-"+bean.getEffMonth()+"-"+bean.getEffYear())!="" )
		{
			final String message = bean.isValidDate(bean.getEffDay()+"-"+bean.getEffMonth()+"-"+bean.getEffYear());
			if(message!=null && message.trim().length()<=0)
				{
			
			final String cylId2=bean.getCylId()==null?"":bean.getCylId();
			final String result=bean.isVAlidCylinderDate((bean.getEffDay()+"-"+bean.getEffMonth()+"-"+bean.getEffYear()),cylId2,branchCode);
			if(result.equalsIgnoreCase("no"))
			{
				errorMessage.append(DATEVALIDATION);
			}


				}
			else if(message!=null)
				{
				errorMessage.append(message);
				}
		}
		
		
		 
		if(errorMessage.length()>0)
		 {
			LogManager.push("hi Ram I am In cylinder If Part");
			request.setAttribute(ERROR, errorMessage);	 
			dispatcher = request.getRequestDispatcher("../admin/MotorCylinderEdit.jsp?mode="+mode);
		 }
		 else
		 { 
			 String msg="";
			 if(session.getAttribute("doProcess")!=null){
				 msg= bean.insertMotorCylinder(branchCode, cylId,bean,mode);
					session.removeAttribute("doProcess");
				}
			 LogManager.push(BRANCHCODE+branchCode+"cylId ID::"+ cylId+"Mode Is====>"+mode);
			 LogManager.push("hi Ram I am In Edit Part ANd going to execute Cylinder Edit Query");
			 
			 LogManager.push("*****After insertMotorCylinder*********"+msg);
			 dispatcher = request.getRequestDispatcher("/servlet/MotorConfigController?requestFrom=displayMotorCylinder&val=displayCylinderList");
	      }
		 
		 dispatcher.forward(request, response);
		  
	
	}
	
	else if("displayUplodeFile".equalsIgnoreCase(requestFrom))
	{

		LogManager.push("enter to Motor File");
	 
		
		final String[][] result=bean.getUplodeFIle(branchCode);
		LogManager.push(RESULTSIZE+result.length);
		ArrayList motorUplodeFileListt=null;
		try
		{
		 
		 motorUplodeFileListt= bean.getMotorUplodeFileList(result, pids);
		 
		}  
		catch(Exception e)
		{
			LogManager.debug(e);
		}
		LogManager.push("++++++++Here Is motorUplodeFileListt Size++++++++"+motorUplodeFileListt.size()); 
	    request.setAttribute("motorUplodeFileListt",motorUplodeFileListt);
		  dispatcher = request.getRequestDispatcher("../admin/MotorUplodeFile.jsp");
		dispatcher.forward(request,response);
		 					
		
	}
	
	else if("editMotorUplodeFile".equalsIgnoreCase(requestFrom))
	{
		
		LogManager.push(FORMPROCESS);
		mode=request.getParameter(MODE);
		
		final String fileId=request.getParameter(FILEID);
		LogManager.push("fileId VALUE==>"+fileId);
	 
		if ("Edit".equalsIgnoreCase(mode)){
			final String[][] result=bean.getMotorFileById(branchCode,fileId);
			
			if(result.length>0)
			{
			bean.setFileId(result[0][0]);
			bean.setFileType(result[0][1]);
			bean.setProductId(result[0][2]);
			bean.setStatus(result[0][3]);
			bean.setRemarks(result[0][4]);
			}
		}
    	request.setAttribute(MOTORCONFIGBEAN,bean);
		LogManager.push("bean fileId====>"+bean.getFileId());
		LogManager.push("bean Product Id====>"+bean.getProductId());
		LogManager.push("------------------------------------>>>>>"+request.getAttribute("pids"));
		  dispatcher=request.getRequestDispatcher("../admin/MotorUplodeFileEdit.jsp?mode="+mode);
		 
		dispatcher.forward(request,response);
		LogManager.push("------------exit from editMotorFile--------");
	}
	
	else if("uplodeMotorFile".equalsIgnoreCase(requestFrom))
	{
		 

		mode=request.getParameter(MODE)==null?"":request.getParameter(MODE);
		LogManager.push("Mode In File Controller: "+mode);
		  dispatcher=null;
		
		  final String fileId=request.getParameter(FILEID)==null?"":request.getParameter(FILEID);
		LogManager.push("In COntroler File Id IS====>>"+fileId);
	 
		
		bean.setFileId(request.getParameter(FILEID)==null?"":request.getParameter(FILEID));
		bean.setFileType(request.getParameter("fileType")==null?"":request.getParameter("fileType"));
		bean.setStatus(request.getParameter(STATUS)==null?"":request.getParameter(STATUS));
		bean.setRemarks(request.getParameter(REMARKS)==null?"":request.getParameter(REMARKS));
		bean.setProductId(request.getParameter(PRODUCTID)==null?"":request.getParameter(PRODUCTID));
		final String productId=(String)session.getAttribute("product_id");
		LogManager.push("productId: ======="+productId);
		 
		
		final String bankId1=bean.getBankId();
		LogManager.push("In COntroler  Bean File Id  IS====>>"+bankId1);
		LogManager.push("Product Id IS==========>>"+session.getAttribute("getProductId"));
		LogManager.push("In COntroler  Bean File Status  IS====>>"+bean.getStatus());
		LogManager.push("In COntroler  Bean File Remarks  IS====>>"+bean.getRemarks());
		
		
		if(bean.getFileType().equalsIgnoreCase(""))
		{
			errorMessage.append("Enter the File Type  <br>");
		}
		if(bean.getStatus().equalsIgnoreCase(""))
		{
			errorMessage.append(VALIDSTATUS);
		}
		
		
		if(errorMessage.length()>0)
		 {
			LogManager.push("hi Ram I am In file If Part");
			request.setAttribute(ERROR, errorMessage);	 
			dispatcher = request.getRequestDispatcher("../admin/MotorUplodeFileEdit.jsp?mode="+mode);
		 }
		 else
		 { 
			 String msg="";
			 if(session.getAttribute("doProcess")!=null){
				 msg= bean.insertMotorFile(branchCode, fileId,bean,mode);
					session.removeAttribute("doProcess");
				}
			 LogManager.push(BRANCHCODE+branchCode+"fileId ID::"+ fileId+"Mode"+mode);
			 LogManager.push("hi Ram I am In Edit Part ANd going to execute  file Edit Query");
			 
			 LogManager.push("*****insertMotorFile*********"+msg);
			 dispatcher = request.getRequestDispatcher("/servlet/MotorConfigController?requestFrom=displayUplodeFile");
	      }
		 
		 dispatcher.forward(request, response);
		  
	
	}
 
	
	else if("displayMotorGroup".equalsIgnoreCase(requestFrom))
	{

		LogManager.push("enter to Motor File");
	 
		
		final String[][] result=bean.getMotorGroup(branchCode);
		LogManager.push(RESULTSIZE+result.length);
		ArrayList motorGroupList=null;
		try
		{
		 
		 motorGroupList= bean.getMotorGroupList(result);
		 
		}  
		catch(Exception e)
		{
			LogManager.debug(e);
		}
		LogManager.push("++++++++Here Is motorGroupList Size++++++++"+motorGroupList.size()); 
	    request.setAttribute("motorGroupList",motorGroupList);
		  dispatcher = request.getRequestDispatcher("../admin/MotorGroup.jsp");
		dispatcher.forward(request,response);
		 					
		
	}
	
	
	else if("editMotorGroup".equalsIgnoreCase(requestFrom))
	{
		
		LogManager.push(FORMPROCESS);
		mode=request.getParameter(MODE);
		
		final String groupId=request.getParameter(GROUPID);
		LogManager.push("groupId VALUE==>"+groupId);
	 
		
		final String[][] result=bean.getMotorGroupById(branchCode,groupId);
		
		if(result.length>0)
		{
		bean.setGroupId(result[0][0]);
		bean.setGroupNameEng(result[0][1]);
		bean.setStatus(result[0][2]);
		bean.setEffYear(result[0][3]);
		bean.setEffMonth(result[0][4]);
		bean.setEffDay(result[0][5]);
		bean.setRemarks(result[0][6]);
		}
    	request.setAttribute(MOTORCONFIGBEAN,bean);
		LogManager.push("bean getGroupId====>"+bean.getGroupId());
		  dispatcher=request.getRequestDispatcher("../admin/MotorGroupEdit.jsp");
		 
		dispatcher.forward(request,response);
		LogManager.push("------------exit from editMotorGroup--------");
	}
	
	
	else if("uplodeMotorGroup".equalsIgnoreCase(requestFrom))
	{
		 

		mode=request.getParameter(MODE)==null?"":request.getParameter(MODE);
		LogManager.push("Mode In RequestGroup Controller: "+mode);
		  dispatcher=null;
		
		  final String groupId=request.getParameter(GROUPID)==null?"":request.getParameter(GROUPID);
		LogManager.push("In COntroler RequestGroup Id  IS====>>"+groupId);
	 
		bean.setGroupId(request.getParameter(GROUPID)==null?"":request.getParameter(GROUPID));
		bean.setGroupNameEng(request.getParameter("groupNameEng")==null?"":request.getParameter("groupNameEng"));
		bean.setEffDay(request.getParameter(EFFECTIVEDATE)==null?"":request.getParameter(EFFECTIVEDATE));
		bean.setEffMonth(request.getParameter(EFFECTIVEMONTH)==null?"":request.getParameter(EFFECTIVEMONTH));
		bean.setEffYear(request.getParameter(EFFECTIVEYEAR)==null?"":request.getParameter(EFFECTIVEYEAR));
		bean.setStatus(request.getParameter(STATUS)==null?"":request.getParameter(STATUS));
		bean.setRemarks(request.getParameter(REMARKS)==null?"":request.getParameter(REMARKS));
		 
		
		final String groupId1=bean.getGroupId();
		LogManager.push("In COntroler  Bean Group Id  IS====>>"+groupId1);
		
		LogManager.push("In COntroler  Bean Group Status  IS====>>"+bean.getStatus());
		LogManager.push("In COntroler  Bean Group Remarks  IS====>>"+bean.getRemarks());
		
		if(bean.getGroupNameEng().equalsIgnoreCase(""))
		{
			errorMessage.append("Enter the Group Name(English) <br>");
		}	
		
		
		if(bean.getStatus().equalsIgnoreCase(""))
		{
			errorMessage.append(VALIDSTATUS);
		}
		LogManager.push("-----dd------->"+bean.getEffDay());
		LogManager.push("-----mm------->"+bean.getEffMonth());
		LogManager.push("-----yy------->"+bean.getEffYear());
		
		
		
		if((bean.getEffDay()+"-"+bean.getEffMonth()+"-"+bean.getEffYear())!=null ||(bean.getEffDay()+"-"+bean.getEffMonth()+"-"+bean.getEffYear())!="" )
		{
			final String message = bean.isValidDate(bean.getEffDay()+"-"+bean.getEffMonth()+"-"+bean.getEffYear());
			if(message!=null && message.trim().length()<=0)
				{
			
			final String groupId2=bean.getGroupId()==null?"":bean.getGroupId();
			final String result=bean.isVAlidGroupDate((bean.getEffDay()+"-"+bean.getEffMonth()+"-"+bean.getEffYear()),groupId2,branchCode);
			if(result.equalsIgnoreCase("no"))
			{
				errorMessage.append(DATEVALIDATION);
			}


				}
			else if(message!=null)
				{
				errorMessage.append(message);
				}
		}
		
		
		 
		if(errorMessage.length()>0)
		 {
			LogManager.push("hi Ram I am In group If Part");
			request.setAttribute(ERROR, errorMessage);	 
			dispatcher = request.getRequestDispatcher("../admin/MotorGroupEdit.jsp?mode="+mode);
		 }
		 else
		 { 
			 String msg="";
			 if(session.getAttribute("doProcess")!=null){
				 msg= bean.insertMotorGroup(branchCode, groupId,bean,mode);
					session.removeAttribute("doProcess");
				}
			 LogManager.push(BRANCHCODE+branchCode+"groupId ID::"+ groupId+"Mode ghIs==>"+mode);
			 LogManager.push("hi Ram I am In Edit Part ANd going to execute group Edit Query");
			 
			 
			 LogManager.push("*****insertMotorGroup*********"+msg);
			 dispatcher = request.getRequestDispatcher("../servlet/MotorConfigController?requestFrom=displayMotorGroup");
	      }
		 
		 dispatcher.forward(request, response);
		  
	
	}
	
	
	
	else if("displayMotorVoluntary".equalsIgnoreCase(requestFrom))
	{
		LogManager.push("enter to displayMotorCylinder config");
		final String value=request.getParameter("val")==null?"":request.getParameter("val");
		LogManager.push("......Seeeeee Problem MAy here........");
		
		
		 
		if(value.equalsIgnoreCase("displayProductIdist"))
		{
			final com.maan.admin.DAO.MotorBodyCreation bodybean = new com.maan.admin.DAO.MotorBodyCreation();
			LogManager.push("Inside displayProductIdist--- displayProductIdist");
	
			String productId=(String)session.getAttribute(PRODUCTID);
			if(productId==null)
			{
				productId="65";
			}
			final String[][] result=bodybean.getMotorBodyTypeId(branchCode,productId);
			request.setAttribute("bodyList",result);
			LogManager.push("t1");
			  dispatcher = request.getRequestDispatcher("../admin/MotorCylinder.jsp");
			LogManager.push("t1");
			dispatcher.forward(request,response);
			
		}
		

		LogManager.push("enter to Motor File");
	
		
		final String[][] result=bean.getMotorVoluntary(branchCode);
		LogManager.push(RESULTSIZE+result.length);
		ArrayList motorVoluntaryList=null;
		try
		{
		 
		 motorVoluntaryList= bean.getmotorVoluntaryList(result,pids);
		 
		}  
		catch(Exception e)
		{
			LogManager.debug(e);
		}
		LogManager.push("++++++++Here Is motorVoluntaryList Size++++++++"+motorVoluntaryList.size()); 
	    request.setAttribute("motorVoluntaryList",motorVoluntaryList);
		  dispatcher = request.getRequestDispatcher("../admin/MotorVoluntary.jsp");
		dispatcher.forward(request,response);
	}
	
	else if("editVoluntary".equalsIgnoreCase(requestFrom))
	{
		
		LogManager.push(FORMPROCESS);
		mode=request.getParameter(MODE);
		
		final String voluntaryId=request.getParameter(VOLUNTARYID);
		LogManager.push("voluntaryId VALUE==>"+voluntaryId);
	 
		if ("Edit".equalsIgnoreCase(mode)){
			final String[][] result=bean.getVoluntaryById(branchCode,voluntaryId);
		
		if(result.length>0)
		{
		bean.setVoluntaryId(result[0][0]);
		bean.setVoluntaryNAme(result[0][1]);
		bean.setStatus(result[0][2]);
		bean.setRemarks(result[0][3]);
		bean.setPolicyTypeId(result[0][4]);
		bean.setVoluntaryValue(result[0][5]);
		bean.setCoreAppCode(result[0][6]);
		}
		}
    	request.setAttribute(MOTORCONFIGBEAN,bean);
		LogManager.push("bean getVoluntaryId====>"+bean.getVoluntaryId());
		  dispatcher=request.getRequestDispatcher("../admin/MotorVoluntaryEdit.jsp");
		 
		dispatcher.forward(request,response);
		LogManager.push("------------exit from editVoluntary--------");
	}
	
	
	else if("uplodeVoluntary".equalsIgnoreCase(requestFrom))
	{
		mode=request.getParameter(MODE)==null?"":request.getParameter(MODE);
		LogManager.push("Mode In voluntary Controller: "+mode);
		final String voluntaryId=request.getParameter(VOLUNTARYID)==null?"":request.getParameter(VOLUNTARYID);
		LogManager.push("In COntroler voluntary Id  IS====>>"+voluntaryId);
		bean.setVoluntaryId(request.getParameter(VOLUNTARYID)==null?"":request.getParameter(VOLUNTARYID));
		bean.setVoluntaryNAme(request.getParameter("voluntaryNAme")==null?"":request.getParameter("voluntaryNAme"));
		bean.setVoluntaryValue(request.getParameter("voluntaryValue")==null?"":request.getParameter("voluntaryValue"));
		bean.setCoreAppCode(request.getParameter(CORECODE)==null?"":request.getParameter(CORECODE));
		bean.setProductId(request.getParameter(PRODUCTID)==null?"":request.getParameter(PRODUCTID));
		bean.setStatus(request.getParameter(STATUS)==null?"":request.getParameter(STATUS));
		bean.setRemarks(request.getParameter(REMARKS)==null?"":request.getParameter(REMARKS));
		final String voluntaryId1=bean.getGroupId();
		LogManager.push("In COntroler  Bean Group Id  IS====>>"+voluntaryId1);
		LogManager.push("In COntroler  Bean   Status  IS====>>"+bean.getStatus());
		LogManager.push("In COntroler  Bean   Remarks  IS====>>"+bean.getRemarks());
		
		if(bean.getVoluntaryNAme().trim().equalsIgnoreCase(""))
		{
			errorMessage.append("Enter the Voluntary Name <br>");
		}	
		if(bean.getVoluntaryValue().trim().equalsIgnoreCase(""))
		{
			errorMessage.append("Enter the Voluntary Value <br>");
		}
		else
		{
			 
			flag=validate.isNumberValue(bean.getVoluntaryValue());
			if (!flag) 
			{
				errorMessage.append("Enter the valid Value <br>");
			}
		}
		
			
		if(bean.getStatus().equalsIgnoreCase(""))
		{
			errorMessage.append(VALIDSTATUS);
		}
		
		if(errorMessage.length()>0)
		 {
			LogManager.push("hi Ram I am In Voluntary If Part");
			request.setAttribute(ERROR, errorMessage);	 
			dispatcher = request.getRequestDispatcher("../admin/MotorVoluntaryEdit.jsp?mode="+mode);
		 }
		 else
		 { 
			 String msg="";
			 if(session.getAttribute("doProcess")!=null){
				 msg= bean.insertMotorVoluntary(branchCode, voluntaryId,bean,mode);
					session.removeAttribute("doProcess");
				}
			 LogManager.push(BRANCHCODE+branchCode+"voluntaryId ID::"+ voluntaryId+"Mode Ihjs==>"+mode);
			 LogManager.push("hi Ram I am In Edit Part ANd going to execute voluntary Edit Query");
			 
			 
			 LogManager.push("*****insertMotorVoluntary*********"+msg);
			 dispatcher = request.getRequestDispatcher("../servlet/MotorConfigController?requestFrom=displayMotorVoluntary");
	      }
		 
		 dispatcher.forward(request, response);
		  
	
	}
	
	else if("displayvehicleusage".equalsIgnoreCase(requestFrom))
	{
		
        LogManager.push("enter to Vehicle Usage  File");
		final String[][] result=bean.getVehicleFile(branchCode);
		LogManager.push(RESULTSIZE+result.length);
		System.out.println("Result length----->"+result.length);
		ArrayList motorVehicleFileListt=null;
		try
		{
		 			motorVehicleFileListt= bean.getVehicleUsageFileList(result);
		}  
		catch(Exception e)
		{
			LogManager.debug(e);
		}
		LogManager.push("++++++++Here Is motorUplodeFileListt Size++++++++"+motorVehicleFileListt.size()); 
	    request.setAttribute("motorVehicleFileListt",motorVehicleFileListt);
		dispatcher = request.getRequestDispatcher("../admin/VehicleUsageFile.jsp");
		dispatcher.forward(request,response); 
		
		
	}
	
	else if("editMotorVehicleFile".equalsIgnoreCase(requestFrom))
	{
		
		LogManager.push(FORMPROCESS);
		mode=request.getParameter(MODE);
		
		 String strvtypeId=request.getParameter(VTYPEID);
		LogManager.push("ID VALUE==>"+strvtypeId);
	 
		
		final String[][] result=bean.getMotorVehicleById(branchCode,strvtypeId);
		
		System.out.println("result.length---->"+result.length);
		if(result.length>0)
		{
			bean.setVtypeId(result[0][0]);
			bean.setVehicleType(result[0][1]);
			bean.setVehicledesc(result[0][2]);
			bean.setVehiclearabic(result[0][3]);
			bean.setVestatus(result[0][4]);
		}
    	request.setAttribute(MOTORCONFIGBEAN,bean);
		LogManager.push("bean strvtypeId===>"+bean.getVtypeId());
		  dispatcher=request.getRequestDispatcher("../admin/MotorVehicleEdit.jsp");
		 
		dispatcher.forward(request,response);
		LogManager.push("------------exit from editVehiclerType-------");
	}
	
	else if("UploadMotorVehicleFile".equalsIgnoreCase(requestFrom))
	{
		LogManager.push("UploadMotorVehicleFile");

		mode=request.getParameter(MODE)==null?"":request.getParameter(MODE);
		LogManager.push("Mode In Vehicle Controller: "+mode);
		  dispatcher=null;
		String strVehicleId=request.getParameter(VTYPEID)==null?"":request.getParameter(VTYPEID);
		LogManager.push("In COntroler Request Color Id IS====>>"+strVehicleId);
		bean.setVtypeId(request.getParameter(VTYPEID)==null?"":request.getParameter(VTYPEID));
		bean.setVehicleType(request.getParameter("vehicleType")==null?"":request.getParameter("vehicleType"));
		bean.setVehicledesc(request.getParameter("vehicledesc")==null?"":request.getParameter("vehicledesc"));
		bean.setVehiclearabic(request.getParameter("vehiclearabic")==null?"":request.getParameter("vehiclearabic"));
		bean.setVestatus(request.getParameter("vestatus")==null?"":request.getParameter("vestatus"));
		
		String strvtypeid=bean.getVtypeId();
		if(bean.getVehicledesc().equalsIgnoreCase(""))
		{
			errorMessage.append("Enter the Vehicle Description <br>");
		}	
		
		if(bean.getVehicleType().equalsIgnoreCase(""))
		{
			
			errorMessage.append("Enter the  Core Application Code <br>");
		}
		
		else  if(session.getAttribute("doProcess")!=null)
		{
			System.out.println("bean.getVehicleType()-inside if -->"+bean.getVehicleType());
		 strvtypeid=(strvtypeid==null||strvtypeid==""||strvtypeid.trim().length()<=0)?"0":strvtypeid;
			final int count=bean.getVehicleCount(branchCode,strvtypeid,bean.getVehicleType());
			if (count>0)
			{
				errorMessage.append("Entered Core Application Code Is Already Exist <br>");
			}
			 
			flag=validate.IsLetterOrDigitWithSpaceValidationFormat(bean.getVtypeId());
			if(!flag)
			{
			errorMessage.append(VALIDCODE);
			}
			
		}
		if(bean.getVestatus().equalsIgnoreCase(""))
		{
			errorMessage.append(VALIDSTATUS);
		}
		
		/*if((bean.getEffDay()+"-"+bean.getEffMonth()+"-"+bean.getEffYear())!=null ||(bean.getEffDay()+"-"+bean.getEffMonth()+"-"+bean.getEffYear())!="" )
		{
			final String message = bean.isValidDate(bean.getEffDay()+"-"+bean.getEffMonth()+"-"+bean.getEffYear());
			if(message!=null && message.trim().length()<=0){
				final String colorId2=bean.getColorId()==null?"":bean.getColorId();
				final String result=bean.isVAlidColorDate((bean.getEffDay()+"-"+bean.getEffMonth()+"-"+bean.getEffYear()),colorId2,branchCode);
				if(result.equalsIgnoreCase("no")){
					errorMessage.append(DATEVALIDATION);
				}
			}else if(message!=null){
				errorMessage.append(message);
			}
		}*/
		if(errorMessage.length()>0)
		 {
			request.setAttribute(ERROR, errorMessage);	 
			dispatcher = request.getRequestDispatcher("../admin/MotorVehicleEdit.jsp?mode="+mode);
		 }
		 else
		 { 
			 String msg="";
			 if(session.getAttribute("doProcess")!=null){
				 msg= bean.insertMotorVehicle(branchCode, strvtypeid,bean,mode);
				 session.removeAttribute("doProcess");
				}
			 LogManager.push(BRANCHCODE+branchCode+"vtype ID::"+ strvtypeid+"Mode Is=--=>"+mode);
			 
			 LogManager.push("*****After insertMotorVehicle*********"+msg);
			 dispatcher = request.getRequestDispatcher("/servlet/MotorConfigController?requestFrom=displayvehicleusage");
	      }
		 
		 dispatcher.forward(request, response);
		  
	
	}
	
	
	
	
 }
		
}
