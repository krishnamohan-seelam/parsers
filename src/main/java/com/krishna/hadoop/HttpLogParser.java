package com.krishna.hadoop;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.hadoop.io.Text;

public class HttpLogParser {

	 public static final String LOG_ENTRY_PATTERN = "^(\\S+) (\\S+) (\\S+) \\[([\\w:/]+\\s[+\\-]\\d{4})\\] \"(.+?)\" (\\d{3}) (\\d+)";
	 private static final SimpleDateFormat SDF_ISO_TIME=new SimpleDateFormat("dd/MMM/yyyy:hh:mm:ss Z");
	 private String userIP,request;
	 
	 private long timestamp;
	 private int  status,responsebytes;
	 public HttpLogParser()
	 {
		 
	 }
	 
	public String getUserIP() {
		return userIP;
	}
	public String getRequest() {
		return request;
	}
	public long getTimestamp() {
		return timestamp;
	}
	public int getStatus() {
		return status;
	}
	
	public int getResponsebytes() {
		return responsebytes;
	}
	
	 
	 public boolean parse(Text logEntry)
	 {
		  return parse(logEntry.toString());
	 }
	private boolean parse(String logEntry_Str) {
		// TODO Auto-generated method stub
		Pattern p = Pattern.compile(LOG_ENTRY_PATTERN);
		Matcher matcher = p.matcher(logEntry_Str);
		if (!matcher.matches()) {
			System.err.println("Bad Record : "+logEntry_Str);
			userIP = " ";
			request= " ";
			timestamp = 0L;
			status= 0;
			responsebytes=0;
			 return false;
		}
		
		 userIP = matcher.group(1);
		 
		 try {
			timestamp = SDF_ISO_TIME.parse(matcher.group(4)).getTime();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			timestamp =0L;
		}
		 
		 request = matcher.group(5);
		 status = isNumeric(matcher.group(6)) ?Integer.parseInt(matcher.group(6)):0;
		 responsebytes = isNumeric(matcher.group(7)) ?Integer.parseInt(matcher.group(7)):0;
		
		return true;
	}
	
	public  boolean isNumeric(String inputData) {
		NumberFormat formatter = NumberFormat.getInstance();
		ParsePosition pos = new ParsePosition(0);
		formatter.parse(inputData, pos);
		return inputData.length() == pos.getIndex();
	}
}
