package com.maan.common;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SimpleLogger {
	private static final SimpleDateFormat sf=new SimpleDateFormat("dd/MM/yyyy hh:mm:ss.SSSSS a");
	public static void info(String strMessage) {
		System.out.println(sf.format(new Date()) + " INFO : " + strMessage);
	}
	public static void debug(Exception exception) {
		System.out.println(sf.format(new Date()) + " DEBUG : ");
		exception.printStackTrace();
	}
	public static void error(Exception exception) {
		System.out.println(sf.format(new Date()) + " ERROR : " + exception);
	}
}
