package com.maan.webservice.rest.bean;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
 

import com.maan.common.LogManager;
import com.maan.common.Validation;

 
public class TravelAction {
	TravelService service=new TravelService();
	Validation validation=new Validation();
	
	public List<CoverageBean> getQuote(Travel travelBean)
	{
		List<String> error=new ArrayList<String>();
		List<CoverageBean> optCoverList=null;
		String result="";
				setDefaultValues(travelBean);
				service.insertOrUpdateCustomerInfo(travelBean,"");				
				travelBean.setCover(service.getCoverInfo(travelBean.getProductId(),travelBean.getSchemeCover(), travelBean.getTravelCover(),travelBean.getBranchCode()));
				result=service.getUpdateTravellersInfo(travelBean);
				if("SUCCESS".equalsIgnoreCase(result)){
					if(!"getSave".equalsIgnoreCase(travelBean.getActionType())){
						result=service.getPremium(travelBean,"C");
						if("SUCCESS".equalsIgnoreCase(result))
							if("getQuote".equalsIgnoreCase(travelBean.getActionType())){
								service.getBackShowQuote(travelBean);								
								optCoverList = service.getOptCoverList(travelBean.getApplicationNo(),travelBean.getTravelCover());
								travelBean.setDisplay("getQuote");								
							}
							else
							{
								travelBean.setDisplay("getQuote");								
							}
						else
							error.add(("error.travel.premium"));
					}else
					{
						travelBean.setDisplay("showQuoteInfo");
					}
				}		 
		return optCoverList;
	}
	private List<Integer> getAge(Travel travelBean){
		List<Integer> ages=new ArrayList<Integer>();
		for(int i=0;i<travelBean.getDobs().size();i++){
			String bday = travelBean.getDobs().get(i);
			Date birthDate=new Date(bday);
			int years = 0;
		      int months = 0;
		      int days = 0;
		      //create calendar object for birth day
		      Calendar birthDay = Calendar.getInstance();
		      birthDay.setTimeInMillis(birthDate.getTime());
		      //create calendar object for current day
		      long currentTime = System.currentTimeMillis();
		      Calendar now = Calendar.getInstance();
		      now.setTimeInMillis(currentTime);
		      //Get difference between years
		      years = now.get(Calendar.YEAR) - birthDay.get(Calendar.YEAR);
		      int currMonth = now.get(Calendar.MONTH) + 1;
		      int birthMonth = birthDay.get(Calendar.MONTH) + 1;
		      //Get difference between months
		      months = currMonth - birthMonth;
		      //if month difference is in negative then reduce years by one and calculate the number of months.
		      if (months < 0)
		      {
		         years--;
		         months = 12 - birthMonth + currMonth;
		         if (now.get(Calendar.DATE) < birthDay.get(Calendar.DATE))
		            months--;
		      } else if (months == 0 && now.get(Calendar.DATE) < birthDay.get(Calendar.DATE))
		      {
		         years--;
		         months = 11;
		      }
		      //Calculate the days
		      if (now.get(Calendar.DATE) > birthDay.get(Calendar.DATE))
		         days = now.get(Calendar.DATE) - birthDay.get(Calendar.DATE);
		      else if (now.get(Calendar.DATE) < birthDay.get(Calendar.DATE))
		      {
		         int today = now.get(Calendar.DAY_OF_MONTH);
		         now.add(Calendar.MONTH, -1);
		         days = now.getActualMaximum(Calendar.DAY_OF_MONTH) - birthDay.get(Calendar.DAY_OF_MONTH) + today;
		      } else
		      {
		         days = 0;
		         if (months == 12)
		         {
		            years++;
		            months = 0;
		         }
		      }
		      //Create new Age object
		       ages.add(years);
		}		 
		return ages;
	}
	private void setDefaultValues(Travel travelBean){
		travelBean.setAges(getAge(travelBean));
		getExpiryDate(travelBean);
	}
	private void getExpiryDate(Travel travelBean)
	{	
		if(StringUtils.isNotBlank(travelBean.getCoverPeriod()) && StringUtils.isNotBlank(travelBean.getInceptionDt()))
		{	
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Calendar c = Calendar.getInstance();
			c.setTime(new Date(travelBean.getInceptionDt())); // Now use today date.
			c.add(Calendar.DATE, Integer.parseInt(travelBean.getCoverPeriod())); // Adding 5 days
			String output = sdf.format(c.getTime());
			System.out.println(output);
			travelBean.setExpiryDt(output);
			/*Date date=new Date(travelBean.getInceptionDt());
				date.setDate(date.getDate()+(Integer.parseInt(travelBean.getCoverPeriod())-1));
	    		String d;
	    		String m;
				if(date.getDate()<10)
				{
					d="0"+date.getDate();
				}else
				{
					d=date.getDate()+"";
				}
				if((date.getMonth()+1)==0)
				{
					m="12";
				}else
					if(((date.getMonth())+1)<10)
					{
						m="0"+((date.getMonth())+1);
					}else
					{
						m=((date.getMonth())+1)+"";
					}
				 int y=date.getYear();
				 String expiryDt=d+"/"+m+"/"+y;
				 travelBean.setExpiryDt(expiryDt);			
		}else {
			 travelBean.setExpiryDt("");
		}*/
	}
	}
	
}
