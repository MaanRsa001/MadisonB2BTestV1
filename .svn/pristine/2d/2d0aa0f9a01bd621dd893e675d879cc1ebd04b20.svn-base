package com.maan.Office.Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.maan.DBCon.DBConnection;
import com.maan.DBCon.DBConnectionStatus;
import com.maan.Office.DAO.OfficeShieldBean;
import com.maan.common.util.StringUtil;


public class OFSController extends HttpServlet  
{
    public  void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException
	{ 
        ProceesRequest(request,response);
    }
    public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException
    {
         ProceesRequest(request,response);
    }
	public void ProceesRequest(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException
	{
		HttpSession session = request.getSession(true);
         if(session.getAttribute("ses")==null)
		 {	
			response.sendRedirect(request.getContextPath()+"/login/error_messg.jsp");
			return;
		 }    
		
		RequestDispatcher dispatcher = null;
		String path = request.getContextPath();
          
        /*RequestDispatcher check = request.getRequestDispatcher("/login/sessionsCheckNormal.jsp");
        check.include(request,response);*/
   	
		//SESSION CHECK
        String usrModeSC=(String)session.getAttribute("userLoginMode")==null?"":(String)session.getAttribute("userLoginMode");
        DBConnectionStatus.statusStatic=usrModeSC;
        //END OF SESSION CHECK
        
		PrintWriter out = response.getWriter();
        response.setContentType("text/html");

		String branch = "";
		String productId = "";
		String error = "";
		String schemeId = "";
		String quoteNo = "";
		String applicationNo = "";
		String loginId = "";
		String requestfrom ="";

		branch = (String)session.getAttribute("LoginBranchCode");
		productId = (String) session.getAttribute("product_id");
		loginId = (String) session.getAttribute("user");
		quoteNo = (String) session.getAttribute("quoteNo");
		//schemeId = (String) session.getAttribute("schemeId");

		loginId = loginId == null ? "" :loginId;
		branch = branch == null?"020":branch;
		productId = productId ==null?"":productId;
			
		requestfrom = request.getParameter("requestfrom");
		
		OfficeShieldBean OSB = new OfficeShieldBean();
		StringUtil strValid = new StringUtil();
		
		schemeId = OSB.getSchemeId(quoteNo,productId);
		schemeId = schemeId == null ? "" : schemeId;

		if(requestfrom.equalsIgnoreCase("SaveAndExit"))
		{
			String AddItem = request.getParameter("AddItem");
			String conTypId = request.getParameter("conTypId");

			AddItem = AddItem == null ?"":AddItem;
			conTypId = conTypId == null ?"":conTypId;

			String cont_desc[][] = new String[5][5];
			String replace_value[][] = new String[5][5];
			String make_year[][] = new String[5][5];
			String equip_desc[][] = new String[5][5];
			String no_emp[][] = new String[5][5];
			String emp_design[][] = new String[5][5];
			String emp_wages[][] = new String[5][5];
			String emp_name[][] = new String[5][5];
			String emp_coverages[][] = new String[5][5];
			String emp_capSumIns[][] = new String[5][5];
			String inceptionDD[][] = new String[5][5];
			String inceptionMonth[][] = new String[5][5];
			String inceptionYear[][] = new String[5][5];
			String dob[] = new String[5];
			/*String temp1[][] = new String[5][5];
			String temp2[][] = new String[5][5];
			String temp3[][] = new String[5][5];*/
			
			String noOfFidelityEmp="0";
			String noOfEmpForGPA="0";
			String noOfEmpDetails[][] = new String[0][0];

			HashMap portHash = new HashMap();
			HashMap fidelityHash = new HashMap();
			boolean flag = true;
			boolean capitalSIFlag = true;
			boolean zeroFlag = true;
			AddItem = AddItem == null ? "" : AddItem;
			
			if(AddItem.equalsIgnoreCase("1"))
			{
				for(int i=0;i<(5);i++)
					cont_desc[0][i] = request.getParameter("Cont_Desc_"+AddItem+"_"+i);
				for(int i=0;i<(5);i++)
					replace_value[0][i] = request.getParameter("Replace_Value_"+AddItem+"_"+i);
				
				for(int i=0;i<5;i++) // Validation
				{
					flag = OSB.validAmount(replace_value[0][i]);
					
					if(replace_value[0][i].length() > 0)
						zeroFlag = OSB.checkZero(replace_value[0][i]);
					
					if(cont_desc[0][i].equalsIgnoreCase("") || cont_desc[0][i].equalsIgnoreCase("null") || cont_desc[0][i]==null)
						error = error+"<br>* Please Enter Content Description "+(i+1);

					if(replace_value[0][i].equalsIgnoreCase("") || replace_value[0][i].equalsIgnoreCase("null") || replace_value[0][i]==null)
						error = error+"<br>* Please Enter Replacement Value "+(i+1);

					if(!flag)
						error = error+"<br>* Please Enter Valid Repalcement Value  "+(i+1);
					if(!zeroFlag)
					{
						error = error+"<br>* Please Enter Valid Repalcement Value  "+(i+1);
						zeroFlag = true;
					}
				}
			}
			
			if(AddItem.equalsIgnoreCase("5"))
			{
				for(int i=0;i<(5);i++)
					no_emp[0][i] = request.getParameter("No_OF_Emp_"+AddItem+"_"+i);
				for(int i=0;i<(5);i++)
					emp_design[0][i] = request.getParameter("Emp_Designation_"+AddItem+"_"+i);
				for(int i=0;i<(5);i++)
					emp_wages[0][i] = request.getParameter("Emp_Wages_"+AddItem+"_"+i);
				
				for(int i=0;i<5;i++)
				{
					flag = OSB.validAmount(no_emp[0][i]);
					
					if(emp_design[0][i].equalsIgnoreCase("") || emp_design[0][i].equalsIgnoreCase("null") || emp_design[0][i]==null)
						error = error+"<br>* Please Enter Employee Designation "+(i+1);

					if(no_emp[0][i].equalsIgnoreCase("") || no_emp[0][i].equalsIgnoreCase("null") || no_emp[0][i]==null)
						error = error+"<br>* Please Enter No. Of Employees  "+(i+1);

					if(!flag)
						error = error+"<br>* Please Enter Valid Number of Employees Count "+(i+1);
				}
				
				for(int i=0;i<5;i++)
				{
					flag = OSB.validAmount(emp_wages[0][i]);
						
					if(emp_wages[0][i].equalsIgnoreCase("") || emp_wages[0][i].equalsIgnoreCase("null") || emp_wages[0][i]==null)
						error = error+"<br>* Please Enter Employee Wages Amount "+(i+1);

					if(!flag)
						error = error+"<br>* Please Enter Valid Wages Amount "+(i+1);
				}
			}
			//Portable Equipment Add Info
			if(AddItem.equalsIgnoreCase("2")) 
			{
				boolean erFlag = false;
				for(int i=0;i<(5);i++)
					make_year[0][i] = request.getParameter("Make_Year_"+AddItem+"_"+i);
				for(int i=0;i<(5);i++)
					equip_desc[0][i] = request.getParameter("Equip_Desc_"+AddItem+"_"+i);
				for(int i=0;i<(5);i++)
					replace_value[0][i] = request.getParameter("Replace_Value_"+AddItem+"_"+i);
				int x=0;
				for(int i=0;i<5;i++)
				{
					if( (make_year[0][i].equalsIgnoreCase("") || make_year[0][i].equalsIgnoreCase("null") || make_year[0][i]==null) && (equip_desc[0][i].equalsIgnoreCase("") || equip_desc[0][i].equalsIgnoreCase("null") || equip_desc[0][i]==null) && (replace_value[0][i].equalsIgnoreCase("") || replace_value[0][i].equalsIgnoreCase("null") || replace_value[0][i]==null))
					{
						erFlag = true;
						x++;
					}
				}

				if(erFlag && x==5)
					error = error+"<br>* Please Enter the details ";

				for(int i=0;i<5;i++)
				{
					flag = OSB.validAmount(make_year[0][i]);
					if(make_year[0][i].length() > 0 )
						zeroFlag = OSB.checkZero(make_year[0][i]);
					if(!flag)
						error = error+"<br>* Please Enter Valid Year  "+(i+1);
					if(!zeroFlag)
					{
						error = error+"<br>* Please Enter Valid Year  "+(i+1);
						zeroFlag = true;
					}
				}

				for(int i=0;i<5;i++) //Validation
				{
					if( (make_year[0][i].equalsIgnoreCase("") || make_year[0][i].equalsIgnoreCase("null") || make_year[0][i]==null) && (equip_desc[0][i].equalsIgnoreCase("") || equip_desc[0][i].equalsIgnoreCase("null") || equip_desc[0][i]==null) && (replace_value[0][i].equalsIgnoreCase("") || replace_value[0][i].equalsIgnoreCase("null") || replace_value[0][i]==null))
					{

					}	
					else
					{
						if(make_year[0][i].equalsIgnoreCase("") || make_year[0][i].equalsIgnoreCase("null") || make_year[0][i]==null)
							error = error+"<br>* Please Enter Year Value "+(i+1);
						if(equip_desc[0][i].equalsIgnoreCase("") || equip_desc[0][i].equalsIgnoreCase("null") || equip_desc[0][i]==null)
							error = error+"<br>* Please Enter Equipment Description Value "+(i+1);
						if(replace_value[0][i].equalsIgnoreCase("") || replace_value[0][i].equalsIgnoreCase("null") || replace_value[0][i]==null)
							error = error+"<br>* Please Enter Replacement Value "+(i+1);
					}

					flag = OSB.validAmount(replace_value[0][i]);
					if(replace_value[0][i].length() > 0)
						zeroFlag = OSB.checkZero(replace_value[0][i]);
					if(!flag)
						error = error+"<br>* Please Enter Valid Replacement Value  "+(i+1);
					if(!zeroFlag)
					{
						error = error+"<br>* Please Enter Valid Replacement Value  "+(i+1);
						zeroFlag = true;
					}
				}
				//
				
				if(error.length() == 0)
				{	int c=0;
					for(int i=0;i<5;i++) //Validation
					{
						if( !(make_year[0][i].equalsIgnoreCase("") || make_year[0][i].equalsIgnoreCase("null") || make_year[0][i]==null) && !(equip_desc[0][i].equalsIgnoreCase("") || equip_desc[0][i].equalsIgnoreCase("null") || equip_desc[0][i]==null) && !(replace_value[0][i].equalsIgnoreCase("") || replace_value[0][i].equalsIgnoreCase("null") || replace_value[0][i]==null))
						{
							/*temp1[0][i] = make_year[0][i];
							temp2[0][i] = equip_desc[0][i];
							temp3[0][i] = replace_value[0][i];*/
							String temp = make_year[0][i]+"~"+equip_desc[0][i]+"~"+replace_value[0][i];
								portHash.put("port"+(++c),temp);
						}
					}
					System.out.println("...portHash... "+portHash.size());
				}
			} //Portable Equipment Add Info

			/** FIDELITY GUARANTEE old- GROUP PERSONAL ACCIDENT - Start **/

			//if(AddItem.equalsIgnoreCase("8") || AddItem.equalsIgnoreCase("10") )
			if(AddItem.equalsIgnoreCase("10") )
			{
				/*if(AddItem.equalsIgnoreCase("8")) //old
				{
					noOfEmpDetails = OSB.getNoOfEmpForFidelity(quoteNo,productId,schemeId,AddItem,conTypId);
					if(noOfEmpDetails.length > 0)
						noOfFidelityEmp = noOfEmpDetails[0][3];
				}
				else
					noOfFidelityEmp = "5";*/

				noOfEmpForGPA = OSB.getNoOfEmpForGPA(quoteNo,productId,schemeId,AddItem,conTypId);
					
				noOfFidelityEmp = noOfEmpForGPA; //		noOfFidelityEmp = "5";
									
				boolean erFlag = false;
				for(int i=0;i<Integer.parseInt(noOfFidelityEmp);i++)
					emp_name[0][i] = request.getParameter("emp_name_"+AddItem+"_"+i);
				for(int i=0;i<Integer.parseInt(noOfFidelityEmp);i++)
					emp_design[0][i] = request.getParameter("Emp_Designation_"+AddItem+"_"+i);
				for(int i=0;i<Integer.parseInt(noOfFidelityEmp);i++)
					emp_wages[0][i] = request.getParameter("Emp_Salary_"+AddItem+"_"+i); //salary
			/*	for(int i=0;i<Integer.parseInt(noOfFidelityEmp);i++)
					emp_coverages[0][i] = request.getParameter("Emp_Coverage_"+AddItem+"_"+i);	*/
				for(int i=0;i<Integer.parseInt(noOfFidelityEmp);i++)	
					emp_capSumIns[0][i] = request.getParameter("Emp_CapSumIns_"+AddItem+"_"+i);

				for(int i=0;i<Integer.parseInt(noOfFidelityEmp);i++)
					inceptionDD[0][i] = request.getParameter("inceptionDD_"+AddItem+"_"+i);
				for(int i=0;i<Integer.parseInt(noOfFidelityEmp);i++)
					inceptionMonth[0][i] = request.getParameter("inceptionMonth_"+AddItem+"_"+i);
				for(int i=0;i<Integer.parseInt(noOfFidelityEmp);i++)
					inceptionYear[0][i] = request.getParameter("inceptionYear_"+AddItem+"_"+i);
				
				int x=0;
				try	
				{
					for(int i=0;i<Integer.parseInt(noOfFidelityEmp);i++)
					{
						//if( (emp_name[0][i].equalsIgnoreCase("") || emp_name[0][i].equalsIgnoreCase("null") || emp_name[0][i]==null) && (emp_design[0][i].equalsIgnoreCase("") || emp_design[0][i].equalsIgnoreCase("null") || emp_design[0][i]==null) && (emp_wages[0][i].equalsIgnoreCase("") || emp_wages[0][i].equalsIgnoreCase("null") || emp_wages[0][i]==null) && (emp_coverages[0][i].equalsIgnoreCase("") || emp_coverages[0][i].equalsIgnoreCase("null") || emp_coverages[0][i]==null) && (emp_capSumIns[0][i].equalsIgnoreCase("") || emp_capSumIns[0][i].equalsIgnoreCase("null") || emp_capSumIns[0][i]==null) ) //old
						if( (emp_name[0][i].equalsIgnoreCase("") || emp_name[0][i].equalsIgnoreCase("null") || emp_name[0][i]==null) && (emp_design[0][i].equalsIgnoreCase("") || emp_design[0][i].equalsIgnoreCase("null") || emp_design[0][i]==null) && (emp_wages[0][i].equalsIgnoreCase("") || emp_wages[0][i].equalsIgnoreCase("null") || emp_wages[0][i]==null) &&  (emp_capSumIns[0][i].equalsIgnoreCase("") || emp_capSumIns[0][i].equalsIgnoreCase("null") || emp_capSumIns[0][i]==null) )
						{
							erFlag = true;
							x++;
						}
					}
				}
				catch(Exception e)
				{
					System.out.println("Exception is..."+e);
					e.printStackTrace();
				}
				
				if(erFlag && x == Integer.parseInt(noOfFidelityEmp))
					error = error+"<br>* Please Enter the details ";
				
				for(int i=0;i<Integer.parseInt(noOfFidelityEmp);i++)
				{
					flag = OSB.validAmount(emp_wages[0][i]);
					if(emp_wages[0][i].length() > 0)
						zeroFlag = OSB.checkZero(emp_wages[0][i]);
					if(!flag)
						error = error+"<br>* Please Enter Valid Salary  "+(i+1);
					if(!zeroFlag)
					{
						error = error+"<br>* Please Enter Valid Salary  "+(i+1);
						zeroFlag = true;
					}
				}

				for(int i=0;i<Integer.parseInt(noOfFidelityEmp);i++)
				{
					capitalSIFlag = OSB.validAmount(emp_capSumIns[0][i]);
					if(emp_capSumIns[0][i].length() > 0)
						zeroFlag = OSB.checkZero(emp_capSumIns[0][i]);
					if(!capitalSIFlag )
						error = error+"<br>* Please Enter Valid Capital Sum Insured Amount "+(i+1);
					if(!zeroFlag)
					{
						error = error+"<br>* Please Enter Valid Capital Sum Insured Amount "+(i+1);
						zeroFlag = true;
					}
				}
				
				for(int i=0;i<Integer.parseInt(noOfFidelityEmp);i++)
				{
					emp_design[0][i] = emp_design[0][i].trim();
					flag = strValid.isAlphabets(emp_design[0][i]);
					if(!flag)
						error = error+"<br>* Please Select employee designation "+(i+1);
				}

				for(int i=0;i<Integer.parseInt(noOfFidelityEmp);i++)
				{
					emp_name[0][i] = emp_name[0][i].trim();
					flag = strValid.isAlphabets(emp_name[0][i]);
					if(!flag)
						error = error+"<br>* Please enter valid employee name "+(i+1);
				}

				for(int i=0;i<Integer.parseInt(noOfFidelityEmp);i++) //Validation
				{
					//if( (emp_name[0][i].equalsIgnoreCase("") || emp_name[0][i].equalsIgnoreCase("null") || emp_name[0][i]==null) && (emp_design[0][i].equalsIgnoreCase("") || emp_design[0][i].equalsIgnoreCase("null") || emp_design[0][i]==null) && (emp_wages[0][i].equalsIgnoreCase("") || emp_wages[0][i].equalsIgnoreCase("null") || emp_wages[0][i]==null) && (emp_coverages[0][i].equalsIgnoreCase("") || emp_coverages[0][i].equalsIgnoreCase("null") || emp_coverages[0][i]==null) && (emp_capSumIns[0][i].equalsIgnoreCase("") || emp_capSumIns[0][i].equalsIgnoreCase("null") || emp_capSumIns[0][i]==null) ) // old
					if( (emp_name[0][i].equalsIgnoreCase("") || emp_name[0][i].equalsIgnoreCase("null") || emp_name[0][i]==null) && (emp_design[0][i].equalsIgnoreCase("") || emp_design[0][i].equalsIgnoreCase("null") || emp_design[0][i]==null) && (emp_wages[0][i].equalsIgnoreCase("") || emp_wages[0][i].equalsIgnoreCase("null") || emp_wages[0][i]==null) && (emp_capSumIns[0][i].equalsIgnoreCase("") || emp_capSumIns[0][i].equalsIgnoreCase("null") || emp_capSumIns[0][i]==null) )
					{
					
					}
					else
					{
						if(emp_name[0][i].equalsIgnoreCase("") || emp_name[0][i].equalsIgnoreCase("null") || emp_name[0][i]==null)
							error = error+"<br>* Please enter employee name "+(i+1);
						if(emp_design[0][i].equalsIgnoreCase("select") || emp_design[0][i].equalsIgnoreCase("null") || emp_design[0][i]==null)
							error = error+"<br>* Please select employee designation "+(i+1);
						if(emp_wages[0][i].equalsIgnoreCase("") || emp_wages[0][i].equalsIgnoreCase("null") || emp_wages[0][i]==null)
							error = error+"<br>* Please enter salary value "+(i+1);
						if(emp_capSumIns[0][i].equalsIgnoreCase("") || emp_capSumIns[0][i].equalsIgnoreCase("null") || emp_capSumIns[0][i]==null)
							error = error+"<br>* Please enter capital sum insured value "+(i+1);
					/*	if(emp_coverages[0][i].equalsIgnoreCase("") || emp_coverages[0][i].equalsIgnoreCase("null") || emp_coverages[0][i]==null)
							error = error+"<br>* Please enter coverage "+(i+1);	*/
					
							String err = OSB.dateValidation(inceptionDD[0][i]+"/"+inceptionMonth[0][i]+"/"+inceptionYear[0][i]); 
							System.out.println("...err...."+err);
							if(err.length() > 0)
								error = error + err + " row " + (i+1)+ "<br>";
							else
								error = error + err;
							boolean flag18 = false;
							
							try
							{
								if(err.length() == 0) //new Jan 16
								{						
									flag18 = OSB.validEmpDOB18(inceptionDD[0][i],inceptionMonth[0][i],inceptionYear[0][i]);
									if(!flag18)
										error = error+"<br>* Employee DOB should be between the age of 18 to 65 row "+(i+1);
								}
							}
							catch(Exception e)
							{
								System.out.println("Date Validation....OFSController..."+e);
								e.printStackTrace();
							}
						
						capitalSIFlag = OSB.validAmount(emp_capSumIns[0][i]);
						
						if(!(emp_wages[0][i].equalsIgnoreCase("") || emp_wages[0][i].equalsIgnoreCase("null") || emp_wages[0][i]==null) && !(emp_capSumIns[0][i].equalsIgnoreCase("") || emp_capSumIns[0][i].equalsIgnoreCase("null") || emp_capSumIns[0][i]==null) && capitalSIFlag)
						{
							double empWages = 0.0;
							double empSumIns = 0.0;
							double calculateSI = 0.0;
							double maxLimit = 1500000.0;
							flag = OSB.validAmount(emp_wages[0][i]);
							if(flag)
								empWages = Double.parseDouble(emp_wages[0][i]); 
							flag = OSB.validAmount(emp_wages[0][i]);
							if(flag)
								empSumIns = Double.parseDouble(emp_capSumIns[0][i]); 
	
							calculateSI = 4 * empWages; 
							System.out.println("...empWages..."+empWages);
							System.out.println("...empSumIns..."+empSumIns);
							System.out.println("...calculateSI..."+calculateSI);
							System.out.println("...empWages..."+empWages);
							
							if( calculateSI < maxLimit )	// 400 < 1500000
								maxLimit = calculateSI;
							
							if( empSumIns > maxLimit  ) // 99999999999 >400
							{	
								error = error+"<br>* Sum Insured should be maximum of 4 times of annual salary or Dhs. 1,500,000 whichever is lower, per person "+(i+1);	
							}
						}
					}
				}

				if(error.length() == 0)
				{	int c=0;
					for(int i=0;i<Integer.parseInt(noOfFidelityEmp);i++) //Validation
					{	
						try
						{	
						//	if( !(emp_name[0][i].equalsIgnoreCase("") || emp_name[0][i].equalsIgnoreCase("null") || emp_name[0][i]==null) && !(emp_design[0][i].equalsIgnoreCase("") || emp_design[0][i].equalsIgnoreCase("null") || emp_design[0][i]==null) && !(emp_wages[0][i].equalsIgnoreCase("") || emp_wages[0][i].equalsIgnoreCase("null") || emp_wages[0][i]==null) && !(emp_coverages[0][i].equalsIgnoreCase("") || emp_coverages[0][i].equalsIgnoreCase("null") || emp_coverages[0][i]==null) && (emp_capSumIns[0][i].equalsIgnoreCase("") || emp_capSumIns[0][i].equalsIgnoreCase("null") || emp_capSumIns[0][i]==null) ) //old
							if( !(emp_name[0][i].equalsIgnoreCase("") || emp_name[0][i].equalsIgnoreCase("null") || emp_name[0][i]==null) && !(emp_design[0][i].equalsIgnoreCase("") || emp_design[0][i].equalsIgnoreCase("null") || emp_design[0][i]==null) && !(emp_wages[0][i].equalsIgnoreCase("") || emp_wages[0][i].equalsIgnoreCase("null") || emp_wages[0][i]==null) && !(emp_capSumIns[0][i].equalsIgnoreCase("") || emp_capSumIns[0][i].equalsIgnoreCase("null") || emp_capSumIns[0][i]==null) )
							{
								String dob1 = inceptionDD[0][i]+"-"+inceptionMonth[0][i]+"-"+inceptionYear[0][i];
								System.out.println("....dob1..."+dob1);
								/*String temp = "";
								temp = emp_name[0][i]+"~"+emp_design[0][i]+"~"+emp_wages[0][i]+"~"+emp_coverages[0][i]+"~"+dob1+"~"+emp_capSumIns[0][i];*/
								String temp = "";
								temp = emp_name[0][i]+"~"+emp_design[0][i]+"~"+emp_wages[0][i]+"~"+dob1+"~"+emp_capSumIns[0][i];
								System.out.println("...fidelity... "+temp);
								fidelityHash.put("fidelity"+(++c),temp);
							}
						}
						catch(Exception e){System.out.println("dsfsdfsdf"+e);e.printStackTrace();}
					}
					System.out.println("...fidelity...c "+c);
					System.out.println("...fidelity... "+fidelityHash.size());
					if(Integer.parseInt(noOfEmpForGPA) == c){}
					else
						error = error+"<br>* Please enter all employee details  ";

				}
			}

			/** FIDELITY GUARANTEE old - GROUP PERSONAL ACCIDENT - End **/

			/** FIDELITY GUARANTEE new - TRVEL BAGGAGE - Start **/

			if(AddItem.equalsIgnoreCase("8") || AddItem.equalsIgnoreCase("9") )
			{
				if(AddItem.equalsIgnoreCase("8")) 
				{
					noOfEmpDetails = OSB.getNoOfEmpForFidelity(quoteNo,productId,schemeId,AddItem,conTypId);
					if(noOfEmpDetails.length > 0)
						noOfFidelityEmp = noOfEmpDetails[0][3];
				}
				else
					noOfFidelityEmp = "5";

				for(int i=0;i<Integer.parseInt(noOfFidelityEmp);i++)
				{
					emp_name[0][i] = request.getParameter("emp_name_"+AddItem+"_"+i);
					System.out.println("Emp Name..."+"emp_name_"+AddItem+"_"+i+"   "+emp_name[0][i]);
				}
				for(int i=0;i<Integer.parseInt(noOfFidelityEmp);i++)
				{
					emp_design[0][i] = request.getParameter("Emp_Designation_"+AddItem+"_"+i);
					System.out.println("Emp Name..."+emp_design[0][i]);
				}
				
				boolean erFlag = false;
				int x = 0;				
				
				try
				{
					for(int i=0;i<Integer.parseInt(noOfFidelityEmp);i++)
					{
						if( (emp_name[0][i].equalsIgnoreCase("") || emp_name[0][i].equalsIgnoreCase("null") || emp_name[0][i]==null) && (emp_design[0][i].equalsIgnoreCase("") || emp_design[0][i].equalsIgnoreCase("null") || emp_design[0][i]==null) )
						{
							erFlag = true;
							x++;
						}
					}
				}
				catch(Exception e)
				{
					System.out.println("Exception is..."+e);
					e.printStackTrace();
				}
				
				if(erFlag && x == Integer.parseInt(noOfFidelityEmp))
					error = error+"<br>* Please Enter the details ";
				
				for(int i=0;i<Integer.parseInt(noOfFidelityEmp);i++)
				{
					emp_design[0][i] = emp_design[0][i].trim();
					flag = strValid.isAlphabets(emp_design[0][i]);
					if(!flag)
						error = error+"<br>* Please enter valid employee designation "+(i+1);
				}

				for(int i=0;i<Integer.parseInt(noOfFidelityEmp);i++)
				{
					emp_name[0][i] = emp_name[0][i].trim();
					flag = strValid.isAlphabets(emp_name[0][i]);
					if(!flag)
						error = error+"<br>* Please enter valid employee name "+(i+1);
				}

				for(int i=0;i<Integer.parseInt(noOfFidelityEmp);i++)
				{
					try
					{
						if( (emp_design[0][i].equalsIgnoreCase("") || emp_design[0][i].equalsIgnoreCase("null") || emp_design[0][i]==null) && (emp_name[0][i].equalsIgnoreCase("") || emp_name[0][i].equalsIgnoreCase("null") || emp_name[0][i]==null) )
						{

						}
						else
						{
							if(emp_design[0][i].equalsIgnoreCase("") || emp_design[0][i].equalsIgnoreCase("null") || emp_design[0][i]==null)
								error = error+"<br>* Please enter employee designation "+(i+1);

							if(emp_name[0][i].equalsIgnoreCase("") || emp_name[0][i].equalsIgnoreCase("null") || emp_name[0][i]==null)
								error = error+"<br>* Please enter employee name  "+(i+1);
						}
					}
					catch(Exception e)
					{
						System.out.println("Exception is ...."+e);
						e.printStackTrace();
					}
				}
				
				if(error.length() == 0)
				{	
					int c=0;
					for(int i=0;i<Integer.parseInt(noOfFidelityEmp);i++) //Validation
					{	
						try
						{	
							if( !(emp_name[0][i].equalsIgnoreCase("") || emp_name[0][i].equalsIgnoreCase("null") || emp_name[0][i]==null) && !(emp_design[0][i].equalsIgnoreCase("") || emp_design[0][i].equalsIgnoreCase("null") || emp_design[0][i]==null))
							{
								String temp = "";
								temp = emp_name[0][i]+"~"+emp_design[0][i];
								System.out.println("...fidelity... "+temp);
								fidelityHash.put("fidelity"+(++c),temp);
							}
						}
						catch(Exception e)
						{
							System.out.println("dsfsdfsdf"+e);
							e.printStackTrace();
						}
					}
					System.out.println("...fidelity... "+fidelityHash.size());
					if(AddItem.equalsIgnoreCase("8")) // new Jan16
					{
						if(Integer.parseInt(noOfFidelityEmp) == c){}
						else
							error = error+"<br>* Please enter all employee details  ";
					}
				}
			}

			/** FIDELITY GUARANTEE new - TRVEL BAGGAGE - END **/
		

			System.out.println("Error......"+error); 
			
			if(error.length() >0)
			{	
				System.out.println("<< error In OFSController >>"+error);
				request.setAttribute("error",error);
				dispatcher = request.getRequestDispatcher("/OfficeInsurance/CoverageDis.jsp?AddItem="+AddItem);
			}
			else
			{	
				if(AddItem.equalsIgnoreCase("2"))
				{
					request.setAttribute("msg","Successfully Inserted");
					OSB.portableEquipmentInsert(quoteNo,portHash,AddItem);
				}
				if(AddItem.equalsIgnoreCase("8"))
				{
					request.setAttribute("msg","Successfully Inserted");
					// OSB.fidelityGuaranteeInsert(quoteNo,fidelityHash,AddItem); // OLD
					OSB.fidelityGuaranteeInsertNew(quoteNo,fidelityHash,AddItem); 
				}
				if(AddItem.equalsIgnoreCase("9"))
				{
					request.setAttribute("msg","Successfully Inserted");
					OSB.travelBaggageInsert(quoteNo,fidelityHash,AddItem); 
				}
				if(AddItem.equalsIgnoreCase("10"))
				{
					request.setAttribute("msg","Successfully Inserted");
					OSB.groupPersonalInsert(quoteNo,fidelityHash,AddItem);
				}
				dispatcher = request.getRequestDispatcher("/OfficeInsurance/CoverageDis1.jsp?AddItem="+AddItem);
			}
		}
		
		else if(requestfrom.equalsIgnoreCase("BackToCoverageDis"))
		{
			String AddItem = request.getParameter("AddItem");
			String conTypId = request.getParameter("conTypId");

			AddItem = AddItem == null ?"":AddItem;
			conTypId = conTypId == null ?"":conTypId;
			dispatcher = request.getRequestDispatcher("/OfficeInsurance/CoverageDis.jsp?AddItem="+AddItem);
		}

		else if(requestfrom.equalsIgnoreCase("InfoClear"))
		{
			String qno = "";
			String customerId ="";
			
			qno = request.getParameter("quoteNo");
			qno = qno == null ? "" : qno;

			customerId = request.getParameter("customerId");
			customerId = customerId == null ? "" : customerId;

			if(qno == null || qno.equalsIgnoreCase("null") || qno.equalsIgnoreCase("") || customerId == null || customerId.equalsIgnoreCase("null") || customerId.equalsIgnoreCase("") )
			{
				response.sendRedirect("../CustomerInfo/CustomerInfoOShield.jsp");
			}
			else
			{
				request.setAttribute("customer",customerId);
				request.setAttribute("QuoteNo",qno);
				request.setAttribute("mode","Edit");
				dispatcher = request.getRequestDispatcher("/CustomerInfo/CustomerInfoOShield.jsp?reqPath=OFSController");
			}
		} // InfoClear
		
		
		else if(requestfrom.equalsIgnoreCase("multipleQuotes")) // WIP --- OCT 11
		{
			String[] selectedQuotes = new String[0];
			String[][] declarationQuotes = new String[0][0];
			String conCatQuotesP = "0";
			String msg = "";
			selectedQuotes=request.getParameterValues("selectedQuotes")==null?selectedQuotes:request.getParameterValues("selectedQuotes");
			if(selectedQuotes.length > 1)
			{
				for(int i=0;i<selectedQuotes.length;i++)
					conCatQuotesP=conCatQuotesP+selectedQuotes[i]+",";
			
				conCatQuotesP=conCatQuotesP.substring(0,(conCatQuotesP.length()-1));
				
				error = OSB.customerIdChkingForDeclaration(conCatQuotesP,productId,schemeId);

				session.setAttribute("scheduleQuotes",selectedQuotes);
			}
			else
				msg = "Please Select Atleast Two Quotes.";
			
			if(error.equalsIgnoreCase("Sucess"))
			{
				msg = "";
				dispatcher = request.getRequestDispatcher(path+"/OfficeInsurance/Final1Multiple.jsp?conCatQuotesP="+conCatQuotesP);
			}
			else //if (error.equalsIgnoreCase("Fail"))
			{
				msg = "<font color=red >Please Select Same Customer Id Quotes.</font>";
				dispatcher = request.getRequestDispatcher(path+"/OfficeInsurance/showMultipleQuotes.jsp?msg="+msg);
			}
		}
		// MultipleQuotes

		else if(requestfrom.equalsIgnoreCase("DeclarationReferal")) 
		{
			String qNo[] = new String[0];
			String conCatQuotesP =""; 
			String cusInfo[][] = new String[0][0];
			String remarks = "Declaration Referal for more than 5 different places";
			
			qNo = (String[]) session.getAttribute("scheduleQuotes");
			conCatQuotesP = (String) session.getAttribute("PDFQuoteNo");
			if(qNo.length > 0)
			{
				OSB.updateAdminReferalForDeclaration(qNo,remarks,productId,schemeId);
				cusInfo = OSB.getPolicyInfo(qNo[0],loginId);
				request.setAttribute("multiQuotes",qNo);
				request.setAttribute("CustomerInfo",cusInfo);
				request.setAttribute("allrefValue","Declaration Referral for more than 5 different places");//"Admin Referal"
			}
			dispatcher = request.getRequestDispatcher("/OfficeInsurance/Office_Referral_ShowQuote.jsp");
		}
		
		else if(requestfrom.equalsIgnoreCase("adminProceedInfo"))
		{	
			String adminReferalRemarks="";
			String refQNo ="";
			String Remarks="";
			String[] selectedQuotes = new String[0];
			
			selectedQuotes = request.getParameterValues("selectedQuotes")==null?selectedQuotes:request.getParameterValues("selectedQuotes");
			if(selectedQuotes.length > 1)
			{
				adminReferalRemarks = request.getParameter("adminReferalRemarks")==null?"":request.getParameter("adminReferalRemarks");
				Remarks = request.getParameter("Remarks")==null?"":request.getParameter("Remarks");
				refQNo = request.getParameter("refQNo")==null?"":request.getParameter("refQNo");
				if(refQNo.length() >0)
				{
					refQNo = refQNo.substring(0,refQNo.length()-1);
					OSB.updateDeclarationReferralStatus(refQNo,adminReferalRemarks,Remarks,productId,schemeId);
				}
				System.out.println("adminReferalRemarks..."+adminReferalRemarks);
				System.out.println("Remarks..."+Remarks);
				System.out.println("refQNo..."+refQNo);
				dispatcher = request.getRequestDispatcher("/admin/HomePendingPolicy.jsp");
			}
			else
			{
				request.setAttribute("DeclarationReferalError","Please select quote nos");
				dispatcher = request.getRequestDispatcher("/admin/OfficeReferalDeclarationPolicyByDate.jsp");
			}
		}
		
		else if(requestfrom.equalsIgnoreCase("FromDelete"))
		{
			String AddItem = request.getParameter("AddItem");
			String conTypId = request.getParameter("conTypId");

			AddItem = AddItem == null ?"":AddItem;
			conTypId = conTypId == null ?"":conTypId;
			dispatcher = request.getRequestDispatcher("/OfficeInsurance/CoverageDis.jsp?AddItem="+AddItem);
		}
		
		else if(requestfrom.equalsIgnoreCase("AdminReferralBackInfo"))
		{
			String QuoteNo = request.getParameter("QuoteNo");
			String customer = request.getParameter("customer");
			String reqFrom = request.getParameter("reqFrom")==null?"":request.getParameter("reqFrom");
			if(reqFrom.length()<=0)
				reqFrom=(String)request.getAttribute("reqFrom")==null?"":(String)request.getAttribute("reqFrom");

			QuoteNo = QuoteNo == null ?"":QuoteNo;
			customer = customer == null ?"":customer;
			request.setAttribute("linkFrom", "admin");
			request.setAttribute("quoteNo", QuoteNo);
			request.setAttribute("customerId", customer);
			request.setAttribute("adminstatus", "ShowAccept");
			request.setAttribute("reqFrom",reqFrom);
			
			dispatcher = request.getRequestDispatcher("/servlet/generateValue");
		}

		if (dispatcher != null)
			dispatcher.forward(request, response);
	}

}  // Class   

