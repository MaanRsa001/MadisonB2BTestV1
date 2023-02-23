package com.maan.common.interceptor;
/**
 * @author Raja.K
*
* Common Login Template
*/
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
import java.util.Map;
public class SessionInterceptor  implements Interceptor {

	private static final long serialVersionUID = 11742585L;
      
   public String intercept(ActionInvocation actionInvocation) throws Exception {
       Map<String, Object> session = actionInvocation.getInvocationContext().getSession();
       String result;
       if(session.get("user") == null) {
            result = "session";
       }else{
           result = actionInvocation.invoke();
       }
       return result;
   }

   public void destroy() {
       
   }

   public void init() {
       
   }
} 
