package com.maan.webservice.rest.resource;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.maan.common.LogManager;
import com.maan.common.login.LogInService;

@Path("login")
public class LoginResource {
	private static final String appID = "16";
	@POST
	@Path("/getReportList")
	@Produces(MediaType.APPLICATION_JSON)
	public Object submit(@FormParam("paramLoginBean") String paramLoginBean) {
		try {
			LogInService service=new LogInService();
			//String[] statuses=service.validateUser(bean.getLoginId(), bean.getPwd(),"",(String)session.get("appID"),(String) session.get("pwdCount"));//===>check method through UserId, Password
		} catch(Exception exception) {
			LogManager.debug(exception);
		}
		return null;
	}

}
