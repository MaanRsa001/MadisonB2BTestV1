package com.maan.services;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import com.maan.DBCon.DBConnection;
import com.maan.common.LogManager;
import com.maan.common.exception.BaseException;

public class showTableValues {
	public Map tTrnPolicy(final String tableName, final String Condition,
			final String quoteNo) throws BaseException {

		LogManager.push("tTrnPolicy method()");
		LogManager.debug("- Enter");
		
		Connection con=null;
		ResultSet res=null;
		Statement stmt=null;
		ResultSetMetaData rsMetaData;
		String qry;
		Map getValue = null;
		String[] columnName;
		
		try {
			con = DBConnection.getInstance().getDBConnection();
			qry = "select * from " + tableName + " where " + Condition + "='"
					+ quoteNo + "'";

			System.out.println("Query-->"+qry);
			stmt = con.createStatement();
			res = stmt.executeQuery(qry);
			rsMetaData = res.getMetaData();
			columnName = new String[rsMetaData.getColumnCount() + 1];
			
			for (int i = 1; i <= rsMetaData.getColumnCount(); i++) {
				columnName[i] = rsMetaData.getColumnName(i);
			}

			getValue = new HashMap();
			getValue.put("columnname", columnName);

			int index = 1;
			String[] values = new String[columnName.length];
			while (res.next()) {
				values = new String[columnName.length];
				for (int i = 1; i < columnName.length; i++) {
					values[i] = res.getString(columnName[i]);
				}
				getValue.put("value" + index, values);
				index++;
			}
			getValue.put("total", Integer.toString(index));
			
		} catch (Exception e) {
			LogManager.debug(e);
		}
		finally{
			try{
				if(stmt!=null){
					stmt.close();
				}if(res!=null){
					res.close();
				}if(con!=null){
					con.close();
				}
			}catch (Exception e) {
				LogManager.debug(e);
			}
		}
		LogManager.debug("- Exit");
		LogManager.popRemove();
		return getValue;

	}
}