package com.maan.Office.Controller;

import com.maan.Office.DAO.officeadminBean;
import java.io.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class AdminCont extends HttpServlet
{

    public AdminCont()
    {
    }

    public void destroy()
    {
        super.destroy();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
        System.out.println("hello doget");
        ProcessResult(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
        System.out.println("hello dopost");
        ProcessResult(request, response);
    }

    public void ProcessResult(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
        RequestDispatcher rd = null;
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession(true);
        RequestDispatcher dispatcher = null;
        String openPath = request.getContextPath();
        String prod_id = "30";
        String linkFrom = request.getParameter("linkFrom") != null ? request.getParameter("linkFrom") : "";
        out.println("its coming" + linkFrom);
        officeadminBean oaBean = new officeadminBean();
        String branch = (String)session.getAttribute("AdminBranchCode");
        branch = branch.replaceAll("'", "");
        oaBean.setBranch_code(branch);
        if("schemeinsert".equalsIgnoreCase(linkFrom))
        {
            String error = "";
            String scheme = request.getParameter("scheme") != null ? request.getParameter("scheme") : "";
            String remarks = request.getParameter("remarks") != null ? request.getParameter("remarks") : "";
            String rsacode = request.getParameter("rsacode") != null ? request.getParameter("rsacode") : "";
            String status = request.getParameter("status") != null ? request.getParameter("status") : "";
            error = oaBean.validateSchemeInfo(scheme, rsacode);
            if(error.length() <= 0)
            {
                oaBean.insertSchemeInfo(prod_id, scheme, remarks, rsacode, status);
                request.setAttribute("error", "Successfully Inserted");
            } else
            {
                request.setAttribute("error", error);
            }
            rd = request.getRequestDispatcher("/OfficeConfig/admin_scheme.jsp");
        } else
        if("contentinsert".equalsIgnoreCase(linkFrom))
        {
            String error = "";
            String scheme = request.getParameter("scheme") != null ? request.getParameter("scheme") : "";
            String cont_desc = request.getParameter("cont_desc") != null ? request.getParameter("cont_desc") : "";
            String cont_value = request.getParameter("cont_value") != null ? request.getParameter("cont_value") : "";
            String min_prem = request.getParameter("min_prem") != null ? request.getParameter("min_prem") : "";
            String remarks = request.getParameter("remarks") != null ? request.getParameter("remarks") : "";
            String rsacode = request.getParameter("rsacode") != null ? request.getParameter("rsacode") : "";
            String status = request.getParameter("status") != null ? request.getParameter("status") : "";
            error = oaBean.validateContentInfo(scheme, cont_desc, min_prem, rsacode);
            if(error.length() <= 0)
            {
                oaBean.insertContentInfo(prod_id, scheme, cont_desc, cont_value, min_prem, remarks, rsacode, status);
                request.setAttribute("error", "Successfully Inserted");
            } else
            {
                request.setAttribute("error", error);
            }
            rd = request.getRequestDispatcher("/OfficeConfig/admin_content.jsp");
        } else
        if("coverageinsert".equalsIgnoreCase(linkFrom))
        {
            String error = "";
            String Cname = request.getParameter("Cname") != null ? request.getParameter("Cname") : "";
            String Cdisname = request.getParameter("Cdisname") != null ? request.getParameter("Cdisname") : "";
            String sce_details = request.getParameter("sce_details") != null ? request.getParameter("sce_details") : "";
            String remarks = request.getParameter("remarks") != null ? request.getParameter("remarks") : "";
            String rsacode = request.getParameter("rsacode") != null ? request.getParameter("rsacode") : "";
            String status = request.getParameter("status") != null ? request.getParameter("status") : "";
            error = oaBean.validateCoverageInfo(Cname, Cdisname, rsacode);
            if(error.length() <= 0)
            {
                oaBean.insertCoverageInfo(Cname, Cdisname, sce_details, remarks, rsacode, status);
                request.setAttribute("error", "Successfully Inserted");
            } else
            {
                request.setAttribute("error", error);
            }
            rd = request.getRequestDispatcher("/OfficeConfig/admin_ofsmaster.jsp");
        } else
        if("schemeedit".equalsIgnoreCase(linkFrom))
        {
            String values[][] = new String[0][0];
            String scheme = request.getParameter("scheme") != null ? request.getParameter("scheme") : "";
            values = oaBean.getSchemeInfo(prod_id, scheme);
            request.setAttribute("values", values);
            request.setAttribute("mode", "updatescheme");
            rd = request.getRequestDispatcher("/OfficeConfig/admin_scheme.jsp");
        }
        if("updatescheme".equalsIgnoreCase(linkFrom))
        {
            String error = "";
            String scheme_id = request.getParameter("scheme_id") != null ? request.getParameter("scheme_id") : "";
            String scheme = request.getParameter("scheme") != null ? request.getParameter("scheme") : "";
            String remarks = request.getParameter("remarks") != null ? request.getParameter("remarks") : "";
            String rsacode = request.getParameter("rsacode") != null ? request.getParameter("rsacode") : "";
            String status = request.getParameter("status") != null ? request.getParameter("status") : "";
            error = oaBean.validateSchemeInfo(scheme, rsacode);
            if(error.length() <= 0)
            {
                oaBean.updateSchemeInfo(prod_id, scheme, remarks, rsacode, scheme_id, status);
                request.setAttribute("error", "Updated Successfully");
            } else
            {
                request.setAttribute("mode", "updatescheme");
                request.setAttribute("error", error);
            }
            rd = request.getRequestDispatcher("/OfficeConfig/admin_scheme.jsp");
        } else
        if("contentedit".equalsIgnoreCase(linkFrom))
        {
            String values[][] = new String[0][0];
            String scheme = request.getParameter("scheme") != null ? request.getParameter("scheme") : "";
            String contentType = request.getParameter("contentType") != null ? request.getParameter("contentType") : "";
            values = oaBean.getContentInfo(prod_id, scheme, contentType);
            request.setAttribute("values", values);
            request.setAttribute("mode", "updatecontent");
            rd = request.getRequestDispatcher("/OfficeConfig/admin_content.jsp");
        } else
        if("updatecontent".equalsIgnoreCase(linkFrom))
        {
            String error = "";
            String scheme = request.getParameter("scheme") != null ? request.getParameter("scheme") : "";
            String cont_desc = request.getParameter("cont_desc") != null ? request.getParameter("cont_desc") : "";
            String cont_value = request.getParameter("cont_value") != null ? request.getParameter("cont_value") : "";
            String min_prem = request.getParameter("min_prem") != null ? request.getParameter("min_prem") : "";
            String remarks = request.getParameter("remarks") != null ? request.getParameter("remarks") : "";
            String rsacode = request.getParameter("rsacode") != null ? request.getParameter("rsacode") : "";
            String content_id = request.getParameter("content_id") != null ? request.getParameter("content_id") : "";
            String status = request.getParameter("status") != null ? request.getParameter("status") : "";
            error = oaBean.validateContentInfo(scheme, cont_desc, min_prem, rsacode);
            if(error.length() <= 0)
            {
                oaBean.updateContentInfo(prod_id, scheme, cont_desc, cont_value, min_prem, remarks, rsacode, content_id, status);
                request.setAttribute("error", "Successfully updated");
            } else
            {
                request.setAttribute("mode", "updatecontent");
                request.setAttribute("error", error);
            }
            rd = request.getRequestDispatcher("/OfficeConfig/admin_content.jsp");
        } else
        if("coverageedit".equalsIgnoreCase(linkFrom))
        {
            String values[][] = new String[0][0];
            String coverage = request.getParameter("coverage") != null ? request.getParameter("coverage") : "";
            values = oaBean.getCoverageInfo(coverage);
            request.setAttribute("values", values);
            request.setAttribute("mode", "updatecoverage");
            rd = request.getRequestDispatcher("/OfficeConfig/admin_ofsmaster.jsp");
        } else
        if("updatecoverage".equalsIgnoreCase(linkFrom))
        {
            String error = "";
            String Cname = request.getParameter("Cname") != null ? request.getParameter("Cname") : "";
            String Cdisname = request.getParameter("Cdisname") != null ? request.getParameter("Cdisname") : "";
            String sce_details = request.getParameter("sce_details") != null ? request.getParameter("sce_details") : "";
            String remarks = request.getParameter("remarks") != null ? request.getParameter("remarks") : "";
            String rsacode = request.getParameter("rsacode") != null ? request.getParameter("rsacode") : "";
            String coverage_id = request.getParameter("coverage_id") != null ? request.getParameter("coverage_id") : "";
            String status = request.getParameter("status") != null ? request.getParameter("status") : "";
            error = oaBean.validateCoverageInfo(Cname, Cdisname, rsacode);
            if(error.length() <= 0)
            {
                oaBean.updateCoverageInfo(Cname, Cdisname, sce_details, remarks, rsacode, coverage_id, status);
                request.setAttribute("error", "Successfully Updated");
            } else
            {
                request.setAttribute("mode", "updatecoverage");
                request.setAttribute("error", error);
            }
            rd = request.getRequestDispatcher("/OfficeConfig/admin_ofsmaster.jsp");
        }else if("extensionedit".equalsIgnoreCase(linkFrom)){
        	String values[][]=new String[0][0];
        	String scheme=(String)request.getParameter("scheme")==null?"":(String)request.getParameter("scheme");
        	String contentType=(String)request.getParameter("contentType")==null?"":(String)request.getParameter("contentType");
        	values=oaBean.getExtensionInfo(prod_id,scheme,contentType);
        	request.setAttribute("values",values);
        	request.setAttribute("mode","updateextension");
        	request.setAttribute("contentType",contentType);
            rd = request.getRequestDispatcher("/OfficeConfig/admin_extension_new.jsp");
        	   
        }else if("extensioninsertview".equalsIgnoreCase(linkFrom)){
        	String values[][]=new String[0][0];
        	String scheme=(String)request.getParameter("scheme")==null?"":(String)request.getParameter("scheme");
        	String contentType=(String)request.getParameter("contentType")==null?"":(String)request.getParameter("contentType");
        	values=oaBean.getCoverInfo();
        	request.setAttribute("values",values);
        	request.setAttribute("mode","extensioninsert");
        	request.setAttribute("contentType",contentType);
        	request.setAttribute("scheme",scheme);
            rd = request.getRequestDispatcher("/OfficeConfig/admin_extension.jsp");
        	   
        }else if("updateextension".equalsIgnoreCase(linkFrom)){
        	   String error="";
        	   String values[][]=new String[0][0];
               String scheme=(String)request.getParameter("scheme_id")==null?"":(String)request.getParameter("scheme_id");
        	   String content_id=(String)request.getParameter("content_id")==null?"":(String)request.getParameter("content_id");
        	   String cover_id=(String)request.getParameter("cover_id")==null?"":(String)request.getParameter("cover_id");
        	   String ext_id=(String)request.getParameter("ext_id")==null?"":(String)request.getParameter("ext_id");
        	   String ival=(String)request.getParameter("ival")==null?"":(String)request.getParameter("ival");
        	   String status=(String)request.getParameter("status"+ival)==null?"":(String)request.getParameter("status"+ival);
        	   String ext_name=(String)request.getParameter("ext_name"+ival)==null?"":(String)request.getParameter("ext_name"+ival);
        	   String dis_order=(String)request.getParameter("dis_order"+ival)==null?"":(String)request.getParameter("dis_order"+ival);
        	   String effectiveDate=(String)request.getParameter("effectiveDate"+ival)==null?"":(String)request.getParameter("effectiveDate"+ival);
        	   String rsa_code=(String)request.getParameter("rsa_code"+ival)==null?"":(String)request.getParameter("rsa_code"+ival);
        	   System.out.println("dis_order"+dis_order+"ext_name"+ext_name+"status"+status);
        	   //error=oaBean.validateContentInfo( scheme,cont_desc,min_prem,rsacode);
        	   if(error.length()<=0)
        	    {  
        	    oaBean.updateExtensionInfo(prod_id,scheme,content_id,status,cover_id,ext_id,ext_name,dis_order,effectiveDate,rsa_code);
        	    values=oaBean.getExtensionInfo(prod_id,scheme,content_id);
        		request.setAttribute("values",values);
        		request.setAttribute("mode","updateextension");
        		request.setAttribute("contentType",content_id);
        	    rd = request.getRequestDispatcher("/OfficeConfig/admin_extension_new.jsp");
        	    request.setAttribute("error","Successfully updated");
        		}else 
        	      {
        		   request.setAttribute("mode","updatecontent");
        		    request.setAttribute("error",error);
        		  }        	   
        }else if("extensioninsert".equalsIgnoreCase(linkFrom)) {
        	   String error="";
               String scheme=(String)request.getParameter("scheme")==null?"":(String)request.getParameter("scheme");
        	   String extn_desc=(String)request.getParameter("extn_desc")==null?"":(String)request.getParameter("extn_desc");
        	   String cont_type=(String)request.getParameter("cont_type")==null?"":(String)request.getParameter("cont_type");
        	   String cover=(String)request.getParameter("cover")==null?"":(String)request.getParameter("cover");
        	   String remarks=(String)request.getParameter("remarks")==null?"":(String)request.getParameter("remarks");
        	   String rsacode=(String)request.getParameter("rsacode")==null?"":(String)request.getParameter("rsacode");
        	   String status=(String)request.getParameter("status")==null?"":(String)request.getParameter("status");
        	   String effectiveDate=(String)request.getParameter("effectiveDate")==null?"":(String)request.getParameter("effectiveDate");
        	   //error=oaBean.validateContentInfo( scheme,cont_desc,min_prem,rsacode);
        	   if(error.length()<=0)
        	    {  
        	    oaBean.insertExtendsInfo(prod_id,scheme,cont_type,extn_desc,cover,remarks,rsacode,status,effectiveDate);
        		 
        	    request.setAttribute("error","Successfully Inserted");
        		}else
        			 request.setAttribute("error",error);
        	  
        	   rd = request.getRequestDispatcher("/OfficeConfig/admin_extension_edit.jsp");
        }
        if(rd != null)
        {
            rd.forward(request, response);
            return;
        } else
        {
            return;
        }
    }
}
