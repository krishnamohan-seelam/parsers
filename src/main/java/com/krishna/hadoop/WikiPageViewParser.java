package com.krishna.hadoop;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;

import org.apache.hadoop.io.Text;

public class WikiPageViewParser {

	
	  private String dateStr;
	  private long longTime;
	  private String siteType;
	  private int requestCount;
	  
	  private static final String delimiter= "\t"; 
	  private static final SimpleDateFormat sdfDDMMYYY =new SimpleDateFormat("dd-MM-yyyy"); 
	  
	  private static final SimpleDateFormat sdfISOTime=new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss"); 
	  
	  public String getDateStr() {
		return dateStr;
	}

	public void setDateStr(String dateStr) {
		this.dateStr = dateStr;
	}

	public long getLongTime() {
		return longTime;
	}

	public void setLongTime(long longTime) {
		this.longTime = longTime;
	}

	public String getSiteType() {
		return siteType;
	}

	public void setSiteType(String siteType) {
		this.siteType = siteType;
	}

	public int getRequestCount() {
		return requestCount;
	}

	public void setRequestCount(int requestCount) {
		this.requestCount = requestCount;
	}


	public void parse(Text recordText)
	  {
		  parse(recordText.toString());
	  }

	private void parse(String recordString) {
		// TODO Auto-generated method stub
		String wikiPV [] = recordString.split(delimiter);
		if( wikiPV.length ==3)
		{
			dateStr =getDateStr(wikiPV[0].replaceAll( "\"", " ").trim());
			longTime = getDateAsTime(dateStr);
			siteType =wikiPV[1].replaceAll( "\"", " ").trim();
			requestCount =isNumeric(wikiPV[2]) ?Integer.parseInt(wikiPV[2]):0;
			
		}
		else
		{
			dateStr="";
			longTime =0L;
			siteType="";
			requestCount=0;
		}
		
	}
	  
	  
	  
	  private long getDateAsTime(String dateStr) {
			// TODO Auto-generated method stub
			try {
					return sdfDDMMYYY.parse(dateStr).getTime();
			}
			 catch(Exception ex)
			{
				 return  0L;
			}
			
		}

		private String getDateStr(String baseDate) {
			// TODO Auto-generated method stub
			try {

				return  sdfDDMMYYY.format((sdfISOTime.parse(baseDate)));

			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}

		}
		
		
		
		public  boolean isNumeric(String inputData) {
			NumberFormat formatter = NumberFormat.getInstance();
			ParsePosition pos = new ParsePosition(0);
			formatter.parse(inputData, pos);
			return inputData.length() == pos.getIndex();
		}
		
		
		public boolean isHeader()
		{
			return (dateStr.equalsIgnoreCase("TIMESTAMP"));
		}
		
		public boolean isValid(String siteType)
		{
			 return  siteType.equalsIgnoreCase("MOBILE")|| siteType.equalsIgnoreCase("DESKTOP");
		}
	  
}
