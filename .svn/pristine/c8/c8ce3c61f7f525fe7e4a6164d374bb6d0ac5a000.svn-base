package rsa.opencoverpdf;

import java.util.Hashtable;
import java.util.Enumeration;
import java.util.Locale;
import java.util.StringTokenizer;

import com.maan.common.LogManager;
import com.maan.common.exception.BaseException;

public class StringFunction
{
	final static transient private String ENTER = "- Enter";
	final static transient private String EXIT = "- Exit";

	public String initCap(final String str) throws BaseException
	{
		LogManager.push("initCap method(String)");
		LogManager.debug(ENTER);

		StringBuffer retStr = new StringBuffer(12000);
		StringTokenizer token = new StringTokenizer(str, " ");
		while (token.hasMoreTokens()) {
			String temp = token.nextToken();
			retStr.append(temp.substring(0, 1).toUpperCase(Locale.ENGLISH));
			retStr.append(temp.substring(1).toLowerCase(Locale.ENGLISH));
			retStr.append(' ');
		}

		LogManager.debug(EXIT);
		LogManager.popRemove();

		return retStr.toString();
	}

	public String[] split(final String str) throws BaseException
	{
		LogManager.push("split method(String)");
		LogManager.debug(ENTER);

		StringTokenizer stoken = new StringTokenizer(str, ",");
		String[] arr = new String[stoken.countTokens()];
		int iValue = 0;
		while (stoken.hasMoreTokens()) {
			arr[iValue++] = stoken.nextToken();
		}
		if(arr.length == 0){
			arr = new String[1];
			arr[0] = str;
		}

		LogManager.debug(EXIT);
		LogManager.popRemove();

		return arr;
	}

	public String[] split(final String str,final String delimiter) throws BaseException
	{
		LogManager.push("split method(String,string)");
		LogManager.debug(ENTER);

		StringTokenizer stVal = new StringTokenizer(str, delimiter);
		String[] arr = new String[stVal.countTokens()];
		int iValue = 0;
		while (stVal.hasMoreTokens()) {
			arr[iValue++] = stVal.nextToken();
		}
		if (arr.length == 0) {
			arr= new String[1];
			arr[0] = str;
		}

		LogManager.debug(EXIT);
		LogManager.popRemove();

		return arr;
	}

	public StringBuffer queryFormat(final String str[]) throws BaseException
	{
		LogManager.push("queryFormat method(String,string)");
		LogManager.debug(ENTER);

		StringBuffer format = new StringBuffer(10000);

		for (int i = 0; i < str.length; i++) {
			format.append("'");
			format.append(str[i]);
			format.append("',");
		}

		LogManager.debug(EXIT);
		LogManager.popRemove();

		return (format.length() > 0) ? new StringBuffer(format.substring(0,format.length() - 1)) : format;
	}

	public StringBuffer getCommaSeparated(final String str[]) throws BaseException
	{
		LogManager.push("getCommaSeparated method(String,string)");
		LogManager.debug(ENTER);

		StringBuffer format = new StringBuffer();

		for (int i = 0; i < str.length; i++) {
			if (!"".equals(str[i])){
				format.append(str[i]);
				format.append(',');
			}
		}

		LogManager.debug(EXIT);
		LogManager.popRemove();

		return (format.length() > 0) ? new StringBuffer(format.substring(0,	format.length() - 1)) : format;
	}

	public StringBuffer getSeparated(final String str[],final String delim) throws BaseException
	{
		LogManager.push("getSeparated method(String,string)");
		LogManager.debug(ENTER);

		StringBuffer format = new StringBuffer(10000);

		for (int i = 0; i < str.length; i++) {
			if (!"".equals(str[i])){
				format.append(str[i]);
				format.append(delim);
			}
		}

		LogManager.debug(EXIT);
		LogManager.popRemove();

		return (format.length() > 0) ? new StringBuffer(format.substring(0,format.length() - 1)) : format;
	}

	public String[] toArray(final String str[][],final int index) throws BaseException
	{
		LogManager.push("toArray method(String,string)");
		LogManager.debug(ENTER);

		String[] retStr = new String[str.length];

		for (int i = 0; i < str.length; i++) {
			retStr[i] = str[i][index];
		}

		LogManager.debug(EXIT);
		LogManager.popRemove();

		return retStr;
	}

	public Hashtable convertHash(final String[] arr) throws BaseException
	{
		LogManager.push("convertHash method(String,string)");
		LogManager.debug(ENTER);

		Hashtable hash = new Hashtable();
		for (int i = 0; i < arr.length; i++) {
			hash.put(arr[i], arr[i]);
		}

		LogManager.debug(EXIT);
		LogManager.popRemove();

		return hash;
	}

	public Hashtable convertHash(final String[][] arr)   throws BaseException
	{
		LogManager.push("convertHash method(String)");
		LogManager.debug(ENTER);

		Hashtable hash = new Hashtable();
		for (int i = 0; i < arr.length; i++) {
			hash.put(arr[i][0], arr[i][1]);
		}

		LogManager.debug(EXIT);
		LogManager.popRemove();

		return hash;
	}

	public Hashtable convertHashFull(final String[][] arr)  throws BaseException
	{
		LogManager.push("convertHashFull method(String)");
		LogManager.debug(ENTER);

		Hashtable hash = new Hashtable();
		for (int i = 0; i < arr.length; i++) {
			hash.put(arr[i][0], arr[i]);
		}

		LogManager.debug(EXIT);
		LogManager.popRemove();

		return hash;
	}

	public String toString(final Hashtable hash,final  String delimiter) throws BaseException
	{
		LogManager.push("toString method(String)");
		LogManager.debug(ENTER);

		StringBuffer buffer = new StringBuffer();
		Enumeration enumeration = hash.elements();
		while(enumeration.hasMoreElements())
		{
			buffer.append(enumeration.nextElement());
			buffer.append(delimiter);
		}

		int len = buffer.length();

		LogManager.debug(EXIT);
		LogManager.popRemove();

		return len > 0 ? buffer.substring(0, len-1) : "";
	}

	// Added By Karthy
	public String toStringAppend(final String[][] twoArray,final String delimiter,final String status) throws BaseException
	{
		LogManager.push("toStringAppend method(String)");
		LogManager.debug(ENTER);

		StringBuffer buffer = new StringBuffer();

		for (int i = 0; i < twoArray.length; i++) {
			if ("NORMAL".equalsIgnoreCase(status)) {
				buffer.append(twoArray[i][0] + delimiter);

			} else {
				buffer.append(twoArray[i][1] + delimiter);
			}
		}
		int len = buffer.length();

		LogManager.debug(EXIT);
		LogManager.popRemove();

		return len > 0 ? buffer.substring(0, len - 1) : "";
	}

	public String toString(final Hashtable hash,final  String delimiter,final  String enclosed) throws BaseException
	{
		LogManager.push("toString method(String)");
		LogManager.debug(ENTER);

		StringBuffer buffer = new StringBuffer();
		Enumeration enumeration = hash.elements();
		while(enumeration.hasMoreElements()){
			buffer.append(enclosed);
			buffer.append(enumeration.nextElement());
			buffer.append(enclosed);
			buffer.append(delimiter);
		}

		int len = buffer.length();

		LogManager.debug(EXIT);
		LogManager.popRemove();

		return len > 0 ? buffer.substring(0, len-1) : "";
	}
	public String[][] toTwoStringArray(final String content,final String delimiter) throws BaseException
	{
		LogManager.push("toString method(String)");
		LogManager.debug(ENTER);

		StringTokenizer stoken = new StringTokenizer(content,delimiter);
		String[][] twoArray = new String[stoken.countTokens()][1];
		int tValue=0;
		while(stoken.hasMoreTokens()){
			String tokens = stoken.nextToken().trim();
			if(tokens!=null && tokens.length()>0 && !"null".equalsIgnoreCase(tokens)){
				twoArray[tValue++][0] = tokens;
			}
		}

		LogManager.debug(EXIT);
		LogManager.popRemove();

		return twoArray;
	}
}