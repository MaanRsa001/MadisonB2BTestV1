package com.maan.common.util;

import java.math.BigDecimal;
import java.math.RoundingMode;


public class Formatter {
	private static final int decimalPlaces = 2;
	private static final java.text.NumberFormat fmt = new java.text.DecimalFormat("0.00");
	
	public static String amount(double amount) {
		return fmt.format(amount);
	}
	public static String amount(String amount) {
		return fmt.format(amount);
	}
	public static double roundAmount(double value) {
		return round(value,decimalPlaces);
	}
	public static double round(double value,int places) {
		//int places = 4;
		if (places < 0) throw new IllegalArgumentException();

	    BigDecimal bd = new BigDecimal(value);
	    bd = bd.setScale(places, RoundingMode.HALF_UP);
	    return bd.doubleValue();
	}
}
