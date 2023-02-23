package com.maan.adminnew.smsEmail;

import java.util.List;

public class SmsEmailMasterService {
	SmsEmailMasterDAO dao=new SmsEmailMasterDAO();

	public List<Object> getSmsEmailTempList() {
		return dao.getSmsEmailTempList();
	}

	public void insertEmaiSmsMaster(String mode, Object[] args) {
		dao.insertEmaiSmsMaster(mode,args);
	}

	public void getEditSmsEmailMaster(SmsEmailMasterBean bean) {
		dao.getEditSmsEmailMaster(bean);
	}


	
	
}
